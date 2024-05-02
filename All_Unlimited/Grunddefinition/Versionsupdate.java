/*
    All_Unlimited-Grunddefinition-Versionsupdate.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.Export;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Zeitraum;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Versionsupdate extends All_Unlimited.Allgemein.Formular
{
private boolean checkOk()
  {
    int iLogging = SQL.getInteger(g,"select count(*) from begriff where aic_logging="+g.getLog());
    if(iLogging>0)
    {
          new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("Restart");
          bDefExport = false;
    }
    //int iLog=SQL.getInteger(g,"select count(*) from logging where aus is null");
    //if (iLog>1 &&  new Message(Message.YES_NO_OPTION,null,g).showDialog("Eingeloggt",new String[] {""+(iLog-1)})==Message.NO_OPTION)
    //  return false;
//    SQL Qry=new SQL(g);
//    String s1=Qry.list("upper(kennung)","eigenschaft group by upper(kennung)"+(g.MS()?",len(kennung+'*')":"")+" having count(*)>1");
    Vector<String> VecE=SQL.getSVector("select distinct upper(kennung) from eigenschaft group by upper(kennung)"+(g.MS()?",len(kennung+'*')":"")+" having count(*)>1", g);
//    String s2=Qry.list("upper(kennung)","begriff where kennung "+(g.MySQL()?"<>''":"is not null")+" group by aic_begriffgruppe,upper(kennung)"+(g.MS()?",len(kennung+'*')":"")+" having count(*)>1");
    Vector<String> VecB=SQL.getSVector("select distinct upper(kennung) from begriff where kennung "+(g.MySQL()?"<>''":"is not null")+" group by aic_begriffgruppe,upper(kennung)"+(g.MS()?",len(kennung+'*')":"")+" having count(*)>1", g);
    Tabellenspeicher TabB=null;
    if (VecB.size()>0)
    	TabB=new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("begriffgruppe","begriff")+" Gruppe,DefBezeichnung,Kennung,aic_begriff from begriff where upper(kennung)"+Static.SQL_in(VecB)+" order by aic_begriffgruppe,kennung",true);
    	//TabB.showGrid("doppelte Begriffe");
    else  if (VecE.size()>0)
    	TabB=new Tabellenspeicher(g,"select Kennung"+g.AU_Bezeichnung("eigenschaft")+",aic_eigenschaft from eigenschaft where upper(kennung)"+Static.SQL_in(VecE)+" order by kennung",true);
//    Qry.free();
//    String s=(s1.equals(" ")?"":"\ndoppelte Eigenschaften:"+s1)+(s2.equals(" ")?"":"\ndoppelte Begriffe:"+s2);//+(s3.equals(" ")?"":"\nleere Begriffe:"+s3);
//    if (!s.equals(""))
//    {
      //if (g.ApplPort())
      if (TabB != null)
      {
//    	  if (TabB==null)
//    		  new Message(Message.WARNING_MESSAGE, null, g).showDialog("Fehler", new String[] {s});
//    	  else
    		  new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB, null, g).showDialog(VecB.size()>0 ? "doppelte_Begriffe":"doppelte_Eigenschaften", TabB);
        bDefExport = false;
//        return false;
      }
//      else
//      {
//        new Message(Message.WARNING_MESSAGE, null, g).showDialog("DefExport_gesperrt", new String[] {s});
//        bDefExport = false;
//      }
//    }
    return true;
  }

public static Versionsupdate get(Global rg)
{
  if (Self==null) 
	  new Versionsupdate(rg);
  else if  (!Self.checkOk())
  {
	  free();
      return null;
  }
  else
	Self.show();
  return Self;
}

public static void free()
{
  if (Self!=null)
  {
    Self.g.winInfo("Versionsupdate.free");
    Self.thisFrame.dispose();
    Self=null;
  }
}

private Versionsupdate(Global glob)
{
	super("Versionsupdate",null,glob);
	g=glob;

        if  (!checkOk())
        {
          thisFrame.dispose();
          return;
        }
        Self=this;
	      Build();
        Load();

        ActionListener AL = new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            String s = e.getActionCommand();
            g.progInfo("Versionsupdate.Action=" + s);
            if (s.equals("Ende"))
              hide();
            else if (s.equals("Export"))
              new Export(g,(JFrame)thisFrame,0,null,0);
            else if (s.equals("DefExport"))
            {
              int iLogging = SQL.getInteger(g, "select count(*) from begriff where aic_logging=" + g.getLog());
              if (iLogging > 0)
                new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("Restart");
              else
                new DefExport(TabAenderung.getVecSpalteI("Aic_Fremd"), (JFrame)thisFrame, g,CboProg.getSelectedAIC(),!CbxSchnellimport.isSelected(),CbxAll.isSelected(),CbxWeb.isSelected());
            }
            else if (s.equals("Hinzufuegen") || s.equals("Entfernen"))
            {
              if (s.equals("Hinzufuegen"))
                Hinzufuegen();
              else
                Entfernen();
              EnableButtons();
            }
            else if (s.equals("Zeitraum"))
              Zeitraum.get(g).show();
            else if (s.equals("Show"))
              showInfo();
            else if (s.equals("Abfragen_testen"))
              Abfragen_testen(g,thisJFrame());
            else if (s.equals("Refresh"))
              fill_OutBegriff();
            else
              Static.printError("Versionsupdate.AL:" + s + " wird nicht unterstützt");
          }
        };
        g.BtnAdd(BtnEnde, "Ende", AL);
        g.BtnAdd(BtnExport, "Export", AL);
        g.BtnAdd(BtnDefExport, "DefExport", AL);
        g.BtnAdd(BtnHinzufuegen, "Hinzufuegen", AL);
        g.BtnAdd(BtnEntfernen, "Entfernen", AL);
	      g.BtnAdd(getButton("show"), "Show", AL);
        g.BtnAdd(getButton("Abfragen_testen"), "Abfragen_testen", AL);
        g.BtnAdd(getButton("Refresh"), "Refresh", AL);
        g.BtnAdd(getButton("Zeitraum"), "Zeitraum", AL);

        JCOutlinerListener OL=new JCOutlinerListener()
        {
          public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev){}
          public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)  {}
          public void outlinerNodeSelectBegin(JCOutlinerEvent ev)       {}
          public void outlinerNodeSelectEnd(JCOutlinerEvent ev)         {}
          public void itemStateChanged(JCItemEvent ev)
          {
                  if(ev.getStateChange() == ItemEvent.SELECTED)
                          EnableButtons();
          }
        };
        OutVersion.addItemListener(OL);
        OutBegriff.addItemListener(OL);
	EnableButtons();
	show();
}

public void show()
{
	fill_OutBegriff();
	super.show();
	EnableButtons();
}

private void Build()
{
	TabAenderung = new Tabellenspeicher(g,new String[]{"AIC_Tab","AIC_Fremd","BITS","Node","Art"});
	TabAenderung.sGruppe="AIC_Tab";
	TabAenderung.sAIC="AIC_Fremd";

	TabVersion = new Tabellenspeicher(g,new String[]{"AIC_Tab","AIC_BG","Node"});
	TabVersion.sGruppe="AIC_Tab";
	TabVersion.sAIC="AIC_BG";

	OutBegriff.setBackground(Color.white);
	OutVersion.setBackground(Color.white);
	OutBegriff.setRootVisible(false);
        OutVersion.setRootVisible(false);
	OutBegriff.setAllowMultipleSelections(true);
	OutVersion.setAllowMultipleSelections(true);

	String[] s = new String[] {g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Aic"),g.getBegriffS("Show","Kennung"),"STT/BEW",g.getBegriffS("Show","Change")};
	OutBegriff.setColumnButtons(s);
	OutBegriff.setNumColumns(s.length);
	OutVersion.setColumnButtons(s);
	OutVersion.setNumColumns(s.length);

	OutBegriff.setColumnLabelSortMethod(Sort.sortMethod);
	OutVersion.setColumnLabelSortMethod(Sort.sortMethod);

	BtnExport = getButton("Export");
	if(BtnExport==null) BtnExport=new JButton();
        BtnDefExport = getButton("DefExport");
	if(BtnDefExport==null) BtnDefExport=new JButton();
	BtnEnde = getButton("Beenden");
	if(BtnEnde==null) BtnEnde=new JButton();
	//BtnOk = getButton("Ok");
	//if(BtnOk==null) BtnOk=new JButton();
	//BtnAbbruch = getButton("Abbruch");
	//if(BtnAbbruch==null) BtnAbbruch=new JButton();
	BtnHinzufuegen = getButton(">");
	if(BtnHinzufuegen==null) BtnHinzufuegen=new JButton();
	BtnEntfernen = getButton("<");
	if(BtnEntfernen==null) BtnEntfernen=new JButton();
	//BtnShow = getButton("show");
        //if(BtnShow==null) BtnShow=new JButton();
        // BtnTest = getButton("Abfragen_testen");
        //if(BtnTest==null) BtnTest=new JButton();
        //BtnRefresh = getButton("Refresh");
        //if(BtnRefresh==null) BtnRefresh=new JButton();
        if (!bDefExport)// && !Static.bJBuilder)
        {
          BtnDefExport.setEnabled(false);
          BtnHinzufuegen.setEnabled(false);
          BtnEntfernen.setEnabled(false);
        }

        CbxSchnellimport = getCheckbox("Schnellimport");
        if(CbxSchnellimport==null) CbxSchnellimport=new AUCheckBox();
        CbxWeb=getCheckbox("Web");
        if (CbxWeb==null) CbxWeb=new AUCheckBox();
        CbxAll=getCheckbox("All");
        if (CbxAll==null) CbxAll=new AUCheckBox();
        CbxAll.setSelected(true);
        //CbxNurModul = getCheckbox("nur Modul");
        //if(CbxNurModul==null) CbxNurModul=new AUCheckBox();
        CbxAlleAbfragen = getCheckbox("alle Abfragen3");
        if(CbxAlleAbfragen==null) CbxAlleAbfragen=new AUCheckBox();
        CboProg=new ComboSort(g);
        CboProg.fillBegriffTable(g.TabBegriffgruppen.getAic("Programm"),true,true);
        //CbxTest = getCheckbox("Test");
        //if(CbxTest==null) CbxTest=new AUCheckBox();
        //CbxFremdSprache = getCheckbox("Fremdsprache");
        //if(CbxFremdSprache==null) CbxFremdSprache=new AUCheckBox();

	JPanel Pnl_Outliner_Begriff = getFrei("Outliner Begriff");
	JPanel Pnl_Outliner_Version = getFrei("Outliner Version");
        JPanel Pnl_Combobox = getFrei("Combobox");
	if(Pnl_Outliner_Begriff!=null)
		Pnl_Outliner_Begriff.add("Center",OutBegriff);
	if(Pnl_Outliner_Version!=null)
		Pnl_Outliner_Version.add("Center",OutVersion);
        if(Pnl_Combobox!=null)
		Pnl_Combobox.add("Center",CboProg);
}

private void fill_OutBegriff()
{
	SQL Qry = new SQL(g);
	((JCOutlinerFolderNode)OutBegriff.getRootNode()).removeChildren();

	//Integer iAIC_Tab = new Integer(g.TabTabellenname.getAic("BEGRIFF"));
	Vector<Object> VecVisible = new Vector<Object>();
        int iPosT=g.TabTabellenname.getPos("Kennung","BEGRIFF");
	VecVisible.addElement(g.TabTabellenname.getS(iPosT,"Bezeichnung"));
	VecVisible.addElement(g.TabTabellenname.getS(iPosT,"Kennung"));
	JCOutlinerFolderNode NodeTable = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutBegriff.getRootNode());
	NodeTable.setUserData(new Integer(0));
	//NodeTable.setUserData(iAIC_Tab);

	for(int i=0;i<sBegriffe.length;i++)
	{
		if(Qry.open("select b.aic_begriff,b.kennung,DefBezeichnung Bezeichnung,b.aic_stammtyp,b.aic_bewegungstyp,b.aic_code"+
                            ",(select ein from logging where aic_logging=b.aic_logging) Log from begriff b"+
                            g.join("begriffgruppe","bg","b","begriffgruppe")+" where "+(sBegriffe[i].equals("Abfrage") && !CbxAlleAbfragen.isSelected()?g.bit("bits",Abfrage.cstVersionsup):"ende is null")+" and bg.kennung='"+sBegriffe[i]+"' order by Bezeichnung"))
		{
			int iPos=g.TabBegriffgruppen.getPos("KENNUNG",sBegriffe[i]);
			VecVisible = new Vector<Object>();
			Vector<Integer> VecInvisible = new Vector<Integer>();
			VecVisible.addElement(g.TabBegriffgruppen.getS(iPos,"Bezeichnung"));
                        VecVisible.addElement(g.TabBegriffgruppen.getInt(iPos,"AIC"));
			VecVisible.addElement(g.TabBegriffgruppen.getS(iPos,"Kennung"));
			VecInvisible.addElement(g.iTabBegriff);
			VecInvisible.addElement(g.TabBegriffgruppen.getInt(iPos,"AIC"));
			JCOutlinerFolderNode NodeBG = new JCOutlinerFolderNode((Object)VecVisible,NodeTable);
			NodeBG.setUserData(VecInvisible);
			NodeBG.setState(BWTEnum.FOLDER_CLOSED);
			ImageIcon Gif = g.LoadImageIcon(g.TabBegriffgruppen.getS(iPos,"FILENAME"));
			JCOutlinerNodeStyle NodeStyle=g.getStyle(Gif!=null?Gif.getImage():null);
			NodeBG.setStyle(NodeStyle);

			boolean bFrame=sBegriffe[i].equals("Frame");
			for(;!Qry.eof();Qry.moveNext())
			{
				int iAIC_Stammtyp=Qry.getI("aic_stammtyp");
				int iAIC_Bewegungstyp=Qry.getI("aic_bewegungstyp");
                                int iCode=Qry.getI("aic_code");
				VecVisible = new Vector<Object>();
				VecVisible.addElement(Static.beiLeer(Qry.getS("Bezeichnung"),Qry.getS("Kennung")));
                                VecVisible.addElement(Qry.getInt("AIC_Begriff"));
                                VecVisible.addElement(Qry.getS("Kennung"));
				VecVisible.addElement(bFrame && iCode>0?"*"+g.TabCodes.getKennung(iCode):iAIC_Stammtyp==0&&iAIC_Bewegungstyp==0?"":iAIC_Bewegungstyp==0||(bFrame&&iAIC_Stammtyp!=0)?g.getBezeichnungS("Stammtyp",iAIC_Stammtyp):"*"+g.getBezeichnungS("Bewegungstyp",iAIC_Bewegungstyp));
                                VecVisible.addElement(Qry.getD("Log"));
				JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeBG);
				Node.setUserData(Qry.getInt("AIC_Begriff"));
				Node.setStyle(NodeStyle);
			}
		}
	}

	OutBegriff.folderChanged(OutBegriff.getRootNode());

	Qry.free();
}

@SuppressWarnings("unchecked")
private void Load()
{
	TabAenderung.clearAll();
	TabVersion.clearAll();
	JCOutlinerFolderNode NodeRoot=(JCOutlinerFolderNode)OutVersion.getRootNode();
	NodeRoot.removeChildren();
	//if(iAIC_Version>0)
	//{
		//g.TabTabellenname.posInhalt("KENNUNG","BEGRIFF");
		//Integer iAIC_Tab = g.TabTabellenname.getInt("AIC");
                /*
		SQL Qry = new SQL(g);
		if(Qry.open("SELECT aic_version,kennung,erstellt"+g.AU_Bezeichnung("Version","")+" FROM Version WHERE AIC_Version="+iAIC_Version))
		{
			if(!Qry.eof())
			{
				Vector VecVisible = new Vector();
				Vector VecInvisible = new Vector();
				VecVisible.addElement(Static.beiLeer(Qry.getS("Bezeichnung"),Qry.getS("Kennung")));
				VecVisible.addElement(Qry.getS("Kennung"));
				VecInvisible.addElement(Qry.getInt("AIC_Version"));
				VecInvisible.addElement(Qry.getTS("erstellt"));
				NodeRoot.setLabel(VecVisible);
				NodeRoot.setUserData(VecInvisible);
			}
			Qry.close();
		}*/

		//String[] sP = new String[]{"Frame","Applikation","Abfrage","Modell","Druck","Message","Button","Checkbox","Show","Modul"};
		for(int i=0;i<sBegriffe.length;i++)
		{
			int iPos=g.TabBegriffgruppen.getPos("KENNUNG",sBegriffe[i]);
			Vector VecVisible = new Vector();
			VecVisible.addElement(g.TabBegriffgruppen.getS(iPos,"Bezeichnung"));
                        VecVisible.addElement(g.TabBegriffgruppen.getS(iPos,"AIC"));
			VecVisible.addElement(g.TabBegriffgruppen.getS(iPos,"Kennung"));
			JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)new Vector(VecVisible),NodeRoot);
			Node.setUserData(g.TabBegriffgruppen.getInt(iPos,"AIC"));
			ImageIcon Gif = g.LoadImageIcon(g.TabBegriffgruppen.getS(iPos,"FILENAME"));
			JCOutlinerNodeStyle NodeStyle=g.getStyle(Gif!=null?Gif.getImage():null);
			Node.setStyle(NodeStyle);

			TabVersion.posInhalt("AIC_Tab",g.iTabBegriff);
			TabVersion.putInhalt("AIC_Tab",g.iTabBegriff,true);
			TabVersion.putInhalt("AIC_BG",g.TabBegriffgruppen.getInt(iPos,"AIC"),true);
			TabVersion.putInhalt("Node",Node,true);
		}

	if(g.Debug())
	{
		//TabVersion.showGrid("TabVersion");
		TabAenderung.showGrid("TabAenderung");
	}

	OutVersion.folderChanged(OutVersion.getRootNode());
}

