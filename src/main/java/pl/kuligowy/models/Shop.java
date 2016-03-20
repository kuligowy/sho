package pl.kuligowy.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shop {

	@Id
	private int id;
	private String name;
	
}
