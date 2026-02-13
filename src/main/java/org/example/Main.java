package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        * 1. Functional Interface
        * 2. PECS
        * 3. DP1 - Strategy/Command
        * */
        //create instance to call instance methods and access instance variables
        Main theApp = new Main();
        theApp.run();
    }

    public void run(){

        //make first list with NO variety in string length
         List<String> dataList1 = new ArrayList<>(List.of("Ana","Ben","Cia","Dav","Era"));
         Accumulator myStringAccumulator1 = new Accumulator();
         getStatistics(dataList1, myStringAccumulator1);
         System.out.println(myStringAccumulator1);

         //make second to demonstrate difference in average and std dev
        List<String> dataList2 = new ArrayList<>(List.of("ad","fghgfhgfh","sdfsd","asddfgdfgfdgfdggsd","sdffsd"));
        Accumulator myStringAccumulator2 = new Accumulator();
        getStatistics(dataList2, myStringAccumulator2);
        System.out.println(myStringAccumulator2);
    }

//    public double getAverage(List<String> data)  {
//        return -1;
//    }

    public double getAverage(List<String> data)  {

        if(data == null || data.size() == 0)
            throw new IllegalArgumentException("data is null or zero length!");

        double lengthSum = 0;
        for(String s : data){
            lengthSum += s.length();
        }
        return lengthSum/data.size();
    }

    public void getStatistics(List<String> data, Accumulator accumulator){
        for(String s : data)
            accumulator.update(s);
    }
}
