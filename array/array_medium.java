package DSASheet.array;
import java.util.*;
public class array_medium {

public static int maxi(int[] arr){
    int m = arr[0];
    for (int i=0;i<arr.length;i++){
        if (arr[i]>m){
            m=arr[i];
        }
    }
    return m;
}

public static int maximum( int a,int b){
        
        if (a>b){
            return a;
        }
        else{
            return b;
        }
        
    }

public static int twosum(int[] arr ,int k){ // brute forcce o(n^2)
    int n = arr.length;
    for (int i=0;i<n;i++){
        for( int j = i+1; j<n;j++){
            if (arr[i] + arr[j] == k){
                 System.out.println("two sum at " + i +" "+j+ " values " + arr[i] + " " +arr[j] );
                 return 0;
            }
        }
    }
    return -1;
}

//can solve using hashing by map like prefix sum as target - num = 2nd num 
public static int twosum_unorder(int[] arr ,int k){
    int n = arr.length;
    Map <Integer,Integer> mp = new HashMap<>();
    
   for (int i = 0;i<n ; i++){
        int rem = k-arr[i];
        
        if (mp.containsKey(rem)){
            System.out.println(i);
            System.out.println(mp.get(rem));
            return 1;
        }
        mp.put(arr[i],i);

            }
    return -1;
}
// two pointer using only for sorted arr 
public static String twosum_pointer(int[] arr ,int k){ // brute forcce o(n^2)
    int n = arr.length;
    int l=0,r=n-1;
    while(r>l){
        int sum = arr[l] + arr[r];
        if (sum == k) return "two sum at " + l +" "+r+ " values " + arr[l] + " " +arr[r];
        else if (sum > k){
            r--;
        }
        else if (sum <k) l++; 
    }
            
    return " ";
}

public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

public static void sort012(int[] arr ){ // optimal o(n)
    int n = arr.length;
    int i=0,k=0,j=n-1;
    while(k<=j){
        
        if (arr[k] == 0) {
            swap(arr,i,k);
            k++;
            i++;
        }
        else if ( arr[k]==1){
            k++;
        }
        else if (arr[k]==2) {
            swap(arr,j,k);
            j--;
            
        } 
    }
            
    System.out.println(Arrays.toString(arr));
}

public static void majarity(int[] arr){ // it is a better solution o(n) s.c o(n) but there is aoptimal aproch by moores voting algo 
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
            if (entry_map.getValue() > (n/2)){
                System.out.println(entry_map.getKey()+" "+ entry_map.getValue()+ " Time");
            }
        }
    }

public static void majarity_optimal(int[] arr){ // it is a better solution o(n) s.c o(n) but there is aoptimal aproch by moores voting algo 
        int n = arr.length;
        int c = 0 , elt = 0;
        for (int i=0; i<n; i++){
           if (c == 0){
            elt = arr[i];
            c++;
           }
           else if (elt == arr[i]){
            c++;
           }
           else{
            c--;
           }
        }
        //"remain elt if it > n/2 arr has major as elt else no check by count (remain elt )>n/2  :" 
        System.out.println(elt);
        int c1 =0;
        for (int i=0;i <n;i++){
            if (elt == arr[i]){
              c1++  ;
            }
            if (c1 > (n/2)){
                System.out.println("majority elt "+ elt +" occured " +c1+" time");
            }
        }
    }

public static void max_subarray_sum(int[] arr){ 
        int n = arr.length;
        int m = Integer.MIN_VALUE;
        for (int i=0; i<n; i++){
           int sum=0;
           for (int j=i; j<n; j++){
                sum +=arr[j];
                m=Math.max(sum,m);
           }
        }
        System.out.println(m);
    }

public static void max_subarray_sum_kadance(int[] arr){
        int n = arr.length;
        int m = Integer.MIN_VALUE;
        int sum=0;
        int ansstart =-1,ansend =-1;
        int start=0;
        for (int i=0; i<n; i++){
           
        sum += arr[i];
        if (sum == 0){
            start = i;
        }
        if (sum > m){
            m=sum ;
            ansstart =start;
            ansend = i;
            
        }
        if (sum < 0){
            sum =0;
        }
        if(m<0){
            m=0;
            //empty array
        }
           
        }
        System.out.println(m + " start to end index of max sum sub array "+ ansstart +" " + ansend);
        System.out.println("the array");
        for (int i=ansstart; i<=ansend; i++){
            System.out.print(arr[i]+ " ");

        }
    }

