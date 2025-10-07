package DSASheet.binary_search;

import java.util.*;


public class bs_ans {
    //what it mean ans because where all qn ask to find min or max possible integer as ans 
    //so the key is if we need to find min or max will be 1 to n so we can use bs 
    //and this is very imp to find we just use bs and check condition will inside bs and eliminate one half 

    public static int sqrt_floor(int k){
        int ans = 1;
        for (int i = 0;i<k ; i++){
            if (i*i<=k){
                ans=i;
            }
            else{
                break;
            }
        }
        return ans;
    }

    public static int sqrt_floor_opti(int k){
        int ans = 1;
        int low=1;
        int high = k;
        while(low<=high){
            int mid = (low+high )/2;
            if (mid*mid <=k ){
                ans = mid;
                low =mid+1;
            }
            else{
                high = mid-1;
            }

        }
        return ans;  //or high also a ans can skip variable cheak note
    }

    public static int power(int mid ,int n,int k){
        int ans = 1;
        //o(n)

        // for (int i = 1;i<=n ; i++){
        //     ans=ans*mid;
        // }
        // return ans;

        //o(logn)
        
        // while(n>0){
        //     if (n%2==1){
        //         ans*=mid;
        //         n--;
        //     }
        //     else{
        //         mid=mid*mid;
        //         n=n/2;
        //     }
        // }
        // return ans;

        //because the above code lead to overflow so...

        for (int i = 1;i<=n ; i++){
            ans=ans*mid;
            if (ans>k){
                return 2;
            }
        }
        if (ans==k) return 1;
        return 0; //if ans not equal even find the whole power refer notes 
        
    }

    public static int nth_sqrt_floor(int k,int n){
        
        int low=1;
        int high = k;
        while(low<=high){
            int mid = (low+high )/2;
            //overflow when power with exponential or linear meth refer notes 

            // int power = power(mid,n,k);
            // if (power ==k ){
            //     return mid;
            // }
            // else if(power>k) {
            //     high = mid-1;
            // }
            // else{
            //     low=mid+1;
            // }
            int power = power(mid,n,k);  //k=value mid ==posibile ans check vary n== nth root  
            if (power ==1 ){ 
                return mid;
            }
            else if(power>1) {
               high = mid -1;
            }
            else{
               low=mid+1;
            }

        }
        return -1;  
    }

    public static int totalhr(int[] arr,int mid){
        
    int totalH = 0;
    for (int i = 0; i < arr.length; i++) {
        // Manual ceil: ceil(v[i] / hourly) ceil(a/b) = (a+b-1)/b
        totalH += (arr[i] + mid - 1) / mid;
    }
    return totalH;
    }
    public static int koko_eating_bananas(int[] arr ,int k){
        int ans = 1;
        int low=1;
        int high = arr[arr.length-1];  //need to max value write a fn to find max if unsorted arr is imp
        while(low<=high){
            int mid = (low+high )/2;
            int totalhr = totalhr(arr,mid);
            if (totalhr <= k){
                ans=mid;
                high=mid-1;   
            }
            else{
                low=mid+1;
            }
        }
        return low;  //or low also a ans can skip variable cheak note
    }
    
    public static boolean check_bouquet(int[] arr,int days, int bou, int flower){
    int c = 0;
    int no_of_bo = 0;
    for (int i = 0; i < arr.length; i++) {
        if (arr[i]<=days){
            c++;
        }
        else{
            no_of_bo+=(c/flower);
            c=0;
        }
    }
    no_of_bo+=(c/flower);  //for last value if last value true false doesnt work
    return no_of_bo>=bou;

    }
    public static int min_day_to_bouquets(int[] arr ,int m,int k){
        int ans = -1;
        int min=arr[0],max=arr[0];
        for (int i = 0;i<arr.length ; i++){
                if (arr[i]>max){
                    max = arr[i];
                }
                if(arr[i]<min) {
                   min = arr[i] ;
                }
        }
        
        int low=min;
        int high = max;  
        while(low<=high){
            int mid = (low+high )/2;
            boolean check_bouquet = check_bouquet(arr,mid,m,k);
            // System.out.println(mid);
            // System.out.println(check_bouquet);
            if (check_bouquet == true){
                ans=mid;
                high=mid-1;
            }
            else{
               low=mid+1;
            }
        }
        if (ans == -1){ // imp for -ve case cant make bouquet or m*k >arr.size()
            return -1;
        }
        return low;  //or low also a ans can skip variable cheak note
    }
    
