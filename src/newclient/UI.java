package newclient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class UI extends JFrame implements ActionListener
{
	JTextField input;
	JTextArea output;
	Client c;
	public UI(Client c)
	{
		this.c = c;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 300, 200);
		setLayout(null);
		//setResizable(false);
		JButton b = new JButton("���������");
		b.setBounds(100, 0, 100, 20);
		b.addActionListener(this);
		add(b);
		input = new JTextField();
		input.setBounds(100, 20, 100, 20);
		add(input);
		JScrollPane sp = new JScrollPane();
		output = new JTextArea("");
		output.setEditable(false);
		output.setBounds(0, 0, 300, 160);
		sp.add(output);
		sp.setBounds(0, 40, 300, 160);
		add(sp);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			if(input.getText().equals("exit"))
			{
				//c.out.writeUTF("�� ���� ����� " + c.login);
				c.in.close();
				c.out.close();
				//return;
				System.exit(0);
			}
			c.out.flush();
			c.out.writeUTF(input.getText());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
