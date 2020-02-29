package easy;

import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shipengfei
 * @data 2020/1/30
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution=new Solution();

        int[] nums ={2,2,2,7,7,11,12,14,14,15};
        int target = 9;
        System.out.println(Arrays.toString(solution.twoSum(nums, target)));

        System.out.println(-120+"\t"+solution.reverse(-120));

        System.out.println(solution.isPalindrome(121));

        System.out.println(solution.romanToInt("MCMXCIV"));

        String[] strings={"asdzxc","asdfqwe","asdrew"};
        System.out.println(solution.longestCommonPrefix4(strings));
        System.out.println(strings[0].indexOf("asdfg"));

        System.out.println(solution.isValid("))"));

        ListNode l1=new ListNode(1);
        l1.next=new ListNode(3);
        l1.next.next=new ListNode(5);
        ListNode l2=new ListNode(2);
        l2.next=new ListNode(4);
        l2.next.next=new ListNode(8);
        ListNode l=solution.mergeTwoLists(l1,l2);
        while (l!=null){
            System.out.print(l.val+" -> ");
            l=l.next;
        }
        System.out.println();

        int[] num={0,0,1,1,1,2,2,3,3,4};
        System.out.println(Arrays.toString(num));
        System.out.println(solution.removeDuplicates(num));
        System.out.println(Arrays.toString(num));

        int[] num1={0,0,1,1,1,2,2,3,3,4};
        System.out.println(Arrays.toString(num1));
        System.out.println(solution.removeElement(num1,2));
        System.out.println(Arrays.toString(num1));

        System.out.println(solution.strStr("",""));

        System.out.println(solution.searchInsert(new int[]{1, 2, 3, 4, 5},2));

        System.out.println(solution.countAndSay(3));

        System.out.println(solution.maxSubArray1(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

        System.out.println(solution.lengthOfLastWord("a"));

        System.out.println(Arrays.toString(solution.plusOne(new int[]{9,9,9})));

        System.out.println(solution.addBinary("11","1001"));

        System.out.println(solution.titleToNumber("AB"));

        System.out.println(solution.mySqrt(32));

        System.out.println(solution.climbStairs(5));

        int[] nums1={4,5,6,0,0,0};
        solution.merge1(nums1,3,new int[]{1,2,3},3);
        System.out.println(Arrays.toString(nums1));

        TreeNode min=new TreeNode(1);
        min.left=new TreeNode(2);
        System.out.println(solution.minDepth(min));

        System.out.println(solution.generate(5));

        System.out.println(solution.getRow(5));

        System.out.println(solution.maxProfit(new int[]{7, 6, 4, 3, 1}));

        System.out.println(solution.isPalindrome(".,"));

        System.out.println(solution.singleNumber(new int[]{1,1,2,2,3}));

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
    //算法四：二分查找法
    public String longestCommonPrefix4(String[] strs){
        if (strs==null||strs.length == 0) return "";
        int min=Integer.MAX_VALUE;
        for (String str:strs){
            min=Math.min(min,str.length());
        }
        int low=0;
        int high=min;
        while (low<=high){
            int mid=(low+high)/2;
            if (isCommonPrefix4(strs,mid))
                low=mid+1;
            else
                high=mid-1;
        }
        return strs[0].substring(0, high);
    }
    private boolean isCommonPrefix4(String[] strs,int length){
        String str=strs[0].substring(0,length);
        for (int i=0;i<strs.length;i++){
            if (!strs[i].startsWith(str))
                return false;
        }
        return true;
    }

    /**
     * 20. 有效的括号
     *
     * 改了好几次，次次都带有一些莫名其妙的错误，代码看起来繁琐无比
     *
     *给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     * 输入: "()"
     * 输出: true
     *
     * 示例 2:
     * 输入: "([)]"
     * 输出: false
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s==null || s.equals(""))
            return true;
        if (s.length()%2==1)
            return false;

        Stack<String> stack=new Stack<>();
        for (int i=0;i<s.length();i++){
            String str=s.substring(i, i + 1);
            if (str.equals("(") || str.equals("[") || str.equals("{"))
                stack.push(str);
            else{
                if (stack.isEmpty())
                    return false;
                String pop=stack.pop();
                System.out.println(pop);
                if (str.equals(")"))
                    if (!pop.equals("("))
                        return false;
                if (str.equals("]"))
                    if (!pop.equals("["))
                        return false;
                if (str.equals("}"))
                    if (!pop.equals("{"))
                        return false;
            }
        }
        return stack.isEmpty();
    }
    public boolean isValid1(String s){
        Map<Character,Character> map=new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

        Stack<Character> stack=new Stack<>();

        for (int i=0;i<s.length();i++){
            char ch=s.charAt(i);

            if (map.containsKey(ch)){
                char top=stack.isEmpty()?'#':stack.pop();
                if (map.get(ch)!=top)
                    return false;
            }else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 21. 合并两个有序链表
     *
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     *
     * 示例：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * @param l1
     * @param l2
     * @return
     */
    //迭代
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node=new ListNode(0);
        ListNode root=node;
        while (l1!=null&&l2!=null){
            if (l1.val<l2.val){
                node.next=new ListNode(l1.val);
                l1=l1.next;
            }else {
                node.next=new ListNode(l2.val);
                l2=l2.next;
            }
            node=node.next;
        }
        node.next=l1!=null?l1:l2;

        return root.next;
    }
    //递归
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1==null)
            return l2;
        else if (l2==null)
            return l1;
        else if (l1.val<=l2.val){
            l1.next=mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next=mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    /**
     * 26. 删除排序数组中的重复项
     *
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * 示例 1:
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @return
     */
    //嵌套循环法 慢，效率低
    public int removeDuplicates(int[] nums) {
        int num=nums.length;
        for (int i=0;i<num-1;i++){
            while (nums[i]==nums[i+1]&&i<num-1){
                for (int j=i+1;j<num-1;j++){
                    nums[j]=nums[j+1];
                }
                num--;
            }
        }
        return num;
    }
    //双指针法
    public int removeDuplicates1(int[] nums){
        if (nums.length==0) return 0;
        int i=0;
        for (int j=i+1;j<nums.length;j++){
            if (nums[i]!=nums[j]){
                i++;
                nums[i]=nums[j];
            }
        }
        return i+1;

    }

    /**
     * 27. 移除元素
     *
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 :
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * 注意这五个元素可为任意顺序。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length==0) return 0;
        int i=0;
        for (int j=i;j<nums.length;j++){
            if (nums[j]!=val){
                nums[i]=nums[j];
                i++;
            }
        }
        return i;
    }
    public int removeElement1(int[] nums, int val){
        int n=nums.length;
        int i=0;
        while (i<n){
            if (nums[i]==val){
                nums[i]=nums[n-1];
                n--;
            }else {
                i++;
            }
        }
        return n;
    }

    /**
     * 28. 实现 strStr()
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中
     * 找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * 示例 1:    输入: haystack = "hello", needle = "ll"
     *           输出: 2
     * 示例 2:    输入: haystack = "aaaaa", needle = "bba"
     *           输出: -1
     *
     * 说明:
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。
     * 这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
//        if (haystack.equals("") && needle.equals(""))
//            return 0;
        int j=0;
        int len=needle.length();
        while (j<haystack.length()-len+1){
            if (haystack.substring(j,j+len).equals(needle)){
                return j;
            }
            j++;
        }
        return -1;
    }

    /**
     * 35. 搜索插入位置
     *
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     *
     * 示例 1:    输入: [1,3,5,6], 5
     *           输出: 2
     * 示例 2:    输入: [1,3,5,6], 2
     *           输出: 1
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        for (int i=0;i<nums.length;i++){
            if (target<=nums[i])
                return i;
        }
        return nums.length;
    }

    /**
     * 38. 外观数列
     *
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 被读作  "one 1"  ("一个一") , 即 11。
     * 11 被读作 "two 1s" ("两个一"）, 即 21。
     * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
     *
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
     *
     * 注意：整数序列中的每一项将表示为一个字符串。
     *
     *  
     *
     * 示例 1:    输入: 1
     *           输出: "1"
     *           解释：这是一个基本样例。
     * 示例 2:    输入: 4
     *           输出: "1211"
     *           解释：当 n = 3 时，序列是 "21"，
     *           其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，
     *           也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。
     *           所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
     *
     * @return
     */
    public String countAndSay(int n) {
        String s="1";
        while (n>1){
            s=countAndSayHelper(s);
            n--;
        }
        return s;
    }
    public String countAndSayHelper(String s) {
        String ret = "";
        int count = 1;
        s += "#";
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
            } else {
                ret = ret + count + s.charAt(i);
                count = 1;
            }
        }
        return ret;
    }

    /**
     * 53. 最大子序和
     *
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:  输入: [-2,1,-3,4,-1,2,1,-5,4],
     *       输出: 6
     *       解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @return
     */
    //超出时间限制 不可取
    public int maxSubArray(int[] nums) {
        int max=0;
        for (int num:nums)
            max+=num;
        return maxSubArrayHelper(Arrays.stream(nums).boxed().collect(Collectors.toList()),max);
    }
    public int maxSubArrayHelper(List<Integer> nums,int max) {
        if (nums.size()==1)
            return nums.get(0);

        int left=maxSubArrayHelper(nums.subList(0,nums.size()-1),max-nums.get(nums.size()-1));
        int right=maxSubArrayHelper(nums.subList(1,nums.size()),max-nums.get(0));

        return Integer.max(max,Integer.max(left,right));
    }
    //贪心算法
    public int maxSubArray1(int[] nums){
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for(int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
    public int maxSubArray2(int[] nums){
//        int[] a = new int[nums.length];
//        a[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1] + nums[i],nums[i]) ;
            max = Math.max(nums[i], max);
        }
        return max;
    }

    /**
     * 58. 最后一个单词的长度
     *
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
     * 如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
     * 如果不存在最后一个单词，请返回 0 。
     * 说明：一个单词是指仅由字母组成、不包含任何空格的 最大子字符串。
     *
     * 示例:  输入: "Hello World"
     *       输出: 5
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s.length()==0) return 0;
        int length=0;
        for (int i=s.length()-1;i>=0;i--){
            if (s.charAt(i)==' '){
                if (length==0) continue;
                break;
            }
            length++;
        }

        return length;
    }

    /**
     * 66. 加一
     *
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * 示例:  输入: [1,2,3]
     *       输出: [1,2,4]
     *       解释: 输入数组表示数字 123。
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        while (len > 0) {
            if (digits[len - 1] == 9) {
                digits[len - 1] = 0;
                len--;
            } else {
                digits[len - 1] += 1;
                return digits;
            }
        }
        int[] r = new int[digits.length + 1];
        r[0] = 1;
        return r;
    }

    /**
     * 67. 二进制求和
     *
     * 给定两个二进制字符串，返回他们的和（用二进制表示）。
     * 输入为非空字符串且只包含数字 1 和 0。
     *
     * 示例:  输入: a = "1010", b = "1011"
     *       输出: "10101"
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if (a.equals("")) return b;
        if (b.equals("")) return a;

        int lenA=a.length()-1;
        int lenB=b.length()-1;
        int len=Integer.max(lenA,lenB);

        String ans= "";
        String temp="0";

        while (len>=0){
            if (lenA>=0&&lenB>=0){
                if (a.charAt(lenA)=='1'&&b.charAt(lenB)=='1'){
                    if (temp.equals("0")){
                        ans="0"+ans;
                    }else {
                        ans="1"+ans;
                    }
                    temp="1";
                }else if (a.charAt(lenA)=='1'&&b.charAt(lenB)=='0'||
                        a.charAt(lenA)=='0'&&b.charAt(lenB)=='1'){
                    if (temp.equals("0")){
                        ans="1"+ans;
                    }else {
                        ans="0"+ans;
                    }
                }else if (a.charAt(lenA)=='0'&&b.charAt(lenB)=='0'){
                    if (temp.equals("0")){
                        ans="0"+ans;
                    }else {
                        ans="1"+ans;
                    }
                    temp="0";
                }

                lenA--;
                lenB--;
            }else if (lenA>=0){
                if (a.charAt(lenA)=='0'){
                    if (temp.equals("0")){
                        ans="0"+ans;
                    }else {
                        ans="1"+ans;
                    }
                    temp="0";
                }else {
                    if (temp.equals("0")){
                        ans="1"+ans;
                    }else {
                        ans="0"+ans;
                    }
                }
                lenA--;
            }else {
                if (b.charAt(lenB)=='0'){
                    if (temp.equals("0")){
                        ans="0"+ans;
                    }else {
                        ans="1"+ans;
                    }
                    temp="0";
                }else {
                    if (temp.equals("0")){
                        ans="1"+ans;
                    }else {
                        ans="0"+ans;
                    }
                }
                lenB--;
            }
            len--;
        }
        return temp.equals("1")?"1"+ans:ans;
    }

    /**
     * 69. x 的平方根
     *
     * 实现 int sqrt(int x) 函数。
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:    输入: 4
     *           输出: 2
     *
     * 示例 2:    输入: 8
     *           输出: 2
     *           说明: 8 的平方根是 2.82842...,
     *           由于返回类型是整数，小数部分将被舍去。
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x<=0) return 0;

        int mid=0;
        int low=1;
        int high=x/2+1;

        while (low<=high){
            mid=(low+high)/2;
            if (mid<=x/mid&&mid+1>x/(mid+1)) return mid;
            if (mid>x/mid)
                high=mid-1;
            else low=mid+1;
        }

        return mid;
    }

    /**
     * 70. 爬楼梯 斐波那契数列
     *
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：    输入： 2
     *           输出： 2
     *           解释： 有两种方法可以爬到楼顶。
     *           1.  1 阶 + 1 阶
     *           2.  2 阶
     *
     * 示例 2：    输入： 3
     *           输出： 3
     *           解释： 有三种方法可以爬到楼顶。
     *           1.  1 阶 + 1 阶 + 1 阶
     *           2.  1 阶 + 2 阶
     *           3.  2 阶 + 1 阶
     *
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] nums=new int[n+1];
        nums[0]=1;
        nums[1]=1;
        for (int i=2;i<n+1;i++){
            nums[i]=nums[i-1]+nums[i-2];
        }
        return nums[n];
    }

    /**
     * 83. 删除排序链表中的重复元素
     *
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * 示例 1:    输入: 1->1->2
     *           输出: 1->2
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) return null;
        ListNode node=head;

        while (node!=null&&node.next!=null){
            if (node.val==node.next.val)
                node.next=node.next.next;
            else node=node.next;
        }
        return head;
    }

    /**
     * 88. 合并两个有序数组
     *
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     *
     * 说明:
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *
     * 示例:
     * 输入:  nums1 = [1,2,3,0,0,0], m = 3
     *       nums2 = [2,5,6],       n = 3
     * 输出:  [1,2,2,3,5,6]
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0;
        int j=0;
        int zero=m;
        while (i<m+j&&j<n){
            if (nums1[i]>nums2[j]){
                while (zero>i)
                    nums1[zero]=nums1[(zero--)-1];
                zero=m+j+1;
                nums1[i]=nums2[j];
                j++;
            }
            i++;
        }
        while (j<n){
            nums1[i++]=nums2[j++];
        }
    }
    //双指针 从后往前 个人觉得思路贼cool
    public void merge1(int[] nums1, int m, int[] nums2, int n){
        int i=m-1;
        int j=n-1;
        int end=m+n-1;
        while (i>=0&&j>=0){
            nums1[end--]=nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
        }
        System.arraycopy(nums2,0,nums1,0,j+1);
    }

    /**
     * 100. 相同的树
     *
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * 示例 1:
     * 输入:       1         1
     *           / \       / \
     *          2   3     2   3
     *
     *         [1,2,3],   [1,2,3]
     *
     * 输出: true
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null&&q==null) return true;
        if (q==null||p==null) return false;
        if (p.val!=q.val) return false;
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }

    /**
     * 101. 对称二叉树
     *
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isEqual(root, root);
    }
    private boolean isEqual(TreeNode node, TreeNode another) {
        if (node == null && another == null) return true;
        if (node == null || another == null) return false;
        return node.val == another.val && isEqual(node.left, another.right) && isEqual(node.right, another.left);
    }

    /**
     * 104. 二叉树的最大深度
     *
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return getMaxDepth(root, 0);
    }
    private int getMaxDepth(TreeNode node, int depth) {
        if (node == null) return depth;
        return Math.max(getMaxDepth(node.left, depth + 1), getMaxDepth(node.right, depth + 1));
    }

    /**
     * 107. 二叉树的层次遍历 II
     *
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 返回其自底向上的层次遍历为：
     *
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists=new ArrayList<>();


        if (root!=null){
            Queue<TreeNode> queue=new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()){
                List<Integer> list=new ArrayList<>();
                int num=queue.size();
                for (int i=0;i<num;i++){
                    TreeNode now=queue.poll();
                    list.add(now.val);

                    if (now.left!=null)
                        queue.add(now.left);
                    if (now.right!=null)
                        queue.add(now.right);
                }
                lists.add(0,list);
            }
        }
        return lists;
    }

    /**
     * 110. 平衡二叉树
     *
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     *
     * 示例 1:    给定二叉树 [3,9,20,null,null,15,7]
     *
     *                      3
     *                     / \
     *                    9  20
     *                      /  \
     *                     15   7
     *                  返回 true 。
     *
     * 示例 2:    给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     *                     1
     *                    / \
     *                   2   2
     *                  / \
     *                 3   3
     *                / \
     *               4   4
     *              返回 false 。
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return judgeBalanced(root).isBalanced;
    }
    private class TreeInfo{
        int depth;
        boolean isBalanced;

        public TreeInfo(int depth, boolean isBalanced) {
            this.depth = depth;
            this.isBalanced = isBalanced;
        }
    }
    private TreeInfo judgeBalanced(TreeNode root){
        if (root==null) return new TreeInfo(0,true);

        TreeInfo left=judgeBalanced(root.left);
        if (!left.isBalanced) return new TreeInfo(-1,false);

        TreeInfo right=judgeBalanced(root.right);
        if (!right.isBalanced) return new TreeInfo(-1,false);

        if (Math.abs(left.depth-right.depth)>1) return new TreeInfo(-1,false);

        return new TreeInfo(Math.max(left.depth,right.depth)+1,true);
    }

    /**
     * 111. 二叉树的最小深度
     *
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 返回它的最小深度  2.
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root==null) return 0;

        int min=Integer.MAX_VALUE;
        if (root.left==null&&root.right==null) return 1;
        if (root.left!=null) min=Math.min(minDepth(root.left),min);
        if (root.right!=null) min=Math.min(minDepth(root.right),min);

        return min+1;
    }

    /**
     * 112. 路径总和
     *
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例: 
     * 给定如下二叉树，以及目标和 sum = 22，
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root==null)
            return false;

        sum-=root.val;
        if (root.left==null&&root.right==null)
            return sum==0;

        return hasPathSum(root.left,sum)||hasPathSum(root.right,sum);
    }

    /**
     * 118. 杨辉三角
     *
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists=new ArrayList<>();
        if (numRows==0) return lists;

        for (int i=0;i<numRows;i++){
            List<Integer> list=new ArrayList<>();
            list.add(1);
            if (lists.size()==0) {
                lists.add(list);
                continue;
            }
            List<Integer> last=lists.get(i-1);
            for (int j=1;j<last.size();j++){
                list.add(last.get(j)+last.get(j-1));
            }
            list.add(1);
            lists.add(list);
        }

        return lists;
    }

    /**
     * 119. 杨辉三角 II
     *
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     * 输入: 3
     * 输出: [1,3,3,1]
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        if (rowIndex==0) return list;

        List<Integer> last;

        for (int i=0;i<rowIndex;i++){
            last=new ArrayList<>();
            last.add(1);
            for (int j=1;j<list.size();j++){
                last.add(list.get(j-1)+list.get(j));
            }
            last.add(1);
            list=last;
        }

        return list;
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * 注意你不能在买入股票前卖出股票。
     *
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     *
     * 示例 2:
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length==0) return 0;
        int max=0;
        for (int i=0;i<prices.length-1;i++){
            for (int j=i+1;j<prices.length;j++){
                max=Math.max(max,prices[j]-prices[i]);
            }
        }
        return max;
    }
    public int maxProfit1(int[] prices) {
        int max=0;
        int min=Integer.MAX_VALUE;
        for (int i=0;i<prices.length;i++){
            if (prices[i]<min) min=prices[i];
            else if (prices[i]-min>max) max=prices[i]-min;
        }
        return max;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     *
     * 示例 2:
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int profit=0;

        for (int i=1;i<prices.length;i++){
            if (prices[i]>prices[i-1]) {
                profit+=prices[i]-prices[i-1];
            }
        }

        return profit;
    }


    /**
     * 125. 验证回文串
     *
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     *
     * 示例 2:
     * 输入: "race a car"
     * 输出: false
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s==null) return true;
        s=s.toLowerCase();
        int before=0;
        int behind=s.length()-1;
        while (before<behind){
            if (!Character.isLetterOrDigit(s.charAt(before)))
                before++;
            else if (!Character.isLetterOrDigit(s.charAt(behind)))
                behind--;
            else if (s.charAt(before)!=s.charAt(behind))
                return false;
            else {
                before++;
                behind--;
            }
        }
        return true;
    }


    /**
     * 136. 只出现一次的数字
     *
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     * 输入: [2,2,3,2]
     * 输出: 3
     *
     * 示例 2:
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     *
     * @param nums
     * @return
     */
    //哈希表
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int num:nums){
            Integer count=map.get(num);
            count= count==null?1:++count;
            map.put(num,count);
        }
        for (int key:map.keySet()){
            if (map.get(key)==1)
                return key;
        }
        return -1;
    }
    //异或
    public int singleNumber1(int[] nums) {
        int ans=nums[0];
        for (int i=1;i<nums.length;i++)
            ans^=nums[i];
        return ans;
    }

    /**
     * 141. 环形链表
     *
     * 给定一个链表，判断链表中是否有环。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。
     *
     * 示例：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     * @param head
     * @return
     */
    //哈希表
    public boolean hasCycle(ListNode head) {
        if (head==null) return false;
        Set<ListNode> set=new HashSet<>();
        while (head!=null){
            if (set.contains(head)) return true;
            set.add(head);
            head=head.next;
        }
        return false;
    }
    //双指针
    public boolean hasCycle1(ListNode head){
        if (head==null||head.next==null) return false;
        ListNode slow=head;
        ListNode fast=head.next;
        while (slow!=fast){
            if (fast==null||fast.next==null) return false;
            slow=slow.next;
            fast=fast.next.next;
        }
        return true;
    }


    /**
     * 155. 最小栈
     *
     * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
     *
     * push(x) -- 将元素 x 推入栈中。
     * pop() -- 删除栈顶的元素。
     * top() -- 获取栈顶元素。
     * getMin() -- 检索栈中的最小元素。
     * 示例:
     *
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *
     */
    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> min;


        /** initialize your data structure here. */
        public MinStack() {
            this.stack=new Stack<>();
            this.min=new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (min.isEmpty()||min.peek()>=x){
                min.push(x);
            }
        }

        public void pop() {
            int top=stack.pop();
            if (min.peek().equals(top))
                min.pop();
        }

        public int top() {
            if (!stack.isEmpty())
                return stack.peek();
            else throw new RuntimeException("NO ELEMENT!!!");
        }

        public int getMin() {
            if (!min.isEmpty())
                return min.peek();
            else throw new RuntimeException("NO ELEMENT!!!");
        }
    }

    /**
     * 160. 相交链表
     *
     * 编写一个程序，找到两个单链表相交的起始节点。
     *
     * 注意：
     *
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     *
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA==null||headB==null) return null;

        ListNode nodeA=headA,nodeB=headB;

        while (nodeA!=nodeB){
            nodeA=nodeA==null?headB:nodeA.next;
            nodeB=nodeB==null?headA:nodeB.next;
        }

        return nodeA;
    }














    /**
     * 171. Excel表列序号
     * 给定一个Excel表格中的列名称，返回其相应的列序号。
     *
     * 例如:   A -> 1
     *        B -> 2
     *        C -> 3
     *        ...
     *        Z -> 26
     *        AA -> 27
     *        AB -> 28
     *        ...
     *
     * 示例 1:    输入: "A"
     *           输出: 1
     *
     * 示例 2:    输入: "AB"
     *           输出: 28

     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int ans=0;
        int len=s.length()-1;
        for (int i=0;i<s.length();len--,i++){
            ans+=Math.pow(26,i)*(s.charAt(len)-64);
        }

        return ans;
    }














    
}
