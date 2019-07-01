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
public class Message implements Serializable{
    private User user;
    private String msg;
    public Message ()
    {
        
    }
    public Message(User u,String s)
    {
        user=u;
        msg=s;
    }
    public String GetMsg(){return msg;}
    public User GetUser(){return user;}
}
