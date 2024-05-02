/*
    All_Unlimited-Allgemein-Transact.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement ;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import All_Unlimited.Allgemein.Anzeige.Memo1;
import All_Unlimited.Hauptmaske.Calc;

import java.security.MessageDigest;
import java.math.BigInteger;
import java.net.InetAddress;
import java.io.*;

public class Transact
{
    // add your data members here
    public static final int INFO1=   0x0001;	// 00
    public static final int INFO2=   0x0002;	// 01
    public static final int INFO3=   0x0004;	// 02
    public static final int EXEC =   0x0008;	// 03
    public static final int FEHLENDE=0x0010;	// 04
    public static final int INFO =   0x0020;	// 05
    public static final int TERMINAL=0x0040;	// 06
    public static final int CLOCK=   0x0080;	// 07
    public static final int NO_CACHE=0x0100;	// 08
    //public static final int FILL    =0x0200;	// 09
    public static final int NO_INFO =0x0400;	// 10
    public static final int NO_SPEED=0x0800;	// 11
    public static final int FONTFAKT=0xf000;	// 12-15: Fontfaktor
    //public static final int LDATE  =0x10000;	// 16
    public static final int FAST_OUT =0x20000;	// 17
    public static final int CLOCKSUB =0x40000;	// 18
    public static final int MEHRMALS =0x80000;	// 19
    public static final int ALLEIN  =0x100000;	// 20
    public static final int NO_ASERVER=0x200000;// 21
    public static final int WORKFLOW=0x400000;  // 22
    public static final int CLRCACHE=0x800000;  // 23 clear Cache
    public static final int NO_FIX =0x1000000;	// 24 keine fixtestInfo
    public static final int CLOCK3 =0x2000000;  // 25
    public static final int MAL2   =0x4000000;  // 26 aus jedem Pixel 4 machen
    public static final int WFZR   =0x8000000;  // 27

    public static final int ASA   =1;
    public static final int ORACLE=2;
    public static final int SAP   =3;
    public static final int MS    =4;
    //public static final int ASA10 =5;
    public static final int MYSQL =6;
    public static final int MY8   =8;

    public static final int ABSTURZ=1;
    public static final int AUTOLOGOUT=2;
    public static final int MANUELL=3;
    public static final int NO_ZR=4;
    public static final int TOKEN=5;   // Token ist abgelaufen
    public static final int WIEDER=6;  // wiedereinloggen mit gleichen Benutzer
    public static final int STARTFOM=7;// kein Start-Formular vorhanden
    public static final int WEBLOG=8;  // bei Einloggen durch Web-Server
    public static final int ADMIN=16;  // ob HS aufgerufen
    
    public static final int cstBenutzerEbene=0x0043;
    public static final int cstNormalUser   =0;
    public static final int cstProg         =1;
    public static final int cstDef          =2;
    public static final int cstVerw         =0x0043;
    public static final int cstSuperUser    =3;
    public static final int cstUserManager  =0x0040;
    
    public static final int PWR=0; // ursprüngliche PW-Art
    public static final int PWH=1; // MD5 mit Aic
    public static final int PWVH=2;// MD5 zusätzlich mit Datum

    static public long lClock0 = Static.get_ms();
        private long lClock1 = Static.get_ms();
        private long lClockx=0;
        
    private static int iCount=0;
        //private long lClockLast=0;
	public java.util.Date dtStichtag=null;
	protected java.sql.Timestamp dtVon=null;
	private java.sql.Timestamp dtBis=null;
	public int iJoker=0;
        public double dJoker=0;
	public String sJoker="";
        private Vector<Integer> VecJokerBew=new Vector<Integer>();
	//public JLabel LblGlobal = new JLabel("----------------------------");
	//public boolean bZeigeInfo = false;
	//protected boolean bInfo = false;
	//protected boolean bProg = false;
	//protected boolean bDef = false;
	protected int iBits = 0; // Benutzerbits
	//private jdbc.sql.Timestamp dbXX=null;
	private String sSub="";
	private String sOpt=null;
	public boolean bTestPC = false;
	//public boolean bSaveExec = false;
	//private boolean bAppletViewer = false;
	private boolean bDebug = false;
	private String sJDBC;
	private String sSQL_Last="";
	private int iOpen=0;
	private int iExec=0;
	private int iOpenFehler=0;
	//private int iOpenGesamt=0;
//	private int iConnect=0;
	//private boolean bConnectOk = true;
	//public boolean bConnect = false;

	private long lDauer_Ges = 0;
        public String sError=null;
        public boolean bISQL=false;
        public boolean bNextReconnect=false;

	//private boolean bJDBC = true;
        //public static long lAb=0;
        public static int iClock2Ab=0;
        public static int lQryAb=-1;
        public static int iQryZeilen=0;
        public static int iTimeOut=0;
        public static int iTimeOutC=30; // Timeout für Connect in s
        public static int iTimeOutS=120; // Timeout für Socket (Statements) in min
        public static int iLogOut=0;
	private long lMem1 = 0;
	private long lMemD = 0;
	private long lMem3 = 0;
	private long lAnz = 0;
        public static int iInfos=0; // Computerbits
        public static boolean bTest=false;
	//public boolean[] bInfos = new boolean[] {false,false,false,false};
	//private String sTemp="";
	public boolean bExit=true;
	//private String sInfo = "";
	protected int iAnlage=0;
	protected int iComputer=0;
	protected int iBenutzer=0;
	protected int iLogFehler=0;
	protected int iMandant=0;
        //private int iZone=-1;
        public static int iZoneOffset=0;
	protected int iLog = 0;
        private int iSid=0;
        private int iSidOld=0;
        /*private boolean bASA=false;
        private boolean bASA10=false;
	private boolean bOracle=false;
        //private boolean bSAP=false;
        private boolean bMS=false;*/
        private int iDB=0;
        private int iVerDB=0;
	public static boolean bAusgabe=true;
	public Vector<String> VecJoker=new Vector<String>();
        public static java.awt.Color ColBackground = java.awt.Color.pink;
        public String sDbUser=null;
        public String sDbPW=null;
        //private String sDb=null;
        public int iPArt=0;

        public int iEigFaktor = 0;

        public static boolean bConnected=false;
        public static boolean bRetry=false;
        public static boolean bFast=false;
        public static boolean bRealExit=false;
        public static boolean bCheckConnect=true;
        private boolean bReConnect=false;
        public static int iSqlL=0;
        public boolean bAppl=false;
        public boolean bBasis=false;
        public boolean bFehler=false;
        private java.sql.Connection conn;

        public static Font fontStandard = new Font("SansSerif",Font.PLAIN,11);
        private boolean bWriteFehler=false;
        boolean bFremd=false;
        private String sZeitart="Tag";
        Vector<Timestamp> VecPerioden=null;
        //private int iBind=0;
        private static boolean bSSL=false;
        //private static Transact g=null;
        public boolean bTry=false;
        private static boolean bFirst=true;
        private int iPU=0;
        //public boolean bDC_Error=true;
        private boolean bDisconnect=true;
        private static String sUserCopy=null;
        private static String sPWCopy=null;
        //public static boolean bAltUser=false;
        private static int iConnectMax=0;
        //public static boolean bVonInt=false;
        public static int iMaxB=10; // maximale Anzahl der Versuche den Zeitraum zu setzen
        public static Tabellenspeicher TabVB=null;
        public static int iVNr=6;
        
        public DateFormatSymbols DFS=new DateFormatSymbols();
        public long lClockVB=0;
        protected String sUser=null;
        private String sInfoOld="";
        private String sDBVer=null;
        public String sLastError=null;
        
        public Vector<String> VecLError=new Vector<String>();

	//public static final int cstFilter=	0x10000000;

        public String getDB_Name()
        {
          return Static.bShowDB ? (ASA()?"A":Oracle()?"O":MS()?"M":MySQL()?"Y":"")+":"+sSub+"|":"";
        }
        
        public String getDB_Typ()
        {
        	return ASA()?"Sybase":Oracle()?"Oracle":MS()?"MS":MySQL()?"MySQL":"unbekannt";
        }

        public String getDB()
        {
          return sSub;
        }
        
//        public String getVDB()
//        {
//        	return iDB==MYSQL || iDB==MY8 ? sSub:"";
//        }

	public int getComputer()
        {
          return iComputer;
        }

        public int getAnlage()
        {
          return iAnlage;
        }

	public int getBenutzer()
        {
          return iLogFehler>0 ? -iLogFehler:iBenutzer;
        }

	public int getMandant()
        {
          return iMandant;
        }

        public int getZone()
        {
          //if (iZone == -1)
          {
            DateWOD DW = new DateWOD();
            return (DW.get(Calendar.ZONE_OFFSET) + DW.get(Calendar.DST_OFFSET)) / 60000+iZoneOffset;
          }
          //else
          //  return iZone;
        }
             
        public int getZone2()
        {
        	DateWOD DW = new DateWOD();
        	return DW.get(Calendar.ZONE_OFFSET) / 60000;
        }
        
        public String getZoneS()
        {
//        	DateWOD DW = new DateWOD();
//        	return new SimpleDateFormat("zzz").format(new Date());
//        	fixInfo("mögliche Zonen:"+ZoneId.systemDefault().getAvailableZoneIds());
//        	fixInfo("aktuelle Zone:"+ZoneId.systemDefault().toString());
        	return ZoneId.systemDefault().getId();
        }

	public int getLog()
	{
		return iLog;
	}

	public void printError(String s)
	{
		printError(s,0);
	}

	public static String iToS(int i)
	{
		return i>0 ? ""+i:"null";
	}
	
	private String getN(int i,String s)
	{
		return i==4 ? "WatchMe_"+s:i==3 ? "WatchMe2":"WatchMe";
	}

