
package ai.datafocus.plugins.qst.resultdatatype;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sku_id",
    "shop_sku_id",
    "shop_i_id",
    "properties_value",
    "amount",
    "base_price",
    "qty",
    "name",
    "price",
    "outer_oi_id",
    "is_gift",
    "refund_status",
    "refund_id",
    "item_status",
    "i_id",
    "raw_so_id",
    "is_presale",
    "oi_id",
    "pic",
    "sku_type",
    "remark"
})
@Generated("jsonschema2pojo")
public class Item {

    @JsonProperty("sku_id")
    private String skuId;
    @JsonProperty("shop_sku_id")
    private String shopSkuId;
    @JsonProperty("shop_i_id")
    private Object shopIId;
    @JsonProperty("properties_value")
    private Object propertiesValue;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("base_price")
    private Double basePrice;
    @JsonProperty("qty")
    private Integer qty;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("outer_oi_id")
    private String outerOiId;
    @JsonProperty("is_gift")
    private Boolean isGift;
    @JsonProperty("refund_status")
    private Object refundStatus;
    @JsonProperty("refund_id")
    private Object refundId;
    @JsonProperty("item_status")
    private Object itemStatus;
    @JsonProperty("i_id")
    private Object iId;
    @JsonProperty("raw_so_id")
    private String rawSoId;
    @JsonProperty("is_presale")
    private Object isPresale;
    @JsonProperty("oi_id")
    private Integer oiId;
    @JsonProperty("pic")
    private Object pic;
    @JsonProperty("sku_type")
    private String skuType;
    @JsonProperty("remark")
    private Object remark;

    @JsonProperty("sku_id")
    public String getSkuId() {
        return skuId;
    }

    @JsonProperty("sku_id")
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    @JsonProperty("shop_sku_id")
    public String getShopSkuId() {
        return shopSkuId;
    }

    @JsonProperty("shop_sku_id")
    public void setShopSkuId(String shopSkuId) {
        this.shopSkuId = shopSkuId;
    }

    @JsonProperty("shop_i_id")
    public Object getShopIId() {
        return shopIId;
    }

    @JsonProperty("shop_i_id")
    public void setShopIId(Object shopIId) {
        this.shopIId = shopIId;
    }

    @JsonProperty("properties_value")
    public Object getPropertiesValue() {
        return propertiesValue;
    }

    @JsonProperty("properties_value")
    public void setPropertiesValue(Object propertiesValue) {
        this.propertiesValue = propertiesValue;
    }

    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty("base_price")
    public Double getBasePrice() {
        return basePrice;
    }

    @JsonProperty("base_price")
    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    @JsonProperty("qty")
    public Integer getQty() {
        return qty;
    }

    @JsonProperty("qty")
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonProperty("outer_oi_id")
    public String getOuterOiId() {
        return outerOiId;
    }

    @JsonProperty("outer_oi_id")
    public void setOuterOiId(String outerOiId) {
        this.outerOiId = outerOiId;
    }

    @JsonProperty("is_gift")
    public Boolean getIsGift() {
        return isGift;
    }

    @JsonProperty("is_gift")
    public void setIsGift(Boolean isGift) {
        this.isGift = isGift;
    }

    @JsonProperty("refund_status")
    public Object getRefundStatus() {
        return refundStatus;
    }

    @JsonProperty("refund_status")
    public void setRefundStatus(Object refundStatus) {
        this.refundStatus = refundStatus;
    }

    @JsonProperty("refund_id")
    public Object getRefundId() {
        return refundId;
    }

    @JsonProperty("refund_id")
    public void setRefundId(Object refundId) {
        this.refundId = refundId;
    }

    @JsonProperty("item_status")
    public Object getItemStatus() {
        return itemStatus;
    }

    @JsonProperty("item_status")
    public void setItemStatus(Object itemStatus) {
        this.itemStatus = itemStatus;
    }

    @JsonProperty("i_id")
    public Object getiId() {
        return iId;
    }

