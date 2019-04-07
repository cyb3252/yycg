package cc.mrbird.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cyb
 * @date 2019/4/6 - 16:18
 */
public class OrderDetail extends Good implements Serializable {

    private String orderId;
    private BigDecimal purchasePrice;
    private Integer purchaseCount;
    private String hasOrder;
    private String orderName;
    private String oldOrderName;
    private String newOrderName;
    private String orderRemarks;

    public String getHasOrder () {
        return hasOrder;
    }

    public String getOrderRemarks () {
        return orderRemarks;
    }

    public void setOrderRemarks (String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }

    public String getOrderName () {
        return orderName;
    }

    public void setOrderName (String orderName) {
        this.orderName = orderName;
    }

    public void setHasOrder (String hasOrder) {
        this.hasOrder = hasOrder;
    }

    public String getOldOrderName () {
        return oldOrderName;
    }

    public void setOldOrderName (String oldOrderName) {
        this.oldOrderName = oldOrderName;
    }

    public String getNewOrderName () {
        return newOrderName;
    }

    public void setNewOrderName (String newOrderName) {
        this.newOrderName = newOrderName;
    }

    public String getOrderId () {
        return orderId;
    }

    public void setOrderId (String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPurchasePrice () {
        return purchasePrice;
    }

    public void setPurchasePrice (BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getPurchaseCount () {
        return purchaseCount;
    }

    public void setPurchaseCount (Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }
}
