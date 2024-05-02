/*
    ALL_Unlimited-Allgemein-Planung.java

    NOTE: This file is a generated file.
          Do not modify it by hand!iLoadStamm
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Image;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;

import jclass.table3.JCCellRange;
import jclass.table3.JCTable;
import jclass.table3.JCTblEnum;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Anzeige.Mass;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AUComboList;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.VonBisEingabe;
import All_Unlimited.Grunddefinition.DefModell;
import All_Unlimited.Grunddefinition.DefPlanung;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.Calc;
import All_Unlimited.Hauptmaske.TCalc;
import All_Unlimited.Hauptmaske.Import;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Zeitraum;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import jclass.table3.TableScrollbar;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import java.awt.Insets;
import java.util.Date;
//import java.util.Stack;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;

import All_Unlimited.Print.DruckHS;
import All_Unlimited.Print.Drucken;
import All_Unlimited.Print.DefVorlagen;
import All_Unlimited.Print.Seitenlayout;
import All_Unlimited.Grunddefinition.DefAbfrage2;
import All_Unlimited.Allgemein.Eingabe.RadioAuswahl;

public class Planung extends javax.swing.JPanel
{
/**
	 *
	 */
	private static final long serialVersionUID = 1681449773133216545L;
public Planung(Formular rFom,int riPlanung,Global glob,int riD,JPanel Pnl)
{
	super(new BorderLayout(2,2));
        long lClock1=Static.get_ms();
	g=glob;
	iD=riD;
	iPlanung=riPlanung;
        Fom=rFom;
        VecMod=Fom.getVec("Modell",Pnl);
        //g.fixtestInfo("Planung "+iPlanung+": Modelle="+VecMod);
        //FrmEdit=new JDialog((Frame)Fom.thisFrame,g.getBegriff("Dialog","Planungseingabe"),true);
        FrmEdit=new JDialog((JFrame)Fom.thisFrame,g.getBegriffS("Dialog","Planungseingabe"),true);
	table = new JCTable();
	table.setAllowCellResize(JCTblEnum.RESIZE_NONE);
	table.setAutoScroll(JCTblEnum.AUTO_SCROLL_COLUMN);
	//table.setHorizSBPosition(JCTblEnum.SBPOSITION_SIDE);
	//table.setHorizSBAttachment(JCTblEnum.ATTACH_SIDE);
	table.setModeList();
        //table.setJumpScroll(JCTblEnum.JUMP_HORIZONTAL);
	table.setCellBorderWidth(1);
        table.setBackground(Global.ColPlanung);
        //table.setMultiline();
        //table.setCellBorderColor(g.ColPlanung);
        //table.setForeground(-1,-1,g.ColPlanung);
	CboStamm = new ComboSort(g);
        CboStamm.setFont(Transact.fontStandard);
        CboStamm.setPreferredSize(new java.awt.Dimension(150*Fom.iFF/100,16*Fom.iFF/100));

	if (!AbfragenErmitteln())
          return;
        if ((iBits&cstMultiP)>0)
          table.setSelectionPolicy(JCTblEnum.SELECT_MULTIRANGE);

	//Refresh(rVecStamm,false);
	add("Center",table);
	//BtnHide = g.getButton2("tab_hide");
        //BtnNeu.setBackground(g.ColPlanung);
        //BtnSave.setBackground(g.ColPlanung);
        //BtnRefresh.setBackground(g.ColPlanung);
        //BtnHide.setBackground(g.ColPlanung);
	//JPanel Pnl = new JPanel(new GridLayout(1,0,2,2));
        AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
            if (s.equals("Zeitraum"))
              Zeitraum.get(g).show();
            else if (s.startsWith("ZR_"))
            {
              sArt = s.substring(3);
              Refresh(VecNeu, iAicStamm, true);
            }
            else if (s.startsWith("ZR"))
              changeZR(s);
            else if (s.startsWith("Jetzt"))
              Jetzt();
            else if (s.equals("Refresh"))
            {
              //TabZeitart.refresh();
              Refresh(VecNeu, iAicStamm, true);
            }
            else if (s.startsWith("M"))
            {
              int iModBeg=Integer.parseInt(s.substring(1));
              int iPos=g.TabBegriffe.getPos("Aic",iModBeg);
              String sBez=g.getBegBez2(iPos);
              int iMbits=g.TabBegriffe.getI(iPos,"bits");
              g.fixtestInfo("rufe Modell "+sBez+"("+iModBeg+") mit Stamm "+iAicStamm+" auf");
              if ((iBits&cstMultiP)==0 || (iMbits&Global.cstMenge)>0)
                TCalc.Berechnen(g,iModBeg,iMbits,iAicStamm,(iBits&cstMultiP)==0 ? VecNeu:getSelectedAics(),Fom,0,null);
              else
              {
                Vector Vec=getSelectedAics();
                for(int i=0;i<Vec.size();i++)
                  TCalc.Berechnen(g,iModBeg,iMbits,Sort.geti(Vec,i),null,Fom,0,null);
              }
            }
            else if (s.equals("suchen"))
              suchen(BtnSuchen.getLocationOnScreen());
            else if (s.equals("Druck"))
              Drucken();
            else if (s.startsWith("set"))
              setMode(s.substring(3));
            else if (s.equals("TabSperren"))
              showTabSperren(0).showGrid("Sperren");
            else if (s.equals("Save"))
              Save();
            else if (s.equals("New"))
            {
                int iPos=getPos();
                //for(;!table.isSelected(iPos,0);iPos++);
                if (iPos>=0)
                {
                  TabPanel.setPos(iPos);
                  CboStamm.setSelectedAIC(TabPanel.getI("AIC_Stamm"));
                  setStatusVG(0);
                  setStatusSV(0);
                  Edit(null,false);
                }
            }
            else if (s.equals("copy"))
            {
//              int iZA1=iZAcopy;
              int iPos = TabButton.getPos("Button", BtnAkt);
              if (iPos > -1 && CboZeitart.getComboBox().contains(TabButton.getI("Zeitart")))
              {
                VBcopy = null;
                VB2copy = null;
                iZAcopy = TabButton.getI(iPos, "Zeitart");
                CboZeitart.getComboBox().setSelectedAIC(iZAcopy);
              }
              //g.fixtestInfo("copy ZA: "+iZA1+"->"+iZAcopy);
            }
            else if (s.equals("paste"))
            {
              if (BtnAkt != null) setZeitart(getiZAcopy());
            }
            //else if (s.equals("Zeitartwahl"))
            //  CboZeitart.OpenList(null);
            else if (s.equals("Edit"))
            {  if (BtnAkt != null) Edit(BtnAkt,false); }
            else if (s.equals("Ok"))
              EditOk(true);
            else if (s.equals("Abbruch"))
            {
              bEdit=false;
              FrmEdit.setVisible(false);
            }
            else if (s.equals("Loeschen"))
            {
              if(Loeschen(CboStamm.getSelectedBezeichnung()+": "+VB))
              {
                bEdit=false;
                FrmEdit.setVisible(false);
              }
            }
            else if (s.equals("Ruecksetzen"))
            {  if (BtnAkt != null) Back(); }
            else if (s.equals("del"))
            {  if (BtnAkt != null) Loeschen(""); }
            else if (s.equals("History"))
              History();
            else if (s.equals("Parameter"))
              ParameterChange();
            else if (s.equals("Spalten"))
            {
              DefAbfrage2 DefAbf=DefAbfrage2.get(g,DefAbfrage2.TABELLE,AbfSonst,Fom.thisJFrame());
              if (DefAbf.bOk)
              {
                AbfSonst = DefAbf.A;
                AbfSonst.sAnfang="p2.aic_stamm,p2.bezeichnung";
                AbfSonst.SQL_String();
                Refresh(VecNeu, iAicStamm, true);
              }
            }
            else if (s.equals("Close"))
              ;
            else
              Static.printError("Planung.action:"+s+" wird nicht unterstützt");
          }
        };
        BtnNeu = g.getButton2("tab_new","New",AL);
	BtnSave = g.getButton2("tab_save","Save",AL);
        BtnRefresh = g.getButton2("tab_refr","Refresh",AL);
        BtnSuchen = g.getButton2("Tb_Suche", "suchen", AL);
        BtnDruck = g.getButton2("Druck", "Druck", AL);
        // -------------------
        BtnZRm2=g.getButton2("Tb_ZRminus2", "ZRminus2", AL);
        BtnZRm=g.getButton2("Tb_ZRminus", "ZRminus", AL);
        BtnZRp=g.getButton2("Tb_ZRplus", "ZRplus", AL);
        BtnZRp2=g.getButton2("Tb_ZRplus2", "ZRplus2", AL);
        BtnJetzt=g.getButton2("Jetzt", "Jetzt", AL);
        BtnTag=g.getTButton("Tag","ZR_Tag",AL);//new JToggleButton("Tag");BtnTag.setActionCommand("Tag");BtnTag.addActionListener(AL);
        //BtnTag=g.getButton2("Tag","Tag",AL);
        BtnWoche=g.getTButton("Woche","ZR_Woche",AL);//new JToggleButton("Woche");BtnWoche.setActionCommand("Woche");BtnWoche.addActionListener(AL);
        //BtnWoche=g.getButton2("Woche","Woche",AL);
        BtnMonat=g.getTButton("Monat","ZR_Monat",AL);//new JToggleButton("Monat");BtnMonat.setActionCommand("Monat");BtnMonat.addActionListener(AL);
        //BtnMonat=g.getButton2("Monat","Monat",AL);

        PnlTB=new JToolBar();
        PnlTB.setOpaque(false);
        PnlTB.setFloatable(false);
        ButtonGroup RadGroup=new ButtonGroup();
        RadGroup.add(BtnTag);
        RadGroup.add(BtnWoche);
        RadGroup.add(BtnMonat);
        if ((iBits&cstZR)>0)
        {
          PnlTB.add(BtnTag);
          PnlTB.add(BtnWoche);
          PnlTB.add(BtnMonat);
          PnlTB.add(BtnZRm);
          PnlTB.add(BtnJetzt);
          PnlTB.add(BtnZRp);
          PnlTB.add(BtnZRm2);
          PnlTB.add(BtnZRp2);
          PnlTB.addSeparator(new Dimension(25,1));
        }
        bSave=!Abfrage.is(AbfDaten.iBits,Abfrage.cstKeinSave) && !Fom.ReadOnly() && (/*g.bTestPC ||*/ g.getMandant()>1 || g.bAppl || g.bBasis);
        if ((iBits&cstAuswahl)>0)//Schicht() && g.Def())
        {
          BtnInfo = g.getTButton("InfoP", "setInfo", AL);
          BtnEdit = g.getTButton("EditP", "setEdit", AL);
          BtnSchicht = g.getTButton("Schicht", "setSchicht", AL);
          BtnFehlz = g.getTButton("Fehlzeit", "setFehlz", AL);
          BtnIst = g.getTButton("Ist", "setIst", AL);
          BtnMove = g.getTButton("Move", "setMove", AL);
          RadGroup=new ButtonGroup();
          RadGroup.add(BtnInfo);
          RadGroup.add(BtnEdit);
          RadGroup.add(BtnSchicht);
          RadGroup.add(BtnFehlz);
          RadGroup.add(BtnIst);
          RadGroup.add(BtnMove);
          PnlTB.add(BtnInfo);
          if (bSave)
          {
            PnlTB.add(BtnEdit);
            if (Schicht())
              PnlTB.add(BtnSchicht);
            PnlTB.add(BtnFehlz);
            //PnlTB.add(BtnIst); // momentan nicht benötigt
          }
          BtnInfo.setSelected(true);
          //VecSperre=new Vector<Integer>();
          TabSperre=new Tabellenspeicher(g, new String[] {"Aic", "Stamm", "von","bis"});
          checkEnabled();
          //ColSperre();
          if (bSave && g.Def())
            PnlTB.add(BtnMove);
          PnlTB.addSeparator(new Dimension(25,1));
          PnlTB.add(BtnRefresh);
        }
        else if (bSave)
          iMode=_EDIT;

        //PnlTB.addSeparator();
        //Pnl.setBackground(g.ColPlanung);
        bDel=bSave && !Abfrage.is(AbfDaten.iBits,Abfrage.cstKeinDel);
        bMove=false;//AZ() && bSave;
        bkRnS=(iBits&cstKRNS)>0;
        bNew=!Abfrage.is(AbfDaten.iBits,Abfrage.cstKeinNeu) && bSave && (TGM() || !Schicht());
        if(bNew)
		PnlTB.add(BtnNeu);
	if(bSave)
		PnlTB.add(BtnSave);
	/*BtnHistory = g.getButton2("tab_his");
	if(g.History())
	{
		//JButton BtnHistory = g.getButton("tab_his");
                //BtnHistory.setBackground(g.ColPlanung);
                PnlTB.addSeparator();
		PnlTB.add(BtnHistory);
		BtnHistory.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int iPos=0;
				for(;!table.isSelected(iPos,0);iPos++);
				TabPanel.setPos(iPos);
                                new Tabellenspeicher(g,getHistory(TabPanel.getI("AIC_Stamm")),true).showGrid(""+table.getRowLabel(iPos));
			}
		});
	}*/
        //PnlTB.addSeparator();

        if (Menge())//Abfrage.is(AbfDaten.iBits,Abfrage.cstMengen))
        {
          PnlTB.addSeparator();
          PnlTB.add(BtnSuchen);
          if (Schicht())
            PnlTB.add(BtnDruck);
        }
        if (VecMod!=null && VecMod.size()>0)
        {
          PnlTB.addSeparator();
          for (int i=0;i<VecMod.size();i++)
            PnlTB.add(g.getButton2(g.TabBegriffe.getPos("Aic",Sort.geti(VecMod,i)),"M"+VecMod.elementAt(i),AL));
            //PnlTB.add(g.BtnAdd(g.getButton(Sort.geti(VecMod,i),SwingConstants.LEFT),"M"+VecMod.elementAt(i),AL));
        }
        //PnlTB.add(BtnHide);
	/*if(g.Abfrage())
	{
		JButton BtnAbfrage = g.getButton("tab_fil");
                BtnAbfrage.setBackground(g.ColPlanung);
		Pnl.add(BtnAbfrage);
		BtnAbfrage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				All_Unlimited.Grunddefinition.DefAbfrage.get(g,AbfDaten,-iAIC_Bewegungstyp).show();
			}
		});
	}*/
        //if(!Abfrage.is(AbfDaten.iBits,Abfrage.cstKeineLeiste))
        //if (Static.bLeiste)
        PnlTB.setBackground(Global.ColPlanung);
        setBackground(Global.ColPlanung);
        add("North",PnlTB);
        g.ColBack(PnlTB);
        //rPnl.add(this);

	/*BtnNeu.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			int iPos=0;
			for(;!table.isSelected(iPos,0);iPos++);
			TabPanel.setPos(iPos);
			CboStamm.setSelectedAIC(TabPanel.getI("AIC_Stamm"));
			Edit(null);
		}
	});*/
	/*BtnRefresh.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Refresh(VecNeu,0,true);
		}
	});*/
	/*BtnSave.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Save();
		}
	});*/

        /*BtnHide.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                        setHide();
                }
        });*/


	//if(!Abfrage.is(AbfDaten.iBits,Abfrage.cstKeinNeu) && !Abfrage.is(AbfDaten.iBits,Abfrage.cstKeinSave))
	//if((AbfDaten.iBits&(Abfrage.cstKeinNeu+Abfrage.cstKeinSave))==0 && !Fom.ReadOnly() || g.Def())
        {
		table.addMouseMotionListener(new MouseMotionListener()
		{
			public void mouseDragged(MouseEvent ev)
			{
				//g.progInfo("mouseDragged:"+ev.getPoint());
				/*if (bStartPress)
				{
					bStartPress=false;
					ErmittleStammDate(ev.getPoint(),false);
				}*/
                           /*if (PntPanel != null)
                           {
                             DateWOD dt = new DateWOD(Math.round((table.getHorizSB().getAdjustable().getValue() + ev.getPoint().x - PntPanel.x) / dFaktor +
                                                           TSZeitraumVon.getTime()));
                             table.setToolTipText("bis:" + dt.Format("dd.MM hh:mm"));
                           }*/
                          if (!table.isEnabled() || !bSave)
                            return;
                          //g.fixtestInfo("mouseDragged:"+ev.getPoint());
				if (bReady && ev.getModifiers()==MouseEvent.BUTTON1_MASK && ev.getModifiersEx()!=Global.iRM)
					ErmittleStammDate(ev.getPoint(),false);
				//ev.getNextRow();
			}
			public void mouseMoved(MouseEvent ev)
			{
                          //g.progInfo("mouseMoved:"+ev.getPoint()+"/"+bReady+"/"+PntPanel);
                          if (!table.isEnabled()/* || !bSave || bStammGesperrt*/)
                            return;
                          if(PntPanel==null && TabPanel!=null && bReady)
                          {
                            TabPanel.moveFirst();
                            Component C=null;
                            while (!TabPanel.eof() && C==null)
                            {
                              C = ((Component)TabPanel.getInhalt("Panel")).getParent();
                              TabPanel.moveNext();
                            }
                            if (C != null)
                              PntPanel=C.getLocation();
                          }

                          if (PntPanel != null)
                          {
                            Point P=ev.getPoint();
                            DateWOD dt=PointToDW(P);
                            if (dt.toTimestamp().before(TSZeitraumVon) || dt.toTimestamp().after(TSZeitraumBis))
                              table.setToolTipText(null);
                            else
                              table.setToolTipText(dt.Format("dd.MM HH:mm"));
                          }
			}

		});

		table.addMouseListener(new MouseListener()
		{
			public void mousePressed(MouseEvent ev)
			{
//				g.fixtestError("mousePressed:"+ev.getPoint()+", Btn="+ev.getButton());
                                if (!table.isEnabled())
                                  return;
				if (bReady && ev.getModifiers()==MouseEvent.BUTTON1_MASK && ev.getModifiersEx()!=Global.iRM)
					ErmittleStammDate(ev.getPoint(),true);
				//else
				//	g.progInfo("mousePressed: not ready");

                            /*DateWOD dt = new DateWOD(Math.round((table.getHorizSB().getAdjustable().getValue() + ev.getPoint().x - PntPanel.x) / dFaktor +
                                                                      TSZeitraumVon.getTime()));
                            if (AZ())
                              dt.setHalfHour();
                            else
                              dt.setTimeZero();*/

                            //table.setToolTipText(""+VB);
			}
			public void mouseClicked(MouseEvent ev)
			{
//                          g.fixtestError("mouseClicked:"+ev.getModifiers()+", Btn="+ev.getButton());
                          if ((iBits&cstMultiP)>0)
                            g.fixtestInfo("AICs="+getSelectedAics());
			  int iPos=getPos();
                          //int iMax=table.getNumRows();
			  //for(;!table.isSelected(iPos,0) && iPos<iMax;iPos++);
                          //g.progInfo("iPos="+iPos+" von "+iMax);
                          if (iPos>=0 && iPos<TabPanel.size())
                          {
                            iAicStamm = TabPanel.getI(iPos, "AIC_Stamm");
                            if ((AbfDaten.iBits & Abfrage.cstSynchron) > 0)
                              g.setSyncStamm(AbfDaten.iStt, iAicStamm,AbfDaten.iRolle);
                            checkEnabled();
                          }
                          //if(ev.getModifiers()==MouseEvent.BUTTON3_MASK)
                          if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                          {
                            //g.fixInfo("ev.getButton()==3");
                            //BtnAkt = (JButton)ev.getSource();
                            //MnuMove.setSelected(bMove);
                            //if(TabButton.posInhalt("Button", BtnAkt)) {
                            //  MnuDel.setEnabled(bDel || TabButton.isNull("VecAIC"));
                              BtnAkt=null;
                              if (bSave && bLoadZeitartEdt && MnuZeitart!=null)
                              {
                                MnuZeitart.setEnabled(false);
                                if (MnuZeitart2 != null)
                                  MnuZeitart2.setEnabled(false);
                                if (MnuZeitart3 != null)
                                  MnuZeitart3.setEnabled(false);
                                if (MnuZeitart4 != null)
                                  MnuZeitart4.setEnabled(false);
                                if (MnuZeitart5 != null)
                                  MnuZeitart5.setEnabled(false);
                                if (MnuZeitart6 != null)
                                  MnuZeitart6.setEnabled(false);
                                if (MnuFarbe != null)
                                  MnuFarbe.setEnabled(false);
                                MnuEdit.setEnabled(false);
                                MnuBack.setEnabled(false);
                                if (MnuDel != null)
                                  MnuDel.setEnabled(false);
                              }
                              //g.fixInfo("vor popup.show1");
                              popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
                            //}
                          }
                          else if (ev.getButton()==1 && ev.getClickCount()==2) // Doppelklick auf Tabelle (freies Feld)
                          {
                            if (iMode==_INFO && (iBits&cstAuswahl)>0)
                            {
                              BtnEdit.setSelected(true);
                              setMode("Edit");
                            }
                            else
                            {
                              //g.progInfo("doppelklick bei "+iAicStamm);
                              BtnAkt = null;
                              int iPos2 = getPos();
                              //for(;!table.isSelected(iPos2,0);iPos2++);
                              if (iPos != iPos2)
                                g.fixInfo("iPos<>iPos2:"+iPos+"/"+iPos2);
                              if (iPos2 >= 0)
                              {
                                TabPanel.setPos(iPos2);
                                int iStamm=TabPanel.getI("AIC_Stamm");
                                //g.fixInfo("prüfe Sperre bei Doppelklick");
                                if (!gesperrt(iStamm,true))
                                {
                                // auf nicht bewilligt stellen
                                  CboStamm.setSelectedAIC(iStamm);
                                  DateWOD DW = PointToDW(ev.getPoint());
                                  VB.getVonEditor().setDate(DW.toTimestamp());
                                  DW.tomorrow();
                                  VB.getBisEditor().setDate(DW.toTimestamp());
                                  if (getiZAcopy() > 0)
                                    CboZeitart.getComboBox().setSelectedAIC(iZAcopy);
                                  setStatusVG(0);
                                  setStatusSV(0);
                                  EditOk(false);
                                }
                              }
                            }
                          }
			}
			public void mouseEntered(MouseEvent ev)
			{
				//g.progInfo("mouseEntered");
			}
			public void mouseExited(MouseEvent ev)
			{
				//g.progInfo("mouseExited");
			}
			public void mouseReleased(MouseEvent ev)
			{
//				g.fixtestError("mouseReleased:"+ev.getPoint());
				if (bReady && !bStammGesperrt)
				{
					//ErmittleStammDate(ev.getPoint(),true);
					//bStartPress=true;
					if (TSBisLast != null)
					{
						TSBisLast=null;
						//Edit(null,false);
                                                if (!TabPanel.isNull("Panel"))
                                                {
                                                  //long lClock2=Static.get_ms();
                                                  ((JPanel)TabPanel.getInhalt("Panel")).remove(LblVor);
                                                  BtnAkt = null;
                                                  if (getiZAcopy()>0)
                                                    CboZeitart.getComboBox().setSelectedAIC(iZAcopy);
                                                  //CbxBewilligt.setSelected(false);
                                                  setStatusVG(0);
                                                  setStatusSV(0);
                                                  EditOk(false);
                                                  //g.clockInfo("neu "+CboZeitart.getComboBox().getSelectedBezeichnung(),lClock2);
                                                }
						//((JPanel)TabPanel.getInhalt("Panel")).repaint();
					}
				}
				//else
				//	g.progInfo("mouseReleased: not ready");
			}

		});
                table.addMouseWheelListener(new MouseWheelListener()
                {
                  public void mouseWheelMoved(MouseWheelEvent e) {
                    TableScrollbar TSB = table.getVertSB();
                    int i=TSB.getAdjustable().getValue()+e.getWheelRotation()*100;
                    int iMax=TSB.getAdjustable().getMaximum()-TSB.getAdjustable().getVisibleAmount();
                    TSB.setValue(i<0 ? 0:i>iMax?iMax:i);
                  }
                });
	}
        if((AbfDaten.iBits&Abfrage.cstSynchron)>0)
          Fom.thisFrame.addWindowListener(new WindowListener()
                        {
                                public void windowClosed(WindowEvent e)
                                {
                                }
                                public void windowOpened(WindowEvent e)
                                {
                                }
                                public void windowClosing(WindowEvent e)
                                {
                                }
                                public void windowIconified(WindowEvent e)
                                {
                                }
                                public void windowDeiconified(WindowEvent e)
                                {
                                }
                                public void windowActivated(WindowEvent e)
                                {
                                  int iStammNeu=g.getSyncStamm(AbfDaten.iStt, AbfDaten.iRolle);
                                  if(iStammNeu > 0 && iStammNeu!=iAicStamm)
                                  {
                                    posZeile(iStammNeu);
                                  }
                                }
                                public void windowDeactivated(WindowEvent e)
                                {
                                }
			});
        
        Pnl.add(this);
        g.clockInfo2("Planung "+sBez,lClock1);
}

      private void setStatusVG(int iStatus)
      {
        CbxBewilligt.setSelected(iStatus==1);
        if (EdtStatus!=null)
          EdtStatus.setNr(iStatus);
      }
      
      private void setStatusSV(int iStatus)
      {
        if (EdtStatusSV!=null)
          EdtStatusSV.setNr(iStatus);
      }

      private Tabellenspeicher showTabSperren(int iStamm)
      {
    	Tabellenspeicher Tab= new Tabellenspeicher(g,"select ben.kennung Benutzer,v.bezeichnung Stamm,p_von von,p_bis bis,p.timestamp seit"+(g.Def() ? ",b.defbezeichnung bei":"")+" from stammview2 v"+
                     " join sperre s on v.aic_stamm=s.aic_stamm and v.aic_rolle is null join protokoll p on s.aic_protokoll=p.aic_protokoll"+
                     " join begriff b on place=b.aic_begriff  join benutzer ben on p.aic_benutzer=ben.aic_benutzer where s.aic_bewegungstyp="+iAIC_Bewegungstyp+" and"+
                     (iStamm>0?" v.aic_stamm="+iStamm:g.in("v.aic_stamm",VecNeu)),true);
        g.setTitel(Tab, new String[] {"Benutzer","Stamm","von","bis","seit","bei"});
        return Tab;
      }

      private void setMode(String s)
      {
        int iM=s.equals("Info") ? _INFO:s.equals("Edit") ? _EDIT:s.equals("Schicht") ? _SCHICHT:s.equals("Fehlz") ? _FEHLZ:s.equals("Ist") ? _IST:s.equals("Move") ? _MOVE:-1;
        if (iM==iMode)
          return;
        int iMv=iMode;
        iMode=iM;
        if (iM==_EDIT)
          bInfoFirst=true;
        g.fixInfo("setMode "+s+"/"+iMv+"->"+iM);
        if (iM==_INFO) // zurück auf Info
          clearSperre(true);
        if (iMv==_INFO) // Edit (von Info)
        {
          setSperre();
          Refresh();
        }
        //ColSperre();
        checkEnabled();
        bMove=iM==_MOVE;
        if (iM==_SCHICHT && Schicht() || iM==_FEHLZ && !Schicht())
          CboZeitart.OpenList(null,true);
        else if (iMv==_SCHICHT)
          CboZeitart.hideFrm();
      }

      private void setSperre()
      {
        //g.fixtestInfo("setSperre:"+g.getVonBis2());
        if (TabSperre.size()>0)
          clearSperre(false);
        //BtnInfo.setEnabled(false);
        Timestamp dtVon=TSZeitraumVon;
        Timestamp dtBis=TSZeitraumBis;
        Vector<Integer> Vec=TabPanel.getVecSpalteI("Aic_Stamm");
        String s="select aic_stamm from sperre where aic_bewegungstyp="+iAIC_Bewegungstyp+" and"+g.in("Aic_Stamm",Vec)+
                                              "and (p_von is null or p_von<"+g.SQL_Format(dtBis)+") and (p_bis is null or p_bis>"+g.SQL_Format(dtVon)+")";
        //g.fixtestInfo(s);
        Vector<Integer> VecLock=SQL.getVector(s,g);
        SQL Qry = new SQL(g);
        if (iProtokoll == 0)
          iProtokoll=g.Protokoll("Planung",iPlanung);
        g.fixtestInfo("setSperre -> Prot="+iProtokoll);
        for (int i=0;i<Vec.size();i++)
        {
          int iAic=Sort.geti(Vec, i);
          if (!VecLock.contains(iAic) && VecNeu.contains(iAic))
          {
            Qry.add("AIC_Stamm", iAic);
            Qry.add("aic_bewegungstyp", iAIC_Bewegungstyp);
            Qry.add("AIC_Protokoll", iProtokoll);
            Qry.add("P_Von", dtVon);
            Qry.add("P_Bis", dtBis);
            TabSperre.addInhalt("Aic", Qry.insert("sperre", true));
            TabSperre.addInhalt("Stamm", iAic);
            TabSperre.addInhalt("von", dtVon);
            TabSperre.addInhalt("bis", dtBis);
          }
        }
        Qry.free();
        //g.fixInfo("setSperre:"+Vec+"/"+VecLock+"->"+TabSperre.getVecSpalteI("Stamm"));
        ColSperre();
        //if (!Menge() && gesperrt(iAicStamm,true))
        //g.fixInfo("prüfe Sperre bei setSperre");
        if (gesperrt(0,true))
        {
          //new Message(Message.INFORMATION_MESSAGE+Message.SHOW_TAB, Fom.thisJFrame(), g).showDialog("Sperre",showTabSperren(iAicStamm));
          setInfo();
        }
      }

      private void clearSperre(boolean bCol)
      {
        //g.fixtestInfo("clearSperre");
        //if (bCol)
        //  Refresh(VecNeu, iAicStamm, true);
        if (BtnInfo.isSelected())
          iProtokoll=0;
        g.fixtestInfo("clearSperre -> Prot="+iProtokoll);
        g.exec("delete from sperre where"+g.in("Aic_Sperre",TabSperre.getVecSpalteI("Aic")));
        TabSperre.clearAll();
        if (bCol) ColSperre();
      }

      private void ColSperre()
      {
        //g.fixtestInfo("ColSperre");
        if (TabPanel!=null)
          for (int i = 0; i < TabPanel.size(); i++)
          {
            int iStamm = TabPanel.getI(i, "AIC_Stamm");
            //g.fixtestInfo(" >"+g.getStamm(iStamm)+":"+gesperrt(iStamm));
            if (TabPanel.isNull(i,"Vec"))
            {
              table.setFont(i,-1,gesperrt(iStamm,false) ? Global.fontInaktiv:Global.fontBezeichnung);
              table.setForeground(i,-1,/*gesperrt(iStamm,false)?g.ColSperre:*/ShowAbfrage.istDa(g,iStamm)?Global.ColBezeichnung:Global.ColAustritt);
            }
          }
      }

      private void History()
      {
        long lClock=Static.get_ms();
        String sRest="";
        for(TabSpaltenDaten.posInhalt("Nummer",iRest);!TabSpaltenDaten.eof();TabSpaltenDaten.moveNext())
		{
			String sDatentyp=TabSpaltenDaten.getS("Datentyp");
			if (sDatentyp.equals("BewStamm"))
			{
				int iAic = TabSpaltenDaten.getI("Kennung2");
				String sBez=TabSpaltenDaten.getS("Bezeichnung");
				sRest+=",(select bezeichnung from stammview2"+g.join("bew_stamm","stammview2","stamm")+" where aic_eigenschaft="+iAic+" and aic_bew_pool=p.aic_bew_pool and aic_rolle is null) '"+sBez+"'";
				//g.fixtestError("Rest"+TabSpaltenDaten.getI("Nummer")+":"+sDatentyp+"/"+TabSpaltenDaten.getS("Bezeichnung"));
			}
		}
//        g.fixtestError("Rest="+sRest);
        String s= "select * from (select p.gueltig,(select bezeichnung from stammview2"+g.join("bew_stamm","stammview2","stamm")+" where aic_eigenschaft="+iLoadZeitart+
        " and aic_bew_pool=p.aic_bew_pool and aic_rolle is null) bezeichnung,von,bis,dauer/3600 dauer,benutzer.kennung erfasst_von,Timestamp erfasst_um"+//SQL.ifnull("p.pro_aic_protokoll","''","'gelöscht'")+" Status"+
        (iLoadBewilligt==0 ? "":",(select Spalte_Boolean from bew_boolean where aic_bew_pool=p.aic_bew_pool and aic_eigenschaft="+iLoadBewilligt+") bewilligt")+
        (iLoadBewilligt3VG==0 ? "":",(select kennung from auswahl join Bew_Aic on aic=aic_auswahl where aic_bew_pool=p.aic_bew_pool and Bew_Aic.aic_eigenschaft="+iLoadBewilligt3VG+") Status")+
        (iLoadBewilligt3SV==0 ? "":",(select kennung from auswahl join Bew_Aic on aic=aic_auswahl where aic_bew_pool=p.aic_bew_pool and Bew_Aic.aic_eigenschaft="+iLoadBewilligt3SV+") StatusSV")+sRest+
        " ,(select benutzer.kennung from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer"+g.join2("protokoll","logging")+" where aic_protokoll=p.pro_aic_protokoll) entfernt_von,"+
        " (select Timestamp from protokoll where aic_protokoll=p.pro_aic_protokoll) entfernt_um"+
        " from bew_von_bis join bewview3 p on p.aic_bew_pool=bew_von_bis.aic_bew_pool and bew_von_bis.aic_eigenschaft="+iLoadVonBis/*+g.join("bew_stamm","s","bewview3","bew_pool")+g.join("bew_pool","p","s","bew_pool")*/+" join protokoll p2 on p.aic_protokoll=p2.aic_protokoll"+g.join("logging","p2")+
        " join benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+
        " where p.aic_bewegungstyp="+iAIC_Bewegungstyp+" and p.anr=? and p.gueltig is not null) x order by "+g.orderD2("entfernt_um")+"desc,von";
        String sBez=TabSonst != null ? TabSonst.getBezeichnungS(iAicStamm):SQL.getString(g,"select bezeichnung from stammview where aic_stamm="+iAicStamm);
        new Tabellenspeicher(g,s,""+iAicStamm,true).showGrid(sBez,Fom.thisFrame);
        g.progInfo("SQL-History:"+s);
        g.clockInfo("History "+sBez, lClock);
      }

      /*private String CR(String s)
      {
        return s==null ? " \n ":s+(s.indexOf("\n") > 0 ? "" :"\n ");
      }*/

      @SuppressWarnings("unchecked")
      private void Drucken()
      {
        //g.fixInfo("Druck wird erst programmiert!");
        int inC=table.getNumColumns();
        int inR=table.getNumRows();
        boolean bDrehen=(iDBits&DREHEN)>0;
        Vector<String> Vec2=new Vector<String>();
        for(int i=0;i<(bDrehen?inR:inC)+2;i++)
          Vec2.addElement(""+i);
        Vec2.addElement("LINES");
        Tabellenspeicher Tab=new Tabellenspeicher(g,Vec2);
        Tabellenspeicher TabFarbe = new Tabellenspeicher(g, new String[] {"Zeile", "Spalte", "Farbe"});
        TabFarbe.sGruppe="Zeile";
        TabFarbe.sAIC="Spalte";
        // Überschrift füllen
        Tab.newLine();
        Tab.setInhalt("0","U");
        if (bDrehen)
        {
          Vector<String> VecBez = table.getRowLabels();
          int iZ=AUTable.getAnzCR(VecBez);
          if (iZ<2) iZ=2;
          Tab.setInhalt("1", 2+AUTable.expandCR("",iZ));
          for (int i = 0; i < inR; i++)
            if ((/*AZ() ||*/ bSum) && i == inR - 1)
              Tab.setInhalt("" + (i + 2),2+AUTable.expandCR(Sort.gets(VecBez, i),iZ));//+(Sort.gets(VecBez, i).indexOf("\n") > 0 ? "" :"\n "));
            else
            {
              Tab.setInhalt("" + (i + 2), 1 + AUTable.expandCR(Static.cutString(Sort.gets(VecBez, i)),iZ));
              if (sLoadFarbe!=null && !table.getBackground(i,-1).equals(Global.ColPlanung))// && TabSonst.exists(sLoadFarbe) && TabSonst.size()>i && !TabSonst.isNull(i,sLoadFarbe))
                setFarbe(TabFarbe,-1,i+2,table.getBackground(i,-1).getRGB());//TabSonst.getI(i,sLoadFarbe));
            }
          VecBez = table.getColumnLabels();
          for (int i = 0; i < inC; i++)
          {
            Tab.newLine();
            Tab.setInhalt("0", iAnz > i ? i+1:"S");
            Tab.setInhalt("1", Sort.gets(VecBez, i).replaceAll("\n"," "));
            if (table.getBackground( -1, i) == Global.ColHoliday)
              setFarbe(TabFarbe, i+1, 1, Global.ColHoliday.getRGB());
            if (i<iAnz)
              Tab.setInhalt("LINES", "L");
            else
              for (int i2 = 0; i2 < inR; i2++)
                Tab.setInhalt("" + (i2 + 2), table.getCell(i2, i));
          }
          //Tab.showGrid("Druck-Tab");
          //return;
        }
        else
        {
          Vector<String> VecBez = table.getColumnLabels();
          int iZ=AUTable.getAnzCR(VecBez);
          Tab.setInhalt("1", 0+AUTable.expandCR("",iZ));
          for (int i = 0; i < inC; i++)
          {
            Tab.setInhalt("" + (i + 2), (iAnz > i ? 1:2) + AUTable.expandCR(Sort.gets(VecBez, i),iZ));//Sort.gets(VecBez, i) + (Sort.gets(VecBez, i).indexOf("\n") > 0 ? "" : "\n "));
            if (table.getBackground( -1, i) == Global.ColHoliday)
              setFarbe(TabFarbe, -1, i + 2, Global.ColHoliday.getRGB());
          }
          // Namen füllen
          VecBez = table.getRowLabels();
          for (int i = 0; i < inR; i++)
          {
            Tab.newLine();
            Color Col=table.getBackground(i,-1);
            boolean bS = (/*AZ() ||*/ bSum) && i == inR - 1;
            Tab.setInhalt("0", bS ? "S" : Col.equals(Global.ColZSum) ? "Z": i + 1);
            Tab.setInhalt("1", Sort.gets(VecBez, i));
            if (sLoadFarbe!=null && !bS && !Col.equals(Global.ColPlanung))
              setFarbe(TabFarbe,i+1,1,Col.getRGB());//TabSonst.getI(i,sLoadFarbe));
            // Seitensummen (Ist/Soll..) füllen
            //if (!bS)
            {
              for (int i2 = iAnz; i2 < inC; i2++)
                Tab.setInhalt("" + (i2 + 2), table.getCell(i, i2));
              Tab.setInhalt("LINES", "L");
            }
          }
        }
        // Schicht füllen
        for (int i=0;i<TabButton.size();i++)
           setTextFarbe(Tab,TabFarbe,TabButton.getI(i,"Stamm"),TabButton.getTimestamp(i,"Von_Neu"),TabButton.getTimestamp(i,"Bis_Neu"),TabButton.getTimestamp(i,"Von2_Neu"),TabButton.getTimestamp(i,"Bis2_Neu"),
                        TabButton.getI(i,"Zeitart"),iRest>0 && TabRest.posInhalt("Button",TabButton.getInhalt("Button",i)) ? " "+TabRest.getM("Neu"):"",TabZeitart);
        // Fehlzeiten füllen
         if (Schicht())
          for (int i=0;i<TabFilter.size();i++)
            setTextFarbe(Tab,TabFarbe,TabFilter.getI(i,"V"+iLoadFilterStamm),TabFilter.getTimestamp(i,"V"+iLoadFilterIstZeit),TabFilter.getTimestamp(i,"B"+iLoadFilterIstZeit),null,null,TabFilter.getI(i,"V"+iLoadFilterZeitart),null,TabZeitart2);
        TabFarbe.sort("Zeile",true);
        // Gesamtsumme
        if (bSum)
          for(int i=0;i<iAnz;i++)
          {
            if (ZS())
              for (int i2=1;i2<TabPanel.size();i2++)
                if (!TabPanel.isNull(i2,"Vec"))
                {
                  Vector<JLabel> VecMom=(Vector)TabPanel.getInhalt("VecAZ",i2);
                  String s = VecMom.elementAt(i * 2).getText();
                  if (bESum)
                    s+=Static.FillSpace("Mass",VecMom.elementAt(i * 2+1).getText(),12);
                  if (bDrehen)
                    Tab.setInhalt(i + 1, "" + (i2 + 2), s);
                  else
                    Tab.setInhalt(i2+1, "" + (i + 2), s);
                }
            String s=VecAZ.elementAt(i * 2).getText();
            if (bESum)
              s+=Static.FillSpace("Mass",VecAZ.elementAt(i * 2+1).getText(),12);
            //g.fixtestInfo(i+":"+s);
            if (bDrehen)
              Tab.setInhalt(i + 1, "" + (inR + 1), s);
            else
              Tab.setInhalt(inR,""+(i+2),s);
          }
        if (Global.bInfoDruck)
          TabFarbe.showGrid("Farbe");
        //Tab.showGrid("Druck-Tab");
        /* ------ Druck ------ */
        DruckHS dh = new DruckHS(g, sBez, iLayout,((iDBits&DAUSW)>0 ? Drucken.cstDruckerauswahl:0)+((iDBits&FARBE)>0 ? Drucken.cstDFFarbe:0) /*iDruckBits*/,0 /*Stamm*/);
        dh.setDTitel(sDruckText+" " + g.getBegriffS("Show", "fuer") + " "+g.getVonBis("dd.MM.yyyy", false),false,Global.fontTitel);
        dh.printTitel(false,true,false,false);
        dh.TabFarbe=TabFarbe;
        dh.addOutliner(Tab,null,iVorlage, Drucken.cstGesamtsumme, Drucken.cstMEMO2+Drucken.cstPb, null/*TabBreite*/);
        dh.vorschau();

      }

      private boolean ZS()
      {
        return iEigGruppe>0 && !bKZS & bSum;
      }

      private void setTextFarbe(Tabellenspeicher Tab,Tabellenspeicher TabFarbe,int iStamm,Timestamp TSVon1,Timestamp TSBis1,Timestamp TSVon2,Timestamp TSBis2,int iZeitart,String sMemo,Tabellenspeicher TabZeitart)
      {
        if (!TabZeitart.posInhalt("aic_stamm",iZeitart))
        {
          Static.printError("Zeitart "+iZeitart+" nicht gefunden");
          return;
        }
        if (TSVon1==null)
        {
          Static.printError("Zeitpunkt ist null bei "+g.getStamm(iStamm));
          return;
        }
        int iZeile=TabPanel.getPos("AIC_Stamm",iStamm)+1;
        //DateWOD DW=new DateWOD(TSZeitraumVon);
        //int iSpalte=(int)((TSVon1.getTime()-TSZeitraumVon.getTime())/DateWOD.TAG+2);
        //g.fixtestInfo("TSZeitraumVon="+TSZeitraumVon+"/"+new DateWOD(TSZeitraumVon)+", TSVon1="+TSVon1);
        int iSpalte=1;
        for(DateWOD DW = new DateWOD(TSZeitraumVon); DW.getTimeInMilli() <= TSVon1.getTime(); DW.tomorrow())
          iSpalte++;
        g.fixtestError("setTextFarbe1: Spalte="+iSpalte+", Zeile="+iZeile);
        boolean bDrehen=(iDBits&DREHEN)>0;
        if (bDrehen)
        {
          int iSpNeu=iZeile+1;
          iZeile=iSpalte-1;
          iSpalte=iSpNeu;
        }
        g.fixtestError("setTextFarbe2: Spalte="+iSpalte+", Zeile="+iZeile);
        if (Tab.getInhalt("" + iSpalte,iZeile)!=null)
        {
          if (iSpalte>1)
          {
            g.fixtestInfo("Zeile " + iZeile + "/ Spalte " + iSpalte + " schon besetzt: " + g.getStamm(iStamm) + "/" + TSVon1);
            setFarbe(TabFarbe, iZeile, iSpalte, Global.ColDoppelt.getRGB());
          }
          return;
        }
        String sBtnText = bVB && TabZeitart!=TabZeitart2 && !TSVon1.equals(TSBis1) ? " " + new Zeit(TSVon1, "HH:mm") + "-" + new Zeit(TSBis1, "HH:mm") + (TSBis2 == null?"":", "+ new Zeit(TSVon2, "HH:mm") + "-"+ new Zeit(TSBis2, "HH:mm")) :
               Static.bTranslate && !Schicht() ? TabZeitart.getS("Bezeichnung").substring(0, 3) : TabZeitart.getS("KZ");
        if ((iDBits&MEMO)>0 && !Static.Leer(sMemo))
          sBtnText+=(iDBits&MEMO2)>0 ? "\n"+sMemo:" "+sMemo.replaceAll("\n"," ");
        //   +(Schicht() && iRest>0 && TabRest.posInhalt("Button",Btn) ? " "+TabRest.getM("Neu"):"")
        Tab.setInhalt(iZeile, "" + iSpalte, sBtnText);
        setFarbe(TabFarbe,iZeile,iSpalte,/*(TabZeitart==TabZeitart2 && iZeitart>0 ? g.ColDisabled:*/g.getColor(iZeitart, Global.ColKein).getRGB());
        /*TabFarbe.addInhalt("Zeile", iZeile);
        TabFarbe.addInhalt("Spalte", iSpalte);
        int iFarbe = g.getColor(iZeitart, g.ColKein).getRGB();
        TabFarbe.addInhalt("Farbe", iFarbe);*/
      }

      private void setFarbe(Tabellenspeicher Tab,int iZeile,int iSpalte,int iFarbe)
      {
        if (!Tab.posInhalt(iZeile,iSpalte))
        {
          Tab.newLine();
          Tab.setInhalt("Zeile", iZeile);
          Tab.setInhalt("Spalte", iSpalte);
        }
        //else
        //  g.fixtestInfo("überschreibe "+iZeile+"/"+iSpalte+" mit "+iFarbe);
        Tab.setInhalt("Farbe", iFarbe);
      }

      private boolean posZeile(int iAic)
      {
        g.progInfo("posZeile "+iAic);
		if (TabPanel==null)
			return false;
        TabPanel.push();
        boolean b=TabPanel.posInhalt("AIC_Stamm",iAic);
        if (b)
        {
          int i=TabPanel.getPos();
          table.setSelectedCells(new JCCellRange(i, -1, i, 0));
          table.makeVisible(i,0);
          //g.fixtestError("makeVisible: Zeile"+i);
        }
        TabPanel.pop();
        return b;
      }

