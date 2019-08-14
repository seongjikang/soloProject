package solo.shinhan.com.solo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoloSingleton {

    /*
     * 필요한 데이터를 여기다 정의하고 사용하면된다.
     *
     */
    private List<HouseInfo> houseInfoList;
    private Map<String,ArrayList<FurnitureInfo>> furnitureMap ;
    private SoloUser soloUser;
    private List<MyCollocateFurnitureInfo> myCollocateFurnitureInfoList;

    private SoloSingleton() {
        myCollocateFurnitureInfoList = new ArrayList<MyCollocateFurnitureInfo>();
        houseInfoList = new ArrayList<HouseInfo>();
        furnitureMap = new HashMap<String, ArrayList<FurnitureInfo>>();
    }

    private static class SoloSingletonHolder {
        public static final SoloSingleton INSTANCE = new SoloSingleton();
    }

    public  static SoloSingleton getInstance() {
        return SoloSingletonHolder.INSTANCE;
    }

    public List<HouseInfo> getHouseInfoList() {
        return houseInfoList;
    }

    public void setHouseInfoList(List<HouseInfo> houseInfoList) {
        this.houseInfoList = houseInfoList;
    }

    public Map<String, ArrayList<FurnitureInfo>> getFurnitureMap() {
        return furnitureMap;
    }

    public void setFurnitureMap(Map<String, ArrayList<FurnitureInfo>> furnitureMap) {
        this.furnitureMap = furnitureMap;
    }

    public SoloUser getSoloUser() {
        return soloUser;
    }

    public void setSoloUser(SoloUser soloUser) {
        this.soloUser = soloUser;
    }

    public List<MyCollocateFurnitureInfo> getMyCollocateFurnitureInfoList() {
        return myCollocateFurnitureInfoList;
    }

    public void setMyCollocateFurnitureInfoList(List<MyCollocateFurnitureInfo> myCollocateFurnitureInfoList) {
        this.myCollocateFurnitureInfoList = myCollocateFurnitureInfoList;
    }
}
