package BinaryTreeDemo;

import sun.nio.cs.ext.MacArabic;

import java.util.*;
import java.util.stream.Collectors;

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

        List<Integer> l=new ArrayList<>();
        l.add(1);
        l.add(3);
        l.add(5);
        l.add(4);
        System.out.println(l);
        System.out.println(l.indexOf(5));
        System.out.println(l.subList(0,2));
        System.out.println(l.subList(1,3));

        int[] pre={3,9,20,15,7};
        int[] in={9,3,15,20,7};
        int[] post={9,15,7,20,3};
        TreeNode node=solution.buildTree(in,post);
        System.out.println(solution.inorderTraversal(node));
        System.out.println(solution.postorderTraversal(node));
        TreeNode node1=solution.buildTree1(pre,in);
        System.out.println(solution.preorderTraversal(node1));
        System.out.println(solution.inorderTraversal(node1));


        Node root1=new Node(1);
        root1.left=new Node(2);
        root1.right=new Node(3);
        root1.left.left=new Node(4);
        root1.left.right=new Node(5);
        root1.right.left=new Node(6);
        root1.right.right=new Node(7);
        solution.connect1(root1);

        TreeNode a=solution.lowestCommonAncestor(root,root.left,root.left.right);
        System.out.println(a.val);



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

    /**
     * 从中序与后序遍历序列构造二叉树
     *
     * @param inorder 中序遍历
     * @param postorder 后序遍历
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        TreeNode node=null;

        if (postorder.length>0){
            int last=postorder.length-1;
            node=new TreeNode(postorder[last]);

            List<Integer> in =Arrays.stream(inorder).boxed().collect(Collectors.toList());
            List<Integer> post =Arrays.stream(postorder).boxed().collect(Collectors.toList());

            int innode=in.indexOf(postorder[last]);
            node.left=build(in.subList(0,innode),post.subList(0,innode));
            node.right=build(in.subList(innode+1,last+1),post.subList(innode,last));
        }

        return node;
    }

    private TreeNode build(List<Integer> inorder, List<Integer> postorder){


        TreeNode node=null;

        if (postorder.size()>0){
            int last=postorder.size()-1;
            node=new TreeNode(postorder.get(last));

            int innode=inorder.indexOf(postorder.get(last));
            node.left=build(inorder.subList(0,innode),postorder.subList(0,innode));
            node.right=build(inorder.subList(innode+1,last+1),postorder.subList(innode,last));
        }

        return node;
    }

    /**
     * 从前序与中序遍历序列构造二叉树
     *
     * @param preorder 前序遍历
     * @param inorder 中序遍历
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        TreeNode node=null;

        if (preorder.length>0){
            int last=preorder.length-1;
            node=new TreeNode(preorder[0]);

            List<Integer> pre =Arrays.stream(preorder).boxed().collect(Collectors.toList());
            List<Integer> in =Arrays.stream(inorder).boxed().collect(Collectors.toList());


            int innode=in.indexOf(preorder[0]);
            node.left=build1(pre.subList(1,innode+1),in.subList(0,innode));
            node.right=build1(pre.subList(innode+1,last+1),in.subList(innode+1,last+1));
        }

        return node;
    }

    private TreeNode build1(List<Integer> preorder, List<Integer> inorder){
        TreeNode node=null;

        if (preorder.size()>0){
            int last=preorder.size()-1;
            node=new TreeNode(preorder.get(0));

            int innode=inorder.indexOf(preorder.get(0));
            node.left=build1(preorder.subList(1,innode+1),inorder.subList(0,innode));
            node.right=build1(preorder.subList(innode+1,last+1),inorder.subList(innode+1,last+1));
        }

        return node;
    }

    //填充每个节点的下一个右侧节点指针 迭代 通用
    public Node connect(Node root) {
        if (root==null)
            return null;

        Queue<Node> queue=new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size=queue.size();

            Node now=queue.poll();
            for (int i = 0; i < size; i++){

                if (now.left!=null)
                    queue.add(now.left);
                if (now.right!=null)
                    queue.add(now.right);

                if (i==size-1)
                    break;

                Node node=queue.poll();
                now.next=node;
                now=node;
            }
        }

        return root;
    }

    //填充每个节点的下一个右侧节点指针 拉链法 完美二叉树
    public Node connect1(Node root){
        if (root==null)
            return root;

        Node left=root.left;
        Node right=root.right;

        while (left!=null){
            left.next=right;
            left=left.right;
            right=right.left;
        }

        connect1(root.left);
        connect1(root.right);

        return root;
    }

    /**
     * 二叉树的最近公共祖先
     * 最low的自己实现的版本，low到爆
     *
     * @param root 根节点
     * @param p 目标节点 p
     * @param q 目标节点 q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null)
            return null;

        List<TreeNode> list=inorderTraversal1(root);

        int indexroot=list.indexOf(root);
        int indexp=list.indexOf(p);
        int indexq=list.indexOf(q);
        int size=list.size();

        while (indexroot<size&&indexp<size&&indexq<size){
            if (indexp<indexroot&&indexq<indexroot){
                list=list.subList(0,indexroot);
                root=root.left;

            }else if (indexp>indexroot&&indexq>indexroot){
                list=list.subList(indexroot,size);
                root=root.right;
            }else {
                return root;
            }

            indexroot=list.indexOf(root);
            indexp=list.indexOf(p);
            indexq=list.indexOf(q);
            size=list.size();

        }

        return null;
    }

    private List<TreeNode> inorderTraversal1(TreeNode root) {
        List<TreeNode> list=new ArrayList<>();

        if (root!=null){
            if (root.left!=null)
                list.addAll(inorderTraversal1(root.left));
            list.add(root);
            if (root.right!=null)
                list.addAll(inorderTraversal1(root.right));
        }

        return list;
    }

    /**
     * 二叉树的最近公共祖先
     * Best版本(leetcode)
     *
     * @param root 根节点
     * @param p 目标节点 p
     * @param q 目标节点 q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null){
            return root;
        }
        return left != null ? left : right;
    }

}
