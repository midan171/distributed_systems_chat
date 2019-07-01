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
import java.util.ArrayList;


public class Client extends Thread {
    private Socket sock;
    private ObjectInputStream In;
    private ObjectOutputStream Out;
    private ArrayList<User> users;
    private ArrayList<Client> clients;
    private User user;
    private Message last_msg;
    private Boolean ready;
    private int timesRed;
    private Boolean stillConnected;
    
    public Client(Socket s,ArrayList<User> u,ArrayList<Client> c)
    {
        sock=s;
        users=new ArrayList<User>();
        users=u;
        clients=new ArrayList<Client>();
        clients=c;
        last_msg=null;
        ready=false;
        timesRed=0;
        
    }
    //suncronized
    public synchronized void SendToAll(Message m)
    {   
        last_msg=new Message();
        last_msg=m;
        ready=true;
    }
    public synchronized void  ReadAndSend(Message m) throws IOException, InterruptedException
    {       
        
        for(int i=0;i<clients.size();i++)
        {
            clients.get(i).sendMsg(m);
        }
        
    }
    
    public Message getIn() throws IOException, ClassNotFoundException
    {
        Message M=new Message();
        M=(Message)In.readObject();
        return M;
    }
    
    public User getUser(){return user;}
    
    public Boolean getReady(){return ready;}
    
    public Boolean getStillConnected(){return stillConnected;}
    
    public void SetstillConnected(Boolean sc)
    {
        stillConnected=sc;
    }
    public void sendMsg(Message m) throws IOException
    {
        Out.writeObject(m);
    }

    
        

    

    @Override
    public void run(){
        try{ 
                
                Out=new ObjectOutputStream(sock.getOutputStream());
                In=new ObjectInputStream(sock.getInputStream());
                System.out.println("In run");
                User newUser=new User();
                newUser=(User)In.readObject();
                Boolean found=false;
                int i=0;
                while(i<users.size() && !found)
                {
                    if(newUser.getName().equals(users.get(i).getName()))
                        found=true;
                    i++;
                }
                
                
                Out.writeObject(found);
                
                if(!found)
                {
                    user=new User();
                    user=newUser;
                    users.add(newUser);
                    ready=false;
                    stillConnected=true;
                    ClientListener CL=new ClientListener(this);
                    CL.start();
                    while(stillConnected)
                    {
                        
                        if(ready)
                        {
                            //ReadAndSend();
                            
                            //isws na prepei na kanw prwta write...                            
                        }
                        System.out.print("");
                        

                    }
                    users.remove(newUser);
                    clients.remove(this);
                       
                }
                
                
            sock.close();
            System.out.println("socket closed...");
        }
                
        catch(Exception e)
        {
            System.out.println("In Client... "+e);
        }
        
    }
}
