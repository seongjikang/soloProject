package solo.shinhan.com.solo;

import java.util.ArrayList;
import java.util.List;

public class SoloSingleton {

    /*
     * 필요한 데이터를 여기다 정의하고 사용하면된다.
     *
     */
    private List<HouseInfo> houseInfoList;

    private SoloSingleton() {
        houseInfoList = new ArrayList<HouseInfo>();
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
}
