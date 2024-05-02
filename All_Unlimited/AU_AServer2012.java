package All_Unlimited;
/**

 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.8
 */

//import java.util.Scanner;
//import java.math.BigInteger;
import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
//import All_Unlimited.Allgemein.Anzeige.Zeit;


import java.net.*;
import java.io.*;
import java.util.*;

import All_Unlimited.Hauptmaske.*;
import All_Unlimited.Grunddefinition.Reinigung;
import All_Unlimited.Grunddefinition.UserManager;


//import java.math.BigDecimal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import All_Unlimited.Grunddefinition.DefImport;
import All_Unlimited.Grunddefinition.Updateeinstellung;
import All_Unlimited.Print.DruckAufrufA;
import All_Unlimited.Print.Drucken;
import All_Unlimited.Grunddefinition.Systemeinstellung;

public class AU_AServer2012
{
  public static Global g=null;
  private Tabellenspeicher TabCalc;
  public static javax.swing.Timer timer=null;
  public static int iWD=240; // Watchdogaufruf in Sekunden
  private static int iLC=3600; // Automatisches ausloggen in Sekunden;
  private static int iMaxLog=0;
  private static boolean bTestinfo=true;
  private static long lMaxMem=0;
  public static int iMaxBefehle=200000;
  private static long lCleanLast=0;
  public static long lCleanDif=0;
  public static long lImportLast=0;
  public static long lImportDif=0;
  private static long lInit=0;
  public static long lInitLast=0;
  public static boolean bClean=false; // Reinigungslauf
  public static boolean bBackup=false;
  public static boolean bUpdate=false;
  private static String sRun;
  private static FileOutputStream fileRun;
  public static boolean bSQL_Aktiv=false;
  public static boolean bClose=false;
  public static boolean bNoClean=false;
  public static boolean bNull=true;
  public static boolean bLDate=true;
  public static boolean bProt=true;
  public static boolean bTC=true;
  public static boolean bLog=true;
  public static boolean bFremd=true;
  public static boolean bFirma=false;
  public static boolean bCalcClean=true;
  public static boolean bBild=true;
  public static boolean bFrist=true;
  public static boolean bNE=true;
  public static boolean bIndex=true;
  static String sMandant=null;
  private static String sDirProg=null;
  //private static String sDirBackup=null;
  //private static String sDirUpdate=null;
  private static String sDB=null;
  private static String sUser=null;
  private static String sPW=null;
  private static String sHost=null; //Backup-Hostname
  private static String sBP=null;  //Backup-Port
  private static boolean bGzip=false;
  private static boolean bAB=false;
  private static boolean bNB=false;
  //private static boolean bAU=false;
  public static boolean bMM=false;
  public static boolean bDebug=false;
  public static boolean bFP=false; // full Protokoll (pro Anweisung eine Zeile)
  public static boolean bFA=false;
  public static boolean bCID=false;
  public static boolean bSII=false;
  static String sDbUser=null;
  static String sDbPW=null;
  static int iBenutzer=0;
  static int iBI=0;
  static String sBackupFile="!kein!";
  private static int iBackup=0;
  private static int iIndex=0;
  static String sUpdate="!kein!";
  static boolean bCC=false;
  static boolean bProgDir=true;
  private static Tabellenspeicher TabSI=null; // anstehende Stempelimporte 
  //private static boolean bSS_Aktiv=false;
  //private int iPU=0;
  private long lStart=Static.get_ms();

