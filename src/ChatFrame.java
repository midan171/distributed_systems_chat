/*
Μιχαήλ Δανούσης
321/2012046 
Συμέλα Ιγνατίδου
321/2012061

1η ομαδική Εργασία
στα Κατανεμημένα Συστήματα
*/
import static com.sun.javafx.fxml.expression.Expression.add;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author filos_filou
 */
public class ChatFrame extends JFrame implements ActionListener{
    private JPanel startPanel, panel1, panel2;
    private JPanel chatPanel;
    private JTextArea readArea;
    private JTextField writeF;
    private JButton send;
    private JLabel UserNameL;
    private JTextField UserNameTF;
    private JButton submit;
    private JLabel note;
    private Box box;
    private JFrame frame;
    private JFrame frame2;
    private JButton startChating;
    private Boolean hasMsg;
    private User user;
    private ClientConnection conn;
    private ChatRead CR;

    
   
   
    
    public ChatFrame()
    {
        
        setLayout(new FlowLayout());
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        
        startPanel=new JPanel();       
        UserNameL=new JLabel("Username: ");
        UserNameTF=new JTextField("Try a username");
        submit=new JButton("submit");
        submit.addActionListener(this);
        
        note=new JLabel();
        startPanel.add(UserNameL);
        startPanel.add(UserNameTF);
        startPanel.add(submit);
        startPanel.add(note);
        startPanel.setVisible(true);
        setContentPane(startPanel);
        setSize(300,100);
        hasMsg=false;
        
    }
    
    public void StartChatFrame()
    {
        chatPanel=new JPanel();
        panel1=new JPanel();
        panel2=new JPanel();
        chatPanel.setLayout(new GridLayout(2,1));
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());

        readArea=new JTextArea(10,30);
        readArea.setEditable(false);
        
        
        writeF=new JTextField(24);
        send=new JButton("send");
        send.addActionListener(this);
        send.setEnabled(false);
        startChating=new JButton("start!");
        startChating.addActionListener(this);
        
        panel1.add(readArea);
        panel2.add(writeF);
        panel2.add(send);
        panel2.add(startChating);
        panel1.setVisible(true);
        panel2.setVisible(true);
        chatPanel.add(panel1);
        chatPanel.add(panel2);
        
        getContentPane().removeAll();
        getContentPane().invalidate();
        getContentPane().add(chatPanel);
        getContentPane().revalidate();
        setBackground(Color.CYAN);
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        validate();
    }
        
    public User getUser(){return user;}
    public JTextArea getReadArea(){return readArea;}        

    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source.equals(submit))
        {
            System.out.println("SUBMITTTTTTTTTTTTTTTTT");
            user=new User();
            user.setName(UserNameTF.getText());
            System.out.println(user);
            conn=new ClientConnection();
            Boolean accept=!conn.CheckUserName(user);
            if (accept==true)
            {
                System.out.println("Name accepted...");
                StartChatFrame();
                               
            }
                
            else
            {
                
                note.setText("This username exists, please try another one");
                note.setVisible(true);
            }
                
             
        }
        else if(source.equals(startChating))
        {
            
            CR=new ChatRead(conn,this);
            CR.start();
            startChating.setEnabled(false);
            send.setEnabled(true);
            
        }
        else if(source.equals(send))
        {
            System.out.println("send!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Message M=new Message(user,writeF.getText());
            conn.SendMsg(M);
            System.out.println("msg sended!");
            writeF.setText(null);
        }
        else if (source.equals(EXIT_ON_CLOSE))
        {
            System.out.println("send END!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Message M=new Message(user,"/END OF CONNECTION");
            conn.SendMsg(M);
            System.out.println("msg sended!");
            writeF.setText(null);
            send.setEnabled(false);
            startChating.setEnabled(true);
        }
    } 
        

    
    
}
