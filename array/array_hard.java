package DSASheet.array;

import java.util.*;

public class array_hard {

public static int compare(ArrayList<Integer>a ,ArrayList<Integer>b){
    if (a.get(0)!=b.get(0)){
        return a.get(0) - b.get(0);
    }
    else{
        return a.get(1) -b.get(1);
    }
    
}

public static ArrayList<ArrayList<Integer>> arr_sort(ArrayList<ArrayList<Integer>> arr){ //based on first 2 element

    int n = arr.size();
    for (int i = 0;i<n-1 ; i++){
        for (int j=0;j<n-i-1;j++){
            if (compare(arr.get(j),arr.get(j+1))>0){
                 ArrayList<Integer> temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
            }
        }
    }
    return arr;

}

public static int compare1(int[]a , int[]b){
    if (a[0]!=b[0]){
        return a[0] - b[0];
    }
    else{
        return a[1] - b[1];
    }
    
}

public static int[][] arr_sort1(int[][] arr){ //based on first 2 element

    int n = arr.length;
    for (int i = 0;i<n-1 ; i++){
        for (int j=0;j<n-i-1;j++){
            if (compare1(arr[j],arr[j+1])>0){
                 int[] temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
    return arr;

}

public static void pascal_triangle_ncr(int r, int c){
   r = r-1;
   c = c-1;
   int result = 1;
   for (int i=0;i<c;i++){
       result = result *(r - i);
       result = result / (i+1);
   }
   System.out.println(result);
}

public static ArrayList<Integer> pascal_triangle_ncr_row(int r){
   int result = 1;
   ArrayList<Integer> ans =new ArrayList<>() ;
   //System.out.print(result+" ");
   ans.add(result);
   for (int i=1;i<r;i++){
        result = result * (r - i);
        result = result / i;
        //System.out.print(result+" ");
        ans.add(result);
   }
   return ans;
}

public static void pascal_triangle(int k){
   
   for (int i=1;i<=k;i++){
        System.out.println(pascal_triangle_ncr_row(i));
   }
}

public static void majarity_n3(int[] arr){
    //refer brute using 2 for loop and better using hash in note 
    //optimal using moore's voting algo
    int c1=0,c2=0,e1=-1,e2=-1;
   
   for (int i=0;i<arr.length;i++){
        if (c1==0){
            c1=1;
            e1 = arr[i];
        }
        else if (c2==0){
            c2=1;
            e2 = arr[i];
        }
        else if (e1 == arr[i]){
            c1++;
        }
        else if (e2 == arr[i]){
            c2++;
        }
        else{
            c1--;
            c2--;
        }
   }
   System.out.println(e1 +" "+e2 );
   c1=0;
   c2=0;
   ArrayList<Integer> ans =new ArrayList<>();
   for (int i = 0;i<arr.length;i++){
    if(e1 == arr[i]){
        c1++;
    }
    if(e2 == arr[i]){
        c2++;
    }
   }
   int min = (arr.length/3) +1;
   if (c1>=min) ans .add(e1);
   if (c2>=min) ans .add(e2);
   System.out.println(ans);
}

public static void maximum_product_arr_1(int[] arr){ //this is a bruteforse o(n^2) use optimal as below
   int n = arr.length;
    int maxi =1;
   for (int i=0;i<n;i++){
        int ans=1;
       for (int j =i;j<n;j++){
            ans=ans*arr[j];
            maxi = array_easy.maximum(maxi, ans);
       }
   }
   System.out.println(maxi);
}

public static void maximum_product_arr_opti(int[] arr){ //this is a bruteforse o(n^2) use optimal as below
   int n = arr.length;
   int maxi =1;
   int prefix = 1,suffix = 1;
   for (int i=0;i<n;i++){
        if (prefix == 0) prefix = 1; //imp to reset arrr when 0
        if (suffix == 0) suffix = 1; //imp to reset arrr when 0
        prefix = prefix*arr[i];
        suffix = suffix*arr[n-1-i];
        maxi = array_easy.maximum(maxi,array_easy.maximum(prefix, suffix));
   }
   System.out.println(maxi);
}

public static void largest_arr_0(int[] arr){ //this is a bruteforse o(n^2) use optimal as below
   int n = arr.length;
   int maxi =1;
   for (int i=0;i<n;i++){
       int sum =0;
       for (int j =i;j<n;j++){
            sum+=arr[j];
            if (sum == 0){
                maxi = array_easy.maximum(maxi,(j-i) +1);
            }
       }
   }
   System.out.println(maxi);
}

public static void largest_arr_0_opti(int[] arr){ 
   int n = arr.length;
   int maxi =1;
   int sum =0;
   HashMap<Integer,Integer> mp = new HashMap<>();
   for (int i=0;i<n;i++){
       sum +=arr[i];
       if (sum == 0){
            maxi = i+1;
       }
       else{
        if (mp.get(sum)!=null){ //if (mp.containsKey(sum)) both are same
            maxi = array_easy.maximum(maxi, i-mp.get(sum));
        }
        else{
            mp.put(sum,i); // duplicate  wont came thre and update becaus if check  handle it 
        }
       }
   }
   System.out.println(maxi);
}

public static void merge_overlaping_intervals(ArrayList<ArrayList<Integer>> arr){ //brute
   //where i did very big mistake we can easily use arr 
   //no need array list ther only at ans need arraylist 
   //because where we need to get add operation no need for any other 
   //so we can use arr itself
   
    int n = arr.size();
   arr_sort(arr);
   //System.out.println(arr);
   ArrayList<List<Integer>> ans=new ArrayList<>();
   for (int i=0;i<n;i++){
       int st = arr.get(i).get(0);
       int end = arr.get(i).get(1);
       if (!ans.isEmpty() && ans.get(ans.size()-1).get(1)>=end){
        continue;
       }
       for (int j =i+1 ; j<n;j++){
        if (end >= arr.get(j).get(0)){
            end = array_easy.maximum(end ,arr.get(j).get(1));
            
        }
        else{
            break;
        }
       }
       ans.add(Arrays.asList(st, end));
       
       
   }
   System.out.println(ans);
}

public static void merge_overlaping_intervals_opti(int[][] arr){
   
   int n = arr.length;
   arr_sort1(arr);
   //System.out.println(arr);
   ArrayList<List<Integer>> ans=new ArrayList<>();
   for (int i=0;i<n;i++){
     if (ans.isEmpty() || ans.get(ans.size()-1).get(1) < arr[i][0]){
        ans.add(Arrays.asList(arr[i][0],arr[i][1]));
     }
     else{
        ans.get(ans.size()-1).set(1,Math.max(arr[i][1],ans.get(ans.size()-1).get(1)));
     }
   }
   System.out.println(ans);
}

public static void merge_2sort_arr_1(int[] arr ,int[] arr1){
   
   int n = arr.length;
   int m = arr1.length;
   int[] ansarr = new int[n+m];
   int l =0 , r=0;
   int ind = 0;
   while(l<n && r<m){
    if (arr[l]<arr1[r]){
        ansarr[ind] = arr[l];
        ind++;
        l++;
    }
    else{
         ansarr[ind] = arr1[r];
         ind++;
         r++;
    }  
   }
   while(l<n){
    ansarr[ind] = arr[l];
        ind++;
        l++;
   }
   while(r<m){
    ansarr[ind] = arr1[r];
         ind++;
         r++;
   }
   System.out.println(Arrays.toString(ansarr));
   for (int i = 0;i<n+m ; i++){
        if (i<n){
            arr[i] = ansarr[i];
        }
        else{
            arr1[i-n] = ansarr[i];
        }
   }
   System.out.println(Arrays.toString(arr));
   System.out.println(Arrays.toString(arr1));
}

public static void merge_2sort_arr_2(int[] arr1 ,int[] arr2){
   
   int n = arr1.length;
   int m = arr2.length;
   int l =n-1, r=0;
   for (int i = 0;i<Math.min(n,m) ; i++){
        if (arr1[l]>arr2[r]){
            int temp = arr1[l];
             arr1[l] = arr2[r];
             arr2[r] = temp;

             l--;
             r++;
        }
        else{
            break;
        }
   }
   Arrays.sort(arr1);
   Arrays.sort(arr2);
   System.out.println(Arrays.toString(arr1));
   System.out.println(Arrays.toString(arr2));
}

public static void sum_3(int[] arr){
   
   int n = arr.length;
   HashSet<ArrayList<Integer>> set = new HashSet<>();
   for (int i = 0;i<n ; i++){
        for (int j = i+1;j<n ; j++){
            for (int k = j+1;k<n ; k++){
                if(arr[i]+arr[j]+arr[k] == 0){
                    ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(arr[i],arr[j],arr[k]));
                    temp.sort(null);
                    set.add(temp);
                }
   }
   }
   }
   
   System.out.println(set); // if u want as in ans like store all in ans list of list add all set elt to ans list
}

public static void sum_3_1(int[] arr){
   
   int n = arr.length;
   HashSet<ArrayList<Integer>> set = new HashSet<>();
   for (int i = 0;i<n ; i++){
        Set<Integer> check_set = new HashSet<>();
        for (int j = i+1;j<n ; j++){
            int third = -(arr[i]+arr[j]);
            if (check_set.contains(third)){
                    ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(arr[i],arr[j],third));
                    temp.sort(null);
                    set.add(temp);
            }
            check_set.add(arr[j]);
        }
        
   }
   System.out.println(set);
   }

public static void sum_3_opti(int[] arr){
   
   int n = arr.length;
   Arrays.sort(arr); // this is going to done by 2 pointer aproch so need to be sorted arr 
   for (int i = 0;i<n ; i++){
       if (i>0 && arr[i]==arr[i-1]){
        continue;
       }
       int j=i+1;
       int k=n-1;
       while(j<k){
        int sum =arr[i] ;
        sum +=arr[j]+arr[k]; //this is for avoid long int
        if (sum >0){
            k--;
        }
        else if(sum <0){
            j++;
        }
        else{
            System.out.println(arr[i]+" "+arr[j]+" "+arr[k]);
            //u want to store and print make array list to store each triplet \\
            //then make list of list for all triplet   now we can just print and see 
            //if solving leetcode it ask a array of ans thet time we need to take care of it

            //move k j after match sum ==0 
            j++;
            k--;
            while(j<k && arr[j]==arr[j-1]) j++;
            while(j<k && arr[k]==arr[k-1]) k--;
            //after obtain triplet we need to move but the value of j,k not equale to prev else it make duplicate
        }
       }
        
   }
   
   }

public static void sum_4(int[] arr){
   int n = arr.length;
   HashSet<ArrayList<Integer>> set = new HashSet<>();
   for (int i = 0;i<n ; i++){
        for (int j = i+1;j<n ; j++){
            for (int k = j+1;k<n ; k++){
                for (int l = k+1;l<n ; l++){

                if(arr[i]+arr[j]+arr[k]+arr[l] == 0){
                    ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(arr[i],arr[j],arr[k],arr[l]));
                    temp.sort(null);
                    set.add(temp);
                }
            }
   }
   }
   }
   
   System.out.println(set); // if u want as in ans like store all in ans list of list add all set elt to ans list
}

public static List<List<Integer>> sum_4_1(int[] nums){
   
    int n = nums.length; // size of the array
        Set<List<Integer>> st = new HashSet<>();
        int target = 0;
        // checking all possible quadruplets:
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set<Long> hashset = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    // taking bigger data type
                    // to avoid integer overflow:
                    long sum = nums[i] + nums[j];
                    sum += nums[k];
                    long fourth = target - sum;
                    if (hashset.contains(fourth)) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add((int) fourth);
                        temp.sort(Integer::compareTo);
                        st.add(temp);
                    }
                    // put the kth element into the hashset:
                    hashset.add((long) nums[k]);
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>(st);
        return ans;
    }

