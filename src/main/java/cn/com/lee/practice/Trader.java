package cn.com.lee.practice;

public class Trader {
    private final String name;

    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
