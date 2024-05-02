/*
    All_Unlimited-Grunddefinition-ModulDefinition.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
//import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Hauptmaske.Zeitraum;
import All_Unlimited.Print.Druckdefinition;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class DefModul extends All_Unlimited.Allgemein.Formular
{
  private static DefModul Self=null;

  public static DefModul get(Global glob,int riBegriff)
  {
    if (Self==null)
      new DefModul(glob);
    else
      Self.show();
    Self.pos(riBegriff);
    return Self;
  }

  public static void free()
  {
    if (Self != null)
    {
      Self.g.winInfo("DefModul.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

private DefModul(Global glob)
{
	super("ModulDefinition",null,glob);
        Self = this;
	g=glob;
	g.winInfo("DefModul.create");
	if (!g.Def())
	{
		new Message(Message.WARNING_MESSAGE,null,g).showDialog("keineBerechtigung");
		thisFrame.dispose();
		return;
	}
    dtEnde=new Datum(g,"dd.MM.yyyy");
	iAIC_Modul=g.TabBegriffgruppen.getAic("Modul");
	if(iAIC_Modul!=0)
	{
		Build();

                AL=new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    String s = ev.getActionCommand();
                    if (s.equals("Init"))
                      Init();
                    else if (s.equals("Neu"))
                      Neu();
                    else if (s.equals("EditOk"))
                      EditOk();
                    else if (s.equals("EditAbbruch"))
                      FrmEdit.setVisible(false);
                    else if (s.equals("Copy"))
                      Copy();
                    else if (s.equals("Edit"))
                      Edit();
                    else if (s.equals("Edit2"))
                      DefEdit();
                    else if (s.equals("Delete") || s.equals("Save") || s.equals("Add") || s.equals("Remove"))
                    {
                      if (s.equals("Delete"))
                        Delete();
                      else if (s.equals("Save"))
                        Save();
                      else if (s.equals("Add"))
                        Add();
                      else if (s.equals("Remove"))
                        Remove();
                      EnableButtons();
                    }
                    else if (s.equals("End"))
                      hide();
                    else if (s.equals("Show"))
                      ShowUsed(0);
                    else if (s.equals("show User"))
                      ShowUsed(1);
                    else if (s.equals("show Frame"))
                      Darstellung.showUsed(g,Sort.geti(OutBegriff.getSelectedNode().getUserData(),0),thisJFrame());
                    else if (s.equals("Export"))
                      Export(0);
                    else if (s.equals("Kunden"))
                      Export(1);
                    else if (s.equals("Kunden2"))
                      Export(2);
                    //else if (s.equals("Programm"))
                    //  Export(3);
                    else if (s.startsWith("Refresh"))
                      Laden(s.equals("Refresh"));
                    else if (s.equals("Zeitraum"))
                      Zeitraum.get(g).show();
                    else if (s.equals("ProgNeu"))
                      ProgNeu();
                    else if (s.equals("ProgDel"))
                      ProgDel();
                    else if (s.equals("OkP"))
                      ProgSave();
                    else if (s.equals("AbbruchP"))
                      DlgProg.setVisible(false);
                    
                    else if (s.equals("History") || s.equals("sync") || s.equals("show"))
                    {
	                    Vector Vec=(Vector)OutModul.getSelectedNode().getUserData();
	                    int iLevel=OutModul.getSelectedNode().getLevel();
	                    g.fixtestInfo("rechte Maustaste:"+iLevel+"/"+Vec);
	                    if ((iLevel==1) && s.equals("History"))
	                      new Tabellenspeicher(g,"select tat,timestamp Zeitpunkt,b.kennung Benutzer from defverlauf v join logging l on v.aic_logging=l.aic_logging"+
	                                           " join benutzer b on l.aic_benutzer=b.aic_benutzer where aic_begriff="+Sort.geti(Vec,0),true).showGrid(Sort.gets(OutModul.getSelectedNode().getLabel()),thisJFrame());
	                    else if (s.equals("sync") && (iLevel==2) && (TabAll.posInhalt("Data", Vec.size()==1 ? Sort.geti(Vec,0):Vec)))
	                        Static.makeVisible(null, ((JCOutlinerNode)TabAll.getInhalt("Node")));
	                    else if (s.equals("show") && (iLevel==2))
	                    	showDlgEE(OutModul.getSelectedNode());
                    }
                    else if (s.equals("suche"))
                    	suchen(OutBegriff,((Component)ev.getSource()).getLocationOnScreen());
                  }
                };
                g.BtnAdd(getButton("Init"),"Init",AL);
                g.BtnAdd(getButton("Zeitraum"),"Zeitraum",AL);
                g.BtnAdd(getButton("Refresh"),"Refresh",AL);
                g.BtnAdd(getButton("Refresh3"),"Refresh3",AL);
                g.BtnAdd(BtnNew,"Neu",AL);
                g.BtnAdd(BtnEditOk,"EditOk",AL);
		g.BtnAdd(BtnEditAbbruch,"EditAbbruch",AL);
                g.BtnAdd(BtnCopy,"Copy",AL);
                g.BtnAdd(BtnEdit,"Edit",AL);
                g.BtnAdd(BtnDelete,"Delete",AL);
		g.BtnAdd(BtnAdd,"Add",AL);
                g.BtnAdd(BtnRemove,"Remove",AL);
                g.BtnAdd(BtnSave,"Save",AL);
                g.BtnAdd(BtnEnd,"End",AL);
                g.BtnAdd(getButton("show"),"Show",AL);
                g.BtnAdd(getButton("show Frame"),"show Frame",AL);
                g.BtnAdd(getButton("show User"),"show User",AL);
                g.BtnAdd(getButton("Export"),"Export",AL);
                g.BtnAdd(getButton("Kunden"),"Kunden",AL);
                g.BtnAdd(getButton("Kunden2"),"Kunden2",AL);
                g.BtnAdd(BtnP,"ProgNeu",AL);
                g.BtnAdd(BtnM,"ProgDel",AL);
                g.BtnAdd(getButton("Edit2"),"Edit2",AL);
                g.BtnAdd(getButton("Tb_Suche"),"suche",AL);
                CbxFAll=getCheckbox("All");
                if (CbxFAll!=null) CbxFAll.setSelected(true);
                CbxFWeb=getCheckbox("Web");
                if (CbxFWeb!=null) CbxFWeb.setSelected(true);
                /*if (!g.ApplPort())
                {
                  BtnSave.setEnabled(false);
                }*/

		OutModul.addItemListener(new JCOutlinerListener()
		{
			public void itemStateChanged(JCItemEvent e)
			{
                          //g.progInfo("OutModul:itemStateChanged");
			}

			public void outlinerFolderStateChangeBegin(JCOutlinerEvent e)
			{
                          //g.progInfo("OutModul:outlinerFolderStateChangeBegin");
			}

			public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)
			{
                          //g.progInfo("OutModul:outlinerFolderStateChangeEnd");
			}

			public void outlinerNodeSelectBegin(JCOutlinerEvent e)
			{
                          //g.progInfo("OutModul:outlinerNodeSelectBegin");
			}

			public void outlinerNodeSelectEnd(JCOutlinerEvent e)
			{
                          //g.fixtestInfo("OutModul.outlinerNodeSelectEnd");
                          bModul=true;
                          JCOutlinerNode Nod=OutModul.getSelectedNode();
                          if (Nod != null && Nod.getLevel()==1)
                            LblMemo.setText((String)((Vector)OutModul.getSelectedNode().getUserData()).elementAt(1));
                          EnableButtons();
			}
		});
		
		JPopupMenu popup= new JPopupMenu();
		g.addMenuItem("show",popup,null,AL);
		g.addMenuItem("sync",popup,null,AL);
		g.addMenuItem("History",popup,null,AL);
		OutModul.getOutliner().addMouseListener(new MouseListener()
          {
            public void mousePressed(MouseEvent ev) {}
            public void mouseClicked(MouseEvent ev)
            {
            	if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM) && g.Def())
            		popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
            }
            public void mouseEntered(MouseEvent ev) {}
            public void mouseExited(MouseEvent ev)  {}
            public void mouseReleased(MouseEvent ev){}
          });
		
		OutBegriff.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
          	if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM) && g.Def())
          		showDlgEE(OutBegriff.getSelectedNode());
          }
          public void mouseEntered(MouseEvent ev) {}
          public void mouseExited(MouseEvent ev)  {}
          public void mouseReleased(MouseEvent ev){}
        });

		OutBegriff.addItemListener(new JCOutlinerListener()
		{
			public void itemStateChanged(JCItemEvent e)
			{
			}

			public void outlinerFolderStateChangeBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectEnd(JCOutlinerEvent e)
			{
				JCOutlinerNode Nod=OutBegriff.getSelectedNode();
                          if (MemoBegriff != null)
                            MemoBegriff.setText(Nod.getLevel()<2 || ((Vector)Nod.getUserData()).size()>1 ? "":
                                                g.getMemo1(Sort.geti(Nod.getUserData(),0)));
                          bModul=false;
//                          if (Nod.getLevel()>1 && !TxtSuche.getText().endsWith("%"))
//                        	  TxtSuche.setText(Sort.gets(Nod.getLabel(),CbxBez!=null && CbxBez.isSelected() ? 0:2));
                          EnableButtons();
			}
		});

		show();
	}
}