private DateWOD PointToDW(Point P)
{
  if (TSZeitraumVon==null || PntPanel==null || table.getHorizSB()==null)
    g.fixInfo("PointToDW:"+TSZeitraumVon+"/"+PntPanel+"/"+table.getHorizSB());
        DateWOD DW=new DateWOD(Math.round((table.getHorizSB().getAdjustable().getValue()+P.x-PntPanel.x)/dFaktor+TSZeitraumVon.getTime()));
        if (Stunden())
          DW.trunc(lGenau);
        else
          DW.setTimeZero();
        return DW;
}

private int getPos()
{
  int iPos=0;
  int iMax=table.getNumRows();
  for(;!table.isSelected(iPos,0) && iPos<iMax;iPos++);
  return iPos<iMax ? iPos:-1;
}

private Vector<Integer> getSelectedAics()
{
  Vector<Integer> Vec=new Vector<Integer>();
  for(int iPos=0;iPos<table.getNumRows();iPos++)
    if (table.isSelected(iPos,0))
      Vec.addElement(TabPanel.getI(iPos,"AIC_Stamm"));
  return Vec;
}

private void ErmittleStammDate(Point P,boolean bFirst)
{
  //g.fixtestInfo("-------------------------   ErmittleStammDate:"+P+"/"+bFirst+"/"+PntPanel);
	//g.progInfo("x="+(P.x-P2.x)+", y="+(P.y-P2.y));
	/*if(PntPanel==null)
	{
	//g.progInfo("ErmittleStammDate:"+P.x+"/"+P.y+"/"+bBis);
                TabPanel.moveFirst();
		PntPanel=((Component)TabPanel.getInhalt("Panel")).getParent().getLocation();
	}*/
	//g.progInfo("ErmittleStammDate: First="+bFirst+"P.y="+P.y+"/"+PntPanel.y);
        if (P==null || PntPanel==null)
          return;
	if(!bFirst && P.y>PntPanel.y)
	{
		//Static.sleep(100);
		int iPos=getPos();
		//for(;!table.isSelected(iPos,0);iPos++);
                if (iPos>=0)
                {
                  TabPanel.setPos(iPos);
                  iAicStamm = TabPanel.getI("AIC_Stamm");
                  checkEnabled();
                  CboStamm.setSelectedAIC(iAicStamm);
                  if(bStammGesperrt)
                  {
                    if (iMode==_INFO)
                      new Message(Message.INFORMATION_MESSAGE,(JFrame)Fom.thisFrame,g).showDialog("Edit-Modus_aktivieren");
                    else
                      new Message(Message.INFORMATION_MESSAGE + Message.SHOW_TAB, Fom.thisJFrame(), g).showDialog("Sperre", showTabSperren(iAicStamm));
                    return;
                  }
                }
                //if((AbfDaten.iBits&Abfrage.cstSynchron)>0) ööö
                //  g.setSyncStamm(AbfDaten.iStt,iAicStamm);
	}
        if(P.x>PntPanel.x)
	{
		DateWOD DW=PointToDW(P);
		if(bFirst)
			VB.getVonEditor().setDate(DW.toTimestamp());
		else
		{
                  if (Stunden())
                    DW.add(lGenau);
                  else
                    DW.tomorrow();
                  if(TSBisLast == null || !TSBisLast.equals(DW.toTimestamp())) {
                    TSBisLast = DW.toTimestamp();
                    if (TSBisLast.after(TSZeitraumBis))
                        TSBisLast=new Timestamp(TSZeitraumBis.getTime());
                    VB.getBisEditor().setDate(TSBisLast);
                    //Edit(null,false);
                    //g.progInfo(CboStamm.getSelectedAIC()+":"+VB.getVon()+"-"+VB.getBis());

                    if(TabPanel.posInhalt("AIC_Stamm", CboStamm.getSelectedAIC())) {
                      JPanel Pnl = (JPanel)TabPanel.getInhalt("Panel");
                      Pnl.remove(LblVor);
                      //g.defInfo(CboStamm.getSelectedBezeichnung()+":"+CboZeitart.getComboBox().getSelectedBezeichnung()+"/"+VB);
                      if((AbfDaten.iBits&Abfrage.cstSynchron)>0)
                        g.setSyncStamm(AbfDaten.iStt,CboStamm.getSelectedAIC(),AbfDaten.iRolle);
                      addComponent(Pnl, LblVor, CboStamm.getSelectedAIC(), CboZeitart.getAIC(),iLoadFarbe>0 ? CboFarbe.getAIC():0, (Timestamp)VB.getVon(),
                                   (Timestamp)VB.getBis(),null,null,null,null, 0, 0, 0, null,0,true);
                      //setBtnBorder(BtnAkt);
                      /*if (AZ())
                      {
                        int iCol=(int)((VB.getVon().getTime() - TSZeitraumVon.getTime()) / DateWOD.TAG);
                        //Object Pnl2=table.getCell(table.getNumRows() - 1, iCol);
                        //g.progInfo("Pnl2="+Static.className(Pnl2));
                        //Pnl2.removeAll();
                        //table.setCell(table.getNumRows() - 1, iCol,null);
                        //table.repaint();
                      //((JLabel)Pnl2.getComponent(0)).setText("von");
                      //((JLabel)Pnl2.getComponent(1)).setText("bis");
                        VecAZ.elementAt(iCol*2).setText(""+new Zeit(VB.getVon(),"d. H:mm"));
                        VecAZ.elementAt(iCol*2+1).setText(""+new Zeit(VB.getBis(),"d. H:mm"));
                        //table.setCell(table.getNumRows() - 1, iCol, VB.Format("d. H:mm"));
                        //table.repaint();
                      }*/
                      Pnl.repaint();
                      //table.setRowLabelOffset(1);
                    }
                  }
		}
	}
	//g.progInfo("-> Stamm="+iStamm+", TS="+DW);

}

/*private void setHide()
{
  boolean b=!table.isEnabled();
    //g.setAbfrageWeg(Fom.getBegriff(),AbfDaten.iBegriff, b?0:1);
  g.AbfrageAusblenden(iD,AbfDaten.iBegriff,!b);
    if (b)
      setEnabled(b);
    Refresh(VecNeu,iAicStamm,true);
}*/

private int getiZAcopy()
{
  if (iMode==_SCHICHT)
  {
    int iZA = CboZeitart.getSelected();
    if(iZA>0)
      iZAcopy = iZA;
  }
  return iZAcopy;
}

private void addEvent(JButton Btn)
{
  if (popup == null && (bSave || Btn==null))
  {
    popup = new JPopupMenu();
    popup.setLabel("Planung");
    if (VecMod!=null && VecMod.size()>0)
    {
      for (int i=0;i<VecMod.size();i++)
        g.addMenuItem(g.getBegBez(Sort.geti(VecMod,i)),popup,"M"+VecMod.elementAt(i),AL);
      popup.addSeparator();
    }
    if (bSave && bLoadZeitartEdt)
    {
      /*JMenuItem MnuCopy = */g.addMenuItem("copy",popup,"copy",AL);
      /*MnuCopy.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent ev) {
        int iPos=TabButton.getPos("Button",BtnAkt);
        if (iPos>-1 && CboZeitart.getComboBox().contains(TabButton.getI("Zeitart")))
        {
          VBcopy = null;
          VB2copy = null;
          iZAcopy=TabButton.getI(iPos,"Zeitart");
          CboZeitart.getComboBox().setSelectedAIC(iZAcopy);
        }
        //g.testInfo("copy->"+iZAcopy);
       }
      });*/

      MnuPaste = g.addMenuItem("paste", popup,"paste",AL);
      /*MnuPaste.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          //g.testInfo("paste "+iZAcopy);
          setZeitart(iZAcopy);
        }
      });*/

     if (Schicht())
     {
      JMenuItem MnuCopy2 = g.addMenuItem("copy2", popup);
      MnuCopy2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          int iPos = TabButton.getPos("Button", BtnAkt);
          if (iPos > -1 && CboZeitart.getComboBox().contains(TabButton.getI("Zeitart")))
          {
            iZAcopy = TabButton.getI(iPos, "Zeitart");
            CboZeitart.getComboBox().setSelectedAIC(iZAcopy);
            VBcopy = new VonBis(g,TabButton.getTimestamp("Von_Neu"), TabButton.getTimestamp("Bis_Neu"), "HH:mm");
            if (!TabButton.isNull("Von2_Neu"))
              VB2copy = new VonBis(g,TabButton.getTimestamp("Von2_Neu"), TabButton.getTimestamp("Bis2_Neu"), "HH:mm");
            //g.fixInfo("VBcopy=" + VBcopy+" / "+VB2copy);
            //VB.getVonEditor().setDate(null);ö
          }
          //g.testInfo("copy->"+iZAcopy);
        }
      });
      JMenuItem MnuClear2 = g.addMenuItem("clear2", popup);
      MnuClear2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          VBcopy = null;
          VB2copy = null;
        }
      });
     }
    }
    Tabellenspeicher TabCbo = CboZeitart.getTabDaten();
    if (bSave && bLoadZeitartEdt && TabCbo!=null)
    {
      MnuZeitart = new JMenu(g.TabEigenschaften.getBezeichnungS(iLoadZeitart));
      popup.add(MnuZeitart);
      //MnuZeitart.setArmed(true);

      int iMax = 40;
      int iGes = TabCbo.getAnzahlElemente(null);
      if (iGes > iMax) // && MnuZeitart2==null)
      {
        MnuZeitart2 = new JMenu(g.TabEigenschaften.getBezeichnungS(iLoadZeitart) + "2");
        popup.add(MnuZeitart2);
        //MnuZeitart2.setEnabled(MnuZeitart.isEnabled());
      }
      if (iGes > iMax * 2) // && MnuZeitart3==null)
      {
        MnuZeitart3 = new JMenu(g.TabEigenschaften.getBezeichnungS(iLoadZeitart) + "3");
        popup.add(MnuZeitart3);
      }
      if (iGes > iMax * 3) // && MnuZeitart4==null)
      {
        MnuZeitart4 = new JMenu(g.TabEigenschaften.getBezeichnungS(iLoadZeitart) + "4");
        popup.add(MnuZeitart4);
      }
      if (iGes > iMax * 4) // && MnuZeitart5==null)
      {
        MnuZeitart5 = new JMenu(g.TabEigenschaften.getBezeichnungS(iLoadZeitart) + "5");
        popup.add(MnuZeitart5);
      }
      if (iGes > iMax * 5) // && MnuZeitart6==null)
      {
        MnuZeitart6 = new JMenu(g.TabEigenschaften.getBezeichnungS(iLoadZeitart) + "6");
        popup.add(MnuZeitart6);
      }
      for (TabCbo.moveFirst(); !TabCbo.eof(); TabCbo.moveNext())
      {
        if (TabZeitart.posInhalt("aic_stamm", TabCbo.getI("aic_stamm")))
        {
          JMenuItem MnuZ = new JMenuItem(TabCbo.getS("Bezeichnung") +
                                         (Static.bTranslate && !Schicht() ? " (" + TabZeitart.getS("Bezeichnung").substring(0, 3) + ")" : TabZeitart.isNull("KZ") ? "" :
                                          " (" + TabZeitart.getS("KZ") + ")"));
          MnuZ.setFont(Transact.fontStandard);
          int iPos = TabCbo.getPos() + 1;
          if (iPos > 5 * iMax)
            MnuZeitart6.add(MnuZ);
          else if (iPos > 4 * iMax)
            MnuZeitart5.add(MnuZ);
          else if (iPos > 3 * iMax)
            MnuZeitart4.add(MnuZ);
          else if (iPos > 2 * iMax)
            MnuZeitart3.add(MnuZ);
          else if (iPos > iMax)
            MnuZeitart2.add(MnuZ);
          else
            MnuZeitart.add(MnuZ);
          TabZeitart.setInhalt("popup", MnuZ);
          MnuZ.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              if (TabZeitart.posInhalt("popup", ev.getSource()))
                setZeitart(TabZeitart.getI("aic_stamm"));
            }
          });
        }
      }
      //if (g.Def())
      //  g.addMenuItem(Schicht()? "Tagesmusterwahl":"Zeitartwahl", popup,"Zeitartwahl",AL);
    }
    if (AF())
    {
      MnuFarbe = new JMenu(g.TabEigenschaften.getBezeichnungS(iLoadFarbe));
      popup.add(MnuFarbe);
      TabCbo = CboFarbe.getTabDaten();
      for (TabCbo.moveFirst(); !TabCbo.eof(); TabCbo.moveNext())
      {
        if (TabFarbe.posInhalt("aic_stamm", TabCbo.getI("aic_stamm")))
        {
          JMenuItem MnuZ = new JMenuItem(TabCbo.getS("Bezeichnung"));
          MnuZ.setFont(Transact.fontStandard);
          MnuFarbe.add(MnuZ);
          TabFarbe.setInhalt("popup", MnuZ);
          MnuZ.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              if (TabFarbe.posInhalt("popup", ev.getSource()))
                setFarbe(TabFarbe.getI("aic_stamm"));
            }
          });
        }
      }
    }

    if (Menge())
    {
      g.addMenuItem("Suche", popup, "suchen", AL);
      if (Schicht())
        g.addMenuItem("Druck", popup, "Druck", AL);
    }
      /*.addMouseListener(new MouseListener()
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

              });*/
    if (bSave)
    {
      MnuEdit = g.addMenuItem("Edit",popup,"Edit",AL);
      MnuBack = g.addMenuItem("Ruecksetzen",popup,"Ruecksetzen",AL);
      if (bNew)
        MnuNeu = g.addMenuItem("tab_new", popup,"New",AL);
      MnuDel = g.addMenuItem("tab_del", popup,"del",AL);
      MnuSave = g.addMenuItem("tab_save", popup,"Save",AL);
    }
    /*JMenuItem MnuDrucken = new JMenuItem(g.getBegriff("Button", "Druck"));
    popup.add(MnuDrucken);
    MnuDrucken.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        JFrame Fom1=new JFrame("table");
        //BufferedImage img=((JFrame)rFom.thisFrame).getSubimage(0,0,100,100);
        Image img=jclass.base.BaseComponent.createImage(table,table.getWidth(),table.getHeight());
        //Image img=table.createImage(100,100);
        //table.get
        ImageIcon imgicon=new ImageIcon(img);
        Fom1.getContentPane().add(new JButton(imgicon));
        //Fom1.getContentPane().add(table.getSource());
        //Image img=new BufferedImage().
        Fom1.pack();
        Fom1.setVisible(true);
      }
    });*/
    popup.addSeparator();
    g.addMenuItem("tab_refr",popup,"Refresh",AL);
    if(g.History())
    {
       g.addMenuItem("History",popup,"History",AL);/*.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e)
          {
            long lClock=Static.get_ms();
            String sBez=TabSonst != null ? TabSonst.getBezeichnungS(iAicStamm):SQL.getString(g,"select bezeichnung from stammview where aic_stamm="+iAicStamm);
            new Tabellenspeicher(g,getHistory(iAicStamm),true).showGrid(sBez);
            g.clockInfo("History "+sBez, lClock);
          }
      });*/
    }
    popup.addSeparator();
    if (AbfSonst != null)
      g.addMenuItem("Abfrage easy",popup,"Spalten",AL);
    /*addCbxItem("Frage",bAsk,popup).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        bAsk = ((JCheckBoxMenuItem)ev.getSource()).getState();
      }
    });*/
    /*MnuMove = addCbxItem("verschieben",bMove,popup);
    MnuMove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        bMove = ((JCheckBoxMenuItem)ev.getSource()).getState();
      }
    });*/
    /*if (AZ())
    {
      addCbxItem("Altdaten",bOld,popup).addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          bOld = ((JCheckBoxMenuItem)ev.getSource()).getState();
          Refresh(VecStamm,iAicStamm,true);
        }
      });
    }*/
    if (Stunden())
    {
      ButtonGroup RadGroup = new ButtonGroup();
      JRadioButtonMenuItem Mnu1h = new JRadioButtonMenuItem("1 h");
      RadGroup.add(Mnu1h);
      popup.add(Mnu1h);
      Mnu1h.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          lGenau = DateWOD.STUNDE;
        }
      });
      JRadioButtonMenuItem Mnu30min = new JRadioButtonMenuItem("30 min");
      RadGroup.add(Mnu30min);
      popup.add(Mnu30min);
      Mnu30min.setSelected(true);
      Mnu30min.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          lGenau = DateWOD.STUNDE / 2;
        }
      });
      JRadioButtonMenuItem Mnu15min = new JRadioButtonMenuItem("15 min");
      RadGroup.add(Mnu15min);
      popup.add(Mnu15min);
      Mnu15min.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          lGenau = DateWOD.STUNDE / 4;
        }
      });
    }
    /*if (Schicht())
    {
      addCbxItem("von_bis",bVonBis,popup).addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          bVonBis = ((JCheckBoxMenuItem)ev.getSource()).getState();
        }
      });
    }*/
    g.addMenuItem("tab_para",popup,"Parameter",AL);/*.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        ParameterChange();
      }
    });*/

    if (g.Def())
    {
      popup.addSeparator();
      JMenu MnuTab = new JMenu("Tabellenspeicher");
      popup.add(MnuTab);
      g.addMenuItem("Sperren",MnuTab,"TabSperren",AL);
      addPopupForTab(MnuTab,"Daten");
      addPopupForTab(MnuTab,"WebDaten");
      if (AbfFilter != null)
        addPopupForTab(MnuTab,"Filter");
      if (AbfSonst != null)
        addPopupForTab(MnuTab,"Sonst");
      addPopupForTab(MnuTab,"Zeitart");
      if (Schicht())
        addPopupForTab(MnuTab,"Zeitart2");
      if (TabFarbe != null)
        addPopupForTab(MnuTab,"Farbe");
      addPopupForTab(MnuTab,"SpaltenDaten");
      if (AbfFilter != null)
        addPopupForTab(MnuTab,"SpaltenFilter");
      if (AbfSonst != null)
        addPopupForTab(MnuTab,"SpaltenSonst");
      addPopupForTab(MnuTab,"Button");
      addPopupForTab(MnuTab,"Panel");
      addPopupForTab(MnuTab,"Rest");
      addPopupForTab(MnuTab,"Sperre");
      JMenuItem MnuPlanung = new JMenuItem("P "+g.getBegBez(iPlanung));
      popup.add(MnuPlanung);
      MnuPlanung.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
        	DefPlanung.get(g,iPlanung);
        }
      });
      JMenuItem MnuAbfrage = new JMenuItem("A "+g.getBegBez(AbfDaten.iBegriff));
      popup.add(MnuAbfrage);
      MnuAbfrage.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          All_Unlimited.Grunddefinition.DefAbfrage.get(g,AbfDaten,-iAIC_Bewegungstyp).show();
        }
      });
      if (AbfDaten.iModell>0)
      {
	JMenuItem Mnu=new JMenuItem("M "+g.getModellBez(AbfDaten.iModell));
	popup.add(Mnu);
	Mnu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          DefModell.get(g, g.ModellToBegriff(AbfDaten.iModell)).show();
        }});
      }
      if (AbfDaten.iModell2>0)
      {
        JMenuItem Mnu=new JMenuItem("M "+g.getModellBez(AbfDaten.iModell2));
        popup.add(Mnu);
        Mnu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          DefModell.get(g, g.ModellToBegriff(AbfDaten.iModell2)).show();
        }});
      }
      if (AbfFilter != null)
      {
        JMenuItem MnuAbfrageFilter = new JMenuItem("A "+g.getBegBez(AbfFilter.iBegriff));
        popup.add(MnuAbfrageFilter);
        MnuAbfrageFilter.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            All_Unlimited.Grunddefinition.DefAbfrage.get(g, AbfFilter, -AbfFilter.iBew).show();
          }
        });
        if (AbfFilter.iModell>0)
        {
  	JMenuItem Mnu=new JMenuItem("M "+g.getModellBez(AbfFilter.iModell));
  	popup.add(Mnu);
  	Mnu.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ev) {
            DefModell.get(g, g.ModellToBegriff(AbfFilter.iModell)).show();
          }});
        }
      }
      if (AbfSonst != null)
      {
        JMenuItem MnuAbfrageFilter = new JMenuItem("A "+g.getBegBez(AbfSonst.iBegriff));
        popup.add(MnuAbfrageFilter);
        MnuAbfrageFilter.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            All_Unlimited.Grunddefinition.DefAbfrage.get(g, AbfSonst, AbfSonst.iStt).show();
          }
        });
      }
      if (AbfWeb != null)
      {
        JMenuItem MnuAbfrageWeb = new JMenuItem("A "+g.getBegBez(AbfWeb.iBegriff));
        popup.add(MnuAbfrageWeb);
        MnuAbfrageWeb.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            All_Unlimited.Grunddefinition.DefAbfrage.get(g, AbfWeb, -AbfWeb.iBew).show();
          }
        });
      }
      popup.add(new JMenuItem(sBez));
    }
    popup.addSeparator();
    g.addMenuItem("Close",popup,"Close",AL);
    /*MnuClose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        //popup.hide();
      }
    });*/


  }
  if (Btn==null)
    return;
        /*Btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
              //g.progInfo("Btn.actionPerformed");
              if (!bMove)
                Edit((JButton)e.getSource());
            }
        });*/
  //g.fixInfo("addMouseMotionListener von "+Btn);
        Btn.addMouseMotionListener(new MouseMotionListener() // zum verschieben einer Planung
        {
                public void mouseDragged(MouseEvent ev)
                {
                  boolean bNotFix=(iLoadBewilligt==0 || TabButton.isNull("VecAIC") || TabButton.getI("Status")==0) && CboZeitart.getComboBox().contains(TabButton.getI("Zeitart"));
                  if (bMove && bNotFix)
                  {

                      int iDif=ev.getPoint().x - PntStart.x;
                      //int iDifV=iDif;
                      //g.progInfo("1:"+iDif+";"+iMovedV+"/"+iMoveMin+";"+iMoveMax);
                      //g.progInfo("2:"+iDif+"/"+iMoveMin+"/"+iMoveMax);
                      if ((iCS&1)>0)
                      {
                        if(iMovedV + iDif < iMoveMin)
                          iDif = iMoveMin - iMovedV;
                        else if(iMovedB + iDif > iMoveMax)
                          iDif = iMoveMax - iMovedB;
                        else if(iMovedV + iDif > iMoveMax)
                          iDif = iMoveMax - iMovedV;
                      }
                      else
                      {
                        if(iDif < iMoveMin)
                          iDif = iMoveMin;
                        else if(iDif > iMoveMax)
                          iDif = iMoveMax;
                      }
                      //g.progInfo(iDifV+"->"+iDif+";"+iMovedV);

                      //long lMove = (long)((iDif+iMovedV) / dFaktor)/lGenau*lGenau;

                      //double dVonB = TabButton.getF("Von_Neu");
                      //double dBisB = TabButton.getF("Bis_Neu");
                      //g.progInfo("mouseDragged: dMove="+(lMove/lGenau)+" / "+DateWOD.AbsFormat("d.M H:mm:ss",dVonB*1000+lMove)+"-"+DateWOD.AbsFormat("d.M H:mm:ss",dBisB*1000+lMove));
                      //((JPanel)TabPanel.getInhalt("Panel")).remove(LblVor);
                      //addComponent(((JPanel)TabPanel.getInhalt("Panel")), LblVor, TabButton.getI("Stamm"),  TabButton.getI("Zeitart"),
                      //             new Timestamp((long)(dVonB*1000+lMove)),new Timestamp((long)(dBisB*1000+lMove)), 0, false, null);
                      if ((iCS&1)>0)
                        iMovedV+=iDif;

                      if (iCS==3)
                        BtnAkt.setLocation(BtnAkt.getLocation().x+iDif,BtnAkt.getLocation().y);
                      else if (iCS==2)
                        BtnAkt.setSize(BtnAkt.getSize().width+iDif-iMovedB,BtnAkt.getSize().height);
                      else
                        BtnAkt.setBounds(BtnAkt.getBounds().x+iDif,BtnAkt.getBounds().y,BtnAkt.getBounds().width-iDif,BtnAkt.getBounds().height);
                      if (iCS==3)
                        iMovedB+=iDif;
                      else if (iCS==2)
                        iMovedB=iDif;

                    /*if (AZ())
                    {
                      int iCol=(int)((dVonB*1000 - TSZeitraumVon.getTime()) / DateWOD.TAG);
                      //((JLabel)Pnl.getComponent(0)).setText("von");
                      //((JLabel)Pnl.getComponent(1)).setText("bis");
                      //table.setCell(table.getNumRows() - 1, iCol,
                      //              DateWOD.AbsFormat("d. H:mm",dVonB*1000+lMove*(iCS&1))+"-"+DateWOD.AbsFormat("H:mm",dBisB*1000+lMove*(iCS&2)/2));
                      VecAZ.elementAt(iCol*2).setText(DateWOD.AbsFormat("d. H:mm",dVonB*1000+lMove*(iCS&1)));
                      VecAZ.elementAt(iCol*2+1).setText(DateWOD.AbsFormat("H:mm",dBisB*1000+lMove*(iCS&2)/2));
                      //table.repaint();
                      //makeSum();
                    }*/
                  }
                }
                public void mouseMoved(MouseEvent ev)
                {
                  if (bMove)
                  {
                    int iCS2 = 3;
                    if (!Schicht())
                    {
                      if(ev.getPoint().x < 5)
                        iCS2 = 1;
                      else if(ev.getPoint().x > ((JButton)ev.getSource()).getSize().width - 5)
                        iCS2 = 2;
                    }
                    if (iCS2 != iCS)
                    {
                      iCS = iCS2;
                      ((JButton)(ev.getSource())).setCursor(new Cursor(iCS == 3 ? Cursor.DEFAULT_CURSOR : iCS == 1 ? Cursor.W_RESIZE_CURSOR : Cursor.E_RESIZE_CURSOR));
                    }
                  }
                }

        });
        Btn.addMouseListener(new MouseListener()
        {
            public void mousePressed(MouseEvent ev) // zum verschieben einer Planung
            {
              if (bMove)
              {
                PntStart = ev.getPoint();
                iMovedV=0;
                iMovedB=0;
                BtnAkt=(JButton)ev.getSource();
                //g.progInfo("mousePressed:"+Static.hash(BtnAkt));
                if (TabButton.posInhalt("Button",BtnAkt) && TabPanel.posInhalt("AIC_Stamm",TabButton.getI("Stamm")))
                  checkVerschiebung();
                else
                  Static.printError("Planung.addEvent: Tabellen für Verschiebung nicht gefunden");
                  //((JPanel)TabPanel.getInhalt("Panel")).remove(BtnAkt);
              }
              //g.progInfo("mousePressed:"+PntStart);
            }
            public void mouseClicked(MouseEvent ev) // zum bearbeiten einer Planung bzw. Popupmenü dafür
            {
             BtnAkt=(JButton)ev.getSource();
             if (BtnAkt != null && TabButton.posInhalt("Button",BtnAkt))
             {
              int iAic=TabButton.getI("Stamm");
              //posZeile(iAic);
              iAicStamm=iAic;
              if ((AbfDaten.iBits & Abfrage.cstSynchron) > 0)
                g.setSyncStamm(AbfDaten.iStt, iAicStamm,AbfDaten.iRolle);
              setBtnBorder(BtnAkt);
              //g.fixtestInfo("Button-Click auf "+g.getStamm(iAic)+": "+ev.getClickCount()); // Doppelklick auf Knopf
              if (ev.getModifiers()==MouseEvent.BUTTON1_MASK && ev.getModifiersEx()!=Global.iRM && ev.getClickCount()==(iMode>_EDIT /*bMove || CboZeitart.isActive()*/?2:1))
              {
                Edit((JButton)ev.getSource(),iMode>_EDIT);
              }
              //else if(ev.getModifiers()==MouseEvent.BUTTON3_MASK && bSave)
              else if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM))// && bSave) 24.7. Popup immer (auch bei ReadOnly
              {
                //BtnAkt=(JButton)ev.getSource();
                //g.progInfo("mouseClicked:"+Static.hash(BtnAkt));
                //MnuMove.setSelected(bMove);
                //if (BtnAkt != null && TabButton.posInhalt("Button",BtnAkt))
                if (bSave)
                {
                  boolean b=iLoadBewilligt==0 && iLoadBewilligt3VG==0 || TabButton.getI("Status")==0 || TabButton.isNull("VecAIC");
                  //if (bSave)
                  {
                    boolean bZC=CboZeitart.getComboBox().contains(TabButton.getI("Zeitart"));
                    boolean bZE=iMode!=_INFO && !bStammGesperrt && bLoadZeitartEdt && bZC;
                    if (MnuPaste != null)
                      MnuPaste.setEnabled(b && bZE);
                    MnuBack.setEnabled(true);//b && bZC);
                    MnuEdit.setEnabled(b && bZC || bZE);
                    if (MnuZeitart != null)
                      MnuZeitart.setEnabled(b && bZE);
                    if (MnuZeitart2 != null)
                      MnuZeitart2.setEnabled(b && bZE);
                    if (MnuZeitart3 != null)
                      MnuZeitart3.setEnabled(b && bZE);
                    if (MnuZeitart4 != null)
                      MnuZeitart4.setEnabled(b && bZE);
                    if (MnuZeitart5 != null)
                      MnuZeitart5.setEnabled(b && bZE);
                    if (MnuZeitart6 != null)
                      MnuZeitart6.setEnabled(b && bZE);
                    if (MnuFarbe != null)
                      MnuFarbe.setEnabled(b && bLoadFarbeEdt);
                    if (MnuDel != null)
                      MnuDel.setEnabled(iMode!=_INFO && !bStammGesperrt && (bDel && bZC || TabButton.isNull("VecAIC")) && b);
                  }
                  //bMove = b ? MnuMove.getState():false;
                  //MnuMove.setEnabled(b && bLoadVonBisEdt);
                  //g.fixInfo("vor popup.show2");



                }
                else if (MnuZeitart != null)
                {
                  MnuZeitart.setEnabled(false);
                  if (MnuZeitart2 != null)
                    MnuZeitart2.setEnabled(false);
                  if (MnuZeitart3 != null)
                    MnuZeitart3.setEnabled(false);
                  if (MnuZeitart4 != null)
                    MnuZeitart4.setEnabled(false);
                  if (MnuZeitart5 != null)
                    MnuZeitart5.setEnabled(false);
                  if (MnuZeitart6 != null)
                    MnuZeitart6.setEnabled(false);

                }
                popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
              }
             }
            }
            public void mouseEntered(MouseEvent ev)
            {
              if (!bEdit)
              {
                  //g.progInfo("mouseEntered");
                  BtnAkt = (JButton)ev.getSource();
                  //g.progInfo("mouseEntered:"+Static.hash(BtnAkt));
                  if (TabButton.posInhalt("Button", BtnAkt))
                  {
                    //bMove = (MnuMove != null) && (iLoadBewilligt == 0 || bLoadBewilligtEdt || TabButton.getI("Status")==0) ? MnuMove.getState() : false;
                  }
              }
            }
            public void mouseExited(MouseEvent ev)
            {
              //g.progInfo("mouseExited");
            }
            public void mouseReleased(MouseEvent ev) // zum verschieben einer Planung (fertig verschoben)
            {
              //g.progInfo("mouseReleased "+ev);
              if (bMove && (iMovedV != 0 || iMovedB != 0))
              {
                //double dMove=(ev.getPoint().x-PntStart.x)/dFaktor;
                //g.progInfo("Verschiebung:" + dMove);
                //g.progInfo("Verschiebung:" + (lMove/DateWOD.STUNDE));
                TabButton.addTS("Von_Neu",(long)(iMovedV / dFaktor)/lGenau*lGenau);
                TabButton.addTS("Bis_Neu",(long)(iMovedB / dFaktor)/lGenau*lGenau);
                //BtnAkt.setToolTipText(CboZeitart.getComboBox().getBezeichnung(TabButton.getI("Zeitart"))+": "+new VonBis(TabButton.getTimestamp("Von_Neu"),TabButton.getTimestamp("Bis_Neu"),TabButton.getF("Dauer_Neu"),sVBFormat,g.getFaktor(iVBStamm)));
                iMovedV=0;
                iMovedB=0;
                //makeSum();
                CboStamm.setSelectedAIC(TabButton.getI("Stamm"));
                CboZeitart.getComboBox().setSelectedAIC(TabButton.getI("Zeitart"));
                if (iLoadFarbe>0)
                  CboFarbe.getComboBox().setSelectedAIC(TabButton.getI("Farbe2"));
                VB.getVonEditor().setDate(TabButton.getTimestamp("Von_Neu"));
                VB.getBisEditor().setDate(TabButton.getTimestamp("Bis_Neu"));
                EditOk(false);
                if (VB2 != null)
                {
                  VB2.getVonEditor().setDate(TabButton.getTimestamp("Von2_Neu"));
                  VB2.getBisEditor().setDate(TabButton.getTimestamp("Bis2_Neu"));
                }
              }
            }
        });
}

      private void suchen(Point P)
        {
                final JDialog Dlg=new JDialog((JFrame)Fom.thisFrame,g.getBegriffS("Dialog","Suche"));
                P.translate(-100,-70);
                if(P.y<0)
                  P.y=0;
                Dlg.setLocation(P);

                //JPanel Pnl = new JPanel(new BorderLayout());
                //final JCheckBox CbxStart=g.getCheckbox("Volltextsuche",false);//(iSuchBits&1)==0);
                //final JCheckBox CbxSucheCase=g.getCheckbox("Case",false);//(iSuchBits&4)>0);
                /*final ComboSort CboSuche=new ComboSort(g,ComboSort.kein);
                CboSuche.setFont(g.fontBezeichnung);
                ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");

                Vector VecGidSpalten = Abf.getBezeichnung();
                for(int i=0;i<VecGidSpalten.size();i++)
                        CboSuche.addItem(""+VecGidSpalten.elementAt(i),i+1,"");*/

                //JPanel Pnl2 = new JPanel(new BorderLayout());
                        //Pnl2.add("West",new JLabel(g.getBegriffS("Show","Name")+":"));
                        final Text Edt=new Text(sSuchtext,20);
                        //Edt.setFont(g.fontStandard);
                        //Pnl2.add("Center",Edt);
                //Pnl.add("North",Pnl2);
                g.addLabel((JPanel)Dlg.getContentPane(),"Name",Edt,"North");
                //Pnl2 = new JPanel(new FlowLayout());
                //Pnl2.add(CbxStart);
                //Pnl2.add(CbxSucheAb);
                //Pnl2.add(CbxSucheCase);
                //Pnl.add("Center",Pnl2);


                //Dlg.getContentPane().add("North",Pnl);

                JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
                //Pnl = new JPanel(new GridLayout(1,2));
                //JButton BtnDlgSuche=g.getButton("Suche");
                final JButton BtnDlgWeiter=g.getButton("Weiter");
                //JButton BtnDlgHelp = g.getButton("help_hs3");
                Dlg.getRootPane().setDefaultButton(BtnDlgWeiter);

                BtnDlgWeiter.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                sSuchtext=Edt.getText();
                                Vector VecBez =table.getRowLabels();
                                boolean b=false;
                                for (int i=0;!b && i<VecBez.size();i++)
                                {
                                  //int i=TabPanel.getPos();
                                  String s=(String)VecBez.elementAt(i);
                                  //iSuchBits=(CbxStart.isSelected()?0:1);//+2+(CbxSucheCase.isSelected()?4:0);
                                  //if((iSuchBits&4)==0)
                                  {
                                          s=s.toUpperCase();
                                          sSuchtext=sSuchtext.toUpperCase();
                                  }
                                  b=bBeginn ? s.startsWith(sSuchtext):s.indexOf(sSuchtext)>-1;
                                  if (b)
                                  {
                                    table.setSelectedCells(new JCCellRange(i, -1, i, 0));
                                    table.makeVisible(i, 0);
                                    if((AbfDaten.iBits&Abfrage.cstSynchron)>0)
                                    {
                                      TabPanel.push();
                                      TabPanel.setPos(i);
                                      g.setSyncStamm(AbfDaten.iStt,TabPanel.getI("AIC_Stamm"),AbfDaten.iRolle);
                                      TabPanel.pop();
                                    }
                                  }
                                }
                                /*bAb=false;

                                //iSpalte=CboSuche.getSelectedAIC();
                                //JCOutlinerFolderNode Nod=(JCOutlinerFolderNode)((JCOutliner)TabOutliner.getInhalt("Gid")).getRootNode();
                                if (suche(Nod,sSuchtext,CboSuche.getSelectedAIC()-1))
                                {
                                  bAb=true;
                                  suche(Nod,sSuchtext,CboSuche.getSelectedAIC()-1);
                                }*/
                                //Dlg.dispose();
                        }
                });
                JButton BtnDlgBeenden=g.getButton("Beenden");
                BtnDlgBeenden.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                Dlg.dispose();
                        }
                });
                //Pnl.add(BtnDlgSuche);
                Pnl.add(BtnDlgWeiter);
                //Pnl.add(BtnDlgHelp);
                Pnl.add(BtnDlgBeenden);
                Dlg.getContentPane().add("South",Pnl);
                Dlg.pack();
                Dlg.setVisible(true);
                Edt.requestFocus();
        }

