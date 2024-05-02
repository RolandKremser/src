/*
    All_Unlimited-Hauptmaske-Aufruf.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import org.json.JSONObject;

import All_Unlimited.Allgemein.AUZip;
import All_Unlimited.Allgemein.AUxml;
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.FTP;
import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.IntusCom;
import All_Unlimited.Allgemein.SMTP2;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
//import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.GPS;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.Zahl;

import java.util.Date;
import java.io.*;
import java.sql.Timestamp;

public class Aufruf// extends java.lang.Object
{
    // add your data members here
  public static int iGesAnzahl=0;
	Tabellenspeicher TabModelle;  // enthält alle Modelle
	Tabellenspeicher TabAbfragen; // enthält alle Abfragen
	Tabellenspeicher TabSpalten;  // enthält alle Spalten

    Tabellenspeicher TabGesamt=null; // mit set Table fixierte Tabelle

    Tabellenspeicher TabVar=null; // enthält alle lokalen Variablen
    Tabellenspeicher TabDebug=null; // alle Debug-Zeilen bei Debug mit bit TAB
    
    Tabellenspeicher TabBack=null; // Tabellen die bei Web zurückgeschickt werden
    Tabellenspeicher TabMD=null;   // Modell-Dialog-Abfrage für Web
    Tabellenspeicher TabClock=null;// Dauer der einzelnen Abfragen
    int iVB=0;
    int iWebEdit=-1;

	int iReg=0;
	int iQryReg=0;
    int iBewReg=0;
	double dReg=0;
	DateWOD dtReg=null;
	boolean bReg=false;
	int iB3Reg=0;
	String sReg="";
	int iStt=0;
	double dReg2=0;
	DateWOD dtReg2=null;
	String sReg2="";
	int iReg2=0;
	int iQryReg2=0;
    int iBewReg2=0;
	int iMass=0;
	int iZone=0;
	int iZoneOld=0;
	boolean bZone=false;
	int iThread=-1;
    int iFirma=0;
    int iDaten=0;
    GPS gps=null;
	String sTrenn=null;
	DateWOD dtStichtag=null;
	int iMReg=0;
	boolean bM_change=false;
        String sDest=null;
        int iFilter=0;

	Global g;
	boolean bAbbruch=false;
	int iAnzahl=0;
	int iAnzahlTab=0;
	int iAnzahlDel=0;
	int iAnzahlRef=0;
	private int iProt=0;
	int iModellBegriff=0;
	int iHauptModell=0;
	int iBegBer=0;
	boolean bTimer=false;
	int iNr=0;
	java.util.Vector VecStamm=null;
	java.util.Vector<Integer> VecBewPool=null;
	java.util.Stack<Integer> VecAic=null;
	java.util.Stack<String> StaString=new java.util.Stack<String>();
	public static java.util.Vector<Integer> VecModelle=null; // Modelle bei denen debuggt werden kann
	public static java.util.Vector<Integer> VecDebug=null;   // Debugausgabe starten
	public static java.util.Vector<Integer> VecBreak=null;   // Pausefenster anzeigen
    public static java.util.Vector<Integer> VecSpalten=null; // Spalten bei den Pausefenster gezeigt wird
    public static java.util.Vector<String> VecVar=null;      // Variablen bei den Pausefenster gezeigt wird
    public static java.util.Vector<String> VecEingabe=null;  // Eingaben bei den Pausefenster gezeigt wird
    public static java.util.Vector<String> VecBefehl=null;	 // Befehle bei den Pausefenster gezeigt wird
	int iPos=0;
	//int iBewegungstyp=0;
	int iMandantVor=0;

	int iMaxBefehle=-1;
	int iPause=-1;
	long iDBits=0;
	int iDebug=0;
	boolean bSetDebug=false; // ob Debug gesetzt werden kann
	boolean bDebug=false; // ob Debug gesetzt ist
	javax.swing.JDialog Dlg=null;
	String sDebug="";
	AUTextArea Txt;
  AUOutliner OutVar=null;
	// JCheckBox Cbxb;
	// Zahl Zahld;
	// Datum Datumdt;
	// Zahl Zahli;
	// Zahl Zahli2;
	JCheckBox CbxEnde=null;
	JCheckBox CbxDebug=null;
	JCheckBox CbxSM=null;
        JCheckBox CbxNoBreak;
        boolean bNoBreak=false;
	int iStamm_Last=0;
	int iStt_Last=0;
	boolean bFehler=false;
	String sFehler="";
	String sInfo=null;
	//String sWarnung=null;
	String sHeader=null;
	int iMsgArt=0;
	OLEopen Ole=null;
	SMTP2	smtp=null;
        AUZip Zip=null;
        AUxml xml=null;
        IntusCom IC=null;
        Import Imp=null;
       JSONObject jO=new JSONObject();

	private int iAnlageOld=0;
	int iAnlage=0;
        boolean bAnlage=false;
        boolean bDel=false;
	java.io.FileOutputStream fos=null;
	FTP ftp=null;
  Gauge Gau=null;
  boolean bGau=false;
  Abfrage AbfH=null;
  String sFrame=null;
  boolean bIO_Error=false;
  javax.swing.JFrame FomA=null;
        //int iSP=-1;
        Vector<Integer> VecAicShow=null;
        JButton BtnShowAic=new JButton("0");
        boolean bCommit=false;
        boolean bMass=false;
        boolean bThread=false;
        //boolean bPause=false;
        ObjectInputStream in=null;
        ObjectOutputStream out=null;
        boolean bReCalc=false;
//        Window FomFX=null;
//        boolean bJavaFX=false;
//        boolean bShowModell=false;
        Transact gF=null;
        SQL QryF=null;
        int iStatus=0;
        
        long lClockSave=0;
        long lClockAbf=0;
        Vector<Integer> VecSperre=new Vector<Integer>();
        String sLast=null;
        long lClockLast=0;
        long lClockDlg=0;

	public Aufruf(Global rg,java.util.Vector rVec,int iStamm,boolean bBew,javax.swing.JFrame rFom,int riProt)
	{
		g=rg;
		FomA=rFom;
		iProt=riProt;
		if (iProt>0)
        {
//        	bAnlage=true;
        	g.defInfo("Aufruf: iProt="+iProt);
        }
		//g.progInfo("Aufruf:"+rVec);
		Count.add(Count.Aufruf);
		if (bBew)
		{
			VecBewPool=Sort.getIntVec(rVec);
		    VecStamm=iStamm>0 ? Static.AicToVec(iStamm):null;
		}
		else
		{
			VecStamm=rVec;
			VecBewPool=null;
		}
		iReg=getStamm();
		iQryReg=iReg;
                iZone=g.getZone();
                iZoneOld=iZone;
                iMReg=g.getMandant();
		TabModelle = new Tabellenspeicher(g,new String[] {"Aic","Modell","Bezeichnung","iPeriode","sPeriode","aic_begriff","bits"});
		TabAbfragen = new Tabellenspeicher(g,new String[] {"Aic","Bezeichnung","Anzahl","Dauer","Stt","Stt-Bez","Bew","Bew-Bez","Begriff","Mandant","Stamm","von","bis","bits","Order","Bedingung","Abfrage1","Abfrage2","save","SQL","sum","AF","Filter"});
		TabSpalten = new Tabellenspeicher(g,new String[] {"Aic","Abfrage","Kennung","Kennung2","Kennung3","Bezeichnung","Datentyp","Eig","Format","Per","Laenge","bits","Eig2","Stt","EF","Anzeige","Ergebnisart","bits2","bits3","Rel","Mass","Faktor","Filter","Vec","x","y","w","h","FxE","TT","Stil","Stamm","Nummer"});
		TabSpalten.sGruppe="Abfrage";
		TabSpalten.sAIC="Kennung";
	}

	public void finalize()
	{
		Count.sub(Count.Aufruf);
	}
	
	Timestamp getVon()
	{
		return g.getVon(iVB);
	}
	
	Timestamp getBis()
	{
		return g.getBis(iVB);
	}
	
	void setVonBis(Timestamp dtVon,Timestamp dtBis)
	{
		if (iVB<=0)
			g.setVonBis(dtVon, dtBis);
		else
			g.setVonBisL(dtVon,dtBis,null,iVB,null,null);
	}
	
	void setVon(Timestamp dtVon)
	{
		if (iVB<=0)
			g.setVon(dtVon);
		else
			g.setVonBisL(dtVon, getBis(),null,iVB,null,null);			
	}
	
	void setBis(Timestamp dtBis)
	{
		if (iVB<=0)
			g.setBis(dtBis);
		else
			g.setVonBisL(getVon(), dtBis,null,iVB,null,null);			
	}
	
	String ViewNr()
	{
		return iVB>0 ? ""+iVB:"";
	}

	int getStamm()
	{
		//g.progInfo(iPos+"/"+VecStamm);
		return VecStamm==null || VecStamm.size()==0 ? 0:((Integer)VecStamm.elementAt(iPos)).intValue();
	}
	
//	void printError(String s) //TODO nur vorübergehend
//	{
//		printError(s,400);
//	}
	
	public static void addDebugModell(int iB)
	{
		if (iB>0)
		{
			if (VecModelle==null)
                VecModelle=new Vector<Integer>();
			if (!VecModelle.contains(iB))
			{
                VecModelle.addElement(iB);
                Global.fixInfoS("Debug-Modell dazu: "+iB+" -> "+VecModelle);
			}
		}
	}
	
	public static void removeDebugModell(int iB)
	{
		if (iB>0 && VecModelle != null && VecModelle.contains(iB))
		{
			VecModelle.removeElement(iB);
			Global.fixInfoS("Debug-Modell weg: "+iB+" -> "+VecModelle);
		}
	}

	void printError(String s,int riStatus)
	{
		bFehler=true;
		/*if(TabModelle!=null)
			sFehler=TabModelle.getS("Bezeichnung")+":"+s;
        else*/
        	sFehler=(iHauptModell>0 ? g.getBegBez(iHauptModell)+": ":"")+s;
        if (riStatus>0 && iStatus==0)
        	iStatus=riStatus;
        if (bSetDebug || g.Debug())
          sDebug+="\nError: "+s+"\n\n";
		g.printError(s,iHauptModell);
	}

	@SuppressWarnings("unchecked")
	public Tabellenspeicher posModell(int riModell,boolean bBegriff)
	{
		//g.TabModell.push();
		if (bBegriff ? TabModelle.posInhalt("aic_Begriff",riModell):TabModelle.posInhalt("Aic",riModell))
			return (Tabellenspeicher)TabModelle.getInhalt("Modell");
		else
		{
			if (bBegriff)
				iModellBegriff=riModell;
                        //SQL Qry=new SQL(g);
			//if (!Qry.open("select aic_modell,begriff.aic_begriff,modell.aic_stamm iPeriode,stamm.kennung sPeriode,bits,defbezeichnung Bezeichnung from begriff"+SQL.join2("modell","begriff")+" left outer"+SQL.join("stamm","modell")+" where "+(bBegriff?"begriff.aic_begriff=":"aic_modell=")+riModell))
			//	printError("Aufruf.posModell: Qry nicht ausführbar!");
      g.checkModelle();
      Tabellenspeicher Tab = null;
      int i=g.TabModelle.getPos(bBegriff?"aic_begriff":"aic_modell",riModell);
      if (i>=0)
      {
      	if ((g.TabModelle.getL(i,"bits")&Global.cstBew)>0 && VecBewPool==null && VecStamm!=null)
      	{
      		VecBewPool=Sort.getIntVec(VecStamm);
      		VecStamm=null;
      	}
        int iModell = g.TabModelle.getI(i,"aic_modell");
        g.printInfo(1, "posModell:" + iModell);
        Tab = new Tabellenspeicher(g,
            "Select aic_befehl,aic_spalte,(select aic_abfrage from spalte where befehl2.aic_spalte=aic_spalte) aic_abfrage" +
            ",mod_aic_modell,befehl2.aic_code,bef_aic_befehl,bef2_aic_befehl,MBits,kennung,Eingabe,Var from befehl2 left outer" + g.join("code", "befehl2") +
            " where (hide is null or hide = 0) and aic_modell=" + iModell + " order by " + g.order("bef2_aic_befehl") + "," + g.order("bef_aic_befehl") +
            ",aic_befehl", true,"Modell"+iModell);
        TabModelle.addInhalt("Aic", iModell);
        TabModelle.addInhalt("Modell", Tab);
        TabModelle.addInhalt("Bezeichnung", g.TabModelle.getS(i,"Bezeichnung"));
        TabModelle.addInhalt("iPeriode", g.TabModelle.getI(i,"iPeriode"));
        TabModelle.addInhalt("sPeriode", g.TabModelle.getS(i,"sPeriode"));
        TabModelle.addInhalt("aic_begriff", g.TabModelle.getI(i,"aic_begriff"));
        //g.addUsed(g.TabModelle.getI(i,"aic_begriff"));
        if (iHauptModell == 0)
        {
          iHauptModell = g.TabModelle.getI(i,"aic_begriff");
          if (iBegBer==0 && Static.bWeb)
        	  iBegBer=iHauptModell;
//          g.addUsed(iHauptModell);
        }
        TabModelle.addInhalt("bits", g.TabModelle.getL(i,"bits"));
        TabModelle.posInhalt("Aic", iModell);
      }
			else
        printError("Aufruf.posModell: Konnte nicht auf "+(bBegriff?"Begriff ":"Modell ")+riModell+" positionieren!",500);
			//g.debugInfo("TabModelle-Bezeichnung="+TabModelle.getS("Bezeichnung")+"/"+Qry.getS("Bezeichnung")+"("+Qry.getS("Kennung")+")");
			//if (g.Debug())
			//	TabModelle.showGrid("Alle Modelle");
			return Tab;
		}
		//TabBefehle.showGrid("Befehle");
		//g.TabModell.pop();
	}

	public void debugInfo(String s)
	{
		if (bDebug || g.Debug())
		{
//			g.fixInfo("(€) "+s);
//			if (Static.bWeb)
			{
				if (lClockLast==0 || (Static.get_ms()-lClockLast)>1000)
				{
					g.debugInfo(sLast==null ? s:sLast+s,bDebug);
					lClockLast=Static.get_ms();
					sLast=null;
				}
				else if (sLast==null)
					sLast=s+"\r\n";
				else
					sLast+=s+"\r\n";
			}
			sDebug+=s+"\r\n";
		}
	}

	public void printInfo(String s)
	{
          if (bDebug || g.Debug())
          {
            debugInfo("");
            debugInfo(s);
          }
          else if (g.Def())
            g.printInfo(s);
	}

        public void printInfo2(String s)
        {
          if (bDebug || g.Debug())
            debugInfo(s);
          else if (g.Def())
            g.printInfo(2,s);
        }
        
        public void save(String sSpalte,Object Obj)
        {
        	save(sSpalte,Obj,false);
        }

	public void save(String sSpalte,Object Obj,boolean bDaten)
	{
		g.printInfo(3,".save in "+sSpalte+":"+Obj);
		Tabellenspeicher TabSave = (Tabellenspeicher)TabAbfragen.getInhalt("save");
		if (!TabSave.posInhalt("Kennung",sSpalte))
		{
			TabSave.newLine();
			TabSave.setInhalt("Kennung",sSpalte);
		}
//		((Tabellenspeicher)TabAbfragen.getInhalt("Abfrage1")).setInhalt(sSpalte,Obj);
//		if (!TabAbfragen.isNull("Filter"))
//			((Tabellenspeicher)TabAbfragen.getInhalt("Abfrage2")).setInhalt(sSpalte,Obj);
		getAbf().setInhalt(sSpalte,Obj);
		TabSave.setInhalt("new",Obj);
		if (bDaten)
			TabSave.setInhalt("Daten",true);
	}

	public void initProt()
	{
		iProt=0;
		iAnlage=g.getBegriffAicS("Anlage",bTimer && !Static.bWeb ? "Timer":"Modell");
	}

	public int getProt()
	{
		if (iProt==0 || iAnlage != iAnlageOld)
		{
		  if (iAnlage==0)
		    initProt();
		  iProt=g.Protokoll(iAnlage,iHauptModell);
		  iAnlageOld=iAnlage;
		}
		return iProt;
	}
	
	public boolean ProtIsNull()
	{
		return iProt==0;
	}

	public void deleteAll(java.util.Vector Vec)
	{
		//int iProt = g.Protokoll();
                SQL.update(g,"bew_pool","pro_aic_protokoll="+getProt(),Vec,"bew_pool",5000,null);
		/*if (Vec.size()>0)
		{
                  SQL Qry=new SQL(g);
                  while (Vec.size()>1000)
                  {
                    java.util.Vector<Object> Vec2=new java.util.Vector<Object>();
                    for(int i=0;i<1000;i++)
                    {
                      Vec2.addElement(Vec.elementAt(0));
                      Vec.removeElementAt(0);
                    }
                    g.exec("update bew_pool set pro_aic_protokoll="+getProt()+" where "+Qry.in("AIC_Bew_Pool",Vec2));
                  }
                  g.exec("update bew_pool set pro_aic_protokoll="+getProt()+" where "+Qry.in("AIC_Bew_Pool",Vec));
                  Qry.free();
		}*/
	}

	public String addGueltig()
	{
		//new DateWOD(iPeriode>0?A.g.getVon():null),new DateWOD(iPeriode>0?A.g.getBis():null)
		String s="";
		if (g.getVon()!=null)
			s+=" and gueltig>="+g.getVonSql();
		if (g.getBis()!=null)
			s+=" and gueltig<"+g.getBisSql();
		return s;
	}

        public double Round100(double d)
        {
          return Abfrage.Round(d,100.0,(TabSpalten.getL("bits")&Global.cstRound100*0x10000)>0);
        }

        /*public static double Round(double dReg,double d2,boolean b)
        {
          if (b)
            if (Math.abs(dReg)<1000000000000000.0)
              if (dReg<0)
                return -Math.round(-dReg*d2)/d2;
              else
                return Math.round(dReg*d2)/d2;
            else
              return Math.rint(dReg*d2)/d2;
          else
            return dReg;
        }*/

        /*public void ClearOldData()
        {
          DateWOD dt=dtReg;
          if (dt==null)
          {
            dt=new DateWOD(SQL.getNow(g));
            dt.setDay1();
            dt.setMonth(1);
            dt.prevYear();
          }
          g.diskInfo("Starte ClearOldData bis "+dt.Format("dd.MM.yyyy"));
          String sWhere=" where pro_aic_protokoll is not null and gueltig<"+g.DateTimeToString(dt.toTimestamp());
          g.diskInfo(g.exec("delete from zwischenspeicher"+sWhere) + " zwischenspeicher richtig gelöscht");
          Vector VecBew=SQL.getVector("select aic_bewegungstyp from bewegungstyp where "+g.bit("bewBits",Global.cstTimer),g);
          //String sWhere2=" from (select * from bew_pool) as x"+sWhere+" and aic_bewegungstyp"+Static.SQL_in(VecBew);
          Vector Vec=SQL.getVector(g.top("aic_bew_pool from bew_pool"+sWhere+" and aic_bewegungstyp"+Static.SQL_in(VecBew),1000),g);
          g.fixInfo("Vec="+Vec);
          //sWhere=" from bew_pool"+sWhere+" and aic_bewegungstyp"+Static.SQL_in(VecBew);
          if (Vec!=null && Vec.size()>0)
          {
            g.diskInfo(g.exec("delete from bew_stamm where aic_bew_pool" + Static.SQL_in2(Vec)) + " bew_stamm richtig gelöscht");
            g.diskInfo(g.exec("delete from bew_wert where aic_bew_pool" + Static.SQL_in2(Vec)) + " bew_wert richtig gelöscht");
            g.diskInfo(g.exec("delete from bew_spalte where aic_bew_pool" + Static.SQL_in2(Vec)) + " bew_spalte richtig gelöscht");
            g.diskInfo(g.exec("delete from bew_boolean where aic_bew_pool" + Static.SQL_in2(Vec)) + " bew_boolean richtig gelöscht");
            g.diskInfo(g.exec("delete from bew_begriff where aic_bew_pool" + Static.SQL_in2(Vec)) + " bew_begriff richtig gelöscht");
            g.diskInfo(g.exec("delete from bew_von_bis where aic_bew_pool" + Static.SQL_in2(Vec)) + " bew_von_bis richtig gelöscht");
            g.diskInfo(g.exec("delete from bew_aic where aic_bew_pool" + Static.SQL_in2(Vec)) + " bew_aic richtig gelöscht");
            g.diskInfo(g.exec("delete from verlauf where aic_bew_pool" + Static.SQL_in2(Vec)) + " verlauf richtig gelöscht");
            Vector Vec2=SQL.getVector("select aic_bew_pool from bew_pool where bew_aic_bew_pool"+ Static.SQL_in2(Vec),g);
            if (Vec2!=null && Vec2.size()>0)
              g.diskInfo(g.exec("update bew_pool set bew_aic_bew_pool=null where"+g.in("aic_bew_pool",Vec2))  + " bew_aic_bew_pool richtig gelöscht");
            Vec2=SQL.getVector("select aic_bew_pool from bew_pool where bew2_aic_bew_pool"+ Static.SQL_in2(Vec),g);
            if (Vec2!=null && Vec2.size()>0)
              g.diskInfo(g.exec("update bew_pool set bew2_aic_bew_pool=null where"+g.in("aic_bew_pool",Vec2)) + " bew2_aic_bew_pool richtig gelöscht");
            g.diskInfo(g.exec("delete from bew_pool where aic_bew_pool" + Static.SQL_in2(Vec)) + " bew_pool richtig gelöscht");
          }
        }*/
        
        public boolean destroyStamm(int iStamm,int iRolle)
        {
        	if (iStamm<=0)
        		return false;
        	String sBez=g.getStamm(iStamm);
        	if (iRolle>0 && g.exec("delete from stamm_protokoll where aic_stamm="+iStamm+" and aic_rolle="+iRolle)>0)
        		g.SaveVerlauf(g.getBegriffAicS("Button","destroy role"),iStamm,0,iRolle,"Rolle von "+sBez+" entfernt",iVB);
        	int iAnz=SQL.getInteger(g, "select count(aic_rolle) from stammview2 where aic_stamm="+iStamm);
        	if (iAnz>0)
        	{
        		g.fixtestError(sBez+" wird nicht gelöscht, da "+iAnz+" Rollen vorhanden");
        		return false;
        	}
        	g.fixtestInfo(g.exec("delete from stammpool where aic_stamm="+iStamm+" or sta_aic_stamm="+iStamm)+" Stammpool von "+sBez+" entfernt");
        	g.fixtestInfo(g.exec("delete from stamm_protokoll where aic_stamm="+iStamm+" and (aic_rolle is not null or pro_aic_protokoll is not null)")+" Stamm_Protokoll von "+sBez+" entfernt");
        	g.fixtestInfo(g.exec("update bew_pool set pro_aic_protokoll="+getProt()+" where pro_aic_protokoll is null and anr="+iStamm)+" bew_pool von "+sBez+" entfernt");
        	g.exec("update stamm_protokoll set pro_aic_protokoll="+getProt()+" where aic_stamm="+iStamm);
        	g.fixtestInfo(sBez+" ("+iStamm+") entfernt");
        	g.SaveVerlauf(g.getBegriffAicS("Button", "destroy data"), iStamm, 0,iRolle,sBez+" komplett entfernt",iVB);
        	return true;
        }

        static int destroyBewPool(Global g,Vector Vec)
        {
          if (Vec==null || Vec.size()==0 || Tabellenspeicher.geti(Vec.elementAt(0))<=0)
            Static.printError("Aufruf.destroyBew ohne Daten nicht möglich");
          else
          {
        	  if (Vec.size()>1000)
        		  g.fixInfoS("destroyBewPool von "+Vec.size()+" Daten");
            String s="AIC_Bew_Pool"+ (Vec.size()==1 ? "="+Vec.elementAt(0):g.SQL_in(Vec));
            g.exec("delete from bew_stamm where " + s);
            g.exec("delete from bew_Wert where " + s);
            g.exec("delete from bew_spalte where " + s);
            g.exec("delete from bew_Boolean where " + s);
            g.exec("delete from bew_begriff where " + s);
            g.exec("delete from bew_Von_Bis where " + s);
            g.exec("delete from stammpool where " + s);
            g.exec("delete from bew_aic where " + s);
            g.exec("delete from verlauf where " + s);
            g.exec("update bew_pool set bew_aic_bew_pool=null where bew_"+s);
            g.exec("update bew_pool set bew2_aic_bew_pool=null where bew2_"+s);
            return g.exec("delete from bew_Pool where " + s);
          }
          return 0;
        }
        
        public static int destroyAll(Global g,Vector Vec)
        {
        	return destroyAll(g,Vec,0);
        }

        public static int destroyAll(Global g,Vector Vec,int iSek)
        {
          long lClock3=Static.get_ms();
          int iAnz=0;
          if (Vec.size()>0)
          {
            int iGes=Vec.size();
            g.diskInfo("removeBew: "+iGes+" x"+Static.Mem(g.TestPC()));
            int iMax=Static.iMaxDel;
            Gauge gauge=Static.bX11 && Vec.size()>iMax ? new Gauge(0,Vec.size()/iMax+1,"delete data",g,false):null;
            int iZeile=0;
            while (Vec.size()>iMax)
            {
              java.util.Vector<Object> Vec2=new java.util.Vector<Object>();
              for(int i=0;i<iMax;i++)
              {
                Vec2.addElement(Vec.elementAt(0));
                Vec.removeElementAt(0);
                iZeile++;
              }
              iAnz+=destroyBewPool(g,Vec2);
              if (iSek>0 && (Static.get_ms()-lClock3)>iSek*1000)
              {
            	  if (gauge != null)
                      gauge.free();
            	  return iAnz;
              }
              g.diskInfo("noch "+Vec.size()+" von "+iGes+Static.Mem(g.TestPC()));
              if (gauge != null)
                gauge.setText("delete data",iZeile);
            }
            iAnz+=destroyBewPool(g,Vec);
//            g.diskInfo("fertig"+Static.Mem(g.TestPC()));
            if (gauge != null)
              gauge.free();
          }
          return iAnz;
        }

        public static void DelDelBew(Global g,int iBew,DateWOD dtReg)
        {
          DateWOD dt=dtReg;
          if(dt==null || dt.isNull())
          {
                  dt=new DateWOD(SQL.getNow(g));
                  dt.setDay1();
                  dt.setMonth(1);
          }
          destroyAll(g,SQL.getVector("select aic_bew_pool from bew_pool where aic_bewegungstyp="+iBew+" and pro_aic_protokoll is not null and gueltig<"+g.DateTimeToString(dt.toTimestamp()),g));
        }

	public static void DelDeleted(Global g,DateWOD dtReg,boolean bAll)
	{
            long lClock=Static.get_ms();
            g.fixInfo("DelDeleted "+(bAll ? "All":"Modell")+" mit Date="+dtReg);
            //g.diskInfo("DelDeleted"+Static.Mem(g.TestPC()));
            int iSum=0;
            boolean bDD=true;
            Timestamp dtToday=null;
            if (dtReg==null)
              dtToday = SQL.getToday(g);
            int iArt=0; // 0..lang, 1..normal, 2.kurz, 3.sehrkurz
            while (bDD)
            {
              DateWOD dt;
              if (bAll)
                dt = dtReg;
              else
              {
                dt = new DateWOD(dtToday);
                for (int i = 0; i < (iArt==0 ? Static.iDelL:iArt==1 ? Static.iDel:iArt==2 ? Static.iDelK:Static.iDelSK); i++)
                  dt.yesterday();
              }
              int iAnz=0;
              Vector<Integer> VecBew=/*bAll ? null:*/SQL.getVector("select aic_bewegungstyp from bewegungstyp where "+g.bits("Bewbits",g.cstDD)+"="+(bAll ? g.cstNIE:iArt==0?g.cstL:iArt==1?g.cstN:iArt==2?g.cstK:g.cstSK),g);
              g.fixInfo("VecBew="+VecBew);
              if (bAll || VecBew.size()>0)
              {
                g.fixInfo("DelDeleted: " + (bAll ? "alles" : iArt == 0 ? "langes" : iArt == 1 ? "normales" : iArt == 2 ? "kurzes" : "sehr kurzes") + " bis " + dt);
                String sWhere;
                if (bAll)
                  sWhere= " bew_pool where bew_pool.pro_aic_protokoll is not null"+(VecBew.size()==0 ? "":" and bew_pool.aic_bewegungstyp not"+g.SQL_in(VecBew));
                else
                {
                  java.util.Vector<Object> Vec = new java.util.Vector<Object>();
                  Vec.addElement(new Integer(g.getBegriffAicS("Anlage", "Timer")));
                  Vec.addElement(new Integer(g.getBegriffAicS("Anlage", "Modell")));
                  Vec.addElement(new Integer(g.getBegriffAicS("Anlage", "Import")));
                  sWhere = " bew_pool join protokoll p on bew_pool.aic_protokoll=p.aic_protokoll and p.aic_code" + g.SQL_in(Vec)+" where bew_pool.aic_bewegungstyp"+g.SQL_in(VecBew);
                }
                int iProtMom = SQL.getInteger(g, "select max(aic_protokoll) from protokoll where timestamp<" + g.DateTimeToString(dt.toTimestamp()));
                int iProtMax = iProtMom;
                iAnz = SQL.getInteger(g, "select count(*) from" + sWhere + " and bew_pool.pro_aic_protokoll<=" + iProtMom);
                if (iAnz > 0)
                  g.diskInfo("DelDeleted:" + iAnz + " zu löschen" + Static.Mem(g.TestPC()));
                g.debugInfo(dt + "/" + iProtMom + ": Zeit=" + (Static.get_ms() - lClock) + ", Anzahl=" + iAnz);
                if (iAnz > 0)
                {
                  if (iAnz > 35000)
                  {
                    //dt=new DateWOD(SQL.getTimestamp(g,"select min(p.timestamp) from"+sWhere));
                    iProtMom = SQL.getInteger(g, "select min(bew_pool.pro_aic_protokoll) from" + sWhere);
                    iAnz = 0;
                    while (iAnz < 20000 && iProtMom < iProtMax)
                    {
                      //dt.tomorrow();
                      iProtMom += iAnz < 10000 ? 500 : iAnz < 16000 ? 200 : 100;
                      if (iProtMom > iProtMax)
                        iProtMom = iProtMax;
                      iAnz = SQL.getInteger(g, "select count(*) from" + sWhere + " and bew_pool.pro_aic_protokoll<=" + iProtMom); //" and p.timestamp<"+g.DateTimeToString(dt.toTimestamp()));
                      g.debugInfo(iProtMom + ": Zeit=" + (Static.get_ms() - lClock) + ", Anzahl=" + iAnz);
                    }
                    if (iProtMom != iProtMax)
                      dt = new DateWOD(SQL.getTimestamp(g, "select timestamp from protokoll where aic_protokoll=" + iProtMom));
                  }
                  g.fixInfo("Entferne bis " + dt.Format(g,"dd.MMM yyyy HH:mm") + ": " + iAnz + " gelöschte Bewegungssätze");
                  sWhere += " and bew_pool.pro_aic_protokoll<=" + iProtMom; //" and p.timestamp<"+g.DateTimeToString(dt.toTimestamp());
                  String sSub = "select aic_bew_pool from" + sWhere;
                  String sWhere2 = "aic_bew_pool in (" + sSub + ")";
                  sWhere = " where aic_bew_pool in (" + sSub + ")";
                  //int iSum=g.exec("delete from bew_stamm from bew_stamm join"+sWhere);
                  iSum += g.MySQL() ? 0 : g.exec("delete from bew_stamm" + sWhere);
                  if (iSum > 0)
                    g.ZeitMem("bew_stamm:" + iSum, lClock);
                  //int i=g.exec("delete from bew_wert from bew_wert join"+sWhere);
                  int i = g.exec("delete from bew_wert" + sWhere);
                  iSum += i;
                  if (i > 0)
                    g.ZeitMem("bew_wert:" + i, lClock);
                  i = g.exec("delete from bew_spalte" + sWhere);
                  iSum += i;
                  if (i > 0)
                    g.ZeitMem("bew_spalte:" + i, lClock);
                  //i=g.exec("delete from bew_boolean from bew_boolean join"+sWhere);
                  i = g.exec("delete from bew_boolean" + sWhere);
                  iSum += i;
                  if (i > 0)
                    g.ZeitMem("bew_boolean:" + i, lClock);
                  //i=g.exec("delete from bew_begriff from bew_begriff join"+sWhere);
                  i = g.exec("delete from bew_begriff" + sWhere);
                  iSum += i;
                  if (i > 0)
                    g.ZeitMem("bew_begriff:" + i, lClock);
                  //i=g.exec("delete from bew_von_bis from bew_von_bis join"+sWhere);
                  i = g.exec("delete from bew_von_bis" + sWhere);
                  iSum += i;
                  if (i > 0)
                    g.ZeitMem("bew_von_bis:" + i, lClock);
                  i = g.exec("delete from bew_aic" + sWhere);
                  iSum += i;
                  if (i > 0)
                    g.ZeitMem("bew_aic:" + i, lClock);
                  // Verlauf
                  i = g.exec("delete from verlauf" + sWhere);
                  iSum += i;
                  if (i > 0)
                    g.ZeitMem("verlauf:" + i, lClock);
                  //i=g.exec("delete from stammpool from stammpool join"+sWhere);
                  i = g.exec("delete from stammpool" + sWhere);
                  iSum += i;
                  if (i > 0)
                    g.ZeitMem("stammpool:" + i, lClock);
                  if (!g.MySQL())
                  {
                    i = g.exec("update bew_pool set bew_aic_bew_pool=null where bew_" + sWhere2);
                    iSum += i;
                    g.ZeitMem("bew_aic_bew_pool:" + i, lClock);
                    i = g.exec("update bew_pool set bew2_aic_bew_pool=null where bew2_" + sWhere2);
                    iSum += i;
                    g.ZeitMem("bew2_aic_bew_pool:" + i, lClock);
                  }
                  //i=g.exec("delete from bew_pool from"+sWhere);
                  int iSumBewStamm = 0;
                  if (g.MySQL())
                  {
                    Vector<Integer> Vec2 = SQL.getVector(sSub, g);
                    if (Vec2.size() > 5000)
                    {
                      i = 0;
                      int iP = 0;
                      for (; iP < Vec2.size(); )
                      {
                        Vector<Integer> Vec3 = new Vector<Integer>();
                        for (int iX = 0; iX < 5000 && iP < Vec2.size(); iX++)
                        {
                          Vec3.addElement(Vec2.elementAt(iP));
                          iP++;
                        }
                        iSumBewStamm += g.exec("delete from bew_stamm where aic_bew_pool" + g.SQL_in(Vec3));
                        int i2 = delBewPool(g, Vec3);
                        i += i2;
                        if (i2 > 0)
                          g.ZeitMem("bew_pool-Teil:" + i2, lClock);
                      }
                    }
                    else
                    {
                      iSumBewStamm += g.exec("delete from bew_stamm where aic_bew_pool" + g.SQL_in(Vec2));
                      i = delBewPool(g, Vec2);
                    }
                    if (iSumBewStamm > 0)
                      g.ZeitMem("bew_stamm:" + iSumBewStamm, lClock);
                  }
                  else
                    i = g.exec("delete from bew_pool" + sWhere);
                  iSum += i + iSumBewStamm;
                  g.ZeitMem("bew_pool:" + i, lClock);
                }
              }
              if (bAll || iArt==3 || iAnz>1000 && (Static.get_ms()-lClock)>120000)
                bDD=false;
              else
                iArt++;
//              if ((Static.get_ms()-lClock)>120000)
//                g.Logcheck();
            }
            g.fixInfo("Gesamtdauer:"+((Static.get_ms()-lClock)/1000)+" s für "+iSum+" Einzelsätze");
	}

        private static int delBewPool(Global g,Vector Vec)
        {
            Vector Vec2=SQL.getVector("select aic_bew_pool from bew_pool where bew_aic_bew_pool"+ g.SQL_in2(Vec),g);
            if (Vec2!=null && Vec2.size()>0)
              g.diskInfo(g.exec("update bew_pool set bew_aic_bew_pool=null where"+g.in("aic_bew_pool",Vec2))  + " bew_aic_bew_pool richtig gelöscht");
            Vec2=SQL.getVector("select aic_bew_pool from bew_pool where bew2_aic_bew_pool"+ g.SQL_in2(Vec),g);
            if (Vec2!=null && Vec2.size()>0)
              g.diskInfo(g.exec("update bew_pool set bew2_aic_bew_pool=null where"+g.in("aic_bew_pool",Vec2)) + " bew2_aic_bew_pool richtig gelöscht");
            return g.exec("delete from bew_pool where aic_bew_pool" + g.SQL_in(Vec));          
        }

        public Date getEinAus(boolean bEin,int riAbf,int riRolle,Tabellenspeicher TabSave)
        {
          int iPos=TabSpalten.getPos("Abfrage",riAbf);
          Date dt=null;
          for(int i=iPos;dt==null && i<TabSpalten.size() && TabSpalten.getI(i,"Abfrage")==riAbf;i++)
          {
            String sDT=TabSpalten.getS(i,"Datentyp");
            if ((sDT.equals("EinAustritt") || sDT.equals(bEin ? "Eintritt":"Austritt")) && TabSpalten.getI(i,"Eig")>0)
            {
              g.testInfo("getEinAus:"+sDT+", Eig="+TabSpalten.getS(i,"Eig"));
              int iPosE=g.TabEigenschaften.getPos("Aic",TabSpalten.getI(i,"Eig"));
              if (g.TabEigenschaften.getI(iPosE,"Rolle")==riRolle)
                if (TabSave.posInhalt("Kennung",TabSpalten.getInhalt("Kennung",i)))
                {
                  TabSave.setInhalt("saved",Boolean.TRUE);
                  dt=TabSave.getDate("new");
                }
            }
          }
          return dt;
        }

        private void changeAus(Date dtAus,String s)
        {
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from stamm_protokoll where "+s,true);
          g.exec("update stamm_protokoll set pro_aic_protokoll="+iProt+" where "+s);
          SQL Qry=new SQL(g);
          //int i=1;
          for (Tab.moveFirst();!Tab.out();Tab.moveNext())
          {
            Qry.add("Aic_Stamm",Tab.getI("Aic_Stamm"));
            Qry.add("aic_protokoll",iProt);
            Qry.add("Bezeichnung",Tab.getS("Bezeichnung"));
            Qry.add("Aic_Code",g.getBegriffAicS("Status","change"));
            Qry.add0("Aic_Rolle",Tab.getI("Aic_Rolle"));
            Qry.addNr(Tab.getI("Aic_Stamm"));
            Qry.add("aic_mandant",iMReg);
            Qry.add("Ab",Tab.getDate("Ab"));
            Qry.add("Weg",Tab.getDate("Weg"));
            Qry.add0("Firma",Tab.getI("Firma"));
            Qry.add("Eintritt",Tab.getDate("Eintritt"));
            Qry.add("Austritt",dtAus);
            Qry.add0("ANR",Tab.getI("ANR"));
            Qry.add("Aic_Stammtyp2",Tab.getI("Aic_Stammtyp2"));
            Qry.add("Kennung2",Tab.getS("Kennung2"));
            Qry.add0("Sta_Aic_Stamm2",Tab.getI("Sta_Aic_Stamm2"));
            Qry.insert("Stamm_Protokoll",false);
            //i++;
          }
        }
        
        public Tabellenspeicher getAbf()
    	{
    	   return (Tabellenspeicher)TabAbfragen.getInhalt("Abfrage"+(TabAbfragen.isNull("Filter") ? "1":"2"));
    	}

	public void saveAll()
	{
		long lClock2=Static.get_ms();
		//g.debugInfo("saveAll-"+iBew);
		g.checkAus("Modell "+g.getBegBez(iHauptModell));

		Tabellenspeicher TabAbf = getAbf();//(Tabellenspeicher)TabAbfragen.getInhalt("Abfrage1");
		Tabellenspeicher TabSave = (Tabellenspeicher)TabAbfragen.getInhalt("save");
		int iBew=TabAbfragen.getI("Bew");
		if(iBew==0) // Stammspeicherung (keine Bewegung)
		{
			int iStt2=TabAbfragen.getI("Stt");
			int iStamm = TabAbf.getI("aic_stamm");
			boolean bNeu=iStamm==0;
			getProt();

			TabSpalten.sAIC="Datentyp";
			String sKennung=null;
			String sBez=null;
			if (TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),"Kennung") && TabSave.posInhalt("Kennung",TabSpalten.getInhalt("Kennung")))
			{
				sKennung=TabSave.getS("new");
				TabSave.setInhalt("saved",Boolean.TRUE);
			}
			if ((TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),"Bezeichnung") || TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),"CalcBezeichnung")) && TabSave.posInhalt("Kennung",TabSpalten.getInhalt("Kennung")))
			{
				sBez=TabSave.getS("new");
				TabSave.setInhalt("saved",Boolean.TRUE);
			}
                        int iRolle=((Abfrage)TabAbfragen.getInhalt("AF")).iRolle;
                        Date dtEin=getEinAus(true,TabAbfragen.getI("Aic"),iRolle,TabSave);
                        Date dtAus=getEinAus(false,TabAbfragen.getI("Aic"),iRolle,TabSave);
			/*if (TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),"Eintritt") && TabSave.posInhalt("Kennung",TabSpalten.getInhalt("Kennung")))
			{
				dtEin=TabSave.getDate("new");
				TabSave.setInhalt("saved",Boolean.TRUE);
			}
			if (TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),"Austritt") && TabSave.posInhalt("Kennung",TabSpalten.getInhalt("Kennung")))
			{
				dtAus=TabSave.getDate("new");
				TabSave.setInhalt("saved",Boolean.TRUE);
			}
			if (TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),"EinAustritt") && TabSave.posInhalt("Kennung",TabSpalten.getInhalt("Kennung")))
			{
				dtEin=TabSave.getDate("new");
				dtAus=TabSave.getDate("new");
				TabSave.setInhalt("saved",Boolean.TRUE);
			}*/

			if (bNeu && sBez==null)
				printError("Neuer Stammsatz ohne Bezeichnung nicht anlegbar!",400);
			else
			{

				SQL Qry = new SQL(g);

                if (bNeu)
				{
					
					Qry.add("aic_stammtyp",iStt2);
					iStamm = Qry.insert("Stamm",true);
					TabAbf.setInhalt("aic_stamm",iStamm);
                                        if (iStt2==g.iSttFirma) // Bei neuem Satz muss Firma im Modell gesetzt werden, außer bei Firma
                                          iFirma=iStamm;
				}
                //g.progInfo("neue Werte von "+sBez+": "+dtEin+" - "+dtAus);
                int iANR=0;
                if (!bNeu && (sBez != null || dtEin != null || dtAus != null))
                {
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select bezeichnung,Eintritt,Austritt,Firma,ANR from Stamm_Protokoll where pro_aic_protokoll is null and aic_stamm="
                      + iStamm+Abfrage.Rolle(iRolle),true);
                  if (sBez == null)
                    sBez = Tab.getS("bezeichnung");
                  iFirma = Tab.getI("Firma");
                  iANR = Tab.getI("ANR");
                  if (dtEin == null)
                    dtEin = Tab.getTimestamp("Eintritt");
                  if (dtAus == null)
                    dtAus = Tab.getTimestamp("Austritt");
                }
                //g.progInfo("ergänzte Werte von "+sBez+": "+dtEin+" - "+dtAus);
				if (sBez != null)
				{
                                  g.progInfo("ändere Stamm_Protokoll von "+sBez+": "+dtEin+" - "+dtAus);
					if(!bNeu)
                                          g.exec("update Stamm_Protokoll set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and aic_stamm="+iStamm+Abfrage.Rolle(iRolle));
					Qry.add("Aic_Stamm",iStamm);
                                        Qry.add("aic_protokoll",iProt);
                                        if (sBez.length() > 250)
                                          sBez = sBez.substring(0,250);
					Qry.add("Bezeichnung",sBez);
					if (dtEin != null)
						Qry.add("Eintritt",dtEin);
					if (dtAus != null)
						Qry.add("Austritt",dtAus);
					Qry.add("Aic_Code",g.getBegriffAicS("Status",bNeu?"new":"change"));
                                        //int iRolle=((Abfrage)TabAbfragen.getInhalt("AF")).iRolle;
                                        if (bNeu && iRolle>0)
                                          Qry.add("aic_rolle",iRolle);
                                        Qry.addNr(iStamm);
                                        Qry.add("aic_mandant",iMReg);
                                        Qry.add0("Firma",iFirma);
                                        Qry.add0("ANR",iANR);
                                        Qry.add("aic_stammtyp2",iStt2);
                                        if (sKennung != null)
                    						Qry.add("Kennung2",sKennung);
                                        Qry.insert("Stamm_Protokoll",false);
                                        
                                        if (bNeu && iRolle>0)
                                        {
                                          Qry.add("Aic_Stamm",iStamm);
                                          Qry.add("aic_protokoll",iProt);
                                          Qry.add("Bezeichnung",sBez);
                                          Qry.add("Aic_Code",g.getBegriffAicS("Status","copy"));
                                          Qry.addNr(iStamm);
                                          Qry.add("aic_mandant",iMReg);
                                          Qry.add0("Firma",iFirma);
                                          Qry.add0("ANR",iANR);
                                          Qry.add("Eintritt",getEinAus(true,TabAbfragen.getI("Aic"),0,TabSave));
                                          Qry.add("Austritt",getEinAus(false,TabAbfragen.getI("Aic"),0,TabSave));
                                          Qry.add("aic_stammtyp2",iStt2);
                                          if (sKennung != null)
                      						Qry.add("Kennung2",sKennung);
                                          Qry.insert("Stamm_Protokoll",false);
                                        }
				}
                else if (!bNeu)
                {
                  TabSave.moveBefore();
                  Date dtAus2=getEinAus(false,TabAbfragen.getI("Aic"),0,TabSave);
                  if (!TabSave.out())
                  {
                    g.testInfo("ändere Austritt bei "+g.getStamm(iStamm)+" auf "+dtAus2);
                    if(dtAus2 != null)
                      changeAus(dtAus2,"Aic_Stamm=" + iStamm +" and pro_aic_protokoll is null and austritt is null");
                      //g.exec("update stamm_protokoll set austritt=" + g.DateTimeToString(dtAus2) + " where Aic_Stamm=" + iStamm +" and pro_aic_protokoll is null and austritt is null");
                    else
                    {
                      dtAus = SQL.getTimestamp(g, "select Austritt from Stamm_Protokoll where pro_aic_protokoll is null and aic_rolle is null and aic_stamm=" + iStamm);
                      if(dtAus != null)
                        changeAus(null,"Aic_Stamm=" + iStamm + " and austritt=" + g.DateTimeToString(dtAus) + " and pro_aic_protokoll is null");
                        //g.exec("update stamm_protokoll set austritt=null where Aic_Stamm=" + iStamm + " and austritt=" + g.DateTimeToString(dtAus) + " and pro_aic_protokoll is null");
                    }
                  }
                }


				Qry.free();
				TabSpalten.sAIC="Kennung";
				for (TabSave.moveFirst();!TabSave.eof();TabSave.moveNext())
				{
					if (!TabSave.getB("saved"))
					{
						char c=TabSave.getS("Kennung").charAt(0);
						sKennung = c=='b' || c=='d' ? 'v'+TabSave.getS("Kennung").substring(1):TabSave.getS("Kennung");
						if (TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),sKennung))
						{
                                                  String sDatentyp=TabSpalten.getS("Datentyp");
                                                  if (sDatentyp.equals("von_bis"))
                                                  {
                                                    TabSave.push();
                                                    java.util.Date dtVon2=null;
                                                    java.util.Date dtBis2=null;
                                                    if (TabSave.posInhalt("Kennung","v"+TabSave.getS("Kennung").substring(1)))
                                                    {
                                                            dtVon2 = TabSave.getTimestamp("new");
                                                            TabSave.setInhalt("saved",Boolean.TRUE);
                                                    }
                                                    if (TabSave.posInhalt("Kennung","b"+TabSave.getS("Kennung").substring(1)))
                                                    {
                                                            dtBis2 = TabSave.getTimestamp("new");
                                                            TabSave.setInhalt("saved",Boolean.TRUE);
                                                    }
                                                    TabSave.pop();
                                                    TabSave.setInhalt("new",new VonBis(g,dtVon2,dtBis2,TabSpalten.getS("Format")));
                                                  }
                                                  g.progInfo("Import.Save1:"+sDatentyp+"/"+TabSpalten.getI("Eig"));
                                                  if (TabSave.getB("Daten"))
                  								  {
                  							        int iD=TabSave.getI("new");
                  							        int iEig=TabSpalten.getI("Eig");
//                  							        g.fixtestError("Speichere Daten="+iD+" mit Eig="+iEig+", Prot="+iProt+", Stamm="+iStamm);
                  							        g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and aic_stamm="+iStamm+" and aic_eigenschaft="+iEig);
                  							        SQL Insert = new SQL(g);
                  							        Insert.add("Aic_Daten",iD);
                  							        Insert.add("Aic_Protokoll",iProt);
                  							        Insert.add("Aic_Stamm",iStamm);
                  							      	Insert.add("Aic_Eigenschaft",iEig);
                  							        Insert.insert("StammPool",false);
                  							        Insert.free();
                  								  }
                                                  else
                                                    Import.Save(g,TabSave.getInhalt("new"),TabSpalten.getI("Eig"),true,iStamm,iProt,bNeu,dtStichtag==null?null:dtStichtag.toTimestamp(),false);
						}
					}
					//TabSave.setInhalt("saved",Boolean.TRUE);
				}
			}
		}
		else // neuer Bewegungssatz 
		{
		int iPool = TabAbf.getI("aic_bew_pool");
		g.printExec("saveAll Bew="+iBew+" bei Pool="+iPool);
		if (iPool==0)	// Neue Zeile
		{
			//g.debugInfo("saveAll: Neue Zeile für "+iBew);
			TabSpalten.sAIC="Datentyp";
			SQL Qry = new SQL(g);
			//Qry.setTest(g.Debug());
			Qry.add("aic_protokoll",getProt());
            if (bDel)
              Qry.add("pro_aic_protokoll",getProt());
			if (TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),"BewDatum") && TabSave.posInhalt("Kennung",TabSpalten.getInhalt("Kennung")))
			{
                          java.sql.Timestamp ts=TabSave.getTimestamp("new");
                          Qry.add("Gueltig",ts);
                          Qry.add("LDATE",Static.DateToInt(ts));
                          TabSave.setInhalt("saved",Boolean.TRUE);
			}
                        else
                          Qry.add("LDATE",0);
                        Qry.add("LDATE2",0);
                        Qry.add("LDATE3",0);
                        Qry.add("BOOL1",0);
                        Qry.add("BOOL2",0);
                        Qry.add("LDateVon",0);
                        Qry.add("LDateBis",0);
                        if (TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),"BewBew") && TabSave.posInhalt("Kennung",TabSpalten.getInhalt("Kennung")))
			{
                          Qry.add0("BEW2_AIC_BEW_POOL",TabSave.getI("new"));
                          TabSave.setInhalt("saved",Boolean.TRUE);
                        }
			Qry.add("aic_bewegungstyp",iBew);
			Qry.add("aic_mandant",iMReg);
			if (g.hasZone(iBew))
			{
				Qry.add("Zone",iZone);
				TabAbf.setInhalt("Zone",iZone);
			}
			iPool=Qry.insert("Bew_Pool",true);
			boolean bANR=false;
			debugInfo("saveAll: Neue Zeile für "+g.TabErfassungstypen.getBezeichnungS(iBew)+" ("+iBew+") -> "+iPool);
			TabAbf.setInhalt("aic_bew_pool",iPool);
			TabSpalten.sAIC="Kennung";
			TabSave.moveFirst();
			while (!TabSave.eof())
			{
				if (!TabSave.getB("saved") && !Static.Gleich(null,TabSave.getInhalt("new")))
				{
					char c=TabSave.getS("Kennung").charAt(0);
					String sKennung = c=='b' || c=='d' ? 'v'+TabSave.getS("Kennung").substring(1):TabSave.getS("Kennung");
					if (TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),sKennung))
					{
						String sDatentyp=TabSpalten.getS("Datentyp");
						int iEig = TabSpalten.getI("Eig");
						//int iPool = TabAbf.getI("aic_bew_pool");
						if (iEig==0)
							printError("Aufruf.saveAll(): Speicherung auf 2. Ebene ("+TabSave.getS("kennung")+") nicht möglich!",500);
							//g.printError("saveAll: Kann auf <"+TabSave.getS("kennung")+"> nicht speichern!");
						else if (TabSave.getB("Daten"))
								{
							        int iD=TabSave.getI("new");
//									g.fixtestError("Speichere Daten="+iD+" mit Eig="+iEig+", Prot="+iProt+", Pool="+iPool);
									g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and aic_bew_pool="+iPool+" and aic_eigenschaft="+iEig);
  							        SQL Insert = new SQL(g);
  							        Insert.add("Aic_Daten",iD);
  							        Insert.add("Aic_Protokoll",iProt);
  							        Insert.add("aic_bew_pool",iPool);
  							      	Insert.add("Aic_Eigenschaft",iEig);
  							        Insert.insert("StammPool",false);
  							        Insert.free();
								}
						else if (!sDatentyp.startsWith("Bew"))
                        {
                          g.progInfo("Import.Save2:"+sDatentyp+"/"+iEig+"/"+sKennung);
                          Import.Save(g, TabSave.getInhalt("new"), iEig, false, iPool, iProt, true, null,false);
                        }
						else
						{
							String sAnzeige= "neue Werte hinzufügen:";
							String sBefehl="";
              String sANR=null;
							if (sDatentyp.equals("BewStamm") || sDatentyp.equals("BewHierarchie"))
                                                        {
                                                          sANR=Import.insertBewStamm(g,iBew,iPool,iEig,TabSave.getI("new"));
                                                          /*sBefehl = "insert into bew_stamm (aic_bew_pool,aic_eigenschaft,aic_stamm) values (" + iPool + "," + iEig + "," +TabSave.getInhalt("new") + ")";
                                                          if (g.isEigANR(iEig))
                                                            g.exec("update bew_pool set anr="+TabSave.getInhalt("new")+" where aic_bew_pool="+iPool);*/
                                                        }
							else if (sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular"))
								sBefehl="insert into bew_Begriff (aic_bew_pool,aic_eigenschaft,aic_Begriff) values ("+iPool+","+iEig+","+TabSave.getInhalt("new")+")";
							else if (sDatentyp.equals("BewBoolean"))
                                                        {
                                                          if (TabSave.getB("new"))
                                                            Import.insertBewBool(g,iBew,iPool,iEig);
                                                            //sBefehl = "insert into bew_boolean (aic_bew_pool,aic_eigenschaft,spalte_boolean) values (" + iPool + "," + iEig + ",1)";
                                                        }
                                                        else if (sDatentyp.equals("BewBool3"))
                                                        {
                                                          if (TabSave.getI("new")>0)
                                                            Import.insertBewBool3(g,iBew,iPool,iEig,-TabSave.getI("new"));
                                                            //sBefehl = "insert into bew_aic (aic_bew_pool,aic_eigenschaft,aic) values (" + iPool + "," + iEig + ","+TabSave.getI("new")+")";
                                                        }
							else if (sDatentyp.equals("BewDatum2"))
                                Import.insertBewDatum2(g,iBew,iPool,iEig,TabSave.getDate("new"));
								//sBefehl="insert into bew_von_bis (aic_bew_pool,aic_eigenschaft,von,dauer) values ("+iPool+","+iEig+","+g.DateTimeToString(TabSave.getDate("new"))+",0)";
							else if (sDatentyp.equals("BewDauer"))
								sBefehl="insert into bew_von_bis (aic_bew_pool,aic_eigenschaft,dauer) values ("+iPool+","+iEig+","+TabSave.getF("new")+")";
							else if (sDatentyp.equals("BewZahl") || sDatentyp.equals("BewCount"))
                                                        {
                                                          if(TabSave.getF("new") != 0.0)
                                                            sBefehl = "insert into bew_Wert (aic_bew_pool,aic_eigenschaft,spalte_wert) values ("+iPool+","+iEig+","+TabSave.getF("new") + ")";
                                                        }
                                                        else if (sDatentyp.equals("BewZahl2"))
                                                        {
                                                          if(TabSave.getF("new") != 0.0)
                                                            sBefehl = "insert into bew_spalte (aic_bew_pool,aic_eigenschaft,aic_stamm,Wert) values ("+iPool+","+iEig+","+TabSpalten.getI("Rel")+","+TabSave.getF("new")+")";
                                                        }
							else if (sDatentyp.equals("BewWaehrung"))
                                                        {
                                                          boolean bInfinity=Math.abs(TabSave.getF("new")) == Double.POSITIVE_INFINITY;
                                                          if (TabSave.getF("new") !=0.0 && !bInfinity)
                                                            sBefehl = "insert into bew_wert (aic_bew_pool,aic_eigenschaft,spalte_Wert,aic_stamm) values (" + iPool + "," + iEig + "," +
                                                              g.getATS(TabSave.getF("new")) + "," + g.getWaehrung() + ")";
                                                          if (bInfinity)
                                                            printError(g.TabEigenschaften.getBezeichnungS(iEig)+" nicht speicherbar, da unendlich",400);
                                                        }
                                                        else if (sDatentyp.equals("BewWaehrung2"))
                                                        {
                                                          if(TabSave.getF("new") != 0.0)
                                                            sBefehl = "insert into bew_spalte (aic_bew_pool,aic_eigenschaft,aic_stamm,Wert,sta_aic_stamm) values ("+
                                                                iPool+","+iEig+","+TabSpalten.getI("Rel")+","+g.getATS(TabSave.getF("new"))+","+g.getWaehrung()+")";
                                                        }
							else if (sDatentyp.startsWith("BewMass"))
							{
                                                          if (TabSave.getF("new") !=0.0)
                                                          {
								int iPos=g.TabEigenschaften.getPos("Aic",iEig);
								int iEinheit=g.TabEigenschaften.getI(iPos,"aic_stamm");
								if (iMass!=0)
                                  iEinheit=iMass;
                                double dFaktor = SQL.getDouble(g,"select spalte_double from stammpool where aic_stamm="+iEinheit+" and pro2_aic_protokoll is null and aic_eigenschaft="+g.iEigFaktor);
                                if (dFaktor != 0.0)
								{
									g.debugInfo("  Faktor="+dFaktor);
									if (sDatentyp.equals("BewMass"))
                                      sBefehl="insert into bew_wert (aic_bew_pool,aic_eigenschaft,spalte_Wert,grundwert,aic_stamm) values ("+iPool+","+iEig+","+TabSave.getF("new")/dFaktor+","+TabSave.getF("new")+","+iEinheit+")";
                                    else
                                      sBefehl="insert into bew_spalte (aic_bew_pool,aic_eigenschaft,aic_stamm,Wert,grundwert,sta_aic_stamm) values ("+
                                          iPool+","+iEig+","+TabSpalten.getI("Rel")+","+TabSave.getF("new")/dFaktor+","+TabSave.getF("new")+","+iEinheit+")";
								}
								else
									printError("Aufruf.saveAll(): BewMass="+g.getStamm(iEinheit)+" ("+iEinheit+") hat keinen Faktor!",400);
                                                          }
                                                        }
							else if (sDatentyp.equals("BewVon_Bis"))
							{
								sBefehl="";
								TabSave.push();
								java.sql.Timestamp dtVon2=null;
								java.sql.Timestamp dtBis2=null;
								double dDauer2=0.0;
								if (TabSave.posInhalt("Kennung","v"+iEig))
								{
									dtVon2 = TabSave.getTimestamp("new");
									TabSave.setInhalt("saved",Boolean.TRUE);
								}
								if (TabSave.posInhalt("Kennung","b"+iEig))
								{
									dtBis2 = TabSave.getTimestamp("new");
									TabSave.setInhalt("saved",Boolean.TRUE);
								}
								if (TabSave.posInhalt("Kennung","d"+iEig))
								{
									dDauer2 = TabSave.getF("new");
									TabSave.setInhalt("saved",Boolean.TRUE);
								}
								else
								{
									if (dtVon2 !=null && dtBis2 != null)
										dDauer2 = (dtBis2.getTime()-dtVon2.getTime())/1000.0;
								}
								TabSave.pop();
								if (dDauer2 != 0.0 || dtVon2 != null || dtBis2 != null)
								{
									Qry.clear();
//									//if (dDauer2 !=0.0)
//										Qry.add("Dauer",dDauer2);
//									if (dtVon2 !=null)
//										Qry.add("von",dtVon2);
//									if (dtBis2 !=null)
//										Qry.add("bis",dtBis2);
//									Qry.add("aic_bew_pool",iPool);
//									Qry.add("aic_eigenschaft",iEig);
//									Qry.insert("bew_von_bis",false);
									Import.insertBewVonBis(g,iBew, iPool, iEig,dtVon2,dtBis2,dDauer2);
								}
								sAnzeige= "BewVon_Bis:";
								//sAnzeige= "BewVon_Bis:"+new Zeit(dtVon2,"")+"/"+new Zeit(dtBis2,"")+"/"+dDauer2+":";
							}
							else
								printError("Aufruf.saveAll(): Datentyp <"+sDatentyp+"> wird bei der Modellberechnung nicht unterstützt",501);
              if (sANR != null && sANR.equals("ANR"))
                bANR=true;
							g.debugInfo("  "+sAnzeige+TabSave.getS("kennung")+"/"+sDatentyp+"/"+iEig);
							if (!sBefehl.equals(""))
							{
								g.debugInfo("  "+sAnzeige+TabSave.getS("kennung")+"/"+sDatentyp+"/"+iEig);
								g.exec(sBefehl);
							}
						}
					}
					else
					{
						printError("Aufruf.saveAll(): Abfrage "+TabAbfragen.getInhalt("Aic")+" - Eigenschaft <"+sKennung+"/"+TabSave.getS("kennung")+"> nicht gefunden!",500);
						if (g.Prog())
							TabSpalten.showGrid();
					}
				}
				TabSave.setInhalt("saved",Boolean.TRUE);
				TabSave.moveNext();
			}
			Qry.free();
      if (!bANR && (g.getBewBits(iBew) & Global.cstNOA)>0)
        printError("ANR nicht gespeichert", 200);
		}
		else // Bewegungssatz nur ändern (bestehenden überschreiben)
		{
			g.debugInfo("saveAll: Ergänzung mit "+iBew);
			//TabSave.showGrid("save");
			//g.debugInfo("nach showGrid");
			TabSave.moveFirst();
			//g.debugInfo("vor while");
			while (!TabSave.eof())
			{
				//g.debugInfo("vor if");
				if (!TabSave.getB("saved"))
				{
					//g.debugInfo("in if");
					char c=TabSave.getS("Kennung").charAt(0);
					//g.debugInfo("c="+c);
					String sKennung = c=='b' || c=='d' ? 'v'+TabSave.getS("Kennung").substring(1):TabSave.getS("Kennung");
					//g.debugInfo("sKennung="+sKennung);
					if (TabSpalten.posInhalt(TabAbfragen.getInhalt("Aic"),sKennung))
					{
						String sDatentyp=TabSpalten.getS("Datentyp");

						//g.debugInfo("sDatentyp="+sDatentyp);
						int iEig = TabSpalten.getI("Eig");
						if (iEig==0)
							printError("Aufruf.saveAll(): Speicherung auf 2. Ebene ("+TabSave.getS("kennung")+") nicht möglich!",500);
							//.printError("saveAll: Kann auf <"+TabSave.getS("kennung")+"> nicht speichern!");
						else
						{
							String sAnzeige;
							//String sBefehl="";
							if (sDatentyp.equals("BewVon_Bis"))
							{
								TabSave.push();
								java.sql.Timestamp dtVon2=null;
								java.sql.Timestamp dtBis2=null;
								double dDauer2=0.0;
								if (TabSave.posInhalt("Kennung","v"+iEig))
								{
									dtVon2 = TabSave.getTimestamp("new");
									TabSave.setInhalt("saved",Boolean.TRUE);
								}
								else
								{
									dtVon2 = TabAbf.getTimestamp("v"+iEig);
								}
								if (TabSave.posInhalt("Kennung","b"+iEig))
								{
									dtBis2 = TabSave.getTimestamp("new");
									TabSave.setInhalt("saved",Boolean.TRUE);
								}
								else
								{
									dtBis2 = TabAbf.getTimestamp("b"+iEig);
								}
								if (TabSave.posInhalt("Kennung","d"+iEig))
								{
									dDauer2 = TabSave.getF("new");
									TabSave.setInhalt("saved",Boolean.TRUE);
								}
								else
								{
									//dDauer1 = TabAbf.getF("d"+iEig);
									dDauer2 = dtBis2==null || dtVon2==null?0:(dtBis2.getTime()-dtVon2.getTime())/1000.0;
								}
								TabSave.pop();
								SQL Qry = new SQL(g);
								//Qry.setTest(g.Debug());
								//if (dDauer2==0 && dtVon2 == null && dtBis2 == null)
								//g.exec("delete from bew_von_bis where aic_bew_pool="+iPool+" and aic_eigenschaft="+iEig);
								Import.delBewVonBis(g,iBew,iPool,iEig);
								if (dDauer2!=0 || dtVon2 != null || dtBis2 != null)
								{
//									Qry.add("Dauer",dDauer2);
//									if (dtVon2 !=null)
//										Qry.add("von",dtVon2);
//									if (dtBis2 !=null)
//										Qry.add("bis",dtBis2);
//									Qry.add("aic_bew_pool",iPool);
//									Qry.add("aic_eigenschaft",iEig);
//									Qry.insert("bew_von_bis",false);
									Import.insertBewVonBis(g,iBew, iPool, iEig,dtVon2,dtBis2,dDauer2);
								}
								Qry.free();
								sAnzeige= "BewVon_Bis:";
								//sAnzeige= "BewVon_Bis:"+new Zeit(dtVon2,"")+"/"+new Zeit(dtBis2,"")+"/"+dDauer2+":";
							}
							else if (!sDatentyp.startsWith("Bew"))
								Import.Save(g,TabSave.getInhalt("new"),iEig,false,iPool,getProt(),false,null,false);
							else
							{
								String sBefehl="";
								sAnzeige= "Wert löschen:";
								if (sDatentyp.equals("BewStamm") || sDatentyp.equals("BewHierarchie"))
                                    Import.delBewStamm(g,iBew,iPool,iEig);
								else if (sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular"))
									sBefehl="delete from bew_begriff where aic_bew_pool="+iPool+" and aic_eigenschaft="+iEig;
								else if (sDatentyp.equals("BewBoolean"))
                                    Import.delBewBool(g,iBew,iPool,iEig);
                                else if (sDatentyp.equals("BewBool3"))
                                    Import.delBewBool3(g,iBew,iPool,iEig);
                                else if (sDatentyp.equals("BewDatum2") || sDatentyp.equals("BewDauer"))
                                    Import.delBewDatum2(g,iBew,iPool,iEig);
								else if (sDatentyp.equals("BewDatum"))
									sBefehl="update bew_pool set gueltig=null,ldate=0 where aic_bew_pool="+iPool;
                                else if (sDatentyp.equals("BewBew"))
									sBefehl="update bew_pool set BEW2_AIC_BEW_POOL=null where aic_bew_pool="+iPool;
								else if (sDatentyp.equals("BewMass") || sDatentyp.equals("BewWaehrung") || sDatentyp.equals("BewZahl") || sDatentyp.equals("BewCount"))
									sBefehl="delete from bew_wert where aic_bew_pool="+iPool+" and aic_eigenschaft="+iEig;
                                else if (sDatentyp.equals("BewMass2") || sDatentyp.equals("BewWaehrung2") || sDatentyp.equals("BewZahl2"))
                                  sBefehl="delete from bew_spalte where aic_bew_pool="+iPool+" and aic_eigenschaft="+iEig+" and aic_stamm="+TabSpalten.getI("Rel");
                                if (sDatentyp.equals("BewStamm") || sDatentyp.equals("BewHierarchie") || sDatentyp.startsWith("BewBool") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("BewDauer"))
                                  ;
								else if (!sBefehl.equals(""))
									g.exec(sBefehl);
								else
									printError("Aufruf.saveAll(): delete: Datentyp <"+sDatentyp+"> wird bei der Modellberechnung nicht unterstützt",501);

								if(TabSave.getInhalt("new")!=null)		// Wert hinzufügen
								{
									sBefehl="";
									sAnzeige= "Wert hinzufügen:";
                  boolean bNull=false;
                  String sANR=null;
									if (sDatentyp.equals("BewStamm") || sDatentyp.equals("BewHierarchie"))
										sANR=Import.insertBewStamm(g,iBew,iPool,iEig,TabSave.getI("new"));                                     
									else if (sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular"))
										sBefehl="insert into bew_begriff (aic_bew_pool,aic_eigenschaft,aic_begriff) values ("+iPool+","+iEig+","+TabSave.getInhalt("new")+")";
									else if (sDatentyp.equals("BewBoolean"))
                                    {
                                      bNull=!TabSave.getB("new");
                                      if (!bNull)
                                        Import.insertBewBool(g,iBew,iPool,iEig);
                                        //sBefehl = "insert into bew_boolean (aic_bew_pool,aic_eigenschaft,spalte_boolean) values (" + iPool + "," + iEig + ",1)";
                                    }
                                    else if (sDatentyp.equals("BewBool3"))
                                    {
                                      bNull=TabSave.getI("new")<=0;
                                      if (!bNull)
                                        Import.insertBewBool3(g,iBew,iPool,iEig,-TabSave.getI("new"));
                                        //sBefehl = "insert into bew_aic (aic_bew_pool,aic_eigenschaft,aic) values (" + iPool + "," + iEig + ","+TabSave.getI("new")+")";
                                    }
									else if (sDatentyp.equals("BewDatum2"))
                                        Import.insertBewDatum2(g,iBew,iPool,iEig,TabSave.getDate("new"));
										//sBefehl="insert into bew_von_bis (aic_bew_pool,aic_eigenschaft,von,dauer) values ("+iPool+","+iEig+","+g.DateTimeToString(TabSave.getDate("new"))+",0)";
									else if (sDatentyp.equals("BewDauer"))
										sBefehl="insert into bew_von_bis (aic_bew_pool,aic_eigenschaft,dauer) values ("+iPool+","+iEig+","+TabSave.getF("new")+")";
									else if (sDatentyp.equals("BewZahl") || sDatentyp.equals("BewCount"))
                                                                        {
                                                                          bNull=TabSave.getF("new") == 0.0;
                                                                          if(!bNull)
                                                                            sBefehl = "insert into bew_Wert (aic_bew_pool,aic_eigenschaft,spalte_wert) values ("+iPool+","+iEig+","+TabSave.getF("new")+")";
                                                                        }
                                                                        else if (sDatentyp.equals("BewZahl2"))
                                                                        {
                                                                          bNull=TabSave.getF("new") == 0.0;
                                                                          if(!bNull)
                                                                            sBefehl = "insert into bew_spalte (aic_bew_pool,aic_eigenschaft,aic_stamm,Wert) values ("+iPool+","+iEig+","+TabSpalten.getI("Rel")+","+TabSave.getF("new")+")";
                                                                        }
									else if (sDatentyp.equals("BewDatum"))
									{
										String sZone=null;
										if (bZone) //iZone != g.getZone())
										{
											sZone=",zone="+iZone;
											debugInfo("Zone geändert von "+TabAbf.getI("Zone")+" auf "+iZone+" bei "+iPool);
											TabAbf.setInhalt("Zone",iZone);
											bZone=false;
										}
										sBefehl="update bew_pool set gueltig="+g.DateTimeToString(TabSave.getDate("new"))+",ldate="+Static.DateToInt(TabSave.getDate("new"))+(sZone==null ?"":sZone)+" where aic_bew_pool="+iPool;
									}
                                                                        else if (sDatentyp.equals("BewBew"))
                                                                          sBefehl="update bew_pool set BEW2_AIC_BEW_POOL="+(TabSave.getI("new")==0 ? "null":TabSave.getI("new"))+" where aic_bew_pool="+iPool;
                                                                        else if (sDatentyp.equals("BewWaehrung"))
                                                                        {
                                                                          bNull=TabSave.getF("new") == 0.0;
                                                                          boolean bInfinity=Math.abs(TabSave.getF("new")) == Double.POSITIVE_INFINITY;
                                                                          if(!bNull && !bInfinity)
                                                                            sBefehl = "insert into bew_Wert (aic_bew_pool,aic_eigenschaft,spalte_Wert,aic_stamm) values ("+
                                                                                iPool+","+iEig+","+g.getATS(TabSave.getF("new"))+","+g.getWaehrung()+")";
                                                                          if (bInfinity)
                                                                            printError(g.TabEigenschaften.getBezeichnungS(iEig)+" nicht speicherbar, da unendlich",400);
                                                                        }
                                                                        else if (sDatentyp.equals("BewWaehrung2"))
                                                                        {
                                                                          bNull=TabSave.getF("new") == 0.0;
                                                                          if(!bNull)
                                                                            sBefehl = "insert into bew_spalte (aic_bew_pool,aic_eigenschaft,aic_stamm,Wert,sta_aic_stamm) values ("+
                                                                                iPool+","+iEig+","+TabSpalten.getI("Rel")+","+g.getATS(TabSave.getF("new"))+","+g.getWaehrung()+")";
                                                                        }
									else if (sDatentyp.startsWith("BewMass"))
									{
										int iPos=g.TabEigenschaften.getPos("Aic",iEig);
										int iEinheit=g.TabEigenschaften.getI(iPos,"aic_stamm");
										if (iMass!=0)
                                          iEinheit=iMass;
                                        double dFaktor = SQL.getDouble(g,"select spalte_double from stammpool where aic_stamm="+iEinheit+" and pro2_aic_protokoll is null and aic_eigenschaft="+g.iEigFaktor);
                                        if (dFaktor != 0.0)
										{
											g.debugInfo("  Faktor="+dFaktor);
                                            if(TabSave.getF("new") != 0.0)
                                            {
                                              if (sDatentyp.equals("BewMass"))
                                                sBefehl="insert into bew_wert (aic_bew_pool,aic_eigenschaft,spalte_Wert,grundwert,aic_stamm) values ("+iPool+","+iEig+","+TabSave.getF("new")/dFaktor+","+TabSave.getF("new")+","+iEinheit+")";
                                              else
                                                sBefehl="insert into bew_spalte (aic_bew_pool,aic_eigenschaft,aic_stamm,Wert,grundwert,sta_aic_stamm) values ("+
                                                    iPool+","+iEig+","+TabSpalten.getI("Rel")+","+TabSave.getF("new")/dFaktor+","+TabSave.getF("new")+","+iEinheit+")";
                                            }
										}
										else
											printError("Aufruf.saveAll(): BewMass="+iEinheit+" hat keinen Faktor!",400);
									}
                                                                        if (sDatentyp.equals("BewStamm") || sDatentyp.equals("BewHierarchie") || sDatentyp.startsWith("BewBool") || sDatentyp.equals("BewDatum2") || bNull)
                                                                          ;
                                                                        else if (!sBefehl.equals(""))
									{
										g.debugInfo("  "+sAnzeige+TabSave.getS("kennung")+"/"+sDatentyp+"/"+iEig);
										g.exec(sBefehl);
									}
									else if (!sDatentyp.startsWith("BewMass"))
										printError("Aufruf.saveAll(): insert: Datentyp <"+sDatentyp+"> wird bei der Modellberechnung nicht unterstützt",501);
								}
							}

							/*if (!sBefehl.equals(""))
							{
								g.debugInfo("  "+sAnzeige+TabSave.getS("kennung")+"/"+sDatentyp+"/"+iEig);
									SQL.exec(g,sBefehl);
							}*/
						}
					}
					else
					{
						//printError("Aufruf.saveAll: Eigenschaft <"+TabSave.getS("kennung")+"> nicht gefunden!");
						printError("Aufruf.saveAll(): Abfrage "+TabAbfragen.getInhalt("Aic")+" - Eigenschaft <"+sKennung+"/"+TabSave.getS("kennung")+"> nicht gefunden!",500);
						if (g.Prog())
							TabSpalten.showGrid();
					}
				}
				TabSave.setInhalt("saved",Boolean.TRUE);
				TabSave.moveNext();
			}
		}
		}
		TabSave.clearAll();
		bZone=false;
		TabSpalten.bInsert = false;
		g.debugInfo("saveAll-Ende");
		lClockSave+=Static.get_ms()-lClock2;
	}
}

