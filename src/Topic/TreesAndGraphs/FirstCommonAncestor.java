package src.Topic.TreesAndGraphs;

import src.DioUtility.DioInt.TreeNode;

/**
 * First Common Ancestor
 * 
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 * Note: This is not necessarily a binary search tree.
 */

public class FirstCommonAncestor {
    
    // sol1: with links to parent
    public TreeNode solve1(TreeNode p, TreeNode q){
        int diff = depth(p) - depth(q);
        TreeNode first = diff > 0 ? p : q;
        TreeNode second = diff > 0 ? q : p;
        diff = Math.abs(diff);
        while (diff > 0){
            first = first.parent;
            diff--;
        }
        while (first != second && first != null && second != null){
            first = first.parent;
            second = second.parent;
        }
        return first == null || second == null ? null : first;
    }
    private int depth(TreeNode node){
        int cnt = 0;
        while (node != null){
            node = node.parent;
            cnt++;
        }
        return cnt;
    }

    // sol2: with links to parent
    public TreeNode solve2(TreeNode root, TreeNode p, TreeNode q){
        if (!covers(root, p) || !covers(root, q)){
            return null;
        } else if (covers(p, q)){
            return p;
        } else if (covers(q, p)){
            return q;
        }

        TreeNode sibling = getSibling(p);
        TreeNode parent = p.parent;
        while (!covers(sibling, q)){
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;

    }

    public TreeNode getSibling(TreeNode node){
        if (node == null || node.parent == null) return null;
        TreeNode parent = node.parent;
        return parent.left == node ? parent.right : parent.left;
    }

    public boolean covers(TreeNode root, TreeNode child){
        if (root == null) return false;
        if (root == child) return true;
        return covers(root.left, child) || covers(root.right, child);
        
    }

    // sol3: without links to parents
    public TreeNode solve3(TreeNode root, TreeNode p, TreeNode q){
        if (!covers(root, p) || !covers(root, q))
            return null;
        return solve3helper(root, p, q);
    }

    public TreeNode solve3helper(TreeNode root, TreeNode p, TreeNode q){
        if (root == null || root == p || root == q)
            return root;
        // if p and q are at the different sides, it means the node is first parent.
        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft = covers(root.left, q);
        if (pIsOnLeft != qIsOnLeft)
            return root;
        TreeNode childside = pIsOnLeft ? root.left : root.right;
        return solve3helper(childside, p, q);
    }

    // sol4: optimized for sol3
    class Result {
        public TreeNode node;
        public boolean isAncestor;
        public Result (TreeNode n, boolean isAnc){
            node = n;
            isAncestor = isAnc;
        }
    }
    public TreeNode solve4(TreeNode root, TreeNode p , TreeNode q){
        Result r = solve4helper(root, p , q);
        if (r.isAncestor) return r.node;
        return null;
    }

    public Result solve4helper(TreeNode root, TreeNode p, TreeNode q){
        if (root == null) return new Result(null, false);
        if (root == p && root == q){
            return new Result(root, true);
        }
        Result rx = solve4helper(root.left, p, q);
        if (rx.isAncestor)   // found common ancestor
            return rx;
        Result ry = solve4helper(root.right, p, q);
        if (ry.isAncestor)   // found common ancestor
            return ry;
        if (rx.node != null && ry.node != null){
            return new Result(root, true);  // common ancestor
        } else if (root == p || root == q){
            boolean isAncestor = rx.node != null || ry.node !=null;
            return new Result(root, isAncestor);
        } else {
            return new Result(rx.node != null ? rx.node : ry.node , false);
        }
    }
    
    public static void main(String[] args){
        TreeNode root = TreeNode.arrayToTree(new Integer[]{2,10,30,55,15,null,null,3,37,null,17});
        TreeNode p = root.getChildAt(8);
        TreeNode q = root.getChildAt(10);
        FirstCommonAncestor clz = new FirstCommonAncestor();
        System.out.println(clz.solve1(p, q).val);  // 10
        System.out.println(clz.solve2(root, p, q).val);  // 10
        System.out.println(clz.solve3(root, p, q).val);  // 10
        System.out.println(clz.solve4(root, p, q).val);  // 10
    }
}
