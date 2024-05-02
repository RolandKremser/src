/*
    All_Unlimited-Hauptmaske-Calc.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.lang.reflect.Field;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Stack;
import java.util.Vector;
import java.util.zip.ZipEntry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.Image;

import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.AUZip;
import All_Unlimited.Allgemein.AUxml;
import All_Unlimited.Allgemein.AUVector;
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.FTP;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.IntusCom;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SMTP2;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.SyncStamm;
import All_Unlimited.Allgemein.TabTerminal;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.Ampel;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Anzeige.GPS;
import All_Unlimited.Allgemein.Anzeige.Mass;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Grunddefinition.DefModell;
import All_Unlimited.Grunddefinition.Lizenz;
import All_Unlimited.Grunddefinition.Reinigung;
import All_Unlimited.Web.webAbf;
import All_Unlimited.Web.webJSON;
import All_Unlimited.Web.webLog;
// import javafx.scene.control.SplitPane;

import java.awt.Font;

public class Calc extends java.lang.Object
{
//    public static final int AS_VER=4;

    // Debug-Bits
    public static final int DD =	0x0001; // dReg  .. Zahlenregister
    public static final int DDT=	0x0002; // dtReg .. Datumsregister
    public static final int DB =	0x0004; // bReg  .. Boolean-Register
    public static final int DS =	0x0008; // sReg  .. String-Register
    public static final int DI =	0x0010; // iReg  .. Aic-Register
    public static final int DQRY=	0x0020; // iQryReg..Qry-Register
    public static final int DBEW=	0x0040; // iBewReg..Bew_Pool-Register
    public static final int DMASS=	0x0080; // iMass .. Mass-Register
    public static final int DSTT=	0x0100; // iStt  .. Stammtyp-Register
    public static final int DPOS=	0x0200; // iPos  .. Position des Stammsatzes
    public static final int DVON=	0x0400; // g.von
    public static final int DBIS=	0x0800; // g.bis
    public static final int DD2 =	0x1000; // dReg2 .. 2. Zahlenregister
    public static final int DDT2=	0x2000; // dtReg2.. 2. Datumsregister
    public static final int DST =	0x4000; // dtStichtag
    public static final int DS2 =	0x8000; // sReg2 .. 2. String-Register
    public static final int DI2 =       0x10000;// iReg  .. 2. Aic-Register
    public static final int DQRY2=      0x20000;// iQryReg2..2. Qry-Register
    public static final int DBEW2=      0x40000;// iBewReg2..Bew_Pool-Register
    public static final int DTRENN=     0x80000;// sTrenn.. Trennzeichen
    public static final int DJDT=      0x100000;// g.dtStichtag
    public static final int DJI=       0x200000;// g.iJoker
    public static final int DJS=       0x400000;// g.sJoker
    public static final int DVEC=      0x800000;// VecAic
    public static final int DZONE=    0x1000000;// iZone
    public static final int DVSA=     0x2000000;// VecStamm
    public static final int DVECS=    0x4000000;// StaString
    public static final int DSAVE=    0x8000000;// Speicher-Zeilen
    public static final int DMANDANT=0x10000000;// Mandant
    public static final int DPR     =0x20000000;// Pause bei Refresh
    public static final int DAE     =0x40000000;// Abfragen am Ende anzeigen
    public static final long DPUSH  =0x80000000l;// Tabellen-Zeilen durch push
    public static final long DGAUGE=0x100000000l;// Gauge
    public static final long DVAR=  0x200000000l;// VAR
    public static final long DANL=  0x400000000l;// Anlage
    public static final long DVECB= 0x800000000l;// VecBewPool
    public static final long DMEM= 0x1000000000l;// Memory
    public static final long DC=   0x2000000000l;// Dauer
    public static final long DEST= 0x4000000000l;// destination
    public static final long FIRMA=0x8000000000l;// Firma
    public static final long TAB= 0x10000000000l;// alles in auch in Tabelle
    public static final long DB3= 0x20000000000l;// Bool3-Register
    public static final long KSL= 0x40000000000l;// kein Sleep
    public static final long DAT= 0x80000000000l;// AIC_Daten
    public static final long BEZ=0x100000000000l;// Bezeichnung bei Aics anzeigen
    public static final long ZBP=0x200000000000l;// Zone-Breakpoint
    public static final long AIC=0x400000000000l;// Aic von Stamm- und Bewegungssatz anzeigen
    public static final long DMZ=0x800000000000l;// Modelle mit Befehle laufend anzeigen
    public static final long DGPS=  0x1000000000000l;// GPS anzeigen
//    public static final long NTH= 0x2000000000000l;// no Thread, d.h. gleich ausführen
    public static final long UMRDT= 0x4000000000000l;// Zahlenregister umrechnen (wenn >60 in Datum)
    public static final long UMRT=  0x8000000000000l;// Zahlenregister umrechnen (wenn >60 in Tage)
    public static final long UMRH= 0x10000000000000l;// Zahlenregister umrechnen (wenn >60 in Stunden)
    // Befehl-Bits
    public static final int SCHLEIFE=	0x0001; // While-Schleife statt Bedingung

    public static final int REFRESH=    0x0006; // Refresh-Art für Abfrage
    public static final int AUTO   =    0x0000; // Automatsiches ermitteln des Refresh für die Abfrage
    public static final int IMMER  =    0x0002; // Abfrage immer refreshen
    public static final int NIE    =    0x0004; // Abfrage nie refreshen (außer neu)
    public static final int VAR    =    0x0008; // Eingabe zeigt auf Variable

    public static final int ART    =    0x0070; // Art die Abfrage zu holen
    public static final int NORMAL =    0x0000; // Abfrage normal holen
    public static final int EMPTY  =    0x0010; // Abfrage leer holen
    //public static final int SYNC   =    0x0020; // verwende Sync-Wert
    public static final int USE_VEC=    0x0030; // verwende Vector
    public static final int VEC_BEW=    0x0040; // Bewegungsvector
//    public static final int ART6   =    0x0050;
//    public static final int ART7   =    0x0060;
//    public static final int ART8   =    0x0070;
    //public static final int CACHE  =    0x0080; // Abfrage wird gecached
    public static final int GRUPPE =    0x0100; // nimm Gruppe dafür
    public static final int SAVE   =    0x0200; // speichern statt merken
    public static final int SPALTE =    0x0400; // direkt auf Spalte anwenden
    public static final int REG =       0x0800; // direkt auf Reg
    public static final int HIDE   =    0x1000; // Befehl nicht durchführen
    public static final int CHANGE =    0x2000; // Zeile wurde geändert
    public static final int ALLE   =    0x4000; // auf alle (Zeilen der Tabelle, Benutzer o.ä.) anwenden
    public static final int THREAD =    0x8000; // im Hintergrund
    
    public static final int KSZR   =   0x10000; // kein Stammzeitraum
    public static final int KBZR   =   0x20000; // kein Bewegungszeitraum
    
    public static final int SUM    =   0x40000; // mit Summe der Spalte
    
    public static final int PERM   =   0x80000; // permanent schicken (für Variablen)
    
    public static final int VART   =  0x300000; // VAR-Art: ob lokal, ich, lesen oder alle
    public static final int VLOK   =  0x000000; // Variable nur Lokal
    public static final int VICH   =  0x100000; // Variable nur ich
    public static final int V_R    =  0x200000; // Variable andere nur lesen
    public static final int VALL   =  0x300000; // Variable von allen lesen und schreiben
    
    public static final int C_ART  =  0xC00000; // Concat-Art
    public static final int CONCAT =  0x400000; // String anhängen
    public static final int CWS    =  0x800000; // Concat with Space
    public static final int INIT   =  0xC00000; // Init statt Concat
    
    public static final int M_ART  = 0x3000000; // Math-Art (add, sub, init)
    public static final int ADD    = 0x1000000; // addiert Wert
    public static final int SUB    = 0x2000000; // subtrahiert Wert
    public static final int MINIT  = 0x3000000; // nur Wert 
    
    // bitsBew
    public static final int NoEDT=1;
    public static final int NoDEL=2;

    private static Vector<Number> VecManual=null;
    private static AUVector VecManualS=null;
    private static JDialog DlgDebug=null; // Dialog für Swing-Debug
//    private static Stage DFxDebug=null; // Dialog für JavaFX-Debug
    public static int iD_Abbruch=0;// Zahl SpnAbbruch = new Zahl(0);
    public static int iD_Debug=0;  // Zahl SpnDebug = new Zahl(0);
    private static int iD_Pause=10; // Zahl SpnPause = new Zahl(10);
    private static long iDBitsL=DD+DDT+DS+DI+DQRY+DBEW+DVON+DBIS+BEZ+(Static.bWeb ? DZONE+DMANDANT+DVEC+DVECB+AIC+DGPS:(webLog.bVarInfo ? DVAR:0));

    public static boolean bPause=false;
    public static Message Msg=null;
    
    public static Tabellenspeicher TabV=null; // Globaler Variablenspeicher
    
    private JButton BtnCol=null;
    public long iMBits=0;
    private boolean bExit=false;
    private int iLastZeile=0;
    private boolean bMD=false;
    private boolean bQ=false;
    private int iWAnz=0;
    private boolean bJa=false;
    private int iAbfrageMD=0;
    private int iFC=0;
//    private int iErrorLast=0;
//    private Stage DlgFX=null;
    
    //TODO Befehle dazu und weg
    // verbotene und depricated Befehle
    public static AUVector Bdead=new AUVector(new String[] {"del deleted","clear old data","use cache","open Stage","error mail","Befehl149","reserve118","check bitsbew","init 12"
    		,"Befehl168","Befehl185","Befehl193","Befehl194","Befehl195","Befehl196","Befehl197","Befehl198","Befehl199"
    		,"Bedingung75","Bedingung76","Bedingung77","Bedingung78","Bedingung79"
    		,"Bedingung44","FX-Dialog","write line hold","write hold data","create Dialog","close Dialog","Dialog-Info","Dialog-Error"
    		,"save act. aic","save act_date","save aic","save Bew","save Bool3","save boolean","save from","save to","save duration","save false","save true","save NIL","save number","save QryAic","save String"});
    public static AUVector Bdep=new AUVector(new String[] {"not refetch","use VecAic","use VecBew","init 86400","round","save false"});
    
    // Bedingte Befehlsbits
    public static AUVector Bconcat=new AUVector(new String[] {"Befehl146","Befehl147","Befehl150","get String","get title","pop String",
    		"get von-bis-String","get Date-String","Befehl164","get path","get from AServer","reserve139","get Temp","get Begriff-Kennung","get dependent memo"});
    public static AUVector Bconcat2=new AUVector(new String[] {"add space-number","add number-String","add Date-String","concat dep memo","concat show","concat error","concat chr","concat format",
    		"Befehl151","add Begriff-Title","use Sync","add dReg-String","concat","Befehl171","Befehl175"});
    public static AUVector Badd=new AUVector(new String[] {"sum","sum group","sum VecAic","count","count distinct"});
    
 // Befehle mit zwingender Eingabe
    public static AUVector BmEingabe=new AUVector(new String[] {"init Bool3","JsonDatum","JsonString","JsonZahl","JsonAic","Befehl159","Befehl174","JsonBoolean","JsonMass","split String","reserve138","get Color","Bedingung55","Bedingung62","round2"});
    // Befehle mit zwingender Spalte
    public static AUVector BmSpalte=new AUVector(new String[] {"clear row","new line","insert line","sort down","sort up","sort String down","sort String up",
      "delete all","delete row","Befehl178","destroy row","destroy all","fix group","set table","copy to","clear with","compress","compress2",
      "get changes","get changes2","get History","set edit","set hide","Befehl163","clearInhalt2","clearEmpty","read Temp","copy new Line","copy insert Line",
      "delete","remember boolean","remember aic","remember Bew","remember QryAic","remember false","remember NIL",
      "remember true","remember act_Aic","remember date","remember from","remember to","remember duration","remember act_date",
      "remember line","save line","save this line","save act. aic","save aic","save Bew","save QryAic","save boolean","save Bool3","remember Bool3",
      "save false","save true","save NIL","save number","save model","save act_date","save datetime","save date","save from","save to",
      "save duration","save String","write data record","clear data record","write file","write line",
      "write file plus","write line plus","Autoimport","Autoimport2","Autoimport3","ODBC Import","JDBC Import","remove real aic","write line hold",
      "sum","sum group","add sum group","sum VecAic","avg","min","max","count distinct",
      "get Mandant","get Bew","get Stt","get real Bew","push","pop","pos -1","pos last","move first","move last","move next","move previous",
      "pos aic","pos next aic","pos real aic","pos min","pos max","get title","is Sperre","get Date-String","VecAic add Vec","replace wildcard",
      "concat table","LineToString","use Sync","clean prot","Anweisung20","JsonMass","Befehl186","Befehl187","get persons"});
    // Befehle ohne Spalten
    public static AUVector BoSpalte=new AUVector(new String[] {"exit","Model finish"/*,"break"*/,"not refetch","refetch","use VecAic","use VecBew","next empty",
      "set testprint","del deleted","clear old data","delete file","open file","create file",
      "close file","write line2","import exchange","String add 1","Rente","ClearJson"});
    // Befehle die Spalte mit Eingabe/Variable kombinierbar sind
    public static AUVector BkSpalte=new AUVector(new String[] {"round2","set title","add number","subtract number","multiply number","divide number","power number","1/number","Modolo","set vonbis",
                                                 "init number","init String","init date","get QryAic","get real QryAic","get real aic","get real Bew","get aic","get 0-String",
                                                 "remember number","remember from","remember to","remember duration","weekday=","<",">","Bedingung64","Bedingung65","Bedingung66","=","= Aic","= DateTime","= Date","< date","> date",
                                                 "remember date","remember String","save number","save duration","save String","concat","xml double","get Date-String","add space-number","get String-Date2",
                                                 "concat with space","concat dep memo","add Date-String","add number-String","add dReg-String","read Temp","write Temp","get Query","get computer",//"get Bool3",
                                                 "= String","date to von","date to bis","delete row","get Color","concat table","LineToString","concat Format","xml string","replace wildcard",
                                                 "add time","subtract time","startsWith","save Bool3","remember Bool3","pos Bool3","pos next Bool3","= Bool3","Zeilen=","Pos=","set Pos","set date","save datetime","save date","write data record",
                                                 "pos String","pos next String","pos number","pos next number","Bedingung44","Befehl141","Befehl152","Befehl153","Befehl175",
                                                 "reserve123","reserve124","reserve125","reserve127","trunc left","trunc right","concat table","reserve138","Bedingung46","< duration","> duration","= duration",
                                                 "get month vb","get years vb","Bedingung54","pos next Date","remember aic","save aic","push aic","floor seconds","roundup seconds","round seconds","concat chr","init 365","first day","init Bool3",
                                                 "pos aic","pos next aic","pos real aic","pos","pos not aic","pos next not aic","pos next","pos real","= std aic","= QryAic","Befehl162","sum","sum group","count","count distinct","sum VecAic","Befehl172",
                                                 "JsonDatum","JsonString","JsonZahl","JsonAic","JsonMass","remember act_date","save act_date","get date","is String","is manual","pos manual","= public holiday","Befehl186","Befehl187","Befehl189",
                                                 "set Sync-Stamm","set Sync-Vec","add Sync-Vec","Tab message"});

    public static AUVector DTnotSave=new AUVector(new String[] {"Stichtag","Trennzeichen","CalcBezeichnung","CalcField","Timestamp","SysAic","Aic","Benutzer","Anlage","Mandant",
                                                  "Filler", "entfernt", "LoeschBenutzer", "CalcMass", "CalcWaehrung", "CalcDauer", "Ampel", "Vorgaenger", "CalcString","CalcBoolean","CalcBool3",
                                                  "BenutzerPasswort","CalcDatum","Firma"});
    
    public static AUVector BnotRefresh=new AUVector(new String[] {"clear query","Bedingung56","init Bool3","Befehl177","clean prot"});
    private Gauge GauZ=null;

   Aufruf A;
	String sBefehl="";
	String sBefBez="";
	String sGruppe="";
        String sEingabe=null;
        String sVar=null;
	int iBew=0;
	DateWOD dtVonOld=null;
	DateWOD dtBisOld=null;
	int iPeriode;
	int iPer;
	boolean bNotRefetch=false;
	boolean bRefetch=false;
  boolean bUseVecAic=false;
  boolean bUseVecBew=false;
  int iANR_Eig=0;
  //boolean bUseSync=false;
  boolean bNextEmpty=false;
  //boolean bCache=false;
        //boolean bUseVar=false;
        boolean bVar=false;
        boolean bAS=false;
        boolean bAR=false;
        //boolean bSave=false;
        boolean bGroup=false;
        boolean bAlle=false;
        boolean bSum=false;
        int iC_Art=0;
        int iM_Art=0;
        boolean bTitel=true;
	Tabellenspeicher TabBefehle;
	public Tabellenspeicher TabAbfrage;

	int iJa=0;
	int iNein=0;
	boolean bModellende = false;
	String sSpalte;
	boolean bSpalte;
//	int iWhileBefehl=-1;
	Vector<Integer> VecWhile=new Vector<Integer>();
	long lClock2 = Static.get_ms();
	Timestamp dtVonVor2=null;
	Timestamp dtBisVor2=null;
        private boolean bHaupt;
        int iErsterBefehl=0;
        //private String sModell=null;
        public static boolean bAbbruch=false;
        private String sBind;
        private Tabellenspeicher TabJokerStt=null;
        long lClockD;
        private String sPeriode=null;
        Vector<Integer> VecM=null;
        int iPosM=-1;
        private boolean bBew=false;
        private int iVArt2=0;
        private boolean bPerm=false;
        
        private String sS;
        private String sB;

//      public Global getg() // für AU_CalcService.modellBerechnung
//      {
//        return A.g;
//      }

      public int getQry()
      {
        return A.iQryReg;
      }

      private String VecStammToString(Vector Vec)
      {
        if (Vec==null || Vec.size()==0)
          return "<null>";
        else if (Vec.size()==1)
          return A.g.getStamm(Sort.geti(Vec,0));
          //return SQL.getString(A.g,"select bezeichnung from stammview2 where aic_rolle is null and aic_stamm="+Vec.elementAt(0));
        else
        {
          SQL Qry=new SQL(A.g);
          String s=Qry.list("bezeichnung","stammview2 where "+A.g.in("aic_stamm",Vec));
          Qry.free();
          return s+"="+Vec;
        }
      }

      private void VecAicToString(String s,Vector Vec)
      {
        if (A.bDebug && Vec != null && !Vec.isEmpty() && Sort.geti(Vec.elementAt(0))!=0)
          A.debugInfo(s+": VecAic="+VecStammToString(Vec));
      }

      /*public static void BG(final Global rg,final int riBegriff,final boolean bBegriff,final Vector rVecStamm,final boolean bTimer)
      {
        new Thread(new Runnable()
        {
          public void run() {
            new Calc(null, rg, riBegriff, bBegriff, rVecStamm, bTimer);
          }}).start();
      }*/

	/*public Calc(Object rA,Global rg,int riBegriff,boolean bBegriff,Vector rVecStamm,boolean bTimer)
	{
		this(rA,rg,riBegriff,bBegriff,rVecStamm,bTimer?200000:-1,null,0);
	}*/
      
//      public Calc(Object rA,Global rg,int riBegriff,boolean bBegriff,Vector rVecStamm,int iMaxTimer,Abfrage AbfH,int iProt)
//      {
//        this(rA,rg,riBegriff,bBegriff,rVecStamm,false,iMaxTimer,AbfH,iProt,null,null);
//      }

      public Calc(Object rA,Global rg,int riBegriff,boolean bBegriff,Vector rVecStamm,int iMaxTimer,Abfrage AbfH,int iProt)
      {
        this(rA,rg,riBegriff,bBegriff,rVecStamm,0,false,iMaxTimer,AbfH,iProt,null,null,null,null,0,-1,null,null);
      }
      
      public Calc(Object rA,Global rg,int riBegriff,boolean bBegriff,Vector rVecStamm,boolean rbBew,int iMaxTimer,Abfrage AbfH,int iProt,ObjectInputStream in,ObjectOutputStream out,Tabellenspeicher TabVar,Tabellenspeicher TabMD,int iVB)
      {
    	  this(rA,rg,riBegriff,bBegriff,rVecStamm,0,rbBew,iMaxTimer,AbfH,iProt,in,out,TabVar,TabMD,iVB,-1,null,null);
      }

	public Calc(Object rA,Global rg,int riBegriff,boolean bBegriff,Vector rVecStamm,int iStamm,boolean rbBew,int iMaxTimer,Abfrage AbfH,int iProt,ObjectInputStream in,ObjectOutputStream out,Tabellenspeicher TabVar,Tabellenspeicher TabMD,int iVB,int iZone,Optional<Integer> iThread,Optional<Integer> iBegBer)
	{
		long lClock = Static.get_ms();
		long lClockVB=0;
        lClockD=lClock;
//        rg.fixInfo("  Calc:"+riBegriff+", iVB="+iVB+" ("+rg.getVB(iVB)+")");
//        boolean bPOHide=false;
		//rg.add("M");
		bPause=false;
		bBew=rbBew;
		JFrame FomA=null;
                String sFrame=null;
//        Window FomFX=null;
//        if (rA!=null && !(rA instanceof Aufruf))
//        	rg.fixtestError("Modellaufruf "+riBegriff+"/"+bBegriff+" von "+Static.print(rA));
//        if (rA==null && Static.bInfoExcept)
//        	new Exception().printStackTrace();
//        if (rA instanceof Stage)
//        {
//        	FomFX=(Stage)rA;
//        	rg.fixtestInfo("FomFX von Stage fixiert:"+FomFX);
//        	rA=null;
//        }
//        else if (rA instanceof PopOver)
//        {
//        	FomFX=(PopOver)rA;
//        	bPOHide=((PopOver)FomFX).isAutoHide();
//    		if (bPOHide) ((PopOver)FomFX).setAutoHide(false);
//        	rg.fixtestInfo("FomFX von PopOver fixiert:"+FomFX);
//        	rA=null;
//        }
//        else 
        boolean bModellFehlt=false;
        if (rA instanceof JFrame || rA instanceof JDialog)
		{
        	if (rA instanceof JFrame)
        	{
			  FomA=(JFrame)rA;
              sFrame=FomA.getName();
        	}
        	else
        	{
        		FomA=null;
        		sFrame=((java.awt.Window)rA).getName();		
        	}
            //rg.checkModelle();
            int iBegriff=bBegriff ? riBegriff:rg.ModellToBegriff(riBegriff);
            rg.fixtestInfo("   ***   Calc ["+rg.getBegBez(iBegriff)+"] von Formular ["+sFrame+"] mit Vec="+rVecStamm);
			rA=null;
		}
        else if (rA==null)
        {
        	int iBegriff=bBegriff ? riBegriff:rg.ModellToBegriff(riBegriff);
        	rg.fixtestInfo("   **   Calc ["+rg.getBegBez(iBegriff)+"] mit Vec="+rVecStamm);
        	if (iBegriff>0)
        		rg.fixInfo("   **   Calc ["+rg.getBegBez(iBegriff)+"] mit Vec="+rVecStamm+", VB="+iVB+" -> ZR="+rg.getVB(iVB));
        	else
        		bModellFehlt=true;
        	if (bModellFehlt || Static.bWeb && !Static.bAll && iVB<6 && !rg.bDruck && iMaxTimer>-2)
        	{
        		rg.printError(bModellFehlt ? "!!! Modell fehlt bei Vec="+rVecStamm:"Calc ["+rg.getBegBez(iBegriff)+"] mit Vec="+rVecStamm+", VB="+iVB+" -> ZR="+rg.getVB(iVB));
        		rg.printStackTrace(new Exception());
        	}
//        	if (A.getVon())
        }
        //rg.fixInfo("!! Calc von Formular ["+sFrame+"]");
		Count.add(Count.Calc);
        bHaupt = rA==null;
		//rg.progInfo("Calc:"+riBegriff+"/"+rVecStamm+"/"+bTimer);
		A = bHaupt ? new Aufruf(rg,rVecStamm,Static.bWeb ? iStamm:0,bBew,FomA,iProt):(Aufruf)rA;
		if (Static.bWeb && iBegBer != null && iBegBer.isPresent())
		{
			A.iBegBer=iBegBer.get();
//			A.g.fixInfo(" verwende Berechtigung "+A.iBegBer);
		}
		//A.bJavaFX=A.FomFX != null;
//		if (bHaupt && FomFX!=null)
//			A.FomFX=FomFX;
                if (AbfH != null)
                  A.AbfH = AbfH;
                if (sFrame != null)
                  A.sFrame=sFrame;
                long lMem = 0;
                if(bModellFehlt)
                {
                	A.printError(riBegriff==0 ? "Modell nicht übergeben":"Begriff zu Modell "+riBegriff+" nicht gefunden - Wahrscheinlich ist Refresh notwendig", 500);
                	return;
                }
                TabBefehle=A.posModell(riBegriff,bBegriff);
                //int iMandantVor=0;
                if (bHaupt)
		{
                  lMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                  lClockVB=A.g.lClockVB;
                  A.iVB=iVB;
//                  A.g.initJokerStt(iVB);
                  
                  dtVonVor2 = A.getVon();
                  dtBisVor2 = A.getBis();
                  A.in=in;
                  A.out=out;
                  A.TabMD=TabMD;
                  A.iZone=iZone==-1 ? A.g.getZone():iZone;
                  A.iZoneOld=A.iZone;
                  A.iThread=iThread!=null && iThread.isPresent() ? iThread.get():-1;
                  if (A.iThread>-1)
                	  A.g.fixInfo(" verwende Thread "+A.iThread);
//                  if(A.iThread>=0)
//                	  rg.setThread(iThread,this);
                  
//                  if (iZone != -1)
//                	  A.iZone=iZone;
                  
                  A.iMandantVor=A.g.getMandant();
                  //A.g.fixInfo("in/out="+A.in+"/"+A.out);
                  iMBits=A.TabModelle.getL("bits");
                  A.bMass=(iMBits&Global.cstMassExport)>0;
                  A.bTimer = iMaxTimer > 0; // entfernt am 4.10. damit Anlage passt: || iMaxTimer==-2; //Thread;
                  if (iD_Abbruch==0)
                	  A.iMaxBefehle = iMaxTimer==-2 ? 1000000:iMaxTimer;
                  A.bThread=(iMBits&Global.cstThread)>0 || iMaxTimer==-2;// || xx;
                  rg.progInfo("Calc:" + riBegriff + "/" + rVecStamm + "/" + iMaxTimer);
                  if ((iMBits&Global.cstJokerStt)>0/* || Static.bTest*/)
                    TabJokerStt=A.g.saveJokerStt((iMBits&Global.cstDelJS)>0); // JokerStt sichern und löschen
                  setDebugValues();
                  if (TabVar != null)
                	  for (TabVar.moveFirst();!TabVar.out();TabVar.moveNext())
                		if (TabVar.getI("Art")>1)
	                    {
                		  Calc.setVar(TabVar.getS("DB"),TabVar.getS("var"),TabVar.getInhalt("Obj"),0,TabVar.getI("Art"),TabVar.getI("User"),TabVar.getInhalt("Perm"),A.g.getBegBez(A.iHauptModell));
                		  A.debugInfo("<-var "+TabVar.getS("var")+":"+TabVar.getInhalt("Obj")+" bei Art="+TabVar.getI("Art"));
                		  if (webLog.bVarInfo)
                			  A.g.fixtestError("<-var "+TabVar.getS("var")+":"+TabVar.getInhalt("Obj")+" bei Art="+TabVar.getI("Art")+", user="+TabVar.getI("User"));
	                    }
                  initAll(true,TabVar);
                  A.g.checkVon();
                }
		Berechne();
		//TabBefehle.pop();
                A.g.testInfo("Modell"+A.TabModelle.getPos()+":"+A.TabModelle.getS("Bezeichnung"));
		if (bHaupt)
		{
			GaugeClose();
            if (Static.bWeb && A.VecSperre != null && A.VecSperre.size()>0)
            {
            	 A.g.exec("delete from bew_boolean where aic_bew_pool" + A.g.SQL_in(A.VecSperre) + " and aic_eigenschaft=" + A.g.iTimerSperre);
            	 A.g.fixInfo("** Sperren löschen:"+A.VecSperre);
            	 A.VecSperre.removeAllElements();
            }
            if (A.bCommit)
            	  A.g.commit2(A.bFehler);
			A.g.clockInfo2("BerechneModell "+A.TabModelle.getS("Bezeichnung")+":"+A.iAnzahl+", "+A.iAnzahlTab+" Tab, "+A.iAnzahlDel+" Del, "+A.iAnzahlRef+" Ref, mem="+Static.printZahl(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()-lMem,11),lClock);
			printClockProz(lClockVB,lClock);
			if (Static.bAll)
			if (A.bSetDebug && A.iPause != A.iAnzahl && (A.iDBits&DAE)>0)
            {
				A.debugInfo(A.iMsgArt==4 ? "unterbrochen wegen Modell-Dialog":"Modellberechnung fertig");
				Pause();
                showTabAbfragen(null);
            }
            else if (A.g.Def() && A.g.Clock2() /*&& A.FomFX==null*/ && A.FomA!=null)
                showTabAbfragen(null);
			if (Static.bAll)
      {
				DefModell.freeDlg();
				if (A.TabClock != null && A.TabClock.size()>0)
					A.TabClock.showGrid(A.TabModelle.getS("Bezeichnung"));
				if (A.bDebug && A.TabBack != null)// && !Static.bWeb)
					A.TabBack.showGrid("Back");
      }
//			A.g.setDebug(A.bDebug);
			if (A.bSetDebug)
				Aufruf.removeDebugModell(A.iHauptModell);
            A.g.setMandant(A.iMandantVor);
            if (iVB==0 && A.g.getLog()>0)
            {
            	A.setVonBis(dtVonVor2,dtBisVor2);
            	Zeitraum.PeriodeToVec(A.g,A.g.getZA(0));
            }
            A.g.loadJokerStt(TabJokerStt);
            setTabV();
          if (Static.bWeb)
          {
            A.debugInfo("<<- alle varL:"+A.TabVar.getVecSpalte("Var"));
            A.debugInfo("<<- alle varG:"+TabV.getVecSpalte("Var"));
            if (A.iStatus==0)
            	A.iStatus=200;
//            if (bPOHide) ((PopOver)FomFX).setAutoHide(true);
            if (A.sLast!=null)
              A.g.debugInfo(A.sLast);
          }
		}
	}
	
	private int r(double d,double d2)
	{
	   return d2==0 ? 0:(int)Math.round(d/d2);	
	}
	
	private void printClockProz(long lClockVB,long lClockGes)
	{
		if (A.g.Clock())
		{
			long lGes=Static.get_ms()-lClockGes-A.lClockDlg;
			if (lGes>2)
			{
				long lVB=A.g.lClockVB-lClockVB;
				long lAbf=A.lClockAbf;
//				long lDlg=A.lClockDlg;
				long lSave=A.lClockSave;
				A.g.clockInfo(A.TabModelle.getS("Bezeichnung")+": Abf/Save/VB/Rest="+r(lAbf*100,lGes)+"/"+r(lSave*100,lGes)+"/"+r(lVB*100,lGes)+"/"+r((lGes-lAbf-lSave-lVB)*100,lGes),lClockGes+A.lClockDlg);
				A.g.fixtestInfo("lAbf="+lAbf+"/"+A.TabAbfragen.sum("Dauer")+", lSave="+lSave+", lVB="+lVB);
			}
		}
	}

	public String getClockProz(long lClockVB)
	{
		long lGes=Static.get_ms()-lClockD;
		if (lGes<2)
			return "-";
		long lVB=0;
		if (A.iVB>0)
		{
//			int iPosVB=Transact.TabVB.getPos("Nr",A.iVB);
//			if (iPosVB>=0)
//				lVB=Transact.TabVB.getI(iPosVB,"clock")-lClockVB;
			Tabellenspeicher TabVB=A.g.getTabVB();
			int iPosVB=TabVB.getPos("Nr",A.iVB);
			if (iPosVB>=0)
				lVB=TabVB.getI(iPosVB,"clock")-lClockVB;
		}
		long lAbf=A.lClockAbf;
//		long lDlg=A.lClockDlg;
		long lSave=A.lClockSave;
		return (lAbf*100/lGes)+"/"+(lSave*100/lGes)+"/"+(lVB*100/lGes)+"/"+((lGes-lAbf-lSave-lVB)*100/lGes);
	}

      private static void showDebugDialog(JFrame FomA,final Global g,String s)
        {
          if (DlgDebug==null)
          {
            final JCheckBox CbxDd  = g.getCheckbox("d",true);
            final JCheckBox CbxDdt = g.getCheckbox("dt",true);
            final JCheckBox CbxDb  = g.getCheckbox("b");
            final JCheckBox CbxDs  = g.getCheckbox("s",true);
            final JCheckBox CbxDi  = g.getCheckbox("i",true);
            final JCheckBox CbxDQry= g.getCheckbox("Qry",true);
            final JCheckBox CbxDBew= g.getCheckbox("Bew",true);
            final JCheckBox CbxDMass=g.getCheckbox("Mass");
            final JCheckBox CbxDStt= g.getCheckbox("Stt");
            final JCheckBox CbxDPos= g.getCheckbox("Pos");
            final JCheckBox CbxDvon= g.getCheckbox("von",true);
            final JCheckBox CbxDbis= g.getCheckbox("bis",true);
            final JCheckBox CbxDd2 = g.getCheckbox("d2");
            final JCheckBox CbxDdt2= g.getCheckbox("dt2");
            final JCheckBox CbxDST = g.getCheckbox("ST");
            final JCheckBox CbxDs2 = g.getCheckbox("s2");
            final JCheckBox CbxDi2 = g.getCheckbox("i2");
            final JCheckBox CbxDQry2=g.getCheckbox("Qry2");
            final JCheckBox CbxDBew2=g.getCheckbox("Bew2");
            final JCheckBox CbxDTrenn  =g.getCheckbox("Trenn");
            final JCheckBox CbxDJokerDT=g.getCheckbox("J-dt");
            final JCheckBox CbxDJokerI =g.getCheckbox("J-i");
            final JCheckBox CbxDJokerS =g.getCheckbox("J-s");
            final JCheckBox CbxDVec   = g.getCheckbox("VecAic");

            final JCheckBox CbxDZone = g.getCheckbox("Zone");
            final JCheckBox CbxDMandant = g.getCheckbox("M");
            final JCheckBox CbxDVSa  = g.getCheckbox("VecStamm");
            final JCheckBox CbxDVecS = g.getCheckbox("VecString");
            final JCheckBox CbxDSave = g.getCheckbox("Save");
            final JCheckBox CbxDPbR = g.getCheckbox("Pause refresh");
            final JCheckBox CbxDAE = g.getCheckbox("Abf Ende",true);
            final JCheckBox CbxPush = g.getCheckbox("push");
            final JCheckBox CbxGauge = g.getCheckbox("gauge3");

            final JCheckBox CbxVar = g.getCheckbox("var");
            final JCheckBox CbxAnlage = g.getCheckbox("Anlage");
            final JCheckBox CbxVecBew = g.getCheckbox("VecBew");
            final JCheckBox CbxMem = g.getCheckbox("Mem");
            final JCheckBox CbxClock = g.getCheckbox("Clock");
            final JCheckBox CbxDest = g.getCheckbox("destination");
            final JCheckBox CbxFirma = g.getCheckbox("Firma");
            final JCheckBox CbxTab = g.getCheckbox("Tab");
            final JCheckBox CbxB3 = g.getCheckbox("Bool3");
            final JCheckBox CbxKSL = g.getCheckbox("kein Sleep",true);
            final JCheckBox CbxDat = g.getCheckbox("Daten");
            final JCheckBox CbxBez = g.getCheckbox("Bezeichnung",true);
            final JCheckBox CbxZBP = g.getCheckbox("ZoneBreakpoint");
            final JCheckBox CbxAIC = g.getCheckbox("Aic_Bew");
            final JCheckBox CbxDMZ = g.getCheckbox("Modelle_zeigen");
            
            final JCheckBox CbxGPS = g.getCheckbox("GPS");
            // final JCheckBox CbxNoThread = g.getCheckbox("noThread");
            final JCheckBox CbxUMRDT = g.getCheckbox("UmrechnenDT");
            final JCheckBox CbxUMRT = g.getCheckbox("UmrechnenTag");
            final JCheckBox CbxUMRH = g.getCheckbox("UmrechnenStunde");

            DlgDebug =new JDialog(new JFrame(),"Debug",true);
            JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
            g.addLabel2(Pnl,"Abbruch bei");
            g.addLabel2(Pnl,"Debug ab");
            g.addLabel2(Pnl,"Pause bei");
            for (int i=0;i<13;i++)
              Pnl.add(new JLabel());
            DlgDebug.getContentPane().add("West",Pnl);

            Pnl = new JPanel(new GridLayout(0,1,2,2));
            final Zahl SpnAbbruch = new Zahl(0);
            final Zahl SpnDebug = new Zahl(0);
            final Zahl SpnPause = new Zahl(10);
            
            Pnl.add(SpnAbbruch);
            Pnl.add(SpnDebug);
            Pnl.add(SpnPause);

            JPanel PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxDd);
                PnlCbx.add(CbxDdt);
                PnlCbx.add(CbxDb);
                PnlCbx.add(CbxDs);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxDd2);
                PnlCbx.add(CbxDdt2);
                PnlCbx.add(CbxDST);
                PnlCbx.add(CbxDs2);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxDi);
                PnlCbx.add(CbxDQry);
                PnlCbx.add(CbxDBew);
                PnlCbx.add(CbxDMass);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxDi2);
                PnlCbx.add(CbxDQry2);
                PnlCbx.add(CbxDBew2);
                PnlCbx.add(CbxDTrenn);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxDStt);
                PnlCbx.add(CbxDPos);
                PnlCbx.add(CbxDvon);
                PnlCbx.add(CbxDbis);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxDJokerDT);
                PnlCbx.add(CbxDJokerI);
                PnlCbx.add(CbxDJokerS);
                PnlCbx.add(CbxDVec);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxDZone);
                PnlCbx.add(CbxDMandant);
                PnlCbx.add(CbxDVSa);
                PnlCbx.add(CbxDVecS);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxDSave);
                PnlCbx.add(CbxDPbR);
                PnlCbx.add(CbxDAE);
                PnlCbx.add(CbxPush);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxGauge);
                PnlCbx.add(CbxVar);
                PnlCbx.add(CbxAnlage);
                PnlCbx.add(CbxVecBew);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxMem);
                PnlCbx.add(CbxClock);
                PnlCbx.add(CbxDest);
                PnlCbx.add(CbxFirma);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxTab);
                PnlCbx.add(CbxB3);
                PnlCbx.add(CbxKSL);
                PnlCbx.add(CbxDat);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxBez);
                PnlCbx.add(CbxZBP);
                PnlCbx.add(CbxAIC);
                PnlCbx.add(CbxDMZ);
                Pnl.add(PnlCbx);
                PnlCbx=new JPanel(new GridLayout(0,4,2,2));
                PnlCbx.add(CbxGPS);
                // PnlCbx.add(CbxNoThread);
                PnlCbx.add(CbxUMRDT);
                PnlCbx.add(CbxUMRT);
                PnlCbx.add(CbxUMRH);
                Pnl.add(PnlCbx);

            DlgDebug.getContentPane().add("Center",Pnl);
            JButton BtnDebugOk=g.getButton("Ok");
            JButton BtnDebugAbbruch=g.getButton("Abbruch");
            BtnDebugOk.setEnabled(true);
            BtnDebugAbbruch.setEnabled(true);
            DlgDebug.getRootPane().setDefaultButton(BtnDebugOk);
            JPanel PnlS=new JPanel(new GridLayout());
            PnlS.add(BtnDebugOk);
            PnlS.add(BtnDebugAbbruch);
            DlgDebug.getContentPane().add("South",PnlS);
            DlgDebug.pack();
            	BtnDebugOk.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          iDBitsL=(CbxDd.isSelected()?DD:0)+(CbxDdt.isSelected()?DDT:0)+(CbxDb.isSelected()?DB:0)+(CbxDs.isSelected()?DS:0)+
                              (CbxDi.isSelected()?DI:0)+(CbxDQry.isSelected()?DQRY:0)+(CbxDBew.isSelected()?DBEW:0)+(CbxDMass.isSelected()?DMASS:0)+
                              (CbxDStt.isSelected()?DSTT:0)+(CbxDPos.isSelected()?DPOS:0)+(CbxDvon.isSelected()?DVON:0)+(CbxDbis.isSelected()?DBIS:0)+
                              (CbxDd2.isSelected()?DD2:0)+(CbxDdt2.isSelected()?DDT2:0)+(CbxDST.isSelected()?DST:0)+(CbxDs2.isSelected()?DS2:0)+
                              (CbxDi2.isSelected()?DI2:0)+(CbxDQry2.isSelected()?DQRY2:0)+(CbxDBew2.isSelected()?DBEW2:0)+(CbxDTrenn.isSelected()?DTRENN:0)+
                              (CbxDJokerDT.isSelected()?DJDT:0)+(CbxDJokerI.isSelected()?DJI:0)+(CbxDJokerS.isSelected()?DJS:0)+(CbxDVec.isSelected()?DVEC:0)+
                              (CbxDZone.isSelected()?DZONE:0)+(CbxDVSa.isSelected()?DVSA:0)+(CbxDVecS.isSelected()?DVECS:0)+(CbxDSave.isSelected()?DSAVE:0)+
                              (CbxDMandant.isSelected()?DMANDANT:0)+(CbxDPbR.isSelected()?DPR:0)+(CbxDAE.isSelected()?DAE:0)+(CbxPush.isSelected()?DPUSH:0)+
                              (CbxGauge.isSelected()?DGAUGE:0)+(CbxVar.isSelected()?DVAR:0)+(CbxAnlage.isSelected()?DANL:0)+(CbxVecBew.isSelected()?DVECB:0)+
                              (CbxMem.isSelected()?DMEM:0)+(CbxClock.isSelected()?DC:0)+(CbxDest.isSelected()?DEST:0)+(CbxFirma.isSelected()?FIRMA:0)+
                              (CbxTab.isSelected()?TAB:0)+(CbxB3.isSelected()?DB3:0)+(CbxKSL.isSelected()?KSL:0)+(CbxDat.isSelected()?DAT:0)+
                              (CbxBez.isSelected()?BEZ:0)+(CbxZBP.isSelected()?ZBP:0)+(CbxAIC.isSelected()?AIC:0)+(CbxDMZ.isSelected()?DMZ:0)+
                              (CbxGPS.isSelected()?DGPS:0)+(CbxUMRDT.isSelected()?UMRDT:0)+(CbxUMRT.isSelected()?UMRT:0)+(CbxUMRH.isSelected()?UMRH:0);//+(CbxNoThread.isSelected()?NTH:0);
                          iD_Abbruch=SpnAbbruch.intValue();
                          iD_Debug=SpnDebug.intValue();
                          iD_Pause=SpnPause.intValue();
                          DlgDebug.setVisible(false);
                        }
                });
            BtnDebugAbbruch.addActionListener(new ActionListener()
            {
                  public void actionPerformed(ActionEvent ev)
                  {
                    bAbbruch = true;
                    DlgDebug.setVisible(false);
                  }
            });
          }
          DlgDebug.setTitle("debug "+s);
          Static.centerComponent(DlgDebug, FomA);
          DlgDebug.setVisible(true);
        }
        
	private void setDebugValues()
	{
          bAbbruch=false;
	  A.bCommit=(A.TabModelle.getI("bits")&Global.cstCommit)>0;
	  if (A.bCommit)
	    A.g.start();
	  // TODO Debug nur bei bestimmten Modellen
		A.bSetDebug=A.g.Debug() || Aufruf.VecModelle != null && Aufruf.VecModelle.contains(A.iHauptModell);
//		A.g.fixInfo("bSetDebug="+A.bSetDebug);
		A.iDBits=iDBitsL;// | (A.g.Def() ? DMZ:0);
		if (A.bSetDebug)// || Aufruf.VecBreak!=null || Aufruf.VecDebug!=null || Aufruf.VecSpalten!=null || Aufruf.VecBefehl!=null || Aufruf.VecVar!=null)
		{
//			A.g.fixtestError("Debug vorbereiten für "+A.g.getBeg(A.iHauptModell));
                  //A.g.testInfo("Debug vorbereiten");
                  //SQL Qry=new SQL(rg);
                          //sModell=Qry.getString("select defbezeichnung from begriff"+(bBegriff?" where aic_begriff=":SQL.join2("modell","begriff")+" where aic_modell=")+riBegriff);
                          //rg.progInfo("sModell="+sModell+" bei "+riBegriff+"/"+bBegriff);
                          A.debugInfo("Berechne Modell "+A.TabModelle.getS("Bezeichnung")+(A.VecStamm==null || A.VecStamm.size()==0?"":
                              (A.VecStamm.size()==1?(Sort.geti(A.VecStamm.elementAt(0))==0 ? " ohne":" mit")+(bBew ? "Bewegungssatz":" Stammsatz "):(bBew ? "mit Bewegungssätze ":" mit Stammsätze "))+(bBew ? ""+A.VecStamm:VecStammToString(A.VecStamm)))+(A.iVB>0 ? ", VB="+A.iVB:"")+
                                      ((Transact.iInfos&Transact.INFO1)>0?"/"+Static.printZahl(Runtime.getRuntime().totalMemory(),11)+"/"+Static.printZahl(Runtime.getRuntime().freeMemory(),11):""));
                          //Qry.free();

			A.g.debugInfo("VecStamm="+A.VecStamm);
                        if (Static.bAll && Static.bX11)
                        	showDebugDialog(A.FomA, A.g, A.TabModelle.getS("Bezeichnung"));
                        if (Static.bX11 || iD_Abbruch>0)
                        {
                          A.bAbbruch = bAbbruch;
                          //Parameter Par= new Parameter(A.g,"Modell");
                          //Par.getParameter("Option",true,false);
                          //if (Par.bGefunden)
                          //{
                          //A.g.progInfo("Parameter laden:"+Par.int1+"/"+Par.int2+"/"+Par.int3+"/"+Par.int4);
                          A.iMaxBefehle = iD_Abbruch;// SpnAbbruch.intValue();
                          A.iDebug = iD_Debug; // SpnDebug.intValue();
                          A.iPause = iD_Pause; // SpnPause.intValue();
                          Static.clearError();
                          if (A.iDebug == 0)
                          {
                        	  A.bDebug=true;
                        	  A.debugInfo("   **   Calc ["+A.g.getBegBez(A.iHauptModell)+"] mit Vec="+A.VecStamm+", VB="+A.iVB+" -> ZR="+A.g.getVB(A.iVB));
                        	  A.debugInfo("  Berechtigung bei "+A.g.getBeg(A.iHauptModell)+" laut "+A.g.getBeg(A.iBegBer)+": "+A.g.getBerechtigt(A.iBegBer));
                          }
                        }
                        A.iDBits = iDBitsL;
			//}
			//Par.free();
                        if ((A.iDBits&TAB)>0)
                        {
                          A.TabDebug = new Tabellenspeicher(A.g, new String[] {"Nr", "Befehl","Zeile","Spalteninfo"});
                          cD(DC,"Clock");cD(DMEM,"total Memory");cD(DMEM,"free Memory");
                          cD(DD,"d");cD(DD2,"d2");cD(DDT,"dt");cD(DDT2,"dt2");cD(DB,"b");cD(DB3,"bool3");cD(DI,"i");cD(DI2,"i2");cD(DQRY,"Qry");cD(DQRY2,"Qry2");cD(DBEW,"Bew");cD(DBEW2,"Bew2");
                          cD(DSTT,"Stt");cD(DMASS,"Mass");cD(DPOS,"Pos");cD(DS,"s");cD(DS2,"s2");cD(DVON,"von");cD(DBIS,"bis");cD(DST,"Stichtag");
                          cD(DTRENN,"Trenn");cD(DJDT,"Joker-Stichtag");cD(DJS,"sJoker");cD(DJI,"iJoker");cD(DVEC,"VecAic");
                          cD(DZONE,"Zone");cD(DEST,"Ziel");cD(FIRMA,"Firma");cD(DAT,"Daten");cD(DVSA,"VecStamm");cD(DVECS,"VecString");cD(DVECB,"VecBew");
                          cD(DMANDANT,"Mandant");cD(DANL,"Anlage");/*cD(DVAR,"Var");*/cD(DSAVE,"Save");cD(DGPS,"GPS");
                        }
		}
		else
		{
			A.iPause=-1;
		}
	}

        private void cD(long l,String s)
        {
          if ((A.iDBits&l)>0)
            A.TabDebug.addTitel(s);
        }

	@SuppressWarnings("unchecked")
	private void BerechneZeitbereich(String rsPeriode)
	{
		A.iNr++;
                sPeriode=rsPeriode;
                if (sPeriode==null || sPeriode.equals("offen"))
                    sPeriode=A.g.getZA(A.iVB);
                boolean b=(A.TabModelle.getI("bits")&Global.cstMitSo)>0;
                Vector<Timestamp> VecPer=Zeitraum.PeriodeToVec2(A.g,A.iVB,rsPeriode,b,b);
		Vector<Timestamp> Vec= VecPer==null? null:new Vector<Timestamp>(VecPer);
		if(Vec == null)
		{
			iPer=-1;
			A.printInfo(iPer+"."+rsPeriode+":"+A.getVon()+" - "+A.getBis());
			BerechneModell();
		}
		else
		{
			int iAnz=Vec.size()-1;
			iPer=0;
                        /*Gauge GauP=null;
                        if (A.bDebug && Gauge.getAnzahl()<4 || Static.bX11 && A.g.Info() && bHaupt)
                          GauP=new Gauge(A.TabModelle.getS("Bezeichnung")+" - Periode",0,iAnz,"",A.g,false);
                        if (GauP != null && !bHaupt)
                          GauP.noInfo();*/
			while (!A.bFehler && !A.bAbbruch && iPer<iAnz)
			{
				A.setVonBis((Timestamp)Vec.elementAt(iPer),(Timestamp)Vec.elementAt(iPer+1));
				//A.g.fixtestError("BerechneZeitbereich:"+A.g.getVB());
				if (iPer>0 && A.iNr==1)
                                {
                                  initAll(false,null);
                                  A.bReCalc=true;
                                }
				else
                                {
                                  iJa=0;
                                  iNein=0;
                                  bModellende = false;
                                }
                                //if (GauP!=null)
                                //  GauP.setText(Static.FormatTS3(A.g.getVon(),rsPeriode),iPer);
				A.printInfo(iPer+"."+rsPeriode+": "+A.getVon()+" - "+A.getBis());
				BerechneModell();
				if (A.iNr==1)
					A.bAbbruch = false;	// !!! Abbruch wieder zurücksetzen
				iPer++;
			}
                        //if (GauP != null)
                        //  GauP.free();
		}
		//A.g.printInfo("Ende"+A.iNr+" - BerechneZeitbereich:"+A.g.getVon()+" - "+A.g.getBis());
		A.iNr--;
	}

	/*private void BerechneZeitbereich(String rsPeriode)
	{
		java.sql.Timestamp rdtVon=A.g.getVon();
		java.sql.Timestamp rdtBis=A.g.getBis();
		A.iNr++;
		DateWOD dtVon=rdtVon==null ? new DateWOD(1970,1,1):new DateWOD(rdtVon);
		//long lStart=dtVon.getTimeInMilli();
		java.sql.Timestamp dtBis=rdtBis==null ? new DateWOD().toTimestamp():rdtBis;
		A.g.printInfo("Anfang"+A.iNr+" - "+A.TabModelle.getS("Bezeichnung")+"("+A.iAnzahl+"):"+dtVon+" - "+dtBis);
		//DateWOD dtBis=new DateWOD(rdtBis);
		if (rsPeriode.equals("Monat"))
			dtVon.setDate(1);
		else if (rsPeriode.equals("Quartal"))
		{
			dtVon.setDate(1);
			dtVon.setMonth(((dtVon.getMonth()-1)/3)*3+1);
		}
		else if (rsPeriode.equals("Jahr"))
		{
			dtVon.setDate(1);
			dtVon.setMonth(1);
		}
		int i=0;
		while (!A.bAbbruch && dtVon.getTimeInMilli()<dtBis.getTime())
		{
			i++;
			//A.g.printInfo("1:von="+A.g.getVon()+", bis="+A.g.getBis());
			A.g.setVon(rdtVon==null || dtVon.getTimeInMilli()>rdtVon.getTime() ? dtVon.toTimestamp():rdtVon);
			//A.g.printInfo("2:von="+A.g.getVon()+", bis="+A.g.getBis());
			if (rsPeriode.equals("Tag"))
				dtVon.tomorrow();
			else if (rsPeriode.equals("Monat"))
				dtVon.nextMonth();
			else if (rsPeriode.equals("Quartal"))
			{
				dtVon.nextMonth();
				dtVon.nextMonth();
				dtVon.nextMonth();
			}
			else if (rsPeriode.equals("Jahr"))
				dtVon.nextYear();
			else
				dtVon.setTimeInMilli(dtBis.getTime());
			A.g.setBis((rdtBis==null || dtVon.getTimeInMilli()<dtBis.getTime()) ? dtVon.toTimestamp():rdtBis);
			//A.g.printInfo("3:von="+A.g.getVon()+", bis="+A.g.getBis());
			if (i>1)
			{
				initAll();
			}
				A.g.printInfo(i+". Berechnung:"+A.g.getVon()+" - "+A.g.getBis());

			BerechneModell();
			if (A.iNr==1)
				A.bAbbruch = false;	// !!! Abbruch wieder zurücksetzen
		}

		A.g.setVon(rdtVon);
		A.g.setBis(rdtBis);
		A.g.printInfo("Ende"+A.iNr+" - BerechneZeitbereich:"+A.g.getVon()+" - "+A.g.getBis());
		A.iNr--;
	}*/

	public void finalize()
	{
		Count.sub(Count.Calc);
	}

	/*public void Abbruch()
	{
		Static.printError("Calc.Abbruch(): Abbruch durch Benutzer");
		A.bAbbruch = true;
	}*/

        public String getErgebnis()
        {
          return A.sReg;
        }

        public int getMsgArt()
        {
          return A.iMsgArt;
        }
        
        public JSONObject getJSON()
        {
        	return A.jO.length()>0 ? A.jO:null;
        }
        
        public Tabellenspeicher getTabBack()
        {
        	return A.TabBack;
        }
        
        public Tabellenspeicher getTabClock()
        {
        	return A.TabClock;
        }
        public boolean getBool()
        {
        	return A.bReg;
        }

	public String Fehler()
	{
		return A.bFehler ? A.sFehler:null;
	}
	
	public long DlgZeit()
	{
		return A.lClockDlg;
	}

    public String Debug()
    {
    	String sRet=A.sDebug;
    	A.sDebug="";
    	return sRet;
    }
	
	public int Status()
	{
		return A.iStatus;
	}
	
	public String getInfo()
	{
		return A.sInfo;
	}
	
	public long clockAbf()
	{
		return A.lClockAbf;
	}


	public int Anzahl()
	{
		return A.iAnzahl;
	}
	
//	private Tabellenspeicher getAbf()
//	{
//	   return (Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage"+(A.TabAbfragen.isNull("Filter") ? "1":"2"));
//	}

	public Tabellenspeicher getTab(int iAbfrage)
	{
		if (!A.TabAbfragen.posInhalt("Aic",iAbfrage))
		{
			A.g.printInfo(1,"Abfrage "+iAbfrage+" wurde nicht erstellt!");
			return null;
		}
		Tabellenspeicher Tab=A.getAbf();
		Tab.moveFirst();
		return  Tab;
	}

        public Tabellenspeicher getSpalten()
        {
          return A.TabSpalten;
        }
        
        public Tabellenspeicher getTabAbfragen()
        {
        	return new Tabellenspeicher(A.g,A.TabAbfragen);
        }

	private void initAll(boolean bInitVar,Tabellenspeicher TabVar)
	{
		//A.g.fixtestError("initAll "+bInitVar);
		iJa=0;
		iNein=0;
		bModellende = false;
		bPause=false;
		A.bAbbruch = bAbbruch;
		bAbbruch=false;
		A.bFehler = false;
		A.sFehler = "";
		iWAnz=0;
		A.iPos=0;
		A.iReg=A.getStamm();
		A.iQryReg=A.iReg;
		A.iBewReg=0;
		A.iBewReg2=0;
		A.iReg2=0;
		A.iQryReg2=0;
		A.dReg=0;
		A.dReg2=0;
		A.dtReg=null;
		A.dtReg2=null;
		A.bReg=false;
                A.sReg="";
                A.sReg2="";
                A.iZone=A.g.getZone();
                A.iZoneOld=A.iZone;
                A.iMReg=A.g.getMandant();
                A.g.defInfo2("initAll->"+A.g.MandantBez(A.iMReg));
                A.sDest=null;
                //A.g.fixInfo("TabVar:"+A.TabVar+"/"+A.g.TabVar);
        if (bInitVar)
        {
                if (A.TabVar!=null && A.g.TabVar==null)
                  A.TabVar.clearAll();
                else if (A.g.TabVar!=null)
                  A.TabVar=new Tabellenspeicher(A.g,A.g.TabVar);
                if (A.TabVar==null) A.TabVar = newTabVar(A.g,false);
                if (TabV==null) 
                	TabV = newTabVar(A.g,true);
                else
                {
                	String sDB="#"+A.g.getDB();
                	for (int i=0;i<TabV.size();i++)
                	{
                		int iArt=TabV.getI(i,"Art");
                		if (iArt>0 && TabV.getS(i,"ID").endsWith(sDB) && (iArt>2 || A.g.getLog()==TabV.getI(i,"Logging")))
                		{
                			String sVar=TabV.getS(i,"Var");
                			if (sVar.startsWith("-"))
                			{
                				String s="Variable "+sVar+" bereits entfernt, Wert wäre "+TabV.getS(i,"Wert");
                				A.debugInfo(s);
                        		if (webLog.bVarInfo)
                        			A.g.fixtestError(s);
                			}
                			else
                			{
                			// {"Var","Wert","Stt","Art","perm","Logging"});
	                			int iPos=A.TabVar.newLine();
	                			A.TabVar.setInhalt(iPos, "Var", sVar);
	                			A.TabVar.setInhalt(iPos, "Wert", TabV.getInhalt("Wert", i));
	                			A.TabVar.setInhalt(iPos, "Stt", TabV.getInhalt("Stt", i));
	                			A.TabVar.setInhalt(iPos, "Art", iArt);
	                			A.TabVar.setInhalt(iPos, "perm", TabV.getInhalt("perm", i));
	                			A.TabVar.setInhalt(iPos, "Logging", TabV.getI(i,"Logging"));
	                			A.TabVar.setInhalt(iPos, "Global","x");
	                			A.debugInfo("-> varG "+TabV.getS(i,"Var")+": "+TabV.getS(i,"Wert")+" (Art="+iArt+")");
                			if (iArt==1)
                			{
//	                				TabV.setInhalt(i, "ID", "-"+TabV.getS(i,"ID"));
//	                				TabV.setInhalt(i, "Var", "#"+TabV.getS(i,"Var"));
	                				delVar(i,"Art=1",A.g.getBegBez(A.iHauptModell));
	                				A.debugInfo("-> entferne var->"+TabV.getS(i,"Var"));
	                				if (webLog.bVarInfo)
	                        			  A.g.fixtestError("-> entferne var->"+TabV.getS(i,"Var"));
	                			}     
                			}
                		}
                	}
                	if (TabVar != null)
                  	  for (TabVar.moveFirst();!TabVar.out();TabVar.moveNext())
                  		if (TabVar.getI("Art")==1)
  	                    {
//                  		  Calc.setVar(TabVar.getS("DB"),TabVar.getS("var"),TabVar.getInhalt("Obj"),0,TabVar.getI("Art"),TabVar.getI("User"),TabVar.getInhalt("Perm"));
                  			int iPos=A.TabVar.newLine();
                  			A.TabVar.setInhalt(iPos, "Var", TabVar.getS("Var"));
                			A.TabVar.setInhalt(iPos, "Wert", TabVar.getInhalt("Obj"));
                			A.TabVar.setInhalt(iPos, "Stt", 0);
                			A.TabVar.setInhalt(iPos, "Art", TabVar.getI("Art"));
                			A.TabVar.setInhalt(iPos, "perm", TabVar.getInhalt("perm"));
                			A.TabVar.setInhalt(iPos, "Logging", TabVar.getI("User"));
//                			A.TabVar.addInhalt("Global","x");
                  		  A.debugInfo("-> var "+TabVar.getS("var")+":"+TabVar.getInhalt("Obj")+" (Art="+TabVar.getI("Art"));
                  		  if (webLog.bVarInfo)
                  			  A.g.fixtestError("-> var "+TabVar.getS("var")+":"+TabVar.getInhalt("Obj")+" bei Art="+TabVar.getI("Art")+", user="+TabVar.getI("User"));
  	                    }
                }
                A.debugInfo("->> alle var:"+A.TabVar.getVecSpalte("Var"));
                //if (A.TabVar != null)
                //  A.TabVar.showGrid(A.TabModelle.getS("Bezeichnung"));
        }
	}
	
	public static void delVar(int i,String sArt,String sModell)
	{
		if (TabV.getI(i,"Art")>0)
		{
			TabV.setInhalt(i, "Art", 0);
			TabV.setInhalt(i, "ID", "-"+TabV.getS(i,"ID"));
			TabV.setInhalt(i, "Var", "-"+TabV.getS(i,"Var"));
			TabV.setInhalt(i, "delDate",new Date());
			TabV.setInhalt(i, "delArt",sArt);
			TabV.setInhalt(i, "delModell",sModell);
		}
	}
	
	private static String getTabV_ID(String sDB,String sVar,int iUser,int iArt)
	{
		return sVar+(iArt==2 ? "@"+iUser:"")+"#"+sDB;
	}
	
	private void setTabV()
	{
		for (int i=0;i<A.TabVar.size();i++)
		{
    		int iArt=A.TabVar.getI(i,"Art");
			if (iArt>1 || !A.TabVar.isNull(i,"perm"))
			{
				String sVar=A.TabVar.getS(i,"Var");
				setVar(A.g.getDB(),sVar,A.TabVar.getInhalt("Wert",i),A.TabVar.getI(i,"Stt"),iArt,A.TabVar.getI(i,"Logging"),A.TabVar.getInhalt("perm",i),A.g.getBegBez(A.iHauptModell));
				A.debugInfo("<- var "+A.TabVar.getS(i,"Var")+": "+A.TabVar.getS(i,"Wert")+" (Art="+A.TabVar.getI(i,"Art")+")");
				if (webLog.bVarInfo)
					A.g.fixtestError("<-var "+A.TabVar.getS(i,"Var")+":"+A.TabVar.getS(i,"Wert")+" bei Art="+A.TabVar.getI(i,"Art")+", log="+A.TabVar.getI(i,"Logging"));
//				String sID=getTabV_ID(A.g,sVar,A.TabVar.getI(i,"Logging"),iArt);
//				int iPos=TabV.getPos("ID",sID);
//				if (iPos<0)
//				{
//					iPos=TabV.newLine();
//					TabV.setInhalt(iPos, "Var", sVar);
//					TabV.setInhalt(iPos, "ID",sID);
//				}
//				TabV.setInhalt(iPos, "Wert", A.TabVar.getInhalt("Wert",i));
//				TabV.setInhalt(iPos, "Stt", A.TabVar.getInhalt("Stt",i));
//				TabV.setInhalt(iPos, "Art", iArt);
//				TabV.setInhalt(iPos, "perm", A.TabVar.getInhalt("perm",i));
//				TabV.setInhalt(iPos, "Logging", A.TabVar.getI(i,"Logging"));
			}
		}
	}
	
	public static String getVar(Global g,String sVar)
	{
		String sDB="#"+g.getDB();
		if (TabV != null)
		  for (int i=0;i<TabV.size();i++)
		  {
			int iArt=TabV.getI(i,"Art");
			if (iArt>2 && TabV.getS(i,"ID").equals(sVar+sDB) || (iArt==2 && TabV.getS(i,"ID").equals(sVar+"@"+g.getLog()+sDB))) //g.getLog()==TabV.getI(i,"Logging")))
			{
				Object Obj=  TabV.getInhalt("Wert",i);
				if (Obj==null)
					return "is null";
				else if (Obj instanceof String)
					return "like '"+TabV.getS(i,"Wert")+"'";
				else if (Obj instanceof Integer)
					return "="+TabV.getI(i,"Wert");
				else if (Obj instanceof Vector)
					return g.SQL_in((Vector)Obj);
				else
				{
					g.printError("Fehler bei Calc.getVar mit "+sVar+": "+Static.print(Obj));
					return "xx";
				}
			}
		  }
		return "is null";
	}
	
	public static void setVar(String sDB,String sVar,Object Obj,int iStt,int iArt,int iLog,Object oPerm,String sModell)
	{
		if (TabV==null)
			TabV = newTabVar(null,true);
		String sID=getTabV_ID(sDB,sVar,iLog,iArt);
		int iPos=TabV.getPos("ID",sID);
		if (iPos<0)
		{
			if (Obj==null)
			{
				Static.printError("Variable "+sVar+" ohne Wert übergeben");
				return;
			}
			iPos=TabV.newLine();
			TabV.setInhalt(iPos, "Var", sVar);
			TabV.setInhalt(iPos, "ID",sID);
			TabV.setInhalt(iPos, "Nr", iPos);
			TabV.setInhalt(iPos, "Date",new Date());
			TabV.setInhalt(iPos, "VarO", sVar);
			TabV.setInhalt(iPos, "WertO", Obj);
		}
		TabV.setInhalt(iPos, "Wert", Obj);
		TabV.setInhalt(iPos, "Stt", iStt==0 ? null:iStt);
		TabV.setInhalt(iPos, "Art", iArt);
		TabV.setInhalt(iPos, "perm", oPerm);
		TabV.setInhalt(iPos, "Logging", iLog);
		if (Obj==null)
			delVar(iPos,"setVar",sModell);
	}
	
	public static int delVarDB(Global g)
	{
		if (TabV==null)
			return 0;
		if (webLog.bVarInfo)
			g.fixtestError("delVar1 auf "+g.getDB());
		int iAnz=0;
		for (int i=0;i<TabV.size();i++)
		{
			if (TabV.getI(i,"Art")>0 && !TabV.getS(i,"Var").startsWith("-") && TabV.getS(i,"ID").endsWith("#"+g.getDB()))
			{
				if (webLog.bVarInfo)
					g.fixtestError(" delVar "+TabV.getS(i,"Var")+" mit Art="+TabV.getI(i,"Art"));
//				TabV.setInhalt(i, "ID", "-"+TabV.getS(i,"ID"));
				delVar(i,"delVar",null);
//				TabV.setInhalt(i, "Var", "-"+TabV.getS(i,"Var"));
				iAnz++;
			}
		}
		return iAnz;
	}
	
	public static int delVar(Global g,String sVar,String sArt)
	{
		if (TabV==null)
			return 0;
		if (webLog.bVarInfo)
			g.fixtestError("delVar2 auf "+g.getDB());
		int iAnz=0;
		for (int i=0;i<TabV.size();i++)
		{
			if (TabV.getI(i,"Art")>0 && TabV.getS(i,"Var").equals(sVar) && TabV.getS(i,"ID").endsWith("#"+g.getDB()))
			{
				if (webLog.bVarInfo)
					g.fixtestError(" delVar "+TabV.getS(i,"Var")+" mit Art="+TabV.getI(i,"Art"));
//				TabV.setInhalt(i, "ID", "-"+TabV.getS(i,"ID"));
				delVar(i,sArt,null);
//				TabV.setInhalt(i, "Var", "."+TabV.getS(i,"Var"));
				iAnz++;
			}
		}
		return iAnz;
	}

	public void reCalc(int riStamm,boolean bZwingend)
	{
		reCalc(Static.AicToVec(riStamm),bZwingend,0,null,null,0,-1);
	}

	private void Berechne()
	{
		iPeriode = A.TabModelle.getI("iPeriode");
                A.printInfo("Modellberechnung von "+A.TabModelle.getS("Bezeichnung")+"("+A.TabModelle.getPos()+")"+A.TabModelle.getS("sPeriode")+(iPeriode>0?" -  von "+A.getVon()+" bis "+A.getBis():""));
		//java.sql.Timestamp dtVonVor=A.g.getVon();
		//java.sql.Timestamp dtBisVor=A.g.getBis();
		//java.sql.Date dtStichtag=A.g.getStichtag();
		if (iPeriode>0)
			if (!Static.bWeb && A.bTimer && bHaupt && (A.getVon()==null || A.getBis()==null))
				A.printError("Calc: Modell "+A.TabModelle.getS("Bezeichnung")+" hat keinen Zeitraum für "+A.TabModelle.getS("sPeriode")+"-Modell bei Stamm="+VecStammToString(A.VecStamm),400);
			else
				BerechneZeitbereich((A.TabModelle.getI("bits")&Global.cstPeriodeM)>0 ? A.g.getZA(A.iVB):A.TabModelle.getS("sPeriode"));
		else
                {
                  sPeriode=A.g.getZA(A.iVB);
                  BerechneModell();
                }
                if (GauZ != null && (A.iDBits&DGAUGE)>0)
                {
                  GauZ.free();
                  GauZ=null;
                }
		//A.g.setStichtag(dtStichtag);
		//A.g.setVon(dtVonVor);
		//A.g.setBis(dtBisVor);
	}
	
	public void reCalc(Vector VecStamm,boolean bZwingend)
    {
      reCalc(VecStamm,bZwingend,0,null,null,0,-1);
    }
	
//    public void reCalc(Vector VecStamm,boolean rbBew,boolean bZwingend)
//    {
//      reCalc(VecStamm,rbBew,bZwingend,0,null,null);
//    }
    
//    public void reCalc(Vector VecStamm,boolean bZwingend,int iAbfrage,ObjectInputStream in,ObjectOutputStream out)
//    {
//        reCalc(VecStamm,false,bZwingend,iAbfrage,in,out);
//    }
        
	@SuppressWarnings("unchecked")
	public void reCalc(Vector VecStamm,boolean bZwingend,int iAbfrage,ObjectInputStream in,ObjectOutputStream out,int iVB,int iZone)
	{
		//bBew=rbBew;
		if (bZwingend || (!A.VecStamm.containsAll(VecStamm)))
		{
			//A.g.progInfo("reCalc: "+A.TabModelle.getS("Bezeichnung")+" mit Stamm="+VecStamm);
			long lClockVB=A.g.lClockVB;
			A.lClockAbf=0;
			A.lClockSave=0;
			A.lClockDlg=0;
			A.sDebug="";
			A.iZone=iZone==-1 ? A.g.getZone():iZone;
			A.printInfo("reCalc: "+A.TabModelle.getS("Bezeichnung")+" mit Stamm="+VecStamm);
                        A.in=in;
                        A.iVB=iVB;
                        A.out=out;
			long lClock = Static.get_ms();
                        lClockD=lClock;
			if (bBew || (A.TabModelle.getI("bits")&Global.cstBew)>0)
			{
				A.VecStamm=null;
				A.VecBewPool=Sort.getIntVec(VecStamm);
			}
			else
			{
				A.VecStamm=VecStamm;
				A.VecBewPool=null;
			}
                        A.VecAic=null;
			setDebugValues();
			for(A.TabAbfragen.moveFirst();!A.TabAbfragen.eof();A.TabAbfragen.moveNext())
                        {
                          A.TabAbfragen.setInhalt("Dauer",null);
                          A.TabAbfragen.setInhalt("Anzahl",null);
                          FilterBack();
                          if(A.TabAbfragen.getI("Aic") == iAbfrage) {
                            ((Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1")).clearAll();
                            //A.TabAbfragen.clearInhalt2();
                          }
                          else
                            ((Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1")).moveFirst();
                        }
                        if (A.iMReg!=A.g.getMandant())
                        {
                          if (A.g.TabMandanten==null)
                            A.g.LoadMandant();
                          A.bM_change=true;
                          A.g.fixInfo("Mandant geändert von "+A.g.TabMandanten.getBezeichnungS(A.iMReg)+" auf "+A.g.TabMandanten.getBezeichnungS(A.g.getMandant()));
                          A.TabSpalten.clearAll();
                          for(A.TabAbfragen.moveFirst();!A.TabAbfragen.eof();A.TabAbfragen.moveNext())
                          {
                                  ((Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1")).clearAll();
                                  A.iAnzahlDel++;
                          }
                          A.TabAbfragen.clearAll();
                        }
                        else
                        {
                        	A.bM_change=false;
                        	if (A.TabAbfragen != null)
                        	 for(A.TabAbfragen.moveFirst();!A.TabAbfragen.out();)
                        	  if (A.TabAbfragen.getI("Bew")>0)
                        	  {
                        		while (A.TabSpalten.posInhalt("Abfrage",A.TabAbfragen.getI("Aic")))
            				  	  A.TabSpalten.clearInhalt();

            				    ((Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1")).clearAll();
            				    if (!A.TabAbfragen.isNull("Abfrage2"))
            				    	((Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage2")).clearAll();
            				    A.TabAbfragen.clearInhalt();
                        	  }
                        	  else
                        		  A.TabAbfragen.moveNext();
//                        	A.g.fixtestError("reCalc: "+A.TabAbfragen.size()+" Abfragen mit "+A.TabSpalten.size()+" Spalten noch vorhanden");
                        }
			initAll(true,null);
			A.initProt();
			A.iAnzahl=0;
                        A.bReCalc=true;
                        dtVonVor2=A.getVon();
                        dtBisVor2=A.getBis();
                        A.iMandantVor=A.iMReg;
                        A.g.checkVon();
			Berechne();
                        GaugeClose();
			//if (dtVonVor2 != null)
                        A.g.setMandant(A.iMandantVor);
            if (iVB==0)
            {
            	A.setVonBis(dtVonVor2,dtBisVor2);
                        //if (dtBisVor2 != null)
                Zeitraum.PeriodeToVec(A.g,A.g.getZA(0));
            }
                        if (A.bCommit)
                        	A.g.commit2(A.bFehler);
			//A.g.clockInfo("BerechneModell "+A.TabModelle.getS("Bezeichnung"),lClock);
                        A.g.clockInfo2("reCalc "+A.TabModelle.getS("Bezeichnung")+":"+A.iAnzahl+", "+A.iAnzahlTab+" Tab, "+A.iAnzahlDel+" Del, "+A.iAnzahlRef+" Ref",lClock);
                        printClockProz(lClockVB,lClock);
                        //A.g.testInfo("Def="+A.g.Def()+", clock2="+A.g.Clock2());
			if (A.bSetDebug && A.iPause != A.iAnzahl && (A.iDBits&DAE)>0)
                        {
                          Pause();
                          showTabAbfragen(null);
                        }
                        else if (A.g.Def() && A.g.Clock2()/* && A.FomFX==null*/ && A.FomA!=null)
                          showTabAbfragen(null);
			if (A.bSetDebug)
				Aufruf.removeDebugModell(A.iHauptModell);
      if (Static.bAll)
			  DefModell.freeDlg();
//			A.g.setDebug(A.bDebug);
		}
	}

	private void BerechneModell()
	{
		//if (A.g.Debug())
		//	TabBefehle.showGrid("Befehle von "+A.TabModelle.getS("Bezeichnung"));
                if (A.bSetDebug && (A.iDBits&DGAUGE)>0)
                  if (GauZ==null && Gauge.getAnzahl()<4 && A.in == null)// && A.FomFX==null)
                  {
                    TabBefehle.moveFirst();
                    GauZ = new Gauge(A.TabModelle.getS("Bezeichnung"), 0, 1+((int)TabBefehle.max("aic_befehl"))-TabBefehle.getI("aic_befehl")/*TabBefehle.getAnzahlElemente(null)*/, "", A.g, false);
                    GauZ.noInfo();
                  }
                  else if (GauZ!=null)
                    GauZ.setText("",0);
                //A.g.progInfo("BerechneModell "+A.TabModelle.getS("Bezeichnung")+":"+TabBefehle.getAnzahlElemente(null)+"/"+iJa+"/"+iNein);
		dtVonOld=new DateWOD(A.getVon());
		dtBisOld=new DateWOD(A.getBis());
		TabBefehle.moveFirst();
    iErsterBefehl=TabBefehle.getI("aic_befehl");
    fortsetzen();
  }
  
  public void weiter(Tabellenspeicher Tab,boolean rbJa)
  {
//    bQ=false;
    iWAnz++;
    bJa=rbJa;
    if (iWAnz>10)
    	A.printError("Calc.weiter bei "+A.TabModelle.getS("Bezeichnung")+": schon 10 x weitergeführt",400);
    if (bMD && Tab==null)
        A.printError("Calc.weiter bei "+A.TabModelle.getS("Bezeichnung")+": Tab für Modelldialog nicht übergeben", 400);
    if (bMD)
    	A.TabMD=Tab;
    bMD=false;
    A.iMsgArt=0;
    A.sDebug="";
    fortsetzen();
  }

  private void fortsetzen()
  {
		while (!TabBefehle.eof() && TabBefehle.getI("BEF_AIC_BEFEHL")==iJa && TabBefehle.getI("BEF2_AIC_BEFEHL")==iNein)
		{
			if (TabBefehle.getI("Mod_Aic_Modell") != 0)
			{
          if (GauZ!=null && (A.iDBits&DGAUGE)>0)
            GauZ.setText("Submodell",TabBefehle.getI("aic_befehl")-iErsterBefehl);
					TabBefehle.push();
					A.TabModelle.push();
					//A.debugInfo("\nvor Modellaufruf:"+TabBefehle.getPos()+"/"+A.TabModelle.getPos());
					Timestamp dtVonVor=A.getVon();
					Timestamp dtBisVor=A.getBis();
					
					boolean bThread=(TabBefehle.getI("MBits")&THREAD)>0;// && (A.iDBits&NTH)==0;
//					int iMBits= A.g.getModellBits(TabBefehle.getI("Mod_Aic_Modell"), true);
//					if ((iMBits&Global.cstThread)>0)
					if (bThread)
					{
						A.g.commit();
						final int iModell=TabBefehle.getI("Mod_Aic_Modell");
						final Vector<Integer> VecStamm=Static.AicToVec(A.iQryReg);
						if (!Static.bWeb && A.bSetDebug)
						{
							A.debugInfo("Starte Modell NICHT im Hintergrund:"+A.g.getModellBez(iModell)+" mit Stamm="+A.g.getStammI(A.iQryReg));
							Global g=new Global(A.g,true);
							new Calc(null,g,iModell,false,VecStamm,-2,null,0);
							g.unConnect();
						}
						else
						{
							A.debugInfo("Starte Modell im Hintergrund:"+A.g.getModellBez(iModell)+" mit Stamm="+A.g.getStammI(A.iQryReg));							
							new Thread(new Runnable()
							{
								public void run()
								{
									long lClock = Static.get_ms();
									Global g=new Global(A.g,true);
						            if (Static.bWeb)
								       g.setVonBis(dtVonVor, dtBisVor);

									String sBez=g.getModellBez(iModell);
	//								g.fixtestError("Submodell in Thread: "+g.getModellBez(iModell));
						            if (Static.bWeb)
								    {
						                int iThread=g.startThread(g.ModellToBegriff(iModell), A.iQryReg, 0);
						                Calc c=new Calc(null,g,iModell,false,VecStamm,A.iQryReg,false,-2,null,0,null,null,null/*TabVar*/,A.TabMD,0,A.iZone,Optional.of(iThread),null);
						                g.endThread(iThread, c.Fehler(),!A.bSetDebug && c.A.iMsgArt<=webLog.iMsgL, lClock);
								    } 
						            else
						            	new Calc(null,g,iModell,false,VecStamm,-2,null,0);
	//								g.fixtestError("Thread fertig: "+g.getModellBez(iModell));
									g.unConnect();
									g.clockInfo("Modell "+sBez+" im Thread", lClock);
								}
							}).start();
						}						
					}
					else
					{
						new Calc(A,A.g,TabBefehle.getI("Mod_Aic_Modell"),false,null,0,null,0);
                        A.debugInfo(A.TabModelle.getS("Bezeichnung")+" fertig, bits="+A.TabModelle.getI("bits"));
                                        if ((A.g.getLog()>0) && ((A.TabModelle.getI("bits")&Global.cstZRhold)==0))
                           A.setVonBis(dtVonVor,dtBisVor);
                        else
                          A.debugInfo("Zeitraum beibehalten");
					}
					A.TabModelle.pop();
                                        if (bModellende)
                                        {
                                          A.debugInfo(" setze Modell-Ende zurück !!!");
                                          bModellende=false;
                                        }
					if (A.bFehler || A.bAbbruch)// || bModellende)
					{
						if (Static.bWeb && (A.bFehler || A.bAbbruch))
							if (A.bFehler)
								Static.printError("Abbruch von Modell "+A.TabModelle.getS("Bezeichnung")+" "+getZeile()+" wegen Fehler");
							else
								A.g.fixInfo("Abbruch von Modell "+A.TabModelle.getS("Bezeichnung")+" "+getZeile()+" wegen Debug-Abbruch");
						if (A.bFehler)
						  if (Static.bWeb)
                                                  {
							int iB=A.TabModelle.getI("aic_Begriff");														
							if (A.iMsgArt < 3)
							{
								A.iMsgArt=3;
								A.sInfo="Fehler bei "+A.g.TabBegriffe.getKennung(iB)+(iB != A.iHauptModell ? " aus HauptModell "+A.g.TabBegriffe.getKennung(A.iHauptModell):"");
				        		A.sHeader="Modellfehler";
							}
							if (A.sFehler.equals(""))
								A.sFehler="Modellfehler: Aufrufendes Modell="+A.TabModelle.getS("Bezeichnung")+(iB != A.iHauptModell ? " aus HauptModell "+A.g.getBegBez(A.iHauptModell):"");
							A.g.printError("Modellfehler: Aufrufendes Modell="+A.TabModelle.getS("Bezeichnung"),A.iHauptModell);
						  }
						  else
							Static.printError("Aufrufendes Modell="+A.TabModelle.getS("Bezeichnung"));
						return;
					}
					TabBefehle=(Tabellenspeicher)A.TabModelle.getInhalt("Modell");
					TabBefehle.pop();
					//A.g.debugInfo("nach Modellaufruf:"+TabBefehle.getPos()+"/"+A.TabModelle.getPos());
					A.printInfo("-- Zurück von "+A.TabModelle.getS("Bezeichnung")+"("+A.TabModelle.getPos()+"-"+(TabBefehle.getPos()+1)+")");
                                        A.g.testInfo("Modell"+A.TabModelle.getPos()+":"+A.TabModelle.getS("Bezeichnung"));
					TabBefehle.moveNext();
				//}
			}
			else
			{
				lClock2 = Static.get_ms();
				int iBefehl=TabBefehle.getI("AIC_CODE");
				int iBBits=TabBefehle.getI("MBITS");
                                bVar=(iBBits&VAR)>0;
                                bAS=(iBBits&SPALTE)>0;
                                bAR=(iBBits&REG)>0;
                                //bSave=(TabBefehle.getI("MBITS")&SAVE)>0;
                                bGroup=(iBBits&GRUPPE)>0;
                                bAlle=(iBBits&ALLE)>0;
                                bSum=(iBBits&SUM)>0;
                                iC_Art=(iBBits&C_ART);
                                iM_Art=(iBBits&M_ART);
                                sEingabe=A.g.encodeMath(TabBefehle.getS("Eingabe"));
                                if (Static.Leer(sEingabe))
                                	sEingabe=null;
                                sVar=bVar ? TabBefehle.getS("Var"):null;
                                if (bVar)
                                {
//                                  if (sEingabe != null && !Version.V18())
//                                  {
//	                                  int iPos=sEingabe.indexOf("=");
//	                                  sVar=iPos<0 ? sEingabe+"":sEingabe.substring(0,iPos);
//	                                  sEingabe=iPos<0 ? null:sEingabe.substring(iPos+1);
//	                                  A.g.progInfo("sEingabe="+sEingabe+", sVar="+sVar);
//                                  }
                                  bPerm=(iBBits&PERM)>0;
                                  int iVArt=iBBits&VART;
                                  iVArt2=iVArt==VICH ? 2:iVArt==V_R ? 3:iVArt==VALL ? 4:1;
                                }
				int iPos=A.g.TabCodes.getPos("Aic",iBefehl);
				sBefehl=A.g.TabCodes.getS(iPos,"Kennung");
				sBefBez=A.g.TabCodes.getS(iPos,"Bezeichnung");
				sGruppe=A.g.TabBegriffgruppen.getKennung(A.g.TabCodes.getI(iPos,"Gruppe"));
                                if (GauZ!=null && (A.iDBits&DGAUGE)>0)
                                  GauZ.setText(sBefehl,TabBefehle.getI("aic_befehl")-iErsterBefehl);
				try
				{
					int iFehler=Static.getError();
					if (!ErmittleSpalte(TabBefehle.getI("Aic_Spalte"),TabBefehle.getI("Aic_Abfrage")) || NaechsterBefehl())
					{
						if (Static.getError()>iFehler)
			            {
			              A.g.printError("bei Modell "+A.TabModelle.getS("Bezeichnung")+"/"+getZeile());//+":"+Static.getErrorString());           	
			            }
						TabBefehle.moveNext();
					}
				}
				catch(Exception e)
				{
					A.g.printStackTrace(e);
					A.printError(MPos()+e+" bei "+sGruppe+"/"+sBefehl+" Ges.:"+(A.iAnzahl+1),400);
				}
			}
		    if (bModellende)
		        A.g.progInfo("Modellende:"+A.TabModelle.getS("Bezeichnung")+"/"+sBefehl);
		    if (bMD || bQ)
		    {
		        A.iMsgArt=bMD ? 4:5;
		        if (bMD)
		        	A.sInfo="Modelldialog";
		        if (A.sLast!=null)
		        {
		          A.g.debugInfo(A.sLast);
		          A.sLast=null;
		        }
		        return;
		    }
			if (A.bFehler || A.bAbbruch || bModellende)
			{
			  if (Static.bWeb)
				  if (A.bFehler && A.iMsgArt < 3)
				  {
					  A.iMsgArt=3;
					  A.sInfo=A.sFehler;
					  A.sHeader="Modellfehler";
				  }else;

			  else if (A.bFehler || A.bAbbruch)
					  Static.printError("Abbruch von Modell "+A.TabModelle.getS("Bezeichnung")+(A.VecStamm != null && A.VecStamm.size()==1 ? "/"+A.g.getStamm(Sort.geti(A.VecStamm, 0)):"")+"/"+DateWOD.Format(A.g, "yyyy-MM-dd", dtVonVor2)+" "+getZeile()+" wegen "+(A.bFehler ? "Fehler":bExit ? "exit":"Debug-Abbruch"));
				return;
			}
			//A.debugInfo("Aic="+TabBefehle.getI("AIC_BEFEHL")+"("+TabBefehle.getS("Kennung")+") Ja="+TabBefehle.getI("BEF_AIC_BEFEHL")+"/"+iJa+", Nein="+TabBefehle.getI("BEF2_AIC_BEFEHL")+"/"+iNein);
			while((TabBefehle.eof() || TabBefehle.getI("BEF_AIC_BEFEHL")!=iJa || TabBefehle.getI("BEF2_AIC_BEFEHL")!=iNein) && (iJa!=0 || iNein !=0))
			{
				TabBefehle.pop();
				//A.g.debugInfo("nach pop: Aic="+TabBefehle.getI("AIC_BEFEHL")+", Ja="+TabBefehle.getI("BEF_AIC_BEFEHL")+"/"+iJa+", Nein="+TabBefehle.getI("BEF2_AIC_BEFEHL")+"/"+iNein);
				iJa = TabBefehle.getI("BEF_AIC_BEFEHL");
				iNein =TabBefehle.getI("BEF2_AIC_BEFEHL");
				if((TabBefehle.getI("MBITS")&SCHLEIFE)==0)
					TabBefehle.moveNext();
				//A.g.debugInfo("nach next: Aic="+TabBefehle.getI("AIC_BEFEHL")+", Ja="+TabBefehle.getI("BEF_AIC_BEFEHL")+"/"+iJa+", Nein="+TabBefehle.getI("BEF2_AIC_BEFEHL")+"/"+iNein);
			}
		}
	}
	
	private String MPos()
	{
		return A.TabModelle.getS("Bezeichnung")+"/"+(TabBefehle.getI("aic_befehl")-iErsterBefehl+1)+": ";
	}

	/*private void moveNext()
	{

	}*/
	private void print(String s,Object Obj)
	{
		A.g.debugInfo(s+":"+(Obj==null?"null":Obj+"/"+Obj.getClass().getName()+"/"+new java.text.DecimalFormat("#.000").format(Sort.getf(Obj))));
	}

        private String getSp(Abfrage AF)
        {
          if ((AF.iBits&Abfrage.cstDistinct)>0 && (AF.iBits&Abfrage.cstSumme)==0)
          {
            String s="null aic_"+(iBew==0 ?"stamm":"Bew_Pool");
            A.TabSpalten.push();
            for(A.TabSpalten.posInhalt("Abfrage",AF.iAbfrage);!A.TabSpalten.eof()&&A.TabSpalten.getI("Abfrage")==AF.iAbfrage;A.TabSpalten.moveNext())
            {
              String sDT=A.TabSpalten.getS("Datentyp");
              String sK=A.TabSpalten.getS("Kennung").substring(1);
              int iDArt=A.TabSpalten.getI("bits3")&Abfrage.cstDatenHolen;
              if (Abfrage.VVonBis.contains(sDT))
                s+=",v"+sK+",b"+sK+(sDT.equals("BewVon_Bis")?",d"+sK:"");
              else if (sDT.endsWith("Bool3"))
                s+=",e"+sK+",v"+sK;
              else if (iDArt==Abfrage.cstNurAicD)
                s+=",d"+sK;
              else if (iDArt==Abfrage.cstDanachD)
                s+=",d"+sK+",null e"+sK;
              else
              {
                  s+=",e"+sK;
	              if (Abfrage.VVerkn.contains(sDT))
	                s+=",v"+sK;
	              if (sDT.endsWith("Hierarchie"))
	                s+=",w"+sK;
              }
            }
            A.TabSpalten.pop();
            return s;
          }
          else
            return "*";
        }

        private boolean NotCopy(int iAic)
        {
          //A.g.TabStammtypen.posInhalt("Aic", AF.iStt) && (A.g.TabStammtypen.getI("bits") & Global.cstCopy) == 0
          int iPos=A.g.TabStammtypen.getPos("Aic", iAic);
          return iPos<0 ? false:(A.g.TabStammtypen.getI(iPos,"bits") & Global.cstCopy) == 0;
        }
        
    private void addClock(long lClock,String sBez,int iBeg,int iStamm,Tabellenspeicher Tab)
    {
    	if (A.g.Clock2() || A.bSetDebug || Static.bWeb)
    	{
    		if (A.TabClock==null)
    			A.TabClock=new Tabellenspeicher(A.g,new String[] {"Abfrage","Dauer","Stamm","Bew","ZR","Zeilen"});
        		long lDauer=Static.get_ms()-lClock;
        		if (lDauer>99 || !Static.bWeb)
        		{
	        		A.TabClock.addInhalt("Dauer",lDauer);
	        		A.TabClock.addInhalt("Abfrage",sBez+" ("+iBeg+")");
	        		A.TabClock.addInhalt("Stamm",sS!=null ? sS:sB!=null || iStamm==0 ? Static.sLeer:""+iStamm/*A.g.getStamm(iStamm)*/); // keine Bezeichung, da es auch Bew sein kann
	        		A.TabClock.addInhalt("Bew",sB==null ? Static.sLeer:sB);
	        		A.TabClock.addInhalt("ZR",A.g.getVB(A.iVB));//A.getVon()+"-"+A.getBis());*/A.g.getVonBis("dd.MM.yyyy", true));//A.g.getVB());
	        		A.TabClock.addInhalt("Zeilen",Tab==null  ? -1:Tab.size());
        		}
        	}
        }

	@SuppressWarnings("unchecked")
	private boolean ErmittleSpalte(int riSpalte,int riAbfrage)
	{
		if (A.g.getLog()==0)
		{
			if (A.iVB==0)
			{
				A.printError(MPos()+"Abbruch wegen Logout",499);
				return false;
			}
			else if (A.g.Disconnected())
			{
				A.g.connect(null);
				A.g.fixInfo(A.g.getBegBez(A.iHauptModell)+" "+MPos()+": weiter rechnen trotz Logout");
			}			
		}
		if (riSpalte==0 && riAbfrage==0)
		{
			sSpalte="";
			bSpalte=false;
		}
		else
		{
                  Tabellenspeicher TabAbfrageOld=null;
                  if (riSpalte==0)
                  {
                    A.TabSpalten.push();
                    A.TabAbfragen.push();
                    TabAbfrageOld=TabAbfrage;
                  }
                  int iBBits=TabBefehle.getI("MBITS");
                  int iArt=iBBits&Calc.ART;
                  bUseVecAic=bUseVecAic || iArt==USE_VEC;
                  bUseVecBew=bUseVecBew || iArt==VEC_BEW;
                  //bUseSync=bUseSync || iArt==SYNC;
                  bNextEmpty=bNextEmpty || iArt==EMPTY;
                  //bCache=bCache || (TabBefehle.getI("MBITS")&Calc.CACHE)>0;
                  //Vector<Integer> VecFilter=null;
//                  A.g.fixtestInfo("MBits="+iBBits+"-> kS="+((iBBits&KSZR)>0)+", kB="+((iBBits&KBZR)>0));

			if(riSpalte>0 ? A.TabSpalten.posInhalt("Aic",riSpalte):A.TabSpalten.posInhalt("Abfrage",riAbfrage))
			{
				A.TabAbfragen.posInhalt("Aic",A.TabSpalten.getInhalt("Abfrage"));
				iBew = A.TabAbfragen.getI("Bew");
				int iBits= A.TabAbfragen.getI("bits");
                                int iRF=TabBefehle.getI("MBITS")&Calc.REFRESH;
                                bRefetch=bRefetch || iRF==IMMER;
                                bNotRefetch=bNotRefetch || iRF==NIE;
                boolean bKeinZR=(iBits&Abfrage.cstKeinZeitraum)>0 || (iBBits&KBZR)>0 || iBew==0 && (iBBits&KSZR)>0;
				boolean bZRistGleich = iPeriode==0 || bKeinZR || Static.Gleich(A.getVon(),A.TabAbfragen.getInhalt("von")) && Static.Gleich(A.getBis(),A.TabAbfragen.getInhalt("bis"));
				boolean bStammRefresh = A.iQryReg!=0 && A.iQryReg!=A.TabAbfragen.getI("Stamm") && A.TabAbfragen.getI("Stamm")!=0;
                                //A.g.testInfo(sBefehl+", Spalte:"+riSpalte+"->R="+bRefetch+"/ NR="+bNotRefetch+"/ KR="+((iBits&Abfrage.cstKeinRefresh)>0)+"/ ZR="+bZRistGleich+"/ S="+bStammRefresh);
				if (!bRefetch && (BnotRefresh.contains(sBefehl) || bNotRefetch || (iBits&Abfrage.cstKeinRefresh)>0 || sGruppe.equals("saveorders") || (iBBits&SPALTE)>0 || !A.TabAbfragen.isNull("Filter") || !bStammRefresh && bZRistGleich))
					TabAbfrage=A.getAbf();//(Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage");
				else
				{
					//boolean bStammRefresh=!bStammGleich;
					//int i=0;
					if (!bRefetch && !bZRistGleich)
					{
						print("von-Vorher",A.TabAbfragen.getInhalt("von"));
						print("von-Nachher",A.getVon());
						print("bis-Vorher",A.TabAbfragen.getInhalt("bis"));
						print("bis-Nachher",A.getBis());
					}
					if(!bRefetch && bStammRefresh)
					{
						//int i = SQL.getInteger(A.g,"select aic_stammtyp from stamm where aic_stamm="+A.iQryReg);
						int i=getStt(A.iQryReg);
						bStammRefresh = i == A.TabAbfragen.getI("Stt");
						/*if(bStammRefresh)
							A.g.testInfo("Aic-Vorher:"+A.TabAbfragen.getI("Stamm")+" - > Aic-Nachher:"+A.iQryReg);
						else
							A.g.testInfo("Stt-Vorher:"+A.TabAbfragen.getI("Stt")+" - > Stt-Nachher:"+i);*/
					}
					if (sBefehl.equals("get Global"))
						TabAbfrage=A.g.getTab(A.TabAbfragen);
					else if (bRefetch || bStammRefresh || !bZRistGleich)
					{
						A.printInfo2("Abfrage erneuern:"+A.TabAbfragen.getS("Bezeichnung")+"("+A.TabAbfragen.getI("Aic")+") mit Stamm="+A.iQryReg+"("+A.getVon()+" - "+A.getBis()+")");
						A.iAnzahlRef++;
                        long lClock1=Static.get_ms();
                        int iStamm=bStammRefresh || sVar != null ? getQry(false):A.TabAbfragen.getI("Stamm");                 
//                        A.g.fixtestError("ErmittleSpalte: "+bStammRefresh+"/"+(sVar==null ? "<null>":sVar)+"-> iStamm="+iStamm);
                        TabAbfrage = getAbfTab(iStamm,A.TabAbfragen.getS("SQL"),A.TabAbfragen.getS("Bedingung"),A.TabAbfragen.getS("Order"),
							(Vector)A.TabAbfragen.getInhalt("sum"),(Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1"),(Abfrage)A.TabAbfragen.getInhalt("AF"),A.TabAbfragen.getS("Bezeichnung"));
						A.TabAbfragen.setInhalt("Dauer",new Long(Static.get_ms()-lClock1+A.TabAbfragen.getL("Dauer")));
						addClock(lClock1,A.TabAbfragen.getS("Bezeichnung"),A.TabAbfragen.getI("Begriff"),iStamm,TabAbfrage);
                        A.TabAbfragen.setInhalt("Anzahl",A.TabAbfragen.getI("Anzahl")+1);
                        A.TabAbfragen.setInhalt("Abfrage1",TabAbfrage);
                        clearDataRecord();
						if (bStammRefresh)
							A.TabAbfragen.setInhalt("Stamm",A.iQryReg);
						if (!bZRistGleich)
						{
							A.TabAbfragen.setInhalt("von",A.getVon());
							A.TabAbfragen.setInhalt("bis",A.getBis());
						}
					}
					else
					{
						//A.g.printInfo(1,"Abfrage nicht erneuern, da "+A.TabAbfragen.getI("Stt")+" ungleich "+i);
						TabAbfrage=(Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1");
					}
				}
				bSpalte=TabAbfrage!=null;
                A.iFilter=0;
			}
			else
			{
				long lClock = Static.get_ms();
				if(sBefehl.equals("clear query"))// || sBefehl.equals("get Global"))
				{
					TabAbfrage = null;
					A.debugInfo("Keine Abfrage ("+(Static.get_ms()-lClock)+" ms)");
					return false;
				}

				Vector VecEigenschaften = new Vector();
				Vector<Object> VecSum=null;
                                //A.g.progInfo("Lade Abfrage mit Spalte "+riSpalte);
				Abfrage AF=A.AbfH==null || A.AbfH.iAbfrage != riAbfrage ? new Abfrage(A.g,riAbfrage,Abfrage.cstAbfrage):A.AbfH;
				AF.iVB=A.iVB;
				//A.g.progInfo("Abfrage geladen "+riSpalte);
				/*
				SQL Qry = new SQL(A.g);
				Qry.open("select abfrage.aic_abfrage,abfrage.aic_begriff,begriff.bits,autorefresh from begriff join abfrage join spalte where aic_spalte="+riSpalte);
				int iAbfrage = Qry.getI("aic_abfrage");
				int iBegriff = Qry.getI("aic_begriff");
				int iBits = Qry.getI("bits");
				int iTop = Qry.getI("autorefresh");*/
				
                if (riSpalte==0)
                {
                  AF.iBits=AF.iBits | Abfrage.cstKeinStamm;
                }
                boolean bZR = AF.iBew==0 ? (AF.iBits & Abfrage.cstKeinStammZeitraum) ==0:iPeriode>0 && (AF.iBits&Abfrage.cstKeinZeitraum)==0 && ((AF.iBits&Abfrage.cstLDATE)==0 || A.iVB>0);
				if (AF.iBew>0 && (iBBits&KBZR)>0 || AF.iBew==0 && (iBBits&KSZR)>0) bZR=false; 
//				A.g.fixtestInfo("-> bZR="+bZR);
				//boolean bKeinStt = (iBits&Abfrage.cstKeinStt)>0;
				boolean bKeinStamm=(AF.iBits&Abfrage.cstKeinStamm)>0 || A.iQryReg==0 && !bUseVecAic;
                boolean bLeer=(AF.iBits&Abfrage.cstLeer)>0;
				boolean bSumme=(AF.iBits&Abfrage.cstSumme)>0;
				//Qry.free();
				//A.g.progInfo(bZR+"/"+bKeinStamm+"/"+bSumme);
				if (bSumme)
					VecSum= new Vector<Object>();
				//A.g.progInfo("VecSum="+VecSum);
				//JCOutlinerFolderNode Nod=Abfrage.InitNode(A.g,true);

				//A.g.debugInfo("iAbfrage="+iAbfrage);
				//JCOutlinerFolderNode NodSpalten = Abfrage.findNode(Nod,false);
				//Vector VecAbfSpalten = AF.NodSpalten == null ? null:AF.NodSpalten.getChildren();
				//if(riSpalte==542)
				//A.g.progInfo("VecAbfSpalten="+VecAbfSpalten);
				//A.debugInfo("VecAbfSpalten="+VecAbfSpalten);
				///Abfrage.ClearVecEigPer(A.g);
				//for(int i=0;VecAbfSpalten.size()>i;i++)
                                String sSpalteSort=null;
				for(AF.TabSp.moveFirst();!AF.TabSp.eof();AF.TabSp.moveNext())
				{
					//A.g.progInfo("i="+i);
					//Vector VecEig = (Vector)((JCOutlinerNode)VecAbfSpalten.elementAt(i)).getUserData();
					Vector VecEig = (Vector)AF.TabSp.getInhalt("Vec");
					//A.g.progInfo("VecEig="+VecEig);
					//g.debugInfo("Vec2="+Vec2);
					int iPos=A.g.TabEigenschaften.getPos("Aic",Tabellenspeicher.geti(VecEig.elementAt(VecEig.size()-1)));
					String sDatentyp = A.g.TabEigenschaften.getS(iPos,"Datentyp");
					Vector<Timestamp> VecPer=A.g.getVecPer(A.iVB);
					boolean bPeriode=(AF.TabSp.getI("bits")&Abfrage.cstPeriode)>0  && VecPer != null;
					char cAic= sDatentyp.equals("Gruppe") || sDatentyp.equals("BewStamm") || sDatentyp.endsWith("Bool3") ||
                                                sDatentyp.equals("Hierarchie") || sDatentyp.equals("BewHierarchie") || sDatentyp.equals("Firma") ||
						sDatentyp.equals("Benutzer") || sDatentyp.equals("Mandant") || sDatentyp.equals("Anlage") || sDatentyp.equals("LoeschBenutzer") ||
                                                sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("BewVon_Bis") && !bPeriode ||
						sDatentyp.equals("BewModul") || sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular") ? 'v':
						(sDatentyp.equals("Eintritt") || sDatentyp.equals("BewDatum")) && (AF.TabSp.getI("bits")&Abfrage.cstBild)>0 ? 'm':'e';
                                        if (!AF.TabSp.isNull("aic_stammtyp"))
                                          AF.iBits=AF.iBits | Abfrage.cstHierarchie;
                                        String sAic=(AF.iBits&Abfrage.cstHierarchie)>0 && sDatentyp.equals("Hierarchie") && !AF.TabSp.isNull("aic_stammtyp") ?
                                          AF.getVecHierarchie(VecEig).elementAt(0)+"_H"+AF.TabSp.getI("aic_stammtyp"):Abfrage.VecToKennung(VecEig);//EigElement(((Integer)VecEig.elementAt(0)).intValue());
                                        boolean bRel=Abfrage.VRel.contains(sDatentyp);//sDatentyp.equals("BewZahl2") || sDatentyp.equals("BewMass2") || sDatentyp.equals("BewWaehrung2");
                                        if (bRel && AF.TabSp.getI("Rel")>0)
                                          sAic+="_"+AF.TabSp.getI("Rel");
					//A.g.testInfo("Datentyp="+sDatentyp+", sAic="+sAic);
					Abfrage.contains(VecEigenschaften,VecEig,AF.TabSp.getInhalt("Gruppe") == null && (AF.TabSp.getI("bits")&Abfrage.cstPeriode)==0 && !bRel ? 0:AF.TabSp.getI("Nummer"));
                                        //Tabellenspeicher TabSpalten=(Tabellenspeicher)Abfrage.findNode(Nod,false).getUserData();
					//AF.TabSp.posInhalt("Node",VecAbfSpalten.elementAt(i));
					//A.g.progInfo("Spalte dazu "+sAic+"/"+sDatentyp);
					A.TabSpalten.addInhalt("Aic",AF.TabSp.getI("aic_spalte"));
					A.TabSpalten.addInhalt("Abfrage",AF.iAbfrage);				
					A.TabSpalten.addInhalt("Kennung2",sDatentyp.equals("von_bis") || sDatentyp.equals("BewVon_Bis") ? 'b'+sAic:
						sDatentyp.equals("Hierarchie") || sDatentyp.equals("BewHierarchie") ? 'w'+sAic:sDatentyp.equals("BewBool3") || sDatentyp.equals("Bool3") ? 'n'+sAic:
						sDatentyp.endsWith("Mass") && (!sDatentyp.equals("BewMass") || (AF.TabSp.getI("bits2") & AF.cstOrigEinh)>0) ? 'v'+sAic:null);
					//A.TabSpalten.addInhalt("Kennung3",(sDatentyp.equals("BewVon_Bis") ? 'd':sDatentyp.equals("Mehrfach") ? 'a':'e')+sAic);
					A.TabSpalten.addInhalt("Bezeichnung",Static.beiLeer(AF.TabSp.getS("Bezeichnung"),Abfrage.getEigenschaftBezeichnung(A.g,VecEig)));
					A.TabSpalten.addInhalt("Datentyp",sDatentyp);
					A.TabSpalten.addInhalt("Eig",VecEig.size()==1 ? VecEig.elementAt(0) : Static.Int0);
                                        A.TabSpalten.addInhalt("Format",AF.TabSp.getI("aic_begriff")==0 ? A.g.TabEigenschaften.getS(iPos,"Format"):A.g.TabBegriffe.getKennung(AF.TabSp.getI("aic_begriff")));
					//A.TabSpalten.addInhalt("Format",Static.beiLeer(AF.TabSp.getS("format"),A.g.TabEigenschaften.getS("Format")));
					A.TabSpalten.addInhalt("Per",new Boolean(bPeriode)/*AF.PeriodenCheck(A.g,AF.TabSp.getI("bits"))*/);
					A.TabSpalten.addInhalt("Laenge",Abfrage.getLaenge(AF.TabSp));
					A.TabSpalten.addInhalt("bits",AF.TabSp.getI("bits")|((A.g.TabEigenschaften.getI(iPos,"bits")&0x00ff)*0x10000));
					A.TabSpalten.addInhalt("bits2",AF.TabSp.getI("bits2"));
					A.TabSpalten.addInhalt("bits3",AF.TabSp.getI("bits3"));
                                        A.TabSpalten.addInhalt("Eig2",A.g.TabEigenschaften.getInhalt("Eig2",iPos));
                                        A.TabSpalten.addInhalt("Stt",AF.TabSp.getInhalt("Aic_Stammtyp"));
                                        A.TabSpalten.addInhalt("EF",AF.TabSp.isNull("Aic_Code")?null:A.g.TabCodes.getKennung(AF.TabSp.getI("Aic_Code")));
                                        A.TabSpalten.addInhalt("Anzeige",AF.TabSp.isNull("Anzeige")?null:A.g.TabCodes.getKennung(AF.TabSp.getI("Anzeige")));
                                        A.TabSpalten.addInhalt("Ergebnisart",AF.TabSp.isNull("cod_aic_code")?null:A.g.TabCodes.getKennung(AF.TabSp.getI("cod_aic_code")));
                                        A.TabSpalten.addInhalt("Rel",AF.TabSp.getI("Rel"));
                                        A.TabSpalten.addInhalt("Mass",AF.TabSp.getI("Mass"));
                                        A.TabSpalten.addInhalt("Faktor",AF.TabSp.getInhalt("Faktor"));
                                        int iFilter=AF.TabSp.getI("Filter");
                                        A.TabSpalten.addInhalt("Filter",iFilter);
                                        A.TabSpalten.addInhalt("Vec",null);
                                        A.TabSpalten.addInhalt("x",AF.TabSp.getI("x"));
                                        A.TabSpalten.addInhalt("y",AF.TabSp.getI("y"));
                                        A.TabSpalten.addInhalt("w",AF.TabSp.getI("w"));
                                        A.TabSpalten.addInhalt("h",AF.TabSp.getI("h"));
                                        A.TabSpalten.addInhalt("FxE",null);
                                        A.TabSpalten.addInhalt("TT",AF.TabSp.getInhalt("TT"));
                                        A.TabSpalten.addInhalt("Stil",AF.TabSp.getInhalt("Stil"));
                                        A.TabSpalten.addInhalt("Stamm",AF.TabSp.getI("Mass")==0 ? A.g.TabEigenschaften.getInhalt("aic_stamm",iPos):AF.TabSp.getInhalt("Mass"));
                                        A.TabSpalten.addInhalt("Nummer",AF.TabSp.getI("Nummer"));
                                        /*if (iFilter>0)
                                        {
                                          if (VecFilter==null)
                                            VecFilter=new Vector<Integer>();
                                          VecFilter.addElement(iFilter);
                                        }*/
                                        if (bSumme)
					{
						//Vector VecMehr=bPeriode?Zeitraum.VecPerioden:null;
						String s=(sDatentyp.equals("BewVon_Bis") && !bPeriode ? 'd':cAic)+sAic;
						int iErgArt=AF.TabSp.getI("cod_aic_code");
						String sArt= iErgArt==0 ? "sum":A.g.TabCodes.getKennung(iErgArt);
                        A.debugInfo("VecPerioden="+VecPer);
						int iAnz=bPeriode ? VecPer.size()-1:1;
						for(int i=0;i<iAnz;i++)
						{
							String s2=bPeriode?s+'p'+i:s;
							VecSum.addElement(sArt+"("+s2+") "+sArt+"_"+s2);
						}
						A.TabSpalten.addInhalt("Kennung",sArt+"_"+s);
						A.TabSpalten.addInhalt("Kennung3",sArt+"_"+(sDatentyp.equals("BewVon_Bis") ? 'd':sDatentyp.equals("Mehrfach") ? 'a':'e')+sAic);
					}
					else if (AF.TabSp.getInhalt("Gruppe") != null)
                                        {
                                          A.TabSpalten.addInhalt("Kennung","e"+sAic+"g"+AF.TabSp.getInhalt("Nummer")+"_0");
                                          A.TabSpalten.addInhalt("Kennung3","e"+sAic+"g"+AF.TabSp.getInhalt("Nummer")+"_0");
                                        }
                                        else
					{
						A.TabSpalten.addInhalt("Kennung",cAic+sAic);
                                                String sKennung3=(sDatentyp.equals("von_bis") ? 'v':sDatentyp.equals("BewVon_Bis") ? 'd':sDatentyp.equals("Mehrfach") ? 'a':'e')+sAic;
						A.TabSpalten.addInhalt("Kennung3",sKennung3);
                                                if (AF.iSort>0 && AF.TabSp.getI("Nummer")==AF.iSort)
                                                  sSpalteSort=sKennung3;
					}

				}
				//A.TabSpalten.showGrid("Spalten");
				//A.g.testInfo("nach iBegriff="+iBegriff);

				//int iStt = (iBits&AF.cstKeinStt)>0 ? 0:A.g.TabBegriffe.getI("Stt");
				iBew = AF.iBew;//A.g.TabBegriffe.getI("Erf");
				//A.g.testInfo("Stt="+iStt+", Bew="+iBew);
				//A.g.TabBegriffe.posInhalt("Aic",AF.iBegriff);
				//String sKennung = A.g.TabBegriffe.getS("Kennung");
				int iPos=A.g.TabBegriffe.getPos("Aic",AF.iBegriff);
				if (iPos<0)
					A.printError("Abfrage mit Aic_Begriff="+AF.iBegriff+" nicht gefunden",500);
				String sBez = iPos<0 ? "? Aic="+AF.iBegriff+"?":/*A.g.getBegBez(AF.iBegriff);*/A.g.TabBegriffe.getS(iPos,"DefBezeichnung");
                                int iPosAF=A.iFilter==0 ? -1:A.TabAbfragen.getPos("Aic",A.iFilter);
                                A.iFilter=0;
                                if (iPosAF>=0)
                                  A.debugInfo("  !!! verwende Filter: "+A.TabAbfragen.getS(iPosAF,"Bezeichnung"));
                                Abfrage AFF=iPosAF<0 ? AF:(Abfrage)A.TabAbfragen.getInhalt("AF",iPosAF);
                                String sCheck=AF.Check(VecEigenschaften,AFF.NodBed.getChildren(),"",false/*iBew==0?"p2.aic_stamm":"p2.aic_bew_pool,p2.gueltig"*/);
                                String sCheck2=AF.Check(VecEigenschaften,AFF.NodVBed.getChildren()," ",true);
				//A.g.testInfo("sBedingung="+sBedingung);
				//boolean bBed=!sCheck.equals("");
                                int iT=(AF.iBits&Abfrage.cstFirst)>0?AF.iTop<2?1:AF.iTop:-1;
				String sSort = (sSpalteSort != null ? " order by "+sSpalteSort:AF.Sortiert())+A.g.topE(iT);
				//A.g.progInfo("Abfrage "+sBez+": sSort="+sSort);
				boolean bKeinStt=/*AF.iStt==0 ||*/ (AF.iBits&Abfrage.cstKeinStt)>0; // Bedingung auf Stt=0 am 14.1.2022 entfernt, da Schwachsinn
                                String sSQL=null;
                                String sBedingung=null;
                                /*if (Static.bView2 && (AF.iBits&Abfrage.cstView2)>0)
                                {
                                  sSQL = "select * from auv_"+sBez;
                                  sBedingung = "";
                                }
                                else*/
                                {
                                  
                                      String sX=(iBew == 0 ? AF.ZusaetzlicheSpalten(VecEigenschaften, Formular.Stamm, false) +" from stammview" + (bZR ? A.ViewNr() : "2") +
                                       " p2 where " +(bKeinStt ? bLeer ? "0=1" : "1=1" : "aic_stammtyp=" + (bLeer ? 0 : AF.iStt)) + Abfrage.Rolle(AF.iRolle):
                                      AF.ZusaetzlicheSpalten(VecEigenschaften, Formular.Bewegung, false) + " from " +
                                      ((AF.iBits & Abfrage.cstEntfernte) > 0 ? bZR ? "BewView"+(A.iVB>0 ? A.ViewNr()+"d":"3") : "Bew_Pool" : bZR ? "BewView"+A.ViewNr() : "BewView2") + " p2" +
                                      (bKeinStamm || /*(AF.iBits&AF.cstANR)>0 &&*/ AF.g.getANR_BS(iBew,AF.iStt) != null ? "" : A.g.join("Bew_Stamm", "p2", "bew_pool")) + " where p2.aic_bewegungstyp=" + (bLeer ? 0 : iBew)) +
                                      /*(riSpalte>0 ? AF.checkFast(A.TabSpalten):"")+*/((AF.lBits2&Abfrage.cstKeinMandant)>0 ? "": iBew > 0 || NotCopy(AF.iStt) ? A.g.getReadMandanten(iBew) : A.g.getCopyMandanten(false)) +
                                      ((AF.iBits & Abfrage.cstEntfernte) > 0 && !bLeer ? iBew>0 && bKeinStt ? "":" and (pro_aic_protokoll is null or pro_aic_protokoll>=1" /*+ A.g.getFirstProt()*/ +
                                       /*(iBew>0 && bKeinStt ?")":*/" and (select count(aic_bew_pool) from bew_pool where bew_aic_bew_pool=p2.aic_bew_pool)=0)" :"");
                                       sSQL = A.g.topA(iT, (AF.iBits&Abfrage.cstDistinct)>0) + getSp(AF) + " FROM (Select " +sX;
                                  sBedingung = sCheck2 + AF.Ebenen() + sCheck;
                                  A.g.progInfo("SQL="+sSQL);
                                  A.g.progInfo("Bedingung="+sBedingung);
                                }
                                long lClock1a=Static.get_ms();
                                if (riSpalte>0)
                                  A.TabSpalten.posInhalt("Aic",riSpalte);
                                else
                                  A.TabSpalten.posInhalt("Abfrage",riAbfrage);
                                /*if(sBefehl.equals("set filter"))
                                  TabAbfrage=null;
                                else*/
//                                A.g.fixInfo("vor getQry mit "+bKeinStamm);
                                int iStamm=getQry(bKeinStamm);
                                  TabAbfrage = 	sBefehl.equals("get Global") ? null:getAbfTab(iStamm,sSQL,sBedingung,sSort,VecSum,null,AF,sBez);
                                long lClock1=Static.get_ms()-lClock1a;
				if (TabAbfrage!=null && TabAbfrage.fehler())
				{
					A.printError(MPos()+"Calc.ErmittleSpalte: Abfrage ["+sBez+"] ("+AF.iBegriff+") fehlerhaft: Bew="+iBew+"/ Stt="+AF.iStt,400);
					return false;
				}

				A.printInfo2("Neue Abfrage <"+sBez+">("+AF.iAbfrage+") mit Stamm="+A.iQryReg+"("+(bZR ? A.getVon()+" - "+A.getBis():"kein")+")");
				A.debugInfo("Neue Abfrage "+AF.iAbfrage+": "+sBez+"("+(Static.get_ms()-lClock)+" ms)");
				A.g.debugInfo("iQryReg="+A.iQryReg+", SQL-Statement:\n"+sSQL);
				//if (A.g.Debug())
				//	A.TabSpalten.showGrid("Spalten");
                                if (riSpalte==0)
                                  A.g.fixtestInfo("Modell "+A.TabModelle.getS("Bezeichnung")+": Ermittle Filter "+sBez);
				A.TabAbfragen.addInhalt("Aic",AF.iAbfrage);
				A.TabAbfragen.addInhalt("Abfrage1",TabAbfrage);
				A.TabAbfragen.addInhalt("Abfrage2",null);
				A.TabAbfragen.addInhalt("Filter",null);
				A.TabAbfragen.addInhalt("Bezeichnung",sBez);
                A.TabAbfragen.addInhalt("Anzahl",1);
                A.TabAbfragen.addInhalt("Dauer",new Long(lClock1));
                A.TabAbfragen.addInhalt("Begriff",AF.iBegriff);
				addClock(lClock1a,sBez,AF.iBegriff,iStamm,TabAbfrage);
				int iStt=bLeer || iBew==0 || bKeinStamm || A.iQryReg==0 ? AF.iStt:getStt(A.iQryReg);
				A.TabAbfragen.addInhalt("Stt",iStt);
				A.TabAbfragen.addInhalt("Stt-Bez",iStt<=0 ? Static.sLeer:A.g.TabStammtypen.getBezeichnungS(iStt));
				A.TabAbfragen.addInhalt("Bew",iBew);
				A.TabAbfragen.addInhalt("Bew-Bez",iBew<=0 ? Static.sLeer:A.g.TabErfassungstypen.getBezeichnungS(iBew));
                                //A.TabAbfragen.addInhalt("Rolle",AF.iRolle);
				A.TabAbfragen.addInhalt("save",new Tabellenspeicher(A.g,new String[] {"Kennung","new","Daten","saved"}));
				A.TabAbfragen.addInhalt("SQL",sSQL);
				A.TabAbfragen.addInhalt("Bedingung",sBedingung);
				A.TabAbfragen.addInhalt("Order",sSort);
				A.TabAbfragen.addInhalt("Mandant",Static.bWeb ? ""+A.g.getMandant():A.g.getMandant(0,null));
				A.TabAbfragen.addInhalt("Stamm",iStamm);//bKeinStamm?0:A.iQryReg);
				A.TabAbfragen.addInhalt("von",A.getVon());
				A.TabAbfragen.addInhalt("bis",A.getBis());
				A.TabAbfragen.addInhalt("bits",new Long(AF.iBits));
				A.TabAbfragen.addInhalt("sum",VecSum);
				A.TabAbfragen.addInhalt("AF",AF);
				A.TabAbfragen.posInhalt("Aic",AF.iAbfrage);
				if (sBefehl.equals("get Global"))
					TabAbfrage=A.g.getTab(A.TabAbfragen);
				bSpalte=TabAbfrage!=null;
				if (!bSpalte)
					A.g.fixtestInfo("TabAbfrage = null bei "+sBefehl);

				//if (A.g.Debug())
				//	A.TabAbfragen.showGrid("TabAbfragen");
                                if ((A.bDebug || A.g.Def()) && TabAbfrage!=null)
                                {
                                  for(A.TabSpalten.posInhalt("Abfrage",AF.iAbfrage);!A.TabSpalten.eof()&&A.TabSpalten.getI("Abfrage")==AF.iAbfrage;A.TabSpalten.moveNext())
                                  {
                                    TabAbfrage.setTitel(A.TabSpalten.getS("Kennung"),A.TabSpalten.getS("Bezeichnung")+"\n"+A.TabSpalten.getS("Kennung"));
                                    if (!A.TabSpalten.isNull("Kennung2"))
                                      TabAbfrage.setTitel(A.TabSpalten.getS("Kennung2"),A.TabSpalten.getS("Bezeichnung")+"\n"+A.TabSpalten.getS("Kennung2"));
                                    TabAbfrage.setTitel(A.TabSpalten.getS("Kennung3"),A.TabSpalten.getS("Bezeichnung")+"\n"+A.TabSpalten.getS("Kennung3"));
                                  }
                                }
                                if (riSpalte>0)
                                  A.TabSpalten.posInhalt("Aic",riSpalte);
				//A.g.saveSqlTime("Neue Abfrage",AF.iBegriff,Static.get_ms()-lClock);
			}
			//sSpalte=A.TabSpalten.getS("Kennung");
                        if (riSpalte>0)
                          sSpalte=A.TabSpalten.getB("Per") ? A.TabSpalten.getS("Kennung")+"p"+iPer:A.TabSpalten.getS("Kennung");
                        else
                        {
                          A.TabSpalten.pop();
                          A.TabAbfragen.push();
                          TabAbfrage=TabAbfrageOld;
                        }

			bNotRefetch=false;
			bRefetch=false;
			bUseVecAic=false;
			bUseVecBew=false;
			//bUseSync=false;
			bNextEmpty=false;
			//bCache=false;
                        /*if (VecFilter!=null)
                          for(int i=0;i<VecFilter.size();i++)
                            ErmittleSpalte(0,Sort.geti(VecFilter,i));*/
		}

                //bUseVar=false;
		return true;
	}

  // private void pruefeDoppelte(Global g,Tabellenspeicher TabAbfrage,Abfrage A)
  // {
  //   boolean bBew=A.iBew>0;
  //   int iAnz=TabAbfrage==null ? -1:TabAbfrage.size();
  //   if (iAnz>1 && bBew && iAnz>TabAbfrage.count_distinct("aic_bew_pool"))
  //   {
  //     g.fixtestError("Bewegungssätze doppelte bei: "+A.sDefBez+"("+A.iBegriff+")");
  //     Vector<Integer> Vec=new Vector<Integer>();
  //     for (int i=0;i<TabAbfrage.size();)
  //     {
  //       int iBew=TabAbfrage.getI(i, "aic_bew_pool");
  //       if (Vec.contains(iBew))
  //         TabAbfrage.clearInhalt(i);
  //       else
  //       {
  //         Vec.addElement(iBew);
  //         i++;
  //       }
  //     }
  //     g.fixtestError(" reduziere von "+iAnz+" auf "+TabAbfrage.size());
  //   } 
  // };

	private int getStt(int iStamm)
	{
		if (iStamm!=A.iStamm_Last)
		{
			A.iStamm_Last=iStamm;
			A.iStt_Last=SQL.getInteger(A.g,"select aic_stammtyp from stamm where aic_stamm=?",0,""+iStamm);
			//A.g.progInfo("Stt von Stammsatz"+iStamm+"="+A.iStt_Last);
		}
		return A.iStt_Last;
	}

        private String Bind(int i)
        {
          sBind=""+i;
          return "?";
        }

	private Tabellenspeicher getAbfTab(int iStamm,String sSQL,String sBedingung,String sSort,Vector VecSum,Tabellenspeicher Tab,Abfrage rAF,String sBez)
	{
//		A.g.fixInfo("getAbfTab mit Stamm="+iStamm);
//		A.g.fixtestError("getAbfTab mit Stamm="+iStamm);
		long lClockAbf = Static.get_ms();
		//if(A.iAnzahl>40)
		//	A.g.fixInfo(A.iAnzahl+":getAbfTab start");
                //if (iStamm!=0)
                //boolean bView2=false;//Static.bView2 && (rAF.iBits&Abfrage.cstView2)>0;
                boolean bLDATE=iPeriode>0 /*&& !bView2*/ && (rAF.iBits&Abfrage.cstLDATE)>0 && iBew>0 && A.iVB==0;
                boolean bNachSave=(rAF.iBits&Abfrage.cstNachSave)>0;
                if (rAF.iAbfF>0)
                {
                	A.VecAic=VecToStack(rAF.FilterCheck(A.VecAic));
                	bUseVecAic=A.VecAic.size()>0;
                }
                  A.debugInfo("getAbfTab mit Stamm:"+(bUseVecAic?""+A.VecAic:""+iStamm)+(bUseVecAic?"/use VecAic":"")+(bUseVecBew?"/use VecBew="+A.VecBewPool:"")+(bNextEmpty?"/next empty":"")+(bNachSave?"/nach Save":"")+(bLDATE?"/LDATE":""));
              //long lClocku1=Static.get_µs();
                String s=sSQL;
                String sAND= /*bView2 ? " where ":*/" and ";
                String sANR=null;
//                if (bUseVecAic)
                //	A.g.fixInfo("getAbfTab mit VecAic: iANR_Eig="+iANR_Eig+", keinStamm="+(rAF.iBits&Abfrage.cstKeinStamm)+", Bew="+iBew+", bNachSave="+bNachSave+", bUseVecBew="+bUseVecBew);
                int iANR2=iANR_Eig;
                if ((iANR_Eig>0 || (rAF.iBits&Abfrage.cstKeinStamm)==0) && iBew>0 && !bUseVecBew && !bNachSave)
                {
                  int iStt=iANR_Eig>0 ? -iANR_Eig:rAF.iStt;
                  if (iStt==0)
                	  A.g.fixInfoT("Abfrage "+sBez+" hat keinen Stammtyp für ANR-Ermittlung");
                  else
                  {
	                  sANR=A.g.getANR_BS(iBew,iStt);
//	                  A.g.fixInfo("getANR_BS von "+iBew+"/"+iStt+" -> "+sANR);
	                  iANR_Eig=0;
	                  if (sANR==null && (rAF.iBits&Abfrage.cstLeer)==0)
	                    A.g.defInfo2("ANR wird bei "+sBez+" nicht verwendet!");
	                  else
	                	  A.g.defInfo2("verwende "+sANR+" für "+sBez);
                  }
                }
//                if (bUseVecAic)
//                	A.g.fixInfo("-> ANR="+sANR+" bei "+sBez);
                if (bUseVecAic && !bNachSave && sANR==null && iBew>0 && (bUseVecAic || iStamm>0))
                	A.g.fixInfoT("UseVec ohne ANR bei Bew im Modell "+A.TabModelle.getS("Bezeichnung")+" "+getZeile()+" nicht möglich");//,rAF.iBegriff,true);
                String sSp= bNachSave || bUseVecBew ? "p2.aic_bew_pool":sANR != null ? sANR:"aic_stamm";
                if (iBew>0 && sSp.equals("aic_stamm") && (iStamm>0 || bUseVecAic))
                	A.debugInfo("Modell "+A.TabModelle.getS("Bezeichnung")+": Bew-Abfrage "+rAF.sDefBez+" ("+rAF.iBegriff+") verwendet aic_stamm da iANR_Eig="+iANR2+" bei Stamm="+iStamm+" (kein Stamm="+(rAF.iBits&Abfrage.cstKeinStamm)+")"
                			+(bUseVecAic ? ", UseVec="+VecStammToString(A.VecAic):""));
//                if (iBew>0 && sSp.equals("aic_stamm") && bUseVecAic)
//                {
////                	A.g.fixtestInfo("sSp="+sSp+", iBew="+iBew);
//                	A.printError("Bewegungsabfrage "+sBez+" mit UseVec nicht ohne ANR aufrufbar");
//                	return null;
//                }
                Vector<Integer> VecBewPool=rAF.getVecBewPool(sANR,iStamm);
                //A.g.fixtestError("bNachSave:"+bNachSave+", bUseVecBew="+bUseVecBew+" -> "+sSp);
                sBind=null;
                if (bNextEmpty)
                  s+=sAND+"1=0";
                /*else if (bUseSync)
                {
                  int iPos=iBew>0 ? A.g.TabErfassungstypen.getPos("Aic",iBew):-1;
                  if (iPos>=0)
                      s+=sAND+"aic_bew_pool="+A.g.TabErfassungstypen.getI(iPos,"Pool");
                  else
                  {
                    iPos=iBew==0 ? A.g.TabStammtypen.getPos("Aic",rAF.iStt):-1;
                    if (iPos>=0)
                      s+=sAND+"aic_stamm="+A.g.TabStammtypen.getI(iPos,"Stamm");
                  }
                }*/
                else
                  s+=bUseVecBew ? sAND+A.g.in(sSp,A.VecBewPool):bUseVecAic?sAND+A.g.in(sSp,A.VecAic):iStamm==0?"":VecBewPool != null ? " and p2.aic_bew_pool"+A.g.SQL_in(VecBewPool):sAND+sSp+"="+Bind(iStamm);
//                  A.g.fixtestError("Abfrage "+rAF.sDefBez+" mit iStamm="+iStamm+" bei "+sSp+":"+s);
                if (A.g.Clock2() || A.bDebug || Static.bWeb)
                {
                	sS=null;
                	sB=null;
                	if (bNextEmpty)
                		sS="Empty";
                	else if (bUseVecBew)
                		sB=""+A.VecBewPool;
                	else if (bUseVecAic)
                		if (bNachSave)
                		  sB=""+A.VecAic;
                		else
                		  sS=""+A.VecAic;
                	else if (bNachSave)
                		sB=""+iStamm;
                	
                }
                if (bLDATE)
                  s+=A.g.getLDATE();
                s+=sBedingung;
		if (VecSum==null)
			s=s+sSort;
		else
		{
			String sSum=(String)VecSum.elementAt(0);
			for(int i=1;i<VecSum.size();i++)
				sSum+=","+VecSum.elementAt(i);
			/*String sSum="";
			for(int i=0;i<VecSum.size();i++)
				sSum+=",sum("+VecSum.elementAt(i)+") "+VecSum.elementAt(i);*/
			s="select "+sSum+" from ("+s+") xxx";
		}
		//if(A.iAnzahl>40)
		//	A.g.fixInfo("SQL="+s);
		A.iAnzahlTab++;
                //long lClocku2=Static.get_µs();
              long lClock = Static.get_ms();
		boolean bNeu=Tab==null;
                if (!bNeu  && (A.iDBits&DPR)>0)
                  Pause();
                rAF.iQry=getQry(false);
                //boolean bStore=bCache;// || bNextEmpty || (rAF.iBits&Abfrage.cstLeer)>0;
                //if (bUseVecBew && A.g.Prog())
                //  A.g.fixInfo(s);
              //long lClocku2=Static.get_µs();
                s=rAF.checkJoker(s,A.iBegBer);//Static.bWeb ? A.iHauptModell:0);
                //A.g.progInfo(s);
              //long lClocku4=Static.get_µs();
                //long lCLock2=Static.get_ms();
//                A.g.fixtestError("Abfrage "+sBez+"mit Bind="+sBind+"/"+rAF.sBind+":"+s);
                if (rAF.sBind != null && ((rAF.iBits&Abfrage.cstFremdJoker)>0))
                  sBind=rAF.sBind;
                if (bNeu)
					Tab=new Tabellenspeicher(A.g,s,sBind,true,/*bStore ? "AbfDaten"+rAF.iAbfrage:*/null);
				else
					Tab.refresh(A.g,s,true,true,sBind);
                rAF.pruefeDoppelte(Tab);
//                A.g.fixtestError("SQL für Abfrage mit Bind="+sBind);
//                A.g.fixtestError(s);
                //A.g.clockInfo(rAF.sDefBez,lCLock2);
              //long lClocku5=Static.get_µs();
		//A.g.progInfo(s);
                if (Tab==null || Tab.fehler())
                {
                  A.printError(MPos()+"Abfrage "+sBez+" fehlerhaft bei sANR="+sANR+", iANR_Eig="+iANR2,400);
                  return Tab;
                }
                if (Static.bInfoLeer && (/*Tab==null || */Tab.isEmpty() && !bNextEmpty))
                {
                  A.g.fixInfo("Abfrage "+sBez+" ("+sBind+", "+A.getVon()+" - "+A.getBis()+") liefert keine Zeile"+(A.g.Prog()? " mit folgendem Statement:":""));
                  if (A.g.Prog())
                    A.g.fixInfo(s);
                }
                if (!A.TabSpalten.out())
		  Tab=rAF.checkNurErste(Tab, A.TabSpalten, null);
                else
                  A.g.fixInfo("Abfrage <"+sBez+">: TabSpalten stehen im Out");
                rAF.checkGleiche(Tab);
                //Tab.moveFirst();
                //rAF.checkDialog(Tab);
		rAF.checkHierarchie(A.TabSpalten,Tab);
                if (Abfrage.is(rAF.iBits,Abfrage.cstCalc))
                  rAF.checkCalc(Tab,A.TabSpalten);
                //long lClocku6=Static.get_µs();
                lClock=Static.get_ms()-lClock;
		//A.g.saveSqlTime("Abfrage",bNeu?rAF.iAbfrage:-rAF.iAbfrage,lClock);
              //long lClocku7=Static.get_µs();
                if (Static.lAbfAb==-1 || Static.lAbfAb>0 && lClock>=Static.lAbfAb)
                {
                  String sWo=rAF.iBew>0 ? "Bew "+A.g.TabErfassungstypen.getBezeichnungS(rAF.iBew):
                      "Stt "+A.g.TabStammtypen.getBezeichnungS(rAF.iStt);
                  String s2=sWo+"."+sBez+"("+rAF.iBegriff+")-Dauer: " + lClock+" ms für "+(Tab==null ? "Fehler":Tab.getAnzahlElemente(null)+" Zeilen");
                  s2+=" (Aic="+(bUseVecBew?""+A.VecBewPool:bUseVecAic?""+A.VecAic:""+iStamm)+", "+A.getVon()+" - "+A.getBis()+")";
                  A.g.defInfo(s2);
                  A.g.saveSqlTime(/*bStore?"AbfCac":*/"AbfTab",0,lClock,sWo+"."+sBez,(Tab==null?-1:Tab.getAnzahlElemente(null)),iStamm);
                  if (!A.g.Def())
                    A.g.testInfo(s2);
                  //if (lClock>29999)
                  //  A.g.diskInfo(s2);
                }
                /*if (A.g.TestPC())
                {
                  A.g.testInfo(sBez);
                  A.g.testInfo("Anfang:    " + Static.printZahl(lClocku2 - lClocku1, 8));
                  A.g.testInfo("checkJoker:" + Static.printZahl(lClocku4 - lClocku2, 8));
                  A.g.testInfo("Abfrage: " + Static.printZahl(lClocku5 - lClocku4, 10));
                  A.g.testInfo("checkes:   " + Static.printZahl(lClocku7 - lClocku5, 8));
                }*/
		//if(A.iAnzahl>40)
		//	A.g.fixInfo(A.iAnzahl+":getAbfTab fertig");
                A.lClockAbf+= Static.get_ms()-lClockAbf;
		return Tab;
	}

        private int getQry(boolean bKeinStamm)
        {
//        	A.g.fixInfo("getQry: sVar="+(sVar==null ? "<null>":sVar));
//        	A.g.fixtestError("getQry: sVar="+(sVar==null ? "<null>":sVar));
          if (sVar != null && sBefehl.equals("move first"))
            return geti(sVar);
          else
            return bKeinStamm?0:A.iQryReg;
        }

	private void addVecShow(Integer Int)
	{
	  if (A.VecAicShow==null)
	    A.VecAicShow=new Vector<Integer>();
	  if (!A.VecAicShow.contains(Int))
	    A.VecAicShow.addElement(Int);
	  A.BtnShowAic.setText(""+A.VecAicShow.size());
	}

	private void showStamm(Global g,JDialog rDlg,Vector Vec)
	{
	  /*JDialog FomGid = new JDialog(rDlg,"Stammsätze",false);
	  JCOutliner Grid = new JCOutliner();
	  FomGid.getContentPane().add("Center",Grid);*/
	  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_stamm,bezeichnung"+g.AU_Bezeichnung1("Stammtyp","v")+" Stammtyp,Eintritt,Austritt"+
	      g.AU_Bezeichnung1("Rolle","v")+" Rolle from stammview2 v where aic_stamm"+g.SQL_in2(Vec),true);
//	  if (A.FomFX != null)
//		  Tab.showGrid(A.FomFX,"Stammsätze");
//	  else
		  Tab.showGrid("Stammsätze",rDlg);//,false);
	  /*Tab.showGrid(Grid);
	  Static.centerComponent(FomGid,rDlg);
          FomGid.toFront();
          FomGid.setVisible(true);*/
	}
	
	private void Pause()
	{
		if (!Static.bX11)
			A.printError("Pause",0);
		else
			PauseDialog();
	}
	

	private void PauseDialog()
	{
          long lClock3=Static.get_ms();
		if (A.Dlg == null)
		{
			int iP=A.g.getBegriffAicS("Dialog","Pause");
			A.Dlg = new JDialog(A.FomA== null ? new JFrame():A.FomA,"Pause",true);

			A.Txt=new AUTextArea();
                        A.Txt.setFont(new Font("Courier New",Font.BOLD,12));
                        A.Txt.setEditable(false);
                        A.Txt.setH();
                        // A.Cbxb=new JCheckBox();
			// A.Zahld=new Zahl(0.0);
			// A.Datumdt=new Datum(A.g,"yyyy-MM-dd HH:mm:ss");
			// A.Zahli=new Zahl(0);
			// A.Zahli2=new Zahl(0);
			final Zahl Zahl1=new Zahl(0);
			A.CbxEnde=A.g.getCheckbox("Modellende",false);
			A.CbxDebug=A.g.getCheckbox("Debug",A.bDebug);//g.Debug());
			A.CbxSM=A.g.getCheckbox("Modelle_zeigen", (A.iDBits&DMZ)>0);
			A.CbxSM.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					
//					A.g.fixtestError("setze Modell-zeigen auf "+A.CbxSM.isSelected());
					if ((A.iDBits&DMZ)>0)
					{
						A.iDBits-=DMZ;
						DefModell.freeDlg();
					}
					else
					{
						A.iDBits+=DMZ;
						DefModell.setDlgM();
					}
				}
			});
            A.CbxNoBreak=A.g.getCheckbox("breakpoints ignorieren",false);
	        // JButton BtniP1=new JButton("+");
	        // JButton BtniP2=new JButton("+");
	        // JButton BtniP3=new JButton("+");
	        // JButton BtnInit=A.g.getButtonS("Init");
            BtnCol=A.g.getButtonS("Col");

			JPanel PnlOben = new JPanel(new GridLayout(1,3,3,1));
			JPanel PnlSp1 = new JPanel(new BorderLayout(3,1));
			JPanel PnlSp1W = new JPanel(new GridLayout(0,1,3,1));
			JPanel PnlSp1C = new JPanel(new GridLayout(0,1,3,1));
			// PnlSp1W.add(new JLabel(" b="));
			// PnlSp1W.add(new JLabel(" dt="));
			// PnlSp1W.add(new JLabel(" d="));
			JButton BtnS=A.g.getButtonS("s");
			BtnS.setEnabled(true);
			BtnS.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					//MemoF memoF=new MemoF(A.Dlg,"Modell "+getZeile(),A.g);
					AUTextArea Txt=new AUTextArea(A.g,8);
					Txt.setText(A.sReg);
					JDialog DlgMemo=new JDialog(A.Dlg,A.TabModelle.getS("Bezeichnung")+getZeile());
					DlgMemo.getContentPane().add(Txt, "Center");
					DlgMemo.setSize(new Dimension(500,400));
					Static.centerComponent(DlgMemo, A.Dlg);
					DlgMemo.setVisible(true);
				}
			});
                        PnlSp1W.add(BtnS);
			// PnlSp1C.add(A.Cbxb);
			// PnlSp1C.add(A.Datumdt);
			// PnlSp1C.add(A.Zahld);
			PnlSp1C.add(A.CbxNoBreak);
			PnlSp1.add("West",PnlSp1W);
			PnlSp1.add("Center",PnlSp1C);
			JPanel PnlSp2 = new JPanel(new BorderLayout(3,1));
			JPanel PnlSp2W = new JPanel(new GridLayout(0,1,3,1));
			JPanel PnlSp2C = new JPanel(new GridLayout(0,1,3,1));
			// JPanel PnlSp2E = new JPanel(new GridLayout(0,1,3,1));

                        final Zahl SBoWeiter= new Zahl(0);
                        final Text EdtSuche=new Text("",80);

                        ActionListener AL=new ActionListener()
                        {
                          public void actionPerformed(ActionEvent ev)
                          {
                            String s = ev.getActionCommand();
                            A.g.progInfo("Action=" + s);
                            if (s.equals("Ok") || s.equals("1weiter"))
                            {
                                    // A.bReg=A.Cbxb.isSelected();
                                    // if(A.Zahld.Modified())
                                    //         A.dReg=A.Zahld.doubleValue();
                                    // if(A.Datumdt.Modified())
                                    //         A.dtReg=A.Datumdt.getDate()==null?null:new DateWOD(A.Datumdt.getDate());
                                    // if(A.Zahli.Modified())
                                    //         A.iReg=A.Zahli.intValue();
                                    // if(A.Zahli2.Modified())
                                    //         A.iQryReg=A.Zahli2.intValue();
                                    bModellende=A.CbxEnde.isSelected();
                                    A.bNoBreak=A.CbxNoBreak.isSelected();
//                                    A.g.setDebug(A.CbxDebug.isSelected());
                                    A.bDebug=A.CbxDebug.isSelected();
                                    if (s.equals("1weiter"))
                                      A.iPause=A.iAnzahl+1;
                                    else if (SBoWeiter.intValue()>0)
                                      A.iPause=A.iAnzahl+SBoWeiter.intValue();
                                    bPause=false;
//                                    if (DlgFX != null)
//                                    	Platform.runLater(() -> DlgFX.close()); 
                                    if (iP>0)
                                    	A.g.setFenster(A.Dlg,iP,-1);
                                    DefModell.setDlgM();
                                    A.Dlg.setVisible(false);                                  
                                    if (A.bDebug && SBoWeiter.intValue()>0 && SBoWeiter.intValue()<100)
                                      ;
                                    else if (Msg != null)
                                    {
                                      //A.g.testInfo(" ------------- vor setVisible");
                                      Msg.setVisible(true);
                                      bPause=true;
                                      //A.g.testInfo(" ------------ nach setVisible");
                                    }

                            }
                            else if (s.equals("Abbruch"))
                            {
                                    A.iMaxBefehle = A.iAnzahl;
                                    bPause=false;
//                                    if (DlgFX != null)
//                                    	Platform.runLater(() -> DlgFX.close()); 
                                    A.Dlg.setVisible(false);
                                    //A.TabAbfragen.showGrid("Abfragen");
//                                    if (A.FomFX==null)
                                    	showTabAbfragen(null);
                            }
                            else if (s.equals("Suche") || s.equals("Back") || s.equals("Weiter"))
                            {
                              String s2 = EdtSuche.getText();
                              int iL=A.Txt.Edt.getText().length();
                              int iPos=A.Txt.Edt.getCaretPosition();
                              A.g.testInfo("max:"+iL+", Pos="+iPos+", ST="+s2.length());
                              int iSP = s.equals("Back") ? A.Txt.getValue().lastIndexOf(s2,iPos>s2.length() ? iPos-s2.length()-1:iL):
                                  A.Txt.getValue().indexOf(s2,s.equals("Suche") || iPos>iL-1 ? 0: iPos + 1);
                              if (iSP >= 0)
                              {
                                A.Txt.Edt.setEditable(true);
                                A.Txt.Edt.requestFocus();
                                A.Txt.Edt.setCaretPosition(iSP);
                                A.Txt.Edt.moveCaretPosition(iSP + s2.length());
                                A.Txt.Edt.setEditable(false);
                              }
                              else
                                A.Txt.Edt.setCaretPosition(s.equals("Back") ? 0:iL);
                            }
                            // else if (s.equals("P1") || s.equals("P2") || s.equals("P3"))
                            //   addVecShow((s.equals("P1") ? A.Zahli:s.equals("P2") ? A.Zahli2:Zahl1).getInteger());
                            // else if (s.equals("Init"))
                            // {
                            //   A.VecAicShow=null;
                            //   A.BtnShowAic.setText("0");
                            // }
                            else if (s.equals("Col"))
                            {
                              BtnCol.setBackground(new Color((int)A.dReg));
                            }
                            else if (s.equals("ShowAic"))
                              showStamm(A.g,A.Dlg,A.VecAicShow);
                            else if (s.equals("Tabellen"))
                              showTabAbfragen(A.Dlg);
                            // else if (s.equals("Var"))
                            // 	if (A.TabVar == null)
                            // 		new Message(Message.INFORMATION_MESSAGE,A.Dlg,A.g,10).showDialog("keinVar");
                            // 	else
                            // 		A.TabVar.showGrid("Var bei Zeile "+getZeile(),A.Dlg);
                            else if (s.equals("VarG"))
                            	TabV.showGrid("VarG",A.Dlg);
                            else if (s.equals("SyncStamm"))
                              SyncStamm.start(A.g,A.Dlg).show();
                            else if (s.equals("Bool3"))
                            	A.g.TabAuswahl.showGrid("Bool3",A.Dlg);
                            else if (s.equals("Debug") && A.TabDebug != null)
                              A.TabDebug.showGrid("Debug",A.Dlg);
                            else if (s.equals("Clock") && A.TabClock != null)
                                A.TabClock.showGrid("Clock",A.Dlg);
                          }
                        };

                        // PnlSp2W.add(new JLabel(" i="));
                        // PnlSp2W.add(new JLabel(" Qry="));
                        PnlSp2W.add(A.g.BtnAdd(BtnCol,"Col",AL));
                        // PnlSp2W.add(A.g.BtnAdd(BtnInit,"Init",AL));
                        // PnlSp2C.add(A.Zahli);
                        // PnlSp2C.add(A.Zahli2);
                        JPanel PnlSp2Cx=new JPanel(new GridLayout());
                        PnlSp2Cx.add(A.CbxEnde);
                        PnlSp2Cx.add(A.CbxDebug);
                        PnlSp2Cx.add(A.CbxSM);
                        PnlSp2C.add(PnlSp2Cx);
                        // PnlSp2C.add(Zahl1);
                        // PnlSp2E.add(A.g.BtnAdd(BtniP1,"P1",AL));
                        // PnlSp2E.add(A.g.BtnAdd(BtniP2,"P2",AL));
                        // PnlSp2E.add(A.g.BtnAdd(A.BtnShowAic,"ShowAic",AL));
                        // PnlSp2E.add(A.g.BtnAdd(BtniP3,"P3",AL));
                        PnlSp2.add("West",PnlSp2W);
                        PnlSp2.add("Center",PnlSp2C);
                        // PnlSp2.add("East",PnlSp2E);

			JPanel PnlSp3 = new JPanel(new GridLayout(0,1,3,1));
			JButton BtnTab=A.g.BtnAdd(A.g.getButtonS("Tabellen"),"Tabellen",AL);
                        BtnTab.setEnabled(true);

			JButton BtnSpalten=A.g.getButtonS("Spalten");
                        BtnSpalten.setEnabled(true);
			BtnSpalten.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
				  A.TabSpalten.showGrid("Spalten",A.Dlg);//,false);
				  A.TabSpalten.Grid.addActionListener(new JCActionListener()
				  {
  					public void actionPerformed(JCActionEvent ev)
  					{
  						JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
  						int i=Sort.geti(Nod.getLabel(),1);
  						A.g.progInfo("Int="+i);
  						A.TabAbfragen.push();
  						if (A.TabAbfragen.posInhalt("Aic",i))
  						{
  							Tabellenspeicher Tab=A.getAbf();//(Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage");
  							Tab.showGrid("Abf.: "+A.TabAbfragen.getS("Bezeichnung"),(JDialog)A.TabSpalten.FrmGrid);//,false);
  						}
  						A.TabAbfragen.pop();
  					}
				  });
				}
			});
			JButton BtnMod=A.g.getButtonS("Modelle");
            BtnMod.setEnabled(true);
			BtnMod.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
				  A.TabModelle.showGrid("Modelle",A.Dlg);//,false);
				  A.TabModelle.addALG(new JCActionListener()
				   {
					public void actionPerformed(JCActionEvent ev)
					{
						JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
						int i=Sort.geti(Nod.getLabel(),0);
						int iPos=A.TabModelle.getPos("Aic",i);
						if (iPos>=0)
						{
							Tabellenspeicher Tab=(Tabellenspeicher)A.TabModelle.getInhalt("Modell",iPos);
//							A.g.fixtestError("Modell "+A.TabModelle.getS(iPos,"Bezeichnung")+" ("+A.TabModelle.getI(iPos,"Aic")+")"+" bei Zeile "+Tab.getI("aic_befehl"));
							if (!DefModell.Zeige(Tab.getI("aic_befehl"),(JDialog)A.TabModelle.FrmGrid))
								Tab.showGrid("Modell "+A.TabModelle.getS(iPos,"Bezeichnung"),(JDialog)A.TabModelle.FrmGrid);//,false);
//							else
//								A.CbxSM.setSelected(true);
						}
					}
				   });
//				  if (A.TabModelle.Grid.getActionMap().size()==0)
//				  {
//					  A.g.fixtestError("addActionListener bei Modelle");
//				   
//				  }
				}
			});
			
                        // JButton BtnVar=A.g.getButton("Var","Var",AL);
                        JButton BtnVarG=A.g.getButton("VarG","VarG",AL);
                        JButton BtnSync=A.g.BtnAdd(A.g.getFrameS("SyncStamm"),"SyncStamm",AL);
                        JButton BtnAuswahl=A.g.getButton("Bool3M","Bool3",AL);
                        JButton BtnDebug=A.g.getButton("Debug","Debug",AL);
                        JButton BtnClock=A.g.getButton("Clock","Clock",AL);
                        // BtnVar.setEnabled(true);
                        BtnVarG.setEnabled(true);
                        BtnSync.setEnabled(true);
                        BtnAuswahl.setEnabled(true);
                        BtnDebug.setEnabled(true);

			PnlSp3.add(BtnTab);
			PnlSp3.add(BtnSpalten);
			PnlSp3.add(BtnMod);
                        JPanel PnlBU=new JPanel(new GridLayout());
                        // PnlBU.add(BtnVar);
                        PnlBU.add(BtnVarG);
                        PnlBU.add(BtnSync);
                        PnlBU.add(BtnAuswahl);
                        PnlBU.add(BtnDebug);
                        PnlBU.add(BtnClock);
                        PnlSp3.add(PnlBU);

			PnlOben.add(PnlSp1);
			PnlOben.add(PnlSp2);
			PnlOben.add(PnlSp3);
                        A.Dlg.getContentPane().setLayout(new BorderLayout(3,3));
			A.Dlg.getContentPane().add("North",PnlOben);

			A.OutVar=new AUOutliner();
      if (A.TabVar != null)
        A.TabVar.showGrid(A.OutVar, null);
      JSplitPane PnlC=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,A.Txt,A.OutVar);
      PnlC.setOneTouchExpandable(true);
      PnlC.setResizeWeight(0.8);
      A.Dlg.getContentPane().add("Center",PnlC);
      // A.Dlg.getContentPane().add("East",A.OutVar);
                        JButton BtnSuche=A.g.getButtonS("Suche");
                        JButton BtnBack=A.g.getButtonS("Ruecksetzen");
                        JButton BtnWeiter=A.g.getButtonS("Weiter");
                        JButton BtnWeiter1=A.g.getButtonS("1_weiter");
                        BtnSuche.setEnabled(true);
                        BtnBack.setEnabled(true);
                        BtnWeiter.setEnabled(true);
                        BtnWeiter1.setEnabled(true);
			JButton BtnOk=A.g.getButtonS("Ok");
			BtnOk.setEnabled(true);
            A.Dlg.getRootPane().setDefaultButton(BtnOk);
			JButton BtnAbbruch=A.g.getButtonS("Abbruch");
			BtnAbbruch.setEnabled(true);

                        A.g.BtnAdd(BtnSuche,"Suche",AL);
                        A.g.BtnAdd(BtnBack,"Back",AL);
                        A.g.BtnAdd(BtnWeiter,"Weiter",AL);
                        A.g.BtnAdd(BtnWeiter1,"1weiter",AL);
                        A.g.BtnAdd(BtnOk,"Ok",AL);
                        A.g.BtnAdd(BtnAbbruch,"Abbruch",AL);
			SBoWeiter.setValue(1);
			JPanel PnlUnten = new JPanel(new GridLayout(1,0,3,1));
            PnlUnten.add(BtnSuche);
            PnlUnten.add(EdtSuche);
            PnlUnten.add(BtnBack);
            PnlUnten.add(BtnWeiter);
            PnlUnten.add(new JLabel());
            PnlUnten.add(BtnWeiter1);         
			PnlUnten.add(SBoWeiter);
			PnlUnten.add(BtnOk);
			PnlUnten.add(BtnAbbruch);
			A.Dlg.getContentPane().add("South",PnlUnten);
			if (iP==0)
				A.Dlg.setSize(1000,500);
			if (iP>0 && A.g.getFenster(A.Dlg,iP,false,0,0,1000,500,A.g.getFontFaktor()))
				;
			else
				Static.centerComponent(A.Dlg, A.FomA);
		}
		A.iDebug=-1;
		if (Msg != null)
		  Msg.setVisible(false);
		A.Txt.setText(A.sDebug);
    A.Txt.Edt.setPreferredSize(new Dimension(A.Txt.Edt.getPreferredScrollableViewportSize().width, A.Txt.Edt.getLineCount()*14+10));
    A.Txt.Edt.setCaretPosition(A.sDebug.length());
    if (A.TabVar != null)
        A.TabVar.showGrid(A.OutVar, null);
    // A.Cbxb.setSelected(A.bReg);
		// A.Zahld.setValue(A.dReg);
		// A.Datumdt.setDate(A.dtReg==null?null:new Date(A.dtReg.getTimeInMilli()));
		// A.Zahli.setValue(A.iReg);
		// A.Zahli2.setValue(A.iQryReg);
		A.CbxEnde.setSelected(bModellende);
        A.CbxDebug.setSelected(true);
        A.Dlg.setTitle(A.TabModelle.getS("Bezeichnung")+" - "+(TabBefehle.getI("aic_befehl")-iErsterBefehl+1));
		A.Dlg.repaint();
		if (/*A.bShowModell ||*/ (A.iDBits&DMZ)>0)
		{
			DefModell.Zeige(((Tabellenspeicher)A.TabModelle.getInhalt("Modell")).getI("aic_befehl"),/*A.bShowModell ? (JDialog)A.TabModelle.FrmGrid:*/A.Dlg);
		}
		A.Dlg.setVisible(true);
                lClock2+=Static.get_ms()-lClock3;
	}

        private void showTabAbfragen(JDialog rDlg)
        {
//        	if (Static.bWeb)
//        		return;
//        	A.g.fixInfo("showTabAbfragen: FomFX="+A.FomFX+", Dialog="+rDlg);
//        	if (A.FomFX!=null)
//        	{
//        		A.g.fixtestInfo("showTabAbfragen bei JavaFX");
//        		//Static.sleep(3000);
//        		A.TabAbfragen.showGrid(A.FomFX,"Tabellen "+A.TabModelle.getS("Bezeichnung"));
//        		return;
//        	}
          final boolean bModal=rDlg != null;
          final JDialog FomGid = bModal ? new JDialog(rDlg, "Tabellen", false):new JDialog((JFrame)null, "Tabellen "+A.TabModelle.getS("Bezeichnung"), false);
          AUOutliner Grid = new AUOutliner();
          FomGid.getContentPane().add("Center", Grid);
          A.TabAbfragen.showGrid(Grid);
          FomGid.setSize(800, 300);
          Grid.addActionListener(new JCActionListener() {
            public void actionPerformed(JCActionEvent ev) {
              JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
              boolean bFilter=Sort.gets(Nod.getLabel(),1).startsWith("*");
//              A.g.fixInfo("Filter="+bFilter+"/"+((Vector)Nod.getLabel()));//.elementAt(20));
              Tabellenspeicher Tab = (Tabellenspeicher)((Vector)Nod.getLabel()).elementAt(bFilter ? 17:16);
              //Tabellenspeicher Tab2 = (Tabellenspeicher)((Vector)Nod.getLabel()).elementAt(15);
              if(Tab.FrmGrid != null)
                Tab.FrmGrid.dispose();
              //Tab.showGrid("Abf.:" + ((Vector)Nod.getLabel()).elementAt(1), bModal ? FomGid:null);
              Tab.showGrid("Abf.:"+((Vector)Nod.getLabel()).elementAt(1),FomGid);//,false);
            }
          });
          if (A.Dlg != null && A.Dlg.isVisible() || A.FomA != null)
          	Static.centerComponent(FomGid,A.Dlg != null && A.Dlg.isVisible() ? A.Dlg:A.FomA);
          FomGid.toFront();
          FomGid.setVisible(true);
        }

        private String getZeile()
        {
          // A.g.fixtestError("getZeile"+A.TabModelle.getPos()+":"+TabBefehle.getI("aic_befehl")+"/"+iErsterBefehl);
          return A.g.Sid()+"x"+Static.VorNull(A.TabModelle.getPos())+"-"+Static.FillNull(""+iLastZeile,3);
        }
        
    private String CoI()
    {
//    	if (iC_Art==0) 
//    	  return Static.sLeer;
    	String s=Static.sLeer;
    	if (iM_Art>0) 
    		s+= " "+(iM_Art==ADD ? "+":iM_Art==SUB ? "-":"I");
    	if (iC_Art>0) 
    	  s+= " "+(iC_Art==CWS ? "C+":iC_Art==CONCAT ? "C":"I");
    	if (bAlle)
    		s+=" alle";
    	if (bSum)
    		s+=" sum";
    	if (bAS)
    		s+=" Sp";
    	if (bAR)
    		s+=" Reg";
    	if (bGroup)
    		s+=" G";
    	if ((TabBefehle.getI("MBITS")&SAVE)>0)
    		s+=" save";
    	return s;
    }

  private String showSpalte(Object Obj)
  {
    //TabAbfrage.getInhalt(sSpalte).getClass().getName()+"->"+TabAbfrage.getInhalt(sSpalte)))
    if (Obj==null)
      return "[null]";
    else if (Obj instanceof Double)
      return "Double->"+checkUm((double)Obj);
    else
      return Obj.getClass().getName()+"->"+Obj;
  }

	private boolean NaechsterBefehl()
	{
		boolean b=true;
		A.iAnzahl++;
		Aufruf.iGesAnzahl++;
		if (A.bSetDebug && A.iDebug==A.iAnzahl)
			A.bDebug=true;
//			A.g.setDebug(true);
        if (A.bSetDebug && Aufruf.VecDebug != null && Aufruf.VecDebug.contains(TabBefehle.getInt("AIC_BEFEHL")))
        	A.bDebug=true;
//          A.g.setDebug(true);

		if (A.iAnzahl % 100 == 0 && A.bSetDebug && !A.bDebug || A.iAnzahl % (Static.bWeb ? 1000:5000) == 0)
                {
                  A.g.fixInfo("** Befehl " + A.iAnzahl+" in "+A.TabModelle.getS("Bezeichnung")+" "+getZeile());
                  //SQL.open_cursors("** Befehl " + A.iAnzahl,A.g);
                }
                if (A.bSetDebug && Static.getError()>0)
                  A.debugInfo("Error: "+Static.getErrorString());
//            	else if (Static.getError()>iErrorLast)
//                {
//                	A.g.printError(A.TabModelle.getS("Bezeichnung")+"/"+getZeile()+":"+Static.getErrorString()); 
//                	Static.clearError();
//                	iErrorLast=Static.getError();             	
//                }
                String sZeile=null;
                if (TabBefehle.getI("aic_befehl")>0)
                  iLastZeile=TabBefehle.getI("aic_befehl")-iErsterBefehl+1;
                if (A.bDebug)
                {
                  sZeile=getZeile();//Static.VorNull(A.TabModelle.getPos())+"-"+Static.FillNull(""+(TabBefehle.getI("aic_befehl")-iErsterBefehl+1),3);
                  Abfrage AF=bSpalte ? (Abfrage)A.TabAbfragen.getInhalt("AF"):null;
                  String sSpaltenInfo=CoI()+(sVar != null ? "/ var="+sVar+" A"+iVArt2:"")+(sEingabe == null ? "":"/ E="+sEingabe)+
                      (!bSpalte ? "":"/ Tab="+A.TabAbfragen.getS("Bezeichnung")+", Zeile="+TabAbfrage.getPos()+
                    		  ((AF.iBits&Abfrage.cstSumme)>0 ? ", Sum-Abfrage":", Aic="+TabAbfrage.getI(iBew==0 ? "aic_stamm":"aic_bew_pool"))+
                    		  ", Spalte="+A.TabSpalten.getS("Bezeichnung")+"("+A.TabSpalten.getS("Kennung")+")"+
                      "="+(TabAbfrage.out() ? "out":TabAbfrage.getInhalt(sSpalte)==null || A.TabSpalten.getS("Datentyp").equals("Filler") ? "null":
                    	  A.TabSpalten.getS("Datentyp").equals("BewHierarchie") && A.TabSpalten.getI("Stt")>0 && TabAbfrage.getI("W"+sSpalte.substring(1))!=A.TabSpalten.getI("Stt") ? "falsche Ebene":
                    		  showSpalte(TabAbfrage.getInhalt(sSpalte))))// TabAbfrage.getInhalt(sSpalte).getClass().getName()+"->"+TabAbfrage.getInhalt(sSpalte)))
                      +(sGruppe.equals("math_functions") && !sBefehl.equals("Befehl159") ? " Fx="+(bAR ? getFx2():getFx1())+"/"+(bAR ? getFx1():getFx2()):"");
                  A.debugInfo(Static.FillNull(""+A.iAnzahl,6)+":"+sZeile+"/"+((A.iDBits&DC)>0 ?(Static.get_ms()-lClock2)+" ms:":"")+sGruppe+"/"+sBefBez+"("+sBefehl+")"+sSpaltenInfo);
                  A.g.testInfo(A.bDebug+"/"+A.iPause+"/"+A.iAnzahl);
                }
                if (bPause && A.g.Def() || (A.bSetDebug || A.bDebug) && A.iPause==A.iAnzahl || A.bSetDebug && (Static.getError()>0 
                    || Aufruf.VecBreak != null && !A.bNoBreak && Aufruf.VecBreak.contains(TabBefehle.getInt("AIC_BEFEHL"))
                    || Aufruf.VecSpalten != null && !A.bNoBreak && bSpalte && Aufruf.VecSpalten.contains(A.TabSpalten.getI("Aic"))
                    || Aufruf.VecVar != null && !A.bNoBreak && sVar!=null && Aufruf.VecVar.contains(sVar)
                    || Aufruf.VecEingabe != null && !A.bNoBreak && sEingabe!=null && Aufruf.VecEingabe.contains(sEingabe)
                    || Aufruf.VecBefehl != null && Aufruf.VecBefehl.contains(sBefehl)
                    || (A.iDBits&ZBP)>0 && (A.iZoneOld != A.iZone)))
                {
                  if (!A.bSetDebug && Static.getError()>0)
                  {
                    A.sDebug+="Error: "+Static.getErrorString()+"\r\n";
//                    Static.printError(A.TabModelle.getS("Bezeichnung")+"/"+getZeile()+":"+Static.getErrorString()); 
                  }
                  if (Static.bAll && Static.bX11)
                    Pause();
                  Static.clearError();
                }
                
                A.iZoneOld=A.iZone;
        if (A.g.getLog()==0)
        {
        	if (A.iVB==0)
          {
            A.printError(MPos()+"Calc.NaechsterBefehl(): Vorzeitiger Abbruch wegen logout",499);
            return false;
          }
          else if (A.g.Disconnected())
          {
            A.g.connect(null);
            A.g.fixInfo(A.g.getBegBez(A.iHauptModell)+" "+MPos()+": weiter rechnen trotz Logout");
          }	
        }
    if (bMD || bQ && A.iMsgArt==5)
        return false;
		if (bPause && !A.g.Def() || A.iMaxBefehle>0 && A.iAnzahl >= A.iMaxBefehle)
		{
      if (Static.bWeb)
			  A.printError(MPos()+"Calc.NaechsterBefehl(): Vorzeitiger Abbruch vor Befehl "+A.iAnzahl,508);
      else
      {
			  A.bAbbruch = true;
			  if (bPause || A.bDebug)
			  	A.g.fixInfo((MPos()+"Calc.NaechsterBefehl(): Vorzeitiger Abbruch vor Befehl "+A.iAnzahl));
			  else
				  A.printError(MPos()+"Calc.NaechsterBefehl(): Vorzeitiger Abbruch vor Befehl "+A.iAnzahl,508);
      }
			return false;
		}
    long lClock3 = Static.get_ms();
		//int iBefehl=TabBefehle.getI("AIC_CODE");
		//A.g.TabCodes.posInhalt("Aic",iBefehl);
		//String sBefehl=A.g.TabCodes.getS("Kennung");
		//A.g.TabBegriffgruppen.posInhalt("Aic",A.g.TabCodes.getI("Gruppe"));
		//String sGruppe=A.g.TabBegriffgruppen.getS("Kennung");

    if (sGruppe.equals("Bedingung"))
			b=RunBedingung();
		//if (sGruppe.equals("Anweisung"))
    else
			RunAnweisung();
		/*else if (sGruppe.equals("init"))
			RunInit();
		else if (sGruppe.equals("convert"))
			RunConvert();
		else if (sGruppe.equals("get data"))
			RunDatenHolen();
		else if (sGruppe.equals("math_functions"))
			RunMathematik();
		else if (sGruppe.equals("dateorder"))
			RunDatum();
		else if (sGruppe.equals("saveorders"))
			RunSpeichern();
		else if (sGruppe.equals("error handling"))
			RunError();
		else if (sGruppe.equals("terminal"))
			RunTerminal();
		else if (sGruppe.equals("OLE"))
			RunOLE();
		else if (sGruppe.equals("FTP"))
			RunFTP();
		else if (sGruppe.equals("E-Mail"))
			RunEMail();
                else if (sGruppe.equals("light"))
			RunLight();
                else if (sGruppe.equals("GAUGE"))
			RunGauge();
                else if (sGruppe.equals("authorisation"))
			RunAuthorisation();
                else if (sGruppe.equals("ZIP"))
			RunZIP();
                else if (sGruppe.equals("XML"))
			RunXML();
                else if (sGruppe.equals("zone"))
			RunZone();
                else if (sGruppe.equals("IntusCom"))
			RunIntusCom();
                else if (sGruppe.equals("string command"))
			RunStringCommand();
                else if (sGruppe.equals("transaction"))
			RunTransaction();
                else if (sGruppe.equals("SQL"))
			RunSQL();
                else if (sGruppe.equals("reserve"))
			RunReserve();
		else
			A.printError("Calc.NaechsterBefehl(): Die Anweisungsgruppe <"+sGruppe+"> steht nicht zur Verfügung!");*/

                if (A.bDebug)
                {
                  String sSpaltenInfo=sSpalte.equals("") || TabAbfrage==null ? null:" ->"+((A.iDBits&DPUSH)>0?TabAbfrage.getPush():"")+(TabAbfrage.out() ? " out":" Zeile "+TabAbfrage.getPos()+" = "+(TabAbfrage.getInhalt(sSpalte)==null ? "null":TabAbfrage.getInhalt(sSpalte)+" ("+TabAbfrage.getInhalt(sSpalte).getClass().getSimpleName()+")"));
                  if ((A.iDBits&TAB)>0)
                  {
                    A.TabDebug.newLine();
                    A.TabDebug.setInhalt("Nr",A.iAnzahl);A.TabDebug.setInhalt("Befehl",sBefehl);A.TabDebug.setInhalt("Zeile",sZeile);A.TabDebug.setInhalt("Spalteninfo",sSpaltenInfo);
                    aD(DMEM,"total Memory",Runtime.getRuntime().totalMemory());aD(DMEM,"free Memory",Runtime.getRuntime().freeMemory());
                    aD(DD,"d",A.dReg);aD(DDT,"dt",A.dtReg);aD(DB,"b",A.bReg);aD(DB3,"bool3",A.iB3Reg);aD(DI,"i",A.iReg);aD(DQRY,"Qry",A.iQryReg);aD(DBEW,"Bew",A.iBewReg);
                    aD(DSTT,"Stt",A.iStt);aD(DMASS,"Mass",A.iMass);aD(DPOS,"Pos",A.iPos);aD(DS,"s",A.sReg);aD(DVON,"von",A.getVon());aD(DBIS,"bis",A.getBis());
                    aD(DD2,"d2",A.dReg2);aD(DDT2,"dt2",A.dtReg2);aD(DST,"Stichtag",A.dtStichtag);aD(DI2,"i2",A.iReg2);aD(DQRY2,"Qry2",A.iQryReg2);aD(DBEW2,"Bew2",A.iBewReg2);
                    aD(DS2,"s2",A.sReg2);aD(DTRENN,"Trenn",A.sTrenn);aD(DJDT,"Joker-Stichtag",A.g.dtStichtag);
                    aD(DJI,"iJoker",A.g.iJoker);aD(DJS,"sJoker",A.g.sJoker);aD(DVEC,"VecAic",A.VecAic);
                    aD(DZONE,"Zone",A.iZone+(A.bZone ? "+":""));aD(DEST,"Ziel",A.sDest);aD(FIRMA,"Firma",A.iFirma);aD(DAT,"Daten",A.iDaten);
                    aD(DVSA,"VecStamm",A.VecStamm);aD(DVECS,"VecString",A.StaString);aD(DVECB,"VecBew",A.VecBewPool);aD(DGPS,"GPS",A.gps);
                    aD(DMANDANT,"Mandant",A.iMReg>0 ? A.g.getMandant(A.iMReg,"Kennung"):null);aD(DANL,"Anlage",A.iAnlage==0?null:A.g.TabCodes.getKennung(A.iAnlage));
                    /*aD(DVAR,"Var",getAllVar());*/aD(DSAVE,"Save",A.TabAbfragen.getInhalt("save") == null ? null:((Tabellenspeicher)A.TabAbfragen.getInhalt("save")).size());
                    if ((A.iDBits&DVAR)>0 && A.TabVar!=null)
                      for(int i=0;i<A.TabVar.size();i++)
                      {
                        String sVar="Var " + A.TabVar.getS(i, "Var");
                        if (!A.TabDebug.exists(sVar))
                          A.TabDebug.addTitel(sVar);
                        A.TabDebug.setInhalt(sVar, A.TabVar.getS(i, "Wert"));
                      }
                    aD(DC,"Clock",Static.get_ms()-lClock2);
                  }
                  lClock3=Static.get_ms()-lClock3;
                  if (sSpaltenInfo != null || lClock3>0 && (A.iDBits&DC)>0)
                    A.debugInfo(Static.FillNull(""+A.iAnzahl,6)+":"+sZeile+"/"+((A.iDBits&DC)>0?lClock3+" ms":"")+
                              ((A.iDBits&DMEM)>0?"/"+Static.printZahl(Runtime.getRuntime().totalMemory(),11)+"/"+Static.printZahl(Runtime.getRuntime().freeMemory(),11):"")+(sSpaltenInfo != null ? ":"+sSpaltenInfo:""));
		  String sDebug=((A.iDBits&DD)>0?" d="+checkUm(A.dReg):"")+((A.iDBits&DDT)>0?" dt="+A.dtReg:"")+((A.iDBits&DB)>0?" b="+A.bReg:"")+((A.iDBits&DB3)>0?" bool3="+A.iB3Reg:"")+
                              ((A.iDBits&DI)>0?" i="+cSAic(A.iReg,DI):"")+((A.iDBits&DQRY)>0?" Qry="+cSAic(A.iQryReg,DQRY):"")+((A.iDBits&DBEW)>0?" Bew="+A.iBewReg:"")+
                              ((A.iDBits&DSTT)>0?" Stt="+A.iStt:"")+((A.iDBits&DMASS)>0?" Mass="+A.iMass:"")+((A.iDBits&DPOS)>0?" Pos="+A.iPos:"")+
                              ((A.iDBits&DS)>0?" s="+A.sReg:"")+((A.iDBits&DVON)>0?" von="+A.getVon():"")+((A.iDBits&DBIS)>0?" bis="+A.getBis()/*+" VecPerioden="+A.g.VecPerioden*/:"")+
                              ((A.iDBits&DD2)>0?" d2="+checkUm(A.dReg2):"")+((A.iDBits&DDT2)>0?" dt2="+A.dtReg2:"")+((A.iDBits&DST)>0?" Stichtag="+A.dtStichtag:"")+
                              ((A.iDBits&DI2)>0?" i2="+cSAic(A.iReg2,DI2):"")+((A.iDBits&DQRY2)>0?" Qry2="+cSAic(A.iQryReg2,DQRY2):"")+((A.iDBits&DBEW2)>0?" Bew2="+A.iBewReg2:"")+
                              ((A.iDBits&DS2)>0?" s2="+A.sReg2:"")+((A.iDBits&DTRENN)>0?" Trenn="+A.sTrenn:"")+((A.iDBits&DJDT)>0?" Joker-Stichtag="+A.g.dtStichtag:"")+
                              ((A.iDBits&DJI)>0?" iJoker="+A.g.iJoker:"")+((A.iDBits&DJS)>0?" sJoker="+A.g.sJoker:"")+((A.iDBits&DVEC)>0?" VecAic="+A.VecAic:"")+
                              ((A.iDBits&DZONE)>0?" Zone="+A.iZone+(A.bZone ? "+":""):"")+((A.iDBits&DEST)>0?" Ziel="+A.sDest:"")+((A.iDBits&FIRMA)>0?" Firma="+A.iFirma:"")+((A.iDBits&DAT)>0?" Daten="+A.iDaten:"")+
                              ((A.iDBits&DVSA)>0?" VecStamm="+A.VecStamm:"")+((A.iDBits&DVECS)>0?" VecString="+A.StaString:"")+((A.iDBits&DVECB)>0?" VecBew="+A.VecBewPool:"")+(A.gps!=null && (A.iDBits&DGPS)>0 ? " GPS="+A.gps:"")+
                              (iFC>0 ? " FC="+iFC:"");
                  if ((A.iDBits&DMANDANT)>0)
                    sDebug+=" Mandant="+A.g.getMandant(A.iMReg,"Kennung");
                  if ((A.iDBits&DANL)>0)
                    sDebug+=" Anlage="+(A.iAnlage==0?"":A.g.TabCodes.getKennung(A.iAnlage));
                  if ((A.iDBits&DVAR)>0)
                    sDebug+=" Var:"+getAllVar();
                  if ((A.iDBits&DSAVE)>0 && A.TabAbfragen.getInhalt("save") != null)
                    sDebug+=" Save="+((Tabellenspeicher)A.TabAbfragen.getInhalt("save")).size();
                  if ((A.iDBits&AIC)>0 && TabAbfrage!=null && !TabAbfrage.out())
                  	sDebug+=TabAbfrage.exists(iBew==0 ? "aic_stamm":"aic_bew_pool") ? " Aic="+(iBew==0 ? A.g.getStammI(TabAbfrage.getI("aic_stamm")):TabAbfrage.getS("aic_bew_pool")):"";
                  if (bVar)
                  	sDebug+=" -> "+sVar+"="+showSpalte(getObj(sVar)); 
                  A.debugInfo(" ->"+sDebug);//+"\r");
                  //lClock2 = Static.get_ms();
                }
                return b;
	}

  private String checkUm(double d)
  {
    String s=""+d;
    if (d>60 && (A.iDBits&UMRDT)>0)
    {
			DateWOD dtReg=new DateWOD(Math.round(d*1000.0));
			dtReg.setTimezoneOffset();
      s+="/"+dtReg.Format(A.g,"yyyy-MM-dd HH:mm:ss");
		}
    if (/*Math.abs(d)>3600 &&*/ (A.iDBits&UMRT)>0)
      s+="/"+new Zahl1(d/86400.0,"#,##0.0000")+"d";
    if (/*Math.abs(d)>1800 &&*/ (A.iDBits&UMRH)>0)
      s+="/"+new Zahl1(d/3600.0,"#,##0.0000")+"h";
    return s;
  }
	
	private Object cSAic (Object Obj,long l)
	{
		if ((A.iDBits&BEZ)>0 && ((l==DQRY) || (l==DQRY2) || (l==DI) || (l==DI2)) && Obj instanceof Integer && Sort.geti(Obj)>0)
            return A.g.getStammI(Sort.geti(Obj));
		else
			return Obj;
	}

        private void aD(long l,String s,Object Obj)
        {
          if ((A.iDBits&l)>0)       	
            A.TabDebug.setInhalt(s,cSAic(Obj,l));
        }

        /*private void aD(long l,String s,double d)
        {
          if ((A.iDBits&l)>0)
            A.TabDebug.addInhalt(s,d);
        }

        private void aD(long l,String s,DateWOD dt)
        {
          if ((A.iDBits&l)>0)
              A.TabDebug.addInhalt(s,dt);
        }*/
        
        private void showMessage(boolean bInfo,boolean b2,int iSek,String sTitel)
        {
//        	if (A.FomFX != null)
//        	{
//        		if (bInfo)
//        			new JFXSnackbar((BorderPane)A.FomFX.getScene().getRoot()).show(getMemo(), iSek*1000);
//        			//MessageFX.Info(A.g, A.FomFX, iSek, sTitel,new String[] {getMemo()});
////        		else
////        			MessageFX.Warning(A.g, A.FomFX, iSek, false,sTitel,new String[] {getMemo()});
//        		//new MessageFX(bInfo ? MessageFX.INFORMATION_MESSAGE:MessageFX.WARNING_MESSAGE,A.FomFX,A.g,iSek).showDialog(sTitel,new String[] {getMemo()});
//        	}
//        	else 
        	if (Static.bWeb)
        	{
        		//String s=A.g.getMsg(sTitel,new String[] {getMemo()});
        		Vector<String> Vec=A.g.getTranslate("Message",sTitel,new String[] {getMemo()},false);
        		A.iMsgArt=bInfo ? 1:2;
        		A.sInfo=Sort.gets(Vec, 1);
        		A.sHeader=Sort.gets(Vec, 0);
        		A.g.fixtestInfo((bInfo ? "Info:":"Warnung:")+A.sInfo);
        	}
        	else if ((!A.bTimer || bInfo && b2) && A.in == null)
        	{
        		long lClock2=Static.get_ms(); 
	            new Message(bInfo ? Message.INFORMATION_MESSAGE:Message.WARNING_MESSAGE,A.FomA,A.g,iSek).showDialog(sTitel,new String[] {getMemo()});
        		A.lClockDlg+=Static.get_ms()-lClock2;
        		A.g.fixtestError("showMessage "+sTitel+"->"+A.lClockDlg);
        	}
	        else if (A.in != null)
	            AClient.sendInfo(bInfo,b2,getMemo(),A.out);
        }
        
        private void addBack(Tabellenspeicher Tab,int iPos,int iAbf,String sEin)
        {
        	if (A.TabBack==null)
        		A.TabBack=new Tabellenspeicher(A.g,new String[] {"Eingabe","Abfrage","Pos","Tab"});
        	int iP=A.TabBack.newLine();
        	A.TabBack.setInhalt(iP, "Eingabe", sEin);
        	A.TabBack.setInhalt(iP, "Abfrage", iAbf);
        	A.TabBack.setInhalt(iP, "Pos", iPos);
        	A.TabBack.setInhalt(iP, "Tab", new Tabellenspeicher(A.g,Tab));
        }
        
        private void sendWeb(int iArt) // 1..Var, 2..Modell
        {
        	String sURL=Static.sWeb;
        	if (!Static.Leer(sURL))
        	{
        		try {
        		URL url = new URL(sURL+"/webCalc/"+(iArt==1 ? "var1":"xx"));
        		HttpURLConnection con = (HttpURLConnection)url.openConnection();
        		con.setRequestMethod("POST");
        		con.setDoOutput(true);
        		con.setRequestProperty("Content-Type", "application/json");
        		con.setConnectTimeout(5000);
        		con.setReadTimeout(5000);
        		if (iArt==1)
        		{
        			con.setRequestProperty("var",sVar);  			
        			con.setRequestProperty("DB", A.g.getDB());      			
        			if (sEingabe != null || posVar(sVar,false))
        			{
	        			con.setRequestProperty("user", ""+(sEingabe==null ? A.TabVar.getI("Logging"):A.g.getLog()));
	        			con.setRequestProperty("art", ""+(sEingabe==null ? A.TabVar.getI("Art"):iVArt2));
	        			Object Obj=sEingabe==null ? A.TabVar.getInhalt("Wert"):sEingabe;
	        			if (webLog.bVarInfo)
	        				A.g.fixtestError(sVar+"="+Static.print(Obj));
	        			con.setRequestProperty("type",Obj instanceof Boolean ? "boolean":Obj instanceof Double ? "double":Obj instanceof Integer ? "int":Obj instanceof DateWOD ? "date":"string");
	        			con.setRequestProperty("wert",Obj instanceof Boolean ? getb(sVar) ? "1":"0":Obj instanceof Double ? ""+getd(sVar):Obj instanceof Integer ? ""+geti(sVar):Obj instanceof DateWOD ? ((DateWOD)Obj).Format(A.g,"yyyy-MM-dd HH:mm:ss"):""+Obj);
	        			con.setRequestProperty("perm",(sEingabe==null ? A.TabVar.isNull("perm"):!bPerm) ? "0":"1");
        			}
        		}
        		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String sIn=in.readLine();
				while (sIn != null) {
					A.g.fixInfo(sIn);
					sIn=in.readLine();
				}
				//System.out.println("\nCrunchify REST Service Invoked Successfully..");
				in.close();
        		} catch (Exception e) {
    				A.printError("sendWeb "+(iArt==1 ? "var "+sVar:"")+" geht nicht:"+e,501);
    			}
        	}
        }
        
    private void FilterBack()
    {
    	A.TabAbfragen.setInhalt("Abfrage2", null);
    	A.TabAbfragen.setInhalt("Filter", null);
    	String sBez=A.TabAbfragen.getS("Bezeichnung");
    	if (sBez.startsWith("*"))
    		A.TabAbfragen.setInhalt("Bezeichnung", sBez.substring(1));
    }

  private int getStt()
  {
    int iStt=A.TabSpalten.getI("Stt");
    if (iStt==0)
    {
      int iEig=A.TabSpalten.getI("Eig");
      iStt=A.g.TabEigenschaften.getI_AIC("AIC_Stammtyp", iEig);
    }
    return iStt;
  }

	@SuppressWarnings("unchecked")
	private void RunAnweisung()
	{
          //A.g.progInfo("RunAnweisung");
		if (sBefehl.equals("exit"))
		{
			A.printInfo("!!! Abbruch !!!");
			A.bAbbruch = true;
      bExit=true;
		}
		else if (sBefehl.equals("Model finish"))
			bModellende = true;
		else if (sBefehl.equals("break"))
		{
          if (sEingabe != null)
            A.debugInfo("break "+sEingabe);
          while (!TabBefehle.Empty_Stack() && !VecWhile.contains(TabBefehle.getI("AIC_BEFEHL")))
             TabBefehle.pop();
			iJa = TabBefehle.getI("BEF_AIC_BEFEHL");
			iNein =TabBefehle.getI("BEF2_AIC_BEFEHL");
			//A.g.progInfo("mom "+TabBefehle.getI("AIC_BEFEHL")+"/"+iJa+"/"+iNein);
//			iWhileBefehl=-1;
		}
		else if (sBefehl.equals("clear"))
                {
                  if (bVar && A.TabVar != null)
                	  if (sVar!=null)
                	  {
                		  if (A.TabVar.posInhalt("Var",sVar))
                			  if (Static.bWeb || A.TabVar.getI("Art")>1)
                				  A.TabVar.setInhalt("Wert",null);
                			  else
                				  A.TabVar.clearInhalt();  
                			if (webLog.bVarInfo)
                				A.g.fixtestError(A.g.getBegBez(A.iHauptModell)+" "+getZeile()+": lösche var "+sVar);

                	  }
                	  else if (!Static.bWeb)
                		  A.TabVar.clearAll();
                	  else
                	  {
//                		  A.TabVar.clearAll();
//                		  A.g.fixtestError("clear ohne Variable und Spalte, lösche deshalb alle von Art="+iVArt2);
                		  for (int i=0;i<A.TabVar.size();i++)
                			  if (A.TabVar.getI(i,"Art")==iVArt2)
                			  {
                				  A.TabVar.setInhalt(i,"Wert",null);
                				  if (webLog.bVarInfo)
                					  A.g.fixtestError(A.g.getBegBez(A.iHauptModell)+" "+getZeile()+": lösche var "+A.TabVar.getS(i,"Var"));  
                			  }
                	  }

                  else if (bSpalte)
                    TabAbfrage.clearAll();
                }
		else if (sBefehl.equals("clear row"))
			TabAbfrage.clearInhalt();
		else if (sBefehl.equals("set Joker"))
			A.g.iJoker=(bSpalte ? getAic() : A.iReg);
		else if (sBefehl.equals("set StringJoker"))
			A.g.sJoker=(bSpalte ? TabAbfrage.getM(A.TabSpalten.getS("Kennung3")) : A.sReg);
		else if (sBefehl.equals("set Sync-Stamm"))
                {
                  if (!bSpalte || !TabAbfrage.out())
                  {
                    int iSatz = bSpalte ? getAic() : A.iReg;
                    if (bVar)
                      A.g.setSyncStamm(geti(sVar)==0 && sEingabe != null ? EtoStt(sEingabe):getStt(sVar),geti(sVar),0,A.iVB);
                    else
                    {
                      int iStt=EtoStt(sEingabe);
                      A.debugInfo("set Sync-Stamm: "+iSatz+" bei Stt: "+iStt);
                      A.g.setSyncStamm(iStt>0 ? iStt:iSatz == 0 ? A.iStt : // vorläufig nicht auf Spalten-Stt
                                     SQL.getInteger(A.g, "select aic_stammtyp from stamm where aic_stamm=?",0,""+iSatz), iSatz,0,A.iVB);
                    }
                  }
                }
                else if (sBefehl.equals("set Sync-Vec"))
                {
                  if (!bSpalte || !TabAbfrage.out())
                  {
                    Vector Vec = bSpalte ? TabAbfrage.toVecAic(sSpalte) : bVar ? getVec(sVar): A.VecAic;
                    int iStt=EtoStt(sEingabe);
                    if(Vec == null || Vec.size() == 0)
                      A.g.setSyncStamm(iStt>0 ? iStt:A.iStt, Vec,A.iVB);
                    else if(Vec.size() > 0 && Tabellenspeicher.geti(Vec.elementAt(0)) > 0)
                      A.g.setSyncStamm(iStt>0 ? iStt:SQL.getInteger(A.g, "select aic_stammtyp from stamm where aic_stamm=" + Vec.elementAt(0)), Vec,A.iVB);
                  }
                }
                else if (sBefehl.equals("add Sync-Vec"))
                {
                  if (!bSpalte || !TabAbfrage.out())
                  {
                    int iSatz=bSpalte ? getAic() : A.iReg;
                    int iStt=EtoStt(sEingabe);
                    if (bAlle)
                      A.g.addSyncVec(iStt>0 ? iStt:getStt(), bSpalte ? TabAbfrage.getVecSpalteI(sSpalte):new Vector(A.VecStamm),A.iVB);
                    else
                      A.g.addSyncStamm(iStt>0 ? iStt:SQL.getInteger(A.g,"select aic_stammtyp from stamm where aic_stamm=?",0,""+iSatz), iSatz,A.iVB);
                  }
                }
                else if (sBefehl.equals("add JokerBew"))
                  A.g.addJokerBew(bSpalte?TabAbfrage.getI(sSpalte):A.iBewReg);
                else if (sBefehl.equals("Befehl190"))
                	A.g.setJokerBew(A.VecBewPool);
		else if (sBefehl.equals("clear query"))
		{
			if (bSpalte)
			{
				int iAbfrage=A.TabSpalten.getI("Abfrage");
				while (A.TabSpalten.posInhalt("Abfrage",iAbfrage))
					A.TabSpalten.clearInhalt();

				((Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1")).clearAll();
				A.TabAbfragen.clearInhalt();
				A.iAnzahlDel++;
				//System.gc();
			}
			else
			{
				A.TabSpalten.clearAll();
				for(A.TabAbfragen.moveFirst();!A.TabAbfragen.eof();A.TabAbfragen.moveNext())
				{
					((Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1")).clearAll();
					A.iAnzahlDel++;
				}
				A.TabAbfragen.clearAll();
				//System.gc();
			}
		}
		else if (sBefehl.equals("new line"))
		{
			TabAbfrage.newLine();
			A.g.printExec(MPos()+": [new line] bei "+A.g.getBeg(A.TabAbfragen.getI("Begriff"))+(A.TabAbfragen.isNull("Filter") ? "":"-Filter"),false);
			clearDataRecord();
		}
		else if (sBefehl.equals("insert line"))
		{
			TabAbfrage.bInsert = !TabAbfrage.out();
			TabAbfrage.newLine();
                        clearDataRecord();
			TabAbfrage.bInsert = false;
		}
		else if (sBefehl.equals("sort down"))
		{
			//long lClock3 = Static.get_ms();
			TabAbfrage.sort(sSpalte,false);
			//long lClock4 = Static.get_ms();
			//A.g.progInfo("..sort down:"+TabAbfrage.getAnzahlElemente(null)+" Zeilen="+(lClock4-lClock3)+"ms");
		}
		else if (sBefehl.equals("sort up"))
		{
			//long lClock3 = Static.get_ms();
			TabAbfrage.sort(sSpalte,true);
			//long lClock4 = Static.get_ms();
			//A.g.progInfo("..sort up:"+TabAbfrage.getAnzahlElemente(null)+" Zeilen="+(lClock4-lClock3)+"ms");
		}
		else if (sBefehl.equals("sort String down"))
		{
			//long lClock3 = Static.get_ms();
			TabAbfrage.sort(A.TabSpalten.getS("Kennung3"),false);
			//long lClock4 = Static.get_ms();
			//A.g.progInfo("..sort down:"+TabAbfrage.getAnzahlElemente(null)+" Zeilen="+(lClock4-lClock3)+"ms");
		}
		else if (sBefehl.equals("sort String up"))
		{
			//long lClock3 = Static.get_ms();
			TabAbfrage.sort(A.TabSpalten.getS("Kennung3"),true);
			//long lClock4 = Static.get_ms();
			//A.g.progInfo("..sort up:"+TabAbfrage.getAnzahlElemente(null)+" Zeilen="+(lClock4-lClock3)+"ms");
		}
		else if (sBefehl.equals("not refetch"))
			bNotRefetch=true;
		else if (sBefehl.equals("refetch"))
			bRefetch=true;
    else if (sBefehl.equals("use VecAic"))
			bUseVecAic=true;
    else if (sBefehl.equals("use VecBew"))
			bUseVecBew=true;
		else if (sBefehl.equals("delete all"))
		{
			//A.debugInfo("delete: Bew="+iBew+", Stamm="+A.iQryReg+(iPeriode==0?" keine Periode":" von "+A.g.getVonSql()+" bis "+A.g.getBisSql()));
			if (iBew==0)
                A.printError("<delete all> nur bei Bewegung aufrufbar",400);
            else
            	A.deleteAll(TabAbfrage.getVecSpalte("aic_bew_pool"));
			TabAbfrage.clearAll();
		}
		else if (sBefehl.equals("delete row"))
		{
                  boolean bEingabe=sEingabe != null && sEingabe.length()>0;
                  if (bEingabe)
                    A.iAnlage = A.g.getCodeAic("Anlage",sEingabe);
                  if (iBew>0)
                  {
                	  int iPool=TabAbfrage.getI("aic_bew_pool");
                	  if ((A.g.getBewBits(iBew)&Global.cstBewBew)>0)
					  {
							int iBewBew=SQL.getInteger(A.g,"select bew2_aic_bew_pool from bew_pool where aic_Bew_Pool="+iPool);
							if (iBewBew>0)
								A.g.exec("update bew_pool set pro_aic_protokoll="+A.getProt()+" where aic_bew_pool="+iBewBew+" and pro_aic_protokoll is null");
					  }
                      A.g.exec("update bew_pool set pro_aic_protokoll="+A.getProt()+" where aic_bew_pool="+iPool);
                  }
                  else
                  {
                    if (!bEingabe)
                    {
                      A.g.exec("update stammpool set pro2_aic_protokoll=" + A.getProt() + " where pro2_aic_protokoll is null and sta_aic_stamm=" + TabAbfrage.getI("aic_stamm"));
                      A.g.exec("update stammpool set pro2_aic_protokoll=" + A.getProt() + " where pro2_aic_protokoll is null and aic_stamm=" + TabAbfrage.getI("aic_stamm"));
                    }
                    A.g.exec("update stamm_protokoll set pro_aic_protokoll="+A.getProt()+(bEingabe ? ",aic_code="+A.iAnlage:"")+
                             " where pro_aic_protokoll is null and aic_stamm="+TabAbfrage.getI("aic_stamm"));
                  }
                  TabAbfrage.clearInhalt();
		}
        else if (sBefehl.equals("Befehl178")) // undelete row
        	A.g.exec("update bew_pool set pro_aic_protokoll=null where aic_bew_pool="+TabAbfrage.getI("aic_bew_pool"));
        else if (sBefehl.equals("Befehl179")) // doku Line
        	dokuLine();
                else if (sBefehl.equals("destroy row"))
                {
                  if (iBew==0)
                	  A.bReg=A.destroyStamm(TabAbfrage.getI("aic_stamm"),((Abfrage)A.TabAbfragen.getInhalt("AF")).iRolle);
                    //A.printError("destroy row nur bei Bewegung aufrufbar");
                  else
                  {
                    A.destroyBewPool(A.g,Static.AicToVec(TabAbfrage.getI("aic_bew_pool")));
                    TabAbfrage.clearInhalt();
                  }
                }
                else if (sBefehl.equals("destroy all"))
                {
                  if (iBew==0)
                    A.printError("destroy all nur bei Bewegung aufrufbar",400);
                  else
                  {
                    A.destroyAll(A.g,TabAbfrage.getVecSpalte("aic_bew_pool"));
                    TabAbfrage.clearAll();
                  }
                }
                else if (sBefehl.equals("del del bew"))
		{
                  if (iBew==0)
                    A.printError("<del del bew> nur bei Bewegung aufrufbar",400);
                  else
                    A.DelDelBew(A.g,iBew,A.dtReg);
                }
		//else if (sBefehl.equals("del deleted"))
        //          A.printError("<del deleted> nur noch über AServer");
                  //A.DelDeleted(A.g,A.dtReg,false);
        //        else if (sBefehl.equals("clear old data"))
        //          A.printError("<clear old data> nur noch über AServer");//ClearOldData();
		else if (sBefehl.equals("delete file"))
		{
                        A.bIO_Error=true;
			File fil = new File(A.sReg);
			if (fil.exists())
				A.bIO_Error=!fil.delete();
		}
		else if (sBefehl.equals("rename file"))
		{
			//A.g.progInfo("rename file:"+A.sReg);
                        A.bIO_Error=true;
			File fil = new File(A.sReg);
			if (fil.exists())
			{
				String sFileNew=A.sReg.substring(0,A.sReg.indexOf('.'));
				if (bSpalte)
					sFileNew = getM3b()+sFileNew.substring(sFileNew.lastIndexOf(File.separator)+1);
				sFileNew += new java.text.SimpleDateFormat("_yyyyMMdd_HHmmss").format(new java.util.Date())+".prot";
				//A.g.progInfo("sFileNew:"+sFileNew);
				A.debugInfo("Neuer Filename:<"+sFileNew+">");
                                A.sReg=sFileNew;
				File fil2= new File(sFileNew);
				A.bIO_Error=!fil.renameTo(fil2);
			}
		}
		else if (sBefehl.equals("open file"))
		{
			A.g.openFile(A.sReg);
		}
                else if (sBefehl.equals("open URL"))
                {
                        Static.OpenURL(bSpalte ? TabAbfrage.getM(A.TabSpalten.getS("Kennung3")):A.sReg);
                }
		else if (sBefehl.equals("create file"))
		{
                  A.bIO_Error=false;
			File fil = new File(A.sReg);
			if (!fil.exists())
			{
				try
				{
					FileOutputStream fos=new FileOutputStream(A.sReg,false);
					fos.close();
				}
				catch (IOException ex)
				{
                                  A.bIO_Error=true;
					A.printError("Calc.RunAnweisung: create file - IOException - "+ex,400);
				}
			}
		}
		else if (sBefehl.equals("Befehl146")) // getDoku
			CCo(SQL.getDoku(A.g,getM3()));
		else if (sBefehl.equals("Befehl147")) // get Filename
			CCo(getFileOnly(getM3()));
		else if (sBefehl.equals("Befehl148")) // set web-var
			sendWeb(1);
		else if (sBefehl.equals("Befehl149")) // call web-Modell
			;
		else if (sBefehl.equals("Anweisung20")) // send Msg
                  if (Static.bWeb)
                    webLog.newMsgF(A.g.getDB(),TabAbfrage.getI("aic_bew_pool"),getAic());
                  else
        	         A.g.sendWebDB("newMsg",TabAbfrage.getI("aic_bew_pool")+":"+getAic(),null);
		else if (sBefehl.equals("Befehl160")) // sendWebDB
			if (Static.bWeb)
				A.printError("sendWebDB wird vom Web noch nicht unterstützt",501);
			else
				A.g.sendWebDB(getS(),null,null);
		else if (sBefehl.equals("Befehl150")) // get Web-Server
			CCo(Static.sWeb);
		else if (sBefehl.equals("Befehl152")) // Debug Zeile
			addBack(TabAbfrage,TabAbfrage.getPos(),A.TabSpalten.getI("Abfrage"),sEingabe);
		else if (sBefehl.equals("Befehl153")) // Debug Tabelle
			addBack(TabAbfrage,-2,A.TabSpalten.getI("Abfrage"),sEingabe);
                else if (sBefehl.equals("fix group"))
                  TabAbfrage.sGruppe=sSpalte;
                else if (sBefehl.equals("set table"))
                {
                  A.TabGesamt = TabAbfrage;
                  A.TabGesamt.sGruppe=sSpalte;
                }
                else if (sBefehl.equals("copy to"))
                {
                  if (TabAbfrage!=null && A.TabGesamt!=null)
                    A.TabGesamt.copyTo(TabAbfrage);
                }
                else if (sBefehl.equals("to VecAic"))
                  A.VecAic=bVar ? getVec(sVar): bGroup ? TabAbfrage.groupVecAic(sSpalte):TabAbfrage.toVecAic(sSpalte);
                else if (sBefehl.equals("clear with"))
                  A.TabGesamt.clearWith(TabAbfrage.getVecSpalte(sSpalte));
                else if (sBefehl.equals("compress"))
                  TabAbfrage.compress(A.TabSpalten.getS("Kennung"),true);
                else if (sBefehl.equals("compress2"))
                  TabAbfrage.compress2(A.TabSpalten,null);
                //else if (sBefehl.equals("use Sync"))
                //  bUseSync=true;
                else if (sBefehl.equals("next empty"))
                  bNextEmpty=true;
                else if (sBefehl.equals("get changes"))
                {
                  Vector Vec = SQL.getVector("select aic_stamm from stammpool where pro2_aic_protokoll is null and "+
                                               A.g.in("aic_eigenschaft",getEigenschaften())+" and "+
                                               A.g.in("aic_stamm",A.VecAic)+" and gultig_von>="+A.g.von()+" and gultig_von<"+A.g.bis(), A.g);
                  A.VecAic=VecToStack(Vec);
//                		  new Stack<Integer>();
//                  for (int i=0;i<Vec.size();i++)
//                    A.VecAic.push(Sort.getInt(Vec,i));
                }
                else if (sBefehl.equals("get changes2"))
                  getChanges2();
                else if (sBefehl.equals("clean prot")) // Filter rücksetzen
                	FilterBack();
                //else if (sBefehl.equals("clean prot"))
                //  A.printError("Protokoll-Reinigung nur noch über AServer");
                  //Reinigung.ProtokollCheck(A.g,null,100000,null,null);
                else if (sBefehl.equals("set general finish"))
                {
                  A.g.testInfo("set general finish");
                  A.g.setAbschluss(iBew,A.dtReg.toTimestamp(),A.getProt(),1);
                  if (!Static.bWeb)
                	  AClient.send_AServer("abschluss",A.g);
                }
                else if (sBefehl.equals("set superior finish"))
                {
                  A.g.testInfo("set superior finish");
                  A.g.setAbschluss(iBew,A.dtReg.toTimestamp(),A.getProt(),2);
                  if (!Static.bWeb)
                	  AClient.send_AServer("abschluss",A.g);
                }
                else if (sBefehl.equals("set personal finish"))
                {
                  A.g.testInfo("set personal finish");
                  A.g.setAbschluss(iBew,A.dtReg.toTimestamp(),A.getProt(),0);
                  if (!Static.bWeb)
                	  AClient.send_AServer("abschluss",A.g);
                }
                else if (sBefehl.equals("Befehl143")) // set Qry-Finish
                {
                	A.g.setAbschluss(sEingabe,A.dtReg.toTimestamp(),A.getProt(),A.iQryReg); //Abschluss laut Qry setzen
                	if (!Static.bWeb)
                		AClient.send_AServer("abschluss",A.g);
                }
                else if (sBefehl.equals("add to"))
                  A.TabGesamt.addTo(TabAbfrage,false);
                //else if (sBefehl.equals("use cache"))
                //  bCache=true;
                else if (sBefehl.equals("read directory"))
                  A.bIO_Error=!TabAbfrage.getDir(A.StaString,iBew==0 ? 1:2);
                else if (sBefehl.equals("clear query except"))
                {
                  int iAbfrage=A.TabSpalten.getI("Abfrage");
                  for(A.TabSpalten.moveFirst();!A.TabSpalten.eof();)
                    if (A.TabSpalten.getI("Abfrage")!=iAbfrage)
                      A.TabSpalten.clearInhalt();
                    else
                      A.TabSpalten.moveNext();
                  for(A.TabAbfragen.moveFirst();!A.TabAbfragen.eof();)
                    if (A.TabAbfragen.getI("Aic")!=iAbfrage)
                    {
                      ((Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1")).clearAll();
                      A.TabAbfragen.clearInhalt();
                      A.iAnzahlDel++;
                    }
                    else
                      A.TabAbfragen.moveNext();
                }
                else if (sBefehl.equals("set testprint"))
                  A.g.bTestdruck=true;
                else if (sBefehl.equals("set edit"))
                  A.TabSpalten.setInhalt("bits",A.TabSpalten.getI("bits") | Abfrage.cstEditierbar);
                else if (sBefehl.equals("set hide"))
                  A.TabSpalten.setInhalt("bits",A.TabSpalten.getI("bits") | Abfrage.cstUnsichtbar);
                else if (sBefehl.equals("Befehl163")) // set allways
                  A.TabSpalten.setInhalt("bits",A.TabSpalten.getI("bits") | Global.cstAlways*0x10000);
                else if (sBefehl.equals("Befehl165")) // set banned
                  A.TabSpalten.setInhalt("bits3",A.TabSpalten.getI("bits3") | Abfrage.cstBanned);
                else if (sBefehl.equals("reserve129")) // set sichtbar
                {
                  if ((A.TabSpalten.getI("bits")&Abfrage.cstUnsichtbar)>0)
                	A.TabSpalten.setInhalt("bits",A.TabSpalten.getI("bits") - Abfrage.cstUnsichtbar);
                }
//                else if (sBefehl.equals("reserve137")) // set kein Stamm-Zeitraum
//                		A.TabAbfragen.setInhalt("bits",A.TabAbfragen.getI("bits") | Abfrage.cstKeinZeitraum);	
                else if (sBefehl.equals("reserve137"))
                  setVec(sVar,A.VecAic);
                else if (sBefehl.equals("clearInhalt2"))
                  TabAbfrage.clearInhalt2();
                else if (sBefehl.equals("info message"))
                	showMessage(true,false,10,"Info");  
                else if (sBefehl.equals("copy bew"))
                  A.iBewReg=Import.Undelete(A.g,TabAbfrage.getI("aic_bew_pool"), A.getProt(),false);
          else if (sBefehl.equals("col to row"))
          {
            Tabellenspeicher Tab=new Tabellenspeicher(A.g,"select anr,anr2,gueltig,aic_stamm,wert summe from bew_spalte s join bewview"+A.ViewNr()+" p on s.aic_bew_pool=p.aic_bew_pool where aic_bewegungstyp="+iBew+
                " and "+(A.VecBewPool!=null ? A.g.in("p.aic_bew_pool",A.VecBewPool):(A.TabAbfragen.getI("bits")&Abfrage.cstMengen)==0 ? "p.anr="+A.iQryReg:A.g.in("p.anr",A.VecAic))+
                " and aic_eigenschaft="+A.TabSpalten.getI("Eig")/*+" and gueltig>="+A.g.von()+" and gueltig<"+A.g.bis()*/+" order by anr,gueltig",true);
            int iPos=A.TabSpalten.getPos("Abfrage",A.TabSpalten.getInhalt("Abfrage"));
            TabAbfrage.clearAll();
            //if (A.g.Prog())
            //  Tab.showGrid("col to row");
            for (Tab.moveFirst();!Tab.out();Tab.moveNext())
            {
              TabAbfrage.newLine();
              TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung"),Tab.getI("anr"));
              TabAbfrage.setInhalt(A.TabSpalten.getS(iPos+1,"Kennung"),Tab.getInhalt("gueltig"));
              TabAbfrage.setInhalt(A.TabSpalten.getS(iPos+2,"Kennung"),Tab.getI("aic_stamm"));
              TabAbfrage.setInhalt("E"+A.TabSpalten.getI("Eig"),new Double(Static.Round6(Tab.getF("summe"))));
              if (A.TabSpalten.size()>iPos+4 && A.TabSpalten.getI(iPos+4,"Abfrage")==A.TabSpalten.getI("Abfrage"))
                TabAbfrage.setInhalt(A.TabSpalten.getS(iPos+4,"Kennung"),Tab.getI("anr2"));
            }
            TabAbfrage.moveFirst();
            //if (A.g.Prog())
            //  TabAbfrage.showGrid("TabAbfrage");
          }
          else if (sBefehl.equals("get History"))
          {
            int iBits= A.TabAbfragen.getI("bits");
            String sDatentyp=A.TabSpalten.getS("Datentyp");
            String sSpalte=sDatentyp.equals("Integer") || sDatentyp.equals("Double") || sDatentyp.equals("Boolean") ? "spalte_double":sDatentyp.equals("Stringx") ? "Spalte_Stringx":"sta_aic_stamm";
            Tabellenspeicher Tab=new Tabellenspeicher(A.g,sDatentyp.equals("Bezeichnung") ? "select bezeichnung,eintritt gultig_von,austritt gueltig_bis from stamm_protokoll where pro_aic_protokoll is null and aic_stamm="+A.iQryReg+
                Abfrage.Rolle(((Abfrage)A.TabAbfragen.getInhalt("AF")).iRolle):
                "select "+sSpalte+",gultig_von,gueltig_bis from stammpool"+(sDatentyp.equals("Stringx")?" join daten_stringx d on d.aic_daten=stammpool.aic_daten":"")+" where aic_stamm="+A.iQryReg+
                ((iBits&Abfrage.cstEntfernte)==0 ? " and pro2_aic_protokoll is null":"")+" and aic_eigenschaft="+A.TabSpalten.getI("Eig")+
                ((iBits&Abfrage.cstKeinStammZeitraum)==0 ? " and ("+A.g.von()+" is null or gueltig_bis is null or gueltig_bis >= "+A.g.von()+") and ("+
                 A.g.bis()+" is null or gultig_von is null or gultig_von < "+A.g.bis()+")":"")+" order by gultig_von",true);
            int iPos=A.TabSpalten.getPos("Abfrage",A.TabSpalten.getInhalt("Abfrage"));
            TabAbfrage.clearAll();
            //Tab.showGrid("Tab");
            for (Tab.moveFirst();!Tab.out();Tab.moveNext())
            {
              TabAbfrage.newLine();
              TabAbfrage.setInhalt(A.TabSpalten.getS(iPos+1,"Kennung"),Tab.getDate("gultig_von"));
              TabAbfrage.setInhalt(A.TabSpalten.getS(iPos+2,"Kennung"),Tab.getDate("gueltig_bis"));
              if (sDatentyp.equals("Gruppe") ||sDatentyp.equals("Hierarchie"))
                TabAbfrage.setInhalt("V"+A.TabSpalten.getI("Eig"),Tab.getI(sSpalte));
              else if (sDatentyp.equals("Stringx"))
                TabAbfrage.setInhalt("E"+A.TabSpalten.getI("Eig"),Tab.getS(sSpalte));
              else if (sDatentyp.equals("Boolean") || sDatentyp.equals("Integer"))
                TabAbfrage.setInhalt("E"+A.TabSpalten.getI("Eig"),Tab.getI(sSpalte));
              else if (sDatentyp.equals("Double"))
                TabAbfrage.setInhalt("E"+A.TabSpalten.getI("Eig"),Tab.getF(sSpalte));
              else if (sDatentyp.equals("Bezeichnung"))
                TabAbfrage.setInhalt("E"+A.TabSpalten.getI("Eig"),Tab.getS("Bezeichnung"));
            }
            TabAbfrage.moveFirst();
          }
          else if (sBefehl.equals("clearEmpty")) // reserve40
            clearEmpty();
          else if (sBefehl.equals("set dJoker")) // reserve42 // set dJoker
            A.g.dJoker=getF2();
          else if (sBefehl.equals("read Temp")) // reserve43 // Daten von Zwischenspeicher einlesen
            ReadZwischenspeicher(getM1(),TabAbfrage);
          else if (sBefehl.equals("copy new Line")) // reserve50
          {
            TabAbfrage.copyLine();
            //TabAbfrage.moveLast();
            TabAbfrage.setInhalt(iBew == 0 ? "aic_stamm" : "aic_bew_pool", null);
          }
          else if (sBefehl.equals("copy insert Line")) // reserve51
          {
            TabAbfrage.bInsert = !TabAbfrage.out();
            TabAbfrage.copyLine();
            TabAbfrage.setInhalt(iBew==0?"aic_stamm":"aic_bew_pool",null);
            TabAbfrage.bInsert = false;
          }
          else if (sBefehl.equals("copy file")) // reserve52
          {
            //A.bIO_Error=true;
        	
            File fil = new File(ohneFile(A.sReg));
            if (fil.exists())
            {
              A.bIO_Error=!Static.copyFile(fil,new File(ohneFile(getM3b())));
              /*try
              {
                BufferedReader Buf = new BufferedReader(new FileReader(fil));
                FileOutputStream fos = new FileOutputStream(getM3b(), false);
                String s = Buf.readLine();
                while (s != null)
                {
                  s+="\r\n";
                  fos.write(s.getBytes());
                  s = Buf.readLine();
                }
                fos.close();
                Buf.close();
                A.bIO_Error=false;
              }
              catch(Exception E)
              {
                Static.printError("Calc.copyFile:"+E);
                A.bIO_Error = true;
              }*/
            }
            else
            	A.g.fixtestError("File "+fil.getName()+" für copy nicht gefunden");
          }
          else if (sBefehl.equals("set prot-File")) // reserve53
          {
            A.sReg2=A.sReg.substring(0,A.sReg.lastIndexOf('.'));
            A.sReg2 += new java.text.SimpleDateFormat("_yyyyMMdd_HHmmss").format(new java.util.Date())+".prot";
          }
          else if (sBefehl.equals("create file2")) // reserve54
          {
                A.bIO_Error=false;
                //File fil = new File(A.sReg);
                //if (!fil.exists())
                //{
                        try
                        {
                                A.fos=new FileOutputStream(A.sReg,false);
                        }
                        catch (IOException ex)
                        {
                          A.bIO_Error=true;
                          A.printError(MPos()+"create file2 - IOException - "+ex,400);
                        }
                //}
          }
          else if (sBefehl.equals("comment"))
            ;
          else if (sBefehl.equals("testinfo"))
          {
            //if (A.g.Def())
            //{
              if (A.g.Clock())
                A.g.clockInfo("Testinfo: "+getM(),lClockD);
              else
                A.g.fixtestInfo(A.TabModelle.getS("Bezeichnung")+"/"+getZeile()+": "+getM());
              lClockD = Static.get_ms();
            //}
            //A.g.defInfo2("Testinfo: "+getM());
          }
          else if (sBefehl.equals("test message"))
          {
            if (A.g.Def() || A.g.TestPC())
            	showMessage(true,true,30,"Testmeldung");
          }
          else if (sBefehl.equals("set title"))
            A.TabSpalten.setInhalt("Bezeichnung",getM1());
          else if (sBefehl.equals("set destination"))
            A.sDest=sVar;
          else if (sBefehl.equals("clear destination"))
            A.sDest=null;
          else if (sBefehl.equals("set filter"))
            A.iFilter=A.TabSpalten.getI("Abfrage");
          else
            RunInit();
            //A.printError("Calc.RunAnweisung(): Die Anweisung >"+sBefehl+"< steht noch nicht zur Verfügung");
	} // RunAnweisung
	
	private void dokuLine()
	{
		String s=A.TabModelle.getS("Bezeichnung")+getZeile();
		String sA=getMo(1);
		if (!Static.Leer(sA) && iC_Art!=INIT)
			s=sA+"\r\n"+s;
		if (bVar)
			set(sVar, s);
		else if (bSpalte)
			SoR(s);
		else
			A.sReg=s;	
	}
	
	private String getFileOnly(String s)
	{
		int iPos=s.lastIndexOf(File.separator);
		if (iPos>-1)
			s=s.substring(iPos+1);
//		A.g.fixtestError("getFileOnly: "+File.separator+" auf Pos "+iPos+"->"+s);		
		return s.substring(s.indexOf("_")+1);
	}

        private Vector getEigenschaften()
        {
          Vector<Object> Vec=new Vector<Object>();
          A.TabSpalten.push();
          int iAbfrage=A.TabSpalten.getI("Abfrage");
          for(A.TabSpalten.posInhalt("Abfrage",iAbfrage);!A.TabSpalten.eof() && A.TabSpalten.getI("Abfrage")==iAbfrage;A.TabSpalten.moveNext())
            Vec.addElement(A.TabSpalten.getInhalt("Eig"));
          A.TabSpalten.pop();
          return Vec;
        }
        
    private String ohneFile(String s)
    {
    	if (s!=null && s.startsWith("file:"))
    		return s.substring(7);
    	else
    		return s;
    }
    
    private void setFilter()
    {
    	if (sEingabe==null)
    	{
    		A.printError("setFilter ohne "+sEingabe+" nicht möglich",400);
    		return;
    	}
    	char c=sEingabe.charAt(0);
    	boolean bPlus=c=='+' || c=='&' || c=='|';
    	boolean bOder=c=='|';
    	if (bPlus)
    		c=sEingabe.charAt(1);
    	if (bPlus && A.TabAbfragen.isNull("Abfrage2"))
    	{
    		sEingabe=sEingabe.substring(1);
    		bPlus=false;
    		bOder=false;
    	}
    	int iVor=bPlus ? 2:1;
    	Tabellenspeicher Tab=bOder ? (Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1"):new Tabellenspeicher(A.g,(Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage"+iVor));
    	Tabellenspeicher Tab2=bOder ? (Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage2"):null;
    	String s=sEingabe.length()>iVor ? sEingabe.substring(iVor):"";
//    	A.g.fixtestError(sEingabe+"/"+s+"/"+iVor);
    	boolean bReg=false;
    	boolean bSp=false;
    	boolean bE=false;
    	boolean bBis=false;
    	String sE=null;
    	char cVG='=';
    	if (c=='T' || c=='D' || c=='Z')
    	{
    		cVG=s.charAt(0);
    		if (s.length()>1)
    			sE=s.substring(1);
    		char cOrt=s.length()<2 ? '0':s.charAt(1);
	    	if (!bVar && s.length()>1 && !Character.isDigit(cOrt))
	    	{    		
	    		if (cOrt=='R')
	    		{
	    			bReg=true;
	    			bBis=s.length()==5 && s.substring(2).equals("bis");
	    		}
	    		else if (cOrt=='S')
	    		{
	    			bSp=true;
	    			bBis=s.length()==5 && s.substring(2).equals("bis");
	    		}
	    		else if (cOrt=='#')
	    		{
	    			bE=true;
	    			sE=s.substring(2);
	    		}
	    		else if (cOrt=='$')
	    		{
	    			bVar=true;
	    			sVar=s.substring(2);
	    		}
	    		else
	    		{
	    			bVar=true;
	    			sVar=s.substring(1);
	    		}
	    	}
	    	else if (!bVar && sE!= null && sE.length()>0)
	    		bE=true;
	    	if (!bReg && !bSp && !bVar && !bE)
	    		A.printError("setFilter fehlt Ortsangabe (Reg/Spalte/Var/Eingabe)",400);
    	}
    	else
    	{
    		if (!bVar && s.length()>0)
    		{
    			char cOrt=s.charAt(0);
    			if (cOrt=='R')
    				bReg=true;
    			else if (cOrt=='S')
    				bSp=true;
    			else if (cOrt=='#')
    			{
	    			bE=true;
	    			sE=s.substring(1);
	    		}	
    			else if (cOrt=='$')
	    		{
	    			bVar=true;
	    			sVar=s.substring(1);
	    		}
    			else
    			{
    				bE=true;
    				sE=s;
    			}
    		}
    		else if (c!='N' && c!='R' && c!='A')
    			A.printError("setFilter fehlt die Eingabe: "+sEingabe,400);
   		
    	}
    	A.debugInfo("set Filter: Typ:"+c+(bPlus ? bOder ? " or ":" and ":" ")+cVG+(bE ? " E:"+sE:bVar ? " Var:"+sVar:bSp ? " Spalte "+(bBis ? "bis":""):bReg ? " Reg":""));
    	
    	if (c=='N') // nicht Null
    		Tab.clearNull(sSpalte,Tab2);
    	else if (c=='B') // Boolean
    	{
    		boolean b=bSp ? Tab.getB(sSpalte):bVar ? getb(sVar):bReg ? A.bReg:sE.equals("1");
//    		A.g.fixtestError("Sp="+bSp+", bVar="+bVar+", bReg="+bReg+", sE="+sE+" -> "+b+"/"+(!s.equals("0")));
    		if (b)
    			Tab.clearNot(sSpalte, true,Tab2);
    		else
    			Tab.clearNotNull(sSpalte,Tab2,!s.equals("0"));
//    		&& (bVar && getb(sVar) || s.equals("1")))
//    		Tab.clearNot(sSpalte, true,Tab2);
//    	else if (c=='B' && (bVar && !getb(sVar) || s.equals("0")))
//    		Tab.clearNotNull(sSpalte,Tab2);
    	}
    	else if (c=='$') // String
    		Tab.clearNotS(A.TabSpalten.getS("Kennung3"), bVar ? gets(sVar): bReg ? A.sReg: bSp ? Tab.getS(A.TabSpalten.getS("Kennung3")):sE,Tab2);
    	else if (c=='R') // Relation z.B. BewStamm
    		Tab.clearNot(sSpalte, bSp ? Tab.getI(sSpalte): bVar ? geti(sVar): bE ? getIC(sE):bReg ? A.iReg:A.iQryReg,Tab2);
    	else if (c=='A') // Relation z.B. BewStamm
    		Tab.clearNot(sSpalte, bVar ? geti(sVar):A.iReg,Tab2);
    	else if (c=='X') // Bool3
    		Tab.clearNot(A.TabSpalten.getS("Kennung2"), bSp ? Tab.getI(A.TabSpalten.getS("Kennung2")):bVar ? geti(sVar): sE!=null ? Sort.geti(sE):A.iB3Reg,Tab2);//A.g.getAuswahlAic(A.g.getAuswahlPos(s.length()==0 ? ""+A.iB3Reg:s, A.TabSpalten.getI("Eig"))));
    	else if (c=='Z' && s.length()>0) // Zahl
    		Tab.clearNot(sSpalte, bVar ? getd(sVar):bSp ? Tab.getF(sSpalte):bReg ? A.dReg :Sort.getf(sE),cVG,Tab2);
    	else if (c=='T' && s.length()>0) // Datum/Zeit (Time)
    		Tab.clearNot(bBis ? A.TabSpalten.getS("Kennung2"):sSpalte, Sort.getf(bVar ? getdt(sVar):bSp ? getDWS(sSpalte,bBis): bReg ? A.dtReg:getDWE(sE)),cVG,Tab2);
    	else if (c=='D' && s.length()>0) // Datum
    		Tab.clearNot(bBis ? A.TabSpalten.getS("Kennung2"):sSpalte, bVar ? getdt(sVar):bSp ? getDWS(sSpalte,bBis): bReg ? A.dtReg:getDWE(sE),cVG,Tab2);
    	else
    		A.printError("setFilter mit "+sEingabe+" nicht verfügbar",400);
//    	Tab.showGrid(sSpalte+"="+sEingabe);
    	if (bOder)
    		Tab2.moveFirst();
    	else
    		Tab.moveFirst();
    	A.TabAbfragen.setInhalt("Abfrage2", bOder ? Tab2:Tab);
    	A.TabAbfragen.setInhalt("Filter", sSpalte+"="+sEingabe);
    	String sBez=A.TabAbfragen.getS("Bezeichnung");
    	if (!sBez.startsWith("*"))
    		A.TabAbfragen.setInhalt("Bezeichnung", "*"+sBez);
    }
    
    private void rememberFilterzeile()
    {
    	String sAic=iBew==0 ? "aic_stamm":"aic_bew_pool";
    	int iAic=TabAbfrage.getI(sAic);
    	Tabellenspeicher TabAbfO=(Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1");
    	int iPos=TabAbfO.getPos(sAic,iAic);
    	if (iPos<0)
    		iPos=TabAbfO.newLine();//A.printError("rememberFilterzeile: Zeile mit Aic="+iAic+" nicht gefunden",400);
//    	else
    		TabAbfO.copyLine(TabAbfrage,iPos);
    }

	private void RunInit()
	{
          //A.g.progInfo("RunInit");
        if (sBefehl.equals("init number"))
            if (bVar) set(sVar,getF2()); else A.dReg=sEingabe != null ? Sort.getf(sEingabe):0.0;
           //A.dReg= bVar ? getd(sEingabe) : sEingabe != null ? Sort.getf(sEingabe):0.0;
        else if (sBefehl.equals("init Bool3")) // Filter setzen
        	if (bSpalte)
        		setFilter();
        	else
        		A.iB3Reg=sEingabe==null ? 0:getI();
        else if (sBefehl.equals("Befehl177")) // Filterzeile übertragen
        	rememberFilterzeile();
        else if (sBefehl.equals("Befehl188"))
        	if (bVar) setGPS(sVar,A.gps); else A.gps=new GPS("  ");
        else if (sBefehl.equals("Befehl189"))
          A.iWebEdit=getI();
        else if (sBefehl.equals("init iReg"))
            if (bVar) set(sVar,A.iReg,0); else A.iReg=sEingabe != null ? Sort.geti(sEingabe):0;
        else if (sBefehl.equals("init Bew"))
			A.iBewReg=0;
		else if (sBefehl.equals("init Aic"))
		  if (bVar) set(sVar,A.iQryReg,0); else A.iQryReg=0;
		else if (sBefehl.equals("init VecAic"))
		  if (sEingabe != null && sEingabe.equals("-1"))
		    A.VecBewPool=new Vector<Integer>();
		  else
		    A.VecAic=null;
		else if (sBefehl.equals("init VecStamm"))
		{
			A.VecStamm.removeAllElements();
			A.iPos=0;
		}
                else if (sBefehl.equals("init JokerBew"))
			A.g.initJokerBew();
                else if (sBefehl.equals("init pos")) // neu 10.2.2004
                        A.iPos=0;
		else if (sBefehl.equals("init VecString"))
			A.StaString.removeAllElements();
		else if (sBefehl.equals("init date"))
                  if (bVar) set(sVar,getDW2(true)); else A.dtReg=getDW(null);
                else if (sBefehl.equals("init 1970"))
			A.dtReg=new DateWOD(Static.dt1970);
		else if (sBefehl.equals("init vonbis"))
                  A.setVonBis(bVar ? getVonVar(sVar):dtVonOld.toTimestamp(), bVar ? getBisVar(sVar):dtBisOld.toTimestamp());
		else if (sBefehl.equals("delete vonbis"))
			A.setVonBis(null,null);
		else if (sBefehl.equals("init real aic"))
			A.iQryReg=A.getStamm();
		else if (sBefehl.equals("init std aic"))
			A.iReg=A.getStamm();
		else if (sBefehl.equals("move next real aic"))
			A.iPos++;
		else if (sBefehl.equals("move prev real aic"))
			A.iPos--;
		else if (sBefehl.equals("init act_date"))
		{
			DateWOD dw=getAktDate();
			if (bVar) set(sVar,dw); else A.dtReg=new DateWOD(dw);
		}
		else if (sBefehl.equals("init TRUE"))
			if (bVar) set(sVar,true); else A.bReg=true;
		else if (sBefehl.equals("init FALSE"))
			if (bVar) set(sVar,false); else A.bReg=false;
		else if (sBefehl.equals("init String"))
			if (bVar) set(sVar,getM()); else A.sReg=sEingabe != null ? sEingabe:"";
                else if (sBefehl.equals("init comma"))
                  A.sTrenn=",";
                else if (sBefehl.equals("init semicolon"))
                  A.sTrenn=";";
                else if (sBefehl.equals("init separator"))
                  A.sTrenn=sEingabe != null ? sEingabe.equals("TAB") ? ""+(char)9:sEingabe:null;
                else if (sBefehl.equals("init Tab"))
                  A.sTrenn="Tab";
                else if (sBefehl.equals("init Stichtag"))
                  A.dtStichtag=null;
                else if (sBefehl.equals("init Sync-Stamm"))
                {
                  int iStt=EtoStt(sEingabe);
                  A.g.setSyncStamm(bSpalte ? A.TabAbfragen.getI("Stt"):iStt>0 ? iStt:A.iStt,0,0,A.iVB);
                }
                else if (sBefehl.equals("init Sync-Vec"))
                {
                  int iStt=EtoStt(sEingabe);
                  A.g.setSyncStamm(bSpalte ? A.TabAbfragen.getI("Stt"):iStt>0 ? iStt:A.iStt, null,A.iVB);
                }
                else if (sBefehl.equals("init 86400"))
                  A.dReg=86400;
                else if (sBefehl.equals("init 365")) // init boolean
                  //A.dReg=365;
                	if (bVar) set(sVar,bSpalte ? TabAbfrage.getB(sSpalte):A.bReg); else A.bReg=getB();
                else if (sBefehl.equals("init 365.25")) // rechne boolean
                	set(sVar,ZerlegeBool(sEingabe));
                  //A.dReg=365.25;
                else if (sBefehl.equals("init 12"))
                	A.printError("<init 12> nur noch mit <init number>",400);
//                  A.dReg=12;
		else if (sBefehl.equals("set vonbis"))
		{
                  if (bVar)
                    setVB(sVar,bSpalte ? TabAbfrage.getTimestamp(sSpalte):A.getVon(),bSpalte ? TabAbfrage.getTimestamp(A.TabSpalten.getS("Kennung2")):A.getBis());
                  else
                  {
                    dtVonVor2 = A.getVon();
                    dtBisVor2 = A.getBis();
                  }
		}
                else if (sBefehl.equals("init Mandant"))
                {
                  A.iMReg = A.g.getMandant();
                  A.g.testInfo("init Mandant->"+A.g.MandantBez(A.iMReg));
                }
                else if (sBefehl.equals("Befehl156")) // init MandantVor
                {
                	A.g.setMandant(A.iMandantVor,false);
                    A.iMReg=A.g.getMandant();
                    A.debugInfo("init Mandant->"+A.g.MandantBez(A.iMReg));
                }
                else if (sBefehl.equals("get Timer-Mandanten"))
                {
                  VecM=SQL.getVector("select aic_mandant from mandant where versuche&16>0",A.g);
                  iPosM=-1;
                }
                else if (sBefehl.equals("pop all"))
                  TabAbfrage.popAll();
                else if (sBefehl.equals("init Stt")) // reserve45
                  A.iStt=0;
                else
                  RunConvert();
                  //A.printError("Calc.RunInit(): Der Init-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
	} // RunInit
	
	private DateWOD getAktDate()
	{
		boolean bZone=false;
		DateWOD dt=null;
		if (sEingabe!=null)
		{
			if (sEingabe.equals("Z"))
				bZone=true;
			else if (sEingabe.equals("L"))
				dt=new DateWOD();
			else
				A.printError(MPos()+" akt.Date mit E="+sEingabe+" nicht erlaubt", 400);
		}
		if (dt==null)
			dt=new DateWOD(SQL.getNow(A.g));
		if (bZone)
		{
			int iZD=A.iZone-SQL.getZone(A.g);
			if (iZD!=0)
				dt.add(iZD*60000);
		}
		return dt;
	}
	
	private int getSZB(String s)
    {
    	if (s.indexOf("&")>0)
    		return s.indexOf("&");
    	else if (s.indexOf("|")>0)
    		return s.indexOf("|");
    	else 
    		return -1;
    }
       
    private boolean ZerlegeBool(String s)
    {
    	//A.g.fixtestError("Zerlege:"+s);
    	int iSZ=getSZB(s);
    	if (iSZ<0)
    	{
    		//A.printError("Zerlegen von "+s+" nicht möglich");
    		s=s.trim();
    		boolean bNot=s.startsWith("!");
    		if (bNot)
    			s=s.substring(1).trim();
    		if (s.startsWith("$"))
    			return bNot ? !getb(s.substring(1)):getb(s.substring(1));
    		else
    		{
    			A.printError("ZerlegeBool:"+s+" nicht erkannt, Variable benötigt $",400);
    			return false;
    		}
    	}
    	else
    	{
	    	String sV1=s.substring(0, iSZ).trim();
	    	boolean bNot=sV1.startsWith("!");
    		if (bNot)
    			sV1=sV1.substring(1).trim();
    		boolean b=false;
    		if (sV1.startsWith("$"))
    			b= bNot ? !getb(sV1.substring(1)):getb(sV1.substring(1));
    		else
    			A.printError("ZerlegeBool:"+sV1+" nicht erkannt, Variable benötigt $",400);
//	    	boolean b=sV1.startsWith("!") ? !getb(sV1.substring(1)):getb(sV1);
	    	char c=s.charAt(iSZ);
	    	String sV2=s.substring(iSZ+1).trim();
	    	boolean b2=ZerlegeBool(sV2);//isNumeric(sV2) ? Double.parseDouble(sV2):getd(sV2);
//	    	A.g.fixtestError("Pos="+iSZ+" "+c+", V1="+sV1+", V2="+sV2+" -> "+b+" "+c+" "+b2);
	    	if (c=='&')
	    		return b && b2;
	    	else if (c=='|')
	    		return b || b2;
	    	else
	    		return false;
    	}
    }
    
    private boolean ZerlegeModul(String sBG,String s)
    {
    	int iSZ=getSZB(s);
    	if (iSZ<0)
    	{
    		s=s.trim();
    		int iPos=A.g.getPosBegriff(sBG,s);  		
    		boolean b= iPos<0 ? false:A.g.BerechtigungS(iPos);
//    		A.g.fixtestError("Zerlege "+sBG+" "+s+" -> "+iPos+", "+b);
    		return b;
    	}
    	else
    	{
    		String sV1=s.substring(0, iSZ).trim();
    		boolean b=ZerlegeModul(sBG,sV1);
    		char c=s.charAt(iSZ);
	    	String sV2=s.substring(iSZ+1).trim();
	    	boolean b2=ZerlegeModul(sBG,sV2);
	    	if (c=='&')
	    		return b && b2;
	    	else if (c=='|')
	    		return b || b2;
	    	else
	    		return false;
    	}
    }

	@SuppressWarnings("unchecked")
	private void RunConvert()
	{
          //A.g.progInfo("RunConvert");
		if (sBefehl.equals("date to von"))
                {
                  DateWOD DW=getDW2(false);
                  if (bVar && !bAR)
                    setVB(sVar,DW==null?null:DW.toTimestamp(),true);
                  else
                    A.setVon(DW==null?null:DW.toTimestamp());
                }
		else if (sBefehl.equals("date to bis"))
                {
                  DateWOD DW=getDW2(false);
                  if (bVar && !bAR)
                    setVB(sVar,DW==null?null:DW.toTimestamp(),false);
                  else
                    A.setBis(DW==null?null:DW.toTimestamp());//A.dtReg == null ? null : A.dtReg.toTimestamp());
                }
		else if (sBefehl.equals("Befehl186")) // get Dauer2
		{
			if (bSpalte && bVar)
				A.dReg=getVBVar(sVar).getDauer(getDWS(sSpalte), getDWS(A.TabSpalten.getS("Kennung2")));
		}
		else if (sBefehl.equals("date to Stichtag"))
		{
			DateWOD DW=bVar ? getdt(sVar):bSpalte ? getDW():A.dtReg;
			A.g.dtStichtag= DW==null ? null:DW.toDate();
		}
		else if (sBefehl.equals("von to date"))
			A.dtReg=A.getVon()==null?null:new DateWOD(A.getVon());
		else if (sBefehl.equals("bis to date"))
			A.dtReg=A.getBis()==null?null:new DateWOD(A.getBis());
		else if (sBefehl.equals("Stichtag to date"))
			A.dtReg=A.g.dtStichtag==null?null:new DateWOD(A.g.dtStichtag);
		else if (sBefehl.equals("copy to QryAic"))
			A.iQryReg=A.iReg;
		else if (sBefehl.equals("copy to Aic"))
			A.iReg=A.iQryReg;
		else if (sBefehl.equals("model to Aic"))
			A.iReg=A.iModellBegriff;
		else if (sBefehl.equals("Aic to model"))
			A.iModellBegriff=A.iReg;
		else if (sBefehl.equals("change Aic"))
		{
			int i=A.iReg;
			A.iReg=A.iQryReg;
			A.iQryReg=i;
		}
		else if (sBefehl.equals("swap number"))
		{
			double d=A.dReg;
			A.dReg=A.dReg2;
			A.dReg2=d;
		}
		else if (sBefehl.equals("swap date"))
		{
			DateWOD dt=A.dtReg;
			A.dtReg=A.dtReg2;
			A.dtReg2=dt;
		}
                else if (sBefehl.equals("copy date"))
                  A.dtReg2=A.dtReg;
                else if (sBefehl.equals("swap String"))
		{
			String s=A.sReg;
			A.sReg=A.sReg2;
			A.sReg2=s;
		}
		else if (sBefehl.equals("swap Aic"))
		{
			int i=A.iReg;
			A.iReg=A.iReg2;
			A.iReg2=i;
		}
                else if (sBefehl.equals("swap Bew"))
                {
                        int i=A.iBewReg;
                        A.iBewReg=A.iBewReg2;
                        A.iBewReg2=i;
                }
		else if (sBefehl.equals("swap QryAic"))
		{
			int i=A.iQryReg;
			A.iQryReg=A.iQryReg2;
			A.iQryReg2=i;
		}
		else if (sBefehl.equals("HoursToSeconds"))
			A.dReg*=3600.0;
		else if (sBefehl.equals("SecondsToHours"))
			A.dReg/=3600.0;
		else if (sBefehl.equals("number to boolean"))
			A.bReg=A.dReg != 0.0;
		else if (sBefehl.equals("number to date"))
		{
			A.dtReg=new DateWOD(Math.round(A.dReg*1000.0));
			A.dtReg.setTimezoneOffset();
		}
		else if (sBefehl.equals("date to number"))
			A.dReg=A.dtReg==null ? 0:A.dtReg.getAbsSeconds();
		else if (sBefehl.equals("VecStamm to VecAic"))
		{
			A.VecAic=VecToStack(A.VecStamm);
//					new java.util.Stack<Integer>();
//			for (int i=0;i<A.VecStamm.size();i++)
//				A.VecAic.push(Sort.getInt(A.VecStamm,i));
			A.debugInfo("VecAic nun: "+A.VecAic);
		}
		else if (sBefehl.equals("VecAic to VecStamm"))
			A.VecStamm=Sort.getIntVec(A.VecAic);
		else
                  RunDatenHolen();
			//A.printError("Calc.RunConvert(): Der Convert-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
	}
	
	private int getAic(String s)
	{
		if (s==null || s.length()==0)
			return 0;
		char c=s.charAt(0);
		if (Character.isDigit(c))
			return Sort.geti(s);
		else if (c=='L')
			return A.g.getLog();
		else if (c=='P')
			return A.getProt();
		else if (c=='B')
			return A.g.getBenutzer();
		else if (c=='C')
			return A.g.getComputer();
		else
		{
			A.printError("getAic von "+s+" nicht erlaubt", 400);
			return -1;
		}
	}

        private int getAic()
        {
          if (A.TabSpalten.isNull("Stt") || !A.TabSpalten.getS("Datentyp").equals("BewHierarchie"))
            return TabAbfrage.getI(sSpalte);
          else
          {
            return TabAbfrage.getI("W"+sSpalte.substring(1))==A.TabSpalten.getI("Stt") ? TabAbfrage.getI(sSpalte):0;
            //A.g.progInfo("getAic: Stt="+A.TabSpalten.getInhalt("Stt")+"/"+Static.className(A.TabSpalten.getInhalt("Stt")));
            /*int iSoll=A.TabSpalten.getI("Stt");
            String s3=A.TabSpalten.getS("Kennung").substring(1);
            int iIst=TabAbfrage.getI("w"+s3);
            int iPos=s3.lastIndexOf("_");
            A.g.progInfo(s3+"/"+iSoll+"/"+iIst+"/"+iPos);
            while(iSoll !=iIst && iPos>0)
            {
              s3=s3.substring(0,iPos);
              iIst=TabAbfrage.getI("w"+s3);
              iPos=s3.lastIndexOf("_");
              A.g.progInfo(s3+"/"+iSoll+"/"+iIst+"/"+iPos);
            }
            return iIst==iSoll ? TabAbfrage.getI("v"+s3):0;*/
          }
        }

        /*private String getString()
        {
          return bSpalte ? TabAbfrage.getM(A.TabSpalten.getS("Kennung3")):sEingabe;
        }*/

        private void setDest(double d)
        {
          if (A.sDest != null)
            set(A.sDest,d);
          else
            A.dReg=d;
        }

         private void setDest(DateWOD DW,boolean bVon)
         {
           if (A.sDest != null)
            setVB(A.sDest,DW==null?null:DW.toTimestamp(),bVon);
          else
            A.dtReg=DW;
         }

    private double getSum()
    {
    	return TabAbfrage.sum(A.TabSpalten.getS("Datentyp").equals("BewVon_Bis") && !A.TabSpalten.getB("Per") ? A.TabSpalten.getS("Kennung3"):sSpalte);
    }
    
    public Date JSONtoDate(String s)
	{
		if (s==null || s.equals(""))
			return null;
		s=s.replaceAll("T", " ");//.substring(0, 19);	
		if (s.length()==25)
		{
			String sZone=s.substring(19);
			A.iZone=(s.charAt(19)=='+' ? 1:-1)*(Sort.geti(s.substring(20, 22))*60+Sort.geti(s.substring(23)));
//			A.g.fixtestError("Zone="+A.iZone+" bei "+sZone);
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s.substring(0, 19),new ParsePosition(0));
	}
    
    private void VarORdReg(double d)
    {
    	boolean bAdd=iM_Art==ADD;
    	boolean bSub=iM_Art==SUB;
    	if (bVar)
    		set(sVar,bAdd ? getd(sVar)+d:bSub ? getd(sVar)-d:d);
    	else
 			A.dReg=bAdd ? d+A.dReg:bSub ? A.dReg-d:d;
    }

	@SuppressWarnings("unchecked")
	private void RunDatenHolen()
	{
          //A.g.progInfo("RunDatenHolen");
		/*if (!bSpalte && sEingabe==null && !sBefehl.equals("push String") && !sBefehl.equals("pop String") && !sBefehl.equals("push aic") && !sBefehl.equals("push Stamm") && !sBefehl.equals("pop aic") && !sBefehl.equals("pos absent -1")
			&& !sBefehl.equals("get String-Date") && !sBefehl.equals("add Date-String") && !sBefehl.equals("add dReg-String") && !sBefehl.equals("get real Stt")
                        && !sBefehl.equals("get Sync-Stamm") && !sBefehl.equals("get Sync-Vecx") && !sBefehl.equals("get duration") && !sBefehl.equals("get model"))
			A.printError("Calc.RunDatenHolen(): Der Befehl >"+sBefehl+"< (Zeile "+Static.FillNull(""+(TabBefehle.getI("aic_befehl")-iErsterBefehl+1),3)+") ohne Spalte oder Variable ist nicht möglich!");
		else*/ if (sBefehl.equals("get number"))
                  /*if (bVar) set(sEingabe,getF2()); else*/ setDest(bVar ? getd(sVar):bSpalte ? bSum ? getSum():TabAbfrage.getF(sSpalte):A.dReg2);
			//if (sEingabe != null) set(sEingabe,bSpalte ? TabAbfrage.getF(sSpalte):A.dReg); else A.dReg=TabAbfrage.getF(sSpalte);
		else if (sBefehl.equals("get Bool3"))
//			if (bVar && bSpalte)
//				set(sVar,A.g.getAuswahlAic(A.g.getAuswahlPos(sEingabe==null ? ""+A.iB3Reg:sEingabe, A.TabSpalten.getI("Eig"))));
//			else
				A.iB3Reg=A.g.TabAuswahl.getI_AIC("Nr",TabAbfrage.getI(sSpalte));
			else if (sBefehl.equals("sum"))
				VarORdReg(getSum());
            else if (sBefehl.equals("sum group"))
            	VarORdReg(TabAbfrage.sumGroup(A.TabSpalten.getS("Datentyp").equals("BewVon_Bis") && !A.TabSpalten.getB("Per") ? A.TabSpalten.getS("Kennung3"):sSpalte,bAlle));
            else if (sBefehl.equals("add sum group"))
            	A.dReg+=TabAbfrage.sumGroup(A.TabSpalten.getS("Datentyp").equals("BewVon_Bis") && !A.TabSpalten.getB("Per") ? A.TabSpalten.getS("Kennung3"):sSpalte,bAlle);
            else if (sBefehl.equals("sum VecAic"))
            	VarORdReg(TabAbfrage.sumVec(A.TabSpalten.getS("Datentyp").equals("BewVon_Bis") && !A.TabSpalten.getB("Per") ? A.TabSpalten.getS("Kennung3"):sSpalte,A.VecAic));
		else if (sBefehl.equals("avg"))
			A.dReg=TabAbfrage.avg(sSpalte);
		else if (sBefehl.equals("min"))
			A.dReg=TabAbfrage.min(sSpalte);
		else if (sBefehl.equals("max"))
			A.dReg=TabAbfrage.max(sSpalte);
		else if (sBefehl.equals("Befehl142"))
		{
			A.dtReg=new DateWOD(Math.round(TabAbfrage.max(sSpalte,bGroup)*1000));
			A.dtReg.setTimezoneOffset();
		}
		else if (sBefehl.equals("count distinct"))
			VarORdReg(bGroup ? TabAbfrage.countGroup(sSpalte):TabAbfrage.count_distinct(sSpalte));
		else if (sBefehl.equals("count"))
			VarORdReg(!bSpalte ? A.VecStamm.size():bGroup ? TabAbfrage.sGruppe.equals("Gruppe") ? TabAbfrage.count(sSpalte,TabAbfrage.getInhalt(sSpalte)):
                            TabAbfrage.countGroup(null):TabAbfrage.getAnzahlElemente(sSpalte));
		else if (sBefehl.equals("get aic"))
			/*if (sEingabe != null && bSpalte) set(sEingabe,getAic(),getDatenStt()); else*/
                        if (bVar && bSpalte) set(sVar,getAic(),getDatenStt()); else A.iReg=bVar ? geti(sVar) : bSpalte ? getAic():sEingabe!=null ? getAic(sEingabe):A.iReg2;
        else if (sBefehl.equals("get Mandant"))
        {
          int iM=TabAbfrage.getI(sSpalte);
          if (A.g.TabMandanten==null)
            A.g.LoadMandant();
          if (iM>0 && A.g.TabMandanten.getPos("aic_mandant",iM)>=0)
          {
            A.iMReg = iM;
            A.g.testInfo("get Mandant->" + A.g.MandantBez(A.iMReg));
          }
          else
            A.g.fixtestInfo("get Mandant: Mandant "+iM+" nicht gefunden");
        }
        else if (sBefehl.equals("get Bew"))
			A.iBewReg=TabAbfrage.getI(sSpalte);
		else if (sBefehl.equals("get Stt"))
			A.iStt=getDatenStt();
			//A.iStt=TabAbfrage.getI(A.TabSpalten.getS("Kennung2"));
		else if (sBefehl.equals("get QryAic"))
			if (bVar && bSpalte) set(sVar,getAic(),getDatenStt()); else A.iQryReg=bVar ? geti(sVar):getAic();
		else if (sBefehl.equals("get real aic"))
			if (bVar && bSpalte) set(sVar,TabAbfrage.getI("aic_stamm"),A.TabAbfragen.getI("Stt")); else A.iReg=bVar ? geti(sVar):TabAbfrage.getI("aic_stamm");
        else if (sBefehl.equals("get real Bew"))
        	if (bVar && bSpalte) set(sVar,TabAbfrage.getI("aic_bew_pool")); else A.iBewReg=TabAbfrage.getI("aic_bew_pool");
		else if (sBefehl.equals("get real Stt"))
			A.iStt=bSpalte ? A.TabAbfragen.getI("Stt")==0 ? TabAbfrage.getI("aic_stammtyp"):A.TabAbfragen.getI("Stt"):SQL.getInteger(A.g,"select aic_stammtyp from stamm where aic_stamm=?",0,""+A.iReg);
		else if (sBefehl.equals("get real QryAic"))
			if (bVar && bSpalte) set(sVar,TabAbfrage.getI("aic_stamm"),A.TabAbfragen.getI("Stt")); else A.iQryReg=bVar ? geti(sVar):TabAbfrage.getI(iBew==0 ? "aic_stamm":"aic_bew_pool");
		else if (sBefehl.equals("get boolean"))
			A.bReg=bVar ? getb(sVar):TabAbfrage.getB(sSpalte);
		else if (sBefehl.equals("get date"))
                {
                  //A.g.testInfo(sBefehl+":"+sSpalte+","+A.TabAbfragen.getS("Bezeichnung")+"/"+A.TabSpalten.getS("Anzeige")+"/"+Static.print(TabAbfrage.getInhalt(sSpalte)));
			A.dtReg=bSpalte ? getDW(): bVar ? getdt(sVar):A.dtReg2;
                        if (A.dtReg != null && !bSpalte)
                          A.dtReg=new DateWOD(A.dtReg);//.getTimeInMilli());
                  if (A.dtReg!=null)
                  {
                	  if (sEingabe!=null && sEingabe.equals("Z"))
                	  {
                		  int iZG=A.g.getZone();
                		  int iZD=iZG-A.dtReg.getZone2();
                		  if (iZD!=0)
                			  A.dtReg.add(iZD*60000);
                		  A.dtReg.setZone(iZG);
                	  }
//                    A.g.fixtestError("get date -> "+A.dtReg);//+", Zone="+A.dtReg.getZone2());
                  }
                }
		else if (sBefehl.equals("get_Daten"))
			A.iDaten=TabAbfrage.getI("D"+sSpalte.substring(1));
		else if (sBefehl.equals("push"))
			TabAbfrage.push();
		else if (sBefehl.equals("pop"))
			TabAbfrage.pop();
		else if (sBefehl.equals("pos -1"))
			TabAbfrage.moveBefore();
                else if (sBefehl.equals("pos absent -1"))
			A.g.TabAustritt.moveBefore();
		else if (sBefehl.equals("pos last"))
			TabAbfrage.posLast(sSpalte);
		else if (sBefehl.equals("move first"))
			TabAbfrage.moveFirst();
		else if (sBefehl.equals("move last"))
			TabAbfrage.moveLast();
		else if (sBefehl.equals("move next"))
			TabAbfrage.moveNext();
		else if (sBefehl.equals("move previous"))
			TabAbfrage.movePrevious();
		else if (sBefehl.equals("pos aic"))
		{
		  int iAic=bVar ? geti(sVar):A.iReg;
          if (bGroup)
          {
            if (!TabAbfrage.posGroup(sSpalte, new Integer(iAic), new Long(iAic), null, false))
              TabAbfrage.moveBefore();
          }
          else if (iAic==0)
            TabAbfrage.posInhalt(sSpalte,null);
          else
            TabAbfrage.posInhalt(sSpalte,iAic);
		}
        else if (sBefehl.equals("pos next aic"))
        {
          int iAic=bVar ? geti(sVar):A.iReg;
          if (bGroup)
          {
            if (!TabAbfrage.posGroup(sSpalte,new Integer(iAic),new Long(iAic),null,true))
              TabAbfrage.moveBefore();
          }
          else
            TabAbfrage.posNextInhalt(sSpalte, new Integer(iAic), new Long(iAic));
        }
		else if (sBefehl.equals("pos real aic"))
		{
		  if (iBew>0)
		    TabAbfrage.posInhalt("aic_Bew_Pool",A.iBewReg);
		  else
		    TabAbfrage.posInhalt("aic_stamm",bVar ? geti(sVar):A.iReg);
		}
		else if (sBefehl.equals("pos min"))
			TabAbfrage.posmin(sSpalte);
		else if (sBefehl.equals("pos max"))
			TabAbfrage.posmax(sSpalte);
		else if (sBefehl.equals("get from"))
			setDest(bVar ? new DateWOD(getVonVar(sVar)):sEingabe!=null?getDW(null):TabAbfrage.getInhalt(sSpalte)==null?null:getDWS(sSpalte),true);
		else if (sBefehl.equals("get duration"))
			A.dReg=bSpalte ? TabAbfrage.getF(A.TabSpalten.getS("Kennung3")):Sort.getf(bVar ? getBisVar(sVar):A.getBis())-Sort.getf(bVar ? getVonVar(sVar):A.getVon());
		else if (sBefehl.equals("get to"))
			setDest(bVar ? new DateWOD(getBisVar(sVar)):sEingabe!=null?getDW(null):TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung2"))==null?null:getDWS(A.TabSpalten.getS("Kennung2")),false);
		else if (sBefehl.equals("get model"))
			A.iModellBegriff=bSpalte ? TabAbfrage.getI(sSpalte):A.TabModelle.getI("Aic_Begriff");
		else if (sBefehl.equals("get String"))
			/*if (bVar) set(sEingabe,getMo()); else*/ CCo(getMo(2));//bVar ? gets(sVar):bSpalte ? TabAbfrage.getM(A.TabSpalten.getS("Kennung3")):A.sReg2;
        else if (sBefehl.equals("add path")) // war reserve109
        {
          String sDir=sEingabe==null || sEingabe.equals("Bild") ? Static.DirImageStamm : sEingabe.equals("Doku") ? Static.DirDoku:null;
          if (A.sReg.indexOf(File.separator) < 0 && A.sReg.indexOf(":") < 0 && sDir != null)
                A.sReg = sDir + A.sReg;
        }
        else if (sBefehl.equals("get title"))
        	CCo(A.TabSpalten.getS("Bezeichnung"));
//			A.sReg=A.TabSpalten.getS("Bezeichnung");
		else if (sBefehl.equals("push String"))
			A.StaString.push(getM3());
		else if (sBefehl.equals("pop String"))
		{
			if (A.StaString.empty())
			{
				A.g.fixtestInfo("Calc.RunDatenHolen: pop String - kein weiterer String vorhanden");
				CCo("");
			}
			else
				CCo(A.StaString.pop());
		}
		else if (sBefehl.equals("push Stamm"))
			A.VecStamm.addElement(new Integer(bSpalte?TabAbfrage.getI(sSpalte):A.iReg));
		else if (sBefehl.equals("push aic"))
		  if (sEingabe != null && sEingabe.equals("-1"))
		    A.VecBewPool.addElement(new Integer(A.iBewReg));
		  else
		  {
			  if (bVar)
			  {
				  Stack Sta=getVec(sVar);
				  if (Sta==null)
				  {
					  Sta=new Stack<Integer>();
					  //setVec(sVar,Sta);
				  }
//				  A.g.fixtestError("Stack davor: "+Sta);
				  int iAic=bSpalte ? getAic():A.iReg;
				  Sta.push(iAic);
				  setVec(sVar,Sta);
//				  A.g.fixtestError("Stack danach: "+Sta+" nach dazu "+iAic);
			  }
			  else
			  {
				if (A.VecAic==null)
					A.VecAic=new Stack<Integer>();
				A.VecAic.push(bSpalte ? getAic():A.iReg);
			  }
		  }
		else if (sBefehl.equals("pop aic"))
		{
			if (!bVar && (A.VecAic==null || A.VecAic.isEmpty()) || bVar && (getVec(sVar)==null || getVec(sVar).isEmpty()))
			{
				A.g.fixtestInfo("Calc.RunDatenHolen: pop aic - kein weiterer Aic vorhanden");
				A.iReg=-1;
			}
			else
			{
				Stack Sta=bVar ? getVec(sVar):A.VecAic;
				A.iReg=Sort.geti(Sta.pop());
				if (!bVar && A.VecAic.isEmpty())
					A.VecAic=null;
				if (bVar)
					setVec(sVar,Sta);
			}
		}
        else if (sBefehl.equals("get String-Date2"))
                 A.dtReg=bSpalte && (TabAbfrage.getS(A.TabSpalten.getS("Kennung3")).equals("") || sEingabe==null && A.TabSpalten.getS("Format").equals("yyyyMMdd") && TabAbfrage.getS(A.TabSpalten.getS("Kennung3")).endsWith("00")) ? null:
                     new DateWOD(sEingabe != null && sEingabe.equals("JSON") ? JSONtoDate(getMo(1)):
                    		 new java.text.SimpleDateFormat(sEingabe==null ? A.TabSpalten.getS("Format"):sEingabe).parse(getMo(1) /*bSpalte ? TabAbfrage.getS(A.TabSpalten.getS("Kennung3")):A.sReg*/,new java.text.ParsePosition(0)));
        else if (sBefehl.equals("get String-number"))
        {
          A.dReg = bSpalte && TabAbfrage.getS(A.TabSpalten.getS("Kennung3")).equals("") ? 0.0:
          new java.text.DecimalFormat(sEingabe==null ? bSpalte ? A.TabSpalten.getS("Format"):"0":sEingabe).parse(bSpalte ? TabAbfrage.getS(A.TabSpalten.getS("Kennung3")):A.sReg,new java.text.ParsePosition(0)).doubleValue();
        }
		else if (sBefehl.equals("get String-Date"))
			A.dtReg=new DateWOD(new java.text.SimpleDateFormat(A.bReg && bSpalte?A.sReg:"yyyyMMddHHmmss").parse(bSpalte?TabAbfrage.getS(A.TabSpalten.getS("Kennung3")):A.sReg,new java.text.ParsePosition(0)));
		else if (sBefehl.equals("get von-bis-String"))
			CCo(new All_Unlimited.Allgemein.Anzeige.VonBis(A.g,TabAbfrage.getDate(sSpalte),TabAbfrage.getDate(A.TabSpalten.getS("Kennung2")),A.TabSpalten.getS("Format")).toString());
		else if (sBefehl.equals("get Date-String"))
			CCo(new All_Unlimited.Allgemein.Anzeige.Zeit(A.g,TabAbfrage.getDate(sSpalte),sEingabe==null ? A.TabSpalten.getS("Format"):sEingabe).toString());
		else if (sBefehl.equals("get unit"))
			A.iMass=bSpalte ? TabAbfrage.isEmpty() || TabAbfrage.isNull(A.TabSpalten.getS("Kennung2")) ?  A.TabSpalten.getI("Mass"):TabAbfrage.getI(A.TabSpalten.getS("Kennung2")):0;
		else if (sBefehl.equals("get 0-String"))
			if (bAS)
				SoR(Static.FillNull(TabAbfrage.getS(A.TabSpalten.getS("Kennung3")),getI()));
			else	
				A.sReg=Static.FillNull(bSpalte?TabAbfrage.getS(A.TabSpalten.getS("Kennung3")):A.sReg,sEingabe!=null || !bSpalte? getI():A.TabSpalten.getI("Laenge"));
		else if (sBefehl.equals("get Sync-Stamm"))
		{
		  int iPos=sEingabe!= null ? A.g.TabStammtypen.getPos("Kennung",sEingabe.toUpperCase()):A.g.TabStammtypen.getPos("Aic",bSpalte ? getDatenStt():A.iStt);
		  if (iPos<0)
        	  A.printError(MPos()+"get Sync-Stamm: Stt nicht ermittelbar bei E="+sEingabe,400);			  
		  A.iReg=iPos>=0 ? A.g.getJokerStt(A.g.TabStammtypen.getI(iPos,"Aic"),A.iVB):0; // A.g.TabStammtypen.getI(iPos,"Sync"):0;
		}
        else if (sBefehl.equals("get Sync-Vec"))
        {
          int iPos=sEingabe!= null ? A.g.TabStammtypen.getPos("Kennung",sEingabe.toUpperCase()):A.g.TabStammtypen.getPos("Aic",A.iStt);
          if (iPos<0)
        	  A.printError(MPos()+"get Sync-Vec: Stt nicht ermittelbar bei E="+sEingabe,400);
          Vector<Integer> Vec=iPos>=0 ? A.g.getJokerVec(A.g.TabStammtypen.getI(iPos,"Aic"),A.iVB):null; //(Vector<Integer>)A.g.TabStammtypen.getInhalt("Vec",iPos):null;
          A.VecAic=new Stack<Integer>();
          if (Vec!=null)
        	  for (int i=0;i<Vec.size();i++)
                  A.VecAic.push(Sort.getInt(Vec,i));
          //A.VecAic=iPos>=0 ? (Stack)A.g.TabStammtypen.getInhalt("Vec",iPos):null;
        }
		else if (sBefehl.equals("add Date-String"))
			addString2(new Zeit(A.g,bVar && (bAS || bAR)  ? getdt(sVar).toDate():bSpalte ? TabAbfrage.getDate(sSpalte): A.dtReg.toDate(),sEingabe!= null ? sEingabe:bSpalte?A.TabSpalten.getS("Format"):null).toString());
		else if (sBefehl.equals("add number-String"))
			addString2(bVar && (bAS || bAR) || !bVar && !bSpalte ? ""+new Zahl1(bVar ? getd(sVar):A.dReg,sEingabe) : A.g.bH_min && A.TabSpalten.getI("Mass")==A.g.iAicStunde ? Static.hm(TabAbfrage.getF(sSpalte)*3600):
                            new Zahl1(TabAbfrage.getF(sSpalte),sEingabe!=null ? sEingabe:A.TabSpalten.getS("Format")).toString());
		else if (sBefehl.equals("Befehl164"))
			CCo(Static.hm(A.dReg));
		else if (sBefehl.equals("Befehl151")) // concat Reg
			addString2(new Zahl1(sEingabe.startsWith("M") ? A.g.getSIMandant():sEingabe.startsWith("m") ? A.g.getMandant():A.iQryReg,sEingabe.substring(1)).toString());
		else if (sBefehl.equals("add space-number"))
			addString2(Static.FillSpace("Double", new Zahl1(TabAbfrage.getF(sSpalte),A.TabSpalten.getS("Format")).getS(), getI()));
		else if (sBefehl.equals("add Begriff-Title"))
			addString2(A.g.getBegBez3(A.g.getPosBegriff("Titel",sEingabe)));
		else if (sBefehl.equals("use Sync"))
			addString2(""+ShowAbfrage.doubleToScreen(A.g,bAR ? A.dReg:TabAbfrage.getF(sSpalte),A.TabSpalten,TabAbfrage.getI(A.TabSpalten.getS("Kennung2"))));
		else if (sBefehl.equals("add dReg-String"))
                {
                  if (sEingabe != null && sEingabe.startsWith("!"))
                    addString2(new java.text.DecimalFormat(sEingabe.substring(1)).format(A.dReg).replace(',', '.'));
                  else
                    addString2(new java.text.DecimalFormat(sEingabe != null ? sEingabe : A.TabSpalten.getS("Format")).format(A.dReg));
                  //A.sReg+=new All_Unlimited.Allgemein.Anzeige.Zahl1(A.dReg,A.TabSpalten.getS("Format")).toString();
                }
                else if (sBefehl.equals("get Stichtag"))
                  A.dtReg=new DateWOD(SQL.getTimestamp(A.g,"select gultig_von from poolview where aic_stamm="+TabAbfrage.getI("aic_stamm")+" and aic_eigenschaft="+A.TabSpalten.getI("Eig")));
		else if (sBefehl.equals("get conclusion"))
                  A.dtReg=new DateWOD(A.g.getAbschlussDate(0,iBew,A.iQryReg));
        else if (sBefehl.equals("get general finish"))
          A.dtReg=new DateWOD(A.g.getAbschluss(iBew,A.iQryReg,1));
        else if (sBefehl.equals("get superior finish"))
          A.dtReg=new DateWOD(A.g.getAbschluss(iBew,A.iQryReg,2));
        else if (sBefehl.equals("get personal finish"))
          A.dtReg=new DateWOD(A.g.getAbschluss(iBew,A.iQryReg,0));
        else if (sBefehl.equals("Befehl144")) // get Finish-Persons
          A.VecStamm=A.g.getAbschlussVecStamm(sEingabe,A.iQryReg);//Personen der Abschluss-Abfrage ermitteln

        else if (sBefehl.equals("get dependent memo"))
        {
          int iSprache=SQL.getInteger(A.g,"select max(aic_sprache) from benutzer where aic_stamm=" + A.iQryReg);
          if (iSprache<1)
            iSprache=SQL.getInteger(A.g,"select aic_sprache from sprache where standard=1");
          CCo(Static.beiLeer(SQL.getString(A.g, "select memo from daten_memo where aic_tabellenname=" + A.g.iTabStamm + " and aic_fremd=" + TabAbfrage.getI(sSpalte)
              + " and aic_sprache="+iSprache), A.sReg));
        }
        else if (sBefehl.equals("get_GPS"))
          A.gps=bVar ? getGPSVar(sVar):new GPS(TabAbfrage,sSpalte);
        else
          RunMathematik();
                //A.printError("Calc.RunDatenHolen(): Das DatenHolen >"+sBefehl+"< steht noch nicht zur Verfügung");
	}
	
	private void CCo(String s)
	{
		if (iC_Art==0 && !Calc.Bconcat2.contains(sBefehl) || Static.Leer(A.sReg))
			A.sReg=s;
		else if (iC_Art==CONCAT || iC_Art==0 && Calc.Bconcat2.contains(sBefehl))
			A.sReg+=s;
		else if (iC_Art==CWS)
			A.sReg+=" "+s;
		else if (iC_Art==INIT)
			A.sReg=s;
	}
	
	private DateWOD getDWS(String sSpalte)
	{
		return getDWS(sSpalte,sEingabe != null && sEingabe.equals("bis"));
	}
	
	private DateWOD getDWS(String sSpalte,boolean bBis)
	{
		String sSp= bBis ? A.TabSpalten.getS("Kennung2"):sSpalte;
		Object Obj=TabAbfrage.getInhalt(sSp);
		if (Obj instanceof DateWOD)
			return new DateWOD((DateWOD)Obj);
		else
			return new DateWOD(TabAbfrage.getDate(sSp),A.g.hasZone(iBew) && TabAbfrage.exists("Zone") && !TabAbfrage.isNull("Zone") ? TabAbfrage.getI("Zone"):-1);
	}

	private int getDatenStt()
	{
          int i=0;
		if (A.TabSpalten.isNull("Kennung2"))
		{
			Abfrage Abf=(Abfrage)A.TabAbfragen.getInhalt("AF");
			Abf.TabSp.posInhalt("aic_spalte",A.TabSpalten.getInhalt("Aic"));
			i=((Integer)Abf.VecToStt((Vector)Abf.TabSp.getInhalt("Vec"))).intValue();
		}
		else
			i=TabAbfrage.getI(A.TabSpalten.getS("Kennung2"));
          A.debugInfo("getDatenStt -> "+i);
          return i;
	}

	private String getM()
        {
	  return sEingabe != null ? sEingabe:bSpalte ? TabAbfrage.getM(sSpalte) : A.sReg;
        }

        private String getM0()
        {
          return bVar ? gets(sVar):sEingabe != null ? sEingabe:bSpalte ? TabAbfrage.getM(sSpalte) : A.sReg;
        }

        private String getM1()
        {
          return sEingabe != null ? sEingabe:A.sReg;
        }

        private String getMo(int i)
        {
          return bVar ? gets(sVar):bSpalte ? TabAbfrage.getM(A.TabSpalten.getS("Kennung3")):i<2? A.sReg:A.sReg2;
        }


        /*private String getMo()
        {
          return bSpalte ? TabAbfrage.getM(sSpalte) : A.sReg;
        }*/

        private String getMemo()
        {
          String s=null;
          if (bSpalte && A.TabSpalten.getS("Datentyp").equals("Text") && (A.TabSpalten.getI("bits")& Abfrage.cstBild)>0)
            s=SQL.getString(A.g,"select memo from daten_memo where aic_tabellenname="+A.g.iTabStamm+" and aic_fremd="+TabAbfrage.getI("aic_stamm")+" and aic_sprache="+A.g.getSprache());
          if (s==null || s.equals(""))
            s=getM();
          return s;
        }

	private String getM3()
        {
	  return bVar && bAR ? gets(sVar):sEingabe != null ? sEingabe:bSpalte ? TabAbfrage.getM(A.TabSpalten.getS("Kennung3")) : A.sReg;
        }

        private String getM3b()
        {
          //A.g.progInfo("getM3b:"+bVar+"/"+sEingabe+"/"+bSpalte+"/"+A.TabSpalten.getS("Kennung3")+"/"+TabAbfrage.getM(A.TabSpalten.getS("Kennung3"))+"/"+A.sReg2);
          return bVar ? gets(sVar):sEingabe != null ? sEingabe:bSpalte ? TabAbfrage.getM(A.TabSpalten.getS("Kennung3")) : A.sReg2;
        }

        private String getM3c()
        {
          //A.g.progInfo("getM3b:"+bVar+"/"+sEingabe+"/"+bSpalte+"/"+A.TabSpalten.getS("Kennung3")+"/"+TabAbfrage.getM(A.TabSpalten.getS("Kennung3"))+"/"+A.sReg2);
          return bVar && bAR ? gets(sVar):sEingabe != null ? sEingabe:bSpalte && !bAS ? TabAbfrage.getM(A.TabSpalten.getS("Kennung3")) : bVar || bAS ? A.sReg : A.sReg2;
        }

        private String getS()
        {
          return sEingabe==null ? A.sReg:sEingabe;
        }

        private String getSo()
        {
          return bVar ? gets(sVar) : bSpalte ? TabAbfrage.getM(A.TabSpalten.getS("Kennung3")):A.sReg;
        }

        private int getI()
        {
          return sEingabe==null ? (int)A.dReg:Sort.geti(sEingabe);
        }

        private int getIC(String sE)
        {
          if (sE==null || sE.trim().length()==0)
            return 0;
          if (sE.startsWith("C:"))
            return A.g.TabCodes.getAic(sE.substring(2));
          else
            return Sort.geti(sE);
        }

        private Vector<Integer> getVec()
        {
        	Vector<Integer> Vec=new Vector<Integer>();
        	if (sEingabe==null)
        	   Vec.add((int)A.dReg);
        	else
        		for (int i=0;i<sEingabe.length();i+=2)
        			Vec.add(sEingabe.charAt(i)-'0');
        	//A.g.fixtestError("getVec="+Vec);
        	return Vec;
        }

        private double getF()
        {
          return bVar ? getd(sVar) : sEingabe != null ? Sort.getf(sEingabe) : A.dReg;
        }

        private int getI0()
        {
          return bVar ? geti(sVar):sEingabe!=null ? Sort.geti(sEingabe):bSpalte && !bAS ? TabAbfrage.getI(sSpalte):(int)A.dReg;
        }

        private boolean getB()
        {
          return bVar ? getb(sVar):bSpalte ? TabAbfrage.getB(sSpalte):sEingabe==null ? A.bReg:Tabellenspeicher.getb(sEingabe);
        }

        private DateWOD getDW(DateWOD dw0)
        {
        	String s=sEingabe;
        	return s==null ? bVar && bAR ? getdt(sVar): dw0:getDWE(s);
        }
        
        private static DateWOD getDWE(String s)
        {
        	  return s.length()==4 ? new DateWOD(Sort.geti(s),1,1):s.length()<12 ?
              new DateWOD(Sort.geti(s.substring(0,4)),Sort.geti(s.substring(4,6)),Sort.geti(s.substring(6,8))):
            	  new DateWOD(Sort.geti(s.substring(0,4)),Sort.geti(s.substring(4,6)),Sort.geti(s.substring(6,8)),Sort.geti(s.substring(8,10)),Sort.geti(s.substring(10,12)),0);
        }

        private DateWOD getDW2(boolean bClone)
        {
          return bSpalte ? getDW():sEingabe==null && bClone && A.dtReg!=null ? new DateWOD(A.dtReg.getTimeInMilli()):getDW(A.dtReg);
        }

        private DateWOD getDW()
        {
          
          return TabAbfrage.getInhalt(sSpalte) == null ? null :getDWS(A.TabSpalten.isNull("Anzeige") ? sSpalte:(A.TabSpalten.getS("Anzeige").equals("timestamp") ? "T" : "A") + sSpalte.substring(1));
        }

        private Date getDo()
        {
          DateWOD DW= bVar ? getdt(sVar) : bSpalte ? getDW():A.dtReg;
          return DW==null || DW.isNull() ? null:DW.toDate();
        }
        
        private double getZo()
        {
          return bVar ? getd(sVar) : bSpalte ? TabAbfrage.getF(sSpalte):A.dReg;
        }
        
        private int getA()
        {
          return bVar ? geti(sVar) : bSpalte ? getAic():A.iReg;
        }
        
        /*private double getF()
        {
          return bSpalte ? TabAbfrage.getF(A.TabSpalten.getS("Datentyp").equals("BewVon_Bis") ? A.TabSpalten.getS("Kennung3"):sSpalte):
                            sEingabe==null ? A.dReg2:Sort.getf(sEingabe);
        }*/

        private double getF2()
        {
          return bSpalte ? TabAbfrage.getF(A.TabSpalten.getS("Datentyp").equals("BewVon_Bis") ? A.TabSpalten.getS("Kennung3"):sSpalte):
                            sEingabe==null ? A.dReg:Sort.getf(sEingabe);
        }

        private double getF3()
        {
          return bSpalte ? TabAbfrage.getF(sSpalte) : bVar ? getd(sVar) : sEingabe == null ? A.dReg2 : Sort.getf(sEingabe);
        }

        private double getF4()
        {
          return bSpalte && bVar ? getd(sVar):(bSpalte || bVar) && sEingabe != null ? Sort.getf(sEingabe):A.dReg;
          //return !bVar || !bSpalte ? A.dReg:getd(sVar);
        }

        /*private double getF5()
        {
          return bVar ? getd(sEingabe):getF2();
        }*/

        private double getFx1()
        {
          if (bAS && bSpalte)
            return bSum ? getSum():TabAbfrage.getF(sSpalte);
          else if (A.sDest!=null)
            return getd(A.sDest);
          else if (bSpalte && sEingabe != null)
            return Sort.getf(sEingabe);
          else
            return bVar ? getd(sVar):A.dReg;
        }

        private double getFx2()
        {
          if (bSpalte && !bAS)
            return bSum ? getSum():TabAbfrage.getF(A.TabSpalten.getS("Datentyp").equals("BewVon_Bis") ? A.TabSpalten.getS("Kennung3"):sSpalte);
          else
            return (A.sDest!=null || bAS && bSpalte) && bVar ? getd(sVar):sEingabe != null ? Sort.getf(sEingabe):bVar || bAS || A.sDest!=null ? A.dReg:A.dReg2;
        }

        private double getFx3()
        {
          return bVar ? getd(sVar):sEingabe != null ? Sort.getf(sEingabe):bSpalte ? TabAbfrage.getF(sSpalte):A.dReg;
        }
        
    private int getSZ(String s)
    {
    	if (s.indexOf("+")>0)
    		return s.indexOf("+");
    	else if (s.indexOf("-")>0)
    		return s.indexOf("-");
    	else if (s.indexOf("*")>0)
    		return s.indexOf("*");
    	else if (s.indexOf("/")>0)
    		return s.indexOf("/");
    	else if (s.indexOf("%")>0)
    		return s.indexOf("%");
    	else if (s.indexOf("^")>0)
    		return s.indexOf("^");
    	else 
    		return -1;
    }
    
//    public static boolean isNumeric(String strNum) {
//        try {
//            double d = Double.parseDouble(strNum);
//        } catch (NumberFormatException | NullPointerException nfe) {
//            return false;
//        }
//        return true;
//    }
    
//    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
//
//    public static boolean isNumeric(String strNum) {
//        if (strNum == null) {
//            return false; 
//        }
//        boolean b= pattern.matcher(strNum).matches();
//        if (!b && strNum.indexOf('E')>0)
//        {
//        	int iPos=strNum.indexOf('E');
//        	return isNumeric(strNum.substring(0,iPos)) && isNumeric(strNum.substring(iPos+1));
//        }
//        
//        return b;
//    }
        
    private double Zerlege(String s)
    {
    	//A.g.fixtestError("Zerlege:"+s);
    	int iSZ=getSZ(s);
    	if (iSZ<0)
    	{
    		//A.printError("Zerlegen von "+s+" nicht möglich");
    		s=s.trim();
    		double d=s.startsWith("$") ? getd(s.substring(1)): Double.parseDouble(s);
    		return d;
    	}
    	else
    	{
	    	String sV1=s.substring(0, iSZ).trim();
	    	double d=sV1.startsWith("$") ? getd(sV1.substring(1)):Double.parseDouble(sV1);
	    	char c=s.charAt(iSZ);
	    	double d2=Zerlege(s.substring(iSZ+1).trim());
	    	if (c=='+')
	    		return d+d2;
	    	else if (c=='-')
	    		return d-d2;
	    	else if (c=='*')
	    		return d*d2;
	    	else if (c=='/')
	    		return d/d2;
	    	else if (c=='%')
	    		return d%d2;
	    	else if (c=='^')
	    		return Math.pow(d,d2);
	    	else
	    		return -1;
    	}
    }
    
    private String ZerlegeS(String s)
    {
    	int iSZ=s.indexOf("+");
    	if (iSZ<0)
    	{
//    		s=s.trim();
    		return s.startsWith("$") ? gets(s.substring(1)):s;
    	}
    	else
    	{
    		String sV1=s.substring(0, iSZ);//.trim();
	    	String s1=sV1.startsWith("$") ? gets(sV1.substring(1)):sV1;
	    	char c=s.charAt(iSZ);
	    	String s2=ZerlegeS(s.substring(iSZ+1));//.trim());
	    	return s1+s2;
    	}
    }

	private void RunMathematik()
	{
          //A.g.progInfo("RunMathematik");
		//if (!bSpalte && (sBefehl.equals("multiply number") || sBefehl.equals("divide number") || sBefehl.equals("Modolo")))
		//	A.printError("Calc.RunMathematik(): Die mathematische Funktion >"+sBefehl+"< ist ohne Spalte nicht ausführbar");
		if (sBefehl.equals("Befehl159"))
			calcF(Zerlege(sEingabe));
		else if (sBefehl.equals("add number"))
                  calcF(getFx1()+getFx2());
		  //if (A.sDest!=null) set(A.sDest,getd(A.sDest)+getF5()); else if (bVar) set(sEingabe,getd(sEingabe)+getF2()); else A.dReg+=getF();
		else if (sBefehl.equals("subtract number"))
                  calcF(bAR ? getFx2()-getFx1():getFx1()-getFx2());
		  //if (A.sDest!=null) set(A.sDest,getd(A.sDest)-getF5()); else if (bVar) set(sEingabe,getd(sEingabe)-getF2()); else A.dReg-=getF();
		else if (sBefehl.equals("multiply number"))
                  calcF(getFx1()*getFx2());
                  //if (A.sDest!=null) set(A.sDest,getd(A.sDest)*getF5()); else if (bVar) set(sEingabe,getd(sEingabe)*getF2()); else A.dReg*=getF();
		else if (sBefehl.equals("divide number"))
		{
			boolean bDiv0=(bAR ? getFx1():getFx2())==0;
			if (bDiv0)
				A.printError(MPos()+"Division durch 0",400);//,A.TabModelle.getI("Aic_Begriff"));
            calcF(bAR ? getFx2()/getFx1():getFx1()/getFx2());
                  //if (A.sDest!=null) set(A.sDest,getd(A.sDest)/getF5()); else if (bVar) set(sEingabe,getd(sEingabe)/getF2()); else A.dReg/=getF();
		}
		else if (sBefehl.equals("power number"))
                  calcF(bAR ? Math.pow(getFx2(),getFx1()):Math.pow(getFx1(),getFx2()));
		  //if (bVar) set(sEingabe,Math.pow(getd(sEingabe),getF2())); else A.dReg=Math.pow(A.dReg,getF());
		else if (sBefehl.equals("random number"))
		{
			A.dReg=Math.random();
                        A.g.testInfo("random:"+A.dReg+" bei "+sEingabe);
			if (sEingabe!=null)
				A.dReg=Math.round(A.dReg*Sort.getf(sEingabe));
                        A.g.testInfo("->"+A.dReg);
		}
		else if (sBefehl.equals("1/number"))
		{
			if (getFx3()==0)
				A.printError(MPos()+"1/0",400);
            calcF(1.0/getFx3());
		  //if (bVar) set(sEingabe,1.0/getF2()); else A.dReg=1.0/getF2();
		}
		else if (sBefehl.equals("Modolo"))
                  calcF(bAR ? getFx2()%getFx1():getFx1()%getFx2());
		  //if (bVar) set(sEingabe,getd(sEingabe)%getF2()); else A.dReg%=getF();
		else if (sBefehl.equals("modolo2"))
                  calcF(getFx3()%2);
		  //if (bVar) set(sEingabe,getd(sEingabe)%2); else A.dReg%=2;
		else if (sBefehl.equals("mul 2"))		// 30.4.2001
                  calcF(getFx3()*2);
		  //if (bVar) set(sEingabe,getd(sEingabe)*2); else A.dReg*=2.0;
		else if (sBefehl.equals("div 2"))		// 30.4.2001
                  calcF(getFx3()*0.5);
		  //if (bVar) set(sEingabe,getd(sEingabe)*0.5); else A.dReg*=0.5;
		else if (sBefehl.equals("add 1"))
                  calcF(getFx3()+1);
		  //if (bVar) set(sEingabe,getd(sEingabe)+1); else A.dReg++;
		else if (sBefehl.equals("subtract 1"))
                  calcF(getFx3()-1);
		  //if (bVar) set(sEingabe,getd(sEingabe)-1); else A.dReg--;
		else if (sBefehl.equals("invert"))
                  calcF(-getFx3());
		  //if (bVar) set(sEingabe,-getd(sEingabe)); else A.dReg=-A.dReg;
		else if (sBefehl.equals("not"))
			A.bReg = !(bSpalte?TabAbfrage.getB(sSpalte):A.bReg);
		else if (sBefehl.equals("round"))
                  A.dReg = Abfrage.Round(bSpalte?TabAbfrage.getF(sSpalte):A.dReg,1.0,true);
		else if (sBefehl.equals("round2"))
			      if (sEingabe==null)
			    	  A.g.fixtestError("round2 ohne Eingabe bei Zeile "+getZeile()+" in Modell "+A.TabModelle.getS("Bezeichnung"));
			      else if (bSpalte)
                    SoR(new Double(Abfrage.Round(TabAbfrage.getF(sSpalte),Sort.getf(sEingabe),true)));
                  else if (bVar)
                	  calcF(Abfrage.Round(getF3(),Sort.getf(sEingabe),true));
                  else
                    A.dReg = Abfrage.Round(A.dReg,Sort.getf(sEingabe),true);
        else if (sBefehl.equals("floor"))
                  if (bAS)
                    SoR(new Double(Math.floor(TabAbfrage.getF(sSpalte))));
                  else if (bVar)
                	  calcF(Math.floor(getF3()));
                  else
                    A.dReg = Math.floor(bSpalte?TabAbfrage.getF(sSpalte):A.dReg);
		else if (sBefehl.equals("ceil"))
                  if (bAS)
                    SoR(new Double(Math.ceil(TabAbfrage.getF(sSpalte))));
                  else if (bVar)
                	  calcF(Math.ceil(getF3()));
                  else
                    A.dReg = Math.ceil(bSpalte?TabAbfrage.getF(sSpalte):A.dReg);
		else if (sBefehl.equals("String add 1"))
			A.sReg=Static.addToString(A.sReg);
                else if (sBefehl.equals("Rente")) // reserve46 // für Rückstellung
                  A.dReg=A.dReg<=0 ? 0:(Math.exp(Math.log(1+A.dReg2/100)*A.dReg)-1)*100/A.dReg2;              	
		else
                  RunDatum();
                  //A.printError("Calc.RunMathematik(): Die mathematische Funktion >"+sBefehl+"< steht noch nicht zur Verfügung");
	}

	private void RunDatum()
	{
          //A.g.progInfo("RunDatum");
          //A.debugInfo("RunDatum");
		if (sBefehl.equals("add date"))
		{
			if (A.dtReg==null)
				A.dtReg=new DateWOD(A.getVon());
                        //A.debugInfo("add date: bSpalte="+bSpalte+"/"+TabAbfrage.getI(A.TabSpalten.getS("Kennung2"))+"/"+A.g.iAicJahr);
                        if (bSpalte && (A.TabSpalten.getS("Datentyp").equals("Mass") || A.TabSpalten.getS("Datentyp").equals("BewMass")) && A.TabSpalten.getI("Stamm")==A.g.getJahr())
                        {
                          long l=Math.round(TabAbfrage.getF(sSpalte)/(365.0*24*3600));
                          //A.g.progInfo("l="+l);
                          for(;l>0;l--)
                            A.dtReg.nextYear();
                        }
                        else
                        {
                          A.dtReg.setAllSeconds(A.dtReg.getAbsSeconds() + (bSpalte ? TabAbfrage.getF(sSpalte) : getF()));
                          A.dtReg.setTimezoneOffset();
                        }
			//A.dtReg=new Date(Math.round((A.dtReg==null?new DateWOD(dtVon):A.dtReg).getTime()+(bSpalte?TabAbfrage.getF(sSpalte):A.dReg)*1000.0));
		}
		else if (sBefehl.equals("subtract date"))
		{
			if (A.dtReg==null)
				A.dtReg=new DateWOD(A.getVon());
			A.dtReg.setAllSeconds(A.dtReg.getAbsSeconds()-(bSpalte?TabAbfrage.getF(sSpalte):A.dReg));
			A.dtReg.setTimezoneOffset();
			//A.dtReg=new Date(Math.round((A.dtReg==null?dtVon:A.dtReg).getTime()-(bSpalte?TabAbfrage.getF(sSpalte):A.dReg)*1000.0));
		}
                else if (sBefehl.equals("subtract dtReg"))
                {
                  if (A.dtReg2==null || A.dtReg==null && A.getBis()==null)
                    Static.printError(MPos()+"subtract dtReg: dtReg oder dtReg2 sind null");
                  else
                    A.dReg=Sort.getf(A.dtReg==null ? (Object)A.getBis():A.dtReg)-Sort.getf(A.dtReg2);
                }
		else if (sBefehl.equals("add time"))
                  addTime(getF());
		/*{
			if(bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.setAllSeconds(A.dtReg.getAbsSeconds()+A.dReg);
			A.dtReg.setTimezoneOffset();
			//A.dtReg=new Date(Math.round((bSpalte?(Date)TabAbfrage.getInhalt(sSpalte):A.dtReg).getTime()+A.dReg*1000.0));
		}*/
		else if (sBefehl.equals("subtract time"))
                  addTime(-getF());
		else if (sBefehl.equals("Befehl155")) // add days
		{
			int iDays=getI0();
			boolean bBack=iDays<0;
			DateWOD dt=new DateWOD(bSpalte && bAS ? TabAbfrage.getDate(sSpalte).getTime():A.dtReg.getTimeInMilli());
			for (int i=0;i<Math.abs(iDays);i++)
			  if (bBack)
				dt.yesterday();
			  else
				dt.tomorrow();
			if (bAS)
				SoR(dt.toTimestamp());
			else
				A.dtReg=dt;
		}
		else if (sBefehl.equals("clear time"))
		{
			if (bSpalte && !TabAbfrage.isNull(sSpalte))
				SoR(new DateWOD(TabAbfrage.getDate(sSpalte)).setTimeZero());
			else if (bVar && getdt(sVar)!=null)
				set(sVar,getdt(sVar).setTimeZero());
			else if (sEingabe!=null)
			{
				if (sEingabe.equals("von"))
					A.setVon(new DateWOD(A.getVon()).setTimeZero().toTimestamp());
				else if (sEingabe.equals("bis"))
					A.setBis(new DateWOD(A.getBis()).setTimeZero2().toTimestamp());
				else if (sEingabe.equals("vb"))
				{
					A.setVon(new DateWOD(A.getVon()).setTimeZero().toTimestamp());
					A.setBis(new DateWOD(A.getBis()).setTimeZero2().toTimestamp());
				}
				else
		            A.printError("<clear time> mit Eingabe="+sEingabe+" wird nicht unterstützt",400);
			}
			else if (A.dtReg != null)
              A.dtReg.setTimeZero();
            else
              A.g.printError(MPos()+"<clear time> mit leere"+(bSpalte ? "r Spalte":bVar ? "r Variable":"m Datumsregister")+" nicht durchführbar");
		}
		else if (sBefehl.equals("first day"))
		{
			DateWOD dt=null;
			if (bSpalte)
				dt=new DateWOD(TabAbfrage.getDate(sSpalte));
			else
				dt=A.dtReg;
//			if (bSpalte)
//				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			if (dt==null)
				A.printError("first day mit Datum=null nicht möglich",400);
			else
			{
			dt.setTimeZero();
			char c= sEingabe==null || sEingabe.length()==0 ? '1':sEingabe.charAt(0);
			if (c=='1' || c=='M')
			   dt.setDate(1);
			else if (c=='J' || c=='Q')
			{
				dt.setDate(1);
				dt.setMonth(c=='J' ? 1:((dt.getMonth()-1)/3)*3+1);
			}
			else if (c!='T')
					A.printError("first day mit "+sEingabe+" wird nicht unterstützt",400);
			if (bAS)
				SoR(dt.toTimestamp());
			else 
				A.dtReg=dt;
			}
		}
		else if (sBefehl.equals("set new year"))
		{
			DateWOD dt=null;
			if (bSpalte)
				dt=new DateWOD(TabAbfrage.getDate(sSpalte));
			else
				dt=A.dtReg;
			dt.setDay1();
			dt.setMonth(1);
			if (bAS)
				SoR(dt.toTimestamp());
			else 
				A.dtReg=dt;
		}
        else if (sBefehl.equals("set year"))  //neu: 10.2.2004
		{
          DateWOD dt=new DateWOD(TabAbfrage.getDate(sSpalte));
          dt.setYear(A.dtReg.getYear());
          A.dtReg=dt;
        }
		else if (sBefehl.equals("last day"))
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			if (A.dtReg != null)
				A.dtReg.setDate(A.dtReg.monthLength());
			else
                A.g.printError(MPos()+"<last day> mit leerem Datumsregister nicht durchführbar");
		}
		else if (sBefehl.equals("day after"))
		{
			DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg;
                if (dt !=null && !dt.isNull())
                  dt.tomorrow();
                else
                  A.g.fixtestInfo("Calc: day after ohne Datum nicht durchführbar: iQryReg="+A.iQryReg);
            if (bAS)
            	SoR(dt);
            else
            	A.dtReg=dt;
                //  Static.printError("Calc: day after ohne Datum nicht möglich: iQryReg="+A.iQryReg);
			//A.dtReg=new Date(new DateWOD((bSpalte?(Date)TabAbfrage.getInhalt(sSpalte):A.dtReg).getTime()).tomorrow());
		}
		else if (sBefehl.equals("day before"))
		{
			DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg;
			if (dt!=null && !dt.isNull())
				dt.yesterday();
			else
                A.g.fixtestInfo("Calc: day before ohne Datum nicht durchführbar: iQryReg="+A.iQryReg);		
			if (bAS)
            	SoR(dt);
            else
            	A.dtReg=dt;
			//A.dtReg=new Date(new DateWOD((bSpalte?(Date)TabAbfrage.getInhalt(sSpalte):A.dtReg).getTime()).yesterday());
		}
                else if (sBefehl.equals("next vb"))
                {
                	DateWOD dwVon=new DateWOD(bVar ? getVonVar(sVar):A.getVon());
                	DateWOD dwBis=new DateWOD(bVar ? getBisVar(sVar):A.getBis());
                	long lVon=sEingabe == null ? dwVon.tomorrow():dwVon.add((int)getF()*1000);
                	long lBis=sEingabe == null ? dwBis.tomorrow():dwBis.add((int)getF()*1000);
                  if (bVar)
                    setVB(sVar,new Timestamp(lVon),new java.sql.Timestamp(lBis));
                  else
                    A.setVonBis(new Timestamp(lVon),new java.sql.Timestamp(lBis));
                }
                else if (sBefehl.equals("prev vb"))
                  if (bVar)
                    setVB(sVar,new Timestamp(new DateWOD(getVonVar(sVar)).yesterday()),new java.sql.Timestamp(new DateWOD(getBisVar(sVar)).yesterday()));
                  else
                    A.setVonBis(new Timestamp(new DateWOD(A.getVon()).yesterday()),new java.sql.Timestamp(new DateWOD(A.getBis()).yesterday()));
                else if (sBefehl.equals("next bis"))
                  A.setBis(new Timestamp(new DateWOD(A.getBis()).tomorrow()));
		else if (sBefehl.equals("set quarter"))
		{
			DateWOD dtVon=new DateWOD(A.dtReg.toTimestamp());
			dtVon.setDate(1);
			dtVon.setMonth(((dtVon.getMonth()-1)/3)*3+1);
                        Timestamp tsVon=dtVon.toTimestamp();
			dtVon.nextMonth();
			dtVon.nextMonth();
			dtVon.nextMonth();
			A.setVonBis(tsVon,dtVon.toTimestamp());
		}
		else if (sBefehl.equals("floor seconds"))
		{
			DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):bVar ? getdt(sVar):A.dtReg;
			double d=getI();
//			if (bSpalte)
//				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			if (d!=0)
			{
				dt.setAllSeconds(Math.floor(dt.getAbsSeconds()/d)*d);
				dt.setTimezoneOffset();
			}
			if (bSpalte)
				SoR(dt.toTimestamp());
			else if (bVar)
				set(sVar,dt);
			else
				A.dtReg=dt;
		}
		else if (sBefehl.equals("roundup seconds"))
		{
			DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):bVar ? getdt(sVar):A.dtReg;
			double d=getI();
//			if (bSpalte)
//				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			if (d!=0)
			{
				dt.setAllSeconds(Math.ceil(dt.getAbsSeconds()/d)*d);
				dt.setTimezoneOffset();
			//A.dtReg= A.dReg==0 ? (bSpalte?(Date)TabAbfrage.getInhalt(sSpalte):A.dtReg):
			//	new Date(Math.round(Math.ceil(((bSpalte?(Date)TabAbfrage.getInhalt(sSpalte):A.dtReg).getTime()/1000.0-Static.lGMT)/A.dReg)*A.dReg+Static.lGMT)*1000);
			}
			if (bSpalte)
				SoR(dt.toTimestamp());
			else if (bVar)
				set(sVar,dt);
			else
				A.dtReg=dt;
		}
		else if (sBefehl.equals("round seconds"))
		{
			DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):bVar ? getdt(sVar):A.dtReg;
			double d=getI();
//			if (bSpalte)
//				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			if (d!=0)
			{
				dt.setAllSeconds(Math.round(dt.getAbsSeconds()/d)*d);
				dt.setTimezoneOffset();
			//A.dtReg= A.dReg==0 ? (bSpalte?(Date)TabAbfrage.getInhalt(sSpalte):A.dtReg):
			//	new Date(Math.round(Math.round(((bSpalte?(Date)TabAbfrage.getInhalt(sSpalte):A.dtReg).getTime()/1000.0-Static.lGMT)/A.dReg)*A.dReg+Static.lGMT)*1000);
			}
			if (bSpalte)
				SoR(dt.toTimestamp());
			else if (bVar)
				set(sVar,dt);
			else
				A.dtReg=dt;
		}
		else if (sBefehl.equals("next week"))
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.nextWeek();
		}
		else if (sBefehl.equals("next month"))
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.nextMonth();
		}
		else if (sBefehl.equals("Befehl157")) // "next quater"
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.nextMonth();
			A.dtReg.nextMonth();
			A.dtReg.nextMonth();
		}
		else if (sBefehl.equals("next year"))
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.nextYear();
		}
		else if (sBefehl.equals("prev week"))
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.prevWeek();
		}
		else if (sBefehl.equals("prev month"))
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.prevMonth();
		}
		else if (sBefehl.equals("Befehl158")) // "prev quater"
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.prevMonth();
			A.dtReg.prevMonth();
			A.dtReg.prevMonth();
		}
		else if (sBefehl.equals("prev year"))
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.prevYear();
		}
		else if (sBefehl.equals("get weekday"))
		{
			A.dReg=bSpalte?new DateWOD(TabAbfrage.getDate(sSpalte)).getDay():A.dtReg.getDay();
		}
		else if (sBefehl.equals("Befehl140")) // get month
		{
			A.dReg=bSpalte?new DateWOD(TabAbfrage.getDate(sSpalte)).getMonth():A.dtReg.getMonth();
		}
		else if (sBefehl.equals("set first weekday") || sBefehl.equals("set last weekday"))
		{
			if (bSpalte)
				A.dtReg=new DateWOD(TabAbfrage.getDate(sSpalte));
			A.dtReg.setWeekday(sBefehl.equals("set first weekday")?0:6);
		}
		else if (sBefehl.equals("next n month"))
                {
                  int iAnzahl = bSpalte ? TabAbfrage.getI(sSpalte) : getI();
                  if (iAnzahl%12==0)
                    for(int i = 0; i < iAnzahl/12; i++)
                      A.dtReg.nextYear();
                  else
                    for(int i = 0; i < iAnzahl; i++)
                      A.dtReg.nextMonth();
                }
		else if (sBefehl.equals("next n weeks"))
                {
                  int iAnzahl = bSpalte ? TabAbfrage.getI(sSpalte) : getI();
                  for(int i = 0; i < iAnzahl; i++)
                    A.dtReg.nextWeek();
                }
        else if (sBefehl.equals("prev n years")) //reserve85
        {
          int iAnzahl=getI0();//bSpalte? TabAbfrage.getI(sSpalte):getI();
          for(int i = 0; i < iAnzahl; i++)
            A.dtReg.prevYear();
        }
		else if (sBefehl.equals("prev n month"))
                {
                  int iAnzahl=bSpalte? TabAbfrage.getI(sSpalte):getI();
                  if (iAnzahl%12==0)
                    for(int i = 0; i < iAnzahl/12; i++)
                      A.dtReg.prevYear();
                  else
                    for(int i = 0; i < iAnzahl; i++)
                      A.dtReg.prevMonth();
                }
		else if (sBefehl.equals("prev n weeks"))
                {
                  int iAnzahl = bSpalte ? TabAbfrage.getI(sSpalte) : getI();
                  for(int i = 0; i < iAnzahl; i++)
                    A.dtReg.prevWeek();
                }
		else if (sBefehl.equals("get monthLength"))
			A.dReg=(bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):bVar ? getdt(sVar):A.dtReg).monthLength();
		else if (sBefehl.equals("Befehl145")) // "get yearLength"
			A.dReg=(bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):bVar ? getdt(sVar):A.dtReg).yearLength();
        else if (sBefehl.equals("get years vb"))
        {
        	Date dtV=bSpalte?TabAbfrage.getDate(sSpalte):bVar ? getVonVar(sVar):A.getVon();
            Date dtB=bSpalte?TabAbfrage.getDate(A.TabSpalten.getS("Kennung2")):bVar ? getBisVar(sVar):A.getBis();
            iFC=dtV==null || dtB==null ? 1:0;
            if (iFC>0)
          	  A.dReg=0;
            else
            {
              DateWOD dtVon1=new DateWOD(dtV);
              DateWOD dtBis1=new DateWOD(dtB);
	          int i=-1;
	          if (sEingabe!=null && sEingabe.equals("all years"))
	          {
	        	  dtVon1.setDay1();
	        	  dtVon1.setMonth(1);
	          }
	          while (dtVon1.getAbsSeconds()<dtBis1.getAbsSeconds())
	          {
	            dtVon1.nextYear();
	            i++;
	          }
	          if (sEingabe!=null && sEingabe.endsWith("years") || dtVon1.equals(dtBis1))
	        	  i++;
	          A.dReg=i;
            }
        }
        else if (sBefehl.equals("get month vb"))
        {
        	Date dtV=bSpalte?TabAbfrage.getDate(sSpalte):bVar ? getVonVar(sVar):A.getVon();
            Date dtB=bSpalte?TabAbfrage.getDate(A.TabSpalten.getS("Kennung2")):bVar ? getBisVar(sVar):A.getBis();
            iFC=dtV==null || dtB==null ? 1:0;
            if (iFC>0)
          	  A.dReg=0;
            else
            {
              DateWOD dtVon1=new DateWOD(dtV);
              DateWOD dtBis1=new DateWOD(dtB);
	          int i=-1;
	          if (sEingabe!=null && sEingabe.equals("all month"))
	        	  dtVon1.setDay1();
	          while (dtVon1.getAbsSeconds()<dtBis1.getAbsSeconds())
	          {
	            i++;
	            dtVon1.nextMonth();          
	          }
	          //A.g.fixtestError(sEingabe+":"+dtVon1+"/"+dtBis1);
	          if (sEingabe!=null && sEingabe.endsWith("month") || dtVon1.equals(dtBis1))
	        	  i++;
	          A.dReg=i;
            }
        }
        else if (sBefehl.equals("get days vb"))
        {
          Date dtV=bSpalte?TabAbfrage.getDate(sSpalte):bVar ? getVonVar(sVar):A.getVon();
          Date dtB=bSpalte?TabAbfrage.getDate(A.TabSpalten.getS("Kennung2")):bVar ? getBisVar(sVar):A.getBis();
          iFC=dtV==null || dtB==null ? 1:0;
          if (iFC>0)
        	  A.dReg=0;
          else
          {
	          DateWOD dtVon1=new DateWOD(dtV);
	          DateWOD dtBis1=new DateWOD(dtB);
	          int i=-1;
	          while (dtVon1.getAbsSeconds()<=dtBis1.getAbsSeconds())
	          {
	            dtVon1.tomorrow();
	            i++;
	          }
	          A.dReg=i;
          }
        }
        else if (sBefehl.equals("set Stichtag"))
              A.dtStichtag=bSpalte ? TabAbfrage.isNull(sSpalte) ? null:new DateWOD(TabAbfrage.getDate(sSpalte)):sEingabe != null ? getDWE(sEingabe):
                  A.dtReg==null ? null:new DateWOD(A.dtReg.getTimeInMilli());
        else if (sBefehl.equals("get days")) // reserve41
        {
              if (A.dtReg==null || bSpalte && TabAbfrage.isNull(sSpalte) || !bSpalte && A.dtReg2==null)
              {
            	  A.dReg=0;
            	  iFC=1;
              }
//            	  A.printError("get days: von ist null", 400);
//              else if (bSpalte && TabAbfrage.isNull(sSpalte) || !bSpalte && A.dtReg2==null)
//            	  A.printError("get days: bis ist null", 400);
              else
              {
            	  DateWOD dtVon1=new DateWOD(A.dtReg.getTimeInMillis());                
	              DateWOD dtBis1=new DateWOD(bSpalte?TabAbfrage.getDate(sSpalte).getTime():A.dtReg2.getTimeInMillis());
	              int iF=1;
	              if (dtVon1.after(dtBis1))
	              {
	                DateWOD dtS=dtVon1;
	                dtVon1=dtBis1;
	                dtBis1=dtS;
	                iF=-1;
	              }
	              int i=-1;
	              while (dtVon1.getAbsSeconds()<=dtBis1.getAbsSeconds())
	              {
	                dtVon1.tomorrow();
	                i++;
	              }
	              A.dReg=i*iF;
	              iFC=0;
              }
        }
        else if (sBefehl.equals("next n years")) // reserve47
            {
              int iAnzahl = bSpalte ? TabAbfrage.getI(sSpalte) : getI();
              for(int i = 0; i < iAnzahl; i++)
                A.dtReg.nextYear();
            }
        else
          RunSpeichern();
            //A.printError("Calc.RunDatum(): Die Datums-Funktion >"+sBefehl+"< steht noch nicht zur Verfügung");
	}

        private void addTime(double d)
        {
          DateWOD dt=new DateWOD(bSpalte ? TabAbfrage.getDate(sSpalte).getTime():A.dtReg.getTimeInMilli());
          dt.setAllSeconds(dt.getAbsSeconds()+d);
          dt.setTimezoneOffset();
          if (bAS)
            SoR(dt.toTimestamp());
          else
            A.dtReg=dt;
        }

	private void clearDataRecord()
	{
		((Tabellenspeicher)A.TabAbfragen.getInhalt("save")).clearAll();
	}

        private void SoR(Object Obj)
        {
          if ((TabBefehle.getI("MBITS")&SAVE)>0)
            A.save(sSpalte,Obj);
          else
            TabAbfrage.setInhalt(sSpalte,Obj);            
        }
        
        private void SoR(Object Obj,int iSp)
        {
          String sSp=iSp<2 ? sSpalte:A.TabSpalten.getS("Kennung"+iSp);
          if ((TabBefehle.getI("MBITS")&SAVE)>0)
            A.save(sSp,Obj);
          else
            TabAbfrage.setInhalt(sSp,Obj);            
        }

        private void calcF(double d)
        {
          if (bAS && bSpalte)
            SoR(new Double(A.Round100(d)));
          else if (bAR)
            A.dReg=d;
          else if (A.sDest!=null)
            set(A.sDest,d);
          else if (bVar)
            set(sVar,d);
          else
            A.dReg=d;
        }
        
    private Timestamp DWtoTS(DateWOD DW,boolean bSetZero)
    {
    // bVar ? getdt(sVar):A.dtReg==null?null:((DateWOD)A.dtReg.clone()).setTimeZero().toTimestamp());
    	if (DW==null)
    		return null;
    	if (bSetZero)
    		return ((DateWOD)DW.clone()).setTimeZero().toTimestamp();
    	else
    	  return DW.toTimestamp();
    }

	private void RunSpeichern()
	{
          //A.g.progInfo("RunSpeichern");
		/*if (!bSpalte && !sBefehl.equals("close file") && !sBefehl.equals("write line2") && !sBefehl.equals("import exchange"))
			A.printError("Calc.RunSpeichern(): Der Befehl >"+sBefehl+"< (Zeile "+Static.FillNull(""+(TabBefehle.getI("aic_befehl")-iErsterBefehl+1),3)+") ohne Spalte ist nicht möglich!");
		else
		{*/
			if (bSpalte && TabAbfrage.out() && sGruppe.equals("saveorders") && (sBefehl.startsWith("remember ") || sBefehl.startsWith("save ")))
				TabAbfrage.newLine();
			// Spalten ändern
			if (sBefehl.equals("delete"))
				TabAbfrage.deleteData(sSpalte);
			else if (sBefehl.equals("remember boolean"))
				SoR(new Boolean(A.bReg));
            else if (sBefehl.equals("remember Bool3"))
            {
              int iPos=A.g.getAuswahlPos(sEingabe==null ? ""+A.iB3Reg:sEingabe, A.TabSpalten.getI("Eig"));
              SoR(A.g.getAuswahlAic(iPos));
              if (iPos<0)
            	  TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung2"),null);
              else
            	  TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung2"),A.g.TabAuswahl.getI(iPos,"Nr"));
              TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung3"), A.g.getAuswahl(iPos));
            }
			else if (sBefehl.equals("remember aic"))
				if (bVar)
					SoR(geti(sVar)==0 ? null:geti(sVar));
				else if (sEingabe != null)
          SoR(getI()==0 ? null:getI());
        else
					SoR(A.iReg==0 ? null:A.iReg); // bVar ? geti(sVar) brachte NullPointerException
      else if (sBefehl.equals("remember Bew"))
        SoR(A.iBewReg==0 ? null:A.iBewReg);
			else if (sBefehl.equals("remember QryAic"))
				SoR(A.iQryReg==0 ? null:A.iQryReg);
			else if (sBefehl.equals("remember false")) // wird für SV-Export benötigt
				SoR(Boolean.FALSE);
			else if (sBefehl.equals("remember NIL"))
				SoR(null);
			else if (sBefehl.equals("remember number"))
                        {
                          if (bSpalte)
                          {
                        	  SoR(new Double(A.Round100(bVar ? getd(sVar):sEingabe != null ? Sort.getf(sEingabe) : A.dReg)));
//                            A.g.fixInfo("remember number "+sSpalte+" "+A.iMass+"/"+A.TabSpalten.getS("Kennung2")+"/"+TabAbfrage.exists(A.TabSpalten.getS("Kennung2")));                           
                            if (A.iMass>0 && !A.TabSpalten.isNull("Kennung2") && TabAbfrage.exists(A.TabSpalten.getS("Kennung2")))
                            	TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung2"),A.iMass);
                          }
                          else if (bVar)
                            set(sVar,A.dReg);
                        }
			else if (sBefehl.equals("remember true"))
				SoR(Boolean.TRUE);
			else if (sBefehl.equals("remember act_Aic"))
				SoR(A.VecStamm.elementAt(A.iPos));
			else if (sBefehl.equals("remember date"))
                        {
                          java.sql.Timestamp ts=bVar ? (getdt(sVar) == null ? null : getdt(sVar).toTimestamp()) : A.dtReg == null ? null : A.dtReg.toTimestamp();
                          if (bAlle)
                          {
                            for(int i=0;i<TabAbfrage.size();i++)
                              TabAbfrage.setInhalt(i,sSpalte, ts);
                          }
                          else
                        	  SoR(ts);
                        }
			else if (sBefehl.equals("remember from"))
			{
				SoR(bVar ? getVonVar(sVar):A.dtReg==null?null:A.g.hasZone(iBew) && A.dtReg.hasZone() ? new DateWOD(A.dtReg):A.dtReg.toTimestamp(),1);
				if (A.dtReg != null && A.TabSpalten.getS("Datentyp").equals("BewVon_Bis") && TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung2")) !=null)
					SoR(diff(A.dtReg,TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung2"))),3);
			}
			else if (sBefehl.equals("remember to"))
			{
				SoR(bVar ? getBisVar(sVar):A.dtReg==null?null:A.g.hasZone(iBew) && A.dtReg.hasZone() ? new DateWOD(A.dtReg):A.dtReg.toTimestamp(),2);
				if (A.dtReg != null && A.TabSpalten.getS("Datentyp").equals("BewVon_Bis") && TabAbfrage.getInhalt(sSpalte) !=null)
					SoR(diff(TabAbfrage.getInhalt(sSpalte),A.dtReg),3);
			}
			else if (sBefehl.equals("remember duration"))
				SoR(getF(),A.TabSpalten.getB("Per") ? 1:3);
			else if (sBefehl.equals("remember act_date"))
				SoR(getAktDate().toTimestamp());
			else if (sBefehl.equals("remember String"))
                        {
                          if (bSpalte)
                          {
                            String s = bVar ? gets(sVar) : sEingabe != null ? sEingabe : A.sReg;
                            if (bAlle)
                            {
                              for (int i = 0; i < TabAbfrage.size(); i++)
                                TabAbfrage.setInhalt(i, A.TabSpalten.getS("Kennung3"), s);
                            }
                            else
                            	SoR(s,3);
                          }
                          else if (bVar)
                            set(sVar,A.sReg);
                        }
			else if (sBefehl.equals("remember line"))
			{
				int iFrom=TabAbfrage.getPos();
				TabAbfrage.newLine();
				int iTo=TabAbfrage.getPos();
				int iAbfrage=A.TabSpalten.getI("Abfrage");
				for(A.TabSpalten.posInhalt("Abfrage",iAbfrage);!A.TabSpalten.eof()&&A.TabSpalten.getI("Abfrage")==iAbfrage;A.TabSpalten.moveNext())
				{
					//A.g.progInfo(A.TabSpalten.getS("Kennung")+"/"+A.TabSpalten.getS("Kennung2")+"/"+A.TabSpalten.getS("Kennung3"));
					TabAbfrage.setPos(iFrom);
					Object Obj=TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung"));
					Object Obj2=A.TabSpalten.getS("Kennung2").equals("")?null:TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung2"));
					Object Obj3=A.TabSpalten.getS("Kennung3").equals("")||A.TabSpalten.getS("Kennung3").equals(A.TabSpalten.getS("Kennung"))?null:TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung3"));
					TabAbfrage.setPos(iTo);
					TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung"),Obj);
					if(Obj2 !=null)
						TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung2"),Obj2);
					if(Obj3 !=null)
						TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung3"),Obj3);
				}
			}
			// Spalten speichern
			else if (sBefehl.equals("save line"))
			{
				int iFrom=TabAbfrage.getPos();
				TabAbfrage.newLine();
				int iTo=TabAbfrage.getPos();
				int iAbfrage=A.TabSpalten.getI("Abfrage");
				TabAbfrage.setPos(iFrom);
				for(A.TabSpalten.posInhalt("Abfrage",iAbfrage);!A.TabSpalten.eof()&&A.TabSpalten.getI("Abfrage")==iAbfrage;A.TabSpalten.moveNext())
				{
					//A.g.progInfo(A.TabSpalten.getS("Kennung")+"/"+A.TabSpalten.getS("Kennung2")+"/"+A.TabSpalten.getS("Kennung3"));
					//TabAbfrage.setPos(iFrom);
					String sDT=A.TabSpalten.getS("Datentyp");
                                        if (A.TabSpalten.getS("Kennung").indexOf('_')<0 && !DTnotSave.contains(sDT))
					{
						Object Obj2=A.TabSpalten.getS("Kennung2").equals("")?null:TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung2"));
						Object Obj3=A.TabSpalten.getS("Kennung3").equals("")||A.TabSpalten.getS("Kennung3").equals(A.TabSpalten.getS("Kennung"))?null:TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung3"));
						//TabAbfrage.setPos(iTo);
						A.save(A.TabSpalten.getS("Kennung"),TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung")));
						if(Obj2 !=null)
							A.save(A.TabSpalten.getS("Kennung2"),Obj2);
						if(Obj3 !=null && !A.TabSpalten.getS("Kennung2").equals(""))
							A.save(A.TabSpalten.getS("Kennung3"),Obj3);
					}
				}
				TabAbfrage.setPos(iTo);
			}
                        else if (sBefehl.equals("save this line"))
                        {
                                int iAbfrage=A.TabSpalten.getI("Abfrage");
                                for(A.TabSpalten.posInhalt("Abfrage",iAbfrage);!A.TabSpalten.eof()&&A.TabSpalten.getI("Abfrage")==iAbfrage;A.TabSpalten.moveNext())
                                {
                                  String sDT=A.TabSpalten.getS("Datentyp");
                                        if (A.TabSpalten.getS("Kennung").indexOf('_')<0 && (A.TabSpalten.getI("bits2")&Abfrage.cstSpNotSave)==0 && !DTnotSave.contains(sDT))
                                        {
                                                Object Obj2=A.TabSpalten.getS("Kennung2").equals("")?null:TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung2"));
                                                Object Obj3=A.TabSpalten.getS("Kennung3").equals("")||A.TabSpalten.getS("Kennung3").equals(A.TabSpalten.getS("Kennung"))?null:TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung3"));
                                                A.g.progInfo(A.TabSpalten.getS("Kennung")+"/"+A.TabSpalten.getS("Kennung2")+":"+Obj2+"/"+A.TabSpalten.getS("Kennung3")+":"+Obj3);
                                                //TabAbfrage.setPos(iTo);
                                                A.save(A.TabSpalten.getS("Kennung"),TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung")));
                                                if (!sDT.endsWith("Hierarchie") && !sDT.equals("BewMass") && !sDT.equals("BewBool3") && !sDT.equals("Bool3"))
                                                {
                                                  if (Obj2 != null)
                                                    A.save(A.TabSpalten.getS("Kennung2"), Obj2);
                                                  if (Obj3 != null && !A.TabSpalten.getS("Kennung2").equals(""))
                                                    A.save(A.TabSpalten.getS("Kennung3"), Obj3);
                                                }
                                        }
                                }
                        }
//			else if (sBefehl.equals("save act. aic"))
//				A.save(sSpalte,A.VecStamm.elementAt(A.iPos));
//			else if (sBefehl.equals("save aic"))
//				if (bVar)
//					A.save(sSpalte,geti(sVar)==0 ? null:geti(sVar));
//				else
//					A.save(sSpalte,A.iReg==0 ? null:A.iReg); // bVar ? geti(sVar) brachte NullPointerException
//            else if (sBefehl.equals("save Bew"))
//				A.save(sSpalte,A.iBewReg==0?null:new Integer(A.iBewReg));
//			else if (sBefehl.equals("save QryAic"))
//				A.save(sSpalte,A.iQryReg==0?null:new Integer(A.iQryReg));
//			else if (sBefehl.equals("save boolean"))
//				A.save(sSpalte,new Boolean(A.bReg));
//                        else if (sBefehl.equals("save Bool3"))
//                        {
//                          int iPos=A.g.getAuswahlPos(sEingabe==null ? ""+A.iB3Reg:sEingabe, A.TabSpalten.getI("Eig"));
//                          A.save(sSpalte, A.g.getAuswahlAic(iPos));
//                          if (iPos<0)
//                        	  TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung2"),null);
//                          else
//                        	  TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung2"),A.g.TabAuswahl.getI(iPos,"Nr"));
//                          TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung3"), A.g.getAuswahl(iPos));
//                        }
//			else if (sBefehl.equals("save false")) // sollte nicht verwendet werden, da auf DB false nicht gespeichert wird
//				A.save(sSpalte,Boolean.FALSE);
//			else if (sBefehl.equals("save true"))
//				A.save(sSpalte,Boolean.TRUE);
//			else if (sBefehl.equals("save NIL"))
//				A.save(sSpalte,null);
			else if (sBefehl.equals("save_Daten"))
				A.save(sSpalte,A.iDaten,true);
//			else if (sBefehl.equals("save number"))
//			{
//				A.save(sSpalte,new Double(A.Round100(bVar ? getd(sVar):sEingabe != null ? Sort.getf(sEingabe):A.dReg)));
//				if (A.iMass>0 && !A.TabSpalten.isNull("Kennung2") && TabAbfrage.exists(A.TabSpalten.getS("Kennung2")))
//					TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung2"),A.iMass);
//			}
			else if (sBefehl.equals("save model"))
				A.save(sSpalte,new Integer(A.iModellBegriff));
//			else if (sBefehl.equals("save act_date"))
//				A.save(sSpalte,getAktDate().toTimestamp());
			else if (sBefehl.equals("save datetime"))
				A.save(sSpalte,DWtoTS(bVar ? getdt(sVar):A.dtReg,false));//==null?null:A.dtReg.toTimestamp());
			else if (sBefehl.equals("save date"))
				A.save(sSpalte,DWtoTS(bVar ? getdt(sVar):A.dtReg,true));//==null?null:((DateWOD)A.dtReg.clone()).setTimeZero().toTimestamp());
//			else if (sBefehl.equals("save from"))
//			{
//				A.save(sSpalte,A.dtReg==null?null:A.g.hasZone(iBew) && A.dtReg.hasZone() ? new DateWOD(A.dtReg):A.dtReg.toTimestamp());
//				if (A.dtReg != null && TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung2")) !=null && A.TabSpalten.getS("Datentyp").equals("BewVon_Bis"))
//					A.save(A.TabSpalten.getS("Kennung3"),diff(A.dtReg,TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung2"))));
//			}
//			else if (sBefehl.equals("save to"))
//			{
//				A.save(A.TabSpalten.getS("Kennung2"),A.dtReg==null?null:A.g.hasZone(iBew) && A.dtReg.hasZone() ? new DateWOD(A.dtReg):A.dtReg.toTimestamp());
//				if (A.dtReg != null && TabAbfrage.getInhalt(sSpalte) !=null && A.TabSpalten.getS("Datentyp").equals("BewVon_Bis"))
//					A.save(A.TabSpalten.getS("Kennung3"),diff(TabAbfrage.getInhalt(sSpalte),A.dtReg));
//			}
//			else if (sBefehl.equals("save duration"))
//				A.save(A.TabSpalten.getB("Per")?sSpalte:A.TabSpalten.getS("Kennung3"),getF());
//			else if (sBefehl.equals("save String"))
//				A.save(A.TabSpalten.getS("Kennung3"),bVar ? gets(sVar):sEingabe != null ? sEingabe:A.sReg);
			else if (sBefehl.equals("Befehl187")) // SoR GPS
			{
				GPS gps=bVar ? getGPSVar(sVar):A.gps;
				if ((TabBefehle.getI("MBITS")&SAVE)>0)
					A.save(sSpalte,gps);
				else
					TabAbfrage.setInhalt(sSpalte,gps.getName());
				TabAbfrage.setInhalt("b"+sSpalte.substring(1),gps.getLat());
	            TabAbfrage.setInhalt("l"+sSpalte.substring(1),gps.getLng());
			}
			else if (sBefehl.equals("write data record"))
			{
				if (sEingabe != null)
				{
					int iAnl=A.g.getBegriffAicS("Anlage",sEingabe);
					if (iAnl==0)
					{
						A.printError("write data record: Anlage "+sEingabe+" nicht gefunden", 400);
						return;
					}
					else
						A.iAnlage=iAnl;
				}
				else if (!A.bAnlage)
					A.iAnlage=A.g.getCodeAic("Anlage",A.bTimer && !Static.bWeb ?"Timer":"Modell");
				A.g.printExec(MPos()+": [write data record] bei "+A.g.getBeg(A.TabAbfragen.getI("Begriff"))+(A.TabAbfragen.isNull("Filter") ? "":"-Filter")+", Anlage="+A.iAnlage,false);
				A.saveAll();
			}
			else if (sBefehl.equals("clear data record"))
				clearDataRecord();
			else if (sBefehl.equals("write file"))
				TabAbfrage.CalcExport(bTitel && (A.TabAbfragen.getI("bits")&Abfrage.cstKeinTitel)==0,true,getTrennzeichnen(),A.sReg,A.TabSpalten,(A.TabAbfragen.getI("bits") & Abfrage.cstAnhaengen)>0,false,A.bMass);
			else if (sBefehl.equals("write line"))
				TabAbfrage.CalcExport(false,false,getTrennzeichnen(),A.sReg,A.TabSpalten,(A.TabAbfragen.getI("bits") & Abfrage.cstAnhaengen)>0,false,A.bMass);
			else if (sBefehl.equals("write file plus"))
				TabAbfrage.CalcExport(false,true,getTrennzeichnen(),A.sReg,A.TabSpalten,(A.TabAbfragen.getI("bits") & Abfrage.cstAnhaengen)>0,true,A.bMass);
			else if (sBefehl.equals("write line plus"))
				TabAbfrage.CalcExport(false,false,getTrennzeichnen(),A.sReg,A.TabSpalten,(A.TabAbfragen.getI("bits") & Abfrage.cstAnhaengen)>0,true,A.bMass);
			else if (sBefehl.equals("Autoimport"))
				A.Imp=new Import(A.g,A.TabAbfragen.getI("Bew")>0 ? -A.TabAbfragen.getI("Bew") : A.TabAbfragen.getI("Stt"), A.TabAbfragen.getI("Aic"),A.sReg,getTrennzeichnen(),false,false,bTitel);
            else if (sBefehl.equals("Autoimport4")) // reserve82
              A.Imp=new Import(A.g,A.TabAbfragen.getI("Bew")>0 ? -A.TabAbfragen.getI("Bew") : A.TabAbfragen.getI("Stt"),
                               A.TabAbfragen.getI("Aic"),A.sReg,getTrennzeichnen(),false,true,bTitel);
            //else if (sBefehl.equals("Autoimport2"))
            //  A.printError("Autoimport2 muss durch Autoimport ersetzt werden!");
            //  A.Imp=new Import(A.g,A.TabAbfragen.getI("Bew")>0 ? -A.TabAbfragen.getI("Bew") : A.TabAbfragen.getI("Stt"),
            //                   A.TabAbfragen.getI("Aic"),A.sReg,getTrennzeichnen(),false);
            else if (sBefehl.equals("Autoimport3"))
              A.Imp=new Import(A.g,A.TabAbfragen.getI("Bew")>0 ? -A.TabAbfragen.getI("Bew") : A.TabAbfragen.getI("Stt"),
                                     A.TabAbfragen.getI("Aic"),A.sReg,getTrennzeichnen(),true,false,bTitel);
            else if (sBefehl.equals("ODBC Import"))
              TabAbfrage.Fremd_Import(A.StaString,A.TabSpalten,true);
            else if (sBefehl.equals("JDBC Import"))
              TabAbfrage.Fremd_Import(A.StaString,A.TabSpalten,false);
            else if (sBefehl.equals("import exchange"))
              ImportiereWaehrung();
			else if (sBefehl.equals("remove real aic"))
				TabAbfrage.setInhalt("aic_bew_pool",null);
//			else if (sBefehl.equals("write line hold"))
//			{
//				if (A.fos == null)
//				{
//                                  A.bIO_Error=false;
//					try
//					{
//						A.fos=new java.io.FileOutputStream(A.sReg,(A.TabAbfragen.getI("bits") & Abfrage.cstAnhaengen)>0);
//					}
//					catch (IOException ex)
//					{
//                                          A.bIO_Error=true;
//                                          A.printError(MPos()+"write line hold - IOException - "+ex);
//					}
//				}
//				TabAbfrage.CalcExport(false,false,getTrennzeichnen(),A.fos,A.TabSpalten,false);
//			}
			else if (sBefehl.equals("close file"))
			{
				if (A.fos != null)
				{
					try
					{
						A.fos.close();
					}
					catch (IOException ex)
					{
						A.printError(MPos()+"close file - IOException - "+ex,400);
					}
					A.fos=null;
				}
			}
            else if (sBefehl.equals("write line2")) // reserve55
            {
              try {
                A.fos.write((A.sReg + "\r\n").getBytes());
              }
              catch(IOException ex) {
                A.bIO_Error = true;
                            A.printError(MPos()+"write line2 - IOException - " + ex,400);
              }
            }
            else
              RunError();
                          //A.printError("Calc.RunSpeichern(): Die Speichern-Funktion >"+sBefehl+"< steht noch nicht zur Verfügung");
		//}
	}
	
	private double diff(Object oVon,Object oBis) 
	{
		DateWOD DW_von=oVon instanceof DateWOD ? (DateWOD)oVon:null;
		DateWOD DW_bis=oBis instanceof DateWOD ? (DateWOD)oBis:null;
		if (DW_von!=null && DW_von.hasZone() && DW_bis!=null && DW_bis.hasZone())
			return Sort.getf(oBis)-Sort.getf(oVon)+(DW_von.getZone2()-DW_bis.getZone2())*60;
		else
			return Sort.getf(oBis)-Sort.getf(oVon);
	}

    private String getTrennzeichnen()
    {
      return A.sTrenn != null ? A.sTrenn:A.StaString.empty()?A.bReg?",":";":A.StaString.pop();
    }

	private void RunError()
	{
          //A.g.progInfo("RunError");
          if (sBefehl.equals("set error"))
         {
            A.bFehler=true;
            if (Static.bWeb && A.iMsgArt < 3)
			{
				A.iMsgArt=3;
				A.sInfo=getM();
        		        A.sHeader="Modellfehler";
			} 
          }
          else if (sBefehl.equals("error message"))
		{
		  String sFehler=getMemo();
          A.g.printError("Modellfehlermeldung: "+sFehler,A.iHauptModell);
          //if (!Static.bWeb)
        	  showMessage(false,false,20,"Modellfehler");
		}
		else if (sBefehl.equals("error message2"))
		{
			//Static.printError("Modellfehlermeldung: "+sEingabe);
			boolean bError=!bAlle;
			if (bError)
				A.g.printError(sEingabe,A.iHauptModell);
			String sBG=getBG("error message2","Fehlermeldung");
//	    	int iTZ=sEingabe.indexOf(':');
//	    	if (iTZ>0)
//	    	{
//	    		sBG=sEingabe.substring(0, iTZ);
//	    		sEingabe=sEingabe.substring(iTZ+1);
//	    		A.g.fixtestError("BG="+sBG+", Eingabe="+sEingabe);
//	    	}
			if (Static.bWeb)
			{
				
				Vector<String> Vec=A.g.getTranslate(sBG,sEingabe,new String[] {getMemo()},false);
				A.iMsgArt=bError ? 3:2;
				A.sInfo=Sort.gets(Vec, 1);
        		A.sHeader=Sort.gets(Vec, 0);
        		if (bError)
        			A.printError(A.sInfo,0);
			}
			else if (!Static.bWeb && !A.bTimer && A.in == null)
			{
				Message Msg2=new Message(Message.WARNING_MESSAGE,A.FomA,A.g,20);
				Msg2.sGruppe=sBG;
				Msg2.showDialog(sEingabe);
			}
            else if (!Static.bWeb && A.in != null)
              AClient.sendInfo(false,true,sEingabe,A.out);
		}
		else if (sBefehl.equals("error exit"))
			A.printError(getM(),0);
		else if (sBefehl.equals("error mail"))
		{
            A.g.fixtestInfo("[error mail] wird nicht mehr verwendet!");
		}
		else
                  RunTerminal();
			//A.printError("Calc.RunError(): Die Fehlerbehandlung >"+sBefehl+"< steht noch nicht zur Verfügung");
	}

	private void RunTerminal()
	{
          //A.g.progInfo("RunTerminal");
		if (sBefehl.equals("read Terminalhits"))
			TabTerminal.Read(A.sReg,TabAbfrage,null,A.g);
		else if (sBefehl.equals("read Terminalfiles"))
			TabTerminal.Read(A.sReg,TabAbfrage,A.dtReg.toTimestamp(),A.g);
		else if (sBefehl.equals("set Terminalstamm"))
			TabTerminal.StammUpload(A.sReg,TabAbfrage,A.g,true);
                else if (sBefehl.equals("set Terminalstamm2"))
			TabTerminal.StammUpload(A.sReg,TabAbfrage,A.g,false);
		else if (sBefehl.equals("set Terminalenter"))
			TabTerminal.ZeitZutrittUpload(A.sReg,TabAbfrage,A.g,true);
                else if (sBefehl.equals("set Terminalopen"))
			TabTerminal.ZeitZutrittUpload(A.sReg,TabAbfrage,A.g,false);
                else if (sBefehl.equals("set Terminalholiday"))
                        TabTerminal.HolidayUpload(A.sReg,A.g);
		else if (sBefehl.equals("Terminalquitierung"))
			TabTerminal.Quittieren(TabAbfrage.getI(iBew==0?"aic_stamm":"aic_bew_pool"),A.getProt(),A.g);
                else if (sBefehl.equals("correct import"))
                {
                  A.g.exec("update zwischenspeicher set aic_mandant="+A.g.getMandant()+" where pro_aic_protokoll is null and aic_mandant is null and kennung='Import'");
                  A.g.exec("update zwischenspeicher set Zone="+A.g.getZone()+" where pro_aic_protokoll is null and Zone is null and kennung='Import'");
                }
                else if (sBefehl.equals("convert mecs"))
                  checkMecs();
		else
			RunFTP();//RunOLE();
			//A.printError("Calc.RunTerminal(): Der Terminalbefehl >"+sBefehl+"< steht noch nicht zur Verfügung");
	}

	private void RunOLE()
	{
          //A.g.progInfo("RunOLE");
		if (sBefehl.equals("OLE open"))
			A.Ole=new OLEopen(A.g);
		else if (sBefehl.equals("OLE open dot"))
			A.Ole.openDot(bSpalte ? TabAbfrage.getS(sSpalte) : A.sReg);
		else if (sBefehl.equals("OLE one row"))
			A.Ole.OLEoneRow(A.TabSpalten,TabAbfrage);
		else if (sBefehl.equals("OLE all rows"))
			A.Ole.OLEallRow(A.TabSpalten,TabAbfrage);
		else if (sBefehl.equals("OLE table"))
			A.Ole.OLEtable(A.TabAbfragen.getS("Bezeichnung"),A.TabSpalten,TabAbfrage,A.TabAbfragen.getL("bits"));
		else if (sBefehl.equals("OLE print"))
			A.Ole.OLEprint();
		else if (sBefehl.equals("OLE print2"))
			A.Ole.OLEprint(true,bSpalte ? TabAbfrage.getI(sSpalte):getI());
		else if (sBefehl.equals("OLE save"))
		{
			A.Ole.OLEsave(bSpalte ? TabAbfrage.getS(sSpalte) : A.sReg);
		}
		else if (sBefehl.equals("OLE quit"))
		{
			A.Ole.OLEquit();
		}
		else if (sBefehl.equals("OLE close"))
		{
			A.Ole.OLEclose();
			A.Ole=null;
		}
		else
            A.printError(MPos()+"Der "+sGruppe+"-Befehl >"+sBefehl+"< steht nicht zur Verfügung",501);
			//A.printError("Calc.RunOLE(): Der OLE-Befehl >"+sBefehl+"< steht nicht zur Verfügung");
	}

	private void RunFTP()
	{
          //A.g.progInfo("RunFTP");
		if (sBefehl.equals("FTP open"))
			A.ftp=new FTP(A.g,A.sReg,A.StaString);
		else if (sBefehl.equals("FTP open file"))
			A.ftp.open(bSpalte ? TabAbfrage.getS(sSpalte) : A.sReg,false);
		else if (sBefehl.equals("FTP write table"))
		{
			if (!A.ftp.fehler())
				TabAbfrage.CalcExport(false,true,getTrennzeichnen(),A.ftp.out,A.TabSpalten,false);
		}
		else if (sBefehl.equals("FTP write line"))
		{
			if (!A.ftp.fehler())
				TabAbfrage.CalcExport(false,false,getTrennzeichnen(),A.ftp.out,A.TabSpalten,false);
		}
		else if (sBefehl.equals("FTP read table"))
		//	A.ftp.getFile(A.sReg2,A.sReg);
			A.ftp.getFile(A.sReg,A.TabSpalten,TabAbfrage,getTrennzeichnen());
		else if (sBefehl.equals("FTP close file"))
			A.ftp.close();
		else if (sBefehl.equals("FTP close"))
		{
			A.ftp.quit();
			A.ftp=null;
		}
		else
                  RunEMail();
			//A.printError("Calc.RunFTP(): Der FTP-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
	}

	private void RunEMail()
	{
          //A.g.progInfo("RunEMail");
		if (sBefehl.equals("SMTP open"))
			A.smtp=new SMTP2(A.g,A.StaString);//A.sReg,bSpalte ? TabAbfrage.getM(sSpalte) : A.sReg2,A.sReg2);
		else if (sBefehl.equals("SMTP sendTo"))
			A.smtp.sendTo(A.StaString);//bSpalte ? TabAbfrage.getM(sSpalte) : A.sReg,A.sReg);
		else if (sBefehl.equals("SMTP CC"))
			A.smtp.sendCC(A.StaString);//bSpalte ? TabAbfrage.getM(sSpalte) : A.sReg,A.sReg);
		else if (sBefehl.equals("SMTP BCC"))
			A.smtp.sendBCC(A.StaString);//bSpalte ? TabAbfrage.getM(sSpalte) : A.sReg,A.sReg);
		else if (sBefehl.equals("SMTP subject"))
			A.smtp.subject(getM0());
		else if (sBefehl.equals("SMTP sendText"))
			A.smtp.sendText(getM0());
		else if (sBefehl.equals("SMTP attach"))
			A.smtp.attach(getM0());
		else if (sBefehl.equals("SMTP close"))
		{
			A.smtp.quit();
			A.smtp=null;
		}
                else
                  RunLight();
			//A.printError("Calc.RunEMail(): Der E-Mail-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
	}

        private void RunLight()
	{
          //A.g.progInfo("RunLight");
        	//TODO: auf SoR umstellen
          if (A.TabSpalten.getS("Datentyp").equals("BewStamm") && sBefehl.endsWith(" light"))
          {
        	  if (TabAbfrage.out())
        		  A.printError("Calc.RunLight: "+sBefehl+" ohne Zeile nicht durchführbar",400);
        	  else
        		  A.save(sSpalte,new Integer(sBefehl.equals("red light")?A.g.iLight_Red:sBefehl.equals("yellow light")?A.g.iLight_Yellow:
                                       sBefehl.equals("green light")?A.g.iLight_Green:sBefehl.equals("clear light")?A.g.iLight_Off:-1));
          }
          else if (sBefehl.equals("clear light"))
            A.g.setAmpel(A.TabSpalten.getI("Eig"),A.iQryReg,Ampel.cstOff);
          else if (sBefehl.equals("red light"))
            A.g.setAmpel(A.TabSpalten.getI("Eig"),A.iQryReg,Ampel.cstRed);
          else if (sBefehl.equals("yellow light"))
            A.g.setAmpel(A.TabSpalten.getI("Eig"),A.iQryReg,Ampel.cstYellow);
          else if (sBefehl.equals("green light"))
            A.g.setAmpel(A.TabSpalten.getI("Eig"),A.iQryReg,Ampel.cstGreen);
          else
            RunGauge();
            //A.printError("Calc.RunLight(): Der Ampel-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
        }

        private void RunGauge()
	{
          //A.g.progInfo("RunGauge");
          if (sBefehl.equals("Gauge open"))
          {
            int i=getI();//(int)getF2();
            if (i<1) i=1000;
            if (A.in == null)
            {
              A.bGau=true;
              if(Static.bWeb && A.iThread>=0)
                A.g.maxThread(A.iThread,i);
              else if(A.bThread)
                A.g.maxThread(i);
              else if (Static.bX11)// && A.FomFX==null)
                A.Gau = new Gauge(A.sReg, 0, i, "", A.g, false);
              else
              {
            	A.bGau=false;
                A.Gau = null;
              }
            }
            else
              AClient.GaugeOpen(A.sReg,i,false,A.out,A.g);
          }
          else if (sBefehl.equals("Gauge open2"))
          {
            if (A.in == null)
            {
              A.bGau=true;
              if(Static.bWeb && A.iThread>=0)
                A.g.maxThread(A.iThread,(int)A.dReg);
              else if(A.bThread)
                A.g.maxThread((int)A.dReg);
              else if (Static.bX11)// && A.FomFX==null)
                A.Gau = new Gauge(A.sReg, 0, (int)A.dReg, "", A.g, true);
              else
              {
              	A.bGau=false;
                A.Gau = null;
              }
            }
            else
              AClient.GaugeOpen(A.sReg,(int)A.dReg,true,A.out,A.g);
          }
          else if (sBefehl.equals("Gauge add"))
          {
            if (A.in == null)
            {
              if(Static.bWeb && A.iThread>=0) A.g.momThread(A.iThread,-2,getM3());
              else if (A.bThread) A.g.momThread(-2,getM3());
              else if (A.Gau != null) A.Gau.setText(getM3());
            }
            else
              AClient.GaugeMom(getM3(),-2,A.out,A.g);
          }
          else if (sBefehl.equals("Gauge set pos"))
          {
            if (A.in == null)
            {
              if(Static.bWeb && A.iThread>=0) A.g.momThread(A.iThread,TabAbfrage.getPos(),TabAbfrage.getS(A.TabSpalten.getS("Kennung3")));
              else if(A.bThread) A.g.momThread(TabAbfrage.getPos(),TabAbfrage.getS(A.TabSpalten.getS("Kennung3")));
              else if (A.Gau != null) A.Gau.setText(TabAbfrage.getS(A.TabSpalten.getS("Kennung3")),TabAbfrage.getPos());
            }
            else
              AClient.GaugeMom(TabAbfrage.getS(A.TabSpalten.getS("Kennung3")),TabAbfrage.getPos(),A.out,A.g);
          }
          else if (sBefehl.equals("Gauge close"))
            GaugeClose();
          else if (sBefehl.equals("sleep")) // 9.2. von 1s auf 100ms pro Wert geändert
          {
        	if ((A.iDBits&KSL)>0)
        	  A.debugInfo(" sleep "+(getI()*100)+" ms deaktiviert");
        	else
              Static.sleep(getI()*100);
          }
          else if (A.Gau == null && (sBefehl.equals("Gauge add") || sBefehl.equals("Gauge set pos") || sBefehl.equals("Gauge close")))
          {  if (!A.bThread) A.printInfo2("Der Befehl "+sBefehl+" wurde ohne geöffnete Gauge durchgeführt"); }
          else
            RunAuthorisation();
            //A.printError("Calc.RunGauge(): Der Gauge-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
        }

        private void GaugeClose()
        {
          if (A.in == null)
          {
            if (A.Gau != null)
            {
              A.Gau.setText("", A.Gau.getMax());
              A.Gau.free();
              A.bGau = false;
              A.Gau = null;
            }
          }
          else if (A.in != null)
              AClient.GaugeClose(A.out,A.g);
        }

        private void RunZIP()
	{
          if (sBefehl.equals("zip create"))
            A.Zip = new AUZip(getM0()/*bSpalte ? TabAbfrage.getS(sSpalte) : A.sReg*/,false);
          else if (sBefehl.equals("zip write lizenz"))
            SaveLizenzVersion();
          else if (sBefehl.equals("zip create file"))
            A.Zip.putNextEntry(new ZipEntry(getM0()));//bSpalte ? TabAbfrage.getS(sSpalte) : A.sReg));
          else if (sBefehl.equals("zip save string"))
            A.Zip.SaveString2(getM0());//bSpalte ? TabAbfrage.getS(sSpalte) : A.sReg);
          else if (sBefehl.equals("zip save number"))
            A.Zip.SaveInteger(new Integer(getI0()));//TabAbfrage.getI(sSpalte)));
          else if (sBefehl.equals("zip close file"))
            A.Zip.closeEntry();
          else if (sBefehl.equals("zip close"))
          {
            A.Zip.close();
            A.Zip=null;
          }
          else
            RunXML();
            //A.printError("Calc.RunZIP(): Der ZIP-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
        }

        private void RunXML()
	{
          //A.g.progInfo("RunXML");
          if (sBefehl.equals("xml create"))
            A.xml=new AUxml(A.sReg,sEingabe);
          else if (sBefehl.equals("xml addTitel"))
            A.xml.addTitel(sEingabe != null ? sEingabe:A.TabSpalten.getS("Bezeichnung"));
          else if (sBefehl.equals("xml okTitel"))
            A.xml.okTitel();
          else if (sBefehl.equals("xml addTitel2"))
            A.xml.addTitel2(A.TabSpalten.getS("Bezeichnung"),TabAbfrage.getS(sSpalte));
          else if (sBefehl.equals("xml addTitel3"))
            A.xml.addTitel3(sEingabe,A.sReg);
          else if (sBefehl.equals("xml Zeile"))
            A.xml.xml_Zeile(sEingabe);
          else if (sBefehl.equals("xml set"))
            A.xml.xml_Set(sEingabe,A.sReg);
          else if (sBefehl.equals("xml double"))
            A.xml.xml_double(sEingabe,bSpalte ? TabAbfrage.getF(sSpalte):A.dReg);
          else if (sBefehl.equals("xml string"))
            A.xml.xml_String(sVar!=null ? sVar:sEingabe != null ? sEingabe:bSpalte ? A.TabSpalten.getS("Bezeichnung"):"Hugo",sVar!=null && sEingabe!=null ? sEingabe:bSpalte ? TabAbfrage.getS(sSpalte):A.sReg);
          else if (sBefehl.equals("xml date"))
            A.xml.xml_Date(A.TabSpalten.getS("Bezeichnung"),A.TabSpalten.getS("Format"),TabAbfrage.getDate(sSpalte));
          else if (sBefehl.equals("xml time"))
            A.xml.xml_Time(A.TabSpalten.getS("Bezeichnung"),A.TabSpalten.getS("Format"),TabAbfrage.getTimestamp(sSpalte));
          else if (sBefehl.equals("xml int"))
            A.xml.xml_Int(A.TabSpalten.getS("Bezeichnung"),TabAbfrage.getI(sSpalte));
          else if (sBefehl.equals("xml KZ"))
            A.xml.KZ(A.TabSpalten.getS("Bezeichnung"),TabAbfrage.getF(sSpalte));
          else if (sBefehl.equals("xml Text"))
            A.xml.xml_Text(A.sReg);
          else if (sBefehl.equals("xml close"))
          {
            A.xml.free();
            A.xml=null;
          }
          else
            RunZone();
            //A.printError("Calc.RunXML(): Der XML-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
        }

        private void ReadQuery(Tabellenspeicher Tab2,String s)
        {
          Tabellenspeicher Tab=new Tabellenspeicher(A.g,s,true);
          Tab2.clearAll();
          int iAbfrage=A.TabSpalten.getI("Abfrage");
          for (Tab.moveFirst();!Tab.out();Tab.moveNext())
          {
            Tab2.newLine();
            A.TabSpalten.posInhalt("Abfrage",iAbfrage);
            for(int iCol=0;iCol<Tab.getAnzahlSpalten() && A.TabSpalten.getI("Abfrage")==iAbfrage;iCol++)
            {
              Tab2.setInhalt(A.TabSpalten.getS("Kennung"),Tab.getElementAt(Tab.getPos(),iCol));
              A.TabSpalten.moveNext();
            }
          }
        }

        private void RunSQL()
        {
          if (sBefehl.equals("SQL_getString"))
            A.sReg=SQL.getString(A.gF==null ? A.g:A.gF,getM());
          else if (sBefehl.equals("SQL_getAic"))
            A.iReg=SQL.getInteger(A.gF==null ? A.g:A.gF,getM());
          else if (sBefehl.equals("exec"))
            A.dReg=(A.gF==null ? A.g:A.gF).exec(getM());
          else if (sBefehl.equals("get Query"))
            ReadQuery(TabAbfrage,getM1());
          else if (sBefehl.equals("reserve121")) // QryNew
          {
        	  if (!A.StaString.isEmpty())
        	  {
	        	  String sPW=""+A.StaString.pop();
	              String sName=""+A.StaString.pop();
	              String sJDBC=""+A.StaString.pop();
	              A.gF=new Transact();
	              A.gF.connect3(sJDBC,sName,sPW);
        	  }
        	A.QryF=new SQL(A.gF==null ? A.g:A.gF);  
          }
          else if (sBefehl.equals("reserve122")) // QryFree
          {
        	  if (A.QryF!=null) A.QryF.free();
        	  if (A.gF!=null)  A.gF.disconnect();
        	  A.QryF=null;
        	  A.gF=null;
          }
          else if (sBefehl.equals("reserve123")) //QryAddI/Spalte
        	  A.QryF.add(sEingabe==null ? A.TabSpalten.getS("Bezeichnung"):sEingabe,bSpalte ? TabAbfrage.getInhalt(sSpalte):A.dReg);
          else if (sBefehl.equals("reserve124")) //QryAddDt/Spalte2
        	  A.QryF.add(sEingabe==null ? A.TabSpalten.getS("Bezeichnung"):sEingabe,bSpalte ? TabAbfrage.getInhalt(A.TabSpalten.isNull("Kennung2") ? sSpalte:A.TabSpalten.getS("Kennung2")):A.dtReg==null ? null:A.dtReg.getTime());
          else if (sBefehl.equals("reserve125")) //QryAddS/Spalte3
        	  A.QryF.add(bVar ? sVar:sEingabe==null ? A.TabSpalten.getS("Bezeichnung"):sEingabe,bVar && sEingabe!=null ? sEingabe:bSpalte ? TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung3")):A.sReg);
          else if (sBefehl.equals("reserve126")) //QryInsert
        	  A.QryF.insert(getM0(),false);
          else if (sBefehl.equals("reserve127")) //QryWhere
        	  A.QryF.addWhere(bVar ? sVar:sEingabe,bVar && sEingabe!=null? sEingabe:bSpalte ? TabAbfrage.getS(sSpalte):A.sReg);
          else if (sBefehl.equals("reserve128")) //QryUpdate
        	  A.dReg=A.QryF.update(getM0());
          else
            RunReserve();
            //A.printError("Calc.RunSQL(): Der SQL-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
        }

        private void RunTransaction()
        {
          if (sBefehl.equals("start transaction"))
          {
        	  A.g.fixInfo("*** start transaction ***");
            A.g.start();
          }
          else if (sBefehl.equals("commit"))
          {
        	  A.g.fixInfo("*** commit ***");
        	  A.g.commit();
          }
          else if (sBefehl.equals("rollback"))
          {
        	  A.g.fixInfo("*** rollback ***");
            A.g.rollback();
          }
          else
            RunSQL();
            //A.printError("Calc.RunTransaction(): Der Transaction-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
        }

        private void RunZone()
        {
          if (sBefehl.equals("add zone"))
            A.dtReg.setAllSeconds(A.dtReg.getAllSeconds()+TabAbfrage.getI("Zone")*60);
          else if (sBefehl.equals("subtract zone"))
            A.dtReg.setAllSeconds(A.dtReg.getAllSeconds()-TabAbfrage.getI("Zone")*60);
          else if (sBefehl.equals("get zone"))
            A.iZone=bSpalte ? TabAbfrage.getI("Zone"):sEingabe != null ? Sort.geti(sEingabe):A.dtReg.getZone2();
          else if (sBefehl.equals("init zone"))
            A.iZone=A.g.getZone();
          else if (sBefehl.equals("set utc"))
            A.iZone=0;
          else if (sBefehl.equals("Befehl161")) // get Date-Zone
  		  {
  			A.iZone=A.dtReg.getAZone();
//  			A.g.fixtestError("Zone="+A.iZone);
  			A.bZone=true;
  		  }
          else if (sBefehl.equals("Befehl162")) // set zone
          {
        	  int iZ=sEingabe==null ? A.iZone:Sort.geti(sEingabe);
        	  if (bSpalte)
        		  if (iZ==-1)
        			  TabAbfrage.setInhalt("Zone",null);
        		  else
        			  TabAbfrage.setInhalt("Zone", iZ);
        	  else
        		  A.dtReg.setZone(iZ);
          }
          else
            RunIntusCom();
            //A.printError("Calc.RunZone(): Der Zone-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
        }

        private void getMotions(String sSpalte,int iAic)
        {
          TabAbfrage.clearAll();
          Tabellenspeicher Tab=new Tabellenspeicher(A.g,"select distinct benutzer.aic_stamm,sta_aic_stamm,benutzer.aic_benutzer from benutzer"+A.g.join2("benutzer_zuordnung","benutzer")+
                                                    " where "+(bAlle ? "":"benutzer.aic_stamm is not null and ")+"geloescht is null and (use_bis is null or use_bis>"+A.g.now()+") and sta_aic_stamm"+(iAic>0 ? "="+iAic:" is not null"),true);
          for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            TabAbfrage.addInhalt("aic_stamm", Tab.getI("aic_stamm"));
            TabAbfrage.addInhalt(sSpalte, Tab.getI("sta_aic_stamm"));
            if (TabAbfrage.getAnzahlSpalten()>2)
              TabAbfrage.addInhalt(TabAbfrage.getSpalten().elementAt(2), Tab.getI("aic_benutzer"));
          }
        }

        /*private String getRolle(int iRolle)
        {
          return iRolle>0 ? " and aic_rolle="+iRolle:" and aic_rolle is null";
        }*/

        private java.util.Stack getPersons(int iUser,int iNRK)
        {
          Tabellenspeicher Tab=new Tabellenspeicher(A.g,"select distinct benutzer_zuordnung.AIC_ABFRAGE from benutzer"+A.g.join2("benutzer_zuordnung","benutzer")+" where sta_aic_stamm="+iNRK+" and benutzer.aic_stamm="+iUser,true);
          // A.g.fixtestError("getPersons:"+Tab.getSQL());
          Stack<Object> Vec2=new Stack<Object>();
          for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            Abfrage A1=new Abfrage(A.g,Tab.getI("aic_abfrage"),Abfrage.cstHS_Filter);
            if (A1.iStt==A.g.iSttANR)
            {
              A1.iVB=A.iVB;
              Vector VecEigenschaften=new Vector();
              String sCheck=A1.Check(VecEigenschaften,A1.NodBed.getChildren(),"",false);
              Vector<Integer> Vec=SQL.getVector("select aic_stamm from (Select "+A1.ZusaetzlicheSpalten(VecEigenschaften,Formular.Stamm,true)+" from "+
              ((A1.iBits&Abfrage.cstKeinStammZeitraum)>0?"stammview2":"Stammview"+A.ViewNr())+" p2 where aic_stammtyp="+A1.iStt+Abfrage.Rolle(A1.iRolle)+A1.Ebenen()+A1.checkJoker(sCheck,A.iHauptModell),A.g);
              A.g.fixInfoT("getPersons: bei Stt="+A1.iStt+"="+Vec);
              for(int i=0;i<Vec.size();i++)
              {
                Object Obj=Vec.elementAt(i);
                if (Obj != null && !Vec2.contains(Obj))
                  Vec2.push(Obj);
              }
            }
          }
          VecAicToString("nach getPersons",Vec2);
          //A.g.progInfo("alle="+Vec2);
          return Vec2;
        }

        private int getDeputy(int iUser,int iNRK)
        {
          return SQL.getInteger(A.g,"select distinct benutzer_zuordnung.aic_stamm from benutzer"+A.g.join2("benutzer_zuordnung","benutzer")+" where sta_aic_stamm="+iNRK+" and benutzer.aic_stamm="+iUser);
        }

        @SuppressWarnings("unchecked")
        private void RunAuthorisation()
        {
          if (sBefehl.equals("get motions"))
            getMotions(sSpalte,0);
          else if (sBefehl.equals("get persons"))
            A.VecAic=getPersons(bSpalte ? TabAbfrage.getI("aic_stamm"):A.iQryReg,bSpalte ? TabAbfrage.getI(sSpalte):12238);
          else if (sBefehl.equals("get deputy"))
            A.iReg=getDeputy(TabAbfrage.getI("aic_stamm"),TabAbfrage.getI(sSpalte));
          else if (sBefehl.equals("get selfchange"))
                {
                  VecAicToString("vor get selfchange",A.VecAic);
                  String s="select distinct s.aic_stamm from stammpool s join protokoll p on s.aic_protokoll=p.aic_protokoll"+
                    (bAlle ? " where":A.g.join("logging","p")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer where benutzer.aic_stamm=s.aic_stamm and")+
                		  " timestamp>="+A.g.von()+" and timestamp<"+A.g.bis()+" and "+A.g.in("s.aic_stamm",A.VecAic);
                  //A.g.progInfo(s);
                  Vector Vec=SQL.getVector(s,A.g);
                  s="select distinct s.aic_stamm from stammpool s2 join stammpool s on s2.aic_stamm=s.sta_aic_stamm join protokoll p on s2.aic_protokoll=p.aic_protokoll"+
                		  (bAlle ? " where":A.g.join("logging","p")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer where benutzer.aic_stamm=s.aic_stamm and")+
                		  " timestamp>="+A.g.von()+" and timestamp<"+A.g.bis()+" and "+A.g.in("s.aic_stamm",A.VecAic);
                  SQL.addVector(Vec,s,A.g);
                  VecAicToString("nach get selfchange",Vec);
                  A.VecAic=new java.util.Stack<Integer>();
                  for (int i=0;i<Vec.size();i++)
                    A.VecAic.push(Sort.getInt(Vec,i));
                }
          else if (sBefehl.equals("get user-stamm"))
            A.iQryReg=bSpalte ? SQL.getInteger(A.g,"select aic_stamm from benutzer where aic_benutzer="+TabAbfrage.getI(sSpalte)):A.g.getStamm();
          else if (sBefehl.equals("Befehl191")) // get User-E-Mail/Tel
            ReadQuery(TabAbfrage,"select aic_stamm,"+A.g.AU_BezeichnungF("Benutzer")+"bezeichnung,kennung,e_mail,tel,aic_benutzer from Benutzer where aic_benutzer="+A.iReg);
          else if (sBefehl.equals("Befehl192")) // print exec
        	  A.g.printExec(MPos()+": "+sEingabe);
          else
            RunZIP();
            //A.printError("Calc.RunAuthorisation(): Der Berechtigungs-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
        }

        private void ImportiereWaehrung()
        {
          Global t=new Global();
          Date dt=SQL.getNow(A.g);
          SQL Qry=new SQL(A.g);
          String sPW=""+A.StaString.pop();
          String sName=""+A.StaString.pop();
          String sTab=""+A.StaString.pop();
          String sJDBC=""+A.StaString.pop();
          t.connect3(sJDBC,sName,sPW);
          Tabellenspeicher Tab2=new Tabellenspeicher(t,"select * from "+sTab,true);
          for(Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
          {
            double d=Tab2.getF("FXRATE");
            int iAic = 0;
            if(Tab2.getS("CURRENCYFROMID").equals("EUR"))
            {
              int iPos=A.g.TabWaehrung.getPos("Kennung", Tab2.getS("CURRENCYTOID"));
              if(iPos>=0)
                iAic = A.g.TabWaehrung.getI(iPos,"aic_stamm");
              if(iAic > 0)
              {
                DateWOD DW = new DateWOD(dt);
                DW.setTimeZero();
                Tabellenspeicher Tab = new Tabellenspeicher(A.g, "select * from ww where aic_stamm=" + iAic + " order by Datum", true);
                if(Tab.isEmpty())
                  for(int iWW = 0; iWW < 366; iWW++)
                  {
                    String sDatum = A.g.DateTimeToString(DW.toDate());
                    Qry.add2("Datum", sDatum);
                    Qry.add("aic_stamm", iAic);
                    Qry.add("Faktor", d);
                    Qry.add("BITS", iWW == 0 ? 1 : 0);
                    Qry.insert("WW", false);
                    DW.tomorrow();
                  }
                else
                {
                  boolean bWeiter = true;
                  for(int iWW = 0; bWeiter && iWW < 366; iWW++)
                  {
                    String sDatum = A.g.DateTimeToString(DW.toDate());
                    int iAicWW = Tab.posInhalt("Datum", DW.toTimestamp()) ? Tab.getI("AIC_WW") : 0;
                    if(iWW == 0 || iAicWW == 0 || (Tab.getI("Bits") & 1) == 0)
                    {
                      Qry.add2("Datum", sDatum);
                      Qry.add("aic_stamm", iAic);
                      Qry.add("Faktor", d);
                      Qry.add("BITS", iWW == 0 ? 1 : 0);
                      if(iAicWW > 0)
                        Qry.update("WW", iAicWW);
                      else
                        Qry.insert("WW", false);
                    }
                    else
                      bWeiter = false;
                    DW.tomorrow();
                  }
                }
                A.g.fixInfo(Tab2.getS("CURRENCYFROMID") + " -> " + Tab2.getS("CURRENCYTOID") + "/" + iAic + " : " + d);
              }
            }
          }
          Qry.free();
          t.disconnect();
        }

    private void getChanges2()
    {
      int iP = A.getProt();
      A.g.fixInfo("get changes2: iProt=" + iP);
      Vector Vec = SQL.getVector("select aic_eigenschaft from stammpool where aic_protokoll=" + iP, A.g);
      A.g.fixInfo("Vec=" + Vec);
      A.TabSpalten.push();
      boolean bChange=false;
      for(A.TabSpalten.posInhalt("Abfrage",TabBefehle.getI("Aic_Abfrage"));!A.TabSpalten.eof()&&A.TabSpalten.getI("Abfrage")==TabBefehle.getI("Aic_Abfrage");A.TabSpalten.moveNext())
      {
        String sK=A.TabSpalten.getS("Kennung").substring(1);
        if (!Vec.contains(new Integer(sK)))
        {
          TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung"),null);
          if (!A.TabSpalten.isNull("Kennung2"))
            TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung2"),null);
          if (!A.TabSpalten.isNull("Kennung3"))
            TabAbfrage.setInhalt(A.TabSpalten.getS("Kennung3"),null);
        }
        else
          bChange=true;
      }
      if (!bChange)
        TabAbfrage.clearInhalt();
      A.TabSpalten.pop();
    }

    private void clearEmpty()
    {
      A.TabSpalten.push();
      boolean bNull=true;
      for (TabAbfrage.moveFirst();!TabAbfrage.out();)
      {
        for (A.TabSpalten.posInhalt("Abfrage", TabBefehle.getI("Aic_Abfrage")); !A.TabSpalten.eof() && A.TabSpalten.getI("Abfrage") == TabBefehle.getI("Aic_Abfrage");A.TabSpalten.moveNext())
          if (!TabAbfrage.isNull(A.TabSpalten.getS("Kennung")))
            bNull=false;
        if (bNull)
          TabAbfrage.clearInhalt();
        else
          TabAbfrage.moveNext();
        bNull=true;
      }
      A.TabSpalten.pop();
      TabAbfrage.moveFirst();
    }

    private void checkMecs()
    {
      Tabellenspeicher Tab=new Tabellenspeicher(A.g,"select * from zwischenspeicher where kennung in ('Mecs','PHP') and pro_aic_protokoll is null"+A.g.getReadMandanten(false,"zwischenspeicher")+" order by aic_zwischenspeicher",true);
      if (!Tab.isEmpty())
      {
        Parameter Para = new Parameter(A.g,"Terminal");
        Para.getMParameterH("Werte");
        //boolean bHitag = Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.HITAG;
        //boolean bLegic = Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.LEGIC;
        boolean bMiro = Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.MIRO;
//        boolean bRo=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.RO;
//        boolean bSymbol=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.SYMBOL;
//        boolean bMifare=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARE;
//        boolean bMifare2=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARE2;
        int iInt1=Para.bGefunden ? Para.int1:-1;
        Para.free();
        int iProt=A.getProt();//A.g.Protokoll("Terminal",0/*unknown*/);
        SQL Qry=new SQL(A.g);
        for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
        {
          boolean bPHP=Tab.getS("Kennung").equals("PHP");
          String s=Tab.getS("Zwischentext");
          String sBDE=null;
          if (bPHP && s.indexOf(";")>0)
          {
            int iPos=s.indexOf(";");
            sBDE=s.substring(iPos);
            s=s.substring(0,iPos);
          }
          int iIndex=bPHP ? s.indexOf(","):0;
          String sKarte=bPHP ?s.substring(0,iIndex):s.substring(19,27);
          String sDate=bPHP ? null:s.substring(5,19);
          String sArt=bPHP ? s.substring(iIndex+1):s.substring(3,5);
          String sMemo="";
          if (bPHP && sArt.indexOf(",")>0)
          {
            iIndex=sArt.indexOf(",");
            sMemo=sArt.substring(iIndex+1);
            sArt=sArt.substring(0,iIndex);
          }
          sKarte=iInt1==-1 ? "NoWood":bMiro ? Static.MiroCalc(sKarte,true):Static.FillNull(sKarte,/*iInt1==-1 ? 0:*/TabTerminal.Kartenlaenge(false, iInt1));//bSymbol ? 16: bMifare || bMifare2 ? 10: bRo ? 12:8);
          Timestamp ts=bPHP? Tab.getTimestamp("Gueltig"):new DateWOD(new java.text.SimpleDateFormat("yyyyMMddHHmmss").parse(sDate,new java.text.ParsePosition(0))).toTimestamp();
          if (bPHP)
            sDate=new DateWOD(ts.getTime()).Format(A.g,"yyyyMMddHHmmss");
          String s2="J4321]00"+sArt+sKarte+sDate+"0"+(sBDE==null?"":sBDE)+";20\r"+sMemo;
          Qry.add("Aic_protokoll", iProt);
          Qry.add("Kennung", "Import");
          Qry.add("Terminal", bPHP?"PHP":"Kunde");
          Qry.add("Gueltig", ts);
          Qry.add("aic_mandant",Tab.isNull("aic_mandant") ? A.g.getMandant():Tab.getI("aic_mandant"));
          Qry.add("zone",Tab.isNull("zone") ? A.g.getZone():Tab.getI("zone"));
          Qry.add("Zwischentext", s2);
          Qry.insert("Zwischenspeicher", false);
          Qry.exec("update zwischenspeicher set pro_aic_protokoll="+iProt+" where aic_zwischenspeicher="+Tab.getI("aic_zwischenspeicher"));
        }
        Qry.free();
      }
    }

    private void ReadZwischenspeicher(String sKennung,Tabellenspeicher Tab)
    {
      Tabellenspeicher Tab2=new Tabellenspeicher(A.g,"select * from zwischenspeicher where kennung ='"+sKennung+"' and pro_aic_protokoll is null order by aic_zwischenspeicher",true);
      A.TabSpalten.push();
      int iAbfrage=A.TabSpalten.getI("Abfrage");
      for(Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
      {
        String s=Tab2.getS("Zwischentext");
        String[] sSp=s.split(";");
        Tab.newLine();
        A.g.fixInfo("read Temp: Neue Zeile");
        Tab.setInhalt((iBew==0?"aic_stamm":"aic_bew_pool"),Tab2.getI("aic_zwischenspeicher"));
        int i=0;
        for(A.TabSpalten.posInhalt("Abfrage",iAbfrage);!A.TabSpalten.eof()&&A.TabSpalten.getI("Abfrage")==iAbfrage && i<sSp.length;A.TabSpalten.moveNext())
        {
          Tab.setInhalt(A.TabSpalten.getS("Kennung"),sSp[i]);
          A.g.fixInfo("-> schreibe ["+sSp[i]+"] in "+A.TabSpalten.getS("Kennung")+" vom Zwischenspeicher");
          i++;
        }
      }
      A.TabSpalten.pop();
      Tab.moveFirst();
    }

    private void WriteZwischenspeicher(String sKennung,Tabellenspeicher Tab)
    {
      int iProt=A.getProt();
      A.g.exec("update zwischenspeicher set pro_aic_protokoll="+iProt+" where kennung ='"+sKennung+"' and pro_aic_protokoll is null");
      for (int iPos=0;iPos<Tab.size();iPos++)
      {
        SQL Qry = new SQL(A.g);
        Qry.add("aic_protokoll",iProt);
        if (A.dtReg != null)
          Qry.add("gueltig", A.dtReg.toTimestamp());
        Qry.add("Zwischentext",Tab.getS(iPos,sSpalte));
        Qry.add("Kennung",sKennung);
        Qry.add("aic_mandant",A.g.getMandant());
        Qry.add("Zone",A.g.getZone());
        Tab.setInhalt(iBew==0?"aic_stamm":"aic_bew_pool",Qry.insert("zwischenspeicher",true));
      }
      //Tabellenspeicher Tab2=new Tabellenspeicher(A.g,"select * from zwischenspeicher where kennung ='"+sKennung+"' and pro_aic_protokoll is null order by aic_zwischenspeicher",true);
      //Tab2.showGrid("überschreibe "+sKennung);
    }

    private void SaveLizenzVersion()
    {
        A.Zip.putNextEntry(new ZipEntry("Version.up"));
        A.Zip.SaveInteger(new Integer(Version.getVer()));
        A.Zip.SaveInteger(new Integer(Lizenz.cstVersion));
        A.Zip.closeEntry();
    }

    private void RunIntusCom()
    {
      if (sBefehl.equals("create IntusCom"))
        A.IC=new IntusCom(A.StaString,A.g);
      else if (sBefehl.equals("free IntusCom"))
      {
        if (A.IC != null)
          A.IC.free();
      }
      else if (sBefehl.equals("read IntusCom"))
        A.IC.ReadStamps(A.sReg,TabAbfrage);
      else if (sBefehl.equals("set ok IntusCom"))
        A.IC.Quittieren(TabAbfrage.getI("aic_bew_pool"));
      else if (sBefehl.equals("reserve135")) // "clear IntusCom"
          A.IC.ClearStamp(TabAbfrage.getI("aic_bew_pool"));
      else if (sBefehl.equals("upload TI IntusCom"))
	A.IC.UploadTI(TabAbfrage);
      else if (sBefehl.equals("change TI IntusCom"))
	A.IC.ChangeTI(TabAbfrage);
      else if (sBefehl.equals("upload IntusCom"))
        A.IC.StammUpload(TabAbfrage);
      else if (sBefehl.equals("change MR IntusCom"))
        A.IC.ChangeStammUpload(TabAbfrage);
      else if (sBefehl.equals("enter IntusCom"))
        A.IC.ZeitZutritt(TabAbfrage,'Z');
      else if (sBefehl.equals("enter2 IntusCom"))
        A.IC.ZeitZutritt(TabAbfrage,'T');
      else if (sBefehl.equals("booking IntusCom"))
        A.IC.ZeitZutritt(TabAbfrage,'B');
      else if (sBefehl.equals("holiday IntusCom"))
        A.IC.Holiday();
      else if (sBefehl.equals("exec IntusCom")) // reserve116
    	A.IC.exec(getM());
      else
    	  RunJSON();
        //A.printError("Calc.RunIntusCom(): Der IntusCom-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
    }
    
    private void RunJSON()
    {
    	String sE=sEingabe;
    	String sT=null;
    	int iP=sEingabe==null ? -2:sE.indexOf('=');
    	if (iP>0)
    	{
    		sT=sE.substring(iP+1);
    		sE=sE.substring(0, iP);
    	}
    	if ((sBefehl.startsWith("Json") || sBefehl.equals("Befehl174")) && (A.jO==null || sEingabe==null))
    	{
    		if (sEingabe==null)
    			A.printError("Befehl "+sBefehl+" ohne Eingabe nicht möglich", 400);
    		else
    			A.g.fixtestError("Befehl "+sBefehl+" ohne JSON-Object nicht durchführbar");
    	}
    	else if (sBefehl.startsWith("Json") || sBefehl.equals("Befehl174"))
    	{
    	  try {
    		if (sBefehl.equals("JsonDatum"))
	    		A.jO.put(sE, webJSON.DateToJSON(sT==null ? getDo():getDWE(sT).toDate()));
	    	else if (sBefehl.equals("JsonString"))
	    		A.jO.put(sE, sT==null ? getSo():sT);
	    	else if (sBefehl.equals("JsonZahl"))
	    		A.jO.put(sE, sT==null ? getZo():Sort.getf(sT));
	    	else if (sBefehl.equals("JsonAic"))
	    		A.jO.put(sE, sT==null ? getA():getAic(sT));
	    	else if (sBefehl.equals("JsonBoolean") || sBefehl.equals("Befehl174")) //JsonBoolean
	    		A.jO.put(sE, sT!=null ? sT.equals("true"):bVar ? getb(sVar):bSpalte ? TabAbfrage.getB(sSpalte):A.bReg);
	    	else if (sBefehl.equals("JsonMass"))
	    	{
	    		Mass mass=(Mass)ShowAbfrage.doubleToScreen(A.g,bAR ? A.dReg:TabAbfrage.getF(sSpalte),A.TabSpalten,TabAbfrage.getI(A.TabSpalten.getS("Kennung2")));
	    		A.jO.put(sEingabe, mass.toJSON(false));
	    	}
    	  } catch (JSONException e) { A.printError("Befehl "+sBefehl+" erzeugt folgende Exception:"+e, 400); }
    	}
    	else if (sBefehl.equals("ClearJson"))
    		A.jO=new JSONObject();
    	else
            RunStringCommand();
    }

    private void addString(String s)
    {
    	if (bAS)
        {
       	  sSpalte=A.TabSpalten.getS("Kennung3");
       	  TabAbfrage.setInhalt(sSpalte, TabAbfrage.getS(sSpalte)+s);
        }
        else  if (bVar && !bAR)
          set(sVar,gets(sVar)+s);
        else
        {
          //A.g.fixtestInfo("addString: "+A.sReg+"+"+s);
          A.sReg+=s;
        }
    }
    
    private void addString2(String s)
    {
    	boolean bInit=false;
    	String sP=s;
    	if (iC_Art==INIT)// || Static.Leer(A.sReg))
    		bInit=true;
    	else if (iC_Art==CWS)
    		sP=" "+s;
    	
    	if (bAS)
        {
       	  sSpalte=A.TabSpalten.getS("Kennung3");
       	  SoR(bInit || Static.Leer(TabAbfrage.getInhalt(sSpalte)) ? s:TabAbfrage.getS(sSpalte)+sP);   
        }
        else  if (bVar && !bAR)
          set(sVar,bInit || Static.Leer(gets(sVar)) ? s:gets(sVar)+sP);
        else if (bInit || Static.Leer(A.sReg))
        	A.sReg=s;
        else
        	A.sReg+=sP;
    }

    private String getString()
    {
      return bAS ? TabAbfrage.getS(A.TabSpalten.getS("Kennung3")) : bVar && !bAR ? gets(sVar):A.sReg;
    }

    private void setString(String s)
    {
	  if (bAS)
		SoR(s);
	  else if (bVar && !bAR)
        set(sVar,s);
      else
        A.sReg=s;
    }

    private void RunStringCommand()
    {
      //A.g.progInfo("RunStringCommand");
      if (sBefehl.equals("concat"))
        addString2(getM3c());
      else if (sBefehl.equals("concat dep memo"))
      {
        int iSprache=A.g.getSprache();
        if (!bAlle)
        	iSprache=SQL.getInteger(A.g,"select max(aic_sprache) from benutzer where aic_stamm=" + A.iQryReg);
        if (iSprache<1)
          iSprache=Global.iSpStd;//SQL.getInteger(A.g,"select aic_sprache from sprache where standard=1");
        String s=SQL.getString(A.g, "select memo from daten_memo where aic_tabellenname=" + Global.iTabStamm + 
        		" and aic_fremd=" + TabAbfrage.getI(sSpalte) + " and aic_sprache="+iSprache);
        if (Static.Leer(s) && iSprache!=Global.iSpStd)
        	s=SQL.getString(A.g, "select memo from daten_memo where aic_tabellenname=" + Global.iTabStamm + 
            		" and aic_fremd=" + TabAbfrage.getI(sSpalte) + " and aic_sprache="+Global.iSpStd);
        addString2(s);
      }
      else if (sBefehl.equals("concat with space"))
        addString((getString().equals("") || getM3c().equals("")?"":" ")+getM3c());
      else if (sBefehl.equals("reserve119"))
    	  addString((getString().equals("")?"":" ")+TabAbfrage.getI("aic_bew_pool"));
      else if (sBefehl.equals("concat table"))
        addString(TabAbfrage.toString(A.TabSpalten,sEingabe != null && sEingabe.length()>0 ? sEingabe:null));
      else if (sBefehl.equals("Befehl141")) // concat Titelzeile
    	addString(TabAbfrage.getTitelzeile(A.TabSpalten,sEingabe));
      else if (sBefehl.equals("indexOf"))
                  A.dReg=A.sReg.indexOf(getM3b());
      else if (sBefehl.equals("trunc left"))
      {
        String s= bSpalte ? TabAbfrage.getS(A.TabSpalten.getS("Kennung3")):getString();
        int i=getI();
        s = i<0 ? Static.FillSpace("Double",s,-i):s!=null && s.length()>i ? s.substring(i):"";
        setString(s);
      }
      else if (sBefehl.equals("trunc right"))
      {
        String s= bSpalte ? TabAbfrage.getS(A.TabSpalten.getS("Kennung3")):getString();
        int i=getI();
        s = i<0 ? Static.FillSpace("",s,-i):s!=null && s.length()>i ? s.substring(0,s.length() - i):"";
        setString(s);
      }
      else  if (sBefehl.equals("concat filesep"))
        A.sReg=A.sReg.endsWith(File.separator)? A.sReg:A.sReg+File.separator;
      else if (sBefehl.equals("concat show"))
      {
    	String sBG=getBG("concat show","Show");
//    	int iTZ=sEingabe.indexOf(':');
//    	if (iTZ>0)
//    	{
//    		sBG=sEingabe.substring(0, iTZ);
//    		sEingabe=sEingabe.substring(iTZ+1);
//    		A.g.fixtestError("BG="+sBG+", Eingabe="+sEingabe);
//    	}
    	if (sBG != null)
    	{
	    	boolean bMemo=true;
	    	if (sEingabe.startsWith("*"))
	    	{
	    		bMemo=false;
	    		sEingabe=sEingabe.substring(1);
	    	}
	        int iPos=A.g.getPosBegriff(sBG,sEingabe);
	        if (iPos<0)
	          Static.printError(sBG+" "+sEingabe+" nicht gefunden!");
	        else if (bMemo)     
	        {
	        	String s=SQL.getString(A.g, "SELECT Memo FROM Daten_Memo where aic_tabellenname="+Global.iTabBegriff+" and aic_fremd="+A.g.TabBegriffe.getI(iPos,"aic")+" and aic_sprache="+A.g.getSprache());
	        	if (Static.Leer(s))
	        		s=SQL.getString(A.g, "SELECT Memo FROM Daten_Memo where aic_tabellenname="+Global.iTabBegriff+" and aic_fremd="+A.g.TabBegriffe.getI(iPos,"aic")+" and aic_sprache=1");
	        	addString2(s);
	        }
	        else
	          addString2(A.g.getBegBez3(iPos));
    	}
      }
      else if (sBefehl.equals("LineToString"))
            addString(TabAbfrage.LineToString(A.TabSpalten));
      else if (sBefehl.equals("concat error")) // concat Fehlermeldung
        {
          int iPos=A.g.getPosBegriff("Fehlermeldung",sEingabe);
          if (iPos<0)
            Static.printError("Fehlermeldung "+sEingabe+" nicht gefunden!");
          else
            addString2(SQL.getString(A.g, "SELECT Memo FROM Daten_Memo where aic_tabellenname="+A.g.iTabBegriff+
                                    " and aic_fremd="+A.g.TabBegriffe.getI(iPos,"aic")+" and aic_sprache="+A.g.getSprache()));
        }
        else if (sBefehl.equals("concat chr")) // concat chr
          addString2(""+(char)getI());
        else if (sBefehl.equals("concat Format")) // concat Format
          addString2(Static.FillSpace("",bAS ? bVar ? gets(sVar):A.sReg:bSpalte ? TabAbfrage.getS(A.TabSpalten.getS("Kennung3")):A.sReg,sEingabe!=null ? getI():A.TabSpalten.getI("Laenge")));
        else if (sBefehl.equals("reserve130"))
        {
        	int iPos=A.sReg==null || sEingabe==null ? -1:sEingabe.indexOf("|");
        	if (iPos>-1)
        	{
        		String sVon=sEingabe.substring(1, iPos);
        		String sBis=sEingabe.substring(iPos+1, sEingabe.length()-1);
        		A.g.debugInfo("Ersetze in:"+A.sReg+":"+sVon+"->"+sBis+", iPos="+iPos);
        		A.sReg=A.sReg.replaceAll(sVon,sBis);
        	}
        }
        else if (sBefehl.equals("reserve136"))
        	addString(A.g.DateTimeToString(A.dtReg.toDate()));
        else if (sBefehl.equals("Befehl180"))
        	addString2(ZerlegeS(sEingabe));
        else if (sBefehl.equals("Befehl181")) // convert Adress to GPS
        {
        	GPS gps=GPS.getGPS(A.g,A.StaString,bAlle);
        	if (bVar)
        		setGPS(sVar,gps);
        	else
        		A.gps=gps;
        	iFC=gps==null ? 2:0;
        }
        else if (sBefehl.equals("Befehl182")) // show route
        {
        	GPS gps2=bSpalte ? new GPS(TabAbfrage,sSpalte):bVar ? getGPSVar(sVar):GPS.getGPS(A.g,A.StaString,bAlle);
        	if (A.gps != null && gps2 !=null)
        		A.gps.showRoute(gps2);
        }
        else if (sBefehl.equals("distance_GPS")) // Luftlinie zwischen GPS-Koordinaten
        {
        	GPS gps2=bSpalte ? new GPS(TabAbfrage,sSpalte):bVar ? getGPSVar(sVar):GPS.getGPS(A.g,A.StaString,bAlle);
        	if (A.gps != null && gps2 !=null)
        	{
        		A.dReg=A.gps.distanceTo(gps2);
        		iFC=0;
        	}
        	else
        	{
        		A.dReg=0;
        		iFC=2;
        	}
        }
        else if (sBefehl.equals("Befehl183")) // Straßen-km zwischen GPS-Koordinaten
        {
        	GPS gps2=bSpalte ? new GPS(TabAbfrage,sSpalte):bVar ? getGPSVar(sVar):GPS.getGPS(A.g,A.StaString,bAlle);
        	if (A.gps==null || gps2 ==null)
        	{
        		A.dReg=0;
        		A.dReg2=0;
        		iFC=2;
        	}
        	Vector<Integer> Vec=A.gps.RouteTo(A.g,gps2);
        	if (Vec!=null && Vec.size()>1)
        	{
        		A.dReg=Sort.geti(Vec, 0);
        		A.dReg2=Sort.geti(Vec, 1);
        		iFC=0;
        	}
        	else
        	{
        		A.dReg=0;
        		A.dReg2=0;
        		iFC=2;
        	}
        }
        else if (sBefehl.equals("Befehl184")) // set Google-Key
        	GPS.sGoogleKey=getM0();
        else
          RunTransaction();
          //A.printError("Calc.RunStringCommand(): Der String-Befehl >"+sBefehl+"< steht noch nicht zur Verfügung");
    }

        private void RunReserve()
        {
          if (sBefehl.equals("get weeks"))
          {
        	  Date dtV=bSpalte?TabAbfrage.getDate(sSpalte):bVar ? getVonVar(sVar):A.getVon();
              Date dtB=bSpalte?TabAbfrage.getDate(A.TabSpalten.getS("Kennung2")):bVar ? getBisVar(sVar):A.getBis();
              iFC=dtV==null || dtB==null ? 1:0;
              if (iFC>0)
            	  A.dReg=0;
              else
              {
	            DateWOD dtVon1=new DateWOD(dtV); // bSpalte?TabAbfrage.getDate(sSpalte):A.getVon());
	            DateWOD dtBis1=new DateWOD(dtB); // bSpalte?TabAbfrage.getDate(A.TabSpalten.getS("Kennung2")):A.getBis());
	            int i=-1;
	            while (dtVon1.getAbsSeconds()<=dtBis1.getAbsSeconds())
	            {
	              dtVon1.nextWeek();
	              i++;
	            }
	            A.dReg=i;
              }
          }
          else if (sBefehl.equals("set ANSI"))
            Static.CP=sEingabe==null ? "Cp1252":sEingabe;
          else if (sBefehl.equals("get path"))
            CCo(A.sReg.substring(0,A.sReg.lastIndexOf(File.separator)));
          /*else if (sBefehl.equals("reserve59"))
            TabAbfrage.setInhalt("sSpalte",new Double(Static.Round6(TabAbfrage.getF(sSpalte)+A.dReg)));
          else if (sBefehl.equals("reserve60"))
            bUseVar=true;*/
          //else if (sBefehl.equals("reserve59")) // init from-to
          //  setVB(sEingabe,bSpalte ? TabAbfrage.getTimestamp(sSpalte):A.g.getVon(),bSpalte ? TabAbfrage.getTimestamp(A.TabSpalten.getS("Kennung2")):A.g.getBis());
          else if (sBefehl.equals("init TabPerioden")) // Init TabPerioden
            if (Static.bWeb && !A.g.bDruck)
              A.printError(MPos()+"init TabPerioden in Web nicht verfügbar",501);
            else
              A.g.TabPerioden = null;
          else if (sBefehl.equals("add Periode")) // add Periode
            if (Static.bWeb && !A.g.bDruck)
              A.printError(MPos()+"add Periode in Web nicht verfügbar",501);
            else
              Zeitraum.addPeriode(A.g);
          else if (sBefehl.equals("copy next line"))
          {
            TabAbfrage.bInsert = !TabAbfrage.out();
            TabAbfrage.copyLine();
            TabAbfrage.moveNext();
            TabAbfrage.setInhalt(iBew==0?"aic_stamm":"aic_bew_pool",null);
            TabAbfrage.bInsert = false;
          }
          else if (sBefehl.equals("get Firma"))
            A.iFirma=bSpalte ? TabAbfrage.getI(sSpalte):0;
          else if (sBefehl.equals("send to AServer"))
            if (Static.bWeb)
              A.printError(MPos()+"send to AServer von web nicht mehr erlaubt",501);
            else
              AClient.send_AServer(getS(),A.g);
          else if (sBefehl.equals("get from AServer"))
            if (Static.bWeb)
              A.printError(MPos()+"get from AServer von web nicht mehr erlaubt",501);
            else
              CCo(AClient.get_AServer(getS()));
          else if (sBefehl.equals("VecAic add Vec")) // Vec to VecAic
          {
            if (A.VecAic==null)
              A.VecAic=new java.util.Stack<Integer>();
            Static.addVectorI(A.VecAic, bGroup ? TabAbfrage.groupVecAic(sSpalte) : TabAbfrage.toVecAic(sSpalte));
          }
          else if (sBefehl.equals("clear history")) // clear Eig-Stichtag
            A.g.fixtestError("clear history entfernt "+A.g.exec("update stammpool set pro2_aic_protokoll="+A.getProt()+" where pro2_aic_protokoll is null and aic_stamm="+A.iQryReg+
                " and aic_eigenschaft="+A.TabSpalten.getI("Eig")+(A.dtStichtag!=null ? " and gultig_von>="+A.g.DateTimeToString(A.dtStichtag.toTimestamp()):""))+" Stammpool-Sätze");
          else if (sBefehl.equals("call AServer-Modell"))
          {
            if (Static.bWeb)
              A.printError(MPos()+"call AServer-Modell von web nicht mehr erlaubt",501);
            else
            { 
        	A.g.commit();
        	String sM=sEingabe.indexOf(';')>0 ? sEingabe:sEingabe+";"+sPeriode;
                AClient.send_AServer("c:"+sM+";"+DateWOD.Format(A.g,"yyyy-MM-dd HH:mm:ss",A.getVon())+DateWOD.Format(A.g,"yyyy-MM-dd HH:mm:ss",A.getBis())+";"+A.g.getMandant()+";"+A.iQryReg+";"+A.iBewReg,A.g);
            }
          }
          else if (sBefehl.equals("split String")) // reserve72
          {
            if (sEingabe == null)
              A.printError(MPos()+"split String (trennen) ohne Trennzeichen nicht möglich",400);
            else
            {
              String sE=sEingabe;
              boolean bH=sE.length()>1 && sE.charAt(0)=='-';
              if (sE.length()>1 && (sE.charAt(0)=='-' || sE.charAt(0)=='+'))
            	  sE=sE.substring(1);
              String sT=sE.equals("sp") ? " " : sEingabe.equals("tab") ? "" + (char)9 : sE;
              int iPos = A.sReg == null || A.sReg.length() < 2 ? -1 : bH ? A.sReg.lastIndexOf(sT):A.sReg.indexOf(sT);
              if (iPos > 0)
              {
                A.sReg2 = A.sReg.substring(iPos + 1);
                A.sReg = A.sReg.substring(0, iPos);
              }
              else
                A.sReg2 = "";
            }
          }
          else  if (sBefehl.equals("reserve138")) // Multisplit
          {
              if (sEingabe == null)
                A.printError(MPos()+"split String (trennen) ohne Trennzeichen nicht möglich",400);
              else
              {
            	String s=getMo(1);
        	    String[] sSp=s.split(sEingabe);
        	    for (int i=sSp.length;i>0;i--)
        	    	A.StaString.push(sSp[i-1]);
              }
          }
          else  if (sBefehl.equals("reserve139")) // fill html
        	  CCo(A.g.fillHtml(A.sReg));
          else if (sBefehl.equals("concat spaces")) // concat spaces
            addString(Static.FillSpace("","",getI()));
          else if (sBefehl.equals("set Anlage"))
          {
            A.bAnlage=sEingabe != null;
            A.bDel=false;
            if (A.bAnlage)
            {
              A.bDel=sEingabe.startsWith("-");
              A.iAnlage = A.g.getCodeAic("Anlage",A.bDel ? sEingabe.substring(1):sEingabe);
            }
          }
          else if (sBefehl.equals("remember real aic")) // remember Real-Aic
            TabAbfrage.setInhalt(iBew>0 ? "aic_Bew_Pool":"aic_stamm",iBew>0 ? A.iBewReg:A.iReg);
          else if (sBefehl.equals("get Color")) // get color
          {
            //A.g.fixInfo("Eingabe="+sEingabe);
            //A.g.fixInfo("Sub1="+sEingabe.substring(1));
             int iCol=0;
             if (sEingabe.startsWith("#"))
             {
               try {
                 Field field = Color.class.getField(sEingabe.substring(1));
                 iCol= ((Color)field.get(null)).getRGB();
               }
               catch (Exception e) {
                 Static.printError("Farbe "+sEingabe.substring(1)+" nicht möglich");
               }
             }
             else
               iCol=A.g.getColor(sEingabe);
              //sEingabe.startsWith("#") ? Color.getColor(sEingabe.substring(1),Color.BLACK).getRGB() : A.g.getColor(sEingabe);
             if (bSpalte) TabAbfrage.setInhalt(sSpalte,iCol); 
             else if (bVar) set(sVar,iCol); else A.dReg=iCol;
          }
          else if (sBefehl.equals("set Mandant")) // reserve77
          {
            A.g.setMandant(bSpalte ? TabAbfrage.getI(sSpalte) : A.iMReg,false);
            A.iMReg=A.g.getMandant();
            A.debugInfo("set Mandant->"+A.g.MandantBez(A.iMReg));
          }
          else if (sBefehl.equals("get length")) // reserve79
            A.dReg=getM0().length();
          else if (sBefehl.equals("change Mandant")) // reserve80
            A.g.exec("update bew_pool set aic_mandant="+A.iMReg+" where aic_bew_pool="+TabAbfrage.getI("aic_bew_pool"));
          else if (sBefehl.equals("get Temp")) // reserve81
            CCo(Static.getTemp());
          else if (sBefehl.equals("add holiday")) // reserve83
            A.g.addFeiertag(A.dtReg,sEingabe);
          else if (sBefehl.equals("clear holidays")) // reserve84
            A.g.clearFeiertag();
          else if (sBefehl.equals("bew new save")) // reserve86
          {
            //A.g.exec("update bew_pool set pro_aic_protokoll="+A.getProt()+" where aic_bew_pool="+TabAbfrage.getI("aic_bew_pool"));
            TabAbfrage.setInhalt("aic_bew_pool", Import.Undelete(A.g, TabAbfrage.getI("aic_bew_pool"), A.getProt(),true));
            //A.copyBewPool(;
          }
          else if (sBefehl.equals("get computer")) // reserve87
          {
            int iC=SQL.getInteger(A.g,A.g.first("aic_computer from logging where aic_benutzer=? order by aic_logging desc"),-1,""+(bSpalte ? TabAbfrage.getI(sSpalte):A.iReg));
            if (iC>0)
            {
              A.sReg = SQL.getString(A.g, "select " + (sEingabe == null ? "hostname" : sEingabe) + " from computer where aic_computer=?", "" + iC);
              if(A.sReg!=null && A.sReg.startsWith("*"))
                A.sReg=A.sReg.substring(1);
            }
            else
              A.g.fixInfo("Computer für Benutzer "+TabAbfrage.getI(sSpalte)+" kann nicht ermittelt werden");
          }
          else if (sBefehl.equals("exec file")) // reserve88
            try { Runtime.getRuntime().exec(getM0());}
            catch(IOException io) { A.printError(MPos()+"exec file: "+io,400);}
          else if (sBefehl.equals("SQL_getAic_Bind")) // reserve89
            A.iReg=SQL.getInteger(A.g,getM(),-1,""+A.iReg);
          else if (sBefehl.equals("replace wildcard")) // reserve90
          {
            if (sEingabe != null && sEingabe.equals("ALL"))
            {
              int iAbf=A.TabAbfragen.getI("Aic");
              A.TabSpalten.push();
              //for(int iPos=A.TabSpalten.getPos("Abfrage",iAbf);iPos<A.TabSpalten.size()&&A.TabSpalten.getI(iPos,"Abfrage")==iAbf;iPos++)
              for(A.TabSpalten.posInhalt("Abfrage",iAbf);!A.TabSpalten.eof()&&A.TabSpalten.getI("Abfrage")==iAbf;A.TabSpalten.moveNext())
                A.sReg = A.sReg.replaceAll("#" + A.TabSpalten.getS("Bezeichnung")+"#", ""+TabAbfrage.TabWertToObj(A.TabSpalten,false));
              A.TabSpalten.pop();
            }
            else
              A.sReg = A.sReg.replaceAll(sEingabe == null ? "#" + A.TabSpalten.getS("Bezeichnung")+"#" : sEingabe, ""+TabAbfrage.TabWertToObj(A.TabSpalten,false));
          }
          else if (sBefehl.equals("kill space")) // reserve91
            A.sReg=A.sReg.replaceAll(sEingabe == null ? " ":sEingabe,"");
          else if (sBefehl.equals("base64")) // reserve92
          {
            A.sReg += A.TabSpalten.getS("Datentyp").equals("Bild2") ? Static.ImageToString(SQL.getImage(A.g,getM3b()),getExt(getM3b())):Static.ImageToString(getM3b());
            A.g.fixtestInfo("sReg-Länge="+A.sReg.length());
          }
          else if (sBefehl.equals("set Timeout")) //  reserve93
            Transact.iTimeOut=getI();
          else if (sBefehl.equals("set Stammtitel")) //  reserve94
            ((Abfrage)A.TabAbfragen.getInhalt("AF")).sStammTitel=getS();
          else if (sBefehl.equals("generate PW")) // Erzeugt Zufallszahl mit Länge laut Eingabe/Zahlenregister: reserve95
            A.sReg=Static.generatePW(getI());
          else if (sBefehl.equals("get filesize")) // reserve96
            A.dReg=new File(getM3()).length();
          else if (sBefehl.equals("get filedate")) // reserve97
            A.dtReg=new DateWOD(new File(getM3()).lastModified());
          else if (sBefehl.equals("Tab message")) // reserve98
          {
        	  if (Static.bWeb)
        		  A.printError("Tab-Message mit Web nicht möglich",501);
        	  else if (!A.bTimer && A.in == null)
            		new Message(Message.INFORMATION_MESSAGE+Message.SHOW_TAB+Message.UNMODAL+Message.EXPORT, null, A.g).showDialog("Info", new String[] {getS()},getTab());
          }
          else if (sBefehl.equals("get Prot")) // reserve99
            A.iReg=SQL.getInteger(A.g,"select max(aic_protokoll) from protokoll where timestamp<"+A.g.DateTimeToString(getDW(A.dtReg).toDate()));
          else if (sBefehl.equals("Prot to Date")) // reserve100
            A.dtReg=new DateWOD(SQL.getTimestamp(A.g,"select timestamp from protokoll where aic_protokoll="+A.iReg));
          else if (sBefehl.equals("get single motion")) // reserve101
            getMotions(sSpalte,A.iReg);
          else if (sBefehl.equals("get Pos")) // reserve102
            A.dReg=TabAbfrage.getPos();
          else if (sBefehl.equals("write Temp")) // reserve103
            WriteZwischenspeicher(getM1(),TabAbfrage);
          else if (sBefehl.equals("set date")) // reserve104
          {
            DateWOD dt=new DateWOD(bSpalte ? getDW(): bVar ? getdt(sVar):A.dtReg2);
            //DateWOD dt=new DateWOD(bSpalte ? TabAbfrage.getDate(sSpalte));
            if (dt.isNull())
            {
              if (bAS)
                TabAbfrage.setInhalt(sSpalte,null);
              else  
                A.dtReg=null;
            }
            else
            {
              DateWOD dt2=getDW(A.dtReg);
              dt.setYear(dt2.getYear());
              dt.setMonth(dt2.getMonth());
              dt.setDate(dt2.getDate());
              if (bAS)
                TabAbfrage.setInhalt(sSpalte, dt.toTimestamp());
              else
                A.dtReg=dt;
            }
          }
          else if (sBefehl.equals("print AServer")) // reserve105 drucke/e-mail/pdf über AServer
            if (Static.bWeb)
        	  A.printError(MPos()+"print AServer von web nicht mehr erlaubt",501);
            else
              AClient.send_AServer("d:"+sEingabe+";"+Static.beiLeer(sPeriode,"Tag")+";"+DateWOD.Format(A.g,"yyyy-MM-dd HH:mm:ss",A.getVon())+";"+
                                 DateWOD.Format(A.g,"yyyy-MM-dd HH:mm:ss",A.getBis())+";"+A.g.getMandant()+";"+(A.VecAic==null ? A.iQryReg:0)+";"+A.sReg,A.g,A.VecAic);
          else if (sBefehl.equals("get Begriff-Kennung")) // reserve106
            CCo(A.g.TabBegriffe.getKennung(bSpalte ? getAic():A.iReg));
          else if (sBefehl.equals("no title")) // reserve107
            bTitel=getB();
          else if (sBefehl.equals("reconnect")) // reserve108
            A.g.bNextReconnect=true;
          else if (sBefehl.equals("set Global")) // reserve114
        	  A.g.setTab(A.TabAbfragen);//.getI(""),A.TabAbfragen.getI("Stamm"),TabAbfrage);
          else if (sBefehl.equals("get Global")) // reserve115
        	  A.g.fixtestInfo("Abfrage "+A.TabAbfragen.getS("Bezeichnung")+(TabAbfrage==null?" nicht geladen":"erfolgreich von Global geladen"));//TabAbfrage.showGrid("get Global");//A.g.getTab(A.TabAbfragen.getI("Begriff"));
          else if (sBefehl.equals("Befehl154")) // clear Global
        	  A.g.clearTab(A.TabAbfragen.getI("Begriff"));
          else if (sBefehl.equals("open Stage")) // reserve117
          {
        	  A.printError("open Stage wird nicht mehr unterstützt seit 25.7.2019",501);
        	  //String s=getM0();
        	  //A.g.fixtestInfo("Aufruf117 von "+s+"/"+A.FomFX+"/"+A.FomA);
//        	  if (A.FomFX != null)
//        		  EingabeFormularFX.Hole(A.g, null/*Btn*/, null/*FomVor*/, A.g.getBegriffAicS("Frame",s), A.iQryReg, null);
//        	  else 
//        		  if(s!=null && s.startsWith("H:"))
//        		  Hauptschirm.get(s.substring(2),A.g);
//        	  else
//        		  EingabeFormular.HoleFormular(A.g, A.g.getBegriffAicS("Frame",s), null, A.iQryReg, false);
          }
//          else if (sBefehl.equals("reserve118")) // set bitsBew
//          	A.g.exec("update bew_pool set bitsbew="+sEingabe+" where aic_bew_pool="+TabAbfrage.getI("aic_bew_pool"));
          else if (sBefehl.equals("reserve120")) // set ANR-Eig
          {
        	  if (sEingabe!= null)
        	  {
        		  int iPos=A.g.TabEigenschaften.getPos("Kennung",sEingabe.toUpperCase());
        		  if (iPos<0)
        			  A.printError("reserve120: Eigenschaft "+sEingabe+" nicht gefunden", 400);
        		  else
        			  iANR_Eig=A.g.TabEigenschaften.getI(iPos,"Aic");
        	  }
        	  else if (bSpalte)
        		  iANR_Eig=A.TabSpalten.getI("Eig");
        	  else
        		  iANR_Eig=0;
//        		  A.printError("reserve120: Spalte oder Eingabe muss angegeben werden", 400);
        	  A.debugInfo("iANR_Eig="+iANR_Eig);
          }
          else if (sBefehl.equals("reserve131")) // Name verschlüsseln
        	  Reinigung.replaceName(A.g,A.iStt);
          else if (sBefehl.equals("reserve132")) // History löschen
        	  Reinigung.clearHistory(A.g);
          else if (sBefehl.equals("reserve133")) // get Anzahl Benutzer
        	  A.dReg=SQL.getInteger(A.g,"select count(aic_benutzer) from benutzer where geloescht is null and aic_stamm="+(bSpalte ? TabAbfrage.getI("aic_stamm"):A.iQryReg));
          else if (sBefehl.equals("reserve134")) // Benutzer laut Stammsatz/Datum löschen
          {
        	  //if (A.dtReg!=null && !A.dtReg.isNull())
        		  A.g.exec("update benutzer set use_bis="+(A.dtReg==null || A.dtReg.isNull() ? "null":A.g.SQL_Format(A.dtReg.toTimestamp()))
        				  +" where geloescht is null and aic_stamm="+(bSpalte ? TabAbfrage.getI("aic_stamm"):A.iQryReg));// Benutzer löschen
          }
          else if (sBefehl.equals("Befehl166")) // get Anzahl in Bew_Stamm
        	  A.dReg=SQL.getInteger(A.g,"select count(bew_stamm.aic_bew_pool)"+SQL_BewStamm());
          else if (sBefehl.equals("Befehl167")) // get VecBewPool aus Bew_Stamm
        	  A.VecBewPool=SQL.getVector("select bew_stamm.aic_bew_pool"+SQL_BewStamm(),A.g);
          else if (sBefehl.equals("Befehl169")) // get UseVec
          {
//        	  int iPos=A.g.TabStammtypen.getPos("Kennung",sEingabe.toUpperCase());
        	  int iStt=EtoStt(sEingabe);
        	  if (iStt==0)
        		  A.printError(MPos()+"Befehl169 findet Stt mit E="+sEingabe+" nicht",400);
        	  else  
        	  {
//        		  int iStt=A.g.TabStammtypen.getI(iPos,"Aic");
        		  Vector<Integer> Vec=Static.bWeb ? A.g.getVecStamm(A.iHauptModell,iStt):A.g.getVecStamm2(iStt);
        		  if (Vec==null)
        			  Vec=SQL.getVector("select aic_stamm from stammview2 p2 where aic_stammtyp="+iStt+" and aic_rolle is null"+A.g.getReadMandanten(), A.g);
        		  A.VecAic=VecToStack(Vec);
        	  }
//        	  A.g.TabStammtypen.showGrid("TabStammtypen");
          }
          else if (sBefehl.equals("Befehl170")) // get VecAic-size
        	  A.dReg=A.VecAic.size();
          else if (sBefehl.equals("Befehl171")) // concat Stamm
        	  addString2(A.g.getStamm(bVar ? geti(sVar) : bSpalte ? getAic():A.iReg));
          else if (sBefehl.equals("Befehl172")) // add to VecBew
        	  A.VecBewPool.addElement(bSpalte ? TabAbfrage.getI(sEingabe==null ? sSpalte:sEingabe):bVar ? geti(sVar):sEingabe!=null ? Sort.geti(sEingabe):A.iBewReg);
          else if (sBefehl.equals("Befehl173")) // get DST-Zone
          {
        	  if (A.dtReg!=null && !A.dtReg.isNull())
        	  {
	        	  A.iZone=DateWOD.getZone(sEingabe!=null && sEingabe.equals("OS") ? A.g.getZoneS():getM0(),A.dtReg.getTime());
	        	  A.bZone=true;
        	  }
          }
          else if (sBefehl.equals("Befehl176")) // set DST-Zone
        	  A.dtReg.setZone(A.g, getM0());
          else if (sBefehl.equals("Befehl175")) // concat boolean
        	  addString2(Static.JaNein(bVar ? getb(sVar):bSpalte ? TabAbfrage.getB(sSpalte):A.bReg));
          else
        	  RunOLE();

        }
        
        private String SQL_BewStamm()
        {
        	return " from bew_stamm"+(bAlle ? "":" join bewview2 v on v.aic_bew_pool=bew_stamm.aic_bew_pool")+" where aic_stamm="+(bVar ? geti(sVar) : bSpalte ? getAic():A.iReg);
        }
        
        private int EtoStt(String s)
        {
        	if (Static.Leer(s))
        		return 0;
        	int iPos=A.g.TabStammtypen.getPos("Kennung",s.toUpperCase());
        	if (iPos<0)
        		A.printError(MPos()+" EtoStt findet Stt mit Kennung="+s+" nicht",400);
        	return iPos<0 ? 0:A.g.TabStammtypen.getI(iPos,"Aic");
        }
        
        private Stack<Integer> VecToStack(Vector<Integer> Vec)
        {
        	Stack<Integer> Sta=new Stack<Integer>();
        	if (Vec != null)
              for (int i=0;i<Vec.size();i++)
            	Sta.push(Sort.getInt(Vec,i));
            return Sta;
        }

        private Tabellenspeicher getTab()
        {
          Tabellenspeicher Tab=new Tabellenspeicher(A.TabSpalten,A.g);
          A.TabSpalten.push();
          int iAbfrage=A.TabSpalten.getI("Abfrage");
          for (TabAbfrage.moveFirst();!TabAbfrage.out();TabAbfrage.moveNext())
            for(A.TabSpalten.posInhalt("Abfrage",iAbfrage);!A.TabSpalten.eof() && A.TabSpalten.getI("Abfrage")==iAbfrage;A.TabSpalten.moveNext())
              Tab.addInhalt(A.TabSpalten.getS("Kennung"),TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung")));
          A.TabSpalten.pop();
          return Tab;
        }

        public static Image getImage(Global g,String sFile)
        {
          if (sFile.indexOf("_")>0)
          {
            int iDaten=Integer.parseInt(sFile.substring(0,sFile.indexOf("_")));
            return SQL.getImage(g,iDaten);
          }
          else if (!sFile.equals(""))
            Static.printError("getImage fehlerhaft:"+sFile);
          return null;
        }

        public static String getExt(String sFile)
        {
          if (sFile.indexOf(".")>0)
            return sFile.substring(sFile.lastIndexOf('.') + 1);
          else if (!sFile.equals(""))
            Static.printError("getExt fehlerhaft:"+sFile);
          return null;

        }

        private static Vector getVecManual(Global g)
        {
          if (VecManual==null)
          {
            VecManualS=new AUVector(new String[] {"Eingabe","Tabelle","Undelete","Planung"});
            VecManual=new Vector<Number>();
            //for (int i=0;i<VecManualS.size();i++)
            for (Object s:VecManualS)
            {
              int iAic=g.getBegriffAicS("Anlage", (String)s);
              VecManual.addElement(new Integer(iAic)); //VecManualS.elementAt(i))));
              VecManual.addElement(new Long(iAic));
            }
          }
          return VecManual;
        }

        private boolean SameDate(DateWOD dt1,DateWOD dt2)
        {
          boolean b1= dt1==null || dt1.isNull();
          boolean b2= dt2==null || dt2.isNull();
          if (b1 && b2)
            return true;
          else if (b1 || b2)
            return false;
          else
          {
              dt1.setTimeZero();
              dt2.setTimeZero();
              return dt1.equals(dt2);
          }
        }

        private boolean isSperre(int iAic)
        {
          int iLog=SQL.getInteger(A.g,"select aic_logging from bew_boolean where aic_bew_pool="+iAic+" and aic_eigenschaft="+A.g.iTimerSperre);
          if (iLog==A.g.getLog())
            return false;
          else if (iLog>0)
            return true;
          A.g.bISQL=true;
          SQL Qry = new SQL(A.g);
          Qry.add("aic_bew_pool", iAic);
          Qry.add("aic_eigenschaft", A.g.iTimerSperre);
          Qry.add("spalte_boolean", 1);
          Qry.add("aic_logging",A.g.getLog());
          boolean b=Qry.insert("bew_boolean", false) < 0;
          if (!b)
          {
        	  A.g.fixInfo("** Sperre setzen bei "+iAic+" mit Log="+A.g.getLog());
        	  A.VecSperre.addElement(iAic);
          }
          else
        	  A.g.fixInfo("** Sperre war schon gesetzt:"+SQL.getInteger(A.g,"select aic_logging from bew_boolean where aic_bew_pool="+iAic+" and aic_eigenschaft="+A.g.iTimerSperre)+" statt "+A.g.getLog());
          A.g.bISQL=false;
          return b;
        }
        
    private boolean Fehler(String s)
    {
    	return Fehler(s,true);
    }
        
    private boolean Fehler(String s,boolean b)
    {
    	if (b)
    		A.g.fixtestError(MPos()+s);
    	return false;
    }
    
        private String getBG(String sBefehl,String sBG)
    {
    	int iTZ=sEingabe.indexOf(':');
    	if (iTZ>0)
    	{
    		sBG=sEingabe.substring(0, iTZ);
    		sEingabe=sEingabe.substring(iTZ+1);
        		int iPos=A.g.TabBegriffgruppen.getPos("Kennung",sBG);
        		if (iPos<0)
        		{
        			A.printError(sBefehl+": Begriffgruppe "+sBG+" existiert nicht",501);
        			sBG=null;
        		}
//    		A.g.fixtestError("BG="+sBG+", Eingabe="+sEingabe);
    	}
    	return sBG;
    }
    
    private boolean Ganztag()
    {
    	if (bSpalte || bVar)
    	{
    		if (bSpalte && TabAbfrage.out())
    		{
        			// A.printError("Ganztag: Keine Daten vorhanden",400);
              A.g.fixtestError("Ganztag: Keine Daten vorhanden bei "+A.TabAbfragen.getS("Bezeichnung")+" bei Zeile "+getZeile()+" in Modell "+A.TabModelle.getS("Bezeichnung"));
              A.debugInfo("Ganztag: Keine Daten vorhanden");
              iFC=4;
        		return true;
    		}
    		DateWOD dtv=bVar ? new DateWOD(getVonVar(sVar)):getDWS(sSpalte);
    		DateWOD dtb=bVar ? new DateWOD(getBisVar(sVar)):getDWS(A.TabSpalten.getS("Kennung2"));
        	if (dtv==null || dtv.isNull() || dtb==null || dtb.isNull())
        	{
        		iFC=4;
        		return true;
        	}
        	else
        	{
            	String sDv=dtv.Format(A.g,"dd.MM.yyyy HH:mm:ss");
            	String sDb=dtb.Format(A.g,"dd.MM.yyyy HH:mm:ss");
            	iFC=0;
        		return sDv.substring(11).equals("00:00:00") && sDb.substring(11).equals("00:00:00");
        	}
    	}
    	else
    	{
        	A.printError("Ganztag nur mit Spalte oder Variable möglich",400);
        	iFC=4;
    		return false;
    	}
    }
    
    private boolean isNullVar()
    {
    	Object Obj=A.TabVar.getInhalt("Wert");
    	boolean bIsNull=false;
        if(Obj==null)
                bIsNull = true;
        else if(Obj instanceof GPS)
            bIsNull = ((GPS)Obj).isNull();
        else
        	bIsNull = Sort.gets(Obj).equals(Static.sLeer);
        return bIsNull;
    }

  private void fillModellDialog()
  {
//	A.g.fixtestError("fillModellDialog");
    A.TabMD.moveFirst();
    if (TabAbfrage.size()==0)
    {
    	TabAbfrage.newLine();
//    	A.g.fixtestError("neue Zeile für Modell-Dialog");
    }
    else if (TabAbfrage.out())
    {
    	TabAbfrage.moveFirst();
//    	A.g.fixtestError("erste Zeile für Modell-Dialog"); 
    }
    for (int iPos=A.TabSpalten.getPos("Abfrage",iAbfrageMD);iPos<A.TabSpalten.size() && A.TabSpalten.getI(iPos,"Abfrage")==iAbfrageMD;iPos++)
    {
      String sK="e"+A.TabSpalten.getS(iPos,"Kennung").substring(1);
      if (A.TabMD.exists(sK))
      {
        Object Obj=A.TabMD.getInhalt(sK);
        A.debugInfo("setze Modell-Dialog: "+A.TabSpalten.getS(iPos,"Bezeichnung")+"("+A.TabSpalten.getS(iPos,"Kennung")+") auf "+Static.print(Obj));
//        A.g.fixtestError("setze Modell-Dialog: "+A.TabSpalten.getS(iPos,"Bezeichnung")+"("+A.TabSpalten.getS(iPos,"Kennung")+") auf "+Static.print(Obj));
//                				  A.g.fixInfo("setze Modell-Dialog: "+A.TabSpalten.getS(iPos,"Bezeichnung")+"("+A.TabSpalten.getS(iPos,"Kennung")+") auf "+Obj);   
        if (Obj==null)
          A.g.fixInfo("MD: setze null auf "+A.TabSpalten.getS(iPos,"Kennung"));
        else if (Obj instanceof String)
        {
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung3"), (String)Obj);
          A.debugInfo("MD: setze String "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung3"));
//                					  A.g.fixtestError("MD: setze String "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung3"));
        }
        else if (Obj instanceof Boolean)
        {
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung"), (boolean)Obj);
          A.debugInfo("MD: setze Boolean "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung"));
//	                				  A.g.fixtestError("MD: setze Boolean "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung"));
      }
        else if (Obj instanceof Double)
        {
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung"), (double)Obj);
          A.debugInfo("MD: setze Double "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung"));
//                					  A.g.fixtestError("MD: setze Double "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung"));
      }
        else if (Obj instanceof Integer)
        {
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung"), (int)Obj);
          A.debugInfo("MD: setze Integer "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung"));
//                					  A.g.fixtestError("MD: setze Integer "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung"));
      }
        else if (Obj instanceof Date)
        {
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung"), (Date)Obj);
          A.debugInfo("MD: setze Date "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung"));
//                					  A.g.fixtestError("MD: setze Date "+Obj+" auf "+A.TabSpalten.getS(iPos,"Kennung"));
        }
        else if (Obj instanceof Combo)
        {
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung"), ((Combo)Obj).getAic());
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung3"), ((Combo)Obj).getBezeichnung());
          A.debugInfo("MD: setze Combo "+A.TabSpalten.getS(iPos,"Kennung")+" auf "+((Combo)Obj).getAic()+"/"+((Combo)Obj).getBezeichnung());
//          A.g.fixtestError("MD: setze Combo "+A.TabSpalten.getS(iPos,"Kennung")+" auf "+((Combo)Obj).getAic()+"/"+((Combo)Obj).getBezeichnung());
        }
        else if (Obj instanceof VonBis)
        {
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung"), ((VonBis)Obj).getVon());
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung2"), ((VonBis)Obj).getBis());
          TabAbfrage.setInhalt(A.TabSpalten.getS(iPos,"Kennung3"),((VonBis)Obj).getDauer());
          A.debugInfo("MD: setze VonBis "+A.TabSpalten.getS(iPos,"Kennung")+" auf "+((VonBis)Obj).getVon()+"-"+((VonBis)Obj).getVon()+"/"+((VonBis)Obj).getDauer());
//          A.g.fixtestError("MD: setze VonBis "+A.TabSpalten.getS(iPos,"Kennung")+" auf "+((VonBis)Obj).getVon()+"-"+((VonBis)Obj).getVon()+"/"+((VonBis)Obj).getDauer());
        }
        else
          A.printError("!! Error bei Modell-Dialog: "+Obj.getClass().getName()+" wird noch nicht unterstützt",400);
      }
//                			  else // kein Fehler, da Spalte auch leer bleiben darf
//                				  A.printError("TabMD: Spalte "+sK+" fehlt",400);
    }
  }

  public boolean MD_Aufruf()
  {
    return new Modell_Dialog(A.FomA, A.g, A.sReg, (int)A.dReg, A.iQryReg, TabAbfrage, A.TabSpalten).bOk;
  }
  
  public JSONObject getJSON_Abf()
  {
//	  int iBits= A.TabAbfragen.getI("bits");
	  Abfrage Abf=(Abfrage)A.TabAbfragen.getInhalt("AF");
	  boolean bEdit=true;
	  JSONObject jO = new JSONObject();
	  JSONObject jh = new JSONObject();
	  webAbf.addHeader(A.g,jh,Abf,bEdit);
	  try
	  {
		jO.put("header", jh);

		jO.put("columns",webAbf.getSpalten(A.g,Abf,A.TabSpalten,bEdit,true,A.iBegBer));
		jO.put("data",webAbf.AbfToJSON(A.g,TabAbfrage,A.TabSpalten,new ShowAbfrage(Abf),0,0));
		if (webLog.bTest)
			jO.put("original",webLog.TabToJSON(TabAbfrage));
	  }
	  catch(Exception e) {};
	  return jO;
  }

	private boolean RunBedingung()
	{
		boolean bFehler=false;
		boolean b=false;
		boolean bSchleife=(TabBefehle.getI("MBITS")&SCHLEIFE)>0;
		// Zahl-Vergleiche
		//if (bSpalte && TabAbfrage.out() && (sBefehl.equals("<") || sBefehl.equals(">") || sBefehl.equals("=") || sBefehl.equals("= 0") || sBefehl.equals("> 0")))
		//	A.printError("Calc.RunBedingung(): Die Bedingung >"+sBefehl+"< ist bei out nicht möglich!");
		if (sBefehl.equals("<"))
			b=Static.Round6(getF3()) < Static.Round6(getF4());
		else if (sBefehl.equals(">"))
			b=Static.Round6(getF3()) > Static.Round6(getF4());
		else if (sBefehl.equals("Bedingung64")) // <=
			b=Static.Round6(getF3()) <= Static.Round6(getF4());
		else if (sBefehl.equals("Bedingung65")) // >=
			b=Static.Round6(getF3()) >= Static.Round6(getF4());
		else if (sBefehl.equals("="))
			b=bSpalte && TabAbfrage.out() ? false:Static.Round6(getF3())==Static.Round6(getF4());
		else if (sBefehl.equals("< duration"))
			b=(bSpalte ? TabAbfrage.getF(A.TabSpalten.getS("Kennung3")):(A.getBis().getTime()-A.getVon().getTime())/1000) < getF();
		else if (sBefehl.equals("> duration"))
			b=(bSpalte ? TabAbfrage.getF(A.TabSpalten.getS("Kennung3")):(A.getBis().getTime()-A.getVon().getTime())/1000) > getF();
		else if (sBefehl.equals("= duration"))
			b=bSpalte && TabAbfrage.out() ? false:(bSpalte ? TabAbfrage.getF(A.TabSpalten.getS("Kennung3")):(A.getBis().getTime()-A.getVon().getTime())/1000)==getF();
		else if (sBefehl.equals("= 0"))
			b=Math.abs(bVar ? getd(sVar):bSpalte ? TabAbfrage.out() ? -1:TabAbfrage.getF(sSpalte):A.dReg)<1e-12;
		else if (sBefehl.equals("> 0"))
			b=(bVar ? getd(sVar):bSpalte ? TabAbfrage.getF(sSpalte):A.dReg)>1e-12;
		// Boolean
		else if (sBefehl.equals("<> NIL"))
			b=bSpalte ? TabAbfrage.getInhalt(sSpalte) != null && !TabAbfrage.getS(sSpalte).equals(""):
                            bVar ? A.TabVar!=null && posVar(sVar,false) /*A.TabVar.posInhalt("Var",sVar)*/ && !isNullVar():!A.sReg.equals("");
		else if (sBefehl.equals("= FALSE"))
			b=!(bSpalte ? TabAbfrage.getB(sSpalte):bVar ? getb(sVar):A.bReg);
		else if (sBefehl.equals("= TRUE"))
			b=bSpalte ? TabAbfrage.getB(sSpalte):bVar ? getb(sVar):A.bReg;
                else if (sBefehl.equals("= Bool3"))
                  b=bSpalte ? getVec().contains(A.g.TabAuswahl.getI_AIC("Nr",TabAbfrage.getI(sSpalte))):false;
		else if (sBefehl.equals("record found"))
			b=TabAbfrage.getAnzahlElemente(sSpalte)>0;
		// Aic
		else if (sBefehl.equals("= Aic"))
			b=bSpalte ? TabAbfrage.out() ? Fehler("= Aic: Abfrage steht im out",!bSchleife):getAic()==(bVar ? geti(sVar):A.iReg):A.iReg==(bVar ? geti(sVar):A.iReg2);
		else if (sBefehl.equals("is Stt"))
			b=TabAbfrage.out() ? false:TabAbfrage.getI(A.TabSpalten.getS("Kennung2"))==A.iStt;
		else if (sBefehl.equals("is real Stt"))
			b=(bSpalte ? A.TabAbfragen.getI("Stt")==0 ? TabAbfrage.getI("aic_stammtyp"):A.TabAbfragen.getI("Stt"):SQL.getInteger(A.g,"select aic_stammtyp from stamm where aic_stamm=?",0,""+A.iReg))==A.iStt;
		else if (sBefehl.equals("= QryAic"))
			b=bSpalte ? TabAbfrage.out() ? Fehler("= QryAic: Abfrage steht im out",!bSchleife):getAic()==(bVar ? geti(sVar):A.iQryReg):bVar ? geti(sVar)==A.iQryReg:Fehler("= QryAic nur mit Spalte oder Var möglich");
//		else if (sBefehl.equals("= real Aic")) // 7.10.2020 entfernt, da es diese Bedingung nicht gibt
//			b=bSpalte ? TabAbfrage.out() ? Fehler("= real Aic: Abfrage steht im out"):TabAbfrage.getI("aic_stamm")==(bVar ? geti(sVar):A.iQryReg):Fehler("= real Aic nur mit Spalte möglich");
		else if (sBefehl.equals("= std aic"))
			b=bSpalte ? TabAbfrage.out() ? Fehler("= std aic: Abfrage steht im out",!bSchleife):TabAbfrage.getI("aic_stamm")==(bVar ? geti(sVar):A.iReg):Fehler("= std Aic nur mit Spalte möglich");
                else if (sBefehl.equals("is Bew"))
			b=TabAbfrage.out() ? false:TabAbfrage.getI(sSpalte)==A.iBewReg;
                else if (sBefehl.equals("is real Bew"))
			b=TabAbfrage.out() ? false:TabAbfrage.getI("aic_bew_pool")==A.iBewReg;
		else if (sBefehl.equals("Aic = 0"))
			b=A.iReg <= 0;
		else if (sBefehl.equals("QryAic = 0"))
			b=(bSpalte ? getAic():A.iQryReg) <= 0;
		else if (sBefehl.equals("eof real aic"))
			b=A.iPos>=A.VecStamm.size();
		// Periode
		else if (sBefehl.equals("same period"))
			b=(bSpalte ? TabAbfrage.getI(sSpalte):A.iReg) == iPeriode;
		// Datum
		else if (sBefehl.equals("date is null"))
			b=bSpalte ? TabAbfrage.getInhalt(sSpalte)==null : A.dtReg==null || A.dtReg.isNull();
		else if (sBefehl.equals("to is null"))
			b=bSpalte ? TabAbfrage.getInhalt(A.TabSpalten.getS("Kennung2"))==null : A.dtReg==null || A.dtReg.isNull();
		else if (sBefehl.equals("= public holiday"))
			b=!A.g.Feiertag(bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg==null || sEingabe!=null && sEingabe.equals("von") ? new DateWOD(A.getVon()):A.dtReg,bVar ? geti(sVar):A.iQryReg).equals("");
		else if (sBefehl.equals("is last day"))
		{
		  DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg;
		  b=dt==null || dt.isNull() ? false: dt.getDate() == dt.monthLength();
		}
		else if (sBefehl.equals("is first day"))
		{
		  DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg;
		  b=dt==null || dt.isNull() ? false: dt.getDate() == 1;
		}
		else if (sBefehl.equals("is last quarter-day"))
		{
		  DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg;
		  b=dt==null || dt.isNull() ? false: dt.getDate() == dt.monthLength() && dt.getMonth()%3==0;
		}
		else if (sBefehl.equals("is new year"))
		{
		  DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg;
		  b=dt==null || dt.isNull() ? false: dt.getDate() == 1 && dt.getMonth() == 1;
		}
		else if (sBefehl.equals("is new year eve"))
		{
		  DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg;
		  b=dt==null || dt.isNull() ? false: dt.getDate() == 31 && dt.getMonth() == 12;
		}
		else if (sBefehl.equals("is first weekday"))
		{
		  DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg;
		  b=dt==null || dt.isNull() ? false: dt.getDay() == dt.getFirstDayOfWeek();
		}
		else if (sBefehl.equals("is last weekday"))
		{
		  DateWOD dt=bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg;
		  b=dt==null || dt.isNull() ? false: dt.getDay() == dt.getFirstDayOfWeek()+6 || dt.getDay() == dt.getFirstDayOfWeek()-1;
		}
                else if (sBefehl.equals("is same weekday"))
                {
                  DateWOD dt2=bSpalte ? TabAbfrage.isNull(sSpalte)?null:new DateWOD(TabAbfrage.getDate(sSpalte)):bVar ? getdt(sVar):A.dtReg2;
                  b = A.dtReg != null && dt2 != null && A.dtReg.getDay() == dt2.getDay();
                }
                else if (sBefehl.equals("is same month"))
                {
                  DateWOD dt2 = bSpalte ? TabAbfrage.isNull(sSpalte) ? null : new DateWOD(TabAbfrage.getDate(sSpalte)):bVar ? getdt(sVar):A.dtReg2;
                  b = A.dtReg != null && dt2 != null && A.dtReg.getMonth() == dt2.getMonth();
                }
                else if (sBefehl.equals("is same year"))
                {
                  DateWOD dt2=bSpalte ? TabAbfrage.isNull(sSpalte) ? null:new DateWOD(TabAbfrage.getDate(sSpalte)):bVar ? getdt(sVar):A.dtReg2;
                  b = A.dtReg != null && dt2 != null && A.dtReg.getYear() == dt2.getYear();
                }
                else if (sBefehl.equals("is Sunday"))
			b=bSpalte ? TabAbfrage.getInhalt(sSpalte)!=null && new DateWOD(TabAbfrage.getDate(sSpalte)).getDay() == DateWOD.SUNDAY
				:A.dtReg != null && A.dtReg.getDay() == DateWOD.SUNDAY;
                else if (sBefehl.equals("weekday=")) // war Bedingung24
                  b=bSpalte ? TabAbfrage.getInhalt(sSpalte)!=null && new DateWOD(TabAbfrage.getDate(sSpalte)).getDay() == getI()
                      :bVar ? getdt(sVar)!=null && !getdt(sVar).isNull() && getdt(sVar).getDay() == getI():A.dtReg != null && A.dtReg.getDay() == getI();
                else if (sBefehl.equals("Bedingung46")) // month=
                          b=bSpalte ? TabAbfrage.getInhalt(sSpalte)!=null && new DateWOD(TabAbfrage.getDate(sSpalte)).getMonth() == getI()
                              :bVar ? getdt(sVar)!=null && !getdt(sVar).isNull() && getdt(sVar).getMonth() == getI():A.dtReg != null && A.dtReg.getMonth() == getI();
                else if (sBefehl.equals("in VecAic")) // war Bedingung25
                  b=A.VecAic==null ? false:sEingabe!=null && sEingabe.equals("A") ? A.VecAic.size()>0: bSpalte ? TabAbfrage.isNull(sSpalte) ? false:A.VecAic.contains(TabAbfrage.getInt(sSpalte)):A.iReg==0 ? false:A.VecAic.contains(new Integer(A.iReg));
                else if (sBefehl.equals("pos not date"))
                  b=TabAbfrage.posNotDate(sSpalte,A.dtReg,bAlle,true);
                else if (sBefehl.equals("pos next not date")) // war Bedingung26
                  b=TabAbfrage.posNotDate(sSpalte,A.dtReg,bAlle,false);
                else if (sBefehl.equals("is String")) // war Bedingung27
                  b=bSpalte && sEingabe!=null ? sEingabe.equals(getMo(1)):A.sReg.equals(getM3b());
                else if (sBefehl.equals("is Sperre")) // Bedingung28
                  b=isSperre(TabAbfrage.getI("aic_bew_pool"));
                else if (sBefehl.equals("use AServer")) // Bedingung29
                  if (Static.bWeb)
                    b=false;
                  else
                    b=sEingabe==null ? AClient.AServerCalc() && AClient.UseAServer(): sEingabe.equals("import") ? AClient.UseAServer2():
                	  sEingabe.equals("backup") ? AClient.UseAServer1(): sEingabe.equals("pdf") ? AClient.UseAServer():false;
                else if (sBefehl.equals("is reCalc")) // Bedingung30
                  b=A.bReCalc;
                else if (sBefehl.equals("is Frame xx")) // Bedingung31
                  b=A.sFrame!=null && A.sFrame.equals(sEingabe);
                else if (sBefehl.equals("pos iqnore Case")) // Bedingung32
                  b=TabAbfrage.posIC(A.TabSpalten.getS("Kennung3"),getS());
                else if (sBefehl.equals("empty stack")) // Bedingung33
                  b=TabAbfrage.Empty_Stack();
                else if (sBefehl.equals("exists")) // Bedingung34
                  b=TabAbfrage.exists(sSpalte) && (A.TabSpalten.getI("bits") & Abfrage.cstUnsichtbar)==0;
                else if (sBefehl.equals("check IBAN")) // Bedingung35
                  b=IBANok(A.g,getM0());
                else if (sBefehl.equals("startsWith")) // Bedingung36
                  b=getMo(1).startsWith(sEingabe);
                else if (sBefehl.equals("calc on AServer")) // Bedingung37
                {
                  if (Static.bWeb)
                    b=false;
                  else
                  {
                  //String sP=sEingabe==null ? sPeriode:sEingabe;  
                  String sM=sEingabe.indexOf(';')>0 ? sEingabe:sEingabe+";"+sPeriode;
                  String sMK=sM.substring(0,sM.indexOf(';'));
                  int iPos=A.g.getPosBegriff("Modell",sMK);
                  if (iPos<0)
                  {
                	 A.g.printError("calc on AServer: Modell ["+sMK+"] nicht vorhanden");
                	 b=false;
                  }
                  else
                	  b=AClient.send_AServer("c:"+sM+";"+DateWOD.Format(A.g,"yyyy-MM-dd HH:mm:ss",A.getVon())+DateWOD.Format(A.g,"yyyy-MM-dd HH:mm:ss",A.getBis())+";"+A.g.getMandant()+";"+A.iQryReg+";"+A.iBewReg,A.g);
                  }
                }
                else if (sBefehl.equals("is Joker"))
                  b=A.iReg == A.g.iJoker;
                else if (sBefehl.equals("is StringJoker"))
                  b=A.sReg.equals(A.g.sJoker);
                else if (sBefehl.equals("is Stichtag"))
                  b=A.dtReg.toDate().equals(A.g.dtStichtag);
                else if (sBefehl.equals("is JokerStt"))
                {
                  int iStt=EtoStt(sEingabe);
                  int iPos=A.g.TabStammtypen.getPos("Aic",iStt>0 ? iStt:bSpalte ? getDatenStt():A.iStt);
                  b=A.iReg == (iPos>=0 ? A.g.getJokerStt(A.g.TabStammtypen.getI(iPos,"Aic"),A.iVB):0); //A.g.TabStammtypen.getI(iPos,"Sync"):0);ö
                }
		else if (sBefehl.equals("< date"))
		{
			b= (bVar ? getdt(sVar)==null || getdt(sVar).isNull(): bSpalte ? TabAbfrage.isNull(sSpalte):A.dtReg == null) ? false:
                            (bVar && !bSpalte ? getdt(sVar).getAbsSeconds():bSpalte ? TabAbfrage.getF(sSpalte):A.dReg)<(bVar && bSpalte ? getdt(sVar):A.dtReg).getAbsSeconds();
			/*if (A.dtReg != null)
			{
				A.g.progInfo("kleiner:"+(A.dtReg.getAbsSeconds()-(bSpalte ? TabAbfrage.getF(sSpalte):A.dReg))+" -> "+b);
				A.g.progInfo("Format: Spalte="+new DateWOD(Math.round((bSpalte ? TabAbfrage.getF(sSpalte):A.dReg)*1000)).Format("yyyy/MM/dd HH:mm:ss.SSS")+", Reg="+A.dtReg.Format("yyyy/MM/dd HH:mm:ss.SSS"));
			}*/
		}
		else if (sBefehl.equals("> date"))
		{
			b= (bVar ? getdt(sVar)==null || getdt(sVar).isNull(): bSpalte ? TabAbfrage.isNull(sSpalte):A.dtReg == null) ? false:
                            (bVar && !bSpalte ? getdt(sVar).getAbsSeconds():bSpalte ? TabAbfrage.getF(sSpalte):A.dReg)>(bVar && bSpalte ? getdt(sVar):A.dtReg).getAbsSeconds();
			/*if (A.dtReg != null)
			{
				A.g.progInfo("größer:"+((bSpalte ? TabAbfrage.getF(sSpalte):A.dReg)-A.dtReg.getAbsSeconds())+" -> "+b);
				A.g.progInfo("Format: Spalte="+new DateWOD(Math.round((bSpalte ? TabAbfrage.getF(sSpalte):A.dReg)*1000)).Format("yyyy/MM/dd HH:mm:ss.SSS")+", Reg="+A.dtReg.Format("yyyy/MM/dd HH:mm:ss.SSS"));
			}*/
		}
		else if (sBefehl.equals("Bedingung66"))
		{
			char c=sEingabe.charAt(0);
			String s=null;
			boolean bTime=false;
			if (c=='T' || c=='D')
			{
				s=sEingabe.substring(1);
				bTime=c=='T';
			}
			else 
			{
				A.printError("Bedingung66: Nur T oder D für 1. Zeichen erlaubt",400);
				s=sEingabe;
			}
			boolean bVar2=false;
			String sVar2=null;
			if (s.length()>1 && s.charAt(1)=='$')
			{
				bVar2=true;
				sVar2=s.substring(2);
			}
			DateWOD dt2=bSpalte ? Sort.getDW(TabAbfrage.getDate(sSpalte)):bVar2 ? getdt(sVar2):getDWE(s.substring(1));
			DateWOD dt=bVar2 && bSpalte ? getdt(sVar2):A.dtReg;
			if (dt==null || dt2==null)
				A.printError("Vergleich mit null nicht erlaubt, vielleicht fehlt ein $ vor Variable "+s.substring(1),400);
			A.g.fixtestError("vergleiche "+(bTime ? "Zeit":"Datum")+": "+dt2+s.charAt(0)+dt);
			b=dt2.compareDay(s.charAt(0),dt,bTime);
		}
		else if (sBefehl.equals("Bedingung67")) // Boolean-Variablen prüfen 
			b=ZerlegeBool(sEingabe);
		else if (sBefehl.equals("Bedingung68")) // Adresse prüfen
			b=GPS.getGPS(A.g,A.StaString,bAlle)!=null;
		else if (sBefehl.equals("Bedingung69")) // prüft ob Ganztag
			b=Ganztag();
		else if (sBefehl.equals("Bedingung70")) // is Thread
			b=A.bThread;
        else if (sBefehl.equals("> von"))
          b= (bSpalte ? TabAbfrage.isNull(sSpalte):A.dtReg == null || A.dtReg.isNull()) ? false:(bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):bVar && !bAR ? getdt(sVar):A.dtReg).getAllSeconds()>(bVar && bAR ? getVonVar(sVar):A.getVon()).getTime()/1000.0;
        else if (sBefehl.equals("< bis"))
          b= (bSpalte ? TabAbfrage.isNull(sSpalte):A.dtReg == null || A.dtReg.isNull()) ? false:(bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):bVar && !bAR ? getdt(sVar):A.dtReg).getAllSeconds()<(bVar && bAR ? getBisVar(sVar):A.getBis()).getTime()/1000.0;
        else if (sBefehl.equals("between"))
          b= (bSpalte ? TabAbfrage.isNull(sSpalte):A.dtReg == null || A.dtReg.isNull()) ? false:(bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):bVar && !bAR ? getdt(sVar):A.dtReg).getAllSeconds()>=A.getVon().getTime()/1000.0 &&
              (bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):bVar && !bAR ? getdt(sVar):A.dtReg).getAllSeconds()<A.getBis().getTime()/1000.0;
                // Tabellen
		else if (sBefehl.equals("end of file"))
                {
                  //if (TabAbfrage.eof3() != TabAbfrage.eof())
                  //  A.g.fixtestInfo("EOF bei "+A.TabModelle.getS("Bezeichnung")+"-"+getZeile()+" anders ("+TabAbfrage.eof3()+")");
                  b = TabAbfrage.eof();
                }
		else if (sBefehl.equals("begin of file"))
                {
                  //if (TabAbfrage.bof3() != TabAbfrage.bof())
                  //  A.g.fixtestInfo("BOF bei " + A.TabModelle.getS("Bezeichnung")+"-"+getZeile()+" anders (" + TabAbfrage.bof3() + ")");
                  b = TabAbfrage.bof();
                }
		else if (sBefehl.equals("out of file"))
			b=TabAbfrage.out();
                else if (sBefehl.equals("is last"))
			b=TabAbfrage.isLast();
		else if (sBefehl.equals("is Empty"))
			b=TabAbfrage.isEmpty();
		else if (sBefehl.equals("pos"))
		{
		  int iAic=bVar ? geti(sVar):A.iReg;
          if (bGroup)
            b=TabAbfrage.posGroup(sSpalte,new Integer(iAic),new Long(iAic),null,false);
          else if (iAic==0)
            b=TabAbfrage.posInhalt(sSpalte,null);
          else
            b=TabAbfrage.posInhalt(sSpalte,iAic);
		}
        else if (sBefehl.equals("pos not aic"))
        {
        	int iAic=bVar ? geti(sVar):A.iReg;
        	if (bGroup)                 
              b=TabAbfrage.posNotGroup(sSpalte, new Integer(iAic), new Long(iAic), null, false);                   
            else 
              b=TabAbfrage.posNotInhalt(sSpalte,iAic==0?null:new Integer(iAic),iAic==0?null:new Long(iAic),true);
        }
        else if (sBefehl.equals("pos next not aic"))
        {
        	int iAic=bVar ? geti(sVar):A.iReg;
        	if (bGroup)                 
              b=TabAbfrage.posNotGroup(sSpalte, new Integer(iAic), new Long(iAic), null, true);                   
            else 
              b=TabAbfrage.posNotInhalt(sSpalte,iAic==0?null:new Integer(iAic),iAic==0?null:new Long(iAic),false);
        }
        else if (sBefehl.equals("> now"))
          b=(bSpalte ? TabAbfrage.isNull(sSpalte):sEingabe==null && (A.dtReg == null || A.dtReg.isNull())) ? false:(bSpalte ? new DateWOD(TabAbfrage.getDate(sSpalte)):getDW(A.dtReg)).getAllSeconds()>SQL.getNow(A.g).getTime()/1000.0;
		else if (sBefehl.equals("pos next"))
		{
			int iAic=bVar ? geti(sVar):A.iReg;
            if (bGroup)
               b=TabAbfrage.posGroup(sSpalte,new Integer(iAic),new Long(iAic),null,true);
            else
               b=TabAbfrage.posNextInhalt(sSpalte,new Integer(iAic),new Long(iAic));
		}
        else if (sBefehl.equals("pos real"))
          if (iBew>0)
            b=TabAbfrage.posInhalt("aic_Bew_Pool",A.iBewReg);
		  else
		    b=TabAbfrage.posInhalt("aic_stamm",bVar ? geti(sVar):A.iReg);
		else if (sBefehl.equals("pos model"))
			b=TabAbfrage.posInhalt(sSpalte,A.iModellBegriff);
		else if (sBefehl.equals("pos String"))
			b=bGroup ? TabAbfrage.posGroup(A.TabSpalten.getS("Kennung3"),getS(),new All_Unlimited.Allgemein.Anzeige.Memo1(getS()),null,false):
                            TabAbfrage.posInhalt(A.TabSpalten.getS("Kennung3"),new All_Unlimited.Allgemein.Anzeige.Memo1(getS()));
		else if (sBefehl.equals("pos next String"))
			b=bGroup ? TabAbfrage.posGroup(sSpalte,getS(),new All_Unlimited.Allgemein.Anzeige.Memo1(getS()),null,true):
                            TabAbfrage.posNextInhalt(A.TabSpalten.getS("Kennung3"),new All_Unlimited.Allgemein.Anzeige.Memo1(getS()));
		else if (sBefehl.equals("pos Date"))
			b=A.dtReg==null || A.dtReg.isNull() ? false:bGroup ? TabAbfrage.posGroup(sSpalte,A.dtReg.toTimestamp(),null,null,false):bAlle ? TabAbfrage.posNextDate(sSpalte,A.dtReg,true):TabAbfrage.posNextDateTime(sSpalte,A.dtReg,true);
		else if (sBefehl.equals("pos next Date"))
			b=A.dtReg==null || A.dtReg.isNull() ? false:
                            bGroup ? TabAbfrage.posGroup(sSpalte,A.dtReg.toTimestamp(),null,null,true):
                            (sEingabe != null && sEingabe.length()>0 ? TabAbfrage.posNextZahl(sSpalte,A.dtReg.getAbsSeconds(),getF()):bAlle ? TabAbfrage.posNextDate(sSpalte,A.dtReg,false):TabAbfrage.posNextDateTime(sSpalte,A.dtReg,false));
        else if (sBefehl.equals("Bedingung54"))	// "pos prev Date")) 
        	b=A.dtReg==null || A.dtReg.isNull() ? false:TabAbfrage.posPrevZahl(sSpalte,A.dtReg.getAbsSeconds(),getF());        
        else if (sBefehl.equals("Bedingung55")) // Berechtigung prüfen
        {
            if (Static.Leer(sEingabe))
            {
        		A.printError("Bedingung55 ohne Eingabe aufgerufen", 400);
        		b=false;
            }
            else
            {
            	String sBG=getBG("Bedingung55","Show");
                if (sBG != null)
                {   	
                	// Static.bInfoTod=true;
                	b=ZerlegeModul(sBG,sEingabe);
//                	A.g.fixtestError("-> "+b);
                	// Static.bInfoTod=false;
                }
	        	else
	        		b=false;
           }
        }
        else if (sBefehl.equals("Bedingung56")) // Veränderung prüfen
        {
        	int iBBits=TabBefehle.getI("MBITS");
        	int iBits= A.TabAbfragen.getI("bits");
        	boolean bKeinZR=(iBits&Abfrage.cstKeinZeitraum)>0 || (iBBits&KBZR)>0 || iBew==0 && (iBBits&KSZR)>0;
        	boolean bZRistGleich = iPeriode==0 || bKeinZR || Static.Gleich(A.getVon(),A.TabAbfragen.getInhalt("von")) && Static.Gleich(A.getBis(),A.TabAbfragen.getInhalt("bis"));
			boolean bStammRefresh = A.iQryReg!=0 && A.iQryReg!=A.TabAbfragen.getI("Stamm") && A.TabAbfragen.getI("Stamm")!=0;
        	b=!bZRistGleich || bStammRefresh;
        }
        else if (sBefehl.equals("Bedingung57")) // prüft ob unsichtbar
        	b=(A.TabSpalten.getI("bits") & Abfrage.cstUnsichtbar)>0;
        else if (sBefehl.equals("Bedingung58")) // positioniert zu anderer Zone
        	b=TabAbfrage.posNotInhalt("zone",TabAbfrage.getInhalt("zone"),false);
        else if (sBefehl.equals("Bedingung59")) // is Zone
        	b=bSpalte ? A.g.hasZone(iBew) && TabAbfrage.exists("Zone") ? TabAbfrage.getI("Zone")==A.iZone:false:A.dtReg==null ? false:A.dtReg.getZone2()==A.iZone;
        else if (sBefehl.equals("Bedingung60")) // pos not null zone
        	b=TabAbfrage.posNull("zone",false);
        else if (sBefehl.equals("Bedingung61")) // has zone
        	b=bSpalte ? A.g.hasZone(iBew) && TabAbfrage.exists("Zone") ? !TabAbfrage.isNull("Zone"):false:A.dtReg==null ? false:A.dtReg.hasZone();
        else if (sBefehl.equals("Bedingung62")) // Lizenz prüfen
        {
        	// Static.bInfoTod=true;
        	if (Static.Leer(sEingabe))
        	{
        		A.printError("Bedingung62 ohne Eingabe aufgerufen", 400);
        		b=false;
        	}
        	else
        	{
        		String sBG=getBG("Bedingung62","Show");  
        		if (sBG != null)
	        	{
		            int iPos=A.g.getPosBegriff(sBG,sEingabe);
		            b=iPos<0 ? false:A.g.Lizenz(iPos);
	        	}
	        	else
	        		b=false;
        	// Static.bInfoTod=false;
        	}
        }
        else if (sBefehl.equals("Bedingung63")) // Spalte verändert
        {
        	if (iBew>0)
        	{
        		String sDt=A.TabSpalten.getS("Datentyp");
        		int iP=TabAbfrage.getI("aic_bew_pool");
        		int iV=SQL.getInteger(A.g, "select bew_aic_bew_pool from bew_pool where aic_bew_pool=?", 0, ""+iP);
//        		A.g.fixtestError("Bedingung63:"+sDt+"/"+iV+"/"+iP);
        		if (sDt.equals("BewDatum2"))
        		{
        			Vector<String> VecS=SQL.getSVector("select von from bew_von_bis where aic_eigenschaft="+A.TabSpalten.getI("Eig")+" and aic_bew_pool in ("+iP+","+iV+")",A.g);
        			b=VecS.size()==2 && !Static.Gleich(VecS.elementAt(0), VecS.elementAt(1));
        		}
        		else if (sDt.equals("BewStamm"))
        		{
        			Vector<Integer> VecI=SQL.getVector("select aic_stamm from bew_stamm where aic_eigenschaft="+A.TabSpalten.getI("Eig")+" and aic_bew_pool in ("+iP+","+iV+")",A.g);
        			b=VecI.size()==2 && !Static.Gleich(VecI.elementAt(0), VecI.elementAt(1));
        		}
        		else
        		{
        			A.printError("Bedingung63: Datentyp "+sDt+" wird noch nicht unterstützt",501);
        			b=false;
        		}
        	}
        	else if (A.ProtIsNull())
        		b=false;
        	else
        		b=SQL.exists(A.g, "select aic_stamm from stammpool where aic_protokoll="+A.getProt()+" and aic_eigenschaft="+A.TabSpalten.getI("Eig"));
        }
 		else if (sBefehl.equals("pos number"))
			b=bGroup ? TabAbfrage.posGroup(sSpalte,new Double(getF()),null,null,false):TabAbfrage.posInhalt(sSpalte,getF(),-1);
		else if (sBefehl.equals("pos next number"))
			b=bGroup ? TabAbfrage.posGroup(sSpalte,new Double(getF()),null,null,true):TabAbfrage.posNextInhalt(sSpalte,getF());
        else if (sBefehl.equals("pos true"))
        	b=bGroup ? TabAbfrage.posGroup(sSpalte,Boolean.TRUE,new Double(1.0),new Integer(1),false):TabAbfrage.posTrue(sSpalte);
        else if (sBefehl.equals("Bedingung71")) // "pos false"
        	b=bGroup ? TabAbfrage.posGroup(sSpalte,Boolean.FALSE,new Double(0.0),new Integer(0),false):TabAbfrage.posFalse(sSpalte);
        else if (sBefehl.equals("Bedingung72")) // "pos next false"
        	b=bGroup ? TabAbfrage.posGroup(sSpalte,Boolean.FALSE,new Double(0.0),0,true):
                    TabAbfrage.posNextInhalt3(sSpalte,Boolean.FALSE,new Double(0.0),0);
        else if (sBefehl.equals("Bedingung73")) // is Fehler
        	b=iFC>0;
        else if (sBefehl.equals("Bedingung74")) // is new
        	b=bSpalte ? TabAbfrage.out() ? false:TabAbfrage.getI(iBew==0 ? "aic_stamm":"aic_bew_pool")==0:false;
        else if (sBefehl.equals("pos next true"))
        	b=bGroup ? TabAbfrage.posGroup(sSpalte,Boolean.TRUE,new Double(1.0),1,true):
                    TabAbfrage.posNextInhalt3(sSpalte,Boolean.TRUE,new Double(1.0),1);
        else if (sBefehl.equals("pos null"))
                b=bGroup ? TabAbfrage.posGroup(sSpalte,null,null,null,false):TabAbfrage.posInhalt(sSpalte,null);
        else if (sBefehl.equals("pos not null"))
                b=bGroup ? TabAbfrage.posGroupNotNull(sSpalte,false):TabAbfrage.posNull(sSpalte,false);
                else if (sBefehl.equals("pos Bool3"))
                {
                  int iAic=A.g.getAuswahlAic(A.g.getAuswahlPos(sEingabe, A.TabSpalten.getI("Eig")));
                  if (A.TabSpalten.getS("Datentyp").equals("BewBool3"))
                    b = TabAbfrage.posInhalt(sSpalte, iAic);
                  else
                    b = TabAbfrage.posInhalt(sSpalte, new Double(iAic));
                }
                else if (sBefehl.equals("pos next Bool3"))
                {
                  int iAic = A.g.getAuswahlAic(A.g.getAuswahlPos(sEingabe, A.TabSpalten.getI("Eig")));
                  b = TabAbfrage.posNextInhalt(sSpalte, iAic,new Double(iAic));
                }
		else if (sBefehl.equals("= DateTime"))
		{
			//A.g.progInfo("Format: Spalte="+(bSpalte ? new DateWOD(TabAbfrage.getTimestamp(sSpalte)) : new DateWOD(Math.round(A.dReg*1000))).Format("yyyy/MM/dd HH:mm:ss.SSS")+", Reg="+A.dtReg.Format("yyyy/MM/dd HH:mm:ss.SSS"));
			b=bVar ? getdt(sVar).getAbsSeconds()==(bSpalte ? TabAbfrage.getF(sSpalte):A.dtReg.getAbsSeconds()):
                            bSpalte ? Static.Gleich2(TabAbfrage.getInhalt(sSpalte),A.dtReg.toTimestamp()):A.dReg==A.dtReg.getAbsSeconds();
		}
        else if (sBefehl.equals("= Date"))
        {
          DateWOD dtx=bSpalte && bVar ? getdt(sVar):A.dtReg;
          if (dtx == null || dtx.isNull())
            b=false;
          else
          {
            DateWOD dt2=bSpalte ? TabAbfrage.isNull(sSpalte)?null:new DateWOD(TabAbfrage.getDate(sSpalte)):bVar ? getdt(sVar):A.dtReg2;
            if (dt2 == null || dt2.isNull())
              b=false;
            else
            {
                DateWOD dt1=new DateWOD(dtx.getTime());
                dt1.setTimeZero();
                dt2.setTimeZero();
                b=dt1.equals(dt2);
            }
          }
        }
		// Datei
		else if (sBefehl.equals("file exists"))
			b=new File(getM3()).exists();
		else if (sBefehl.equals("read file"))
		{
			File fil = new File(A.sReg);
			//File fil2= new File(Static.FehlerVerzeichnis+new java.text.SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new java.util.Date())+".prot");
			//b=fil.renameTo(fil2);
			b=fil.exists();
			if (b)
				TabAbfrage.CalcImport(getTrennzeichnen(),fil,A.TabSpalten,bTitel && (A.TabAbfragen.getI("bits")&Abfrage.cstKeinTitel)==0);
		}
		// Sonst
                else if (sBefehl.equals("= Mandant"))
                  b = bSpalte ? A.iMReg == TabAbfrage.getI(sSpalte):VecM==null ? !A.bM_change:A.iMReg == A.g.getMandant();
                else if (sBefehl.equals("next Mandant")) // war bis 5.13.15 "Bedingung42"
                {
                  iPosM++;
                  if (VecM==null || VecM.size()==0)
                    A.iMReg = iPosM>0 ? -1:A.g.getMandant();
                  else
                  {
                    while (iPosM < VecM.size() && !A.g.isWriteMandant(VecM.elementAt(iPosM)))
                      iPosM++;
                    A.iMReg = iPosM < VecM.size() ? VecM.elementAt(iPosM) : -1;
                  }
                  b=A.iMReg>0;
                }
//                else if (sBefehl.equals("check bitsbew")) // war Bedingung43
//                	b=(SQL.getInteger(A.g, "select bitsbew from bew_pool where aic_bew_pool=?",0,TabAbfrage.getS("aic_bew_pool"))&getI())==getI();
                else if (sBefehl.equals("is JavaFX")) // is Web
                	b=Static.bWeb;//A.FomFX != null;
		else if (sBefehl.equals("= String"))
                {
                  String s=bSpalte ? TabAbfrage.out() ? "!out!":TabAbfrage.isNull(A.TabSpalten.getS("Kennung3"))?"":TabAbfrage.getM(A.TabSpalten.getS("Kennung3")):A.sReg;
                  b = bVar ? gets(sVar).equals(s):sEingabe != null ? sEingabe.equals(s):A.sReg.equals(bSpalte ? s:A.sReg2);
                  /*String s=bSpalte ? TabAbfrage.out() ? "!out!":TabAbfrage.getM(A.TabSpalten.getS("Kennung3")):sEingabe;
                  b = bVar ? gets(sVar).equals(s): A.sReg.equals(s);*/
                }
		else if (sBefehl.equals("is Timer"))
			b=A.bThread || A.bTimer && !Static.bWeb;
        else if (sBefehl.equals("is manual"))
          b = (sEingabe != null ? Static.AicToVec(A.g.getBegriffAicS("Anlage", sEingabe)): getVecManual(A.g)).contains(new Integer(bSpalte ? TabAbfrage.getI(sSpalte):A.iReg));
        else if (sBefehl.equals("pos manual"))
          b = TabAbfrage.posInhalt2(sSpalte,sEingabe != null ? Static.AicToVec(A.g.getBegriffAicS("Anlage", sEingabe)):getVecManual(A.g));
		else if (sBefehl.equals("FTP-Error"))
			b=A.ftp != null && A.ftp.fehler();
		else if (sBefehl.equals("SMTP-Error"))
			b=A.smtp != null && A.smtp.fehler();
                else if (sBefehl.equals("IO-Error"))
			b= bSpalte ? TabAbfrage.bFileError:A.bIO_Error;
                else if (sBefehl.equals("IntusCom error"))
			b= A.IC==null || A.IC.bFehler;
                else if (sBefehl.equals("Gauge Abbruch"))
                  b=A.in == null && A.bGau && (A.bThread ? A.g.bThreadEscape : A.Gau==null || A.Gau.bEscape);
                else if (sBefehl.equals("has deputy"))
                      b=getDeputy(TabAbfrage.getI("aic_stamm"),TabAbfrage.getI(sSpalte))>0;
                else if (sBefehl.equals("question"))
                {
                  long lClock2=Static.get_ms();               
                  if (Static.bWeb)
                  {
                	  if (A.iVB==0 && !Static.bAll || (iMBits&Global.cstWeiter)==0)
                	  {
                		  A.debugInfo("keine question bei Web:"+getMemo());
                    	  b=true;
                	  }
                	  else if (bQ)
                	  {
                		  b=bJa;
                		  bQ=false;
                		  A.debugInfo("Antwort von Web:"+b);
                	  }
                	  else
                	  {
	                	  bQ=true;
	                	  A.sHeader="Modellfrage";
	                	  A.sInfo=getMemo();
	                	  b=true;
                	  }
                  }
                  else if (A.bTimer)
                    A.printError(MPos()+"question bei Timer nicht möglich!",501);
                  else if (A.in != null)
                    b=AClient.sendMsg(false,"Modellfrage",getMemo(),A.in,A.out);
//                  else if (A.FomFX != null)
//                    b=MessageFX.Frage(A.g,A.FomFX,"Modellfrage",new String[] {getMemo()});
                  else
                    b=new Message(Message.YES_NO_OPTION,A.FomA,A.g).showDialog("Modellfrage",new String[] {getMemo()}) == Message.YES_OPTION;
                  A.lClockDlg+=Static.get_ms()-lClock2;
                }
                else if (sBefehl.equals("was absent"))
                {
                  b=A.g.TabAustritt.posNextInhalt("aic_stamm",new Integer(A.iQryReg),new Long(A.iQryReg));
                  if (b)
                  {
                    A.dtReg=A.g.TabAustritt.isNull("EIN")?null:new DateWOD(A.g.TabAustritt.getDate("EIN"));
                    A.dtReg2=A.g.TabAustritt.isNull("AUS")?null:new DateWOD(A.g.TabAustritt.getDate("AUS"));
                  }
                }
                else if (sBefehl.equals("Modell_Dialog"))
                {
                	long lClock2=Static.get_ms();
                  if (TabAbfrage.size()==0)
                    TabAbfrage.newLine();
                  iAbfrageMD=A.TabSpalten.getI("Abfrage");
                  for (int iPos=A.TabSpalten.getPos("Abfrage",iAbfrageMD);iPos<A.TabSpalten.size() && A.TabSpalten.getI(iPos,"Abfrage")==iAbfrageMD;iPos++)
                    if (A.TabSpalten.getI(iPos,"Filter")>0)
                    {
                      ErmittleSpalte(0,A.TabSpalten.getI(iPos,"Filter"));
                      Tabellenspeicher Tab=(Tabellenspeicher)A.TabAbfragen.getInhalt("Abfrage1",A.TabAbfragen.getPos("Aic",A.TabSpalten.getI(iPos,"Filter")));
                      A.TabSpalten.setInhalt(iPos, "Vec", Tab.getVecSpalte("AIC_Stamm"));
                    }
                  if (Static.bWeb)
                  {
//                	  A.g.fixtestError("Modell-Dialog im web für "+A.TabModelle.getS("Bezeichnung"));
//                	  A.g.fixtestError("Modell_Dialog Tab="+A.TabMD+", iVB="+A.iVB+", All="+Static.bAll+", weiter="+(iMBits&Global.cstWeiter));
                	  if (A.TabMD==null)
                		  if (A.iVB==0 && !Static.bAll || (iMBits&Global.cstWeiter)==0)
                		  {
                			  A.g.fixtestError("Modell "+A.TabModelle.getS("Bezeichnung")+": kein TabMD für Modelldialog übergeben");
                    		  A.iMsgArt=2;
                      		  A.sInfo="keine Daten für Modell-Dialog übergeben";
                      		  b=false;
                		  }
                		  else
                		  {
                			  bMD=true;
                			  b=true;
                		  }
                	  else
                	  {
                		  fillModellDialog();
                		  b=true;
                	  }
                  }
                  else if (A.bTimer)
                    A.printError(MPos()+"Modell_Dialog bei Timer nicht möglich!",501);
                  else if (A.in != null)
                    b = AClient.sendDlg(A.g,A.sReg, (int)A.dReg, TabAbfrage, A.TabSpalten, A.in, A.out);
//                  else if (A.FomFX != null)
//                	b = new Modell_DialogFX(A.FomFX,A.g,A.sReg,TabAbfrage, A.TabSpalten).bOk;
                  else
                  {
//                	  Cursor C=null;
//                	  if (A.FomA != null)
//                	  {
//                		  C=A.FomA.getCursor();
//                		  //A.g.fixtestError("Fom="+A.FomA+", Cursor="+C);
//                	  }
                    b = MD_Aufruf();//new Modell_Dialog(A.FomA, A.g, A.sReg, (int)A.dReg, A.iQryReg, TabAbfrage, A.TabSpalten).bOk;
//                    if (C != null)
//                    {
//              		  A.g.fixtestError("Cursor2="+A.FomA.getCursor());
//                    if (A.FomA != null)
//                    {
//                    	A.FomA.requestFocusInWindow();
//              		  A.FomA.setCursor(new Cursor(Cursor.WAIT_CURSOR));
//                    }
//                    }
                  }
                  A.lClockDlg+=Static.get_ms()-lClock2;
                }
                else if (sBefehl.equals("path exists")) // Path exists
                {
                  //File Fil=new File(getM3()).getParentFile();
                  //A.debugInfo("Path="+Fil);
                  File Fil=new File(getM3());
                  if (Fil != null)
                    Fil=Fil.getParentFile();
                  b = Fil==null ? false:Fil.exists();
                }
                else if (sBefehl.equals("is first row")) // is first
                  b=TabAbfrage.getPos()==0;
                else if (sBefehl.equals("is last row"))  // Bedingung38
                  b=TabAbfrage.getPos()==TabAbfrage.size()-1;
                else if (sBefehl.equals("Zeilen=")) // Bedingung39
                  b=TabAbfrage.size()== getI();
                else if (sBefehl.equals("Pos=")) // Bedingung40
                  b=TabAbfrage.getPos()==getI();
                else if (sBefehl.equals("set Pos")) // Bedingung41
                  b=TabAbfrage.setPos(getI());
                else if (sBefehl.equals("question2")) // Bedingung13 
                	if (Static.bWeb)
                    {
                	  if (A.iVB==0 && !Static.bAll || (iMBits&Global.cstWeiter)==0)
                  	  {
                  		  A.debugInfo("keine question2 bei Web:"+sEingabe);
                      	  b=true;
                  	  }
                  	  else if (bQ)
                  	  {
                  		  b=bJa;
                  		  bQ=false;
                  		  A.debugInfo("Antwort von Web:"+b);
                  	  }
                  	  else
                  	  {
	                  	  bQ=true;
	                  	  int iAic=A.g.getBegriffAicS("Fehlermeldung",sEingabe);
	                  	  //VecMemo=A.g.getMemoVector("Begriff", iAIC);
	                  	  Vector<String> Vec=iAic>0 ? A.g.getMemoVector("Begriff", iAic):null;
	                  	  A.sInfo=Vec != null ? Vec.elementAt(1):sEingabe;
	                  	  A.sHeader=Vec != null ? Sort.gets(Vec, 0):"Modellfrage2";
	                  	  b=true;
                  	  }
                    }
                	else if (A.bTimer)
	                    A.printError(MPos()+"question2 bei Timer nicht möglich!",501);
	                else if (A.in != null)
	                    b=AClient.sendMsg(true,sEingabe,null,A.in,A.out);
//                  else if (A.FomFX != null)
//                  {
//                	b=MessageFX.Frage(A.g, A.FomFX, true, sEingabe, null,null);
//                    //MessageFX Msg2=new MessageFX(MessageFX.YES_NO_OPTION,A.FomFX,A.g);
//                    //Msg2.sGruppe="Fehlermeldung";
//                    //b=Msg2.showDialog(sEingabe) == MessageFX.YES_OPTION;
//
//                    //b = new Message(Message.YES_NO_OPTION, A.FomA, A.g).showDialog("Modellfrage", new String[] {getM()}) == Message.YES_OPTION;
//                  }
                  else
                  {
                    Message Msg2=new Message(Message.YES_NO_OPTION,A.FomA,A.g);
                    Msg2.sGruppe="Fehlermeldung";
                    b=Msg2.showDialog(sEingabe) == Message.YES_OPTION;

                    //b = new Message(Message.YES_NO_OPTION, A.FomA, A.g).showDialog("Modellfrage", new String[] {getM()}) == Message.YES_OPTION;
                  }
                else if (sBefehl.equals("Bedingung45")) // StaString has Element
                	b=A.StaString != null && !A.StaString.isEmpty();
                else if (sBefehl.equals("between2")) // Bedingung14 // Jahrestag zwischen von und bis
                {
                  int iDoY=bSpalte ? TabAbfrage.isNull(sSpalte) ? 0:new DateWOD(TabAbfrage.getDate(sSpalte)).get(DateWOD.DAY_OF_YEAR):
                        A.dtReg == null || A.dtReg.isNull() ? 0:A.dtReg.get(DateWOD.DAY_OF_YEAR);
                  b = iDoY>0 && iDoY>=new DateWOD(A.getVon()).getYearDay(false) && iDoY<=new DateWOD(A.getBis()).getYearDay(true);
                }
                else if (sBefehl.equals("between3")) // Bedingung auf von-bis von Spalte mit Datumsregister
                {
                  if (bVar)
                    b= (A.dtReg == null || A.dtReg.isNull()) ? false:A.dtReg.getAllSeconds()>=getVonVar(sVar).getTime()/1000.0 && A.dtReg.getAllSeconds()<getBisVar(sVar).getTime()/1000.0;
                  else
                    b= (TabAbfrage.isNull(sSpalte) || A.dtReg == null || A.dtReg.isNull()) ? false:
                      A.dtReg.getAllSeconds()>=new DateWOD(TabAbfrage.getDate(sSpalte)).getAllSeconds() && A.dtReg.getAllSeconds()<new DateWOD(TabAbfrage.getDate(A.TabSpalten.getS("Kennung2"))).getAllSeconds();
                }
                else if (sBefehl.equals("holiday between")) // Bedingung15 // Feiertag zwischen von und bis
                {
                  DateWOD dw=new DateWOD(A.getVon());
                  dw.setTimeZero();
                  long lBis=A.getBis().getTime();
                  b=false;
                  while (!b && dw.getTimeInMilli()<lBis)
                  {
                    String s=A.g.Feiertag(dw,A.iQryReg);
                    b=!s.equals("");
                    A.g.progInfo(dw+":"+s+"/"+b);
                    dw.tomorrow();
                  }
                }
                else if (sBefehl.equals("is manual2")) // Bedingung16
                {
                  getVecManual(A.g);
                  String s=TabAbfrage.getS("q"+sSpalte.substring(1));
                  A.g.defInfo("Anlage="+s);
                  b = VecManualS.contains(s);
                }
                else if (sBefehl.equals("is reentry")) // Bedingung17
                {
                  Date dt=SQL.getTimestamp(A.g,"select eintritt from stamm_protokoll where aic_protokoll="+A.getProt());
                  if (dt != null && !SQL.exists(A.g,"select aic_stamm from stamm_protokoll where pro_aic_protokoll="+A.getProt()))
                    b=SQL.exists(A.g,"select aic_stamm from stamm_protokoll where weg="+A.g.DateTimeToString(dt));
                  else
                    b=false;
                }
                else if (sBefehl.equals("question3")) // Bedingung18
                 if (A.bTimer || Static.bWeb)
                   A.printError(MPos()+"question3 bei "+(Static.bWeb ?"Web":"Timer")+" nicht möglich!",501);
                 else if (A.in != null)
                    b=AClient.sendMsg(true,sEingabe,A.sReg,A.in,A.out);
//                 else if (A.FomFX != null)
//                 {
//                   b=MessageFX.Frage(A.g, A.FomFX, true, sEingabe, new String[] {A.sReg},null);
//                   //MessageFX Msg2=new MessageFX(MessageFX.YES_NO_OPTION,A.FomFX,A.g);
//                   //Msg2.sGruppe="Fehlermeldung";
//                   //b=Msg2.showDialog(sEingabe,new String[] {A.sReg}) == MessageFX.YES_OPTION;
//                 }
                 else
                 {
                   Message Msg2=new Message(Message.YES_NO_OPTION,A.FomA,A.g);
                   Msg2.sGruppe="Fehlermeldung";
                   b=Msg2.showDialog(sEingabe,new String[] {A.sReg}) == Message.YES_OPTION;
                 }
                else if (sBefehl.equals("is red")) // Bedingung19
                  b=TabAbfrage.getI(sSpalte)==A.g.iLight_Red;
                else if (sBefehl.equals("is yellow")) // Bedingung20
                  b=TabAbfrage.getI(sSpalte)==A.g.iLight_Yellow;
                else if (sBefehl.equals("is green")) // Bedingung21
                  b=TabAbfrage.getI(sSpalte)==A.g.iLight_Green;
                else if (sBefehl.equals("= von")) // Bedingung22
                  b=SameDate(new DateWOD(bVar ? getVonVar(sVar):A.getVon()),bSpalte ? TabAbfrage.isNull(sSpalte)? null:new DateWOD(TabAbfrage.getDate(sSpalte)):getDW(A.dtReg));
                //else if (sBefehl.equals("Bedingung23")) // = bis
                //  b=SameDate(new DateWOD(A.g.getBis()),bSpalte ? TabAbfrage.isNull(sSpalte)?null:new DateWOD(TabAbfrage.getDate(sSpalte)):A.dtReg);
                else if (sBefehl.equals("Bedingung47")) // is Bew
                	b=bBew;
                else if (sBefehl.equals("Bedingung48"))
                	b=posVar(sVar,true);
                else if (sBefehl.equals("Bedingung49"))
                {
                  if (!bSpalte || TabAbfrage.out() || TabAbfrage.isNull(sSpalte))
                  {
                    String s=!bSpalte ? "Kein Spalte angegeben":TabAbfrage.out() ? "Keine Daten vorhanden":"Datum ist null";
                    A.g.fixtestError("Bedingung49: "+s+" bei "+A.TabAbfragen.getS("Bezeichnung")+" bei Zeile "+getZeile()+" in Modell "+A.TabModelle.getS("Bezeichnung"));
                    A.debugInfo("Bedingung49: "+s);
                    b=true;
                  }
                  else
                  {
                	  Date dt=TabAbfrage.getDate(sSpalte);
                	  String sD=new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss",A.g.DFS).format(dt);
                	  b=sD.substring(11).equals("00:00:00");
                  }
                }
                else if (sBefehl.equals("Bedingung50")) // web is leer
                	b=Static.Leer(Static.sWeb);
                else if (sBefehl.equals("Bedingung51")) // user has end
                	b=SQL.exists(A.g, "select aic_benutzer from benutzer where geloescht is null and aic_stamm="+A.iQryReg+" and use_bis is not null");
                else if (sBefehl.equals("Bedingung52")) // pos größer
                	b=TabAbfrage.posBigger(sSpalte,getDW(A.dtReg));
                else if (sBefehl.equals("Bedingung53")) // ImportM..prüft ob Stempelimport mit Mandant durchgeführt werden soll
                	b=Static.bWeb ? false:AClient.bImportM;
                else if (sBefehl.equals("import-error")) // Bedingung23
                  b=sEingabe == null ? A.Imp.iFehler>0:A.Imp.iFehler==Sort.geti(sEingabe);
		// A.g.exec("update benutzer set use_bis="+(A.dtReg==null || A.dtReg.isNull() ? "null":A.g.SQL_Format(A.dtReg.toTimestamp()))
		//  +" where geloescht is null and aic_stamm="+(bSpalte ? TabAbfrage.getI("aic_stamm"):A.iQryReg));// Benutzer löschen
                else if (sBefehl.equals("= Kennzeichen"))
                  b=sEingabe != null && A.g.sKennzeichen != null && A.g.sKennzeichen.equals(sEingabe);
		else
                  bFehler= true;
		if (bMD || bQ)
			return false;
		if (bFehler)
			A.printError(MPos()+"Die Bedingung >"+sBefehl+"< steht nicht zur Verfügung",501);
		else
		{
			int iBefehl=TabBefehle.getI("AIC_BEFEHL");
			if((TabBefehle.getI("MBITS")&SCHLEIFE)>0 && !VecWhile.contains(iBefehl))
				VecWhile.addElement(iBefehl); //iWhileBefehl=iBefehl;
			TabBefehle.push();
			if (b)
			{
				A.debugInfo("Ja-Teil wurde aufgerufen");
				if(TabBefehle.posInhalt("BEF_AIC_BEFEHL",iBefehl))
				{
					iJa = iBefehl;
					iNein =0;
				}
				else
				{
					TabBefehle.pop();
					return true;
				}
			}
			else
			{
				A.debugInfo("Nein-Teil wurde aufgerufen");
				if(TabBefehle.posInhalt("BEF2_AIC_BEFEHL",iBefehl))
				{
					iJa = 0;
					iNein = iBefehl;
				}
				else
				{
					TabBefehle.pop();
					return true;
				}
			}
//			A.g.fixtestError("Ja="+iJa+", Nein="+iNein);
		}
		return false;
	}

        public static boolean IBANok(Global g,String s)
        {
          if (s==null || s.equals(""))
          {
            g.fixtestInfo("IBAN leer");
            return true;
          }
          if (s.indexOf(" ")>-1)
          {
            g.fixtestInfo("IBAN "+s+" enthält Leerzeichen");
            return false;
          }
          if (s.length()<15)
          {
            g.fixtestInfo("IBAN " + s + " zu kurz");
            return false;
          }
          boolean b=true;
          if (s.startsWith("AT"))
            b=s.length()==20;
          else if (s.startsWith("DE"))
            b=s.length()==22;
          if (b)
          {
            if (!(Character.isUpperCase(s.charAt(0)) && Character.isUpperCase(s.charAt(1)) && Character.isDigit(s.charAt(2)) && Character.isDigit(s.charAt(3))))
            {
              g.fixtestInfo("IBAN "+s+" erste Zeichen falsch");
              return false;
            }
            if (!s.startsWith("AT") && !s.startsWith("DE"))
            {
              g.fixInfo("IBAN "+s+" wird nicht geprüft");
              return true;
            }
            s=s.substring(4)+(s.charAt(0)-'A'+10)+(s.charAt(1)-'A'+10)+s.substring(2,4);
            try
            {
              BigInteger BI = new BigInteger(s);
              int iMod = BI.mod(new BigInteger("97")).intValue();
              g.fixtestInfo("BI=" + BI + ", mod=" + iMod);
              return iMod == 1;
            }
            catch(Exception e)
            {
              g.fixtestInfo("IBAN "+s+" nicht in Zahl umwandelbar");
              return false;
            }
          }
          else
          {
            g.fixtestInfo("IBAN "+s+" Länge falsch");
            return false;
          }
        }
        
        public Tabellenspeicher getTabVar()
        {
        	return A.TabVar;
        }

        private String getAllVar()
        {
          String s=null;
          if (A.TabVar!=null)
            for (int i=0;i<A.TabVar.size();i++)
              if (i==0)
                s=A.TabVar.getS(i,"Var")+"="+A.TabVar.getS(i,"Wert");
              else
                s+=", "+A.TabVar.getS(i,"Var")+"="+A.TabVar.getS(i,"Wert");
          return s;
        }
        
        private static Tabellenspeicher newTabVar(Global g,boolean bGlobal)
        {
        	return new Tabellenspeicher(g,bGlobal ? new String[] {"Var","Wert","Stt","Art","perm","Logging","ID","Nr","Date","VarO","WertO","delDate","delArt","delModell"}:new String[] {"Var","Wert","Stt","Art","perm","Logging","Global"});
        }
        
//        private boolean posVar(String sVar) //TODO nur vorübergehend
//        {
//        	return posVar(sVar,true);
//        }

        private boolean posVar(String sVar,boolean bAn)
        {
          if (Static.Leer(sVar))
          {
        	  A.debugInfo("posVar ohne Variable aufgerufen");
        	  return false;
          }
          if (A.TabVar==null) A.TabVar = newTabVar(A.g,false);
          if (A.TabVar.posInhalt("Var",sVar))
          {
        	  if ((A.iDBits&DVAR)>0)
        		  A.debugInfo("posVar "+sVar+": "+A.TabVar.getS("Wert")+" (Art="+iVArt2+")");
        	  return iVArt2 !=3 || A.TabVar.getI("Logging")==A.g.getLog();
          }
          else if (bAn)
          {
            A.TabVar.newLine();
            A.TabVar.setInhalt("Var",sVar);
            A.TabVar.setInhalt("Art",iVArt2);
            if (bPerm)
            	A.TabVar.setInhalt("perm","x");
            A.TabVar.setInhalt("Logging",A.g.getLog());
            if ((A.iDBits&DVAR)>0)
            	A.debugInfo("posVar "+sVar+": <neu> (Art="+iVArt2+")");
            return true;
          }
          else
        	  return false;

        }
        
        private boolean setAll(String sVar,Object Obj)
        {     	
        	boolean b=posVar(sVar,true);
        	if (b)
        	{
	            A.TabVar.setInhalt("Wert",Obj);
	            A.TabVar.setInhalt("Global",null);
	            if (sVar.startsWith("@"))
	            	A.g.setVar(sVar, Obj);
	            	if (webLog.bVarInfo)
	            		A.g.fixtestError("<-setAll "+sVar+":"+Obj+" bei Art="+A.TabVar.getI("Art")+", user="+A.TabVar.getI("Logging"));
        	}
            return b;
        }

        private void set(String sVar,double d)
        {
          A.g.progInfo("setd:"+sVar+"="+d);
          setAll(sVar,new Double(Static.Round6(d)));
        }

        private void set(String sVar,String s)
        {
        	setAll(sVar,s);
        }
        
        private void setGPS(String sVar,GPS gps)
        {
        	setAll(sVar,gps);
        }

        private void set(String sVar,int iAic,int iStt)
        {
          setAll(sVar,iAic==0 ? null:new Integer(iAic));
          A.TabVar.setInhalt("Stt",new Integer(iStt));
        }

        private void set(String sVar,boolean b)
        {
        	setAll(sVar,b);
        }

        private void setVB(String sVar,Timestamp dtVon,Timestamp dtBis)
        {
        	setAll(sVar,new VonBis(A.g,dtVon,dtBis,"dd.MM.yyyy HH:mm"));
        }

        private void setVB(String sVar,Timestamp dt,boolean bVon)
        {
          if (posVar(sVar,true))
          {
          VonBis VB=(VonBis)A.TabVar.getInhalt("Wert");
          if (bVon)
            if (VB instanceof VonBis)
              VB.setVon(dt);
            else
              VB = new VonBis(A.g,dt, null, "dd.MM.yyyy HH:mm");
          else
            if (VB instanceof VonBis)
              VB.setBis(dt);
            else
              VB = new VonBis(A.g,null, dt, "dd.MM.yyyy HH:mm");

          A.TabVar.setInhalt("Wert",VB);
         }
        }
        
        private void setVec(String sVar,Stack Vec)
        {
        	setAll(sVar,Vec);
        }

        private void set(String sVar,DateWOD dt)
        {
        	setAll(sVar, dt);
        }

        /*private void setB(String sVar,Timestamp dtBis)
        {
          posVar(sVar);
          VonBis VB=(VonBis)A.TabVar.getInhalt("Wert");
          if (VB instanceof VonBis)
            VB.setBis(dtBis);
          else
            VB=new VonBis(null,dtBis,"dd.MM.yyyy HH:mm");
            A.TabVar.setInhalt("Wert",VB);
        }*/

        private double getd(String sVar)
        {
          if (posVar(sVar,false))
          {
          double d=A.TabVar.getF("Wert");
          A.g.progInfo("getd:"+sVar+"="+d);
          return d;
        }
          else
        	  return 0.0;
        }

        private String gets(String sVar)
        {
          if (posVar(sVar,false))
            return A.TabVar.getS("Wert");
          else
        	  return "";
        }

        private Object getObj(String sVar)
        {
          if (posVar(sVar,false))
            return A.TabVar.getInhalt("Wert");
          else
        	  return null;
        }

        private int geti(String sVar)
        {
          if (posVar(sVar,false))
            return A.TabVar.getI("Wert");
          else
        	  return 0;
        }

        private boolean getb(String sVar)
        {
          if (posVar(sVar,false))
            return A.TabVar.getB("Wert");
          else
        	  return false;
        }

        private int getStt(String sVar)
        {
          if (posVar(sVar,false))
            return A.TabVar.getI("Stt");
          else
        	  return 0;

        }

        private Timestamp getVonVar(String sVar)
        {
           if (posVar(sVar,false))
             return ((VonBis)A.TabVar.getInhalt("Wert")).getVon();
           else
        		return null;
        }

        private Timestamp getBisVar(String sVar)
        {
           if (posVar(sVar,false))
          return ((VonBis)A.TabVar.getInhalt("Wert")).getBis();
        	else
        		return null;
        }
        
        private VonBis getVBVar(String sVar)
        {
        	if (posVar(sVar,false))
          return (VonBis)A.TabVar.getInhalt("Wert");
        	else
        		return null;
        }
        
        private GPS getGPSVar(String sVar)
        {
        	if (posVar(sVar,false))
          return (GPS)A.TabVar.getInhalt("Wert");
        	else
        		return null;
        }
       

        private DateWOD getdt(String sVar)
        {
        	if (posVar(sVar,false))
          return (DateWOD)A.TabVar.getInhalt("Wert");
        	else
        		return null;
        }
        
        private Stack getVec(String sVar)
        {

        	Stack Sta=new Stack<Integer>();
        	if (posVar(sVar,false))
        	{
        	Vector Vec=(Vector)A.TabVar.getInhalt("Wert");
        	if (Vec!=null)
	            for (int i=0;i<Vec.size();i++)
	              Sta.push(Sort.getInt(Vec,i));
        	}
        	return Sta;
        }

}

