/*
Μιχαήλ Δανούσης
321/2012046 
Συμέλα Ιγνατίδου
321/2012061

1η ομαδική Εργασία
στα Κατανεμημένα Συστήματα
*/
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientConnection {
    private User user;
    private Socket sock;
    private ObjectInputStream In;
    private ObjectOutputStream Out;
    
    public ClientConnection()
    {
        Boolean flag=true;
        user=new User();
        
            try
            {
                sock = new Socket("127.0.0.1", 1234);
                System.out.println("Connected to server!!");
                In=new ObjectInputStream(sock.getInputStream());
                Out=new ObjectOutputStream(sock.getOutputStream());
                
                System.out.println();
                System.out.println("Connecting to "+sock.getInetAddress()+ " and port" +sock.getPort());
                System.out.println("Local Address :"+sock.getLocalAddress()+" Port :"+sock.getLocalPort());

                
                    
               
            }
            catch(Exception e)
            {
                System.out.println("Cl Conn error: "+e);
            }

        
        
    }
    public Boolean CheckUserName(User u)
    {
        Boolean b=false;
        try{
            Out.writeObject(u);
            b=(Boolean)In.readObject();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        if(!b)
            user=u;
        
        
        return b;
    }
    public void SendMsg(Message msg)
    {
        try{
            
            Out.writeObject(msg);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    public Message readMessage()
    {
        Message m=new Message();
        try{
            m=(Message)In.readObject();
        }
        
        catch(Exception e)
        {
            System.out.println(e);
        }
            
                
        return m;
    }
    
}
