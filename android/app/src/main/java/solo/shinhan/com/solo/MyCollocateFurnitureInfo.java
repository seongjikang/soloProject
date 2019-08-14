package solo.shinhan.com.solo;

public class MyCollocateFurnitureInfo {
    private int houseNo;
    private FurnitureInfo furnitureInfo;
    private float x;
    private float y;

    private boolean selectFurniture;

    public MyCollocateFurnitureInfo() {
    }

    public MyCollocateFurnitureInfo(int houseNo, FurnitureInfo furnitureInfo, float x, float y, boolean selectFurniture) {
        this.houseNo = houseNo;
        this.furnitureInfo = furnitureInfo;
        this.x = x;
        this.y = y;
        this.selectFurniture = selectFurniture;
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

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isSelectFurniture() {
        return selectFurniture;
    }

    public void setSelectFurniture(boolean selectFurniture) {
        this.selectFurniture = selectFurniture;
    }
}
