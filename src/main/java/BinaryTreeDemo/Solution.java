package BinaryTreeDemo;

import java.util.*;

/**
 * @author shipengfei
 * @data 2020/1/20
 */
public class Solution {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);

        Solution solution=new Solution();
        List<Integer> list;
        list=solution.preorderTraversal(root);
        System.out.println(list);
        list=solution.inorderTraversal(root);
        System.out.println(list);
        list=solution.postorderTraversal(root);
        System.out.println(list);
        List<List<Integer>> lists=solution.levelOrder(root);
        System.out.println(lists);

    }

    //前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();

        if (root!=null){
            list.add(root.val);
            if (root.left!=null)
                list.addAll(preorderTraversal(root.left));
            if (root.right!=null)
                list.addAll(preorderTraversal(root.right));
        }

        return list;
    }

    //中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();

        if (root!=null){
            if (root.left!=null)
                list.addAll(inorderTraversal(root.left));
            list.add(root.val);
            if (root.right!=null)
                list.addAll(inorderTraversal(root.right));
        }

        return list;
    }

    //后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();

        if (root!=null){
            if (root.left!=null)
                list.addAll(postorderTraversal(root.left));
            if (root.right!=null)
                list.addAll(postorderTraversal(root.right));
            list.add(root.val);
        }

        return list;
    }

    //层次遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
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
                lists.add(list);
            }
        }
        return lists;
    }

}