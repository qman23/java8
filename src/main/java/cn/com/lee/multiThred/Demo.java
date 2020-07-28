package cn.com.lee.multiThred;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    public static void main(String[] args) {
        Callable<Integer> call = ()->{
            System.out.println("This is the thead:"+Thread.currentThread());
            return 12;
        };
        ExecutorService ex = Executors.newFixedThreadPool(5);
        ex.submit(call);
        ex.submit(call);
        ex.shutdown();


    }
}
