/*
    All_Unlimited-Allgemein-AUTable.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
//import javax.swing.JLabel;
//import java.awt.event.WindowEvent;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JComponent;
//import javax.swing.JOptionPane;
//import javax.swing.JToolBar;


import jclass.bwt.BWTEnum;
import jclass.table3.JCCellRange;
import jclass.table3.JCTable;
import jclass.table3.JCTblEnum;
import jclass.table3.JCTraverseCellEvent;
import jclass.table3.JCTraverseCellListener;
import jclass.table3.LabelTrigger;
import jclass.table3.TableScrollbar;
/*import All_Unlimited.Allgemein.Anzeige.Ampel;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Anzeige.HierarchieAnzeige;
import All_Unlimited.Allgemein.Anzeige.JaNein;
import All_Unlimited.Allgemein.Anzeige.Mass;
import All_Unlimited.Allgemein.Anzeige.Memo1;
import All_Unlimited.Allgemein.Anzeige.PW1;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Waehrung;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.Zeit;*/
import All_Unlimited.Allgemein.Anzeige.*;
import All_Unlimited.Allgemein.Eingabe.*;
import All_Unlimited.Grunddefinition.DefModell;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.Aufruf;
import All_Unlimited.Hauptmaske.Calc;
import All_Unlimited.Hauptmaske.Import;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.TCalc;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;

import All_Unlimited.Grunddefinition.DefAbfrage2;
import All_Unlimited.Hauptmaske.AClient;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.JEditorPane;

import All_Unlimited.Print.DruckHS;
import All_Unlimited.Print.Drucken;

public class AUTable extends javax.swing.JPanel implements ActionListener
{
/**
	 *
	 */
	private static final long serialVersionUID = -3464588860370582059L;
public AUTable(Formular rFom,int riAIC_Begriff_Abfrage, int riAIC_Stamm,int riAIC_Stammtyp, Global glob,boolean rbBew,Vector rVecMod,int riD,ActionListener revent)
{
  //sBez= glob.Clock()?SQL.getString(glob,"select defbezeichnung from begriff where aic_begriff="+riAIC_Begriff_Abfrage):"";
  long lClock1=Static.get_ms();
	g=glob;
	iD=riD;
	Fom=rFom;
	VecMod=rVecMod;
        evRefresh=revent;
	//g.progInfo("AUTable("+riAIC_Begriff_Abfrage+","+riAIC_Stamm+","+riAIC_Stammtyp+")");

	colorBackground = Global.ColTable;//table.getBackground(0,0);
        setBackground(Global.ColTable);
        table.setBackground(Global.ColTable);

	iAIC_Stamm = riAIC_Stamm;
	iAIC_Stammtyp = riAIC_Stammtyp;
	iAIC_Begriff_Abfrage = riAIC_Begriff_Abfrage;
        bBew=rbBew;
        Start(false);
        //addPopup();
        if (TabColumn.posInhalt("Haupt","AUTable" + iAIC_Begriff_Abfrage))
        {
          int i=TabColumn.getI("int4");
          if (MnuAsk !=null)
            MnuAsk.setSelected((i&1)>0);
          MnuWeiter.setSelected((i&2)>0);
        }
	dtMoa=bBew ? null:g.getAbschlussDate(/*Fom.getBegriff(),*/iAIC_Bewegungstyp,riAIC_Stamm);
        EnableButtons();
    g.clockInfo2("AUTable "+sDefBez,lClock1);
}

public void suchen(Point P)
        {
          if (DlgSuche==null)
          {
                DlgSuche=new JDialog((JFrame)Fom.thisFrame,g.getBegriffS("Dialog","Suche"));
                P.translate(-100,-70);
                if(P.y<0)
                  P.y=0;
                DlgSuche.setLocation(P);

                JPanel Pnl = new JPanel(new BorderLayout());
                //final JCheckBox CbxStart=g.getCheckbox("Volltextsuche",(iSuchBits&1)==0);
                //final JCheckBox CbxSucheCase=g.getCheckbox("Case",(iSuchBits&4)>0);
                final ComboSort CboSuche=new ComboSort(g,ComboSort.kein);
                CboSuche.setFont(Global.fontBezeichnung);
                //ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                //Tabellenspeicher TabSpalten=A.getSpalten();
                /*if (TabSpalten == null || TabSpalten.isEmpty())
                {
                        CboSuche.addItem(g.getBegriff("Show","Bezeichnung"),1,"");
                }
                else*/
                {
                        Vector VecGidSpalten = A.getBezeichnung();
                        for(int i=0;i<VecGidSpalten.size();i++)
                                CboSuche.addItem(""+VecGidSpalten.elementAt(i),i+1,"");
                }
                //CboSuche.setSelectedAIC(iSpalte);
                JPanel Pnl2 = new JPanel(new BorderLayout(2,2));
                Pnl2.add("West",CboSuche/*new JLabel("Suchtext:")*/);
                final Text Edt=new Text("",20);
                Edt.setFont(Transact.fontStandard);
                Pnl2.add("Center",Edt);
                Pnl.add("North",Pnl2);
                //Pnl2 = new JPanel(new FlowLayout());
                //Pnl2.add(CbxStart);
                //Pnl2.add(CbxSucheAb);
                //Pnl2.add(CbxSucheCase);
                //Pnl.add("Center",Pnl2);
                JScrollPane Scroll=new JScrollPane(Pnl);
                Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
                DlgSuche.getContentPane().add("North",Scroll);

                Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
                //JButton BtnDlgSuche=g.getButton("Suche");
                BtnSucheOk=g.getButton("Weiter");
                //JButton BtnDlgHelp = g.getButton("help_hs3");

                BtnSucheOk.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                //g.progInfo("Suche:"+Edt.getText()+"/"+iSTT_Mom);
                                //String sSuchtext=Edt.getText();
                                //bStartedMit=CbxStart.isSelected();
                                //bAb=false;
                                //iSuchBits=(CbxStart.isSelected()?0:1);//+2+(CbxSucheCase.isSelected()?4:0);
                                //iSpalte=CboSuche.getSelectedAIC();
                                /*JCOutlinerFolderNode Nod=(JCOutlinerFolderNode)((JCOutliner)TabOutliner.getInhalt("Gid")).getRootNode();
                                if (suche(Nod,sSuchtext,CboSuche.getSelectedAIC()-1))
                                {
                                  bAb=true;
                                  suche(Nod,sSuchtext,CboSuche.getSelectedAIC()-1);
                                }*/
                                int iPos=-1;
                                int iSpalte=CboSuche.getSelectedAIC() - 1;
                                String sText=Edt.getText();
                                for (int i=iMomY+1;i!=iMomY && iPos==-1;i++)
                                {
                                  //g.progInfo(i + ":" + table.getCell(i, iSpalte));
                                  String sMom=Sort.gets(table.getCell(i, iSpalte));
                                  //if((iSuchBits&4)==0)
                                  //{
                                          sMom=sMom.toUpperCase();
                                          sText=sText.toUpperCase();
                                  //}
                                  if(MnuBeginn.isSelected() ? sMom.startsWith(sText):sMom.indexOf(sText)>-1)
                                    iPos=i;
                                  if (i==table.getNumRows())
                                    i=-1;
                                }
                                //DlgSuche.dispose();
                                if (iPos>=0)
                                  if (MnuZeilen != null && MnuZeilen.isSelected())
                                    table.setSelectedCells(new JCCellRange(iPos,iSpalte,iPos,iSpalte));
                                  else
                                  {
                                    iMomY=iPos;
                                    iMomX=iSpalte;
                                    table.traverse(iMomY,iMomX, true, true);
                                  }
                                table.repaint();
                                //Dlg.dispose();
                        }
                });
                JButton BtnDlgBeenden=g.getButton("Beenden");
                BtnDlgBeenden.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                DlgSuche.setVisible(false);
                        }
                });
                //Pnl.add(BtnDlgSuche);
                Pnl.add(BtnSucheOk);
                //Pnl.add(BtnDlgHelp);
                Pnl.add(BtnDlgBeenden);
                DlgSuche.getContentPane().add("South",Pnl);
                DlgSuche.pack();
                DlgSuche.setVisible(true);
                Edt.requestFocus();
          }
          DlgSuche.getRootPane().setDefaultButton(BtnSucheOk);
          DlgSuche.setVisible(true);
	}

private void checkZeilen(boolean b)
  {
    //g.fixtestInfo("checkZeilen:"+b);
        boolean bAuswahl=sTabTyp!=null && sTabTyp.equals("Auswahl");
        //b=b || bAuswahl;
        if (MnuZeilen!=null && MnuZeilen.isSelected()!=b)
          MnuZeilen.setSelected(b);
        bEdit=!b;
        EnableButtons();
        if (b)
        {
          int i=iOldY;
          //g.progInfo("Zeile="+iOldY+"/"+iOldX);
          EnterCell(iOldY,iOldX,-1,-1);
          if(Modified())
            Save(0,MnuAsk.isSelected(),iAIC_Stamm);
          table.setModeList();
          if (bAuswahl)
            table.setSelectionPolicy(JCTblEnum.SELECT_MULTIRANGE);
          table.setSelectedCells(new JCCellRange(i,0,i,1));
        }
        else{
          table.setSelectedCells((Vector)null);
          table.setModeTable();
        }
  }

public void setHide()
{
  boolean b=!table.isEnabled();
  //g.setAbfrageWeg(Fom.getBegriff(),iAIC_Begriff_Abfrage, b?0:1);
  g.AbfrageAusblenden(iD,iAIC_Begriff_Abfrage,!b);
  if (b)
    setEnabled(b);
  else
    table.setNumRows(0);
  Refresh();
}

private void addMnuModell(JMenu MnuSub,int iAic)
{
	int iPos=g.TabBegriffe.getPos("Aic",iAic);
	if (iPos>=0)
	{
	  if (MActL==null)
            MActL=new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                String s = ev.getActionCommand();
                int iModBegriff = Sort.geti(s.substring(1));
                char c = s.charAt(0);
                if (c == 'D')
                  DefModell.get(g, iModBegriff).show();
                else if (c == 'T')
                  Fom.ToolTipEdit((JComponent)((JMenuItem)ev.getSource()).getParent(), "B" + iModBegriff);
                else
                {
                  boolean bDebug=c=='+' || c=='x';
                  Fom.bCalc=true;
                  //boolean bKeinReCalc=c=='N' || c=='x';
                  g.checkModelle();
                  int iPos = g.TabModelle.getPos("Aic_Begriff", iModBegriff);
                  int iMbits = g.TabModelle.getI(iPos, "bits");
                  boolean bBew = (iMbits & Global.cstBew) > 0;
                  int iMSatz = bBew || iArt == Stamm ? getAIC(iMomY).intValue() : iAIC_Stamm;
                  //Vector<Integer> VecMSelect=new Vector<Integer>();
                  if(bDebug)g.setDebug(true);
                  int iAicNeu = TCalc.Berechnen(g, iModBegriff, iMbits, iMSatz /*bBew ? iSatz:iStamm*/, null /*VecMSelect*/, Fom, 0,null);
                  if(bDebug)g.setDebug(false);
                  if ((iMbits & Global.cstRefreshM) > 0 && iAicNeu >= 0)
                    evRefresh.actionPerformed(null);
                  else
                    Refresh();
                  Fom.bCalc=false;
                }
              }
            };
          JMenuItem Mnu;
          if (g.Def())
          {
              Mnu = new JMenu(g.getBegBez2(iPos));
              g.addMenuItem("Modell",(JMenu)Mnu,"-"+iAic).addActionListener(MActL);
              g.addMenuItem("DefModell",(JMenu)Mnu,"D"+iAic).addActionListener(MActL);
              g.addMenuItem("Modell neu",(JMenu)Mnu,"N"+iAic).addActionListener(MActL);
              g.addMenuItem("Debug Modell",(JMenu)Mnu,"+"+iAic).addActionListener(MActL);
              g.addMenuItem("Debug Modell neu",(JMenu)Mnu,"x"+iAic).addActionListener(MActL);
              g.addMenuItem("set Tooltip",(JMenu)Mnu,"T"+iAic).addActionListener(MActL);
          }
          else
          {
              Mnu = new JMenuItem(g.getBegBez2(iPos));
              Mnu.setActionCommand("-" + iAic);
              Mnu.addActionListener(MActL);
          }
          Mnu.setFont(Global.fontPopup);
          g.setTooltip(g.TabBegriffe.getS(iPos,"Tooltip"),Mnu);
          MnuSub.add(Mnu);
        }
}

private void addPopup()
  {
    //g.fixInfo("addPopup "+sDefBez);
    popup = new JPopupMenu();
    popup.setLabel("AUTable");
    if (bCalc && !Fom.ReadOnly())
    {
      MnuCalc = g.addMenuItem("Calc", popup);
      MnuCalc.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Calc(false);
        }
      });
      if (g.Def())
      g.addMenuItem("Debug Modell",popup).addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Calc(true);
        }
      });

      MnuCalc.setVisible(false);
    }
    if (VecMod != null && !VecMod.isEmpty() && !Fom.ReadOnly())
    {
    	JMenu MnuSub = g.addMenu("Modelle",popup);//new JMenu(g.getBegriff("Show", "Modell"));
    	//popup.add(MnuSub);
    	for (int i=0;i<VecMod.size();i++)
    		addMnuModell(MnuSub,Sort.geti(VecMod,i));
    }
    if (g.Def() && !bSperre)
    {
      MnuZeilen = g.addCbxItem("Zeilen", popup, !bEdit);
      MnuZeilen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          checkZeilen(((JCheckBoxMenuItem)e.getSource()).isSelected());
        }
      });
      popup.addSeparator();
    }
    MnuSuchen=g.addMenuItem("Tb_Suche",popup);
    MnuSuchen.addMouseListener(new MouseListener()
    {
      public void mousePressed(MouseEvent ev)
      {
        P = ((Component)ev.getSource()).getLocationOnScreen();
        P.translate(ev.getX(), ev.getY());
      }

      public void mouseClicked(MouseEvent ev)
      {
      }

      public void mouseEntered(MouseEvent ev)
      {
      }

      public void mouseExited(MouseEvent ev)
      {
      }

      public void mouseReleased(MouseEvent ev)
      {
        suchen(P);
      }

    });
    MnuSuchen.setVisible(false);
    if (iSpalteEMail>-1)
    {
      MnuEMail = g.addMenuItem("E-Mail", popup);
      MnuEMail.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          //Static.OpenURL("mailto:" + table.getCell(((JCCellRange)table.getSelectedCells().elementAt(0)).start_row,iSpalteEMail));
          EMail(MnuZeilen != null && MnuZeilen.isSelected() ? ((JCCellRange)table.getSelectedCells().elementAt(0)).start_row:iMomY);
        }
      });
      MnuEMail.setVisible(false);
    }


    //if(!Abfrage.is(iBitsAbfrage,Abfrage.cstKeinNeu))
      MnuNew=g.addMenuItem("tab_new",popup,null,AL);

      MnuDel=g.addMenuItem("tab_del", popup,null,AL);
      MnuUndel=g.addMenuItem("tab_undel", popup,null,AL);

    if(/*!Abfrage.is(iBitsAbfrage,Abfrage.cstKeinSave) &&*/ !Fom.ReadOnly())
      MnuSave=g.addMenuItem("tab_save",popup,null,AL);
    //popup.addSeparator();
    g.addMenuItem("tab_refr",popup,null,AL);
    if (g.Def())
    {
    	if (A.iModell>0)
    	{
    		MnuM1 = g.addCbxItem("Debug_Modell", popup, false);
    	      MnuM1.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) 
    	        {
    	        	int iModBegriff=g.ModellToBegriff(A.iModell);
	  	        	if (MnuM1.isSelected())
	  	        		Aufruf.addDebugModell(iModBegriff);
	  	        	else
	  	        		Aufruf.removeDebugModell(iModBegriff);
    	        }
    	      });
    	}
//    		g.addMenuItem("Debug_Modell",popup,null,AL);
    	if (A.iModell2>0)
    	{
    		 MnuM2 = g.addCbxItem("Debug_Modell2", popup, false);
    	      MnuM2.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) 
    	        {
    	        	int iModBegriff=g.ModellToBegriff(A.iModell2);
    	        	if (MnuM2.isSelected())
    	          		Aufruf.addDebugModell(iModBegriff);
    	        	else
    	        		Aufruf.removeDebugModell(iModBegriff);
    	        }
    	      });
    	}
