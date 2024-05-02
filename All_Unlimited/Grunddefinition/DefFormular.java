/*
    All_Unlimited-Grunddefinition-Formularerstellung.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
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
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.Zeit;
//import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.DruckEingabe;
import All_Unlimited.Allgemein.Eingabe.EigenschaftsEingabe;
import All_Unlimited.Allgemein.Eingabe.RolleEingabe;
import All_Unlimited.Allgemein.Eingabe.StammEingabe;
import All_Unlimited.Allgemein.Eingabe.ModellEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Hauptmaske.Abfrage;

import javax.swing.JRadioButton;

public class DefFormular extends All_Unlimited.Allgemein.Formular
{
	
//  public static DefFormular get(Global g,int iFom)
//  {
//	  return get(g,iFom,0);
//  }
	
  public static DefFormular get(Global g,int iFom)//,int iArt)
  {
    if (Self==null)
      new DefFormular(g);
    else
      Self.show();
    Self.find(iFom);
    return Self;
  }
  
  private void find(int iFom)
  {
	  g.fixtestInfo("DefFormular.find "+iFom);
//	  if (iFom>0 && TabFormulare.getPos("aic_begriff",iFom)<0)
//	  {
//		  CbxSwing.setSelected(true);
//		  g.fixtestInfo("set Swing1");
//		  fill_Outliner();
//	  }
	    if (iFom==0 || iFom>0 && !TabFormulare.posInhalt("aic_begriff",iFom))
	    {
	    	g.fixtestInfo("nicht gefunden:"+iFom);
	      if (!CbxSwing.isSelected())
	      {
//	    	  g.fixtestInfo("Swing aktiviert");
	        CbxSwing.setSelected(true);
//	        CbxWebFom.setSelected(true);
	        fill_Outliner();
	      }
	      Out.selectNode((JCOutlinerNode)Out.getRootNode().getChildren().elementAt(0),null);	      
	    }
	    else
	    {
	      JCOutlinerNode Nod=iFom>0 ? (JCOutlinerNode)TabFormulare.getInhalt("Node"):null;
	      g.fixInfo("Nod="+Nod);
	      //TabFormulare.showGrid("TabFom");
	      if (Nod==null && !CbxSwing.isSelected() && iFom>0)
	      {
	    	 CbxSwing.setSelected(true);
//	    	 CbxWebFom.setSelected(true);
	    	 fill_Outliner();
	    	 //g.fixtestInfo("set Swing2");
	    	 if (TabFormulare.posInhalt("aic_begriff",iFom))
	    	   Nod=(JCOutlinerNode)TabFormulare.getInhalt("Node");	    	  
	      }
	      if (Nod!=null)
	    	  Static.makeVisible(null/*Self.Out*/, Nod);
	    }
  }

  public static void free()
  {
	if (Self != null)
	{
		Self.g.winInfo("DefFormular.free");
	    Self.thisFrame.dispose();
	    Self=null;
	}
  }

  private DefFormular(Global glob)
	{
		super("Formularerstellung",null,glob);
                Self=this;
		g=glob;
		g.winInfo("DefFormular.create");
                int iPosBG=g.TabBegriffgruppen.getPos("Kennung","Frame");
		GifFormular = iPosBG>=0 ? g.LoadImage(g.TabBegriffgruppen.getS(iPosBG,"Filename")) : null;
		if(GifFormular!=null)
		{
			StyFolderFormular.setItemIcon(GifFormular);
			StyFX.setItemIcon(GifFormular);
			StyFertig.setItemIcon(GifFormular);
			StyWeb.setItemIcon(GifFormular);
		}
		StyFX.setForeground(Global.ColJavaFX);
		StyWeb.setForeground(Global.ColWeb);
		StyFertig.setForeground(Color.BLUE);

		//CboBenutzergruppe = new ComboSort(g);
		//CboBenutzergruppe.fillDefTable("BENUTZERGRUPPE",true);
		CboTyp = new ComboSort(g);
		CboTyp.addItem(g.getBezeichnungS("Begriffgruppe","Formulartyp"),-1,"AIC_CODE");
		CboTyp.addItem(g.getBezeichnungS("Tabellenname","STAMMTYP"),1,"AIC_STAMMTYP");
		CboTyp.addItem(g.getBezeichnung("Tabellenname","BEWEGUNGSTYP"),2,"AIC_BEWEGUNGSTYP");
		//CboTyp.setKein(false);
		CboProg = new ComboSort(g);
		CboProg.fillBegriffTable(g.TabBegriffgruppen.getAic("Programm"),true,true);
		CboArt = new ComboSort(g);
		CboStammtypen = new ComboSort(g);
		CboStammtypen.fillDefTable("Stammtyp",true);
                CboEigenschaft = new EigenschaftsEingabe(g,thisFrame);
		CboEigenschaft.Cbo.fillCbo("select e.aic_eigenschaft,e.kennung,"+g.concat(g.concat(g.AU_Bezeichnungo("Eigenschaft","e"),"' ('"),g.concat("begriff.kennung","')'"))+
                    " bezeichnung from eigenschaft e"+g.join("begriff","e")+" where begriff.kennung='Ampel' or begriff.kennung='BewStamm' and e.aic_stammtyp="+g.iSttLight,"eigenschaft",true,false);
                CboRolle =new RolleEingabe(g,thisFrame);
                CboRolle.fillRolle(-1,true);
                CboStamm = new StammEingabe(thisFrame,g);
                CboAbfrage = new AbfrageEingabe(g,thisFrame,true,false);
                CboAbfrage.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("bits",Abfrage.cstFilter),"Abfrage",true);
                CboModell=new ModellEingabe(g,thisFrame);
                CboModell.fillCboM(-1);

		//CboTyp_itemStateChanged();

		Out.setColumnLabelSortMethod(Sort.sortMethod);
		Out.setRootVisible(false);

		addPopup();
		Build();

		String[] sTitelZeile = {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Rolle"),g.getShow("Art"),g.getShow("Change"),g.getShow("Programm")/*,g.getShow("CSS"),g.getShow("FXML"),g.getShow("Kennzeichen"),g.getShow("Alias"),g.getShow("fertig")*/,"Begriff","Formular"};
		Out.setColumnButtons(sTitelZeile);
		Out.setNumColumns(sTitelZeile.length);

		fill_Outliner();


		/*BtnZeigen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				new Formular(((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(2)).intValue(),g).show();
			}
		});

		BtnDarstellung.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				//long lClock = Static.get_ms();
				//g.printInfo("Darstellungsübergabe: "+((Vector)Out.getSelectedNode().getUserData()).elementAt(0));
				Darstellung.start2(((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(0)).intValue(),Bits(),g).show2();
				//g.printInfo("Darstellung"+(Static.get_ms()-lClock));
			}
		});*/

		BtnNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Memo.setText("");
                Tooltip.setText("");
				if (BtnBildSw!=null)
	            	BtnBildSw.Delete();
	            if (BtnBildFX!=null)
	            	BtnBildFX.Delete();
                TxtDefBezeichnung.setText("");
                TxtBezeichnung.setText("");
				TxtKennung.setText("");
                CbxNotResizeable.setSelected(false);
				CbxNotClose.setSelected(false);
				CbxAutoSave.setSelected(false);
				//CboBenutzergruppe.setSelectedAIC(0);
				bNew=true;
				bCopy=false;
				EnableButtons();
			}
		});

		BtnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Delete();
			}
		});

		BtnCopy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				bNew=true;
				bEdit=false;
                                BtnOk_actionPerformed();

				bNew=false;
				bCopy=false;
				EnableButtons();
			}
		});

		BtnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnOk_actionPerformed();
			}
		});

		BtnAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				bNew=false;
				bEdit=false;
				bCopy=false;
				Out_itemStateChanged();
			}
		});

		BtnBeenden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				hide();
			}
		});

		KeyListener KeyList= new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
			}
			public void keyPressed(KeyEvent e)
			{
			}
			public void keyReleased(KeyEvent e)
			{
				checkEdit();
			}
		};
                Memo.Edt.addKeyListener(KeyList);
                Tooltip.Edt.addKeyListener(KeyList);
		TxtBezeichnung.addKeyListener(KeyList);
        TxtDefBezeichnung.addKeyListener(KeyList);
		TxtKennung.addKeyListener(KeyList);
//		TxtCss.addKeyListener(KeyList);
		TxtFxml.addKeyListener(KeyList);
//		TxtKennzeichen.addKeyListener(KeyList);
		TxtAlias.addKeyListener(KeyList);

		/*CboBenutzergruppe.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange() == ev.SELECTED)
				{
					checkEdit();
				}
			}
		});*/

		CboTyp.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
                          //g.progInfo("State="+ev.getStateChange()+"/"+ev.SELECTED+", Kennung="+CboTyp.getSelectedKennung());
                          if(ev.getStateChange() == ItemEvent.SELECTED)
				{
					CboTyp_itemStateChanged();
					checkEdit();
				}
			}
		});

                ItemListener ItemList=new ItemListener ()
                {
                        public void itemStateChanged(ItemEvent ev)
                        {
                                if(ev.getStateChange() == ItemEvent.SELECTED)
                                {
                                        checkEdit();
                                }
                        }
                };
		CboArt.addItemListener(ItemList);
		CboStammtypen.addItemListener(ItemList);
		CboProg.addItemListener(ItemList);
                CboEigenschaft.Cbo.addItemListener(ItemList);
                CboAbfrage.Cbo.addItemListener(ItemList);
                CboRolle.Cbo.addItemListener(ItemList);
                CboModell.Cbo.addItemListener(ItemList);
                CboStamm.getComboBox().addItemListener(ItemList);

		Out.addItemListener(new JCItemListener()
		{
			public void itemStateChanged(JCItemEvent ev)
			{
				if(ev.getStateChange()==ItemEvent.SELECTED)
					Out_itemStateChanged();
			}
		});

		Out.addActionListener(new JCActionListener()
		{
			public void actionPerformed(JCActionEvent ev)
			{
                                JCOutlinerNode NodeSelected = Out.getSelectedNode();
				if(NodeSelected!=null && NodeSelected.getLevel()==3 )
				{
					long lClock = Static.get_ms();
					g.printInfo("Darstellungsübergabe: "+((Vector)NodeSelected.getUserData()).elementAt(0));
//					if (CbxJavaFX.isSelected())
//						DarstellungFX.start1(((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue(),Bits(),g).show2();
//					else
						Darstellung.start1(((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue(),Bits(),g).show2();
					g.printInfo("Darstellung"+(Static.get_ms()-lClock));
				}
			}
		});

                ActionListener Act=new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                checkEdit();
                        }
                };
		CbxNotResizeable.addActionListener(Act);
		CbxNotClose.addActionListener(Act);
		CbxAutoSave.addActionListener(Act);
		CbxStdFormular.addActionListener(Act);
		CbxKopierbar.addActionListener(Act);
		CbxNurOrdner.addActionListener(Act);
		CbxBewVoll.addActionListener(Act);
		CbxKeinStamm.addActionListener(Act);
		CbxSynchron.addActionListener(Act);
		CbxVerteiler.addActionListener(Act);
                CbxRefresh.addActionListener(Act);
                CbxMehrfach.addActionListener(Act);
                CbxKeinStammTitel.addActionListener(Act);
                CbxOhneStamm.addActionListener(Act);
                CbxKeinReadOnly.addActionListener(Act);
                CbxPopup.addActionListener(Act);
                CbxNeuCheck.addActionListener(Act);
                CbxHGB.addActionListener(Act);
                CbxBewBew.addActionListener(Act);
                CbxSubRollen.addActionListener(Act);
                CbxRefresh2.addActionListener(Act);
                CbxStammFix.addActionListener(Act);
                CbxMultiselect.addActionListener(Act);
                CbxNbMulti.addActionListener(Act);
                CbxRepaint.addActionListener(Act);
                CbxKeinZR.addActionListener(Act);
//                CbxJavaFX.addActionListener(Act);
                CbxAllSync.addActionListener(Act);
                CbxInfo.addActionListener(Act);
//                CbxOnTop.addActionListener(Act);
                CbxFullScreen.addActionListener(Act);
//                CbxCenter.addActionListener(Act);
                CbxTabFom.addActionListener(Act);
                CbxWeb.addActionListener(Act);
                CbxPers.addActionListener(Act);
                CbxNotHandy.addActionListener(Act);
                CbxTod.addActionListener(Act);
                CbxFxHS.addActionListener(Act);
//                CbxMenu.addActionListener(Act);
//                //CbxAP.addActionListener(Act);
//                CbxFomTitel.addActionListener(Act);
//                CbxNoDetach.addActionListener(Act);
//                CbxNoArrow.addActionListener(Act);
//                CbxKeinST.addActionListener(Act);
//                CbxFertig.addActionListener(Act);
                CbxJeder.addActionListener(Act);
                CbxCombo.addActionListener(Act);
//                RadStage.addActionListener(Act);
//                RadDialog.addActionListener(Act);
//                RadPopOver.addActionListener(Act);
//                RadSubScene.addActionListener(Act);
//                RadBtnScene.addActionListener(Act);
//                RadDrawer.addActionListener(Act);

                /*RbnNormal.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                checkEdit();
                        }
                });

                RbnTerminal.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                checkEdit();
                        }
                });*/

                CbxLogin.addActionListener(Act);
                if (RadKeinZR != null)
                {
                  RadMonat.addActionListener(Act);
                  RadWoche.addActionListener(Act);
                  RadTag.addActionListener(Act);
                  RadKeinZR.addActionListener(Act);
                }