private void addPopupForTab(JMenu Mnu,String s)
{
  JMenuItem MnuTab = new JMenuItem(s);
  Mnu.add(MnuTab);
  MnuTab.setActionCommand(s);
  if (AL1==null)
    AL1=new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        String s2=ev.getActionCommand();
        Tabellenspeicher.showGrid(TabDaten,"Daten",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabFilter,"Filter",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabSonst,"Sonst",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabZeitart,"Zeitart",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabZeitart2,"Zeitart2",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabFarbe,"Farbe",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabSpaltenDaten,"SpaltenDaten",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabSpaltenFilter,"SpaltenFilter",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabSpaltenSonst,"SpaltenSonst",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabButton,"Button",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabPanel,"Panel",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabRest,"Rest",s2,Fom.thisFrame);
        Tabellenspeicher.showGrid(TabSperre,"Sperre",s2,Fom.thisFrame);
        if (s2.equals("WebDaten"))
        	Tabellenspeicher.showGrid(AbfWeb.getDaten(AbfWeb.iStt, iAicStamm, null, null),"WebDaten",s2,Fom.thisFrame);
      }
    };
  MnuTab.addActionListener(AL1);
}

private void checkVerschiebung()
{
      int iStamm=TabButton.getI("Stamm");
      long lVonB = TabButton.getL("Von_Neu");
      long lBisB = TabButton.getL("Bis_Neu");
      long lC=lBisB-lVonB-lGenau;
      long lMin=TSNow.getTime();
      long lMax=TSZeitraumBis.getTime();
      TabButton.push();
      for (TabButton.moveFirst();!TabButton.eof();TabButton.moveNext())
      {
        if (TabButton.getI("Stamm")==iStamm && !TabButton.isNull("Von_Neu"))
        {
          long lV2=TabButton.getL("Von_Neu");
          long lB2=TabButton.getL("Bis_Neu");
          if (lB2<=lVonB)
            lMin = Math.max(lMin,lB2);
          if (lV2>=lBisB)
            lMax = Math.min(lMax,lV2);
        }
      }
      TabButton.pop();
      iMoveMin= (int)((iCS==2 ? -lC:lMin-lVonB)*dFaktor)-1;
      iMoveMax= (int)((iCS==1 ? lC:lMax-lBisB)*dFaktor)+1;
      //g.progInfo("iMoveMin="+iMoveMin+", iMoveMax="+iMoveMax);
      //g.progInfo("a="+((lVonB-lMin)/3600000.0)+", b="+((lMax-lBisB)/3600000.0)+", c="+(lC/3600000.0));
}

private void setZeitart(int iZA)
{
  long lClock2 = Static.get_ms();
        CboStamm.setSelectedAIC(TabButton.getI("Stamm"));
        CboZeitart.getComboBox().setSelectedAIC(iZA);
        //g.fixtestInfo("setZeitart bei "+g.getStamm(TabButton.getI("Stamm"))+": "+g.getStamm(iZA)+" ("+iZA+")");
        if (iLoadFarbe>0)
          CboFarbe.getComboBox().setSelectedAIC(TabButton.getI("Farbe2"));
        VB.getVonEditor().setDate(TabButton.getTimestamp("Von_Neu"));
        VB.getBisEditor().setDate(TabButton.getTimestamp("Bis_Neu"));
        EditOk(false);
        if (VB2 != null)
        {
          VB2.getVonEditor().setDate(TabButton.getTimestamp("Von2_Neu"));
          VB2.getBisEditor().setDate(TabButton.getTimestamp("Bis2_Neu"));
        }
  g.clockInfo("setZeitart "+CboZeitart.getComboBox().getSelectedBezeichnung(),lClock2);
}

private void setFarbe(int iAF)
{
  CboStamm.setSelectedAIC(TabButton.getI("Stamm"));
  CboZeitart.getComboBox().setSelectedAIC(TabButton.getI("Zeitart"));
  if (iLoadFarbe>0)
    CboFarbe.getComboBox().setSelectedAIC(iAF);
  VB.getVonEditor().setDate(TabButton.getTimestamp("Von_Neu"));
  VB.getBisEditor().setDate(TabButton.getTimestamp("Bis_Neu"));
  EditOk(false);
  if (VB2 != null)
  {
    VB2.getVonEditor().setDate(TabButton.getTimestamp("Von2_Neu"));
    VB2.getBisEditor().setDate(TabButton.getTimestamp("Bis2_Neu"));
  }
}

private void Back()
{
        CboStamm.setSelectedAIC(TabButton.getI("Stamm_Alt"));
        CboZeitart.getComboBox().setSelectedAIC(TabButton.getI("Zeitart_Alt"));
        if (iLoadFarbe>0)
          CboFarbe.getComboBox().setSelectedAIC(TabButton.getI("Farbe_Alt"));
        VB.getVonEditor().setDate(TabButton.getTimestamp("Von_Alt"));
        VB.getBisEditor().setDate(TabButton.getTimestamp("Bis_Alt"));
        if (VB2 != null)
        {
          VB2.getVonEditor().setDate(TabButton.getTimestamp("Von2_Alt"));
          VB2.getBisEditor().setDate(TabButton.getTimestamp("Bis2_Alt"));
        }
        setStatusVG(TabButton.getI("Status_Alt"));
        setStatusSV(TabButton.getI("StatusSV_Alt"));
        EditOk(false);
}

public boolean Menge()
{
  return AbfDaten != null && (AbfDaten.iBits & Abfrage.cstMengen)>0;
}

private boolean AZ() // alter Dienstplan
{
  return (iBits&cstAZ)>0;
}

private boolean Stunden()
{
  return (iBits&cstStunden)>0;
}

private boolean Schicht()
{
  return (iBits&cstSchichten)>0;
}

private boolean verschleiern(boolean bImmer)
{
  return (iBits&cstPveil)>0 && (bImmer || TabZeitart.getB("verschl"));
}

private boolean AF()
{
  return (iBits&cstAndereFarbe)>0;
}

private boolean TGM()
{
	return (iBits&cstTGM)>0;
}

private int getPBits()
{
  return PVER+(bAsk?ASK:0)+(bMove?MOVE:0)/*+(bOld?OLD:0)*/+(bVoll?VOLL:0)+(bVB?VBT:0)+(bAZ?PAZ:0)+(bIst?IST:0)+(bSoll?SOLL:0)+(bDiff?DIFF:0)+
  	(bPause?PAUSE:0)/*+(bKSt?KST:0)*/+(bSum?SUM:0)+(bBeginn?BEGINN:0)+(bTMZ?TMZ:0)+(bVA?VA:0)+(bSaldo?SALDO:0)+(bMZ?MZ:0)+(bPPS?PPS:0)+
        (bESum?ESUM:0)+(bTag?TAG:0)+(bWoche?WOCHE:0)+(bKZS?KZS:0)+(bSES?SES:0);
}

private boolean AbfragenErmitteln()
{
	SQL Qry = new SQL(g);
	if (!Qry.open("select aic_abfrage,abf_aic_abfrage,abf2_aic_abfrage,spaltenbreite,bits,defbezeichnung from planung"+g.join("begriff","planung")+" where planung.aic_begriff="+iPlanung))
        {
          Static.printError("Planung.AbfragenErmitteln: Statement fehlerhalft bei "+g.TabBegriffe.getBezeichnung(iPlanung));
          return false;
        }
        if (Qry.eof())
        {
          Static.printError("Planung.AbfragenErmitteln: Planung "+g.TabBegriffe.getBezeichnung(iPlanung)+" nicht gefunden!");
          return false;
        }
	//{
		iSpaltenOri=Qry.getI("spaltenbreite");
                iSpaltenbreite=iSpaltenOri%1000;
                iZeilenhoehe=iSpaltenOri/1000;
		//iSpaltenbreite=Qry.getI("spaltenbreite")*(Static.sZeitart.equals("Woche")?2:1);
		int iAbfDaten=Qry.getI("aic_abfrage");
		int iAbfFilter=Qry.getI("abf_aic_abfrage");
                int iAbfSonst=Qry.getI("abf2_aic_abfrage");
		iBits=Qry.getI("bits");
                sBez=Qry.getS("defbezeichnung");
		g.progInfo("Planung "+sBez+":"+iAbfDaten+"/"+iAbfFilter+"/ Breite="+iSpaltenbreite+"/ bits="+iBits);
		Qry.close();
                Qry.free();
	//}
	/////////////////
	//Abfrage Daten/////////////////////////////////////////////////////////////////
	/////////////////

	AbfDaten=new ShowAbfrage(g,iAbfDaten,Abfrage.cstAbfrage);
	iAIC_Bewegungstyp=AbfDaten.iBew;
	TabSpaltenDaten=AbfDaten.getSpalten();
        iRolle=AbfDaten.iRolle;

	//////////////////
	//Abfrage Filter////////////////////////////////////////////////////////////////
	//////////////////

	if (iAbfFilter>0)
	{
		AbfFilter=new ShowAbfrage(g,iAbfFilter,Abfrage.cstAbfrage);
		TabSpaltenFilter=AbfFilter.getSpalten();
	}

        if (iAbfSonst>0)
        {
                AbfSonst=new ShowAbfrage(g,iAbfSonst,Abfrage.cstAbfrage);
                AbfSonst.sAnfang="p2.aic_stamm,p2.bezeichnung";
                AbfSonst.SQL_String();
                TabSpaltenSonst=AbfSonst.getSpalten();
        }


	////////////////////////////////////////////////////////////////////////////////

	Parameter Para = new Parameter(g,"Planung"+iPlanung);
        Para.getParameter("Druck",true,false);
        if (Para.bGefunden)
        {
          sDruckText=Para.sParameterzeile;
          iLayout=Para.int1;
          iVorlage=Para.int2;
          iDBits=Para.int3;
        }
        Para.getParameter("Planung",true,false);
        iLastZA=Para.bGefunden ? Para.int1:-1;
        iLastBits=Para.bGefunden ? Para.int2:0;
        if (Para.bGefunden && Para.int3>0)
        {
          iSpaltenbreite = Para.int3%1000;
          iZeilenhoehe = Para.int3/1000;
        }
        iLastAF=Para.bGefunden ? Para.int4:-1;
        iLastSB=iSpaltenbreite;
        iLastZH=iZeilenhoehe;
        if (Schicht())
          bAZ=true;
        if (iLastBits==0)
          iLastBits=getPBits();
        else
        {
          bMZ=(iLastBits&MZ)>0;
          bAsk=(iLastBits&ASK)>0;
          bMove=(iLastBits&MOVE)>0;
          //bOld=(iLastBits&OLD)>0;
          bVoll=(iLastBits&VOLL)>0;
          bVB=(iLastBits&VBT)>0;
          bAZ=(iLastBits&PAZ)>0;
          bPPS=(iLastBits&PPS)>0;
          bIst=(iLastBits&IST)>0;
          bSoll=(iLastBits&SOLL)>0;
          bDiff=(iLastBits&DIFF)>0;
          bSaldo=(iLastBits&SALDO)>0;
          bPause=(iLastBits&PAUSE)>0;
          //bKSt=(iLastBits&KST)>0;
          bSum=(iLastBits&SUM)>0;
          bKZS=(iLastBits&KZS)>0;
          bBeginn=(iLastBits&BEGINN)>0;
          bTMZ=(iLastBits&TMZ)>0;
          bESum=(iLastBits&ESUM)>0;
          bTag=(iLastBits&TAG)>0;
          bWoche=(iLastBits&WOCHE)>0;
          bVA=(iLastBits&VA)>0;
          bSES=(iLastBits&SES)>0;
        }
        Para.free();
        
    if (!Schicht() && !AZ() && g.TabBegriffe.getPos("Kennung","WebPlanung_erstellen")>=0)
    {
    	
    	AbfWeb=new ShowAbfrage(g,"WebPlanung_erstellen");
    	if (AbfWeb.TabSp.posInhalt("Kennung", "vb"))
    		iWebVonBis=Sort.geti(AbfWeb.TabSp.getInhalt("Vec"),0);
    	if (AbfWeb.TabSp.posInhalt("Kennung", "ZA"))
    		iWebZeitart=Sort.geti(AbfWeb.TabSp.getInhalt("Vec"),0);
    	if (AbfWeb.TabSp.posInhalt("Kennung", "SV")) // Stellvertreter
    		iWebErsatz=Sort.geti(AbfWeb.TabSp.getInhalt("Vec"),0);
    	if (AbfWeb.TabSp.posInhalt("Kennung", "MA"))
    		iWebStamm=Sort.geti(AbfWeb.TabSp.getInhalt("Vec"),0);
    	if (AbfWeb.TabSp.posInhalt("Kennung", "b1"))
    		iWebBew1=Sort.geti(AbfWeb.TabSp.getInhalt("Vec"),0);
    	if (AbfWeb.TabSp.posInhalt("Kennung", "b2"))
    		iWebBew2=Sort.geti(AbfWeb.TabSp.getInhalt("Vec"),0);
    	if (AbfWeb.TabSp.posInhalt("Kennung", "Memo"))
    		iWebMemo=Sort.geti(AbfWeb.TabSp.getInhalt("Vec"),0);
    	if (AbfWeb.TabSp.posInhalt("Kennung", "Memo2"))
    		iWebMemo2=Sort.geti(AbfWeb.TabSp.getInhalt("Vec"),0);
//    	g.fixtestError("Web-Eig:"+iWebStamm+"/"+iWebZeitart+"/"+iWebVonBis+"/"+iWebBew1+"/"+iWebBew2+"/"+iWebMemo+"/"+iWebMemo2+"/"+iWebErsatz);
    }

	TabSpaltenDaten.moveBefore();
	if(TabSpaltenDaten.posNextInhalt("Datentyp","BewStamm"))
	{
		iLoadStamm = TabSpaltenDaten.getI("Kennung2");
                bLoadStammEdt = (TabSpaltenDaten.getI("bits")&Abfrage.cstEditierbar)>0;
	}
	else
		Static.printError("Planung.AbfragenErmitteln: 1.BewStamm von Daten (z.B. Mitarbeiter) fehlt!");

	if(TabSpaltenDaten.posNextInhalt("Datentyp","BewStamm"))
	{
		iLoadZeitart = TabSpaltenDaten.getI("Kennung2");
		bLoadZeitartEdt = (TabSpaltenDaten.getI("bits")&Abfrage.cstEditierbar)>0;
		int iBits2=TabSpaltenDaten.getI("bits");
		CboZeitart = new AUComboList(FrmEdit,Fom.getBegriff(),AbfDaten.iBegriff,iLoadZeitart,TabSpaltenDaten.getI("STT"),g,TabSpaltenDaten.getI("Filter"),
			(iBits2&(Global.cstKeinAutoDate*0x10000+Abfrage.cstKeinAutoDate))==0,(iBits2 & (Global.cstAlways * 0x10000)) == 0,true,false);
                //CboZeitart.setFont(g.fontStandard);

                CboZeitart.checkFill();
                if (Schicht())
                  CboZeitart.getComboBox().addItemListener(new ItemListener () {
                    public void itemStateChanged(ItemEvent ev) {
                      if (ev.getStateChange() == ItemEvent.SELECTED && bEdit)
                      {
                        g.defInfo2("neue Zeitart:"+CboZeitart.getAIC()+"/"+CboZeitart.getComboBox().getSelectedBezeichnung());
                        //new Exception().printStackTrace();
                        int iPos=TabZeitart.getPos("aic_stamm",CboZeitart.getAIC());
                        if (iPos>=0)
                        {
                          DateWOD dt = new DateWOD(VB.getVon());
                          dt.setTimeZero();
                          double dVon = dt.getAbsSeconds();
                          dt.setAllSeconds(dVon + TabZeitart.getF(iPos,"Von1"));dt.setTimezoneOffset();
                          VB.getVonEditor().setDate(dt.toTimestamp());
                          dt.setAllSeconds(dVon + TabZeitart.getF(iPos,"Bis1"));dt.setTimezoneOffset();
                          VB.getBisEditor().setDate(dt.toTimestamp());
                          if (VB2 != null && !TabZeitart.isNull(iPos,"Von2"))
                          {
                            dt.setAllSeconds(dVon + TabZeitart.getF(iPos,"Von2"));dt.setTimezoneOffset();
                            VB2.getVonEditor().setDate(dt.toTimestamp());
                            dt.setAllSeconds(dVon + TabZeitart.getF(iPos,"Bis2"));dt.setTimezoneOffset();
                            VB2.getBisEditor().setDate(dt.toTimestamp());
                          }
                          else if (VB2 != null)
                          {
                            VB2.getVonEditor().setDate(null);
                            VB2.getBisEditor().setDate(null);
                          }
                        }
                      }}});
                if(iLastZA>0 && CboZeitart.getComboBox().contain(ComboSort.Aic,iLastZA)>-1)
                  CboZeitart.getComboBox().setSelectedAIC(iLastZA);
                else
                  CboZeitart.getComboBox().setFirst();
                iZAcopy=CboZeitart.getAIC();

		//CboZeitart.fillStammTable(TabSpaltenDaten.getI("STT"),false);
        
		String s="select aic_stamm"+g.AU_Bezeichnung2("Stamm","v.aic_stamm","v.Bezeichnung")+" Bezeichnung,kennung,null popup,(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("GRANTED")+") bewilligt"+
                        /*(AZ()?",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("ZE_ARBEITSZEIT")+") \"use\""+
                        ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("PRODUCTIVE ACTIVITY")+") prod"+
                        ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("PAYED ACTIVITY")+") pay":"")+*/
                        (verschleiern(true) ? ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("WEB_VERSCHLEIERN")+") verschl":"")+
                        (Schicht()?",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("DAYDEBIT")+") soll"+
                         ",(select zeit_von from zeit_von_bis d"+Transact.join2("poolview","p","d","daten")+" where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("KERNZEIT")+") von1"+
                         ",(select zeit_bis from zeit_von_bis d"+Transact.join2("poolview","p","d","daten")+" where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("KERNZEIT")+") bis1"+
                         ",(select zeit_von from zeit_von_bis d"+Transact.join2("poolview","p","d","daten")+" where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("2. KERNZEIT")+") von2"+
                         ",(select zeit_bis from zeit_von_bis d"+Transact.join2("poolview","p","d","daten")+" where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("2. KERNZEIT")+") bis2"+
                         ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("MIN_PAUSE")+") TM_Pause"+
                         ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("TIME BEFOR BREAK")+") TM_ab"+
                         ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("TM_OPEN")+") TM_open"+
                         ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("TM_CORE")+") TM_core"+
                         ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("TM_CLOSE")+") TM_close":"")+
                        /*",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("VALID WHOLE DAY")+") day"+*/
			",(select Spalte_Stringx from Daten_Stringx d"+Transact.join2("poolview","p","d","daten")+" where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("SHORTCUT_3")+") KZ"+
			",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.iEigFarbe+") Farbe"+
			" from stammview2 v where aic_stammtyp="+TabSpaltenDaten.getI("STT");
		//g.progInfo(s);
		TabZeitart = new Tabellenspeicher(g,s,true);
		TabZeitart.sAIC="AIC_Stamm";
		TabZeitart.sKennung="KZ";
                //TabZeitart.clearWithVec("aic_stamm",g.getVecStamm(Fom.getBegriff(),TabSpaltenDaten.getI("STT")));
                //if (AZ())
                //  iStammArbeitszeit=TabZeitart.posInhalt("use",new Double(1.0)) ? TabZeitart.getI("aic_stamm"):0;
                //g.progInfo("iStammArbeitszeit="+iStammArbeitszeit);
	}
	else
		Static.printError("Planung.AbfragenErmitteln: 2.BewStamm von Daten (z.B. Zeitart) fehlt!");

        if (AF() && TabSpaltenDaten.posNextInhalt("Datentyp","BewStamm"))
        {
          iLoadFarbe = TabSpaltenDaten.getI("Kennung2");
          bLoadFarbeEdt = (TabSpaltenDaten.getI("bits")&Abfrage.cstEditierbar)>0;
          int iBits2=TabSpaltenDaten.getI("bits");
          CboFarbe = new AUComboList(FrmEdit,Fom.getBegriff(),AbfDaten.iBegriff,iLoadFarbe,TabSpaltenDaten.getI("STT"),g,TabSpaltenDaten.getI("Filter"),
                        (iBits2&(Global.cstKeinAutoDate*0x10000+Abfrage.cstKeinAutoDate))==0,(iBits2 & (Global.cstAlways * 0x10000)) == 0,true,false);
          //CboFarbe.setFont(g.fontStandard);

          CboFarbe.checkFill();
          if(iLastAF>0 && CboFarbe.getComboBox().contain(ComboSort.Aic,new Integer(iLastAF))>-1)
            CboFarbe.getComboBox().setSelectedAIC(iLastAF);
          else
            CboFarbe.getComboBox().setFirst();

          String s="select aic_stamm,bezeichnung,null popup,(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.iEigFarbe+") Farbe"+
              " from stammview2 v where aic_stammtyp="+TabSpaltenDaten.getI("STT");
          TabFarbe = new Tabellenspeicher(g,s,true);
        }

        VecStunde=g.getMass(g.getStunde(),false);
        lGenau=Stunden()? DateWOD.STUNDE/2:DateWOD.TAG;

	//TabSpaltenDaten.moveBefore();
	//if((iBits&cstTGM)==0)
	  if(TabSpaltenDaten.posInhalt("Datentyp","BewDatum"))
		iLoadDatum = TabSpaltenDaten.getI("Kennung2");
	  else
		Static.printError("Planung.AbfragenErmitteln: BewDatum von Daten (z.B. Gültigkeit) fehlt!");

	//TabSpaltenDaten.moveBefore();
	if(TabSpaltenDaten.posInhalt("Datentyp","BewVon_Bis"))
	{
		iLoadVonBis = TabSpaltenDaten.getI("Kennung2");
		bLoadVonBisEdt = (TabSpaltenDaten.getI("bits")&Abfrage.cstEditierbar)>0;
		sVBFormat = Static.beiLeer(TabSpaltenDaten.getS("FORMAT"),"dd/MM/yyyy HH:mm:ss");
		iVBStamm = TabSpaltenDaten.getI("STAMM");
		VB = new VonBisEingabe(g.getVon(),g.getBis(),sVBFormat,g,0/*iVBStamm*/,1);
                VB.setFont(Transact.fontStandard);
                bMoa=(TabSpaltenDaten.getI("bits")&(Abfrage.cstGueltig))>0;
                if (Schicht() && TabSpaltenDaten.posNextInhalt("Datentyp","BewVon_Bis"))
                {
                  iLoadVonBis2 = TabSpaltenDaten.getI("Kennung2");
                  VB2 = new VonBisEingabe(g.getVon(),g.getBis(),sVBFormat,g,0/*iVBStamm*/,1);
                  VB2.setFont(Transact.fontStandard);
                }
	}
	else
		Static.printError("Planung.AbfragenErmitteln: BewVon_Bis von Daten (z.B. von-bis) fehlt!");

	//TabSpaltenDaten.moveBefore();
	if (!TGM())
		if(TabSpaltenDaten.posInhalt("Datentyp","BewBoolean"))
		{
			iLoadBewilligt=TabSpaltenDaten.getI("Kennung2");
			bLoadBewilligtVG_Edt=(TabSpaltenDaten.getI("bits")&Abfrage.cstEditierbar)>0;
		}
        else if(TabSpaltenDaten.posInhalt("Datentyp","BewBool3"))
        {
                iLoadBewilligt3VG=TabSpaltenDaten.getI("Kennung2");
                EdtStatus=new RadioAuswahl(g,iLoadBewilligt3VG);
                bLoadBewilligtVG_Edt=(TabSpaltenDaten.getI("bits")&Abfrage.cstEditierbar)>0;
        }
	if (!TabSpaltenDaten.isLast())
	{
		TabSpaltenDaten.moveNext();
		iRest=TabSpaltenDaten.getI("Nummer");
		TabSpaltenDaten.movePrevious();
		if(TabSpaltenDaten.posNextInhalt("Datentyp","BewBool3"))
		{
			iLoadBewilligt3SV=TabSpaltenDaten.getI("Kennung2");
			EdtStatusSV=new RadioAuswahl(g,iLoadBewilligt3SV);
            bLoadBewilligtSV_Edt=(TabSpaltenDaten.getI("bits")&Abfrage.cstEditierbar)>0;
			//g.fixtestInfo("     ---            iLoadBewilligt3b="+iLoadBewilligt3b);
            TabSpaltenDaten.moveNext();
    		iRest=TabSpaltenDaten.getI("Nummer");
		}
	}
//	if(TabSpaltenDaten.posInhalt("Datentyp","BewBew"))
//		sLoadBewBew=TabSpaltenDaten.getS("Kennung");
	if (AbfDaten.TabSp.posInhalt("Kennung", "BewBew"))
		sLoadBewBew="e"+Abfrage.VecToKennung(AbfDaten.TabSp.getInhalt("Vec"));
        //TabSpaltenDaten.showGrid("TabSpaltenDaten");
        if (iAbfSonst>0)
        {
          for (TabSpaltenSonst.moveFirst();!TabSpaltenSonst.eof();TabSpaltenSonst.moveNext())
          {
            String sDt=TabSpaltenSonst.getS("Datentyp");
            if (sLoadKSt==null)
              sLoadKSt = TabSpaltenSonst.getS("Kennung");
            if(iLoadSoll == 0 && (sDt.equals("CalcField") || sDt.equals("Double")))
              iLoadSoll = TabSpaltenSonst.getI("Kennung2");
            else if(iLoadSaldo == 0 && (sDt.equals("CalcField") || sDt.equals("Double")))
              iLoadSaldo = TabSpaltenSonst.getI("Kennung2");
            else if(sDt.equals("Farbe"))
              sLoadFarbe = TabSpaltenSonst.getS("Kennung");
            if ((TabSpaltenSonst.getI("bits")&Abfrage.cstGruppiert)>0)
              iEigGruppe=TabSpaltenSonst.getI("Kennung2");
          }
          g.fixtestInfo("----------------- iAbfSonst="+iAbfSonst+": KSt="+sLoadKSt+", Soll="+iLoadSoll+", Saldo="+iLoadSaldo+", Farbe="+sLoadFarbe+", Gruppe="+iEigGruppe);
        }
        if (iLoadSaldo==0)
          bSaldo=false;

	if (iAbfFilter>0)
	{
          //TabSpaltenFilter.showGrid("TabSpaltenFilter");
		TabSpaltenFilter.moveBefore();
		if(TabSpaltenFilter.posNextInhalt("Datentyp",Schicht()?"BewVon_Bis":"BewMass"))
                  iLoadFilterIstZeit = TabSpaltenFilter.getI("Kennung2");
		else
			Static.printError("Planung.AbfragenErmitteln: BewMass von Filter(z.B.: Sollstunden) fehlt!");
                /*if (AZ())
                {
                  if (TabSpaltenFilter.posNextInhalt("Datentyp", "BewMass"))
                    iLoadFilterSaldo = TabSpaltenFilter.getI("Kennung2");
                  else
                    Static.printError("Planung.AbfragenErmitteln: Saldo von Filter fehlt!");
                  if (TabSpaltenFilter.posNextInhalt("Datentyp", "BewMass"))
                    iLoadFilterPauseAb = TabSpaltenFilter.getI("Kennung2");
                  else
                    Static.printError("Planung.AbfragenErmitteln: <Pause ab> von Filter fehlt!");
                  if (TabSpaltenFilter.posNextInhalt("Datentyp", "BewMass"))
                    iLoadFilterPausedauer = TabSpaltenFilter.getI("Kennung2");
                  else
                    Static.printError("Planung.AbfragenErmitteln: <Pausedauer> von Filter fehlt!");
                  if (TabSpaltenFilter.posNextInhalt("Datentyp", "BewBoolean"))
                    iLoadFilterPauseGleitend = TabSpaltenFilter.getI("Kennung2");
                  else
                    Static.printError("Planung.AbfragenErmitteln: <Pause gleitend> von Filter fehlt!");

                }*/

		TabSpaltenFilter.moveBefore();
		if(TabSpaltenFilter.posNextInhalt("Datentyp","BewStamm"))
			iLoadFilterStamm = TabSpaltenFilter.getI("Kennung2");
		else
			Static.printError("Planung.AbfragenErmitteln: 1.BewStamm von Filter (z.B. Mitarbeiter) fehlt!");

		if(TabSpaltenFilter.posNextInhalt("Datentyp","BewStamm"))
                {
                  iLoadFilterZeitart = TabSpaltenFilter.getI("Kennung2");
                  if (Schicht())
                  {
                    int iEigPay=g.TabEigenschaften.getAic("PAYED ACTIVITY");
                    int iEigProd=g.TabEigenschaften.getAic("PRODUCTIVE ACTIVITY");
                    int iEigCntr=g.TabEigenschaften.getAic("PZE_CONTROLLING");
                    int iEigSPlan=g.TabEigenschaften.getAic("ZE_DAUER_SCHICHTPLAN");
                    if (iEigPay==0 || iEigProd==0 || iEigCntr==0 || iEigSPlan==0) g.fixtestError("Eigenschaft "+(iEigProd==0 ? "PRODUCTIVE ACTIVITY":iEigSPlan==0 ? "ZE_DAUER_SCHICHTPLAN":iEigPay==0 ? "PAYED ACTIVITY":"PZE_CONTROLLING")+" nicht gefunden");
                    String s = "select v.aic_stamm"+g.AU_Bezeichnung2("Stamm","v.aic_stamm","v.Bezeichnung")+" Bezeichnung,kennung,(select Spalte_Stringx from Daten_Stringx d" + Transact.join2("poolview", "p", "d", "daten") +
                        " where aic_stamm=v.aic_stamm and aic_eigenschaft=" + g.TabEigenschaften.getAic("SHORTCUT_3") + ") KZ" +
                        ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+iEigPay+") pay"+
                        ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+iEigCntr+") ctrl"+
                        ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+iEigProd+") prod"+
                        ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+iEigSPlan+") SPlan"+
                        ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft=" + g.iEigFarbe + ") Farbe" +
                        " from stammview2 v where aic_stammtyp=" + TabSpaltenFilter.getI("STT");
                    TabZeitart2 = new Tabellenspeicher(g, s, true);
                  }
                }
		else
			Static.printError("Planung.AbfragenErmitteln: 2.BewStamm von Filter (z.B. Zeitart) fehlt!");
                /*if (AZ())
                  if(TabSpaltenFilter.posNextInhalt("Datentyp","BewStamm"))
                        iLoadFilterZeitart2 = TabSpaltenFilter.getI("Kennung2");
                  else
                        Static.printError("Planung.AbfragenErmitteln: 3.BewStamm von Filter (z.B. Zeitart2) fehlt!");*/

		TabSpaltenFilter.moveBefore();
		if(TabSpaltenFilter.posNextInhalt("Datentyp","BewDatum"))
			iLoadFilterGueltig = TabSpaltenFilter.getI("Kennung2");
		else
			Static.printError("Planung.AbfragenErmitteln: BewDatum von Filter (z.B. Gültigkeit) fehlt!");

                /*if (AZ())
                {
                  TabSpaltenFilter.moveBefore();
                  if(TabSpaltenFilter.posNextInhalt("Datentyp","BewVon_Bis"))
                    iLoadFilterOffen1 = TabSpaltenFilter.getI("Kennung2");
                  else
                    Static.printError("Planung.AbfragenErmitteln: Öffnungszeit1 fehlt!");
                  if (TabSpaltenFilter.posNextInhalt("Datentyp","BewVon_Bis"))
                    iLoadFilterOffen2 = TabSpaltenFilter.getI("Kennung2");
                  else
                    Static.printError("Planung.AbfragenErmitteln: Öffnungszeit2 fehlt!");
                  if (TabSpaltenFilter.posNextInhalt("Datentyp","BewVon_Bis"))
                    iLoadFilterAnwesend = TabSpaltenFilter.getI("Kennung2");
                  else
                    Static.printError("Planung.AbfragenErmitteln: Anwesend fehlt!");
                }*/
	}
        return true;
}

