package DSASheet.array;
import java.util.*;
public class array_easy {

    public static void largest(){
        int[] arr ={3,5,2,8,2,99,55,43,5} ;
        int m = arr[0];
        for (int x : arr){
            if (m < x){
                m = x;
            } 
        }
        System.out.println("maximum in an array" + m);
     }
    
    public static void secondLargest() {
    int[] arr = {3, 5, 2, 8, 2, 99, 55, 43, 5};
    int first = Integer.MIN_VALUE;
    int second = Integer.MIN_VALUE;

    for (int x : arr) {
        if (x > first) {
            second = first;
            first = x;
        } else if (x > second && x != first) {
            second = x;
        }
    }
    System.out.println(second);
}

    public static void thirdLargest() {
    int[] arr = {3, 5, 2, 8, 2, 99, 55, 43, 5};

    int first = Integer.MIN_VALUE;
    int second = Integer.MIN_VALUE;
    int third = Integer.MIN_VALUE;

    for (int x : arr) {
        if (x > first) {
            third = second;
            second = first;
            first = x;
        } else if (x > second && x != first) {
            third = second;
            second = x;
        } else if (x > third && x != second && x != first) {
            third = x;
        }
    }

    System.out.println(third);
}

    public static boolean issorted(){
        int[] arr ={1,2,2,2,3,4,5} ;
        for (int i = 0; i<arr.length -1 ; i++){
            if (arr[i] > arr[i+1]){
                System.out.println("not a sorted arr");
                return false;
            }
        }
        System.out.println("sorted array");
        return true;
        
     }
    
    public static void removedup(){
        int[] arr ={1,2,2,2,3,4,5,5,5,5,6,6,7,8} ;
        int j=0;
        for (int i = 1; i<arr.length ; i++){
           if (arr[i] != arr[j]){
            ++j;
            arr[j] = arr[i]; 
           }
        }
        for (int i=0; i<=j;i++){
            System.err.println(arr[i]);
        }

     }
   
    public static void rev(int[] arr , int st , int end){
        while (st<=end){
            int temp;
            temp = arr[st];
            arr[st]= arr[end];
            arr[end] = temp;
            st++;
            end--;
            
        }
    }
    
