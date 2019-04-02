package com.example.leetcode.april2019.addTwoNumbersTest;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);

        ListNode listNode1=new ListNode(0);
        ListNode l1=listNode1;
        ListNode listNode2=new ListNode(0);
        ListNode l2=listNode2;
        for (int i=0;i<3;i++){
            System.out.print("listNode1:");
            listNode1.val=scanner.nextInt();
            System.out.print("listNode2:");
            listNode2.val=scanner.nextInt();


            if (i<2) {
                listNode1.next=new ListNode(0);
                listNode2.next=new ListNode(0);
                listNode1 = listNode1.next;
                listNode2 = listNode2.next;
            }

        }

        SolutionImpl solution=new SolutionImpl();
        ListNode listNode=solution.addTwoNumbersImpl(l1,l2);
        while (listNode!=null){
            System.out.print("--->"+listNode.val);
            listNode=listNode.next;
        }


    }
}
