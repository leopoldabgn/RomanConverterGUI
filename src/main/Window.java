package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Window extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JTextField edit;
	private JLabel lbl;
	
	private Timer timer = new Timer(100, this);
	
	private char[] romanNb = new char[] {'I','V','X','L','C','D','M'}; // 1 -> 4999
	
	public Window(int w, int h)
	{
		this.setPreferredSize(new Dimension(w, h));
		this.setTitle("Roman Numerals Converter");
		this.setLayout(new GridLayout(2, 1));
		this.setMinimumSize(new Dimension(w, h));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setDefaultLookAndFeelDecorated(true);
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		
		edit = new JTextField();
		edit.setPreferredSize(new Dimension(150, 24));
		edit.setText("2020");
		lbl = new JLabel("", SwingConstants.CENTER);
		Font font = new Font("Verdana", Font.BOLD, 50);
		lbl.setFont(font);
		pan1.add(edit);
		pan2.add(lbl);

		this.getContentPane().add(pan1);
		this.getContentPane().add(pan2);
		
		this.setVisible(true);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String txt = edit.getText();
		if(isInteger(txt))
		{
			int temp = Integer.parseInt(txt);
			if(temp >= 4000)
			{
				txt = "3999";
				edit.setText(txt);
			}
			else if(temp <= 0)
			{
				txt = "1";
				edit.setText(txt);
			}
				
			lbl.setText(convert(txt));
		}
		else
		{
			edit.setText("");
			lbl.setText("NULL");
		}
	}
	
	public String convert(String nb)
	{	
		int n1, n2 = 1;
		String romanNumber = "";
		
		for(int i=nb.length()-1;i>=0;i--)
		{
			n1 = nb.charAt(i) - '0';
			
			if(n1 >= 1 && n1 <= 3)
			{
				for(int j=0;j<n1;j++)
					romanNumber = getRomanNb(n2)+romanNumber;
			}
			else if(n1 == 4)
			{
				romanNumber = getRomanNb(5*n2)+romanNumber;
				romanNumber = getRomanNb(n2)+romanNumber;
			}
			else if(n1 == 5)
			{
				romanNumber = getRomanNb(n1*n2)+romanNumber;
			}
			else if(n1 >= 6 && n1 <= 8)
			{
				for(int j=6;j<=n1;j++)
					romanNumber = getRomanNb(n2)+romanNumber;
				romanNumber = getRomanNb(5*n2)+romanNumber;
			}
			else if(n1 == 9)
			{
				romanNumber = getRomanNb(n2*10)+romanNumber;
				romanNumber = getRomanNb(n2)+romanNumber;
			}
			n2 *= 10;
		}
		
		return romanNumber;
	}
	
	public char getRomanNb(int nb)
	{
		switch(nb)
		{
		case 1:
			return romanNb[0];
		case 5:
			return romanNb[1];
		case 10:
			return romanNb[2];
		case 50:
			return romanNb[3];
		case 100:
			return romanNb[4];
		case 500:
			return romanNb[5];
		case 1000:
			return romanNb[6];
		default:
			return '\0';
		}
	}
	
	public boolean isInteger(String nb)
	{
		try
		{
			Integer.parseInt(nb);
			return true;
		}
		catch(Exception e) 
		{
			return false;
		}
	}
	
}
