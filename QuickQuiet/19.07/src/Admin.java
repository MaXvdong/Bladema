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
    JLabel label6 = new JLabel(new ImageIcon("image/登录界面.jpg")); 
 
	JTextField txtname=new JTextField(); 
	JPasswordField txtPwd = new JPasswordField(); 
 
	JButton but1 = new JButton("登录"); 
	 
	JButton but2 = new JButton("退出"); 
	
	JButton but3 = new JButton("注册");  
 
	JProgressBar jpb = new JProgressBar();// 进度条 
 
 
 
	/*构造方法 */ 
	public Admin(){} 
	public Admin(int i,int num) { 
 
		initAdmin(); 
		this.setLocation(new Point(436,176));//界面初始化指定位置 
		this.setResizable(true);// 禁止改变大小 
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		this.setDefaultCloseOperation(i); 
 
	} 
 
	/*界面代码 */ 
	public void initAdmin() {
		
	
		this.setTitle("QuickQuiet 快客"); 
		this.setSize(440, 338); 
		JLayeredPane pa = new JLayeredPane(); // 容器 
 
		// 设置组件字体和颜色 

		txtname.setFont(new Font("宋体", 0, 15)); 
		txtPwd.setFont(new Font("宋体", 0, 15)); 
	    txtPwd.setEchoChar('*'); 
        txtname.setForeground(new Color(30, 85, 85)); 
		txtname.setEditable(true); 
		txtname.setText(""); 
 
		// 设置组件位置 
	
		txtname.setBounds(130, 165, 195, 31); 
	    txtPwd.setBounds(130, 195, 195, 31); 
	    but1.setBounds(131, 258, 195, 30); 
		but2.setBounds(330, 195, 65, 30);
		but3.setBounds(330, 165, 65, 30);
 
		 
		// 添加组件到容器 

 
		pa.add(txtname); 
		pa.add(txtPwd); 
		pa.add(but1); 
		pa.add(but2); 
		pa.add(but3);
		this.add(pa);// 添加容器 
		 
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
					JOptionPane.showMessageDialog(null, "请填入用户名!", "警告", 0); 
					return; 
				} 
				 
				if(txtPwd.getText().trim().length()==0) 
				{ 
					JOptionPane.showMessageDialog(null, "请填入密码!", "警告", 0); 
					return;	 
					 
				} 
				if(txtPwd.getText().trim().length()>6){ 
					JOptionPane.showMessageDialog(null, "密码太长!", "警告", 0); 
					return; 
					 
				} 
				
                if(abc.readtofile(s2)==0){
                	JOptionPane.showMessageDialog(null,"密码错误或账号不存在！","警告",0);
                }
				if(abc.readtofile(s2)!=0){ 
					 
					//显示进度条 
					jpb.setVisible(true); 
					 
					yin();//进度条 
					JOptionPane.showMessageDialog(null, "登录成功"); 
						} 
					  	
			} 
		} 
					  	 
 
	 
 
		 
		); 
		 
	but2.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent arg0) { 
			// TODO 自动生成方法存根 
			int a = JOptionPane.showConfirmDialog(null, "是否退出", "警告", 0); 
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
			JOptionPane.showMessageDialog(null,"注册账户后登录即可！");
			if(txtname.getText().trim().length()==0){
				JOptionPane.showMessageDialog(null, "请填入账号!", "警告", 0); 
				return;	 
			}
			if(txtPwd.getText().trim().length()==0){
				JOptionPane.showMessageDialog(null, "请填入密码!", "警告", 0); 
				return;	 
		    }
		 abc.writetofile(s1);
		 return;
	   }	
	}
	);
	}
	 
	 
	/*进度条 */ 
	public void yin() { 
		this.setEnabled(false);// 锁屏 
	 
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
								Admin.this.jpb.setString("正在载入系统......" + 4 * k+ "%"); 
							} else if (k <= 75) { 
								java.lang.Thread.sleep(80);  
								Admin.this.jpb.setString("加载资料......" + k + "%"); 
							} else { 
								java.lang.Thread.sleep(30);  
								Admin.this.jpb.setString("正在缓冲,请稍后......" + k+ "%"); 
							} 
						} else { 
							Admin.this.dispose(); 
						} 
					} catch (Exception e) { 
						e.printStackTrace(); 
					} 
					// 成功登入 
					if (Admin.this.jpb.getValue() == 100) { 
						Admin.this.jpb.setString("完成100%"); 
	 
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


