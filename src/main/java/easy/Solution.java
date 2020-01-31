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

        System.out.println(solution.romanToInt("MCMXCIV"));

        String[] strings={"asdzxc","asdfqwe","asdrew"};
        System.out.println(solution.longestCommonPrefix(strings));
        System.out.println(strings[0].indexOf("asdfg"));

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

    /**
     * 13. 罗马数字转整数
     *
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     *
     *字符    I   V    X    L     C    D    M
     *
     *数值    1   5   10   50   100  500  1000
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
     * 例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
     * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     *
     * 示例 1:
     * 输入: "III"
     * 输出: 3
     *
     * 示例 2:
     * 输入: "IV"
     * 输出: 4
     *
     * 示例 3:
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<String,Integer> map=new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("IV",4);
        map.put("X",10);
        map.put("IX",9);
        map.put("L",50);
        map.put("XL",40);
        map.put("C",100);
        map.put("XC",90);
        map.put("D",500);
        map.put("CD",400);
        map.put("M",1000);
        map.put("CM",900);

        int ans=0;

        for (int i=0;i<s.length();i++){
            if (i+1<s.length()&&map.containsKey(s.substring(i,i+2))){
                ans+=map.get(s.substring(i,i+2));
                i++;
            }else {
                ans+=map.get(s.substring(i,i+1));
            }
        }

        if (ans<1||ans>3999)
            throw new IllegalArgumentException("Input mast between 1 and 3999!!!");

        return ans;
    }

    /**
     * 14. 最长公共前缀
     *
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 示例 1:
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     *
     * 示例 2:
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     *
     * 说明:
     * 所有输入只包含小写字母 a-z 。
     *
     * @param strs
     * @return
     */
    //算法一：水平扫描法
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }
    //算法二：水平扫描
    public String longestCommonPrefix1(String[] strs) {
        if (strs==null||strs.length == 0) return "";
        for (int i=0;i<strs[0].length();i++){
            char c=strs[0].charAt(i);
            for (int j=1;j<strs.length;j++){
                if (i==strs[j].length()||c!=strs[j].charAt(i))
                    return strs[0].substring(0,i);
            }
        }
        return strs[0];
    }
    //算法三：分治
    public String longestCommonPrefix3(String[] strs){
        if (strs==null||strs.length == 0) return "";
        return longestCommonPrefix3(strs,0,strs.length-1);
    }
    private String longestCommonPrefix3(String[] strs,int x,int y){
        if (x==y)
            return strs[x];
        int min=(x+y)/2;
        String left=longestCommonPrefix3(strs,x,min);
        String right=longestCommonPrefix3(strs,min+1,y);
        return longestCommonPrefix3(left,right);
    }
    private String longestCommonPrefix3(String left,String right){
        int min=Math.min(left.length(),right.length());
        for (int i=0;i<min;i++){
            if (left.charAt(i)!=right.charAt(i))
                return left.substring(0,i);
        }
        return left.substring(0,min);
    }


}
