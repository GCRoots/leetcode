package BinarySearchTreeDemo;

import BinaryTreeDemo.Node;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.*;

/**
 * @author shipengfei
 * @data 2020/1/28
 */
public class Solution {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(4);
        root.left=new TreeNode(2);
        root.right=new TreeNode(7);
        root.left.left=new TreeNode(1);
        root.left.right=new TreeNode(3);
        root.right.left=new TreeNode(6);

        Solution solution=new Solution();

        boolean isValidBST=solution.isValidBST(root);
        System.out.println(isValidBST);

        System.out.println(solution.searchBST(root,2));

        System.out.println(solution.inorderTraversalStack(root));
//        System.out.println(solution.inorderTraversalStack(solution.insertIntoBST(root,5)));

//        System.out.println(solution.inorderTraversalStack(solution.deleteNode(root,3)));

        TreeNode treeNode=solution.deserialize("[2,0,33,null,1,25,40,null,null,11,31,34,45,10,18,29,32,null,36,43,46,4,null,12,24,26,30,null,null,35,39,42,44,null,48,3,9,null,14,22,null,null,27,null,null,null,null,38,null,41,null,null,null,47,49,null,null,5,null,13,15,21,23,null,28,37,null,null,null,null,null,null,null,null,8,null,null,null,17,19,null,null,null,null,null,null,null,7,null,16,null,null,20,6]");
        System.out.println(solution.inorderTraversalStack(solution.deleteNode(treeNode,33)));

        KthLargest k=solution.new KthLargest(1,new int[]{-2});
        System.out.println(k.count);
        System.out.println(k.add(-3));

