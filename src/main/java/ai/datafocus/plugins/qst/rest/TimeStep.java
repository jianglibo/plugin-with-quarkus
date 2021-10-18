package ai.datafocus.plugins.qst.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.quarkus.logging.Log;
import io.quarkus.runtime.annotations.IgnoreProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TimeStep {
  private static final DateTimeFormatter DT_PATTERN =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private String modified_begin;
  @Builder.Default private int page_index = 1;
  private int page_size;
  private int step_days;

  private int empty_result_count;

  /**
   * This factory method will be used before and after the first invoke. after receive from the
   * plugin controller will only use the nextPage method. You must sure that the time range will
   * return data or else it maybe wrongly treat as ended.
   *
   * @param modified_begin
   * @param page_size
   * @param step_days
   */
  public static TimeStep initCreate(String modified_begin, int page_size, int step_days) {
    TimeStep ts =
        TimeStep.builder()
            .modified_begin(modified_begin)
            .page_size(page_size)
            .step_days(step_days)
            .build();
    if (modified_begin == null || modified_begin.trim().isEmpty()) {
      ts.modified_begin = LocalDateTime.now().format(DT_PATTERN);
      Log.warn("no modified_begin specified. Use current time instead.");
    } else {
      try {
        LocalDateTime.parse(modified_begin, DT_PATTERN);
      } catch (DateTimeParseException e) {
        Log.warn("modified_begin not in correct formatter yyyy-MM-dd HH:mm:ss.", e);
        ts.modified_begin = LocalDateTime.now().format(DT_PATTERN);
      }
    }
    return ts;
  }

  private TimeStep nextTimeStep() {
    if (empty_result_count > 0) {
      return null;
    }
    return TimeStep.builder()
        .modified_begin(getModified_end())
        .page_index(1)
        .page_size(page_size)
        .step_days(step_days)
        .empty_result_count(empty_result_count + 1)
        .build();
  }

  /**
   * After each invoke this method will be called. if last invoke return empty result then
   * nextTimeStep will be contructed.
   *
   * <p>if empty_result_count > 1 it means there is no more result.
   *
   * @param last_item_number
   * @return TimeStep
   */
  public TimeStep nextPage(int last_item_number) {
    if (last_item_number < page_size) {
      return nextTimeStep();
    } else {
      return TimeStep.builder()
          .modified_begin(modified_begin)
          .page_index(page_index + 1)
          .page_size(page_size)
          .step_days(step_days)
          .empty_result_count(0)
          .build();
    }
  }

  public OrderQueryBody toOrderQueryBody() throws JsonProcessingException {
    return OrderQueryBody.builder()
        .page_index(getPage_index())
        .page_size(getPage_size())
        .modified_begin(getModified_begin())
        .modified_end(getModified_end())
        .build();
  }

  @IgnoreProperty
  public String getModified_end() {
    return LocalDateTime.parse(modified_begin, DT_PATTERN).plusDays(step_days).format(DT_PATTERN);
  }
}
