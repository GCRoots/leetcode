package com.example.leetcode.singleNumber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SingleNumber {

    public static void main(String[] args) {

        SingleNumber singleNumber=new SingleNumber();
        int[] nums={1,2,3,4,5,6,6,5,4,3,1};
        int singleMap=singleNumber.singleNumber_Map(nums);
        System.out.println(singleMap);
        int singleSet=singleNumber.singleNumber_Set(nums);
        System.out.println(singleSet);
    }


    public int singleNumber_Map(int[] nums){
        Map<Integer,Integer> map=new HashMap();

        for (int num:nums){
            if (!map.containsKey(num)){
                map.put(num,1);
//                System.out.println(map.toString());
            }else {
//                map.put(num,2);
                map.remove(num);
//                System.out.println(map.toString());
            }
        }
//        System.out.println(map.toString());

        Object[] n=map.keySet().toArray();
//        System.out.println(map.keySet());
        return (int)n[0];
    }

    public int singleNumber_Set(int[] nums){
        Set set=new HashSet();
        int count=0;
        int doubles=0;
        for (int num:nums){
            if (!set.contains(num)){
                count+=num;
//                System.out.println("count:"+count);
                set.add(num);
            }else {
                doubles+=num;
//                System.out.println("doubles:"+doubles);
            }

        }
        return count-doubles;
    }



}
