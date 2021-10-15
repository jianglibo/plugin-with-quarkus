
package ai.datafocus.plugins.qst.resultdatatype;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "page_index",
    "page_size",
    "has_next",
    "datas",
    "code",
    "issuccess",
    "msg"
})
@Generated("jsonschema2pojo")
public class ShopQueryResult {

    @JsonProperty("page_index")
    private Integer pageIndex;
    @JsonProperty("page_size")
    private Integer pageSize;
    @JsonProperty("has_next")
    private Boolean hasNext;
    @JsonProperty("datas")
    private List<Data> datas = new ArrayList<Data>();
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("issuccess")
    private Boolean issuccess;
    @JsonProperty("msg")
    private String msg;

    @JsonProperty("page_index")
    public Integer getPageIndex() {
        return pageIndex;
    }

    @JsonProperty("page_index")
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    @JsonProperty("page_size")
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty("page_size")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonProperty("has_next")
    public Boolean getHasNext() {
        return hasNext;
    }

    @JsonProperty("has_next")
    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    @JsonProperty("datas")
    public List<Data> getDatas() {
        return datas;
    }

    @JsonProperty("datas")
    public void setDatas(List<Data> datas) {
        this.datas = datas;
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
        sb.append(ShopQueryResult.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("pageIndex");
        sb.append('=');
        sb.append(((this.pageIndex == null)?"<null>":this.pageIndex));
        sb.append(',');
        sb.append("pageSize");
        sb.append('=');
        sb.append(((this.pageSize == null)?"<null>":this.pageSize));
        sb.append(',');
        sb.append("hasNext");
        sb.append('=');
        sb.append(((this.hasNext == null)?"<null>":this.hasNext));
        sb.append(',');
        sb.append("datas");
        sb.append('=');
        sb.append(((this.datas == null)?"<null>":this.datas));
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
        result = ((result* 31)+((this.code == null)? 0 :this.code.hashCode()));
        result = ((result* 31)+((this.issuccess == null)? 0 :this.issuccess.hashCode()));
        result = ((result* 31)+((this.pageIndex == null)? 0 :this.pageIndex.hashCode()));
        result = ((result* 31)+((this.datas == null)? 0 :this.datas.hashCode()));
        result = ((result* 31)+((this.pageSize == null)? 0 :this.pageSize.hashCode()));
        result = ((result* 31)+((this.hasNext == null)? 0 :this.hasNext.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShopQueryResult) == false) {
            return false;
        }
        ShopQueryResult rhs = ((ShopQueryResult) other);
        return ((((((((this.msg == rhs.msg)||((this.msg!= null)&&this.msg.equals(rhs.msg)))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))))&&((this.issuccess == rhs.issuccess)||((this.issuccess!= null)&&this.issuccess.equals(rhs.issuccess))))&&((this.pageIndex == rhs.pageIndex)||((this.pageIndex!= null)&&this.pageIndex.equals(rhs.pageIndex))))&&((this.datas == rhs.datas)||((this.datas!= null)&&this.datas.equals(rhs.datas))))&&((this.pageSize == rhs.pageSize)||((this.pageSize!= null)&&this.pageSize.equals(rhs.pageSize))))&&((this.hasNext == rhs.hasNext)||((this.hasNext!= null)&&this.hasNext.equals(rhs.hasNext))));
    }

}
