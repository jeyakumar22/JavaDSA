package String;

public class CountSubstring {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------- Testcase 1 ----------
        String s1 = "abcbaa";
        int k1 = 3;
        int ans1 = sol.countSubstringsAtMostK(s1, k1)
                - sol.countSubstringsAtMostK(s1, k1 - 1);
        System.out.println("Testcase 1 Output: " + ans1);

        // ---------- Testcase 2 ----------
        String s2 = "pqpqs";
        int k2 = 2;
        int ans2 = sol.countSubstringsAtMostK(s2, k2)
                - sol.countSubstringsAtMostK(s2, k2 - 1);
        System.out.println("Testcase 2 Output: " + ans2);

        // ---------- Testcase 3 ----------
        String s3 = "aaabbcccc";
        int k3 = 2;
        int ans3 = sol.countSubstringsAtMostK(s3, k3)
                - sol.countSubstringsAtMostK(s3, k3 - 1);
        System.out.println("Testcase 3 Output: " + ans3);
    }
}


class Solution {
    public int countSubstringsAtMostK(String s, int k) {
       int[] freq =  new int[128];
       int l=0;
       int r=0;
       int d=0;
       int n=s.length();
       int c=0;
       while(r<n){
           freq[s.charAt(r)-'a']++;
           if(freq[s.charAt(r)-'a'] ==1) d++;

           while(d>k) {
               freq[s.charAt(l) - 'a']--;
               if (freq[s.charAt(l) - 'a'] == 0) d--;
               l++;
           }
           c+=r-l+1;
           System.out.println(c + " d");
           r++;
       }
        return c;
    }
}