private void ParameterChange()
{
  if (DlgP==null)
  {
    DlgP = new JDialog((JFrame)Fom.thisFrame, g.getBegriffS("Dialog","Parameter"), true);
    DlgP.getContentPane().setLayout(new BorderLayout(2, 2));
    JPanel PnlW = new JPanel(new GridLayout(0, 1));
    JPanel PnlC = new JPanel(new GridLayout(0, 1));
    JPanel PnlS = new JPanel(new GridLayout(1, 0));
    g.addLabel2(PnlW, "Spaltenbreite"); //1
    g.addLabel2(PnlW, "Zeilenhoehe");
    if (!Schicht())
      g.addLabel2(PnlW, "Darstellung");
    //PnlW.add(new JLabel()); // Tag
    //PnlW.add(new JLabel()); // Woche
    PnlW.add(new JLabel()); // MZ
    PnlW.add(new JLabel()); // 2
    PnlW.add(new JLabel());
    //if (g.Def() || !Schicht())
    //  PnlW.add(new JLabel()); //3
    final SpinBox SBsb = new SpinBox();
    final SpinBox SBzh = new SpinBox();
    final AUCheckBox CbxMZ=g.getCheckbox("Mehrzeilen",bMZ);
    final AUCheckBox CbxAsk=g.getCheckbox("Frage",bAsk);
    final AUCheckBox CbxMove=g.getCheckbox("verschieben",bMove && bSave);
    //final AUCheckBox CbxOld=g.getCheckbox("Altdaten",bOld);
    final AUCheckBox CbxVoll=g.getCheckbox("voll",bVoll);
    final AUCheckBox CbxVBT=g.getCheckbox("von_bis_Text",bVB);
    //final AUCheckBox CbxKRNS=g.getCheckbox("kein Save-Refresh",bkRnS);
    final AUCheckBox CbxAZ=g.getCheckbox("AZ",bAZ);
    final AUCheckBox CbxPPS=g.getCheckbox("PPS",bPPS);
    final AUCheckBox CbxIST=g.getCheckbox("ist",bIst);
    final AUCheckBox CbxSOLL=g.getCheckbox("soll",bSoll);
    final AUCheckBox CbxDIFF=g.getCheckbox("Diff",bDiff);
    final AUCheckBox CbxSALDO=g.getCheckbox("Saldo",bSaldo);
    final AUCheckBox CbxPAUSE=g.getCheckbox("Pause",bPause);
    //CbxPAUSE.setAlignmentY(CENTER_ALIGNMENT);
    //CbxPAUSE.setMargin(new java.awt.Insets(0,20,0,0));
    //final AUCheckBox CbxKST=g.getCheckbox("KSt",bKSt);
    final AUCheckBox CbxSUM=g.getCheckbox("Sum",bSum);
    final AUCheckBox CbxKZS=g.getCheckbox("keine Zwischensumme",bKZS);
    final AUCheckBox CbxBEGINN=g.getCheckbox("Beginnsuche",bBeginn);
    final AUCheckBox CbxTMZ=g.getCheckbox("TMZ",bTMZ);
    final AUCheckBox CbxESUM=g.getCheckbox("ESum",bESum);
    final AUCheckBox CbxSES=g.getCheckbox("gesperrt lassen",bSES);
    //final AUCheckBox CbxTag=g.getCheckbox("Tage",bTag);
    //final AUCheckBox CbxWoche=g.getCheckbox("Wochen",bWoche);
    final JRadioButton RadTag=g.getRadiobutton("Tage");
    final JRadioButton RadWoche=g.getRadiobutton("Wochen");
    final JRadioButton RadAuto=g.getRadiobutton("Auto2");
    ButtonGroup group = new ButtonGroup();
    group.add(RadTag);
    group.add(RadWoche);
    group.add(RadAuto);
    if (bTag) RadTag.setSelected(true); else if (bWoche) RadWoche.setSelected(true); else RadAuto.setSelected(true);
    CbxTMZ.setMargin(new java.awt.Insets(0,20,0,0));
    CbxESUM.setMargin(new java.awt.Insets(0,20,0,0));
    CbxKZS.setMargin(new java.awt.Insets(0,20,0,0));
    final AUCheckBox CbxVA=g.getCheckbox("Austritt",bVA);

    final Text EdtDruckTitel=new Text(sDruckText,30);
    final AUCheckBox CbxDrehen=g.getCheckbox("drehen",(iDBits&DREHEN)>0);
    //CbxDrehen.setEnabled(false);
    final AUCheckBox CbxMemo=g.getCheckbox("Memo",(iDBits&MEMO)>0);
    final AUCheckBox CbxMemo2=g.getCheckbox("Memo2",(iDBits&MEMO2)>0);
    final AUCheckBox CbxDruckerAuswahl=g.getCheckbox("Druckerauswahl",(iDBits&DAUSW)>0);
    final AUCheckBox CbxFarbe=g.getCheckbox("Farbe",(iDBits&FARBE)>0);
    CbxMemo2.setMargin(new java.awt.Insets(0,20,0,0));
    SBsb.setIntValue(iSpaltenbreite);
    SBsb.setMinimum(20);
    SBzh.setIntValue(iZeilenhoehe);
    g.addComp(PnlC, SBsb);   // 1
    g.addComp(PnlC, SBzh);
    if (!Schicht())
    {
      //g.addComp(PnlC, CbxTag);
      //g.addComp(PnlC, CbxWoche);
      JPanel Pnl=new JPanel(new FlowLayout(FlowLayout.LEFT,5,2));
      Pnl.add(RadTag);
      Pnl.add(RadWoche);
      Pnl.add(RadAuto);
      g.addComp(PnlC,Pnl);
    }
    g.addComp(PnlC, CbxMZ);
    if (bSave)
    {
      g.addComp(PnlC, CbxAsk); // 2
      g.addComp(PnlC, CbxSES);
      if (g.Def())// || AZ())
      {
        PnlW.add(new JLabel());
        g.addComp(PnlC, CbxMove); // 3
      }
    }
    //if (g.Def())
    //  g.addComp(PnlC, CbxKRNS);
    PnlW.add(new JLabel());
    g.addComp(PnlC, CbxVA);
    /*if (AZ())
    {
      PnlW.add(new JLabel());
      g.addComp(PnlC, CbxOld);
    }*/
    PnlW.add(new JLabel());
    g.addComp(PnlC, CbxVoll);
    //if (Schicht())
    {
      PnlW.add(new JLabel());
      g.addComp(PnlC, CbxVBT);
    }
    //PnlW.add(new JLabel());
    //g.addComp(PnlC, CbxKST);
    PnlW.add(new JLabel());
    g.addComp(PnlC, CbxBEGINN);
    if (Schicht())
    {
      PnlW.add(new JLabel());
      g.addComp(PnlC, CbxSUM);
      PnlW.add(new JLabel());
      PnlW.add(new JLabel());
      PnlW.add(new JLabel());
      g.addComp(PnlC, CbxTMZ);
      g.addComp(PnlC, CbxESUM);
      g.addComp(PnlC, CbxKZS);
      g.addLabel2(PnlW, "Spalten");
      PnlW.add(new JLabel());
      PnlW.add(new JLabel());
      PnlW.add(new JLabel());
      PnlW.add(new JLabel());
      PnlW.add(new JLabel());
      g.addComp(PnlC, CbxAZ);
      g.addComp(PnlC, CbxPPS);
      g.addComp(PnlC, CbxIST);
      g.addComp(PnlC, CbxPAUSE);
      g.addComp(PnlC, CbxSOLL);
      g.addComp(PnlC, CbxDIFF);
      if (iLoadSaldo>0)
      {
        PnlW.add(new JLabel());
        g.addComp(PnlC, CbxSALDO);
      }
    }
    final DefVorlagen CboVorlagen = Schicht() ? new DefVorlagen(g,true):null;
    JPanel PnlLayout = new JPanel();
    final ComboSort CboLayout = Schicht() ? Seitenlayout.getCbo(g, PnlLayout):null;

    if (Schicht())
    {
      PnlW.add(new JLabel());
      PnlC.add(new JLabel());
      g.addLabel2(PnlW, "Drucktitel");
      g.addComp(PnlC, EdtDruckTitel);

      CboVorlagen.setSelectedAIC(iVorlage);
      g.addLabel2(PnlW, "Vorlagen");
      g.addComp(PnlC, CboVorlagen);
      CboLayout.setSelectedAIC(iLayout);
      g.addLabel2(PnlW, "Layout");
      g.addComp(PnlC, PnlLayout);
      PnlW.add(new JLabel());
      g.addComp(PnlC, CbxDrehen);
      PnlW.add(new JLabel());
      g.addComp(PnlC, CbxMemo);
      PnlW.add(new JLabel());
      g.addComp(PnlC, CbxMemo2);
      PnlW.add(new JLabel());
      JPanel Pnlx=new JPanel(new FlowLayout(FlowLayout.LEFT,0,2));
      Pnlx.add(CbxFarbe);
      Pnlx.add(CbxDruckerAuswahl);
      g.addComp(PnlC,Pnlx);
    }
    ActionListener ALP=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s = ev.getActionCommand();
        if (s.equals("InitP"))
        {
          new Parameter(g, "Planung" + iPlanung).deleteAll();
          //g.fixInfo("ZA-vorher:"+iZAcopy+"/"+iLastZA+"/"+CboZeitart.getAIC());
          CboZeitart.getComboBox().setSelectedAIC(0);
          CboZeitart.getComboBox().setFirst();iZAcopy=CboZeitart.getAIC();iLastZA=iZAcopy;
          //g.fixInfo("ZA-danach:"+iZAcopy+"/"+iLastZA+"/"+CboZeitart.getAIC());
          iSpaltenbreite=iSpaltenOri%1000;iZeilenhoehe=iSpaltenOri/1000;
          iLastSB=iSpaltenbreite;iLastZH=iZeilenhoehe;SBsb.setValue(iSpaltenbreite);SBzh.setValue(iZeilenhoehe);
          bAsk=true;bMove=false;/*bOld=false;*/bVoll=false;bVB=false;bMZ=false;
          bAZ=Schicht();bIst=false;bSoll=false;bDiff=false;bSaldo=false;bPPS=false;
          bPause=false;/*bKSt=false;*/bSum=false;bKZS=false;bBeginn=false;bTMZ=false;bVA=false;bESum=false;bSES=false;
          CbxAsk.setSelected(bAsk);CbxMove.setSelected(bMove && bSave);/*CbxOld.setSelected(bOld);*/CbxVoll.setSelected(bVoll);CbxVBT.setSelected(bVB);CbxMZ.setSelected(bMZ);
          CbxAZ.setSelected(bAZ);CbxIST.setSelected(bIst);CbxSOLL.setSelected(bSoll);CbxDIFF.setSelected(bDiff);CbxSALDO.setSelected(bSaldo);CbxPPS.setSelected(bPPS);
          CbxPAUSE.setSelected(bPause);/*CbxKST.setSelected(bKSt);*/CbxSUM.setSelected(bSum);CbxBEGINN.setSelected(bBeginn);CbxTMZ.setSelected(bTMZ);CbxVA.setSelected(bVA);
          CbxESUM.setSelected(bESum);RadTag.setSelected(bTag);RadWoche.setSelected(bWoche);RadAuto.setSelected(!bTag && !bWoche);CbxKZS.setSelected(bKZS);CbxSES.setSelected(bSES);
        }
        else if (s.equals("OkP"))
        {
          iSpaltenbreite = SBsb.getIntValue();
          iZeilenhoehe = SBzh.getIntValue();
          bMZ = CbxMZ.isSelected();
          bAsk = CbxAsk.isSelected();
          bMove = CbxMove.isSelected();
          //bOld = CbxOld.isSelected();
          //bkRnS=CbxKRNS.isSelected();
          bVoll = CbxVoll.isSelected();
          bVB = CbxVBT.isSelected();
          bAZ = CbxAZ.isSelected();
          bPPS= CbxPPS.isSelected();
          bIst = CbxIST.isSelected();
          bSoll = CbxSOLL.isSelected();
          bDiff = CbxDIFF.isSelected();
          bSaldo = CbxSALDO.isSelected();
          bPause = CbxPAUSE.isSelected();
          //bKSt = CbxKST.isSelected();
          bSum = CbxSUM.isSelected();
          bKZS = CbxKZS.isSelected();
          bBeginn = CbxBEGINN.isSelected();
          bTMZ = CbxTMZ.isSelected();
          bESum = CbxESUM.isSelected();
          bTag = RadTag.isSelected();
          bWoche = RadWoche.isSelected();
          bVA = CbxVA.isSelected();
          bSES = CbxSES.isSelected();
          int iBitsNeu = getPBits();
          Parameter Para=null;
          if (iLastZA != CboZeitart.getAIC() || iBitsNeu != iLastBits || iSpaltenbreite != iLastSB || iZeilenhoehe != iLastZH || iLoadFarbe > 0 && iLastAF != CboFarbe.getAIC())
          {
            iLastZA = CboZeitart.getAIC();
            iLastBits = iBitsNeu;
            iLastSB = iSpaltenbreite;
            iLastZH = iZeilenhoehe;
            g.progInfo("Save Parameter:" + iLastZA + "/" + iBitsNeu + "/" + iSpaltenbreite);
            Para = new Parameter(g, "Planung" + iPlanung);
            Para.setParameter("Planung", "", iLastZA, iBitsNeu, iSpaltenbreite+iZeilenhoehe*1000, iLastAF, true, false);
          }
          if (Schicht())
          {
            int iDBitsNeu = (CbxDrehen.isSelected() ? DREHEN : 0) + (CbxMemo2.isSelected() ? MEMO2 : 0) + (CbxMemo.isSelected() ? MEMO : 0)
                 + (CbxDruckerAuswahl.isSelected() ? DAUSW : 0) + (CbxFarbe.isSelected() ? FARBE : 0);
            if(iLayout != CboLayout.getSelectedAIC() || iVorlage != CboVorlagen.getSelectedAIC() || !EdtDruckTitel.getText().equals(sDruckText) ||
               iDBitsNeu != iDBits) {
              sDruckText = EdtDruckTitel.getText();
              EdtDruckTitel.setText(sDruckText);
              iLayout = CboLayout.getSelectedAIC();
              CboLayout.setAktAIC(iLayout);
              iVorlage = CboVorlagen.getSelectedAIC();
              CboVorlagen.setSelectedAIC(iVorlage);
              iDBits = iDBitsNeu;
              CbxDrehen.setSelected((iDBits&DREHEN)>0);
              CbxMemo2.setSelected((iDBits&MEMO2)>0);
              CbxMemo.setSelected((iDBits&MEMO)>0);
              CbxDruckerAuswahl.setSelected((iDBits&DAUSW)>0);
              CbxFarbe.setSelected((iDBits&FARBE)>0);
              //g.fixtestInfo("Speichere Druck-Parameter:" + sDruckText + "/" + iLayout + "/" + iVorlage + "/" + iDBits);
              if(Para == null)Para = new Parameter(g, "Planung" + iPlanung);
              Para.setParameter("Druck", sDruckText, iLayout, iVorlage, iDBits, 0, true, false);
            }
          }
          if (Para!=null) Para.free();
          DlgP.setVisible(false);
        }
        else if (s.equals("AbbruchP"))
        {
          bMZ = (iLastBits & MZ) > 0;
          bAsk = (iLastBits & ASK) > 0;
          bMove = (iLastBits & MOVE) > 0;
          //bOld = (iLastBits & OLD) > 0;
          bVoll = (iLastBits & VOLL) > 0;
          bVB = (iLastBits & VBT) > 0;
          bAZ = (iLastBits & PAZ) > 0;
          bPPS = (iLastBits & PPS) > 0;
          bIst = (iLastBits & IST) > 0;
          bSoll = (iLastBits & SOLL) > 0;
          bDiff = (iLastBits & DIFF) > 0;
          bSaldo = (iLastBits & SALDO) > 0;
          bPause = (iLastBits & PAUSE) > 0;
          //bKSt = (iLastBits & KST) > 0;
          bSum = (iLastBits & SUM) > 0;
          bKZS = (iLastBits & KZS) > 0;
          bBeginn = (iLastBits & BEGINN) > 0;
          bTMZ = (iLastBits & TMZ) > 0;
          bESum = (iLastBits & ESUM) > 0;
          bTag = (iLastBits & TAG) > 0;
          bWoche = (iLastBits & WOCHE) > 0;
          bVA = (iLastBits & VA) > 0;
          bSES = (iLastBits & SES) > 0;
          SBsb.setIntValue(iLastSB);
          SBzh.setIntValue(iLastZH);
          DlgP.setVisible(false);
        }
      }
    };
    JButton BtnInit=g.getButton("Init","InitP",ALP);
    JButton BtnDOk = g.getButton("Ok","OkP",ALP);
    JButton BtnDAbbruch = g.getButton("Abbruch","AbbruchP",ALP);
    PnlS.add(BtnInit);
    PnlS.add(BtnDOk);
    PnlS.add(BtnDAbbruch);
    DlgP.getContentPane().add("West", PnlW);
    DlgP.getContentPane().add("Center", PnlC);
    DlgP.getContentPane().add("South", PnlS);
    DlgP.pack();
    Static.centerComponent(DlgP,Fom.thisFrame);
  }
  DlgP.setVisible(true);
}

private void changeZR(String s)
{
  //DateWOD DWvon=new DateWOD(g.getVon());
  //DateWOD DWbis=new DateWOD(g.getBis());
  String sZA=sZeitartMom;
  if (sZA.equals("Tag") || sZA.equals("Woche"))
    sZA="Monat";
  if (s.startsWith("ZRplus"))
  {
    if (s.equals("ZRplus"))
      DWvon.next(sZA);
    DWbis.next(sZA);
  }
  else
  {
      if (s.equals("ZRminus") || ((DWbis.getTimeInMilli()-DWvon.getTimeInMilli())<DateWOD.TAG*(sZA.equals("Jahr") ? 400:sZA.equals("Quartal") ? 100:35)))
        DWvon.prev(sZA);
      //if (s.equals("ZRminus"))
        DWbis.prev(sZA);
  }
  Timestamp TSZeitraumVonOld=g.getVon();
  Timestamp TSZeitraumBisOld=g.getBis();
  g.setVonBis(DWvon.toTimestamp(),DWbis.toTimestamp());
  Refresh(VecNeu,iAicStamm,true);
  g.setVonBis(TSZeitraumVonOld,TSZeitraumBisOld);
}

private void Jetzt()
{
  Timestamp TSZeitraumVonOld=g.getVon();Timestamp TSZeitraumBisOld=g.getBis();
  g.setAktDate(true);
  Zeitraum.PeriodeToVec(g,g.getZA(0),true);
  DWvon=new DateWOD(g.getVon());DWbis=new DateWOD(g.getBis());
  //g.fixInfo("Jetzt:"+DWvon+"/"+DWbis);
  Refresh(VecNeu, iAicStamm, true);
  g.setVonBis(TSZeitraumVonOld,TSZeitraumBisOld);
}

public void Reset()
{
	if (Modified())
		if (!isShowing())
			TabButton.clearAll();
		else
			Refresh();
}

public void Refresh()
{
  bSaveMom=true;
  if (BtnInfo!=null) BtnInfo.setEnabled(true);
  Refresh(VecNeu, iAicStamm, true);
}

public void setInfo()
{
  if ((iBits&cstAuswahl)>0 && BtnInfo!= null && !BtnInfo.isSelected())
  {
    BtnInfo.setSelected(true);
    BtnInfo.setEnabled(true);
    setMode("Info");
  }
}

private int addZeile(int i,Tabellenspeicher Tab,Vector<String> VecBez,Vector<JCCellRange> VecSpan,int iGold)
{
  String sBez;
  if (iGold<0)
  {
    //boolean bG = sLoadKSt != null && bKSt;
    sBez = AbfSonst == null ? Tab.getS("Bezeichnung") : AbfSonst.TabToString(Tab); // sBez statt sBez2
    //sBez = (bG && AbfSonst == null && !bMZ ? Tab.getS(sLoadKSt) + (bMZ ? "\n" : " ") : "") + sBez2;
    VecBez.addElement(bMZ /*&& !bG*/ ? Static.cutString(sBez) : sBez);
    CboStamm.addItem2(sBez,Tab.getI("aic_stamm"),"");
  }
  else
  {
    sBez = iGold == 0 ? Static.sKein : g.getStamm(iGold);
    VecBez.addElement(sBez);
  }
  //g.fixInfo("Zeile für "+(iGold<0 ? "Einzelzeile ":"Gruppe ")+sBez);
  VecSpan.addElement(new JCCellRange(i,0,i,iGold<0 ? iAnz-1:0));
  JPanel Pnl = new JPanel(null);
  if (iGold<0)
  {
    Pnl.setBackground(Global.ColPlanung);
    table.setCell(i, 0, Pnl);
  }
  int iStammMom=Tab.getI("aic_stamm");
  if(iAicStamm==iStammMom)
          table.setSelectedCells(new JCCellRange(i,-1,i,0));
  //table.setFont(i,-1,/*iStamm==Qry.getI("aic_stamm")?g.fontTitel:*/g.fontBezeichnung);
  table.setFont(i,-1,gesperrt(iStammMom,false) ? Global.fontInaktiv:Global.fontBezeichnung);
  table.setForeground(i,-1,iGold>=0 ? Global.ColTitel:/*gesperrt(iStammMom,false) ? g.ColSperre :*/ ShowAbfrage.istDa(g,iStammMom) ? Global.ColBezeichnung:Global.ColAustritt);
  //Color ColSum=iGold>=0 ? table.getBackground(i-1,-1).brighter():null;
  if (iGold>=0)
  {
    //Pnl.setBackground(ColSum);
    for (int i2 = -1; i2 < table.getNumColumns(); i2++)
    {
      table.setBackground(i, i2, Global.ColZSum /*ColSum*/); //g.ColPlanung.darker());
      //table.setCell(i,i2,null);
    }
  }
  else
    table.setBackground(i,-1,/*ColSum!=null ? ColSum:*/sLoadFarbe != null && Tab.exists(sLoadFarbe) && !Tab.isNull(sLoadFarbe) ? new Color(Tab.getI(sLoadFarbe)):Global.ColPlanung);

  table.setAlignment(i,-1,JCTblEnum.MIDDLELEFT);
  int iH=iGold>=0 ? 23:iZeilenhoehe>20?iZeilenhoehe:bMZ?40:/*AZ()?33:*/23;
  if (Fom.iFF != 100)
	  iH=Fom.iFF*iH/100;
  if (table.getPixelHeight(i) != iH)
    table.setPixelHeight(i,iH);
  TabPanel.addInhalt("AIC_Stamm",iGold<0 ? Tab.getI("AIC_Stamm"):iGold==0? -1:iGold);
  TabPanel.addInhalt("Bezeichnung",sBez);
  TabPanel.addInhalt("Panel",Pnl);
  //TabPanel.addInhalt("Art",iGold<0 ? null:"sum");
  TabPanel.addInhalt("Vec",iGold<0 ? null:Tab.groupVecAic(iGold));
  TabPanel.addInhalt("VecAZ",iGold<0 ? null:new Vector<JLabel>());
  return i+1;
}

private Vector check(Vector Vec,Vector Vec2)
{
  if (Vec2==null || Vec==null)
    return Vec;
  for(int i=0;i<Vec.size();)
    if (Vec2.contains(Vec.elementAt(i)))
      i++;
    else
      Vec.removeElementAt(i);
  return Vec;
}

