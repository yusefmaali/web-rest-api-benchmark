package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {
	
	@Id
	private int id;
	
	@Column(name ="name", nullable = false)
    private String name;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
