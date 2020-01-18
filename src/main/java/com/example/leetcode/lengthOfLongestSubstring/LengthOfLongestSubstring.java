package com.example.leetcode.lengthOfLongestSubstring;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * @author shipengfei
 * @data 2020/1/18
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s="";

        System.out.println(s.length());

        LengthOfLongestSubstring l=new LengthOfLongestSubstring();
        System.out.println(l.lengthOfLongestSubstring(s));

    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length()<2)
            return s.length();

        int longest=0;
        for (int i=0;i<s.length();i++){
            char before=s.charAt(i);
            HashSet set=new HashSet();
            set.add(before);
            for (int j=i+1;j<s.length();j++){
                char last=s.charAt(j);
                if (!set.contains(last)){
                    set.add(last);
                }else{
                    longest=Math.max(longest,set.size());
                    break;
                }

                if (j==s.length()-1){
                    longest=Math.max(longest,set.size());
                }
            }
        }
        return longest;
    }

    public int lengthOfLongestSubstring1(String s){
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

}
