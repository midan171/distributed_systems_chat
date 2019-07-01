/*
Μιχαήλ Δανούσης
321/2012046 
Συμέλα Ιγνατίδου
321/2012061

1η ομαδική Εργασία
στα Κατανεμημένα Συστήματα
*/
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author filos_filou
 */
public class ClientListener extends Thread{
    private Client client;
    
    public ClientListener(Client c)
    {
        client=c;
    }
    
    public void StartListening() throws IOException, ClassNotFoundException
    {
        try{
            while(client.getStillConnected())
            {
            System.out.println("Starting Listen");
            Message M=new Message();
            M=client.getIn();
            client.ReadAndSend(M);
            System.out.println("!");
            if(M.GetMsg().equals("/END OF CONNECTION"))
            {
                client.SetstillConnected(false);
            }
            
            }
        }
        catch(Exception e)
        {
            System.out.println("In listener"+e);
            Message M=new Message(client.getUser(),"DISCONNECTED");
            client.SendToAll(M);
            client.SetstillConnected(false);
        }
        
    }
    @Override
    public void run()
    {
        try {
            StartListening();
        } catch (IOException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
