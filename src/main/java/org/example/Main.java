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
    }
}
