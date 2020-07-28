package cn.com.lee.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * apple filter
 */
public class AppleFilter {
    public static boolean isGreenApple(Apple apple){
        return apple.getColor().equals("GREEN");

    }

    public static boolean isHeavyApple(Apple apple){
        return apple.getWeight()>150;
    }


    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:apples){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
