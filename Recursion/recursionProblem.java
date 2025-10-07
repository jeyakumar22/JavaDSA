package DSASheet.Recursion;

import java.util.*;

public class recursionProblem {
    //very imp thing i learned remove (i) remove besed on index as well as value so be carfull of removeing value use valueOf(arr[i])
    //veery critical err ds.remove(arr[ind]); here we need to remove last so removeLast
    public void allSubSequence(int[] arr,int ind,List<Integer>ds,int n){
        if (ind>=n){
            System.out.println(ds.toString());
            return ;
        }

        //done decending 

        // ds.add(arr[ind]);
        // allSubSequence(arr, ind+1, ds, n);
        // ds.removeLast();
        // allSubSequence(arr, ind+1, ds, n);

        //todo nontake first
        
        //not take value
        allSubSequence(arr, ind+1, ds, n);
        ds.add(arr[ind]);
        //take value
        allSubSequence(arr, ind+1, ds, n);
        ds.removeLast();
        
    }
    public void print_ss_eequals_k(int k,int sum ,int[] arr,int ind,List<Integer>ds,int n){
        if (ind==n){
            if (sum==k){
                System.out.println(ds.toString());
            }
            return ;
        }

        //done decending 

        // ds.add(arr[ind]);
        // allSubSequence(arr, ind+1, ds, n);
        // ds.removeLast();
        // allSubSequence(arr, ind+1, ds, n);

        //todo nontake first
        
        //not take value
        print_ss_eequals_k(k,sum,arr, ind+1, ds, n);
        ds.add(arr[ind]);
        sum+=arr[ind];
        //take value
        print_ss_eequals_k(k,sum,arr, ind+1, ds, n);
        ds.removeLast();
        sum-=arr[ind];
    }
    public boolean print_ss_eequals_k_oneAnsOnly(int k,int sum ,int[] arr,int ind,List<Integer>ds,int n){
        if (ind==n){
            if (sum==k){
                System.out.println(ds.toString());
                return true;
            }
            return false ;
        }

        //done decending 
        ds.add(arr[ind]);
        sum+=arr[ind];
        //take value
        if(print_ss_eequals_k_oneAnsOnly(k,sum,arr, ind+1, ds, n) == true){
                return true;
        }
        ds.removeLast();
        sum-=arr[ind];
        if(print_ss_eequals_k_oneAnsOnly(k,sum,arr, ind+1, ds, n) == true){
                return true;
        }
        return false; //for
       
    }
    public int count_ss_equalto_k(int k,int sum ,int[] arr,int ind,int n){
        if (k<sum) return 0;
        if (ind==n){
            if (sum==k){
                return 1;
            }
            return 0 ;
        }
        
        sum+=arr[ind];
        //take value
        int l=count_ss_equalto_k(k,sum,arr, ind+1,  n);
        
        sum-=arr[ind];
        int r=count_ss_equalto_k(k,sum,arr, ind+1,n);
        
        return l+r;
       
    }
    public void combinationSum(int t , int ind ,int[] arr, List<List<Integer>> ans ,List<Integer> ds){
        if (ind>=arr.length){
            if (t == 0) ans.add(new ArrayList<>(ds));
            //we can just print using ds only like before but ps platform ask list of list ans
            //System.out.println(ds.toString());
            return ;
        }

        //done decending 
        if (arr[ind] <=t){
            ds.add(arr[ind]);
            combinationSum(t-arr[ind],ind,arr,ans,ds );
            ds.removeLast();
        }
        combinationSum(t,ind+1,arr,ans,ds);
        //exp in note unlimited same elt pick so condition use for pic and no inc for non pic only condition false
        
    }

    public void combinationSum2_brute(int t , int ind ,int[] arr, Set<List<Integer>> ans ,List<Integer> ds){
        if (ind>=arr.length){
            if (t == 0) ans.add(new ArrayList<>(ds));
            return ;
        }

        //done decending 
        if (arr[ind] <=t){
            ds.add(arr[ind]);
            combinationSum2_brute(t-arr[ind],ind+1,arr,ans,ds );
            ds.removeLast();
        }
        combinationSum2_brute(t,ind+1,arr,ans,ds);
        //exp in note unlimited same elt pick so condition use for pic and no inc for non pic only condition false
        
    }
    public void combinationSum2(int t , int ind ,int[] arr, List<List<Integer>> ans ,List<Integer> ds){
        
            if (t == 0) {
            ans.add(new ArrayList<>(ds));
            return ;
            }
            for (int i=ind;i<arr.length;i++){
                
                if(i>ind && arr[i]==arr[i-1]) continue;
                if(arr[i]>t){
                    break;
                }
                ds.add(arr[i]);
                combinationSum2(t-arr[i],i+1,arr,ans,ds);
                ds.removeLast();
                //ds.remove(ds.size()-1);
                
            }
    }
    