public static void stock_buy_sell(int[] arr){ // it is a brute solution o(n^2)  but there is aoptimal aproch 
        int m = 0;
        int n = arr.length;
        for (int i=0; i<n; i++){
           for (int j=i; j<n; j++){
              if (arr[j] > arr[i] )  {
                m = Math.max(arr[j] - arr[i], m);
              }
           }
        }
        System.out.println(m);
    }

public static void stock_buy_sell_opti(int[] arr){ 
        int min_price = Integer.MAX_VALUE;
        int max_profit = 0;
        int n = arr.length;
        
        for (int i=0; i<n; i++){
        //    if (arr[i] < min_price){
        //     min_price = arr[i];
        //    }
        //    else{
        //     max_profit = maximum(max_profit, arr[i]-min_price);
        //    }
        //    //or
           min_price = Math.min(min_price, arr[i]);
           max_profit= maximum(max_profit,arr[i]-min_price);

        }
        
        System.out.println(max_profit);
    }

public static void stock_buy_sell_dp(int[] arr){ 
        int min_price = arr[0];
        int max_profit = 0;
        int n = arr.length;
        
        for (int i=1; i<n; i++){
            
           max_profit= maximum(max_profit,arr[i]-min_price);
           min_price = Math.min(min_price, arr[i]);
        }
        
        System.out.println(max_profit);
    }

public static void leader_arr(int[] arr){  // o(n^2) brute
        int n = arr.length;
        for (int i=0; i<n; i++){
            boolean a = true;
            for (int j=i+1; j<n; j++){
              if (arr[j] > arr[i] )  {
                a=false;
                break;
              }
            
           }
           if (a==true){
                System.out.print(arr[i] + " " );
            }
        }
        
    }

public static void leader_arr_opti(int[] arr){  // o(n^2) brute
        int n = arr.length;
        int max = arr[n-1];
        ArrayList<Integer> a = new ArrayList<>();
        a.add(max);
        for (int i=n-2; i>=0; i--){
            if (arr[i]> max){
                max=arr[i];
                a.add(max);
            }
        }
        System.out.print(a);
        for (int i = a.size() - 1; i >= 0; i--) {
            System.out.print(a.get(i) + " ");
        }
        
    }

public static void longest_continuous_subarray(int[] arr){  //  brute
        int n = arr.length;
        int largest = 1;
        for (int i=0; i<n; i++){
            int x = arr[i];
            int c =1;
            while(ls(arr,x+1) == true){
                x+=1;
                c+=1;
            }
            largest= maximum(largest,c);
            
        }
        System.out.println(largest);
        
        
    }

public static boolean ls(int[] arr,int a){
            for (int i = 0 ; i<arr.length;i++){
                if (arr[i] == a){
                    return true;
                }
            }
            return false;
        }

public static void longest_continuous_subarray_sort(int[] arr){  //  better
        int n = arr.length;
        int c = 0;
        int lastsmall = Integer.MIN_VALUE;
        int largest = 1;
        Arrays.sort(arr);
        for (int i=0; i<n; i++){
            if (arr[i]-1 == lastsmall){
                c+=1;
                lastsmall= arr[i];
            }
            else if (arr[i] != lastsmall){
                c=0;
                lastsmall = arr[i];
            }
            largest=maximum(largest,c);
        }
        System.out.println(largest);
      
        
    }

public static void longest_continuous_subarray_set(int[] arr){
        int n = arr.length;
        int c = 0;
        //int lastsmall = Integer.MIN_VALUE;
        int largest = 1;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i<n; i++){
            set.add(arr[i]);
        }
        for (int it :set){
            if (!set.contains(it - 1)){
                int x=it;
                c=1;
                while(set.contains(x+1)){
                    c+=1;
                    x+=1;

                }
            largest = maximum(c, largest);
            }
        }
        System.out.println(largest);
}

public static ArrayList<ArrayList<Integer>> setmatrix_0(ArrayList<ArrayList<Integer>> m ,int x,int y){
        int c0=1;
        for (int i = 0; i<x; i++){
            for (int j = 0; j<y; j++){
            
                    if (m.get(i).get(j) == 0){
                        m.get(i).set(0,0);
                        if (j!=0){
                            m.get(0).set(j,0);
                        }
                        else{
                            c0 = 0;
                        }
                    }
        }
    }
        for (int i = 1; i<x; i++){
            for (int j = 1; j<y; j++){
            
                    if (m.get(i).get(j) != 0){
                        if(m.get(i).get(0)==0 || m.get(0).get(j)==0){
                            m.get(i).set(j,0);
                        }
                    }
        }
    }
    if (m.get(0).get(0) == 0){
        for (int j=0;j<y;j++){
                m.get(0).set(j,0);
        }
    }
    if (c0 == 0){
        for (int i=0;i<x;i++){
                m.get(i).set(0,0);
        }
    }

    return m;
}
    
