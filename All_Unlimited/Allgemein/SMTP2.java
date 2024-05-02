package All_Unlimited.Allgemein;

import java.util.Properties;
import java.util.Stack;

//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;

import com.sun.mail.smtp.SMTPTransport;
import java.io.File;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class SMTP2
{
  private MimeMessage message=null;
  private Session session = null;
  private boolean bError=false;
  private String sError=null;
  //private String sText="";
  private String sBetreff="";
  private static String sAuthName=null;
  private static String sAuthPW=null;
  private String sMailhost;
  private String sSender="";
  private Global g;
  private MimeMultipart multipart = new MimeMultipart();

  static class PopupAuthenticator extends Authenticator {
      public PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(sAuthName,sAuthPW);
      }
  }

  public SMTP2(Global rg,Stack<String> Sta)
  {
    this(rg, Sta, 0);
  }

  public SMTP2(Global rg,Stack<String> Sta,int iMandant)
  {
    g=rg;
    bError=false;
    sError=null;
    Properties props = System.getProperties();
    String sIp_Port="";
    String sName="";
    String sAbsender="";
    if (Sta != null)
    {
      sIp_Port=""+Sta.pop();
      sName=""+Sta.pop();
      sAbsender=""+Sta.pop();
    
      sAuthName=Sta.isEmpty() ? null:""+Sta.pop();
      sAuthPW=Sta.isEmpty() ? null:""+Sta.pop();
    }
    int i=sIp_Port.indexOf(":");
    /*if (i==-1)
      i=sIp_Port.indexOf(";");*/
    String sIP= i>-1 ? sIp_Port.substring(0,i):sIp_Port;
    int iPort = i>-1 ? Integer.parseInt(sIp_Port.substring(i+1)):25;
    sMailhost=sIP;
    /*i=sIp_Port.indexOf(";");
    if(i != -1)
    {
      String sNP = sIp_Port.substring(i+1);
      i=sNP.indexOf(";");
      sAuthName=sNP.substring(0,i);
      sAuthPW=sNP.substring(i+1);
    }*/
    g.fixtestInfo("sIP="+sIP);
    if (!Static.Leer(sIP))
      props.put("mail.smtp.host", sIP);
    if (Static.bInfoEMail)
    	props.put("mail.debug", "true");
    Parameter Para=new Parameter(g,"SMTP");
	String s=Para.getMParameter("TLS",iMandant);
	boolean b=Para.bGefunden;
	int iBits=4+2; // StartTLS + TLSv1.2
	if (b)
	{
		iPort=Para.int1;
		iBits=Para.int2;
	}
	Para.free();
    if (iPort > 25 || b)
    {
      //g.fixtestInfo("SMTP: s="+s);
      if (b && Sta==null && !Static.Leer(s))
      {
        String sAry[]=s.split("&");
        i=sAry.length;
        if (i>=3)
        {
          sIP=sAry[0];
          sMailhost=sIP;
          props.put("mail.smtp.host", sIP);
          sAbsender=sAry[1];
          sName=sAbsender;
          if (i==5)
          {
            sAuthPW=g.getPassword(sAry[3],Global.PWR,0);
            sAuthName=sAry[4];
            if (sAuthName.equals(""))
              sAuthName=null;
          }
          else
          {
            sAuthName=null;
            sAuthPW=null;
          }
           g.fixtestError("Absender="+sAbsender+" von "+sMailhost+":"+iPort+" AuthName="+sAuthName);
        }
      }
    	boolean bStartTLS=(iBits&4)>0;
    	int iTLS=iBits&3;       
      g.fixtestInfo("Port="+iPort+(bStartTLS ? " StartTLS":"")+(iTLS>0 ? " TLSv1."+iTLS:""));
      props.put("mail.smtp.port", ""+iPort);
      // if (bStartTLS)
        props.put("mail.smtp.starttls.enable", bStartTLS ? "true":"false");
      if (iTLS>0)
      {
        props.put("mail.smtp.ssl.protocols",iTLS==1 ? "TLSv1.1":"TLSv1.2");
        props.put("mail.smtp.ssl.trust", "*");
      }
      else
        props.remove("mail.smtp.ssl.protocols");
      // g.fixtestInfo("SMTP="+sIP+":"+iPort);
    }
    if (sAuthName==null)
    {
      session = Session.getInstance(props);
      props.put("mail.smtp.auth", "false");
    }
    else
    {
      props.put("mail.smtp.auth", "true");
      Authenticator auth = new PopupAuthenticator();
      session = Session.getInstance(props, auth);
    }
    if (Static.bInfoEMail)
    {
      g.fixtestError("SMTP2-Properties:");
      for (Object key : props.keySet())
        if (((String)key).startsWith("mail"))	
     	  g.fixtestError(String.format("%s='%s'", key, props.get(key)));
    }
    message = new MimeMessage(session);
    try
    {
      message.setFrom(new InternetAddress(sAbsender, sName));
      g.fixtestInfo("setFrom:"+sName+"/"+sAbsender);
    }
    catch (Exception   e)
    {
      addError("SMTP2.setFrom:"+e);
    }
  }

  private void addError(String s)
  {
    Static.printError(s);
    bError=true;
    if (sError==null)
      sError=s;
    else
      sError+=";"+s;
  }

  public String getError()
  {
    return sError;
  }

  public void sendTo(String s)
  {
    Stack<String> Sta=new Stack<>();
    Sta.push(s);
    Sta.push(s);
    sendTo(Sta);
  }

  public void sendTo(Stack<String> Sta)
  {
    String sName=""+Sta.pop();
    String sEmpfaenger=""+Sta.pop();
    try
    {
      sSender=sEmpfaenger;
      message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(sEmpfaenger, sName));
      g.fixtestInfo("sendTo:"+sName+"/"+sEmpfaenger);
    }
    catch (Exception e)
    {
      addError("SMTP2.sendTO:"+e);
    }
  }

  public void sendCC(Stack<String> Sta)
  {
    String sName=""+Sta.pop();
    String sEmpfaenger=""+Sta.pop();
    try
    {
      message.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(sEmpfaenger, sName));
      g.fixtestInfo("sendCC:"+sName+"/"+sEmpfaenger);
    }
    catch (Exception e)
    {
      addError("SMTP2.sendCC:"+e);
    }
  }

  public void sendBCC(Stack<String> Sta)
    {
      String sName=""+Sta.pop();
      String sEmpfaenger=""+Sta.pop();
      try
      {
        message.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress(sEmpfaenger, sName));
        g.fixtestInfo("sendBCC:"+sName+"/"+sEmpfaenger);
      }
      catch (Exception e)
      {
        addError("SMTP2.sendBCC:"+e);
      }
  }

  public void subject(String rsBetreff)
  {
    try
    {
      sBetreff=rsBetreff;
      message.setSubject(sBetreff);
      g.fixtestInfo("subject:"+sBetreff);
    }
    catch (Exception e)
    {
      addError("SMTP2.setSubject:"+e);
    }
  }

  public void sendText(String sText)
  {
      try
      {
        //sText=rsText;
        //message.setText(sText);
        //message.setContent(sText, "text/html; charset=utf-8");
        BodyPart messageBodyPart = new MimeBodyPart();
//        String sCRLF=sText.startsWith("<html") ? "":"\r\n";
        messageBodyPart.setContent(sText/*+sCRLF*/, "text/html; charset=utf-8");
        multipart.addBodyPart(messageBodyPart); // fügt Text hinzu
        g.fixtestInfo("sendText:"+sText);
      }
      catch (Exception e)
      {
        addError("SMTP2.sendText:"+e);
      }
  }

  public void quit()
  {
	  try
	  {
		  if (Static.Java8())
		  {
			  MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		      mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		      mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		      mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		      mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		      mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		      CommandMap.setDefaultCommandMap(mc);
		  }
	  }
	  catch(Exception e)
	  {
		  g.fixtestError("SMTP.quit:"+e);
	  }
      
    new Thread(new Runnable()
    {
      public void run()
      {
    	boolean bConnect=false;
        try
        {
          message.setContent(multipart);
          //Transport.send(message);
          SMTPTransport t =
                (SMTPTransport)session.getTransport("smtp");
            try {
                if (sAuthName!=null)
                    t.connect(sMailhost, sAuthName, sAuthPW);
                else
                    t.connect();
                bConnect=true;
                t.sendMessage(message, message.getAllRecipients());
            } finally {
                g.fixtestInfo("Response: " + t.getLastServerResponse());
                t.close();
            }

          g.fixInfo("Mail <"+sBetreff+"/"+sSender+"> gesendet");
        }
        catch (Exception e)
        {
          addError("SMTP2.send  <"+sBetreff+"/"+sSender+">:" + e);
          if (bConnect)
        	  Static.printError("SMTP-Sendefehler mit "+message);
          else
        	  Static.printError("SMTP-Verbindungsfehler mit "+sMailhost+"/"+sAuthName);
        }
      }
    }).start();
  }

  public void attach(String sFileMitPfad)
  {
    try
    {
      //MimeMultipart multipart = new MimeMultipart();
    	File f =new File(sFileMitPfad);
    	if (f.exists())
    	{
    		g.fixtestInfo("attach:"+sFileMitPfad);
    		MimeBodyPart messageBodyPart = new MimeBodyPart();
    		//DataSource fds = new FileDataSource(sFileMitPfad);      
    		String sFile=sFileMitPfad.substring(sFileMitPfad.lastIndexOf(File.separator)+1);
//    		messageBodyPart.addHeaderLine(sFile);
    		messageBodyPart.attachFile(f);
    		//messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(sFileMitPfad)));
    		//Transact.fixInfo("File angehängt:"+sFile);
    		messageBodyPart.setFileName(sFile);
    		//messageBodyPart.setHeader("Content-ID", "<memememe>");
    		multipart.addBodyPart(messageBodyPart); // fügt File hinzu
    		//message.setContent(multipart);
    	}
    	else
    		g.printError("SMTP2.attach: Bild "+sFileMitPfad+" nicht gefunden");
    }
      catch (Exception e)
      {
        addError("SMTP2.attach:"+e);
      }
    }

  public boolean fehler()
  {
    return bError;
  }

}
