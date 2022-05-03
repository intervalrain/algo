package temp;

import java.util.Arrays;

public class coin {
    public static int findBag(double[] coins){
        double sum = 0;
        for (int i = 0; i < 10; i++){
            sum += (i + 1) * coins[i];
        }
        
        return (int)(((sum + 0.01) - 55.0) / 0.1);  // add 0.01 to cover tuncate by floating-point
    }

    public static double[] refill(int i){
        double[] bag = new double[10];
        Arrays.fill(bag, 1.0);
        bag[i] = 1.1;
        return bag;
    }
    public static void main(String[] args){
        for (int i = 0; i < 10; i++){
            System.out.println(findBag(refill(i)));
        }
    }
}
