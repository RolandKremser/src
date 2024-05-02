package All_Unlimited.Hauptmaske;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import All_Unlimited.Allgemein.AUTable;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.Ampel;
import All_Unlimited.Grunddefinition.JavaKonsole;
import All_Unlimited.Grunddefinition.Systemeinstellung;

public class Run {
	
	public static boolean bJavaFX=false;
	public static boolean bUseOption=false;
	private static boolean bCC=false;
	public static boolean bOS_User=false;
	public static boolean bLastUser=false;
	//public static boolean bInternerTimer=true;
	public static boolean bKAF=false;
	public static boolean bSiemens=false;
	public static String sTitel="ALL_UNLIMITED";
	public static String sBenutzer=null;
	public static String sMandant=null;
	public static String sToken=null;
	public static String sPW=null;
	public static int iPArt=0;
	public static String sConnection=null;
	public static String sConnection2=null;
	public static String sQryUser=null;
	public static String sQryPW=null;
	public static int iQryPArt=0;
	public static List<String> list=new ArrayList<String>();
	public static boolean bAlt=false;
	//public static String[] args;
	public static int iAnzBG=0; 
	public static int iMandantNeu=0;
  public static boolean bSilent=false;
  private static boolean bH_dez=false;
  private static boolean bH_min=false;

  public static String sAServer=null;
  public static String sWeb=null;
  public static String sWebServer=null;
	
	public static void add(String sName,String sValue)
	{
		if (sValue!=null)
			list.add(sName+"="+sValue);
	}
	
	private static String getParameter(String s)
    {
      for(int i=0;i<list.size();i++)
        if(list.get(i).startsWith(s+"="))
          return list.get(i).substring(s.length()+1);
      return null;
    }
	