  public AU_AServer2012(String sJDBC,int iPort) throws IOException
  {
    Transact.fixInfoS("All Unlimited-Version="+Version.getVersion()+" ("+Version.getDate()+")");
    Transact.fixInfoS("Speicher:"+(Runtime.getRuntime().maxMemory()/1024/1024)+" MB");
    Transact.fixInfoS("Land:"+Locale.getDefault().getCountry());
    if (connectDB(sJDBC))
    {
      int iDO=SQL.getInteger(g,"select "+g.int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Option'");
      //Static.bView2 = (iDO&1)>0;
      Static.bInsert2 = (g.MS() || g.MySQL()) && (iDO&2)>0;
      if (g.MySQL())
      {
        sDB=SQL.getString(g,"select database()");
        if (bProgDir && sDirProg==null)
        {
        	if (g.My8())
        		sDirProg=SQL.getString2(g,"show global variables like 'basedir'");
        	else
        		sDirProg=SQL.getString(g,"select variable_value from information_schema.session_variables where variable_name='BASEDIR'");
        	if (!sDirProg.endsWith(File.separator))
        		sDirProg+=File.separator;
	        sDirProg+="bin"+File.separator;
	        if (sDirProg.indexOf(" ")>0)
	        {
	          Transact.fixInfoS("Programmpfad <"+sDirProg+"> enthält Leerzeichen");
	          Transact.fixInfoS("Bitte mysqldump ins aktuelle Verzeichnis kopieren");
	          sDirProg=null;
	        }
        }
        Transact.fixInfoS("Prog:"+sDirProg);
        //Transact.fixInfoS("Backup:"+sDirBackup);
      }
      else
        sDB=g.getDB();
      //PfadeEinlesen();
      Transact.fixInfoS("Datenbank="+sDB);
      lInitLast=Static.get_ms();
      /*if (bAU)
      {
        Backup(true);
        Update(null);
      }
      else*/
      {
    	TabSI=new Tabellenspeicher(null, new String[] {"Mandant","Art","erfasst","Beginn","Ende"});
        startTimer();
        TabCalc = new Tabellenspeicher(null, new String[] {"g", "model", "calc", "used"});
        ProtSave(g,null,"AServer",lStart);
        try
        {
          startSS(iPort);
        }
        catch (Exception io)
        {
          Static.printError("AServer-Verbindung mit Port "+iPort+" fehlerhaft:"+ io);
          deAktiv(TabCalc,false);
        }
      }
    }
    else
    {
      g.Logout(false);
      if (g.isConnected())
        g.unConnect();
      Static.sleep(15000);
    }
  }
  
  private static File LogFile()
  {
    return new File(Static.DirError+"AServer"+new java.text.SimpleDateFormat("_yyyy-MM").format(new java.util.Date())+(bCID ? "c":"")+".log");
  }
  
  static void ProtSave(Global g,Tabellenspeicher TabCalc,String s,long lStart)
  {
	  ProtSave(g,TabCalc,s,lStart,0);
  }

static void ProtSave(Global g,Tabellenspeicher TabCalc,String s,long lStart,int iStamm)
{
	if (bFP || bFA)
	{
	    String sTitel="mem\tfree\tProz\tAbf-Tab-SQL\tconnects"+(bCID ?"\tcID":"")+"\tclock\tver\tcount\tcomment\tclient\tzr\tstamm";
	    int iCalcAnzahl=TabCalc==null ? 0:TabCalc.size();
	    int iCon=g==null || g.Disconnected() || !g.isConnected() ? 0:g.MySQL() ? SQL.getInteger2(g,"show global status like 'threads_connected'"):
	    	g.MS() ? SQL.getInteger(g,"select count(*) from master.dbo.sysprocesses where net_library='TCP/IP'"):-1;
		if (!Save.prot(sTitel,LogFile(),Runtime.getRuntime().totalMemory()+"\t"+Runtime.getRuntime().freeMemory()+"\t"+new Zahl1(Static.Used(),"#0.0 %")+"\t"+Count.get(Count.AbfrageLaden)+"-"+Count.get(Count.Tabellenspeicher)
			+"-"+Count.get(Count.SQL)+"\t"+g.count()+"/"+iCon+(bCID ?"\t"+(g==null || g.Disconnected() ? 0:g.Sid()):"")+"\t"+(Static.get_ms()-lStart)+"\t"+Version.getVer2()+"\t"+iCalcAnzahl+"\t"+s+"\t"+(g==null ? "":g.getMandant(0,null))+"\t"+(g==null ? "":g.getVonBis("dd.MM.yyyy",true))+(iStamm>0 ? "\t"+g.getStamm(iStamm):""),true))
	    {
	      g.printError("AServer geschlossen, da protokollieren in "+LogFile()+" nicht möglich");
	      if (g.getLog()>0)
	      {
	        g.Logout(false);
	        RunClose();
	        System.exit(0);
	      }
	    }
	}
}

  private static String PfadeEinlesen(int iArt,boolean bCheckBackup)
  {
    String sRet=null;
    boolean bNC = g.Disconnected();
	if (bNC)
		g.connect(null);
    Parameter Para = new Parameter(g,"Update");
    Tabellenspeicher Tab=Para.getParameter("Dir");
    for (Tab.moveFirst();!Tab.out();Tab.moveNext())
    {
      int i=Tab.getI("int1");
      String s=Tab.getS("Parameterzeile");
      if (i==iArt)
        sRet=s;
      /*if (i==Updateeinstellung.INST)
        sDirUpdate=s;
      else if (i==Updateeinstellung.BACKUP)
        sDirBackup=s;*/
    }
    Para.free();
    if (bCheckBackup)
    {
    	Para = new Parameter(g,"Datenbank");
    	Para.getParameter("Backup", false, false);
    	iBackup=Para.int1;
    	if (Para.bGefunden && iBackup==Sort.geti(new DateWOD().Format("yyyyMMdd")))
    	{
    		sRet=null;
    		g.fixtestError("kein Backup da heute schon gesichert:"+iBackup+" "+Para.int2);
    	}
    	Para.free();
    }
    if (bNC && g.MySQL())
    	g.unConnect();
    //Transact.fixInfoS("Backup:"+sDirBackup);
    //Transact.fixInfoS("Update:"+sDirUpdate);
    return sRet;
  }

  private boolean connectDB(String sJDBC)
  {
    g = new Global(sJDBC,"AServer",sDbUser,sDbPW,1);
    //if (g.MS()) bCC=false;
    g.bCC=bCC;
    if (bCC)
    	Transact.fixInfoS("Wechselwährung "+(bCC ? "eingeschaltet":"ausgeschaltet"));
    if (!bTestinfo)
      Transact.iInfos |= Transact.NO_INFO;
    int iPU=SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
    int iStatus= /*bAU ? -3:*/iPU>0 ? SQL.getInteger(g,"select "+g.int1()+" from parameter where aic_parameter="+iPU,5):-2;
    if (iStatus!=0)
    	Transact.fixInfoS("Status="+iStatus);
    if (iStatus==-2 || iStatus==4)
      return false;
    else if (iStatus!=0 && iStatus!=6)
    {
      // g.unConnect(); // nicht erlaubt, da danach Verbindung benötigt
      //bSS_Aktiv=true;
      return true;
    }
    bSQL_Aktiv=true;
    if (Version.UpdateNoetig(g))
    {
      //PfadeEinlesen();
      Backup(true);
    }
    //if (new Version(g).OK())
    if (sMandant != null)
      g.setMandantT(sMandant);
    if (new Version(g).OK())
      g.checkSpracheLand();
    else
      return false;
    /*else
      return false;*/
    g.Login();
    String sDirError= Static.DirError;
    Systemeinstellung.LoadDir(g,null,-1);
    Static.DirError = sDirError;

    iBenutzer=g.getBenutzer();
    ShowAbfrage.refreshBerechtigung(g);
    if (!bTestinfo)
      Transact.iInfos |= Transact.NO_INFO;
    return g.isConnected();
  }

  private void startTimer()
  {
    if (timer != null)
      return;
    //Global.fixInfoS("startTimer");
    timer = new javax.swing.Timer(iWD*1000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        WatchDog();
      }
    });
    timer.setDelay(iWD*1000);
    timer.start();
  }
  
  public static void Clean_Frist(Global g)
  {
	  long lClock = Static.get_Mikro_s();
	  Vector<Integer> VecM=SQL.getVector("select aic_mandant from mandant where tod is null",g);
	  DateWOD DW=null;
	  for (int i1=0;i1<VecM.size();i1++)
	  {
		  int iM=Sort.geti(VecM, i1);
		  // int iTab=g.TabTabellenname.getAic(bEig?"EIGENSCHAFT":"BEWEGUNGSTYP");
		  int iTabEig=g.TabTabellenname.getAic("EIGENSCHAFT");
		  Tabellenspeicher TabEigAus=new Tabellenspeicher(g,"select aic_fremd,frist_austritt from verboten where frist_austritt>0 and aic_tabellenname="+iTabEig+" and aic_mandant="+iM,true);
		  for (TabEigAus.moveFirst();!TabEigAus.out();TabEigAus.moveNext())
		  {
			  int iEig=TabEigAus.getI("aic_fremd");
			  String sBezEig=g.TabEigenschaften.getBezeichnungS(iEig);
			  DW=new DateWOD();
			  DW.setTimeZero();
			  for (int i=0;i<TabEigAus.getI("Frist_Austritt");i++)
				  DW.prevMonth();
			  g.fixtestInfo("zu entfernen: Austritt-MA bei Eigenschaft "+sBezEig+" vor "+DW.Format("dd.MM.yyyy"));			  
			  Tabellenspeicher Tab=new Tabellenspeicher(g,"select s.bezeichnung,s.austritt,p.aic_stammpool,p.aic_stamm from stammpool p join stamm_protokoll s on p.aic_stamm=s.aic_stamm and p.aic_eigenschaft="+iEig+
					  " where s.aic_rolle="+g.iRolMA+" and s.pro_aic_protokoll is null and s.austritt is not null and s.aic_mandant="+iM+" and s.austritt<"+g.DateTimeToString(DW.toTimestamp()),true);
			  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
			  {
				  g.fixtestInfo("entferne "+sBezEig+" bei "+Tab.getS("Bezeichnung")+" (Austritt="+Tab.getDate("Austritt")+"):"+Tab.getI("Aic_stammpool"));
				  int iAnz=g.exec("delete from stammpool where aic_stammpool="+Tab.getI("Aic_stammpool"));
				  g.SaveVerlauf(g.getBegriffAicS("Radiobutton", "Frist_Austritt"), Tab.getI("aic_stamm"), 0, 0, iAnz+" bei "+sBezEig,0);
			  }
		  }
		// Reinigung Eigenschaften laut Eingabe
		  Tabellenspeicher TabEigEin=new Tabellenspeicher(g,"select aic_fremd,frist_anlage from verboten where frist_anlage>0 and aic_tabellenname="+iTabEig+" and aic_mandant="+iM,true);
		  for (TabEigEin.moveFirst();!TabEigEin.out();TabEigEin.moveNext())
		  {
			  int iEig=TabEigEin.getI("aic_fremd");
			  String sBezEig=g.TabEigenschaften.getBezeichnungS(iEig);
			  DW=new DateWOD();
			  DW.setTimeZero();
			  for (int i=0;i<TabEigEin.getI("frist_anlage");i++)
				  DW.prevMonth();
			  g.fixtestInfo("zu entfernen: Timestamp bei Eigenschaft "+sBezEig+" vor "+DW.Format("dd.MM.yyyy"));
			  Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct s.bezeichnung,p2.timestamp,p.aic_stammpool,p.aic_protokoll,p.aic_stamm from stammpool p join stamm_protokoll s on p.aic_stamm=s.aic_stamm and s.aic_mandant="+iM+
					  " and p.aic_eigenschaft="+iEig+" and s.aic_stammtyp2="+g.iSttANR+" and s.pro_aic_protokoll is null join protokoll p2 on p2.aic_protokoll=p.aic_protokoll and p2.timestamp<"+g.DateTimeToString(DW.toTimestamp()),true);
			  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
			  {
				  g.fixtestInfo("entferne "+sBezEig+" bei "+Tab.getS("Bezeichnung")+" (Timestamp="+Tab.getDate("timestamp")+"):"+Tab.getI("Aic_stammpool"));
				  int iAnz=g.exec("delete from stammpool where aic_stammpool="+Tab.getI("Aic_stammpool"));
				  g.SaveVerlauf(g.getBegriffAicS("Radiobutton", "Frist_Anlage"), Tab.getI("aic_stamm"), 0, 0, iAnz+" bei "+sBezEig,0);
			  }
		  }
		  int iTabBew=g.TabTabellenname.getAic("BEWEGUNGSTYP");
		// Reinigung Bewegung laut Austritt
		  Tabellenspeicher TabBewAus=new Tabellenspeicher(g,"select aic_fremd,frist_austritt from verboten where frist_austritt>0 and aic_tabellenname="+iTabBew+" and aic_mandant="+iM,true);
		  for (TabBewAus.moveFirst();!TabBewAus.out();TabBewAus.moveNext())
		  {
			  int iBew=TabBewAus.getI("aic_fremd");
			  String sBezBew=g.TabErfassungstypen.getBezeichnungS(iBew);
			  DW=new DateWOD();
			  DW.setTimeZero();
			  for (int i=0;i<TabBewAus.getI("Frist_Austritt");i++)
				  DW.prevMonth();
			  g.fixtestInfo("zu entfernen: Austritt-MA bei Bewegung "+sBezBew+" vor "+DW.Format("dd.MM.yyyy"));
			  Tabellenspeicher Tab=new Tabellenspeicher(g,"select s.bezeichnung,s.austritt,p.aic_bew_pool,p.pro_aic_protokoll from bew_pool p join stamm_protokoll s on p.anr=s.aic_stamm and s.aic_mandant="+iM+
					  " and s.aic_rolle="+g.iRolMA+" and s.pro_aic_protokoll is null and s.austritt is not null where aic_bewegungstyp="+iBew+" and s.austritt<"+g.DateTimeToString(DW.toTimestamp()),true);
			  if (Tab.size()>0)
			  {
			    Vector<Integer> Vec=Tab.getVecD("aic_bew_pool");
			    int iAnz=Aufruf.destroyAll(g,Vec);
			    g.fixtestInfo(Vec.size()+" Bew_Pool von "+sBezBew+" wegen Frist_Austritt entfernt");
			    g.SaveVerlauf(g.getBegriffAicS("Radiobutton", "Frist_Austritt"), 0, 0, 0, iAnz+" bei "+sBezBew,0);
			  }
		  }
		// Reinigung Bewegung laut Eingabe
		  Tabellenspeicher TabBewEin=new Tabellenspeicher(g,"select aic_fremd,frist_anlage from verboten where frist_anlage>0 and aic_tabellenname="+iTabBew+" and aic_mandant="+iM,true);
		  for (TabBewEin.moveFirst();!TabBewEin.out();TabBewEin.moveNext())
		  {
			  int iBew=TabBewEin.getI("aic_fremd");
			  String sBezBew=g.TabErfassungstypen.getBezeichnungS(iBew);
			  DW=new DateWOD();
			  DW.setTimeZero();
			  for (int i=0;i<TabBewEin.getI("Frist_Anlage");i++)
				  DW.prevMonth();
			  g.fixtestInfo("zu entfernen: Timestamp bei Bewegung "+sBezBew+" vor "+DW.Format("dd.MM.yyyy"));
			  Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct s.bezeichnung,p2.timestamp,p.aic_bew_pool from protokoll p2 join bew_pool p on p2.aic_protokoll=p.aic_protokoll join stamm_protokoll s on p.anr=s.aic_stamm and s.aic_mandant="+iM+
					  " and s.aic_stammtyp2="+g.iSttANR+" and s.pro_aic_protokoll is null where aic_bewegungstyp="+iBew+" and timestamp<"+g.DateTimeToString(DW.toTimestamp()),true);
			  if (Tab.size()>0)
			  {
			    Vector<Integer> Vec=Tab.getVecD("aic_bew_pool");
			    int iAnz=Aufruf.destroyAll(g,Vec);
			    g.fixtestInfo(Vec.size()+" Bew_Pool von "+sBezBew+" wegen Frist_Anlage entfernt");
			    g.SaveVerlauf(g.getBegriffAicS("Radiobutton", "Frist_Anlage"), 0, 0, 0, iAnz+" bei "+sBezBew,0);
			  }
		  }
	  }
	  Transact.fixInfoS("Frist-Reinigung: " + Static.Mikro(lClock));
  }

  public static void Clean_Benutzer(Global g)
  {
	long lClock = Static.get_Mikro_s();
    Vector<Integer> VecM=SQL.getVector("select aic_mandant from mandant where tod=1",g);
    if (VecM.size()>0)
    {
      Vector<Integer> VecBG = SQL.getVector("select aic_benutzergruppe from benutzergruppe where" + g.in("aic_mandant", VecM), g);
      if (VecBG.size() > 0)
      {
        DelFromTab(g,"Benutzer_Zuordnung",g.in("aic_benutzergruppe", VecBG));
        DelFromTab(g,"Abschluss_Zuordnung",g.in("aic_benutzergruppe", VecBG));
        DelFromTab(g,"Berechtigung",g.in("aic_benutzergruppe", VecBG));
        g.exec("update benutzer set aic_Benutzergruppe=null where" + g.in("aic_benutzergruppe", VecBG));
        g.exec("update abfrage set aic_Benutzergruppe=null where" + g.in("aic_benutzergruppe", VecBG));
        DelFromTab(g,"Spalten2",g.in("aic_benutzergruppe", VecBG));
        DelFromTab(g,"Spalte_z2",g.in("aic_benutzergruppe", VecBG));
        DelFromTab(g,"Benutzergruppe",g.in("aic_benutzergruppe", VecBG));
      }
      int iAnzDU=g.exec("update benutzer set geloescht="+g.now()+",kennung="+g.concat("'-'","kennung")+" where geloescht is null and"+g.in("aic_mandant", VecM));
      if (iAnzDU>0)
    	  g.diskInfo(iAnzDU+" Benutzer auf gelöscht markiert gesetzt");
    }
    Vector<Integer> VecBalt=SQL.getVector("select aic_benutzer from benutzer where geloescht is null and use_bis<"+g.now(), g);
    for (int i=0;i<VecBalt.size();i++)
    	UserManager.LoescheBenutzer(g, Sort.geti(VecBalt, i),g.getBegriffAicS("Button", "AServer"),false);

    Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_benutzer,kennung from benutzer where (geloescht is not null "+(VecM.size()==0?"":"or"+g.in("aic_mandant", VecM))+")"+
                                              " and ((select count(*) from logging where aic_benutzer=benutzer.aic_benutzer)"+
                             "+(select count(*) from HAUPTSCHIRM where aic_benutzer=benutzer.aic_benutzer)+(select count(*) from Abfrage where aic_benutzer=benutzer.aic_benutzer)"+
                             "+(select count(*) from Spalten2 where aic_benutzer=benutzer.aic_benutzer)+(select count(*) from spalte_z2 where aic_benutzer=benutzer.aic_benutzer))=0",true);
    if (Tab.size()>0)
    {
      Vector<Object> Vec=Tab.getVecSpalte("aic_Benutzer");
      DelFromTab(g,"Benutzer_Zuordnung",g.in("aic_Benutzer",Vec));
      DelFromTab(g,"Parameter",g.in("aic_Benutzer",Vec));
      DelFromTab(g,"Druck",g.in("aic_Benutzer",Vec));
      if (g.exec("delete from benutzer where"+ g.in("aic_Benutzer",Vec))>0)
        g.diskInfo("Folgende Benutzer wurden gelöscht:"+Sort.gets2(Tab.getVecSpalte("kennung"),','));
    }
    if (VecM.size()>0)
    {
      Tab=new Tabellenspeicher(g,"select aic_mandant,kennung,tod from mandant where tod=1 "+
                               " and((select count(*) from logging where aic_mandant=mandant.aic_mandant)+(select count(*) from benutzer where aic_mandant=mandant.aic_mandant)+(select count(*) from bew_pool where aic_mandant=mandant.aic_mandant))=0",true);
      if (Tab.size()>0)
      {
        Vector<Object> Vec=Tab.getVecSpalte("aic_mandant");
        DelFromTab(g,"Lizenz",g.in("aic_mandant", Vec));
        DelFromTab(g,"Parameter",g.in("aic_mandant", Vec));
        if (g.exec("delete from mandant where"+ g.in("aic_mandant",Vec))>0)
          g.diskInfo("Folgende Mandanten wurden gelöscht:" + Sort.gets2(Tab.getVecSpalte("kennung"), ','));
      }
    }
    Transact.fixInfoS("Benutzer-Reinigung: " + Static.Mikro(lClock));
  }

  public static void Clean_Logging(DateWOD dt,Global g)
  {
	  long lClock = Static.get_Mikro_s();
    int iLogMax=SQL.getInteger(g,"select max(aic_logging) from logging where ein<"+g.DateTimeToString(dt.toTimestamp()));
    Vector<Integer> VecB=SQL.getVector("select aic_benutzer from benutzer where geloescht is not null",g);
    if (VecB.size()>0)
    {
      int iAnz=g.exec("delete from parameter where (aic_logging is null or aic_logging<="+iLogMax+") and "+ g.in("aic_Benutzer",VecB));
      if (iAnz>0) g.diskInfo(iAnz + " PARAMETER gelöscht");
      iAnz=g.exec("delete from fensterpos where aic_logging in (select aic_logging from logging where aic_logging<="+iLogMax+" and "+ g.in("aic_Benutzer",VecB)+")");
      if (iAnz>0) g.diskInfo(iAnz + " FENSTERPOS gelöscht");
    }
    Tabellenspeicher Tab=new Tabellenspeicher(g,g.topOrder("* from (select aic_logging,ein,(select count(*) from protokoll where aic_logging=logging.aic_logging)"+
        "+(select count(*) from PARAMETER where aic_logging=logging.aic_logging)+(select count(*) from Feiertag_zuordnung where aic_logging=logging.aic_logging)"+
        "+(select count(*) from BEGRIFF where aic_logging=logging.aic_logging)+(select count(*) from STAMMTYP where aic_logging=logging.aic_logging)"+
        "+(select count(*) from EIGENSCHAFT where aic_logging=logging.aic_logging)+(select count(*) from BEWEGUNGSTYP where aic_logging=logging.aic_logging)"+
        "+(select count(*) from BENUTZER where aic_logging=logging.aic_logging)+(select count(*) from BENUTZERGRUPPE where aic_logging=logging.aic_logging)"+
        "+(select count(*) from DEFVERLAUF where aic_logging=logging.aic_logging)+(select count(*) from MANDANT where aic_logging=logging.aic_logging)"+
        "+(select count(*) from HAUPTSCHIRM where aic_logging=logging.aic_logging) Anzahl from logging where aic_logging<="+iLogMax+") x where Anzahl=0",10000,"aic_logging"),true);
    if (Tab.size()>0)
    {
        Vector Vec=Tab.getVecSpalte("aic_logging");
        String sWhere=g.in("aic_logging",Vec);
        DelFromTab(g,"VERLAUF",sWhere);
        DelFromTab(g,"ASERVER",sWhere);
        DelFromTab(g,"FEHLER",sWhere);
        DelFromTab(g,"FENSTERPOS",sWhere);
        int iAnz=g.exec("delete from LOGGING where"+sWhere);
        if (iAnz>0)
        {
          Tab.moveLast();
          g.diskInfo(iAnz + " LOGGING bis " + Tab.getDate("ein") + " gelöscht");
        }
    }
    Transact.fixInfoS("Logging-Reinigung: " + Static.Mikro(lClock));
  }

  private static void DelFromTab(Global g,String sTab,String sWhere)
  {
    int iAnz=g.exec("delete from "+sTab+" where"+sWhere);
    if (iAnz>0)
      g.diskInfo(iAnz + " "+sTab+" gelöscht");
  }

  private static int Eingeloggt(Global g)
  {
    String sWhere=" where aus is null and aic_code is null";
    int i=SQL.getInteger(g, "select count(*) from logging"+sWhere);
    if (i>0)
    {
      if (iLC<300)
        iLC=300;
      if (!bNoClean &&(g.MySQL() || g.MS())) // Ausloggen wenn 1 Stunde nicht gemeldet
        if (g.exec("update logging set aus="+g.now()+",status="+Transact.AUTOLOGOUT+sWhere+" and "+(g.MySQL() ? "TIME_TO_SEC(TIMEDIFF(now(),mom))":"datediff(s,mom,getdate())")+">"+iLC)>0)
          i=SQL.getInteger(g, "select count(*) from logging"+sWhere);
      Transact.fixInfoS("Noch eingeloggt:" + i);
    }
    return i;
  }

  public static void Backup(boolean bSofort)
  {
	long lGesClock=Static.get_ms();
    String sDirBackup=PfadeEinlesen(Updateeinstellung.BACKUP,!bSofort);
    if (sDirBackup==null && !bSofort)
    	return;
    Transact.fixInfoS("Backup-Verzeichnis:"+sDirBackup);
    sBackupFile="x";
    if (Static.Leer(sDirBackup))
      sBackupFile="!Backup nicht eingerichtet";
    else if (bBackup)
      sBackupFile="!Backup läuft bereits!";
    if (!sBackupFile.equals("x"))
    	Global.fixInfoS(sBackupFile);
    else if (g.MySQL() || g.MS())
    {
      long lClock = Static.get_ms();//Static.get_Mikro_s();
      bBackup=true;
      boolean bNC = g.MS() && !g.isConnected();
      if (bNC)
        g.connect(null);
      if (sDB==null)
        sDB=g.getDB();
      DateWOD DW=new DateWOD();
//      String sDate=new java.text.SimpleDateFormat(bSofort || iBI==0 ? "_yyyy-MM-dd_HHmm":iBI==1 ? "_EEE":"_dd").format(new java.util.Date());
      String sDate=DW.Format(bSofort || iBI==0 ? "_yyyy-MM-dd_HHmm":iBI==1 ? "_EEE":"_dd");
      String sFile=Static.cin(sDirBackup,File.separator)+sDB+sDate+(g.MySQL() ? ".sql":".bak");
      String s=g.MySQL() ? (sDirProg==null ? "":sDirProg)+"mysqldump"+(sHost==null ? "":" -h "+sHost)+(sBP==null ? "":" --port="+sBP)+" --add-drop-table -a -u "+
    		  (sUser==null ? "root":sUser)+" "+sDB+" -r "+sFile:
          "BACKUP DATABASE "+sDB+" TO DISK = '"+sFile+"' WITH INIT"+(bGzip ?"":",COMPRESSION");
      Global.fixInfoS(s);
      if (sPW != null)
    	  s+=" -p"+sPW;
      //
      try
      {
        if (g.MS())
        {
          g.exec(s);
          if (bNC) g.unConnect();
        }
        else if (g.MySQL())
        {
          //if (bNC) g.unConnect();
          Process rtProcess = Runtime.getRuntime().exec(s);
          rtProcess.waitFor();
        }
        sBackupFile=sFile;
        if (bGzip)
          Runtime.getRuntime().exec("gzip -f " + sFile);
      }
      catch (Exception io)
      {
        sBackupFile="!Backup nicht durchführbar: " + io;
        Static.printError(sBackupFile);
      }
      bBackup=false;
      long lDauer=Static.get_ms()-lClock;
      Transact.fixInfoS("Backupdauer: " + lDauer+" ms");//Static.Mikro(lClock));
      ProtSave(g,null,"Backup",lGesClock);
      bNC = g.Disconnected();
  	  if (bNC)
  		g.connect(null);
      Parameter Para = new Parameter(g,"Datenbank");
      iBackup=Sort.geti(DW.Format("yyyyMMdd"));
      Para.setParameter("Backup", sFile,iBackup,Sort.geti(DW.Format("HHmmss")),Sort.geti(new DateWOD().Format("HHmmss")),(int)lDauer, false, false);
      Para.free();
      if (bNC) g.unConnect();
    }
    else
    {
      sBackupFile="!Backup wird für diesen Datenbanktyp noch nicht unterstützt";
      Static.printError(sBackupFile);
    }
  }

  public static void Update(Tabellenspeicher TabCalc)
  {
    String sDirUpdate=PfadeEinlesen(Updateeinstellung.INST,false);
    Transact.fixInfoS("Update-Verzeichnis:"+sDirUpdate);
    sUpdate="x";
    if (sDirUpdate==null)
      sUpdate="!Update nicht eingerichtet";
    else if (bUpdate)
      sUpdate="!Update läuft bereits!";
    else
      bUpdate=true;
    if (!sUpdate.equals("x"))
      Transact.fixInfoS(sUpdate);
    else
    {
      long lClock = Static.get_Mikro_s();
      if (g.TabBegriffe==null)
        g.checkSpracheLand();
      deAktiv(TabCalc,true);
      if (g.Disconnected())
			g.connect(null);
      sUpdate=DefImport.StartPfad(g,sDirUpdate,null);
      Transact.fixInfoS("sUpdate="+sUpdate);
      //if (!bAU)
        Aktiv(true);
      bUpdate=false;
      Transact.fixInfoS("Updatedauer: " + Static.Mikro(lClock));
    }
  }

  private static void Logcheck(Global g)
  {
    long lGesClock=Static.get_ms();
    if (g.Logcheck()==0)
    {
      ProtSave(g,null,"exit bei Logcheck",lGesClock);
      AU_AServer2012.close(g,null,true,null,8);
    }
  }

  public static void Reinigung(Global g)
  {
	  long lGesClock=Static.get_ms();
    Logcheck(g);
    if (bClean)
        {
          Transact.fixInfoS("Reinigung arbeitet bereits!");
        }
        else if (!bClose)
        {
          long lMomTime = Static.get_ms();
          if ((/*lCleanDif==0 ||*/ lCleanDif > 0 && lMomTime > lCleanLast + lCleanDif) && Eingeloggt(g) <= iMaxLog)
          {
        	  g.diskInfo("Reinigung gestartet");
            bClean = true;
            long lClockV=lMomTime;
            int iVerlauf=g.SaveVerlauf(g.getBegriffAicS("Frame", "Reinigung"),0,0);
            long lClock = Static.get_Mikro_s();
            lCleanLast=lMomTime;
            int iAC = 0;
            //int iTO=Transact.iTimeOut;
            //Transact.iTimeOut=3600;
            g.fixInfo(Reinigung.DSP2_Check(g, ""));
            if (bNull)
            {
//            	g.fixInfoS("Null-Reinigung");
                
              iAC += g.exec("delete from bew_boolean where spalte_boolean=0");
              iAC += g.exec("delete from bew_wert where spalte_wert=0");
              iAC += g.exec("delete from stammpool where spalte_double=0.0");
              if (iAC > 0)
                g.diskInfo(iAC + " Null-Werte entfernt");
            }
            int iDW=Sort.geti(new DateWOD().Format("yyyyMMdd"));
            if (bIndex && iIndex<=iDW-100)
            {
            	if (iIndex==0)
            	{
            		Parameter Para = new Parameter(g,"Datenbank");
                	Para.getParameter("Index", false, false);
                	iIndex=Para.int1;
                	Para.free();
            	}
            	if (iIndex<=iDW-100)
            	{
            		Transact.fixInfoS("Index-Reinigung");
                    
	            	long lClockI=Static.get_ms();
	            	DateWOD DW=new DateWOD();
	            	Reinigung.Optimize(g,null);
	            	Parameter Para = new Parameter(g,"Datenbank");
	            	iIndex=iDW;//Sort.geti(DW.Format("yyyyMMdd"));
	                long lDauer=Static.get_ms()-lClockI;
	                Para.setParameter("Index", "",iIndex,Sort.geti(DW.Format("HHmmss")),Sort.geti(new DateWOD().Format("HHmmss")),(int)lDauer, false, false);
	            	g.diskInfo(" Indexe optimiert");
            	}
            }
            if (bLDate && SQL.getInteger(g,"select count(*) from bew_pool where gueltig is not null and ldate is null")>0)
            {
//            	g.fixInfoS("LDate-Reinigung");
                
              iAC = g.exec("update bew_pool set ldate="+(g.Oracle()?"to_number(to_char(gueltig,'YYYYMMDD')":"year(gueltig)*10000+month(gueltig)*100+day(gueltig)")+" where gueltig is not null and ldate is null");
              if (iAC > 0)
                g.diskInfo(iAC + " LDATE richtiggestellt");
            }
            if (bCalcClean)
            {
//            	g.fixInfoS("CalcClean-Reinigung");
                
            	long lClock2 = Static.get_Mikro_s();
            	Vector<Integer> VecM=!bMM ? Static.AicToVec(g.getMandant()):SQL.getVector("select aic_mandant from mandant where versuche&16>0",g);
            	int iMOld=bMM?g.getMandant():0;
            	Vector<Integer> VecCC=SQL.getVector("select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell")+" and "+g.bit("bits",Global.cstClean), g);
            	g.fixtestInfo("CalcClean: VecM="+VecM+", VecCC="+VecCC);
            	if (VecCC!=null && VecCC.size()>0)
            	  for (int i=0;i<VecM.size();i++)
            	  {
            		int iM=Sort.geti(VecM, i);
            		if (iM>0)
            		{
            			g.setMandant(iM);
            			for (int i2=0;i2<VecCC.size();i2++)
                          new Calc(null, g, g.BegriffToModell(Sort.geti(VecCC,i2)), false, Static.AicToVec(0),false, AU_AServer2012.iMaxBefehle, null, 0, null, null,null,null,0);
            		}
            	  }
            	if (iMOld>0) g.setMandant(iMOld);
            	Transact.fixInfoS("Reinigung mit Modell: " + Static.Mikro(lClock2));
            }
//            g.fixInfoS("Bild-Reinigung");           
            if (bBild)
            	Reinigung.BildCheck(g, false);
            	//g.diskInfo("Bild-Reinigung:"+Reinigung.BildCheck(g, false));
//            g.fixInfoS("Frist-Reinigung");           
            if (bFrist)
            	Clean_Frist(g);
            /*DateWOD dt = new DateWOD(SQL.getNow(g));
            dt.setTimeZero();
            for (int i = 0; i < Static.iDel; i++)
              dt.yesterday();*/
            //g.fixInfoS("Bereinige bis " + dt);
            if ((Static.get_ms()-lMomTime) > 60000)
              Logcheck(g);
            long lClock3=bNE ? Static.get_ms():0;
            if (bNE)
            {
	            Transact.fixInfoS("NichtExistente-Reinigung");            
	            Reinigung.NichtExistenteLoeschen(g,null,180);
	            lClock3=Static.get_ms()-lClock3;
            }
            if (lClock3>60000)
            {
            	Transact.fixInfoS("Nicht existente Bew-Daten entfernt: "+ (lClock3/1000)+" s");
            	Logcheck(g);
            }
            else
            {
            	Transact.fixInfoS("DelDeleted-Reinigung"); 
            	Aufruf.DelDeleted(g, null, false);
            }
            //g.Logcheck();
            if (Static.iDel2 > 0)
            {
            	Global.fixInfoS("Del2-Reinigung"); 
              DateWOD dt2 = new DateWOD(SQL.getNow(g));
              dt2.setTimeZero();
              for (int i = 0; i < Static.iDel2; i++)
                dt2.prevYear();
              String sDate=g.DateTimeToString(dt2.toTimestamp());
              int iAic = SQL.getInteger(g, "select max(aic_protokoll) from protokoll where timestamp<" + sDate);
              Global.fixInfoS("entferne gelöschtes bis " + dt2 + " (Prot=" + iAic + ")");
              // aus Zwischenspeicher löschen
              iAC=g.exec("delete from zwischenspeicher where pro_aic_protokoll>=100 and pro_aic_protokoll<=" + iAic);
              if (iAC>0)
                g.diskInfo(iAC + " zwischenspeicher richtig gelöscht");
              iAC=g.exec("delete from zwischenspeicher where gueltig<" + sDate);
              if (iAC>0)
                g.diskInfo(iAC + " aus zwischenspeicher gelöscht");
              // aus Fehler löschen
              iAC=g.exec("delete from fehler where timestamp<"+sDate);
              if (iAC>0)
                g.diskInfo(iAC + " aus Fehler gelöscht");
              Vector<Integer> VecBew1=SQL.getVector("select aic_bewegungstyp from bewegungstyp where"+g.bit("bewbits",Global.cstDel),g);
              Vector<Integer> VecBew2=SQL.getVector("select aic_bewegungstyp from bewegungstyp where"+g.bit("bewbits",Global.cstNOA),g);
              if (VecBew1.size()>0 || VecBew2.size()>0)
              {
                int iProt=g.Protokoll("Entfernen",0);
                if (VecBew1.size()>0)
                {
                  iAC=g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where"+g.in("aic_bewegungstyp",VecBew1)+" and pro_aic_protokoll is null and aic_protokoll<="+ iAic);
                  if (iAC>0) g.diskInfo(iAC + " aus bew_pool gelöscht markiert da zu entfernen");
                }
                if (VecBew2.size()>0)
                {
                  iAC=g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where"+g.in("aic_bewegungstyp",VecBew2)+" and pro_aic_protokoll is null and anr is null and aic_protokoll<="+ iAic);
                  if (iAC>0) g.diskInfo(iAC + " aus bew_pool gelöscht markiert da ohne ANR");
                }
                if (bTC)
                {
                  Vector<Integer> VecBew=SQL.getVector("select aic_bewegungstyp from bewegungstyp where"+g.bit("bewbits",Global.cstTimer),g);
                  int iTimerAic = SQL.getInteger(g, "select aic_code from code where kennung='Timer'");
                  Global.fixInfoS("Timer-Reinigung: iTimerAic="+iTimerAic+", VecBew="+VecBew);
                  Vector<Integer> Vec=SQL.getVector(g.top("aic_bew_pool from bew_pool p join protokoll p2 on p.aic_protokoll=p2.aic_protokoll and" + g.in("aic_bewegungstyp", VecBew) +
                               " where p2.aic_code=" + iTimerAic + " and  p2.aic_protokoll<=" + iAic + " and pro_aic_protokoll is null order by aic_bew_pool",30000),g);
                  if (Vec.size()>0)
                  {
                    Global.fixInfoS("setzte "+Vec.size()+" Timer-bew_pool als gelöscht markiert");
                    iAC = SQL.update(g,"bew_pool","pro_aic_protokoll="+iProt,Vec,"bew_pool",5000,null);
                    if (iAC > 0)g.diskInfo(iAC + " aus Timer-bew_pool gelöscht markiert");
                  }
                }
              }
              Aufruf.DelDeleted(g, dt2, true);
              Logcheck(g);
              if (bLog)
                Clean_Logging(dt2,g);
              Clean_Benutzer(g);
            }
//            g.fixInfoS("Fremd-Reinigung"); 
            if (bFremd)
              Reinigung.FremdtabellenLoeschen(g, null,null);
//            g.fixInfoS("Firma-Reinigung"); 
            if (bFirma)
              Version.FirmaRebuild(g,0);
            if (bProt)
            {
              DateWOD dt = new DateWOD(SQL.getNow(g));
              dt.setTimeZero();
              for (int i = 0; i < Static.iDelSK; i++)
                dt.yesterday();
              Reinigung.ProtokollCheck(g, dt, 20000, null, null);
            }
            lCleanLast=Static.get_ms();
            //Transact.iTimeOut=iTO;
            bClean = false;
            g.SaveVerlaufFertig(iVerlauf,Static.get_ms()-lClockV,false);
            Transact.fixInfoS("Reinigungsdauer: " + Static.Mikro(lClock));
            ProtSave(g,null,"Reinigung",lGesClock);
          }
        }
  }

  private void WatchDog()
  {
    //g.fixInfoS("WatchDog gestartet");
    long lClock = Static.get_Mikro_s();
    long lMomMem=Static.Mem();
    if (lMaxMem>0 && lMomMem > lMaxMem)
    {
      Transact.fixInfoS("Speicherverbrauch zu hoch");
      close(g,null,true,TabCalc,1);
    }
    if (bSQL_Aktiv)
    {
      long lMomTime = Static.get_ms();
      //if (lInitLast == 0)
      //  lInitLast=lMomTime;
      //g.fixInfoS(lImportDif > 0 ? "Importtimer in "+(lImportLast+lImportDif-lMomTime)/1000+" s:"+AS_Thread.bSC:" kein Importtimeraufruf");
      //g.fixInfoS(lCleanDif > 0 ? "Reinigung in "+(lCleanLast+lCleanDif-lMomTime)/1000+" s:"+bClean:" kein Reinigungsaufruf");
      //g.fixInfoS("Abschaltung in "+(lInitLast + lInit-lMomTime)+" ms, bClean="+bClean+", SC="+AS_Thread.bSC);
      if (!bClean && !AS_Thread.bSC)
      {
        if(lImportDif > 0 && lMomTime > lImportLast + lImportDif)
        {
          lImportLast = lMomTime;
          Logcheck(g);
          AS_Thread.Import(g, TabCalc, -1,null);
          if (bAB && iBackup!=Sort.geti(new DateWOD().Format("yyyyMMdd")) && SQL.getInteger(g, "select count(*) from logging where aus is null and aic_code is null")<= iMaxLog)
        	  Backup(false);
        }
        else if (lInit > 0 && lMomTime > lInitLast + lInit && Eingeloggt(g) == 0)
        {
          bSQL_Aktiv = false;
          //g.fixInfoS("Zwischenstart gestartet");
          lInitLast = lMomTime;
          timer.stop();
          //g.fixInfoS("vor closeTabCalc");
          closeTabCalc(TabCalc);
          //g.fixInfoS("vor Logout");
          String sJDBC=g.getJDBC();
          g.Logout(false);
          bBackup=false;
          if (TabSI!=null)
        	  TabSI.clearAll();
          //g.fixInfoS("nach Logout");
          if (bAB)
            Backup(false);
          g=null;
          //Transact.fixInfoS("vor sleep");
          Static.sleep(5000);
          //Transact.fixInfoS("nach sleep");
          g = new Global(sJDBC,"AServer",sDbUser,sDbPW,1);
          g.bCC=bCC;
          //g.fixInfoS("vor checkSpracheLand");
          if (sMandant != null)
            g.setMandantT(sMandant);
          g.checkSpracheLand();
          //g.fixInfoS("vor Login");
          g.Login();
          ShowAbfrage.refreshBerechtigung(g);
          //g.fixInfoS("Zwischenstart");
          bSQL_Aktiv = true;
          timer.start();
        }
        else if(lCleanDif > 0 && !bNoClean)
          Reinigung(g);
        else
          Logcheck(g);
      }
      else
        Logcheck(g);
    }
    Transact.fixInfoS("WatchDog um "+Save.now()+" | "+Static.Mikro(lClock)+" | "+Static.Mem(true));
  }

  private void startSS(int iPort) throws IOException
    {
      ServerSocket server = new ServerSocket(iPort);
      Transact.fixInfoS("ServerSocket gestartet mit Port "+iPort);
      if (bAB && !bNB)
        Backup(false);
      while (true)
      {
          //Socket socket = null;
          try
          {
            Socket socket=server.accept();
            //long lClock = Static.get_µs();
            //handleConnection(socket);
            Thread handle =new AS_Thread(g,socket,TabCalc,TabSI);
            handle.start();
            //g.fixInfoS("THREAD:" + (Static.get_µs() - lClock));
          }
          catch (IOException e)
          {
            Static.printError("ServerSocket-Verbindungsfehler:");
            Transact.printStackTraceS(e);
          }
      }
    }

  private static boolean ParameterCheck(String[] args)
  {
    if (args.length<3)
    {
      System.out.println("java -jar AU_AServer.jar Connect Port Fehlerverzeichnis [Options]");
      System.out.println("Connect: z.B.: .my:127.0.0.1/All");
      System.out.println("         /M=xx ... nur Mandant xx");
      System.out.println("         /wd60 ... 60 s Abstand bei WatchDog");
      System.out.println("         /i23  ... Init (Neu-Log) alle 23 Stunden wenn alle ausgeloggt");
      System.out.println("         /off  ... keine Konsolen-Ausgabe");
      System.out.println("         /Err  ... Fehler in Tabelle protokollieren");
      System.out.println("         /FP   ... genaue Protollierung in weiterer log-Datei"); 
      System.out.println("         /CID  ... protokolliert auch Connection-ID");
      System.out.println("         /FA   ... bei genauer Protokollierung auch Aufruf von Client");
      System.out.println("         /SII  ... Stempelimport-Pos-Info");
      System.out.println("         /save ... Konsole speichern");
      System.out.println("         /noMac... Mac-Adresse nicht verwenden");
      System.out.println("         noCC  ... kein CheckConnects");
      System.out.println("         /x    ... Exit bei Disconnect");
      System.out.println("         /RC   ... reconnect (wiederverbinden nach Trennung)");
      System.out.println("         /fast ... reconnect nach 2s");
      System.out.println("         /lc30 ... Ausloggen von anderen nach 30 min");
      System.out.println("         /Use60... maximal 60 MB");
      System.out.println("         /MB5  ... maximal 5x Bereich setzen");
      System.out.println("         /QU=xx... User xx wird für Datenbankverbindung");
      System.out.println("         /QP=yy... Passwort yy statt sql verwenden");
      System.out.println("  Clean:");
      System.out.println("         /noClean.. keine Reinigung");
      System.out.println("         /cd12  ... automatisches Reinigen alle 12 Stunden");
      System.out.println("         /CA2   ... entfernt 2-Jahre altes entferntes");
      System.out.println("         /ML3   ... Reinigung wenn maximal 3 eingeloggt");
      System.out.println("         /del60  ... entfernte Daten nach 60 Tagen richtig entfernen");
      System.out.println("         /delSK2 ... entferntes mit sehr kurzer Behaltedauer nach 2 Tagen");
      System.out.println("         /delK14 ... entferntes mit kurzer Behaltedauer nach 14 Tagen");
      System.out.println("         /delL730... entferntes mit langer Behaltedauer nach 730 Tagen");
      System.out.println("         /noNull ... Null-Reinigung nicht durchführen");
      System.out.println("         /noLDate... LDATE nicht richtigstellen");
      System.out.println("         /noFremd... keine Fremdtabellenreinigung");
      System.out.println("         /noProt ... Protokolle nicht bereinigen");
      System.out.println("         /noTC   ... keine Timer-Reinigung");
      System.out.println("         /noLog  ... keine Logging-Reinigung");
      System.out.println("         /noCC   ... keine Reinigungsmodelle");
      System.out.println("         /noBild ... keine Bild-Reinigung");
      System.out.println("         /noIndex... keine Index-Reinigung");
      System.out.println("         /noFrist... keine Frist-Reinigung");
      System.out.println("         /noNE   ... keine Nicht-Existent-Reinigung");
      System.out.println("         /maxDel ... Anzahl auf einmal löschen");
      System.out.println("         /Firma  ... Firmen prüfen");
      System.out.println("         /later  ... Reinigung später laut /cd");
      System.out.println("  Import:");
      System.out.println("         /I=       ... gibt Stempelimport-Modell an");
      System.out.println("         /im30     ... automatischer Import alle 30 Minuten");
      System.out.println("         /ohneProt ... Terminal-Zutritte löschen");
      System.out.println("         /max9000  ... maximal 9000 Befehle pro Modell sonst 200000");
      System.out.println("         /MM       ... über markierte Mandanten (mit Schleife)");
      System.out.println("  Backup:");
      System.out.println("         /MyProg=... gibt mysql/bin-Verzeichnis an");
      System.out.println("         /noProg ... schaltet MySQL-Verzeichnis Ermittlung ab");
      System.out.println("         -hxx  ... Host für MySQL-Datenbank ist xx");
      System.out.println("         -bpxx ... Port zum sichern für MySQL-Datenbank ist xx");
      System.out.println("         -pxx  ... root-Passwort für MySQL-Datenbank ist xx");
      System.out.println("         /gzip ... Backup wird anschließend mit gzip komprimiert");
      System.out.println("         /AB   ... Automatisches Backup bei Init");
      System.out.println("         /AU   ... Automatisches Update beim starten");
      System.out.println("         /BW   ... Backup mit Wochentag (d.h. max 7)");
      System.out.println("         /BM   ... Backup mit Tag des Monats (d.h. max 31)");
//      System.out.println("");
//      System.out.println("File.separator="+File.separator);
//      Static.bMySQL=true;
//      System.out.println("Hochkomma-My="+Static.forSQL(""+'"'));
//      Static.bMySQL=false;
//      System.out.println("Hochkomma-MS="+Static.forSQL(""+'"'));
      Static.sleep(10000);
      return false;
    }
    else
    {
      Static.DirError = args[2];
      Static.bX11=false;
      if (args.length>3)
        for(int i=3;i<args.length;i++)
          if(args[i].equals("/off"))
            Transact.bAusgabe=false;
          else if(args[i].equals("/Err"))
            Static.bProtFehler=true;
          else if(args[i].equals("/FP"))
            bFP=true;
          else if(args[i].equals("/FA"))
            bFA=true;
          else if(args[i].equals("/CID"))
            bCID=true;
          else if(args[i].equals("/SII"))
              bSII=true;
          else if(args[i].equals("/save"))
            Static.bSave = true;
          else if(args[i].equals("/debug"))
            bDebug = true;
          else if(args[i].equals("/ohneProt"))
            Static.bOhneProt = true;
          else if(args[i].equals("/noMac"))
        	  Static.cMacArt = '-';
          else if(args[i].equals("noCC"))
            Transact.bCheckConnect = false;
          else if(args[i].startsWith("/AA")) // automatisches Ausloggen [h]
            Transact.iLogOut=new Integer(args[i].substring(3)).intValue();
          else if(args[i].startsWith("/MA") && args[i].length()>3)
              Static.cMacArt = args[i].charAt(3);
          else if(args[i].equals("/noV"))
            Static.bVerlauf = false;
          else if(args[i].equals("/Test"))
            Transact.bTest=true;
          else if(args[i].equals("/noTest"))
            bTestinfo=false;
          else if(args[i].startsWith("/M="))
            sMandant=args[i].substring(3);
          else if(args[i].equals("/noClean"))
            bNoClean=true;
          else if(args[i].equals("/noNull"))
            bNull=false;
          else if(args[i].equals("/noLDate"))
            bLDate=false;
          else if(args[i].equals("/noFremd"))
            bFremd=false;
          else if(args[i].equals("/noCC"))
            bCalcClean=false;
          else if(args[i].equals("/noBild"))
            bBild=false;
          else if(args[i].equals("/noIndex"))
            bIndex=false;
          else if(args[i].equals("/noFrist"))
            bFrist=false;
          else if(args[i].equals("/noNE"))
            bNE=false;
          else if(args[i].startsWith("/maxDel"))
        	Static.iMaxDel=Sort.geti(args[i].substring(7));
          else if(args[i].equals("/Firma"))
            bFirma=true;
          else if(args[i].equals("/later"))
        	lCleanLast= Static.get_ms();
          else if(args[i].equals("/noProt"))
            bProt=false;
          else if(args[i].equals("/noTC"))
            bTC=false;
          else if(args[i].equals("/noLog"))
            bLog=false;
          else if(args[i].equals("/x"))
          {
            //bExit = true;
            Transact.bRealExit=true;
          }
          else if(args[i].equals("/RC"))
            Transact.bRetry=true;
          else if(args[i].equals("/fast"))
          {
            Transact.bRetry= true;
            Transact.bFast = true;
          }
          else if(args[i].startsWith("/to")) // Timeout für Befehle zwischendurch
            Transact.iTimeOut=new Integer(args[i].substring(3)).intValue();
          else if(args[i].startsWith("/tc")) // Connection-Timeout in s
            Transact.iTimeOutC=new Integer(args[i].substring(3)).intValue();
          else if(args[i].startsWith("/ts")) // Socket-Timeout in min beim Verbinden
            Transact.iTimeOutS=new Integer(args[i].substring(3)).intValue();
          else if(args[i].startsWith("/wd"))
            iWD=new Integer(args[i].substring(3)).intValue();
          else if(args[i].startsWith("/lc"))
            iLC=new Integer(args[i].substring(3)).intValue()*60;
          else if(args[i].startsWith("/MB"))
            Transact.iMaxB=new Integer(args[i].substring(3)).intValue();
          else if(args[i].startsWith("/ML"))
            iMaxLog=new Integer(args[i].substring(3)).intValue();
          else if(args[i].startsWith("/QryAb"))
            Transact.lQryAb=new Integer(args[i].substring(6)).intValue();
          else if(args[i].startsWith("/AbfAb"))
            Static.lAbfAb=new Long(args[i].substring(6)).longValue();
          else if(args[i].startsWith("/I="))
            Static.sImportModell=args[i].substring(3);
          else if(args[i].startsWith("/MyProg="))
            sDirProg=args[i].substring(8);
          else if(args[i].equals("/noProg"))
        	bProgDir=false;
          else if(args[i].startsWith("-u"))
              sUser=args[i].substring(2);
          else if(args[i].startsWith("-p"))
            sPW=args[i].substring(2);
          else if(args[i].startsWith("-h"))
              sHost=args[i].substring(2);
          else if(args[i].startsWith("-bp"))
              sBP=args[i].substring(3);
          else if(args[i].equals("/gzip"))
            bGzip=true;
          else if(args[i].equals("/AB"))
            bAB=true;
          else if(args[i].equals("/NB"))
            bNB=true;
          //else if(args[i].equals("/AU"))
          //  bAU=true;
          else if(args[i].equals("/BW"))
            iBI=1;
          else if(args[i].equals("/BM"))
            iBI=2;
          else if(args[i].startsWith("/Use"))
            lMaxMem=new Long(args[i].substring(4)).longValue()*1024*1024;
          else if(args[i].startsWith("/max"))
            iMaxBefehle=new Integer(args[i].substring(4)).intValue();
          else if(args[i].startsWith("/delSK"))
            Static.iDelSK=new Integer(args[i].substring(6)).intValue();
          else if(args[i].startsWith("/delK"))
            Static.iDelK=new Integer(args[i].substring(5)).intValue();
          else if(args[i].startsWith("/delL"))
            Static.iDelL=new Integer(args[i].substring(5)).intValue();
          else if(args[i].startsWith("/del"))
            Static.iDel=new Integer(args[i].substring(4)).intValue();
          else if(args[i].startsWith("/CA"))
            Static.iDel2=new Integer(args[i].substring(3)).intValue();
          else if(args[i].startsWith("/cd"))
            lCleanDif=new Long(args[i].substring(3)).intValue()*3600000;
          else if(args[i].startsWith("/im"))
            lImportDif=new Long(args[i].substring(3)).intValue()*60000;
          else if(args[i].equals("/MM"))
            bMM=true;
          else if(args[i].startsWith("/i"))
          {
            lInit = new Long(args[i].substring(2)).longValue() * 3600000;
            if (lInit<0)
              lInit/=-60;
            Transact.fixInfoS("lInit="+(lInit/1000)+" s");
          }
          else if(args[i].startsWith("/QU="))
            sDbUser=args[i].substring(4);
          else if(args[i].startsWith("/QP="))
            sDbPW=args[i].substring(4);
          else if(args[i].equals("/CC"))
            bCC = true;
          else if(args[i].equals("/beep"))
        	Static.bBeep=true;
      if (Static.iDelL<Static.iDel)
        Static.iDelL=Static.iDel;
      Transact.fixInfoS("Del: "+Static.iDelSK+"/"+Static.iDelK+"/"+Static.iDel+"/"+Static.iDelL);
      return true;
    }

  }

  private static void RunOpen()
  {
    sRun=Static.DirError+"AServer.run";
    if (new File(sRun).exists())
    {
      System.out.println(sRun+" existiert bereits");
      Static.sleep(5000);
    }
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
                    Static.printError("Save.prot(): IOException - "+ioe);
                    System.out.println(sRun+" wahrscheinlich falsch");
                    Static.sleep(5000);
                    System.exit(4);
            }
    }
  }

  public static void RunClose()
  {
    try
    {
            fileRun.close();
    }
    catch(IOException ioe)
    {
            Static.printError("AU_Timer.RunClose(): IOException - "+ioe);
    }
    if (!new File(sRun).delete())
        Static.printError(sRun+" not deleted");
    Static.sleep(2000);
  }

  public static void closeTabCalc(Tabellenspeicher Tab)
  {
    if (Tab!=null)
    {
      for (int i = 0; i < Tab.size(); i++)
      {
        Global g = (Global)Tab.getInhalt("g", i);
        if (g != null && g.isConnected())
          g.unConnect();
      }
      Tab.clearAll();
    }
  }

  public static boolean isRunning(Global g,Tabellenspeicher Tab)
  {
    for (int i = 0; i < Tab.size(); i++)
      if (Tab.getI(i,"used")>0)
      {
        g.testInfo("isRunning: Tab="+Tab.size()+"->true");
        return true;
      }
    g.testInfo("isRunning: Tab="+Tab.size()+"->false");
    return false;
  }

  public static void deAktiv(Tabellenspeicher Tab,boolean bStopTimer)
  {
    if (bSQL_Aktiv)
    {
      while (isRunning(g,Tab))
        Static.sleep(1000);
      bSQL_Aktiv = false;
      if (bStopTimer)
        timer.stop();
      closeTabCalc(Tab);
      g.Logout(false);
      if (TabSI!=null)
    	  TabSI.clearAll();
    }
  }

  public static void Aktiv(boolean bStartTimer)
  {
    g = new Global(g.getJDBC(), "AServer", sDbUser, sDbPW, 1);
    g.bCC=bCC;
    if (sMandant != null)
      g.setMandantT(sMandant);
    g.checkSpracheLand();
    g.Login();
    String sDirError= Static.DirError;
    Systemeinstellung.LoadDir(g,null,-1);
    Static.DirError = sDirError;
    iBenutzer=g.getBenutzer();
    ShowAbfrage.refreshBerechtigung(g);
    bSQL_Aktiv = true;
    if (bStartTimer)
      AU_AServer2012.timer.start();
  }

  public static void close(Global g,Socket socket,boolean bSofort,Tabellenspeicher Tab,int i)
  {
	long lGesClock=Static.get_ms();
    bClose=true;
    if (timer != null)
      timer.stop();
    if (!bSofort)
      while (isRunning(g,Tab))
        Static.sleep(5000);
    if (bSQL_Aktiv || bSofort)
    {
      if (!bSofort)
        closeTabCalc(Tab);
      if (g!=null)
        g.Logout(false);
    }
    bSQL_Aktiv=false;
    try
    {
      if (socket != null)
        socket.close();
    }
    catch(IOException ioe) {}
    RunClose();
    //g.testInfo("close");
    ProtSave(g,null,"Close",lGesClock);
    System.exit(i);
  }

  public static void main(String[] args) throws IOException
  {
    if (ParameterCheck(args))
    {
      RunOpen();
      String sJDBC = args[0];
      int iPort = Integer.parseInt(args[1]);
      new AU_AServer2012(sJDBC, iPort);
      RunClose();
      System.exit(5);
    }
    System.exit(6);
  }
}

