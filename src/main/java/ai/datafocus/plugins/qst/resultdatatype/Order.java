
package ai.datafocus.plugins.qst.resultdatatype;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "o_id",
    "shop_id",
    "so_id",
    "order_date",
    "shop_status",
    "question_type",
    "question_desc",
    "node",
    "status",
    "buyer_id",
    "sign_time",
    "shop_buyer_id",
    "receiver_country",
    "receiver_state",
    "receiver_city",
    "receiver_district",
    "receiver_town",
    "receiver_address",
    "receiver_name",
    "receiver_mobile",
    "send_date",
    "plan_delivery_date",
    "creator_name",
    "buyer_tax_no",
    "invoice_type",
    "pay_amount",
    "freight",
    "buyer_message",
    "remark",
    "invoice_title",
    "is_cod",
    "items",
    "type",
    "shop_site",
    "paid_amount",
    "pay_date",
    "outer_pay_id",
    "modified",
    "receiver_zip",
    "order_from",
    "pays",
    "shop_name",
    "l_id",
    "wms_co_id",
    "logistics_company",
    "free_amount",
    "co_id",
    "drp_co_id_from",
    "drp_co_id_to",
    "labels",
    "currency",
    "lc_id",
    "skus",
    "un_lid",
    "f_freight",
    "end_time",
    "seller_flag",
    "receiver_email",
    "referrer_id",
    "referrer_name",
    "created"
})
@Generated("jsonschema2pojo")
public class Order {

    @JsonProperty("o_id")
    private Integer oId;
    @JsonProperty("shop_id")
    private Integer shopId;
    @JsonProperty("so_id")
    private String soId;
    @JsonProperty("order_date")
    private String orderDate;
    @JsonProperty("shop_status")
    private String shopStatus;
    @JsonProperty("question_type")
    private String questionType;
    @JsonProperty("question_desc")
    private Object questionDesc;
    @JsonProperty("node")
    private Object node;
    @JsonProperty("status")
    private String status;
    @JsonProperty("buyer_id")
    private String buyerId;
    @JsonProperty("sign_time")
    private Object signTime;
    @JsonProperty("shop_buyer_id")
    private Object shopBuyerId;
    @JsonProperty("receiver_country")
    private Object receiverCountry;
    @JsonProperty("receiver_state")
    private Object receiverState;
    @JsonProperty("receiver_city")
    private Object receiverCity;
    @JsonProperty("receiver_district")
    private Object receiverDistrict;
    @JsonProperty("receiver_town")
    private Object receiverTown;
    @JsonProperty("receiver_address")
    private Object receiverAddress;
    @JsonProperty("receiver_name")
    private Object receiverName;
    @JsonProperty("receiver_mobile")
    private Object receiverMobile;
    @JsonProperty("send_date")
    private Object sendDate;
    @JsonProperty("plan_delivery_date")
    private Object planDeliveryDate;
    @JsonProperty("creator_name")
    private Object creatorName;
    @JsonProperty("buyer_tax_no")
    private Object buyerTaxNo;
    @JsonProperty("invoice_type")
    private Object invoiceType;
    @JsonProperty("pay_amount")
    private Double payAmount;
    @JsonProperty("freight")
    private Double freight;
    @JsonProperty("buyer_message")
    private Object buyerMessage;
    @JsonProperty("remark")
    private Object remark;
    @JsonProperty("invoice_title")
    private Object invoiceTitle;
    @JsonProperty("is_cod")
    private Boolean isCod;
    @JsonProperty("items")
    private List<Item> items = new ArrayList<Item>();
    @JsonProperty("type")
    private String type;
    @JsonProperty("shop_site")
    private String shopSite;
    @JsonProperty("paid_amount")
    private Double paidAmount;
    @JsonProperty("pay_date")
    private String payDate;
    @JsonProperty("outer_pay_id")
    private String outerPayId;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("receiver_zip")
    private Object receiverZip;
    @JsonProperty("order_from")
    private String orderFrom;
    @JsonProperty("pays")
    private List<Pay> pays = new ArrayList<Pay>();
    @JsonProperty("shop_name")
    private String shopName;
    @JsonProperty("l_id")
    private String lId;
    @JsonProperty("wms_co_id")
    private Integer wmsCoId;
    @JsonProperty("logistics_company")
    private String logisticsCompany;
    @JsonProperty("free_amount")
    private String freeAmount;
    @JsonProperty("co_id")
    private Integer coId;
    @JsonProperty("drp_co_id_from")
    private Object drpCoIdFrom;
    @JsonProperty("drp_co_id_to")
    private Object drpCoIdTo;
    @JsonProperty("labels")
    private String labels;
    @JsonProperty("currency")
    private Object currency;
    @JsonProperty("lc_id")
    private String lcId;
    @JsonProperty("skus")
    private String skus;
    @JsonProperty("un_lid")
    private Object unLid;
    @JsonProperty("f_freight")
    private Object fFreight;
    @JsonProperty("end_time")
    private Object endTime;
    @JsonProperty("seller_flag")
    private Object sellerFlag;
    @JsonProperty("receiver_email")
    private Object receiverEmail;
    @JsonProperty("referrer_id")
    private Object referrerId;
    @JsonProperty("referrer_name")
    private Object referrerName;
    @JsonProperty("created")
    private String created;

