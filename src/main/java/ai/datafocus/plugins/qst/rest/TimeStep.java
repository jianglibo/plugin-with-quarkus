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

  private int partial_result_count;

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
    return TimeStep.builder()
        .modified_begin(getModified_end())
        .page_index(1)
        .page_size(page_size)
        .step_days(step_days)
        .build();
  }

  /**
   * 对于聚水潭的API来说，它是按照日期区间来限定查询。 假设每页50条记录。时间的区间是yyyy-mm-dd 24:00:00 + 7天
   *
   * <p>如果当前返回的记录数小于50，说明这当前这个日期区间没有新的记录了（成立的条件是这个区间是过去的区间，如果还没到这个区间的上限，那么接下来还是可能会有新的记录加入）。
   *
   * <p>这个情况比较复杂需要仔细考虑！！！！！！！在新的设计方案下，返回null肯定是错误的。
   * 
   * <p> 必须结合时间来判断，除非知道区间已经成为过去，不然就可能会造成数据丢失，或者不会步进死循环！！！。
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
