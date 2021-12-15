## 源代码简介
* 入口是EntryCommand.java
* 配置文件是resources/application.yml和对应的MyConfig.java
* 如果不确定某个值从哪里来，那么很可能是从环境变量而来。 环境变量会覆盖application.yml的配置值。

## 下载地址

[https://github.com/jianglibo/plugin-with-quarkus/releases](https://github.com/jianglibo/plugin-with-quarkus/releases)


## Build and run

```bash
./gradlew build "-Dquarkus.package.type=uber-jar" -x test
```

```powershell
Copy-Item .\build\plugin-with-quarkus-0.1.2-SNAPSHOT-runner.jar ..\focus_dcs\dcs-plugin-server\plugin-exec-folder\dcs-plugin-113
```

```powershell
java -jar .\build\plugin-with-quarkus-0.1.2-runner.jar 
```

```bash
java -jar build/*-runner.jar
```

## Example run

```bash
java -jar build/*-runner.jar stdio --name-length=6 --pages=1 --per-page=10
```

```powershell
java -jar .\build\plugin-with-quarkus-1.0.0-SNAPSHOT-runner.jar stdio --name-length=6 --pages=1 --per-page=3
```

The output:

```
08877f24-464a-4867-b58c-6b500349dae1
{
  "state" : {
    "current_id" : 4,
    "current_page" : 2,
    "per_page" : 3,
    "name_length" : 6
  },
  "data" : [ {
    "id" : 1,
    "name" : "lmayft",
    "age" : 31
  }, {
    "id" : 2,
    "name" : "uifmzw",
    "age" : 92
  }, {
    "id" : 3,
    "name" : "obrcxy",
    "age" : 15
  } ]
}
08877f24-464a-4867-b58c-6b500349dae1
```

## some git command
* ./gradlew createRelease
* git tag v0.1.
* git push origin v0.1.
* gh release create v0.1.

## re release
* git tag --delete v0.1.
* git push --delete origin v0.1.
* gh release delete v0.1.