package DSASheet.Hashmap;
import java.util.*;
public class map {
    public static void occurance_hash(int[] arr){
        int[] hash =new int[6]; //max value + 1(0 index so)
        for (int i=0 ; i<arr.length ; i++){
            hash[arr[i]]+=1;
        }
        System.out.println(hash[5]);//only we can ger occuranve of first 5 more value will throw index err
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
    public static void min_max_occ(int[] arr){
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
        int maxelt = arr[1];
        int minelt = arr[1];
        int maxcount = 0;
        int mincount = Integer.MAX_VALUE; //care ful to handle
        for (Map.Entry<Integer,Integer> entry_map : map.entrySet()){
           int count = entry_map.getValue();
           int elt = entry_map.getKey();
           if (count > maxcount){
            maxcount = count;
            maxelt = elt;
           }
           if (count < mincount){
            mincount = count;
            minelt = elt;
           }
        }
        System.out.println("maxcountelement is " + maxelt + " min count element is " + minelt);
    }
    public static void main(String args[]){
        int[] arr = {1,2,3,1,3,2,4,5};
        //occurance_hash(arr);
        //hashmap(arr);
        min_max_occ(arr);
    }
}