	public static void setPara()
	{
		System.setProperty("file.encoding","UTF-8");
//		Field charset = Charset.class.getDeclaredField("defaultCharset");
//		charset.setAccessible(true);
//		charset.set(null,null);
		
		//Transact.bAltUser=true;
		sConnection = getParameter("Connection");
        sConnection2 = getParameter("Connection2");
        String sQryZeilen = getParameter("QryZeilen");
        sQryUser = getParameter("QryUser");
        sQryPW = getParameter("QryPW");
        iQryPArt = getParameter("QryPArt")==null ? 0 :Integer.parseInt(getParameter("QryPArt"));
        String sQryAb = getParameter("QryAb");
        String sAbfAb = getParameter("AbfAb");
        String sTO = getParameter("TO"); // Timeout für alle SQL-Befehle [s]
        String sTOC = getParameter("TOC");  // Timeout für My-SQL-Verbindung [s]
        String sTOS = getParameter("TOS");  // Timeout für My-SQL-Befehle [min]
        sTitel = getParameter("Titel");
        String sImage = getParameter("Image");
        String sHelp = getParameter("Help");
        Static.DirError = getParameter("Error");
        if (getParameter("MB")!=null)
        	Transact.iMaxB=Sort.geti(getParameter("MB"));
        
        sWeb = getParameter("Web");
        String sSep = sWeb == null ? null:sWeb.startsWith("http") ? "/":sWeb.substring(sWeb.length()-1);
        Static.HilfeVerzeichnis= sHelp==null ? sWeb==null ? null:sWeb+"Hilfe"+sSep:sHelp;
        Static.DirImageSys = getParameter("ImageSys");
        if (Static.DirImageSys==null && sWeb!= null) Static.DirImageSys=sWeb+"Images"+sSep;
        Static.DirImageOD=Static.DirImageSys;
        if (Static.DirImageND==null && sWeb!= null) Static.DirImageND=sWeb+"Images2020"+sSep;
        Static.DirImageDef = getParameter("ImageDef");
        if (Static.DirImageDef==null && sWeb!= null) Static.DirImageDef=sWeb+"Images"+sSep+"KopfFuss"+sSep;
        Static.DirImageStamm = getParameter("ImageStamm");
        if (Static.DirImageStamm==null && sWeb!= null) Static.DirImageStamm=sWeb+"Images"+sSep+"MA_Bilder"+sSep;
        
        Static.DirDoku = getParameter("Doku");
        String sPopup = getParameter("Popup");
        if (sPopup != null)
          Global.iPopkey=Integer.parseInt(sPopup);
        if (Static.DirDoku != null && Static.DirDoku.length()<1)
          Static.DirDoku=null;
        sAServer = getParameter("AServer");
        AClient.sAServerSoll=sAServer;
        if (AClient.sAServerSoll != null && AClient.sAServerSoll.indexOf(':')<1)
          AClient.sAServerSoll=null;
        sWebServer=getParameter("WebServer");
        Static.sWeb = sWebServer;
//        Static.printError("Web="+sWeb+", AServer="+AClient.sAServerSoll);
        if (Static.DirImageSys == null)
          Static.DirImageSys=sImage;
        if (Static.DirImageDef == null)
          Static.DirImageDef=sImage;
        if (Static.DirImageStamm == null)
          Static.DirImageStamm=sImage;
        if (Static.DirError != null)
        {
          if(Static.DirError.startsWith("+"))
          {
            Static.bSave = true;
            Static.DirError = Static.DirError.substring(1);
          }
          if(Static.DirError.equals("*"))
            Static.DirError=null;
          else
          {
            File Fil=new File(Static.ErrorFile());
            if(Fil != null)
              Fil = Fil.getParentFile();
            if(Fil == null || !Fil.exists())
            {
              String s=Static.DirError;
              Static.bSave=false;
              Static.DirError=null;
              Static.printError("Fehlerverzeichnis "+s+" nicht gefunden/beschreibbar");
              JOptionPane.showMessageDialog(new JFrame(), "Das Fehlerverzeichnis ist falsch!", "Fehler", JOptionPane.WARNING_MESSAGE);
            }
          }
        }
        Transact.fixInfoS("Connection:"+sConnection);
        Transact.fixInfoS("JRE:"+System.getProperty("java.version"));
        Transact.fixInfoS("Speicher:"+(Runtime.getRuntime().maxMemory()/1024/1024)+" MB");
        Transact.fixInfoS("Land:"+Locale.getDefault().getCountry());
        Transact.fixInfoS("Sprache:"+Locale.getDefault().getLanguage());
        if (sQryZeilen != null && sQryZeilen.length()>0)
        {
          Transact.iQryZeilen = new Integer(sQryZeilen).intValue();
          Transact.fixInfoS("QryZeilen="+Transact.iQryZeilen);
        }
        if (sTO != null && sTO.length()>0)
        {
          Transact.iTimeOut = new Integer(sTO).intValue();
          Transact.fixInfoS("Qry-Timeout="+Transact.iTimeOut);
        }
        if (sTOC != null && sTOC.length()>0)
        {
          Transact.iTimeOutC = new Integer(sTOC).intValue();
          Transact.fixInfoS("Mysql-connectTimeout="+Transact.iTimeOutC);
        }
        if (sTOS != null && sTOS.length()>0)
        {
          Transact.iTimeOutS = new Integer(sTOS).intValue();
          Transact.fixInfoS("Mysql-socketTimeout="+Transact.iTimeOutS);
        }
        if (sQryAb != null && sQryAb.length()>0)
        {
          Transact.lQryAb = new Integer(sQryAb).intValue();
          if (Transact.lQryAb != -1)
            Transact.fixInfoS("QryAb="+Transact.lQryAb);
        }
        if (sAbfAb != null && sAbfAb.length()>0)
        {
          Static.lAbfAb = new Long(sAbfAb).longValue();
          if (Static.lAbfAb != 0)
            Transact.fixInfoS("AbfAb="+Static.lAbfAb);
        }
        if (Global.iPopkey!=525)
          Transact.fixInfoS("Popup-Key="+Global.iPopkey);
        
        Static.bBilder =Static.DirImageSys != null;
		//Static.HilfeVerzeichnis=sHelp.equals("") ? sImage+"hmtl\\":sHelp;
		if (getParameter("Mandant") != null)
		{
			sMandant=getParameter("Mandant");
			Transact.fixInfoS("Mandant="+sMandant);
		}
//            Static.printError("Mandant-Parameter wird nicht mehr unterstützt, setzen Sie Option 25 auf +");
//		Calendar cal = Calendar.getInstance();
//		int iFD1=cal.getFirstDayOfWeek();
//		  cal.setFirstDayOfWeek(Calendar.MONDAY);
//		  int iFD2=cal.getFirstDayOfWeek();
//		if (iFD1 != iFD2)
//			Static.printError("ändere FirstDayOfWeek von "+iFD1+" auf "+iFD2);
	}
	
