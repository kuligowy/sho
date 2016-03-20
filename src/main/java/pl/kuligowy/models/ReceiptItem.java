package pl.kuligowy.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReceiptItem {

	@Id
	private int id;
	private Item name;
	private BigDecimal value;
	private int quantity;
}
