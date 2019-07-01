/*
Μιχαήλ Δανούσης
321/2012046 
Συμέλα Ιγνατίδου
321/2012061

1η ομαδική Εργασία
στα Κατανεμημένα Συστήματα
*/
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//Starts the server that distributes the messages across
//all active clients
public class ServerMain extends Thread{

    private Socket sock;
    private ArrayList<User> users;
    private ArrayList<Client> clients;
    private Message last_msg;
    private Boolean ready;
    private int timesRed;

    
    public ServerMain(Socket s,ArrayList<User> u)
    {
        sock=s;
        users=new ArrayList<User>();
        clients=new ArrayList<Client>();
        users=u;
        last_msg=null;
        ready=false;
        timesRed=0;
        
        
    }
    public Message getLast_msg(){return last_msg;}
    public void setLast_msg(Message M){last_msg=M;}
    public Boolean getReady(){return ready;}
    public void setReady(Boolean b){ready=b;}
    public int getTimesRed(){return timesRed;}
    public void setTimesRed(int num){timesRed=num;}

        public static void main(String args[]) 
        throws Exception {
            ServerSocket ServerSock = new ServerSocket(1234);
            ArrayList<User> USERS=new ArrayList<User>();
            ArrayList<Client> CLIENTS=new ArrayList<Client>();
            Message LM=new Message();
            
            while (true) {
                try{
                    System.out.println("Server is Waiting");
                    Socket sock = ServerSock.accept();
                    System.out.println("Connected");
                    //new Thread(new ServerMain(sock,USERS)).start();
                
                    Client c= new Client(sock,USERS,CLIENTS);
                    c.start();
                    CLIENTS.add(c);
                    for(int i=0;i<CLIENTS.size();i++)
                    {
                        
                            
                            
                    }
                    for(int i=0;i<CLIENTS.size();i++)
                    {
                        CLIENTS.get(i).SendToAll(LM);
                    }
                    
                    
                }
                catch(Exception e)
                {
                    System.out.println("In serverMain"+e);
                }
                
                
            }
        }
        
    
}