class AS_Thread extends Thread
{
  private Socket socket;
  private Global g;
  private Tabellenspeicher TabCalc;
  private static Tabellenspeicher TabSI=null;
  private static int iAnz=0;
  private int iMom=0;

  public static boolean bSC=false; // Stempelberechnung läuft
  private static boolean bSCW=false;// Stempelberechnungswunsch
  public static boolean bCalc=false; // Berechnung mit c: (call AServer-Modell-Befehl)
  private static int iIM=0;
  private static Tabellenspeicher TabBT=null;
  private static Tabellenspeicher TabC=null;
  private static int iAnzC=0;
  private static int iAnzB=0;

  private String readString(ObjectInputStream in)
{
  try
  {
    return (String)in.readObject();
  }
  catch(Exception e)
  {
      Static.printError("readString:"+e);
      return "???";
  }
}

/*private Vector readVec(ObjectInputStream in) throws IOException
{
  try
  {
    return (Vector)in.readObject();
  }
  catch(Exception e)
  {
      Static.printError("readVec:"+e);
      return null;
  }
}*/

  public AS_Thread(Global rg,Socket rsocket,Tabellenspeicher Tab,Tabellenspeicher Tab2)
  {
    socket=rsocket;
    g=rg;
    TabCalc=Tab;
    if (TabSI==null || Tab2==null)
    	TabSI=Tab2;
    iMom=iAnz++;
  }

