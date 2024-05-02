
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Memo1;

public class Sybase_To_Oracle
{
  public static void main( String[] args )
    {
      new Sybase_To_Oracle();
    }

    public Sybase_To_Oracle()
    {
		Static.DirError = "R:\\K2\\";
		Static.DirImageSys = "http://192.168.1.2/Images/";
		//g = new Global("192.1.1.237:2650");

		g = new Global("192.168.1.105:2669");
                //g = new Global("Roland2:2669");
		//g.ComputerErmitteln();
		t.connect(".my:192.168.1.11/dvh_test");
                //t.connect("//192.168.1.104:1433;databaseName=Renner");
                Transact.iInfos=128;
                start();
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
		long lClockGes=System.currentTimeMillis();
		t.exec("delete from "+sTable);
                //t.exec("set IDENTITY_INSERT "+sTable+" ON");
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable+(iAb>1?" where aic_"+sTable+">="+iAb:"")+" order by aic_"+sTable,true);
		//Tab.showGrid(sTable+"-Sybase");
		int iAnzSpalten=Tab.getAnzahlSpalten();
                t.diskInfo(sTable+" holen\t"+(System.currentTimeMillis()-lClockGes)+"\t"+Tab.getAnzahlElemente(null)+
                           "\t"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
                SQL Qry =new SQL(t);
                long lClock=System.currentTimeMillis();
		for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			for(int i=0;i<iAnzSpalten;i++)
			{
				String sTitel=Tab.getTitel(i);
				Object Obj=Tab.getElementAt(Tab.getPos(),i);
				//g.fixInfo(sTitel+":"+Obj+"/"+Static.className(Obj));
                                //if (sTitel.equals("SIZE")) // Auch bei MySQL nicht mehr nötig
                                //  sTitel='"'+"SIZE"+'"';
                                if (sTitel.toUpperCase().startsWith("INT"))
                                  sTitel='`'+sTitel+'`';
                                // bei folgenden Tabellen nicht:
                                // Bewegungstyp: AIC_LOGGING
                                // Stammtyp: AIC_LOGGING, AIC_EIGENSCHAFT, STA_AIC_STAMMTYP
                                // Begriff: AIC_LOGGING, AIC_MANDANT, IMPORTZEIT
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
                          lClock=System.currentTimeMillis();
                        }
			//g.clockInfo(sTable+" "+Static.rightString(Tab.getS("aic_"+sTable),5)/*+":"+Tab.getS("kennung")*/,lClock);
		}
                Qry.free();
		//new Tabellenspeicher(t,"select * from "+sTable/*+" where rowno<100"*/,true).showGrid(sTable+"-MS");
		t.clockInfo("Gesamt "+sTable,lClockGes);
                //t.exec("set IDENTITY_INSERT "+sTable+" OFF");
                t.diskInfo(sTable+"\t"+(System.currentTimeMillis()-lClockGes)+"\t"+Tab.getAnzahlElemente(null)+
                           "\t"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
                Tab.clearAll();
                Tab=null;
	}

        /*private void TableCopyb(String sTable,int iAb)
        {
          long lClockGes = System.currentTimeMillis();
          //t.exec("delete from "+sTable);
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable+" where aic_"+sTable+">="+iAb+" order by aic_"+sTable,true);
          Tab.moveNext();
          t.clockInfo("Gesamt " + sTable, lClockGes);
          t.diskInfo("Teste "+sTable + ": " + (System.currentTimeMillis() - lClockGes) + " ms\tAnzahl="+Tab.getAnzahlElemente(null)
              +"\tSpeicher="+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
          Tab.moveLast();
          t.clockInfo("Last   " + sTable+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()), lClockGes);
          Tab.clearAll();
          Tab=null;
          t.clockInfo("Clear  " + sTable, lClockGes);
        }*/

        /*private void TableCopy2b(String sTable)
        {
          long lClockGes = System.currentTimeMillis();
          //t.exec("delete from "+sTable);
          Tabellenspeicher Tab = new Tabellenspeicher(g, "select * from " + sTable, true);
          Tab.moveNext();
          t.clockInfo("Gesamt " + sTable, lClockGes);
          t.diskInfo("Teste "+sTable + ": " + (System.currentTimeMillis() - lClockGes) + " ms\tAnzahl="+Tab.getAnzahlElemente(null)
              +"\tSpeicher="+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
          Tab.clearAll();
          Tab=null;
          t.clockInfo("Clear  " + sTable, lClockGes);
        }*/

