package src.DioUtility.DioInt;

import java.util.Arrays;

/**
 * A implementation of array generator,
 * which to generates array in optional condition.
 * @author: Rain Hu
 * @since: 2022/2/7
 * @version: 1.0.2
 */
public class arrayGenerator {

    static final int DEFAULT_ARRAY_LENGTH = 32;
    static final boolean DEFAULT_INORDER = true;
    static final boolean DEFAULT_UNREPEATABLE = true;
    static final int MAXIMUM_ARRAY_LEGNTH = (1 << 16); // 65536
    
    private int size;
    private boolean inorder;
    private boolean unrepeatable;
    private int[] array;
    private int n;
    private int _min;
    private int _max;

    /**
     * arrayGenerator
     *
     * @param size The array's length. Default is 32. The maximum length up to 65536.
     * @param inorder Return a sorted array or not. Default is true.
     * @param unrepeatable Return an array with no repeatable numbers. Default is true.
     * @param max_val: The largest number(included) in the array.
     * @param min_val: The smallest number(included) in the array.
     * @return A [sorted/unsorted] [ordered/disordered] array ranged from [min_val] to [max_val].
     */

    public arrayGenerator(int size, boolean inorder, boolean unrepeatable, int min_val, int max_val){
        this.size = size;
        this.inorder = inorder;
        this.unrepeatable = unrepeatable;
        this.n = max_val - min_val + 1;
        this._min = min_val;
        this._max = max_val;
        this.array = this.get();
    }

    public arrayGenerator(int size, boolean inorder, boolean unrepeatable) {this(size, inorder, unrepeatable, 1, size);}
    public arrayGenerator(int size, boolean inorder) {this(size, inorder, DEFAULT_UNREPEATABLE);}
    public arrayGenerator(int size) {this(size, DEFAULT_INORDER, DEFAULT_UNREPEATABLE);}
    public arrayGenerator() {this(DEFAULT_ARRAY_LENGTH, DEFAULT_INORDER, DEFAULT_UNREPEATABLE);}

    private int[] get(){
        int[] arr;
        size = size <= 0 ? 0 : size > MAXIMUM_ARRAY_LEGNTH ? MAXIMUM_ARRAY_LEGNTH : size;
        int cnt = 0;
        if (!unrepeatable){
            arr = new int[size];
            while (cnt < size){
                arr[cnt++] = (int)(Math.random() * n) + _min;
            }
            if (inorder) Arrays.sort(arr);
        } else {
            size = size > n ? n : size; 
            arr = new int[size];
            if (inorder && n == size){
                for (int i = _min; i <= _max; i++){
                    arr[cnt++] = i;
                }
            } else {
                boolean[] dp = new boolean[n];
                while (cnt < size){
                    int rand = (int)(Math.random() * n) + _min;
                    while (dp[rand - _min]){
                        rand++;
                        if (rand > _max)
                            rand -= n;
                    }
                    arr[cnt++] = rand;
                    dp[rand - _min] = true;
                }
                if (inorder && n != size) Arrays.sort(arr);
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