    @JsonProperty("i_id")
    public void setiId(Object iId) {
        this.iId = iId;
    }

    @JsonProperty("raw_so_id")
    public String getRawSoId() {
        return rawSoId;
    }

    @JsonProperty("raw_so_id")
    public void setRawSoId(String rawSoId) {
        this.rawSoId = rawSoId;
    }

    @JsonProperty("is_presale")
    public Object getIsPresale() {
        return isPresale;
    }

    @JsonProperty("is_presale")
    public void setIsPresale(Object isPresale) {
        this.isPresale = isPresale;
    }

    @JsonProperty("oi_id")
    public Integer getOiId() {
        return oiId;
    }

    @JsonProperty("oi_id")
    public void setOiId(Integer oiId) {
        this.oiId = oiId;
    }

    @JsonProperty("pic")
    public Object getPic() {
        return pic;
    }

    @JsonProperty("pic")
    public void setPic(Object pic) {
        this.pic = pic;
    }

    @JsonProperty("sku_type")
    public String getSkuType() {
        return skuType;
    }

    @JsonProperty("sku_type")
    public void setSkuType(String skuType) {
        this.skuType = skuType;
    }

    @JsonProperty("remark")
    public Object getRemark() {
        return remark;
    }

    @JsonProperty("remark")
    public void setRemark(Object remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Item.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("skuId");
        sb.append('=');
        sb.append(((this.skuId == null)?"<null>":this.skuId));
        sb.append(',');
        sb.append("shopSkuId");
        sb.append('=');
        sb.append(((this.shopSkuId == null)?"<null>":this.shopSkuId));
        sb.append(',');
        sb.append("shopIId");
        sb.append('=');
        sb.append(((this.shopIId == null)?"<null>":this.shopIId));
        sb.append(',');
        sb.append("propertiesValue");
        sb.append('=');
        sb.append(((this.propertiesValue == null)?"<null>":this.propertiesValue));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("basePrice");
        sb.append('=');
        sb.append(((this.basePrice == null)?"<null>":this.basePrice));
        sb.append(',');
        sb.append("qty");
        sb.append('=');
        sb.append(((this.qty == null)?"<null>":this.qty));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("outerOiId");
        sb.append('=');
        sb.append(((this.outerOiId == null)?"<null>":this.outerOiId));
        sb.append(',');
        sb.append("isGift");
        sb.append('=');
        sb.append(((this.isGift == null)?"<null>":this.isGift));
        sb.append(',');
        sb.append("refundStatus");
        sb.append('=');
        sb.append(((this.refundStatus == null)?"<null>":this.refundStatus));
        sb.append(',');
        sb.append("refundId");
        sb.append('=');
        sb.append(((this.refundId == null)?"<null>":this.refundId));
        sb.append(',');
        sb.append("itemStatus");
        sb.append('=');
        sb.append(((this.itemStatus == null)?"<null>":this.itemStatus));
        sb.append(',');
        sb.append("iId");
        sb.append('=');
        sb.append(((this.iId == null)?"<null>":this.iId));
        sb.append(',');
        sb.append("rawSoId");
        sb.append('=');
        sb.append(((this.rawSoId == null)?"<null>":this.rawSoId));
        sb.append(',');
        sb.append("isPresale");
        sb.append('=');
        sb.append(((this.isPresale == null)?"<null>":this.isPresale));
        sb.append(',');
        sb.append("oiId");
        sb.append('=');
        sb.append(((this.oiId == null)?"<null>":this.oiId));
        sb.append(',');
        sb.append("pic");
        sb.append('=');
        sb.append(((this.pic == null)?"<null>":this.pic));
        sb.append(',');
        sb.append("skuType");
        sb.append('=');
        sb.append(((this.skuType == null)?"<null>":this.skuType));
        sb.append(',');
        sb.append("remark");
        sb.append('=');
        sb.append(((this.remark == null)?"<null>":this.remark));
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
        result = ((result* 31)+((this.oiId == null)? 0 :this.oiId.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.iId == null)? 0 :this.iId.hashCode()));
        result = ((result* 31)+((this.refundStatus == null)? 0 :this.refundStatus.hashCode()));
        result = ((result* 31)+((this.remark == null)? 0 :this.remark.hashCode()));
        result = ((result* 31)+((this.pic == null)? 0 :this.pic.hashCode()));
        result = ((result* 31)+((this.shopSkuId == null)? 0 :this.shopSkuId.hashCode()));
        result = ((result* 31)+((this.skuType == null)? 0 :this.skuType.hashCode()));
        result = ((result* 31)+((this.propertiesValue == null)? 0 :this.propertiesValue.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.qty == null)? 0 :this.qty.hashCode()));
        result = ((result* 31)+((this.itemStatus == null)? 0 :this.itemStatus.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.isPresale == null)? 0 :this.isPresale.hashCode()));
        result = ((result* 31)+((this.isGift == null)? 0 :this.isGift.hashCode()));
        result = ((result* 31)+((this.shopIId == null)? 0 :this.shopIId.hashCode()));
        result = ((result* 31)+((this.outerOiId == null)? 0 :this.outerOiId.hashCode()));
        result = ((result* 31)+((this.skuId == null)? 0 :this.skuId.hashCode()));
        result = ((result* 31)+((this.refundId == null)? 0 :this.refundId.hashCode()));
        result = ((result* 31)+((this.basePrice == null)? 0 :this.basePrice.hashCode()));
        result = ((result* 31)+((this.rawSoId == null)? 0 :this.rawSoId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return ((((((((((((((((((((((this.oiId == rhs.oiId)||((this.oiId!= null)&&this.oiId.equals(rhs.oiId)))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.iId == rhs.iId)||((this.iId!= null)&&this.iId.equals(rhs.iId))))&&((this.refundStatus == rhs.refundStatus)||((this.refundStatus!= null)&&this.refundStatus.equals(rhs.refundStatus))))&&((this.remark == rhs.remark)||((this.remark!= null)&&this.remark.equals(rhs.remark))))&&((this.pic == rhs.pic)||((this.pic!= null)&&this.pic.equals(rhs.pic))))&&((this.shopSkuId == rhs.shopSkuId)||((this.shopSkuId!= null)&&this.shopSkuId.equals(rhs.shopSkuId))))&&((this.skuType == rhs.skuType)||((this.skuType!= null)&&this.skuType.equals(rhs.skuType))))&&((this.propertiesValue == rhs.propertiesValue)||((this.propertiesValue!= null)&&this.propertiesValue.equals(rhs.propertiesValue))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.qty == rhs.qty)||((this.qty!= null)&&this.qty.equals(rhs.qty))))&&((this.itemStatus == rhs.itemStatus)||((this.itemStatus!= null)&&this.itemStatus.equals(rhs.itemStatus))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.isPresale == rhs.isPresale)||((this.isPresale!= null)&&this.isPresale.equals(rhs.isPresale))))&&((this.isGift == rhs.isGift)||((this.isGift!= null)&&this.isGift.equals(rhs.isGift))))&&((this.shopIId == rhs.shopIId)||((this.shopIId!= null)&&this.shopIId.equals(rhs.shopIId))))&&((this.outerOiId == rhs.outerOiId)||((this.outerOiId!= null)&&this.outerOiId.equals(rhs.outerOiId))))&&((this.skuId == rhs.skuId)||((this.skuId!= null)&&this.skuId.equals(rhs.skuId))))&&((this.refundId == rhs.refundId)||((this.refundId!= null)&&this.refundId.equals(rhs.refundId))))&&((this.basePrice == rhs.basePrice)||((this.basePrice!= null)&&this.basePrice.equals(rhs.basePrice))))&&((this.rawSoId == rhs.rawSoId)||((this.rawSoId!= null)&&this.rawSoId.equals(rhs.rawSoId))));
    }

}
