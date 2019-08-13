package solo.shinhan.com.solo;

public class MyCollocateFurnitureInfo {
    private int houseNo;
    private FurnitureInfo furnitureInfo;

    public MyCollocateFurnitureInfo() {
    }

    public MyCollocateFurnitureInfo(int houseNo, FurnitureInfo furnitureInfo) {
        this.houseNo = houseNo;
        this.furnitureInfo = furnitureInfo;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public FurnitureInfo getFurnitureInfo() {
        return furnitureInfo;
    }

    public void setFurnitureInfo(FurnitureInfo furnitureInfo) {
        this.furnitureInfo = furnitureInfo;
    }
}
