package temp;

import java.util.Arrays;

class Solution{
    int n;
    int[] updateQuery(int n, int k, int[][] q){
        this.n = n;
        int[][] tree = new int[n][n];
        for (int i = 0; i < k; i++){
            int l = q[i][0] - 1;
            int r = q[i][1] - 1;
            int x = q[i][2];
            tree[r-l][l] |= x;
        }
        update(tree, 0, n-2);
        update(tree, 1, n-1);

        return tree[0];
    }
    public void update(int[][] tree, int left, int right){
        int level = right - left;

        if (left == 0){
            tree[level][0] |= tree[level+1][0];
        } else if (right == n-1){
            tree[level][n-1-level] |= tree[level+1][n-1-level-1];
        } else {
            tree[level][left] |= tree[level+1][left-1];
            tree[level][left] |= tree[level+1][left];
        }
        
        if (level == 0) return;
        update(tree, left, right-1);
        update(tree, left+1, right);
    }
    public static void main(String[] args){
        int n = 3;
        int k = 2;
        int[][] q = {{1,2,1},{3,3,2}};
        Solution sol = new Solution();
        int[] res = sol.updateQuery(n, k, q);
        System.out.println(Arrays.toString(res));
    }

}
