
package ai.datafocus.plugins.qst.resultdatatype;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "co_id",
    "shop_id",
    "shop_name",
    "short_name",
    "shop_site",
    "nick",
    "session_expired",
    "created",
    "shop_url",
    "session_uid",
    "organization"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("co_id")
    private Integer coId;
    @JsonProperty("shop_id")
    private Integer shopId;
    @JsonProperty("shop_name")
    private String shopName;
    @JsonProperty("short_name")
    private String shortName;
    @JsonProperty("shop_site")
    private String shopSite;
    @JsonProperty("nick")
    private String nick;
    @JsonProperty("session_expired")
    private String sessionExpired;
    @JsonProperty("created")
    private Object created;
    @JsonProperty("shop_url")
    private String shopUrl;
    @JsonProperty("session_uid")
    private String sessionUid;
    @JsonProperty("organization")
    private Object organization;

    @JsonProperty("co_id")
    public Integer getCoId() {
        return coId;
    }

    @JsonProperty("co_id")
    public void setCoId(Integer coId) {
        this.coId = coId;
    }

    @JsonProperty("shop_id")
    public Integer getShopId() {
        return shopId;
    }

    @JsonProperty("shop_id")
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @JsonProperty("shop_name")
    public String getShopName() {
        return shopName;
    }

    @JsonProperty("shop_name")
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @JsonProperty("short_name")
    public String getShortName() {
        return shortName;
    }

    @JsonProperty("short_name")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @JsonProperty("shop_site")
    public String getShopSite() {
        return shopSite;
    }

    @JsonProperty("shop_site")
    public void setShopSite(String shopSite) {
        this.shopSite = shopSite;
    }

    @JsonProperty("nick")
    public String getNick() {
        return nick;
    }

    @JsonProperty("nick")
    public void setNick(String nick) {
        this.nick = nick;
    }

    @JsonProperty("session_expired")
    public String getSessionExpired() {
        return sessionExpired;
    }

    @JsonProperty("session_expired")
    public void setSessionExpired(String sessionExpired) {
        this.sessionExpired = sessionExpired;
    }

    @JsonProperty("created")
    public Object getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(Object created) {
        this.created = created;
    }

    @JsonProperty("shop_url")
    public String getShopUrl() {
        return shopUrl;
    }

    @JsonProperty("shop_url")
    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    @JsonProperty("session_uid")
    public String getSessionUid() {
        return sessionUid;
    }

    @JsonProperty("session_uid")
    public void setSessionUid(String sessionUid) {
        this.sessionUid = sessionUid;
    }

    @JsonProperty("organization")
    public Object getOrganization() {
        return organization;
    }

    @JsonProperty("organization")
    public void setOrganization(Object organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("coId");
        sb.append('=');
        sb.append(((this.coId == null)?"<null>":this.coId));
        sb.append(',');
        sb.append("shopId");
        sb.append('=');
        sb.append(((this.shopId == null)?"<null>":this.shopId));
        sb.append(',');
        sb.append("shopName");
        sb.append('=');
        sb.append(((this.shopName == null)?"<null>":this.shopName));
        sb.append(',');
        sb.append("shortName");
        sb.append('=');
        sb.append(((this.shortName == null)?"<null>":this.shortName));
        sb.append(',');
        sb.append("shopSite");
        sb.append('=');
        sb.append(((this.shopSite == null)?"<null>":this.shopSite));
        sb.append(',');
        sb.append("nick");
        sb.append('=');
        sb.append(((this.nick == null)?"<null>":this.nick));
        sb.append(',');
        sb.append("sessionExpired");
        sb.append('=');
        sb.append(((this.sessionExpired == null)?"<null>":this.sessionExpired));
        sb.append(',');
        sb.append("created");
        sb.append('=');
        sb.append(((this.created == null)?"<null>":this.created));
        sb.append(',');
        sb.append("shopUrl");
        sb.append('=');
        sb.append(((this.shopUrl == null)?"<null>":this.shopUrl));
        sb.append(',');
        sb.append("sessionUid");
        sb.append('=');
        sb.append(((this.sessionUid == null)?"<null>":this.sessionUid));
        sb.append(',');
        sb.append("organization");
        sb.append('=');
        sb.append(((this.organization == null)?"<null>":this.organization));
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
        result = ((result* 31)+((this.nick == null)? 0 :this.nick.hashCode()));
        result = ((result* 31)+((this.created == null)? 0 :this.created.hashCode()));
        result = ((result* 31)+((this.shopSite == null)? 0 :this.shopSite.hashCode()));
        result = ((result* 31)+((this.sessionExpired == null)? 0 :this.sessionExpired.hashCode()));
        result = ((result* 31)+((this.sessionUid == null)? 0 :this.sessionUid.hashCode()));
        result = ((result* 31)+((this.organization == null)? 0 :this.organization.hashCode()));
        result = ((result* 31)+((this.coId == null)? 0 :this.coId.hashCode()));
        result = ((result* 31)+((this.shopName == null)? 0 :this.shopName.hashCode()));
        result = ((result* 31)+((this.shopId == null)? 0 :this.shopId.hashCode()));
        result = ((result* 31)+((this.shortName == null)? 0 :this.shortName.hashCode()));
        result = ((result* 31)+((this.shopUrl == null)? 0 :this.shopUrl.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return ((((((((((((this.nick == rhs.nick)||((this.nick!= null)&&this.nick.equals(rhs.nick)))&&((this.created == rhs.created)||((this.created!= null)&&this.created.equals(rhs.created))))&&((this.shopSite == rhs.shopSite)||((this.shopSite!= null)&&this.shopSite.equals(rhs.shopSite))))&&((this.sessionExpired == rhs.sessionExpired)||((this.sessionExpired!= null)&&this.sessionExpired.equals(rhs.sessionExpired))))&&((this.sessionUid == rhs.sessionUid)||((this.sessionUid!= null)&&this.sessionUid.equals(rhs.sessionUid))))&&((this.organization == rhs.organization)||((this.organization!= null)&&this.organization.equals(rhs.organization))))&&((this.coId == rhs.coId)||((this.coId!= null)&&this.coId.equals(rhs.coId))))&&((this.shopName == rhs.shopName)||((this.shopName!= null)&&this.shopName.equals(rhs.shopName))))&&((this.shopId == rhs.shopId)||((this.shopId!= null)&&this.shopId.equals(rhs.shopId))))&&((this.shortName == rhs.shortName)||((this.shortName!= null)&&this.shortName.equals(rhs.shortName))))&&((this.shopUrl == rhs.shopUrl)||((this.shopUrl!= null)&&this.shopUrl.equals(rhs.shopUrl))));
    }

}