private void DefEdit()
{
	JCOutlinerNode N1=OutBegriff.getSelectedNode();
	if (N1!=null && N1.getLevel()==2)
	{
		String sArt=Sort.gets(N1.getParent().getLabel(),2);
		g.fixtestError("DefEdit "+sArt+": "+N1.getUserData());
		int iAic=Sort.geti(N1.getUserData(),0);
		Tsearch.Edit(g,0,sArt,iAic,true);
//		if (sArt.equals("Abfrage"))
//		{
//			int iStt=SQL.getInteger(g,"select "+g.isnull()+"-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff="+iAic);
//	        DefAbfrage.get(g, new ShowAbfrage(g, iAic, Abfrage.cstBegriff), iStt,null,false,-1).show();
//	    }
//		else if (sArt.equals("Frame"))
//			DefFormular.get(g,iAic);
//	    else if (sArt.equals("Modell"))
//	    	DefModell.get(g, iAic).show();
//	    else if (sArt.equals("Druck"))
//	    {
//	        int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iAic);
//	        Druckdefinition.get(g, true, iAic, iStt);
//	    }
	}
}

private void showDlgEE(JCOutlinerNode Nod)
{
	Vector Vec=(Vector)Nod.getLabel();
	g.fixtestError("Nod="+Vec);
	JDialog Dlg=new JDialog(thisJFrame(),"Einzeldaten");
	Dlg.getContentPane().setLayout(new BorderLayout(2, 2));
    JPanel Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
    g.addLabel2(Pnl1, "Bezeichnung");
    g.addLabel2(Pnl1, "Kennung");
    g.addLabel2(Pnl1, "Aic");
    Dlg.getContentPane().add("West", Pnl1);
    JPanel Pnl2 = new JPanel(new GridLayout(0, 1, 2, 2));
    Pnl2.add(new Text(Sort.gets(Vec, 0),99));
    Pnl2.add(new Text(Sort.gets(Vec, 2),99));
    Pnl2.add(new Text(Sort.gets(Vec, 1),99));
    Dlg.getContentPane().add("Center", Pnl2);
    Dlg.pack();
	Dlg.setVisible(true);
	Static.centerComponent(Dlg, thisFrame);
}

private void showDel(int i,String s1,String sn)
{
  if (i>0)
    g.diskInfo(i+" "+(i>1 ? sn:s1)+" aus Modul_Zuordnung gelöscht");
}

private void Init()
{
  int iMessage = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Del_Jeder");
  if(iMessage==Message.YES_OPTION)
  {
    showDel(g.exec("delete from begriff_zuordnung where beg_aic_begriff in (select aic_begriff from begriff where jeder=1)"+
                     " and aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+iAIC_Modul+")"),"Jeder-Begriff","Jeder-Begriffe");
    showDel(g.exec("delete from modul where aic_tabellenname="+g.TabTabellenname.getAic("EIGENSCHAFT")+
                     " and aic_fremd in (select distinct aic_eigenschaft from eigenschaft where bits&0x20000000=0)"),"nicht-Lizenz-Eigenschaft","nicht-Lizenz-Eigenschaften");
  }
  Laden(true);
}

private void Neu()
{
  Static.centerComponent(FrmEdit,thisFrame);
    bNew=true;
    bCopy=false;
    TxtBezeichnung.setText("");
    TxtKennung.setText("");
    dtEnde.setDate(null);
    CboProgramm.setSelectedAIC(0);
    TxtMemo.setText("");
    CbxWeb.setSelected(false);
    TxtKennung.setEditable(true);
    FrmEdit.setVisible(true);
}

private void Copy()
{
    bNew=true;
    bCopy=true;
    TxtKennung.setEditable(true);
    TxtBezeichnung.setText("");
    TxtKennung.setText("");
    TxtMemo.setText("");
    FrmEdit.setVisible(true);
 }

 private void Edit()
 {
   if (OutModul.getSelectedNode().getLevel()!=1)
     return;
   Static.centerComponent(FrmEdit,thisFrame);
     TxtKennung.setEditable(false);
     bNew=false;
     bCopy=false;
     Vector VecVisible=(Vector)OutModul.getSelectedNode().getLabel();
     Vector VecInvisible=(Vector)OutModul.getSelectedNode().getUserData();
     TxtBezeichnung.setText((String)VecVisible.elementAt(0));
     TxtKennung.setText((String)VecVisible.elementAt(2));
     dtEnde.setDate((Date)VecInvisible.elementAt(2));
     CboProgramm.setSelectedAIC(Sort.geti(VecInvisible,3));
     int iBegriff=Sort.geti(VecInvisible,0);
     int iPos=iBegriff>0 ? g.TabBegriffe.getPos("Aic",iBegriff):-1;
     CbxWeb.setSelected(iPos<0 ? false:g.TabBegriffe.getB(iPos,"Web"));
     TxtMemo.setText((String)VecInvisible.elementAt(1));
     FrmEdit.setVisible(true);
  }

  private void Delete()
  {
      SQL Qry=new SQL(g);
      JCOutlinerNode[] Node=OutModul.getSelectedNodes();
      for(int i=0;i<Node.length;i++)
      {
        Integer iAIC_Begriff=(Integer)((Vector)Node[i].getUserData()).elementAt(0);
        String sBez="\""+Sort.gets(Node[i].getLabel())+"("+iAIC_Begriff+")\"";
        Tabellenspeicher Tab=new Tabellenspeicher(g,"select bg.kennung Gruppe, defbezeichnung,begriff.Kennung from begriffgruppe bg join begriff on bg.aic_begriffgruppe=begriff.aic_begriffgruppe"+
                                                  " join begriff_zuordnung z on begriff.aic_begriff=z.aic_begriff and beg_aic_begriff="+iAIC_Begriff,true);
        if (Tab.size()>0)
          new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,(JFrame)thisFrame,g).showDialog("BereitsVerwendet",new String[] {"\n"+sBez},Tab);
        else
        {
          int iMessage = new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Loeschen", new String[] {sBez});
          if (iMessage == Message.YES_OPTION)
          {

            while (TabAenderung.posInhalt("AIC_Modul", iAIC_Begriff))
              TabAenderung.clearInhalt();
            Qry.clear();
            Qry.add("AIC_Begriff", iAIC_Begriff);
            Qry.delete("Begriff_Zuordnung");
            g.exec("delete from defverlauf where aic_begriff=" + iAIC_Begriff);
            g.exec("delete from modul where aic_begriff=" + iAIC_Begriff);
            //Qry.add("AIC_Begriff",iAIC_Begriff);
            Qry.delete("Begriff");
            g.TabBegriffe.clearInhaltS("aic", iAIC_Begriff);
            g.exec("delete from bezeichnung where aic_fremd=" + iAIC_Begriff + " and aic_tabellenname=" + g.iTabBegriff);

            Node[i].getParent().removeChild(Node[i]);
          }
        }
      }
      Qry.free();
      OutModul.folderChanged(OutModul.getRootNode());
  }

  private void ShowUsed(int iArt)
  {
    g.fixtestInfo("DefModul.Show");
      String s="";
      Tabellenspeicher Tab;
      if (bModul)
      {
        JCOutlinerNode[] NodeModul = OutModul.getSelectedNodes();
        Vector<Object> Vec = new Vector<Object>();
        for (int i = 0; i < NodeModul.length; i++)
          Vec.addElement(((Vector)NodeModul[i].getUserData()).elementAt(0));
        if (iArt==0)
        {
	        s="select b.defbezeichnung Modul,bg.kennung Art,b2.defBezeichnung Element"+g.AU_BezeichnungP("Code","b.prog")+" Programm from begriffgruppe g"+g.join("begriff","b","g","begriffgruppe")+
	            " join begriff_zuordnung z on z.aic_begriff=b.aic_begriff join begriff b2 on z.beg_aic_begriff=b2.aic_begriff and z.aic_begriff"+Static.SQL_in(Vec)+" join begriffgruppe bg on bg.aic_begriffgruppe=b2.aic_begriffgruppe";
	        Tab=new Tabellenspeicher(g,s,true);
	        Tabellenspeicher Tab2=new Tabellenspeicher(g,"select * from modul where aic_begriff"+Static.SQL_in(Vec),true);
	        for (Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
	        {
	          Tab.newLine();
	          Tab.setInhalt("Modul",g.TabBegriffe.getBezeichnungS(Tab2.getI("AIC_begriff")));
	          int iPosT=g.TabTabellenname.getPos("Aic",Tab2.getI("AIC_Tabellenname"));
	          Tab.setInhalt("Art",g.TabTabellenname.getS(iPosT,"Bezeichnung"));
	          String sTab=g.TabTabellenname.getS(iPosT,"Kennung");
	          Tab.setInhalt("Element",(sTab.equals("EIGENSCHAFT")?g.TabEigenschaften:sTab.equals("STAMMTYP")?g.TabStammtypen:
	              sTab.equals("BEWEGUNGSTYP")?g.TabErfassungstypen:sTab.equals("SPRACHE")? TabSprache:sTab.equals("HAUPTSCHIRM")?TabHS:null).getBezeichnungS(Tab2.getI("AIC_Fremd")));
	        }
        }
        else if (iArt==1)
        {
        	s="select "+g.AU_BezeichnungF("Benutzer","b",1)+" Benutzer,b.kennung,"+g.AU_BezeichnungF("Mandant","b",1)+" Mandant,b.geloescht,"+g.AU_BezeichnungF("Benutzergruppe","z",1)+" Benutzergruppe,b2.aic_fremd aic"+
        			" from benutzer b join benutzer_zuordnung z on b.aic_benutzer=z.aic_benutzer join berechtigung b2 on z.aic_benutzergruppe=b2.aic_benutzergruppe where b2.aic_fremd"+Static.SQL_in(Vec)+" and b2.aic_tabellenname="+g.iTabBegriff;
        	Tab=new Tabellenspeicher(g,s,true);
        }
        else
        	Tab=null;
      }
      else
      {
        JCOutlinerNode[] NodeBegriff = OutBegriff.getSelectedNodes();
        Vector<Object> Vec = new Vector<Object>();
        int iTab=0;
        for (int i = 0; i < NodeBegriff.length; i++)
          Vec.addElement(((Vector)NodeBegriff[i].getUserData()).elementAt(0));
        if (Vec.size()>0 && ((Vector)NodeBegriff[0].getUserData()).size()>1)
          iTab=((Integer)(((Vector)NodeBegriff[0].getUserData()).elementAt(1))).intValue();
        if (iArt==0)
	        if (iTab==0)
	          s="select b.defbezeichnung Modul,b.web,bg.kennung Begriffgruppe,b2.defBezeichnung Begriff"+g.AU_BezeichnungP("Code","b.prog")+" Programm from begriffgruppe g"+g.join("begriff","b","g","begriffgruppe")+
	              " join begriff_zuordnung z on z.aic_begriff=b.aic_begriff join begriff b2 on z.beg_aic_begriff=b2.aic_begriff and z.beg_aic_begriff"+Static.SQL_in(Vec)+" join begriffgruppe bg on bg.aic_begriffgruppe=b2.aic_begriffgruppe where g.kennung='Modul'";
	        else
	        {
	          String sTab=g.TabTabellenname.getKennung(iTab);
	          s="select begriff.defbezeichnung Modul,'"+sTab+"' Art"+g.AU_Bezeichnung3(sTab)+" Bezeichnung"+g.AU_BezeichnungP("Code","begriff.prog")+" Programm from begriff"+g.join2("modul","begriff")+" where aic_tabellenname="+iTab+" and aic_fremd"+Static.SQL_in(Vec);
	        }
        else if (iArt==1)
        {
        	if (iTab==0) iTab=g.iTabBegriff;
        	s="select "+g.AU_BezeichnungF("Benutzer","b",1)+" Benutzer,b.kennung,"+g.AU_BezeichnungF("Mandant","b",1)+" Mandant,b.geloescht,"+g.AU_BezeichnungF("Benutzergruppe","z",1)+" Benutzergruppe,b2.aic_fremd aic"+
        			" from benutzer b join benutzer_zuordnung z on b.aic_benutzer=z.aic_benutzer join berechtigung b2 on z.aic_benutzergruppe=b2.aic_benutzergruppe where b2.aic_fremd"+Static.SQL_in(Vec)+" and b2.aic_tabellenname="+iTab;
        }
        Tab=new Tabellenspeicher(g,s,true);
      }
      Tab.showGrid("Verwendet "+(bModul?"Module":"Elemente"));
  }
  
  /*private void ShowUser()
  {
	  
	  select b.kennung,b.geloescht from benutzer b join berechtigung b2 on b.aic_benutzergruppe=b2.aic_benutzergruppe where aic_fremd=76 and aic_tabellenname=6
  }*/

  private void Export(int iArt)
  {
    // Art=2 .. Elemente der Kunden
    // Art=1 .. Module der Kunden
    // Art=0 .. Module inkl Programm

    JCOutlinerNode[] NodeModul = OutModul.getSelectedNodes();
    Vector<Object> Vec = new Vector<Object>();
    if (NodeModul.length>1 && NodeModul[0].getLevel()>0)
      for (int i = 0; i < NodeModul.length; i++)
        Vec.addElement(((Vector)NodeModul[i].getUserData()).elementAt(0));

    String s=iArt==2 ? "select distinct b1.kennung Kunde,b3.kennung,b3.defbezeichnung"+g.AU_Bezeichnung1("Begriffgruppe","b3")+" Gruppe from begriff b1"+
        " join begriff_zuordnung z on b1.aic_begriff=z.aic_begriff and b1.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Kunde")+
        " join begriff b2 on b2.aic_begriff=z.beg_aic_begriff join begriff_zuordnung z2 on b2.aic_begriff=z2.aic_begriff join begriff b3 on b3.aic_begriff=z2.beg_aic_begriff"+
        " order by b1.kennung,b3.aic_begriffgruppe,b3.defbezeichnung":
        iArt==1 ? "select b1.kennung Kunde,b2.kennung,b2.defbezeichnung from begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.aic_begriff and b1.aic_begriffgruppe="+
          g.TabBegriffgruppen.getAic("Kunde")+" join begriff b2 on b2.aic_begriff=z.beg_aic_begriff order by b1.kennung,b2.kennung":
        "select kennung,DefBezeichnung"+g.AU_BezeichnungP("Code","Begriff.prog")+"Programm"+g.AU_Memo("Begriff")+
        ",(select ein from logging where aic_logging=begriff.aic_logging) Log from begriff where "+
        (Vec.size()==0 ? "aic_begriffgruppe="+iAIC_Modul:"aic_begriff"+Static.SQL_in(Vec))+" and prog is not null order by defbezeichnung";
    Tabellenspeicher Tab=new Tabellenspeicher(g,s,true);
    Tab.showGrid(iArt==2 ? "Kunden2":iArt==1 ? "Kunden":"Module");
  }

