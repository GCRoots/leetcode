package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shipengfei
 * @data 2020/1/30
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution=new Solution();

        int[] nums ={2, 7, 11, 15};
        int target = 9;
        System.out.println(solution.twoSum(nums,target)[1]);

        System.out.println(-120+"\t"+solution.reverse(-120));

        System.out.println(solution.isPalindrome(121));

    }

    /**
     * 1. 两数之和
     *
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums   给定整数数组
     * @param target 目标值
     * @return
     */
    //两遍for循环 暴力方法
    public int[] twoSum(int[] nums, int target) {
        for (int i=0;i<nums.length-1;i++){
            for (int j=i+1;j<nums.length;j++){
                if (nums[i]+nums[j]==target)
                    return new int[] {i,j};
            }
        }
        throw new IllegalArgumentException("No two sum solution");

    }
    //两遍哈希表
    public int[] twoSum1(int[] nums, int target){
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++)
            map.put(nums[i],i);

        for (int i=0;i<nums.length;i++){
            int num=target-nums[i];
            if (map.containsKey(num)&&map.get(num)!=i)
                return new int[] {map.get(num),i};
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    //一遍哈希表
    public int[] twoSum2(int[] nums, int target){
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){

            int num=target-nums[i];
            if (map.containsKey(num)&&map.get(num)!=i)
                return new int[] {map.get(num),i};

            map.put(nums[i],i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 7. 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * 示例 1:
     *   输入: 123
     *   输出: 321
     *
     * 示例 2:
     *   输入: -123
     *   输出: -321
     *
     * 注意:
     *   假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
     *   请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int curr=0;
        while (x!=0){
            int pop=x%10;
            x/=10;
            if (curr>Integer.MAX_VALUE/10 || (curr == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (curr < Integer.MIN_VALUE/10 || (curr == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            curr = curr * 10 + pop;
        }

        return curr;
    }

    /**
     * 9. 回文数
     *
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 示例 1:
     * 输入: 121
     * 输出: true
     *
     * 示例 2:
     * 输入: -121
     * 输出: false
     *
     * 示例 3:
     * 输入: 10
     * 输出: false
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x<0)
            return false;

        int temp=0;
        int xx=x;
        while (xx!=0){
            temp=temp*10+xx%10;
            xx/=10;
        }
        return temp==x;
    }

    public int romanToInt(String s) {
        int res=0;

        return res;
    }

}