        int[] nums={0,1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, 0, 2)));

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
     * 二叉树的反序列化
     * Decodes your encoded data to tree.
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        if (data=="[null]")
            return null;

        data=data.replace("[","");
        data=data.replace("]","");
        List<String> data_list= Arrays.asList(data.split(","));
        List<String> list=new ArrayList(data_list);

        TreeNode root=new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()&&list.size()>0) {
            int num = queue.size();
            for (int i = 0; i < num; i++) {
                TreeNode now = queue.poll();

                if (list.size()==0) break;
                if (list.get(0).equals("null")){
                    now.left=null;
                }else {
                    now.left=new TreeNode(Integer.parseInt(list.get(0)));
                    queue.add(now.left);
                }
                list.remove(0);

                if (list.size()==0) break;
                if (list.get(0).equals("null")){
                    now.right=null;
                }else {
                    now.right=new TreeNode(Integer.parseInt(list.get(0)));
                    queue.add(now.right);
                }
                list.remove(0);

            }
        }

        return root;
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

    // 在二叉搜索树中实现搜索操作
    public TreeNode searchBST(TreeNode root, int val) {
        if (root==null) return null;
        if (root.val==val) return root;
        return root.val>val?searchBST(root.left,val):searchBST(root.right,val);
    }

    // 二叉搜索树迭代器
    class BSTIterator {
        private Queue<Integer> queue=new LinkedList<>();

        //初始化BSTIterator
        public BSTIterator(TreeNode root) {
            Stack<TreeNode> stack=new Stack<>();
            TreeNode curr=root;
            while (curr!=null||!stack.isEmpty()){
                while (curr!=null){
                    stack.push(curr);
                    curr=curr.left;
                }
                curr=stack.pop();
                queue.add(curr.val);
                curr=curr.right;
            }
        }

        /** @return the next smallest number */
        public int next() {
            return queue.poll();
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
    class BSTIterator1 {
        private Stack<TreeNode> stack;

        public BSTIterator1(TreeNode root) {
            this.stack=new Stack<>();
            pushElement(root);
        }
        private void pushElement(TreeNode root){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node=stack.pop();
            pushElement(node.right);
            return node.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    // 在二叉搜索树中实现插入操作
    public TreeNode insertIntoBST(TreeNode root, int val) {
        insertIntoBSTHelper(root,val);
        return root;
    }
    private void insertIntoBSTHelper(TreeNode root, int val){
        if (root.val>val) {
            if (root.left!=null)
                insertIntoBSTHelper(root.left, val);
            else root.left=new TreeNode(val);
        }
        if (root.val<val) {
            if (root.right!=null)
                insertIntoBSTHelper(root.right,val);
            else root.right=new TreeNode(val);
        }
    }

    // 在二叉搜索树中实现删除操作
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root==null||root.left==null&&root.right==null&&root.val==key) return null;
        TreeNode treeNode = root;
        TreeNode node = root;

        while (root.val != key) {
            node=root;
            if (key < root.val){
                if (root.left!=null) root = root.left;
                else return treeNode;
            } else if (key > root.val){
                if (root.right!=null) root = root.right;
                else return treeNode;
            }
        }

        if (root.right!=null){
            node=root.right;
            TreeNode node1=root;
            while (node.left!=null){
                node1=node;
                node=node.left;
            }
            if (node1==root) node1.right=node.right;
            else node1.left=node.right;

            root.val=node.val;

        } else if (root.left!=null){
            if (node==root) return node.left;

            if (node.val>key)
                node.left=root.left;
            else node.right=root.left;
        }else {
            if (key < node.val) node.left=null;
            else node.right=null;
        }
        return treeNode;
    }

    // 设计一个找到数据流中第K大元素的类（class）二叉排序树
    class KthLargest {
        class Node{
            int val;
            int ranking;
            Node left;
            Node right;

            Node(int val){
                this.val=val;
            }

            public Node(int val, int ranking) {
                this.val = val;
                this.ranking = ranking;
            }
        }

        Node root;
        int count;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k=k;
            if (nums.length==0) return;
            root=new Node(nums[0],1);
            if (k==1) count=nums[0];

            for (int i=1;i<nums.length;i++){
                addHelper(root,nums[i]);
            }

        }

        public int add(int val) {
            if (root==null) {
                root=new Node(val,1);
                if (k==1) count=val;
            }else addHelper(root,val);
            return count;
        }

        private void addHelper(Node root, int val){
            if (val<root.val) {
                if (root.left!=null)
                    addHelper(root.left, val);
                else {
                    root.left=new Node(val,root.ranking+1);
                    if (root.ranking+1==k) count=val;

                }
            }else {
                root.ranking++;
                if (root.ranking==k) count=root.val;
                if (root.left!=null) addRanking(root.left);
                if (root.right!=null)
                    addHelper(root.right,val);
                else {
                    root.right=new Node(val,root.ranking-1);
                    if (root.ranking-1==k) count=val;
                }
            }

        }

        private void addRanking(Node root){
            if (root!=null){
                root.ranking++;
                if (root.ranking==k) count=root.val;
                if (root.left!=null) addRanking(root.left);
                if (root.right!=null) addRanking(root.right);

            }
        }
    }

    // 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pval=p.val;
        int qval=q.val;
        int rval=root.val;

        if (pval>rval&&qval>rval) return lowestCommonAncestor(root.right,p,q);
        else if (pval<rval&&qval<rval) return lowestCommonAncestor(root.left,p,q);
        else return root;
    }

    // 判断平衡二叉树 自顶向下的递归
    public boolean isBalanced(TreeNode root) {
        if (root==null) return true;
        return Math.abs(isBalencedHelper(root.left)-isBalencedHelper(root.right))<2
                &&isBalanced(root.left)&&isBalanced(root.right);
    }
    private int isBalencedHelper(TreeNode root){
        if (root==null) return 0;
        return 1+Math.max(isBalencedHelper(root.left),isBalencedHelper(root.right));
    }

    // 判断平衡二叉树 自底向上的递归
    public boolean isBalancedUp(TreeNode root) {
        return judgeBalanced(root).isBalanced;
    }
    private class TreeInfo{
        int depth;
        boolean isBalanced;

        public TreeInfo(int depth, boolean isBalanced) {
            this.depth = depth;
            this.isBalanced = isBalanced;
        }
    }
    private TreeInfo judgeBalanced(TreeNode root){
        if (root==null)
            return new TreeInfo(0,true);

        TreeInfo left=judgeBalanced(root.left);
        if (!left.isBalanced)
            return new TreeInfo(-1,false);

        TreeInfo right=judgeBalanced(root.right);
        if (!right.isBalanced)
            return new TreeInfo(-1,false);

        if (Math.abs(left.depth-right.depth)>1)
            return new TreeInfo(-1,false);

        return new TreeInfo(Math.max(left.depth,right.depth)+1,true);
    }

    // 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length==0) return null;
        return build(nums,0,nums.length-1);
    }
    private TreeNode build(int[] nums,int left,int right){
        if (left>right) return null;

        int mid=(left+right)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=build(nums,left,mid-1);
        root.right=build(nums,mid+1,right);
        return root;
    }



}

