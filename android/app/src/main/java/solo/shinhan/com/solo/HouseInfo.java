package solo.shinhan.com.solo;

import android.graphics.Bitmap;

class HouseInfo {
    private int houseNo;
    private String houseName;
    private String address;
    private double houseSize;
    private String addressDetail;
    private Bitmap houseView;
    private Bitmap houseFloorPlan;
    private int bedroom;
    private int restroom;
    private int balcony;
    private int kitchen;
    private int livingroom;
    private int hall;

    public HouseInfo() {
    }

    public HouseInfo(int houseNo, String houseName, String address, double houseSize, String addressDetail, Bitmap houseView, Bitmap houseFloorPlan, int bedroom, int restroom, int balcony, int kitchen, int livingroom, int hall) {
        this.houseNo = houseNo;
        this.houseName = houseName;
        this.address = address;
        this.houseSize = houseSize;
        this.addressDetail = addressDetail;
        this.houseView = houseView;
        this.houseFloorPlan = houseFloorPlan;
        this.bedroom = bedroom;
        this.restroom = restroom;
        this.balcony = balcony;
        this.kitchen = kitchen;
        this.livingroom = livingroom;
        this.hall = hall;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(double houseSize) {
        this.houseSize = houseSize;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Bitmap getHouseView() {
        return houseView;
    }

    public void setHouseView(Bitmap houseView) {
        this.houseView = houseView;
    }

    public Bitmap getHouseFloorPlan() {
        return houseFloorPlan;
    }

    public void setHouseFloorPlan(Bitmap houseFloorPlan) {
        this.houseFloorPlan = houseFloorPlan;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getRestroom() {
        return restroom;
    }

    public void setRestroom(int restroom) {
        this.restroom = restroom;
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

    public int getLivingroom() {
        return livingroom;
    }

    public void setLivingroom(int livingroom) {
        this.livingroom = livingroom;
    }

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }
}
