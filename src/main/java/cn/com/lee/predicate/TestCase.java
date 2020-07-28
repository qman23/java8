package cn.com.lee.predicate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestCase {

    private List<Apple> apples = new ArrayList<>();

    @BeforeEach
    public void init(){
//        apples.add(new Apple("GREEN",180));
        BiFunction<String,Integer,Apple> con = Apple::new;
        apples.add(con.apply("Green",180));
        apples.add(new Apple("GREEN",140));
        apples.add(new Apple("RED",150));
        apples.add(new Apple("BLUE",150));
    }
    @Test
    public void pickApple01(){
        List<Apple> result = new ArrayList<>();
        result = AppleFilter.filterApples(apples,AppleFilter::isGreenApple);
        result = AppleFilter.filterApples(apples,AppleFilter::isHeavyApple);
        result.stream().forEach(System.out::println);
    }


    @Test
    public void pickApple02(){
        apples.stream().filter(AppleFilter::isGreenApple).filter(a->a.getWeight()>100).forEach(System.out::println);
    }

    @Test
    public void sort(){
        System.out.println("before sort..");
        apples.stream().forEach(System.out::println);
        System.out.println("after sort asc..");
        apples.stream().sorted(Comparator.comparing(Apple::getWeight)).forEach(System.out::println);
        System.out.println("after sort desc..");
        apples.stream().sorted(Comparator.comparing(Apple::getWeight).reversed()).forEach(System.out::println);
        //比较器链
        System.out.println("after sort desc and then color..");
        apples.stream().sorted(Comparator.comparing(Apple::getWeight)
                .reversed().thenComparing(Apple::getColor))
                .forEach(System.out::println);
    }

    @Test
    /**
     * 谓词复合
     */
    public void predicateCombine() {
        //not red apple
        Predicate<Apple> redApple = a -> (a.getColor().equals("RED"));
        Predicate<Apple> notRedApple = redApple.negate();
        System.out.println("not red apple");
        apples.stream().filter(notRedApple).forEach(System.out::println);

        System.out.println("not red and weight more and 150 apple");
        Predicate<Apple> heavyGreenApple = notRedApple.and(a->a.getWeight()>150);
        apples.stream().filter(heavyGreenApple).forEach(System.out::println);
    }

    @Test
    public void functionCombine(){
        Function<Integer,Integer> f1 = x->x+2;
        Function<Integer,Integer> f2 = x->x*2;
        Function<Integer,Integer> f1AndF2 = f1.andThen(f2);
        System.out.println(f1AndF2.apply(1));

        Function<Integer,Integer> compose = f1.compose(f2);
        System.out.println(compose.apply(1));


        apples.stream().map((a)->
        {
            a.setWeight(a.getWeight()*2);
            a.setColor(a.getColor().toLowerCase());
            return a;
        })
                .forEach(System.out::println);
    }

    @Test
    public void iterateStream(){
        //get all apple's name
        apples.stream().map(Apple::getColor).forEach(System.out::println);

        //test word length
        List<String> list = Arrays.asList("Java8","hello world!","welcome to new world!");
        list.stream().map(String::length).forEach(System.out::println);

        //get all apple's name length

        apples.stream().map(Apple::getColor).map(String::length).forEach(System.out::println);
    }

    @Test
    public void mapTest(){
        String[] str = {"Hello","World"};
        Arrays.stream(str).map(w->w.split("")).
                map(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
        Arrays.stream(str).map(w->w.split("")).
                flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

    }
    public static void main(String[] args) {
        int a = 1;
        Runnable r = ()->System.out.println(a);

    }
}