	public static void setOpt()
	{
		String sOption=getParameter("Option");
		try
		{
			if (sOption!=null)
			{
                          /*if (sOption==null)
                          {
                            sOption=sID1.substring(4);
                            sID1=sID1.substring(0,4);
                          }*/
                          //Transact.fixInfoS("ID="+sID1);
                          Transact.fixInfoS("Option="+sOption);
                          // 1..Optionen für div anwählbar: -..keine Option, N..Notfall, S..Siemens, x..lt. Html
                          // 2..Autoausloggen in Stunden (war Memo laden)
                          // 3..+= Image mit Pfad , -= in Mandantenverzeichnis
                          // 4..+= Java-Look&Feel , -=System, W= Windows-Look&Feel, G=GTK, M=Motif, C=Classic, N=Nimbus
                          // 5..+= Passwortabfrage, -= Benutzer wird übergeben, w=Benutzer laut System
                          // 6..T= Cachen (nur extern), I .. immer cachen
                          // 7..-= Computer doppelt verwenden
                          // 8..-= Comboboxen leer lassen (nur extern), I .. immer leer lassen
                          // 9..-= keine Währungsumrechnung
                          //10..+= Clockinfo
                          //11..+= * anzeigen
                          //12..-= ohne Funktion (war Leiste nicht anzeigen)
                          //13..-= letzten User nicht anzeigen
                          //14..-= internen Timer ausschalten
                          //15..A= AServer: - .. nie, A .. auto, + .. immer
                          //16..+= Stellvertreter
                          //17..m= 1:45, d=1,75 h
                          //18..-= kein Mac-Adressen-Prüfung
                          //19..o= ohne automatisch alle zuletzt offenen Masken -> nur Startformular
                          //20..r=retry bei Verbindungsverlust, f..fast retry, e..exit
                          //21..+=checkConnects vor jeder Verbindung
                          //22..+=Rahmen um Eingabefelder (ab 5.8.1) bzw Neues Design
                          //23..k=Verlauf nicht protokollieren      (ab V 5.10.12)
                          //24..+=Java-Konsole mitspeichern
                          //25..+=Mandantensuche                    (ab V 5.5.6); A.. AutoAll, a..Azure (ab V 5.16.21)
                          //26..+=Datenbank anzeigen                (ab V 5.5.6)
                          //27..+=T .. Test
                          //28..k=Fehler nicht in Datenbank mitprotokollieren (ab V 5.9.9)
                          //29..+=Zeitarten übersetzt
                          //30..N=ANSI; S=ASCI; U=UTF16
                          //31..-=Aufruf nicht über Betriebsystem-Befehl (sondern von Applet)
                          //32..Oracle-Optimizer-Mode R=RULE, C=CHOOSE, F=FIRST_ROWS, A=ALL_ROWS
                          //33..k=Lizenzprüfung deaktiviert
                          //34..+=Stempelimport auf Einzelmandant
                          if (sOption.length()>0)
                          {
                            char c=sOption.charAt(0);
                            bUseOption = c!='-' && c!='s';
                            bSiemens = c=='s' || c=='S';
                            Static.bNotfall = c=='N';
                            Static.bLtHtml = c=='x';
//                            Global.fixInfoS("Siemens="+bSiemens+", Notfall="+Static.bNotfall+"/"+c);
                          }
				if (sOption.length()>1 && sOption.charAt(1)!='-')
					Static.setAutoAus(sOption.charAt(1));//Static.bMemo=false;
                                if (sOption.length()>2 && sOption.charAt(2)=='-')
                                  Static.bImgM=true;
                                if (sOption.length()>3 && sOption.charAt(3)!='+')
                                {
                                  Static.bDefaultLook = false;
                                  Static.cLook=sOption.charAt(3);
                                }
                                if (sOption.length()>4)
                                 if(sOption.charAt(4)=='-')
                                 {
                                   sBenutzer = getParameter("User");
                                   sPW = getParameter("PW");                            
                                 }
                                 else if(sOption.charAt(4)=='w')
                                 {
                                   sBenutzer = System.getProperty("user.name");
                                   Global.fixInfoS("Systembenutzer="+sBenutzer);
                                 }
                                if (sOption.length()>5)
                                  Static.createTemp(getParameter("Temp"),sOption.charAt(5)=='T' ? Static.AUTO:sOption.charAt(5)=='I' ? Static.IMMER:Static.NIE);
                                // if (sOption.length()>6 && sOption.charAt(6)=='-')
                                //   Static.bComputerLock=false;
                                if (sOption.length()>7)
                                  Static.iComboLeer= sOption.charAt(7)=='-' ? Static.AUTO:sOption.charAt(7)=='I' ? Static.IMMER:Static.NIE;
                                if (sOption.length()>8 && sOption.charAt(8)=='W') //Wechselwährung nur bei W einschalten
                                  bCC=true;
                                if (sOption.length()>8 && sOption.charAt(8)=='s')
                                	bSilent=true;
                                if (sOption.length()>9 && sOption.charAt(9)=='+')
                                  Transact.iInfos=Transact.CLOCK;
                                if (sOption.length()>10 && sOption.charAt(10)=='+')
                                  Static.bStern=true;
//                                if (sOption.length()>11 && sOption.charAt(11)=='-')
//                                  Static.bLeiste=false;
                                if (sOption.length()>12 && sOption.charAt(12)=='+')
                                  bLastUser=true;
                                if (sOption.length()>12 && sOption.charAt(12)=='w')
                                    bOS_User=true;
                                if (sOption.length()>13 && sOption.charAt(13)=='-')
                                  Static.bInternerTimer=false;
                                if (sOption.length()>14)
                                  Static.iAServer= sOption.charAt(14)=='A' ? Static.AUTO:sOption.charAt(14)=='+' ? Static.IMMER:Static.NIE;
                                if (sOption.length()>15 && sOption.charAt(15)=='+')
                                {
                                  Static.sAbfrageErsatz=getParameter("Ersatz");
                                  //Static.bErsatz = sAbfrageErsatz != null;
                                }
                                if (sOption.length()>16)
                                {
                                  bH_dez = sOption.charAt(16)=='d';
                                  bH_min = sOption.charAt(16)=='m';
                                }
                                if (sOption.length()>17)
                                 {
                                	Static.cMacArt= sOption.charAt(17);
                                   //Static.bMac = sOption.charAt(17)=='+' || sOption.charAt(17)=='c'; // über Mac-Adresse
                                   //Static.bHost= sOption.charAt(17)=='H'; // Hostnamne
                                   //Static.bHIC=  sOption.charAt(17)=='c'; // über IP-Adresse mit Hostname kombiniert
                                 }
                                if (sOption.length()>18 && sOption.charAt(18)=='o')
                                  bKAF=true;
                                if (sOption.length()>19)
                                {
                                  char c=sOption.charAt(19);
                                  Transact.bRetry=c=='r' || c=='f';
                                  Transact.bFast=c=='f';
                                  Transact.bRealExit=c=='e';
                                }
                                if (sOption.length()>20)
                                  Transact.bCheckConnect=sOption.charAt(20)=='+';
                                if (sOption.length()>21)
                                {
                                  Static.bRahmen=sOption.charAt(21)=='+';
                                  if (sOption.charAt(21)=='n')
                                	  Static.setNI(true);
                                  Static.setND(sOption.charAt(21)=='N' || sOption.charAt(21)=='n');
                                }
                                if (sOption.length()>22)
                                  Static.bVerlauf=sOption.charAt(22)!='k';
                                if (sOption.length()>23)
                                {
                                  Static.bSave = sOption.charAt(23)=='+' || sOption.charAt(23)=='S';
                                  if (sOption.charAt(23)=='S' || sOption.charAt(23)=='s')
                                	  JavaKonsole.get(true);
                                }
                                if (sOption.length()>24)
                                {
                                  Static.bAzure=sOption.charAt(24)=='a';
                                  Static.bMandantensuche=sOption.charAt(24)=='+' || sOption.charAt(24)=='A';
                                  Global.bAutoAll=sOption.charAt(24)=='A';								  
                                }
                                if (sOption.length()>25)
                                  Static.bShowDB=sOption.charAt(25)=='+';
                                //if (sOption.length()>26 && ((sOption.charAt(26)=='-') || (sOption.charAt(26)=='+')))
                                //	Static.bStringX=sOption.charAt(26)=='+';
                                if (sOption.length()>26 && (sOption.charAt(26)=='T'))
                                  Static.bTest=true;
                                if (sOption.length()>27)
                                	Static.bProtFehler=sOption.charAt(27)!='k';
                                if (sOption.length()>28)
                          		Static.bTranslate=sOption.charAt(28)=='+';
                                if (sOption.length()>29)
                                {
                                  char c=sOption.charAt(29); // default UTF-8
                                  if (c=='N') 		  // ANSI
                                    Static.CP="Cp1252";
                                  else if (c=='S')    // ASCII
                                    Static.CP="Cp850";
                                  else if (c=='U')    // UTF-16
                                    Static.CP="UTF-16";
                                }
                                if (sOption.length()>30)
                                  Static.cURL=sOption.charAt(30);
                                if (sOption.length()>31)
                                  Static.cOOM=sOption.charAt(31);
                                if (sOption.length()>32)
                                  Static.bLizenz = sOption.charAt(32)!='k';
                                if (sOption.length()>33)
                                  AClient.bImportM=sOption.charAt(33)=='+';
			}
                        else
                        {
                          Static.printError("Optionen fehlen!");
                          JOptionPane.showMessageDialog(new JFrame(),new JLabel("Die Options-Parameter fehlen!\nBitte html-Seite anpassen!"),"Optionen fehlen",JOptionPane.WARNING_MESSAGE);
                        }
			String sPArt = getParameter("PArt");
	        if (sPArt != null)
	        {
	          iPArt = Integer.parseInt(sPArt);
	          Global.fixInfoS("PArt="+iPArt);
	        }
	        if (Static.sAbfrageErsatz != null)
                Transact.fixInfoS("Ersatz="+Static.sAbfrageErsatz);
              
		}
		catch(Exception e)
		{
                  Static.printError("Run.Optionsprüfung:"+e);
                  Static.sleep(5000);
			System.exit(-1);
		}
		
	}
	