public static int[][] rotate90(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i<n-1; i++){
           for (int j=i+1;j<m;j++){
                int temp = 0;
                temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
           }
        }
        for (int i = 0; i<n; i++){
           for (int j=0;j<m/2;j++){
                int temp = 0;
                temp = arr[i][j];
                arr[i][j] = arr[i][m-1-j];
                arr[i][m-1-j] = temp;
           }
        }
        return arr;
        
}

public static ArrayList<Integer> spiral(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;
        int t = 0,l = 0,r = m-1,b=n-1;
        ArrayList<Integer> ans = new ArrayList<>();
        while(t<=b && l<=r){
             for (int i = l; i<=r; i++){
                ans.add(arr[t][i]);
             }
             t++;
             for (int i = t; i<=b; i++){
                ans.add(arr[i][r]);
             }
             r--;
             if(t<=b){
             for (int i = r; i>=l; i--){
                ans.add(arr[b][i]);
             }
             b--;
            }
            if (l<=r){
                for (int i = b; i>=t; i--){
                ans.add(arr[i][l]);
             }
             l++;
            }
             
        }
        return ans;
        
}

public static void subarray_count_k(int[] arr,int k){
        int n = arr.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        int prefix_sum=0;
        int c=0;
        map.put(0,1);
        for (int i = 0; i<n; i++){
            prefix_sum +=arr[i];
            int remove =prefix_sum-k;
            if (map.containsKey(remove)){
                c+=map.getOrDefault(remove,0);
            }
            map.put(prefix_sum,map.getOrDefault(prefix_sum,0)+1);
        }
        System.out.println(c);
             
}

public static void all_permutation(int[] arr, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> ds,
        boolean[] map)   {
        int n = arr.length;
        if (ds.size() ==n){
            ans.add(new ArrayList<>(ds));
            return ;
        }
        for (int i= 0;i<n;i++){
            if (!map[i]){
                map[i] = true;
                ds.add(arr[i]);
                all_permutation(arr, ans, ds, map);
                ds.remove(ds.size() - 1);
                map[i] = false;

            }
        }
             
}

public static void all_permutation_1(int index , int[] arr,ArrayList<ArrayList<Integer>>  answer)   {
        int n = arr.length;
        if (index ==n){
            // ArrayList<Integer> jk =new ArrayList<>();
            // for (int i=0;i<n;i++){
            //     jk.add(arr[i]);
            // } 
            // answer.add(new ArrayList<>(jk));
            for (int i=0;i<n;i++){
                System.out.print(arr[i]);
            } 
            System.out.println();
            return ;
        }
        
        for (int i=index;i<n;i++){
            swap(arr, index, i);
            all_permutation_1(index+1 , arr , answer);
            swap(arr, index, i);
        }
        
}

public static void next_permutation(int[] arr){
    int n=arr.length;
    int index = -1;
    //find breakpoint 
    for(int i=n-2; i>=0;i--){
        if(arr[i]<arr[i+1]){
            index = i ;
            break;
        }
    }
    if (index == -1){
        array_easy.rev(arr,0,n-1);
        return;
    }
    //swap with min max to find next greater in permu
    for (int i = n-1; i>=index;i--){
        if (arr[i]>arr[index]){
            swap(arr, index, i);
            break;
        }
    }
    array_easy.rev(arr,index +1 , n-1);
    

}

public static void rearrange_elt(int[] arr){
    int n=arr.length;
    int[] pos = new int[n/2];
    int[] neg = new int[n/2];
    int j=0;
    int k=0;
    for(int i=0; i<n;i++){
        if (arr[i]<0){
            neg[j++]=arr[i];
        }
        else{
            pos[k++]=arr[i];
        }
    }
    System.out.println(Arrays.toString(pos));
    System.out.println(Arrays.toString(neg));
    int q=0,r=0;
    for (int i=0;i<n/2;i++){
        arr[2*i] = pos[q++];
        arr[2*i+1] = neg[r++];
    }
}

public static void rearrange_elt_opti(int[] arr){
    int n=arr.length;
    int j=0;
    int k=1;
    int [] ans = new int[n] ;
    for(int i=0; i<n;i++){
        if (arr[i] <0){
            ans[k]=arr[i];
            k+=2;
        }
        else{
            ans[j]=arr[i];
            j+=2;

        }
    }
   
}

