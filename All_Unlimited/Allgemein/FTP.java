/*
    All_Unlimited-Allgemein-FTP.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.Zahl;

public class FTP
{
    // add your data members here
	public FTP(Transact rt,String sIp_Port,Stack<String> stackUser)
	{
		try
		{

			t=rt;
			String sIp = sIp_Port!=null&&sIp_Port.indexOf(":")>-1?sIp_Port.substring(0,sIp_Port.indexOf(":")):"";
			int iPort = sIp_Port!=null&&sIp_Port.indexOf(":")>-1?new Integer(sIp_Port.substring(sIp_Port.indexOf(":")+1)).intValue():0;

			sockControl=new Socket(sIp,iPort);

			InputStream inp=sockControl.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inp));

			OutputStream outp=sockControl.getOutputStream();
			osw=new OutputStreamWriter(outp);

			readReply();
			String sPassword="anonymous";
			String sUser="a.b@c.d";

			if(!stackUser.isEmpty())
			{
				sPassword=(String)stackUser.pop();
				sUser=(String)stackUser.pop();
			}

			sendCommand("USER "+sUser);
			sendCommand("PASS "+sPassword);

		}catch(Exception e)
		{
			bFehler = true;
			Static.printError("Fehler beim Öffnen der FTP-Verbindung->"+e);
		}
	}

	public FTP(Transact rt,String sIP)
	{
		this(rt,sIP,new Stack<String>());
	}

        public static void posTab(Global g,String sKennung)
        {
          if (Tab==null)
            Tab=new Tabellenspeicher(g,new String[] {"Kennung","Dlg","Adresse","Port","Dir","User","PW"});
          if (!Tab.posInhalt("Kennung",sKennung))
          {
            Tab.newLine();
            Tab.setInhalt("Kennung",sKennung);
            Parameter P = new Parameter(g, sKennung);
            String sAdresse=P.getParameter("Adresse",false,false);
            if (P.bGefunden)
            {
              //Vector Vec=new Vector();
              Tab.setInhalt("Adresse",sAdresse);
              Tab.setInhalt("Port",P.int1);
              String sDir="";
              if (P.int2>0)
                sDir=P.getParameter("Dir",false,false);
              String s=P.getParameter("Name_PW",false,false);
              Tab.setInhalt("Dir",sDir);
              Tab.setInhalt("User",s.substring(0,s.indexOf(":")));
              Tab.setInhalt("PW",s.substring(s.indexOf(":")+1));
            }
            P.free();
          }
        }

        public static void setParameter(final Global g,JFrame Fom,String sKennung)
        {
          posTab(g,sKennung); //"FTP_Bild");
          JDialog Dlg=(JDialog)Tab.getInhalt("Dlg");
          if (Dlg==null)
          {
            Dlg = new JDialog(Fom, sKennung+"-Parameter", true);
            Tab.setInhalt("Dlg",Dlg);
            Dlg.getContentPane().setLayout(new BorderLayout());
            JPanel PnlLabel = new JPanel(new GridLayout(0, 1));
            JPanel PnlEdit = new JPanel(new GridLayout(0, 1));
            JPanel PnlS = new JPanel(new GridLayout(1, 2));
            g.addLabel(PnlLabel,"Adresse");
            g.addLabel(PnlLabel,"Port");
            g.addLabel(PnlLabel,"Verzeichnis");
            g.addLabel(PnlLabel,"Name");
            g.addLabel(PnlLabel,"Passwort");
            final Text EdtAdr=new Text("",20);
            final Zahl EdtPort=new Zahl(21);
            final Text EdtDir=new Text("",100);
            final Text EdtName=new Text("",49);
            final AUPasswort EdtPW=new AUPasswort();
            if (!Tab.isNull("Adresse"))
            {
              EdtAdr.setText(Tab.getS("Adresse"));
              EdtPort.setValue(Tab.getI("Port"));
              EdtDir.setText(Tab.getS("Dir"));
              EdtName.setText(Tab.getS("User"));
              EdtPW.setValue(Tab.getS("PW"));
            }
            /*Parameter P = new Parameter(g, "FTP_Bild");
            EdtAdr.setText(P.getParameter("Adresse",false,false));
            if (P.bGefunden)
            {
              EdtPort.setValue(P.int1);
              if (P.int2>0)
                EdtDir.setText(P.getParameter("Dir",false,false));
              String s=P.getParameter("Name_PW",false,false);
              EdtName.setText(s.substring(0,s.indexOf(":")));
              EdtPW.setValue(s.substring(s.indexOf(":")+1));
            }
            P.free();*/
            g.addComp(PnlEdit,EdtAdr);
            g.addComp(PnlEdit,EdtPort);
            g.addComp(PnlEdit,EdtDir);
            g.addComp(PnlEdit,EdtName);
            g.addComp(PnlEdit,EdtPW);
            JButton BtnOk = g.getButton("Ok");
            JButton BtnAbbruch = g.getButton("Abbruch");
            BtnOk.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e) {
                Parameter P = new Parameter(g, Tab.getS("Kennung"));
                boolean bDir=!EdtDir.getText().equals("");
                P.setParameter("Adresse",EdtAdr.getText(),EdtPort.intValue(),bDir ? 1:0,0,0,false,false);
                if (bDir)
                  P.setParameter("Dir",EdtDir.getText(),0,0,0,0,false,false);
                P.setParameter("Name_PW",EdtName.getText()+":"+new String(EdtPW.getPassword()),0,0,0,0,false,false);
                P.free();
                Tab.setInhalt("Adresse",EdtAdr.getText());
                Tab.setInhalt("Port",EdtPort.intValue());
                Tab.setInhalt("Dir",EdtDir.getText());
                Tab.setInhalt("User",EdtName.getText());
                Tab.setInhalt("PW",new String(EdtPW.getPassword()));
                ((JDialog)Tab.getInhalt("Dlg")).setVisible(false);
              }
            });
            BtnAbbruch.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e) {
                //g.progInfo("! BtnOk");
                ((JDialog)Tab.getInhalt("Dlg")).setVisible(false);
              }
            });

            PnlS.add(BtnOk);
            PnlS.add(BtnAbbruch);
            Dlg.getContentPane().add("West", PnlLabel);
            Dlg.getContentPane().add("Center", PnlEdit);
            Dlg.getContentPane().add("South", PnlS);
            Dlg.pack();
          }
          Static.centerComponent(Dlg,Fom);
          Dlg.setVisible(true);
        }

        public static String CopyTo(Global rg,String sKennung,String rsFile)//String sIp_Port,String sUser,String sPassword,String sFile)
        {
          posTab(rg,sKennung);
          if (Tab.isNull("Adresse"))
          {
            Static.printError("FTP noch nicht definiert!");
            return null;
          }
          String sIp_Port=Tab.getS("Adresse")+":"+Tab.getI("Port");
          String sDir=Tab.getS("Dir");
          //rg.progInfo(sKennung+"="+sIp_Port+"/"+sDir);
          Stack<String> stack=new Stack<String>();
          stack.push(Tab.getS("User"));
          stack.push(Tab.getS("PW"));
          FTP ftp=new FTP(rg,sIp_Port,stack);
          String sFile=Static.replaceString(ftp.copyToFTP(rsFile,sDir)," ","%20");
          ftp.close2();
          return sFile;
        }

        private String copyToFTP(String sDirFile,String sDir)
        {
          String sFile=t.getLog()+"_"+sDirFile.substring(sDirFile.lastIndexOf(File.separator)+1);
          //System.out.println("copyToFTP "+sFile);
          if(/*!bOpenDataSocket&&*/!bFehler)
          {
            try
            {
              sockData=createDataSocket();
              //bOpenDataSocket=true;
              if(sockData!=null)
              {
                //sendCommand("TYPE I");
                sendCommand("STOR "+sDir+sFile);
                FileInputStream in = new FileInputStream(sDirFile);
                BufferedOutputStream out = new BufferedOutputStream(sockData.getOutputStream());
                byte[] buf = new byte[4096];
                int len;
                while ((len = in.read(buf)) > 0)
                  out.write(buf, 0, len);
                //Static.sleep(100);
                out.flush();
                out.close();
                Static.sleep(100);
                in.close();
              }
            }catch(Exception e)
            {
                    Static.printError("FTP:Fehler in open->"+e);
            }
          }
          return sFile;
        }

	public boolean open(String sFile,boolean bAppend)
	{
		if(!bOpenDataSocket&&!bFehler)
		{
			try
			{
				sockData=createDataSocket();
				bOpenDataSocket=true;
				if(sockData!=null)
				{

					sendCommand((bAppend?"APPE ":"STOR ")+sFile);
					out = new BufferedWriter(new OutputStreamWriter(sockData.getOutputStream()));
					return true;
				}
				return false;

			}catch(Exception e)
			{
				Static.printError("FTP:Fehler in open->"+e);
				return false;
			}
		}
		else
			return false;

	}

	public void getFile(String sFile,Tabellenspeicher TabSpalten,Tabellenspeicher TabDaten,String sTrennzeichen)
	{
		Vector<Object> Vec = new Vector<Object>();
		Vec.addElement(sFile);
		Vec.addElement(TabSpalten);
		Vec.addElement(TabDaten);
		Vec.addElement(sTrennzeichen);
		getFtpFile(Vec,false);
	}

	public void getFile(String sFile,String sLocation)
	{
		/*if(!bOpenDataSocket&&!bFehler)
		{
			try
			{
				sockData=createDataSocket();
				if(sockData!=null)
				{

					String check=sendCommand("RETR "+sFile);
					if(!check.startsWith("550"))
					{
						BufferedWriter out=new BufferedWriter(new FileWriter(sLocation));
						LineNumberReader in = new LineNumberReader(new InputStreamReader(sockData.getInputStream()));
						String line = null;
						while ((line = in.readLine()) != null)
						{
							out.write(line, 0, line.length());
							out.newLine();
						}
						out.close();
						in.close();
						sockData.close();
						readReply();
						sendCommand("DELE "+sFile);
					}
					else
						sockData.close();





				}

			}catch(Exception e)
			{
				Static.printError("FTP:Fehler in getFile.");
			}
		}*/
		Vector<Object> Vec = new Vector<Object>();
		Vec.addElement(sFile);
		Vec.addElement(sLocation);
		getFtpFile(Vec,true);
	}

	private void getFtpFile(Vector<Object> Vec,boolean bSaveAsFile)
	{
		if(!bOpenDataSocket&&!bFehler)
		{
			try
			{
				sockData=createDataSocket();
				if(sockData!=null)
				{

					String sFile=(String)Vec.elementAt(0);
					String check=sendCommand("RETR "+sFile);
					if(!check.startsWith("550"))
					{


						LineNumberReader in = new LineNumberReader(new InputStreamReader(sockData.getInputStream()));
						//DataInputStream in = new DataInputStream(sockData.getInputStream());

						if(bSaveAsFile)
						{
							String sLocation = (String)Vec.elementAt(1);
							BufferedWriter out=new BufferedWriter(new FileWriter(sLocation));
							String line = null;
							while ((line = in.readLine()) != null)
							{
								out.write(line, 0, line.length());
								out.newLine();
							}
							out.close();
						}
						else
						{
							Tabellenspeicher TabSpalten = (Tabellenspeicher)Vec.elementAt(1);
							Tabellenspeicher TabDaten = (Tabellenspeicher)Vec.elementAt(2);
							String sTrennzeichen = (String)Vec.elementAt(3);
							TabDaten.CalcImport(sTrennzeichen,in,TabSpalten,false);
						}
						in.close();
						sockData.close();
						readReply();
						sendCommand("DELE "+sFile);
					}
					else
					{
						sockData.close();
						quit();
						bFehler=true;
					}





				}

			}catch(Exception e)
			{
				Static.printError("FTP:Fehler in getFile->"+e);
			}
		}
	}

	public void writeln(String sLine)
	{
		if(!bFehler)
		{
			try
			{
				String EOL="\r\n";
				out.write(sLine, 0, sLine.length());
				out.write(EOL, 0, EOL.length());
			}catch(Exception e)
			{
				Static.printError("FTP:Fehler in writeLn->"+e);
			}
		}

	}

	public void close()
	{
		if(!bFehler)
		{
			try
			{
				out.flush();
				out.close();
				sockData.close();
				bOpenDataSocket=false;
				readReply();
			}catch(Exception e)
			{
				Static.printError("FTP:Fehler in close->"+e);
			}
		}
	}

        public void close2()
	{
          try
          {
            reader.close();
            osw.close();
            sockData.close();
          }catch(Exception e)
          {
                  Static.printError("FTP:Fehler in close->"+e);
          }
        }

	public void quit()
	{
		if(!bFehler)
		{
			if(bOpenDataSocket)
				close();
			sendCommand("QUIT");
		}
	}

	/*private void validateReply(String reply, String expectedReplyCode) throws Exception
	{
        String replyCode = reply.substring(0, 3);
		if (!replyCode.equals(expectedReplyCode))
		{
			Static.printError("FTP.Fehlercode:"+reply.substring(4));
			throw new Exception();
		}
    }*/

	private String readReply() throws IOException
    {

         StringBuffer reply = new StringBuffer(reader.readLine());


         t.debugInfo(reply.toString());
		 //System.out.println(reply.toString());

         String replyCode = reply.toString().substring(0, 3);


         if (reply.charAt(3) == '-') {

             boolean complete = false;
             while (!complete) {

                 String line = reader.readLine();

                 t.debugInfo(line.toString());//(line);
				 //System.out.println(line.toString());//(line);

                 if (line.length() > 3 && line.substring(0, 3).equals(replyCode) && line.charAt(3) == ' ')
				 {
                     reply.append(line.substring(3));
                     complete = true;
                 }
                 else
				 {
                     reply.append(" ");
                     reply.append(line);
                 }
             }
         }
         //System.out.println("Reply: "+reply.toString());
		 return reply.toString();
     }


	private String sendCommand(String sCommand)
	{
		t.debugInfo("sendCommand:"+sCommand);
		//System.out.println("sendCommand:"+sCommand);
		String sReply="";
		try
		{
			osw.write(sCommand + "\r\n");
		 	osw.flush();
			sReply=readReply();
		}catch(Exception e)
		{
			Static.printError("FTP:Fehler beim Senden des Kommandos:"+sCommand);
		}
		return sReply;
	}

	private Socket createDataSocket()
	{
		sendCommand("TYPE I");
		String response=sendCommand("PASV");




		try
		{
			int bracket1 = response.indexOf('(');
			int bracket2 = response.indexOf(')');
			String ipData = response.substring(bracket1+1,bracket2);
			//System.out.println("IP-Daten:"+ipData);

			int parts[] = new int[6];

			int len = ipData.length();
			int partCount = 0;
			StringBuffer buf = new StringBuffer();

			for (int i = 0; i < len && partCount <= 6; i++)
			{

				char ch = ipData.charAt(i);
				if (Character.isDigit(ch))
					buf.append(ch);
				else if (ch != ',')
				{
					throw new Exception("Malformed PASV reply: " + response);
				}


				if (ch == ',' || i+1 == len)
				{
					try
					{
						parts[partCount++] = Integer.parseInt(buf.toString());
						buf.setLength(0);
					}
					catch (NumberFormatException ex)
					{
						throw new Exception("Malformed PASV reply: " + response);
					}
				}
			}

			//System.out.println("1.Oktett: "+parts[0]);
			int port = (parts[4] << 8) + parts[5];
			//System.out.println("Port: "+port);

			t.debugInfo("DestIP:"+parts[0]+"."+parts[1]+"."+parts[2]+"."+parts[3]+"/DataPort: "+port);
			Socket data=new Socket(""+parts[0]+"."+parts[1]+"."+parts[2]+"."+parts[3],port);
			data.setSoTimeout(0);

			return data;
		}catch(Exception e)
		{
			quit();
			bFehler = true;
			Static.printError("Fehler beim Erstellen des DataSockets->"+e);
		}
		return null;


	 }

	 public boolean fehler()
	 {
		 return bFehler;
	 }

	 public String getSystemInfo()
	 {
		 return sendCommand("SYST");
	 }

	 public String getDirectory()
	 {
		 return sendCommand("PWD");
	 }

	 public void setDirectory(String sDirectory)
	 {
		 sendCommand("CWD "+sDirectory);
	 }

	 BufferedReader reader=null;
	 OutputStreamWriter osw=null;
	 public BufferedWriter out=null;
	 Socket sockControl=null;
	 Socket sockData=null;
	 boolean bOpenDataSocket=false;
	 boolean bFehler=false;
	 private Transact t=null;
         private static Tabellenspeicher Tab=null;
}

