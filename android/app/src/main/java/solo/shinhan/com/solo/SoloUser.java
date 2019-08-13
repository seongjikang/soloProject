package solo.shinhan.com.solo;

public class SoloUser {
    private int soloUserNo;
    private String Name;
    private int age;
    private String gender;

    public SoloUser() {
    }

    public SoloUser(int soloUserNo, String name, int age, String gender) {
        this.soloUserNo = soloUserNo;
        Name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getSoloUserNo() {
        return soloUserNo;
    }

    public void setSoloUserNo(int soloUserNo) {
        this.soloUserNo = soloUserNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
