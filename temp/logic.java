package temp;

import java.util.Stack;

public class logic {
    
    static class G{
        int ones = 0;
        int ds = 0;
        public G(int[] minterms, int[] dcterms){
            for (int i = 0; i < minterms.length; i++){
                ones |= ((1 << minterms[i]));
            }
            for (int i = 0; i < dcterms.length; i++){
                ds |= ((1 << dcterms[i]));
            }
        }
        public boolean contains(F fx){
            int bits = fx.bitset;
            int res = bits ^ ones;
            return ds == (ds | res);
        }
    }
    static class F{
        int[] var = new int[]{15, 240, 51, 204, 85, 170}; // X', X, Y', Y, Z', Z
        int bitset;
        public F(String eq){
            bitset = numerize(eq);
        }
        public int numerize(String eq){
            Stack<Integer> st = new Stack<>();
            int num = 0;
            int curr = 255;
            int n = eq.length();
            int i = 0;
            char[] ch = eq.toCharArray();
            while (i < n){
                if (i + 1 < n && ch[i+1] == '\''){
                    switch (ch[i]) {
                        case 'X': st.push(var[0]); break;
                        case 'Y': st.push(var[2]); break;
                        case 'Z': st.push(var[4]); break;
                        default: break;
                    }
                    i += 2;
                } else if (ch[i] == ' '){
                    i++;
                } else if (ch[i] == '+'){
                    while (!st.isEmpty())
                        curr &= st.pop();
                    i++;
                    num |= curr;
                    curr = 255;
                } else if (ch[i] == '('){
                    int j = i + 1;
                    for (; j < n; j++){
                        if (ch[j] == ')') break;
                    }
                    String s = eq.substring(i + 1, j);
                    i = j + 1;
                    st.push(numerize(s));
                } else {
                    switch (ch[i]) {
                        case 'X': st.push(var[1]); break;
                        case 'Y': st.push(var[3]); break;
                        case 'Z': st.push(var[5]); break;
                        default: break;
                    }
                    i++;
                }
            }
            while (!st.isEmpty()){
                curr &= st.pop();
            }
            num |= curr;
            return num;
        }
    }


    public static void main(String[] args){
        int[] minterms = new int[]{5,6};
        int[] dcterms = new int[]{1,2,4};
        G gx = new G(minterms, dcterms);
        String[] eq = new String[]{"XYZ'+XY'Z", "Z'+XY'Z", "X(Y'+Z')", "Y'Z+XZ'+X'Z", "XZ'+X'Z"};

        for (String e : eq){
            F fx = new F(e);
            System.out.println(gx.contains(fx));
        }
    }
}
