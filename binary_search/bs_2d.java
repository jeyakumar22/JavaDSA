package DSASheet.binary_search;

import java.util.*;

// import DSASheet.array.array_easy;

public class bs_2d {
    
    public static int max_1s_in_a_row (int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int max_count = -1;  // if o then the row eith no 1 return index if no 1s return -1 so count>max not update
        int index = 0;
        for (int i = 0;i<n ; i++){
            int count_1s = 0;
            for (int j = 0;j<m ; j++){
                count_1s+=matrix[i][j];
            }
            if (count_1s>max_count) {
                max_count=count_1s;
                index=i;
            }
        }
        return index;

    }
    
    public static int max_1s_in_a_row_bs (int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int index=0;
        int max_count =-1;
        for (int i =0;i<n;i++){
            int count_1s = m - bs_easy.bs_low_bound (matrix[i],1); // we can use lower bound of one 
            // upperbound of 0 
            // firstoccurance to count 1s in sorted row matrix
            //all in 1d array
            if (count_1s>max_count) {
                max_count=count_1s;
                index = i;
            }
        }
        return index;

    }
    
    public static int[] search_in_matrix (int[][] matrix,int target){
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0;i<n ; i++){
            for (int j = 0;j<m ; j++){
                if (matrix[i][j]==target) {
                    return new int[]{i,j};
            }
            } 
            
        }
        return new int[] {-1,-1} ;

    }
    
    public static int[] search_in_matrix_bs (int[][] matrix,int target){
        int n = matrix.length;
        int m = matrix[0].length;
        int index = -1;
         for (int i=0;i<n;i++){
            if(matrix[i][0]<=target && target<=matrix[i][m-1]){
                    index =bs_easy.binary_search(matrix[i],target); // use == in lowerbound >= if value not in it return carefull
                    if(index!=-1){
                        return new int[]{i,index};
                    }
            }
        }
        return new int[]{-1,-1};
    }
    
    public static boolean search_in_matrix_bs_opti(int[][] matrix,int target){
        int n = matrix.length;
        int m = matrix[0].length;
        int low =0;
        int high =(n*m-1);
        while(low<=high){
            int mid = (low+high)/2;
            int row =mid/m; 
            int col =mid%m; 
            if (matrix[row][col] == target){
                return true;
            }
            else if(matrix[row][col]>target){
                high =mid-1;
            }
            else{
                low=mid+1;
            }
        }
        return false;
    }
    
    public static int[] search_in_matrix_II(int[][] matrix,int target){
        int n = matrix.length;
        int m = matrix[0].length;
        //st from right top corner to cancel row or col by condition check
        int row =0;
        int col = m-1;
        while(row<n && col>=0){
            
            if (matrix[row][col] == target){
                return new int[]{row,col};
            }
            else if(matrix[row][col]>target){
                col--;
            }
            else{
                row++;
            }      
        }
        return new int[]{-1,-1};
    }

    public static int maxi_elt_mat(int[][] matrix,int n,int m,int col){
        int index =-1;
        int max_elt =-1;
        for (int i = 0;i<n; i++){
                if (matrix[i][col]>max_elt){
                    max_elt = matrix[i][col];
                    index=i;
                }
            }
            return index;
    }
    
    public static int[] find_max_in_mat(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int low =0;
        int high = m-1;
        while(low<=high){
            int mid = (low+high)/2;
            int col = maxi_elt_mat(matrix,n,m,mid); //max elt in col for top and bottom cheak
            int left = mid-1>=0 ?matrix[col][mid-1] :-1;
            int right = mid+1<m ?matrix[col][mid+1] :-1;
            if (matrix[col][mid]>left && matrix[col][mid]>right){
                return new int[]{col,mid};
            }
            else if(matrix[col][mid]<left){
                high=mid-1;
            }
            else{
                low = mid+1;
            }      
        }
        return new int[]{-1,-1};
    }
    
    public static int median_2d (int[][] matrix){
        List <Integer> arr = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0;i<n ; i++){
            for (int j = 0;j<m ; j++){
                arr.add(matrix[i][j]);
            } 
        }
        Collections.sort(arr);
        return arr.get((n*m)/2);
    }
    

     public static int black_box(int[][] matrix,int x){
            int count =0;
            for (int i = 0;i<matrix.length ; i++){
                count+=bs_easy.bs_upper_bound(matrix[i], x);
             }
             return count;
     }
     public static int median_2d_bs(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
            int low =Integer.MAX_VALUE;
            int high =Integer.MIN_VALUE;
        for (int i = 0;i<n ; i++){
            low = Math.min(matrix[i][0], low); //min of first in each row
            high = Math.max(matrix[i][m-1], high);//max of last in each row
        }
        int req = (n*m)/2;
        while(low<=high){
            int mid = (low+high)/2;
            int small_equal_elt = black_box(matrix, mid) ;
            if (small_equal_elt<=req){
                low=mid+1;
            }
            else {
                high =mid-1;
            }
        }
        return low;
    }

    public static void op(String answer){
    Scanner jk = new Scanner(System.in);
    int[][] matrix = {{0,1,1,1},{1,1,1,1},{0,0,0,1}};
    int[][] mat = {
    {4,2,0,1,4,5},
    {2,9,3,2,3,2},
    {1,7,8,9,1,3},
    {3,6,2,3,7,2}
};

    System.out.println("enter the value for n");
    int n1 = jk.nextInt();
    System.out.println("enter the value for m");
    int n2 = jk.nextInt();
    int[][] matrix1 = new int[n1][n2];
    for (int i=0;i<n1;i++){
        for (int j=0;j<n2;j++){
            System.out.println("enter the value ");
            matrix1[i][j] = jk.nextInt();
        }
    }
    // System.err.println("enter m value:");
    // int m = jk.nextInt();
    // System.err.println("enter k value:");
    // int k = jk.nextInt();
    
    switch(answer){
        case "max_1s_in_a_row":{  
            //int ans= max_1s_in_a_row(matrix); // brute using to travers all 
            int ans1= max_1s_in_a_row_bs(matrix); // bs use to find max one intuition in note 
            //o(n*log2m)
            System.out.println(ans1);
            break;
        }
        case "search_in_matrix_I":{  
            System.err.println("enter target value:");
            int target = jk.nextInt();
            //int[] ans= search_in_matrix(matrix1,target); // brute using to travers all but t.c o(n*m)
            //int[] ans1= search_in_matrix_bs(matrix1,target); //better using bs for pass each row as arr
            boolean ans2= search_in_matrix_bs_opti(matrix1,target); //opti using 2d to 1d matrix solved o(log2m )
            System.out.println(ans2);
            break;
        }
        case "search_in_matrix_II":{  
            //this matrix differ from prev its col and row wise individually sorted not like matrix sort echeck notes
            System.err.println("enter target value:");
            int target = jk.nextInt();
            //brute using all traversal 2 loop o(n*m)
            // better also done using one loop and binarysearch O(n*log2m) but it sol diff from prev better need each row traverse
            //brute better already done in previouse version
            int[] ans = search_in_matrix_II(mat,target);
            System.out.println(Arrays.toString(ans));
            break;
        }
        case "find_max_in_mat":{  
            //brute traverse each and check 4 counter elt but is too time complexity N*M *4
            //better solution return the largest elt is ans n*m
            //use bs to skip elt computation opti
            int ans[] = find_max_in_mat(mat);
            System.out.println(Arrays.toString(ans));
            break;
        }
        case "median_2d":{  
            //brute using extra space but it took more time n*m*n*log(n*m)
            int[][] ip = {
                {1,5,7,11,11},
                {2,3,4,5,10},
                {8,10,12,14,16}
            };
            //int ans = median_2d(ip);
            //use bs optimal 
            int ans1 = median_2d_bs(ip);
            System.out.println(ans1);
            break;
        }
    }
    jk.close();
}

public static void main(String args[]){
        op("median_2d");
}
}