//	public String S255(String s,boolean b)
//	{
////		if (MySQL())
////		  s=Static.StringForMySQL(s); // ist doppelt, deshalb 9.4.2018 entfernt
//                if (b && s.length()>255)
//                  s=s.substring(0,255);
//                return Static.StringForSQL(s);
//	}
	
	public String S(String s,int iMax)
	{
		if (s == null)
            return null; 
		if (s.length()>iMax)
          s=s.substring(0,iMax);
		
		if (s.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]","").length() < 1) { 
            return "'"+s+"'"; 
        } 
 
        String clean_string = s; 
        if (MySQL())
        {
          clean_string = clean_string.replaceAll("\\\\", "\\\\\\\\"); 
          clean_string = clean_string.replaceAll("\\n","\\\\n"); 
          clean_string = clean_string.replaceAll("\\r", "\\\\r");
          clean_string = clean_string.replaceAll("'", "\\\\'");   
          clean_string = clean_string.replaceAll("\\\"", "\\\\\"");
        }
        else
          clean_string = clean_string.replaceAll("'", "''"); 
        clean_string = clean_string.replaceAll("\\t", "\\\\t"); 
        clean_string = clean_string.replaceAll("\\00", "\\\\0"); 
		
		return "'"+clean_string+"'";
	}
	
	public void printError(String s,int iBegriff)
	{
		printError(s,iBegriff,false);
	}

	public void printError(String s,int iBegriff,boolean bSilent)
	{
		if (Static.bProtFehler && !bWriteFehler && !bFremd && isConnected())
		{
			bWriteFehler=true;
                        String sAic=null;
                        if (Oracle())
                          sAic=getString("Select aic_FEHLER.nextval from dual");
                        exec("insert into fehler("+(Oracle()?"aic_fehler,":"")+"aic_computer,aic_logging,aic_begriff,fehler) values ("+
                             (Oracle()?sAic+",":"")+iToS(iComputer)+","+iToS(iLog)+","+iToS(iBegriff)+","+S(s,255)+")",true);
			bWriteFehler=false;
		}
		if (bSilent)
			fixInfo(s);
		else
		{
          //Static.printError(s);
			Static.beep();
	        System.err.println("Error:"+s);		
	        Static.addError(s);
	        if (Static.DirError != null)
			  Save.prot(new java.io.File(ErrorFile()+".err"),s);
		}
	}

	public void checkJoker(String rsJoker)
	{

		boolean bNeu=!VecJoker.contains(rsJoker);
		if (bNeu)
                  VecJoker.addElement(rsJoker);
	}

        public void addJokerBew(int i)
        {
          if (i>0 && !VecJokerBew.contains(new Integer(i)))
              VecJokerBew.addElement(new Integer(i));
        }

        public void setJokerBew(Vector Vec)
        {
          VecJokerBew.removeAllElements();
          for (int i=0;i<Vec.size();i++)
            if (!VecJokerBew.contains(Vec.elementAt(i)))
              VecJokerBew.addElement((Integer)Vec.elementAt(i));
          fixInfo("setJokerBew:"+VecJokerBew);
        }

        public void initJokerBew()
        {
          VecJokerBew.removeAllElements();
        }

        public String getJokerBew()
        {
          return Static.SQL_in(VecJokerBew);
        }

        public boolean ASA()
        {
                return iDB==ASA;
        }

        public boolean ASA10()
        {
                return iDB==ASA && iVerDB==10;
        }

	public boolean Oracle()
	{
		return iDB==ORACLE;
	}

        /*public boolean SAP()
        {
                return bSAP;
        }*/

        public boolean MS()
        {
                return iDB==MS;
        }

        public boolean MySQL()
        {
          return iDB==MYSQL || iDB==MY8;
        }
        
        public boolean My8()
        {
          return iDB==MY8;
        }
        
        public String getSqlUser()
        {
        	return getString("select "+(MySQL() ?"user()":MS() ? "CURRENT_USER": Oracle() ? "user from dual":"DB_User"));
        }

        public String getVersion()
        {
        	if (sDBVer != null)
        		return sDBVer;
          //if (bMS)
          //  return "";
          //powersoft.powerj.db.java_sql.Query Qry =new powersoft.powerj.db.java_sql.Query(this,null,false);
          String s=ASA() ? "SELECT @@version": MS() ? "Select SERVERPROPERTY('productversion')": //"SELECT cast(@@version as varchar(60))":
                     Oracle() ? "select version from product_component_version where product like 'Oracle%'":
                     MySQL() ? "select version()":"select Version from Datenbank";
          //SafetyOpen(Qry);
          //String s = Qry.eof() ? "" : Qry.getStringValue(1);
          s=getString(s);
          /*fixtestInfo("DB-Version="+s);
          if (MS())
	          try
	          {
	        	int iB=s.indexOf("-");
	            s=s.substring(iB+2,s.substring(iB+3).indexOf(" ")+iB+3);
	          }
	          catch(Exception e)
	          {
	              printError("Transact.getVersion-Fehler:"+s);
	              s="?";
	          }*/
          //testInfo("DB-Version=<"+s+">");
          //Qry.destroy(true,true);
          return s;
        }

        public int Sid()
        {
          if (iSid==0)
          {
            //powersoft.powerj.db.java_sql.Query Qry = new powersoft.powerj.db.java_sql.Query(this, null, false);
            String s=ASA() || MS() ? "select @@spid":Oracle() ? "select sys_context('userenv','sessionid') from dual" :
            	MySQL() ? "select CONNECTION_ID()":"select SessionID from Datenbank";
                       //MySQL() ? "select variable_value from information_schema.session_variables where variable_name='PSEUDO_THREAD_ID'" :
                       /*bSAP ? "select session from connectparameters":*/
            //SafetyOpen(Qry);
            //String s = Qry.eof() ? "" : Qry.getStringValue(1);
            s=getString(s);
            //testInfo("SPID=" + s);
            //Qry.destroy(true, true);
            iSid=new Integer(s).intValue();
          }
          return iSid;
        }

        private Vector<Integer> getVec(String s)
        {
          Vector<Integer> Vec=new Vector<Integer>();
          java.sql.ResultSet rs=open2(s,true,null);
          if (bFehler)
            return null;
          else
            try
              {
                while (rs.next())
                  Vec.addElement(new Integer(rs.getInt(1)));
                rs.close();
              }
              catch(SQLException e)
              {
                printError("Transact.getVec("+s+"):"+e);
              }
          String s2=s.substring(s.indexOf("from")+4);
          fixtestInfo("getVec"+s2.substring(0,s2.indexOf(" "))+":"+Vec);
          return Vec;
        }

	public void checkConnects(boolean bBereich)
	{
		if (bBereich)
          fixtestError("checkConnects "+bBereich);
          Vector<Integer> Vec=new Vector<Integer>();
          //powersoft.powerj.db.java_sql.Query Qry =new powersoft.powerj.db.java_sql.Query(this,null,false);
          String s=null;
          if (Oracle() || MS() || MySQL())
          {
            s=MS() ? "select p.spid from master.dbo.sysprocesses p JOIN master.dbo.sysdatabases d ON p.dbid = d.dbid and d.name=db_Name()"://  AND p.loginame = suser_sname()
                       MySQL() ? "select id from information_schema.PROCESSLIST where db=DATABASE()":
                       Oracle() ? "select audsid from v$session where username=user":"select session from connectedusers";

            //if (SafetyOpen(Qry) && !Qry.eof())
            //java.sql.ResultSet rs=open2(s,true,null);
            Vec=getVec(s);
            if (Vec != null)
            {
              /*try
              {
                while (rs.next())
                  Vec.addElement(new Integer(rs.getInt(1)));
                rs.close();
              }
              catch(SQLException e)
              {
                printError("Transact.checkConnects():"+e);
              }*/

              if(iLog == 0 && Vec.contains(new Integer(Sid())))
                Vec.remove(new Integer(Sid()));
              if (bBereich)
              {
                exec("delete from bereich" + (Vec.size() > 0 ? " where connid not" + Static.SQL_in(Vec) : ""),true);
              }
            }
            else
            {
              printError("Transact.checkConnects: Offene Verbindungen nicht ermittelbar");
              if (Static.bX11)
                JOptionPane.showMessageDialog(new JFrame(), "Offene Verbindungen nicht ermittelbar !", "Fehler", JOptionPane.ERROR_MESSAGE);
              return;
            }
          }
//          else if (ASA())
//          {
//            int iMom = 0;
//            //String s="select connid";
//            do
//            {
//              exec("set connid=next_connection(connid,null)");
//              //SafetyOpen(Qry);
//              java.sql.ResultSet rs=open2("select connid",true,null);
//              //iMom = Qry.eof() || Qry.getValue(1).isNull() ? 0 : Qry.getValue(1).getIntNoThrow();
//              try
//              {
//                iMom = rs.next() ? rs.getInt(1) : 0;
//                rs.close();
//              }
//              catch (SQLException e)
//              {
//                printError("Transact.checkConnects():" + e);
//              }
//              if (iMom != 0)
//                Vec.addElement(new Integer(iMom));
//            }
//            while (iMom != 0);
//          }
//          else
//            Vec.addElement(new Integer(0));
          //Qry.destroy(true,true);
          if (iLogOut>0)
          {
            //Vector<Integer> VecTimer=getVec("select aic_code from code where kennung like 'Timer%' or kennung='AServer'");
            String sStunden=MS() ? now()+"-mom>'"+iLogOut+":00'":"time_to_sec(timediff("+now()+",mom))>"+(iLogOut*3600);
            fixtestInfo("sStunden="+sStunden);//+", VecTimer="+VecTimer);
            int i = exec("update logging set aus="+now()+",status="+AUTOLOGOUT+" where aus is null and "+sStunden+" and aic_code is not null",true); // +Static.SQL_in(VecTimer),true);
            if (i > 0)
              fixInfo(i + " Auto-ausgeloggt!");
          }
          bISQL = true;
          int iAnz=0;
          Vector<Integer> VecLog=getVec("select aic_logging,status from logging where aus is null and C_Number not in ("+s+")");
          if (VecLog.size()>0)
        	  for (int i=0;i<VecLog.size();i++)
        		  if (exec("update logging set aus="+now()+",status=(status&"+WEBLOG+")+"+ABSTURZ+" where aic_logging="+Sort.geti(VecLog, i)+" and aus is null")>0)
        			  iAnz++;
//        		  else if (exec("update logging set aus="+now()+",status="+(ABSTURZ)+" where aic_logging="+Sort.geti(VecLog, i)+" and aus is null")>0)
//        			  iAnz++;
//          int i = exec("update logging set aus="+now()+",status="+ABSTURZ+" where aus is null"+(Vec.size()>0?" and C_Number not" + Static.SQL_in(Vec):""),true);
          bISQL = false;
//          if (i<0)
//            i = exec("update logging set aus=mom where aus is null"+(Vec.size()>0?" and C_Number not" + Static.SQL_in(Vec):""),true);
          if (iAnz > 0)
            fixInfo(iAnz + " ausgeloggt!");
	}

        public boolean connect3(String rsJDBC,String rsName,String rsPW)
        {
          String sUserOld=sDbUser;
          String sPWOld=sDbPW;
          int iPArtOld=iPArt;
          sDbUser=rsName;
          sDbPW=rsPW;
          iPArt=-1;
          bFremd=true;
          boolean b=connect(rsJDBC);
          sDbUser=sUserOld;
          sDbPW=sPWOld;
          iPArt=iPArtOld;
          return b;
        }

        public String getJDBC()
        {
          return new String(sJDBC);
        }
        
        public String getAnzConnects()
        {
        	if (iConnectMax==0)
        		iConnectMax=SQL.getInteger2(this,"show global variables like 'max_connections'");
        	int iConnectMom=SQL.getInteger2(this,"show global status like 'threads_connected'");
        	return "Connection "+iConnectMom+" / "+iConnectMax;
        }

	public boolean connect(String rsJDBC)
	{
          long lClock=Static.get_ms();
          bDisconnect=false;
          		//boolean b=bSaveExec;
		//bSaveExec = false;
                if (rsJDBC != null)
                  sJDBC = rsJDBC;
                else if (sDbUser==null)
                {
                	sDbUser=sUserCopy;
                	sDbPW=sPWCopy;		
                }
                if (sJDBC.startsWith("SSL"))
                {
                  sJDBC=sJDBC.substring(3);
                  bSSL=true;
                }
                fixInfo("vor "+(bSSL?"SSL-":"")+"connect "+sJDBC);//+" mit User "+sDbUser);

                String sIP=null;
		if (sJDBC != null)
		{
                  if (sJDBC.startsWith(".my:")) // MySQL
                  {
                    iDB=MYSQL;
                    Static.bMySQL=true;
                    sSub=sJDBC.substring(sJDBC.indexOf("/")+1);
                    sIP=sJDBC.substring(4,sJDBC.indexOf("/"));
                  }
                  else if (sJDBC.startsWith(".my8:")) // MySQL
                  {
                      iDB=MY8;
                      Static.bMySQL=true;
                      int iOpt=sJDBC.indexOf("?");
                      int iSl=sJDBC.indexOf("/");
                      if (iOpt>0)
                      {
                    	  sOpt=sJDBC.substring(iOpt+1);
                    	  sSub=sJDBC.substring(iSl+1,iOpt);
                      }
                      else
                    	  sSub=sJDBC.substring(iSl+1);
                      sIP=sJDBC.substring(5,iSl);
                      fixInfo("IP="+sIP+", Sub="+sSub+", Opt="+sOpt);
                    }
                  else if (sJDBC.indexOf("//")==0) // MS
                  {
                    //if (sJDBC.indexOf("jdbc:odbc:")==0)
                    iDB = MS;
                    sSub=sJDBC.substring(sJDBC.indexOf("=") + 1);
                    sIP=sJDBC.substring(2,sJDBC.indexOf(":"));
                    //fixInfo("sSub="+sSub);
                    //else if (sJDBC.indexOf("/")>1)
                    //  bSAP=true;
                  }
                  else  // Sybase oder Oracle
                  {
                    sSub = sJDBC.substring(sJDBC.indexOf(":") + 1);
                    sIP=sJDBC.substring(0,sJDBC.indexOf(":"));
                    int iPos = sSub.indexOf(":");
                    if (iPos == -1)
                      iDB=ASA;
                    else
                    {
                      iDB=ORACLE;
                      sSub = sSub.substring(iPos + 1);
                    }
                    Static.iMemoMax=4000;
                  }
		}
                bTestPC = MySQL() && (sSub.indexOf("_test")>0 || sSub.startsWith("aPPl")) || MS() && (sSub.indexOf("_Test")>0) || Oracle() && sSub.equals("xE") ||
                    ASA() && (sSub.equals("2639") /* Prog */ || sSub.equals("2669") /* K2 */);

                if (sIP != null)
                {
                  try
                  {
                    //long lClock2=Static.get_ms();
                    //fixInfo("sIP="+sIP);
                    if (sIP.indexOf(":")>0)
                    	sIP=sIP.substring(0,sIP.indexOf(":"));
                    InetAddress inet = bRealExit || Static.bTest || !Static.bSlow ? InetAddress.getByName(sIP):null;
//                    if (bRealExit)
//                    {
//                      //fixInfo("versuche Verbindung zu " + sIP);
//                      if(!inet.isReachable(30000)) {
//                        Static.printError("Datenbank-Rechner mit IP " + sIP + " ist nicht erreichbar!");
//                        JOptionPane.showMessageDialog(null, "Datenbank-Rechner mit IP " + sIP + " ist nicht erreichbar!", "Fehler",
//                            JOptionPane.ERROR_MESSAGE);
//                        System.exit(11);
//                      }
//                      clockInfo("Db-Server-Suche",lClock2);
//                    }
//                    if (bFirst)
//                    {
//                      if (inet != null && Static.bTest)
//                      {
//                        fixInfo("CHN=" + inet.getCanonicalHostName());
//                        fixInfo(" HA=" + inet.getHostAddress());
//                        fixInfo(" HN=" + inet.getHostName());
//                        fixInfo("isSiteLocalAddress=" + inet.isSiteLocalAddress());
//                        fixInfo("isLinkLocalAddress=" + inet.isLinkLocalAddress());
//                        fixInfo("isLoopbackAddress=" + inet.isLoopbackAddress());
//                        /*fixtestInfo("isAnyLocalAddress="+inet.isAnyLocalAddress());
//                                               fixtestInfo("isMCNodeLocal="+inet.isMCNodeLocal());
//                                               fixtestInfo("isMCLinkLocal="+inet.isMCLinkLocal());*/
//                      }
//                      //System.out.println("  s=" + inet.toString());
                      Static.bLocal = inet != null && (inet.isSiteLocalAddress() || inet.isLoopbackAddress());
//
//                      fixInfo("local=" + Static.bLocal);
//                      bFirst = false;
//                    }
                  }
                  catch(Exception e2)
                  {
                    System.err.println("Db-Error:" + e2);
                    e2.printStackTrace();
                  }
                }

                bAppl = MySQL() && sSub.toUpperCase().startsWith("APPL_");
                bBasis = sSub.startsWith("Basis_");
		//testInfo("Debug="+bDebug);
                if (!bReConnect && (bAppl || bBasis))
                  fixInfo("JDBC-Verbindung mit "+sJDBC/*+","+bOracle+","+sSub+","+iPos*/+(bAppl?" = Appl":"")+(bBasis?" = Basis":""));               
                boolean bConnect = false;
                if (MySQL())
                {            
                  try
                  {                
                    if (sDbUser!=null && sUserCopy==null)
                      fixInfo("MySQL-Connect with alternative user");//"+sDbUser);
                    Properties props = new Properties();
                    sUserCopy= sDbUser==null? getN(iPArt,sSub):sDbUser;
                    sPWCopy=sDbPW==null? getP(iPArt):sDbPW;
//                    fixInfo("******* MySQL-Connect with "+sUserCopy); // war nur Testhalber
                    props.put("user", sUserCopy);
                    props.put("password",sPWCopy);
                    //props.put("autoReconnect",true);
                    props.put("connectTimeout",""+(iTimeOutC*1000)); // bis V 5.11.06: 5000
                    props.put("socketTimeout",""+(iTimeOutS*60000)); // bis V 5.11.06: 300000
                    //fixInfo("SQL-Timeout="+iTimeOutS+" min");
                    //props.put("net_read_timeout","120");
                    //props.put("net_write_timeout","130");
                    props.put("useCompression","true");
                    props.put("allowMultiQueries","true");
                    //props.put("paranoid","true");
                    props.put("cacheCallableStmts","true");
                    props.put("prepStmtCacheSqlLimit","8000"); // 4000
                    props.put("cachePrepStmts","true");
                    //props.put("ROW_FORMAT","COMPRESSED");
                    String s2=bSSL ? "?verifyServerCertificate=false&useSSL=true&requireSSL=true":My8() && sOpt==null ? "?allowPublicKeyRetrieval=true":"";//"?serverTimeZone='Europe/Vienna'";
                    if (bSSL)
                    {
                      if (!new File(Static.DirTemp+"truststore").exists())
                      {
                        InputStream is = getClass().getResourceAsStream("/ssl/keystore");
                        FileOutputStream os = new FileOutputStream(new File(Static.DirTemp+"keystore"));
                        for(int read = 0; (read = is.read()) != -1;)
                          os.write(read);
                        os.flush();
                        is = getClass().getResourceAsStream("/ssl/truststore");
                        os = new FileOutputStream(new File(Static.DirTemp+"truststore"));
                        for(int read = 0; (read = is.read()) != -1;)
                          os.write(read);
                        os.flush();
                      }
                      props.put("useSSL", "true");
                      props.put("verifyServerCertificate", "false"); //"true");
                      props.put("requireSSL", "true");

                      Properties p = System.getProperties();                    
                      p.put("javax.net.ssl.keyStore",Static.DirTemp+"keystore");
                      p.put("javax.net.ssl.keyStorePassword", "fs7615");                   
                      p.put("javax.net.ssl.trustStore",Static.DirTemp+"truststore");
                      p.put("javax.net.ssl.trustStorePassword", "fs7615");
                    }
                    else
                    {
                    	props.put("useSSL", "false");
                    	props.put("serverTimezone","Europe/Vienna");
                    }
                    testInfo("\n                 Connect-Properties:");
                    for (Enumeration e = props.propertyNames() ; e.hasMoreElements() ;)
                    {
                      String s=(String)e.nextElement();
                      if (!s.equals("user") && !s.equals("password"))
                        testInfo(s+"="+props.get(s));
                      //System.out.println(e.nextElement());
                    }
                    if (My8())
                    {
                    	//fixtestError("MySQL8-Verbindung:"+sJDBC);
                    	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    	conn = DriverManager.getConnection("jdbc:mysql://"+sJDBC.substring(5)+s2,props);
                    }
                    else
                    {
                    	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    	conn = DriverManager.getConnection("jdbc:mysql://"+sJDBC.substring(4)+s2,props);
                    }
                    if (bFirst && conn!=null)
                    {
                      fixInfo("MySQL-Treiber-Version "+conn.getMetaData().getDriverVersion());
                      bFirst=false;
                    }
                    //exec("set net_read_timeout=1");
                    //exec("set net_write_timeout=2");
                    //props=conn.getClientInfo();
                    //fixInfo("Host="+props.get("host")+", port="+props.get("port")+", db="+props.get("db"));
//                    if (bTestPC)
//                    	fixtestError(getAnzConnects());
                    bConnect = true;
                  }
//                  catch (CommunicationsException e) {
//                	  Static.printError("MySQL-CommunicationsException:"+e);
//                  }
                  catch (Exception e)
                  {
                    Static.printError("Transact.connect-MySQL:" + e);
                    printStackTrace(e);
                    return false;
                    //System.exit(0);
                  }

                }
        else if (Oracle())
		{
			
			Properties props = new Properties();
			props.put("user", sDbUser==null ? getN(iPArt,sSub):sDbUser);
			props.put("password", sDbPW==null ? getP(iPArt):sDbPW);
                        //props.put("allowMultiQueries","true");
                        if (iQryZeilen>0)
                        {
                          props.put("defaultRowPrefetch", ""+iQryZeilen);
                          props.put("defaultBatchValue", "5");
                        }
                        //setProperties(props);
                        try
                        {
                          DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                          conn = DriverManager.getConnection("jdbc:oracle:thin:@"+sJDBC, props);
                          bConnect = true;
                        }
                        catch (Exception e)
                        {
                          Static.printError("Transact.connect-Oracle:" + e);
                        }

		}
		else if (ASA())
		{
			/*String sDriver="com.sybase.jdbc2.jdbc.SybDriver";
                        registerDriver( sDriver );
			setDriverName( sDriver );
			setConnectionSource( createJDBCConnectionSource( sDriver ) );
			setDataSource( "jdbc:sybase:Tds:"+sJDBC);*/
			Properties props = new Properties();
			props.put("user", sDbUser==null?"dba":sDbUser);
                        if (sDbPW==null)
                        {
                          /*if(Static.iID < 1000)
                            fixInfo("ID=" + Static.iID);*/
                          props.put("password", /*Static.iID == 13 ? "sql" :*/ "au" + 6162/*Static.iID*/ / 13 + "o");
                        }
                        else
                          props.put("password",sDbPW);
			props.put("CHARSET_CONVERTER_CLASS","com.sybase.jdbc2.utils.PureConverter");
			//setProperties(props);
                        try
                        {
                          DriverManager.registerDriver(new com.sybase.jdbc2.jdbc.SybDriver());
                          conn = DriverManager.getConnection("jdbc:sybase:Tds:"+sJDBC, props);
                          bConnect = true;
                        }
                        catch (Exception e)
                        {
                          Static.printError("Transact.connect-ASA:" + e);
                        }
		}
        else if (MS())
		{
                  if (sDbUser!=null)
                      fixInfo("MS-SQL-Connect with alternative user");//+sDbUser);
			Properties props = new Properties();
                        props.put("user", sDbUser==null ? getN(iPArt,sSub):sDbUser);
                        props.put("password", sDbPW==null? getP(iPArt):sDbPW);
			//setProperties(props);
                        try
                        {
                          //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
                          //conn = DriverManager.getConnection("jdbc:sqlserver:"+sJDBC+";selectMethod=cursor;", props);
                          DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
                          conn = DriverManager.getConnection("jdbc:jtds:sqlserver:"+sJDBC+";selectMethod=cursor;", props);
                          bConnect = true;
                        }
                        catch (Exception e)
                        {
                          Static.printError("Transact.connect-MS:" + e);
                        }
		}
                if (!bConnect)
                {
                	if (sDbUser != null)
                		Static.printError("Verbindung mit "+sDbUser+" nicht möglich, deshalb zurückgesetzt");
                	sDbUser=null;
                	sDbPW=null;         	
                }
                if (!bConnected)
                  bConnected=bConnect;
			//int iSidOld=0;
                        //if (bReConnect)
                        //  iSidOld=Sid();
                        iSid=0;
                        if (bConnect)
                          iSid=Sid();
                        bConnect=iSid>0;
                        iCount++;
                        if (!bReConnect || bConnect)
                          fixInfo((bSSL?"SSL-":"")+"Connect="+iCount+"/"+bConnect+", ID="+iSid+": "+(Static.get_ms()-lClock)+" ms");
                        printExec("++ connect "+iSid,false);
                        //setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
                        //setDatabaseType(DATABASE_ASE);
                        //fixInfo("bConnect="+bConnect+",bASA="+bASA);
                        //fixInfo("Typ="+getDatabaseType()+",ASA="+DATABASE_ASE+",ORACLE="+DATABASE_ORACLE);
                        //if (!bASA || Prog())
                        if (lQryAb>-1)
                          saveSqlTime("Log",-1,Static.get_ms()-lClock,null,0,-4);
			//connect();
			//printInfo(">nach connect");
//                        if (bConnect && !bFremd)
//                          g=this;
                        if (bConnect && iPArt ==-1)
                          ;
			else if (bConnect)
			{
				if (sDbUser==null)// && !bTestPC)// && bAltUser)
				{
				  String sNP=getString("select Parameterzeile from parameter where aic_parameter=" + getPU());
				  int iS=sNP==null?-1:sNP.indexOf(";");
				  if (iS>0)
			      {
				    sDbUser=getPassword(sNP.substring(0,iS),PWR,0);
				    sDbPW=getPassword(sNP.substring(iS+1),PWR,0);
                                    //bDC_Error=false;
//				    if (bSSL)
//				    	Static.sleep(2000);
				    disconnect();
				    return connect(null);
                                    //bDC_Error=true;
			      }
				}
				//addInfo("-> connect");
                                //fixInfo("-> xconnect"+iConnect);
                                //if (!bMS)
                                //{
                                  String sVer=getVersion();
                                  //char c = sVer.charAt(0);
                                  //bASA10=bASA && c=='1';
                                  iVerDB=Integer.parseInt(sVer.substring(0,sVer.charAt(0)=='1' ? 2:1));
                                  testInfo("Datenbank="+iDB+", Ver="+iVerDB);
                                  if (ASA() && iVerDB < 8 || Oracle() && iVerDB < 9 || MS() && iVerDB < 8 || MySQL() && iVerDB<5)
                                  {
                                    disconnect();
                                    Static.printError("Bitte auf " + (ASA() ? "Sybase 9" : Oracle() ? "Oracle 10" : MS() ? "MS 2005": MySQL() ? "MySQL 5.5":"unbekannt") + " updaten!");
                                    System.exit(12);
                                  }
                                //}
//				iConnect++;
				// fixInfo("-> connect "+Sid());
                                //iOpenFehler=0;				
                                //else if (Oracle() || MS() || MySQL())
                                {
                                  if (Oracle() && Static.cOOM!='-')
                                    exec("alter session SET OPTIMIZER_MODE = "+(Static.cOOM=='R' ? "RULE":Static.cOOM=='C' ? "CHOOSE":Static.cOOM=='F' ? "FIRST_ROWS":"ALL_ROWS"));
                                  //iSid=0;
                                  if (bReConnect)
                                  {
                                	if (iSidOld>0)
                                	{
                                      exec("delete From bereich where connid="+iSidOld);
                                      exec("update logging Set C_Number="+iSid+" where aus is null and C_Number="+iSidOld);
                                	}
                                	else
                                	{
                                		Static.printError("Transact.connect: reconnect nicht möglich");
                                		System.exit(1);
                                	}
                                  }
                                  else if (bCheckConnect)
                                    checkConnects(false);
                                  bISQL=true;
                                  if (iSid>0)
                                  {
                                    exec("delete fRom bereich where connid="+iSid);
                                    exec("insert iNto bereich (connid) values ("+iSid+")");
                                    if (dtVon==null || dtBis==null)
                                    	setAktDate(true);
                                    else
                                    	setVonBis(dtVon,dtBis,true);
                                  }
                                  else
	                              	{
	                              		Static.printError("Transact.connect: Sid="+iSid);
	                              		System.exit(1);
	                              	}
                                  //testInfo(sError);
                                  bISQL=false;
                                }
				//if (Static.iID != 13)
                                //{
                                  if (bReConnect)
                                  {
                                    java.sql.Timestamp dtVonN=getVon();
                                    java.sql.Timestamp dtBisN=getBis();
                                    //setVonBis(null,null);
                                    //dtVon=null;
                                    //dtBis=null;
                                    setVonBis(dtVonN,dtBisN,true);
                                    bReConnect=false;
                                  }
                                  else
                                  {
                                    VecJoker.removeAllElements();
                                    setAktDate(true);
                                    //checkBereich();
                                  }
                                //}
                                iSidOld=iSid;
			}
			else if (bExit && !bRetry)
			{
				//printInfo(">vor Fehlermeldung");
				//Static.printError("Transact.connect: Connect failed");
                                if (Static.bX11)
				  JOptionPane.showMessageDialog(new JFrame(),"Connect failed to "+sJDBC,"Fehler",JOptionPane.ERROR_MESSAGE);
                                else
                                  Static.printError("Transact.connect: Connect failed");
				//System.exit(1);
				//printInfo(">nach Fehlermeldung");
			}
		//}
		//return bConnectOk;
		//bSaveExec = b;
		return bConnect;
	}

        public void disconnect()
        {
          boolean b=Static.bAutoKonsole;
          if (b)
        	  Static.bAutoKonsole=false;
          try
          {
        	bDisconnect=true;
            conn.close();
            //if (bDC_Error)
            //  new Exception().printStackTrace();
            iCount--;
            fixInfo("<- disconnect "+iSid+" -> "+iCount);
            printExec("-- disconnect "+iSid,false);
          }
          catch(SQLException e)
          {
       	    Static.printError("Transact.disconnect-SQL:"+e);
          }
          catch(Exception e)
          {
            Static.printError("Transact.disconnect:"+e);
          }
          if (b)
        	  Static.bAutoKonsole=true;
        }
        
        public int count()
        {
        	return iCount;
        }

        public boolean isConnected()
        {
          //if (iSid==0)
          //  return false;
          try
          {
            return!conn.isClosed();
          }
          catch(Exception e)
          {
            //Static.printError("Transact.isConnected:"+e);
            return false;
          }
        }
        
        private String getP(int i)
        {
        	return i==3 ? "KD61H0rVE2pp9ZpC":"Fox03:06";
        }

        public void start()
        {
          fixtestInfo("Transact.start");
          printExec("start Transaction",false);
          try
          {
            conn.setAutoCommit(false);
          }
          catch(Exception e)
          {
            Static.printError("Transact.start:"+e);
          }
        }
        
        public void commit2(boolean bFehler)
        {
        	try
            {
          	  if (!conn.getAutoCommit())
          	  {
          		  if (bFehler)
          		  {
          			fixtestInfo("Transact.rollback");
          			printExec("rollback",false);
          			conn.rollback();
          		  }
          		  else
          		  {
          			  fixtestInfo("Transact.commit");
          			  printExec("commit",false);             
          			  conn.commit();
          		  }
          		  conn.setAutoCommit(true);
          	  }
            }
            catch(Exception e)
            {
              Static.printError("Transact.commit2:"+e);
            }
        }

        public void commit()
        {
          try
          {
        	  if (!conn.getAutoCommit())
        	  {
        		  fixtestInfo("Transact.commit");
                  printExec("commit",false);             
        		  conn.commit();
        		  conn.setAutoCommit(true);
        	  }
          }
          catch(Exception e)
          {
            Static.printError("Transact.commit:"+e);
          }
        }

        public void rollback()
        {     	
          try
          {
        	  if (!conn.getAutoCommit())
        	  {
        		  fixtestInfo("Transact.rollback");
        		  printExec("rollback",false);
        		  conn.rollback();
        		  conn.setAutoCommit(true);
        	  }
          }
          catch(Exception e)
          {
            Static.printError("Transact.rollback:"+e);
          }
        }

        public int Logcheck()
        {
          try
          {
        	if (iLog==0)
        	  return 2; // nicht nötig
        	if (iLog<0)
        	  return 0; // Fehler
            if (conn.getAutoCommit())
              return exec("Update Logging set MOM="+now()+" where aic_Logging =" + iLog+" and aus is null")>0 ? 3 /* ok */:0 /* nicht */;
            else
              return 1; // noch nicht möglich
          }
          catch(Exception e)
          {
            Static.printError("Transact.Logcheck:"+e);
            return 0; // Fehler -> nicht durchgeführt
          }

        }

        public void setAktDate(boolean bAktuell)
        {
          DateWOD dtMom = bAktuell || dtVon==null ? new DateWOD():new DateWOD(dtVon.getTime());
          dtMom.setTimeZero();
          Timestamp tsVon=dtMom.toTimestamp();
          dtMom.tomorrow();
          setVonBis(tsVon,dtMom.toTimestamp());
        }

        public void setMonth(java.sql.Timestamp rdtVon) // für Klasse Zeitraum
        {
          DateWOD dtMom = rdtVon==null ? new DateWOD():new DateWOD(rdtVon.getTime());
          //progInfo("setMonth:"+dtVon+"/"+dtMom);
          //if (dtBis==null)
          //dtMom.yesterday();
          dtMom.setDay1();
          Timestamp tsVon=dtMom.toTimestamp();
          dtMom.nextMonth();
          setVonBis(tsVon,dtMom.toTimestamp());
        }

        public String PasswordConvert(String s,int iArt,int iAic)
        {
          String sNeu = "";
          try
          {
            if (iArt>0)
            {
              if (iArt==PWVH && iAic>0)
            	  s=iAic+"$"+s+"#"+new DateWOD(SQL.getTimestamp(this, "select seit from benutzer where aic_benutzer="+iAic)).Format("yyyy-MM-dd");
              else
            	  s+=""+iAic;               
              MessageDigest m=MessageDigest.getInstance("MD5");
              m.update(s.getBytes(),0,s.length());
              sNeu=new BigInteger(1,m.digest()).toString(16);
              if (sNeu.length()<32)
            	  sNeu=Static.FillNull(sNeu, 32);
              //fixtestInfo("PW1="+s+", PW2="+sNeu);            
            }
            else
            {
              byte[] b = s.getBytes();
              byte[] b2 = "Zweistein".getBytes();
              for (int i = 0; i < b.length; i++)
              {
                int i2 = b[i] ^ b2[i % 9];
                sNeu += new String(new byte[] {(byte)(i2 / 16 + 65), (byte)(i2 % 16 + 75)});
              }
            }
          }
          catch (Exception e)
          {
            System.out.println(e);
            e.printStackTrace();
          }

          return sNeu;
        }

        public String getPassword(String s,int iArt,int iAic)
        {
          String sNeu = "";
          byte[] b = s.getBytes();
          if (iArt>0)
          {
            sNeu=PasswordConvert(Static.sDefaultPW,iArt,iAic).equals(s) ? Static.sDefaultPW:iArt==PWVH?"<sehr sicher>":"<sicher>";
          }
          else
          {
            byte[] b2 = "Zweistein".getBytes();
            for (int i = 0; i < b.length / 2; i++)
              sNeu += new String(new byte[] {(byte)(((b[i * 2] - 65) * 16 + b[i * 2 + 1] - 75) ^ b2[i % 9])});
          }
          return sNeu;
        }

	public boolean TestPC()
	{
		return bTest || bTestPC && (iLog==0 || (iInfos&NO_INFO)==0);
	}

        public void unConnect()
        {
          unConnect(false);
        }

	public void unConnect(boolean bLogout)
	{
          fixtestInfo("unConnect "+bLogout);
		if (isConnected())
		{
              long lClock=Static.get_ms();
              if (iLog>0 && bLogout)
              {
                exec("update Logging set aus="+now()+" where aic_logging="+iLog);
                fixtestInfo("unConnect Log"+iLog);
                iLog=0;
                if (lQryAb>-1)
                    saveSqlTime("Logout",-1,Static.get_ms()-lClock,null,0,-4);
              }
              if (!ASA())
                 exec("delete from bereich where connid="+iSid);
			disconnect();
                        
			//bConnect = false;
			dtStichtag=null;
			dtVon=null;
			dtBis=null;
			iJoker=0;
            dJoker=0.0;
			sJoker="";
			//addInfo("<- disconnect");
			iSid=0;
		}
	}

	public void printExec(String s)
	{
		printExec(s,true,false);
	}

        /*private static String ErrorFile()
        {
          return Static.DirError+Static.sIP+(sUser==null ? "":"_"+sUser);
        }*/
	
	public void printExec(String s, boolean b)
	{
		printExec(s,b,false);
	}

	public void printExec(String s, boolean b,boolean bExec)
	{
		if (Def())
			printInfo(3, (bExec ? "  Exec:":"")+s);
		if(b)
            printInfo(s);
		if (lQryAb>-1 && Static.DirError != null)
			if (!Save.prot(new java.io.File(ErrorFile()+".exec"),iSid+"\t"+s))
                          printError("Protokollieren in "+ErrorFile()+".exec nicht möglich");
	}

	/*public boolean NormalPort()
	{
		return sSub.equals("2638");
	}*/

        public boolean ApplPort()
        {
                return bAppl;
        }

        public boolean BasisPort()
        {
                return bBasis;
        }


    /*public static String SqlDate(java.sql.Date date)  // für saveView in DefAbfrage
    {
		return date == null ? "null":"'"+new SimpleDateFormat("yyyy/MM/dd").format(date)+"'";
    }*/

    /*protected String SqlDate(java.sql.Timestamp date)
    {
		return date == null ? "null":
                    Oracle() ? "to_timestamp('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+"','YYYY-MM-DD HH24:MI:SS')":
                    "'"+new SimpleDateFormat(ASA() || MySQL() ?"yyyy/MM/dd HH:mm:ss":MS()?"yyyy/dd/MM HH:mm:ss":"").format(date)+"'";
    }*/

	/*public void setStichtag(java.sql.Date dt)
	{
		dtStichtag=dt;
	}*/

        /*public void checkBereich()
        {
          testInfo("checkBereich");
          if (!ASA())
          {
            powersoft.powerj.db.java_sql.Query Qry =new powersoft.powerj.db.java_sql.Query(this,null,false);
            Qry.setSQL("SELECT von from bereich where connid="+Sid());
            SafetyOpen(Qry);
            String s = Qry.eof() ? "" : Qry.getStringValue(1);
            Qry.destroy(true,true);
            if (s.equals(""))
            {
              Static.printError("Transact.checkBereich: Tabelle Bereich nicht erreichbar");
              JOptionPane.showMessageDialog(new JFrame(),"Tabelle Bereich nicht erreichbar !","Fehler",JOptionPane.ERROR_MESSAGE);
              System.exit(2);
            }
          }
        }*/
        
        public Tabellenspeicher getTabVB()
        {
        	return TabVB;
        }
        
        public java.sql.Timestamp getVon(int iNr)
        {
        	if (iNr>0)
        	{
        		int iPos=TabVB.getPos("Nr",iNr);
        		if (iPos<0)
            		Static.printError("Transact.getVon: Nr "+iNr+" nicht gefunden");
        		else
        			return TabVB.getTimestamp(iPos,"von");
        	}
        	else
        		return dtVon;
        	return null;
        }
        
        public java.sql.Timestamp getBis(int iNr)
        {
        	if (iNr>0)
        	{
        		int iPos=TabVB.getPos("Nr",iNr);
        		if (iPos<0)
            		Static.printError("Transact.getBis: Nr "+iNr+" nicht gefunden");
        		else
        			return TabVB.getTimestamp(iPos,"bis");
        	}
        	else
        		return dtBis;
        	return null;
        }
        
        public String getZA(int iNr) // holt ob Tag oder Monat ..
        {
        	if (sZeitart==null || sZeitart.equals("offen"))
        		fixtestError("getZA"+iNr+":"+sZeitart);
        	if (iNr>0)
        	{
        		int iPos=TabVB.getPos("Nr",iNr);
        		if (iPos<0)
            		Static.printError("Transact.getZA: Nr "+iNr+" nicht gefunden");
        		else
        			return TabVB.getS(iPos,"ZA");
        	}
        	else
        		return sZeitart;
        	return null;
        }
        
        public void setZA(int iNr,String sZA)
        {
        	if (sZA==null || sZA.equals("offen"))
        		fixtestError("setZA"+iNr+":"+sZA);
        	if (iNr>0)
        	{
        		int iPos=TabVB.getPos("Nr",iNr);
        		if (iPos<0)
            		Static.printError("Transact.setZA: Nr "+iNr+" nicht gefunden");
        		else
        			TabVB.setInhalt(iPos, "ZA", sZA); 
        	}
        	else
        		sZeitart=sZA;
        }
        
        public Vector<Timestamp> getVecPer(int iNr)
        {
        	if (iNr>0)
        	{
        		int iPos=TabVB.getPos("Nr",iNr);
        		if (iPos<0)
            		Static.printError("Transact.getVecPer: Nr "+iNr+" nicht gefunden");
        		else
        			return (Vector)TabVB.getInhalt("VecPer",iPos);
        	}
        	else
        		return VecPerioden;
        	return null;
        }
        
        public void setVecPer(Vector<Timestamp> Vec)
        {
        	VecPerioden=Vec;
        }

        public void setCalc(int iNr,Calc c)
        {
          if (iNr>0)
        	{
        		int iPos=TabVB.getPos("Nr",iNr);
            if (iPos<0)
            	Static.printError("Transact.setCalc: Nr "+iNr+" nicht gefunden");
            else
              TabVB.setInhalt(iPos,"Calc",c);
          }
          else
            Static.printError("Transact.setCalc nur mit lokalen ZR");
        }
        
        public void setVB_Fertig(int iNr)
        {
        	int iPos=TabVB.getPos("Nr",iNr);
        	if (iPos<0)
        		Static.printError("Transact.setFertig: Nr "+iNr+" nicht gefunden");
        	else
        		TabVB.setInhalt(iPos, "Ende",new Date());
        }
        
        public void setVonBis(Timestamp dtVon,Timestamp dtBis,int iVB)
    	{
    		if (iVB<=0)
    			setVonBis(dtVon, dtBis);
    		else
    			setVonBisL(dtVon,dtBis,null,iVB,null,null);
    	}
        
        public int setVonBisL(Date dtV,Date dtB,String sZA,int iNr,String sAbf,Vector<Timestamp> VecPer)
        {
        	//int iNr=0;
        	int iPos=-1;
        	if (TabVB==null)
        	{
        		TabVB=new Tabellenspeicher(null, new String[] {"Nr", "von", "bis","ZA","VecPer","Beginn","Ende","Abfrage","DB","Calc"});
        		iVNr++;
        		iNr=6;
        	}
        	else if (iNr>0)
        		iPos=TabVB.getPos("Nr",iNr);
        	else
        		for (int i=0;iNr==0 && i<TabVB.size();i++)
        			if (!TabVB.isNull(i,"Ende"))
        			{
        				TabVB.setInhalt(i, "Ende", null);               		
        				iNr=TabVB.getI(i, "Nr");
        				iPos=i;
        			}
        	if (iPos==-1)
        	{
        		if (iNr==0)
        			iNr=iVNr++;
        		iPos=TabVB.newLine();
        		TabVB.setInhalt(iPos, "Nr",iNr);
        	}
        	TabVB.setInhalt(iPos, "Beginn",new Date());
    		if (sAbf!=null)
    			TabVB.setInhalt(iPos, "Abfrage",sAbf);
    		TabVB.setInhalt(iPos, "DB",getDB());
    		if (MySQL())
    		{
    			String sNr=iNr+sSub;
	        	if (!exists("select table_name from information_schema.views where table_schema=database() and table_name='stammview"+iNr+"'",null))
	        	{
	        		String sV="von"+sNr+"()";
	        		String sB="bis"+sNr+"()";
	        		bISQL=true;
	        		exec("create function von"+sNr+"() returns DATETIME DETERMINISTIC NO SQL return @von"+sNr);
	        		exec("create function bis"+sNr+"() returns DATETIME DETERMINISTIC NO SQL return @bis"+sNr);
	        		bISQL=false;
	        		exec("create view stammview"+iNr+" as select aic_stamm,kennung2 kennung,bezeichnung,aic_stammtyp2 aic_stammtyp,aic_mandant,ab,firma,anr,eintritt,austritt,aic_rolle from stamm_protokoll"+
	            			" where pro_aic_protokoll is null and ("+sV+" is null or austritt is null or austritt >= "+sV+") and ("+sB+" is null or eintritt is null or eintritt < "+sB+
	                        ") and ("+sB+" is null and weg is null or (ab is null or "+sB+">ab) and (weg is null or "+sB+"<=weg))");
	        		String sPlus=",anr,ldate,BEW2_AIC_BEW_POOL,ZONE,ANR2,ANR3,ANR4,ANR5,ANR6,ANR7,ANR8,ANR9,LDATE2,LDATE3,BOOL1,BOOL2,AIC_PROTOKOLL,LDateVon,LDateBis";
	                exec("create view bewview"+iNr+" as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant"+sPlus+" from bew_pool where pro_aic_protokoll is null and(gueltig is null or("+sV+" is null or "+sV+" <= gueltig) and("+sB+" is null or "+sB+" > gueltig))");
	                exec("create view bewview"+iNr+"d as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant,pro_aic_protokoll"+sPlus+" from bew_pool where (gueltig is null or ("+sV+" is null or "+sV+" <= gueltig) and ("+sB+" is null or "+sB+" > gueltig))");	                
	                exec("create view poolview"+iNr+" as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von,aic_daten,aic_protokoll from stammpool where pro2_aic_protokoll is null and ("+
	                		sB+" is null or gultig_von is null or gultig_von < "+sB+") and (gueltig_bis is null or gueltig_bis >= "+sB+")");
	                
	        	}
	        	if (!Static.Gleich(dtV, TabVB.getDate(iPos, "von")))
	        	{
	        		TabVB.setInhalt(iPos, "von", dtV);
	        		exec("set @von"+sNr+"="+DateTimeToString(dtV));
	        	}
	        	if (!Static.Gleich(dtB, TabVB.getDate(iPos, "bis")))
	        	{
	        		TabVB.setInhalt(iPos, "bis", dtB);     		
	        		exec("set @bis"+sNr+"="+DateTimeToString(dtB));
	        	}
    		}
    		else if (MS())
    		{
    			boolean bVV=exists("select name,crdate from sysobjects where xtype='V ' and name='stammview"+iNr+"'",null);
    			boolean bNV=!bVV;
    			if (!bVV || !Static.Gleich(dtV, TabVB.getDate(iPos, "von")))
	        	{
	        		TabVB.setInhalt(iPos, "von", dtV);
	        		bNV=true;
	        	}
	        	if (!bVV || !Static.Gleich(dtB, TabVB.getDate(iPos, "bis")))
	        	{
	        		TabVB.setInhalt(iPos, "bis", dtB);     		
	        		bNV=true;
	        	}
	        	if (bNV)
	        	{
	        		String sV=DateTimeToString(dtV);
	        		String sB=DateTimeToString(dtB);	
	        		exec((bVV ?"alter":"create")+" view stammview"+iNr+" as select aic_stamm,kennung2 kennung,bezeichnung,aic_stammtyp2 aic_stammtyp,aic_mandant,ab,firma,anr,eintritt,austritt,aic_rolle from stamm_protokoll"+
	            			" where pro_aic_protokoll is null and ("+sV+" is null or austritt is null or austritt >= "+sV+") and ("+sB+" is null or eintritt is null or eintritt < "+sB+
	                        ") and ("+sB+" is null and weg is null or (ab is null or "+sB+">ab) and (weg is null or "+sB+"<=weg))");
	        		String sPlus=",anr,ldate,BEW2_AIC_BEW_POOL,ZONE,ANR2,ANR3,ANR4,ANR5,ANR6,ANR7,ANR8,ANR9,LDATE2,LDATE3,BOOL1,BOOL2,AIC_PROTOKOLL,LDateVon,LDateBis";
	                exec((bVV ?"alter":"create")+" view bewview"+iNr+" as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant"+sPlus+" from bew_pool where pro_aic_protokoll is null and(gueltig is null or("+sV+" is null or "+sV+" <= gueltig) and("+sB+" is null or "+sB+" > gueltig))");
	                exec((bVV ?"alter":"create")+" view bewview"+iNr+"d as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant,pro_aic_protokoll"+sPlus+" from bew_pool where (gueltig is null or ("+sV+" is null or "+sV+" <= gueltig) and ("+sB+" is null or "+sB+" > gueltig))");
	                exec((bVV ?"alter":"create")+" view poolview"+iNr+" as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von,aic_daten,aic_protokoll from stammpool where pro2_aic_protokoll is null and ("+
	                		sB+" is null or gultig_von is null or gultig_von < "+sB+") and (gueltig_bis is null or gueltig_bis >= "+sB+")");
	        	}
    		}
    		else
    			printError("setVonBisL: Datenbanktyp "+getDB_Typ()+" wird noch nicht unterstützt");
        	if (sZA != null)
        		TabVB.setInhalt(iPos, "ZA", sZA);  
        	if (VecPer!= null)
        		TabVB.setInhalt(iPos, "VecPer", VecPer); 
        	return iNr;
        }

        public void setVonBis(Date dtV,Date dtB,boolean b)
        {
          setVonBis(new java.sql.Timestamp(dtV.getTime()),new java.sql.Timestamp(dtB.getTime()));
        }
        
        public void setVonBis(java.sql.Timestamp dtV,java.sql.Timestamp dtB)
        {
        	setVonBis(dtV,dtB,false);
        }
        
        public Date getVortag(java.sql.Timestamp dtB)
        {
        	if (dtB==null)
        		return null;
        	return new Date(new DateWOD(dtB).yesterday());
        }

        public void setVonBis(java.sql.Timestamp dtV,java.sql.Timestamp dtB,boolean b)
        {
        	//fixtestError("setVonBis:"+dtV+"/"+dtB+"/"+bVonInt);
          if(b || !Static.Gleich(dtV,dtVon) || !Static.Gleich(dtB,dtBis))
          {
        	  long lClock2=Static.get_ms();
            if (dtV==null || dtB==null)
              testInfo(iSid + "-setVonBis:"+dtV+"-"+dtB);
            else
            {
              //fixInfo("setVonBis "+iSid+":"+TS_String(dtVon)+"->"+TS_String(dtV)+","+TS_String(dtBis)+"->"+TS_String(dtB));
              String sFormat = "yyyy-MM-dd HH:mm:ss z(Z)";
              testInfo(iSid + "-setVonBis:" + new SimpleDateFormat(sFormat).format(dtV) + " - " + new SimpleDateFormat(sFormat).format(dtB));
            }
            String s= ASA() ? "set von=" + DateTimeToString(dtV) + ";set bis=" + DateTimeToString(dtB):
            	"update bereich set von="+DateTimeToString(dtV)+",bis="+DateTimeToString(dtB)/*+(bVonInt ? ",vonInt="+Static.DateToInt(dtV)+",bisInt="+Static.DateToInt(getVortag(dtB)):"")*/+" where connid=";
            checkUpdateBereich(s,"von-bis");
            dtVon=dtV;
            dtBis=dtB;
            //fixInfo("Sid"+iSid+":"+getVonBis2());
            fixtestInfo(iSid+"setVonBis auf "+dtV+"-"+dtB+" gesetzt");
//            if (dtVon==null || dtBis==null)
//          	  fixtestError("Zeitraum-von/bis ist null");
            //g.clockInfo2("set von/bis="+dtV+"-"+dtB, lClock2);
            lClockVB+=Static.get_ms()-lClock2;
          }
        }     
        
        private void checkUpdateBereich(String s,String sErr)
        {
        	int iA=exec(s+Sid());
        	int iAnz=0;
        	if (iMaxB<1 && iA<1)
        	{
        		printError("Bereichszeile ("+sErr+") setzen wurde ausgeschaltet");
        	}
        	else if (!ASA() && (iA<1))
            {
            	exec("insert into bereich (connid) values ("+iSid+")");
            	printError("Bereichszeile für "+Sid()+" hat gefehlt");
            	while (iA<1 && iAnz<iMaxB)
            	{
            	  iAnz++;
            	  Static.sleep(1000);
            	  iA=exec(s+Sid());
            	}     
	            if (iA<1)
	            {
	            	printError(sErr+" konnte nach "+iAnz+"x nicht gesetzt werden!");
	            	System.exit(2);
	            }
	            else
	            	printError(sErr+" konnte nach "+iAnz+"x gesetzt werden");
            }
        }

	public void setVon(java.sql.Timestamp dt)
	{
		if(!Static.Gleich(dt,dtVon))
		{
			long lClock2=Static.get_ms();
                  //fixInfo("setVon "+iSid+":"+TS_String(dtVon)+"->"+TS_String(dt));
                  fixtestInfo(iSid+"-setVon:"+dt);
                  String s="update bereich set von="+DateTimeToString(dt)/*+(bVonInt ? ",vonInt="+Static.DateToInt(dt):"")*/+" where connid=";
                  checkUpdateBereich(s,"von");
                  dtVon=dt;                
//                  if (dtVon==null)
//                	  fixtestError("Zeitraum-von ist null");
                  //g.clockInfo2("set von="+dt, lClock2);
                  fixtestInfo(iSid+"setVon auf "+dtVon+" gesetzt");
            lClockVB+=Static.get_ms()-lClock2;
                  //fixInfo("Sid"+iSid+":"+getVonBis2());
                  //new Exception().printStackTrace();
		}
	}

	public void setBis(java.sql.Timestamp dt)
	{
		if(!Static.Gleich(dt,dtBis))
		{
			long lClock2=Static.get_ms();
                  //fixInfo("setBis "+iSid+":"+TS_String(dtBis)+"->"+TS_String(dt));
                  fixtestInfo(iSid+"-setBis:"+dt);
                  String s="update bereich set bis="+DateTimeToString(dt)/*+(bVonInt ? ",bisInt="+Static.DateToInt(getVortag(dt)):"")*/+" where connid=";
                  checkUpdateBereich(s,"bis");
                  dtBis=dt;                  
//                  if (dtBis==null)
//                	  fixtestError("Zeitraum-bis ist null");
            //g.clockInfo2("set bis="+dt, lClock2);
                  fixtestInfo(iSid+"setBis auf "+dtBis+" gesetzt");
            lClockVB+=Static.get_ms()-lClock2;
                  //fixInfo("Sid"+iSid+":"+getVonBis2());
		}
	}

	/*public void setJoker(int i)
	{
		iJoker=i;
	}

        public void setDoubleJoker(double d)
        {
                dJoker=d;
        }

	public void setStringJoker(String s)
	{
		sJoker=s;
	}

	public java.sql.Date getStichtag()
	{
		return dtStichtag;
	}*/

	public java.sql.Timestamp getVon()
	{
		return dtVon;
	}

        public String getLDATE()
        {
          if (dtVon == null || dtBis == null)
          {
            if (dtVon == null && dtBis == null)
              return "";
            else if (dtBis== null)
              return " and (LDATE >="+Static.DateToInt(dtVon)+" or LDATE=0)";
            else
              return " and LDATE <"+Static.DateToInt(dtBis);
          }
          else if (((dtBis.getTime()-dtVon.getTime())/3600000/24)>800)
              return " and (LDATE >="+Static.DateToInt(dtVon)+" and LDATE <"+Static.DateToInt(dtBis)+" or LDATE=0)";
          else if (dtBis.getTime()<=dtVon.getTime())
            return " and LDATE=0";
          else
          {
            Vector<Integer> Vec=new Vector<Integer>();
            Vec.addElement(Static.Int0);
            Vec.addElement(Static.DateToInt(dtVon));
            DateWOD DW=new DateWOD(dtVon);
            DateWOD DW2=new DateWOD(dtBis);
            boolean b=true;
            while (b)
            {
              DW.tomorrow();
              b=DW.before(DW2);
              if (b)
                Vec.addElement(Static.DateToInt(DW.toTimestamp()));
            }
            return " and LDATE"+(Vec.size()==1?" = "+Vec.elementAt(0):Static.SQL_in(Vec));
          }
        }

	public java.sql.Timestamp getBis()
	{
		return dtBis;
	}

        /*public int getiJoker()
	{
		return iJoker;
	}

        public double getdJoker()
        {
                return dJoker;
        }

	public String getJoker()
	{
		return sJoker;
	}*/

	/*public String getVonBis(String sFormat,boolean bVortag)
	{
		progInfo("getVonBis:"+sFormat+"/"+bVortag+"/"+dtVon+"/"+dtBis);
		java.sql.Timestamp dtBis2=dtBis;
		if (dtBis != null && bVortag)
		{
			DateWOD dtMom = new DateWOD(dtBis);
			dtMom.setTimeZero();
			dtMom.yesterday();
			dtBis2=dtMom.toTimestamp();
		}
		return (dtVon==null ?"":new SimpleDateFormat(sFormat).format(dtVon))+" - "+(dtBis==null ?"":new SimpleDateFormat(sFormat).format(dtBis2));
	}*/
	
	public String getVB(int iVB)
	{
		DateWOD DWVon=new DateWOD(getVon(iVB));
		DateWOD DWBis=new DateWOD(getBis(iVB));
		String sVon=null;
		String sBis=null;
		if (DWVon.isNull())
			sVon="";
		else if (DWVon.hasTime())
			sVon=DWVon.Format("dd.MM.yyyy HH:mm");
		else if (DWVon.getYear()==DWBis.getYear())
			sVon=DWVon.Format("dd.MM.");
		else
			sVon=DWVon.Format("dd.MM.yyyy");
		if (DWBis.isNull())
			sBis="";
		else if (DWBis.hasTime())
			sBis=DWBis.Format("dd.MM.yyyy HH:mm");
		else
			sBis=DWBis.Format("dd.MM.yyyy");
		return sVon+"-"+sBis;
	}
	
	public String getVB()
	{
		String sFormat=sZeitart.equals("Tag") ? "yyyy-MM-dd":sZeitart.equals("Woche") ? "yyyy-ww":sZeitart.equals("Monat") || sZeitart.equals("Quartal") ? "yyyy-MM":"yyyy";
		java.sql.Timestamp dtBis2=dtBis;
		if (dtBis != null)
		{
			DateWOD dtMom = new DateWOD(dtBis);
			dtMom.setTimeZero();
			dtMom.yesterday();
			dtBis2=dtMom.toTimestamp();
		}
		String sV2=dtVon==null ?"":new SimpleDateFormat(sFormat).format(dtVon);
		String sB2=dtBis==null ?"":new SimpleDateFormat(sFormat).format(dtBis2);
		return (sZeitart.equals("Woche") ? Static.sKW:"")+sV2+(!sV2.equals(sB2) ? "-"+sB2:"");
	}

	public String getVonBis(String sFormat,boolean bTagesanzeige)
	{
		java.sql.Timestamp dtBis2=dtBis;
		if (dtBis != null)
		{
			DateWOD dtMom = new DateWOD(dtBis);
			dtMom.setTimeZero();
			dtMom.yesterday();
			dtBis2=dtMom.toTimestamp();
		}
		/*java.sql.Timestamp dtVon2=dtVon;
		if (dtVon != null && Static.sZeitart.equals("Woche"))
		{
			DateWOD dtMom = new DateWOD(dtVon);
			dtMom.setTimeZero();
			for(int i=0;i<6;i++)
				dtMom.tomorrow();
			dtVon2=dtMom.toTimestamp();
		}*/
//        if (sZeitart==null)
//        	sZeitart="Tag";
		String sVon=Static.FormatTS3(dtVon,sZeitart);
		String sBis=Static.FormatTS3(dtBis2,sZeitart);
		String sV2=dtVon==null ?"":new SimpleDateFormat(sFormat).format(dtVon);
		String sB2=dtBis==null ?"":new SimpleDateFormat(sFormat).format(dtBis2);
		if (sZeitart.equals("Tag"))
			return sV2.equals(sB2) && dtVon != null ? new SimpleDateFormat("EEEE, d.M.yyyy",DateWOD.DFS).format(dtVon):sV2+" - "+sB2;
		else
			return sVon+(sBis.equals(sVon) ? "":" - "+sBis)+(bTagesanzeige?" ("+sV2+(sV2.equals(sB2)?"":" - "+sB2)+")":"");
	}

        public String getVonBis2()
        {
                java.sql.Timestamp dtBis2=dtBis;
                if (dtBis != null)
                {
                        DateWOD dtMom = new DateWOD(dtBis);
                        dtMom.setTimeZero();
                        dtMom.yesterday();
                        dtBis2=dtMom.toTimestamp();
                }
//                if (sZeitart==null)
//                  return (dtVon==null ? "null":new SimpleDateFormat("EE,d.M.yyyy",DateWOD.DFS).format(dtVon))+"-"+new SimpleDateFormat("EE,d.M.yyyy",DateWOD.DFS).format(dtBis2);
                String sVon=Static.FormatTS(dtVon,sZeitart);
                String sBis=Static.FormatTS(dtBis2,sZeitart);
                if (sZeitart.equals("Tag"))
                        return sVon.equals(sBis) ? new SimpleDateFormat("EE,d.M.yy",DateWOD.DFS).format(dtVon):sVon+"-"+sBis;
                else
                        return sVon+(sBis.equals(sVon) ? "":"-"+sBis);
        }

	public String getVonSql()
	{
		return DateTimeToString(dtVon);
	}

	public String getBisSql()
	{
		return DateTimeToString(dtBis);
	}

	/*public void OracleInfo(String s)
	{
		if (Oracle())
			fixInfo("(O)"+s);
	}*/
	public String ErrorFile()
    {
//    	if (DirError==null)
//    		DirError=
      return Static.DirError+sSub+(sUser==null ? "":"_"+sUser);
    }

        public void diskInfo(String s)
        {
          if (SuperUser() || bTestPC || Info())
            fixInfo("(+)"+s);
          if(Static.DirError != null)
            if (!Save.prot(new java.io.File(ErrorFile() + ".inf"), s))
              printError("Protokollieren in "+ErrorFile() + ".inf nicht möglich");
        }
        
        public boolean delInfo(String s,String sTab)
        {
        	int iAnz=exec(s);
        	if (iAnz<0)
        		return false;
        	if (iAnz>0)
        		diskInfo(iAnz+" "+sTab+" gelöscht");
        	return true;
        }

	public void printInfo(String s)
	{
		if (Info())
			fixInfo("(I)"+s);
	}

	public void fixInfo(String s)
	{
		if (s.equals(sInfoOld))
			return;
		sInfoOld=s;
		if (bAusgabe)
			System.out.print(s+"\n");
		if (Static.bSave && Static.DirError != null)
			Save.prot(new java.io.File(ErrorFile()+".log"),s);
	}
	
	public static void fixInfoS(String s)
	{
		if (bAusgabe)
			System.out.print(s+"\n");
		if (Static.bSave && Static.DirError != null)
			Save.prot(new java.io.File(Static.ErrorFile()+".log"),s);
	}
	