	public static void setDbPara(Global g)
	{
		if (!Static.bLtHtml && !Static.bNotfall)
            Systemeinstellung.LoadDir(g,null,-1);
		else
		{
			g.fixInfo("AServer=" + AClient.sAServerSoll);
		    g.fixInfo("Web-Server=" + Static.sWeb);
		    g.fixInfo("DirError=" + Static.DirError);
		    g.fixInfo("DirImageSys=" + Static.DirImageSys);
		}
          Static.bBilder =Static.DirImageSys != null;
          AClient.setg(g);
          if (!Transact.bConnected)
            if (Transact.bRealExit)
              System.exit(0);
            else
              return;
          int iDO=0;   
          Parameter Para2 = new Parameter(g,"System");
          Para2.getParameter("Option", false, false);
          if (Para2.bGefunden)
          {
            iDO = Para2.int1;
            g.fixInfoT("Para:"+Para2.int1+"/"+Para2.int2+"/"+Para2.int3+"/"+Para2.int4);
            AUTable.iMaxZeilen = Para2.int2 / 1024;
            AUTable.iGaugeAb = Para2.int2 % 1024;
            if (Para2.int4>0)
              Static.iESleep=Para2.int4%100;
            if (Static.iESleep==0)
              Static.iESleep=5;
            // if (Para2.int4>100)
            //   Static.iTT=Para2.int4/100;
            // if (Static.iTT>1000)
            // {
            // 	Static.iLTT=Static.iTT/1000;
            //   Static.iTT%=1000;
            //   if (Static.iLTT>100)
            //   {
            //     Static.iETimer=Static.iLTT/100*30;
            //     Static.iLTT%=100;
            //   }
            // }
            g.fixInfoT("E-Mail:"+Static.iESleep+"/"+Static.iETimer);//+", TT="+Static.iTT+"/"+Static.iLTT);
          }
          else
            iDO = SQL.getInteger(g, "select " + g.int1() + " from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Option'");
          //Static.bView2 = (iDO&1)>0;
          boolean bSetbH=true;
          if (!Static.bLtHtml)
          {
	          Para2.getParameter("Option2", false, false);
	          if (Para2.bGefunden && Para2.int1>0) // ab V1
	          {
	        	  int iAutoAus=Para2.int2%16;
	        	  int iBits=Para2.int2/16;
	        	  Static.iAutoAus=iAutoAus==0 ? 5*60:iAutoAus*3600;
	        	  Static.bStern=(iBits & Systemeinstellung.cstStern)>0;
	//        	  Static.bLeiste=(iBits & Systemeinstellung.cstLeiste)>0;
	        	  Static.bRahmen=(iBits & Systemeinstellung.cstRahmen)>0;
	        	  g.fixInfoT("Autoaus="+Static.iAutoAus+", Stern="+Static.bStern+", Rahmen="+Static.bRahmen);
	        	  if (Para2.int1>1) // ab V2
	        	  {
                Static.bDS=(iBits & Systemeinstellung.cstDG)>0;
                if (Static.bDS)
	        		    Static.setND((iBits & Systemeinstellung.cstND)>0);
	        		  Static.bMandantensuche=(iBits & Systemeinstellung.cstMS)>0;
	        	      Static.bInternerTimer=(iBits & Systemeinstellung.cstIT)>0;
	        	      g.bH_dez=(iBits & Systemeinstellung.cstDEZ)>0;
	        	      g.bH_min=(iBits & Systemeinstellung.cstMIN)>0;
	        	      if ((iBits & Systemeinstellung.cstSV)>0)
	  	    			Static.sAbfrageErsatz="ZE_Pruefung_Stellvertreter";
	        	      bSetbH=false;
	        	      g.fixInfoT("ND="+Static.bND+", Timer="+Static.bInternerTimer+" ZF="+(g.bH_dez ? "dez":g.bH_min ? "min":"auto")+(Static.sAbfrageErsatz==null ? "":" "+Static.sAbfrageErsatz));
	        	  }
	          }
          }
          if (bSetbH)
          {
        	  g.bH_dez=bH_dez;
        	  g.bH_min=bH_min;
          }
          Para2.free();
          Static.bInsert2 = (g.MS() || g.MySQL()) && (iDO&2)>0;
          //Static.bCheckZR = (iDO&4)>0;
          g.bCC=bCC; 
          Transact.fixInfoS("\nDatum/Uhrzeit= "+new DateWOD().Format("dd.MM.yyyy HH:mm")+"\nAll Unlimited-Version="+Version.getVersion()+(g.TestPC() ? " ("+Version.getDate()+")":""));
          Ampel.check(g);
          if (!Static.Leer(sMandant))
          {
        	  g.setMandantT(sMandant);
//        	  g.fixtestError("-> Mandant="+g.getMandantT());
          }
	}
	
