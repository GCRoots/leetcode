package addTwoNumbersTest;

import java.util.Scanner;


/*
 * 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ListNode listNode1 = new ListNode(0);
        ListNode l1 = listNode1;
        ListNode listNode2 = new ListNode(0);
        ListNode l2 = listNode2;
        for (int i = 0; i < 3; i++) {
            System.out.print("listNode1:");
            listNode1.val = scanner.nextInt();
            System.out.print("listNode2:");
            listNode2.val = scanner.nextInt();


            if (i < 2) {
                listNode1.next = new ListNode(0);
                listNode2.next = new ListNode(0);
                listNode1 = listNode1.next;
                listNode2 = listNode2.next;
            }

        }

        SolutionImpl solution = new SolutionImpl();
        ListNode listNode = solution.addTwoNumbersImpl(l1, l2);
        while (listNode != null) {
            System.out.print("--->" + listNode.val);
            listNode = listNode.next;
        }


    }
}