//                if (RadDec != null)
//                {
//                  RadDec.addActionListener(Act);
//                  RadUnDec.addActionListener(Act);
//                  RadTrans.addActionListener(Act);
//                  RadUtil.addActionListener(Act);
//                }
		/*BtnDefinition.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnDefinition.setEnabled(false);

				Def=new Definition(g,"BEGRIFF");

				Def.addWindowListener(new WindowListener()
				{
					public void windowClosed(WindowEvent e)
					{
						BtnDefinition.setEnabled(true);
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
					}
					public void windowDeactivated(WindowEvent e)
					{
					}
				});

			}
		});*/

		thisFrame.addWindowListener(new WindowListener()
		{
			public void windowClosed(WindowEvent e)
			{
				/*Def.dispose();
				Dar.dispose();
				Form.dispose();*/
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
                          checkEdit();
			}
			public void windowDeactivated(WindowEvent e)
			{
			}
		});

		((JFrame)thisFrame).setCursor(new Cursor(Cursor.WAIT_CURSOR));
		show();

		//EnableButtons();
		//g.printInfo("Rest:"+(Static.get_ms()-lClock));
		//lClock = Static.get_ms();
		((JFrame)thisFrame).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

  private void addPopup()
  {
    ActionListener AL=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s = ev.getActionCommand();
        if (s.equals("Darstellung"))
//        	if (CbxJavaFX.isSelected())
//				DarstellungFX.start1(((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(0)).intValue(),Bits(),g).show2();
//			else
				Darstellung.start1(((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(0)).intValue(),Bits(),g).show2();
        else if (s.equals("Darstellung2"))
//        	if (CbxJavaFX.isSelected())
//				DarstellungFX.start2(((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(0)).intValue(),Bits(),g).show2();
//			else
				Darstellung.start2(((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(0)).intValue(),Bits(),g).show2();
        else if (s.equals("Info"))
          Info().showGrid("Info",thisJFrame());
        else if (s.equals("Loeschen"))
          Delete();
        else if (s.equals("show"))
        {
        	int iForm=Sort.geti(Out.getSelectedNode().getUserData(),2);
//        	if (Static.bJavaFX && (Bits()&Formular.cstJavaFX)>0)
//        		new FormularFX(iForm,g,true);
//        	else
        		new Formular(iForm,g).show();
        }
        else if (s.equals("DefVerlauf"))
          new Tabellenspeicher(g,"select tat,timestamp,(select benutzer.kennung from benutzer join logging l on benutzer.aic_benutzer=l.aic_benutzer where l.aic_logging=v.aic_logging) Benutzer"+
              " from defverlauf v where aic_begriff="+Sort.geti(Out.getSelectedNode().getUserData(),0)+" order by aic_defverlauf desc",true).showGrid("DefVerlauf",thisJFrame());
        else if (s.equals("Formularbits"))
          ShowFormularBits();
        else if (s.equals("Definition"))
          new Definition(g,"MANDANT");
        else if (s.equals("ISQL"))
          ISQL.get(g,thisFrame,false);
      }
    };
    MnuDarstellung = g.addMenuItem("Darstellung",popup,null,AL);
    MnuDarstellung2 = g.addMenuItem("Darstellung2",popup,null,AL);
    MnuInfo = g.addMenuItem("Info",popup,null,AL);
    MnuDelete = g.addMenuItem("Loeschen",popup,null,AL);
    MnuShow = g.addMenuItem("show",popup,null,AL);
    MnuDefVerlauf = g.addMenuItem("DefVerlauf",popup,null,AL);
    g.addMenuItem("Formularbits",popup,null,AL);
    if (g.Def())
    {
      JMenuItem MnuTabFom=new JMenuItem("TabFormulare");
      popup.add(MnuTabFom);
      MnuTabFom.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          TabFormulare.showGrid("TabFormulare",thisJFrame());
        }
      });
      popup.addSeparator();
      g.addMenuItem("Definition",popup,null,AL);
      g.addMenuItem("ISQL",popup,null,AL);
    }

    Out.getOutliner().addMouseListener(new MouseListener()
    {
      public void mousePressed(MouseEvent ev)
      {}

      public void mouseClicked(MouseEvent ev)
      {
        //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
        if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM) && g.Def())
          popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
      }

      public void mouseEntered(MouseEvent ev)
      {}

      public void mouseExited(MouseEvent ev)
      {}

      public void mouseReleased(MouseEvent ev)
      {}
    });
  }

  private void ShowFormularBits()
  {
      Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr","Sub","Kennung","Bezeichnung","Anzahl"});
	  addArt(Tab2,"Jeder","Jeder",-14);
	  addArt(Tab2,"Combo","Combo",-13);
	  addArt(Tab2,"Tod","Tod",-12);
      addArt(Tab2,"Prog","Programm",-11);
      addArt(Tab2,"aic_modell","Modell",-10);
      addArt(Tab2,"aic_abfrage","Abfrage",-9);
      addArt(Tab2,"aic_Rolle","Rolle",-8);
      addArt(Tab2,"aic_stamm","Ampel",-7);
      addArt(Tab2,"aic_eigenschaft","Eigenschaft",-6);
      addArt(Tab2,"WEB","Web",-5);
      addArt(Tab2,"ALIAS","Alias",-4);
      addArt(Tab2,"KENNZEICHEN","Kennzeichen",-3);
      addArt(Tab2,"HOMEPAGE","CSS",-2);
      addArt(Tab2,"FXML","ToggleSight",-1);
      addBit(Tab2, "cstNotResizeable","fixe Groesse", 0x0001, 0);
      addBit(Tab2, "cstNotClose","kein Schliessen",  0x0002, 1);
      addBit(Tab2, "cstAutoSave","Autospeichern",  0x0004, 2);
      addBit(Tab2, "cstStdFormular","StdFormular", 0x0008, 3);
      addBit(Tab2, "cstKopierbar","Kopierbar", 0x0010, 4);
      addBit(Tab2, "cstNurOrdner","NurOrdner", 0x0020, 5);
      addBit(Tab2, "cstBewVoll","BewVoll",   0x0040, 6);
      addBit(Tab2, "cstKeinStamm","kein_Stammsatz", 0x0080, 7);
      addBit(Tab2, "cstSynchron","Synchron",  0x0100, 8);
      addBit(Tab2, "cstVerteiler","Verteiler", 0x0200, 9);
      addBit(Tab2, "cstRefresh","Refresh",   0x0400,10);
      addBit(Tab2, "cstMehrfach","Mehrfach",  0x0800,11);
      addBit(Tab2, "cstLogin","Login",     0x1000,12);
      //addBit(Tab2, "cstMonat","Monat",     0x2000,13);
      addBit(Tab2, "cstKeinStammTitel","kein Stammtitel",0x4000,14);
      addBit(Tab2, "cstOhneStamm","ohne Stammsatz", 0x8000,15);
      addBit(Tab2, "cstKeinReadOnly","kein ReadOnly",0x10000,16);
      addBit(Tab2, "cstPopup","Popup",    0x20000,17);
      addBit(Tab2, "cstNeuCheck","NeuCheck", 0x40000,18);
      addBit(Tab2, "cstHGB","HGB",      0x80000,19);
      addBit(Tab2, "cstBewBew","BewBew",  0x100000,20);
      addBit(Tab2, "cstSubRollen","Sub-Rollen",0x200000,21);
      addBit(Tab2, "cstRefresh2","Refresh2",0x400000,22);
      addBit(Tab2, "cstStammFix","Stamm fix",0x800000,23);
      addBit(Tab2, "cstMultiselect","Multiselect",0x1000000,24);
      addBit(Tab2, "cstNbMulti","nicht bei Multiselect",0x2000000,25);
      addBit(Tab2, "cstRepaint","repaint",0x4000000,26);
      addBit(Tab2, "cstKeinZR","kein Zeitraum",0x8000000,27);
      addBit(Tab2, "cstJavaFX","JavaFX",0x10000000,28);
      addBit(Tab2, "cstAllSync","AllSync",0x20000000,29);
      addBit(Tab2, "cstInfo","InfoF",0x40000000,30);
      addBit(Tab2, "cstMonat","Monat",cstZR,cstMonat,13,1);
      addBit(Tab2, "cstWoche","Woche",cstZR,cstWoche,13,2);
      addBit(Tab2, "cstTag","Tag",cstZR,cstTag,13,3);
      addBit(Tab2, "del: cstUnDec","Undecorated",Formular.cstSS,Formular.cstUnDec,33,1);
      addBit(Tab2, "del: cstTrans","Transparent",Formular.cstSS,Formular.cstTrans,33,2);
      addBit(Tab2, "del: cstUtil","Utility",Formular.cstSS,Formular.cstUtil,33,3);
      addBit(Tab2, "del: cstOnTop","OnTop",0x800000000l,35);
      addBit(Tab2, "cstFullScr","FullScreen",0x1000000000l,36);
      addBit(Tab2, "del: cstPopOver","PopOver",Formular.cstFxArt,Formular.cstPopOver,32,1);
      addBit(Tab2, "del: cstScene","SubScene",Formular.cstFxArt,Formular.cstScene,32,2);
      addBit(Tab2, "del: cstBtnScene","BtnScene",Formular.cstFxArt,Formular.cstBtnScene,32,3);
      addBit(Tab2, "del: cstDialog","Dialog",Formular.cstFxArt,Formular.cstDialog,32,4);
      addBit(Tab2, "del: cstDrawer","Drawer",Formular.cstFxArt,Formular.cstDrawer,32,5);
      addBit(Tab2, "cstFxHS","FxHS",Formular.cstFxHS,38);
      addBit(Tab2, "del: cstMenu","Menu",0x8000000000l,39);
      addBit(Tab2, "del: cstAP","AP",0x20000000000l,41);
      addBit(Tab2, "del: cstFomTitel","FomTitel",0x40000000000l,42);
      addBit(Tab2, "del: cstNoDetach","noDetach",0x80000000000l,43);
      addBit(Tab2, "del: cstNoArrow","noArrow",0x100000000000l,44);
      addBit(Tab2, "del: cstKeinST","kein Stichtag",0x200000000000l,45);
      addBit(Tab2, "del: cstFertig","fertig",0x400000000000l,46);
      addBit(Tab2, "del: cstCenter","CenterOnScreen",0x800000000000l,47);
      addBit(Tab2, "cstTabFom","TabFom",Formular.cstTabFom,48);
      addBit(Tab2, "cstTemplate","Template",0x2000000000000l/*Formular.cstTemplate*/,49);
      addBit(Tab2, "cstPers","pers",Formular.cstPers,50);
      addBit(Tab2, "cstNotHandy","nichtHandy",Formular.cstNotHandy,51);
      JDialog FomGid = new JDialog((Frame)thisFrame, "Formularbits", false);
      AUOutliner Grid = new AUOutliner();
      FomGid.getContentPane().add("Center", Grid);
      Tab2.showGrid(Grid);
      FomGid.setSize(400, 600);
      Static.centerComponent(FomGid,thisFrame);
      Grid.addActionListener(new JCActionListener() {
        public void actionPerformed(JCActionEvent ev) {
          JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
          int i=Sort.geti(Nod.getLabel(),0);
          int i2=Sort.geti(Nod.getLabel(),1);
          long l=0;
          long l2=0;
          String sSp=null;
          if (i<0)
          {
        	  sSp=Sort.gets(Nod.getLabel(), 2);
        	  if (sSp.equals("Prog"))
        		  sSp="(select kennung from code where aic_code=b."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
        	  else if (sSp.equals("aic_Rolle"))
        		  sSp="(select kennung from Rolle where aic_Rolle=b."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
        	  else if (sSp.equals("aic_eigenschaft"))
        		  sSp="(select kennung from Eigenschaft where aic_Eigenschaft=f."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
        	  else if (sSp.equals("aic_stamm"))
        		  sSp="(select bezeichnung from stammview2 where aic_rolle is null and aic_stamm=f."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
        	  else if (sSp.equals("aic_abfrage"))
              	sSp="(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage=f."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
        	  else if (sSp.equals("aic_modell"))
                	sSp="(select kennung from begriff join modell on begriff.aic_begriff=modell.aic_begriff where aic_modell=f."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
          }
          else if (i2>0)
          {
            if (i == 13)
            {
              l=cstZR;
              l2=i2==1 ? cstMonat:i2==2 ? cstWoche:cstTag;
            }
            else if (i == 32)
            {
                l=Formular.cstFxArt;
                l2=i2==1 ? Formular.cstPopOver:i2==2 ? Formular.cstScene:i2==3 ? Formular.cstBtnScene:i2==4 ? Formular.cstDialog:i2==5 ? Formular.cstDrawer:0;
            }
            else if (i == 33)
            {
                l=Formular.cstSS;
                l2=i2==1 ? Formular.cstUnDec:i2==2 ? Formular.cstTrans:Formular.cstUtil;
            }
          }
          else
            l=(long)Math.pow(2,i);
          Tabellenspeicher Tab = new Tabellenspeicher(g,"select defbezeichnung,kennung"+g.AU_Bezeichnung1("bewegungstyp","b")+" Bewegungstyp"+
              g.AU_Bezeichnung1("stammtyp","b")+" Stammtyp,b.web"+(i<0 ? ","+sSp:"")+" from begriff b join formular f on b.aic_begriff=f.aic_begriff where "+(i<0 ? Sort.gets(Nod.getLabel(),2)+" is not null":i2>0 ? g.bits("bits",l)+"="+l2:g.bit("b.bits",l)),true);
          //if(Tab.FrmGrid != null)
          //  Tab.FrmGrid.dispose();
          Tab.showGrid("Formulare mit bit " + Sort.gets(Nod.getLabel(),3), /*bModal ? FomGid :*/ null);
        }
      });
      FomGid.setVisible(true);
  }
  
  private void addArt(Tabellenspeicher Tab2,String sConst,String sBez,int i)
  {
	  Tab2.addInhalt("Nr",i);
	  Tab2.addInhalt("Sub",0);
	  Tab2.addInhalt("Kennung",sConst);
	  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getShow(sBez));
	  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from begriff b join formular f on b.aic_begriff=f.aic_begriff where "+sConst+" is not null"));
  }

  private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
  {
    Tab2.addInhalt("Nr",i);
    Tab2.addInhalt("Sub",0);
    Tab2.addInhalt("Kennung",sConst);
    Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
    Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from begriff b join formular f on b.aic_begriff=f.aic_begriff where "+g.bit("b.bits",l)));
  }

  private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,long l2,int i,int iSub)
  {
    Tab2.addInhalt("Nr",i);
    Tab2.addInhalt("Sub",iSub);
    Tab2.addInhalt("Kennung",sConst);
    Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Radiobutton",sBez));
    Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from begriff b join formular f on b.aic_begriff=f.aic_begriff where "+g.bits("b.bits",l)+"="+l2));
  }

	private void Build()
	{
		/*BtnDefinition=getFormularButton("Definition");
		if(BtnDefinition==null)
			BtnDefinition=g.getButton("Definition");*/
		//BtnZeigen=getButton("show");
		//if(BtnZeigen==null)
		//	BtnZeigen=g.getButton("show");
		BtnDelete=getButton("Loeschen");
		if(BtnDelete==null)
			BtnDelete=g.getButton("Loeschen");
		BtnCopy=getButton("Kopieren");
		if(BtnCopy==null)
			BtnCopy=g.getButton("Kopieren");
		BtnBeenden=getButton("Beenden");
		if(BtnBeenden==null)
			BtnBeenden=g.getButton("Beenden");
		//BtnDarstellung=getButton("Darstellung");
		BtnNew=getButton("Neu");
		BtnOk=getButton("Speichern");
		BtnAbbruch=getButton("Ruecksetzen");

		CbxNotResizeable = getCheckbox("fixe Groesse");
		if(CbxNotResizeable==null) CbxNotResizeable=new JCheckBox();
		CbxNotClose = getCheckbox("kein Schliessen");
		if(CbxNotClose==null) CbxNotClose=new JCheckBox();
		CbxAutoSave = getCheckbox("Autospeichern");
		if(CbxAutoSave==null) CbxAutoSave=new JCheckBox();
		CbxStdFormular = getCheckbox("StdFormular");
		if(CbxStdFormular==null) CbxStdFormular=new JCheckBox();
		CbxKopierbar = getCheckbox("Kopierbar");
		if(CbxKopierbar==null) CbxKopierbar=new JCheckBox();
		CbxNurOrdner = getCheckbox("NurOrdner");
		if(CbxNurOrdner==null) CbxNurOrdner=new JCheckBox();
		CbxBewVoll = getCheckbox("BewVoll");
		if(CbxBewVoll==null) CbxBewVoll=new JCheckBox();
		CbxKeinStamm = getCheckbox("kein_Stammsatz");
		if(CbxKeinStamm==null) CbxKeinStamm=new JCheckBox();
		CbxSynchron = getCheckbox("Synchron");
		if(CbxSynchron==null) CbxSynchron=new JCheckBox();
		CbxVerteiler = getCheckbox("Verteiler");
		if(CbxVerteiler==null) CbxVerteiler=new JCheckBox();
                CbxRefresh = getCheckbox("Refresh");
		if(CbxRefresh==null) CbxRefresh=new JCheckBox();
                CbxMehrfach = getCheckbox("Mehrfach");
		if(CbxMehrfach==null) CbxMehrfach=new JCheckBox();
                /*RbnNormal = getRadiobutton("Normal");
		if(RbnNormal==null) RbnNormal=new JRadioButton();
                RbnTerminal = getRadiobutton("Terminal");
		if(RbnTerminal==null) RbnTerminal=new JRadioButton();*/
                CbxLogin = getCheckbox("Login");
		if(CbxLogin==null) CbxLogin=new JCheckBox();
        
		RadMonat = getRadiobutton("Monat");
		RadWoche = getRadiobutton("Woche");
		RadTag = getRadiobutton("Tag");
        RadKeinZR = getRadiobutton("offen");
        
//        RadDec = getRadiobutton("Decorated");
//        if(RadDec==null) RadDec=new JRadioButton();
//        RadUnDec = getRadiobutton("Undecorated");
//        if(RadUnDec==null) RadUnDec=new JRadioButton();
//        RadTrans = getRadiobutton("Transparent");
//        if(RadTrans==null) RadTrans=new JRadioButton();
//        RadUtil = getRadiobutton("Utility");
//        if(RadUtil==null) RadUtil=new JRadioButton();
        
                CbxKeinStammTitel = getCheckbox("kein Stammtitel");
		if(CbxKeinStammTitel==null) CbxKeinStammTitel=new JCheckBox();
                CbxOhneStamm = getCheckbox("ohne Stammsatz");
		if(CbxOhneStamm==null) CbxOhneStamm=new JCheckBox();
                CbxKeinReadOnly = getCheckbox("kein ReadOnly");
		if(CbxKeinReadOnly==null) CbxKeinReadOnly=new JCheckBox();
                CbxPopup = getCheckbox("Popup");
		if(CbxPopup==null) CbxPopup=new JCheckBox();
                CbxNeuCheck = getCheckbox("NeuCheck");
		if(CbxNeuCheck==null) CbxNeuCheck=new JCheckBox();
                CbxHGB = getCheckbox("HGB");
		if(CbxHGB==null) CbxHGB=new JCheckBox();
                CbxBewBew = getCheckbox("BewBew");
		if(CbxBewBew==null) CbxBewBew=new JCheckBox();
                CbxSubRollen = getCheckbox("Sub-Rollen");
		if(CbxSubRollen==null) CbxSubRollen=new JCheckBox();
        
		CbxRefresh2 = getCheckbox("Refresh2");
		if(CbxRefresh2==null) CbxRefresh2=new JCheckBox();
        CbxStammFix = getCheckbox("Stamm fix");
		if(CbxStammFix==null) CbxStammFix=new JCheckBox();
		CbxMultiselect = getCheckbox("Multiselect");
		if(CbxMultiselect==null) CbxMultiselect=new JCheckBox();
		CbxNbMulti = getCheckbox("nicht bei Multiselect");
		if(CbxNbMulti==null) CbxNbMulti=new JCheckBox();
        CbxRepaint = getCheckbox("repaint");
		if(CbxRepaint==null) CbxRepaint=new JCheckBox();
        CbxKeinZR = getCheckbox("kein Zeitraum");
		if(CbxKeinZR==null) CbxKeinZR=new JCheckBox();
//        CbxJavaFX = getCheckbox("JavaFX");
//		if(CbxJavaFX==null) CbxJavaFX=new JCheckBox();
        CbxAllSync = getCheckbox("AllSync");
		if(CbxAllSync==null) CbxAllSync=new JCheckBox();
        CbxInfo = getCheckbox("InfoF");
		if(CbxInfo==null) CbxInfo=new JCheckBox();
		
//		CbxOnTop = getCheckbox("OnTop");
//		if(CbxOnTop==null) CbxOnTop=new JCheckBox();
		CbxFullScreen = getCheckbox("FullScreen");
		if(CbxFullScreen==null) CbxFullScreen=new JCheckBox();
//		CbxCenter = getCheckbox("CenterOnScreen");
//		if(CbxCenter==null) CbxCenter=new JCheckBox();
		CbxTabFom = getCheckbox("TabFom");
		if(CbxTabFom==null) CbxTabFom=new JCheckBox();
		CbxWeb = getCheckbox("Template");
		if(CbxWeb==null) CbxWeb=new AUCheckBox();
		CbxPers = getCheckbox("pers");
		if(CbxPers==null) CbxPers=new JCheckBox();
		CbxNotHandy = getCheckbox("nichtHandy");
		if(CbxNotHandy==null) CbxNotHandy=new JCheckBox();
		CbxFxHS = getCheckbox("FxHS");
		if(CbxFxHS==null) CbxFxHS=new JCheckBox();
//		CbxMenu = getCheckbox("Menu");
//		if(CbxMenu==null) CbxMenu=new JCheckBox();
//		CbxAP = getCheckbox("AP");
//		if(CbxAP==null) CbxAP=new JCheckBox();
//		CbxFomTitel = getCheckbox("FomTitel");
//		if(CbxFomTitel==null) CbxFomTitel=new JCheckBox();
//		CbxNoDetach = getCheckbox("noDetach");
//		if(CbxNoDetach==null) CbxNoDetach=new JCheckBox();
//		CbxNoArrow = getCheckbox("noArrow");
//		if(CbxNoArrow==null) CbxNoArrow=new JCheckBox();
//		CbxKeinST = getCheckbox("kein Stichtag");
//		if(CbxKeinST==null) CbxKeinST=new JCheckBox();
//		CbxFertig = getCheckbox("fertig");
//		if(CbxFertig==null) CbxFertig=new JCheckBox();
//		RadStage = getRadiobutton("Stage");
//		if(RadStage==null) RadStage=new JRadioButton();
//		RadDialog = getRadiobutton("Dialog");
//		if(RadDialog==null) RadDialog=new JRadioButton();
//		RadPopOver = getRadiobutton("PopOver");
//		if(RadPopOver==null) RadPopOver=new JRadioButton();
//		RadSubScene = getRadiobutton("SubScene");
//		if(RadSubScene==null) RadSubScene=new JRadioButton();
//		RadBtnScene = getRadiobutton("BtnScene");
//		if(RadBtnScene==null) RadBtnScene=new JRadioButton();
//		RadDrawer = getRadiobutton("Drawer");
//		if(RadDrawer==null) RadDrawer=new JRadioButton();
		
		CbxJeder = getCheckbox("Jeder");
		if(CbxJeder==null) CbxJeder=new AUCheckBox();
		CbxCombo = getCheckbox("combo");
		if(CbxCombo==null) CbxCombo=new AUCheckBox();
		CbxTod = getCheckbox("Tod");
		if(CbxTod==null) CbxTod=new AUCheckBox();
		
		CbxSwing=getCheckbox("Swing");
//		CbxFX=getCheckbox("FX");
		CbxWebFom=getCheckbox("WebFom");
		CbxTote=getCheckbox("tote");
		RadAlles=getRadiobutton("alles");
		RadLeere=getRadiobutton("leere");
		RadKeineLeere=getRadiobutton("keine leere");
		if (RadAlles!=null) 
			/*if (Static.bJavaFX) RadKeineLeere.setSelected(true); else*/ RadAlles.setSelected(true);
		if (CbxSwing!=null/* && !Static.bJavaFX*/) CbxSwing.setSelected(true);
		if (CbxWebFom!=null/* && !Static.bJavaFX*/) CbxWebFom.setSelected(true);
		//if (CbxFX!=null) CbxFX.setSelected(Static.bJavaFX);
		//if (CbxTote!=null) CbxTote.setSelected(true);
		ActionListener Act2=new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                	if (e.getSource()==CbxSwing)
                		bOut=true;
                	fill_Outliner();
                }
        };
		if (CbxSwing!=null) CbxSwing.addActionListener(Act2);
