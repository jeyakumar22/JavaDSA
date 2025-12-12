package String;


class LongestPalindrome {
    public static void main(String[] args) {
        Ans sol = new Ans();

        // ---------- Testcase 1 ----------
        String s1 = "babad";
        System.out.println("Testcase 1 Output: " + sol.longestPalindrome(s1));
        // Expected: "bab" or "aba"

        // ---------- Testcase 2 ----------
        String s2 = "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv";
        System.out.println("Testcase 2 Output: " + sol.longestPalindrome(s2));
        // Expected: "bb"

        // ---------- Testcase 3 ----------
        String s3 = "aacabdkacaa";
        System.out.println("Testcase 3 Output: " + sol.longestPalindrome(s3));
        // Expected: "aca"

        // ---------- Testcase 4 ----------
        String s4 = "aaaa";
        System.out.println("Testcase 4 Output: " + sol.longestPalindrome(s4));
        // Expected: "aaaa"

        // ---------- Testcase 5 ----------
        String s5 = "racecarxyz";
        System.out.println("Testcase 5 Output: " + sol.longestPalindrome(s5));
        // Expected: "racecar"
    }
}
class Ans {
    public String longestPalindrome(String s) {
        String ans  = "";
        int max =0;
        for(int i=0;i<s.length();i++){
            //odd
            int l=i;
            int r=i;


            while(l>=0 && r<s.length() && s.charAt(l) ==  s.charAt(r)){
                char a = s.charAt(l);
                char b = s.charAt(r);
                l--;
                r++;

            }

            if(r-l-1 >max ){
                max = r-l-1;
                ans = s.substring(l+1,l+max+1);
            }

            //even
             l=i;
             r=i+1;


            while(l>=0 && r<s.length() && s.charAt(l) ==  s.charAt(r)){
                char a = s.charAt(l);
                char b = s.charAt(r);
                l--;
                r++;

            }

            if(r-l-1 >max ){
                max = r-l-1;
                ans = s.substring(l+1,l+max+1);
            }

        }

        return ans;
    }
}