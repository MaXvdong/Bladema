import javax.swing.*; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.Point; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
public class Admin extends JFrame{ 

	 
    java.sql.Connection con=null; 
    java.sql.ResultSet  rs=null; 
    java.sql.PreparedStatement ps=null; 
    JLabel label6 = new JLabel(new ImageIcon("image/��¼����.jpg")); 
 
	JTextField txtname=new JTextField(); 
	JPasswordField txtPwd = new JPasswordField(); 
 
	JButton but1 = new JButton("��¼"); 
	 
	JButton but2 = new JButton("�˳�"); 
	
	JButton but3 = new JButton("ע��");  
 
	JProgressBar jpb = new JProgressBar();// ������ 
 
 
 
	/*���췽�� */ 
	public Admin(){} 
	public Admin(int i,int num) { 
 
		initAdmin(); 
		this.setLocation(new Point(436,176));//�����ʼ��ָ��λ�� 
		this.setResizable(true);// ��ֹ�ı��С 
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		this.setDefaultCloseOperation(i); 
 
	} 
 
	/*������� */ 
	public void initAdmin() {
		
	
		this.setTitle("QuickQuiet ���"); 
		this.setSize(440, 338); 
		JLayeredPane pa = new JLayeredPane(); // ���� 
 
		// ��������������ɫ 

		txtname.setFont(new Font("����", 0, 15)); 
		txtPwd.setFont(new Font("����", 0, 15)); 
	    txtPwd.setEchoChar('*'); 
        txtname.setForeground(new Color(30, 85, 85)); 
		txtname.setEditable(true); 
		txtname.setText(""); 
 
		// �������λ�� 
	
		txtname.setBounds(130, 165, 195, 31); 
	    txtPwd.setBounds(130, 195, 195, 31); 
	    but1.setBounds(131, 258, 195, 30); 
		but2.setBounds(330, 195, 65, 30);
		but3.setBounds(330, 165, 65, 30);
 
		 
		// ������������ 

 
		pa.add(txtname); 
		pa.add(txtPwd); 
		pa.add(but1); 
		pa.add(but2); 
		pa.add(but3);
		this.add(pa);// ������� 
		 
		jpb.setBounds(0, 258, 430, 40); 
		jpb.setForeground(new Color(250, 40, 135)); 
		jpb.setVisible(false); 
		pa.add(jpb); 
		label6.setBounds(0, 0, 425, 300); 
		pa.add(label6); 
		 
		 
		 
		but1.addActionListener(new ActionListener(){ 
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) { 
				UserInformation abc=new UserInformation();
			    String s2;
			    s2=txtname.getText().trim()+txtPwd.getText().trim();
				if(txtname.getText().trim().length()==0){ 
					JOptionPane.showMessageDialog(null, "�������û���!", "����", 0); 
					return; 
				} 
				 
				if(txtPwd.getText().trim().length()==0) 
				{ 
					JOptionPane.showMessageDialog(null, "����������!", "����", 0); 
					return;	 
					 
				} 
				if(txtPwd.getText().trim().length()>6){ 
					JOptionPane.showMessageDialog(null, "����̫��!", "����", 0); 
					return; 
					 
				} 
				
                if(abc.readtofile(s2)==0){
                	JOptionPane.showMessageDialog(null,"���������˺Ų����ڣ�","����",0);
                }
				if(abc.readtofile(s2)!=0){ 
					 
					//��ʾ������ 
					jpb.setVisible(true); 
					 
					yin();//������ 
					JOptionPane.showMessageDialog(null, "��¼�ɹ�"); 
						} 
					  	
			} 
		} 
					  	 
 
	 
 
		 
		); 
		 
	but2.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent arg0) { 
			// TODO �Զ����ɷ������ 
			int a = JOptionPane.showConfirmDialog(null, "�Ƿ��˳�", "����", 0); 
			if (a == 0) { 
				dispose(); 
			} else { 
				return; 
			} 
		} 
	}
	
	);
	but3.addActionListener(new ActionListener(){
		@SuppressWarnings("deprecation")
	
		public void actionPerformed(ActionEvent arg0){
		   UserInformation abc=new UserInformation();
			String s1;		    
		    s1=txtname.getText().trim()+txtPwd.getText().trim();
			JOptionPane.showMessageDialog(null,"ע���˻����¼���ɣ�");
			if(txtname.getText().trim().length()==0){
				JOptionPane.showMessageDialog(null, "�������˺�!", "����", 0); 
				return;	 
			}
			if(txtPwd.getText().trim().length()==0){
				JOptionPane.showMessageDialog(null, "����������!", "����", 0); 
				return;	 
		    }
		 abc.writetofile(s1);
		 return;
	   }	
	}
	);
	}
	 
	 
	/*������ */ 
	public void yin() { 
		this.setEnabled(false);// ���� 
	 
		new java.lang.Thread(new java.lang.Runnable() { 
			public void run() { 
				while (true) { 
					try { 
						int i = Admin.this.jpb.getValue(); 
 
						if (i < Admin.this.jpb.getMaximum()) { 
							int k = ++i; 
							Admin.this.jpb.setValue(k); 
							Admin.this.jpb.setStringPainted(true); 
 
							if (k <= 25) { 
								java.lang.Thread.sleep(100);  
								Admin.this.jpb.setString("��������ϵͳ......" + 4 * k+ "%"); 
							} else if (k <= 75) { 
								java.lang.Thread.sleep(80);  
								Admin.this.jpb.setString("��������......" + k + "%"); 
							} else { 
								java.lang.Thread.sleep(30);  
								Admin.this.jpb.setString("���ڻ���,���Ժ�......" + k+ "%"); 
							} 
						} else { 
							Admin.this.dispose(); 
						} 
					} catch (Exception e) { 
						e.printStackTrace(); 
					} 
					// �ɹ����� 
					if (Admin.this.jpb.getValue() == 100) { 
						Admin.this.jpb.setString("���100%"); 
	 
						new Admin().setVisible(true); 
						Admin.this.dispose();
						TCPClient tc = new TCPClient();
						tc.launchFrame();
					
						break; 
					} 
				} 
			} 
		}).start(); 
	} 
 
	 
    public static void main(String[] args) 
    { 
	     new Admin(0,0).setVisible(true); 
		
    } 
 
	
}


