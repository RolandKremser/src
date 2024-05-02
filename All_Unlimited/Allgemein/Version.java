/*
    All_Unlimited-Allgemein-Version.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import All_Unlimited.Grunddefinition.Reinigung;
import All_Unlimited.Hauptmaske.Calc;

public class Version extends java.lang.Object
{
  // add your data members here
	private static int iHaupt = 5;
	private static int iNeben = 18;
	private static int iProg  = 0;
	private static int iSub	  = 11;
	private static GregorianCalendar dt = new GregorianCalendar(2024,Calendar.APRIL,26); //TODO Datum anpassen
  private static boolean bTest=true;

	private int iSoll;
	private int iIst;
	private boolean bOK = false;
	private Parameter p;
	public String sStatus = "";
	Global g;

	public Version(Global rg)
	{
		g = rg;
		iSoll = Ver(iHaupt,iNeben,iProg);
		p = new Parameter(g,"Datenbank");
		if(iProg > 0 && !g.TestPC())
		{
                  if (Static.bX11)
                    JOptionPane.showMessageDialog(new JFrame(),"All Unlimited für nicht prog-Datenbank gesperrt\nBitte alte Version aus n:\\Versionen kopieren!","Sicherheits-Ende",JOptionPane.ERROR_MESSAGE);
			System.exit(17);
		}
                if (Static.bNotfall)
                {
                  bOK = true;
                  sStatus = "Notfall-> Version ignorieren";
                }
                else
                  checkVersion();

		p.free();
	}

        public static boolean Test()
        {
          return bTest || iProg != 0;
        }

        private void waitUpdate()
        {
          //g.testInfo("waitUpdate");
          Gauge gauge=new Gauge(0,100,"Update wird durchgeführt",g,false);
          int i=0;
          Static.sleep(2000);
          while(SQL.exists(g,"select aic_parameter from parameter where aic_parameter="+p.iAic))
          {
            Static.sleep(2000);
            i++;
            gauge.setText("warte ...",i);
          }
          bOK=true;
          gauge.setText("fertig",gauge.getMax());
          //checkVersion();
        }

        private boolean checkUpdate()
        {
          //g.testInfo("checkUpdate");
          if (p.int4 >0)
          {
            waitUpdate();
            return false;
          }
          else
          {
            p.exec("update parameter set "+g.int4()+"="+g.Sid()+" where aic_parameter="+p.iAic);
            Static.sleep(1000);
            if (p.getInteger("select "+g.int4()+" from parameter where aic_parameter="+p.iAic)==g.Sid())
              return true;
            else
            {
              waitUpdate();
              return false;
            }
          }
        }

        public static boolean UpdateNoetig(Global g)
        {
          int iSoll = Ver(iHaupt,iNeben,iProg);
          Parameter p = new Parameter(g,"Datenbank");
          p.getParameter("Version",false,false);
          int iIst = Ver(p.int1,p.int2,p.int3);
          return iSoll > iIst;
        }

	private void checkVersion()
	{
		p.getParameter("Version",false,false);
		iIst = Ver(p.int1,p.int2,p.int3);
		if (iSoll == iIst)
		{
			bOK = true;
			sStatus = "Version ident";
		}
		else if (/*Static.bX11 &&*/ iSoll > iIst)
		{
			String s=getVersion(p.int1,p.int2,p.int3,0);
                        if (Static.bX11)
                        {
                          int pane = JOptionPane.showConfirmDialog(new JFrame(),"Es wird ein Update von " + getS(iIst) + " auf " + getS(iSoll) +
                              " durchgeführt!\nHaben Sie die Datenbank gesichert?", "Sicherheitsabfrage",JOptionPane.YES_NO_OPTION);
                          if (pane == JOptionPane.NO_OPTION)
                            System.exit(18);
                        }
                        //g.start();
                        if (checkUpdate())
                        {
			//p.exec("update logging set aus=mom where aus is null and seconds(mom,now())>900");
                        //g.checkConnects(true);
			int i=p.getInteger("select count(*) from logging where aus is null");
			bOK = i==0;
			if (bOK)
			{
                          if (Static.cache())
                            Static.clearCache();
                          bOK = UpdateVersion();
                          sStatus = bOK ? "Version upgedatet von "+s+"->"+getVersion():"Fehler nach Updaten von "+getVersion(iIst/1000,(iIst%1000)/10,iIst%10,0);
                          /*if (g.MS() && bOK)
                          {
                            bOK = false;
                            //g.commit();
                            JOptionPane.showMessageDialog(new JFrame(),"Update durchgeführt.\nUm weiterzuarbeiten bitte Datenbank neu starten.\nBrowser wird geschlossen.","MS-SQL",JOptionPane.WARNING_MESSAGE);
                            System.exit(0);
                          }*/
			}
			else
				sStatus = "Update nicht möglich da noch "+(i==1?"eine Person eingeloggt ist":i+" Leute eingeloggt sind")+"!";
                        }
                        else
                          sStatus = "Update wurde bereits durchgeführt";
                        //if (bOK) g.commit(); else g.rollback();
			g.fixInfo("checkVersion: "+sStatus);
		}
		else
		{
			bOK = false;
			//sStatus = "Version zu hoch";
                        sStatus = "Version "+(Static.bX11 ? "ab ":"")+getVersion(p.int1,p.int2,p.int3,0)+" nötig!";
		}
		if (!bOK)
		{
			Static.printError("Version.checkVersion(): "+sStatus);
                        if (Static.bX11)
                          JOptionPane.showMessageDialog(new JFrame(),sStatus,"Fehler",JOptionPane.ERROR_MESSAGE);
		}
		//bOK = true;
//		if (!Transact.bVonInt && bOK)
//		{
//			Transact.bVonInt=bOK;
//			g.setVonBis(g.getVon(), g.getBis(),true);
//		}
	}

	private int addBegriff(int riGruppe,String sKennung)
	{
		int iBeg=SQL.getInteger(g,"select aic_begriff from begriff where aic_begriffgruppe="+riGruppe+" and kennung='"+sKennung+"'");
		if (iBeg>0)
			return iBeg;
		p.add("AIC_Begriffgruppe",riGruppe);
		p.add("Kennung",sKennung);
		return p.insert("Begriff",true);
	}

	private int addCode(int riGruppe,String sKennung)
	{
		if (riGruppe>0)
		{
                  int iAic=p.getInteger("select aic_code from code where AIC_Begriffgruppe="+riGruppe+" and kennung='"+sKennung+"'");
                  if (iAic>0)
                    return iAic;
                  else
                  {
                    p.add("AIC_Begriffgruppe", riGruppe);
                    p.add("Kennung", sKennung);
                    return p.insert("Code", true);
                  }
		}
		else
		{
			Static.printError("Version.addCode(): "+sKennung+" kann nicht angelegt werden!");
			return -1;
		}
	}

	/*private boolean KennungTrimmen(String sTabelle,int iAnzahl)
	{
		boolean b= true;
		System.err.println(sTabelle+" trimmen!");
		b=p.exec("alter table "+sTabelle+" add KENNUNG2 char("+iAnzahl+") null");
		b=b && p.exec("update "+sTabelle+" set kennung2=kennung");
		b=b && p.exec("alter table "+sTabelle+" drop KENNUNG");
		b=b && p.exec("alter table "+sTabelle+" rename KENNUNG2 to KENNUNG");

		System.err.println(sTabelle+(b?" wurde getrimmt.":" konnte nicht getrimmt werden !!!"));
		return b;
	}*/

	private boolean UpdateVersion()
	{
		boolean b = true;
		if(iIst < Ver(4,4,0))
		{
			Static.printError("Version viel zu niedrig!");
			JOptionPane.showMessageDialog(new JFrame(),"Bitte vorher mit Verion 4.4 - 5.17.11 updaten!","Version viel zu niedrig",JOptionPane.ERROR_MESSAGE);
			System.exit(19);
		}
		/*
		if(b && iIst < Ver(1,17,1))	// 30.4.2001
		{
			g.fixInfo("Versionsupdate 1.17.1");
			g.fixInfo("- neue Befehle");
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'"),"clear")>0;
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'"),"date is null")>0;
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'");
			if (b && iGruppe>0)
			{
				addCode(iGruppe,"mul 2");
				addCode(iGruppe,"div 2");
			}
			else
				b = false;

			g.fixInfo("- neue Datentypen");
			iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			if (b && iGruppe>0)
			{
				addBegriff(iGruppe,"BewDatumPlus");
				addBegriff(iGruppe,"BewVonBisPlus");
				addBegriff(iGruppe,"BewDatumAuto");
				addBegriff(iGruppe,"BewComputer");
				addBegriff(iGruppe,"BewBenutzer");
			}
			else
				b = false;

			g.fixInfo("- neue Begriffgruppen");
			p.clear();
			p.add("Kennung","Radiobutton");
			b=b && p.insert("begriffgruppe",true)>0;
			p.clear();
			p.add("Kennung","Grafik");
			b=b && p.insert("begriffgruppe",true)>0;

			g.fixInfo("- Tabellenänderungen");
			b=b && p.exec("alter table ABFRAGE ADD AUTOREFRESH integer null");
			b=b && p.exec("alter table FORMULAR ADD BITS integer null");
			b=b && p.exec("alter table STAMM_PROTOKOLL MODIFY BEZEICHNUNG char(50)");
			b=b && p.exec("alter table STAMMPOOL add foreign key FK_STAMMPOO_ERF_POOL__BEW_POOL (AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL) on update restrict on delete restrict");

			iIst = b ? Ver(1,17,1):iIst;
		}
		if(b && iIst < Ver(1,17,2))	// 2.5.2001
		{
			g.fixInfo("Versionsupdate 1.17.2");

			g.fixInfo("- neue Befehle");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			if (b && iGruppe>0)
			{
				addCode(iGruppe,"read data");
				addCode(iGruppe,"data deleted");
			}
			else
				b = false;
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'"),"saveorders")>0;

			g.fixInfo("- neue Grafiken");
			iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Grafik'");
			if (b && iGruppe>0)
			{
				addBegriff(iGruppe,"Linien");
				addBegriff(iGruppe,"Balken");
				addBegriff(iGruppe,"Torten");
				addBegriff(iGruppe,"Ring");
			}
			else
				b = false;

			g.fixInfo("- Tabellenänderungen");
			b=b && p.exec("alter table ABFRAGE ADD FORMAT char(20) null");

			b=b && p.exec("create table BEW_AIC("+
    			"AIC_BEW_POOL     integer               not null,"+
    			"AIC_EIGENSCHAFT  integer               not null,"+
    			"AIC              integer                   null,"+
    			"primary key (AIC_BEW_POOL, AIC_EIGENSCHAFT))");
			b=b && p.exec("alter table BEW_AIC add foreign key FK_BEW_AIC_RELATION__EIGENSCH (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
			b=b && p.exec("alter table BEW_AIC add foreign key FK_BEW_AIC_RELATION__BEW_POOL (AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL) on update restrict on delete restrict");

			b=b && p.exec("drop index stamm_pool_pk");

			iIst = b ? Ver(1,17,2):iIst;
		}

		if(b && iIst < Ver(1,17,3))	// 2.5.2001
		{
			g.fixInfo("Versionsupdate 1.17.3");
			b=b && p.exec("alter table ABFRAGE drop FORMAT");
			b=b && p.exec("alter table SPALTE ADD FORMAT char(20) null");
			iIst = b ? Ver(1,17,3):iIst;
		}
		if(b && iIst < Ver(1,17,4))	// 2.5.2001
		{
			g.fixInfo("Versionsupdate 1.17.4");
			b=b && p.exec("update code set kennung='read file' where kennung='read data'");
			b=b && p.exec("update code set kennung='file exists' where kennung='data deleted'");
			b=b && p.exec("delete from code where kennung='saveorders'");
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'"),"write file")>0;

			iIst = b ? Ver(1,17,4):iIst;
		}
		if(b && iIst < Ver(1,17,5))	// 3.5.2001
		{
			g.fixInfo("Versionsupdate 1.17.5");

			g.fixInfo("- neue Befehle");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			if (b && iGruppe>0)
			{
				addCode(iGruppe,"pos String");
				addCode(iGruppe,"pos next String");
			}
			else
				b = false;

			iIst = b ? Ver(1,17,5):iIst;
		}
		if(b && iIst < Ver(1,17,6))	// 3.5.2001
		{
			g.fixInfo("Versionsupdate 1.17.6");

			g.fixInfo("- neue Befehle");
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'"),"concat")>0;
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'"),"String to date")>0;

			iIst = b ? Ver(1,17,6):iIst;
		}
		if(b && iIst < Ver(1,18,1))	// 15.5.2001
		{
			g.fixInfo("Versionsupdate 1.18.1");

			g.fixInfo("- neue Befehle");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"init TRUE")>0;
				b=b && addCode(iGruppe,"init FALSE")>0;
				b=b && addCode(iGruppe,"init String")>0;
			}
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"not")>0;
				b=b && addCode(iGruppe,"round")>0;
				b=b && addCode(iGruppe,"floor")>0;
				b=b && addCode(iGruppe,"ceil")>0;
			}
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"move next real aic")>0;
			}
			iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"eof real aic")>0;
				b=b && addCode(iGruppe,"is first weekday")>0;
				b=b && addCode(iGruppe,"is last weekday")>0;
			}
			iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"get weekday")>0;
				b=b && addCode(iGruppe,"set first weekday")>0;
				b=b && addCode(iGruppe,"set last weekday")>0;
			}

			g.fixInfo("- neue Datentypen");
			iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			if (b && iGruppe>0)
			{
				addBegriff(iGruppe,"Anlage");
				addBegriff(iGruppe,"Mandant");
			}

			iIst = b ? Ver(1,18,1):iIst;
		}
		if(b && iIst < Ver(1,18,2))	// 15.5.2001
		{
			g.fixInfo("Versionsupdate 1.18.2");
			b=b && p.exec("alter table ABFRAGE ADD BITS integer null");
			b=b && p.exec("update ABFRAGE set kein_zeitraum=0 where kein_zeitraum is null");
			b=b && p.exec("update ABFRAGE set kein_stamm=0 where kein_stamm is null");
			b=b && p.exec("update ABFRAGE set summe=0 where summe is null");
			b=b && p.exec("update ABFRAGE set kein_refresh=0 where kein_refresh is null");
			b=b && p.exec("update ABFRAGE set nur_modell=0 where nur_modell is null");
			b=b && p.exec("update ABFRAGE set exportierbar=0 where exportierbar is null");
			b=b && p.exec("update ABFRAGE set BITS=kein_zeitraum+kein_stamm*2+summe*4+kein_refresh*8+nur_modell*16+exportierbar*96");
			b=b && p.exec("alter table ABFRAGE DROP kein_zeitraum");
			b=b && p.exec("alter table ABFRAGE DROP kein_stamm");
			b=b && p.exec("alter table ABFRAGE DROP summe");
			b=b && p.exec("alter table ABFRAGE DROP kein_refresh");
			b=b && p.exec("alter table ABFRAGE DROP nur_modell");
			b=b && p.exec("alter table ABFRAGE DROP exportierbar");

			b=b && p.exec("alter table SPALTE ADD BITS integer null");
			b=b && p.exec("update SPALTE set Summe=0 where Summe is null");
			b=b && p.exec("update SPALTE set Gruppiert=0 where Gruppiert is null");
			b=b && p.exec("update SPALTE set Sortdesc=0 where Sortdesc is null");
			b=b && p.exec("update SPALTE set Anzeigen=0 where Anzeigen is null");
			b=b && p.exec("update SPALTE set Editierbar=0 where Editierbar is null");
			b=b && p.exec("update SPALTE set BITS=Gruppiert+Sortdesc*2+Summe*4+Anzeigen*8+editierbar*16");
			b=b && p.exec("alter table SPALTE DROP Summe");
			b=b && p.exec("alter table SPALTE DROP Gruppiert");
			b=b && p.exec("alter table SPALTE DROP Sortdesc");
			b=b && p.exec("alter table SPALTE DROP Anzeigen");
			b=b && p.exec("alter table SPALTE DROP Editierbar");

			iIst = b ? Ver(1,18,2):iIst;
		}

		if(b && iIst < Ver(1,18,3))	// 16.5.2001
		{
			g.fixInfo("Versionsupdate 1.18.3");
			//b=b && p.exec("update code set aic_begriffgruppe=(select aic_begriffgruppe from begriffgruppe where kennung='init') where kennung='change Aic'");
			iIst = b ? Ver(1,18,3):iIst;
		}

		if(b && iIst < Ver(1,18,4))	// 17.5.2001
		{
			g.fixInfo("Versionsupdate 1.18.4");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"change Aic")>0;
			}
			iIst = b ? Ver(1,18,4):iIst;
		}

		if(b && iIst < Ver(1,19,1))	// 11.6.2001
		{
			g.fixInfo("Versionsupdate 1.19.1");

			g.fixInfo("Druck-Tabellen erstellen");
			b=b && p.exec("create table DRUCK("+
				"AIC_DRUCK        integer               not null default AUTOINCREMENT,"+
				"AIC_STAMMTYP     integer               not null,"+
				"KENNUNG          char(20)                  	,"+
				"primary key (AIC_DRUCK))");

			b=b && p.exec("create table ABSCHNITT("+
				"AIC_ABSCHNITT    integer               not null default AUTOINCREMENT,"+
				"AIC_SCHRIFT      integer                   null,"+
				"SCH_AIC_SCHRIFT  integer                   null,"+
				"AIC_BEGRIFF      integer                   null,"+
				"KENNUNG          char(20)                  	,"+
				"BITS             integer                       ,"+
				"primary key (AIC_ABSCHNITT))");

			b=b && p.exec("create table DRUCK_ZUORDNUNG("+
				"AIC_DRUCK        integer               not null,"+
				"AIC_ABSCHNITT    integer               not null,"+
				"REIHENFOLGE      integer                       ,"+
				"primary key (AIC_DRUCK, AIC_ABSCHNITT))");

			b=b && p.exec("alter table DRUCK add foreign key FK_DRUCK_STT_DES_D_STAMMTYP (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP) on update restrict on delete restrict");
			b=b && p.exec("alter table ABSCHNITT add foreign key FK_ABSCHNIT_SCHRIFT_TITEL (AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
			b=b && p.exec("alter table ABSCHNITT add foreign key FK_ABSCHNIT_SCHRIFT_TEXT (SCH_AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
			b=b && p.exec("alter table ABSCHNITT add foreign key FK_ABSCHNIT_RELATION__BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");
			b=b && p.exec("alter table DRUCK_ZUORDNUNG add foreign key FK_DRUCK_ZU_ABSCHNITT_ABSCHNIT (AIC_ABSCHNITT) references ABSCHNITT (AIC_ABSCHNITT) on update restrict on delete restrict");
			b=b && p.exec("alter table DRUCK_ZUORDNUNG add foreign key FK_DRUCK_ZU_DRUCK_DES_DRUCK (AIC_DRUCK) references DRUCK (AIC_DRUCK) on update restrict on delete restrict");

			b=b && p.exec("insert into tabellenname(kennung) values ('DRUCK')");
			b=b && p.exec("insert into tabellenname(kennung) values ('ABSCHNITT')");

			g.fixInfo("- neue Grafiken");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Grafik'");
			if (b && iGruppe>0)
			{
				addBegriff(iGruppe,"Pyramide");
				addBegriff(iGruppe,"Band");
				addBegriff(iGruppe,"Zylinder");
				addBegriff(iGruppe,"Cone");
				addBegriff(iGruppe,"sBalken");
				addBegriff(iGruppe,"sZylinder");
			}
			else
				b = false;

			iIst = b ? Ver(1,19,1):iIst;
		}

		if(b && iIst < Ver(1,19,2))	// 11.6.2001
		{
			g.fixInfo("Versionsupdate 1.19.2");
			b=b && p.exec("update code set kennung='init act_date' where kennung='act_date to date'");
			b=b && p.exec("update code set kennung='get String-Date' where kennung='String to date'");
			b=b && p.exec("update code set kennung='clear time' where kennung='convert date'");

			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			if (b && iGruppe>0)
			{
				b=b && p.exec("update code set aic_begriffgruppe="+iGruppe+" where kennung='get String-Date'");
				b=b && addCode(iGruppe,"get von-bis-String")>0;
				b=b && addCode(iGruppe,"get Date-String")>0;
				b=b && addCode(iGruppe,"push String")>0;
				b=b && addCode(iGruppe,"pop String")>0;
				b=b && addCode(iGruppe,"push aic")>0;
				b=b && addCode(iGruppe,"pop aic")>0;
			}
			else
				b = false;

			b=b && p.exec("update code set aic_begriffgruppe=(select aic_begriffgruppe from begriffgruppe where kennung='Anweisung') where kennung in ('delete all','delete row','destroy row','destroy all')");
			b=b && p.exec("update code set aic_begriffgruppe=(select aic_begriffgruppe from begriffgruppe where kennung='init') where kennung = 'move next real aic'");
			p.clear();
			p.add("Kennung","convert");
			p.add("code",1);
			p.add("Anweisung",1);
			iGruppe = p.insert("begriffgruppe",true);
			b= b && iGruppe>0 && p.exec("update code set aic_begriffgruppe="+iGruppe+" where kennung in ('date to von','date to bis','von to date','bis to date','copy to QryAic','copy to Aic','change Aic','HoursToSeconds','SecondsToHours','number to boolean','number to date','date to number')");

			iIst = b ? Ver(1,19,2):iIst;
		}

		if(b && iIst < Ver(1,19,3))	// 12.6.2001
		{
			g.fixInfo("Versionsupdate 1.19.3");

			b=b && p.exec("update code set kennung='exit' where kennung='break'");
			b=b && p.exec("update code set kennung='init number' where kennung='Initialization'");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"break")>0;
			}
			else
				b = false;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"pos Date")>0;
				b=b && addCode(iGruppe,"pos next Date")>0;
				b=b && addCode(iGruppe,"= Date")>0;
			}
			else
				b = false;

			b=b && p.exec("delete from code where kennung like 'remove %'");

			iIst = b ? Ver(1,19,3):iIst;
		}
		if(b && iIst < Ver(1,19,4))	// 12.6.2001
		{
			g.fixInfo("Versionsupdate 1.19.4");

			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"pos -1")>0;
			}
			else
				b = false;

			iIst = b ? Ver(1,19,4):iIst;
		}

		if(b && iIst < Ver(1,20,1))	// 26.6.2001
		{
			g.fixInfo("Versionsupdate 1.20.1");
			b=b && p.exec("update bew_von_bis set dauer=dauer*3600");
			b=b && p.exec("alter table Bewegungstyp ADD NUR_MODELL integer null");
			b=b && p.exec("alter table SPALTE ADD AIC_STAMM integer null");
			b=b && p.exec("alter table SPALTE ADD COD2_AIC_CODE integer null");
			b=b && p.exec("alter table SPALTE add foreign key FK_SPALTE_EINHEIT_STAMM (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
			b=b && p.exec("alter table SPALTE add foreign key FK_SPALTE_AUSRICHTU_CODE (COD2_AIC_CODE) references CODE (AIC_CODE) on update restrict on delete restrict");
			b=b && p.exec("update code set kennung='save datetime' where kennung='save date'");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"save date")>0;
			}

			iIst = b ? Ver(1,20,1):iIst;
		}

		if(b && iIst < Ver(1,21,1))	// 1.8.2001
		{
			g.fixInfo("Versionsupdate 1.21.1");
			b=b && p.exec("alter table STAMM_PROTOKOLL drop PRIMARY KEY");
			b=b && p.exec("alter table STAMM_PROTOKOLL add PRIMARY  KEY (AIC_STAMM, AIC_PROTOKOLL)");
			b=b && p.exec("alter table BEGRIFF_ZUORDNUNG add REIHENFOLGE integer null");
			b=b && p.exec("update BEGRIFF_ZUORDNUNG set REIHENFOLGE=1");
			b=b && p.exec("update ZUORDNUNG set REIHENFOLGE=1 where kennung='Begriff'");
			int iBegriff=SQL.getInteger(g,"select aic_tabellenname from tabellenname where kennung='Begriff'");
			int iModell=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Modell'");
			int iAppl=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Applikation'");
			b=b && p.exec("insert into zuordnung(kennung,aic_tabellenname,tab_aic_tabellenname,reihenfolge,aic_begriffgruppe,beg_aic_begriffgruppe) values "+
				"('Modell',"+iBegriff+","+iBegriff+",1,"+iAppl+","+iModell+")");

			iIst = b ? Ver(1,21,1):iIst;
		}
		if(b && iIst < Ver(1,21,2))	// 2.8.2001
		{
			g.fixInfo("Versionsupdate 1.21.2");
			b=b && p.exec("alter table BEGRIFF ADD BITS integer null");
			b=b && p.exec("alter table DRUCK_ZUORDNUNG ADD AIC_BEGRIFF integer null");
			p.clear();
			p.add("Kennung","Druck");
			int iDruck = p.insert("begriffgruppe",true);
			int iDruckOld=SQL.getInteger(g,"select aic_tabellenname from tabellenname where kennung='Druck'");
			int iBegriff=SQL.getInteger(g,"select aic_tabellenname from tabellenname where kennung='Begriff'");
			Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from druck",true);
			for(Tab.moveFirst();b && !Tab.eof();Tab.moveNext())
			{
				p.clear();
				p.add("AIC_Begriffgruppe",iDruck);
				p.add("Kennung",Tab.getS("Kennung"));
				p.add("aic_stammtyp",Tab.getI("aic_stammtyp"));
				int iAicOld=Tab.getI("aic_Druck");
				int iAicNeu=p.insert("Begriff",true);
				b=b && iAicNeu>0;
				b=b && p.exec("update bezeichnung set aic_tabellenname="+iBegriff+",aic_fremd="+iAicNeu+" where aic_tabellenname="+iDruckOld+" and aic_fremd="+iAicOld);
				b=b && p.exec("update druck_zuordnung set aic_begriff="+iAicNeu+" where aic_druck="+iAicOld);
			}
			b=b && p.exec("alter table DRUCK_ZUORDNUNG drop PRIMARY KEY");
			b=b && p.exec("alter table DRUCK_ZUORDNUNG drop foreign key FK_DRUCK_ZU_DRUCK_DES_DRUCK");
			b=b && p.exec("alter table DRUCK_ZUORDNUNG MODIFY AIC_BEGRIFF integer not null");
			b=b && p.exec("alter table DRUCK_ZUORDNUNG add foreign key FK_DRUCK_ZU_DRUCK_DES_DRUCK (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");
			b=b && p.exec("alter table DRUCK_ZUORDNUNG add PRIMARY KEY (AIC_BEGRIFF, AIC_ABSCHNITT)");
			b=b && p.exec("alter table DRUCK_ZUORDNUNG drop AIC_DRUCK");
			b=b && p.exec("drop table DRUCK");

			iIst = b ? Ver(1,21,2):iIst;
		}
		if(b && iIst < Ver(1,21,3))	// 4.8.2001
		{
			g.fixInfo("Versionsupdate 1.21.3");
			g.fixInfo(SQL.getInteger(g,"select count(*) from stammpool join eigenschaft join begriff where begriff.kennung in ('Bezeichnung','Kennung','Eintritt','Austritt')")+" Stammpools gelöscht!");
			b=b && p.exec("delete from stammpool from stammpool join eigenschaft join begriff where begriff.kennung in ('Bezeichnung','Kennung','Eintritt','Austritt')");
			g.fixInfo("neue Befehle");
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'"),"save NIL")>0;
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'"),"concat with space")>0;
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'"),"= 0")>0;
			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'"),"pos last")>0;
			g.fixInfo("neuer Datentyp");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			p.clear();
			b=b && addBegriff(iGruppe,"Filler")>0;

			iIst = b ? Ver(1,21,3):iIst;
		}

		if(b && iIst < Ver(1,21,4))	// 6.8.2001
		{
			g.fixInfo("Versionsupdate 1.21.4");

			g.fixInfo("neuer Datentyp");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			p.clear();
			b=b && addBegriff(iGruppe,"Zeit")>0;

			iIst = b ? Ver(1,21,4):iIst;
		}

		if(b && iIst < Ver(1,21,5))	// 8.8.2001
		{
			g.fixInfo("Versionsupdate 1.21.5");
			int iTab = p.getInteger("select aic_tabellenname from tabellenname where kennung='Druck'");
			if(iTab>0)
			{
				b=b && p.exec("delete from bezeichnung where aic_tabellenname="+iTab);
				b=b && p.exec("delete from Daten_Bild where aic_tabellenname="+iTab);
				b=b && p.exec("delete from Daten_Memo where aic_tabellenname="+iTab);
				b=b && p.exec("delete from Lizenz where aic_tabellenname="+iTab);
				b=b && p.exec("delete from Versionsupdate where aic_tabellenname="+iTab);
				b=b && p.exec("delete from Tabellenname where aic_tabellenname="+iTab);
			}

			iIst = b ? Ver(1,21,5):iIst;
		}

		if(b && iIst < Ver(1,22,1))	// 24.9.2001
		{
			g.fixInfo("Versionsupdate 1.22.1");

			b=b && p.exec("alter table bewegungstyp modify NUR_MODELL bit null");
			b=b && p.exec("alter table eigenschaft add JEDER bit null");

			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			if (b && iGruppe>0)
			{
				addCode(iGruppe,"count");
			}
			else
				b = false;

			b=b && p.exec("alter table Benutzer add BITS integer null");
			b=b && p.exec("update Benutzer set bits=2 where Entwickler=1");
			b=b && p.exec("update Benutzer set bits=1 where Prog=1");
			b=b && p.exec("update Benutzer set bits=0 where bits is null");
			b=b && p.exec("update Benutzer set bits=3 where aic_mandant>1 and bits>0");
			b=b && p.exec("alter table Benutzer drop PROG");
			b=b && p.exec("alter table Benutzer drop Entwickler");

			b=b && p.exec("alter table BENUTZERGRUPPE add AIC_BEGRIFF integer null");
			b=b && p.exec("alter table BENUTZERGRUPPE add foreign key FK_BENUTZER_STARTFORM_BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");

			b=b && p.exec("alter table BEGRIFF add AIC_MANDANT integer null");
			b=b && p.exec("alter table BEGRIFF add foreign key FK_BEGRIFF_MANDANT_D_MANDANT (AIC_MANDANT) references MANDANT (AIC_MANDANT) on update restrict on delete restrict");
			b=b && p.exec("update begriff as b join modell as m set b.aic_mandant=m.aic_mandant");
			b=b && p.exec("update begriff set aic_mandant=1 where aic_mandant is null");
			b=b && p.exec("alter table MODELL drop foreign key FK_MODELL_MANDANT_D_MANDANT");
			b=b && p.exec("alter table modell drop aic_mandant");

			iIst = b ? Ver(1,22,1):iIst;
		}

		if(b && iIst < Ver(1,22,2))	// 27.9.2001
		{
			g.fixInfo("Versionsupdate 1.22.2");

			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"count distinct")>0;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"save String")>0;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'");
			b=b && iGruppe>0;
			if (b)
			{
				b=addCode(iGruppe,"trunc left")>0;
				b=b && addCode(iGruppe,"trunc right")>0;
			}

			p.clear();
			p.add("Kennung","Format");
			b=b && p.insert("begriffgruppe",true)>0;

			iIst = b ? Ver(1,22,2):iIst;
		}

		if(b && iIst < Ver(1,23,1))	// 25.10.2001
		{
			g.fixInfo("Versionsupdate 1.23.1");
			b=b && p.exec("alter table BEWEGUNGSTYP add LOKALTIMER bit null");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"next year")>0;
				b=b && addCode(iGruppe,"next week")>0;

				b=b && addCode(iGruppe,"prev week")>0;
				b=b && addCode(iGruppe,"prev month")>0;
				b=b && addCode(iGruppe,"prev year")>0;
			}
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"Anweisung1")>0;
				b=b && addCode(iGruppe,"Anweisung2")>0;
				b=b && addCode(iGruppe,"Anweisung3")>0;
				b=b && addCode(iGruppe,"Anweisung4")>0;
				b=b && addCode(iGruppe,"Anweisung5")>0;
				b=b && addCode(iGruppe,"Anweisung6")>0;
				b=b && addCode(iGruppe,"Anweisung7")>0;
				b=b && addCode(iGruppe,"Anweisung8")>0;
				b=b && addCode(iGruppe,"Anweisung9")>0;
				b=b && addCode(iGruppe,"Anweisung10")>0;
			}
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"Bedingung1")>0;
				b=b && addCode(iGruppe,"Bedingung2")>0;
				b=b && addCode(iGruppe,"Bedingung3")>0;
				b=b && addCode(iGruppe,"Bedingung4")>0;
				b=b && addCode(iGruppe,"Bedingung5")>0;
			}
			b=b && p.exec("update code set kennung='Autoimport' where kennung='copie'");

			g.fixInfo("Filter ermitteln");
			b=b && p.exec("update abfrage set bits=bits|"+g.cstFilter+" where (select count(*) from spalte where aic_abfrage=abfrage.aic_abfrage)=0");
			iIst = b ? Ver(1,23,1):iIst;
		}
		if(b && iIst < Ver(1,24,1))	// 19.11.2001
		{
			g.fixInfo("Versionsupdate 1.24.1");
			b=b && p.exec("update begriff as b join modell as m set b.bits=m.mehrfach*1");
			b=b && p.exec("update begriff as b join formular as f set b.bits=f.bits");
			b=b && p.exec("update begriff as b join abfrage as a set b.bits=a.bits");
			b=b && p.exec("alter table modell drop anzeigen");
			b=b && p.exec("alter table modell drop mehrfach");
			b=b && p.exec("alter table formular drop bits");
			b=b && p.exec("alter table abfrage drop bits");
			iIst = b ? Ver(1,24,1):iIst;
		}
		if(b && iIst < Ver(1,24,2))	// 19.11.2001
		{
			g.fixInfo("Versionsupdate 1.24.2");
			p.clear();
			for(Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct daten_string60.aic_daten,spalte_string60 from daten_string60 join daten join stammpool join eigenschaft join begriff where begriff.kennung='Pfad'",true);
				!Tab.eof();Tab.moveNext())
			{

				p.add("aic_daten",Tab.getI("aic_daten"));
				p.add("spalte_stringLang",Tab.getS("spalte_string60"));
				p.insert("daten_stringLang",false);
			}
			iIst = b ? Ver(1,24,2):iIst;
		}

		if(b && iIst < Ver(1,24,3))	// 20.11.2001
		{
			g.fixInfo("Versionsupdate 1.24.3");
			b=b && p.exec("alter table EIGENSCHAFT add EIG_AIC_EIGENSCHAFT integer null");
			b=b && p.exec("alter table EIGENSCHAFT add foreign key FK_EIGENSCH_EIGENSCHA_EIGENSCH (EIG_AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Vergleich'");
			if (b && iGruppe>0)
			{
				addBegriff(iGruppe,"is main");
			}
			else
				b = false;

			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"remember line")>0;
				b=b && addCode(iGruppe,"save line")>0;
			}

			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"is new year")>0;
				b=b && addCode(iGruppe,"is new year eve")>0;
			}

			iIst = b ? Ver(1,24,3):iIst;
		}
		if(b && iIst < Ver(1,24,4))	// 22.11.2001
		{
			g.fixInfo("Versionsupdate 1.24.4");

			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"remove real aic")>0;
			}

			iIst = b ? Ver(1,24,4):iIst;
		}

		if(b && iIst < Ver(1,25,1))	// 13.12.2001
		{
			g.fixInfo("Versionsupdate 1.25.1");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"del deleted")>0;
			}
			else
				b = false;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"set new year")>0;
			}
			else
				b = false;
			b=b && p.exec("update begriff set kennung='= Joker' where kennung='is main'");
			b=b && p.exec("alter table LOGGING add C_NUMBER integer null");
			b=b && p.exec("update begriff set aic_mandant=1 where aic_mandant is null");
			b=b && p.exec("alter table ABFRAGE add SPALTEN integer null");
			b=b && p.exec("alter table SPALTE add NUMMER integer null");
			b=b && p.exec("update SPALTE set NUMMER=REIHENFOLGE");
			b=b && p.exec("update ABFRAGE set SPALTEN=(select max(reihenfolge) from spalte where aic_abfrage=abfrage.aic_abfrage)");

			iIst = b ? Ver(1,25,1):iIst;
		}

		if(b && iIst < Ver(1,25,2))	// 19.12.2001
		{
			g.fixInfo("Versionsupdate 1.25.2");

			b=b && p.exec("delete from begriff where kennung='= Joker'");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Vergleich'");
			if (b && iGruppe>0)
			{
				addBegriff(iGruppe,">=");
				addBegriff(iGruppe,"<=");
			}
			else
				b = false;

			if (b)
			{
				p.clear();
				p.add("Kennung","Platzhalter");
				p.add("code",1);
				iGruppe = p.insert("begriffgruppe",true);
			}
			if (b && iGruppe>0)
			{
				p.clear();
				addCode(iGruppe,"Joker");
				addCode(iGruppe,"Stichtag");
				addCode(iGruppe,"von");
				addCode(iGruppe,"bis");
				addCode(iGruppe,"now()");
				addCode(iGruppe,"today()");
			}

			b=b && p.exec("alter table LOGGING add VERSION integer null");

			iIst = b ? Ver(1,25,2):iIst;
		}
		if(b && iIst < Ver(1,27,1))	//  5. 2.2002
		{
			g.fixInfo("Versionsupdate 1.27.1");
			b=b && p.exec("update code set kennung='clear row' where kennung='Anweisung1'");
			b=b && p.exec("update code set kennung='set Joker' where kennung='Anweisung2'");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"get Stt")>0;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			b=b && iGruppe>0;
			if (b)
				b=b && addCode(iGruppe,"is Stt")>0;

			b=b && p.exec("alter table SPALTE add FAKTOR double null");
			b=b && p.exec("alter table SPALTE add FILTER integer null");

			iIst = b ? Ver(1,27,1):iIst;
		}
		if(b && iIst < Ver(1,27,2))	//  7. 2.2002
		{
			g.fixInfo("Versionsupdate 1.27.2");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"get real Stt")>0;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"set StringJoker")>0;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"init iReg")>0;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"StringJoker")>0;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Tabellentyp'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"Entfernen")>0;
			if (b)
			{
				p.clear();
				p.add("Kennung","Modul");
				iGruppe = p.insert("begriffgruppe",true);
			}

			iIst = b ? Ver(1,27,2):iIst;
		}
		if(b && iIst < Ver(1,27,3))	//  14. 2.2002
		{
			g.fixInfo("Versionsupdate 1.27.3");
			b=b && p.exec("delete from stammtyp_zuordnung from stammtyp_zuordnung join eigenschaft join begriff where begriff.kennung like 'Bew%'");
			b=b && p.exec("delete from bew_zuordnung from bew_zuordnung join eigenschaft join begriff where begriff.kennung in ('Kennung','Bezeichnung','Eintritt','Austritt')");
			iIst = b ? Ver(1,27,3):iIst;
		}
		if(b && iIst < Ver(1,27,4))	//  19. 2.2002
		{
			g.fixInfo("Versionsupdate 1.27.4");

			p.clear();
			p.add("Kennung","terminal");
			p.add("code",1);
			p.add("Anweisung",1);
			int iGruppe = p.insert("begriffgruppe",true);
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"set Terminaltime")>0;
				b=b && addCode(iGruppe,"read Terminalhits")>0;
				b=b && addCode(iGruppe,"read Terminalfiles")>0;
				b=b && addCode(iGruppe,"set Terminalstamm")>0;
			}
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"< date")>0;
				b=b && addCode(iGruppe,"> date")>0;
			}

			iIst = b ? Ver(1,27,4):iIst;
		}
		if(b && iIst < Ver(1,27,5))	//  20. 2.2002
		{
			g.fixInfo("Versionsupdate 1.27.5");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='terminal'");
			b=b && iGruppe>0;
			if (b)
				b=b && addCode(iGruppe,"reset Terminal")>0;
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			b=b && iGruppe>0;
			if (b)
				b=b && addCode(iGruppe,"is Sunday")>0;
			iIst = b ? Ver(1,27,5):iIst;
		}
		if(b && iIst < Ver(1,27,6))	//   7. 3.2002
		{
			g.fixInfo("Versionsupdate 1.27.6");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='terminal'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"connect Terminal")>0;
				b=b && addCode(iGruppe,"close Terminal")>0;
			}
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='convert'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"swap number")>0;
				b=b && addCode(iGruppe,"swap date")>0;
				b=b && addCode(iGruppe,"swap String")>0;
				b=b && addCode(iGruppe,"swap Aic")>0;
				b=b && addCode(iGruppe,"swap QryAic")>0;
			}

			iIst = b ? Ver(1,27,6):iIst;
		}
		if(b && iIst < Ver(1,27,7))	//   12. 3.2002
		{
			g.fixInfo("Versionsupdate 1.27.7");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"delete file")>0;
				b=b && addCode(iGruppe,"rename file")>0;
			}
			iIst = b ? Ver(1,27,7):iIst;
		}

		if(b && iIst < Ver(1,28,1))	//   9. 4.2002
		{
			g.fixInfo("Versionsupdate 1.28.1");

			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='convert'");
			b=b && iGruppe>0;
			if (b)
			{
				b=b && addCode(iGruppe,"date to Stichtag")>0;
				b=b && addCode(iGruppe,"Stichtag to date")>0;
			}
			b=b && p.exec("alter table BEZEICHNUNG MODIFY BEZEICHNUNG char(60)");

			iIst = b ? Ver(1,28,1):iIst;
		}

		if(b && iIst < Ver(1,28,2))	//   10. 4.2002
		{
			g.fixInfo("Versionsupdate 1.28.2");
			b=b && p.exec("create table ZWISCHENSPEICHER("+
    			"AIC_ZWISCHENSPEICHER  integer               not null default AUTOINCREMENT,"+
    			"AIC_PROTOKOLL         integer               not null,"+
    			"PRO_AIC_PROTOKOLL     integer                   null,"+
    			"KENNUNG               char(20)              not null,"+
    			"GUELTIG               timestamp                 null,"+
    			"ZWISCHENTEXT          char(255)                 null,"+
    			"primary key (AIC_ZWISCHENSPEICHER));");

			b=b && p.exec("alter table ZWISCHENSPEICHER add foreign key FK_ZWISCHEN_ERSTELLT_PROTOKOL (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL) on update restrict on delete restrict;");
			b=b && p.exec("alter table ZWISCHENSPEICHER add foreign key FK_ZWISCHEN_GELOESCHT_PROTOKOL (PRO_AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL) on update restrict on delete restrict;");

	   		b=b && p.exec("alter table eigenschaft add KEINAUTODATE bit null");

			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anlage'"),"Terminal")>0;
			b=b && p.exec("alter table LOGGING add AIC_CODE integer null");
			b=b && p.exec("alter table LOGGING add foreign key FK_LOGGING_ART_DER_A_CODE (AIC_CODE) references CODE (AIC_CODE) on update restrict on delete restrict");

	   		iIst = b ? Ver(1,28,2):iIst;
		}

		if(b && iIst < Ver(1,28,4))	//   15. 4.2002
		{
			g.fixInfo("Versionsupdate 1.28.4");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			b=b && iGruppe>0;
			if (b)
				b=b && addCode(iGruppe,"insert line")>0;
			iIst = b ? Ver(1,28,4):iIst;
		}

		if(b && iIst < Ver(1,29,1))	//   26. 4.2002
		{
			g.fixInfo("Versionsupdate 1.29.1");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='terminal'");
			b=b && iGruppe>0;
			if (b)
				b=b && addCode(iGruppe,"Terminalquitierung")>0;
			iIst = b ? Ver(1,29,1):iIst;
		}

		if(b && iIst < Ver(1,29,2))	//   3. 5.2002
		{
			g.fixInfo("Versionsupdate 1.29.2");
			p.clear();
			p.add("Kennung","Planung");
			b=b && p.insert("begriffgruppe",true)>0;

			b=b && p.exec("create table PLANUNG("+
    			"AIC_BEGRIFF       integer               not null,"+
    			"AIC_ABFRAGE       integer               not null,"+
    			"AIC_MODELL        integer                   null,"+
    			"primary key (AIC_BEGRIFF))");

			b=b && p.exec("alter table PLANUNG add foreign key FK_PLANUNG_ABFRAGE_D_ABFRAGE (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");
			b=b && p.exec("alter table PLANUNG add foreign key FK_PLANUNG_MODELL_DE_MODELL (AIC_MODELL) references MODELL (AIC_MODELL) on update restrict on delete restrict");
			b=b && p.exec("alter table PLANUNG add foreign key FK_PLANUNG_BEGRIFF_D_BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");

			b=b && p.exec("create table SPALTE_ZUORDNUNG("+
    			"AIC_SPALTE        integer               not null,"+
				"AIC_STAMM         integer               not null,"+
				"REIHENFOLGE       integer                   null,"+
				"primary key (AIC_SPALTE, AIC_STAMM))");

			b=b && p.exec("alter table SPALTE_ZUORDNUNG add foreign key FK_SPALTE_Z_SPALTE_DE_SPALTE (AIC_SPALTE) references SPALTE (AIC_SPALTE) on update restrict on delete restrict");
			b=b && p.exec("alter table SPALTE_ZUORDNUNG add foreign key FK_SPALTE_Z_STAMM_DER_STAMM (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");

			g.fixInfo("- neue Datentypen");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			if (b && iGruppe>0)
			{
				addBegriff(iGruppe,"Farbe");
				addBegriff(iGruppe,"Bits");
			}

			iIst = b ? Ver(1,29,2):iIst;
		}

		if(b && iIst < Ver(1,29,3))	//   8. 5.2002
		{
			g.fixInfo("Versionsupdate 1.29.3");
			b=p.exec("SET OPTION public.OPTIMIZATION_GOAL = 'all-rows'");

			iIst = b ? Ver(1,29,3):iIst;
		}

		if(b && iIst < Ver(1,29,4))	//   8. 5.2002
		{
			g.fixInfo("Versionsupdate 1.29.4");
			Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_zwischenspeicher,zwischentext from zwischenspeicher where zwischentext like '%78'+char(13)+'%'",true);
			for(Tab.moveFirst();b && !Tab.eof();Tab.moveNext())
			{
				String s=Tab.getS("zwischentext");
				int i=s.indexOf("78\r");
				if(i==0)
					s=s.substring(3);
				else if(i>0)
					s=s.substring(0,i);
				b=p.exec("update zwischenspeicher set zwischentext='"+s+"' where aic_zwischenspeicher="+Tab.getI("aic_zwischenspeicher"));
			}

			iIst = b ? Ver(1,29,4):iIst;
		}

		if(b && iIst < Ver(1,29,5))	//   8. 5.2002
		{
			g.fixInfo("Versionsupdate 1.29.5");

			b=p.exec("alter table zwischenspeicher ALTER GUELTIG DROP DEFAULT");

			iIst = b ? Ver(1,29,5):iIst;
		}

		if(b && iIst < Ver(1,30,1))	//   23. 5.2002
		{
			g.fixInfo("Versionsupdate 1.30.1");

			b=b && p.exec("drop table planung");

			b=b && p.exec("create table PLANUNG("+
    			"AIC_BEGRIFF      integer               not null,"+
    			"AIC_ABFRAGE      integer               not null,"+
				"ABF_AIC_ABFRAGE  integer                   null,"+
				"SPALTENBREITE    integer                   null,"+
				"primary key (AIC_BEGRIFF))");

			b=b && p.exec("alter table PLANUNG add foreign key FK_PLANUNG_PLANUNGSD_ABFRAGE (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");
			b=b && p.exec("alter table PLANUNG add foreign key FK_PLANUNG_PLANUNGSF_ABFRAGE (ABF_AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");
			b=b && p.exec("alter table PLANUNG add foreign key FK_PLANUNG_PLANUNGSB_BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");

			b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anlage'"),"Planung")>0;

			iIst = b ? Ver(1,30,1):iIst;
		}

		if(b && iIst < Ver(1,31,1))	//   19. 6.2002
		{
			g.fixInfo("Versionsupdate 1.31.1");

			b=b && p.exec("create view stammview2 as select stamm.aic_stamm,kennung,bezeichnung,aic_stammtyp,aic_mandant,eintritt,austritt from stamm join stamm_protokoll where pro_aic_protokoll is null");

			b=b && p.exec("alter view stammview as select * from stammview2 where (von is null or austritt is null or austritt>=von) and (bis is null or eintritt is null or eintritt<bis)");
			//b=b && p.exec("alter view stammview as select stamm.aic_stamm,kennung,bezeichnung,aic_stammtyp,aic_mandant,eintritt,austritt from stamm join stamm_protokoll"+
			//	" where pro_aic_protokoll is null and (von is null or austritt is null or austritt>=von) and (bis is null or eintritt is null or eintritt<bis)");

			b=b && p.exec("create view bewview2 as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant from bew_pool where pro_aic_protokoll is null");

			b=b && p.exec("create view bewview3 as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant from bew_pool"+
 				" where (gueltig is null or (von is null or von<=gueltig) and (bis is null or bis>gueltig))");


			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			if (b && iGruppe>0)
				addBegriff(iGruppe,"entfernt");


			iIst = b ? Ver(1,31,1):iIst;
		}

		if(b && iIst < Ver(1,31,2))	//   11. 7.2002
		{
			g.fixInfo("Versionsupdate 1.31.2");

			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
			b=b && iGruppe>0;
			if (b)
				b=b && p.exec("update code set kennung='write import data',aic_begriffgruppe="+iGruppe+" where kennung='Anweisung3'");

			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			b=b && iGruppe>0;
			if (b)
				b=b && addCode(iGruppe,"= String")>0;

			iIst = b ? Ver(1,31,2):iIst;
		}

		if(b && iIst < Ver(1,31,3))	//   16. 7.2002
		{
			g.fixInfo("Versionsupdate 1.31.3");

			b=b && p.exec("alter table PLANUNG add ABF2_AIC_ABFRAGE integer null");
			b=b && p.exec("alter table PLANUNG add foreign key FK_PLANUNG_PLANUNGS2_ABFRAGE (ABF2_AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");

			iIst = b ? Ver(1,31,3):iIst;
		}

		if(b && iIst < Ver(1,31,4))	//   20. 7.2002
		{
			g.fixInfo("Versionsupdate 1.31.4");

			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"init VecAic")>0;

			b=b && p.exec("update stamm_protokoll join stamm join stammtyp set austritt=null where stamm.kennung='ATS' and stammtyp.kennung='Waehrung' and pro_aic_protokoll is null");

			iIst = b ? Ver(1,31,4):iIst;
		}

		if(b && iIst < Ver(1,31,5))	//   23. 7.2002
		{
			g.fixInfo("Versionsupdate 1.31.5");

			int iStt=p.getInteger("select aic_stammtyp from stammtyp where kennung='Projekt'");
			int iEig=p.getInteger("select aic_eigenschaft from eigenschaft where kennung='Projekt Name'");

			if (iStt>0 && iEig>0)
			{
				int iDaten=0;
				int iPool=0;
				p.clear();
				Tabellenspeicher Tab=new Tabellenspeicher(g,"select stamm.aic_stamm,bezeichnung,aic_protokoll,"+
					"(select spalte_string60 from daten_string60 join daten join stammpool where aic_stamm=stamm.aic_stamm and pro2_aic_protokoll is null and aic_eigenschaft="+iEig+
					"),(select aic_daten from daten_string60 where bezeichnung=spalte_string60)"+
 					"from stamm join stamm_protokoll where pro_aic_protokoll is null and aic_stammtyp="+iStt,true);
				for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
					if (Tab.getInhalt("spalte_string60")==null)
					{
						int iNr=Tab.getI("aic_daten");
						if (iNr==0)
						{
							p.add("Aic_Daten","DEFAULT".getBytes());
							iNr = p.insert("Daten",true);
							p.add("aic_daten",iNr);
							p.add("spalte_string60",Tab.getS("Bezeichnung"));
							p.insert("daten_string60",false);
							iDaten++;
						}
						p.add("Aic_Stamm",Tab.getI("aic_stamm"));
						p.add("Aic_Eigenschaft",iEig);
						p.add("aic_protokoll",Tab.getI("aic_protokoll"));
						p.add("aic_daten",iNr);
						p.insert("Stammpool",false);
						iPool++;
					}
				g.fixInfo(iPool+" Projekte umkopiert mit "+iDaten+" Daten");
			}

			iIst = b ? Ver(1,31,5):iIst;
		}


		if(b && iIst < Ver(2,0,1))	//   21. 8.2002
		{
			g.fixInfo("Versionsupdate 2.0.1");
			b=b && p.exec("delete from lizenz from lizenz join tabellenname where kennung in ('Tabellenname','Begriffgruppe')");
			b=b && p.exec("alter table stammtyp add LIZENZ bit NULL");
			b=b && p.exec("alter table stammtyp add COPY bit NULL");
			b=b && p.exec("update stammtyp,lizenz join tabellenname set lizenz=1 where tabellenname.kennung='Stammtyp' and anzahl>-1 and aic_stammtyp=aic_fremd");

			iIst = b ? Ver(2,0,1):iIst;
		}

		if(b && iIst < Ver(2,0,2))	//   30. 8.2002
		{
			g.fixInfo("Versionsupdate 2.0.2");
			b=b && p.exec("delete from daten_bild where filename='' or filename like ' '");
			b=b && p.exec("alter table FORMULAR drop foreign key FK_FORMULAR_RELATION__BENUTZER");
			b=b && p.exec("alter table FORMULAR drop AIC_BENUTZERGRUPPE");
			b=b && p.exec("delete from lizenz where anzahl is null");
			b=b && p.exec("alter table ABFRAGE ADD AIC_BENUTZER integer null");
			b=b && p.exec("alter table ABFRAGE add foreign key FK_ABFRAGE_RELATION_BENUTZER (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER) on update restrict on delete restrict");

			iIst = b ? Ver(2,0,2):iIst;
		}

		if(b && iIst < Ver(2,0,3))	//   9. 9.2002
		{
			g.fixInfo("Versionsupdate 2.0.3");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"ich")>0;
			iIst = b ? Ver(2,0,3):iIst;
		}

		if(b && iIst < Ver(2,0,4))	//  12. 9.2002
		{
			g.fixInfo("Versionsupdate 2.0.4");
			b=b && p.exec("alter table daten_image MODIFY Filename char(100)");

			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			if (b && iGruppe>0)
			{
				addBegriff(iGruppe,"BewDruck");
				addBegriff(iGruppe,"BewFormular");
			}

			iIst = b ? Ver(2,0,4):iIst;
		}

		if(b && iIst < Ver(2,0,5))	//  16. 9.2002
		{
			g.fixInfo("Versionsupdate 2.0.5");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='terminal'");
			b=b && iGruppe>0;
			if (b)
				b=b && addCode(iGruppe,"set Terminalenter")>0;
			iIst = b ? Ver(2,0,5):iIst;
		}

		if(b && iIst < Ver(2,0,6))	//  23. 9.2002
		{
			g.fixInfo("Versionsupdate 2.0.6");
			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			b=b && iGruppe>0;
			b=b && addCode(iGruppe,"sort String down")>0;
			b=b && addCode(iGruppe,"sort String up")>0;

			iIst = b ? Ver(2,0,6):iIst;
		}

		if(b && iIst < Ver(2,1,2))	//  29. 10.2002
		{
			g.fixInfo("Versionsupdate 2.1.2");
			b=b && p.exec("alter table BENUTZERGRUPPE ADD READONLY bit null");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			if (b && iGruppe>0)
			{
				b= b && addBegriff(iGruppe,"EinAustritt")>0;
			}
			iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"next n month")>0;
				b=b && addCode(iGruppe,"next n weeks")>0;
				b=b && addCode(iGruppe,"get monthLength")>0;
			}
			iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"< duration")>0;
				b=b && addCode(iGruppe,"= duration")>0;
				b=b && addCode(iGruppe,"> duration")>0;
			}
			p.add("Kennung","OLE");
			p.add("code",1);
			p.add("Anweisung",1);
			iGruppe = p.insert("begriffgruppe",true);
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"OLE row")>0;
				b=b && addCode(iGruppe,"OLE all")>0;
				b=b && addCode(iGruppe,"OLE print row")>0;
				b=b && addCode(iGruppe,"OLE print all")>0;
			}

			iIst = b ? Ver(2,1,2):iIst;
		}

		if(b && iIst < Ver(2,1,3))	//   4. 11.2002
		{
			g.fixInfo("Versionsupdate 2.1.3");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			b=b && iGruppe>0;
			int iBegriff=addBegriff(iGruppe,"Filename");
			b=b && iBegriff>0;
			b=b && p.exec("Update eigenschaft e join begriff set e.aic_begriff="+iBegriff+" where begriff.kennung='Pfad'");

			b=b && p.exec("alter table versionsupdate add BITS bit NULL");

			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			b=b && iGruppe>0;
			b=b && addCode(iGruppe,"open file")>0;

			iIst = b ? Ver(2,1,3):iIst;
		}

		if(b && iIst < Ver(2,1,4))	//   7. 11.2002
		{
			g.fixInfo("Versionsupdate 2.1.4");

			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"pos number")>0;
				b=b && addCode(iGruppe,"pos next number")>0;
			}

			iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"pos min")>0;
				b=b && addCode(iGruppe,"pos max")>0;
			}

			b=b && p.exec("alter table SPALTE_ZUORDNUNG ADD TITEL char(20) null");

			iIst = b ? Ver(2,1,4):iIst;
		}

		if(b && iIst < Ver(2,2,1))	//   20. 11.2002
		{
			g.fixInfo("Versionsupdate 2.2.1");

			b=b && p.exec("alter table STAMM MODIFY KENNUNG char(20)");

			b=b && p.exec("delete from begriff_zuordnung from begriffgruppe g1 join begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.aic_begriff"+
				" join begriff b2 on b2.aic_begriff=z.beg_aic_begriff join begriffgruppe g2 where g1.kennung='Applikation' and g2.kennung='Modell'");
			b=b && p.exec("delete from zuordnung where kennung='Modell'");

			iIst = b ? Ver(2,2,1):iIst;
		}

		if(b && iIst < Ver(2,3,1))	//   12. 12.2002
		{
			g.fixInfo("Versionsupdate 2.3.1");

			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='OLE'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"OLE open")>0;
				b=b && addCode(iGruppe,"OLE one row")>0;
				b=b && addCode(iGruppe,"OLE all rows")>0;
				b=b && addCode(iGruppe,"OLE table")>0;
				b=b && addCode(iGruppe,"OLE print")>0;
				b=b && addCode(iGruppe,"OLE save")>0;
				b=b && addCode(iGruppe,"OLE close")>0;
			}
			iIst = b ? Ver(2,3,1):iIst;
		}

		if(b && iIst < Ver(2,3,2))	//   30. 12.2002
		{
			g.fixInfo("Versionsupdate 2.3.2");

			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"write file plus")>0;
				b=b && addCode(iGruppe,"write line plus")>0;
			}
			iIst = b ? Ver(2,3,2):iIst;
		}

		if(b && iIst < Ver(2,4,1))	//   14. 1.2003
		{
			g.fixInfo("Versionsupdate 2.4.1");

			int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"meinStamm")>0;

			iIst = b ? Ver(2,4,1):iIst;
		}

		if(b && iIst < Ver(2,4,2))	//   21. 1.2003
		{
			g.fixInfo("Versionsupdate 2.4.2");

			int iSave = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
			int iGet = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			int iMath = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'");
			int iInit = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
			int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			b=b && iSave>0 && iGet>0 && iMath>0 && iInit>0 && iAnw>0;
			b=b && p.exec("update code set kennung='String add 1',aic_begriffgruppe="+iMath+" where kennung='Anweisung4' and aic_begriffgruppe="+iAnw);
			b=b && p.exec("update code set kennung='get unit',aic_begriffgruppe="+iGet+" where kennung='Anweisung5' and aic_begriffgruppe="+iAnw);
			b=b && p.exec("update code set kennung='get 0-String',aic_begriffgruppe="+iGet+" where kennung='Anweisung6' and aic_begriffgruppe="+iAnw);
			b=b && p.exec("update code set kennung='write line',aic_begriffgruppe="+iSave+" where kennung='Anweisung7' and aic_begriffgruppe="+iAnw);
			b=b && p.exec("update code set kennung='create file' where kennung='Anweisung8' and aic_begriffgruppe="+iAnw);
			b=b && p.exec("update code set kennung='set vonbis',aic_begriffgruppe="+iInit+" where kennung='Anweisung9' and aic_begriffgruppe="+iAnw);

			b=b && addCode(iAnw,"Anweisung11")>0;
			b=b && addCode(iAnw,"Anweisung12")>0;
			b=b && addCode(iAnw,"Anweisung13")>0;
			b=b && addCode(iAnw,"Anweisung14")>0;
			b=b && addCode(iAnw,"Anweisung15")>0;
			b=b && addCode(iAnw,"Anweisung16")>0;
			b=b && addCode(iAnw,"Anweisung17")>0;
			b=b && addCode(iAnw,"Anweisung18")>0;
			b=b && addCode(iAnw,"Anweisung19")>0;
			b=b && addCode(iAnw,"Anweisung20")>0;

			b=b && addCode(iSave,"write line hold")>0;
			b=b && addCode(iSave,"close file")>0;

			iIst = b ? Ver(2,4,2):iIst;
		}

		if(b && iIst < Ver(2,9,1) && iSoll>=Ver(2,9,1))	//   9. 1.2003
		{
			g.fixInfo("Versionsupdate 2.9.1");

			b=b && p.exec("create view poolview as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von from stammpool where pro2_aic_protokoll is null and (bis is null or gultig_von is null or gultig_von<bis) and (gueltig_bis is null or gueltig_bis>=bis)");
			b=b && p.exec("create view poolview2 as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von from stammpool where pro2_aic_protokoll is null");

			iIst = b ? Ver(2,9,1):iIst;
		}

		if(b && iIst < Ver(2,9,2) && iSoll>=Ver(2,9,2))	//  30. 1.2003
		{
			g.fixInfo("Versionsupdate 2.9.2");

			p.add("Kennung","FTP");
			p.add("code",1);
			p.add("Anweisung",1);
			int iGruppe = p.insert("begriffgruppe",true);
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"FTP open")>0;
				b=b && addCode(iGruppe,"FTP open file")>0;
				b=b && addCode(iGruppe,"FTP write table")>0;
				b=b && addCode(iGruppe,"FTP write line")>0;
				b=b && addCode(iGruppe,"FTP read table")>0;
				b=b && addCode(iGruppe,"FTP close file")>0;
				b=b && addCode(iGruppe,"FTP close")>0;
			}


			iIst = b ? Ver(2,9,2):iIst;
		}

		if(b && iIst < Ver(2,9,3) && iSoll>=Ver(2,9,3))	//  31. 1.2003
		{
			g.fixInfo("Versionsupdate 2.9.3");

			b=b && p.exec("alter table BEW_POOL ADD BEW_AIC_BEW_POOL integer null");
			b=b && p.exec("alter table BEW_POOL add foreign key FK_BEW_POOL_BEW_NACHF_BEW_POOL (BEW_AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL) on update restrict on delete restrict");

			b=b && p.exec("alter table BEGRIFF ADD AIC_LOGGING integer null");
			b=b && p.exec("alter table BEGRIFF add foreign key FK_BEGRIFF_RELATION__LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
			b=b && p.exec("alter table STAMMTYP ADD AIC_LOGGING integer null");
			b=b && p.exec("alter table STAMMTYP add foreign key FK_STT_RELATION__LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
			b=b && p.exec("alter table BEWEGUNGSTYP ADD AIC_LOGGING integer null");
			b=b && p.exec("alter table BEWEGUNGSTYP add foreign key FK_BEW_RELATION__LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
			b=b && p.exec("alter table EIGENSCHAFT ADD AIC_LOGGING integer null");
			b=b && p.exec("alter table EIGENSCHAFT add foreign key FK_EIG_RELATION__LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");

			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			if (b && iGruppe>0)
				addBegriff(iGruppe,"Passwort");

			iIst = b ? Ver(2,9,3):iIst;
		}

		if(b && iIst < Ver(2,9,4) && iSoll>=Ver(2,9,4))	//  10. 2.2003
		{
			g.fixInfo("Versionsupdate 2.9.4");

			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='OLE'");
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"OLE open dot")>0;
				b=b && addCode(iGruppe,"OLE quit")>0;
			}

			p.add("Kennung","E-Mail");
			p.add("code",1);
			p.add("Anweisung",1);
			iGruppe = p.insert("begriffgruppe",true);
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"SMTP open")>0;
				b=b && addCode(iGruppe,"SMTP sendTo")>0;
				b=b && addCode(iGruppe,"SMTP subject")>0;
				b=b && addCode(iGruppe,"SMTP sendText")>0;
				b=b && addCode(iGruppe,"SMTP attach")>0;
				b=b && addCode(iGruppe,"SMTP close")>0;
			}
			iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
			b=b && iGruppe>0;
			if (b)
				b=addCode(iGruppe,"move prev real aic")>0;

			iIst = b ? Ver(2,9,4):iIst;
		}

		if(b && iIst < Ver(3,0,1) && iSoll>=Ver(3,0,1))	//  19. 2.2003
		{
			g.fixInfo("Versionsupdate 3.0.1");

			//b=b && p.exec("alter table BEGRIFF ADD BITS2 integer null");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Panel'");
			b=b && iGruppe>0 && addBegriff(iGruppe,"EFGroup")>0;


			iIst = b ? Ver(3,0,1):iIst;
		}

		if(b && iIst < Ver(3,0,2) && iSoll>=Ver(3,0,2))	//  4. 3.2003
		{
			g.fixInfo("Versionsupdate 3.0.2");

			int iSave = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
			int iGet = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			int iConvert = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='convert'");
			int iInit = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
			int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");

			b=iSave>0 && iGet>0 && iConvert>0 && iInit>0 && iAnw>0;

			b=b && p.exec("update code set kennung='init VecStamm',aic_begriffgruppe="+iInit+" where kennung='Anweisung10' and aic_begriffgruppe="+iAnw);
			b=b && p.exec("update code set kennung='push Stamm',aic_begriffgruppe="+iGet+" where kennung='Anweisung11' and aic_begriffgruppe="+iAnw);
			b=b && addCode(iConvert,"VecStamm to VecAic")>0;
			b=b && addCode(iConvert,"VecAic to VecStamm")>0;

			b=b && addCode(iSave,"clear data record")>0;
			b=b && addCode(iAnw,"refetch")>0;

			iIst = b ? Ver(3,0,2):iIst;
		}

		if(b && iIst < Ver(3,0,3) && iSoll>=Ver(3,0,3))	//  7. 3.2003
		{
			g.fixInfo("Versionsupdate 3.0.3");

			int iGet = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			int iMail = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='E-Mail'");
			b= iGet>0 && iMail>0;
			b=b && addCode(iGet,"get Sync-Stamm")>0;
			b=b && addCode(iMail,"SMTP CC")>0;
			b=b && addCode(iMail,"SMTP BCC")>0;

			//b=b && p.exec("alter table BEGRIFF DROP BITS2");
			b=b && p.exec("alter table begriff modify BITS bigint");

			iIst = b ? Ver(3,0,3):iIst;
		}

		if(b && iIst < Ver(3,0,4) && iSoll>=Ver(3,0,4))	// 17. 3.2003
		{
			g.fixInfo("Versionsupdate 3.0.4");

			int iDate = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
			int iInit = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
			int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");

			b=b && p.exec("update code set kennung='set quarter',aic_begriffgruppe="+iDate+" where kennung='Anweisung12' and aic_begriffgruppe="+iAnw);
			b=b && p.exec("update code set kennung='to is null' where kennung='Bedingung1' and aic_begriffgruppe="+iBed);
			b=b && p.exec("update code set kennung='is last quarter-day' where kennung='Bedingung2' and aic_begriffgruppe="+iBed);
			b=b && addCode(iInit,"init VecString")>0;

			iIst = b ? Ver(3,0,4):iIst;
		}

		if(b && iIst < Ver(3,0,5) && iSoll>=Ver(3,0,5))	// 17. 3.2003
		{
			g.fixInfo("Versionsupdate 3.0.5");

			b=b && p.exec("alter table BEGRIFF ADD ImportZeit DateTime null");

			iIst = b ? Ver(3,0,5):iIst;
		}

		if(b && iIst < Ver(3,0,6) && iSoll>=Ver(3,0,6))	// 24. 3.2003
		{
			g.fixInfo("Versionsupdate 3.0.6");

			int iAnz=g.exec("delete from stammpool where spalte_double=0.0");
			if(iAnz>0)
				g.fixInfo("0-Daten:"+iAnz+" gelöscht!");

			iIst = b ? Ver(3,0,6):iIst;
		}

		if(b && iIst < Ver(3,1,2) && iSoll>=Ver(3,1,2))	// 29. 4.2003
		{
			g.fixInfo("Versionsupdate 3.1.2");

			int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			int iError = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='error handling'");

			b=b && p.exec("update code set kennung='FTP-Error' where kennung='Bedingung3' and aic_begriffgruppe="+iBed);
			b=b && addCode(iBed,"SMTP-Error")>0;
			b=b && addCode(iBed,"is real Stt")>0;
			b=b && addCode(iError,"error exit")>0;
			b=b && addCode(iError,"error mail")>0;

			iIst = b ? Ver(3,1,2):iIst;
		}

		if(b && iIst < Ver(3,1,3) && iSoll>=Ver(3,1,3))	// 30. 4.2003
		{
			g.fixInfo("Versionsupdate 3.1.3");
			int iOLE = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='OLE'");
			int iGet = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			b=b && addCode(iOLE,"OLE print2")>0;
			b=b && addCode(iGet,"add Date-String")>0;
			b=b && addCode(iGet,"add number-String")>0;
			b=b && addCode(iGet,"add dReg-String")>0;
			iIst = b ? Ver(3,1,3):iIst;
		}

		if(b && iIst < Ver(3,1,4) && iSoll>=Ver(3,1,4))	//  6. 5.2003
		{
			g.fixInfo("Versionsupdate 3.1.4");
			Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct filter,begriff.kennung,begriff.bits,begriff.aic_begriff from spalte,abfrage join begriff where filter=abfrage.aic_abfrage and mod(begriff.bits,536870912)&268435456=0",true);
			for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
			{
				p.add("bits",Tab.getL("bits")+268435456);
				p.update("Begriff",Tab.getI("aic_begriff"));
			}
			iIst = b ? Ver(3,1,4):iIst;
		}

		if(b && iIst < Ver(3,2,2) && iSoll>=Ver(3,2,2))	//  16. 5.2003
		{
			g.fixInfo("Versionsupdate 3.2.2");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
			b=b && p.exec("update code set kennung='prev n month',aic_begriffgruppe="+iGruppe+" where kennung='Anweisung13'");
			b=b && addCode(iGruppe,"prev n weeks")>0;

			b=b && p.exec("alter table BENUTZERGRUPPE ADD AIC_STAMM integer null");
			b=b && p.exec("alter table BENUTZERGRUPPE add foreign key FK_BENUTZER_ABSCHLUSS_STAMM (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");

			iIst = b ? Ver(3,2,2):iIst;
		}

		if(b && iIst < Ver(3,2,3) && iSoll>=Ver(3,2,3))	//  28. 5.2003
		{
			g.fixInfo("Versionsupdate 3.2.3");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			p.clear();
			b=b && addBegriff(iGruppe,"LoeschBenutzer")>0;
			b=b && p.exec("alter table stammtyp add STD_FORMULAR bit NULL");

			b=b && p.exec("alter table BERECHTIGUNG ADD BITS integer null");
			b=b && p.exec("update BERECHTIGUNG set gruppe=0 where gruppe is null");
			b=b && p.exec("update BERECHTIGUNG set BITS=gruppe");
			b=b && p.exec("alter table BERECHTIGUNG DROP anlegen");
			b=b && p.exec("alter table BERECHTIGUNG DROP aendern");
			b=b && p.exec("alter table BERECHTIGUNG DROP loeschen");
			b=b && p.exec("alter table BERECHTIGUNG DROP gruppe");


			iIst = b ? Ver(3,2,3):iIst;
		}

		if(b && iIst < Ver(3,2,4) && iSoll>=Ver(3,2,4))	//  11. 6.2003
		{
			g.fixInfo("Versionsupdate 3.2.4");
			int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
			int iConvert = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='convert'");

			b=b && addCode(iBed,"pos model")>0;
			b=b && addCode(iConvert,"model to Aic")>0;
			b=b && addCode(iConvert,"Aic to model")>0;

			iIst = b ? Ver(3,2,4):iIst;
		}

		if(b && iIst < Ver(3,2,5) && iSoll>=Ver(3,2,5))	//  16. 6.2003
		{
			g.fixInfo("Versionsupdate 3.2.5");
			String s=" where kennung in ('close Terminal','connect Terminal','reset Terminal','set Terminaltime','< period','> period','destroy all','destroy row','or','between')";
			g.fixInfo(g.exec("delete from Befehl from Befehl join code"+s)+" Befehle gelöscht!");
			g.fixInfo(g.exec("delete from code"+s)+" Codes gelöscht!");

			iIst = b ? Ver(3,2,5):iIst;
		}

		if(b && iIst < Ver(3,2,6) && iSoll>=Ver(3,2,6))	//  16. 6.2003
		{
			g.fixInfo("Versionsupdate 3.2.6");
			String s=" where b.kennung in ('BewZeitraum','StringZahl','BewDatumPlus','BewVonBisPlus','BewDatumAuto','BewComputer','BewBenutzer') and g.kennung='Datentyp'";
			g.fixInfo(g.exec("delete from begriff from begriff b join begriffgruppe g"+s)+" Datentypen gelöscht!");

			iIst = b ? Ver(3,2,6):iIst;
		}

		if(b && iIst < Ver(3,3,1) && iSoll>=Ver(3,3,1))	//  17. 6.2003
		{
			g.fixInfo("Versionsupdate 3.3.1");

			b=b && p.exec("alter table EIGENSCHAFT ADD BITS integer null");
			b=b && p.exec("update EIGENSCHAFT set eindeutig=0 where eindeutig is null");
			b=b && p.exec("update EIGENSCHAFT set always=0 where always is null");
			b=b && p.exec("update EIGENSCHAFT set nur_modell=0 where nur_modell is null");
			b=b && p.exec("update EIGENSCHAFT set keinautodate=0 where keinautodate is null");
			b=b && p.exec("update EIGENSCHAFT set Jeder=0 where Jeder is null");
			b=b && p.exec("update EIGENSCHAFT set BITS=eindeutig+always*2+nur_modell*4+keinautodate*8+Jeder*268435456");
			b=b && p.exec("alter table EIGENSCHAFT DROP eindeutig");
			b=b && p.exec("alter table EIGENSCHAFT DROP always");
			b=b && p.exec("alter table EIGENSCHAFT DROP nur_modell");
			b=b && p.exec("alter table EIGENSCHAFT DROP keinautodate");
			b=b && p.exec("alter table EIGENSCHAFT DROP Jeder");

			b=b && p.exec("alter table EIGENSCHAFT add AIC_MANDANT integer null");
			b=b && p.exec("alter table EIGENSCHAFT add foreign key FK_EIGENSCHAFT_MANDANT (AIC_MANDANT) references MANDANT (AIC_MANDANT) on update restrict on delete restrict");
			b=b && p.exec("update EIGENSCHAFT set aic_mandant=1");

			iIst = b ? Ver(3,3,1):iIst;
		}

		if(b && iIst < Ver(3,3,2) && iSoll>=Ver(3,3,2))	//  18. 6.2003
		{
			g.fixInfo("Versionsupdate 3.3.2");

			int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
			int iPlatz = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");

			b=b && addCode(iAnw,"set Sync-Stamm")>0;
			b=b && addCode(iPlatz,"JokerStt")>0;

			b=b && p.exec("create table DRUCKER("+
				"AIC_DRUCKER      integer               not null default AUTOINCREMENT,"+
				"KENNUNG          char(20)                      not null,"+
				"BITS             integer                       not null,"+
				"H                integer                       not null,"+
				"V                integer                       not null,"+
				"primary key (AIC_DRUCKER))");

			b=b && p.exec("create table RASTER("+
				"AIC_RASTER       integer              not null default AUTOINCREMENT,"+
				"KENNUNG          char(20)                     not null,"+
				"AIC_SCHRIFT      integer                       null,"+
				"SCH_AIC_SCHRIFT  integer                       null,"+
				"AIC_FARBE        integer                       null,"+
				"FAR_AIC_FARBE    integer                       null,"+
				"FAR2_AIC_FARBE   integer                       null,"+
				"FAR3_AIC_FARBE   integer                       null,"+
				"BITS             integer                      not null,"+
				"primary key (AIC_RASTER))");

			b=b && p.exec("alter table RASTER add foreign key FK_RASTER_TEXT_SCHR_SCHRIFT (AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
			b=b && p.exec("alter table RASTER add foreign key FK_RASTER_TITEL_SCH_SCHRIFT (SCH_AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
			b=b && p.exec("alter table RASTER add foreign key FK_RASTER_HINTERGRU_FARBE (AIC_FARBE) references FARBE (AIC_FARBE) on update restrict on delete restrict");
			b=b && p.exec("alter table RASTER add foreign key FK_RASTER_TITELFARB_FARBE (FAR_AIC_FARBE) references FARBE (AIC_FARBE) on update restrict on delete restrict");
			b=b && p.exec("alter table RASTER add foreign key FK_RASTER_SPERRFARB_FARBE (FAR2_AIC_FARBE) references FARBE (AIC_FARBE) on update restrict on delete restrict");
			b=b && p.exec("alter table RASTER add foreign key FK_RASTER_SUMMENFAR_FARBE (FAR3_AIC_FARBE) references FARBE (AIC_FARBE) on update restrict on delete restrict");

			b=b && p.exec("alter table ABSCHNITT ADD AIC_RASTER integer null");
			b=b && p.exec("alter table ABSCHNITT add foreign key FK_ABSCHNIT_RELATION__RASTER (AIC_RASTER) references RASTER (AIC_RASTER) on update restrict on delete restrict");

			b=b && p.exec("insert into tabellenname(kennung) values ('RASTER')");
			b=b && p.exec("insert into tabellenname(kennung) values ('DRUCKER')");

			iIst = b ? Ver(3,3,2):iIst;
		}

		if(b && iIst < Ver(3,3,3) && iSoll>=Ver(3,3,3))	//  1. 8.2003
		{
			g.fixInfo("Versionsupdate 3.3.3");

			b=b && p.exec("create view stringXview as select aic_stamm,aic_eigenschaft,aic_bew_pool,spalte_double,gultig_von,(case"+
				" when spalte_double<11 then (select spalte_stringsehrkurz from daten_StringSehrKurz where aic_daten=stammpool.aic_daten)"+
				" when spalte_double<31 then (select spalte_stringkurz from daten_StringKurz where aic_daten=stammpool.aic_daten)"+
				" when spalte_double<61 then (select spalte_string60 from daten_String60 where aic_daten=stammpool.aic_daten)"+
				" when spalte_double<256 then (select spalte_stringLang from daten_StringLang where aic_daten=stammpool.aic_daten)"+
				" else (select spalte_text from daten_text where aic_daten=stammpool.aic_daten) end) StringX from stammpool join daten where spalte_double>0"+
				" and pro2_aic_protokoll is null and (bis is null or gultig_von is null or gultig_von<bis) and (gueltig_bis is null or gueltig_bis>=bis)");

			iIst = b ? Ver(3,3,3):iIst;
		}

		if(b && iIst < Ver(3,3,4) && iSoll>=Ver(3,3,4))	//  5. 8.2003
		{
			g.fixInfo("Versionsupdate 3.3.4");

			p.add("Kennung","Operation");
			p.add("code",1);
			int iGruppe = p.insert("begriffgruppe",true);
			if (b && iGruppe>0)
			{
				b=b && addCode(iGruppe,"add")>0;
				b=b && addCode(iGruppe,"sub")>0;
				b=b && addCode(iGruppe,"mul")>0;
				b=b && addCode(iGruppe,"div")>0;
				b=b && addCode(iGruppe,"prev")>0;
			}

			b=b && p.exec("create table SPALTE_BERECHNUNG("+
				"AIC_SPALTE      integer               not null,"+
				"POS             integer               not null,"+
				"AIC_CODE        integer               not null,"+
				"SPA_AIC_SPALTE  integer               null    ,"+
				"WERT            double                null    ,"+
				"primary key (AIC_SPALTE, POS))");

			b=b && p.exec("alter table SPALTE_BERECHNUNG add foreign key FK_SPALTE_B_ERGEBNISF_SPALTE (AIC_SPALTE) references SPALTE (AIC_SPALTE) on update restrict on delete restrict");
			b=b && p.exec("alter table SPALTE_BERECHNUNG add foreign key FK_SPALTE_B_OPERATOR_CODE (AIC_CODE) references CODE (AIC_CODE) on update restrict on delete restrict");
			b=b && p.exec("alter table SPALTE_BERECHNUNG add foreign key FK_SPALTE_B_OPERATION_SPALTE (SPA_AIC_SPALTE) references SPALTE (AIC_SPALTE) on update restrict on delete restrict");

			iIst = b ? Ver(3,3,4):iIst;
		}

		if(b && iIst < Ver(3,3,5) && iSoll>=Ver(3,3,5))	//  14. 8.2003
		{
			g.fixInfo("Versionsupdate 3.3.5");

			Tabellenspeicher Tab= new Tabellenspeicher(g,"select aic_begriff,bits from begriff where bits<0",true);
			for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
				p.exec("update begriff set bits="+(Tab.getL("bits")+2147483648l)+" where aic_begriff="+Tab.getI("aic_begriff"));
			if (!Tab.isEmpty())
				g.fixInfo(Tab.getAnzahlElemente(null)+" negative bits gelöscht!");

			Tab= new Tabellenspeicher(g,"select aic_begriff,bits from begriff where ((bits/1024/1024/1024) & 32)>0",true);
			for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
				p.exec("update begriff set bits="+(Tab.getL("bits")-34359738368l)+" where aic_begriff="+Tab.getI("aic_begriff"));
			if (!Tab.isEmpty())
				g.fixInfo(Tab.getAnzahlElemente(null)+" Joker-bits gelöscht!");

			Tab= new Tabellenspeicher(g,"select distinct begriff.aic_begriff,begriff.bits from spalte_zuordnung join spalte join abfrage join begriff",true);
			for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
				p.exec("update begriff set bits="+(Tab.getL("bits")+1152921504606846976l)+" where aic_begriff="+Tab.getI("aic_begriff"));
			if (!Tab.isEmpty())
				g.fixInfo(Tab.getAnzahlElemente(null)+" Gruppen-bits gesetzt!");

			Tab= new Tabellenspeicher(g,"select distinct begriff.aic_begriff,begriff.bits from spalte_berechnung b join spalte on b.aic_spalte=spalte.aic_spalte join abfrage join begriff",true);
			for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
				p.exec("update begriff set bits="+(Tab.getL("bits")+2305843009213693952l)+" where aic_begriff="+Tab.getI("aic_begriff"));
			if (!Tab.isEmpty())
				g.fixInfo(Tab.getAnzahlElemente(null)+" Calc-bits gesetzt!");

			iIst = b ? Ver(3,3,5):iIst;
		}

		if(b && iIst < Ver(3,3,6) && iSoll>=Ver(3,3,6))	//  22. 8.2003
		{
			g.fixInfo("Versionsupdate 3.3.6");
			int iSum=p.getInteger("select aic_code from code join begriffgruppe g where code.kennung='sum' and g.kennung='Ergebnisart'");
			g.fixInfo(g.exec("update spalte set cod_aic_code="+iSum+" where bits&4=4 and cod_aic_code is null")+" auf Summe gesetzt!");

			g.fixInfo("- neue Datentypen");
			int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
			if (b && iGruppe>0)
			{
				addBegriff(iGruppe,"CalcMass");
				addBegriff(iGruppe,"CalcWaehrung");
				addBegriff(iGruppe,"CalcDauer");
			}

			int iOp = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
			if (b && iOp>0)
			{
				addCode(iOp,"age");
			}

			iIst = b ? Ver(3,3,6):iIst;
		}

		if(b && iIst < Ver(3,3,7) && iSoll>=Ver(3,3,7))	//  26. 8.2003
		{
			g.fixInfo("Versionsupdate 3.3.7");
			b=b && p.exec("alter table BEGRIFF add COMBO bit null");
			iIst = b ? Ver(3,3,7):iIst;
		}

                if(b && iIst < Ver(3,3,8) && iSoll>=Ver(3,3,8))	//  15. 9.2003
                {
                        g.fixInfo("Versionsupdate 3.3.8");
                        int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                        int iHolen = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
			if (iAnw>0 && iHolen>0)
                        {
                          addCode(iAnw,"fix group");
                          addCode(iHolen,"sum group");
                        }

                        iIst = b ? Ver(3,3,8):iIst;
                }

                if(b && iIst < Ver(3,4,1) && iSoll>=Ver(3,4,1))	//  26. 9.2003
                {
                  g.fixInfo("Versionsupdate 3.4.1");
                  b=b && p.exec("alter table BEGRIFF MODIFY KENNUNG char(40)");
                  b=b && p.exec("alter table BEGRIFF add DEFBEZEICHNUNG char(60) null");
                  int iTabBeg=p.getInteger("select aic_tabellenname from tabellenname where kennung='Begriff'");
                  b=b && p.exec("update BEGRIFF set DEFBEZEICHNUNG=isnull((select bezeichnung from bezeichnung where aic_sprache=1 and aic_tabellenname=6 and aic_fremd=aic_begriff),kennung)");

                  g.fixInfo(g.exec("delete from bezeichnung from begriff,bezeichnung where aic_sprache=1 and aic_tabellenname=6 and aic_fremd=aic_begriff and bezeichnung=defbezeichnung")+" Bezeichnungen gelöscht");

                  iIst = b ? Ver(3,4,1):iIst;
                }

                if(b && iIst < Ver(3,5,1) && iSoll>=Ver(3,5,1))	//  31.10.2003
                {
                  g.fixInfo("Versionsupdate 3.5.1");
                  b=b && p.exec("update bew_begriff join begriff join modell set bits=bits&1+2");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Tabellentyp'");
                  b=b && iGruppe>0;
                  if (b)
                    b=addCode(iGruppe,"Gruppe")>0;

                  iIst = b ? Ver(3,5,1):iIst;
                }

                if(b && iIst < Ver(3,5,2) && iSoll>=Ver(3,5,2))	//   3.11.2003
                {
                  g.fixInfo("Versionsupdate 3.5.2");
                  b=b && p.exec("alter table benutzer MODIFY passwort char(40)");
                  b=b && p.exec("alter table mandant MODIFY passwort char(40)");
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_benutzer,passwort from benutzer",true);
                  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                    g.exec("Update Benutzer set passwort='"+g.PasswordConvert(Tab.getS("Passwort"))+"' where aic_benutzer="+Tab.getI("aic_benutzer"));
                  Tab=new Tabellenspeicher(g,"select aic_mandant,passwort from mandant",true);
                  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                    g.exec("Update Mandant set passwort='"+g.PasswordConvert(Tab.getS("Passwort"))+"' where aic_Mandant="+Tab.getI("aic_Mandant"));

                  iIst = b ? Ver(3,5,2):iIst;
                }

                if(b && iIst < Ver(3,5,3) && iSoll>=Ver(3,5,3))	//   3.11.2003
                {
                  g.fixInfo("Versionsupdate 3.5.3");

                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='terminal'");
                  b=b && iGruppe>0;
                  if (b)
                  {
                    b=b && addCode(iGruppe,"set Terminalopen")>0;
                    b=b && addCode(iGruppe,"set Terminalholiday")>0;
                  }

                  iIst = b ? Ver(3,5,3):iIst;
                }

                if(b && iIst < Ver(3,5,4) && iSoll>=Ver(3,5,4))	//   3.11.2003
                {
                  g.fixInfo("Versionsupdate 3.5.4");
                  g.fixInfo(g.exec("delete from stammtyp_zuordnung from stammtyp_zuordnung join eigenschaft join begriff where begriff.kennung in ('Timestamp','SysAic','Aic','Benutzer','Anlage','Mandant','entfernt','LoeschBenutzer','CalcField','CalcWaehrung','CalcMass','CalcDauer')")+" von Stammtyp_zuordnung gelöscht!");
                  g.fixInfo(g.exec("delete from bew_zuordnung from bew_zuordnung join eigenschaft join begriff where begriff.kennung in ('Timestamp','SysAic','Aic','Benutzer','Anlage','Mandant','entfernt','LoeschBenutzer','CalcField','CalcWaehrung','CalcMass','CalcDauer')")+" von Bew_zuordnung gelöscht!");

                  iIst = b ? Ver(3,5,4):iIst;
                }

                if(b && iIst < Ver(3,5,5) && iSoll>=Ver(3,5,5))	//   4.11.2003
                {
                  g.fixInfo("Versionsupdate 3.5.5");

                  b=b && p.exec("create view stringXview2 as select aic_stamm,aic_eigenschaft,aic_bew_pool,spalte_double,gultig_von,(case"+
                                " when spalte_double<11 then (select spalte_stringsehrkurz from daten_StringSehrKurz where aic_daten=stammpool.aic_daten)"+
                                " when spalte_double<31 then (select spalte_stringkurz from daten_StringKurz where aic_daten=stammpool.aic_daten)"+
                                " when spalte_double<61 then (select spalte_string60 from daten_String60 where aic_daten=stammpool.aic_daten)"+
                                " when spalte_double<256 then (select spalte_stringLang from daten_StringLang where aic_daten=stammpool.aic_daten)"+
                                " else (select spalte_text from daten_text where aic_daten=stammpool.aic_daten) end) StringX from stammpool join daten where spalte_double>0"+
                                " and pro2_aic_protokoll is null and gueltig_bis is null");


                  iIst = b ? Ver(3,5,5):iIst;
                }

                if(b && iIst < Ver(3,6,1) && iSoll>=Ver(3,6,1))	//  17.11.2003
                {
                  g.fixInfo("Versionsupdate 3.6.1");
                  b=b && p.exec("update farbe set kennung='.'+kennung where kennung in ('holiday','today','select','edit','new','delete')");
                  b=b && p.exec("update schrift set kennung='.'+kennung where kennung in ('Standard','Titel','Bezeichnung','Button')");

                  g.fixInfo(g.exec("delete from abfrage where (select count(*) from spalte where aic_abfrage=abfrage.aic_abfrage)=0 and (select count(*) from Bedingung where aic_abfrage=abfrage.aic_abfrage)=0")+" leere Abfragen gelöscht");
                  g.fixInfo(g.exec("delete from begriff from begriffgruppe g join begriff left outer join abfrage where g.kennung='Abfrage' and aic_abfrage is null")+" leere Abfrage-Begriffe gelöscht");
                  b=b && p.exec("update begriff join abfrage set bits=bits+576460752303423488 where kennung is null"); // HS setzen
                  if (b)
                  {
                    int cstFilter= 0x10000000;
                    Tabellenspeicher Tab=new Tabellenspeicher(g,"select begriff.aic_begriff,bits from begriff join abfrage where (select count(*) from spalte where aic_abfrage=abfrage.aic_abfrage)=0 and kennung is null",true);
                    for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                      if ((Tab.getL("bits")&cstFilter)==0)
                      {
                        g.fixInfo("Ändere Abfrage "+Tab.getI("aic_Begriff")+" zu Filter");
                        p.exec("update begriff set bits=" + (Tab.getL("Bits") + cstFilter) + " where aic_begriff=" + Tab.getI("aic_Begriff"));
                      }
                  }
                  iIst = b ? Ver(3,6,1):iIst;
                }

                if(b && iIst < Ver(3,6,2) && iSoll>=Ver(3,6,2))	//  19.11.2003
                {
                  g.fixInfo("Versionsupdate 3.6.2");
                  b=b && p.exec("alter table BEGRIFF drop foreign key FK_BEGRIFF_HINTERGRU_FARBE");
                  b=b && p.exec("alter table BEGRIFF DROP AIC_Farbe");

                  b=b && p.exec("alter table RASTER drop foreign key FK_RASTER_HINTERGRU_FARBE");
                  b=b && p.exec("alter table RASTER drop foreign key FK_RASTER_TITELFARB_FARBE");
                  b=b && p.exec("alter table RASTER drop foreign key FK_RASTER_SPERRFARB_FARBE");
                  b=b && p.exec("alter table RASTER drop foreign key FK_RASTER_SUMMENFAR_FARBE");
                  b=b && p.exec("alter table RASTER DROP AIC_Farbe");
                  b=b && p.exec("alter table RASTER DROP FAR_AIC_FARBE");
                  b=b && p.exec("alter table RASTER DROP FAR2_AIC_FARBE");
                  b=b && p.exec("alter table RASTER DROP FAR3_AIC_FARBE");

                  b=b && p.exec("alter table RASTER add SCH2_AIC_SCHRIFT integer null");
                  b=b && p.exec("alter table RASTER add SCH3_AIC_SCHRIFT integer null");
                  b=b && p.exec("alter table RASTER add SCH4_AIC_SCHRIFT integer null");
                  b=b && p.exec("alter table RASTER add SCH5_AIC_SCHRIFT integer null");
                  b=b && p.exec("alter table RASTER add SCH6_AIC_SCHRIFT integer null");
                  b=b && p.exec("alter table RASTER add foreign key FK_RASTER_UEBER_SCH_SCHRIFT (SCH2_AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
                  b=b && p.exec("alter table RASTER add foreign key FK_RASTER_SPERR_SCH_SCHRIFT (SCH3_AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
                  b=b && p.exec("alter table RASTER add foreign key FK_RASTER_ZWISCHEN_SCH_SCHRIFT (SCH4_AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
                  b=b && p.exec("alter table RASTER add foreign key FK_RASTER_SUMME_SCH_SCHRIFT (SCH5_AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
                  b=b && p.exec("alter table RASTER add foreign key FK_RASTER_NEGATIV_SCH_SCHRIFT (SCH6_AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");

                  b=b && p.exec("update RASTER set aic_schrift=sch_aic_schrift,sch_aic_schrift=aic_schrift");

                  b=b && p.exec("alter table SCHRIFT add FAR_AIC_FARBE integer null");
                  b=b && p.exec("alter table SCHRIFT add foreign key FK_SCHRIFT_HINTERGRU_FARBE (FAR_AIC_FARBE) references FARBE (AIC_FARBE) on update restrict on delete restrict");

                  iIst = b ? Ver(3,6,2):iIst;
                }

                if(b && iIst < Ver(3,6,3) && iSoll>=Ver(3,6,3))	//  26.11.2003
                {
                  g.fixInfo("Versionsupdate 3.6.3");
                  g.fixInfo(g.exec("delete from parameter from parameter join nebengruppe where kennung in ('Last','Elemente','Spalten')")+" Parameter gelöscht");
                  iIst = b ? Ver(3,6,3):iIst;
                }

                if(b && iIst < Ver(3,6,4) && iSoll>=Ver(3,6,4))	//   1.12.2003
                {
                  g.fixInfo("Versionsupdate 3.6.4");
                  b=b && p.exec("create table DEFIMPORT("+
                                "AIC_DEFIMPORT  integer               not null default AUTOINCREMENT,"+
                                "AIC_COMPUTER   integer               not null,"+
                                "ANFANG         DateTime                  null,"+
                                "ENDE           DateTime                  null,"+
                                "STATUS         integer                   null,"+
                                "FILENAME       char(100)                 null,"+
                                "primary key (AIC_DEFIMPORT))");

                  b=b && p.exec("alter table DEFIMPORT add foreign key FK_DEFIMPOR_IMPORTCOM_COMPUTER (AIC_COMPUTER) references COMPUTER (AIC_COMPUTER) on update restrict on delete restrict");

                  iIst = b ? Ver(3,6,4):iIst;
                }

                if(b && iIst < Ver(3,6,5) && iSoll>=Ver(3,6,5))	//   3.12.2003
                {
                  g.fixInfo("Versionsupdate 3.6.5");
                  int iFormat=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Format'");
                  Vector Vec=SQL.getSVector("select distinct format from eigenschaft where format is not null",g);
                  SQL.addSVector(Vec,"select distinct format from spalte where format is not null",g);
                  for(int i=0;i<Vec.size();i++)
                  {
                    addBegriff(iFormat,(String)Vec.elementAt(i));
                  }

                  iIst = b ? Ver(3,6,5):iIst;
                }

                if(b && iIst < Ver(3,7,1) && iSoll>=Ver(3,7,1))	//   29.12.2003
                {
                  g.fixInfo("Versionsupdate 3.7.1");
                  b=b && p.exec("alter table BENUTZERGRUPPE ADD BITS integer null");
                  b=b && p.exec("update BENUTZERGRUPPE set HISTORY=0 where HISTORY is null");
                  b=b && p.exec("update BENUTZERGRUPPE set ABFRAGE=0 where ABFRAGE is null");
                  b=b && p.exec("update BENUTZERGRUPPE set READONLY=0 where READONLY is null");
                  b=b && p.exec("update BENUTZERGRUPPE set BITS=HISTORY+ABFRAGE*2+READONLY*4");
                  b=b && p.exec("alter table BENUTZERGRUPPE DROP HISTORY");
                  b=b && p.exec("alter table BENUTZERGRUPPE DROP ABFRAGE");
                  b=b && p.exec("alter table BENUTZERGRUPPE DROP READONLY");

                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  if (b && iGruppe>0)
                    addBegriff(iGruppe,"Ampel");

                  p.add("Kennung","light");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  iGruppe = p.insert("begriffgruppe",true);
                  if (b && iGruppe>0)
                  {
                    b=b && addCode(iGruppe,"clear light")>0;
                    b=b && addCode(iGruppe,"red light")>0;
                    b=b && addCode(iGruppe,"yellow light")>0;
                    b=b && addCode(iGruppe,"green light")>0;
                  }

                  iIst = b ? Ver(3,7,1):iIst;
                }

                if(b && iIst < Ver(3,7,2) && iSoll>=Ver(3,7,2))	//    7. 1.2004
                {
                  g.fixInfo("Versionsupdate 3.7.2");
                  b=b && p.exec("alter table FORMULAR add AIC_EIGENSCHAFT  integer null");
                  b=b && p.exec("alter table FORMULAR add foreign key FK_FORMULAR_AMPEL_DES_EIGENSCH (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");

                  iIst = b ? Ver(3,7,2):iIst;
                }

                if(b && iIst < Ver(3,8,1) && iSoll>=Ver(3,8,1))	//   10. 2.2004
                {
                  g.fixInfo("Versionsupdate 3.8.1");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  if (b && iGruppe>0)
                  {
                    b=b && addCode(iGruppe,"init pos")>0;
                    b=b && addCode(iGruppe,"init comma")>0;
                    b=b && addCode(iGruppe,"init semicolon")>0;
                    b=b && addCode(iGruppe,"init separator")>0;
                  }
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  if (b && iGruppe>0)
                  {
                    b=b && addCode(iGruppe,"pos true")>0;
                    b=b && addCode(iGruppe,"pos null")>0;
                    b=b && addCode(iGruppe,"get Stichtag")>0;
                  }
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  if (b && iGruppe>0)
                  {
                    b=b && addCode(iGruppe,"set year")>0;
                  }
                  iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  if (b && iGruppe>0)
                  {
                    b=b && addCode(iGruppe,"question")>0;
                    b=b && addCode(iGruppe,"> von")>0;
                  }

                  iIst = b ? Ver(3,8,1):iIst;
                }
                if(b && iIst < Ver(3,8,2) && iSoll>=Ver(3,8,2))	//   19. 2.2004
                {
                  g.fixInfo("Versionsupdate 3.8.2");
                  b=b && p.exec("delete from code where kennung in ('and','notice date','save act date')");
                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  if (b && iGruppe>0)
                  {
                    b=b && addCode(iGruppe,"< bis")>0;
                    b=b && addCode(iGruppe,"between")>0;
                    b=b && addCode(iGruppe,"pos real")>0;
                  }
                  b=b && p.exec("update code set aic_begriffgruppe="+iGruppe+" where Kennung in ('pos true','pos null')");
                  b=b && p.exec("update code set kennung='set table' where Kennung='Anweisung14'");
                  b=b && p.exec("update code set kennung='copy to' where Kennung='Anweisung15'");
                  b=b && p.exec("alter table BEGRIFFGRUPPE DROP MODELLBEFEHL");

                  iIst = b ? Ver(3,8,2):iIst;
                }
                if(b && iIst < Ver(3,8,3) && iSoll>=Ver(3,8,3))	//   21. 2.2004
                {
                   g.fixInfo("Versionsupdate 3.8.3");
                   int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                   b=b && iGruppe>0;
                   if (b)
                   {
                     b = b && addCode(iGruppe, "to VecAic") > 0;
                     b  =b && addCode(iGruppe, "clear with")>0;
                   }
                   iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                   if (b && iGruppe>0)
                   {
                     b=b && addCode(iGruppe,"get month vb")>0;
                   }

                   iIst = b ? Ver(3,8,3):iIst;
                }
                if(b && iIst < Ver(3,9,1) && iSoll>=Ver(3,9,1))	//   11. 3.2004
                {
                  g.fixInfo("Versionsupdate 3.9.1");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
                  b=b && p.exec("update code set kennung='*ich*' where kennung='ich' and aic_begriffgruppe="+iGruppe);
                  b=b && p.exec("update code set kennung='*meinStamm*' where kennung='meinStamm' and aic_begriffgruppe="+iGruppe);
                  b=b && p.exec("update code set kennung='*Stichtag*' where kennung='Stichtag' and aic_begriffgruppe="+iGruppe);
                  b=b && p.exec("update code set kennung='*Joker*' where kennung='Joker' and aic_begriffgruppe="+iGruppe);
                  b=b && p.exec("update code set kennung='*StringJoker*' where kennung='StringJoker' and aic_begriffgruppe="+iGruppe);
                  b=b && p.exec("update code set kennung='*JokerStt*' where kennung='JokerStt' and aic_begriffgruppe="+iGruppe);
                  b=b && addCode(iGruppe,"*JokerVec*")>0;
                  g.fixInfo(g.exec("update bedingung set vergleichswert='*ich*' where vergleichswert='ich'")+" ich-Bedingungen geändert");
                  g.fixInfo(g.exec("update bedingung set vergleichswert='*meinStamm*' where vergleichswert='meinStamm'")+" meinStamm-Bedingungen geändert");
                  g.fixInfo(g.exec("update bedingung set vergleichswert='*Stichtag*' where vergleichswert='Stichtag'")+" Stichtag-Bedingungen geändert");
                  g.fixInfo(g.exec("update bedingung set vergleichswert='*Joker*' where vergleichswert='Joker'")+" Joker-Bedingungen geändert");
                  g.fixInfo(g.exec("update bedingung set vergleichswert='*StringJoker*' where vergleichswert='StringJoker'")+" StringJoker-Bedingungen geändert");
                  g.fixInfo(g.exec("update bedingung set vergleichswert='*JokerStt*' where vergleichswert='JokerStt'")+" JokerStt-Bedingungen geändert");
                  iIst = b ? Ver(3,9,1):iIst;
                }

                if(b && iIst < Ver(3,9,2) && iSoll>=Ver(3,9,2))	//   12. 3.2004
                {
                  g.fixInfo("Versionsupdate 3.9.2");

                  b=b && p.exec("create view poolview3 as select a=0");
                  b=b && p.exec("create view stringXview3 as select b=0");

                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  if (b && iGruppe>0)
                     b = addCode(iGruppe, "set Sync-Vec") > 0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  if (b && iGruppe>0)
                     b = addCode(iGruppe, "get Sync-Vec") > 0;

                  iIst = b ? Ver(3,9,2):iIst;
                }

                if(b && iIst < Ver(3,9,3) && iSoll>=Ver(3,9,3))	//   23. 3.2004
                {
                  g.fixInfo("Versionsupdate 3.9.3");
                  int iOp = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                  if (b && iOp>0)
                  {
                    addCode(iOp,"proz");
                    addCode(iOp,"min");
                    addCode(iOp,"max");
                  }
                  int iAnz=g.exec("update eigenschaft join begriff set feldlaenge=-1 where begriff.kennung in ('Double','BewZahl') and feldlaenge is null");
                  if(iAnz>0)
                    g.fixInfo(iAnz+" Zahl-Eigenschaften auf beliebige Nachkommastellen");
                  iAnz=g.exec("update eigenschaft join begriff set feldlaenge=2 where begriff.kennung like ('%Waehrung') and feldlaenge is null");
                  if(iAnz>0)
                    g.fixInfo(iAnz+" Währungs-Eigenschaften auf 2 Nachkommastellen");
                  iAnz=g.exec("update tabellenname set fixe_sprache=1 where fixe_sprache is null and kennung in('Mandant','Benutzergruppe','Testtypen','Version','Drucker','Befehl','Komponente')");
                  if(iAnz>0)
                    g.fixInfo(iAnz+" Tabellen auf fixe Sprache!");

                  iIst = b ? Ver(3,9,3):iIst;
                }

                if(b && iIst < Ver(3,9,4) && iSoll>=Ver(3,9,4))	//   24. 3.2004
                {
                  g.fixInfo("Versionsupdate 3.9.4");
                  int iAnz=g.exec("update spalte set bits=bits&0x5fbfff7f where bits&0x20400080>0");
                  g.fixInfo(iAnz+" Spalten-bits zurückgesetzt");

                  iAnz=g.exec("delete from parameter from parameter join nebengruppe n where n.kennung='Fenster'");
                  g.fixInfo(iAnz+" alte Fenster-Parameter gelöscht");

                  b=b && p.exec("update computer set info=0 where info is null");
                  b=b && p.exec("alter table computer add CBITS integer NULL");
                  g.exec("update computer c left outer join parameter p join nebengruppe n set cbits=int1+int2*2+int3*4+int4*8+info*32"+
                         " where n.kennung='Infos' and int1<2");
                  g.exec("update computer c left outer join parameter p join nebengruppe n set cbits=(select int2 from parameter join nebengruppe n where n.kennung='Infos' and int1=2 and aic_computer=c.aic_computer)+info*32"+
                         " where n.kennung='Infos' and int1=2");
                  g.exec("update computer c set cbits=info*32 where (select count(*) from parameter join nebengruppe where kennung='Infos' and aic_computer=c.aic_computer)=0");
                  g.exec("delete from parameter from parameter join nebengruppe n where n.kennung='Infos'");
                  b=b && p.exec("alter table computer DROP Info");

                  b=b && p.exec("alter table begriff add HOTKEY char(1) NULL");

                  iIst = b ? Ver(3,9,4):iIst;
                }

                if(b && iIst < Ver(3,9,5) && iSoll>=Ver(3,9,5))	//   31. 3.2004
                {
                  g.fixInfo("Versionsupdate 3.9.5");

                  b=b && p.exec("create table HAUPTSCHIRM("+
                                "AIC_HAUPTSCHIRM integer not null default AUTOINCREMENT,"+
                                "AIC_BEGRIFF integer not null,"+
                                "AIC_BENUTZER integer null,"+
                                "AIC_ABFRAGE integer null,"+
                                "KENNUNG char(20) null,"+
                                "BITS integer null,"+
                                "primary key(AIC_HAUPTSCHIRM))");

                  b=b && p.exec("create table ANSICHT("+
                                "AIC_ANSICHT integer not null default AUTOINCREMENT,"+
                                "AIC_HAUPTSCHIRM integer not null,"+
                                "AIC_ABFRAGE integer null,"+
                                "INT1 integer null,"+
                                "INT2 integer null,"+
                                "INT3 integer null,"+
                                "INT4 integer null,"+
                                "primary key(AIC_ANSICHT))");

                  b=b && p.exec("alter table HAUPTSCHIRM add foreign key FK_HAUPTSCH_APPL_BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");
                  b=b && p.exec("alter table HAUPTSCHIRM add foreign key FK_HAUPTSCH_BENUTZER__BENUTZER (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER) on update restrict on delete restrict");
                  b=b && p.exec("alter table HAUPTSCHIRM add foreign key FK_HAUPTSCH_SPALTEN_D_ABFRAGE (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");
                  b=b && p.exec("alter table ANSICHT add foreign key FK_ANSICHT_FILTER_DE_HAUPTSCH (AIC_HAUPTSCHIRM) references HAUPTSCHIRM (AIC_HAUPTSCHIRM) on update restrict on delete restrict");
                  b=b && p.exec("alter table ANSICHT add foreign key FK_ANSICHT_FILTER_DE_ABFRAGE (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");

                  b=b && p.exec("insert into tabellenname(kennung) values ('HAUPTSCHIRM')");

                  iIst = b ? Ver(3,9,5):iIst;
                }

                if(b && iIst < Ver(3,9,6) && iSoll>=Ver(3,9,6))	//   9. 4.2004
                {
                  g.fixInfo("Versionsupdate 3.9.6");

                  int iTab=SQL.getInteger(g,"select aic_tabellenname from tabellenname where kennung='Begriff'");

                  p.clear();
                  p.add("Kennung","Formularmenge");
		  int iGruppe=p.insert("begriffgruppe",true);
                  p.clear();
                  p.add("Kennung","Menge");
                  p.add("aic_tabellenname",iTab);
                  p.add("tab_aic_tabellenname",iTab);
                  p.add("Reihenfolge",1);
                  p.add("aic_begriffgruppe",iGruppe);
                  p.add("beg_aic_begriffgruppe",SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Frame'"));
                  b=b && p.insert("zuordnung",true)>0;

                  p.clear();
                  p.add("Kennung","Kunde");
		  iGruppe=p.insert("begriffgruppe",true);
                  p.clear();
                  p.add("Kennung","Kunde");
                  p.add("aic_tabellenname",iTab);
                  p.add("tab_aic_tabellenname",iTab);
                  p.add("aic_begriffgruppe",iGruppe);
                  p.add("beg_aic_begriffgruppe",SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Modul'"));
                  b=b && p.insert("zuordnung",true)>0;

                  iIst = b ? Ver(3,9,6):iIst;
                }

                if(b && iIst < Ver(3,9,7) && iSoll>=Ver(3,9,7))	//  19. 4.2004
                {
                  g.fixInfo("Versionsupdate 3.9.7");

                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  if (b && iGruppe>0)
                    b=addCode(iGruppe,"get days vb")>0;

                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Tabellentyp'");
                  if (b && iGruppe>0)
                    b=addCode(iGruppe,"Gesperrt")>0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                  if (b && iGruppe>0)
                    b=addCode(iGruppe,"write hold data")>0;

                  iIst = b ? Ver(3,9,7):iIst;
                }

                if(b && iIst < Ver(3,10,1) && iSoll>=Ver(3,10,1))	//  23. 4.2004
                {
                  g.diskInfo("Versionsupdate 3.10.1");

                  b=b && p.exec("alter table stammtyp add KEIN_STICHTAG bit NULL");
                  b=b && p.exec("alter table DEFIMPORT add VERSION integer NULL");
                  b=b && p.exec("alter table DEFIMPORT add ART integer NULL");
                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  b=b && addBegriff(iGruppe,"BewModul")>0;
                  int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iAnw,"use VecAic")>0;
                  //int iErg = p.getInteger("select aic_begriffgruppe from begriffgruppe g where g.kennung='Ergebnisart'");
                  //b=b && addCode(iErg,"proz")>0;

                  iIst = b ? Ver(3,10,1):iIst;
                }

                if(b && iIst < Ver(3,10,2) && iSoll>=Ver(3,10,2))	//  28. 4.2004
                {
                  g.diskInfo("Versionsupdate 3.10.2");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  if (b && iGruppe>0)
                    b=b && addCode(iGruppe,"set Stichtag")>0;
                  iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                  if (b && iGruppe>0)
                    b=b && addCode(iGruppe,"ODBC Import")>0;

                  iIst = b ? Ver(3,10,2):iIst;
                }

                if(b && iIst < Ver(3,10,3) && iSoll>=Ver(3,10,3))	//  11. 5.2004
                {
                  g.diskInfo("Versionsupdate 3.10.3");

                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  if (b && iGruppe>0)
                    b=addCode(iGruppe,"get years vb")>0;
                  iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  if (b && iGruppe>0)
                    b=addCode(iGruppe,"is manual")>0;

                  iIst = b ? Ver(3,10,3):iIst;
                }

                if(b && iIst < Ver(3,10,4) && iSoll>=Ver(3,10,4))	//  20. 5.2004
                {
                  g.diskInfo("Versionsupdate 3.10.4");
                  b=b && p.exec("alter table SPALTE_ZUORDNUNG drop PRIMARY KEY");
                  b=b && p.exec("alter table SPALTE_ZUORDNUNG MODIFY REIHENFOLGE integer not null");
                  b=b && p.exec("alter table SPALTE_ZUORDNUNG add PRIMARY KEY (AIC_SPALTE, REIHENFOLGE)");
                  iIst = b ? Ver(3,10,4):iIst;
                }

                if(b && iIst < Ver(3,10,5) && iSoll>=Ver(3,10,5))	//   3. 6.2004
                {
                  g.diskInfo("Versionsupdate 3.10.5");
                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  b=b && addBegriff(iGruppe,"Vorgaenger")>0;

                  b=b && p.exec("drop table versionsupdate");
                  b=b && p.exec("drop table version");
                  b=b && p.exec("drop table menue");
                  b=b && p.exec("drop table popup");
                  b=b && p.exec("delete from bezeichnung from bezeichnung join tabellenname where kennung='Version'");
                  b=b && p.exec("delete from tabellenname where kennung='VERSION'");

                  iIst = b ? Ver(3,10,5):iIst;
                }

                if(b && iIst < Ver(3,11,1) && iSoll>=Ver(3,11,1))	//   6. 7.2004
                {
                  g.diskInfo("Versionsupdate 3.11.1");
                  b=b && p.exec("alter table SPALTE ADD AIC_STAMMTYP integer null");
                  b=b && p.exec("alter table SPALTE add foreign key FK_SPALTE_HIERARCHIE (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP) on update restrict on delete restrict");

                  int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  int iInit = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  b=b && p.exec("update code set kennung='init Stichtag',aic_begriffgruppe="+iInit+" where Kennung='Anweisung16' and aic_begriffgruppe="+iAnw);
                  b=b && addCode(iAnw,"add Sync-Vec")>0;

                  iIst = b ? Ver(3,11,1):iIst;
                }

                if(b && iIst < Ver(3,11,2) && iSoll>=Ver(3,11,2))	//   8. 7.2004
                {
                  g.diskInfo("Versionsupdate 3.11.2");
                  b=b && p.exec("alter table SYSTEMINFO ADD DB_VERSION char(15) null");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
                  b=b && addCode(iGruppe,"*newyear*")>0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Ergebnisart'");
                  b=b && addCode(iGruppe,"last")>0;
                  iIst = b ? Ver(3,11,2):iIst;
                }

                if(b && iIst < Ver(3,11,3) && iSoll>=Ver(3,11,3))	//  23. 7.2004
                {
                  g.diskInfo("Versionsupdate 3.11.3");
                  int iOp = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                  if (b && iOp>0)
                  {
                    addCode(iOp,"string concat");
                    addCode(iOp,"string right");
                    addCode(iOp,"string fix");
                    addCode(iOp,"string fillnull");
                  }
                  p.add("Kennung","GAUGE");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iGruppe = p.insert("begriffgruppe",true);
                  if (b && iGruppe>0)
                  {
                          b=b && addCode(iGruppe,"Gauge open")>0;
                          b=b && addCode(iGruppe,"Gauge add")>0;
                          b=b && addCode(iGruppe,"Gauge set pos")>0;
                          b=b && addCode(iGruppe,"Gauge close")>0;
                          b=b && addCode(iGruppe,"sleep")>0;
                  }
                  iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  if (b && iGruppe>0)
                    b=addBegriff(iGruppe,"CalcString")>0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  if (b && iGruppe>0)
                    b=addCode(iGruppe,"compress")>0;

                  iIst = b ? Ver(3,11,3):iIst;
                }

                if(b && iIst < Ver(3,11,4) && iSoll>=Ver(3,11,4))	//  3. 8.2004
                {
                  g.diskInfo("Versionsupdate 3.11.4");

                  b=b && p.exec("update code set kennung='= DateTime' where Kennung='= Date'");
                  b=b && p.exec("update code set kennung='= Date' where Kennung='Bedingung4'");
                  b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'"),"concat table")>0;

                  iIst = b ? Ver(3,11,4):iIst;
                }

                if(b && iIst < Ver(4,0,0) && iSoll>=Ver(4,0,0))	// 15.11.2004
                {
                  g.diskInfo("Versionsupdate 4.0");
                  g.diskInfo(ViewRebuild(g)+" Views erneuert");

                  iIst = b ? Ver(4,0,0):iIst;
                }*/

                Gauge gauge=new Gauge("Versionsupdate",0,200,"V 4.04 - Update",g,false);

                /*if(b && iIst < Ver(4,0,1) && iSoll>=Ver(4,0,1))	// 25.11.2004
                {
                  g.diskInfo("Versionsupdate 4.0.1");
                  int iEuro=p.getInteger("select aic_stamm from stamm"+g.join("stammtyp","stamm")+" where stammtyp.kennung='Waehrung' and stamm.kennung='Euro'");
                  int iEF=p.getInteger("select aic_eigenschaft from eigenschaft where kennung='Eurofaktor'");
                  p.exec("update stammpool set sta_aic_stamm="+iEuro+" where pro2_aic_protokoll is null and aic_eigenschaft="+iEF+" and aic_stamm="+iEuro);
                  p.exec("update land set aic_stamm="+iEuro);
                  LoescheWaehrung("ATS",iEuro,13.7603);
                  LoescheWaehrung("DM",iEuro,1.95583);
                  LoescheWaehrung("PTS",iEuro,166.386);

                  g.diskInfo("Schrftzuordnung zu alten Druck löschen");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table abschnitt drop foreign key FK_ABSCHNIT_SCHRIFT_TEXT");
                    b = b && p.exec("alter table abschnitt drop foreign key FK_ABSCHNIT_SCHRIFT_TITEL");
                  }
                  b=b && p.exec("alter table abschnitt drop"+(g.Oracle()?" COLUMN":"")+" aic_schrift");
                  b=b && p.exec("alter table abschnitt drop"+(g.Oracle()?" COLUMN":"")+" sch_aic_schrift");

                  g.diskInfo("Umrechnungstabelle erstellen");
                  if (g.ASA())
                  {
                    b = b && p.exec("create table WW(" +
                                    "AIC_WW     integer               not null default AUTOINCREMENT," +
                                    "AIC_STAMM  integer               not null," +
                                    "DATUM      date                  not null," +
                                    "FAKTOR     double                not null," +
                                    "BITS       integer               null," +
                                    "primary key (AIC_WW))");
                    b=b && p.exec("alter table WW add foreign key FK_WW_Waehrung (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("create table WW(" +
                                    "AIC_WW         NUMBER(10)        not null," +
                                    "AIC_STAMM      NUMBER(10)        not null," +
                                    "DATUM          DATE              not null," +
                                    "FAKTOR         FLOAT             not null," +
                                    "BITS           NUMBER(10)        null    ," +
                                    "constraint PK_WW primary key (AIC_WW))");
                    b=b && p.exec("alter table WW add constraint FK_WW_Waehrung foreign key  (AIC_STAMM) references STAMM (AIC_STAMM)");
                    g.exec("create sequence AIC_WW start with 1");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("create table WW(" +
                                    "AIC_WW     int               not null identity," +
                                    "AIC_STAMM  int               not null," +
                                    "DATUM      datetime          not null," +
                                    "FAKTOR     double precision  not null," +
                                    "BITS       int               null," +
                                    "primary key (AIC_WW))");
                    b=b && p.exec("alter table WW add constraint FK_WW_Waehrung foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                  }
                  else
                  {
                    b=false;
                    Static.printError("Umrechnungstabelle konnte nicht erstellt werden");
                  }
                  iIst = b ? Ver(4,0,1):iIst;
                }

                if(b && iIst < Ver(4,0,2) && iSoll>=Ver(4,0,2))	// 7.12.2004
                {
                  g.diskInfo("Versionsupdate 4.0.2");

                  int iPlatz = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
                  b=b && addCode(iPlatz,"*meine*")>0;

                  if (g.ASA())
                  {
                    b=b && p.exec("alter table Benutzer_zuordnung ADD AIC_ABFRAGE integer null,ADD NR integer null default 0,ADD AIC_STAMM integer null,ADD BITS integer null");
                    //b=b && p.exec("update table Benutzer_zuordnung SET Nr=0");
                    b=b && p.exec("alter table benutzer_zuordnung modify NR not null");
                    b=b && p.exec("alter table benutzer_zuordnung drop primary key");
                    b=b && p.exec("alter table benutzer_zuordnung add primary key(AIC_BENUTZER,AIC_BENUTZERGRUPPE,NR)");
                    b=b && p.exec("alter table Benutzer_zuordnung add foreign key FK_Benutzer_Frage (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");
                    b=b && p.exec("alter table Benutzer_zuordnung add foreign key FK_Ersatz_Stamm (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b=b && p.exec("alter table Benutzer_zuordnung ADD AIC_ABFRAGE number(10) null");
                    b=b && p.exec("alter table Benutzer_zuordnung ADD NR number(10) null");
                    b=b && p.exec("alter table Benutzer_zuordnung ADD AIC_STAMM number(10) null");
                    b=b && p.exec("alter table Benutzer_zuordnung ADD BITS number(10) null");
                    b=b && p.exec("alter table Benutzer_zuordnung modify NR number(10) default 0");
                    b=b && p.exec("update Benutzer_zuordnung set nr=0");
                    b=b && p.exec("alter table Benutzer_zuordnung modify NR not null");
                    b=b && p.exec("alter table benutzer_zuordnung drop CONSTRAINT PK_BENUTZER_ZUORDNUNG");
                    b=b && p.exec("alter table benutzer_zuordnung add CONSTRAINT PK_BENUTZER_ZUORDNUNG primary key (AIC_BENUTZER,AIC_BENUTZERGRUPPE,NR)");
                    b=b && p.exec("alter table Benutzer_zuordnung add constraint FK_Benutzer_Frage foreign key (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE)");
                    b=b && p.exec("alter table Benutzer_zuordnung add constraint FK_Ersatz_Stamm foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table Benutzer_zuordnung ADD AIC_ABFRAGE int null");
                    b = b && p.exec("alter table Benutzer_zuordnung ADD NR int null");
                    b = b && p.exec("alter table Benutzer_zuordnung ADD AIC_STAMM int null");
                    b = b && p.exec("alter table Benutzer_zuordnung ADD BITS int null");
                    b=b && p.exec("update Benutzer_zuordnung SET Nr=0");
                    //b = b && p.exec("alter table benutzer_zuordnung modify NR not null");
                    //b = b && p.exec("alter table benutzer_zuordnung drop primary key");
                    //b = b && p.exec("alter table benutzer_zuordnung add primary key(AIC_BENUTZER,AIC_BENUTZERGRUPPE,NR)");
                    b = b && p.exec("alter table Benutzer_zuordnung add constraint FK_Benutzer_Frage foreign key (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE)");
                    b = b && p.exec("alter table Benutzer_zuordnung add constraint FK_Ersatz_Stamm foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                  }
                  else
                  {
                    b=false;
                    Static.printError("Benutzer_zuordnung konnte nicht erstellt werden");
                  }
                  iIst = b ? Ver(4,0,2):iIst;
                }*/
//                gauge.setText("V 4.02 - Update",10);
                /*if(b && iIst < Ver(4,1,1) && iSoll>=Ver(4,1,1))	// 5.1.2005
                {
                  g.diskInfo("Versionsupdate 4.1.1");
                  if (g.ASA())
                  {
                    b=b && p.exec("alter table Benutzer_zuordnung ADD STA_AIC_STAMM integer null");
                    b=b && p.exec("alter table Benutzer_zuordnung add foreign key FK_Antrag (STA_AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                    b=b && p.exec("alter table PROTOKOLL ADD PLACE integer null");
                  }
                  else if (g.Oracle())
                  {
                    b=b && p.exec("alter table Benutzer_zuordnung ADD STA_AIC_STAMM number(10) null");
                    b=b && p.exec("alter table Benutzer_zuordnung add constraint FK_Antrag foreign key (STA_AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("alter table PROTOKOLL ADD PLACE number(10) null");
                  }
                  else if (g.MS())
                  {
                    b=b && p.exec("alter table Benutzer_zuordnung ADD STA_AIC_STAMM int null");
                    b=b && p.exec("alter table Benutzer_zuordnung add constraint FK_Antrag foreign key (STA_AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("alter table PROTOKOLL ADD PLACE int null");
                  }

                  p.add("Kennung","authorisation");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iGruppe = p.insert("begriffgruppe",true);
                  if (b && iGruppe>0)
                  {
                    b = b && addCode(iGruppe, "get motions") > 0;
                    b = b && addCode(iGruppe, "get persons") > 0;
                    b = b && addCode(iGruppe, "get deputy") > 0;
                  }

                  iIst = b ? Ver(4,1,1):iIst;
                }

                if(b && iIst < Ver(4,1,2) && iSoll>=Ver(4,1,2))	// 2.2.2005
                {
                  g.diskInfo("Versionsupdate 4.1.2");
                  b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anlage'"),"Gesperrt")>0;
                  iIst = b ? Ver(4,1,2):iIst;
                }*/
                /*gauge.setText("V 4.03 - Update",20);
                if(b && iIst < Ver(4,2,1) && iSoll>=Ver(4,2,1))	// 3.3.2005
                {
                  g.diskInfo("Versionsupdate 4.2.1");
                  p.exec("create index index_BEW_GUELTIG on BEW_POOL (AIC_BEWEGUNGSTYP,GUELTIG asc)");

                  p.add("Kennung","reserve");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iRes = p.insert("begriffgruppe",true);
                  for (int i=0;i<10;i++)
                      b = b && addCode(iRes, "reserve"+i) > 0;
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b = b && addCode(iBed, "pos next true") > 0;
                  int iAuth = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='authorisation'");
                  int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && p.exec("update code set kennung='get selfchange',aic_begriffgruppe="+iAuth+" where Kennung='Anweisung17' and aic_begriffgruppe="+iAnw);
                  b=b && p.exec("update code set kennung='use Sync' where Kennung='Anweisung18' and aic_begriffgruppe="+iAnw);
                  b=b && p.exec("update code set kennung='has deputy' where Kennung='Bedingung5'");
                  b = b && addCode(iAnw, "next empty") > 0;
                  b=b && addCode(iBed,"Bedingung6")>0;
                  b=b && addCode(iBed,"Bedingung7")>0;
                  b=b && addCode(iBed,"Bedingung8")>0;
                  b=b && addCode(iBed,"Bedingung9")>0;

                  iIst = b ? Ver(4,2,1):iIst;
                }
                if(b && iIst < Ver(4,2,2) && iSoll>=Ver(4,2,2))	// 3.3.2005
                {
                  g.diskInfo("Versionsupdate 4.2.2");
                  b=b && p.exec("delete from zwischenspeicher where kennung in ('ZeitZutritt','StammUpload','Feiertag')");
                  b=b && p.exec("alter table zwischenspeicher ADD TERMINAL varchar(20) null");
                  b=b && p.exec("update zwischenspeicher set TERMINAL=KENNUNG");
                  b=b && p.exec("update zwischenspeicher set KENNUNG='Import'");

                  iIst = b ? Ver(4,2,2):iIst;
                }
                if(b && iIst < Ver(4,2,3) && iSoll>=Ver(4,2,3))	// 3.3.2005
                {
                  g.diskInfo("Versionsupdate 4.2.3");
                  if (g.ASA())
                  {
                    b=b && p.exec("create table ZEILE("+
                                  "AIC_ZEILE      integer      not null default AUTOINCREMENT,"+
                                  "Name           varchar(30)  null,"+
                                  "Links          varchar(30)  null,"+
                                  "Mitte          varchar(30)  null,"+
                                  "Rechts         varchar(30)  null,"+
                                  "Bild           varchar(100) null,"+
                                  "Hoehe          integer      null,"+
                                  "Ausrichtung    integer      null,"+
                                  "BITS           integer      null,"+
                                  "primary key (AIC_ZEILE))");
                    b=b && p.exec("create table LAYOUT("+
                                  "AIC_LAYOUT     integer      not null default AUTOINCREMENT,"+
                                  "Name           varchar(30)  null,"+
                                  "Links          integer      null,"+
                                  "Rechts         integer      null,"+
                                  "Oben           integer      null,"+
                                  "Unten          integer      null,"+
                                  "Bits           integer      null,"+
                                  "AIC_ZEILE      integer      null,"+
                                  "ZEI_AIC_ZEILE  integer      null,"+
                                  "AIC_BENUTZER   integer      null,"+
                                  "AIC_STAMM      integer      null,"+
                                  "primary key (AIC_LAYOUT))");
                    b=b && p.exec("alter table LAYOUT add foreign key FK_LAYOUT_KOPFZEILE_ZEILE (AIC_ZEILE) references ZEILE (AIC_ZEILE) on update restrict on delete restrict");
                    b=b && p.exec("alter table LAYOUT add foreign key FK_LAYOUT_FUSSZEILE_ZEILE (ZEI_AIC_ZEILE) references ZEILE (AIC_ZEILE) on update restrict on delete restrict");
                    b=b && p.exec("alter table LAYOUT add foreign key FK_LAYOUT_BENUTZER__BENUTZER (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER) on update restrict on delete restrict");
                    b=b && p.exec("alter table LAYOUT add foreign key FK_LAYOUT_FIRMA_DES_STAMM (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b=b && p.exec("create table ZEILE("+
                                "AIC_ZEILE      NUMBER(10)   not null,"+
                                "Name           VARCHAR(30)  null,"+
                                "Links          VARCHAR(30)  null,"+
                                "Mitte          VARCHAR(30)  null,"+
                                "Rechts         VARCHAR(30)  null,"+
                                "Bild           VARCHAR(100) null,"+
                                "Hoehe          NUMBER(10)      null,"+
                                "Ausrichtung    NUMBER(10)      null,"+
                                "BITS           NUMBER(10)      null,"+
                                "constraint PK_ZEILE primary key (AIC_ZEILE))");
                    b=b && p.exec("create table LAYOUT("+
                                "AIC_LAYOUT     NUMBER(10)      not null,"+
                                "Name           VARCHAR(30)  null,"+
                                "Links          NUMBER(10)      null,"+
                                "Rechts         NUMBER(10)      null,"+
                                "Oben           NUMBER(10)      null,"+
                                "Unten          NUMBER(10)      null,"+
                                "Bits           NUMBER(10)      null,"+
                                "AIC_ZEILE      NUMBER(10)      null,"+
                                "ZEI_AIC_ZEILE  NUMBER(10)      null,"+
                                "AIC_BENUTZER   NUMBER(10)      null,"+
                                "AIC_STAMM      NUMBER(10)      null,"+
                                "constraint PK_LAYOUT primary key (AIC_LAYOUT))");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_KOPFZEILE_ZEILE foreign key (AIC_ZEILE) references ZEILE (AIC_ZEILE)");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_FUSSZEILE_ZEILE foreign key (ZEI_AIC_ZEILE) references ZEILE (AIC_ZEILE)");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_BENUTZER__BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_FIRMA_DES_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                  }
                  else if (g.MS())
                  {
                    b=b && p.exec("create table ZEILE("+
                                "AIC_ZEILE      int               not null identity,"+
                                "Name           varchar(30)  null,"+
                                "Links          varchar(30)  null,"+
                                "Mitte          varchar(30)  null,"+
                                "Rechts         varchar(30)  null,"+
                                "Bild           varchar(100) null,"+
                                "Hoehe          int      null,"+
                                "Ausrichtung    int      null,"+
                                "BITS           int      null,"+
                                "primary key (AIC_ZEILE))");
                    b=b && p.exec("create table LAYOUT("+
                                "AIC_LAYOUT     int               not null identity,"+
                                "Name           varchar(30)  null,"+
                                "Links          int      null,"+
                                "Rechts         int      null,"+
                                "Oben           int      null,"+
                                "Unten          int      null,"+
                                "Bits           int      null,"+
                                "AIC_ZEILE      int      null,"+
                                "ZEI_AIC_ZEILE  int      null,"+
                                "AIC_BENUTZER   int      null,"+
                                "AIC_STAMM      int      null,"+
                                "primary key (AIC_LAYOUT))");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_KOPFZEILE_ZEILE foreign key (AIC_ZEILE) references ZEILE (AIC_ZEILE)");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_FUSSZEILE_ZEILE foreign key (ZEI_AIC_ZEILE) references ZEILE (AIC_ZEILE)");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_BENUTZER__BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_FIRMA_DES_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                  }
                  iIst = b ? Ver(4,2,3):iIst;
                }
                if(b && iIst < Ver(4,2,4) && iSoll>=Ver(4,2,4))	// 11.3.2005
                {
                  g.diskInfo("Versionsupdate 4.2.4");
                  if (g.ASA())
                  {
                    b=b && p.exec("alter table ZEILE ADD AIC_STAMM integer null");
                    b=b && p.exec("alter table LAYOUT drop foreign key FK_LAYOUT_FIRMA_DES_STAMM");
                    b=b && p.exec("alter table LAYOUT drop AIC_STAMM");
                    b=b && p.exec("alter table ZEILE add foreign key FK_ZEILE_FIRMA_DES_STAMM (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");

                    b=b && p.exec("create table Druck("+
                                  "AIC_DRUCK      integer               not null default AUTOINCREMENT,"+
                                  "AIC_BEGRIFF    integer               not null,"+
                                  "AIC_RASTER     integer                   null,"+
                                  "AIC_LAYOUT     integer                   null,"+
                                  "AIC_BENUTZER   integer                   null,"+
                                  "Bits           integer                   null,"+
                                  "primary key (AIC_DRUCK))");

                    b=b && p.exec("alter table Druck add foreign key FK_DRUCK_DRUCK_DES_BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");
                    b=b && p.exec("alter table Druck add foreign key FK_DRUCK_RASTER_DES_RASTER (AIC_RASTER) references Raster (AIC_RASTER) on update restrict on delete restrict");
                    b=b && p.exec("alter table Druck add foreign key FK_DRUCK_LAYOUT_DES_LAYOUT (AIC_LAYOUT) references LAYOUT (AIC_LAYOUT) on update restrict on delete restrict");
                    b=b && p.exec("alter table Druck add foreign key FK_DRUCK_RELATION__BENUTZER (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER) on update restrict on delete restrict");

                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table ZEILE ADD AIC_STAMM NUMBER(10) null");
                    b = b && p.exec("alter table LAYOUT drop COLUMN AIC_STAMM");
                    b = b && p.exec("alter table ZEILE add constraint FK_ZEILE_FIRMA_DES_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");

                    b = b && p.exec("create table Druck("+
                                  "AIC_DRUCK      NUMBER(10)               not null,"+
                                  "AIC_BEGRIFF    NUMBER(10)               not null,"+
                                  "AIC_RASTER     NUMBER(10)                   null,"+
                                  "AIC_LAYOUT     NUMBER(10)                   null,"+
                                  "AIC_BENUTZER   NUMBER(10)                   null,"+
                                  "Bits           NUMBER(10)                   null,"+
                                  "constraint PK_DRUCK primary key (AIC_DRUCK))");

                    b=b && p.exec("alter table Druck add constraint FK_DRUCK_DRUCK_DES_BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                    b=b && p.exec("alter table Druck add constraint FK_DRUCK_RASTER_DES_RASTER foreign key (AIC_RASTER) references Raster (AIC_RASTER)");
                    b=b && p.exec("alter table Druck add constraint FK_DRUCK_LAYOUT_DES_LAYOUT foreign key (AIC_LAYOUT) references LAYOUT (AIC_LAYOUT)");
                    b=b && p.exec("alter table Druck add constraint FK_DRUCK_RELATION__BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                    p.createAllSequences();
                  }
                  else if (g.MS())
                  {
                    b=b && p.exec("alter table ZEILE ADD AIC_STAMM int null");
                    b=b && p.exec("alter table LAYOUT drop constraint FK_LAYOUT_FIRMA_DES_STAMM");
                    b=b && p.exec("alter table LAYOUT drop COLUMN AIC_STAMM");
                    b = b && p.exec("alter table ZEILE add constraint FK_ZEILE_FIRMA_DES_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");

                    b=b && p.exec("create table Druck("+
                                  "AIC_DRUCK      int               not null identity,"+
                                  "AIC_BEGRIFF    int               not null,"+
                                  "AIC_RASTER     int                   null,"+
                                  "AIC_LAYOUT     int                   null,"+
                                  "AIC_BENUTZER   int                   null,"+
                                  "Bits           int                   null,"+
                                  "primary key (AIC_DRUCK))");

                    b=b && p.exec("alter table Druck add constraint FK_DRUCK_DRUCK_DES_BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                    b=b && p.exec("alter table Druck add constraint FK_DRUCK_RASTER_DES_RASTER foreign key (AIC_RASTER) references Raster (AIC_RASTER)");
                    b=b && p.exec("alter table Druck add constraint FK_DRUCK_LAYOUT_DES_LAYOUT foreign key (AIC_LAYOUT) references LAYOUT (AIC_LAYOUT)");
                    b=b && p.exec("alter table Druck add constraint FK_DRUCK_RELATION__BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                  }
                  iIst = b ? Ver(4,2,4):iIst;
                }
                gauge.setText("V 4.04 - Update",30);
                if(b && iIst < Ver(4,3,1) && iSoll>=Ver(4,3,1))	// 7.4.2005
                {
                  g.diskInfo("Versionsupdate 4.3.1");
                  if (g.ASA())
                  {
                    b=b && p.exec("alter table Daten_Bild drop Bild");
                    b=b && p.exec("alter table Daten_Image drop Spalte_Image");
                  }
                  else if (g.MS())
                  {
                    b=b && p.exec("alter table Daten_Bild drop COLUMN Bild");
                    b=b && p.exec("alter table Daten_Image drop COLUMN Spalte_Image");
                  }

                  int iPlatz = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
                  b=b && addCode(iPlatz,"*Joker von*")>0;
                  b=b && addCode(iPlatz,"*Qry*")>0;
                  b=b && addCode(iPlatz,"*Qry von*")>0;
                  iIst = b ? Ver(4,3,1):iIst;
                }
                if(b && iIst < Ver(4,3,2) && iSoll>=Ver(4,3,2))	// 20.4.2005
                {
                  g.diskInfo("Versionsupdate 4.3.2");
                  if (g.ASA())
                  {
                    b=b && p.exec("create table ABSCHLUSSTYP("+
                                  "AIC_ABSCHLUSSTYP    integer               not null default AUTOINCREMENT,"+
                                  "KENNUNG             varchar(20)           not null,"+
                                  "AIC_STAMMTYP        integer                   null,"+
                                  "primary key (AIC_ABSCHLUSSTYP))");
                    b=b && p.exec("create table ABSCHLUSS("+
                                  "AIC_ABSCHLUSS       integer               not null default AUTOINCREMENT,"+
                                  "AIC_ABSCHLUSSTYP    integer               not null,"+
                                  "AIC_STAMM           integer                   null,"+
                                  "DATUM               date                  not null,"+
                                  "AIC_PROTOKOLL       integer               not null,"+
                                  "PRO_AIC_PROTOKOLL   integer                   null,"+
                                  "primary key (AIC_ABSCHLUSS))");
                    b=b && p.exec("create table ABSCHLUSSDEFINITION("+
                                  "AIC_ABSCHLUSSTYP    integer               not null,"+
                                  "AIC_BEWEGUNGSTYP    integer               not null,"+
                                  "AIC_EIGENSCHAFT     integer               not null,"+
                                  "primary key (AIC_ABSCHLUSSTYP, AIC_BEWEGUNGSTYP))");
                    b=b && p.exec("create table ABSCHLUSS_ZUORDNUNG("+
                                  "AIC_BENUTZERGRUPPE  integer               not null,"+
                                  "AIC_ABSCHLUSSTYP    integer               not null,"+
                                  "primary key (AIC_BENUTZERGRUPPE, AIC_ABSCHLUSSTYP))");
                    b=b && p.exec("alter table ABSCHLUSS add foreign key FK_ABSCHLUS_RELATION__ABSCHLUS (AIC_ABSCHLUSSTYP) references ABSCHLUSSTYP (AIC_ABSCHLUSSTYP) on update restrict on delete restrict");
                    b=b && p.exec("alter table ABSCHLUSS add foreign key FK_ABSCHLUS_FIRMA_DES_STAMM (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                    b=b && p.exec("alter table ABSCHLUSS add foreign key FK_ABSCHLUS_FIXIERT_PROTOKOL (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL) on update restrict on delete restrict");
                    b=b && p.exec("alter table ABSCHLUSS add foreign key FK_ABSCHLUS_ENTFERNT_PROTOKOL (PRO_AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL) on update restrict on delete restrict");
                    b=b && p.exec("alter table ABSCHLUSSDEFINITION add foreign key FK_ABSCHLUS_BEWEGUNGS_BEWEGUNG (AIC_BEWEGUNGSTYP) references BEWEGUNGSTYP (AIC_BEWEGUNGSTYP) on update restrict on delete restrict");
                    b=b && p.exec("alter table ABSCHLUSSDEFINITION add foreign key FK_ABSCHLUS_EIGENSCHA_EIGENSCH (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
                    b=b && p.exec("alter table ABSCHLUSSTYP add foreign key FK_ABSCHLUS_STAMMTYP__STAMMTYP (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP) on update restrict on delete restrict");
                    b=b && p.exec("alter table ABSCHLUSSDEFINITION add foreign key FK_ABSCHLUS_RELATION__ABSCHLUS (AIC_ABSCHLUSSTYP) references ABSCHLUSSTYP (AIC_ABSCHLUSSTYP) on update restrict on delete restrict");
                    b=b && p.exec("alter table ABSCHLUSS_ZUORDNUNG add foreign key FK_ABSCHLUS_RELATION__ABSCHLUS (AIC_ABSCHLUSSTYP) references ABSCHLUSSTYP (AIC_ABSCHLUSSTYP) on update restrict on delete restrict");
                    b=b && p.exec("alter table ABSCHLUSS_ZUORDNUNG add foreign key FK_ABSCHLUS_RELATION__BENUTZER (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b=b && p.exec("create table ABSCHLUSSTYP("+
                                  "AIC_ABSCHLUSSTYP    NUMBER(10)            not null,"+
                                  "KENNUNG             varchar(20)           not null,"+
                                  "AIC_STAMMTYP        NUMBER(10)                null,"+
                                  "constraint PK_ABSCHLUSSTYP primary key (AIC_ABSCHLUSSTYP))");
                    b=b && p.exec("create table ABSCHLUSS("+
                                  "AIC_ABSCHLUSS       NUMBER(10)            not null,"+
                                  "AIC_ABSCHLUSSTYP    NUMBER(10)            not null,"+
                                  "AIC_STAMM           NUMBER(10)                null,"+
                                  "DATUM               date                  not null,"+
                                  "AIC_PROTOKOLL       NUMBER(10)            not null,"+
                                  "PRO_AIC_PROTOKOLL   NUMBER(10)                null,"+
                                  "constraint PK_ABSCHLUSS primary key (AIC_ABSCHLUSS))");
                    b=b && p.exec("create table ABSCHLUSSDEFINITION("+
                                  "AIC_ABSCHLUSSTYP    NUMBER(10)               not null,"+
                                  "AIC_BEWEGUNGSTYP    NUMBER(10)               not null,"+
                                  "AIC_EIGENSCHAFT     NUMBER(10)               not null,"+
                                  "constraint PK_ABSCHLUSSDEFINITION primary key (AIC_ABSCHLUSSTYP, AIC_BEWEGUNGSTYP))");
                    b=b && p.exec("create table ABSCHLUSS_ZUORDNUNG("+
                                  "AIC_BENUTZERGRUPPE  NUMBER(10)               not null,"+
                                  "AIC_ABSCHLUSSTYP    NUMBER(10)               not null,"+
                                  "constraint PK_ABSCHLUSS_ZUORDNUNG primary key (AIC_BENUTZERGRUPPE, AIC_ABSCHLUSSTYP))");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUS_RELATION__ABSCHLUS foreign key (AIC_ABSCHLUSSTYP) references ABSCHLUSSTYP (AIC_ABSCHLUSSTYP)");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUS_FIRMA_DES_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUS_FIXIERT_PROTOKOL foreign key (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL)");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUS_ENTFERNT_PROTOKOL foreign key (PRO_AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL)");
                    b=b && p.exec("alter table ABSCHLUSSDEFINITION add constraint FK_ABSCHLUS_BEWEGUNGS_BEWEGUNG foreign key (AIC_BEWEGUNGSTYP) references BEWEGUNGSTYP (AIC_BEWEGUNGSTYP)");
                    b=b && p.exec("alter table ABSCHLUSSDEFINITION add constraint FK_ABSCHLUS_EIGENSCHA_EIGENSCH foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b=b && p.exec("alter table ABSCHLUSSTYP add constraint FK_ABSCHLUS_STAMMTYP__STAMMTYP foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b=b && p.exec("alter table ABSCHLUSSDEFINITION add constraint FK_ABSCHLUS_RELATION_2ABSCHLUS foreign key (AIC_ABSCHLUSSTYP) references ABSCHLUSSTYP (AIC_ABSCHLUSSTYP)");
                    b=b && p.exec("alter table ABSCHLUSS_ZUORDNUNG add constraint FK_ABSCHLUS_RELATION_3ABSCHLUS foreign key (AIC_ABSCHLUSSTYP) references ABSCHLUSSTYP (AIC_ABSCHLUSSTYP)");
                    b=b && p.exec("alter table ABSCHLUSS_ZUORDNUNG add constraint FK_ABSCHLUS_RELATION__BENUTZER foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                    p.createAllSequences();
                  }
                  else if (g.MS())
                  {
                    b=b && p.exec("create table ABSCHLUSSTYP("+
                                  "AIC_ABSCHLUSSTYP    int            not null identity,"+
                                  "KENNUNG             varchar(20)           not null,"+
                                  "AIC_STAMMTYP        int                   null,"+
                                  "primary key (AIC_ABSCHLUSSTYP))");
                    b=b && p.exec("create table ABSCHLUSS("+
                                  "AIC_ABSCHLUSS       int            not null identity,"+
                                  "AIC_ABSCHLUSSTYP    int            not null,"+
                                  "AIC_STAMM           int                null,"+
                                  "DATUM               datetime       not null,"+
                                  "AIC_PROTOKOLL       int               not null,"+
                                  "PRO_AIC_PROTOKOLL   int                   null,"+
                                  "primary key (AIC_ABSCHLUSS))");
                    b=b && p.exec("create table ABSCHLUSSDEFINITION("+
                                  "AIC_ABSCHLUSSTYP    int               not null,"+
                                  "AIC_BEWEGUNGSTYP    int               not null,"+
                                  "AIC_EIGENSCHAFT     int               not null,"+
                                  "primary key (AIC_ABSCHLUSSTYP, AIC_BEWEGUNGSTYP))");
                    b=b && p.exec("create table ABSCHLUSS_ZUORDNUNG("+
                                  "AIC_BENUTZERGRUPPE  int               not null,"+
                                  "AIC_ABSCHLUSSTYP    int               not null,"+
                                  "primary key (AIC_BENUTZERGRUPPE, AIC_ABSCHLUSSTYP))");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUS_RELATION__ABSCHLUS foreign key (AIC_ABSCHLUSSTYP) references ABSCHLUSSTYP (AIC_ABSCHLUSSTYP)");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUS_FIRMA_DES_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUS_FIXIERT_PROTOKOL foreign key (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL)");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUS_ENTFERNT_PROTOKOL foreign key (PRO_AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL)");
                    b=b && p.exec("alter table ABSCHLUSSDEFINITION add constraint FK_ABSCHLUS_BEWEGUNGS_BEWEGUNG foreign key (AIC_BEWEGUNGSTYP) references BEWEGUNGSTYP (AIC_BEWEGUNGSTYP)");
                    b=b && p.exec("alter table ABSCHLUSSDEFINITION add constraint FK_ABSCHLUS_EIGENSCHA_EIGENSCH foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b=b && p.exec("alter table ABSCHLUSSTYP add constraint FK_ABSCHLUS_STAMMTYP__STAMMTYP foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b=b && p.exec("alter table ABSCHLUSSDEFINITION add constraint FK_ABSCHLUS_RELATION_2ABSCHLUS foreign key (AIC_ABSCHLUSSTYP) references ABSCHLUSSTYP (AIC_ABSCHLUSSTYP)");
                    b=b && p.exec("alter table ABSCHLUSS_ZUORDNUNG add constraint FK_ABSCHLUS_RELATION_3ABSCHLUS foreign key (AIC_ABSCHLUSSTYP) references ABSCHLUSSTYP (AIC_ABSCHLUSSTYP)");
                    b=b && p.exec("alter table ABSCHLUSS_ZUORDNUNG add constraint FK_ABSCHLUS_RELATION__BENUTZER foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                  }

                  if (b)
                  {
                    p.add("Kennung", "ABSCHLUSSTYP");
                    b=p.insert("tabellenname", true)>0;
                  }
                  iIst = b ? Ver(4,3,2):iIst;
                }
                if(b && iIst < Ver(4,3,3) && iSoll>=Ver(4,3,3))	// 26.4.2005
                {
                  g.diskInfo("Versionsupdate 4.3.3");
                  b=b && p.exec("drop table stamm_berechtigung");
                  int iGet = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  b=b && addCode(iGet,"get conclusion")>0;
                  if (g.ASA())
                  {
                    b=b && p.exec("alter table ABSCHLUSSTYP ADD BITS integer null");
                    b=b && p.exec("alter table ABSCHLUSS ADD AIC_ABFRAGE integer null");
                    b=b && p.exec("alter table ABSCHLUSS drop foreign key FK_ABSCHLUS_FIRMA_DES_STAMM");
                    b=b && p.exec("alter table ABSCHLUSS drop aic_stamm");
                    b=b && p.exec("alter table ABSCHLUSS add foreign key FK_ABSCHLUSS_STAMMSATZ (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b=b && p.exec("alter table ABSCHLUSSTYP ADD BITS NUMBER(10) null");
                    b=b && p.exec("alter table ABSCHLUSS ADD AIC_ABFRAGE NUMBER(10) null");
                    b=b && p.exec("alter table ABSCHLUSS drop COLUMN aic_stamm");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUSS_STAMMSATZ foreign key (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE)");
                  }
                  else if (g.MS())
                  {
                    b=b && p.exec("alter table ABSCHLUSSTYP ADD BITS int null");
                    b=b && p.exec("alter table ABSCHLUSS ADD AIC_ABFRAGE int null");
                    b=b && p.exec("alter table ABSCHLUSS drop constraint FK_ABSCHLUS_FIRMA_DES_STAMM");
                    b=b && p.exec("alter table ABSCHLUSS drop COLUMN aic_stamm");
                    b=b && p.exec("alter table ABSCHLUSS add constraint FK_ABSCHLUSS_STAMMSATZ foreign key (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE)");
                  }
                  iIst = b ? Ver(4,3,3):iIst;
                }
                if(b && iIst < Ver(4,3,4) && iSoll>=Ver(4,3,4))	// 28.4.2005
                {
                  g.diskInfo("Versionsupdate 4.3.4");
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && addCode(iBed,"IO-Error")>0;
                  if (g.ASA())
                  {
                    b=b && p.exec("alter table SPALTE ADD MIN double null");
                    b=b && p.exec("alter table SPALTE ADD MAX double null");
                  }
                  else if (g.Oracle())
                  {
                    b=b && p.exec("alter table SPALTE ADD MIN FLOAT null");
                    b=b && p.exec("alter table SPALTE ADD MAX FLOAT null");
                  }
                  else if (g.MS())
                  {
                    b=b && p.exec("alter table SPALTE ADD MIN double precision null");
                    b=b && p.exec("alter table SPALTE ADD MAX double precision null");
                  }
                  iIst = b ? Ver(4,3,4):iIst;
                }
                */
                gauge.setText("V 4.05 - Update",1);
                if(b && iIst < Ver(4,4,1) && iSoll>=Ver(4,4,1))	// 30.5.2005
                {
                  g.diskInfo("Versionsupdate 4.4.1");
                  if (g.MS())
                  {
                    b=b && p.exec("ALTER TABLE BEFEHL ALTER COLUMN HIDE bit NULL");
                    b=b && p.exec("ALTER TABLE BEFEHL ALTER COLUMN SCHLEIFE bit NULL");
                    b=b && p.exec("ALTER TABLE BEGRIFF ALTER COLUMN COMBO bit NULL");
                    b=b && p.exec("ALTER TABLE BEGRIFF ALTER COLUMN FIXE_SPRACHE bit NULL");
                    b=b && p.exec("ALTER TABLE BEGRIFF ALTER COLUMN JEDER bit NULL");
                    b=b && p.exec("ALTER TABLE BEGRIFFGRUPPE ALTER COLUMN ANWEISUNG bit NULL");
                    b=b && p.exec("ALTER TABLE BEGRIFFGRUPPE ALTER COLUMN CODE bit NULL");
                    b=b && p.exec("ALTER TABLE BEW_BOOLEAN ALTER COLUMN SPALTE_BOOLEAN bit NULL");
                    b=b && p.exec("ALTER TABLE BEWEGUNGSTYP ALTER COLUMN LOKALTIMER bit NULL");
                    b=b && p.exec("ALTER TABLE BEWEGUNGSTYP ALTER COLUMN NUR_MODELL bit NULL");
                    b=b && p.exec("ALTER TABLE BEWEGUNGSTYP ALTER COLUMN Timer bit NULL");
                    b=b && p.exec("ALTER TABLE FIXEIGENSCHAFT ALTER COLUMN RICHTUNG bit NULL");
                    b=b && p.exec("ALTER TABLE LAND ALTER COLUMN STANDARD bit NULL");
                    b=b && p.exec("ALTER TABLE SCHRIFT ALTER COLUMN BOLD bit NULL");
                    b=b && p.exec("ALTER TABLE SCHRIFT ALTER COLUMN ITALIC bit NULL");
                    b=b && p.exec("ALTER TABLE SPALTENNAME ALTER COLUMN FREI bit NULL");
                    b=b && p.exec("ALTER TABLE SPRACHE ALTER COLUMN STANDARD bit NULL");
                    b=b && p.exec("ALTER TABLE STAMMTYP ALTER COLUMN BENUTZER bit NULL");
                    b=b && p.exec("ALTER TABLE STAMMTYP ALTER COLUMN COPY bit NULL");
                    b=b && p.exec("ALTER TABLE STAMMTYP ALTER COLUMN EINHEIT bit NULL");
                    b=b && p.exec("ALTER TABLE STAMMTYP ALTER COLUMN ENDE bit NULL");
                    b=b && p.exec("ALTER TABLE STAMMTYP ALTER COLUMN KEIN_STICHTAG bit NULL");
                    b=b && p.exec("ALTER TABLE STAMMTYP ALTER COLUMN LIZENZ bit NULL");
                    b=b && p.exec("ALTER TABLE STAMMTYP ALTER COLUMN STD_FORMULAR bit NULL");
                    b=b && p.exec("ALTER TABLE STAMMTYP ALTER COLUMN translate bit NULL");
                    b=b && p.exec("ALTER TABLE TABELLENNAME ALTER COLUMN FIXE_SPRACHE bit NULL");
                    b=b && p.exec("ALTER TABLE ZUORDNUNG ALTER COLUMN GUELTIGKEIT bit NULL");
                    b=b && p.exec("ALTER TABLE ZUORDNUNG ALTER COLUMN REIHENFOLGE bit NULL");

                    b=b && p.exec("alter table ZEILE ADD AIC_SCHRIFT int null");
                    b=b && p.exec("alter table ZEILE add constraint FK_ZEILE_SCHRIFT foreign key (AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT)");

                  }
                  else if (g.ASA())
                  {
                    b=b && p.exec("alter table ZEILE ADD AIC_SCHRIFT integer null");
                    b=b && p.exec("alter table ZEILE add foreign key FK_ZEILE_SCHRIFT (AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table ZEILE ADD AIC_SCHRIFT NUMBER(10) null");
                    b = b && p.exec("alter table ZEILE add constraint FK_ZEILE_SCHRIFT foreign key (AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT)");
                  }

                  int iSum=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Ergebnisart'");
                  b=b && addCode(iSum,"_count_max")>0;
                  b=b && addCode(iSum,"_count_min")>0;
                  b=b && addCode(iSum,"_count_in")>0;
                  b=b && addCode(iSum,"_count_not_in")>0;
                  int iAnw=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iAnw,"get changes")>0;
                  int iGet=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  b=b && addCode(iGet,"get dependent memo")>0;

                  iIst = b ? Ver(4,4,1):iIst;
                }

                if(b && iIst < Ver(4,4,2) && iSoll>=Ver(4,4,2))	// 10.6.2005
                {
                  g.diskInfo("Versionsupdate 4.4.2");

                  int iGet=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  b=b && addCode(iGet,"get title")>0;
                  int iInit=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  b=b && addCode(iInit,"init Sync-Stamm")>0;
                  b=b && addCode(iInit,"init Sync-Vec")>0;
                  if (g.ASA())
                    b=b && p.exec("alter table SPALTE ADD BITS2 integer null");
                  else if (g.Oracle())
                    b = b && p.exec("alter table SPALTE ADD BITS2 NUMBER(10) null");
                  else if (g.MS())
                    b = b && p.exec("alter table SPALTE ADD BITS2 int null");

                  iIst = b ? Ver(4,4,2):iIst;
                }

                if(b && iIst < Ver(4,4,3) && iSoll>=Ver(4,4,3))	// 21.6.2005
                {
                  g.diskInfo("Versionsupdate 4.4.3");
                  b=b && p.exec("update layout set bits=bits-4 where"+g.bit("bits",4)+" and aic_benutzer is not null");  // Drucken.cstLayStd
                  b=b && p.exec("update layout set bits=bits-4 where"+g.bit("bits",4)+" and aic_layout>(select min(aic_layout) from layout where"+g.bit("bits",4)+")");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table RASTER ADD AIC_BENUTZER integer null");
                    b = b && p.exec("alter table RASTER add foreign key FK_RASTER_RELATION_BENUTZER (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table RASTER ADD AIC_BENUTZER NUMBER(10) null");
                    b = b && p.exec("alter table RASTER add constraint FK_RASTER_RELATION_BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table RASTER ADD AIC_BENUTZER int null");
                    b = b && p.exec("alter table RASTER add constraint FK_RASTER_RELATION_BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                  }

                  iIst = b ? Ver(4,4,3):iIst;
                }
                gauge.setText("V 4.06 - Update",2);
                if(b && iIst < Ver(4,5,1) && iSoll>=Ver(4,5,1))	// 21.7.2005
                {
                  g.diskInfo("Versionsupdate 4.5.1");

                  //int iPlatz = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
                  //b=b && addCode(iPlatz,"*JokerAbf*")>0;
                  int iInit=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  b=b && addCode(iInit,"init 1970")>0;
                  b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'"),"concat dep memo")>0;
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table benutzer ADD AIC_BENUTZERGRUPPE integer null");
                    b = b && p.exec("alter table benutzer add foreign key FK_BENUTZER_HAUPTBG (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE) on update restrict on delete restrict");

                    /*b = b && p.exec("create table LAND_ZUORDNUNG("+
                                    " AIC_ABFRAGE  integer               not null,"+
                                    " AIC_LAND     integer               not null,"+
                                    " primary key (AIC_ABFRAGE, AIC_LAND))");

                    b = b && p.exec("alter table LAND_ZUORDNUNG add foreign key FK_LAND_ZUO_LAND_DER__LAND (AIC_LAND) references LAND (AIC_LAND) on update restrict on delete restrict");
                    b = b && p.exec("alter table LAND_ZUORDNUNG add foreign key FK_LAND_ZUO_BST_DES_L_ABFRAGE (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");*/

                    b = b && p.exec("alter table LAND ADD AIC_ABFRAGE integer null");
                    b = b && p.exec("alter table LAND add foreign key FK_LAND_BST (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");

                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table benutzer ADD AIC_BENUTZERGRUPPE NUMBER(10) null");
                    b = b && p.exec("alter table benutzer add constraint FK_BENUTZER_HAUPTBG foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                    b = b && p.exec("alter table LAND ADD AIC_ABFRAGE NUMBER(10) null");
                    b = b && p.exec("alter table LAND add constraint FK_LAND_BST foreign key (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table benutzer ADD AIC_BENUTZERGRUPPE int null");
                    b = b && p.exec("alter table benutzer add constraint FK_BENUTZER_HAUPTBG foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                    b = b && p.exec("alter table LAND ADD AIC_ABFRAGE int null");
                    b = b && p.exec("alter table LAND add constraint FK_LAND_BST foreign key (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE)");
                  }
                  iIst = b ? Ver(4,5,1):iIst;
                }

                if(b && iIst < Ver(4,5,2) && iSoll>=Ver(4,5,2))	// 27.7.2005
                {
                  g.diskInfo("Versionsupdate 4.5.2");

                  int iAF=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Auswertformat'");
                  b=b && addCode(iAF,"SV_Zahl")>0;
                  b=b && addCode(iAF,"SV_Cent")>0;
                  b=b && addCode(iAF,"SV_Datum")>0;
                  b=b && addCode(iAF,"SV_Boolean")>0;
                  b=b && addCode(iAF,"L1_Cent")>0;
                  p.add("Kennung","Anzeigeart");
                  p.add("code",1);
                  int iGruppe = p.insert("begriffgruppe",true);
                  b=b && addCode(iGruppe,"timestamp")>0;
                  b=b && addCode(iGruppe,"user")>0;
                  b=b && addCode(iGruppe,"currency")>0;
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table spalte ADD COD3_AIC_CODE integer null");
                    b = b && p.exec("alter table spalte add foreign key FK_SPALTE_Anzeige (COD3_AIC_CODE) references CODE (AIC_CODE) on update restrict on delete restrict");
                    b = b && p.exec("alter table Formular ADD AIC_STAMM integer null");
                    b = b && p.exec("alter table Formular add foreign key FK_Formular_Stamm (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table spalte ADD COD3_AIC_CODE NUMBER(10) null");
                    b = b && p.exec("alter table spalte add constraint FK_SPALTE_Anzeige foreign key (COD3_AIC_CODE) references CODE (AIC_CODE)");
                    b = b && p.exec("alter table Formular ADD AIC_STAMM NUMBER(10) null");
                    b = b && p.exec("alter table Formular add constraint FK_Formular_Stamm foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table spalte ADD COD3_AIC_CODE int null");
                    b = b && p.exec("alter table spalte add constraint FK_SPALTE_Anzeige foreign key (COD3_AIC_CODE) references CODE (AIC_CODE)");
                    b = b && p.exec("alter table Formular ADD AIC_STAMM int null");
                    b = b && p.exec("alter table Formular add constraint FK_Formular_Stamm foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                  }
                  iIst = b ? Ver(4,5,2):iIst;
                }
                gauge.setText("V 4.07 - Update",3);
                if(b && iIst < Ver(4,6,1) && iSoll>=Ver(4,6,1))	// 24.8.2005
                {
                  g.diskInfo("Versionsupdate 4.6.1");

                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  if (b && iGruppe>0)
                  {
                          b=b && addCode(iGruppe,"is same weekday")>0;
                          b=b && addCode(iGruppe,"is same month")>0;
                          b=b && addCode(iGruppe,"is same year")>0;
                  }
                  iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Vergleich'");
                  addBegriff(iGruppe,"soundex");

                  if (g.ASA())
                  {
                    b = b && p.exec("alter table bedingung ADD BBITS integer null");

                    b = b && p.exec("create table ROLLE("+
                                    "AIC_ROLLE        integer  not null default AUTOINCREMENT,"+
                                    "AIC_STAMMTYP     integer  null,"+
                                    "ROL_AIC_ROLLE    integer  null,"+
                                    "KENNUNG          char(20) null,"+
                                    "primary key (AIC_ROLLE))");

                    b = b && p.exec("create table ROLLE_ZUORDNUNG("+
                                    "AIC_ROLLE        integer  not null,"+
                                    "AIC_EIGENSCHAFT  integer  not null,"+
                                    "primary key (AIC_ROLLE,AIC_EIGENSCHAFT))");

                    b = b && p.exec("alter table BEGRIFF ADD AIC_ROLLE integer null");

                    b = b && p.exec("alter table ROLLE add foreign key FK_ROLLE_STT_D_ROL_STAMMTYP (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP) on update restrict on delete restrict");
                    b = b && p.exec("alter table ROLLE add foreign key FK_ROLLE_ROLLEOHR_ROLLE (ROL_AIC_ROLLE) references ROLLE (AIC_ROLLE) on update restrict on delete restrict");
                    b = b && p.exec("alter table BEGRIFF add foreign key FK_BEGRIFF_ROLLE_VON_ROLLE (AIC_ROLLE) references ROLLE (AIC_ROLLE) on update restrict on delete restrict");
                    b = b && p.exec("alter table ROLLE_ZUORDNUNG add foreign key FK_ROLLE_ZU_ROLLE_D_E_ROLLE (AIC_ROLLE) references ROLLE (AIC_ROLLE) on update restrict on delete restrict");
                    b = b && p.exec("alter table ROLLE_ZUORDNUNG add foreign key FK_ROLLE_ZU_EIG_D_ROL_EIGENSCH (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table bedingung ADD BBITS NUMBER(10) null");

                    b = b && p.exec("create table ROLLE("+
                                    "AIC_ROLLE        NUMBER(10)  not null,"+
                                    "AIC_STAMMTYP     NUMBER(10)  null,"+
                                    "ROL_AIC_ROLLE    NUMBER(10)  null,"+
                                    "KENNUNG          varchar(20) null,"+
                                    "constraint PK_ROLLE primary key (AIC_ROLLE))");

                    b = b && p.exec("create table ROLLE_ZUORDNUNG("+
                                    "AIC_ROLLE        NUMBER(10)  not null,"+
                                    "AIC_EIGENSCHAFT  NUMBER(10)  not null,"+
                                    "constraint PK_ROLLE_ZUORDNUNG primary key (AIC_ROLLE,AIC_EIGENSCHAFT))");

                    b = b && p.exec("alter table BEGRIFF ADD AIC_ROLLE NUMBER(10) null");

                    b = b && p.exec("alter table ROLLE add constraint FK_ROLLE_STT_D_ROL_STAMMTYP foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b = b && p.exec("alter table ROLLE add constraint FK_ROLLE_ROLLEOHR_ROLLE foreign key (ROL_AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                    b = b && p.exec("alter table BEGRIFF add constraint FK_BEGRIFF_ROLLE_VON_ROLLE foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                    b = b && p.exec("alter table ROLLE_ZUORDNUNG add constraint FK_ROLLE_ZU_ROLLE_D_E_ROLLE foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                    b = b && p.exec("alter table ROLLE_ZUORDNUNG add constraint FK_ROLLE_ZU_EIG_D_ROL_EIGENSCH foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");

                    p.createAllSequences();
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table bedingung ADD BBITS int null");

                    b = b && p.exec("create table ROLLE("+
                                    "AIC_ROLLE        int  not null identity,"+
                                    "AIC_STAMMTYP     int  null,"+
                                    "ROL_AIC_ROLLE    int  null,"+
                                    "KENNUNG          varchar(20) null,"+
                                    "primary key (AIC_ROLLE))");

                    b = b && p.exec("create table ROLLE_ZUORDNUNG("+
                                    "AIC_ROLLE        int  not null,"+
                                    "AIC_EIGENSCHAFT  int  not null,"+
                                    "primary key (AIC_ROLLE,AIC_EIGENSCHAFT))");

                    b = b && p.exec("alter table BEGRIFF ADD AIC_ROLLE int null");

                    b = b && p.exec("alter table ROLLE add constraint FK_ROLLE_STT_D_ROL_STAMMTYP foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b = b && p.exec("alter table ROLLE add constraint FK_ROLLE_ROLLEOHR_ROLLE foreign key (ROL_AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                    b = b && p.exec("alter table BEGRIFF add constraint FK_BEGRIFF_ROLLE_VON_ROLLE foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                    b = b && p.exec("alter table ROLLE_ZUORDNUNG add constraint FK_ROLLE_ZU_ROLLE_D_E_ROLLE foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                    b = b && p.exec("alter table ROLLE_ZUORDNUNG add constraint FK_ROLLE_ZU_EIG_D_ROL_EIGENSCH foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");

                  }
                  p.add("kennung","ROLLE");
                  int iTab=p.insert("tabellenname",true);
                  p.add("AIC_Tabellenname",iTab);
                  p.add("AIC_Komponente",6);//Rekursion
                  p.add("FREI",1);
                  p.add("KENNUNG","ROL_AIC_ROLLE");
                  p.insert("SPALTENNAME",false);
                  p.add("AIC_TABELLENNAME",iTab);
                  p.add("TAB_AIC_TABELLENNAME",p.getString("select aic_tabellenname from tabellenname where kennung='Eigenschaft'"));
                  p.add("KENNUNG","ROLLE");
                  p.insert("ZUORDNUNG",false);
                  iIst = b ? Ver(4,6,1):iIst;
                }
                if(b && iIst < Ver(4,6,2) && iSoll>=Ver(4,6,2))	// 30.8.2005
                {
                  g.diskInfo("Versionsupdate 4.6.2");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table STAMM_PROTOKOLL ADD AIC_ROLLE integer null");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add foreign key FK_STAMM_PROT_ROLLE (AIC_ROLLE) references ROLLE (AIC_ROLLE) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table STAMM_PROTOKOLL ADD AIC_ROLLE NUMBER(10) null");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add constraint FK_STAMM_PROT_ROLLE foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table STAMM_PROTOKOLL ADD AIC_ROLLE int null");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add constraint FK_STAMM_PROT_ROLLE foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                  }
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anzeigeart'");
                  b=b && addCode(iGruppe,"date")>0;
                  b=b && addCode(iGruppe,"computer")>0;
                  b=b && addCode(iGruppe,"usage")>0;
                  b=b && addCode(iGruppe,"place")>0;
                  b=b && addCode(iGruppe,"os")>0;
                  iIst = b ? Ver(4,6,2):iIst;
                }
                if(b && iIst < Ver(4,6,3) && iSoll>=Ver(4,6,3))	// 1.9.2005
                {
                  g.diskInfo("Versionsupdate 4.6.3");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Druck'");
                  int iBegriff=p.getInteger("select aic_tabellenname from tabellenname where kennung='Begriff'");
                  b = b && p.exec("delete from daten_bild where aic_tabellenname="+iBegriff+" and aic_fremd in (select aic_begriff from begriff where aic_begriffgruppe="+iGruppe+")");
                  iIst = b ? Ver(4,6,3):iIst;
                }
                if(b && iIst < Ver(4,6,4) && iSoll>=Ver(4,6,4))	// 6.9.2005
                {
                  g.diskInfo("Versionsupdate 4.6.4");
                  int iBedingung=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && addCode(iBedingung,"was absent")>0;
                  int iGet=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  b=b && addCode(iGet,"pos absent -1")>0;
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  b=addCode(iGruppe,"subtract dtReg")>0;

                  if (g.ASA())
                    b = b && p.exec("alter table ABSCHNITT ADD ZAHL double null");
                  else if (g.Oracle())
                    b = b && p.exec("alter table ABSCHNITT ADD ZAHL FLOAT null");
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table ABSCHNITT ADD ZAHL double precision null");
                    //b = b && p.exec("ALTER TABLE protokoll DROP COLUMN timestamp");
                    //b = b && p.exec("ALTER TABLE protokoll ADD timestamp datetime null CONSTRAINT DF_PROTOKOLL_TIMESTAMP DEFAULT (getdate())");
                    //b = b && p.exec("update protokoll set timestamp=(select ein from logging where aic_logging=protokoll.aic_logging)");
                  }
                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");

                  iIst = b ? Ver(4,6,4):iIst;
                }
                gauge.setText("V 4.08 - Update",4);
                if(b && iIst < Ver(4,7,1) && iSoll>=Ver(4,7,1))	// 22.9.2005
                {
                  g.diskInfo("Versionsupdate 4.7.1");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table STAMM_PROTOKOLL ADD Nr integer null");
                    b = b && p.exec("update STAMM_PROTOKOLL set Nr=1");
                    b = b && p.exec("alter table STAMM_PROTOKOLL drop PRIMARY KEY");
                    b = b && p.exec("alter table STAMM_PROTOKOLL MODIFY Nr integer not null");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add PRIMARY  KEY (AIC_STAMM, AIC_PROTOKOLL,Nr)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table STAMM_PROTOKOLL ADD Nr int null");
                    b = b && p.exec("update STAMM_PROTOKOLL set Nr=1");
                    String sPK=p.getString("select name from sysobjects where name like 'PK__STAMM_PROTOKOLL%'");
                    b = b && p.exec("ALTER TABLE STAMM_PROTOKOLL DROP CONSTRAINT "+sPK);
                    b = b && p.exec("ALTER TABLE STAMM_PROTOKOLL ALTER COLUMN Nr int NOT NULL");
                    b = b && p.exec("ALTER TABLE STAMM_PROTOKOLL ADD CONSTRAINT "+sPK+" PRIMARY KEY CLUSTERED  ([AIC_STAMM], [AIC_PROTOKOLL], [Nr])");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table STAMM_PROTOKOLL ADD Nr number(10) null");
                    b = b && p.exec("update STAMM_PROTOKOLL set Nr=1");
                    b = b && p.exec("alter table STAMM_PROTOKOLL MODIFY Nr number(10) not null");
                    b = b && p.exec("alter table STAMM_PROTOKOLL drop PRIMARY KEY");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add PRIMARY  KEY (AIC_STAMM, AIC_PROTOKOLL,Nr)");
                  }
                  iIst = b ? Ver(4,7,1):iIst;
                }
                if(b && iIst < Ver(4,7,2) && iSoll>=Ver(4,7,2))	// 9.9.2005
                {
                  g.diskInfo("Versionsupdate 4.7.2");

                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Status'");
                  int iCopy=addCode(iGruppe,"copy");
                  b=b && iCopy>0;

                  iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  if (b && iGruppe>0)
                  {
                          addBegriff(iGruppe,"BenutzerPasswort");
                          addBegriff(iGruppe,"Rolle");
                  }

                  iIst = b ? Ver(4,7,2):iIst;
                }

                if(b && iIst < Ver(4,7,3) && iSoll>=Ver(4,7,3))	// 28.9.2005
                {
                  g.diskInfo("Versionsupdate 4.7.3");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table EIGENSCHAFT ADD AIC_ROLLE integer null");
                    b = b && p.exec("alter table EIGENSCHAFT add foreign key FK_EIG_ROLLE (AIC_ROLLE) references ROLLE (AIC_ROLLE) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table EIGENSCHAFT ADD AIC_ROLLE NUMBER(10) null");
                    b = b && p.exec("alter table EIGENSCHAFT add constraint FK_EIG_ROLLE foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table EIGENSCHAFT ADD AIC_ROLLE int null");
                    b = b && p.exec("alter table EIGENSCHAFT add constraint FK_EIG_ROLLE foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                  }
                  iIst = b ? Ver(4,7,3):iIst;
                }

                if(b && iIst < Ver(4,7,4) && iSoll>=Ver(4,7,4))	// 2.11.2005
                {
                  g.diskInfo("Versionsupdate 4.7.4");
                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  if (b && iGruppe>0)
                    b=addCode(iGruppe,"is last")>0;
                  iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                  if (b && iGruppe>0)
                    b=addCode(iGruppe,"save this line")>0;
                  iIst = b ? Ver(4,7,4):iIst;
                }

                if(b && iIst < Ver(4,7,5) && iSoll>=Ver(4,7,5))	// 2.11.2005
                {
                  g.diskInfo("Versionsupdate 4.7.5");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table spalte add AIC_BEGRIFF integer null");
                    b = b && p.exec("alter table spalte add foreign key FK_FORMAT (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");

                    b = b && p.exec("create table MODUL("+
                                    "AIC_BEGRIFF       integer               not null,"+
                                    "AIC_TABELLENNAME  integer               not null,"+
                                    "AIC_FREMD         integer               not null,"+
                                    "primary key (AIC_BEGRIFF, AIC_TABELLENNAME, AIC_FREMD))");

                    b = b && p.exec("alter table MODUL add foreign key FK_MODUL_TABELLE_D_TABELLEN (AIC_TABELLENNAME) references TABELLENNAME (AIC_TABELLENNAME) on update restrict on delete restrict");
                    b = b && p.exec("alter table MODUL add foreign key FK_MODUL_MODUL_DES_BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table spalte ADD AIC_BEGRIFF NUMBER(10) null");
                    b = b && p.exec("alter table spalte add constraint FK_FORMAT foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");

                    b = b && p.exec("create table MODUL("+
                                    "AIC_BEGRIFF       NUMBER(10)               not null,"+
                                    "AIC_TABELLENNAME  NUMBER(10)               not null,"+
                                    "AIC_FREMD         NUMBER(10)               not null,"+
                                    "constraint PK_MODUL primary key (AIC_BEGRIFF, AIC_TABELLENNAME, AIC_FREMD))");
                    b = b && p.exec("alter table MODUL add constraint FK_MODUL_TABELLE_D_TABELLEN foreign key (AIC_TABELLENNAME) references TABELLENNAME (AIC_TABELLENNAME)");
                    b = b && p.exec("alter table MODUL add constraint FK_MODUL_MODUL_DES_BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table spalte ADD AIC_BEGRIFF int null");
                    b = b && p.exec("alter table spalte add constraint FK_FORMAT foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");

                    b = b && p.exec("create table MODUL("+
                                    "AIC_BEGRIFF       int               not null,"+
                                    "AIC_TABELLENNAME  int               not null,"+
                                    "AIC_FREMD         int               not null,"+
                                    "primary key (AIC_BEGRIFF, AIC_TABELLENNAME, AIC_FREMD))");
                    b = b && p.exec("alter table MODUL add constraint FK_MODUL_TABELLE_D_TABELLEN foreign key (AIC_TABELLENNAME) references TABELLENNAME (AIC_TABELLENNAME)");
                    b = b && p.exec("alter table MODUL add constraint FK_MODUL_MODUL_DES_BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                  }

                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Format'");
                  b = b && p.exec("update spalte set aic_begriff=(select aic_begriff from begriff where format=begriff.kennung"+(g.MS()?" and len(begriff.kennung+'*')=len(format+'*')":"")+" and aic_begriffgruppe="+iGruppe+
                                  ") where format is not null");
                  b = b && p.exec("alter table spalte drop"+(g.ASA()?"":" COLUMN")+" FORMAT");
                  iIst = b ? Ver(4,7,5):iIst;
                }
                if(b && iIst < Ver(4,7,6) && iSoll>=Ver(4,7,6))	// 8.11.2005
                {
                  g.diskInfo("Versionsupdate 4.7.6");
                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  if (b && iGruppe>0)
                  {
                    b = addCode(iGruppe, "is JokerStt") > 0;
                    b = addCode(iGruppe, "is Joker") > 0;
                    b = addCode(iGruppe, "is StringJoker") > 0;
                    b = addCode(iGruppe, "is Stichtag") > 0;
                  }
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  if (b && iGruppe>0)
                  {
                    b = addCode(iGruppe, "get String-number") > 0;
                    b = addCode(iGruppe, "get String-Date2") > 0;
                  }
                  iIst = b ? Ver(4,7,6):iIst;
                }
                gauge.setText("V 4.09 - Update",5);
                if(b && iIst < Ver(4,8,1) && iSoll>=Ver(4,8,1))	// 23.11.2005
                {
                  g.diskInfo("Versionsupdate 4.8.1");
                  //int iANR=p.getInteger("select aic_eigenschaft from eigenschaft where kennung='Mitarbeiter'");
                  int iANR = p.getInteger("select aic_stammtyp from stammtyp where Kennung='Arbeitnehmer'");
                  String sANR=Static.SQL_in(SQL.getVector("select aic_eigenschaft from eigenschaft where aic_stammtyp="+iANR,g));
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table bew_pool add ANR integer null");
                    b = b && p.exec("alter table bew_pool add LDATE integer null");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table bew_pool add ANR NUMBER(10) null");
                    b = b && p.exec("alter table bew_pool add LDATE NUMBER(10) null");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table bew_pool add ANR int null");
                    b = b && p.exec("alter table bew_pool add LDATE int null");
                  }
                  gauge.setText("LDATE anlegen",6);
                  if (g.ASA() || g.MS())
                    b = b && p.exec("update bew_pool set ldate=year(gueltig)*10000+month(gueltig)*100+day(gueltig)");
                  else if (g.Oracle())
                    b = b && p.exec("update bew_pool set ldate=to_number(to_char(gueltig,'YYYYMMDD'))");
                  gauge.setText("ANR anlegen",7);
                  b = b && p.exec("update bew_pool set anr=("+g.first("aic_stamm from bew_stamm where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft"+sANR)+")");
                  gauge.setText("Index erstellen",8);
                  b = b && p.exec("create index BEW_TEST1 on BEW_POOL (AIC_BEWEGUNGSTYP,ANR,LDATE asc)");
                  b = b && p.exec("create index BEW_TEST2 on BEW_POOL (AIC_BEWEGUNGSTYP,LDATE asc)");
                  gauge.setText("V 4.09 - Erweiterungen",9);
                  int iOp = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                  if (b && iOp>0)
                    addCode(iOp,"diff prev");
                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");
                  iIst = b ? Ver(4,8,1):iIst;
                }
                if(b && iIst < Ver(4,8,2) && iSoll>=Ver(4,8,2))	// 23.11.2005
                {
                  g.diskInfo("Versionsupdate 4.8.2");
                  int iOp = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                  if (b && iOp>0)
                    addCode(iOp,"mul if");
                  iIst = b ? Ver(4,8,2):iIst;
                }
                if(b && iIst < Ver(4,8,3) && iSoll>=Ver(4,8,3))	// 11.1.2006
                {
                  g.diskInfo("Versionsupdate 4.8.3");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anzeigeart'");
                  b=b && addCode(iGruppe,"anr")>0;
                  b=b && addCode(iGruppe,"ldate")>0;
                  iIst = b ? Ver(4,8,3):iIst;
                }
                if(b && iIst < Ver(4,8,4) && iSoll>=Ver(4,8,4))	// 13.1.2006
                {
                  g.diskInfo("Versionsupdate 4.8.4");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Tabellentyp'");
                  int iAic=p.getInteger("select aic_code from code where aic_begriffgruppe="+iGruppe+" and code.kennung='Gesperrt'");
                  if (iAic>0)
                  {
                    int iAic2=p.getInteger("select aic_code from code where aic_begriffgruppe="+iGruppe+" and code.kennung='Anzeige'");
                    p.exec("update begriff set aic_code="+iAic2+" where aic_code="+iAic);
                    p.exec("delete from code where aic_code="+iAic);
                    g.diskInfo("Tabellentyp Gesperrt gelöscht");
                  }
                  if (g.ASA())
                    b = b && p.exec("alter table PARAMETER MODIFY PARAMETERZEILE char(100) null");
                  else if (g.MS())
                    b = b && p.exec("ALTER TABLE PARAMETER ALTER COLUMN PARAMETERZEILE varchar(100)");
                  else if (g.Oracle())
                    b = b && p.exec("alter table PARAMETER MODIFY PARAMETERZEILE varchar(100)");
                  iIst = b ? Ver(4,8,4):iIst;
                }
                gauge.setText("Rollenvergabe",10);
                if(b && iIst < Ver(4,9,1) && iSoll>=Ver(4,9,1))	// 9.9.2005
                {
                  g.diskInfo("Versionsupdate 4.9.1");
                  if (g.Oracle())
                    g.exec("drop index stamm_protokoll_pk");
                  else if (g.MS())
                    g.exec("drop index stamm_protokoll.stamm_protokoll_pk");
                  int iANR = p.getInteger("select aic_stammtyp from stammtyp where Kennung='Arbeitnehmer'");
                  if (p.getInteger("select count(*) from stammview2 where aic_rolle is not null and aic_stammtyp="+iANR)==0)
                  {
                    int iBewerber = p.getInteger("select aic_eigenschaft from eigenschaft where Kennung='Bewerber'");
                    int iGebDat = p.getInteger("select aic_eigenschaft from eigenschaft where Kennung='Geburtsdatum'");
                    Vector Vec = SQL.getVector("select distinct aic_stamm from poolview where spalte_double=1 and aic_eigenschaft=" + iBewerber, g);
                    int iRolAnr = p.getInteger("select aic_Rolle from Rolle where Kennung='Mitarbeiter'");
                    if(iRolAnr == 0) {
                      p.add("Kennung", "Mitarbeiter");
                      p.add("aic_stammtyp", iANR);
                      iRolAnr = p.insert("Rolle", true);
                    }
                    if(!Vec.isEmpty())
                    {
                      int iRolBew = p.getInteger("select aic_Rolle from Rolle where Kennung='Bewerber'");
                      if(iRolBew == 0)
                      {
                        p.add("Kennung", "Bewerber");
                        p.add("aic_stammtyp", iANR);
                        iRolBew = p.insert("Rolle", true);
                      }
                      g.diskInfo(g.exec("update stamm_protokoll set aic_rolle=" + iRolBew + " where " + g.in("aic_stamm", Vec))+" auf Rolle Bewerber geändert");
                    }
                    g.diskInfo(g.exec("update stamm_protokoll set aic_rolle=" + iRolAnr +
                           " where aic_rolle is null and aic_stamm in (select aic_stamm from stamm where aic_stammtyp=" + iANR + ")")+" auf Rolle Mitarbeiter geändert");

                    Tabellenspeicher Tab = new Tabellenspeicher(g,"select p.aic_stamm,aic_protokoll,bezeichnung,(select gultig_von from poolview2 where aic_stamm=s.aic_stamm and aic_eigenschaft=" +
                        iGebDat +") Eintritt from stamm_protokoll p join stamm s on p.aic_stamm=s.aic_stamm where p.pro_aic_protokoll is null and s.aic_stammtyp=" + iANR, true);
                    int iCopy = p.getInteger("select aic_code from code where kennung='copy'");
                    for(Tab.moveFirst(); !Tab.eof(); Tab.moveNext()) {
                      p.add("aic_stamm", Tab.getI("aic_stamm"));
                      p.add("aic_protokoll", Tab.getI("aic_protokoll"));
                      p.add("Nr", 2);
                      p.add("Bezeichnung", Tab.getS("Bezeichnung"));
                      if(!Tab.isNull("Eintritt"))
                        p.add("Eintritt", Tab.getDate("Eintritt"));
                      p.add("aic_code", iCopy);
                      p.insert("stamm_protokoll", false);
                    }
                  }
                  iIst = b ? Ver(4,9,1):iIst;
                }
                if(b && iIst < Ver(4,9,2) && iSoll>=Ver(4,9,2))	// 20.1.2006
                {
                  g.diskInfo("Versionsupdate 4.9.2");
                  gauge.setText("V 5.00 - Erweiterungen",15);
                  if (g.ASA() || g.MS())
                    b=b && p.exec("alter table BEGRIFF add TOD bit null");
                  else if (g.Oracle())
                    b=b && p.exec("alter table BEGRIFF add TOD NUMBER(1) null");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
                  b=b && addCode(iGruppe,"*UseVec*")>0;
                  int iAuth = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='authorisation'");
                  b=b && addCode(iAuth,"get user-stamm")>0;
                  int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iAnw,"clean prot")>0;
                  iIst = b ? Ver(4,9,2):iIst;
                }
                if(b && iIst < Ver(4,9,3) && iSoll>=Ver(4,9,3))	// 26.1.2006
                {
                  g.diskInfo("Versionsupdate 4.9.3");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table BEGRIFF add USED integer null default 0");
                    b = b && p.exec("update BEGRIFF set USED=0");
                    b = b && p.exec("alter table BEGRIFF MODIFY USED integer not null default 0");

                    b = b && p.exec("alter table EIGENSCHAFT add BREITE integer null");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table BEGRIFF add USED NUMBER(10) null");
                    b = b && p.exec("update BEGRIFF set USED=0");
                    b = b && p.exec("alter table BEGRIFF MODIFY USED number(10) default 0");
                    b = b && p.exec("alter table EIGENSCHAFT add BREITE NUMBER(10) null");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table BEGRIFF add USED int null default 0");
                    b = b && p.exec("update BEGRIFF set USED=0");
                    b = b && p.exec("ALTER TABLE BEGRIFF ALTER COLUMN USED int NOT NULL");
                    b = b && p.exec("alter table EIGENSCHAFT add BREITE int null");
                  }

                  iIst = b ? Ver(4,9,3):iIst;
                }
                if(b && iIst < Ver(4,9,4) && iSoll>=Ver(4,9,4))	// 3.2.2006
                {
                  g.diskInfo("Versionsupdate 4.9.4");
                  b = b && p.exec("update tabellenname set fixe_sprache=null where kennung='Benutzergruppe'");
                  if (g.ASA())
                    b = b && p.exec("alter table MANDANT add Versuche integer null");
                  else if (g.Oracle())
                    b = b && p.exec("alter table MANDANT add Versuche NUMBER(10) null");
                  else if (g.MS())
                    b = b && p.exec("alter table MANDANT add Versuche int null");
                  iIst = b ? Ver(4,9,4):iIst;
                }

                gauge.setText("V 5.01 - Erweiterungen",20);
                if(b && iIst < Ver(5,0,1) && iSoll>=Ver(5,0,1))	// 18.4.2006
                {
                  g.diskInfo("Versionsupdate 5.0.1");
                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  p.clear();
                  b=b && addBegriff(iGruppe,"BewBew")>0;
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table BEW_POOL add BEW2_AIC_BEW_POOL integer null");
                    b = b && p.exec("alter table BEW_POOL add foreign key FK_BEW_POOL_BEWBEW (BEW2_AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL) on update restrict on delete restrict");
                    b = b && p.exec("alter table EIGENSCHAFT add AIC_BEWEGUNGSTYP integer null");
                    b = b && p.exec("alter table EIGENSCHAFT add foreign key FK_EIGENSCHAFT_BEW (AIC_BEWEGUNGSTYP) references BEWEGUNGSTYP (AIC_BEWEGUNGSTYP) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table BEW_POOL add BEW2_AIC_BEW_POOL NUMBER(10) null");
                    b = b && p.exec("alter table BEW_POOL add constraint FK_BEW_POOL_BEWBEW foreign key (BEW2_AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL)");
                    b = b && p.exec("alter table EIGENSCHAFT add AIC_BEWEGUNGSTYP NUMBER(10) null");
                    b = b && p.exec("alter table EIGENSCHAFT add constraint FK_EIGENSCHAFT_BEW foreign key (AIC_BEWEGUNGSTYP) references BEWEGUNGSTYP (AIC_BEWEGUNGSTYP)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table BEW_POOL add BEW2_AIC_BEW_POOL int null");
                    b = b && p.exec("alter table BEW_POOL add constraint FK_BEW_POOL_BEWBEW foreign key (BEW2_AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL)");
                    b = b && p.exec("alter table EIGENSCHAFT add AIC_BEWEGUNGSTYP int null");
                    b = b && p.exec("alter table EIGENSCHAFT add constraint FK_EIGENSCHAFT_BEW foreign key (AIC_BEWEGUNGSTYP) references BEWEGUNGSTYP (AIC_BEWEGUNGSTYP)");
                  }
                  b = b && p.exec("create index Ref_696_FK on BEW_POOL (BEW2_AIC_BEW_POOL asc)");

                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  if (b && iGruppe>0)
                    b=b && addCode(iGruppe,"init Bew")>0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='convert'");
                  if (b && iGruppe>0)
                    b=b && addCode(iGruppe,"swap Bew")>0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  if (b && iGruppe>0)
                  {
                    b=b && addCode(iGruppe,"get Bew")>0;
                    b=b && addCode(iGruppe,"get real Bew")>0;
                  }
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                  if (b && iGruppe>0)
                  {
                    b=b && addCode(iGruppe,"remember Bew")>0;
                    b=b && addCode(iGruppe,"save Bew")>0;
                  }
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  if (b && iGruppe>0)
                  {
                    b=b && addCode(iGruppe,"is Bew")>0;
                    b=b && addCode(iGruppe,"is real Bew")>0;
                  }
                  iIst = b ? Ver(5,0,1):iIst;
                }
                if(b && iIst < Ver(5,0,2) && iSoll>=Ver(5,0,2))	// 27.4.2006
                {
                  g.diskInfo("Versionsupdate 5.0.2");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table COMPUTER MODIFY HOSTNAME char(40) null");
                    b = b && p.exec("alter table COMPUTER MODIFY KENNUNG char(40) null");
                    b = b && p.exec("alter table DATEN_BILD MODIFY FILENAME char(100) null");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("ALTER TABLE COMPUTER ALTER COLUMN HOSTNAME varchar(40)");
                    b = b && p.exec("ALTER TABLE COMPUTER ALTER COLUMN KENNUNG varchar(40)");
                    b = b && p.exec("ALTER TABLE DATEN_BILD ALTER COLUMN FILENAME varchar(100)");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table COMPUTER MODIFY HOSTNAME varchar(40)");
                    b = b && p.exec("alter table COMPUTER MODIFY KENNUNG varchar(40)");
                    b = b && p.exec("alter table DATEN_BILD MODIFY FILENAME varchar(100)");
                  }
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iGruppe,"add JokerBew")>0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  b=b && addCode(iGruppe,"init JokerBew")>0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
                  b=b && addCode(iGruppe,"*JokerBew*")>0;

                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");
                  iIst = b ? Ver(5,0,2):iIst;
                }
                if(b && iIst < Ver(5,0,3) && iSoll>=Ver(5,0,3))	// 3.5.2006
                {
                  g.diskInfo("Versionsupdate 5.0.3");
                  if (g.ASA())
                    b = b && p.exec("update bew_pool p set gueltig=(select gueltig from bew_pool where aic_bew_pool=p.bew2_aic_bew_pool),"+
                                    "ldate=(select ldate from bew_pool where aic_bew_pool=p.bew2_aic_bew_pool) where bew2_aic_bew_pool is not null and gueltig is null");
                  b = b && p.exec("update bew_pool set ldate=0 where gueltig is null");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table ANSICHT add ANS_AIC_ANSICHT integer null");
                    b = b && p.exec("alter table ANSICHT add foreign key FK_ANSICHT_ANSICHT (ANS_AIC_ANSICHT) references ANSICHT (AIC_ANSICHT) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table ANSICHT add ANS_AIC_ANSICHT NUMBER(10) null");
                    b = b && p.exec("alter table ANSICHT add constraint FK_ANSICHT_ANSICHT foreign key (ANS_AIC_ANSICHT) references ANSICHT (AIC_ANSICHT)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table ANSICHT add ANS_AIC_ANSICHT int null");
                    b = b && p.exec("alter table ANSICHT add constraint FK_ANSICHT_ANSICHT foreign key (ANS_AIC_ANSICHT) references ANSICHT (AIC_ANSICHT)");
                  }
                  int iHS=-1;
                  int i1=0;
                  int iAic=0;
                  Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_ansicht,aic_hauptschirm,int1 from ansicht order by aic_hauptschirm,aic_ansicht",true);
                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    if (iHS==Tab.getI("aic_hauptschirm") && i1==Tab.getI("int1"))
                    {
                      b = b && p.exec("update ansicht set ANS_AIC_ANSICHT="+iAic+" where aic_ansicht="+Tab.getI("aic_ansicht"));
                    }
                    else
                    {
                      iHS=Tab.getI("aic_hauptschirm");
                      i1=Tab.getI("int1");
                    }
                    iAic=Tab.getI("aic_ansicht");
                  }
                  iIst = b ? Ver(5,0,3):iIst;
                }
                if(b && iIst < Ver(5,0,4) && iSoll>=Ver(5,0,4))	// 11.5.2006
                {
                  g.diskInfo("Versionsupdate 5.0.4");
                  b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'"),"pos not null")>0;
                  int iSave = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                  b=b && addCode(iSave,"JDBC Import")>0;
                  b=b && p.exec("update code set kennung='import exchange',aic_begriffgruppe="+iSave+" where kennung='reserve0'");
                  iIst = b ? Ver(5,0,4):iIst;
                }
                if(b && iIst < Ver(5,0,5) && iSoll>=Ver(5,0,5))	// 29.5.2006
                {
                  g.diskInfo("Versionsupdate 5.0.5");
                  b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anlage'"),"Undelete")>0;
                  b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'"),"clear old data")>0;
                  iIst = b ? Ver(5,0,5):iIst;
                }
                if(b && iIst < Ver(5,0,6) && iSoll>=Ver(5,0,6))	// 31.5.2006
                {
                  g.diskInfo("Versionsupdate 5.0.6");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                  b=b && addCode(iGruppe,"<")>0;
                  b=b && addCode(iGruppe,">")>0;
                  b=b && addCode(iGruppe,"=")>0;
                  b=b && addCode(iGruppe,"<=")>0;
                  b=b && addCode(iGruppe,">=")>0;
                  b=b && addCode(iGruppe,"<>")>0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  b=b && addCode(iGruppe,"next vb")>0;
                  b=b && addCode(iGruppe,"prev vb")>0;

                  iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  b=b && addBegriff(iGruppe,"FixBild")>0;
                  b=b && addBegriff(iGruppe,"FixDoku")>0;

                  if (g.ASA())
                  {
                    b = b && p.exec("create table ASERVER(" +
                                    "AIC_ASERVER      integer  not null default AUTOINCREMENT," +
                                    "AIC_LOGGING      integer  not null," +
                                    "AIC_BEGRIFF      integer  not null," +
                                    "AIC_STAMM        integer  null," +
                                    "VON              datetime null," +
                                    "BIS              DateTime null,"+
                                    "ART              integer  null," +
                                    "ERSTELLT         datetime null," +
                                    "ANFANG           DateTime null,"+
                                    "ENDE             DateTime null,"+
                                    "STATUS           integer  null,"+
                                    "primary key (AIC_ASERVER))");
                    b=b && p.exec("alter table ASERVER add foreign key FK_ASERVER_RELATION__LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");

                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("create table ASERVER("+
                                    "AIC_ASERVER        NUMBER(10)  not null,"+
                                    "AIC_LOGGING        NUMBER(10)  not null,"+
                                    "AIC_BEGRIFF        NUMBER(10)  not null,"+
                                    "AIC_STAMM          NUMBER(10)  null," +
                                    "VON                Date null," +
                                    "BIS                Date null," +
                                    "ART                NUMBER(10)  null," +
                                    "ERSTELLT           Date null," +
                                    "ANFANG             Date null,"+
                                    "ENDE               Date null,"+
                                    "STATUS             NUMBER(10) null,"+
                                    "constraint PK_ASERVER primary key (AIC_ASERVER))");
                    b = b && p.exec("alter table ASERVER add constraint FK_ASERVER_RELATION__LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    p.createAllSequences();
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("create table ASERVER("+
                                    "AIC_ASERVER      int  not null identity,"+
                                    "AIC_LOGGING      int  not null," +
                                    "AIC_BEGRIFF      int  not null," +
                                    "AIC_STAMM        int  null," +
                                    "VON              datetime null," +
                                    "BIS              DateTime null,"+
                                    "ART              int  null," +
                                    "ERSTELLT         datetime null," +
                                    "ANFANG           DateTime null,"+
                                    "ENDE             DateTime null,"+
                                    "STATUS           int  null,"+
                                    "primary key (AIC_ASERVER))");
                    b = b && p.exec("alter table ASERVER add constraint FK_ASERVER_RELATION__LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                  }
                  iIst = b ? Ver(5,0,6):iIst;
                }
                if(b && iIst < Ver(5,0,7) && iSoll>=Ver(5,0,7))	// 13.6.2006
                {
                  g.diskInfo("Versionsupdate 5.0.7");
                  b=b && addBegriff(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'"),"BewCount")>0;
                  b=addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'"),"compress2")>0;
                  b=p.exec("update spalte set bits=bits-4 where"+g.bit("bits",4 /* cstSumme */));
                  iIst = b ? Ver(5,0,7):iIst;
                }
                if(b && iIst < Ver(5,0,8) && iSoll>=Ver(5,0,8))	// 21.6.2006
                {
                  g.diskInfo("Versionsupdate 5.0.8");
                  int iAbfrage=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Abfrage'");
                  b=p.exec("update begriff set bits=bits-1024 where aic_begriffgruppe="+iAbfrage+" and"+g.bit("bits",1024 /* cstKeineLeiste */));
                  b=p.exec("update begriff set bits=bits-2048 where aic_begriffgruppe="+iAbfrage+" and"+g.bit("bits",2048 /* cstTitelzeile */));
                  b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Ergebnisart'"),"count_distinct")>0;
                  b=b && addCode(p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'"),"open URL")>0;
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table LAYOUT add AIC_MANDANT integer null");
                    b = b && p.exec("alter table LAYOUT add foreign key FK_LAYOUT_MANDANT (AIC_MANDANT) references MANDANT (AIC_MANDANT) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table LAYOUT add AIC_MANDANT NUMBER(10) null");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_MANDANT foreign key (AIC_MANDANT) references MANDANT (AIC_MANDANT)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table LAYOUT add AIC_MANDANT int null");
                    b = b && p.exec("alter table LAYOUT add constraint FK_LAYOUT_MANDANT foreign key (AIC_MANDANT) references MANDANT (AIC_MANDANT)");
                  }

                  iIst = b ? Ver(5,0,8):iIst;
                }
                gauge.setText("V 5.02 - Erweiterungen",30);
                if(b && iIst < Ver(5,1,1) && iSoll>=Ver(5,1,1))	// 3.8.2006
                {
                  g.diskInfo("Versionsupdate 5.1.1");
                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  if (b && iGruppe>0)
                    addBegriff(iGruppe,"CalcDatum");
                  int iOp = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                  if (b && iOp>0)
                  {
                    addCode(iOp,"add days");
                    addCode(iOp,"init");
                    addCode(iOp,"add prev days");
                  }

                  iIst = b ? Ver(5,1,1):iIst;
                }
                if(b && iIst < Ver(5,1,2) && iSoll>=Ver(5,1,2))	// 14.9.2006
                {
                  g.diskInfo("Versionsupdate 5.1.2");
                  int iAbfrage = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Abfrage'");
                  b=p.exec("update begriff set bits=bits-1024 where aic_begriffgruppe="+iAbfrage+" and"+g.bit("bits",1024 /* cstKeineLeiste */));
                  b = p.exec("update begriff set bits=bits-"+0x80000000l+" where aic_begriffgruppe=" + iAbfrage + " and" + g.bit("bits", 0x80000000l /* cstZweizeilig */));

                  int iBedingung=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && addCode(iBedingung,"pos manual")>0;
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  b=b && addCode(iGruppe,"get general finish")>0;
                  b=b && addCode(iGruppe,"get superior finish")>0;
                  b=b && addCode(iGruppe,"get personal finish")>0;

                  iIst = b ? Ver(5,1,2):iIst;
                }
                if(b && iIst < Ver(5,1,3) && iSoll>=Ver(5,1,3))	// 21.9.2006
                {
                  g.diskInfo("Versionsupdate 5.1.3");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iGruppe,"destroy all")>0;
                  b=b && addCode(iGruppe,"destroy row")>0;
                  b=b && addCode(iGruppe,"del del bew")>0;
                  iIst = b ? Ver(5,1,3):iIst;
                }
                gauge.setText("V 5.03 - Erweiterungen",40);
                if(b && iIst < Ver(5,2,1) && iSoll>=Ver(5,2,1))	// 24.1.2007
                {
                  g.diskInfo("Versionsupdate 5.2.1");

                  if (g.ASA())
                  {
                    b = b && p.exec("alter table bew_pool add ZONE integer null");
                    b = b && p.exec("alter table hauptschirm add AIC_STAMMTYP integer null");
                    b = b && p.exec("alter table hauptschirm add foreign key FK_HS_STT_DES_HS (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP) on update restrict on delete restrict");
                    b = b && p.exec("alter table formular add AIC_ABFRAGE integer null");
                    b = b && p.exec("alter table formular add foreign key FK_FORM_ABF_DES_FORM (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table bew_pool add ZONE NUMBER(10) null");
                    b = b && p.exec("alter table hauptschirm add AIC_STAMMTYP NUMBER(10) null");
                    b = b && p.exec("alter table hauptschirm add constraint FK_HS_STT_DES_HS foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b = b && p.exec("alter table formular add AIC_ABFRAGE NUMBER(10) null");
                    b = b && p.exec("alter table formular add constraint FK_FORM_ABF_DES_FORM foreign key (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table bew_pool add ZONE int null");
                    b = b && p.exec("alter table hauptschirm add AIC_STAMMTYP int null");
                    b = b && p.exec("alter table hauptschirm add constraint FK_HS_STT_DES_HS foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b = b && p.exec("alter table formular add AIC_ABFRAGE int null");
                    b = b && p.exec("alter table formular add constraint FK_FORM_ABF_DES_FORM foreign key (AIC_ABFRAGE) references ABFRAGE (AIC_ABFRAGE)");
                  }
                  //DateWOD DW=new DateWOD();
                  b=b && p.exec("update bew_pool set zone="+g.getZone());
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  for (int i=10;i<30;i++)
                      b = b && addCode(iRes, "reserve"+i) > 0;

                  p.add("Kennung","ZIP");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iZip = p.insert("begriffgruppe",true);
                  int iSave=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                  int iAnw=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && p.exec("update code set kennung='Autoimport2',aic_begriffgruppe="+iSave+" where kennung='reserve1'");
                  b=b && p.exec("update code set kennung='convert mecs',aic_begriffgruppe="+iAnw+" where kennung='reserve2'");
                  b=b && p.exec("update code set kennung='zip create',aic_begriffgruppe="+iZip+" where kennung='reserve3'");
                  b=b && p.exec("update code set kennung='zip write lizenz',aic_begriffgruppe="+iZip+" where kennung='reserve4'");
                  b=b && p.exec("update code set kennung='zip create file',aic_begriffgruppe="+iZip+" where kennung='reserve5'");
                  b=b && p.exec("update code set kennung='zip save string',aic_begriffgruppe="+iZip+" where kennung='reserve6'");
                  b=b && p.exec("update code set kennung='zip save number',aic_begriffgruppe="+iZip+" where kennung='reserve7'");
                  b=b && p.exec("update code set kennung='zip close file',aic_begriffgruppe="+iZip+" where kennung='reserve8'");
                  b=b && p.exec("update code set kennung='zip close',aic_begriffgruppe="+iZip+" where kennung='reserve9'");
                  b=b && p.exec("update code set kennung='Autoimport3',aic_begriffgruppe="+iSave+" where kennung='Anweisung19'");

                  int iAbfrage = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Abfrage'");
                  b = p.exec("update begriff set bits=bits-"+0x02000000+" where aic_begriffgruppe=" + iAbfrage + " and" + g.bit("bits",0x02000000 /* cstOLE */));
                  b = p.exec("update begriff set bits=bits-"+0x20000000+" where aic_begriffgruppe=" + iAbfrage + " and" + g.bit("bits",0x20000000 /* cstSuchen */));
                  iIst = b ? Ver(5,2,1):iIst;
                }
                if(b && iIst < Ver(5,2,2) && iSoll>=Ver(5,2,2))	// 1.2.2007
                {
                  g.diskInfo("Versionsupdate 5.2.2");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table spalte add AIC_ROLLE integer null");
                    b = b && p.exec("alter table spalte add foreign key FK_SPALTE_ROLLE_D_SP (AIC_ROLLE) references ROLLE (AIC_ROLLE) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table spalte add AIC_ROLLE NUMBER(10) null");
                    b = b && p.exec("alter table spalte add constraint FK_SPALTE_ROLLE_D_SP foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table spalte add AIC_ROLLE int null");
                    b = b && p.exec("alter table spalte add constraint FK_SPALTE_ROLLE_D_SP foreign key (AIC_ROLLE) references ROLLE (AIC_ROLLE)");
                  }
                  int iGebDat = p.getInteger("select aic_eigenschaft from eigenschaft where Kennung='Geburtsdatum'");
                  int iANR = p.getInteger("select aic_stammtyp from stammtyp where Kennung='Arbeitnehmer'");
                  int iAnz=g.exec("delete from stammpool where pro_aic_protokoll is not null and aic_eigenschaft="+iGebDat);
                  if (iAnz>0)
                    g.diskInfo(iAnz+" Geburtsdaten gelöscht");
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from (select aic_stamm,bezeichnung,eintritt"+
                      ",(select gultig_von from stammpool where aic_stamm=v.aic_stamm and pro2_aic_protokoll is null and aic_eigenschaft="+iGebDat+") Geb"+
                      " from stammview2 v where aic_stammtyp="+iANR+" and aic_rolle is null) x where geb<>eintritt or eintritt is null and geb is not null",true);
                  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    g.diskInfo("Eintrittsdatum von Person "+Tab.getS("Bezeichnung")+" geändert");
                    g.exec("update stamm_protokoll set eintritt="+g.DateTimeToString(Tab.getDate("Geb"))+
                           "where aic_stamm="+Tab.getI("aic_stamm")+" and aic_rolle is null and pro_aic_protokoll is null");
                  }
                  iIst = b ? Ver(5,2,2):iIst;
                }
                if(b && iIst < Ver(5,2,3) && iSoll>=Ver(5,2,3))	// 15.2.2007
                {
                  g.diskInfo("Versionsupdate 5.2.3");
                  g.diskInfo(ViewRebuild(g, iIst) + " Views erneuert");
                  int iDate=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  b=b && addCode(iDate,"add zone")>0;
                  b=b && addCode(iDate,"subtract zone")>0;
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anzeigeart'");
                  b=b && addCode(iGruppe,"zone")>0;
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iGruppe,"set general finish")>0;
                  b=b && addCode(iGruppe,"set superior finish")>0;
                  b=b && addCode(iGruppe,"set personal finish")>0;
                  // alte Parameter löschen
                  Vector VecNG=SQL.getVector("select aic_nebengruppe from nebengruppe where kennung in ('GruppenFilter','Fusszeile Text','Fusszeile Rechts','Rand','Kopfzeile Text','Kopfzeile Rechts'"+
                                     ",'Titel Schrift','Text Schrift','Kopfzeile Mitte','Kopfzeile Bild','Fusszeile Mitte','Fusszeile Bild')",g);
                  g.diskInfo(g.exec("delete from parameter where "+g.in("aic_nebengruppe",VecNG))+" Parameter gelöscht");
                  // alete drucke löschen
                  Vector<Integer> VecDrucke=SQL.getVector("select aic_begriff from begriff where not"+g.bit("bits",0x400/*cstPntNeuerDruck*/),g);
                  if (!VecDrucke.isEmpty())
                    g.exec("delete from druck_zuordnung where " + g.in("aic_begriff",VecDrucke));
                  iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Druck'");
                  VecDrucke=SQL.getVector("select aic_begriff from begriff where aic_begriffgruppe="+iGruppe+
                                          " and (select count(*) from druck_zuordnung where aic_begriff=begriff.aic_begriff)=0",g);
                  if (!VecDrucke.isEmpty())
                  {
                    g.exec("delete from begriff_zuordnung where " + g.in("beg_aic_begriff", VecDrucke));
                    g.exec("delete from darstellung where " + g.in("aic_begriff", VecDrucke));
                    g.exec("delete from bew_begriff where " + g.in("aic_begriff", VecDrucke));
                    g.diskInfo(g.exec("delete from begriff where " + g.in("aic_begriff", VecDrucke))+" alte Drucke gelöscht");
                  }
                  g.diskInfo("Indexe erstellen");
                  g.bISQL = true;
                  if (g.MS())
                  {
                    g.exec("drop index BEW_POOL.Ref_686_FK");
                    g.exec("drop index BEW_STAMM.Ref_703_FK");
                    g.exec("drop index Zwischenspeicher.IND_DBC_ZSS");
                  }
                  else
                  {
                    g.exec("drop index Ref_686_FK");
                    g.exec("drop index Ref_703_FK");
                    g.exec("drop index IND_DBC_ZSS");
                  }
                  g.bISQL = false;
                  g.exec("create index Ref_686_FK on BEW_POOL (AIC_BEWEGUNGSTYP asc)");
                  g.exec("create index Ref_703_FK on BEW_STAMM (AIC_STAMM asc,AIC_EIGENSCHAFT asc)");
                  g.exec("create index IND_DBC_ZSS on Zwischenspeicher (Kennung,Terminal,Zwischentext asc)");
                  int i = g.exec("delete from zwischenspeicher where terminal is null");
                  if (i > 0)
                    g.diskInfo(i + " Zeilen aus Zwischenspeicher gelöscht");
                  iIst = b ? Ver(5,2,3):iIst;
                }
                if(b && iIst < Ver(5,2,4) && iSoll>=Ver(5,2,4))	// 22.2.2007
                {
                  g.diskInfo("Versionsupdate 5.2.4");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table zwischenspeicher add ZONE integer null");
                    b = b && p.exec("alter table benutzer add AIC_LOGGING integer null");
                    b = b && p.exec("alter table benutzergruppe add AIC_LOGGING integer null");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table zwischenspeicher add ZONE NUMBER(10) null");
                    b = b && p.exec("alter table benutzer add AIC_LOGGING NUMBER(10) null");
                    b = b && p.exec("alter table benutzergruppe add AIC_LOGGING NUMBER(10) null");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table zwischenspeicher add ZONE int null");
                    b = b && p.exec("alter table benutzer add AIC_LOGGING int null");
                    b = b && p.exec("alter table benutzergruppe add AIC_LOGGING int null");
                  }
                  b=b && p.exec("update zwischenspeicher set zone="+g.getZone());
                  p.add("Kennung","zone");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iZone = p.insert("begriffgruppe",true);
                  b=b && p.exec("update code set aic_begriffgruppe="+iZone+" where kennung='add zone'");
                  b=b && p.exec("update code set aic_begriffgruppe="+iZone+" where kennung='subtract zone'");
                  b=b && addCode(iZone,"get zone")>0;
                  b=b && addCode(iZone,"init zone")>0;
                  b=b && addCode(iZone,"set utc")>0;
                  iIst = b ? Ver(5,2,4):iIst;
                }
                if(b && iIst < Ver(5,2,5) && iSoll>=Ver(5,2,5))	// 9.3.2007
                {
                  g.diskInfo("Versionsupdate 5.2.5");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table spalte_zuordnung add aic_stammtyp integer null");
                    b = b && p.exec("alter table spalte_zuordnung drop foreign key FK_SPALTE_Z_STAMM_DER_STAMM");
                    b = b && p.exec("alter table spalte_zuordnung modify aic_stamm null");
                    b = b && p.exec("alter table spalte_zuordnung add foreign key FK_SZ_STT_DES_SZ (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP) on update restrict on delete restrict");
                    b = b && p.exec("alter table SPALTE_ZUORDNUNG add foreign key FK_SPALTE_Z_STAMM_DER_STAMM (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");

                    b = b && p.exec("alter table benutzer add foreign key FK_LOG_DES_BEN (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
                    b = b && p.exec("alter table benutzergruppe add foreign key FK_LOG_DES_BG (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table spalte_zuordnung add aic_stammtyp NUMBER(10) null");
                    b = b && p.exec("alter table spalte_zuordnung drop constraint fk_spalte_z_ref_1022_stamm");
                    b = b && p.exec("alter table spalte_zuordnung drop constraint PK_Spalte_Zuordnung");
                    b=  b && p.exec("alter table spalte_zuordnung modify aic_stamm null");
                    b = b && p.exec("alter table spalte_zuordnung add constraint fk_spalte_z_ref_1022_stamm foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                    b = b && p.exec("alter table spalte_zuordnung add constraint FK_SZ_STT_DES_SZ foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b = b && p.exec("alter table benutzer add constraint FK_LOG_DES_BEN foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    b = b && p.exec("alter table benutzergruppe add constraint FK_LOG_DES_BG foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table spalte_zuordnung add aic_stammtyp int null");
                    b = b && p.exec("alter table spalte_zuordnung alter column aic_stamm int null");
                    b = b && p.exec("alter table spalte_zuordnung add constraint FK_SZ_STT_DES_SZ foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b = b && p.exec("alter table benutzer add constraint FK_LOG_DES_BEN foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    b = b && p.exec("alter table benutzergruppe add constraint FK_LOG_DES_BG foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                  }
                  int iAF=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Auswertformat'");
                  b=b && addCode(iAF,"SV_Zahl2")>0;
                  b=b && addCode(iAF,"SV_Cent2")>0;
                  iIst = b ? Ver(5,2,5):iIst;
                }
                gauge.setText("V 5.04 - Erweiterungen",50);
                if(b && iIst < Ver(5,3,1) && iSoll>=Ver(5,3,1))	// 10.7.2007
                {
                  g.diskInfo("Versionsupdate 5.3.1");
                  if (g.ASA())
                  {
                    b = b && p.exec("create table VERLAUF(" +
                                    "AIC_VERLAUF      integer  not null default AUTOINCREMENT," +
                                    "TIMESTAMP        timestamp null," +
                                    "AIC_LOGGING      integer  not null," +
                                    "AIC_BEGRIFF      integer  not null," +
                                    "AIC_STAMM        integer  null," +
                                    "VON              datetime null," +
                                    "BIS              DateTime null,"+
                                    "primary key (AIC_VERLAUF))");
                    b=b && p.exec("alter table VERLAUF add foreign key FK_VERLAUF_RELATION__LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
                    b=b && p.exec("alter table VERLAUF add foreign key FK_VERLAUF_RELATION__BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");
                    b=b && p.exec("alter table VERLAUF add foreign key FK_VERLAUF_RELATION__STAMM (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("create table VERLAUF("+
                                    "AIC_VERLAUF        NUMBER(10)  not null,"+
                                    "TIMESTAMP          DATE null,"+
                                    "AIC_LOGGING        NUMBER(10)  not null,"+
                                    "AIC_BEGRIFF        NUMBER(10)  not null,"+
                                    "AIC_STAMM          NUMBER(10)  null," +
                                    "VON                Date null," +
                                    "BIS                Date null," +
                                    "constraint PK_VERLAUF primary key (AIC_VERLAUF))");
                    b = b && p.exec("alter table verlauf modify timestamp default sysdate");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_RELATION__LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_RELATION__BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_RELATION__STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                    p.createAllSequences();
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("create table VERLAUF("+
                                    "AIC_VERLAUF      int identity not null,"+
                                    "TIMESTAMP        datetime null default (getdate())," +
                                    "AIC_LOGGING      int  not null," +
                                    "AIC_BEGRIFF      int  not null," +
                                    "AIC_STAMM        int  null," +
                                    "VON              datetime null," +
                                    "BIS              DateTime null,"+
                                    "primary key (AIC_VERLAUF))");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_RELATION__LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_RELATION__BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_RELATION__STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                  }

                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");
                  iIst = b ? Ver(5,3,1):iIst;
                }
                if(b && iIst < Ver(5,3,2) && iSoll>=Ver(5,3,2))	// 16.7.2007
                {
                  g.diskInfo("Versionsupdate 5.3.2");
                  p.add("Kennung","XML");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iXML = p.insert("begriffgruppe",true);
                  b=b && addCode(iXML,"xml create")>0;
                  b=b && addCode(iXML,"xml addTitel")>0;
                  b=b && addCode(iXML,"xml okTitel")>0;
                  b=b && addCode(iXML,"xml addTitel2")>0;
                  b=b && addCode(iXML,"xml string")>0;
                  b=b && addCode(iXML,"xml date")>0;
                  b=b && addCode(iXML,"xml time")>0;
                  b=b && addCode(iXML,"xml int")>0;
                  b=b && addCode(iXML,"xml KZ")>0;
                  b=b && addCode(iXML,"xml close")>0;
                  iIst = b ? Ver(5,3,2):iIst;
                }
                if(b && iIst < Ver(5,3,3) && iSoll>=Ver(5,3,3))	// 20.8.2007
                {
                  g.diskInfo("Versionsupdate 5.3.3");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table MODELL add AIC_EIGENSCHAFT integer null");
                    b = b && p.exec("alter table MODELL add foreign key FK_MODELL_AMPEL_DES_EIGENSCH (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
                    b = b && p.exec("alter table spalte_zuordnung add AIC_EIGENSCHAFT integer null");
                    b = b && p.exec("alter table spalte_zuordnung add foreign key FK_SZ_EIG_DES_SZ (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table MODELL add AIC_EIGENSCHAFT NUMBER(10) null");
                    b = b && p.exec("alter table MODELL add constraint FK_MODELL_AMPEL_DES_EIGENSCH foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b = b && p.exec("alter table spalte_zuordnung add AIC_EIGENSCHAFT NUMBER(10) null");
                    b = b && p.exec("alter table spalte_zuordnung add constraint FK_SZ_EIG_DES_SZ foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table MODELL add AIC_EIGENSCHAFT int null");
                    b = b && p.exec("alter table MODELL add constraint FK_MODELL_AMPEL_DES_EIGENSCH foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b = b && p.exec("alter table spalte_zuordnung add AIC_EIGENSCHAFT int null");
                    b = b && p.exec("alter table spalte_zuordnung add constraint FK_SZ_EIG_DES_SZ foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                  }
                  int iInit=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  if (b && iInit>0)
                  {
                    b = b && addCode(iInit, "init 86400") > 0;
                    b = b && addCode(iInit, "init 365") > 0;
                    b = b && addCode(iInit, "init 365.25") > 0;
                    b = b && addCode(iInit, "init 12") > 0;
                  }
                  else
                    b=false;
                  iIst = b ? Ver(5,3,3):iIst;
                }
                gauge.setText("V 5.05 - Erweiterungen",60);
                if(b && iIst < Ver(5,4,1) && iSoll>=Ver(5,4,1))	// 22.8.2007
                {
                  g.diskInfo("Versionsupdate 5.4.1");
                  if (g.exec("update stamm set aic_mandant=1 where aic_stammtyp=6 and kennung='Euro' and aic_mandant>1")>0)
                    g.fixInfo("Euro-Mandant richtiggestellt");
                  if (g.exec("update stamm set aic_mandant=1 where aic_stammtyp=29 and kennung='y' and aic_mandant>1")>0)
                    g.fixInfo("Jahr-Mandant richtiggestellt");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table BEWEGUNGSTYP add BEWBITS integer null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG1 integer null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG2 integer null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG3 integer null");

                      b = b && p.exec("alter table BEWEGUNGSTYP add foreign key FK_BEWEGUNGSTYP_ANR1 (AIC_EIG1) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
                      b = b && p.exec("alter table BEWEGUNGSTYP add foreign key FK_BEWEGUNGSTYP_ANR2 (AIC_EIG2) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
                      b = b && p.exec("alter table BEWEGUNGSTYP add foreign key FK_BEWEGUNGSTYP_ANR3 (AIC_EIG3) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");

                    b = b && p.exec("alter table bew_pool add ANR2 integer null");
                    b = b && p.exec("alter table bew_pool add ANR3 integer null");

                    b = b && p.exec("alter table Stamm_Protokoll add AIC_MANDANT integer null");
                    b = b && p.exec("update Stamm_Protokoll set aic_mandant=(select aic_mandant from stamm where aic_stamm=stamm_protokoll.aic_stamm)");
                    g.bISQL = true;
                    p.exec("alter table STAMM drop foreign key FK_STAMM_MANDANT_D_MANDANT");
                    p.exec("alter table STAMM drop foreign key FK_STAMM_STAMM__MANDANT");
                    g.bISQL = false;
                    b = b && p.exec("alter table Stamm drop AIC_MANDANT");

                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table BEWEGUNGSTYP add BEWBITS NUMBER(10) null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG1 NUMBER(10) null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG2 NUMBER(10) null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG3 NUMBER(10) null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add constraint FK_BEWEGUNGSTYP_ANR1 foreign key (AIC_EIG1) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b = b && p.exec("alter table BEWEGUNGSTYP add constraint FK_BEWEGUNGSTYP_ANR2 foreign key (AIC_EIG2) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b = b && p.exec("alter table BEWEGUNGSTYP add constraint FK_BEWEGUNGSTYP_ANR3 foreign key (AIC_EIG3) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b = b && p.exec("alter table bew_pool add ANR2 NUMBER(10) null");
                    b = b && p.exec("alter table bew_pool add ANR3 NUMBER(10) null");

                    b = b && p.exec("alter table Stamm_Protokoll add AIC_MANDANT NUMBER(10) null");
                    b = b && p.exec("update Stamm_Protokoll set aic_mandant=(select aic_mandant from stamm where aic_stamm=stamm_protokoll.aic_stamm)");
                    //b = b && p.exec("drop index Ref_1019_FK");
                    b = b && p.exec("alter table Stamm drop COLUMN AIC_MANDANT");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table BEWEGUNGSTYP add BEWBITS int null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG1 int null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG2 int null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG3 int null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add constraint FK_BEWEGUNGSTYP_ANR1 foreign key (AIC_EIG1) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b = b && p.exec("alter table BEWEGUNGSTYP add constraint FK_BEWEGUNGSTYP_ANR2 foreign key (AIC_EIG2) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b = b && p.exec("alter table BEWEGUNGSTYP add constraint FK_BEWEGUNGSTYP_ANR3 foreign key (AIC_EIG3) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b = b && p.exec("alter table bew_pool add ANR2 int null");
                    b = b && p.exec("alter table bew_pool add ANR3 int null");

                    b = b && p.exec("alter table Stamm_Protokoll add AIC_MANDANT int null");
                    b = b && p.exec("update Stamm_Protokoll set aic_mandant=(select aic_mandant from stamm where aic_stamm=stamm_protokoll.aic_stamm)");
                    g.bISQL = true;
                    p.exec("drop index Stamm.Ref_1019_FK");
                    g.bISQL = false;
                    b = b && p.exec("ALTER TABLE STAMM DROP CONSTRAINT FK_STAMM_REF_1034_MANDANT");
                    b = b && p.exec("alter table Stamm drop COLUMN AIC_MANDANT");
                  }
                  b = b && p.exec("update bewegungstyp set TIMER=0 where TIMER is null");
                  b = b && p.exec("update bewegungstyp set LOKALTIMER=0 where LOKALTIMER is null");
                  b = b && p.exec("update bewegungstyp set NUR_MODELL=0 where NUR_MODELL is null");
                  b = b && p.exec("update BEWEGUNGSTYP SET BEWBITS=TIMER+LOKALTIMER*2+NUR_MODELL*4");
                  //b = b && p.exec("update bewegungstyp set bewbits=0 where bewbits is null");
                  b = b && p.exec("alter table BEWEGUNGSTYP drop"+(g.ASA()?"":" COLUMN")+" TIMER");
                  b = b && p.exec("alter table BEWEGUNGSTYP drop"+(g.ASA()?"":" COLUMN")+" LOKALTIMER");
                  b = b && p.exec("alter table BEWEGUNGSTYP drop"+(g.ASA()?"":" COLUMN")+" NUR_MODELL");
                  int iANR = p.getInteger("select aic_stammtyp from stammtyp where Kennung='Arbeitnehmer'");
                  b = b && p.exec("update bewegungstyp set AIC_Eig1=("+g.first(" e.aic_eigenschaft from eigenschaft e"+g.join("bew_zuordnung","z","e","eigenschaft")+
                                  " where z.aic_bewegungstyp=bewegungstyp.aic_bewegungstyp and e.aic_stammtyp="+iANR)+")");
                  b = b && p.exec("create index BEW_TEST4 on BEW_POOL (AIC_BEWEGUNGSTYP,ANR2,LDATE asc)");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anzeigeart'");
                  b=b && addCode(iGruppe,"anr2")>0;
                  b=b && addCode(iGruppe,"anr3")>0;
                  iIst = b ? Ver(5,4,1):iIst;
                  ViewRebuild(g,iIst);
                }
                if(b && iIst < Ver(5,4,2) && iSoll>=Ver(5,4,2))	// 27.9.2007
                {
                  g.diskInfo("Versionsupdate 5.4.2");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table VERLAUF add AIC_BEW_POOL integer null");
                    b = b && p.exec("alter table VERLAUF add foreign key FK_VERLAUF_BEW (AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL) on update restrict on delete restrict");
                    b = b && p.exec("alter table MANDANT add Verbindungen integer null");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table VERLAUF add AIC_BEW_POOL NUMBER(10) null");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_BEW foreign key (AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL)");
                    b = b && p.exec("alter table MANDANT add Verbindungen NUMBER(10) null");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table VERLAUF add AIC_BEW_POOL int null");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_BEW foreign key (AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL)");
                    b = b && p.exec("alter table MANDANT add Verbindungen int null");
                  }
                  iIst = b ? Ver(5,4,2):iIst;
                }
                if(b && iIst < Ver(5,4,3) && iSoll>=Ver(5,4,3))	// 12.10.2007
                {
                  g.diskInfo("Versionsupdate 5.4.3");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table VERLAUF add AIC_STAMMTYP integer null");

                      b = b && p.exec("alter table VERLAUF add foreign key FK_VERLAUF_STT (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP) on update restrict on delete restrict");
                      b = b && p.exec("alter table VERLAUF drop foreign key FK_VERLAUF_RELATION__BEGRIFF");
                      b = b && p.exec("alter table VERLAUF modify aic_begriff null");
                      b = b && p.exec("alter table VERLAUF add foreign key FK_VERLAUF_RELATION__BEGRIFF (aic_begriff) references begriff (aic_begriff) on update restrict on delete restrict");

                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table VERLAUF add AIC_STAMMTYP NUMBER(10) null");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_STT foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b = b && p.exec("alter table VERLAUF drop constraint FK_VERLAUF_RELATION__BEGRIFF");
                    b = b && p.exec("alter table VERLAUF modify aic_begriff null");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_RELATION__BEGRIFF foreign key (aic_begriff) references begriff (aic_begriff)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table VERLAUF add AIC_STAMMTYP int null");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_STT foreign key (AIC_STAMMTYP) references STAMMTYP (AIC_STAMMTYP)");
                    b = b && p.exec("alter table VERLAUF drop constraint FK_VERLAUF_RELATION__BEGRIFF");
                    b = b && p.exec("alter table VERLAUF alter column aic_begriff int null");
                    b = b && p.exec("alter table VERLAUF add constraint FK_VERLAUF_RELATION__BEGRIFF foreign key (aic_begriff) references begriff (aic_begriff)");
                  }
                  int iGet=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  b=b && addCode(iGet,"get Mandant")>0;
                  int iInit=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  b=b && addCode(iInit,"init Mandant")>0;
                  int iBed=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && addCode(iBed,"= Mandant")>0;
                  int iTerm=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='terminal'");
                  b=b && addCode(iTerm,"set Terminalstamm2")>0;
                  iIst = b ? Ver(5,4,3):iIst;
                }
                if(b && iIst < Ver(5,4,4) && iSoll>=Ver(5,4,4))	// 19.10.2007
                {
                  g.diskInfo("Versionsupdate 5.4.4");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table BEGRIFFGRUPPE add AIC_SCHRIFT integer null");
                    b = b && p.exec("alter table BEGRIFFGRUPPE add foreign key FK_BG_SCHRIFT (AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT) on update restrict on delete restrict");
                    b = b && p.exec("alter table ZWISCHENSPEICHER add AIC_MANDANT integer null");
                    b = b && p.exec("alter table ZWISCHENSPEICHER add foreign key FK_ZS_MANDANT (AIC_MANDANT) references MANDANT (AIC_MANDANT) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table BEGRIFFGRUPPE add AIC_SCHRIFT NUMBER(10) null");
                    b = b && p.exec("alter table BEGRIFFGRUPPE add constraint FK_BG_SCHRIFT foreign key (AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT)");
                    b = b && p.exec("alter table ZWISCHENSPEICHER add AIC_MANDANT NUMBER(10) null");
                    b = b && p.exec("alter table ZWISCHENSPEICHER add constraint FK_ZS_MANDANT foreign key (AIC_MANDANT) references MANDANT (AIC_MANDANT)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table BEGRIFFGRUPPE add AIC_SCHRIFT int null");
                    b = b && p.exec("alter table BEGRIFFGRUPPE add constraint FK_BG_SCHRIFT foreign key (AIC_SCHRIFT) references SCHRIFT (AIC_SCHRIFT)");
                    b = b && p.exec("alter table ZWISCHENSPEICHER add AIC_MANDANT int null");
                    b = b && p.exec("alter table ZWISCHENSPEICHER add constraint FK_ZS_MANDANT foreign key (AIC_MANDANT) references MANDANT (AIC_MANDANT)");
                  }
                  b = b && p.exec("alter table BEFEHL add EINGABE varchar(50) null");
                  b = b && p.exec("update zwischenspeicher set aic_mandant=(select aic_mandant from logging"+g.join2("protokoll","logging")+
                                  " where aic_protokoll=zwischenspeicher.aic_protokoll)");

                  iIst = b ? Ver(5,4,4):iIst;
                }
                if(b && iIst < Ver(5,4,5) && iSoll>=Ver(5,4,5))	// 25.10.2007
                {
                  g.diskInfo("Versionsupdate 5.4.5");
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  int iCon=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='convert'");
                  int iDate=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  int iInit=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  b=b && p.exec("update code set kennung='add to',aic_begriffgruppe="+iAnw+" where Kennung='reserve10' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='use cache',aic_begriffgruppe="+iAnw+" where Kennung='reserve11' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='copy date',aic_begriffgruppe="+iCon+" where Kennung='reserve12' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='next bis',aic_begriffgruppe="+iDate+" where Kennung='reserve13' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='init Tab',aic_begriffgruppe="+iInit+" where Kennung='reserve14' and aic_begriffgruppe="+iRes);
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && p.exec("update code set kennung='pos not aic' where Kennung='Bedingung6' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='pos next not aic' where Kennung='Bedingung7' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='pos not date' where Kennung='Bedingung8' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='> now' where Kennung='Bedingung9' and aic_begriffgruppe="+iBed);
                  for (int i=10;i<30;i++)
                      b = b && addCode(iBed, "Bedingung"+i) > 0;
                  if (g.ASA())
                  {
                    g.exec("drop index Ref_696_FK");
                    g.exec("drop index Ref_686_FK");
                  }
                  iIst = b ? Ver(5,4,5):iIst;
                }
                if(b && iIst < Ver(5,4,6) && iSoll>=Ver(5,4,6))	// 30.10.2007
                {
                  g.diskInfo("Versionsupdate 5.4.6");
                  if (g.ASA())
                  {
                    b=b && p.exec("alter table BEGRIFF ADD LOG_AIC_LOGGING integer null");
                    b=b && p.exec("alter table BEGRIFF add foreign key FK_BEGRIFF_SPERRE (LOG_AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table BEGRIFF add LOG_AIC_LOGGING NUMBER(10) null");
                    b = b && p.exec("alter table BEGRIFF add constraint FK_BEGRIFF_SPERRE foreign key (LOG_AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("alter table BEGRIFF add LOG_AIC_LOGGING int null");
                    b = b && p.exec("alter table BEGRIFF add constraint FK_BEGRIFF_SPERRE foreign key (LOG_AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                  }
                  int iGauge=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='GAUGE'");
                  b=b && addCode(iGauge,"Gauge open2")>0;
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && addCode(iBed,"Gauge Abbruch")>0;
                  g.diskInfo(g.exec("update spalte_zuordnung set aic_stammtyp=(select aic_stammtyp from stamm where aic_stamm=spalte_zuordnung.aic_stamm) where aic_stammtyp is null and aic_stamm is not null")+
                         " Stammtypen bei Spalte_Zuordnung richtiggestellt");

                  iIst = b ? Ver(5,4,6):iIst;
                }
                if(b && iIst < Ver(5,4,7) && iSoll>=Ver(5,4,7))	// 12.11.2007
                {
                  g.diskInfo("Versionsupdate 5.4.7");
                  int iHolen = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  addCode(iHolen,"add sum group");
                  addCode(iHolen,"sum VecAic");
                  iIst = b ? Ver(5,4,7):iIst;
                }
                gauge.setText("V 5.06 - Erweiterungen",70);
                if(b && iIst < Ver(5,5,1) && iSoll>=Ver(5,5,1))	// 19.03.2008
                {
                  g.diskInfo("Versionsupdate 5.5.1");
                  int iDT = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  if (b && iDT>0)
		  {
			addBegriff(iDT,"Land");
			addBegriff(iDT,"BewLand");
			addBegriff(iDT,"Bezeichnung2");
			addBegriff(iDT,"SttReserve");
			b = addBegriff(iDT,"BewReserve") > 0;
		  }
                  int iMath = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'");
                  if (b && iMath>0)
                  {
			addCode(iMath,"random number");
			addCode(iMath,"power number");
    			b = addCode(iMath,"1/number") > 0;
                  }
                  int iJoker=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
		  if (b && iJoker>0)
		  {
		  	addCode(iJoker,"*Checkbox*");
		  	b=addCode(iJoker,"*Radiobutton*")>0;
		  }
		  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
		  for (int i=30;i<40;i++)
                    b = b && addCode(iRes, "reserve"+i) > 0;
                  b=b && addIntSpalte("Bew_Boolean","AIC_LOGGING","LOGGING");
                  if (g.Oracle() || g.MS() || g.MySQL())
                  {
                  	if (g.Oracle())
	                  {
	                    b = b && p.exec("create table DATEN_STRINGX("+
	                                    "AIC_DATEN        NUMBER(10)  not null,"+
	                                    "SPALTE_STRINGX   varchar(255),"+
	                                    "constraint PK_DATEN_STRINGX primary key (AIC_DATEN))");
	                  }
	                  if (g.MS())
	                  {
	                    b = b && p.exec("create table DATEN_STRINGX ("+
	                		    "AIC_DATEN int not null,"+
	                		    "SPALTE_STRINGX  varchar(255),"+
	                		    "primary key (AIC_DATEN))");
                            b = b && p.exec("create unique index PK__DATEN_STRINGX on DATEN_STRINGX (AIC_DATEN)");
	                  }
	                  //if (Static.bStringX)
                            //Transact.fixInfo(StringX_Rebuild(g));
                  }
                  iIst = b ? Ver(5,5,1):iIst;
                }
                if(b && iIst < Ver(5,5,2) && iSoll>=Ver(5,5,2))	// 03.04.2008
                {
                  g.diskInfo("Versionsupdate 5.5.2");
                  p.add("Kennung","Fehlermeldung");
                  b= p.insert("begriffgruppe",true)>0;
                  int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  int iMath = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'");
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  p.add("Kennung","IntusCom");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iIC = p.insert("begriffgruppe",true);
                  p.add("Kennung","string command");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iString = p.insert("begriffgruppe",true);
                  b=b && p.exec("update code set kennung='round2',aic_begriffgruppe="+iMath+" where Kennung='reserve15' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='create IntusCom',aic_begriffgruppe="+iIC+" where Kennung='reserve16' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='free IntusCom',aic_begriffgruppe="+iIC+" where Kennung='reserve17' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='read IntusCom',aic_begriffgruppe="+iIC+" where Kennung='reserve18' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='set ok IntusCom',aic_begriffgruppe="+iIC+" where Kennung='reserve19' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='upload IntusCom',aic_begriffgruppe="+iIC+" where Kennung='reserve20' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='enter IntusCom',aic_begriffgruppe="+iIC+" where Kennung='reserve21' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='holiday IntusCom',aic_begriffgruppe="+iIC+" where Kennung='reserve22' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='read directory',aic_begriffgruppe="+iAnw+" where Kennung='reserve23' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='clear query except',aic_begriffgruppe="+iAnw+" where Kennung='reserve24' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='concat filesep',aic_begriffgruppe="+iString+" where Kennung='reserve25' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='set testprint',aic_begriffgruppe="+iAnw+" where Kennung='reserve26' and aic_begriffgruppe="+iRes);
                  b=b && addCode(iString, "concat show") > 0;
                  b=b && addCode(iAnw, "use VecBew") > 0;
                  int iError = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='error handling'");
                  b=b && addCode(iError, "error message2") > 0;
                  b=b && p.exec("update code set aic_begriffgruppe="+iString+" where Kennung='concat' and aic_begriffgruppe="+iMath);
                  b=b && p.exec("update code set aic_begriffgruppe="+iString+" where Kennung='concat with space' and aic_begriffgruppe="+iMath);
                  b=b && p.exec("update code set aic_begriffgruppe="+iString+" where Kennung='concat table' and aic_begriffgruppe="+iMath);
                  b=b && p.exec("update code set aic_begriffgruppe="+iString+" where Kennung='concat dep memo' and aic_begriffgruppe="+iMath);
                  b=b && p.exec("update code set aic_begriffgruppe="+iString+" where Kennung='trunc left' and aic_begriffgruppe="+iMath);
                  b=b && p.exec("update code set aic_begriffgruppe="+iString+" where Kennung='trunc right' and aic_begriffgruppe="+iMath);

                  if (g.ASA())
                  {
                    b = b && p.exec("create table FEHLER(" +
                                    "AIC_FEHLER      integer  not null default AUTOINCREMENT," +
                                    "TIMESTAMP       timestamp null," +
                                    "AIC_COMPUTER    integer  null," +
                                    "AIC_LOGGING     integer  null," +
                                    "AIC_BEGRIFF     integer  null," +
                                    "FEHLER  varchar(255),"+
                                    "primary key (AIC_FEHLER))");
                    b=b && p.exec("alter table FEHLER add foreign key FK_FEHLER_RELATION__LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
                    b=b && p.exec("alter table FEHLER add foreign key FK_FEHLER_RELATION__BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");
                    b=b && p.exec("alter table FEHLER add foreign key FK_FEHLER_RELATION__COMPUTER (AIC_COMPUTER) references COMPUTER (AIC_COMPUTER) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("create table FEHLER("+
                                    "AIC_FEHLER      NUMBER(10)  not null,"+
                                    "TIMESTAMP       DATE null,"+
                                    "AIC_COMPUTER    NUMBER(10)  null," +
	                                  "AIC_LOGGING     NUMBER(10)  null," +
	                                  "AIC_BEGRIFF     NUMBER(10)  null," +
	                                  "FEHLER  varchar(255),"+
	                                  "constraint PK_FEHLER primary key (AIC_FEHLER))");
                    b = b && p.exec("alter table FEHLER modify timestamp default sysdate");
                    b = b && p.exec("alter table FEHLER add constraint FK_FEHLER_RELATION__LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    b = b && p.exec("alter table FEHLER add constraint FK_FEHLER_RELATION__BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                    b = b && p.exec("alter table FEHLER add constraint FK_FEHLER_RELATION__COMPUTER foreign key (AIC_COMPUTER) references COMPUTER (AIC_COMPUTER)");
                  }
                  else if (g.MS())
                  {
                  	b = b && p.exec("create table FEHLER("+
                                    "AIC_FEHLER      int identity not null,"+
                                    "TIMESTAMP       datetime null default (getdate())," +
                                    "AIC_COMPUTER    int  null," +
                                    "AIC_LOGGING     int  null," +
                                    "AIC_BEGRIFF     int  null," +
                                    "FEHLER  varchar(255),"+
                                    "primary key (AIC_FEHLER))");
                    b = b && p.exec("alter table FEHLER add constraint FK_FEHLER_RELATION__LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    b = b && p.exec("alter table FEHLER add constraint FK_FEHLER_RELATION__BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                    b = b && p.exec("alter table FEHLER add constraint FK_FEHLER_RELATION__COMPUTER foreign key (AIC_COMPUTER) references COMPUTER (AIC_COMPUTER)");
                  }
                  else if (g.MySQL())
                  {
                  	b = b && p.exec("create table FEHLER(" +
                                    "AIC_FEHLER      int  not null AUTO_INCREMENT," +
                                    "TIMESTAMP       timestamp null," +
                                    "AIC_COMPUTER    int  null," +
                                    "AIC_LOGGING     int  null," +
                                    "AIC_BEGRIFF     int  null," +
                                    "FEHLER  varchar(255)," +
                                    "primary key (AIC_FEHLER))");
	                  b=b && p.exec("alter table FEHLER add foreign key FK_FEHLER_RELATION__LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
	                  b=b && p.exec("alter table FEHLER add foreign key FK_FEHLER_RELATION__BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");
	                  b=b && p.exec("alter table FEHLER add foreign key FK_FEHLER_RELATION__COMPUTER (AIC_COMPUTER) references COMPUTER (AIC_COMPUTER) on update restrict on delete restrict");
                  }
                  iIst = b ? Ver(5,5,2):iIst;
                }
                if(b && iIst < Ver(5,5,3) && iSoll>=Ver(5,5,3))	// 24.04.2008
                {
                  g.diskInfo("Versionsupdate 5.5.3");
                  int iOp = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                  if (b && iOp>0)
                  {
                    addCode(iOp,"add months");
                    addCode(iOp,"add years");
                  }
                  int iAnw=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iAnw,"get changes2")>0;
                  int iIC=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='IntusCom'");
                  b=b && addCode(iIC,"upload TI IntusCom")>0;
                  b=b && addCode(iIC,"change TI IntusCom")>0;
                  b=b && addCode(iIC,"change MR IntusCom")>0;
                  iIst = b ? Ver(5,5,3):iIst;
                }
                if(b && iIst < Ver(5,5,4) && iSoll>=Ver(5,5,4))	// 6.05.2008
                {
                  g.diskInfo("Versionsupdate 5.5.4");
                  int iIC=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='IntusCom'");
                  b=b && addCode(iIC,"enter2 IntusCom")>0;
                  b=b && addCode(iIC,"booking IntusCom")>0;
                  if (g.ASA())
                  {
                    b = b && p.exec("create table DEFVERLAUF(" +
                                    "AIC_DEFVERLAUF   integer  not null default AUTOINCREMENT," +
                                    "TIMESTAMP        timestamp null," +
                                    "AIC_LOGGING      integer  not null," +
                                    "AIC_BEGRIFF      integer  not null," +
                                    "TAT        varchar(80)," +
                                    "primary key (AIC_DEFVERLAUF))");
                    b=b && p.exec("alter table DEFVERLAUF add foreign key FK_DEFVERLAUF_RELATION__LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
                    b=b && p.exec("alter table DEFVERLAUF add foreign key FK_DEFVERLAUF_RELATION__BEGRIFF (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("create table DEFVERLAUF("+
                                    "AIC_DEFVERLAUF        NUMBER(10)  not null,"+
                                    "TIMESTAMP          DATE null,"+
                                    "AIC_LOGGING        NUMBER(10)  not null,"+
                                    "AIC_BEGRIFF        NUMBER(10)  not null,"+
                                    "TAT        varchar(80)," +
                                    "constraint PK_DEFVERLAUF primary key (AIC_DEFVERLAUF))");
                    b = b && p.exec("alter table DEFVERLAUF modify timestamp default sysdate");
                    b = b && p.exec("alter table DEFVERLAUF add constraint FK_DEFVERLAUF_LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    b = b && p.exec("alter table DEFVERLAUF add constraint FK_DEFVERLAUF_BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                    p.createAllSequences();
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("create table DEFVERLAUF("+
                                    "AIC_DEFVERLAUF      int identity not null,"+
                                    "TIMESTAMP        datetime null default (getdate())," +
                                    "AIC_LOGGING      int  not null," +
                                    "AIC_BEGRIFF      int  not null," +
                                    "TAT        varchar(80)," +
                                    "primary key (AIC_DEFVERLAUF))");
                    b = b && p.exec("alter table DEFVERLAUF add constraint FK_DEFVERLAUF_RELATION__LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    b = b && p.exec("alter table DEFVERLAUF add constraint FK_DEFVERLAUF_RELATION__BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                  }
                  else if (g.MySQL())
                  {
                    b = b && p.exec("create table DEFVERLAUF("+
                                    "AIC_DEFVERLAUF      int NOT NULL AUTO_INCREMENT,"+
                                    "TIMESTAMP        timestamp null," +
                                    "AIC_LOGGING      int  not null," +
                                    "AIC_BEGRIFF      int  not null," +
                                    "TAT        varchar(80)," +
                                    "primary key (AIC_DEFVERLAUF))");
                    b = b && p.exec("alter table DEFVERLAUF add constraint FK_DEFVERLAUF_RELATION__LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                    b = b && p.exec("alter table DEFVERLAUF add constraint FK_DEFVERLAUF_RELATION__BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");

                  }
                  iIst = b ? Ver(5,5,4):iIst;
                }
                if(b && iIst < Ver(5,5,5) && iSoll>=Ver(5,5,5))	// 7.05.2008
                {
                  g.diskInfo("Versionsupdate 5.5.5");
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && addCode(iBed,"IntusCom error")>0;
                  int iIC=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='IntusCom'");
                  b=b && addCode(iIC,"autorisation IC")>0;
                  b=b && addCode(iIC,"holiday2 IntusCom")>0;
                  int iDT = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  b= b && addBegriff(iDT,"BewHoliday")>0;

                  iIst = b ? Ver(5,5,5):iIst;
                }
                if(b && iIst < Ver(5,5,6) && iSoll>=Ver(5,5,6))	// 28.05.2008
                {
                  if (g.MySQL())
                  {
                    g.diskInfo("Versionsupdate 5.5.6");
                    p.exec("update protokoll set timestamp=(select ein from logging where aic_logging=protokoll.aic_logging) where timestamp is null");
                    p.exec("alter table protokoll modify timestamp timestamp default current_timestamp");

                    p.exec("update verlauf set timestamp=(select ein from logging where aic_logging=verlauf.aic_logging) where timestamp is null");
                    p.exec("alter table verlauf modify timestamp timestamp default current_timestamp");

                    p.exec("update fehler set timestamp=(select ein from logging where aic_logging=fehler.aic_logging) where timestamp is null");
                    p.exec("alter table fehler modify timestamp timestamp default current_timestamp");

                    p.exec("update defverlauf set timestamp=(select ein from logging where aic_logging=defverlauf.aic_logging) where timestamp is null");
                    p.exec("alter table defverlauf modify timestamp timestamp default current_timestamp");
                  }
                  iIst = b ? Ver(5,5,6):iIst;
                }
                gauge.setText("V 5.07 - Erweiterungen",80);
                if(b && iIst < Ver(5,6,1) && iSoll>=Ver(5,6,1))	// 18.12.2008
                {
                  g.diskInfo("Versionsupdate 5.6.1");
                  if (g.ASA())
                  {
                    b=b && p.exec("alter table BEFEHL ADD AIC_DEFVERLAUF integer null");
                    b=b && p.exec("alter table BEFEHL ADD DEF_AIC_DEFVERLAUF integer null");
                    b=b && p.exec("alter table BEFEHL add foreign key FK_BEFEHL_VERLAUF (AIC_DEFVERLAUF) references DEFVERLAUF (AIC_DEFVERLAUF) on update restrict on delete restrict");
                    b=b && p.exec("alter table BEFEHL add foreign key FK_BEFEHL_VERLAUF2 (DEF_AIC_DEFVERLAUF) references DEFVERLAUF (AIC_DEFVERLAUF) on update restrict on delete restrict");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add AB date null");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add WEG date null");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table BEFEHL add AIC_DEFVERLAUF NUMBER(10) null");
                    b = b && p.exec("alter table BEFEHL add DEF_AIC_DEFVERLAUF NUMBER(10) null");
                    b = b && p.exec("alter table BEFEHL add constraint FK_BEFEHL_VERLAUF foreign key (AIC_DEFVERLAUF) references DEFVERLAUF (AIC_DEFVERLAUF)");
                    b = b && p.exec("alter table BEFEHL add constraint FK_BEFEHL_VERLAUF2 foreign key (DEF_AIC_DEFVERLAUF) references DEFVERLAUF (AIC_DEFVERLAUF)");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add AB date null");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add WEG date null");
                  }
                  else if (g.MS() || g.MySQL())
                  {
                    b = b && p.exec("alter table BEFEHL add AIC_DEFVERLAUF int null");
                    b = b && p.exec("alter table BEFEHL add DEF_AIC_DEFVERLAUF int null");
                    b = b && p.exec("alter table BEFEHL add constraint FK_BEFEHL_VERLAUF foreign key (AIC_DEFVERLAUF) references DEFVERLAUF (AIC_DEFVERLAUF)");
                    b = b && p.exec("alter table BEFEHL add constraint FK_BEFEHL_VERLAUF2 foreign key (DEF_AIC_DEFVERLAUF) references DEFVERLAUF (AIC_DEFVERLAUF)");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add AB datetime null");
                    b = b && p.exec("alter table STAMM_PROTOKOLL add WEG datetime null");
                  }
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct aic_modell,(select max(aic_defverlauf) from defverlauf where aic_begriff=modell.aic_begriff) aic"+
                      " from defverlauf join modell on defverlauf.aic_begriff=modell.aic_begriff",true);
                  b = b && p.exec("update STAMM_PROTOKOLL set ab=eintritt");
                  b = b && p.exec("create view befehl2 as select * from befehl where def_aic_defverlauf is null");
                  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                    g.exec("update befehl set AIC_DEFVERLAUF="+Tab.getI("AIC")+" where aic_modell="+Tab.getI("aic_modell"));
                  iIst = b ? Ver(5,6,1):iIst;
                  //ViewRebuild(g,iIst);
                }
                if(b && iIst < Ver(5,6,2) && iSoll>=Ver(5,6,2))	// 12. 2.2009
                {
                  g.diskInfo("Versionsupdate 5.6.2");
                  if (g.ASA())
                  {
                    b=b && p.exec("alter table SPALTE ADD STA_AIC_STAMM integer null");
                    b=b && p.exec("alter table SPALTE add foreign key FK_SPALTE_REL_STAMM (STA_AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                    b=b && p.exec("create table BEW_SPALTE("+
                        "AIC_BEW_POOL     integer               not null,"+
                        "AIC_EIGENSCHAFT  integer               not null,"+
                        "AIC_STAMM        integer               not null,"+
                        "Wert             double                null,"+
                        "STA_AIC_STAMM    integer               null,"+
                        "primary key (AIC_BEW_POOL, AIC_EIGENSCHAFT,AIC_STAMM))");
                    b=b && p.exec("alter table BEW_SPALTE add foreign key FK_BEWSPALTE_POOL (AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL) on update restrict on delete restrict");
                    b=b && p.exec("alter table BEW_SPALTE add foreign key FK_BEWSPALTE_EIG (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
                    b=b && p.exec("alter table BEW_SPALTE add foreign key FK_BEWSPALTE_REL (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                    b=b && p.exec("alter table BEW_SPALTE add foreign key FK_BEWSPALTE_EINHEIT (STA_AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table SPALTE add STA_AIC_STAMM NUMBER(10) null");
                    b = b && p.exec("alter table SPALTE add constraint FK_SPALTE_REL_STAMM foreign key (STA_AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("create table BEW_SPALTE("+
                        "AIC_BEW_POOL     NUMBER(10)               not null,"+
                        "AIC_EIGENSCHAFT  NUMBER(10)               not null,"+
                        "AIC_STAMM        NUMBER(10)               not null,"+
                        "Wert             FLOAT                    null,"+
                        "STA_AIC_STAMM    NUMBER(10)               null,"+
                        "constraint PK_BEW_SPALTE primary key (AIC_BEW_POOL, AIC_EIGENSCHAFT,AIC_STAMM))");
                    b=b && p.exec("alter table BEW_SPALTE add constraint FK_BEWSPALTE_POOL foreign key (AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL)");
                    b=b && p.exec("alter table BEW_SPALTE add constraint FK_BEWSPALTE_EIG foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b=b && p.exec("alter table BEW_SPALTE add constraint FK_BEWSPALTE_REL foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("alter table BEW_SPALTE add constraint FK_BEWSPALTE_EINHEIT foreign key (STA_AIC_STAMM) references STAMM (AIC_STAMM)");
                  }
                  else if (g.MS() || g.MySQL())
                  {
                    b = b && p.exec("alter table SPALTE add STA_AIC_STAMM int null");
                    b = b && p.exec("alter table SPALTE add constraint FK_SPALTE_REL_STAMM foreign key (STA_AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("create table BEW_SPALTE("+
                        "AIC_BEW_POOL     int              not null,"+
                        "AIC_EIGENSCHAFT  int              not null,"+
                        "AIC_STAMM        int              not null,"+
                        "Wert             double precision null,"+
                        "STA_AIC_STAMM    int              null,"+
                        "constraint PK_BEW_SPALTE primary key (AIC_BEW_POOL, AIC_EIGENSCHAFT,AIC_STAMM))");
                    b=b && p.exec("alter table BEW_SPALTE add constraint FK_BEWSPALTE_POOL foreign key (AIC_BEW_POOL) references BEW_POOL (AIC_BEW_POOL)");
                    b=b && p.exec("alter table BEW_SPALTE add constraint FK_BEWSPALTE_EIG foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                    b=b && p.exec("alter table BEW_SPALTE add constraint FK_BEWSPALTE_REL foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("alter table BEW_SPALTE add constraint FK_BEWSPALTE_EINHEIT foreign key (STA_AIC_STAMM) references STAMM (AIC_STAMM)");
                  }
                  int iDT = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  b= b && addBegriff(iDT,"BewZahl2")>0;
                  b= b && addBegriff(iDT,"BewMass2")>0;
                  b= b && addBegriff(iDT,"BewWaehrung2")>0;
                  iIst = b ? Ver(5,6,2):iIst;
                  ViewRebuild(g,iIst);
                }
                if(b && iIst < Ver(5,6,3) && iSoll>=Ver(5,6,3))	// 27. 2.2009
                {
                  g.diskInfo("Versionsupdate 5.6.3");
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  for (int i=40;i<60;i++)
                    b = b && addCode(iRes, "reserve"+i) > 0;
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && p.exec("update code set kennung='Modell_Dialog' where Kennung='Bedingung10' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='path exists' where Kennung='Bedingung11' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='is first row' where Kennung='Bedingung12' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='question2' where Kennung='Bedingung13' and aic_begriffgruppe="+iBed);
                  int iAnw=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  int iInit = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  int iErr = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='error handling'");
                  int iTerm = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='terminal'");
                  int iString = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='string command'");
                  b=b && p.exec("update code set kennung='set error',aic_begriffgruppe="+iErr+" where Kennung='reserve27'");
                  b=b && p.exec("update code set kennung='set edit',aic_begriffgruppe="+iAnw+" where Kennung='reserve28'");
                  b=b && p.exec("update code set kennung='set hide',aic_begriffgruppe="+iAnw+" where Kennung='reserve29'");
                  b=b && p.exec("update code set kennung='clearInhalt2',aic_begriffgruppe="+iAnw+" where Kennung='reserve30'");
                  b=b && p.exec("update code set kennung='pop all',aic_begriffgruppe="+iInit+" where Kennung='reserve31'");
                  b=b && p.exec("update code set kennung='info message',aic_begriffgruppe="+iAnw+" where Kennung='reserve32'");
                  p.add("Kennung","transaction");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iTrans = p.insert("begriffgruppe",true);
                  b=b && p.exec("update code set kennung='start transaction',aic_begriffgruppe="+iTrans+" where Kennung='reserve33'");
                  b=b && p.exec("update code set kennung='commit',aic_begriffgruppe="+iTrans+" where Kennung='reserve34'");
                  b=b && p.exec("update code set kennung='rollback',aic_begriffgruppe="+iTrans+" where Kennung='reserve35'");
                  b=b && p.exec("update code set kennung='correct import',aic_begriffgruppe="+iTerm+" where Kennung='reserve36'");
                  b=b && p.exec("update code set kennung='LineToString',aic_begriffgruppe="+iString+" where Kennung='reserve37'");
                  b=b && p.exec("update code set kennung='copy bew',aic_begriffgruppe="+iAnw+" where Kennung='reserve38'");
                  b=b && p.exec("update code set kennung='col to row',aic_begriffgruppe="+iAnw+" where Kennung='reserve39'");
                  b=b && p.exec("update code set aic_begriffgruppe="+iTerm+" where Kennung='convert mecs'");
                  iIst = b ? Ver(5,6,3):iIst;
                }
                if(b && iIst < Ver(5,6,4) && iSoll>=Ver(5,6,4))	// 11. 3.2009
                {
                  g.diskInfo("Versionsupdate 5.6.4");
                  int iPanel=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Panel'");
                  b=b && iPanel>0 && addBegriff(iPanel,"SplitPaneH")>0;
                  b=b && iPanel>0 && addBegriff(iPanel,"SplitPaneV")>0;
                  b=b && iPanel>0 && addBegriff(iPanel,"ReservePane1")>0;
                  b=b && iPanel>0 && addBegriff(iPanel,"ReservePane2")>0;
                  b=b && iPanel>0 && addBegriff(iPanel,"ReservePane3")>0;
                  int iAnw=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iAnw,"get History")>0;
                  if (g.Oracle())
                    b = b && p.exec("alter table DATEN_MEMO add FIX NUMBER(1) null");
                  else
                    b = b && p.exec("alter table DATEN_MEMO add FIX bit NULL");
                  iIst = b ? Ver(5,6,4):iIst;
                }
                if(b && iIst < Ver(5,6,5) && iSoll>=Ver(5,6,5))	// 17. 3.2009
                {
                  g.diskInfo("Versionsupdate 5.6.5");
                  if (g.ASA())
                    b = b && p.exec("alter table BEZEICHNUNG MODIFY BEZEICHNUNG char(200) null");
                  else if (g.MS())
                    b = b && p.exec("ALTER TABLE BEZEICHNUNG ALTER COLUMN BEZEICHNUNG varchar(200)");
                  else
                    b = b && p.exec("alter table BEZEICHNUNG MODIFY BEZEICHNUNG varchar(200)");
                  if (g.ASA())
                  {
                    b = b && p.exec("create table Tooltip ("+
                                    "aic_tabellenname int not null,"+
                                    "aic_fremd int not null,"+
                                    "aic_sprache int not null,"+
                                    "Memo2 varchar(512) null,"+
                                    "primary key(aic_tabellenname,aic_fremd,aic_sprache))");
                    b=b && p.exec("alter table Tooltip add foreign key FK_Tooltip_Tab (aic_tabellenname) references TABELLENNAME (aic_tabellenname) on update restrict on delete restrict");
                    b=b && p.exec("alter table Tooltip add foreign key FK_Tooltip_Sp (aic_sprache) references SPRACHE (aic_sprache) on update restrict on delete restrict");
                  }
                  else if (g.MS() || g.MySQL())
                  {
                    b = b && p.exec("create table Tooltip ("+
                                    "aic_tabellenname int not null,"+
                                    "aic_fremd int not null,"+
                                    "aic_sprache int not null,"+
                                    "Memo2 varchar(512) null,"+
                                    "constraint PK_TOOLTIP primary key(aic_tabellenname,aic_fremd,aic_sprache))");
                    b=b && p.exec("alter table Tooltip add constraint FK_Tooltip_Tab foreign key (aic_tabellenname) references TABELLENNAME (aic_tabellenname)");
                    b=b && p.exec("alter table Tooltip add constraint FK_Tooltip_Sp foreign key (aic_sprache) references SPRACHE (aic_sprache)");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("create table Tooltip ("+
                                    "aic_tabellenname NUMBER(10) not null,"+
                                    "aic_fremd        NUMBER(10) not null,"+
                                    "aic_sprache      NUMBER(10) not null,"+
                                    "Memo2 varchar(512) null,"+
                                    "constraint PK_TOOLTIP primary key(aic_tabellenname,aic_fremd,aic_sprache))");
                    b=b && p.exec("alter table Tooltip add constraint FK_Tooltip_Tab foreign key (aic_tabellenname) references TABELLENNAME (aic_tabellenname)");
                    b=b && p.exec("alter table Tooltip add constraint FK_Tooltip_Sp foreign key (aic_sprache) references SPRACHE (aic_sprache)");
                  }
                  if (b)
                  {
                    Vector Vec=SQL.getVector("select aic_begriffgruppe from begriffgruppe where kennung in ('Show','Button','Checkbox','Radiobutton','Frame','Modell','Druck','Applikation')",g);
                    int iBegriff=SQL.getInteger(g,"select aic_tabellenname from tabellenname where kennung='Begriff'");
                    Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_tabellenname,aic_fremd,aic_sprache,memo from daten_memo join begriff on aic_fremd=aic_begriff and aic_begriffgruppe"+Static.SQL_in(Vec)+" where aic_tabellenname="+iBegriff,true);
                    Vec=SQL.getVector("select aic_tabellenname from tabellenname where kennung in ('Eigenschaft','SPALTE')",g);
                    Tab.addTab(new Tabellenspeicher(g,"select aic_tabellenname,aic_fremd,aic_sprache,memo from daten_memo where aic_tabellenname"+Static.SQL_in(Vec),true));
                    Gauge gauge2=new Gauge(0,Tab.getAnzahlElemente(null),"Tooltips umkopieren",g,false);
                    int i=0;
                    for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                    {
                      p.add("aic_tabellenname",Tab.getI("aic_tabellenname"));
                      p.add("aic_sprache",Tab.getI("aic_sprache"));
                      p.add("aic_fremd",Tab.getI("aic_fremd"));
                      String s=Tab.getM("memo");
                      if (s!=null && s.length()>512)
                        s=s.substring(0,512);
                      p.add("memo2",s);
                      p.insert("tooltip",false);
                      i++;
                      gauge2.setText("",i);
                    }
                  }
                  iIst = b ? Ver(5,6,5):iIst;
                }
                if(b && iIst < Ver(5,6,6) && iSoll>=Ver(5,6,6))	// 16. 4.2009
                {
                  g.diskInfo("Versionsupdate 5.6.6");
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && p.exec("update code set kennung='between2' where Kennung='Bedingung14' and aic_begriffgruppe="+iBed);
                  if (g.Oracle())
                    b = b && p.exec("alter table LAYOUT add AIC_STAMM NUMBER(10) null");
                  else
                    b = b && p.exec("alter table LAYOUT add AIC_STAMM int NULL");
                  if (g.ASA())
                    b = b && p.exec("alter table LAYOUT add foreign key FK_Layout_Stamm (aic_stamm) references STAMM (aic_stamm) on update restrict on delete restrict");
                  else
                    b = b && p.exec("alter table LAYOUT add constraint FK_Layout_Stamm foreign key (aic_stamm) references STAMM (aic_stamm)");
                  iIst = b ? Ver(5,6,6):iIst;
                }
                if(b && iIst < Ver(5,6,7) && iSoll>=Ver(5,6,7))	// 13. 5.2009
                {
                  g.diskInfo("Versionsupdate 5.6.7");
                  p.exec("create index SP_TEST4 on STAMMPOOL (AIC_STAMM,AIC_EIGENSCHAFT asc)");
                  iIst = b ? Ver(5,6,7):iIst;
                }
                gauge.setText("V 5.08 - Erweiterungen",90);
                if(b && iIst < Ver(5,7,1) && iSoll>=Ver(5,7,1))	// 18. 3. 2010
                {
                  g.diskInfo("Versionsupdate 5.7.1");
                  String sInt=g.ASA() ? "integer":g.Oracle() ? "NUMBER(10)":"int";
                  String s="alter table bew_pool add ANR4 " + sInt + " null";
                  for (int i=5;i<10;i++)
                    s+=(g.Oracle() ? " ":",")+(g.MS() ? "":"add ")+"ANR" + i + " " + sInt + " null";
                  gauge.setText("V 5.08 - ANR",91);
                  b = b && p.exec(s);
                  gauge.setText("V 5.08 - ANR-Def",93);
                  for (int i=4;i<10;i++)
                  {
                    b = b && p.exec("alter table BEWEGUNGSTYP add AIC_EIG" + i + " " + sInt + " null");
                    //b = b && p.exec("alter table bew_pool add ANR" + i + " " + sInt + " null");
                    if (g.ASA())
                      b = b && p.exec("alter table BEWEGUNGSTYP add foreign key FK_BEWEGUNGSTYP_ANR"+i+" (AIC_EIG"+i+") references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
                    else
                      b = b && p.exec("alter table BEWEGUNGSTYP add constraint FK_BEWEGUNGSTYP_ANR"+i+" foreign key (AIC_EIG"+i+") references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                  }
                  g.bISQL = true;
                  gauge.setText("V 5.08 - Indexe",94);
                  for (int i=3;i<10;i++)
                    p.exec("create index BEW_ANR"+i+" on BEW_POOL (AIC_BEWEGUNGSTYP,ANR"+i+",LDATE asc)");
                  g.bISQL = false;
                  gauge.setText("V 5.08 - StringX",95);
                  p.exec("delete from daten_stringsehrkurz where spalte_stringsehrkurz is null");
                  p.exec("delete from daten_stringkurz where spalte_stringkurz is null");
                  if (g.ASA())
                    b = b && p.exec("create table DATEN_STRINGX ("+
                                    "AIC_DATEN integer not null,"+
                                    "SPALTE_STRINGX varchar(255),"+
                                    "primary key (AIC_DATEN))");
                  //if (!g.MySQL())
                  //  Transact.fixInfo(StringX_Rebuild(g));
                  /*g.bISQL = true;
                  g.exec("drop view stringXview4");
                  g.exec("drop view stringXview2");
                  g.exec("drop view stringXview");
                  g.bISQL = false;*/
                  iIst = b ? Ver(5,7,1):iIst;
                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");
                }
                if(b && iIst < Ver(5,7,2) && iSoll>=Ver(5,7,2))	//  8. 4. 2010
                {
                  g.diskInfo("Versionsupdate 5.7.2");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                    b = b && p.exec("alter table BEFEHL MODIFY EINGABE varchar(255)"+(g.Oracle()?"":" null"));
                  else if (g.MS())
                    b = b && p.exec("ALTER TABLE BEFEHL ALTER COLUMN EINGABE varchar(255)");
                  p.add("Kennung","SQL");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iSQL = p.insert("begriffgruppe",true);
                  b=b && addCode(iSQL,"get String")>0;
                  b=b && addCode(iSQL,"get Aic")>0;
                  b=b && addCode(iSQL,"exec")>0;
                  b=b && addCode(iSQL,"get Query")>0;
                  iIst = b ? Ver(5,7,2):iIst;
                }
                if(b && iIst < Ver(5,7,3) && iSoll>=Ver(5,7,3))	//  14. 4. 2010
                {
                  g.diskInfo("Versionsupdate 5.7.3");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                  {
                    b = b && p.exec("alter table BEZEICHNUNG MODIFY BEZEICHNUNG varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table STAMM_PROTOKOLL MODIFY BEZEICHNUNG varchar(255)"+(g.Oracle()?"":" null"));
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("ALTER TABLE BEZEICHNUNG ALTER COLUMN BEZEICHNUNG varchar(255)");
                    b = b && p.exec("ALTER TABLE STAMM_PROTOKOLL ALTER COLUMN BEZEICHNUNG varchar(255)");
                  }
                  g.fixInfo(StringX_Rebuild(g));
                  gauge.setText("V 5.08 - Rest",97);
                  int iDt = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  int iSx=p.getInteger("select aic_begriff from begriff where aic_begriffgruppe="+iDt+" and kennung='Stringx'");
                  String[] AryString = new String[] {"StringSehrKurz","StringKurz","String60","StringLang"};
                  for(int i=0;i<AryString.length;i++)
                  {
                    int iBeg=p.getInteger("select aic_begriff from begriff where aic_begriffgruppe="+iDt+" and kennung='"+AryString[i]+"'");
                    if (i<2)
                      b = b && p.exec("update eigenschaft set feldlaenge="+(i==0 ? 10:30)+" where aic_begriff="+iBeg+" and feldlaenge is null");
                    b = b && p.exec("update eigenschaft set aic_begriff="+iSx+" where aic_begriff="+iBeg);
                    b = b && p.exec("delete from begriff where aic_begriff="+iBeg);
                    b = b && p.exec("drop table daten_"+AryString[i]);
                  }
                  iIst = b ? Ver(5,7,3):iIst;
                }
                if(b && iIst < Ver(5,7,4) && iSoll>=Ver(5,7,4))	//  27. 4. 2010
                {
                  g.diskInfo("Versionsupdate 5.7.4");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                    b = b && p.exec("alter table BEGRIFF MODIFY DEFBEZEICHNUNG varchar(255)"+(g.Oracle()?"":" null"));
                  else
                    b = b && p.exec("ALTER TABLE BEGRIFF ALTER COLUMN DEFBEZEICHNUNG varchar(255)");
                  int iPanel=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Panel'");
                  b=b && iPanel>0 && addBegriff(iPanel,"ToolBarH")>0;
                  b=b && iPanel>0 && addBegriff(iPanel,"ToolBarV")>0;

                  iIst = b ? Ver(5,7,4):iIst;
                }
                if(b && iIst < Ver(5,7,5) && iSoll>=Ver(5,7,5))	//  20. 5. 2010
                {
                  g.diskInfo("Versionsupdate 5.7.5");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                  {
                    b = b && p.exec("alter table COMPUTER MODIFY KENNUNG varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table COMPUTER MODIFY HOSTNAME varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table COMPUTER MODIFY IP_ADRESSE varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table COMPUTER MODIFY ADRESSE varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table SYSTEMINFO MODIFY BROWSER varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table SYSTEMINFO MODIFY BROWSER_VERSION varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table SYSTEMINFO MODIFY BETRIEBSSYSTEM varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table SYSTEMINFO MODIFY OS_VERSION varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table SYSTEMINFO MODIFY JAVA_VERSION varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table SYSTEMINFO MODIFY DB_VERSION varchar(255)"+(g.Oracle()?"":" null"));
                    b = b && p.exec("alter table SPALTE_ZUORDNUNG MODIFY TITEL varchar(255)"+(g.Oracle()?"":" null"));
                  }
                  else
                  {
                    b = b && p.exec("ALTER TABLE COMPUTER ALTER COLUMN KENNUNG varchar(255)");
                    b = b && p.exec("ALTER TABLE COMPUTER ALTER COLUMN HOSTNAME varchar(255)");
                    b = b && p.exec("ALTER TABLE COMPUTER ALTER COLUMN IP_ADRESSE varchar(255)");
                    b = b && p.exec("ALTER TABLE COMPUTER ALTER COLUMN ADRESSE varchar(255)");
                    b = b && p.exec("ALTER TABLE SYSTEMINFO ALTER COLUMN BROWSER varchar(255)");
                    b = b && p.exec("ALTER TABLE SYSTEMINFO ALTER COLUMN BROWSER_VERSION varchar(255)");
                    b = b && p.exec("ALTER TABLE SYSTEMINFO ALTER COLUMN BETRIEBSSYSTEM varchar(255)");
                    b = b && p.exec("ALTER TABLE SYSTEMINFO ALTER COLUMN OS_VERSION varchar(255)");
                    b = b && p.exec("ALTER TABLE SYSTEMINFO ALTER COLUMN JAVA_VERSION varchar(255)");
                    b = b && p.exec("ALTER TABLE SYSTEMINFO ALTER COLUMN DB_VERSION varchar(255)");
                    b = b && p.exec("ALTER TABLE SPALTE_ZUORDNUNG ALTER COLUMN TITEL varchar(255)");
                  }
                  if (g.ASA() || g.MS() || g.MySQL())
                  {
                    b = b && p.exec("alter table RASTER add TOD bit null");
                    b = b && p.exec("alter table EIGENSCHAFT add TOD bit null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add TOD bit null");
                    b = b && p.exec("alter table STAMMTYP add TOD bit null");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table RASTER add TOD NUMBER(1) null");
                    b = b && p.exec("alter table EIGENSCHAFT add TOD NUMBER(1) null");
                    b = b && p.exec("alter table BEWEGUNGSTYP add TOD NUMBER(1) null");
                    b = b && p.exec("alter table STAMMTYP add TOD NUMBER(1) null");
                  }
                  iIst = b ? Ver(5,7,5):iIst;
                }
                if(b && iIst < Ver(5,7,6) && iSoll>=Ver(5,7,6))	//  2. 6. 2010
                {
                  g.diskInfo("Versionsupdate 5.7.6");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                  {
                    b = b && p.exec("ALTER TABLE Schriftart MODIFY KENNUNG varchar(255)"+(g.Oracle()?"":" null"));
                  }
                  else
                  {
                    b = b && p.exec("ALTER TABLE Schriftart ALTER COLUMN KENNUNG varchar(255)");
                  }
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && p.exec("update code set kennung='holiday between' where Kennung='Bedingung15' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='is manual2' where Kennung='Bedingung16' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='is reentry' where Kennung='Bedingung17' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='question3' where Kennung='Bedingung18' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='is red' where Kennung='Bedingung19' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='is yellow' where Kennung='Bedingung20' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='is green' where Kennung='Bedingung21' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='= von' where Kennung='Bedingung22' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='import-error' where Kennung='Bedingung23' and aic_begriffgruppe="+iBed);
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  for (int i=60;i<80;i++)
                    b = b && addCode(iRes, "reserve"+i) > 0;
                  int iAnw = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  int iString=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='string command'");
                  int iDate=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  int iInit=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  int iSave=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                  int iMath=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'");
                  b=b && p.exec("update code set kennung='clearEmpty',aic_begriffgruppe="+iAnw+" where Kennung='reserve40' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='get days',aic_begriffgruppe="+iDate+" where Kennung='reserve41' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='set dJoker',aic_begriffgruppe="+iAnw+" where Kennung='reserve42' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='read Temp',aic_begriffgruppe="+iAnw+" where Kennung='reserve43' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='concat error',aic_begriffgruppe="+iString+" where Kennung='reserve44' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='init Stt',aic_begriffgruppe="+iInit+" where Kennung='reserve45' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='Rente',aic_begriffgruppe="+iMath+" where Kennung='reserve46' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='next n years',aic_begriffgruppe="+iDate+" where Kennung='reserve47' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='concat chr',aic_begriffgruppe="+iString+" where Kennung='reserve48' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='concat Format',aic_begriffgruppe="+iString+" where Kennung='reserve49' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='copy new Line',aic_begriffgruppe="+iAnw+" where Kennung='reserve50' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='copy insert Line',aic_begriffgruppe="+iAnw+" where Kennung='reserve51' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='copy file',aic_begriffgruppe="+iAnw+" where Kennung='reserve52' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='set prot-File',aic_begriffgruppe="+iAnw+" where Kennung='reserve53' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='create file2',aic_begriffgruppe="+iAnw+" where Kennung='reserve54' and aic_begriffgruppe="+iRes);
                  b=b && p.exec("update code set kennung='write line2',aic_begriffgruppe="+iSave+" where Kennung='reserve55' and aic_begriffgruppe="+iRes);
                  iIst = b ? Ver(5,7,6):iIst;
                }
                if(b && iIst < Ver(5,7,7) && iSoll>=Ver(5,7,7))	//  23. 6. 2010
                {
                  g.diskInfo("Versionsupdate 5.7.7");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                    b = b && p.exec("ALTER TABLE Raster MODIFY KENNUNG varchar(40)"+(g.Oracle()?"":" null"));
                  else
                    b = b && p.exec("ALTER TABLE Raster ALTER COLUMN KENNUNG varchar(40)");
                  String sInt=g.ASA() ? "integer":g.Oracle() ? "NUMBER(10)":"int";
                  b=b && p.exec("ALTER TABLE FORMULAR ADD AIC_MODELL "+sInt+" null");
                  p.add("Kennung","Web");
                  int iWeb = p.insert("begriffgruppe",true);
                  b=b && iWeb>0;
                  b=b && p.exec("update begriff set aic_begriffgruppe="+iWeb+" where aic_homepage is not null");
                  b=b && p.exec("ALTER TABLE BEGRIFF ADD HOMEPAGE varchar(255) null");
                  b=b && p.exec("update begriff set homepage=(select url from homepage where aic_homepage=begriff.aic_homepage) where aic_homepage is not null");
                  p.exec("update begriff set aic_homepage=null");
                  if (g.MS())
                  {
                    p.exec("alter table begriff drop constraint FK_BEGRIFF_REF_626_HOMEPAGE");
                    p.exec("DROP index begriff.Ref_611_FK");
                  }
                  p.exec("ALTER TABLE BEGRIFF DROP"+(g.ASA()?"":" COLUMN")+" AIC_HOMEPAGE");
                  p.exec("drop table homepage");
                  int iHP=p.getInteger("select aic_tabellenname from tabellenname where kennung='Homepage'");
                  b=b && p.exec("delete from bezeichnung where aic_tabellenname="+iHP);
                  b=b && p.exec("delete from tabellenname where aic_tabellenname="+iHP);
                  iIst = b ? Ver(5,7,7):iIst;
                }
                if(b && iIst < Ver(5,7,8) && iSoll>=Ver(5,7,8))	//  29. 7. 2010
                {
                  g.diskInfo("Versionsupdate 5.7.8");
                  int iBegriff=SQL.getInteger(g,"select aic_tabellenname from tabellenname where kennung='Begriff'");
                  int iAppl=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Applikation'");
                  if (b)
                  {
                    p.add("aic_begriffgruppe",iAppl);
                    p.add("beg_aic_begriffgruppe",iAppl);
                    p.add("aic_tabellenname",iBegriff);
                    p.add("tab_aic_tabellenname",iBegriff);
                    p.add("kennung","Appl2");
                    p.add("REIHENFOLGE",1);
                    b=p.insert("Zuordnung",true)>0;
                  }
                  //b=b && p.exec("insert into Zuordnung(aic_begriffgruppe,beg_aic_begriffgruppe,aic_tabellenname,tab_aic_tabellenname,kennung,REIHENFOLGE) values ("+iAppl+
                  //              ","+iAppl+","+iBegriff+","+iBegriff+",'Appl2',1)");
                  int iPanel=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Panel'");
                  b=b && iPanel>0 && addBegriff(iPanel,"GroupBox2")>0;
                  b=b && iPanel>0 && addBegriff(iPanel,"EmptyBorder")>0;
                  b=b && iPanel>0 && addBegriff(iPanel,"ReservePane4")>0;
                  b=b && iPanel>0 && addBegriff(iPanel,"ReservePane5")>0;
                  int iOp = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                  addCode(iOp,"round");
                  addCode(iOp,"add char");
                  addCode(iOp,"reserveOP1");
                  addCode(iOp,"reserveOP2");
                  addCode(iOp,"reserveOP3");
                  addCode(iOp,"reserveOP4");
                  addCode(iOp,"reserveOP5");
                  iIst = b ? Ver(5,7,8):iIst;
                }
                if(b && iIst < Ver(5,7,9) && iSoll>=Ver(5,7,9))	//  8. 9. 2010
                {
                  g.diskInfo("Versionsupdate 5.7.9");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                    b = b && p.exec("alter table TOOLTIP MODIFY MEMO2 varchar(4000)"+(g.Oracle()?"":" null"));
                  else
                    b = b && p.exec("ALTER TABLE TOOLTIP ALTER COLUMN MEMO2 varchar(4000)");
                  g.diskInfo(g.exec("delete from fensterpos")+"x fensterpos gelöscht");
                  //Static.bView2=g.MS() || g.Oracle();
                  Static.bInsert2=g.MS() || g.MySQL();
                  if (/*Static.bView2 ||*/ Static.bInsert2)
                    p.setParameter("Option","",/*(Static.bView2?1:0)+*/(Static.bInsert2?2:0),0,0,0,false,false);
                  iIst = b ? Ver(5,7,9):iIst;
                }
                gauge.setText("V 5.09 - Erweiterungen",100);
                if(b && iIst < Ver(5,8,1) && iSoll>=Ver(5,8,1))	// 25. 5. 2011
                {
                  g.diskInfo("Versionsupdate 5.8.1");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table befehl ADD MBITS integer null");
                    b = b && p.exec("alter table stamm_protokoll ADD FIRMA integer null");
                    //b = b && p.exec("alter table stamm_protokoll add foreign key FK_FIRMA (FIRMA) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table befehl ADD MBITS NUMBER(10) null");
                    b = b && p.exec("alter table stamm_protokoll ADD FIRMA NUMBER(10) null");
                    b = b && p.exec("alter table stamm_protokoll add constraint FK_FIRMA foreign key (FIRMA) references STAMM (AIC_STAMM)");
                  }
                  else
                  {
                    b = b && p.exec("alter table befehl ADD MBITS int null");
                    b = b && p.exec("alter table stamm_protokoll ADD FIRMA int null");
                    b = b && p.exec("alter table stamm_protokoll add constraint FK_FIRMA foreign key (FIRMA) references STAMM (AIC_STAMM)");
                  }
                  gauge.setText("Befehl-Bits",101);
                  b = b && p.exec("update befehl set MBITS=1 where schleife=1");
                  b = b && p.exec("update befehl set MBITS=0 where MBITS is null");
                  b = b && p.exec("alter table befehl drop"+(g.ASA()?"":" COLUMN")+" schleife");
                  gauge.setText("FirmaRebuild",102);
                  FirmaRebuild(g,0);
                  gauge.setText("Rest",104);
                  int iDT = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  addBegriff(iDT,"Firma");
                  iIst = b ? Ver(5,8,1):iIst;
                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");
                }
                if(b && iIst < Ver(5,8,2) && iSoll>=Ver(5,8,2))	// 9. 6. 2011
                {
                  g.diskInfo("Versionsupdate 5.8.2");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table HAUPTSCHIRM ADD AIC_BENUTZERGRUPPE integer null");
                    b = b && p.exec("alter table FENSTERPOS ADD BITS integer null");
                    b = b && p.exec("alter table FENSTERPOS ADD SATZ integer null");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table HAUPTSCHIRM ADD AIC_BENUTZERGRUPPE NUMBER(10) null");
                    b = b && p.exec("alter table FENSTERPOS ADD BITS NUMBER(10) null");
                    b = b && p.exec("alter table FENSTERPOS ADD SATZ NUMBER(10) null");
                  }
                  else
                  {
                    b = b && p.exec("alter table HAUPTSCHIRM ADD AIC_BENUTZERGRUPPE int null");
                    b = b && p.exec("alter table FENSTERPOS ADD BITS int null");
                    b = b && p.exec("alter table FENSTERPOS ADD SATZ int null");
                  }
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,kennung from begriff where kennung in ('joker gekapselt','Tb_loeschen','Tb_neu','Tb_refresh')",true);
                  int iAnz=0;
                  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                  {
                    String s=Tab.getS("Kennung");
                    if (s.equals("joker gekapselt") || s.equals("Tb_loeschen") || s.equals("Tb_neu") || s.equals("Tb_refresh"))
                    {
                      if (g.exec("delete from begriff where aic_begriff="+Tab.getI("aic_begriff"))>0)
                        iAnz++;
                    }
                  }
                  if (iAnz>0)
                    g.diskInfo(iAnz+" alte Begriffe gelöscht");
                  iIst = b ? Ver(5,8,2):iIst;
                }
                if(b && iIst < Ver(5,8,3) && iSoll>=Ver(5,8,3))	// 21. 6. 2011
                {
                  g.diskInfo("Versionsupdate 5.8.3");
                  if (g.ASA())
                  {
                    b = b && p.exec("create table SPALTEN2 ("+
                                    "AIC_SPALTEN2 integer not null default AUTOINCREMENT,"+
                                    "AIC_SPALTE integer not null,"+
                                    "AIC_BENUTZER integer null,"+
                                    "AIC_BENUTZERGRUPPE integer null,"+
                                    "REIHE integer null,"+
                                    "SORTIERT integer null,"+
                                    "BITS integer not null,"+
                                    "primary key (AIC_SPALTEN2))");
                    b=b && p.exec("alter table SPALTEN2 add foreign key FK_SPALTEN2_SPALTE (AIC_SPALTE) references SPALTE (AIC_SPALTE) on update restrict on delete restrict");
                    b=b && p.exec("alter table SPALTEN2 add foreign key FK_SPALTEN2_BENUTZER (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER) on update restrict on delete restrict");
                    b=b && p.exec("alter table SPALTEN2 add foreign key FK_SPALTEN2_BG (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b=b && p.exec("create table SPALTEN2("+
                        "AIC_SPALTEN2        NUMBER(10)   not null,"+
                        "AIC_SPALTE          NUMBER(10)   not null,"+
                        "AIC_BENUTZER        NUMBER(10)   null,"+
                        "AIC_BENUTZERGRUPPE  NUMBER(10)   null,"+
                        "REIHE               NUMBER(10)   null,"+
                        "SORTIERT            NUMBER(10)   null,"+
                        "BITS                NUMBER(10)   not null,"+
                        "constraint PK_SPALTEN2 primary key (AIC_SPALTEN2))");
                    b=b && p.exec("alter table SPALTEN2 add constraint FK_SPALTEN2_SPALTE foreign key (AIC_SPALTE) references SPALTE (AIC_SPALTE)");
                    b=b && p.exec("alter table SPALTEN2 add constraint FK_SPALTEN2_BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                    b=b && p.exec("alter table SPALTEN2 add constraint FK_SPALTEN2_BG foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                    p.createAllSequences();
                  }
                  else if (g.MS() || g.MySQL())
                  {
                    b=b && p.exec("create table SPALTEN2("+
                                  "AIC_SPALTEN2 int not null "+(g.MS()?"identity,":"AUTO_INCREMENT,")+
                                  "AIC_SPALTE   int not null,"+
                                  "AIC_BENUTZER int null,"+
                                  "AIC_BENUTZERGRUPPE int null,"+
                                  "REIHE        int null,"+
                                  "SORTIERT     int null,"+
                                  "BITS         int not null,"+
                                  "constraint PK_SPALTEN2 primary key (AIC_SPALTEN2))");
                    if (g.MySQL())
                      b=b && p.exec("alter table spalten2 engine=innodb");
                    b=b && p.exec("alter table SPALTEN2 add constraint FK_SPALTEN2_SPALTE foreign key (AIC_SPALTE) references SPALTE (AIC_SPALTE)");
                    b=b && p.exec("alter table SPALTEN2 add constraint FK_SPALTEN2_BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                    b=b && p.exec("alter table SPALTEN2 add constraint FK_SPALTEN2_BG foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                  }
                  iIst = b ? Ver(5,8,3):iIst;
                }
                if(b && iIst < Ver(5,8,4) && iSoll>=Ver(5,8,4))	// 10. 8. 2011
                {
                  g.diskInfo("Versionsupdate 5.8.4");
                  b = b && p.exec("alter table ABSCHNITT ADD KENNZEICHEN varchar(255) null");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                  {
                    b = b && p.exec("alter table ABSCHNITT MODIFY KENNUNG varchar(40)" + (g.Oracle() ? "" : " null"));
                    b = b && p.exec("alter table ZEILE MODIFY Links varchar(255)" + (g.Oracle() ? "" : " null"));
                    b = b && p.exec("alter table ZEILE MODIFY Mitte varchar(255)" + (g.Oracle() ? "" : " null"));
                    b = b && p.exec("alter table ZEILE MODIFY Rechts varchar(255)" + (g.Oracle() ? "" : " null"));
                  }
                  else
                  {
                    b = b && p.exec("ALTER TABLE ABSCHNITT ALTER COLUMN KENNUNG varchar(40)");
                    b = b && p.exec("ALTER TABLE ZEILE ALTER COLUMN Links varchar(255)");
                    b = b && p.exec("ALTER TABLE ZEILE ALTER COLUMN Mitte varchar(255)");
                    b = b && p.exec("ALTER TABLE ZEILE ALTER COLUMN Rechts varchar(255)");
                  }
                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  if (b && iGruppe>0)
                    b=b && addCode(iGruppe,"= Kennzeichen")>0;

                  iIst = b ? Ver(5,8,4):iIst;
                }
                if(b && iIst < Ver(5,8,5) && iSoll>=Ver(5,8,5))	// 30. 8. 2011
                {
                  g.diskInfo("Versionsupdate 5.8.5");
                  int iAnw=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iAnw,"comment")>0;
                  b=b && addCode(iAnw,"testinfo")>0;
                  b=b && addCode(iAnw,"test message")>0;
                  b=b && addCode(iAnw,"set title")>0;
                  b=b && p.exec("alter table HAUPTSCHIRM ADD AIC_LOGGING"+intSQL()+" null");
                  if (g.ASA())
                    b=b && p.exec("alter table HAUPTSCHIRM add foreign key FK_HAUPTSCHIRM_LOGGING (AIC_LOGGING) references LOGGING (AIC_LOGGING) on update restrict on delete restrict");
                  else
                    b=b && p.exec("alter table HAUPTSCHIRM add constraint FK_HAUPTSCHIRM_LOGGING foreign key (AIC_LOGGING) references LOGGING (AIC_LOGGING)");
                  iIst = b ? Ver(5,8,5):iIst;
                }
                if(b && iIst < Ver(5,8,6) && iSoll>=Ver(5,8,6))	// 20. 9. 2011
                {
                  g.diskInfo("Versionsupdate 5.8.6");
                  int iSQL=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='SQL'");
                  p.exec("update code set kennung='SQL_getString' where aic_begriffgruppe="+iSQL+" and kennung='get String'");
                  p.exec("update code set kennung='SQL_getAic' where aic_begriffgruppe="+iSQL+" and kennung='get Aic'");
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  p.exec("update code set kennung='get weeks' where aic_begriffgruppe="+iRes+" and kennung='reserve56'");
                  p.exec("update code set kennung='set ANSI' where aic_begriffgruppe="+iRes+" and kennung='reserve57'");
                  p.exec("update code set kennung='get path' where aic_begriffgruppe="+iRes+" and kennung='reserve58'");
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  p.exec("update code set kennung='weekday=' where aic_begriffgruppe="+iBed+" and kennung='Bedingung24'");
                  p.exec("update code set kennung='in VecAic' where aic_begriffgruppe="+iBed+" and kennung='Bedingung25'");
                  p.exec("update code set kennung='pos next not date' where aic_begriffgruppe="+iBed+" and kennung='Bedingung26'");
                  p.exec("update code set kennung='is String' where aic_begriffgruppe="+iBed+" and kennung='Bedingung27'");
                  for (int i=30;i<40;i++)
                      b = b && addCode(iBed, "Bedingung"+i) > 0;
                  iIst = b ? Ver(5,8,6):iIst;
                }
                if(b && iIst < Ver(5,8,7) && iSoll>=Ver(5,8,7))	// 26. 9. 2011
                {
                  g.diskInfo("Versionsupdate 5.8.7");
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b = b && addCode(iBed, "between3") > 0;
                  int iAnw=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anweisung'");
                  b=b && addCode(iAnw,"set filter")>0;
                  b=b && addCode(iAnw,"set destination")>0;
                  b=b && addCode(iAnw,"clear destination")>0;
                  iIst = b ? Ver(5,8,7):iIst;
                }
                if(b && iIst < Ver(5,8,8) && iSoll>=Ver(5,8,8))	// 24.10. 2011
                {
                  g.diskInfo("Versionsupdate 5.8.8");
                  b=b && p.exec("update tabellenname set fixe_sprache=1 where aic_tabellenname=38");
                  iIst = b ? Ver(5,8,8):iIst;
                }
                gauge.setText("V 5.10 - Erweiterungen",110);
                if(b && iIst < Ver(5,9,1) && iSoll>=Ver(5,9,1))	// 15.03. 2013
                {
                  g.diskInfo("Versionsupdate 5.9.1");
                  b=b && addDateSpalte("abschluss","AB");                     // 1228
                  b=b && addIntSpalte("protokoll","aic_benutzer","Benutzer"); // 1332
                  b=b && p.exec("update protokoll set aic_benutzer=(select aic_benutzer from logging where aic_logging=protokoll.aic_logging)");
                  b=b && addBitSpalte("darstellung","HIDE");                  // 1425
                  b=b && addIntSpalte("feiertag_zuordnung","aic_stamm","Stamm"); //2004
                  b=b && addIntSpalte("spalten2","aic_stamm","Stamm");        // 2010, 2022
                  b=b && addIntSpalte("spalte","aic_farbe","Farbe");          // 2011
                  b=b && addDateSpalte("begriff","ENDE");                     // 2032
                  b=b && addIntSpalte("layout","aic_schrift","Schrift");      // 2046
                  iIst = b ? setVer(5,9,1):iIst;

                }
                if(b && iIst < Ver(5,9,2) && iSoll>=Ver(5,9,2))	// 25.04. 2013
                {
                  g.diskInfo("Versionsupdate 5.9.2");
                  b=b && addIntSpalte("spalten2","aic_farbe","Farbe");          // 2011
                  b=b && addIntSpalte("spalten2","aic_begriff","Begriff");      // Format
                  b=b && addIntSpalte("spalten2","aic_abfrage","Abfrage");      // 2010
                  b=b && p.exec("update spalten2 set aic_abfrage=(select aic_abfrage from spalte where aic_spalte=spalten2.aic_spalte)");
                  if (g.ASA())
                  {
                    b = b && p.exec("create table SPALTE_Z2 ("+
                                    "AIC_SPALTE_Z2 integer not null default AUTOINCREMENT,"+
                                    "AIC_SPALTE integer not null,"+
                                    "AIC_STAMM  integer not null," +
                                    "REIHE integer null,"+
                                    "TITEL varchar(255) null," +
                                    "AIC_BENUTZER integer null,"+
                                    "AIC_BENUTZERGRUPPE integer null,"+
                                    "primary key (AIC_SPALTE_Z2))");
                    b=b && p.exec("alter table SPALTE_Z2 add foreign key FK_SPALTE_Z2_SPALTE (AIC_SPALTE) references SPALTE (AIC_SPALTE) on update restrict on delete restrict");
                    b=b && p.exec("alter table SPALTE_Z2 add foreign key FK_SPALTE_Z2_STAMM (AIC_STAMM) references STAMM (AIC_STAMM) on update restrict on delete restrict");
                    b=b && p.exec("alter table SPALTE_Z2 add foreign key FK_SPALTE_Z2_BENUTZER (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER) on update restrict on delete restrict");
                    b=b && p.exec("alter table SPALTE_Z2 add foreign key FK_SPALTE_Z2_BG (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b=b && p.exec("create table SPALTE_Z2("+
                        "AIC_SPALTE_Z2       NUMBER(10)   not null,"+
                        "AIC_SPALTE          NUMBER(10)   not null,"+
                        "AIC_STAMM           NUMBER(10)   not null,"+
                        "REIHE               NUMBER(10)   null,"+
                        "TITEL               varchar(255) null," +
                        "AIC_BENUTZER        NUMBER(10)   null,"+
                        "AIC_BENUTZERGRUPPE  NUMBER(10)   null,"+
                        "constraint PK_SPALTE_Z2 primary key (AIC_SPALTE_Z2))");
                    b=b && p.exec("alter table SPALTE_Z2 add constraint FK_SPALTE_Z2_SPALTE foreign key (AIC_SPALTE) references SPALTE (AIC_SPALTE)");
                    b=b && p.exec("alter table SPALTE_Z2 add constraint FK_SPALTE_Z2_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("alter table SPALTE_Z2 add constraint FK_SPALTE_Z2_BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                    b=b && p.exec("alter table SPALTE_Z2 add constraint FK_SPALTE_Z2_BG foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                    p.createAllSequences();
                  }
                  else if (g.MS() || g.MySQL())
                  {
                    b = b && p.exec("create table SPALTE_Z2(" +
                                    "AIC_SPALTE_Z2 int not null " + (g.MS() ? "identity," : "AUTO_INCREMENT,") +
                                    "AIC_SPALTE   int not null," +
                                    "AIC_STAMM    int not null," +
                                    "REIHE        int null," +
                                    "TITEL        varchar(255) null," +
                                    "AIC_BENUTZER int null," +
                                    "AIC_BENUTZERGRUPPE int null," +
                                    "constraint PK_SPALTE_Z2 primary key (AIC_SPALTE_Z2))");
                    b=b && p.exec("alter table SPALTE_Z2 add constraint FK_SPALTE_Z2_SPALTE foreign key (AIC_SPALTE) references SPALTE (AIC_SPALTE)");
                    b=b && p.exec("alter table SPALTE_Z2 add constraint FK_SPALTE_Z2_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                    b=b && p.exec("alter table SPALTE_Z2 add constraint FK_SPALTE_Z2_BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                    b=b && p.exec("alter table SPALTE_Z2 add constraint FK_SPALTE_Z2_BG foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                  }
                  iIst = b ? setVer(5,9,2):iIst;
                }
                if(b && iIst < Ver(5,9,3) && iSoll>=Ver(5,9,3))	// 29.05. 2013
                {
                  g.diskInfo("Versionsupdate 5.9.3");
                  b=b && addIntSpalte("Parameter","aic_mandant","Mandant");          // 2076
                  p.exec("update parameter set aic_mandant=(select aic_mandant from logging where aic_logging=parameter.aic_logging)"+
                         " where aic_benutzer is null and aic_computer is null and aic_logging is not null");
                  b=b && addIntSpalte("Feiertag_Zuordnung","aic_logging","Logging"); // 2077
                  //b=b && addIntSpalte("Feiertag_Zuordnung","log_aic_logging","Logging");
                  b = b && p.exec("update Feiertag_Zuordnung set aic_stamm=16 where aic_stamm is null");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table Feiertag_Zuordnung drop PRIMARY KEY");
                    b = b && p.exec("alter table Feiertag_Zuordnung drop foreign key FK_Feiertag_Zuordnung_Stamm");
                    b = b && p.exec("alter table Feiertag_Zuordnung MODIFY aic_stamm integer not null");
                    b = b && p.exec("alter table Feiertag_Zuordnung add PRIMARY KEY (AIC_LAND, AIC_FEIERTAG,AIC_STAMM)");
                  }
                  else if (g.MS())
                  {
                    g.bISQL = true;
                    p.exec("ALTER TABLE Feiertag_Zuordnung DROP CONSTRAINT PK__FEIERTAG_ZUORDNU__6D0D32F4");
                    p.exec("ALTER TABLE Feiertag_Zuordnung DROP CONSTRAINT FEIERTAG_ZUORDNUNG_PK");
                    p.exec("ALTER TABLE Feiertag_Zuordnung DROP CONSTRAINT PK_FEIERTAG_ZUORDNUNG");
                    g.bISQL = false;
                    b = b && p.exec("ALTER TABLE Feiertag_Zuordnung ALTER COLUMN aic_stamm int NOT NULL");
                    b = b && p.exec("ALTER TABLE Feiertag_Zuordnung ADD CONSTRAINT PK_FEIERTAG_ZUORDNUNG PRIMARY KEY CLUSTERED ([AIC_LAND], [AIC_FEIERTAG], [AIC_STAMM])");
                  }
                  else if (g.MySQL())
                  {
                    b = b && p.exec("alter table Feiertag_Zuordnung drop PRIMARY KEY");
                    p.exec("ALTER TABLE Feiertag_Zuordnung DROP index PK__FEIERTAG_ZUORDNU__6D0D32F4");
                    b = b && p.exec("ALTER TABLE Feiertag_Zuordnung DROP INDEX FEIERTAG_ZUORDNUNG_PK");
                    b = b && p.exec("ALTER TABLE Feiertag_Zuordnung MODIFY COLUMN aic_stamm int NOT NULL");
                    //b = b && p.exec("create unique index FEIERTAG_ZUORDNUNG_PK on FEIERTAG_ZUORDNUNG (AIC_LAND, AIC_FEIERTAG,AIC_STAMM)");
                    b = b && p.exec("alter table Feiertag_Zuordnung add PRIMARY KEY (AIC_LAND, AIC_FEIERTAG,AIC_STAMM)");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table Feiertag_Zuordnung MODIFY aic_stamm number(10) not null");
                    b = b && p.exec("alter table Feiertag_Zuordnung drop PRIMARY KEY");
                    b = b && p.exec("alter table Feiertag_Zuordnung add PRIMARY  KEY (AIC_LAND, AIC_FEIERTAG,AIC_STAMM)");
                  }

                  iIst = b ? setVer(5,9,3):iIst;
                }
                if(b && iIst < Ver(5,9,4) && iSoll>=Ver(5,9,4))	// 20.06. 2013
                {
                  g.diskInfo("Versionsupdate 5.9.4");
                  // Land: aic_abfrage löschen
                  if (g.ASA() || g.MySQL())
                    p.exec("alter table LAND drop foreign key FK_LAND_BST");
                  else if (g.MS())
                    p.exec("alter table LAND drop constraint FK_LAND_BST");
                  p.exec("ALTER TABLE LAND DROP"+(g.ASA()?"":" COLUMN")+" AIC_ABFRAGE");
                  // Zuordnung HS/EF <-> Druck
                  int iBegriff=SQL.getInteger(g,"select aic_tabellenname from tabellenname where kennung='Begriff'");
                  int iDruck=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Druck'");
                  int iFrame=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Frame'");
                  if (b)
                  {
                    p.add("aic_begriffgruppe",iDruck);
                    p.add("beg_aic_begriffgruppe",iFrame);
                    p.add("aic_tabellenname",iBegriff);
                    p.add("tab_aic_tabellenname",iBegriff);
                    p.add("kennung","Druck");
                    //p.add("REIHENFOLGE",1);
                    b=p.insert("Zuordnung",true)>0;
                  }
                  p.exec("delete from zuordnung where kennung='BENUTZER'");
                  p.exec("delete from zuordnung where kennung='Feiertag'");
                  // Reserve-Befehle
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  for (int i=80;i<100;i++)
                    b = b && addCode(iRes, "reserve"+i) > 0;
                  iIst = b ? setVer(5,9,4):iIst;
                }
                if(b && iIst < Ver(5,9,5) && iSoll>=Ver(5,9,5))	// 30.07. 2013
                {
                  g.diskInfo("Versionsupdate 5.9.5");
                  b = b && modifyString("Benutzer","Kennung",40);
                  b = b && modifyString("Benutzergruppe","Kennung",40);
                  b = b && modifyString("HAUPTSCHIRM","Kennung",40);
                  b = b && modifyString("MANDANT","Kennung",40);
                  b = b && modifyString("SCHRIFT","Kennung",40);
                  b = b && modifyString("SPRACHE","Kennung",40);
                  b = b && modifyString("DEFIMPORT","FILENAME",255);

                  b=b && addIntSpalte("ABFRAGE","MOD_AIC_MODELL","AIC_MODELL","MODELL");
                  /*b = b && */p.exec("update abfrage set mod_aic_modell=aic_modell,aic_modell=null where aic_begriff in (select aic_begriff from begriff where"+g.bit("bits",0x40000000000000l)+")");

                  b=b && addIntSpalte("STAMM","STA_AIC_STAMM","AIC_STAMM","STAMM");
                  iIst = b ? setVer(5,9,5):iIst;
                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");
                }
                if(b && iIst < Ver(5,9,6) && iSoll>=Ver(5,9,6))	// 04.09. 2013
                {
                  g.diskInfo("Versionsupdate 5.9.6");
                  b=b && p.exec("update code set kennung='init TabPerioden' where kennung='reserve59'");
                  b=b && p.exec("update code set kennung='add Periode' where kennung='reserve60'");
                  b=b && p.exec("update code set kennung='copy next line' where kennung='reserve61'");
                  b=b && p.exec("update code set kennung='get Firma' where kennung='reserve62'");
                  b=b && p.exec("update code set kennung='send to AServer' where kennung='reserve63'");
                  b=b && p.exec("update code set kennung='xml addTitel3' where kennung='reserve64'");
                  b=b && p.exec("update code set kennung='xml Zeile' where kennung='reserve65'");
                  b=b && p.exec("update code set kennung='xml set' where kennung='reserve66'");
                  b=b && p.exec("update code set kennung='xml double' where kennung='reserve67'");
                  b=b && p.exec("update code set kennung='get from AServer' where kennung='reserve68'");
                  b=b && p.exec("update code set kennung='VecAic add Vec' where kennung='reserve69'");
                  b=b && p.exec("update code set kennung='clear history' where kennung='reserve70'");
                  b=b && p.exec("update code set kennung='call AServer-Modell' where kennung='reserve71'");
                  b=b && p.exec("update code set kennung='split String' where kennung='reserve72'");
                  b=b && p.exec("update code set kennung='concat spaces' where kennung='reserve73'");
                  b=b && p.exec("update code set kennung='set Anlage' where kennung='reserve74'");
                  b=b && p.exec("update code set kennung='remember real aic' where kennung='reserve75'");
                  b=b && p.exec("update code set kennung='get Color' where kennung='reserve76'");
                  b=b && p.exec("update code set kennung='set Mandant' where kennung='reserve77'");
                  b=b && p.exec("update code set kennung='get length' where kennung='reserve78'");
                  b=b && p.exec("update code set kennung='xml Text' where kennung='reserve79'");
                  b=b && p.exec("update code set kennung='change Mandant' where kennung='reserve80'");
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && p.exec("update code set kennung='is Sperre' where kennung='Bedingung28' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='use AServer' where kennung='Bedingung29' and aic_begriffgruppe="+iBed);
                  b=b && p.exec("update code set kennung='is reCalc' where kennung='Bedingung30' and aic_begriffgruppe="+iBed);
                  iIst = b ? setVer(5,9,6):iIst;
                }
                if(b && iIst < Ver(5,9,7) && iSoll>=Ver(5,9,7))	// 03.10. 2013
                {
                  g.diskInfo("Versionsupdate 5.9.7");
                  b=b && addIntSpalte("Abschluss","aic_mandant","Mandant");          // 3004
                  b=b && addIntSpalte("Zeile","aic_mandant","Mandant");
                  b=b && addIntSpalte("feiertag_zuordnung","aic_mandant","Mandant");
                  b=b && addBitSpalte("Rolle","Tod");  //
                  p.add("Kennung","Programm");         // 2130
                  p.add("code",1);
                  b= p.insert("begriffgruppe",true)>0;
                 if (g.ApplPort())
                 {
                  int iDruck=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Druck'");  //
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,defbezeichnung from begriff where aic_begriffgruppe="+iDruck+" and jeder is null",true);
                  Gauge gauge2=new Gauge(0,Tab.getAnzahlElemente(null),"Drucke umkopieren",g,false);
                  int iFrame=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Frame'");
                  int iAppl=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Applikation'");
                  p.exec("delete from begriff_zuordnung where aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+iDruck+" and jeder is null)");
                  Tabellenspeicher Tab2=null;
                  String sSQL="select distinct z.beg_aic_begriff from begriff b join begriff_zuordnung z on b.aic_begriff=z.beg_aic_begriff and b.aic_begriffgruppe in ("+iAppl+","+iFrame+")"+
                      " and b.aic_code is null where z.aic_begriff in (select aic_begriff from begriff_zuordnung where beg_aic_begriff=?)";
                  for(;!Tab.out();Tab.moveNext())
                  {
                    gauge2.setText(Tab.getS("defbezeichnung"),Tab.getPos());
                    if (Tab2==null)
                      Tab2=new Tabellenspeicher(g,sSQL,Tab.getS("aic_begriff"),true,null);
                    else
                      Tab2.refresh(g,sSQL,true,true,Tab.getS("aic_begriff"));
                    if (Tab2.isEmpty())
                      g.fixInfo("Druck "+Tab.getS("defbezeichnung")+" wird nicht verwendet");
                    else
                      for(Tab2.moveFirst();!Tab2.out();Tab2.moveNext())
                      {
                        p.add("aic_begriff",Tab.getI("aic_begriff"));
                        p.add("beg_aic_begriff",Tab2.getI("beg_aic_begriff"));
                        p.insert("begriff_zuordnung",false);
                      }
                  }
                  gauge2.setText("fertig Drucke",gauge2.getMax());
                  gauge2.free();
                 }
                  iIst = b ? setVer(5,9,7):iIst;
                }
                if(b && iIst < Ver(5,9,8) && iSoll>=Ver(5,9,8))	// 16.10. 2013
                {
                  g.diskInfo("Versionsupdate 5.9.8");
                  b=b && p.exec("update Abschluss set aic_mandant=1 where aic_mandant is null");
                  b=b && p.exec("update zeile set aic_mandant=1 where aic_mandant is null");
                  b=b && p.exec("update feiertag_zuordnung set aic_mandant=1 where aic_mandant is null");
                  b=b && addDTSpalte("Verlauf","fertig");
                  b=b && addLongSpalte("Verlauf","Dauer");
                  b=b && addBitSpalte("Verlauf","Abbruch");
                  iIst = b ? setVer(5,9,8):iIst;
                }
                if(b && iIst < Ver(5,9,9) && iSoll>=Ver(5,9,9))	// 31.10. 2013
                {
                  g.diskInfo("Versionsupdate 5.9.9");
                  b = b && p.exec("update stammtyp set Ende=0 where Ende is null");
                  b = b && p.exec("update stammtyp set Translate=0 where Translate is null");
                  b = b && p.exec("update stammtyp set Benutzer=0 where Benutzer is null");
                  b = b && p.exec("update stammtyp set Einheit=0 where Einheit is null");
                  b = b && p.exec("update stammtyp set Lizenz=0 where Lizenz is null");
                  b = b && p.exec("update stammtyp set Copy=0 where Copy is null");
                  b = b && p.exec("update stammtyp set Std_Formular=0 where Std_Formular is null");
                  b = b && p.exec("update stammtyp set KEIN_STICHTAG=0 where KEIN_STICHTAG is null");
                  b = b && p.exec("alter table STAMMTYP add STTBITS"+intSQL()+" null");
                  b = b && p.exec("update STAMMTYP SET STTBITS=Ende+translate*2+Benutzer*4+Einheit*8+Lizenz*16+Copy*32+Std_Formular*64+KEIN_STICHTAG*128");
                  b = b && p.exec("alter table STAMMTYP drop"+(g.ASA()?"":" COLUMN")+" Ende");
                  b = b && p.exec("alter table STAMMTYP drop"+(g.ASA()?"":" COLUMN")+" translate");
                  b = b && p.exec("alter table STAMMTYP drop"+(g.ASA()?"":" COLUMN")+" Benutzer");
                  b = b && p.exec("alter table STAMMTYP drop"+(g.ASA()?"":" COLUMN")+" Einheit");
                  b = b && p.exec("alter table STAMMTYP drop"+(g.ASA()?"":" COLUMN")+" Lizenz");
                  b = b && p.exec("alter table STAMMTYP drop"+(g.ASA()?"":" COLUMN")+" Copy");
                  b = b && p.exec("alter table STAMMTYP drop"+(g.ASA()?"":" COLUMN")+" Std_Formular");
                  b = b && p.exec("alter table STAMMTYP drop"+(g.ASA()?"":" COLUMN")+" KEIN_STICHTAG");

                  int iAnz=g.exec("update eigenschaft set bits=bits-"+0x20000000+" where"+g.bit("bits",0x20000000));
                  if (iAnz>0) g.diskInfo(iAnz+"x bit Gruppierbar aus Eigenschaft gelöscht");
                  iAnz=g.exec("update eigenschaft set bits=bits-"+0x40000000+" where"+g.bit("bits",0x40000000));
                  if (iAnz>0) g.diskInfo(iAnz+"x bit KeinANR aus Eigenschaft gelöscht");
                  iIst = b ? setVer(5,9,9):iIst;
                }
                if(b && iIst < Ver(5,10,0) && iSoll>=Ver(5,10,0))	// 4.11. 2013
                {
                  g.diskInfo("Versionsupdate 5.10.0");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table Feiertag_Zuordnung drop PRIMARY KEY");
                    b = b && p.exec("alter table Feiertag_Zuordnung drop foreign key FK_Feiertag_Zuordnung_Mandant");
                    b = b && p.exec("alter table Feiertag_Zuordnung MODIFY aic_mandant integer not null");
                    b = b && p.exec("alter table Feiertag_Zuordnung add PRIMARY KEY (AIC_LAND, AIC_FEIERTAG,AIC_STAMM,AIC_MANDANT)");
                  }
                  else if (g.MS())
                  {
                    g.bISQL = true;
                    p.exec("ALTER TABLE Feiertag_Zuordnung DROP CONSTRAINT PK_FEIERTAG_ZUORDNUNG");
                    p.exec("drop index feiertag_zuordnung.feiertag_zuordnung_pk");
                    p.exec("drop index feiertag_zuordnung.pk__feiertag_zuordnu__6D0D32F4");
                    g.bISQL = false;
                    b = b && p.exec("ALTER TABLE Feiertag_Zuordnung ALTER COLUMN aic_mandant int NOT NULL");
                    b = b && p.exec("ALTER TABLE Feiertag_Zuordnung ADD CONSTRAINT PK_FEIERTAG_ZUORDNUNG PRIMARY KEY CLUSTERED ([AIC_LAND], [AIC_FEIERTAG], [AIC_STAMM],[AIC_MANDANT])");
                  }
                  else if (g.MySQL())
                  {
                    b = b && p.exec("alter table Feiertag_Zuordnung drop PRIMARY KEY");
                    b = b && p.exec("ALTER TABLE Feiertag_Zuordnung MODIFY COLUMN aic_mandant int NOT NULL");
                    b = b && p.exec("alter table Feiertag_Zuordnung add PRIMARY KEY (AIC_LAND, AIC_FEIERTAG,AIC_STAMM,AIC_MANDANT)");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table Feiertag_Zuordnung MODIFY aic_mandant number(10) not null");
                    b = b && p.exec("alter table Feiertag_Zuordnung drop PRIMARY KEY");
                    b = b && p.exec("alter table Feiertag_Zuordnung add PRIMARY  KEY (AIC_LAND, AIC_FEIERTAG,AIC_STAMM,AIC_MANDANT)");
                  }
                  iIst = b ? setVer(5,10,0):iIst;
                }
                gauge.setText("V 5.11 - Erweiterungen",120);
                if(b && iIst < Ver(5,10,1) && iSoll>=Ver(5,10,1))	// 7. 7. 2014
                {
                  g.diskInfo("Versionsupdate 5.10.1");
                  b = b && addIntSpalte("spalten2","ANR","aic_stamm","Stamm");
                  //b = b && addIntSpalte("spalte_z2","ANR","aic_stamm","Stamm");
                  b = b && p.exec("alter table spalten2 add TITEL varchar(255) null");
                  b = b && p.exec("alter table spalten2 add LAENGE"+intSQL()+" null");
                  if (g.ASA())
                  {
                    b = b && p.exec("create table SPERRE(" +
                                    "AIC_SPERRE integer not null default AUTOINCREMENT," +
                                    "AIC_PROTOKOLL integer not null," + // wer sperrt
                                    "AIC_STAMM  integer null," +  // für Planung: welcher MA gesperrt
                                    "AIC_BEWEGUNGSTYP  integer null," +       //:Planung oder leer für Stammsatz
                                    "P_VON             datetime null," +      //:Plaungszeitraum von
                                    "P_BIS             datetime null," +      //:Plaungszeitraum bis
                                    "AIC_BEW_POOL  integer null," + // für Tabelle: welche Sätze gesperrt
                                    "primary key (AIC_SPERRE))");
                    b=b && p.exec("alter table SPERRE add foreign key FK_SPERRE_PROTOKOLL (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("create table SPERRE(" +
                                    "AIC_SPERRE NUMBER(10) not null," +
                                    "AIC_PROTOKOLL NUMBER(10) not null," + // wer sperrt
                                    "AIC_STAMM  NUMBER(10) null," +  // für Planung: welcher MA gesperrt
                                    "AIC_BEWEGUNGSTYP  NUMBER(10) null," +
                                    "P_VON             Date null," +
                                    "P_BIS             Date null," +
                                    "AIC_BEW_POOL  NUMBER(10) null," + // für Tabelle: welche Sätze gesperrt
                                    "constraint PK_SPERRE primary key (AIC_SPERRE))");
                    b = b && p.exec("alter table SPERRE add constraint FK_SPERRE_PROTOKOLL foreign key (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL)");
                    //p.createAllSequences();
                  }
                  else if (g.MS() || g.MySQL())
                  {
                    b = b && p.exec("create table SPERRE(" +
                                    "AIC_SPERRE int not null "+(g.MS()?"identity,":"AUTO_INCREMENT,")+
                                    "AIC_PROTOKOLL int not null," + // wer sperrt
                                    "AIC_STAMM  int null," + // für Planung: welcher MA gesperrt
                                    "AIC_BEWEGUNGSTYP  int null," +
                                    "P_VON             DateTime  null," +
                                    "P_BIS             DateTime  null," +
                                    "AIC_BEW_POOL  int null," + // für Tabelle: welche Sätze gesperrt
                                    "primary key (AIC_SPERRE))");
                    if (g.MySQL())
                      b=b && p.exec("alter table SPERRE engine=innodb");
                    b = b && p.exec("alter table SPERRE add constraint FK_SPERRE_PROTOKOLL foreign key (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL)");
                  }
                  b = b && addIntSpalte("Modell","aic_Abfrage","Abfrage");
                  iIst = b ? setVer(5,10,1):iIst;
                }
                if(b && iIst < Ver(5,10,2) && iSoll>=Ver(5,10,2))	// 28. 7. 2014
                {
                  g.diskInfo("Versionsupdate 5.10.2");
                  addDateSpalte("spalten2","Datum");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Tabellentyp'");
                  b=b && iGruppe>0;
                  b=b && addCode(iGruppe,"Stundenerf")>0;
                  b=b && addCode(iGruppe,"Schnellerf")>0;
                  iIst = b ? setVer(5,10,2):iIst;
                }
                if(b && iIst < Ver(5,10,3) && iSoll>=Ver(5,10,3))	// 18. 9. 2014
                {
                  g.diskInfo("Versionsupdate 5.10.3");
                  b = b && p.exec("alter table defimport add Programm varchar(255) null");
                  b = b && p.exec("alter table defimport add Prog_Ver varchar(255) null");
                  if (g.ASA())
                    b = b && p.exec("create table PROGRAMM_ZUORDNUNG("+
                                "AIC_CODE        integer               not null,"+
                                "AIC_BEGRIFF    integer               not null,"+
                                "REIHENFOLGE      integer             null,"+
                                "primary key (AIC_CODE, AIC_BEGRIFF))");
                  else if (g.Oracle())
                    b = b && p.exec("create table PROGRAMM_ZUORDNUNG(" +
                                    "AIC_CODE NUMBER(10) not null," +
                                    "AIC_BEGRIFF NUMBER(10) not null,"+
                                    "REIHENFOLGE NUMBER(10) null,"+
                                    "constraint PK_PROGRAMM_ZUORDNUNG primary key (AIC_CODE, AIC_BEGRIFF))");
                  else if (g.MS() || g.MySQL())
                    b = b && p.exec("create table PROGRAMM_ZUORDNUNG("+
                                "AIC_CODE       int               not null,"+
                                "AIC_BEGRIFF    int               not null,"+
                                "REIHENFOLGE    int               null,"+
                                "constraint PK_PROGRAMM_ZUORDNUNG primary key (AIC_CODE, AIC_BEGRIFF))");

                  p.add("Kennung","Programm");
                  p.add("aic_tabellenname",p.getInteger("select aic_tabellenname from tabellenname where kennung='CODE'"));
                  p.add("tab_aic_tabellenname",p.getInteger("select aic_tabellenname from tabellenname where kennung='Begriff'"));
                  p.add("aic_begriffgruppe",SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Programm'"));
                  p.add("beg_aic_begriffgruppe",SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Abfrage'"));
                  p.add("Reihenfolge",true);
                  b=b && p.insert("zuordnung",true)>0;

                  iIst = b ? setVer(5,10,3):iIst;
                }
                if(b && iIst < Ver(5,10,4) && iSoll>=Ver(5,10,4))	// 15.10. 2014
                {
                  g.diskInfo("Versionsupdate 5.10.4");
                  int iJoker=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Platzhalter'");
                  if (b && iJoker>0)
                  {
                        addCode(iJoker,"*Combobox*");
                        addCode(iJoker,"*Reserve1*");
                        b=addCode(iJoker,"*Reserve2*")>0;
		  }
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  int iDate=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='dateorder'");
                  int iSQL=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='SQL'");
                  int iSave=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                  int iMail = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='E-Mail'");
                  p.exec("update code set kennung='get Temp' where kennung='reserve81'");
                  p.exec("update code set kennung='Autoimport4',aic_begriffgruppe="+iSave+" where aic_begriffgruppe="+iRes+" and kennung='reserve82'");
                  p.exec("update code set kennung='add holiday' where aic_begriffgruppe="+iRes+" and kennung='reserve83'");
                  p.exec("update code set kennung='clear holidays' where aic_begriffgruppe="+iRes+" and kennung='reserve84'");
                  p.exec("update code set kennung='prev n years',aic_begriffgruppe="+iDate+" where aic_begriffgruppe="+iRes+" and kennung='reserve85'");
                  p.exec("update code set kennung='bew new save' where aic_begriffgruppe="+iRes+" and kennung='reserve86'");
                  p.exec("update code set kennung='get computer' where aic_begriffgruppe="+iRes+" and kennung='reserve87'");
                  p.exec("update code set kennung='exec file' where aic_begriffgruppe="+iRes+" and kennung='reserve88'");
                  p.exec("update code set kennung='SQL_getAic_Bind',aic_begriffgruppe="+iSQL+" where aic_begriffgruppe="+iRes+" and kennung='reserve89'");
                  p.exec("update code set kennung='replace wildcard' where aic_begriffgruppe="+iRes+" and kennung='reserve90'");
                  p.exec("update code set kennung='kill space' where aic_begriffgruppe="+iRes+" and kennung='reserve91'");
                  p.exec("update code set kennung='base64',aic_begriffgruppe="+iMail+" where aic_begriffgruppe="+iRes+" and kennung='reserve92'");
                  for (int i=100;i<120;i++)
                      b = b && addCode(iRes, "reserve"+i) > 0;
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  p.exec("update code set kennung='is Frame xx' where aic_begriffgruppe="+iBed+" and kennung='Bedingung31'");
                  p.exec("update code set kennung='pos iqnore Case' where aic_begriffgruppe="+iBed+" and kennung='Bedingung32'");
                  p.exec("update code set kennung='empty stack' where aic_begriffgruppe="+iBed+" and kennung='Bedingung33'");
                  p.exec("update code set kennung='exists' where aic_begriffgruppe="+iBed+" and kennung='Bedingung34'");
                  p.exec("update code set kennung='check IBAN' where aic_begriffgruppe="+iBed+" and kennung='Bedingung35'");
                  p.exec("update code set kennung='startsWith' where aic_begriffgruppe="+iBed+" and kennung='Bedingung36'");
                  for (int i=40;i<50;i++)
                      b = b && addCode(iBed, "Bedingung"+i) > 0;
                  iIst = b ? setVer(5,10,4):iIst;
                }
                if(b && iIst < Ver(5,10,5) && iSoll>=Ver(5,10,5))	// 13.11. 2014
                {
                  g.diskInfo("Versionsupdate 5.10.5");
                  if (g.MySQL())
                  {
                    g.bISQL = true;
                    g.exec("alter table BEDINGUNG drop foreign key FK_BEDINGUN_REF_593_BEDINGUN");
                    g.exec("alter table BEFEHL drop foreign key FK_BEFEHL_REF_608_BEFEHL");
                    g.exec("alter table BEFEHL drop foreign key FK_BEFEHL_REF_611_BEFEHL");
                    g.exec("alter table DARSTELLUNG drop foreign key FK_DARSTELL_REF_767_DARSTELL");
                    g.bISQL = false;
                  }

                  iIst = b ? setVer(5,10,5):iIst;
                }
                gauge.setText("V 5.12 - Erweiterungen",130);
                if(b && iIst < Ver(5,11,1) && iSoll>=Ver(5,11,1))	// 31. 10. 2014
                {
                  g.diskInfo("Versionsupdate 5.11.1");
                  if (g.MySQL())
                  {
                    b = b && p.exec("create table Daten_Bild2 ("+
                                    "AIC_Daten int not null PRIMARY KEY,"+
                                    "filename varchar(255) not null,"+
                                    "Bild2 MEDIUMBLOB NOT NULL)");
                    b = b && p.exec("create table Daten_Doku ("+
                                    "AIC_Daten int not null PRIMARY KEY,"+
                                    "filename varchar(255) not null,"+
                                    "doku MEDIUMBLOB NOT NULL)");
                  }
                  else if (g.MS())
                  {
                    b = b && p.exec("create table Daten_Bild2 (" +
                                    "AIC_Daten int not null," +
                                    "filename varchar(255) not null," +
                                    "Bild2 IMAGE NOT NULL," +
                                    "primary key (AIC_Daten))");
                    b = b && p.exec("create table Daten_Doku (" +
                                   "AIC_Daten int not null," +
                                   "filename varchar(255) not null," +
                                   "doku varbinary(max) NOT NULL," +
                                   "primary key (AIC_Daten))");

                  }
                  else if (g.Oracle())
                  {
                      b = b && p.exec("create table Daten_Bild2 (" +
                                      "AIC_Daten NUMBER(10) not null," +
                                      "filename varchar(255) not null," +
                                      "Bild2 BLOB NOT NULL," +
                                      "constraint PK_Daten_Bild2 primary key (AIC_Daten))");
                      b = b && p.exec("create table Daten_Doku (" +
                                     "AIC_Daten NUMBER(10) not null," +
                                     "filename varchar(255) not null," +
                                     "doku BLOB NOT NULL," +
                                     "constraint PK_Daten_Doku primary key (AIC_Daten))");
                  }
                  int iDT = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  if (b && iDT>0)
                  {
                    addBegriff(iDT, "Bild2");
                    addBegriff(iDT, "Doku");
                  }
                  if (g.MS())
                    b = b && p.exec("ALTER TABLE DATEN_TEXT ALTER COLUMN SPALTE_TEXT varchar(max)");
                  else if (g.MySQL())
                    b = b && p.exec("alter table DATEN_TEXT MODIFY SPALTE_TEXT varchar(30000)"+(g.Oracle()?"":" null"));
                  iIst = b ? setVer(5,11,1):iIst;
                }
                if(b && iIst < Ver(5,11,2) && iSoll>=Ver(5,11,2))	// 23. 4. 2015
                {
                  g.diskInfo("Versionsupdate 5.11.2");
                  // BewDatum2-Redundanz zur Beschleunigung z.B. für Timersätze
                  //b = b && addIntSpalte("bew_pool","LDATE2");
                  //b = b && addIntSpalte("bew_pool","LDATE3");
                  String sInt=g.ASA() ? "integer":g.Oracle() ? "NUMBER(10)":"int";
                  b = b && p.exec("alter table bew_pool add LDATE2 " + sInt + " null"+(g.Oracle() ? " ":",")+(g.MS() ? "":"add ")+"LDATE3 " + sInt + " null");
                  b = b && addIntSpalte("bewegungstyp","Datum_Eig2","aic_eigenschaft","Eigenschaft");
                  b = b && addIntSpalte("bewegungstyp","Datum_Eig3","aic_eigenschaft","Eigenschaft");
                  /*if (b)
                  {
                    Vector VecBew=SQL.getVector("select aic_bewegungstyp from bewegungstyp where "+g.bit("bewBits",1),g); // Global.cstTimer
                    int iEig=SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='Abgearbeitet'");
                    p.exec("update bewegungstyp set Datum_Eig2="+iEig+" where aic_bewegungstyp"+Static.SQL_in(VecBew));
                    p.exec("update bew_pool set LDATE2=(select "+(g.Oracle()?"to_number(to_char(bew_von_bis.von,'YYYYMMDD')":"year(bew_von_bis.von)*10000+month(bew_von_bis.von)*100+day(bew_von_bis.von)")+
                           " from bew_von_bis where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" +iEig +") where aic_bewegungstyp"+Static.SQL_in(VecBew));
                    p.exec("update bew_pool set LDATE2=0 where LDATE2 is null and aic_bewegungstyp"+Static.SQL_in(VecBew));
                  }*/
                  b = b && p.exec("create index BEW_LDATE2 on BEW_POOL (AIC_BEWEGUNGSTYP,LDATE2 asc)");
                  b = b && p.exec("create index BEW_LDATE3 on BEW_POOL (AIC_BEWEGUNGSTYP,LDATE3 asc)");

                  // Relation ANR zu Stamm z.B. für MA in Reise
                  b = b && addIntSpalte("stamm_protokoll","ANR","aic_stamm","Stamm");
                  b = b && addIntSpalte("stammtyp","ANR_Eig","aic_eigenschaft","Eigenschaft");
                  if (b)
                  {
                    int iEig=SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='MA relation'");
                    int iStt=SQL.getInteger(g,"select aic_stammtyp from stammtyp where kennung='RK_travel'");
                    p.exec("update stammtyp set ANR_Eig="+iEig+" where aic_stammtyp="+iStt);
                    p.exec("update stamm_protokoll set ANR=(select sta_aic_stamm from poolview2 where stamm_protokoll.aic_stamm=aic_stamm and aic_eigenschaft="+iEig+
                           ") where pro_aic_protokoll is null and aic_stamm in (select aic_stamm from stamm where aic_stammtyp="+iStt+")");
                  }

                  iIst = b ? setVer(5,11,2):iIst;
                }
                if(b && iIst < Ver(5,11,3) && iSoll>=Ver(5,11,3))	// 21. 5. 2015
                {
                  g.diskInfo("Versionsupdate 5.11.3");
                  if (g.ASA())
                  {
                    b = b && p.exec("create table AUSWAHL(" +
                                    "AIC_AUSWAHL integer not null default AUTOINCREMENT," +
                                    "KENNUNG char(20) null,"+
                                    "AIC_EIGENSCHAFT integer not null,"+
                                    "NR integer not null,"+
                                    "primary key (AIC_AUSWAHL))");
                    b=b && p.exec("alter table AUSWAHL add foreign key FK_AUSWAHL_EIGENSCHAFT (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT) on update restrict on delete restrict");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("create table AUSWAHL(" +
                                    "AIC_AUSWAHL NUMBER(10) not null," +
                                    "KENNUNG varchar(20) null,"+
                                    "AIC_EIGENSCHAFT NUMBER(10) not null,"+
                                    "NR NUMBER(10) not null,"+
                                    "constraint PK_AUSWAHL primary key (AIC_AUSWAHL))");
                    p.createAllSequences();
                    b = b && p.exec("alter table AUSWAHL add constraint FK_AUSWAHL_EIGENSCHAFT foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                  }
                  else if (g.MS() || g.MySQL())
                  {
                    b = b && p.exec("create table AUSWAHL(" +
                                    "AIC_AUSWAHL int not null "+(g.MS()?"identity,":"AUTO_INCREMENT,")+
                                    "KENNUNG varchar(20) null,"+
                                    "AIC_EIGENSCHAFT int not null,"+
                                    "NR int not null,"+
                                    "primary key (AIC_AUSWAHL))");
                    if (g.MySQL())
                      b=b && p.exec("alter table AUSWAHL engine=innodb");
                    b = b && p.exec("alter table AUSWAHL add constraint FK_AUSWAHL_EIGENSCHAFT foreign key (AIC_EIGENSCHAFT) references EIGENSCHAFT (AIC_EIGENSCHAFT)");
                  }
                  if (b)
                  {
                    p.add("Kennung", "AUSWAHL");
                    b=p.insert("tabellenname", true)>0;
                  }
                  int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  if (b && iGruppe>0)
                  {
                          addBegriff(iGruppe,"Bool3");
                          addBegriff(iGruppe,"BewBits");
                  }

                  iIst = b ? setVer(5,11,3):iIst;
                }
                if(b && iIst < Ver(5,11,4) && iSoll>=Ver(5,11,4))	// 3. 6. 2015
                {
                  g.diskInfo("Versionsupdate 5.11.4");
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b = b && p.exec("update code set kennung='calc on AServer' where aic_begriffgruppe="+iBed+" and kennung='Bedingung37'");
                  b = b && addCode(iBed, "= Bool3") > 0;
                  b = b && addCode(iBed, "pos Bool3") > 0;

                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  b = b && p.exec("update code set kennung='set Timeout' where aic_begriffgruppe="+iRes+" and kennung='reserve93'");
                  b = b && p.exec("update code set kennung='set Stammtitel' where aic_begriffgruppe="+iRes+" and kennung='reserve94'");
                  int iSave = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                  int iGet = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                  int iInit = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='init'");
                  b = b && addCode(iInit, "init Bool3") > 0;
                  b = b && addCode(iGet, "get Bool3") > 0;
                  b = b && addCode(iSave, "save Bool3") > 0;
                  b = b && addCode(iSave, "remember Bool3") > 0;
                  iIst = b ? setVer(5,11,4):iIst;
                }
                if(b && iIst < Ver(5,11,5) && iSoll>=Ver(5,11,5))	// 11. 6. 2015
                {
                  g.diskInfo("Versionsupdate 5.11.5");
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b = b && addCode(iBed, "pos next Bool3") > 0;

                  iIst = b ? setVer(5,11,5):iIst;
                }
                if(b && iIst < Ver(5,11,6) && iSoll>=Ver(5,11,6))	// 23. 7. 2015
                {
                  g.diskInfo("Versionsupdate 5.11.6");
                  b = b && addIntSpalte("mandant","AIC_LOGGING","LOGGING");
                  b = b && addBitSpalte("mandant","Tod");
                  iIst = b ? setVer(5,11,6):iIst;
                }
                if(b && iIst < Ver(5,11,7) && iSoll>=Ver(5,11,7))	// 28. 7. 2015
                {
                  g.diskInfo("Versionsupdate 5.11.7");
                  int cstDialog=0x04000000;
                  p.exec("update spalte set bits=bits-"+cstDialog+" where"+g.bit("bits",cstDialog));
                  b = b && addIntSpalte("Logging","Status");
                  iIst = b ? setVer(5,11,7):iIst;
                }
                if(b && iIst < Ver(5,11,8) && iSoll>=Ver(5,11,8))	// 21. 8. 2015
                {
                  g.diskInfo("Versionsupdate 5.11.8");
                  //b = b && addIntSpalte("bew_pool","BOOL1");
                  //b = b && addIntSpalte("bew_pool","BOOL2");
                  String sInt=g.ASA() ? "integer":g.Oracle() ? "NUMBER(10)":"int";
                  b = b && p.exec("alter table bew_pool add BOOL1 " + sInt + " null"+(g.Oracle() ? " ":",")+(g.MS() ? "":"add ")+"BOOL2 " + sInt + " null");
                  b = b && addIntSpalte("bewegungstyp","Bool_Eig1","aic_eigenschaft","Eigenschaft");
                  b = b && addIntSpalte("bewegungstyp","Bool_Eig2","aic_eigenschaft","Eigenschaft");
                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");
                  int iDt = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  if (b && iDt>0)
                    addBegriff(iDt,"Protokoll");

                  if (g.ASA() || g.Oracle() || g.MySQL())
                    b = b && p.exec("alter table PARAMETER MODIFY Parameterzeile varchar(255)"+(g.Oracle()?"":" null"));
                  else
                    b = b && p.exec("ALTER TABLE PARAMETER ALTER COLUMN Parameterzeile varchar(255)");

                  iIst = b ? setVer(5,11,8):iIst;
                }
                if(b && iIst < Ver(5,11,9) && iSoll>=Ver(5,11,9))	// 1. 9. 2015
                {
                  g.diskInfo("Versionsupdate 5.11.9");

                  int iBG_Dt = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                  int iDt=p.getInteger("select aic_begriff from begriff where aic_begriffgruppe="+iBG_Dt+" and kennung='Memo'");
                  g.fixtestInfo("Datentyp Memo="+iDt);
                  //int iDt=g.getBegriffAicS("Datentyp","Text");
                  //if (!g.Oracle()) // bei LK geht es nicht -> muss da ausgeklammert sein
                  {
                   Tabellenspeicher TabE = new Tabellenspeicher(g,"select kennung,aic_eigenschaft,(select count(daten_stringx.aic_daten) from daten_stringx join stammpool on daten_stringx.aic_daten=stammpool.aic_daten where aic_eigenschaft=e.aic_eigenschaft) Anzahl from eigenschaft e where aic_begriff=" + iDt, true);
                   for (TabE.moveFirst(); !TabE.out(); TabE.moveNext())
                    if (TabE.getI("Anzahl") > 0)
                    {
                      int iEig=TabE.getI("aic_eigenschaft");
                      Vector<Integer> Vec = SQL.getVector("select aic_daten from stammpool where aic_eigenschaft=" + iEig, g);
                      Tabellenspeicher Tab = new Tabellenspeicher(g, "select * from daten_stringx where" + g.in("aic_daten", Vec), true);
                      SQL Qry = new SQL(g);
                      int iAnz = 0;
                      Vector<Integer> VecDa = SQL.getVector("select aic_daten from daten_text", g);
                      for (Tab.moveFirst(); !Tab.out(); Tab.moveNext())
                      {
                        int iD = Tab.getI("aic_daten");
                        if (!VecDa.contains(iD))
                        {
                          Qry.add("AIC_Daten", iD);
                          Qry.add("spalte_text", Tab.getM("spalte_stringx"));
                          if (Qry.insert("daten_text", false) == 0)
                            iAnz += 1; // g.exec("delete from daten_stringx where aic_daten=" + iD);
                        }
                      }
                      Qry.free();
                      if (iAnz > 0)
                        g.diskInfo(TabE.getS("Kennung") + "(" + iEig + "): " + iAnz + " auf Text umkopiert");
                    }
                  }
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  p.exec("update code set kennung='generate PW' where aic_begriffgruppe="+iRes+" and kennung='reserve95'");
                  p.exec("update code set kennung='get filesize' where aic_begriffgruppe="+iRes+" and kennung='reserve96'");
                  p.exec("update code set kennung='get filedate' where aic_begriffgruppe="+iRes+" and kennung='reserve97'");
                  p.exec("update code set kennung='Tab message' where aic_begriffgruppe="+iRes+" and kennung='reserve98'");
                  p.exec("update code set kennung='get Prot' where aic_begriffgruppe="+iRes+" and kennung='reserve99'");
                  p.exec("update code set kennung='Prot to Date' where aic_begriffgruppe="+iRes+" and kennung='reserve100'");
                  p.exec("update code set kennung='get single motion' where aic_begriffgruppe="+iRes+" and kennung='reserve101'");
                  p.exec("update code set kennung='get Pos' where aic_begriffgruppe="+iRes+" and kennung='reserve102'");
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  p.exec("update code set kennung='is last row' where aic_begriffgruppe="+iBed+" and kennung='Bedingung38'");
                  p.exec("update code set kennung='Zeilen=' where aic_begriffgruppe="+iBed+" and kennung='Bedingung39'");
                  p.exec("update code set kennung='Pos=' where aic_begriffgruppe="+iBed+" and kennung='Bedingung40'");
                  p.exec("update code set kennung='set Pos' where aic_begriffgruppe="+iBed+" and kennung='Bedingung41'");
                  for (int i=50;i<60;i++)
                      b = b && addCode(iBed, "Bedingung"+i) > 0;
                  iIst = b ? setVer(5,11,9):iIst;
                }
                if(b && iIst < Ver(5,12,0) && iSoll>=Ver(5,12,0))	// 27. 06. 2016
                {
                  g.diskInfo("Versionsupdate 5.12.0");
                  g.diskInfo(g.exec("DELETE FROM Zwischenspeicher WHERE Kennung='StammUpload' and aic_mandant is null")+" alte StammUploads gelöscht");
                  g.diskInfo(g.exec("DELETE FROM Zwischenspeicher WHERE Kennung='ZeitZutritt' and aic_mandant is null")+" alte ZeitZutritt gelöscht");
                  g.diskInfo(g.exec("DELETE FROM Zwischenspeicher WHERE Kennung='Feiertag' and aic_mandant is null")+" alte Feiertage gelöscht");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                      g.exec("alter table ZWISCHENSPEICHER MODIFY TERMINAL varchar(40)"+(g.Oracle()?"":" null"));
                    else
                      g.exec("ALTER TABLE ZWISCHENSPEICHER ALTER COLUMN TERMINAL varchar(40)");
                  iIst = b ? setVer(5,12,0):iIst;
                }
                gauge.setText("V 5.13 - Erweiterungen",140);
                if(b && iIst < Ver(5,12,1) && iSoll>=Ver(5,12,1))	// 29. 04. 2016
                {
                  g.diskInfo("Versionsupdate 5.12.1");
                  p.add("Kennung","PaneFX");
                  int iPane = p.insert("begriffgruppe",true);
                  b=b && iPane>0 && addBegriff(iPane,"Accordion")>0;
                  b=b && iPane>0 && addBegriff(iPane,"AnchorPane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"BorderPane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"ButtonBar")>0;
                  b=b && iPane>0 && addBegriff(iPane,"FlowPane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"GridPane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"HBox")>0;
                  b=b && iPane>0 && addBegriff(iPane,"ScrollPane-FX")>0;
                  b=b && iPane>0 && addBegriff(iPane,"SplitPaneH-FX")>0;
                  b=b && iPane>0 && addBegriff(iPane,"SplitPaneV-FX")>0;
                  b=b && iPane>0 && addBegriff(iPane,"StackPane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"TabPane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"TilePane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"TitledPane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"ToolBar-FX")>0;
                  b=b && iPane>0 && addBegriff(iPane,"VBox")>0;
                  iIst = b ? setVer(5,12,1):iIst;
                }
                if(b && iIst < Ver(5,12,2) && iSoll>=Ver(5,12,2))	// 3. 5. 2016
                {
                  g.diskInfo("Versionsupdate 5.12.2");
                  if (!g.Oracle())
                	  p.exec("alter table DARSTELLUNG drop"+(g.ASA()?"":" column")+" aic_popup");
                  b=b && addIntSpalte("DARSTELLUNG","ALIGN");
                  p.add("Kennung","AlignFX");
                  p.add("code",1);
                  int iAlign = p.insert("begriffgruppe",true);
                  String sV[] = new String[] {"TOP","CENTER","BOTTOM","BASELINE"};
                  String sH[] = new String[] {"LEFT","CENTER","RIGHT"};
                  for (int iV=0;iV<sV.length;iV++)
                	  for (int iH=0;iH<sH.length;iH++)
                	  {
                		  String s=sV[iV].equals(sH[iH]) ? sV[iV]:sV[iV]+"_"+sH[iH];
                		  b=b && iAlign>0 && addCode(iAlign,s)>0;
                	  }
                  p.add("Kennung","ParameterFX");
                  int iPara = p.insert("begriffgruppe",true);
                  b=b && iPara>0 && addBegriff(iPara,"MinSize")>0;
                  b=b && iPara>0 && addBegriff(iPara,"MaxSize")>0;
                  //b=b && iPara>0 && addBegriff(iPara,"Gap")>0;
                  iIst = b ? setVer(5,12,2):iIst;
                  
                }
                if(b && iIst < Ver(5,12,3) && iSoll>=Ver(5,12,3))	// 11. 5. 2016
                {
                  g.diskInfo("Versionsupdate 5.12.3");
                  b = b && p.exec("alter table FORMULAR ADD FXML varchar(255) null");
                  int iPara=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='ParameterFX'");
                  b=b && iPara>0 && addBegriff(iPara,"margin")>0;
                  b=b && iPara>0 && addBegriff(iPara,"padding")>0;
                  b=b && iPara>0 && addBegriff(iPara,"background")>0;
                  b=b && iPara>0 && addBegriff(iPara,"textfill")>0;
                  p.add("Kennung","ControllFX");
                  int iCntr = p.insert("begriffgruppe",true);
                  b=b && iCntr>0 && addBegriff(iCntr,"TreeTableView")>0;
                  b=b && iCntr>0 && addBegriff(iCntr,"Pagination")>0;
                  iIst = b ? setVer(5,12,3):iIst;
                }
                if(b && iIst < Ver(5,12,4) && iSoll>=Ver(5,12,4))	// 25. 5. 2016
                {
                  g.diskInfo("Versionsupdate 5.12.4");
                  b = b && p.exec("alter table BEGRIFF ADD KENNZEICHEN varchar(40) null");
                  b = b && addLongSpalte("Abfrage","ABITS2");
                  b = b && addIntSpalte("Spalte","BITS3");
                  int iPane=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='PaneFX'");
                  b=b && iPane>0 && addBegriff(iPane,"Pane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"DialogPane")>0;
                  b=b && iPane>0 && addBegriff(iPane,"TextFlow")>0;
                  b=b && iPane>0 && addBegriff(iPane,"MasterDetailPane")>0;
                  int iCntr=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='ControllFX'");
                  b=b && iCntr>0 && addBegriff(iCntr,"MenuBar")>0;
                  b=b && iCntr>0 && addBegriff(iCntr,"MenuButton")>0;
                  b=b && iCntr>0 && addBegriff(iCntr,"Separator")>0;
                  iIst = b ? setVer(5,12,4):iIst;
                }
                if(b && iIst < Ver(5,12,5) && iSoll>=Ver(5,12,5))	// 29. 8. 2016
                {
                  g.diskInfo("Versionsupdate 5.12.5");
                  b = b && p.exec("alter table STAMM_PROTOKOLL ADD AIC_STAMMTYP2 int null");
                  b = b && p.exec("alter table STAMM_PROTOKOLL ADD KENNUNG2 varchar(40) null");
                  b = b && p.exec("alter table STAMM_PROTOKOLL ADD STA_AIC_STAMM2 int null");
                  b = b && p.exec("update stamm_protokoll set AIC_STAMMTYP2=(select aic_stammtyp from stamm where aic_stamm=stamm_protokoll.aic_stamm)");
                  b = b && p.exec("update stamm_protokoll set KENNUNG2=(select kennung from stamm where aic_stamm=stamm_protokoll.aic_stamm)");
                  b = b && p.exec("update stamm_protokoll set STA_AIC_STAMM2=(select STA_AIC_STAMM from stamm where aic_stamm=stamm_protokoll.aic_stamm)");
                  p.exec("create index Ref_SP_STT2_FK on STAMM_PROTOKOLL (AIC_STAMMTYP2)");
                  p.exec("alter table stamm drop"+(g.ASA()?"":" COLUMN")+" kennung");
                  if (!g.Oracle())
                	  p.exec("alter table stamm drop "+(g.ASA() || g.MySQL() ? "foreign key":"constraint")+" FK_STAMM_STAMM");
                  p.exec("alter table stamm drop"+(g.ASA()?"":" COLUMN")+" sta_aic_stamm");
                  
                  p.add("Kennung","Rahmen");
                  p.insert("begriffgruppe",true);
                  iIst = b ? setVer(5,12,5):iIst;
                }
                if(b && iIst < Ver(5,12,6) && iSoll>=Ver(5,12,6))	// 21.11. 2016
                {
                  g.diskInfo("Versionsupdate 5.12.6");
                  // Darstellung: + HGap (=spacing), VGap, Pos, i1, i2
                  b = b && addIntSpalte("Darstellung","HGap");
                  b = b && addIntSpalte("Darstellung","VGap");
                  b = b && addIntSpalte("Darstellung","Pos");
                  b = b && addIntSpalte("Darstellung","i1");
                  b = b && addIntSpalte("Darstellung","i2");
                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");
                  // PaneFX: + TabPaneV, ToolBarV-FX, ToggleBtn, Breadcrumb
                  int iPane=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='PaneFX'");
                  b=b && iPane>0 && addBegriff(iPane,"TabPaneV")>0;
                  b=b && iPane>0 && addBegriff(iPane,"ToolBarV-FX")>0;
                  int iCntr=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='ControllFX'");
                  b=b && iCntr>0 && addBegriff(iCntr,"SegmentedButton")>0;
                  // Spalte: + Style, x, y, w, h
                  b = b && p.exec("alter table Spalte ADD STIL varchar(30) null");
                  b = b && addIntSpalte("Spalte","x");
                  p.exec("update spalte set x=min where min is not null and min<>0");
                  p.exec("update spalte set x=null where min<>x");
                  p.exec("update spalte set min=null where x is not null");
                  b = b && addIntSpalte("Spalte","y");
                  p.exec("update spalte set y=max where max is not null and max<>0");
                  p.exec("update spalte set y=null where max<>y");
                  p.exec("update spalte set max=null where y is not null");
                  b = b && addIntSpalte("Spalte","w");
                  b = b && addIntSpalte("Spalte","h");
                  // Formulartyp: + Menu
                  int iFT=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Formulartyp'");
                  b=b && iFT>0 && addCode(iFT,"Menu")>0;
                  iIst = b ? setVer(5,12,6):iIst;
                }
                if(b && iIst < Ver(5,12,7) && iSoll>=Ver(5,12,7))	// 15.12. 2016
                {
                  g.diskInfo("Versionsupdate 5.12.7");
                  // Favoriten-Tabelle
                  if (g.MS() || g.MySQL())
                  {
                    b = b && p.exec("create table FAVORIT(" +
                                    "AIC_FAVORIT int not null "+(g.MS()?"identity,":"AUTO_INCREMENT,")+
                                    "AIC_STAMM int null,"+
                                    "AIC_BEGRIFF int null,"+
                                    "STA_AIC_STAMM int null,"+
                                    "AIC_BENUTZER int null,"+
                                    "AIC_BENUTZERGRUPPE int null,"+
                                    "AIC_PROTOKOLL int not null,"+
                                    "STATUS int not null,"+
                                    "primary key (AIC_FAVORIT))");
                    if (g.MySQL())
                      b=b && p.exec("alter table FAVORIT engine=innodb");
                    b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                    b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                    b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_ZU_STAMM foreign key (STA_AIC_STAMM) references STAMM (AIC_STAMM)");
                    b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_ZU_BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                    b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_ZU_BENUTZERGRUPPE foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                    b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_PROTOKOLL foreign key (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL)");
                  }
                  else if (g.Oracle())
                  {
                	  b = b && p.exec("create table FAVORIT(" +
                              "AIC_FAVORIT NUMBER(10) not null,"+
                              "AIC_STAMM NUMBER(10) null,"+
                              "AIC_BEGRIFF NUMBER(10) null,"+
                              "STA_AIC_STAMM NUMBER(10) null,"+
                              "AIC_BENUTZER NUMBER(10) null,"+
                              "AIC_BENUTZERGRUPPE NUMBER(10) null,"+
                              "AIC_PROTOKOLL NUMBER(10) not null,"+
                              "STATUS NUMBER(10) not null,"+
                              "constraint PK_FAVORIT primary key (AIC_FAVORIT))");
                	  p.createAllSequences();
                	  b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_STAMM foreign key (AIC_STAMM) references STAMM (AIC_STAMM)");
                      b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_BEGRIFF foreign key (AIC_BEGRIFF) references BEGRIFF (AIC_BEGRIFF)");
                      b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_ZU_STAMM foreign key (STA_AIC_STAMM) references STAMM (AIC_STAMM)");
                      b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_ZU_BENUTZER foreign key (AIC_BENUTZER) references BENUTZER (AIC_BENUTZER)");
                      b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_ZU_BENUTZERGRUPPE foreign key (AIC_BENUTZERGRUPPE) references BENUTZERGRUPPE (AIC_BENUTZERGRUPPE)");
                      b = b && p.exec("alter table FAVORIT add constraint FK_FAVORIT_PROTOKOLL foreign key (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL)");
                  }
                  // Memo-Header
                  b = b && p.exec("alter table DATEN_MEMO add HEADER varchar(255) null");
                  // Modellbefehle umbenennen
                  b=b && p.exec("update code set kennung='write Temp' where kennung='reserve103'");
                  b=b && p.exec("update code set kennung='set date' where kennung='reserve104'");
                  b=b && p.exec("update code set kennung='print AServer' where kennung='reserve105'");
                  b=b && p.exec("update code set kennung='get Begriff-Kennung' where kennung='reserve106'");
                  b=b && p.exec("update code set kennung='no title' where kennung='reserve107'");
                  b=b && p.exec("update code set kennung='reconnect' where kennung='reserve108'");
                  b=b && p.exec("update code set kennung='add path' where kennung='reserve109'");
                  b=b && p.exec("update code set kennung='indexOf' where kennung='reserve110'");
                  b=b && p.exec("update code set kennung='get Timer-Mandanten' where kennung='reserve111'");
                  b=b && p.exec("update code set kennung='add space-number' where kennung='reserve112'");
                  b=b && p.exec("update code set kennung='add Begriff-Title' where kennung='reserve113'");
                  b=b && p.exec("update code set kennung='set Global' where kennung='reserve114'");
                  b=b && p.exec("update code set kennung='get Global' where kennung='reserve115'");
                  b=b && p.exec("update code set kennung='exec IntusCom' where kennung='reserve116'");
                  b=b && p.exec("update code set kennung='open Stage' where kennung='reserve117'");
                  b=b && p.exec("update code set kennung='next Mandant' where kennung='Bedingung42'");
                  // neue Reserve-Befehle
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  for (int i=120;i<140;i++)
                      b = b && addCode(iRes, "reserve"+i) > 0;
                  iIst = b ? setVer(5,12,7):iIst;
                }
                if(b && iIst < Ver(5,12,8) && iSoll>=Ver(5,12,8))	// 30.1. 2017
                {
                  g.diskInfo("Versionsupdate 5.12.8");
                  // Alias in Begriff
                  b = b && p.exec("alter table BEGRIFF ADD ALIAS varchar(40) null");
                  // Reserve-Panes
                  int iPane=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='PaneFX'");
                  for(int i=1;i<6;i++)
                    b=b && iPane>0 && addBegriff(iPane,"Pane"+i)>0;
                  int iPara=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='ParameterFX'");
                  for(int i=1;i<6;i++)
                    b=b && iPara>0 && addBegriff(iPara,"Para"+i)>0;
                  int iContr=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='ControllFX'");
                  for(int i=1;i<6;i++)
                    b=b && iContr>0 && addBegriff(iContr,"Ctrl"+i)>0;
                  iIst = b ? setVer(5,12,8):iIst;
                }
                if(b && iIst < Ver(5,12,9) && iSoll>=Ver(5,12,9))	// 6.2. 2017
                {
                  g.diskInfo("Versionsupdate 5.12.9");
                  // Sperre in Bew-Poool
                  addIntSpalte("Bew_Pool","bitsBew");
                  addIntSpalte("Bew_Pool","BewSperre","AIC_LOGGING","LOGGING");
                  // weitere Abfrage-Befehle
                  int iOP=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                  b = b && addCode(iOP,"use unit") > 0;
                  for (int i=6;i<11;i++)
                      b = b && addCode(iOP, "reserveOP"+i) > 0;
                  b=b && p.exec("update code set kennung='string_left' where aic_begriffgruppe="+iOP+" and kennung='reserveOP1'");
                  b=b && p.exec("update code set kennung='trunc_left' where aic_begriffgruppe="+iOP+" and kennung='reserveOP2'");
                  iIst = b ? setVer(5,12,9):iIst;
                }
                gauge.setText("V 5.14 - Erweiterungen",150);
                if(b && iIst < Ver(5,13,1) && iSoll>=Ver(5,13,1))	// 12.3. 2018
                {
                  g.diskInfo("Versionsupdate 5.13.1");
                  // Verlauf erweitern
                  addIntSpalte("Verlauf","AIC_ROLLE","AIC_ROLLE","ROLLE");
                  b = b && p.exec("alter table Verlauf ADD Bemerkung varchar(255) null");
                  // Benutzer mit Host erweitern
                  if (g.MySQL())
                  {
                	  b = b && p.exec("alter table Benutzer ADD Host varchar(60) null");
                	  p.exec("update benutzer set Host='%'");
                	  p.exec("update benutzer set Host='localhost' where"+g.bits("bits", Global.cstAServerUser+Global.cstTerminalUser+Global.cstTimerBenutzer)+">0");
                  }
                  // Datentypen erweitern
                  int iDt = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
      			  if (b && iDt>0)
      			  {
      				addBegriff(iDt,"Favorit");
      				addBegriff(iDt,"Formular");
      			  }
      			  addIntSpalte("Spalte","BEG_AIC_BEGRIFF","AIC_BEGRIFF","BEGRIFF");
      			  // Tabelle Verboten
      			  if (g.MS() || g.MySQL())
      			  {
      				  b = b && p.exec("create table VERBOTEN (" +
      						  			"AIC_MANDANT int not null,"+
      						  			"AIC_TABELLENNAME int not null,"+
      						  			"AIC_FREMD int not null,"+
      						  			"AIC_PROTOKOLL int null,"+
                                  		"primary key (AIC_MANDANT,AIC_TABELLENNAME, AIC_FREMD))");
      				  if (g.MySQL())
                        b=b && p.exec("alter table VERBOTEN engine=innodb");
      				b = b && p.exec("alter table VERBOTEN add constraint FK_VERBOTEN_MANDANT foreign key (AIC_MANDANT) references MANDANT (AIC_MANDANT)");
      				b = b && p.exec("alter table VERBOTEN add constraint FK_VERBOTEN_TAB foreign key (AIC_TABELLENNAME) references TABELLENNAME (AIC_TABELLENNAME)");
      				b = b && p.exec("alter table VERBOTEN add constraint FK_VERBOTEN_PROT foreign key (AIC_PROTOKOLL) references PROTOKOLL (AIC_PROTOKOLL)");
      			  }
      			  else if (g.Oracle())
      			  {
      				b = b && p.exec("create table VERBOTEN(" +
                            "AIC_MANDANT NUMBER(10) not null,"+
                            "AIC_TABELLENNAME NUMBER(10) not null,"+
                            "AIC_FREMD NUMBER(10) not null,"+
                            "AIC_PROTOKOLL NUMBER(10) not null,"+
      						"constraint PK_VERBOTEN primary key (AIC_MANDANT,AIC_TABELLENNAME, AIC_FREMD))");
      			  }
      				  
                  iIst = b ? setVer(5,13,1):iIst;
                }
                if(b && iIst < Ver(5,13,2) && iSoll>=Ver(5,13,2))	// 15.3. 2018
                {
                  g.diskInfo("Versionsupdate 5.13.2");
                  // Tabelle Zustand erzeugen
                  if (g.ASA())
                    b = b && p.exec("create table ZUSTAND(" +
                                    "AIC_ZUSTAND integer not null default AUTOINCREMENT," +
                                    "KENNUNG char(20) null,"+
                                    "primary key (AIC_ZUSTAND))");
                  else if (g.Oracle())
                  {
                    b = b && p.exec("create table ZUSTAND(" +
                                    "AIC_ZUSTAND NUMBER(10) not null," +
                                    "KENNUNG varchar(20) null,"+
                                    "constraint PK_ZUSTAND primary key (AIC_ZUSTAND))");
                    p.createAllSequences();
                  }
                  else if (g.MS() || g.MySQL())
                  {
                    b = b && p.exec("create table ZUSTAND(" +
                                    "AIC_ZUSTAND int not null "+(g.MS()?"identity,":"AUTO_INCREMENT,")+
                                    "KENNUNG varchar(20) null,"+
                                    "primary key (AIC_ZUSTAND))");
                    if (g.MySQL())
                      b=b && p.exec("alter table ZUSTAND engine=innodb");
                  }
                  if (b)
                  {
                    p.add("Kennung", "ZUSTAND");
                    b=p.insert("tabellenname", true)>0;
                  }
                  // neue Zustände erzeugen
                  p.add("Kennung","Swing");
                  int iSw = p.insert("ZUSTAND",true);
                  p.add("Kennung","JavaFX");
                  int iFX = p.insert("ZUSTAND",true);
                  p.add("Kennung","Selected");
                  int iSel = p.insert("ZUSTAND",true);
                  b=b && iSw>0 && iFX>0 && iSel>0;
                  // Daten_Bild-Verknüpgung
                  addIntSpalte("Daten_Bild","AIC_Zustand","AIC_Zustand","ZUSTAND");
                  p.exec("update Daten_Bild set aic_Zustand="+iSw+" where aic_sprache=1");
                  p.exec("update Daten_Bild set aic_Zustand="+iFX+" where aic_sprache=2");
                  int iAnz=g.exec("delete from daten_bild where aic_zustand is null");
                  if (iAnz>0)
                	  g.fixtestError(iAnz+" von Daten_Bild gelöscht");
                  if (g.ASA())
                  {
                    b = b && p.exec("alter table Daten_Bild drop PRIMARY KEY");
                    b = b && p.exec("alter table Daten_Bild MODIFY aic_zustand integer not null");
                    b = b && p.exec("alter table Daten_Bild add PRIMARY KEY (AIC_TABELLENNAME, AIC_FREMD, AIC_Zustand)");
                  }
                  else if (g.MS())
                  {
                    String sPK=p.getString("select name from sysobjects where name in ('PK_Daten_Bild','PK__DATEN_BILD__5812160E')");
                    b = b && p.exec("ALTER TABLE Daten_Bild DROP CONSTRAINT "+sPK);
                    p.exec("alter table daten_bild drop constraint FK_DATEN_BI_REF_770_SPRACHE");
                    g.bISQL=true;
                     p.exec("drop index daten_bild.DATEN_BILD_PK");
                     p.exec("drop index daten_bild.PK__DATEN_BILD__5812160E");
                     p.exec("drop index daten_bild.Ref_755_FK");
                    g.bISQL = false;
                    p.exec("alter table daten_bild drop column AIC_SPRACHE");
                    b = b && p.exec("ALTER TABLE Daten_Bild ALTER COLUMN aic_zustand int NOT NULL");
                    b = b && p.exec("ALTER TABLE Daten_Bild ADD CONSTRAINT PK_Daten_Bild PRIMARY KEY CLUSTERED  ([AIC_TABELLENNAME], [AIC_FREMD], [AIC_Zustand])");
                  }
                  else if (g.Oracle())
                  {
                    b = b && p.exec("alter table Daten_Bild MODIFY aic_zustand number(10) not null");
                    b = b && p.exec("alter table Daten_Bild drop PRIMARY KEY");
                    b = b && p.exec("alter table Daten_Bild add PRIMARY KEY (AIC_TABELLENNAME, AIC_FREMD,AIC_Zustand)");
                  }
                  else if (g.MySQL())
                  {
                	g.bISQL=true;
                	 p.exec("ALTER TABLE daten_Bild DROP INDEX DATEN_BILD_PK");
                	 p.exec("ALTER TABLE daten_Bild DROP INDEX PK__DATEN_BILD__5812160E");
                	g.bISQL = false;
                    b = b && p.exec("alter table Daten_Bild drop PRIMARY KEY");
                    b = b && p.exec("ALTER TABLE Daten_Bild MODIFY COLUMN aic_zustand int NOT NULL");
                    b = b && p.exec("alter table daten_bild modify column aic_sprache int null");
                    b = b && p.exec("update daten_bild set aic_sprache=null");
                    b = b && p.exec("alter table Daten_Bild add PRIMARY KEY (AIC_TABELLENNAME, AIC_FREMD,AIC_Zustand)");
                    p.exec("ALTER TABLE daten_Bild DROP FOREIGN KEY FK_DATEN_BI_REF_770_SPRACHE");
                    p.exec("ALTER TABLE daten_Bild DROP INDEX Ref_755_FK");
                    b = b && p.exec("alter table daten_bild drop column aic_sprache");
                  }
                  // Modellbefehle für Fom
                  p.add("Kennung","FxDialog");
                  p.add("code",1);
                  p.add("Anweisung",1);
                  int iFxDialog = p.insert("begriffgruppe",true);
                  b=b && addCode(iFxDialog,"create Dialog")>0;
                  b=b && addCode(iFxDialog,"close Dialog")>0;
                  //b=b && addCode(iFxDialog,"set Dialog")>0;
                  b=b && addCode(iFxDialog,"Dialog-Info")>0;
                  b=b && addCode(iFxDialog,"Dialog-Error")>0;
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  b=b && addCode(iBed,"is JavaFX")>0;
                  b=b && addCode(iBed,"FX-Dialog")>0;                                
                  b=b && p.exec("update code set kennung='check bitsbew' where Kennung='Bedingung43' and aic_begriffgruppe="+iBed);
                  iIst = b ? setVer(5,13,2):iIst;
                }
                if(b && iIst < Ver(5,13,3) && iSoll>=Ver(5,13,3))	// 12.4. 2018
                {
                  g.diskInfo("Versionsupdate 5.13.3");
                  //Reserve-Befehle anlegen
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  for (int i=140;i<160;i++)
                      b = b && addCode(iRes, "Befehl"+i) > 0;
                  // Bits in Verboten
                  b = b && addIntSpalte("Verboten","VBits");
                      
                  iIst = b ? setVer(5,13,3):iIst;
                }
                if(b && iIst < Ver(5,13,4) && iSoll>=Ver(5,13,4))	// 25.4. 2018
                {
                  g.diskInfo("Versionsupdate 5.13.4");
                  // Benutzertabelle mit Erstanlage erweitern
                  b = b && p.exec("alter table benutzer add seit datetime null");
                  // Erstanlage laut Logging oder am 10.4.2000 (IFABO-Day mit V 1.0)
                  b = b && p.exec("update benutzer set seit=(select ein from logging where aic_logging=benutzer.aic_logging) where aic_logging is not null");
                  b = b && p.exec("update benutzer set seit=("+g.DateTimeToString(new DateWOD(2000,4,10).toDate())+") where seit is null");
                  iIst = b ? setVer(5,13,4):iIst;
                }
                if(b && iIst < Ver(5,13,5) && iSoll>=Ver(5,13,5))	// 26.4. 2018
                {
                  g.diskInfo("Versionsupdate 5.13.5");
                  // niedrige Sicherheit auf sehr hohe Sicherheit stellen                 
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_benutzer,kennung,passwort,bits from benutzer where geloescht is null and"+g.bitis("bits",Global.cstPW,0),true);
                  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                  {
                	  g.fixtestInfo("konvertiere "+Tab.getS("Kennung"));
                	  int iAic=Tab.getI("aic_benutzer");
                	  String sPWx=g.PasswordConvert(g.getPassword(Tab.getS("passwort"),Transact.PWR,0),Transact.PWVH,iAic);
                      p.add("Passwort",sPWx);
                      p.add("bits", Tab.getI("bits")+Global.cstPW_MD5B);
                      p.update("benutzer", iAic);
                  }
                  iIst = b ? setVer(5,13,5):iIst;
                }
                if(b && iIst < Ver(5,13,6) && iSoll>=Ver(5,13,6))	// 27.4. 2018
                {
                  g.diskInfo("Versionsupdate 5.13.6");
                  b = b && p.exec("alter table benutzer add use_bis datetime null");
                  g.diskInfo(ViewRebuild(g,iIst)+" Views erneuert");
                  iIst = b ? setVer(5,13,6):iIst;
                }
                if(b && iIst < Ver(5,13,7) && iSoll>=Ver(5,13,7))	// 17.5. 2018
                {
                  g.diskInfo("Versionsupdate 5.13.7");
                  b = b && addIntSpalte("Verboten","Frist_Austritt");
                  b = b && addIntSpalte("Verboten","Frist_Anlage");
                  
                  
                  iIst = b ? setVer(5,13,7):iIst;
                }
                gauge.setText("V 5.15 - Erweiterungen",160);
                if(b && iIst < Ver(5,14,1) && iSoll>=Ver(5,14,1))	// 18.9. 2018
                {
                  g.diskInfo("Versionsupdate 5.14.1");
                //Stamm/Begriff-Kennung von 40 auf 98
                  if (g.ASA() || g.Oracle() || g.MySQL())
                      b = b && p.exec("alter table STAMM_PROTOKOLL MODIFY KENNUNG2 varchar(98)"+(g.Oracle()?"":" null"));
                    else
                      b = b && p.exec("ALTER TABLE STAMM_PROTOKOLL ALTER COLUMN KENNUNG2 varchar(98)");
                  if (g.ASA() || g.Oracle() || g.MySQL())
                      b = b && p.exec("alter table BEGRIFF MODIFY KENNUNG varchar(98)"+(g.Oracle()?"":" null"));
                    else
                      b = b && p.exec("ALTER TABLE BEGRIFF ALTER COLUMN KENNUNG varchar(98)");
                //Sortierung auf Datenbank richtigstellen
                  if (g.MySQL())
            	  {
            	    //g.exec("ALTER TABLE stamm_protokoll CONVERT TO CHARACTER SET latin1 COLLATE latin1_german2_ci");
            	    g.exec("ALTER TABLE stamm_protokoll CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci");
            	    g.exec("ALTER TABLE bezeichnung CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci");
            	    g.exec("ALTER TABLE Daten_Memo CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci");
            	    g.exec("ALTER TABLE Daten_Stringx CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci");
            	    g.exec("ALTER TABLE Tooltip CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci");
            	    g.fixtestInfo("Bezeichnungen-Sortierung für Umlaute geändert");
            	  }
                  g.exec("update benutzer set kennung="+g.concat("'-'","kennung")+" where geloescht is not null and kennung not like '-%'");
                //Web-bit
                  b=b && addBitSpalte("begriff","WEB");
                  int iFrame=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Frame'");
                  g.exec("update begriff set web=1 where aic_begriffgruppe="+iFrame+" and"+g.bit("bits", 0x2000000000000l/*Formular.cstTemplate*/)); // Formular-Web
                  g.exec("update begriff set web=1 where aic_begriff in (select aic_begriff from abfrage where"+g.bit("abits2",0x2000 /*Abfrage.cstAbfWeb*/)+")"); // Abfrage-Web
                //Begriff - Prog-Verbindung
                  b=b && addIntSpalte("begriff","Prog","aic_code","code");
                  int iProg=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Programm'");
                  Vector<Integer> Vec=SQL.getVector("select aic_code from code where aic_begriffgruppe="+iProg, g);
                  g.exec("update begriff set Prog=aic_code,aic_code=null where aic_code"+Static.SQL_in(Vec));
                  iIst = b ? setVer(5,14,1):iIst;
                }
                if(b && iIst < Ver(5,14,2) && iSoll>=Ver(5,14,2))	// 11.3. 2019
                {
                  g.diskInfo("Versionsupdate 5.14.2");
                  //Abfrage: +PagAnzahl
                  addIntSpalte("Abfrage","Anzahl"); // für PagAnzahl, Spaltenanzahl
                  //Fensterpos: +Zoom
                  addIntSpalte("Fensterpos","Zoom");
                  g.exec("update fensterpos set zoom=100");
                  
                  //Bereich erweitern
                  addIntSpalte("Bereich","vonInt");
                  addIntSpalte("Bereich","bisInt");
                  //Transact.bVonInt=true;
                  
                  iIst = b ? setVer(5,14,2):iIst;
                }
                if(b && iIst < Ver(5,14,3) && iSoll>=Ver(5,14,3))	// 25.3. 2019
                {
                  g.diskInfo("Versionsupdate 5.14.3");
                  int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anzeigeart'");
                  b=b && addCode(iGruppe,"ldate2")>0;
                  b=b && addCode(iGruppe,"ldate3")>0;
                  for (int i=4;i<10;i++)
                	  b=b && addCode(iGruppe,"anr"+i)>0;
                  
                
            	  if (g.ASA())
                    b=b && p.exec("alter table BEW_WERT ADD GRUNDWERT double null");
                  else if (g.Oracle())
                    b=b && p.exec("alter table BEW_WERT ADD GRUNDWERT FLOAT null");
                  else
                    b=b && p.exec("alter table BEW_WERT ADD GRUNDWERT double precision null");
            	  int iEig=SQL.getInteger(g, "select aic_eigenschaft from eigenschaft where kennung='Faktor'");
            	  p.exec("update bew_wert set Grundwert="+g.isnull()+"(select spalte_double from stammpool where aic_eigenschaft="+iEig+" and pro2_aic_protokoll is null and aic_stamm=bew_wert.aic_stamm),1)*spalte_Wert"); 
                	  
                  iIst = b ? setVer(5,14,3):iIst;
                }
                if(b && iIst < Ver(5,14,4) && iSoll>=Ver(5,14,4))	// 25.4. 2019
                {
                  g.diskInfo("Versionsupdate 5.14.4");
                // LDate für BewVonBis
                  String sInt=g.ASA() ? "integer":g.Oracle() ? "NUMBER(10)":"int";
                  b = b && p.exec("alter table bew_pool add LDateVon " + sInt + " null"+(g.Oracle() ? " ":",")+(g.MS() ? "":"add ")+"LDateBis " + sInt + " null");
                  b = b && addIntSpalte("bewegungstyp","VonBis_Eig","aic_eigenschaft","Eigenschaft");
                  
                  addIntSpalte("Spalte","WebLaenge"); // für Spaltenbreite u.ä. bei Web
                // Grundeinheit auch in Bew-Spalte umwanden
                  if (g.ASA())
                      b=b && p.exec("alter table BEW_SPALTE ADD GRUNDWERT double null");
                    else if (g.Oracle())
                      b=b && p.exec("alter table BEW_SPALTE ADD GRUNDWERT FLOAT null");
                    else
                      b=b && p.exec("alter table BEW_SPALTE ADD GRUNDWERT double precision null");
                  int iEig=SQL.getInteger(g, "select aic_eigenschaft from eigenschaft where kennung='Faktor'");
              	  p.exec("update BEW_SPALTE set Grundwert="+g.isnull()+"(select spalte_double from stammpool where aic_eigenschaft="+iEig+" and pro2_aic_protokoll is null and aic_stamm=bew_spalte.sta_aic_stamm),1)*Wert");             
              	  
              	  ViewRebuild(g,iIst);
                  iIst = b ? setVer(5,14,4):iIst;
                }
                if(b && iIst < Ver(5,14,5) && iSoll>=Ver(5,14,5))	// 8.5. 2019
                {
                  g.diskInfo("Versionsupdate 5.14.5");
                  // InnoDB-Umwandlung
                  if (g.MySQL())
                  {
                	  Tabellenspeicher Tab=new Tabellenspeicher(g,"select table_name,engine from information_schema.tables where table_schema=DATABASE() and engine<>'InnoDB'",true);
                	  int iAnz=0;
                	  if (Tab!= null && Tab.size()>0)
                		  for (int i=0;i<Tab.size();i++)
                			  if (p.exec("alter table "+Tab.getS(i,"table_name")+" engine=InnoDB"))
                				  iAnz++;
                	  if (iAnz>0)
                		  g.diskInfo(iAnz+" auf InnoDB gesetzt");
//                      b=b && p.exec("alter table spalte_z2 engine=innodb");
//                      b=b && p.exec("alter table programm_zuordnung engine=innodb");
//                      b=b && p.exec("alter table daten_doku engine=innodb");
//                      b=b && p.exec("alter table daten_bild2 engine=innodb");
                  }
                  iIst = b ? setVer(5,14,5):iIst;
                }
                if(b && iIst < Ver(5,14,6) && iSoll>=Ver(5,14,6))	// 10.5. 2019
                {
                  g.diskInfo("Versionsupdate 5.14.6");
                  b=b && addIntSpalte("abschlusstyp","Prog","aic_code","code");
                  iIst = b ? setVer(5,14,6):iIst;
                }
                if(b && iIst < Ver(5,14,7) && iSoll>=Ver(5,14,7))	// 16.5. 2019
                {
                  g.diskInfo("Versionsupdate 5.14.7");
                  int iAnz=g.exec("update begriff set web=1 where aic_begriff in (select distinct a.aic_begriff from abfrage a join eigenschaft e on a.aic_abfrage=e.aic_abfrage) and web is null");
                  if (iAnz>0)
                	  g.diskInfo(iAnz+" Abfragen auf WEB gestellt");
                  iIst = b ? setVer(5,14,7):iIst;
                }
                gauge.setText("V 5.16 - Erweiterungen",170);
                if(b && iIst < Ver(5,15,1) && iSoll>=Ver(5,15,1))	// 14.12. 2020
                {
                  g.diskInfo("Versionsupdate 5.15.1");
                  // Befehle anlegen
                  int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                  for (int i=160;i<180;i++)
                      b = b && addCode(iRes, "Befehl"+i) > 0;
                  int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                  for (int i=60;i<70;i++)
                      b = b && addCode(iBed, "Bedingung"+i) > 0;
                  // Abschluss weiter
                  addIntSpalte("abschluss","weiter");
                  g.exec("update abschluss set weiter=-2 where ab is not null"); // Monat weiterschreiben  
                  // Aufgabe/Status anlegen
                  p.add("Kennung","Aufgabe");
                  b = b && p.insert("begriffgruppe",true)>0;
                  if (g.ASA())
                  {
                      b = b && p.exec("create table STATUS(" +
                                      "AIC_STATUS integer not null default AUTOINCREMENT," +
                                      "Aufgabe integer not null,"+
                                      "KENNUNG char(40) null,"+
                                      "REIHENFOLGE integer null,"+
                                      "primary key (AIC_STATUS))");
                      b=b && p.exec("create table STATUS_ZUORDNUNG("+
            				"AIC_BEGRIFF      integer               not null,"+
            				"AIC_STATUS       integer               not null,"+
            				"primary key (AIC_BEGRIFF, AIC_STATUS))");
                  }
                  else if (g.Oracle())
                  {
                	  b = b && p.exec("create table STATUS(" +
                                  "AIC_STATUS NUMBER(10) not null," +
                                  "Aufgabe NUMBER(10) not null,"+
                                  "KENNUNG varchar(40) null,"+
                                  "REIHENFOLGE NUMBER(10) null,"+
                                  "constraint PK_STATUS primary key (AIC_STATUS))");
                	  p.createAllSequences();
                	  b=b && p.exec("create table STATUS_ZUORDNUNG("+
            				"AIC_BEGRIFF      NUMBER(10)               not null,"+
            				"AIC_STATUS       NUMBER(10)               not null,"+
            				"constraint PK_STATUS_ZUORDNUNG primary key (AIC_BEGRIFF, AIC_STATUS))");
                  }
                  else if (g.MS() || g.MySQL())
                  {
                      b = b && p.exec("create table STATUS(" +
                                      "AIC_STATUS int not null "+(g.MS()?"identity,":"AUTO_INCREMENT,")+
                                      "Aufgabe int not null,"+
                                      "KENNUNG varchar(40) null,"+
                                      "REIHENFOLGE int null,"+
                                      "primary key (AIC_STATUS))");
                      b=b && p.exec("create table STATUS_ZUORDNUNG("+
              				"AIC_BEGRIFF      int            not null,"+
              				"AIC_STATUS       int            not null,"+
              				"primary key (AIC_BEGRIFF, AIC_STATUS))");
                      if (g.MySQL())
                      {
                        b=b && p.exec("alter table STATUS engine=innodb");
                        b=b && p.exec("alter table STATUS_ZUORDNUNG engine=innodb");
                      }
                  }
                  addIntSpalte("STATUS","Aufgabe","AIC_BEGRIFF","BEGRIFF",false);
                  b=b && addIntSpalte("STATUS","Abfrage","AIC_ABFRAGE","ABFRAGE");
                  addIntSpalte("STATUS_ZUORDNUNG","AIC_BEGRIFF","AIC_BEGRIFF","BEGRIFF",false);
                  addIntSpalte("STATUS_ZUORDNUNG","AIC_STATUS","AIC_STATUS","STATUS",false);
                  if (b)
                  {
                      p.add("Kennung", "STATUS");
                      b=p.insert("tabellenname", true)>0;
                  }                   
                  iIst = b ? setVer(5,15,1):iIst;
                }
                if(b && iIst < Ver(5,15,2) && iSoll>=Ver(5,15,2))	// 25.1.2021
                {
                  g.diskInfo("Versionsupdate 5.15.2");
                  // offen bereinigen
                  Vector<Integer> VecO=SQL.getVector("select aic_auswahl from auswahl where nr=0", g);
                  int iAnz=g.exec("delete from bew_aic where aic"+Static.SQL_in(VecO));
                  g.diskInfo(iAnz+" Bool3 auf offen entfernt");
                  // Abschlusstyp bei Begriff
                  b=b && addIntSpalte("Begriff","AIC_Abschlusstyp","Abschlusstyp");
                  // Miniaturbild
                  b=b && p.exec("alter table Daten_Bild2 ADD Mini "+(g.Oracle()?"BLOB":g.MySQL() ? "MEDIUMBLOB":"IMAGE")+" null");
                  // JSON-Befehle
                  p.clear();
      			  p.add("Kennung","JSON");
      			  p.add("code",1);
      			  p.add("Anweisung",1);
      			  int iJSON = p.insert("begriffgruppe",true);
                  b = b && addCode(iJSON, "JsonDatum") > 0;
                  b = b && addCode(iJSON, "JsonString") > 0;
                  b = b && addCode(iJSON, "JsonZahl") > 0;
                  b = b && addCode(iJSON, "JsonAic") > 0;
                  b = b && addCode(iJSON, "JsonMass") > 0;
                  b = b && addCode(iJSON, "ClearJson") > 0;
                  iIst = b ? setVer(5,15,2):iIst;
                }
                if(b && iIst < Ver(5,15,3) && iSoll>=Ver(5,15,3))	// 16.2.2021
                {
                  g.diskInfo("Versionsupdate 5.15.3");
                  // Status-Erweiterung (Code, bits)
                  p.add("Kennung","Darstellung");
                  p.add("code",1);
                  b= p.insert("begriffgruppe",true)>0;
                  addIntSpalte("STATUS","AIC_CODE","CODE");
                  addIntSpalte("STATUS","bits");
                  // Berechnung + String
                  p.exec("alter table Spalte_berechnung ADD Eingabe varchar(100) null");
                  
                  iIst = b ? setVer(5,15,3):iIst;
                }
                if(b && iIst < Ver(5,15,4) && iSoll>=Ver(5,15,4))	// 17.3.2021
                {
                	g.diskInfo("Versionsupdate 5.15.4");
                	// Status, Aufgabe: + Bildname
                	b = b && p.exec("alter table Status ADD Bildname varchar(50) null");
                	b = b && p.exec("alter table BEGRIFF ADD Bildname varchar(50) null");
                	// Modell: + maxBefehle, Abfrage2
                	b = b && addIntSpalte("Modell","Max_B"); //maximale Befehlsanzahl
                	b = b && addIntSpalte("Modell","Farbe","Aic_Abfrage","Abfrage");
                	// Stt: + Aufgabe, Status
                	addIntSpalte("Stammtyp","AIC_Status","Status");
                	addIntSpalte("Stammtyp","Aufgabe","Aic_Begriff","Begriff");
                	//Aic_Daten holen, speichern
                	int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Anzeigeart'");
                    b=b && addCode(iGruppe,"AIC_Daten")>0;
                    b=b && addCode(iGruppe,"AIC_Stammpool")>0;
                    b=b && addCode(iGruppe,"AIC_Protokoll")>0;
                    int iGet=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                    b=b && addCode(iGet,"get_Daten")>0;
                    int iSave=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='saveorders'");
                    b=b && addCode(iSave,"save_Daten")>0;
                	iIst = b ? setVer(5,15,4):iIst;
                }
                if(b && iIst < Ver(5,15,5) && iSoll>=Ver(5,15,5))	// 29.3.2021
                {
                	g.diskInfo("Versionsupdate 5.15.5");
                	// Datentypen anlegen
                	int iGruppe = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                    b=b && addBegriff(iGruppe,"CalcBoolean")>0;
                    b=b && addBegriff(iGruppe,"CalcBool3")>0;
                    b=b && addBegriff(iGruppe,"Aufgabe")>0;
                    b=b && addBegriff(iGruppe,"Status")>0;
                    b=b && addBegriff(iGruppe,"GPS")>0;
                	//Tabelle Daten_GPS, Spalte_User anlegen
                    if (g.MySQL() || g.MS())
                    {
                      b = b && p.exec("create table Daten_GPS (" +
                                      "AIC_Daten int not null," +
                                      "lat double precision not null,"+
                                      "lng double precision not null,"+
                                      "name varchar(50) null,"+
                                      "primary key (AIC_Daten))");
                      b = b && p.exec("create table Spalte_User (" +
                    		  		  "AIC_Benutzer int not null,"+
                    		  		  "AIC_Spalte int not null,"+
		                              "bits int null,"+
		                              "breite int null,"+
		                              "wert varchar(255) null,"+
		                              "primary key (Aic_Benutzer,AIC_Spalte))");
                    }
                    else if (g.Oracle())
                    {
                        b = b && p.exec("create table Daten_GPS (" +
                                        "AIC_Daten NUMBER(10) not null," +
                                        "lat FLOAT not null,"+
                                        "lng FLOAT not null,"+
                                        "name varchar(255) not null," +
                                        "constraint PK_Daten_GPS primary key (AIC_Daten))");
                        b = b && p.exec("create table Spalte_User (" +
                        				"AIC_Benutzer NUMBER(10) not null," +
                        				"AIC_Spalte NUMBER(10) not null," +
                                        "bits NUMBER(10) not null," +
                                        "breite NUMBER(10) not null," +
                                        "wert varchar(255) not null," +
                                        "constraint PK_Spalte_User primary key (Aic_Benutzer,AIC_Spalte))");
                    }
                	// Status: +Stamm, div. Relationen
                    b = b && addIntSpalte("STATUS","AIC_STAMM","Stamm");
//                    addIntSpalte("Daten_GPS","AIC_Daten","AIC_Daten","Daten",false);
                    addIntSpalte("Spalte_User","AIC_Benutzer","AIC_Benutzer","Benutzer",false);
                    addIntSpalte("Spalte_User","AIC_Spalte","AIC_Spalte","Spalte",false);
                	iIst = b ? setVer(5,15,5):iIst;
                }
                if(b && iIst < Ver(5,15,6) && iSoll>=Ver(5,15,6))	// 19.4.2021
                {
                	g.diskInfo("Versionsupdate 5.15.6");
                	// reserveOP anlegen
                	int iOP=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Operation'");
                    for (int i=11;i<21;i++)
                        b = b && addCode(iOP, "reserveOP"+i) > 0;
                	// GPS-Befehle, Faktor
                        int iGet=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='get data'");
                        b=b && addCode(iGet,"get_GPS")>0;
                        int iMath = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='math_functions'");
                        b=b && addCode(iMath,"distance_GPS")>0;
                    addIntSpalte("Daten_GPS","map");
                	iIst = b ? setVer(5,15,6):iIst;
                }
                if(b && iIst < Ver(5,15,7) && iSoll>=Ver(5,15,7))	// 5.5.2021
                {
                	g.diskInfo("Versionsupdate 5.15.7");
                	// Pane Karusell anlegen
                	int iPanel=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Panel'");
                    b=b && iPanel>0 && addBegriff(iPanel,"Karussell")>0;
                	// Datentyp Modell & Abfrage
                    int iDT = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                    if (b && iDT>0)
                    {
                    	addBegriff(iDT,"Modell");
                    	addBegriff(iDT,"Abfrage");
                    }
                	// Relation Spalte-Begriff 2x inkl umkopieren
                    addIntSpalte("Spalte","Abfrage_BEGRIFF","AIC_BEGRIFF","BEGRIFF");
                    addIntSpalte("Spalte","Modell_BEGRIFF","AIC_BEGRIFF","BEGRIFF");
                    int iFom=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Frame'");
                    int iAbf=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Abfrage'");
                    Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_spalte,beg_aic_begriff,(select aic_begriffgruppe from  begriff where aic_begriff=spalte.beg_aic_begriff) bg from spalte where beg_aic_begriff is not null",true);
                    int iAnz=0;
                    for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                    {
                    	int iBG=Tab.getI("bg");
                    	if (iBG==iAbf)
                    		iAnz+=g.exec("update spalte set Abfrage_BEGRIFF="+Tab.getI("beg_aic_begriff")+",beg_aic_begriff=null where aic_spalte="+Tab.getI("aic_spalte"));
                    	else if (iBG != iFom)
                    		g.printError("Spalte "+Tab.getI("aic_spalte")+" hat BG="+iBG);
                    }
                    g.diskInfo("SubAbf von "+iAnz+" Spalten umkopiert");
                	iIst = b ? setVer(5,15,7):iIst;
                }
                if(b && iIst < Ver(5,15,8) && iSoll>=Ver(5,15,8))	// 11.5.2021
                {
                	g.diskInfo("Versionsupdate 5.15.8");
                	// Reihenfolge für Aufgabe und Status_Zuordnung
                	b = b && addIntSpalte("Status_Zuordnung","Pos");
                	b = b && addIntSpalte("Begriff","Pos");
                	b = b && addIntSpalte("Spalte","BITS4");
                	iIst = b ? setVer(5,15,8):iIst;
                }
                if(b && iIst < Ver(5,15,9) && iSoll>=Ver(5,15,9))	// 20.5.2021
                {
                	g.diskInfo("Versionsupdate 5.15.9");
                	b = b && p.exec("alter table spalte ADD ToggleSight varchar(50) null");
                	b = b && p.exec("alter table spalte ADD Icon varchar(30) null");
                	b = b && p.exec("alter table spalte ADD Farbe varchar(30) null");
                	iIst = b ? setVer(5,15,9):iIst;
                }
                if(b && iIst < Ver(5,16,0) && iSoll>=Ver(5,16,0))	// 1.3.2022
                {
                  g.diskInfo("Versionsupdate 5.16.0");
                  // PW-Verschlüsselung ändern
//                  int iPW_MD5=0x0400; // cstPW_MD5
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_benutzer,kennung,passwort,bits from benutzer where"+g.bitis("bits",Global.cstPW,0),true);
                  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
	              {
                	  int iAic=Tab.getI("aic_benutzer");
                	  String sPWx=g.PasswordConvert(g.getPassword(Tab.getS("passwort"),Transact.PWR,0),Transact.PWVH,iAic);
	                  g.diskInfo("konvertiere PW auf sehr hoch bei "+Tab.getS("kennung")+" ("+iAic+")");
                	  p.add("Passwort",sPWx);
                	  p.add("bits", Tab.getI("bits")+Global.cstPW_MD5B);
                	  p.update("benutzer", iAic);
	              }
                  int iAnz=Tab.size();
                  if (iAnz>0)
                	  g.diskInfo(iAnz+" PW auf sehr hoch konvertiert");
                  
                  iIst = b ? setVer(5,16,0):iIst;
                }
                gauge.setText("V 5.17 - Erweiterungen",180);
                if(b && iIst < Ver(5,16,1) && iSoll>=Ver(5,16,1))	// 30.8.2022
                {
                	g.diskInfo("Versionsupdate 5.16.1");
//                	b = b && addIntSpalte("status_zuordnung","sBits");
//                	// umkopieren von Status zu Status_Zuordnung mit sBits=1
//                	
//                	if (g.MySQL() || g.MS())
//                		b = b && p.exec("alter table status drop column Reihenfolge");
//                	b = b && p.exec("alter table status_zuordnung ADD ToggleSight varchar(50) null");
                	
                	// Status-Neu-Prüfung
//                	DefAufgabe.checkKonvert(g);
                  int AUFG=0x10000;
              	  int iAnz2=SQL.getInteger(g, "select count(*) from status_zuordnung where"+g.bit("Pos", AUFG));
        		  if (/*iAnz1>0 &&*/ iAnz2==0)
        		  {
//        			  g.fixInfo("Konvertierung nötig, da "+iAnz2+"/"+iAnz1);
        			  if (g.MS())
                          g.exec("ALTER TABLE STATUS ALTER COLUMN Aufgabe int null");
        			  else
        				  g.exec("alter table STATUS MODIFY Aufgabe int null");
        			  
        			  Version.addIntSpalte(g,"STATUS","sProg","aic_code","code",true);
        			  
        			  Tabellenspeicher TabS=new Tabellenspeicher(g,"select aic_status,Aufgabe,Reihenfolge from status",true);
        			  SQL Qry=new SQL(g);
        			  for (TabS.moveFirst();!TabS.out();TabS.moveNext())
        			  {
        				  Qry.add("aic_status", TabS.getI("aic_status"));
        				  Qry.add("aic_begriff", TabS.getI("Aufgabe"));
        				  Qry.add("Pos", TabS.getI("Reihenfolge")+AUFG);
        				  Qry.insert("Status_Zuordnung", false);
        			  }
        			  Qry.free();
        			  g.exec("Update Status set Aufgabe=null");
        		  }
                	// UTF8-Umwandlungen
//                	g.diskInfo(Reinigung.checkUTF8(g,true));
                	// neue Befehle
                	int iRes=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='reserve'");
                    for (int i=180;i<200;i++)
                        b = b && addCode(iRes, "Befehl"+i) > 0;
                    int iBed = p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Bedingung'");
                    for (int i=70;i<80;i++)
                        b = b && addCode(iBed, "Bedingung"+i) > 0;
                	// Kennungen erweitern
                	if (g.ASA() || g.Oracle() || g.MySQL())
                	{
                        b = b && p.exec("alter table EIGENSCHAFT MODIFY KENNUNG varchar(98)"+(g.Oracle()?"":" null"));
                        b = b && p.exec("alter table EIGENSCHAFT MODIFY FORMAT varchar(98)"+(g.Oracle()?"":" null"));
                        b = b && p.exec("alter table STAMMTYP MODIFY KENNUNG varchar(98)"+(g.Oracle()?"":" null"));
                        b = b && p.exec("alter table BEWEGUNGSTYP MODIFY KENNUNG varchar(98)"+(g.Oracle()?"":" null"));
                        b = b && p.exec("alter table BEGRIFF MODIFY KENNZEICHEN varchar(98)"+(g.Oracle()?"":" null"));
                        b = b && p.exec("alter table BEGRIFF MODIFY ALIAS varchar(98)"+(g.Oracle()?"":" null"));
                        b = b && p.exec("alter table BEGRIFF MODIFY BILDNAME varchar(98)"+(g.Oracle()?"":" null"));
                	}
                	else if (g.MS())
                	{
                        b = b && p.exec("ALTER TABLE EIGENSCHAFT ALTER COLUMN KENNUNG varchar(98)");
                        b = b && p.exec("ALTER TABLE EIGENSCHAFT ALTER COLUMN FORMAT varchar(98)");
                        b = b && p.exec("ALTER TABLE STAMMTYP ALTER COLUMN KENNUNG varchar(98)");
                        b = b && p.exec("ALTER TABLE BEWEGUNGSTYP ALTER COLUMN KENNUNG varchar(98)");
                        b = b && p.exec("ALTER TABLE BEGRIFF ALTER COLUMN KENNZEICHEN varchar(98)");
                        b = b && p.exec("ALTER TABLE BEGRIFF ALTER COLUMN ALIAS varchar(98)");
                        b = b && p.exec("ALTER TABLE BEGRIFF ALTER COLUMN BILDNAME varchar(98)");
                	}
                	// Begriff_Zuordnung erweitern 
                	b=b && addIntSpalte("begriff_Zuordnung","Art","aic_code","code");
                	p.add("Kennung","Zuordnungsart");
                    p.add("code",1);
                    int iBG = p.insert("begriffgruppe",true);
                    b=b && addCode(iBG,"Filter")>0;
                    b=b && addCode(iBG,"Detail")>0;
                	// Spalten2 mit Mandant
                	b=b && addIntSpalte("Spalten2","aic_mandant","aic_mandant","mandant");
                	iIst = b ? setVer(5,16,1):iIst;
                }
                if(b && iIst < Ver(5,16,2) && iSoll>=Ver(5,16,2))	// 9.9.2022
                {
                	g.diskInfo("Versionsupdate 5.16.2");
                	// Expanded anlegen
                	int iBG=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Zuordnungsart'");
                	b=b && addCode(iBG,"erweitert")>0;
                	// Datentyp User, BewUser, DtReserve anlegen
                	int iDt=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='Datentyp'");
                	b=b && addBegriff(iDt,"User")>0;
                	b=b && addBegriff(iDt,"BewUser")>0;
                	b=b && p.exec("update begriff set kennung='BewBG' where kennung='BewReserve' and aic_begriffgruppe="+iDt);
                	for (int i=1;i<6;i++)
                		b=b && addBegriff(iDt,"ResDt"+i)>0;
                	//Bild in Zeile
                	b=b && p.exec("alter table Zeile ADD BildKF "+(g.Oracle()?"BLOB":g.MySQL() ? "MEDIUMBLOB":"IMAGE")+" null");
                	iIst = b ? setVer(5,16,2):iIst;
                }
                if(b && iIst < Ver(5,16,3) && iSoll>=Ver(5,16,3))	// 28.9.2022
                {
                	g.diskInfo("Versionsupdate 5.16.3");
                	p.exec("alter table stammpool drop column Reihenfolge");
                	// Bilder von Zeile umkopieren
                	Reinigung.KonvertBildKF(g);
                	// Bild in Daten_Bild anlegen
                	b=b && p.exec("alter table Daten_Bild ADD Bild "+(g.Oracle()?"BLOB":g.MySQL() ? "MEDIUMBLOB":"IMAGE")+" null");
                	// Stammtyp erweitern
                	b = b && p.exec("alter table stammtyp ADD WebIcon varchar(98) null");
                	b = b && p.exec("alter table stammtyp ADD WebFarbe varchar(98) null");
                	iIst = b ? setVer(5,16,3):iIst;
                }
                if(b && iIst < Ver(5,16,4) && iSoll>=Ver(5,16,4))	// 5.10.2022
                {
                	g.diskInfo("Versionsupdate 5.16.4");
                	// Benutzer: + PW_Date, Tel
                	b = b && p.exec("alter table benutzer ADD Tel varchar(98) null");
                	b = b && p.exec("alter table benutzer ADD E_Mail varchar(98) null");
                	b = b && addDateSpalte("benutzer","PW_Date");
                	b = b && p.exec("update benutzer set PW_Date=seit");
                	// div. Bilder konvertieren
                	Reinigung.KonvertBildAbschnitt(g);
                	Reinigung.KonvertMini(g,null);
                	iIst = b ? setVer(5,16,4):iIst;
                }
                if(b && iIst < Ver(5,16,5) && iSoll>=Ver(5,16,5))	// 11.10.2022
                {
                	g.diskInfo("Versionsupdate 5.16.5");
                	// Status: + Hauptschirm
                	b = b && addIntSpalte("STATUS","Hauptschirm","AIC_Hauptschirm","Hauptschirm");
                	// Hauptschirm als Begriff
                	p.clear();
        			p.add("Kennung","Hauptschirm");
        			int iHS = p.insert("begriffgruppe",true);
        			b = b && iHS>0;
        			b = b && addIntSpalte("HAUPTSCHIRM","selbst","AIC_Begriff","Begriff");
        			if (b)
        			{
        				int iTabHS=SQL.getInteger(g, "select aic_tabellenname from tabellenname where kennung='Hauptschirm'");
        				Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_hauptschirm,kennung,aic_logging,(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTabHS+
        				          " AND AIC_Fremd=Hauptschirm.AIC_Hauptschirm AND AIC_Sprache=1) Bezeichnung from hauptschirm where"+g.bit("bits",0x100000 /* WEB */),true);
        				for (Tab.moveFirst();!Tab.out();Tab.moveNext())
        				{
        					p.add("defBezeichnung",Tab.getS("Bezeichnung"));
        					p.add("Kennung",Tab.getS("Kennung"));
        					p.add0("aic_logging",Tab.getI("aic_logging"));
        					p.add("aic_begriffgruppe",iHS);
        					p.add("web",true);
        					int iB = p.insert("begriff",true);
        					g.exec("update Hauptschirm set selbst="+iB+" where aic_hauptschirm="+Tab.getI("aic_hauptschirm"));
        				}
        			}
                	// Abfrage zu Stamm (wie Status)
                	b = b && addIntSpalte("ABFRAGE","WebStamm","AIC_STAMM","Stamm");
                	iIst = b ? setVer(5,16,5):iIst;
                }
                if(b && iIst < Ver(5,16,6) && iSoll>=Ver(5,16,6))	// 21.10.2022
                {
                	g.diskInfo("Versionsupdate 5.16.6");
                	b=b && addIntSpalte("Spalte_Z2","aic_mandant","aic_mandant","mandant");
                	iIst = b ? setVer(5,16,6):iIst;
                }
                if(b && iIst < Ver(5,16,7) && iSoll>=Ver(5,16,7))	// 3.11.2022
                {
                	g.diskInfo("Versionsupdate 5.16.7");
                	b = b && p.exec("alter table stammtyp ADD WebIconOffen varchar(98) null");
                	iIst = b ? setVer(5,16,7):iIst;
                }
                gauge.setText("V 5.18 - Erweiterungen",190);
                if(b && iIst < Ver(5,17,1) && iSoll>=Ver(5,17,1))	// 19.4.2023
                {
                	g.diskInfo("Versionsupdate 5.17.1");
                	// 1970 richtigstellen
                	Reinigung.check1970(g);
                	// auf UTF-8 umstellen
                	p.exec("delete from daten_text where spalte_text is null");          		
                	if (g.MySQL())
                	{
//                		p.exec("ALTER TABLE Verlauf CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci");
                		p.exec("alter table daten_text modify SPALTE_Text text");
//                		p.exec("ALTER TABLE Daten_Text CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci");
//                		g.diskInfo("Verlauf und Daten_Text auf UTF8 geändert");
                		g.diskInfo(Reinigung.checkUTF8(g,true));
                	}
                	else if (g.MS())
                	{
                		p.exec("alter table daten_text alter column SPALTE_Text text");
                	}
                	//TODO UTF-8 auch bei MS
                	
                	// FX-Abfrage-bits löschen
                	g.diskInfo(g.exec("update abfrage set abits2=abits2&0x7fffffa4 where abits2 & 0x5b>0")+"x Abfragebits entfernt");
                	
                	iIst = b ? setVer(5,17,1):iIst;
                }
                if(b && iIst < Ver(5,17,2) && iSoll>=Ver(5,17,2)) // 20.4.2023
                {
                	g.diskInfo("Versionsupdate 5.17.2");
                	// import data umwandeln
                	g.refreshCodes();
                	int iAnz=g.exec("update befehl set aic_code="+g.TabCodes.getAic("write data record")+", eingabe='Import' where aic_code="+g.TabCodes.getAic("write import data"));
                	b = b && iAnz>=0;
                	if (iAnz>0)
            			g.diskInfo(iAnz+" <write import data> auf <write data record> geändert");
                	b = b && p.exec("delete from code where kennung='write import data'");
                	// remember statt save
                	String[] sC=new String[] {"act_Aic","act_date","aic","Bew","Bool3","boolean","from","to","duration","false","true","NIL","number","QryAic","String"};
                	for (int i=0;i<sC.length;i++)
                		b = b && SaveToRemember(sC[i]);
                	// alte Modellbefehle benennen
                	b=b && p.exec("update code set kennung='JsonBoolean' where kennung='Befehl174'");
                	 
                	// neue Modellbefehle
                	int iNew=0;
                	if (b)
                	{
	                	p.add("Kennung","Calc_new");
	                    p.add("code",1);
	                    p.add("Anweisung",1);
	                    iNew = p.insert("begriffgruppe",true);
                	}
                    b=b && addCode(iNew,"init")>0;
                    b=b && addCode(iNew,"get")>0;
                    b=b && addCode(iNew,"set")>0;
                	iIst = b ? setVer(5,17,2):iIst;
                }
                if(b && iIst < Ver(5,17,3) && iSoll>=Ver(5,17,3)) // 24.4.2023
                {
                	g.diskInfo("Versionsupdate 5.17.3");
                	// Stt / Modelle für Formulare
                	if (g.MySQL() || g.MS())
                    {
                        b = b && p.exec("create table Beg2_Z (" +
                        		      "AIC_Beg2_Z int not null "+(g.MS()?"identity,":"AUTO_INCREMENT,")+
                    		  		  "AIC_Begriff int not null,"+
                    		  		  "AIC_Tabellenname int not null,"+
                    		  		  "AIC_Fremd int not null,"+
                    		  		  "Reihe int null,"+
		                              "ZBits int null,"+
		                              "Art int null,"+
		                              "primary key (AIC_Beg2_Z))");
                    }
                	else if (g.Oracle())
                    {
                  	  b = b && p.exec("create table Beg2_Z(" +
                                    "AIC_Beg2_Z NUMBER(10) not null," +
                                    "AIC_Begriff NUMBER(10) not null,"+
                  		  		  	"AIC_Tabelle NUMBER(10) not null,"+
                  		  		  	"AIC_Fremd NUMBER(10) not null,"+
                  		  		  	"Reihe NUMBER(10) null,"+
		                            "ZBits NUMBER(10) null,"+
		                            "Art NUMBER(10) null,"+
                                    "constraint PK_Beg2_Z primary key (AIC_Beg2_Z))");
                  	  p.createAllSequences();
                    }
                    b=b && addIntSpalte("Beg2_Z","AIC_Begriff","AIC_Begriff","Begriff",false);
                    b=b && addIntSpalte("Beg2_Z","AIC_Tabellenname","AIC_Tabellenname","Tabellenname",false);
                    b=b && addIntSpalte("Beg2_Z","Art","aic_code","code",false);
                	iIst = b ? setVer(5,17,3):iIst;
                }
                if(b && iIst < Ver(5,17,4) && iSoll>=Ver(5,17,4))
                {
                	g.diskInfo("Versionsupdate 5.17.4");
                	// Relation Sprache: + SPR_AIC_SPRACHE
                	b=b && addIntSpalte("Sprache","SPR_AIC_SPRACHE","aic_Sprache","Sprache");
                	// Tabelle Individuell: Übersetzung pro Mandant und Sprache: wie bezeichnung: +AIC_Mandant
                	if (g.MySQL() || g.MS())
                    {
                		b = b && p.exec("create table INDIVIDUELL (" +
					  			"AIC_MANDANT int not null,"+
					  			"AIC_TABELLENNAME int not null,"+
					  			"AIC_FREMD int not null,"+
					  			"AIC_SPRACHE int not null,"+
					  			"BEZEICHNUNG varchar(255) not null,"+
					  			"AIC_PROTOKOLL int not null,"+
                      		"primary key (AIC_MANDANT,AIC_TABELLENNAME, AIC_FREMD, AIC_SPRACHE))");
                    }
                	else if (g.Oracle())
                    {
                		b = b && p.exec("create table INDIVIDUELL (" +
					  			"AIC_MANDANT NUMBER(10) not null,"+
					  			"AIC_TABELLENNAME NUMBER(10) not null,"+
					  			"AIC_FREMD NUMBER(10) not null,"+
					  			"AIC_SPRACHE NUMBER(10) not null,"+
					  			"BEZEICHNUNG varchar(255) not null,"+
					  			"AIC_PROTOKOLL NUMBER(10) not null,"+
                      		"constraint PK_INDIVIDUELL primary key (AIC_MANDANT,AIC_TABELLENNAME, AIC_FREMD, AIC_SPRACHE))");
                    }
//                	gauge.setText("Centrunden",195);
//                	// BewWaehrung-Grundwert in Cent                	               	
//                	int iDtW=p.getInteger("select aic_begriff from begriff where kennung='BewWaehrung'");
//                	int iAnz=g.exec("update bew_wert set grundwert=round(Spalte_Wert*100"+(g.MS()?",0":"")+") where aic_eigenschaft in (select aic_eigenschaft from eigenschaft where aic_begriff="+iDtW+" and (feldlaenge<3 or feldlaenge is null))");
//                	g.diskInfo(iAnz+"x Grundwert auf cent gerundet");
//                	Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_eigenschaft,feldlaenge from eigenschaft where aic_begriff="+iDtW+" and feldlaenge>2",true);
//                	for (Tab.moveFirst();!Tab.out();Tab.moveNext())
//                	{
//                		if (g.MS())
//                			iAnz=g.exec("update bew_wert set grundwert=round(Spalte_Wert*100,"+(Tab.getI("feldlaenge")-2)+") where aic_eigenschaft="+Tab.getI("aic_eigenschaft"));
//                		else
//                		{
//	                		iAnz=Tab.getI("feldlaenge");
//	                		double d1=Math.pow(10,iAnz);
//	                		double d2=Math.pow(10,iAnz-2);
//	                		iAnz=g.exec("update bew_wert set grundwert=round(Spalte_Wert*"+d1+")/"+d2+" where aic_eigenschaft="+Tab.getI("aic_eigenschaft"));
//                		}
//                		g.diskInfo(iAnz+"x Grundwert bei Eig="+Tab.getI("aic_eigenschaft")+" geändert");
//                	}               	
                	iIst = b ? setVer(5,17,4):iIst;
                }
                if(b && iIst < Ver(5,17,5) && iSoll>=Ver(5,17,5))
                {
                	g.diskInfo("Versionsupdate 5.17.5");
                	// Programm-Eig-Zuordnung: Über Modul in Tabelle Prog2_Zuordnung
                	if (g.Oracle())
                        b = b && p.exec("create table PROG2_ZUORDNUNG(" +
                                        "AIC_CODE NUMBER(10) not null," +
                                        "AIC_EIGENSCHAFT NUMBER(10) not null,"+
                                        "REIHENFOLGE NUMBER(10) null,"+
                                        "constraint PK_PROG2_ZUORDNUNG primary key (AIC_CODE, AIC_EIGENSCHAFT))");
                      else if (g.MS() || g.MySQL())
                        b = b && p.exec("create table PROG2_ZUORDNUNG("+
                                    "AIC_CODE       int               not null,"+
                                    "AIC_EIGENSCHAFT    int           not null,"+
                                    "REIHENFOLGE    int               null,"+
                                    "constraint PK_PROG2_ZUORDNUNG primary key (AIC_CODE, AIC_EIGENSCHAFT))");
                	if (b)
                	{
                      p.add("Kennung","Prog2");
                      p.add("aic_tabellenname",p.getInteger("select aic_tabellenname from tabellenname where kennung='CODE'"));
                      p.add("tab_aic_tabellenname",p.getInteger("select aic_tabellenname from tabellenname where kennung='Eigenschaft'"));
                      p.add("aic_begriffgruppe",SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Programm'"));
                      p.add("Reihenfolge",true);
                      b=p.insert("zuordnung",true)>0;
                	}
                	// Var/Eingabe getrennt
                      gauge.setText("Var/Eingabe",197);
                      g.exec("drop view befehl2");
                      b = b && p.exec("alter table Befehl ADD Var varchar(50) null");
                      String sVarBit=g.bit("MBits",Calc.VAR);
                      b = b && p.exec("update Befehl set Var=Eingabe,Eingabe=null where eingabe not like '%=%' and"+sVarBit);
                      if (b)
                      {
	                      Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_befehl,eingabe from befehl where eingabe like '%=%' and"+sVarBit,true);
	                      for (Tab.moveFirst();!Tab.out();Tab.moveNext())
	                      {
	                    	  String sEingabe=Tab.getS("Eingabe");
	                    	  String sVar=null;
	                    	  if (sEingabe != null)
	                          {
	                              int iPos=sEingabe.indexOf("=");
	                              sVar=iPos<0 ? sEingabe+"":sEingabe.substring(0,iPos);
	                              sEingabe=iPos<0 ? null:sEingabe.substring(iPos+1);
	//                              A.g.progInfo("sEingabe="+sEingabe+", sVar="+sVar);
	                          }
	                    	  if (sVar != null)
	                    		  g.exec("update Befehl set Var="+Static.StringForSQL(sVar)+",Eingabe="+Static.StringForSQL(sEingabe)+" where aic_befehl="+Tab.getI("aic_befehl"));                  	  
	                      }
                      }
                      g.exec("create view befehl2 as select * from befehl where def_aic_defverlauf is null");
                	// Code: +Nr
                      if (b)
                    	  addIntSpalte("Code","Nr");
                	iIst = b ? setVer(5,17,5):iIst;
                }
                if(b && iIst < Ver(5,17,6) && iSoll>=Ver(5,17,6))
                {
                	g.diskInfo("Versionsupdate 5.17.6");
                	// Programm-Stt-Zuordnung: Über Modul in Tabelle Prog_Stt_Zuordnung
                	if (g.Oracle())
                        b = b && p.exec("create table PROG_STT_ZUORDNUNG(" +
                                        "AIC_CODE NUMBER(10) not null," +
                                        "AIC_STAMMTYP NUMBER(10) not null,"+
                                        "REIHENFOLGE NUMBER(10) null,"+
                                        "constraint PK_PROG_STT_ZUORDNUNG primary key (AIC_CODE, AIC_STAMMTYP))");
                      else if (g.MS() || g.MySQL())
                        b = b && p.exec("create table PROG_STT_ZUORDNUNG("+
                                    "AIC_CODE       int               not null,"+
                                    "AIC_STAMMTYP   int               not null,"+
                                    "REIHENFOLGE    int               null,"+
                                    "constraint PK_PROG_STT_ZUORDNUNG primary key (AIC_CODE, AIC_STAMMTYP))");
                	if (b)
                	{
                      p.add("Kennung","Prog_Stt");
                      p.add("aic_tabellenname",p.getInteger("select aic_tabellenname from tabellenname where kennung='CODE'"));
                      p.add("tab_aic_tabellenname",p.getInteger("select aic_tabellenname from tabellenname where kennung='Stammtyp'"));
                      p.add("aic_begriffgruppe",SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Programm'"));
                      p.add("Reihenfolge",true);
                      b=p.insert("zuordnung",true)>0;
                	}
                	// Relationen Code/Eig/Stt
                	addIntSpalte(g,"PROG_STT_ZUORDNUNG","AIC_CODE","AIC_CODE","CODE",false);
                	addIntSpalte(g,"PROG_STT_ZUORDNUNG","AIC_STAMMTYP","AIC_STAMMTYP","STAMMTYP",false);
                	addIntSpalte(g,"PROG2_ZUORDNUNG","AIC_CODE","AIC_CODE","CODE",false);
                	addIntSpalte(g,"PROG2_ZUORDNUNG","AIC_EIGENSCHAFT","AIC_EIGENSCHAFT","EIGENSCHAFT",false);
                	// Spalte mit Subspalte (für Subformular welche Spalte übergeben werden soll
                	b=b && addIntSpalte("Spalte","SPA_AIC_SPALTE","aic_Spalte","Spalte");
                	iIst = b ? setVer(5,17,6):iIst;
                }
                gauge.setText("fertig V 5.18",gauge.getMax());
                g.diskInfo(b ? "fertig":"Fehler");
                if (b)
                {
                  p.setParameter("Version","",iHaupt,iNeben,iProg,0,false,false);
                  g.fixInfo("fertig Upgedatet");
                }
                else
                  p.setParameter("Version","",iIst/1000,(iIst%1000)/10,iIst%10,0,false,false);
		return b;
	}
	
	private boolean SaveToRemember(String s)
	{
		int iR=g.TabCodes.getAic("remember "+s);
		int iS=g.TabCodes.getAic("save "+(s.equals("act_Aic") ? "act. aic":s));
		if (iR==0 || iS==0)
			return false;
		int iAnz=g.exec("update befehl set aic_code="+iR+",mbits=mbits|0x0200 where aic_code="+iS);
		if (iAnz<0)
			return false;
		p.exec("delete from code where aic_code="+iS);
		if (iAnz>0)
			g.diskInfo(iAnz+" <save "+s+"> auf remember geändert");
		return true;
	}
	
	public static boolean V18()
	{
		return getVer()>Ver(5,17,0);
	}

    private String intSQL()
    {
      return g.ASA() ? " integer":g.Oracle() ? " NUMBER(10)":" int";
    }

    private boolean addIntSpalte(String sTab,String sCol,String sTab2)
    {
      return addIntSpalte(sTab,sCol,sCol,sTab2,true);
    }
    
    private boolean addIntSpalte(String sTab,String sCol,String sCol2,String sTab2)
    {
    	return addIntSpalte(sTab,sCol,sCol2,sTab2,true);
    }
    
    private boolean addIntSpalte(String sTab,String sCol,String sCol2,String sTab2,boolean bAddCol)
    {
    	return addIntSpalte(g,sTab,sCol,sCol2,sTab2,bAddCol);
    }

	public static boolean addIntSpalte(Global g,String sTab,String sCol,String sCol2,String sTab2,boolean bAddCol)
	{
		  boolean bSame=sCol.equals(sCol2);
          String sFK=" FK_"+(bSame ? sTab:sTab+"_"+sCol)+(g.Oracle() && !bSame ? "":"_"+sTab2);
          boolean b=bAddCol ? g.exec("alter table "+sTab+" ADD "+sCol+" "+(g.ASA()?"integer":g.Oracle()?"NUMBER(10)":"int")+" null")>-2:true;
          b=b && g.exec("alter table "+sTab+" add "+(g.ASA()? "foreign key"+sFK+" (":"constraint"+sFK+" foreign key (")+sCol+") references "+sTab2+" ("+sCol2+")"+
                        (g.ASA()? " on update restrict on delete restrict":""))>-2;
          return b;
	}

        private boolean addDateSpalte(String sTab,String sCol)
        {
                return p.exec("alter table "+sTab+" ADD "+sCol+" "+(g.ASA() || g.Oracle()?"date":"datetime")+" null");
        }

        private boolean addDTSpalte(String sTab,String sCol)
        {
                return p.exec("alter table "+sTab+" ADD "+sCol+" "+(g.Oracle()?"date":"datetime")+" null");
        }

        private boolean addBitSpalte(String sTab,String sCol)
        {
                return p.exec("alter table "+sTab+" ADD "+sCol+" "+(g.Oracle()?"NUMBER(1)":"bit")+" null");
        }

        private boolean addIntSpalte(String sTab,String sCol)
        {
                return p.exec("alter table "+sTab+" ADD "+sCol+" "+(g.Oracle()?"NUMBER(10)":"int")+" null");
        }

        private boolean addLongSpalte(String sTab,String sCol)
        {
                return p.exec("alter table "+sTab+" ADD "+sCol+" "+(g.Oracle()?"NUMBER(20)":"bigint")+" null");
        }

        private boolean modifyString(String sTab,String sCol,int iSize)
        {
          if (g.ASA() || g.Oracle() || g.MySQL())
            return p.exec("alter table "+sTab+" MODIFY "+sCol+" varchar("+iSize+")"+(g.Oracle()?"":" null"));
          else
            return p.exec("ALTER TABLE "+sTab+" ALTER COLUMN "+sCol+" varchar("+iSize+")");
        }

        public static String getS(int i)
        {
          if (i == 0)
            return "";
          else
          {
            if (i < 10000)
              i *= 100;
            String s = "" + i;
            return s.charAt(3) == '0' ? "V" + s.charAt(0) + "." + s.charAt(1) + s.charAt(2) + "." + s.charAt(4) + s.charAt(5) :
                "P" + s.charAt(0) + "." + (new Integer(s.substring(1, 3)).intValue() + 1) + "(" + s.charAt(3) + ")";
          }
        }

        public static int getVer()
	{
		return Ver(iHaupt,iNeben,iProg);
	}

	public static int getVer2()
	{
		return iHaupt*100000+iNeben*1000+iProg*100+iSub;
	}

	private static int Ver(int i1,int i2,int i3)
	{
		return i1*1000+i2*10+i3;
	}

        private int setVer(int i1,int i2,int i3)
        {
          p.setParameter("Version","",i1,i2,i3,0,false,false);
          return i1*1000+i2*10+i3;
        }

        public static String getAktVer(Global g)
        {
          Parameter P2 = new Parameter(g,"Datenbank");
          P2.getParameter("Version",false,false);
          return getVersion(P2.int1,P2.int2,P2.int3,0);
        }

	public boolean OK()
	{
		return bOK;
	}

	private static String getVersion(int i1,int i2,int i3,int i4)
	{
		return (Test()? "P ":"V ")+(i3 == 0 ? +i1+"."+Static.VorNull(i2)+(i4>0?"."+Static.VorNull(i4):"") : i1+"."+Static.VorNull(i2+1)+"("+i3+")");
	}

	public static String getVersion()
	{
		return getVersion(iHaupt,iNeben,iProg,iSub);
	}

	public static String getDate()
	{
		return DateWOD.Format(null,"dd.MM.yyyy",dt.getTime());//Static.DateToString(dt.getTime());
	}

//        private void LoescheWaehrung(String sW,int iEuro,double f)
//        {
//          int iDel=p.getInteger("select aic_stamm from stamm"+g.join("stammtyp","stamm")+" where stammtyp.kennung='Waehrung' and stamm.kennung='"+sW+"'");
//          if (iDel>0)
//          {
//            p.exec("update stammpool set spalte_double=spalte_double/"+f+",sta_aic_stamm="+iEuro+" where sta_aic_stamm="+iDel);
//            p.exec("update bew_wert set spalte_wert=spalte_wert/"+f+",aic_stamm="+iEuro+" where aic_stamm="+iDel);
//            p.exec("update eigenschaft set aic_stamm=null where aic_stamm="+iDel);
//            p.exec("update spalte set aic_stamm=null where aic_stamm="+iDel);
//            p.exec("delete from stammpool where aic_stamm="+iDel);
//            p.exec("delete from stamm_protokoll where aic_stamm="+iDel);
//            p.exec("delete from stamm where aic_stamm="+iDel);
//            g.diskInfo(sW+" gelöscht");
//          }
//        }

        public static String StringX_Rebuild(Global t)
        {
          Tabellenspeicher TabX=new Tabellenspeicher(t,"select * from daten_stringx",true);
          String[] AryString = new String[] {"stringsehrkurz","stringkurz","string60","stringlang"};
          SQL Qry=new SQL(t);
          String s="";
          for(int i2=0;i2<AryString.length;i2++)
          {
            Tabellenspeicher Tab = new Tabellenspeicher(t, "select * from daten_" + AryString[i2], true);
            int iString=0;
            for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
              if (!TabX.posInhalt("aic_daten",Tab.getInhalt("aic_daten")))
              {
                iString++;
                Qry.add("aic_daten",Tab.getI("aic_daten"));
                Qry.add("spalte_stringx",Tab.getS("spalte_"+AryString[i2]));
                Qry.insert("daten_stringx",false);
                TabX.addInhalt("aic_daten",Tab.getInhalt("aic_daten"));
                TabX.addInhalt("spalte_stringx",Tab.getS("spalte_"+AryString[i2]));
              }
            if (iString>0)
            {
              s+=iString+" "+AryString[i2]+" nach Daten_Stringx kopiert\n";
            }
          }
          return s;
        }

        public static String FirmaRebuild(Global g,int iStamm)
        {
          long lClock = Static.get_ms();
          String s=null;
          int iSttF=g.getSttFirma();//SQL.getInteger(g,"select aic_stammtyp from stammtyp where kennung='Firma'");
          int iEigF=g.getEigFirma();//SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='Firma'");
          int iEigM=SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='MA relation'");
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_stamm,bezeichnung from stammview2 where aic_stammtyp="+iSttF,true);
                Gauge gauge2=null;
                  if (Static.bX11) gauge2=new Gauge(0,Tab.getAnzahlElemente(null),"Firma umkopieren",g,false);
                  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                  {
                    Vector<Integer> VecAll=new Vector<Integer>();
                    //Tab.setInhalt("Vec",VecAll);
                    Vector<Integer> Vec=Static.AicToVec(Tab.getI("aic_stamm"));
                  if (Static.bX11) gauge2.setText(Tab.getS("Bezeichnung"),Tab.getPos());
                    g.testInfo("Firmenzuordnung für "+Tab.getS("Bezeichnung"));
                    int i=0;
                    while (Vec.size()>0)// && Vec.size()<20000)
                    {
                      int iOld=VecAll.size();
                      Static.addVectorI(VecAll,Vec);
                      if (VecAll.size()>iOld)
                        Vec=SQL.getVector("select aic_stamm from poolview2 where aic_eigenschaft="+iEigF+" and "+g.in("sta_aic_stamm",Vec),g);
                      else
                        Vec=new Vector<Integer>();
                      i++;
                      if (Vec.size()>0)
                        g.testInfo(i+".:"+Vec.size()+"x");
                    }
                    if (iStamm>0)
                    	if (VecAll.contains(iStamm))
                    		VecAll=Static.AicToVec(iStamm);
                    	else
                    		VecAll.clear();
                    int iAnz=0; 
                    if (VecAll.size()>0)
                    {
                      Vec=SQL.getVector("select aic_stamm from poolview2 where aic_eigenschaft="+iEigM+" and "+g.in("sta_aic_stamm",VecAll),g);
                      g.fixtestInfo("MA:"+Vec.size()+"x");
                      Static.addVectorI(VecAll,Vec);
                      
                      iAnz=SQL.update(g,"stamm_protokoll","firma="+Tab.getI("aic_stamm"),VecAll,"stamm",1000,"(firma is null or firma<>"+Tab.getI("aic_stamm")+") and pro_aic_protokoll is null and weg is null"); // nur aktuelle, damit alte nicht überschrieben
                  	}
                    if (iAnz>0)
                    {
                      if (s==null)
                        s="Firmen bereinigen:\n";
                      g.diskInfo(Tab.getS("Bezeichnung") + ": " + iAnz);
                      s+=Tab.getS("Bezeichnung") + ": " + iAnz+"\n";
                    }
                  }
                if (Static.bX11) gauge2.setText("fertig Firma",gauge2.getMax());
            g.clockInfo("FirmaRebuild",lClock);
            return s==null?"keine Firmen-bereinigung\n":s;
        }

        public static int ViewRebuild(Global g,int iVer)
        {
        	g.fixInfo("ViewRebuild");
                long lClock = Static.get_ms();
                Tabellenspeicher Tab=null;
                Tabellenspeicher Tab2=null;
                if (g.ASA())
                {
                  Tab = new Tabellenspeicher(g, "select table_name,view_def from systable where creator=1 and table_type='VIEW' and table_name like '%Temp%' order by table_id", true);
                  Tab2 = new Tabellenspeicher(g, "select table_name,view_def from systable where creator=1 and table_type='VIEW' and table_name not in ('stringXview','stringXview2','stringXview3','stringXview4','poolview2','poolview','poolview3','poolview4','poolview5','bewview3','bewview2','bewview','stammview','stammview2','stammview3','stammview4','stammview5','befehl2','darstellung2') and table_name not like '%Temp%' order by table_id", true);
                  for (Tab2.moveFirst(); !Tab2.eof(); Tab2.moveNext())
                    g.exec("drop view " + Tab2.getS("table_name"));
                  for (Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                    g.exec("drop view " + Tab.getS("table_name"));
                }
                g.bISQL = true;
                if (iVer<5100)
                {
                  g.exec("drop view stringXview4");
                  g.exec("drop view stringXview2");
                  g.exec("drop view stringXview");
                }
                g.exec("drop view poolview5");
                g.exec("drop view poolview4");
                g.exec("drop view poolview3");
                g.exec("drop view poolview2");
                g.exec("drop view poolview");
                g.exec("drop view bewview3");
                g.exec("drop view bewview2");
                g.exec("drop view bewview");
                g.exec("drop view stammview");
                g.exec("drop view stammview2");
                g.exec("drop view stammview3");
                g.exec("drop view stammview4");
                g.exec("drop view stammview5");
                g.exec("drop view befehl2");
                if (iVer>5090)
                {
                  //g.exec("drop view abschluss2");
                  g.exec("drop view darstellung2");
                }
                g.bISQL = false;
                int i=0;
                if (!g.ASA())
                {
                	g.bISQL = true;
                  if (g.MySQL() && !SQL.exists(g, "select table_name from information_schema.views where table_schema=database() and table_name='zr'"))
                  {
                	  g.exec("drop table zr");
                	  g.exec("drop table stammview");
                	  g.exec("drop table stammview2");
                	  g.exec("drop table stammview3");
                	  g.exec("drop table stammview4");
                	  g.exec("drop table stammview5");
                	  g.exec("drop table bewview");
                	  g.exec("drop table bewview2");
                	  g.exec("drop table bewview3");
                	  g.exec("drop table poolview");
                	  g.exec("drop table poolview2");
                	  g.exec("drop table poolview3");
                	  g.exec("drop table poolview4");
                	  g.exec("drop table poolview5");
                	  g.exec("drop table befehl2");
                	  g.exec("drop table darstellung2");
                  }
                  else
                	  g.exec("drop view zr");
                  	  
                  g.bISQL = false;
                  g.exec("create view zr as select von,bis"/*+(iVer>5140 ? ",vonInt,bisInt":"")*/+" from bereich where connid="+
                         (g.Oracle()?"sys_context('userenv','sessionid')":g.MS()?"@@spid":g.MySQL()?"CONNECTION_ID()":"sessionid"));
                  i++;
                }
                if (iVer>5060)
                {
                  g.exec("create view befehl2 as select * from befehl where def_aic_defverlauf is null");
                  i++;
                }
                if (iVer>5090)
                {
                  //g.exec("create view abschluss2 as select * from abschluss where pro_aic_protokoll is null and (ab is null or ab<"+g.now()+")");
                  g.exec("create view darstellung2 as select * from darstellung where hide is null");
                  i++;
                }
                //String sVon=g.ASA() ? "von":"(select von from zr)";
                //String sBis=g.ASA() ? "bis":"(select bis from zr)";
                String sSVx=(iVer>5060 ?",ab":"")+(iVer>5080 ? ",firma":"")+(iVer>5110 ? ",anr":"");
                if (iVer>5120)
                {
                	g.exec("create view stammview2 as select aic_stamm,kennung2 kennung,bezeichnung,aic_stammtyp2 aic_stammtyp,aic_mandant,ab,firma,anr,eintritt,austritt,aic_rolle,sta_aic_stamm2 vg from stamm_protokoll"+
                			" where pro_aic_protokoll is null and weg is null");
                	g.exec("create view stammview as select aic_stamm,kennung2 kennung,bezeichnung,aic_stammtyp2 aic_stammtyp,aic_mandant,ab,firma,anr,eintritt,austritt,aic_rolle from stamm_protokoll"+
                			" where pro_aic_protokoll is null and ("+g.von()+" is null or austritt is null or austritt >= "+g.von()+") and ("+g.bis()+" is null or eintritt is null or eintritt < "+g.bis()+
                            ") and ("+g.bis()+" is null and weg is null or (ab is null or "+g.bis()+">ab) and (weg is null or "+g.bis()+"<=weg))");
                	g.exec("create view stammview4 as select aic_stamm,kennung2 kennung,bezeichnung,aic_stammtyp2 aic_stammtyp,aic_mandant,ab,firma,anr,eintritt,austritt,aic_rolle,aic_code from stamm_protokoll where weg is null");
                	g.exec("create view stammview3 as select aic_stamm,kennung2 kennung,bezeichnung,aic_stammtyp2 aic_stammtyp,aic_mandant,ab,firma,anr,eintritt,austritt,aic_rolle,aic_code from stamm_protokoll"+
                            " where ("+g.von()+" is null or austritt is null or austritt >= "+g.von()+") and ("+g.bis()+" is null or eintritt is null or eintritt < "+g.bis()+
                            ") and ("+g.bis()+" is null and weg is null or (ab is null or "+g.bis()+">ab) and (weg is null or "+g.bis()+"<=weg))");
                }
                else
                {
                  g.exec("create view stammview2 as select stamm.aic_stamm,kennung,bezeichnung,aic_stammtyp,aic_mandant"+sSVx+
                       ",eintritt,austritt,aic_rolle"+(iVer>5094 ?",sta_aic_stamm vg":"")+" from stamm join stamm_protokoll on stamm.aic_stamm=stamm_protokoll.aic_stamm"+
                       " where pro_aic_protokoll is null"+
                    (iVer>5060 ?" and weg is null":""));
                 if (iVer>5060)
                  g.exec("create view stammview as select stamm.aic_stamm,kennung,bezeichnung,aic_stammtyp,aic_mandant"+sSVx+
                         ",eintritt,austritt,aic_rolle from stamm join stamm_protokoll on stamm.aic_stamm=stamm_protokoll.aic_stamm"+
                         " where pro_aic_protokoll is null and ("+g.von()+" is null or austritt is null or austritt >= "+g.von()+") and ("+g.bis()+" is null or eintritt is null or eintritt < "+g.bis()+
                         ") and ("+g.bis()+" is null and weg is null or (ab is null or "+g.bis()+">ab) and (weg is null or "+g.bis()+"<=weg))");
                 else
                  g.exec("create view stammview as select * from stammview2 where ("+g.von()+" is null or austritt is null or austritt >= "+g.von()+") and ("+g.bis()+" is null or eintritt is null or eintritt < "+g.bis()+")");           
                 if (iVer>5080)
                 {
                  g.exec("create view stammview4 as select stamm.aic_stamm,kennung,bezeichnung,aic_stammtyp,aic_mandant"+sSVx+",eintritt,austritt,aic_rolle,aic_code"+
                         " from stamm join stamm_protokoll on stamm.aic_stamm=stamm_protokoll.aic_stamm where weg is null");
                  g.exec("create view stammview3 as select stamm.aic_stamm,kennung,bezeichnung,aic_stammtyp,aic_mandant"+sSVx+",eintritt,austritt,aic_rolle,aic_code from stamm join stamm_protokoll on stamm.aic_stamm=stamm_protokoll.aic_stamm"+
                      " where ("+g.von()+" is null or austritt is null or austritt >= "+g.von()+") and ("+g.bis()+" is null or eintritt is null or eintritt < "+g.bis()+
                      ") and ("+g.bis()+" is null and weg is null or (ab is null or "+g.bis()+">ab) and (weg is null or "+g.bis()+"<=weg))");
                  i+=2;
                 }
                }
                String sPlus=iVer<4076 ? "":",anr,ldate"+(iVer<5001 ? "":",BEW2_AIC_BEW_POOL")+(iVer<5021 ? "":",ZONE")+(iVer<5041 ? "":",ANR2,ANR3")+(iVer>5070?",ANR4,ANR5,ANR6,ANR7,ANR8,ANR9":"")+
                		(iVer>5110 ? ",LDATE2,LDATE3,BOOL1,BOOL2,AIC_PROTOKOLL":"")+(iVer>5140 ? ",LDateVon,LDateBis":"");
                g.exec("create view bewview as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant"+sPlus+" from bew_pool where pro_aic_protokoll is null and(gueltig is null or("+g.von()+" is null or "+g.von()+" <= gueltig) and("+g.bis()+" is null or "+g.bis()+" > gueltig))");
                g.exec("create view bewview2 as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant"+sPlus+" from bew_pool where pro_aic_protokoll is null");
                g.exec("create view bewview3 as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant,pro_aic_protokoll"+sPlus+" from bew_pool"+
                                 " where (gueltig is null or ("+g.von()+" is null or "+g.von()+" <= gueltig) and ("+g.bis()+" is null or "+g.bis()+" > gueltig))");
                g.exec("create view poolview as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von,aic_daten,aic_protokoll from stammpool where pro2_aic_protokoll is null and ("+g.bis()+" is null or gultig_von is null or gultig_von < "+g.bis()+") and (gueltig_bis is null or gueltig_bis >= "+g.bis()+")");
                g.exec("create view poolview5 as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von,aic_daten,aic_protokoll from stammpool where pro2_aic_protokoll is null and gultig_von >= "+g.von()+" and gultig_von < "+g.bis()+" and (gueltig_bis is null or gueltig_bis >= "+g.bis()+")");

                g.exec("create view poolview2 as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von,aic_daten,aic_protokoll from stammpool where pro2_aic_protokoll is null and gueltig_bis is null");
                g.exec("create view poolview4 as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von,aic_daten,s.aic_protokoll from stammpool s join protokoll p on s.aic_protokoll=p.aic_protokoll"+
                       " where pro2_aic_protokoll is null and gueltig_bis is null and timestamp>="+g.von()+" and timestamp<"+g.bis());
                String sHeute = g.today();
                g.exec("create view poolview3 as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von,aic_daten from stammpool where pro2_aic_protokoll is null and (gultig_von is null or gultig_von <= " +
        			  sHeute + ") and (gueltig_bis is null or gueltig_bis > " + sHeute + ")");
          	  	g.exec("create view stammview5 as select stamm.aic_stamm,"+(iVer>5120 ?"kennung2 ":"")+"kennung,bezeichnung,aic_stammtyp,aic_mandant"+sSVx+
          	  			",eintritt,austritt,aic_rolle,aic_code from stamm join stamm_protokoll on stamm.aic_stamm=stamm_protokoll.aic_stamm"+
          	  			" where pro_aic_protokoll is null and (austritt is null or austritt >= "+sHeute+") and (eintritt is null or eintritt <= "+sHeute+") and ((ab is null or "+sHeute+">=ab) and (weg is null or "+sHeute+"<=weg))");

                i+=13;
                //if (!g.MySQL())
                /*if (!Static.bStringX)
                {
                  g.exec("create view stringXview as select aic_stamm,aic_eigenschaft,aic_bew_pool,spalte_double,gultig_von,"+
                        "(case when spalte_double < 11 then(select spalte_stringsehrkurz from daten_StringSehrKurz where aic_daten = stammpool.aic_daten)"+
                                 " when spalte_double < 31 then(select spalte_stringkurz from daten_StringKurz where aic_daten = stammpool.aic_daten)"+
                        " when spalte_double < 61 then(select spalte_string60 from daten_String60 where aic_daten = stammpool.aic_daten)"+
                        " when spalte_double < 256 then(select spalte_stringLang from daten_StringLang where aic_daten = stammpool.aic_daten)"+
                        " else(select spalte_text from daten_text where aic_daten = stammpool.aic_daten) end) as StringX"+
                        " from stammpool where aic_daten is not null and spalte_double > 0 and pro2_aic_protokoll is null and("+g.bis()+" is null or gultig_von is null or gultig_von < "+g.bis()+") and(gueltig_bis is null or gueltig_bis >= "+g.bis()+")");
                  g.exec("create view stringXview2 as select aic_stamm,aic_eigenschaft,aic_bew_pool,spalte_double,gultig_von,(case"+
                                 " when spalte_double<11 then (select spalte_stringsehrkurz from daten_StringSehrKurz where aic_daten=stammpool.aic_daten)"+
                                 " when spalte_double<31 then (select spalte_stringkurz from daten_StringKurz where aic_daten=stammpool.aic_daten)"+
                                 " when spalte_double<61 then (select spalte_string60 from daten_String60 where aic_daten=stammpool.aic_daten)"+
                                 " when spalte_double<256 then (select spalte_stringLang from daten_StringLang where aic_daten=stammpool.aic_daten)"+
                                 " else (select spalte_text from daten_text where aic_daten=stammpool.aic_daten) end) StringX from stammpool where aic_daten is not null and spalte_double>0"+
                                 " and pro2_aic_protokoll is null and gueltig_bis is null");
                  g.exec("create view stringXview4 as select aic_stamm,aic_eigenschaft,aic_bew_pool,spalte_double,gultig_von,(case"+
                                 " when spalte_double<11 then (select spalte_stringsehrkurz from daten_StringSehrKurz where aic_daten=s.aic_daten)"+
                                 " when spalte_double<31 then (select spalte_stringkurz from daten_StringKurz where aic_daten=s.aic_daten)"+
                                 " when spalte_double<61 then (select spalte_string60 from daten_String60 where aic_daten=s.aic_daten)"+
                                 " when spalte_double<256 then (select spalte_stringLang from daten_StringLang where aic_daten=s.aic_daten)"+
                                 " else (select spalte_text from daten_text where aic_daten=s.aic_daten) end) StringX from stammpool s join protokoll p on s.aic_protokoll=p.aic_protokoll where aic_daten is not null and spalte_double>0"+
                                 " and pro2_aic_protokoll is null and gueltig_bis is null and timestamp>="+g.von()+" and timestamp<"+g.bis());
                  i+=3;
                }*/
                if (g.ASA())
                {
                  i+=Tab.getAnzahlElemente(null)+Tab2.getAnzahlElemente(null);
                  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                    g.exec(Tab.getM("view_def"));
                  //int iM=g.getMandant();
                  //if (iM==0)
                  //  iM=SQL.getInteger(g,"select "+g.int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Default'");
                  //String sMandant="_"+SQL.getString(g,"select kennung from mandant where aic_mandant="+iM);
                  for (Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
                  {
                    g.exec(Tab2.getM("view_def"));
                    //if (Tab2.getS("table_name").startsWith(sMandant))
                    //if (!Tab2.getS("table_name").startsWith("AUV"))
                    //  g.exec("grant SELECT ON "+Tab2.getS("table_name")+" TO "+sMandant);
                  }
                }
                g.clockInfo("Views erneuert",lClock);
                return i;
        }
  }

  /*private boolean MonatUmkopieren()
	{
		int iTabBegriff=p.getInteger("select aic_tabellenname from tabellenname where kennung='Begriff'");
		int iTabCode=p.getInteger("select aic_tabellenname from tabellenname where kennung='Code'");
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,g.aic_begriffgruppe,begriff.kennung from begriff join begriffgruppe as g where g.kennung in ('Monat','Berechnete Feiertage')",true);
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			int iAlt=Tab.getI("aic_begriff");
			int iGruppe=Tab.getI("aic_begriffgruppe");
			p.clear();
			p.add("aic_begriffgruppe",iGruppe);
			p.add("kennung",Tab.getS("kennung"));
			int iNeu=p.insert("Code");
			p.exec("update feiertag set aic_code="+iNeu+",aic_begriff=null where aic_begriff="+iAlt);
			p.exec("update bezeichnung set aic_tabellenname="+iTabCode+",aic_fremd="+iNeu+" where aic_tabellenname="+iTabBegriff+" and aic_fremd="+iAlt);
			p.exec("delete from begriff where aic_begriff="+iAlt);
			g.printInfo("Umkopieren:"+Tab.getS("kennung"));
		}
		p.exec("update begriffgruppe set code=1 where kennung in ('Monat','Berechnete Feiertage')");
		return true;
	}*/

	/*private boolean BegriffeUmkopieren(String s)
	{
		int iTabBegriff=p.getInteger("select aic_tabellenname from tabellenname where kennung='Begriff'");
		int iTabCode=p.getInteger("select aic_tabellenname from tabellenname where kennung='Code'");
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,g.aic_begriffgruppe,begriff.kennung from begriff join begriffgruppe as g where g.kennung in ('"+(s.equals("Monat")?"Monat','Berechnete Feiertage":s)+"')",true);
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			int iAlt=Tab.getI("aic_begriff");
			int iGruppe=Tab.getI("aic_begriffgruppe");
			p.clear();
			p.add("aic_begriffgruppe",iGruppe);
			p.add("kennung",Tab.getS("kennung"));
			int iNeu=p.insert("Code");

			if (s.equals("Monat"))
				p.exec("update feiertag set aic_code="+iNeu+",aic_begriff=null where aic_begriff="+iAlt);
			else if (s.equals("Status"))
				p.exec("update Stamm_protokoll set aic_code="+iNeu+",aic_begriff=null where aic_begriff="+iAlt);
			else if (s.equals("Formulartyp"))
				p.exec("update Begriff set aic_code="+iNeu+",beg_aic_begriff=null where beg_aic_begriff="+iAlt);

			p.exec("update bezeichnung set aic_tabellenname="+iTabCode+",aic_fremd="+iNeu+" where aic_tabellenname="+iTabBegriff+" and aic_fremd="+iAlt);
			p.exec("delete from begriff where aic_begriff="+iAlt);
			g.debugInfo("Umkopieren:"+Tab.getS("kennung"));
		}
		p.exec("update begriffgruppe set code=1 where kennung in ('"+(s.equals("Monat")?"Monat','Berechnete Feiertage":s)+"')");
		g.fixInfo(s+" von Begriff nach Code kopiert");
		return true;
	}

	private boolean BegriffgruppeLoeschen(String s)
	{
		int iGruppe=p.getInteger("select aic_begriffgruppe from begriffgruppe where kennung='"+s+"'");
		p.exec("delete from begriff where aic_begriffgruppe="+iGruppe);
		p.exec("delete from begriffgruppe where aic_begriffgruppe="+iGruppe);
		g.fixInfo("Begriffgruppe "+s+" gelöscht!");
		return true;
	}*/