	public static void setUserPara(Global g)
	{
		g.setAutoFC();
		if (g.Def())
			JavaKonsole.get(true,g);
		ShowAbfrage.refreshBerechtigung(g);	
		Parameter ParaEinst = new Parameter(g,"Pers. Einstellungen");
		ParaEinst.getParameter("Benutzer",true,false);
        if (ParaEinst.bGefunden)
        {
//        	g.fixtestError("Benutzer-Para="+ParaEinst.sParameterzeile+", int1="+ParaEinst.int1+", int2="+ParaEinst.int2+", int3="+ParaEinst.int3+", int4="+ParaEinst.int4+", Alt="+bAlt);
          if (ParaEinst.int1>1)
          {
	          int iPbits=ParaEinst.int2;
	//          if (!Static.Leer(ParaEinst.sParameterzeile))
	//          {
	//        	  Static.DirCss=ParaEinst.sParameterzeile;
	//        	  g.fixtestError("CSS-Dir="+Static.DirCss);
	//        	  g.setDefaultCss();
	//          }
	          Static.bSpeichernAnzeigen = g.Def() ? (iPbits&Global.SAVE_SHOW)>0:false;
	          Static.bTestFenster = (iPbits&Global.TEST_FENSTER)>0;
	          Static.bFormatSortiert = (iPbits&Global.FORMAT_SORTIERT)>0;
	          g.bH_dez=(iPbits&Global.ART_H)==Global.H_DEZ;
	          g.bH_min=(iPbits&Global.ART_H)==Global.H_MIN;
	          g.setWaehrung(ParaEinst.int3);
	//          Static.bJavaFX=(iPbits&Global.FX)>0;
	//          Static.bAlert=(iPbits&Global.ALERT)>0;
	//          Static.bShowStyle=(iPbits&Global.STYLE)>0;
	          Static.bBeep=(iPbits&Global.BEEP)>0;
	//          Static.bUseDefaultCss=(iPbits&Global.NO_CSS)==0;
	//          Static.bSpeichernFrage=(iPbits&Global.SAVE_ASK)>0;
	//          Static.bCenterStage=(iPbits&Global.CENTER_STAGE)>0;
	          if (ParaEinst.int1==2)
	          {
	            Static.bDefBezeichnung = g.Def();
	            Static.bShowSize = g.Def();
	          }
	          else
	          {
	            Static.bDefBezeichnung=(iPbits&Global.DEFBEZ)>0;
	            Static.bShowSize=(iPbits&Global.SHOWSIZE)>0;
	            Static.bStdSize=(iPbits&Global.STDSIZE)>0;
	//            Static.bOriginal=(iPbits&Global.ORIGINAL)>0;
	            Static.bNurStart=(iPbits&Global.NURSTART)>0;
	            Static.bDefShow=(iPbits&Global.DEF_SHOW)>0;
	          }
          }
          if (!bAlt && g.getMandant()==1 && ParaEinst.int4>1 && !Static.bNurStart)
            iMandantNeu=ParaEinst.int4;
//          g.fixtestError("Mandant="+iMandantNeu+", NurStart="+Static.bNurStart+", H_min="+Static.bH_min);
        }
        else
        {
          Static.bDefBezeichnung = false;
          Static.bTestFenster =false;
          Static.bShowSize=false;
//          if(ParaEinst.bGefunden)
//          {
//            //Static.bLeerLassen = ParaEinst.int3 != 0;
//            //g.setDebug(ParaEinst.int1 != 0);
//            //g.bZeigeInfo=ParaEinst.int2!=0;
//            Static.bSpeichernAnzeigen = ParaEinst.int4 != 0;
//          }
//          ParaEinst.getParameter("Optionen", true, false);
//          if(ParaEinst.bGefunden)
//            g.setWaehrung(ParaEinst.int1);
        }
        if (Static.bND)
        {
        	ParaEinst.getParameter("Computer",true,false);
        	if (ParaEinst.bGefunden)
        		Transact.iInfos=ParaEinst.int1;
        }
		g.setZA(0,ParaEinst.getParameter("Periode",true,false));
		if (ParaEinst.bGefunden)
		{
			//int iZeitart=ParaEinst.int1;
                            Static.bZR_Leiste=ParaEinst.int2==1;
                            int iVon=ParaEinst.int3;
                            int iBis=ParaEinst.int4;
//                            g.fixtestError("von/bis1="+iVon+"-"+iBis+"; "+ParaEinst.int1+"/"+ParaEinst.int2+"/"+ParaEinst.sParameterzeile);
                            if (!bAlt && iVon>1000 && iBis>1000 && g.HS() && !Static.bNurStart)
                              g.setVonBis(DateWOD.getTS(iVon),DateWOD.getTS(iBis));
		}
		String sZeitart=g.getZA(0);
        if (sZeitart.equals("offen") || sZeitart.equals(""))
          g.setZA(0,"Monat");
        //g.testInfo("von/bis1="+g.getVonBis("dd.MM.yyyy",false)+"/"+g.sZeitart);
        Zeitraum.PeriodeToVec(g,g.getZA(0),true);

		ParaEinst.free();
		Parameter Para2 = new Parameter(g,"System");
        Para2.getMParameter("ML", true);
        if (Para2.bGefunden)
        {
      	  Static.iBenML=Para2.int1;
      	  if (Para2.int2==99)
      	  {
      		Static.iPWM=Para2.int3;
      		Static.iPWMM=Para2.int4;
      	  }
      	  else
      	  {
      		Static.iPWM=Para2.int2*1000+Para2.int3*10+Para2.int4;
      		Static.iPWMM=0;
      	  }
//      	  Static.iPWML=Para2.int2;
//      	  Static.iPWMZ=Para2.int3;
//      	  Static.iPWMS=Para2.int4;
      	  g.fixtestInfo("BenML="+Static.iBenML+", PWM="+Static.iPWM+", PWMM="+Static.iPWMM);
        }
        Para2.free();
	}
	
