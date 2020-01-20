package findMedianSortedArrays;

import java.util.*;

public class XXX {
    public static void main(String[] args) {
        int[] num1={1};
        int[] num2={-1,-2};

        XXX xxx=new XXX();
        System.out.println(xxx.findMedianSortedArrays(num1,num2));

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        List<Integer> list=new ArrayList<>();

        for (int i:nums1){
            list.add(i);
        }
        for (int i:nums2){
            list.add(i);
        }

//        System.out.println(set.toString());

        int len=list.size();
        Integer[] array=new Integer[len];
        list.toArray(array);
        Arrays.sort(array);


        int med=len/2;
        int judge=len%2;
//        System.out.println("med:"+med);
//        System.out.println("judge:"+judge);
        double ret;
        if (judge==0){
//            System.out.println(array[med-1]);
//            System.out.println(array[med]);
            ret=(double) (array[med-1]+array[med])/2;
        }else {
//            System.out.println(array[med]);
            ret=array[med];
        }

        return ret;

    }


}
