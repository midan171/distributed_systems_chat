/*
Μιχαήλ Δανούσης
321/2012046 
Συμέλα Ιγνατίδου
321/2012061

1η ομαδική Εργασία
στα Κατανεμημένα Συστήματα
*/
public class ChatRead extends Thread{
    private ClientConnection conn;
    private ChatFrame CF;
    
    public ChatRead(ClientConnection c,ChatFrame cf)
    {
        conn=c;
        CF=cf;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            Message M=new Message();
            M=conn.readMessage();
            CF.getReadArea().append(M.GetUser().getName()+": "+M.GetMsg());
            CF.getReadArea().append("\n");
            
            System.out.println("reading...");
        }
    }
}