	public static void setMandant(Global g)
	{
		if (iMandantNeu>0 && iMandantNeu!=g.getMandant())
        {
			if (!SQL.exists(g,"select aic_mandant from mandant where aic_mandant=? and Tod is null", ""+iMandantNeu))//g.TabMandanten.getPos("aic_mandant", iMandantNeu)<0)
			{
				g.fixtestError("Mandant "+iMandantNeu+" nicht gefunden, verwende deshalb Mandant "+g.getMandant());
				iMandantNeu=g.getMandant();
			}
			else
			{
	          g.setMandant(iMandantNeu);
	          Transact.fixInfoS("Mandant geändert auf "+g.MandantBez(iMandantNeu));
	          g.fillBerechtigung(0);
	          ShowAbfrage.refreshBerechtigungSmall(g);
			}
        }
        if (g.getMandant()>1)
          Systemeinstellung.LoadDir(g,null,g.getMandant());
        else if (Static.bLtHtml || Static.bNotfall)
        	LoadDefaultPW(g);
	}
	
	private static void LoadDefaultPW(Global g)
	{
		Parameter Para = new Parameter(g,"System");
		Tabellenspeicher Tab=Para.getMParaTab("Dir",-1);
		if (Tab.posInhalt("int1", 11)) // PW
		{
			Static.sDefaultPW=Tab.getS("Parameterzeile");
//			g.fixtestError("LoadDefaultPW:"+Static.sDefaultPW);
		}
	}
	
