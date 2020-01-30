package BinarySearchTreeDemo;

import java.util.*;

/**
 * @author shipengfei
 * @data 2020/1/28
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

        boolean isValidBST=solution.isValidBST(root);
        System.out.println(isValidBST);
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

    //中序遍历 递归
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

    //中序遍历 栈
    public List < Integer > inorderTraversalStack(TreeNode root) {
        List <Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr=root;
        while (curr!=null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
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


    /**
     * 验证二叉搜索树 Lowest
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        List<Integer> list=inorderTraversal(root);

        TreeSet set = new TreeSet(list);
        List<Integer> removed = new ArrayList<>();
        removed.addAll(set);

        if (!removed.equals(list))
            return false;

        List<Integer> sort = new ArrayList<>();
        sort.addAll(list);
        list.sort(Comparator.comparingInt(Integer::intValue));
        return list.equals(sort);
    }

    public boolean isValidBSTStack(TreeNode root){
        if (root == null)
            return true;

        Stack<TreeNode> stack = new Stack<>();
        double inorder=-Double.MAX_VALUE;
        while (root!=null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


}