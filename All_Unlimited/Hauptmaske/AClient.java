package All_Unlimited.Hauptmaske;

import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Anzeige.Zahl1;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.8
 */
public class AClient
{
  public static String sAServerSoll = null;
  private static String sAServer = null;
  private static boolean bVersionFalsch = false;
  private static int iIA = -1; // Stempelimport aktiv bei 1
  //public static final int iVersion=1;
  private static boolean bGaugeAktiv=true;
  private static boolean bFirst=true;
  private static Global g=null;
  public static boolean bShowError=true;
  public static boolean bImportM=false;

  public static Socket connect()
  {
    //g.fixtestInfo("---------------AClient.connect");
    try
    {
      String sIP = sAServer.substring(0, sAServer.indexOf(':'));
      int iPort = Integer.parseInt(sAServer.substring(sAServer.indexOf(':') + 1));
      //System.out.println("Connect to AServer "+sIP+" mit Port "+iPort);
      Socket server = new Socket(sIP, iPort);
      server.setSoTimeout(600000); // 16.10.2014: 10 min statt 1 min
      return server;
    }
    catch (IOException e2)
    {
      if (bShowError)
        Static.printError("AClient.connect "+sAServer+":" + e2);
      //sAServer = null;
      setNull();
      return null;
    }
  }

  private static String getVer_AServer()
  {
    try
    {
      Socket server = connect();
      if (server == null || sAServer == null)
        return null;
      else
      {
        new ObjectOutputStream(server.getOutputStream()).writeObject("ver");
        String sVer = new Scanner(server.getInputStream()).nextLine();
        server.close();
        return sVer;
      }
    }
    catch (Exception e2)
    {
      if (bShowError)
        Static.printError("AClient.getVer_AServer:" + e2);
      return null;
    }
  }

  public static String getVersion(Global g)
  {
    return g.getDB() + ":" + Version.getVersion(); //+"/"+iVersion;
  }

  public static boolean reCheck()
  {
    return sAServer == null && (bFirst || sAServerSoll != null);
  }

  private static String getAServerSoll()
  {
    if (sAServerSoll!=null && (sAServerSoll.equals("") || sAServerSoll.indexOf(':')<1))
      sAServerSoll=null;
    if (sAServerSoll==null && g!=null && bFirst && !Static.bLtHtml)
    {
      bFirst=false;
      Parameter Para = new Parameter(g,"System");
      String sWhere=Para.getWhere("Dir");
      sAServerSoll=Para.getString(g,"select Parameterzeile from parameter"+sWhere+" and "+g.int1()+"=8");
      if (sAServerSoll != null && sAServerSoll.indexOf(':')<1)
        sAServerSoll=null;
      Para.free();
      if (bShowError)
        g.fixInfo(sAServerSoll==null ? "AServer nicht in Parameter gefunden":"AServer geladen:"+sAServerSoll);
    }
    return sAServerSoll;
  }

  public static boolean checkAServer(Global g)
  {
    //g.fixInfo("       !!!               checkAServer:"+sAServerSoll);
    long lClock = Static.get_ms();
    if (reCheck())
      sAServer = getAServerSoll();
    if (sAServer == null)
      return false;
    //g.fixInfo("-> sAServer="+sAServer);
    //sAServer=Static.sAServer;
    //int iVersion=Version.getVer2()+Calc.AS_VER*1000000;
    //String sVersion=Version.getVersion();
    //g.testInfo("Alljar-Version="+iVersion);
    bFirst=false;
    String sVersion = AClient.getVer_AServer();
//    boolean bMeldung = g.UserManager() && Static.bX11;
    if (sVersion == null)
    {
      //sAServer=null;
//      if (bShowError)
      g.printError("AServer " + sAServerSoll + " nicht erreichbar!");
      sAServer = null;
//      if (bMeldung)
//        new Message(Message.WARNING_MESSAGE, null, g).showDialog("Soap_Verbindungsfehler");
      return false;
    }
    else
    {
      //int iAS_Ver = Integer.parseInt(sVersion);
      String sVerClient = getVersion(g);
      bVersionFalsch = !sVerClient.equals(sVersion);
      boolean bDbFalsch=false;
      if (bVersionFalsch)
      {
        g.fixInfo("AServer-Version: " + sVersion + " statt " + sVerClient);
        if (sVersion.indexOf(":") > 0 && !sVersion.substring(0, sVersion.indexOf(":")).equals(sVerClient.substring(0, sVerClient.indexOf(":"))))
        {
          //sAServer = null;
          //sAServerSoll = null;
          bDbFalsch=true;
          setNull();
          g.printError("AServer-Datenbank falsch: "+sVersion+" statt "+sVerClient);
//          if (bMeldung)
//            new Message(Message.WARNING_MESSAGE, null, g).showDialog("Soap_Datenbank_falsch", new String[] {sVersion.substring(0, sVersion.indexOf(":"))});
        }
        else if (sVersion.endsWith("*"))// && bMeldung)
        	g.fixtestError("AServer inaktiv");
//          new Message(Message.WARNING_MESSAGE, null, g).showDialog("Soap_inaktiv");
        else //if (bMeldung)
        	g.fixtestError("AServer-Version falsch: "+sVersion+" statt "+sVerClient);
//          new Message(Message.WARNING_MESSAGE, null, g).showDialog("Soap_Version_falsch", new String[] {sVersion.indexOf(":") > 0 ? sVersion.substring(sVersion.indexOf(":") + 1) : sVersion});
      }
      else
        g.defInfo2("AServer-Version ist ok");
      g.clockInfo("checkAServer ", lClock);
      return !bDbFalsch;
    }
  }