	/* Aufruf der letzen Fenster */
	
	public static void checkReady(Global g)
	  {
	    iAnzBG--;
	    if (iAnzBG<=0)
	    {
	      Global.bLogout=true;
	      Static.bVerlauf=true;//bVerlauf;
	      if (Global.BtnLogout != null)
	        Global.BtnLogout.setEnabled(true);
	    }
	  }
	
	private static void OpenHS(final Global g,final String s,final int iAic)
	  {
	    new Thread(new Runnable()
	    {
	      public void run()
	      {
	        try
	        {
	          Hauptschirm.get(s, g);
//	          g.putTabFormulare(iAic, 0, Obj);
	        }
	        catch(Exception e)
	        {
	        	if (g.Def() || g.TestPC())
		        {
	        		g.printError("Fehler bei öffnen von Hauptschirm " + s + ":" + e,0);
	        		g.printStackTrace(e);
		        }
	        }
	        checkReady(g);
	      }
	    }).start();
	  }

	  private static void OpenEF(final Global g,final int iAic,final int iSatz)
	  {
	    new Thread(new Runnable()
	    {
	      public void run()
	      {
	        try
	        {
	          int iStt=g.getStt(iAic);
	          int iRolle=g.getRolle(iAic);
	          int iStamm=0;
	          if (iStt>0)
	              iStamm=g.getSyncStamm(iStt,iRolle);
	          if (iStamm==0)
	          {
	            iStamm=iSatz;
	            if (iStt>0)
	              g.setSyncStamm(iStt,iStamm,iRolle);
	          }
	          g.progInfo("EF mit Stt="+iStt+", iStamm="+iStamm+" statt "+iSatz);
	          EingabeFormular.HoleFormular(/*new Global(g) weg lt. Kurt am 13.9.*/g, iAic, Static.AicToVec(iStamm), iStamm, false);
	        }
	        catch(Exception e)
	        {
	          if (g.Def() || g.TestPC())
	          {
		          g.printError("Fehler bei öffnen von EingabeFormular "+SQL.getString(g,"select defbezeichnung from begriff where aic_begriff="+iAic)+" (" + iAic + "):" + e,iAic);
		          g.printStackTrace(e);
	          }
	        }
	        checkReady(g);
	      }
	    }).start();
	  }
	
