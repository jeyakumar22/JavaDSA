package DSASheet.String;
import java.util.*;
public class string_easy {

public static void remove_outer_paranthesis(String s){
    String ans= "";
    int c = 0;
    for (int i=0; i<s.length();i++){
        char ch = s.charAt(i);
        if (ch == '('){
            if (c>0){
                ans+=ch;
            }
            c++;
        }
        else if(ch == ')'){
            c--;
            if (c>0){
                ans += ch;
            }
        } 
    }
    System.out.println(ans);
}

//for remove outer paranthesis using += not good it create new str every time so string builder
public static void remove_outer_paranthesis_opti(String s) {
    StringBuilder ans = new StringBuilder();
    int count = 0;

    for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);

        if (ch == '(') {
            if (count > 0) ans.append(ch);  // skip outermost '('
            count++;
        } else if (ch == ')') {
            count--;
            if (count > 0) ans.append(ch);  // skip outermost ')'
        }
    }

    System.out.println(ans.toString());
}

public static void validparanthesis(String s){
    char[] ans =new char[s.length()];
    int j = -1;
    for (int i=0; i<s.length();i++){
        char ch = s.charAt(i);
        if (ch == '(' || ch == '{' || ch == '['){
            ans[++j] = ch;
            
        }
        else {
            if (j == -1){
                System.out.println("not valid");
                break;
            }
            char prev = ans[j];
            System.out.println(prev);
            if (ch ==')' && prev == '(' || ch =='}' && prev == '{' || ch ==']' && prev == '['){
                j--;
            }
            else{
               System.out.println("not valid");
            break;
            }

        } 
    }
    if (j==-1){
        System.out.println("valid");
    }
    
}

//deque (stack) version for validparanthesis
public static boolean isValidParentheses(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char ch : s.toCharArray()) {
        if (ch == '(' || ch == '{' || ch == '[') {
            stack.push(ch);
        } else {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if ((ch == ')' && top != '(') ||
                (ch == '}' && top != '{') ||
                (ch == ']' && top != '[')) {
                return false;
            }
        }
    }
    return stack.isEmpty();
}

public static void reverse_words(String s){
    String temp = "",ans="";
    int i =0, r =s.length() -1;
    while(i<=r){
        char ch = s.charAt(i);
        if (ch ==' '){
            if (ans!=""){
                ans=temp + " "+ ans;
                temp="";
            }
            else{
                ans = temp;
                temp="";
            }
        }else{
            temp+=ch;
        }
        i++;
    }
    if (temp!=""){  
        if (ans!=""){
            ans=temp + " "+ ans;
            temp="";
        }
        else{
            ans = temp;
            temp="";
        }
    }
    System.out.println(ans);
    }

//reverse word for more leading space ex  "  hello   world  "
public static String reverseWords_leading_space(String s) {
    StringBuilder res = new StringBuilder();
    int i = s.length() - 1;

    while (i >= 0) {
        while (i >= 0 && s.charAt(i) == ' ') i--; // skip spaces
        if (i < 0) break;

        int j = i;
        while (i >= 0 && s.charAt(i) != ' ') i--;

        // Append space only if result is not empty
        if (res.length() > 0) res.append(" ");
        res.append(s.substring(i + 1, j + 1));
    }

    return res.toString();
}

public static void large_odd_num(String s){
       int index=0;
       for (int i=s.length()-1;i>=0;i--){
            char ch =s.charAt(i);
            int num = ch -'0';
            if (num%2 == 1){
                index = i;
                break;
            }
       }
       for (int i=0;i<=index;i++){
        System.out.print(s.charAt(i));
       }
    
        
       
        }

public static void longest_prefix(String[] s){
       String pre = s[0];
       for (int i=1;i<s.length;i++){
            int j=0;
            while(j<pre.length() && j<s[i].length() && pre.charAt(j)==s[i].charAt(j)){
                j++;
            }
            pre = pre.substring(0, j);
       }
       System.out.println(pre);
        }

public static boolean isomorphic(String s,String st){
            HashMap<Character,Character> maps = new HashMap<>();
            HashMap<Character,Character> mapst = new HashMap<>();
            if (s.length() != st.length()){
                return false;
            }
            for (int i =0 ; i<s.length();i++){
                char ch = s.charAt(i);
                char ch1 = st.charAt(i);
                if (maps.containsKey(ch) && maps.get(ch)!=ch1){
                    return false;
                }
                if (mapst.containsKey(ch1) && mapst.get(ch1)!=ch){
                    return false;
                }
                maps.put(ch,ch1);
                mapst.put(ch1,ch);
            }
            return true;
            

    }

    //using arr
public static boolean isIsomorphic(String s, String t) {
    int[] mapS = new int[256];
    int[] mapT = new int[256];

    for (int i = 0; i < s.length(); i++) {
        if (mapS[s.charAt(i)] != mapT[t.charAt(i)])
            return false;

        // Use i + 1 to avoid 0-default ambiguity
        mapS[s.charAt(i)] = i + 1;
        mapT[t.charAt(i)] = i + 1;
    }

    return true;
}
 
public static boolean s_is_a_rotation_of_another(String s,String st){
            String str = s+s;
            int n = s.length();
            for (int i  =0 ; i<n; i++){
                boolean a= true;
                for(int j=0;j<n;j++){
                    if (str.charAt(j+i)!=st.charAt(j)){
                            a=false;
                            break;
                    }
                // for(int j=i;j<n+i;j++){
                //     if (str.charAt(j)!=st.charAt(j-i)){
                //             a=false;
                //             break;
                //     }
                }
                if (a) return true;
            }
            return false;

            //also ans using contains()

            // public static boolean isRotation(String s, String st) {
            //         return s.length() == st.length() && (s + s).contains(st);
            // }

    }

public static boolean anagram(String s,String st){
            int a = s.length();
            int b =st.length();
            if (a!=b){
                return false;
            }
            int[] freq = new int[26];
            for (int i = 0;i<a;i++){
                freq[s.charAt(i) - 'a']++;
            }
            for (int i = 0;i<a;i++){
                freq[st.charAt(i) - 'a']--;
            }
            for (int i = 0;i<26;i++){
                if (freq[i]!=0){
                    return false;
                }
            }
            return true;

    }



public static void main(String args []){
    Scanner jk = new Scanner(System.in);

    System.out.println(" enter the string;");
    String s = jk.nextLine();
    String st = jk.nextLine();
    jk.close();
    //String[] str ={"k","flow","flowght"} ;
    //remove_outer_paranthesis(s);
    //validparanthesis(s);
    //reverse_words(s);
    //large_odd_num(s);
    //longest_prefix(str);
    //boolean ans = isomorphic(s,st);
    //boolean ans = s_is_a_rotation_of_another (s,st);
    boolean ans = anagram(s,st);
    if(ans){
        System.out.println("anagram");
    }
    else{
        System.out.println("not anagram");
    }   
}
}