  public static boolean AServerCalc()
  {
    return Static.iAServer == Static.IMMER || (!Static.bLocal || Static.bSlow) && Static.iAServer == Static.AUTO;
  }

  public static boolean UseAServer(int iBitsM)
  {
    return AServerCalc() && UseAServer() && (Transact.iInfos & Transact.NO_ASERVER) == 0 && (iBitsM & Global.cstAServer) > 0;
  }

  public static void setg(Global rg)
  {
    g=rg;
    bFirst=true;
    if (bShowError)
      g.fixtestInfo("setg für AServer "+sAServerSoll);
  }

  public static boolean UseAServer1() // prüft ob AServer vorhanden
  {
    if (bFirst && sAServer==null && g!=null)
      checkAServer(g);
    return sAServer != null;
  }

  public static boolean UseAServer() // prüft ob AServer mit richtiger Version
  {
    return UseAServer1() && !bVersionFalsch;
  }

  public static boolean UseAServer2() // prüft ob Stempelimport aktiv
  {
    return UseAServer1() && !bVersionFalsch && StempelimportAktiv();
  }

  public static boolean StempelimportAktiv()
  {
    if (iIA < 0)
      iIA = Integer.parseInt(get_AServer("i:ImportModell"));
    return iIA == 1;
  }

  public static void setNull()
  {
    sAServer = null;
    //sAServerSoll = null;
  }

  public static void GaugeOpen(String s, int iAnz, boolean b, ObjectOutputStream out,Global g)
  {
    if (bGaugeAktiv)
    {
      try
      {
        out.writeObject("GaugeOpen");
        out.writeObject(s);
        out.writeInt(iAnz);
        out.writeBoolean(b);
      }
      catch (Exception e)
      {
        Static.printError("AClient.GaugeOpen:" + e);
      }
    }
    //else
      g.fixtestInfo("GaugeOpen "+s+" mit "+iAnz);
  }

  public static void GaugeMom(String s, int i, ObjectOutputStream out,Global g)
  {
    if (bGaugeAktiv)
    {
      try
      {
        out.writeObject("GaugeMom");
        out.writeObject(s);
        out.writeInt(i);
      }
      catch (Exception e)
      {
        Static.printError("AClient.GaugeOpen:" + e);
      }
    }
    //else
      g.fixtestInfo("GaugeMom "+s+" auf "+i);

  }

  public static void GaugeClose(ObjectOutputStream out,Global g)
  {
    if (bGaugeAktiv)
    {
      try
      {
        out.writeObject("GaugeClose");
      }
      catch (Exception e)
      {
        Static.printError("AClient.GaugeClose:" + e);
      }
    }
    //else
      g.fixtestInfo("GaugeClose");
  }

  public static void sendInfo(boolean bInfo,boolean b2,String sDaten,ObjectOutputStream out)
  {
    try
    {
      out.writeObject("info");
      out.writeBoolean(bInfo);
      out.writeBoolean(b2);
      out.writeObject(sDaten);
    }
    catch(Exception e)
    {
      Static.printError("AClient.sendInfo:" + e);
    }
  }


  public static boolean sendMsg(boolean bFM,String sTitel,String sDaten,ObjectInputStream in,ObjectOutputStream out)
  {
    try
    {
      out.writeObject("msg");
      out.writeBoolean(bFM);
      out.writeObject(sTitel);
      out.writeObject(sDaten);
      String sb=(String)in.readObject();
      //System.out.println("sendMsg:"+sTitel+"/"+sDaten+"->"+sb);
      //return in.readBoolean();
      return sb.equals("1");
    }
    catch(Exception e)
    {
      Static.printError("AClient.sendMsg:" + e);
      return false;
    }
  }

