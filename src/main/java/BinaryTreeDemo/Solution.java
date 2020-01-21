package BinaryTreeDemo;

import sun.nio.cs.ext.MacArabic;

import java.util.*;

/**
 * @author shipengfei
 * @data 2020/1/20
 */
public class Solution {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(2);
        root.left.left=new TreeNode(3);
        root.left.right=new TreeNode(4);
        root.right.left=new TreeNode(4);
        root.right.right=new TreeNode(3);

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
        int depth=solution.maxDepthTopDown(root,1);
        System.out.println(depth);
        depth=solution.maxDepthBottomUp(root);
        System.out.println(depth);
        depth=solution.maxDepth(root);
        System.out.println(depth);
        boolean isSymmetric=solution.isSymmetric(root);
        System.out.println(isSymmetric);
        isSymmetric=solution.isSymmetricBest(root);
        System.out.println(isSymmetric);
        boolean hasPathSum=solution.hasPathSum(root,8);
        System.out.println(hasPathSum);

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

    //层次遍历 ---- 非递归
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

    //最大深度 ---- 自顶向下
    public int maxDepthTopDown(TreeNode root,int depth) {
        if (root==null)
            return depth-1;
        if (root.left==null&&root.right==null)
            return depth;

        int left=maxDepthTopDown(root.left,depth+1);
        int right=maxDepthTopDown(root.right,depth+1);
        return Math.max(left,right);
    }

    //最大深度 ---- 自底向上(Best)
    public int maxDepthBottomUp(TreeNode root) {
        if (root==null)
            return 0;

        int left=maxDepthBottomUp(root.left)+1;
        int right=maxDepthBottomUp(root.right)+1;
        return Math.max(left,right);
    }

    //最大深度 ---- 迭代
    public int maxDepth(TreeNode root) {
        if (root==null)
            return 0;

        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int depth=0;

        while (!queue.isEmpty()){
            depth++;
            int size=queue.size();

            for (int i = 0; i < size; i++){
                TreeNode node=queue.poll();
                if (node.left!=null)
                    queue.add(node.left);
                if (node.right!=null)
                    queue.add(node.right);
            }
        }

        return depth;
    }

    //对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if (root==null||root.left==null&&root.right==null)
            return true;
        if (root.left==null||root.right==null)
            return false;

        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()){
            int size=queue.size();

            for (int i = 0; i < size; i+=2){
                TreeNode node1=queue.poll();
                TreeNode node2=queue.poll();

                if (node1.val!=node2.val)
                    return false;

                if (node1.left!=null&&node2.right!=null){
                    queue.add(node1.left);
                    queue.add(node2.right);
                }else if (node1.left!=null||node2.right!=null){
                    return false;
                }

                if (node1.right!=null&&node2.left!=null){
                    queue.add(node1.right);
                    queue.add(node2.left);
                }else if (node1.right!=null||node2.left!=null){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isSymmetricBest(TreeNode root) {
        return isEqual(root, root);
    }

    private boolean isEqual(TreeNode node, TreeNode another) {
        if (node == null && another == null) {
            return true;
        }
        if (node == null || another == null) {
            return false;
        }
        return node.val == another.val && isEqual(node.left, another.right) && isEqual(node.right, another.left);
    }

    //路径总和
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root==null)
            return false;

        sum-=root.val;
        if (root.left==null&&root.right==null)
            return sum==0;

        return hasPathSum(root.left,sum)||hasPathSum(root.right,sum);
    }


}
