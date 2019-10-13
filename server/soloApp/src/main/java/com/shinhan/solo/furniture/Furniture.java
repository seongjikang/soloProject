package com.shinhan.solo.furniture;

public class Furniture {
	private int furnitureNo;
	private String category;
	private String model;
	private int price;
	private String furnitureName;
	private String furnitureUrl;
	private int direction;
	private String brand;
	
	public Furniture(int furnitureNo, String category, String model, int price, String furnitureName,
			String furnitureUrl, int direction, String brand) {
		super();
		this.furnitureNo = furnitureNo;
		this.category = category;
		this.model = model;
		this.price = price;
		this.furnitureName = furnitureName;
		this.furnitureUrl = furnitureUrl;
		this.direction = direction;
		this.brand = brand;
	}

	public Furniture() {
		super();
	}

	public int getFurnitureNo() {
		return furnitureNo;
	}

	public void setFurnitureNo(int furnitureNo) {
		this.furnitureNo = furnitureNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFurnitureName() {
		return furnitureName;
	}

	public void setFurnitureName(String furnitureName) {
		this.furnitureName = furnitureName;
	}

	public String getFurnitureUrl() {
		return furnitureUrl;
	}

	public void setFurnitureUrl(String furnitureUrl) {
		this.furnitureUrl = furnitureUrl;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
