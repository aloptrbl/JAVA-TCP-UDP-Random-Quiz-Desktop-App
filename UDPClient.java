import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SecondaryLoop;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.AbstractButton;
import java.io.InputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;

public class UDPClient {

	String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,end;
	String q1jawapanA,q1jawapanB,q1jawapanC,q2jawapanA,q2jawapanB,q2jawapanC,q3jawapanA,q3jawapanB,q3jawapanC,q4jawapanA,q4jawapanB,q4jawapanC,q5jawapanA,q5jawapanB,q5jawapanC,q6jawapanA,q6jawapanB,q6jawapanC,q7jawapanA,q7jawapanB,q7jawapanC,q8jawapanA,q8jawapanB,q8jawapanC,q9jawapanA,q9jawapanB,q9jawapanC,q10jawapanA,q10jawapanB,q10jawapanC;
	String jawapan;
	private JRadioButton radio1,radio2,radio3;
	private JFrame frame;
	private JButton btnNext;
	private JTextField textField;
	private JLabel lblSoalan;
	private JLabel lblSoalan1;
	private JLabel lblSoalan2;
	Socket clientSocket;
	DataOutputStream outToServer;
	DataInputStream inFromServer;
	private JTextField textField_1;
	private JLabel lblNickName;
	private JButton btnDownload;
	private JButton btnConfirm;
	private JButton btnExit;
	private ButtonGroup group; 
	JDesktopPane desktopPane = new JDesktopPane();
	byte[] outb = new byte[8192];
	byte[] inb = new byte[8192];
	ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
	DataOutputStream dataOut = new DataOutputStream(byteOut);
	DatagramSocket socket;
	DatagramPacket inpkt;
	DatagramPacket opkt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDPClient window = new UDPClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void sentPacketString(String a)
	{
		try
		{
			dataOut.writeUTF(a);
			dataOut.flush();
			outb = byteOut.toByteArray();
		}
		catch(Exception ioerr)
		{
			JOptionPane.showMessageDialog(null,ioerr,"IO Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	void sentPacketBoolean(boolean request)
	{
		try
		{
			dataOut.writeBoolean(request);
			dataOut.flush();
			outb = byteOut.toByteArray();
		}
		catch(Exception ioerr)
		{
			JOptionPane.showMessageDialog(null,ioerr,"IO Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void sentPacket()
	{
		try{
			socket = new DatagramSocket();
			InetAddress addr = InetAddress.getByName("localhost");

			boolean stats = false;
			double marks;

			inpkt = new DatagramPacket(inb, inb.length);
			opkt = new DatagramPacket(outb, outb.length, addr, 3000);

			ByteArrayInputStream byteIn = new ByteArrayInputStream(inpkt.getData());
			DataInputStream dataIn = new DataInputStream(byteIn);

			sentPacketString(jawapan);
			opkt.setData(outb);
			socket.send(opkt);
			socket.receive(inpkt);
			marks = dataIn.readDouble();
			lblSoalan.setText(String.valueOf(marks).toString());
			lblSoalan.setForeground(Color.red);
			lblSoalan.setFont(new Font("Serif", Font.BOLD, 30));
		} catch(SocketException sock) {
			JOptionPane.showMessageDialog(null,
					sock,
				    "Socket Error",
				    JOptionPane.ERROR_MESSAGE);
		} catch(IOException ioerr) {
			JOptionPane.showMessageDialog(null,
					ioerr,
				    "IO Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
	}

	void soalan(){
		
	q1 = "Apakah rukun ke-3 dalam Rukun Negara?";
	q1jawapanA = "keluhuran perlembagaan";
	q1jawapanB = "kedaulatan undang undang";
	q1jawapanC = "kesopanan dan kesusilaan";
	
	q2 = "<html>Terdapat berapa negeri di Malaysia yang <br>mempunyai Raja atau Sultan?</html>";
	q2jawapanA = "12";
	q2jawapanB = "8";
	q2jawapanC = "9";
	
	q3 = "<html>Malaysia berada dibawah naungan British <br>di dalam pertubuhan negara-negara?</html>";
	q3jawapanA = "komanwel";
	q3jawapanB = "ASEAN";
	q3jawapanC = "PBB";
	
	q4 = "Pada perang apakah Malaysia dijajah oleh Jepun?";
	q4jawapanA = "perang dunia ke-1";
	q4jawapanB = "perang dunia ke-2";
	q4jawapanC = "perang dunia ke-3";
	
	q5 = "<html>Siapakah di antara berikut merupakan pejuang <br>kemerdekaan Negeri Sembilan?</html>";
	q5jawapanA = "tun razak";
	q5jawapanB = "tok gajah";
	q5jawapanC = "dol said";
	
	q6 = "<html>Di negara manakah Sukan Komenwel(SUKOM) <br>1988 telah diadakan?</html>";
	q6jawapanA = "malaysia";
	q6jawapanB = "thailand";
	q6jawapanC = "japan";
	
	q7 = "Apakah kereta kedua keluaran Perodua";
	q7jawapanA = "rusa";
	q7jawapanB = "kijang";
	q7jawapanC = "harimau";
	
	q8 = "<html>Siapakah Perdana Menteri yang telah menubuhkan <br>Felda?</html>";
	q8jawapanA = "najib abdul razak";
	q8jawapanB = "tun abdul rahman";
	q8jawapanC = "tun abdul razak";
	
	q9 = "<html>Siapakah individu yang telah mendapat <br>derma sumbangan terbanyak di Malaysia?</html>";
	q9jawapanA = "mahathir mohamad";
	q9jawapanB = "najib razak";
	q9jawapanC = "rahman dahlan";
	
	q10 = "<html>Siapakah angkasawan kedua yang telah <br>menjejakkan kaki ke bulan?</html>";
	q10jawapanA = "edward vinxen";
	q10jawapanB = "buzz aldrin";
	q10jawapanC = "muffin";
	end = "<html>Quiz is end. Please click confirm <br>to show your mark and click <br>download to download your marks.</html>";
	if (lblSoalan.getText().isEmpty())
	{
		lblSoalan.setText(q1);
		radio1.setText(q1jawapanA);
		radio2.setText(q1jawapanB);
		radio3.setText(q1jawapanC);

	    ActionListener sliceActionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        AbstractButton aButton = (AbstractButton) actionEvent.getSource();
	        System.out.println("Selected: " + aButton.getText());
	        textField.setText(aButton.getText());
	      }
	    };
	    radio1.addActionListener(sliceActionListener);
	    radio2.addActionListener(sliceActionListener);
	    radio3.addActionListener(sliceActionListener);
	    
	}else if (lblSoalan.getText().equals(q1))
	{
		lblSoalan.setText(q2);
		textField_1.setEnabled(false);
		radio1.setText(q2jawapanA);
		radio2.setText(q2jawapanB);
		radio3.setText(q2jawapanC);

	}else if (lblSoalan.getText().equals(q2))
	{
		lblSoalan.setText(q3);
		radio1.setText(q3jawapanA);
		radio2.setText(q3jawapanB);
		radio3.setText(q3jawapanC);
	}else if (lblSoalan.getText().equals(q3))
	{
		lblSoalan.setText(q4);
		radio1.setText(q4jawapanA);
		radio2.setText(q4jawapanB);
		radio3.setText(q4jawapanC);
		textField_1.setEnabled(false);
	}else if (lblSoalan.getText().equals(q4))
	{
		lblSoalan.setText(q5);
		radio1.setText(q5jawapanA);
		radio2.setText(q5jawapanB);
		radio3.setText(q5jawapanC);
		textField_1.setEnabled(false);
	}else if (lblSoalan.getText().equals(q5))
	{
		lblSoalan.setText(q6);
		radio1.setText(q6jawapanA);
		radio2.setText(q6jawapanB);
		radio3.setText(q6jawapanC);
		textField_1.setEnabled(false);
	}else if (lblSoalan.getText().equals(q6))
	{
		lblSoalan.setText(q7);
		radio1.setText(q7jawapanA);
		radio2.setText(q7jawapanB);
		radio3.setText(q7jawapanC);
		textField_1.setEnabled(false);
	}else if (lblSoalan.getText().equals(q7))
	{
		lblSoalan.setText(q8);
		radio1.setText(q8jawapanA);
		radio2.setText(q8jawapanB);
		radio3.setText(q8jawapanC);
		textField_1.setEnabled(false);
	}else if (lblSoalan.getText().equals(q8))
	{
		lblSoalan.setText(q9);
		radio1.setText(q9jawapanA);
		radio2.setText(q9jawapanB);
		radio3.setText(q9jawapanC);
		textField_1.setEnabled(false);
	}else if (lblSoalan.getText().equals(q9))
	{
		lblSoalan.setText(q10);
		radio1.setText(q10jawapanA);
		radio2.setText(q10jawapanB);
		radio3.setText(q10jawapanC);
		textField_1.setEnabled(false);
	}else
	{
		lblSoalan.setText(end);
		btnNext.setEnabled(false);
		btnConfirm.setEnabled(true);
		btnDownload.setEnabled(true);
		lblSoalan1.setText("");
		textField_1.setEnabled(false);
		radio1.setVisible(false);
		radio2.setVisible(false);
		radio3.setVisible(false);
	}
	
	
}

		void jawapan()
	{
		try
		{
		if(jawapan==null)
		{
			jawapan = textField_1.getText() + "|" + textField.getText() + "|";
		}else
		{
			jawapan = jawapan + textField.getText() + "|";
		}
		}
		catch(Exception err)
		{
			JOptionPane.showMessageDialog(null,err,"WARNING !!!",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	void download()
	{
		try
		{
			sentPacketBoolean(true);
			opkt.setData(outb);
			socket.send(opkt);

			File file = new File(textField_1.getText() +"c.txt");
			PrintStream fw = new PrintStream(file);
			byte[] receiveData = new byte[8192];

			for (int i = 0; i < 1; i++) {
            	
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                fw.println(sentence.trim());
                fw.flush();
                i++;
                }
            fw.flush();
            fw.close();
            socket.close();
		    JOptionPane.showMessageDialog(null,"Succesful download", "Download", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(IOException err)
		{
			JOptionPane.showMessageDialog(null,err, "IO Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Create the application.
	 */
	public UDPClient() {
		initialize();
		soalan();
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
		
		btnNext = new JButton("NEXT");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().isEmpty())
					{
					JOptionPane.showMessageDialog(null,"ANDA BELUM MENJAWAB SOALAN.","WARNING !!!",JOptionPane.INFORMATION_MESSAGE);
					}else if(textField_1.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"ANDA BELUM MASUKKAN NICKNAME ANDA.","WARNING !!!",JOptionPane.INFORMATION_MESSAGE);

					}
				
				else{
					jawapan();
					//JOptionPane.showMessageDialog(null,jawapan,"WARNING !!!",JOptionPane.INFORMATION_MESSAGE);
					textField.setText("");
					soalan();
				}
			}
		});
		btnNext.setBounds(111, 196, 89, 23);
		desktopPane.add(btnNext);
		
		btnExit = new JButton("FINISH");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{socket.close();}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err, "IO Error", JOptionPane.ERROR_MESSAGE);
				}
				System.exit(0);
			}
		});
		btnExit.setBounds(225, 196, 89, 23);
		desktopPane.add(btnExit);
		
		lblSoalan1 = new JLabel("SOALAN :");
		lblSoalan1.setBounds(10, 24, 68, 14);
		desktopPane.add(lblSoalan1);
		
		radio1 = new JRadioButton();
		radio1.setBounds(100, 131, 200, 14);
		desktopPane.add(radio1);
		
		radio2 = new JRadioButton();
		radio2.setBounds(100, 151, 200, 14);
		desktopPane.add(radio2);
		
		radio3 = new JRadioButton("Item 3");
		radio3.setBounds(100, 171, 200, 14);
		desktopPane.add(radio3);

		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
	    group.add(radio2);
	    group.add(radio3);
		
		textField = new JTextField();
		textField.setBounds(46, 156, 323, 29);
		textField.setVisible(false);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblYourAnswer = new JLabel("JAWAPAN :");
		lblYourAnswer.setBounds(10, 131, 112, 14);
		desktopPane.add(lblYourAnswer);
		
		lblSoalan = new JLabel("");
		lblSoalan.setBounds(57, 47, 312, 73);
		desktopPane.add(lblSoalan);
		
		JLabel lblTcpClient = new JLabel("UDP CLIENT");
		lblTcpClient.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTcpClient.setBounds(10, -1, 101, 23);
		desktopPane.add(lblTcpClient);
		
		btnConfirm = new JButton("CONFIRM");
		btnConfirm.setEnabled(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sentPacket();
				/*download();
				lblSoalan.setText(q1);
				textField_1.setEnabled(true);
				lblSoalan1.setText("SOALAN :");
				btnNext.setEnabled(true);
				btnExit.setEnabled(true);
				btnConfirm.setEnabled(false);
				btnDownload.setEnabled(false);
				jawapan = null;*/
			}
		});
		btnConfirm.setBounds(111, 230, 89, 23);
		desktopPane.add(btnConfirm);
		
		textField_1 = new JTextField();
		textField_1.setBounds(224, 1, 200, 20);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNickName = new JLabel("nick name :");
		lblNickName.setBounds(154, 4, 78, 14);
		desktopPane.add(lblNickName);
		
		btnDownload = new JButton("DOWNLOAD");
		btnDownload.setEnabled(false);
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				download();
				lblSoalan.setText(q1);
				textField_1.setEnabled(true);
				textField_1.setText("");
				lblSoalan.setForeground(Color.black);
				lblSoalan.setFont(new Font("Serif", Font.PLAIN, 15));
				lblSoalan1.setText("SOALAN :");
				btnNext.setEnabled(true);
				btnExit.setEnabled(true);
				btnConfirm.setEnabled(false);
				btnDownload.setEnabled(false);
				radio1.setVisible(true);
				radio2.setVisible(true);
				radio3.setVisible(true);
				jawapan = null;
				
				
				
			}
		});
		btnDownload.setBounds(225, 230, 101, 23);
		desktopPane.add(btnDownload);
	}
}