  private static int getPos(int iModell,int iMom,Tabellenspeicher TabCalc)
  {
	if (AU_AServer2012.bSII) Global.fixInfoS("****** getPos von Modell="+iModell+"/, Mom="+iMom);
    for(int i=0;i<TabCalc.size();i++)
    {
      if (AU_AServer2012.bSII) Global.fixInfoS(" * Pos"+i+": M="+TabCalc.getI(i,"model")+" u="+TabCalc.getI(i,"used")+", c="+TabCalc.getInhalt("calc",i));
      if ((TabCalc.getI(i,"model")==iModell || TabCalc.getInhalt("calc",i)==null) && TabCalc.getI(i,"used")==0)
      {
        TabCalc.setInhalt(i,"used",iMom);
        if (AU_AServer2012.bSII) Global.fixInfoS(" -> nehme "+i+" -> setze used auf "+iMom);
        return i;
      }
    }
    return -1;
  }
  
  private static boolean addSI(String sArt)
  {
	  boolean b=sArt!=null;
	  if (b)
      {
    	  int iPos=TabSI.newLine();
    	  //{"Mandant","Art","erfasst","Beginn","Ende"}
    	  int iM=Integer.parseInt(sArt.substring(0, 3));
    	  sArt=sArt.substring(3);
    	  TabSI.setInhalt(iPos,"Mandant",iM);
    	  TabSI.setInhalt(iPos,"Art",sArt);
    	  TabSI.setInhalt(iPos,"erfasst",new Date());
      }
	  return b;
  }
  
