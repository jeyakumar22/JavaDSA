package DSASheet.binary_search;

import java.util.*;

public class bs_easy {


public static int  binary_search(int[] arr ,int target){
    int n = arr.length;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] == target ){
            return mid ;
        }
        else if (arr[mid] > target){
            high = mid -1;
        }
        else{
            low = mid +1;
        }
    }
    return -1;
}

public static int  recursive_bs(int[] arr , int low ,int high ,int target){
    
        if (low>high) return -1;
        int mid = low + (-low + high)/2;
        if (arr[mid] == target ){
            return mid ;
        }
        else if (arr[mid] > target){
            return  recursive_bs(arr , low ,mid -1 ,target);
        }
        else{
           return  recursive_bs(arr , mid +1 ,high ,target);
        }
    
    
}

public static int  bs_low_bound(int[] arr ,int target){
    int n = arr.length;
    int ans = n;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] >= target ){
            ans = mid;
            high = mid -1;
        }
        else{
            low = mid +1;
        }
    }
    return ans ;  //return low
}

public static int  insert_pos(int[] arr ,int target){ //is also a lower bound code use for find insert position >=
    int n = arr.length;
    int ans = n;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] >= target ){
            ans = mid;
            high = mid -1;
        }
        else{
            low = mid +1;
        }
    }
    return ans ;  //insert position 
}

public static int  bs_upper_bound(int[] arr ,int target){
    int n = arr.length;
    int ans = n;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] > target ){
            ans = mid;
            high = mid -1;
        }
        else{
            low = mid +1;
        }
    }
    return ans ; //return low; is also a answer
}

public static int  bs_floor(int[] arr ,int target){
    int n = arr.length;
    int ans = -1;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] <= target ){
            ans = arr[mid];
            low = mid +1;
        }
        else{
            high = mid -1;
        }
    }
    return ans ; //return low; is also a answer
}

public static int  bs_ceil(int[] arr ,int target){
    int n = arr.length;
    int ans = -1;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] >= target ){
            ans = arr[mid];
            high = mid -1;
        }
        else{
            low = mid +1;
        }
    }
    return ans ;  //ceil >=
}

public static int[]  first_last_occ(int[] arr ,int target){
    int n = arr.length;
    int first = -1,last = -1;
    for (int i = 0;i<n ; i++){
            if (arr[i] == target){
                if (first == -1){
                    first =i;
                }
                else{
                    last =i;
                }
            }
   }
   return new int[]{first,last};
}

public static int[]  first_last_occ_opti(int[] arr ,int target){
    int n = arr.length;
    int lb =bs_low_bound(arr, target);
    int ub=bs_upper_bound(arr, target);
    if (lb==n || arr[lb]!=target){
        return new int[]{-1,-1};
    }
        return new int[]{lb,ub-1};
}

public static int first(int[ ] arr , int target){
    int n = arr.length;
    int first = -1;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] == target ){
            first = mid;
            high = mid -1;
        }
        else if (arr[mid] < target){
            low = mid +1;
        }
        else{
            high = mid -1;
        }
    }
    return first ;
}

public static int last(int[ ] arr , int target){
    int n = arr.length;
    int last = -1;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] == target ){
            last = mid;
            low = mid +1;
        }
        else if (arr[mid] < target){
            low = mid +1;
        }
        else{
            high = mid -1;
        }
    }
    return last ;
}

public static int[] bs_to_first_last(int[ ] arr , int target){
        
    int f = first(arr, target);
    if (f==-1) return new int[]{-1,-1}; //reduce o(log n ) operation
    int l = last(arr, target);
    // int l = -1;
    // if (f==-1){ // use to reduse o(log n)
    //      l =-1;
    // }
    // else{
    //     l = last(arr, target);
    // }
    return new int[] {f,l};
 }


 public static int rotated_arr(int[] arr , int target){
    int n = arr.length;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] == target ){
            return mid;
        }
        if (arr[mid] >= arr[low]){
            if (arr[low]<=target && arr[mid]>=target){
                high = mid-1;
            }
            else{
                low=mid +1;
            }
        }
        else{
            if (arr[high]>=target && arr[mid]<=target){
                    low=mid +1;
            }
            else{
                high = mid -1;
            }
                
        }
    }
     return -1;
}

