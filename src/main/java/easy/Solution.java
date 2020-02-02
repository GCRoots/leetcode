package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

}
