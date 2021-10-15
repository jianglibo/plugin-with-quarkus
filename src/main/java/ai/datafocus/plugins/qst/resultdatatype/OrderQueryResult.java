
package ai.datafocus.plugins.qst.resultdatatype;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "page_size",
    "page_index",
    "data_count",
    "page_count",
    "has_next",
    "orders",
    "code",
    "issuccess",
    "msg"
})
@Generated("jsonschema2pojo")
public class OrderQueryResult {

    @JsonProperty("page_size")
    private Integer pageSize;
    @JsonProperty("page_index")
    private Integer pageIndex;
    @JsonProperty("data_count")
    private Integer dataCount;
    @JsonProperty("page_count")
    private Integer pageCount;
    @JsonProperty("has_next")
    private Boolean hasNext;
    @JsonProperty("orders")
    private List<Order> orders = new ArrayList<Order>();
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("issuccess")
    private Boolean issuccess;
    @JsonProperty("msg")
    private String msg;

    @JsonProperty("page_size")
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty("page_size")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonProperty("page_index")
    public Integer getPageIndex() {
        return pageIndex;
    }

    @JsonProperty("page_index")
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    @JsonProperty("data_count")
    public Integer getDataCount() {
        return dataCount;
    }

    @JsonProperty("data_count")
    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    @JsonProperty("page_count")
    public Integer getPageCount() {
        return pageCount;
    }

    @JsonProperty("page_count")
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty("has_next")
    public Boolean getHasNext() {
        return hasNext;
    }

    @JsonProperty("has_next")
    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    @JsonProperty("orders")
    public List<Order> getOrders() {
        return orders;
    }

    @JsonProperty("orders")
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("issuccess")
    public Boolean getIssuccess() {
        return issuccess;
    }

    @JsonProperty("issuccess")
    public void setIssuccess(Boolean issuccess) {
        this.issuccess = issuccess;
    }

    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }

    @JsonProperty("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OrderQueryResult.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("pageSize");
        sb.append('=');
        sb.append(((this.pageSize == null)?"<null>":this.pageSize));
        sb.append(',');
        sb.append("pageIndex");
        sb.append('=');
        sb.append(((this.pageIndex == null)?"<null>":this.pageIndex));
        sb.append(',');
        sb.append("dataCount");
        sb.append('=');
        sb.append(((this.dataCount == null)?"<null>":this.dataCount));
        sb.append(',');
        sb.append("pageCount");
        sb.append('=');
        sb.append(((this.pageCount == null)?"<null>":this.pageCount));
        sb.append(',');
        sb.append("hasNext");
        sb.append('=');
        sb.append(((this.hasNext == null)?"<null>":this.hasNext));
        sb.append(',');
        sb.append("orders");
        sb.append('=');
        sb.append(((this.orders == null)?"<null>":this.orders));
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("issuccess");
        sb.append('=');
        sb.append(((this.issuccess == null)?"<null>":this.issuccess));
        sb.append(',');
        sb.append("msg");
        sb.append('=');
        sb.append(((this.msg == null)?"<null>":this.msg));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.msg == null)? 0 :this.msg.hashCode()));
        result = ((result* 31)+((this.pageCount == null)? 0 :this.pageCount.hashCode()));
        result = ((result* 31)+((this.code == null)? 0 :this.code.hashCode()));
        result = ((result* 31)+((this.issuccess == null)? 0 :this.issuccess.hashCode()));
        result = ((result* 31)+((this.pageIndex == null)? 0 :this.pageIndex.hashCode()));
        result = ((result* 31)+((this.pageSize == null)? 0 :this.pageSize.hashCode()));
        result = ((result* 31)+((this.hasNext == null)? 0 :this.hasNext.hashCode()));
        result = ((result* 31)+((this.orders == null)? 0 :this.orders.hashCode()));
        result = ((result* 31)+((this.dataCount == null)? 0 :this.dataCount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderQueryResult) == false) {
            return false;
        }
        OrderQueryResult rhs = ((OrderQueryResult) other);
        return ((((((((((this.msg == rhs.msg)||((this.msg!= null)&&this.msg.equals(rhs.msg)))&&((this.pageCount == rhs.pageCount)||((this.pageCount!= null)&&this.pageCount.equals(rhs.pageCount))))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))))&&((this.issuccess == rhs.issuccess)||((this.issuccess!= null)&&this.issuccess.equals(rhs.issuccess))))&&((this.pageIndex == rhs.pageIndex)||((this.pageIndex!= null)&&this.pageIndex.equals(rhs.pageIndex))))&&((this.pageSize == rhs.pageSize)||((this.pageSize!= null)&&this.pageSize.equals(rhs.pageSize))))&&((this.hasNext == rhs.hasNext)||((this.hasNext!= null)&&this.hasNext.equals(rhs.hasNext))))&&((this.orders == rhs.orders)||((this.orders!= null)&&this.orders.equals(rhs.orders))))&&((this.dataCount == rhs.dataCount)||((this.dataCount!= null)&&this.dataCount.equals(rhs.dataCount))));
    }

}