    public void subSetSum(int[] arr,int sum ,int ind,List<Integer> ds ){
        
        if(arr.length==ind){
            ds.add(sum);
            return ;
        }
        subSetSum(arr, sum+arr[ind],ind+1,  ds);
        subSetSum(arr,  sum, ind+1,ds);
        }
    public void subSetSum2(int[] arr,int ind,List<Integer> ds,List<List<Integer>>ans ){
            ans.add(new ArrayList<>(ds));
            for(int i=ind;i<arr.length;i++){
                if (i>ind && arr[i]==arr[i-1]) continue;
                ds.add(arr[i]);
                subSetSum2(arr,i+1,ds,ans);
                ds.remove(ds.size() - 1); 
        }
    }

        
public static void op(String answer){
    Scanner jk = new Scanner(System.in);
    System.out.println("enter the value for array range");
    int n = jk.nextInt();
    int[] arr = new int[n];
    for (int i=0;i<n;i++){
        System.out.println("enter the element "+i);
        arr[i] = jk.nextInt();
    }
    // System.err.println("enter k value:");
    // int k = jk.nextInt();
    int len = arr.length;

    recursionProblem need = new recursionProblem();
    List<Integer> ds = new ArrayList<>();
    switch(answer){
        case "print_all_subsequence":{
            
            
            need.allSubSequence(arr,0,ds,arr.length);
            
            break;
        }
        case "print_ss_eequals_k":{
            System.err.println("enter k value:");
            int k = jk.nextInt();
            
            
            need.print_ss_eequals_k(k,0,arr,0,ds,arr.length);
            //only diff is sum comparision than before prlm
            
            break;
        }
        case "print_ss_eequals_k_oneAnsOnly":{
            System.err.println("enter k value:");
            int k = jk.nextInt();
            
            
            need.print_ss_eequals_k_oneAnsOnly(k,0,arr,0,ds,arr.length);
            //use to learn pattern to return one ans only not specified then it reduce fn call
            //edge explained in note
            
            break;
        }
        case "count_ss_equalto_k":{
            //no need ds only count no need ss
            System.err.println("enter k value:");
            int k = jk.nextInt();
            
            
            int ans=need.count_ss_equalto_k(k,0,arr,0,arr.length);
            System.out.println("ans: "+ans);
            //use to learn pattern to return one ans only not specified then it reduce fn call
            //edge explained in note
            
            break;
        }
        case "combinationSum":{
            //no need ds only count no need ss
            System.err.println("enter k value:");
            int target = jk.nextInt();
            
            
            List<List<Integer>> ans = new ArrayList<>();
            
            need.combinationSum(target,0,arr,ans,ds);
            System.out.println("ans: "+ans);
            //use to learn pattern to return one ans only not specified then it reduce fn call
            //edge explained in note
            
            break;
        }
        case "combinationSum2":{
            //the problem states that find combinations one time only take elt and no duplicate ans allowed
            //no need ds only count no need ss
            //only change is pick once not unlimited and store in set
            
            System.err.println("enter k value:");
            int target = jk.nextInt();
            
            
            Arrays.sort(arr);
            
            Set<List<Integer>> ans =new HashSet<>();
            List<List<Integer>> ans1 =new ArrayList<>();
            
            
            //need.combinationSum2_brute(target,0,arr,ans,ds);
            
            need.combinationSum2(target,0,arr,ans1,ds);
            
            System.out.println("ans: "+ans1);
            //use to learn pattern to return one ans only not specified then it reduce fn call
            //edge explained in note
            
            break;
        }
        case "subSetSum":{
            
            need.subSetSum(arr,0,0,ds);
            Collections.sort(ds);
            System.out.println(ds);
            break;
        }
        case "subSetSum2":{
            List<List<Integer>> ans =new ArrayList<>();
            Arrays.sort(arr);
            need.subSetSum2(arr,0,ds,ans);
            System.out.println(ans);
            break;
        }
    }
    jk.close();
}
public static void main(String args[]){
        op("subSetSum2");
}
}
