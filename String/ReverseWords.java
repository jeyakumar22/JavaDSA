package String;

public class ReverseWords {
    public static void main(String[] args) {

        // Testcase 1
        String s1 = "the sky is blue";
        String s2 = "  hello   world  ";
        System.out.println(reverseWords1(s1));

        // Testcase 2
        //String s2 = "  hello   world  ";
        System.out.println(reverseWords1(s2));

        // Testcase 3
        String s3 = "a good   example";
        System.out.println(reverseWords1(s3));
    }

    // 👉 Write ONLY your logic here (no extra printing)
    public static String reverseWords(String s) {
        String temp ="";
        String ans ="";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) != ' '){
                temp+=s.charAt(i);
            }
            else{
                if(!temp.isEmpty()){
                    if(!ans.isEmpty()) {
                        ans = temp + " " + ans;
                    }
                    else{
                        ans=temp;
                    }
                }
                temp="";
            }
        }
        if(!temp.isEmpty()){
            if(!ans.isEmpty()) {
                ans = temp + " " + ans;
            }
            else{
                ans=temp;
            }
        }
        return ans;
    }public static String reverseWords1(String s) {
        String temp ="";
        String ans ="";
        int i=s.length()-1;
        while(i>=0){
            while(i>=0 && s.charAt(i) == ' ')i--;
            if(i<0) break;

            int end = i;
            while(i>=0 && s.charAt(i) !=' '){
                i--;
            }
            int start =i+1;
            //temp = s.substring(start,end+1);
            //System.out.println(temp);
            if(!ans.isEmpty()) ans += " " + s.substring(start,end+1);
            else ans += s.substring(start,end+1);
            //System.out.println(ans);
        }
        return ans;
    }
}

