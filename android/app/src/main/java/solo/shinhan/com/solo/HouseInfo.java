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

    public HouseInfo() {
    }

    public HouseInfo(int houseNo, String houseName, String address, double houseSize, String addressDetail, Bitmap houseView, Bitmap houseFloorPlan) {
        this.houseNo = houseNo;
        this.houseName = houseName;
        this.address = address;
        this.houseSize = houseSize;
        this.addressDetail = addressDetail;
        this.houseView = houseView;
        this.houseFloorPlan = houseFloorPlan;
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
}