	public static void RestFenster(Global g)
	  {
	    iAnzBG=g.TabFensterpos.size();
	    if (iAnzBG==0)
	      checkReady(g);
	    else
	     for (int iPos=0;iPos<g.TabFensterpos.size();iPos++)
	     {
	      if ((g.TabFensterpos.getI(iPos,"bits")&1)>0 && !g.TabFensterpos.getS(iPos,"Status").equals("del"))
	      {
	    	int iBeg=g.TabFensterpos.getI(iPos,"vfenster");
	        int iPosB=g.TabBegriffe.getPos("Aic",iBeg);
	        int iPosBG=iPosB<0 ? -1:g.TabBegriffgruppen.getPos("Aic",g.TabBegriffe.getI(iPosB,"Gruppe"));
	        //g.progInfo("Rest:"+iPosB+"/"+iPosBG);
	        String sBG= iPosBG<0 ? "":g.TabBegriffgruppen.getS(iPosBG,"Kennung");
	        //g.fixtestError("versuche "+sBG+" "+iBeg+" ("+iPosB+","+iPosBG+") zu öffnen");
	    	if (iPosBG<0)
	        {
	        	g.fixtestError("Begriff "+iBeg+" nicht gefunden");
	        	g.TabFensterpos.setInhalt(iPos,"Status","del");
	        	checkReady(g);
	        }
	        else if (sBG.equals("Applikation"))
	        {
	          OpenHS(g,g.TabBegriffe.getS(iPosB,"Kennung"),g.TabBegriffe.getI(iPosB,"Aic"));
	          //Static.sleep(100);
	          //Object Obj=new Hauptschirm(g.TabBegriffe.getS(iPosB,"Kennung"), g);
	          //g.putTabFormulare(g.TabBegriffe.getI(iPosB,"Aic"),0,Obj);
	        }
	        else if (sBG.equals("Frame"))
	        {
	          int iAic=g.TabBegriffe.getI(iPosB,"Aic");
	          Object Obj=null;
	          if (g.TabBegriffe.getI(iPosB,"Typ")==g.SystemFormular())
	          {
	            AU_Formular.OpenSystem(g,null, g.TabBegriffe.getS(iPosB, "Kennung"));
	            checkReady(g);
	          }
	          else if (g.TabBegriffe.getI(iPosB,"Stt")>0)
	          {
	        	  int iSatz=g.TabFensterpos.getI(iPos,"satz");	          
	        	  if (iSatz>0)
	        		  OpenEF(g,iAic,iSatz);
	        	  else
	        		  checkReady(g);
	          }
	          else
	          {
	            Obj = new AU_Formular(Formular.getForm(g, iAic), g);
	            ((AU_Formular)Obj).thisFrame.setVisible(true);
	            checkReady(g);
	          }
	          if (Obj != null)
	            g.putTabFormulare(iAic,0,Obj);
	          //Fom.thisFrame.setVisible(true);
	        }
	        else
	        {
	        	g.fixtestError("Begriffgruppe "+sBG+" wird nicht automatisch geöffnet");
	        	g.TabFensterpos.setInhalt(iPos,"Status","del");
	        	checkReady(g);
	        }
	        	
	      }
	      else
	        checkReady(g);
	     }
	  }

}
