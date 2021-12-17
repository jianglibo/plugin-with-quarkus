package ai.datafocus.plugins.qst.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OsUtil {

  public static Set<PosixFilePermission> SSH_PRIVATE_KEY_PERMISSIONS =
      new HashSet<PosixFilePermission>(
          Arrays.asList(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE));

  public static Set<PosixFilePermission> SSH_PUBLIC_KEY_PERMISSIONS =
      new HashSet<PosixFilePermission>(
          Arrays.asList(
              PosixFilePermission.OWNER_READ,
              PosixFilePermission.OWNER_WRITE,
              PosixFilePermission.GROUP_READ,
              PosixFilePermission.OTHERS_READ));

  public static boolean isWindows =
      System.getProperty("os.name").toLowerCase().startsWith("windows");

  public static Path homeDir() {
    return Paths.get(System.getProperty("user.home"));
  }

  public static Path currentDir() {
    return Paths.get(".");
  }

  public static String deleteUser(String userName) throws InterruptedException, IOException {
    Stream.Builder<String> sb = Stream.builder();
    CommonCommand.CommonCommandBuilder builder =
        CommonCommand.builder()
            .exec("deluser")
            .escalatePrivilege(true)
            .commandLine("--remove-home")
            .commandLine(userName);
    int i = builder.consumer(sb).workingDirectory(OsUtil.currentDir()).build().play();
    if (i != 0) {
      return "failed: " + sb.build().collect(Collectors.joining());
    } else {
      return "";
    }
  }

  public static String createGroup(String groupName) throws InterruptedException, IOException {
    Stream.Builder<String> sb = Stream.builder();
    CommonCommand.CommonCommandBuilder builder =
        CommonCommand.builder().exec("addgroup").escalatePrivilege(true).commandLine(groupName);
    int i = builder.consumer(sb).workingDirectory(OsUtil.currentDir()).build().play();
    if (i != 0) {
      return "failed: " + sb.build().collect(Collectors.joining());
    } else {
      return "";
    }
  }

  public static String createUser(String userName) throws InterruptedException, IOException {
    return createUser(userName, null);
  }

  public static String createUser(String userName, String primaryGroup)
      throws InterruptedException, IOException {
    Stream.Builder<String> sb = Stream.builder();
    CommonCommand.CommonCommandBuilder builder =
        CommonCommand.builder().exec("useradd").escalatePrivilege(true);
    if (primaryGroup != null) {
      builder.commandLine("-g").commandLine(primaryGroup);
    }

    builder.commandLine("--create-home").commandLine(userName);
    int i = builder.consumer(sb).workingDirectory(OsUtil.currentDir()).build().play();
    if (i != 0) {
      return "failed: " + sb.build().collect(Collectors.joining());
    } else {
      return "";
    }
  }

  public static boolean userExists(String userName) {
    if (OsUtil.isWindows) {
      throw new RuntimeException("not implement yet.");
    } else {
      try {
        return Files.lines(Paths.get("/etc/passwd"))
            .anyMatch(line -> line.startsWith(userName + ":"));
      } catch (IOException e) {
        return true;
      }
    }
  }

  public static String killPid(Path pidFile) throws InterruptedException, IOException {
    String pid = new String(Files.readAllBytes(pidFile)).trim();
    Stream.Builder<String> sb = Stream.builder();
    int i =
        CommonCommand.builder()
            .exec("kill")
            .commandLine("-9")
            .commandLine(pid)
            .consumer(sb)
            .build()
            .play();
    if (i != 0) {
      return "failed: " + sb.build().collect(Collectors.joining());
    } else {
      Files.deleteIfExists(pidFile);
      return "";
    }
  }

  public static boolean isRootUser() {
    return "root".equals(System.getProperty("user.name"));
  }

  public static List<String> listUsersInGroup(String groupName) throws IOException {
    final Pattern groupPtn = Pattern.compile(groupName + ":x:(\\d+).*");
    String gid =
        Files.lines(Paths.get("/etc/group"))
            .map(String::trim)
            .map(
                line -> {
                  Matcher m = groupPtn.matcher(line);
                  if (m.matches()) {
                    return m.group(1);
                  } else {
                    return null;
                  }
                })
            .filter(Objects::nonNull)
            .findFirst()
            .orElse(null);
    if (gid != null) {
      final Pattern passwdPtn = Pattern.compile("([^:]+):x:\\d+:" + gid + ":.*");
      return Files.lines(Paths.get("/etc/passwd"))
          .map(String::trim)
          .map(
              line -> {
                Matcher m = passwdPtn.matcher(line);
                if (m.matches()) {
                  return m.group(1);
                } else {
                  return null;
                }
              })
          .filter(Objects::nonNull)
          .collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  public static void deleteDirectory(Path target) throws IOException {
    if (Files.notExists(target)) return;
    try (Stream<Path> walk = Files.walk(target)) {
      walk.sorted(Comparator.reverseOrder())
          .map(Path::toFile)
          .peek(System.out::println)
          .forEach(File::delete);
    }
  }

  public static String Md5OfString(String value) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(value.getBytes());
    byte[] digest = md.digest();
    StringBuilder result = new StringBuilder();
    for (byte aByte : digest) {
      result.append(String.format("%02x", aByte));
    }
    return result.toString();
  }
}
