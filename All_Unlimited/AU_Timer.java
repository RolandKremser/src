package All_Unlimited;
/*
    AU_Timer.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/
// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Anzeige.Ampel;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Allgemein.Eingabe.StammEingabe;
import All_Unlimited.Grunddefinition.Systemeinstellung;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Allgemein.Anzeige.Zeit;

public class AU_Timer implements ActionListener
{
    public static void main( String[] args )
    {
		if (args.length<2)
		{
			System.out.println("java -jar AU_Timer.jar Connect Dateiname [Options]");
			System.out.println("Connect: z.B.: 127.0.0.1:2638");
			System.out.println("Dateiname: z.B.: c:\\All_Unlimited\\AU_Timer\\");
			System.out.println("Options: /Auto ... startet automatisch");
			System.out.println("         /o    ... nur Z/Z-Buchungen");
			System.out.println("         /m    ... keine Z/Z-Buchungen");
			//System.out.println("         /n    ... keine Last-Timer-suche");
			System.out.println("         /off  ... keine Konsolen-Ausgabe");
			System.out.println("         /Use60... maximal 60 MB");
			System.out.println("         /max9000 ... maximal 9000 Befehle pro Modell sonst 200000");
			System.out.println("         /i12  ... Init (Neu-Log) alle 12 Stunden");
                        System.out.println("         /I60  ... Init (Neu-Log) jede Minute");
                        System.out.println("         /x    ... Exit bei Disconnect");
                        System.out.println("         M=xx  ... nur Mandant xx");
                        System.out.println("         /Mup  ... Mandanten-Hierarchie");
                        //System.out.println("         C=xx  ... Ersatzverbindung");
                        System.out.println("         ANR=xx... Mitarbeiter lt. Abfrage xx");
                        System.out.println("         Nrxx  ... xx=Nummer");
                        System.out.println("         /bis=x... x=ersetzt aktuelle Zeit im FORMAT yyyy-MM-dd");
                        System.out.println("         /RC   ... reconnect (wiederverbinden nach Trennung)");
                        System.out.println("         U=xx  ... xx=Datenbankuser");
                        System.out.println("         P=yy  ... yy=Datenbankpasswort (statt sql)");
                        System.out.println("         /ZMxx ... Zone minus Minuten");
                        System.out.println("         Land=GB  ...Land England");
                        System.out.println("         /Err  ... Fehler in Tabelle protokollieren");
                        System.out.println("         /save... Konsole speichern");
                        System.out.println("         noMac... Mac-Adresse nicht verwenden");
                        System.out.println("         noX11... kein Formular");
                        System.out.println("         noCC ... kein CheckConnects");
                        System.out.println("         s5 ... 5 Sekunden Abstand");
                        System.out.println("         f5 ... Faktor 5 Abstand");
                        System.out.println("         to60 ... Timeout von 1 Minute");
                        System.out.println("         ww100... 100 ms zwischen Stammupload-Zeilen");
                        System.out.println("         /ohneProt ... Terminal-Zutritte löschen");
                        System.out.println("         /CC .. Wechselwährung einschalten");

                        Static.sleep(5000);
			System.exit(5);
		}
		sRun=args[1]+"Timer.run";
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
		new AU_Timer(args);
    }
	private AU_Timer( String[] args )
	{
		//fileRun=new File(args[1]+"Timer.run");
		/*if (fileRun.exists())
			System.exit(2);
		else
			Save.prot(fileRun,"");*/
		sConnect= args[0];
		Static.DirError = args[1];
		//Static.bMemo=false;
		ParameterCheck(args);
		if (connectDatabase())
			showTimer();
		else
			ENDE("Connect-Fehler",false);
	}
	private void ParameterCheck( String[] args )
	{
          sArgs="";
          for(int i=0;i<args.length;i++)
            sArgs+=" "+args[i];
          Transact.fixInfoS("sArgs="+sArgs);
          //Static.bStringX=false;
          Static.bProtFehler=false;
		if (args.length>2)
			for(int i=2;i<args.length;i++)
				if(args[i].equals("/Auto"))
					bAuto= true;
				else if(args[i].startsWith("/Use"))
					lMax=new Long(args[i].substring(4)).longValue()*1024*1024;
				else if(args[i].startsWith("/MemRC"))
					dRC=new Integer(args[i].substring(6)).intValue()/100.0;
				else if(args[i].startsWith("/MemExit"))
					dExit=new Integer(args[i].substring(8)).intValue()/100.0;
				else if(args[i].startsWith("/MemShow"))
					dShow=new Integer(args[i].substring(8)).intValue()/100.0;
				else if(args[i].startsWith("/max"))
					iMaxBefehle=new Integer(args[i].substring(4)).intValue();
				else if(args[i].startsWith("/i"))
					lInit=new Long(args[i].substring(2)).longValue()*3600000;
                else if(args[i].startsWith("/I"))
					lInit=new Long(args[i].substring(2)).longValue()*1000;
                else if(args[i].startsWith("s"))
					iSek=new Integer(args[i].substring(1)).intValue();
                else if(args[i].startsWith("f"))
					iFak=new Integer(args[i].substring(1)).intValue();
				else if(args[i].equals("/m"))
					iMode=1;
				else if(args[i].equals("/o"))
					iMode=-1;
				//else if(args[i].equals("/n"))
				//	bLastTimer=false;
				else if(args[i].equals("/off"))
					bAusgabe=false;
                else if(args[i].equals("/prog"))
					bProg=true;
                else if(args[i].equals("/x"))
                                {
                                  bExit = true;
                                  Transact.bRealExit=true;
                                }
                                else if(args[i].startsWith("M="))
					sMandant=args[i].substring(2);
                                //else if(args[i].startsWith("C="))
				//	sConnect[1]=args[i].substring(2);
                                else if(args[i].startsWith("ANR="))
					sANR=args[i].substring(4);
                                else if(args[i].startsWith("/bis="))
                                  sBis=args[i].substring(5);
                                else if(args[i].startsWith("Nr"))
                                  sNr=args[i].substring(2);
                                else if(args[i].equals("/Mup"))
					bMup=true;
                                else if(args[i].startsWith("/to")) // Timeout für Befehle zwischendurch
                                  Transact.iTimeOut=new Integer(args[i].substring(3)).intValue();
                                else if(args[i].startsWith("/tc")) // Connection-Timeout in s
                                  Transact.iTimeOutC=new Integer(args[i].substring(3)).intValue();
                                else if(args[i].startsWith("/ts")) // Socket-Timeout in min beim Verbinden
                                  Transact.iTimeOutS=new Integer(args[i].substring(3)).intValue();
                                else if(args[i].startsWith("/AA")) // automatisches Ausloggen [h]
                                  Transact.iLogOut=new Integer(args[i].substring(3)).intValue();
                                else if(args[i].startsWith("/QryAb"))
					iQryAb=new Integer(args[i].substring(6)).intValue();
                                else if(args[i].startsWith("/AbfAb"))
					lAbfAb=new Long(args[i].substring(6)).longValue();
                                else if(args[i].startsWith("to"))
					Transact.iTimeOut=new Integer(args[i].substring(2)).intValue();
                                else if(args[i].startsWith("ww"))
                                  IntusCom.iWait=new Integer(args[i].substring(2)).intValue();
                                else if(args[i].equals("/RC"))
					Transact.bRetry=true;
                                else if(args[i].startsWith("U="))
					sDbUser=args[i].substring(2);
                                else if(args[i].startsWith("P="))
					sDbPW=args[i].substring(2);
                                else if(args[i].startsWith("/ZM"))
					Transact.iZoneOffset=new Integer(args[i].substring(3)).intValue();
                                else if(args[i].equals("/TRB"))
                                {
                                  bTRB=true;
                                  Transact.fixInfoS("Test von Refresh-Berechtigung aktiviert");
                                }
                                else if(args[i].startsWith("Land="))
                                  Static.sLand=args[i].substring(5);
                                else if(args[i].equals("/Err"))
                                  Static.bProtFehler=true;
                                else if(args[i].equals("/save"))
                                  Static.bSave = true;
                                else if(args[i].equals("/ohneProt"))
                                  Static.bOhneProt = true;
                                else if(args[i].equals("noMac"))
                                  Static.cMacArt = '-';
                                else if(args[i].startsWith("/MA") && args[i].length()>3)
                                    Static.cMacArt = args[i].charAt(3);
                                else if(args[i].equals("/noV"))
                                  Static.bVerlauf = false;
                                else if(args[i].equals("noX11"))
                                  Static.bX11 = false;
                                else if(args[i].equals("noCC"))
                                  Transact.bCheckConnect = false;
                                else if(args[i].equals("/CC"))
                                  bCC = true;
                                else if(args[i].equals("/noTest"))
                                  bTestinfo=false;
                                else if(args[i].equals("/beep"))
                                	Static.bBeep=true;
	}
	private boolean connectDatabase()
	{
        if (sDbUser != null)
          Transact.fixInfoS("Db-User="+sDbUser);
		g = new Global(sConnect,"Timer"+(iMode==0?"":iMode==1?"+":"-")+sNr,sDbUser,sDbPW,1);
                g.fixInfo("All Unlimited-Version="+Version.getVersion());
                //if (g.MySQL())
                //  Static.bStringX=true;
                //if (g.MS()) bCC=false;
                if (bCC)
                	g.fixInfo("Wechselwährung "+(bCC ? "eingeschaltet":"ausgeschaltet"));
                if (sANR != null)
                  g.fixInfo("Eingeschränkt durch Abfrage "+sANR);
                if (sBis != null)
                  g.fixInfo("Berechnung nur bis "+sBis);
                g.bCC=bCC;
                if (!bTestinfo)
                  Transact.iInfos |= Transact.NO_INFO;
                //Static.bView2  = bView2 && !g.ASA();
                //Static.bInsert2= bInsert2 && g.MS();
                int iDO=SQL.getInteger(g,"select "+g.int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Option'");
                //Static.bView2 = (iDO&1)>0;
                Static.bInsert2 = (g.MS() || g.MySQL()) && (iDO&2)>0;
                //g.bCC=!g.MS() && bCC;
                //if (g.ASA() || g.MySQL()) Static.bView2=false;

                if (g.Oracle() || g.MS())
                  bExit=true;
		g.bExit = false;
		Transact.bAusgabe=bAusgabe;
                Ampel.check(g);
                iEigFehler=SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='Fehler aufgetreten'");
                if (bProg)
                  g.fixInfo("lMax="+lMax+", iEigFehler="+iEigFehler);
                iPU=g.getPU();//SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
                if (sMandant != null)
                  g.setMandantT(sMandant);
                //TabBFilter=new Tabellenspeicher(g,new String[] {"Abfrage","Daten"});
		if (new Version(g).OK())
			g.checkSpracheLand();
		else
			return false;
		String sDirError= Static.DirError;
	    Systemeinstellung.LoadDir(g,null,-1);
	    Static.DirError = sDirError;
	    g.fixInfo("Web-Server="+Static.sWeb);
                //Transact.fixInfo("All Unlimited-Version="+Version.getVersion());
		//Static.printError("Nur Timer-Test");
		/*for(g.TabEigenschaften.moveFirst();!g.TabEigenschaften.eof();g.TabEigenschaften.moveNext())
		{
			String sKennung=g.TabEigenschaften.getS("Kennung");
			//g.debugInfo(sKennung+":"+g.TabEigenschaften.getI("Aic"));
			if (sKennung.equalsIgnoreCase("AufrufModell"))
				iEigModell=g.TabEigenschaften.getI("Aic");
			if (sKennung.equalsIgnoreCase("fortsetzen"))
				iEigFort=g.TabEigenschaften.getI("Aic");
			if (sKennung.equalsIgnoreCase("Abgearbeitet"))
				iEigFertig=g.TabEigenschaften.getI("Aic");
			if (sKennung.equalsIgnoreCase("Modellzeitraum"))
				iEigZR=g.TabEigenschaften.getI("Aic");
			if (sKennung.equalsIgnoreCase("TimerStamm"))
				iEigStamm=g.TabEigenschaften.getI("Aic");
		}*/

             if (g.iTimerModell == 0 || g.iTimerZR == 0 || g.iTimerStamm == 0 || g.iTimerSperre == 0)
             {
               JOptionPane.showMessageDialog(new JFrame(), "eine Eigenschaft fehlt", "Fehler", JOptionPane.ERROR_MESSAGE);
               System.exit( -2);
             }
             g.delSperren();
             return true;
	}

        private void createSQL()
        {
          if (bProg)
            g.fixInfo("iTimerNicht="+g.iTimerNicht+", iTimerAlt="+g.iTimerAlt);
          java.util.Vector VecBew=SQL.getVector("select aic_bewegungstyp from bewegungstyp where "+g.bit("bewBits",Global.cstTimer),g);
                //g.printInfo(iEigModell+"/"+iEigFertig+"/"+iEigZR+"/"+iEigStamm);
                g.testInfo(g.in("stamm",VecANR));
                String sBedMandant=g.getMandantT()>0?bMup?g.getReadMandanten(false,"p"):" and aic_mandant="+g.getMandantT():"";
                sAbfrage ="select x.*,(select bezeichnung from stammview2 s where stamm=s.aic_stamm and s.aic_rolle is null) bezeichnung"+
                        ",(select aic_mandant from stammview2 s where stamm=s.aic_stamm and s.aic_rolle is null) stamm_mandant from (select aic_bew_Pool aic,gueltig"+
                        ",(select aic_begriff from bew_begriff p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft="+g.iTimerModell+") aic_begriff"+
                        ",p.anr stamm,p.aic_mandant"+//",(select aic_stamm from bew_stamm p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft="+g.iTimerStamm+") stamm"+
                        ",(select von from bew_von_bis p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft="+g.iTimerZR+") von"+
                        ",(select bis from bew_von_bis p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft="+g.iTimerZR+") bis"+
                        ",(select von from bew_von_bis p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft="+g.iTimerFort+") fort"+
                        //",(select von from bew_von_bis p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft="+g.iTimerFertig+") fertig"+
                        ",(select aic_bew_pool from bew_boolean p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + g.iTimerSperre+") Sperre"+
                        (iEigFehler>0 ?",(select aic_bew_pool from bew_boolean p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + iEigFehler+") Fehler":"")+
                        (g.iTimerNicht>0 ?",(select aic_bew_pool from bew_boolean p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + g.iTimerNicht+") nicht":"")+
                        ",null Node,null Jetzt,LDATE3 from bew_pool p where aic_bewegungstyp"+Static.SQL_in(VecBew)+sBedMandant+" and LDATE2=0"+
                        (VecANR == null ? "":" and "+g.in("anr",VecANR))+" and pro_aic_protokoll is null) x where nicht is null"+
                        (iMode==0?"":iMode==1?" and fort is not null":" and fort is null")+" order by gueltig,stamm_mandant";
        }
	private void showTimer()
	{
		long lClock=Static.get_ms();
                BtnStart = g.getButton("Start");
                BtnStop = g.getButton("Stop");
                //BtnClean = g.getButton("Clean");
                BtnFehler= new JButton("0 Fehler");
                BtnBeenden = g.getButton("Beenden");
                //BtnTest = g.getButton("Test");
                //CbxRechnen = g.getCheckbox("Rechnen");
                CbxExec = g.getCheckbox("Exec");
                CbxDebug = g.getCheckbox("Debug");

		JPanel PnlN = new JPanel(new FlowLayout());
		PnlN.add(LblDatum);
		PnlN.add(new JLabel("   Prüfabstand:"));
		PnlN.add(EdtSekunden);
		PnlN.add(new JLabel("s, Leerfaktor:"));
		PnlN.add(EdtFaktor);
		//PnlN.add(CbxRechnen);
		//DtFort = new All_Unlimited.Allgemein.Eingabe.Datum(g,"mm:ss");
		//DtFort.setDate(new java.sql.Time(60000));
		//PnlN.add(CbxTest);
		//PnlN.add(DtFort);
                if (g.TestPC() || bProg)
                {
                  PnlN.add(CbxExec);
                  if (bProg)
                    CbxExec.setSelected(true);
                  PnlN.add(CbxDebug);
                }

		JPanel PnlC = new JPanel(new GridLayout());
		PnlC.add(GidMom);
		PnlC.add(GidLater);

		//EdtSekunden.setMinimum(1);
		//EdtFaktor.setMinimum(2);

		String[] sLabels = new String[] {g.getBegriffS("Show","Aktivierung"),g.getBegriffS("Show","Sperre"),g.getBegriffS("Show","Modell"),g.getBegriffS("Show","Stamm"),g.getBegriffS("Show","Mandant")};
		GidMom.setColumnButtons(sLabels);
		GidMom.setNumColumns(bMup ? 5:4);
		GidLater.setColumnButtons(sLabels);
		GidLater.setNumColumns(bMup ? 5:4);

		JPanel PnlS = new JPanel(new GridLayout());
		PnlS.add(BtnStart);
		PnlS.add(BtnStop);
                //PnlS.add(BtnClean);
                PnlS.add(BtnFehler);
		PnlS.add(BtnBeenden);
                if (Static.bX11)
                {
                  Fom=new JFrame("Timer" + (iMode == 0 ? " " : iMode == 1 ? "+ " : "- ") +
                           (sANR != null ? sANR : sMandant == null ? sConnect : sMandant) +
                           " - " + Version.getVersion() + " (" + Version.getDate() + ")");
                  Fom.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                  Fom.getContentPane().add("North", PnlN);
                  Fom.getContentPane().add("Center", PnlC);
                  Fom.getContentPane().add("South", PnlS);
                  Fom.setSize(800, 500);
                }
		Parameter Para =new Parameter(g,"Timer");
		Para.getParameter(iMode>0?"Option2":"Option",false,true);
		if(Para.bGefunden)
		{
			EdtSekunden.setIntValue(Para.int1);
			EdtFaktor.setIntValue(Para.int2);
			if (EdtSekunden.getIntValue()<1)
				EdtSekunden.setIntValue(1);
			if (EdtFaktor.getIntValue()<1)
				EdtFaktor.setIntValue(1);
			//CbxRechnen.setSelected(Para.int3==1);
			bBerechnen = bProg ? false:Para.int4==1;
		}
		else
		{
			EdtSekunden.setIntValue(10);
			EdtFaktor.setIntValue(12);
			//CbxRechnen.setSelected(true);
			bBerechnen=false;
		}
                if (iSek>0)
                  EdtSekunden.setIntValue(iSek);
                if (iFak>0)
                  EdtFaktor.setIntValue(iFak);
		if (bAuto)
			bBerechnen=true;
		Para.free();

		EnableButtons();
                if (Static.bX11)
                {
                  Fom.setVisible(true);
                  Events();
                }

                checkExit2();
		ProtSave(0,null,"TIMER",Static.get_ms()-lClock);

		if (bBerechnen)
		{
			Login();
                        if (lInit>0)
				lInitNext=Static.get_ms()+lInit;
			timer.setDelay(EdtSekunden.getIntValue()*1000);
			timer.start();
		}
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
                      //Runtime rt=Runtime.getRuntime();
                      double dUsed=Static.Used();
                      if (dUsed>0.9)//(rt.maxMemory()*9/10 < rt.totalMemory())
                        ENDE("Autoexit",false);
                      else if (dUsed>0.6 && g!=null)
                    	g.fixInfo(Static.Mem(true));
                      //else if (rt.maxMemory()/2 < rt.totalMemory())
                      //  System.out.println((rt.totalMemory()/1024/1024)+" MB von "+(rt.maxMemory()/1024/1024)+" MB");
                    }
                  };
                  java.util.Timer t = new java.util.Timer();
                  t.schedule(task, 5000, 5000);
                }
		//else
		//	g.unConnect();
	}

        @SuppressWarnings("unchecked")
        private void Login()
        {
          Transact.lQryAb=CbxExec.isSelected() && iQryAb>=0 ? iQryAb:-1;
          Static.lAbfAb=CbxExec.isSelected() ? lAbfAb:0;
          if (g.getLog()==0)
            g.Login(); // (bLastTimer);
          AClient.setg(g);
          if (sANR != null)
          {
            ShowAbfrage A=new ShowAbfrage(g,sANR);
            if (A.Fehler())
            {
              Static.sleep(10000);
              System.exit(-3);
            }
            else
            {
              VecANR = new Vector(A.getDaten0().getVecSpalte("AIC_STAMM"));
              g.testInfo("VecANR=" + VecANR);
            }
          }
          //ShowAbfrage.refreshBerechtigung(g);
          createSQL();
        }

	private void EnableButtons()
	{
		BtnStart.setEnabled(!bBerechnen);
		BtnStop.setEnabled(bBerechnen);
                //BtnClean.setEnabled(!bBerechnen);
		BtnBeenden.setEnabled(!bBerechnen);
		EdtSekunden.setEnabled(!bBerechnen);
		EdtFaktor.setEnabled(!bBerechnen);
		//CbxRechnen.setEnabled(!bBerechnen);
	}

        private void Start(boolean bAktiv)
        {
          long lClock=Static.get_ms();
          g.unConnect();
          g=null;
          g = new Global(sConnect,"Timer"+(iMode==0?"":iMode==1?"+":"-")+sNr,sDbUser,sDbPW,1);
          g.bCC=bCC;
          if (new Version(g).OK())
            g.checkSpracheLand();
          else
            System.exit(-1);
          if (sMandant != null)
            g.setMandantT(sMandant);
          //g.connect(sConnect);
          Login();
          if (!bTestinfo)
            Transact.iInfos |= Transact.NO_INFO;
          ShowAbfrage.refreshBerechtigung(g);
          bBerechnen=true;
          saveParameter();
          if (EdtSekunden.getIntValue()<1)
                  EdtSekunden.setIntValue(1);
          if (EdtFaktor.getIntValue()<1)
                  EdtFaktor.setIntValue(1);
          timer.setDelay(EdtSekunden.getIntValue()*1000);
          timer.start();
          EnableButtons();
          if (lInit>0)
                  lInitNext=lClock+lInit;
          ProtSave(0,null,bAktiv ? "Start":"Zwischenstart",Static.get_ms()-lClock);
        }

        private void Stop(boolean bAktiv)
        {
          long lClock=Static.get_ms();
          bBerechnen=false;
          timer.stop();
          if (TabRechnen != null)
            TabRechnen.moveLast();
          ((JCOutlinerFolderNode)GidMom.getRootNode()).removeChildren();
          ((JCOutlinerFolderNode)GidLater.getRootNode()).removeChildren();
          //g.unConnect();
          iModellOld=0;
          calc=null;
          g.Logout(true);
          EnableButtons();
          VecFehler.removeAllElements();
          BtnFehler.setText("0 Fehler");
          ProtSave(0,null,bAktiv ? "Stop":"Zwischenstop",Static.get_ms()-lClock);
        }

	private void Events()
	{
		BtnStart.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
                                  Start(true);
				}
			});

		BtnStop.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					Stop(true);
				}
			});

                /*BtnClean.addActionListener(new ActionListener()
                        {
                                public void actionPerformed(ActionEvent ev)
                                {
                                        Clean(false);
                                }
                        });*/

		BtnBeenden.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					ENDE("Beenden",true);
				}
			});

                /*BtnTest.addActionListener(new ActionListener()
                        {
                                public void actionPerformed(ActionEvent ev)
                                {
                                        Test();
                                }
                        });*/

		Fom.addWindowListener(new WindowListener()
		{
			public void windowClosed(WindowEvent e)
			{
			}
			public void windowOpened(WindowEvent e)
			{
			}
			public void windowClosing(WindowEvent e)
			{
				ENDE("ENDE",false);
			}
			public void windowIconified(WindowEvent e)
			{
			}
			public void windowDeiconified(WindowEvent e)
			{
			}
			public void windowActivated(WindowEvent e)
			{
			}
			public void windowDeactivated(WindowEvent e)
			{
			}
		});
	}
        /*private void Test()
        {
          long lClock=Static.get_ms();
          RunClose();
          dispose();
          if (g.getLog()>0)
            g.Logout(false);
          String sDir=System.getProperty("user.dir");
          g.fixInfo("sDir="+sDir);
          g.execFile("java.exe"," -jar "+sDir+File.separator+"AU_Timer.jar"+sArgs);
          //g.execFile("d:\\DVH\\AU\\Timer\\Timer_m.bat","");
          ProtSave("Test",Static.get_ms()-lClock);
          //System.exit(0);
        }*/

  /*private void Clean(boolean bSilent)
  {
    int i=SQL.getInteger(g,"select count(*) from bew_boolean where aic_eigenschaft="+g.iTimerSperre);
    if (i>0)
    {

    	long lClock=Static.get_ms();
    	g.diskInfo(g.delSperren()+" Timer-Sperren gelöscht");
    	ProtSave("Clean",Static.get_ms()-lClock);
    }
    else if (!bSilent && Static.bX11)
      new Message(Message.WARNING_MESSAGE,Fom,g,10).showDialog("keine_Sperre");
  }*/

	private void ENDE(String s,boolean bSave)
	{
		long lClock=Static.get_ms();
                bDeAktiv = true;
                timer.stop();
		//g.connect(sConnect);
		if (bSave)
			saveParameter();
		if (g.getLog()>0)
			g.Logout(false);

		RunClose();
                if (Static.bX11)
                  Fom.dispose();
		ProtSave(0,null,s,Static.get_ms()-lClock);
		System.exit(0);
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
              ProtSave(0,null,"not deleted",0);
        }

	public void actionPerformed(ActionEvent e)
	{
		LblDatum.setText(sBis==null ? Save.now():sBis);
                checkExit2();
                if (bDeAktiv)
                {
                  int iStatus= iPU>0 ? SQL.getInteger(g,"select "+g.int1()+" from parameter where aic_parameter="+iPU,5):-2;
                  if (iStatus<1)
                  {
                    bDeAktiv = false;
                    timer.stop();
                    Start(false);
                  }
                  else
                    return;
                }
                if (bTRB)
                  RB();
		if(!bWerteHolen && !bBerechnung)
		{
			if (Static.Used()>dShow)
			{
				g.fixInfo("Used: "+new Zahl1(Static.Used(),"#0.0 %")+Static.Mem(true));
				System.gc();
			}
			if (lMax>0 && Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory() > lMax || Static.Used()>dExit)
				ENDE("Autoexit",false);
			if(bWerteVorhanden)
				if(TabRechnen.eof())
				{
					g.progInfo("> keine weiteren Zeilen");
					g.printInfo(Save.now()+":Fertig");
					iHolNummer=0;
					bWerteVorhanden=false;
				}
				else
				{
					bBerechnung=true;
					while(!TabRechnen.eof() && !TabRechnen.getB("Jetzt"))
						TabRechnen.moveNext();
					if(!TabRechnen.eof())
					{
                                          if (TabRechnen.bof()) RB();
						g.progInfo("> Berechne Zeile "+TabRechnen.getPos());
						GidMom.setFocusNode((JCOutlinerNode)TabRechnen.getInhalt("Node"),null);
						if (Rechnen())
						{
							do
								TabRechnen.moveNext();
							while(!TabRechnen.eof() && !TabRechnen.getB("Jetzt"));
						}
						else
						{
							bWerteVorhanden=false;
							iHolNummer=0;
						}
					}
					bBerechnung=false;
				}
			else
			{
				g.progInfo(iHolNummer==0?"Hole Timersätze":"> Warte "+iHolNummer);
				if (iHolNummer==0)
				{
					long lClock=Static.get_ms();
					checkConnection();
					if(!g.isConnected())
					{
						ProtSave(0,null,"Autoexit2",Static.get_ms()-lClock);
						System.exit(1);
					}
					if(lInitNext>0 && lClock>lInitNext || Static.Used()>dRC)
					{
						Stop(false);
						System.gc();
						Static.sleep(EdtSekunden.getIntValue()*1000);
						g.connect(null);
                        Start(false);
                        g.delSperren();
						ProtSave(0,null,"Wait",Static.get_ms()-lClock);
					}
					lClock=Static.get_ms();
					bWerteHolen=true;
					g.printInfo(Save.now()+":WerteHolen");
                                        g.setMandant(0);
					TabRechnen=new Tabellenspeicher(g,sAbfrage,true);
					iCalcAnzahl=TabRechnen.size();
					//Tabellenspeicher Tab2=new Tabellenspeicher(g,sAbfrage+">now() order by gueltig",true);
					fillGrid();
					//fillGrid((JCOutlinerFolderNode)GidLater.getRootNode(),Tab2);
					GidMom.folderChanged(GidMom.getRootNode());
					GidLater.folderChanged(GidLater.getRootNode());
					if (bWerteVorhanden)
						TabRechnen.moveFirst();
					else
					{
						//if (lInit>0)
						//	lInitNext=lClock+lInit;	// !!! später einblenden

						//Logcheck();
						//g.unConnect();
					}
					ProtSave(0,null,"Holen",Static.get_ms()-lClock);
					iCalcAnzahl=0;
                    Logcheck();
				}
				iHolNummer++;
				if (iHolNummer>=EdtFaktor.getIntValue())
					iHolNummer=0;
				bWerteHolen=false;
			}

		}
	}

  private void Logcheck()
  {
    long lClock=Static.get_ms();
    if (g.Logcheck()==0)
    {
      RunClose();
      if (Static.bX11)
        Fom.dispose();
		  ProtSave(0,null,"exit bei Logcheck",Static.get_ms()-lClock);
		  System.exit(8);
    }
  }

	private void saveParameter()
	{
		boolean bNC  = g.Disconnected();
		if (bNC)
			g.connect(null);
		Parameter Para=new Parameter(g,"Timer");
		Para.setParameter(iMode>0?"Option2":"Option","",EdtSekunden.getIntValue(),EdtFaktor.getIntValue(),/*CbxRechnen.isSelected()?*/1,bBerechnen?1:0,false,true);
		Para.free();
		if (bNC)
			g.unConnect();
	}
        private void checkDebug(String sModell,int iStamm)
        {
          if (FrmAufruf==null)
          {
            DatVon = new Datum(g,"yyyy-MM-dd HH:mm:ss");
            DatBis = new Datum(g,"yyyy-MM-dd HH:mm:ss");
            DatVon.setEditable(false);
            DatBis.setEditable(false);
            FrmAufruf = new JDialog(Fom, true);
            FrmAufruf.getContentPane().setLayout(new BorderLayout(2, 2));
            SteStamm = new StammEingabe(FrmAufruf,g);
            SteStamm.setEditable(false);
            SteStamm.setStt(g.iSttANR);
            BtnAufrufOk = g.getButton("Aufruf");
            BtnAufrufAbbruch = g.getButton("Abbruch");

            JPanel Pnl = new JPanel(new GridLayout(0, 1, 2, 2));
            Pnl.add(new JLabel(g.getBegriffS("Show", "Datum von") + ":"));
            Pnl.add(new JLabel(g.getBegriffS("Show", "Datum bis") + ":"));
            Pnl.add(new JLabel(g.getBegriffS("Show", "Stamm") + ":"));
            Pnl.add(new JLabel(g.getBegriffS("Show", "Abbruch bei") + ":"));
            Pnl.add(new JLabel(g.getBegriffS("Show", "Debug ab") + ":"));
            Pnl.add(new JLabel(g.getBegriffS("Show", "Pause bei") + ":"));
            FrmAufruf.getContentPane().add("West", Pnl);

            Pnl = new JPanel(new GridLayout(0, 1, 2, 2));
            Pnl.add(DatVon);
            Pnl.add(DatBis);
            Pnl.add(SteStamm);
            Pnl.add(SpnAbbruch);
            Pnl.add(SpnDebug);
            Pnl.add(SpnPause);
            FrmAufruf.getContentPane().add("Center",Pnl);

            Pnl = new JPanel(new GridLayout(1,0,2,2));
            Pnl.add(BtnAufrufOk);
            Pnl.add(BtnAufrufAbbruch);
            FrmAufruf.getContentPane().add("South",Pnl);

            FrmAufruf.pack();

            BtnAufrufOk.addActionListener(new ActionListener()
            {

                    public void actionPerformed(ActionEvent ev)
                    {
                      Parameter Para = new Parameter(g,"Modell");
                      Para.setParameter("Option","",SpnAbbruch.getIntValue(),SpnDebug.getIntValue(),0,SpnPause.getIntValue(),true,false);
                      Para.free();
                      FrmAufruf.setVisible(false);
                    }
            });

            BtnAufrufAbbruch.addActionListener(new ActionListener()
            {

                    public void actionPerformed(ActionEvent ev)
                    {
                      bNext=true;
                      FrmAufruf.setVisible(false);
                    }
            });

          }
          bNext=false;
          DatVon.setDate(g.getVon());
          DatBis.setDate(g.getBis());
          SteStamm.setStamm(iStamm);
          Parameter Para = new Parameter(g,"Modell");
          Para.getParameter("Option",true,false);
          SpnAbbruch.setIntValue(Para.int1);
          SpnDebug.setIntValue(Para.int2);
          SpnPause.setIntValue(Para.int4);
          Para.free();

          Static.centerComponent(FrmAufruf,Fom);
          FrmAufruf.setTitle(sModell);
          FrmAufruf.setVisible(true);
        }

      private boolean Rechnen()
      {
        checkExit();
        long lClock=Static.get_ms();
        int iAic=TabRechnen.getI("Aic");
        g.fixtestInfo("Rechnen:"+iAic);
        if (TabRechnen.getInhalt("fort")==null && SQL.exists(g, "select aic_bew_pool from Bew_von_bis where aic_bew_pool=" + iAic + " and aic_eigenschaft=" + g.iTimerFertig))
        {
        	g.fixInfo("bereits fertig");
        	return true;
        }
        if (TabRechnen.getI("LDATE3")>0)
        {
          if (SQL.exists(g, "select von from bew_von_bis where aic_bew_pool=? and aic_eigenschaft=" + g.iTimerAlt+" and von>"+(sBis==null ? g.now():g.DateTimeToString(DWnow.getTime())),""+iAic))
          {
        	g.fixInfo("alternativer Aufruf später");
            return true;
          }
        }
      if (SQL.exists(g, "select aic_bew_pool from bew_boolean where aic_bew_pool=" + iAic + " and aic_eigenschaft=" + g.iTimerSperre))
        if (g.delSperren()==0)
		{
		  g.fixInfo("Sperre1");
	        	return true;
		}
      g.bISQL=true;
      SQL Qry = new SQL(g);
      Qry.add("aic_bew_pool", iAic);
      Qry.add("aic_eigenschaft", g.iTimerSperre);
      Qry.add("spalte_boolean", 1);
      Qry.add("aic_logging",g.getLog());
      if (Qry.insert("bew_boolean", false) < 0)
      {
        Qry.free();
        g.bISQL=false;
        g.fixInfo("Sperre2");
        return true;
      }
      Qry.free();
      g.bISQL=false;
		int iModell=TabRechnen.getI("Aic_Begriff");
		int iStamm=TabRechnen.getI("Stamm");
		int iMandant=TabRechnen.getI(iStamm==0 ? "aic_mandant":"stamm_mandant");
		g.setMandant(iMandant);
                if (bProg)
                  g.fixInfo("iModell="+iModell+", iStamm="+iStamm+", iMandant="+iMandant);
                if (TabRechnen.getInhalt("fort")==null)
                {
                  g.setVon(TabRechnen.getInhalt("von") == null ? null : TabRechnen.getTimestamp("von"));
                  g.setBis(TabRechnen.getInhalt("bis") == null ? null : TabRechnen.getTimestamp("bis"));
                }
                else
                {
                  g.setAktDate(true);
                }

		g.printInfo("---------------------------------------------------------------------");
		int iPos=g.TabBegriffe.getPos("Aic",iModell);
		String sModell=g.TabBegriffe.getS(iPos,"Bezeichnung");
		String sStamm=TabRechnen.getS("Bezeichnung");
                if (bProg)
                  g.fixInfo("iPos="+iPos+", sModell="+sModell+", sStamm="+sStamm);
		String sFehler=null;
		g.fixInfo(Save.now()+": Berechnung von Modell="+sModell+",Stamm="+sStamm+",Datum="+new Zeit(TabRechnen.getTimestamp("gueltig"), "dd.MM.yyyy HH:mm"));
		//if (CbxRechnen.isSelected())
		{
			try
			{
				if (iModell==0)
					Static.printError("AU_Timer.Rechnen(): Timerberechnung ohne Modell nicht möglich!");
				else if (TabRechnen.getF("gueltig")<0)
					Static.printError("AU_Timer.Rechnen(): Timerberechnung vor 1970 nicht möglich!");
				//else if (g.getVon()==null || g.getBis()==null)
				//	Static.printError("AU_Timer.Rechnen(): Timerberechnung ohne Zeitraum nicht möglich!");
				else
				{
					//g.bSaveExec=CbxExec.isSelected();
                                        //g.lQryAb=CbxExec.isSelected() ? 0:-1;
                                        Transact.lQryAb=CbxExec.isSelected() && iQryAb>=0 ? iQryAb:-1;
                                        Static.lAbfAb=CbxExec.isSelected() ? lAbfAb:0;
					g.setDebug(CbxDebug.isSelected());
					g.printExec(Save.now()+": "+sModell+", Stamm="+sStamm,false);
                                        if (g.Debug())
                                            checkDebug(sModell,iStamm);
                                        if (bNext)
                                          return true;
                                        long lClockV = Static.get_ms();
                                        int iVerlauf=g.SaveVerlauf(iModell,iStamm,0);
					if (iModell != iModellOld)// || Static.Used()>0.5)
					{
//						if (iMode==-1)
//							g.fixInfo("!!! neue Zeit-Zulagenbuchung !!!");
                                          calc = new All_Unlimited.Hauptmaske.Calc(null,g,iModell,true,Static.AicToVec(iStamm),iMaxBefehle,null,0);
                                          iModellOld = iModell;
					}
					else
                                          calc.reCalc(Static.AicToVec(iStamm),true);
                                        g.SaveVerlaufFertig(iVerlauf,Static.get_ms()-lClockV,false);
                                        g.testInfo(Count.print("SQL",Count.SQL));
					iCalcAnzahl = calc.Anzahl();
					sFehler = calc.Fehler();
					//g.bSaveExec=false;
					g.setDebug(false);
				}

				if (!g.isConnected())
				{
					g.disconnect();
					iModellOld=0;
					calc=null;
					checkConnection();
					if (g.isConnected())
						return false;
					else
						System.exit(1);
				}

				if (sFehler == null)
				{
				  if (TabRechnen.getInhalt("fort")==null)
					{
				    		SQL Qry1 = new SQL(g);
						Qry1.add("aic_bew_pool",iAic);
						Qry1.add("aic_eigenschaft",g.iTimerFertig);
						Qry1.addnow("von");
						Qry1.add("dauer",0);
						Qry1.insert("Bew_von_bis",false);
						Qry1.free();
                                                Qry.exec("update bew_pool set LDATE2="+Static.DateToInt(new java.util.Date())+" where aic_bew_pool="+iAic);
					}
					else
					{
						double dSeconds=(/*CbxTest.isSelected()?Sort.getf(DtFort.getTime()):*/TabRechnen.getF("Fort"));
						double dGueltig=TabRechnen.getF("gueltig");
						int i= (int)Math.floor((Sort.getf(new DateWOD())-dGueltig)/dSeconds+1);
						DateWOD DW=new DateWOD((long)Math.rint(dGueltig+i*dSeconds)*1000);
						DW.setTimezoneOffset();
						g.progInfo("Fortschreiben: Gültig="+TabRechnen.getTimestamp("gueltig")+"/"+dSeconds+"-> Faktor="+i+" neu:"+DW);
						g.exec("update bew_pool set gueltig="+g.DateTimeToString(DW.toTimestamp())+",ldate="+Static.DateToInt(DW.toTimestamp())+" where aic_bew_pool="+iAic);
						//Qry.exec("update bew_pool set gueltig=dateadd(Second,"+dSeconds+","+(TabRechnen.getF("gueltig")+dSeconds<Sort.getf(new DateWOD())?"now()":Static.SQL_Format(TabRechnen.getTimestamp("gueltig")))+") where aic_bew_pool="+iAic);
					}
					ProtSave(iStamm,TabRechnen.getTimestamp("gueltig"),(iModell==0?"("+iAic+") kein Modell angegeben!":"("+iAic+")"+sModell+"/"+sStamm+"/"+g.getVon()+"-"+g.getBis()+": ok"),Static.get_ms()-lClock);
                                        if (iEigFehler>0 && TabRechnen.getB("Fehler"))
                                          g.exec("delete from bew_boolean where aic_bew_pool="+iAic+" and aic_eigenschaft="+iEigFehler);
				}
				else
				{
					VecFehler.addElement(new Integer(iAic));
					ProtSave(iStamm,TabRechnen.getTimestamp("gueltig"),"("+iAic+")"+sModell+"/"+sStamm+"/"+g.getVon()+"-"+g.getBis()+":"+sFehler,Static.get_ms()-lClock);
					Static.printError("AU_Timer.Rechnen(): Modell "+sModell+": Fehler - s. oben");
                                        if (iEigFehler>0 && !TabRechnen.getB("Fehler"))
                                        {
                                          Qry = new SQL(g);
                                          Qry.add("aic_bew_pool", iAic);
                                          Qry.add("aic_eigenschaft", iEigFehler);
                                          Qry.add("spalte_boolean", 1);
                                          Qry.add("aic_logging", g.getLog());
                                          Qry.insert("bew_boolean", false);
                                          Qry.free();
                                        }
                                        BtnFehler.setText(VecFehler.size()+" Fehler");
				}
				iCalcAnzahl=0;
			}
			catch(Exception e)
			{
				VecFehler.addElement(new Integer(iAic));
				ProtSave(iStamm,TabRechnen.getTimestamp("gueltig"),"("+iAic+")"+sModell+"/"+sStamm+"/"+g.getVon()+"-"+g.getBis()+":"+e,Static.get_ms()-lClock);
                                e.printStackTrace();
				Static.printError("AU_Timer.Rechnen(): Modell "+sModell+": Exception - "+e);
                                if (iEigFehler>0 && !TabRechnen.getB("Fehler"))
                                {
                                  Qry = new SQL(g);
                                  Qry.add("aic_bew_pool", iAic);
                                  Qry.add("aic_eigenschaft",iEigFehler);
                                  Qry.add("spalte_boolean", 1);
                                  Qry.add("aic_logging",g.getLog());
                                  Qry.insert("bew_boolean", false);
                                  Qry.free();
                                }
                                BtnFehler.setText(VecFehler.size()+" Fehler");
			}
			g.exec("delete from bew_boolean where aic_bew_pool=" + iAic + " and aic_eigenschaft=" + g.iTimerSperre);
		}
		Logcheck();
                if (CbxDebug.isSelected())
                  if (new Message(Message.YES_NO_OPTION,Fom,g).showDialog("Weiterrechnen",new String[] {}) == Message.NO_OPTION)
                    Stop(true);
		g.printInfo("---------------------------------------------------------------------");
		return true;
	}

        private boolean checkExit()
        {
          //g.testInfo("bExit="+bExit+", isConnected="+g.isConnected()+" / "+g.getWarningObjects());g.isConnected()
          boolean bConnect=SQL.getInteger(g,"select count(aic_stammtyp) from stammtyp")>0;
          if (bExit && !bConnect)
          {
            long lClock=Static.get_ms();
            RunClose();
            if (Static.bX11)
              Fom.dispose();
            ProtSave(0,null,"disconnected",Static.get_ms()-lClock);
            //if (sConnect[1] == null)
              System.exit(6);
          }
          return bConnect;
        }

        private void checkExit2()
        {
          long lClock=Static.get_ms();
          int iStatus= iPU>0 ? SQL.getInteger(g,"select "+g.int1()+" from parameter where aic_parameter="+iPU,5):4;
          g.testInfo("checkExit2:Status="+iStatus+"/"+lClock);
          if (iStatus==4)
          {
            if (g.getLog()>0)
              g.Logout(false);
            else
              g.disconnect();
            ProtSave(0,null,"deactivated",Static.get_ms()-lClock);
            System.exit(7);
          }
          else if (iStatus>0 && !bDeAktiv)
          {
            boolean bRaus=true;
            boolean bExit=false;
            if (iStatus==6)
            {
                Vector Vec=SQL.getVector("select "+g.int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='raus'",g);
                bRaus=Vec.contains(new Integer(g.getLog()));
                bExit=bRaus;
            }
            if (bExit)
              ENDE("single-logout",true);
            else if (bRaus)
            {
              bDeAktiv = true;
              if (iStatus != 5)
                g.delSperren();//Clean(true);
              Stop(false);
              timer.start();
            }
          }
        }

	private void checkConnection()
	{
          boolean bConnect=checkExit();
		timer.stop();
		int i=0;
                if (!bConnect)
                {
                  Transact t = new Transact();
                  t.bExit = false;
                  while(!t.connect(sConnect) && i < 60) {
                    t.testInfo("Keine Verbindung -> warte " + i);
//                    if (sConnect[1] != null)
//                      iC=1-iC;
                    Static.sleep(15000);
                    i++;
                  }
                  if(t.isConnected())
                  {
                    t.unConnect();
                    g = new Global(sConnect, "Timer" + (iMode == 0 ? "" : iMode == 1 ? "+" : "-")+sNr,sDbUser,sDbPW,1);
                    g.bCC=bCC;
                    g.bExit = false;
                    Transact.bAusgabe=bAusgabe;
                    if (sMandant != null)
                      g.setMandantT(sMandant);
                    g.checkSpracheLand();
                    iModellOld=0;
                    calc=null;
                  }
                }
                timer.start();
	}
        private void RB()
        {
          long lClock=Static.get_ms();
          try
          {
            ShowAbfrage.refreshBerechtigung(g);
            ProtSave(0,null,"refreshBerechtigung",Static.get_ms()-lClock);
          }
          catch(Exception e)
          {
              ProtSave(0,null,"kein RB",Static.get_ms()-lClock);
              Stop(false);
              Start(false);
          }

        }

        private File LogFile()
        {
          return new File(Static.DirError+"Timer"+(iMode==0?"":iMode==1?"m":"o")+new java.text.SimpleDateFormat("_yyyy-MM").format(new java.util.Date())+".log");
        }

	private void ProtSave(int iANR,java.sql.Timestamp ts,String s,long l)
	{
          String sZeit=ts==null ? "":""+new Zeit(ts, "dd.MM.yyyy HH:mm");
          String sTitel="mem\tfree\tProz\tAbf-Tab-SQL\tclock\tsql\tver\tcount\tclient\tanr\tvalid\tcomment";
		if (!Save.prot(sTitel,LogFile(),Runtime.getRuntime().totalMemory()+"\t"+Runtime.getRuntime().freeMemory()+"\t"+new Zahl1(Static.Used(),"#0.0 %")+"\t"+Count.get(Count.AbfrageLaden)+"-"+Count.get(Count.Tabellenspeicher)+"-"+Count.get(Count.SQL)+"\t"+l+"\t"+
			(g.getSqlms()-SqlmsLast)+"\t"+Version.getVer2()+"\t"+iCalcAnzahl+"\t"+g.getMandant()+"\t"+iANR+"\t"+sZeit+"\t"+s,true))
          {
            g.printError("Timer geschlossen, da protokollieren in "+LogFile()+" nicht möglich");
            if (g.getLog()>0)
            {
              g.Logout(false);
              RunClose();
              if (Static.bX11)
                  Fom.dispose();
              System.exit(0);
            }
          }
		SqlmsLast = g.getSqlms();
	}
	private void fillGrid()
	{
		int iAnzahl=0;
		JCOutlinerFolderNode Nod1=(JCOutlinerFolderNode)GidMom.getRootNode();
		JCOutlinerFolderNode Nod2=(JCOutlinerFolderNode)GidLater.getRootNode();
		Nod1.removeChildren();
		Nod2.removeChildren();
		java.util.Vector<Object> VecStamm=new java.util.Vector<Object>();
		DWnow=sBis==null ? new DateWOD():new DateWOD(sBis);
		double dJetzt=DWnow.getAbsSeconds();
		int iJetzt=Static.DateToInt(DWnow.getTime());
		for(TabRechnen.moveFirst();!TabRechnen.eof();TabRechnen.moveNext())
			if (!VecFehler.contains(TabRechnen.getInhalt("Aic")))
			{
				Object ObjStamm=TabRechnen.getInhalt("Stamm");
				Vector<Object> VecSpalten = new Vector<Object>();
				VecSpalten.addElement(TabRechnen.getInhalt("Gueltig"));
				int iPos=g.TabBegriffe.getPos("Aic",TabRechnen.getI("aic_begriff"));
                                VecSpalten.addElement(TabRechnen.isNull("Sperre") ? null:"x");
				VecSpalten.addElement(g.TabBegriffe.getInhalt("Bezeichnung",iPos));
				VecSpalten.addElement(TabRechnen.getInhalt("Bezeichnung"));
                                if (bMup)
                                  VecSpalten.addElement(g.TabMandanten.getBezeichnungS(TabRechnen.getI("stamm_mandant")));
				Vector<Object> VecAic = new Vector<Object>();
				VecAic.addElement(TabRechnen.getInhalt("Aic"));
				VecAic.addElement(TabRechnen.getInhalt("aic_begriff"));
				VecAic.addElement(ObjStamm);
				VecAic.addElement(TabRechnen.getInhalt("von"));
				VecAic.addElement(TabRechnen.getInhalt("bis"));
				int iDatum2=TabRechnen.getI("LDATE3");
				boolean bJetzt=(TabRechnen.getF("Gueltig")<dJetzt || iDatum2>0 && iDatum2<=iJetzt) && (ObjStamm==null || !VecStamm.contains(ObjStamm));
				JCOutlinerNode NodNeu = new JCOutlinerNode(VecSpalten,bJetzt?Nod1:Nod2);
				NodNeu.setUserData(VecAic);
				if (bJetzt)
				{
					if (ObjStamm != null)
						VecStamm.addElement(ObjStamm);
					TabRechnen.setInhalt("Node",NodNeu);
					TabRechnen.setInhalt("Jetzt",Boolean.TRUE);
					iAnzahl++;
				}
			}
		bWerteVorhanden=iAnzahl>0;
                if (bWerteVorhanden)
                  g.fixtestInfo("Aktuell "+iAnzahl+" Zeilen zum berechnen");
	}
        /*private void checkAbschluss()
        {
          if (g.TabMA !=null && !g.TabMA.isEmpty())
          {
            for (g.TabMA.moveFirst(); !g.TabMA.eof(); g.TabMA.moveNext())
              if (g.TabMA.getI("AIC_Abfrage") > 0)
              {
                int iAbf = g.TabMA.getI("AIC_Abfrage");
                Vector Vec2;
                if (TabBFilter.posInhalt("Abfrage", iAbf))
                  Vec2 = (Vector)TabBFilter.getInhalt("Daten");
                else
                {
                  ShowAbfrage Abf = new ShowAbfrage(g, iAbf, Abfrage.cstHS_Filter);
                  Vec2 = new Vector(Abf.getDaten(Abf.iStt, 0, null).getVecSpalte("AIC_STAMM"));
                  TabBFilter.addInhalt("Abfrage", iAbf);
                  TabBFilter.addInhalt("Daten", Vec2);
                }
                g.TabMA.setInhalt("Daten",Vec2);
              }
            //g.TabMA.showGrid("checkAbschluss");
          }
        }*/

    // add your data members here
	private Global g;
	private DateWOD DWnow=null;
    //private int iC=0;
	private String sConnect=null;
        private JButton BtnStart = null;
	private JButton BtnStop = null;
        //private JButton BtnClean = null;
        private JButton BtnFehler = null;
	private JButton BtnBeenden = null;
        //private JButton BtnTest = null;
	//private JCheckBox CbxRechnen = null;
	//private JCheckBox CbxTest = new JCheckBox("Test:");
	private JCheckBox CbxExec = null;
	private JCheckBox CbxDebug = null;
	//private All_Unlimited.Allgemein.Eingabe.Datum DtFort;

	private boolean bBerechnen;
        private boolean bDeAktiv=false;
	private boolean bBerechnung=false;
	private boolean bWerteVorhanden=false;
	private boolean bWerteHolen=false;
	//private boolean bLastTimer=true;
        private boolean bProg=false;
        private boolean bTRB=false;

	private JCOutliner GidMom = new JCOutliner(new JCOutlinerFolderNode("Aktuelle Berechnung"));
	private JCOutliner GidLater = new JCOutliner(new JCOutlinerFolderNode("Spätere Berechnung"));

	private javax.swing.Timer timer = new javax.swing.Timer(1000,this);

	private JLabel LblDatum = new JLabel(Save.now());
	private SpinBox EdtSekunden = new SpinBox(10,1,60,1,Color.WHITE);
	private SpinBox EdtFaktor= new SpinBox(12,2,100,1,Color.WHITE);

	private String sAbfrage;

	private Tabellenspeicher TabRechnen;

	/*private int iEigModell=0;
	private int iEigFort=0;
	private int iEigZR=0;
	private int iEigStamm=0;*/

	private int iHolNummer=0;

	private Vector<Integer> VecFehler=new Vector<Integer>();
        private int iEigFehler=0;
	private long SqlmsLast=0;

	private All_Unlimited.Hauptmaske.Calc calc=null;
	private int iModellOld=0;
	private int iCalcAnzahl=0;
	private boolean bAuto=false;
	private long lMax=0;
	private int iMaxBefehle=200000;
	private int iMode=0;
	private long lInit=0;
	private long lInitNext=0;
	//private File fileRun;
	private static String sRun;
	private static FileOutputStream fileRun;
	private boolean bAusgabe=true;
        private boolean bExit=false;
        private String sMandant=null;
        //private Tabellenspeicher TabBFilter=null;
        private JDialog FrmAufruf=null;
        private Datum DatVon;
        private Datum DatBis;
        private StammEingabe SteStamm;
        private SpinBox SpnAbbruch = new SpinBox();
        private SpinBox SpnDebug = new SpinBox();
        private SpinBox SpnPause = new SpinBox();
        private JButton BtnAufrufOk;
        private JButton BtnAufrufAbbruch;
        private boolean bNext=false;
        private boolean bMup=false;
        private int iQryAb=-1;
        private long lAbfAb=0;
        private static int iPU=0;
        private String sANR=null;
        private String sBis=null;
        private Vector VecANR=null;
        private String sNr="";
        private String sArgs;
        private String sDbUser=null;
        private String sDbPW=null;
        //private boolean bView2=false;
        //private boolean bInsert2=false;
        private JFrame Fom=null;
        private int iSek=0;
        private int iFak=0;
        private boolean bCC=false;
        private static boolean bTestinfo=true;
        private double dRC=0.6; // reConnect bei 60%
        private double dExit=0.95; //Exit bei 95% des Speichers
        private double dShow=0.7;
}