@SuppressWarnings("unchecked")
private void EditOk()
{
    JCOutlinerFolderNode Node=bNew?null:(JCOutlinerFolderNode)OutModul.getSelectedNode();
    int iAIC_Begriff =bNew ? 0:((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
    if (Message.KennungWarnung(g,TxtKennung.getText(),iAIC_Begriff,iAIC_Modul,FrmEdit))
      return;
    /*
    if(TxtKennung.isNull())
    {
      new Message(Message.ERROR_MESSAGE, null, g).showDialog("KennungLeer");
      return;
    }
    else if(SQL.exists(g,"select aic_begriff from Begriff WHERE begriff.aic_begriff<> " + iAIC_Begriff + " and AIC_Begriffgruppe="+iAIC_Modul+
                       " and Begriff.Kennung='" +TxtKennung.getText() + "' AND Begriff.AIC_Mandant=" + g.getMandant()))
    {
      new Message(Message.WARNING_MESSAGE, null, g).showDialog("KennungVorhanden");
      return;
    }*/

          SQL Qry = new SQL(g);
          Qry.add("Kennung",TxtKennung.getText());
          Qry.add("ENDE",dtEnde.getDate());
          Qry.add0("prog",CboProgramm.getSelectedAIC());
          Qry.add0("Web",CbxWeb.isSelected());
          Qry.add("AIC_Begriffgruppe",iAIC_Modul);
          Qry.add("DefBezeichnung",TxtBezeichnung.getText());
          Qry.add("aic_logging",g.getLog());
          iAIC_Begriff=bNew?Qry.insert("Begriff",true):((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
          if(!bNew)
                  Qry.update("Begriff",iAIC_Begriff);
          //g.setBezeichnung(bNew?"":(String)((Vector)Node.getLabel()).elementAt(0),TxtBezeichnung.getText(),g.TabTabellenname.getAic("BEGRIFF"),iAIC_Begriff,0);
          if (TxtMemo.Modified())
            g.setMemo(TxtMemo.getValue(),TxtBezeichnung.getText(),g.iTabBegriff,iAIC_Begriff,0);
          Vector<Comparable> VecVisible=new Vector<Comparable>();
          VecVisible.addElement(TxtBezeichnung.getText());
          VecVisible.addElement(new Integer(iAIC_Begriff));
          VecVisible.addElement(TxtKennung.getText());
          VecVisible.addElement(CboProgramm.getSelectedBezeichnung());
          Vector<Comparable> VecInvisible=new Vector<Comparable>();
          VecInvisible.addElement(new Integer(iAIC_Begriff));
          VecInvisible.addElement(TxtMemo.getValue());
          VecInvisible.addElement(dtEnde.getDate());
          VecInvisible.addElement(CboProgramm.getSelectedAIC());
          if(bNew)
          {
                  Node = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutModul.getRootNode());
                  int iPosBG=g.TabBegriffgruppen.getPos("Aic",iAIC_Modul);
                  ImageIcon Gif = iPosBG>=0 ?g.LoadImageIcon(g.TabBegriffgruppen.getS(iPosBG,"FILENAME")):null;
                  JCOutlinerNodeStyle Sty=g.getStyle(Gif!=null?Gif.getImage():null);
                  if (CbxWeb.isSelected())
                	  Sty.setForeground(g.ColWeb);
                  Node.setStyle(Sty);                
          }
          else
          {
                  Node.setLabel(VecVisible);
                  if (CbxWeb.Modified())
                  {
                	  JCOutlinerNodeStyle Sty=new JCOutlinerNodeStyle(Node.getStyle());
                	  Sty.setForeground(CbxWeb.isSelected() ? g.ColWeb:Color.BLACK);
                	  Node.setStyle(Sty);
                  }
          }
          Node.setUserData(VecInvisible);
          g.SaveDefVerlauf(iAIC_Begriff,bNew ? "neu":"geändert");
          g.putTabBegriffe(iAIC_Modul,iAIC_Begriff,TxtKennung.getText(),TxtBezeichnung.getText(),TxtBezeichnung.getText(),null,0,null,-1,-1,-1,-1,-1,-1,false,CbxWeb.isSelected(),0,null,false,null,null,null,null,null,bNew);

          if(bCopy)
          {
                  Vector VecNodeCopy=OutModul.getSelectedNode().getChildren();
                  if (VecNodeCopy != null)
                    for(int i=0;i<VecNodeCopy.size();i++)
                    {
                          JCOutlinerNode NodeCopy=(JCOutlinerNode)VecNodeCopy.elementAt(i);
                          JCOutlinerNode NodeChild=new JCOutlinerNode(new Vector((Vector)NodeCopy.getLabel()),Node);
                          NodeChild.setUserData(new Vector((Vector)NodeCopy.getUserData()));
                          JCOutlinerNodeStyle Sty=NodeCopy.getStyle();
                          if (CbxWeb.isSelected())
                        	  Sty.setForeground(g.ColWeb);
                          NodeChild.setStyle(Sty);

                          TabAenderung.bInsert=false;
                          TabAenderung.addInhalt("AIC_Modul",iAIC_Begriff);
                          TabAenderung.addInhalt("AIC_Fremd",NodeCopy.getUserData());
                          TabAenderung.addInhalt("Node",NodeChild);
                          TabAenderung.addInhalt("Art","Akt");

                          Vector Vec=(Vector)NodeCopy.getUserData();
                          if (Vec.size()==1)
                          {
                            Qry.add("AIC_Begriff", iAIC_Begriff);
                            Qry.add("Beg_AIC_Begriff", Vec.elementAt(0));
                            Qry.insert("Begriff_Zuordnung", false);
                          }
                          else
                          {
                            Qry.add("AIC_Begriff", iAIC_Begriff);
                            Qry.add("AIC_Tabellenname", Vec.elementAt(1));
                            Qry.add("AIC_Fremd", Vec.elementAt(0));
                            Qry.insert("Modul", false);
                          }
                  }
          }
          Qry.free();
          OutModul.folderChanged(OutModul.getRootNode());
          FrmEdit.setVisible(false);
          Static.makeVisible(OutModul, Node);
}

private void ProgNeu()
    {
      if (DlgProg==null)
      {
        DlgProg = new JDialog(FrmEdit, "neues Programm", true);
        DlgProg.getContentPane().setLayout(new BorderLayout(2, 2));
        JPanel Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
        g.addLabel2(Pnl1, "Bezeichnung");
        g.addLabel2(Pnl1, "Kennung");
        DlgProg.getContentPane().add("West", Pnl1);
        Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
        Pnl1.add(TxtBezP);
        Pnl1.add(TxtKenP);
        DlgProg.getContentPane().add("Center", Pnl1);
        JPanel Pnl = new JPanel(new GridLayout(1, 0, 2, 2));
        JButton BtnOk = g.getButton("Ok");
        JButton BtnAbbruch = g.getButton("Abbruch");
        g.BtnAdd(BtnOk, "OkP", AL);
        g.BtnAdd(BtnAbbruch, "AbbruchP", AL);
        Pnl.add(BtnOk);
        Pnl.add(BtnAbbruch);
        DlgProg.getContentPane().add("South", Pnl);
        DlgProg.pack();
      }
      Static.centerComponent(DlgProg,FrmEdit);
      TxtBezP.setText("");
      TxtKenP.setText("");
      DlgProg.setVisible(true);
    }

private void ProgDel()
    {
      int iAic=CboProgramm.getSelectedAIC();
      String sBez=CboProgramm.getSelectedBezeichnung();
      //g.fixInfo("Programm="+iAic+"/"+CboProgramm.getSelectedBezeichnung());
      Tabellenspeicher Tab=new Tabellenspeicher(g,"select defbezeichnung,kennung,aic_begriff from begriff where prog=?",""+iAic,true);
      if (Tab.isEmpty())
      {
        if (new Message(Message.YES_NO_OPTION,FrmEdit,g,10).showDialog("Loeschen", new String[] {"(" + sBez + ")"}) == Message.YES_OPTION)
        {
          g.exec("delete from code where aic_code="+iAic);
          CboProgramm.fillBegriffTable(g.TabBegriffgruppen.getAic("Programm"),true,true);
        }
      }
      else
        Tab.showGrid(sBez+" schon verwendet",FrmEdit);
    }

private void ProgSave()
    {
      SQL Qry=new SQL(g);
      Qry.add("Kennung",TxtKenP.getText());
      Qry.add("AIC_Begriffgruppe",g.TabBegriffgruppen.getAic("Programm"));
      int iAic=Qry.insert("Code",true);
      g.setBezeichnung("",TxtBezP.getText(),g.TabTabellenname.getAic("CODE"),iAic,1);
      Qry.free();
      CboProgramm.addItem(TxtBezP.getText(),iAic,TxtKenP.getText());
      DlgProg.setVisible(false);
    }

  private void pos(int iModul)
  {
    //g.fixtestInfo("DefModul.pos "+iModul);
    if (iModul<=0)
      return;
    Vector Vec = OutModul.getRootNode().getChildren();
    //boolean b=true;
    if (Vec != null)
      for(int i=0;i<Vec.size()/* && b*/;i++)
      {
        JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
        int iMom=Sort.geti(Nod.getUserData(),0);
        //g.fixtestInfo("DefModul.Mom="+iMom);
        if (iMom==iModul)
        {
          //b=false;
          //g.fixtestInfo("DefModul markiere "+Nod.getLabel());
          Static.makeVisible(OutModul,Nod);
          //g.fixtestInfo("DefModul selektiert "+OutModul.getSelectedNode().getLabel());
          return;
        }
      }
  }

  private void pos(String sSuch,boolean bWeiter)
    {
      //g.fixtestInfo("pos "+sSuch+(bWeiter?" weiter":" zurück"));
      if (Static.Leer(sSuch))
        return;
      JCOutlinerNode NodMom=OutModul.getSelectedNode();
      Vector Vec = OutModul.getRootNode().getChildren();
      int iStep=bWeiter?1:-1;
      sSuch=sSuch.toLowerCase();
      if (Vec != null)
        for(int i=NodMom==null ?0:Vec.indexOf(NodMom)+iStep;i<Vec.size() && i>=0;i+=iStep)
        {
          JCOutlinerNode Nod = (JCOutlinerNode)Vec.elementAt(i);
          String s1 = Sort.gets(Nod.getLabel(),0).toLowerCase();
          String s2 = Sort.gets(Nod.getLabel(),1).toLowerCase();
          if (s1.contains(sSuch) || s2.contains(sSuch))
          {
            Static.makeVisible(null, Nod);
            return;
          }
      }

  }
  
  private void pos2(String sSuch,boolean bWeiter,boolean bBez)
  {
//	  g.fixInfo("pos2 "+sSuch+"/"+bWeiter+", Bez="+bBez);
	  JCOutlinerNode NodMom=OutBegriff.getSelectedNode();
	  if (Static.Leer(sSuch) || NodMom==null)
	        return;
	  int iLev=NodMom.getLevel();
	  if (iLev<1)
		  return;
	  
	  Vector Vec = (iLev==1 ? NodMom:NodMom.getParent()).getChildren();
      int iStep=bWeiter?1:-1;
      sSuch=sSuch.toLowerCase();
      if (sSuch.endsWith("%"))
    	  sSuch=sSuch.substring(0, sSuch.length()-1);
      if (Vec != null)
        for(int i=iLev==1 ? 0:Vec.indexOf(NodMom)+iStep;i<Vec.size() && i>=0;i+=iStep)
        {
          JCOutlinerNode Nod = (JCOutlinerNode)Vec.elementAt(i);
          String s1 = Sort.gets(Nod.getLabel(),bBez ? 0:2).toLowerCase();
          if (s1.contains(sSuch))
          {
            Static.makeVisible(null, Nod);
            return;
          }
      }
  }

private void Build()
{
	String[] s = new String[] {g.getShow("Bezeichnung"),g.getShow("Aic"),g.getShow("Kennung"),g.getShow("Prog"),g.getBezeichnungS("tabellenname","STAMMTYP"),g.getBezeichnungS("tabellenname","BEWEGUNGSTYP")
			,g.getShow("System"),g.getShow("Web"),g.getShow("Anzahl"),g.getShow("Tod"),g.getShow("Ber_API"),g.getBegriffS("Checkbox","WebLizenz")};
	OutBegriff.setColumnButtons(s);
	OutBegriff.setNumColumns(s.length);
	OutBegriff.setRootVisible(false);
	OutBegriff.setBackground(Color.white);
	OutBegriff.setAllowMultipleSelections(true);
	OutBegriff.setColumnLabelSortMethod(Sort.sortMethod);
	OutBegriff.setColumnWidth(0,150);

	//s = new String[] {g.getBegriff("Show","Bezeichnung"),g.getBegriff("Show","Nr"),g.getBegriff("Show","Kennung"),g.getBegriff("Show","Stammtyp"),g.getBegriff("Show","Tod")};
	s = new String[] {g.getShow("Bezeichnung"),g.getShow("Aic"),g.getShow("Kennung"),g.getShow("Prog"),g.getBezeichnungS("tabellenname","STAMMTYP"),g.getShow("Web"),g.getShow("Anzahl"),g.getShow("Tod"),g.getShow("Gruppe")};	
	OutModul.setColumnButtons(s);
	OutModul.setNumColumns(s.length);
	OutModul.setRootVisible(false);
	OutModul.setBackground(Color.white);
	OutModul.setAllowMultipleSelections(true);
	OutModul.setColumnLabelSortMethod(Sort.sortMethod);
	OutModul.setColumnWidth(0,150);

	BtnNew=getButton("Neu");

	BtnCopy=getButton("Kopieren");
	if(BtnCopy==null)
		BtnCopy=new JButton();
	BtnEdit=getButton("Edit");
	if(BtnEdit==null)
		BtnEdit=new JButton();
	BtnDelete=getButton("Loeschen");
	if(BtnDelete==null)
		BtnDelete=new JButton();
	BtnAdd=getButton(">");
	if(BtnAdd==null)
		BtnAdd=new JButton();
	BtnRemove=getButton("<");
	if(BtnRemove==null)
		BtnRemove=new JButton();
	BtnSave=getButton("Speichern");
	if(BtnSave==null)
		BtnSave=new JButton();
	BtnEnd=getButton("Beenden");

        //BtnShow = getButton("show");

        JPanel PnlSuche = getFrei("Eingabe");
        if (PnlSuche != null)
        {
          EdtSuche = new Text("", 30);
          EdtSuche.setFont(g.fontStandard);
          PnlSuche.add(EdtSuche);
          EdtSuche.addKeyListener(new KeyListener()
          {
            public void keyPressed(KeyEvent ev) {}

            public void keyReleased(KeyEvent ev)
            {
              int iK = ev.getKeyCode();
              if (iK == KeyEvent.VK_DOWN || iK == KeyEvent.VK_ENTER)
                pos(EdtSuche.getText(), true);
              else if (iK == KeyEvent.VK_UP)
                pos(EdtSuche.getText(), false);
            }

            public void keyTyped(KeyEvent ev)  {}
          });
        }

        LblMemo.setEditable(false);
        JPanel PnlMemo = getFrei("Memo");
        if (PnlMemo != null)
          PnlMemo.add(LblMemo);
        PnlMemo = getFrei("Memo2");
        if (PnlMemo != null)
        {
          MemoBegriff=new AUTextArea();
          MemoBegriff.setEditable(false);
          PnlMemo.add(MemoBegriff);
        }
	BtnEditOk=g.getButton("Ok");
	BtnEditAbbruch=g.getButton("Abbruch");

	JPanel PnlOut_Begriff = getFrei("Outliner Begriff");
	JPanel PnlOut_Modul = getFrei("Outliner Modul");
	JPanel PnlSuche2 = getFrei("Suche");

	if(PnlOut_Begriff!=null)
		PnlOut_Begriff.add(OutBegriff);
	if(PnlOut_Modul!=null)
		PnlOut_Modul.add(OutModul);
	CbxBez=getCheckbox("Bezeichnung");
	if(PnlSuche2!=null)
	{
		TxtSuche.setFont(g.fontStandard);
		PnlSuche2.add(TxtSuche);
		TxtSuche.addKeyListener(new KeyListener()
        {
          public void keyPressed(KeyEvent ev) {}

          public void keyReleased(KeyEvent ev)
          {
            int iK = ev.getKeyCode();
            if (iK == KeyEvent.VK_DOWN || iK == KeyEvent.VK_ENTER)
              pos2(TxtSuche.getText(), true,CbxBez.isSelected());
            else if (iK == KeyEvent.VK_UP)
              pos2(TxtSuche.getText(), false,CbxBez.isSelected());
          }

          public void keyTyped(KeyEvent ev)  {}
        });
	}

	FrmEdit=new JDialog((JFrame)thisFrame,"Module bearbeiten",true);
	FrmEdit.getContentPane().setLayout(new BorderLayout(2,2));
        CboProgramm=new ComboSort(g);
        CboProgramm.fillBegriffTable(g.TabBegriffgruppen.getAic("Programm"),true,true);
        CbxWeb=g.getCheckbox("Web");
	JPanel Pnl=new JPanel(new BorderLayout(2,2));
	JPanel Pnl1=new JPanel(new GridLayout(0,1,2,2));
	g.addLabel2(Pnl1,"Bezeichnung");
        g.addLabel2(Pnl1,"Kennung");
        g.addLabel2(Pnl1,"Programm");
        g.addLabel2(Pnl1,"Ende");
        Pnl1.add(new JLabel());
	Pnl.add("West",Pnl1);
        Pnl1=new JPanel(new GridLayout(0,1,2,2));
	Pnl1.add(TxtBezeichnung);
	Pnl1.add(TxtKennung);
        JPanel PnlProgramm=new JPanel(new BorderLayout(2,2));
        PnlProgramm.add("Center",CboProgramm);
        if (g.ApplPort())
        {
          BtnP=g.getButton("+");
          BtnM=g.getButton("-");
          JPanel PnlSub=new JPanel(new GridLayout());
          PnlSub.add(BtnP);
          PnlSub.add(BtnM);
          PnlProgramm.add("East", PnlSub);
        }
        Pnl1.add(PnlProgramm);
        Pnl1.add(dtEnde);
        Pnl1.add(CbxWeb);
        TxtMemo.setPreferredSize(new Dimension(100,100));
        //Pnl.add("South",TxtMemo);
	Pnl.add("Center",Pnl1);
        FrmEdit.getContentPane().add("North",Pnl);
	FrmEdit.getContentPane().add("Center",TxtMemo);
	Pnl=new JPanel(new GridLayout(1,0,2,2));
	Pnl.add(BtnEditOk);
	Pnl.add(BtnEditAbbruch);
	FrmEdit.getContentPane().add("South",Pnl);
	FrmEdit.pack();
}

public void show()
{
  if (!thisFrame.isVisible())
    Laden(true);
  super.show();
}

private void Laden(boolean b)
{
	SQL Qry = new SQL(g);

	((JCOutlinerFolderNode)OutBegriff.getRootNode()).removeChildren();
        Tabellenspeicher TabAnzahl=new Tabellenspeicher(g,"select beg_aic_begriff aic,count(*) Anzahl from begriff_zuordnung where aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+iAIC_Modul+
        		(CbxFAll.isSelected() ? "":" and web=1")+") group by beg_aic_begriff",true);
	JCOutlinerNodeStyle NodeStyle=null;
        TabAll=new Tabellenspeicher(g,new String[]{"Data","Node"});
	if(Qry.open("select b.aic_begriff AIC,b.DefBezeichnung bezeichnung,b.kennung,b.aic_stammtyp,b.aic_bewegungstyp,b.aic_code,bg.aic_begriffgruppe,b.bits,b.tod,b.web,b.jeder,b.prog from begriff b"+g.join("begriffgruppe","bg","b","begriffgruppe")+" where bg.kennung in('Applikation','Frame','Button','Radiobutton','Modell','Druck','Abfrage','Titel','Label','Aufgabe','Hauptschirm') order by bg.kennung,bezeichnung"))
	{
		int iBG=0;
		String sBG="";
		Vector<Comparable> VecVisible;
		Vector<Integer> VecInvisible;
		JCOutlinerFolderNode NodeGruppe=null;
		for(;!Qry.eof();Qry.moveNext())
		{
			if(iBG!=Qry.getI("aic_begriffgruppe"))
			{
				iBG=Qry.getI("aic_begriffgruppe");
				VecVisible=new Vector<Comparable>();
				VecInvisible=new Vector<Integer>();
				VecInvisible.addElement(new Integer(iBG));
                                int iPosBG=g.TabBegriffgruppen.getPos("Aic",iBG);
				VecVisible.addElement(g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung"));
                                VecVisible.addElement(new Integer(iBG));
				sBG=g.TabBegriffgruppen.getS(iPosBG,"Kennung");
				VecVisible.addElement(sBG);
                                NodeGruppe=new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutBegriff.getRootNode());
				NodeGruppe.setUserData(VecInvisible);
				ImageIcon Gif = iPosBG>=0 ? g.LoadImageIcon(g.TabBegriffgruppen.getS(iPosBG,"FILENAME")):null;
				NodeStyle=g.getStyle(Gif!=null?Gif.getImage():null);
				NodeGruppe.setStyle(NodeStyle);
				NodeGruppe.setState(BWTEnum.FOLDER_CLOSED);
			}
			long lBits=Qry.getL("bits");
			if (Qry.getB("Tod") && g.ApplPort() || (!(sBG.equals("Abfrage") && (lBits&Abfrage.cstImportierbar+Abfrage.cstExportierbar+Abfrage.cstVersionsup)==0) || Qry.getB("Web"))
					&& (!Qry.getB("Jeder") || sBG.equals("Abfrage")) && !Qry.getB("Tod"))// || sBG.equals("Frame"))) // 21.10 will Isi weg (727)
                                            //|| sBG.equals("Modell") && Qry.getI("aic_stammtyp")==0 && (Qry.getL("bits")& Global.cstKnopf)==0*/))
//				&& (!sBG.equals("Frame") || (lBits&Formular.cstFxArt)!=Formular.cstScene))
                        {
                            VecVisible = new Vector<Comparable>();
                            VecInvisible = new Vector<Integer>();

                            VecVisible.addElement(Qry.getS("bezeichnung"));
                            VecVisible.addElement(Qry.getInt("AIC"));
                            VecVisible.addElement(Qry.getS("kennung"));
                            VecVisible.addElement(g.TabCodes.getBezeichnungS(Qry.getI("prog")));
//                            long lArt=lBits&Formular.cstFxArt;
//                            String sTyp=lArt==Formular.cstPopOver ? "+Pop-":lArt==Formular.cstBtnScene ? "+Btn-":(lBits&Formular.cstJavaFX)>0 ? "+FX-":"";
                            String sTyp=sBG.equals("Frame") && !Qry.isNull("aic_code") ? "* "+g.TabCodes.getBezeichnungS(Qry.getI("aic_code")) :"";
                            int iPosS=g.TabStammtypen.getPos("AIC", Qry.getI("aic_stammtyp"));
                            int iPosB=Qry.isNull("aic_bewegungstyp") ? -1:g.TabErfassungstypen.getPos("AIC", Qry.getI("aic_bewegungstyp"));
                            VecVisible.addElement(iPosS>=0 ? /*sTyp+*/g.TabStammtypen.getS(iPosS,"BEZEICHNUNG") : sTyp);
                            VecVisible.addElement(iPosB>=0 ? g.TabErfassungstypen.getS(iPosB,"BEZEICHNUNG") : "");
//                            if (sBG.equals("Abfrage"))
//                            {
//                            	int iPosAbf=g.TabAbfragen.getPos("aic_begriff",Qry.getInt("AIC"));
//                            	VecVisible.addElement(iPosAbf<0 ? "-":Static.JaNein((g.TabAbfragen.getI(iPosAbf,"Abits2")&Abfrage.cstAbfWeb)>0));
//                            }
//                            else
//                            	VecVisible.addElement(sBG.equals("Frame") ? Static.JaNein((lBits&Formular.cstTemplate)>0):"-");//Static.JaNein((lBits&Formular.cstJavaFX)>0));
                            if (sBG.equals("Abfrage"))
                            {
                            	g.checkAbfragen();
                            	int iPosA=g.TabAbfragen.getPos("Aic_Begriff",Qry.getInt("AIC"));
                            	VecVisible.addElement(iPosA<0 ? "":Static.JaNein2((g.TabAbfragen.getL(iPosA, "Abits2")&Abfrage.cstAbfSystem)>0));
                            }
                            else
                            	VecVisible.addElement("");
                            VecVisible.addElement(Static.JaNein2(Qry.getB("Web")));
                            int iAnz=TabAnzahl.posInhalt("Aic",Qry.getInt("AIC")) ? TabAnzahl.getI("Anzahl"):0;
                            VecVisible.addElement(iAnz);
                            boolean bTod=Qry.getB("Tod");
                            VecVisible.addElement(Static.JaNein2(bTod));
                            if (sBG.equals("Titel"))
                            	VecVisible.addElement(Static.JaNein2((lBits & g.cstBer)>0));
                            else if (sBG.equals("Abfrage"))
                            	VecVisible.addElement(Static.JaNein2(g.AbfIsBit2(Qry.getI("AIC"),Abfrage.cstAbfAPI)));
                            VecInvisible.addElement(Qry.getInt("AIC"));
                            if (Qry.getB("Web") && (CbxFWeb==null || CbxFWeb.isSelected()) || !Qry.getB("Web") && (CbxFAll==null || CbxFAll.isSelected()))// && (!sBG.equals("Titel") || (lBits & g.cstBer)>0))
                            {
	                            JCOutlinerNode Node = new JCOutlinerNode(VecVisible, NodeGruppe);
	                            Node.setUserData(VecInvisible);
	                            boolean bAnz0=!Qry.getB("Jeder") && iAnz==0;
	                            if (bTod || bAnz0 /*|| sBG.equals("Abfrage") && (lBits2&Abfrage.cstAbfFX)>0*/)
	                            {
	                              JCOutlinerNodeStyle NodeStyle2=new JCOutlinerNodeStyle(NodeStyle);
	                              NodeStyle2.setForeground(/*sBG.equals("Frame") ? lArt==Formular.cstPopOver ? Color.BLUE : g.ColJavaFX:*/bTod ? g.ColLoeschen:g.ColBreakpoint);
	                              Node.setStyle(NodeStyle2);
	                            }
	                            else
	                              Node.setStyle(NodeStyle);
	                            TabAll.addInhalt("Data",Qry.getInt("AIC"));
	                            TabAll.addInhalt("Node",Node);
                            }
			}
		}
	}

        Laden("EIGENSCHAFT",g.TabEigenschaften);
        Laden("BEWEGUNGSTYP",g.TabErfassungstypen);
        Laden("STAMMTYP",g.TabStammtypen);
        TabSprache=new Tabellenspeicher(g,"select aic_sprache aic,kennung"+g.AU_Bezeichnung("SPRACHE")+" from sprache",true);
        Laden("SPRACHE",TabSprache);
        TabHS=new Tabellenspeicher(g,"select aic_hauptschirm aic,kennung"+g.AU_Bezeichnung("HAUPTSCHIRM")+" from hauptschirm where kennung<>'*' and kennung is not null and selbst is null",true);
        Laden("HAUPTSCHIRM",TabHS);

        Qry.free();
        Laden2(b);
}

private boolean inModulLaden(String sTab,int iPos,Tabellenspeicher Tab)
{
  if (sTab.equals("EIGENSCHAFT"))
    return (Tab.getI(iPos,"bits")&(Global.cstEigLizenz+Global.cstWebLizenz))>0;// && (g.ApplPort() || !g.EigIsTod(Tab.getInt(iPos,"AIC")));
  else if (sTab.equals("STAMMTYP"))
    return (Tab.getI(iPos,"bits")&Global.cstModul)>0;
  else
    return true;
  //!sTab.equals("EIGENSCHAFT") || (Tab.getI(iPos,"bits")&Global.cstEigLizenz)>0 && (g.ApplPort() || !g.EigIsTod(Tab.getInt(iPos,"AIC")))
}

private void Laden(String sTab,Tabellenspeicher Tab) // läd Eigenschaften, Bewegungstyp, Stammtyp, Sprache und Hauptschirm
      {
        Vector<Comparable> VecVisible=new Vector<Comparable>();
        Vector<Comparable> VecInvisible=new Vector<Comparable>();
        int iPosT=g.TabTabellenname.getPos("Kennung",sTab);
        Integer IntAIC=g.TabTabellenname.getInt(iPosT,"AIC");
        VecInvisible.addElement(IntAIC);
        VecVisible.addElement(g.TabTabellenname.getS(iPosT,"Bezeichnung"));
        VecVisible.addElement(IntAIC);
        VecVisible.addElement(g.TabTabellenname.getS(iPosT,"Kennung"));
        JCOutlinerFolderNode NodeGruppe=new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutBegriff.getRootNode());
        JCOutlinerNodeStyle NodeStyle=g.getStyle(g.getGif(g.TabTabellenname,sTab));//(Image)g.TabTabellenname.getInhalt("Bild"));
        NodeGruppe.setStyle(NodeStyle);
        NodeGruppe.setState(BWTEnum.FOLDER_CLOSED);
//        if (sTab.equals("EIGENSCHAFT"))
//        	g.fixtestError("VecEigTod="+g.VecEigTod);
        for (int iPos=0;iPos<Tab.size();iPos++)
          if (inModulLaden(sTab,iPos,Tab))
          {
            VecVisible = new Vector<Comparable>();
            VecInvisible = new Vector<Comparable>();
            VecVisible.addElement(Tab.getS(iPos, "bezeichnung"));
            VecVisible.addElement(Tab.getInt(iPos, "AIC"));
            VecVisible.addElement(Tab.getS(iPos, "kennung"));
            VecVisible.addElement(""); // Prog
            if (sTab.equals("EIGENSCHAFT"))
            {
            	int iBits=Tab.getI(iPos,"bits");
              VecVisible.addElement(Tab.isNull(iPos,"aic_stammtyp") ? "":g.TabStammtypen.getBezeichnungS(Tab.getI(iPos, "aic_stammtyp")));
              VecVisible.addElement(Tab.isNull(iPos,"Bew") ? "":g.TabErfassungstypen.getBezeichnungS(Tab.getI(iPos, "Bew"))); // Bew
              VecVisible.addElement(""); // System
              VecVisible.addElement(""); // Web
              VecVisible.addElement(null); // Anzahl
              VecVisible.addElement(Static.JaNein2(g.EigIsTod(Tab.getInt(iPos, "AIC")))); // Tod
              VecVisible.addElement(Static.JaNein2((iBits & Global.cstEigLizenz)>0)); // Ber
              VecVisible.addElement(Static.JaNein2((iBits & Global.cstWebLizenz)>0)); // Web-Lizenz
            }
            else
              VecVisible.addElement("");
            //VecVisible.addElement(""); //BEW
            VecInvisible.addElement(Tab.getInt(iPos, "AIC"));
            VecInvisible.addElement(IntAIC);

            JCOutlinerNode Node = new JCOutlinerNode(VecVisible, NodeGruppe);
            Node.setUserData(VecInvisible);
            Node.setStyle(NodeStyle);
            TabAll.addInhalt("Data", VecInvisible);
            TabAll.addInhalt("Node", Node);
          }
      }

@SuppressWarnings("unchecked")
private void Laden2(boolean b) // läd rechte Liste mit Modulen
{
  //g.fixtestInfo("DefModul.Laden2");
        ((JCOutlinerFolderNode)OutModul.getRootNode()).removeChildren();

	TabAenderung=new Tabellenspeicher(g,new String[]{"AIC_Modul","AIC_Fremd","Node","Art"});
	TabAenderung.sGruppe="AIC_Modul";
	TabAenderung.sAIC="AIC_Fremd";
        Vector<Comparable> VecVisible;
        Vector<Comparable> VecInvisible;
	Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_begriff,kennung,DefBezeichnung,null node"+g.AU_Memo("Begriff")+",Ende,prog,web from begriff where aic_begriffgruppe="+iAIC_Modul+(b ? " and (ende is null or ende>="+g.von()+")":" and ende is not null")+" order by defbezeichnung",true);
        int iPosBG=g.TabBegriffgruppen.getPos("Aic",iAIC_Modul);
        ImageIcon Gif=iPosBG>=0 ? g.LoadImageIcon(g.TabBegriffgruppen.getS(iPosBG,"FILENAME")):null;
	JCOutlinerNodeStyle NodeStyle=g.getStyle(Gif!=null?Gif.getImage():null);
	JCOutlinerNodeStyle StyWeb=new JCOutlinerNodeStyle(NodeStyle);
	StyWeb.setForeground(g.ColWeb);
	
	for(Tab.moveFirst();!Tab.eof();Tab.moveNext()) // Module füllen
		if (Tab.getB("Web") && (CbxFWeb==null || CbxFWeb.isSelected()) || !Tab.getB("Web") && (CbxFAll==null || CbxFAll.isSelected()))
	{
                VecVisible=new Vector<Comparable>();
                VecInvisible=new Vector<Comparable>();
                VecVisible.addElement(Tab.getS("DefBezeichnung")); // 0
                VecVisible.addElement(Tab.getInt("aic_begriff"));  // 1
                VecVisible.addElement(Tab.getS("kennung"));		   // 2
                VecVisible.addElement(Tab.getI("prog")==0 ? Static.sKein:g.TabCodes.getBezeichnungS(Tab.getI("prog")));
                VecVisible.addElement(Tab.isNull("Ende") ? null : new Zeit(Tab.getTimestamp("Ende"), "dd.MM.yyyy"));
                VecVisible.addElement(Static.JaNein2(Tab.getB("web")));
                VecVisible.addElement(0);  // Anzahl
                VecVisible.addElement(""); // Tod
                VecVisible.addElement(""); // Gruppe  
                VecInvisible.addElement(Tab.getInt("aic_begriff"));
                VecInvisible.addElement(Tab.getM("Memo"));
                VecInvisible.addElement(Tab.getDate("Ende"));
                VecInvisible.addElement(Tab.getI("prog"));
		JCOutlinerFolderNode Node=new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutModul.getRootNode());
		Node.setUserData(VecInvisible);
		Node.setStyle(Tab.getB("Web") ? StyWeb:NodeStyle);
		Node.setState(BWTEnum.FOLDER_CLOSED);

		Tab.setInhalt("Node",Node);
	}
	int iBG_Frame=g.TabBegriffgruppen.getAic("Frame");
    SQL Qry = new SQL(g);
	if(Qry.open("select b2.kennung,b2.defBezeichnung bezeichnung,b2.tod,b2.bits,b2.web,b2.prog,b1.aic_begriff b1,b2.aic_begriff b2,b2.aic_stammtyp,b2.aic_begriffgruppe from begriff b1,begriff_zuordnung bz,begriff b2 where b1.aic_begriffgruppe="+iAIC_Modul+" and b1.aic_begriff=bz.aic_begriff and b2.aic_begriff=bz.beg_aic_begriff order by b1.aic_begriff,b2.aic_begriffgruppe,bezeichnung"))
	{
		JCOutlinerFolderNode NodeParent=null;
		int iAIC_Mod=0;
                Vector VecParent=null;
                int iAnz=0;
		for(;!Qry.eof();Qry.moveNext())
		{
			if(iAIC_Mod!=Qry.getI("b1"))
			{
                          if (VecParent !=null)
                            VecParent.setElementAt(iAnz,6);
                          iAIC_Mod=Qry.getI("b1");
                          VecParent=null;
                          if (Tab.posInhalt("aic_begriff",iAIC_Mod))
                          {
                            NodeParent = (JCOutlinerFolderNode)Tab.getInhalt("Node");
                            if (NodeParent != null)
//                            	break;
////                              g.defInfo2("Modul " + Tab.getS("DefBezeichnung")+"("+iAIC_Mod+") hat keinen Knoten");                            
//                            else
                              VecParent = (Vector)NodeParent.getLabel();
                          }
                          iAnz=0;
			}
			if (VecParent !=null)
			{
                        iAnz++;
                        VecVisible=new Vector<Comparable>();
                        VecInvisible=new Vector<Comparable>();
                        VecVisible.addElement(Qry.getS("Bezeichnung"));
                        VecVisible.addElement(Qry.getInt("b2"));
                        VecVisible.addElement(Qry.getS("Kennung"));
                        VecVisible.addElement(g.TabCodes.getBezeichnungS(Qry.getI("prog"))); 			// Prog
                        VecVisible.addElement(g.TabStammtypen.getBezeichnungS(Qry.getI("aic_stammtyp")));	// Stt
                        VecVisible.addElement(Static.JaNein2(Qry.getB("web")));//Qry.getI("aic_begriffgruppe")==iBG_Frame ? Static.JaNein((Qry.getL("bits")&Formular.cstJavaFX)>0):""); // JavaFX
                        VecVisible.addElement(""); // Anzahl
                        VecVisible.addElement(Static.JaNein2(Qry.getB("Tod")));
                        iPosBG=g.TabBegriffgruppen.getPos("Aic",Qry.getI("aic_begriffgruppe"));
                        VecVisible.addElement(iPosBG<0 ? "???":g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung"));
				VecInvisible.addElement(Qry.getInt("b2"));
				JCOutlinerNode Node=new JCOutlinerNode(VecVisible,NodeParent);
				Node.setUserData(VecInvisible);
	                        
				Gif = iPosBG>=0 ? g.LoadImageIcon(g.TabBegriffgruppen.getS(iPosBG,"FILENAME")):null;
				NodeStyle=g.getStyle(Gif!=null?Gif.getImage():null);
				Node.setStyle(NodeStyle);
				TabAenderung.addInhalt("AIC_Modul",new Integer(iAIC_Mod));
				TabAenderung.addInhalt("AIC_Fremd",VecInvisible);
				TabAenderung.addInhalt("Node",Node);
				TabAenderung.addInhalt("Art","Akt");
			}
		}
                if (VecParent !=null)
                  VecParent.setElementAt(iAnz,6);
	}

        if(Qry.open("select * from modul order by aic_begriff,aic_tabellenname")) // Unter Module, aber nicht Begriffe
        {
                JCOutlinerFolderNode NodeParent=null;
                int iAIC_Mod=0;
                for(;!Qry.eof();Qry.moveNext())
                {
                        if(iAIC_Mod!=Qry.getI("aic_begriff"))
                        {
                                iAIC_Mod=Qry.getI("aic_begriff");
                                Tab.posInhalt("aic_begriff",iAIC_Mod);
                                NodeParent=(JCOutlinerFolderNode)Tab.getInhalt("Node");
                        }
                        VecVisible=new Vector<Comparable>();
                        VecInvisible=new Vector<Comparable>();
                        int iPosT=g.TabTabellenname.getPos("Aic",Qry.getI("AIC_Tabellenname"));
                        String sTab=g.TabTabellenname.getS(iPosT,"Kennung");
                        Tabellenspeicher Tabx=sTab.equals("EIGENSCHAFT") ? g.TabEigenschaften:sTab.equals("STAMMTYP") ? g.TabStammtypen:
                            sTab.equals("BEWEGUNGSTYP") ? g.TabErfassungstypen:sTab.equals("SPRACHE") ? TabSprache:sTab.equals("HAUPTSCHIRM") ? TabHS:null;
                        int iAic=Qry.getInt("aic_fremd");
                        int iPos=Tabx.getPos("AIC",iAic);
                        VecVisible.addElement(iPos<0 ? "* TOD *":Tabx.getS(iPos,"Bezeichnung"));
                        VecVisible.addElement(iAic);
                        VecVisible.addElement(iPos>=0 ? Tabx.getS(iPos,"Kennung"):sTab.equals("BEWEGUNGSTYP") ?
                                              SQL.getString(g,"select kennung from bewegungstyp where aic_bewegungstyp="+iAic):sTab+iAic);
                        VecVisible.addElement("");//Prog
                        VecVisible.addElement("");//Stammtyp
                        VecVisible.addElement("");//Web
                        VecVisible.addElement("");//Anzahl
                        VecVisible.addElement("");//Tod
                        VecVisible.addElement(g.TabTabellenname.getS(iPosT,"Bezeichnung"));
                        VecInvisible.addElement(iAic);
                        VecInvisible.addElement(Qry.getInt("aic_tabellenname"));
                        JCOutlinerNode Node=new JCOutlinerNode(VecVisible,NodeParent);
                        Node.setUserData(VecInvisible);
                        NodeStyle=g.getStyle(g.getGif(g.TabTabellenname,Qry.getI("AIC_Tabellenname")));//(Image)g.TabTabellenname.getInhalt("Bild"));
                        Node.setStyle(NodeStyle);
                        if (NodeParent!= null)
                        {
//                        	g.fixtestError("NP="+NodeParent.getLabel());
                        	Sort.add((Vector)NodeParent.getLabel(),6);
                        }

                        TabAenderung.addInhalt("AIC_Modul",new Integer(iAIC_Mod));
                        TabAenderung.addInhalt("AIC_Fremd",VecInvisible);
                        TabAenderung.addInhalt("Node",Node);
                        TabAenderung.addInhalt("Art","Akt");
                }
        }

	OutBegriff.folderChanged(OutBegriff.getRootNode());
	OutModul.folderChanged(OutModul.getRootNode());

	Qry.free();
}

@SuppressWarnings("unchecked")
private void Add()
{
	JCOutlinerNode[] NodeBegriff=OutBegriff.getSelectedNodes();
	JCOutlinerNode[] NodeModul=OutModul.getSelectedNodes();
        boolean bSub=OutModul.getSelectedNode().getLevel()>1;
        JCOutlinerFolderNode NodeM2=bSub ? OutModul.getSelectedNode().getParent():null;
        int iAnz=bSub ? 1:NodeModul.length;
	for(int i=0;i<iAnz;i++)
	{
		for(int j=0;j<NodeBegriff.length;j++)
		{
                  JCOutlinerFolderNode NodM=NodeM2==null ? (JCOutlinerFolderNode)NodeModul[i]:NodeM2;
			boolean bOut=!TabAenderung.posInhalt(((Vector)NodM.getUserData()).elementAt(0),NodeBegriff[j].getUserData());
			if(bOut||TabAenderung.getS("Art").equals("Del"))
			{
				Vector VecVisible=new Vector((Vector)NodeBegriff[j].getLabel());
				if (VecVisible.size()>6)
				{
					VecVisible.remove(6);
					VecVisible.remove(5);
				}
				else
				{
					VecVisible.addElement("");
					VecVisible.addElement("");
					VecVisible.addElement("");
				}
				VecVisible.addElement("xx");
				Vector VecInvisible=new Vector((Vector)NodeBegriff[j].getUserData());
				JCOutlinerNode Node=new JCOutlinerNode(VecVisible,NodM);
				Node.setUserData(VecInvisible);
				Node.setStyle(NodeBegriff[j].getStyle());

				if(bOut)
				{
					TabAenderung.addInhalt("AIC_Modul",((Vector)(bSub ? NodeModul[i].getParent():NodeModul[i]).getUserData()).elementAt(0));
					TabAenderung.addInhalt("AIC_Fremd",NodeBegriff[j].getUserData());
					TabAenderung.addInhalt("Node",Node);
					TabAenderung.addInhalt("Art","New");
				}
				else if(TabAenderung.getS("Art").equals("Del"))
					TabAenderung.setInhalt("Art","Akt");
			}
		}
	}
        //TabAenderung.showGrid("TabAenderung");
	OutModul.folderChanged(OutModul.getRootNode());
}

private void Remove()
{
	JCOutlinerNode[] Node=OutModul.getSelectedNodes();
        if (Node[0].getLevel()==2)
        {
          for (int i = 0; i < Node.length; i++)
          {
            if (i == 0)
              OutModul.selectNode(OutModul.getPreviousNode(Node[i]), null);
            if (TabAenderung.posInhalt("Node", Node[i]))
            {
              if (TabAenderung.getS("Art").equals("Akt"))
                TabAenderung.setInhalt("Art", "Del");
              else
                TabAenderung.clearInhalt();
            }
            Node[i].getParent().removeChild(Node[i]);
          }
          OutModul.folderChanged(OutModul.getRootNode());
        }
        else if (new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Del2") == Message.YES_OPTION)
        {
          Node=OutBegriff.getSelectedNodes();
          Vector VecMom=(Vector)OutBegriff.getSelectedNode().getUserData();
          int iTab=VecMom.size()==1 ?g.iTabBegriff:Sort.geti(VecMom,1);
          Vector<Integer> Vec=new Vector<Integer>();
          for (int i = 0; i < Node.length; i++)
            Vec.addElement(Sort.geti(Node[i].getUserData(),0));
          g.progInfo("löschen Vec="+Vec+" bei "+iTab);
          if (iTab==g.iTabBegriff)
            g.exec("delete from begriff_zuordnung where aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+iAIC_Modul+
                   ") and beg_aic_begriff"+Static.SQL_in(Vec));
          else
            g.exec("delete from modul where aic_tabellenname="+iTab+" and aic_fremd"+Static.SQL_in(Vec));
          Laden2(true);
        }
}

private void Save()
{
	SQL Qry=new SQL(g);
	for(TabAenderung.moveFirst();!TabAenderung.eof();TabAenderung.moveNext())
	{
		String sArt=TabAenderung.getS("Art");
                Vector Vec=(Vector)TabAenderung.getInhalt("AIC_Fremd");
		if(!sArt.equals("Akt"))
		{
			Qry.clear();
                        boolean bDel=sArt.equals("Del");
                        if (Vec.size()==1)
                        {
                          Qry.add("AIC_Begriff", TabAenderung.getI("AIC_Modul"));
                          Qry.add("Beg_AIC_Begriff", Vec.elementAt(0));

                          if (bDel)
                            Qry.delete("Begriff_Zuordnung");
                          else
                            Qry.insert("Begriff_Zuordnung", false);
                          int iPos=g.TabBegriffe.getPos("Aic",Sort.geti(Vec,0));
                          g.SaveDefVerlauf(TabAenderung.getI("AIC_Modul"),(bDel ? "weg ":"dazu ")+(iPos<0 ? "???":
                              g.TabBegriffgruppen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Gruppe"))+" "+g.TabBegriffe.getS(iPos,"DefBezeichnung")));
                        }
                        else
                        {
                          Qry.add("AIC_Begriff", TabAenderung.getI("AIC_Modul"));
                          Qry.add("AIC_Tabellenname", Vec.elementAt(1));
                          Qry.add("AIC_Fremd", Vec.elementAt(0));
                          if (bDel)
                            Qry.delete("Modul");
                          else
                            Qry.insert("Modul", false);
                          g.SaveDefVerlauf(TabAenderung.getI("AIC_Modul"),(bDel ? "weg ":"dazu ")+g.TabTabellenname.getBezeichnungS(Sort.geti(Vec,1))+" "+Sort.gets(((JCOutlinerNode)TabAenderung.getInhalt("Node")).getLabel(),0));
                        }
		}
		TabAenderung.setInhalt("Art","Akt");
	}
        Qry.free();
	//Laden();
}

private void EnableButtons()
{
	JCOutlinerNode NodeModul=OutModul.getSelectedNode();
	JCOutlinerNode NodeBegriff=OutBegriff.getSelectedNode();
	boolean bCpyEdtDel=g.ApplPort() && NodeModul!=null&&NodeModul.getLevel()==1;
	BtnCopy.setEnabled(bCpyEdtDel);
	BtnEdit.setEnabled(bCpyEdtDel);
	BtnDelete.setEnabled(bCpyEdtDel);
        BtnNew.setEnabled(g.ApplPort());
	BtnAdd.setEnabled(g.ApplPort() && NodeBegriff!=null&&NodeBegriff.getLevel()==2&&NodeModul!=null&&NodeModul.getLevel()>0);
	BtnRemove.setEnabled(g.ApplPort() && (NodeModul!=null&&NodeModul.getLevel()==2 || NodeBegriff!=null&&NodeBegriff.getLevel()==2));
	BtnSave.setEnabled(g.ApplPort() && (TabAenderung.posInhalt("Art","New")||TabAenderung.posInhalt("Art","Del")));
}
// add your data members here
private Global g;

private int iAIC_Modul=0;
private Tabellenspeicher TabAenderung;
private Tabellenspeicher TabAll;

private AUOutliner OutBegriff = new AUOutliner(new JCOutlinerFolderNode(""));
private AUOutliner OutModul = new AUOutliner(new JCOutlinerFolderNode(""));

private JButton BtnNew;
private JButton BtnCopy;
private JButton BtnEdit;
private JButton BtnDelete;
private JButton BtnAdd;
private JButton BtnRemove;
private JButton BtnSave;
private JButton BtnEnd;
private JButton BtnEditOk;
private JButton BtnEditAbbruch;
private JButton BtnP=null;
private JButton BtnM=null;
//private JButton BtnShow;

private JDialog FrmEdit;
private Text TxtBezeichnung=new Text("",60);
private Text TxtKennung=new Text("",98,Text.KENNUNG);
private Text TxtBezP=new Text("",60);
private Text TxtKenP=new Text("",20,Text.KENNUNG);
private Text TxtSuche=new Text("",60);
private ComboSort CboProgramm=null;
private AUCheckBox CbxFAll;
private AUCheckBox CbxFWeb;
private AUCheckBox CbxWeb;
private AUCheckBox CbxBez;
private Datum dtEnde;
private AUTextArea TxtMemo=new AUTextArea();
private AUTextArea LblMemo=new AUTextArea();
private AUTextArea MemoBegriff=null;
private boolean bNew=false;
private boolean bCopy=false;
private boolean bModul=false;
private Tabellenspeicher TabSprache;
private Tabellenspeicher TabHS;
private ActionListener AL;
private JDialog DlgProg;
private Text EdtSuche;
}