    public static void rotate_arr_Dtime(int[] arr){
        int d = 3;
        int n = arr.length;
        rev(arr,0,d-1);
        rev(arr,d,n-1);
        rev(arr,0,n-1);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
            
        }  
        System.out.println();
    }

    public static void endzero(){
        int[] arr ={1,2,0,0,2,3,0,1,0,0,1,1,1,0} ;
        int j=0;
        int temp;
        for (int i =0; i<arr.length; i++){
            if (arr[i] !=0){
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        for (int i=0;i<arr.length;i++){
            System.out.print(" " + arr[i]);
        }
    }
    
    public static ArrayList <Integer> union(){
        int[] arr1 ={1,2,3,4,5};
        int[] arr2 ={3,4,5,6,7};
        ArrayList <Integer> ans =new ArrayList <>();
        int i=0,j=0;
        while (i<arr1.length && j<arr2.length){
            if (arr1[i]<=arr2[j]){
                if (ans.size()==0 || ans.get(ans.size()-1) !=arr1[i]){
                    ans.add(arr1[i]);
                    
                }
                i++;
            }
            else{
                if (ans.size()==0 || ans.get(ans.size()-1) !=arr2[j]){
                    ans.add(arr2[j]);
                    
            }
            j++;
        }
         }
        while (i<arr1.length){
            if (ans.get(ans.size()-1) !=arr1[i]){
                    ans.add(arr1[i]);
                    
         }
         i++;
    }
        while (j<arr2.length){
            if (ans.get(ans.size()-1) !=arr2[j]){
                    ans.add(arr2[j]);
                    
         }
         j++;
    }
    // for (int k=0;k<ans.size();k++){
    //         System.out.println(ans.get(k));
    //     }
    return ans;
}
    
    public static int[] union_primitive_arr() {
    int[] arr1 = {1, 2, 3, 4, 5};
    int[] arr2 = {3, 4, 5, 6, 7};

    int i = 0, j = 0;
    int n = arr1.length, m = arr2.length;

    // max possible size
    int[] temp = new int[n + m];
    int k = 0;

    while (i < n && j < m) {
        if (arr1[i] <= arr2[j]) {
            if (k == 0 || temp[k - 1] != arr1[i]) {
                temp[k++] = arr1[i];
            }
            i++;
        } else {
            if (k == 0 || temp[k - 1] != arr2[j]) {
                temp[k++] = arr2[j];
            }
            j++;
        }
    }

    while (i < n) {
        if (k == 0 || temp[k - 1] != arr1[i]) {
            temp[k++] = arr1[i];
        }
        i++;
    }

    while (j < m) {
        if (k == 0 || temp[k - 1] != arr2[j]) {
            temp[k++] = arr2[j];
        }
        j++;
    }

    // create final result of correct size
    int[] result = new int[k];
    for (int x = 0; x < k; x++) {
        result[x] = temp[x];
    }

    return result;
}

    public static void Consecutive1(){
        int[] arr ={1,1,1,2,1,1,3,3,4,5,1,5,1,1,1,1,1,1} ;
        int c=0;
        int mc=c;
        for (int i = 0; i<arr.length ; i++){
           if (arr[i] == 1){
             c++;
             mc = Math.max(mc,c);
           }
           else{
            
            c=0;
           }
        }
         System.err.println(mc);

     }
    
    public static ArrayList <Integer> intersection_1(){
        int[] arr1 ={1,2,3,4,5};
        int[] arr2 ={3,4,5,6,7};
        int[] visit = {0,0,0,0,0};
        ArrayList <Integer> ans =new ArrayList <>();
        for (int i=0;i<arr1.length;i++){
            for (int j=0;j<arr2.length;j++){
                if (arr1[i]==arr2[j] && visit[j]==0){
                    visit[j]=1;
                    ans.add(arr1[i]);
                    break;
                }
                if(arr1[i]<arr2[j]){
                    break;
                }
            }
        }
    return ans;
}
    
    public static void hashmap(int[] arr){
        int n = arr.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<n; i++){
           int key = arr[i];
            if (map.containsKey(key)){
                map.put(key,map.get(key) + 1);
            }
            else{
                map.put(key,1);
            }
        }
        for (Map.Entry<Integer,Integer> entry_map : map.entrySet()){
            System.out.println("occurance of " +entry_map.getKey() + " is " + entry_map.getValue() );
        }
    }
    
    public static ArrayList <Integer> intersection_2(){
        int[] a ={1,2,3,4,5};
        int[] b ={3,4,5,6,7};
        int c = a.length;
        int d = b.length;
        int i=0,j=0;
        ArrayList <Integer> ans =new ArrayList <>();
        while(i<c && j<d){
            if (a[i] == b[j]){
                ans.add(a[i]);
                i++;
                j++;
            }
            else if (a[i] < b[j]){
                i++;
            }
            else if (a[i] > b[j]){
                j++;
            }

        }
    return ans;
}

    public static void one_occ(int[] arr){
        int n = arr.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<n; i++){
           int key = arr[i];
            if (map.containsKey(key)){
                map.put(key,map.get(key) + 1);
            }
            else{
                map.put(key,1);
            }
        }
        for (Map.Entry<Integer,Integer> entry_map : map.entrySet()){
            if (entry_map.getValue() == 1){
                System.err.println(entry_map.getKey());
            }
        }

        //second method xor learn xor property from tuf
        int xor = 0;
        for (int i=0; i<n;i++){
            xor =xor^arr[i];
        }
        System.err.println(xor);

    }
    
    public static void longest_subarray(int[] arr,int k){
        int n = arr.length;
        int sum = 0;
        int ml = 0;
        int reminder = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<n;i++){
            sum = sum + arr[i];
            if (sum == k){
                ml = i+1;
            }
            reminder =sum -k;
            if (map.containsKey(reminder)){
                int len = i - map.get(reminder);
                ml = Math.max(ml , len);
            }
            if (!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        System.out.println(ml);

    }
    
    public static int maximum(int a,int b){
        if (a > b){
            return a;
        }
        else{
            return b;
        }
    }
    
    public static void longest_subarray_positive_only(int[] arr,int k){
        int n = arr.length;
        int sum = arr[0];
        int ml = 0;
        int l=0,r =0;
        
        while(r<n){
            while(r>=l && sum>k){
                sum =sum - arr[l];
                l++;
            }
            
            if (sum == k){
                ml = Math.max(ml,(r - l )+1 );
            }
            r++;
            if (r<n){
                
                sum = sum + arr[r];
            }
        }
        System.out.println(ml);

    }
    public static void main (String args[]){
        //int[] arr = {1,2,3,4,5};
        // reverse(arr);
        endzero();      
        // largest();
        // issorted();
        // removedup();
        // Consecutive1();
        //ArrayList <Integer> union=union();
        //System.out.println(union);
        //ArrayList <Integer> intersection=intersection_1();
        //ArrayList <Integer> intersection_2=intersection_2();
        //System.out.println(intersection_2);
        int [] a = {1,2,1,1,1};
        Scanner jk = new Scanner(System.in);
        //one_occ(a);
        int k = jk.nextInt();
        //longest_subarray(a,k);
        jk.close();
        longest_subarray_positive_only(a,k);  
     }
    
}
