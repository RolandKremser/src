package All_Unlimited;

import All_Unlimited.Allgemein.Static;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import All_Unlimited.Allgemein.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import All_Unlimited.Grunddefinition.Updateeinstellung;
import javax.swing.JPanel;
//import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Parameter;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import All_Unlimited.Hauptmaske.AClient;
import java.awt.FlowLayout;
import java.util.Date;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Grunddefinition.Security;
import java.awt.Cursor;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.11
 */
public class AU_Update
{
  private static String sRun;
  private static FileOutputStream fileRun;
  private String sConnect;
  private Global g;
  private JFrame Fom;
  private String sDbUser=null;
  private String sDbPW=null;
  private JTextPane Memo = new JTextPane();
  private JButton BtnStatus;
  private JButton BtnSecurity;
  private JButton BtnTimerClose;
  //private JButton BtnTimerAktiv;
  private JButton BtnBackup;
  private JButton BtnAServerClose;
  private JButton BtnCopy;
  private JButton BtnAServerStart;
  private JButton BtnUpdate;
  private JButton BtnKeineAufheben;
  private JButton BtnTimerStart;

  private String sBtnStatus;
  private String sBtnSecurity;
  private int iPU=0;

  //private boolean bAuto=false;
  private int iSek=5;

  private String sInst=null;
  private String sWeb=null;
  private String sProg=null;
  private String sTimer=null;
  private String sTerminal=null;
  private String sAServer=null;
  private String sBilder=null;
  private String sHilfe=null;
//  private String sCss=null;
  private int iAStatus=-3;
  private int iLog=-1;
  private Style red;
  private Style black;
  private boolean bTimer=true;

  public static void main(String[] args)
  {
    if (args.length<2)
    {
      System.out.println("java -jar AU_Update.jar Connect Pfad [Options]");
      System.out.println("Options: /Auto ... startet automatisch");
      System.out.println("         /s5 ... 5 Sekunden Abstand");
      System.out.println("         /off  ... keine Konsolen-Ausgabe");
      System.out.println("         /save... Konsole speichern");
      System.out.println("         /x    ... Exit bei Disconnect");
      System.out.println("         /RC   ... reconnect (wiederverbinden nach Trennung)");
      System.out.println("         /noX11... kein Formular");
      Static.sleep(5000);
      System.exit(5);
    }
    sRun=args[1]+"Update.run";
    if (new File(sRun).exists() && !new File(sRun).delete())
            System.exit(3);
    if (new File(sRun).exists())
            System.exit(2);
    else
    {
            Save.prot(new File(sRun),"");
            try
            {
                    fileRun = new FileOutputStream(sRun,true);
            }
            catch(IOException ioe)
            {
                    System.err.println("Save.prot(): IOException - "+ioe);
                    System.exit(4);
            }
    }
    new AU_Update(args);
  }

  private void RunClose()
  {
    try
    {
            fileRun.close();
    }
    catch(IOException ioe)
    {
            System.err.println("AU_Timer.RunClose(): IOException - "+ioe);
    }
    if (!new File(sRun).delete())
        Static.printError(sRun+" not deleted");
  }

  private AU_Update( String[] args )
        {
                sConnect= args[0];
                Static.DirError = args[1];
                if (args.length>2)
                  for(int i=2;i<args.length;i++)
                    /*if(args[i].equals("/Auto"))
                      bAuto= true;
                    else*/ if(args[i].equals("/off"))
                      Transact.bAusgabe=false;
                    else if(args[i].equals("/save"))
                      Static.bSave = true;
                    else if(args[i].startsWith("/s"))
                      iSek=new Integer(args[i].substring(2)).intValue();
                    else if(args[i].equals("/x"))
                      Transact.bRealExit=true;
                    else if(args[i].equals("/RC"))
                      Transact.bRetry=true;
                    else if(args[i].equals("/noX11"))
                      Static.bX11 = false;

                Build();
                /*ParameterCheck(args);
                if (connectDatabase())
                        showTimer();
                else
                        ENDE("Connect-Fehler",false);*/
        }