//		if (CbxFX!=null) CbxFX.addActionListener(Act2);
		if (CbxWebFom!=null) CbxWebFom.addActionListener(Act2);
		if (CbxTote!=null) CbxTote.addActionListener(Act2);
		if (RadAlles!=null) RadAlles.addActionListener(Act2);
		if (RadLeere!=null) RadLeere.addActionListener(Act2);
		if (RadKeineLeere!=null) RadKeineLeere.addActionListener(Act2);

		JPanel Pnl_TxtDefBezeichnung = getFrei("TxtBezeichnung");
                JPanel Pnl_TxtBezeichnung = getFrei("TxtFremdSprache");
		JPanel Pnl_TxtKennung = getFrei("TxtKennung");
//		JPanel Pnl_Css = getFrei("Css");
		JPanel Pnl_Fxml = getFrei("fxml");
//		JPanel Pnl_Kennz = getFrei("Kennzeichen");
		JPanel Pnl_Alias = getFrei("Alias");
		JPanel Pnl_Combo_Typ = getFrei("Combo Typ");
		JPanel Pnl_Combo_Art = getFrei("Combo Art");
		JPanel Pnl_Combo_Prog = getFrei("Combo Prog");
		JPanel Pnl_Combo_Stammtyp = getFrei("Combo Stammtyp");
		JPanel Pnl_Outliner = getFrei("Outliner");
                JPanel Pnl_Combo_Eigenschaft = getFrei("Combo Eigenschaft");
                JPanel Pnl_Combo_Rolle = getFrei("Combo Rolle");
                JPanel Pnl_Combo_Stamm = getFrei("Stamm");
                JPanel Pnl_Combo_Abfragen = getFrei("Combo Abfragen");
                JPanel Pnl_Combo_Modelle = getFrei("Combo Modelle");
		//JPanel Pnl_Combo_Benutzergruppe = getFrei("Combo Benutzergruppe");

		JPanel Pnl_Memo = getFrei("Memo");
		if (Pnl_Memo != null)
			Pnl_Memo.add(Memo);
        JPanel Pnl_Tooltip = getFrei("Tooltip");
        if (Pnl_Tooltip != null)
          Pnl_Tooltip.add(Tooltip);
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
        
        JPanel Pnl_Eingabe = getFrei("Eingabe");
        if (Pnl_Eingabe != null)
        {
        	EdtFilter=new Text("",20);
        	Pnl_Eingabe.add(EdtFilter);
        	EdtFilter.addKeyListener(new KeyListener()
			{
				public void keyPressed(KeyEvent e)
				{
					int i=e.getKeyCode();
					//g.fixtestError("Key-DefForm="+i);
					if (i==10)
						fill_Outliner();
				}
				public void keyReleased(KeyEvent e)
				{
					
				}
				public void keyTyped(KeyEvent e)
				{
					
				}
			});
        }
        
        if (Version.V18())
        {
        	JPanel Pnl_CboStt2 = getFrei("ComboStt2");
        	if (Pnl_CboStt2 != null)
        	{
        		CboStt2=new ComboSort(g);
        		CboStt2.fillDefTable("Stammtyp",true);
        		Pnl_CboStt2.add(CboStt2);
        	}
        	JPanel Pnl_CboMod2 = getFrei("ComboMod2");
        	if (Pnl_CboMod2 != null)
        	{
        		CboMod2=new ModellEingabe(g,thisFrame);
        		CboMod2.fillCboM(-2);
        		Pnl_CboMod2.add(CboMod2);
        	}
        	JPanel Pnl_CboAbf2 = getFrei("ComboAbf2");
        	if (Pnl_CboAbf2 != null)
        	{
        		CboAbf2=new AbfrageEingabe(g,thisFrame,true,false);
        		CboAbf2.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("bits",Abfrage.cstExportierbar),"Abfrage",true);
        		Pnl_CboAbf2.add(CboAbf2);
        	}
			JPanel Pnl_CboPnt2 = getFrei("ComboPnt2");
			if (Pnl_CboPnt2 != null)
        	{
        		CboPnt2=new DruckEingabe(g,thisJFrame());
        		CboPnt2.fillCbo(new Tabellenspeicher(g, "select aic_begriff aic,kennung,defbezeichnung defBez"+g.AU_Bezeichnung("begriff")+
				",(select ein from logging where aic_logging=begriff.aic_logging) Log from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+" and web=1", true), true);
        		Pnl_CboPnt2.add(CboPnt2);
        	}
        	JPanel Pnl_OutlinerZ = getFrei("Outliner Zuordnung");
        	if (Pnl_OutlinerZ != null)
        	{
        		OutZ = new AUOutliner(new JCOutlinerFolderNode(""));
	        	NodStt = new JCOutlinerFolderNode((Object)"Stammtyp", (JCOutlinerFolderNode)OutZ.getRootNode());
	        	NodMod = new JCOutlinerFolderNode((Object)"Modell", (JCOutlinerFolderNode)OutZ.getRootNode());
	        	NodAbf = new JCOutlinerFolderNode((Object)"Abfrage", (JCOutlinerFolderNode)OutZ.getRootNode());
	        	NodDruck = new JCOutlinerFolderNode((Object)"Druck", (JCOutlinerFolderNode)OutZ.getRootNode());
	        	OutZ.setRootVisible(false);
	        	OutZ.setColumnLabelSortMethod(Sort.sortMethod);
	        	String[] sTitelZeile = {g.getShow("Aic"),g.getShow("DefBezeichnung"),g.getShow("Kennung"),g.getShow("Reihenfolge")};
	    		OutZ.setColumnButtons(sTitelZeile);
	    		OutZ.setNumColumns(sTitelZeile.length);
	        	Pnl_OutlinerZ.add(OutZ);
        	}
        	ActionListener ALZ=new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                String s=ev.getActionCommand();
//                g.fixtestError("ALZ:"+s);
                if (s.equals("addStt"))
                {
                	int iStt=CboStt2.getSelectedAIC();
                	if (!VecStt.contains(iStt))
                	{
//                		new JCOutlinerNode(CboStt2.getSelectedItem(), NodStt);
                		addSttNode(iStt);
                		VecStt.addElement(iStt);
                		VecSttp.addElement(iStt);
                		setZu(true);
                	}
                }
                else if (s.equals("addMod"))
                {
                	int iM=CboMod2.getSelectedAIC();
                	if (!VecMod.contains(iM))
                	{
//                		new JCOutlinerNode(CboMod2.Cbo.getSelectedItem(), NodMod);
                		addModNode(iM);
                		VecMod.addElement(iM);
                		VecModp.addElement(iM);
                		setZu(true);
                	}
                }
                else if (s.equals("addAbf"))
                {
                	int iA=g.AbfToBeg(CboAbf2.getSelectedAIC());
                	if (!VecAbf.contains(iA))
                	{
//                		new JCOutlinerNode(CboMod2.Cbo.getSelectedItem(), NodMod);
                		addAbfNode(iA);
                		VecAbf.addElement(iA);
                		VecAbfp.addElement(iA);
                		setZu(true);
                	}
                }
				else if (s.equals("addPnt"))
                {
                	int iP=CboPnt2.getSelectedAIC();
                	if (!VecPnt.contains(iP))
                	{
//                		new JCOutlinerNode(CboMod2.Cbo.getSelectedItem(), NodMod);
                		addDruckNode(iP);
                		VecPnt.addElement(iP);
                		VecPntp.addElement(iP);
                		setZu(true);
                	}
                }
                else if (s.equals("sub"))
                {
                	JCOutlinerNode Nod=OutZ.getSelectedNode();
                	Nod.getParent().removeChild(Nod);
                	int iAic=Sort.geti(Nod.getUserData());
                	if (VecSttp.contains(iAic)) { VecStt.remove((Integer)iAic);VecSttp.remove((Integer)iAic); }
                	else if (VecStt.contains(iAic)) { VecStt.remove((Integer)iAic);VecSttm.addElement(iAic); }
                	else if (VecModp.contains(iAic)) { VecMod.remove((Integer)iAic);VecModp.remove((Integer)iAic); }
                	else if (VecMod.contains(iAic)) { VecMod.remove((Integer)iAic);VecModm.addElement(iAic); }
                	else if (VecAbfp.contains(iAic)) { VecAbf.remove((Integer)iAic);VecAbfp.remove((Integer)iAic); }
                	else if (VecAbf.contains(iAic)) { VecAbf.remove((Integer)iAic);VecAbfm.addElement(iAic); }
					else if (VecPntp.contains(iAic)) { VecPnt.remove((Integer)iAic);VecPntp.remove((Integer)iAic); }
                	else if (VecPnt.contains(iAic)) { VecPnt.remove((Integer)iAic);VecPntm.addElement(iAic); }
                	setZu(true);
                }
                else if (s.equals("up") || s.equals("down"))
                {
                	boolean bUp=s.equals("up");
                	//TODO Reihe ändern
                	JCOutlinerNode Nod=OutZ.getSelectedNode();
                	if (Nod!=null && Nod.getLevel()==2)
                	{
                		setZu(true);
                		Vector<JCOutlinerNode> VecNodes = Nod.getParent().getChildren();
                        int iPos=VecNodes.indexOf(Nod);
                        if(iPos+(bUp?-1:1)>=0 && iPos+(bUp?-1:1)<VecNodes.size())
                        {
                            JCOutlinerNode NodAndere = VecNodes.elementAt(iPos+(bUp?-1:1));
                            ((Vector)NodAndere.getLabel()).setElementAt(iPos+1, 3);
                            ((Vector)Nod.getLabel()).setElementAt(iPos+(bUp ? 0:2), 3);
                            if (Nod.getParent()==NodMod)
                            {
                            	int iPos2=TabZBeg.getPos("aic_fremd",NodAndere.getUserData());
                            	if (iPos>=0)
                            		TabZBeg.setInhalt(iPos2, "R2", iPos+1);
                            	iPos2=TabZBeg.getPos("aic_fremd",Nod.getUserData());
                            	if (iPos>=0)
                            		TabZBeg.setInhalt(iPos2, "R2", iPos+(bUp ? 0:2));
                            }
                            else
                            {
                            	int iPos2=TabZStt.getPos("aic_fremd",NodAndere.getUserData());
                            	if (iPos>=0)
                            		TabZStt.setInhalt(iPos2, "R2", iPos+1);
                            	iPos2=TabZStt.getPos("aic_fremd",Nod.getUserData());
                            	if (iPos>=0)
                            		TabZStt.setInhalt(iPos2, "R2", iPos+(bUp ? 0:2));
                            }
                          //TODO Reihenänderung merken fürs speichern
                            VecNodes.setElementAt(NodAndere,iPos);
                            VecNodes.setElementAt(Nod,iPos+(bUp?-1:1));
                        }
                        OutZ.folderChanged(Nod.getParent());
//                        TabZStt.showGrid("ZStt");
//                        TabZMod.showGrid("ZMod");             		
                	}
                }
                else if (s.equals("ref"))
                	LoadZ();//g.fixtestError("refresh");
//                else if (s.equals("save"))
//                	saveZ(bNew ? 0 : ((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(0)).intValue());
                if (s.equals("addStt") || s.equals("addMod") || s.equals("addAbf") || s.equals("addPnt") || s.equals("sub"))
                	OutZ.folderChanged(OutZ.getRootNode());
              }
            };
//            BtnSave2=getButton("tab_save");
            BtnRefr2=getButton("tab_refr");
            BtnAddMod=getButton(">2");
            BtnAddStt=getButton(">3");
            BtnAddAbf=getButton(">4");
			BtnAddPnt=getButton(">5");
            BtnWeg2=getButton("<2");
            BtnUp=getButton("Pfeil oben");
            BtnDown=getButton("Pfeil unten");
            g.BtnAdd(BtnAddMod,"addMod",ALZ);
            g.BtnAdd(BtnAddStt,"addStt",ALZ);
            g.BtnAdd(BtnAddAbf,"addAbf",ALZ);
			g.BtnAdd(BtnAddPnt,"addPnt",ALZ);
            g.BtnAdd(BtnWeg2,"sub",ALZ);
            g.BtnAdd(BtnUp,"up",ALZ);
            g.BtnAdd(BtnDown,"down",ALZ);
            g.BtnAdd(BtnRefr2,"ref",ALZ);
//            g.BtnAdd(BtnSave2,"save",ALZ);              	
        }

		if(Pnl_Outliner==null || BtnNew==null || Pnl_TxtKennung==null || BtnOk==null || BtnAbbruch==null)// || BtnDarstellung==null)
		{
			((JFrame)thisFrame).getContentPane().removeAll();
			//if(BtnDarstellung==null)
			//	BtnDarstellung=g.getButton("Darstellung");
			if(BtnNew==null)
				BtnNew=g.getButton("Neu");
			if(BtnOk==null)
				BtnOk=g.getButton("Speichern");
			if(BtnAbbruch==null)
				BtnAbbruch=g.getButton("Ruecksetzen");


			((JFrame)thisFrame).getContentPane().setLayout(new BorderLayout(2,2));
			Pnl_Outliner = new JPanel();
			((JFrame)thisFrame).getContentPane().add("Center",Pnl_Outliner);
			JPanel Pnl1 = new JPanel();
			((JFrame)thisFrame).getContentPane().add("East",Pnl1);
			Pnl1.setLayout(new BorderLayout(2,2));
			JPanel Pnl2 = new JPanel();
			Pnl1.add("North",Pnl2);
			Pnl2.setLayout(new GridLayout(1,0,2,2));
			Pnl2.add(BtnNew);
			Pnl2.add(BtnDelete);
			Pnl2.add(BtnCopy);
			Pnl2 = new JPanel();
			Pnl1.add("Center",Pnl2);
			Pnl2.setLayout(new BorderLayout(2,2));
			//Pnl2.add("South",BtnDefinition);
			Pnl1 = new JPanel();
			Pnl2.add("Center",Pnl1);
			Pnl1.setLayout(new BorderLayout(2,2));
			Pnl2 = new JPanel();
			Pnl1.add("South",Pnl2);
			Pnl2.setLayout(new GridLayout(1,0,2,2));
			//Pnl2.add(BtnZeigen);
			//Pnl2.add(BtnDarstellung);
			Pnl2 = new JPanel();
			Pnl1.add("North",Pnl2);
			Pnl2.setLayout(new BorderLayout(2,2));
			Pnl1 = new JPanel();
			Pnl2.add("South",Pnl1);
			Pnl1.setLayout(new GridLayout(1,0,2,2));
			Pnl1.add(BtnOk);
			Pnl1.add(BtnAbbruch);
			Pnl1 = new JPanel();
			Pnl2.add("West",Pnl1);
			Pnl1.setLayout(new GridLayout(0,1,2,2));
			Pnl1.add(new JLabel("Bezeichnung:"));
			Pnl1.add(new JLabel("Kennung:"));
			//Pnl1.add(new JLabel("Benutzergruppe:"));
			Pnl_Combo_Typ = new JPanel();
			Pnl1.add(Pnl_Combo_Typ);
			Pnl1.add(new JLabel("Stammtyp:"));
			Pnl1 = new JPanel();
			Pnl2.add("Center",Pnl1);
			Pnl1.setLayout(new GridLayout(0,1,2,2));
			Pnl_TxtDefBezeichnung = new JPanel();
			Pnl1.add(Pnl_TxtDefBezeichnung);
			Pnl_TxtKennung = new JPanel();
			Pnl1.add(Pnl_TxtKennung);
			//Pnl_Combo_Benutzergruppe = new JPanel();
			//Pnl1.add(Pnl_Combo_Benutzergruppe);
			Pnl_Combo_Art = new JPanel();
			Pnl1.add(Pnl_Combo_Art);
			Pnl_Combo_Stammtyp = new JPanel();
			Pnl1.add(Pnl_Combo_Stammtyp);
			g.printInfo("*");
		}
		if(Pnl_TxtKennung!=null)
                  Pnl_TxtKennung.add("Center",TxtKennung);
//		if(Pnl_Css!=null)
//			Pnl_Css.add("Center",TxtCss);
		if(Pnl_Fxml!=null)
			Pnl_Fxml.add("Center",TxtFxml);
