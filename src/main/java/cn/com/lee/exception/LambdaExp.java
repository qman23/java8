package cn.com.lee.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaExp {

    public static void main(String[] args) {
        Integer[] temp = {1,2,3};
        List<Integer> list = Arrays.asList(temp);
        try{

            list.stream().forEach((i)->System.out.println(i/0));
        }catch (Exception e){
            System.out.println("exception happened!"+e.toString());
        }
    }
}