    public static int sum_of_smalldiv(int[] arr,int mid,int threshold){
    int s = 0;
    for (int i = 0; i < arr.length; i++) {
        // Manual ceil: ceil(v[i] / hourly) ceil(a/b) = (a+b-1)/b
        s += (arr[i] + mid - 1) / mid;
    }
    return s;
}
    public static int smallest_divisor(int[] arr ,int threshold){
        int ans = -1;
        int min=arr[0],max=arr[0];
        for (int i = 0;i<arr.length ; i++){
                if (arr[i]>max){
                    max = arr[i];
                }
                if(arr[i]<min) {
                   min = arr[i] ;
                }
        }
        
        int low=min;
        int high = max;  
        while(low<=high){
            int mid = (low+high )/2;
            int sum_of_smalldiv = sum_of_smalldiv(arr,mid,threshold);
            
            if (sum_of_smalldiv <= threshold){
                ans=mid;
                high=mid-1;
            }
            else{
               low=mid+1;
            }
        }
        return low;  //or low also a ans can skip variable cheak note
    }
    
    public static int day_req_mincap(int[] arr, int capacity, int days) {
    int req_days = 1;  // Start with day 1
    int total = 0;
    
    for (int i = 0; i < arr.length; i++) {
        // // If any single element is more than capacity, it's invalid
        // if (arr[i] > capacity) return Integer.MAX_VALUE;
        
        if (total + arr[i] > capacity) { // very imp when i try my own i done logical err when  compare to move weight to next day 
            // Ship this item on next day
            req_days++;
            total = arr[i];
        } else {
            total += arr[i];
        }
    }
    return req_days;
}
    public static int capacity_in_minday(int[] arr,int days){
       
        int sum=0,max=arr[0];
        for (int i = 0;i<arr.length ; i++){
                if (arr[i]>max){
                    max = arr[i];
                }
                sum+=arr[i];
        }
        int ans = -1;
        int low=max;
        int high = sum;  
        while(low<=high){
            int mid = (low+high )/2;
            int day_req_mincap = day_req_mincap(arr,mid,days);
            if (day_req_mincap <= days){
                ans=mid;
                high=mid-1;
            }
            else{
               low=mid+1;
            }
        }
        return low;  //ans or low also a ans can skip variable ,,,cheak note
    }
    
    public static int kth_missing_no(int[] arr, int k) {
    for (int i = 0; i < arr.length; i++) {
            if (arr[i]<=k){
                k++;
            }
            else{
                break;
            }
        }
        return k;
    }

public static int kth_missing_no_opti(int[] arr,int k){ //intuition in note lightly diff bs
        int low=0;
        int high = arr.length-1;  
        while(low<=high){
            int mid = (low+high )/2;
            if (arr[mid]-(mid+1)>=k){
                high=mid-1;
            }
            else{
               low=mid+1;
            }
            // if (arr[mid]-(mid+1)<k){
            //     low=mid+1;
            // }
            // else{
            //    high=mid-1;
            // }
        }
        return high+k+1;  //low+k also a ans ,cheak note y  and cheak y return like // must know y
    }

