package All_Unlimited.Grunddefinition;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Hauptmaske.Zeitraum;
import All_Unlimited.Allgemein.Message;
import jclass.bwt.JCOutlinerNodeStyle;
import jclass.bwt.JCOutlinerComponent;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: All Unlimited</p>
 *
 * <p>Copyright: Copyright (c) 2003</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 3.12
 */
public class Logging extends Formular
{

  private Global g;
  private static Logging Self=null;
  private AUOutliner Out;
  private Text EdtSuche;
  private static final int VERLAUF=1;
  private static final int DEFVERLAUF=2;
  private static final int FEHLER=3;

  public static Logging get(Global rg)
  {
      if (Self==null)
    	  new Logging(rg);
      Self.show();
      return Self;
  }

  public static void free()
  {
    if (Self!=null)
    {
      Self.g.winInfo("Logging.free");
      Self.thisFrame.dispose();
      Self=null;
    }
  }

  public void show()
  {
        Refresh();
        super.show();
  }

  public Logging(Global rg)
  {
    super("Logging", null, rg);
    g = rg;
    Self=this;
    g.winInfo("Logging.create");
    Out=new AUOutliner();
    Out.setRootNode(new JCOutlinerFolderNode(""));
    Out.setColumnLabelSortMethod(Sort.sortMethod);
    Out.setRootVisible(false);
    //Out.setFont(g.fontBezeichnung);
    String[] sAry=new String[] {"Wann","Anzahl","Def","Fehler","HangUp","Wo","Was","Wer","Mandant","Version","PlugIn","OS","Datenbank","Aic"};
    for (int i=0;i<sAry.length;i++)
      sAry[i]=g.getBegriffS("Show", sAry[i]);
    Out.setColumnButtons(new jclass.util.JCVector(sAry));
    Out.setNumColumns(sAry.length);
    //Out.setRootNode(new JCOutlinerFolderNode("Stammtyp - Stamm"));
    JPanel Pnl_Outliner = getFrei("Outliner");
    if (Pnl_Outliner != null)
      Pnl_Outliner.add(Out);
    JPanel Pnl_Suche = getFrei("Suche");
    EdtSuche=new Text("",30); 
    if (Pnl_Suche != null)
    	Pnl_Suche.add(EdtSuche);

    ActionListener AL = new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String s = e.getActionCommand();
        g.progInfo("Logging.Action="+s);
        if (s.equals("Refresh"))
          Refresh();
        else if (s.equals("Zeitraum"))
          Zeitraum.get(g).show();
        else if (s.equals("Statistik"))
          showBenutzer();
        else if (s.equals("Connects"))
            showConnects();
        //else if (s.equals("Lost"))
        //  showLost();
        else if (s.equals("Verlauf"))
          show(VERLAUF);
        else if (s.equals("DefVerlauf"))
          show(DEFVERLAUF);
        else if (s.equals("Fehler"))
          show(FEHLER);
        else if (s.equals("Undo"))
          Undo();
        else if (s.equals("Druck"))
          Out.print();
        else
          Static.printError("Logging.AL:"+s+" wird nicht unterstützt");
      }
    };
    g.BtnAdd(getButton("Refresh"),"Refresh",AL);
    g.BtnAdd(getButton("Zeitraum"),"Zeitraum",AL);
    g.BtnAdd(getButton("Statistik"),"Statistik",AL);
    //g.BtnAdd(getButton("Lost"),"Lost",AL);
    g.BtnAdd(getButton("Verlauf"),"Verlauf",AL);
    g.BtnAdd(getButton("DefVerlauf"),"DefVerlauf",AL);
    g.BtnAdd(getButton("Fehler"),"Fehler",AL);
    g.BtnAdd(getButton("Undo"),"Undo",AL);
    g.BtnAdd(getButton("Druck"),"Druck",AL);
    g.BtnAdd(getButton("Connects"),"Connects",AL);

    JButton BtnBeenden = getButton("Beenden");
    Action cancelKeyAction = new AbstractAction()
        {
          /**
			 *
			 */
			private static final long serialVersionUID = 4916136589749366544L;

		public void actionPerformed(ActionEvent e) {
            hide();
          }
        };
    Static.addActionListener(BtnBeenden,cancelKeyAction);
    Static.Escape(BtnBeenden,thisFrame,cancelKeyAction);

    Out.addItemListener(new JCOutlinerListener()
                {
                        public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
                        {
                                JCOutlinerFolderNode folder = (JCOutlinerFolderNode) ev.getNode();
                                AUOutliner outliner = (AUOutliner) ev.getSource();

                                if (folder == outliner.getRootNode())
                                        return;
                                else if (ev.getNewState() == BWTEnum.FOLDER_OPEN_ALL)
                                {
                                        if (folder.getChildren() == null)
                                        {
                                                //int i=((Integer)((Vector)folder.getUserData()).elementAt(1)).intValue();
                                                fillGid(folder);
                                        }
                                }
                                else if (ev.getNewState() == BWTEnum.FOLDER_CLOSED)
                                {
                                }

                        }
                        public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
                        {
                                //Out.sortByColumn(0,Sort.sortMethod);
                        }

                        public void outlinerNodeSelectBegin(JCOutlinerEvent ev) {}
                        public void outlinerNodeSelectEnd(JCOutlinerEvent ev) {}

                        public void itemStateChanged(JCItemEvent ev)
                        {
                                if(ev.getStateChange() == ItemEvent.SELECTED)
                                {
                                        //EnableButtons();
                                }
                        }
                });

  }
  
  private String getStatus(int i)
  {
	  if (i==Transact.ABSTURZ)
		  return "Absturz";
	  else if (i==Transact.AUTOLOGOUT)
		  return "Autologout";
	  else if (i==Transact.MANUELL)
		  return "manuell";
	  else if (i==Transact.NO_ZR)
		  return "kein Zeitraum";
	  else if (i==Transact.TOKEN)
		  return "Token abgelaufen";
	  else if (i==Transact.WIEDER)
		  return "wiedereinloggen";
	  else if (i==Transact.STARTFOM)
		  return "kein Startformular";
	  else
		  return "unbekannt "+i;
  }

  private void fillGid(JCOutlinerFolderNode Nod)
  {
    String sMandant=g.AllUnlimited() ? "":" and l.aic_mandant="+g.getMandant();//+Static.SQL_in(g.VecMandantWrite);
    //g.progInfo("sMandant="+sMandant);
    String sPlus=g.Def() ? "":"ein>"+g.DateTimeToString(g.getBeginn())+" and ";
    JCOutlinerNodeStyle StyDel=null;
    JCOutlinerNodeStyle StyWeb=null;
    JCOutlinerNodeStyle StyAdmin=null;
    int iLevel=Nod==null ? -1:Nod.getLevel();
    if (iLevel>0 && iLevel<5)
    {
      Tabellenspeicher Tab=null;
      if (iLevel==1)
      {
        String s=""+Nod.getUserData();
        String s2 = g.Oracle() ? "to_char(ein,'YYYYMM')" : "year(ein)*100+month(ein)";
        String s3 = g.Oracle() ? "to_char(ein,'YYYY')='" + s + "'" : "year(ein)=" + s;
        Tab = new Tabellenspeicher(g, "select distinct " + s2 + " zeit from logging l where " + sPlus + s3 +sMandant+ " order by zeit desc", true);
      }
      else if (iLevel==2)
      {
        String s=""+Nod.getUserData();
        String s2 = g.Oracle() ? "to_char(ein,'YYYYMMDD')" : "year(ein)*10000+month(ein)*100+day(ein)";
        String s3 = g.Oracle() ? "to_char(ein,'YYYYMM')='" + s + "'" : "year(ein)*100+month(ein)=" + s;
        Tab = new Tabellenspeicher(g, "select zeit,sum(A) Anzahl,sum(D) Def,sum(F) Fehler,sum(H) HU from (select " + s2 + " zeit"+
                                   ",(select count(*) from protokoll where aic_logging=l.aic_logging) A"+
                                   ",(select count(*) from defverlauf where aic_logging=l.aic_logging) D"+
                                   ",(select count(*) from Fehler where aic_logging=l.aic_logging) F"+
                                   ",(select count(*) from Logging where aic_logging=l.aic_logging and status&7>0) H"+
                                   " from logging l where " + sPlus + s3 +sMandant+ ") x group by zeit order by zeit desc", true);
      }
      else if (iLevel==3)
      {
    	if (StyDel==null)
    	{
	        StyDel = new JCOutlinerComponent().getDefaultNodeStyle();
	        StyDel.setForeground(g.ColLoeschen);
	        StyWeb = new JCOutlinerComponent().getDefaultNodeStyle();
	        StyWeb.setForeground(g.ColWeb);
	        StyAdmin = new JCOutlinerComponent().getDefaultNodeStyle();
	        StyAdmin.setForeground(g.ColAdmin);
    	}
        String s=""+Nod.getUserData();
        String s2 = g.Oracle() ? "to_char(ein,'YYYYMMDD')='" + s + "'" : "year(ein)*10000+month(ein)*100+day(ein)="+s;
        Tab = new Tabellenspeicher(g, "select aic_logging,ein,aus,mom,status,(select kennung from computer where aic_computer=l.aic_computer) Computer"+
                                   ",(select kennung from code where aic_code=l.aic_code) Anlage"+
                                   ",(select kennung from benutzer where aic_benutzer=l.aic_benutzer) Benutzer"+
                                   ",(select kennung from Mandant where aic_Mandant=l.aic_Mandant) Mandant"+
                                   ",(select count(*) from protokoll where aic_logging=l.aic_logging) Anzahl"+
                                   ",(select count(*) from defverlauf where aic_logging=l.aic_logging) Def"+
                                   ",(select count(*) from Fehler where aic_logging=l.aic_logging) Fehler"+
                                   ",Version,(select java_version from systeminfo where aic_systeminfo=l.aic_systeminfo) Java"+
                                   ",(select Betriebssystem from Systeminfo where aic_systeminfo=l.aic_systeminfo) Betriebssystem"+
                                   ",(select DB_Version from Systeminfo where aic_systeminfo=l.aic_systeminfo) DB"+
                                   " from logging l where "+s2+sMandant+" order by ein desc", true);
      }
      else if (iLevel==4)
      {
        Tab = new Tabellenspeicher(g,"select aic_protokoll,timestamp,p.aic_code Anlage"+
                                   ",(select kennung from benutzer where aic_benutzer=p.aic_benutzer) Benutzer"+
                                   ",(select count(*) from stammpool where aic_protokoll=p.aic_protokoll)+(select count(*) from stammpool where pro2_aic_protokoll=p.aic_protokoll)+"+
                                   "(select count(*) from stamm_protokoll where aic_protokoll=p.aic_protokoll)+(select count(*) from stamm_protokoll where pro_aic_protokoll=p.aic_protokoll)+"+
                                   "(select count(*) from bew_pool where aic_protokoll=p.aic_protokoll)+(select count(*) from zwischenspeicher where aic_protokoll=p.aic_protokoll) Anzahl"+
                                   ",place from protokoll p where aic_logging="+Nod.getUserData()+" order by aic_protokoll",true);
      }
      //JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
      //JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
      //StyFolder.setFont(g.fontStandard);
      for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
      {
        JCOutlinerFolderNode Nod2 = null;
        if (iLevel==1)
        {
          String s=Tab.getS("Zeit");
          Nod2 = new JCOutlinerFolderNode(DateWOD.DFS.getMonths()[Integer.parseInt(s.substring(4))-1]+" "+s.substring(0,4),Nod);
          Nod2.setUserData(Tab.getS("Zeit"));
        }
        else if (iLevel==2)
        {
          String s=Tab.getS("Zeit");
          Vector<Object> Vec=new Vector<Object>();
          Vec.addElement(s.substring(6)+"."+s.substring(4,6)+"."+s.substring(0,4));
          Vec.addElement(Tab.getInt("Anzahl"));
          Vec.addElement(Tab.getInt("Def"));
          Vec.addElement(Tab.getInt("Fehler"));
          Vec.addElement(Tab.getInt("HU"));
          Nod2 = new JCOutlinerFolderNode(Vec, Nod);
          Nod2.setUserData(Tab.getS("Zeit"));
        }
        else if (iLevel==3)
        {
          Vector<Object> Vec=new Vector<Object>();
          Vec.addElement(new VonBis(g,Tab.getTimestamp("ein"),Tab.getTimestamp("aus"),"dd.MMM HH:mm"));
          Vec.addElement(Tab.getInt("Anzahl"));
          Vec.addElement(Tab.getInt("Def"));
          Vec.addElement(Tab.getInt("Fehler"));
          Vec.addElement((Tab.getI("Status")&7)>0 ? getStatus(Tab.getI("Status")&7):""); //TODO Status aufschlüsseln
          Vec.addElement(Tab.getS("Computer"));
          Vec.addElement(Tab.getS("Anlage"));
          Vec.addElement(Tab.getS("Benutzer"));
          Vec.addElement(Tab.getS("Mandant"));
          Vec.addElement(Version.getS(Tab.getI("Version")));
          Vec.addElement(Tab.getS("Java"));
          Vec.addElement(Tab.getS("Betriebssystem"));
          Vec.addElement(Tab.getS("DB"));
          Vec.addElement(Tab.getInt("aic_logging"));
          Nod2=new JCOutlinerFolderNode((Object)Vec,Nod);
          Nod2.setUserData(Tab.getInt("aic_logging"));
          //if (Static.Gleich(Tab.getTimestamp("aus"),Tab.getTimestamp("mom")) && (Tab.isNull("Anlage") || !Tab.getS("Anlage").equals("SoftTerm")))       
          if ((Tab.getI("Status")&7)>0)
              Nod2.setStyle(StyDel);
          else if ((Tab.getI("Status")&Transact.WEBLOG)>0)
        	Nod2.setStyle(StyWeb);
          else if ((Tab.getI("Status")&Transact.ADMIN)>0)
          	Nod2.setStyle(StyAdmin);
        }
        else if (iLevel==4)
        {
          Vector<Object> Vec=new Vector<Object>();
          Vec.addElement(new Zeit(Tab.getTimestamp("timestamp"),null));
          Vec.addElement(Tab.getInt("Anzahl"));
          Vec.addElement(null);
          Vec.addElement(null);
          Vec.addElement(null);
          Vec.addElement(g.getBegBez(Tab.getI("Place")));
          Vec.addElement(Tab.isNull("Anlage")? Static.sLeer:g.TabCodes.getBezeichnungS(Tab.getI("Anlage")));
          Vec.addElement(Tab.getS("Benutzer"));
          Vec.addElement(null);
          Vec.addElement(null);
          Vec.addElement(null);
          Vec.addElement(null);
          Vec.addElement(null);
          Vec.addElement(Tab.getInt("aic_protokoll"));
          Nod2=new JCOutlinerFolderNode((Object)Vec,Nod);
          Nod2.setUserData(Tab.getInt("aic_protokoll"));
        }
        Nod2.setState(BWTEnum.FOLDER_CLOSED);
        //Nod2.setStyle(StyFolder);
        //Nod.setUserData(new JCVector(new Object[] {Tab.getInhalt("AIC_Computer"),Tab.getInhalt("CBITS")}));
      }
      Out.folderChanged(Nod);
    }
    if (iLevel==5)
    {
      // Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Tabelle","Aic","Typ","Stamm","Mandant","ANR","ANR2","ANR3","von","bis","Eigenschaft","erfasst","gelöscht","Zahl","Daten","Farbe"});
      // int iProt=Tabellenspeicher.geti(Nod.getUserData());
      // check(Tab,"Zwischenspeicher",iProt,1);
      // check(Tab,"Zwischenspeicher",iProt,2);
      // check(Tab,"Bew_Pool",iProt,2);
      // check(Tab,"Bew_Pool",iProt,1);
      // check(Tab,"Stamm_Protokoll",iProt,2);
      // check(Tab,"Stamm_Protokoll",iProt,1);
      // check(Tab,"Stammpool",iProt,3);
      // check(Tab,"Stammpool",iProt,2);
      // check(Tab,"Stammpool",iProt,1);
      // check(Tab,"Abschluss",iProt,1);
      // check(Tab,"Abschluss",iProt,2);
      // Tab.showGrid("Protokoll="+Nod.getUserData());
      ProtInfo(g,Tabellenspeicher.geti(Nod.getUserData()),thisJFrame());
      //g.fixInfo("Protokoll="+Nod.getUserData());
    }
  }

  public static void ProtInfo(Global g,int iProt,Window Fom)
  {
    Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Tabelle","Aic","Typ","Stamm","Mandant","ANR","ANR2","ANR3","von","bis","Eigenschaft","erfasst","gelöscht","Zahl","Daten","Farbe"});
      // int iProt=Tabellenspeicher.geti(Nod.getUserData());
      check(g,Tab,"Zwischenspeicher",iProt,1);
      check(g,Tab,"Zwischenspeicher",iProt,2);
      check(g,Tab,"Bew_Pool",iProt,2);
      check(g,Tab,"Bew_Pool",iProt,1);
      check(g,Tab,"Stamm_Protokoll",iProt,2);
      check(g,Tab,"Stamm_Protokoll",iProt,1);
      check(g,Tab,"Stammpool",iProt,3);
      check(g,Tab,"Stammpool",iProt,2);
      check(g,Tab,"Stammpool",iProt,1);
      check(g,Tab,"Abschluss",iProt,1);
      check(g,Tab,"Abschluss",iProt,2);
      Tab.showGrid("Protokoll="+iProt,Fom);
  }

  private static void check(Global g,Tabellenspeicher Tab,String s,int iProt,int iAnz)
  {
    String sProt="="+iProt;
    boolean bLog=iProt<0;
    if (bLog)
      sProt=Static.SQL_in(SQL.getVector("select aic_protokoll from protokoll where aic_logging="+(-iProt), g));
    Tabellenspeicher TabD1=null;
    int i=0;
    //int i2=0;
    String sSpProt=iAnz==1 ? "aic_protokoll":iAnz==2 ? "pro_aic_protokoll":iAnz==3 ? "pro2_aic_protokoll":"";
    if (s.equals("Stammpool"))
    {
      /*TabD1=new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("stammtyp","v")+" Typ,bezeichnung,count(*) Anzahl"+
                                 ",(select count(*) from stammpool where aic_stamm=p.aic_stamm and pro2_aic_protokoll="+iProt+") del"+
                                 " from stammview2 v join stammpool p on v.aic_stamm=p.aic_stamm and aic_rolle is null"+
                                 " where aic_protokoll="+iProt+" group by aic_stammtyp,bezeichnung",true);*/
      String s2="select "+g.AU_Bezeichnungo("stammtyp","s")+" Typ,(select bezeichnung from stammview2 where aic_stamm=p.aic_stamm and aic_rolle is null) bezeichnung,"+
                                 g.AU_Bezeichnungo("Eigenschaft","p")+" Eigenschaft, p.gultig_von Datum,p.gueltig_bis Datum2,aic_stammpool aic,pro2_aic_protokoll del,spalte_double zahl,"+//aic_daten Daten"+
                                 g.isnull()+"(select spalte_stringx from daten_stringx where aic_daten=p.aic_daten),(select spalte_text from daten_text where aic_daten=p.aic_daten)) Daten"+
                                 " from stamm s join stammpool p on s.aic_stamm=p.aic_stamm where "+sSpProt+sProt;
      //g.fixtestInfo(s2);
                                 TabD1=new Tabellenspeicher(g,s2,true);
      TabD1.addTab(new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("Bewegungstyp","b")+" Typ,v.bezeichnung,"+
                                        g.AU_Bezeichnungo("Eigenschaft","p")+" Eigenschaft, p.gultig_von Datum,p.gueltig_bis Datum2,aic_stammpool aic,pro2_aic_protokoll del,spalte_double zahl,"+//aic_daten Daten"+
                                        g.isnull()+"(select spalte_stringx from daten_stringx where aic_daten=p.aic_daten),(select spalte_text from daten_text where aic_daten=p.aic_daten)) Daten"+
                                 " from stammview2 v right outer join bew_pool b on v.aic_stamm=b.anr and v.aic_rolle is null join stammpool p on b.aic_bew_pool=p.aic_bew_pool"+
                                 " where p."+sSpProt+sProt,true));
      i=TabD1.getAnzahlElemente(null);
      //TabD1.showGrid("Stammpool");
    }
    else if (s.equals("Bew_Pool"))
    {
      /*TabD1 = new Tabellenspeicher(g, "select "+g.AU_Bezeichnungo("bewegungstyp","p")+" Typ,bezeichnung,count(*) Anzahl"+
                                   ",(select count(*) from bew_pool where aic_bewegungstyp=p.aic_bewegungstyp and aic_stamm=p.anr and pro_aic_protokoll="+iProt+") del"+
                                   " from stammview2 v join bew_pool p on v.aic_stamm=p.anr and aic_rolle is null" +
                                   " where aic_protokoll="+iProt+" group by aic_bewegungstyp,bezeichnung", true);*/
      TabD1 = new Tabellenspeicher(g, "select "+g.AU_Bezeichnungo("bewegungstyp","p")+" Typ,ANR,ANR2,ANR3,'*Bew*' Eigenschaft,p.aic_mandant,p.gueltig Datum,aic_bew_pool aic,pro_aic_protokoll del"+
                                   " from bew_pool p where "+sSpProt+sProt,true);
                                   //" from stammview2 v right outer join bew_pool p on v.aic_stamm=p.anr and aic_rolle is null where "+sSpProt+"="+iProt,true);
      //select (SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname=43 AND AIC_Fremd=p.AIC_bewegungstyp AND AIC_Sprache=1) Typ,bezeichnung from stammview2 v right outer join bew_pool p on v.aic_stamm=p.anr and aic_rolle is null where aic_protokoll=58917
      i=TabD1.getAnzahlElemente(null);
    }
    else if (s.equals("Stamm_Protokoll"))
    {
      TabD1=new Tabellenspeicher(g,"select aic_stammtyp,aic_rolle,p.bezeichnung,'Bez.,Ein-,Austritt' Eigenschaft,p.aic_mandant,p.Eintritt Datum,p.Austritt Datum2,v.aic_stamm aic,pro_aic_protokoll del,anr"+
                                 " from stamm v join Stamm_Protokoll p on v.aic_stamm=p.aic_stamm"+
                                 " where "+sSpProt+sProt,true);
      i=TabD1.getAnzahlElemente(null);
    }
    else if (s.equals("Zwischenspeicher"))
    {
      TabD1=new Tabellenspeicher(g,"select aic_zwischenspeicher aic,kennung Typ,Terminal Bezeichnung,Zwischentext Daten,aic_mandant,Gueltig Datum,pro_aic_protokoll del"+
                                 " from Zwischenspeicher where "+sSpProt+sProt+" order by aic_zwischenspeicher",true);
      i=TabD1.getAnzahlElemente(null);
    }
    else if (s.equals("Abschluss"))
    {
      TabD1=new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("abschlusstyp","abschluss")+" Typ,aic_abschluss aic,Datum,'*Sperre*' Eigenschaft,aic_mandant,pro_aic_protokoll del from abschluss where "+sSpProt+sProt,true);
      i=TabD1.getAnzahlElemente(null);
    }
    else
    {
      Static.printError("Logging: Tabelle "+s+" wird noch nicht unterstützt");
      //i = SQL.getInteger(g, "select count(*) from " + s + " where aic_protokoll=" + iProt);
      //i2 = SQL.getInteger(g, "select count(*) from " + s + " where "+sSpProt+"=" + iProt);
    }
    if (i>0)// || i2>0)
    {
      if (TabD1 != null)
        for (TabD1.moveFirst();!TabD1.eof();TabD1.moveNext())
        {
          Tab.newLine();
          Tab.setInhalt("Tabelle", s);
          Tab.setInhalt("Typ", s.equals("Stamm_Protokoll") ? TabD1.getI("aic_rolle")>0?g.TabRollen.getBezeichnungS(TabD1.getI("aic_rolle")):
                        g.TabStammtypen.getBezeichnungS(TabD1.getI("aic_stammtyp")):TabD1.getS("Typ"));
          if (TabD1.exists("bezeichnung"))
            Tab.setInhalt("Stamm", TabD1.getS("bezeichnung"));
          if (TabD1.exists("aic_mandant"))
            Tab.setInhalt("Mandant",g.TabMandanten.getBezeichnungS(TabD1.getI("aic_mandant")));
          if (TabD1.exists("ANR") && TabD1.getI("ANR")>0)
            Tab.setInhalt("ANR",g.getStamm(TabD1.getI("ANR")));
          if (TabD1.exists("ANR2") && TabD1.getI("ANR2")>0)
            Tab.setInhalt("ANR2",g.getStamm(TabD1.getI("ANR2")));
          if (TabD1.exists("ANR3") && TabD1.getI("ANR3")>0)
            Tab.setInhalt("ANR3",g.getStamm(TabD1.getI("ANR3")));
          if (TabD1.exists("Eigenschaft"))
            Tab.setInhalt("Eigenschaft", TabD1.getS("Eigenschaft"));
            Tab.setInhalt("Aic", TabD1.getI("Aic"));
            Tab.setInhalt("von", TabD1.getInhalt("Datum"));
            if (TabD1.exists("Datum2"))
              Tab.setInhalt("bis", TabD1.getInhalt("Datum2"));
            Tab.setInhalt(iAnz==1 ? "erfasst":"gelöscht", 1);
            if (TabD1.exists("Zahl"))
              Tab.setInhalt("Zahl",TabD1.getS("Zahl"));
            if (TabD1.exists("Daten"))
              Tab.setInhalt("Daten",TabD1.getM("Daten"));
          //}
          //else
          //{
            //Tab.setInhalt("erfasst", TabD1.getI("Anzahl"));
            //Tab.setInhalt("gelöscht", TabD1.getI("Del"));
          //}
          if (TabD1.exists("del"))
            Tab.setInhalt("Farbe",TabD1.isNull("del")? 0:Global.ColLoeschen.getRGB());
        }
      else
      {
        Static.printError("Logging: Tabelle "+s+" ohne Daten");
        /*Tab.newLine();
        Tab.setInhalt("Tabelle", s);
        Tab.setInhalt("erfasst", i);*/
        //Tab.setInhalt("gelöscht", i2);
      }
    }
  }

  private void show(int iArt)
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    int iLevel=Nod==null ? -1:Nod.getLevel();
    String sArt=iArt==VERLAUF ? "Verlauf":iArt==DEFVERLAUF?"DefVerlauf":iArt==FEHLER?"Fehler":"";
    String s=EdtSuche.isNull() ? null:EdtSuche.getText();
    int iAic=iLevel==4 ? Tabellenspeicher.geti(Nod.getUserData()):0;
    String sSQL=iArt==VERLAUF ? "select timestamp,fertig,abbruch,dauer,begriffgruppe.kennung,defbezeichnung,Bemerkung"+g.AU_Bezeichnung1("Rolle", "Verlauf")+" Rolle"+
                           ",(select bezeichnung from stammview2 where aic_stamm=verlauf.aic_stamm and aic_rolle is null) Stamm,verlauf.aic_stamm,von,bis,verlauf.aic_begriff"+(iAic==0 ? ",verlauf.aic_logging":"")+
                           " from verlauf"+g.join("begriff","verlauf")+g.join("begriffgruppe","begriff"):
                           iArt==DEFVERLAUF ? "select timestamp,begriffgruppe.kennung Gruppe,defbezeichnung,tat"+(iAic==0 ? ",defverlauf.aic_logging":"")+
                           " from defverlauf"+g.join("begriff","defverlauf")+g.join("begriffgruppe","begriff"):
                           iArt==FEHLER ? "select Timestamp,(select defbezeichnung from begriff where begriff.aic_begriff=fehler.aic_begriff) Begriff,Fehler"+(iAic==0 ? ",aic_logging":"")+" from fehler":"";
    String sZR="timestamp>="+g.DateTimeToString(g.getVon())+" and timestamp<"+g.DateTimeToString(g.getBis());
    String sWhere=" where "+(s==null && iAic>0 ? (iArt==VERLAUF ? "verlauf.":iArt==DEFVERLAUF ? "defverlauf.":"")+"aic_logging="+iAic:sZR);
    sSQL=sSQL+sWhere+(s==null ? " order by aic_"+sArt+" desc":"");
    if (s!=null)
    {
    	String sW=" like '%"+s+"%'";
    	sSQL="select * from ("+sSQL+") x where "+(iArt==FEHLER ? "Fehler":"defbezeichnung")+sW+(iArt==DEFVERLAUF ? " or tat"+sW:iArt==VERLAUF ? " or Stamm"+sW:"");
    }
    Tabellenspeicher Tab=new Tabellenspeicher(g,sSQL,true);
    for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
    	 if (Tab.exists("aic_stamm") && Tab.getI("aic_stamm")>0 && Tab.isNull("Stamm"))
             Tab.setInhalt("Stamm",g.getStamm(Tab.getI("aic_stamm")));
    if (iArt==VERLAUF || iArt==FEHLER && iAic==0)
    {
    	final JDialog FomGid = new JDialog((Frame)thisFrame, sArt+" "+(iAic>0 ? ""+iAic:g.getVonBis("dd.MM.yyyy",false)), false);
        AUOutliner Grid = new AUOutliner();
        FomGid.getContentPane().add("Center", Grid);
        Tab.showGrid(Grid);
        FomGid.pack();
        Static.centerComponent(FomGid,thisFrame);
        Grid.addActionListener(new JCActionListener()  {
            public void actionPerformed(JCActionEvent ev) {
              JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
              Vector Vec=(Vector)Nod.getLabel();
//              g.fixtestError("gewählt:"+Nod.getLabel());
              if (iArt==VERLAUF)
                Tsearch.Edit(g, 0, Sort.gets(Vec,4), Sort.geti(Vec,12), false);
              else
                ProtInfo(g,-Sort.geti(Vec,3),thisJFrame());
                // g.fixtestError("Logging="+Sort.geti(Vec,3));
            }
        });
        FomGid.setVisible(true);
    }
    // else if (iArt==FEHLER && iAic==0)
    // {

    // }
    else
    	Tab.showGrid(sArt+" "+(iAic>0 ? ""+iAic:g.getVonBis("dd.MM.yyyy",false)));
    /*if (iLevel==4)
    {
      int iAic=Tabellenspeicher.geti(Nod.getUserData());
      new Tabellenspeicher(g,iArt==VERLAUF ? "select timestamp,fertig,abbruch,dauer,begriffgruppe.kennung,defbezeichnung"+
                           ",(select bezeichnung from stammview2 where aic_stamm=verlauf.aic_stamm and aic_rolle is null) Stamm,von,bis"+
                           " from verlauf"+g.join("begriff","verlauf")+g.join("begriffgruppe","begriff")+" where verlauf.aic_logging="+iAic+" order by aic_verlauf":
                           iArt==DEFVERLAUF ? "select timestamp,begriffgruppe.kennung Gruppe,defbezeichnung,tat"+
                           " from defverlauf"+g.join("begriff","defverlauf")+g.join("begriffgruppe","begriff")+" where defverlauf.aic_logging="+iAic+" order by aic_defverlauf":
                           iArt==FEHLER ? "select Timestamp,(select defbezeichnung from begriff where begriff.aic_begriff=fehler.aic_begriff) Begriff,Fehler from fehler where aic_logging="+iAic+" order by aic_fehler":
                           "",true).showGrid((iArt==VERLAUF ? "Verlauf ":iArt==DEFVERLAUF?"DefVerlauf ":iArt==FEHLER?"Fehler ":"")+iAic);
    }
    else if (iArt==FEHLER)
      new Tabellenspeicher(g,"select Timestamp,(select defbezeichnung from begriff where begriff.aic_begriff=fehler.aic_begriff) Begriff,Fehler,aic_logging from fehler"+
                           " where timestamp>="+g.DateTimeToString(g.getVon())+" and timestamp<"+g.DateTimeToString(g.getBis())+" order by aic_fehler desc",true).showGrid("Fehler");*/
  }

  private void Undo()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    int iLevel=Nod==null ? -1:Nod.getLevel();
    String s=null;
    if (iLevel==5)
    {
      int iAic=Tabellenspeicher.geti(Nod.getUserData());
      int iAnz=SQL.getInteger(g,"select count(*) from bew_pool where aic_protokoll="+iAic+" and pro_aic_protokoll is null");
      if (iAnz>0)
      {
        int iProt=g.Protokoll("Entfernen",0);
        iAnz=g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_protokoll="+iAic+" and pro_aic_protokoll is null");
        if (iAnz>0)
          s=iAnz+" Bew_pool gelöscht";
      }
      iAnz=SQL.getInteger(g,"select count(*) from bew_pool where pro_aic_protokoll="+iAic);
      if (iAnz>0)
      {
        iAnz=g.exec("update bew_pool set pro_aic_protokoll=null where pro_aic_protokoll="+iAic);
        if (iAnz>0)
          s=(s==null?"":s+"\n")+iAnz+" Bew_pool wiederhergestellt";
      }
      iAnz=SQL.getInteger(g,"select count(*) from stamm_protokoll where aic_protokoll="+iAic+" and pro_aic_protokoll is null");
      if (iAnz>0)
      {
    	  int iProt=g.Protokoll("Entfernen",0);
    	  iAnz=g.exec("update stamm_protokoll set pro_aic_protokoll="+iProt+" where aic_protokoll="+iAic+" and pro_aic_protokoll is null");
    	  if (iAnz>0) s=iAnz+" stamm_protokoll gelöscht";
    	  iAnz=g.exec("update stamm_protokoll set pro_aic_protokoll=null where pro_aic_protokoll="+iAic);
    	  if (iAnz>0) s=(s==null?"":s+"\n")+iAnz+" stamm_protokoll wiederhergestellt";
      }
    }
    if (s!=null)
    {
      g.diskInfo(s);
      new Message(Message.INFORMATION_MESSAGE,null,g).showDialog("erledigt",new String[] {s});
    }
  }

  /*private void Delete()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    int iLevel=Nod==null ? -1:Nod.getLevel();
    if (iLevel==5)
    {
      int iAic=Tabellenspeicher.geti(Nod.getUserData());
      int iAnz=SQL.getInteger(g,"select count(*) from bew_pool where aic_protokoll="+iAic+" and pro_aic_protokoll is null");
      if (iAnz>0)
      {
        int iProt=g.Protokoll("Entfernen",0);
        iAnz=g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_protokoll="+iAic+" and pro_aic_protokoll is null");
        if (iAnz>0)
          g.diskInfo(iAnz+" Bew_pool gelöscht");
      }
    }
  }*/

  private void showBenutzer()
  {
    Tabellenspeicher Tab = new Tabellenspeicher(g,"select benutzer.kennung,count(*) from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer"+g.join2("protokoll","logging")+
                                                " where timestamp>="+g.DateTimeToString(g.getVon())+" and timestamp<"+g.DateTimeToString(g.getBis())+" group by benutzer.kennung",true);
    Tab.showGrid("Benutzer");
  }

  /*private void showLost()
  {
    Tabellenspeicher Tab = new Tabellenspeicher(g,"select p.timestamp"+g.AU_Bezeichnung1("Eigenschaft","sp")+" Eigenschaft,(select bezeichnung from stammview2 where aic_stamm=b.anr and aic_rolle is null) Stamm1"+
                                                ",(select bezeichnung from stammview2 where aic_stamm=b.anr2 and aic_rolle is null) Stamm2,b.aic_bew_pool,sp.aic_stammpool"+
                                                g.AU_Bezeichnung1("Benutzer","l")+" Benutzer,(SELECT kennung from computer where aic_computer=l.aic_computer) Computer"+
                                                " from logging l join protokoll p on l.aic_logging=p.aic_logging join bew_pool b on p.aic_protokoll=b.aic_protokoll"+
                                                " join stammpool sp on b.aic_bew_pool=sp.aic_bew_pool join daten_text d on sp.aic_daten=d.aic_daten and d.spalte_text is null"+
                                                " where aic_eigenschaft in (select aic_eigenschaft from eigenschaft where aic_begriff=432)"+
                                                " and timestamp>="+g.DateTimeToString(g.getVon())+" and timestamp<"+g.DateTimeToString(g.getBis())+" order by timestamp",true);
    Tab.showGrid("Lost");
  }*/

  private void showConnects()
  {
	  int iD=0;
	  if (EdtSuche.getText().length()>0)
		 iD=Sort.geti(EdtSuche.getText());
	  thisJFrame().setCursor(new Cursor(Cursor.WAIT_CURSOR));
	  DateWOD DW=new DateWOD(g.getVon());
	  long lBis=g.getBis().getTime();
	  Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Zeit","eingeloggt"});
	  int iLast=-2;
	  while (DW.getTimeInMilli()<lBis)
	  {
		  String sBind=DW.Format("yyyy-MM-dd HH:mm");
		  //g.fixtestError("Datum="+sBind);
		  String sDate=g.DateTimeToString(DW.toTimestamp());
		  String s="select count(l.aic_logging) from logging l join benutzer b on l.aic_benutzer=b.aic_benutzer where ein<"+sDate+" and aus>"+sDate+" and aic_code is null and bits&0x0143>0";
		  int iAnz=SQL.getInteger(g, s, -1);
		  if (iAnz != iLast)
		  {
			  iLast=iAnz;
			  Tab.addInhalt("Zeit", sBind);
			  Tab.addInhalt("eingeloggt",iAnz);
//			  g.fixtestError("Datum="+sBind+" -> "+iAnz);
//			  Static.EdtErrorKonsole.invalidate();
//			  Static.EdtErrorKonsole.validate();
//			  Static.EdtErrorKonsole.repaint();
			  //Static.sleep(1);
			  //Thread.yield();
		  }
//	  select b.*,l.ein from logging l join benutzer b on l.aic_benutzer=b.aic_benutzer where ein<'2020-01-13 10:20' and aus>'2020-01-13 10:20' 
//	  and aic_code is null and bits&0x0143>0
		  if (iD>0)
			DW.add(DW.MINUTE, iD);
		  else
		    DW.add(DW.HOUR, 1);
	  }
	  thisJFrame().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	  Tab.showGrid("Connects "+g.getVonBis("dd.MM.yyyy", false),thisJFrame());
  }
  
  private void Refresh()
  {
    g.fixInfo("Refresh");
    String sMandant=g.AllUnlimited() ? "":" and aic_mandant"+Static.SQL_in(g.VecMandantWrite);
    String sPlus=g.Def() ? "":" where ein>"+g.DateTimeToString(g.getBeginn())+sMandant;
    Tabellenspeicher Tab = new Tabellenspeicher(g, "select distinct "+(g.Oracle() ? "to_char(ein,'YYYY')":"year(ein)")+" zeit from logging"+sPlus+" order by zeit desc", true);

    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    //JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
    //StyFolder.setFont(g.fontStandard);
    NodeRoot.removeChildren();
    for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
    {
      JCOutlinerFolderNode Nod = new JCOutlinerFolderNode(Tab.getS("Zeit"),NodeRoot);
      Nod.setState(BWTEnum.FOLDER_CLOSED);
      //Nod.setStyle(StyFolder);
      Nod.setUserData(Tab.getS("Zeit"));
    }
    Out.folderChanged(NodeRoot);

  }

}