  private static int getNextMSI(int iAb)
  {
	  for (int i=iAb;i<TabSI.size();i++)
		  if (TabSI.isNull(i,"Beginn") && !TabSI.isNull(i,"erfasst"))
		  {
			  TabSI.setInhalt(i,"Beginn",new Date());
			  return i;
		  }
	  return -1;
  }

  public static void Import(Global g,Tabellenspeicher TabCalc,int iMom,String sArt)
  {
	  long lGesClock=Static.get_ms();
    bSCW=true;
        if (Static.sImportModell==null)
          Transact.fixInfoS("kein Stempelimport-Modell definiert!");
        else if (bSC)
        {
          addSI(sArt);
          Transact.fixInfoS("Stempelimport arbeitet bereits!");
        }
        else if (AU_AServer2012.bClose)
          Transact.fixInfoS("Stempelimport wegen Schließung nicht mehr möglich!");
        else
        {
          bSC=true;
          boolean bMSI=addSI(sArt);
          Vector<Integer> VecM=bMSI || !AU_AServer2012.bMM ? null:SQL.getVector("select aic_mandant from mandant where versuche&16>0 and tod is null",g);
          g.fixtestInfo("VecM="+VecM);
          int iMM=0;
          long lClockV = Static.get_ms();
          int iVerlauf=g.SaveVerlauf(g.getBegriffAicS("Modell", Static.sImportModell),0,0);
          int iPosMSI=-1;          
          while (bSCW)// && (VecM==null || iMM<VecM.size()))
          {
            if (iIM == 0)
            {
              int iAic = g.getBegriffAicS("Modell", Static.sImportModell);
              int iBewT= SQL.getInteger(g,"select aic_bewegungstyp from bewegungstyp where kennung='Timer Reisekosten'");
              TabBT=new Tabellenspeicher(g,"select p.aic_bew_pool,p.aic_mandant from bew_begriff b join bewview2 p on b.aic_bew_pool=p.aic_bew_pool where aic_bewegungstyp="+iBewT+
            		  " and aic_begriff="+iAic,true);//+" and aic_mandant in (8,1)
              iIM = g.BegriffToModell(iAic);
              g.fixtestError("iIM=" + iIM + " bei Aic="+iAic+", Bew="+iBewT);
            }
            if (bMSI)
            {
            	iPosMSI=getNextMSI(iPosMSI+1);
            	if (iPosMSI<0)
            		break;
            }
            int iPos = getPos(iIM, iMom,TabCalc);
            boolean bNeu = iPos < 0;
            //g.fixtestError("import mit "+sArt+": Pos="+iPos+", Neu="+bNeu);
            Global g2 = bNeu ? new Global(g,false) : (Global)TabCalc.getInhalt("g", iPos);
            if (g2.Disconnected())
    			g2.connect(null);
            g2.setDebug(AU_AServer2012.bDebug);
            if (iPosMSI>=0)
        		g2.setMandant(TabSI.getI(iPosMSI,"Mandant"));
            if (VecM != null) g2.setMandant(Sort.geti(VecM,iMM));
            g2.setBenutzer(AU_AServer2012.iBenutzer);
            g2.setZA(0,"Tag");
            g2.setAktDate(true);
            Transact.fixInfoS("Berechne "+g2.getModellBez(iIM)+", Zeitraum=" + g2.getVonBis2() + ", Mandant=" + g2.getMandant());
            if (bNeu)
            {
              iPos = TabCalc.newLine();
              if (AU_AServer2012.bSII) Transact.fixInfoS("Neue Verbindung:"+(iPos+1));
              TabCalc.setInhalt(iPos, "g", g2);
            }
            if (VecM!=null)
              iMM++;
            if (iPosMSI<0 && (VecM==null || iMM==VecM.size()))
              bSCW = false;
            long lClockSub=Static.get_ms();
            if (TabCalc.getInhalt("calc", iPos) == null)
            {
              TabCalc.setInhalt(iPos, "used", iMom);
              TabCalc.setInhalt(iPos, "model", iIM);
              if (AU_AServer2012.bSII) Global.fixInfoS(" -> beginne Stempelimport mit Pos="+iPos+" -> setze used auf "+iMom);
              TabCalc.setInhalt(iPos, "calc", new Calc(null, g2, iIM, false, Static.AicToVec(0), false,AU_AServer2012.iMaxBefehle, null, 0, null, null,null,null,0));
            }
            else
            {
              if (AU_AServer2012.bSII) Global.fixInfoS(" -> reCalc Stempelimport");
              ((Calc)TabCalc.getInhalt("calc", iPos)).reCalc(Static.AicToVec(0), true, 0, null, null,0,-1);
            }
            TabCalc.setInhalt(iPos, "used", null);
            if (AU_AServer2012.bSII) Global.fixInfoS(" <- beende Stempelimport mit Pos="+iPos+" -> setze used auf "+TabCalc.getI(iPos,"used"));            
            AU_AServer2012.ProtSave(g2,TabCalc,"Einzel-Import",lClockSub);
            if (iPosMSI>=0)
            	TabSI.setInhalt(iPosMSI,"Ende",new Date());
            if (TabBT!=null && !TabBT.isEmpty())
            {
            	if (VecM==null)
            		TabBT.moveFirst();
            	else
            		TabBT.posInhalt("aic_mandant", g2.getMandant());
            	if (TabBT.out())
            		Static.printError("Mandant "+g2.getMandant()+" in TabBT nicht gefunden!");
            	else
            	{
            		Transact.fixInfoS("setze Datum bei "+TabBT.getI("aic_bew_pool")+" auf "+SQL.getNow(g2));
            		g2.exec("update bew_pool set gueltig="+g2.now()+",ldate="+Static.DateToInt(new Date())+" where aic_bew_pool="+TabBT.getI("aic_bew_pool"));
            	}
            }
            if (bSCW && VecM!=null && iMM==VecM.size()) iMM=0;
            if (bSCW && VecM==null)
              Static.sleep(2000);
            else if (!bSCW)
              Transact.fixInfoS("Stempelimport erledigt");
          }
          g.SaveVerlaufFertig(iVerlauf,Static.get_ms()-lClockV,false);
          bSC=false;
          if (sArt==null)
        	  AU_AServer2012.ProtSave(g,TabCalc,"Import",lGesClock);
        }

  }