@SuppressWarnings("unchecked")
private void Hinzufuegen()
{
	JCOutlinerNode[] NodeB = OutBegriff.getSelectedNodes();

	for(int i=0;i<NodeB.length;i++)
	{
		Integer iAIC_Tab = (Integer)((Vector)NodeB[i].getParent().getUserData()).elementAt(0);
		Integer iAIC_Fremd = (Integer)NodeB[i].getUserData();
		Integer iAIC_BG = (Integer)((Vector)NodeB[i].getParent().getUserData()).elementAt(1);
		Integer iBit = (Integer)NodeB[i].getParent().getParent().getUserData();

		//boolean b=!(!TabAenderung.posInhalt(iAIC_Tab,iAIC_Fremd)||TabAenderung.getS("Art").equals("Loeschen")&&TabAenderung.getI("BITS")!=iBit.intValue());
		boolean b=TabAenderung.posInhalt(iAIC_Tab,iAIC_Fremd);//&&!TabAenderung.getS("Art").equals("Loeschen");
		if(b)
		{
			/*if((TabAenderung.getI("BITS")&DefExport.cstNur)>0&&iBit.intValue()==0)
			{
				Entfernen(new JCOutlinerNode[]{(JCOutlinerNode)TabAenderung.getInhalt("Node")});
				b=false;
			}
			else*/ if(TabAenderung.getS("Art").equals("Loeschen"))
				b=false;

		}

		if(!b)
		{
			if(TabVersion.posInhalt(iAIC_Tab,iAIC_BG))
			{
				JCOutlinerNode Node = new JCOutlinerNode(new Vector((Vector)NodeB[i].getLabel()),(JCOutlinerFolderNode)TabVersion.getInhalt("Node"));
				Node.setUserData(iAIC_Fremd);
				Node.setStyle(NodeB[i].getStyle());

				if(TabAenderung.eof()||!TabAenderung.getS("Art").equals("Loeschen"))
				{
					TabAenderung.posInhalt("AIC_Tab",iAIC_Tab);
					TabAenderung.putInhalt("AIC_Tab",iAIC_Tab,true);
					TabAenderung.putInhalt("AIC_Fremd",iAIC_Fremd,true);
					TabAenderung.putInhalt("BITS",iBit,true);
					TabAenderung.putInhalt("Node",Node,true);
					TabAenderung.putInhalt("Art","Neu",true);
				}
				else if(TabAenderung.getI("BITS")!=iBit.intValue()||TabAenderung.getS("Art").equals("Aenderung"))
				{
					TabAenderung.setInhalt("Art","Aenderung");
					TabAenderung.setInhalt("Node",Node);
					TabAenderung.setInhalt("BITS",iBit);
				}
				else
					TabAenderung.setInhalt("Art","Vorhanden");
			}
			else
				Static.printError("Versionsupdate.Hinzufuegen(): Das positionieren in TabVersion muß immer funktionieren!!!");
		}
	}

	if(g.Debug())
		TabAenderung.showGrid("TabAenderung");

	OutVersion.folderChanged(OutVersion.getRootNode());
}

