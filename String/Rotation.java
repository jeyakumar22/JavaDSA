package String;

import java.util.*;

class D {
    public boolean isRotation(String s1, String s2) {
        String doubled = s1+s1;
        for(int i=0;i<s1.length();i++){
            int j=0;
            while(j<s2.length() && doubled.charAt(i+j) == s2.charAt(j)){
                j++;
            }
            if(s2.length() == j) return true;
        }

        return false;
    }
}

public class Rotation {
    public static void main(String[] args) {

        D obj = new D();  // FIXED

        // ----- Testcase 1 -----
        String s1 = "abcd";
        String s2 = "cdab";
        System.out.println("Testcase 1: " + obj.isRotation(s1, s2));

        // ----- Testcase 2 -----
        s1 = "aaaa";
        s2 = "aaaa";
        System.out.println("Testcase 2: " + obj.isRotation(s1, s2));

        // ----- Testcase 3 -----
        s1 = "abcde";
        s2 = "abced"; // not rotation
        System.out.println("Testcase 3: " + obj.isRotation(s1, s2));
    }
}