  public void run() {
	  long lGesClock=Static.get_ms();
    long lClock = Static.get_Mikro_s();
    String sArt="???";
    String sTitel="Hugo";
    String sOk="";
    //String sZusatz="";
    ObjectInputStream in=null;
    int iStamm=0;
    boolean bBerechne=false;
    try {
      in = new ObjectInputStream(socket.getInputStream());
      sArt = readString(in);
      sTitel=sArt.indexOf(";")<0 ? sArt:sArt.substring(0,sArt.indexOf(";"));
      if (Transact.bTest || !sArt.startsWith("i:") && !sArt.startsWith("status"))
        Transact.fixInfoS("\n"+iMom+":"+sTitel+" von "+socket.getInetAddress().getHostName());
      if (!sArt.startsWith("i:") && !sArt.startsWith("status") && AU_AServer2012.bFA)
      	AU_AServer2012.ProtSave(g,TabCalc,sTitel+" <-",lGesClock,iStamm);
      boolean bAktiv=AU_AServer2012.bSQL_Aktiv;
      if (AU_AServer2012.bClose && !sArt.equals("quit"))
      {
        Transact.fixInfoS(sTitel+" wegen Schließung nicht mehr durchführbar");
      }
      else if (sArt.equals("sql") && bAktiv)
      {
        Transact.fixInfoS("SQL-Befehl wird ausgeführt:");
        String sSQL = readString(in);
        Transact.fixInfoS(sSQL);
        //ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
        new ObjectOutputStream(socket.getOutputStream()).writeObject(new Tabellenspeicher(g, sSQL, true));
        //out.println(new Tabellenspeicher(g,sSQL,true));
      }
      else if (sArt.equals("debug") || sArt.equals("noDebug"))
        g.setDebug(sArt.equals("debug"));
      else if (sArt.startsWith("d:")) // druck & e-mail
      {
        //String sDA=sArt.substring(2,sArt.indexOf(";"));
        //String s=sArt.substring(sArt.indexOf(";")+1);
        //String sDirError= Static.DirError;
        //Systemeinstellung.LoadDir(g,null);
        //Static.DirError = sDirError;
        //g.fixInfoS("s="+sArt);
        String[] sSp=sArt.substring(2).split(";");
        String sDA=sSp[0];
        String sZA=sSp[1];
        String sVon=sSp[2];
        String sBis=sSp[3];
        int iM=Sort.geti(sSp[4]);
        iStamm=Sort.geti(sSp[5]);
        String sDruck=sSp[6];
        String sFile=sSp.length>7 ? sSp[7]:null;
        String sPW=sSp.length>8 ? sSp[8]:null;
        Vector Vec=null;
        if (iStamm==0)
          Vec = (Vector)in.readObject();
        else
          Vec = Static.AicToVec(iStamm);
        //String sBez=g.getStamm(iStamm);
        //g.fixInfoS("Art="+sDA+" Stamm="+sBez);
        int iDruck=SQL.getInteger(g,"select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+" and kennung='"+sDruck+"'");
        if (iDruck>0)
        {
          Global g2 = new Global(g,false);
          g2.setZA(0,sZA);
          g2.setVonBis(java.sql.Timestamp.valueOf(sVon + ".000000000"), java.sql.Timestamp.valueOf(sBis + ".000000000"));
          Transact.fixInfoS(sDA+" von "+sDruck+", ZR="+g2.getVonBis("dd.MM.yyyy",true));
          //g2.fixInfoS("Zeitraum="+g2.sZeitart+": "+g2.getVonBis("dd.MM.yyyy",true));
          g2.setMandant(iM);
          Tabellenspeicher TabStamm=new Tabellenspeicher(g2,new String[] {"Aic","Bezeichnung"});
          for (int i=0;i<Vec.size();i++)
          {
            iStamm=Sort.geti(Vec,i);
            TabStamm.addInhalt("Aic", iStamm);
            TabStamm.addInhalt("Bezeichnung", g.getStamm(iStamm));
          }
          DruckAufrufA daa = new DruckAufrufA(g2, 0);
          int iBits = (sDA.equals("pdf") ? Drucken.cstPDF : sDA.equals("e-mail") ? Drucken.cstEMail : sDA.equals("pdf-db") ? Drucken.cstPDF_DB : 0)+Drucken.cstSeitenumbruch+Drucken.cstDFFarbe;
          daa.druckeDruck(iDruck, TabStamm, sFile, sPW, null, iBits, 0, null, 0,0);
          g2.fixtestInfo(sDA+" von "+sDruck+" aufgerufen");
        }
        else
          Static.printError("Druck "+sDruck+" nicht gefunden");
      }
      else if ((sArt.equals("calc") || sArt.startsWith("c:") || sArt.startsWith("e:")) && bAktiv)
      {
        //new Thread(new Runnable()
        //{
        //public void run()
        //{
        final boolean b=sArt.equals("calc");
        final String sP=b ? null:sArt.substring(2);
        
          //long lClock2 = Static.get_µs();
          int iModell = b ? Integer.parseInt(readString(in)):g.BegriffToModell(g.getBegriffAicS("Modell", sP.substring(0,sP.indexOf(";"))));
          
          String sR = b ? null:sP.substring(sP.indexOf(";")+1);
          //sZusatz=" "+g.getBegBez(iB);       
          boolean bRechne=false;
          if (sArt.startsWith("c:"))
          {
        	  bRechne=!bCalc;
        	  if (bCalc)
        	  {
        		  if (TabC==null)
        			  TabC=new Tabellenspeicher(g,new String[] {"Modell","s","start","stop"});
        		  TabC.addInhalt("Modell",iModell);
        		  TabC.addInhalt("s",sR);
        		  TabC.addInhalt("start",new Date());
        		  TabC.addInhalt("stop",null);
        		  iAnzC=TabC.size();
        		  g.fixtestInfo("auf Warteschleife "+iAnzC+":"+sR);
        	  }
        	  else
        		  bCalc=true;
          }
          else
        	  bRechne=true;
          if (bRechne)
          {
        	  bBerechne=true;
        	  iStamm=berechne(b,sArt,iModell,sR,in);
	          if (sArt.startsWith("c:"))
	          {
	        	  while(iAnzC>iAnzB)
	        	  {
	        		  int i=iAnzB;
	        		  iAnzB++;
	        		  sR=TabC.getS(i,"s");
	        		  g.fixtestInfo("Berechne von Warteschleife "+iAnzB+":"+sR);
	        		  iModell=TabC.getI(i,"Modell");
	        		  berechne(b,sArt,iModell,sR,in);
	        		  TabC.setInhalt(i, "stop", new Date());
	        		  
	        	  }
	        	  bCalc=false;
	          }
          }
          //long lClock4 = Static.get_µs();
          //g.fixInfoS("Global/Calc:" + (lClock3 - lClock2)+"/"+(lClock4 - lClock3));
          //g.fixInfoS("Calc:" + (lClock4 - lClock3));
        
        //}
        //}).start();
      }
      else if (sArt.startsWith("import") && bAktiv)
        Import(g,TabCalc,iMom,sArt.length()==6 ? null:sArt.substring(6));
      else if (sArt.equals("backup"))
        AU_AServer2012.Backup(true);
      else if (sArt.equals("update"))
        AU_AServer2012.Update(TabCalc);
      else if (sArt.equals("abschluss") && bAktiv)
      {
        Transact.fixInfoS("Abschluss-Anfang: "+TabCalc.size()+" in TabCalc");
        while (AU_AServer2012.isRunning(g,TabCalc))
          Static.sleep(1000);
        AU_AServer2012.closeTabCalc(TabCalc);
        ShowAbfrage.refreshBerechtigungSmall(g);
        Transact.fixInfoS("Abschluss-Ende: "+TabCalc.size()+" in TabCalc");
      }
      else if (sArt.equals("clean") && bAktiv)
        AU_AServer2012.Reinigung(g);
      else if (sArt.equals("ver"))
        new PrintWriter(socket.getOutputStream(), true).println(AClient.getVersion(g)+(bAktiv?"":"*"));
      else if (sArt.equals("show"))
        TabCalc.showGrid("TabCalc");
      else if (sArt.equals("showSI"))
    	  TabSI.showGrid("SI");
      else if (sArt.startsWith("i:"))
      {
        String s=sArt.substring(2);
        long i=s.equals("del") ? Static.iDel:s.equals("delSK") ? Static.iDelSK:s.equals("delK") ? Static.iDelK:s.equals("delL") ? Static.iDelL:
            s.equals("CA") ? Static.iDel2:s.equals("wd") ? AU_AServer2012.iWD:s.equals("size") ? TabCalc.size():s.equals("ImportModell") ? (Static.sImportModell==null ? 0:1):
            s.equals("cd") ? (int)(AU_AServer2012.lCleanDif/1000):s.equals("im") ? (int)(AU_AServer2012.lImportDif/1000):s.equals("mem") ? Static.Mem():0;
        String sE=s.equals("backup") ? AU_AServer2012.sBackupFile:s.equals("update") ? DefImport.sZInfo!=null ? DefImport.sZInfo:Static.forExport(AU_AServer2012.sUpdate,true):""+i;
        if (Transact.bTest)
          Transact.fixInfoS(s+":"+sE);
        new PrintWriter(socket.getOutputStream(), true).println(sE);
      }
      else if (sArt.equals("deaktiv") && bAktiv)
      {
        AU_AServer2012.deAktiv(TabCalc,false);
        /*while (AU_AServer2012.isRunning(g,TabCalc))
          Static.sleep(1000);
        AU_AServer2012.bSQL_Aktiv=false;
        AU_AServer2012.closeTabCalc(TabCalc);
        g.Logout(false);*/
      }
      else if (sArt.equals("aktiv") && !bAktiv)
      {
        Transact.fixInfoS("aktiv gestartet");
        AU_AServer2012.Aktiv(false);
        /*g = new Global(g.getJDBC(),"AServer",AU_AServer2012.sDbUser,AU_AServer2012.sDbPW,1);
        AU_AServer2012.g=g;
        //g.connect(null);
        //g.fixInfoS("vor Login");
        if (AU_AServer2012.sMandant != null)
          g.setMandantT(AU_AServer2012.sMandant);
        g.checkSpracheLand();
        g.Login();
        //g.fixInfoS("nach Login");
        ShowAbfrage.refreshBerechtigung(g);
        AU_AServer2012.bSQL_Aktiv=true;*/
      }
      else if (sArt.startsWith("status"))
      {
        int iStatus= AU_AServer2012.bUpdate ? 4:AU_AServer2012.bBackup ? 1:AU_AServer2012.bClean ? 2:bSC ? 3:bAktiv ? 0:-1;
        //String s2=(bAktiv?"":" "+g.getShow("inaktiv"))+(bSC?" "+g.getShow("Stempelimport"):"")+(AU_AServer2012.bClean?" "+g.getShow("Reinigung"):"")+(AU_AServer2012.bBackup?" "+g.getShow("Backup"):"");
        //if (s2.equals("")) s2=" "+g.getShow("Leerlauf");
         //String s="Version="+AClient.getVersion(g)+s2+": "+TabCalc.size()+" Threads";
        new PrintWriter(socket.getOutputStream(), true).println(sArt.equals("status2") ? ""+iStatus:"Version="+AClient.getVersion(g)+AClient.getStatus(iStatus,g)+": "+TabCalc.size()+" Threads");
      }
      else if (sArt.equals("reset"))
      {
        // String sJDBC=g.getJDBC();
        AU_AServer2012.deAktiv(TabCalc,true);
        /*if (bAktiv)
        {
          //bAktiv=false;
          AU_AServer2012.bSQL_Aktiv = false;
          AU_AServer2012.timer.stop();
          AU_AServer2012.closeTabCalc(TabCalc);
          g.Logout(false);
        }*/
        //g=null;
        Static.sleep(1000);
        AU_AServer2012.Aktiv(true);
        /*g = new Global(sJDBC,"AServer",AU_AServer2012.sDbUser,AU_AServer2012.sDbPW,1);
        AU_AServer2012.g=g;
        if (AU_AServer2012.sMandant != null)
          g.setMandantT(AU_AServer2012.sMandant);
        g.checkSpracheLand();
        g.Login();
        ShowAbfrage.refreshBerechtigung(g);
        //bAktiv=true;
        AU_AServer2012.bSQL_Aktiv = true;
        AU_AServer2012.timer.start();*/
      }
      else if (sArt.startsWith("x") || sArt.equals("quit"))
      {
        Transact.fixInfoS("Beende");
        AU_AServer2012.close(g,socket,sArt.equals("quit"),TabCalc,7);
      }
      else if (sArt.startsWith("exec:"))
      {
        Transact.fixInfoS("ausführen:"+sArt.substring(5));
        g.openFile(sArt.substring(5));
        Transact.fixInfoS("fertig ausgeführt");
      }
      else if (sArt.startsWith("prog:"))
      {
        Transact.fixInfoS("Programm anlegen:"+sArt.substring(5)+"->"+g.getCodeAic("Programm",sArt.substring(5)));
      }
      else if (!bAktiv)
      {
        Static.printError("nicht aktiv, deshalb " + sTitel + " nicht ausführbar!");
        sOk=" -> inaktiv";
      }
      else
      {
        Static.printError("Art " + sTitel + " nicht verstanden!");
        sOk=" -> nicht verstanden";
      }
    } catch (Exception e)
    {
      Static.printError("AS_Thread:");
      Transact.printStackTraceS(e);
      sOk=" -> Error:"+e;
    }
    finally
    {
      if (socket != null)
        try
        {
          in.close();
          socket.close();
        }
        catch (IOException e)
        {Static.printError("finally:" + e);}
    }
    //String sArt2=sArt.indexOf(";")<0 ? sArt:sArt.substring(0, sArt.indexOf(";"));
    if (!bBerechne && !sArt.startsWith("i:") && !sArt.startsWith("status"))
    	AU_AServer2012.ProtSave(g,TabCalc,sTitel+sOk,lGesClock,iStamm);
    if (Transact.bTest || !sArt.startsWith("i:") && !sArt.startsWith("status"))
    {
      int iError=Static.getError();
      Transact.fixInfoS(sTitel + ": " + Static.Mikro(lClock) + " | " + Static.Mem(true)+(iError>0 ? " | "+iError:""));
      if (!sArt.startsWith("i:") && iError>0)
        Static.clearError();
    }
  }
  
  private int berechne(boolean b,String sArt,int iModell, String sR,ObjectInputStream in)
  {
	long lClockV = Static.get_ms();
	int iStamm=0;
	String sZusatz=Static.sLeer;
	int iPos=-1;
	Global g2 = null;
	try
    {
	  int iB=g.ModellToBegriff(iModell);
	  sZusatz=" "+g.getBegBez(iB);
	  iPos=getPos(iModell,iMom,TabCalc);//TabCalc.getPos("model",iModell);
      boolean bNeu= iPos<0;
      
	  g2 = bNeu ? new Global(g,false):(Global)TabCalc.getInhalt("g",iPos);
	  g2.setDebug(AU_AServer2012.bDebug);
	  if (bNeu && g2.getZA(0)==null)
		  g2.setZA(0,"Tag");
	  int iMandant = g.getMandant();
	  if (sArt.startsWith("e:"))
	  {
	    Transact.fixInfoS("Berechne extern: Modell="+iModell+", Qry="+sR);
	    Transact.fixInfoS("Mandant="+g.MandantBez(iMandant)+", ZR="+g2.getVonBis2());
	    g2.setMandant(iMandant);
	  }
	  else
	  {
	    g2.setZA(0, b ? readString(in) : sR.substring(0, sR.indexOf(";")));
	    sR = b ? null : sR.substring(sR.indexOf(";") + 1);
	    if (b)
	      g2.setVonBis((java.sql.Timestamp)in.readObject(), (java.sql.Timestamp)in.readObject());
	    else
	    {
	      String s = sR.substring(0, 38);
	      g2.setVonBis(java.sql.Timestamp.valueOf(s.substring(0, 19) + ".000000000"), java.sql.Timestamp.valueOf(s.substring(19) + ".000000000"));
	    }
	    sR = b ? null : sR.substring(sR.indexOf(";") + 1);
	    iMandant = Integer.parseInt(b ? readString(in) : sR.substring(0, sR.indexOf(";")));
	    g2.setMandant(iMandant);
	    //g2.fixInfoS("vonbis gelesen");
	    if (b)
	      g2.loadJokerStt((Tabellenspeicher)in.readObject());
	    //g2.TabStammtypen.showGrid("TabStammtypen");
	    //g2.fixInfoS("Modell gelesen:"+iModell);
	  }
	  int iAic=0;
	  int iBew=0;
	  if (!b)
		if (sArt.startsWith("e:"))
		  iAic=Sort.geti(sR);
		else if (sArt.startsWith("c:"))
		{
			sR = b ? null : sR.substring(sR.indexOf(";") + 1);
			iAic=Sort.geti(sR.substring(0, sR.indexOf(";")));
			iBew=Sort.geti(sR.substring(sR.indexOf(";")+1));
		}
		else
			iAic=Sort.geti(sR.substring(sR.indexOf(";")+1));
	  if (iBew>0)
	  {
		  g2.bISQL=true;
		  SQL Qry = new SQL(g2);
	      Qry.add("aic_bew_pool", iBew);
	      Qry.add("aic_eigenschaft", g2.iTimerSperre);
	      Qry.add("spalte_boolean", 1);
	      Qry.add("aic_logging",g2.getLog());
	      if (Qry.insert("bew_boolean", false) < 0)
	      {
	        Qry.free();
	        g2.bISQL=false;
	        AU_AServer2012.ProtSave(g2,TabCalc,sArt+sZusatz+" -> gesperrt",lClockV,iAic);
	        return iAic;
	      }	
	      g2.bISQL=false;
	  }
	  Vector Vec = b ? (Vector)in.readObject():Static.AicToVec(iAic);//Integer.parseInt(sArt.startsWith("e:") ? sR:sR.substring(sR.indexOf(";")+1))); //readVec(in);
	  int iVerlauf=g2.SaveVerlauf(g2.ModellToBegriff(iModell),Vec!=null && Vec.size()>0 ? Sort.geti(Vec,0):0,0);
	  if (Vec.size()>0)
		  iStamm=Sort.geti(Vec, 0);
	  //g2.fixInfoS("Vec gelesen:"+Vec);
	  int iAbfrage = b ? Integer.parseInt(readString(in)):0;
	  int iBitsM = b ? Integer.parseInt(readString(in)):Global.cstThread;
	  if (b)
	  {
	    g2.testInfo("vorher: Benutzer="+g2.getBenutzer()+", Sprache="+g2.getSprache()+", Land="+g2.getLand()+", Zone="+g2.getZone());
	    g2.setBenutzer(Integer.parseInt(readString(in)));
	    g2.setSprache(Integer.parseInt(readString(in)),Integer.parseInt(readString(in)));
	    int iZone=Integer.parseInt(readString(in));
	    Transact.iZoneOffset+=iZone-g2.getZone();
	    g2.testInfo("nachher:Benutzer="+g2.getBenutzer()+", Sprache="+g2.getSprache()+", Land="+g2.getLand()+", Zone="+iZone+"/"+g2.getZone());
	  }
	  //g2.fixInfoS("Abfrage gelesen:"+iAbfrage);
	  //Thread.sleep(1000);
	  //long lClock3 = Static.get_µs();
	  //if (!sArt.startsWith("e:"))
		  Transact.fixInfoS(iMom+": Modell=" + iModell + "(pos="+(iPos+1)+"), Vec=" + Vec + ", Abf=" + iAbfrage + ", M="+iMandant+" bei " + g2.getVonBis("dd.MM.yyyy",true));

	  Calc calc=null;
	  ObjectOutputStream out=/*sArt.startsWith("e:")*/!b ? null:new ObjectOutputStream(socket.getOutputStream());
	  if (bNeu)
	  {
	    iPos = TabCalc.newLine();
	    Transact.fixInfoS("Neue Verbindung:"+(iPos+1));
	    TabCalc.setInhalt(iPos,"g",g2);
	  }
	  if (TabCalc.getInhalt("calc",iPos)==null)
	  {
	    TabCalc.setInhalt(iPos,"used",iMom);
	    TabCalc.setInhalt(iPos,"model",iModell);
	    calc=new Calc(null, g2, iModell, false, Vec, false, -1, null, 0,b ? in:null,b ? out:null,null,null,0);
	    if (iAbfrage>0 || (iBitsM & Global.cstRecalc)>0)
	      TabCalc.setInhalt(iPos,"calc",calc);
	  }
	  else
	  {
	    calc=(Calc)TabCalc.getInhalt("calc",iPos);
	    calc.reCalc(Vec, true, iAbfrage,b ? in:null,b ? out:null,0,-1);
	  }
	  if (iBew>0)
		  g2.exec("delete from bew_boolean where aic_bew_pool=" + iBew + " and aic_eigenschaft=" + g2.iTimerSperre);
      
	  //Tabellenspeicher Tab = calc.getTab(iAbfrage);
	  //if ((iBitsM & Global.cstThread)==0)
	  String sFehler=null;
	  if (sArt.startsWith("e:"))
	    new PrintWriter(socket.getOutputStream(), true).println(calc.getErgebnis());
	  else if (b)//sArt.equals("calc"))
	  {
	    if ((iBitsM & Global.cstJokerStt) == 0)
	    {
	      out.writeObject("joker");
	      out.writeObject(g2.saveJokerStt(false));
	    }
	    out.writeObject("tab");
	    
	    if (iAbfrage > 0)
	      out.writeObject(calc.getTab(iAbfrage));
	    else
	    {
	      Tabellenspeicher Tab = new Tabellenspeicher(null, new String[]
	          {"Bezeichnung", "Inhalt"});
	      sFehler = calc.Fehler();
	      if (sFehler != null)
	      {
	        Tab.addInhalt("Bezeichnung", "Error");
	        Tab.addInhalt("Inhalt", sFehler);
	      }
	      if ((iBitsM & Global.cstErgebnis) > 0)
	      {
	        Tab.addInhalt("Bezeichnung", "Ergebnis");
	        Tab.addInhalt("Inhalt", calc.getErgebnis());
	      }
	      if ((iBitsM & Global.cstUseQry) > 0)
	      {
	        Tab.addInhalt("Bezeichnung", "Qry");
	        Tab.addInhalt("Inhalt", calc.getQry());
	      }
	      out.writeObject(Tab);
	    }
	  }
	  TabCalc.setInhalt(iPos,"used",null);
	  g2.SaveVerlaufFertig(iVerlauf,Static.get_ms()-lClockV,false);
	  AU_AServer2012.ProtSave(g2,TabCalc,sArt+sZusatz+(sFehler==null ? "":" -> "+sFehler),lClockV,iStamm);
    }
    catch (Exception e)
    {
      Static.printError("Modellfehler:");
      Transact.printStackTraceS(e);
      if (iPos>=0)
    	  TabCalc.setInhalt(iPos,"used",null);
      AU_AServer2012.ProtSave(g2,TabCalc,sArt+sZusatz+" -> "+e,lClockV,iStamm);
    }
	return iStamm;
  }
  
}
