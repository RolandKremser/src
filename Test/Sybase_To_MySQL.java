package Test;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Memo1;

public class Sybase_To_MySQL extends java.applet.Applet
{
    /**
	 *
	 */
	private static final long serialVersionUID = -4720456842782182354L;
	public void init()
    {
		Static.DirError = "d:\\DVH\\AU\\Fehler\\MySQL\\";
		Static.DirImageSys = "file:\\D:\\DVH\\AU\\Images\\";
		//g = new Global("192.1.1.237:2650");

		g = new Global("192.168.1.140:2669");
                //g = new Global("Roland2:2669");
		//g.ComputerErmitteln();
		//t.connect("Roland2:1521:prog");
                //SQL.bASA=true;
                //SQL.bOracle=false;
                //t.connect2("myodbc","root",null);
                t.connect(".my:Roland3/DVH");
                Transact.iInfos=128;
                //t.connect("192.1.1.246/AU4");
                //t.connect("//192.1.1.254:1433;DatabaseName=AU;Uid=dbaau;Pwd=sql");
                //t.connect("//SERVER1\\SQL_SERVER1:1433;DatabaseName=SMT_CALIBRATE;User=datenbank;Password=datenbank");
                //t.connect("//192.1.1.254/jtds;tds=8.0;DatabaseName=AU;User=dbaau;Password=sql");
                //t.connect("jdbc:odbc:AU_MS2");

    }
	/*private void TabellennameCopy()
	{
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from tabellenname",true);
		Tab.showGrid();
		//g.clockInfo("Start",lClock);
		for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			long lClock=System.currentTimeMillis();
			SQL Qry =new SQL(t);
			//g.fixInfo(Tab.getPos()+":"+Tab.getI("aic_tabellenname")+"/"+Tab.getS("Kennung")+Tab.getB("fixe_sprache"));
			if (Tab.getI("aic_tabellenname")>0)
			{
				Qry.add("aic_tabellenname",Tab.getI("aic_tabellenname"));
				Qry.add("Kennung",Tab.getS("Kennung"));
				if(Tab.getB("fixe_sprache"))
					Qry.add("fixe_sprache",Tab.getB("fixe_sprache"));
				Qry.insert("tabellenname",false);
			}
			g.clockInfo(Tab.getS("kennung"),lClock);
		}
		long lClock=System.currentTimeMillis();
		new Tabellenspeicher(t,"select * from tabellenname",true).showGrid("Neu");
	}*/
	private void TableCopy(String sTable,int iAb)
	{
		long lClockGes=Static.get_ms();
		t.exec("delete from "+sTable);
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable+" where aic_"+sTable+">="+iAb+" order by aic_"+sTable,true);
		//Tab.showGrid(sTable+"-Sybase");
                int iAnzahl=Tab.getAnzahlElemente(null);
		int iAnzSpalten=Tab.getAnzahlSpalten();
                SQL Qry =new SQL(t);
                long lClock=Static.get_ms();
		for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			for(int i=0;i<iAnzSpalten;i++)
			{
				String sTitel=Tab.getTitel(i);
				Object Obj=Tab.getElementAt(Tab.getPos(),i);
				//g.fixInfo(sTitel+":"+Obj+"/"+Static.className(Obj));
                                if (sTitel.toUpperCase().startsWith("INT"))
                                  sTitel='`'+sTitel+'`';
                                // bei folgenden Tabellen nicht:
                                // Bewegungstyp: AIC_LOGGING
                                // Stammtyp: AIC_LOGGING, AIC_EIGENSCHAFT, STA_AIC_STAMMTYP
                                // Begriff: AIC_LOGGING, AIC_MANDANT, IMPORTZEIT
                                //if (Obj instanceof String)
                                //  Obj=((String)Obj).replaceAll("\\","\\\\");
                                if (Obj instanceof String)
                                  Qry.add(sTitel,(String)Obj);
                                else if (Obj !=null)
                                  Qry.add(sTitel,Obj);
			}
			Qry.insert(sTable,false);
                        int iPos=Tab.getPos();
                        if (iPos%1000==0)
                        {
                          t.clockInfo(sTable+" "+Static.rightString(""+iPos,5),lClock);
                          lClock=Static.get_ms();
                        }
			//g.clockInfo(sTable+" "+Static.rightString(Tab.getS("aic_"+sTable),5)/*+":"+Tab.getS("kennung")*/,lClock);
		}
                Qry.free();
		t.clockInfo("Gesamt "+sTable,lClockGes);
                t.diskInfo(sTable+" : "+iAnzahl+": "+(Static.get_ms()-lClockGes)+" ms");
                //new Tabellenspeicher(t,"select * from "+sTable,true).showGrid(sTable+"-MySQL");
	}

        private void TableCopy2(String sTable)
	{
		long lClockGes=Static.get_ms();
		t.exec("delete from "+sTable);
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable,true);
		//Tab.showGrid(sTable+"-Sybase");
                int iAnzahl=Tab.getAnzahlElemente(null);
		int iAnzSpalten=Tab.getAnzahlSpalten();
                SQL Qry =new SQL(t);
                long lClock=Static.get_ms();
		for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			for(int i=0;i<iAnzSpalten;i++)
			{
				String sTitel=Tab.getTitel(i);
				Object Obj=Tab.getElementAt(Tab.getPos(),i);
                                if (Obj instanceof String)
                                  Qry.add(sTitel,(String)Obj);
                                else if(Obj instanceof Memo1)
                                  Qry.add(sTitel,((Memo1)Obj).getValue());
                                else if ( Obj !=null)
                                  Qry.add(sTitel,Obj);
			}
			Qry.insert(sTable,false);
                        int iPos=Tab.getPos();
                        if (iPos%1000==0)
                        {
                          t.clockInfo(sTable+" "+Static.rightString(""+iPos,5),lClock);
                          lClock=Static.get_ms();
                        }
			//g.clockInfo(sTable+" "+Static.rightString(Tab.getS("aic_"+sTable),5)/*+":"+Tab.getS("kennung")*/,lClock);
		}
                Qry.free();
		//new Tabellenspeicher(t,"select * from "+sTable/*+" where rowno<100"*/,true).showGrid(sTable+"-Oracle");
		t.clockInfo("Gesamt "+sTable,lClockGes);
                t.diskInfo(sTable+" : "+iAnzahl+": "+(Static.get_ms()-lClockGes)+" ms");
	}

        /*private void TableCopy3(String sTable,int iAb,int iBis)
        {
                long lClockGes=Static.get_ms();
                //t.exec("delete from "+sTable);
                Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable+" where aic_"+sTable+">="+iAb+" and aic_"+sTable+"<="+iBis+" order by aic_"+sTable,true);
                //Tab.showGrid(sTable+"-Sybase");
                int iAnzSpalten=Tab.getAnzahlSpalten();
                SQL Qry =new SQL(t);
                long lClock=Static.get_ms();
                for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                {
                        for(int i=0;i<iAnzSpalten;i++)
                        {
                                String sTitel=Tab.getTitel(i);
                                Object Obj=Tab.getElementAt(Tab.getPos(),i);
                                //if (sTitel.equals("SIZE"))
                                //  sTitel='"'+"SIZE"+'"';
                                if ( Obj !=null)
                                  Qry.add(sTitel,Obj);
                        }
                        Qry.insert(sTable,false);
                        int iPos=Tab.getPos();
                        if (iPos%1000==0)
                        {
                          t.clockInfo(sTable+" "+Static.rightString(""+iPos,5),lClock);
                          lClock=Static.get_ms();
                        }

                }
                Qry.free();
                //new Tabellenspeicher(t,"select * from "+sTable,true).showGrid(sTable+"-Oracle");
                g.clockInfo("Gesamt "+sTable,lClockGes);
                t.diskInfo(sTable+" "+iAb+"-"+iBis+": "+(Static.get_ms()-lClockGes)+" ms");
        }*/

        /*private void TableCopy4(String sTable,int iAb,int iBis,String sSpalte)
        {
                long lClockGes=Static.get_ms();
                //t.exec("delete from "+sTable);
                Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable+" where aic_"+sSpalte+">="+iAb+" and aic_"+sSpalte+"<="+iBis+" order by aic_"+sSpalte,true);
                //Tab.showGrid(sTable+"-Sybase");
                int iAnzSpalten=Tab.getAnzahlSpalten();
                SQL Qry =new SQL(t);
                long lClock=Static.get_ms();
                for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                {
                        for(int i=0;i<iAnzSpalten;i++)
                        {
                                String sTitel=Tab.getTitel(i);
                                Object Obj=Tab.getElementAt(Tab.getPos(),i);
                                //if (sTitel.equals("SIZE"))
                                //  sTitel='"'+"SIZE"+'"';
                                if ( Obj !=null)
                                  Qry.add(sTitel,Obj);
                        }
                        Qry.insert(sTable,false);
                        int iPos=Tab.getPos();
                        if (iPos%1000==0)
                        {
                          t.clockInfo(sTable+" "+Static.rightString(""+iPos,5),lClock);
                          lClock=Static.get_ms();
                        }

                }
                Qry.free();
                //new Tabellenspeicher(t,"select * from "+sTable,true).showGrid(sTable+"-Oracle");
                g.clockInfo("Gesamt "+sTable,lClockGes);
                t.diskInfo(sTable+" "+iAb+"-"+iBis+": "+(Static.get_ms()-lClockGes)+" ms");
        }*/

        /*private void TableCorrect(String sTable)
	{
		long lClockGes=System.currentTimeMillis();
		//t.exec("delete from "+sTable);
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable+" order by aic_"+sTable,true);
		//Tab.showGrid(sTable+"-Sybase");
		int iAnzSpalten=Tab.getAnzahlSpalten();
                SQL Qry =new SQL(t);
		for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			long lClock=System.currentTimeMillis();
			for(int i=1;i<iAnzSpalten;i++)
			{
				String sTitel=Tab.getTitel(i);
				Object Obj=Tab.getElementAt(Tab.getPos(),i);
				//g.fixInfo(sTitel+":"+Obj+"/"+Static.className(Obj));
                                if (sTitel.equals("SIZE"))
                                  sTitel='"'+"SIZE"+'"';
                                // bei folgenden Tabellen nicht:
                                // Bewegungstyp: AIC_LOGGING
                                // Stammtyp: AIC_LOGGING, AIC_EIGENSCHAFT, STA_AIC_STAMMTYP
                                // Begriff: AIC_LOGGING, AIC_MANDANT, IMPORTZEIT
                                if (Obj !=null)
                                  Qry.add(sTitel,Obj);
			}
			Qry.update(sTable,Tab.getI("AIC_"+sTable));
			g.clockInfo(sTable+" "+Static.rightString(Tab.getS("aic_"+sTable),5),lClock);
		}
                Qry.free();
		new Tabellenspeicher(t,"select * from "+sTable,true).showGrid(sTable+"-Oracle");
		g.clockInfo("Gesamt "+sTable,lClockGes);
	}*/

	public void start()
	{
          //new Tabellenspeicher(t,"select * from tabellenname where fixe_sprache=1",true).showGrid("tab");
          /*String s="c:\\abc\\xx\\";
          t.fixInfo(s+":"+Static.StringForMySQL(s));
          new Tabellenspeicher(t,"select * from information_schema.session_variables",true).showGrid("variables");*/
          //t.exec("ALTER SESSION SET nls_date_format = 'YYYY-MM-DD HH24:MI:SS'");
          //g.fixInfo("Sybase:<"+SQL.getString(g,"select betriebssystem from systeminfo")+">");
          //g.fixInfo("Oracle:<"+SQL.getString(t,"select betriebssystem from systeminfo")+">");
          //g.fixInfo('"'+"SIZE"+'"');
          //new Tabellenspeicher(g,"select * from tabellenname",true).showGrid("Tabellenname");
          //new Tabellenspeicher(g,"select version()",true).showGrid("Version");
          //t.exec("delete from tabellenname");
          //TableCopy("DefImport",1);
          /*TableCopy("ANSICHT",1);
          TableCopy("PARAMETER",1);

          TableCopy("Tabellenname",1);
          TableCopy("Komponente",1);
          TableCopy("Begriffgruppe",1);
          TableCopy("Spaltenname",1);
          TableCopy("Zuordnung",1);

          TableCopy("Homepage",1);
          TableCopy("Farbe",1);
          TableCopy("Schriftart",1);
          TableCopy("Schrift",1);
          TableCopy("Sprache",1);

          TableCopy2("Bezeichnung");
          TableCopy2("Daten_Memo");

          TableCopy("Bewegungstyp",1);
          TableCopy("Stammtyp",1);
          TableCopy("Mandant",1);
          TableCopy("Eigenschaft",1);

          TableCopy("Land",1);
          TableCopy("COMPUTER",1);
          TableCopy("SYSTEMINFO",1);
          TableCopy("HAUPTGRUPPE",1);
          TableCopy("NEBENGRUPPE",1);

          TableCopy("Code",1);
          TableCopy("Stamm",1);
          TableCopy("Formular",1);
          TableCopy("Darstellung",1);

          //"ABFRAGE","ABSCHNITT","ANSICHT","BEFEHL","BEGRIFFGRUPPE","BENUTZER","BENUTZERGRUPPE","BEWEGUNGSTYP","CODE","COMPUTER","DARSTELLUNG","DEFIMPORT","EIGENSCHAFT","FARBE",
          //"FEIERTAG","FENSTERPOS","FIXEIGENSCHAFT","FORMULAR","HAUPTGRUPPE","HAUPTSCHIRM","HOMEPAGE","KOMPONENTE","LAND","LOGGING","MANDANT","MODELL","NEBENGRUPPE","PARAMETER","PROTOKOLL","RASTER",
          //"SCHRIFT","SCHRIFTART","SPALTE","SPALTENNAME","SPRACHE","STAMM","STAMMTYP","SYSTEMINFO","TABELLENNAME","ZUORDNUNG"});

          TableCopy("ABFRAGE",1);
          TableCopy("BEDINGUNG",1);

          TableCopy("ABSCHNITT",1);
          TableCopy("BENUTZERGRUPPE",1);
          TableCopy("FEIERTAG",1);
          TableCopy2("Feiertag_zuordnung");
          TableCopy("FENSTERPOS",1);
          TableCopy("HAUPTSCHIRM",1);
          TableCopy("MODELL",1);
          TableCopy("RASTER",1);


    TableCopy("Benutzer",1);
    TableCopy2("Applikation_zuordnung");

    TableCopy("FIXEIGENSCHAFT",1);
    TableCopy("Logging",1);
    TableCopy("SPALTE",1);

    TableCopy2("Begriff_zuordnung");
    TableCopy2("Benutzer_zuordnung");
    TableCopy2("Druck_zuordnung");
    TableCopy2("Eigenschaft_zuordnung");
    TableCopy2("Bew_zuordnung");
    TableCopy2("Gruppe_zuordnung");
    TableCopy2("Lizenz");
    TableCopy2("Planung");
    TableCopy2("Stammtyp_zuordnung");

    TableCopy2("Daten_Bild");

    TableCopy("BEFEHL",1);
    TableCopy2("Stamm_Protokoll");

    TableCopy2("Berechtigung");
    TableCopy2("BEW_Begriff");
    TableCopy2("bew_wert");
    TableCopy2("Daten_Image");
    TableCopy2("Spalte_berechnung");
    TableCopy2("Spalte_zuordnung");
    TableCopy2("zeit_von_bis");

    TableCopy2("Daten_string60");
    TableCopy2("Daten_stringkurz");
    TableCopy2("Daten_stringlang");
    TableCopy2("Daten_stringsehrkurz");
    TableCopy2("Daten_Text");

    TableCopy("BEGRIFF",1);
    TableCopy("ZWISCHENSPEICHER",1);
    TableCopy2("BEW_Boolean");
    TableCopy("Stammpool",1);
    TableCopy("Protokoll",1);*/
    //TableCopy("BEW_POOL",1);
    //TableCopy2("bew_von_bis");
    //TableCopy2("BEW_stamm");
    try
    {
      TableCopy("Rolle",1);
      TableCopy2("Rolle_Zuordnung");
      /*TableCopy2("Modul");
      TableCopy("Druck",1);
      TableCopy("Abschluss",1);
      TableCopy("Abschlusstyp",1);
      TableCopy2("Abschluss_zuordnung");
      TableCopy2("Abschlussdefinition");
      TableCopy("Zeile",1);
      TableCopy("Layout",1);
      TableCopy("Aserver",1);*/
    /*TableCopy3("Protokoll",99930,200000);
    TableCopy("Protokoll",200001);
    TableCopy3("BEW_POOL",1,99997);
    TableCopy3("BEW_POOL",99998,200000);
    TableCopy3("BEW_POOL",200001,300000);
    TableCopy3("BEW_POOL",300001,400000);
    TableCopy3("BEW_POOL",400001,500000);
    TableCopy3("BEW_POOL",500001,600000);
    TableCopy("BEW_POOL",600001);*/
  /*TableCopy4("bew_von_bis",1,100000,"BEW_POOL");
  TableCopy4("bew_von_bis",100001,200000,"BEW_POOL");
  TableCopy4("bew_von_bis",200001,300000,"BEW_POOL");
  TableCopy4("bew_von_bis",300001,400000,"BEW_POOL");
  TableCopy4("bew_von_bis",400001,500000,"BEW_POOL");
  TableCopy4("bew_von_bis",500001,700000,"BEW_POOL");
  TableCopy4("bew_von_bis",700001,900000,"BEW_POOL");
  TableCopy4("BEW_stamm",1,100000,"BEW_POOL");
  TableCopy4("BEW_stamm",100001,200000,"BEW_POOL");
  TableCopy4("BEW_stamm",200001,300000,"BEW_POOL");
  TableCopy4("BEW_stamm",300001,400000,"BEW_POOL");
  TableCopy4("BEW_stamm",400001,500000,"BEW_POOL");
  TableCopy4("BEW_stamm",500001,700000,"BEW_POOL");
  TableCopy4("BEW_stamm",700001,900000,"BEW_POOL");*/
    }
    catch(Exception e)
    {
        Static.printError("Fehler "+e);
        Static.sleep(100);
        Static.printError("Fehler2");
        Static.sleep(100);
        Static.printError("Fehler3");
        Static.sleep(100);
        Static.printError("Fehler4");
        Static.sleep(100);
        Static.printError("Fehler5");
    }


    //TableCopy2("bew_von_bis");
    //TableCopy2("BEW_stamm");

//TableCopy3("Stammpool",1,50000);
    //TableCopy3("Stammpool",50001,100000);
    //TableCopy3("Stammpool",100001,150000);
    //TableCopy("Stammpool",150001);

    //TableCopy("Daten",1);
    //TableCopy("Verlauf",1);

    /*SQL Qry=new SQL(t);
    Qry.createAllSequences();
    Qry.free();*/

             //new Tabellenspeicher(g,"select * from defimport",true).showGrid("MS");
             //new Tabellenspeicher(t,"select * from tabellenname order by aic_tabellenname",true).showGrid("MS-SQL");
             //new Tabellenspeicher(t,"select * from test",true).showGrid("test");

		t.disconnect();
                Static.printError("fertig");
                /*Static.sleep(500);
                Static.printError("fertig2");*/
                //Static.sleep(500);
                //Static.printError("fertig3");
		//g.disconnect();
		//g.clockInfo("fertig",lClock);
	}
    // add your data members here
	//long lClock=System.currentTimeMillis();
	Transact t=new Transact();
	Global g;
}

