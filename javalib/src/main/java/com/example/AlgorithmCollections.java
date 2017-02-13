package com.example;

/**
 * Created by ZCM on 2017/2/9.
 */

public class AlgorithmCollections {
    /**
     * 二分查找
     * @param array
     * @param des
     * @return
     */
    public static int binarySearch(int array[],int des){
        int low=0;
        int high=array.length-1;
        while (low<=high){
            int middle=(low+high)/2;
            if (array[middle]==des){
                return array[middle];
            }else if (des<array[middle]){
                high=middle-1;
            }else {
                low=middle+1;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        System.out.println(binarySearch(new int[]{1,3,5,7,9},7));
        new StaticMethod().VarintTest();
        new StaticMethod().VarintTest();
    }
}
