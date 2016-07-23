import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.MediaTracker;
public class MyCalci implements Runnable,MouseListener , KeyListener {
	JButton b[],b2,clr,clral;
	JLabel jl;
	JTextField tf1;
	char prevchar='a';
	Double value=(double) 0,value1=(double) 0;
	int point=0;
	
	public void run()
	{
	}
	MyCalci(){
		
		JFrame f =new JFrame();
		JPanel p =new JPanel();
		JPanel lp = new JPanel();
		
		lp.setLayout(null);
		p.setLayout(null);
		f.add(lp);
		
		jl = new JLabel();
		ImageIcon image = new ImageIcon("icon2.png");
		jl.setIcon(image);
		
		
		Font font = new Font("Cooper Std",Font.BOLD,18);
		clral = new JButton("CLEAR ALL");
		clral.setBorder(null);
		clral.setBackground(Color.GRAY);
		clral.addMouseListener(this);
		clral.setEnabled(false);
		clral.setFont(font);
		clral.setBounds(250,180,110,40);
		clr = new JButton("CE");
		clr.setBorder(null);
		clr.setBackground(Color.GRAY);
		clr.addMouseListener(this);
		clr.setEnabled(false);
		clr.setFont(font);
		clr.setBounds(200,180,40,40);
		b2= new JButton(" ");
		b2.setBounds(350,10,10,10);
		b2.setBackground(Color.red);
		b2.setBorder(null);
		b= new JButton[20];
		String str[] ={"ON","√","x²","1/x","1","2","3","+","4","5","6","-","7","8","9","*",".","0","=","/"};
		for(int i=0;i<str.length;i++)
		{
			b[i] = new JButton(str[i]);
			b[i].setFont(font);
			b[i].setBounds(90*(i%4)+10,(((i/4)+5)*50), 80, 40);
			b[i].setBorder(null);
			b[i].setBackground(Color.GRAY);
			b[i].addMouseListener(this);
			b[i].setEnabled(false);
			p.add(b[i]);
		}
		b[0].setEnabled(true);
		//Text Field

		tf1 = new JTextField();
		tf1.setBounds(10, 100, 360, 60);
		Font font1 = new Font("Cooper Std",Font.BOLD,28);
		tf1.setFont(font1);
		tf1.setEditable(false);
		tf1.setHorizontalAlignment(SwingConstants.RIGHT);
		f.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        tf1.requestFocusInWindow();
		    }
		});
		tf1.setBackground(Color.white);tf1.addKeyListener(this);
		p.add(tf1);p.add(b2);p.add(clr);p.add(clral);lp.add(jl);
		f.setSize(400, 600);
		f.setTitle("Calculator");
		f.setVisible(true);
		f.setResizable(false);
		
		Image image2 = image.getImage();
		f.setIconImage(image2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		////Effects
		jl.setBounds(-50, 0, 512, 512);
		try{
			Thread.sleep(500);
		}
		catch(InterruptedException ie){}
		for(int i=-50;i<400;i++)
		{
			try{
				Thread.sleep(1);
			}
			catch(InterruptedException ie){}
			jl.setBounds(i,0,512,512);
			
		}
		lp.setVisible(false);
		f.add(p);
		p.setVisible(true);

	}
	public void mouseEntered(MouseEvent ml ){
		
		for(int i=0;i<b.length;i++)
		{
			if(ml.getSource()==b[i] && b[i].isEnabled())
			{
				b[i].setBackground(Color.WHITE);
			}
		}
		if(ml.getSource()==clr && clr.isEnabled()) 
			clr.setBackground(Color.WHITE);
	}
	public void mouseExited(MouseEvent ml ){
		for(int i=0;i<b.length;i++)
		{
			if(ml.getSource()==b[i])
			{
				b[i].setBackground(Color.GRAY);
			}
		}
		if(ml.getSource()==clr) 
			clr.setBackground(Color.GRAY);
		if(ml.getSource()==clral)
			clral.setBackground(Color.GRAY);
	}
	public void mouseClicked(MouseEvent ml ){
		tf1.requestFocus();
		if(ml.getSource()==b[0] && b[0].getText().equals("ON"))
		{
			for(int i=1;i<b.length;i++)
			{
				b[i].setEnabled(true);
			}
			clr.setEnabled(true);
			clral.setEnabled(true);
			b[0].setText("OFF");
			b2.setBackground(Color.GREEN);
			tf1.setBackground(Color.BLACK);
			tf1.setForeground(Color.WHITE);
		}
		else if(ml.getSource()==b[0] && b[0].getText().equals("OFF"))
		{
			for(int i=1;i<b.length;i++)
			{
				b[i].setEnabled(false);
			}
			b[0].setText("ON");
			value=(double)0;
			value1=(double)0;
			tf1.setText("");
			clr.setEnabled(false);
			clral.setEnabled(false);
			b2.setBackground(Color.red);
			tf1.setBackground(Color.white);
			tf1.setForeground(Color.BLACK);
		}
		else
		{
			for(int i=1;i<b.length;i++)
			{
				if(ml.getSource()==b[i])
				{
					if(b[i].getText().equals("1/x"))
						calculate('r');
					else if(b[i].getText().equals("√"))
						calculate('s');
					else if(b[i].getText().equals("x²"))
						calculate('p');
					else
					{
						calculate(b[i].getText().charAt(0));
					}
						
				}
			}
			if(ml.getSource()==clr && clr.isEnabled())
				calculate('\b');
			if(ml.getSource()==clral && clral.isEnabled())
			{
				value=(double)0;
				value1=(double)0;
				tf1.setText("");
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		calculate(c);
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	void calculate(char c)
	{
		int i=(int)c;
		if(i==10)
			c='=';
		System.out.println(i);
		if(i>=48 && i<=57)
		{
			if(point>0)
			{
				value1=value1+Math.pow(0.1, point)*(i-48);
				point++;
			}
			else
				value1=value1*10+(i-48);
			if(prevchar=='a'){
				value=(double)0;
				tf1.setText(fmt(value1)+"");
			}
			else
			{ 
				tf1.setText(fmt(value)+""+prevchar+""+fmt(value1));
			}
		}
		else if(c=='+' || c=='-' || c=='*' || c=='/' || c=='=' || c=='s' ||c=='p'||c=='r')
		{
			System.out.println(c);
			
			if(prevchar=='a' )
			{
				if(c=='r')
				{
					if(value==0)
					{
						value=value1;
						value1=(double)0;
					}
					value=1/value;
					tf1.setText(fmt(value)+"");
				}
				else if(c=='p')
				{
					if(value==0)
					{
						value=value1;
						value1=(double)0;
					}
					value=value*value;
					tf1.setText(fmt(value)+"");
				}
				else if(c=='s')
				{
					if(value==0)
					{
						value=value1;
						value1=(double)0;
					}
					value=Math.sqrt(value);
					tf1.setText(fmt(value)+"");
				}
				else if(c!='=')
				{
					value=value+value1;
					value1=(double)0;
					tf1.setText(fmt(value)+""+c);
					if(c!='=')
						prevchar=c;
				}
			}
			else
			{
				
				try{
					if(prevchar=='+')
					{
							
						value=value+value1;
					}
					else if(prevchar=='-')
					{
						value=value-value1;
					}
					else if(prevchar=='*')
					{
						value=value*value1;
					}
					else if(prevchar=='/')
					{
						value=value/value1;
					}
					
					
					if(c=='=')
					{
						prevchar='a';
						tf1.setText(fmt(value));
					}
					else if(c=='r')
					{
						prevchar='a';
						value=1/value;
						tf1.setText(fmt(value)+"");
					}
					else if(c=='p')
					{
						prevchar='a';
						value=value*value;
						tf1.setText(fmt(value)+"");
					}
					else if(c=='s')
					{
						prevchar='a';
						value=Math.sqrt(value);
						tf1.setText(fmt(value)+"");
					}
					else
					{	prevchar=c;
						tf1.setText(fmt(value)+""+c);
					}
					value1=(double) 0;
				}
				catch(Exception ex){
					prevchar='a';
					tf1.setText("Error");
				}
			}
			point=0;
		}
		else if(c=='\b')
		{
			if(value1==0 && prevchar!='a')
			{
				prevchar='a';
				tf1.setText(fmt(value)+"");
			}
			if(prevchar=='a')
			{
				value=(double)0;
				value1=(double)0;
				tf1.setText(fmt(value)+"");
			}
			else
			{
				value1=(double)0;
				tf1.setText(fmt(value)+""+prevchar);
			}
			point=0;
		}
		else if(c=='.'){
			if(point==0) point=1;
		}
			
	}
	public static String fmt(double d)
	{
	    if(d == (long) d)
	        return String.format("%d",(long)d);
	    else
	        return String.format("%s",d);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyCalci calci = new MyCalci();
		Thread t = new Thread(calci);
		t.start();
	}
}

//s=sqroot
//r=reciprocal
//p=power^2