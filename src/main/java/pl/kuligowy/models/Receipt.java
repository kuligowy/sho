package pl.kuligowy.models;

import java.math.BigDecimal;

public class Receipt {

	private String shopName;
	private BigDecimal value;
 
	
	public Receipt(String shopName, BigDecimal value) {
		super();
		this.shopName = shopName;
		this.value = value;
	}
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
