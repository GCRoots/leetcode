package com.example.leetcode.april2019.addTwoNumbersTest;

public class SolutionImpl {

    public ListNode addTwoNumbersImpl(ListNode l1,ListNode l2){
        ListNode listNode=new ListNode(0);
        ListNode l=listNode;
        int carry=0;

        while (l1!=null||l2!=null){
            int x=0,y=0;
            if (l1!=null) {
                x = l1.val;
                l1 = l1.next;
            }
            if (l2!=null) {
                y = l2.val;
                l2=l2.next;
            }
            int sum=x+y+carry;
            carry=sum/10;
            listNode.next=new ListNode(sum%10);
            listNode=listNode.next;
        }

        if (carry>0) {
            listNode.next = new ListNode(carry);
        }

        return l.next;



    }
}