@SuppressWarnings("unchecked")
public void Refresh(Vector rVecStamm,int iStamm,boolean bRefresh)
{
  g.fixtestInfo("Refresh1 "+sBez+":"+rVecStamm+"/"+iStamm+"/"+g.getVonBis2());
  if (rVecStamm!=null && rVecStamm.size()>0)
  {
    Vector VecPStamm = g.getVecStamm(Fom.getBegriff(), Fom.iStammtyp);
    rVecStamm = check(rVecStamm, VecPStamm);
    if (VecPStamm != null && !VecPStamm.contains(iStamm))
      iStamm = 1; // ist Stammsatz nicht erlaubt wird auf einen nicht vorhandenen gesetzt, damit Refresh funktioniert
    if (VecPStamm != null)
      g.fixtestInfo("Refresh2 " + sBez + ":" + rVecStamm + "/" + iStamm + " bei VecPStamm=" + VecPStamm);
  }
  /*if (rVecStamm != null)
    for (int i=0;i<rVecStamm.size();i++)
      g.progInfo(i+".Stamm: "+Static.print(rVecStamm.elementAt(i)));*/
  if (rVecStamm==null || rVecStamm.size()==0)
    if (iStamm <= 0)
    {
      g.fixInfo("---------------------- Planung.Refresh -------------- :" + rVecStamm + "/" + iStamm + "/" + bRefresh);
      //Static.printError("Planung.Refresh " + sBez + " nicht möglich da keine Stammsätze");
      //return;
      if (rVecStamm==null)
        rVecStamm=new Vector();
      rVecStamm.addElement(1);
      iStamm=1;
    }
    else
      rVecStamm = Static.AicToVec(iStamm);
  //g.fixtestInfo("Refresh "+iStamm+", bSave="+bSave+", Modified="+Modified()+", bSaveMom="+bSaveMom);
  if (bSave && Modified() && !bSaveMom)
  {
    int i=new Message(Message.QUESTION_MESSAGE, null, g).showDialog("Speichern");
    if (i==Message.CANCEL_OPTION)
      return;
    else if (i == Message.YES_OPTION)
    {
      Save();
      return;
    }
    else if (!bSES)
      setInfo();
  }

  g.testInfo("                 Planung.Refresh:"+VecStamm+"->"+rVecStamm+"/"/*+g.getStamm(iStamm)+" ("*/+iStamm+" /"+bRefresh);
  int iAnzDavor=VecStamm.size();
  //g.progInfo("Refresh1: Static.sZeitart="+Static.sZeitart);
  if (QrySave!=null && QrySave.bExec2)
  {
    g.fixInfo("Refresh momentan nicht möglich!");
    return;
  }
  if (AbfDaten==null || !g.isAbfrageAktiv(iD/*Fom.getBegriff(),AbfDaten.iBegriff*/)|| !isShowing() && (Transact.iInfos&Transact.NO_SPEED)==0 || iStamm<=0 && !bRefresh)
  {
    g.testInfo("!!! not Refresh Planung "+sBez);
    setEnabled(false);
    table.setNumRows(0);
    table.setNumColumns(0);
    if (iStamm>0)
      iAicStamm=iStamm;
    if (popup==null && /*g.Def() &&*/ AbfDaten!=null)
      addEvent(null);
    //VecStamm=rVecStamm;
    return;
  }
  Timestamp TSZeitraumVonOld=g.getVon();
  Timestamp TSZeitraumBisOld=g.getBis();
  if ((iBits&cstZR)>0)
    if (DWvon==null)
    {
      DWvon = new DateWOD(g.getVon());
      DWbis = new DateWOD(g.getBis());
    }
    else
      g.setVonBis(DWvon.toTimestamp(),DWbis.toTimestamp());
  setEnabled(true);
  //if (iStamm>0 && rVecStamm==null)// || !rVecStamm.contains(new Integer(iStamm))))
  //  rVecStamm=Static.AicToVec(iStamm);
  //g.testInfo("rVecStamm2="+rVecStamm);
  long lClock1=Static.get_ms();
  setVisible(false);
  	g.clockInfo2("Refresh",lClock);
	lClock=Static.get_ms();
	iAicStamm=iStamm;
	if(iStamm !=0)
		CboStamm.setSelectedAIC(iStamm);
        if (Schicht())
        {
          g.setVar("CbxAZ",bAZ);
          g.setVar("CbxPPS",bPPS);
          g.setVar("CbxIst",bIst);
          g.setVar("CbxSoll",bSoll);
          g.setVar("CbxDiff",bDiff);
          g.setVar("CbxSaldo",bSaldo);
          g.setVar("CbxPause",bPause);
          //g.TabVar.showGrid("TabVar");
        }
        TabSonst=null;
        if (AbfSonst != null)
        {
          //g.progInfo("TabSonst mit "+AbfDaten.iStt+"/"+iStamm+"/"+rVecStamm);
          if (iStamm<=0)
            iStamm=VecStamm!=null && VecStamm.size()>0 ? Sort.geti(VecStamm,0):1;
          g.fixtestInfo("VecStamm vor AbfSonst.getDaten: "+iStamm+"/"+VecStamm);
          TabSonst=AbfSonst.getDaten(AbfDaten.iStt,iStamm,rVecStamm,Fom.thisFrame);
          g.progInfo("VecStamm nach AbfSonst.getDaten:"+VecStamm);
          //TabSonst.showGrid();
          if (TabSonst==null)
            Static.printError("Sonst-Abfrage "+AbfSonst.sDefBez+" bringt keine Daten");
          else
          {
            TabSonst.sAIC = "Aic_Stamm";
            TabSonst.sGruppe="V"+iEigGruppe;
          }
          //g.progInfo("rVecStamm="+rVecStamm);
        }
        //g.fixtestInfo(iRefresh+"*****");
        //g.fixtestInfo("iStt="+AbfDaten.iStt+", AbfSonst="+AbfSonst+", TabSonst="+Static.print(TabSonst));
        if (TabSonst == null && AbfDaten.iStt>0)
        {
          //g.fixtestInfo("Hole TabSonst für "+rVecStamm);
		TabSonst=new Tabellenspeicher(g,"select aic_stamm,Bezeichnung from stammview where aic_stamm"+Static.SQL_in(rVecStamm)+
                                              "and aic_stammtyp="+AbfDaten.iStt+Abfrage.Rolle(iRolle)+((iBits&cstNotSelf)>0?" and aic_stamm<>"+g.getStamm():""),true);
                TabSonst.sAIC="Aic_Stamm";
        }
        if ((AbfDaten.iBits&Abfrage.cstKeinAustritt)==0 && !bVA)
          ShowAbfrage.removeAustritt(g,TabSonst,false);
        rVecStamm=TabSonst.getVecSpalte("Aic_Stamm");
        //g.fixtestInfo("rVecStamm="+rVecStamm+" | "+(VecStamm.size()-rVecStamm.size()));
	boolean bStammChange=iAnzDavor!=rVecStamm.size() || !VecStamm.containsAll(rVecStamm);
        if (bStammChange)
          setInfo();
        VecStamm=rVecStamm;
	VecNeu=VecStamm;
	//PntPanel=null;
        //g.testInfo("Refresh/Change="+bRefresh+"/"+bStammChange);
        //g.fixtestInfo("Refresh/Change="+bRefresh+"/"+bStammChange+"/ rows="+table.getNumRows()+"/ iAnzDavor="+iAnzDavor+"->"+rVecStamm.size());
	if(bRefresh||bStammChange||table.getNumRows()==0)
	{
          g.fixtestInfo("Neuaufbau da "+(bStammChange?"Stammänderung":bRefresh?"Refresh":"keine Zeile"));
          Cursor Cur=Fom.thisFrame.getCursor();
          Fom.thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
          //table.clearCells();
          table.deleteRows(0,table.getNumRows()); // 3010..nötig damit bei Zeitraumwechselalte Daten gelöscht und somit neue sichtbar sind
          table.setCell(0,0,new JPanel());
          VecAZ=null;
          String sZeitartOld=g.getZA(0);
          if ((iBits&cstZR)==0)
          {
            TSZeitraumVonOld = g.getVon();
            TSZeitraumBisOld = g.getBis();
          }
          sZeitartMom=sZeitartOld;
          if ((sZeitartMom.equals("Tag") || sZeitartMom.equals("Woche")) && TSZeitraumBisOld.getTime()-TSZeitraumVonOld.getTime()<DateWOD.WOCHE*4 && (iBits&cstZR)>0)
          {
            DateWOD dtVon= new DateWOD(g.getVon());
            dtVon.set(Calendar.DAY_OF_MONTH,1);
            g.setVon(dtVon.toTimestamp());
            dtVon.nextMonth();
            g.setBis(dtVon.toTimestamp());
          }
          if (bTag || bWoche || (iBits&(cstDays+cstWeeks))>0 && !sZeitartMom.equals("Tag") || sArt != null)
          {
            //g.fixtestInfo("Art="+sArt+", bTag="+bTag+", g.sZeitart="+g.sZeitart);
            g.setZA(0,sArt != null ? sArt:bTag || (iBits&cstDays)>0 && !bWoche ? "Tag":"Woche");
            //g.progInfo("Zeitart="+Static.sZeitart);
            if (bTag || (iBits&cstDays)>0 && !bWoche && sArt==null || sArt != null && sArt.equals("Tag"))
              Zeitraum.PeriodeToVec(g,"Tag",false);
            else if (bWoche || (iBits&cstWeeks)>0 && sArt==null || sArt != null && sArt.equals("Woche"))
              Zeitraum.PeriodeToVec(g,"Woche",true);
            else if (sArt != null && sArt.equals("Monat"))
              Zeitraum.PeriodeToVec(g,"Monat",true);
          }
          else if (TSZeitraumBisOld.getTime()-TSZeitraumVonOld.getTime()>DateWOD.WOCHE*105  && sZeitartMom.equals("Tag"))
          {
            g.progInfo("schalte auf Zeitart Woche");
            g.setZA(0,"Woche");
          }
          sZeitartMom=g.getZA(0);
          if (sZeitartMom.equals("Tag") && !BtnTag.isSelected())
            BtnTag.setSelected(true);
          else if (sZeitartMom.equals("Woche") && !BtnWoche.isSelected())
            BtnWoche.setSelected(true);
          else if (sZeitartMom.equals("Monat") && !BtnMonat.isSelected())
            BtnMonat.setSelected(true);

          g.progInfo("Refresh2: Static.sZeitart="+sZeitartMom);
		bReady=false;
		g.progInfo("Planung.Refresh():"+rVecStamm+"/"+bRefresh);
		TabButton = new Tabellenspeicher(g,new String[]{"Button","Stamm_Alt","Stamm","Von_Alt","Bis_Alt","Von2_Alt","Bis2_Alt","Dauer_Alt","Von_Neu","Bis_Neu","Von2_Neu","Bis2_Neu","Dauer_Neu","Zeitart","Zeitart_Alt","Farbe2","Farbe_Alt","Status","Status_Alt","StatusSV","StatusSV_Alt","VecAIC","BewBew"});
                TabButton.sGruppe="Stamm";
                TabButton.sAIC="Von_Neu";
		TabPanel = new Tabellenspeicher(g,new String[]{"AIC_Stamm","Bezeichnung","Panel","Vec","VecAZ"});
		if (iRest>0)
		{
			TabRest = new Tabellenspeicher(g,new String[]{"Button","Nr","Neu","Alt"});
			TabRest.sGruppe="Button";
			TabRest.sAIC="Nr";
			TabRest.bInsert=false;
		}
                if (bStammChange)
                  PntPanel=null;
		//SQL Qry = new SQL(g);
		/////////////////
		//Abfrage Daten////////////////////////////////////////////////////////////////////////////////
		/////////////////

		g.clockInfo2("Refresh-Start ",lClock);
		Timestamp dtVon=g.getVon();
                if (Schicht() && !TGM())
                  g.setVon(new Timestamp(new DateWOD(dtVon).yesterday()));
                //int iAicStammVor=g.getSyncStamm(AbfDaten.iStt);
                g.progInfo(" ---------------------------------------------- vor TabDaten ----------------------------------------------");
                //if ((iBits&cstAuswahl)>0 && iMode>_INFO)
                //  setSperre();
		TabDaten=AbfDaten.getDaten(0,iStamm,VecStamm,Fom.thisFrame);
                g.progInfo(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< nach TabDaten <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                if (Schicht() && !TGM())
                  g.setVon(dtVon);
                TabDaten.sAIC="E"+iLoadDatum;
                TabDaten.sGruppe="V"+iLoadStamm;
		g.clockInfo2("Refresh-Daten ",lClock);
		///////////////////////////////////////////////////////////////////////////////////////////////

		//////////////////
		//Abfrage Filter///////////////////////////////////////////////////////////////////////////////
		//////////////////
		String sZeitart=g.getZA(0);
		if(Schicht() || bStammChange || TSZeitraumVon==null || TSZeitraumVon.getTime()!=g.getVon().getTime() || TSZeitraumBis.getTime()!=g.getBis().getTime())
		{
                  g.progInfo(" ---------------------------------------------- vor TabFilter ----------------------------------------------");
			if (AbfFilter !=null)
				TabFilter=AbfFilter.getDaten(0,iStamm,VecStamm,Fom.thisFrame);
                  g.progInfo(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< nach TabFilter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            
			if (sZeitart.equals("Woche"))
                        {
                          //g.progInfo("schalte auf Woche");
                          Zeitraum.PeriodeToVec(g, sZeitart, true);
                        }
			TSZeitraumVon=/*AZ() ? TSZeitraumVonOld:*/checkTage(true);//g.getVon();
			TSZeitraumBis=checkTage(false);//g.getBis();
			g.fixInfoT("ZR1="+TSZeitraumVon+"-"+TSZeitraumBis);
                        /*if (AZ())
                        {
                          if (!bOld)
                            TSNow=SQL.getToday(g);
                          if (bOld || TSZeitraumVon.after(TSNow))
                            TSNow=TSZeitraumVon;
                        }
                        else*/
                          TSNow=TSZeitraumVon;
			if (TabFilter != null)
			{
//				if ((Transact.iInfos&4)>0)
//				{
//					iFilter++;
//					new Tabellenspeicher(g,TabFilter).showGrid("Filter"+iFilter);
//				}
				TabFilter.sAIC="E"+iLoadFilterGueltig;
				TabFilter.sGruppe="V"+iLoadFilterStamm;
				g.fixInfoT("TabFilter: sAic="+TabFilter.sAIC+", sGruppe="+TabFilter.sGruppe);
			}
		///////////////////////////////////////////////////////////////////////////////////////////////
		}
                //g.setSyncStamm(AbfDaten.iStt,iAicStammVor);
	     g.clockInfo2("Refresh-Filter",lClock);
                if (sZeitartOld.equals(sZeitart))
                  Zeitraum.PeriodeToVec(g,sZeitart,false);
                g.progInfo("VecPer="+Sort.gets2(g.VecPerioden,';'));
                iAnz=g.VecPerioden.size()-1;
                iTage=sZeitart.equals("Tag") ? iAnz:(int)((TSZeitraumBisOld.getTime()-TSZeitraumVonOld.getTime())/DateWOD.TAG);
                //g.progInfo("Tage="+iTage);
                Vector<String> VecColumnLabel = new Vector<String>();
                int iFaktor=sZeitart.equals("Jahr")?16:sZeitart.equals("Quartal")?8:sZeitart.equals("Monat")?4:sZeitart.equals("Woche")?2:1;
		//g.progInfo("VecPerioden="+Zeitraum.VecPerioden);
             //g.clockInfo2("Refresh-Titel1",lClock);
                DateWOD TodayDate = new DateWOD();
                TodayDate.setTimeZero();
		for(int i=0;i<iAnz;i++)
		{
			VecColumnLabel.addElement(Static.FormatTS2(false/*AZ()*/,g.VecPerioden.elementAt(i),sZeitart));
			if (sZeitart.equals("Monat"))
			{
				double dMax=(g.VecPerioden.elementAt(iAnz)).getTime()-(g.VecPerioden.elementAt(0)).getTime();
				double d=(g.VecPerioden.elementAt(i+1)).getTime()-(g.VecPerioden.elementAt(i)).getTime();
				//g.progInfo(d+"/"+dMax);
				table.setPixelWidth(i,(int)Math.round(d*iAnz/dMax*iSpaltenbreite*iFaktor*Fom.iFF/100));
			}
			else
				table.setPixelWidth(i,iSpaltenbreite*iFaktor*Fom.iFF/100);
			if (table.getFont(-1, i) != Global.fontTitelzeile)
			  table.setFont(-1,i,g.getTabFontTZ());//g.fontTitelzeile);
			table.setForeground(-1,i,Global.ColBezeichnung);
                        if(sZeitart.equals("Tag"))
                        {
                          DateWOD dw = new DateWOD(g.VecPerioden.elementAt(i));
                          //int iD=dw.getDay();
                          /*Color Col=(AbfDaten.iBits&Abfrage.cstFeiertage)>0 && !g.Feiertag(dw).equals("") ? g.ColHoliday:
                              iD==dw.MONDAY ? g.ColMo: iD==dw.TUESDAY ? g.ColDi:iD==dw.WEDNESDAY ? g.ColMi:iD==dw.THURSDAY ? g.ColDo:
                              iD==dw.FRIDAY ? g.ColFr:iD==dw.SATURDAY ? g.ColSa:iD==dw.SUNDAY ? g.ColSo:null;
                          table.setBackground( -1, i,Col==null ? g.ColPlanung:Col);*/
                          table.setBackground( -1, i,(iBits&cstFTT)>0 && (!g.Feiertag(dw).equals("") || dw.getDay() == Calendar.SUNDAY) ? Global.ColHoliday :
                                               dw.equals(TodayDate) ? Global.ColToday:Global.ColPlanung);
                        }
                        else
                          table.setBackground( -1, i,Global.ColPlanung);
		}
	     //g.clockInfo2("Refresh-Titel2",lClock);
                int iP=0;
                /*if (AZ())
                {
                  VecColumnLabel.addElement(Static.cutString(g.getBegriffS("Checkbox","soll")));
                  VecColumnLabel.addElement(Static.cutString(g.getBegriffS("Checkbox","ist")));
                  VecColumnLabel.addElement(Static.cutString(g.getBegriffS("Checkbox","Saldo")));
                  iP=3;
                }
                else*/ if (Schicht())
                {
                  if (bAZ)  {VecColumnLabel.addElement(Static.cutString(g.getBegriffS("Checkbox", "AZ"   )));iPosA=iAnz+iP;iP++;}
                  if (bPPS) {VecColumnLabel.addElement(Static.cutString(g.getBegriffS("Checkbox", "PPS"  )));iPosP=iAnz+iP;iP++;}
                  if (bIst) {VecColumnLabel.addElement(Static.cutString(g.getBegriffS("Checkbox", "ist"  )));iPosI=iAnz+iP;iP++;}
                  if (bSoll){VecColumnLabel.addElement(Static.cutString(g.getBegriffS("Checkbox", "soll" )));iPosS=iAnz+iP;iP++;}
                  if (bDiff){VecColumnLabel.addElement(Static.cutString(g.getBegriffS("Checkbox", "Diff" )));iPosD=iAnz+iP;iP++;}
                  if (bSaldo){VecColumnLabel.addElement(Static.cutString(g.getBegriffS("Checkbox","Saldo")));iPosO=iAnz+iP;iP++;}
                  //table.setPixelWidth(iPosD,150);
                }
                //table.setForeground(-1,-1,g.ColPlanung);
                //table.setBackground(-1,-1,g.ColPlanung);
		table.setColumnLabels(VecColumnLabel);
             g.clockInfo2("Refresh-Titel ",lClock);
		CboStamm.Clear(false);
		//PntPanel=null;
		Vector<String> VecBez = new Vector<String>();
		Vector<JCCellRange> VecSpan = new Vector<JCCellRange>();
                Tabellenspeicher Tab=TabSonst;
                if (AbfSonst == null)
                  Tab=new Tabellenspeicher(g,"select bezeichnung,aic_stamm from stammview where aic_stamm"+Static.SQL_in(VecStamm)+Abfrage.Rolle(iRolle)+" order by bezeichnung",true);
		//Tab.showGrid("Tab");
                //if(Qry.open("select bezeichnung,kennung,aic_stamm from stammview where aic_stamm"+Static.SQL_in(VecStamm)+" and aic_rolle is null order by bezeichnung"))
                //System.runFinalization();
                //Static.sleep(300);
                System.gc();
             g.clockInfo2("Refresh-Zeilen1",lClock);
             table.setNumColumns(iAnz+iP);
             if (AbfSonst!=null)
               TabSpaltenSonst=AbfSonst.refreshTabSpalten();
                int iZeilen=0;
                if (Tab != null)
		{
                  int iGold=-1;
                  //int i=0;

			for(Tab.moveFirst();!Tab.out();Tab.moveNext())
                          if(/*(!AZ() || TabFilter.posInhalt("V"+iLoadFilterStamm,Tab.getI("aic_stamm"))) && */
                              ((iBits&cstBewilligung)==0 || TabDaten.posInhalt("V"+iLoadStamm,Tab.getI("aic_stamm"))))
                          {
                            if (ZS() && Tab.exists("V"+iEigGruppe))
                            {
                              if (iGold>=0 && iGold!=Tab.getI("V"+iEigGruppe))
                                iZeilen=addZeile(iZeilen,Tab,VecBez,VecSpan,iGold);
                                //g.fixInfo("Neue Zeile für Gruppe "+g.getStamm(iGold));
                              iGold=Tab.getI("V"+iEigGruppe);
                            }
                            iZeilen=addZeile(iZeilen,Tab,VecBez,VecSpan,-1);
                            /*boolean bG=sLoadKSt!=null && bKSt;
                            String sBez2=AbfSonst==null ? Tab.getS("Bezeichnung"):AbfSonst.TabToString(Tab);
                            String sBez=(bG && AbfSonst==null && !bMZ ? Tab.getS(sLoadKSt)+(bMZ?"\n":" "):"")+sBez2;
				VecBez.addElement(bMZ&&!bG?Static.cutString(sBez):sBez);
                                g.fixInfo("Zeile für "+sBez);
				CboStamm.addItem2(sBez2,Tab.getI("aic_stamm"),"");
				VecSpan.addElement(new JCCellRange(i,0,i,iAnz-1));
				JPanel Pnl = new JPanel(null);
                                Pnl.setBackground(g.ColPlanung);
				table.setCell(i,0,Pnl);
                                int iStammMom=Tab.getI("aic_stamm");
				if(iStamm==iStammMom)
					table.setSelectedCells(new JCCellRange(i,-1,i,0));
                                table.setForeground(i,-1,gesperrt(iStammMom,false) ? g.ColSperre : ShowAbfrage.istDa(g,iStammMom) ? g.ColBezeichnung:g.ColAustritt);
				table.setBackground(i,-1,sLoadFarbe != null && !Tab.isNull(sLoadFarbe) ? new Color(Tab.getI(sLoadFarbe)):g.ColPlanung);
                                table.setAlignment(i,-1,JCTblEnum.MIDDLELEFT);
                                if (table.getPixelHeight(i) != iH)
                                  table.setPixelHeight(i,iH);
                                TabPanel.addInhalt("AIC_Stamm",Tab.getI("AIC_Stamm"));
                                TabPanel.addInhalt("Bezeichnung",sBez);
				TabPanel.addInhalt("Panel",Pnl);
                                TabPanel.addInhalt("Art",null);
				i++;*/
                          }
                        if (ZS())
                          iZeilen=addZeile(iZeilen,Tab,VecBez,VecSpan,iGold);
                          //g.fixInfo("Neue Zeile für Gruppe "+g.getStamm(iGold));
                        try
                        {
                          CboStamm.Sort();
                        }
                        catch(Exception e)
                        {
                          Static.printError("Planung.Refresh: CboStamm.Sort nicht möglich");
                        }
                        //iZeilen=i;
                        if (/*AZ() ||*/ bSum)
                          VecBez.addElement(g.getBegriffS("Show", "Summe"));
                        //if (AZ())
                        //  table.setPixelHeight(iZeilen,18*g.getFontFaktor()/100+4);
		}
                if ((iBits&cstAuswahl)>0 && iMode>_INFO)
                  setSperre();
             g.clockInfo2("Refresh-Zeilen2",lClock);
                table.setNumRows(VecSpan.size()+(/*AZ() ||*/ bSum ? 1:0));
		/*if (AZ())
                {
                  table.setFont(-1,iAnz,g.fontTitelzeile);
                  table.setFont(-1,iAnz+1,g.fontTitelzeile);
                  table.setFont(-1,iAnz+2,g.fontTitelzeile);
                  int iBreite=65*g.getFontFaktor()/100;
                  table.setPixelWidth(iAnz,iBreite);
                  table.setPixelWidth(iAnz+1,iBreite);
                  table.setPixelWidth(iAnz+2,iBreite);
                  table.setForeground(new JCCellRange(0,iAnz,VecSpan.size(),iAnz),ColDefault);
                  table.setAlignment(new JCCellRange(0,iAnz,VecSpan.size()-1,iAnz+2),JCTblEnum.MIDDLERIGHT);
                  table.setFont(new JCCellRange(0,iAnz,VecSpan.size()-1,iAnz+2),g.fontStandard);
                  JCCellRange CRsum=new JCCellRange(VecSpan.size(),0,VecSpan.size(),iAnz+2);
                  table.setAlignment(CRsum,JCTblEnum.TOPRIGHT);
                  table.setFont(CRsum,g.fontTitel);
                  table.setForeground(CRsum,ColDefault);
                  table.setFont(VecSpan.size(),-1,g.fontTitel);
                }*/
                if (Schicht())
                {
                  int iBreite=55*g.getFontFaktor()/100;
                  for (int i=0;i<iP;i++)
                  {
                    table.setFont( -1, iAnz+i,g.getTabFontTZ());// g.fontTitelzeile);
                    table.setPixelWidth(iAnz+i, iBreite);//*(iPosO>0 && iPosO==iAnz+i ? 2 /*2..breiter Saldo*/:1));
                    table.setForeground(new JCCellRange(0, iAnz+i, VecSpan.size(), iAnz+i), ColDefault);
                    table.setBackground( -1, iAnz+i,Global.ColPlanung);
                    table.setAlignment(new JCCellRange(0, iAnz+i, VecSpan.size() - 1, iAnz+i), JCTblEnum.MIDDLERIGHT);
                    table.setFont(new JCCellRange(0, iAnz+i, VecSpan.size() - 1, iAnz+i), g.getTabFont());//g.fontStandard/*fontSumme*/);
                  }
                }
                if (Menge())
                {
                  table.setRowLabels(VecBez);
                  //table.setFont(new JCCellRange(0,-1,iZeilen,-1),g.fontTitelzeile); //weg 8.10.2014 Schrift inaktiv
                  if (/*AZ() ||*/ bSum)
                  {
                    table.setFont(iZeilen,-1,g.getTabFontSum());//g.fontTitel);
                    table.setPixelHeight(iZeilen, 30); 
                  }
                }
		table.setSpans(VecSpan);
		//g.progInfo("TSZeitraum:"+TSZeitraumVon+"-"+TSZeitraumBis);
		dFaktor = (double)iSpaltenbreite*iFaktor*Fom.iFF/100*(double)iAnz/(TSZeitraumBis.getTime()-TSZeitraumVon.getTime());
		//PntPanel=null;
		//g.testInfo(dFaktor+":"+table.getColumnPixelWidth(0));
		g.clockInfo2("Refresh-"+iZeilen+" Zeilen",lClock);
                addLinien();
                g.clockInfo2("Refresh-Linien",lClock);
                addFeiertage();
                g.clockInfo2("Refresh-Feiert",lClock);
                if (!sZeitartOld.equals(sZeitart) || !TSZeitraumVonOld.equals(g.getVon()) || !TSZeitraumBisOld.equals(g.getBis()))
                {
                  g.progInfo("Zeitraum zurückstellen:"+g.getVon()+"->"+TSZeitraumVonOld);
                  g.setZA(0,sZeitartOld);
                  g.setVonBis(TSZeitraumVonOld,TSZeitraumBisOld);
                  Zeitraum.PeriodeToVec(g, sZeitart, false);
                }
		if(VecSpan.size()>0)
                {
                  //if (AZ())
                  //  LoadAnwesend();
                  Load(lClock);
                  bReady=(AbfDaten.iBits&(Abfrage.cstKeinNeu+Abfrage.cstKeinSave))==0;
                }
                if (popup==null)// && g.Def())
                  addEvent(null);
		g.clockInfo2("Refresh-Werte ",lClock);

		Fom.thisFrame.setCursor(Cur);
		g.clockInfo2("Refresh- ENDE ",lClock);
	}
        setVisible(true);
        posZeile(iAicStamm);
        bSaveMom=false;
        g.clockInfo("Refresh "+sBez,lClock1);
}//Refresh

private Timestamp checkTage(boolean bAb)
{
  Timestamp TS=bAb ? g.getVon():g.getBis();
  /*if (AZ())
  {
    if (TabFilter.isEmpty())
    {
      if (bAb)
        return TS;
      else
      {
        g.setBis(g.VecPerioden.elementAt(1));
        Zeitraum.PeriodeToVec(g, g.sZeitart, false);
        return g.getBis();
      }
    }
    //boolean bWeiter=true;
    int iAnz=g.VecPerioden.size()-1;
    for(int i=0;i<iAnz;i++)
    {
      if (TabFilter.posInhalt("E" + iLoadFilterGueltig,g.VecPerioden.elementAt(bAb ? i:iAnz-i-1)) && !TabFilter.isNull("V"+iLoadFilterOffen1))
      {
        if (bAb)
        {
          g.setVon(g.VecPerioden.elementAt(i));
          return g.getVon();
        }
        else
        {
          g.setBis(g.VecPerioden.elementAt(iAnz - i));
          Zeitraum.PeriodeToVec(g, g.sZeitart, false);
          return g.getBis();
        }
      }
    }
  }*/
  return TS;
}

@SuppressWarnings("unchecked")
private void makeSum(int iStamm)
{
  /*if (AZ())
  {
    //long lClock = Static.get_ms();
    // Soll/Ist-Stunden
    double dSumSoll = 0;
    double dSumIst = 0;
    double dSumSaldo = 0;
    int i = 0;
    for(TabPanel.moveFirst(); !TabPanel.eof(); TabPanel.moveNext()) {
      iStamm = TabPanel.getI("AIC_Stamm");
      double dDauer = getDauer(iStamm, TSZeitraumVon, TSZeitraumBis.getTime(),0);
      double dSaldo=TabFilter.posInhalt("V"+iLoadFilterStamm,iStamm)?TabFilter.getF("E"+iLoadFilterSaldo):0;
      dSumSoll += dDauer;
      //int i=TabPanel.getPos();
      table.setCell(i, iAnz, new Mass(VecStunde, new Double(dDauer), "0.00"));
      double dPerson = 0;
      //double dPerson2 = 0;
      for(TabButton.moveFirst(); !TabButton.eof(); TabButton.moveNext())
        if(iStamm == TabButton.getI("Stamm") && TabZeitart.posInhalt("aic_stamm",TabButton.getI("Zeitart")) && TabZeitart.getB("pay"))
          //if(TabZeitart.getB("ctrl"))
            dPerson += TabButton.getF("Dauer_Neu");
          //else
          //  dPerson2 += TabButton.getF("Dauer_Neu");
      dSumIst += dPerson;
      table.setCell(i, iAnz + 1, new Mass(VecStunde, new Double(dPerson), "0.00"));
      table.setForeground(i, iAnz + 1,dDauer>dPerson ? ColMinus:dDauer<dPerson ? ColPlus:ColDefault);
      dSaldo+=dPerson - dDauer;
      table.setCell(i, iAnz + 2, new Mass(VecStunde, new Double(dSaldo), "0.00"));
      table.setForeground(i, iAnz + 2,dSaldo>0 ? ColPlus:dSaldo<0 ? ColMinus:ColDefault);
      i++;
    }
    table.setCell(i, iAnz, new Mass(VecStunde, new Double(dSumSoll), "0.00"));
    table.setCell(i, iAnz + 1, new Mass(VecStunde, new Double(dSumIst), "0.00"));
    table.setForeground(i, iAnz + 1,dSumSoll>dSumIst ? ColMinus:dSumSoll<dSumIst ? ColPlus:ColDefault);
    dSumSaldo+=dSumIst-dSumSoll;
    table.setCell(i, iAnz + 2, new Mass(VecStunde, new Double(dSumSaldo), "0.00"));
    table.setForeground(i, iAnz + 2,dSumSaldo>0 ? ColPlus:dSumSaldo<0 ? ColMinus:ColDefault);
  //}
    // Tages-Arbeitszeiten
  //if (AZ())// || bSum)
  //{
    i = 0;
    boolean bNeu=VecAZ==null;
      if (bNeu)
        VecAZ=new Vector<JLabel>();

    for(DateWOD DW = new DateWOD(TSZeitraumVon); DW.getTimeInMilli() < TSZeitraumBis.getTime(); DW.tomorrow()) {
      //Tab.clearAll();
      Tabellenspeicher Tab = new Tabellenspeicher(g, new String[] {"Datum", "Anzahl", "Saldo"});
      double dTag = 0;
      int iVor=0;

      for(int iO=0;iO<2;iO++)
      {
        int iOffen=iO==0 ? iLoadFilterOffen1:iLoadFilterOffen2;
        if(TabFilter.posInhalt("E" + iLoadFilterGueltig, DW.toTimestamp()) && !TabFilter.isNull("V" + iOffen))
        {
          double dVonD = DW.getAbsSeconds() + TabFilter.getF("V" + iOffen);
          double dBisD = DW.getAbsSeconds() + TabFilter.getF("B" + iOffen);
          checkTab(Tab, new Timestamp((long)dVonD * 1000), iVor);
          for(TabButton.moveFirst(); !TabButton.eof(); TabButton.moveNext())
            if(TabZeitart.posInhalt("aic_stamm", TabButton.getI("Zeitart")) && TabZeitart.getB("use"))
            {
              double dVonB = TabButton.getF("Von_Neu");
              double dBisB = TabButton.getF("Bis_Neu");

              double dTagMom = 0;
              if(dVonB >= dVonD && dBisB < dBisD)
                dTagMom = TabButton.getF("Dauer_Neu");
              else if(dBisB > dVonD && dVonB < dBisD)
                dTagMom = getDauer(TabButton.getI("Stamm"), new Timestamp((long)Math.max(dVonD, dVonB) * 1000), (long)Math.min(dBisD, dBisB) * 1000,
                                   TabButton.getI("Zeitart"));
              dTag += dTagMom;
              if(dTagMom > 0) {
                checkTab(Tab, new Timestamp((long)Math.max(dVonD, dVonB) * 1000), 1);
                if(dBisB < dBisD)
                  checkTab(Tab, new Timestamp((long)dBisB * 1000), -1);
              }
            }
          Tab.sort("Datum", true);
          Tab.Saldo("Anzahl", "Saldo", 0);
          Tab.moveLast();
          iVor=-Tab.getI("Saldo");
        }
      }
      //Tab.sort("Datum", true);
      //Tab.Saldo("Anzahl", "Saldo", 0);
      //Tab.showGrid(""+DW);
      //table.setCell(table.getNumRows() - 2, i, new Mass(VecStunde, new Double(dTag), "0.00"));
      if (bNeu)
      {
        JPanel Pnl = new JPanel(new GridLayout(1, 0));
        Pnl.setBackground(g.ColPlanung);
        table.setCell(table.getNumRows() - 1, i, Pnl);
        VecAZ.addElement(new JLabel(""));
        VecAZ.addElement(new JLabel("",JLabel.RIGHT));
        Pnl.add(VecAZ.elementAt(i*2));
        Pnl.add(VecAZ.elementAt(i*2+1));
      }
      //Pnl.add(new JLabel((int)Tab.min("Saldo") + " - " + (int)Tab.max("Saldo")));
      //Pnl.add(new JLabel(""+new Mass(VecStunde, new Double(dTag), "0.00"),JLabel.RIGHT));
      VecAZ.elementAt(i*2).setText((int)Tab.min("Saldo") + " - " + (int)Tab.max("Saldo"));
      VecAZ.elementAt(i*2+1).setText(""+new Mass(VecStunde, new Double(dTag), "0.00"));
      //(int)Tab.min("Saldo") + " - " + (int)Tab.max("Saldo")+"             "+new Mass(VecStunde, new Double(dTag), "0.00"));
      i++;
    }
    g.clockInfo2("makeSum", lClock);
  }*/
  if (Schicht())
  {
    //long lClock2=Static.get_ms();
    TabButton.push();
    TabPanel.push();
    //TabFilter.moveFirst();
    //g.progInfo(TabFilter.sGruppe+"/"+TabFilter.sAIC+":"+Static.print(TabFilter.getInhalt(TabFilter.sGruppe))+"/"+Static.print(TabFilter.getInhalt(TabFilter.sAIC)));
    //long lClock3=Static.get_ms();
    //Runtime rt=Runtime.getRuntime();
    if (bSum)
    {
      boolean bNeu=VecAZ==null;
      if (bNeu)
        VecAZ=new Vector<JLabel>();
      Tabellenspeicher Tab = new Tabellenspeicher(g, new String[] {"Datum","Stamm","Anzahl","Saldo","open","core","close"});
      Tab.sGruppe="Datum";
      Tab.sAIC="Stamm";
      for (TabButton.moveFirst();!TabButton.out();TabButton.moveNext())
      {
        if (TabButton.getF("Dauer_Neu")>0)
        {
          DateWOD DW = Sort.getDW(TabButton.getInhalt("Von_Neu"));
          DW.setTimeZero();
          int iStammMom=TabButton.getI("Stamm");
          if(!TabFilter.posInhalt(new Integer(iStammMom)/*TabButton.getInhalt("Stamm")*/,DW.toTimestamp()))
          {
            checkTab(Tab, DW.toTimestamp(),iStammMom, 1);
            int iPos=TabZeitart.getPos("aic_stamm",TabButton.getI("Zeitart"));
            Tab.setInhalt("Saldo", Pause(TabButton.getF("Dauer_Neu"),iPos) + Tab.getF("Saldo"));
            //TabZeitart.posInhalt("aic_stamm",TabButton.getI("Zeitart"));
            if (TabZeitart.getB(iPos,"TM_open"))
              Tab.addI("open",1);
            if (TabZeitart.getB(iPos,"TM_core"))
              Tab.addI("core",1);
            if (TabZeitart.getB(iPos,"TM_close"))
              Tab.addI("close",1);
          }
        }
      }
      //Tab.showGrid("Tab");
      int i = 0;
      for(DateWOD DW = new DateWOD(TSZeitraumVon); DW.getTimeInMilli() < TSZeitraumBis.getTime(); DW.tomorrow())
      {
        if (bNeu)
        {
          /*JPanel Pnl = new JPanel(new BorderLayout());
          Pnl.setBackground(g.ColPlanung);
          //Pnl.setFont(g.fontTitel);
          JLabel Lbl1=new JLabel("");
          table.setCell(table.getNumRows() - 1, i, Pnl);
          JLabel Lbl2=new JLabel("", JLabel.RIGHT);
          Lbl1.setFont(g.fontSumme2);
          Lbl2.setFont(g.fontSumme2);
          Lbl1.setForeground(g.ColSumme2);
          Lbl2.setForeground(g.ColSumme2);
          VecAZ.addElement(Lbl1);
          VecAZ.addElement(Lbl2);
          Pnl.add("West",VecAZ.elementAt(i * 2));
          Pnl.add("East",VecAZ.elementAt(i * 2 + 1));*/
          addPnlSum(0,i,VecAZ);
          if (ZS())
            for (int i2=1;i2<TabPanel.size();i2++)
              if (!TabPanel.isNull(i2,"Vec"))
                addPnlSum(i2,i,(Vector)TabPanel.getInhalt("VecAZ",i2));
        }
        if (Tab.posInhalt("Datum",DW.toTimestamp()))
        {
          if (ZS())
            for (int i2=1;i2<TabPanel.size();i2++)
              if (!TabPanel.isNull(i2,"Vec"))
              {
                Vector<JLabel> VecMom=(Vector)TabPanel.getInhalt("VecAZ",i2);
                int iOpen=0;
                int iCore=0;
                int iClose=0;
                int iAnzahl=0;
                double d=0;
                for (int i3=0;i3<Tab.size();i3++)
                  if (Tab.getInhalt("Datum",i3).equals(DW.toTimestamp()) && ((Vector)TabPanel.getInhalt("Vec",i2)).contains(Tab.getI(i3,"Stamm")))
                  {
                    iOpen+=Tab.getI(i3,"open");
                    iCore+=Tab.getI(i3,"core");
                    iClose+=Tab.getI(i3,"close");
                    iAnzahl+=Tab.getI(i3,"Anzahl");
                    d+=Tab.getF(i3,"Saldo");
                  }
                VecMom.elementAt(i * 2).setText(d==0 ? "":bTMZ ? iOpen+" / "+iCore+" / "+iClose:""+iAnzahl);
                VecMom.elementAt(i * 2 + 1).setText(!bESum || d==0 ? "":"" + new Mass(g,VecStunde, d, "0.00"));
              }
          VecAZ.elementAt(i * 2).setText(bTMZ ? (int)Tab.sumGroup("open",false)+" / "+(int)Tab.sumGroup("core",false)+" / "+(int)Tab.sumGroup("close",false):""+(int)Tab.sumGroup("Anzahl",false));
          VecAZ.elementAt(i * 2 + 1).setText(!bESum ? "":"" + new Mass(g,VecStunde, Tab.sumGroup("Saldo",false), "0.00"));
        }
        i++;
      }
    }
    if (iStamm>0)
    {
      TabPanel.posInhalt("AIC_Stamm",iStamm);
      sumSchicht(iStamm,TabPanel.getPos());
      sumGruppe(-iStamm);
    }
    else
      for(TabPanel.moveFirst(); !TabPanel.eof(); TabPanel.moveNext())
        if (TabPanel.isNull("Vec"))
          sumSchicht(TabPanel.getI("AIC_Stamm"),TabPanel.getPos());
        else
          sumGruppe(TabPanel.getPos());
    if (bSum)
    {
      sumGruppe(0);
      if (iStamm==0)
        for (int i2 = 1; i2 < table.getNumColumns(); i2++)
        {
          table.setFont(TabPanel.size(), i2, g.getTabFontSum());//g.fontSumme);
          table.setAlignment(TabPanel.size(), i2,JCTblEnum.MIDDLERIGHT);
        }
      /*if (iPosA > 0) table.setCell(table.getNumRows() - 1, iPosA, "");
      if (iPosP > 0) table.setCell(table.getNumRows() - 1, iPosP, "");
      if (iPosI > 0) table.setCell(table.getNumRows() - 1, iPosI, "");
      if (iPosS > 0) table.setCell(table.getNumRows() - 1, iPosS, "");
      if (iPosD > 0) table.setCell(table.getNumRows() - 1, iPosD, "");
      if (iPosO > 0) table.setCell(table.getNumRows() - 1, iPosO, "");*/
    }
    TabPanel.pop();
    TabButton.pop();
    //g.clockInfo("makeSum", lClock2);
  }
  table.repaint();
}

private void addPnlSum(int iRow,int iCol,Vector<JLabel> VecAZ)
{
  JPanel Pnl = new JPanel(new BorderLayout());
  if (iRow>0) // Zwischensumme
    Pnl.setBackground(table.getBackground(iRow,-1));
  else // Gesamtsumme
    Pnl.setBackground(Global.ColPlanung);
  //Pnl.setFont(g.fontTitel);
  JLabel Lbl1=new JLabel("");
  table.setCell(iRow==0 ? table.getNumRows() - 1:iRow, iCol, Pnl);
  JLabel Lbl2=new JLabel("", JLabel.RIGHT);
  Lbl1.setFont(Global.fontSumme2);
  Lbl2.setFont(Global.fontSumme2);
  Lbl1.setForeground(Global.ColSumme2);
  Lbl2.setForeground(Global.ColSumme2);
  VecAZ.addElement(Lbl1);
  VecAZ.addElement(Lbl2);
  Pnl.add("West",VecAZ.elementAt(iCol * 2));
  Pnl.add("East",VecAZ.elementAt(iCol * 2 + 1));
}

public void paint(Graphics rg){
  boolean b=false;
  if (!bPaint)
  {
    super.paint(rg);
    bPaint = true;
    if(AbfDaten != null && (AbfDaten.iBits & Abfrage.cstSynchron) > 0) {
//      int iPos = g.TabStammtypen.getPos("Aic", AbfDaten.iStt);
//      int iStammNeu=g.TabStammtypen.getI(iPos, "Stamm");
    	int iStammNeu=g.getSyncStamm(AbfDaten.iStt, AbfDaten.iRolle);
      b=iStammNeu > 0 && iStammNeu!=iAicStamm;
      if (b)
      {
        g.progInfo("paint: "+iStammNeu+" statt "+iAicStamm);
        iAicStamm=iStammNeu;
        posZeile(iStammNeu);
      }
    }
    bPaint = false;
  }
  if (b)
    Fom.thisFrame.repaint();
}

private void sumGruppe(int i)
{
  //g.fixtestInfo("sumGruppe:"+i);
  if (i<0)
    for(int i2=0;i2<TabPanel.size() && i<0;i2++)
      if (!TabPanel.isNull(i2,"Vec") && ((Vector)TabPanel.getInhalt("Vec", i2)).contains(-i))
        i = i2;
  if (i<0)
    return;
  int iZ=i==0 ? TabPanel.size():((Vector)TabPanel.getInhalt("Vec",i)).size();
  for (int i2 = 1; i2 < table.getNumColumns(); i2++)
  {
    double d=Sort.getf(table.getCell(i==0 ? 0:i-iZ,i2));
    for (int i3=1;i3<iZ;i3++)
      if (i>0 || TabPanel.isNull(i3,"Vec"))
        d+=Sort.getf(table.getCell(i==0 ? i3:i-i3,i2));
    table.setCell(i==0 ? iZ:i, i2, checkedMass(d));
  }
}

private Mass checkedMass(double d)
{
  return new Mass(g,VecStunde, new Double(d), d>3599999 || d<-719999 ? "0":"0.00");
}

private void sumSchicht(int iStamm,int i)
{
  if (iPosA>0)
    table.setCell(i, iPosA, new Mass(g,VecStunde, new Double(sumIst(iStamm,false,true)), "0.00")); // Arbeitszeit
  double dIst=0;
  double dSoll=0;
  double dSaldo=0;
  if (iPosI>0 || iPosD>0 || iPosO>0)
    dIst=sumIst(iStamm,false,false);
  if (iPosS>0 || iPosD>0 || iPosO>0)
  {
    dSoll=AbfSonst!=null && TabSonst.posInhalt("aic_stamm",iStamm) && iLoadSoll>0 && !TabSonst.isNull("E"+iLoadSoll) ? TabSonst.getF("E"+iLoadSoll): 38.5;
    dSoll=dSoll*3600*iTage/7;
    if (iLoadSaldo>0)
      dSaldo=TabSonst.getF("E"+iLoadSaldo)*3600;
  }
  if (iPosP>0)
    table.setCell(i, iPosP, checkedMass(sumIst(iStamm,true,false))); // Planzeit
  if (iPosI>0)
    table.setCell(i, iPosI, checkedMass(dIst));		// Ist-Zeit
  if (iPosS>0)
    table.setCell(i, iPosS, checkedMass(dSoll));	// Soll-Zeit
  if (iPosD>0)
    table.setCell(i, iPosD, checkedMass(dIst-dSoll));
  if (iPosO>0)
    table.setCell(i, iPosO, checkedMass(dSaldo + dIst - dSoll));
  //i++;
}

public double Pause(double d,int iPos)
{
  int iPause=iPos<0 || TabZeitart.isNull(iPos,"TM_Pause") ? 1800:(int)(TabZeitart.getF(iPos,"TM_Pause")*60);
  if (iPause<300)
    iPause*=60;
  int iAb=iPos<0 || TabZeitart.isNull(iPos,"TM_ab") ? 6*3600:(int)(TabZeitart.getF(iPos,"TM_ab")*3600);
  //g.fixInfo("Pause:"+iPause+" ab "+iAb);
  if (bPause && d>iAb+29)
    d-=iPause;
  return d;
}

 private double sumAZ(int iStamm,DateWOD DW)
{
  double d=0;
  double dVon=new DateWOD(g.getVon()).getAbsSeconds();
  for(TabButton.moveFirst(); !TabButton.eof(); TabButton.moveNext())
  {
    if (TabButton.getI("Stamm") == iStamm)
    {
      double dN=TabButton.getF("von_neu");
      if(dN>=dVon)
    //g.testInfo("sumAZ "+iStamm+"/"+DW+":"+TabButton.getTimestamp("von_neu")+"="+dN+"/"+g.getVon()+"="+dVon);
        if (DW == null || DW.compareDay('=',new DateWOD((long)dN*1000)))
        {
          int iPos=TabZeitart.getPos("aic_stamm",TabButton.getI("Zeitart"));
          double d2 = Pause(TabButton.getF("bis_neu") - dN,iPos);//TabButton.getF("von_neu");
          //if(bPause && d2 > 6 * 3600)
          //  d2 -= 1800;
          //if (DW != null || !bProd || TabZeitart.getB(iPos,"ctrl"))
          //{
            d += d2;
            if (!TabButton.isNull("von2_neu"))
              d += Pause(TabButton.getF("bis2_neu") - TabButton.getF("von2_neu"), iPos);
            if (DW != null)
              return d;
          //}
        }
    }
  }
//  if (d==0)
//  {
//	  int iPosF=TabFilter.getPos3(new Integer(iStamm),DW.toTimestamp());
//	  int iZA=iPosF<0 ? 0:TabFilter.getI(iPosF,"V" + iLoadFilterZeitart);
//	  int iPos2=iZA>0 ? TabZeitart2.getPos("aic_stamm",iZA):-1;
//	  if (iPos2>=0 && TabZeitart2.getB(iPos2,"prod"))
//	  {
//		  d = TabFilter.getF(iPosF,"D" + iLoadFilterIstZeit);
//		  g.fixtestError(TabZeitart2.getS(iPos2,"Bezeichnung")+" ist produktiv -> "+d);
//	  }
//  }
  return d;
} 

private double sumIst(int iStamm,boolean bCtrl,boolean bProd)
{
  double d=0;
  long lBis=(long)new DateWOD(g.getBis()).getAbsSeconds()*1000;//TSZeitraumBis.getTime();
  DateWOD DW=new DateWOD(TSZeitraumVon);
  do
  {
    DW.setTimeZero();
    double d0=sumAZ(iStamm,DW);
    //if (posButton(iStamm,DW.setTimeZero().getAbsSeconds()) && TabZeitart.posInhalt("aic_stamm",TabButton.getI("Zeitart")))
//    int iPosF=TabFilter.posInhalt(new Integer(iStamm),DW.toTimestamp()) ? TabFilter.getPos():-1;
    int iPosF=TabFilter.getPos3(new Integer(iStamm),DW.toTimestamp());
    int iZA=iPosF<0 ? 0:TabFilter.getI(iPosF,"V" + iLoadFilterZeitart);
    int iPos2=iZA>0 ? TabZeitart2.getPos("aic_stamm",iZA):-1;
    //g.fixtestInfo("sumIst "+iStamm+"/"+DW+"->"+iPosF+"/"+iZA+"/"+iPos2+(iPos2<0 ? "":TabZeitart2.getS(iPos2,"Bezeichnung")));
    if (d0>0 && (iPos2<0 || TabZeitart2.getB(iPos2,"SPlan")))
      d+=d0;//TabZeitart.getF("soll")*3600;
    else if (iPos2>=0 && TabZeitart2.getB(iPos2,"pay") && (!bProd && !bCtrl || bCtrl && TabZeitart2.getB(iPos2,"ctrl") || bProd && TabZeitart2.getB(iPos2,"prod")))
      d += TabFilter.getF(iPosF,"D" + iLoadFilterIstZeit);
    if (iPos2>0)
    	g.fixtestInfo("sumIst "+iStamm+"/"+DW+":"+TabZeitart2.getS(iPos2,"Bezeichnung")+"/"+TabFilter.getF(iPosF,"D" + iLoadFilterIstZeit)+"/"+d);
//    else if(TabFilter.posInhalt(new Integer(iStamm),DW.toTimestamp()))
//    {
//      if(TabZeitart2.posInhalt("aic_stamm",TabFilter.getI("V" + iLoadFilterZeitart)) && TabZeitart2.getB("pay") && (!bProd || TabZeitart2.getB("ctrl")))
//        d += TabFilter.getF("D" + iLoadFilterIstZeit);
//    }
    //else if(TabFilter.posInhalt(new Long(iStamm),DW.toTimestamp()))
    //  d+=TabFilter.getF("D" + iLoadFilterIstZeit);
    DW.tomorrow();
  }
  while(DW.getTimeInMilli()<lBis);
  return d;
}

/*private boolean posButton(int iStamm,double dTime)
{
  //g.progInfo("posButton:"+iStamm+"/"+dTime);
  for (TabButton.moveFirst();!TabButton.eof();TabButton.moveNext())
  {
    //g.progInfo("posButton:"+iStamm+"/"+dTime);
    if (TabButton.getI("Stamm") == iStamm && !TabButton.isNull("Von_Neu") && TabButton.getF("Von_Neu") <= dTime && TabButton.getF("Bis_Neu") > dTime)
      return true;
  }
  return false;
}*/

private void checkTab(Tabellenspeicher Tab,Timestamp ts,int iStamm,int i)
{
  if (Tab.posInhalt(ts,iStamm))
    Tab.addI("Anzahl",i);
  else
  {
    Tab.newLine();
    Tab.setInhalt("Datum",ts);
    Tab.setInhalt("Stamm",iStamm);
    Tab.setInhalt("Anzahl",i);
  }
}

private void addComponent(JPanel Pnl,JComponent Com,int iStamm,int iZeitart,int iFarbe,Timestamp TSVon,Timestamp TSBis,
                          Timestamp TSVon1,Timestamp TSBis1,Timestamp TSVon2,Timestamp TSBis2,
                          double dDauer,int iStatus,int iStatusSV /*boolean bBewilligt*/,Vector VecAIC,int iBewBew,boolean bOben)
{
	//boolean bNeu=false;
	if(Com==null||Com instanceof JButton)
	{
		//g.testInfo("iEig: "+iStamm+" - iZeit: "+iZeitart+" - Von:"+TSVon+" - Bis:"+TSBis+" - Dauer:"+dDauer);
                //g.progInfo("nach:"+TSVon+"/"+TSBis+"//"+TSVon1+"/"+TSBis2);
                JButton Btn = (JButton)Com;
		if (!TabZeitart.posInhalt("aic_stamm",iZeitart))
		{
			TabZeitart.refresh(g);
                        if (!TabZeitart.posInhalt("aic_stamm",iZeitart))
                        {
                          g.fixInfo("Planung.addComponent: "+g.getStamm(iStamm)+", Zeitart "+g.getStamm(iZeitart)+"("+iZeitart+") auch refresh nicht gefunden!");
                          //Static.printError("Zeitart "+g.getStamm(iZeitart)+" wirklich nicht gefunden");
                          return;
                        }
		}
                //if (TSVon != null && TSVon.before(g.getVon()))
                //  TSVon=new Timestamp(g.getVon().getTime());
                if (TSVon1 == null)
                  if(/*bVoll ||*/ Schicht())
                  {
                    DateWOD dt = new DateWOD(TSVon);
                    double dVon = dt.getAbsSeconds();
                    dt.setAllSeconds(dVon + (VBcopy==null ? TabZeitart.getF("von1"):new DateWOD(VBcopy.getVon().getTime()).setDateZero().getAbsSeconds()));
                    dt.setTimezoneOffset();
                    TSVon1 = dt.toTimestamp();
                    dt.setAllSeconds(dVon + (VBcopy==null ? TabZeitart.getF("bis1"):new DateWOD(VBcopy.getBis().getTime()).setDateZero().getAbsSeconds()));
                    dt.setTimezoneOffset();
                    TSBis1 = dt.toTimestamp();
                    dDauer=(TSBis1.getTime()-TSVon1.getTime())/1000;
                    if(!TabZeitart.isNull("von2") || VB2copy != null)
                    {
                      dt.setAllSeconds(dVon + (VB2copy==null ?  TabZeitart.getF("von2"):new DateWOD(VB2copy.getVon().getTime()).setDateZero().getAbsSeconds()));
                      dt.setTimezoneOffset();
                      TSVon2 = dt.toTimestamp();
                      dt.setAllSeconds(dVon + (VB2copy==null ?  TabZeitart.getF("bis2"):new DateWOD(VB2copy.getBis().getTime()).setDateZero().getAbsSeconds()));
                      dt.setTimezoneOffset();
                      TSBis2 = dt.toTimestamp();
                      if (TSVon2 != null)
                        dDauer+=(TSBis2.getTime()-TSVon2.getTime())/1000;
                    }
                  }
                  else
                  {
                    TSVon1 = TSVon;
                    TSBis1 = TSBis;
                  }
                if (TSVon.equals(TSBis))
		{
			g.progInfo("von=bis bei "+iStamm+"/"+TSVon);
			return;
		}
                boolean bNormal;
                String sBtnText=bVB && !TSVon1.equals(TSBis1) ? " "+new Zeit(TSVon1,"HH:mm")+"-"+new Zeit(TSBis1, "HH:mm") + (TSBis2 == null?"":", "+ new Zeit(TSVon2, "HH:mm") + "-"+new Zeit(TSBis2,"HH:mm")):Static.bTranslate && !Schicht() ? TabZeitart.getS("Bezeichnung").substring(0,3):TabZeitart.getS("KZ");
                if(Btn!=null && TabButton.posInhalt("Button",Btn))
		{
			Pnl.remove(Btn);
			TabButton.setInhalt("Stamm",iStamm);
			TabButton.setInhalt("Von_Neu",TSVon1);
			TabButton.setInhalt("Bis_Neu",TSBis1);
                        TabButton.setInhalt("Von2_Neu",TSVon2);
			TabButton.setInhalt("Bis2_Neu",TSBis2);
			TabButton.setInhalt("Dauer_Neu",new Double(dDauer));
			TabButton.setInhalt("Zeitart",iZeitart);
                        TabButton.setInhalt("Farbe2",iFarbe);
			TabButton.setInhalt("Status",iStatus);
			TabButton.setInhalt("StatusSV",iStatusSV);
			//Btn.setText(CboZeitart.getComboBox().getSelectedBezeichnung());
			Btn.setText(sBtnText);
                        Btn.setFont(Global.fontButton);
                        bNormal=!TabButton.isNull("VecAIC") && TabButton.gleich("Stamm_Alt","Stamm") && TabButton.gleich("Zeitart","Zeitart_Alt") && TabButton.gleich("Farbe2","Farbe_Alt") &&
                            TabButton.gleich("Von_Neu","Von_Alt") && TabButton.gleich("Bis_Neu","Bis_Alt") && TabButton.gleich("Status","Status_Alt") && TabButton.gleich("StatusSV","StatusSV_Alt");
		}
		else
		{
			//Btn=new JButton(CboZeitart.getComboBox().getBezeichnung(iZeitart));
			Btn=new JButton(sBtnText);
                        Btn.setFont(Global.fontButton);
                        if (Schicht())
                        {
                          DateWOD DW = new DateWOD(TSVon);
                          DW.setTimeZero();
                          if(TabFilter.posInhalt(new Integer(iStamm),DW.toTimestamp())/* && TabFilter.getF("D" + iLoadFilterIstZeit)>0*/)
                          {
                            Btn.setFont(Global.fontDisabled);
                            Btn.setForeground(Global.ColDisabled);
                          }
                          else
                            Btn.setFont(Global.fontButton);
                        }
                        BtnAkt=Btn;
                        //Btn.setMargin(new Insets(0,0,0,0));
                        Btn.setBorder(BorderFactory.createLineBorder(Color.black));
			Btn.setHorizontalAlignment(SwingConstants.CENTER);//LEFT);

			if (iRest>0)
				for(TabSpaltenDaten.posInhalt("Nummer",iRest);!TabSpaltenDaten.eof();TabSpaltenDaten.moveNext())
				{
					String sDt=TabSpaltenDaten.getS("Datentyp");
					//if (!sDt.equals("SysAic"))
					{
						TabRest.addInhalt("Button",Btn);
						TabRest.addInhalt("Nr",TabSpaltenDaten.getInhalt("Nummer"));
						Object Obj=null;
						if (sDt.equals("BewStamm") || sDt.equals("BewBool3"))
							Obj=new Combo(TabDaten.getS("E"+TabSpaltenDaten.getS("Kennung2")),TabDaten.getI("V"+TabSpaltenDaten.getS("Kennung2")),true);
						else
							Obj=TabDaten.getInhalt(TabSpaltenDaten.getS("Kennung"));
						if (iLoadMemoNr==0 && sDt.equals("Text"))
							iLoadMemoNr=TabSpaltenDaten.getI("Nummer");
						else if (iLoadMemoNr>0 && iLoadMemo2Nr==0 && sDt.equals("Text"))
							iLoadMemo2Nr=TabSpaltenDaten.getI("Nummer");
						if (iLoadErsatzNr==0 && sDt.equals("BewStamm"))
							iLoadErsatzNr=TabSpaltenDaten.getI("Nummer");
								//TabDaten.getInhalt((sDt.equals("BewStamm") || sDt.equals("BewBool3")?"V":"E")+TabSpaltenDaten.getS("Kennung2"));
						TabRest.addInhalt("Neu",Obj);
						TabRest.addInhalt("Alt",Obj);
					}
				}
			//g.fixtestError("iLoadMemoNr="+iLoadMemoNr+", iLoadErsatzNr="+iLoadErsatzNr);

			//boolean bNeu=VecAIC==null;
                        //if (!AZ() || TSVon.before(TSNow) && !bOben || !TSVon.before(TSNow) && bOben)
                        {
                          TabButton.addInhalt("Button", Btn);
                          TabButton.addInhalt("Stamm", iStamm);
                          TabButton.addInhalt("Stamm_Alt", iStamm);
                          TabButton.addInhalt("Von_Alt", TSVon1);
                          TabButton.addInhalt("Bis_Alt", TSBis1);
                          TabButton.addInhalt("Von2_Alt", TSVon2);
                          TabButton.addInhalt("Bis2_Alt", TSBis2);
                          TabButton.addInhalt("Dauer_Alt", VecAIC == null && bOben ? null : new Double(dDauer));
                          TabButton.addInhalt("Von_Neu", TSVon1);
                          TabButton.addInhalt("Bis_Neu", TSBis1);
                          TabButton.addInhalt("Von2_Neu", TSVon2);
                          TabButton.addInhalt("Bis2_Neu", TSBis2);
                          TabButton.addInhalt("Dauer_Neu", new Double(dDauer));
                          TabButton.addInhalt("Zeitart", iZeitart);
                          TabButton.addInhalt("Zeitart_Alt", iZeitart);
                          TabButton.addInhalt("Farbe2", iFarbe);
                          TabButton.addInhalt("Farbe_Alt", iFarbe);
                          TabButton.addInhalt("Status", iStatus);
                          TabButton.addInhalt("Status_Alt", iStatus);
                          TabButton.addInhalt("StatusSV", iStatusSV);
                          TabButton.addInhalt("StatusSV_Alt", iStatusSV);
                          TabButton.addInhalt("VecAIC", bOben ? VecAIC:new Vector());
                          TabButton.addInhalt("BewBew",iBewBew);
                          if (TGM())
                        	  TabButton.posInhalt("Button", Btn);
                          if(bOben)
                          {
                            TabZeitart.push();
                            addEvent(Btn);
                            TabZeitart.pop();
                          }
                        }
                        bNormal = VecAIC != null;
		}
                //g.progInfo("iLoadBewilligt="+iLoadBewilligt+", Zeitart="+TabZeitart.getS("KZ")+"/"+TabZeitart.getB("bewilligt")+", ZR="+TSVon+"-"+TSBis);
                int iCol=iFarbe>0 ? iFarbe:iZeitart;
                Color Col=bNormal ? g.getColor(iCol,Global.ColKein) : g.getColor(iCol,Global.ColKein).brighter().brighter()/*.brighter()*/;
                boolean bVerschleiern= verschleiern(false);// && TabZeitart.getB("verschl");
                if (bVerschleiern)
                	;
                else if(iLoadBewilligt>0 && TabZeitart.getB("bewilligt"))
				{
		                  int iPos=g.getPosBegriff("Show",iStatus==1 ?"Ja":"Nein");
		                  if (iPos>=0)
		                    Btn.setIcon(g.LoadImageIcon(g.TabBegriffe.getS(iPos,"BildFile")));
				}
                else if(iLoadBewilligt3VG>0 && TabZeitart.getB("bewilligt"))
                {
                  BufferedImage img = new BufferedImage( 23, 23, BufferedImage.TYPE_INT_RGB );
                  Graphics gr = img.getGraphics();
                  gr.setColor( Col);
	              gr.fillRect( 0, 0, 23, 23 );
	              if (iLoadBewilligt3SV>0)
	              {
	            	   //int iStatus2=0;//(int)Math.random()*3;
	            	   /*int iPos=TabSpaltenDaten.getPos("Kennung2", ""+iLoadBewilligt3SV);
	            	   if (iPos>0)
	            	   {
	            		   int iNr=TabSpaltenDaten.getI(iPos,"Nummer");
	            		   //int iP=TabRest.getPos("Nr",iNr);
	            		   if (TabRest.posInhalt("Button", Btn))
	            		   {
		            		   if (TabRest.getI("Nr")!=iNr)
		            			   TabRest.posNextInhalt("Nr", iNr);
		            		   //int iAicS=TabRest.getI("Neu");
		            		   //iStatus2=g.getAuswahlNr(TabRest.getI("Neu"));
		            		   //g.fixtestInfo("Bewilligt3b: Nr="+iNr+", Status2="+iStatus2);
	            		   }
	            	   }*/
	            	   gr.setColor( iStatusSV==1 ? Color.GREEN : iStatusSV==2 ? Color.RED:Color.LIGHT_GRAY );
		               gr.fillOval( 0, 0, 21, 21 );
		               if (iStatusSV>0)
		               {
		            	   gr.setColor(Color.LIGHT_GRAY );
		            	   gr.fillOval( 3, 3, 15, 15 );
		               }
	              }	               
	              Image Img=g.getGif(g.TabAuswahl,EdtStatus.getAic(iStatus));
	              gr.drawImage(Img, 3, 3, 16, 16, new ImageIcon().getImageObserver());	                  
                  if (img!=null)
                    Btn.setIcon(new javax.swing.ImageIcon(img));
                }
                String sFormat=Schicht()?"HH:mm":sVBFormat;
                
		String sTT=TabZeitart.getBezeichnung(iZeitart)+//CboZeitart.getComboBox().getBezeichnung2(iZeitart)+
                 (bVB?"":": "+Tage(TSVon1,TSBis1,iStamm)+new VonBis(g,TSVon1,TSBis1,dDauer,sFormat,g.getFaktor(iVBStamm))
                  +(TSVon2 == null ? "":", "+new VonBis(g,TSVon2,TSBis2,dDauer,sFormat,g.getFaktor(iVBStamm))))
                  //+(Schicht() && iRest>0 && TabRest.posInhalt("Button",Btn) ? " "+TabRest.getM("Neu"):"")
                  +(iFarbe>0 && TabFarbe.posInhalt("aic_stamm",iFarbe) ? " / "+TabFarbe.getS("Bezeichnung"):"")
                  +(iLoadBewilligt>0 && TabZeitart.getB("bewilligt") && iStatus>0 ? " "+g.getBegriffS("Show",iStatus==1 ? "Bewilligt":"Abgelehnt"):"")
                  +(iLoadBewilligt3VG>0 && TabZeitart.getB("bewilligt") ? "\n"+getBez(iLoadBewilligt3VG)+": "+EdtStatus.getBez(iStatus):"")+(iLoadBewilligt3SV>0 && TabZeitart.getB("bewilligt") ? "\n"+getBez(iLoadBewilligt3SV)+": "+EdtStatusSV.getBez(iStatusSV):"");
                String sMemo=null;
                if (iRest > 0 && TabRest.posInhalt("Button", Btn))
                {
                  for(; !TabRest.out() && TabRest.getInhalt("Button") == Btn; TabRest.moveNext())
                    if(!Check.isNull(TabRest.getInhalt("Neu")) || !Check.isNull(TabRest.getInhalt("Alt")))
                    {
                      TabSpaltenDaten.posInhalt("Nummer", TabRest.getI("Nr"));
                      if ((TabSpaltenDaten.getI("bits")&Abfrage.cstUnsichtbar)==0)
                      {
                        String s=getS(TabSpaltenDaten.getS("Datentyp"), TabRest.getInhalt(TabRest.isNull("Neu") ? "Alt" : "Neu"));
                        //g.fixtestInfo(TabSpaltenDaten.getS("Bezeichnung")+":"+TabSpaltenDaten.getS("Datentyp")+"/"+s);
                        if (sMemo==null && TabSpaltenDaten.getS("Datentyp").equals("Text")) sMemo=s;
                        sTT += "\n" + TabSpaltenDaten.getS("Bezeichnung") + ": " + s;
                      }
                    }
                }
                //sMemo=sMemo==null ? sMemo=null:sMemo.replaceAll("\n", "<p>");
                //g.fixtestInfo(Btn.getText()+":"+sMemo);
                if (bMZ && sMemo!=null)
                {
                  String s="<html><CENTER>" + Btn.getText() + "</CENTER><p><FONT SIZE=2>" + sMemo.replaceAll("\n", "<br>") + "</html>";
                  g.fixtestInfo(s);
                  Btn.setText(s);
                }
                if (bVerschleiern)
                	Btn.setText("xx");
                else
                	Btn.setToolTipText(Global.toHtml(sTT));
                
                  //Btn.setText("<html>"+"<PRE WIDTH="+(iSpaltenbreite-5)+">"+"<CENTER><b>"+Btn.getText()+"</b></CENTER><p>"+(sMemo!=null?"<small>"+sMemo+"</small>":"")+"</html>");
		//Btn.setBackground(bNeu?new Color(TabZeitart.getI("Farbe")).brighter():new Color(TabZeitart.getI("Farbe")));
		//boolean bNormal=Com==null && VecAIC!=null;
                //((JButton)Btn).setAlignmentY(BOTTOM_ALIGNMENT);
		//g.progInfo(iStamm+":"+TSVon+"-"+TSBis+"="+(bNormal?"Normal":"Heller"));
                
                //g.fixInfo(bNormal+", iCol="+iCol+"->"+g.getColor(iCol,g.ColKein)+", Btn="+Btn);
		g.setBack(Btn,bVerschleiern ? Color.LIGHT_GRAY:Col);
		Com=Btn;
	}
	else
	{
//		g.fixtestError(((JLabel)Com).getText()+" ausgeben bei "+TSVon);
                Com.setOpaque(true);
                if (Schicht())
                  Com.setFont(Transact.fontStandard);
                if (Schicht() && TabZeitart2.posInhalt("aic_stamm",iZeitart))
                {
//                	g.fixtestError("Schicht mit ZA="+iZeitart);
                  ((JLabel)Com).setText(Static.bTranslate ? TabZeitart2.getS("Bezeichnung").substring(0,3):TabZeitart2.getS("KZ"));
                  ((JLabel)Com).setHorizontalAlignment(JLabel.CENTER);
                  Com.setBackground(new Color(TabZeitart2.getI("Farbe")));
                }
		else if (iZeitart>0 && TabZeitart.posInhalt("aic_stamm",iZeitart))
		{
			((JLabel)Com).setText(Static.bTranslate ? TabZeitart.getS("Bezeichnung").substring(0,3):TabZeitart.getS("KZ"));
//			g.fixtestError("auf "+((JLabel)Com).getText()+" umbenannt bei ZA="+iZeitart);
			((JLabel)Com).setHorizontalAlignment(JLabel.CENTER);
			Com.setBackground(g.getColor(iZeitart,Global.ColKein));
		}
		else
			Com.setBackground(Stunden() && iStatus==1 ? Color.black:Global.ColHoliday);
                //g.progInfo("Linie:"+TSVon.getHours());
	}
        long lVon=TSVon.getTime();
        long lBis=iZeitart==-1?0:TSBis.getTime();
        if (!bVoll && TSVon1 != null)
        {
          lVon=TSVon1.getTime();
          lBis=TSBis2 == null ? TSBis1.getTime():TSBis2.getTime();
        }
        int iH=/*Com instanceof JButton && AZ() && TSVon.before(TSNow) ? 15:*/iZeilenhoehe>20?iZeilenhoehe-3:bMZ?37:/*AZ()?30:*/20;
	Com.setBounds((int)((lVon-TSZeitraumVon.getTime())*dFaktor)+(iZeitart==-1?-1:0),bOben? 0:15*Fom.iFF/100,iZeitart==-1?1:(int)((lBis-lVon)*dFaktor),iH*Fom.iFF/100);
	if(Com instanceof JButton && (iBits&cstBehind)>0)
          Pnl.add(Com,0);
        else
          Pnl.add(Com);
}

private String Tage(Date dtVon,Date dtBis,int iStamm) 
{
	int i=0;
	if (dtVon==null || dtBis==null)
		return "";
	else
	{
		DateWOD DW=new DateWOD(dtVon);
		while (DW.getTime().before(dtBis))
		{
			Timestamp TSvon=DW.toTimestamp();
			DW.tomorrow();
			if (!DW.getTime().after(dtBis) && getDauer(iStamm,TSvon,DW.getTimeInMilli(),0)>0)
				i++;
		}
	}
	return i<1 ?"":i+" "+g.getShow(i==1 ? "Tag":"Tage")+" ";
}

private String getS(String sDt,Object Obj)
      {
        if (sDt.equals("BewStamm"))
          return g.getStamm(Sort.geti(Obj));
        else if (sDt.equals("BewBoolean"))
          return Static.JaNein(Tabellenspeicher.getb(Obj));
        else if (sDt.equals("Text"))
          return Tabellenspeicher.getm(Obj);
        else
          return ""+Obj;
      }

private void addFeiertage()
{
  /*if (AZ())
  {
    long lDif=new DateWOD(1970,1,1).getTimeInMillis();
    if(AbfFilter != null && TabFilter != null)
      for (TabPanel.moveFirst();!TabPanel.eof();TabPanel.moveNext())
       if(TabFilter.posInhalt("V"+iLoadFilterStamm,TabPanel.getI("AIC_Stamm")))
       {
         Timestamp TS1=TSZeitraumVon;
         while(!TabFilter.eof() && TabFilter.getI("V"+iLoadFilterStamm)==TabPanel.getI("AIC_Stamm"))
         {
           if (!TabFilter.isNull("V"+iLoadFilterOffen1))
           {
             Timestamp TS2=new Timestamp(TabFilter.getL("E" + iLoadFilterGueltig)+TabFilter.getL("V"+iLoadFilterOffen1)-lDif);
             addComponent((JPanel)TabPanel.getInhalt("Panel"), new JLabel(" "),TabFilter.getI("V" + iLoadFilterStamm),
                          TabFilter.getI("V" + iLoadFilterZeitart),0,TS1,TS2,null,null,null,null,0,false,null,true);
             //g.progInfo("1.Teil:"+TS1+" -"+TS2);
             TS1=new Timestamp(TabFilter.getL("E" + iLoadFilterGueltig)+TabFilter.getL("B"+iLoadFilterOffen1)-lDif);
           }
           if (!TabFilter.isNull("V"+iLoadFilterOffen2))
           {
             Timestamp TS2=new Timestamp(TabFilter.getL("E" + iLoadFilterGueltig)+TabFilter.getL("V"+iLoadFilterOffen2)-lDif);
             addComponent((JPanel)TabPanel.getInhalt("Panel"), new JLabel(" "),TabFilter.getI("V" + iLoadFilterStamm),
                          TabFilter.getI("V" + iLoadFilterZeitart),0,TS1,TS2,null,null,null,null,0,false,null,true);
             //g.progInfo("2.Teil:"+TS1+" -"+TS2);
             TS1=new Timestamp(TabFilter.getL("E" + iLoadFilterGueltig)+TabFilter.getL("B"+iLoadFilterOffen2)-lDif);
           }
           TabFilter.moveNext();
         }
         addComponent((JPanel)TabPanel.getInhalt("Panel"), new JLabel(" "),TabFilter.getI("V" + iLoadFilterStamm),
                          TabFilter.getI("V" + iLoadFilterZeitart),0,TS1,TSZeitraumBis,null,null,null,null,0,false,null,true);
       }
  }
  else*/
  {
    if(AbfFilter != null && TabFilter != null) {
      for(TabFilter.moveFirst(); !TabFilter.eof(); TabFilter.moveNext()) {
        if((Schicht() || TabFilter.getF("E" + iLoadFilterIstZeit) == 0.0 || !TabFilter.getS("E" + iLoadFilterZeitart).equals(""))
           && TabPanel.posInhalt("AIC_Stamm", TabFilter.getInt("V" + iLoadFilterStamm))) {
          //g.progInfo("Feiertag von Stamm="+TabFilter.getInt("V"+iLoadFilterStamm)+" ->"+TabFilter.getTimestamp("E"+iLoadFilterGueltig)+"/"+TabFilter.getF("E"+iLoadFilterIstZeit)+"/"+Static.className(TabFilter.getInhalt("E"+iLoadFilterZeitart)));
          addComponent((JPanel)TabPanel.getInhalt("Panel"), new JLabel(TabFilter.getS("E" + iLoadFilterZeitart)),
                       TabFilter.getI("V" + iLoadFilterStamm),TabFilter.getI("V" + iLoadFilterZeitart),0, TabFilter.getTimestamp("E" + iLoadFilterGueltig),
                       new Timestamp(TabFilter.getTimestamp("E" + iLoadFilterGueltig).getTime() + 86400000l),null,null,null,null, 0, 0, 0, null,0,true);
        }
      }
    }
    if (/*TabFilter == null &&*/ (AbfDaten.iBits&Abfrage.cstFeiertage)>0)
      for (TabPanel.moveFirst();!TabPanel.out();TabPanel.moveNext())
      {
        long lBis=TSZeitraumBis.getTime();
        for(DateWOD DW=new DateWOD(TSZeitraumVon);DW.getTimeInMilli()<lBis;DW.tomorrow())
        {

                {
                  String s=g.Feiertag(DW,TabPanel.getI("AIC_Stamm"),false);
                  if (!s.equals(""))
                    s=g.getBegriffS("Show","Ft");
//                  g.fixtestError("-1>"+s);
                  if (TabFilter == null && s.equals("") && DW.getDay() == Calendar.SUNDAY)
                    s=DateWOD.DFS.getShortWeekdays()[1];
//                  g.fixtestError("-2>"+s);
//                  g.fixtestError("Feiertag bei "+DW+":"+s);
                  if (!s.equals(""))
                  {
                    //g.testInfo(DW.toTimestamp()+"/"+TabPanel.getInhalt("AIC_Stamm")+":"+s);
                    addComponent((JPanel)TabPanel.getInhalt("Panel"), new JLabel(s),0,-2, 0,DW.toTimestamp(),new Timestamp(DW.toTimestamp().getTime() + 86400000l),null,null,null,null, 0, 0, 0, null,0,true);
                  }
                }
        }
      }
  }
}

private void next(DateWOD DW)
{
	String sZeitart=g.getZA(0);
    if (Stunden())
        DW.add(DateWOD.STUNDE*6);
	else if (sZeitart.equals("Tag") || sZeitart.equals("Woche"))
		DW.tomorrow();
	else if (sZeitart.equals("Monat"))
		DW.nextWeek();
	else if (sZeitart.equals("Quartal") || sZeitart.equals("Jahr"))
		DW.nextMonth();
	else
		DW.setTimeInMilli(TSZeitraumBis.getTime());
}

private void addLinien()
{
	int iLin=0;
	String sZeitart=g.getZA(0);
	g.fixInfoT("ZR2="+TSZeitraumVon+"-"+TSZeitraumBis+", Anz="+TabPanel.size()+"/"+sZeitart);
	for(TabPanel.moveFirst();!TabPanel.eof();TabPanel.moveNext())
	{
		DateWOD DW=new DateWOD(TSZeitraumVon);
		if (sZeitart.equals("Monat"))
			DW.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);

		long lBis=TSZeitraumBis.getTime();
		for(next(DW);DW.getTimeInMilli()<lBis;next(DW))
		{
			addComponent((JPanel)TabPanel.getInhalt("Panel"),new JLabel("."),0,-1,0,DW.toTimestamp(),null,null,null,null,null,0,DW.getHours()==0 ? 1:0, 0,null,0,true);
			iLin++;
                        /*if (TabFilter == null && (AbfDaten.iBits&Abfrage.cstFeiertage)>0)
                        {
                          String s=g.Feiertag(DW,TabPanel.getI("AIC_Stamm"),false);
                          if (s.equals("") && DW.getDay() == DW.SUNDAY)
                            s=DateWOD.DFS.getShortWeekdays()[1];
                          if (!s.equals(""))
                          {
                            g.testInfo(DW.toTimestamp()+"/"+TabPanel.getInhalt("AIC_Stamm")+":"+s);
                            addComponent((JPanel)TabPanel.getInhalt("Panel"), new JLabel(s),0,-2, DW.toTimestamp(),new Timestamp(DW.toTimestamp().getTime() + 86400000l),null,null,null,null, 0, false, null,true);
                          }
                        }*/
		}
				//new Timestamp(((Timestamp)Zeitraum.VecPerioden.elementAt(i)).getTime()-900000l),null
				//new Timestamp(((Timestamp)Zeitraum.VecPerioden.elementAt(i)).getTime()+900000l),0,false,null);
	}
//	g.fixtestError(iLin+" Linien ausgegeben");
}

private Timestamp getDatenVon()
{
	return TabDaten.getInhalt("V"+iLoadVonBis)==null ? TabDaten.getTimestamp("E"+iLoadDatum) : TabDaten.getTimestamp("V"+iLoadVonBis);
}

private Timestamp getDatenBis()
{
	if(TabDaten.getInhalt("B"+iLoadVonBis)==null)
	{
		DateWOD DW=new DateWOD(TabDaten.getTimestamp("E"+iLoadDatum));
		DW.tomorrow();
		return DW.toTimestamp();
	}
	else
		return TabDaten.getTimestamp("B"+iLoadVonBis);
}

/*private void LoadAnwesend()
{
  //g.progInfo("LoadAnwesend");
  for(TabFilter.moveFirst();!TabFilter.eof();TabFilter.moveNext())
  {
    int iStamm = TabFilter.getI("V" + iLoadFilterStamm);
    int iZeitart = TabFilter.getI("V" + iLoadFilterZeitart2);
    Timestamp TSVon = TabFilter.getTimestamp("V"+iLoadFilterAnwesend);
    Timestamp TSBis = TabFilter.getTimestamp("B"+iLoadFilterAnwesend);
    if(iZeitart==0)
      iZeitart=iStammArbeitszeit;
    else if (TSVon==null)
    {
      TSVon=TabFilter.getTimestamp("E"+iLoadFilterGueltig);
      DateWOD DW=new DateWOD(TabFilter.getL("E"+iLoadFilterGueltig));
      DW.tomorrow();
      TSBis=DW.toTimestamp();
    }
    double dDauer = TabFilter.getF("D"+iLoadFilterAnwesend);
    if (TSVon != null && TabPanel.posInhalt("AIC_Stamm",iStamm))
    {
      //g.progInfo("Anwesend "+iStamm+":"+TSVon+"_"+TSBis);
          addComponent((JPanel)TabPanel.getInhalt("Panel"),null,iStamm,iZeitart,0,TSVon,TSBis,null,null,null,null,dDauer,true,null,false);
    }
  }
}*/

@SuppressWarnings("unchecked")
private void doubleCheck() // prüft auf doppelte Daten
{
  int iStammOld=0;
  Timestamp TSVonOld=null;
  int iMom=0;
  Vector<Integer> Vec=new Vector<Integer>();
  Tabellenspeicher Tab=null;
  boolean bNeu=true;
  for(int iPos=0;iPos<TabDaten.size();iPos++)
  {
    int iStamm = TabDaten.getI(iPos,"V"+iLoadStamm);
    if (iStamm!=iStammOld)
    {
      iStammOld = iStamm;
      TSVonOld=TabDaten.getTimestamp(iPos, Schicht() || TabDaten.isNull(iPos,"V"+iLoadVonBis)?"E"+iLoadDatum:"V" + iLoadVonBis);
      bNeu=true;
    }
    else
    {
      Timestamp TSVon = TabDaten.getTimestamp(iPos, Schicht() || TabDaten.isNull(iPos,"V"+iLoadVonBis)?"E"+iLoadDatum:"V" + iLoadVonBis);
      if (TSVon.equals(TSVonOld))
      {
        String sZeitart=g.TabEigenschaften.getBezeichnungS(iLoadZeitart);
        if (Tab==null) Tab=new Tabellenspeicher(g, new String[] {"Mitarbeiter", "Datum", sZeitart, "Anzahl"});
        String sANR=g.getStamm(iStamm);
        if (bNeu)
        {
          Tab.newLine();
          Tab.setInhalt("Mitarbeiter",sANR);
          Tab.setInhalt("Datum",new Zeit(TabDaten.getDate(iPos,"E"+iLoadDatum),"dd.MM.yyyy"));
          Vector<String> VecZA=new Vector<String>();
          String sZA=TabDaten.getS(iPos,"E"+iLoadZeitart);
          VecZA.addElement(sZA);
          sZA=TabDaten.getS(iPos-1,"E"+iLoadZeitart);
          if (!VecZA.contains(sZA)) VecZA.addElement(sZA);
          Tab.setInhalt(sZeitart,VecZA);
          Tab.setInhalt("Anzahl",2);
        }
        else
        {
          String sZA=TabDaten.getS(iPos,"E"+iLoadZeitart);
          Vector VecZA=(Vector)Tab.getInhalt(sZeitart);
          if (!VecZA.contains(sZA)) VecZA.addElement(sZA);
          Tab.addI("Anzahl", 1);
        }
        g.fixtestInfo("doppelt:"+sANR+"/"+TSVon+" ("+iMom+")");
        iPos--;
        TabDaten.clearInhalt(iPos);
        Vec.addElement(iMom);
        bNeu=false;
      }
      else
      {
        TSVonOld = TSVon;
        bNeu=true;
      }
    }
    iMom=TabDaten.getI(iPos,"aic_bew_pool");
  }
  if (Vec.size()>0 && new Message(Message.YES_NO_OPTION+Message.SHOW_TAB, (JFrame)Fom.thisFrame, g).showDialog("doppelte_entfernen",Tab) == Message.YES_OPTION)
  {
    int iProt=g.Protokoll("Entfernen",iPlanung);
    g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and"+g.in("aic_bew_pool",Vec));
  }
}

private void Load(long lClock)
{
  //g.fixInfo("-------------------- Load");
  doubleCheck();
  Vector<String> VecRest=null;
	for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
	{
		Vector<Integer> VecAIC = new Vector<Integer>();
		VecAIC.addElement(TabDaten.getInt("AIC_BEW_POOL"));
		int iStamm = TabDaten.getI("V"+iLoadStamm);
		int iZeitart = TabDaten.getI("V"+iLoadZeitart);
                int iFarbe = iLoadFarbe>0 ? TabDaten.getI("V"+iLoadFarbe):0;
		int iStatus = iLoadBewilligt>0 ? TabDaten.getB("E"+iLoadBewilligt)?1:0:iLoadBewilligt3VG>0? EdtStatus.getNr(TabDaten.getI("V"+iLoadBewilligt3VG)):1;
		int iStatusSV = iLoadBewilligt3SV>0? EdtStatusSV.getNr(TabDaten.getI("V"+iLoadBewilligt3SV)):1;
		int iBewBew=0;
                if (iRest>0)
                {
                  //g.testInfo("iRest="+iRest);
                  //TabSpaltenDaten.showGrid("TabSpaltenDaten");
                  VecRest = new Vector<String>();
                  for (TabSpaltenDaten.posInhalt("Nummer", iRest); !TabSpaltenDaten.eof(); TabSpaltenDaten.moveNext())
                    VecRest.addElement(TabDaten.getS(TabSpaltenDaten.getS("Kennung")));
                  //g.progInfo("VecRest="+VecRest);
                }
                Timestamp TSVon = getDatenVon();
		Timestamp TSBis = getDatenBis();
		long lBisLast=TSBis.getTime();
		double dDauer = TabDaten.getF("D"+iLoadVonBis);
		if (sLoadBewBew != null)
			iBewBew=TabDaten.getI(sLoadBewBew);
		TabDaten.push();
		boolean bWeiter=true;
		do
		{
			TabDaten.moveNext();
			if(!TabDaten.out()&&iStamm==TabDaten.getI("V"+iLoadStamm)&&iZeitart==TabDaten.getI("V"+iLoadZeitart))
			{
				bWeiter=!Schicht() && getDatenVon().getTime()==lBisLast && (iLoadBewilligt==0 && iLoadBewilligt3VG==0 ||
						iLoadBewilligt>0 && TabDaten.getI("E"+iLoadBewilligt)==iStatus || iLoadBewilligt3VG>0 && EdtStatus.getNr(TabDaten.getI("V"+iLoadBewilligt3VG))==iStatus);
//				g.fixtestError("Stamm="+iStamm+", iZA="+iZeitart+": "+getDatenVon()+"-"+getDatenBis()+"->"+bWeiter);
                                if (bWeiter && iRest>0)
                                {
                                  int i=0;
                                  for (TabSpaltenDaten.posInhalt("Nummer", iRest); !TabSpaltenDaten.eof(); TabSpaltenDaten.moveNext())
                                  {
                                    if(/*(sLoadBewBew==null || !TabSpaltenDaten.getS("Kennung").equalsIgnoreCase(sLoadBewBew)) &&*/ !Static.Gleich(VecRest.elementAt(i),TabDaten.getS(TabSpaltenDaten.getS("Kennung"))))
                                    {
                                        bWeiter=false;
                                        g.fixtestError("ungleich:"+TabSpaltenDaten.getS("Kennung")+"( BewBew="+sLoadBewBew+")");
                                    }
                                    i++;
                                  }
                                }
				lBisLast=getDatenBis().getTime();
				if(bWeiter)
				{
					dDauer+=TabDaten.getF("D"+iLoadVonBis);
					TSBis = getDatenBis();
//					g.fixtestError(TSVon+"-"+TSBis+"->"+dDauer);
					VecAIC.addElement(TabDaten.getInt("AIC_BEW_POOL"));
					TabDaten.change();
				}
			}
		}
		while(!TabDaten.out() && iStamm==TabDaten.getI("V"+iLoadStamm) && iZeitart==TabDaten.getI("V"+iLoadZeitart) && bWeiter);
                /*if (Schicht() && TabZeitart.posInhalt("aic_stamm",iZeitart))
                {
                  DateWOD dt=new DateWOD(TSVon);
                  double dVon=dt.getAbsSeconds();
                  dt.setAllSeconds(dVon + TabZeitart.getF("von1"));
                  dt.setTimezoneOffset();
                  TSVon=dt.toTimestamp();
                  dt.setAllSeconds(dVon + (TabZeitart.isNull("bis2")?TabZeitart.getF("bis1"):TabZeitart.getF("bis2")));
                  dt.setTimezoneOffset();
                  TSBis=dt.toTimestamp();

                  g.progInfo(TabZeitart.getS("KZ")+":"+TSVon+"/"+TSBis);
                }*/
                Timestamp TSVon0=TSVon;
                Timestamp TSBis0=TSBis;
                Timestamp TSVon2=null;
                Timestamp TSBis2=null;
                if (Schicht() || bVoll && (TSBis.getTime()-TSVon.getTime())<23*3600*1000)
                {
                  DateWOD dt=new DateWOD(TSVon);
                  dt.setTimeZero();
                  TSVon0=dt.toTimestamp();
                  dt.tomorrow();
                  TSBis0=dt.toTimestamp();
                  TabDaten.pop();
                  /*if (TabDaten.getTimestamp("E"+iLoadDatum).equals(TabDaten.getTimestamp("V"+iLoadVonBis)))  // !!! nur Übergangsweise
                  {
                    TSVon=null;
                    TSBis=null;
                  }
                  else*/
                  {
                    TSVon = TabDaten.getTimestamp("V" + iLoadVonBis);
                    TSBis = TabDaten.getTimestamp("B" + iLoadVonBis);
                    if (iLoadVonBis2>0)
                    {
                      TSVon2 = TabDaten.getTimestamp("V" + iLoadVonBis2);
                      TSBis2 = TabDaten.getTimestamp("B" + iLoadVonBis2);
                      if (TSVon2 != null)
                        dDauer+=TabDaten.getF("D"+iLoadVonBis2);
                    }
                  }
                }
                else
                  TabDaten.pop();
                //g.fixInfo(TabDaten.getPos()+":"+iStamm+"/"+TSVon+"/"+TSBis+"/"+dDauer);
		if(TabPanel.posInhalt("AIC_Stamm",TabDaten.getI("V"+iLoadStamm)))
			addComponent((JPanel)TabPanel.getInhalt("Panel"),null,iStamm,iZeitart,iFarbe,TSVon0,TSBis0,TSVon,TSBis,TSVon2,TSBis2,/*AZ() ? getDauer(iStamm,TSVon,TSBis.getTime(),iZeitart):*/dDauer,iStatus, iStatusSV,VecAIC,iBewBew,true);
	}
	g.clockInfo2("Daten anzeigen",lClock);
        makeSum(0);
        g.clockInfo2("makeSum",lClock);
}

/*private boolean Anschluss(long l)
{
	//g.progInfo("Anschluss:"+(getDatenVon().getTime()-l));
	return getDatenVon().getTime()==l;
}*/

private boolean Loeschen(String s)
{
  long lClock2 = Static.get_ms();
    if(TabButton.posInhalt("Button",BtnAkt) && TabPanel.posInhalt("AIC_Stamm",TabButton.getI("Stamm")) && CboZeitart.getComboBox().contains(TabButton.getI("Zeitart")))
    {
      //int pane = new Message(Message.YES_NO_OPTION,(JFrame)Fom.thisFrame,g).showDialog("Loeschen",new String[] {s});
      if(bMoa && Check.Monatsabschluss(g/*,Fom.getBegriff()*/,iAIC_Bewegungstyp,TabButton.getI("Stamm"),new Zeit((java.util.Date)TabButton.getInhalt("Von_Neu"),""),null))
      {
              new Message(Message.WARNING_MESSAGE,(JFrame)Fom.thisFrame,g).showDialog("Monatsabschluss");
              return false;
      }
      else if(!bAsk || new Message(Message.YES_NO_OPTION,(JFrame)Fom.thisFrame,g).showDialog("Loeschen",new String[] {s}) == Message.YES_OPTION)
      {
        ((JPanel)TabPanel.getInhalt("Panel")).remove(BtnAkt);
        if (TabButton.isNull("VecAIC"))
          TabButton.clearInhalt();
        else
        {
          TabButton.setInhalt("Von_Neu", null);
          TabButton.setInhalt("Bis_Neu", null);
          TabButton.setInhalt("Dauer_Neu", null);
        }
        makeSum(TabButton.getI("Stamm"));
        g.clockInfo("Loeschen ",lClock2);
        return true;
      }
    }
    return false;
}

public boolean Modified()
{
  if (TabButton != null)
	for(TabButton.moveFirst();!TabButton.eof();TabButton.moveNext())
	{
		Timestamp TSVon = TabButton.getTimestamp("Von_Neu");
		Timestamp TSBis = TabButton.getTimestamp("Bis_Neu");
		Vector VecAIC=(Vector)TabButton.getInhalt("VecAIC");

		if(VecAIC==null||TSVon==null||TSBis==null||TabButton.getTimestamp("Von_Alt").getTime()!=TSVon.getTime()||TabButton.getTimestamp("Bis_Alt").getTime()!=TSBis.getTime()
		   ||TabButton.getI("Stamm")!=TabButton.getI("Stamm_Alt")||TabButton.getI("Zeitart")!=TabButton.getI("Zeitart_Alt")||iLoadFarbe>0 && TabButton.getI("Farbe2")!=TabButton.getI("Farbe_Alt")
                   ||TabButton.getI("Status")!=TabButton.getI("Status_Alt")||TabButton.getI("StatusSV")!=TabButton.getI("StatusSV_Alt")||TabButton.getF("Dauer_Neu")!=TabButton.getF("Dauer_Alt"))
			return true;
	}
	return false;
}

private boolean RestAnders(Object Btn)
{
	boolean b=false;
	if (iRest>0 && TabRest.posInhalt("Button",Btn))
		for(;!TabRest.out() && TabRest.getInhalt("Button")==Btn;TabRest.moveNext())
			if(!TabRest.getS("Neu").equals(TabRest.getS("Alt")))
			{
				//g.progInfo("RestAnders:"+TabRest.getS("Neu")+":"+Static.className(TabRest.getInhalt("Neu"))+"/"+TabRest.getS("Alt")+":"+Static.className(TabRest.getInhalt("Alt")));
				b=true;
			}
	return b;
}

private void checkNext(boolean b,Tabellenspeicher Tab)
{
  if (b) Tab.moveNext();
}

@SuppressWarnings("unchecked")
public void Save()
{
  if (BtnInfo!=null) BtnInfo.setEnabled(true);
  g.checkAus("Planung "+sBez);
        if (!bSave || /*!g.bTestPC &&*/ g.getMandant()==1 && !g.bAppl && !g.bBasis)
        {
          new Message(Message.WARNING_MESSAGE, null, g).showDialog("Speichernsperre");
          Static.printError("Planung.Save: Speichern mit All nicht möglich");
          Refresh(VecNeu,iAicStamm,true);
          return;
        }
        //if (bAsk && new Message(Message.YES_NO_OPTION, (JFrame)Fom.thisFrame, g).showDialog("Speichern") != Message.YES_OPTION)
        //  return;

        long lClock1=Static.get_ms();
        if (QrySave == null)
          QrySave=new SQL(g);
        if (QrySave.bExec2)
        {
          g.fixInfo("Speichern momentan nicht möglich!");
          return;
        }
        bSaveMom=true;
	Vector<Integer> VecSave=new Vector<Integer>();
        boolean bNext=true;
        Vector<JButton> VecBtn=new Vector<JButton>();
        try
        {
          if (!bkRnS)
            g.start();
          for (TabButton.moveFirst(); !TabButton.eof(); checkNext(bNext, TabButton))
          {
            bNext = true;
            Timestamp TSVon = TabButton.getTimestamp("Von_Neu");
            Timestamp TSBis = TabButton.getTimestamp("Bis_Neu");
            Vector<Integer> VecAIC = (Vector)TabButton.getInhalt("VecAIC");

            if (VecAIC == null || TSVon == null || TSBis == null || TGM() && !VecBtn.contains(TabButton.getInhalt("Button")) ||RestAnders(TabButton.getInhalt("Button")) ||
                TabButton.getTimestamp("Von_Alt").getTime() != TSVon.getTime() || TabButton.getTimestamp("Bis_Alt").getTime() != TSBis.getTime()
                || TabButton.getI("Stamm") != TabButton.getI("Stamm_Alt") || TabButton.getI("Zeitart") != TabButton.getI("Zeitart_Alt") ||
                iLoadFarbe > 0 && TabButton.getI("Farbe2") != TabButton.getI("Farbe_Alt")
                || TabButton.getI("Status") != TabButton.getI("Status_Alt") || TabButton.getI("StatusSV") != TabButton.getI("StatusSV_Alt") || TabButton.getF("Dauer_Neu") != TabButton.getF("Dauer_Alt"))
            {
              if (iProtokoll == 0)
                iProtokoll = g.Protokoll("Planung", iPlanung);

              g.fixInfo("Save"+TabButton.getPos()+":"+TabButton.getI("Stamm")+"="+TSVon+"-"+TSBis);
              int iBP_Web = 0;
              if (AbfWeb!=null)
              {
            	  int iBewBew=TabButton.getI("BewBew");
            	  if (iBewBew>0)
            	  {
            		  g.exec("update bew_pool set pro_aic_protokoll=" + iProtokoll + " where aic_bew_pool="+iBewBew);
            		  QrySave.add("bew_aic_bew_pool",iBewBew);
            	  }
            	  QrySave.add("AIC_Protokoll", iProtokoll);
                  QrySave.add("AIC_Bewegungstyp", AbfWeb.iBew);
                  QrySave.add("AIC_Mandant", g.getMandant());
                  if (g.hasZone(AbfWeb.iBew))
                	  QrySave.add("Zone", g.getZone());
                  iBP_Web = QrySave.insert("Bew_Pool", true);
                  if (iWebStamm>0)
                	  Import.insertBewStamm(g, QrySave, AbfWeb.iBew, iBP_Web, iWebStamm, TabButton.getI("Stamm"), bkRnS);
                  if (iWebZeitart>0)
                	  Import.insertBewStamm(g, QrySave, AbfWeb.iBew, iBP_Web, iWebZeitart, TabButton.getI("Zeitart"), bkRnS);
                  //g.fixtestError("ZA auf Eig="+iWebZeitart+":"+TabButton.getI("Zeitart"));
                  if (iWebVonBis>0)
                	  Import.insertBewVonBis(g, AbfWeb.iBew, iBP_Web,iWebVonBis,TSVon,TSBis,TabButton.getF("Dauer_Neu"));
//                  {
//                	  QrySave.add("AIC_Bew_Pool", iBP_Web);
//                      QrySave.add("AIC_Eigenschaft", iWebVonBis);
//                      QrySave.add("Von", TSVon);
//                      QrySave.add("Bis", TSBis);
//                      QrySave.add("Dauer", TabButton.getF("Dauer_Neu"));
//                      QrySave.insert2("Bew_Von_Bis", bkRnS);
//                  }
                  if (iWebBew1 > 0 && TabButton.getI("Status")>0)
                      Import.insertBewBool3(g,AbfWeb.iBew, iBP_Web,iWebBew1,TabButton.getI("Status"));
                  if (iWebBew2 > 0 && TabButton.getI("StatusSV")>0)
                      Import.insertBewBool3(g,AbfWeb.iBew, iBP_Web,iWebBew2,TabButton.getI("StatusSV"));  
                  // Memo und Stellvertreter speichern
                  if (iWebMemo > 0 && iLoadMemoNr>0 && TabRest.posInhalt(TabButton.getInhalt("Button"), iLoadMemoNr))
                	  Import.Save(g, TabRest.getInhalt("Neu"), iWebMemo, false, iBP_Web, iProtokoll, true, null,false);
                  if (iWebMemo2 > 0 && iLoadMemo2Nr>0 && TabRest.posInhalt(TabButton.getInhalt("Button"), iLoadMemo2Nr))
                	  Import.Save(g, TabRest.getInhalt("Neu"), iWebMemo2, false, iBP_Web, iProtokoll, true, null,false);
                  if (iWebErsatz > 0 && iLoadErsatzNr>0 && TabRest.posInhalt(TabButton.getInhalt("Button"), iLoadErsatzNr))
                  {
                	  //g.fixtestError("Ersatz:"+iWebErsatz+"/"+iLoadErsatzNr+"="+TabRest.getI("Neu"));
                	  Import.insertBewStamm(g, QrySave, AbfWeb.iBew, iBP_Web, iWebErsatz, TabRest.getI("Neu"), bkRnS);
                  }
              }

              if (VecAIC != null)
                QrySave.exec2("update bew_pool set pro_aic_protokoll=" + iProtokoll + " where aic_bew_pool " + Static.SQL_in(VecAIC), bkRnS);
              if (/*TSVon == null &&*/ VecAIC!=null) // TS == null weg, da auch geänderte Werte übergeben werden sollen
                for (int i = 0; i < VecAIC.size(); i++)
                  VecSave.addElement(VecAIC.elementAt(i));
              g.fixtestError("VecSave nach löschen:"+VecSave);
              if (!(TSVon == null || TSBis == null))
              {
                Timestamp TSTomorrow = null;
                Tabellenspeicher TabDauer = null;
                if (!bLoadVonBisEdt)
                {
                  TabDauer = new Tabellenspeicher(g,
                                                  "select * from Bew_Von_Bis where aic_bew_pool " + Static.SQL_in(VecAIC) + " and aic_eigenschaft=" + iLoadVonBis + " order by bew_von_bis.von", true);
                  //TabDauer.showGrid("TabDauer");
                  TabDauer.moveFirst();
                  //g.testInfo("von="+Static.print(TabDauer.getInhalt("von")));
                }
                Vector<Integer> VecOld = VecAIC==null ? null:new Vector<Integer>(VecAIC);
                g.fixtestError("VecOld="+VecOld);
                int iPos=0;
                VecAIC = new Vector();
                do
                {
                  //Gueltigkeit//
                  QrySave.add("AIC_Protokoll", iProtokoll);
                  QrySave.add("AIC_Bewegungstyp", iAIC_Bewegungstyp);
                  java.sql.Timestamp ts = new DateWOD(TSVon.getTime()).setTimeZero().toTimestamp();
                  QrySave.add("Gueltig", ts);
                  QrySave.add("LDATE", Static.DateToInt(ts));
                  QrySave.add("BOOL1",0);
                  QrySave.add("BOOL2",0);
                  //if (g.isEigANR(iLoadStamm))
                  QrySave.add("ANR", TabButton.getI("Stamm"));
                  QrySave.add("AIC_Mandant", g.getMandant());
                  if (g.hasZone(iAIC_Bewegungstyp))
                	  QrySave.add("Zone", g.getZone());
                  if (/*sLoadBewBew !=null &&*/ iBP_Web>0)
                	  QrySave.add("BEW2_AIC_BEW_POOL",iBP_Web);
                  if (VecOld != null && iPos<VecOld.size())
                	  QrySave.add("bew_aic_bew_pool",Sort.geti(VecOld, iPos));
                  iPos++;
                  int iAIC_Bew_Pool = QrySave.insert("Bew_Pool", true);
                  VecAIC.addElement(new Integer(iAIC_Bew_Pool));
                  VecSave.addElement(new Integer(iAIC_Bew_Pool));
                  //Stamm//
                  Import.insertBewStamm(g, QrySave, iAIC_Bewegungstyp, iAIC_Bew_Pool, iLoadStamm, TabButton.getI("Stamm"), bkRnS);
                  /*QrySave.add("AIC_Bew_Pool",iAIC_Bew_Pool);
                        QrySave.add("AIC_Stamm",TabButton.getI("Stamm"));
                        QrySave.add("AIC_Eigenschaft",iLoadStamm);
                        QrySave.insert2("Bew_Stamm",bkRnS);*/

                  //Zeitart//
                  Import.insertBewStamm(g, QrySave, iAIC_Bewegungstyp, iAIC_Bew_Pool, iLoadZeitart, TabButton.getI("Zeitart"), bkRnS);
                  /*QrySave.add("AIC_Bew_Pool",iAIC_Bew_Pool);
                        QrySave.add("AIC_Stamm",TabButton.getI("Zeitart"));
                        QrySave.add("AIC_Eigenschaft",iLoadZeitart);
                        QrySave.insert2("Bew_Stamm",bkRnS);*/

                  if (iLoadFarbe > 0)
                  {
                    Import.insertBewStamm(g, QrySave, iAIC_Bewegungstyp, iAIC_Bew_Pool, iLoadFarbe, TabButton.getI("Farbe2"), bkRnS);
                    /*QrySave.add("AIC_Bew_Pool",iAIC_Bew_Pool);
                                                               QrySave.add("AIC_Stamm", TabButton.getI("Farbe2"));
                                                               QrySave.add("AIC_Eigenschaft", iLoadFarbe);
                                                               QrySave.insert2("Bew_Stamm", bkRnS);*/
                  }

                  //Bewilligt//
                  if (iLoadBewilligt > 0 && TabButton.getI("Status")>0)
                    Import.insertBewBool(g,iAIC_Bewegungstyp, iAIC_Bew_Pool,iLoadBewilligt);                   
                  if (iLoadBewilligt3VG > 0 && TabButton.getI("Status")>0)
                    Import.insertBewBool3(g,iAIC_Bewegungstyp, iAIC_Bew_Pool,iLoadBewilligt3VG,TabButton.getI("Status"));
                  if (iLoadBewilligt3SV > 0 && TabButton.getI("StatusSV")>0)
                      Import.insertBewBool3(g,iAIC_Bewegungstyp, iAIC_Bew_Pool,iLoadBewilligt3SV,TabButton.getI("StatusSV"));  

                  JButton Btn = (JButton)TabButton.getInhalt("Button");
                  if (iRest > 0 && TabRest.posInhalt("Button", Btn))
                  {
                    for (; !TabRest.out() && TabRest.getInhalt("Button") == Btn; TabRest.moveNext())
                    {
                      if (!Check.isNull(TabRest.getInhalt("Neu")))
                      {
                        TabSpaltenDaten.posInhalt("Nummer", TabRest.getI("Nr"));
                        String sDatentyp = TabSpaltenDaten.getS("Datentyp");
                        g.fixtestInfo("------ Save "+TabRest.getI("Nr")+"/"+sDatentyp+":"+TabRest.getS("Neu"));
                        if (!sDatentyp.equals("SysAic"))
                        {
	                        int iEig=TabSpaltenDaten.getI("Kennung2");
	                        if (sDatentyp.equals("BewStamm"))
	                        {
	                          Import.insertBewStamm(g, QrySave, iAIC_Bewegungstyp, iAIC_Bew_Pool, iEig, TabRest.getI("Neu"), bkRnS);
	                          /*QrySave.add("AIC_Bew_Pool",iAIC_Bew_Pool);
	                                    QrySave.add("AIC_Eigenschaft",TabSpaltenDaten.getI("Kennung2"));
	                                    QrySave.add("AIC_Stamm",TabRest.getI("Neu"));
	                                    QrySave.insert2("Bew_Stamm",bkRnS);*/
	                        }
	                        else if (sDatentyp.equals("BewBoolean"))
	                        {
	                          if (TabRest.getB("Neu"))
	                            Import.insertBewBool(g,iAIC_Bewegungstyp, iAIC_Bew_Pool,iEig);
	                            //g.exec("insert into bew_boolean (aic_bew_pool,aic_eigenschaft,spalte_boolean) values ("+iAIC_Bew_Pool+","+TabSpaltenDaten.getI("Kennung2")+",1)");
	                        }
	                        else if (sDatentyp.equals("BewBool3"))
	                        {
	                        	if (TabRest.getI("Neu")>0)
	                        		Import.insertBewBool3(g, iAIC_Bewegungstyp, iAIC_Bew_Pool, iEig, -TabRest.getI("Neu"));
	                        }
	                        else if (sDatentyp.equals("BewZahl"))
	                        {
	                        	if (TabRest.getI("Neu")!=0)
	                        		g.exec("insert into bew_Wert (aic_bew_pool,aic_eigenschaft,spalte_wert) values ("+iAIC_Bew_Pool+","+iEig+","+TabRest.getI("Neu")+")");
	                        }
	                        else if (sDatentyp.equals("BewDatum2"))
	                        {
	                        	Import.insertBewDatum2(g,iAIC_Bewegungstyp,iAIC_Bew_Pool,iEig,TabRest.getDate("Neu"));
	                        }
	                        else //if (!sDatentyp.equals("SysAic"))
	                          Import.Save(g, TabRest.getInhalt("Neu"), iEig, false, iAIC_Bew_Pool, iProtokoll, true, null,false);
                        }
                        //g.progInfo("Save:"+TabSpaltenDaten.getS("Bezeichnung"));
                      }
                    }
                  }
                  DateWOD DW = new DateWOD(TSVon.getTime());
                  DW.tomorrow();
                  if (!Schicht())
                    DW.setTimeZero();
                  TSTomorrow = DW.toTimestamp();
//                  QrySave.add("AIC_Bew_Pool", iAIC_Bew_Pool);
//                  QrySave.add("AIC_Eigenschaft", iLoadVonBis);
//                  QrySave.add("Von", TSVon);
                  Timestamp TSsaveBis = TSTomorrow.getTime() < TSBis.getTime() ? TSTomorrow : TSBis;
//                  QrySave.add("Bis", TSsaveBis);
                  double d = 0;
                  if (TabDauer != null) // && TabDauer.posInhalt("von",TSVon2))
                  {
                    d = TabDauer.getF("Dauer");
                    TabDauer.moveNext();
                    g.testInfo(TabDauer.getInhalt("Von") + ": Dauer bleibt bei " + d);
                  }
                  else
                    d = getDauer(TabButton.getI("Stamm"), TSVon, TSsaveBis.getTime(), TabButton.getI("Zeitart"));

//                  QrySave.add("Dauer", d);
//                  QrySave.insert2("Bew_Von_Bis", bkRnS);
                  Import.insertBewVonBis(g, iAIC_Bewegungstyp, iAIC_Bew_Pool,iLoadVonBis,TSVon,TSsaveBis,d);
                  if (iLoadVonBis2 > 0 && !TabButton.isNull("Von2_Neu"))
                  {
                    QrySave.add("AIC_Bew_Pool", iAIC_Bew_Pool);
                    QrySave.add("AIC_Eigenschaft", iLoadVonBis2);
                    Timestamp TSVon2 = TabButton.getTimestamp("Von2_Neu");
                    Timestamp TSBis2 = TabButton.getTimestamp("Bis2_Neu");
                    QrySave.add("Von", TSVon2);
                    QrySave.add("Bis", TSBis2);
                    d = getDauer(TabButton.getI("Stamm"), TSVon2, TSBis2.getTime(), TabButton.getI("Zeitart"));
                    QrySave.add("Dauer", d);
                    QrySave.insert2("Bew_Von_Bis", bkRnS);
                  }
                  TSVon = TSTomorrow;
                }
                while (TSTomorrow.getTime() < TSBis.getTime());
                if (bkRnS)
                {
                  /*TabButton.setInhalt("VecAIC",VecAIC);
                     TabButton.setInhalt("Von_Alt",TabButton.getInhalt("Von_Neu"));
                     TabButton.setInhalt("Bis_Alt",TabButton.getInhalt("Bis_Neu"));
                     TabButton.setInhalt("Dauer_Alt",TabButton.getInhalt("Dauer_Neu"));
                     TabButton.setInhalt("Stamm_Alt",TabButton.getInhalt("Stamm"));
                     TabButton.setInhalt("Zeitart_Alt",TabButton.getInhalt("Zeitart"));
                     TabButton.setInhalt("Farbe_Alt",TabButton.getInhalt("Farbe2"));
                     TabButton.setInhalt("Bewilligt_Alt",TabButton.getInhalt("Bewilligt"));*/
                  TabButton.push();
                  if (TabPanel.posInhalt("AIC_Stamm", TabButton.getInhalt("Stamm")))
                  {
                    Timestamp TSVon0 = TabButton.getTimestamp("Von_Neu");
                    Timestamp TSBis0 = TSBis;
                    if (Schicht())
                    {
                      DateWOD dt = new DateWOD(TSVon0);
                      dt.setTimeZero();
                      TSVon0 = dt.toTimestamp();
                      dt.tomorrow();
                      TSBis0 = dt.toTimestamp();
                    }
                    addComponent((JPanel)TabPanel.getInhalt("Panel"), null, TabButton.getI("Stamm"), TabButton.getI("Zeitart"), TabButton.getI("Farbe2"),
                                 TSVon0, TSBis0, TabButton.getTimestamp("Von_Neu"), TSBis, TabButton.getTimestamp("Von2_Neu"), TabButton.getTimestamp("Bis2_Neu"),
                                 /*AZ() ? getDauer(TabButton.getI("Stamm"), TSVon, TSBis.getTime(), TabButton.getI("Zeitart")) :*/ TabButton.getF("Dauer_Neu"),
                                 TabButton.getI("Status"), TabButton.getI("StatusSV"), VecAIC, iBP_Web, true);
                    if (TGM())
                    	VecBtn.addElement((JButton)TabButton.getInhalt("Button"));
                  }
                  else
                    Static.printError("Planung.Save: Panel für Stamm " + TabButton.getInhalt("Stamm") + " nicht gefunden");
                  TabButton.pop();
                  ((JPanel)TabPanel.getInhalt("Panel")).remove((JButton)TabButton.getInhalt("Button"));
                  ((JPanel)TabPanel.getInhalt("Panel")).repaint();
                  TabButton.clearInhalt();
                  bNext = false;
                }
              }
              else if (bkRnS)
              {
                TabButton.clearInhalt();
                bNext = false;
              }
            }
          }
          g.fixtestError("VecSave nach speichern:"+VecSave);
          if (bkRnS)
            QrySave.exec2();
          g.clockInfo("Save " + sBez, lClock1);
          //g.testInfo("VecSave="+VecSave+", Modell="+AbfDaten.iModell+"/"+((AbfDaten.iBits&AbfDaten.cstNachSave)>0));
          boolean bError = false;
          //g.progInfo("               Zeitraum1="+g.getVonBis("dd.MM.yyyy",true));
          if (AbfDaten.iModell2 > 0 && !VecSave.isEmpty())
          {
            lClock1 = Static.get_ms();
            Timestamp TSZeitraumVonOld=g.getVon();
            Timestamp TSZeitraumBisOld=g.getBis();
            g.setVonBis(TSZeitraumVon,TSZeitraumBis);
            //g.progInfo("               Zeitraum2="+g.getVonBis("dd.MM.yyyy",true));
            Calc c = new Calc((JFrame)Fom.thisFrame, g, AbfDaten.iModell2, false, VecSave, 0, null, 0);
            bError = c.Fehler() != null;
            g.setVonBis(TSZeitraumVonOld,TSZeitraumBisOld);
            g.clockInfo("nach Save " + sBez, lClock1);
            if (!bError && (c.iMBits & Global.cstRefreshM) > 0)
              Fom.bFRefresh=true;
          }
          //g.progInfo("               Zeitraum3="+g.getVonBis("dd.MM.yyyy",true));
          if (!bkRnS)
        	  g.commit2(bError);
        }
        catch(Exception e)
        {
          if (!bkRnS)
            g.rollback();
          e.printStackTrace();
          Static.printError("Planung.Save:"+e);
        }
        //g.fixtestError("Save: vor Refresh");
        if (!isShowing())
        {
        	//g.fixtestError("Save: Planung aktuell nicht aktiv");
        	TabButton.clearAll();
        }
        else 
        {
          if (!bSES)
            setInfo();
          if (!bkRnS)
          {
        	//g.fixtestError("Save: Refresh");
        	Refresh(VecNeu, iAicStamm, true);
          }
        }
        iProtokoll=0;
        bSaveMom=false;
}// Save()

private void addOnFlow(JPanel Pnl,JComponent C)
      {
        JPanel PnlF=new JPanel(new FlowLayout(FlowLayout.LEFT,5,2));
        PnlF.add(C);
        Pnl.add(PnlF);
      }

private void setBtnBorder(JButton Btn)
{
  if (BtnLast!=null)
    BtnLast.setBorder(BorderFactory.createLineBorder(Color.black));
  BtnLast=Btn;
  if (Btn!=null)
    Btn.setBorder(BorderFactory.createLineBorder(Color.RED,2));
}

private String getBez(int iEig)
{
  return TabSpaltenDaten.posInhalt("Kennung2",""+iEig) ? TabSpaltenDaten.getS("Bezeichnung"):g.TabEigenschaften.getBezeichnungS(iEig);
}

private void checkBewilligbar(int iEig)
{
  //g.fixtestInfo("checkBewilligbar "+iEig+":"+bLoadBewilligtEdt+"/"+iMode+"/"+bSave);
  boolean b=iMode!=_INFO && bSave && iEig>0;
  if (b)
  {
    int iPos=TabZeitart.getPos("aic_stamm",iEig);
    b=iPos<0 ? false:TabZeitart.getB(iPos,"bewilligt");
    //g.fixtestInfo("Pos="+iPos+" -> "+b);
  }
  if(iLoadBewilligt>0)
    CbxBewilligt.setEnabled(b);
  else if(iLoadBewilligt3VG>0)
    EdtStatus.setEnabled(b && bLoadBewilligtVG_Edt);
  if(iLoadBewilligt3SV>0)
	    EdtStatusSV.setEnabled(b && bLoadBewilligtSV_Edt);
}

/*private void checkBewilligbarSV(int iEig)
{
	boolean b=bLoadBewilligtSV_Edt && iMode!=_INFO && bSave && iEig>0;
	  if (b)
	  {
	    int iPos=TabZeitart.getPos("aic_stamm",iEig);
	    b=iPos<0 ? false:TabZeitart.getB(iPos,"bewilligt");
	    //g.fixtestInfo("Pos="+iPos+" -> "+b);
	  }
	  if(iLoadBewilligt3SV>0)
	    EdtStatusSV.setEnabled(b);
}*/

private void Edit(final JButton Btn,boolean bSetZA)
{
  if (!table.isEnabled())
    return;
  //g.fixInfo("Edit:"+CboZeitart.isActive()+"/"+Btn.getText());
  if (bSetZA) // ändern über offene AUComboList
  {
    //TabButton.posInhalt("Button",Btn);
    //g.fixInfo("prüfe Sperre bei Edit");
    if (Btn!=null && TabButton.posInhalt("Button",Btn) && !gesperrt(TabButton.getI("Stamm"),true))
    {
      VB.setValue((Timestamp)TabButton.getInhalt("Von_Neu"), (Timestamp)TabButton.getInhalt("Bis_Neu"));
      if (CboZeitart.isActive())
        setZeitart(CboZeitart.getSelected());
      setBtnBorder(Btn);
    }
    return;
  }
  if (verschleiern(true) && Btn != null)
  {
	  int iZA=TabButton.posInhalt("Button",Btn) ? TabButton.getI("Zeitart"):0;
	  g.fixtestError("Edit prüfe auf verschleiert:"+iZA);
	  if (TabZeitart.posInhalt("aic_stamm",iZA) &&  TabZeitart.getB("verschl"))
		  return;
  }
  //g.progInfo("VB="+VB.getVonBis());
  //g.fixInfo("Edit->"+bEdit);
  bEdit=true;
	BtnAkt = Btn;
        //g.progInfo("BtnAkt="+Static.hash(BtnAkt));
	if(bFrmEditFirst)
	{
		BtnOk = g.getButton("Uebernehmen","Ok",AL);
		BtnAbbruch = g.getButton("Beenden","Abbruch",AL);
		BtnLoeschen = g.getButton("Loeschen","Loeschen",AL);
                //g.progInfo("Fom="+Fom);
		//FrmEdit=new JDialog((Frame)Fom.thisFrame,g.getBegriff("Dialog","Planungseingabe"),true);
                bFrmEditFirst=false;
		FrmEdit.getContentPane().setLayout(new BorderLayout(2,2));
                JPanel PnlNorth = new JPanel(new BorderLayout(2,2));
                JScrollPane Scroll=new JScrollPane(PnlNorth);
                Scroll.setBorder(new EmptyBorder(new Insets(5,5,4,5)));
                JPanel PnlWest = new JPanel(new GridLayout(0,1,2,2));
		g.addLabel(PnlWest,g.getBegriffS("Show","Name"));
                g.addLabel(PnlWest,getBez(iLoadZeitart));
                if (iLoadFarbe>0)
                  g.addLabel(PnlWest,getBez(iLoadFarbe));
		g.addLabel(PnlWest,g.getBegriffS("Show","Von-Bis"));
                if (VB2 != null)
                  g.addLabel(PnlWest,g.getBegriffS("Show","Von-Bis2"));
		if(iLoadBewilligt>0)
           PnlWest.add(new JLabel());
        else if(iLoadBewilligt3VG>0)
           g.addLabel(PnlWest,getBez(iLoadBewilligt3VG));
		if(iLoadBewilligt3SV>0)
	       g.addLabel(PnlWest,getBez(iLoadBewilligt3SV));
		JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
		//Pnl.setFont(g.fontStandard);
                addOnFlow(Pnl,CboStamm);
                addOnFlow(Pnl,CboZeitart);
                if (iLoadFarbe>0)
                  addOnFlow(Pnl,CboFarbe);
		addOnFlow(Pnl,VB);

                if (VB2 != null)
                {
                  addOnFlow(Pnl,VB2);
                  //VB2.setFont(g.fontStandard);
                }
		if(iLoadBewilligt>0)
		{
			Pnl.add(CbxBewilligt);
			CbxBewilligt.setFont(Transact.fontStandard);
            CbxBewilligt.setText(/*TabSpaltenDaten.posInhalt("Kennung2",""+iLoadBewilligt) ? TabSpaltenDaten.getS("Bezeichnung"):*/g.getShow("Bewilligt"));
		}
        else if(iLoadBewilligt3VG>0)
          Pnl.add(EdtStatus);
        if(iLoadBewilligt3SV>0)
          Pnl.add(EdtStatusSV);
        JTabbedPane TP_Memo=null;
		if (iRest>0)
			for(TabSpaltenDaten.posInhalt("Nummer",iRest);!TabSpaltenDaten.eof();TabSpaltenDaten.moveNext())
			{
				String sDatentyp=TabSpaltenDaten.getS("Datentyp");
//				g.fixtestError("Rest"+TabSpaltenDaten.getI("Nummer")+":"+sDatentyp+"/"+TabSpaltenDaten.getS("Bezeichnung"));
				if (sDatentyp.equals("BewStamm"))
				{
					int iBits2=TabSpaltenDaten.getI("bits");
					AUComboList Cbo = new AUComboList(FrmEdit,Fom.getBegriff(),AbfDaten.iBegriff,TabSpaltenDaten.getI("Kennung2"),TabSpaltenDaten.getI("STT"),g,TabSpaltenDaten.getI("Filter"),
						(iBits2&(Global.cstKeinAutoDate*0x10000+Abfrage.cstKeinAutoDate))==0,(iBits2 & (Global.cstAlways * 0x10000)) == 0,true,false);
					Cbo.setFont(Transact.fontStandard);
					//Cbo.setPreferredSize(new java.awt.Dimension(250,16));
                    Cbo.setEnabled((iBits2&Abfrage.cstEditierbar)>0);
					TabSpaltenDaten.setInhalt("Component",Cbo);
					if ((TabSpaltenDaten.getI("bits")&Abfrage.cstUnsichtbar)==0)
					{
						g.addLabel(PnlWest,TabSpaltenDaten.getS("Bezeichnung"));	
						addOnFlow(Pnl,Cbo);
						//Pnl.add(Cbo);
					}
				}
                else if (sDatentyp.equals("BewBoolean"))
                {
                        int iBits2=TabSpaltenDaten.getI("bits");
                        //AUComboList Cbo = new AUComboList(FrmEdit,Fom.getBegriff(),AbfDaten.iBegriff,TabSpaltenDaten.getI("Kennung2"),TabSpaltenDaten.getI("STT"),g,TabSpaltenDaten.getI("Filter"),
                        //        (iBits2&(Global.cstKeinAutoDate*0x10000+Abfrage.cstKeinAutoDate))==0,(iBits2 & (Global.cstAlways * 0x10000)) == 0,true);
                        AUCheckBox Cbx= new AUCheckBox(TabSpaltenDaten.getS("Bezeichnung"));
                        Cbx.setFont(Transact.fontStandard);
                        Cbx.setEnabled((iBits2&Abfrage.cstEditierbar)>0);
                        TabSpaltenDaten.setInhalt("Component",Cbx);
                        if ((TabSpaltenDaten.getI("bits")&Abfrage.cstUnsichtbar)==0)
    					{
                          g.addLabel(PnlWest,"");
                          Pnl.add(Cbx);
    					}
                }
                else if (sDatentyp.equals("Text"))
				{
					AUTextArea memo=new AUTextArea(g,0);
					memo.setFont(Transact.fontStandard);
					TabSpaltenDaten.setInhalt("Component",memo);
                    if ((TabSpaltenDaten.getI("bits")&Abfrage.cstUnsichtbar)==0)
                    {
//                      GroupBox GP = new GroupBox(TabSpaltenDaten.getS("Bezeichnung"));
//                      GP.add(memo);
//                      JScrollPane Scroll2 = new JScrollPane(GP);
//                      Scroll2.setBorder(new EmptyBorder(new Insets(4, 5, 4, 5)));
                    	if (TP_Memo==null)
                    	{
                    		TP_Memo=new JTabbedPane();
                    		FrmEdit.getContentPane().add("Center", TP_Memo);
                    	}
                    	TP_Memo.add(TabSpaltenDaten.getS("Bezeichnung"),memo);
                    }
				}
				else
				{
					JLabel Lbl=new JLabel();
					Lbl.setFont(Transact.fontStandard);
					TabSpaltenDaten.setInhalt("Component",Lbl);
					if ((TabSpaltenDaten.getI("bits")&Abfrage.cstUnsichtbar)==0)
					{
					  g.addLabel(PnlWest,TabSpaltenDaten.getS("Bezeichnung"));
					  Pnl.add(Lbl);
					}
					//Static.printError("Planung: Datentyp "+sDatentyp+" wird nicht unterstützt!");
				}
			}

		//PnlWest.setFont(g.fontTitel);
		//Pnl.setFont(g.fontStandard);
		//g.progInfo("Font setzen");

		PnlNorth.add("West",PnlWest);
		PnlNorth.add("Center",Pnl);
		FrmEdit.getContentPane().add("North",Scroll);
		Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,3,3));
                Pnl.setBorder(new EmptyBorder(new Insets(4,5,5,5)));
		Pnl.add(BtnLoeschen);
		Pnl.add(BtnOk);
		Pnl.add(BtnAbbruch);
		FrmEdit.getContentPane().add("South",Pnl);
        FrmEdit.setSize(420*Fom.iFF/100,350*Fom.iFF/100);
		//FrmEdit.pack();
                Static.centerComponent(FrmEdit,Fom.thisFrame);
		CboZeitart.getComboBox().getComboSortEditor().addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
			}
			public void keyReleased(KeyEvent e)
			{
			}
			public void keyTyped(KeyEvent e)
			{
				char cKey=e.getKeyChar();
				if(cKey==13 || cKey==10)
				{
					e.consume();
					EditOk(true);
				}
			}
		});
                CboZeitart.getComboBox().addItemListener(new ItemListener ()
                {
                        public void itemStateChanged(ItemEvent ev)
                        {
                                if(ev.getStateChange() == ItemEvent.SELECTED)
                                        checkBewilligbar(CboZeitart.getAIC());
                        }
                });

                checkBewilligbar(CboZeitart.getAIC());
		//boolean bSave=!Abfrage.is(AbfDaten.iBits,Abfrage.cstKeinSave);
                /*if(bSave)
			BtnOk.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					EditOk(true);
				}
			});
		BtnAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                          bEdit=false;
                          FrmEdit.setVisible(false);
                          //BtnAkt.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});
		BtnLoeschen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s=CboStamm.getSelectedBezeichnung()+": "+VB;
				if(Loeschen(s))
				{
                                  bEdit=false;
                                  FrmEdit.setVisible(false);
                                  //BtnAkt.setBorder(BorderFactory.createLineBorder(Color.black));
				}
			}
		});*/
	}
        //FrmEdit.pack();
        //iAicStamm=CboStamm.getSelectedAIC();
        //checkEnabled();
        //g.fixInfo("iAicStamm="+iAicStamm+", iMode="+iMode+", bSave="+bSave+", bStammGesperrt="+bStammGesperrt);
        if (iMode==_INFO  && (iBits&cstAuswahl)>0 && bInfoFirst)
        {
          bInfoFirst=false;
          new Message(Message.INFORMATION_MESSAGE,(JFrame)Fom.thisFrame,g).showDialog("Edit-Modus_aktivieren");
        }
        boolean bNotFix=bLoadBewilligtVG_Edt || Btn==null || iLoadBewilligt==iLoadBewilligt3VG || TabButton.posInhalt("Button",Btn) && (TabButton.isNull("VecAIC") || TabButton.getI("Status")!=1);
        boolean bWrite=iMode!=_INFO && bSave && bNotFix;
        BtnOk.setVisible(bWrite);// && !bStammGesperrt);
        if (bWrite)
        	FrmEdit.getRootPane().setDefaultButton(BtnOk);

        BtnLoeschen.setVisible(iMode!=_INFO && !bStammGesperrt && Btn!=null && (bDel && bNotFix || TabButton.posInhalt("Button",Btn) && TabButton.isNull("VecAIC"))); // neue immer löschen lassen
	CboStamm.setEnabled(bLoadStammEdt /*&& bNotFix*/ && bWrite);
	CboZeitart.setEnabled(bLoadZeitartEdt && /*!Schicht() && bNotFix &&*/ bWrite);
        if (iLoadFarbe>0)
          CboFarbe.setEnabled(false);
	VB.setEnabled(bLoadVonBisEdt /*&& bNotFix*/ && bWrite);
	if (VB2 != null) VB2.setEnabled(bLoadVonBisEdt && bWrite);
        /*CbxBewilligt.setEnabled(bLoadBewilligtEdt && bWrite);
	if (EdtStatus!= null)
	{
		//g.fixInfo("EdtStatus.setEnabled:"+bLoadBewilligtEdt+"/"+bWrite);
		EdtStatus.setEnabled(bLoadBewilligtEdt && bWrite);
	}*/
        boolean bZA_Ok=true;
        boolean bAF_Ok=true;
	if(Btn!=null && TabButton.posInhalt("Button",Btn))
	{
          int iStamm=TabButton.getI("Stamm");
          iAicStamm=iStamm;
          checkEnabled();
		CboStamm.setSelectedAIC(iStamm);
                setBtnBorder(Btn);
                //Btn.setBorder(BorderFactory.createLineBorder(Color.RED,2));
                int iPosZ=TabPanel.getPos("AIC_Stamm", iStamm);
                if (iPosZ>-1)
                {
                  //g.fixtestInfo("Zeile="+iPosZ);
                  table.setSelectedCells(new JCCellRange(iPosZ, -1, iPosZ, 0));
                  if((AbfDaten.iBits&Abfrage.cstSynchron)>0)
                        g.setSyncStamm(AbfDaten.iStt,iStamm,AbfDaten.iRolle);
                }
                //if (CboZeitart.getComboBox().contain(ComboSort.Aic,TabButton.getInhalt("Zeitart"))==-1)
                bEdit=false;
                if (CboZeitart.getComboBox().contains(TabButton.getI("Zeitart")))
                  CboZeitart.getComboBox().setSelectedAIC(TabButton.getI("Zeitart"));
                else
                  bZA_Ok=false;
                bEdit=true;
                if (iLoadFarbe>0)
                {
                  if (CboFarbe.getComboBox().contain(ComboSort.Aic,TabButton.getInhalt("Farbe2"))==-1)
                    bAF_Ok = false;
                  else
                    CboFarbe.getComboBox().setSelectedAIC(TabButton.getI("Farbe2"));
                }
                setStatusVG(TabButton.getI("Status"));
                setStatusSV(TabButton.getI("StatusSV"));
		//CbxBewilligt.setSelected(TabButton.getI("Status")==1);
		VB.setValue((Timestamp)TabButton.getInhalt("Von_Neu"),(Timestamp)TabButton.getInhalt("Bis_Neu"));
                if (VB2 != null)
                  VB2.setValue((Timestamp)TabButton.getInhalt("Von2_Neu"),(Timestamp)TabButton.getInhalt("Bis2_Neu"));
		if (iRest>0)
			for(TabSpaltenDaten.moveFirst();!TabSpaltenDaten.eof();TabSpaltenDaten.moveNext())
			{
				Object Obj=TabSpaltenDaten.getInhalt("Component");
				if (Obj!= null)
                                {
                                  if (TabRest.posInhalt(Btn, TabSpaltenDaten.getInhalt("Nummer")))
                                    if (Obj instanceof AUComboList)
                                      ((AUComboList)Obj).getComboBox().setSelectedAIC(TabRest.getI("Neu"));
                                    else if (Obj instanceof AUCheckBox)
                                      ((AUCheckBox)Obj).setSelected(TabRest.getB("Neu"));
                                    else if (Obj instanceof AUTextArea)
                                      ((AUTextArea)Obj).setText(TabRest.getM("Neu"));
                                    else
                                      ((JLabel)Obj).setText(TabRest.getM("Neu"));
                                      //Static.printError("Planung mit " + Static.className(Obj) + " nicht möglich!");
                                  else
                                  if (Obj instanceof AUComboList)
                                    ((AUComboList)Obj).getComboBox().setSelectedAIC(0);
                                  else if (Obj instanceof AUCheckBox)
                                    ((AUCheckBox)Obj).setSelected(false);
                                  else if (Obj instanceof AUTextArea)
                                    ((AUTextArea)Obj).setText("");
                                  else
                                    Static.printError("Planung mit " + Static.className(Obj) + " nicht möglich!");
                                  boolean bEditMom=bWrite && (TabSpaltenDaten.getI("bits")&Abfrage.cstEditierbar)>0;
                                  g.fixtestInfo("setze "+TabSpaltenDaten.getS("Bezeichnung")+" ("+Obj.getClass().getName()+") auf "+bEditMom);
                                  if (Obj instanceof AUComboList)
                                    ((AUComboList)Obj).setEnabled(bEditMom);//setEditable(bEditMom);
                                  else if (Obj instanceof AUCheckBox)
                                    ((AUCheckBox)Obj).setEditable(bEditMom);
                                  else if (Obj instanceof AUTextArea)
                                    ((AUTextArea)Obj).setEditable(bEditMom);
                                  else
                                	  g.fixtestInfo("  ***  setEditable nicht setzbar bei "+Static.print(Obj));
                                }
                                //else
                                //  Static.printError("Restspalte "+TabSpaltenDaten.getInhalt("Nummer")+" enthält nichts");
			}
	}
        else
        {
          VB.setValue(TSZeitraumVon,TSZeitraumBis);
          //VB.getVonEditor().setDate(TSZeitraumVon);
          //VB.getBisEditor().setDate(TSZeitraumBis);
        }
        checkBewilligbar(CboZeitart.getAIC());
	//if (bAuto)
	//	CboZeitart.getComboBox().requestFocus();
        FrmEdit.toFront();
	if(bZA_Ok && bAF_Ok && (iLoadBewilligt==0 || bLoadBewilligtVG_Edt || Btn==null || TabButton.getI("Status")==0)
           || !BtnOk.isEnabled() && !BtnLoeschen.isEnabled())
        {
          FrmEdit.setVisible(true);
          VB.getVonEditor().requestFocus();
        }
	else
		new Message(Message.WARNING_MESSAGE,(JFrame)Fom.thisFrame,g).showDialog("keineBerechtigung2");
}

