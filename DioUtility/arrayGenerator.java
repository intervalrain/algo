package DioUtility;

import java.util.Arrays;

public class arrayGenerator {

    static final int DEFAULT_ARRAY_LENGTH = 32;
    static final boolean DEFAULT_INORDER = true;
    static final boolean DEFAULT_UNREPEATABLE = true;
    static final int MAXIMUM_ARRAY_LEGNTH = (1 << 16); // 65536
    
    int n;
    boolean inorder;
    boolean unrepeatable;
    int[] array;

    public arrayGenerator(int n, boolean inorder, boolean unrepeatable){
        this.n = n;
        this.inorder = inorder;
        this.unrepeatable = unrepeatable;
        this.array = this.get();
    }

    public arrayGenerator(int n, boolean inorder) {this(n, inorder, DEFAULT_UNREPEATABLE);}
    public arrayGenerator(int n) {this(n, DEFAULT_INORDER, DEFAULT_UNREPEATABLE);}
    public arrayGenerator() {this(DEFAULT_ARRAY_LENGTH, DEFAULT_INORDER, DEFAULT_UNREPEATABLE);}

    private int[] get(){
        int[] arr = new int[n];
        n = n <= 0 ? 0 : n > MAXIMUM_ARRAY_LEGNTH ? MAXIMUM_ARRAY_LEGNTH : n;
        int cnt = 0;
        if (!unrepeatable){
            while (cnt < n){
                arr[cnt++] = (int)(Math.random()*n) + 1;
            }
            if (inorder) Arrays.sort(arr);
        } else {
            if (inorder){
                for (int i = 1; i <= n; i++){
                    arr[i-1] = i;
                }
            } else {
                boolean[] dp = new boolean[n];
                while (cnt < n){
                    int rand = (int)(Math.random() * n) + 1;
                    while (dp[rand-1]){
                        rand++;
                        if (rand > n) rand %= n;
                    }
                    arr[cnt++] = rand;
                    dp[rand-1] = true;
                }
            }
        }
        return arr;
    }

    public int[] toArray(){
        return array;
    }

    public String toString(){
        return Arrays.toString(this.array);
    }
}