private void Entfernen()
{
	Entfernen(OutVersion.getSelectedNodes());
}

private void Entfernen(JCOutlinerNode[] NodeV)
{
	for(int i=0;i<NodeV.length;i++)
	{
		if(TabAenderung.posInhalt("AIC_Fremd",NodeV[i].getUserData()))
		{
			if(TabAenderung.getS("Art").equals("Neu"))
				TabAenderung.clearInhalt();
			else
				TabAenderung.setInhalt("Art","Loeschen");

			NodeV[i].getParent().removeChild(NodeV[i]);
		}
		else
		{
			Static.printError("Versionsupdate.Entfernen(): AIC_Fremd:"+NodeV[i].getUserData());
			Static.printError("Versionsupdate.Entfernen(): Das Positionieren in TabAenderung muß immer funktionieren.");
		}
	}

	if(g.Debug())
		TabAenderung.showGrid("TabAenderung");

	OutVersion.folderChanged(OutVersion.getRootNode());
}

private void EnableButtons()
{
	BtnExport.setEnabled(true);

	JCOutlinerNode[] NodeB = OutBegriff.getSelectedNodes();
	JCOutlinerNode[] NodeV = OutVersion.getSelectedNodes();
	BtnHinzufuegen.setEnabled(NodeB!=null && NodeB.length>0 && NodeB[0].getLevel()==3);
	BtnEntfernen.setEnabled(NodeV!=null && NodeV.length>0 && NodeV[0].getLevel()==2);
}