public static void sum_4_opti(int[] arr){
   
   int n = arr.length;
   Arrays.sort(arr); // this is going to done by 2 pointer aproch so need to be sorted arr 
   for (int i = 0;i<n ; i++){
       if (i>0 && arr[i]==arr[i-1]){
        continue;
       }
       for (int j=i+1; j<n;j++){
            if (j>i+1 && arr[j]==arr[j-1]){
                continue;
            }
        
       int k=j+1;
       int l=n-1;
       while(k<l){
        int sum =arr[i] ;
        sum +=arr[j]+arr[k]; //this is for avoid long int
        sum += arr[l];
        if (sum >0){
            l--;
        }
        else if(sum <0){
            k++;
        }
        else{
            System.out.println(arr[i]+" "+arr[j]+" "+arr[k]+" "+arr[l]);
            //u want to store and print make array list to store each triplet \\
            //then make list of list for all triplet   now we can just print and see 
            //if solving leetcode it ask a array of ans thet time we need to take care of it

            //move k l after match sum ==0 
            k++;
            l--;
            while(k<l && arr[k]==arr[k-1]) k++;
            while(k<l && arr[l]==arr[l-1]) l--;
            //after obtain triplet we need to move but the value of j,k not equale to prev else it make duplicate
        }
       }
        
   }
   
   }
       }

