package com.modeln.fxr.model;

public class Product {
	private long id;
	private String name;
	private String description;
	private String type;

	public Product(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Product [id=%s, name=%s, description=%s, type=%s]", id, name, description, type);
	}

}
