package com.shinhan.solo.house;

public class House {
	private int houseNo;
	private String houseName;
	private float houseSize;
	private String address;
	private String addressDetail;
	private String houseViewUrl;
	private String houseFloorPlanUrl;
	private int bedRoom;
	private int restRoom;
	private int balcony;
	private int kitchen;
	private int livingRoom;
	private int hall;
	public House(int houseNo, String houseName, float houseSize, String address, String addressDetail,
			String houseViewUrl, String houseFloorPlanUrl, int bedRoom, int restRoom, int balcony, int kitchen,
			int livingRoom, int hall) {
		super();
		this.houseNo = houseNo;
		this.houseName = houseName;
		this.houseSize = houseSize;
		this.address = address;
		this.addressDetail = addressDetail;
		this.houseViewUrl = houseViewUrl;
		this.houseFloorPlanUrl = houseFloorPlanUrl;
		this.bedRoom = bedRoom;
		this.restRoom = restRoom;
		this.balcony = balcony;
		this.kitchen = kitchen;
		this.livingRoom = livingRoom;
		this.hall = hall;
	}
	public House() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public float getHouseSize() {
		return houseSize;
	}
	public void setHouseSize(float houseSize) {
		this.houseSize = houseSize;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getHouseViewUrl() {
		return houseViewUrl;
	}
	public void setHouseViewUrl(String houseViewUrl) {
		this.houseViewUrl = houseViewUrl;
	}
	public String getHouseFloorPlanUrl() {
		return houseFloorPlanUrl;
	}
	public void setHouseFloorPlanUrl(String houseFloorPlanUrl) {
		this.houseFloorPlanUrl = houseFloorPlanUrl;
	}
	public int getBedRoom() {
		return bedRoom;
	}
	public void setBedRoom(int bedRoom) {
		this.bedRoom = bedRoom;
	}
	public int getRestRoom() {
		return restRoom;
	}
	public void setRestRoom(int restRoom) {
		this.restRoom = restRoom;
	}
	public int getBalcony() {
		return balcony;
	}
	public void setBalcony(int balcony) {
		this.balcony = balcony;
	}
	public int getKitchen() {
		return kitchen;
	}
	public void setKitchen(int kitchen) {
		this.kitchen = kitchen;
	}
	public int getLivingRoom() {
		return livingRoom;
	}
	public void setLivingRoom(int livingRoom) {
		this.livingRoom = livingRoom;
	}
	public int getHall() {
		return hall;
	}
	public void setHall(int hall) {
		this.hall = hall;
	}
}
