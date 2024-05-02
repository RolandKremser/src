package All_Unlimited;
/*
    AU_Terminal.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/
// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerComponent;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Save;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.TabTerminal;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Terminal;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Zeitraum;
import All_Unlimited.Hauptmaske.AClient;

import javax.swing.JLabel;
//import All_Unlimited.Grunddefinition.Systemeinstellung;

public class AU_Terminal
{
    /**
	 *
	 */
	private static final long serialVersionUID = -7563596558412204286L;
	public static void main( String[] args )
    {
		if (args.length<2)
		{
                  System.out.println("java -jar AU_Terminal.jar Connect Dateiname [Options]");
                  System.out.println("Connect: z.B.: 127.0.0.1:2638");
                  System.out.println("Dateiname: z.B.: c:\\All_Unlimited\\AU_Timer\\");
                  System.out.println("Options: /Auto... startet automatisch");
                  System.out.println("         /off ... keine Konsolen-Ausgabe");
                  System.out.println("         /x   ... Exit bei Disconnect");
                  System.out.println("         M=xx ... nur Mandant xx");
                  System.out.println("         C=xx ... Ersatzverbindung");
                  System.out.println("         A=xx ... Alarm-Abfrage");
                  System.out.println("         B=xx ... Abwesenheits-Abfrage");
                  System.out.println("         T=xx ... Terminal-Abfrage");
                  System.out.println("         Nrxx ... xx=Nummer");
                  System.out.println("         LZxx ... Anzahl der Lebenszeichen zwischen Upload-Check");
                  System.out.println("         LSxx ... Anzahl der Lebenszeichen zwischen Status-Check");
                  System.out.println("         LAxx ... Anzahl der Lebenszeichen zwischen Alarm-Check");
                  System.out.println("         DSxx ... Mindestzeit zwischen Spempelungen");
                  System.out.println("         /RC  ... reconnect (wiederverbinden nach Trennung)");
                  System.out.println("         U=xx  ... xx=Datenbankuser");
                  System.out.println("         P=yy  ... yy=Datenbankpasswort (statt sql)");
                  System.out.println("         /ZMxx... Zone minus Minuten");
                  System.out.println("         /UL  ... Upload later");
                  System.out.println("         /save... Konsole speichern");
                  //System.out.println("         /wi  ... Meldung auch nach schreiben");
                  System.out.println("         /i12 ... Init (Neu-Log) alle 12 Stunden");
                  System.out.println("         noMac... Mac-Adresse nicht verwenden");
                  System.out.println("         noX11... kein Formular");
                  System.out.println("         noCC ... kein CheckConnects");
                  System.out.println("         s5   ... 5s Abstand zwischen Terminal-Verbindungen");
                  System.out.println("         ww100... 100 ms zwischen Stammupload-Zeilen");
                  //System.out.println("         ASERVER=127.0.0.1:4444 ... gibt Application-Server an");
                  Static.sleep(5000);
                  System.exit(1);
		}
		else
		{
			sRun=args[1]+"Terminal.run";
			if (new File(sRun).exists() && !new File(sRun).delete())
				System.exit(3);
			if (new File(sRun).exists())
				System.exit(2);
			else
			{
				Save.prot(new File(sRun),"");
				try
				{
					Terminal.fileRun = new FileOutputStream(sRun,true);
				}
				catch(IOException ioe)
				{
					System.err.println("Save.prot(): IOException - "+ioe);
					System.exit(4);
				}
			}
			new AU_Terminal(args);
		}

		//new AU_Terminal(new String[] {"192.1.1.232:2644","r:\\prog\\Angela2\\"});
    }

    private void checkMecs()
    {
      bMecsReady = false;
      Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from zwischenspeicher where kennung='Mecs' and pro_aic_protokoll is null",true);
      g.fixInfo("checkMecs:"+Tab.size()+" neue Daten");
      if (!Tab.isEmpty())
      {
        int iProt=g.Protokoll("Terminal",0/*unknown*/);
        SQL Qry=new SQL(g);
        for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
        {
          String s=Tab.getS("Zwischentext");
          String sKarte=s.substring(19,27);
          String sDate=s.substring(5,19);
          String sArt=s.substring(3,5);
          sKarte=RadMiro.isSelected()?Static.MiroCalc(sKarte,true):Static.FillNull(sKarte,RadSymbol.isSelected() ? 16:
              RadMifare.isSelected() || RadMifare2.isSelected() || RadHitagHex.isSelected() ? 10: RadMifareD.isSelected() ? 17 :
            	  RadNova.isSelected() ? 14 : RadRo.isSelected() ? 12:RadSonst.isSelected() ? iKL: 8);
          java.sql.Timestamp ts=new DateWOD(new java.text.SimpleDateFormat("yyyyMMddHHmmss").parse(sDate,new java.text.ParsePosition(0))).toTimestamp();
          String s2="J4321]00"+sArt+sKarte+sDate+"0;20\r";
          Qry.add("Aic_protokoll", iProt);
          Qry.add("Kennung", "Import");
          Qry.add("Terminal", "Kunde");
          Qry.add("Gueltig", ts);
          Qry.add("Zwischentext", s2);
          Qry.add("aic_mandant",g.getMandant());
          Qry.insert("Zwischenspeicher", false);
          Qry.exec("update zwischenspeicher set pro_aic_protokoll="+iProt+" where aic_protokoll="+Tab.getI("aic_protokoll"));
        }
        Qry.free();
      }
      bMecsReady = true;
    }

	private AU_Terminal(String[] args)
	{
		//super("AU_Terminal");
		Static.DirError = args[1];
		//Static.bMemo=false;
                Terminal.sPath=args[1];
                sConnect=args[0];
                String sTry=sConnect;
                String sAlarm=null;
                String sAbwesenheit=null;
                boolean bTermAbf=false;
		if (args.length>2)
                  for(int i=2;i<args.length;i++)
                     if(args[i].equals("/Auto"))
                       bAuto= true;
                     else if(args[i].equals("/off"))
                       bAusgabe=false;
                     else if(args[i].equals("/x"))
                       Terminal.bExit=true;
                     else if(args[i].startsWith("M="))
                       sMandant=args[i].substring(2);
                     else if(args[i].startsWith("C="))
                       sConnect2=args[i].substring(2);
                     else if(args[i].startsWith("T="))
                     {
                       bTermAbf=true;
                       sTerminalAbfrage = args[i].substring(2);
                     }
                     else if(args[i].startsWith("A="))
                       sAlarm=args[i].substring(2);
                     else if(args[i].startsWith("B="))
                       sAbwesenheit=args[i].substring(2);
                     else if(args[i].startsWith("Nr"))
                       sNr=args[i].substring(2);
                     else if(args[i].startsWith("/to")) // Timeout für Befehle zwischendurch
                       Transact.iTimeOut=new Integer(args[i].substring(3)).intValue();
                     else if(args[i].startsWith("/tc")) // Connection-Timeout in s
                       Transact.iTimeOutC=new Integer(args[i].substring(3)).intValue();
                     else if(args[i].startsWith("/ts")) // Socket-Timeout in min beim Verbinden
                       Transact.iTimeOutS=new Integer(args[i].substring(3)).intValue();
                     else if(args[i].startsWith("/AA")) // automatisches Ausloggen [h]
                         Transact.iLogOut=new Integer(args[i].substring(3)).intValue();
                     else if(args[i].startsWith("/QryAb"))
                       Transact.lQryAb=new Integer(args[i].substring(6)).intValue();
                     else if(args[i].startsWith("/AbfAb"))
                            Static.lAbfAb=new Long(args[i].substring(6)).longValue();
                     else if(args[i].startsWith("LZ"))
                       Terminal.iLZ=new Integer(args[i].substring(2)).intValue();
                     else if(args[i].startsWith("LS"))
                       Terminal.iLS=new Integer(args[i].substring(2)).intValue();
                     else if(args[i].startsWith("LA"))
                       Terminal.iLA=new Integer(args[i].substring(2)).intValue();
                     else if(args[i].startsWith("DS"))
                       Terminal.iDS=new Integer(args[i].substring(2)).intValue();
                     else if(args[i].equals("/RC"))
                       Transact.bRetry=true;
                     else if(args[i].equals("/fast"))
                       Transact.bFast=true;
                     else if(args[i].startsWith("U="))
                       sDbUser=args[i].substring(2);
                     else if(args[i].startsWith("P="))
                       sDbPW=args[i].substring(2);
                     else if(args[i].equals("/UL"))
                       bUL=true;
                     else if(args[i].equals("/save"))
                       Static.bSave = true;
//                     else if(args[i].equals("/wi"))
//                       Terminal.bWI = true;
                     else if(args[i].startsWith("/ZM"))
                       Transact.iZoneOffset=new Integer(args[i].substring(3)).intValue();
                     else if(args[i].startsWith("/i"))
                       lInit=getInitdauer(args[i].substring(2));
                     else if(args[i].equals("noMac"))
                       Static.cMacArt = '-';
                     else if(args[i].startsWith("/MA") && args[i].length()>3)
                       Static.cMacArt = args[i].charAt(3);
                     else if(args[i].equals("/Test"))
                       Transact.bTest=true;
                     else if(args[i].equals("noX11"))
                       Static.bX11 = false;
                     else if(args[i].equals("noCC"))
                       Transact.bCheckConnect = false;
                     else if(args[i].startsWith("s"))
                       iSec=new Integer(args[i].substring(1)).intValue();
                     else if(args[i].startsWith("ww"))
                       Terminal.iWW=new Integer(args[i].substring(2)).intValue();
                     //else if(args[i].startsWith("ASERVER="))
                     //  Terminal.sAServer=args[i].substring(8);

                if (Static.bX11)
                {
                  Fom=new JFrame("AU_Terminal - " + (bTermAbf ? sTerminalAbfrage : sMandant != null ? sMandant : sConnect) +
                           " - " + Version.getVersion() + " (" + Version.getDate() + ")");
                }
                Transact.fixInfoS("Datum/Uhrzeit="+new DateWOD().Format("dd.MM.yyyy HH:mm"));
                Transact.fixInfoS("All Unlimited-Version="+Version.getVersion());
		Transact t = new Transact();
		t.bExit = false;
                Transact.bAusgabe=bAusgabe;
                if (lInit>0)
                  Transact.fixInfoS("Init alle "+(lInit/3600000.0)+" h");
		int i=0;
		while (!t.connect(sTry) && i<6)
		{
			//long l=Static.get_ms();
			//while(Static.get_ms()-l < 20000);
                        if (sConnect2 != null)
                          sTry = sConnect2;
                        else
                          Static.sleep(20000);
			i++;
		}
		if (!t.isConnected())
			return;
		t.unConnect();
                ProtSave("TERMINAL");
                if (Version.Test())
                  Static.printError("Diese Testversion kann Daten zerstören!");
                  // if (Static.bX11)
                  //   JOptionPane.showMessageDialog(new JFrame(),"Diese Testversion kann Daten zerstören!","Achtung Testversion",JOptionPane.WARNING_MESSAGE);
                  // else
                                 
		g = new Global(sTry,"Terminal"+sNr,sDbUser,sDbPW,1);
                LoadDir(g);
                Static.bBilder =Static.DirImageSys != null;
                //Static.DirError = args[1];
                Terminal.iPU=SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
                Terminal.checkExit2(g);
                g.bExit = false;
                int iDO=SQL.getInteger(g,"select "+g.int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Option'");
                //Static.bView2 = (iDO&1)>0;
                Static.bInsert2 = (g.MS() || g.MySQL()) && (iDO&2)>0;
                //g.bCC=!g.MS() && bCC;
                //if (g.ASA() || g.MySQL()) Static.bView2=false;
                if (g.Oracle() || g.MS()) Terminal.bExit=true;
                Transact.bAusgabe=bAusgabe;
                if (sMandant != null)
                  g.setMandantT(sMandant);
                g.ComputerErmitteln();
                g.Login();
                if (g.getLog()<=0)
                {
                  Static.printError("ENDE da nicht einloggbar");
                  ENDE();
                }
                ShowAbfrage.refreshBerechtigung(g);
                if (sAlarm != null)
                  Terminal.AbfAlarm=new ShowAbfrage(g,sAlarm);
                if (sAbwesenheit != null)
                {
                  Terminal.AbfAbwesend = new ShowAbfrage(g, sAbwesenheit);
                  if (!Terminal.AbfAbwesend.Fehler())
                  {
                    Tabellenspeicher TabSpalten = Terminal.AbfAbwesend.getSpalten();
                    if(TabSpalten.posInhalt("Datentyp", "BewStamm"))
                      Terminal.iAbwStamm = TabSpalten.getI("Kennung2");
                    if(TabSpalten.posInhalt("Datentyp", "BewBoolean"))
                      Terminal.iAbwBool = TabSpalten.getI("Kennung2");
                  }
                  //g.testInfo("Eigenschaften Abwesenheit: Stamm="+Terminal.iAbwStamm+", Boolean="+Terminal.iAbwBool);
                  if (Terminal.iAbwStamm==0 || Terminal.iAbwBool==0)
                  {
                    Static.printError("Eigenschaften von Abwesenheit nicht gefunden!");
                    Static.sleep(5000);
                    System.exit(5);
                  }
                }
                Build();
                LoadParameter();
                autoUpdate();
		g.Logout(false);
                timer = new javax.swing.Timer(60000,new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    if (!bTimer)
                    {
                      bTimer=true;
                      long lClock = Static.get_ms();
                      if (lInit > 0 && lInitNext > 0 && lClock > lInitNext)
                      {
                        ProtSave("Zwischenstop");
                        Stop();
                        Static.sleep(2000);
                        Start();
                      }
                      else
                      {
                        if (CbxMecs.isSelected() && bMecsReady)
                          checkMecs();
                        for (int i = 0; VecThreads != null && i < VecThreads.size(); i++)
                        {
                          if (VecThreads.elementAt(i) != null)
                          {
                            Terminal T = (Terminal)VecThreads.elementAt(i);
                            long lDif = T.Dif();
                            g.testInfo(T.sIp_Port + "=" + T.Aktiv() + "/" + lDif);
                            if (!T.Aktiv() && lDif > 599000 || !T.bThread)
                            {
                              T.Close();
                              //T.bThread=false;
                              String s = T.sIp_Port;
                              //if (T != null)
                              //  T.destroy();
                              T = null;
                              VecThreads.setElementAt(null, i);

                              Static.sleep(1000);
                              T = new Terminal(s, g, bUL);
                              T.start();
                              VecThreads.setElementAt(T, i);
                              g.testInfo("VecThreads=" + VecThreads);
                              //T.Connect(null,g);
                              //T.bThread=true;
                              //T.start();
                            }
                          }
                        }
                      }
                      bTimer=false;
                    }
                  }
                });
                //timer.setDelay(10000);

		if(bAuto || bStart)
                  Start();

                if (!Static.bX11)
                {
                  /*while(true)
                  {
                    Static.sleep(500);
                    Thread.yield();
                  }*/
                  java.util.TimerTask task = new java.util.TimerTask()
                  {
                    public void run()
                    {
                    	double dUsed=Static.Used();
                        if (dUsed>0.9)//(rt.maxMemory()*9/10 < rt.totalMemory())
                          ENDE();
                        else if (dUsed>0.6)
                        	System.out.println("Used: "+new Zahl1(dUsed,"#0.0 %")+Static.Mem(true));
                      /*Runtime rt=Runtime.getRuntime();
                      if (rt.maxMemory()*9/10 < rt.totalMemory())
                        ENDE();
                      else if (rt.maxMemory()/2 < rt.totalMemory())
                        System.out.println((rt.totalMemory()/1024/1024)+" MB von "+(rt.maxMemory()/1024/1024)+" MB");*/
                    }
                  };
                  java.util.Timer tim = new java.util.Timer();
                  tim.schedule(task, 5000, 5000);
                }
	}

        private static long getInitdauer(String s)
        {
          int i=s.length();
          if (i==0)
            return 0;
          else
          {
            if (s.charAt(i-1)=='m')
              return new Long(s.substring(0,i-1)).longValue()*60000;
            else if (s.charAt(i-1)=='h')
              return new Long(s.substring(0,i-1)).longValue()*3600000;
            else if (s.charAt(i-1)=='d')
              return new Long(s.substring(0,i-1)).longValue()*3600000*24;
            else
              return new Long(s).longValue()*3600000;
          }
        }

  private static void setDir(int iConst,String sDir)
  {
    if (Static.Leer(sDir))
      return;
    if (iConst==2/*ISYS*/)
          Static.DirImageSys=sDir;
      else if (iConst==8/*ASERV*/)
      {
        AClient.sAServerSoll=new String(sDir);
        if (AClient.sAServerSoll != null && AClient.sAServerSoll.indexOf(':')<1)
        	AClient.sAServerSoll=null;
      }
  }

  public static void LoadDir(Global g)
  {
    Parameter Para = new Parameter(g,"System");
    Tabellenspeicher Tab=Para.getParameter("Dir");
    //Tab.showGrid("Tab-Dir");
    for (Tab.moveFirst();!Tab.out();Tab.moveNext())
      setDir(Tab.getI("int1"), Tab.getS("Parameterzeile"));

      g.fixtestInfo("AServer=" + AClient.sAServerSoll);
      g.fixtestInfo("DirError=" + Static.DirError);
      g.fixtestInfo("DirImageSys=" + Static.DirImageSys);
      //g.fixtestInfo("DirImageDef=" + Static.DirImageDef);
      //g.fixtestInfo("DirImageStamm=" + Static.DirImageStamm);
      //g.fixtestInfo("DirDoku=" + Static.DirDoku);

    Para.free();
  }

        private void LoadParameter()
        {
          Para=new Parameter(g,"Terminal");
          Para.getParameter("Start",false,false);
          bStart=Para.int1==1;
          Para.getMParameterH("Werte");
          if(Para.bGefunden)
          {
        	  if (Static.bX11)
        	  {
                  TxtAnzKST.setValue(Para.int2);
                  TxtAnzTaet.setValue(Para.int3);
                  TxtAnzAuft.setValue(Para.int4);
        	  
                  CbxBDENull.setSelected((Para.int1 & TabTerminal.BDE00) > 0);
                  CbxDatei.setSelected((Para.int1 & TabTerminal.DATEI) > 0);
                  CbxKarteNull.setSelected((Para.int1 & TabTerminal.KARTE0) > 0);
        	  }
                  RadHitag.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.HITAG);
                  RadMiro.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.MIRO);
                  RadLegic.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.LEGIC);
                  RadRo.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.RO);
                  RadSymbol.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.SYMBOL);
                  RadMifare.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARE);
                  RadMifare2.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARE2);
                  RadMifareD.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARED);
                  RadNova.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.NOVA);
                  RadHitagHex.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.HITAGHEX);
                  RadMifare8.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARE8);
                  RadSonst.setSelected((Para.int1&TabTerminal.KARTE)==TabTerminal.SONST);
                  CbxMecs.setSelected((Para.int1&TabTerminal.MECS)>0);
                  showBits(Para.int1);
          }
        }

        private void CloseAll()
        {
          boolean bOn=true;
          int iAnz=0;
          while(bOn && iAnz<300)
          {
            bOn=false;
            for(int i = 0; VecThreads != null && i < VecThreads.size(); i++)
              if(VecThreads.elementAt(i) != null) {
                Terminal T=(Terminal)VecThreads.elementAt(i);
                if (T.Aktiv())
                {
                  bOn = true;
                  Thread.yield();
                  Static.sleep(500);
                }
                else
                {
                  T.Close();
                  VecThreads.setElementAt(null,i);
                }
              }
            iAnz++;
          }
          g.testInfo("CloseAll:"+iAnz);
        }
   private int showBits(int iBits)
   {
	 iKL=TabTerminal.Kartenlaenge(false,iBits);
     String s=""+iKL;
       if (RadHitag.isSelected() || RadLegic.isSelected() || RadMifare2.isSelected())
         s+=" - 2";
       else if (RadMiro.isSelected())
         s+=" Miro";
       else if (RadHitagHex.isSelected())
         s+=" Hex";
       LblKL.setText(s);
       return iBits;
   }

	private void Build()
	{
              Out.setColumnButtons(new String[] {g.getBegriffS("Show","Kartennummer"),g.getBegriffS("Show","Art"),g.getBegriffS("Show","Zeit"),g.getBegriffS("Show","KST"),g.getBegriffS("Show","TaetigkeitsNr"),g.getBegriffS("Show","AuftragsNr"),g.getBegriffS("Show","IP/Port"),g.getBegriffS("Show","SubLeser"),g.getBegriffS("Show","Fehler")});
              Out.setNumColumns(9);
              Out.setColumnLabelSortMethod(Sort.sortMethod);
              Out.setBackground(Color.white);
              Out.setRootVisible(false);
              Out.setAllowMultipleSelections(true);

              BtnAus_Datei_Laden = g.getButton("Aus_Datei_Laden");
              BtnAus_DB_Laden = g.getButton("Aus_DB_Laden");
              BtnFreischalten = g.getButton("Freischalten");
              //BtnParametersetzen = g.getButton("Parametersetzen");
              //BtnHinzufuegen = g.getButton("Hinzufuegen");
              BtnStart= g.getButton("Start");
              BtnStop = g.getButton("Stop");
              BtnZeitraum = g.getButton("Zeitraum");
              BtnEnde = g.getButton("Beenden");
              if (Static.bX11)
              {
                  Fom.getContentPane().setLayout(new BorderLayout(2, 2));
                  JPanel Pnl = new JPanel(new GridLayout(1, 0, 2, 2));
                  Pnl.add(BtnStart);
                  Pnl.add(BtnStop);
                  Pnl.add(BtnEnde);
                  Fom.getContentPane().add("South", Pnl);

                  Pnl = new JPanel(new BorderLayout(2, 2));
                  JPanel Pnl2 = new JPanel(new GridLayout(1, 0, 2, 2));
                  Pnl2.add(BtnZeitraum);
                  Pnl2.add(BtnAus_DB_Laden);
                  Pnl2.add(BtnAus_Datei_Laden);
                  Pnl2.add(BtnFreischalten);
                  Pnl.add("South", Pnl2);
                  Pnl.add("Center", Out);
                  Fom.getContentPane().add("Center", Pnl);

                  Pnl = new JPanel(new BorderLayout(2, 2));
                  Pnl2 = new JPanel(new BorderLayout(2, 2));
                  JPanel Pnl3 = new JPanel(new GridLayout(0, 1, 2, 2));
                  g.addLabel4(Pnl3, "Anz. KST");
                  g.addLabel4(Pnl3, "Anz. Taetigkeit");
                  g.addLabel4(Pnl3, "Anz. Auftrag");
                  g.addLabel4(Pnl3, "Karte ohne fuehrende Nullen");
                  g.addLabel4(Pnl3, "BDE mit fuehrende Nullen");
                  g.addLabel4(Pnl3, "In Datei sichern");
                  g.addLabel4(Pnl3, "Mecs-Terminal");
                  g.addLabel4(Pnl3, "Hitag-Leser");
                  g.addLabel4(Pnl3, "Miro-Leser");
                  g.addLabel4(Pnl3, "Legic-Leser");
                  g.addLabel4(Pnl3, "Ro-Leser");
                  g.addLabel4(Pnl3, "Symbol-Leser");
                  g.addLabel4(Pnl3, "Mifare-Leser");
                  g.addLabel4(Pnl3, "Mifare2-Leser");
                  g.addLabel4(Pnl3, "Mifare Desfire-Leser");
                  g.addLabel4(Pnl3, "Novachron");
                  g.addLabel4(Pnl3, "HitagHex-Leser");
                  g.addLabel4(Pnl3,"Mifare8-Leser");
                  g.addLabel4(Pnl3,"Sonst-Leser");
                  g.addLabel4(Pnl3,"Kartenlaenge");
                  Pnl2.add("West", Pnl3);
                  Pnl3 = new JPanel(new GridLayout(0, 1, 2, 2));
                  Pnl3.add(TxtAnzKST);
                  Pnl3.add(TxtAnzTaet);
                  Pnl3.add(TxtAnzAuft);
                  Pnl3.add(CbxKarteNull);
                  Pnl3.add(CbxBDENull);
                  Pnl3.add(CbxDatei);
                  Pnl3.add(CbxMecs);
                  ButtonGroup RadGroup = new ButtonGroup();
                  RadGroup.add(RadHitag);
                  RadGroup.add(RadMiro);
                  RadGroup.add(RadLegic);
                  RadGroup.add(RadRo);
                  RadGroup.add(RadSymbol);
                  RadGroup.add(RadMifare);
                  RadGroup.add(RadMifare2);
                  RadGroup.add(RadMifareD);
                  RadGroup.add(RadNova);
                  RadGroup.add(RadHitagHex);
                  RadGroup.add(RadMifare8);
                  RadGroup.add(RadSonst);
                  Pnl3.add(RadHitag);
                  Pnl3.add(RadMiro);
                  Pnl3.add(RadLegic);
                  Pnl3.add(RadRo);
                  Pnl3.add(RadSymbol);
                  Pnl3.add(RadMifare);
                  Pnl3.add(RadMifare2);
                  Pnl3.add(RadMifareD);
                  Pnl3.add(RadNova);
                  Pnl3.add(RadHitagHex);
                  Pnl3.add(RadMifare8);
                  Pnl3.add(RadSonst);
                  Pnl3.add(LblKL);
                  Pnl2.add("Center", Pnl3);
                  //Pnl2.add("South", BtnParametersetzen);
                  Pnl.add("North", Pnl2);
                  /*
                     Pnl2 = new JPanel(new BorderLayout(2,2));
                     Pnl3 = new JPanel(new GridLayout(0,1,2,2));
                     Pnl3.add(new JLabel("Kartennummer: "));
                     Pnl3.add(new JLabel("Datum/Uhrzeit: "));
                     Pnl3.add(new JLabel("Stempelungsart: "));
                     Pnl3.add(new JLabel("KST: "));
                     Pnl3.add(new JLabel("Tätigkeit: "));
                     Pnl3.add(new JLabel("Auftrag: "));
                     Pnl2.add("West",Pnl3);
                     Pnl3 = new JPanel(new GridLayout(0,1,2,2));
                     Pnl3.add(TxtAnzKST);
                     Pnl3.add(new JLabel("!!!"));
                     Pnl3.add(new JLabel("!!!"));
                     Pnl3.add(new JLabel("!!!"));
                     Pnl3.add(new JLabel("!!!"));
                     Pnl3.add(new JLabel("!!!"));
                     Pnl3.add(new JLabel("!!!"));
                     Pnl2.add("Center",Pnl3);
                     Pnl2.add("South",BtnHinzufuegen);
                     Pnl.add("North",Pnl2);*/
                  Fom.getContentPane().add("East", Pnl);
                  Fom.pack();

		BtnAus_DB_Laden.addActionListener(new ActionListener()
		{
                  public void actionPerformed(ActionEvent e)
                  {
                    //g.testInfo("Zeitraum1="+g.getVon()+" - "+g.getBis());
                    if (!g.isConnected())
			g.connect(null);
                    Para.getMParameterH("Werte");
                    //g.testInfo("Zeitraum2="+g.getVon()+" - "+g.getBis());
                    //g.testInfo("Zeitraum3="+SQL.getTimestamp(g,"select von")+" - "+SQL.getTimestamp(g,"select bis"));
                    SQL Qry = new SQL(g);
                    String s="select distinct "+(g.ASA() || g.MySQL()?"date(gueltig)":g.Oracle()?"trunc(gueltig)":g.MS()?"CAST(CONVERT(char(8),gueltig, 112) AS datetime)":"Datum")+
                                " datum from zwischenspeicher where kennung='Import' and gueltig>="+g.von()+" and gueltig<"+g.bis()+" order by datum desc";
                    //g.testInfo(s);
                    if(Qry.open(s));
                    {
                      JCOutlinerFolderNode NodeRoot=(JCOutlinerFolderNode)Out.getRootNode();
                      NodeRoot.removeChildren();
                      for(;!Qry.eof();Qry.moveNext())
                      {
                        Vector<Date> VecVisible = new Vector<Date>();
                        Vector<?> VecInvisible = new Vector();
                        VecVisible.addElement(Qry.getD("datum"));
                        VecInvisible.addElement(null);
                        JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,NodeRoot);
                        Node.setUserData(VecInvisible);
                        Node.setState(BWTEnum.FOLDER_CLOSED);
                      }
                      Qry.close();

                      Out.folderChanged(NodeRoot);
                    }
                    Qry.free();

                    bDB=true;
                    EnableButtons();
                  }
		});
		BtnAus_Datei_Laden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String[] sFiles = new File(Terminal.sPath).list();
				JCOutlinerFolderNode NodeRoot=(JCOutlinerFolderNode)Out.getRootNode();
				NodeRoot.removeChildren();
				for(int i=0;i<sFiles.length;i++)
				{
					if(sFiles[i].endsWith(".term"))
					{
						Vector<String> VecVisible = new Vector<String>();
						Vector<Object> VecInvisible = new Vector<Object>();
						VecVisible.addElement(sFiles[i]);
						VecInvisible.addElement(null);
						JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,NodeRoot);
						Node.setUserData(VecInvisible);
						Node.setState(BWTEnum.FOLDER_CLOSED);
					}
				}
				Out.folderChanged(NodeRoot);

				bDB=false;
				EnableButtons();
			}
		});
		BtnFreischalten.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JCOutlinerNode[] Nodes = Out.getSelectedNodes();
				if(Nodes.length>0 && Nodes[0].getLevel()==2)
				{
					Vector<Object> Vec = new Vector<Object>();
					for(int i=0;i<Nodes.length;i++)
						Vec.addElement(Nodes[i].getUserData());

					g.exec("UPDATE Zwischenspeicher SET Pro_AIC_Protokoll=null where AIC_Zwischenspeicher "+Static.SQL_in(Vec));
				}
			}
		});
		/*BtnParametersetzen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(BtnStart.isEnabled())
					g.Login();
                                      Para.setMParameter("Werte","",(CbxBDENull.isSelected()? TabTerminal.BDE00:0)+(CbxDatei.isSelected()? TabTerminal.DATEI:0)+
                                                   (CbxKarteNull.isSelected()? TabTerminal.KARTE0:0)+(CbxMecs.isSelected()?TabTerminal.MECS:0)+
                                                   (RadMiro.isSelected()?TabTerminal.MIRO:RadLegic.isSelected()?TabTerminal.LEGIC:RadRo.isSelected()?TabTerminal.RO:RadSymbol.isSelected()?TabTerminal.SYMBOL:
                                                    RadMifare.isSelected()?TabTerminal.MIFARE:RadMifare2.isSelected()?TabTerminal.MIFARE2:RadMifareD.isSelected()?TabTerminal.MIFARED:
                                                    	RadNova.isSelected()?TabTerminal.NOVA:RadHitagHex.isSelected()?TabTerminal.HITAGHEX:0)
                                                   ,TxtAnzKST.intValue(),TxtAnzTaet.intValue(),TxtAnzAuft.intValue(),true);
				//Para.getMParameter("Werte",true);
				if(BtnStart.isEnabled())
					g.Logout(true);
			}
		});*/
		BtnStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                          ProtSave("Start");
                          Start();
			}
		});
		BtnStop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                          Stop();
                          ProtSave("Stop");
			}
		});
                BtnZeitraum.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                          if (!g.isConnected())
                            g.connect(null);
                          Zeitraum.get(g).show();
                        }
                });

		BtnEnde.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                          ProtSave("Beenden");
				ENDE();
			}
		});

		Out.addItemListener(new JCOutlinerListener()
		{
			 public void itemStateChanged(JCItemEvent ev)
			 {
			 }

			 public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
			 {
			 }

			 public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
			 {
				JCOutlinerNode NodeSelected = Out.getSelectedNode();
				Vector VecInvisible = NodeSelected!=null?(Vector)NodeSelected.getUserData():null;
				if(VecInvisible!=null && NodeSelected.getState()==5 && VecInvisible.elementAt(0)==null )
					LoadStamps((JCOutlinerFolderNode)NodeSelected);
				EnableButtons();
			 }

			 public void outlinerNodeSelectBegin(JCOutlinerEvent ev)
			 {
			 }

			 public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
			 {
			 }
		});

		Fom.addWindowListener(new WindowListener()
		{
			public void windowActivated(WindowEvent e)
			{
			}
			public void windowClosed(WindowEvent e)
			{
			}
			public void windowClosing(WindowEvent e)
			{
                          ProtSave("ENDE");
				ENDE();
			}
			public void windowDeactivated(WindowEvent e)
			{
			}
			public void windowDeiconified(WindowEvent e)
			{
			}
			public void windowIconified(WindowEvent e)
			{
			}
			public void windowOpened(WindowEvent e)
			{
			}
		});

		EnableButtons();
              Fom.setVisible(true);
            }
        }

	private void ENDE()
	{
		for(int i=0;VecThreads!=null&&i<VecThreads.size();i++)
		{
			if(VecThreads.elementAt(i)!=null)
			{
				//((Terminal)VecThreads.elementAt(i)).bThread=false;
				VecThreads.setElementAt(null,i);
			}
		}

		CloseAll();
		if(g!=null && g.getLog()>0 && !BtnStart.isEnabled())
			g.Logout(false);
		try
		{
			Terminal.fileRun.close();
		}
		catch(IOException ioe)
		{
			System.err.println("Save.prot(): IOException - "+ioe);
		}

		if (!new File(sRun).delete())
			System.out.println(sRun+" konnte nicht gelöscht werden!");
			//ProtSave("not deleted",0);
                if (Static.bX11)
                  Fom.dispose();
		System.exit(0);
	}
	@SuppressWarnings("unchecked")
	private void LoadStamps(JCOutlinerFolderNode NodeRoot)
	{
		Vector VecV = (Vector)NodeRoot.getLabel();
		Vector<Object> VecI = (Vector)NodeRoot.getUserData();
                getTerminals();
		if(VecV.elementAt(0) instanceof Date)
		{
                  //g.fixInfo("LoadStamps VecIP="+(VecIP==null ? "null":""+VecIP.size())+", Datum="+(Date)VecV.elementAt(0));
                  SQL Qry = new SQL(g);
                  if(Qry.open("SELECT Pro_AIC_Protokoll,AIC_Zwischenspeicher,Gueltig,TERMINAL,Zwischentext FROM Zwischenspeicher"+
                              " WHERE kennung ='Import' and "+(VecIP!=null && VecIP.size()>0 ? "Terminal"+Static.SQL_in(VecIP)+" and ":"")+
                              (g.ASA() || g.MySQL()?"date(Gueltig)":g.Oracle()?"trunc(Gueltig)":g.MS()?"CAST(CONVERT(char(8),gueltig, 112) AS datetime)":"Datum")+"="+g.SQL_Format((Date)VecV.elementAt(0))+" order by gueltig desc"))
                  {
                    for(;!Qry.eof();Qry.moveNext())
                    {
                      String sSatz =Qry.getS("Zwischentext");
                      WriteStampInOutliner(NodeRoot,sSatz,Qry.getS("TERMINAL"),Qry.getInt("AIC_Zwischenspeicher"),Qry.getI("Pro_AIC_Protokoll")==0);
                    }
                    Qry.close();
                  }
                  Qry.free();
		}
		else
		{
			try
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(Terminal.sPath+((String)VecV.elementAt(0)))));
				for(String sSatz=in.readLine();sSatz!=null;sSatz=in.readLine())
					if(!sSatz.startsWith("78"))
					{
						if(sSatz.indexOf(":")==-1)
							sSatz+=in.readLine().substring(2);
						WriteStampInOutliner(NodeRoot,sSatz.substring(0,sSatz.indexOf(":"))+"\r",sSatz.substring(sSatz.indexOf(":")+1,sSatz.lastIndexOf(":")),null,false);
					}
			}
			catch(FileNotFoundException e){}
			catch(IOException e){}
		}

		VecI.setElementAt(new Boolean(true),0);
	}
	private void WriteStampInOutliner(JCOutlinerFolderNode NodeRoot,String sSatz,String IP_Port,Integer iAIC,boolean bFrei)
	{
		if(sSatz.indexOf("78\r")==0)
			sSatz=sSatz.substring(3);
		sSatz=sSatz.substring(0,sSatz.indexOf('\r'))+"\r";

		String sSubLeser = sSatz.substring(6,8);
		String sArt = sSatz.substring(8, 10);
                boolean bSymbol=RadSymbol.isSelected() || IP_Port.equals("Symbol");
                int iL=bSymbol ? 16:RadMifare.isSelected() || RadMifare2.isSelected() || RadHitagHex.isSelected() ? 10:RadMifareD.isSelected() ? 17 :
                	RadNova.isSelected() ? 14 : RadRo.isSelected() ? 12:RadSonst.isSelected() ? iKL:8;
		String sKartenNr = sSatz.substring(10, 10+iL);
		if(!bSymbol && (RadHitag.isSelected() || RadLegic.isSelected() || RadMifare2.isSelected()))
                  sKartenNr=sKartenNr.substring(2);
                else if (sArt.equals("PN"))
                  g.testInfo("Personalnummer="+sKartenNr);
                else if(RadHitagHex.isSelected())
                  sKartenNr=Static.FillNull(""+Long.decode("#"+sKartenNr),12);
                else if(RadMiro.isSelected())
                  sKartenNr=Static.MiroCalc(sKartenNr,false);
		String sFehler = sSatz.substring(24+iL, 25+iL);
		String sDatum = sSatz.substring(10+iL, 18+iL);
		String sZeit = sSatz.substring(18+iL, 24+iL);
		@SuppressWarnings("deprecation")
		java.sql.Timestamp TS=new java.sql.Timestamp(new DateWOD(
		new Integer(sDatum.substring(0,4)).intValue(),
		new Integer(sDatum.substring(4,6)).intValue(),
		new Integer(sDatum.substring(6,8)).intValue(),
		new Integer(sZeit.substring(0,2)).intValue(),
		new Integer(sZeit.substring(2,4)).intValue(),
		new Integer(sZeit.substring(4,6)).intValue()).getTimeInMilli());
		String sTaetigkeit = "";
		String sAuftrag = "";
		String sKostenstelle = "";

		int iA=sSatz.indexOf(';')+1;
		int iE=0;
		//g.testInfo("gefunden:"+Para.bGefunden+"/"+Para.int2);
		do
		{
			iE=sSatz.indexOf(';',iA);
			if(iE==-1)
				iE=sSatz.length()-3;

			String sTAK = sSatz.substring(iA,iE).trim();
			if(sTAK.length()==(Para.bGefunden?Para.int3:3))
				sTaetigkeit=sTAK;
			else if(sTAK.length()==(Para.bGefunden?Para.int4:15))
				sAuftrag=sTAK;
			else if(sTAK.length()==(Para.bGefunden?Para.int2:6))
				sKostenstelle=sTAK;

			iA=iE+1;
		}
		while(iE!=sSatz.length()-3);

		Vector<Object> VecVisible = new Vector<Object>();
		VecVisible.addElement(sKartenNr);
		VecVisible.addElement(sArt);
		VecVisible.addElement(new Zeit(TS,"yyyy-MM-dd HH:mm:ss"));
		VecVisible.addElement(sKostenstelle.equals("")||Para.bGefunden&&(Para.int1&1)==1?sKostenstelle:Static.CutNull(sKostenstelle));
		VecVisible.addElement(sTaetigkeit.equals("")||Para.bGefunden&&(Para.int1&1)==1?sTaetigkeit:Static.CutNull(sTaetigkeit));
		VecVisible.addElement(sAuftrag.equals("")||Para.bGefunden&&(Para.int1&1)==1?sAuftrag:Static.CutNull(sAuftrag));
		VecVisible.addElement(IP_Port);
		VecVisible.addElement(sSubLeser);
		VecVisible.addElement(sFehler);
		JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeRoot);
		Node.setUserData(iAIC);
		if(bFrei || !sFehler.equals("0"))
		{
			JCOutlinerNodeStyle StyNode = new JCOutlinerNodeStyle(Node.getStyle()==null?(new JCOutlinerComponent()).getDefaultNodeStyle():Node.getStyle());
			StyNode.setForeground(bFrei?colFrei:colFehler);
			Node.setStyle(StyNode);
		}
		Out.folderChanged(NodeRoot);
	}
	private void EnableButtons()
	{
		BtnStart.setEnabled(!bStart);
                if (Fom != null)
                {
                  BtnZeitraum.setEnabled(!bStart);
                  BtnStop.setEnabled(bStart);
                  BtnFreischalten.setEnabled(bDB);
                }
	}
	private void Start()
	{
		bStart=true;
                if (lInit>0)
                  lInitNext=Static.get_ms()+lInit;
		EnableButtons();
		if (g.Disconnected())
			g.connect(null);
		g.Login();
                VecThreads=null;
                if (g.getLog()>0)
                {
                  AClient.setNull();
                  //if (Terminal.sAServer != null)
                  //  AClient.sAServerSoll = Terminal.sAServer;
                  AClient.setg(g);
                  Para.setParameter("Start", "", 1, 0, 0, 0, false, false);
                  //Para.getMParameter("Werte",true);
                  StartThread();
                  timer.start();
                }
                else
                {
                  Static.printError("ENDE da nicht einloggbar");
                  ENDE();
                }
	}

        private void Stop()
	{
          timer.stop();
          if (Fom != null)
          {
            BtnStop.setEnabled(false);
            BtnStop.revalidate();
            Fom.repaint();
          }
          Thread.yield();
          CloseAll();
          VecThreads.clear();
          /*for(int i=0;i<VecThreads.size();i++)
          {
              if(VecThreads.elementAt(i)!=null)
              {
                  //((Terminal)VecThreads.elementAt(i)).bThread=false;
                  VecThreads.setElementAt(null,i);
              }
          }*/

          bStart=false;
          EnableButtons();
          Para.setParameter("Start","",0,0,0,0,false,false);
          //Para.getMParameter("Werte",true);
          g.Logout(true);
	}

        @SuppressWarnings("unchecked")
	private void getTerminals()
        {
          if (VecIP==null)
          {
            ShowAbfrage A=new ShowAbfrage(g,sTerminalAbfrage);
            VecIP=new Vector(A.getDaten0().getVecSpalte(A.getSpalten().getS("KENNUNG")));
            g.testInfo("getTerminals: VecIP von "+sTerminalAbfrage+"="+VecIP);
          }
        }
	private void StartThread()
	{
		//VecIP = new Vector();
		//ShowAbfrage A=new ShowAbfrage(g,sTerminalAbfrage);
		//VecIP=A.getDaten(0,0,null).getVecSpalte(A.getSpalten().getS("KENNUNG"));
                getTerminals();
		//VecIP = ((Tabellenspeicher)VecIP.elementAt(5)).getVecSpalte(((Tabellenspeicher)VecIP.elementAt(0)).getS("KENNUNG"));
                g.exec("DELETE FROM Zwischenspeicher WHERE Kennung='Status' AND TERMINAL"+Static.SQL_in(VecIP));
		VecThreads=new Vector<Terminal>();
                //Terminal T=null;
		for(int i=0;i<VecIP.size();i++)
		{
                  String s=(String)VecIP.elementAt(i);
                  if (s.indexOf(":")>0)
                  {
                    //g.progInfo("Start Thread "+s);
                    Terminal T = new Terminal(s, g,bUL);
                    T.start();
                    VecThreads.addElement(T);
                  }
                  if (iSec>0)
                  {
                    Thread.yield();
                    Static.sleep(iSec*1000);
                  }
                  //Static.sleep(5000);
		}
                /*if (g.TestPC())
                {
                  Static.sleep(1000);
                  g.testInfo("Active Treads:" + Terminal.activeCount());
                }*/
	}

        private String checkFile(String sFile)
        {
          if (sFile.indexOf(File.separator)>-1)
            return sFile;
          else
            return Terminal.sPath+sFile;
        }

	private void autoUpdate()
	{
          String sFile=Terminal.sPath+"AIC.info";
          g.testInfo("autoUpdate: File="+sFile);
          if (g.MS())
            g.exec("set IDENTITY_INSERT zwischenspeicher ON");
		if(new File(sFile).exists())
		{
			//g.Login();
			try
			{
				SQL Qry = new SQL(g);
				int iTopAIC = SQL.getInteger(g,g.top("aic_zwischenspeicher from zwischenspeicher where kennung='Import' order by aic_zwischenspeicher desc",1));
				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(sFile)));

				String sFirstFile = null;
				Vector<String> VecFiles = new Vector<String>();
				for(String sSatz=in.readLine();sSatz!=null;sSatz=in.readLine())
				{
					int iAIC=new Integer(sSatz.substring(0,sSatz.indexOf(':'))).intValue();

					if(iAIC<=iTopAIC+1)
						sFirstFile = sSatz.substring(sSatz.indexOf(':')+1);
					else
						VecFiles.addElement(sSatz.substring(sSatz.indexOf(':')+1));
				}
				in.close();

				if(sFirstFile!=null)
				{
					if(VecFiles.size()==0)
						VecFiles.addElement(sFirstFile);
					else
						VecFiles.insertElementAt(sFirstFile,0);
				}

				int iProt=0;
				boolean bWrite=false;
				for(int i=0;i<VecFiles.size();i++)
				{
					in = new BufferedReader(new InputStreamReader(new FileInputStream(checkFile(""+VecFiles.elementAt(i)))));
					for(String sSatz=in.readLine();sSatz!=null;sSatz=in.readLine())
					{
						if(!bWrite)
							bWrite=new Integer(sSatz.substring(sSatz.lastIndexOf(':')+1)).intValue()>iTopAIC;
						if(bWrite)
						{
                                                  String sIP_Port=sSatz.substring(sSatz.indexOf(':')+1,sSatz.lastIndexOf(':'));
                                                  int iL=RadSymbol.isSelected() || sIP_Port.equals("Symbol") ? 16:RadMifare.isSelected() || RadMifare2.isSelected() ? 10:RadMifareD.isSelected() ? 17 :
                                                	  RadNova.isSelected() ? 14 : RadRo.isSelected() ? 12:RadSonst.isSelected() ? iKL:8;
							String sDatum = sSatz.substring(10+iL, 18+iL);
							String sZeit = sSatz.substring(18+iL, 24+iL);
							java.sql.Timestamp TS=new java.sql.Timestamp(new DateWOD(
							new Integer(sDatum.substring(0,4)).intValue(),
							new Integer(sDatum.substring(4,6)).intValue(),
							new Integer(sDatum.substring(6,8)).intValue(),
							new Integer(sZeit.substring(0,2)).intValue(),
							new Integer(sZeit.substring(2,4)).intValue(),
							new Integer(sZeit.substring(4,6)).intValue()).getTimeInMilli());

							iProt=iProt==0?g.Protokoll("Terminal",0):iProt; //unknown
							Qry.add("AIC_Zwischenspeicher",new Integer(sSatz.substring(sSatz.lastIndexOf(':')+1)).intValue());
							Qry.add("AIC_Protokoll",iProt);
                                                        Qry.add("Kennung","Import");
							Qry.add("Terminal",sIP_Port);
							Qry.add("Gueltig",TS);
							Qry.add("Zwischentext",sSatz.substring(0,sSatz.indexOf(':'))+"\r");
                                                        Qry.add("aic_mandant",g.getMandant());
							Qry.insert("Zwischenspeicher",false);
							g.fixInfo("autoUpdate: "+sSatz);
						}
					}
					in.close();
				}
			}
			catch(IOException e)
			{
				Static.printError("AU_Terminal.autoUpdate(): Fehler beim Update!!!");
			}

			//g.Logout(true);
		}
                if (g.MS())
                  g.exec("set IDENTITY_INSERT zwischenspeicher OFF");
	}

        private void ProtSave(String s)
        {
                Save.prot(new java.io.File(Static.DirError+"TERMINAL.log"),Runtime.getRuntime().totalMemory()+"\t"+Runtime.getRuntime().freeMemory()+"\t"+Version.getVer2()+"\t"+s);
        }

    // add your data members here

	private Global g;
        private String sConnect;
        private String sConnect2=null;

	private JCOutliner Out = new JCOutliner(new JCOutlinerFolderNode("Root"));

	private JButton BtnAus_Datei_Laden;
	private JButton BtnAus_DB_Laden;
	private JButton BtnFreischalten;
	//private JButton BtnParametersetzen;
	//private JButton BtnHinzufuegen;
	private JButton BtnStart;
	private JButton BtnStop;
        private JButton BtnZeitraum;
	private JButton BtnEnde;

	private Zahl TxtAnzKST = new Zahl(6);
	private Zahl TxtAnzTaet = new Zahl(3);
	private Zahl TxtAnzAuft = new Zahl(15);
	private JCheckBox CbxBDENull = new JCheckBox("");
	private JCheckBox CbxKarteNull = new JCheckBox("");
	private JCheckBox CbxDatei = new JCheckBox("");
        private JCheckBox CbxMecs = new JCheckBox("");
        private JRadioButton RadHitag = new JRadioButton("");
	private JRadioButton RadMiro = new JRadioButton("");
        private JRadioButton RadLegic = new JRadioButton("");
        private JRadioButton RadRo = new JRadioButton("");
        private JRadioButton RadMifare = new JRadioButton("");
        private JRadioButton RadMifare2 = new JRadioButton("");
        private JRadioButton RadMifareD = new JRadioButton("");
        private JRadioButton RadNova = new JRadioButton("");
        private JRadioButton RadSymbol = new JRadioButton("");
        private JRadioButton RadHitagHex = new JRadioButton("");
        private JRadioButton RadMifare8 = new JRadioButton("");
        private JRadioButton RadSonst = new JRadioButton("");
        private JLabel LblKL= new JLabel();
	private boolean bStart = false;
	private boolean bDB = false;
	private Vector<Terminal> VecThreads=null;

	private Parameter Para;

	private Color colFehler = Color.red;
	private Color colFrei = new Color(0,150,0);
	private boolean bAuto=false;
	private static String sRun;
        private javax.swing.Timer timer;
        private boolean bAusgabe=true;
        private boolean bMecsReady=true;
        private String sMandant=null;
        //private JFrame self = this;
        private String sTerminalAbfrage="TerminalTCPIP";
        private String sNr="";
        //private boolean bInsert2=false;
        private Vector VecIP=null;
        private boolean bUL=false;
        private long lInit=0;
        private long lInitNext=0;
        private int iSec=0;
        private JFrame Fom=null;
        private boolean bTimer=false;
        private String sDbUser=null;
        private String sDbPW=null;
        private int iKL=0;
        //private String sAServer=null;
}

