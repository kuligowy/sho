package pl.kuligowy.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Receipt {

	@Id
	private int id;
	private Shop shop;
	private BigDecimal value;
	private Date eventDate;
	private List<ReceiptItem> receiptItems;
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public List<ReceiptItem> getReceiptItems() {
		return receiptItems;
	}
	public void setReceiptItems(List<ReceiptItem> receiptItems) {
		this.receiptItems = receiptItems;
	}
 
 
	
	
}
