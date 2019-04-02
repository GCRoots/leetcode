package com.example.leetcode.april2019.addTwoNumbersTest;

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode listNode=new ListNode(0);
        ListNode returnNode=listNode;
        int carry=0;
        while(l1!=null||l2!=null||carry==1){

            if (l1==null&&l2==null){
                listNode.val=carry;

            } else if(l1==null&&l2!=null){
                listNode.val=(l2.val+carry)%10;
                carry=(l2.val+carry)/10;
                l2=l2.next;
                if (l2!=null||carry==1) {
                    listNode.next=new ListNode(0);
                    listNode = listNode.next;
                }
            } else if (l2==null&&l1!=null){
                listNode.val=(l1.val+carry)%10;
                carry=(l1.val+carry)/10;
                l1=l1.next;
                if (l1!=null||carry==1) {
                    listNode.next=new ListNode(0);
                    listNode = listNode.next;
                }
            } else if(l1!=null&&l2!=null){
                listNode.val=(l1.val+l2.val+carry)%10;
                carry=(l1.val+l2.val+carry)/10;
                l1=l1.next;
                l2=l2.next;
                if (l1!=null||l2!=null||carry==1) {
                    listNode.next=new ListNode(0);
                    listNode = listNode.next;
                }
            }

        }

        return returnNode;
    }
}