    @JsonProperty("o_id")
    public Integer getoId() {
        return oId;
    }

    @JsonProperty("o_id")
    public void setoId(Integer oId) {
        this.oId = oId;
    }

    @JsonProperty("shop_id")
    public Integer getShopId() {
        return shopId;
    }

    @JsonProperty("shop_id")
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @JsonProperty("so_id")
    public String getSoId() {
        return soId;
    }

    @JsonProperty("so_id")
    public void setSoId(String soId) {
        this.soId = soId;
    }

    @JsonProperty("order_date")
    public String getOrderDate() {
        return orderDate;
    }

    @JsonProperty("order_date")
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @JsonProperty("shop_status")
    public String getShopStatus() {
        return shopStatus;
    }

    @JsonProperty("shop_status")
    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    @JsonProperty("question_type")
    public String getQuestionType() {
        return questionType;
    }

    @JsonProperty("question_type")
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @JsonProperty("question_desc")
    public Object getQuestionDesc() {
        return questionDesc;
    }

    @JsonProperty("question_desc")
    public void setQuestionDesc(Object questionDesc) {
        this.questionDesc = questionDesc;
    }

    @JsonProperty("node")
    public Object getNode() {
        return node;
    }

    @JsonProperty("node")
    public void setNode(Object node) {
        this.node = node;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("buyer_id")
    public String getBuyerId() {
        return buyerId;
    }

    @JsonProperty("buyer_id")
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    @JsonProperty("sign_time")
    public Object getSignTime() {
        return signTime;
    }

    @JsonProperty("sign_time")
    public void setSignTime(Object signTime) {
        this.signTime = signTime;
    }

    @JsonProperty("shop_buyer_id")
    public Object getShopBuyerId() {
        return shopBuyerId;
    }

    @JsonProperty("shop_buyer_id")
    public void setShopBuyerId(Object shopBuyerId) {
        this.shopBuyerId = shopBuyerId;
    }

    @JsonProperty("receiver_country")
    public Object getReceiverCountry() {
        return receiverCountry;
    }

    @JsonProperty("receiver_country")
    public void setReceiverCountry(Object receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    @JsonProperty("receiver_state")
    public Object getReceiverState() {
        return receiverState;
    }

    @JsonProperty("receiver_state")
    public void setReceiverState(Object receiverState) {
        this.receiverState = receiverState;
    }

    @JsonProperty("receiver_city")
    public Object getReceiverCity() {
        return receiverCity;
    }

    @JsonProperty("receiver_city")
    public void setReceiverCity(Object receiverCity) {
        this.receiverCity = receiverCity;
    }

    @JsonProperty("receiver_district")
    public Object getReceiverDistrict() {
        return receiverDistrict;
    }

    @JsonProperty("receiver_district")
    public void setReceiverDistrict(Object receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    @JsonProperty("receiver_town")
    public Object getReceiverTown() {
        return receiverTown;
    }

    @JsonProperty("receiver_town")
    public void setReceiverTown(Object receiverTown) {
        this.receiverTown = receiverTown;
    }

    @JsonProperty("receiver_address")
    public Object getReceiverAddress() {
        return receiverAddress;
    }

    @JsonProperty("receiver_address")
    public void setReceiverAddress(Object receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    @JsonProperty("receiver_name")
    public Object getReceiverName() {
        return receiverName;
    }

    @JsonProperty("receiver_name")
    public void setReceiverName(Object receiverName) {
        this.receiverName = receiverName;
    }

    @JsonProperty("receiver_mobile")
    public Object getReceiverMobile() {
        return receiverMobile;
    }

    @JsonProperty("receiver_mobile")
    public void setReceiverMobile(Object receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    @JsonProperty("send_date")
    public Object getSendDate() {
        return sendDate;
    }

    @JsonProperty("send_date")
    public void setSendDate(Object sendDate) {
        this.sendDate = sendDate;
    }

    @JsonProperty("plan_delivery_date")
    public Object getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    @JsonProperty("plan_delivery_date")
    public void setPlanDeliveryDate(Object planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    @JsonProperty("creator_name")
    public Object getCreatorName() {
        return creatorName;
    }

    @JsonProperty("creator_name")
    public void setCreatorName(Object creatorName) {
        this.creatorName = creatorName;
    }

    @JsonProperty("buyer_tax_no")
    public Object getBuyerTaxNo() {
        return buyerTaxNo;
    }

    @JsonProperty("buyer_tax_no")
    public void setBuyerTaxNo(Object buyerTaxNo) {
        this.buyerTaxNo = buyerTaxNo;
    }

    @JsonProperty("invoice_type")
    public Object getInvoiceType() {
        return invoiceType;
    }

    @JsonProperty("invoice_type")
    public void setInvoiceType(Object invoiceType) {
        this.invoiceType = invoiceType;
    }

    @JsonProperty("pay_amount")
    public Double getPayAmount() {
        return payAmount;
    }

    @JsonProperty("pay_amount")
    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    @JsonProperty("freight")
    public Double getFreight() {
        return freight;
    }

    @JsonProperty("freight")
    public void setFreight(Double freight) {
        this.freight = freight;
    }

    @JsonProperty("buyer_message")
    public Object getBuyerMessage() {
        return buyerMessage;
    }

    @JsonProperty("buyer_message")
    public void setBuyerMessage(Object buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    @JsonProperty("remark")
    public Object getRemark() {
        return remark;
    }

    @JsonProperty("remark")
    public void setRemark(Object remark) {
        this.remark = remark;
    }

    @JsonProperty("invoice_title")
    public Object getInvoiceTitle() {
        return invoiceTitle;
    }

    @JsonProperty("invoice_title")
    public void setInvoiceTitle(Object invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    @JsonProperty("is_cod")
    public Boolean getIsCod() {
        return isCod;
    }

    @JsonProperty("is_cod")
    public void setIsCod(Boolean isCod) {
        this.isCod = isCod;
    }

    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("shop_site")
    public String getShopSite() {
        return shopSite;
    }

    @JsonProperty("shop_site")
    public void setShopSite(String shopSite) {
        this.shopSite = shopSite;
    }

    @JsonProperty("paid_amount")
    public Double getPaidAmount() {
        return paidAmount;
    }

    @JsonProperty("paid_amount")
    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    @JsonProperty("pay_date")
    public String getPayDate() {
        return payDate;
    }

    @JsonProperty("pay_date")
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    @JsonProperty("outer_pay_id")
    public String getOuterPayId() {
        return outerPayId;
    }

    @JsonProperty("outer_pay_id")
    public void setOuterPayId(String outerPayId) {
        this.outerPayId = outerPayId;
    }

    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    @JsonProperty("modified")
    public void setModified(String modified) {
        this.modified = modified;
    }

    @JsonProperty("receiver_zip")
    public Object getReceiverZip() {
        return receiverZip;
    }

    @JsonProperty("receiver_zip")
    public void setReceiverZip(Object receiverZip) {
        this.receiverZip = receiverZip;
    }

    @JsonProperty("order_from")
    public String getOrderFrom() {
        return orderFrom;
    }

    @JsonProperty("order_from")
    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    @JsonProperty("pays")
    public List<Pay> getPays() {
        return pays;
    }

    @JsonProperty("pays")
    public void setPays(List<Pay> pays) {
        this.pays = pays;
    }

    @JsonProperty("shop_name")
    public String getShopName() {
        return shopName;
    }

    @JsonProperty("shop_name")
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @JsonProperty("l_id")
    public String getlId() {
        return lId;
    }

    @JsonProperty("l_id")
    public void setlId(String lId) {
        this.lId = lId;
    }

    @JsonProperty("wms_co_id")
    public Integer getWmsCoId() {
        return wmsCoId;
    }

    @JsonProperty("wms_co_id")
    public void setWmsCoId(Integer wmsCoId) {
        this.wmsCoId = wmsCoId;
    }

    @JsonProperty("logistics_company")
    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    @JsonProperty("logistics_company")
    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    @JsonProperty("free_amount")
    public String getFreeAmount() {
        return freeAmount;
    }

    @JsonProperty("free_amount")
    public void setFreeAmount(String freeAmount) {
        this.freeAmount = freeAmount;
    }

    @JsonProperty("co_id")
    public Integer getCoId() {
        return coId;
    }

    @JsonProperty("co_id")
    public void setCoId(Integer coId) {
        this.coId = coId;
    }

    @JsonProperty("drp_co_id_from")
    public Object getDrpCoIdFrom() {
        return drpCoIdFrom;
    }

    @JsonProperty("drp_co_id_from")
    public void setDrpCoIdFrom(Object drpCoIdFrom) {
        this.drpCoIdFrom = drpCoIdFrom;
    }

    @JsonProperty("drp_co_id_to")
    public Object getDrpCoIdTo() {
        return drpCoIdTo;
    }

    @JsonProperty("drp_co_id_to")
    public void setDrpCoIdTo(Object drpCoIdTo) {
        this.drpCoIdTo = drpCoIdTo;
    }

    @JsonProperty("labels")
    public String getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(String labels) {
        this.labels = labels;
    }

    @JsonProperty("currency")
    public Object getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    @JsonProperty("lc_id")
    public String getLcId() {
        return lcId;
    }

    @JsonProperty("lc_id")
    public void setLcId(String lcId) {
        this.lcId = lcId;
    }

    @JsonProperty("skus")
    public String getSkus() {
        return skus;
    }

    @JsonProperty("skus")
    public void setSkus(String skus) {
        this.skus = skus;
    }

    @JsonProperty("un_lid")
    public Object getUnLid() {
        return unLid;
    }

    @JsonProperty("un_lid")
    public void setUnLid(Object unLid) {
        this.unLid = unLid;
    }

    @JsonProperty("f_freight")
    public Object getfFreight() {
        return fFreight;
    }

    @JsonProperty("f_freight")
    public void setfFreight(Object fFreight) {
        this.fFreight = fFreight;
    }

    @JsonProperty("end_time")
    public Object getEndTime() {
        return endTime;
    }

    @JsonProperty("end_time")
    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("seller_flag")
    public Object getSellerFlag() {
        return sellerFlag;
    }

    @JsonProperty("seller_flag")
    public void setSellerFlag(Object sellerFlag) {
        this.sellerFlag = sellerFlag;
    }

    @JsonProperty("receiver_email")
    public Object getReceiverEmail() {
        return receiverEmail;
    }

    @JsonProperty("receiver_email")
    public void setReceiverEmail(Object receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    @JsonProperty("referrer_id")
    public Object getReferrerId() {
        return referrerId;
    }

    @JsonProperty("referrer_id")
    public void setReferrerId(Object referrerId) {
        this.referrerId = referrerId;
    }

    @JsonProperty("referrer_name")
    public Object getReferrerName() {
        return referrerName;
    }

    @JsonProperty("referrer_name")
    public void setReferrerName(Object referrerName) {
        this.referrerName = referrerName;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Order.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("oId");
        sb.append('=');
        sb.append(((this.oId == null)?"<null>":this.oId));
        sb.append(',');
        sb.append("shopId");
        sb.append('=');
        sb.append(((this.shopId == null)?"<null>":this.shopId));
        sb.append(',');
        sb.append("soId");
        sb.append('=');
        sb.append(((this.soId == null)?"<null>":this.soId));
        sb.append(',');
        sb.append("orderDate");
        sb.append('=');
        sb.append(((this.orderDate == null)?"<null>":this.orderDate));
        sb.append(',');
        sb.append("shopStatus");
        sb.append('=');
        sb.append(((this.shopStatus == null)?"<null>":this.shopStatus));
        sb.append(',');
        sb.append("questionType");
        sb.append('=');
        sb.append(((this.questionType == null)?"<null>":this.questionType));
        sb.append(',');
        sb.append("questionDesc");
        sb.append('=');
        sb.append(((this.questionDesc == null)?"<null>":this.questionDesc));
        sb.append(',');
        sb.append("node");
        sb.append('=');
        sb.append(((this.node == null)?"<null>":this.node));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("buyerId");
        sb.append('=');
        sb.append(((this.buyerId == null)?"<null>":this.buyerId));
        sb.append(',');
        sb.append("signTime");
        sb.append('=');
        sb.append(((this.signTime == null)?"<null>":this.signTime));
        sb.append(',');
        sb.append("shopBuyerId");
        sb.append('=');
        sb.append(((this.shopBuyerId == null)?"<null>":this.shopBuyerId));
        sb.append(',');
        sb.append("receiverCountry");
        sb.append('=');
        sb.append(((this.receiverCountry == null)?"<null>":this.receiverCountry));
        sb.append(',');
        sb.append("receiverState");
        sb.append('=');
        sb.append(((this.receiverState == null)?"<null>":this.receiverState));
        sb.append(',');
        sb.append("receiverCity");
        sb.append('=');
        sb.append(((this.receiverCity == null)?"<null>":this.receiverCity));
        sb.append(',');
        sb.append("receiverDistrict");
        sb.append('=');
        sb.append(((this.receiverDistrict == null)?"<null>":this.receiverDistrict));
        sb.append(',');
        sb.append("receiverTown");
        sb.append('=');
        sb.append(((this.receiverTown == null)?"<null>":this.receiverTown));
        sb.append(',');
        sb.append("receiverAddress");
        sb.append('=');
        sb.append(((this.receiverAddress == null)?"<null>":this.receiverAddress));
        sb.append(',');
        sb.append("receiverName");
        sb.append('=');
        sb.append(((this.receiverName == null)?"<null>":this.receiverName));
        sb.append(',');
        sb.append("receiverMobile");
        sb.append('=');
        sb.append(((this.receiverMobile == null)?"<null>":this.receiverMobile));
        sb.append(',');
        sb.append("sendDate");
        sb.append('=');
        sb.append(((this.sendDate == null)?"<null>":this.sendDate));
        sb.append(',');
        sb.append("planDeliveryDate");
        sb.append('=');
        sb.append(((this.planDeliveryDate == null)?"<null>":this.planDeliveryDate));
        sb.append(',');
        sb.append("creatorName");
        sb.append('=');
        sb.append(((this.creatorName == null)?"<null>":this.creatorName));
        sb.append(',');
        sb.append("buyerTaxNo");
        sb.append('=');
        sb.append(((this.buyerTaxNo == null)?"<null>":this.buyerTaxNo));
        sb.append(',');
        sb.append("invoiceType");
        sb.append('=');
        sb.append(((this.invoiceType == null)?"<null>":this.invoiceType));
        sb.append(',');
        sb.append("payAmount");
        sb.append('=');
        sb.append(((this.payAmount == null)?"<null>":this.payAmount));
        sb.append(',');
        sb.append("freight");
        sb.append('=');
        sb.append(((this.freight == null)?"<null>":this.freight));
        sb.append(',');
        sb.append("buyerMessage");
        sb.append('=');
        sb.append(((this.buyerMessage == null)?"<null>":this.buyerMessage));
        sb.append(',');
        sb.append("remark");
        sb.append('=');
        sb.append(((this.remark == null)?"<null>":this.remark));
        sb.append(',');
        sb.append("invoiceTitle");
        sb.append('=');
        sb.append(((this.invoiceTitle == null)?"<null>":this.invoiceTitle));
        sb.append(',');
        sb.append("isCod");
        sb.append('=');
        sb.append(((this.isCod == null)?"<null>":this.isCod));
        sb.append(',');
        sb.append("items");
        sb.append('=');
        sb.append(((this.items == null)?"<null>":this.items));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("shopSite");
        sb.append('=');
        sb.append(((this.shopSite == null)?"<null>":this.shopSite));
        sb.append(',');
        sb.append("paidAmount");
        sb.append('=');
        sb.append(((this.paidAmount == null)?"<null>":this.paidAmount));
        sb.append(',');
        sb.append("payDate");
        sb.append('=');
        sb.append(((this.payDate == null)?"<null>":this.payDate));
        sb.append(',');
        sb.append("outerPayId");
        sb.append('=');
        sb.append(((this.outerPayId == null)?"<null>":this.outerPayId));
        sb.append(',');
        sb.append("modified");
        sb.append('=');
        sb.append(((this.modified == null)?"<null>":this.modified));
        sb.append(',');
        sb.append("receiverZip");
        sb.append('=');
        sb.append(((this.receiverZip == null)?"<null>":this.receiverZip));
        sb.append(',');
        sb.append("orderFrom");
        sb.append('=');
        sb.append(((this.orderFrom == null)?"<null>":this.orderFrom));
        sb.append(',');
        sb.append("pays");
        sb.append('=');
        sb.append(((this.pays == null)?"<null>":this.pays));
        sb.append(',');
        sb.append("shopName");
        sb.append('=');
        sb.append(((this.shopName == null)?"<null>":this.shopName));
        sb.append(',');
        sb.append("lId");
        sb.append('=');
        sb.append(((this.lId == null)?"<null>":this.lId));
        sb.append(',');
        sb.append("wmsCoId");
        sb.append('=');
        sb.append(((this.wmsCoId == null)?"<null>":this.wmsCoId));
        sb.append(',');
        sb.append("logisticsCompany");
        sb.append('=');
        sb.append(((this.logisticsCompany == null)?"<null>":this.logisticsCompany));
        sb.append(',');
        sb.append("freeAmount");
        sb.append('=');
        sb.append(((this.freeAmount == null)?"<null>":this.freeAmount));
        sb.append(',');
        sb.append("coId");
        sb.append('=');
        sb.append(((this.coId == null)?"<null>":this.coId));
        sb.append(',');
        sb.append("drpCoIdFrom");
        sb.append('=');
        sb.append(((this.drpCoIdFrom == null)?"<null>":this.drpCoIdFrom));
        sb.append(',');
        sb.append("drpCoIdTo");
        sb.append('=');
        sb.append(((this.drpCoIdTo == null)?"<null>":this.drpCoIdTo));
        sb.append(',');
        sb.append("labels");
        sb.append('=');
        sb.append(((this.labels == null)?"<null>":this.labels));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("lcId");
        sb.append('=');
        sb.append(((this.lcId == null)?"<null>":this.lcId));
        sb.append(',');
        sb.append("skus");
        sb.append('=');
        sb.append(((this.skus == null)?"<null>":this.skus));
        sb.append(',');
        sb.append("unLid");
        sb.append('=');
        sb.append(((this.unLid == null)?"<null>":this.unLid));
        sb.append(',');
        sb.append("fFreight");
        sb.append('=');
        sb.append(((this.fFreight == null)?"<null>":this.fFreight));
        sb.append(',');
        sb.append("endTime");
        sb.append('=');
        sb.append(((this.endTime == null)?"<null>":this.endTime));
        sb.append(',');
        sb.append("sellerFlag");
        sb.append('=');
        sb.append(((this.sellerFlag == null)?"<null>":this.sellerFlag));
        sb.append(',');
        sb.append("receiverEmail");
        sb.append('=');
        sb.append(((this.receiverEmail == null)?"<null>":this.receiverEmail));
        sb.append(',');
        sb.append("referrerId");
        sb.append('=');
        sb.append(((this.referrerId == null)?"<null>":this.referrerId));
        sb.append(',');
        sb.append("referrerName");
        sb.append('=');
        sb.append(((this.referrerName == null)?"<null>":this.referrerName));
        sb.append(',');
        sb.append("created");
        sb.append('=');
        sb.append(((this.created == null)?"<null>":this.created));
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
        result = ((result* 31)+((this.sendDate == null)? 0 :this.sendDate.hashCode()));
        result = ((result* 31)+((this.fFreight == null)? 0 :this.fFreight.hashCode()));
        result = ((result* 31)+((this.coId == null)? 0 :this.coId.hashCode()));
        result = ((result* 31)+((this.drpCoIdTo == null)? 0 :this.drpCoIdTo.hashCode()));
        result = ((result* 31)+((this.buyerId == null)? 0 :this.buyerId.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.receiverCity == null)? 0 :this.receiverCity.hashCode()));
        result = ((result* 31)+((this.unLid == null)? 0 :this.unLid.hashCode()));
        result = ((result* 31)+((this.isCod == null)? 0 :this.isCod.hashCode()));
        result = ((result* 31)+((this.receiverCountry == null)? 0 :this.receiverCountry.hashCode()));
        result = ((result* 31)+((this.payAmount == null)? 0 :this.payAmount.hashCode()));
        result = ((result* 31)+((this.invoiceType == null)? 0 :this.invoiceType.hashCode()));
        result = ((result* 31)+((this.modified == null)? 0 :this.modified.hashCode()));
        result = ((result* 31)+((this.shopId == null)? 0 :this.shopId.hashCode()));
        result = ((result* 31)+((this.invoiceTitle == null)? 0 :this.invoiceTitle.hashCode()));
        result = ((result* 31)+((this.receiverZip == null)? 0 :this.receiverZip.hashCode()));
        result = ((result* 31)+((this.questionDesc == null)? 0 :this.questionDesc.hashCode()));
        result = ((result* 31)+((this.soId == null)? 0 :this.soId.hashCode()));
        result = ((result* 31)+((this.signTime == null)? 0 :this.signTime.hashCode()));
        result = ((result* 31)+((this.buyerTaxNo == null)? 0 :this.buyerTaxNo.hashCode()));
        result = ((result* 31)+((this.referrerId == null)? 0 :this.referrerId.hashCode()));
        result = ((result* 31)+((this.lId == null)? 0 :this.lId.hashCode()));
        result = ((result* 31)+((this.created == null)? 0 :this.created.hashCode()));
        result = ((result* 31)+((this.planDeliveryDate == null)? 0 :this.planDeliveryDate.hashCode()));
        result = ((result* 31)+((this.labels == null)? 0 :this.labels.hashCode()));
        result = ((result* 31)+((this.receiverAddress == null)? 0 :this.receiverAddress.hashCode()));
        result = ((result* 31)+((this.node == null)? 0 :this.node.hashCode()));
        result = ((result* 31)+((this.outerPayId == null)? 0 :this.outerPayId.hashCode()));
        result = ((result* 31)+((this.shopSite == null)? 0 :this.shopSite.hashCode()));
        result = ((result* 31)+((this.receiverState == null)? 0 :this.receiverState.hashCode()));
        result = ((result* 31)+((this.orderFrom == null)? 0 :this.orderFrom.hashCode()));
        result = ((result* 31)+((this.orderDate == null)? 0 :this.orderDate.hashCode()));
        result = ((result* 31)+((this.items == null)? 0 :this.items.hashCode()));
        result = ((result* 31)+((this.paidAmount == null)? 0 :this.paidAmount.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        result = ((result* 31)+((this.lcId == null)? 0 :this.lcId.hashCode()));
        result = ((result* 31)+((this.drpCoIdFrom == null)? 0 :this.drpCoIdFrom.hashCode()));
        result = ((result* 31)+((this.skus == null)? 0 :this.skus.hashCode()));
        result = ((result* 31)+((this.shopBuyerId == null)? 0 :this.shopBuyerId.hashCode()));
        result = ((result* 31)+((this.freight == null)? 0 :this.freight.hashCode()));
        result = ((result* 31)+((this.creatorName == null)? 0 :this.creatorName.hashCode()));
        result = ((result* 31)+((this.shopName == null)? 0 :this.shopName.hashCode()));
        result = ((result* 31)+((this.remark == null)? 0 :this.remark.hashCode()));
        result = ((result* 31)+((this.shopStatus == null)? 0 :this.shopStatus.hashCode()));
        result = ((result* 31)+((this.sellerFlag == null)? 0 :this.sellerFlag.hashCode()));
        result = ((result* 31)+((this.receiverDistrict == null)? 0 :this.receiverDistrict.hashCode()));
        result = ((result* 31)+((this.logisticsCompany == null)? 0 :this.logisticsCompany.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.receiverTown == null)? 0 :this.receiverTown.hashCode()));
        result = ((result* 31)+((this.wmsCoId == null)? 0 :this.wmsCoId.hashCode()));
        result = ((result* 31)+((this.referrerName == null)? 0 :this.referrerName.hashCode()));
        result = ((result* 31)+((this.freeAmount == null)? 0 :this.freeAmount.hashCode()));
        result = ((result* 31)+((this.receiverName == null)? 0 :this.receiverName.hashCode()));
        result = ((result* 31)+((this.receiverMobile == null)? 0 :this.receiverMobile.hashCode()));
        result = ((result* 31)+((this.oId == null)? 0 :this.oId.hashCode()));
        result = ((result* 31)+((this.buyerMessage == null)? 0 :this.buyerMessage.hashCode()));
        result = ((result* 31)+((this.endTime == null)? 0 :this.endTime.hashCode()));
        result = ((result* 31)+((this.questionType == null)? 0 :this.questionType.hashCode()));
        result = ((result* 31)+((this.receiverEmail == null)? 0 :this.receiverEmail.hashCode()));
        result = ((result* 31)+((this.payDate == null)? 0 :this.payDate.hashCode()));
        result = ((result* 31)+((this.pays == null)? 0 :this.pays.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Order) == false) {
            return false;
        }
        Order rhs = ((Order) other);
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.sendDate == rhs.sendDate)||((this.sendDate!= null)&&this.sendDate.equals(rhs.sendDate)))&&((this.fFreight == rhs.fFreight)||((this.fFreight!= null)&&this.fFreight.equals(rhs.fFreight))))&&((this.coId == rhs.coId)||((this.coId!= null)&&this.coId.equals(rhs.coId))))&&((this.drpCoIdTo == rhs.drpCoIdTo)||((this.drpCoIdTo!= null)&&this.drpCoIdTo.equals(rhs.drpCoIdTo))))&&((this.buyerId == rhs.buyerId)||((this.buyerId!= null)&&this.buyerId.equals(rhs.buyerId))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.receiverCity == rhs.receiverCity)||((this.receiverCity!= null)&&this.receiverCity.equals(rhs.receiverCity))))&&((this.unLid == rhs.unLid)||((this.unLid!= null)&&this.unLid.equals(rhs.unLid))))&&((this.isCod == rhs.isCod)||((this.isCod!= null)&&this.isCod.equals(rhs.isCod))))&&((this.receiverCountry == rhs.receiverCountry)||((this.receiverCountry!= null)&&this.receiverCountry.equals(rhs.receiverCountry))))&&((this.payAmount == rhs.payAmount)||((this.payAmount!= null)&&this.payAmount.equals(rhs.payAmount))))&&((this.invoiceType == rhs.invoiceType)||((this.invoiceType!= null)&&this.invoiceType.equals(rhs.invoiceType))))&&((this.modified == rhs.modified)||((this.modified!= null)&&this.modified.equals(rhs.modified))))&&((this.shopId == rhs.shopId)||((this.shopId!= null)&&this.shopId.equals(rhs.shopId))))&&((this.invoiceTitle == rhs.invoiceTitle)||((this.invoiceTitle!= null)&&this.invoiceTitle.equals(rhs.invoiceTitle))))&&((this.receiverZip == rhs.receiverZip)||((this.receiverZip!= null)&&this.receiverZip.equals(rhs.receiverZip))))&&((this.questionDesc == rhs.questionDesc)||((this.questionDesc!= null)&&this.questionDesc.equals(rhs.questionDesc))))&&((this.soId == rhs.soId)||((this.soId!= null)&&this.soId.equals(rhs.soId))))&&((this.signTime == rhs.signTime)||((this.signTime!= null)&&this.signTime.equals(rhs.signTime))))&&((this.buyerTaxNo == rhs.buyerTaxNo)||((this.buyerTaxNo!= null)&&this.buyerTaxNo.equals(rhs.buyerTaxNo))))&&((this.referrerId == rhs.referrerId)||((this.referrerId!= null)&&this.referrerId.equals(rhs.referrerId))))&&((this.lId == rhs.lId)||((this.lId!= null)&&this.lId.equals(rhs.lId))))&&((this.created == rhs.created)||((this.created!= null)&&this.created.equals(rhs.created))))&&((this.planDeliveryDate == rhs.planDeliveryDate)||((this.planDeliveryDate!= null)&&this.planDeliveryDate.equals(rhs.planDeliveryDate))))&&((this.labels == rhs.labels)||((this.labels!= null)&&this.labels.equals(rhs.labels))))&&((this.receiverAddress == rhs.receiverAddress)||((this.receiverAddress!= null)&&this.receiverAddress.equals(rhs.receiverAddress))))&&((this.node == rhs.node)||((this.node!= null)&&this.node.equals(rhs.node))))&&((this.outerPayId == rhs.outerPayId)||((this.outerPayId!= null)&&this.outerPayId.equals(rhs.outerPayId))))&&((this.shopSite == rhs.shopSite)||((this.shopSite!= null)&&this.shopSite.equals(rhs.shopSite))))&&((this.receiverState == rhs.receiverState)||((this.receiverState!= null)&&this.receiverState.equals(rhs.receiverState))))&&((this.orderFrom == rhs.orderFrom)||((this.orderFrom!= null)&&this.orderFrom.equals(rhs.orderFrom))))&&((this.orderDate == rhs.orderDate)||((this.orderDate!= null)&&this.orderDate.equals(rhs.orderDate))))&&((this.items == rhs.items)||((this.items!= null)&&this.items.equals(rhs.items))))&&((this.paidAmount == rhs.paidAmount)||((this.paidAmount!= null)&&this.paidAmount.equals(rhs.paidAmount))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))))&&((this.lcId == rhs.lcId)||((this.lcId!= null)&&this.lcId.equals(rhs.lcId))))&&((this.drpCoIdFrom == rhs.drpCoIdFrom)||((this.drpCoIdFrom!= null)&&this.drpCoIdFrom.equals(rhs.drpCoIdFrom))))&&((this.skus == rhs.skus)||((this.skus!= null)&&this.skus.equals(rhs.skus))))&&((this.shopBuyerId == rhs.shopBuyerId)||((this.shopBuyerId!= null)&&this.shopBuyerId.equals(rhs.shopBuyerId))))&&((this.freight == rhs.freight)||((this.freight!= null)&&this.freight.equals(rhs.freight))))&&((this.creatorName == rhs.creatorName)||((this.creatorName!= null)&&this.creatorName.equals(rhs.creatorName))))&&((this.shopName == rhs.shopName)||((this.shopName!= null)&&this.shopName.equals(rhs.shopName))))&&((this.remark == rhs.remark)||((this.remark!= null)&&this.remark.equals(rhs.remark))))&&((this.shopStatus == rhs.shopStatus)||((this.shopStatus!= null)&&this.shopStatus.equals(rhs.shopStatus))))&&((this.sellerFlag == rhs.sellerFlag)||((this.sellerFlag!= null)&&this.sellerFlag.equals(rhs.sellerFlag))))&&((this.receiverDistrict == rhs.receiverDistrict)||((this.receiverDistrict!= null)&&this.receiverDistrict.equals(rhs.receiverDistrict))))&&((this.logisticsCompany == rhs.logisticsCompany)||((this.logisticsCompany!= null)&&this.logisticsCompany.equals(rhs.logisticsCompany))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.receiverTown == rhs.receiverTown)||((this.receiverTown!= null)&&this.receiverTown.equals(rhs.receiverTown))))&&((this.wmsCoId == rhs.wmsCoId)||((this.wmsCoId!= null)&&this.wmsCoId.equals(rhs.wmsCoId))))&&((this.referrerName == rhs.referrerName)||((this.referrerName!= null)&&this.referrerName.equals(rhs.referrerName))))&&((this.freeAmount == rhs.freeAmount)||((this.freeAmount!= null)&&this.freeAmount.equals(rhs.freeAmount))))&&((this.receiverName == rhs.receiverName)||((this.receiverName!= null)&&this.receiverName.equals(rhs.receiverName))))&&((this.receiverMobile == rhs.receiverMobile)||((this.receiverMobile!= null)&&this.receiverMobile.equals(rhs.receiverMobile))))&&((this.oId == rhs.oId)||((this.oId!= null)&&this.oId.equals(rhs.oId))))&&((this.buyerMessage == rhs.buyerMessage)||((this.buyerMessage!= null)&&this.buyerMessage.equals(rhs.buyerMessage))))&&((this.endTime == rhs.endTime)||((this.endTime!= null)&&this.endTime.equals(rhs.endTime))))&&((this.questionType == rhs.questionType)||((this.questionType!= null)&&this.questionType.equals(rhs.questionType))))&&((this.receiverEmail == rhs.receiverEmail)||((this.receiverEmail!= null)&&this.receiverEmail.equals(rhs.receiverEmail))))&&((this.payDate == rhs.payDate)||((this.payDate!= null)&&this.payDate.equals(rhs.payDate))))&&((this.pays == rhs.pays)||((this.pays!= null)&&this.pays.equals(rhs.pays))));
    }

}
