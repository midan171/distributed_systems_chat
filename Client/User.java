/*
Μιχαήλ Δανούσης
321/2012046 
Συμέλα Ιγνατίδου
321/2012061

1η ομαδική Εργασία
στα Κατανεμημένα Συστήματα
*/
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author filos_filou
 */
public class User implements Serializable{
    private String UserName;
    
    public User()
    {

    }
    public User(String un)
    {
        UserName=un;
    }
    
    public String getName(){return UserName;}

    public void setName(String n){UserName=n;}
            
}
