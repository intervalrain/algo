package temp;
import DioUtility.Generic.TreeNode;

public class TreeNodeDriver {
    public static void main(String[] args){
        String[] words = new String[]{"Hello", "Ni", "How", "Ma", "Jong", "sin", "gan", "hsie", "Jen", "Jong"};
        TreeNode<String> root = new TreeNode<>(words);
        System.out.println(root.toString());
    }
}