    public static boolean canweplace(int[] arr, int cows,int distance) {
        //only work for sorted arr because finding concequtive dis require sorted
        Arrays.sort(arr);
        int countcows = 1;
        int last = arr[0]; //initially 1st cow to 0 index use to calculate dis with cow to then cow 2 update like go
        for (int i = 1; i<arr.length;i++){
            if (arr[i] - last >=distance){
                countcows++;
                last = arr[i];
            }
        }
        if (countcows>=cows) return true;
        return false;
        
    }
    public static int aggressive_cows(int[] arr, int cows) {
    Arrays.sort(arr);
    //sorted array so no need max min a[0],a[n-1]
    int min=arr[0],max=arr[0];
        for (int i = 0;i<arr.length ; i++){
                if (arr[i]>max){
                    max = arr[i];
                }
                if(arr[i]<min) {
                   min = arr[i] ;
                }
        }
    int n = (max-min) +1;
    for (int i = 1; i < n; i++) {
        System.out.println(canweplace(arr,cows,i));
        if (canweplace(arr,cows,i) == true){
            continue;
        }
        else{
            return i-1;
        }
    }
    return -1;
}
    public static int aggressive_cows_bs(int[] arr,int cows){ //intuition in note lightly diff bs
        //only work for sorted arr because finding concequtive dis require sorted
        //sorted array so no need max min a[0],a[n-1]
        int ans=-1;
        Arrays.sort(arr);
        int min=arr[0],max=arr[0];
        for (int i = 0;i<arr.length ; i++){
                if (arr[i]>max){
                    max = arr[i];
                }
                if(arr[i]<min) {
                   min = arr[i] ;
                }
        }    
        int low=1;
        int high =max-min +1 ;  
        while(low<=high){
            int mid = (low+high )/2;
            if (canweplace(arr,cows, mid) == true){
                //System.out.println("eee");
                ans=mid;
                 low=mid+1;
            }
            else{
              high=mid-1;
            }
            // if (arr[mid]-(mid+1)<k){
            //     low=mid+1;
            // }
            // else{
            //    high=mid-1;
            // }
        }
        return high;   //ans also answer
    }
    