        private void TableCopy2(String sTable)
	{
		long lClockGes=System.currentTimeMillis();
		t.exec("delete from "+sTable);
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable,true);
		//Tab.showGrid(sTable+"-Sybase");
		int iAnzSpalten=Tab.getAnzahlSpalten();
                t.diskInfo(sTable+" holen\t"+(System.currentTimeMillis()-lClockGes)+"\t"+Tab.getAnzahlElemente(null)+
                           "\t"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
                SQL Qry =new SQL(t);
                long lClock=System.currentTimeMillis();
		for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			for(int i=0;i<iAnzSpalten;i++)
			{
				String sTitel=Tab.getTitel(i);
				Object Obj=Tab.getElementAt(Tab.getPos(),i);
                                if (Obj instanceof String)
                                  Qry.add(sTitel,(String)Obj);
                                else if (Obj instanceof Memo1)
                                  Qry.add(sTitel,((Memo1)Obj).getValue());
                                else if ( Obj !=null)
                                  Qry.add(sTitel,Obj);
			}
			Qry.insert(sTable,false);
                        int iPos=Tab.getPos();
                        if (iPos%1000==0)
                        {
                          t.clockInfo(sTable+" "+Static.rightString(""+iPos,5),lClock);
                          lClock=System.currentTimeMillis();
                        }
			//g.clockInfo(sTable+" "+Static.rightString(Tab.getS("aic_"+sTable),5)/*+":"+Tab.getS("kennung")*/,lClock);
		}
                Qry.free();
		//new Tabellenspeicher(t,"select * from "+sTable/*+" where rowno<100"*/,true).showGrid(sTable+"-Oracle");
		t.clockInfo("Gesamt "+sTable,lClockGes);
                t.diskInfo(sTable+"\t"+(System.currentTimeMillis()-lClockGes)+"\t"+Tab.getAnzahlElemente(null)+
                           "\t"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
                Tab.clearAll();
                Tab=null;
	}

