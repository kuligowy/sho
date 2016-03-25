package pl.kuligowy.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="receipt_item")
public class ReceiptItem {

	@Id
	private int id;
	@OneToOne()
	@JoinColumn(name="item_id")
	private Item itemId;
	@Column(name="price")
	private BigDecimal price;
	@Column(name="quantity")
	private int quantity;
	public int getId() {
		return id;
	}
	
	
	public ReceiptItem() {
 
	}


	public void setId(int id) {
		this.id = id;
	}
	public Item getItemId() {
		return itemId;
	}
	public void setItemId(Item itemId) {
		this.itemId = itemId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
