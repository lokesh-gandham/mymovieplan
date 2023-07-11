package com.nmscinemas.entities;

import javax.persistence.CascadeType;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pid;
	
	@NotBlank(message = "name cannot be blank")
	private String name;
	
	
	@NotBlank(message = "category cannot be blank")
	private String category;
	

	@NotBlank(message="theatername cannot be blank")
	private String theatername;
	
	@NotBlank(message="showtimings cannot be blank")
	private String showtimings;
	
	@NotBlank(message = "description cannot be blank")
	private String description;
	
	
	@NotNull(message = "available cannot be null")
	private int totalAvailable;
	
	@NotNull(message = "price cannot be null")
	private Double price;
	
	@NotNull(message = "isAvailable cannot be null")
	private boolean isAvailable;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private ProductImage productImage;
	
	public Product() {
		super();
	}
	public Product(Long pid, String name, String category,String theatername,String showtimmings,String description, int totalAvailable, Double price,
			boolean isAvailable, ProductImage productImage) {
		super();
		this.pid = pid;
		this.name = name;
		this.category = category;
		this.theatername=theatername;
		this.showtimings=showtimmings;
		this.description = description;
		this.totalAvailable = totalAvailable;
		this.price = price;
		this.isAvailable = isAvailable;
		this.productImage = productImage;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getTotalAvailable() {
		return totalAvailable;
	}
	public void setTotalAvailable(int totalAvailable) {
		this.totalAvailable = totalAvailable;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public ProductImage getProductImage() {
		return productImage;
	}
	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTheatername() {
		return theatername;
	}
	public void setTheatername(String theatername) {
		this.theatername = theatername;
	}
	public String getShowtimings() {
		return showtimings;
	}
	public void setShowtimings(String showtimings) {
		this.showtimings = showtimings;
	}
	
	
}
