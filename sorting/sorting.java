package DSASheet.sorting;
// public class sorting{
//     public static void main (String args[]){
//         void mergesort(arr,l,h){
//             if (l>=h) return 
//             m = (l+h)/2;
//             mergesort(arr,l,m);
//             mergesort(arr,m+1,h);
//             con(arr,l,m,h);
//         };
//         con(arr,l,m,h){
//             int i = l;
//             int j = h;
//             int arr1 = new int [];
//             while (i<m && j>h){
//                 if (arr[i]> arr[j]){
//                     arr1.add(arr1[j]);
//                     --j;
//                 }
//                 else{
//                     arr1.add(arr[i]);
//                     --i;

//                 }
//             }
//             while (i<=m ){
                
//                     arr1.add(arr[i]);
//                     --i;
                
//             }
//             while (j>=h){
                
//                     arr1.add(arr[j]);
//                     --j;
                
//             }
//             for (i=0;i<arr1.length;i++){
//                 arr[i] = arr1[l-i];
//             }

//         };

//         int arr = {1,2,3,12,3};
//         mergesort(arr,0,arr.length -1 );
//         System.out.println(arr);
//     }
// }


import java.util.Arrays;

public class sorting {

    public static void mergesort(int[] arr, int l, int h) {
        if (l >= h) return;

        int m = (l + h) / 2;
        mergesort(arr, l, m);
        mergesort(arr, m + 1, h);
        merge(arr, l, m, h);
    }

    public static void merge(int[] arr, int l, int m, int h) {
        int[] temp = new int[h - l + 1];
        int i = l;
        int j = m + 1;
        int k = 0;

        while (i <= m && j <= h) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= m) {
            temp[k++] = arr[i++];
        }

        while (j <= h) {
            temp[k++] = arr[j++];
        }

        // Copy back to original array
        for (int x = 0; x < temp.length; x++) {
            arr[l + x] = temp[x];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 12, 3};
        mergesort(arr, 0, arr.length - 1);

        // Print the sorted array
        System.out.println(Arrays.toString(arr));
    }
}
