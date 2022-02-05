package temp;
/**
 * Illustrate big(O) for permutation of string.
 * 
 * n = 1, count = 1;
 * n = 2, count = 4 = (f(1) + 1) * 2
 * n = 3, count = 15 = (f(2) + 1) * 3
 * n = 4, count = 64 = (f(3) + 1) * 4)
 * n = 5, count = 325 = (f(4) + 1) * 5)
 * n = n, count = (f(n-1) + 1) * n
 */

public class Permutation{

    static int count;
    public Permutation(){
        count = 0;
    }

    public void permutation(String str){
        permutation(str, "");
    }
    public void permutation(String str, String prefix){
        
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); ++i){
                count++;
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }

    public static void main(String[] args){
        Permutation clz = new Permutation();
        String str = "abcdefg";
        clz.permutation(str);
        System.out.println(count);
    }

}
