package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private int id;

	@Column(name ="email", nullable = false)
	private String email;

	@JsonProperty("first_name")
	@Column(name ="first_name", nullable = false)
    private String firstName;

	@JsonProperty("last_name")
	@Column(name ="last_name", nullable = false)
    private String lastName;

	@ManyToMany(fetch = FetchType.EAGER)
	@OrderBy("id ASC")
	@JoinTable(name = "user_country_mapping",
         joinColumns = @JoinColumn(name = "user_id"),
         inverseJoinColumns = @JoinColumn(name = "country_id"))
	Set<Country> countries = new HashSet<>(0);
    
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Set<Country> getCountries() {
		return countries;
	}
}