//	public static void styleInfo(boolean b,String s,Object P)
//	{
//		if (b && Static.bShowStyle)
//			fixInfo("Style-"+s+"="+(P instanceof TreeTableColumn ? ((TreeTableColumn)P).getStyleClass():
//		P instanceof Parent ? ((Parent)P).getStyleClass()+":"+((Parent)P).getPseudoClassStates():P instanceof MenuItem ? ((MenuItem)P).getStyleClass():P instanceof ContextMenu ? ((ContextMenu)P).getStyleClass():"???"));
//	}
	
//	public static void setStyle(boolean b,String s,String sText,Parent P)
//    {
//    	if (b)
//  		  P.getStyleClass().add(s);
//    	else
//  		  P.getStyleClass().remove(s);
//    	styleInfo(true,sText,P);
//    	//if (Static.bShowStyle)
//    	//	System.out.println("Style-Cbo="+getStyleClass());
//    }

	public void clockInfo(String s,long lClock)
	{
		if (Clock())
			fixInfo("(C)["+s+"]: "+(Static.get_ms()-lClock)+" ms"+(TestPC()?", "+getCount():""));
	}

        public void clockMikroInfo(String s,long lClock)
        {
          if (Clock())
            fixInfo("(C)["+s+"]: "+new java.text.DecimalFormat("#,##0").format(Static.get_Mikro_s()-lClock)+" µs");
        }

        public void clockInfo(String s,long lClock,int i)
        {
          long l=Static.get_ms()-lClock;
          if (Clock() && l>i*1000)
            fixInfo("(C)["+s+"]: "+l+" ms"+(TestPC()?", "+getCount():""));
        }

        public void clockInfo2(String s,long lClock)
        {
          long l=Static.get_ms()-lClock;
          if (l>iClock2Ab && (iInfos&CLOCKSUB)>0)
            fixInfo("(C2)["+s+"]: "+l+" ms"+(TestPC()?", "+getCount():""));
        }

        public boolean Clock2()
        {
          return (iInfos&CLOCKSUB)>0;
        }
        
        public long clockInfo3(String s,long lClock)
        {
          long l2=Static.get_ms();
          long l=l2-lClock;
          if ((iInfos&CLOCK3)>0)
            fixInfo("(C3)["+s+"]: "+l+" ms"+(TestPC()?", "+getCount():""));
          return l2;
        }

        public boolean Clock3()
        {
          return (iInfos&CLOCK3)>0;
        }
        
        public boolean Mal2()
        {
          return (iInfos&MAL2)>0;
        }

        public void clockStart()
        {
          lClockx=Static.get_ms();
        }

        public void clockInfo(String s)
        {
          long lClockMom=Static.get_ms();
                if (Clock() && Prog())// && lClockMom != lClockLast)
                        fixInfo("(CP)["+s+"]: "+(lClockMom-lClockx)+" ms"+(TestPC()?", "+getCount():""));
          //lClockLast=lClockMom;
        }

	public void printInfo(int i,String s)
	{
		if (i==0 && (iInfos&INFO1)>0 || i==1 && (iInfos&INFO2)>0 || i==2 && (iInfos&INFO3)>0 || i==3 && (iInfos&EXEC)>0)
			fixInfo("("+(i+1)+")"+s);
	}

        public void fehlInfo(String s,boolean bSystem)
	{
          if ((iInfos&FEHLENDE)>0)
            fixInfo("(F)"+s);
          if (bSystem)
        	  fixtestError(s);
        }

	public void saveInfo(String s)
	{
		if (Static.bSpeichernAnzeigen)
			fixInfo("(S)"+s);
	}

        public int getSqlAnzahl()
        {
          return iOpen+iExec;
        }

        /*private void saveSql(String s)
        {
          if (Prog() && bTestPC && !Static.FehlerVerzeichnis.equals("*"))
            Save.prot(new java.io.File(Static.FehlerVerzeichnis+Static.sIP+"sql2.log"),(iOpen+iExec)+":"+(s.length()>200?s.substring(0,200):s));
        }*/

	/*public void saveSqlTime(String sArt,int iAnzahl,long lZeit)
	{
		saveSqlTime(sArt,iAnzahl,lZeit,null,0);
	}*/

        public static String TS_String(Object Obj)
        {
                //return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((java.sql.Timestamp)Obj);
                if (Obj==null)
                  return "null";

                String s=Obj instanceof java.sql.Timestamp ? ((java.sql.Timestamp)Obj).toString():new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0").format(Obj);
                return s.substring(0,s.endsWith("00:00:00.0") ? 10:19);
        }

        public void saveSqlTime(String sArt,int iL,long lZeit,String s,int iAnz,int iAIC)
        {
          saveSqlTime(sArt,iL,lZeit,s,""+iAnz,""+iAIC);
        }

	public void saveSqlTime(String sArt,int iL,long lZeit,String s,String sAnz,String sAIC)
	{
		//if (lQryAb>-1)
		//{
                  //if (lZeit>9999 && s!=null)
                  //  progInfo(s+"\nDauer:"+lZeit+" ms");
                  if (Static.bSQL)
                    fixInfo((iOpen + iExec)+":"+s);
                  if (Static.DirError != null && (lQryAb==0 || lZeit>=lQryAb || iL==0 && Static.lAbfAb!=0))
                  {
//                    if (sArt.equals("tab"))
//                      iL=iL+0;
                      String sTitel="nr\tart\tlen\tclock\tcount\tAIC\tvon\tbis\tmem\tcomment";
                    if(!Save.prot(sTitel,new java.io.File(ErrorFile() + "_sql.log"),
                              (iOpen + iExec) + "\t" + sArt + "\t" + iL + "\t" + lZeit + "\t" + sAnz + "\t" + sAIC + "\t" +
                              TS_String(dtVon) + "\t" + TS_String(dtBis) +"\t" + Static.Mem() + (s==null? "":"\t" +
                              (iSqlL>0 && s.length()>iSqlL ? s.substring(0,iSqlL):s)),false))
                          printError("Protokollieren in "+ErrorFile() + "_sql.log nicht möglich");
                  }
                  //if (s!=null)
                  //  Save.prot(new java.io.File(Static.FehlerVerzeichnis+Static.sIP+"sql2.log"),(iOpen+iExec)+":"+(s.length()>220?s.substring(0,220):s));
                //}
			//Save.prot(new java.io.File(Static.FehlerVerzeichnis+Static.sIP+"sql.log"),(iAnzahl==0?bExec?"Log":"Error":bExec?"Exec":"Open")+"\t"+iAnzahl+"\t"+lZeit);
	}

	public void progInfo(String s)
	{
		if (Def() && (iInfos&NO_INFO)==0)
			fixInfo("(P)"+s);
	}

	public void testInfo(String s)
	{
		if (bTest || bTestPC && (iInfos&NO_INFO)==0)
			fixInfo("(T)"+s);
	}

    public void fixtestInfo(String s)
    {
      if ((iInfos&NO_FIX)==0 && (bTest || bTestPC || bAppl) || bDebug)
        fixInfo("(x)"+s);
    }

    public void fixInfoT(String s)
    {
    	if (bTestPC || bAppl || Def())
    		fixInfo("(T)"+s);
    }
    
    public void fixtestError(String s)
    {
      if (/*(iInfos&NO_FIX)==0 &&*/ (bTest || bTestPC || bAppl || Def()))
      {
        //fixInfo("(x)"+s);
    	  if (bAusgabe)
  			System.err.print("(x)"+s+"\n");
    	  if (Static.bSave && Static.DirError != null)
    		  Save.prot(new java.io.File(ErrorFile()+".warn"),s);
//  			if (!Save.prot(new java.io.File(Static.ErrorFile()+"x.err"),s) && g!=null)
//          {
//            String sError="Protokollieren in "+Static.ErrorFile() + "x.err nicht möglich";
//            g.printError(sError);
//            if (Static.bX11 && g.iAnlage==0)
//              JOptionPane.showMessageDialog(new JFrame(),sError,"Fehler",JOptionPane.ERROR_MESSAGE);
//            g.unConnect(true);
//            System.exit(-2);
//          }
      }
    }
    
    public static void fixError(Transact g,String s)
    {
    	if (g==null)
    		Static.printError(s);
    	else
    	  g.fixError(s);
    }
    
    public void fixError(String s)
    {
    	if (Static.VecErrorAll != null)
    		Static.VecErrorAll.addElement(s);
    	VecLError.addElement(s);
    	if (bAusgabe)
  			System.err.print("Error:"+s+"\n");
    	if (Static.bSave && Static.DirError != null)
    		Save.prot(new java.io.File(ErrorFile()+".err"),s);
    }

	public boolean Prog()
        {
          return (iBits&cstBenutzerEbene)==cstProg;
        }

	public boolean Def()
	{
		int i=iBits&cstBenutzerEbene;
        return i==cstProg || i==cstDef;
	}
	
	public boolean Verwaltung()
	{
		int i=iBits&cstBenutzerEbene;
		return i==cstProg || i==cstDef || i==cstVerw;
	}

	public boolean SuperUser()
	{
		int i=iBits&cstBenutzerEbene;
		return i==cstProg || i==cstDef || i==cstVerw || i==cstSuperUser;
	}

	/*public void addTemp(String s)
	{
		sTemp+=s;
	}

	public void tempInfo(String s)
	{
		if (Prog() && !sTemp.equals(""))
			fixInfo(sTemp);
		sTemp=s;
	}*/

	public void defInfo(String s)
	{
		if (Def() && (iInfos&NO_INFO)==0)
			fixInfo("(D)"+s);
	}

        public void defInfo2(String s)
        {
                if (bTest || (Def() || bTestPC) && (iInfos&NO_INFO)==0)
                        fixInfo("(DT)"+s);
        }

	public boolean Debug()
	{
		return bDebug;
	}

	public void setDebug(boolean b)
	{
		if (b!=bDebug)
			fixtestError("Debug "+(b ? "ein":"aus"));
		bDebug = b;
	}
	
	public void debugInfo(String s)
	{
		debugInfo(s,false);
	}

	public void debugInfo(String s,boolean bImmer)
	{
		if (bDebug || bImmer)
			fixInfo("(*)"+s);
	}

	public void initSI()
	{
		printInfo(0,Static.rightString("initSI",20)+":"+Static.printZahl(Static.Mem(),11));
		System.runFinalization();
		printInfo(0,Static.rightString("runFinalization",20)+":"+Static.printZahl(Static.Mem(),11));
		System.gc();
		printInfo(0,Static.rightString("gc",20)+":"+Static.printZahl(Static.Mem(),11));
		lMem1 = Static.Mem();
		lMemD = 0;
		lAnz = 0;
		lClock1 = Static.get_ms();
	}

	public void printSI(String s)
	{
		if ((iInfos&INFO1)>0)
		{
			long lClock2 = Static.get_ms();
			System.gc();
			long lMem = Static.Mem();
			long lMem2= lMem-lMem1;
			lMem1 = lMem;
			lMemD+=lMem2;
			lMem3+=lMem2;
			lAnz++;
			/*if (sInfo.length()>0)
			{
				printInfo(0,sInfo);
				sInfo="";
			}*/
			printInfo(0,Static.rightString(s,20)+":"+Static.printZahl(lMem2,11)+"  t:"+Static.printZahl(lClock2-lClock1,6)+"/"+Static.printZahl(lClock2-lClock0,7)+"  avg:"+Static.printZahl(lMemD/lAnz,11)+"  sum:"+Static.printZahl(lMem3,11));
			if (lMem2<0)
				lMem3=0;
			//lMem1 = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
			lClock1 = Static.get_ms();
		}
	}

	/*public int add(String s)
	{
		sInfo += s;
		int i=sInfo.length();
		if (i>80)
		{
			printInfo(0,sInfo);
			sInfo="";
		}
		return i;
	}*/

	public boolean Info()
	{
		return (iInfos&INFO)>0;
	}

        public boolean Clock()
        {
                return (iInfos&CLOCK)>0;
        }

	/*public void addInfo(String s)
	{
		if (bZeigeInfo)
			sSQL_Info = sSQL_Info + iOpen+':'+s + "\n";
	}

	public String getInfo()
	{
		return(sSQL_Info);
	}*/

	/*private void setLblGlobal()
	{
		LblGlobal.setText("C="+iConnect+", O="+iOpen+", E="+iExec+", D="+(lDauer_Ges/(iOpen+iExec))+", F="+iOpenFehler);
	}*/

	/*public void OpenFehler()
	{
		iOpenFehler++;
		//iOpenGesamt++;
		setLblGlobal();
		//bZeigeInfo = true;
		addInfo("! ! ! ! ! ! ! Fehler ! ! ! ! ! ! !");
	}*/

	/*public powersoft.powerj.db.java_sql.Query getQry()
	{
		return new powersoft.powerj.db.java_sql.Query(this,null,false);
	}*/

	/*private boolean SafetyOpen(powersoft.powerj.db.java_sql.Query rQry)
	{
          //testInfo("SafetyOpen");
		long lClock = Static.get_ms();
		boolean b = false;
                String sSQL=rQry.getSQL();
		if (SaveConnect())
		{
			iOpen++;
                        rQry.setSQL(sSQL);
			rQry.open();
                        if (Retry()) return SafetyOpen(rQry);
                        b=true;
			Vector Vec = rQry.getExceptionList();
			if (Vec != null)
			{
				b = false;
				String s = ((powersoft.powerj.db.DataSourceException)Vec.elementAt(0)).getMessage();
                                if (bISQL)
                                  sError=s;
                                else
                                {
                                  Static.printError("Transact.SafetyOpen: SQL-Fehler:");
                                  Static.printError(sSQL);
                                  Static.printError("-> " + s);
                                }
				iOpenFehler++;
				printCount();
                                //if (!bASA || Prog())
                                  saveSqlTime("Error",-2,Vec.size(),s,0,-3);
			}
			rQry.moveFirst();
		}
                if (QrySave())
                {
                  rQry.moveLast();
                  saveClock("Open", rQry.getSQL(),lClock,rQry.getRowCount());
                  rQry.moveFirst();
                }
		return(b);
	}*/

        public void saveClock(String sArt,String sSQL,long lClock,int iAnz)
        {
          lClock=Static.get_ms()-lClock;
          lDauer_Ges +=lClock;
          if (lQryAb>-1)
            saveSqlTime(sArt,sSQL.length(),lClock,sSQL,iAnz,-2);
          if ((iOpen+iExec)%100 == 0)
            printCount();
        }

        public void saveClock(String sArt,String sSQL,long lClock,String sAnz,String sAic)
        {
          lClock=Static.get_ms()-lClock;
          lDauer_Ges +=lClock;
          if (lQryAb>-1)
            saveSqlTime(sArt,sSQL.length(),lClock,sSQL,sAnz,sAic);
          if ((iOpen+iExec)%100 == 0)
            printCount();
        }

        public boolean QrySave()
        {
          return lQryAb>-1;
        }

	public String getCount()
	{
          if (iOpen+iExec ==0)
            return "";
          else
            return "Open="+iOpen+", Exec="+iExec+", Dauer="+(lDauer_Ges/(iOpen+iExec))+", Fehler="+iOpenFehler;
	}

        public int getFehler()
        {
          return iOpenFehler;
        }

	public void printCount()
	{
		printInfo(1,getCount());
	}

        public boolean Retry(boolean bImmer)
        {
          boolean bIC=isConnected();
          boolean bConn=!bImmer && bIC;
          if (!bConn && TestPC())
            testInfo("Retry: connect="+ bIC+", immer="+bImmer+", Fremd="+bFremd+", Exit="+bRealExit+", Retry="+bRetry);
          if (bRealExit && !bFremd && !bConn)
          {
            Static.printError("Transact.Retry: exit wegen Verbindungsverlust");
            System.exit(-1);
            return false;
          }
          if (bFremd)
            return false;
          else if (bNextReconnect || bRetry && !bConn)
          {
            if (Static.DirError != null)
            {
              Save.prot(new java.io.File(Static.ErrorFile() + ".err"), "Retry Immer="+bImmer+", isConnected="+bIC+", Retry="+bRetry+", Next="+bNextReconnect+":");
              Save.prot(new java.io.File(Static.ErrorFile() + ".err"),sSQL_Last);
            }
            disconnect();
            //JOptionPane.showMessageDialog(new JFrame(), "keine Datenbank-Verbindung !", "Fehler", JOptionPane.ERROR_MESSAGE);
            bReConnect=true;
            //if (TestPC())
            //  new Exception().printStackTrace();
            fixInfo("wait to connect: "+new DateWOD().Format("yyyy-MM-dd HH:mm:ss"));
            Static.sleep(bNextReconnect ? 100:bFast?2000:60000);
            bNextReconnect=false;
            return true;
          }
          else
            return false;
        }
        
        public boolean Disconnected()
        {
        	return bDisconnect;
        }

        public boolean SaveConnect()
        {
          if (bDisconnect)
          {
        	  if (Version.Test())
        	  {
        		  printError("reconnect nach disconnect");
        		  printStackTrace(new Exception());
        	  }
        	  connect(null);
          }
          if (bRealExit && !bFremd && !isConnected())
          {
            Static.printError("Transact.SaveConnect: exit wegen Verbindungsverlust");
            System.exit(-1);
            return false;
          }
          if (bFremd)
            return true;
          if (!isConnected())
          {
        	  if (Version.Test())
        	  {
        		  printError("not connected");
        		  printStackTrace(new Exception());
        	  }
              connect(null);
          }
          while (Retry(false))
            connect(null);
          return isConnected();
        }

        /*public java.sql.ResultSet open2(String s)
        {
          return open2(s,true,null);
        }

        public java.sql.ResultSet open2(String s,boolean bInfo)
        {
          return open2(s,bInfo,null);
        }*/

        public java.sql.ResultSet open2(String s,boolean bInfo,String sBind)
        {
          //testInfo("open2");
          boolean bNoBind=sBind==null || sBind.equals("");
          //fixInfo("open2:"+s.indexOf('?')+"/"+bNoBind);
          if ((s.indexOf('?')>0) == bNoBind)
          {
            fixtestError("Bind="+sBind+" passt nicht bei:"+s);
            if (bISQL || !bNoBind) 
            	bNoBind = !bNoBind;
          }
          sSQL_Last=s+(bNoBind ?"":"; mit Bind "+sBind);
          long lClock = Static.get_ms();
          bFehler=false;
          java.sql.ResultSet resultSet = null;

          if (bTry || SaveConnect())
          {
            iOpen++;
            try {
              if (bNoBind)
              {
                Statement stmt = conn.createStatement();
                if (iTimeOut>0)
                  stmt.setQueryTimeout(iTimeOut);
                resultSet=stmt.executeQuery(s);
              }
              else
              {
                if (sBind==null || sBind.equals(""))
                  sBind="0";
                //fixtestInfo("Bind bei open2="+sBind);
                //iBind++;
                PreparedStatement stmt = conn.prepareStatement(s);
                stmt.setString(1, sBind);
                if (iTimeOut>0)
                  stmt.setQueryTimeout(iTimeOut);
                resultSet = stmt.executeQuery();
                //if (iBind%10==0)
                //  testInfo("Bind="+iBind);
              }
              //long lClock2 = Static.get_ms()-lClock;
              //testInfo("open2:"+isConnected()+"/"+(s.length()>30?s.substring(0,30):s));
              sError=null;

              //resultSet.last();
              if (bInfo)
                saveClock("open2", s,lClock,-1/*resultSet.getRow()*/);
              else
                lDauer_Ges +=Static.get_ms()-lClock;
              //resultSet.absolute(0);
              //if (lQryAb>-1)
              //  saveSqlTime("open2", s.length(), lClock2, s, -3);
            }
            catch(SQLException e) {
              //testInfo("Exception:"+isConnected()+"/"+(s.length()>30?s.substring(0,30):s));
              int iError=e.getErrorCode();
              String sState=e.getSQLState();
              if (bISQL || bTry)
                fixtestError("Error-Code="+iError+"/"+sState);
              else if (iError==0 && !sState.equals("22025" /*DATA_EXCEPTION_INVALID_ESCAPE_SEQUENCE*/))
              {
                fixInfo("Transact.open2: connection lost");
                saveSqlTime("Lost", s.length(), Static.get_ms() - lClock, s, "-1",sBind);
              }
              else
                printError("Transact.open2: Error-Code="+iError+"/"+sState + " ("+e+")");
              if (bTry)
              {
            	if (bISQL)
                  fixtestError(s+" (Bind="+sBind+") nicht durchführbar");
            	else
            	  printError(s+" (Bind="+sBind+") nicht durchführbar");
                bTry=false;
                return null;
              }
              else
              {
            	boolean bOk=iError==0 && !sState.equals("22025") && !sState.startsWith("0700");
            	if (Static.bInfoSQL || Def())
            	{
            		fixInfo("Transact.open2: Fehler "+iError+" mit SQLState="+sState+"-> ok="+bOk+" bei Bind="+sBind);
            		fixInfo("SQL="+s);
            		if (bISQL)
            		{
            			sError =""+e+"\nError-Code="+iError+" mit SQLState="+sState;
            			bFehler=true;
            			return resultSet;
            		}
            		else
            		{
            		  printError("Transact.open2: "+e+" ("+iError+") mit SQLState="+sState+"-> ok="+bOk+" bei Bind="+sBind);
            		  printError("SQL="+s);
            		  return null;
            		}
            		
            	}
                if (bRealExit && !bFremd && bOk)
                {
                  Static.printError("Transact.open2: exit wegen Verbindungsverlust");
                  System.exit( -1);
                }
                //fixInfo("vor Retry wegen Fehler");
                if (bOk && Retry(iError == 0))
                  return open2(s, bInfo, sBind);
              }
              //fixInfo("nach Retry wegen Fehler");
              bFehler=true;
              sError = "" + e;
              if (iError != 0 && lQryAb>-1)
                saveSqlTime("Error", s.length(), Static.get_ms() - lClock, s + "\n" + e, -2,-3);
              iOpenFehler++;
              if (bISQL)
                Static.addError(sError);
              else
              {
                printCount();
                if (iError==1205)
                {
                  fixInfo("Deadlock-Fehler -> Wiederholung nach 30 s");
                  Static.sleep(30000);
                  return open2(s,bInfo,sBind);
                }
                else if (MS() && sError.indexOf("Abfrageinterner Parallelismus verursachte Deadlock") > 0 && sError.indexOf("MAXDOP 1") > 0)
                {
                  testInfo("!!! verwende MAXDOP !!!");
                  return open2(s + " OPTION (MAXDOP 1)",bInfo,sBind);
                }
                else
                {
                  printError(s);
                  if (sBind!=null)
                    printError("bei Bind="+sBind);
                    printStackTrace(e);
                  
                  //printError("Transact.open2:" + e);
                }
              }
            }
            if ((iOpen+iExec)%100 == 0)
              printCount();
          }
          return resultSet;
        }
        
        public void printStackTrace(Exception e)
        {
        	StackTraceElement[] STE= e.getStackTrace();
        	printError(e.toString());
        	for (int i=0;i<STE.length;i++)
        	{
        	  String s=STE[i].toString();
        	  //fixInfo(s);
        	  if (s.indexOf("All_Unlimited")>-1)
        		  printError("  at "+s);
        	}
        }
        
        public static void printStackTraceS(Exception e)
        {
        	StackTraceElement[] STE= e.getStackTrace();
        	Static.printError(e.toString());
        	for (int i=0;i<STE.length;i++)
        	{
        	  String s=STE[i].toString();
        	  //fixInfo(s);
        	  if (s.indexOf("All_Unlimited")>-1)
        		  Static.printError("  at "+s);
        	}
        }
        
        public static void printStackTrace(Transact t,Exception e)
        {
        	if (t==null)
        		printStackTraceS(e);
        	else
        		t.printStackTrace(e);
        }
        
        public boolean insertFile(int iDaten,String sFilename,String sSQL)
        {
        	return insertFile(iDaten,sFilename,null,null,sSQL);
        }

        public boolean insertFile(int iDaten,String sFilename,String sFileBild,String sFileMini,String sSQL)
        {
          long lClock = Static.get_ms();
          try
          {
//        	fixtestInfo("insertFile1:"+sFilename);
        	if (sFileBild==null)
        		sFileBild=sFilename;
            if (sFileBild.startsWith("file:"))
            	sFileBild=sFileBild.substring(sFileBild.charAt(5)==File.separator.charAt(0) ? 5:6);
//            fixtestInfo("insertFile2:"+sFilename);
            File file = new File(sFileBild);
            FileInputStream fis = new FileInputStream(file);
            PreparedStatement ps = conn.prepareStatement(sSQL);
            if (sFilename.indexOf(File.separator)>=0)
              sFilename=sFilename.substring(sFilename.lastIndexOf(File.separator)+1);
            ps.setString(1, ""+iDaten);
            ps.setString(2, iDaten+"_"+sFilename);
            ps.setBinaryStream(3, fis, (int)file.length());
            ps.executeUpdate(); 
            ps.close();
            fis.close();
            saveClock("insertFile", sSQL,lClock,-1);
            return true;
          }
          catch(Exception e)
          {
            printError("Transact.insertFile:"+sFilename+"->"+e);
            return false;
          }
        }
        
        public boolean SaveImage(BufferedImage img,String sFile,String sSQL,String sExt)
        {
        	long lClock = Static.get_ms();
            try
            {
            	ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, sExt, baos);
                byte[] bytes = baos.toByteArray();
                PreparedStatement ps = conn.prepareStatement(sSQL);
                ps.setBytes(1, bytes);
                ps.executeUpdate(); 
                ps.close();
            	saveClock("SaveImage", sSQL,lClock,-1);
                return true;
            }
            catch(Exception e)
            {
              printError("Transact.SaveImage:"+sFile+"->"+e);
              return false;
            }
        }
        
        public boolean UpdateMini(String sFile,String sSQL)
        {
//        	fixtestError("UpdateMini "+sFile+" mit "+sSQL);
        	long lClock = Static.get_ms();
            try
            {
            	File file = new File(sFile);
                FileInputStream fis = new FileInputStream(file);
                PreparedStatement ps = conn.prepareStatement(sSQL);
//                fixtestError("UpdateMini "+file.getPath()+": size="+file.length()+" am "+new Date(file.lastModified())+", free: "+file.getFreeSpace());
                ps.setBinaryStream(1, fis, (int)file.length());
                ps.executeUpdate(); 
                ps.close();
                fis.close();
                saveClock("UpdateMini", sSQL,lClock,-1);
                return true;
            }
            catch(Exception e)
            {
              printError("Transact.UpdateMini:"+sFile+"->"+e);
              return false;
            }
        }

        public int insert(String s)
        {
          long lClock = Static.get_ms();
          try
          {
            Statement stmt = conn.createStatement();
            if (iTimeOut>0)
              stmt.setQueryTimeout(iTimeOut);
//            if (Def())
//                  printInfo(3,"insert:"+s);
            if (stmt.executeUpdate(s, Statement.RETURN_GENERATED_KEYS) > 0)
            {
              ResultSet rs = stmt.getGeneratedKeys();
              if (rs != null && rs.next())
              {
                int iResult = rs.getInt(1);
                iExec++;
                printExec(" * "+s,false,true);
                saveClock("insert", s,lClock,iResult);
                return iResult;
              }
            }
          }
          catch(SQLException e)
                        {
                          int iError=e.getErrorCode();
                          testInfo("Error-Code="+iError);
                          boolean bNoConnection=iError==0 && e.getSQLState().charAt(2)=='S';
                          if (Retry(bNoConnection)) return insert(s);
                          //lClock=Static.get_ms()-lClock;
                          //lDauer_Ges +=lClock;
                          //if (!bASA || Prog())
                          //fixInfo("Exception bei insert:"+e);
                          if (bISQL)
                            sError=""+e;
                          else if (bNoConnection)
                            fixInfo("Transact.insert: connection lost");
                          else
                          {
                        	iOpenFehler++;
                            if (/*iError != 0 &&*/ lQryAb>-1)
                              saveSqlTime("Error",s.length(),Static.get_ms()-lClock,s+"\n"+e,-2,-3);
                            printError("Transact.insert: Error-Code="+iError+"/"+e.getSQLState()+" ("+e+")");
                            printError(s);
                          }
                          return -2;
                        }
          printError("Transact.insert nicht durchgeführt:"+s);
          return -3;
        }
        
        public int exec(String s)
        {
        	return exec(s,false);
        }

        public int exec(String s,boolean bSingle)
        {
		//System.gc();
		//long lMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		long lClock = Static.get_ms();
                sSQL_Last=s;
                bFehler=false;
		printExec("   "+s,false,true);
//		if (Def())
//                  printInfo(3,"  Exec:"+s);
                //saveSql(s);
		//testInfo("connected:"+isConnected());
		//boolean bConnect=isConnected();
		if (bSingle || SaveConnect())
		{

			try
			{
				Statement stmt= conn.createStatement();
                                if (iTimeOut>0)
                                  stmt.setQueryTimeout(iTimeOut);
                                sError=null;
                                int iAnz=-1;
				if (stmt.execute(s) && !s.startsWith("OPTIMIZE"))
                                {
                                  fixInfo("************ > RESULTSET="+stmt.getResultSet());
                                  stmt.getResultSet().close();
                                }
                                else
                                  iAnz=stmt.getUpdateCount();
                                //conn.commit();
                                stmt.close();
				stmt=null;
                                iExec++;
                                saveClock("exec", s,lClock,iAnz);
				return iAnz;
			}
			catch(SQLException e)
			{
                          int iError=e.getErrorCode();
                          String sState=e.getSQLState();
                          sError=""+e;
                          sLastError=sError;
                          /*int i=0;
                          while (e != null)
                          {
                            iError=e.getErrorCode();
                            sState=e.getSQLState();
                            testInfo(i+".: getSQLState="+sState+", getErrorCode="+iError);
                            e=e.getNextException();
                            i++;
                          }*/
                          boolean bNoConnection=iError==0 && sState.charAt(2)=='S';
                          if (bISQL)
                          {
                        	  if (Prog())
                        		  fixInfo("Error-Code="+iError+"/"+sState+" ("+sError+")");
                          }
                          else if (bNoConnection)
                            fixInfo("Transact.exec: connection lost: "+sState);
                          else
                            printError("Transact.exec: Error-Code="+iError+"/"+sState+" ("+sError+")");
                          if (bSingle)
                        	  return -4;
                          if (bRealExit && !bFremd && bNoConnection)
                          {
                            Static.printError("Transact.exec: exit wegen Verbindungsverlust");
                            System.exit(-1);
                          }
                          //fixInfo("exec: vor Retry wegen Fehler");
                          if (iError!=1205 /* Deadlock */ && iError!=1062 /* Dublicate */ && Retry(bNoConnection))// sonst Endlosschleife bei iError==0))
                            return exec(s);
                          else  if (iError==1205)
                          {
                            fixInfo("Deadlock-Fehler -> Wiederholung nach 30 s");
                            Static.sleep(30000);
                            return exec(s);
                          }
                          //fixInfo("exec: nach Retry wegen Fehler");

                          lClock=Static.get_ms()-lClock;
                          lDauer_Ges +=lClock;
                          bFehler=true;
                          iOpenFehler++;
                          //if (!bASA || Prog())
                          if (!bISQL && !bNoConnection)
                          {
                            if (lQryAb>-1)
                              saveSqlTime("Error",s.length(),lClock,s+"\n"+sError,-2,-3);
                            printError(s);
                            Static.sleep(100);
                            //printError("Transact.exec:" + e);
                          }
                          return -2;
			}
		}
		else
			return -3;
	}

	public long getSqlms()
	{
		return lDauer_Ges;
	}

        private String getString(String s)
        {
          return getString(s,null);
        }

        private String getString(String s,String sBind)
        {
           long lClock = Static.get_ms();
           java.sql.ResultSet resultSet = open2(s,false,sBind);
           String s2="";
            try {
              if (resultSet.next())
                s2 = resultSet.getString(1);
              resultSet.close();
              if (Oracle())
                 resultSet.getStatement().close();
            }
            catch (java.sql.SQLException e) {
              printError("Transact.getString:"+e);
            }
            saveClock("String", s,lClock,s2,sBind);
            return s2;
        }

        public boolean exists(String s,String sBind)
        {
             long lClock = Static.get_ms();
             boolean b=false;
             java.sql.ResultSet resultSet = open2(s,false,sBind);
              try {
                b=resultSet.next();
                resultSet.close();
                if (Oracle())
                 resultSet.getStatement().close();
              }
              catch (java.sql.SQLException e) {
                printError("SQL.exists:"+e);
              }
              saveClock("exists", s,lClock,b ? 1:0);
              return b;
        }

    // ----------------- Datenbankabhängig ------------------------------

        public String DateTimeToString(java.util.Date date)
        {
        	//java.util.Date date=date0==null ? null:date0 instanceof Zeit ? ((Zeit)date0).getDate():(java.util.Date)date0;
                return date == null ? "null":
                    ASA() || MySQL() ? "'"+new SimpleDateFormat(MySQL() ? "yyyy-MM-dd HH:mm:ss":"yyyy/MM/dd HH:mm:ss").format(date)+"'":
                    MS()  ? "CONVERT(datetime,'"+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date)+"',111)":
                    "to_timestamp('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+"','YYYY-MM-DD HH24:MI:SS')";
        }

        public String int1_int4()
        {
          return MySQL() ? "`int1`,`int2`,`int3`,`int4`":"int1,int2,int3,int4";
        }

        public String int1()
        {
          return MySQL() ? "`int1`":"int1";
        }
        public String int2()
        {
          return MySQL() ? "`int2`":"int2";
        }
        public String int3()
        {
          return MySQL() ? "`int3`":"int3";
        }
        public String int4()
        {
          return MySQL() ? "`int4`":"int4";
        }
        public String SIZE()
        {
          return ASA() || MySQL() ? "SIZE" : '"' + "SIZE" + '"';
        }
        public String rw(String s)
        {
          return MySQL() ? "`"+s+"`":s;
        }

        public String bit(String s,long lBits)
        {
          return (MS() || ASA10() ? " "+s+"&0x"+Long.toHexString(lBits):" mod("+s+","+(lBits*2)+")-"+(lBits-1))+">0";
        }
        public String not(String s,long lBits)
        {
          return " "+s+"&0x"+Long.toHexString(lBits)+"=0";
        }
        public String bei(String s,int iBits,String sSpalte)
        {
          return ",(case when " + bit(s, iBits) + " then '" + Static.JaNein2(true) + "' else '" + Static.JaNein2(false) + "' end) " + sSpalte;
        }
        public String bei(String s,int iBits,String sSpalte,String sJa,String sNein) // für Run2: Fehlerzeilen bei DefImport
        {
          return ",(case when " + bit(s, iBits) + " then " + sJa + " else " + sNein + " end) " + sSpalte;
        }

        public String bits(String s,long lBits)
        {
          return ASA() || MS() || MySQL() ? " "+s+"&"+lBits:" bitand("+s+","+lBits+")";
        }
        
        public String bitis(String s,long lBits,long lis)
        {
          String sBits="0x"+Long.toHexString(lBits);
          String sis="0x"+Long.toHexString(lis);
          return ASA() || MS() || MySQL() ? " "+s+"&"+sBits+"="+sis:" bitand("+s+","+lBits+")="+lis;
        }

        public String concat(String s1,String s2)
        {
          return MS() ? s1+" + "+s2: MySQL() ? "concat("+s1+","+s2+")":s1+" || "+s2;
        }

        public String von()
        {
          return ASA() ? "von":"(select von from zr)";
        }
        public String bis()
        {
          return ASA() ? "bis":"(select bis from zr)";
        }

        public String isnull()
        {
          return ASA() ? "isnull(":Oracle() ? "nvl(" :MS() || MySQL() ? "coalesce(":"value(";
        }

        public String order(String s)
        {
          return ASA() || MS() || MySQL() ? s:Oracle() ? "nvl("+s+",0)":"value("+s+",0)";
        }

        public String orderS(String s)
        {
          return ASA() || MS() || MySQL() ? s:Oracle() ? "nvl("+s+",' ')":"value("+s+",0)";
        }

        public String orderD(String s)
        {
          return ASA() || MS() || MySQL() ? s:Oracle() ? "nvl("+s+","+DateTimeToString(Static.dt1970)+")":"value("+s+",0)";
        }
        
        public String orderD2(String s)
        {
          return ASA() || MS() || MySQL() ? "case when "+s+" is null then "+now()+" else "+s+" end ":Oracle() ? "nvl("+s+","+DateTimeToString(new java.util.Date())+")":"value("+s+",0)";
        }

        public String ifnull(String s,String s2,String s3)
        {
          return ASA() ? "ifnull("+s+','+s2+','+s3+')':Oracle() ? "nvl2("+s+','+s3+','+s2+')':
              "case when "+s+" is null then "+s2+" else "+s3+" end ";
        }

        public String ifnullA(String s)
        {
          return ASA() ? "ifnull("+s+",null,":Oracle() ? "nvl2("+s+',':"case when "+s+" is null then null else ";
        }

        public String ifnullE()
        {
          return ASA() ? ") ":Oracle() ? ",null) ":" end ";
        }

        public String now()
        {
          return Oracle() ? "sysdate":MS() ? "getdate()":"now()";
        }

        public String today()
        {
          return Oracle() ? "trunc(sysdate)" : MS() ? "CAST(CONVERT(char(8), GETDATE(), 112) AS datetime)" : MySQL() ? "current_date" : "today()";
        }

        public String fDate(String sDate,String sFormat)
        {
          if (MS() && !Static.bFuDate)
          {
            Static.bFuDate=exists("select name,crdate from sysobjects where xtype='FN' and name='fnFormatDate'",null);
            if (!Static.bFuDate)
            {
              fixInfo("erstelle fnFormatDate:" + exec("CREATE FUNCTION dbo.fnFormatDate (@Datetime DATETIME, @FormatMask VARCHAR(32)) RETURNS VARCHAR(32) AS BEGIN "+
                  "DECLARE @StringDate VARCHAR(32) SET @StringDate = @FormatMask "+
                  "IF(CHARINDEX('YYYY',@StringDate) > 0) SET @StringDate = REPLACE(@StringDate, 'YYYY',DATENAME(YY, @Datetime)) "+
                  "IF(CHARINDEX('ZZZZ',@StringDate) > 0)"+
                  " IF(DATEPART(ISO_WEEK, @Datetime) = 1) SET @StringDate = REPLACE(@StringDate, 'ZZZZ',DATENAME(YY, DATEADD(DAY, 6, @Datetime)))"+
                  " ELSE SET @StringDate = REPLACE(@StringDate, 'ZZZZ',DATENAME(YY, DATEADD(DAY, 1 - DATEPART(WEEKDAY, @Datetime), @Datetime))) "+
                  "IF(CHARINDEX('YY',@StringDate) > 0) SET @StringDate = REPLACE(@StringDate, 'YY',RIGHT(DATENAME(YY, @Datetime), 2)) "+
                  "IF(CHARINDEX('MM',@StringDate) > 0) SET @StringDate = REPLACE(@StringDate, 'MM',RIGHT('0' + CONVERT(VARCHAR, DATEPART(MM, @Datetime)), 2)) "+
                  "IF(CHARINDEX('DD',@StringDate) > 0) SET @StringDate = REPLACE(@StringDate, 'DD',RIGHT('0' + DATENAME(DD, @Datetime), 2)) "+
                  "IF(CHARINDEX('WW',@StringDate) > 0) SET @StringDate = REPLACE(@StringDate, 'WW',RIGHT('0' + DATENAME(ISO_WEEK, @Datetime), 2)) "+
                  "IF(CHARINDEX('HH',@StringDate) > 0) SET @StringDate = REPLACE(@StringDate, 'HH',RIGHT('0' + DATENAME(HH, @Datetime), 2)) "+
                  "IF(CHARINDEX('MI',@StringDate) > 0) SET @StringDate = REPLACE(@StringDate, 'MI',RIGHT('0' + DATENAME(MI, @Datetime), 2)) "+
                  "IF(CHARINDEX('SS',@StringDate) > 0) SET @StringDate = REPLACE(@StringDate, 'SS',RIGHT('0' + DATENAME(SS, @Datetime), 2)) "+
                  "RETURN @StringDate END"));
              Static.bSpWho=true;
            }
          }
          sFormat=sFormat.replaceAll("mm","nn").toUpperCase();
          if (MySQL())
          {
            if (sFormat.indexOf("WW")>=0)
              sFormat=sFormat.replaceAll("YYYY","%x").replaceAll("WW","%v");
            else
              sFormat=sFormat.replaceAll("YYYY", "%Y").replaceAll("MM", "%m").replaceAll("DD", "%d")
                  .replaceAll("HH", "%H").replaceAll("NN", "%i").replaceAll("SS", "%S");
          }
          else if (MS())
            if (sFormat.indexOf("WW")>=0)
              sFormat=sFormat.replaceAll("YYYY","ZZZZ");
            else
              sFormat=sFormat.replaceAll("NN","MI");
          else if (Oracle())
            sFormat=sFormat.replaceAll("HH","HH24").replaceAll("NN","MI");
          else if (ASA() && sFormat.indexOf("WW")>=0)
            Static.printError("Format WW für Sybase wird nicht unterstützt");
          return (MySQL() ? "DATE_FORMAT":Oracle() ? "to_char":MS()?"dbo.fnFormatDate":"dateformat")+"("+sDate+",'"+sFormat+"')";
        }

        public String top(String s,int i)
        {
          return i==-1 ? "select "+s:Oracle() ? "select * from (select "+s+") where rownum<="+i:MySQL() ? "select "+s+" limit "+i:"select top "+i+" "+s;
        }

        public String topOrder(String s,int i,String sOrder)
        {
          return i==-1 ? "select "+s+" order by "+sOrder:Oracle() ? "select * from (select "+s+") where rownum<="+i+" order by "+sOrder:MySQL() ? "select "+s+" order by "+sOrder+" limit "+i:"select top "+i+" "+s+" order by "+sOrder;
        }

        public String topA(int i,boolean bDistinct)
        {
          String sSelect=(Oracle() && Static.cOOM=='-' ? "select /*+ RULE */ ":"select ")+(bDistinct ? "distinct ":"");
          return i==-1 || MySQL() ? sSelect:Oracle() ? "select * from ("+sSelect:sSelect+"top "+i+" ";
        }

        public String topE(int i)
        {
          return i==-1 ? "": Oracle() ? ") where rownum<="+i:MySQL() ? " limit "+i:"";
        }

        public String first(String s)
        {
          return Oracle() ? "select "+s+" and rown"+(Oracle()?"um":"o")+"<=1" :MySQL() ? "select "+s+" limit 1": "select top 1 "+s;
        }

        public String join(String s,String s2)
        {
          return " join "+s+(ASA() ? "":" on "+s+".aic_"+s+"="+s2+".aic_"+s);
        }

        public String join2(String s,String s2)
        {
          return " join "+s+(ASA() ? "":" on "+s+".aic_"+s2+"="+s2+".aic_"+s2);
        }

        public String join(String s,String s2,String s3)
        {
          return " join "+s+(ASA() ? "":" on "+s+".aic_"+s3+"="+s2+".aic_"+s3);
        }

        public String join(String s,String sAs,String s2,String s3)
        {
          return " join "+s+" "+sAs+(ASA() ? "":" on "+sAs+".aic_"+s3+"="+s2+".aic_"+s3);
        }

        public static String join2(String s,String sAs,String s2,String s3)
        {
          return " join "+s+" "+sAs+" on "+sAs+".aic_"+s3+"="+s2+".aic_"+s3;
        }

        public String SQL_Format(Object Obj)
        {
		  return Obj == null ? "null" : Obj instanceof Date ? DateTimeToString((Date)Obj):
			Obj instanceof String ? Static.StringForSQL((String)Obj):
                        Obj instanceof Memo1 ? Static.StringForSQL(((Memo1)Obj).getValue()):
                        Obj instanceof Boolean?Obj.equals(Boolean.TRUE)?"1":"0":Obj.toString();
        }
        
        public String SQL_in2(Vector Vec)
        {
          if (Vec==null || Vec.size()==0)
            return "=-1";
          else if (Vec.size()==1)
            return "="+Vec.elementAt(0);
          else
            return SQL_in(Vec);
        }

	   	public String SQL_in(Vector Vec)
	   	{
	   		if (Vec==null || Vec.size()==0)
	   		{
	   			//System.err.println("Error: SQL_in ohne Vec aufgerufen");
	   			return " is null ";
	   		}
	   		String sIn=" in("+SQL_Format(Vec.elementAt(0));
	
	   		for(int i=1;i<Vec.size();i++)
	   			sIn=sIn+","+SQL_Format(Vec.elementAt(i));
	
	   		sIn=sIn+")";
	
	   		return (sIn);
	   	}

        public String in(String sSpalte,Vector Vec)
        {
          if (Vec==null || Vec.size()==0)
            return " "+sSpalte+"=-1 ";
          else if (!Oracle() || Vec.size()<=1000)
          {
            String sIn = " "+sSpalte + " in(" + SQL_Format(Vec.elementAt(0));
            for (int i = 1; i < Vec.size(); i++)
              sIn += "," + SQL_Format(Vec.elementAt(i));
            return sIn + ") ";
          }
          else
          {
            String sIn = " (";
            for (int j=0;j<=(Vec.size()-1)/1000;j++)
            {
              sIn += (j>0?") or ":"")+sSpalte + " in(" + SQL_Format(Vec.elementAt(j*1000));
              for (int i = j*1000+1; i < Vec.size() && i<(j+1)*1000; i++)
                sIn += "," + SQL_Format(Vec.elementAt(i));
            }
            return sIn + ")) ";
          }
        }

        public int getPU()
        {
          if (iPU>0)
            return iPU;
          String s=getString("select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
          if (s!=null && s.length()>0)
        	  iPU=Integer.parseInt(s);
          return iPU;
        }

}