public static int count_xor_eqltok(int[] arr,int k){
    int count=0;
    int n = arr.length;
    for (int i = 0;i<n ; i++){
        int xor=0;
        for (int j = i;j<n ; j++){
            xor=xor^arr[j];
            if (xor==k){
                count++;
            }
   }
   }
    return count;
}

public static int count_xor_eqltok_opti(int[] arr,int k){
    int count=0;
    int n = arr.length;
    int xor=0;
    int x=0;
    Map<Integer,Integer> mp = new HashMap<>();
    mp.put(0,1); //pre insertion 0 for calc
    for (int i = 0;i<n ; i++){
        xor=xor^arr[i];
        x=xor^k;
        count =count+mp.get(x);
        mp.put(xor,mp.getOrDefault(xor, 0)+1);
   }
    return count;
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
    System.err.println("enter r value:");
    int r = jk.nextInt();
    System.err.println("enter c value:");
    int c = jk.nextInt();
    
    jk.close();
    switch(answer){
        case "pascal_triangle_ncr":{
            pascal_triangle_ncr(r, c);
            break;
        }     
        case "pascal_triangle_ncr_row":{
            //pascal_triangle_ncr_row(r);
            ArrayList<Integer> ans = pascal_triangle_ncr_row(r);
            System.out.println(ans);
            break;
        }     
        case "pascal_triangle":{
            pascal_triangle(k);
            break;
        }     
        case "majarity_n3":{
            majarity_n3(arr);
            break;
        }     
        case "maximum_product_arr":{
            //maximum_product_arr_1(arr);
            maximum_product_arr_opti(arr);
            break;
        }     
        case "largest_arr_0":{
            //largest_arr_0(arr);
            largest_arr_0_opti(arr);
            break;
        }     
        case "merge_overlaping_intervals":{
            //merge_overlaping_intervals(arr);
            ArrayList<ArrayList<Integer>> ip_arr =new ArrayList<>();
            ip_arr.add(new ArrayList<>(Arrays.asList(1,3)));
            ip_arr.add(new ArrayList<>(Arrays.asList(2,6)));
            ip_arr.add(new ArrayList<>(Arrays.asList(16,17)));
            ip_arr.add(new ArrayList<>(Arrays.asList(8,9)));
            ip_arr.add(new ArrayList<>(Arrays.asList(9,11)));
            ip_arr.add(new ArrayList<>(Arrays.asList(15,18)));
            int[][] ip_arr1 ={{1,2},{2,3},{2,6},{8,9},{9,11},{15,18}};
            //merge_overlaping_intervals(ip_arr);
            merge_overlaping_intervals_opti(ip_arr1);
            break;
        }     
        case "merge_2sort_arr":{
            int[] arr1 = {1,3,5,6};
            int[] arr2 = {2,4,7,9,11};
            //merge_2sort_arr_1(arr1,arr2);
            merge_2sort_arr_2(arr1,arr2);
            break;
        }     
        case "sum_3":{
            //sum_3(arr);
            //sum_3_1(arr);
            sum_3_opti(arr);
            break;
        }     
        case "sum_4":{
            //sum_4(arr);
            //System.out.println(sum_4_1(arr));
            
            sum_4_opti(arr);
            break;
        }     
        case "count_xor_eqltok":{
            
            int count=count_xor_eqltok(arr,k);
            System.out.println(count);
            break;
        }     
    }
}
public static void main(String args[]){
        op("count_xor_eqltok");
        
        
}
}