private void EditOk(boolean bRest)
{
	//g.fixtestInfo("EditOk:"+CboStamm.getSelectedAIC()+"/"+VB);
        if (BtnInfo!=null) BtnInfo.setEnabled(false);
	if(CheckTime(VB.getVon(),VB.getBis(),CboStamm.getSelectedAIC()) && (VB2==null || VB2.isValid2()))
	{
		//g.progInfo("CheckTime");
		if(TabPanel.posInhalt("AIC_Stamm",CboStamm.getSelectedAIC()))
		{
			//g.progInfo("TabPanel.posInhalt");
			Timestamp TSVon=new Timestamp(VB.getVon().getTime());
			Timestamp TSBis=new Timestamp(VB.getBis().getTime());
                        int iZA=CboZeitart.getAIC();
                        int iFarbe=iLoadFarbe>0 ? CboFarbe.getAIC():0;
			double dDauer=getDauer(CboStamm.getSelectedAIC(),TSVon,TSBis.getTime()+(VB2 != null && VB2.getVon() != null ? VB2.getBis().getTime()-VB2.getVon().getTime():0),iZA);
                        Timestamp TSVon2=VB2 != null && VB2.getVon() != null ? new Timestamp(VB2.getVon().getTime()):null;
                        Timestamp TSBis2=VB2 != null && VB2.getBis() != null ? new Timestamp(VB2.getBis().getTime()):null;
                        Timestamp TSVon0=TSVon;
                        Timestamp TSBis0=TSBis;
                        //if (bVoll || Schicht())
                        if (Schicht() || bVoll && (TSBis.getTime()-TSVon.getTime())<23*3600*1000)
                        {
                          DateWOD dt = new DateWOD(TSVon);
                          dt.setTimeZero();
                          TSVon0 = dt.toTimestamp();
                          dt.tomorrow();
                          TSBis0 = dt.toTimestamp();
                          if (!bRest)
                          {
                            TSVon=null;
                            TSBis=null;
                            TSVon2=null;
                            TSBis2=null;
                          }
                        }
                        boolean bSOK=!bRest; // Status OK -> wird übernommen
                        if (!bSOK)
                        {
                          bSOK=!CboZeitart.Modified() && !CboStamm.Modified() && !VB.Modified();
                        }
                        //g.progInfo("vor :"+TSVon0+"/"+TSBis0+"//"+TSVon+"/"+TSBis);
                        int iStatus=EdtStatus!= null ? (EdtStatus.Modified() || bSOK ?  EdtStatus.getNr(EdtStatus.getAic()):0):bLoadBewilligtVG_Edt&&CbxBewilligt.isSelected() ? 1:0;
                        int iStatusSV=iStatus!=2 && EdtStatusSV!= null && bSOK ? EdtStatusSV.getNr(EdtStatusSV.getAic()):0;
                        addComponent((JPanel)TabPanel.getInhalt("Panel"),BtnAkt,CboStamm.getSelectedAIC(),iZA,iFarbe,TSVon0,TSBis0,TSVon,TSBis,TSVon2,TSBis2,dDauer,iStatus,iStatusSV,null,0,true);
                        //g.fixtestInfo("iZA="+iZA+", BtnAkt="+BtnAkt);
                        setBtnBorder(BtnAkt);
			if (bRest && iRest>0)
				for(TabSpaltenDaten.moveFirst();!TabSpaltenDaten.eof();TabSpaltenDaten.moveNext())
				{
					Object Obj=TabSpaltenDaten.getInhalt("Component");
					if (Obj!= null && ((TabSpaltenDaten.getI("bits")&Abfrage.cstUnsichtbar)==0))
					{
						g.fixtestInfo(TabSpaltenDaten.getS("Nummer")+":"+Static.className(Obj));
						if (Obj instanceof JLabel || Obj instanceof Combo)
							;
						else if(TabRest.posInhalt(BtnAkt,TabSpaltenDaten.getInhalt("Nummer")))
							TabRest.setInhalt("Neu",Obj instanceof AUComboList ? (Object)new Integer(((AUComboList)Obj).getComboBox().getSelectedAIC()):
                                                            Obj instanceof AUCheckBox ? ((AUCheckBox)Obj).isSelected():Obj instanceof AUTextArea ? ((AUTextArea)Obj).getValue():((JLabel)Obj).getText());
						else
						{
							TabRest.addInhalt("Button",BtnAkt);
							TabRest.addInhalt("Nr",TabSpaltenDaten.getInhalt("Nummer"));
							TabRest.addInhalt("Neu",Obj instanceof AUComboList ? (Object)new Integer(((AUComboList)Obj).getComboBox().getSelectedAIC()):
                                                            Obj instanceof AUCheckBox ? ((AUCheckBox)Obj).isSelected():((AUTextArea)Obj).getValue());
							TabRest.addInhalt("Alt",null);
						}
					}
				}
                              //TabRest.showGrid("Rest");

		}
                makeSum(CboStamm.getSelectedAIC());
                bEdit=false;
		if (FrmEdit != null)
                {
                  FrmEdit.setVisible(false);
                  //BtnAkt.requestFocus();
                  //g.fixtestInfo("BtnAkt nun fokusiert");
                  //BtnAkt.setBorder(BorderFactory.createLineBorder(Color.black));
                }
	}
}