public static Tabellenspeicher getChange(Global g)
      {
        return new Tabellenspeicher(g,"select g.kennung Gruppe,defBezeichnung,begriff.kennung"+g.AU_Bezeichnung1("Bewegungstyp","Begriff")+" Bewegungstyp"+
                               g.AU_Bezeichnung1("Stammtyp","Begriff")+" Stammtyp,Importzeit,ein anders,benutzer.kennung Benutzer from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+
                               " join logging on logging.aic_logging=begriff.aic_logging join benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+
                               " where tod is null and begriff.kennung is not null and ein"+(g.ApplPort()?"<":">")+"importzeit"+
                               (g.ApplPort() || g.Oracle() ? "":" and (g.kennung<>'Abfrage' or begriff.bits&0x1000000000000400=0)")+" order by begriff.aic_logging desc",true);
      }

private void showInfo()
        {

          JDialog DlgTab=new JDialog((JFrame)null,"Test-Daten",false);
          JTabbedPane Pane = new JTabbedPane();

          AUOutliner GidImport = new AUOutliner();
          AUOutliner GidSperre = new AUOutliner();
          AUOutliner GidDefVerlauf = new AUOutliner();
          AUOutliner GidBeg = new AUOutliner();
          AUOutliner GidChange = new AUOutliner();
          AUOutliner GidEig = new AUOutliner();
          AUOutliner GidStt = new AUOutliner();
          AUOutliner GidBew = new AUOutliner();

          Pane.add("Import", GidImport);
          Pane.add("Sperre", GidSperre);
          Pane.add("DefVerlauf", GidDefVerlauf);
          Pane.add("Change", GidChange);
          Pane.add("Begriff", GidBeg);
          Pane.add("Stammtyp", GidStt);
          Pane.add("Bewegungstyp", GidBew);
          Pane.add("Eigenschaft", GidEig);
          String sWhere=" where logging.ein>"+g.von()+" and logging.mom<"+g.bis()+" order by ein desc";
          new Tabellenspeicher(g,"select aic_defimport AIC,computer.kennung Computer,Programm,Prog_Ver,Anfang,ende,filename,version from computer"+g.join2("defimport","computer")+" order by aic_defimport desc",true).showGrid(GidImport);
          new Tabellenspeicher(g,"select g.kennung Gruppe,defBezeichnung,begriff.kennung"+g.AU_Bezeichnung1("Bewegungstyp","Begriff")+" Bewegungstyp"+
                               g.AU_Bezeichnung1("Stammtyp","Begriff")+" Stammtyp,benutzer.kennung Benutzer from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" join logging on logging.aic_logging=begriff.log_aic_logging join benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+sWhere,true).showGrid(GidSperre);
          new Tabellenspeicher(g,"select g.kennung Gruppe,defbezeichnung,v.aic_begriff,tat,timestamp,(select benutzer.kennung from benutzer join logging l on benutzer.aic_benutzer=l.aic_benutzer where l.aic_logging=v.aic_logging) Benutzer"+
              " from defverlauf v join begriff b on v.aic_begriff=b.aic_begriff join begriffgruppe g on g.aic_begriffgruppe=b.aic_begriffgruppe where timestamp>"+g.von()+" and timestamp<"+g.bis()+" order by timestamp desc",true).showGrid(GidDefVerlauf);
          getChange(g).showGrid(GidChange);
          new Tabellenspeicher(g,"select g.kennung Gruppe,defBezeichnung,begriff.kennung"+g.AU_Bezeichnung1("Bewegungstyp","Begriff")+" Bewegungstyp"+
                               g.AU_Bezeichnung1("Stammtyp","Begriff")+" Stammtyp,ein,benutzer.kennung Benutzer from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" join logging on logging.aic_logging=begriff.aic_logging join benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+sWhere,true).showGrid(GidBeg);
          new Tabellenspeicher(g,"select Stammtyp.kennung"+g.AU_Bezeichnung("Stammtyp")+",ein,benutzer.kennung Benutzer from Stammtyp"+g.join("logging","Stammtyp")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+sWhere,true).showGrid(GidStt);
          new Tabellenspeicher(g,"select Bewegungstyp.kennung"+g.AU_Bezeichnung("Bewegungstyp")+",ein,benutzer.kennung Benutzer from Bewegungstyp"+g.join("logging","Bewegungstyp")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+sWhere,true).showGrid(GidBew);
          new Tabellenspeicher(g,"select Eigenschaft.kennung"+g.AU_Bezeichnung("Eigenschaft")+",ein,benutzer.kennung Benutzer from Eigenschaft"+g.join("logging","Eigenschaft")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+sWhere,true).showGrid(GidEig);
          DlgTab.getContentPane().add("Center",Pane);
          DlgTab.setSize(850,400);
          Static.centerComponent(DlgTab,thisFrame);
          DlgTab.setVisible(true);
        }

