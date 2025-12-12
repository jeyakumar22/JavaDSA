package String;

import java.util.Arrays;

public class RemoveOuterParanthesis {
    public static void main(String[] args) {

        // Testcase 1
        String s1 = "(()())";
        System.out.println(removeOuterParentheses(s1));

        // Testcase 2
        String s2 = "()(()())(())";
        System.out.println(removeOuterParentheses(s2));

        // Testcase 3
        String s3 = "((()()))";
        System.out.println(removeOuterParentheses(s3));
    }

    // 👉 Write ONLY your solution logic inside this method
    public static String removeOuterParentheses(String s) {
        char[] ans =new char[s.length()];
        int p=0;
        int c=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                if(c>0){
                    ans[p++] = '(';
                }
                c++;
            }
            else{
                c--;
                if(c>0){
                    ans[p++] = ')';
                }
            }
        }
        return new String(ans,0,p);
    }
}

