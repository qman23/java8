package cn.com.lee.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Demo {
    private List<Transaction> transactions;
    @BeforeEach
    public void init(){
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brain = new Trader("Brain","Cambridge");

         this.transactions = Arrays.asList(
                new Transaction(brain,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );
    }

    /**
     * 找出2011年发生的所有交易，并按交易额排序(由低到高)
     */
    @Test
    public void transaction2011(){
        this.transactions.stream()
                .filter(t->t.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
    }

    /**
     * 交易员都在那些不同的城市工作过
     */
    @Test
    public void diffCity() {
        this.transactions.stream()
                .map(t->t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    /**
     * 查找所有来自剑桥的交易员，并按姓名排序
     */
    public void cambridgeTrador(){
        this.transactions.stream()
                .map(t->t.getTrader())
                .distinct()
                .filter(t->t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
    }

    @Test
    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序
     */
    public void traderName(){
        //通过拼接字符串的方式，效率不高
        String str = this.transactions.stream()
                .map(t->t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(a,b)->a+" "+b);
        System.out.println(str);
        //通过stringbuilder的方式，高效
        String str1 = this.transactions.stream()
                .map(t->t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
         System.out.println(str1);
    }

    /**
     * 由美亚交易员是在米兰工作的
     */
    @Test
    public void isTraderFromMilan(){
        this.transactions.stream()
                .map(t->t.getTrader())
                .distinct()
                .filter(t->t.getCity().equals("Milan"))
                .forEach(System.out::println);
        boolean isMatch = this.transactions.stream()
                .anyMatch(t->t.getTrader().getCity().equals("Milan"));
        System.out.println(isMatch);
    }

    @Test
    /**
     * 打印在剑桥生活的交易员所有交易额
     */
    public void cambridgeTraderTotal(){
        int total = this.transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Cambridge"))
                .map(t->t.getValue())
                .reduce(0,(a,b)->a+b);
        System.out.println(total);
    }

    /**
     * 所有交易中，最高的交易额是多少
     */
    @Test
    public void maxTransactionValue(){
        Optional<Integer> optional = this.transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        if(optional.isPresent()){
            System.out.println(optional.get());
        }

        Optional<Transaction> o1 = this.transactions.stream()
                .max(Comparator.comparing(Transaction::getValue));
        if(o1.isPresent()){
            System.out.println(o1.get().getValue());
        }
    }

    /**
     * 找到交易额最小的交易
     */
    @Test
    public void minTransactionValue(){

        Optional<Transaction> o1 = this.transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        if(o1.isPresent()){
            System.out.println(o1);
        }
    }
}