public static int rotated_arr_dup(int[] arr , int target){
    int n = arr.length;
    int low =0,high = n-1;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid] == target ){
            return mid;
        }
        if (arr[mid]==arr[low] && arr[mid] == arr[high]){
            low = low+1;
            high=high-1;
            continue;
        }
        
        if (arr[mid] >= arr[low]){
            if (arr[low]<=target && arr[mid]>=target){
                high = mid-1;
            }
            else{
                low=mid +1;
            }
        }
        else  {
            if (arr[high]>=target && arr[mid]<=target){
                    low=mid +1;
            }
            else{
                high = mid -1;
            }
                
        }
    }
     return -1;
}

public static int small_rotate_arr(int[] arr ){ //only for uniqe value
    int n = arr.length;
    int low =0,high = n-1;
    int ans = arr[0];
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[low]<=arr[high]){ // sorted arr so low will ans
            ans = Math.min(ans, arr[low]);
            break;
        }
        // if (arr[low] == arr[mid] && arr[mid] == arr[high]) {//for handle duplicates
        //     low++;
        //     high--;
        //     continue;
        // }
        if (arr[low]<=arr[mid]){ //left sorted arr take min and eliminate
            ans = Math.min(ans, arr[low]);
            low = mid+1;
        }
        else{ //arr[mid]<arr[high]
             ans = Math.min(ans, arr[mid]);
           high=mid -1;
        }
    }
        return ans;
}

public static int small_rotate_arr_dup(int[] arr ){ //only for uniqe value
    int n = arr.length;
    int low =0,high = n-1;
    int ans = arr[0];
    // there is a mistake when 3313 l==m==h so 31 then 1 l==m==h and skip handle ans =low as 1 so ans 3
    //so this can handle ans = Math.min(ans,mid); always store mid as min so no values cant skip
    while (low<=high){
        int mid = (low + high)/2;
        ans = Math.min(ans,arr[mid]);
        if (arr[low]<arr[high]){ // sorted arr so low will ans and dont check low == high because if duplicate ans will wrong are handle dupicate first then check else check l<h only not ==
            ans = Math.min(ans, arr[low]);
            break;
        }
        if (arr[low] == arr[mid] && arr[mid] == arr[high]) {//for handle duplicates
            low++;
            high--;
            continue;
        }
        if (arr[low]<=arr[mid]){ //left sorted arr take min and eliminate
            ans = Math.min(ans, arr[low]);
            low = mid+1;
        }
        else{ //arr[mid]<arr[high]
             ans = Math.min(ans, arr[mid]);
                high=mid -1;
        }
    }
        return ans;
}

public static int times_rotated(int[] arr ){ //returning the index of the small elt is equal to times rotated array
    int n = arr.length;
    int low =0,high = n-1;
    int ans = arr[0];
    int index=-1;
       while (low<=high){
        int mid = (low + high)/2;
        ans = Math.min(ans,arr[mid]);
        index = mid;
        if (arr[low]<arr[high]){ // sorted arr so low will ans and dont check low == high because if duplicate ans will wrong are handle dupicate first then check else check l<h only not ==
            ans = Math.min(ans,arr[low]);
            index = low; // only index =mid at top is enough no need for each but know it 
            break;
        }
        if (arr[low] == arr[mid] && arr[mid] == arr[high]) {//for handle duplicates
            low++;
            high--;
            continue;
        }
        if (arr[low]<=arr[mid]){ //left sorted arr take min and eliminate
            ans = Math.min(ans, arr[low]);
            index=low;
            low = mid+1;
        }
        else{ //arr[mid]<arr[high]
             ans = Math.min(ans,arr[mid]);
             index = mid;
                high=mid -1;
        }
    }
        return index;
}

public static int find_1occ_elt(int[] arr ){
    int n = arr.length;
    for (int i = 0;i<n ; i++){
            if (i == 0){
                if (arr[i] != arr[i+1]){
                    return arr[i];
                }
            }
            else if (i==n-1){
               if (arr[i] != arr[i-1]){
                    return arr[i];
                } 
            }
            else{
                if (arr[i]!=arr[i+1] && arr[i]!=arr[i-1]){
                    return arr[i];
                }
            }
   }
   return -1;
}

