package solo.shinhan.com.solo;

public class SoloSingleton {

    /*
     * 필요한 데이터를 여기다 정의하고 사용하면된다.
     *
     */

    private SoloSingleton() {}

    private static class SoloSingletonHolder {
        public static final SoloSingleton INSTANCE = new SoloSingleton();
    }

    public  static SoloSingleton getInstance() {
        return SoloSingletonHolder.INSTANCE;
    }

}