private boolean CheckTime(Date dtVon,Date dtBis,int iStamm)
{
  //g.fixtestInfo("CheckTime "+iStamm+":"+dtVon+"/"+dtBis+","+TSZeitraumVon.getTime()+"/"+TSZeitraumBis.getTime()+":"+Static.hash(BtnAkt));
  //long lClock2=Static.get_ms();
  if (dtVon==null || dtBis==null)
    return false;
  long lVon=dtVon.getTime();
  long lBis=dtBis.getTime();
  if (lBis<lVon)
    return false;
  if (Schicht())
  {
    DateWOD DW=new DateWOD(lVon);
    //g.progInfo("CheckTime: DW="+DW+"/"+lVon+"/"+lBis);
    DW.setTimeZero();
    lVon=DW.getTimeInMilli();
    DW.tomorrow();
    lBis=DW.getTimeInMilli();
    //g.progInfo("CheckTime: DW="+DW+"/"+lVon+"/"+lBis);
  }
	if (lBis<=lVon)
	{
		new Message(Message.WARNING_MESSAGE,(JFrame)Fom.thisFrame,g).showDialog("ungueltig");
		return false;
	}
        if(bMoa && Check.Monatsabschluss(g/*,Fom.getBegriff()*/,iAIC_Bewegungstyp,iStamm,new Zeit(lVon,""),null))
        {
                new Message(Message.WARNING_MESSAGE,(JFrame)Fom.thisFrame,g).showDialog("Monatsabschluss");
                return false;
        }
        //if (Schicht())
        //  return true;
	boolean bOk=lVon<TSZeitraumBis.getTime()&&lVon>=TSNow.getTime()&&lBis<=TSZeitraumBis.getTime()&&lBis>TSNow.getTime();
	if(!bOk)
	{
          if (!Schicht())
            new Message(Message.WARNING_MESSAGE,FrmEdit,g,10).showDialog("Out of Range");
		return bOk;
	}
	//int iZA1=0;
	//Object Obj=null;
        TabButton.push();
        //if (!CboZeitart.isActive())
	 for(TabButton.moveFirst();bOk&&!TabButton.eof();TabButton.moveNext())
	 {
		//DateWOD DWVon = new DateWOD((Timestamp)TabButton.getInhalt("Von_Neu"));
		//DateWOD DWBis = new DateWOD((Timestamp)TabButton.getInhalt("Bis_Neu"));
                Timestamp TSVon = (Timestamp)TabButton.getInhalt("Von_Neu");
                Timestamp TSBis = (Timestamp)TabButton.getInhalt("Bis_Neu");
                if (Schicht() && TSVon != null)
                {
                  DateWOD DW=new DateWOD(TSVon);
                  DW.setTimeZero();
                  TSVon=DW.toTimestamp();
                  DW.tomorrow();
                  TSBis=DW.toTimestamp();
                }
                if(TSVon!=null&&TSBis!=null&&TabButton.getI("Stamm")==iStamm&&!TabButton.getInhalt("Button").equals(BtnAkt))
                  bOk=lVon>=TSBis.getTime()||lBis<=TSVon.getTime();
                 //if (!bOk)
                 //{
                   //iZA1=TabButton.getI("Zeitart");
                   //if (Schicht())
                   //  Obj=TabButton.getInhalt("Button");
                 //}
		//if(!DWVon.isNull() && !DWBis.isNull() && TabButton.getI("Stamm")==iStamm&&!TabButton.getInhalt("Button").equals(BtnAkt))
		//	bOk=lVon>=DWBis.getTimeInMilli()||lBis<=DWVon.getTimeInMilli();
	 }
	TabButton.pop();
	if(!bOk)
        {
          table.repaint();
          /*if (new Message(Message.YES_NO_OPTION, (JFrame)Fom.thisFrame, g).showDialog("Ueberschreiben",new String[] {TabZeitart.getKennung(iZA1)}) == Message.YES_OPTION)
          {
            TabButton.push();
            for (TabButton.moveFirst();!TabButton.eof(); TabButton.moveNext())
            {
              Timestamp TSVon = (Timestamp)TabButton.getInhalt("Von_Neu");
              Timestamp TSBis = (Timestamp)TabButton.getInhalt("Bis_Neu");
              bOk=Obj==null || !Obj.equals(TabButton.getInhalt("Button"));
              if (bOk && TSVon != null && TSBis != null && TabButton.getI("Stamm") == iStamm && !TabButton.getInhalt("Button").equals(BtnAkt))
                bOk = lVon >= TSBis.getTime() || lBis <= TSVon.getTime();
              if (!bOk)
              {
                ((JPanel)TabPanel.getInhalt("Panel")).remove((JButton)TabButton.getInhalt("Button"));
                TabButton.setInhalt("Von_Neu", null);
                TabButton.setInhalt("Bis_Neu", null);
                TabButton.setInhalt("Dauer_Neu", null);
              }
            }
            TabButton.pop();
            //makeSum();
            bOk=true;
          }*/
          new Message(Message.WARNING_MESSAGE, (JFrame)Fom.thisFrame, g).showDialog("Ueberschneidung");
        }
	//g.clockInfo("CheckTime:"+bOk,lClock2);
	return bOk;
}