public static void Abfragen_testen(Global g,JFrame Fom)
        {
          // g.fixtestError("Abfragen_testen");
          bTesten=false;
          final JDialog DlgTest=new JDialog(Fom,"Test-Einstellung",true);
          DlgTest.getContentPane().setLayout(new BorderLayout(2,2));
          JPanel PnlW = new JPanel(new GridLayout(0,1,2,2));
           g.addLabel3(PnlW,"Anzahl_ab");
           g.addLabel3(PnlW,"Dauer_ab");
           g.addLabel3(PnlW,"max_Zeilen");
           g.addLabel3(PnlW,null);
          DlgTest.getContentPane().add("West",PnlW);

          JPanel PnlC = new JPanel(new GridLayout(0,1,2,2));
           final Zahl EdtAnzahlAb=new Zahl(iAnzahlAb,0);
           final Zahl EdtDauerAb=new Zahl(iDauerAb,0);
           final Zahl EdtMaxZeilen=new Zahl(iMaxZeilen,0);
           final AUCheckBox CbxTab=g.getCheckbox("TabDaten_zeigen",bTabZeigen);//new AUCheckBox(bTabZeigen);
           PnlC.add(EdtAnzahlAb);
           PnlC.add(EdtDauerAb);
           PnlC.add(EdtMaxZeilen);
           PnlC.add(CbxTab);
          DlgTest.getContentPane().add("Center",PnlC);

          JButton BtnOk = g.getButton("Ok");
          DlgTest.getRootPane().setDefaultButton(BtnOk);
          BtnOk.addActionListener(new ActionListener()
          {
                  public void actionPerformed(ActionEvent ev)
                  {
                    iAnzahlAb=EdtAnzahlAb.intValue();
                    iDauerAb=EdtDauerAb.intValue();
                    iMaxZeilen=EdtMaxZeilen.intValue();
                    bTabZeigen=CbxTab.isSelected();
                    bTesten=true;
                    DlgTest.setVisible(false);
                  }
          });
          JButton BtnAbbruch = g.getButton("Abbruch");
          BtnAbbruch.addActionListener(new ActionListener()
          {
                  public void actionPerformed(ActionEvent ev)
                  {
                          DlgTest.setVisible(false);
                  }
          });
          JPanel PnlS = new JPanel(new GridLayout(1,0,2,2));
          PnlS.add(BtnOk);
          PnlS.add(BtnAbbruch);
          DlgTest.getContentPane().add("South",PnlS);
          DlgTest.pack();
          Static.centerComponent(DlgTest,Fom);
          DlgTest.setVisible(true);
          if (bTesten)
          {
           final Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,defBezeichnung,aic_stammtyp,aic_rolle from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+" and tod is null order by defbezeichnung",true);
           final Gauge gauge=new Gauge(0,Tab.getAnzahlElemente(null),"Abfrage-Test",g,true);
           new Thread(new Runnable()
           {
            public void run()
            {
              g.bISQL=true;
              Static.printError("Abfrage-Test gestartet");
              final Tabellenspeicher TabFehler=new Tabellenspeicher(g,bTabZeigen ? new String[] {"Aic","Abfrage","Typ","Zeilen","Dauer","Anzahl","Fehler","TabDaten"}:
                  new String[] {"Aic","Abfrage","Typ","Zeilen","Dauer","Anzahl","Fehler"});
              Static.clearError();
              for (Tab.moveFirst(); !Tab.out() && !gauge.bEscape; Tab.moveNext())
              {
                int iFehler=g.getFehler();
                //int iError=Static.getError();
                String s = Tab.getS("defBezeichnung");
                int iAic=Tab.getI("aic_begriff");
                gauge.setText(s, Tab.getPos());
                Tabellenspeicher TabD = null;
                long lDauer=Static.get_ms();
                boolean bKeinTest=false;
                try
                {
                  ShowAbfrage A = new ShowAbfrage(g, iAic, Abfrage.cstBegriff);
                  bKeinTest=(A.iBits & Abfrage.cstKeinTest)>0;
                  if (A.iModell > 0) A.iModell = 0;
                  if ((A.iBits & (Abfrage.cstSumme+Abfrage.cstFirst))==0)
                  {
                    A.iTop = iMaxZeilen;
                    A.iBits = A.iBits | Abfrage.cstFirst;
                  }
                  A.SQL_String();
                  int iStt=Tab.getI("aic_stammtyp");
                  int iStamm=iStt<1 ? 0:g.getSyncStamm(iStt,Tab.getI("aic_rolle"));
                  TabD = bKeinTest ? null:A.getDaten(A.iBew == 0 ? A.iStt : 0, iStamm<1 ? 1:iStamm/*1*/, iStt<1 ? null:g.getSyncVec(iStt),null);
                }
                catch (Exception e)
                {
                  g.printError("Abfragetest: Exception bei Abfrage " + s,iAic);
                }
                lDauer=Static.get_ms()-lDauer;
                if (TabD == null || g.getFehler()>iFehler || Static.getError()>0 || lDauer>=iDauerAb || TabD.size()>=iAnzahlAb)
                {
                  //g.TabBegriffe.push();
                  int iPos=g.TabBegriffe.getPos("Aic",iAic);
                  String sTyp=g.TabBegriffe.getI(iPos,"Erf")>0 ? "Bew "+g.getBezeichnungS("Bewegungstyp",g.TabBegriffe.getI(iPos,"Erf")):"Stt "+g.getBezeichnungS("Stammtyp",g.TabBegriffe.getI(iPos,"Stt"));
                  //g.TabBegriffe.pop();
                  TabFehler.addInhalt("Aic",iAic);
                  TabFehler.addInhalt("Abfrage",s);
                  TabFehler.addInhalt("Typ",sTyp);
                  TabFehler.addInhalt("Zeilen",TabD == null ? -1:TabD.size());
                  TabFehler.addInhalt("Dauer",lDauer);
                  int iAnzError=Static.getError();
                  TabFehler.addInhalt("Anzahl",iAnzError);
                  String sE=Static.getErrorString();
                  TabFehler.addInhalt("Fehler",bKeinTest ? "kein Test":sE==null && TabD == null ? "keine Daten":sE);
                  if (bTabZeigen)
                    TabFehler.addInhalt("TabDaten",TabD);
                  if (iAnzError>0)
                    Static.printError("Abfrage " + s + "("+iAic+"/"+sTyp+") fehlerhaft!");
                  Static.clearError();                 
                }
                //else
                //  g.testInfo("Abfrage "+s+" ok:"+Static.Mem());
              }
              Static.printError("Abfrage-Test "+(gauge.bEscape ? "abgebrochen bei "+new Zahl1(1.0*Tab.getPos()/Tab.getAnzahlElemente(null),"0 %"):"fertig durchgeführt"));
              g.bISQL=false;
              TabFehler.showGrid("Abfrage-Fehler",Fom);
              if (bTabZeigen)
                TabFehler.Grid.addActionListener(new JCActionListener()
                {
                    public void actionPerformed(JCActionEvent ev)
                    {
                            JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
                            Tabellenspeicher Tab=(Tabellenspeicher)((Vector)Nod.getLabel()).elementAt(7);
                            if (Tab!=null)
                              Tab.showGrid("Abf.: "+Sort.gets(Nod.getLabel(),1));
                    }
                });
              TabFehler.Grid.getOutliner().addMouseListener(new MouseListener()
                {
                  public void mousePressed(MouseEvent ev) {}
                  public void mouseClicked(MouseEvent ev)
                  {
                    //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
                    if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
                    {
                      JCOutlinerNode Nod = TabFehler.Grid.getSelectedNode();//((JCOutliner)ev.getSource()).getSelectedNode();
                      int iAic = Sort.geti(Nod.getLabel(), 0);
                      int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iAic);
                      DefAbfrage.get(g, new ShowAbfrage(g, iAic, Abfrage.cstBegriff), iStt, null, false, 1).show();
                    }
                  }
                  public void mouseEntered(MouseEvent ev) {}
                  public void mouseExited(MouseEvent ev) {}
                  public void mouseReleased(MouseEvent ev) {}
                });

              gauge.setText("fertig",gauge.getMax());
            }
          }).start();
         }
        }


