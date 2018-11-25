package automation.web.base.utils;

import java.util.Random;

public class RandomUtil {


    private Random rand ;


    public RandomUtil () {
        rand = new Random() ;
    }

    public String randomGenerator (int range , int s, int size){
        char [] cache = new char[size] ;
        int i = 0 ;
        while (i < size) {
            cache[i] = (char)(s + (rand.nextInt(range)));
            i++;
        }
        return new String(cache);
    }

    public String randomDigitString (int size) {
        return randomGenerator(10,'0', size);
    }

    public String randomLowercaseString (int size) {
        return randomGenerator(26,'a', size);
    }

    public String randomUppercaseString (int size) {
        return randomGenerator(26,'A', size);
    }



    public static void main (String [] args){
        RandomUtil randomUtil = new RandomUtil ();
        String text = randomUtil.randomDigitString(1000000);
        System.out.println(text);
    }


}