/*private double getAZ(long li)
{
  double dAb=TabFilter.getF("E"+iLoadFilterPauseAb);
  double dDauer=TabFilter.getF("E"+iLoadFilterPausedauer);
  double dDif=li-dDauer;
  return li<=dAb ? li:dDif>dAb ? dDif:TabFilter.getB("E"+iLoadFilterPauseGleitend) ? dAb:dDif;
}*/

private double getDauer(int iStamm,Timestamp TSvon,long lBis,int iZA)
{

	double d=0.0;
	double dMax=(lBis-TSvon.getTime())/1000.0;
        //g.progInfo("getDauer:"+iStamm+"/"+TSvon+"/"+iAbfFilter+"/"+TabFilter);
	if(AbfFilter == null || TabFilter == null || Schicht())
        {
          d = dMax;
          if (d>22*3600)
          {
            DateWOD DW=new DateWOD(lBis);
            double d1=DW.getAbsSeconds();
            g.testInfo("bis="+d1+"/"+DW.getAllSeconds());
            DW=new DateWOD(TSvon);
            d=d1-DW.getAbsSeconds();
            g.testInfo("von="+DW.getAbsSeconds()+"/"+DW.getAllSeconds());
            g.testInfo("->"+Static.Round6(d/3600)+" h");
          }
          return d;
        }
	if(TabFilter.posInhalt(new Integer(iStamm),new DateWOD(TSvon).setTimeZero().toTimestamp()))
	{
		DateWOD DW=new DateWOD(TSvon);
		do
		{
			//g.progInfo(TabFilter.getF("E"+iLoadFilterIstZeit)+" / "+((lBis-DW.getTimeInMilli())/1000.0));
                        /*if (AZ() && iZA>0 && TabZeitart.posInhalt("aic_stamm",iZA))
                        {
                          long ls=(long)TabFilter.getF("E"+iLoadFilterIstZeit);
                          long li=(lBis-DW.getTimeInMilli())/1000;
                          d+=TabZeitart.getB("pay") ? TabZeitart.getB("ctrl") ? iLoadFilterPauseAb==0 ? (li>6*3600+29 ? li-1800:li):getAZ(li):Math.min(ls,li):0;
                        }
			else*/ if(TabFilter.getS("E"+iLoadFilterZeitart).equals(""))
				d+=Math.min(TabFilter.getF("E"+iLoadFilterIstZeit),(lBis-DW.getTimeInMilli())/1000.0);
			//g.progInfo(TabFilter.getI("V"+iLoadFilterStamm)+"/"+TabFilter.getTimestamp("E"+iLoadFilterGueltig)+"->"+d);
			DW.tomorrow();
			TabFilter.moveNext();
		}
		while(!TabFilter.eof()&&TabFilter.getI("V"+iLoadFilterStamm)==iStamm&&DW.getTimeInMilli()<lBis);
	}
	else
		if (TabFilter.bInsert)
			Static.printError("Planung.getDauer(): Nicht gefunden("+iStamm+"/"+TSvon+")!!!");
		else
                {
                  d = dMax;
                }
	//g.progInfo("getDauer:"+d);
	return d;
}

public String getRolle()
{
  return Abfrage.Rolle(iRolle);
}

private boolean gesperrt(int iStamm,boolean bMeldung)
{
  boolean b= TabSperre!=null && !TabSperre.getVecSpalteI("Stamm").contains(iStamm);
  if (iStamm==0)
    b = b && TabSperre.isEmpty();
  if (bMeldung && b)
    new Message(Message.INFORMATION_MESSAGE+Message.SHOW_TAB, Fom.thisJFrame(), g).showDialog("Sperre",showTabSperren(iStamm));
  return b;
}

public void checkEnabled()
{
  if ((iBits&cstAuswahl)>0)
  {
    bStammGesperrt=gesperrt(iAicStamm,false);
    if (MnuNeu != null) MnuNeu.setEnabled(!bStammGesperrt);
    if (BtnNeu != null) BtnNeu.setEnabled(!bStammGesperrt);
    if (BtnSave != null) BtnSave.setEnabled(bSave && !bStammGesperrt);
    if (MnuSave != null) MnuSave.setEnabled(bSave && !bStammGesperrt);
    if (MnuPaste != null) MnuPaste.setEnabled(!bStammGesperrt);
    if (BtnOk != null) BtnOk.setEnabled(iMode!=_INFO && bSave && !bStammGesperrt);
  }
}

public void setEnabled(boolean b)
      {
        table.setBackground(b ? Global.ColPlanung:Global.ColHide);
        table.setEnabled(b);
        boolean bNeu=b && ((iBits&cstAuswahl)==0 || !gesperrt(iAicStamm,false));
        if (MnuNeu != null)
          MnuNeu.setEnabled(bNeu);
        if (BtnNeu != null)
        {
          BtnNeu.setEnabled(bNeu);
          //BtnNeu.setBackground(b ? g.ColPlanung : g.ColHide);
          BtnSave.setEnabled(bNeu);
          if (MnuSave != null) MnuSave.setEnabled(bNeu);
          //BtnSave.setBackground(b ? g.ColPlanung : g.ColHide);
          BtnRefresh.setEnabled(b);
          BtnSuchen.setEnabled(b);
          BtnDruck.setEnabled(b);//g.sZeitart.equals("Tag"));
          BtnZRm2.setEnabled(b);
          BtnZRm.setEnabled(b);
          BtnTag.setEnabled(b);
          BtnWoche.setEnabled(b);
          BtnMonat.setEnabled(b);
          BtnZRp.setEnabled(b);
          BtnZRp2.setEnabled(b);
          BtnJetzt.setEnabled(b);
          //BtnRefresh.setBackground(b ? g.ColPlanung : g.ColHide);
          //BtnHistory.setEnabled(b);
          //BtnHistory.setBackground(b ? g.ColPlanung : g.ColHide);
          PnlTB.setBackground(b ? Global.ColPlanung : Global.ColHide);
        }
      }

// add your data members here
public static final int cstNotSelf=	0x0001;	// 00
public static final int cstBehind=	0x0002;	// 01
public static final int cstDays=	0x0004;	// 02
public static final int cstWeeks=	0x0008;	// 03
public static final int cstAZ=          0x0010;	// 04 alter Dienstplan
public static final int cstStunden=     0x0020;	// 05
public static final int cstBewilligung= 0x0040; // 06
public static final int cstSchichten=   0x0080; // 07
public static final int cstAndereFarbe= 0x0100; // 08
public static final int cstKRNS=        0x0200; // 09 kein Refresh nach Speichern
//public static final int cst3W=        0x0400; // 10
public static final int cstZR=          0x0800; // 11 Zeitraumleiste in Toolbar anzeigen
public static final int cstFTT=         0x1000; // 12 Feiertag in Titelzeile färben
public static final int cstAuswahl=     0x2000; // 13 ToogleButtons mit Info, Edit, Schicht, verschieben
public static final int cstMultiP=      0x4000; // 14 mehrere Zeilen anwählbar
public static final int cstTGM=         0x8000; // 15 Tagesmuster-Zuordnung -> keine Bewilligung
public static final int cstPveil=      0x10000; // 16 bestimmte Zeitarten in Planung verschleiern 

private static final int PVER=1;
private static final int ASK=8;
private static final int MOVE=  0x0010;	// 04 bewegen lassen
//private static final int OLD=   0x0020;// 05 altes anzeigen (mit V 5.11 entfernt)
private static final int VOLL=  0x0040; // 06 über die volle Breite des Tages
private static final int VBT=   0x0080; // 07 von-bis-Text auf Knopf
private static final int PAZ=   0x0100; // 08 geplante Arbeitszeit
private static final int IST=   0x0200; // 09 geplante Ist-Zeit
private static final int SOLL=  0x0400; // 10 Sollzeit
private static final int DIFF=  0x0800; // 11 Ist-Soll (Planungsaldo)
private static final int PAUSE= 0x1000; // 12
//private static final int KST=   0x2000; // 13 erweiterte Bezeichnung (mit V 5.11 entfernt)
private static final int SUM=   0x4000; // 14 Gesamtsummenzeile wird angezeigt
private static final int BEGINN=0x8000; // 15 Suche von Anfang der Bezeichnung (Mitarbeiter)
private static final int TMZ=  0x10000; // 16 Tagesmusterzählung (open, core, close)
private static final int VA=   0x20000; // 17 vorrübergehender Austritt
private static final int SALDO=0x40000; // 18 Gesamtsaldo
private static final int MZ=   0x80000; // 19 Mehrzeiligkeit
private static final int PPS= 0x100000; // 20 prod. Planstunden
private static final int ESUM=0x200000; // 21 Einzelsumme pro Spalte
private static final int TAG =0x400000; // 22 fix auf Tagesdarstellung
private static final int WOCHE=0x800000;// 23 fix auf Wochendarstellung
private static final int KZS=0x1000000; // 24 keine Zwischensumme
private static final int SES=0x2000000; // 25 nach speichern bearbeitete gesperrt lassen -> Edit-Modus beibehalten

private static final int DREHEN=0x0001;
private static final int MEMO2 =0x0002;
private static final int MEMO  =0x0004;
private static final int DAUSW =0x0008; // Druckerauswahl
private static final int FARBE =0x0010; // Farb-Druck

private static final int _INFO=0;
private static final int _EDIT=1;
private static final int _SCHICHT=2;
private static final int _FEHLZ=3;
private static final int _IST=4;
private static final int _MOVE=5;

private boolean bAZ=false;
private boolean bPPS=false;
private boolean bIst=false;
private boolean bSoll=false;
private boolean bDiff=false;
private boolean bSaldo=false;

private int iPosA=-1; // Position für Arbeitszeit
private int iPosP=-1; // Position für prod. Planstunden
private int iPosI=-1; // Position für Ist
private int iPosS=-1; // Position für Soll
private int iPosD=-1; // Position für Diff
private int iPosO=-1; // Position für Saldo

private Global g;
private Formular Fom;
private Point PntPanel=null;
private Point PntStart=null;
private SQL QrySave=null;
private boolean bPaint=false;

private Vector<Integer> VecStamm=new Vector<Integer>();
private Tabellenspeicher TabSperre=null;
public Vector VecNeu=null;
private int iPlanung;
public String sBez=null;
private ShowAbfrage AbfDaten=null;
private ShowAbfrage AbfFilter=null;
private ShowAbfrage AbfSonst=null;
private ShowAbfrage AbfWeb=null;

private Timestamp TSNow=null;
private Timestamp TSZeitraumVon=null;
private Timestamp TSZeitraumBis=null;
private String sZeitartMom=null;

private String sVBFormat;
private int iVBStamm=0;

public JCTable table;
public int iAicStamm=0;

private Tabellenspeicher TabSpaltenDaten;
private Tabellenspeicher TabSpaltenFilter;
private Tabellenspeicher TabSpaltenSonst;
private Tabellenspeicher TabDaten;
private Tabellenspeicher TabFilter=null;
private Tabellenspeicher TabSonst=null;
private Tabellenspeicher TabButton;
private Tabellenspeicher TabPanel;
private Tabellenspeicher TabZeitart;
private Tabellenspeicher TabZeitart2=null;
private Tabellenspeicher TabFarbe=null;
private Tabellenspeicher TabRest;
private int iRest=0;

private int iAIC_Bewegungstyp=0;

private int iZAcopy=0;
private VonBis VBcopy=null;
private VonBis VB2copy=null;

// Eigenschafts-AICs
private int iLoadStamm=0;
private int iLoadDatum=0;
private int iLoadVonBis=0;
private int iLoadVonBis2=0;
private int iLoadZeitart=0;
private int iLoadFarbe=0;
private int iLoadBewilligt=0;
private int iLoadBewilligt3VG=0;
private int iLoadBewilligt3SV=0;
private int iLoadFilterStamm=0;
private int iLoadFilterGueltig=0;
private int iLoadFilterIstZeit=0;
private int iLoadFilterZeitart=0;
private String sLoadBewBew=null;

private int iWebStamm=0;
private int iWebZeitart=0;
private int iWebVonBis=0;
private int iWebBew1=0;
private int iWebBew2=0;
private int iWebMemo=0;
private int iWebMemo2=0;
private int iLoadMemoNr=0;
private int iLoadMemo2Nr=0;
private int iWebErsatz=0;
private int iLoadErsatzNr=0;
/*private int iLoadFilterSaldo=0;
private int iLoadFilterOffen1=0;
private int iLoadFilterOffen2=0;
private int iLoadFilterAnwesend=0;
private int iLoadFilterZeitart2=0;
private int iLoadFilterPauseAb=0;
private int iLoadFilterPausedauer=0;
private int iLoadFilterPauseGleitend=0;*/
private int iLoadSoll=0;
private int iLoadSaldo=0;
private String sLoadKSt=null;
private String sLoadFarbe=null;
private int iEigGruppe=0;

private int iRolle=0;

//private int iStammArbeitszeit=0;

private boolean bLoadStammEdt=false;
private boolean bLoadVonBisEdt=false;
private boolean bLoadZeitartEdt=false;
private boolean bLoadFarbeEdt=false;
private boolean bLoadBewilligtVG_Edt=false; // Bewilligung durch Vorgesetzten
private boolean bLoadBewilligtSV_Edt=false; // Bewilligung durch Stellvertreter
private boolean bMoa=false;

private JDialog FrmEdit;
private JDialog DlgP;
private boolean bFrmEditFirst=true;

private int iAnz=0;
private int iTage=0;
private double dFaktor=0;

private JButton BtnAbbruch;
private JButton BtnOk;
private JButton BtnLoeschen;

private JButton BtnNeu;
private JButton BtnSave;
private JButton BtnRefresh;
private JButton BtnSuchen;
private JButton BtnDruck;
//private JButton BtnHistory;
private JButton BtnZRm2;
private JButton BtnZRm;
// Zeitraum
private JToggleButton BtnTag;
private JToggleButton BtnWoche;
private JToggleButton BtnMonat;
// Tätigkeit
private JToggleButton BtnInfo;
private JToggleButton BtnEdit;
private JToggleButton BtnSchicht;
private JToggleButton BtnFehlz;
private JToggleButton BtnIst;
private JToggleButton BtnMove;
private JButton BtnZRp;
private JButton BtnZRp2;
private JButton BtnJetzt;
//private JButton BtnHide;
private JButton BtnAkt;
private JButton BtnLast=null;

private String sArt=null;

private ComboSort CboStamm;
private AUComboList CboZeitart;
private AUComboList CboFarbe;
private VonBisEingabe VB;
private VonBisEingabe VB2=null;
private AUCheckBox CbxBewilligt=new AUCheckBox();
private RadioAuswahl EdtStatus=null;
private RadioAuswahl EdtStatusSV=null;
private int iSpaltenbreite = 50;
private int iZeilenhoehe = 0;
private int iSpaltenOri;
private int iVorlage=0;
private int iLayout=0;
private int iDBits=0;
//private int iAbfDaten=0;
//private int iAbfFilter=0;
//private int iAbfSonst=0;
private int iBits;
private boolean bReady=false;
//private boolean bBisOk=false;
//private int iStammLast=-1;
//public static boolean bFilter=true;
private JLabel LblVor=new JLabel("");
private Timestamp TSBisLast=null;
//private int iFilter=0;
private long lClock=Static.get_ms();
private Vector VecStunde;
private Vector<JLabel> VecAZ;
private ActionListener AL=null;
private JToolBar PnlTB;

private boolean bMZ=false;
private boolean bAsk=true;
private boolean bMove=false;
//private boolean bOld=false;
private boolean bVoll=false;
private boolean bVB=false;
private boolean bkRnS=false;
private boolean bPause=false;
//private boolean bKSt=false;
private boolean bSum=false;
private boolean bBeginn=false;
private boolean bTMZ=false;
private boolean bESum=false;
private boolean bTag=false;
private boolean bWoche=false;
private boolean bVA=false;
private boolean bKZS=false;
private boolean bSES=false;
private int iMovedV=0;
private int iMovedB=0;
private int iMoveMin;
private int iMoveMax;
private long lGenau;
private int iCS=0;
private boolean bEdit=false;
private boolean bStammGesperrt=false;
private boolean bInfoFirst=true;
private int iMode=0;
private Vector<Integer> VecMod=null;

private int iLastZA=-1;
private int iLastAF=-1;
private int iLastBits=0;
private int iLastSB=50;
private int iLastZH=0;

private JPopupMenu popup;
private JMenuItem MnuEdit;
private JMenuItem MnuBack;
private JMenuItem MnuNeu=null;
private JMenuItem MnuDel=null;
private JMenuItem MnuSave=null;
private JMenuItem MnuPaste=null;

//private JCheckBoxMenuItem MnuMove=null;
private JMenu MnuZeitart;
private JMenu MnuZeitart2 = null;
private JMenu MnuZeitart3 = null;
private JMenu MnuZeitart4 = null;
private JMenu MnuZeitart5 = null;
private JMenu MnuZeitart6 = null;
private JMenu MnuFarbe;
private ActionListener AL1=null;
//private Point P=null;
private String sSuchtext="";
private String sDruckText="";
//private int iSuchBits=0;
private int iD=0;
private int iProtokoll=0;

public boolean bSaveMom=false;

private boolean bSave;
private boolean bDel;
private boolean bNew;

//private Color ColMinus=Color.GREEN.darker();
//private Color ColPlus=Color.RED;
private Color ColDefault=Color.BLACK;

private DateWOD DWvon=null;
private DateWOD DWbis=null;
//private int iRefresh=0;
}

