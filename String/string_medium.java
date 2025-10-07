package DSASheet.String;

import java.util.*;

public class string_medium {

    public static String sort_char_by_frequency(String str){
            int maxi_freq = 0;
            Map<Character,Integer> mp = new HashMap<>();
            for (int i=0 ; i<str.length();i++){
                char ch = str.charAt(i);
                mp.put(ch,mp.getOrDefault(ch,0)+1);
                maxi_freq= Math.max(maxi_freq,mp.getOrDefault(ch, 0)+1);
            }
            List<List<Character>> bucket = new ArrayList<>();
            // for (int i = 0; i <= maxi_freq; i++) {
            //     bucket.add(new ArrayList<>());
            // }
            while (maxi_freq-- >= 0)bucket.add(new ArrayList<>());
            for (Map.Entry<Character,Integer> entry :mp.entrySet()){
                char ch = entry.getKey();
                int count = entry.getValue();
                bucket.get(count).add(ch);
            }
            //System.out.println(bucket);
             StringBuilder result = new StringBuilder();
        for (int i = maxi_freq; i > 0; i--) {
            for (char ch : bucket.get(i)) {
                for (int j = 0; j < i; j++) {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }
    public static void max_deapth_paranthesis(String str){
        int c = 0;
        int maxi =0;
        for (char i : str.toCharArray()){
            //System.out.println(i);
            if (i == '('){
                c++;
                maxi=Math.max(c,maxi);
            }
            else if (i == ')'){
                c--;
            }
        }
        System.out.println(maxi); 
    }
    public static void integer_to_roman(int k){
        String[] thousands = {"","M","MM","MMM",};

    }
    public static void op(String answer){
    Scanner jk = new Scanner(System.in);
    System.out.println("enter the string");
    String str = jk.nextLine();
    // int[] arr = new int[n];
    // for (int i=0;i<n;i++){
    //     arr[i] = jk.nextInt();
    // }
    // System.err.println("enter k value:");
    // int k = jk.nextInt();
    switch(answer){
        case "sort_char_by_frequency":{
            String ans = sort_char_by_frequency(str);
            System.err.println(ans);
            break;
        }
        case "max_deapth_paranthesis":{
            max_deapth_paranthesis(str);
            break;
        }
        case "integer_to_roman":{
            System.err.println("enter k value:");
            int k = jk.nextInt();
            integer_to_roman(k);
            break;
        }
    }
    jk.close();
}
public static void main(String args[]){
        op("integer_to_roman");
}
}