  private void Build()
  {
    g = new Global(sConnect,"Update",sDbUser,sDbPW,1);
    LoadParameter();
    Static.bBilder=Static.DirImageSys!=null;
    g.fixInfo("DirImageSys="+Static.DirImageSys);
    g.bUP=true;
    g.checkSpracheLand();
    iPU=SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
    AClient.bShowError=false;
    //AClient.setg(g);
    ActionListener AL=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s = ev.getActionCommand();
        Info("->" + s + " aufgerufen",false);
        if (s.equals("Parameter"))
        {
          sAServer=null;
          Updateeinstellung.get(g).show(Fom);
        }
        else if (s.equals("Copy"))
          CopyFiles();
        else if (s.equals("status"))
          setBtnStatus(true);
        else if(s.equals("Security"))
            Security.get(g);
        else if (s.equals("Backup"))
        {
          if (Fom != null) Fom.setCursor(new Cursor(Cursor.WAIT_CURSOR));
          sendInfo("backup","Backup wird erstellt");
          String sE="x";
          while(sE.equals("x"))
          {
            Static.sleep(1000);
            sE = AClient.get_AServer("i:backup");
          }
          Info(sE);
          //boolean b=AClient.send_AServer("backup", g);
          //Info(b ? "Backup_erstellt":"kein_AServer");
          if (Fom != null) Fom.setCursor(Cursor.getDefaultCursor());
        }
        else if (s.equals("Update"))
        {
          if (Fom != null) Fom.setCursor(new Cursor(Cursor.WAIT_CURSOR));
          sendInfo("update", "Update wird eingespielt");
          bTimer=false;
          new Thread(new Runnable()
          {

            public void run()
            {
              String sE = "x";
              String sL = sE;
              while (sE.equals("x") || sE.startsWith("#"))
              {
                Static.sleep(10000);
                sE = Static.fromImport(AClient.get_AServer("i:update"));
                if(sE.startsWith("#") && !sL.equals(sE))
                {
                  sL=sE;
                  Info(sE.substring(1), false);
                }
              }
              bTimer = true;
              Info(sE);
              if (Fom != null)Fom.setCursor(Cursor.getDefaultCursor());
            }
          }).start();
        }
        else if (s.equals("Beenden"))
          Beenden();
        else if (s.equals("AServer_close"))
        {
            AClient.send_AServer("quit",g);
            AClient.setNull();
        }
        else if (s.equals("Timer_close"))
        {
          g.exec("Update parameter set " + g.int1() + "=4 where aic_parameter=" + iPU);
          AClient.send_AServer("deaktiv",g);
          Global.iInfos=Global.iInfos|Global.ALLEIN;
          g.exec("Update computer set cbits="+Global.iInfos+" where aic_computer="+g.getComputer());
        }
        //else if (s.equals("Timer_aktiv"))
        //  g.exec("Update parameter set " + g.int1() + "=0 where aic_parameter=" + iPU);
        else if (s.equals("AServer_start"))
          start_AServer();
        else if (s.equals("keineSperre"))
        {
           Global.iInfos-=Global.ALLEIN;
           g.exec("Update computer set cbits="+Global.iInfos+" where aic_computer="+g.getComputer());
        }
        else if (s.equals("Timer_start"))
          start_Timer();
        EnableButtons();
      }
    };
    //Static.bDefBezeichnung=true;
    BtnStatus=g.getButton("status","status",AL);
    BtnSecurity = g.getFrameS("Security");
    g.BtnAdd(BtnSecurity,"Security",AL);
    BtnTimerClose = g.getButton("Timer schliessen","Timer_close",AL);
    //BtnTimerAktiv = g.getButton("Timer aktivieren","Timer_aktiv",AL);
    BtnBackup = g.getButton("Backup","Backup",AL);
    BtnAServerClose = g.getButton("AServer schliessen","AServer_close",AL);
    BtnCopy = g.getButton("Kopie_Jar","Copy",AL);
    BtnAServerStart = g.getButton("AServer starten","AServer_start",AL);
    BtnUpdate = g.getButton("Update","Update",AL);
    BtnKeineAufheben = g.getButton("Sperre aufheben","keineSperre",AL);
    BtnTimerStart = g.getButton("Timer starten","Timer_start",AL);
    JButton BtnParameter = g.getButton("Parameter","Parameter",AL);
    JButton BtnBeenden = g.getButton("Beenden","Beenden",AL);
    sBtnStatus = BtnStatus.getText();
    sBtnSecurity=BtnSecurity.getText();
    Fom=new JFrame("ALL UNLIMITED - Update");
    //Fom.getContentPane().add("North",BtnStatus);
    JPanel PnlE = new JPanel(new GridLayout(0,1));
     PnlE.add(BtnStatus);
     PnlE.add(BtnSecurity);
     PnlE.add(new javax.swing.JLabel());
     PnlE.add(BtnTimerClose);
     PnlE.add(BtnBackup);
     PnlE.add(BtnAServerClose);
     PnlE.add(BtnCopy);
     //PnlE.add(BtnTimerAktiv);
     PnlE.add(BtnAServerStart);
     PnlE.add(BtnUpdate);
     PnlE.add(BtnKeineAufheben);
     PnlE.add(BtnTimerStart);
    Fom.getContentPane().add("East",PnlE);
    JPanel PnlS = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
     PnlS.add(BtnParameter);
     PnlS.add(BtnBeenden);
    Fom.getContentPane().add("South", PnlS);
    Memo.setEditable(false);
    JPanel PnlTextfeld=new JPanel(new BorderLayout(2,2));
    PnlTextfeld.add(new javax.swing.JScrollPane(Memo));
    Fom.getContentPane().add("Center", PnlTextfeld);
    Fom.setSize(800, 500);
    setBtnStatus(true);
    Fom.setVisible(true);
    if (iSek>0)
    {
      javax.swing.Timer timer = new javax.swing.Timer(iSek * 1000, new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          if (bTimer)
            setBtnStatus(false);
        }
      });
      timer.start();
    }
  }

  private void startProg(String sPfad,String sFile)
  {
    Runtime rt=Runtime.getRuntime();
    String sFS=File.separator;
    String sDir=Static.cin(sPfad,sFS);
    boolean bUnix=sFS.equals("/");
    try
    {
      rt.exec(bUnix ? sDir+sFile+".sh":"cmd /c start "+sDir+sFile+".bat");
    }
    catch(Exception e)
    {
      //System.err.println("start_"+sFile+"-Exception:"+e);
      Error("start_"+sFile+"-Exception:"+e);
    }

  }

  private void start_AServer()
  {
    bTimer=false;
    LoadParameter();
    g.exec("Update parameter set " + g.int1() + "=0 where aic_parameter=" + iPU);
    startProg(sAServer,"AServer");
    /*Runtime rt=Runtime.getRuntime();
    try
    {
      rt.exec("cmd /c start "+sAServer+"\\AServer.bat");
      //Static.sleep(2000);
      //AClient.setg(g);
    }
    catch(Exception e)
    {
      System.err.println("start_AServer-Exception:"+e);
    }*/
  new Thread(new Runnable()
          {
            public void run()
            {
              String sVer=Version.getAktVer(g);
              Static.sleep(2000);
              iAStatus = -3;
              int iAnz = 60;
              int iSek = 2;
              while (iAStatus != 0 && iAnz > 0 || iAStatus == 1)
              {
                Static.sleep(1000 * iSek);
                if (Static.bSave)
                  g.fixInfo(""+iAnz+" ("+iSek+","+iAStatus+")");
                String sVer2 = Version.getAktVer(g);
                if (!sVer.equals(sVer2))
                {
                  sVer = sVer2;
                  Info("Version=" + sVer);
                  iAnz = 60;
                  iSek = 5;
                }
                AClient.setg(g);
                iAStatus = AClient.getStatus();
                iAnz--;
              }
              g.fixInfo("Status=" + iAStatus + ", iAnz=" + iAnz);
              if (iAStatus == 0)
              {
                g.exec("Update parameter set " + g.int1() + "=3 where aic_parameter=" + iPU);
                Static.sleep(2000);
                AClient.send_AServer("deaktiv", g);
                Info("AServer gestartet", false);
              }
              else //if(iAStatus==-2)
              {
                g.exec("Update parameter set " + g.int1() + "=4 where aic_parameter=" + iPU);
                Error("AServer konnte nicht gestartet werden");
              }
              EnableButtons();
              bTimer=true;
            }
          }).start();
  }

  private void start_Timer()
  {
    AClient.send_AServer("aktiv",g);
    g.exec("Update parameter set " + g.int1() + "=0 where aic_parameter=" + iPU);
    LoadParameter();
    //Runtime rt=Runtime.getRuntime();
    //try
    //{
      if (sTerminal != null)
        startProg(sTerminal,"Terminal");
        //rt.exec("cmd /c start "+sTerminal+"\\Terminal.bat");
      if (sTimer != null)
      {
        startProg(sTimer,"Timer_m");
        startProg(sTimer,"Timer_o");
        //rt.exec("cmd /c start " + sTimer + "\\Timer_m.bat");
        //rt.exec("cmd /c start " + sTimer + "\\Timer_o.bat");
      }
      //Static.sleep(2000);
      //AClient.setg(g);
    /*}
    catch(Exception e)
    {
      System.err.println("start_AServer-Exception:"+e);
    }*/
  }

  private void sendInfo(String sSend,String sTrue)
  {
    boolean b=AClient.send_AServer(sSend, g);
    Info(b ? sTrue:"kein_AServer",!b);
    if (b)
    {
      Static.sleep(200);
      setBtnStatus(false);
    }
  }

  private void setBtnStatus(boolean b)
  {
    AClient.setg(g);
    String s0=BtnStatus.getText();
    iAStatus=AClient.getStatus();
    int iLog0=iLog;
    iLog=SQL.getInteger(g,"select count(*) from logging where aus is null");
    String s=sBtnStatus+":"+AClient.getStatus(iAStatus,g);//+" (user="+iLog+")";
    if (!s.equals(s0) || iLog!=iLog0)
    {
      BtnStatus.setText(s);
      BtnSecurity.setText(sBtnSecurity+": "+iLog+" user");
      if (!s.equals(s0))
        Info(s,false);
      EnableButtons();
    }
  }

  private void Info(String s)
  {
    boolean b=s.startsWith("!");
    Info(b? s.substring(1):s,b);
  }

  private void Info(String s,boolean bFehler)
  {
    if (red==null)
    {
      red = Memo.addStyle("red", null);
      black = Memo.addStyle("black", null);
      StyleConstants.setForeground(red, Color.red);
      StyleConstants.setForeground(black, Color.black);
    }
    int offset = Memo.getDocument().getLength();
    String sAdd=new java.text.SimpleDateFormat("HH:mm:ss").format(new Date())+"> "+s;
    g.fixInfo(sAdd);
    try
    {
      Memo.getDocument().insertString(offset, sAdd+"\n", bFehler ? red : black);
      Memo.repaint();
      Fom.addNotify();
    }
    catch( BadLocationException ex )
    {
      ex.printStackTrace();
      Static.printError("AU_Update.Info:"+ex);
    }

    //Memo.setText(Memo.getText()+new java.text.SimpleDateFormat("HH:mm:ss").format(new Date())+"> "+s+"\n");
    //Memo.repaint();
  }

  private void EnableButtons()
  {
    BtnAServerClose.setEnabled(iAStatus==0 || iAStatus==-1);
    BtnBackup.setEnabled(iAStatus==0 || iAStatus==-1);
    BtnCopy.setEnabled(iAStatus==-2 && iLog==0);
    BtnAServerStart.setEnabled(iAStatus==-2);
    BtnUpdate.setEnabled(iAStatus==-1 && iLog==0);
    BtnTimerStart.setEnabled(iAStatus==0 || iAStatus==-1);
    BtnKeineAufheben.setEnabled((Global.iInfos&Global.ALLEIN)>0);
  }

  private void Error(String s)
  {
    Static.printError(s);
    Info("!!! "+s,true);
  }

  private String FileWithDate(String sFile)
  {
    String sExt=sFile.substring(sFile.lastIndexOf("."));
    String sFileName=sFile.substring(0,sFile.lastIndexOf("."));
    return sFileName+new java.text.SimpleDateFormat("_yyyyMMdd_HHmmss").format(new java.util.Date())+sExt;
  }

  private File getFile(String sPfad,String sFile)
  {
    return new File(Static.cin(sPfad, File.separator) + sFile);
  }

  private void copyFile(String sFile,String sFrom,String sTo)
  {
    boolean b=false;
    if (sFrom!=null && sTo!=null)
    {
      File FilFrom = getFile(sFrom, sFile); //new File(Static.cin(sFrom, File.separator) + sFile);
      File FilTo = getFile(sTo, sFile); //new File(Static.cin(sTo, File.separator) + sFile);
      if (FilTo.exists())
        FilTo.renameTo(getFile(sTo, FileWithDate(sFile))); //new File(Static.cin(sTo, File.separator) + FileWithDate(sFile)));
      b = Static.copyFile(FilFrom, FilTo);
      if (b)
        Info(sFile + " kopiert",false);
      else
        Error(sFile + " konnte nicht kopiert werden");
    }
  }

  private void copyDir(String sDir,String sFrom,String sTo)
  {
    if (sFrom!=null && sTo!=null)
    {
      String sFS=File.separator;
      boolean bUnix=sFS.equals("/");
      try
      {
        Runtime.getRuntime().exec((bUnix ? "cp -R ":"robocopy ") + Static.cin(sFrom, sFS)+ sDir + (bUnix ?"/* \"":" \"") + sTo +(bUnix ? "\"":"\" /E /NP"));
        Info(sDir + " kopiert",false);
      }
      catch (IOException io)
      {
        Error(sDir + " konnte nicht kopiert werden");
        Error("IOException-Error: " + io);
      }
    }
  }

  private void makeAllT(String sInst)
  {
    if (sInst!=null)
    {
      String sAllT="AllT.jar";
      File FilTo = getFile(sInst,sAllT);
      String sAllx="Allx.jar";
      if (g.MySQL())
        sAllx="AllMy.jar";
      else if (g.MS())
        sAllx="AllMS.jar";
      else if (g.Oracle())
        sAllx="AllOra.jar";
      else if (g.ASA())
        sAllx="AllASA.jar";
      File FilFrom = getFile(sInst,sAllx);
      if (FilFrom.exists())
      {
        if (FilTo.exists())
          FilTo.renameTo(getFile(sInst,FileWithDate(sAllT)));
        FilFrom.renameTo(FilTo);
      }
    }
  }

  private void LoadParameter()
  {
    if (sAServer==null)
    {
      Parameter Para = new Parameter(g, "Update");
      Tabellenspeicher Tab = Para.getParameter("Dir");
      for (Tab.moveFirst(); !Tab.out(); Tab.moveNext())
      {
        int i = Tab.getI("int1");
        String s = Tab.getS("Parameterzeile");
        if (i == Updateeinstellung.INST)
          sInst = s;
        else if (i == Updateeinstellung.WEB)
          sWeb = s;
        else if (i == Updateeinstellung.PROG)
          sProg = s;
        else if (i == Updateeinstellung.TIMER)
          sTimer = s;
        else if (i == Updateeinstellung.TERM)
          sTerminal = s;
        else if (i == Updateeinstellung.ASERVER)
          sAServer = s;
        else if (i == Updateeinstellung.IMG)
        {
          sBilder=s;
          Static.DirImageSys = "file:\\" + Static.cin(s, File.separator);
        }
        else if (i == Updateeinstellung.HELP)
          sHilfe=s;
//        else if (i == Updateeinstellung.CSS)
//          sCss=s;
      }
      Para.free();
    }
  }

  private void CopyFiles()
  {
    LoadParameter();
    makeAllT(sInst);
    //copyFile("All.jar",sInst,sWeb);
    copyFile("All2.jar",sInst,sWeb);
    copyFile("AllT.jar",sInst,sWeb);
    if (sProg!=null && (sWeb==null || !sWeb.equals(sProg)))
    {
    	copyFile("All2.jar",sInst,sProg);
        copyFile("AllT.jar",sInst,sProg);
    }
    copyFile("AU_Timer.jar",sInst,sTimer);
    copyFile("AU_Terminal.jar",sInst,sTerminal);
    copyFile("AU_AServer.jar",sInst,sAServer);
    copyDir("Images",sInst,sBilder);
    copyDir("HILFE",sInst,sHilfe);
//    copyDir("css",sInst,sCss);
  }

  private void Beenden()
  {
    g.unConnect();
    if (Fom!=null)
      Fom.dispose();
    RunClose();
    System.exit(0);
  }

}