  public static boolean sendDlg(Global g,String sTitel,int i,Tabellenspeicher Tab,Tabellenspeicher TabSp,ObjectInputStream in,ObjectOutputStream out)
  {
    try
    {
      //System.out.println("sendDlg:");
      out.writeObject("dlg");
      out.writeObject(new Tabellenspeicher(g,Tab));
      //System.out.println("write Tab");
      out.writeObject(sTitel);
      //System.out.println("sTitel="+sTitel);
      out.writeInt(i);
      //System.out.println("i="+i);
      out.writeObject(new Tabellenspeicher(g,TabSp));
      //if (g.Debug())
      //  new Tabellenspeicher(g,TabSp).showGrid("AServer-Spalten für:"+sTitel);
      //System.out.println("write TabSp");
      //Static.sleep(2000);
      //System.out.println("nach sleep");
      Tabellenspeicher Tab2=(Tabellenspeicher)in.readObject();
      //System.out.println("read Tab");
      Tab2.copyTo(Tab);
      //Tab.showGrid("Tab-neu");
      //Tab2.showGrid("Tab2");
      String sb=(String)in.readObject();
      System.out.println("sendDlg:"+sTitel+":"+sb);
      //boolean b=sb.equals("1");
      //System.out.println("-> b="+b);
      return sb.equals("1");
    }
    catch(Exception e)
    {
      Static.printError("AClient.sendDlg:" + e);
      return false;
    }
  }

  public static boolean send_AServer(String s,Global g)
  {
    return send_AServer(s,g,null);
  }

  public static boolean send_AServer(String s,Global g,Vector Vec)
  {
    String sTitel=s.indexOf(";")<0 ? s:s.substring(0,s.indexOf(";"));
    if (g!=null) g.printExec("send_AServer:"+sTitel,false);//+" an "+sAServer+","+g);
    if (sAServer==null && (g==null || !checkAServer(g)))
      return false;
    try
    {
      if (bVersionFalsch && !s.equals("quit") && !s.equals("x") && !s.equals("aktiv") && !s.equals("deaktiv") && !s.equals("reset")
          && !s.equals("backup") && !s.equals("update") && !s.equals("import"))
        Static.printError("AClient.send_AServer "+sTitel+" wird nicht durchgeführt, da Version falsch");
      else
      {
        Socket server = connect();
        if (server != null)
        {
          if (g!=null) g.fixtestInfo("sende "+s+" an "+sAServer);
          ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
          out.writeObject(s);
          if (Vec != null)
          {
            if (g!=null) g.fixtestInfo("sende Vec="+Sort.gets2(Vec,','));
            out.writeObject(Vec);
          }
          Static.sleep(200);
          out.close();
          server.close();
          return true;
        }
      }
    }
    catch(Exception e2)
    {
      Static.printError("AClient.send_AServer "+sTitel+":" + e2);
    }
    return false;
  }

  public static int getStatus()
  {
    return Sort.geti(AClient.get_AServer("status2"));
  }

  /*public static String getStatus(Global g)
  {
    String s=AClient.get_AServer("status2");
    int i=99;
    try
    {
      i=Integer.parseInt(s);
    }
    catch(Exception e) {}
    return getStatus(i,g);
  }*/

  public static String getStatus(int i,Global g)
  {
    //String s2= ""+(AU_AServer2012.bBackup ? 1:AU_AServer2012.bClean ? 2:bSC ? 3:bAktiv ? 0:-1);
    //String s2=(bAktiv?"":" "+g.getShow("inaktiv"))+(bSC?" "+g.getShow("Stempelimport"):"")+(AU_AServer2012.bClean?" "+g.getShow("Reinigung"):"")+(AU_AServer2012.bBackup?" "+g.getShow("Backup"):"");
    //if (s2.equals("")) s2=" "+g.getShow("Leerlauf");
    return " "+g.getShow(i==0 ? "Leerlauf":i==-1 ? "inaktiv":i==-2 ? "unerreichbar":i==1 ? "Backup":i==2 ? "Reinigung":i==3 ? "Stempelimport":i==4 ? "Update":"unbekannt");
  }

  public static String get_AServer(String s)
  {
    if (!UseAServer1())
      return /*s.startsWith("status")?" "+g.getShow("unerreichbar"):*/"-2";
    try
    {
      Socket server=connect();
      if (server!=null)
      {
        new ObjectOutputStream(server.getOutputStream()).writeObject(s);
        String sGet = new Scanner(server.getInputStream()).nextLine();
        server.close();
        return sGet;//Integer.parseInt(sGet);
      }
    }
    catch(Exception e2)
    {
      Static.printError("AClient.get_AServer "+s+":" + e2);
    }
    return "-2";
  }
  
  public static String getImport(Global g)
  {
	  int iM=g.getSIMandant();
	  String s="import"+(bImportM && iM>0 ? new Zahl1(iM,"000")+"SK":"");
	  g.fixtestError("sende an AServer: "+s);
	  return s;
  }
  
  public static void sendImport(Global g,long lBits)
  {
	  if (sAServerSoll==null)
		  return;
	  if ((lBits & 0x02000000/*Abfrage.cstStempelimport*/) > 0)
		  send_AServer(getImport(g),g);
  }

  /*public static void checkLogging(Global g,int iAnz)
  {
    //int iAnz=SQL.getInteger(g,"select count(*) from logging where aus is null and aic_code is null");
    if (sAServer == null)
      return;
    else if (iAnz>0)
      g.fixInfo("Noch "+iAnz+" Benutzer eingeloggt");
    else if (iAnz==0)
    {
      g.fixInfo("Letzter Benutzer, deshalb Reinigung gestartet");
      send_AServer("clean");
    }
  }*/

}