// add your data members here
private Global g;
private static Versionsupdate Self=null;

private JButton BtnExport;
private JButton BtnDefExport;
private JButton BtnEnde;
//private JButton BtnRefresh;
//private JButton BtnOk;
//private JButton BtnAbbruch;
private JButton BtnHinzufuegen;
private JButton BtnEntfernen;
//private JButton BtnShow;
//private JButton BtnTest;

private AUCheckBox CbxSchnellimport;
private AUCheckBox CbxWeb;
private AUCheckBox CbxAll;
//private JCheckBox CbxNurModul;
private AUCheckBox CbxAlleAbfragen;
//private JCheckBox CbxTest;
//private JCheckBox CbxFremdSprache;

private AUOutliner OutBegriff = new AUOutliner(new JCOutlinerFolderNode(""));
private AUOutliner OutVersion = new AUOutliner(new JCOutlinerFolderNode(""));

private Tabellenspeicher TabAenderung;
private Tabellenspeicher TabVersion;
private boolean bDefExport=true;
private ComboSort CboProg;
private static boolean bTesten=false;
private static int iDauerAb=500;
private static int iAnzahlAb=1000;
private static int iMaxZeilen=30000;
private static boolean bTabZeigen=true;

//private boolean bNew=false;
private String[] sBegriffe = new String[] {"Frame","Applikation","Abfrage","Modell","Druck","Modul","Kunde","Message","Button","Checkbox","Show","Format"};

}

