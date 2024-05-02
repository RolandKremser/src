/*
    All_Unlimited-Allgemein-SQL.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.sql.SQLException;
//import java.util.Date;
import java.util.Vector;
import java.awt.Image;
import java.io.*;

import javax.swing.ImageIcon;

import All_Unlimited.Allgemein.Anzeige.Combo;

//import All_Unlimited.Allgemein.Anzeige.Memo1;

//public class SQL extends powersoft.powerj.db.java_sql.Query
public class SQL
{
	private Vector<String> VecBezeichnung 	= new Vector<String>();
	private Vector<String> VecInhalt 	= new Vector<String>();
	private Vector<String> VecVerbindungstabellen = new Vector<String>();
	private Vector<String> VecVerbindungstabellenwerte = new Vector<String>();
	private Transact g;
        private Vector<String> VecNachher = null;
        public boolean bExec2=false;
        private static boolean bSperre=false;
        private static String sSperre=null;
        /*public static boolean bASA=false;
        public static boolean bASA10=false;
        public static boolean bOracle=false;
        //public static boolean bSAP=false;
        public static boolean bMS=false;
        public static boolean bMySQL=false;*/
        public static AUVector VecTab=new AUVector(new String[] {"ABFRAGE","ABSCHLUSS","ABSCHLUSSTYP","ABSCHNITT","ANSICHT","ASERVER","AUSWAHL",
            "BEDINGUNG","BEFEHL","BEGRIFF","BEGRIFFGRUPPE","BEG2_Z","BENUTZER","BENUTZERGRUPPE","BEW_POOL","BEWEGUNGSTYP","CODE","COMPUTER",
            "DATEN","DARSTELLUNG","DRUCK","DEFVERLAUF","DEFIMPORT","EIGENSCHAFT","FARBE","FAVORIT","FEHLER","FEIERTAG","FENSTERPOS","FIXEIGENSCHAFT","FORMULAR",
            "HAUPTGRUPPE","HAUPTSCHIRM","KOMPONENTE","LAND","LAYOUT","LOGGING","MANDANT","MODELL","NEBENGRUPPE","PARAMETER","PROTOKOLL","RASTER","ROLLE","SCHRIFT","SCHRIFTART",
            "SPALTE","SPALTEN2","SPALTE_Z2","SPALTENNAME","SPERRE","SPRACHE","STAMM","STAMMPOOL","STAMMTYP","STATUS","SYSTEMINFO","TABELLENNAME","VERLAUF","WW","ZEILE","ZUORDNUNG","ZUSTAND","ZWISCHENSPEICHER"});
	//private boolean bTest=false;
        private String sSQL=null;
        private String sBind=null;
        private java.sql.ResultSet resultSet;
        private boolean bEof;
        private boolean bINH=false;
        private boolean bFirstError=true;
        private static Tabellenspeicher TabImage=null;

	public SQL(Transact rg)
	{
		//setTransactionObject( Tra );
		//super(rg,null,false);
		g=rg;
		Count.add(Count.SQL);
		//g.add("S");
	}

	/*
	public boolean exec(String s)
	{
		setSQL(s);
		StatementResults Error = execute();
		//s = s + "\n";
		//for (Enumeration e = getPrimaryBuffer() ; e.hasMoreElements() ;)
		//	s = s + e.nextElement().toString();
		boolean bFehler = Error.numExceptions() == 1;
		if (bFehler)
		{
			//s = Error.exceptions().nextElement().toString();
			for (Enumeration e = Error.exceptions() ; e.hasMoreElements() ;)
				s = s+"\n ->"+e.nextElement().toString().substring(71);
			Error.clear(false);
		}
		g.addInfo(s);
		return !bFehler;
	}
	*/

       public void createAllSequences()
       {
         //for(int i=0;i<VecTab.size();i++)
         for( Object s:VecTab)
         {
           String sTab=(String)s;//VecTab.elementAt(i);
           if (exists(g,"select OBJECT_ID from user_objects where object_type='SEQUENCE' and OBJECT_NAME='AIC_"+sTab+"'"))
             g.exec("drop sequence AIC_"+sTab);
          g.bISQL = true;
          int iAic=getInteger(g,"select max(aic_"+sTab+") from "+(sTab.equals("DATEN") ? "stammpool":sTab),-1);
          g.bISQL = false;
          if (iAic>=0)
          {
            g.fixInfo("AIC_"+sTab+"="+iAic);
            g.exec("create sequence AIC_"+sTab+" start with "+(iAic+1)+" NOCACHE");
          }
         }
       }

	public boolean exec(String s)
	{
		return g.exec(s)>-2;
	}

	/*public static boolean exec(Transact t,String s)
	{
		//powersoft.powerj.db.java_sql.Query Qry =new powersoft.powerj.db.java_sql.Query(t,null,false);
		//boolean b=t.exec(s);
		//Qry.destroy(true,true);
		return t.exec(s)>-2;
	}*/

	/*public boolean exec(String s,boolean bDestroy)
	{
		boolean b=g.exec(this,s);
		if (bDestroy)
			destroy(true,true);
		return b;
	}*/

        public void setSQL(String s)
        {
          sSQL=s;
        }
        
        public void printSQL()
        {
        	g.fixInfo("SQL="+sSQL);
        }

	public boolean open(String s)
	{
          setSQL(s);
          /*resultSet = g.open2(sSQL);
          if (!g.bFehler)
            resultSet.next();
          return !g.bFehler;*/
          return open2();
	}

        /*public void moveFirst()
        {
          if (!g.bFehler)
          try
          {
            resultSet.first();
          }
          catch (SQLException e) {}
        }*/


        public boolean moveNext()
        {
          if (!g.bFehler)
          try
          {
            bEof=!resultSet.next();
            return !bEof;
          }
          catch (SQLException e)
          {
            Static.printError("SQL.moveNext():"+e);
          }
          return false;
        }

        /*public void moveLast()
        {
          try
          {
            bEof=!resultSet.last();
          }
          catch (SQLException e)
          {
            Static.printError("SQL.moveLast():"+e);
          }
        }*/

        public boolean eof()
        {
          return resultSet==null || bEof;
        }

        public void close()
        {
          try
          {
            resultSet.close();
            if (g.Oracle())
                 resultSet.getStatement().close();
          }
          catch (SQLException e)
          {
            Static.printError("SQL.close():"+e);
          }

        }

        public boolean open2()
        {
          resultSet = g.open2(sSQL,true,sBind);
          if (resultSet != null)
        	  moveNext();
          return !g.bFehler;
        }

	/*public void close()
	{
		destroy(true,true);
	}*/

	public void free()
	{
		if (VecNachher != null)
			exec3();
		//while (VecNachher != null);
          //g.progInfo("SQL.free");
          if (resultSet != null)
          {
            close();
            /*try
            {
              resultSet.close();
            }
            catch(Exception e)
            {
              Static.printError("SQL.free:"+e);
            }*/
            resultSet=null;
          }
          //destroy(true,true);
	}

	public void finalize()
	{
		Count.sub(Count.SQL);

		//g.debugInfo("SQL finalize");
		/*VecBezeichnung = null;
		VecInhalt 	= null;
		VecVerbindungstabellen = null;
		VecVerbindungstabellenwerte = null;*/
	}

	public Object getSave(char c,String s)
	{
		//ConstantDataValue iVor = getValue(s);
                try
                {
                  resultSet.getString(s);
                  return resultSet.wasNull() ? null : c == 'D' ? resultSet.getDate(s) : c == 'T' ? resultSet.getTime(s) : c == 'P' ? resultSet.getTimestamp(s) :
                      c == 'I' ? (Object)new Integer(resultSet.getInt(s)) : c == 'X' ? new ImageIcon(resultSet.getBytes(s)) : c == 'B' ? resultSet.getBinaryStream(s): null;
                }
                catch (SQLException e)
                {
                    Fehlerausgabe("getSave",s,e);
                    return null;
                }
	}
	
	private void Fehlerausgabe(String sFunc,String s,SQLException e)
    {
		Static.printError("SQL."+sFunc+"(): Spalte " + s + " nicht gefunden!");    
		if ((Static.bInfoExcept || g.Def()) && bFirstError)
            g.printStackTrace(e);
        bFirstError=false;
    }

	public java.sql.Date getD(String s)
	{
		return (java.sql.Date)getSave('D',s);
		//Date dt=null;
		//ConstantDataValue iVor = getValue(s);
		//return iVor.isNull() ? null : (java.sql.Date)iVor.getDateNoThrow();//new Date(((java.sql.Date)iVor.getDateNoThrow()).getTime());
	}

	public java.sql.Time getT(String s)
	{
		return (java.sql.Time)getSave('T',s);
		//Date dt=null;
		//ConstantDataValue iVor = getValue(s);
		//return iVor.isNull() ? null : (java.sql.Time)iVor.getTimeNoThrow();//new java.sql.Time(((java.sql.Time)iVor.getTimeNoThrow()).getTime());
	}

	public java.sql.Timestamp getTS(String s)
	{
		return (java.sql.Timestamp)getSave('P',s);
		//Date dt=null;
		//ConstantDataValue iVor = getValue(s);
		//return iVor.isNull() ? null : (java.sql.Timestamp)iVor.getTimestampNoThrow();//new Date(((java.sql.Timestamp)iVor.getTimestampNoThrow()).getTime());
	}

	public double getF(String s)
	{
          try
          {
            return resultSet.getDouble(s);
          }
          catch (SQLException e)
          {
        	Fehlerausgabe("getF",s,e);
            return 0.0;
          }
	}

	public int getI(String s)
	{
          if (eof())
            return 0;
          try
          {
            int i=resultSet.getInt(s);
            return i;
          }
          catch (SQLException e)
          {
        	  Fehlerausgabe("getI",s,e);
            //Static.printError("SQL.getI(): Spalte " + s + " nicht gefunden bei:"+sSQL);
            return 0;
          }
	}

	public long getL(String s)
	{
          try
          {
            return resultSet.getLong(s);
          }
          catch (SQLException e)
          {
        	  Fehlerausgabe("getL",s,e);
            //Static.printError("SQL.getL(): Spalte " + s + " nicht gefunden!");
            return 0l;
          }
	}

	public Integer getInt(String s)
	{
          Object Obj=getSave('I',s);
          return Obj==null ? null:(Integer)Obj;
		//ConstantDataValue iVor = getValue(s);
		//return iVor.isNull() ? null : new Integer(iVor.getIntNoThrow());
	}

        /*public Integer getInt2(String s)
        {
          Object Obj=getSave('I',s);
          return Obj==null ? Static.Int0:(Integer)Obj;
        }*/


	public boolean getB(String s)
	{
          try
          {
            String s2=resultSet.getString(s);
            return s2!=null && s2.startsWith("1");
          }
          catch (SQLException e)
          {
        	  Fehlerausgabe("getB",s,e);
            //Static.printError("SQL.getB(): Spalte " + s + " nicht gefunden bei:"+sSQL);
            return false;
          }

	}

	public String getS(String s)
	{
          if (eof())
            return "";
          try
          {
            String s2=resultSet.getString(s);
            return s2==null ? "":s2;
          }
          catch (SQLException e)
          {
        	  Fehlerausgabe("getS",s,e);
            //Static.printError("SQL.getS(): Spalte " + s + " nicht gefunden bei:"+sSQL);
            return "";
          }
	}
	
	public boolean exists(String s)
	{
		try
        {
          resultSet.getString(s);
          return true;
        }
        catch (SQLException e)
        {
        	g.fixtestInfo("Spalte "+s+" nicht gefunden");
        	return false;
        }
	}
	
	public static Image getImage(Transact g,int iDaten)
	{
		return getImage(g,iDaten,false);
	}

    public static Image getImage(Transact g,int iDaten,boolean bMini)
    {
      SQL Qry =new SQL(g);
      Qry.sBind=""+iDaten;
      String sSpalte=bMini? "Mini":"Bild2";
      Qry.sSQL="select "+sSpalte+" from Daten_Bild2 where aic_daten=?";
      Qry.open2();
      Qry.sBind=null;
      Image Img=Qry.eof() || Qry.isNull(sSpalte) ? null:Qry.getImageIcon(sSpalte).getImage();
      Qry.free();
      return Img;
    }
    
    public static Image getImage(Global g,String sFile)
    {
      if (sFile.indexOf("_")>0)
      {
        int iDaten=Integer.parseInt(sFile.substring(0,sFile.indexOf("_")));
        if(iDaten>0)
        {
        	SQL Qry =new SQL(g);
            Qry.sBind=""+iDaten;
            Qry.sSQL="select Bild2 from Daten_Bild2 where aic_daten=?";
            Qry.open2();
            Qry.sBind=null;
            Image Img=Qry.eof() || Qry.isNull("Bild2") ? null:Qry.getImageIcon("Bild2").getImage();
            Qry.free();
            return Img;
        }
      }
      else if (!sFile.equals(""))
        Static.printError("getImage fehlerhaft:"+sFile);
      return null;
    }
    
    public static Image getImageD(Transact g,int iDaten)
    {
    	SQL Qry =new SQL(g);
        Qry.sBind=""+iDaten;
        Qry.sSQL="select doku from Daten_Doku where aic_daten=?";
        Qry.open2();
        Qry.sBind=null;
        Image Img=Qry.eof() || Qry.isNull("doku") ? null:Qry.getImageIcon("doku").getImage();
        Qry.free();
        return Img;
    }
        
    public static Image getImageZ(Global g,int iZeile)
    {
    	Image Img=getTabImage(g,"Z"+iZeile);
    	if (Img != null) return Img;
    	g.fixtestError("Hole Bild für Zeile "+iZeile);
    	SQL Qry =new SQL(g);
        Qry.sBind=""+iZeile;
        Qry.sSQL="select BildKF from Zeile where aic_zeile=?";
        Qry.open2();
        Qry.sBind=null;
        Img=Qry.eof() || Qry.isNull("BildKF") ? null:Qry.getImageIcon("BildKF").getImage();
        setTabImage("Z"+iZeile,Img);
        Qry.free();
        return Img;
    }
    
    private static Image getTabImage(Global g,String s)
    {
    	if (TabImage==null)
    		TabImage=new Tabellenspeicher(g,new String[] {"K","Bild"});
    	int iPos=TabImage.getPos("K", s);
    	return iPos<0 ? null:(Image)TabImage.getInhalt("Bild", iPos);
    }
    
    private static void setTabImage(String s,Image Img)
    {
    	int iPos=TabImage.newLine();
    	TabImage.setInhalt(iPos,"K", s);
    	TabImage.setInhalt(iPos,"Bild", Img);
    }
    
    public static void clearTabImage(Global g)
    {
    	if (TabImage!=null)
    	{
    		g.fixtestError(TabImage.size()+" aus TabImage gelöscht");
    		TabImage.clearAll();
    		TabImage=null;
    	}
    }
    
    public static Image getImageA(Global g,int iAbschnitt)
    {
    	Image Img=getTabImage(g,"A"+iAbschnitt);
    	if (Img != null) return Img;
    	g.fixtestError("Hole Bild für Abschnitt "+iAbschnitt);
    	SQL Qry =new SQL(g);
        Qry.sBind=""+iAbschnitt;
        Qry.sSQL="select Bild from daten_bild where aic_tabellenname="+g.TabTabellenname.getAic("ABSCHNITT")+" and aic_fremd=?";
        Qry.open2();
        Qry.sBind=null;
        Img=Qry.eof() || Qry.isNull("Bild") ? null:Qry.getImageIcon("Bild").getImage();
        setTabImage("A"+iAbschnitt,Img);
        Qry.free();
        return Img;
    }

	private ImageIcon getImageIcon(String s)
	{
		return (ImageIcon)getSave('X',s);
		//ConstantDataValue iVor = getValue(s);
		//return(iVor.isNull() ? null : new ImageIcon(iVor.getBytesNoThrow()));
	}

        public static String getDoku(Transact g,int iDaten)
        {
          long lClock3=Static.get_ms();
          SQL Qry =new SQL(g);
          Qry.sSQL="select filename,Doku from Daten_Doku where aic_daten=?";
          Qry.sBind=""+iDaten;
          g.bTry=true;
          Qry.open2();
//          g.clockInfo3("Doku "+iDaten+" holen1", lClock3);
//          lClock3=Static.get_ms();
          String s=Qry.eof() ? null:Qry.getDoku("Doku",Qry.getS("Filename"),lClock3);
          Qry.free();
          return s;
        }
        
        public static String getDoku(Transact g,String sFile)
        {
    	  if (Static.Leer(sFile))
    		return Static.sLeer;
    	  int iIndexUS=sFile.indexOf("_");
      	  if (iIndexUS<1)
      		return Static.sLeer;
      	  int iDaten=Integer.parseInt(sFile.substring(0,iIndexUS));
      	  if (iDaten>0)
      		  return getDoku(g,iDaten);
      	  else
      		return Static.sLeer;
//          SQL Qry =new SQL(g);
//          Qry.sSQL="select filename,Doku from Daten_Doku where aic_daten=?";
//          Qry.sBind=""+iDaten;
//          g.bTry=true;
//          Qry.open2();
//          String s=Qry.eof() ? null:Qry.getDoku("Doku",sFile);
//          Qry.free();
//          return s;
        }

        private String getDoku(String s, String sFile,long lClock3)
        {
          InputStream is = (InputStream)getSave('B',s);
          sFile=sFile.replaceAll(" ", "_");
          g.clockInfo3("Doku "+sFile+" holen", lClock3);
          lClock3=Static.get_ms();
          String sFileNeu=Static.getTemp()+sFile;
          File doku = new File(sFileNeu);
          try
          {
            FileOutputStream fos = new FileOutputStream(doku);
            byte[] buffer = new byte[64];
            int iAnz=is.read(buffer);
            // int i1=0;
            while (iAnz > 0) {
              // g.fixtestError(sFile+": "+iAnz+" bei "+i1);            
              if (iAnz==64)
                fos.write(buffer);
              else for (int i=0;i<iAnz;i++)
                fos.write(buffer[i]);
              iAnz=is.read(buffer);
              // i1++;
            }
            fos.close();
            g.clockInfo3("Doku "+sFile+" erstellen", lClock3);           
            return "file:"+File.separator+File.separator+sFileNeu;
          }
          catch (Exception e)
          {
            Static.printError("SQL.getDoku: "+sFile+" nicht ladbar");
          }
          g.clockInfo3("keine Doku erstellen", lClock3);        
          return null;
        }

	public boolean isNull(String s)
	{
          try
          {
            resultSet.getString(s);
            return resultSet.wasNull();
          }
          catch (SQLException e)
          {
            Static.printError("SQL.isNull(): Spalte " + s + " nicht gefunden!");
            return true;
          }
	}

        /* ----------------------------------------------------------------------------------- */

        /*public String in(String sSpalte,Vector Vec)
	{
          return g.in(sSpalte, Vec);
	}*/

        public int deleteTop(String s,int iTop)
        {
          return g.exec("delete"+(g.Oracle() || g.MS() || g.MySQL() ?"":" top "+iTop)+" from "+s+(g.Oracle()?" and rownum<="+iTop:""));
        }

        // Ende der Unterschiede

        public int deleteFrom(String sTab,String s)
        {
          return deleteFrom(sTab,s,0);
        }

        private String getStringValue(int i)
        {
          try
            {
              return resultSet.getString(i);
            }
            catch(SQLException e)
            {
              Static.printError("SQL.getStringValue():"+e);
              return null;
            }
        }

        public int deleteFrom(String sTab,String s,int iTab)
        {
          //g.progInfo("delete from " + sTab+":"+s);
          Vector<Integer> Vec=new Vector<Integer>();
          if (!open("select "+sTab+".aic_"+sTab+" from "+s))
              return -1;
          for(;!eof();moveNext())
          {
            //s = getValue(1).isNull()?null:getStringValue(1);
            int i = getI("aic_"+sTab);
            if(i>0 && !Vec.contains(i))
              Vec.addElement(i);
          }
          if (Vec.size()==0)
            return 0;
          else
          {
            if (sTab.toUpperCase().equals("BEGRIFF"))
            {
              int i1=g.exec("delete from verlauf where "+g.in("aic_begriff",Vec));
              int i2=g.exec("delete from defverlauf where "+g.in("aic_begriff",Vec));
              int i3=g.exec("delete from fehler where "+g.in("aic_begriff",Vec));
              int i4=g.exec("delete from begriff_Zuordnung where "+g.in("beg_aic_begriff",Vec));
              g.diskInfo("delete verlauf:"+i1+", defverlauf:"+i2+", fehler="+i3+", Zuord="+i4);
            }
            else if (sTab.toUpperCase().equals("STAMM"))
            {
            	int i1=g.exec("delete from stammpool where"+g.in("aic_stamm",Vec));
            	int i2=g.exec("delete from stammpool where"+g.in("sta_aic_stamm",Vec));
            	int i3=g.exec("update stamm_protokoll set firma=null where"+g.in("Firma",Vec));
            	int i4=g.exec("delete from spalten2 where"+g.in("anr",Vec));
            	int i5=g.exec("update benutzer set aic_stamm=null where"+g.in("aic_stamm",Vec));
            	int i6=g.exec("update stamm_protokoll set anr=null where"+g.in("anr",Vec));
            	int i7=g.exec("delete from spalte_Zuordnung where"+g.in("aic_stamm",Vec));
            	g.diskInfo("delete stammpool1: "+i1+", stammpool2: "+i2+", Firma: "+i3+", ANR: "+i6+", Spalten2: "+i4+", Spalten_Z: "+i7+", Benutzer: "+i5);
            }
            if (iTab>0)
            {
              int iB=g.exec("delete from Bezeichnung where aic_tabellenname="+iTab+" and "+g.in("aic_fremd",Vec));
              int iM=g.exec("delete from Daten_Memo where aic_tabellenname="+iTab+" and "+g.in("aic_fremd",Vec));
              int iI=g.exec("delete from Daten_Bild where aic_tabellenname="+iTab+" and "+g.in("aic_fremd",Vec));
              int iT=g.exec("delete from Tooltip where aic_tabellenname="+iTab+" and "+g.in("aic_fremd",Vec));
              g.progInfo(sTab+": "+iB+" x Bezeichnung, "+iM+"x Memo, "+iT+"x Tooltip, "+iI+"x Bild gelöscht");
            }
            int i=g.exec("delete from " + sTab + " where " + g.in("aic_" + sTab,Vec));
            if (i>0)
              g.progInfo(sTab+": "+i+" gelöscht");
            return i;
          }
        }

        public static boolean exists(Transact t,String s)
	{
          return t.exists(s,null);
        }

	public static boolean exists(Transact t,String s,String sBind)
	{
          return t.exists(s,sBind);
             /*long lClock = Static.get_ms();
             boolean b=false;
             java.sql.ResultSet resultSet = t.open2(s,false,sBind);
              try {
                b=resultSet.next();
                resultSet.close();
                if (t.Oracle())
                 resultSet.getStatement().close();
              }
              catch (java.sql.SQLException e) {
                t.printError("SQL.exists:"+e);
              }
              t.saveClock("exists", s,lClock,b ? 1:0);
              return b;*/
	}

        public static String getString(Transact t,String s)
        {
          return getString(t,s,null,1);
        }
        
        public static String getString2(Transact t,String s)
        {
          return getString(t,s,null,2);
        }
        
        public static String getString(Transact t,String s,String sBind)
        {
        	return getString(t,s,sBind,1);
        }

	public static String getString(Transact t,String s,String sBind,int iNr)
	{
		/*powersoft.powerj.db.java_sql.Query Qry =new powersoft.powerj.db.java_sql.Query(t,null,false);
		Qry.setSQL(s);
		t.SafetyOpen(Qry);
		s = Qry.eof() ? "" : Qry.getStringValue(1);
		Qry.destroy(true,true);*/
             long lClock = Static.get_ms();
             java.sql.ResultSet resultSet = t.open2(s,false,sBind);
             if (resultSet==null)
             {
              if (!t.bISQL)
                t.printError("SQL-Fehler bei getString:"+s);
              return null;
             }
             String s2="";
              try {
                if (resultSet.next())
                  s2 = resultSet.getString(iNr);
                resultSet.close();
                if (t.Oracle())
                 resultSet.getStatement().close();
              }
              catch (java.sql.SQLException e) {
                t.printError("SQL.getString:"+e);
              }
              //t.saveClock("String", s,lClock,-2);
              t.saveClock("String", s,lClock,s2,sBind);
              return s2;
	}

  public static long getLong(Transact t,String s)
  {
    return getLong(t,s,null);
  }

	public static long getLong(Transact t,String s,String sBind)
	{
             long lClock = Static.get_ms();
             long l = 0;
             java.sql.ResultSet resultSet = t.open2(s,false,sBind);
             try
             {
               if (resultSet.next())
              	 l = resultSet.getLong(1);
               resultSet.close();
               if (t.Oracle())
                 resultSet.getStatement().close();
             }
             catch (java.sql.SQLException e)
             {
               t.printError("SQL.getLong:"+e);
             }
             t.saveClock("Long", s, lClock, ""+l,"-2");
             return l;
	}

        public static int getInteger(Transact t,String s)
        {
          return getInteger(t,s,0,null,1);
        }
        
        public static int getInteger2(Transact t,String s)
        {
          return getInteger(t,s,0,null,2);
        }

        public static int getInteger(Transact t,String s,int i2)
        {
          return getInteger(t,s,i2,null,1);
        }
        
        public static int getInteger(Transact t,String s,int i2,String sBind)
        {
        	return getInteger(t,s,i2,sBind,1);
        }

        public static int getInteger(Transact t,String s,int i2,String sBind,int iPos)
        {
		/*powersoft.powerj.db.java_sql.Query Qry =new powersoft.powerj.db.java_sql.Query(t,null,false);
		Qry.setSQL(s);
		t.SafetyOpen(Qry);
		int i = Qry.eof() || Qry.getValue(1).isNull() ? 0 : Qry.getValue(1).getIntNoThrow();
		Qry.destroy(true,true);*/
             long lClock = Static.get_ms();
             int i = i2;
             java.sql.ResultSet resultSet = t.open2(s,false,sBind);
             try
             {
               i= resultSet.next() ? resultSet.getInt(iPos):i2;
               resultSet.close();
               if (t.Oracle())
                 resultSet.getStatement().close();
             }
             catch (Exception e)
             {
               if (!t.bISQL)// && i2==0)
                 t.printError("SQL.getInteger:"+e);
             }
             resultSet=null;
             t.saveClock("Integer", s, lClock, i);
             return i;
        }

        public static java.sql.Timestamp getNow(Transact t)
        {
          return getTimestamp(t,t.Oracle() ?"select sysdate from dual":t.ASA() || t.MySQL() ? "select now()":t.MS()?"select getdate()":"getNow");
        }

        public static java.sql.Timestamp getToday(Transact t)
        {
          return getTimestamp(t,t.Oracle() ?"select trunc(sysdate) from dual":t.MySQL() ? "select current_date":
                              t.MS()?"select CAST(CONVERT(char(8), GETDATE(), 112) AS datetime) today":"select today()");
        }
        
        public static int getZone(Transact t)
        {
//        	java.sql.Timestamp ts= getTimestamp(t,t.MySQL() ? "SELECT TIMEDIFF(NOW(), UTC_TIMESTAMP)":"xx");
//        	long l=ts.getTime();
//        	int i=(int)(l/60000);
        	int i=SQL.getInteger(t, t.MySQL() ? "select TIMESTAMPDIFF(MINUTE,UTC_TIMESTAMP,NOW())":"select datediff(mi,getUTCDATE(),CURRENT_TIMESTAMP)");
        	t.fixtestError("getZone von DB:"+i);
//        	select datediff(mi,getUTCDATE(),CURRENT_TIMESTAMP)
        	return i;
        }
       

        /*public String Dummy()
        {
          return bASA || bMS ? "":" from dual";
        }*/
        
        public static java.sql.Timestamp getTimestamp(Transact t,String s)
        {
        	return getTimestamp(t,s,null);
        }

	public static java.sql.Timestamp getTimestamp(Transact t,String s,String sBind)
	{
		/*powersoft.powerj.db.java_sql.Query Qry =new powersoft.powerj.db.java_sql.Query(t,null,false);
		Qry.setSQL(s);
		t.SafetyOpen(Qry);
		java.sql.Timestamp ts = Qry.eof() || Qry.getValue(1).isNull() ? null : (java.sql.Timestamp)Qry.getValue(1).getTimestampNoThrow();
		Qry.destroy(true,true);*/
             long lClock = Static.get_ms();
             java.sql.Timestamp ts = null;
             java.sql.ResultSet resultSet = t.open2(s,false,sBind);
             try
             {
               if (resultSet.next())
              	 ts = resultSet.getTimestamp(1);
               resultSet.close();
               if (t.Oracle())
                 resultSet.getStatement().close();
             }
             catch (java.sql.SQLException e) {}
             t.saveClock("Timestamp", s, lClock, -2);
		return ts;
	}

        public String list(String sSpalte,String sSQL)
        {
          if (open("select distinct "+sSpalte+" from "+sSQL))
          {
            String s=eof() ? " ":getStringValue(1);
            if (!eof())
              for(moveNext();!eof();moveNext())
                s+=","+getStringValue(1);
            close();
            return s;
          }
          else
            return "";
        }

	public String getString(String s)
	{
		return getString(g,s);
	}

	public int getInteger(String s)
	{
          return getInteger(g,s);
	}

	public static double getDouble(Transact t,String s)
	{
    long lClock = Static.get_ms();
		double d = 0;
     java.sql.ResultSet resultSet = t.open2(s,false,null);
     try
     {
       if (resultSet.next())
      	 d = resultSet.getDouble(1);
       resultSet.close();
       if (t.Oracle())
         resultSet.getStatement().close();
     }
     catch (java.sql.SQLException e) {}
     t.saveClock("Double", s,lClock,(int)Math.round(d));

		return d;
	}

	/*public java.sql.Date getDate(String s)
	{
          long lClock = Static.get_ms();
          java.sql.Date dt=null;

             java.sql.ResultSet resultSet = g.open2(s,false);
             try
             {
               resultSet.next();
               dt = resultSet.getDate(1);
             }
             catch (java.sql.SQLException e) {}
             g.saveClock("Date", s, lClock, -2);

             return dt;
	}*/

	public void clear()
	{
		VecBezeichnung.removeAllElements ();
		VecInhalt.removeAllElements ();
		VecVerbindungstabellen.removeAllElements ();
		VecVerbindungstabellenwerte.removeAllElements ();
	}

	//String in Byte-Arrayform mit einfachen Hochkomma//
	public void add(String Bez, byte[] s)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement(new String(s));
	}

	public void add2(String Bez, String s)
	{
		VecBezeichnung.addElement(Bez);
                VecInhalt.addElement(s==null||s.equals("") ? "null" : s);
		//VecInhalt.addElement(s);
	}

    public void addnow(String Bez)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement(g.now());
		//VecInhalt.addElement(s);
	}
    
    public void addNr(int iStamm)
    {
    	VecBezeichnung.addElement("Nr");
    	VecInhalt.addElement(""+(getInteger(g,"select max(nr) from stamm_protokoll where aic_stamm=?",0,""+iStamm)+1));
    }

        /*public void addVon()
        {
          VecBezeichnung.addElement("von");
          VecInhalt.addElement(bASA ? "(select von from dummy)":"(select von from zr)");
        }

        public void addBis()
        {
          VecBezeichnung.addElement("bis");
          VecInhalt.addElement(bASA ? "(select bis from dummy)":"(select bis from zr)");
        }*/

	//String//
        public void add(String Bez, String s)
        {
          add(Bez,s,Static.iMemoMax);
        }

	public void add(String Bez, String s,int iAnz)
	{
		VecBezeichnung.addElement(Bez);
//                if (g.MySQL()) 	
//                  s=Static.StringForMySQL(s); // ist doppelt, deshalb 9.4.2018 entfernt
		VecInhalt.addElement(s==null||s.equals("") ? "null" : Static.StringForSQL(s,iAnz));
		//VecInhalt.addElement(s);
	}


	//Integer//
	public void add(String Bez, Integer i)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement( i==null?"null":new String(i.toString()) );
	}
        public void add0(String Bez, Integer i)
        {
                VecBezeichnung.addElement(Bez);
                VecInhalt.addElement( i==null || i.intValue()==0?"null":new String(i.toString()) );
        }

	//int//
	public void add(String Bez, int i)
	{
          if (g.MySQL() && Bez.startsWith("int"))
            Bez=g.rw(Bez);
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement( String.valueOf(i));
	}
	public void add0(String Bez, int i)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement( i==0 ? "null" : String.valueOf(i));
	}

	//long//
	public void add(String sBez, long l)
	{
		VecBezeichnung.addElement(sBez);
		VecInhalt.addElement( String.valueOf(l));
		//g.fixInfo("SQL.add(l):"+sBez+"="+VecInhalt.lastElement());
	}

	//Float//
	/*public void add(String Bez, float f)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement(new String().valueOf(f));
	}
	public void add0(String Bez, float f)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement( f==0.0 ? "null" : new String().valueOf(f));
	}*/

	//Double//
	public void add(String Bez, double f)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement(String.valueOf(f));
	}
	public void add0(String Bez, double f)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement( f==0.0 ? "null" : String.valueOf(f));
	}

	//Boolean//
	public void add(String Bez, boolean b)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement(b ? "1":"0");
	}
	public void add0(String Bez, boolean b)
	{
		VecBezeichnung.addElement(Bez);
		VecInhalt.addElement( b ? "1":"null");
	}

	//Date//
	/*public void add(String Bez, Date d)
	{
		//add(Bez,d == null ? "" : ""+(d.getYear ()+1900) + "/" + (d.getMonth()+1) + "/" + d.getDate () );
		//add(Bez,d == null ? "" : new SimpleDateFormat("yyyy/MM/dd").format(d));
                add2(Bez,DateTimeToString(d,g));
	}*/

	//Timestamp//
	public void add(String Bez,java.sql.Timestamp d)
	{
		//add(Bez,d == null ? "" : ""+(d.getYear ()+1900) + "/" + (d.getMonth()+1) + "/" + d.getDate () );
		add2(Bez,g.DateTimeToString(d));
	}

	// Object //
	public void add(String Bez,Object Obj)
	{
		VecBezeichnung.addElement(Bez);
		if (Obj instanceof Combo)
		  VecInhalt.addElement(Obj==Combo.CboKein ? "null" : String.valueOf(((Combo)Obj).getAic()));
		else
		  VecInhalt.addElement(g.SQL_Format(Obj));
	}

        /*public String SQL_Format(Object Obj)
	{
		return Obj == null ? "null" : Obj instanceof Date ? g.DateTimeToString((Date)Obj):
			Obj instanceof String ? Static.StringForSQL((String)Obj):
                        Obj instanceof Memo1 ? Static.StringForSQL(((Memo1)Obj).getValue()):
                        Obj instanceof Boolean?Obj.equals(Boolean.TRUE)?"1":"0":Obj.toString();
	}*/

	public void addWhere(String sBez, int iAIC)
	{
		VecVerbindungstabellen.addElement(sBez);
		VecVerbindungstabellenwerte.addElement(""+iAIC);
	}

        public void addWhere(String sBez, String s)
        {
                VecVerbindungstabellen.addElement(sBez);
                VecVerbindungstabellenwerte.addElement(Static.StringForSQL(s));
        }

	public boolean delete(String sTabellenname)
	{
		String s = new String("Delete from "+sTabellenname+" where "+VecBezeichnung.elementAt(0)+"="+VecInhalt.elementAt(0));
		for(int i=1; i<VecBezeichnung.size(); i++)
			s = s + " and " + VecBezeichnung.elementAt(i)+"="+VecInhalt.elementAt(i);

		int iAnz = -1;
		iAnz=g.exec(s);

		return iAnz>0;
	}

        public void insert2(String sTabellenname,boolean bNachher)
        {
          insert(sTabellenname,false,bNachher);
        }

        public void exec2(String s,boolean bNachher)
        {
          if (bNachher)
          {
            if (VecNachher == null)
              VecNachher=new Vector<String>();
            VecNachher.addElement(s);
          }
          else
            g.exec(s);
        }

        public void exec2()
        {
          if (VecNachher != null)
            new Thread(new Runnable() {
              public void run() {
            	  exec3();
              }
            }).start();
        }
        
        public void exec3()
        {
        	//g.fixtestError("exec3");
        	bExec2 = true;
            //long lClock = Static.get_ms();
            int iAnz = VecNachher.size();
            String sSQL="";
            for(int i = 0; i < iAnz; i++)
              sSQL+=VecNachher.elementAt(i)+";";
            g.exec(sSQL);
            VecNachher = null;
            bExec2 = false;
//            g.clockInfo("exec2: " + iAnz + " x", lClock);
        }
        
        public void setNachher()
        {
        	bINH=true;
        }

        public int insert(String sTabellenname,boolean bNeedAIC)
        {
          return insert(sTabellenname,bNeedAIC,bINH);
        }

	public int insert(String sTabellenname,boolean bNeedAIC,boolean bNachher)
	{
          int iAic = 0;
          if (bNeedAIC)
          {
        	  bNachher=false;
        	  if (VecNachher != null)
        		  exec3();
          }
          if (bNachher)
          {
            if (VecNachher==null)
              VecNachher=new Vector<String>();
          }
          else if (g.Oracle())
          {
            if (VecTab.contains(sTabellenname.toUpperCase()))
            {
              iAic=getInteger("Select aic_"+sTabellenname+".nextval from dual");
              if (sTabellenname.toUpperCase().equals("DATEN"))
                return iAic;
              add("aic_"+sTabellenname,iAic);
            }
          }
          else if (sTabellenname.toUpperCase().equals("DATEN"))
            if ((g.ASA() || g.MySQL()) && VecBezeichnung.size()==0)
              add2("Aic_Daten","DEFAULT");
            else if (g.MS())
              add2("MS_will",null);

		String s = new String("INSERT INTO " + sTabellenname + "(" + VecBezeichnung.elementAt(0) );
		for(int i=1; i<VecBezeichnung.size(); i++)
			s = s + "," + VecBezeichnung.elementAt(i);
		s = s + ") VALUES (" + VecInhalt.elementAt(0);
		for(int i=1; i<VecInhalt.size(); i++)
			s = s + "," + VecInhalt.elementAt(i);
		s = s + ")";

		clear();
          if (bNachher)
          {
            VecNachher.addElement(s);
          }
          else
          {
            if(g.ASA() || (g.MySQL() || g.MS()) && !Static.bInsert2)
            {
              while(bSperre) {
                g.diskInfo("!!!!!!!! SQL-Sperre für "+sTabellenname+" wegen "+sSperre+" !!!!!!!!!");
                Static.sleep(100);
              }
              bSperre = true;
              sSperre=sTabellenname;
            }
            if (bNeedAIC && Static.bInsert2)
              iAic=g.insert(s);
            else if(g.exec(s) > -2) {
              if(bNeedAIC && !g.Oracle())
              {
                //if (g.ASA() || g.MS())
                  iAic = getInteger("SELECT @@identity");
                  //g.progInfo("AIC_"+sTabellenname+"="+iAic);
              }
            }
            else
              iAic = -1;
            bSperre = false;
          }
          return iAic;
	}
	
	public boolean update(String sTabellenname,int iAIC)
	{
		return update(sTabellenname,iAIC,false);
	}

	public boolean update(String sTabellenname,int iAIC,boolean bSingle)
    {
		if (VecBezeichnung.isEmpty())
		{
			g.printInfo(1,"Nichts zu ändern bei Tabelle "+sTabellenname+" mit AIC "+iAIC);
			return false;
		}

        String s = new String("UPDATE "+sTabellenname+" SET "+VecBezeichnung.elementAt(0)+"="+VecInhalt.elementAt(0));

		for(int i=1; i<VecBezeichnung.size(); i++)
			s = s + "," + VecBezeichnung.elementAt(i)+"="+VecInhalt.elementAt(i);

		s = s + " WHERE AIC_" + sTabellenname + "=" + iAIC;

		/*
		File f = new File("N:\\TEST.TXT");
		Save sav = new Save();
		sav.add(s);
		sav.save(f,f.exists());
		*/
		clear();

		boolean bOk = g.exec(s,bSingle)>-2;//exec(s);
		if (!bOk)
			Static.printError("SQL.Update: Folgender Befehl nicht durchführbar: "+s);
		return bOk;
    }

	public int update(String sTabellenname)
	{
    	String s = new String("UPDATE "+sTabellenname+" SET "+VecBezeichnung.elementAt(0)+"="+VecInhalt.elementAt(0));

		for(int i=1; i<VecBezeichnung.size(); i++)
			s = s + "," + VecBezeichnung.elementAt(i)+"="+VecInhalt.elementAt(i);

		s = s + " WHERE " + VecVerbindungstabellen.elementAt(0) + "=" + VecVerbindungstabellenwerte.elementAt(0);

		for(int i=1; i<VecVerbindungstabellen.size(); i++)
			s = s + " AND " + VecVerbindungstabellen.elementAt(i) + "=" + VecVerbindungstabellenwerte.elementAt(i);

		/*
		File f = new File("N:\\TEST.TXT");
		Save sav = new Save();
		sav.add(s);
		sav.save(f,f.exists());
		*/
		clear();

		return g.exec(s);
    }

    public static int update(Transact g,String sTable,String sSet,Vector Vec)
    {
      return update(g,sTable,sSet,Vec,sTable,5000,null);
    }

    public static int update(Transact g,String sTable,String sSet,Vector Vec,String sTable2,int iAnzahl,String sWhere)
        {
                //int iProt = g.Protokoll();
                if (sTable2==null)
                  sTable2=sTable;
                int iAnz=0;
                if (Vec.size()>0)
                {
                  while (Vec.size()>iAnzahl)
                  {
                    Vector<Object> Vec2=new Vector<Object>();
                    for(int i=0;i<iAnzahl;i++)
                    {
                      Vec2.addElement(Vec.elementAt(0));
                      Vec.removeElementAt(0);
                    }
                    iAnz+=g.exec("update "+sTable+" set "+sSet+" where "+(sWhere==null ? "":sWhere+" and ")+g.in("AIC_"+sTable2,Vec2));
                  }
                  iAnz+=g.exec("update "+sTable+" set "+sSet+" where "+(sWhere==null ? "":sWhere+" and ")+g.in("AIC_"+sTable2,Vec));
                }
                return iAnz;
        }


	/*public void setTest(boolean b)
	{
		bTest = b;
	}*/

	public static boolean addVector(Vector<Integer> Vec,String s,Transact t)
	{
		int iAnz=Vec.size();
		//powersoft.powerj.db.java_sql.Query Qry =new powersoft.powerj.db.java_sql.Query(t,null,false);
		//Qry.setSQL(s);
		//t.SafetyOpen(Qry);
                java.sql.ResultSet resultSet = t.open2(s,true,null);
                if (resultSet==null)
                	return false;
		//for(;!Qry.eof();Qry.moveNext())
                try
                {
                  while (resultSet.next())
                  {
                    //Integer i = Qry.getValue(1).isNull() ? null : new Integer(Qry.getValue(1).getIntNoThrow());
                    int i=resultSet.getInt(1);
                    Integer Int=new Integer(i);
                    if (!resultSet.wasNull() && !Vec.contains(Int))
                      Vec.addElement(Int);
                  }
                  resultSet.close();
                  if (t.Oracle())
                    resultSet.getStatement().close();
                }
                catch(SQLException e)
                {
                  Static.printError("SQL.addVector():"+e);
                }
		//Qry.destroy(true,true);

		return iAnz<Vec.size();
	}

	public static Vector<Integer> getVector(String s,Transact t)
	{
		Vector<Integer> Vec=new Vector<Integer>();
		addVector(Vec,s,t);
		return Vec;
	}

        public static boolean addSVector(Vector<String> Vec,String s,Transact t)
        {
                int iAnz=Vec.size();
                //powersoft.powerj.db.java_sql.Query Qry =new powersoft.powerj.db.java_sql.Query(t,null,false);
                //Qry.setSQL(s);
                //t.SafetyOpen(Qry);
                java.sql.ResultSet resultSet = t.open2(s,true,null);
                //for(;!Qry.eof();Qry.moveNext())
                try
                {
                  while (resultSet.next())
                  {
                    //s = Qry.getValue(1).isNull() ? null : Qry.getStringValue(1);
                    s=resultSet.getString(1);
                    if (s != null && !Vec.contains(s))
                      Vec.addElement(s);
                  }
                  resultSet.close();
                  if (t.Oracle())
                    resultSet.getStatement().close();
                }
                catch(SQLException e)
                {
                  Static.printError("SQL.addSVector():"+e);
                }
                //Qry.destroy(true,true);

                return iAnz<Vec.size();
        }

        public static Vector<String> getSVector(String s,Transact t)
        {
                Vector<String> Vec=new Vector<String>();
                addSVector(Vec,s,t);
                return Vec;
        }

  public static void open_cursors(String s,Transact t)
  {
  	if (t.Oracle())
  	{
  		SQL Qry=new SQL(t);
  		Qry.open("select max(a.value)  highest_open_cur, p.value  max_open_cur"+
  		  " from v$sesstat a, v$statname b, v$parameter p"+
  		  " where a.statistic# = b.statistic# and b.name = 'opened cursors current'"+
  		  " and p.name= 'open_cursors' group by p.value");
  		t.diskInfo(Qry.getI("highest_open_cur")+"/"+Qry.getI("max_open_cur")+" bei "+s);
  		Qry.free();
  	}
  }

}