    public static int check_students(int[] arr, int students,int page) {
         int stu = 1 , pages = 0;
         for (int i = 0; i<arr.length;i++){
            if (pages+arr[i] <=page){
                pages+= arr[i];
            }
            else{
                stu++;
                pages=arr[i];
            }
        }
        return stu;
    }
    public static int book_allocation(int[] arr, int students) {
    int max=arr[0] ,sum = 0;
        for (int i = 0;i<arr.length ; i++){
                if (arr[i]>max){
                    max = arr[i];
                }       
                   sum+= arr[i] ;
        }
    for (int i = max; i <sum; i++) {
        System.out.println(check_students(arr,students,i));
        if (check_students(arr,students,i) == students){
            return i;
        }
    }
    return -1;
}
public static int book_allocation_bs(int[] arr,int students){ //intuition in note lightly diff bs
        int max=arr[0] ,sum = 0;
        for (int i = 0;i<arr.length ; i++){
                if (arr[i]>max){
                    max = arr[i];
                }
                   sum+= arr[i] ;
        }
        int low=max;
        int high =sum ;
        int ans=-1;  
        while(low<=high){
            //imp where we can write condition > or < but where equal in else part if stu> l=m+1 
            //in else part <= high 
            
            // if we write oppo <= if < we put ans will err
            int mid = (low+high )/2;
            if (check_students(arr,students,mid) <= students){
                //System.out.println("eee");
                ans=mid;
                high=mid-1;
            }
            else{
              low=mid+1;
            }
            // if (check_students(arr,students,mid) > students){
            //     //System.out.println("eee");
            //     ans=mid;
            //     low=mid+1;
            // }
            // else{
            //   high=mid-1;
            // }
        }
        return low;   //ans also answer
    }
    public static int median_of_array (int[] arr1,int arr2[]){
        int n1 = arr1.length;
        int n2 = arr2.length;
        int i = 0;
        int j = 0;
        int k=0;
        int [] temp = new int[n1+n2];
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                temp[k++] = arr1[i++];
            } else {
                temp[k++] = arr2[j++];
            }
        }

        while (i < n1) {
            temp[k++] = arr1[i++];
        }

        while (j < n2) {
            temp[k++] = arr2[j++];
        }
        int n = n1+n2;
        int m1 = temp[n/2];
        int m2 = temp[n/2-1];
        if (n%2 == 0){
            return (m1+m2) /2;
        }
        else{
            return m1;
        }
    }
    public static int median_of_array_1 (int[] arr1,int arr2[]){ // use double for decimal use long dt for intt
        int n1 = arr1.length;
        int n2 = arr2.length;
        int ind1 = (n1+n2)/2;
        int ind2 = ((n1+n2)/2)- 1;
        int ind_elt1 = -1;
        int ind_elt2 = -1;
        int i = 0;
        int j = 0;
        int index =0;
        while (i < arr1.length && j < arr2.length) {
            if (ind_elt1!=-1 &&ind_elt2!=-1)break;
            if (arr1[i] <= arr2[j]) {
                if (index == ind1 ) ind_elt1 = arr1[i];
                if (index == ind2 ) ind_elt2 = arr1[i];
                index++ ;
                i++;
            } else {
                if (index == ind1 ) ind_elt1 = arr2[j];
                if (index == ind2 ) ind_elt2 = arr2[j];
                index++ ;
                j++;
            }
        }

        while (i < n1) {
            if (ind_elt1!=-1 &&ind_elt2!=-1)break;
                if (index == ind1 ) ind_elt1 = arr1[i];
                if (index == ind2 ) ind_elt2 = arr1[i];
                index++ ;
                i++;
        }

        while (j < n2) {
            if (ind_elt1!=-1 &&ind_elt2!=-1)break;
                if (index == ind1 ) ind_elt1 = arr2[j];
                if (index == ind2 ) ind_elt2 = arr2[j];
                index++ ;
                j++;
        }
        int n = n1+n2;
        if (n%2 == 0){
            return (ind_elt1 + ind_elt2) /2;
        }
        else{
            return ind_elt1;
        }
    }
    public static int median_of_array_bs (int[] arr1,int arr2[]){ // use double for decimal use long dt for intt
        int n1 = arr1.length;
        int n2 = arr2.length;
        int n = n1+n2;
        //always do woth small size for time complexity in note check
        if (n1>n2) return  median_of_array_bs (arr2,arr1); //always work with small size
        int low =0;
        int high = n1;
        int left = (n1+n2+1)/2;  //symmetric //to find mid 2
        while(low<=high){
            int l1=Integer.MIN_VALUE,l2 = Integer.MIN_VALUE;
            int r1 =  Integer.MAX_VALUE,r2 = Integer.MAX_VALUE;
            int mid1 = (low+high)/2;
            int mid2 = left - mid1;
            
            if (mid1<n1) r1 =arr1[mid1];
            if (mid2<n2) r2 =arr2[mid2];

            if (mid1-1>=0) l1 =arr1[mid1-1];
            if (mid2-1>=0) l2 =arr2[mid2-1];
            
            if (l1<=r2 && l2<=r1){
                if (n%2==1) return Math.max(l1,l2);
                return (Math.max(l1,l2) + Math.min(r1,r2))/2;
            }
            else if (l1>r2) high = mid1-1;
            else low = mid1 +1;

        }
       return 0;
    }
    public static int kth_elt_of_2sortarr (int[] arr1,int arr2[],int k){ // use double for decimal use long dt for intt
        int n1 = arr1.length;
        int n2 = arr2.length;
        int n = n1+n2;
        //always do woth small size for time complexity in note check
        if (n1>n2) return  kth_elt_of_2sortarr (arr2,arr1,k); //always work with small size
        int low =Math.max(0, k-n2);
        int high = Math.min(k,n1);
        int left =  k; //to find mid 2
        while(low<=high){
            int l1=Integer.MIN_VALUE,l2 = Integer.MIN_VALUE;
            int r1 =  Integer.MAX_VALUE,r2 = Integer.MAX_VALUE;
            int mid1 = (low+high)/2;
            int mid2 = left - mid1;
            
            if (mid1<n1) r1 =arr1[mid1];
            if (mid2<n2) r2 =arr2[mid2];

            if (mid1-1>=0) l1 =arr1[mid1-1];
            if (mid2-1>=0) l2 =arr2[mid2-1];
            
            if (l1<=r2 && l2<=r1){
                 return Math.max(l1,l2);
            }
            else if (l1>r2) high = mid1-1;
            else low = mid1 +1;
        }
       return 0;
    }
    
    public static void op(String answer){
    Scanner jk = new Scanner(System.in);
    System.out.println("enter the value for array range");
    int n = jk.nextInt();
    int[] arr = new int[n];
    for (int i=0;i<n;i++){
        arr[i] = jk.nextInt();
    }
    System.err.println("enter m value:");
    int m = jk.nextInt();
    System.err.println("enter k value:");
    int k = jk.nextInt();
    
    
    switch(answer){
        case "sqrt_floor":{
            //int ans = sqrt_floor(k); // linear so O(n)not always but ...
            int ans1 = sqrt_floor_opti(k); // bs so O(log2n)
            System.out.println(ans1);
            break;
        }
        case "nth_sqrt_floor":{
            int ans = nth_sqrt_floor(k,n); // bs so O(log2n)
            System.out.println(ans);
            break;
        }
        case "koko_eating_bananas":{// brute will be for 1-11
            int ans = koko_eating_bananas(arr,k); // bs so O(log2n)
            System.out.println(ans);
            break;
        }
        case "min_day_to_bouquets":{// brute will be for 1-11
            int ans = min_day_to_bouquets(arr,m,k); // bs so O(log2n)
            if (m*k > arr.length){
                 System.out.println(-1);
            }
            else{
                    System.out.println(ans);
            }
            break;
        }
         case "smallest_divisor":{// brute will be for loop 1-11
            int ans = smallest_divisor(arr,k); // bs so O(log2n)
            System.out.println(ans);
            break;
        }
         case "capacity_in_minday":{// brute will be for loop max to sum of arr
            int ans = capacity_in_minday(arr,k); // bs so O(log2n)
            System.out.println(ans);
            break;
        }
         case "kth_missing_no":{
            int ans = kth_missing_no(arr,k); //o(n) // brute intuition in note
            int ans1 = kth_missing_no_opti(arr,k);  //intuition in the note and return variable case also explained
            System.out.println(ans);
            break;
        }
         case "aggressive_cows":{
            int ans = aggressive_cows(arr,k); //o(n) // brute intuition in note
            int ans1 = aggressive_cows_bs(arr,k);  
            System.out.println(ans1);
            break;
        }
         case "book_allocation":{
            //int ans = book_allocation(arr,k); //o(n) 
            int ans1 = book_allocation_bs(arr,k); //bs o(log 2 q)
            System.out.println(ans1);
            break;
        }
         case "painter_partition or subbarray with k also same as book allocation prblm":{
            //int ans = book_allocation(arr,k); //o(n) 
            int ans1 = book_allocation_bs(arr,k); //bs o(log 2 q)
            System.out.println(ans1);
            break;
        }
         case "median_of_array":{
            System.out.println("enter the value for array1 range");
            int n1 = jk.nextInt();
            int[] arr1 = new int[n1];
            for (int i=0;i<n1;i++){
                arr1[i] = jk.nextInt();
            }
            System.out.println("enter the value for array2 range");
            int n2 = jk.nextInt();
            int[] arr2 = new int[n2];
            for (int i=0;i<n2;i++){
                arr2[i] = jk.nextInt();
            }
            
            
            //int ans = median_of_array(arr1,arr2); //using extra space
            //int ans = median_of_array_1(arr1,arr2); // without using using extra space only find 2 index for find median 
            int ans = median_of_array_bs(arr1,arr2); // optimal solution with log 2 q
            //the intuition concept in note 
            System.out.println(ans);
            break;
        }
         case "kth_elt_of_2sortarr":{
            System.out.println("enter the value for array1 range");
            int n1 = jk.nextInt();
            int[] arr1 = new int[n1];
            for (int i=0;i<n1;i++){
                arr1[i] = jk.nextInt();
            }
            System.out.println("enter the value for array2 range");
            int n2 = jk.nextInt();
            int[] arr2 = new int[n2];
            for (int i=0;i<n2;i++){
                arr2[i] = jk.nextInt();
            }
            jk.close();
            //same as median of 2 sorted arr but it has some changes ,
            //i wrote only optimal sol chaeck other sol from before problem 
            int ans = kth_elt_of_2sortarr(arr1,arr2,k);
            System.out.println(ans);
            break;
        }
        
    }
}
public static void main(String args[]){
        op("kth_elt_of_2sortarr");
}
}