public static void rearrange_elt_difflen(int[] arr){
    int n=arr.length;
    int[] pos = new int[n];
    int[] neg = new int[n];
    int j=0;
    int k=0;
    for(int i=0; i<n;i++){
        if (arr[i]>0){
            pos[j++]=arr[i];
        }
        else{
            neg[k++]=arr[i];
        }
    }
        if (j < k){
            for (int i = 0; i < j; i++){
                arr[2 * i] = pos[i];
                arr[2 * i + 1] = neg[i];
            }
            int ind = 2 * j;
            for (int i = j; i < k; i++){
                arr[ind++] = neg[i];
            }
        } else {
            for (int i = 0; i < k; i++){
                arr[2 * i] = pos[i];
                arr[2 * i + 1] = neg[i];
            }
            int ind = 2 * k;
            for (int i = k; i < j; i++){
                arr[ind++] = pos[i]; 
            }
        }
    
   
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
    //twosum(arr,k);
    twosum_unorder(arr,k);
    //System.out.println(twosum_pointer(arr, k));
    //sort012(arr);
    //majarity(arr);
    //majarity_optimal(arr);
    //max_subarray_sum(arr);
    //max_subarray_sum_kadance(arr);
    //stock_buy_sell_opti(arr);
    //stock_buy_sell_dp(arr);
    //leader_arr_opti(arr);
    //longest_continuous_subarray_sort(arr);
    //longest_continuous_subarray_set(arr);
    //subarray_count_k(arr,k);
    switch(answer){
        case "setmatrix0":{
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            matrix.add(new ArrayList<>(Arrays.asList(1,1,1,1)));
            matrix.add(new ArrayList<>(Arrays.asList(1,1,0,1)));
            matrix.add(new ArrayList<>(Arrays.asList(1,1,0,1)));
            int x = matrix.size();
            int y = matrix.get(0).size();
            ArrayList<ArrayList<Integer>> ans = setmatrix_0(matrix,x,y);

            for(ArrayList<Integer> row :ans){
                for (Integer i : row){
                    System.out.print(i + " ");
                    
                }
                System.out.println();
            }
        }
        case "rotate90":{
            int ar1[][] =  {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            rotate90(ar1);
            System.out.println("Rotated Image");
            for (int i = 0; i < ar1.length; i++) {
                for (int j = 0; j < ar1.length; j++) {
                    System.out.print(ar1[i][j] + " ");
                }
                System.out.println();
            }
        }
        case "spiral":{
            int ar1[][] =  {{1, 2, 3,4,5}, {6, 7,8,9,10}, {11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
            System.out.println("Spiraled");
            ArrayList<Integer> ans = spiral(ar1);
            for(int i = 0;i<ans.size();i++){
                System.out.print(ans.get(i) + " ");
            }
        }
        case "permu":{
            ArrayList<ArrayList<Integer>> ans =new ArrayList<>();
            ArrayList<Integer> ds =new ArrayList<>();
            boolean[] map = new boolean[arr.length];
            all_permutation(arr, ans, ds,map);
            // for (ArrayList<Integer> row :ans){
            //     for (Integer a : row){
            //     System.out.print(a);
            // }
            // System.out.println();}

            for (ArrayList<Integer> row :ans){
                System.out.println(row);
            }
            break;

        }
        case "permu1":{
            ArrayList<ArrayList<Integer>> ans =new ArrayList<>();
            all_permutation_1(0,arr,ans);
            for (ArrayList<Integer> row :ans){
                System.out.println(row);
            }
            break;

        }
        case "next_permutation":{
            next_permutation(arr);
            for (int x :arr){
                System.out.print(x);
            }
            break;
        }
        case "rearrange_elt":{
            rearrange_elt(arr);
            for (int x :arr){
                System.out.print(x + " ");
            }
            break;
        }
        case "rearrange_elt_opti":{
            rearrange_elt(arr);
            for (int x :arr){
                System.out.print(x + " ");
            }
            break;
        }
        case "rearrange_elt_difflen":{
            rearrange_elt_difflen(arr);
            for (int x :arr){
                System.out.print(x + " ");
            }
            break;
        }
    }
}
public static void main(String args[]){
        op("rearrange_elt_difflen");
}
}



// public static void stock_buy_sell_1(int[] arr){//i use 0 so not optimal wrong 
//         int m = Integer.MAX_VALUE;
//         int n = arr.length;
//         int start =0,sell=0;
//         for (int i=0; i<n; i++){
//            if (arr[i]<m){
//             m=arr[i];
//             start = i;
//            }
//         }
//         for (int i=start; i<n; i++){
//             if (arr[i] > m){
//                 sell = maximum(arr[i] - m ,sell);
//             }
//         }
//         System.out.println(sell);
//     }