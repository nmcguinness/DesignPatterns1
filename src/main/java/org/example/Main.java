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
         List<String> dataList = new ArrayList<>(List.of("Ana","Ben","Ciara","Dave","Erica"));
         //write the code to output the average string length
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

    }
}
