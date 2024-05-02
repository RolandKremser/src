/*
    StartAll.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/
// add your custom import statements here
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class StartAll
{
    public static void main( String[] args )
    {
		new StartAll(args);
    }
	private void checkForStart()
	{
		Runtime rt=Runtime.getRuntime();

		System.out.println("now="+new java.util.Date()+" / "+rt.totalMemory()+"/"+rt.freeMemory());
		//try
		//{
			for(int i=0;i<Vec.size();i++)
			{
				try
				{
					rt.exec(""+Vec.elementAt(i));
					System.out.println(/*iExit+":"+*/Vec.elementAt(i));
				}
				catch(Exception e)
				{
					System.out.println("Exception:"+e);
				}
				sleep(2000);
			}
		/*}catch(IOException io)
		 {
			 //Static.printError("Adresse.refresh(): IOException - "+io);
			 //return false;
		 }*/

	}
	private StartAll( String[] args)
	{
		//sJava=args.length>2?args[1]:"C:\\Programme\\JavaSoft\\Jre\\1.3.1_04";
		int iZeit=args.length>1?new Long(args[1]).intValue()*1000:13000;
		readFile(args.length>1?args[0]:"file:\\n:\\Start\\Start.txt");
		checkForStart();
		timer = new javax.swing.Timer(iZeit,new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					checkForStart();
				}
			});



		timer.start();
		new java.awt.Frame();
	}
	public void readFile(String sFile)
	{
		try
		{
			java.net.URL url=new java.net.URL(sFile);
			InputStream is=(InputStream)url.getContent();
			//DataInputStream Buf=new DataInputStream(is);
                        BufferedReader Buf=new BufferedReader(new InputStreamReader(is));

			String s=Buf.readLine();
			while (s != null)
			{
				Vec.addElement(s);
				s=Buf.readLine();
			}
			Buf.close();

		}catch(IOException io)
		{
			System.out.println("Tabellenspeicher.readFile(): IOException - "+io);
		}
	}
	public static void sleep(int iMillis)
	{
		try
		{
			Thread.sleep(iMillis);
		}
		catch(Exception e)
		{
			System.out.println("Static.sleep:"+e);
		}
	}
    // add your data members here
	private javax.swing.Timer timer;
	//private String sJava;
	private Vector<String> Vec=new Vector<String>();
}