//		if(Pnl_Kennz!=null)
//			Pnl_Kennz.add("Center",TxtKennzeichen);
		if(Pnl_Alias!=null)
			Pnl_Alias.add("Center",TxtAlias);
		if(Pnl_Outliner!=null)
                  Pnl_Outliner.add("Center",Out);
		if(Pnl_TxtBezeichnung!=null)
                  Pnl_TxtBezeichnung.add("Center",TxtBezeichnung);
                if(Pnl_TxtDefBezeichnung!=null)
                  Pnl_TxtDefBezeichnung.add("Center",TxtDefBezeichnung);
		if(Pnl_Combo_Typ!=null)
                  Pnl_Combo_Typ.add("Center",CboTyp);
		if(Pnl_Combo_Prog!=null)
			Pnl_Combo_Prog.add("Center",CboProg);
		if(Pnl_Combo_Art!=null)
                  Pnl_Combo_Art.add("Center",CboArt);
		if(Pnl_Combo_Stammtyp!=null)
                  Pnl_Combo_Stammtyp.add("Center",CboStammtypen);
		if(Pnl_Combo_Eigenschaft!=null)
                  Pnl_Combo_Eigenschaft.add("Center",CboEigenschaft);
                if(Pnl_Combo_Rolle!=null)
                  Pnl_Combo_Rolle.add("Center",CboRolle);
                if(Pnl_Combo_Stamm!=null)
                  Pnl_Combo_Stamm.add("Center",CboStamm);
                if(Pnl_Combo_Abfragen!=null)
                  Pnl_Combo_Abfragen.add("Center",CboAbfrage);
                if(Pnl_Combo_Modelle!=null)
                  Pnl_Combo_Modelle.add("Center",CboModell);

		/*if(Pnl_Combo_Benutzergruppe!=null)
		{
			Pnl_Combo_Benutzergruppe.setLayout(new BorderLayout(2,2));
			Pnl_Combo_Benutzergruppe.add("Center",CboBenutzergruppe);
		}*/

	}
	
	private void setZu(boolean b)
	{
		bZuO=b;
		BtnOk.setEnabled(bNew || bZuO || bEdit && !bCopy);
	}
	
	private void addSttNode(int iStt)
	{
		// new Combo(g.TabStammtypen.getBezeichnungS(Sort.geti(VecStt,i)),Sort.geti(VecStt,i),"",0)
		iAnzStt++;
		Vector<Object> VecLabel = new Vector<Object>();
		VecLabel.addElement(iStt);
		VecLabel.addElement(g.TabStammtypen.getBezeichnungS(iStt));
		VecLabel.addElement(g.TabStammtypen.getKennung(iStt));
		VecLabel.addElement(iAnzStt);
		JCOutlinerNode Nod=new JCOutlinerNode(VecLabel, NodStt);
		Nod.setUserData(iStt);
		int iPos=TabZStt.getPos("AIC_Fremd", iStt);
		if (iPos<0)
		{
			iPos=TabZStt.newLine();
			TabZStt.setInhalt(iPos, "AIC_Fremd", iStt);
		}
		TabZStt.setInhalt(iPos, "R2",iAnzStt);
	}
	
	private void addModNode(int iModell)
	{
		// new Combo(g.TabStammtypen.getBezeichnungS(Sort.geti(VecStt,i)),Sort.geti(VecStt,i),"",0)
		iAnzMod++;
		Vector<Object> VecLabel = new Vector<Object>();
		VecLabel.addElement(iModell);
		VecLabel.addElement(g.getDefBez(iModell));
		VecLabel.addElement(g.TabBegriffe.getKennung(iModell));
		VecLabel.addElement(iAnzMod);
		JCOutlinerNode Nod=new JCOutlinerNode(VecLabel, NodMod);
		Nod.setUserData(iModell);
//		g.fixtestError("TabZMod1:"+TabZMod.getAnzahlElemente("AIC_Fremd")+"/"+TabZMod.getAnzahlElemente("Reihe")+"/"+TabZMod.getAnzahlElemente("R2"));
		int iPos=TabZBeg.getPos("AIC_Fremd", iModell);
		if (iPos<0)
		{
			iPos=TabZBeg.newLine();
			TabZBeg.setInhalt(iPos, "AIC_Fremd", iModell);
		}
		TabZBeg.setInhalt(iPos, "R2",iAnzMod);
//		g.fixtestError("TabZMod2:"+TabZMod.getAnzahlElemente("AIC_Fremd")+"/"+TabZMod.getAnzahlElemente("Reihe")+"/"+TabZMod.getAnzahlElemente("R2"));
//		TabZMod.showGrid("TabMod");
			
	}
	
	private void addAbfNode(int iAbf)
	{
		// new Combo(g.TabStammtypen.getBezeichnungS(Sort.geti(VecStt,i)),Sort.geti(VecStt,i),"",0)
		iAnzAbf++;
		Vector<Object> VecLabel = new Vector<Object>();
		VecLabel.addElement(iAbf);
		VecLabel.addElement(g.getDefBez(iAbf));
		VecLabel.addElement(g.TabBegriffe.getKennung(iAbf));
		VecLabel.addElement(iAnzAbf);
		JCOutlinerNode Nod=new JCOutlinerNode(VecLabel, NodAbf);
		Nod.setUserData(iAbf);
		int iPos=TabZBeg.getPos("AIC_Fremd", iAbf);
		if (iPos<0)
		{
			iPos=TabZBeg.newLine();
			TabZBeg.setInhalt(iPos, "AIC_Fremd", iAbf);
		}
		TabZBeg.setInhalt(iPos, "R2",iAnzAbf);	
	}
	
	private void addDruckNode(int iDruck)
	{
		// new Combo(g.TabStammtypen.getBezeichnungS(Sort.geti(VecStt,i)),Sort.geti(VecStt,i),"",0)
		iAnzDruck++;
		Vector<Object> VecLabel = new Vector<Object>();
		VecLabel.addElement(iDruck);
		VecLabel.addElement(g.getDefBez(iDruck));
		VecLabel.addElement(g.TabBegriffe.getKennung(iDruck));
		VecLabel.addElement(iAnzDruck);
		JCOutlinerNode Nod=new JCOutlinerNode(VecLabel, NodDruck);
		Nod.setUserData(iDruck);
		int iPos=TabZBeg.getPos("AIC_Fremd", iDruck);
		if (iPos<0)
		{
			iPos=TabZBeg.newLine();
			TabZBeg.setInhalt(iPos, "AIC_Fremd", iDruck);
		}
		TabZBeg.setInhalt(iPos, "R2",iAnzAbf);
	}
	
	private void getVecBeg()
	{
		VecMod=new Vector<Integer>();
		VecAbf=new Vector<Integer>();
		// VecPnt=new Vector<Integer>();
		for (int i=0;i<TabZBeg.size();i++)
		{
			int iAic=TabZBeg.getI(i,"AIC_Fremd");
			String sBG=g.getBG(iAic);
			if (sBG.equals("Modell"))
				VecMod.addElement(iAic);
			else if (sBG.equals("Abfrage"))
				VecAbf.addElement(iAic);
			// else if (sBG.equals("Druck"))
			// 	VecPnt.addElement(iAic);
			else
				g.printError("getVecBeg: BG "+sBG+" wird nicht unterstützt ("+iAic+")");
		}
	}
	
	private void LoadZ()
	{
		if (OutZ==null)
			return;
		int iAIC_Begriff=bNew ? 0 : ((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(0)).intValue();
		iAnzStt=0;iAnzMod=0;iAnzAbf=0;iAnzDruck=0;
		TabZStt=new Tabellenspeicher(g,"select AIC_Fremd,Reihe,null R2 from Beg2_Z where aic_begriff="+iAIC_Begriff+" and AIC_Tabellenname="+Global.iTabStt+" order by Reihe",true);
		VecStt=TabZStt.getVecSpalteI("AIC_Fremd");
		TabZBeg=new Tabellenspeicher(g,"select AIC_Fremd,Reihe,null R2 from Beg2_Z where aic_begriff="+iAIC_Begriff+" and AIC_Tabellenname="+Global.iTabBegriff+" order by Reihe",true);
		VecPnt=SQL.getVector("select z.aic_begriff from begriff_zuordnung z join begriff b on z.aic_begriff=b.aic_begriff where beg_aic_begriff="+iAIC_Begriff+" and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck"), g);
//		VecMod=TabZBeg.getVecSpalteI("AIC_Fremd");
		getVecBeg();
		NodStt.removeChildren();
		for (int i=0;i<VecStt.size();i++)
			addSttNode(Sort.geti(VecStt,i));
		NodMod.removeChildren();
		for (int i=0;i<VecMod.size();i++)
			addModNode(Sort.geti(VecMod,i));
		NodAbf.removeChildren();
		for (int i=0;i<VecAbf.size();i++)
			addAbfNode(Sort.geti(VecAbf,i));
		NodDruck.removeChildren();
		for (int i=0;i<VecPnt.size();i++)
			addDruckNode(Sort.geti(VecPnt,i));
		OutZ.folderChanged(OutZ.getRootNode());
		VecSttp.clear();VecSttm.clear();
		VecModp.clear();VecModm.clear();
		VecAbfp.clear();VecAbfm.clear();
		VecPntp.clear();VecPntm.clear();
		setZu(false);
	}
	
	private void saveZ(int iAIC_Begriff)
	{
//		g.fixtestError("save: VecStt="+VecStt+", VecModelle="+VecMod);
//		g.fixtestError("TabZMod3:"+TabZMod.getAnzahlElemente("AIC_Fremd")+"/"+TabZMod.getAnzahlElemente("Reihe")+"/"+TabZMod.getAnzahlElemente("R2"));
		SQL Qry=new SQL(g);
		int iCDF=g.getCodeAic("Zuordnungsart", "DetailForm");
		if (VecSttm.size()>0)
			g.exec("delete from Beg2_Z where AIC_Begriff="+iAIC_Begriff+" and AIC_Tabellenname="+Global.iTabStt+" and"+g.in("aic_fremd", VecSttm));
		if (VecModm.size()>0)
			g.exec("delete from Beg2_Z where AIC_Begriff="+iAIC_Begriff+" and AIC_Tabellenname="+Global.iTabBegriff+" and"+g.in("aic_fremd", VecModm));
		if (VecAbfm.size()>0)
			g.exec("delete from Beg2_Z where AIC_Begriff="+iAIC_Begriff+" and AIC_Tabellenname="+Global.iTabBegriff+" and"+g.in("aic_fremd", VecAbfm));
		if (VecPntm.size()>0)
			g.exec("delete from Begriff_Zuordnung where Beg_AIC_Begriff="+iAIC_Begriff+" and"+g.in("aic_begriff", VecPntm));
		for (int i=0;i<VecSttm.size();i++)
			g.SaveDefVerlauf(iAIC_Begriff, "Stt "+g.TabStammtypen.getBezeichnungS(Sort.geti(VecSttm, i))+" ("+Sort.geti(VecSttm, i)+") weg");
		for (int i=0;i<VecModm.size();i++)
			g.SaveDefVerlauf(iAIC_Begriff, "Modell "+g.getDefBez(Sort.geti(VecModm, i))+" ("+Sort.geti(VecModm, i)+") weg");
		for (int i=0;i<VecAbfm.size();i++)
			g.SaveDefVerlauf(iAIC_Begriff, "Abfrage "+g.getDefBez(Sort.geti(VecAbfm, i))+" ("+Sort.geti(VecAbfm, i)+") weg");
		for (int i=0;i<VecPntm.size();i++)
			g.SaveDefVerlauf(iAIC_Begriff, "Druck "+g.getDefBez(Sort.geti(VecPntm, i))+" ("+Sort.geti(VecPntm, i)+") weg");
		for (int i=0;i<VecSttp.size();i++)
		{
			Qry.add("AIC_Begriff", iAIC_Begriff);
			Qry.add("AIC_Tabellenname",Global.iTabStt);
			int iAic=Sort.geti(VecSttp, i);
			Qry.add("AIC_Fremd",iAic);
			int iPos=TabZStt.getPos("Aic_Fremd",iAic);
			if (iPos>=0)
			{
				Qry.add("Reihe",TabZStt.getI(iPos,"R2"));
				TabZStt.setInhalt(iPos,"Reihe", TabZStt.getI(iPos,"R2"));
			}
//                    "ZBits int null,"+
			Qry.add("Art",iCDF);
			Qry.insert("Beg2_Z", false);
			g.SaveDefVerlauf(iAIC_Begriff, "Stt "+g.TabStammtypen.getBezeichnungS(iAic)+" ("+iAic+") dazu");
		}
		for (int i=0;i<VecModp.size();i++)
		{
			Qry.add("AIC_Begriff", iAIC_Begriff);
			Qry.add("AIC_Tabellenname",Global.iTabBegriff);
			int iAic=Sort.geti(VecModp, i);
			Qry.add("AIC_Fremd",iAic);
			int iPos=TabZBeg.getPos("Aic_Fremd",iAic);
			if (iPos>=0)
			{
				Qry.add("Reihe",TabZBeg.getI(iPos,"R2"));
				TabZBeg.setInhalt(iPos,"Reihe", TabZBeg.getI(iPos,"R2"));
			}
			Qry.add("Art",iCDF);
			Qry.insert("Beg2_Z", false);
			g.SaveDefVerlauf(iAIC_Begriff, "Modell "+g.getDefBez(iAic)+" ("+iAic+") dazu");
		}
		for (int i=0;i<VecAbfp.size();i++)
		{
			Qry.add("AIC_Begriff", iAIC_Begriff);
			Qry.add("AIC_Tabellenname",Global.iTabBegriff);
			int iAic=Sort.geti(VecAbfp, i);
			Qry.add("AIC_Fremd",iAic);
			int iPos=TabZBeg.getPos("Aic_Fremd",iAic);
			if (iPos>=0)
			{
				Qry.add("Reihe",TabZBeg.getI(iPos,"R2"));
				TabZBeg.setInhalt(iPos,"Reihe", TabZBeg.getI(iPos,"R2"));
			}
			Qry.add("Art",iCDF);
			Qry.insert("Beg2_Z", false);
			g.SaveDefVerlauf(iAIC_Begriff, "Abfrage "+g.getDefBez(iAic)+" ("+iAic+") dazu");
		}
		for (int i=0;i<VecPntp.size();i++)
		{
			Qry.add("Beg_AIC_Begriff", iAIC_Begriff);
			// Qry.add("AIC_Tabellenname",Global.iTabBegriff);
			int iAic=Sort.geti(VecPntp, i);
			Qry.add("AIC_Begriff",iAic);
			// int iPos=TabZBeg.getPos("Aic_Fremd",iAic);
			// if (iPos>=0)
			// {
			// 	Qry.add("Reihe",TabZBeg.getI(iPos,"R2"));
			// 	TabZBeg.setInhalt(iPos,"Reihe", TabZBeg.getI(iPos,"R2"));
			// }
			// Qry.add("Art",iCDF);
			Qry.insert("Begriff_Zuordnung", false);
			g.SaveDefVerlauf(iAIC_Begriff, "Druck "+g.getDefBez(iAic)+" ("+iAic+") dazu");
		}
		for (int i=0;i<TabZStt.getAnzahlElemente("R2");i++)// TabZStt.moveFirst();!TabZStt.out();TabZStt.moveNext())
			if (TabZStt.getI(i,"R2")!=TabZStt.getI(i,"Reihe"))
				g.exec("update Beg2_Z set Reihe="+TabZStt.getI(i,"R2")+" where aic_fremd="+TabZStt.getI(i,"AIC_Fremd")+" and AIC_Tabellenname="+Global.iTabStt+" and Art="+iCDF);
//		TabZMod.showGrid("ZMod");
//		g.fixtestError("TabZMod4:"+TabZMod.getAnzahlElemente("AIC_Fremd")+"/"+TabZMod.getAnzahlElemente("Reihe")+"/"+TabZMod.getAnzahlElemente("R2"));
		for (int i=0;i<TabZBeg.getAnzahlElemente("R2");i++)
			if (TabZBeg.getI(i,"R2")!=TabZBeg.getI(i,"Reihe"))
				g.exec("update Beg2_Z set Reihe="+TabZBeg.getI(i,"R2")+" where aic_fremd="+TabZBeg.getI(i,"AIC_Fremd")+" and AIC_Tabellenname="+Global.iTabBegriff+" and Art="+iCDF);
		LoadZ();
	}

	private void CboTyp_itemStateChanged()
	{
          int iTyp=CboTyp.getSelectedAIC();
          if (iTyp != iTypOld)
          {
            iTypOld=iTyp;
            //g.progInfo("DefFormular.CboTyp_itemStateChanged:" + CboTyp.getSelectedKennung());
            if (CboTyp.getSelectedKennung().equals("AIC_STAMMTYP"))
              CboArt.fillDefTable("Stammtyp", false);
            else if (CboTyp.getSelectedKennung().equals("AIC_BEWEGUNGSTYP"))
              CboArt.fillDefTable("BEWEGUNGSTYP", false);
            else
              CboArt.fillBegriffTable("Formulartyp", false);
          }
	}

	private long Bits()
        {
          long l= (CbxNotResizeable.isSelected()?Formular.cstNotResizeable:0)+(CbxNotClose.isSelected()?Formular.cstNotClose:0)+
              (CbxAutoSave.isSelected()?Formular.cstAutoSave:0)+(CbxStdFormular.isSelected()?Formular.cstStdFormular:0)+
              (CbxKopierbar.isSelected()?Formular.cstKopierbar:0)+(CbxNurOrdner.isSelected()?Formular.cstNurOrdner:0)+
              (CbxBewVoll.isSelected()?Formular.cstBewVoll:0)+(CbxKeinStamm.isSelected()?Formular.cstKeinStamm:0)+
              (CbxSynchron.isSelected()?Formular.cstSynchron:0)+(CbxVerteiler.isSelected()?Formular.cstVerteiler:0)+
              (CbxRefresh.isSelected()?Formular.cstRefresh:0)+(CbxMehrfach.isSelected()?Formular.cstMehrfach:0)+
              (CbxLogin.isSelected()?Formular.cstLogin:0)+(RadKeinZR==null || RadKeinZR.isSelected() ? 0://(CbxMonat.isSelected()?Formular.cstMonat:0)+
                RadMonat.isSelected()?Formular.cstMonat:RadWoche.isSelected()?Formular.cstWoche:RadTag.isSelected()?Formular.cstTag:0)+
              (CbxKeinStammTitel.isSelected()?Formular.cstKeinStammTitel:0)+(CbxOhneStamm.isSelected()?Formular.cstOhneStamm:0)+
              (CbxKeinReadOnly.isSelected()?Formular.cstKeinReadOnly:0)+(CbxPopup.isSelected()?Formular.cstPopup:0)+
              (CbxNeuCheck.isSelected()?Formular.cstNeuCheck:0)+(CbxHGB.isSelected()?Formular.cstHGB:0)+
              (CbxBewBew.isSelected()?Formular.cstBewBew:0)+(CbxSubRollen.isSelected()?Formular.cstSubRollen:0)+
              (CbxRefresh2.isSelected()?Formular.cstRefresh2:0)+(CbxStammFix.isSelected()?Formular.cstStammFix:0)+
              (CbxMultiselect.isSelected()?Formular.cstMultiselect:0)+(CbxNbMulti.isSelected()?Formular.cstNbMulti:0)+
              (CbxRepaint.isSelected()?Formular.cstRepaint:0)+(CbxKeinZR.isSelected()?Formular.cstKeinZR:0)+
//              (RadDec==null || RadDec.isSelected() ? Formular.cstDec: RadUnDec.isSelected()?Formular.cstUnDec:RadTrans.isSelected()?Formular.cstTrans:RadUtil.isSelected()? Formular.cstUtil:0)+
//              (CbxJavaFX.isSelected()?Formular.cstJavaFX:0)+
              (CbxAllSync.isSelected()?Formular.cstAllSync:0)+(CbxInfo.isSelected()?Formular.cstInfo:0)+(CbxFullScreen.isSelected()?Formular.cstFullScr:0)+
//              (CbxOnTop.isSelected()?Formular.cstOnTop:0)+(CbxFxHS.isSelected()?Formular.cstFxHS:0)+(CbxMenu.isSelected()? Formular.cstMenu:0)+//(CbxAP.isSelected()?FormularFX.cstAP:0)+
//              (CbxFomTitel.isSelected()?Formular.cstFomTitel:0)+(CbxNoDetach.isSelected()?Formular.cstNoDetach:0)+(CbxNoArrow.isSelected()?Formular.cstNoArrow:0)+(CbxKeinST.isSelected()?Formular.cstKeinST:0)+
//              (CbxFertig.isSelected()?Formular.cstFertig:0)+
//              (RadDialog.isSelected()?Formular.cstDialog:RadPopOver.isSelected()?Formular.cstPopOver:RadSubScene.isSelected()?Formular.cstScene:
//            	  RadBtnScene.isSelected()?Formular.cstBtnScene:RadDrawer.isSelected()?Formular.cstDrawer:Formular.cstStage)+//(CbxCenter.isSelected()?Formular.cstCenter:0)+
              (CbxTabFom.isSelected()?Formular.cstTabFom:0)/*+(CbxWeb.isSelected()?Formular.cstTemplate:0)*/+(CbxPers.isSelected()?Formular.cstPers:0)+(CbxNotHandy.isSelected()?Formular.cstNotHandy:0);
          //g.fixInfo("Bits="+l);
          return l;
        }

    private void checkEdit()
	{

		if(!bNew)
		{
			bEdit=TxtBezeichnung.Modified();
            bEdit=bEdit || TxtDefBezeichnung.Modified() || TxtFxml.Modified() || TxtAlias.Modified();// || TxtCss.Modified() || TxtKennzeichen.Modified();
			bEdit=bEdit || !sAktKennung.equals(TxtKennung.getText());
			//bEdit=bEdit || !(iAktBenutzergruppe==CboBenutzergruppe.getSelectedAIC());
			bEdit=bEdit || CboTyp.Modified();//!(iAktTyp==CboTyp.getSelectedAIC());
			bEdit=bEdit || CboArt.Modified();//!(iAktArt==CboArt.getSelectedAIC());
			bEdit=bEdit || CboStammtypen.Modified();//!(iAktStm==CboStammtypen.getSelectedAIC());
			bEdit=bEdit || CboProg.Modified();
            bEdit=bEdit || CboEigenschaft.Cbo.Modified();
            bEdit=bEdit || CboRolle.Cbo.Modified();
            bEdit=bEdit || CboStamm.Modified();
            bEdit=bEdit || CboAbfrage.Cbo.Modified();
            bEdit=bEdit || CboModell.Cbo.Modified();
			bEdit=bEdit || lBits != Bits();
			bEdit=bEdit || Memo.Modified();
            bEdit=bEdit || Tooltip.Modified();
            bEdit=bEdit || CbxJeder.Modified();
            bEdit=bEdit || CbxCombo.Modified();
            bEdit=bEdit || CbxTod.Modified();
			bEdit=bEdit || CbxWeb.Modified();
            if (BtnBildSw != null)
              bEdit=bEdit || BtnBildSw.Modified();
            if (BtnBildFX != null)
              bEdit=bEdit || BtnBildFX.Modified();
			bCopy=TxtKennung.Modified();
		}
		else
			bCopy=false;

		EnableButtons();
	}

	private void Out_itemStateChanged()
	{
		JCOutlinerNode NodeSelected = Out.getSelectedNode();
		int iLevel = NodeSelected.getLevel();
        int iAktEig=0;
        int iAktStamm=0;
        int iAktRolle=0;
        int iAktAbfrage=0;
        int iAktModell=0;
        int iAktProg=0;
		if(iLevel==3)
		{
			String sAktBezeichnung=(String)((Vector)NodeSelected.getLabel()).elementAt(0);
			sAktKennung=(String)((Vector)NodeSelected.getLabel()).elementAt(1);
			String sAktAlias=Sort.gets(NodeSelected.getLabel(), 6);
//			String sAktCss=Sort.gets(NodeSelected.getLabel(), 6);
			String sAktFxml=Sort.gets(NodeSelected.getLabel(), 7);
//			String sAktKennz=Sort.gets(NodeSelected.getLabel(), 8);
//			String sAktAlias=Sort.gets(NodeSelected.getLabel(), 9);
			//iAktBenutzergruppe=((Integer)((Vector)NodeSelected.getUserData()).elementAt(1)).intValue();
			TxtDefBezeichnung.setText(sAktBezeichnung);
                        TxtKennung.setText(sAktKennung);
//                        TxtCss.setText(sAktCss);
                        TxtFxml.setText(sAktFxml);
//                        TxtKennzeichen.setText(sAktKennz);
                        TxtAlias.setText(sAktAlias);
                        int iBeg=((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue();
			Vector Vec=g.getMemoVector("BEGRIFF",iBeg);
                        String sBez=g.TabBegriffe.getBezeichnungS(iBeg);
                        TxtBezeichnung.setText(sAktBezeichnung.equals(sBez) ? "":sBez);
                        //g.progInfo("Memo.setText:"+Vec.elementAt(1).toString());
			Memo.setText(Vec.elementAt(1).toString());
            int iPos=g.TabBegriffe.getPos("Aic",iBeg);
            Tooltip.setText(iPos<0 ?"":g.TabBegriffe.getM(iPos,"Tooltip"));
            if (BtnBildSw != null)
            	BtnBildSw.setValue(iPos,Global.iSpSw);
            if (BtnBildFX != null)
            	BtnBildFX.setValue(iPos,Global.iSpFX);
			//CboBenutzergruppe.setSelectedAIC(iAktBenutzergruppe);

			iAktStt=((Integer)((Vector)NodeSelected.getParent().getUserData()).elementAt(0)).intValue();
			iAktTyp=((Integer)((Vector)NodeSelected.getParent().getParent().getUserData()).elementAt(0)).intValue();
			iAktBew=iAktTyp==1?((Integer)((Vector)NodeSelected.getUserData()).elementAt(4)).intValue():0;
            if (((Vector)NodeSelected.getUserData()).size()>5)
              iAktStamm=((Integer)((Vector)NodeSelected.getUserData()).elementAt(5)).intValue();
            if (((Vector)NodeSelected.getUserData()).size()>6)
              iAktRolle=((Integer)((Vector)NodeSelected.getUserData()).elementAt(6)).intValue();
            if (((Vector)NodeSelected.getUserData()).size()>7)
              iAktAbfrage=((Integer)((Vector)NodeSelected.getUserData()).elementAt(7)).intValue();
            if (((Vector)NodeSelected.getUserData()).size()>8)
              iAktModell=((Integer)((Vector)NodeSelected.getUserData()).elementAt(8)).intValue();
            if (((Vector)NodeSelected.getUserData()).size()>9)
                iAktProg=((Integer)((Vector)NodeSelected.getUserData()).elementAt(9)).intValue();
			lBits=((Long)((Vector)NodeSelected.getUserData()).elementAt(3)).longValue();
                        iAktEig=((Integer)((Vector)NodeSelected.getUserData()).elementAt(1)).intValue();
			CbxNotResizeable.setSelected((lBits&Formular.cstNotResizeable) > 0);
			CbxNotClose.setSelected((lBits&Formular.cstNotClose) > 0);
			CbxAutoSave.setSelected((lBits&Formular.cstAutoSave) > 0);
			CbxStdFormular.setSelected((lBits&Formular.cstStdFormular) > 0);
			CbxKopierbar.setSelected((lBits&Formular.cstKopierbar) > 0);
			CbxNurOrdner.setSelected((lBits&Formular.cstNurOrdner) > 0);
			CbxBewVoll.setSelected((lBits&Formular.cstBewVoll) > 0);
			CbxKeinStamm.setSelected((lBits&Formular.cstKeinStamm) > 0);
			CbxSynchron.setSelected((lBits&Formular.cstSynchron) > 0);
			CbxVerteiler.setSelected((lBits&Formular.cstVerteiler) > 0);
                        CbxRefresh.setSelected((lBits&Formular.cstRefresh) > 0);
                        CbxMehrfach.setSelected((lBits&Formular.cstMehrfach) > 0);
                        //RbnNormal.setSelected((lBits&Formular.cstEinstieg) == Formular.cstNormal);
                        //RbnTerminal.setSelected((lBits&Formular.cstEinstieg) == Formular.cstTerminal);
                        CbxLogin.setSelected((lBits&Formular.cstLogin)  > 0);
                        //CbxMonat.setSelected((lBits&Formular.cstMonat)  > 0);
                        if (RadKeinZR==null)
                          Static.printError("Zeitraum-Radiobuttons fehlen");
                        else if ((lBits&Formular.cstZR)==Formular.cstMonat)
                          RadMonat.setSelected(true);
                        else if ((lBits&Formular.cstZR)==Formular.cstWoche)
                          RadWoche.setSelected(true);
                        else if ((lBits&Formular.cstZR)==Formular.cstTag)
                          RadTag.setSelected(true);
                        else
                          RadKeinZR.setSelected(true);
//                        if (RadDec==null)
//                            Static.printError("Style-Radiobuttons fehlen");
//                          else if ((lBits&Formular.cstSS)==Formular.cstUnDec)
//                            RadUnDec.setSelected(true);
//                          else if ((lBits&Formular.cstSS)==Formular.cstTrans)
//                            RadTrans.setSelected(true);
//                          else if ((lBits&Formular.cstSS)==Formular.cstUtil)
//                            RadUtil.setSelected(true);
//                          else
//                            RadDec.setSelected(true);
                        CbxKeinStammTitel.setSelected((lBits&Formular.cstKeinStammTitel) > 0);
                        CbxOhneStamm.setSelected((lBits&Formular.cstOhneStamm) > 0);
                        CbxKeinReadOnly.setSelected((lBits&Formular.cstKeinReadOnly) > 0);
                        CbxPopup.setSelected((lBits&Formular.cstPopup) > 0);
                        CbxNeuCheck.setSelected((lBits&Formular.cstNeuCheck) > 0);
                        CbxHGB.setSelected((lBits&Formular.cstHGB) > 0);
                        CbxBewBew.setSelected((lBits&Formular.cstBewBew) > 0);
                        CbxSubRollen.setSelected((lBits&Formular.cstSubRollen) > 0);
                        CbxRefresh2.setSelected((lBits&Formular.cstRefresh2) > 0);
                        CbxStammFix.setSelected((lBits&Formular.cstStammFix) > 0);
                        CbxMultiselect.setSelected((lBits&Formular.cstMultiselect) > 0);
                        CbxNbMulti.setSelected((lBits&Formular.cstNbMulti) > 0);
                        CbxRepaint.setSelected((lBits&Formular.cstRepaint) > 0);
                        CbxKeinZR.setSelected((lBits&Formular.cstKeinZR) > 0);
//                        CbxJavaFX.setSelected((lBits&Formular.cstJavaFX) > 0);
                        CbxAllSync.setSelected((lBits&Formular.cstAllSync) > 0);
                        CbxInfo.setSelected((lBits&Formular.cstInfo) > 0);
//                        CbxOnTop.setSelected((lBits&Formular.cstOnTop) > 0);
                        CbxFullScreen.setSelected((lBits&Formular.cstFullScr) > 0);
//                        CbxCenter.setSelected((lBits&Formular.cstCenter) > 0);
                        CbxTabFom.setSelected((lBits&Formular.cstTabFom) > 0);
                        CbxWeb.setSelected(iPos>=0 && g.TabBegriffe.getB(iPos,"Web"));//(lBits&Formular.cstTemplate) > 0);
                        CbxPers.setSelected((lBits&Formular.cstPers) > 0);
                        CbxNotHandy.setSelected((lBits&Formular.cstNotHandy) > 0);
                        CbxFxHS.setSelected((lBits&Formular.cstFxHS) > 0);
//                        CbxMenu.setSelected((lBits&Formular.cstMenu) > 0);
                        //CbxAP.setSelected((lBits&FormularFX.cstAP) > 0);
//                        CbxFomTitel.setSelected((lBits&Formular.cstFomTitel) > 0);
//                        CbxNoDetach.setSelected((lBits&Formular.cstNoDetach) > 0);
//                        CbxNoArrow.setSelected((lBits&Formular.cstNoArrow) > 0);
//                        CbxKeinST.setSelected((lBits&Formular.cstKeinST) > 0);
//                        CbxFertig.setSelected((lBits&Formular.cstFertig) > 0);
                        CbxJeder.setSelected(g.getJeder(iBeg,false).equals(Static.sJa));
                        CbxCombo.setSelected(iPos<0 ? false:g.TabBegriffe.getB(iPos,"Combo"));
                        CbxTod.setSelected(iPos<0 ? true:g.TabBegriffe.getB(iPos,"Tod"));
//                        if ((lBits&Formular.cstFxArt)==Formular.cstDialog)
//                        	RadDialog.setSelected(true);
//                        else if ((lBits&Formular.cstFxArt)==Formular.cstPopOver)
//                        	RadPopOver.setSelected(true);
//                        else if ((lBits&Formular.cstFxArt)==Formular.cstScene)
//                        	RadSubScene.setSelected(true);
//                        else if ((lBits&Formular.cstFxArt)==Formular.cstBtnScene)
//                        	RadBtnScene.setSelected(true);
//                        else if ((lBits&Formular.cstFxArt)==Formular.cstDrawer)
//                        	RadDrawer.setSelected(true);
//                        else
//                        	RadStage.setSelected(true);
                        if (Version.V18())
                        	LoadZ();
		}
		else
		{
			TxtDefBezeichnung.setText("");
                        TxtBezeichnung.setText("");
			TxtKennung.setText("");
//			TxtCss.setText("");
			TxtFxml.setText("");
//			TxtKennzeichen.setText("");
			TxtAlias.setText("");
			Memo.setText("");
            Tooltip.setText("");
            if (BtnBildSw != null)
            	BtnBildSw.Delete();
            if (BtnBildFX != null)
            	BtnBildFX.Delete();
			//CboBenutzergruppe.setSelectedAIC(0);
		}
                if(iLevel==2)
		{
			iAktStt=((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue();
			iAktTyp=((Integer)((Vector)NodeSelected.getParent().getUserData()).elementAt(0)).intValue();
			iAktBew=0;
		}
		else if(iLevel==1)
		{
			iAktStt = 0;
			iAktTyp = ((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue();
			iAktBew = 0;
		}
                //g.progInfo("Level="+iLevel+", Typ="+iAktTyp+", Bew="+iAktBew);
		CboTyp.setSelectedAIC(iAktTyp==-1?-1:iAktBew>0?2:1);
                CboTyp_itemStateChanged();
                checkEdit();
		if(iLevel>1)
			CboArt.setSelectedAIC(iAktBew==0?iAktStt:iAktBew);
                if (iAktStt != CboStammtypen.getSelectedAIC())
                {
                  CboRolle.fillRolle(iAktStt, true);
                  CboStammtypen.setSelectedAIC(iAktStt);
                }
                CboEigenschaft.setSelectedAIC(iAktEig);
                CboRolle.setSelectedAIC(iAktRolle);
                CboStamm.setStamm2(iAktStamm);
                CboAbfrage.setSelectedAIC(iAktAbfrage);
                CboModell.setSelectedAIC(iAktModell);
                CboProg.setSelectedAIC(iAktProg);
		bNew=false;
		bEdit=false;
		//g.fixtestError("Prog="+iAktProg+", Typ="+iAktTyp+", Art="+CboArt.getSelectedAIC());
		EnableButtons();
	}

	private void BtnOk_actionPerformed()
	{
          /*if (TxtKennung.isNull())
          {
            new Message(Message.ERROR_MESSAGE, null,g).showDialog("KennungLeer");
            return;
          }*/

		JCOutlinerNode Node = Out.getSelectedNode();
		//boolean bKennungExists;
                int iAIC_Begriff=bNew ? 0 : ((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
                int iAIC_Begriffgruppe =g.TabBegriffgruppen.getAic("Frame");

		//Qry.open("SELECT Begriff.AIC_Begriff FROM Formular JOIN Begriff WHERE begriff.aic_begriff<> "+iAIC_Begriff+" and Kennung='"+TxtKennung.getText()+"' AND "+CboTyp.getSelectedKennung()+" "+(CboArt.getSelectedAIC()==0?"is null":"="+CboArt.getSelectedAIC()));
		/*if(bEdit)
			bKennungExists=!Qry.eof() && Qry.getI("AIC_Begriff")!=((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
		else*/
		//	bKennungExists=!Qry.eof();
		//Qry.close();

		String sKennung = TxtKennung.getText();

                if (Message.KennungWarnung(g,sKennung,iAIC_Begriff,iAIC_Begriffgruppe,(JFrame)thisFrame))
                    return;

		String sBezeichnung = TxtDefBezeichnung.getText();
        SQL Qry = new SQL(g);

		//int iBenutzergruppe = CboBenutzergruppe.getSelectedAIC();

		/*if(bKennungExists)
		{
			new Message(Message.INFORMATION_MESSAGE,(JFrame)thisFrame,g).showDialog("KennungVorhanden");
		}
		else*/ 
        int iTyp=CboTyp.getSelectedAIC();
        int iArt=CboArt.getSelectedAIC();
		
		if (iTyp==0 || iArt==0)
		{
			Static.printError("DefFormular: ohne Typ und ohne Art nicht speicherbar");
			Qry.free();
			return;
		}
		if (iTyp<0)
		{
			Qry.add("aic_code",iArt);
			Qry.add0("aic_STAMMTYP",0);
			Qry.add0("AIC_ROLLE",0);		
		}
		else
		{
//		if(CboArt.getSelectedAIC()!=0)
			Qry.add(CboTyp.getSelectedKennung(),iArt);
			if(iTyp==2 && CboStammtypen.getSelectedAIC()!=0)
				Qry.add("AIC_STAMMTYP",CboStammtypen.getSelectedAIC());		
			Qry.add0("AIC_ROLLE",CboRolle.getSelectedAIC());
		}
		int iProg=CboProg.getSelectedAIC();
        Qry.add0("Prog",iProg);
        boolean bWeb=CbxWeb.isSelected();
		if (iTyp!=2)
			Qry.add0("AIC_BEWEGUNGSTYP",0);
		if(bNew)
		{
			g.printInfo("Neu: Copy="+bCopy);
                        Formular.clearForm();
			int iOld_AIC_Formular = bCopy ? ((Integer)((Vector)Node.getUserData()).elementAt(2)).intValue() : -1;
			
            Qry.add("AIC_Begriffgruppe",iAIC_Begriffgruppe);
			Qry.add("Kennung",sKennung);
            Qry.add("DefBezeichnung",sBezeichnung);
//            Qry.add("Homepage",TxtCss.getText());
//            Qry.add("Kennzeichen",TxtKennzeichen.getText());
            Qry.add("Alias",TxtAlias.getText());
			Qry.add("bits",Bits());
            Qry.add0("Jeder",CbxJeder.isSelected());
            Qry.add0("Combo",CbxCombo.isSelected());
            Qry.add0("Web",bWeb);
            Qry.add0("Tod",CbxTod.isSelected());
			Qry.add("aic_logging",g.getLog());
			iAIC_Begriff=Qry.insert("Begriff",true);
			g.setJeder(iAIC_Begriff, CbxJeder.isSelected() ? 1:0);
			Qry.add("AIC_Begriff",iAIC_Begriff);
			//if (iBenutzergruppe != 0)
			//	Qry.add("AIC_Benutzergruppe",iBenutzergruppe);
            //if(CboEigenschaft.getSelectedAIC()!=0)
            Qry.add0("AIC_EIGENSCHAFT",CboEigenschaft.getSelectedAIC());
            Qry.add0("AIC_Stamm",CboStamm.getStamm());
            Qry.add0("AIC_Abfrage",CboAbfrage.getSelectedAIC());
            Qry.add0("AIC_Modell",CboModell.getSelectedAIC());
            Qry.add("FXML",TxtFxml.getText());  
            int iAIC_Formular=Qry.insert("Formular",true);
            g.setMemo(Memo.getValue(),"",g.iTabBegriff,iAIC_Begriff,0);
            g.setTooltip(Tooltip.getValue(),g.iTabBegriff,iAIC_Begriff,0);
            String sBildSw= BtnBildSw==null ? null:BtnBildSw.getFilename();
            String sBildFX= BtnBildFX==null ? null:BtnBildFX.getFilename();
            if (BtnBildSw!=null && (BtnBildSw.Modified() || bCopy))
            	g.setImage(sBildSw, g.iTabBegriff, iAIC_Begriff, Global.iSpSw);
            if (BtnBildFX!=null && (BtnBildFX.Modified() || bCopy))
            	g.setImage(sBildFX, g.iTabBegriff, iAIC_Begriff, Global.iSpFX);
			//int iAIC_Tabellenname = g.TabTabellenname.posInhalt("Kennung","BEGRIFF") ? g.TabTabellenname.getI("AIC") : -1;
            if (TxtBezeichnung.Modified() || bCopy)
              g.setBezeichnung("",TxtBezeichnung.getText(),g.iTabBegriff,iAIC_Begriff,0);
            if (OutZ!=null)
				saveZ(iAIC_Begriff);
			if(bCopy)
				copy_Darstellung(iOld_AIC_Formular,iAIC_Formular);

			int iStm=CboStammtypen.getSelectedAIC();
			long lBits2=Bits();
			
			g.putTabBegriffe(iAIC_Begriffgruppe,iAIC_Begriff,sKennung,TxtBezeichnung.Modified() || bCopy ? TxtBezeichnung.getText():sBezeichnung,sBezeichnung,sBildSw,0,null/*TxtCss.getText()*/,iTyp==-1?iArt:0,
					iProg,iTyp==1?iArt:iTyp==2?iStm:0,iTyp==2?iArt:0,CboRolle.getSelectedAIC(),
					lBits2,CbxCombo.isSelected(),CbxWeb.isSelected(),0,null,CbxTod.isSelected(),Tooltip.getValue(),null,null/*TxtKennzeichen.getText(),TxtAlias.getText()*/,sBildFX,null,true);
			g.SaveDefVerlauf(iAIC_Begriff,bCopy ? "Kopie von "+TxtKennung.getOld():"neu");
			Vector<Object> VecVisible = new Vector<Object>();
			Vector<Number> VecInvisible = new Vector<Number>();
			VecVisible.addElement(Static.beiLeer(sBezeichnung,sKennung));
			VecVisible.addElement(sKennung);
            VecVisible.addElement(CboRolle.Cbo.getSelectedBezeichnung());
            VecVisible.addElement(null);
            VecVisible.addElement(new Zeit(new java.util.Date(),"dd.MM.yyyy"));
            VecVisible.addElement(CboProg.getSelectedBezeichnung());
//            VecVisible.addElement(TxtCss.getText());
            VecVisible.addElement(TxtAlias.getText());
            VecVisible.addElement(TxtFxml.getText());
//            VecVisible.addElement(TxtKennzeichen.getText());
            
//            VecVisible.addElement((lBits2&Formular.cstFertig)>0 ? "x":"");
			//VecVisible.addElement(CboBenutzergruppe.getSelectedItem());
			VecInvisible.addElement(iAIC_Begriff);
			VecInvisible.addElement(CboEigenschaft.getSelectedAIC()/*iBenutzergruppe*/);
			VecInvisible.addElement(iAIC_Formular);
			VecInvisible.addElement(lBits2);
                        //if(iTyp>0)
                        {
                          VecInvisible.addElement(iTyp == 2 ? iArt : 0);
                          VecInvisible.addElement(CboStamm.getStamm());
                          VecInvisible.addElement(CboRolle.getSelectedAIC());
                          VecInvisible.addElement(CboAbfrage.getSelectedAIC());
                          VecInvisible.addElement(CboModell.getSelectedAIC());
                          VecInvisible.addElement(CboProg.getSelectedAIC());
                        }
			Node= new JCOutlinerNode(VecVisible,getNodeByTypAndArt());
			Node.setUserData(VecInvisible);
			/*
			if(iTyp==2)
			{
				g.TabStammtypen.posInhalt("AIC",new Integer(iStm));
				Image GifStm =(Image)g.TabStammtypen.getInhalt("Bild");
				if (GifStm != null)
				{
					JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
					StyFolder.setItemIcon(GifStm);
					Node.setStyle(StyFolder);
				}
			}
			else*/
			Node.setStyle(bWeb ? StyWeb/*:(lBits2&Formular.cstFertig)>0 ? StyFertig:(lBits2&Formular.cstJavaFX)>0 ? StyFX*/:StyFolderFormular);

			//Out.selectNode(Node,null);
			//Out.folderChanged(Out.getRootNode());
                        Static.makeVisible(Out,Node);

			bNew=false;
			Out_itemStateChanged();
		}
		else if(bEdit || bZuO)
		{
			//int iAIC_Begriff=((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
			Qry.open("SELECT formular.aic_formular AIC_Formular FROM begriff"+g.join2("formular","begriff")+" WHERE begriff.aic_begriff="+iAIC_Begriff);
			int iAIC_Formular=!Qry.eof() ? Qry.getI("AIC_Formular") : -1;
			Qry.close();
			//int iAIC_Tabellenname = g.TabTabellenname.posInhalt("Kennung","BEGRIFF") ? g.TabTabellenname.getI("AIC") : -1;
			//int	iAIC_Begriffgruppe =g.TabBegriffgruppen.posInhalt("Kennung","Frame") ? g.TabBegriffgruppen.getI("AIC") : -1;

			g.testInfo(CboTyp.getSelectedKennung()+"-"+CboTyp.getSelectedAIC()+":"+CboArt.getSelectedKennung()+"-"+CboArt.getSelectedAIC());

//			if(CboArt.getSelectedAIC()!=0)
//				Qry.add(CboTyp.getSelectedKennung(),CboArt.getSelectedAIC());
//			else
//				Qry.add(CboTyp.getSelectedKennung(),"");
//			if (CboTyp.getSelectedAIC()>0 && CboProg.Modified())
//				Qry.add0("AIC_CODE",CboProg.getSelectedAIC());
//			if(CboTyp.getSelectedAIC()==2)
//			{
//				if(CboStammtypen.getSelectedAIC()!=0)
//					Qry.add("AIC_STAMMTYP",CboStammtypen.getSelectedAIC());
//				else
//					Qry.add("AIC_STAMMTYP","");
//			}
//
//			if(CboTyp.getSelectedAIC()==1)
//				Qry.add("AIC_BEWEGUNGSTYP","");
			long lBits2=Bits();
			Qry.add("Kennung",sKennung);
            Qry.add("DefBezeichnung",sBezeichnung);
            //Qry.add0("AIC_Rolle", CboRolle.getSelectedAIC()); // schon oben
			Qry.add("bits",lBits2);
			Qry.add0("Jeder",CbxJeder.isSelected());
			Qry.add0("Combo",CbxCombo.isSelected());
			Qry.add0("Web",bWeb);
			Qry.add0("Tod",CbxTod.isSelected());
			Qry.add("aic_logging",g.getLog());
//			Qry.add("Homepage",TxtCss.getText());
//			Qry.add("Kennzeichen",TxtKennzeichen.getText());
	        Qry.add("Alias",TxtAlias.getText());
			Qry.update("Begriff",iAIC_Begriff);
			g.setJeder(iAIC_Begriff, CbxJeder.isSelected() ? 1:0);
			//if (iBenutzergruppe == 0)
			//	Qry.add("AIC_Benutzergruppe","");
			//else
			//	Qry.add("AIC_Benutzergruppe",iBenutzergruppe);
                        if(CboEigenschaft.Cbo.Modified() || CboStamm.Modified() || CboAbfrage.Cbo.Modified() || CboModell.Cbo.Modified() || TxtFxml.Modified())
                        {
                          Qry.add0("AIC_EIGENSCHAFT", CboEigenschaft.getSelectedAIC());
                          Qry.add0("AIC_Stamm", CboStamm.getStamm());
                          Qry.add0("AIC_ABFRAGE", CboAbfrage.getSelectedAIC());
                          Qry.add0("AIC_Modell",CboModell.getSelectedAIC());
                          Qry.add("FXML",TxtFxml.getText());                         
                          Qry.update("Formular", iAIC_Formular);
                        }

			if (TxtBezeichnung.Modified())
                          g.setBezeichnung("",TxtBezeichnung.getText(),Global.iTabBegriff,iAIC_Begriff,0);
			if (OutZ!=null)
				saveZ(iAIC_Begriff);
			if (Memo.Modified())
				g.setMemo(Memo.getValue(),"",Global.iTabBegriff,iAIC_Begriff,0);
            if (Tooltip.Modified())
				g.setTooltip(Tooltip.getValue(),Global.iTabBegriff,iAIC_Begriff,0);
            String sBildSw= BtnBildSw==null ? null:BtnBildSw.getFilename();
            String sBildFX= BtnBildFX==null ? null:BtnBildFX.getFilename();
            if (BtnBildSw!=null && BtnBildSw.Modified())
            	g.setImage(sBildSw, Global.iTabBegriff, iAIC_Begriff, Global.iSpSw);
            if (BtnBildFX!=null && BtnBildFX.Modified())
            	g.setImage(sBildFX, Global.iTabBegriff, iAIC_Begriff, Global.iSpFX);
            //int iTyp=CboTyp.getSelectedAIC();
			//int iArt=CboArt.getSelectedAIC();
			int iStm=CboStammtypen.getSelectedAIC();
			g.putTabBegriffe(iAIC_Begriffgruppe,iAIC_Begriff,sKennung,TxtBezeichnung.getText(),sBezeichnung,sBildSw,0,null/*TxtCss.getText()*/,iTyp==-1?iArt:0,iProg,iTyp==1?iArt:iTyp==2?iStm:0,iTyp==2?iArt:0,CboRolle.getSelectedAIC(),
					lBits2,CbxCombo.isSelected(),CbxWeb.isSelected(),0,null,CbxTod.isSelected(),Tooltip.getValue(),null,null/*TxtKennzeichen.getText(),TxtAlias.getText()*/,sBildFX,null,false);
			String sSave=cCbx(CbxWeb)+cCbx(CbxTod)+cCbx(CbxCombo)+cCbx(CbxJeder);
			g.SaveDefVerlauf(iAIC_Begriff,sSave.equals("") ? "Parameter":"u.a."+sSave);
			Vector<Object> VecVisible = new Vector<Object>();
			Vector<Number> VecInvisible = new Vector<Number>();
			VecVisible.addElement(Static.beiLeer(sBezeichnung,sKennung));
			VecVisible.addElement(sKennung);
            VecVisible.addElement(CboRolle.Cbo.getSelectedBezeichnung());
            VecVisible.addElement(getInfo(lBits2));
            VecVisible.addElement(new Zeit(new java.util.Date(),"dd.MM.yyyy"));
            VecVisible.addElement(CboProg.getSelectedBezeichnung());
//            VecVisible.addElement(TxtCss.getText());
            VecVisible.addElement(TxtAlias.getText());
            VecVisible.addElement(TxtFxml.getText());
//            VecVisible.addElement(TxtKennzeichen.getText());
//            VecVisible.addElement(TxtAlias.getText());
//            VecVisible.addElement((lBits2&Formular.cstFertig)>0 ? "x":"");
			//VecVisible.addElement(CboBenutzergruppe.getSelectedItem());
			VecInvisible.addElement(iAIC_Begriff);
			VecInvisible.addElement(CboEigenschaft.getSelectedAIC()/*iBenutzergruppe*/);
			VecInvisible.addElement(iAIC_Formular);
			VecInvisible.addElement(lBits2);
                        //if(iTyp>0)
                        {
                          VecInvisible.addElement(iTyp == 2 ? iArt : 0);
                          VecInvisible.addElement(CboStamm.getStamm());
                          VecInvisible.addElement(CboRolle.getSelectedAIC());
                          VecInvisible.addElement(CboAbfrage.getSelectedAIC());
                          VecInvisible.addElement(CboModell.getSelectedAIC());
                          VecInvisible.addElement(CboProg.getSelectedAIC());
                        }
                        if (CboArt.Modified())
                        {
                          //JCOutlinerFolderNode NodeParent = Node.getParent();
                          Node.getParent().removeChild(Node);
                          Node = new JCOutlinerNode(VecVisible, getNodeByTypAndArt());
                        }
                        else
                          Node.setLabel(VecVisible);
			Node.setUserData(VecInvisible);

			/*
			if(iTyp==2)
			{
				g.TabStammtypen.posInhalt("AIC",new Integer(iStm));
				Image GifStm =(Image)g.TabStammtypen.getInhalt("Bild");
				if (GifStm != null)
				{
					JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
					StyFolder.setItemIcon(GifStm);
					Node.setStyle(StyFolder);
				}
			}
			else*/
			Node.setStyle(bWeb ? StyWeb/*:(lBits2&Formular.cstFertig)>0 ? StyFertig:(lBits2&Formular.cstJavaFX)>0 ? StyFX*/:StyFolderFormular);

			//Out.selectNode(Node,null);
			//Out.folderChanged(Out.getRootNode());
                        Static.makeVisible(Out,Node);

			bEdit=false;
			bCopy=false;
			Out_itemStateChanged();
		}
		Qry.free();

	}

	private String cCbx(AUCheckBox Cbx)
	{
		if (Cbx.Modified())
			return " "+Cbx.getText()+(Cbx.isSelected() ? " gesetzt":" entfernt");
		else
			return "";
	}

	private JCOutlinerFolderNode getNodeByTypAndArt()
	{
		int iAIC = CboTyp.getSelectedAIC();
		//g.printInfo((iAIC==0 ? "F" : iAIC==1 ? "S" : "E" )+CboArt.getSelectedAIC());
		return(TabArten.posInhalt("Art",(iAIC==-1 ? "F" : "S" )+(iAIC==2?CboStammtypen.getSelectedAIC():CboArt.getSelectedAIC())) ? (JCOutlinerFolderNode)TabArten.getInhalt("Node") : null);
	}

	private void copy_Darstellung(int iOld_AIC_Formular, int iNew_AIC_Formular)
	{
		SQL Qry = new SQL(g);
		Tabellenspeicher TabDarstellung = new Tabellenspeicher(g,"SELECT AIC_Darstellung,Dar_AIC_Darstellung,AIC_Begriff,Reihenfolge,x,y,w,h,Align,HGap,VGap,Pos,i1,i2,null AIC_New_Darstellung FROM Darstellung WHERE AIC_Formular="+iOld_AIC_Formular+" order by AIC_Darstellung",true);
		while(!TabDarstellung.eof())
		{
			int iDar_AIC_Darstellung=TabDarstellung.getI("Dar_AIC_Darstellung");
			//g.debugInfo("iDar_AIC_Darstellung="+iDar_AIC_Darstellung);
			if(iDar_AIC_Darstellung!=0)
			{
				TabDarstellung.push();
				if (TabDarstellung.posInhalt("AIC_Darstellung",iDar_AIC_Darstellung))
					Qry.add("Dar_AIC_Darstellung",TabDarstellung.getI("AIC_New_Darstellung"));
				else
					Static.printError("Formularerstellung.copy_Darstellung(): Vorgänger von "+iDar_AIC_Darstellung+" nicht gefunden!");
				TabDarstellung.pop();
			}
			Qry.add("AIC_Formular",iNew_AIC_Formular);
			Qry.add("AIC_Begriff",TabDarstellung.getS("AIC_Begriff"));
			Qry.add0("Align",TabDarstellung.getI("Align"));
			Qry.add("Reihenfolge",TabDarstellung.getI("Reihenfolge"));
			Qry.add("X",TabDarstellung.getS("X"));
			Qry.add("Y",TabDarstellung.getS("Y"));
			Qry.add("W",TabDarstellung.getS("W"));
			Qry.add("H",TabDarstellung.getS("H"));
			Qry.add("HGap",TabDarstellung.getS("HGap"));
			Qry.add("VGap",TabDarstellung.getS("VGap"));
			Qry.add("Pos",TabDarstellung.getS("Pos"));
			Qry.add("i1",TabDarstellung.getS("i1"));
			Qry.add("i2",TabDarstellung.getS("i2"));
			int iNew_AIC_Darstellung=Qry.insert("Darstellung",true);
			//int i=4;
			//g.debugInfo(""+5/(i-4));
			TabDarstellung.setInhalt("AIC_New_Darstellung",new Integer(iNew_AIC_Darstellung));

			TabDarstellung.moveNext();
		}
		Qry.free();
	}
	
	private int getAic()
	{
		if (bOut)
		{
			bOut=false;
			return -1;
		}
		if (Out==null || Out.getSelectedNode()==null)
			return 0;
		JCOutlinerNode NodeSelected = Out.getSelectedNode();
        return ((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue();
	}

        private Tabellenspeicher Info()
        {
//          JCOutlinerNode NodeSelected = Out.getSelectedNode();
//          int iAIC_Begriff = ((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue();
        	int iAIC_Begriff=getAic();
          Tabellenspeicher Tab = new Tabellenspeicher(g, "select distinct 'Mandant' Art" + g.AU_Bezeichnung("Mandant") +
                                                      ",kennung,null Prog,aic_mandant aic from mandant where aic_begriff=" + iAIC_Begriff, true);
          new Tabellenspeicher(g, "select 'Benutzergruppe' Art" + g.AU_Bezeichnung("Benutzergruppe") +
                               ",kennung,null Prog,aic_benutzergruppe aic from benutzergruppe where aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
          new Tabellenspeicher(g, "select 'Formular' Art, defBezeichnung Bezeichnung,kennung"+g.AU_Bezeichnung1("Code", "Begriff")+" Prog,begriff.aic_begriff aic from darstellung" +
                               g.join("formular", "darstellung") + g.join("begriff", "formular") + " where darstellung.aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
          new Tabellenspeicher(g, "select 'Formularmenge' Art, defBezeichnung Bezeichnung,kennung,null Prog,begriff.aic_begriff aic from begriff" +
                               " join begriff_zuordnung z on z.aic_begriff=begriff.aic_begriff and aic_begriffgruppe=" +
                               g.TabBegriffgruppen.getAic("Formularmenge") + " and z.beg_aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
          new Tabellenspeicher(g, "select 'Modul' Art, defBezeichnung Bezeichnung,kennung"+g.AU_Bezeichnung1("Code", "Begriff")+" Prog,begriff.aic_begriff aic from begriff" +
                               " join begriff_zuordnung z on z.aic_begriff=begriff.aic_begriff and aic_begriffgruppe=" +
                               g.TabBegriffgruppen.getAic("Modul") + " and z.beg_aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
          new Tabellenspeicher(g, "select 'Applikation' Art, defBezeichnung Bezeichnung,kennung"+g.AU_Bezeichnung1("Code", "Begriff")+" Prog,begriff.aic_begriff aic from begriff" +
                               " join begriff_zuordnung z on z.beg_aic_begriff=begriff.aic_begriff and aic_begriffgruppe=" +
                               g.TabBegriffgruppen.getAic("Applikation") + " and z.aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
          new Tabellenspeicher(g, "select 'Spalte in' Art, defBezeichnung Bezeichnung,begriff.kennung"+g.AU_Bezeichnung1("Code", "Begriff")+" Prog,begriff.aic_begriff aic from begriff" +
                               " join abfrage a on a.aic_begriff=begriff.aic_begriff join spalte s on s.aic_abfrage=a.aic_abfrage and s.beg_aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
          return Tab;
        }
	private void Delete()
	{
		//String s1=Qry.list("kennung","mandant where aic_begriff="+iAIC_Begriff);
                //String s2=Qry.list("kennung","benutzergruppe where aic_begriff="+iAIC_Begriff);
		//String s3=Qry.list("defBezeichnung","darstellung"+g.join("formular","darstellung")+g.join("begriff","formular")+" where darstellung.aic_begriff="+iAIC_Begriff);
		//String s=(s1.equals(" ")?"":"\nMandant:"+s1)+(s2.equals(" ")?"":"\nBenutzergruppe:"+s2)+(s3.equals(" ")?"":"\nFormulare:"+s3);
		//if (!s.equals(""))
		//		new Message(Message.WARNING_MESSAGE,null,g).showDialog("BereitsVerwendet",new String[] {s});
//                JCOutlinerNode NodeSelected = Out.getSelectedNode();
//                int iAIC_Begriff = ((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue();
                int iAIC_Begriff=getAic();
                if (iAIC_Begriff==0)
                	return;
                Tabellenspeicher Tab=Info();
                if(Tab.isEmpty())
		{
            JCOutlinerNode NodeSelected = Out.getSelectedNode();
			int pane = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",new String[] {"["+((String)((Vector)NodeSelected.getLabel()).elementAt(0))+"]"});
			if(pane== Message.YES_OPTION)
			{

				/*Qry.open("SELECT AIC_Formular FROM Formular WHERE AIC_Begriff="+iAIC_Begriff);
				int iAIC_Formular=!Qry.eof() ? Qry.getI("AIC_Formular"):-1;
				Qry.close();*/
                           SQL Qry = new SQL(g);

				int iAIC_Formular=getForm(g,iAIC_Begriff);

				if(iAIC_Formular>0)
				{
					Qry.exec("DELETE FROM Darstellung WHERE AIC_Formular="+iAIC_Formular);
					Qry.exec("DELETE FROM Formular WHERE AIC_Formular="+iAIC_Formular);
				}
				Qry.exec("DELETE FROM Fensterpos WHERE AIC_Begriff="+iAIC_Begriff);
				Qry.exec("DELETE FROM Begriff_Zuordnung WHERE AIC_Begriff="+iAIC_Begriff);
				Qry.exec("DELETE FROM Begriff_Zuordnung WHERE BEG_AIC_Begriff="+iAIC_Begriff);
				Qry.deleteFrom("Begriff","Begriff WHERE AIC_Begriff="+iAIC_Begriff,g.iTabBegriff);

				g.TabBegriffe.clearInhaltS("AIC",new Integer(iAIC_Begriff));
				g.TabFensterpos.clearInhaltS("vFenster",new Integer(iAIC_Begriff));

				Vector VecParChildren = NodeSelected.getParent().getChildren();
				int iPos= VecParChildren.indexOf(NodeSelected);
				Out.selectNode(iPos>0 ? (JCOutlinerNode)VecParChildren.elementAt(iPos-1):NodeSelected.getParent(),null);
				NodeSelected.getParent().removeChild(NodeSelected);
				Out.folderChanged(Out.getRootNode());
                                Qry.free();
				Out_itemStateChanged();
				EnableButtons();
			}
		}
		else
		  new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,(JFrame)thisFrame,g).showDialog("BereitsVerwendet",new String[] {(String)((Vector)Out.getSelectedNode().getLabel()).elementAt(0)},Tab);

	}

	private void EnableButtons()
	{
		JCOutlinerNode NodeSelected = Out.getSelectedNode();
		boolean bLevel3 = NodeSelected!=null ? NodeSelected.getLevel()==3 : false;


		//BtnZeigen.setEnabled(bLevel3);
		//BtnDarstellung.setEnabled(bLevel3);
		MnuDarstellung.setEnabled(bLevel3);
		MnuDarstellung2.setEnabled(bLevel3);
                MnuDefVerlauf.setEnabled(bLevel3);
		MnuShow.setEnabled(bLevel3);// && !CbxJavaFX.isSelected());
                MnuInfo.setEnabled(bLevel3);
		BtnNew.setEnabled(!(bNew || bEdit) && NodeSelected!=null);
		BtnDelete.setEnabled(g.Def() && bLevel3 && !(bNew || bEdit));
		MnuDelete.setEnabled(BtnDelete.isEnabled());
		BtnCopy.setEnabled(bLevel3 && bCopy);
		BtnOk.setEnabled(bNew || bZuO || bEdit && !bCopy);
		BtnAbbruch.setEnabled(bNew || bEdit);

		TxtDefBezeichnung.setEnabled(bLevel3 || bNew);
        TxtBezeichnung.setEnabled(bLevel3 || bNew);
		TxtKennung.setEnabled(bLevel3 || bNew);
//		TxtCss.setEnabled(CbxJavaFX!=null && CbxJavaFX.isSelected() && (bLevel3 || bNew));
//		TxtFxml.setEnabled(CbxJavaFX!=null && CbxJavaFX.isSelected() && (bLevel3 || bNew));
//		TxtKennzeichen.setEnabled(CbxJavaFX!=null && CbxJavaFX.isSelected() && (bLevel3 || bNew));
//		TxtAlias.setEnabled(CbxJavaFX!=null && CbxJavaFX.isSelected() && (bLevel3 || bNew));
		Memo.setEnabled(bLevel3 || bNew);
        Tooltip.setEnabled(bLevel3 || bNew);
		CbxAutoSave.setEnabled(bLevel3 || bNew);
		CbxNotResizeable.setEnabled(bLevel3 || bNew);
		CbxNotClose.setEnabled(bLevel3 || bNew);
		CbxStdFormular.setEnabled(bLevel3 || bNew);
		CbxKopierbar.setEnabled(bLevel3 || bNew);
		CbxNurOrdner.setEnabled(bLevel3 || bNew);
		CbxBewVoll.setEnabled(bLevel3 || bNew);
		CbxKeinStamm.setEnabled(bLevel3 || bNew);
		CbxSynchron.setEnabled(bLevel3 || bNew);
		CbxVerteiler.setEnabled(bLevel3 || bNew);
                CbxRefresh.setEnabled(bLevel3 || bNew);
                CbxMehrfach.setEnabled(bLevel3 || bNew);
                //RbnNormal.setEnabled(bLevel3 || bNew);
                //RbnTerminal.setEnabled(bLevel3 || bNew);
                CbxLogin.setEnabled(bLevel3 || bNew);
                if (RadKeinZR!=null)
                {
                  RadMonat.setEnabled(bLevel3 || bNew);
                  RadWoche.setEnabled(bLevel3 || bNew);
                  RadTag.setEnabled(bLevel3 || bNew);
                  RadKeinZR.setEnabled(bLevel3 || bNew);
                }
                CbxKeinStammTitel.setEnabled(bLevel3 || bNew);
                CbxOhneStamm.setEnabled(bLevel3 || bNew);
                CbxKeinReadOnly.setEnabled(bLevel3 || bNew);
                CbxPopup.setEnabled(bLevel3 || bNew);
                CbxNeuCheck.setEnabled(bLevel3 || bNew);
                CbxHGB.setEnabled(bLevel3 || bNew);
                CbxBewBew.setEnabled(bLevel3 || bNew);
                CbxSubRollen.setEnabled(bLevel3 || bNew);
                CbxRefresh2.setEnabled(bLevel3 || bNew);
                CbxStammFix.setEnabled(bLevel3 || bNew);
                CbxMultiselect.setEnabled(bLevel3 || bNew);
                CbxNbMulti.setEnabled(bLevel3 || bNew);
                CbxRepaint.setEnabled(bLevel3 || bNew);
                CbxKeinZR.setEnabled(bLevel3 || bNew);
//                CbxJavaFX.setEnabled(bLevel3 || bNew);
                CbxAllSync.setEnabled(bLevel3 || bNew);
                CbxInfo.setEnabled(bLevel3 || bNew);
//                CbxFertig.setEnabled(bLevel3 || bNew);
//                boolean bJavaFX=CbxJavaFX.isSelected() && (bLevel3 || bNew);
                boolean bWeb=CbxWeb.isSelected() && (bLevel3 || bNew);
//                CbxOnTop.setEnabled(bJavaFX);
                CbxFullScreen.setEnabled(bLevel3 || bNew);
//                CbxCenter.setEnabled(bJavaFX);
                CbxTabFom.setEnabled(CbxWeb.isSelected());
                CbxWeb.setEnabled(bLevel3 || bNew);
                CbxPers.setEnabled(bLevel3 || bNew);
                CbxNotHandy.setEnabled(bLevel3 || bNew);
                CbxFxHS.setEnabled(bWeb);
//                CbxMenu.setEnabled(bJavaFX);
                //CbxAP.setEnabled(bJavaFX);
//                CbxFomTitel.setEnabled(bJavaFX);
//                CbxNoDetach.setEnabled(bJavaFX);
//                CbxNoArrow.setEnabled(bJavaFX);  
//                CbxKeinST.setEnabled(bJavaFX); 
//                RadStage.setEnabled(bJavaFX);
//                RadDialog.setEnabled(bJavaFX);
//                RadPopOver.setEnabled(bJavaFX);
//                RadSubScene.setEnabled(bJavaFX);
//                RadBtnScene.setEnabled(bJavaFX);
//                RadDrawer.setEnabled(bJavaFX);
//                if (RadDec!=null)
//                {
//                  RadDec.setEnabled(bJavaFX);
//                  RadUnDec.setEnabled(bJavaFX);
//                  RadTrans.setEnabled(bJavaFX);
//                  RadUtil.setEnabled(bJavaFX);
//                }
                CbxJeder.setEnabled(bLevel3 || bNew);
                CbxCombo.setEnabled(bLevel3 || bNew);
                CbxTod.setEnabled(bLevel3 || bNew);
                
		//CboBenutzergruppe.setEnabled(bLevel3 || bNew);
		CboTyp.setEnabled(bLevel3 || bNew);
		CboArt.setEnabled(bLevel3 || bNew);
                CboEigenschaft.setEnabled(bLevel3 || bNew);
                CboRolle.setEnabled(bLevel3 || bNew);
                CboStamm.setEnabled(bLevel3 || bNew);
                CboAbfrage.setEnabled(bLevel3 || bNew);
                CboModell.setEnabled(bLevel3 || bNew);

		CboStammtypen.setEnabled((bLevel3 || bNew) && CboTyp.getSelectedKennung().equals("AIC_BEWEGUNGSTYP"));
		CboProg.setEnabled(bLevel3 || bNew);// && CboTyp.getSelectedAIC()>0);
		if (!bLevel3)
			CboProg.setSelectedAIC(0);
		if (NodeSelected==null || NodeSelected.getLevel()<2 || CboTyp.getSelectedAIC()<0)
			CboStammtypen.setSelectedAIC(0);
		if (NodeSelected==null || NodeSelected.getLevel()<2)
			CboArt.setSelectedAIC(0);
		if (Version.V18())
		{
//			BtnSave2.setEnabled(bWeb && !bNew);
			if (BtnRefr2!=null) BtnRefr2.setEnabled(bWeb && !bNew);
			if (BtnAddStt!=null) BtnAddStt.setEnabled(bWeb);
			if (BtnAddMod!=null) BtnAddMod.setEnabled(bWeb);
			if (BtnAddAbf!=null) BtnAddAbf.setEnabled(bWeb);
			if (BtnAddPnt!=null) BtnAddPnt.setEnabled(bWeb);
			if (BtnWeg2!=null) BtnWeg2.setEnabled(bWeb);
			if (BtnUp != null)
			{
			  BtnUp.setEnabled(bWeb);
			  BtnDown.setEnabled(bWeb);
			}
		}
	}

        @SuppressWarnings("unchecked")
	private void fill_Outliner()
	{
        	g.fixtestInfo("DefFormular.fill_Outliner");
        	int iAic=getAic();
        	if (iAic==0) iAic=-1;
		TabArten = new Tabellenspeicher(g,new String[] {"Art","Node"});
		NodRoot = (JCOutlinerFolderNode)Out.getRootNode();
		NodRoot.removeChildren();

                TabFormulare = new Tabellenspeicher(g,"SELECT null Node,AIC_CODE,Prog, AIC_Formular, AIC_Modell, begriff.AIC_Begriff, AIC_Stammtyp, AIC_Bewegungstyp,bits,aic_eigenschaft,aic_stamm,aic_rolle,aic_abfrage,tod,fxml,alias"+//",kennzeichen"+
                ",(select ein from logging where aic_logging=begriff.aic_logging) Log FROM formular"+g.join("begriff","formular")+/*" where tod is null"*/" ORDER BY AIC_Stammtyp,AIC_CODE,DefBezeichnung",true);
                //TabFormulare.showGrid();

		Vector<Object> VecVisible = new Vector<Object>();
		Vector<Object> VecInvisible = new Vector<Object>();
		VecVisible.addElement(" "+g.getBegriffS("Show","AU Formular"));
		VecVisible.addElement("AU Formular");
		VecInvisible.addElement(new Integer(-1));

		JCOutlinerFolderNode NodRootFill = new JCOutlinerFolderNode((Object)VecVisible,NodRoot);
		NodRootFill.setUserData(VecInvisible);
		NodRootFill.setState(BWTEnum.FOLDER_CLOSED);
                int iPosBG=g.TabBegriffgruppen.getPos("Kennung","Formulartyp");
        JCOutlinerNodeStyle StyTod = new JCOutlinerNodeStyle();
        StyTod.setForeground(g.ColTod);
        //JCOutlinerNodeStyle StyFX = new JCOutlinerNodeStyle();
        //StyFX.setItemIcon(StyFolderFormular.getItemIcon());
        //StyFX.setForeground(g.ColJavaFX);
		if(iPosBG>=0)
		{
			int iBG=g.TabBegriffgruppen.getI(iPosBG,"AIC");
			for(int iPosC=g.TabCodes.getPos("Gruppe",iBG);iPosC<g.TabCodes.size() && g.TabCodes.getI(iPosC,"Gruppe")==iBG;iPosC++)
			{
				int iAIC_Code = g.TabCodes.getI(iPosC,"AIC");
				VecVisible = new Vector<Object>();
				VecInvisible = new Vector<Object>();
				VecVisible.addElement(g.TabCodes.getS(iPosC,"Bezeichnung"));
				VecVisible.addElement(g.TabCodes.getS(iPosC,"Kennung"));
				VecInvisible.addElement(new Integer(iAIC_Code));
				VecInvisible.addElement("Allgemein");

				JCOutlinerFolderNode NodGruppen = new JCOutlinerFolderNode((Object)VecVisible,NodRootFill);
				NodGruppen.setUserData(VecInvisible);
				NodGruppen.setState(BWTEnum.FOLDER_CLOSED);

				TabArten.addInhalt("Art","F"+iAIC_Code);
				TabArten.addInhalt("Node",NodGruppen);

				//int iAIC_Stammtyp = g.TabStammtypen.getI("AIC");
				for(TabFormulare.posInhalt("aic_code",iAIC_Code);!(TabFormulare.out()||TabFormulare.eof()) && TabFormulare.getI("aic_code")==iAIC_Code;TabFormulare.moveNext())
				{
					int iPos=g.TabBegriffe.getPos("AIC",new Integer(TabFormulare.getI("AIC_Begriff")));
                                        if (iPos>=0)
                                        {
                                          long lBits2=TabFormulare.getL("bits");
                                          VecVisible = new Vector<Object>();
                                          VecInvisible = new Vector<Object>();
                                          VecVisible.addElement(g.TabBegriffe.getS(iPos, "DefBezeichnung"));
                                          VecVisible.addElement(g.TabBegriffe.getS(iPos, "Kennung"));
                                          VecVisible.addElement(null);
                                          VecVisible.addElement(null);
                                          VecVisible.addElement(TabFormulare.isNull("log") ? null : new Zeit(TabFormulare.getTimestamp("log"), "dd.MM.yyyy"));
                                          VecVisible.addElement(g.TabCodes.getBezeichnungS(TabFormulare.getI("Prog")));
//                                          VecVisible.addElement(g.TabBegriffe.getS(iPos,"URL"));
                                          VecVisible.addElement(TabFormulare.isNull("Alias") ? null:TabFormulare.getS("Alias"));
                                          VecVisible.addElement(TabFormulare.isNull("fxml") ? null:TabFormulare.getS("fxml"));
//                                          VecVisible.addElement(TabFormulare.isNull("Kennzeichen") ? null:TabFormulare.getS("Kennzeichen"));
                                          
//                                          VecVisible.addElement((lBits2&Formular.cstFertig)>0 ? "x":"");
                                          VecVisible.addElement(TabFormulare.getI("AIC_Begriff"));
                                          VecVisible.addElement(TabFormulare.getI("AIC_Formular"));
                                          //VecVisible.addElement(CboBenutzergruppe.getBezeichnung(TabFormulare.getI("aic_benutzergruppe")));
                                          VecInvisible.addElement(new Integer(TabFormulare.getI("AIC_Begriff")));
                                          VecInvisible.addElement(new Integer(TabFormulare.getI("AIC_EIGENSCHAFT"))); //TabFormulare.getI("aic_benutzergruppe")));
                                          VecInvisible.addElement(new Integer(TabFormulare.getI("AIC_Formular")));
                                          VecInvisible.addElement(lBits2);
                                          VecInvisible.addElement(0); // Bew
                                          VecInvisible.addElement(0); // Stamm
                                          VecInvisible.addElement(0); // Rolle
                                          VecInvisible.addElement(0); // Abfrage
                                          VecInvisible.addElement(0); // Modell
                                          VecInvisible.addElement(TabFormulare.getI("Prog"));
                                          JCOutlinerNodeStyle Sty=TabFormulare.getB("Tod") ? StyTod:g.TabBegriffe.getB(iPos,"Web") ? StyWeb/*:(lBits2&Formular.cstFertig)>0 ? StyFertig:(lBits2&Formular.cstJavaFX)>0 ? StyFX*/:StyFolderFormular;
                                          if ((Sty==StyTod && (CbxTote==null || CbxTote.isSelected()) //|| (Sty==StyFertig || Sty==StyFX) && (CbxFX==null || CbxFX.isSelected())
                                        		  || Sty==StyWeb && (CbxWebFom==null || CbxWebFom.isSelected()) || Sty==StyFolderFormular && (CbxSwing==null || CbxSwing.isSelected()))
                                        	  && (EdtFilter==null || EdtFilter.isNull() || g.TabBegriffe.getS(iPos, "DefBezeichnung").startsWith(EdtFilter.getText())))
                                          {
                                        	  JCOutlinerNode NodAllg = new JCOutlinerNode(VecVisible, NodGruppen);
                                        	  TabFormulare.setInhalt("Node", NodAllg);
                                        	  NodAllg.setUserData(VecInvisible);
                                        	  NodAllg.setStyle(Sty);   
                                          }
                                        }
				}
			}
		}


		VecVisible = new Vector<Object>();
		VecInvisible = new Vector<Object>();
		VecVisible.addElement(" "+g.getBegriffS("Show","Eingabeformular"));
		VecVisible.addElement("Eingabeformular");
		VecInvisible.addElement(new Integer(1));

		NodRootFill = new JCOutlinerFolderNode((Object)VecVisible,NodRoot);
		NodRootFill.setUserData(VecInvisible);
		NodRootFill.setState(BWTEnum.FOLDER_CLOSED);

		for(int iPosS=0;iPosS<g.TabStammtypen.size();iPosS++)
		{
			VecVisible = new Vector<Object>();
			VecInvisible = new Vector<Object>();
			VecVisible.addElement(g.TabStammtypen.getS(iPosS,"Bezeichnung"));
			VecVisible.addElement(g.TabStammtypen.getS(iPosS,"Kennung"));
                        VecVisible.addElement(null);
                        VecVisible.addElement(null);
                        VecVisible.addElement(null);
			VecInvisible.addElement(g.TabStammtypen.getInhalt("AIC",iPosS));
			VecInvisible.addElement("Stammtyp");

			JCOutlinerFolderNode NodStamm = new JCOutlinerFolderNode((Object)VecVisible,NodRootFill);
			NodStamm.setUserData(VecInvisible);
			NodStamm.setState(BWTEnum.FOLDER_CLOSED);

			Image Gif =Static.bDefBezeichnung ? g.getSttGif(g.TabStammtypen.getI(iPosS,"AIC")):null;
			if (Gif != null)
			{
				JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
				StyFolder.setFolderClosedIcon(Gif);
				StyFolder.setFolderOpenIcon(Gif);
				NodStamm.setStyle(StyFolder);
			}

			TabArten.addInhalt("Art","S"+g.TabStammtypen.getI(iPosS,"AIC"));
			TabArten.addInhalt("Node",NodStamm);

			int iAIC_Stammtyp = g.TabStammtypen.getI(iPosS,"AIC");
			int iAnzS=0;
			for(TabFormulare.posInhalt("aic_stammtyp",new Integer(iAIC_Stammtyp));!(TabFormulare.out()||TabFormulare.eof()) && TabFormulare.getI("aic_Stammtyp")==iAIC_Stammtyp;TabFormulare.moveNext())
			{
				VecVisible = new Vector<Object>();
				VecInvisible = new Vector<Object>();
				int iPos=g.TabBegriffe.getPos("AIC",new Integer(TabFormulare.getI("AIC_Begriff")));
				if (iPos>=0)
				{
					VecVisible.addElement(g.TabBegriffe.getS(iPos,"DefBezeichnung"));
					VecVisible.addElement(g.TabBegriffe.getS(iPos,"Kennung"));
                    VecVisible.addElement(g.TabRollen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Rolle")));//CboRolle.Cbo.getBezeichnung(g.TabBegriffe.getI(iPos,"Rolle")));
                                long lBits2=TabFormulare.getL("bits");
                                VecVisible.addElement(getInfo(lBits2));
                                Zeit Z=TabFormulare.isNull("log")?null:new Zeit(TabFormulare.getTimestamp("log"),"dd.MM.yyyy");
                                VecVisible.addElement(Z);
                                if (Z != null)
                                  ((Vector)NodStamm.getLabel()).setElementAt(Z,4);
                    VecVisible.addElement(g.TabCodes.getBezeichnungS(TabFormulare.getI("Prog")));
//	                VecVisible.addElement(g.TabBegriffe.getS(iPos,"URL"));
                    VecVisible.addElement(TabFormulare.isNull("Alias") ? null:TabFormulare.getS("Alias"));
	                VecVisible.addElement(TabFormulare.isNull("fxml") ? null:TabFormulare.getS("fxml"));
//                    VecVisible.addElement(TabFormulare.isNull("Kennzeichen") ? null:TabFormulare.getS("Kennzeichen"));
//                    
//                    VecVisible.addElement((lBits2&Formular.cstFertig)>0 ? "x":"");
                    VecVisible.addElement(TabFormulare.getI("AIC_Begriff"));
                    VecVisible.addElement(TabFormulare.getI("AIC_Formular"));
					//VecVisible.addElement(CboBenutzergruppe.getBezeichnung(TabFormulare.getI("aic_benutzergruppe")));
					VecInvisible.addElement(TabFormulare.getI("AIC_Begriff"));
					VecInvisible.addElement(TabFormulare.getI("AIC_EIGENSCHAFT"));//TabFormulare.getI("aic_benutzergruppe")));
					VecInvisible.addElement(TabFormulare.getI("AIC_Formular"));
					VecInvisible.addElement(lBits2);
					VecInvisible.addElement(TabFormulare.getI("AIC_Bewegungstyp"));
                                VecInvisible.addElement(TabFormulare.getI("AIC_Stamm"));
                                VecInvisible.addElement(TabFormulare.getI("AIC_Rolle"));
                                VecInvisible.addElement(TabFormulare.getI("AIC_Abfrage"));
                                VecInvisible.addElement(TabFormulare.getI("AIC_Modell"));
                                VecInvisible.addElement(TabFormulare.getI("Prog"));
                                JCOutlinerNodeStyle Sty=TabFormulare.getB("Tod") ? StyTod:g.TabBegriffe.getB(iPos,"Web") ? StyWeb/*:(lBits2&Formular.cstFertig)>0 ? StyFertig:(lBits2&Formular.cstJavaFX)>0 ? StyFX*/:StyFolderFormular;
                                if ((Sty==StyFertig || Sty==StyTod && (CbxTote==null || CbxTote.isSelected()) //|| Sty==StyFX && (CbxFX==null || CbxFX.isSelected())
                                		|| Sty==StyWeb && (CbxWebFom==null || CbxWebFom.isSelected()) || Sty==StyFolderFormular && (CbxSwing==null || CbxSwing.isSelected()))
                                		&& (EdtFilter==null || EdtFilter.isNull() || g.TabBegriffe.getS(iPos, "DefBezeichnung").startsWith(EdtFilter.getText())))
                                {
                                	JCOutlinerNode NodAllg= new JCOutlinerNode(VecVisible,NodStamm);
                                	TabFormulare.setInhalt("Node",NodAllg);
                                	NodAllg.setUserData(VecInvisible);
                                	NodAllg.setStyle(Sty);
                                	iAnzS++;
                                }
				}
			}
			if (RadKeineLeere!=null && RadKeineLeere.isSelected() && iAnzS==0 || RadLeere!=null && RadLeere.isSelected() && iAnzS>0)
				NodRootFill.removeChild(NodStamm);
		}

		Out.folderChanged(Out.getRootNode());
		find(iAic);
	}
        
    public static String getInfo(long lBits)
    {
//    	long lFxArt=lBits&Formular.cstFxArt;
    	return (lBits&Formular.cstMehrfach)>0 ? "MF ":(lBits&Formular.cstStdFormular)>0 ? "Std ":(lBits&Formular.cstPopup)>0 ? "Popup "/*:(lBits&Formular.cstJavaFX)>0 ? "Stage"*/:"";
//        		(lFxArt==Formular.cstPopOver ? "PopOver":lFxArt==Formular.cstScene ? "Sub":lFxArt==Formular.cstBtnScene ? "Btn":lFxArt==Formular.cstDialog ? "Dialog":
//        			lFxArt==Formular.cstDrawer ? "Drawer": (lBits&Formular.cstJavaFX)>0 ? "Stage":"");
    }


// add your data members here
private Global g;
private Image GifFormular;
private JCOutlinerNodeStyle StyFolderFormular = new JCOutlinerNodeStyle();
private JCOutlinerNodeStyle StyFX = new JCOutlinerNodeStyle();
private JCOutlinerNodeStyle StyWeb = new JCOutlinerNodeStyle();
private JCOutlinerNodeStyle StyFertig = new JCOutlinerNodeStyle();
private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
private AUOutliner OutZ = null;//new AUOutliner(new JCOutlinerFolderNode(""));
private JCOutlinerFolderNode NodStt=null;
private JCOutlinerFolderNode NodMod=null;
private JCOutlinerFolderNode NodAbf=null;
private JCOutlinerFolderNode NodDruck=null;
private JCOutlinerFolderNode NodRoot ;
private Text TxtDefBezeichnung = new Text("",255);
private Text TxtBezeichnung = new Text("",255);
private Text TxtKennung = new Text("",98,Text.KENNUNG);
//private Text TxtCss = new Text("",255);
private Text TxtFxml = new Text("",255);
//private Text TxtKennzeichen = new Text("",255);
private Text TxtAlias = new Text("",98);
//private ComboSort CboBenutzergruppe;
private ComboSort CboProg;
private ComboSort CboTyp;
private ComboSort CboArt;
private ComboSort CboStammtypen;
private EigenschaftsEingabe CboEigenschaft;
private RolleEingabe CboRolle;
private StammEingabe CboStamm;
private AbfrageEingabe CboAbfrage;
private AbfrageEingabe CboAbf2;
private ModellEingabe CboModell;
private DruckEingabe CboPnt2;

private ComboSort CboStt2=null;
private ModellEingabe CboMod2=null;
private Tabellenspeicher TabZStt=null;
private Tabellenspeicher TabZBeg=null;
private Vector<Integer> VecStt=new Vector<Integer>();
private Vector<Integer> VecSttp=new Vector<Integer>();
private Vector<Integer> VecSttm=new Vector<Integer>();
private Vector<Integer> VecMod=new Vector<Integer>();
private Vector<Integer> VecModp=new Vector<Integer>();
private Vector<Integer> VecModm=new Vector<Integer>();
private Vector<Integer> VecAbf=new Vector<Integer>();
private Vector<Integer> VecAbfp=new Vector<Integer>();
private Vector<Integer> VecAbfm=new Vector<Integer>();
private Vector<Integer> VecPnt=new Vector<Integer>();
private Vector<Integer> VecPntp=new Vector<Integer>();
private Vector<Integer> VecPntm=new Vector<Integer>();
private int iAnzStt=0;
private int iAnzMod=0;
private int iAnzAbf=0;
private int iAnzDruck=0;

private boolean bNew=false;
private boolean bZuO=false;
private boolean bEdit=false;
private boolean bCopy=false;

private boolean bOut=false;

//private JButton BtnZeigen;
//private JButton BtnDarstellung;
private JButton BtnNew;
private JButton BtnDelete;
private JButton BtnCopy;
private JButton BtnOk;
private JButton BtnAbbruch;
//private JButton BtnDefinition;
private JButton BtnBeenden;

//private JButton BtnSave2;
private JButton BtnRefr2;
private JButton BtnAddStt;
private JButton BtnAddMod;
private JButton BtnAddAbf;
private JButton BtnAddPnt;
private JButton BtnWeg2;
private JButton BtnUp;
private JButton BtnDown;

private String sAktKennung="";
//private String sAktBezeichnung="";
//private int iAktBenutzergruppe=0;
private int iAktTyp=0;
private int iAktBew=0;
private int iAktStt=0;

//private Darstellung Dar;
//private Definition Def;
//private Formular Form;

//private String sAktTyp;

private Tabellenspeicher TabArten;

private JCheckBox CbxAutoSave;
private JCheckBox CbxNotResizeable;
private JCheckBox CbxNotClose;
private JCheckBox CbxStdFormular;
private JCheckBox CbxKopierbar;
private JCheckBox CbxNurOrdner;
private JCheckBox CbxBewVoll;
private JCheckBox CbxKeinStamm;
private JCheckBox CbxSynchron;
private JCheckBox CbxVerteiler;
private JCheckBox CbxRefresh;
private JCheckBox CbxMehrfach;
private JCheckBox CbxLogin;
private JRadioButton RadMonat;
private JRadioButton RadWoche;
private JRadioButton RadTag;
private JRadioButton RadKeinZR;
private JCheckBox CbxKeinStammTitel;
private JCheckBox CbxOhneStamm;
private JCheckBox CbxKeinReadOnly;
private JCheckBox CbxPopup;
private JCheckBox CbxNeuCheck;
private JCheckBox CbxHGB;
private JCheckBox CbxBewBew;
private JCheckBox CbxSubRollen;
private JCheckBox CbxRefresh2;
private JCheckBox CbxStammFix;
private JCheckBox CbxMultiselect;
private JCheckBox CbxNbMulti;
private JCheckBox CbxRepaint;
private JCheckBox CbxKeinZR;
//private JCheckBox CbxJavaFX;
private JCheckBox CbxAllSync;
private JCheckBox CbxInfo;
//private JCheckBox CbxOnTop;
private JCheckBox CbxFullScreen;
//private JCheckBox CbxCenter;
private JCheckBox CbxTabFom;
private AUCheckBox CbxWeb;
private JCheckBox CbxPers;
private JCheckBox CbxNotHandy;
private JCheckBox CbxFxHS;
//private JCheckBox CbxMenu;
//private JCheckBox CbxAP;
//private JCheckBox CbxFomTitel;
//private JCheckBox CbxNoDetach;
//private JCheckBox CbxNoArrow;
//private JCheckBox CbxKeinST;
//private JCheckBox CbxFertig;
private AUCheckBox CbxJeder;
private AUCheckBox CbxCombo;
private AUCheckBox CbxTod;
//private JRadioButton RadStage;
//private JRadioButton RadDialog;
//private JRadioButton RadPopOver;
//private JRadioButton RadSubScene;
//private JRadioButton RadBtnScene;
//private JRadioButton RadDrawer;
//private JRadioButton RadDec;
//private JRadioButton RadUnDec;
//private JRadioButton RadTrans;
//private JRadioButton RadUtil;

private JCheckBox CbxSwing=null;
//private JCheckBox CbxFX=null;
private JCheckBox CbxWebFom=null;
private JCheckBox CbxTote=null;
private JRadioButton RadAlles;
private JRadioButton RadLeere;
private JRadioButton RadKeineLeere;
private Text EdtFilter=null;

private long lBits=0;
private int iTypOld=-2;

private AUTextArea Memo = new AUTextArea();
private AUTextArea Tooltip = new AUTextArea();
private BildEingabe BtnBildSw=null;
private BildEingabe BtnBildFX=null;

private JPopupMenu popup= new JPopupMenu();
private JMenuItem MnuDarstellung;
private JMenuItem MnuDarstellung2;
private JMenuItem MnuDefVerlauf;
private JMenuItem MnuDelete;
private JMenuItem MnuShow;
private JMenuItem MnuInfo;

private Tabellenspeicher TabFormulare;

private static DefFormular Self=null;

  //private long lClock = Static.get_ms();
}