public static int find_1occ_elt_opti(int[] arr ){
    int n = arr.length;
    if (arr[0]!=arr[1]) return arr[0];
    if (arr[n-1]!=arr[n-2]) return arr[n-1];
    int low =1,high = n-2;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid]!=arr[mid-1] && arr[mid]!=arr[mid+1] ){
            return arr[mid];
        }
        else if ((mid%2==1 && arr[mid]==arr[mid-1] )||(mid%2==0 && arr[mid]==arr[mid+1])){
            low = mid+1;
            
        }
        else{  //(mid%2==1 && arr[mid]==arr[mid+1] )||(mid%2==0 && arr[mid]==arr[mid-1])
            high=mid-1;
        }
    }
     return -1;
}

public static int find_peak_elt(int[] arr ){
    int n = arr.length;
    if (arr[0]>arr[1]) return arr[0];
    if (arr[n-1]>arr[n-2]) return arr[n-1];
    int low =1,high = n-2;
    while (low<=high){
        int mid = (low + high)/2;
        if (arr[mid]>arr[mid-1] && arr[mid]>arr[mid+1] ){
            return arr[mid];
        }
        else if (arr[mid]>arr[mid-1]){
            low = mid+1;
            
        }
        else if(arr[mid]>arr[mid+1]){  
            high=mid-1;
        }
        else{ // arr[mid]<arr[mid-1] and arr[mid]<arr[mid+1] this is for multiple peak
            high=mid-1;
        }
    }
     return -1;
}

public static void op(String answer){
    Scanner jk = new Scanner(System.in);
    System.out.println("enter the value for array range");
    int n = jk.nextInt();
    int[] arr = new int[n];
    for (int i=0;i<n;i++){
        arr[i] = jk.nextInt();
    }
    System.err.println("enter k value:");
    int k = jk.nextInt();
    jk.close();
    
    switch(answer){
        case "bs":{
            int ans = binary_search(arr,k);
            //int ans1 = recursive_bs(arr,0,arr.length,k);
            System.out.println(ans);
            break;
        }
        case "bs_low_bound":{
            int ans = bs_low_bound(arr,k);
            System.out.println(ans);
            break;
        }
        case "bs_upper_bound":{
            int ans = bs_upper_bound(arr,k);
            System.out.println(ans);
            break;
        }
        case "insert_pos":{
            int ans = insert_pos(arr,k);
            System.out.println(ans);
            break;
        }
        case "floor_and_ceil":{
            int floor = bs_floor(arr,k); //<=
            int ceil  = bs_ceil(arr,k);  //>= lower bound but ans =-1 not size 
            System.out.println(floor +" "  + ceil);
            break;
        }
        case "first_last_occ":{
            
            //int[] ans_brute  = first_last_occ(arr,k); 
            //int[] ans  = first_last_occ_opti(arr,k); 
            int[] ans_bs=bs_to_first_last(arr,k); // where we use bs to achieve the ans
            System.out.println(Arrays.toString(ans_bs));
            break;
        }
        case "count_occ":{
            // it will be last -first occurance +1 thats the ans
            int first = first(arr,k);
            if (first==-1) {
                System.out.println(0);
            }
            int last = last(arr, k);
            System.out.println("count of occurance = " + (last - first +1));
            break;
        }
        case "rotated_arr":{
            int ans = rotated_arr(arr,k);
            System.out.println(ans);
            break;
        }
        case "rotated_arr_dup":{
            int ans = rotated_arr_dup(arr,k);
            System.out.println(ans);
            break;
        }
        case "small_rotate_arr":{
            //int ans = small_rotate_arr(arr);  // can we do min of arr but O(n) sorted so bs ->O(logn)
            int ans1 = small_rotate_arr_dup(arr);  // can we do min of arr but O(n) sorted so bs ->O(logn)
            System.out.println(ans1);
            break;
        }
        case "times_rotated":{
            int ans = times_rotated(arr);
            System.out.println(ans);
            break;
        }
        case "find_1occ_elt":{
            //int ans = find_1occ_elt(arr);//o(n)
            int ans1 = find_1occ_elt_opti(arr);//o(log2n)
            //can use xor(arr) == ans but o(n) //always remembet 2 equal num and one single num we can use xor
            System.out.println(ans1);
            break;
        }
        case "find_peak_elt":{
            int ans = find_peak_elt(arr); // only one peak
            System.out.println(ans);
            break;
        }
    }
}
public static void main(String args[]){
        op("find_peak_elt");
}
}