//    		g.addMenuItem("Debug_Modell2",popup,null,AL);
    }
    g.addMenuItem("Druck",popup,"Drucken",AL);
    popup.addSeparator();
    /*g.addMenuItem("tab_hide",popup).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev)
      {
        setHide();
      }
    });*/

    if(g.History())
      g.addMenuItem("History",popup,null,AL);
    if (!sTabTyp.endsWith("erf"))
     g.addMenuItem("Abfrage easy",popup,null,AL);
    if(g.Abfrage())
    {
      if (g.Def() || (A.iBits&Abfrage.cstNoChange)==0)
        g.addMenuItem("Abfrage", popup,null,AL);
      if (g.Def())
        g.addMenuItem("Abfrage2", popup,null,AL);
      g.addMenuItem("Init", popup,null,AL);

    }
    if (sTabTyp.endsWith("erf"))
    {
      g.addMenuItem("List_SE", popup,null,AL);
      boolean bMenge=(A.iBits&Abfrage.cstMengen)>0;
      g.addMenuItem("List_SE_"+(bMenge?"group":"this"), popup,null,AL);
      MnuSE_this=g.addMenuItem("Init_SE_this", popup,null,AL);
      MnuSE_leer=g.addMenuItem("Init_SE_leer", popup,null,AL);
      if (g.Def())
      {
        g.addMenuItem("Init_SE_"+(bMenge?"group":"Stamm"), popup, null, AL);
        g.addMenuItem("Init_SE_All", popup, null, AL);
      }
    }
    /*g.addMenuItem("tab_para",popup).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Parameter();
      }
    });*/
    //if (!bEdit)
      g.addMenuItem("Info",popup,null,AL);
    popup.addSeparator();
    if(g.Geloeschte())
    {
      g.addCbxItem("tab_sdel", popup, false).addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          A.bEntfernte = ((JCheckBoxMenuItem)e.getSource()).isSelected();
          A.SQL_String();
          Refresh();
        }
      });
    }
    if (g.Def())
      MnuAsk=g.addCbxItem("Frage", popup, false);
    MnuWeiter=g.addCbxItem("weiter", popup, false);
    MnuBeginn=g.addCbxItem("Beginnsuche", popup, false);
    /*if (TCalc.IsAlive())
      g.addMenuItem("AServer", popup).addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          TCalc.showAServer(g);
        }
      });*/

    if (g.Def())
    {
      popup.addSeparator();
      JMenu MnuTab = new JMenu("Tabellenspeicher");
      popup.add(MnuTab);
      addPopupForTab(MnuTab, "Aenderungen");
      addPopupForTab(MnuTab, "Daten");
      if (sTabTyp.endsWith("erf"))
        addPopupForTab(MnuTab, "SE");
      addPopupForTab(MnuTab, "Spalten");
      addPopupForTab(MnuTab, "Sp");
      addPopupForTab(MnuTab, "Column");
      addPopupForTab(MnuTab, "Spalten2");
      addPopupForTab(MnuTab, "Spalten2-BG");
      addPopupForTab(MnuTab, "Spalten2-Jeder");
      addPopupForTab(MnuTab, "Rest");
      addPopupForTab(MnuTab, "Gruppen");
      g.addMenuItem("Spaltenbreite", popup,null,AL);

      if (A.iModell>0)
      {
	JMenuItem Mnu=new JMenuItem("M "+g.getModellBez(A.iModell));
	popup.add(Mnu);
	Mnu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          DefModell.get(g, g.ModellToBegriff(A.iModell)).show();
        }});
      }
      if (A.iModell2>0)
      {
        JMenuItem Mnu=new JMenuItem("M "+g.getModellBez(A.iModell2));
        popup.add(Mnu);
        Mnu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          DefModell.get(g, g.ModellToBegriff(A.iModell2)).show();
        }});
      }
      popup.add(new JMenuItem("A "+sDefBez));
      popup.addSeparator();
      g.addCbxItem("sichtbar", popup, bSichtbar).addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          bSichtbar=((JCheckBoxMenuItem)ev.getSource()).isSelected();
        }
      });
      g.addCbxItem("gauge2", popup, bGauge).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                bGauge=((JCheckBoxMenuItem)ev.getSource()).isSelected();
              }
      });
    }
  }

  private void EMail(int i)
  {
    if (iSpalteEMail>-1 && i>-1)
    Static.OpenURL("mailto:" + table.getCell(i,iSpalteEMail));
  }

  private void Calc(boolean bDebug)
  {
    //g.clockStart();
    Fom.bCalc=true;
    if (bDebug && g.Def()) g.setDebug(true);
    final Message Msg=new Message(Message.INFORMATION_MESSAGE,(JFrame)Fom.thisFrame,g,-1);
    Msg.showDialog("rechnet");
    g.testInfo("Calc1");
    new Thread(new Runnable()
    {
      public void run() {
        int iSx=TabSpalten.getVecSpalte("Kennung").size();
        Vector<Integer> Vec=new Vector<Integer>();
        if (bEdit)
          Vec.addElement(Tabellenspeicher.geti(table.getCell(iMomY,iSx)));
        else
          for(int iPos=0;iPos<table.getNumRows();iPos++)
            if (table.isSelected(iPos,0))
              Vec.addElement(Sort.geti(table.getCell(iPos,iSx)));
        g.fixtestInfo("Calc mit:"+Vec);
        for(int iPos=0;iPos<Vec.size();iPos++)
        {
          int iAic = Sort.geti(Vec, iPos);
          if (iAic > 0)
            try
            {
              TCalc.TimerBerechnung(g, Fom, iAic);
            }
            catch (Exception e) { g.printStackTrace(e); }
        }
        //g.testInfo("Calc4");
        g.setDebug(false);
        //g.testInfo("Calc5");
        Msg.dispose();
        //g.testInfo("Calc6");
      }}).start();
    //g.clockInfo("Calc gestartet");
    //g.testInfo("Calc2");
    Msg.setVisible(true);
    //g.testInfo("Calc7");
    //if ((iMbits & Global.cstRefreshM) > 0 && iAicNeu >= 0)
    evRefresh.actionPerformed(null);
    Fom.bCalc=false;
    //g.testInfo("Calc8");
    //g.clockInfo("Calc beendet");
  }

  /*private void Parameter()
  {
    Static.centerComponent(FrmParameter,thisComponent);
    FrmParameter.setVisible(true);
  }*/

  private int getRow()
  {
    int iRow=-1;
    for (int iPos = 0; iRow < 0 && iPos < table.getNumRows(); iPos++)
      if (table.isSelected(iPos, 0))
        iRow = iPos;
    return iRow;
  }

  private void History()
  {
    if (!bEdit)
    {
      iMomY=getRow();/*-1;
      for (int iPos = 0; iMomY < 0 && iPos < table.getNumRows(); iPos++)
        if (table.isSelected(iPos, 0))
          iMomY = iPos;*/
    }
    g.fixtestInfo("History:"+iMomY);
    if(g.History() && iMomY>=0)
    {
      A.showHistory2(Fom, getAIC(iMomY).intValue(),A.iModell2,(A.iBits & Abfrage.cstStempelimport) > 0);
      if(A.bRefresh)
      {
        //g.progInfo("Refresh nach Undelete");
        Refresh();
      }
    }
  }

  private void addPopupForTab(JMenu Mnu,String s)
        {
          JMenuItem MnuTab = new JMenuItem(s);
          Mnu.add(MnuTab);
          MnuTab.setActionCommand(s);
          if (AL1 == null)
            AL1 = new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                String s2 = ev.getActionCommand();
                Tabellenspeicher.showGrid(Tab, "Daten", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(TabSpalten, "Spalten", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(A.TabSp, "Sp", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(TabAenderungen, "Aenderungen", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(TabColumn, "Column", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(TabRest, "Rest", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(Abfrage.getBA(g,"2a"), "Spalten2", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(Abfrage.getBA(g,"2b"), "Spalten2-BG", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(Abfrage.getBA(g,"2c"), "Spalten2-Jeder", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(TabSE, "SE", s2,Fom.thisFrame);
                Tabellenspeicher.showGrid(TabGruppen, "Gruppen", s2,Fom.thisFrame);
              }
            };
          MnuTab.addActionListener(AL1);
        }

private void setSorted(boolean b)
{
	if(b!=bSort)
	{
		bSort=b;
		if(b)
			table.setColumnTrigger(new LabelTrigger(Event.ALT_MASK,LabelTrigger.SORT));
		else
			table.removeRowTrigger(Event.ALT_MASK);
	}
}

public static void readColumns(Global g)
      {
//        g.fixtestError("----------------------------   readColumns");
        TabColumn=new Tabellenspeicher(g,"select aic_parameter,h.kennung Haupt,"+g.int1_int4()+",p.aic_hauptgruppe,p.aic_nebengruppe"+
                               " from hauptgruppe h"+g.join("parameter","p","h","hauptgruppe")+g.join("nebengruppe","n","p","nebengruppe")+
                               " where n.kennung='SpaltenNeu' and aic_benutzer="+g.getBenutzer()+" order by h.kennung,"+g.int1(),true);
        TabColumn.sGruppe="Haupt";
        TabColumn.sAIC="int1";
        //TabColumn.clearAll();
        //if(g.Debug())
        //        TabColumn.showGrid("TabColumn");
      }

private void showColumns()
      {
        g.progInfo("Abfrage="+iAIC_Begriff_Abfrage);
        Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Nr","Pos","Spalte","Vorher","Nachher","anders"});
        for (TabSpalten.moveFirst();!TabSpalten.out();TabSpalten.moveNext())
        {
          boolean bVorhanden=TabColumn.posInhalt((Object)("AUTable"+iAIC_Begriff_Abfrage),TabSpalten.getInhalt("nummer"));
          int iVorher=bVorhanden ? TabColumn.getI("int2"):Sort.geti(VecWidth,TabSpalten.getPos());
          int iNacher=table.getPixelWidth(TabSpalten.getPos());
//          g.fixInfo(TabSpalten.getI("nummer")+"/"+TabSpalten.getPos()+":"+iVorher+"->"+iNacher+" ("+(iVorher==iNacher)+")");
          Tab.addInhalt("Nr",TabSpalten.getI("nummer"));
          Tab.addInhalt("Pos",TabSpalten.getPos());
          Tab.addInhalt("Spalte",TabSpalten.getS("Bezeichnung"));
          Tab.addInhalt("Vorher",iVorher);
          Tab.addInhalt("Nachher",iNacher);
          Tab.addInhalt("anders",Static.JaNein2(iVorher!=iNacher));
        }
        Tab.showGrid("Spaltenbreite bei "+sDefBez, Fom.thisJFrame());
      }

public boolean saveColumns()
      {
        g.saveInfo("saveColumns Abfrage="+sDefBez);
        int iHaupt=0;
        int iNeben=0;
        SQL Qry=new SQL(g);
        boolean b=false;
        for (TabSpalten.moveFirst();!TabSpalten.out();TabSpalten.moveNext())
        {
          boolean bVorhanden=TabColumn.posInhalt((Object)("AUTable"+iAIC_Begriff_Abfrage),TabSpalten.getInhalt("nummer"));
          int iVorher=bVorhanden ? TabColumn.getI("int2"):Sort.geti(VecWidth,TabSpalten.getPos());
          int iNachher=table.getPixelWidth(TabSpalten.getPos());
          if (iVorher != iNachher)
          {
            if (iHaupt==0)
              iHaupt = SQL.getInteger(g, "select aic_hauptgruppe from hauptgruppe where kennung='AUTable" + iAIC_Begriff_Abfrage + "'");
            if (iHaupt==0)
            {
              Qry.add("Kennung","AUTable"+iAIC_Begriff_Abfrage);
              iHaupt=Qry.insert("Hauptgruppe",true);
            }
            Qry.add("aic_Hauptgruppe",iHaupt);
            if (iNeben==0)
              iNeben=SQL.getInteger(g,"select aic_nebengruppe from nebengruppe where kennung='SpaltenNeu'");
            Qry.add("aic_Nebengruppe",iNeben);
            Qry.add("AIC_Benutzer",g.getBenutzer());
            Qry.add("int1",TabSpalten.getI("nummer"));
            Qry.add("AIC_Logging",g.getLog());
            Qry.add("int2",iNachher);
            if(bVorhanden)
              Qry.update("Parameter",TabColumn.getI("AIC_Parameter"));
            else
              Qry.insert("Parameter",false);
//            g.fixtestError("speichere Spalte "+TabSpalten.getS("Bezeichnung")+": "+iNachher);
            b=true;
          }
        }
        Qry.free();
        if (b)
        	readColumns(g);
        return b;
      }

	private int Eig(String sDT)
      {
        String sSpalte=TabSpalten.getS("Kennung2");
        //g.fixInfo("Eig-"+sDT+"="+sSpalte);
        if (sSpalte.equals("") || sDT.equals("BewBew"))
          return 0;
        //g.testInfo("v_"+sDT+":"+sSpalte);
        if (Abfrage.VRel.contains(sDT))
          sSpalte=sSpalte.substring(0,sSpalte.indexOf("_"));
        if (sSpalte.indexOf("_")>0)
          return 0;
        //g.testInfo("n_"+sDT+":"+sSpalte);
        return Sort.geti(sSpalte);
      }

private void PruefeAbfrage()
{
  //boolean bSE=false;
  boolean bSEok=true;
  for (A.TabSp.moveFirst(); !A.TabSp.out(); A.TabSp.moveNext())
    if (g.Spezial() && A.TabSp.getInhalt("Gruppe") != null) {
    } else
    {
      Vector VecEig = (Vector)A.TabSp.getInhalt("Vec");
      Object ObjEig = VecEig.elementAt(VecEig.size() - 1);
      int iPos = g.TabEigenschaften.getPos("Aic", Tabellenspeicher.geti(ObjEig));
      String sDatentyp = g.TabEigenschaften.getS(iPos, "Datentyp");
      if (sDatentyp.endsWith("BewZahl2") || sDatentyp.endsWith("BewMass2") || sDatentyp.endsWith("BewWaehrung2"))
      {
        //bSE = true;
        if (bSEok &&  A.TabSp.getI("Rel")==0 && (A.TabSp.getI("bits") & Abfrage.cstUnsichtbar)==0)
          bSEok=false;
      }
    }
  //g.progInfo("PruefeAbfrage:"+bHA+"/"+bSE+"/"+bSEok);
  if (!bSEok)
    new Message(Message.WARNING_MESSAGE,null/*(JFrame)Fom.thisFrame*/,g).showDialog("Abfrage_unfertig");
}

private void Start(boolean bFill)
{
  g.fixtestInfo("AUTable.Start "+bFill);
  if (DlgSuche != null)
  {
    DlgSuche.dispose();
    DlgSuche = null;
  }
        if (!bFirst && bEdit2)
          EnterCell(iOldY,iOldX,-1,-1);
	iRowLabel=0;
        //iSpalteANR=-1;
        iSpalteGueltigkeit=-1;
        iSpalteGueltig2=-1;
        iSpalteEMail=-1;
        iSpalteModell=-1;
        iSpalteZone=-1;
        iSpalteDST=-1;
	table.setFrozenColumns(0);
	//ParaColumn = new Parameter(g,"AUTable"+iAIC_Begriff_Abfrage);
	//ParaColumn.ErmittleParameter("SpaltenNeu",false);
	//TabColumn = ParaColumn.getBParameter("SpaltenNeu");
        if (TabColumn==null)
          readColumns(g);

	Vector Vec = ErmittleTab(iAIC_Stamm,iAIC_Stammtyp,bFill);
        PruefeAbfrage();
	//g.progInfo("bKeinStm: "+bKeinStm);
	//g.progInfo("bFehler: "+bFehler);
	if (bFehler)
		return;
	if (bFirst)
		Build();
	TabAenderungen = new Tabellenspeicher(g,new String[] {"Zeile","Spalte","Alter Wert","Neuer Wert","Datentyp","Aenderungsart","Keys","Alte Farbe","Eigenschaft"});
	Tab = (Tabellenspeicher)Vec.elementAt(0);
        TabSpalten = (Tabellenspeicher)Vec.elementAt(1);
	//if(g.Debug())
	//	TabSpalten.showGrid("TabSpalten");
	iArt = ((Integer)Vec.elementAt(2)).intValue();
	//Vector VecSpalten = TabSpalten.getVecSpalte("Kennung");

	TabRest=Import.getTabRest(g,iAIC_Bewegungstyp>0 && ((Abfrage.cstBewVoll&iBitsAbfrage)>0 || (g.getBewBits(iAIC_Bewegungstyp)&Global.cstBewVoll)>0),iAIC_Bewegungstyp);
	//TabRest=new Tabellenspeicher(g,"select aic_eigenschaft aic,null saved from bew_zuordnung where aic_bewegungstyp="+iAIC_Bewegungstyp+" order by reihenfolge",true);

	table.setAutoScroll(JCTblEnum.AUTO_SCROLL_BOTH);
	//table.setColumnLabels(TabSpalten.getVecSpalte("Bezeichnung"));
	//table.setNumColumns(TabSpalten.getVecSpalte("Bezeichnung").size()+(g.Debug()?1:0));

	//table.setColumnLabelSortMethod(Sort.sortMethod);

	/*for(int i=0;i<VecSpalten.size();i++)
	{
		table.setFont(-1,i,fontTitel);
		table.setForeground(-1,i,g.ColBezeichnung);
	}*/

	//Tabellenspeicher TabComboBox = new Tabellenspeicher(g, new String[] {"Name","ComboBox"});

	TabSpalten.moveFirst();
        bOne=true;
        iXmin=-1;
	for(int iSpaltenAnz=0;!TabSpalten.eof();iSpaltenAnz++)
	{
		String sDatentyp = TabSpalten.getS("Datentyp").trim();
		String sFormat = TabSpalten.getS("Format");
		int iMaxLength = Abfrage.getLaenge(TabSpalten);
                if (iSpalteGueltig2<0 && (TabSpalten.getI("bits")&Abfrage.cstGueltig2)>0 && sDatentyp.startsWith("BewDatum"))
                  iSpalteGueltig2 = iSpaltenAnz;
                if (TabSpalten.getS("Kennung2").indexOf("_")<0)
                {
	                int iEig=TabSpalten.getI("Kennung2");
	                if (iSpalteZone<0 && g.VecEigZone.contains(iEig))
	                	iSpalteZone=iSpaltenAnz;
	                if (iSpalteDST<0 && g.VecEigDST.contains(iEig))
	                	iSpalteDST=iSpaltenAnz;
                }
                if ((TabSpalten.getI("bits")&Abfrage.cstGruppiert)>0)
                {
                  iEigGruppe = TabSpalten.getI("Kennung2");
                  sFG=TabSpalten.getS("Format");
                  if (sFG!=null && sFG.equals("")) sFG=null;
                }
//                else if(sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") || sDatentyp.equals("Doku") || sDatentyp.equals("FixDoku")) // macht Gruppen (grauen Zellen)
//                	iEigGruppe = TabSpalten.getI("Kennung2");
                	
		if(sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe"))
		{
                  if (TabSpalten.getS("Kennung2").indexOf("_")<0)
                  {
                    final AUComboList Cbo = new AUComboList(null,Fom.getBegriff(),iAIC_Begriff_Abfrage, TabSpalten.getI("Kennung2"), TabSpalten.getI("Stt"), g,
                        /*TabColumn.posInhalt((Object)("AUTable" + iAIC_Begriff_Abfrage), TabSpalten.getInhalt("nummer")) &&
                        TabColumn.getI("int3") >= 0 ? TabColumn.getI("int3") :*/ TabSpalten.getI("Filter"),
                        (TabSpalten.getI("bits") & (Global.cstKeinAutoDate * 0x10000 + Abfrage.cstKeinAutoDate)) == 0,
                        (TabSpalten.getI("bits") & (Global.cstAlways * 0x10000)) == 0,true,(TabSpalten.getI("bits3") & Abfrage.cstNoFav)>0);
                    Cbo.getComboBox().bCombo2=(TabSpalten.getI("bits") & Abfrage.cstShow)==Abfrage.cstCombo2;
                    //Cbo.getComboBox().bStamm=true;
                    //Cbo.setFont(fontStandard);
                    if ((TabSpalten.getI("bits2") & Abfrage.cstMulti) >0)
                    {
                      ActionListener ev=new ActionListener()
                      {
                        public void actionPerformed(ActionEvent ev)
                        {
                          Vector Vec=Cbo.getSelectedAics();
                          if (Vec.size()<2)
                            return;
                          //g.progInfo("Event von AUComboList ausgelöst: "+Vec);
                          //Cbo.getComboBox().setAktAIC(0);
                          Cbo.getComboBox().setSelectedAIC(Sort.geti(Vec,0));
                          //Vector Vec2=table.getSelectedCells();
                          table.traverse(iMomY,iMomX,true,true);
                          EnterCell(iOldY,iOldX,-1,-1);
                          //g.progInfo(iMomY+"/"+iMomX+": 0,"+Sort.geti(Vec,0)+"->"+Cbo.getComboBox().getSelectedBezeichnung());
                          EnterNext();
                          for (int i=1;i<Vec.size();i++)
                          {
                            //iMomY++;
                            Neu();
                            //Cbo.getComboBox().setAktAIC(0);
                            Cbo.getComboBox().setSelectedAIC(Sort.geti(Vec,i));
                            table.traverse(iMomY,iMomX,true,true);
                            EnterCell(iOldY,iOldX,-1,-1);
                            EnterNext();
                            //table.traverse(iMomY,iMomX,true,true);
                            //g.progInfo(iMomY+"/"+iMomX+": "+i+","+Sort.geti(Vec,i)+"->"+Cbo.getComboBox().getSelectedBezeichnung());
                          }
                          //if (Vec.size()>1)
                          //{
                            Neu();
                            if (iMomY<0)
                              iMomY++;
                            Enter();
                            iMomY+=Vec.size();
                            //g.progInfo(iMomY+"/"+iMomX);
                            Loeschen();
                            iMomY-=Vec.size();
                            EnableButtons();
                          //}
                          //repaint();
                          //table.setSelectedCells(Vec2);
                          //checkZeilen(false);
                          //EnterCell(iOldY,iOldX,-1,-1);
                          //EnterNext();
                          //JOptionPane.showMessageDialog((JFrame)Fom.thisFrame,"Testpause!","Test",JOptionPane.INFORMATION_MESSAGE);
                          //Loeschen();
                          //Refresh();
                        }
                      };
                      Cbo.setMulti(true,ev);
                    }
                    TabSpalten.putInhalt("Component", Cbo, false);
                    //if (g.isEigANR(TabSpalten.getI("Kennung2")))
                    //  iSpalteANR = iSpaltenAnz;
                  }
		}
                else if(sDatentyp.equals("Passwort"))
                {
                        AUPasswort pw = new AUPasswort();
                        pw.setFont(fontStandard);
                        TabSpalten.putInhalt("Component",pw,false);
                }
		else if(sDatentyp.equals("E-Mail"))
		{
			EMail mail = new EMail(iMaxLength==0?60:iMaxLength,g);
            mail.setFont(fontStandard);
			TabSpalten.putInhalt("Component",mail,false);
            iSpalteEMail = iSpaltenAnz;
		}
		else if(sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular"))
		{
			ComboSort Cbo = new ComboSort(g);
                        if (sDatentyp.equals("BewModell"))
                          iSpalteModell = iSpaltenAnz;
			Cbo.fillBegriffTable(sDatentyp.equals("BewModell") ? "Modell":sDatentyp.equals("BewDruck") ? "Druck":"Frame",true);
			Cbo.setFont(fontStandard);
			TabSpalten.putInhalt("Component",Cbo,false);
		}
		else if(sDatentyp.equals("BewLand") || sDatentyp.equals("BewHoliday") || sDatentyp.equals("BewBG") || sDatentyp.equals("BewUser"))
		{
		  ComboSort Cbo = new ComboSort(g);
		  if (sDatentyp.endsWith("Land"))
            Cbo.fillDefTable("Land",true);
		  else if (sDatentyp.equals("BewHoliday"))
		    Cbo.fillDefTable("Feiertag",true);
		  else if (sDatentyp.equals("BewBG"))
			Cbo.fillDefTable("Benutzergruppe",true);
		  else if (sDatentyp.equals("BewUser"))
			Cbo.fillDefTable("Benutzer",true);
		  Cbo.setFont(fontStandard);
		  TabSpalten.putInhalt("Component",Cbo,false);
		}
                boolean bEdit=!Fom.ReadOnly() && (TabSpalten.getI("bits")&Abfrage.cstEditierbar)>0;
		if(bEdit || sDatentyp.equals("Text") || sDatentyp.equals("Memo") || sDatentyp.equals("Doku") || sDatentyp.equals("GPS"))//TabSpalten.getI("Locked")==0)
		{
			if(sDatentyp.equals("BewVon_Bis")||sDatentyp.equals("von_bis")||sDatentyp.equals("Auto_von_bis"))
                        {
                          VonBisEingabe Edt=new VonBisEingabe(null, null,Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"), g, TabSpalten.getI("Stamm"), iMaxLength);
                          Edt.setFont(fontStandard);
                          TabSpalten.putInhalt("Component",Edt, false);
                        }
			else if(sDatentyp.equals("GPS"))
			{
				GPS_Eingabe Edt=new GPS_Eingabe(g,bEdit);
				Edt.setFont(fontStandard);
                TabSpalten.putInhalt("Component",Edt, false);
			}
			else if(sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcDauer"))
                        {
                          MassEingabe Edt=new MassEingabe(TabSpalten.getI("Stamm"),g,true,(TabSpalten.getI("bits")&Abfrage.cstKeineEinheit)==0,iMaxLength,sFormat,(TabSpalten.getI("bits3")&Abfrage.cstInStunden)>0);
                          Edt.setFont(fontStandard);
                          if (TabSpalten.getI("max")>0)
                        	  Edt.setMax(TabSpalten.getI("max"));
                          TabSpalten.putInhalt("Component",Edt,false);
                        }
                        else if(sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
                        {
                          WaehrungsEingabe Edt=new WaehrungsEingabe(0.0,g.getWaehrung(),g,(TabSpalten.getI("bits")&Abfrage.cstKeineEinheit)==0,iMaxLength);
                          Edt.setFont(fontStandard);
                          TabSpalten.putInhalt("Component",Edt,false);
                        }
                        else if(sDatentyp.equals("BewDauer")||sDatentyp.startsWith("BewZahl")||sDatentyp.equals("Double")||sDatentyp.equals("Integer") || sDatentyp.equals("CalcField"))
			{
				Zahl2 z=new Zahl2((Object)null,sDatentyp.equals("Integer")?0:iMaxLength);
                                int iPosE=g.TabEigenschaften.getPos("AIC",Eig(sDatentyp));
				z.Edt.setMax(g.TabEigenschaften.getInt(iPosE,"max"));
				TabSpalten.putInhalt("Component",z,false);
			}
			else if(sDatentyp.endsWith("Boolean"))
                        {
                          AUCheckBox Cbx=new AUCheckBox(TabSpalten.getF("Faktor") != 0.0);
                          Cbx.setBackground(Global.ColTable);
                          TabSpalten.putInhalt("Component", Cbx, false);
                        }
                        else if(sDatentyp.endsWith("Bool3"))
                        {
                          SpinBoxAuswahl Edt=new SpinBoxAuswahl(g,TabSpalten.getI("Kennung2"));
                          TabSpalten.putInhalt("Component", Edt, false);
                        }
			else if(sDatentyp.equals("BewDatum")||sDatentyp.equals("BewDatum2")||sDatentyp.equals("Zeit"))
				TabSpalten.putInhalt("Component",new Datum(g,Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss")),false);
			else if(sDatentyp.equals("Eintritt")||sDatentyp.equals("Austritt")||sDatentyp.equals("EinAustritt")||sDatentyp.equals("Datum")||sDatentyp.equals("CalcDatum"))
				TabSpalten.putInhalt("Component",new Datum(g,Static.beiLeer(sFormat,"dd/MM/yyyy")),false);
			else if(sDatentyp.equals("StringKurz")||sDatentyp.equals("StringSehrKurz")||sDatentyp.equals("String60")||sDatentyp.equals("StringLang")||sDatentyp.equals("StringKurzOhne")||sDatentyp.equals("Stringx"))
			{
				iMaxLength = iMaxLength<=0?sDatentyp.equals("StringKurz")||sDatentyp.equals("StringKurzOhne")?30:sDatentyp.equals("StringSehrKurz")?10:sDatentyp.equals("String60")?60:sDatentyp.equals("StringLang")||sDatentyp.equals("Stringx")?100:1:iMaxLength;

                                JComponent Edt;
				//if((Transact.iInfos&Transact.FILL)==0 || sDatentyp.equals("StringLang")||sDatentyp.equals("StringKurzOhne")||sDatentyp.equals("Stringx"))
                                //{
                                  Edt=new Text("",iMaxLength);
                                  //Cbo.setEditable(iMaxLength);
                                /*}
				else
                                {
                                  Edt = new ComboSort(g);
                                  ((ComboSort)Edt).fillDaten(TabSpalten.getI("Kennung2"), iMaxLength);
                                }*/
				TabSpalten.putInhalt("Component",Edt,false);
			}
			else if(sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") || sDatentyp.equals("Doku") || sDatentyp.equals("FixDoku"))
                        {
                          FileEingabe Edt=new FileEingabe(g, !sDatentyp.equals("Pfad"), false,TabSpalten.getI("Kennung2"),(TabSpalten.getI("bits2")&Abfrage.cstSpNotSave)==0 && bEdit);
                          Edt.setFTP(sDatentyp.equals("FixDoku"));
                          Edt.setDB(sDatentyp.equals("Doku"));
                          TabSpalten.putInhalt("Component", Edt, false);
                        }
			else if(sDatentyp.equals("WWW"))
				TabSpalten.putInhalt("Component",new WWW(g),false);
			else if(sDatentyp.equals("Text")||sDatentyp.equals("Memo"))
                        {
                          //MemoF Dlg=new MemoF((JFrame)Fom.thisFrame,TabSpalten.getS("Bezeichnung"),g);
                          //AUTextArea Txt=new AUTextArea(g, 3);
                          //Dlg.getContentPane().add("Center",Txt);
                          //Dlg.setSize(400,400);
                          //Vector Vec2=new Vector();
                          //Vec2.addElement(Dlg);
                          //Vec2.addElement(Txt);
                          Object Edt=null;
                          if ((TabSpalten.getI("bits3") & Abfrage.cstHtml)>0)
                            Edt = new AUEkitCore(g,Fom.thisJFrame(),7);
                          else
                            Edt = new MemoF((JFrame)Fom.thisFrame,TabSpalten.getS("Bezeichnung"),g,true,bEdit,iMaxLength);

                          TabSpalten.putInhalt("Component",Edt , false);
                        }
			else if(sDatentyp.equals("Hierarchie")||sDatentyp.equals("BewHierarchie"))
                          if ((TabSpalten.getI("bits2")&Abfrage.cstStt)>0)
                            TabSpalten.putInhalt("Component",new AUComboList(null,Fom.getBegriff(),iAIC_Begriff_Abfrage,TabSpalten.getI("Kennung2"), TabSpalten.getI("Stt"), g,
                                /*TabColumn.posInhalt((Object)("AUTable" + iAIC_Begriff_Abfrage), TabSpalten.getInhalt("nummer")) &&
                                TabColumn.getI("int3") >= 0 ? TabColumn.getI("int3") :*/ TabSpalten.getI("Filter"),
                                (TabSpalten.getI("bits") & (Global.cstKeinAutoDate * 0x10000 + Abfrage.cstKeinAutoDate)) == 0,
                                (TabSpalten.getI("bits") & (Global.cstAlways * 0x10000)) == 0,true,(TabSpalten.getI("bits3") & Abfrage.cstNoFav)>0),false);
                          else
                            TabSpalten.putInhalt("Component",new HierarchieEingabe(Fom.getBegriff(),TabSpalten.getI("Stt"),iAIC_Stammtyp,TabSpalten.getI("Kennung2"),g),false);
			else if(sDatentyp.equals("Bild"))
			{
				BildEingabe BE = new BildEingabe(null,g);
				BE.activateEvent();
				TabSpalten.putInhalt("Component",BE,false);
			}

		}

		if(sDatentyp.equals("BewDatum"))
		{
			iSpalteGueltigkeit = iSpaltenAnz;
			//bGueltigHH = sFormat.indexOf("HH")>=0;
		}

		if(!sTabTyp.endsWith("erf") && TabColumn.posInhalt((Object)("AUTable"+iAIC_Begriff_Abfrage),TabSpalten.getInhalt("nummer")) && TabColumn.getI("int2")>1)
		{
			if ((TabSpalten.getI("bits") & Abfrage.cstUnsichtbar)>0 || sDatentyp.equals("Farbe"))
			{
				table.setPixelWidth(iSpaltenAnz, 0);
				g.fixtestInfo(TabSpalten.getS("Bezeichnung")+": setzte Spaltenbreite auf 0 statt "+TabColumn.getI("int2")+", da unsichtbar");
			}
			else
				table.setPixelWidth(iSpaltenAnz,TabColumn.getI("int2"));
		}
		else
		{
			int iL=Abfrage.getLaengeB(TabSpalten);
                        //g.progInfo("Spalte "+iSpaltenAnz+":"+iL);
                        boolean bOk=true;
			if ((TabSpalten.getI("bits") & Abfrage.cstUnsichtbar)>0 || sDatentyp.equals("Farbe"))
                        {
                          table.setPixelWidth(iSpaltenAnz, 0);
                          bOk=false;
                        }
			else if (iL>0)
                          table.setPixelWidth(iSpaltenAnz,iL*Fom.iFF/100);
			else if(iMaxLength>0 && (sDatentyp.startsWith("String")))
                          table.setPixelWidth(iSpaltenAnz,iMaxLength*8*Fom.iFF/100);
                        else
                          table.setPixelWidth(iSpaltenAnz,-999);
                        if (iXmin<0 && bOk)
                        {
                          iXmin = iSpaltenAnz;
                          //g.fixtestInfo("iXmin="+iXmin);
                        }
		}
                int iSBits=TabSpalten.getI("Bits");
		if((iSBits&Abfrage.cstSperre)>0)
		{
			iRowLabel++;
			table.setFrozenColumns(iRowLabel);
		}
                String s=Static.cutString(TabSpalten.getS("Bezeichnung"));
                if (s.indexOf("\n")>-1)
                  bOne=false;
		table.setCell(-1,iSpaltenAnz,s);
                //Font font=(iSBits&Abfrage.cstEditierbar)==0?g.fontBezeichnung:(iSBits&0x10000*g.cstAlways)>0?g.fontEF_Bez2:g.fontEF_Bez;
                Color Col=(iSBits&Abfrage.cstEditierbar)==0?Global.ColBezeichnung:(iSBits&0x10000*Global.cstAlways)>0?Global.ColEF_Bez2:Static.bND ? Global.ColTab_Bez:Global.ColEF_Bez;
		table.setFont(-1,iSpaltenAnz,fontTitel);
                table.setForeground(-1,iSpaltenAnz,TabSpalten.getI("Farbe")>0 ? g.setColor(Color.BLACK,TabSpalten.getI("Farbe")):Col);
                if (Global.bInfoJeder)
                {
                  int iPosE=g.TabEigenschaften.getPos("AIC",Eig(sDatentyp));
                  g.testInfo("iPosE="+iPosE+(iPosE<0?" --":":"+g.TabEigenschaften.getS(iPosE, "Bezeichnung")));
                  if (iPosE>=0)
                  {
                    java.awt.Color Col2=g.getColLizEig(iPosE);
                    if (Col2 != null)
                      table.setBackground(-1,iSpaltenAnz,Col2);
                    //g.testInfo("Lizenzierbar:" + g.TabEigenschaften.getS(iPosE, "Bezeichnung"));
                  }
                }
		//int iAlign=g.getAlignment(sDatentyp,TabSpalten.getI("Ausrichtung"));
		//table.setAlignment(-1,iSpaltenAnz,iAlign == BWTEnum.MIDDLELEFT ? JCTblEnum.TOPLEFT:iAlign == BWTEnum.MIDDLECENTER ? JCTblEnum.TOPCENTER:JCTblEnum.TOPRIGHT);
		table.setAlignment(-1,iSpaltenAnz,BWTEnum.TOPCENTER);

		TabSpalten.moveNext();
	}
//	g.fixtestError(sDefBez+": iSpalteZone="+iSpalteZone+", iSpalteDST="+iSpalteDST+", iSpalteGueltig2="+iSpalteGueltig2);
        table.setPixelHeight(-1,(g.Mojave() ? (bOne? 22:36):(bOne ? 16:30))/*38*/*g.getFontFaktor()/100+4);
	table.setNumColumns(TabSpalten.getVecSpalte("Bezeichnung").size()+(g.Debug()?1:0));
        bCalc=iSpalteModell>-1;
        VecWidth=new Vector<Integer>();
        for (TabSpalten.moveFirst();!TabSpalten.out();TabSpalten.moveNext())
          VecWidth.addElement(table.getPixelWidth(TabSpalten.getPos()));
        g.progInfo("VecWidth="+VecWidth);
	for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
	{
		Object Com = TabSpalten.getInhalt("Component");
		//String sDatentyp = TabSpalten.getS("Datentyp").trim();
		//g.progInfo("Spaltendatentyp: "+sDatentyp);
		if(Com!=null)
		{
			Vector<Component> VecCom = new Vector<Component>();

			if(Com instanceof AUComboList)
                          if ((TabSpalten.getI("bits") & Abfrage.cstShow)==Abfrage.cstCombo2)
                            VecCom.addElement(((AUComboList)Com).getComboBox().getComboSortEditor());
                          else
                            VecCom.addElement(((AUComboList)Com).getComboBox());//.getComboSortEditor());
			else if(Com instanceof Datum)
				VecCom.addElement(((Datum)Com).getDatumEditor());
			else if(Com instanceof MassEingabe)
				VecCom.addElement(((MassEingabe)Com).getMassEditor());
			else if(Com instanceof WaehrungsEingabe)
				VecCom.addElement(((WaehrungsEingabe)Com).getWaehrungsEditor());
                        else if(Com instanceof Zahl2)
				VecCom.addElement(((Zahl2)Com).Edt);
			else if(Com instanceof ComboSort)
				VecCom.addElement((ComboSort)Com);//.getComboSortEditor());
			else if(Com instanceof FileEingabe)
				VecCom.addElement(((FileEingabe)Com).getFileEditor());
			else if(Com instanceof HierarchieEingabe)
				VecCom.addElement(((HierarchieEingabe)Com).Cbo2);//.getComboStammEditor());
			else if(Com instanceof WWW)
				VecCom.addElement(((WWW)Com).getEditor());
			else if(Com instanceof EMail)
				VecCom.addElement(((EMail)Com).getEditor());
			else if(Com instanceof VonBisEingabe)
			{
				VecCom.addElement(((VonBisEingabe)Com).getVonEditor());
				VecCom.addElement(((VonBisEingabe)Com).getBisEditor());
				VecCom.addElement(((VonBisEingabe)Com).getDauerEditor());
			}
			else
				VecCom.addElement(Com instanceof MemoF || Com instanceof AUEkitCore ? null:(Component)Com);

			for(int i=0;i<VecCom.size();i++)
			{
				if(VecCom.elementAt(i) != null/* && !(VecCom.elementAt(i) instanceof AUTextArea)*/)
					(VecCom.elementAt(i)).addKeyListener(new KeyListener()
					{
						public void keyPressed(KeyEvent e)
						{}
						public void keyReleased(KeyEvent e)
						{}
						public void keyTyped(KeyEvent e)
						{
							char cKey=e.getKeyChar();
							if(cKey==13 || cKey==10)// || cKey==32)
                                                        {
                                                          e.consume();
                                                          Enter();
                                                        }
						}
					});
			}
		}
	}
        if (TabColumn.posInhalt("Haupt","AUTable" + iAIC_Begriff_Abfrage))
        {
          int i=TabColumn.getI("int4");
          bSichtbar=(i&4)>0;
          bGauge=(i&8)>0;
        }
        setVisible(bSichtbar);
        long lClock=Static.get_ms();
	Fuellen();
        if(bSperre && BtnInfo.isSelected())
          checkZeilen(true);
        g.clockInfo("Füllen1 von "+sDefBez+(Tab==null?"":": "+Tab.getAnzahlElemente(null))+"/"+bSichtbar+"/"+bGauge,lClock,1);
        setVisible(true);
	EnableButtons();

	if(iAutorefresh>0)
	{
		bAutorefresh=false;
		timer = new javax.swing.Timer(iAutorefresh*1000,this);
		timer.start();
	}
        if (popup==null || g.Def())
          addPopup();
}

      private void Enter()
      {
        //g.testInfo("Enter in Cell:" + iMomX + "/" + iMomY);
        if (MnuWeiter.isSelected())
        {
          int y = -1;
          int x = 1;
          do
          {
            y++;
            if (y > 0)x = iOldX * ( -1);
            for (; !table.getEditable(iOldY + y, iOldX + x); x++);
          }
          while (iOldX + x >= iSpalte);
          //g.progInfo(iOldX+"/"+x+"/"+iOldY+"/"+y+"/"+iZeile+"->"+allow_New());
          //if(iOldY+y>=iZeile && allow_New())
          if (y > 0 && allow_New())
            Neu();
          else if (iOldY + y < iZeile)
            EnterCell(iOldY, iOldX, iOldY + y, iOldX + x);
        }
        else
        {
          //table.commitEdit(true);
          //g.testInfo("Enter-> vor EnterCell");
          setSpaltenEdit(false);
          EnterCell(iOldY, iOldX,-1,-1);
          Static.sleep(100);
          //g.testInfo("Enter-> vor traverse");
          table.traverse(iMomY,iMomX,true,true);
          //table.actionPerformed(null);
        }
      }

private void EnterNext()
{
  //g.testInfo("EnterNext:"+bEdit2);
  if (bEdit2)
  {
    setSpaltenEdit(false);
    EnterCell(iOldY, iOldX,-1,-1);
    table.traverse(iMomY,iMomX,true,true);
  }
  else
  {
    setSpaltenEdit(true);
    int iX = 0;
    for (; !table.getEditable(iMomY, iMomX + iX); iX++);
    //g.testInfo("EnterNext:"+(iMomX + iX)+" von "+iSpalte+" / "+iMomY);
    if ((iMomX + iX) < iSpalte)
    {
      //table.traverse(iMomY,iMomX + iX,true,true);
      iMomX+=iX;
      EnterCell(iOldY, iOldX, iMomY, iMomX);
    }
    else
      setSpaltenEdit(false);
  }
  //g.fixInfo("table.keyTyped:"+((int)cKey)+"/"+iMomX+"/"+iMomY);
  //g.fixInfo("Zeile/Spalte="+table.getCells()+"/"+table.getX()+"/"+table.getY());
}

private void setSpaltenEdit(boolean b)
{
  //if (true)
  //  return; // !!!
  bEdit2=b;
  for(int iZ=0;iZ<iZeile;iZ++)
  {
    int iSpaltenAnz = 0;
    for (TabSpalten.moveFirst(); !TabSpalten.eof(); TabSpalten.moveNext())
    {
      String sDatentyp = TabSpalten.getS("Datentyp");
      if(!b || (Fom.ReadOnly() || (TabSpalten.getI("bits3")&Abfrage.cstAuto)>0 || (TabSpalten.getI("bits")&Abfrage.cstEditierbar)==0) && !sDatentyp.equals("Text") && !sDatentyp.equals("Memo") && !sDatentyp.equals("Doku") || sDatentyp.equals("long binary") ||
         sDatentyp.equals("Kennung")||sDatentyp.equals("Bezeichnung")||sDatentyp.equals("Eintritt")||sDatentyp.equals("Austritt")||sDatentyp.equals("EinAustritt")||
         sDatentyp.equals("Stichtag")||sDatentyp.equals("Trennzeichen")||sDatentyp.equals("CalcBezeichnung")||sDatentyp.equals("Timestamp")||
         sDatentyp.equals("entfernt")||sDatentyp.equals("SysAic")||sDatentyp.equals("Aic")||sDatentyp.endsWith("Benutzer")||sDatentyp.equals("Anlage")||
         sDatentyp.equals("Mandant")||sDatentyp.equals("Einheiten")||sDatentyp.equals("Vorgaenger") || TabAenderungen.posInhalt("Zeile",iZ) && TabAenderungen.getS("Aenderungsart").equals("Loeschen"))
        table.setEditable(iZ,iSpaltenAnz,false);
      else
      {
        //g.testInfo("setEditable:"+sDatentyp);
        table.setEditable(iZ, iSpaltenAnz, true);
      }
      iSpaltenAnz++;
    }
  }
}

private void NeueGruppe(String sGold)//,Vector<Integer> VecZ)
{
  if (TabGruppen==null)
    TabGruppen=new Tabellenspeicher(g,new String[] {"Gruppe", "Summe"/*, "Zeilen"*/});
  TabGruppen.addInhalt("Gruppe",sGold);
  TabGruppen.addInhalt("Summe",iZeile);
  //TabGruppen.addInhalt("Zeilen",VecZ);
  //g.fixtestInfo(sGold+":"+Sort.gets2(VecZ,',')+"-> Summe in "+iZeile);
  VecSperre.addElement(iZeile);
  table.setCell(iZeile,0,sGold);
  for (int i2 = -1; i2 < table.getNumColumns(); i2++)
    table.setBackground(iZeile, i2, Global.ColZSum);
  iZeile++;
  //VecZ.removeAllElements();
}

private void Fuellen()
{
  //g.fixtestInfo("Fuellen");
	Vector VecSpalten = TabSpalten.getVecSpalte("Kennung");
        VecSperre=new Vector<Integer>();
	iSpalte = VecSpalten.size();
	iZeile=0;
        if (iMomY>-1)
        {
          g.progInfo("iMomY " + iMomY + " -> -1");
          iMomY = -1;
        }
	//table.disable();
	table.clearCells();
	int iZH= (g.Mojave() ? 20:16)*g.getFontFaktor()/100;
	//table.setNumRows(0);
	//table.getCells().clear();
	//System.gc();

	//table.revalidate();
	//if (bGauge)
	//  return;
        bOkStamm=bBew && iAIC_Stamm<0 || !bBew && (bKeinStm || iAIC_Stamm>0);
        setEnabled(bOkStamm);
  	  if (BtnSEp!=null) BtnSEp.setEnabled(false);
  	  if (BtnSEm!=null) BtnSEm.setEnabled(false);
  	  if (BtnSEe!=null) BtnSEe.setEnabled(false);
  	  if (BtnSEl!=null) BtnSEl.setEnabled(false);
  	  if (BtnSEr!=null) BtnSEr.setEnabled(false);
	if((bKeinStm || iAIC_Stamm>0) && Tab!=null)
	{
          int iGesamt=Tab.getAnzahlElemente(null);
          //if (iGesamt==0)
          //  bFirst2=false;
          //long lClock = Static.get_ms();
          String sBez=g.getBegBez(iAIC_Begriff_Abfrage);
          Gauge gauge=iGesamt>=iGaugeAb || bGauge ? new Gauge(0,iGesamt,sBez,g,false):null;
          if (gauge != null)
            Static.bRefreshStop=true;
          String sGold=null;
          if (TabGruppen != null)
            TabGruppen.clearAll();
          //Vector<Integer> VecZ=null;
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			TabSpalten.moveFirst();
                        int iZeilen=1;
                        if (iEigGruppe>0)
                        {
                          String sGnew=sFG==null ? Tab.getS("E" + iEigGruppe):""+new Zeit(Tab.getD("E" + iEigGruppe),sFG);
                          //if (VecZ==null) VecZ=new Vector<Integer>();
                          if (sGold==null)  sGold=sGnew;
                          else if (!sGold.equals(sGnew))
                          {
                            NeueGruppe(sGold);//,VecZ);
                            sGold = sGnew;
                            //VecZ=new Vector<Integer>();
                          }
                          //VecZ.addElement(iZeile);
                        }
			for(int iSpaltenAnz=0;!TabSpalten.eof();iSpaltenAnz++)
			{
				String sDatentyp = TabSpalten.getS("Datentyp");
				String sSpaltenname = TabSpalten.getS("Kennung2").trim();
				String sFormat = TabSpalten.getS("Format");
				/*if(Fom.ReadOnly() || (TabSpalten.getI("bits")&Abfrage.cstEditierbar)==0 || sDatentyp.equals("long binary") ||
					sDatentyp.equals("Kennung")||sDatentyp.equals("Bezeichnung")||sDatentyp.equals("Eintritt")||sDatentyp.equals("Austritt")||
					sDatentyp.equals("Stichtag")||sDatentyp.equals("Trennzeichen")||sDatentyp.equals("CalcBezeichnung")||sDatentyp.equals("Timestamp")||
					sDatentyp.equals("entfernt")||sDatentyp.equals("SysAic")||sDatentyp.equals("Aic")||sDatentyp.endsWith("Benutzer")||sDatentyp.equals("Anlage")||
					sDatentyp.equals("Mandant")||sDatentyp.equals("Einheiten")||sDatentyp.equals("Vorgaenger"))
					table.setEditable(iZeile,iSpaltenAnz,false);
				else
					table.setEditable(iZeile,iSpaltenAnz,true);*/

				Object oWert = null;
				if(iArt==Standard)
					oWert=Tab.getInhalt(sSpaltenname);
				/*
				if(sDatentyp.equals("bit"))
					oWert = oWert==null || !((Boolean)oWert).booleanValue() ? g.getBegriff("Show","Nein") : g.getBegriff("Show","Ja");
				else if(sDatentyp.equals("date"))
					oWert = Static.DateToString((Date)oWert);
				else if(sDatentyp.equals("timestamp"))
					oWert = oWert!=null ? ((Date)oWert).toString() : "";
				//////////////////////////////
				//Muß noch überarbeitet werden
				//////////////////////////////
				//else if(sDatentyp.equals("time"))
				//	oWert = oWert!=null ? ((Date)oWert).getHours()+":"+((Date)oWert).getMinutes()+":"+((Date)oWert).getSeconds() : "";
				//////////////////////////////
				else if(sDatentyp.equals("double"))
					oWert = oWert!=null ? ((Double)oWert).toString() : "0.0";
				else if(sDatentyp.equals("float"))
					oWert = oWert!=null ? ((Float)oWert).toString() : "0.0";
				else if(sDatentyp.equals("integer"))
					oWert = oWert!=null ? oWert: new Integer(0);*/
				if(TabSpalten.getInhalt("Component") instanceof AUComboList || sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe"))
				{
					//((AUComboList)TabSpalten.getInhalt("Component")).getComboBox().setSelectedAIC(Tab.getI("V"+sSpaltenname));
                                        oWert=new Combo(Tab.getS("E"+sSpaltenname),Tab.getI("V"+sSpaltenname),"",0);
					//oWert = ((AUComboList)TabSpalten.getInhalt("Component")).getComboBox().getItem(Tab.getI("V"+sSpaltenname));
				}
				else if(sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular")
				    || sDatentyp.equals("BewLand") || sDatentyp.equals("BewHoliday") || sDatentyp.equals("BewBG") || sDatentyp.equals("BewUser"))
                                  oWert=new Combo(Tab.getS("E"+sSpaltenname),Tab.getI("V"+sSpaltenname),"",0);
					//oWert = ((ComboSort)TabSpalten.getInhalt("Component")).getItem(Tab.getI("V"+sSpaltenname));
				else if(sDatentyp.equals("BewVon_Bis"))
					oWert=new VonBis(g,Tab.getInhalt("V"+sSpaltenname),Tab.getInhalt("B"+sSpaltenname),Tab.getF("D"+sSpaltenname),Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"),Abfrage.getLaenge(TabSpalten),g.getFaktor(TabSpalten.getI("Stamm")));
				else if(sDatentyp.equals("GPS"))
					oWert=new GPS(Tab,"E"+sSpaltenname);
				else if(sDatentyp.equals("von_bis")||sDatentyp.equals("Auto_von_bis"))
					oWert=new VonBis(g,Tab.getInhalt("V"+sSpaltenname),Tab.getInhalt("B"+sSpaltenname),Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"));
				else if(sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcDauer"))
				{
					//g.progInfo("Mass: "+TabSpalten.getI("Stamm")+" : "+Tab.getInhalt("E"+sSpaltenname)+" : "+sFormat);
					oWert=new Mass(g,g.getMass(TabSpalten.getI("Stamm")==0?Tab.getI("V"+sSpaltenname):TabSpalten.getI("Stamm"),(TabSpalten.getI("bits")&Abfrage.cstKeineEinheit)>0),Tab.getInhalt("E"+sSpaltenname),sFormat);
				}
				else if(sDatentyp.equals("Einheiten"))
				{
					Vector<Object> Vec = new Vector<Object>();
					Vec.addElement(new Double(1));
					Vec.addElement(Tab.getS("V"+sSpaltenname));
					Vec.addElement(new Integer(0));
					oWert=new Mass(g,Vec,Tab.getInhalt("E"+sSpaltenname),sFormat);
				}
				else if(sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
				{
					oWert=new Waehrung(g.getVecWaehrung2((TabSpalten.getI("Bits")&Abfrage.cstKeineEinheit)>0,Tab.getI("V"+sSpaltenname)),Tab.getInhalt("E"+sSpaltenname),sFormat);
				}
				else if(sDatentyp.equals("BewBoolean")||sDatentyp.equals("Boolean")||sDatentyp.equals("Vorgaenger"))
					oWert=new JaNein(Tab.getB("E"+sSpaltenname));
                                else if(sDatentyp.endsWith("Bool3"))
                                  oWert=new Combo(Tab.getS("E"+sSpaltenname),Tab.getI("V"+sSpaltenname),!Tab.isNull("V"+sSpaltenname));
				else if(sDatentyp.equals("BewDatum"))
				{
					//iSpalteGueltigkeit = iSpaltenAnz;
					Date date = (Date)Tab.getInhalt("E"+sSpaltenname);
					oWert=new Zeit(date,Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"));

					for(int i=0;i<VecSpalten.size();i++)
						table.setBackground(iZeile,i,colorBackground);
					if(date!=null && (A.iBits&Abfrage.cstFeiertage)>0)
					{
						//g.progInfo("Feiertag: "+date);
						DateWOD dw = new DateWOD(date);
                                                int iD=dw.getDay();
                                                Color Col= !g.Feiertag(dw,iAIC_Stamm).equals("") ? Global.ColHoliday:
                                                    iD==Calendar.MONDAY ? Global.ColMo: iD==Calendar.TUESDAY ? Global.ColDi:iD==Calendar.WEDNESDAY ? Global.ColMi:iD==Calendar.THURSDAY ? Global.ColDo:
                                                    iD==Calendar.FRIDAY ? Global.ColFr:iD==Calendar.SATURDAY ? Global.ColSa:iD==Calendar.SUNDAY ? Global.ColSo:null;
						if(Col != null)
							for(int i=0;i<VecSpalten.size();i++)
								table.setBackground(iZeile,i,Col);
					}

				}
				else if(sDatentyp.equals("Farbe"))
				{
                                  int iC=Tab.getI("E"+sSpaltenname);
				  Color Col=Tab.isNull("E"+sSpaltenname)?null:new Color(iC);
				  //if(Col != null)
					for(int i=0;i<VecSpalten.size();i++)
						table.setBackground(iZeile,i,Col != null ? Col:Global.ColTable);
                                  oWert=Col!=null?new Integer(iC):null;
				}
				else if(sDatentyp.equals("BewDatum2")||sDatentyp.equals("Timestamp")||sDatentyp.equals("entfernt")||sDatentyp.equals("Zeit"))
					oWert=new Zeit((Date)Tab.getInhalt("E"+sSpaltenname),Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"));
				else if(sDatentyp.equals("Eintritt")||sDatentyp.equals("Austritt")||sDatentyp.equals("EinAustritt")||sDatentyp.equals("Datum")||sDatentyp.equals("CalcDatum"))
					oWert=new Zeit((Date)Tab.getInhalt("E"+sSpaltenname),Static.beiLeer(sFormat,"dd/MM/yyyy"));
				else if(sDatentyp.startsWith("BewZahl")||sDatentyp.equals("BewDauer")||sDatentyp.equals("Double")||sDatentyp.equals("CalcField"))
				{
                                  //g.progInfo(sDatentyp+":"+sSpaltenname+"/"+Static.className(Tab.getInhalt("E"+sSpaltenname)));
					oWert=new Zahl1(Tab.getF("E"+sSpaltenname),sFormat);
				}
				else if(sDatentyp.equals("Hierarchie")||sDatentyp.equals("BewHierarchie"))
				{
					int iStt = Tab.getI("W"+sSpaltenname);
					int iStamm = Tab.getI("V"+sSpaltenname);
					//int iPosS=g.TabStammtypen.getPos("AIC",iStt);
					oWert=new HierarchieAnzeige(iStamm!=0?/*g.TabStammtypen.getS("Bezeichnung")+" - "+*/Tab.getS("E"+sSpaltenname):"",iStt,iStamm);
				}
				else if(sDatentyp.equals("Integer"))
				{
					Double d = new Double(Tab.getF("E"+sSpaltenname));
					oWert=d!=null?new Integer(d.intValue()):null;
				}
				else if(sDatentyp.equals("SysAic")||sDatentyp.equals("Aic") || sDatentyp.equals("BewBew"))
					oWert = Tab.getInt("E"+sSpaltenname);
                else if(sDatentyp.equals("Passwort"))
                    oWert = new PW1(Tab.getS("E"+sSpaltenname));
				else if(sDatentyp.equals("StringKurz")||sDatentyp.equals("StringSehrKurz")||sDatentyp.equals("String60")||sDatentyp.equals("StringLang")||sDatentyp.equals("Stringx")||
						sDatentyp.equals("StringKurzOhne")||sDatentyp.equals("Pfad")||sDatentyp.equals("Filename")||sDatentyp.equals("Doku")||sDatentyp.equals("FixDoku")||
                                                sDatentyp.equals("WWW")||sDatentyp.equals("E-Mail")||sDatentyp.equals("Bild")||sDatentyp.equals("Bezeichnung")||sDatentyp.equals("CalcBezeichnung")||
						sDatentyp.equals("Kennung") || sDatentyp.endsWith("Benutzer") || sDatentyp.equals("Anlage") || sDatentyp.equals("Mandant"))
					oWert=Tab.getM("E"+sSpaltenname);
				else if(sDatentyp.equals("Text")||sDatentyp.equals("Memo"))
				{
					//oWert=new Memo1(Tab.getM("E"+sSpaltenname),Abfrage.getLaenge(TabSpalten));
                                        /*if(Fom.ReadOnly() || (TabSpalten.getI("bits")&Abfrage.cstEditierbar)==0)//TabSpalten.getI("Locked")>0)
                                        {
                                          oWert = new AUTextArea(g, 0);
                                          ((AUTextArea)oWert).setText(Tab.getM("E" + sSpaltenname));
                                          ((AUTextArea)oWert).setEditable(false);
                                        }
                                        else*/ // !!! Memo weg
                                          oWert=Tab.getM("E" + sSpaltenname);
					//oWert=new Memo1(Tab.getM("E"+sSpaltenname),-1);
                                        //if (!Check.isNull(oWert) && oWert.toString().length()>15)
                                        if (oWert != null && oWert instanceof String && ((String)oWert).startsWith("<html>"))
                                        {
                                          oWert=new Html((String)oWert,"\n");
                                          iZeilen=4;
                                        }
                                        else if (!Check.isNull(oWert) && oWert.toString().indexOf("\n")>0)
                                        {
                                          int iZmom=oWert.toString().split("\n").length;
                                          if (iZmom>iZeilen)
                                            iZeilen=iZmom>4 ? 4:iZmom;
                                        }
                                        //  table.setPixelHeight(iZeile,3*16*g.getFontFaktor()/100+4);
                                          //table.setPixelHeight(iZeile,((AUTextArea)oWert).getHeight());
				}
				else if(sDatentyp.equals("Mehrfach"))
					oWert=Tab.getS("A"+sSpaltenname);				
				else
					Static.printError("AUTable.Fuellen(): Der Datentyp <"+sDatentyp+"> wird zur Zeit nicht unterstützt!");

				int iAlign=g.getAlignment(sDatentyp,TabSpalten.getI("Ausrichtung"));
				table.setAlignment(iZeile,iSpaltenAnz,iAlign == BWTEnum.TOPLEFT || iAlign == BWTEnum.MIDDLELEFT ? JCTblEnum.TOPLEFT:
                                                   iAlign == BWTEnum.TOPCENTER || iAlign == BWTEnum.MIDDLECENTER ? JCTblEnum.TOPCENTER:JCTblEnum.TOPRIGHT);
                                //g.progInfo("setCell "+iZeile+"/"+iSpaltenAnz+":"+oWert);
                                /*if (oWert instanceof AUTextArea)
                                  table.setCell(iZeile,iSpaltenAnz,((AUTextArea)oWert).getValue());*/ // !!! Memo weg
                                if (((TabSpalten.getI("Bits")&Abfrage.cstBild)>0 || TabSpalten.getI("Anzeige")>0) && (TabSpalten.getI("Bits")&Abfrage.cstEditierbar)==0)
                                  oWert=A.TabToScreen(g,TabSpalten.getS("Kennung"),Tab,TabSpalten,false);
				table.setCell(iZeile,iSpaltenAnz,oWert);
                                //g.progInfo("getCell "+iZeile+"/"+iSpaltenAnz+":"+table.getCell(iZeile,iSpaltenAnz));
				//table.setForeground(iZeile,iSpaltenAnz,Color.black);
				Font font=g.getTabFont();
				if (table.getFont(iZeile, iSpaltenAnz) != font)
					table.setFont(iZeile,iSpaltenAnz,font);
				if (table.getForeground(iZeile, iSpaltenAnz) != Global.ColStandard)
				  table.setForeground(iZeile,iSpaltenAnz,Global.ColStandard);
                                if ((TabSpalten.getI("bits2")&Abfrage.cstNegativRot)>0 && Sort.getf(oWert)<0)
                                  table.setForeground(iZeile,iSpaltenAnz,Color.RED);
                                if (TabSpalten.getI("Farbe")>0)
                                  table.setForeground(iZeile,iSpaltenAnz,g.setColor(Color.BLACK,TabSpalten.getI("Farbe")));
                                TabSpalten.moveNext();
			}
                        if (Tab.exists("pro_aic_protokoll") && !Tab.isNull("pro_aic_protokoll"))
                          for(int i=0;i<VecSpalten.size();i++)
                            table.setForeground(iZeile,i,Global.ColLoeschen);

			if(iArt==Stamm)
				table.setCell(iZeile,VecSpalten.size(),Tab.getInhalt("AIC_Stamm"));
			else if(iArt==Bewegung)
				table.setCell(iZeile,iSpalte,Tab.getInhalt("AIC_Bew_Pool"));
                        table.setEditable(iZeile,VecSpalten.size(),false);
                        //g.testInfo("setPixelHeight "+iZeile+";"+(iZeilen*16*g.getFontFaktor()/100+4));
                        //if (iZeilen>1)
                          table.setPixelHeight(iZeile,iZeilen*iZH+4);
                        //if (iZeile%3==1)
                        //  VecSperre.addElement(iZeile);
                        iZeile++;
                        //g.progInfo("Zeile "+iZeile+" gefüllt");
			/*if(iZeile%100 == 0)
			{
				g.printInfo("iZeile: "+iZeile+" - FreeMemory: "+Runtime.getRuntime().freeMemory()+" - TotalMemory: "+Runtime.getRuntime().totalMemory());
				System.gc();
			}*/
                        if (gauge != null && ((iZeile%10)==0 || iZeile==iGesamt))
                        {
                          //Static.sleep(10);
                          //if (gauge.bEscape)
                          //  Tab.moveLast();
                          gauge.setText(sBez,iZeile);
                          if (!bAlleZeilen && iZeile>=iMaxZeilen && iZeile<iGesamt)
                          {
                            int i=new Message(Message.QUESTION_MESSAGE, null, g).showDialog("Abbruch_zu_viele", new String[] {sBez,""+iGesamt,""+iZeile});
                            if (i == Message.YES_OPTION)
                            {
                              Static.printError("Tabelle " + sBez + "/" + g.getStamm(iAIC_Stamm) + ": Abbruch bei " + iZeile + "/" + iGesamt);
                              Tab.moveLast();
                              gauge.setText("", iGesamt);
                            }
                            else if (i == Message.NO_OPTION)
                              bAlleZeilen=true;
                          }
                        }
		}
                if (iEigGruppe>0)
                {
                  NeueGruppe(sGold);//,VecZ);
                  //TabGruppen.showGrid("TabGruppen");
                }
                setSpaltenEdit(false);
                //if (iGesamt>=iGaugeAb)
                //  g.clockInfo("Füllen "+sDefBez+" ("+iGesamt+" Zeilen)",lClock,1);
	}
	table.setNumRows(iZeile);

	iOldY=-1;
	iOldX=-1;
        //if (VecSperre!=null && VecSperre.size()>0)
        //  g.fixtestInfo("VecSperre="+VecSperre);
	Ergebnisart();
        bAlleZeilen=false;
}

private void Ergebnisart()
{
	for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
	{
		if(TabSpalten.getI("Ergebnisart")>0)
			makeErgebnisart(TabSpalten.getPos());
	}
}

private void makeErgebnisart(int iSpalte)
{
	TabSpalten.push();
	TabSpalten.setPos(iSpalte);
	String sDatentyp = TabSpalten.getS("Datentyp");
	String sBezeichnung = Static.cutString(TabSpalten.getS("Bezeichnung"));
        if (!bOne && sBezeichnung.indexOf("\n")==-1)
          sBezeichnung+="\n ";
	String sFormat = TabSpalten.getS("Format");
	String sErgebnisart = g.TabCodes.getKennung(TabSpalten.getI("Ergebnisart"));
	boolean bBerechnen = !sErgebnisart.equals("count");

	double dSumme=0;
        double dZS=0;
	for(int i=0;bBerechnen && i<iZeile;i++)
	{
		if(!TabAenderungen.posInhalt("Zeile",i) || (!TabAenderungen.getS("Aenderungsart").equals("Loeschen") && !TabAenderungen.getS("Aenderungsart").equals("DelEdit")))
		{
			Object Obj = table.getCell(i,iSpalte);
			//g.progInfo("makeErgebnisart: Datentyp="+sDatentyp+", Class="+Static.className(Obj)+"/"+i+"/"+iSpalte);
			if(sErgebnisart.equals("sum") && Obj!=null)
			{
                          double d=Obj instanceof VonBis ? ((VonBis)Obj).getDauer():Obj instanceof String ? 0:Sort.getf(Obj);
                          if (TabGruppen!=null && TabGruppen.posInhalt("Summe",i))
                          {
                            //g.fixtestInfo("Summenzeile "+i+" gefunden:"+dZS+" ["+sFormat+"]");
                            table.setCell(i,iSpalte,SumToString(sErgebnisart,sDatentyp,dZS,sFormat));
                            table.setFont(i,iSpalte,fontTitel);
                            table.setForeground(i,iSpalte,dZS>0?Color.BLACK:dZS<0?Color.RED:Global.ColTable);
                            table.setForeground(i,0,Color.BLACK);
                            table.setAlignment(i,iSpalte,JCTblEnum.MIDDLERIGHT);
                            dZS=0;
                          }
                          else
                          {
                            dSumme+=d;
                            dZS += d;
                          }
                          /*if (Obj instanceof VonBis)
                            dSumme+=;
                          else
                            dSumme+=Sort.getf(Obj);*/
				/*if(sDatentyp.equals("Integer"))
					dSumme+=Tabellenspeicher.geti(Obj);//((Integer)Obj).doubleValue();
				else if(sDatentyp.equals("BewZahl")||sDatentyp.equals("Double")||sDatentyp.equals("BewDauer") || sDatentyp.equals("CalcField"))
					dSumme+=Sort.getf(Obj);//((Zahl1)Obj).getValue();
				else if(sDatentyp.endsWith("Waehrung"))
					dSumme+=((Waehrung)Obj).getValue();
				else if(sDatentyp.endsWith("Mass") || sDatentyp.equals("CalcDauer"))
					dSumme+=((Mass)Obj).getValue();
				else if(sDatentyp.equals("BewVon_Bis"))
					dSumme+=((VonBis)Obj).getDauer();*/
			}
			else if(sErgebnisart.equals("avg") && Obj!=null)
			{
				if(sDatentyp.equals("Integer"))
					dSumme+=((Integer)Obj).doubleValue();
				else if(sDatentyp.startsWith("BewZahl")||sDatentyp.equals("Double")||sDatentyp.equals("BewDauer") || sDatentyp.equals("CalcField"))
					dSumme+=((Zahl1)Obj).getValue();
				else if(sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
					dSumme+=((Waehrung)Obj).getValue();
				else if(sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcDauer"))
					dSumme+=((Mass)Obj).getValue();
				else if(sDatentyp.equals("BewVon_Bis"))
					dSumme+=((VonBis)Obj).getDauer();
			}
			else if(sErgebnisart.equals("max") || sErgebnisart.equals("min") && Obj!=null)
			{
				double dWert=0;
				if(sDatentyp.equals("Integer"))
					dWert+=((Integer)Obj).doubleValue();
				else if(sDatentyp.startsWith("BewZahl")||sDatentyp.equals("Double")||sDatentyp.equals("BewDauer") || sDatentyp.equals("CalcField"))
					dWert+=Obj instanceof Zahl1 ?((Zahl1)Obj).getValue():0;
				else if(sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
					dWert+=((Waehrung)Obj).getValue();
				else if(sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcDauer"))
					dWert+=((Mass)Obj).getValue();
				else if(sDatentyp.equals("BewVon_Bis"))
					dWert+=((VonBis)Obj).getDauer();

				dSumme = (sErgebnisart.equals("max")?dSumme<dWert:i==0 || dSumme>dWert)?dWert:dSumme;
			}
		}
	}
        table.setCell(-1,iSpalte,sBezeichnung+"\n"+SumToString(sErgebnisart,sDatentyp,dSumme,sFormat));
	/*if(sErgebnisart.equals("avg"))
	{
		dSumme=dSumme/iZeile;
		table.setCell(-1,iSpalte,sBezeichnung+"\n"+new Zahl1(dSumme,"#.##"));
	}
	else if(sErgebnisart.equals("count"))
		table.setCell(-1,iSpalte,sBezeichnung+"\n"+new Zahl1(iZeile,"#"));
	else if(sDatentyp.equals("Integer") || sDatentyp.equals("Boolean") || sDatentyp.equals("BewBoolean"))
		table.setCell(-1,iSpalte,sBezeichnung+"\n"+new Zahl1(dSumme,"#"));
	else if(sDatentyp.equals("BewVon_Bis"))
		table.setCell(-1,iSpalte,sBezeichnung+"\n"+new Zahl1(dSumme,"#.##"));
	else if(sDatentyp.startsWith("BewZahl")||sDatentyp.equals("Double")||sDatentyp.equals("BewDauer") || sDatentyp.equals("CalcField"))
		table.setCell(-1,iSpalte,sBezeichnung+"\n"+new Zahl1(dSumme,sFormat));
	else if(sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
		table.setCell(-1,iSpalte,sBezeichnung+"\n"+new Waehrung(g.getVecWaehrung((TabSpalten.getI("Bits")&Abfrage.cstKeineEinheit)>0,TabSpalten.getI("Stamm")),new Double(dSumme),sFormat));
	else if(sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcDauer"))
		table.setCell(-1,iSpalte,sBezeichnung+"\n"+new Mass(g.getMass(TabSpalten.getI("Stamm"),(TabSpalten.getI("bits")&Abfrage.cstKeineEinheit)>0),new Double(dSumme),sFormat));*/
        int iH=(g.Mojave() ? (bOne? 34:50):(bOne?30:44))*g.getFontFaktor()/100+4;
        //g.testInfo("Höhe1="+iH+"/"+table.getPixelHeight(-1));
        if (iH != table.getPixelHeight(-1))
        {
          table.setPixelHeight( -1, iH);
          //g.testInfo("Höhe2=" + iH + "/" + table.getPixelHeight( -1));
        }
	TabSpalten.pop();
}

private String SumToString(String sErgebnisart,String sDatentyp,double dSumme,String sFormat)
      {
        if(sErgebnisart.equals("avg"))
        {
                dSumme=dSumme/iZeile;
                return ""+new Zahl1(dSumme,"#.##");
        }
        else if(sErgebnisart.equals("count"))
                return ""+new Zahl1(iZeile,"#");
        else if(sDatentyp.equals("Integer") || sDatentyp.equals("Boolean") || sDatentyp.equals("BewBoolean"))
                return ""+new Zahl1(dSumme,"#");
        else if(sDatentyp.equals("BewVon_Bis"))
                return ""+new Zahl1(dSumme,"#.##");
        else if(sDatentyp.startsWith("BewZahl")||sDatentyp.equals("Double")||sDatentyp.equals("BewDauer") || sDatentyp.equals("CalcField"))
                return ""+new Zahl1(dSumme,sFormat);
        else if(sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
                return ""+new Waehrung(g.getVecWaehrung((TabSpalten.getI("Bits")&Abfrage.cstKeineEinheit)>0,TabSpalten.getI("Stamm")),new Double(dSumme),sFormat);
        else if(sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcDauer"))
                return ""+new Mass(g,g.getMass(TabSpalten.getI("Stamm"),(TabSpalten.getI("bits")&Abfrage.cstKeineEinheit)>0),new Double(dSumme),sFormat);
        else
          return "";
      }

private void EnterCell(int iOldRow, int iOldColumn, int iAktRow, int iAktColumn)
{
//	g.fixtestError("EnterCell "+iOldRow+"/"+iOldColumn+"->"+iAktRow+"/"+iAktColumn);
  //if (true)
  //  return; // !!!
  //if (iAktColumn==-1 && iAktRow==-1 && sAktDatentyp!=null && sAktDatentyp.equals("Text"))
  //  return;
  //g.progInfo("     ------------            -----------            EnterCell:"+sAktDatentyp+"/"+sFormat+"/"+iOldRow+"/"+iOldColumn+"->"+iAktRow+"/"+iAktColumn);
  //g.testInfo("EnterCell:"+iOldRow+"=>"+iAktRow+", "+iOldColumn+"=>"+iAktColumn);
	if(iOldRow>=0 && iOldColumn>=0)
	{
          //g.testInfo("vor checkMoa");

		Object ObjWertNeu = null;
		boolean bAenderung = false;
		//g.progInfo("EnterCell:"+sAktDatentyp+"/"+sFormat+"/"+iAktRow+"/"+iAktColumn);

		if(Com instanceof AUComboList)//sAktDatentyp.equals("BewStamm") || sAktDatentyp.equals("Gruppe"))
		{
                  AUComboList CL=(AUComboList)Com;
			bAenderung=CL.Modified();
			if(bAenderung)
				ObjWertNeu = CL.getComboBox().getSelectedItem();
                        g.progInfo("AUComboList:"+bAenderung+"/"+ObjWertNeu);
                        TabSpalten.posInhalt("Component",CL);
                        if ((TabSpalten.getI("Bits2") & Abfrage.cstSetSync) > 0)
                          g.setSyncStamm(CL.getStt(),CL.getAIC());
		}
		else if(sAktDatentyp.equals("BewModell") || sAktDatentyp.equals("BewDruck") || sAktDatentyp.equals("BewFormular")
		    || sAktDatentyp.equals("BewLand") || sAktDatentyp.equals("BewHoliday") || sAktDatentyp.equals("BewBG") || sAktDatentyp.equals("BewUser"))
		{
			bAenderung=((ComboSort)Com).Modified();
			if(bAenderung)
				ObjWertNeu = ((ComboSort)Com).getSelectedItem();
		}
		else if(sAktDatentyp.equals("BewVon_Bis"))
		{
			bAenderung = ((VonBisEingabe)Com).Modified();
			if(bAenderung)
				ObjWertNeu = new VonBis(g,((VonBisEingabe)Com).getVon(),((VonBisEingabe)Com).getBis(),((VonBisEingabe)Com).getSeconds(),Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"),((VonBisEingabe)Com).getFaktor());
		}
		else if(sAktDatentyp.equals("GPS"))
		{
			bAenderung = ((GPS_Eingabe)Com).Modified();
			if(bAenderung)
				ObjWertNeu = new GPS((GPS_Eingabe)Com);
		}
		else if(sAktDatentyp.equals("von_bis")||sAktDatentyp.equals("Auto_von_bis"))
		{
			bAenderung = ((VonBisEingabe)Com).Modified();
			if(bAenderung)
				ObjWertNeu = new VonBis(g,((VonBisEingabe)Com).getVon(),((VonBisEingabe)Com).getBis(),Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"));
		}
		else if(sAktDatentyp.equals("BewDauer")||sAktDatentyp.startsWith("BewZahl")||sAktDatentyp.equals("Double")||sAktDatentyp.equals("Integer") || sAktDatentyp.equals("CalcField"))
		{
			/*int iPosE=g.TabEigenschaften.getPos("AIC",Eig(sAktDatentyp));
                        if (iPosE<0)
                        {
                          g.fixInfo("Eig für "+sAktDatentyp+" nicht gefunden:"+Eig(sAktDatentyp));
                        }
			else if(Check.inRange((Zahl2)Com,g.TabEigenschaften.getInt(iPosE,"min"),g.TabEigenschaften.getInt(iPosE,"max")))
			{*/
				if(sAktDatentyp.equals("Integer"))
				{
					ObjWertNeu = ((Zahl2)Com).Edt.getInteger();
					bAenderung=!Static.Gleich(ObjWert,ObjWertNeu);
				}
				else
				{
					bAenderung = ((Zahl2)Com).Edt.Modified();
					if(bAenderung)
						ObjWertNeu = new Zahl1(((Zahl2)Com).Edt.getDouble(),sFormat);
				}
			/*}
			else
			{
				bAenderung=false;
				new Message(Message.WARNING_MESSAGE,null,g).showDialog("Range",new String[] {TabSpalten.getS("Bezeichnung"),sAktDatentyp,g.TabEigenschaften.getS(iPosE,"min"),g.TabEigenschaften.getS(iPosE,"max")});
				iAktRow=iOldRow;
				iAktColumn = iOldColumn;
			}*/
		}
		else if(sAktDatentyp.endsWith("Mass") || sAktDatentyp.equals("BewMass2") || sAktDatentyp.equals("CalcDauer"))
		{
			bAenderung=((MassEingabe)Com).Modified();
			if(bAenderung)
                        {
                          MassEingabe ME=(MassEingabe)Com;
                          //g.fixInfo("vor Pos:"+TabSpalten.getS("Bezeichnung")+"/"+TabSpalten.getS("Kennung")+":"+TabSpalten.getPos());
                          TabSpalten.posInhalt("Component",Com);
                          //g.fixInfo("nach Pos:"+TabSpalten.getS("Bezeichnung")+"/"+TabSpalten.getS("Kennung")+"/"+sAktDatentyp+":"+ME.getAbsValue()+"/"+ME.getMass());
                          ObjWertNeu = new Mass(g,g.getMass(ME.getMass(), (TabSpalten.getI("Bits")&Abfrage.cstKeineEinheit)>0), new Double(ME.getAbsValue()), sFormat);
                        }
		}
		else if(sAktDatentyp.endsWith("Waehrung") || sAktDatentyp.equals("BewWaehrung2"))
		{
			bAenderung=((WaehrungsEingabe)Com).Modified();
                        //TabSpalten.showGrid("TabSpalten");
                        //g.progInfo("Waehrung:"+bAenderung+"/"+sFormat+"/"+Com);
			if(bAenderung)
                        {
                          WaehrungsEingabe WE=(WaehrungsEingabe)Com;
                          TabSpalten.posInhalt("Component",Com);
                          ObjWertNeu = new Waehrung(g.getVecWaehrung((TabSpalten.getI("Bits")&Abfrage.cstKeineEinheit)>0,WE.getWaehrung()),new Double(WE.getAbsValue()),sFormat);
                        }
		}
		else if(sAktDatentyp.equals("Text")||sAktDatentyp.equals("Memo"))
		{
                  bAenderung=Com!=null && (Com instanceof MemoF && ((MemoF)Com).Txt.Modified() && ((MemoF)Com).bOk || Com instanceof AUEkitCore && ((AUEkitCore)Com).Modified());
                  //MemoF Memo=(MemoF)Com;
                  //bAenderung=Memo!=null && Memo.Txt.Modified() && Memo.bOk;
                  if(bAenderung)
                    ObjWertNeu = Com instanceof MemoF ? ((MemoF)Com).Txt.getValue():Com instanceof AUEkitCore ? new Html(((AUEkitCore)Com).getValue(),"\n"):"Hugo-Tab";
                        //g.progInfo("change "+sAktDatentyp+":"+bAenderung+"/"+ObjWertNeu);
			//	ObjWertNeu = new Memo1(((AUTextArea)Com).getValue(),Abfrage.getLaenge(TabSpalten));
		}
		else if(sAktDatentyp.equals("Bild"))
		{
			bAenderung=((BildEingabe)Com).Modified();
			if(bAenderung)
				ObjWertNeu = ((BildEingabe)Com).getFilename();
		}
		else if(sAktDatentyp.equals("Hierarchie")||sAktDatentyp.equals("BewHierarchie"))
		{
			bAenderung=((HierarchieEingabe)Com).Modified();
			if(bAenderung)
				ObjWertNeu = new HierarchieAnzeige( ((HierarchieEingabe)Com).toString(),((HierarchieEingabe)Com).getValueStt(),((HierarchieEingabe)Com).getValueStamm());
		}
		else if(sAktDatentyp.endsWith("Boolean"))
		{
			g.printInfo(2,"EnterCell:"+sAktDatentyp+"/"+Static.className(Com));
			bAenderung = Com==null?false:((AUCheckBox)Com).Modified();
			if(bAenderung)
				ObjWertNeu = new JaNein(((AUCheckBox)Com).isSelected());
		}
                else if(sAktDatentyp.endsWith("Bool3"))
                {
                      g.printInfo(2,"EnterCell:"+sAktDatentyp+"/"+Static.className(Com));
                      bAenderung = Com==null?false:((SpinBoxAuswahl)Com).Modified();
                      if(bAenderung)
                        ObjWertNeu =((SpinBoxAuswahl)Com).getCbo();//((SpinBoxAuswahl)Com).getValue();
              }
		else if(sAktDatentyp.equals("BewDatum")||sAktDatentyp.equals("BewDatum2")||sAktDatentyp.equals("Zeit"))
		{
			g.printInfo(2,"EnterCell:"+sAktDatentyp+"/"+Static.className(Com));
			bAenderung = Com==null?false:((Datum)Com).Modified();
			if(bAenderung)
				ObjWertNeu = new Zeit(((Datum)Com).getDateTime(),Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"));
		}
		else if(sAktDatentyp.equals("Datum")||sAktDatentyp.equals("Austritt")||sAktDatentyp.equals("Eintritt")||sAktDatentyp.equals("EinAustritt"))
		{
			bAenderung=((Datum)Com).Modified();
			if(bAenderung)
				ObjWertNeu = new Zeit(((Datum)Com).getDateTime(),Static.beiLeer(sFormat,"dd/MM/yyyy"));
		}
                else if(sAktDatentyp.equals("Passwort"))
                {
                  bAenderung=((AUPasswort)Com).Modified();
                  if(bAenderung)
                    ObjWertNeu = new PW1(new String(((AUPasswort)Com).getPassword()));
                }
		else if(sAktDatentyp.equals("StringKurz")||sAktDatentyp.equals("StringSehrKurz")||sAktDatentyp.equals("String60")||sAktDatentyp.equals("StringLang")||sAktDatentyp.equals("StringKurzOhne")||sAktDatentyp.equals("Stringx"))
		{
                  if (Com instanceof ComboSort)
                    ObjWertNeu = ((ComboSort)Com).getEditor().getItem();
                  else
                    ObjWertNeu = ((Text)Com).getText();
                        //g.progInfo(sAktDatentyp+":"+Static.className(ObjWert)+"/"+Static.className(ObjWertNeu));
			bAenderung=!(ObjWert.toString()).equals(ObjWertNeu.toString());
		}
		else if(sAktDatentyp.equals("Pfad") || sAktDatentyp.equals("Filename") || sAktDatentyp.equals("Doku") || sAktDatentyp.equals("FixDoku"))
		{
			ObjWertNeu = ((FileEingabe)Com).getValue();
			bAenderung=!((String)ObjWert).equals((String)ObjWertNeu);
		}
		else if(sAktDatentyp.equals("WWW"))
		{
			ObjWertNeu = ((WWW)Com).getValue();
			bAenderung=!((String)ObjWert).equals((String)ObjWertNeu);
		}
		else if(sAktDatentyp.equals("E-Mail"))
		{
			bAenderung = ((EMail)Com).Modified();
			if(bAenderung)
				ObjWertNeu = ((EMail)Com).getValue();
		}

		if(bAenderung)
		{
                  //g.progInfo("setCell2 "+iOldRow+"/"+iOldColumn+":"+ObjWertNeu);
			table.setCell(iOldRow,iOldColumn,ObjWertNeu);
                        //g.fixtestInfo("Änderung in Zeile "+iOldRow+" Spalte "+iOldColumn);
			for(TabAenderungen.moveFirst();!TabAenderungen.eof() && !(Static.Gleich(TabAenderungen.getInhalt("Zeile"),new Integer(iOldY)) && (Static.Gleich(TabAenderungen.getInhalt("Spalte"),new Integer(iOldX)) || Static.Gleich(TabAenderungen.getInhalt("Aenderungsart"),"Neu")));TabAenderungen.moveNext());
			if(TabAenderungen.eof())
			{
				TabAenderungen.addInhalt("Aenderungsart","Aendern");
				TabAenderungen.addInhalt("Datentyp", sAktDatentyp);
				TabAenderungen.addInhalt("Neuer Wert", ObjWertNeu);
				TabAenderungen.addInhalt("Alter Wert", ObjWert);
				TabAenderungen.addInhalt("Spalte", new Integer(iOldColumn));
				TabAenderungen.addInhalt("Zeile", new Integer(iOldRow));
				TabAenderungen.addInhalt("Eigenschaft",new Integer(iEigenschaft));
				TabAenderungen.addInhalt("Keys",table.getCell(iOldRow,TabSpalten.getVecSpalte("Kennung").size()));

				TabAenderungen.addInhalt("Alte Farbe",table.getForeground(iOldY,iOldX));
				table.setForeground(iOldRow,iOldColumn,Global.ColAendern);
			}
			else if(!Static.Gleich(TabAenderungen.getInhalt("Aenderungsart"),"Neu"))
			{
				boolean bNeu = TabAenderungen.getInhalt("Keys")!=null;
				Object ObjWertAlt = TabAenderungen.getInhalt("Alter Wert");
				if(bNeu && Static.Gleich(ObjWertAlt,ObjWertNeu))
				{
			       table.setForeground(iOldRow,iOldColumn,(Color)TabAenderungen.getInhalt("Alte Farbe"));
			       TabAenderungen.clearInhalt();
				}
				else
				{
					TabAenderungen.setInhalt("Neuer Wert",ObjWertNeu);
					if(bNeu)
						table.setForeground(iOldRow,iOldColumn,Global.ColAendern);
				}
			}

                        if(TabSpalten.getI("Ergebnisart")>0) // weg am 26.9.2003
				makeErgebnisart(iOldColumn);

			//if(g.Debug())
			//	TabAenderungen.showGrid("TabAenderungen");
			//g.printInfo("Änderung...");
		}
                //g.progInfo("table.setComponent1:"+iOldRow+"/"+iOldColumn);
		table.setComponent(iOldRow,iOldColumn,null);
	}
        if (VecSperre!=null && VecSperre.contains(iAktRow))
        {
          g.fixInfo("Zeile "+iAktRow+" gesperrt");
          EnableButtons();
          return;
        }
        if (checkMoa(iAktRow))
        {
          new Message(Message.WARNING_MESSAGE,(JFrame)Fom.thisFrame,g).showDialog("Monatsabschluss");
          return;
        }
	if(iAktColumn>=0)
	{
		TabSpalten.setPos(iAktColumn);
		sAktDatentyp=TabSpalten.getS("Datentyp");
                sFormat = TabSpalten.getS("Format");
	}

	if(iAktRow>=0 && iAktColumn>=0 && (table.getEditable(iAktRow,iAktColumn) || sAktDatentyp.equals("E-Mail") || sAktDatentyp.equals("WWW") || sAktDatentyp.equals("GPS")))
	{
		iOldY=iAktRow;
		iOldX=iAktColumn;
		if(!Fom.ReadOnly() && (TabSpalten.getI("bits")&Abfrage.cstEditierbar)>0)//TabSpalten.getI("Locked")==0)
			iEigenschaft = Eig(sAktDatentyp);//TabSpalten.getI("Kennung2");
		Com = TabSpalten.getInhalt("Component");

		//ObjWert = Tab.getElementAt(iOldY,iOldX);
		//g.progInfo("getCell: "+iAktRow+":"+iAktColumn);
		ObjWert = table.getCell(iAktRow,iAktColumn);

		//if((TabSpalten.getI("bits")&Abfrage.cstKeinAutoLast)>0 || !Check.isNull(ObjWert))
		if((TabSpalten.getI("bits")&Abfrage.cstNeu)!=Abfrage.cstLast || !Check.isNull(ObjWert) || sAktDatentyp.equals("Text")||sAktDatentyp.equals("Memo"))
		{
                  //g.progInfo("Tabelle1:"+sAktDatentyp+"/"+(TabSpalten.getI("bits")&Abfrage.cstNeu));
			if(Com instanceof AUComboList)//sAktDatentyp.equals("BewStamm") || sAktDatentyp.equals("Gruppe"))
            {
				if ((TabSpalten.getI("bits")&Abfrage.cstSpRefresh)>0)
					((AUComboList)Com).fillCbo(true);
//				if (iSpalteZone>-1 && iSpalteZone==iOldX)
//				{
//					g.fixtestError("Zonen-Spalte aufgerufen:"+g.getZoneS());
//					((AUComboList)Com).getComboBox().setSelectedAIC(g.getZoneAic());
//				}
//				else
					((AUComboList)Com).getComboBox().setSelectedAIC(ObjWert instanceof Combo ? ((Combo)ObjWert).getAic() : 0);
                          //g.progInfo("AUComboList set1:"+((AUComboList)Com).getComboBox().getSelectedAIC());
            }
			else if(sAktDatentyp.equals("BewModell") || sAktDatentyp.equals("BewDruck") || sAktDatentyp.equals("BewFormular")
			    || sAktDatentyp.equals("BewLand") || sAktDatentyp.equals("BewHoliday") || sAktDatentyp.equals("BewBG") || sAktDatentyp.equals("BewUser"))
				((ComboSort)Com).setSelectedAIC(((Combo)ObjWert).getAic());
			else if(sAktDatentyp.equals("BewVon_Bis"))
				((VonBisEingabe)Com).setValue(((VonBis)ObjWert).getVon(),((VonBis)ObjWert).getBis(),((VonBis)ObjWert).getSeconds());
			else if(sAktDatentyp.equals("GPS"))
			{
				((GPS_Eingabe)Com).set(ObjWert instanceof GPS ? (GPS)ObjWert:null);
//				((GPS_Eingabe)Com).setEditable(table.getEditable(iAktRow,iAktColumn));
			}
			else if(sAktDatentyp.equals("von_bis")||sAktDatentyp.equals("Auto_von_bis"))
				((VonBisEingabe)Com).setValue(((VonBis)ObjWert).getVon(),((VonBis)ObjWert).getBis());
			else if(sAktDatentyp.equals("BewDauer")||sAktDatentyp.startsWith("BewZahl")||sAktDatentyp.equals("Double") || sAktDatentyp.equals("CalcField"))
				((Zahl2)Com).Edt.setValue(((Zahl1)ObjWert).getDouble());
			else if(sAktDatentyp.endsWith("Boolean"))
			{
				//g.progInfo(sAktDatentyp+":"+Static.className(ObjWert));
				//g.progInfo("Checkbox:"+Static.className(Com));
				//g.progInfo("Wert="+((JaNein)ObjWert).getValue());
				((AUCheckBox)Com).setSelected(((JaNein)ObjWert).getValue().booleanValue());
			}
                        else if(sAktDatentyp.endsWith("Bool3"))
                          ((SpinBoxAuswahl)Com).setCbo((Combo)ObjWert);
			else if(sAktDatentyp.equals("BewDatum")||sAktDatentyp.equals("BewDatum2")||sAktDatentyp.equals("Austritt")||sAktDatentyp.equals("EinAustritt")||sAktDatentyp.equals("Eintritt")||sAktDatentyp.equals("Datum")||sAktDatentyp.equals("Zeit"))
                        {
                          //g.progInfo("AUTable:"+sAktDatentyp+"/"+Static.print(ObjWert)+"/"+(TabSpalten.getI("bits")&Abfrage.cstNeu));
                          if ((ObjWert==null || ((Zeit)ObjWert).isNull()) && (TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstNimmSync)
                            ((Datum)Com).setDate(new Date(new DateWOD(g.getBis()).yesterday()),false,true);
                          else
                            ((Datum)Com).setDate(((Zeit)ObjWert).isNull() ? null : new Date(((Zeit)ObjWert).getValue()),false,(TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstNimmSync);
                        }
			else if(sAktDatentyp.endsWith("Mass") || sAktDatentyp.equals("BewMass2") || sAktDatentyp.equals("CalcDauer"))
                        {
                          ((MassEingabe)Com).setValue(((Mass)ObjWert).getAbsValue(), ((Mass)ObjWert).getAIC());
                          if((TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstLimit)
                            ((MassEingabe)Com).setMax();
                        }
			else if(sAktDatentyp.endsWith("Waehrung") || sAktDatentyp.equals("BewWaehrung2"))
                        {
                          ((WaehrungsEingabe)Com).setValue(((Waehrung)ObjWert).getAbsValue(), Static.bei0(((Waehrung)ObjWert).getAIC(), g.getWaehrung()));
                          if((TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstLimit)
                            ((WaehrungsEingabe)Com).setMax();
                        }
			else if(sAktDatentyp.equals("Integer"))
				((Zahl2)Com).Edt.setValue(Tabellenspeicher.geti(ObjWert));
			else if(sAktDatentyp.equals("Text")||sAktDatentyp.equals("Memo"))
                        {
                          String s=ObjWert instanceof Html ? ((Html)ObjWert).getValue():ObjWert instanceof Memo1 ? ((Memo1)ObjWert).getValue() : ObjWert.toString();
                          if (Com instanceof MemoF)
                          {
                            MemoF Dlg = (MemoF)Com;
                            Text Edt = new Text(s, 0);
                            table.setComponent(iAktRow, iAktColumn, Edt);
                            Dlg.Txt.setText(s);
                            Dlg.show2();
                            if (Dlg.bOk)
                              table.setCell(iAktRow, iAktColumn, Dlg.Txt.getValue());
                          }
                          else if (Com instanceof AUEkitCore)
                          {
                            //((AUEkitCore)Com).setText(s);
                            AUEkitCore Ek=(AUEkitCore)Com;
//                            g.fixtestError("AUEkitCore1");
                            table.setComponent(iAktRow, iAktColumn, Ek);
                            if (!(ObjWert instanceof Html))
                            {
                              Ek.setText(s);
                              s = new Html(Ek.getValue(), "\n").getValue();
                            }
//                            Static.centerComponent(Ek, this);
                            Ek.show2(s,this);
                            g.fixtestError("AUEkitCore-ObjWert="+Static.hash(ObjWert));
                            if (ObjWert instanceof Html)
                              ((Html)ObjWert).setValue(Ek.getValue());
                            else
                              ObjWert=new Html(Ek.getValue(),"\n");
                            //if (Ek.Modified())
                              table.setCell(iAktRow, iAktColumn, ObjWert);
                          }
                          Enter();
                        }
                        else if(sAktDatentyp.equals("Passwort"))
				((AUPasswort)Com).setValue("");
			else if(sAktDatentyp.equals("StringKurz")||sAktDatentyp.equals("StringSehrKurz")||sAktDatentyp.equals("String60")||sAktDatentyp.equals("StringLang")||sAktDatentyp.equals("StringKurzOhne")||sAktDatentyp.equals("Stringx"))
                        {
                          if (Com instanceof ComboSort)
                            ((ComboSort)Com).setSelectedItem((String)ObjWert);
                          else
                            ((Text)Com).setText((String)ObjWert);
                        }
			else if(sAktDatentyp.equals("Pfad") || sAktDatentyp.equals("Filename") || sAktDatentyp.equals("Doku") || sAktDatentyp.equals("FixDoku"))
				((FileEingabe)Com).setValue((String)ObjWert);
			else if(sAktDatentyp.equals("WWW"))
			{
				((WWW)Com).setValue((String)ObjWert);
				((WWW)Com).setEditable(table.getEditable(iAktRow,iAktColumn));
			}
			else if(sAktDatentyp.equals("E-Mail"))
			{
				((EMail)Com).setValue((String)ObjWert);
				((EMail)Com).setEditable(table.getEditable(iAktRow,iAktColumn));
			}
//			else if(sAktDatentyp.equals("GPS"))
//			{
//				Com=new GPS_Eingabe(g);
//				((GPS_Eingabe)Com).set((GPS)ObjWert);
//				((GPS_Eingabe)Com).setEditable(table.getEditable(iAktRow,iAktColumn));
//			}
			else if(sAktDatentyp.equals("Bild"))
                        {
                          String sMandant= g.getMandant(0,"Kennung");
                          ( (BildEingabe) Com).setValue(g.LoadImage((!Static.bImgM || ((String) ObjWert).startsWith("file:\\")?"":Static.DirImageStamm)+(String) ObjWert),(String) ObjWert,sMandant);
                        }
			else if(sAktDatentyp.equals("Hierarchie")||sAktDatentyp.equals("BewHierarchie"))
				((HierarchieEingabe)Com).setValue(((HierarchieAnzeige)ObjWert).getValueStt(),((HierarchieAnzeige)ObjWert).getValueStamm());
		}
		else
		{
                  //g.progInfo("Tabelle2:"+sAktDatentyp+"/"+(TabSpalten.getI("bits")&Abfrage.cstNeu));
			if(Com instanceof AUComboList)//sAktDatentyp.equals("BewStamm") || sAktDatentyp.equals("Gruppe"))
                        {
                          ((AUComboList)Com).getComboBox().setAktAIC(ObjWert instanceof Combo ? ((Combo)ObjWert).getAic() : 0);
                          g.progInfo("AUComboList set2:"+((AUComboList)Com).getComboBox().getSelectedAIC());
                        }
			else if(sAktDatentyp.equals("BewModell") || sAktDatentyp.equals("BewDruck") || sAktDatentyp.equals("BewFormular")
			    || sAktDatentyp.equals("BewLand") || sAktDatentyp.equals("BewHoliday") || sAktDatentyp.equals("BewBG") || sAktDatentyp.equals("BewUser"))
				((ComboSort)Com).setAktAIC(((Combo)ObjWert).getAic());
			else if(sAktDatentyp.equals("BewVon_Bis"))
				((VonBisEingabe)Com).setAktValue(((VonBis)ObjWert).getVon(),((VonBis)ObjWert).getBis(),((VonBis)ObjWert).getSeconds());
			else if(sAktDatentyp.equals("GPS"))
				((GPS_Eingabe)Com).set((GPS)ObjWert);
			else if(sAktDatentyp.equals("von_bis")||sAktDatentyp.equals("Auto_von_bis"))
				((VonBisEingabe)Com).setAktValue(((VonBis)ObjWert).getVon(),((VonBis)ObjWert).getBis());
			else if(sAktDatentyp.equals("BewDauer")||sAktDatentyp.startsWith("BewZahl")||sAktDatentyp.equals("Double") || sAktDatentyp.equals("CalcField"))
				((Zahl2)Com).Edt.setAktValue(((Zahl1)ObjWert).getDouble());
			else if(sAktDatentyp.endsWith("Boolean"))
				((AUCheckBox)Com).setAktSelected(((JaNein)ObjWert).getValue().booleanValue());
                        else if(sAktDatentyp.endsWith("Bool3"))
                          ((SpinBoxAuswahl)Com).setCbo((Combo)ObjWert);
			else if(sAktDatentyp.equals("BewDatum")||sAktDatentyp.equals("BewDatum2")||sAktDatentyp.equals("EinAustritt")||sAktDatentyp.equals("Austritt")||sAktDatentyp.equals("Eintritt")||sAktDatentyp.equals("Datum")||sAktDatentyp.equals("Zeit"))
				((Datum)Com).setAktDate(((Zeit)ObjWert).isNull()?null:new Date(((Zeit)ObjWert).getValue()));
			else if(sAktDatentyp.endsWith("Mass") || sAktDatentyp.equals("BewMass2") || sAktDatentyp.equals("CalcDauer"))
				((MassEingabe)Com).setAktValue(((Mass)ObjWert).getAbsValue(),((Mass)ObjWert).getAIC());
			else if(sAktDatentyp.endsWith("Waehrung") || sAktDatentyp.equals("BewWaehrung2"))
				((WaehrungsEingabe)Com).setAktValue(((Waehrung)ObjWert).getAbsValue(),g.getWaehrung());
			else if(sAktDatentyp.equals("Integer"))
				((Zahl2)Com).Edt.setAktValue(ObjWert);
			else if(sAktDatentyp.equals("Text")||sAktDatentyp.equals("Memo"))
                        {
                          if (Com instanceof MemoF)
                            ((MemoF)Com).Txt.setAktText(ObjWert instanceof Memo1 ? ((Memo1)ObjWert).getValue() : ObjWert.toString()); //((Memo1)ObjWert).getValue());
                          else if (Com instanceof AUEkitCore)
                            ((AUEkitCore)Com).setAktText(ObjWert instanceof Memo1 ? ((Memo1)ObjWert).getValue() : ObjWert instanceof Html ? ((Html)ObjWert).getValue() : ObjWert.toString());
                        }
			else if(sAktDatentyp.equals("Pfad") || sAktDatentyp.equals("Filename") || sAktDatentyp.equals("Doku") || sAktDatentyp.equals("FixDoku"))
				((FileEingabe)Com).setAktValue((String)ObjWert);
			else if(sAktDatentyp.equals("WWW"))
			{
				((WWW)Com).setAktValue((String)ObjWert);
				((WWW)Com).setEditable(table.getEditable(iAktRow,iAktColumn));
			}
			else if(sAktDatentyp.equals("E-Mail"))
			{
				((EMail)Com).setAktValue((String)ObjWert);
				((EMail)Com).setEditable(table.getEditable(iAktRow,iAktColumn));
			}
			else if(sAktDatentyp.equals("Bild"))
				((BildEingabe)Com).setAktValue(g.LoadImage((String)ObjWert),(String)ObjWert);
			else if(sAktDatentyp.equals("Hierarchie")||sAktDatentyp.equals("BewHierarchie"))
				((HierarchieEingabe)Com).setStamm(((HierarchieAnzeige)ObjWert).getValueStamm());
		}

		JPanel Pnl = new JPanel(new BorderLayout(0,0));
                Pnl.setBackground(colorBackground);
                if (Com != null && !(Com instanceof MemoF) && !(Com instanceof AUEkitCore) && table!=null)
                {
                  //bGroesser=table.getPixelWidth(iAktColumn)>0 && (sAktDatentyp.equals("BewStamm") || sAktDatentyp.equals("Gruppe") || sAktDatentyp.equals("Hierarchie")||sAktDatentyp.equals("BewHierarchie"));
                  //if (bGroesser)
                  //  table.setPixelWidth(iAktColumn,table.getPixelWidth(iAktColumn)+30);
                  //g.progInfo("Größer:"+bGroesser+"/"+iAktColumn+"="+table.getPixelWidth(iAktColumn));
                  Pnl.add(/*Com instanceof AUTextArea ||*/ table.getPixelHeight(iAktRow) < 25 ? "Center" : "North", (Component)Com);
                  //g.progInfo("table.setComponent3:"+iAktRow+"/"+iAktColumn);
                  table.setComponent(iAktRow, iAktColumn, Pnl);
                  //table.setComponent(iAktRow,iAktColumn,Com);

                  if (Com instanceof HierarchieEingabe)
                    ((HierarchieEingabe)Com).Cbo2.requestFocus();
                  else if (Com instanceof AUComboList)
                    ((AUComboList)Com).getComboBox().requestFocus();//.getComboSortEditor().requestFocus();
                  else
                    ((Component)Com).requestFocus();
                  //else if(Com instanceof Zahl)
                  //  ((Zahl)Com).requestFocus();
                  //g.progInfo(Static.className(Com)+" requestFocus");
                  //Static.sleep(200);
                  //((Component)Com).requestFocus();
                }
		//if (Static.bMemo)
		//{
			//String sMemo=SQL.getString(g,"select memo from spalte, daten_memo join tabellenname t on daten_memo.aic_tabellenname=t.aic_tabellenname where t.kennung='spalte' and aic_fremd=aic_spalte and aic_sprache="+g.getSprache()+" and aic_abfrage="+iAIC_Abfrage+" and nummer="+TabSpalten.getI("NUMMER"));
			//table.setToolTipText(!sMemo.equals("")?sMemo:g.TabEigenschaften.posInhalt("AIC",iEigenschaft) && !g.TabEigenschaften.getS("Info").equals("")?g.TabEigenschaften.getS("Info"):null);
                        g.setTooltip(TabSpalten.getM("Tooltip"),table);
		//}
	}
	else
	{
		table.setToolTipText(null);

		if(iOldY>=0 && iOldX>=0)
                {
                  //g.progInfo("table.setComponent4:"+iOldRow+"/"+iOldColumn);
                  //if (bGroesser)
                  //  table.setPixelWidth(iOldColumn,table.getPixelWidth(iOldColumn)-30);
                  table.setComponent(iOldRow, iOldColumn, null);
                }
		iOldX=-1;
                //g.fixtestInfo("iOldY von "+iOldY+" auf "+iAktRow);
		iOldY=iAktRow;
	}
        //g.progInfo("vor EnableButtons");
	EnableButtons();
        //g.progInfo("nach EnableButtons");
}

private int Neu()
{
//	g.fixtestError("Neu():"+iOldY+"/"+iOldX+"/"+iMomY+" bei "+table.getNumRows());
        //g.fixtestInfo("Neu1: Zeile="+iOldY);
        if (table.getNumRows()==0)
          iMomY=-1;
        EnterCell(iOldY,iOldX,iOldY,1);
        if (iOldY==-1)
        {
          g.fixtestInfo("Neu: Zeile=" + iOldY + " -> "+iMomY);
          iOldY=iMomY;
        }
	for(TabAenderungen.moveFirst();!TabAenderungen.eof();TabAenderungen.moveNext())
	{
		int i=TabAenderungen.getI("Zeile");
		if(i>=iOldY)
			TabAenderungen.setInhalt("Zeile",new Integer(i+1));
	}
        if (VecSperre!=null)
          for(int i=0;i<VecSperre.size();i++)
          {
            int iZ=Sort.geti(VecSperre,i);
            if(iZ>=iOldY)
              VecSperre.setElementAt(iZ+1,i);
          }
        //g.fixtestInfo("VecSperre="+VecSperre);
        if (TabGruppen!=null)
          for(TabGruppen.moveFirst();!TabGruppen.eof();TabGruppen.moveNext())
          {
            int i=TabGruppen.getI("Summe");
            if(i>=iOldY)
              TabGruppen.setInhalt("Summe",new Integer(i+1));

          }

	int iMarkieren=-1;
	int i=0;
	//int iZ = iOldY<0?iZeile:iOldY+1;
	int iZ = iMomY<0?0:iMomY;
	table.addRow(iZ,null,null);
	iOldY=iOldY>=0?iOldY+1:iOldY;
        //g.fixtestInfo("Neu3: Zeile="+iOldY);
	for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
	{
		Object oWert=null;
		String sDatentyp = TabSpalten.getS("Datentyp").trim();

		//String sSpaltenname = TabSpalten.getS("Kennung").trim();
		String sFormat = TabSpalten.getS("Format");

		//g.printInfo(sSpaltenname);
		if(Fom.ReadOnly() || (TabSpalten.getI("bits")&Abfrage.cstEditierbar)==0)//TabSpalten.getI("Locked")>0)
			oWert=null;
		else if(TabSpalten.getInhalt("Component") instanceof AUComboList)//sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe"))
		{
                  int iAic=0;
                  if (g.bZoneChange && iSpalteZone>-1 && TabSpalten.getS("Kennung2").indexOf("_")<0 && g.VecEigZone.contains(TabSpalten.getI("Kennung2")))
	  			  {
//  					g.fixtestError("Zonen-Spalte aufgerufen:"+g.getZoneS());
//  					((AUComboList)Com).getComboBox().setSelectedAIC(g.getZoneAic());
  					iAic=g.getZoneAic();
	  			  }
  				  else if ((TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstNimmSync) /* Syncron-Wert */
                    iAic=g.TabStammtypen.posInhalt("Aic",TabSpalten.getI("Stt"))?g.TabStammtypen.getI("Sync"):0;
                  else if ((TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstLast /* letzter Wert */)
                    iAic=((AUComboList)TabSpalten.getInhalt("Component")).getComboBox().getSelectedAIC();
                  else if ((TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstKeinAutoLast /* leer lassen */)
                    ((AUComboList)TabSpalten.getInhalt("Component")).getComboBox().setSelectedAIC(0);
                  oWert = ((AUComboList)TabSpalten.getInhalt("Component")).getComboBox().getItem(iAic);
                  //g.progInfo(TabSpalten.getS("Bezeichnung")+"/"+sDatentyp+":"+oWert+"/"+Static.className(oWert));
		}
		else if(sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular")
		    || sDatentyp.equals("BewLand") || sDatentyp.equals("BewHoliday") || sDatentyp.equals("BewBG") || sDatentyp.equals("BewUser"))
			oWert = ((ComboSort)TabSpalten.getInhalt("Component")).getItemAt(0);
		else if(sDatentyp.equals("BewVon_Bis"))
		{
//                  if ((TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstLast)
//                  {
//                	  g.fixtestError("Last bei BewVon_Bis");
//                    Com=TabSpalten.getInhalt("Component");
//                    oWert = new VonBis(((VonBisEingabe)Com).getVon(), ((VonBisEingabe)Com).getBis(), Static.beiLeer(sFormat, "dd/MM/yyyy HH:mm:ss"));
//                  }
//                  else
                    oWert=new VonBis(g,null,null,0,Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"),Abfrage.getLaenge(TabSpalten),g.getFaktor(TabSpalten.getI("Stamm")));
			//table.setAlignment(iZ,i,JCTblEnum.MIDDLERIGHT);
		}
		else if(sDatentyp.equals("von_bis")||sDatentyp.equals("Auto_von_bis"))
			oWert=new VonBis(g,sDatentyp.equals("Auto_von_bis")?new Date():null,null,Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"));
		else if(sDatentyp.equals("BewDauer")||sDatentyp.startsWith("BewZahl")||sDatentyp.equals("Double") || sDatentyp.equals("CalcField"))
		{
			oWert=new Zahl1(null,TabSpalten.getS("Format"));
			//table.setAlignment(iZ,i,JCTblEnum.MIDDLERIGHT);
		}
		else if(sDatentyp.endsWith("Boolean"))
                  if ((TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstLast /* letzter Wert */)
                    oWert=new JaNein(((AUCheckBox)TabSpalten.getInhalt("Component")).isSelected());
                  else
                    oWert=new JaNein(TabSpalten.getF("Faktor")!=0.0);
                else if(sDatentyp.endsWith("Bool3"))
                  oWert=((SpinBoxAuswahl)TabSpalten.getInhalt("Component")).getCbo();
		else if(sDatentyp.equals("BewDatum")||sDatentyp.equals("BewDatum2")||sDatentyp.equals("Zeit"))
			oWert=new Zeit((TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstNimmSync ? new Date(new DateWOD(g.getBis()).yesterday()):
                                 (TabSpalten.getI("bits")&(Global.cstKeinAutoDate*0x10000+Abfrage.cstKeinAutoDate))>0?null:iMomY<0 || iMomX<0 || (TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstLimit ? new Date():
                                 (TabSpalten.getI("bits")&Abfrage.cstNeu)==Abfrage.cstKeinAutoLast || Check.isNull(table.getCell(iZ+1,i))?null:
                                       new Date(((Zeit)table.getCell(iZ+1,i)).getValue()),Static.beiLeer(sFormat,"dd/MM/yyyy HH:mm:ss"));
		else if(sDatentyp.equals("Eintritt")||sDatentyp.equals("Austritt")||sDatentyp.equals("EinAustritt")||sDatentyp.equals("Datum"))
			oWert=new Zeit(null,Static.beiLeer(sFormat,"dd/MM/yyyy"));
		else if(sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcDauer"))
		{
			oWert=new Mass(g,g.getMass(TabSpalten.getI("Stamm"),(TabSpalten.getI("bits")&Abfrage.cstKeineEinheit)>0),TabSpalten.getI("Stamm")==0?null:new Double(0.0),TabSpalten.getS("Format"));
			//table.setAlignment(iZ,i,JCTblEnum.MIDDLERIGHT);
		}
		else if(sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
		{
			oWert=new Waehrung(g.getVecWaehrung((TabSpalten.getI("Bits")&Abfrage.cstKeineEinheit)>0,TabSpalten.getI("Stamm")),new Double(0.0),TabSpalten.getS("Format"));
			//table.setAlignment(iZ,i,JCTblEnum.MIDDLERIGHT);
		}
		else if(sDatentyp.equals("Integer"))
		{
			oWert=null;
			//table.setAlignment(iZ,i,JCTblEnum.MIDDLERIGHT);
		}
		else if(sDatentyp.equals("Hierarchie")||sDatentyp.equals("BewHierarchie"))
			oWert=new HierarchieAnzeige("",iAIC_Stammtyp,0);
                else if(sDatentyp.equals("Passwort"))
                  oWert=new PW1("");
		else if(sDatentyp.equals("StringKurz")||sDatentyp.equals("StringSehrKurz")||sDatentyp.equals("String60")||sDatentyp.equals("StringLang")||sDatentyp.equals("Stringx")||
				sDatentyp.equals("StringKurzOhne")||sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") ||sDatentyp.equals("Doku") || sDatentyp.equals("FixDoku") ||
                                sDatentyp.equals("WWW")||sDatentyp.equals("E-Mail")||sDatentyp.equals("Bild"))
			oWert="";
		else if(sDatentyp.equals("Text")||sDatentyp.equals("Memo"))
			oWert="";//new AUTextArea(g,3);//new Memo1("",Abfrage.getLaenge(TabSpalten));

		int iAlign=g.getAlignment(sDatentyp,TabSpalten.getI("Ausrichtung"));
		table.setAlignment(iZ,i,iAlign == BWTEnum.TOPLEFT || iAlign == BWTEnum.MIDDLELEFT ? JCTblEnum.TOPLEFT:
                                   iAlign == BWTEnum.TOPCENTER || iAlign == BWTEnum.MIDDLECENTER ? JCTblEnum.TOPCENTER:JCTblEnum.TOPRIGHT);

		table.setForeground(iZ,i,Global.ColNeu);

		if(!Fom.ReadOnly() && (TabSpalten.getI("bits")&Abfrage.cstEditierbar)>0/*TabSpalten.getI("Locked")==0*/ && !(sDatentyp.equals("long binary")||
		   sDatentyp.equals("Kennung")||sDatentyp.equals("Bezeichnung")||sDatentyp.equals("Eintritt")||sDatentyp.equals("Austritt")||sDatentyp.equals("EinAustritt")||
		   sDatentyp.equals("Stichtag")||sDatentyp.equals("Trennzeichen")||sDatentyp.equals("CalcBezeichnung")||sDatentyp.equals("Timestamp")||sDatentyp.equals("entfernt")||
		   sDatentyp.equals("SysAic")||sDatentyp.equals("Aic")||sDatentyp.endsWith("Benutzer")||sDatentyp.equals("Anlage")||sDatentyp.equals("Mandant")||
		   sDatentyp.equals("Einheiten")||sDatentyp.equals("Vorgaenger")))
		{
			//table.setEditable(iZ,i,true);

			TabAenderungen.addInhalt("Aenderungsart","Aendern");
			TabAenderungen.addInhalt("Spalte",new Integer(i));
			TabAenderungen.addInhalt("Zeile",new Integer(iZ));
			TabAenderungen.addInhalt("Keys",null);
			TabAenderungen.addInhalt("Alte Farbe",null);
			TabAenderungen.addInhalt("Eigenschaft",new Integer(Eig(sDatentyp)));//TabSpalten.getInhalt("Kennung2"));
			TabAenderungen.addInhalt("Alter Wert",oWert);
			TabAenderungen.addInhalt("Datentyp",sDatentyp);
			TabAenderungen.addInhalt("Neuer Wert",oWert);

			if(iMarkieren<0)
				iMarkieren=i;
		}
		//else
			//table.setEditable(iZ,i,false);

		table.setCell(iZ,i,oWert);
                table.setBackground(iZ,i,Global.ColTable);
		table.setFont(iZ,i,g.getTabFont());
		i++;
	}
        //if (true)
        //  return iZ; // !!!
	//table.setNumRows(++iZeile);
	++iZeile;
        setSpaltenEdit(true);

	//Ergebnisart();  // weg am 26.9.2003
        table.setPixelHeight(iZ,16*g.getFontFaktor()/100+4);
	EnterCell(iOldY,iOldX,iZ,iMarkieren); //Testweise weg am 3.12.2021
        if(iMarkieren>=0)
          iMomX=iMarkieren;
	if(g.Debug())
		TabAenderungen.showGrid("TabAenderungen");

        EnableButtons();
        if(iZ==iZeile-1)
        {
          TableScrollbar TSB = table.getVertSB();
          TSB.setValue(TSB.getAdjustable().getMaximum()-TSB.getAdjustable().getVisibleAmount());
        }

	g.debugInfo("Funktion Neu() Ende");
	return iZ;
}

      private boolean checkMoa(int iAktRow)
      {
        if (dtMoa != null && iAktRow>=0)
        {
          //g.testInfo("checkMoa:"+iOldY+"/"+iAktRow);
          TabSpalten.push();
          boolean bMonatsabschluss=false;
          if (!table.getCell(iAktRow,iSpalte).equals("") && iAIC_Stamm>0)
            for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
            {
              //g.progInfo("checkMoa"+iAIC_Stamm+":"+iAktRow+"/"+TabSpalten.getPos()+"="+table.getCell(iAktRow,TabSpalten.getPos()));
		bMonatsabschluss=bMonatsabschluss||(TabSpalten.getI("bits")&(Abfrage.cstGueltig))>0 && Check.Monatsabschluss(g/*,Fom.getBegriff()*/,iAIC_Bewegungstyp,iAIC_Stamm,table.getCell(iAktRow,TabSpalten.getPos()),null);
            }
          TabSpalten.pop();
          return bMonatsabschluss;
        }
        else
          return false;
      }

      private boolean checkZeileFix(int iAktRow)
      {
        sNichtLeer=null;
        for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
        {
          Object Obj=table.getCell(iAktRow, TabSpalten.getPos());
          if ((TabSpalten.getI("bits2") & Abfrage.cstSpKeinDel) > 0 && !Check.isNull(Obj))
          {
            //boolean b=!Check.isNull(Obj);
            sNichtLeer=TabSpalten.getS("Bezeichnung");
            g.fixtestInfo("checkZeileFix "+TabSpalten.getS("Kennung")+"/"+TabSpalten.getS("Bezeichnung")+":"+Obj+"-> true");
            return true;
          }
        }
        return false;
      }

private void Loeschen()
{
	//g.debugInfo("Funktion Loeschen() Anfang");
	if(checkMoa(iMomY))
          new Message(Message.WARNING_MESSAGE,(JFrame)Fom.thisFrame,g).showDialog("Monatsabschluss");
        else if (checkZeileFix(iMomY))
          new Message(Message.WARNING_MESSAGE,(JFrame)Fom.thisFrame,g).showDialog("Zeile_fixiert",new String[] {sNichtLeer});
        else
	{
          //g.progInfo("table.setComponent5:"+iMomY+"/"+iMomX);
		table.setComponent(iMomY,iMomX,null);

		boolean bNeu = false;

		TabAenderungen.moveFirst();
		while(!TabAenderungen.eof())
		{
			boolean bClear = false;

			if(Static.Gleich(TabAenderungen.getInhalt("Zeile"),new Integer(iMomY)))
			{
				bNeu = bNeu || TabAenderungen.getS("Aenderungsart").equals("Aendern") && TabAenderungen.getInhalt("Keys")==null;
				//if(!bNeu)
					//TabAenderungen.clearInhalt();
				if(TabAenderungen.getS("Aenderungsart").equals("Aendern"))
				{
					if(!bNeu)
					{
						TabAenderungen.setInhalt("Aenderungsart","DelEdit");
						TabAenderungen.setInhalt("Alte Farbe",table.getForeground(TabAenderungen.getI("Zeile"),TabAenderungen.getI("Spalte")));
					}
					else
					{
						TabAenderungen.clearInhalt();
						bClear=true;
					}
				}
			}

			if(!bClear)
				TabAenderungen.moveNext();

		}

		if(!bNeu)
		{
			Vector<Object> VecKeys = new Vector<Object>();
			Vector<Color> VecFarben = new Vector<Color>();
			TabSpalten.moveFirst();
			for(int i=0;!TabSpalten.eof();i++)
			{
				if(iArt==Standard && TabSpalten.getS("pkey").equalsIgnoreCase("Y"))
					VecKeys.addElement(table.getUserdata(iMomY,i));

				VecFarben.addElement(table.getForeground(iMomY,i));
				table.setForeground(iMomY,i,Global.ColLoeschen);
				table.setEditable(iMomY,i,false);
				TabSpalten.moveNext();
			}

			TabAenderungen.addInhalt("Aenderungsart","Loeschen");
			TabAenderungen.addInhalt("Datentyp", null);
			TabAenderungen.addInhalt("Neuer Wert", null);
			TabAenderungen.addInhalt("Alter Wert", null);
			TabAenderungen.addInhalt("Spalte", null);
			TabAenderungen.addInhalt("Zeile", new Integer(iMomY));
			TabAenderungen.addInhalt("Keys",iArt==Standard?VecKeys:table.getCell(iMomY,TabSpalten.getVecSpalte("Kennung").size()));
			TabAenderungen.addInhalt("Alte Farbe",VecFarben);
			TabAenderungen.addInhalt("Eigenschaft",null);
		}
		else
		{
			for(TabAenderungen.moveFirst();!TabAenderungen.eof();TabAenderungen.moveNext())
				if(TabAenderungen.getI("Zeile")>iMomY)
					TabAenderungen.addI("Zeile",-1);
                        if (TabGruppen!=null)
                          for(TabGruppen.moveFirst();!TabGruppen.eof();TabGruppen.moveNext())
                                if(TabGruppen.getI("Summe")>iMomY)
                                        TabGruppen.addI("Summe",-1);
                        if (VecSperre!=null)
                          for(int i=0;i<VecSperre.size();i++)
                          {
                            int iZ=Sort.geti(VecSperre,i);
                            if(iZ>iMomY)
                              VecSperre.setElementAt(iZ-1,i);
                          }
			table.deleteRows(iMomY,1);
			iZeile--;
		}

		iOldX=-1;
		iOldY=-1;

		Ergebnisart(); // verursacht Fehler bei h:mm-Darstellung
                EnableButtons();
	}

	//if(g.Debug())
	//	TabAenderungen.showGrid("TabAenderungen");

	//g.debugInfo("Funktion Loeschen() Ende");
}

private void Undelete()
{
	//g.debugInfo("Undelete Anfang");

	for(TabAenderungen.moveFirst();!TabAenderungen.eof();TabAenderungen.moveNext())
	{
		if(TabAenderungen.getI("Zeile")==iMomY)
		{
			if(TabAenderungen.getS("Aenderungsart").equals("DelEdit"))
				TabAenderungen.setInhalt("Aenderungsart","Aendern");
			else if(TabAenderungen.getS("Aenderungsart").equals("Loeschen"))
			{
				TabSpalten.moveFirst();
				for(int i=0;!TabSpalten.eof();i++)
				{
					table.setForeground(iMomY,i,(Color)((Vector)TabAenderungen.getInhalt("Alte Farbe")).elementAt(i));
					//table.setEditable(iMomY,i,!Fom.ReadOnly() && (TabSpalten.getI("bits")&Abfrage.cstEditierbar)>0/*TabSpalten.getI("Locked")==0*/);
					TabSpalten.moveNext();
				}
				TabAenderungen.clearInhalt();
			}
		}
	}
        setSpaltenEdit(bEdit2);

	Ergebnisart();
        EnableButtons();
	//if(g.Debug())
	//	TabAenderungen.showGrid("TabAenderungen");

	//g.debugInfo("Undelete Ende");
}

public boolean Modified()
{
  //g.saveInfo("AUTable ["+sDefBez+"].Modified:"+(TabAenderungen==null ? -1:TabAenderungen.size()));
	return TabAenderungen.getAnzahlElemente("Zeile")>0;
}

public void Reset()
      {
        if (Modified())
        {
          clear();
          Refresh();
        }
      }

public void clear()
{
	for(TabAenderungen.moveFirst();!TabAenderungen.eof();TabAenderungen.moveNext())
	{
		Integer iZ= TabAenderungen.getInt("Zeile");
		Integer iS= TabAenderungen.getInt("Spalte");
		if(iZ!=null&&iS!=null)
			table.setForeground(iZ.intValue(),iS.intValue(),Global.ColStandard);
	}

	TabAenderungen.clearAll();
}

public boolean Save(int iProt, boolean bSpeicherabfrage, int riAIC_Stamm)
{
  g.saveInfo("AUTable ["+sDefBez+"].Save "+iProt+"/"+bSpeicherabfrage+"/"+riAIC_Stamm);
	if(bFehler)
          return false;

	boolean bSave = Fom.ReadOnly() ? false :BtnSave.isEnabled() && Modified();
        if (bSave && /*!g.bTestPC &&*/ g.getMandant()==1 && !g.bAppl && !g.bBasis && iAIC_Bewegungstyp>0 && (g.getBewBits(iAIC_Bewegungstyp)&(Global.cstDefinition+Global.cstMandantIgnorieren))==0)
        {
          new Message(Message.WARNING_MESSAGE, null, g).showDialog("Speichernsperre");
          Static.printError("AUTable.Save: Speichern mit All nicht möglich");
          bSave=false;
        }
	EnterCell(iOldY,iOldX,-1,-1);

	if(bSpeicherabfrage && bSave)
        {
          int i=new Message(Message.QUESTION_MESSAGE, null, g).showDialog("Speichern");
          if (i==Message.CANCEL_OPTION)
            return false;
          bSave = i == Message.YES_OPTION;
        }
	boolean bDoIt=true;
	if(bSave)
	{
		Vector Vec=FehlerListe();
		bDoIt = Vec.size()==0;
		if(!bDoIt)
                {
                  new Message(Message.WARNING_MESSAGE, null, g).showDialog("Fehlerliste", new String[] {Static.VecToString(Vec)});
                  return false;
                }
	}
        if(bDoIt)
	{
          iAIC_Stamm=riAIC_Stamm;
		if(bSave)
		{
            long lClock = Static.get_ms();
            g.printExec("Speichere "+g.getBegBez(iAIC_Begriff_Abfrage),false);
            g.start();
            int iError=g.getFehler();
			iProt=iProt==0?g.Protokoll("Tabelle",iAIC_Begriff_Abfrage):iProt;

			String sSpeichernInfo = "";

			int iZeile = -1;
			int iAIC_Bew=0;
			int iAIC_BewOld=0;
			//Vector VecLoeschen = new Vector();
			g.debugInfo("iProt:"+iProt);

			TabAenderungen.zusammenfassen("Zeile");
			g.debugInfo("Nach TabAenderungen.zusammenfassen");
      Vector<Integer> VecSave=new Vector<Integer>();
      SQL Insert = new SQL(g);
      g.checkAus("Tabelleneingabe "+A.sDefBez);
			for(TabAenderungen.moveFirst();!TabAenderungen.eof();TabAenderungen.moveNext())
			{
				if(TabAenderungen.getS("Aenderungsart").equals("Loeschen"))
				{
					if(iArt == Stamm)
					{
						int iStamm = TabAenderungen.getI("Keys");
						g.exec("update Stamm_Protokoll set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and aic_stamm="+iStamm);
						g.exec("update Stamm_Protokoll set aic_begriff="+ g.getBegriffAicS("Status","del") +" where aic_stamm="+iStamm);
						g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and aic_stamm="+iStamm+" or  sta_aic_stamm="+iStamm);
					}
					else if(iArt == Bewegung)
					{
						int iBew = TabAenderungen.getI("Keys");
						//SQL.exec(g,"update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and aic_bew_pool="+iBew);
						if ((g.getBewBits(iAIC_Bewegungstyp)&Global.cstBewBew)>0)
						{
							int iBewBew=SQL.getInteger(g,"select bew2_aic_bew_pool from bew_pool where aic_Bew_Pool="+iBew);
							if (iBewBew>0)
								g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_bew_pool="+iBewBew+" and pro_aic_protokoll is null");
						}
						g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_bew_pool="+iBew);
                                                VecSave.addElement(new Integer(iBew));
					}

					//VecLoeschen.addElement(TabAenderungen.getInhalt("Zeile"));
				}
				else if(TabAenderungen.getS("Aenderungsart").equals("Aendern"))
				{
					table.setForeground(TabAenderungen.getI("Zeile"),TabAenderungen.getI("Spalte"),Color.black);

                                        Object oGueltigkeit = iSpalteGueltigkeit!=-1?table.getCell(TabAenderungen.getI("Zeile"),iSpalteGueltigkeit):null;
                                        Zeit zGueltigkeit = oGueltigkeit instanceof Zeit?(Zeit)oGueltigkeit:null;
                                        //g.progInfo("iSpalteGueltigkeit="+iSpalteGueltigkeit+","+(TabSpalten.getI(iSpalteGueltigkeit,"bits2")&Abfrage.cstSpNotSave));
					if(iZeile!=TabAenderungen.getI("Zeile"))
					{
						if(iArt==Bewegung)
						{
                                                  iAIC_Bew = TabAenderungen.getI("Keys");
							iAIC_BewOld=iAIC_Bew;
							if(iAIC_Bew!=0)
							{
								//SQL.exec(g,"update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and aic_bew_pool="+iAIC_Bew);
								g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_bew_pool="+iAIC_Bew);
							}

							Insert.add("aic_bewegungstyp",iAIC_Bewegungstyp);
                                                        if ((TabRest!=null && iAIC_Bew>0 || riAIC_Stamm<0) && zGueltigkeit==null)
                                                          zGueltigkeit =new Zeit(SQL.getTimestamp(g,"select Gueltig from bew_pool where aic_Bew_Pool="+(riAIC_Stamm<0 ?-riAIC_Stamm:iAIC_Bew)),null);
							if (zGueltigkeit!=null && !zGueltigkeit.isNull() && (iSpalteGueltigkeit<0 || (TabSpalten.getI(iSpalteGueltigkeit,"bits2")&Abfrage.cstSpNotSave)==0))
							{
								g.saveInfo("save: zGueltigkeit: "+zGueltigkeit.getDate()/*(bGueltigHH ? zGueltigkeit.sqlDateTime():zGueltigkeit.sqlDate())*/);
								//if (bGueltigHH)
                                                                  Insert.add("Gueltig", zGueltigkeit.getDate()); // DateTime
                                                                  Insert.add("LDATE", Static.DateToInt(zGueltigkeit.getDate()));
                                                                //else
								//	Insert.add2("Gueltig",zGueltigkeit.sqlDate());// Date
							}
                            else
                              Insert.add("LDATE",0);
                            Insert.add("LDATE2",0);
                            Insert.add("LDATE3",0);
                            Insert.add("BOOL1",0);
                            Insert.add("BOOL2",0);
                            Insert.add("LDateVon",0);
                            Insert.add("LDateBis",0);
							Insert.add("aic_protokoll",iProt);
							Insert.add("aic_mandant",g.getMandant());
                                                        /*if (g.isEigANR(iAIC_Eigenschaft))
                                                          Insert.add("ANR",riAIC_Stamm);
                                                        else if (iSpalteANR>-1)
                                                        {
                                                          Object oANR =  table.getCell(TabAenderungen.getI("Zeile"),iSpalteANR);
                                                          int iANR = oANR instanceof Combo ? ((Combo)oANR).getAic():0;
                                                          if (iANR>0)
                                                            Insert.add("ANR",iANR);
                                                        }*/

							if(iAIC_Bew!=0)
								Insert.add("bew_aic_bew_pool",iAIC_Bew);
                                                        //g.progInfo("Save: riAIC_Stamm="+riAIC_Stamm);
							boolean bNeu=iAIC_BewOld == 0;
                            if (riAIC_Stamm<0)
                              Insert.add("bew2_aic_bew_pool",-riAIC_Stamm);
                            else if (!bNeu)
                            {
                              int iBewBew=SQL.getInteger(g,"select bew2_aic_bew_pool from bew_pool where aic_Bew_Pool="+iAIC_BewOld);
                              if (iBewBew>0)
                                Insert.add("bew2_aic_bew_pool",iBewBew);
                            }
                            if (g.hasZone(iAIC_Bewegungstyp))
                            {
	                            int iZone=-1;
	                            if (iSpalteDST>-1 && (bNeu || iSpalteZone>-1))
	                            {
	                            	Object oDST = table.getCell(TabAenderungen.getI("Zeile"),iSpalteDST);
	                                Zeit zDST = oDST != null && oDST instanceof Zeit ? (Zeit)oDST:null;
	                                String sZone=null;
	                                if (iSpalteZone>-1)
	                                {
	                                	Object oZone = table.getCell(TabAenderungen.getI("Zeile"),iSpalteZone);
	                                	Combo CboZone=oZone != null && oZone instanceof Combo ? (Combo)oZone:null;
	                                	sZone=CboZone != null && CboZone.getAic()>0 ? g.getZone(CboZone.getAic()):null;
	                                	if (bNeu && Static.Leer(sZone))
	                                		sZone=g.getZoneS();//"CET";
	                                }
	                                else
	                                	sZone=g.getZoneS();//"CET";
	                                if (Static.Leer(sZone))
	                                	g.printError("Zone-Spalte hat keinen Wert",iAIC_Begriff_Abfrage);
	                                else if (zDST==null)
	                                	g.printError("DST-Spalte hat keinen Wert",iAIC_Begriff_Abfrage);
	                                else
	                                  iZone=DateWOD.getZone(sZone,zDST.getDate());
	                            }
	                            if (iZone!=-1)
	                              Insert.add("Zone",iZone);
	                            else if (bNeu)
	                              Insert.add("Zone", g.getZone());
	                            else
	                              Insert.add("Zone", SQL.getInteger(g, "select zone from bew_pool where aic_bew_pool=" + iAIC_BewOld));
                            }
							iAIC_Bew = Insert.insert("Bew_Pool",true);
                            if (!bNeu)
                            {
                              g.exec("update bew_pool set bew2_aic_bew_pool=" + iAIC_Bew + " where bew2_aic_bew_pool=" + iAIC_BewOld);
                              VecSave.addElement(new Integer(iAIC_BewOld));
                            }
                            VecSave.addElement(new Integer(iAIC_Bew));
							//g.debugInfo("Satz="+iSatz);

							if(!bKeinStm)
							{
                                                          g.progInfo("Hauptrelation:"+riAIC_Stamm+" bei "+iAIC_Eigenschaft+"/"+iAIC_Bewegungstyp);
                                                          Import.insertBewStamm(g,iAIC_Bewegungstyp,iAIC_Bew,iAIC_Eigenschaft,riAIC_Stamm);
								/*Insert.add("aic_Bew_Pool",iAIC_Bew);
								Insert.add("aic_Eigenschaft",iAIC_Eigenschaft);
								Insert.add("aic_Stamm",riAIC_Stamm);
								Insert.insert("Bew_Stamm",false);*/
								if (TabRest!=null && iArt==Bewegung && TabRest.posInhalt("aic",iAIC_Eigenschaft))
									TabRest.setInhalt("saved",Boolean.TRUE);
							}
							//forTabSpalten.moveFirst();
						}
					}

					if(iArt==Bewegung || iArt==Stamm)
					{
						for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
						{
                                                  //g.progInfo("save:"+TabAenderungen.getI("Zeile")+"/"+TabSpalten.getPos()+":"+table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos()));
                                                  String sDatentyp = iArt==Stamm?TabAenderungen.getS("Datentyp"):TabSpalten.getS("Datentyp");
                                                  //g.progInfo(TabSpalten.getS("Kennung2")+":"+sDatentyp);

							if((iArt==Stamm?true:(Abfrage.VRel.contains(sDatentyp) || TabSpalten.getS("Kennung2").indexOf('_')==-1)) && table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos())!=null)
							{
                                                          int iAIC_Eig = iArt==Stamm?TabAenderungen.getI("Eigenschaft"):Eig(sDatentyp);
                                                          if (TabRest!=null&&iArt==Bewegung&&TabRest.posInhalt("aic",iAIC_Eig))
                                                            TabRest.setInhalt("saved",Boolean.TRUE);
								if(((TabSpalten.getI("bits2")&Abfrage.cstSpNotSave)==0))
								{
									SQL Qry = new SQL(g);

                                                                        Object ObjCell = table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
                                                                        //g.fixtestInfo("Datentyp="+sDatentyp);
                                                                        if (ObjCell instanceof String && ((String)ObjCell).equals(""))
                                                                          ;//g.testInfo("String=null bei "+TabSpalten.getS("Bezeichnung")+"/"+sDatentyp);
									else if(sDatentyp.equals("BewVon_Bis"))
									{
										VonBis vb = (VonBis)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										//if(vb.getVon()!=null || vb.getBis()!=null || vb.getDauer()!=0.0)
										if(!vb.isNull())
										{
											Import.insertBewVonBis(g,iAIC_Bewegungstyp, iAIC_Bew, iAIC_Eig,vb.getVon(),vb.getBis(),vb.getSeconds());
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("BewStamm")||sDatentyp.equals("BewHierarchie"))
									{
										//Combo Cbo = (Combo)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
                                                                                Object Obj=table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
                                                                                //g.progInfo("AUTable.Save:"+sDatentyp+"/"+TabSpalten.getS("Bezeichnung")+":"+Static.print(Obj));
										int i = Obj instanceof Image ? Ampel.getStamm((Image)Obj):
                                                                                    Obj instanceof Combo ? ((Combo)Obj).getAic():
                                                                                    Obj instanceof HierarchieAnzeige ? ((HierarchieAnzeige)Obj).getValueStamm():-1;
                                                                                if (i<0)
                                                                                  Static.printError("AUTable.Save: "+TabSpalten.getS("Bezeichnung")+"="+Static.print(Obj)+" nicht speicherbar!");
										else if(i>0 && iAIC_Eigenschaft!=iAIC_Eig)
										{
                                                                                  Import.insertBewStamm(g,iAIC_Bewegungstyp,iAIC_Bew,iAIC_Eig,i);
											/*Qry.add("AIC_Bew_Pool",iAIC_Bew);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("AIC_Stamm",i);
											Qry.insert("Bew_Stamm",false);*/
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular"))
									{
										int i = ((Combo)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos())).getAic();
										if(i>0)
										{
											Qry.add("AIC_Bew_Pool",iAIC_Bew);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("AIC_Begriff",i);
											Qry.insert("Bew_Begriff",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if (sDatentyp.equals("BewLand") || sDatentyp.equals("BewHoliday") || sDatentyp.equals("BewBG") || sDatentyp.equals("BewUser"))
									{
									  	int i = ((Combo)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos())).getAic();
										if(i>0)
										{
											Qry.add("AIC_Bew_Pool",iAIC_Bew);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("AIC",i);
											Qry.insert("Bew_Aic",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("Gruppe")||sDatentyp.equals("Hierarchie"))
									{
										//Combo Cbo = iArt==Stamm?(Combo)TabAenderungen.getInhalt("Neuer Wert"):(Combo)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										Object Obj=iArt==Stamm ? TabAenderungen.getInhalt("Neuer Wert"):table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
                                                                                int i = Obj instanceof Combo ? ((Combo)Obj).getAic():((HierarchieAnzeige)Obj).getValueStamm();
										if(i!=0)
										{
											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Sta_AIC_Stamm",i);
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("BewDauer"))
									{
										Zahl1 z = (Zahl1)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										//if(z.getValue()!=0.0)
										if(!z.isNull())
										{
											Qry.add("AIC_Bew_Pool",iAIC_Bew);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Dauer",z.getValue());
											Qry.insert("Bew_von_bis",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("BewBoolean"))
									{
										JaNein JN = (JaNein)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										//if(JN.getValue().booleanValue())
										if(!JN.isNull() && JN.getValue().booleanValue())
										{
                                                                                  Import.insertBewBool(g,iAIC_Bewegungstyp,iAIC_Bew,iAIC_Eig);
											/*Qry.add("AIC_Bew_Pool",iAIC_Bew);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Spalte_Boolean",JN.getValue().booleanValue());
											Qry.insert("Bew_Boolean",false);*/
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
                                                                        else if(sDatentyp.equals("BewBool3"))
                                                                        {
                                                                          //Static.printError("Speichern von BewBool3 noch nicht möglich!!!");
                                                                          Combo Cbo=(Combo)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
                                                                          if (Cbo != null && Cbo.use)
                                                                          {
                                                                            Import.insertBewBool3(g,iAIC_Bewegungstyp,iAIC_Bew,iAIC_Eig,-Cbo.getAic());
                                                                            /*Qry.add("AIC_Bew_Pool",iAIC_Bew);
                                                                            Qry.add("AIC_Eigenschaft",iAIC_Eig);
                                                                            Qry.add("Aic",Cbo.getAic());
                                                                            Qry.insert("Bew_Aic",false);*/
                                                                            sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
                                                                          }
                                                                        }
									else if(sDatentyp.equals("Boolean"))
									{
										JaNein JN = iArt==Stamm?(JaNein)TabAenderungen.getInhalt("Neuer Wert"):(JaNein)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										//if(JN.getValue().booleanValue())
										if(!JN.isNull() && JN.getValue().booleanValue())
										{
											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Spalte_Double",1.0);
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
                                                                        else if(sDatentyp.equals("Bool3"))
                                                                        {
                                                                                Combo Cbo = iArt==Stamm?(Combo)TabAenderungen.getInhalt("Neuer Wert"):(Combo)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
                                                                                //if(JN.getValue().booleanValue())
                                                                                if (Cbo != null && Cbo.use)
                                                                                {
                                                                                        if(iArt==Bewegung)
                                                                                                Qry.add("AIC_Bew_Pool",iAIC_Bew);
                                                                                        else
                                                                                                Qry.add("AIC_Stamm",riAIC_Stamm);
                                                                                        Qry.add("AIC_Eigenschaft",iAIC_Eig);
                                                                                        Qry.add("Spalte_Double",Cbo.getAic());
                                                                                        Qry.add("AIC_Protokoll",iProt);
                                                                                        Qry.insert("Stammpool",false);
                                                                                        sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
                                                                                }
                                                                        }
									else if(sDatentyp.equals("Double"))
									{
										Zahl1 z = iArt==Stamm?(Zahl1)TabAenderungen.getInhalt("Neuer Wert"):(Zahl1)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										if(!z.isNull())
										{
											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Spalte_Double",z.getValue());
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("Datum"))
									{
										Zeit z = iArt==Stamm?(Zeit)TabAenderungen.getInhalt("Neuer Wert"):(Zeit)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										if(!z.isNull())
										{
											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Gultig_Von",z.getDate());// Date
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("BewDatum2"))
									{
										Zeit z = (Zeit)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										if(!z.isNull())
										{
											//g.debugInfo("z.sqlDateTime(): "+z.sqlDateTime()+ " / "+z.isNull());
											/*Qry.add("AIC_Bew_Pool",iAIC_Bew);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											//if(TabSpalten.getS("Format").equals("")||TabSpalten.getS("Format").indexOf("HH")>=0)
												Qry.add("Von",z.getDate()); // DateTime
											//else
											//	Qry.add2("Von",z.sqlDate()); // Date
											Qry.add("Dauer",0);
											Qry.insert("Bew_von_bis",false);*/
                                            Import.insertBewDatum2(g,iAIC_Bewegungstyp,iAIC_Bew,iAIC_Eig,z.getDate());
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("BewMass"))
									{
										Mass m = (Mass)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										//if(m.getAbsValue()!=0.0)
										if(!m.isNull())
										{
											Qry.add("AIC_Bew_Pool",iAIC_Bew);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Spalte_Wert",m.getAbsValue());
											Qry.add("Grundwert",m.getValue());
											Qry.add("AIC_Stamm",m.getAIC());
											Qry.insert("Bew_Wert",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
                                                                        else if(sDatentyp.equals("BewMass2"))
                                                                        {
                                                                                Mass m = (Mass)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
                                                                                //if(m.getAbsValue()!=0.0)
                                                                                if(!m.isNull())
                                                                                {
                                                                                        Qry.add("AIC_Bew_Pool",iAIC_Bew);
                                                                                        Qry.add("AIC_Eigenschaft",iAIC_Eig);
                                                                                        Qry.add("AIC_Stamm",Sort.geti(TabSpalten.getS("Kennung2").substring(TabSpalten.getS("Kennung2").indexOf("_")+1)));
                                                                                        Qry.add("Wert",m.getAbsValue());
                                                                                        Qry.add("GrundWert",m.getValue());
                                                                                        Qry.add("STA_AIC_Stamm",m.getAIC());
                                                                                        Qry.insert("Bew_Spalte",false);
                                                                                        sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
                                                                                }
                                                                        }
									else if(sDatentyp.equals("Mass"))
									{
										Mass m = iArt==Stamm?(Mass)TabAenderungen.getInhalt("Neuer Wert"):(Mass)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										//if(m.getAbsValue()!=0.0)
										if(!m.isNull())
										{
											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Spalte_Double",m.getAbsValue());
											Qry.add("Sta_AIC_Stamm",m.getAIC());
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("Integer") || sDatentyp.equals("Farbe"))
									{
                                                                          //g.progInfo("Speichere "+sDatentyp);
										Object Obj = iArt==Stamm?TabAenderungen.getInhalt("Neuer Wert"):table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										if(Obj !=null &&!(Obj instanceof String))
										{
											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Spalte_Double",Sort.geti(Obj));
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.startsWith("BewWaehrung"))
									{
										Waehrung w = (Waehrung)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										//if(w.getAbsValue()!=0)
										if(!w.isNull())
										{
											Qry.add("AIC_Bew_Pool",iAIC_Bew);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
                                                                                        if(sDatentyp.equals("BewWaehrung"))
                                                                                        {
                                                                                          Qry.add("Spalte_Wert", w.getAbsValue());
                                                                                          Qry.add("AIC_Stamm",(TabSpalten.getI("bits") & Abfrage.cstKeineEinheit) > 0 ? g.iEuro : w.getAIC());
                                                                                          Qry.add("GrundWert", w.getValue());
                                                                                          Qry.insert("Bew_Wert", false);
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                          Qry.add("AIC_Stamm",Sort.geti(TabSpalten.getS("Kennung2").substring(TabSpalten.getS("Kennung2").indexOf("_")+1)));
                                                                                          Qry.add("Wert",w.getAbsValue());
                                                                                          Qry.add("STA_AIC_Stamm",(TabSpalten.getI("bits") & Abfrage.cstKeineEinheit) > 0 ? g.iEuro : w.getAIC());
                                                                                          Qry.insert("Bew_Spalte",false);
                                                                                        }
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
                                                                                        if (iAIC_Bewegungstyp==g.iWechselwaehrung)
                                                                                        {
                                                                                          int iW=w.getAIC();
                                                                                          DateWOD DW=new DateWOD(zGueltigkeit.getDate());
                                                                                          boolean bWeiter=true;
                                                                                          for (int iWW=0; bWeiter && iWW<100;iWW++)
                                                                                          {
                                                                                            String sDatum = g.DateTimeToString(DW.toDate());
                                                                                            int iAicWW = SQL.getInteger(g, "select aic_ww from ww where aic_stamm=" + iW + " and datum=" + sDatum);
                                                                                            g.progInfo(iWW+": iAicWW="+iAicWW+" bei iW="+iW);
                                                                                            if (iWW==0 || iAicWW==0 || (SQL.getInteger(g, "select bits from ww where aic_stamm=" + iW + " and datum=" + sDatum)&1)==0)
                                                                                            {
                                                                                              Qry.add2("Datum", sDatum);
                                                                                              Qry.add("aic_stamm", iW);
                                                                                              Qry.add("Faktor", w.getAbsValue());
                                                                                              Qry.add("BITS", iWW == 0 ? 1 : 0);
                                                                                              if (iAicWW > 0)
                                                                                                Qry.update("WW", iAicWW);
                                                                                              else
                                                                                                Qry.insert("WW", false);
                                                                                            }
                                                                                            else
                                                                                              bWeiter=false;
                                                                                            DW.tomorrow();
                                                                                          }
                                                                                          g.progInfo("Kurs:"+zGueltigkeit.getDate()+"/"+w.getAbsValue()+"/"+w.getAIC());
                                                                                        }
										}
									}
									else if(sDatentyp.equals("Waehrung"))
									{
										Waehrung w = iArt==Stamm?(Waehrung)TabAenderungen.getInhalt("Neuer Wert"):(Waehrung)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										//if(w.getAbsValue()!=0)
										if(!w.isNull())
										{
											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Spalte_Double",w.getAbsValue());
											Qry.add("Sta_AIC_Stamm",w.getAIC());
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("BewZahl"))
									{
										Zahl1 z = (Zahl1)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										if(!z.isNull())
										{
											Qry.add("AIC_Bew_Pool",iAIC_Bew);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("Spalte_Wert",z.getValue());
											Qry.insert("Bew_Wert",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
                                                                        else if(sDatentyp.equals("BewZahl2"))
                                                                        {
                                                                                Zahl1 z = (Zahl1)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
                                                                                if(!z.isNull())
                                                                                {
                                                                                        Qry.add("AIC_Bew_Pool",iAIC_Bew);
                                                                                        Qry.add("AIC_Eigenschaft",iAIC_Eig);
                                                                                        Qry.add("AIC_Stamm",Sort.geti(TabSpalten.getS("Kennung2").substring(TabSpalten.getS("Kennung2").indexOf("_")+1)));
                                                                                        Qry.add("Wert",z.getValue());
                                                                                        Qry.insert("Bew_Spalte",false);
                                                                                        sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
                                                                                }
                                                                        }
									else if(sDatentyp.startsWith("String")||sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") || sDatentyp.equals("Doku") || sDatentyp.equals("FixDoku")
                                                                                ||sDatentyp.equals("WWW")||sDatentyp.equals("E-Mail")||sDatentyp.equals("Memo")||sDatentyp.equals("Text")||sDatentyp.equals("Passwort"))
									{
										Object ObjWert=table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
                                                                                g.fixtestInfo("*** save "+sDatentyp+"/"+Static.className(ObjWert));
                                                                                //g.fixtestInfo(sDatentyp+":"+ObjWert+"/"+Static.className(ObjWert));
										//String s = iArt==Stamm?TabAenderungen.getS("Neuer Wert"):(sDatentyp.equals("Memo")||sDatentyp.equals("Text")?((Memo1)ObjWert).getValue():(String)ObjWert);
										String s = iArt==Stamm?TabAenderungen.getS("Neuer Wert"):ObjWert instanceof Html ? ((Html)ObjWert).getValue():ObjWert instanceof PW1 ? ((PW1)ObjWert).getPW():ObjWert.toString();
										if(!s.equals(""))
										{
											int iNr=Import.getAicDaten(g,sDatentyp,s,iAIC_Eig);

											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("AIC_Daten",iNr);
											if (sDatentyp.equals("Stringx"))
												Qry.add("Spalte_Double",s.length());
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("von_bis")||sDatentyp.equals("Auto_von_bis")||sDatentyp.equals("Zeit"))
									{
										VonBis vb = sDatentyp.equals("Zeit")?null:(VonBis)TabAenderungen.getInhalt("Neuer Wert");
										if(!vb.isNull())
										{
											int iNr=Qry.insert("Daten",true);

											if(sDatentyp.equals("Zeit"))
												Qry.add("Zeit_von",((Zeit)TabAenderungen.getInhalt("Neuer Wert")).getDate()); // DateTime
											else
											{
												if(vb.getVon()!=null)
													Qry.add2("Zeit_von",g.DateTimeToString(vb.getVon()));
												if(vb.getBis()!=null)
													Qry.add2("Zeit_bis",g.DateTimeToString(vb.getBis()));
												else if(sDatentyp.startsWith("Auto_von_bis"))
													Qry.addnow("Zeit_bis");
											}
											Qry.add("AIC_Daten",iNr);
											Qry.insert("Zeit_von_bis",false);

											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("AIC_Daten",iNr);
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("Bild"))
									{
										String s = iArt==Stamm?TabAenderungen.getS("Neuer Wert"):(String)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos());
										if(!s.equals(""))
										{
											int iNr=Qry.insert("Daten",true);

											Qry.add("Filename",s);
											Qry.add("AIC_Daten",iNr);
											Qry.insert("Daten_Image",false);

											if(iArt==Bewegung)
												Qry.add("AIC_Bew_Pool",iAIC_Bew);
											else
												Qry.add("AIC_Stamm",riAIC_Stamm);
											Qry.add("AIC_Eigenschaft",iAIC_Eig);
											Qry.add("AIC_Daten",iNr);
											Qry.add("AIC_Protokoll",iProt);
											Qry.insert("Stammpool",false);
											sSpeichernInfo+=TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+","+iAIC_Eig+")\n";
										}
									}
									else if(sDatentyp.equals("GPS"))
										Import.Save(g, (GPS)table.getCell(TabAenderungen.getI("Zeile"),TabSpalten.getPos()), iAIC_Eig, false, iAIC_Bew, iProt, true, null, false);

									Qry.free();
									if(iArt==Stamm)
										TabSpalten.moveLast();
								}
							}
						}

						///////////////////////////////////////////////////////////
						if (TabRest != null)
						{
							g.printExec("Restspeicherung",false);
							// SQL Insert=new SQL(g);
							Import.saveRest(g,Insert,TabRest,iAIC_BewOld,iAIC_Bew,iAIC_Bewegungstyp,iProt);
							// Insert.free();												
						}	
						///////////////////////////////////////////////

						g.saveInfo("\nsave:\n"+sSpeichernInfo);

						//if(iArt==Bewegung && TabAenderungen.getI("Keys")==0)
						if(iArt==Bewegung)
						{
							int iZ = TabAenderungen.getI("Zeile");
							for(;!TabAenderungen.eof() && iZ==TabAenderungen.getI("Zeile");TabAenderungen.moveNext());
								//table.setForeground(TabAenderungen.getI("Zeile"),TabAenderungen.getI("Spalte"),Color.black);
							if(!TabAenderungen.eof())
								TabAenderungen.movePrevious();
						}

					}
				}
				iZeile = TabAenderungen.getI("Zeile");
			}
      Insert.free();
                        g.testInfo("VecSave="+VecSave+", Modell="+A.iModell2);
                        boolean bError=(g.getFehler()-iError)>0;
                        boolean bCommit=true;
                        if (!bError && A.iModell2>0)
                        {
                          Fom.bCalc=true;
                          if ((g.getModellBits(A.iModell2, true)&Global.cstCommit)>0)
                          {
                        	  g.commit();
                        	  bCommit=false;
                          }
                          //Msg=new Message(Message.INFORMATION_MESSAGE+Message.UNMODAL,Fom.thisJFrame(),g,-1);
                          //Msg.showDialog("nach_speichern");
                          //Msg.setVisible(true);
                          g.printExec("Modell danach:"+g.getModellBez(A.iModell2),false);
                          Timestamp TSZeitraumVonOld=g.getVon();
                          Timestamp TSZeitraumBisOld=g.getBis();
                          g.fixtestInfo("aktueller Zeitraum: "+g.getVonBis2());
                          g.setVonBis(A.TSZeitraumVon,A.TSZeitraumBis);
                          g.fixtestInfo("Mod.n.sp.-Zeitraum: "+g.getVonBis2());
                          Calc c=new Calc((JFrame)Fom.thisFrame, g, A.iModell2, false, VecSave, 0, null, 0); // iProt); 7.3.2024 wieder raus da sonst nicht Timer gesetzt wird
                          bError=c.Fehler() != null;
                          //bError=TCalc.Berechnen(g, A.iModell,0,VecSave,Fom.thisFrame,iProt)==-1;//c.Fehler() != null;
                          g.setVonBis(TSZeitraumVonOld,TSZeitraumBisOld);
                          Fom.bCalc=false;
                          if (!bError && (c.iMBits & Global.cstRefreshM) > 0)
                            Fom.bFRefresh=true;
                        }
                        if (bCommit) g.commit2(bError);
                        if (bError)
                        {
                        	g.fixtestError(A.sDefBez+": Rollback wegen Fehler");
                        	new Message(Message.ERROR_MESSAGE,Fom.thisJFrame(),g,20).showDialog("Rollback");
                        }
                        else
                        	AClient.sendImport(g,A.iBits);
                        //if ((A.iBits & Abfrage.cstStempelimport) > 0)
                        //  AClient.send_AServer("import",g);
                        if (sTabTyp.endsWith("erf"))
                          saveS2(-2);
                        g.printExec("speichern fertig",false);
                        g.clockInfo("Save "+sDefBez,lClock,1);
		}

		TabAenderungen.clearAll();
                if (getTabSE())
                  return false;
                //g.fixtestInfo("Refresh in Save");
                //if (Msg!=null)
                //  Msg.dispose();
                if (Fom.bFRefresh)
                {
                  //Fom.thisJFrame().dispatchEvent(new WindowEvent(Fom.thisJFrame(),WindowEvent.WINDOW_ACTIVATED));
                  //actionPerformed(event);
                  //g.progInfo("Refresh in Save()");
                  Fom.hide();
                  Fom.show();
                }
      // iAIC_Stamm = riAIC_Stamm==0?iAIC_Stamm:riAIC_Stamm;//26.2. ausgeklammert
		//iAIC_Stamm=riAIC_Stamm;
                //setEnabled(iAIC_Stamm>0 || bKeinStm);
                /*if(CALC==null)
		Tab=new Tabellenspeicher(g,sAbf1+(bKeinStm ? "":" and aic_stamm="+iAIC_Stamm)+sAbf2,true);
		else
		{
			CALC.reCalc(iAIC_Stamm,true);
			Tab=CALC.getTab(iAIC_Abfrage);
		}*/
                bOkStamm=bBew && iAIC_Stamm<0 || !bBew && (bKeinStm || iAIC_Stamm>0);
                //g.fixtestInfo("bOkStamm="+bOkStamm+"/"+bBew+"/"+bKeinStm+"/"+iAIC_Stamm);
                /*if (bSave && !bSpeicherabfrage && A.iModell==0)
                {
                  g.progInfo("nicht refreshen");
                }
                else
                {*/
                  if (!bAktiv)
                  {
                    table.getParent().getParent().setVisible(true);
                    return true;
                  }
                  //g.fixInfo("fill:2");
                  //A.SQL_String();
                  //A.TabSp.showGrid("TabSp");
                  //g.fixInfo(" getDaten1 von Tabelle ------------- iStamm="+iAIC_Stamm+", VecAic="+VecAic);
                  Tab = A.getDaten(iAIC_Stammtyp, bOkStamm ? iAIC_Stamm : -1, VecAic,Fom.thisFrame);
                  //if (Tab==null)
                  //  g.fixInfo("!!!!!!!!!!!!!! Tabelleneingabe "+sDefBez+" ist null !!!!!!!!!!!!!!!!!!!!!!!");
                  if ((A.iBits & Abfrage.cstAusblenden) > 0 || Tab == null || bUnsichtbar)
                  {
                    boolean b = Tab != null && (!Tab.isEmpty() || (A.iBits & Abfrage.cstAusblenden) == 0) || Static.bDefShow;
                    bUnsichtbar = !b;
                    //g.fixtestError(A.sBez+": Tab="+Tab+", Unsichtbar="+bUnsichtbar+", Static.bDefShow="+Static.bDefShow);
                    //g.fixInfo(" ---------- AU_Table.Save ----------- :"+riAIC_Stamm+"/"+g.getVonBis("", false)+"->"+b);
                    //g.fixInfo(" ---------- AU_Table.Save ----------- :"+sDefBez+":"+b);
                    if ((A.iBits & Abfrage.cstAusblenden) > 0)
                      table.getParent().getParent().setVisible(b); // 9.9.2010 ausgeblendet, damit "manuelle Erfassung" aus Einzelüberweisungen sichtbar
                    //setEnabled(Tab!=null);
                  }
//                  if (Tab==null)
//                    g.fixtestError(A.sBez+": Tab="+Tab+", Unsichtbar="+bUnsichtbar);
                  setVisible(bSichtbar);
                  long lClock = Static.get_ms();
                  //g.progInfo("Zeile "+iZeile+" -> 0");
                  //iZeile=0;
                  Fuellen();
                  g.clockInfo("Füllen von " + sDefBez + (Tab == null ? "" : ": " + Tab.getAnzahlElemente(null)) + "/" + bSichtbar + "/" + bGauge, lClock, 1);
                  setVisible(true);
                //}
                setEnabled(bOkStamm && !bUnsichtbar);
		EnableButtons();
	}
        return true;
	//g.debugInfo("Save() Ende");
}//Save

public void Refresh()
{
  Refresh(iAIC_Stamm);
}

public void Refresh(Vector rVec,int riKnoten)
{
  g.progInfo("Refresh AUTable "+sDefBez+": "+rVec);
  iKnoten=riKnoten;
  VecAic=rVec;
  if (bAbfrage)
  {
    bAbfrage=false;
    g.setAbfrage(iD,A.iBegriff);
    iAIC_Begriff_Abfrage=A.iBegriff;
    Start(true);
  }
  else
    Save(0,true,Sort.geti(VecAic,0));
}

public void Refresh(int riAIC_Stamm)
{
  g.fixtestInfo("Refresh AUTable "+sDefBez+": "+riAIC_Stamm+"/"+g.getVonBis2());

  //g.progInfo("iMomY "+iMomY+" -> -1");
  //iMomY=-1;
	//if (!g.isAbfrageAktiv(Fom.getBegriff(),iAIC_Begriff_Abfrage))
        //g.progInfo("Tabellen-Refresh bei "+TabSpalten.getS("Bezeichnung")+":"+riAIC_Stamm);
  	if (/*!g.isAbfrageAktiv(iD) ||*/ !isShowing() && (Transact.iInfos&Transact.NO_SPEED)==0)
        {
          //setEnabled(false);
          bAktiv=false;
          if (riAIC_Stamm != 0)
            iAIC_Stamm=riAIC_Stamm;
          //return;
        }
        else
          bAktiv=true;//table.setEnabled(true);
        if (bAbfrage)
        {
          bAbfrage=false;
          g.setAbfrage(iD,A.iBegriff);
          iAIC_Begriff_Abfrage=A.iBegriff;
          Start(true);
          return;
        }
        /*
        for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
          if ((TabSpalten.getI("bits")&Abfrage.cstSpRefresh)>0)
          {
            g.testInfo("Spalten-Refresh bei "+TabSpalten.getS("Bezeichnung"));
            AUComboList Cbo=(AUComboList)TabSpalten.getInhalt("Component");
            int iAic=Cbo.getComboBox().getSelectedAIC();
            Cbo.fillCbo(true);
            Cbo.getComboBox().setSelectedAIC(iAic);
          }
         */
        long lClock = Static.get_ms();

	Save(0,true,riAIC_Stamm);
        iMomX=-1;
        iMomY=-1;
        g.clockInfo2("Tab "+A.sDefBez,lClock);
	//g.debugInfo("Refresh() Ende");
}

public void actionPerformed(ActionEvent e)
{
	if(timer!=null && bAutorefresh && Fom.thisFrame.isShowing() && ((Frame)Fom.thisFrame).getState()==Frame.NORMAL && !Modified())
	{
		g.progInfo("*** Autorefresh ***");
		bAutorefresh=false;
		timer.stop();
		Refresh();
		timer.start();
	}
	bAutorefresh=true;
}

/*private Tabellenspeicher getTabAenderungen()
{
	return(new Tabellenspeicher (g,TabAenderungen));
}*/

private int getSpalten2ANR()
{
         return iKnoten>0 ? iKnoten:iAIC_Stamm;
}

private void Build()
{
	//g.progInfo("AUTable.Build");

	/*BtnNew = g.getButton2("tab_new");
        BtnDelete = g.getButton2("tab_del");
	BtnSave = g.getButton2("tab_save");
        if (sTabTyp.endsWith("erf"))
        {
          BtnSEp = g.getButton2("SE_dazu");
          BtnSEm = g.getButton2("SE_weg");
          BtnSEe = g.getButton2("SE_edit");
        }*/

	//BtnRefresh = g.getButton2("tab_refr");
        //JButton BtnHelp=g.getButton2("tab_help");
        //BtnZeilen = g.getButton("tab_rows");
        //BtnHide = g.getButton2("tab_hide");
        //BtnShowDel = g.getButton("tab_sdel");
	//BtnInit = g.getButton("tab_init");
	//BtnHistory = g.getButton("tab_his");
	//BtnParameter = g.getButton("tab_para");
	//BtnParaOk = g.getButton("Ok");
        //BtnParaLoeschen = g.getButton("Loeschen");
	//BtnParaAbbruch = g.getButton("Abbruch");
        /*BtnNew.setBackground(g.ColTable);
        //BtnNew.setOpaque(true);
        //BtnNew.setUI(new com.sun.java.swing.plaf.motif.MotifButtonUI());
        BtnDelete.setBackground(g.ColTable);
        //BtnDelete.setOpaque(false);
        //BtnDelete.setUI(new com.sun.java.swing.plaf.motif.MotifButtonUI());
        BtnUndelete.setBackground(g.ColTable);
        //BtnUndelete.setOpaque(true);
        BtnSave.setBackground(g.ColTable);
        //BtnSave.setOpaque(false);
        //BtnSave.setUI(new com.sun.java.swing.plaf.motif.MotifButtonUI());
        BtnRefresh.setBackground(g.ColTable);
        //BtnZeilen.setBackground(g.ColTable);
        BtnHide.setBackground(g.ColTable);
        BtnHelp.setBackground(g.ColTable);*/
        //BtnRefresh.setUI(new com.sun.java.swing.plaf.motif.MotifButtonUI());
        //BtnShowDel.setBackground(g.ColTable);
        //BtnInit.setBackground(g.ColTable);
        //BtnHistory.setBackground(g.ColTable);
        //BtnParameter.setBackground(g.ColTable);

        //BtnParaSpeichern.setBackground(g.ColTable);
        //BtnParaLoeschen.setBackground(g.ColTable);
        //BtnParaBeenden.setBackground(g.ColTable);


	//JPanel Pnl = new JPanel(new GridLayout(1,0,2,2));
        /*JToolBar TBl=new JToolBar();
        TBl.setRollover(true);
        TBl.setFloatable(false);
	//g.progInfo("AUTable.iBitsAbfrage="+iBitsAbfrage);
        TBl.setBackground(g.ColTable);
	if( !Fom.ReadOnly())
	{
          //BtnNew.setMargin(new java.awt.Insets(0,5,0,5));
          //BtnNew.setMinimumSize(new java.awt.Dimension(25,16));
          //BtnDelete.setMargin(new java.awt.Insets(0,5,0,5));
          //BtnDelete.setMinimumSize(new java.awt.Dimension(25,16));
          //BtnUndelete.setMargin(new java.awt.Insets(0,5,0,5));
          //BtnUndelete.setMinimumSize(new java.awt.Dimension(25,16));
          //TBl.add(BtnSuche);
          //TBl.addSeparator();
          TBl.add(BtnNew);
          TBl.addSeparator();
          TBl.add(BtnDelete);
          TBl.addSeparator();
          TBl.add(BtnUndelete);
          TBl.addSeparator();
          TBl.add(BtnSave);
          TBl.addSeparator();
	}
        TBl.add(BtnRefresh);
        TBl.addSeparator();
        //Pnl.add(BtnZeilen);
        BtnHide.setBorder(null);
        TBl.add(BtnHide);
        TBl.addSeparator();
        BtnHelp.setBorder(null);
        TBl.add(BtnHelp);*/
        /*if(g.Geloeschte())
            Pnl.add(BtnShowDel);
	if(g.History())
		Pnl.add(BtnHistory);
	if(g.Abfrage())
	{
          if (g.Def() || (A.iBits&A.cstNoChange)==0)
          {
            BtnAbfrage = g.getButton("tab_fil");
            BtnAbfrage.setBackground(g.ColTable);
            Pnl.add(BtnAbfrage);
          }
          Pnl.add(BtnInit);
	}
	Pnl.add(BtnParameter);*/

	setLayout(new BorderLayout(2,2));
	add("Center",table);
	//if(!Abfrage.is(iBitsAbfrage,Abfrage.cstKeineLeiste))
	//if (Static.bLeiste)
	//	add("South",TBl);

	/*if(Abfrage.is(iBitsAbfrage,Abfrage.cstTitelzeile))
	{
		String s=g.TabBegriffe.getBezeichnung(iAIC_Begriff_Abfrage);
		JLabel Lbl = new JLabel(s);
		Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Lbl.setFont(fontTitel);
		Lbl.setForeground(g.ColTitel);
		add("North",Lbl);
	}*/


	/*CbxSpaltenbreite = new JCheckBox("Spaltenbreite",true);
	CbxFilter = new JCheckBox("Filter",true);
	FrmParameter = new JDialog((Frame)null,true);
	FrmParameter.setTitle(g.getBegriffS("Dialog","Parameter"));
	FrmParameter.getContentPane().setLayout(new BorderLayout(2,2));
        FrmParameter.getRootPane().setDefaultButton(BtnParaOk);
	JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
	Pnl.add(CbxSpaltenbreite);
	Pnl.add(CbxFilter);
	FrmParameter.getContentPane().add("Center",Pnl);
	Pnl = new JPanel(new GridLayout(1,0,2,2));
	Pnl.add(BtnParaOk);
	Pnl.add(BtnParaLoeschen);
	Pnl.add(BtnParaAbbruch);
	FrmParameter.getContentPane().add("South",Pnl);
	FrmParameter.setSize(300,100);*/

	/*BtnParameter.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Static.centerComponent(FrmParameter,thisComponent);
			FrmParameter.setVisible(true);
		}
	});*/

	/*BtnParaAbbruch.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			FrmParameter.setVisible(false);
		}
	});

	BtnParaOk.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
                  SQL Qry=new SQL(g);
                  int iNeben=0;
			if (CbxSpaltenbreite.isSelected()||CbxFilter.isSelected())
				for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
				{
					Object ObjComponent = TabSpalten.getInhalt("Component");
					boolean bVorhanden=TabColumn.posInhalt((Object)("AUTable"+iAIC_Begriff_Abfrage),TabSpalten.getInhalt("nummer"));

					//ParaColumn.clear();
					if(!bVorhanden)
					{
						//ParaColumn.addGruppen();
                                                int iHaupt=SQL.getInteger(g,"select aic_hauptgruppe from hauptgruppe where kennung='AUTable"+iAIC_Begriff_Abfrage+"'");
                                                if (iHaupt==0)
                                                {
                                                  Qry.add("Kennung","AUTable"+iAIC_Begriff_Abfrage);
                                                  iHaupt=Qry.insert("Hauptgruppe",true);
                                                }
                                                Qry.add("aic_Hauptgruppe",iHaupt);
                                                if (iNeben==0)
                                                  iNeben=SQL.getInteger(g,"select aic_nebengruppe from nebengruppe where kennung='SpaltenNeu'");
                                                Qry.add("aic_Nebengruppe",iNeben);
						Qry.add("AIC_Benutzer",g.getBenutzer());
						Qry.add("int1",TabSpalten.getI("nummer"));
						Qry.add("AIC_Logging",g.getLog());
					}
					if (CbxSpaltenbreite.isSelected() || !bVorhanden)
						Qry.add("int2",CbxSpaltenbreite.isSelected()?table.getColumnPixelWidth(TabSpalten.getPos()):0);
					if (CbxFilter.isSelected() || !bVorhanden)
						Qry.add("int3",CbxFilter.isSelected()&&ObjComponent instanceof AUComboList?((AUComboList)ObjComponent).getFilter():-1);
                                        Qry.add("int4",(MnuAsk!=null && MnuAsk.isSelected()?1:0)+(MnuWeiter.isSelected()?2:0)+(bSichtbar?4:0)+(bGauge?8:0));

					if(bVorhanden)
						Qry.update("Parameter",TabColumn.getI("AIC_Parameter"));
					else
					{
                                          Qry.insert("Parameter",false);
						//TabColumn.putInhalt("AIC_Parameter",Qry.insert("Parameter",true),!bVorhanden);
						//TabColumn.putInhalt("int1",TabSpalten.getI("nummer"),!bVorhanden);
					}
					//if (CbxSpaltenbreite.isSelected() || !bVorhanden)
					//	TabColumn.putInhalt("int2",CbxSpaltenbreite.isSelected()?table.getColumnPixelWidth(TabSpalten.getPos()):0,!bVorhanden);
					//if (CbxFilter.isSelected() || !bVorhanden)
					//	TabColumn.putInhalt("int3",CbxFilter.isSelected()&&ObjComponent instanceof AUComboList?((AUComboList)ObjComponent).getFilter():-1,!bVorhanden);
				}
                      Qry.free();
                      readColumns(g);
                      FrmParameter.setVisible(false);
		}
	});

	BtnParaLoeschen.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
                  if (new Message(Message.YES_NO_OPTION,null,g).showDialog("Parameter_loeschen")==Message.YES_OPTION)
                  {
                    if(CbxSpaltenbreite.isSelected() && CbxFilter.isSelected())
                      g.exec("delete from Parameter where aic_parameter in (select aic_parameter from Hauptgruppe h join Parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on p.aic_nebengruppe=n.aic_nebengruppe where n.kennung ='SpaltenNeu' and h.kennung='AUTable" + iAIC_Begriff_Abfrage +
                             "' and Aic_Benutzer=" + g.getBenutzer()+")");
                    else if(CbxFilter.isSelected())
                      g.exec("update Parameter SET int3=-1 from Parameter where aic_parameter in (select aic_parameter from Hauptgruppe h join Parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where n.kennung ='SpaltenNeu' and h.kennung='AUTable" + iAIC_Begriff_Abfrage +
                             "' and Aic_Benutzer=" + g.getBenutzer()+")");
                    else if(CbxSpaltenbreite.isSelected())
                      g.exec("update Parameter SET int2=0 from Parameter where aic_parameter in (select aic_parameter from Hauptgruppe h join Parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where n.kennung ='SpaltenNeu' and h.kennung='AUTable" + iAIC_Begriff_Abfrage +
                             "' and Aic_Benutzer=" + g.getBenutzer()+")");

                    readColumns(g);
                    FrmParameter.setVisible(false);
                    Start(true);
                  }
		}
	});*/

	table.addTraverseCellListener(eventJCTCL=new JCTraverseCellListener()
	{
		public void traverseCell(JCTraverseCellEvent e)
		{
			//if(!bFirst2) // ??? 31.5.2007
			{
                          //g.testInfo("traverseCell");
				TraverseCell(e);
			}
			//else
			//	bFirst2=false;
			//EnterCell(iOldY,iOldX,e.getRow(),e.getColumn());
		}
	});

        table.addMouseWheelListener(new MouseWheelListener()
                {
                  public void mouseWheelMoved(MouseWheelEvent e) {
                    //table.getVertSB().setValue(table.getVertSB().getAdjustable().getValue()+e.getWheelRotation()*100);
                    TableScrollbar TSB = table.getVertSB();
                    int i=TSB.getAdjustable().getValue()+e.getWheelRotation()*20;
                    int iMax=TSB.getAdjustable().getMaximum()-TSB.getAdjustable().getVisibleAmount();
                    TSB.setValue(i<0 ? 0:i>iMax?iMax:i);
                    table.validate();
                  }
                });


        table.addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev)
          {
            //g.fixtestInfo("mousePressed");
          }
          public void mouseClicked(MouseEvent ev)
          {
            //g.fixtestInfo("mouseClicked:"+ev.getButton()+","+ev.getModifiersEx()+"/"+Global.iRM+","+ev.getClickCount());
            //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
            //g.testInfo("mouseClicked:"+ev.getButton()+","+ev.getModifiersEx()+"/"+Global.iRM+","+ev.getClickCount());
            if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==2048)
            {
              //g.testInfo("popup");
              if (MnuM1 != null)
            	  MnuM1.setSelected(Aufruf.VecModelle!= null && Aufruf.VecModelle.contains(g.ModellToBegriff(A.iModell)));
              if (MnuM2 != null)
            	  MnuM2.setSelected(Aufruf.VecModelle!= null && Aufruf.VecModelle.contains(g.ModellToBegriff(A.iModell2)));
              popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
              //g.fixInfo("tableXY="+table.getX()+"/"+table.getY()+"/"+table.getNumColumnTriggers());
            }
            else if (ev.getModifiers() == MouseEvent.BUTTON1_MASK && ev.getClickCount() == 2)
            {
              //g.testInfo("doppelklick:" + iMomX + "/" + iMomY);
              EnterNext();
            }
            else if (!bEdit)
              g.setTooltip(getInfo(true),table);
          }
          public void mouseEntered(MouseEvent ev)
          {
          }
          public void mouseExited(MouseEvent ev)
          {
          }
          public void mouseReleased(MouseEvent ev)
          {
          }
        });

        addKeyListener(new KeyListener()
        {
          public void keyPressed(KeyEvent e)
          {}

          public void keyReleased(KeyEvent e)
          {
            int iKey=e.getKeyCode();
            g.fixInfo(sDefBez+".Rel="+iKey);
          }

          public void keyTyped(KeyEvent e)
          {
            char cKey = e.getKeyChar();
            g.fixtestInfo(sDefBez+".key2="+cKey);
          }
        });


        table.addKeyListener(new KeyListener()
        {
          public void keyPressed(KeyEvent e) {}

          public void keyReleased(KeyEvent e)
          {
            int iKey=e.getKeyCode();
            g.fixtestInfo("Key="+iKey);
            /*if (iKey==KeyEvent.VK_RIGHT)
              table.actionPerformed();*/
            if (iKey==Global.iPopkey)
              popup.show((Component)e.getSource(),0,0);
            else if(iKey==KeyEvent.VK_F1)
            {
              g.getBegriffS("Button","tab_help");
              if (!g.TabBegriffe.out())
                g.OpenURL(g.TabBegriffe.getS("URL"));
            }
          }

          public void keyTyped(KeyEvent e)
          {
            char cKey = e.getKeyChar();
            g.fixtestInfo(sDefBez+".key="+cKey);
            if (bEdit)
            {
              if (cKey == 13 || cKey == 10 || cKey == 32)
                EnterNext();
              else if (cKey == 'n' && BtnNew.isEnabled())
                Neu();
              else if (cKey == 'd' && BtnDelete.isEnabled())
                if (bUndelete)
                  Undelete();
                else
                  Loeschen();
              else if (g.Def() && cKey== 'ä')
                TabAenderungen.showGrid("Änderungen");
              else if (cKey== 'v')
              {
                g.fixInfo("pos="+iMomX+"/"+iMomY+", VecSperre="+VecSperre);
              }
              //else if (cKey == 'u' && BtnUndelete.isEnabled())
              //  Undelete();
            }
            if (cKey == 'h')
              History();
            else if (cKey == 'f')
              suchen(table.getLocationOnScreen());
            //else if (cKey == 'p')
            //  Parameter();
            else if (cKey == 'r' || cKey=='s')
            {
              int iX=iMomX;
              int iY=iMomY;
              if (cKey == 'r' || Modified())
                Refresh();
              table.traverse(iY,iX,true,true);
              iMomX=iX;
              iMomY=iY;
            }
            else if (cKey == 'i')
              ZeilenInfo();
            //else if (cKey == 'x')
            //  g.fixInfo("aktuelle Zeilen: ?");
            else if ((cKey == 'c' || cKey == 'C') && MnuCalc != null)
              Calc(cKey == 'C');
            else if (cKey == 'e')
              EMail(iMomY);
          }
        });

        /*table.addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev){}

          public void mouseClicked(MouseEvent ev)
          {

          }

          public void mouseEntered(MouseEvent ev){}
          public void mouseExited(MouseEvent ev) {}
          public void mouseReleased(MouseEvent ev) {}
        });*/

        /*table.addMouseMotionListener(new MouseMotionListener()
        {
          public void mouseDragged(MouseEvent ev)
          {
          }
          public void mouseMoved(MouseEvent ev)
          {
          }
        });*/

        AL = new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            String s = e.getActionCommand();
            g.progInfo("AUTable.Action=" + s);
            if (s.equals("New") || s.equals("tab_new"))
              Neu();
            else if (s.equals("tab_del"))
              Loeschen();
            else if (s.equals("tab_undel"))
              Undelete();
            else if (s.equals("Delete"))
              if (bUndelete)
                Undelete();
              else
                Loeschen();
            else if (s.equals("Save") || s.equals("tab_save"))
              Save(0,false,iAIC_Stamm);
            else if (s.equals("Drucken"))
              Drucken();
            else if (s.equals("tab_refr"))
              Refresh();
            else if (s.equals("Spalte_weg"))
              SpalteWeg();
            else if (s.equals("Spalte_edit") || s.equals("Spalte_dazu"))
              EditSpalte(s.equals("Spalte_dazu"));
            else if (s.equals("Spalte_<") || s.equals("Spalte_>"))
              SpalteWeiter(s.equals("Spalte_>"));
            else if (s.equals("EditOk"))
              EditOk();
            else if (s.equals("EditAbbruch"))
              DlgEditSp.setVisible(false);
            else if (s.equals("History"))
              History();
            else if (s.equals("Info"))
              ZeilenInfo();
            else if (s.startsWith("Init_SE"))
            {
              boolean bThis=s.equals("Init_SE_this") || s.equals("Init_SE_leer");
              if (bThis && !keineDatenAll())
                new Message(Message.WARNING_MESSAGE,DlgEditSp,g,10).showDialog("Daten_vorhanden");
              else if (new Message(Message.YES_NO_OPTION, null, g).showDialog(s)==Message.YES_OPTION)
              {
                g.exec("delete from spalten2 where aic_abfrage=" + A.iAbfrage + (s.equals("Init_SE_All") ? "" : " and anr=" + getSpalten2ANR()) +
                       (bThis ? " and datum=" + g.DateTimeToString(g.getVon()) : ""));
                bLeer = s.equals("Init_SE_leer");
                iAIC_Stamm_Old = -1;
                Save(0, true, iAIC_Stamm);
                //Start(true);
                bLeer=false;
                iAIC_Stamm_Old = -1;
              }
            }
            else if (s.startsWith("List_SE"))
              new Tabellenspeicher(g,"select distinct spalten2.anr,Bezeichnung,datum from spalten2 join stammview v on spalten2.anr=v.aic_stamm and v.aic_rolle is null where aic_abfrage=" +A.iAbfrage+
                                   (s.equals("List_SE_this")?" and spalten2.anr=" + iAIC_Stamm:s.equals("List_SE_group")?" and spalten2.anr=" + iKnoten:"")+" order by Bezeichnung,datum",true).showGrid("Liste");
            //else if (s.equals("set-1"))
            //  table.setFrozenColumns(-1);
            else if (s.equals("Abfrage easy"))
            {
              DefAbfrage2 DefAbf = DefAbfrage2.get(g, DefAbfrage2.TABELLE, A, (JFrame)Fom.thisFrame);
              if (DefAbf.bOk)
              {
                A = DefAbf.A;
                A.SQL_String();
                Start(true);
              }
            }
            else if (s.equals("Abfrage") || s.equals("Abfrage2"))
            {
              bAbfrage = true;
              All_Unlimited.Grunddefinition.DefAbfrage.get(g, A, iAIC_Bewegungstyp > 0 ? -iAIC_Bewegungstyp : iAIC_Stammtyp, null, false, s.equals("Abfrage") ? 0:1).show();
            }
            else if (s.equals("Init"))
            {
              A = null;
              sDefBez = "";
              Vector Vec = SQL.getVector("select aic_parameter from Hauptgruppe h join Parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on p.aic_nebengruppe=n.aic_nebengruppe where n.kennung ='SpaltenNeu' and h.kennung='AUTable" +
                                         iAIC_Begriff_Abfrage +"' and Aic_Benutzer=" + g.getBenutzer(), g);
              if (Vec != null && Vec.size() > 0)
                g.exec("delete from Parameter where aic_parameter " + Static.SQL_in2(Vec));
              TabColumn = null;
              g.setAbfrage(iD, Fom.FirstAbfrage(iD));
              dtMoa = null;
              Start(true);
              dtMoa = bBew ? null : g.getAbschlussDate(/*Fom.getBegriff(),*/ iAIC_Bewegungstyp, iAIC_Stamm);
            }
            else if (s.equals("Spaltenbreite"))
              showColumns();
            else if (s.startsWith("set"))
            {
              if (s.equals("setEdit"))
                Refresh();
              checkZeilen(s.equals("setInfo"));
            }
            else if (s.equals("Alle"))
              table.setSelectedCells(new JCCellRange(0,0,table.getNumRows()-1,1));
            else if (s.equals("Calc"))
              Calc(false);
            else
              g.fixInfo(s+" gedrückt");
          }
        };

        if (bSperre)
        {
          BtnInfo = g.getTButton("InfoP", "setInfo", AL);
          BtnEdit = g.getTButton("EditP", "setEdit", AL);
          ButtonGroup RadGroup=new ButtonGroup();
          RadGroup.add(BtnInfo);
          RadGroup.add(BtnEdit);
          BtnInfo.setSelected(true);
        }

        BtnAlle = g.getButton2("tab_Alle","Tb_Alle", "Alle", AL,"Button");
        BtnCalc = g.getButton2("tab_Calc","Calc", "Calc", AL,"Button");

        BtnNew = g.getButton2("tab_new",null,"New",AL,"Button");
        BtnDelete = g.getButton2("tab_del",null,"Delete",AL,"Button");
        BtnSave = g.getButton2("tab_save",null,"Save",AL,"Button");
        BtnDruck = g.getButton2("tab_druck",null,"Drucken",AL,"Button"); // war Druck
        //g.BtnAdd(BtnNew,"New",AL);
        //g.BtnAdd(BtnDelete,"Delete",AL);
        //g.BtnAdd(BtnSave,"Save",AL);
        if (sTabTyp.endsWith("erf"))
        {
          BtnSEp = g.getButton2("SE_dazu",null, "Spalte_dazu", AL,"Button");
          BtnSEm = g.getButton2("SE_weg",null, "Spalte_weg", AL,"Button");
          BtnSEe = g.getButton2("SE_edit",null, "Spalte_edit", AL,"Button");
          BtnSEl = g.getButton2("SE_links",null, "Spalte_<", AL,"Button");
          BtnSEr = g.getButton2("SE_rechts",null, "Spalte_>", AL,"Button");
        }
        /*if (BtnAbfrage != null)
          BtnAbfrage.addActionListener(new ActionListener()
          {
		public void actionPerformed(ActionEvent e)
		{
			bAbfrage=true;
			All_Unlimited.Grunddefinition.DefAbfrage.get(g,A,iAIC_Bewegungstyp>0?-iAIC_Bewegungstyp:iAIC_Stammtyp).show();
		}
          });
	BtnInit.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
                  A=null;
                  g.setAbfrage(Fom.getBegriff(),iAIC_Begriff_Abfrage,iAIC_Begriff_Abfrage);
                  dtMoa=null;
                  Start(true);
                  dtMoa=bBew ? null:g.getAbschlussDate(Fom.getBegriff(),iAIC_Bewegungstyp,iAIC_Stamm);
		}
	});*/
	bFirst=false;
}

      private void ZeilenInfo()
      {
        JDialog Dlg=new JDialog(Fom.thisJFrame(),"Zeilen-Info",false);
        JEditorPane TxtInfo= new JEditorPane();
        JScrollPane Scroll=new JScrollPane(TxtInfo);
        Color Col=new java.awt.Color(249,249,249);
        Scroll.setBackground(Col);
        Scroll.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
        TxtInfo.setEditable(false);
        TxtInfo.setContentType("text/html");
        //TxtInfo.setFont(g.fontStandard);
        TxtInfo.setText("<span style=\"font-family:"+Static.sFont+"\">"+getInfo(false)+"</span>");
        //JLabel Lbl=new JLabel(g.toHtml(getInfo()));
        Dlg.getContentPane().add(Scroll);
        Dlg.pack();
        Dlg.setVisible(true);
        /*MemoF mem = new MemoF(Fom.thisJFrame(),"Zeilen-Info", g);
        mem.Txt.setText(getInfo());
        mem.Txt.setEditable(false);
            mem.show2();*/
      }

private String getInfo(boolean b)
{
  String s="";
  int iRow=bEdit ? iMomY:getRow();//((Integer)table.getSelectedCells().elementAt(0)).intValue();
  for (int i = 0; i < TabSpalten.size(); i++)
    if ((TabSpalten.getI(i,"bits")&Abfrage.cstUnsichtbar)==0)
    {
      Object sCell=table.getCell(iRow, i);
      if (sCell!=null && !sCell.toString().trim().equals(Static.sLeer))
        s += (b?"<p width=\"400px\"><b>":"<b>") + Static.cin(TabSpalten.getS(i, "Bezeichnung"), ":") + "</b> " + g.getHtmlPart(sCell.toString(),true) + (b?"</p>":"<p>");//sCell.toString().replaceAll("\n", "<br>")
    }
  return s;
}


private Vector<Integer> getVecRel()
{
  Vector<Integer> Vec= new Vector<Integer>();
  int iSpalte=TabSpalten.getI("Aic");
  for (int i=0;i<A.TabSp.size();i++)
    if (A.TabSp.getI(i,"aic_spalte")==iSpalte)
      Vec.addElement(A.TabSp.getI(i,"rel"));
  g.progInfo("VecRel="+Vec);
  return Vec;
}

private void SpalteWeiter(boolean bWeiter)
{
  if (TabSpalten.out())
  {
    g.fixtestInfo("SpalteWeiter: Keine Spalte angewählt!");
    return;
  }
  int i_=TabSpalten.getS("Kennung2").indexOf("_");
  int iRel=i_<0 ? 0:Sort.geti(TabSpalten.getS("Kennung2").substring(i_+1));
  TabSE.posInhalt("Datum",dt_Old);
  //TabSE.moveBefore();
  TabSE.posGroup(iRel == 0 ? "aic_spalte":"aic_stamm",iRel == 0 ? TabSpalten.getI("Aic"):iRel,null,null,false);
  int iMomS=TabSE.getI("aic_spalten2");
  int iMomR=TabSE.getI("Reihe");
  int iMomR2=bWeiter ? iMomR+1:iMomR-1;
  if (bWeiter) TabSE.moveNext(); else TabSE.movePrevious();
  int iMomS2=TabSE.getI("aic_spalten2");
  //int iTemp=2000000000+g.getLog();
  g.fixtestInfo("Tausche "+iMomR+"/"+iMomR2+" bei "+iMomS+"/"+iMomS2);
  //g.fixInfo(TabSpalten.getS("Bezeichnung")+"<"+iMom+"> "+(bWeiter ? "auf nächste Position":"auf vorherige Position"));
  g.exec("update spalten2 set reihe="+iMomR2+" where aic_spalten2="+iMomS);
  g.exec("update spalten2 set reihe="+iMomR+" where aic_spalten2="+iMomS2);
  //g.exec("update spalten2 set aic_spalten2="+iMom2+" where aic_spalten2="+iTemp);
  iAIC_Stamm_Old=-1;
  Save(0,true,iAIC_Stamm);
}

private void EditSpalte(boolean rbNeu)
{
  bNeueSpalte=rbNeu;
  if (DlgEditSp==null)
  {
    DlgEditSp=new JDialog(Fom.thisJFrame(),"xx",true);
    DlgEditSp.getContentPane().setLayout(new BorderLayout(2,2));
    JPanel Pnl=new JPanel(new BorderLayout(2,2));
     JPanel PnlW=new JPanel(new GridLayout(0,1,2,2));
       g.addLabel4(PnlW,"Stamm");
       g.addLabel4(PnlW,"Titel");
      //if (g.Prog())
        g.addLabel4(PnlW,"Breite");
      g.addLabel4(PnlW,"Format");
      g.addLabel4(PnlW,"Farbe");

     Pnl.add("West",PnlW);
     JPanel PnlC=new JPanel(new GridLayout(0,1,2,2));
      EdtTitel2=new Text("",99);
      EdtBreite = new SpinBox();
      EdtFormat=new Format(g,"Spalte",DlgEditSp);
      CboFarbe=new ComboSort(g);
      CboFarbe.fillFarbe(false);
      CboStamm=new AUComboList(DlgEditSp,Fom.getBegriff(),0,0,0,g,0,true,true,true,false);
      CboStamm.setEnabled(false);
      CboStamm.getComboBox().addItemListener(new ItemListener () {
        public void itemStateChanged(ItemEvent ev)
        {
          if (ev.getStateChange() == ItemEvent.SELECTED && DlgEditSp.isVisible())
          {
            EdtTitel2.setText(CboStamm.getComboBox().getSelectedBezeichnung());
          }
        }
      });
      PnlC.add(CboStamm);
      PnlC.add(EdtTitel2);
      //if (g.Prog())
        PnlC.add(EdtBreite);
      PnlC.add(EdtFormat);
      PnlC.add(CboFarbe);
     Pnl.add("Center",PnlC);
    DlgEditSp.getContentPane().add("North",Pnl);
     Pnl=new JPanel(new GridLayout(1,0,2,2));
      JButton BtnEditOk=g.getButton("Ok");
      DlgEditSp.getRootPane().setDefaultButton(BtnEditOk);
      JButton BtnEditAbbruch=g.getButton("Abbruch");
      Pnl.add(BtnEditOk);
      Pnl.add(BtnEditAbbruch);
      g.BtnAdd(BtnEditOk,"EditOk",AL);
      g.BtnAdd(BtnEditAbbruch,"EditAbbruch",AL);
    DlgEditSp.getContentPane().add("South",Pnl);
    DlgEditSp.pack();
    Static.centerComponent(DlgEditSp,Fom.thisJFrame());
  }
  DlgEditSp.setTitle(g.getShow("Spalte")+" "+g.getShow(bNeueSpalte ? "Neu":"Edit2"));
  if (!bNeueSpalte && TabSpalten.out())
  {
    g.fixtestInfo("Keine Spalte angewählt!");
    return;
  }
  //g.fixInfo(DlgEditSp.getTitle()+"/"+TabSpalten.getS("Bezeichnung")+"("+TabSpalten.getS("Kennung")+"): "+sAktDatentyp+"/"+sFormat);
  EdtTitel2.setText(TabSpalten.getS("Bezeichnung"));
  EdtBreite.setIntValue(table.getPixelWidth(TabSpalten.getPos()));
  EdtFormat.setTyp(sAktDatentyp,null,true);
  EdtFormat.setText(sFormat);
  CboFarbe.setSelectedAIC(TabSpalten.getI("Farbe"));
  CboStamm.setEnabled(false);
  CboStamm.getComboBox().setSelectedAIC(0);
  //boolean bSE=Abfrage.VRel.contains(sAktDatentyp);
  //Vector<Integer> VecRel=null;
  if (Abfrage.VRel.contains(sAktDatentyp))// && (rbNeu || keineDaten()))
  {
    int iPosE = g.TabEigenschaften.getPos("AIC", Eig(sAktDatentyp));
    if (iPosE>=0)
    {
      //VecRel=getVecRel();
      CboStamm.setEnabled(true);
      CboStamm.setStt(g.TabEigenschaften.getI(iPosE, "aic_stammtyp"),TabSpalten.getI("Filter"));
      int iRel=Sort.geti(TabSpalten.getS("Kennung2").substring(TabSpalten.getS("Kennung2").indexOf("_")+1));
      CboStamm.getComboBox().setSelectedAIC(iRel);
      if (bNeueSpalte)
        CboStamm.getComboBox().requestFocus();
      else if (!keineDaten())
        CboStamm.setEnabled(false);
    }
  }
  if (rbNeu)
  {
    CboStamm.OpenList();
    if (CboStamm.bOk && !getVecRel().contains(CboStamm.getAIC()))
    {
      EdtTitel2.setText(CboStamm.getComboBox().getSelectedBezeichnung());
      EditOk();
    }
    else if (CboStamm.bOk)
      new Message(Message.WARNING_MESSAGE,DlgEditSp,g,10).showDialog("schon_vorhanden", new String[] {CboStamm.getComboBox().getSelectedBezeichnung()});
      //g.fixInfo(CboStamm.bOk? "schon vorhanden":"Abbruch -> nicht gespeichert");
  }
  else
    DlgEditSp.setVisible(true);
}

private void SpalteWeg()
{
  if (table.getPixelWidth(TabSpalten.getPos())!=0)
  {
    g.fixtestInfo("SpalteWeg: " + TabSpalten.getS("Bezeichnung"));
    TabSpalten.setInhalt("bits", TabSpalten.getI("bits") + Abfrage.cstUnsichtbar);
    int iRel=Sort.geti(TabSpalten.getS("Kennung2").substring(TabSpalten.getS("Kennung2").indexOf("_")+1));
    if (iRel == 0)
      A.TabSp.posInhalt("aic_spalte", TabSpalten.getI("Aic"));
    else
      A.TabSp.posInhalt("rel", iRel);
    if ((A.TabSp.getI("bits") & Abfrage.cstUnsichtbar) == 0)
      A.TabSp.setInhalt("bits",A.TabSp.getI("bits")+Abfrage.cstUnsichtbar);
    table.setPixelWidth(TabSpalten.getPos(), 0);
    saveS2(iRel);
    //dt_Old=null;
    iAIC_Stamm_Old=-1;
    Save(0,true,iAIC_Stamm);
    //getTabSE();
  }
}

private void EditOk()
{
  if (CboStamm.Modified() && getVecRel().contains(CboStamm.getAIC()))
  {
    //g.fixInfo(CboStamm.getComboBox().getSelectedBezeichnung()+" ist schon vorhanden!");
    new Message(Message.WARNING_MESSAGE,DlgEditSp,g,10).showDialog("schon_vorhanden", new String[] {CboStamm.getComboBox().getSelectedBezeichnung()});
    return;
  }
  int iOld=CboStamm.getComboBox().getOld();
  if (iOld==0)
    A.TabSp.posInhalt("aic_spalte", TabSpalten.getI("Aic"));
  else
    A.TabSp.posInhalt("rel", iOld);
  if (bNeueSpalte)
  {
    iOld=-1;
    //A.TabSp.bInsert=true;
    A.TabSp.copyLine2();
    //A.TabSp.moveNext();
    //A.TabSp.bInsert=false;
    //A.TabSp.setInhalt("Nummer",A.TabSp.max("Nummer")+1); // Nummer muss gleich bleiben
  }
  else
  {
    table.setPixelWidth(TabSpalten.getPos(), EdtBreite.getIntValue());
    TabSpalten.setInhalt("Bezeichnung", EdtTitel2.getText());
    TabSpalten.setInhalt("Format", EdtFormat.getText());
    TabSpalten.setInhalt("Farbe", CboFarbe.getSelectedAIC());

  }
  A.TabSp.setInhalt("Bezeichnung",EdtTitel2.getText());
  if (CboStamm.Modified())
    A.TabSp.setInhalt("rel",CboStamm.getAIC());
  A.TabSp.setInhalt("aic_begriff",EdtFormat.getAIC());
  A.TabSp.setInhalt("aic_farbe",CboFarbe.getSelectedAIC());
  if (EdtBreite.getIntValue()>0)
    A.TabSp.setInhalt("Laenge",Abfrage.getLaenge(A.TabSp)/*+Abfrage.getLaengeD(A.TabSp)*512*/+EdtBreite.getIntValue()*512*1024);
  saveS2(iOld);
  //A.TabSp.showGrid("TabSp vor neuladen");
  //if (bNeueSpalte || CboStamm.Modified()) // entfernt 25.9.2014
  {
    //Start(true);
    iAIC_Stamm_Old=-1;
    Save(0,true,iAIC_Stamm);
    //getTabSE();
  }
  //else // entfernt 25.9.2014
  //  Fuellen();
  DlgEditSp.setVisible(false);
}

private void saveS2(int iOld)
{
  int iANR=getSpalten2ANR();
  g.fixtestInfo("saveS2 "+iOld+" mit ANR="+iANR);
  int iAic = iOld < 0 ? -1 : SQL.getInteger(g, "select aic_spalten2 from spalten2 where aic_spalte=" + TabSpalten.getI("Aic") + " and anr=" + iANR +
                                            (iOld > 0 ? " and aic_stamm=" + iOld : "") + " and datum=" + g.DateTimeToString(g.getVon()));
  if (iAic > 0)
  {
    g.fixtestInfo("saveS2 für " + iAic);
    if ((A.TabSp.getI("bits") & Abfrage.cstUnsichtbar)==0)
    {
      SQL Qry = new SQL(g);
      Qry.add("Titel", A.TabSp.getS("Bezeichnung"));
      Qry.add0("aic_stamm", A.TabSp.getI("rel"));
      Qry.add0("aic_begriff", A.TabSp.getI("aic_begriff"));
      Qry.add0("aic_farbe", A.TabSp.getI("aic_farbe"));
      Qry.add0("Laenge", A.TabSp.getI("Laenge"));
      Qry.add("bits", (A.TabSp.getI("bits") & Abfrage.cstUnsichtbar) == 0 ? 1 : 0);
      Qry.update("Spalten2", iAic);
      Qry.free();
    }
    else
      g.exec("delete from Spalten2 where aic_spalten2="+iAic);
  }
  else
  {
    if (iOld==-2)
    {
      if (SQL.exists(g,"select aic_spalten2 from spalten2 where aic_abfrage=" + A.iAbfrage + " and anr=" + iANR + " and datum=" + g.DateTimeToString(g.getVon())))
        return;
      else
        g.fixtestInfo("saveS2all erstmalig für Abfrage" + A.iAbfrage + "/" + g.getStamm(iAIC_Stamm)+"/"+g.getVon());
      iAIC_Stamm_Old=-1;
    }
    else
    {
      g.fixtestInfo("saveS2all für " + A.iAbfrage + "/" + iAIC_Stamm);
      g.exec("delete from spalten2 where aic_abfrage=" + A.iAbfrage + " and anr=" + iANR + " and datum=" + g.DateTimeToString(g.getVon()));
    }
    SQL Qry = new SQL(g);
    for (A.TabSp.moveFirst(); !A.TabSp.out(); A.TabSp.moveNext())
      if ((A.TabSp.getI("bits") & Abfrage.cstAnzeigen) > 0 && (A.TabSp.getI("rel")==0 || (A.TabSp.getI("bits") & Abfrage.cstUnsichtbar)==0))
      {
        Qry.add("reihe", A.TabSp.getI("Reihenfolge"));
        Qry.add("aic_spalte", A.TabSp.getI("aic_spalte"));
        Qry.add("Titel", A.TabSp.getS("Bezeichnung"));
        Qry.add0("aic_stamm", A.TabSp.getI("rel"));
        Qry.add0("aic_begriff", A.TabSp.getI("aic_begriff"));
        Qry.add0("aic_farbe", A.TabSp.getI("aic_farbe"));
        Qry.add("Datum", g.getVon());
        Qry.add("anr", iANR);
        Qry.add("aic_abfrage", A.iAbfrage);
        Qry.add("bits", (A.TabSp.getI("bits") & Abfrage.cstUnsichtbar) == 0 ? 1:0);
        Qry.insert("Spalten2", false);
      }
    Qry.free();
  }

}

public void setAbfrage(int iAbf)
{
  //A=new ShowAbfrage(g,iAbf,Abfrage.cstBegriff);
//	g.fixtestError(sDefBez+" setAbfrage "+iAbf);
	saveColumns();
  if (timer != null)
  {
    timer.stop();
    timer=null;
  }
  bAutorefresh=false;
  iMomX=-1;
  iMomY=-1;
  A=null;
  sDefBez="";
  iAIC_Begriff_Abfrage=iAbf;
  g.setAbfrage(iD,iAbf);
  //g.setAbfrage(Fom.getBegriff(),iAbf,iAbf);
  dtMoa=null;
  table.clearCells();
  table.removeActionKeys();
  Start(true);
  dtMoa=bBew ? null:g.getAbschlussDate(/*Fom.getBegriff(),*/iAIC_Bewegungstyp,iAIC_Stamm);
}

private void delRest (int iSpalte)
{
  int iPos=A.TabSp.getPos();
  while(iPos+1<A.TabSp.size() && iSpalte==A.TabSp.getI(iPos+1,"aic_spalte"))
      A.TabSp.clearInhalt(iPos+1);
}

private Timestamp getNextTS()
{
  Timestamp ts=null;
  for(TabSE.moveFirst();!TabSE.out();TabSE.moveNext())
    if (TabSE.getTimestamp("Datum").after(g.getVon()))
      return ts;
    else
      ts=TabSE.getTimestamp("Datum");
  return ts;
}

private void korrektReihe()
{
  Timestamp ts=null;
  int iR=0;
  for (int i=0;i<TabSE.size();i++)
  {
    if (ts==null || !ts.equals(TabSE.getTimestamp(i,"Datum")))
    {
      ts = TabSE.getTimestamp(i,"Datum");
      iR=1;
    }
    if (iR!=TabSE.getI(i,"reihe"))
    {
      g.defInfo2("Reihe bei "+TabSE.getI(i,"aic_spalten2")+" von "+TabSE.getI(i,"reihe")+" auf "+iR+" geändert");
      g.exec("update spalten2 set reihe="+iR+" where aic_spalten2="+TabSE.getI(i,"aic_spalten2"));
      TabSE.setInhalt(i,"reihe",iR);
    }
    iR++;
  }
}

private boolean getTabSE()
{
  if (sTabTyp.endsWith("erf"))
  {
    //g.fixtestInfo("getTabSE "+sDefBez+": "+iAIC_Stamm+"/"+g.getVonBis2());
    int iANR=getSpalten2ANR();
    boolean bCopySp=false;
    if (iANR!=iAIC_Stamm_Old || Static.Ungleich(getNextTS(),dt_Old))
    {
      g.fixtestInfo("getTabSE: Stamm oder Zeitraum anders:"+iAIC_Stamm_Old+"->"+iANR+", TabSpC="+TabSpC);
      if (TabSpC==null)
        TabSpC=new Tabellenspeicher(g,A.TabSp);
      else //if (iANR==iAIC_Stamm_Old)
      {
        if (SQL.exists(g,"select aic_spalten2 from spalten2 where aic_abfrage="+A.iAbfrage + " and ANR=" + iANR))
          A.TabSp.copyFrom(TabSpC);
        else
          bCopySp = true;
      }
    }
    if (iANR!=iAIC_Stamm_Old)
    {
      iAIC_Stamm_Old = iANR;
      g.fixtestInfo("Lade TabSE für "+g.getStamm(iANR));
      TabSE = new Tabellenspeicher(g, "select aic_spalten2,reihe,aic_spalte,Titel,bits,aic_stamm,aic_begriff,aic_farbe,Datum,LAENGE from spalten2 where aic_abfrage=" +
                                   A.iAbfrage + " and ANR=" + iANR + " order by Datum,reihe,aic_spalten2", true);
      TabSE.sGruppe="Datum";
      //if (TabSE.isEmpty())
      //  saveS2(-2);
      //else if (bCopySp)
      //  A.TabSp.copyFrom(TabSpC);
      korrektReihe();
      dt_Old=getNextTS();
      sAktDatentyp=null;
      if (bLeer || !TabSE.isEmpty() && dt_Old!=null)
      {
        g.fixtestInfo("getTabSE: Start mit bLeer="+bLeer);
        if (bLeer)
          A=null;
        Start(true);
        //bLeer=false;
        return true;
      }
    }
    else if (Static.Ungleich(getNextTS(),dt_Old))
    {
      Timestamp dt=getNextTS();
      if (dt!=null)
      {
        g.fixtestInfo("Ändere Datum von " + dt_Old + " auf " + dt);
        dt_Old=dt;
        Start(true);
        return true;
      }
    }
    else if (bLeer)
    {
      g.fixtestInfo("Ermittle TabSp für leer");
      A.SQL_String();
    }
    else if (!TabSE.isEmpty() && dt_Old!=null)
    {
      if (bCopySp)
          A.TabSp.copyFrom(TabSpC);
      //g.fixtestInfo("Ermittle TabSp für "+g.getStamm(iAIC_Stamm)+"/"+dt_Old);
      int iSpalte=0;
      Vector<Integer> Vec = null;
      boolean bPos=false;
      for(TabSE.posInhalt("Datum",dt_Old);!TabSE.out() && TabSE.getTimestamp("Datum").equals(dt_Old);TabSE.moveNext())
      {
        //boolean bNeueSpalte=;
        if (iSpalte!=TabSE.getI("aic_spalte"))
        {
          delRest(iSpalte);
          iSpalte = TabSE.getI("aic_spalte");
          //Vec = null;
          bPos=A.TabSp.posInhalt("aic_spalte", iSpalte);
          if (bPos && TabSE.getI("reihe")!=A.TabSp.getI("Reihenfolge"))
          {
            g.fixtestInfo("Reihenfolge ändern von "+iSpalte+"/"+A.TabSp.getI("Reihenfolge")+" auf "+TabSE.getI("reihe"));
            int iNr=A.TabSp.getI("Nummer");
            A.TabSp.setInhalt("Reihenfolge", TabSE.getI("reihe"));
            A.TabSp.sort("Reihenfolge",true);
            A.TabSp.posInhalt("Nummer", iNr);
          }
        }
        else
        {
          A.TabSp.moveNext();
          boolean bCopy=true;
          if (A.TabSp.out())
            A.TabSp.moveLast();
          else if(A.TabSp.getI("aic_spalte")!=iSpalte)
            A.TabSp.movePrevious();
          else
            bCopy=false;
          if (bCopy)
            A.TabSp.copyLine2();
        }
        if (bPos)
        {
          int iRel=TabSE.getI("aic_stamm");
          if (iRel>0)
            g.fixtestInfo("Pos"+TabSE.getI("reihe")+": Rel="+iRel+" ("+TabSE.getS("Titel")+")");
          if (iRel>0 && Vec == null) Vec = new Vector<Integer>();
          if (iRel>0 && Vec.contains(iRel))
          {
            A.TabSp.clearInhalt();
            TabSE.clearInhalt2();
            g.fixInfo(TabSE.getS("Titel")+" doppelt und wird deshalb gelöscht");
          }
          else
          {
            if (iRel>0) Vec.addElement(iRel);
            A.TabSp.setInhalt("Bezeichnung", TabSE.getS("Titel"));
            A.TabSp.setInhalt("rel", iRel);
            A.TabSp.setInhalt("aic_begriff", TabSE.getI("aic_begriff"));
            A.TabSp.setInhalt("aic_farbe", TabSE.getI("aic_farbe"));
            if(!TabSE.isNull("laenge"))
              A.TabSp.setInhalt("laenge", TabSE.getI("laenge"));
            boolean bAktiv = (TabSE.getI("bits") & 1) > 0;
            boolean bSpAktiv = (A.TabSp.getI("bits") & Abfrage.cstUnsichtbar) == 0;
            if (bSpAktiv && !bAktiv)
              A.TabSp.setInhalt("bits", A.TabSp.getI("bits") + Abfrage.cstUnsichtbar);
            else if (bAktiv && !bSpAktiv)
              A.TabSp.setInhalt("bits", A.TabSp.getI("bits") - Abfrage.cstUnsichtbar);
          }
        }
      }
      delRest(iSpalte);
      A.SQL_String();
      //TabSpalten = A.getSpalten();
    }
  }
  return false;
}

private Vector ErmittleTab(int riAIC_Stamm,int riAIC_Stammtyp,boolean bFill)
{
//	g.fixtestError("ErmittleTab "+riAIC_Stamm+" vorher: "+sDefBez);
	int iB_Abfrage=g.getAbfrage(iD);
	if (iB_Abfrage>0)
	  iAIC_Begriff_Abfrage=iB_Abfrage;
        sTabTyp = g.TabCodes.getKennung(g.TabBegriffe.getI_AIC("Typ", iAIC_Begriff_Abfrage));
        /*if (sTabTyp.endsWith("erf"))
        {
          g.checkAbfragen();
          int iPos=g.TabAbfragen.getPos("aic_begriff",iAIC_Begriff_Abfrage);
          if (iPos>=0)
            TabSE=new Tabellenspeicher(g,"select aic_spalten2,reihe,aic_spalte,Titel,aic_stamm,aic_begriff,aic_farbe,Datum,anr from spalten2 where aic_abfrage="+
                                       g.TabAbfragen.getI(iPos,"aic_abfrage")+" order by ANR,Datum,reihe",true);
          else
            Static.printError("AUTable: Abfrage-Begriff "+iAIC_Begriff_Abfrage+" nicht gefunden");
        }*/
  	if (A==null)
        {
          A = new ShowAbfrage(g, iAIC_Begriff_Abfrage /*Fom.getBegriff(),riAIC_Begriff_Abfrage*/, Abfrage.cstBegriff);
          sDefBez=A.sDefBez;
          bSperre=g.BewSperre(A.iBew);
          A.bTable=true;
          A.SQL_String();
          if (bSperre)
            g.fixtestInfo("Sperre für "+sDefBez+" nötig!");

          //g.fixtestInfo(sDefBez + ": sTabTyp=" + sTabTyp);
        }
        //else
        //  A.SQL_String();
        getTabSE();
        fontStandard=g.getTabFont();
	fontTitel=g.getTabFontTZ();//fontTitelzeile;
	Font font=g.getFont(A.iBegriff);
	if (font != null)
	{
		fontStandard=font;
		fontTitel=new Font(font.getName(),Font.BOLD,font.getSize());
	}

	Vector<Object> Vec = new Vector<Object>();
	//iAIC_Abfrage=A.iAbfrage;
	iAIC_Bewegungstyp=A.iBew;
	iAutorefresh = (A.iBits & Abfrage.cstFirst)==0 ? A.iTop:0;
	iBitsAbfrage = A.iBits;
	bKeinStm=iAIC_Bewegungstyp==0 || Abfrage.is(iBitsAbfrage,Abfrage.cstKeinStamm) || Abfrage.is(iBitsAbfrage,Abfrage.cstMengen);//Qry.getB("kein_stamm");
	//iAIC_Eigenschaft=bKeinStm?0:SQL.getInteger(g,"select first e.aic_eigenschaft from bewegungstyp join bew_zuordnung join eigenschaft e where aic_stammtyp="+riAIC_Stammtyp+" and bewegungstyp.aic_bewegungstyp="+iAIC_Bewegungstyp+" order by reihenfolge");
	iAIC_Eigenschaft=0;
	if (!bKeinStm)
	{
		Tabellenspeicher TabEig=new Tabellenspeicher(g,"select e.aic_eigenschaft,b.kennung,e.aic_stammtyp from bew_zuordnung z join eigenschaft e on z.aic_eigenschaft=e.aic_eigenschaft join begriff b on e.aic_begriff=b.aic_begriff"+
			" where z.aic_bewegungstyp="+iAIC_Bewegungstyp+" and (e.aic_stammtyp="+riAIC_Stammtyp+" or b.kennung='BewHierarchie') order by reihenfolge",true);
		for(TabEig.moveFirst();iAIC_Eigenschaft==0 && !TabEig.eof();TabEig.moveNext())
		{
			if(TabEig.getS("kennung").equals("BewStamm") || TabEig.getS("kennung").equals("BewHierarchie") && g.InHierarchie(TabEig.getI("aic_stammtyp"),riAIC_Stammtyp))
				iAIC_Eigenschaft=TabEig.getI("aic_eigenschaft");
		}
		TabEig.clearAll();
	}
        iRolle=0;
        if (iAIC_Eigenschaft>0)
        {
          int iPos=g.TabEigenschaften.getPos("Aic",iAIC_Eigenschaft);
          iRolle=g.TabEigenschaften.getI(iPos,"Rolle");
        }
	bFehler=iAIC_Eigenschaft==0 && !bKeinStm;
	if(bFehler)
		new Message(Message.ERROR_MESSAGE,null,g).showDialog("keineEigenschaft2",new String[] {A.sDefBez,g.TabErfassungstypen.getBezeichnungS(A.iBew)});
	else
	{
	  Tabellenspeicher Tab=null;
	  if (bFill)
	  {
            //g.fixInfo("fill:1");
            //A.SQL_String();
            //A.TabSp.showGrid("TabSp");
            //g.fixInfo(" getDaten2 von Tabelle ------------- iStamm="+riAIC_Stamm+", VecAic="+VecAic);
            Tab = A.getDaten(iAIC_Stammtyp, riAIC_Stamm, VecAic,Fom.thisFrame);
	    //Tab=A.getDaten(riAIC_Stammtyp,riAIC_Stamm,null);
	    if ((A.iBits&Abfrage.cstAusblenden)>0 || Tab==null || bUnsichtbar)
	    {
	      boolean b=Tab!=null && (!Tab.isEmpty() || (A.iBits&Abfrage.cstAusblenden)==0) || g.Def() && Static.bDefBezeichnung;
	      bUnsichtbar=!b;
	      //g.fixInfo(" ---------- AU_Table.ErmittleTab ----------- :"+riAIC_Stamm+"/"+g.getVonBis("", false)+"->"+b);
              //g.fixInfo(" ---------- AU_Table.ErmittleTab ----------- :"+sDefBez+":"+b);

              //table.getParent().getParent().setVisible(b); // 9.9.2010 ausgeblendet, damit "manuelle Erfassung" aus Einzelüberweisungen sichtbar
	    }
	  }
		Vec.addElement(Tab);
		Vec.addElement(A.getSpalten());
		Vec.addElement(new Integer(A.iBew==0 ? AUTable.Stamm:AUTable.Bewegung));
	}

	return(Vec);
}

private boolean keineDaten()
{
  return !TabSpalten.out() && Tab.sum(TabSpalten.getS("Kennung"))==0;
}

private boolean Weiter(boolean bSES,boolean b)
{
  int iPos=b?TabSpalten.getPos()+1:TabSpalten.getPos()-1;
  return !TabSpalten.out() && iPos>=0 && (bSES && TabSpalten.getI("Aic")==TabSpalten.getI(iPos,"Aic") || !bSES && !Abfrage.VRel.contains(TabSpalten.getS(iPos,"Datentyp")));
}

private boolean keineDatenAll()
{
  if (Tab!=null)
   for (int i=0;i<TabSpalten.size();i++)
   {
    String sDt=TabSpalten.getS(i, "Datentyp");
    String sKen=TabSpalten.getS(i, "Kennung");
    //g.fixInfo("keineDatenAll:"+TabSpalten.getS(i,"Bezeichnung")+"/"+sKen+"->"+sDt/*+"/"+Tab*/);
    if (Abfrage.VRel.contains(sDt) && (Tab.sum(sKen) != 0))
      return false;
   }
  return true;
}

private boolean nichtLetzte()
{
  int iAnz=0;
  int iSpalte=TabSpalten.out() ? 0:TabSpalten.getI("aic");
  for (A.TabSp.posInhalt("aic_spalte",iSpalte);!A.TabSp.out() && A.TabSp.getI("aic_spalte")==iSpalte;A.TabSp.moveNext())
    if ((A.TabSp.getI("bits")&Abfrage.cstUnsichtbar)==0)
      iAnz++;
  return iAnz>1;
}

private void EnableButtons()
{
	bUndelete = false;
	//TabAenderungen.moveFirst();
	if (TabAenderungen != null)
	 for(TabAenderungen.posInhalt("Zeile",new Integer(iMomY));(bKeinStm || iAIC_Stamm>0) && !TabAenderungen.out() && !bUndelete;TabAenderungen.posNextInhalt("Zeile",new Integer(iMomY)))
		bUndelete = bUndelete || TabAenderungen.getS("Aenderungsart").equals("Loeschen");
	BtnSave.setEnabled((Static.bDefBezeichnung || !Abfrage.is(iBitsAbfrage,Abfrage.cstKeinSave) && !Fom.ReadOnly()) && (bKeinStm || iAIC_Stamm>0) && Modified());
        if (MnuSave != null)
          MnuSave.setEnabled(BtnSave.isEnabled());
        //boolean bSE=sTabTyp.endsWith("erf");
        boolean bSES=/*iOldX>=0 &&*/ sAktDatentyp!=null && Abfrage.VRel.contains(sAktDatentyp);
        //BtnSEp.setVisible(bSE);
        //BtnSEm.setVisible(bSE);
        if (BtnSEp!=null) BtnSEp.setEnabled(bSES);
        if (BtnSEm!=null) BtnSEm.setEnabled(bSES && keineDaten() && nichtLetzte());
        if (BtnSEe!=null) BtnSEe.setEnabled(sAktDatentyp != null);
        //g.fixInfo("iMomX="+iMomX+", Anz="+getAnzCol());
        if (BtnSEl!=null) BtnSEl.setEnabled(iMomX>iXmin && /*!bSES ||*/ Weiter(bSES,false));
        if (BtnSEr!=null) BtnSEr.setEnabled(iMomX<getAnzCol()-1 && /*!bSES ||*/ Weiter(bSES,true));
        boolean bSE_keine=/*sTabTyp!=null && */sTabTyp.endsWith("erf") && keineDatenAll();
        if (MnuSE_this != null) MnuSE_this.setEnabled(bSE_keine);
        if (MnuSE_leer != null) MnuSE_leer.setEnabled(bSE_keine);
        //boolean bEdit=true;
        if (MnuZeilen != null)
        {
          //MnuZeilen.setEnabled(!BtnSave.isEnabled());
          bEdit=!MnuZeilen.isSelected();
        }
        if (MnuNew != null)
          MnuNew.setVisible(bEdit && !Fom.ReadOnly());
        if (MnuDel != null)
          MnuDel.setVisible(bEdit && !Fom.ReadOnly());
        if (MnuUndel != null)
          MnuUndel.setVisible(bEdit);
        if (MnuSave != null)
          MnuSave.setVisible(bEdit);
        if (MnuSuchen != null)
          MnuSuchen.setVisible(!bEdit || !bEdit2);
        if (MnuEMail !=null)
          MnuEMail.setVisible(!bEdit || !bEdit2);
        if (MnuCalc !=null)
          MnuCalc.setVisible(bEdit && !bEdit2 && iMomY>=0 || !bEdit);
        //BtnUndelete.setEnabled(bEdit && (bKeinStm || iAIC_Stamm>0) && bUndelete);
        boolean bDaten=VecSperre==null || VecSperre.size()==0 || !VecSperre.contains(iMomY);
        //g.fixtestInfo("Zeile="+iMomY+" -> "+bDaten);
        if (MnuUndel != null)
          MnuUndel.setVisible(bEdit && (bKeinStm || iAIC_Stamm>0) && bUndelete);
	BtnDelete.setEnabled(bEdit && bDaten && (bKeinStm || iAIC_Stamm>0) && iMomY>=0 && iMomY<iZeile /*&& !bUndelete*/ && (Static.bDefBezeichnung || !Abfrage.is(iBitsAbfrage,Abfrage.cstKeinDel) || table.getCell(iMomY,iSpalte).equals("")));
        if (MnuDel != null)
          MnuDel.setVisible(BtnDelete.isEnabled());
        //if (MnuH != null)
        //  MnuH.setEnabled(iMomY>=0 && getAIC(iMomY).intValue()>0);
        //g.fixtestInfo("bEdit="+bEdit+", allow_New="+allow_New()+", bOkStamm="+bOkStamm+", bDaten="+bDaten);
        BtnNew.setEnabled(bEdit && allow_New() && bOkStamm && bDaten && !bUnsichtbar);
        if (MnuNew != null)
          MnuNew.setVisible(BtnNew.isEnabled());
	setSorted(TabAenderungen.isEmpty() && (iBitsAbfrage&Abfrage.cstNoSort)==0);
}

private boolean allow_New()
{
	return Static.bDefBezeichnung || !Abfrage.is(iBitsAbfrage,Abfrage.cstKeinNeu) && MnuNew != null && (iArt==Bewegung?bKeinStm || iAIC_Stamm>0:true);
}

public Vector<String> FehlerListe()
{
	Vector<String> Vec = new Vector<String>();
        boolean bErrFirst=true;
        int iZeileLast=-2;
        boolean bBZ=false; // bedingt zwingend vorhanden
        boolean bKW=true;  // kein Wert für bedingt Zwingend vorhanden
        String sEigM=null;
	for(TabAenderungen.moveFirst();!TabAenderungen.eof();TabAenderungen.moveNext())
	{
		if(!TabAenderungen.getInhalt("Aenderungsart").equals("Loeschen"))
		{
                  if(!TabSpalten.setPos(TabAenderungen.getI("Spalte")))
                  {
				Vec.addElement("AUTable.FehlerListe(): In TabSpalten nicht richtig positioniert!");
				Static.printError(" AUTable.FehlerListe(): In TabSpalten nicht richtig positioniert!");
                  }
                  else
                  {
                    String sDT = TabSpalten.getS("Datentyp");
                    String sAnf=" "+TabSpalten.getS("Bezeichnung")+", "+g.getShow("Zeile")+" "+(TabAenderungen.getI("Zeile")+1)+": ";
                    int iPosE=g.TabEigenschaften.getPos("AIC",Eig(sDT));
                        if(iPosE<0)
			{
				Vec.addElement("AUTable.FehlerListe(): In TabEigenschaften konnte nicht richtig positioniert werden!");
				Static.printError(" AUTable.FehlerListe(): In TabEigenschaften konnte nicht richtig positioniert werden!");
			}
                        // Prüfung auf zwingende Werte
			else if((TabSpalten.getI("bits")&0x10000*Global.cstAlways)>0 && Check.isNull(TabAenderungen.getInhalt("Neuer Wert")))
                        {
                          Vec.addElement(sAnf + g.getShow("benoetigt Wert"));
                          //g.fixtestInfo(TabSpalten.getS("Bezeichnung")+" zwingend:"+TabSpalten.getI("bits"));
                        }
                        // Prüfung auf eindeutige Werte
			else if (iArt==Stamm && (TabSpalten.getI("bits")&(Global.cstEindeutig*0x10000))>0 && Check.nichtEindeutig(g,TabAenderungen.getInhalt("Neuer Wert"),sDT,TabSpalten.getI("Kennung2"),iAIC_Stammtyp))
					Vec.addElement(sAnf+g.getShow("ist nicht eindeutig"));
                        // Prüfung auf Monatsabschluß
			else if (iAIC_Stamm>0 && (TabSpalten.getI("bits")&(Abfrage.cstGueltig))>0 && Check.Monatsabschluss(g/*,Fom.getBegriff()*/,iAIC_Bewegungstyp,iAIC_Stamm,TabAenderungen.getInhalt("Neuer Wert"),TabAenderungen.getInhalt("Alter Wert")))
				Vec.addElement(sAnf+g.getShow("schon abgeschlossen"));
                        // Prüfung auf Gültigkeit
			else if(!Check.isValid(TabAenderungen.getInhalt("Neuer Wert")))
				Vec.addElement(sAnf+g.getShow("ist nicht gueltig"));
                        // Prüfung auf min/max - Bereich
			else if(!Check.inRange(TabAenderungen.getInhalt("Neuer Wert"),g.TabEigenschaften.getInt(iPosE,"min"),g.TabEigenschaften.getInt(iPosE,"max")))
				Vec.addElement(sAnf+g.getShow("liegt ausserhalb")+" "+g.TabEigenschaften.getI(iPosE,"min")+"-"+g.TabEigenschaften.getI(iPosE,"max"));
                        else if(Check.zuLang(TabAenderungen.getInhalt("Neuer Wert"),sDT,Abfrage.getLaenge(TabSpalten)))
                                Vec.addElement(sAnf+(TabAenderungen.getS("Neuer Wert").length()-Check.getMaxLaenge(sDT,Abfrage.getLaenge(TabSpalten)))+" "+g.getShow("zu lang"));
                        else
                        {
                          int iZeile=TabAenderungen.getI("Zeile");
                          Object oGueltigkeit = iSpalteGueltig2!=-1?table.getCell(iZeile,iSpalteGueltig2):null;
                          if (iZeileLast!=iZeile && bErrFirst && iAIC_Stamm>0 && oGueltigkeit != null && oGueltigkeit instanceof Zeit &&
                              !Check.isNull(oGueltigkeit) && !Check.Vorhanden(g,iAIC_Stamm,iRolle,(Zeit)oGueltigkeit))
                          {
                            Vec.addElement(" " + g.getStamm(iAIC_Stamm)/*SQL.getString(g, "select bezeichnung from stammview where aic_stamm=" + iAIC_Stamm)*/ + " " + g.getShow("ist nicht vorhanden"));
                            bErrFirst = false;
                          }
                          if((TabSpalten.getI("bits2")&Abfrage.cstBedZwang)>0)
                          {
                            if (!bBZ)
                            {
                              bBZ = true;
                              iZeileLast = iZeile;
                            }
                            bKW=bKW && Check.isNull(TabAenderungen.getInhalt("Neuer Wert"));
                            //g.fixtestInfo("bed "+TabSpalten.getS("Bezeichnung")+"->"+bKW+" in Zeile "+iZeile);
                            if (sEigM==null)
                              sEigM=" "+TabSpalten.getS("Bezeichnung");
                            else
                              sEigM+=","+TabSpalten.getS("Bezeichnung");
                          }
                          if (iZeileLast != iZeile)
                          {
                            //g.fixtestInfo("Zeile geändert von "+ iZeileLast+ " auf "+iZeile);
                            if (bBZ && bKW)
                              Vec.addElement(sEigM+" "+g.getShow("benoetigt bedingten Wert"));
                            bKW=true;
                            bBZ=false;
                            sEigM=null;
                            iZeileLast = iZeile;
                          }
                          //Zeit zGueltigkeit = oGueltigkeit instanceof Zeit?(Zeit)oGueltigkeit:null;
                          if ((TabSpalten.getI("bits")&Abfrage.cstGueltig2)>0)
                          {
                            if (sDT.startsWith("BewDatum") && !Check.inZR(TabAenderungen.getInhalt("Neuer Wert"),g))
                              Vec.addElement(sAnf+g.getShow("liegt ausserhalb")+" "+g.getVonBis("dd.MM.yyyy",true));
                            else if ((sDT.equals("BewStamm") || sDT.equals("BewHierarchie") || sDT.equals("Gruppe") || sDT.equals("Hierarchie")) &&
                                       oGueltigkeit instanceof Zeit && !Check.Vorhanden(g, Check.getI(TabAenderungen.getInhalt("Neuer Wert")),g.TabEigenschaften.getI(iPosE,"Rolle"), (Zeit)oGueltigkeit))
                                Vec.addElement(sAnf +g.getShow("ist nicht vorhanden"));
                          }
                        }
                  }
		}
	}
        if (bBZ && bKW)
          Vec.addElement(sEigM+" "+g.getShow("benoetigt bedingten Wert"));
	return Vec;
}

/*public boolean Verteiler()
{
	return (A.iBits&A.cstVerteiler)>0;
}*/

public Vector<Integer> getVecAIC()
       {
         Vector<Integer> VecKeys = new Vector<Integer>();
         VecKeys.addElement(getAIC(iMomY));
         return VecKeys;
       }

public Integer getAIC(int i)
{
  if (i>=0&&i<iZeile)
  {
    //Object Obj =
    	table.getCell(i, TabSpalten.getVecSpalte("Kennung").size());
    //g.progInfo(Obj + ":" + Static.className(Obj));
  }
	return (new Integer(i>=0&&i<iZeile ? (int)Sort.getf(table.getCell(i,TabSpalten.getVecSpalte("Kennung").size())):0));
}

public Vector<Integer> getAllAICs()
{
	Vector<Integer> VecKeys = new Vector<Integer>();
	int iKey=TabSpalten.getVecSpalte("Kennung").size();
	for(int i=0;i<iZeile;i++)
	{
		Object Obj=table.getCell(i,iKey);
		if(Obj instanceof Integer)
			VecKeys.addElement((Integer)Obj);
	}
	return VecKeys;
}

public AUOutliner getGid()
{
	AUOutliner GidGesamt = new AUOutliner();
	ShowAbfrage.initOutliner(g, GidGesamt);
	A.TabToOutliner(GidGesamt,Tab,  null);
	return GidGesamt;
}

public void TraverseCell(JCTraverseCellEvent e)
{
  iMomX=e.getNextColumn();
  iMomY=e.getNextRow();
	//g.fixtestInfo("traverseCell: "+iOldY+":"+iOldX+":"+iMomY+":"+iMomX);
        if (iOldX>-1)
          setSpaltenEdit(false);
	EnterCell(iOldY,iOldX,iMomY,iMomX); // Testweise weg am 3.12.2021
        //g.progInfo("nach EnterCell");
	if ((iBitsAbfrage&Abfrage.cstSynchron)>0)
	{
		int iAic=getAIC(iMomY).intValue();
		if(iArt == Stamm)
		{
			g.setSyncStamm(iAIC_Stammtyp,iAic);
		}
		else if(iArt == Bewegung)
                {
                  int iPosB=g.TabErfassungstypen.getPos("Aic", iAIC_Bewegungstyp);
                  if (iPosB>=0)
                  {
                    g.defInfo("Setze Bew " + iAIC_Bewegungstyp + " auf Pool " + iAic);
                    g.TabErfassungstypen.setInhalt(iPosB,"Pool", iAic);
                  }
                }
	}
        if (iSyncY != iMomY)
        {
          iSyncY=iMomY;
          for(int iPos=0;iPos<TabSpalten.size();iPos++)
            if ((TabSpalten.getI(iPos,"bits2") & Abfrage.cstSetSync) >0)
            {
              Object Obj=table.getCell(iSyncY,iPos);
              if (Obj instanceof Combo)
                g.setSyncStamm(TabSpalten.getI(iPos,"Stt"),((Combo)Obj).getAic());
            }
        }
}

      private int getDruckAlign(int iCol)
      {
        int iA=table.getAlignment(0,iCol);
        return iA==JCTblEnum.TOPRIGHT ? 2:iA==JCTblEnum.TOPCENTER ? 1:0;
      }

      private int getAnzCol()
      {
        int i=table.getNumColumns();
        while (!DruckCol(i-1))
          i--;
        return i;
      }

      private boolean DruckCol(int iCol)
      {
        int iW=table.getPixelWidth(iCol);
        boolean b= iW>2 || iW<0;
        //g.fixInfo("DruckCol"+iCol+":"+iW+"/"+b);
        return b;
      }

      public static int getAnz(String s,char c)
      {
        if (s==null || s.equals(Static.sLeer))
          return 0;
        int iAnz=1;
        for (int i=0;i<s.length();i++)
          if (s.charAt(i)==c) iAnz++;
        return iAnz;
      }
      public static int getAnzCR(Vector VecBez)
      {
        int iZ=1;
        for (int i=0;i<VecBez.size();i++)
        {
          //String s=;
          int iMom=getAnz(Sort.gets(VecBez, i), '\n');
          if (iMom>iZ) iZ=iMom;
          //iZ = Math.max(iZ, iMom);
          //System.out.println("Spalte "+i+":<"+Sort.gets(VecBez, i).replaceAll("\n","[CR]")+"> enthält "+iMom+" CR ->"+iZ);
        }
        return iZ;
      }

      public static String expandCR(String s,int iAnz)
      {
        int iAnzMom=getAnz(s,'\n');
        if (iAnzMom==0)
        {
          s=" ";
          iAnzMom++;
        }
        for (int i=iAnzMom;i<iAnz;i++)
          s+="\n ";
        return s;
      }

      private void Drucken()
      {
        int inC=table.getNumColumns();
        int inR=table.getNumRows();
        Vector<String> Vec2=new Vector<String>();
        int i1=0;
        for(int i=0;i<inC;i++)
          if (DruckCol(i))
            Vec2.addElement(""+i1++);
        Vec2.addElement(""+i1);
        Vec2.addElement("LINES");
        //g.fixInfo("Vec2="+Vec2);
        Tabellenspeicher Tab=new Tabellenspeicher(g,Vec2);
        Tabellenspeicher TabFarbe = new Tabellenspeicher(g, new String[] {"Zeile", "Spalte", "Farbe"});
        TabFarbe.sGruppe="Zeile";
        TabFarbe.sAIC="Spalte";
        Vector VecBez = table.getColumnLabels();
        Tab.newLine();
        Tab.setInhalt("0","U");
        int iZ=getAnzCR(VecBez);
        i1=1;
        //g.fixInfo("vor Titelzeile");
        for (int i = 0; i < inC; i++)
          if (DruckCol(i))
          {
            //g.fixInfo("Titel"+i+"/"+i1+":"+Sort.gets(VecBez, i));
            Tab.setInhalt("" + (i1++), /*DruckCol(i) ? */ getDruckAlign(i) + expandCR(Sort.gets(VecBez, i), iZ) /*:"0"*/);
          }
        for (int i = 0; i < inR; i++)
        {
          Tab.newLine();
          i1=1;
          //g.fixInfo("Zeile "+i);
          Tab.setInhalt("0", i+1);
          for (int i2 = 0; i2 < inC; i2++)
            if(DruckCol(i2))
            {
              Tab.setInhalt("" + (i1++), table.getCell(i, i2));
              if (!table.getBackground(i,i2).equals(Global.ColTable))
                setFarbe(TabFarbe,i+1,i1-1,table.getBackground(i,i2).getRGB());
            }
          Tab.setInhalt("LINES", "L");
        }
        if (g.Prog() && g.TestPC())
            Tab.showGrid("Druck "+sDefBez);

        DruckHS dh = new DruckHS(g, sDefBez, 0,Drucken.cstPntQuerformat/*Drucken.cstDFFarbe*/ /*iDruckBits*/,iAIC_Stamm /*Stamm*/);
        dh.setDTitel(g.getBegBez(iAIC_Begriff_Abfrage)+" " + g.getBegriffS("Show", "fuer") + " "+(Menge()? g.getVonBis("dd.MM.yyyy", false):g.getStamm(iAIC_Stamm)),false,Global.fontTitel);
        dh.printTitel(false,true,false,false);
        dh.TabFarbe=TabFarbe;
        //Tabellenspeicher TabBreite = A.getTabDruckbreite(0);
        //if (g.Prog() && g.TestPC())
        //  TabBreite.showGrid("TabBreite");
        dh.addOutliner(Tab,null,0, Drucken.cstGesamtsumme, /*Drucken.cstDFFarbe+*/Drucken.cstMEMO2+Drucken.cstPb,null);// TabBreite);
        dh.vorschau();

      }

      private void setFarbe(Tabellenspeicher Tab,int iZeile,int iSpalte,int iFarbe)
      {
        Tab.newLine();
        Tab.setInhalt("Zeile", iZeile);
        Tab.setInhalt("Spalte", iSpalte);
        Tab.setInhalt("Farbe", iFarbe);
      }

public long getBits()
{
  return iBitsAbfrage;
}

public boolean Menge()
{
  return (iBitsAbfrage&Abfrage.cstMengen)>0;
}

/*public int get_iD()
{
  return iD;
}*/

public void setEnabled(boolean b)
    {
	  if (!b)
	    g.fixtestError(A.sBez+(b ? " setEnabled":" DISABLED"));
      setBackground(b ? Global.ColTable:Global.ColHide);
        if (!g.Def())
          table.setBackground(b ? Global.ColTable:Global.ColHide);
        table.setEnabled(b);
        if (b)
          EnableButtons();
        else
        {
          BtnNew.setEnabled(b);
          BtnSave.setEnabled(b);
          BtnDelete.setEnabled(b);
          //BtnUndelete.setEnabled(b);
        }
        /*BtnNew.setBackground(b ? g.ColTable:g.ColHide);
        BtnSave.setBackground(b ? g.ColTable:g.ColHide);
        BtnDelete.setBackground(b ? g.ColTable:g.ColHide);
        BtnUndelete.setBackground(b ? g.ColTable:g.ColHide);
        BtnRefresh.setBackground(b ? g.ColTable:g.ColHide);*/
        //BtnZeilen.setBackground(b ? g.ColTable:g.ColHide);
        //BtnRefresh.setEnabled(b);
        //BtnZeilen.setEnabled(b);
        //BtnHistory.setEnabled(b);
      }

// add your data members here
private Global g;
private javax.swing.Timer timer = null;
private int iZeile = 0;
private int iSpalte = 0;
private int iMomX = -1;
private int iMomY = -1;
public int iOldY = 0;
private int iOldX = 0;
private int iRowLabel = 0;
private Tabellenspeicher Tab = null;
private Tabellenspeicher TabSpalten;
private Tabellenspeicher TabAenderungen;
private Tabellenspeicher TabRest;
private String sAktDatentyp;
private String sFormat;
private int iEigenschaft;
private Object Com;
private Object ObjWert;
private int iArt = 0;

public JButton BtnAlle;
public JButton BtnCalc;
public JButton BtnNew;
public JButton BtnDelete;
//public JButton BtnUndelete;
public JButton BtnSave;
//private JButton BtnRefresh;
public JButton BtnDruck;

public JToggleButton BtnInfo;
public JToggleButton BtnEdit;

public JButton BtnSEp=null; // Stundenerfassung Spalte (+) dazu
public JButton BtnSEm=null; // Stundenerfassung Spalte (-) weg
public JButton BtnSEe=null; // Stundenerfassung Spalte Edit
public JButton BtnSEl=null; // Stundenerfassung Spalte nach links
public JButton BtnSEr=null; // Stundenerfassung Spalte nach rechts
//private JButton BtnZeilen;
//public JButton BtnHide;
/*private JButton BtnShowDel;
private JButton BtnAbfrage;
private JButton BtnInit;
private JButton BtnHistory;
private JButton BtnParameter;*/
//private JButton BtnParaOk;
//private JButton BtnParaLoeschen;
//private JButton BtnParaAbbruch;

private JCTable table = new JCTable();
public boolean bAktiv=true;

public static int Standard = 0;
public static int Stamm = 1;
public static int Bewegung = 2;

//private int iSpalteANR=-1;
private int iSpalteGueltigkeit=-1;
private int iSpalteGueltig2=-1;
private int iSpalteEMail=-1;
private int iSpalteModell=-1;
private int iSpalteZone=-1;
private int iSpalteDST=-1;
//private boolean bGueltigHH=false;
//private int iAIC_Abfrage;
private int iAIC_Bewegungstyp;
private int iAIC_Stamm;
private int iAIC_Eigenschaft;
private int iAIC_Stammtyp;
private int iAIC_Begriff_Abfrage;

private boolean bFehler;

private int iAutorefresh = 0;
private boolean bAutorefresh = false;

//private String sAbf1;
//private String sAbf2;

//private Parameter ParaColumn;
public static Tabellenspeicher TabColumn;
public static boolean bSichtbar=false;
public static boolean bGauge=false;

//private JCOutlinerFolderNode Nod;

//private Calc CALC =null;
private boolean bKeinStm;

private Color colorBackground;

private long iBitsAbfrage = 0;

private boolean bFirst = true;
//private boolean bFirst2 = true;

//private JDialog FrmParameter;
//private JCheckBox CbxSpaltenbreite;
//private JCheckBox CbxFilter;
//private AUTable thisComponent=this;
private boolean bSort=false;
public ShowAbfrage A=null;
private boolean bAbfrage=false;
private Formular Fom=null;

public JCTraverseCellListener eventJCTCL;
private Font fontTitel;
private Date dtMoa=null;
public Font fontStandard;
private boolean bOne=true;
private boolean bBew=false;
private boolean bOkStamm=true;
private JPopupMenu popup=null;
private JMenuItem MnuSuchen;
private JMenuItem MnuEMail=null;
private JMenuItem MnuCalc=null;
private JMenuItem MnuNew;
private JMenuItem MnuDel;
private JMenuItem MnuUndel;
private JMenuItem MnuSave;
private JMenuItem MnuSE_this;
private JMenuItem MnuSE_leer;
//private JMenuItem MnuH;
private JCheckBoxMenuItem MnuWeiter;
private JCheckBoxMenuItem MnuBeginn;
private JCheckBoxMenuItem MnuAsk;
private JCheckBoxMenuItem MnuZeilen;
private JCheckBoxMenuItem MnuM1=null;
private JCheckBoxMenuItem MnuM2=null;
public String sDefBez="";
private Point P=null;
//private String sSuchtext="";
//private int iSuchBits=1;
private ActionListener AL1=null;
private boolean bEdit2=false;
private Vector VecMod;
private int iD=0;
private int iRolle=0;
private boolean bUnsichtbar=false;
private JDialog DlgSuche=null;
private JButton BtnSucheOk=null;
private boolean bUndelete = false;
private ActionListener MActL=null;
private ActionListener evRefresh=null;
private Vector VecAic=null;
private Vector<Integer> VecWidth=null;
public static int iMaxZeilen=1000;
public static int iGaugeAb=100;
private boolean bAlleZeilen=false;
private String sTabTyp=Static.sLeer;
private JDialog DlgEditSp=null;
private ActionListener AL=null;
private Text EdtTitel2=null;
private SpinBox EdtBreite=null;
private Format EdtFormat=null;
private ComboSort CboFarbe=null;
private AUComboList CboStamm=null;
private boolean bNeueSpalte=false;
private Tabellenspeicher TabSE=null;
private int iAIC_Stamm_Old=-1;
private Timestamp dt_Old=null;
public boolean bSperre=false;
public boolean bCalc=false;
private boolean bEdit=true;
private boolean bLeer=false;
//private boolean bDefChange=false;
private int iKnoten=0;
private Tabellenspeicher TabSpC=null;
private Vector<Integer> VecSperre=null;
private int iEigGruppe=0;
private Tabellenspeicher TabGruppen=null;
private String sFG=null;
private int iXmin;
private String sNichtLeer=null;
private int iSyncY=-1;
//private boolean bGroesser=false;
//private boolean bEvents=false;
//private boolean bAb = true;
//private JCheckBoxMenuItem MnuSdel;
}

