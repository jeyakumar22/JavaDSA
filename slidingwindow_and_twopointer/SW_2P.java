package DSASheet.slidingwindow_and_twopointer;

import java.util.*;

public class SW_2P {
    
public static int constant_window(int[] arr ,int k ){
    //find max sum
    int n=arr.length;
    int l = 0, r = k-1;
    int ml = 0 ;
    int sum = 0;
    for (int i = 0 ; i<=r;i++){
        sum+=arr[i];
    }
    while(r<n-1){
        sum=sum-arr[l];
        l++;
        r++;
        sum = sum+arr[r];
        ml = Math.max(ml, sum);
    }
    return ml;
}
   
public static int longest_arr_sum(int[] arr, int k) {
    int n = arr.length;
    int ml = 0;
    for (int i = 0;i<n ; i++){
        int sum = 0;
        for (int j = i;j<n ; j++){
            sum+=arr[j];
            if (sum <=k){
               ml = Math.max(ml, j-i+1); 
            }
            else if(sum>k) break;
    }
   }
   return ml;
}

public static int longest_arr_sum_sw(int[] arr, int k) {
    int n = arr.length;
    int ml = 0;
    int l = 0,r=0;
    int sum = 0;
    while(r<n){
        sum=sum+arr[r];
        while(l<r && sum>k){
            sum-=arr[l];
            l++;
        }
        if(sum<=k){
             ml = Math.max(ml, r-l+1);
        }
        r++;
    }
   return ml;
}

public static int longest_arr_sum_sw_op(int[] arr, int k) {
    int n = arr.length;
    int ml = 0;
    int l = 0,r=0;
    int sum = 0;
    while(r<n){
        sum=sum+arr[r];
        if(sum>k){
            sum-=arr[l];
            l++;
        }
        if(sum<=k){
             ml = Math.max(ml, r-l+1);
        }
        r++;
    }
   return ml;
}

public static int maxi_point_card(int[] arr, int k) {
    int n = arr.length;
    int ms = 0 ;
    int leftsum = 0,rightsum=0;

    for (int i = 0 ; i<=k-1;i++){
        leftsum+=arr[i];
    }
    ms = leftsum;
    int right_indx = n-1;
    for (int i = k-1;i>=0; i--){
        leftsum-=arr[i];
        rightsum+=arr[right_indx];
        right_indx--;
        ms = Math.max(ms, leftsum+rightsum);
    }
   return ms;
}

public static int longest_substr_without_dup(String s) {
    int n = s.length();
    int ml = 0;
    int len = 0;
    for (int i = 0;i<n ; i++){
        int hash[] = new int[256]; 
        //here initialy 0 was ok 
        //Arrays.fill(hash, -1);
        // for (int e = 0;e<256 ; i++){
        //         hash[e]=-1;
        //     }
        for (int j = i;j<n ; j++){
            if (hash[s.charAt(j)]==1) break;
            len = j - i +1 ;
            ml = Math.max(len,ml);
            hash[s.charAt(j)] =1;
    }
   }
   return ml;
}

public static int longest_substr_without_dup_opti(String s) {
   int n = s.length();
    int ml = 0;
    int len = 0;
    int l = 0,r=0;
    int hash[] = new int[256]; 
    Arrays.fill(hash, -1);
    // for (int e = 0;e<256 ; i++){
    //         hash[e]=-1;
    //     }
    while(r<n){
        if (hash[s.charAt(r)]!=-1){
            if (hash[s.charAt(r)]>=l){
                l=hash[s.charAt(r)]+1;
            }
        }
        len = r-l+1;
        ml = Math.max(len, ml);
        hash[s.charAt(r)]=r;
        r++;
    }
   return ml;
}

static int longest_substr_without_dup_opti_hp(String s) {
        HashMap < Character, Integer > mpp = new HashMap < Character, Integer > ();

        int left = 0, right = 0;
        int n = s.length();
        int len = 0;
        while (right < n) {
            if (mpp.containsKey(s.charAt(right))) left = Math.max(mpp.get(s.charAt(right)) + 1, left);

            mpp.put(s.charAt(right), right);

            len = Math.max(len, right - left + 1);
            right++;
        }
        return len;
    }

public static int maxi_consecutive_1_with_k0(int[] arr,int k) {
    int n = arr.length;
    int ml = 0;
    int len = 0;
    for (int i = 0;i<n ; i++){
        int zeros  = 0;
        for (int j = i;j<n ; j++){
           if (arr[j]==0){
            zeros++;
           }
           if (zeros<=k){
                len = j - i +1 ;
                ml = Math.max(len,ml);
           }
           else{
            break;
           }
    }
   }
   return ml;
}

public static int maxi_consecutive_1_with_k0_sw(int[] arr,int k) {
   int n =arr.length;
    int ml = 0;
    int len = 0;
    int l = 0,r=0;
   int zeros=0;
    while(r<n){
        if (arr[r]==0) zeros++;
        while(zeros>k){
            if (arr[l]==0) zeros--;
            l++;
        }
        if (zeros<=k){
            len = r-l+1;
            ml = Math.max(len, ml);
        }
        r++;
    }
   return ml;
}

public static int maxi_consecutive_1_with_k0_sw_opti(int[] arr,int k) {
   int n =arr.length;
    int ml = 0;
    int len = 0;
    int l = 0,r=0;
   int zeros=0;
    while(r<n){
        if (arr[r]==0) zeros++;
        if(zeros>k){ 
            if (arr[l]==0) zeros--;
            l++;
        }
        if (zeros<=k){
            len = r-l+1;
            ml = Math.max(len, ml);
        }
        r++;
    }
   return ml;
}

public static int fruits_in_basket(int[] arr,int k) {
    int n = arr.length;
    int ml = 0;
    int len = 0;
    for (int i = 0;i<n ; i++){
        Set<Integer> st = new HashSet<>();
        for (int j = i;j<n ; j++){
            st.add(arr[j]);
           if (st.size()<=k){
                len = j - i +1 ;
                ml = Math.max(len,ml);
           }
           else{
            break;
           }
    }
   }
   return ml;
}

public static int fruits_in_basket_sw(int[]arr,int k){
    int n= arr.length;
    int l=0,r=0;
    int ml=0,len=0;
    Map<Integer,Integer> mp = new HashMap<>();
    while(r<n){
        mp.put(arr[r],mp.getOrDefault(arr[r],0 )+1);
        if(mp.size()>k){
            while(mp.size()>k){
                 mp.put(arr[l],mp.getOrDefault(arr[l],0 )-1);
                 if (mp.get(arr[l]) ==0){
                    mp.remove(arr[l]);
                }
                l++;
            }
        }
        if (mp.size()<=k){
            len = r-l+1;
            ml = Math.max(ml, len);
        }
        r++;
    }
    return ml;
}

public static int fruits_in_basket_sw_opti(int[]arr,int k){
    int n= arr.length;
    int l=0,r=0;
    int ml=0,len=0;
    Map<Integer,Integer> mp = new HashMap<>();
    while(r<n){
        mp.put(arr[r],mp.getOrDefault(arr[r],0 )+1);
        if(mp.size()>k){
            if(mp.size()>k){
                 mp.put(arr[l],mp.getOrDefault(arr[l],0 )-1);
                 if (mp.get(arr[l]) ==0){
                    mp.remove(arr[l]);
                }
                l++;
            }
        }
        if (mp.size()<=k){
            len = r-l+1;
            ml = Math.max(ml, len);
        }
        r++;
    }
    return ml;
}

public static int longest_substr_with_k_diff_char_1(String s,int k) {
    int n = s.length();
    int ml = 0;
    int len = 0;
    for (int i = 0;i<n ; i++){
        Set<Character> st = new HashSet<>();
        for (int j = i;j<n ; j++){
            st.add(s.charAt(j));
           if (st.size()<=k){
                len = j - i +1 ;
                ml = Math.max(len,ml);
           }
           else{
            break;
           }
    }
   }
   return ml;
}

public static int longest_substr_with_k_diff_char_2(String s,int k) {//brute
    int n = s.length();
    int ml = 0;
    int len = 0;
    Map<Character,Integer> mp = new HashMap<>();
    for (int i = 0;i<n ; i++){
        mp.clear();
        for (int j = i;j<n ; j++){
            mp.put(s.charAt(j), mp.getOrDefault(s.charAt(j),0)+1);
           if (mp.size()<=k){
                len = j - i +1 ;
                ml = Math.max(len,ml);
           }
           else{
            break;
           }
    }
   }
   return ml;
}

public static int longest_substr_with_k_diff_char_sw(String s,int k){
    int n= s.length();
    int l=0,r=0;
    int ml=0,len=0;
    Map<Character,Integer> mp = new HashMap<>();
    while(r<n){
        mp.put(s.charAt(r),mp.getOrDefault(s.charAt(r),0 )+1);
        if(mp.size()>k){
            while(mp.size()>k){
                 mp.put(s.charAt(l),mp.getOrDefault(s.charAt(l),0 )-1);
                 if (mp.get(s.charAt(l)) ==0){
                    mp.remove(s.charAt(l));
                }
                l++;
            }
        }
        if (mp.size()<=k){
            len = r-l+1;
            ml = Math.max(ml, len);
        }
        r++;
    }
    return ml;
}

public static int longest_substr_with_k_diff_char_sw_opti(String s,int k){
    int n= s.length();
    int l=0,r=0;
    int ml=0,len=0;
    Map<Character,Integer> mp = new HashMap<>();
    while(r<n){
        mp.put(s.charAt(r),mp.getOrDefault(s.charAt(r),0 )+1);
        if(mp.size()>k){
            if(mp.size()>k){
                 mp.put(s.charAt(l),mp.getOrDefault(s.charAt(l),0 )-1);
                 if (mp.get(s.charAt(l)) ==0){
                    mp.remove(s.charAt(l));
                }
                l++;
            }
        }
        if (mp.size()<=k){
            len = r-l+1;
            ml = Math.max(ml, len);
        }
        r++;
    }
    return ml;
}

public static int no_substr_with_all_3elt(String s) {//brute
    int n = s.length();
    int count=0;
    for (int i = 0;i<n ; i++){
        int[] hash = {-1,-1,-1};
        for (int j = i;j<n ; j++){//Index 97 out of bounds for length 3 need to -"a" else err
           hash[s.charAt(j)-'a'] = 1;
           if (hash[0]+hash[1]+hash[2] == 3){//we get all 3 elt
            //count+=1; 
                //it run for all ss but we need count
                // where if we fount valid ss if more on right all are valid so just add that
                //explanation with ex in note

            //to optimaize count+=1 insteadd
               count = count+(n-j);
               break;  //imp to break
           } 
    }
   }
   return count;
}

public static int no_substr_with_all_3elt_sw(String s){
    int n= s.length();
    int count=0;
    int lastseen[] = {-1,-1,-1};
    int min_idx = -1;
    for(int i=0;i<n;i++){
        lastseen[s.charAt(i)-'a']=i;
        if (lastseen[0]!=-1&& lastseen[1]!=-1&& lastseen[1]!=-1){//without if check also work check in note
            min_idx = Math.min(Math.min(lastseen[0],lastseen[1]),lastseen[2]);
            count = count+(min_idx+1); //same in brute to reduce computation we did minwindow ss plus the character in front also valid ss so added 
        }
    }
       
    return count;
}

public static int long_charss_rep_replace(String s,int k) {//brute ..caps only
    int n = s.length();
    int ml = 0;
    int mf =0; //max freq
    int conversion = 0;
    for (int i = 0;i<n ; i++){
        int hash[] =new int[26];
        for (int j = i;j<n ; j++){
            hash[s.charAt(j)-'A']++;
            mf = Math.max(mf,hash[s.charAt(j)-'A']);
            conversion = (j-i+1)-mf;
            if (conversion<=k){
                ml=Math.max(ml,j-i+1);
            }
            else{
                break;
            }
    }
   }
   return ml;
}

public static int long_charss_rep_replace_sw(String s,int k){
    int n= s.length();
    int l=0,r=0;
    int ml=0,mf=0;
    int conversion=0;
    int[] hash = new int[26];
    while(r<n){
        hash[s.charAt(r)-'A']++;
        mf=Math.max(mf, hash[s.charAt(r)-'A']);
        conversion=(r-l+1) - mf;
        if((r - l + 1) - mf>k){ // here for loop  no need exxp in note 
            hash[s.charAt(l)-'A']--;
            mf=0;
            for (int i = 0;i<26 ; i++){
                mf=Math.max(mf,hash[i]);
   }
            l++;
        }
        if (conversion<=k){
                ml=Math.max(ml,r-l+1);
            }
        r++;
    }
    return ml;
}

public static int long_charss_rep_replace_sw_opti(String s,int k){
    int n= s.length();
    int l=0,r=0;
    int ml=0,mf=0;
    int conversion=0;
    int[] hash = new int[26];
    while(r<n){
        hash[s.charAt(r)-'A']++;
        mf=Math.max(mf, hash[s.charAt(r)-'A']);
        conversion=(r-l+1) - mf;
        if(conversion>k){
            hash[s.charAt(l)-'A']--;
            l++;
        }
        if (conversion<=k){
                ml=Math.max(ml,r-l+1);
            }
        r++;
    }
    return ml;
}

public static int binary_subarr_sum(int[]arr,int goal){//this is exactly equal as subarr sum ==k solved in arr so refer it i dirctly go too optimal
    int n= arr.length;
    int count = 0;
    for (int i = 0;i<n ; i++){
        int sum =0;
        for (int j = i;j<n ; j++){
            sum+=arr[j];
            if (sum ==goal){
                count+=1;
            }
            else if(sum>goal){
                break;
            }
    }
   }
   return count;
}

public static int binary_subarr_sum_opti(int[] arr,int goal){
    //this can done by math fn(==k) = fn(<=k)-fn(<=k-1)
    if (goal==0) return 0; //edge case
    int n= arr.length;
    int l=0,r=0;
    int count=0;
    int sum = 0;
    while(r<n){
        sum+=arr[r];
        while(sum>goal){
            sum-=arr[l];
            l++;
        }
        if (sum<=goal){
            count=count + (r-l+1); //count +len give all posible subarr in frount
        }
        r++;
    }
    return count;
}

public static int count_nice_subarr(int[] arr,int k){
    //this can done by math fn(==k) = fn(<=k)-fn(<=k-1)
    int n= arr.length;
    int l=0,r=0;
    int count=0;
    int sum = 0;
    while(r<n){
        sum+=arr[r]%2;
        while(sum>k){
            sum-=arr[l]%2;
            l++;
        }
        if (sum<=k){
            count=count + (r-l+1); //count +len give all posible subarr in frount
        }
        r++;
    }
    return count;
}

public static int subbarr_kdiff_int_count(int[] arr,int k) {//brute ..caps only
    int n = arr.length;
    int count=0;
    Map<Integer,Integer> mp = new HashMap<>();
    for (int i = 0;i<n ; i++){
        mp.clear();
        for (int j = i;j<n ; j++){
            mp.put(arr[j],mp.getOrDefault(arr[j],0)+1);
            if (mp.size()==k){
                count+=1;
            }
            else if (mp.size()>k){
                break;
            }
    }
   }
   return count;
}

public static int subbarr_kdiff_int_count_opti(int[] arr,int k){
    //this can done by math fn(==k) = fn(<=k)-fn(<=k-1)
    int n= arr.length;
    int l=0,r=0;
    int count=0;
    Map<Integer,Integer> mp = new HashMap<>();
    while(r<n){
         mp.put(arr[r],mp.getOrDefault(arr[r],0)+1);
         while(mp.size()>k){
             mp.put(arr[l],mp.getOrDefault(arr[l],0)-1);
             if(mp.get(arr[l])==0){
                mp.remove(arr[l]);
             }
            l++;
         }
        count+=r-l+1;
        r++;
    }
    return count;
}

public static String minimum_window_ss(String s,String s1) {//brute
    int n = s.length();
    int m=s1.length();
    
    int si = -1;
    int minlen = Integer.MAX_VALUE;
    for (int i = 0;i<n ; i++){
        int hash[] = new int[256];
        for (int k=0;k<m;k++){
            hash[s1.charAt(k)]++;
        }
        int count=0;
        for (int j = i;j<n ; j++){
           
           if(hash[s.charAt(j)]>0){
            count++;
           }
           hash[s.charAt(j)]--;
           if (count==m){
                si=i;
                if (j-i+1<minlen){
                    minlen=j-i+1;
                    si=i;
                }
                break;
           }
        }
    }
    System.out.println(si+" "+minlen);
    return s.substring(si,minlen+si);
}

public static String minimum_window_ss_opti(String s,String s1){
    int n = s.length();
    int m=s1.length();
    int count=0;
    int si = -1;
    int minlen = Integer.MAX_VALUE;
    int[] hash = new int[256];
    int l=0,r=0;
     for (int k=0;k<m;k++){
            hash[s1.charAt(k)]++;
        }
    while(r<n){
        if (hash[s.charAt(r)]>0){
            count++;
        }
        hash[s.charAt(r)]--;
        while (count==m) {
            if (r-l+1<minlen){
                
            si=l;
            minlen=r-l+1;
            }
            hash[s.charAt(l)]++;
            
            if (hash[s.charAt(l)]>0) count--;
            l++;
        }r++;
    }
    return si==-1 ? "" :s.substring(si, si + minlen) ;

       
}


public static void op(String answer){
    Scanner jk = new Scanner(System.in);
    System.out.println("enter the value for array range");
    int n = jk.nextInt();
    int[] arr = new int[n];
    for (int i=0;i<n;i++){
        arr[i] = jk.nextInt();
    }
    System.out.println("enter k value:");
    int k = jk.nextInt();
    
    
    switch(answer){
        
        case "constant_window":{
            int ans = constant_window(arr,k);
            System.out.println(ans);
            break;
        }
        //dynamic window
        case "longest_arr_sum<=k":{
            //brute using for loop for generate all subarrays
            int ans = longest_arr_sum(arr,k);
            //better using sliding window
            int ans1  = longest_arr_sum_sw(arr,k);
            //opti not all problem where we need ml so no need to shrink more exp in note use if instead while
            int ans2  = longest_arr_sum_sw_op(arr,k);
            
            System.out.println(ans2);
            break;
        }
        case "maxi_point_card":{
            int ans = maxi_point_card(arr,k);
            System.out.println(ans);
            break;
        }
        case "longest_substr_without_dup":{
            jk.nextLine();
            System.out.println("enter a str");
            String s =jk.nextLine(); 
            //brute by genarating all substr
            int ans = longest_substr_without_dup(s);
            //opti by 1pass sliding window 
            int ans1 = longest_substr_without_dup_opti(s);
            //same but hash map 
            int ans_m = longest_substr_without_dup_opti_hp(s);
            System.out.println(ans_m);
            break;
        }
        case "maxi_consecutive_1_with_k0":{
            jk.nextLine();
            System.out.println("enter a str");
            String s =jk.nextLine(); 
            //brute by genarating all substr
            int ans = maxi_consecutive_1_with_k0(arr,k);
            //opti by sliding window 
            int ans1 = maxi_consecutive_1_with_k0_sw(arr,k);
            //same if instead of while to reduce time  
            int ans2 = maxi_consecutive_1_with_k0_sw_opti(arr,k);
            System.out.println(ans2);
            break;
        }
        case "fruits_in_basket":{//we can state longest subarr with at most 2 types
            
            //brute by genarating all substr
            int ans = fruits_in_basket(arr,k);
            //opti by sliding window 
            int ans1 = fruits_in_basket_sw(arr,k);
            //same if instead of while to reduce time by moving l pointer once 
            int ans2 = fruits_in_basket_sw_opti(arr,k);
            System.out.println(ans2);
            break;
        }
        case "longest_substr_with_k_diff_char":{
            jk.nextLine();
            System.out.println("enter a str");
            String s =jk.nextLine(); 
            //brute by genarating all substr using set or map to achieve
            int ans_s = longest_substr_with_k_diff_char_1(s,k);
            int ans_m = longest_substr_with_k_diff_char_2(s,k);
            //opti by sliding window 
            int ans1 = longest_substr_with_k_diff_char_sw(s,k);
            //same if instead of while to reduce time by moving l pointer once 
            int ans2 = longest_substr_with_k_diff_char_sw_opti(s,k);
            System.out.println(ans2);
            break;
        }
        case "no_substr_with_all_3elt":{
            jk.nextLine();
            System.out.println("enter a str");
            String s =jk.nextLine(); 
            //brute by genarating all substr 
            int ans1 = no_substr_with_all_3elt(s);
            //opti by sliding window 
            int ans2 = no_substr_with_all_3elt_sw(s);
            System.out.println(ans2);
            break;
        }
        case "long_charss_rep_replace":{
            jk.nextLine();
            System.out.println("enter a str");
            String s =jk.nextLine(); 
            //brute by genarating all substr 
            //int ans1 = long_charss_rep_replace(s,k);
            // by sliding window 
            int ans2 = long_charss_rep_replace_sw(s,k);
            //optimal by sliding window 
            //int ans3 = long_charss_rep_replace_sw_opti(s,k);
            System.out.println(ans2);
            break;
        }
        case "binary_subarr_sum":{
            jk.nextLine();
            System.out.println("enter a str");
            String s =jk.nextLine(); 
            //brute by genarating all substr 
            //this is exactly equal as subarr sum ==k solved in arr so refer it i dirctly go too optimal
            int ans1 = binary_subarr_sum(arr,k);
            //optimal than prefix sum without hashmap
            //we cant directly got ==goal there is 0 affect the sum so l++ r++ stuck and wrong ans 
            //so //this can done by math fn(==k) = fn(<=k)-fn(<=k-1)
            // but goal =0 return 0 beacuse using 0,1 we cant find sum <0 as-1 
            int ans2 = binary_subarr_sum_opti(arr,k)- binary_subarr_sum_opti(arr,k-1) ;
            System.out.println(ans2);
            break;
        }
        case "count_nice_subarr":{
            //qn is subarr should have odd numbers equal to k
            //when just apply pattern to solve with binary sum subarray code
            int ans = count_nice_subarr(arr,k)-count_nice_subarr(arr,k-1);
            System.out.println(ans);
            break;
        }
        case "subbarr_kdiff_int_count":{
 
            //brute by genarating all substr == k 
            int ans = subbarr_kdiff_int_count(arr,k);
            //optimal by sliding window ==k so diffly
            int ans1 = subbarr_kdiff_int_count_opti(arr,k) ;
            int ans2 = subbarr_kdiff_int_count_opti(arr,k-1) ;
            System.out.println(ans1-ans2);
            break;
        }
        case "minimum_window_ss":{
            jk.nextLine();
            System.out.println("enter a str");
            String s =jk.nextLine(); 
            String s1 =jk.nextLine(); 
            //brute by genarating all substr 
            
            //String ans1 = minimum_window_ss(s,s1);
            // by sliding window
            
            String ans2 = minimum_window_ss_opti(s,s1); 
            System.out.println(ans2);
            break;
        }
    }
    jk.close();
}
public static void main(String args[]){
        op("minimum_window_ss");
}
}

