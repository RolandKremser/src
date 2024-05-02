package All_Unlimited.Grunddefinition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;
//import javax.swing.JTextArea;


import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
//import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
// import All_Unlimited.Allgemein.Eingabe.AUEkitCore;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Print.Druckdefinition;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutlinerEvent;
import All_Unlimited.Hauptmaske.EingabeFormular;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class Tsearch extends Formular
{
  private Global g;
  private AUOutliner Out= new AUOutliner(new JCOutlinerFolderNode(""));
  private ComboSort CboSprache1;
  private ComboSort CboSprache2;
  private Text EdtKennung = null;
  private Text EdtDefBezeichnung = null;
  private Text EdtSprache1 = new Text("",255);
  private Text EdtSprache2 = new Text("",255);
  private Text EdtTitel1 = new Text("",40);
  private Text EdtTitel2 = new Text("",40);
  private Text EdtHeader1 = new Text("",255);
  private Text EdtHeader2 = new Text("",255);
  private JRadioButton Rad_Aic=null;
  private JRadioButton Rad_Bez=null;
  private JRadioButton Rad_TT=null;
  private JRadioButton Rad_Memo=null;
  private JRadioButton Rad_Bild=null;
  private AUCheckBox CbxWeb=null;
  private AUTextArea EdtMemo1;
  private AUTextArea EdtMemo2;
  private AUTextArea EdtTT1;
  private AUTextArea EdtTT2;
  private BildEingabe BtnBildSw=null;
  private BildEingabe BtnBildFX=null;
  private BildEingabe BtnBildSel=null;
  private JButton BtnEdit;
  private JButton BtnEdit2;
  private JButton BtnSuche;
  //private JButton BtnSave;
  //private JButton BtnShow;
  //private JButton BtnTake;
  //private JButton BtnBeenden;
  private static Tsearch Self=null;
  private boolean bMemo=false;
  private boolean bTT=false;
  private Vector<Integer> VecB=null;

  public static Tsearch get(Global g,String s)
  {
    if (Self==null)
      new Tsearch(g);
    else
      Self.show2();
    if (s != null)
    	Self.EdtSprache1.setText(s);
    return Self;
  }

  public static void free()
  {
    if (Self != null)
    {
    	Self.g.winInfo("Tsearch.free");
    	Self.thisFrame.dispose();     
    	Self=null;
    }
  }

  private Tsearch(Global rg)
  {
    super("translate-search", null, rg);
    Self=this;
    g=rg;
    g.winInfo("Tsearch.create");
    Build();
    show2();
  }

    private void Build()
    {
      Out.setRootVisible(false);
      if (g.Def())
        Out.setColumnButtons(new String[] {"Tabelle","Gruppe","Kennung","DefBezeichnung","Bezeichnung","FremdSprache","Bild","BildFX","BildSel","Info","Bewegungstyp","Stammtyp","Tod","Jeder","Combo","Web","change","Aic"});
      else
        Out.setColumnButtons(new String[] {"Tabelle","Gruppe","Kennung","DefBezeichnung","Bezeichnung","FremdSprache","Bild"});
      Out.setNumColumns(g.Def() ? 18:7);
      Out.setColumnLabelSortMethod(Sort.sortMethod);
      Out.setAllowMultipleSelections(true);
      getFrei("Outliner").add(Out);
      JPanel Pnl_TxtDefBezeichnung = getFrei("TxtBezeichnung");
      if (Pnl_TxtDefBezeichnung != null)
      {
        EdtDefBezeichnung = new Text("",255);
        Pnl_TxtDefBezeichnung.add(EdtDefBezeichnung);
      }
      JPanel Pnl_TxtKennung = getFrei("TxtKennung");
      if (Pnl_TxtKennung != null)
      {
        EdtKennung = new Text("",255);
        EdtKennung.setEditable(false);
        Pnl_TxtKennung.add(EdtKennung);
      }
      JPanel Pnl_TxtStdSprache = getFrei("TxtStdSprache");
      JPanel Pnl_TxtFremdSprache = getFrei("TxtFremdSprache");
      JPanel Pnl_CboStdSprache = getFrei("Combo Sprache");
      JPanel Pnl_CboFremdSprache = getFrei("Combo Sprache2");
      CboSprache1 = new ComboSort(g);
      CboSprache2 = new ComboSort(g);
      CboSprache1.fillDefTable("SPRACHE",false);
      CboSprache2.fillDefTable("SPRACHE",false);
      CboSprache1.setSelectedAIC(g.getSprache());
      CboSprache2.setSelectedAIC(g.getSprache());
      CboSprache1.setPreferredSize(new Dimension(80,16));
      CboSprache2.setPreferredSize(new Dimension(80,16));
      if (Pnl_CboStdSprache==null)
      {
	      Pnl_TxtStdSprache.setLayout(new BorderLayout());
	      Pnl_TxtStdSprache.add("West",CboSprache1);
	      Pnl_TxtStdSprache.add("Center",EdtSprache1);
      }
      else
      {
    	  Pnl_CboStdSprache.add(CboSprache1);
    	  Pnl_TxtStdSprache.add(EdtSprache1);
      }
      if (Pnl_CboFremdSprache==null)
      {
        Pnl_TxtFremdSprache.setLayout(new BorderLayout());
        Pnl_TxtFremdSprache.add("West",CboSprache2);
        Pnl_TxtFremdSprache.add("Center",EdtSprache2);
      }
      else
      {
    	  Pnl_CboFremdSprache.add(CboSprache2);
    	  Pnl_TxtFremdSprache.add(EdtSprache2);
      }
      JPanel PnlMemo=getFrei("Memo");
      bMemo=PnlMemo !=null;
      JPanel PnlTitel=getFrei("Titel");
      boolean bTitel=PnlTitel != null;
      JPanel PnlHeader=getFrei("Header");
      boolean bHeader=PnlHeader != null;
      if (bTitel)
      {
        PnlTitel.add(EdtTitel1);
        EdtTitel1.setEditable(false);
        getFrei("Titel2").add(EdtTitel2);
      }
      if (bHeader)
      {
        PnlHeader.add(EdtHeader1);
        EdtHeader1.setEditable(false);
        getFrei("Header2").add(EdtHeader2);
      }
      if (bMemo)
      {
        EdtMemo1 = new AUTextArea(g,0);
        EdtMemo1.setEditable(false);
        EdtMemo2 = new AUTextArea(g,7);
        PnlMemo.add(EdtMemo1);
        getFrei("Memo2").add(EdtMemo2);
      }
      JPanel PnlTooltip=getFrei("Tooltip");
      bTT=PnlTooltip != null;
      if (bTT)
      {
        EdtTT1 = new AUTextArea(g,0);
        EdtTT1.setEditable(false);
        EdtTT2 = new AUTextArea(g,7);//new HtmlEingabe(g,thisJFrame(), 0);
        PnlTooltip.add(EdtTT1);
        getFrei("Tooltip2").add(EdtTT2);
      }
      JPanel Pnl_Bild = getFrei("Bild");
      if (Pnl_Bild != null)
      {
      	BtnBildSw=new BildEingabe(thisFrame,g);
      	BtnBildSw.activateEvent();
      	Pnl_Bild.add(BtnBildSw);
      }
      Pnl_Bild = getFrei("BildFX");
      if (Pnl_Bild != null)
      {
      	BtnBildFX=new BildEingabe(thisFrame,g);
      	BtnBildFX.activateEvent();
      	Pnl_Bild.add(BtnBildFX);
      }
      Pnl_Bild = getFrei("BildSel");
      if (Pnl_Bild != null)
      {
      	BtnBildSel=new BildEingabe(thisFrame,g);
      	BtnBildSel.activateEvent();
      	Pnl_Bild.add(BtnBildSel);
      }
      BtnSuche = getButton("Suche");
      if(BtnSuche==null) BtnSuche = new JButton();
      ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnSuche);
      BtnEdit = getButton("Edit");
      if(BtnEdit==null) BtnEdit = new JButton();
      BtnEdit2 = getButton("Edit2");
      if(BtnEdit2==null) BtnEdit2 = new JButton();
      BtnEdit.setEnabled(g.Def());
      BtnEdit2.setEnabled(g.Def());
      //BtnSave = getButton("Speichern");
      //if(BtnSave==null) BtnSave = new JButton();
      //BtnShow = getButton("show");
      //if(BtnShow==null) BtnShow = new JButton();
      //BtnTake = getButton("Uebernehmen");
      //if(BtnTake==null) BtnTake = new JButton();
      //BtnBeenden = getButton("Beenden");
      //if(BtnBeenden==null) BtnBeenden = new JButton();
      CbxWeb=getCheckbox("nur_Web");
      Rad_Aic = getRadiobutton("Aic");
      Rad_Bez = getRadiobutton("Bezeichnung");
      Rad_TT = getRadiobutton("Tooltip");
      Rad_Memo = getRadiobutton("Memo");
      Rad_Bild = getRadiobutton("Bild");
      if (Rad_Bez != null)
        Rad_Bez.setSelected(true);
      ActionListener ALR=new ActionListener()
      {
    	  public void actionPerformed(ActionEvent e)
    	  {
    		  JCOutlinerNode Nod=Out.getSelectedNode();
    		  if (Nod!=null && Nod.getLevel()>0)
    		  {
    			  JRadioButton Rad=(JRadioButton)e.getSource();
    			  g.fixtestError("Rad="+Rad.getText());
    			  int iBez=Sort.gets(Nod.getLabel(),4).equals("") ? 3:4;
    			  EdtSprache1.setText(Sort.gets(Nod.getLabel(),Rad==Rad_Aic ? g.Def() ? 17:iBez:Rad==Rad_Bild ? 6:iBez));
    		  }
    	  }
      };
      Rad_Aic.addActionListener(ALR);
      Rad_Bez.addActionListener(ALR);
      Rad_TT.addActionListener(ALR);
      Rad_Memo.addActionListener(ALR);
      Rad_Bild.addActionListener(ALR);

      ItemListener IL=new ItemListener()
      {
        public void itemStateChanged(ItemEvent ev)
        {
          if (ev.getStateChange() == ItemEvent.SELECTED)
          {
            EdtSprache2.setText("");
            Suche(null,0);
          }
        }
      };
      CboSprache1.addItemListener(IL);
      CboSprache2.addItemListener(IL);

      ActionListener AL=new ActionListener()
      {
          @SuppressWarnings("unchecked")
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
            if (s.equals("NV")) // nicht verwendet
              Suche(ev,1);
            else if (s.equals("oK")) // ohne Kennung
              Suche(ev,2);
            else if (s.equals("NT")) // nur Tote
              Suche(ev,3);
            else if (s.equals("BK")) // Bedingung auf Kennung
              Suche(ev,4);
            else if (s.equals("aktuell")) // Begriffe im Zeitraum erzeugt oder geändert
              Suche(ev,5);
            else if (s.equals("HA")) // Horizontale Auflösung
              Suche(ev,6);
            else if (s.equals("kD")) // kein Druck
              Suche(ev,7);
            else if (s.equals("kF")) // kein Formular
              Suche(ev,8);
            else if (s.equals("kM")) // kein Modell
              Suche(ev,9);
            else if (s.equals("NW")) // nur Web-Begriffe
              Suche(ev,10);
            else if (s.equals("Memo_Abfragen")) // nur Memo-Abfragen
              Suche(ev,11);
            else if (s.equals("alle_Bilder"))
              alleBilder();
            else if (s.equals("Suche"))
              Suche(null,0);
            else if (s.equals("Meldung"))
            	Meldung(Out);
            else if (s.equals("Edit"))
              Edit(1,Out);
            else if (s.equals("Edit2"))
              Edit(2,Out);
            else if (s.equals("Abfrage2"))
              EditUser();
            else if (s.equals("Save"))
            {
              Save();
              Suche(null,0);
            }
            else if (s.equals("Info"))
            {
              int iBeg=Sort.geti(Out.getSelectedNode().getUserData(), 1);
              int iAbf=SQL.getInteger(g,"select aic_abfrage from abfrage where aic_begriff="+iBeg);
              //g.fixInfo("Begriff="+iBeg+", Abfrage="+iAbf);
              DefAbfrage.InfoAbfrage(g, iAbf).showGrid("Begriff "+iBeg,thisJFrame());
            }
            else if (s.equals("Tod"))
            {
              Object[] Nod=Out.getSelectedNodes();
              for (int i = 0; i < Nod.length; i++)
              {
                JCOutlinerNode NodMom = (JCOutlinerNode)Nod[i];
                String sTab=Sort.gets(NodMom.getLabel(),0);
                int iBeg=Sort.geti(NodMom.getUserData(), 1);
                int iTod=SQL.getInteger(g,"select tod from "+sTab+" where aic_"+sTab+"="+iBeg);
                if (g.exec("update "+sTab+" set tod="+(iTod==1 ?"null":"1")+" where aic_"+sTab+"="+iBeg)>0)
                {
                  if (sTab.toUpperCase().equals("BEGRIFF"))
                	g.SaveDefVerlauf(iBeg, "Tod "+(iTod==0 ? "gesetzt":"gelöscht"));
                  ((Vector)NodMom.getLabel()).setElementAt(Static.JaNein2(iTod==0),12);
                  g.ErmittleJeder();
                }
              }
              Out.folderChanged(Out.getRootNode());
            }
            else if (s.equals("Jeder"))
            {
              Object[] Nod=Out.getSelectedNodes();
              for (int i = 0; i < Nod.length; i++)
              {
                JCOutlinerNode NodMom = (JCOutlinerNode)Nod[i];
                String sTab=Sort.gets(NodMom.getLabel(),0);
                if (sTab.toUpperCase().equals("BEGRIFF"))
                {
                  int iBeg=Sort.geti(NodMom.getUserData(), 1);
                  int iJeder=SQL.getInteger(g,"select jeder from begriff where aic_begriff="+iBeg);
                  if (g.exec("update begriff set jeder="+(iJeder==1 ?"null":"1")+" where aic_begriff="+iBeg)>0)
                  {
                    g.SaveDefVerlauf(iBeg, "Jeder "+(iJeder==0 ? "gesetzt":"gelöscht"));
                    ((Vector)NodMom.getLabel()).setElementAt(Static.JaNein2(iJeder==0),13);
                    g.ErmittleJeder();
                  }
                }
              }
              Out.folderChanged(Out.getRootNode());
            }
            else if (s.equals("Combo"))
            {
              Object[] Nod=Out.getSelectedNodes();
              for (int i = 0; i < Nod.length; i++)
              {
                JCOutlinerNode NodMom = (JCOutlinerNode)Nod[i];
                String sTab=Sort.gets(NodMom.getLabel(),0);
                if (sTab.toUpperCase().equals("BEGRIFF"))
                {
                  int iBeg=Sort.geti(NodMom.getUserData(), 1);
                  int iCombo=SQL.getInteger(g,"select Combo from begriff where aic_begriff="+iBeg);
                  if (g.exec("update begriff set Combo="+(iCombo==1 ?"null":"1")+" where aic_begriff="+iBeg)>0)
                  {
                    g.SaveDefVerlauf(iBeg, "Combo "+(iCombo==0 ? "gesetzt":"gelöscht"));
                    ((Vector)NodMom.getLabel()).setElementAt(Static.JaNein2(iCombo==0),14);
                  }
                }
              }
              Out.folderChanged(Out.getRootNode());
            }
            else if (s.equals("Web"))
            {
              Object[] Nod=Out.getSelectedNodes();
              for (int i = 0; i < Nod.length; i++)
              {
                JCOutlinerNode NodMom = (JCOutlinerNode)Nod[i];
                String sTab=Sort.gets(NodMom.getLabel(),0);
                if (sTab.toUpperCase().equals("BEGRIFF"))
                {
                  int iBeg=Sort.geti(NodMom.getUserData(), 1);
                  int iCombo=SQL.getInteger(g,"select Web from begriff where aic_begriff="+iBeg);
                  if (g.exec("update begriff set Web="+(iCombo==1 ?"null":"1")+" where aic_begriff="+iBeg)>0)
                  {
                    g.SaveDefVerlauf(iBeg, "Web "+(iCombo==0 ? "gesetzt":"gelöscht"));
                    ((Vector)NodMom.getLabel()).setElementAt(Static.JaNein2(iCombo==0),15);
                  }
                }
              }
              Out.folderChanged(Out.getRootNode());
            }
            else if (s.equals("Show"))
              ShowInfo();
            else if (s.equals("show Frame"))
              Darstellung.showUsed(g,Sort.geti(Out.getSelectedNode().getUserData(),1),thisJFrame());
            else if (s.equals("Verlauf"))
              ShowVerlauf();
            else if (s.equals("DefVerlauf"))
              ShowDefVerlauf();
            //else if (s.equals("Take"))
            //  Take();
            else if (s.equals("Beenden"))
              thisFrame.setVisible(false);
            else if (s.equals("Suche2"))
            	SucheInOutliner();
            else if (s.equals("Individuell"))
            	Individuell.get(g, EdtDefBezeichnung.getText());
          }
      };
      g.BtnAdd(getButton("S"),"Suche2",AL);
      if (g.Def())
      {
        g.BtnAdd(getButton("not_used"), "NV", AL);
        g.BtnAdd(getButton("kein_Druck"), "kD", AL);
        g.BtnAdd(getButton("Fom_not_used"), "kF", AL);
        g.BtnAdd(getButton("Mod_not_used"), "kM", AL);
        g.BtnAdd(getButton("ohne_Kennung"), "oK", AL);
        g.BtnAdd(getButton("nur_tote"), "NT", AL);
        g.BtnAdd(getButton("nur_Web"), "NW", AL);
        g.BtnAdd(getButton("Bedingung_Kennung"), "BK", AL);
        g.BtnAdd(getButton("aktuell"), "aktuell", AL);
        g.BtnAdd(getButton("horiz._Aufl."), "HA", AL);
        g.BtnAdd(getButton("alle_Bilder"),"alle_Bilder",AL);
        g.BtnAdd(getButton("Info"), "Info", AL);
        g.BtnAdd(getButton("Tod"), "Tod", AL);
        g.BtnAdd(getButton("Jeder"), "Jeder", AL);
        g.BtnAdd(getButton("Combo"), "Combo", AL);
        g.BtnAdd(getButton("Web2"), "Web", AL);
        g.BtnAdd(getButton("Memo_Abfragen"), "Memo_Abfragen", AL);
        g.BtnAdd(getButton("DefVerlauf"), "DefVerlauf", AL);
        g.BtnAdd(getFormularButton("Individuell"),"Individuell",AL);
      }
      g.BtnAdd(BtnSuche,"Suche",AL);
      g.BtnAdd(getButton("Meldung"),"Meldung",AL);
      g.BtnAdd(BtnEdit,"Edit",AL);
      g.BtnAdd(BtnEdit2,"Edit2",AL);
      g.BtnAdd(getButton("Abfrage easy"),"Abfrage2",AL);
      g.BtnAdd(getButton("Speichern"),"Save",AL);
      g.BtnAdd(getButton("show"),"Show",AL);
      g.BtnAdd(getButton("showAll"),"Show",AL);
      g.BtnAdd(getButton("show Frame"),"show Frame",AL);
      g.BtnAdd(getButton("Verlauf"),"Verlauf",AL);
      //g.BtnAdd(getButton("Uebernehmen"),"Take",AL);
      g.BtnAdd(getButton("Beenden"),"Beenden",AL);

      Out.addItemListener(new JCOutlinerListener()
      {
        public void itemStateChanged(JCItemEvent e) {}
        public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {}
        public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {}
        public void outlinerNodeSelectBegin(JCOutlinerEvent e) {}

        public void outlinerNodeSelectEnd(JCOutlinerEvent e)
        {
          JCOutlinerNode Nod=Out.getSelectedNode();
          if (Nod != null && Nod.getLabel() instanceof Vector)
          {
            Vector Vec = (Vector)Nod.getLabel();
            //EdtSprache1.setText("" + Static.beiLeer(""+Vec.elementAt(4),""+Vec.elementAt(3)));
            if (EdtDefBezeichnung !=null)
              EdtDefBezeichnung.setText(Sort.gets(Vec,3));
            if (EdtKennung !=null)
            	EdtKennung.setText(Sort.gets(Vec,2));
            EdtSprache2.setText(""+Vec.elementAt(5));
            Vector Vec2 = (Vector)Nod.getUserData();
            //g.progInfo("Vec2="+Vec2);
            int iTab=Sort.geti(Vec2,0);
            int iAic=Sort.geti(Vec2,1);          
            if (bMemo)
            {
              Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_sprache,memo,titel,header from daten_memo where aic_tabellenname="+iTab+" and aic_fremd="+iAic,true);
              if (Tab.posInhalt("aic_sprache",CboSprache1.getSelectedAIC()))
              {
                EdtTitel1.setText(Tab.getS("Titel"));
                EdtHeader1.setText(Tab.getS("Header"));
                EdtMemo1.setText(Tab.getM("memo"));
              }
              else
              {
                EdtTitel1.setText("");
                EdtHeader1.setText("");
                EdtMemo1.setText("");
              }
              if (Tab.posInhalt("aic_sprache",CboSprache2.getSelectedAIC()))
              {
                EdtTitel2.setText(Tab.getS("Titel"));
                EdtHeader2.setText(Tab.getS("Header"));
                EdtMemo2.setText(Tab.getM("memo"));
              }
              else
              {
                EdtTitel2.setText("");
                EdtHeader2.setText("");
                EdtMemo2.setText("");
              }
            }
            if (bTT)
            {
              EdtTT1.setText(Sort.gets(Vec2, 3));
              EdtTT2.setText(Sort.gets(Vec2, 4));
            }
            boolean bBegriff=iTab==g.iTabBegriff;
            if (bBegriff)
            {
            	int iPos=g.TabBegriffe.getPos("Aic", iAic);
	            if (BtnBildSw != null)
	            	BtnBildSw.setValue(iPos,g.iSpSw);
	            if (BtnBildFX != null)
	            	BtnBildFX.setValue(iPos,g.iSpFX);
	            if (BtnBildSel != null)
	            	BtnBildSel.setValue(iPos,g.iZSel);
            }
            else
            {
            	setBild(BtnBildSw,Vec,6);
            	setBild(BtnBildFX,Vec,7);
            	setBild(BtnBildSel,Vec,8);
//            	if (BtnBildSw != null)
//            	{
//            		String s=Sort.gets(Vec,6);
//            		if (Static.Leer(s))
//            			BtnBildSw.Delete();
//            		else
//            			BtnBildSw.setValue(g.LoadImage(s),s,null);
//            	}
//	            if (BtnBildFX != null)
//	            	BtnBildFX.Delete();
//	            if (BtnBildSel != null)
//	            	BtnBildSel.Delete();
            }
            EdtDefBezeichnung.setEnabled(bBegriff);
          }
        }
      });
      sFormularBezeichnung=getTitle();
    }
    
    private void setBild(BildEingabe Btn,Vector Vec,int i)
    {
    	if (Btn != null)
    	{
    		String s=Sort.gets(Vec,i);
    		if (Static.Leer(s))
    			Btn.Delete();
    		else
    			Btn.setValue(g.LoadImage(s),s,null);
    	}
    }
    
    private void SucheInOutliner()
    {
    	String s=EdtSprache1.getText();
    	//g.fixtestError("Suche:"+s);
    	JCOutlinerNode NodSel=Out.getSelectedNode();
    	JCOutlinerNode Nod=null;//Out.getNextNode(NodSel);
    	while (Nod==null || Nod!=NodSel)
    	{
    		Nod=Out.getNextNode(Nod==null ? NodSel:Nod);
    		if (Nod==null)
    			Nod=Out.getNextNode(Out.getRootNode());
    		//g.fixtestError("Nod="+Nod.getLabel());
    		if ((""+Nod.getLabel()).indexOf(s)>-1)
    		{
    			Static.makeVisible(Out, Nod);
    			return;
    		}  		
    	}
    }

    private void alleBilder()
    {
      Tabellenspeicher Tab=new Tabellenspeicher(g,"select Filename,count(*) Anzahl from daten_bild group by filename",true);
      final JDialog FomGid = new JDialog((JFrame)thisFrame, "alle Bilder", false);
      AUOutliner Grid = new AUOutliner();
      FomGid.getContentPane().add("Center", Grid);
      Tab.showGrid(Grid);
      FomGid.setSize(400, 600);
      Static.centerComponent(FomGid,thisFrame);
      Grid.addActionListener(new JCActionListener() {
        public void actionPerformed(JCActionEvent ev) {
          JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
          String s=Sort.gets((Vector)Nod.getLabel(),0);
          Tabellenspeicher Tab2=new Tabellenspeicher(g,"select aic_tabellenname Tabelle,null Gruppe,null Bezeichnung,null Kennung,aic_fremd Aic from daten_bild where filename="+Static.StringForSQL(s),true);
          for (int i=0;i<Tab2.size();i++)
          {
            int iTab=Tab2.getI(i,"Tabelle");
            int iFremd=Tab2.getI(i,"Aic");
            if (iTab==g.iTabBegriff)
            {
              int iPos=g.TabBegriffe.getPos("Aic",iFremd);
              if (iPos>=0)
              {
                Tab2.setInhalt(i,"Tabelle","BEGRIFF");
                Tab2.setInhalt(i,"Gruppe",g.TabBegriffgruppen.getKennung(g.TabBegriffe.getI(iPos,"Gruppe")));
                Tab2.setInhalt(i,"Bezeichnung",g.TabBegriffe.getS(iPos,"DefBezeichnung"));
                Tab2.setInhalt(i,"Kennung",g.TabBegriffe.getS(iPos,"Kennung"));
              }
            }
            else
            {
              String sTab=g.TabTabellenname.getKennung(iTab);
              Tabellenspeicher Tab3=new Tabellenspeicher(g,"select kennung "+g.AU_Bezeichnung(sTab)+" from "+sTab+" where aic_"+sTab+"="+iFremd,true);
              Tab2.setInhalt(i,"Tabelle",sTab);
              Tab2.setInhalt(i,"Bezeichnung",Tab3.getS("Bezeichnung"));
              Tab2.setInhalt(i,"Kennung",Tab3.getS("Kennung"));
            }
          }
          Tab2.showGrid(s,FomGid);
        }
      });
      FomGid.setVisible(true);
    }

    private String getZusatz(String sBegriff)
    {
      return ",Tod,jeder,Combo,Web,(select ein from logging where aic_logging="+(sBegriff==null ? "Begriff":sBegriff)+".aic_logging) Log,"+(sBegriff==null ? "Begriff":sBegriff)+".aic_stammtyp from begriff";
    }

    private void Suche(ActionEvent ev,int iArt)
    {
      long lClock = Static.get_ms();
      if (ev!=null)
      {
    	  String sTitel=((JButton)ev.getSource()).getText();
    	  setTitle(sFormularBezeichnung+": "+sTitel);
//    	  g.fixtestError("Titel auf "+sTitel);
      }
      else
    	  setTitle(sFormularBezeichnung);
      JCOutlinerNode NodSel=Out.getSelectedNode();
      int iTab=0;
      int iAic=0;
      if (NodSel!=null && NodSel.getLevel()>0)
      {
        Vector Vec=(Vector)NodSel.getUserData();
        iTab=Sort.geti(Vec,0);
        iAic=Sort.geti(Vec,1);
      }
      //g.fixInfo("Suche "+EdtSprache1.getText()+"("+iArt+"), pos="+iTab+"/"+iAic);
      String s=EdtSprache1.getText();
      boolean bLeer=s.equals("");
      if (bLeer)
        s = " is null";
      else
      {
        s = "upper('" + EdtSprache1.getText() + "')";
        s = s.indexOf('%') > -1 ? " like " + s : "=" + s;
      }
      int iSprache1=CboSprache1.getSelectedAIC();
      int iSprache2=CboSprache2.getSelectedAIC();
      Tabellenspeicher Tab=null;
      if (iArt==1) // nicht verwendete Abfragen
      {
        String sZusatz=getZusatz(null);
        int iBG_Abf=g.TabBegriffgruppen.getAic("Abfrage");
        // Abfragen der Module
        Vector<Integer> VecAbf=SQL.getVector("select distinct b1.aic_begriff from begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.beg_aic_begriff and b1.aic_begriffgruppe="+iBG_Abf+
                                             " join begriff b on b.aic_begriff=z.aic_begriff and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"),g);
        // Abfragen der Formulare
        Static.addVectorI(VecAbf,SQL.getVector("select distinct b2.aic_begriff from begriff b join formular f on b.aic_begriff=f.aic_begriff and (tod is null or tod=0)"+
          " join darstellung d on d.aic_formular=f.aic_formular join begriff b2 on b2.aic_begriff=d.aic_begriff and b2.aic_begriffgruppe="+iBG_Abf,g));
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from formular f join abfrage a on f.aic_abfrage=a.aic_abfrage",g));
        // Abfragen der Modelle
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from begriff b join Modell m on b.aic_begriff=m.aic_begriff and (tod is null or tod=0)"+
          " join befehl on m.aic_modell=befehl.aic_modell join spalte s on s.aic_spalte=befehl.aic_spalte join abfrage a on a.aic_abfrage=s.aic_abfrage",g));
        // Abfragen der Drucke
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from begriff b join druck_zuordnung z on b.aic_begriff=z.aic_begriff and (b.tod is null or b.tod=0)"+
          " join abschnitt a on a.aic_abschnitt=z.aic_abschnitt",g));
        // Abfragen der Planung
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from abfrage a join planung p on a.aic_abfrage in  (p.aic_abfrage,p.abf_aic_abfrage,p.abf2_aic_abfrage)",g));
        // Abfragen (Filter) der Eigenschaften
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from abfrage a join eigenschaft e on a.aic_abfrage=e.aic_abfrage",g));
        // Abfragen (Filter) der Spalten
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from abfrage a join spalte s on a.aic_abfrage=s.filter",g));
        // Abfragen des Hauptschirms
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from abfrage a join Hauptschirm x on a.aic_abfrage=x.aic_abfrage",g));
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from abfrage a join Ansicht x on a.aic_abfrage=x.aic_abfrage",g));
        // Abschluss
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from abfrage a join abschluss x on a.aic_abfrage=x.aic_abfrage",g));
        // Benutzer
        Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from abfrage a join benutzer_zuordnung x on a.aic_abfrage=x.aic_abfrage",g));

        Tab=new Tabellenspeicher(g,"select * from (select 'BEGRIFF' Tab,aic_begriff aic,aic_begriffgruppe,kennung,defbezeichnung,"+
        g.AU_BezeichnungF("Begriff","Begriff",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","Begriff",iSprache2)+" FremdSprache"+
        //(bMemo?g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache1)+" StdMemo"+g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache2)+" FremdMemo":"")+
        (bTT?g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache2)+" FremdTT":"")+
        ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+Global.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
        ",Tod,Combo,aic_stammtyp from begriff where aic_begriff not"+Static.SQL_in(VecAbf)+" and (tod is null or tod=0) and kennung is not null and aic_begriffgruppe="+iBG_Abf+") x",true);
      }
      else if (iArt==7) // Druck-Abfragen ohne Druck/Abschnitt
      {
        Tab=new Tabellenspeicher(g,"select 'BEGRIFF' Tab,aic_begriff aic,aic_begriffgruppe,kennung,defbezeichnung,"+
        g.AU_BezeichnungF("Begriff","Begriff",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","Begriff",iSprache2)+" FremdSprache"+
        //(bMemo?g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache1)+" StdMemo"+g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache2)+" FremdMemo":"")+
        (bTT?g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache2)+" FremdTT":"")+
        ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+Global.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
        ",(select count(aic_befehl) from befehl2 b2 join spalte s on b2.aic_spalte=s.aic_spalte join abfrage a on s.aic_abfrage=a.aic_abfrage where a.aic_begriff=begriff.aic_begriff) Info"+
        getZusatz(null)+" where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+" and "+g.bit("bits",Abfrage.cstAuswertung)+" and (select count(*) from abschnitt where aic_begriff=begriff.aic_begriff)=0",true);
      }
      else if (iArt>7 && iArt!=10) // nicht verwendete Formulare (8) / Modelle (9) // Memo-Abfragen (11)
      {
    	  Vector<Integer> Vec=SQL.getVector("select "+(iArt==11 ?"b.aic_begriff":"aic_begriff")+" from "+(iArt==8 ? "(select b.aic_begriff,f.aic_formular,defbezeichnung,b.kennung"+
                  ",(select count(*) from darstellung where aic_begriff=b.aic_begriff) Form"+
                  ",(select count(*) from mandant where aic_begriff=b.aic_begriff) Mandant"+
                  ",(select count(*) from benutzergruppe where aic_begriff=b.aic_begriff) bg"+
                  ",(select count(*) from begriff_zuordnung where aic_begriff=b.aic_begriff) zu"+
                  ",(select count(*) from begriff_zuordnung where beg_aic_begriff=b.aic_begriff) zu2"+
                  ",(select kennung from code where aic_code=b.aic_code) code_kennung"+
                  " from begriff b"+g.join("formular","f","b","begriff")+") x where (code_kennung is null or code_kennung <>'System') and Form+Mandant+bg+zu+zu2=0":
                	  iArt==9 ? "(select begriff.aic_begriff,aic_modell,defbezeichnung"+
                  ",(select count(*) from darstellung where aic_begriff=begriff.aic_begriff) F"+
                  ",(select count(*) from formular where aic_modell=modell.aic_modell) F2"+
                  ",(select count(*) from begriff_zuordnung where beg_aic_begriff=begriff.aic_begriff) Modul"+
                  ",(select count(*) from abfrage where aic_modell=modell.aic_modell) M"+
                  ",(select count(*) from abfrage where mod_aic_modell=modell.aic_modell) M2"+
                  ",(select count(*) from befehl where mod_aic_modell=modell.aic_modell) B"+
                  ",(select count(*) from berechtigung where aic_fremd=begriff.aic_begriff and aic_tabellenname="+Global.iTabBegriff+") BG"+
                  ",(select count(*) from bew_begriff where aic_begriff=begriff.aic_begriff) D"+
                  " from begriff"+g.join2("modell","begriff")+") x where F+F2+M+M2+B+BG+D+Modul=0":
                    iArt==11 ? "begriff b join abfrage a on b.aic_begriff=a.aic_begriff join spalte s on a.aic_abfrage=s.aic_abfrage join fixeigenschaft f on s.aic_spalte=f.aic_spalte"+
                    " join eigenschaft e on f.aic_eigenschaft=e.aic_eigenschaft and e.aic_begriff=432 where b.aic_bewegungstyp is not null and b.aic_stammtyp is null":""),g);
    	  Tab=new Tabellenspeicher(g,"select 'BEGRIFF' Tab,aic_begriff aic,aic_begriffgruppe,kennung,defbezeichnung,"+
    		        g.AU_BezeichnungF("Begriff","Begriff",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","Begriff",iSprache2)+" FremdSprache"+
    		        (bTT?g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache2)+" FremdTT":"")+
    		        ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+Global.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
    		        ",(select count(aic_befehl) from befehl2 b2 join spalte s on b2.aic_spalte=s.aic_spalte join abfrage a on s.aic_abfrage=a.aic_abfrage where a.aic_begriff=begriff.aic_begriff) Info"+
    		        getZusatz(null)+" where"+g.in("aic_begriff",Vec),true); 
      }
      else if (iArt==2) // Begriffe ohne Kennung
      {
        Tab=new Tabellenspeicher(g,"select 'BEGRIFF' Tab,aic_begriff aic,aic_begriffgruppe,kennung,defbezeichnung,"+
        g.AU_BezeichnungF("Begriff","Begriff",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","Begriff",iSprache2)+" FremdSprache"+
        //(bMemo?g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache1)+" StdMemo"+g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache2)+" FremdMemo":"")+
        (bTT?g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache2)+" FremdTT":"")+
        ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+Global.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
        getZusatz(null)+" where Kennung is null and (tod is null or tod=0)",true);
      }
      else if (iArt==3) // nur tote Begriffe
      {
        Tab=new Tabellenspeicher(g,"select 'BEGRIFF' Tab,aic_begriff aic,aic_begriffgruppe,kennung,defbezeichnung,"+
        g.AU_BezeichnungF("Begriff","Begriff",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","Begriff",iSprache2)+" FremdSprache"+
        //(bMemo?g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache1)+" StdMemo"+g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache2)+" FremdMemo":"")+
        (bTT?g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache2)+" FremdTT":"")+
        ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+g.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
        getZusatz(null)+" where Tod=1",true);
      }
      else if (iArt==10) // nur Web-Begriffe
      {
        Tab=new Tabellenspeicher(g,"select 'BEGRIFF' Tab,aic_begriff aic,aic_begriffgruppe,kennung,defbezeichnung,"+
        g.AU_BezeichnungF("Begriff","Begriff",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","Begriff",iSprache2)+" FremdSprache"+
        //(bMemo?g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache1)+" StdMemo"+g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache2)+" FremdMemo":"")+
        (bTT?g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache2)+" FremdTT":"")+
        ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+g.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
        getZusatz(null)+" where web=1",true);
      }
      else if (iArt==4) // Abfragen mit Bedingung auf Kennung
      {
        Vector<Integer> VecEig=SQL.getVector("select e.aic_eigenschaft from eigenschaft e join begriff b on e.aic_begriff=b.aic_begriff and b.kennung='Kennung'",g);
        Tab=new Tabellenspeicher(g,"select 'BEGRIFF' Tab,b.aic_begriff aic,b.aic_begriffgruppe,b.kennung,b.DefBezeichnung,vergleichswert info,"+
                                 g.AU_BezeichnungF("Begriff","b",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","b",iSprache2)+" FremdSprache"+
                                 //g.AU_Bezeichnung("Stammtyp","b")+" Stammtyp"+g.AU_Bezeichnung("Bewegungstyp","b")+" Bewegungstyp,count(*)
                                 (bTT?g.AU_Sprache("Tooltip","Memo2","Begriff","b",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","b",iSprache2)+" FremdTT":"")+
                                 ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+g.iTabBegriff+" AND AUI.AIC_Fremd=b.AIC_BEGRIFF AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
                                 /*",Tod,aic_stammtyp from begriff"*/getZusatz("b")+" b "+g.join("abfrage","b","begriff")+g.join2("bedingung","abfrage")+
                                 g.join2("fixeigenschaft","bedingung")+" where aic_eigenschaft"+Static.SQL_in(VecEig),true);
      }
      else if (iArt==5) // Begriffe laut Zeitraum geändert
      {
      Tab=new Tabellenspeicher(g,"select 'BEGRIFF' Tab,aic_begriff aic,aic_begriffgruppe,kennung,defbezeichnung,"+
      g.AU_BezeichnungF("Begriff","Begriff",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","Begriff",iSprache2)+" FremdSprache"+
      //(bMemo?g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache1)+" StdMemo"+g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache2)+" FremdMemo":"")+
      (bTT?g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache2)+" FremdTT":"")+
      ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+g.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
      getZusatz(null)+" join logging on begriff.aic_logging=logging.aic_logging where logging.ein>"+g.von()+" and logging.mom<"+g.bis()+" order by ein desc",true);
      }
      else if (iArt==6) // horizonatale Auflösung
      {
        Tab=new Tabellenspeicher(g,"select distinct 'BEGRIFF' Tab,begriff.aic_begriff aic,aic_begriffgruppe,begriff.kennung,defbezeichnung,"+
      g.AU_BezeichnungF("Begriff","Begriff",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","Begriff",iSprache2)+" FremdSprache"+
      (bTT?g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache2)+" FremdTT":"")+
      ",z.titel Bild"+
      //",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+g.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Sprache=1) Bild"+
      ",Tod,begriff.aic_stammtyp from begriff join abfrage a on begriff.aic_begriff=a.aic_begriff join spalte s on a.aic_abfrage=s.aic_abfrage"+
      " join spalte_zuordnung z on s.aic_spalte=z.aic_spalte"+(bLeer ? " where z.aic_stamm is null":" join stammview2 v on z.aic_stamm=v.aic_stamm and v.bezeichnung"+s),true);
      }
      else
      {
    	boolean bAic= Rad_Aic != null && Rad_Aic.isSelected();
    	if (bAic)
    		s=EdtSprache1.getText();
        boolean bBez= Rad_Bez == null || Rad_Bez.isSelected();
        boolean bMemoS=Rad_Memo != null && Rad_Memo.isSelected();
        boolean bBild=Rad_Bild != null && Rad_Bild.isSelected();
        boolean bTTS=  Rad_TT != null && Rad_TT.isSelected();
        boolean bWeb=CbxWeb != null && CbxWeb.isSelected();
        String sWhere=bAic ? "aic="+s:"upper("+(bLeer ? "Kennung":bBez ? "StdSprache":bTTS ?"StdTT":bMemoS ?"StdMemo":bBild ? "Bild":"Hugo")+")"+s+(bBez && g.Def() ? " or upper(Kennung)"+s:"");
        g.fixtestInfo("Where:"+sWhere);
        Tab=new Tabellenspeicher(g,"select * from (select 'BEGRIFF' Tab,aic_begriff aic,aic_begriffgruppe,kennung,defbezeichnung,"+
        g.AU_BezeichnungF("Begriff","Begriff",iSprache1)+" StdSprache,"+g.AU_BezeichnungF("Begriff","Begriff",iSprache2)+" FremdSprache"+
        (bMemoS?g.AU_Sprache("Daten_Memo","Memo","Begriff","",iSprache1)+" StdMemo":"")+
        (bTT || bTTS ?g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache1)+" StdTT"+g.AU_Sprache("Tooltip","Memo2","Begriff","",iSprache2)+" FremdTT":"")+
        ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+g.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+Global.iSpSw+") Bild,null BildFX,null BildSel"+
        getZusatz(null)+") x where ("+sWhere+(bBez ? " or upper(defbezeichnung)"+s+")":")")+(bWeb ? " and web=1":""),true);
      Tabellenspeicher Tab2=new Tabellenspeicher(g,"select * from (select 'CODE' Tab,aic_code aic,aic_begriffgruppe,kennung"+
        g.AU_Sprache("Bezeichnung","Bezeichnung","Code","",iSprache1)+" StdSprache"+g.AU_Sprache("Bezeichnung","Bezeichnung","Code","",iSprache2)+" FremdSprache"+
        (bTTS ?g.AU_Sprache("Tooltip","Memo2","Code","",iSprache1)+" StdTT":bMemoS?g.AU_Sprache("Daten_Memo","Memo","Code","",iSprache1)+" StdMemo":"")+
        ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+g.TabTabellenname.getAic("CODE")+" AND AUI.AIC_Fremd=Code.AIC_Code AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
        " from code) x where "+sWhere,true);
      if (!bWeb)
       for (Tab2.moveFirst(); !Tab2.out(); Tab2.moveNext())
       {
        Tab.newLine();
        Tab.setInhalt("Tab", Tab2.getInhalt("Tab"));
        Tab.setInhalt("aic", Tab2.getInhalt("aic"));
        Tab.setInhalt("aic_begriffgruppe", Tab2.getInhalt("aic_begriffgruppe"));
        Tab.setInhalt("kennung", Tab2.getInhalt("kennung"));
        Tab.setInhalt("StdSprache", Tab2.getInhalt("StdSprache"));
        Tab.setInhalt("FremdSprache", Tab2.getInhalt("FremdSprache"));
        Tab.setInhalt("Bild", Tab2.getS("Bild"));
        if (Tab2.exists("StdTT"))
          Tab.setInhalt("StdTT",Tab2.getInhalt("StdTT"));
        /*if (bMemo)
        {
          Tab.setInhalt("StdMemo", Tab2.getInhalt("StdMemo"));
          Tab.setInhalt("FremdMemo", Tab2.getInhalt("FremdMemo"));
        }*/
        //Tab.setInhalt("Tod", Tab2.getInhalt("Tod"));
       }
      String[] sTabellen = new String[] {"STAMMTYP","ROLLE","EIGENSCHAFT","BEWEGUNGSTYP","TABELLENNAME","SPALTE","AUSWAHL","BEGRIFFGRUPPE","STATUS","ABSCHLUSSTYP"};
      if (!bWeb)
       for(int i=0;i<sTabellen.length;i++)
       {
        String sT=sTabellen[i];
        int iTabAic=g.TabTabellenname.getAic(sT);
        Tab2 = new Tabellenspeicher(g,"select * from (select '"+sT+"' Tab,aic_"+sT+" aic,kennung" +
                                    (sT.equals("TABELLENNAME") || sT.equals("SPALTE") || sT.equals("AUSWAHL") || sT.equals("BEGRIFFGRUPPE") || sT.equals("STATUS") || sT.equals("ABSCHLUSSTYP") ?"":",Tod"+
                                    (sT.equals("ROLLE") || sT.equals("SPALTE") || sT.equals("AUSWAHL") || sT.equals("BEGRIFFGRUPPE") || sT.equals("STATUS") || sT.equals("ABSCHLUSSTYP") ? "":",(select ein from logging where aic_logging="+sT+".aic_logging) Log"))+
                                    g.AU_Sprache("Bezeichnung", "Bezeichnung", sT, "", iSprache1) +" StdSprache" +g.AU_Sprache("Bezeichnung", "Bezeichnung", sT, "", iSprache2) + " FremdSprache"+
                                    (bTT && sT.equals("EIGENSCHAFT") || bTTS ?g.AU_Sprache("Tooltip","Memo2",sT,"",iSprache1)+" StdTT":"")+(bMemoS?g.AU_Sprache("Daten_Memo","Memo",sT,"",iSprache1)+" StdMemo":"")+
                                    (bTT && sT.equals("EIGENSCHAFT") ? g.AU_Sprache("Tooltip","Memo2",sT,"",iSprache2)+" FremdTT":"")+
                                    ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+iTabAic+" AND AUI.AIC_Fremd="+sT+".AIC_"+sT+" AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
                                    ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+iTabAic+" AND AUI.AIC_Fremd="+sT+".AIC_"+sT+" AND AUI.AIC_Zustand="+Global.iSpFX+") BildFX"+
                                    ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+iTabAic+" AND AUI.AIC_Fremd="+sT+".AIC_"+sT+" AND AUI.AIC_Zustand="+Global.iZSel+") BildSel"+
                                    (sT.equals("EIGENSCHAFT") ? ",aic_stammtyp,bits":sT.equals("ABSCHLUSSTYP") ?",aic_stammtyp":"")+
                                    //(bMemo?g.AU_Sprache("Daten_Memo","Memo",sT,"",iSprache1)+" StdMemo"+g.AU_Sprache("Daten_Memo","Memo",sT,"",iSprache2)+" FremdMemo":"")+
                                    " from "+sT+") x where " + sWhere, true);
        //g.fixtestInfo(sT+":"+Tab2.getSQL());
        for (Tab2.moveFirst(); !Tab2.out(); Tab2.moveNext())
        {
          Tab.newLine();
          Tab.setInhalt("Tab", Tab2.getInhalt("Tab"));
          Tab.setInhalt("aic", Tab2.getInhalt("aic"));
          Tab.setInhalt("kennung", Tab2.getInhalt("kennung"));
          Tab.setInhalt("StdSprache", Tab2.getInhalt("StdSprache"));
          Tab.setInhalt("FremdSprache", Tab2.getInhalt("FremdSprache"));
          Tab.setInhalt("Bild", Tab2.getS("Bild"));
          Tab.setInhalt("BildFX", Tab2.getS("BildFX"));
          Tab.setInhalt("BildSel", Tab2.getS("BildSel"));
          if (Tab2.exists("StdTT"))
            Tab.setInhalt("StdTT",Tab2.getInhalt("StdTT"));
          if (Tab2.exists("FremdTT"))
            Tab.setInhalt("FremdTT",Tab2.getInhalt("FremdTT"));
          if (Tab2.exists("Tod") && Tab2.getB("Tod"))
            Tab.setInhalt("Tod",Boolean.TRUE);
          if (Tab2.exists("bits") && (Tab2.getI("bits")&Global.cstJeder)>0)
              Tab.setInhalt("Jeder",Boolean.TRUE);
          if (Tab2.exists("Log") && !Tab2.isNull("Log"))
            Tab.setInhalt("Log",Tab2.getInhalt("Log"));
          if (Tab2.exists("aic_stammtyp") && !Tab2.isNull("aic_stammtyp"))
            Tab.setInhalt("aic_stammtyp",Tab2.getInhalt("aic_stammtyp"));
          /*if (bMemo)
          {
            Tab.setInhalt("StdMemo", Tab2.getInhalt("StdMemo"));
            Tab.setInhalt("FremdMemo", Tab2.getInhalt("FremdMemo"));
          }*/
        }
      }
      if (g.Def() && !bLeer && !bWeb)
      {
        Tab2=new Tabellenspeicher(g,"select distinct * from (select 'STAMM' Tab,aic_stamm aic,kennung,bezeichnung defbezeichnung,aic_stammtyp"+
          g.AU_Sprache("Bezeichnung", "Bezeichnung", "STAMM", "stammview2", iSprache1) +" StdSprache" +g.AU_Sprache("Bezeichnung", "Bezeichnung", "STAMM", "stammview2", iSprache2) + " FremdSprache"+
          (bTTS ?g.AU_Sprache("Tooltip","Memo2","STAMM","stammview2",iSprache1)+" StdTT":bMemoS?g.AU_Sprache("Daten_Memo","Memo","STAMM","stammview2",iSprache1)+" StdMemo":"")+
          ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+g.TabTabellenname.getAic("STAMM")+" AND AUI.AIC_Fremd=stammview2.AIC_STAMM AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
          //(bMemo?g.AU_Sprache("Daten_Memo","Memo","STAMM","stammview2",iSprache1)+" StdMemo"+g.AU_Sprache("Daten_Memo","Memo","STAMM","stammview2",iSprache2)+" FremdMemo":"")+
          " from stammview2) x where "+sWhere+(bBez ? " or upper(defbezeichnung)"+s:""), true);
        for (Tab2.moveFirst(); !Tab2.out(); Tab2.moveNext())
        {
          Tab.newLine();
          Tab.setInhalt("Tab", Tab2.getInhalt("Tab"));
          Tab.setInhalt("aic", Tab2.getInhalt("aic"));
          Tab.setInhalt("kennung", Tab2.getInhalt("kennung"));
          Tab.setInhalt("defbezeichnung", Tab2.getInhalt("defbezeichnung"));
          Tab.setInhalt("StdSprache", Tab2.getInhalt("StdSprache"));
          Tab.setInhalt("FremdSprache", Tab2.getInhalt("FremdSprache"));
          Tab.setInhalt("Bild", Tab2.getS("Bild"));
          Tab.setInhalt("aic_stammtyp",Tab2.getInhalt("aic_stammtyp"));
          /*if (bMemo)
          {
            Tab.setInhalt("StdMemo", Tab2.getInhalt("StdMemo"));
            Tab.setInhalt("FremdMemo", Tab2.getInhalt("FremdMemo"));
          }*/
          //Tab.setInhalt("Tod", Tab2.getInhalt("Tod"));
        }
      }
      //Tab.showGrid("Tab");
      }
      JCOutlinerFolderNode NodP=(JCOutlinerFolderNode)Out.getRootNode();
      NodP.removeChildren();
      JCOutlinerNode NodS=null;
      JCOutlinerNodeStyle StyTod = new JCOutlinerNodeStyle();
      StyTod.setForeground(g.ColTod);
      for (Tab.moveFirst();!Tab.out();Tab.moveNext())
      {
        Vector<Comparable> VecVisible = new Vector<Comparable>();
        String sTab=Tab.getS("Tab");
        int iPosT=g.TabTabellenname.getPos("Kennung",sTab);
        VecVisible.addElement(g.TabTabellenname.getS(iPosT,"Bezeichnung"));
        String sG=sTab.equals("EIGENSCHAFT") ? g.TabEigenschaften.getS_AIC("Datentyp",Tab.getI("Aic")):Static.sLeer;
        String sK=Static.sLeer;
        if (sTab.equals("BEGRIFF") || sTab.equals("CODE"))
        {
          int iPosBG=g.TabBegriffgruppen.getPos("Aic",Tab.getI("aic_begriffgruppe"));
          sG=g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung");
          sK=g.TabBegriffgruppen.getS(iPosBG,"Kennung");
        }
        else
          sK=sTab;
        VecVisible.addElement(sG);
        VecVisible.addElement(Tab.getS("Kennung"));
        VecVisible.addElement(sTab.equals("BEGRIFF") || sTab.equals("STAMM") ? Tab.getS("defbezeichnung"):"");
        VecVisible.addElement(Tab.getS("StdSprache"));
        VecVisible.addElement(Tab.getS("FremdSprache"));
        int iPosB=sTab.equals("BEGRIFF") ? g.TabBegriffe.getPos("Aic",Tab.getInhalt("Aic")):-1;
        VecVisible.addElement(iPosB<0 ? Tab.getS("Bild"):g.TabBegriffe.getS(iPosB,"BildFile"));
        VecVisible.addElement(iPosB<0 ? Tab.getS("BildFX"):g.TabBegriffe.getS(iPosB,"BildFX"));
        VecVisible.addElement(iPosB<0 ? Tab.getS("BildSel"):g.TabBegriffe.getS(iPosB,"BildSel"));
        if (g.Def())
        {
          VecVisible.addElement(Tab.exists("Info") ? (Comparable)Tab.getInhalt("Info"):sTab.equals("BEGRIFF") && iPosB>=0 ? g.TabCodes.getKennung(g.TabBegriffe.getI(iPosB,"Typ")):null);
          if (sTab.equals("BEGRIFF"))
          {
            //g.TabBegriffe.push();
            
            VecVisible.addElement(g.TabBegriffe.getI(iPosB,"Erf")>0?g.TabErfassungstypen.getBezeichnungS(g.TabBegriffe.getI(iPosB,"Erf")):"");
            VecVisible.addElement(g.TabBegriffe.getI(iPosB,"Stt")>0?g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPosB,"Stt")):"");
            //g.TabBegriffe.pop();
          }
          else if (Tab.exists("aic_stammtyp") && !Tab.isNull("aic_stammtyp"))
          {
            VecVisible.addElement("");
            VecVisible.addElement(g.TabStammtypen.getBezeichnungS(Tab.getI("aic_stammtyp")));
          }
          else
          {
            VecVisible.addElement("");
            VecVisible.addElement("");
          }
          VecVisible.addElement(Static.JaNein2(Tab.getB("Tod")));
          VecVisible.addElement(Tab.exists("Jeder") ? Static.JaNein2(Tab.getB("Jeder")):"?");
          VecVisible.addElement(Tab.exists("Combo") ? Static.JaNein2(Tab.getB("Combo")):null);
          VecVisible.addElement(Tab.exists("Web") ? Static.JaNein2(Tab.getB("Web")):null);
          VecVisible.addElement(Tab.exists("Log") && !Tab.isNull("Log") ? new Zeit(Tab.getDate("Log"), "dd.MM.yyyy"):null);
          VecVisible.addElement(Tab.getInt("Aic"));
        }
        Vector<Object> VecInvisible=new Vector<Object>();
        VecInvisible.addElement(g.TabTabellenname.getInhalt("Aic",iPosT));
        VecInvisible.addElement(Tab.getInhalt("Aic"));
        VecInvisible.addElement(sK);
        /*if (bMemo)
        {
          VecInvisible.addElement(Tab.getM("StdMemo"));
          VecInvisible.addElement(Tab.getM("FremdMemo"));
        }*/
        if (bTT)
        {
          VecInvisible.addElement(Tab.getM("StdTT"));
          VecInvisible.addElement(Tab.getM("FremdTT"));
        }
        JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodP);
        if (Tab.getB("Tod"))
        	Nod.setStyle(StyTod);
        Nod.setUserData(VecInvisible);
        //g.fixInfo(iTab+"/"+g.TabTabellenname.getInhalt("Aic",iPosT)+" | "+iAic+"/"+Tab.getInhalt("Aic"));
        if (NodS==null || (iTab==g.TabTabellenname.getI(iPosT,"Aic")) && (iAic==Tab.getI("Aic")))
        {
          NodS = Nod;
          //g.fixInfo("-> NodS="+NodS);
        }
      }
      //Out.folderChanged(NodP);
      //g.fixInfo("NodS="+NodS);
      Static.makeVisible(Out,NodS);
      g.clockInfo("Suche " + s, lClock);
    }

    private void EditUser()
    {
      JCOutlinerNode Nod=Out.getSelectedNode();
      if (Nod==null || Nod.getLevel()==0)
        return;
      Vector Vec=(Vector)Nod.getUserData();
      //boolean bBegriff=Sort.gets(Nod.getLabel(),0).toUpperCase().equals("BEGRIFF");
      String sArt=(String)Vec.elementAt(2);
      //g.fixtestInfo("EditUser: Art="+sArt+"/"+bBegriff);
      if (/*bBegriff && */sArt.equals("Abfrage"))
      {
        int iBegriff = Sort.geti(Vec, 1);
        ShowAbfrage Abf = new ShowAbfrage(g, iBegriff, Abfrage.cstBegriff);
        DefAbfrage2.get(g, DefAbfrage2.DRUCK, Abf, (JFrame)thisFrame);
      }
    }
    
    private void Meldung(JCOutliner Out)
    {
    	JCOutlinerNode Nod=Out.getSelectedNode();
        if (Nod==null || Nod.getLevel()==0)
          return;
        Vector Vec=(Vector)Nod.getUserData();
        //g.fixtestError("Meldung: Vec="+Vec);
        boolean bBegriff=Sort.gets(Nod.getLabel(),0).toUpperCase().equals("BEGRIFF");
        String sArt=(String)Vec.elementAt(2);
        int iAic=Tabellenspeicher.geti(Vec.elementAt(1));
        String sKennung=g.TabBegriffe.getKennung(iAic);
        //g.fixtestError("Meldung: Art="+sArt+", Kennung="+sKennung);
        if (bBegriff && sArt.equals("Message"))
        	new Message(Message.INFORMATION_MESSAGE,thisJFrame(),g,20).showDialog(sKennung);
    }
    
    private void Edit(int i,JCOutliner Out)
    {
      JCOutlinerNode Nod=Out.getSelectedNode();
      if (Nod==null || Nod.getLevel()==0)
        return;
      Vector Vec=(Vector)Nod.getUserData();
      boolean bBegriff=Sort.gets(Nod.getLabel(),0).toUpperCase().equals("BEGRIFF");
      int iAic=Tabellenspeicher.geti(Vec.elementAt(i==0?0:1));
      String sArt=(String)Vec.elementAt(i==0?1:2);
      g.fixtestInfo("Edit"+i+":"+sArt+"/"+iAic+"/"+bBegriff+"/"+Sort.gets(Vec,0)+"/"+Sort.gets(Vec,1)+"/"+Sort.gets(Vec,2));
      Edit(g,i,sArt,iAic,bBegriff);
    }
      
    public static void Edit(Global g,int i,String sArt,int iAic,boolean bBegriff)
    {
      g.fixtestError("Edit "+sArt+" "+iAic);
      if (sArt.equals("Frame"))
      {
	//if (i==1)
	  DefFormular.get(g,iAic);
	//else
	//  Darstellung.start2(iAic,0,g).show2();
      }
      else if (sArt.equals("Abfrage") || sArt.equals("SPALTE"))
      {
    	if (sArt.equals("SPALTE"))
    		iAic=SQL.getInteger(g,"select a.aic_begriff from abfrage a join spalte s on a.aic_abfrage=s.aic_abfrage where s.aic_spalte="+iAic);
        int iStt=SQL.getInteger(g,"select "+g.isnull()+"-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff="+iAic);
        DefAbfrage.get(g, new ShowAbfrage(g, iAic, Abfrage.cstBegriff), iStt,null,false,i-1).show();
      }
      else if (sArt.equals("Modell"))
    	DefModell.get(g, iAic).show();
      else if (sArt.equals("Modul"))
        DefModul.get(g,iAic);
      else if (sArt.equals("Druck"))
      {
        int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iAic);
        Druckdefinition.get(g, true, iAic, iStt);
      }
      else if (sArt.equals("Web"))
        DefHtml.get(g,iAic);
      else if (sArt.equals("Aufgabe"))
    	  DefAufgabe.get(g,iAic);
      else if (sArt.equals("STAMMTYP"))
        DefStt.get(g,iAic);
      else if (sArt.equals("BEWEGUNGSTYP"))
        DefBew.get(g,iAic);
      else if (sArt.equals("EIGENSCHAFT"))
      {
        DefEigenschaft.get(g,iAic);
      }
      else if (sArt.equals("STAMM"))
      {
        int iStt = SQL.getInteger(g,"select aic_stammtyp from stamm where aic_stamm="+iAic);
        int iForm= SQL.getInteger(g,"SELECT Formular.AIC_begriff FROM Formular join begriff on formular.aic_begriff=begriff.aic_begriff"+
                         " WHERE aic_bewegungstyp is null and aic_stammtyp="+iStt+" and "+g.bit("bits",cstStdFormular));
        EingabeFormular.HoleFormular(g,iForm>0?iForm:-iStt,null,iAic,false);
      }
      else if (sArt.equals("ABSCHLUSSTYP"))
    	  new DefAbschluss(g);
      else if (sArt.equals("STATUS"))
    	  DefAufgabe.get(g,-iAic);
      else if (bBegriff)
          new Definition(g,"BEGRIFF");
      else
        g.fixInfo("Art "+sArt+" wird noch nicht unterstützt!");
    }

    private void Save()
    {
      String s=EdtSprache2.getText();
      int iSprache2=CboSprache2.getSelectedAIC();
      Object[] Nod=Out.getSelectedNodes();
      for(int i=0;i<Nod.length;i++)
      {
        JCOutlinerNode NodMom=(JCOutlinerNode)Nod[i];
        Vector Vec=(Vector)NodMom.getUserData();
        Vector VecL=(Vector)NodMom.getLabel();
        int iTab=Tabellenspeicher.geti(Vec.elementAt(0));
        int iAic=Tabellenspeicher.geti(Vec.elementAt(1));
        g.progInfo("Save "+s+"/"+iTab+"/"+iAic+"/"+iSprache2);
        boolean bBeg=iTab==g.iTabBegriff;
        int iPos=g.TabBegriffe.getPos("Aic", iAic);
        if (bBeg && EdtDefBezeichnung != null && EdtDefBezeichnung.Modified())
        {
          SQL Qry=new SQL(g);
          Qry.add("DefBezeichnung",EdtDefBezeichnung.getText());
          Qry.update("Begriff",iAic);
          Qry.free();
          g.SaveDefVerlauf(iAic,"DefBezeichnung");
        }
        if (g.setBezeichnung(""+VecL.elementAt(5),s,iTab,iAic,iSprache2) && bBeg)
        	g.SaveDefVerlauf(iAic,"Bezeichnung "+CboSprache2.getSelectedBezeichnung());
        if (bMemo && (EdtMemo2.Modified() || EdtTitel2.Modified()) || EdtHeader2.Modified())
        {
          g.setMemo(EdtMemo2.getValue(),EdtTitel2.getText(),EdtHeader2.getText(),iTab,iAic,iSprache2);
          if (bBeg) g.SaveDefVerlauf(iAic,"Memo "+CboSprache2.getSelectedBezeichnung());
        }
        if (bTT && EdtTT2.Modified())
        {
          g.setTooltip(EdtTT2.getValue(),iTab,iAic,iSprache2);
          if (bBeg) g.SaveDefVerlauf(iAic,"Tooltip "+CboSprache2.getSelectedBezeichnung());
        }
        if (BtnBildSw != null && BtnBildSw.Modified())
        {
        	String sBildSw=BtnBildSw.getFilename();
        	g.setImage(sBildSw, iTab, iAic, Global.iSpSw);
        	if (bBeg)
        		g.TabBegriffe.setInhalt(iPos, "BildFile", sBildSw);
        	g.SaveDefVerlauf(bBeg ? iAic:getBegriff(), (bBeg ?"":Sort.gets(VecL,0)+"."+Sort.gets(VecL,2)+": ")+"BildSw nun "+sBildSw);
        }
        if (BtnBildFX != null && BtnBildFX.Modified())
        {
        	String sBildFX=BtnBildFX.getFilename();
        	g.setImage(sBildFX, iTab, iAic, Global.iSpFX);
        	if (bBeg)
        		g.TabBegriffe.setInhalt(iPos, "BildFX", sBildFX);
        	g.SaveDefVerlauf(bBeg ? iAic:getBegriff(), (bBeg ?"":Sort.gets(VecL,0)+"."+Sort.gets(VecL,2)+": ")+"BildFX nun "+sBildFX);
        }
        if (BtnBildSel != null && BtnBildSel.Modified())
        {
        	String sBildSel=BtnBildSel.getFilename();
        	g.setImage(sBildSel, iTab, iAic, Global.iZSel);
        	if (bBeg)
        		g.TabBegriffe.setInhalt(iPos, "BildSel", sBildSel);
        	g.SaveDefVerlauf(bBeg ? iAic:getBegriff(), (bBeg ?"":Sort.gets(VecL,0)+"."+Sort.gets(VecL,2)+": ")+"BildSel nun "+sBildSel);
        }
      }
    }

    private void addNode(JCOutlinerFolderNode Nod,String sArt,Tabellenspeicher Tab)
    {
      for (Tab.moveFirst();!Tab.out();Tab.moveNext())
      {
        Vector<Comparable> VecVisible = new Vector<Comparable>();
        VecVisible.addElement(sArt);
        VecVisible.addElement(Tab.getS("bezeichnung"));
        VecVisible.addElement(Tab.getS("kennung"));
        VecVisible.addElement(Tab.exists("Web") ? Static.JaNein2(Tab.getB("Web")):Static.sLeer);
        VecVisible.addElement(Tab.exists("Prog") && Tab.getI("Prog")>0 ? g.TabCodes.getBezeichnungS(Tab.getI("Prog")):null);
        VecVisible.addElement(Tab.exists("aic_stammtyp") && Tab.getI("aic_stammtyp")>0 ? g.TabStammtypen.getBezeichnungS(Tab.getI("aic_stammtyp")):null);
        VecVisible.addElement(Tab.exists("Tod") ? Static.JaNein2(Tab.getB("Tod")):null);
        Integer iAic=new Integer(Tab.getI("aic"));
        VecVisible.addElement(iAic);
        JCOutlinerFolderNode NodNeu=new JCOutlinerFolderNode(VecVisible);
        Vector<Comparable> VecInvisible = new Vector<Comparable>();
        VecInvisible.addElement(iAic);
        VecInvisible.addElement(sArt);
        NodNeu.setUserData(VecInvisible);
        NodNeu.setStyle(sArt.equals("Stamm") || sArt.equals("Abschnitt") || sArt.equals("EIGENSCHAFT") ?g.getTabStype(sArt.toUpperCase()):g.getBGStype(sArt));
        Nod.addNode(NodNeu);
        Ermittle(g,NodNeu,sArt,iAic);
      }
    }

    private void Ermittle(Global g,JCOutlinerFolderNode NodNeu,String sArt,int iAic)
    {
      if (sArt.equals("Abfrage"))
        {
          if (Ermittle(NodNeu,g,iAic,"Frame",true))
          {
            Ermittle(NodNeu, g, iAic, "Modell", false);
            addNode(NodNeu,"Abschnitt",ErmittleAbschnitt(g,iAic));
            addNode(NodNeu,"Modul",ErmittleModul(g,iAic));
          }
        }
        else if (sArt.equals("Modell"))
        {
          if (Ermittle(NodNeu,g,iAic,"SubModell",true))
          {
            Ermittle(NodNeu,g,iAic,"Abfrage",false);
            Ermittle(NodNeu,g,iAic,"Frame",false);
            addNode(NodNeu,"Modul",ErmittleModul(g,iAic));
          }
        }
        else if (sArt.equals("Frame"))
        {
          if (Ermittle(NodNeu,g,iAic,"Frame",true))
            addNode(NodNeu,"Modul",ErmittleModul(g,iAic));
        }
        else if (sArt.equals("Abschnitt"))
          addNode(NodNeu,"Druck",ErmittleDruck(g,iAic));
        else //if (sArt.equals("Druck"))
          addNode(NodNeu,"Modul",ErmittleModul(g,iAic));
    }

    private Tabellenspeicher ErmittleAbfragenS(Global g,int iAic)
    {
      return new Tabellenspeicher(g,"select distinct b.aic_begriff aic,b.defbezeichnung bezeichnung,b.kennung,b.aic_stammtyp,b.Tod,b.Web,b.Prog from begriff b" +
                                   g.join("abfrage", "b", "begriff") + g.join2("bedingung", "abfrage") + " where vergleichswert='" + iAic + "'", true);
    }

    private Tabellenspeicher ErmittleFrameS(Global g,int iAic)
    {
      return new Tabellenspeicher(g,"select distinct b.aic_begriff aic,b.defbezeichnung bezeichnung,b.kennung,b.aic_stammtyp,b.Tod,b.Web,b.Prog from begriff b" +
                                   g.join("formular", "b", "begriff") + " where formular.aic_stamm=" + iAic, true);
    }

    private Tabellenspeicher ErmittleEigS(Global g,int iAic)
    {
      return new Tabellenspeicher(g,"select distinct aic_eigenschaft aic,kennung,aic_stammtyp,Tod"+g.AU_Bezeichnung("eigenschaft")+" from eigenschaft where aic_stamm=" + iAic, true);
    }

    private Tabellenspeicher ErmittleAbschnitt(Global g,int iAic)
    {
      return new Tabellenspeicher(g,"select aic_abschnitt aic,kennung"+g.AU_Bezeichnung("abschnitt")+" from abschnitt where aic_begriff="+iAic,true);
    }

    private Tabellenspeicher ErmittleDruck(Global g,int iAic)
    {
      return new Tabellenspeicher(g,"select b.aic_begriff aic,b.defbezeichnung bezeichnung,b.kennung,b.aic_stammtyp,b.Tod,b.web,b.Prog from begriff b"+g.join("druck_Zuordnung","b","begriff")+" where aic_abschnitt="+iAic,true);
    }

    private Tabellenspeicher ErmittleModul(Global g,int iAic)
    {
      return new Tabellenspeicher(g,"select b.aic_begriff aic,b.defbezeichnung bezeichnung,b.kennung,bg.kennung Begriffgruppe,b2.defBezeichnung Begriff,b.Prog,b.web from begriffgruppe g"+g.join("begriff","b","g","begriffgruppe")+
                                  " join begriff_zuordnung z on z.aic_begriff=b.aic_begriff join begriff b2 on z.beg_aic_begriff=b2.aic_begriff and z.beg_aic_begriff="+iAic+
                                  " join begriffgruppe bg on bg.aic_begriffgruppe=b2.aic_begriffgruppe where g.kennung='Modul'",true);
    }

    private boolean Ermittle(JCOutlinerFolderNode Nod,Global g,Integer iAic,String sArt,boolean b)
    {
      if (b)
      {
        if (VecB.contains(iAic))
          return false;
        VecB.addElement(iAic);
      }
      //else if (iAic.equals(Static.Int0))
      //  return false;
      String s=null;
      if (sArt.equals("Frame"))
        s="select distinct b.aic_begriff aic,b.defbezeichnung bezeichnung,b.kennung,b.aic_stammtyp,b.Tod,b.web,b.prog" + /*g.AU_Bezeichnung("Stammtyp", "b") + " Stammtyp,count(*) Anzahl"*/" from begriff b"+g.join("formular", "b", "begriff")
            + g.join("darstellung", "d", "formular", "formular") + " where d.aic_begriff=" + iAic;//+" group by b.aic_begriff,b.defbezeichnung,b.kennung,b.aic_stammtyp,b.tod";
      else if (sArt.equals("Abfrage"))
        s="select distinct b.aic_begriff aic,b.defbezeichnung bezeichnung,b.kennung,b.aic_stammtyp,b.Tod,b.web,b.prog from begriff b"+g.join("abfrage", "b", "begriff")+" where aic_modell="+g.BegriffToModell(iAic);
      else if (sArt.equals("Modell"))
        s="select distinct defbezeichnung bezeichnung,begriff.kennung,begriff.aic_begriff aic,begriff.Tod,begriff.web,begriff.prog from abfrage"+g.join2("spalte","abfrage")+g.join2("befehl2","spalte")+
            ",modell"+g.join("begriff","modell")+" where befehl2.aic_modell=modell.aic_modell and abfrage.aic_begriff="+iAic;
      else if (sArt.equals("SubModell"))
        s="select distinct defbezeichnung bezeichnung,kennung,begriff.aic_begriff aic,begriff.Tod,begriff.web,begriff.prog from begriff"+g.join2("modell","begriff")+" join befehl2 b on modell.aic_modell=b.aic_modell" +
                                                 " where b.Mod_aic_modell=" +g.BegriffToModell(iAic);
      Tabellenspeicher Tab=new Tabellenspeicher(g, s, true);
      if (Tab != null && !Tab.isEmpty())
        addNode(Nod,sArt.equals("SubModell") ? "Modell":sArt,Tab);
      return true;
    }

    private void ShowVerlauf()
    {
      int iAic=Sort.geti(Out.getSelectedNode().getUserData(),1);
      new Tabellenspeicher(g,"select Dauer,timestamp,fertig,Abbruch,(select bezeichnung from Stammview2 where aic_rolle is null and aic_stamm=v.aic_stamm) Stamm,von,bis"+
                           ",(select kennung from benutzer where l.aic_benutzer=aic_benutzer) Benutzer,(select kennung from Mandant where l.aic_Mandant=aic_Mandant) Mandant"+
                           ",(select kennung from Computer where l.aic_Computer=aic_Computer) Computer"+
                           " from verlauf v join logging l on v.aic_logging=l.aic_logging where timestamp>(select von from zr) and aic_begriff="+iAic,true).showGrid(Sort.gets(Out.getSelectedNode().getLabel(),2),thisJFrame());
    }
    
    private void ShowDefVerlauf()
    {
      int iAic=Sort.geti(Out.getSelectedNode().getUserData(),1);
      new Tabellenspeicher(g,"select tat,timestamp,(select benutzer.kennung from benutzer join logging l on benutzer.aic_benutzer=l.aic_benutzer where l.aic_logging=v.aic_logging) Benutzer"+
              " from defverlauf v where aic_begriff="+iAic+" order by aic_defverlauf desc",true).showGrid(Sort.gets(Out.getSelectedNode().getLabel(),2),thisJFrame());
    }

    private void ShowInfo()
    {
      long lClock = Static.get_ms();
      VecB=new Vector<Integer>();
      Vector Vec=(Vector)(Out.getSelectedNode()).getUserData();
      Vector VecV=(Vector)(Out.getSelectedNode()).getLabel();
      String sTitel=Static.beiLeer(Sort.gets(VecV,3),Sort.gets(VecV,2));
      JDialog Dlg=new JDialog((JFrame)thisFrame,sTitel);
      int iTab=Sort.geti(Vec,0);
      int iAic=Sort.geti(Vec,1);
      String sArt=(String)Vec.elementAt(2);
      Vector<Comparable> VecVisible = new Vector<Comparable>();
      VecVisible.addElement(sArt);
      VecVisible.addElement(Sort.gets(VecV,3));
      VecVisible.addElement(Sort.gets(VecV,2));
      if (iTab==g.iTabBegriff)
      {
    	int iPos=g.TabBegriffe.getPos("Aic", iAic);
    	VecVisible.addElement(iPos<0 ? Static.sLeer:Static.JaNein2(g.TabBegriffe.getB(iPos,"Web")));
    	VecVisible.addElement(iPos<0 ? Static.sLeer:g.TabCodes.getBezeichnungS(g.TabBegriffe.getI(iPos,"Prog")));
      }
      else
      {
        VecVisible.addElement(null); // Web
        VecVisible.addElement(null); // Prog
      }
      VecVisible.addElement(Sort.gets(VecV,7));
      VecVisible.addElement(null);
      VecVisible.addElement(iAic);
      final AUOutliner OutS= new AUOutliner(new JCOutlinerFolderNode(VecVisible));
      JCOutlinerFolderNode NodR=(JCOutlinerFolderNode)OutS.getRootNode();
      OutS.setColumnButtons(new String[] {"Gruppe","Bezeichnung","Kennung","Web","Prog","Typ","Tod","Aic"});
      OutS.setNumColumns(8);
      if (g.Def())
        OutS.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
            //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
            if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM) && g.Def())
            {
              g.testInfo("OutS.actionPerformed");
              Edit(0, OutS);
            }
          }
          public void mouseEntered(MouseEvent ev) {}
          public void mouseExited(MouseEvent ev) {}
          public void mouseReleased(MouseEvent ev) {}
        });

      g.testInfo(sTitel+":"+sArt+","+iAic);
      if (sArt.equals("STAMM"))
      {
        addNode(NodR, "Abfrage", ErmittleAbfragenS(g, iAic));
        addNode(NodR, "Frame", ErmittleFrameS(g, iAic));
        addNode(NodR, "EIGENSCHAFT", ErmittleEigS(g, iAic));
      }
      else if (sArt != Static.sLeer)
      {
        Ermittle(g,NodR,sArt,iAic);
        //if (Ermittle(NodR, g, iAic, "Modell", true))
        //  Ermittle(NodR, g, iAic, "Frame", false);
      }
      OutS.folderChanged(NodR);
      Dlg.getContentPane().add(OutS);
      Dlg.pack();
      Dlg.setSize(Dlg.getWidth()+20, Dlg.getHeight()+32);
      Static.centerComponent(Dlg, thisJFrame());
      g.clockInfo("Show " + sTitel, lClock);
      Dlg.setVisible(true);
    }

    private void show2()
    {
      super.show();
      EdtSprache1.requestFocus();
    }

    /*private void Take()
    {
      Vector Vec=(Vector)(Out.getSelectedNode()).getLabel();
      EdtSprache2.setText(""+Vec.elementAt(5));
    }
*/
}
