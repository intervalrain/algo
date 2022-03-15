package temp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TransientExample {
    
    public static void main(String[] args){
        User user = new User();
        user.setUsername("Rain");
        user.setPassword("12345678");

        System.out.println("Read before Serializable: ");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());

        try {
            ObjectOutput os = new ObjectOutputStream(new FileOutputStream("/Users/rainhu/workspace/algo/temp/user.txt"));
            os.writeObject(user);
            os.flush();
            os.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("/Users/rainhu/workspace/algo/temp/user.txt"));
            user = (User) is.readObject();
            is.close();

            System.out.println("Read after Serializable: ");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

class User implements Serializable{
    @java.io.Serial
    private static final long serialVersionID = 8294180014912103005L;
    
    private String username;
    private transient String password;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}
