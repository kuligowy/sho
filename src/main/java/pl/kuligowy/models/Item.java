package pl.kuligowy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item {
	@Id
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="sort")
	private int sort;
	
	public Item(int id, String name, int sort) {
		super();
		this.id = id;
		this.name = name;
		this.sort = sort;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

 
}
