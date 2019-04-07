package cc.mrbird.system.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_purchase_order")
public class PurchaseOrder implements Serializable {

	private static final long serialVersionUID = -4855899765810959L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "order_id")
	private String orderId;

	private Long goodsId;

	@Column(name = "hospital_name")
	@ExportConfig(value = "买方")
	private String hospitalName;

	@Column(name = "company_name_sc")
	@ExportConfig(value = "生产企业")
	private String companyNameSc;

	@Column(name = "order_name")
	@ExportConfig(value = "订单名称")
	private String orderName;

	@Column(name = "order_amount")
	@ExportConfig(value = "订单金额")
	private BigDecimal orderAmount;

	@Column(name = "order_remarks")
	@ExportConfig(value = "订单标识")
	private String orderRemarks;

	@Column(name = "submit_time")
	@ExportConfig(value = "提交时间")
	private Date submitTime;

	@Column(name = "subminter")
	@ExportConfig(value = "提交人")
	private String subminter;

	@Column(name = "order_state")
	@ExportConfig(value = "完成进度")
	private Integer orderState;

	@Column(name = "order_status")
	@ExportConfig(value = "订单状态")
	private Integer orderStatus;

	@Column(name = "add_user_name")
	@ExportConfig(value = "订单添加人")
	private String addUserName;

	@Column(name = "add_time")
	@ExportConfig(value = "订单添加时间")
	private Date addTime;

	public String getOrderId () {
		return orderId;
	}

	public void setOrderId (String orderId) {
		this.orderId = orderId;
	}

	public String getHospitalName () {
		return hospitalName;
	}

	public void setHospitalName (String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getCompanyNameSc () {
		return companyNameSc;
	}

	public void setCompanyNameSc (String companyNameSc) {
		this.companyNameSc = companyNameSc;
	}

	public String getOrderName () {
		return orderName;
	}

	public Long getGoodsId () {
		return goodsId;
	}

	public void setGoodsId (Long goodsId) {
		this.goodsId = goodsId;
	}

	public void setOrderName (String orderName) {
		this.orderName = orderName;
	}

	public BigDecimal getOrderAmount () {
		return orderAmount;
	}

	public void setOrderAmount (BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderRemarks () {
		return orderRemarks;
	}

	public void setOrderRemarks (String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}

	public Date getSubmitTime () {
		return submitTime;
	}

	public void setSubmitTime (Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getSubminter () {
		return subminter;
	}

	public void setSubminter (String subminter) {
		this.subminter = subminter;
	}

	public Integer getOrderState () {
		return orderState;
	}

	public void setOrderState (Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getOrderStatus () {
		return orderStatus;
	}

	public void setOrderStatus (Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAddUserName () {
		return addUserName;
	}

	public void setAddUserName (String addUserName) {
		this.addUserName = addUserName;
	}

	public Date getAddTime () {
		return addTime;
	}

	public void setAddTime (Date addTime) {
		this.addTime = addTime;
	}
}