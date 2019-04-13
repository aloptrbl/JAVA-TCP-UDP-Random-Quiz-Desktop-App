import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class TCPServer {

	private JFrame frame;
	private JLabel labStatus;
	private JTextArea showFrame;
	int service_port = 3000;
	String result[];
	String cache;
	String name;
	double total = 0;
	boolean request;
	/**
	 * Launch the application.
	 */
	
	
	public static ServerSocket newSocket = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCPServer window = new TCPServer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	void startServer()
	{
		try
		{
		newSocket = new ServerSocket(service_port);
		labStatus.setText("ONLINE!!!");
		labStatus.setForeground(Color.BLUE);
		showFrame.setText("Waiting client to connect.....\n");
		JOptionPane.showMessageDialog(null,"Your TCP server is online!.","Server Up",JOptionPane.INFORMATION_MESSAGE);
        while(true)
        {
           final Socket r_client = newSocket.accept();
           showFrame.append("Receive request from"+ r_client.getInetAddress() + " and port " + r_client.getPort() + "\n");
           JOptionPane.showMessageDialog(null,"Someone is connected.","Start Connection",JOptionPane.INFORMATION_MESSAGE);
           Runnable r = new Runnable()
              {
                 @Override
                 public void run()
                 {
                	 doAct(r_client);
                 }
              };
           new Thread(r).start();
           resetData();
           showFrame.append("Connection idle.....\n");
           //JOptionPane.showMessageDialog(null,"Connection is end.","End Connection",JOptionPane.INFORMATION_MESSAGE);
        }
		}
		catch(IOException err)
		{
			JOptionPane.showMessageDialog(null,err, "IO Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	void resetData()
	{
		result = new String[0];
		total = 0;
		name = "";
		
	}
	
	
	 void doAct(Socket r_client) 
     {
		 try{
		 DataInputStream inFromClient = new DataInputStream(r_client.getInputStream());
         DataOutputStream outToClient = new DataOutputStream(r_client.getOutputStream());
         cache = inFromClient.readUTF();
         result = cache.split("\\|"); 
         getMarks();
		 outToClient.writeDouble(total);
         request = false;
		 do{
			 request = inFromClient.readBoolean();
		 }while(request != true);
         if (request = true)
         {
      	   download(r_client);
      	  resetData();
         }
        	 r_client.close();
      	   resetData();
         
         
		 }
		 catch(IOException err)
	        {
	           return;
	        }
     }
	 
	 void download(Socket sock)
		{
			String content = (result[0] + "\r\nyour total mark is:  " + String.valueOf(total).toString());
			try
			{
				//wat folder kat desktop btulkan path 
				File file = new File(result[0] + "s.txt");
				if (!file.exists())
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
				byte[] mybytearray = new byte[(int)file.length()];
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				bis.read(mybytearray, 0, mybytearray.length);
				OutputStream os = sock.getOutputStream();
				os.write(mybytearray, 0, mybytearray.length);
				os.flush();
				showFrame.append("Connection end.....\n");
			}
	 
			catch(IOException err)
			{
				JOptionPane.showMessageDialog(null,err, "IO Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	 void getMarks()
		{
		 try{
			 	if (result[1].toLowerCase().equals("keluhuran perlembagaan"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
			 	if(result[2].toLowerCase().equals("9"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
			 	if(result[3].toLowerCase().equals("komanwel")|result[3].toLowerCase().equals("commonwealth"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
			 	if(result[4].toLowerCase().equals("perang dunia kedua")|result[4].toLowerCase().equals("perang dunia ke-2"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
			 	if(result[5].toLowerCase().equals("dato dol said")|result[5].toLowerCase().equals("yamtuan antah")|(result[5].toLowerCase().equals("za'ba"))|result[5].toLowerCase().equals("dol said"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
			 	if(result[6].toLowerCase().equals("malaysia"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
			 	if(result[7].toLowerCase().equals("rusa"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
			 	if(result[8].toLowerCase().equals("tun abdul razak"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
			 	if(result[9].toLowerCase().equals("dato sri najib razak")|result[9].toLowerCase().equals("najib razak")|result[9].toLowerCase().equals("jibby")|result[9].toLowerCase().equals("mohd najib bin abdul razak"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
			 	if(result[10].toLowerCase().equals("edwin eugene aldrin")|result[10].toLowerCase().equals("buzz aldrin"))
			 	{
			 		total = total + 1;
			 	}else
			 	{
			 		total = total + 0;
			 	}
			 	
		 }
					catch(Exception cc)
					{
						
					}
		 
		 
		
				
	}
	 public double calMark()
		{
			return total;
			
		}
	 /**
	 * Create the application.
	 */
	public TCPServer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		showFrame = new JTextArea();
		showFrame.setBounds(10, 11, 266, 240);
		desktopPane.add(showFrame);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnNewButton.setEnabled(false);
				startServer();
			}
		});
		btnNewButton.setBounds(308, 191, 89, 23);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				//btnNewButton_1.registerKeyboardAction(this, KeyStroke.getKeyStroke('C', KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED); 
			}

			
		});
		btnNewButton_1.setBounds(308, 225, 89, 23);
		desktopPane.add(btnNewButton_1);
		
		JLabel lblTcpServer = new JLabel("TCP SERVER");
		lblTcpServer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTcpServer.setBounds(286, 28, 116, 14);
		desktopPane.add(lblTcpServer);
		
		labStatus = new JLabel("OFFLINE");
		labStatus.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		labStatus.setForeground(Color.RED);
		labStatus.setBounds(309, 102, 115, 14);
		desktopPane.add(labStatus);
	}
}
