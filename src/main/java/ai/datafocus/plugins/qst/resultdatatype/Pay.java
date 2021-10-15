
package ai.datafocus.plugins.qst.resultdatatype;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pay_id",
    "outer_pay_id",
    "pay_date",
    "amount",
    "payment",
    "buyer_account",
    "status",
    "is_order_pay"
})
@Generated("jsonschema2pojo")
public class Pay {

    @JsonProperty("pay_id")
    private String payId;
    @JsonProperty("outer_pay_id")
    private String outerPayId;
    @JsonProperty("pay_date")
    private String payDate;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("payment")
    private String payment;
    @JsonProperty("buyer_account")
    private String buyerAccount;
    @JsonProperty("status")
    private String status;
    @JsonProperty("is_order_pay")
    private Boolean isOrderPay;

    @JsonProperty("pay_id")
    public String getPayId() {
        return payId;
    }

    @JsonProperty("pay_id")
    public void setPayId(String payId) {
        this.payId = payId;
    }

    @JsonProperty("outer_pay_id")
    public String getOuterPayId() {
        return outerPayId;
    }

    @JsonProperty("outer_pay_id")
    public void setOuterPayId(String outerPayId) {
        this.outerPayId = outerPayId;
    }

    @JsonProperty("pay_date")
    public String getPayDate() {
        return payDate;
    }

    @JsonProperty("pay_date")
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty("payment")
    public String getPayment() {
        return payment;
    }

    @JsonProperty("payment")
    public void setPayment(String payment) {
        this.payment = payment;
    }

    @JsonProperty("buyer_account")
    public String getBuyerAccount() {
        return buyerAccount;
    }

    @JsonProperty("buyer_account")
    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("is_order_pay")
    public Boolean getIsOrderPay() {
        return isOrderPay;
    }

    @JsonProperty("is_order_pay")
    public void setIsOrderPay(Boolean isOrderPay) {
        this.isOrderPay = isOrderPay;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Pay.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("payId");
        sb.append('=');
        sb.append(((this.payId == null)?"<null>":this.payId));
        sb.append(',');
        sb.append("outerPayId");
        sb.append('=');
        sb.append(((this.outerPayId == null)?"<null>":this.outerPayId));
        sb.append(',');
        sb.append("payDate");
        sb.append('=');
        sb.append(((this.payDate == null)?"<null>":this.payDate));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("payment");
        sb.append('=');
        sb.append(((this.payment == null)?"<null>":this.payment));
        sb.append(',');
        sb.append("buyerAccount");
        sb.append('=');
        sb.append(((this.buyerAccount == null)?"<null>":this.buyerAccount));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("isOrderPay");
        sb.append('=');
        sb.append(((this.isOrderPay == null)?"<null>":this.isOrderPay));
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
        result = ((result* 31)+((this.outerPayId == null)? 0 :this.outerPayId.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.buyerAccount == null)? 0 :this.buyerAccount.hashCode()));
        result = ((result* 31)+((this.payment == null)? 0 :this.payment.hashCode()));
        result = ((result* 31)+((this.payId == null)? 0 :this.payId.hashCode()));
        result = ((result* 31)+((this.isOrderPay == null)? 0 :this.isOrderPay.hashCode()));
        result = ((result* 31)+((this.payDate == null)? 0 :this.payDate.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Pay) == false) {
            return false;
        }
        Pay rhs = ((Pay) other);
        return (((((((((this.outerPayId == rhs.outerPayId)||((this.outerPayId!= null)&&this.outerPayId.equals(rhs.outerPayId)))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.buyerAccount == rhs.buyerAccount)||((this.buyerAccount!= null)&&this.buyerAccount.equals(rhs.buyerAccount))))&&((this.payment == rhs.payment)||((this.payment!= null)&&this.payment.equals(rhs.payment))))&&((this.payId == rhs.payId)||((this.payId!= null)&&this.payId.equals(rhs.payId))))&&((this.isOrderPay == rhs.isOrderPay)||((this.isOrderPay!= null)&&this.isOrderPay.equals(rhs.isOrderPay))))&&((this.payDate == rhs.payDate)||((this.payDate!= null)&&this.payDate.equals(rhs.payDate))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
