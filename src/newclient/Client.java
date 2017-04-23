package newclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client implements Runnable
{
	DataOutputStream out;
	DataInputStream in;
	UI ui;
	public static void main(String[] args)
	{
		new Thread(new Client()).start();
	}
	
	@Override
	protected void finalize() throws Throwable
	{
		out.close();
		in.close();
		super.finalize();
	}

	@Override
	public void run()
	{
		try {
			Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), Integer.parseInt(JOptionPane.showInputDialog(null, "")));
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			ui = new UI(this);
			while (true) {
				String str = in.readUTF();
				System.out.println(str);
				ui.output.setText(ui.output.getText() + str + "\n");
				Thread.sleep(10);
			}

		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}
