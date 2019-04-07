package cc.mrbird.system.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_good")
public class Good implements Serializable {

	private static final long serialVersionUID = -4852732617766210959L;


	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "GOODS_ID")
	private Long goodsId;

	@Column(name = "GOODS_NAME")
	@ExportConfig(value = "通用名")
	private String goodsName;

	@Column(name = "MEDICINEMODEL")
	@ExportConfig(value = "剂型")
	private String medicinemodel;

	@Column(name = "OUTLOOK")
	@ExportConfig(value = "规格")
	private String outlook;

	@Column(name = "FACTOR")
	@ExportConfig(value = "转换系数")
	private String factor;

	@Column(name = "UNIT")
	@ExportConfig(value = "制剂单位")
	private String unit;

	@Column(name = "MATERIAL_NAME")
	@ExportConfig(value = "材质")
	private String materialName;

	@Column(name = "COMPANY_NAME_SC")
	@ExportConfig(value = "生产企业名称")
	private String companyNameSc;

	@Column(name = "PRICE")
	@ExportConfig(value = "价格")
	private BigDecimal price;


	public Long getGoodsId () {
		return goodsId;
	}

	public void setGoodsId (Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName () {
		return goodsName;
	}

	public void setGoodsName (String goodsName) {
		this.goodsName = goodsName;
	}

	public String getMedicinemodel () {
		return medicinemodel;
	}

	public void setMedicinemodel (String medicinemodel) {
		this.medicinemodel = medicinemodel;
	}

	public String getOutlook () {
		return outlook;
	}

	public void setOutlook (String outlook) {
		this.outlook = outlook;
	}

	public String getFactor () {
		return factor;
	}

	public void setFactor (String factor) {
		this.factor = factor;
	}

	public String getUnit () {
		return unit;
	}

	public void setUnit (String unit) {
		this.unit = unit;
	}

	public String getMaterialName () {
		return materialName;
	}

	public void setMaterialName (String materialName) {
		this.materialName = materialName;
	}

	public String getCompanyNameSc () {
		return companyNameSc;
	}

	public void setCompanyNameSc (String companyNameSc) {
		this.companyNameSc = companyNameSc;
	}

	public BigDecimal getPrice () {
		return price;
	}

	public void setPrice (BigDecimal price) {
		this.price = price;
	}
}