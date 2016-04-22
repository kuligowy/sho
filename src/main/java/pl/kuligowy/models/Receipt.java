package pl.kuligowy.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "receipt")
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne()
	@JoinColumn(name = "shop_id")
	private Shop shopId;
	@Column(name = "total")
	private BigDecimal total;
	@Column(name = "event_time")
	private Date eventTime;
	@OneToMany
	@JoinColumn(name = "receipt_id", referencedColumnName = "id")
	private List<ReceiptItem> receiptItems;

	public Receipt() {
		// TODO Auto-generated constructor stub
	}

	public Receipt(int id, Shop shopId, BigDecimal total, Date eventTime, List<ReceiptItem> receiptItems) {
		super();
		this.id = id;
		this.shopId = shopId;
		this.total = total;
		this.eventTime = eventTime;
		this.receiptItems = receiptItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Shop getShopId() {
		return shopId;
	}

	public void setShopId(Shop shopId) {
		this.shopId = shopId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public List<ReceiptItem> getReceiptItems() {
		return receiptItems;
	}

	public void setReceiptItems(List<ReceiptItem> receiptItems) {
		this.receiptItems = receiptItems;
	}

}
