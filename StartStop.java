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

import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;

public class StartStop
{
  private static String sAServer=null;

  public static String writeAServer(String s,boolean bGet)
  {
    long lClock=Static.get_Mikro_s();
    String sServer=sAServer;
    String sIP=sServer.substring(0,sServer.indexOf(':'));
    int iPort=Integer.parseInt(sServer.substring(sServer.indexOf(':')+1));
    String sReturn="";
    try
    {
      Socket server = new Socket(sIP, iPort);
      OutputStream out = server.getOutputStream();
      out.write(s.getBytes());
      if (bGet)
      {
        InputStream in = server.getInputStream();
        byte[] b = new byte[100];
        int iAnz = in.read(b);
        sReturn = new String(b).substring(0, iAnz);
      }
    }
    catch (Exception e2)
      {
        //bError=true;
        Static.printError("Problem mit Socket-Server "+sAServer+":"+e2);
      }
    long lClock2=Static.get_Mikro_s();
    System.out.println("[writeAServer:"+s+"->"+sReturn+"]:"+(lClock2-lClock));
    return sReturn;
  }

  public static void main(String[] args)
  {
    if (args.length<2)
    {
      System.out.println("java -jar AU_Timer.jar Connect [Options]");
      System.out.println("Connect: z.B.: 127.0.0.1:2638");
      System.out.println("Options: /Stop ... stoppt  Timer/Terminal-Prozesse");
      System.out.println("         /Start... startet Timer/Terminal-Prozesse");
      System.out.println("         /Close ...schließt Timer/Terminal-Prozesse");
      System.out.println("         /AStop xx ... stoppt Applikationsserver xx");
    }
    else
    {
      Transact t = new Transact();
      t.connect(args[0]);
      String s = args[1];
      int iArt = s.equals("/AStop") ? -2:s.equals("/Stop") ? 3:s.equals("/Close") ? 4:s.equals("/Start") ? 0:-1;
      if (iArt==-2)
      {
        sAServer=args[2];
        writeAServer("X",false);
      }
      else if (iArt>=0)
      {
        s=iArt==0 ? "<aktivieren>":iArt==3 ? "<deaktivieren>":"<schließen>";
        int iPU=SQL.getInteger(t,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
        int iStatus= iPU>0 ? SQL.getInteger(t,"select "+t.int1()+" from parameter where aic_parameter="+iPU):-2;
        if (iStatus != iArt && (iStatus==0 || iStatus==3 || iStatus==4))
        {
          t.exec("Update parameter set " + t.int1() + "=" + iArt + " where aic_parameter=" + iPU);
          Transact.fixInfoS("Status "+s+" gesetzt");
        }
        else if (iStatus == iArt)
          Transact.fixInfoS("Status "+s+" bereits gesetzt");
        else
          Transact.fixInfoS("Status nicht änderbar");
      }
      t.disconnect();
    }
    //StartStop startstop = new StartStop();
  }
}