        private void TableCopy3(String sTable,int iAb,int iBis)
        {
                long lClockGes=System.currentTimeMillis();
                //t.exec("delete from "+sTable);
                //t.exec("set IDENTITY_INSERT "+sTable+" ON");
                Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable+" where aic_"+sTable+">="+iAb+" and aic_"+sTable+"<="+iBis+" order by aic_"+sTable,true);
                t.diskInfo(sTable+"-"+iBis+"\t"+(System.currentTimeMillis()-lClockGes)+"\t"+Tab.size()+
                           "\t"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
                //Tab.showGrid(sTable+"-Sybase");
                int iAnzSpalten=Tab.getAnzahlSpalten();
                SQL Qry =new SQL(t);
                long lClock=System.currentTimeMillis();
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
                          lClock=System.currentTimeMillis();
                        }
                        //g.clockInfo(sTable+" "+Static.rightString(Tab.getS("aic_"+sTable),5)/*+":"+Tab.getS("kennung")*/,lClock);
                }
                Qry.free();
                //new Tabellenspeicher(t,"select * from "+sTable,true).showGrid(sTable+"-Oracle");
                g.clockInfo("Gesamt "+sTable,lClockGes);
                //t.exec("set IDENTITY_INSERT "+sTable+" OFF");
                t.diskInfo(sTable+" "+iAb+"-"+iBis+"\t"+(System.currentTimeMillis()-lClockGes)+"\t"+Tab.getAnzahlElemente(null)+
                           "\t"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
        }

        private void TableCopy4(String sTable,int iAb,int iBis,String sSpalte)
        {
                long lClockGes=System.currentTimeMillis();
                //t.exec("delete from "+sTable);
                Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTable+" where aic_"+sSpalte+">="+iAb+" and aic_"+sSpalte+"<="+iBis+" order by aic_"+sSpalte,true);
                //Tab.showGrid(sTable+"-Sybase");
                int iAnzSpalten=Tab.getAnzahlSpalten();
                SQL Qry =new SQL(t);
                long lClock=System.currentTimeMillis();
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
                        if (iPos%100==0)
                        {
                          t.clockInfo(sTable+" "+Static.rightString(""+iPos,5),lClock);
                          lClock=System.currentTimeMillis();
                        }
                        //g.clockInfo(sTable+" "+Static.rightString(Tab.getS("aic_"+sTable),5)/*+":"+Tab.getS("kennung")*/,lClock);
                }
                Qry.free();
                //new Tabellenspeicher(t,"select * from "+sTable,true).showGrid(sTable+"-Oracle");
                g.clockInfo("Gesamt "+sTable,lClockGes);
                t.diskInfo(sTable+" "+iAb+"-"+iBis+": "+(System.currentTimeMillis()-lClockGes)+" ms");
        }

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
          //t.exec("ALTER SESSION SET nls_date_format = 'YYYY-MM-DD HH24:MI:SS'");
          //g.fixInfo("Sybase:<"+SQL.getString(g,"select betriebssystem from systeminfo")+">");
          //g.fixInfo("Oracle:<"+SQL.getString(t,"select betriebssystem from systeminfo")+">");
          //g.fixInfo('"'+"SIZE"+'"');

          /*TableCopy("DefImport",1);
          new Tabellenspeicher(t,"select * from DefImport",true).showGrid("MySQL");
          new Tabellenspeicher(g,"select * from DefImport",true).showGrid("ASA");*/
          /*TableCopy("Tabellenname",1);
          TableCopy("Komponente",1);
          TableCopy("Begriffgruppe",1);
          TableCopy("Spaltenname",1);
          TableCopy("Zuordnung",1);

          TableCopy("Farbe",1);
          TableCopy("Schriftart",1);
          TableCopy("Sprache",1);

          TableCopy2("Bezeichnung");
          TableCopy2("Daten_Memo");
          TableCopy("BEGRIFF",1);
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
          TableCopy("PARAMETER",1);

          //"ABFRAGE","ABSCHNITT","ANSICHT","BEFEHL","BEGRIFFGRUPPE","BENUTZER","BENUTZERGRUPPE","BEWEGUNGSTYP","CODE","COMPUTER","DARSTELLUNG","DEFIMPORT","EIGENSCHAFT","FARBE",
          //"FEIERTAG","FENSTERPOS","FIXEIGENSCHAFT","FORMULAR","HAUPTGRUPPE","HAUPTSCHIRM","HOMEPAGE","KOMPONENTE","LAND","LOGGING","MANDANT","MODELL","NEBENGRUPPE","PARAMETER","PROTOKOLL","RASTER",
          //"SCHRIFT","SCHRIFTART","SPALTE","SPALTENNAME","SPRACHE","STAMM","STAMMTYP","SYSTEMINFO","TABELLENNAME","ZUORDNUNG"});

          TableCopy("ABFRAGE",1);
          TableCopy("BEDINGUNG",1);

          TableCopy("ABSCHNITT",1);
          TableCopy("ANSICHT",1);
          TableCopy("BENUTZERGRUPPE",1);
          TableCopy("FEIERTAG",1);
          TableCopy2("Feiertag_zuordnung");
          TableCopy("FENSTERPOS",1);
          TableCopy("HAUPTSCHIRM",1);
          TableCopy("MODELL",1);
          TableCopy("RASTER",1);

    TableCopy("BEFEHL",1);

    TableCopy("Benutzer",1);
    TableCopy2("Applikation_zuordnung");

    TableCopy("FIXEIGENSCHAFT",1);
    TableCopy("SPALTE",1);
    TableCopy("SPALTEN2",1);

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
    TableCopy2("Stamm_Protokoll");

    TableCopy2("Berechtigung");
    TableCopy2("Daten_Image");
    //TableCopy2("Daten_stringlang");
    TableCopy2("Spalte_berechnung");
    TableCopy2("Spalte_zuordnung");
    TableCopy2("zeit_von_bis");
    TableCopy("abschluss",1);
    TableCopy2("abschlussdefinition");
    TableCopy("abschlusstyp",1);
    TableCopy2("abschluss_zuordnung");
    TableCopy2("bew_aic");
    TableCopy("druck",1);
    TableCopy("drucker",1);
    TableCopy("zeile",1);
    TableCopy("ww",1);
    TableCopy("layout",1);

  //neu
    TableCopy("ASERVER",1);
    TableCopy2("MODUL");
    TableCopy("ROLLE",1);
    TableCopy2("ROLLE_ZUORDNUNG");
    TableCopy("VERLAUF",1);

    TableCopy("DefVerlauf",1);
    TableCopy("Fehler",1);
    TableCopy2("Tooltip");
    TableCopy("Schrift",1);*/

  try
    {
      //TableCopy2("Daten_Text");
      /*TableCopy("ZWISCHENSPEICHER",1);
      TableCopy2("BEW_Begriff");
      TableCopy("Logging",1);
      TableCopy2("Daten_stringx");
      TableCopy2("BEW_Boolean");
      TableCopy2("bew_wert");
      TableCopy("DATEN",1);
      TableCopy("BEW_POOL",1);
      TableCopy2("bew_von_bis");
      TableCopy("Protokoll",1);
      TableCopy("Stammpool",1);
      TableCopy2("BEW_stamm");*/
      //TableCopy3("BEW_POOL",1,2000000);
      //TableCopy3("BEW_POOL",2000001,4000000);
      //TableCopy3("BEW_POOL",4000001,6000000);
      //TableCopy3("BEW_POOL",6000001,8000000);
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


    /*SQL Qry=new SQL(t);
    Qry.createAllSequences();
    Qry.free();*/

             //new Tabellenspeicher(g,"select * from defimport",true).showGrid("MS");
             //new Tabellenspeicher(t,"select * from tabellenname order by aic_tabellenname",true).showGrid("MS-SQL");
             //new Tabellenspeicher(t,"select * from test",true).showGrid("test");

		t.disconnect();
                Static.printError("fertig");
                Static.sleep(300);
                Static.printError("fertig2");
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

