/*
    All_Unlimited-Hauptmaske-Hauptschirm.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
//import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
//import java.awt.Color;
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

import jclass.util.JCqsort;

//import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JRadioButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JLabel;
//import javax.swing.SwingConstants;

//import javax.swing.SwingUtilities;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
//import javax.swing.plaf.MenuItemUI;
//import javax.swing.UIManager;
//import jclass.bwt.JCItemListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Text;
//import All_Unlimited.Grunddefinition.DefFormular;
//import All_Unlimited.Grunddefinition.Definition;
import All_Unlimited.Grunddefinition.ISQL;
import All_Unlimited.Grunddefinition.Logging;
import All_Unlimited.Grunddefinition.Tsearch;
import All_Unlimited.Print.*;
//import All_Unlimited.Allgemein.Eingabe.AUTextPane;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;

//import java.io.File;
import javax.swing.JToolBar;

import All_Unlimited.Grunddefinition.*;
import All_Unlimited.Allgemein.Eingabe.Schrift;

//import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.UIDefaults;
import javax.swing.JToggleButton;

//import java.awt.*;
//import java.awt.geom.*;
//import javax.swing.*;

public class Hauptschirm extends javax.swing.JFrame
{
	/**
	 *
	 */
	private static final long serialVersionUID = 3004937128925940583L;
	//private static final int AUTO    =0x0008;
	private static final int STT     =0x0010;
	//private static final int ORDNER  =0x0020;
        private static final int OFFEN   =0x0080;
        private static final int STD     =0x0100;
        private static final int JEDER   =0x0200;
//        private static final int SORT    =0x0400;

        private static final int VL      =0x0840;
        private static final int ALLES   =0x0000;
        private static final int VOLLE   =0x0040;
        private static final int LEERE   =0x0800;
        private static final int BEIDE   =0x0840;

        private static final int DEL     =0x1000;
        private static final int AUSTRITT=0x2000;
        //private static final int ANZEIGE =0x4000;
        private static final int FIX     =0x8000;
        private static final int AUSWAHL=0x10000;
        private static final int BEGINN =0x20000;
        private static final int SPALTE1=0x40000;
        private static final int TOOLTIP=0x80000;
        
        public static final int WEB   =0x100000;  

	private Global g;
	private int iBegriff=0;
	private AUOutliner Gid = new AUOutliner();
	private Tabellenspeicher TabSpalten=null;	// enthält Spalten-Bezeichnung mit dazugehöriger Info
	private Tabellenspeicher TabGruppierung;	// für Knotenspeicherung, damit jede Gruppe nur 1x aufgebaut wird
	private Tabellenspeicher TabGruppenFilter;	// wird Gruppenfilter übergeben
	private Tabellenspeicher TabGruppen;		// enthält Aic, Kennung und Bezeichnung der Gruppen
	private Tabellenspeicher TabStyle;		// enthält Abfragefilter der Stammtypen
	//private Tabellenspeicher TabAbfrage2;		// enthält Abfragenummern der Stammtypen
	private Tabellenspeicher TabAktFormular;
	//private JButton BtnSpeichern;
	//private JButton BtnSchliessen;
	//private JButton BtnNeu;
        private JMenuItem MnuLastForm=new JMenuItem("");
        private JMenuItem MnuClose;
	//private JButton BtnEdit;
        private JButton BtnEinstellung;
        private JButton BtnSuche;
        private JButton BtnSpotlight;
	//private JButton BtnEditOk;
	//private JButton BtnImport;
	private JButton BtnSpaltenDef;
	private JButton BtnFilter;
	private JButton BtnAbfrage;
        private JButton BtnAnsicht;
	private JButton BtnGruppenFilter;
	private JButton BtnInit;
        private JButton BtnZRplus;
        private JButton BtnZRminus;
        //private JButton BtnZRplus2;
        //private JButton BtnZRminus2;
        private JToggleButton BtnTag=null;
        private JToggleButton BtnWoche=null;
        private JToggleButton BtnMonat=null;

	private JButton BtnRefresh;
        private JButton BtnRefreshAll;
	private JButton BtnZeitraum;
	//private JButton BtnInfo;
	//private JButton BtnExport;
	private JButton BtnDruckHS;
        private JButton BtnMandant;
        private JButton BtnSystem;
        private JButton BtnHS;
	//private JButton BtnDrucken;
	//private JButton BtnTable;
//	private JButton BtnHelp;
	//private JButton BtnBeenden;

	private JMenu MnuBearbeiten;
        private JMenu MnuEdit2;
        private JMenu MnuEdit3;
        //private JMenu MnuAuswahl;
        //private JMenuItem MnuFilter;
	//private JMenuItem MnuDruck;
	private JMenuItem MnuExport;
        private JMenuItem MnuEMail;
        private JMenuItem MnuGPS;
        private JMenuItem MnuBild;

	//private JPanel PnlNeu = new JPanel(new BorderLayout());
	//private Datum dtZeitpunkt;
	//private JButton BtnDatum = new JButton("...");

	/*private JCheckBox CbxAutoDarstellen;
	private JCheckBox CbxNurOrdner;
	private JCheckBox CbxStammtypen;*/
	//private boolean bAutoDarstellen=false;
	//private boolean bNurOrdner=false;
	private boolean bStammtypen=false;
        private boolean bSttOld=false;
        //private boolean bKeineLeere=false;
        //private boolean bLeere=false;
        private int iVL=0;
        private boolean bOffen=false;
//        private boolean bSort=false;
        private boolean bDel=false;
        private boolean bAustritt=false;
        private boolean bBeginn=false;
        private boolean bSpalte1=false;
        private boolean bTooltip=false;
        //private boolean bMandant=false;

	//private JCheckBox CbxGeloeschte;
	//private ComboSort CboTyp;
	//private ComboSort CboFormular;
        private Tabellenspeicher TabHS;
        private ComboSort CboHS;
        private ComboSort CboStt;
        private ComboSort CboBenutzergruppe;
        private ComboSort CboEbene;
        private ComboSort CboProg;
	private JCOutlinerNode NodVorher = null;
	//private JCOutlinerFolderNode NodSpaltendef;
	private ShowAbfrage Abf=null;
        private boolean bChange=false;
	//private boolean bCbo = false;
	//private int iSTT_Oben = -1;
	//private int iAbfrageOld = 0;
        private int iBitsOld=0;
        private int iStamm_Start = -1;
        private int iForm=0;
	private int iFormOld=0;
	private int iSTT_Mom = -1;
	private int iSTT_Last = -1;
	private int iStamm_Mom = -1;
        private boolean bStamm = false;
	private boolean bAb = true;
	private JCOutlinerNode NodStammMom = null;
	private int iEigenschaft_Mom = -1;
	//private String sBezeichnung_Mom="";
	private Tabellenspeicher TabSTT2;		// zum füllen aller Stammtypen eine Applikation
        Vector<Integer> VecStt=null;
        //private int iAnzahl = 0;
	//private int iAnzahlEingaben = 0;
	private JFrame self = this;
	//private Vector VecElemente = new Vector();
	private Vector<Object> VecEigenschaften;// = new Vector();
	//private boolean bEnde=false;
	private boolean bStdForm=false;
	private JCOutlinerFolderNode NodStamm;
	private JCOutlinerFolderNode NodStt;
	private boolean bNodStammGeladen = false;
	private boolean bNodSttGeladen = false;
	//private boolean bZeitraumFirst = true;
	//private boolean bFilter=false;
        private String sFilterShow="";
	private String sTitel;
	private String sSortiert;
	private Tabellenspeicher TabKnoten;
	//private Formular FomEinstellung=null;
	///private boolean bMitPeriode=false;
	private Point P=null;
	//private AUTextArea Mmo=null;
	private boolean bGidChange=true;
	//private JCheckBox CbxNurOrdner=null;
        //private JCheckBox CbxAnzahl=null;
	private String sSuchtext="";
	//private int iSuchBits=0;
	private int iSpalte=0;
	private int iEnabled;
        private int iHS=0;
        private int iHSold=0;
        //private Hauptschirm Self=null;
        //private int iEnable2=0;
        private JDialog DlgFilter=null;
        private JDialog DlgFormularliste;
        private JCheckBoxMenuItem MnuStt;
        //private JCheckBoxMenuItem MnuAuto;
        private JCheckBoxMenuItem MnuOffen;
//        private JCheckBoxMenuItem MnuSort;
        //private JCheckBoxMenuItem MnuAnzahl;
        private JRadioButtonMenuItem MnuAlles;
        private JRadioButtonMenuItem MnuBeide;
        private JRadioButtonMenuItem MnuKeineLeere;
        private JRadioButtonMenuItem MnuLeere;
        //private JButton BtnOk;
        //private JButton BtnAbbruch;
        private JButton BtnInfo;
        private JButton BtnBeenden2;
        private JButton BtnRefresh2;
        private ActionListener AL1=null;
        private int iRolle=0;
        private int iSttRolle=0;
        private int iAnsicht=0;
        Tabellenspeicher TabRPop=null;
        //private AUCheckBox CbxAnzeigen;
        private int iSttHS=0;
        private JPanel PnlRbt=null;
        private Tabellenspeicher TabRbt=null;
        private ButtonGroup RadGroup=new ButtonGroup();
        private JDialog DlgSuche=null;
        private JDialog DlgSpotlight=null;
        private JCOutlinerNodeStyle StyAustritt = null;
        private Tabellenspeicher TabForm;
        private JPopupMenu popEinStellung=null;
        private JPopupMenu popSystem=null;
        private JPopupMenu popMandant=null;
        private JPopupMenu popHS=null;
        private String sApplikation;
        private AUOutliner GidsB=null;
        private int iSAnz=0;
        private Text EdtSucheSL=null;
        private ComboSort CboModul=null;
        private JPanel PnlX=null;
        private UIDefaults UID;
        private UIDefaults UID2;
        //private int iHAnz=0;
        //private int iMAnz=0;
        private Text EdtBez;
        private Text EdtKenn;
        private AUCheckBox CbxStd;
        private AUCheckBox CbxJeder;
        private AUCheckBox CbxFix;
        private AUCheckBox CbxAuswahl;
        private AUCheckBox CbxWeb=null;
        private AUCheckBox CbxStt=null;
        private AUCheckBox CbxOffen=null;
//        private AUCheckBox CbxSort=null;
        private AUCheckBox CbxAustritt=null;
        private JRadioButton RadAlles=null;
        private JRadioButton RadVolle=null;
        private JRadioButton RadLeere=null;
        private JRadioButton RadBeide=null;
        private JLabel LblBenutzer=new JLabel();
        private AUTextArea MemoHS;
        private AUTextArea TooltipHS;
        private JButton BtnSave;
        private JButton BtnDel;
        private int iFF;
        private Clock clock=null;
        private boolean bCheckOld=false;
        private JLabel LblM;
        //private boolean bEE=false;
        private int iFillStt=-1;
        private Text EdtSuche=null;
        public boolean bFP=false; //ob Fensterpos gespeichert war
        private AUCheckBox CbxAll2=null;
        private AUCheckBox CbxWeb2=null;
        
    public static Hauptschirm get(String sApplikation,Global g)
    {
    	Hauptschirm HS=null;
    	int iPos=g.getPosBegriff("Applikation",sApplikation);
    	if (iPos>=0)
    		iPos=g.TabFormulare.getPos("Aic",g.TabBegriffe.getI(iPos,"Aic"));
    	if (iPos>=0)
    	{
    		HS=(Hauptschirm)g.TabFormulare.getInhalt("Formular",iPos);
    		HS.setVisible(true);
    	}
    	else
    	{
	    	HS=new Hauptschirm(sApplikation,g);
	    	if (HS!=null && HS.iBegriff>0)
	    		g.putTabFormulare(HS.iBegriff, 0, HS);
    	}
    	return HS;
    }

	private Hauptschirm(String rsApplikation,Global glob)
	{
		super(glob.getDB_Name()+(glob.Def()?"Hauptschirm - ":"")+glob.getBegriffS("Applikation",rsApplikation));
                long lClock = Static.get_ms();
                g = glob;
                g.fixtestInfo("Hauptschirm "+rsApplikation);
                //if (Static.cssShowGrid==null)
                /*{
                	String sCss=g.getCss(g.getPosBegriff("Show","showGrid"));
                	if (!Static.Gleich(Static.cssShowGrid, sCss))
                	{
                		Static.cssShowGrid=sCss;
                		//g.fixtestInfo("cssShowGrid="+Static.cssShowGrid);
                	}
                }*/
                iFF=g.getFontFaktor();
                //g.fixInfo("HS "+rsApplikation+":"+iFF);
                //UIManager.getDefaults().put("CheckBoxMenuItemUI.icon", AUCheckBox.unchecked);
                //UIManager.getDefaults().put("CheckBoxMenuItemUI.selectedicon", AUCheckBox.checked);
                //UIManager.put("CheckBoxMenuItemUI","All_Unlimited.Allgemein.Eingabe.AUCheckBox");
                //UIManager.put("CheckBoxMenuItem.Icon", AUCheckBox.unchecked);
                /*UIManager.put("CheckBoxMenuItem.checkIcon", AUCheckBox.unchecked);
                UIManager.put("CheckBoxMenuItem[MouseOver].checkIcon", AUCheckBox.unchecked);
                UIManager.put("CheckBoxMenuItem[Enabled+Selected].checkIcon", AUCheckBox.checked);
                UIManager.put("CheckBoxMenuItem[MouseOver+Selected].checkIcon", AUCheckBox.checked);*/
                //UIManager.put("CheckBoxMenuItem.arrowIcon", AUCheckBox.unchecked);
                //UIManager.put("CheckBoxMenuItem.Icon.InheritDefaults", false);
                //UIManager.put("CheckBoxMenuItem.checkIcon.InheritDefaults", false);

                int iPos=g.getPosBegriff("Applikation",rsApplikation);

                UID = new UIDefaults();
                ImageIcon ImgCbxY=g.getImageIcon(Static.bND ? "cbx1.png":"ok.png");
                ImageIcon ImgCbxN=g.getImageIcon(Static.bND ? "cbx0.png":"cancel.png");
                UID.put("CheckBoxMenuItem.checkIcon", ImgCbxN);
                UID.put("CheckBoxMenuItem[MouseOver].checkIcon", ImgCbxN);
                UID.put("CheckBoxMenuItem[Enabled+Selected].checkIcon", ImgCbxY);
                UID.put("CheckBoxMenuItem[MouseOver+Selected].checkIcon", ImgCbxY);

                UID2 = new UIDefaults();
                ImageIcon ImgRbnY=g.getImageIcon(Static.bND ? "rad1.png":"Rbn_ok.png");
                ImageIcon ImgRbnN=g.getImageIcon(Static.bND ? "rad0.png":"Rbn_cancel.png");
                UID2.put("RadioButtonMenuItem.checkIcon", ImgRbnN);
                UID2.put("RadioButtonMenuItem[MouseOver].checkIcon", ImgRbnN);
                UID2.put("RadioButtonMenuItem[Enabled+Selected].checkIcon", ImgRbnY);
                UID2.put("RadioButtonMenuItem[MouseOver+Selected].checkIcon", ImgRbnY);

                //UID.put("CheckBoxMenuItem[Enabled].Icon", AUCheckBox.checked);
                /*UID.put("CheckBoxMenuItem[Enabled].checkIconPainter", new MyCheckBoxMenuItemPainter(MyCheckBoxMenuItemPainter.CHECKICON_ENABLED));
                UID.put("CheckBoxMenuItem[MouseOver].checkIconPainter",new MyCheckBoxMenuItemPainter(MyCheckBoxMenuItemPainter.CHECKICON_MOUSEOVER));
                UID.put("CheckBoxMenuItem[Enabled+Selected].checkIconPainter",new MyCheckBoxMenuItemPainter(MyCheckBoxMenuItemPainter.CHECKICON_ENABLED_SELECTED));
                UID.put("CheckBoxMenuItem[MouseOver+Selected].checkIconPainter",new MyCheckBoxMenuItemPainter(MyCheckBoxMenuItemPainter.CHECKICON_SELECTED_MOUSEOVER));*/
                //cbmi.putClientProperty("Nimbus.Overrides", d);
                //cbmi.putClientProperty("Nimbus.Overrides.InheritDefaults", false);

		//glob.progInfo("Nach Super");
                //Self=this;
		sTitel=getTitle();
                //glob.progInfo("Titel="+sTitel);
		g.saveSqlTime("HS1",0,-1,sTitel,iPos<0 ? -1:g.TabBegriffe.getI(iPos,"aic"),0);
		//TabKnoten = new Tabellenspeicher(g,new String[]{"Stamm","Knoten"});
		Gid.setColumnLabelSortMethod(Sort.sortMethod);
                Gid.setBackground(g.ColHS);
                Gid.setAllowMultipleSelections(true);
		Count.add(Count.Hauptschirm);

		TabStyle = new Tabellenspeicher(g,new String[]{"Stammtyp","Style","Style2"});
		TabAktFormular = new Tabellenspeicher(g,new String[]{"Stt","Formular","Tab"});
		//g.addInfo("### Applikation "+rsApplikation+" ###");
		//g.debugInfo("sApplikation="+rsApplikation);
                sApplikation=rsApplikation;
		if(!rsApplikation.equals(g.TabBegriffe.getInhalt("Kennung",iPos)))
		{
			new Message(Message.WARNING_MESSAGE,null,g).showDialog("Applikation",new String[] {rsApplikation});
			dispose();
            return;
		}
                else if(!g.HS(true))
                {
                        new Message(Message.WARNING_MESSAGE,null,g).showDialog("keinHS");
                        dispose();
                        return;
                }
                else
		{
			/*Image Gif= g.getImage("Begriffgruppe","Applikation");
			if (Gif != null)
				setIconImage(Gif);*/
                        //setIconImage(g.getImageIcon("mandant.png").getImage());
                        //Gid.setFont(g.fontBezeichnung);
			NodStamm = new JCOutlinerFolderNode(null,g.getMandant(0,null));
			iBegriff=g.TabBegriffe.getI(iPos,"aic");
                        g.SaveVerlauf(iBegriff,0,0);
                        iRolle=g.TabBegriffe.getI(iPos,"Rolle");
                        if (iRolle>0)
                          iSttRolle=g.RolleToStt(iRolle);//g.TabRollen.posInhalt("AIC",iRolle) ? g.TabRollen.getI("Stt"):0;
			//g.progInfo(rsApplikation+"="+iBegriff);
                        //CboFormular  = new ComboSort(g,ComboSort.kein);
                        /*CbxAnzahl= g.getCheckbox("Haeufigkeit");
                        CbxAnzahl.addActionListener(new ActionListener()
                        {
                          public void actionPerformed(ActionEvent e)
                          {
                            FillCboFormular();
                          }
                        });*/
                        TabSTT2 = new Tabellenspeicher(g,"select s.aic_stammtyp,s.kennung,s.aic_eigenschaft from stammtyp s " + g.join("applikation_zuordnung", "s", "stammtyp") +
                                                 " where applikation_zuordnung.aic_begriff=" + iBegriff + " order by reihenfolge", true);
                        //LadeParameter(false);
                        CboHS=new ComboSort(g);
                        Formular Fom=new Formular(g,this);
			BtnEinstellung=Fom.getButton2("Tb_Einstellung");
                        fillCboHS();
                        CboHS.setMaximumRowCount(15);
                        CboStt=new ComboSort(g,ComboSort.kein);
                        fillCboStt();

                        CboBenutzergruppe=new ComboSort(g);
			CboBenutzergruppe.fillDefTable("BENUTZERGRUPPE",true);
            CboEbene=new ComboSort(g);
            CboProg=new ComboSort(g);
            CboProg.fillBegriffTable(g.TabBegriffgruppen.getAic("Programm"),true,true);
            
            BtnSuche=Fom.getButton2("Tb_Suche");
            BtnSpotlight=Fom.getButton2("Spotlight");
			//BtnExport=g.getButton("Export");
			//BtnImport=g.getButton("Import");
			BtnSpaltenDef=Fom.getButtonM("Spalten");
            BtnAnsicht=g.getButton2("GruppenFilter",null,null,null,"Frame");
			BtnGruppenFilter=g.getFrameS("GruppenFilter");//,null,null,"Frame");
			BtnFilter=Fom.getButtonM("Filter");
			if (g.Def())
				BtnAbfrage=Fom.getButtonM("Abfrage");
			BtnDruckHS=Fom.getButton2("Tb_Drucken","DruckHS",null,null,"Button");
			//BtnDrucken=g.getButton("Druck");
			BtnInit=Fom.getButton2("Tb_Init","Init",null,null,"Button");
			//BtnInit.setMargin(g.inset);
            BtnZRplus=Fom.getButton2("Tb_ZRplus");
            BtnZRminus=Fom.getButton2("Tb_ZRminus");
			BtnZeitraum=Fom.getButton2("Tb_Zeitraum");
			BtnRefresh=Fom.getButton2("Tb_Refresh");
                        BtnRefreshAll=Fom.getButton2("FullRefresh");
                        BtnMandant=Fom.getButton2("Mandant");
                        BtnSystem=Fom.getButton2("System");
                        BtnHS=Fom.getButton2("HS");
			//BtnRefresh.setMargin(g.inset);
			//BtnEinstellung=g.getFrame("einstellg_hs");
			iEnabled=(BtnDruckHS.isEnabled()?1:0)+(BtnGruppenFilter.isEnabled()?2:0)+(g.getButton("Export").isEnabled()?4:0);
			//if(!g.TabBegriffe.out())
			//	EinstellungsEvent();
                        TabForm=new Tabellenspeicher(g,new String[] {"Aic","Nr","Bezeichnung","Anzahl","Memo","last","Reihenfolge"});
			LadeParameter(false);
                        //g.fixtestInfo("iHS="+iHS+", iSttHS="+iSttHS+"/"+g.TabStammtypen.getBezeichnungS(iSttHS));
                        fillCboStt();

			//BtnInfo=g.getButton("Info");
			//BtnSpeichern=g.getButton("Speichern");
			//BtnSchliessen=g.getButton("Schliessen");
			//BtnBeenden=Fom.getButton2("Beenden");
//			BtnHelp=Fom.getButton2("help_hs",null,null,"Web");

                        JToolBar PnlTB=new JToolBar(JToolBar.HORIZONTAL);
                        PnlTB.setOpaque(Static.bND);
                        PnlTB.setFloatable(false);
                        if (Static.bND)
                        {
                        	PnlTB.setPreferredSize(new java.awt.Dimension(/*g.Mal2() ? 84:48*/1,g.Mal2() ? 84:48));
                        	PnlTB.setBackground(g.ColToolbar);
                        	//g.fixtestError("HS-Farbe-Toolbar");
                        }
                        else
                        	PnlTB.setPreferredSize(new java.awt.Dimension(/*g.Mal2() ? 84:48*/1,g.Mal2() ? 80:40));
                        //PnlTB.setRollover(false);
			//JPanel Pnl1 = new JPanel(new GridLayout(1,0));
			//Pnl1.add(BtnGruppenFilter);
			//Pnl1.add(BtnSpaltenDef);
			//Pnl1.add(BtnFilter);

			//Pnl1.add(CboTyp);
			//Pnl1.add(CboFormular);
                        //PnlTB.add(BtnEdit);

                        //if (g.WorkflowZR())
                        {
                          ActionListener AL=new ActionListener()
                          {
                            public void actionPerformed(ActionEvent ev) {
                              String s = ev.getActionCommand();
                              g.progInfo("Action=" + s);
                              if (s.equals("Tag") || s.equals("Woche") || s.equals("Monat"))
                              {
                                g.changeZA(s);
                                bNodStammGeladen = false;
				RefreshGrid();
                              }
                              else if (s.equals("ZRminus2") || s.equals("ZRplus2"))
                              {
                                g.changeZR(s);
                                Zeitraum.PeriodeToVec(g, g.getZA(0));
                                //bNodStammGeladen = false;
                                RefreshGrid();
                              }
                              else if (s.equals("Jetzt"))
                              {
                                g.setAktDate(true);
                                Zeitraum.PeriodeToVec(g,g.getZA(0),true);
                                RefreshGrid();
                              }
                            }
                          };
                          if (g.WorkflowZR())
                          {
                            ButtonGroup RadGroup = new ButtonGroup();
                            BtnTag = g.getTButton("Tag", AL);
                            PnlTB.add(BtnTag);
                            RadGroup.add(BtnTag);
                            BtnWoche = g.getTButton("Woche", AL);
                            PnlTB.add(BtnWoche);
                            RadGroup.add(BtnWoche);
                            BtnMonat = g.getTButton("Monat", AL);
                            PnlTB.add(BtnMonat);
                            RadGroup.add(BtnMonat);
                            String sZeitart=g.getZA(0);
                            if (sZeitart.equals("Tag"))
                              BtnTag.setSelected(true);
                            else if (sZeitart.equals("Woche"))
                              BtnWoche.setSelected(true);
                            else if (sZeitart.equals("Monat"))
                              BtnMonat.setSelected(true);
                          }
                          else
                            PnlTB.add(BtnZeitraum);
                          PnlTB.add(BtnZRminus);
                          PnlTB.add(g.getButton2("Jetzt",null, "Jetzt", AL, "Button"));
                          PnlTB.add(BtnZRplus);
                          if (g.WorkflowZR())
                          {
                            PnlTB.add(g.getButton2("Tb_ZRminus2",null, "ZRminus2", AL, "Button"));
                            PnlTB.add(g.getButton2("Tb_ZRplus2",null, "ZRplus2", AL, "Button"));
                          }
                        }
                        /*else
                        {
                          PnlTB.add(BtnZRminus);
                          PnlTB.add(BtnZeitraum);
                          PnlTB.add(BtnZRplus);
                        }*/
                        PnlTB.addSeparator();
                        PnlTB.add(BtnRefresh);
                        PnlTB.add(BtnRefreshAll);
                        PnlTB.add(BtnSuche);
			PnlTB.addSeparator();
                        PnlTB.add(BtnInit);
                        PnlTB.add(BtnEinstellung);
                        PnlTB.add(BtnAnsicht);
                        PnlTB.addSeparator();
                        PnlTB.add(BtnMandant);
                        PnlTB.addSeparator(new Dimension(3,3));
                        PnlTB.add(BtnSystem);
                        PnlTB.addSeparator(new Dimension(3,3));
                        PnlTB.add(BtnHS);
                        //Pnl1.add(BtnNeu);
                        //PnlTB.addSeparator(new java.awt.Dimension(25,1));


			//PnlTB.addSeparator(new java.awt.Dimension(25,1));
			//Pnl1.add(BtnExport);
			//Pnl1.add(BtnImport);

			//Pnl1.add(BtnRefresh);
			//Pnl1.add(BtnInfo);
			getContentPane().add("North",PnlTB);

                        //BtnRefresh.setPreferredSize(new java.awt.Dimension(40,16));
                        //BtnZRplus.setPreferredSize(new java.awt.Dimension(40,8));
			//JPanel PnlSouth = new JPanel(new GridLayout(1,0,1,1));
			//JPanel Pnl = new JPanel(new GridLayout(1,4,1,1));
                        /*  JPanel PnlWest=new JPanel(new GridLayout(1,2));
                          PnlWest.add(BtnInit);*/
                            //JPanel PnlZR=new JPanel(new GridLayout(2,1));

                        /*  PnlWest.add(PnlZR);
			Pnl.add("West",PnlWest);
			Pnl.add("Center",BtnZeitraum);
                        Pnl.add("East",BtnRefresh);*/
                        //Pnl.add(BtnZeitraum);
                        //PnlSouth.add(Pnl);
			//Pnl = new JPanel(new BorderLayout());
			//Pnl.add("Center",BtnEinstellung);
			//PnlSouth.add(Pnl);
			//PnlSouth.add(BtnDrucken);
                        PnlTB.addSeparator();
                        PnlTB.add(BtnSpotlight);
                        //PnlTB.add(new JPanel());
//                        PnlTB.add(BtnHelp);
                        PnlTB.add(BtnDruckHS);
                        JPanel Pnl =new JPanel();
                        if (Static.bND)
                        	Pnl.setBackground(g.ColToolbar);
                        PnlTB.add(Pnl);
                        //PnlTB.addSeparator();
                        LblM=new JLabel(g.getMandant(0,null));
                        LblM.setFont(g.fontTitel);
                        LblM.setForeground(g.ColEF_Bez2);
                        PnlTB.add(LblM);
			//PnlTB.add(BtnBeenden);
			/*
			JPanel PnlCbx = new JPanel();
			PnlCbx.add(CbxAutoDarstellen);
			PnlCbx.add(CbxNurOrdner);
			PnlCbx.add(CbxStammtypen);
			JPanel Pnl3=new JPanel(new GridLayout(2,1));
			Pnl3.add(PnlZeitpunkt);
			Pnl3.add(PnlCbx);
			Pnl2.add("West",Pnl3);
			if (g.Prog())
				Pnl2.add("Center",g.LblGlobal);
			Pnl3=new JPanel(new GridLayout(2,1));
			Pnl3.add(BtnHelp);
			Pnl3.add(BtnBeenden);
			Pnl2.add("East",Pnl3);*/
			//getContentPane().add("South",PnlSouth);

			//Gid.setBackground(Color.white);

			//LadeSttBilder();
			//g.progInfo("vor RefreshGrid");
                        //g.clockInfo("Toolbar",lClock2);
			/*if (g.bAutoInit)
                          InitParameter();
			else*/
                          RefreshGrid();
                        //lClock2=Static.get_ms();
			//g.progInfo("nach RefreshGrid");
			JPanel PnlC=new JPanel(new BorderLayout());
			PnlRbt=new JPanel(new FlowLayout());
			PnlRbt.setBackground(g.ColHS);
			PnlC.add("North",PnlRbt);
			PnlC.add("Center",Gid);
			getContentPane().add("Center",PnlC);
			Events();
                        //g.clockInfo("Events",lClock2);
                        //lClock2=Static.get_ms();
			//Parameter p=new Parameter(g,sApplikation);
			bFP=g.getFenster(this,iBegriff,false,50,50,600,600,iFF);
			/*if (!g.getFenster(this,iBegriff))
			{
				setSize(600,600);
				setLocation(50,50);
			}*/
			//g.progInfo("vor show");
			if (bStammtypen)
                          ErmittleSttAnzahl();
                        //g.clockInfo("ErmittleSttAnzahl",lClock2);
                        //lClock2=Static.get_ms();
			addPopup();
                        //g.clockInfo("addPopup",lClock2);
			setVisible(true);
                        FillCboFormular2();
			//g.debugInfo("nach show");
			//g.printInfo("-gc:"+Runtime.getRuntime().freeMemory()+"/"+Runtime.getRuntime().totalMemory());
			System.gc();
			//g.printInfo("+gc:"+Runtime.getRuntime().freeMemory()+"/"+Runtime.getRuntime().totalMemory());
			//g.printInfo(getTitle()+":"+(Static.get_ms()-lClock));
			//dtZeitpunkt.requestFocus();
			//Gid.transferFocus();
			//EnableButtons();
			if (NodStammMom !=null)
			{
				Static.makeVisible(Gid,NodStammMom);
				//g.progInfo("!!! makeVisible");
			}
		}
                g.saveSqlTime("HS2",0,Static.get_ms()-lClock,sTitel,iBegriff,0);
                g.clockInfo(sTitel,lClock);
	}//Hauptschirm

        private void fillCboStt()
        {
          if (iFillStt==iSttHS)
            return;
          g.fixtestInfo("fillCboStt "+iSttHS+":"+(iSttHS==0 ?"<kein>":g.TabStammtypen.getBezeichnungS(iSttHS)));
          iFillStt=iSttHS;
          CboStt.Clear(true);
          Vector<Integer> VecPos=new Vector<Integer>();
          //g.fixtestInfo("iHS="+iHS+", iSttHS="+iSttHS);
          if (iSttHS>0)
          {
            int iVon = iSttHS;
            while (iVon != 0)
            {
              int iPosS = g.TabStammtypen.getPos("Aic", iVon);
              //g.fixtestInfo("iPosS="+iPosS+", Aic="+g.TabStammtypen.getI(iPosS,"Aic")+"/"+g.TabStammtypen.getS(iPosS,"Bezeichnung"));
              //if (!VecPos.contains(iPosS))
              //{
                //VecPos.addElement(iPosS);
                if (g.existsSttS(iPosS))
                  CboStt.addItem(g.TabStammtypen.getS(iPosS,"Bezeichnung"),g.TabStammtypen.getI(iPosS,"Aic"),g.TabStammtypen.getS(iPosS,"Kennung"));
              //}
              iVon = g.TabStammtypen.getI(iPosS, "Darunter");
            }
          }
          else
            for(TabSTT2.moveFirst(); !TabSTT2.eof(); TabSTT2.moveNext()) {
              int iVon = TabSTT2.getI("aic_stammtyp");
              //g.fixtestInfo("iStt="+iVon+"/"+g.TabStammtypen.getBezeichnungS(iVon));
              while(iVon!=0) {
                int iPosS = g.TabStammtypen.getPos("Aic",iVon);
                if(!VecPos.contains(iPosS)) {
                  VecPos.addElement(iPosS);
                  if(g.existsSttS(iPosS))
                    CboStt.addItem(g.TabStammtypen.getS(iPosS,"Bezeichnung"),g.TabStammtypen.getI(iPosS,"Aic"),g.TabStammtypen.getS(iPosS,"Kennung"));
                }
                iVon = TabSTT2.getI("aic_eigenschaft")==0?0:g.TabStammtypen.getI(iPosS,"Darunter");
              }
            }
          CboStt.setKein(true);
          CboStt.setSelectedAIC(iSttHS);
        }

        private JCheckBoxMenuItem addCbxItem(String sCbx,boolean b,Object pop)
        {
          int iPos= g.getPosBegriff("Checkbox", sCbx);
          JCheckBoxMenuItem Mnu = new JCheckBoxMenuItem(iPos<0?sCbx:g.getBegBez3(iPos), b);
          //Mnu.setUI(UID);
          Mnu.putClientProperty("Nimbus.Overrides", UID);
          Mnu.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
          g.setMI(Mnu,iPos,false);
          //Mnu.setEnabled(g.PME(iPos));//g.bEnable && iPos>=0 && g.BerechtigungS(iPos));
          //Mnu.setFont(g.fontPopup);
          //Mnu.putClientProperty(JCheckBoxMenuItem.SELECTED_ICON_CHANGED_PROPERTY, Boolean.TRUE);

          //Mnu.setSelectedIcon(AUCheckBox.checked);
          //Mnu.setIcon(AUCheckBox.unchecked);
          //if (iPos>=0)
          //  g.setTooltip(g.TabBegriffe.getS(iPos,"Tooltip"),Mnu);
          if (pop instanceof JPopupMenu)
            ((JPopupMenu)pop).add(Mnu);
          else if (pop instanceof JMenu)
            ((JMenu)pop).add(Mnu);
          return Mnu;
        }

        private JRadioButtonMenuItem addRadItem(String sRad,boolean b,ButtonGroup RadGroup,Object pop)
        {
          int iPos= g.getPosBegriff("Radiobutton", sRad);
          JRadioButtonMenuItem Mnu = new JRadioButtonMenuItem(iPos<0?sRad:g.getBegBez3(iPos), b);
          Mnu.putClientProperty("Nimbus.Overrides", UID2);
          Mnu.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
          g.setMI(Mnu,iPos,false);
          //Mnu.setEnabled(g.PME(iPos));//g.bEnable && iPos>=0 && g.BerechtigungS(iPos));
          //Mnu.setFont(g.fontPopup);
          //if (iPos>=0)
          //  g.setTooltip(g.TabBegriffe.getS(iPos,"Tooltip"),Mnu);
          RadGroup.add(Mnu);
          if (pop instanceof JPopupMenu)
            ((JPopupMenu)pop).add(Mnu);
          else if (pop instanceof JMenu)
            ((JMenu)pop).add(Mnu);
          return Mnu;
        }


	private void addPopup()
	{
		//g.progInfo("addPopup");
		final JPopupMenu popup= new JPopupMenu();
                popup.setLabel("Hauptschirm");
		/*MnuBearbeiten = addMenuItem("Edit",popup);
		MnuBearbeiten.addMouseListener(new MouseListener()
		{
			public void mousePressed(MouseEvent ev)
			{
				P=((Component)ev.getSource()).getLocationOnScreen();
				P.translate(ev.getX(),ev.getY());
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
                          if (MnuBearbeiten.isEnabled())
                            EditAny(P);
			}
		});*/

             MnuLastForm.setFont(g.fontPopup);
             popup.add(MnuLastForm);
             MnuLastForm.addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    if (iForm>0)
                      DetailAufruf(true, iForm,0);
                  }
                });
             MnuBearbeiten = g.addMenu("Edit",popup);
             //MnuBearbeiten.setFont(g.fontPopup);
             //popup.add(MnuBearbeiten);
             MnuEdit2 = g.addMenu("Edit2",popup);
             //MnuEdit2.setFont(g.fontPopup);
             //popup.add(MnuEdit2);
             MnuEdit2.setVisible(false);
             MnuEdit3 = g.addMenu("Edit3",popup);
             //MnuEdit3.setFont(g.fontPopup);
             //popup.add(MnuEdit3);
             MnuEdit3.setVisible(false);
             g.addMenuItem("Formularliste", popup).addActionListener(new ActionListener()
             {
               public void actionPerformed(ActionEvent ev)
               {
                 Formularliste();
               }
             });
             MnuClose = g.addMenuItem("Close", popup);
             MnuClose.addActionListener(new ActionListener()
             {
               public void actionPerformed(ActionEvent ev)
               {
                 popup.setVisible(false);
                 MnuClose.setVisible(false);
               }
             });
             MnuClose.setVisible(false);

                /*addCbxItem("NurOrdner",bNurOrdner,popup).addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    bNurOrdner = ((JCheckBoxMenuItem)ev.getSource()).isSelected();
                    FillCboFormular2();
                  }
                });*/

                MnuEMail = g.addMenuItem("E-Mail",popup);
                MnuGPS = g.addMenuItem("GPS",popup);
                MnuBild = g.addMenuItem("Bild",popup);
                popup.addSeparator();
                //MnuAuswahl = g.addMenu("Hauptschirm2",null);
                //MnuAuswahl.setFont(g.fontPopup);
                //popup.add(MnuAuswahl);
                /*if (BtnEinstellung.isEnabled())
                  g.addMenuItem("Einstellung", popup).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                      FilterAny(null);
                    }
                  });*/
                //FillEinstellung();
                //popup.addSeparator();
                JMenuItem MnuRefresh = g.addMenuItem("Refresh",popup);
                MnuRefresh.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    bNodStammGeladen = false;
                    RefreshGrid();
                  }
                });
                /*JMenuItem MnuFullRefresh = g.addMenuItem("FullRefresh",popup);
                MnuFullRefresh.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    bNodStammGeladen = false;
                    ShowAbfrage.refreshBerechtigung(g);
                    RefreshGrid();
                  }
                });*/
                JMenuItem MnuSuchen = g.addMenuItem("Suche",popup);
                popup.addSeparator();
                JMenuItem MnuOeffnen = g.addMenuItem("open down",popup);

                if (iRolle>0 && g.RolleToStt(iRolle)!=g.iSttANR)
                {
                  Vector VecRolle = g.getVecRolle3(iRolle);
                  int iAnzRollen=0;
                  for (int i=0;i<VecRolle.size();i++)
                  {
                    int iPos=g.TabRollen.getPos("Aic", VecRolle.elementAt(i));
                    if (iPos>=0 && g.TabRollen.getI(iPos,"Anzahl") != 0)
                      iAnzRollen++;
                  }
                  if (iAnzRollen>1)
                  {
                    popup.addSeparator();
                    //JMenu MnuSub = new JMenu(g.getBegriff("Show", "Rolle"));
                    //popup.add(MnuSub);
                    ButtonGroup RadGroupRolle = new ButtonGroup();
                    TabRPop=new Tabellenspeicher(g,new String[] {"Aic","Popup"});
                    for (int i=0; i<VecRolle.size(); i++)
                      addMnuRolle(VecRolle.elementAt(i),RadGroupRolle,popup);
                  }
                }
                /*MnuFilter = new JMenuItem(g.getBegriff("Button","Einstellung"));
                //MnuFilter.setMnemonic('f');
                popup.add(MnuFilter);
                MnuFilter.addMouseListener(new MouseListener()
                {
                        public void mousePressed(MouseEvent ev)
                        {
                                P=((Component)ev.getSource()).getLocationOnScreen();
                                P.translate(ev.getX(),ev.getY());
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
                          if (MnuFilter.isEnabled())
                            FilterAny(P);
                        }

                });
		MnuDruck = new JMenuItem(g.getBegriff("Button","Druck"));
		MnuDruck.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				All_Unlimited.Print.DruckFrage.start(Gid,0,iStamm_Mom,g,0,iBegriff).show();
			}
		});*/

		MnuSuchen.addMouseListener(new MouseListener()
		{
			public void mousePressed(MouseEvent ev)
			{
				P=((Component)ev.getSource()).getLocationOnScreen();
				P.translate(ev.getX(),ev.getY());
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
                //JCOutlinerNode Nod = Gid.getSelectedNode();
                //if(Nod != null && Nod.getLabel() instanceof Vector) {
                //  final String s = EingabeFormular.getEMail((Vector)Nod.getLabel());
                //  if(s != null)
                //  {
                    MnuEMail.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                        JCOutlinerNode Nod = Gid.getSelectedNode();
                        if(Nod != null && Nod.getLabel() instanceof Vector)
                        {
                          String s = EingabeFormular.getEMail((Vector)Nod.getLabel());
                          //g.progInfo("E-mail=" + s);
                          Static.OpenURL("mailto:" + s);
                        }
                      }
                    });
                    MnuGPS.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                          JCOutlinerNode Nod = Gid.getSelectedNode();
                          if(Nod != null && Nod.getLabel() instanceof Vector)
                          {
                            String s = EingabeFormular.getGPS((Vector)Nod.getLabel());
                            //g.progInfo("E-mail=" + s);
                            if (s != null)
                            	Static.OpenURL(s);
                          }
                        }
                      });
                    MnuBild.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                        JCOutlinerNode Nod = Gid.getSelectedNode();
                        if(Nod != null && Nod.getLabel() instanceof Vector)
                        {
                          String s = EingabeFormular.getBild((Vector)Nod.getLabel());
                          //g.progInfo("Bild="+s);
                          if (s != null)
                            Static.OpenURL(s.indexOf(':')==4 ? s:"file://"+s,Sort.gets(Nod.getLabel()));
                          //EingabeFormular.showBild
                        }
                      }
                    });
                 // }
                //}

                MnuOeffnen.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                EingabeFormular.OeffneAlle(Gid,null,0);//(JCOutlinerFolderNode)Gid.getSelectedNodes(),0);
                        }
                });

                /*
		JMenuItem MnuWeitersuchen = new JMenuItem(g.getBegriff("Show","weitersuchen"));
		popup.add(MnuWeitersuchen);
		MnuWeitersuchen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				bAb=false;
				suche((JCOutlinerFolderNode)Gid.getRootNode(),sSuchtext,iSpalte-1);
			}
		});*/
                //popup.add(MnuDruck);
                int iPosDetails=g.getPosBegriff("Titel", "Details");
                /*JMenu MnuDetails = new JMenu(iPosDetails<0?"Details":g.getBegBez2(iPosDetails));
                MnuDetails.setFont(g.fontPopup);
                if (iPosDetails>=0)
                  MnuDetails.setIcon(g.LoadImageIcon(iPosDetails));
                popup.add(MnuDetails); */
                JMenu MnuDetails = g.addMenu(iPosDetails,"Details",popup);
                MnuStt = addCbxItem("Stammtypen",bStammtypen,MnuDetails);
                /*if (g.Def())
                {
                  JMenuItem MnuGetForm = addMenuItem("Formularsuche", MnuDetails);
                  MnuGetForm.addMouseListener(new MouseListener()
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
                      FormSuchen(P);
                    }

                  });
                }*/
                if(g.Def())//g.Geloeschte())
                {
                      addCbxItem("tab_sdel", bDel, MnuDetails).addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                          bDel = ((JCheckBoxMenuItem)e.getSource()).isSelected();
                          //RefreshGrid();
                        }
                      });
                }
                addCbxItem("Austritt", bAustritt, MnuDetails).addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    bAustritt = ((JCheckBoxMenuItem)e.getSource()).isSelected();
                    //RefreshGrid();
                  }
                });
                addCbxItem("Tooltip", bTooltip, MnuDetails).addActionListener(new ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    bTooltip = ((JCheckBoxMenuItem)e.getSource()).isSelected();
                                  }
                });
                

                MnuOffen = addCbxItem("offen",bOffen,MnuDetails);
                MnuDetails.addSeparator();
                addCbxItem("Beginnsuche", bBeginn, MnuDetails).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      bBeginn = ((JCheckBoxMenuItem)e.getSource()).isSelected();
                    }
                  });
                  addCbxItem("Spalte1", bSpalte1, MnuDetails).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      bSpalte1 = ((JCheckBoxMenuItem)e.getSource()).isSelected();
                    }
                  });                
                MnuDetails.addSeparator();
                ActionListener AL=new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          if (ev.getSource()==MnuAlles)
                            iVL=ALLES;
                          else if (ev.getSource()==MnuKeineLeere)
                            iVL=VOLLE;
                          else if (ev.getSource()==MnuLeere)
                            iVL=LEERE;
                          else if (ev.getSource()==MnuBeide)
                            iVL=BEIDE;
                        }
                };
                ButtonGroup RadGroup = new ButtonGroup();
                MnuAlles = addRadItem("alles",iVL==ALLES,RadGroup,MnuDetails);
                MnuAlles.addActionListener(AL);
                MnuKeineLeere = addRadItem("keine leere",iVL==VOLLE,RadGroup,MnuDetails);
                MnuKeineLeere.addActionListener(AL);
                if (g.Def())
                {
                  MnuBeide = addRadItem("beide", iVL == BEIDE, RadGroup, MnuDetails);
                  MnuBeide.addActionListener(AL);
                  MnuLeere = addRadItem("leere",iVL==LEERE,RadGroup,MnuDetails);
                  MnuLeere.addActionListener(AL);                
                }
                MnuOffen.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                if(((JCheckBoxMenuItem)ev.getSource()).getState() != bOffen)
                                {
                                        bOffen=((JCheckBoxMenuItem)ev.getSource()).getState();
                                        //g.progInfo("MnuOffen.addActionListener");
                                        //RefreshGrid();
                                }
                        }
                });
//                if(g.Def())
//                {
//                	MnuDetails.addSeparator();
//                    addCbxItem("JavaFX", Static.bJavaFX, MnuDetails).addActionListener(new ActionListener() {
//                      public void actionPerformed(ActionEvent e) {
//                      	Static.bJavaFX = ((JCheckBoxMenuItem)e.getSource()).isSelected();
//                      }
//                    });
//                    addCbxItem("show Style", Static.bShowStyle, MnuDetails).addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent e) {
//                        	Static.bShowStyle = ((JCheckBoxMenuItem)e.getSource()).isSelected();
//                        }
//                      });
//                    addCbxItem("Alert", Static.bAlert, MnuDetails).addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent e) {
//                        	Static.bAlert = ((JCheckBoxMenuItem)e.getSource()).isSelected();
//                        }
//                      });
//                }
                /*
                MnuSort = addCbxItem("sortiert",bSort,MnuDetails);
                MnuSort.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                if(((JCheckBoxMenuItem)ev.getSource()).getState() != bSort)
                                {
                                        bSort=((JCheckBoxMenuItem)ev.getSource()).getState();
                                        //g.progInfo("MnuSort.addActionListener");
                                        //RefreshGrid();
                                        if (bSort)
                                          Gid.sortByColumn(0,Sort.sortMethod);
                                }
                        }
                });*/

                /*if (g.SuperUser())
                {
                  JCheckBoxMenuItem MnuMandant = new JCheckBoxMenuItem(g.getBegriff("Checkbox", "Mandant"), bMandant);
                  popup.add(MnuMandant);
                  MnuMandant.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      if (((JCheckBoxMenuItem)ev.getSource()).getState() != bOffen)
                      {
                        bMandant = ((JCheckBoxMenuItem)ev.getSource()).getState();
                      }
                    }
                  });
                }*/

		/*MnuDetails.addSeparator();
                MnuAnzahl = addCbxItem("Haeufigkeit",false,MnuDetails);
                MnuAnzahl.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    FillCboFormular2();
                  }
                });*/
                MnuExport = g.addMenuItem("Export",popup);
                iEnabled=iEnabled&3+(MnuExport.isEnabled()?4:0);
                //bEE=MnuExport.isEnabled();
		MnuExport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                          Vector VecS=iSTT_Mom>0 ? g.getSyncVec(iSTT_Mom):Static.AicToVec(iStamm_Mom);
                          new Export(g,self,iSTT_Mom,VecS,0);
			}
		});
                MnuStt.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                if(((JCheckBoxMenuItem)ev.getSource()).getState() != bStammtypen)
                                {
                                        bStammtypen=((JCheckBoxMenuItem)ev.getSource()).getState();
                                        RefreshGrid();
                                }
                        }
                });
                /*MnuAuto = addCbxItem("Auto",bAutoDarstellen,MnuDetails);
                MnuAuto.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                if(((JCheckBoxMenuItem)ev.getSource()).getState() != bAutoDarstellen)
                                {
                                        bAutoDarstellen=((JCheckBoxMenuItem)ev.getSource()).getState();
                                }
                        }
                });*/

		/*JMenuItem MnuBeenden = new JMenuItem("Beenden");
		popup.add(MnuBeenden);
		MnuBeenden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Beenden();
			}
		});*/

                /*BtnEdit.addActionListener(new ActionListener()
                {
                      public void actionPerformed(ActionEvent ev)
                      {
                        if (TabForm.size()==1)
                          DetailAufruf(true, Sort.geti(TabForm.getI(0,"Aic")));
                        else
                        {
                          //Formularliste();
                          MnuClose.setVisible(true);
                          //Point P=BtnEdit.getLocationOnScreen();
                          //popup.show(null,P.x,P.y);
                          //popup.setLocation(BtnEdit.getLocationOnScreen());
                          //popup.setSelected(MnuBearbeiten);
                          //popup.setVisible(true);
                          //popup.setComponent(BtnEdit.getComponent(0));
                          popup.show(BtnEdit, 0, 0);
                          //popup.setSelected(MnuBearbeiten);
                          //MnuBearbeiten.requestFocus();
                          //popup.show((Component)ev.getSource(),BtnEdit.getLocationOnScreen().getX(),BtnEdit.getLocationOnScreen().getY());
                          //EditAny(BtnEdit.getLocationOnScreen());
                        }
                      }
                });*/


		//Gid.add(popup);
		Gid.getOutliner().addMouseListener(new MouseListener()
		{
			public void mousePressed(MouseEvent ev) {}
			public void mouseClicked(MouseEvent ev)
			{
                            //if(ev.getModifiers()==MouseEvent.BUTTON3_MASK)
                            //g.progInfo("HS.mouseClicked1:"+ev.getButton()+"/"+ev.getModifiersEx());
                            if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                            {
                              //g.progInfo("HS.mouseClicked:"+ev.getButton()+"/"+ev.getX()+"/"+ev.getY()+" | "+MouseEvent.CTRL_DOWN_MASK);
                              popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
                            }
			}
			public void mouseEntered(MouseEvent ev) {}
			public void mouseExited(MouseEvent ev)  {}
			public void mouseReleased(MouseEvent ev){}
		});
		Gid.getOutliner().addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{}
			public void keyReleased(KeyEvent e)
			{
			  if (e.getKeyCode()==Global.iPopkey)
			  {
			    MnuClose.setVisible(true);
			    popup.show((Component)e.getSource(),0,0);
			  }
			}
			public void keyTyped(KeyEvent e)
			{}
		});
                popup.addSeparator();
                if (g.Def())
                 g.addMenuItem("eigene Abfragen",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      JButton Btn=g.getButton("Loeschen");
                      final Tabellenspeicher Tab = g.getUserAbfragen();
                      if(Tabellenspeicher.showGrid(Tab, g.getBegriffS("Button", "eigene Abfragen"), Btn,self))
                        Btn.addActionListener(new ActionListener() {
                          public void actionPerformed(ActionEvent ev) {
                            JCOutlinerNode Nod = Tab.Grid.getSelectedNode();
                            int i = Nod.getLevel() > 0 ? Sort.geti(Nod.getLabel(), 0) : 0;
                            if(i > 0) {
                              g.DeleteUserAbfrage(i);
                              Tab.Grid.selectNode(Tab.Grid.getRootNode(), null);
                              Nod.getParent().removeChild(Nod);
                              //Tab.Grid.repaint();
                              Tab.Grid.folderChanged(Tab.Grid.getRootNode());
                            }
                          }
                        });
                    }
                  });

                if (g.Prog())
                {
                  g.addMenuItem("Zeile",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      Vector Vec=(Vector)Gid.getSelectedNode().getLabel();
                      for(int i=0;i<Vec.size();i++)
                        g.progInfo(i+":"+Static.print(Vec.elementAt(i)));
                    }
                  });
                }
                g.addMenuItem("Konsole",popup).addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    JavaKonsole.get(true);
                  }
                });              
                if (g.Def())
                {
                  JMenu MnuTab = new JMenu("Tabellenspeicher");
                  popup.add(MnuTab);
                  addPopupForTab(MnuTab,"Spalten");
                  addPopupForTab(MnuTab,"STT2");
                  addPopupForTab(MnuTab,"GruppenFilter");
                  addPopupForTab(MnuTab,"Ansicht");
                  addPopupForTab(MnuTab,"HS");
                  addPopupForTab(MnuTab,"Knoten");
                  addPopupForTab(MnuTab,"Gruppierung");
                  addPopupForTab(MnuTab,"Gruppen");
                  addPopupForTab(MnuTab,"AktFormular");
                  addPopupForTab(MnuTab,"Formulare");
                  MnuTab.addSeparator();
                  addPopupForTab(MnuTab,"Fts");
                  addPopupForTab(MnuTab,"Ft");
                  addPopupForTab(MnuTab,"BSt");
                  addPopupForTab(MnuTab,"H");
                  MnuTab.addSeparator();
                  addPopupForTab(MnuTab,"TabMonitor");
                  addPopupForTab(MnuTab,"TabVB");
                  addPopupForTab(MnuTab,"Austritt");
                  addPopupForTab(MnuTab,"alle Threads");
                  //addPopupForTab(MnuTab,"aktive Threads");
                  g.addMenuItem("killThreads",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      //Tabellenspeicher TabAT=g.aktiveThreads(false);
                      //if (TabAT.size()>0)
                        if (g.ModellThread())
                          new Message(Message.ERROR_MESSAGE, self,g).showDialog("ModellThread");
                        else if (g.SonstThread())
                          g.killThreads();
                    }
                  });
                  g.addMenuItem("getAll",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      Vector<JCOutlinerNode> Vec=new Vector<JCOutlinerNode>();
                      Static.getAll(Gid.getRootNode(),Vec);
                      for (int i=0;i<Vec.size();i++)
                        g.fixInfo(i+".:"+Vec.elementAt(i));
                    }
                  });
                  addPopupForTab(MnuTab,"TabMandanten");
                  addPopupForTab(MnuTab,"TabStamm");
                  //addPopupForTab(MnuTab,"TabBSt");
                  addPopupForTab(MnuTab,"TabMass");
                  addPopupForTab(MnuTab,"Waehrung");
                  addPopupForTab(MnuTab,"Begriffe");
                  addPopupForTab(MnuTab,"Codes");
                  addPopupForTab(MnuTab,"Begriffgruppen");
                  addPopupForTab(MnuTab,"Rollen");
                  addPopupForTab(MnuTab,"Stammtypen");
                  addPopupForTab(MnuTab,"Bewegungstypen");
                  addPopupForTab(MnuTab,"Individuell");
                  //addPopupForTab(MnuTab,"Abfragen");
                  addPopupForTab(MnuTab,"Dialog");
                  addPopupForTab(MnuTab,"Ampel");
                  addPopupForTab(MnuTab,"Land");
                  addPopupForTab(MnuTab,"Meine");
                  addPopupForTab(MnuTab,"MA");
                  addPopupForTab(MnuTab,"Auswahl");
                  addPopupForTab(MnuTab,"StammBild");
                  addPopupForTab(MnuTab,"Fensterpos");
                  addPopupForTab(MnuTab,"TabFormulare");
                  addPopupForTab(MnuTab,"SplitPos");
                  //addPopupForTab(MnuTab,"ModulLizenz");
                  addPopupForTab(MnuTab,"ModulBerechtigt");
                  //addPopupForTab(MnuTab,"pers. Abfragen");
                  addPopupForTab(MnuTab,"pers. Filter");
                  addPopupForTab(MnuTab,"pers. Abfragen");
                  //addPopupForTab(MnuTab,"pers. Joker");
                  addPopupForTab(MnuTab,"pers. GF");
                  addPopupForTab(MnuTab,"pers. Pos");
                  addPopupForTab(MnuTab,"alle Abfragen");
                  addPopupForTab(MnuTab,"alle Modelle");
                  MnuTab.addSeparator();
                  addPopupForTab(MnuTab,"TabBA");
                  addPopupForTab(MnuTab,"Spalten2");
                  addPopupForTab(MnuTab,"Spalten2-BG");
                  addPopupForTab(MnuTab,"Spalten2-Jeder");
                  //addPopupForTab(MnuTab,"Spalten2d");
                  addPopupForTab(MnuTab,"SpalteZ2");
                  addPopupForTab(MnuTab,"SpalteZ2-BG");
                  addPopupForTab(MnuTab,"SpalteZ2-Jeder");
                  addPopupForTab(MnuTab,"Favoriten");
                  MnuTab.addSeparator();
                  if (g.MySQL() || g.MS())
                    addPopupForTab(MnuTab,"Prozesse");
                  g.addMenuItem("Druck-Reinigung",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      g.fixInfo("Druck-Reinigung:"+Reinigung.DelDrucke(g,false));
                    }
                  });
                  g.addMenuItem("freeAll",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                    	freeAllSwing(g);
                    }
                  });
//                  g.addMenuItem("freeFX",popup).addActionListener(new ActionListener()
//                  {
//                      public void actionPerformed(ActionEvent ev)
//                      {
//                    	  freeFX(g);
//                      }
//                    });
                  g.addMenuItem("ISQL",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      ISQL.get(g,self,false);
                    }
                  });
                  g.addMenuItem("SQL_log",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"datetime","nr","art","len","clock","count","AIC","von","bis","mem","comment"});
                      String sFile=/*"file:"+File.separator+File.separator+*/g.ErrorFile() + "_sql.log";
                      //g.fixInfo("Log-Datei="+sFile);
                      Tab.readFile('\t',sFile,true);
                      Tab.showGrid("SQL-Log",self);
                    }
                  });
//                  g.addMenuItem("Memo-Format-Test",popup).addActionListener(new ActionListener()
//                  {
//                    public void actionPerformed(ActionEvent ev)
//                    {
//                      JFrame Fom=new JFrame("Memo formatiert");
//                      Fom.getContentPane().add("Center",new AUTextPane(g));
//                      Fom.setSize(400,400);
//                      Fom.setVisible(true);
//                    }
//                  });

                  /*addMenuItem("pers. Einstellung",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      PersEinstellungen.get(g).show(self);
                    }
                  });*/
                  /*addMenuItem("DefFormular",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      DefFormular.get(g,0);
                    }
                  });
                  addMenuItem("Definition",popup).addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      new Definition(g,"");
                    }
                  });*/
                  ActionListener ALP=new ActionListener()
                  {
                	  public void actionPerformed(ActionEvent ev)
                      {
                		  String s = ev.getActionCommand();
      	            	  if (s.equals("Tsearch"))
      	            		Tsearch.get(g,null);
      	            	  else if (s.equals("Individuell"))
      	            		Individuell.get(g,null); 
      	            	  else if (s.equals("DruckO"))
      	            		Gid.print();
      	            	  else if (s.equals("Druck"))
      	            		All_Unlimited.Print.DruckHS.printOutliner(g,"Hauptschirm",false,Gid);
      	            	  else if (s.equals("DruckQ"))
      	            		All_Unlimited.Print.DruckHS.printOutliner(g,"Hauptschirm",true,Gid);
                      }
                  };                
                  g.addMenuItem("translate-search",popup,"Tsearch",ALP,"Frame");
                  g.addMenuItem("Individuell",popup,"Individuell",ALP,"Frame");
                  g.addMenuItem("Druck Original",popup,"DruckO",ALP,"Button");
                  g.addMenuItem("Druck",popup,"Druck",ALP,"Button");
                  g.addMenuItem("Druck Quer",popup,"DruckQ",ALP,"Button");

                }
		EnableButtons();
	}

        private void Formularliste()
        {
          final AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
          String [] s = !Static.bVerlauf ? new String[]{g.getBegriffS("Show","Formular"),g.getBegriffS("Show","Aufrufe"),g.getBegriffS("Show","Memo"),g.getBegriffS("Show","Nr")}:
              new String[]{g.getBegriffS("Show","Formular"),g.getBegriffS("Show","Aufrufe"),g.getBegriffS("Show","zuletzt"),g.getBegriffS("Show","Memo"),g.getBegriffS("Show","Nr")};
          Out.setColumnButtons(s);
          Out.setNumColumns(s.length);
          Out.setRootVisible(false);
          Out.setBackground(g.ColHS);
          JCOutlinerNodeStyle Sty=g.getSttStyle(iSTT_Mom);
          //JCOutlinerNodeStyle Sty1=new JCOutlinerNodeStyle(Sty);
          //Sty1.setForeground(g.Col1);
          //JCOutlinerNodeStyle Sty2=new JCOutlinerNodeStyle(Sty);
          //Sty2.setForeground(g.Col2);
          //Sty=Sty1;
          TabForm.moveFirst();
          //int iR=TabForm.getI("Reihenfolge");
          JCOutlinerFolderNode NodeL=null;
          JCOutlinerFolderNode Node=null;
          for(;!TabForm.out();TabForm.moveNext())
          {
            Vector<Object> VecSpalten=new Vector<Object>();
            VecSpalten.addElement(TabForm.getS("Bezeichnung"));
            VecSpalten.addElement(TabForm.getInhalt("Anzahl"));
            if (Static.bVerlauf)
              VecSpalten.addElement(TabForm.isNull("last")?null:new Zeit(/*TabForm.isNull("last")?new Date(0):*/TabForm.getTimestamp("last"),"dd.MM.yyyy HH:mm"));
            VecSpalten.addElement(TabForm.getS("Memo"));
            VecSpalten.addElement(TabForm.getInt("Nr"));
            Node = new JCOutlinerFolderNode((Object)VecSpalten, (JCOutlinerFolderNode)Out.getRootNode());
            Node.setStyle(Sty);
            Node.setUserData(TabForm.getInhalt("Aic"));
            if (iForm==TabForm.getI("Aic"))
              NodeL = Node;
            /*if (TabForm.getI("Reihenfolge")>iR+1)
              if (Sty==Sty1)
                Sty=Sty2;
              else
                Sty=Sty1;*/
            //iR=TabForm.getI("Reihenfolge");
          }
          Out.sortByColumn(Static.bVerlauf ? 2:1,Sort.sortMethod,JCqsort.DESCENDING);
          Out.setColumnAlignment(1,BWTEnum.MIDDLERIGHT);
          Out.setColumnAlignment(s.length-1,BWTEnum.MIDDLERIGHT);
          Out.setColumnWidth(s.length-2,250);
          DlgFormularliste=new JDialog(this,g.getBegriffS("Dialog","Formularliste"),false);
          DlgFormularliste.getContentPane().add("Center",Out);
          //Dimension D=new Dimension(120,24);
          JButton BtnOk = g.getButton("Ok");
          //BtnOk.setPreferredSize(Static.D);
          JButton BtnAbbruch = g.getButton("Abbruch");
          //BtnAbbruch.setPreferredSize(Static.D);
          DlgFormularliste.getRootPane().setDefaultButton(BtnOk);
          JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
          Pnl.add(BtnOk);
          Pnl.add(BtnAbbruch);
          DlgFormularliste.getContentPane().add("South",Pnl);
          BtnOk.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              int i=Sort.geti(Out.getSelectedNode().getUserData());
              DlgFormularliste.dispose();
              DetailAufruf(true, i,0);
            }
          });
          Action cancelKeyAction = new AbstractAction()
          {
            private static final long serialVersionUID = 2477022925733839631L;

            public void actionPerformed(ActionEvent e)
            {
              DlgFormularliste.dispose();
            }
          };
          Static.Escape(BtnAbbruch,DlgFormularliste,cancelKeyAction);
          BtnAbbruch.addActionListener(cancelKeyAction);

          DlgFormularliste.pack();
          //g.progInfo("iForm="+iForm+","+NodeL);
          if (DlgFormularliste.getHeight()>550)
            DlgFormularliste.setSize(DlgFormularliste.getWidth(),550);
          if (NodeL != null)
            Static.makeVisible(Out,NodeL);
          Static.centerComponent(DlgFormularliste,self);
          Out.addItemListener(new JCOutlinerListener ()
          {
              public void outlinerNodeSelectEnd(JCOutlinerEvent ev) {}
              public void outlinerNodeSelectBegin(JCOutlinerEvent ev) {}
              public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev) {}
              public void itemStateChanged(JCItemEvent ev){}
              public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
              {
                int i=Sort.geti(Out.getSelectedNode().getUserData());
                DlgFormularliste.dispose();
                DetailAufruf(true, i,0);
              }
          });
          DlgFormularliste.setVisible(true);
        }

        private void addMnuRolle(Object Obj,ButtonGroup RadGroup,JPopupMenu pop)
        {
          int iPos=g.TabRollen.getPos("Aic",Obj);
          JRadioButtonMenuItem Mnu = new JRadioButtonMenuItem(iPos>=0 ? g.TabRollen.getS(iPos,"Bezeichnung"):"Rolle "+Obj,Tabellenspeicher.geti(Obj)==iRolle);
          if (g.TabRollen.getI(iPos,"Anzahl") != 0)
          {
            Mnu.setFont(g.fontPopup);
            TabRPop.addInhalt("Aic", Obj);
            TabRPop.addInhalt("popup", Mnu);
            RadGroup.add(Mnu);
            pop.add(Mnu);
            Mnu.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                if(TabRPop.posInhalt("popup", ev.getSource())) {
                  iRolle = TabRPop.getI("Aic");
                  iSTT_Last = 0;
                  RefreshGrid();
                }
              }
            });
          }
        }
        
        private Tabellenspeicher getTabGF()
        {
        	Tabellenspeicher Tab=new Tabellenspeicher(g,new String[]{"Stt1","Stt2","Rolle","Bew","Eigenschaft","Filter","Art"});
        	for (int i=0;i<TabGruppenFilter.size();i++)
        	{
        		Tab.newLine();
        		Tab.setInhalt("Stt1",g.TabStammtypen.getBezeichnungS(TabGruppenFilter.getI(i,"int1")));
        		int iStt2=TabGruppenFilter.getI(i,"int2");
        		int iArt=TabGruppenFilter.getI(i,"int4");
        		if (iStt2>0)
        			Tab.setInhalt("Stt2",g.TabStammtypen.getBezeichnungS(iStt2));
        		else if ((iArt==4) || (iArt==6))
        			Tab.setInhalt("Bew",g.TabErfassungstypen.getBezeichnungS(-iStt2));
        		else
        		{
        			int iRolle=-iStt2;
        			Tab.setInhalt("Rolle",g.TabRollen.getBezeichnungS(iRolle));
        			Tab.setInhalt("Stt2",g.TabStammtypen.getBezeichnungS(g.RolleToStt(iRolle)));
        		}
        		Tab.setInhalt("Eigenschaft",g.TabEigenschaften.getBezeichnungS(TabGruppenFilter.getI(i,"int3")));
        		Tab.setInhalt("Filter",TabGruppenFilter.getS(i,"aic_abfrage"));
        		Tab.setInhalt("Art",iArt==1 ? "aktiv":iArt==0 ? "inaktiv":iArt==2 ? "immer": iArt==3 ? "Gruppe": iArt==4 ? "Bew":iArt==5 ? "BewGruppe":iArt==6 ? "BewBew":iArt==7 ? "mehrfach":iArt==8 ? "SubStt":"???");
        	}
        	return Tab;
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
//                ev.getSource();
                Tabellenspeicher.showGrid(TabSpalten, "Spalten", s2,self);
                Tabellenspeicher.showGrid(TabSTT2, "STT2", s2,self);
                if (s2.equals("GruppenFilter"))
                	Tabellenspeicher.showGrid(getTabGF(), "GruppenFilter", s2,self);
                Tabellenspeicher.showGrid(TabGruppenFilter, "Ansicht", s2,self);
                Tabellenspeicher.showGrid(TabHS, "HS", s2,self);
                Tabellenspeicher.showGrid(TabKnoten, "Knoten", s2,self);
                Tabellenspeicher.showGrid(TabGruppierung, "Gruppierung", s2,self);
                Tabellenspeicher.showGrid(TabGruppen, "Gruppen", s2,self);
                Tabellenspeicher.showGrid(TabAktFormular, "AktFormular", s2,self);
                Tabellenspeicher.showGrid(TabForm, "Formulare", s2,self);
                Tabellenspeicher.showGrid(g.TabFeiertagspeicher, "Fts", s2,self);
                Tabellenspeicher.showGrid(g.TabFeiertage, "Ft", s2,self);
                Tabellenspeicher.showGrid(g.TabBSt, "BSt", s2,self);
                Tabellenspeicher.showGrid(g.TabH, "H", s2,self);
                Tabellenspeicher.showGrid(g.TabAustritt, "Austritt", s2,self);
                Tabellenspeicher.showGrid(g.TabMass, "TabMass", s2,self);
                Tabellenspeicher.showGrid(g.TabWaehrung, "Waehrung", s2,self);
                Tabellenspeicher.showGrid(g.TabBegriffe, "Begriffe", s2,self);
                Tabellenspeicher.showGrid(g.TabCodes, "Codes", s2,self);
                Tabellenspeicher.showGrid(g.TabBegriffgruppen, "Begriffgruppen", s2,self);
                Tabellenspeicher.showGrid(g.TabRollen, "Rollen", s2,self);
                Tabellenspeicher.showGrid(g.TabStammtypen, "Stammtypen", s2,self);
                Tabellenspeicher.showGrid(g.TabErfassungstypen, "Bewegungstypen", s2,self);
                Tabellenspeicher.showGrid(g.TabDialog, "Dialog", s2,self);
                Tabellenspeicher.showGrid(g.TabAmpel, "Ampel", s2,self);
                Tabellenspeicher.showGrid(g.TabIndi,"Individuell",s2,self);
                Tabellenspeicher.showGrid(g.TabLand, "Land", s2,self);
                Tabellenspeicher.showGrid(g.TabMeine, "Meine", s2,self);
                Tabellenspeicher.showGrid(g.TabMA, "MA", s2,self);
                Tabellenspeicher.showGrid(g.TabStammBild, "StammBild", s2,self);
                Tabellenspeicher.showGrid(g.TabFensterpos, "Fensterpos", s2,self);
                Tabellenspeicher.showGrid(g.TabFormulare, "TabFormulare", s2,self);
                Tabellenspeicher.showGrid(g.TabSplitPos, "SplitPos", s2,self);
                //Tabellenspeicher.showGrid(g.TabModulLizenz, "ModulLizenz", s2);
                Tabellenspeicher.showGrid(g.TabModulBerechtigt, "ModulBerechtigt", s2,self);
                Tabellenspeicher.showGrid(Global.TabThread, "alle Threads", s2,self);
                Tabellenspeicher.showGrid(g.TabStamm,"TabStamm",s2,self);
                Tabellenspeicher.showGrid(g.TabMandanten,"TabMandanten",s2,self);
                //Tabellenspeicher.showGrid(g.TabBSt,"TabBSt",s2);
                Tabellenspeicher.showGrid(Abfrage.TabBA,"TabBA",s2,self);
                Tabellenspeicher.showGrid(Abfrage.getBA(g,"2a"),"Spalten2",s2,self);
                Tabellenspeicher.showGrid(Abfrage.getBA(g,"2b"),"Spalten2-BG",s2,self);
                Tabellenspeicher.showGrid(Abfrage.getBA(g,"2c"),"Spalten2-Jeder",s2,self);
                //Tabellenspeicher.showGrid(Abfrage.TabSpalten2d,"Spalten2d",s2);
                Tabellenspeicher.showGrid(Abfrage.getBA(g,"Z2a"),"SpalteZ2",s2,self);
                Tabellenspeicher.showGrid(Abfrage.getBA(g,"Z2b"),"SpalteZ2-BG",s2,self);
                Tabellenspeicher.showGrid(Abfrage.getBA(g,"Z2c"),"SpalteZ2-Jeder",s2,self);
                //Tabellenspeicher.showGrid(Abfrage.TabSpalteZ2d,"SpalteZ2d",s2);
                Tabellenspeicher.showGrid(g.TabAuswahl, "Auswahl", s2,self);
                Tabellenspeicher.showGrid(Favorit.getTab(g), "Favoriten", s2,self);
                Tabellenspeicher.showGrid(g.TabVB, "TabVB", s2,self);
                Tabellenspeicher.showGrid(g,Static.getTabMonitor(), "TabMonitor", s2,self);
                if (s2.equals("MA"))
                  g.fixInfo("VecBG="+g.VecBG);
                else if (s2.equals("Prozesse") && (g.MySQL() || g.MS()))
                {
                  if (g.MS() && !Static.bSpWho)
                  {
                    Static.bSpWho=SQL.exists(g,"select name,crdate from sysobjects where xtype='P ' and name='sp_who2db'");
                    if (!Static.bSpWho)
                    {
                      g.fixInfo("erstelle sp_who2db:"+g.exec("CREATE PROC [dbo].[sp_who2db] (@DBName VARCHAR(200)) AS BEGIN "+
                             "DECLARE @who2 TABLE( [SPID] INT, [Status] VARCHAR(200), [Login] VARCHAR(200), [HostName] VARCHAR(200), [BlkBy] VARCHAR(20), [DBName] VARCHAR(200),"+
                             "[Command] VARCHAR(200), [CPUTime] BIGINT, [DiskIO] BIGINT, [LastBatch] VARCHAR(20), [ProgramName] VARCHAR(200), [SPID2] INT, [RequestID] INT ) "+
                             "INSERT @who2 EXEC sp_who2 SELECT * FROM @who2 WHERE DBName = @DBName END"));
                      Static.bSpWho=true;
                    }
                  }
                  new Tabellenspeicher(g, g.MySQL() ? "select ID,USER,HOST,TIME,STATE,INFO from information_schema.processlist where db=database()":
                                       "sp_who2db "+g.getDB(), true).showGrid("Prozesse",self);
                }
                //Tabellenspeicher.showGrid(g.aktiveThreads(), "aktive Threads", s2);
                /*if (s2.equals("pers. Abfragen"))
                {
                  JButton Btn=g.getButton("Loeschen");
                  final Tabellenspeicher Tab=g.getUserAbfragen();
                  if (Tabellenspeicher.showGrid(Tab, "pers. Abfragen", Btn))
                    Btn.addActionListener(new ActionListener()
                    {
                      public void actionPerformed(ActionEvent ev)
                      {
                	JCOutlinerNode Nod=Tab.Grid.getSelectedNode();
                        int i=Nod.getLevel()>0 ? Sort.geti(Nod.getLabel(),0):0;
                        if (i>0)
                        {
                    	  g.DeleteUserAbfrage(i);
                    	  Tab.Grid.selectNode(Tab.Grid.getRootNode(), null);
                    	  Nod.getParent().removeChild(Nod);
                    	  //Tab.Grid.repaint();
                    	  Tab.Grid.folderChanged(Tab.Grid.getRootNode());
                        }
                      }
                    });
                }*/
                Tabellenspeicher.showGrid(g.TabFilter, "pers. Filter", s2,self);
                Tabellenspeicher.showGrid(g.TabPersAbfragen, "pers. Abfragen", s2,self);
                //Tabellenspeicher.showGrid(g.TabPersJoker, "pers. Joker", s2);
                Tabellenspeicher.showGrid(g.TabAbfragen, "alle Abfragen", s2,self);
                Tabellenspeicher.showGrid(g.TabModelle, "alle Modelle", s2,self);
                Tabellenspeicher.showGrid(g.TabGF, "pers. GF", s2,self);
                Tabellenspeicher.showGrid(g.TabPos, "pers. Pos", s2,self);
              }
            };
          MnuTab.addActionListener(AL1);
        }

        private void Save(String sBez,String sKenn,int iBG,int iBitsNew,String sText,String sTooltip,int iProg)
        {
          //new Tabellenspeicher(g,TabGruppenFilter).showGrid("TabGruppenFilter Save");
          SQL Qry=new SQL(g);
          int iB = 0;
          if ((iBitsNew&WEB)>0)
          {
        	iB= iHS==0 ? 0:SQL.getInteger(g, "select selbst from hauptschirm where aic_hauptschirm="+iHS);
        	if (iB==0)
        	{
        	  int iBG_HS=g.TabBegriffgruppen.getAic("Hauptschirm");
        	  Qry.add("defBezeichnung",sBez);
        	  Qry.add("Kennung",sKenn);
        	  Qry.add("aic_logging",g.getLog());
        	  Qry.add("aic_begriffgruppe",iBG_HS);
        	  Qry.add0("prog", iProg);
        	  Qry.add("web",true);
        	  iB = Qry.insert("begriff",true);
        	}
        	else
        	{
        		Qry.add("defBezeichnung",sBez);
          	  	Qry.add("Kennung",sKenn);
          	  	Qry.add("aic_logging",g.getLog());
          	  	Qry.add0("prog", iProg);
        		Qry.update("begriff",iB);
        	}
          }
          Qry.add("aic_begriff",iBegriff);
          Qry.add0("aic_benutzer",(iBitsNew&JEDER)>0 && (iBitsNew&FIX)==0 ? 0:g.getBenutzer());
          Qry.add0("aic_benutzergruppe",iBG);
          Qry.add0("aic_abfrage",Abf.iAbfrage>0?Abf.iAbfrage:0);
          
          //g.progInfo("iBitsNew="+Integer.toBinaryString(iBitsNew));
          Qry.add("Bits",iBitsNew);
          Qry.add0("AIC_STAMMTYP",CboStt.getSelectedAIC());
          Qry.add("Kennung",sKenn);
          Qry.add("aic_logging",g.getLog());
          if (iB>0)
        	  Qry.add("selbst", iB);
          if (iHS==0)
            iHS=Qry.insert("Hauptschirm",true);
          else
            Qry.update("Hauptschirm",iHS);
          int iTabHS=g.TabTabellenname.getAic("HAUPTSCHIRM");
          g.setBezeichnung("!",sBez,iTabHS,iHS,0);
          //if (Static.bMemo)
            g.setMemo(sText,"",iTabHS,iHS,0);
            g.setTooltip(sTooltip,iTabHS,iHS,0);
          Qry.exec("update ansicht set ans_aic_ansicht=null where aic_hauptschirm="+iHS);
          Qry.exec("delete from ansicht where aic_hauptschirm="+iHS);
          //new Tabellenspeicher(g,TabGruppenFilter).showGrid("TabGruppenFilter vor speichern");
          for (TabGruppenFilter.moveFirst();!TabGruppenFilter.eof();TabGruppenFilter.moveNext())
          {
            Qry.add("aic_hauptschirm", iHS);
            Qry.add("int1", TabGruppenFilter.getI("int1"));
            if ((iBitsNew&WEB)>0 && TabGruppenFilter.getI("int2")==iSttRolle)
            	Qry.add("int2", -iRolle);
            else
            	Qry.add("int2", TabGruppenFilter.getI("int2"));
            Qry.add("int3", TabGruppenFilter.getI("int3"));
            Qry.add("int4", TabGruppenFilter.getI("int4"));
            if (TabGruppenFilter.getI("ans_aic_ansicht")>0)
              Qry.add("ans_aic_ansicht",TabGruppenFilter.getI("ans_aic_ansicht"));
            if (!TabGruppenFilter.isNull("aic_abfrage") && ((ShowAbfrage)TabGruppenFilter.getInhalt("aic_abfrage")).iAbfrage>0)
              Qry.add("aic_abfrage",((ShowAbfrage)TabGruppenFilter.getInhalt("aic_abfrage")).iAbfrage);
            int iAic=Qry.insert("Ansicht",true);
            int iOld=TabGruppenFilter.getI("aic_ansicht");
            TabGruppenFilter.setInhalt("aic_ansicht",iAic);
            TabGruppenFilter.push();
            for (TabGruppenFilter.moveFirst();!TabGruppenFilter.eof();TabGruppenFilter.moveNext())
              if (TabGruppenFilter.getI("ans_aic_ansicht")==iOld)
                TabGruppenFilter.setInhalt("ans_aic_ansicht",iAic);
            TabGruppenFilter.pop();
          }
          fillCboHS();
          //FillEinstellung();
          CboHS.setSelectedAIC(iHS);
          Qry.free();
        }

        private void FilterAny(Point P,boolean bShow)
        {
          g.progInfo("FilterAny1:"+bShow+"/"+iHS+"/"+CboHS.getSelectedAIC()+"/"+CboStt.getSelectedAIC());
          int iHSmom=CboHS.getSelectedAIC();
          if (iHSmom != iHS)
          {
            CboHS.setSelectedAIC(iHS);
            iHSmom=iHS;
          }
          if (DlgFilter==null)
          {
            //g.progInfo("DlgFilter erzeugen");
            DlgFilter = new JDialog(this, g.getBegriffS("Dialog","Einstellungen"));
            JPanel PnlNorth = new JPanel(new BorderLayout(2,1));
            JPanel PnlLabel = new JPanel(new GridLayout(0, 1, 2, 2));
            JPanel PnlEdit = new JPanel(new GridLayout(0, 1, 2, 2));
            JPanel PnlCbx = new JPanel(new GridLayout(0, 1, 2, 0));
            Formular FomFilter=new Formular(g,DlgFilter);
            boolean b= iHSmom>0 && TabHS.posInhalt("Aic",iHSmom);
            EdtBez = new Text(b ? Static.beiLeer(TabHS.getS("Bezeichnung"), TabHS.getS("DefBez")):"", 80);
            EdtKenn = new Text(b ? TabHS.getS("Kennung"):"", 40, Text.KENNUNG);
            EdtKenn.setEnabled(g.Def());
            CboBenutzergruppe.setSelectedAIC(b ? TabHS.getI("Aic_benutzergruppe"):0);
            GroupBox GbH = new GroupBox(g.getBegriffS("Show","Hauptschirm2"));
             JPanel PnlAuswahl = new JPanel(new BorderLayout(2,1));
             PnlAuswahl.add("Center", CboHS);
             JPanel PnlCbx2 = new JPanel(new GridLayout(1, 2, 2, 2));
             CbxAll2=g.getCheckbox("All", true);
             CbxWeb2=g.getCheckbox("Web", false);
             ActionListener ALC=new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						fillCboHS();
					}
				};
             CbxAll2.addActionListener(ALC);
             CbxWeb2.addActionListener(ALC);
             PnlCbx2.add(CbxAll2);
             if (g.Def())
            	 PnlCbx2.add(CbxWeb2);
             PnlAuswahl.add("East",PnlCbx2);
            GbH.add(PnlAuswahl);
            //g.addLabel(PnlLabel, "Hauptschirm",CboHS);
            //PnlLabel.add(new JLabel());
            g.addLabel(PnlLabel, "Bezeichnung",EdtBez);
            g.addLabel(PnlLabel, "Kennung",EdtKenn);
            g.addLabel(PnlLabel, "Stammtyp",CboStt);
            g.addLabel(PnlLabel, "Benutzergruppe",CboBenutzergruppe);
            if (g.Def())
            	g.addLabel(PnlLabel, "Prog",CboProg);
            g.addLabel(PnlLabel, "Ebene",CboEbene);

            int iBits=b ? TabHS.getI("bits"):g.SuperUser()?JEDER:0;//(int)CboHS.getSelectedFaktor();
            CbxStd=FomFilter.getCheckboxM("Standard",(iBits&STD)>0);
            CbxJeder=FomFilter.getCheckboxM("Jeder",(iBits&JEDER)>0);
            CbxFix=FomFilter.getCheckboxM("fix3",(iBits&FIX)>0);
            CbxAuswahl=FomFilter.getCheckboxM("in Auswahl",(iBits&AUSWAHL)>0);
            CbxOffen=FomFilter.getCheckboxM("offen",(iBits&OFFEN)>0);
            CbxStt=FomFilter.getCheckboxM("Stammtypen",(iBits&STT)>0);
            CbxAustritt=FomFilter.getCheckboxM("Austritt",(iBits&AUSTRITT)>0);
            RadAlles=FomFilter.getRadiobuttonM("alles");
            RadVolle=FomFilter.getRadiobuttonM("keine leere");
            RadLeere=FomFilter.getRadiobuttonM("leere");
            RadBeide=FomFilter.getRadiobuttonM("beide");
            ButtonGroup RadGrVL = new ButtonGroup();
            RadGrVL.add(RadAlles);
            RadGrVL.add(RadVolle);
            RadGrVL.add(RadLeere);
            RadGrVL.add(RadBeide);
            int iVL2=iBits & VL;
            RadAlles.setSelected(iVL2==ALLES);
            RadVolle.setSelected(iVL2==VOLLE);
            RadLeere.setSelected(iVL2==LEERE);
            RadBeide.setSelected(iVL2==BEIDE);
            if (g.Def())
            {
	            CbxWeb=FomFilter.getCheckboxM("Web",(iBits&WEB)>0);
	            EnableButtons();
	            CbxWeb.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						EnableButtons();
					}
				});
            }
            MemoHS=new AUTextArea();
            TooltipHS=new AUTextArea();
            //MemoHS.setPreferredSize(new Dimension(200,10));
            //if (Static.bMemo)
            //{
              //Vector Vec = (Vector)g.getMemoVector("Hauptschirm", CboHS.getSelectedAIC());
              //MemoHS.setText("" + Vec.elementAt(1));
              MemoHS.setText(b ? TabHS.getM("Memo"):"");
              TooltipHS.setText(b ? TabHS.getM("TT"):"");
            //}
            //g.addComp(PnlEdit, CboHS);
            //PnlEdit.add(new JLabel());
            g.addN(EdtBez,PnlEdit);
            g.addN(EdtKenn,PnlEdit);
            g.addN(CboStt,PnlEdit);
            g.addN(CboBenutzergruppe,PnlEdit);
            if (g.Def())
            	g.addN(CboProg,PnlEdit);
            g.addN(CboEbene,PnlEdit);
            //g.addComp(PnlCbx,new JLabel());
            if (g.SuperUser() || g.Spezial())
            {
              JPanel Pnl2=new JPanel(new GridLayout());
              g.addN(CbxJeder/*g.Def() ? CbxJeder:new JLabel()*/,Pnl2);
              g.addN(CbxStd,Pnl2);
              if (g.Def())
              {
            	  g.addN(CbxWeb,Pnl2);
              }
              PnlCbx.add(Pnl2);
            }
            //else
            //  g.addComp(PnlCbx, CbxJeder);
            JPanel Pnl2=new JPanel(new GridLayout());
            g.addN(CbxFix,Pnl2);
            g.addN(CbxAuswahl,Pnl2);
            if (g.Def())
            	g.addN(RadBeide,Pnl2);
            PnlCbx.add(Pnl2);
            
            	Pnl2=new JPanel(new GridLayout());
            	g.addN(RadAlles,Pnl2);            	
            	g.addN(RadVolle,Pnl2);
            	if (g.Def())
            		g.addN(RadLeere,Pnl2);
            	PnlCbx.add(Pnl2);
        	
                Pnl2=new JPanel(new GridLayout());
            	g.addN(CbxStt,Pnl2);
            	g.addN(CbxOffen,Pnl2);
            	
            	PnlCbx.add(Pnl2);
            	
            if (!g.Def())
            	CbxStt.setVisible(false);
            
            LblBenutzer.setFont(g.fontStandard);
            
            	Pnl2=new JPanel(new GridLayout());
            	g.addN(LblBenutzer,Pnl2);
            	g.addN(CbxAustritt,Pnl2);
            	PnlCbx.add(Pnl2);
            //g.addComp(PnlCbx, new JLabel());// CbxAnzeigen);
            JPanel PnlAbfrage=new JPanel(new GridLayout());
            g.addN(BtnSpaltenDef,PnlAbfrage);
            g.addN(BtnFilter,PnlAbfrage);
            if (g.Def())
            {
            	//BtnAbfrage=Fom.getButtonM("Abfrage");
            	g.addN(BtnAbfrage,PnlAbfrage);
            	
            }
            PnlCbx.add(PnlAbfrage);
            /*JPanel PnlEdit2 = new JPanel(new GridLayout(0, 3, 2, 2));
              g.addComp(PnlEdit2, CbxJeder);
              g.addComp(PnlEdit2, CbxFix);
              g.addComp(PnlEdit2, CbxAnzeigen);
            g.addComp(PnlEdit, PnlEdit2);*/
            PnlNorth.add("North",GbH);
            PnlNorth.add("West", PnlLabel);
            PnlNorth.add("Center", PnlEdit);
            PnlNorth.add("East", PnlCbx);
            JPanel PnlAll = new JPanel(new BorderLayout());
            JScrollPane Scroll=new JScrollPane(PnlAll);
            Scroll.setBorder(new EmptyBorder(new Insets(2,2,2,2)));
            PnlAll.add("North",PnlNorth);
            //GroupBox GbM = new GroupBox(g.getBegriff("Show","Memo"));
            JTabbedPane TP=new JTabbedPane();
            TP.add(g.getBegriffS("Show","Memo"),MemoHS);
            TP.add(g.getBegriffS("Show","Tooltip"),TooltipHS);
            DlgFilter.getContentPane().add("Center",Scroll);
            PnlAll.add("Center", TP);
            JPanel Pnl = new JPanel(new GridLayout(1, 0, 1, 1));
            //JToolBar Pnl=new JToolBar(JToolBar.HORIZONTAL);
            //Pnl.setOpaque(false);
            //Pnl.setFloatable(false);
            JButton BtnNeu = g.getButton("Neu");
            BtnSave = g.getButton("Speichern");
            //BtnSave.setEnabled(g.SuperUser() || g.Spezial() || (iBits&JEDER)==0);
            BtnDel = g.getButton("Loeschen");
            fillHS_Dlg(iHSmom,false);
            //BtnOk = g.getButton("Ok");
            BtnRefresh2 = g.getButton("Refresh");
            if (CbxWeb!=null && CbxWeb.isSelected())
            	BtnRefresh2.setEnabled(false);
            BtnBeenden2 = g.getButton("Beenden");
            BtnInfo=g.BtnAdd(g.getButton("Info"),"GruppenFilter",AL1);
            Pnl.add(BtnRefresh2);
            Pnl.add(BtnGruppenFilter);
            Pnl.add(BtnInfo);
            Pnl.add(BtnNeu);
            Pnl.add(BtnDel);
            Pnl.add(BtnSave);
            //Pnl.add(g.getButton2("help_hs2",null,null,"Web"));
            /*Pnl.add(new JPanel());
            Pnl.add(BtnOk);
            Pnl.add(BtnAbbruch);*/
            Pnl.add(BtnBeenden2);
            PnlAll.add("South", Pnl);
            CboHS.addItemListener(new ItemListener()
            {
		public void itemStateChanged(ItemEvent e)
		{
			if(e.getStateChange()==ItemEvent.SELECTED)
			{
                          fillHS_Dlg(CboHS.getSelectedAIC(),true);
                          int iAic=CboHS.getSelectedAIC();
                          //if (iAic==0)
                            fillCboStt();
                          if (TabRbt!=null)
                          {
                            int iPos = TabRbt.getPos("Aic", iAic);
                            if (iPos >= 0 && !((JRadioButtonMenuItem)TabRbt.getInhalt("Rbt", iPos)).isSelected())
                            {
                              ((JRadioButtonMenuItem)TabRbt.getInhalt("Rbt", iPos)).setSelected(true);
                              bNodStammGeladen = false;
                              if (!CboHS.isVisible())
                                RefreshGrid();
                            }
                          }
                          EnableButtons();
			}
		}
            });
            /*CboStt.addItemListener(new ItemListener()
            {
                public void itemStateChanged(ItemEvent e)
                {
                        if(e.getStateChange()==ItemEvent.SELECTED)
                        {
                          boolean bAicNull=CboHS.getSelectedAIC()==0;
                        }
                }
            });*/
            BtnNeu.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                BtnSave.setEnabled(true);
                BtnDel.setEnabled(false);
                DlgFilter.getRootPane().setDefaultButton(BtnSave);
                EdtBez.setText("");
                CboBenutzergruppe.setSelectedAIC(0);
                EdtBez.requestFocus();
                EdtKenn.setText("");
                //CboStt.setSelectedAIC(0);
                MemoHS.setText("");
                TooltipHS.setText("");
                CboHS.setSelectedAIC(0);
                CbxStd.setSelected(false);
                if (CbxWeb != null)
                	CbxWeb.setSelected(false);
                CboProg.setSelectedAIC(0);
                CbxJeder.setSelected(false);
                CbxFix.setSelected(false);
                RadAlles.setSelected(true);
                CbxStt.setSelected(false);
                CbxOffen.setSelected(false);
                CbxAustritt.setSelected(false);
              }
            });
            BtnSave.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                if (EdtBez.getText().equals(""))
                  new Message(Message.WARNING_MESSAGE, DlgFilter, g,10).showDialog("BezeichnungFehlt");
                else
                {
                  //new Tabellenspeicher(g,TabGruppenFilter).showGrid("TabGruppenFilter vor checkCboStt");
                  checkCboStt();
                  //new Tabellenspeicher(g,TabGruppenFilter).showGrid("TabGruppenFilter nach checkCboStt");
                  iHS = CboHS.getSelectedAIC();
                  String sKen=EdtKenn.getText();
                  if (sKen != null && sKen.length()>40)
                  {
                	  sKen=sKen.substring(0, 40);
                	  EdtKenn.setText(sKen);
                  }
                  iVL=RadVolle.isSelected() ? VOLLE:RadLeere.isSelected() ? LEERE:RadBeide.isSelected() ? BEIDE:ALLES;
                  int iBitsNew=3+(CbxStt.isSelected() ? STT:0)+(CbxAuswahl.isSelected() ? AUSWAHL:0)+
                          iVL+(CbxOffen.isSelected() ? OFFEN:0)+(CbxStd.isSelected()?STD:0)+(CbxJeder.isSelected()?JEDER:0)+(CbxFix.isSelected()?FIX:0)+(CbxAustritt.isSelected()?AUSTRITT:0)+(CbxWeb!= null && CbxWeb.isSelected() ? WEB:0);
                  Save(EdtBez.getText(), sKen,CboBenutzergruppe.getSelectedAIC(),iBitsNew, MemoHS.getValue(), TooltipHS.getValue(),CboProg.getSelectedAIC());
                  //DlgFilter.getRootPane().setDefaultButton(BtnOk);
                }
              }
            });
            BtnDel.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                int iHSmom = CboHS.getSelectedAIC();
                if (new Message(Message.YES_NO_OPTION,DlgFilter,g,0).showDialog("Loeschen",new String[] {"["+CboHS.getSelectedItem()+"]"})== Message.YES_OPTION)
                {
                  g.exec("update ansicht set ans_aic_ansicht=null where aic_hauptschirm="+iHSmom);
                  g.exec("delete from ansicht where aic_hauptschirm=" + iHSmom);
                  g.exec("delete from hauptschirm where aic_hauptschirm=" + iHSmom);
                  fillCboHS();
                  //FillEinstellung();
                  CboHS.setSelectedAIC(iHS);
                  iHS=CboHS.getSelectedAIC();
                }
              }
            });
            /*BtnOk.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                iHS = CboHS.getSelectedAIC();
                LoadHS(iHS,true);
                DlgFilter.setVisible(false);
                bNodStammGeladen = false;
                //findRbt();
		RefreshGrid();
              }
            });*/
            BtnBeenden2.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
        	//if (iHS != CboHS.getSelectedAIC())
        	//  LoadHS(iHS,true);
                DlgFilter.setVisible(false);
              }
            });
            Action cancelKeyAction = new AbstractAction() {
              /**
				 *
				 */
				private static final long serialVersionUID = -5642331968894366714L;

			public void actionPerformed(ActionEvent e)
              {
                DlgFilter.dispose();
              }
            };
            Static.Escape(BtnBeenden2,this,cancelKeyAction);

            BtnRefresh2.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent ev)
                    {
                            bNodStammGeladen = false;
                            RefreshGrid();
                            //DlgFilter.getRootPane().setDefaultButton(BtnAbbruch);
                    }
            });

            DlgFilter.setSize(840,420);
          }
          //else if (CboHS.getSelectedAIC() != iHS)
          //  CboHS.setSelectedAIC(iHS);
          g.progInfo("FilterAny2:"+bShow+"/"+iHS+"/"+CboHS.getSelectedAIC()+"/"+CboStt.getSelectedAIC());
          if (P != null)
          {
            P.translate( -100, -170);
            if(P.x < 0)
              P.x = 0;
            if(P.y < 0)
              P.y = 0;
          }
          int iSttHaupt=TabGruppenFilter.posInhalt("int2",new Integer(iSTT_Mom)) ? TabGruppenFilter.getI("int1"):0;
          TabGruppenFilter.posInhalt("int1",iSttHaupt);
          Vector<Object> Vec=new Vector<Object>();
//          g.fixtestError("iSttHaupt="+iSttHaupt);
          for (;!TabGruppenFilter.out() && TabGruppenFilter.getI("int1")==iSttHaupt;TabGruppenFilter.moveNext())
            if (TabGruppenFilter.getI("int4")==GruppenFilter.EIN || TabGruppenFilter.getI("int4")==GruppenFilter.BEW || TabGruppenFilter.getI("int4")==GruppenFilter.BEWBEW
                || TabGruppenFilter.getI("int4")==GruppenFilter.MF || TabGruppenFilter.getI("int4")==GruppenFilter.SUB)
            {
            	if (TabGruppenFilter.getI("int2")>0 || TabGruppenFilter.getI("int4")>3)
            	{
            		Vec.addElement(TabGruppenFilter.getInhalt("int2"));           	
//            		g.fixtestError("int2 dazu:"+TabGruppenFilter.getI("int2"));
            	}
            	else
            	{
            		int iSttR=g.RolleToStt(-TabGruppenFilter.getI("int2"));
            		Vec.addElement(iSttR);
            		g.fixtestError("int2 dazu: Rolle umgewandelt von "+ TabGruppenFilter.getI("int2")+" auf "+iSttR);
            	}
            }
//          g.fixtestError("fill CboEbene mit "+Vec+"/"+iSTT_Mom);
          CboEbene.fillStt(Vec,iSTT_Mom);
          if (bShow)
          {
            if (P == null)
              Static.centerComponent(DlgFilter, self);
            else
              DlgFilter.setLocation(P);
            //BtnOk.setEnabled(false);
            //DlgFilter.getRootPane().setDefaultButton(BtnAbbruch);
            DlgFilter.setVisible(true);
          }
        }

        private void fillHS_Dlg(int iHSmom,boolean bLoad)
        {
            //boolean bAicNull=CboHS.getSelectedAIC()==0;
            g.progInfo("..fillHS_Dlg "+bLoad);
                            if (bLoad)
                              LoadHS(iHSmom,true);
                            boolean bAicNull= iHSmom==0 || !TabHS.posInhalt("Aic",iHSmom);
                            EdtBez.setText(bAicNull?"":Static.beiLeer(TabHS.getS("Bezeichnung"), TabHS.getS("DefBez")));
                            EdtKenn.setText(bAicNull?"":TabHS.getS("Kennung"));
                            CboStt.setSelectedAIC(bAicNull?0:TabHS.getI("AIC_STAMMTYP"));
                            CboBenutzergruppe.setSelectedAIC(bAicNull ? 0:TabHS.getI("Aic_benutzergruppe"));
                            int iBits=bAicNull?0:TabHS.getI("bits");//(int)CboHS.getSelectedFaktor();
                            CbxJeder.setSelected((iBits&JEDER)>0);
                            CbxStd.setSelected((iBits&STD)>0);
                            CbxStt.setSelected((iBits&STT)>0);
                            CbxOffen.setSelected((iBits&OFFEN)>0);
                            CbxAustritt.setSelected((iBits&AUSTRITT)>0);
                            int iVL2=iBits & VL;
                            RadAlles.setSelected(iVL2==ALLES);
                            RadVolle.setSelected(iVL2==VOLLE);
                            RadLeere.setSelected(iVL2==LEERE);
                            RadBeide.setSelected(iVL2==BEIDE);
                            if (CbxWeb !=null)
                            {
                            	CbxWeb.setSelected((iBits&WEB)>0);
                            	if ((iBits&WEB)>0 && iHSmom>0)
                            		CboProg.setSelectedAIC(SQL.getInteger(g, "select prog from begriff join hauptschirm on begriff.aic_begriff=selbst where aic_hauptschirm="+iHSmom));
                            }
                            String s=TabHS.getI("Aic_benutzer")==0 ? "":SQL.getString(g,"select kennung from benutzer where aic_benutzer=?",TabHS.getS("Aic_benutzer"));
                            LblBenutzer.setText(s.equals("") ? "":g.getBegriffS("Show","Eigentuemer")+": "+s);
                            boolean bFix=(iBits&FIX)>0;
                            boolean bAuswahl=(iBits&AUSWAHL)>0;
                            CbxFix.setSelected(bFix);
                            CbxAuswahl.setSelected(bAuswahl);
                            //if (Static.bMemo)
                            //{
                              //Vector Vec = (Vector)g.getMemoVector("Hauptschirm", CboHS.getSelectedAIC());
                              //MemoHS.setText("" + Vec.elementAt(1));
                              MemoHS.setText(bAicNull ? "":TabHS.getM("Memo"));
                              TooltipHS.setText(bAicNull ? "":TabHS.getM("TT"));
                            //}
                            boolean bSave=true;
                            if (bFix && !g.Def())
                              bSave=SQL.getInteger(g,"select aic_benutzer from hauptschirm where aic_hauptschirm="+iHSmom)==g.getBenutzer();
                            BtnSave.setEnabled(bSave);// && (g.SuperUser() || g.Spezial() || (iBits&JEDER)==0));
                            BtnDel.setEnabled(!bAicNull && bSave);// && (g.SuperUser() || g.Spezial() || (iBits&JEDER)==0));
                            /*BtnOk.setEnabled(true);
                            if (!bAicNull)
                              DlgFilter.getRootPane().setDefaultButton(BtnOk);*/

        }

        private void checkCboStt()
        {
          int iAic=CboStt.getSelectedAIC();
          if (iAic>0 && TabGruppenFilter.posInhalt("int2",iAic))
          {
            int iStt=TabGruppenFilter.getI("int1");
            for (TabGruppenFilter.moveFirst();!TabGruppenFilter.out();TabGruppenFilter.moveNext())
              if (TabGruppenFilter.getI("int1")!=iStt)
                TabGruppenFilter.clearInhalt();
            TabGruppenFilter.posInhalt("int1",iStt);
            while (TabGruppenFilter.getI("int2")!=iAic && TabGruppenFilter.getI("int4")<3)
            {
              g.progInfo("lösche bei "+iAic+":"+TabGruppenFilter.getI("int1")+"/"+TabGruppenFilter.getI("int2")+"/"+TabGruppenFilter.getI("int3")+"/"+TabGruppenFilter.getI("int4"));
              TabGruppenFilter.clearInhalt();
            }
            TabGruppenFilter.setInhalt("ans_aic_ansicht",null);
            for (;!TabGruppenFilter.out();TabGruppenFilter.moveNext())
              TabGruppenFilter.setInhalt("int1",iAic);
          }
          if (g.bInfoGF)
            new Tabellenspeicher(g,TabGruppenFilter).showGrid("TabGruppenFilter-checkCboStt",self);
        }

	/*private void EditAny(Point P)
	{
		if (P==null)
		{
			P=new Point(Gid.getLocationOnScreen().x+40,Gid.getLocationOnScreen().y+40);
		}
		else
		  P.translate(-100,-170);
		final JDialog Dlg=new JDialog(this,g.getBegriff("Dialog","Formularaufruf"),true);
		if(P.x<0)
			P.x=0;
		if(P.y<0)
			P.y=0;
		Dlg.setLocation(P);
			JPanel PnlNorth = new JPanel(new GridLayout(0,1));
                        PnlNorth.add(new JLabel());
				g.addComp(PnlNorth,CboFormular);
                                CboFormular.setMaximumRowCount(15);
			CbxNurOrdner = g.getCheckbox("NurOrdner");
                        JPanel PnlNS = new JPanel(new GridLayout(0,2));
                        PnlNS.add(CbxNurOrdner);
                        PnlNS.add(CbxAnzahl);
                        PnlNorth.add(PnlNS);
                        PnlNorth.add(new JLabel());
		Dlg.getContentPane().add("North",PnlNorth);
		Mmo=new AUTextArea();
		Mmo.setFont(g.fontStandard);
		setToolTip();
		Dlg.getContentPane().add("Center",Mmo);
		JPanel Pnl = new JPanel(new GridLayout());
		BtnEditOk=g.getButton("Ok");
                Dlg.getRootPane().setDefaultButton(BtnEditOk);

                if (bStamm)
                  CbxNurOrdner.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent e)
                    {
                      Enable2();
                    }
                  });

		CbxNurOrdner.setSelected(bNurOrdner);
                Enable2();

		BtnEditOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				bNurOrdner=CbxNurOrdner.isSelected();
				BtnEditOk=null;
				Dlg.dispose();
				DetailAufruf(true,CboFormular.getSelectedAIC());
			}
		});
		JButton BtnDlgAbbruch=g.getButton("Abbruch");
		BtnDlgAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Dlg.dispose();
			}
		});
                Action cancelKeyAction = new AbstractAction() {
                  public void actionPerformed(ActionEvent e)
                  {
                    Dlg.dispose();
                  }
                };
                Static.Escape(BtnDlgAbbruch,this,cancelKeyAction);

		Pnl.add(BtnEditOk);
		Pnl.add(BtnDlgAbbruch);
		Dlg.getContentPane().add("South",Pnl);
		Dlg.setSize(250,200);
		Dlg.setVisible(true);
	}*/

        /*private void FormSuchen(Point P)
        {
          final JDialog Dlg=new JDialog(this,g.getBegriff("Dialog","Formularsuche"));
          P.translate(-100,-70);
          if(P.y<0)
            P.y=0;
          Dlg.setLocation(P);
            JPanel Pnl = new JPanel(new BorderLayout());
            JLabel Lbl=g.getLabel("Eigenschaft",SwingConstants.LEFT);
            Lbl.setFont(g.fontBezeichnung);
            Lbl.setForeground(g.ColBezeichnung);
            Pnl.add("West",Lbl);
            final Text Edt=new Text("",40);
            Edt.setFont(g.fontStandard);
            Pnl.add("Center",Edt);
          Dlg.getContentPane().add("North",Pnl);
            final JCOutliner GidDaten = new JCOutliner(new JCOutlinerFolderNode(""));
            String [] s = new String[]{g.getBegriff("Show","Eigenschaft"),g.getBegriff("Show","Art"),g.getBegriff("Show","Formular"),g.getBegriff("Show","Memo")};
            GidDaten.setColumnButtons(s);
            GidDaten.setNumColumns(s.length);
            GidDaten.setRootVisible(false);
            GidDaten.addActionListener(new JCActionListener()
            {
                    public void actionPerformed( JCActionEvent ev )
                    {
                      DetailAufruf(true,Tabellenspeicher.geti(GidDaten.getSelectedNode().getUserData()));
                    }
            });

          Dlg.getContentPane().add("Center",GidDaten);
            Pnl = new JPanel(new GridLayout());
            final JButton BtnSuche=g.getButton("Suche");
            final JButton BtnOk=g.getButton("Ok");
            Dlg.getRootPane().setDefaultButton(BtnSuche);
            final JButton BtnAbbruch=g.getButton("Abbruch");
            Pnl.add(BtnSuche);
            Pnl.add(BtnOk);
            Pnl.add(BtnAbbruch);
            BtnSuche.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent ev)
                    {
                            Vector Vec=SQL.getVector("select * from (select aic_eigenschaft,kennung"+g.AU_Bezeichnung2("eigenschaft")+
                                                     " Bez from eigenschaft) x where kennung like '"+Edt.getText()+"' or bez like '"+Edt.getText()+"'",g);
                            Tabellenspeicher Tab=new Tabellenspeicher(g,"select gruppe_zuordnung.aic_eigenschaft,f.aic_begriff,'Gruppe' Art from begriff f"+g.join("formular","f","begriff")+
                                g.join2("darstellung","formular")+g.join("begriff","darstellung")+g.join2("gruppe_zuordnung","begriff")+
                                " where f.aic_stammtyp="+iSTT_Mom+" and gruppe_zuordnung.aic_eigenschaft"+Static.SQL_in(Vec),true);
                            Tabellenspeicher Tab2=new Tabellenspeicher(g,"select fixeigenschaft.aic_eigenschaft,f.aic_begriff from begriff f"+g.join("formular","f","begriff")+
                                g.join2("darstellung","formular")+g.join("begriff","darstellung")+g.join2("abfrage","begriff")+g.join2("spalte","abfrage")+g.join2("fixeigenschaft","spalte")+
                                " where f.aic_stammtyp="+iSTT_Mom+" and fixeigenschaft.aic_eigenschaft"+Static.SQL_in(Vec),true);
                            for (Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
                            {
                              Tab.addInhalt("aic_eigenschaft",Tab2.getI("aic_eigenschaft"));
                              Tab.addInhalt("aic_begriff",Tab2.getI("aic_begriff"));
                              Tab.addInhalt("Art","Spalte");
                            }
                            JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)GidDaten.getRootNode();
                            NodeRoot.removeChildren();
                            for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                              if (g.TabBegriffe.posInhalt("aic",Tab.getI("aic_begriff")) && g.Berechtigung())
                              {
                                Vector<String> VecSpalten = new Vector<String>();
                                VecSpalten.addElement(g.TabEigenschaften.posInhalt("aic",Tab.getI("aic_eigenschaft")) ? g.TabEigenschaften.getS("Bezeichnung"):Edt.getText());
                                VecSpalten.addElement(Tab.getS("Art"));
                                VecSpalten.addElement(g.TabBegriffe.getS("Bezeichnung"));
                                VecSpalten.addElement(g.TabBegriffe.getS("Info"));
                                JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
                                Node.setUserData(g.TabBegriffe.getInhalt("aic"));
                              }
                            GidDaten.folderChanged(NodeRoot);
                    }
            });
            BtnOk.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent ev)
                    {
                      DetailAufruf(true,Tabellenspeicher.geti(GidDaten.getSelectedNode().getUserData()));
                      Dlg.dispose();
                    }
            });
            BtnAbbruch.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent ev)
                    {
                            Dlg.dispose();
                    }
            });
          Dlg.getContentPane().add("South",Pnl);
          Dlg.setSize(640,300);
          Dlg.setVisible(true);
        }*/

	private void suchen(Point P)
	{
	  if (DlgSuche==null)
	  {
            g.fixtestInfo("Suche erzeugen");
	    DlgSuche=new JDialog(this,g.getBegriffS("Dialog","Suche"));
	    P.translate(-100,-70);
	    if(P.y<0)
	      P.y=0;
	    DlgSuche.setLocation(P);


		JPanel Pnl = new JPanel(new BorderLayout());
                //final JCheckBox CbxSpot=g.getCheckbox("Spotlight",(iSuchBits&8)>0);

		//final JCheckBox CbxStart=g.getCheckbox("Volltextsuche",(iSuchBits&1)==0);
		//final JCheckBox CbxSucheAb=g.getCheckbox("Suche ab",(iSuchBits&2)>0);
		//final JCheckBox CbxSucheCase=g.getCheckbox("Case",(iSuchBits&4)>0);
		final ComboSort CboSuche=new ComboSort(g,ComboSort.kein);
                CboSuche.setFont(g.fontBezeichnung);
                /*CbxSpot.addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    CboSuche.setEnabled(!CbxSpot.isSelected());
                    if (CbxSpot.isSelected())
                      CboSuche.setSelectedAIC(0);
                  }
                });*/
		if (TabSpalten == null || TabSpalten.isEmpty())
		{
			CboSuche.addItem(g.getBegriffS("Show","Bezeichnung"),1,"");
		}
		else
		{
			Vector VecGidSpalten = Abf.getBezeichnung();
                        CboSuche.setKein(true);
			for(int i=0;i<VecGidSpalten.size();i++)
				CboSuche.addItem(""+VecGidSpalten.elementAt(i),i+1,"");
		}
		CboSuche.setSelectedAIC(iSpalte);
		JPanel Pnl2 = new JPanel(new BorderLayout(2,2));
			Pnl2.add("West",CboSuche/*new JLabel("Suchtext:")*/);
			EdtSuche=new Text(""/*sSuchtext*/,20);
			EdtSuche.addKeyListener(new KeyListener()
			{
				public void keyPressed(KeyEvent e)
				{}
				public void keyReleased(KeyEvent e)
				{
				  if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
				  {
				    DlgSuche.setVisible(false);
				    //DlgSuche=null;
				  }
				}
				public void keyTyped(KeyEvent e)
				{}
			});
                        EdtSuche.setFont(g.fontStandard);
			Pnl2.add("Center",EdtSuche);
		Pnl.add("North",Pnl2);
		Pnl2 = new JPanel(new FlowLayout());
                //if (g.SuperUser())
                  //Pnl2.add(CbxSpot);
		//Pnl2.add(CbxStart);
		//Pnl2.add(CbxSucheAb);
		//Pnl2.add(CbxSucheCase);
		Pnl.add("Center",Pnl2);

                JScrollPane Scroll=new JScrollPane(Pnl);
                Scroll.setBorder(new EmptyBorder(new Insets(5,5,0,2)));
		DlgSuche.getContentPane().add("North",Scroll);

		Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
		//JButton BtnDlgSuche=g.getButton("Suche");
                final JButton BtnDlgWeiter=g.getButton("Weiter");
                //JButton BtnDlgHelp = g.getButton("help_hs3");
                DlgSuche.getRootPane().setDefaultButton(BtnDlgWeiter);

                BtnDlgWeiter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				//g.progInfo("Suche:"+Edt.getText()+"/"+iSTT_Mom);
				sSuchtext=EdtSuche.getText();
				//bStartedMit=CbxStart.isSelected();
				bAb=false;
				//iSuchBits=/*(CbxStart.isSelected()?0:1)+*/2+(CbxSucheCase.isSelected()?4:0);//+(CbxSpot.isSelected()?8:0);
				iSpalte=CboSuche.getSelectedAIC();
                                //g.progInfo("Suche:"+sSuchtext+"/"+iSpalte+"/"+iSuchBits);
                                /*if (CbxSpot.isSelected())
                                {
                                  sucheBegriff(sSuchtext);
                                }
                                else*/ if (suche((JCOutlinerFolderNode)Gid.getRootNode(),sSuchtext,iSpalte-1))
                                {
                                  bAb=true;
                                  suche((JCOutlinerFolderNode)Gid.getRootNode(),sSuchtext,iSpalte-1);
                                }
                                //Static.makeVisible(Gid,Gid.getSelectedNode());
				//Dlg.dispose();
			}
		});
		JButton BtnDlgBeenden=g.getButton("Beenden");
		Action cancelKeyAction2 = new AbstractAction() {
		  /**
		   *
		   */
		  private static final long serialVersionUID = 1830461202585978279L;

		  public void actionPerformed(ActionEvent e)
                  {
                    DlgSuche.setVisible(false);
		    //DlgSuche.dispose();
		    //DlgSuche=null;
                  }
                };
		BtnDlgBeenden.addActionListener(cancelKeyAction2);
		Static.Escape(BtnDlgBeenden,this,cancelKeyAction2);
		//Pnl.add(BtnDlgSuche);
                Pnl.add(BtnDlgWeiter);
                //Pnl.add(BtnDlgHelp);
		Pnl.add(BtnDlgBeenden);
		DlgSuche.getContentPane().add("South",Pnl);
                if (bSpalte1 && CboSuche.getSelectedAIC()==0)
                  CboSuche.setSelectedAIC(1);
		DlgSuche.pack();
		DlgSuche.setVisible(true);
	        EdtSuche.requestFocus();
	  }
	  else
          {
            //g.fixtestInfo("Such-text löschen");
            EdtSuche.requestFocus();
            EdtSuche.selectAll();
            //EdtSuche.setText("");
            DlgSuche.setVisible(true);
          }
	}

        private void open(int iAic,int iEig)
        {
          setCursor(new Cursor(Cursor.WAIT_CURSOR));
          int iPosB = g.TabBegriffe.getPos("Aic", iAic);
          int iBG = g.TabBegriffe.getI(iPosB, "Gruppe");
          if (iBG == g.TabBegriffgruppen.getAic("Druck"))
            openDF(iAic);
          else if (iBG == g.TabBegriffgruppen.getAic("Frame"))
            DetailAufruf(true, iAic,iEig);
          setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        private String Upper(String s)
        {
          return /*(iSuchBits&1)==0 ?*/ "UPPER("+s+")";//:s;
        }

        private boolean BezOk(String s,String sBez)
        {
          //if((iSuchBits&4)==0)
          {
            s = s.toUpperCase();
            sBez = sBez.toUpperCase();
          }
          return /*(iSuchBits&1)>0 ? sBez.startsWith(s):*/sBez.indexOf(s)>-1;
        }

        private void Spotlightdialog(Point P)
        {
          if (DlgSpotlight==null)
          {
            DlgSpotlight = new JDialog(this, g.getBegriffS("Dialog","Spotlightsuche"));
            //DlgSpotlight.setIconImage(g.getImage("Begriff","Spotlight"));
            //DlgSpotlight.setMaximumSize(new Dimension(800,600));
            JPanel PnlN=new JPanel(new FlowLayout(FlowLayout.CENTER,2,2));
            EdtSucheSL=new Text(sSuchtext,20);
            PnlX=new JPanel(new BorderLayout(2,2));
            //PnlX.setMaximumSize(new Dimension(700,500));
            EdtSucheSL.setMaximumSize(new java.awt.Dimension(100,18));
            EdtSucheSL.addKeyListener(new KeyListener()
            {
              public void keyPressed(KeyEvent e) {}
              public void keyReleased(KeyEvent e) {}
              public void keyTyped(KeyEvent e)
              {
                    char cKey=e.getKeyChar();
                    g.testInfo("key="+((int)cKey));
                    if (cKey==10)
                    {
                      sucheBegriff(EdtSucheSL.getText(),CboModul==null?0:CboModul.getSelectedAIC());
                      /*int iW1=DlgSpotlight.getWidth();
                      DlgSpotlight.validate();
                      DlgSpotlight.pack();
                      int iW2=DlgSpotlight.getWidth();
                      int iH=DlgSpotlight.getHeight();
                      if (iH>500)
                        DlgSpotlight.setSize(new Dimension(iW2+20,500));
                      Point P=DlgSpotlight.getLocation();
                      P.translate(iW1-iW2,0);
                      DlgSpotlight.setLocation(P);*/
                    }
                    if (cKey==27)
                      DlgSpotlight.setVisible(false);
              }
            });
            JButton BtnSucheSL=g.getButton("Suche");
            BtnSucheSL.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev)
                {
                        sucheBegriff(EdtSucheSL.getText(),CboModul==null?0:CboModul.getSelectedAIC());
                }
            });
            JButton BtnEndSS=g.getButton("Beenden");
            Action cancelKeyActionSS = new AbstractAction() {
              private static final long serialVersionUID = -3465254972996464502L;
              public void actionPerformed(ActionEvent e) {
                DlgSpotlight.setVisible(false);
              }
            };
            Static.Escape(BtnEndSS,DlgSpotlight,cancelKeyActionSS);
            BtnEndSS.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev)
                {
                        DlgSpotlight.setVisible(false);
                }
            });
            if (g.Def())
            {
              CboModul=new ComboSort(g);
              CboModul.fillBegriffTable("Modul",true);
              CboModul.setPreferredSize(new java.awt.Dimension(250,16));
              PnlN.add(CboModul);
            }
            PnlN.add(g.getLabel("Suchbegriff"));
            PnlN.add(EdtSucheSL);
            PnlN.add(BtnSucheSL);
            PnlN.add(BtnEndSS);
            /*TitledBorder TB=new TitledBorder(g.getBegriff("Dialog","Spotlightsuche"));
            TB.setBorder(new javax.swing.border.LineBorder(g.ColRahmen,3,true));
            PnlX.setBorder(TB);*/
            PnlX.setOpaque(true);
            DlgSpotlight.getContentPane().add("Center",PnlX);

            PnlX.add("North",PnlN);
            //DlgSpotlight.setUndecorated(true);
            DlgSpotlight.pack();
          }
          EdtSucheSL.setText("");
          EdtSucheSL.requestFocus();
          int iW1=DlgSpotlight.getWidth();
          P.translate(400-iW1, 24);
          DlgSpotlight.setLocation(P);
          DlgSpotlight.setVisible(true);
        }

        private void sucheBegriff(String rs,int iModul)
        {
          long lClock = Static.get_ms();
          Vector<Integer> Vec=new Vector<Integer>();
          Vec.addElement(g.TabBegriffgruppen.getAic("Druck"));
          Vec.addElement(g.TabBegriffgruppen.getAic("Frame"));
          String s=rs+"%";
          //if ((iSuchBits&1)==0)
            s="%"+s;
          //boolean bUC=(iSuchBits&4)==0;
          //s=(bUC?" like UPPER('":" like '")+s+(bUC?"')":"'");
          s=" like "+Upper("'"+s+"'");
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from (select 'BEGRIFF' Tab,aic_begriff aic,null aic_eigenschaft,defbezeichnung"+g.AU_Bezeichnung("Begriff")+g.AU_Memo("Begriff")+
                                                " from begriff where "+g.in("aic_begriffgruppe",Vec)+" and aic_stammtyp="+iSTT_Mom+") x where "+Upper("defbezeichnung")+s+" or "+Upper("Bezeichnung")+s,true);
          Vector Vec2=SQL.getVector("select * from (select aic_eigenschaft"+g.AU_Bezeichnung2("eigenschaft")+" Bez from eigenschaft) x where bez"+s,g);
          g.progInfo("Vec2="+Vec2);
          //new Tabellenspeicher(g,"select * from (select aic_eigenschaft"+g.AU_Bezeichnung2("eigenschaft")+" Bez from eigenschaft) x where bez"+s,true).showGrid("Eig");
          /*Tab.addTab(new Tabellenspeicher(g,"select distinct * from (select 'Teil' Tab,b.aic_begriff aic,b2.aic_begriffgruppe aic_eigenschaft,b2.defbezeichnung"+g.AU_Bezeichnung3("Begriff","b2")+g.AU_Memo("Begriff","b")+
                                          " from begriff b"+g.join("formular","b","begriff")+g.join2("darstellung","formular")+g.join("begriff","b2","darstellung","begriff")+
                                          " where b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame")+" and b.aic_stammtyp="+iSTT_Mom+" and b2.aic_begriffgruppe<>"+g.TabBegriffgruppen.getAic("Panel")+") x where "+Upper("defbezeichnung")+s+" or "+Upper("Bezeichnung")+s,true));*/
          Tab.addTab(new Tabellenspeicher(g,"select 'Gruppe' Tab,f.aic_begriff aic,gruppe_zuordnung.aic_eigenschaft,f.defbezeichnung"+g.AU_Bezeichnung3("eigenschaft","gruppe_zuordnung")+g.AU_Memo("Begriff","f")+
                                          " from begriff f"+g.join("formular","f","begriff")+g.join2("darstellung","formular")+g.join("begriff","darstellung")+g.join2("gruppe_zuordnung","begriff")+
                                          " where "+g.in("gruppe_zuordnung.aic_eigenschaft",Vec2)+" and f.aic_stammtyp="+iSTT_Mom,true));
          Tab.addTab(new Tabellenspeicher(g,"select distinct 'Spalte' Tab,f.aic_begriff aic,fixeigenschaft.aic_eigenschaft,f.defbezeichnung"+g.AU_Bezeichnung3("eigenschaft","fixeigenschaft")+g.AU_Memo("Begriff","f")+
                                         " from begriff f"+g.join("formular","f","begriff")+g.join2("darstellung","formular")+g.join("begriff","darstellung")+g.join2("abfrage","begriff")+
                                         g.join2("spalte","abfrage")+g.join2("fixeigenschaft","spalte")+" where"+g.bit("spalte.bits",Abfrage.cstEditierbar)+" and "+g.in("fixeigenschaft.aic_eigenschaft",Vec2)+" and f.aic_stammtyp="+iSTT_Mom,true));
          //Tab.showGrid("sucheBegriff");
          if (GidsB ==null)
          {
            GidsB = new AUOutliner(new JCOutlinerFolderNode(""));
            GidsB.setRootVisible(false);
            String[] sAry=g.Def()? new String[] {"Bezeichnung", "Kennung", "Eigenschaft"/*,"Anzahl"*/,"Gruppe", "Stammtyp", "Memo"}:
                                   new String[] {"Bezeichnung", "Eigenschaft"/*,"Anzahl"*/,"Gruppe", "Stammtyp", "Memo"};
            for (int i=0;i<sAry.length;i++)
              sAry[i]=g.getBegriffS("Show", sAry[i]);
            GidsB.setColumnButtons(sAry);
            GidsB.setNumColumns(sAry.length);
            GidsB.setColumnLabelSortMethod(Sort.sortMethod);
            //GidsB.setMaximumSize(new Dimension(900,600));
          }
          else
            ((JCOutlinerFolderNode)GidsB.getRootNode()).removeChildren();
          JCOutlinerFolderNode NodP=(JCOutlinerFolderNode)GidsB.getRootNode();
          Vector VecB=iModul==0 ? null:SQL.getVector("select beg_aic_begriff from begriff_zuordnung where aic_begriff="+iModul,g);
          //Vector<Integer> VecSS=new Vector<Integer>();
          //JCOutlinerNodeStyle StyNull=g.getStyle(null);
          for (Tab.moveFirst();!Tab.out();Tab.moveNext())
          {
            int iPosB=g.TabBegriffe.getPos("Aic",Tab.getI("aic"));
            boolean bBG=Tab.getS("Tab").equals("Teil");
            boolean bEig=!bBG && !Tab.isNull("aic_eigenschaft");
            String sBez=g.getBegBez2(iPosB);//Static.bDefBezeichnung ? Tab.getS("DefBezeichnung"):Static.beiLeer(Tab.getS("Bezeichnung"), Tab.getS("DefBezeichnung"));
            String sEig=bEig ? g.TabEigenschaften.getBezeichnungS(Tab.getI("aic_eigenschaft")):
                bBG ?Static.bDefBezeichnung ? Tab.getS("DefBezeichnung"):Static.beiLeer(Tab.getS("Bezeichnung"), Tab.getS("DefBezeichnung")):"";
            if (iPosB>=0 && g.TabBegriffe.getI(iPosB,"Stt")>0 && g.BerechtigungS(iPosB) && BezOk(rs,bEig || bBG ? sEig:sBez) && (VecB==null || VecB.contains(Tab.getI("aic"))))
            {
              /*if (VecSS.contains(Tab.getI("aic")))
              {

              }
              else*/
              {
                Vector<Comparable> VecVisible = new Vector<Comparable>();
                Vector<Integer> VecInvisible = new Vector<Integer>();
                VecVisible.addElement(sBez);
                if (g.Def())
                  VecVisible.addElement(g.TabBegriffe.getS(iPosB,"Kennung"));
                VecVisible.addElement(sEig);
                //VecVisible.addElement(1);
                VecVisible.addElement(bEig ? Tab.getS("Tab") : g.TabBegriffgruppen.getBezeichnungS(bBG ? Tab.getI("aic_eigenschaft") : g.TabBegriffe.getI(iPosB, "Gruppe")));
                int iPosS = g.TabStammtypen.getPos("Aic", g.TabBegriffe.getI(iPosB, "Stt"));
                //g.progInfo(Tab.getI("aic")+"/"+iPosB+"/"+iPosS);
                VecVisible.addElement(iPosS > -1 ? g.TabStammtypen.getS(iPosS, "Bezeichnung") : "");
                //VecVisible.addElement(iPosS > -1 ? g.TabBegriffe.getS(iPosB, "Info") : "");
                VecVisible.addElement(Tab.getS("Memo"));
                VecInvisible.addElement(Tab.getI("aic"));
                VecInvisible.addElement(g.TabBegriffe.getI(iPosB, "Stt"));
                VecInvisible.addElement(Tab.getI("aic_eigenschaft"));
                JCOutlinerNode Nod = new JCOutlinerNode(VecVisible, NodP);
                //Nod.setStyle(StyNull);
                Nod.setUserData(VecInvisible);
                //VecSS.addElement(Tab.getI("aic"));
              }
            }
          }
          GidsB.sortByColumn(0,Sort.sortMethod);
          GidsB.folderChanged(NodP);
          GidsB.addActionListener(new JCActionListener()
          {
                public void actionPerformed( JCActionEvent ev )
                {
                  g.testInfo("GidsB:"+ev);
                  //if(ev.getStateChange() == ItemEvent.SELECTED)
                  {
                        JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
                        g.progInfo("Nod.getUserData()="+Nod.getUserData());
                        int iAic=Sort.geti(Nod.getUserData(),0);
                        int iStt=Sort.geti(Nod.getUserData(),1);
                        int iEig=Sort.geti(Nod.getUserData(),2);
                        //g.progInfo("GidsB:"+iAic+"/"+iStt);
//                        g.fixtestError("prüft int2:"+TabGruppenFilter.getVecSpalte("int2"));
                        if (g.BerechtigungS(g.TabBegriffe.getPos("Aic",iAic)))
                        {
                          if (iStt == iSTT_Mom)
                            open(iAic,iEig);
                          else if (TabGruppenFilter.getVecSpalte("int2").contains(new Integer(iStt)))
                          {
                            Nod=NodStammMom;
                            int iStt2=iSTT_Mom;
                            while(iStt2 != iStt && Nod.getLevel() > 0) {
                              Nod = Nod.getParent();
                              iStt2=Sort.geti(Nod.getUserData(),1);
                            }
                            if (iStt==iStt2)
                            {
                              Gid.selectNode(Nod);
                              open(iAic,iEig);
                            }
                            else
                              new Message(Message.INFORMATION_MESSAGE, DlgSpotlight, g,10).showDialog("Stammtyp_wechseln");
                          }
                          else
                          {
                            Tabellenspeicher TabAppl = new Tabellenspeicher(g,
                                "select begriff.aic_begriff,kennung from begriff_zuordnung z join begriff on z.beg_aic_begriff=begriff.aic_begriff where z.aic_begriff=" + iAic +
                                " and begriff.aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Applikation"), true);
                            boolean b = false;
                            for (TabAppl.moveFirst(); !b && !TabAppl.eof(); TabAppl.moveNext())
                            {
                              int iAppl=TabAppl.getI("aic_begriff");
                              b = g.BerechtigungS(g.TabBegriffe.getPos("Aic",iAppl));
                              if (b)
                              {
                                setCursor(new Cursor(Cursor.WAIT_CURSOR));
                                g.setForm(iStt,iAic);
                                int iPos=g.TabFormulare.getPos("Aic",iAppl);
                                if (iPos>=0)
                                  ((Hauptschirm)g.TabFormulare.getInhalt("Formular",iPos)).setVisible(true);
                                else
                                {
                                  Object Obj=Hauptschirm.get(TabAppl.getS("Kennung"), g);
                                  //g.putTabFormulare(iAppl,0,Obj);
                                }
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                              }
                            }
                            if (!b)
                              new Message(Message.INFORMATION_MESSAGE, DlgSpotlight, g,10).showDialog("keine_Applikation");
                            //Static.printError("Aufruf mit anderern Stammtyp noch nicht möglich");
                          }
                        }
                        else
                          new Message(Message.WARNING_MESSAGE, DlgSpotlight, g,10).showDialog("keineBerechtigung");
                        //g.fixInfo("Userdata:"+Nod.getUserData());
                }
              }
          });
          PnlX.add("Center", GidsB);
          int iW1=DlgSpotlight.getWidth();
          DlgSpotlight.validate();
          DlgSpotlight.pack();
          int iW2=DlgSpotlight.getWidth();
          int iH=DlgSpotlight.getHeight();
          //if (iH>500)
            DlgSpotlight.setSize(new Dimension(iW2+18,iH>500 ? 500:iH));
          Point P=DlgSpotlight.getLocation();
          P.translate(iW1-iW2-18,0);
          DlgSpotlight.setLocation(P);
          EdtSucheSL.requestFocus();
          g.clockInfo("Spotlightsuche "+rs,lClock);
          //return GidsB;
          /*JDialog Dlg=new JDialog(this,g.getBegriffS("Dialog","Suchergebnis")+" "+g.getBegriffS("Show","fuer")+" "+rs);
          Dlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          Dlg.getContentPane().add("Center",GidsB);
          Dlg.pack();
          Dlg.setVisible(true);*/
        }

	private boolean suche(JCOutlinerFolderNode Nod,String sText,int iPos)
	{
		boolean b=true;
		Vector Vec = Nod.getChildren();
                if(/*(iSuchBits&4)==0 && */sText != null)
                  sText=sText.toUpperCase();
                //int i2=iPos;
		if (Vec != null)
		{
			for(int i=0;i<Vec.size() && b;i++)
			{
				JCOutlinerFolderNode NodNeu=(JCOutlinerFolderNode)Vec.elementAt(i);
				//if (NodNeu.getLevel()<4)
				//  g.progInfo(NodNeu.getLevel()+"_"+i+":"+NodNeu.getLabel()+"/"+NodNeu.getUserData());
                                //g.progInfo("Ebene"+NodNeu.getLevel()+":"+iSTT_Mom+"/"+((Integer)((Vector)NodNeu.getUserData()).elementAt(1)).intValue());
				if (iSTT_Mom == ((Integer)((Vector)NodNeu.getUserData()).elementAt(1)).intValue()
					&& ((Integer)((Vector)NodNeu.getUserData()).elementAt(0)).intValue()!=0)
				{
                                  //g.progInfo("Stamm:"+iStamm_Mom+"/"+((Integer)((Vector)NodNeu.getUserData()).elementAt(0)).intValue());
					if (sText==null)
                                        {
                                          if (((Integer)((Vector)NodNeu.getUserData()).elementAt(0)).intValue()==iStamm_Mom)
                                          {
                                            NodStammMom=NodNeu;
                                            //Static.makeVisible(Gid, NodNeu);
                                            return false;
                                          }
                                        }
                                        else if (bAb)
                                        {
                                          //int i2=iPos;
                                          //g.progInfo("Ab:"+iPos+"/"+((Vector)NodNeu.getLabel()).size()+"/"+(iPos>=0 && i2>iPos || i2==((Vector)NodNeu.getLabel()).size()));
                                          for (int i2=iPos;i2==iPos || iPos<0 && i2<((Vector)NodNeu.getLabel()).size();i2++)
                                          {
                                            if (i2<0) i2++;
                                            String sMom=Sort.gets(((Vector)NodNeu.getLabel()).elementAt(i2));
                                            //g.progInfo("sMom:"+sMom+"/"+sText+"/"+i2);
						//if((iSuchBits&4)==0)
                                                  sMom=sMom.toUpperCase();
						if(bBeginn ? sMom.startsWith(sText):sMom.indexOf(sText)>-1)
						{
							Static.makeVisible(Gid,NodNeu);
                                                        Static.makeVisible(null,NodNeu);
							//g.progInfo("gefunden:"+NodNeu.getLabel());
							return false;
						}
                                          }
                                        }
					else if(iStamm_Mom == ((Integer)((Vector)NodNeu.getUserData()).elementAt(0)).intValue())
						bAb=true;
				}
				else
					b=suche(NodNeu,sText,iPos);
				if (!b)
                                {
                                  NodNeu.setState(BWTEnum.FOLDER_OPEN_ALL);
                                  Gid.folderChanged(NodNeu.getParent());
                                }
			}

		}//iSTT_Mom
		return b;

	}

	/*private void OeffneAlle(JCOutlinerFolderNode Nod,int iEbene)
	{
		//g.progInfo("OeffneAlle");
                Vector Vec=Nod==null ? Gid.getOutliner().selected_nodes:Nod.getChildren();
		if (Vec != null)
		{
                  if (iEbene>0)
                  {
                    //g.progInfo(iEbene+".:"+Nod.getLabel()+"/"+Static.className(Nod));
                    Nod.setState(BWTEnum.FOLDER_OPEN_ALL);
                  }
                  for(int i=0;i<Vec.size();i++)
                  {
                    //g.progInfo(iEbene+".:"+((JCOutlinerFolderNode)Vec.elementAt(i)).getLabel()+"/"+Static.className(Vec.elementAt(i)));
                    OeffneAlle((JCOutlinerFolderNode)Vec.elementAt(i), iEbene + 1);
                  }
                  if (iEbene==0)
                    Gid.folderChanged(Gid.getRootNode());
		}
	}*/

	/*private void EinstellungsEvent()
	{
		FomEinstellung=new Formular("einstellg_hs",self,g);
		final JCheckBox CbxAutoDarstellen = FomEinstellung.getCheckbox("Auto");
		final JCheckBox CbxNurOrdner = FomEinstellung.getCheckbox("NurOrdner");
		final JCheckBox CbxStammtypen = FomEinstellung.getCheckbox("Stammtypen");
		Static.addActionListener(FomEinstellung.getButton("Ok"),new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				bNurOrdner=CbxNurOrdner.isSelected();
				bAutoDarstellen=CbxAutoDarstellen.isSelected();
				FomEinstellung.hide();
				if(CbxStammtypen.isSelected() != bStammtypen)
				{
					bStammtypen=CbxStammtypen.isSelected();
					///if (bMitPeriode)
						RefreshGrid();
					///else
					///	GidDarstellung();
				}
			}
		});
		Static.addActionListener(FomEinstellung.getButton("Abbruch"),new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				CbxNurOrdner.setSelected(bNurOrdner);
				CbxAutoDarstellen.setSelected(bAutoDarstellen);
				CbxStammtypen.setSelected(bStammtypen);
				FomEinstellung.hide();
			}
		});
		BtnEinstellung.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				CbxNurOrdner.setSelected(bNurOrdner);
				CbxAutoDarstellen.setSelected(bAutoDarstellen);
				CbxStammtypen.setSelected(bStammtypen);
				Static.centerComponent(FomEinstellung.thisFrame,self);
				FomEinstellung.show();
			}
		});
	}*/

	public void finalize()
	{
		Count.sub(Count.Hauptschirm);
	}

        private void LoadHS(int riHS,boolean bBits)
        {
          g.progInfo(" ---   --- LoadHS:"+riHS+"/"+bBits);
          //if (g.bAutoInit) riHS=0;
          SQL Qry=new SQL(g);
          if (riHS>0) Qry.open("select hs.*"+g.AU_Bezeichnung3("hauptschirm", "hs")+" from hauptschirm hs where aic_hauptschirm="+riHS);
          Abf = new ShowAbfrage(g, riHS==0?0:Qry.getI("AIC_Abfrage"), Abfrage.cstHS_Spalte);
          iSttHS = riHS>0 ? Qry.getI("aic_stammtyp"):0;
          if (riHS>0)
            TabGruppenFilter = new Tabellenspeicher(g,"select "+g.int1_int4()+",aic_abfrage,aic_ansicht,ans_aic_ansicht from ansicht where aic_hauptschirm="+riHS+" order by aic_ansicht",true);
          else
          {
            if (TabGruppenFilter == null)
              TabGruppenFilter=new Tabellenspeicher(g,new String[] {"int1","int2","int3","int4","aic_abfrage","aic_ansicht","ans_aic_ansicht"});
            else
        	  TabGruppenFilter.clearAll();
            //if (VecStt!=null && VecStt.size()==1)
            //  VecStt.setElementAt(TabSTT2.getI(0, "aic_stammtyp"), 0);
            CboStt.setSelectedAIC(0);
            LadeHierachie(false);
          }
          if (iSttHS==0)
          {
            /*if (TabSTT2 != null && TabSTT2.size()==1)
            {
              iSttHS=TabGruppenFilter.getI(0,"int2");
              g.testInfo("Setze Stammtyp auf " + g.TabStammtypen.getBezeichnungS(iSttHS));
            }*/
            /*if (TabSTT2 != null)
            {
              int iSttO = TabSTT2.getI(0, "aic_stammtyp");
              int iPosO = g.TabStammtypen.getPos("Aic", iSttO);
              int iPosS = g.getPosFirstStt(iSttO);
              if (iPosS >= 0 && iPosO != iPosS)
              {
                iSttHS = g.TabStammtypen.getI(iPosS, "Aic");
                g.testInfo("Ändere Stammtyp von " + g.TabStammtypen.getBezeichnungS(iSttO) + " auf " + g.TabStammtypen.getBezeichnungS(iSttHS));
                checkCboStt();
              }
            }*/
            if (VecStt!=null && (VecStt.size()>1 || TabGruppenFilter.posInhalt("int1",VecStt.elementAt(0))))
                CheckOldGF();
          }
//          Vector<Object> Vec=new Vector<Object>();
          int iAF_Art=Abfrage.cstHS_Filter;
          if (g.Def())
          {
        	  if ((Qry.getI("bits")&WEB)>0 || CbxWeb!=null && CbxWeb.isSelected())
        		iAF_Art=Abfrage.cstAbfrage;
          }
          //g.fixInfo("TabGruppenFilter-Zeilen:"+TabGruppenFilter.size());
          CboEbene.Clear();
          int iSTT_Mom2=iSTT_Mom;
          if (riHS>0 && TabGruppenFilter.size()==0 && !Qry.eof() && !Qry.isNull("Bezeichnung"))
        	  new Message(Message.WARNING_MESSAGE,null,g).showDialog("HS_leer",new String[] {Qry.getS("Bezeichnung")});
          for(TabGruppenFilter.moveFirst();!TabGruppenFilter.eof();TabGruppenFilter.moveNext())
          {
            if (!TabGruppenFilter.isNull("aic_abfrage"))
              TabGruppenFilter.setInhalt("aic_abfrage",new ShowAbfrage(g,TabGruppenFilter.getI("aic_abfrage"),iAF_Art));
            if (TabGruppenFilter.getI("int4")==GruppenFilter.EIN || TabGruppenFilter.getI("int4")==GruppenFilter.BEW || TabGruppenFilter.getI("int4")==GruppenFilter.BEWBEW
                || TabGruppenFilter.getI("int4")==GruppenFilter.MF || TabGruppenFilter.getI("int4")==GruppenFilter.SUB)
            {
            	int iStt=TabGruppenFilter.getI("int2");
            	if (iStt>0 || TabGruppenFilter.getI("int4")>3)
            	{
//            		Vec.addElement(TabGruppenFilter.getInhalt("int2"));      
            		if (iStt>0)
            			CboEbene.addItem(g.TabStammtypen.getBezeichnungS(iStt), iStt, true);
            		else
            			CboEbene.addItem(g.TabErfassungstypen.getBezeichnungS(-iStt), iStt, true);
//            		g.fixtestError("int2 dazu2:"+TabGruppenFilter.getI("int2"));
            	}
            	else
            	{
            		int iSttR=g.RolleToStt(-TabGruppenFilter.getI("int2"));
            		if (iSttR==iSTT_Mom)
            			iSTT_Mom2=iStt;
//            		Vec.addElement(iSttR);
            		CboEbene.addItem(g.TabRollen.getBezeichnungS(-iStt), iStt, true);
//            		g.fixtestError("int2 dazu2: Rolle umgewandelt von "+ TabGruppenFilter.getI("int2")+" auf "+iSttR);
            	}
            }
            //g.fixInfo("Zeile "+(TabGruppenFilter.getPos()+1)+":"+TabGruppenFilter.getI("int4")+"/"+TabGruppenFilter.getI("int2"));
          }
          //if (g.bInfoGF)
          //  new Tabellenspeicher(g,TabGruppenFilter).showGrid("TabGruppenFilter-LoadHS");

          //g.progInfo("Vec="+Vec);
          int iEbeneV=CboEbene.getSelectedAIC();
//          g.fixtestError("fill2 CboEbene mit "+Vec+"/"+iSTT_Mom);
//          CboEbene.fillStt(Vec,iSTT_Mom);
          CboEbene.Sort();
          CboEbene.setSelectedAIC(iSTT_Mom2);
          //g.fixInfo("Ebene:"+iEbeneV+" -> "+CboEbene.getSelectedAIC()+" (soll="+iSTT_Mom+")");
          if (bBits)
            if(riHS>0)
            {
              int iBits = Qry.getI("bits");
              //g.progInfo("Load Bits=" + Integer.toBinaryString(iBits)+ ", Stt="+iSttHS);
              if(MnuStt != null)
                MnuStt.setSelected((iBits & STT) > 0);
              bStammtypen = (iBits & STT) > 0;
              iVL=iBits & VL;
              if (MnuAlles != null)
                MnuAlles.setSelected(iVL==ALLES);
              if(MnuKeineLeere != null)
                MnuKeineLeere.setSelected(iVL==VOLLE);
              bOffen = (iBits & OFFEN) > 0;
              bAustritt=(iBits & AUSTRITT) > 0;
              if(MnuOffen != null)
                MnuOffen.setSelected(bOffen);
//              bSort= (iBits & SORT) > 0;
//              if(MnuSort != null)
//                MnuSort.setSelected(bSort);
              if(MnuLeere != null)
                MnuLeere.setSelected(iVL==LEERE);
              if(MnuBeide != null)
                MnuBeide.setSelected(iVL==BEIDE);
            }
            else
            {
              //if(MnuKeineLeere != null)
              //  MnuKeineLeere.setSelected(false);
              iVL=ALLES;
              if(MnuAlles != null)
                MnuAlles.setSelected(true);
              if(MnuOffen != null)
                MnuOffen.setSelected(false);
//              if(MnuSort != null)
//                MnuSort.setSelected(false);
              bOffen = false;
//              bSort = false;
            }
          Qry.free();
        }

	private void LadeParameter(boolean bDefault)
	{
          g.progInfo("..LadeParameter "+bDefault);
		int iPos = g.TabBegriffe.getPos("Aic",iBegriff);
		Parameter p = new Parameter(g,g.TabBegriffe.getS(iPos,"Kennung"));

                //if (g.Prog())
                //  TabAbfrage2.showGrid();
		//for(TabAbfrage2.moveFirst();!TabAbfrage2.eof();TabAbfrage2.moveNext())
		//	TabAbfrage2.setInhalt("int4",new Abfrage(g,TabAbfrage2.getI("int2"),Abfrage.cstHS_Filter));
                //g.progInfo("LadeParameter");

		p.getMParameter("Spaltendef",bDefault);
                if (!p.bGefunden && !bDefault)
                  iHS=SQL.getInteger(g,"Select aic_hauptschirm from hauptschirm where aic_begriff="+iBegriff+" and"+g.bit("bits",STD)+" and (aic_benutzer is null or aic_benutzer="+g.getBenutzer()+" or "+g.bit("bits",JEDER)+")");
                if (iHS>0)
                {
                  iHSold=iHS;
                  //CboHS.setSelectedAIC(iHS);
                  LoadHS(iHS, true);
                }
                else
                {
                  int iVer = p.int2 & 7;
                  if(iVer == 3)
                  {
                    iHSold = p.int1;
                    iBitsOld = p.int2;
                    int iBitsHS=SQL.getInteger(g, "select bits from hauptschirm where aic_hauptschirm="+iHSold);
//                    g.fixtestError("HS="+iHSold+" bei iBits="+iBitsOld);
                    if ((iBitsHS&WEB)>0)
                    {
                    	g.fixtestError("Web-HS "+iHSold+" ist nicht erlaubt, stelle deshalb auf HS=0");
                    	iHSold=0;
                    }
                    iStamm_Start = p.int3;
                    iFormOld = p.int4;
                    //bAutoDarstellen = (iBitsOld & AUTO) > 0;
                    bStammtypen = (iBitsOld & STT) > 0;
                    //bNurOrdner = (iBitsOld & ORDNER) > 0;
                    iVL=iBitsOld & VL;
                    bOffen = (iBitsOld & OFFEN) > 0;
//                    bSort = (iBitsOld & SORT) > 0;
                    bDel = (iBitsOld & DEL) > 0;
                    bAustritt = (iBitsOld & AUSTRITT) > 0;
                    bBeginn = (iBitsOld & BEGINN) > 0;
                    bSpalte1 = (iBitsOld & SPALTE1) > 0;
                    bTooltip = (iBitsOld & TOOLTIP) > 0;
                    //CbxAnzeigen.setSelected((iBitsOld & ANZEIGE) > 0);
                    //g.progInfo("iHSold="+iHSold);
                    LoadHS(iHSold, false);
                  }
                  else
                  {
                    //iAbfrageOld = p.int1;
                    Abf = new ShowAbfrage(g,/* g.bAutoInit ? 0 : */p.int1, Abfrage.cstHS_Spalte);

                    if(iVer < 2) {
                      iStamm_Start = iVer > 0 ? p.int3 : g.getBenutzer();
                      p.getParameter("Cbx", true, false);
                      //bAutoDarstellen = p.int1 == 1;
                      bStammtypen = p.int2 == 1 || MnuStt != null && MnuStt.isSelected();
                      //bNurOrdner = p.int3 == 1;
                    }
                    else {
                      iBitsOld = p.int2;
                      iStamm_Start = p.int3;
                      iFormOld = p.int4;
                      //bAutoDarstellen = (iBitsOld & AUTO) > 0;
                      bStammtypen = (iBitsOld & STT) > 0;
                      //bNurOrdner = (iBitsOld & ORDNER) > 0;
                      iVL=iBitsOld & VL;
                      bOffen = (iBitsOld & OFFEN) > 0;
//                      bSort = (iBitsOld & SORT) > 0;
                      bDel = (iBitsOld & DEL) > 0;
                      bAustritt = (iBitsOld & AUSTRITT) > 0;
                    }
                    /*TabGruppenFilter = p.getMTab("GruppenFilter", bDefault);
                    Tabellenspeicher TabAbfrage2 = p.getMTab("Abfrage", bDefault);
                    for(TabGruppenFilter.moveFirst(); !TabGruppenFilter.eof(); TabGruppenFilter.moveNext())
                      TabGruppenFilter.setInhalt("aic_abfrage", TabAbfrage2.posInhalt("int1", TabGruppenFilter.getI("int2")) ?
                                                 new ShowAbfrage(g, TabAbfrage2.getI("int2"), Abfrage.cstHS_Filter) : null);
                     */
                    if (TabGruppenFilter==null)
                    	TabGruppenFilter=new Tabellenspeicher(g,new String[] {"int1","int2","int3","int4","aic_abfrage","aic_ansicht","ans_aic_ansicht"});
//                    g.progInfo("                      !!!! clearAll bei LadeParameter");
                  }
                  /*for (TabAbfrage2.moveFirst();!TabAbfrage2.eof();TabAbfrage2.moveNext())
                    if (TabGruppenFilter.posInhalt("int2",TabAbfrage2.getI("int1")))
                      TabGruppenFilter.setInhalt("aic_abfrage",new ShowAbfrage(g,TabAbfrage2.getI("int2"),Abfrage.cstHS_Filter));*/
                }
                g.progInfo("iFormOld="+iFormOld);
		//CbxGeloeschte.setSelected(p.int3 == 1);
                if (iStamm_Start>0)
                {
                  int iStt=SQL.getInteger(g,"select aic_stammtyp from stamm where aic_stamm=?",0,""+iStamm_Start);
                  int iStamm=g.getSyncStamm(iStt,iRolle);
                  g.progInfo("HS: Stt="+iStt+", Stamm="+iStamm+" statt"+iStamm_Start);
                  if (iStamm>0)
                    iStamm_Start=iStamm;
                  else
                    g.setSyncStamm(iStt,iStamm_Start,iRolle);
                }
		iStamm_Mom = iStamm_Start;
                bStamm=iStamm_Mom != 0;
                iHS=iHSold;
                CboHS.setSelectedAIC(iHS);
		//g.printInfo("iStamm_Start:"+iStamm_Start);
		//p.getBParameter("Elemente",VecElemente,true);

                if (g.bInfoGF)
                  new Tabellenspeicher(g,TabGruppenFilter).showGrid("TabGruppenFilter-LadeParameter",self);
		p.free();
	}

	private void InitParameter()
	{
          g.progInfo("..InitParameter");
		//VecElemente.removeAllElements();
		//TabAbfrage2.clearAll();
                iHS=SQL.getInteger(g,"Select aic_hauptschirm from hauptschirm where aic_begriff="+iBegriff+" and"+g.bit("bits",STD)+" and (aic_benutzer is null or aic_benutzer="+g.getBenutzer()+" or "+g.bit("bits",JEDER)+")");
                if (TabRbt != null)
                {
                  int iPos=TabRbt.getPos("Aic",iHS);
                  if (iPos>=0)
                    ((JRadioButtonMenuItem)TabRbt.getInhalt("Rbt",iPos)).setSelected(true);
                }
                if (iHS>0)
                  LoadHS(iHS,true);
                else
                {
                  g.progInfo("                      !!!! clearAll bei InitParameter");
                  TabGruppenFilter.clearAll();
                  Abf=new ShowAbfrage(g,0,Abfrage.cstHS_Spalte);
                  TabStyle.clearAll();
                  //if (NodStt != null)
                  //  NodStt.removeChildren();
                  LadeParameter(true);
                }
                iVL=ALLES;
                bNodStammGeladen = false;
                //g.progInfo("InitParameter:bStammtypen="+bStammtypen);
                Refresh_HS(iHS);
		//RefreshGrid();
                if (g.bInfoGF)
                  new Tabellenspeicher(g,TabGruppenFilter).showGrid("Gruppenfilter-InitParameter",self);
	}

        private void fillCboHS()
        {
        	boolean bAll=CbxAll2==null || CbxAll2.isSelected();
        	boolean bWeb=CbxWeb2!=null && CbxWeb2.isSelected();
          TabHS=new Tabellenspeicher(g,"SELECT aic_Hauptschirm aic,kennung,"+g.AU_BezeichnungF("Hauptschirm")+" DefBez"+g.AU_Bezeichnung("Hauptschirm")+g.AU_Memo("Hauptschirm")+g.AU_Tooltip("Hauptschirm","Hauptschirm")+
                                     ",bits,aic_benutzergruppe,aic_benutzer,AIC_STAMMTYP FROM Hauptschirm where aic_begriff="+iBegriff+
                                     (bAll && bWeb ? "":bWeb ? " and"+g.bit("bits",WEB):" and "+g.not("bits", WEB))+" and (aic_benutzer="+g.getBenutzer()+" or"+g.bit("bits",JEDER)+
                                     (g.Def() ?" or kennung is not null and kennung<>'*')":" or aic_benutzer is null and aic_benutzergruppe is null or aic_benutzergruppe"+Static.SQL_in(g.VecBG)+" or aic_hauptschirm"+Static.SQL_in(g.VecHS)+") and not"+g.bit("bits",WEB)),true);
          int iAnz=0;
//          g.fixtestError("TabHS:"+TabHS.getSQL());
          for(TabHS.moveFirst();!TabHS.out() && TabHS.getPos()<TabHS.size();)
          {
            //g.fixtestInfo("HS-Pos:"+TabHS.getPos()+" von "+TabHS.size());
            if (TabHS.getS("DefBez").equals("") && TabHS.getS("Bezeichnung").equals("") || !TabHS.getS("Kennung").equals("") && TabHS.isNull("aic_benutzer") && !g.existsHS(TabHS.getI("aic")))
              TabHS.clearInhalt(TabHS.getPos());
            else
            {
              iAnz++;
              //g.fixtestInfo("HS"+iAnz+":"+TabHS.getS("Bezeichnung")+"/"+TabHS.getS("Kennung"));
              TabHS.moveNext();
            }
          }
          g.setAnzahl(BtnEinstellung,iAnz);
          //CboHS.fillHS(iBegriff);
          CboHS.fillCbo(TabHS,true);
//          TabHS.showGrid("HS");
        }

        /*private void FillEinstellung()
        {
          MnuAuswahl.removeAll();
          int iAnz=0;
          for(TabHS.moveFirst();!TabHS.eof();TabHS.moveNext())
            if (!TabHS.getS("Bezeichnung").equals(""))
          {
            if ((TabHS.getI("bits")&AUSWAHL)>0)
              iAnz++;
            JMenuItem MnuES = new JMenuItem(TabHS.getS("Bezeichnung"));
            MnuES.setFont(g.fontPopup);
            //if (!TabHS.isNull("Memo"))
            //  MnuES.setToolTipText(TabHS.getM("Memo"));
            g.setTooltip(TabHS.getM("TT"),MnuES);
            MnuES.setActionCommand(""+TabHS.getI("Aic"));
            MnuES.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
        	Refresh_HS(Sort.geti(ev.getActionCommand()));
        	//findRbt();
              }
            });

            MnuAuswahl.add(MnuES);
          }
          g.setAnzahl(BtnEinstellung,iAnz);
          //BtnEinstellung.setIconTextGap(1);
          //BtnEinstellung.setFont(g.fontStandard);
          //BtnEinstellung.setText("<html><sup><font color=\"#FF0000 \">"+iAnz+"</font></sup></html>");
        }*/

        /*private void findRbt()
        {
          if (TabRbt!=null)
            for(TabRbt.moveFirst();!TabRbt.eof();TabRbt.moveNext())
              if (iHS==TabRbt.getI("Aic") && !((JRadioButton)TabRbt.getInhalt("Rbt")).isSelected())
        	((JRadioButton)TabRbt.getInhalt("Rbt")).setSelected(true);
              else if (iHS!=TabRbt.getI("Aic") && ((JRadioButton)TabRbt.getInhalt("Rbt")).isSelected())
              {
        	JRadioButton Rad=(JRadioButton)TabRbt.getInhalt("Rbt");
        	RadGroup.remove(Rad);
        	Rad.setSelected(false);
        	RadGroup.add(Rad);
              }
        }*/

        private void Refresh_HS(int riHS)
        {
          g.progInfo("..Refresh_HS "+riHS);
          iHS=riHS;
          //if (DlgFilter != null && DlgFilter.isVisible())
            //CboHS.setSelectedAIC(iHS);
          //else
            //LoadHS(iHS,true); !!! entfernt 12.7.2013
            CboHS.setSelectedAIC(iHS);
          //DlgFilter.setVisible(false);
          bNodStammGeladen = false;
          RefreshGrid();
        }

        private void FillCboFormular2()
	{
          //g.progInfo("FillCboFormular2:"+iForm);
          int iForm2=g.getForm(iSTT_Mom);
          if (iForm2>0)
          {
            iForm = iForm2;
            MnuLastForm.setText(g.TabBegriffe.getBezeichnungS(iForm));
            g.setTooltip(g.TabBegriffe.getS(iForm,"Tooltip"),MnuLastForm);
            //MnuLastForm.setToolTipText(g.TabBegriffe.getInfoS(iForm,"Info"));
          }
          MnuBearbeiten.removeAll();
          MnuEdit2.removeAll();
          MnuEdit2.setVisible(false);
          MnuEdit3.removeAll();
          MnuEdit3.setVisible(false);
          MnuLastForm.setVisible(iForm2>0);
          if (bStdForm)
          {
            JMenuItem MnuEF = new JMenuItem(g.TabStammtypen.getBezeichnungS(iSTT_Mom));
            MnuBearbeiten.add(MnuEF);
            MnuEF.setFont(g.fontStandard);
            MnuEF.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                DetailAufruf(true, 0,0);
              }
            });
          }
          else if (iSTT_Mom<0 && bStamm || iSTT_Mom>0)
          {
            if(!TabAktFormular.posInhalt("Stt",iSTT_Mom))
            {
              TabAktFormular.newLine();
              TabAktFormular.setInhalt("Stt",iSTT_Mom);
            }
            if (TabAktFormular.isNull("Tab"))
            {
              //g.fixtestInfo("!!! HS "+sApplikation+": Benutzer="+g.getBenutzer()+", Stt="+iSTT_Mom);
              String sVerlauf=/*Static.bVerlauf ? */",(select max(timestamp) from verlauf join logging on verlauf.aic_logging=logging.aic_logging and aic_benutzer="+g.getBenutzer()+
                  " where verlauf.aic_begriff=begriff.aic_begriff) last";//:"";
              String s="select formular.aic_begriff,reihenfolge,used"+sVerlauf+" from formular"+g.join("begriff","formular")+
                  " join begriff_zuordnung on begriff_zuordnung.aic_begriff=begriff.aic_begriff where beg_aic_begriff="+iBegriff+
                            (iSTT_Mom>0 ? " and aic_stammtyp="+iSTT_Mom:" and aic_bewegungstyp="+(-iSTT_Mom));
              //g.fixtestInfo(s);
              Tabellenspeicher Tab=new Tabellenspeicher(g,s,true);
              if (Tab.isEmpty())
                Tab=new Tabellenspeicher(g,"select aic_begriff,0 reihenfolge,used"+sVerlauf+" from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Frame")+
                  (iSTT_Mom>0 ? " and aic_stammtyp="+iSTT_Mom:" and aic_bewegungstyp="+(-iSTT_Mom))+" and "+g.bit("bits",Formular.cstStdFormular)+" and not "+g.bit("bits",Formular.cstMehrfach),true);
              TabAktFormular.setInhalt("Tab",Tab);
            }
            Tabellenspeicher Tab=(Tabellenspeicher)TabAktFormular.getInhalt("Tab");
            //MnuLastForm.setVisible(iForm>0 && Tab.getVecSpalte("aic_begriff").contains(new Integer(iForm)));

            /*if (MnuAnzahl.isSelected())
              Tab.sort("used",false);
            else*/
              Tab.sort("reihenfolge",true);
            int iReihe=0;
            int iAnzForm=0;
            boolean bSep=false;
            Vector VecRolle=iSTT_Mom!=iSttRolle ? null:g.getVecRolle(iRolle);
            //g.progInfo("VecRolle="+VecRolle);
            TabForm.clearAll();
            for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
            {
              /*if (MnuAnzahl.isSelected())
              {
                if (iReihe==0 && Tab.getI("Used")<10 || iReihe<2 && Tab.getI("Used")==0)
                {
                  iReihe = Tab.getI("Used")==0 ? 2:1;
                  if (iAnzForm % 38==0)
                    ;
                  else if (iAnzForm<39)
                    MnuBearbeiten.addSeparator();
                  else if(iAnzForm > 76)
                    MnuEdit3.addSeparator();
                  else
                    MnuEdit2.addSeparator();
                }
              }
              else*/ if (bSep && iReihe+1<Tab.getI("Reihenfolge"))
              {
                      bSep=false;
                      iReihe=Tab.getI("Reihenfolge");
                      if (iAnzForm<39)
                        MnuBearbeiten.addSeparator();
                      else if (iAnzForm>76)
                        MnuEdit3.addSeparator();
                      else
                        MnuEdit2.addSeparator();

              }
              //g.TabBegriffe.posInhalt("Aic",Tab.getI("Aic_Begriff"));
              //g.progInfo("FillCboFormular2:"+Tab.getI("Aic_Begriff")+"/"+bStammtypen+"/"+g.TabBegriffe.getI("Erf")+"/"+iSTT_Mom+"/"+iSttRolle);
              int iPos=g.TabBegriffe.getPos("Aic",Tab.getI("Aic_Begriff"));
              if (iPos>=0 && (!bStammtypen || g.TabBegriffe.getI(iPos,"Erf")==0) /*&& g.Lizenz()*/ && g.BerechtigungS(iPos)
                  && (iSTT_Mom!=iSttRolle || g.TabBegriffe.getI(iPos,"Rolle")==0 || VecRolle.contains(g.TabBegriffe.getInhalt("Rolle",iPos)))
                  /*&& (!bStammtypen || bNurOrdner && bStamm || (g.TabBegriffe.getL(iPos,"bits")&Formular.cstNurOrdner)==0)*/)
              {
                bSep=true;
                //g.progInfo("schon "+g.TabBegriffe.getS("Bezeichnung"));
                String sBez=g.getBegBez3(iPos);
                JMenuItem MnuEF = new JMenuItem(sBez);
                iAnzForm++;
                if (iAnzForm<39)
                  MnuBearbeiten.add(MnuEF);
                else if (iAnzForm>76)
                  MnuEdit3.add(MnuEF);
                else
                  MnuEdit2.add(MnuEF);
                if (iAnzForm==39)
                  MnuEdit2.setVisible(true);
                else if (iAnzForm==77)
                  MnuEdit3.setVisible(true);
                MnuEF.setFont(g.fontPopup);
                String sTooltip=Static.sLeer;
                if (g.TabBegriffe.getInhalt("Tooltip",iPos) != null)
                {
                  sTooltip=g.TabBegriffe.getS(iPos, "Tooltip");
                  g.setTooltip((g.Def() ? Tab.getI("used") + "x:<p>" : "") + sTooltip, MnuEF);
                }
                MnuEF.setActionCommand(""+Tab.getI("Aic_Begriff"));
                if (iForm==Tab.getI("Aic_Begriff"))
                  MnuLastForm.setVisible(true);
                MnuEF.addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    DetailAufruf(true, Sort.geti(ev.getActionCommand()),0);
                  }
                });
                TabForm.addInhalt("Aic",Tab.getI("Aic_Begriff"));
                TabForm.addInhalt("Nr",iAnzForm);
                TabForm.addInhalt("Bezeichnung",sBez);
                TabForm.addInhalt("Anzahl",Tab.getI("used"));
                TabForm.addInhalt("last",Tab.exists("last") ? Tab.getInhalt("last"):null);
                TabForm.addInhalt("Memo",sTooltip);
                TabForm.addInhalt("Reihenfolge",Tab.getInhalt("Reihenfolge"));
              }
              //else
              //  g.progInfo("nicht "+g.TabBegriffe.getS("Bezeichnung"));
              //if (!MnuAnzahl.isSelected())
                iReihe=Tab.getI("Reihenfolge");
            }
          }
        }

	@SuppressWarnings("unchecked")
	private void RefreshGrid()
	{
          long lClock = Static.get_ms();
          if (DlgSuche!=null)
          {
            DlgSuche.dispose();
            DlgSuche=null;
          }
		//g.progInfo(".RefreshGrid");
		bGidChange=false;
		//VecEigenschaften = new Vector();
		//JCOutlinerFolderNode NodSpalten = Abfrage.findNode(NodSpaltendef,false);
		//if (Abf != null)
		//{
			//TabSpalten = Abf.getAnzeigeSpalten(VecEigenschaften);
			//Abf.SQL_String();
			TabSpalten = Abf.getSpalten();
			VecEigenschaften=Abf.VecEigenschaften;
			if (VecEigenschaften==null)
				VecEigenschaften = new Vector<Object>();
			sSortiert=Abf.Sortiert();
		/*}
		else
			sSortiert="";*/
		//g.progInfo("sSortiert="+sSortiert);

		//if (g.Debug())
		//	TabSpalten.showGrid();
		//g.debugInfo("VecEigenschaften="+VecEigenschaften);
                if (bStammtypen)
                {
                  Gid.setColumnButtons(new jclass.util.JCVector(new String[] {g.getBezeichnung("Tabellenname","STAMMTYP"),g.getBegriffS("Show","Anzahl")}));
                  Gid.setNumColumns(2);
                  Gid.setColumnWidth(0,250);
                  //Gid.setColumnWidth(1,50);
                  Gid.setColumnAlignment(1,BWTEnum.MIDDLERIGHT);
                }
                else
                {
                  Vector VecGidSpalten;
                  if(TabSpalten == null || TabSpalten.isEmpty() || sSortiert.equals("")) {
                    sSortiert = " order by bezeichnung";
                  }
                  /*  VecGidSpalten = new Vector();
                    VecGidSpalten.addElement(g.getBegriff("Show", "Bezeichnung"));
                    //g.progInfo("Bezeichnung hinzugefügt");
                  }
                  else {*/
                    Abf.checkHS_Bezeichnung();
                    if (TabSpalten==null)
                      TabSpalten = Abf.getSpalten();
                    VecGidSpalten = Abf.getBezeichnung();
                    //Gid.setColumnAlignments(Abf.getAusrichtung());	19.8.2003
                  //}
                  Abf.setOutlinerButtons(0, TabSpalten, Gid, VecGidSpalten);
                  //for (int i=0;i<VecGidSpalten.size();i++)
                  //  g.progInfo("Spalte "+i+":"+VecGidSpalten.elementAt(i)+"="+Gid.getColumnWidth(i));;
                }
		/*if (TabSpalten == null || TabSpalten.isEmpty())
			VecGidSpalten.addElement(g.getBegriff("Show","Bezeichnung"));
		int i=-1;
		///bMitPeriode=false;
		if (TabSpalten != null)
		{
			TabSpalten.moveFirst();
			while(!TabSpalten.eof())
			{
				//String sDatentyp = TabSpalten.getS("Datentyp");
				boolean bPeriode=(TabSpalten.getI("bits")&Abfrage.cstPeriode)>0  && Zeitraum.VecPerioden!=null;
				boolean bGruppe=TabSpalten.getInhalt("Gruppe")!=null;
				int iAnz=bGruppe ? ((Vector)TabSpalten.getInhalt("Gruppe")).size(): bPeriode ? Zeitraum.VecPerioden.size()-1:1;
				for(int iPer=0;iPer<iAnz;iPer++)
				{
					///bMitPeriode=true;
					VecGidSpalten.addElement(TabSpalten.getS("Bezeichnung")+(bGruppe ? " "+((Vector)TabSpalten.getInhalt("Gruppe")).elementAt(iPer)
						:bPeriode ?" "+Static.FormatTS(Zeitraum.VecPerioden.elementAt(iPer)):Static.sLeer));
					i++;
					Gid.setColumnAlignment(i,g.getAlignment(TabSpalten.getS("Datentyp"),TabSpalten.getI("Ausrichtung")));
				}
				TabSpalten.moveNext();
			}
		}*/

		//Gid.setColumnButtons(VecGidSpalten);		19.8.2003
		//Gid.setNumColumns(VecGidSpalten.size());	19.8.2003

		/*
		Vector VecGidSpalten = new Vector();
		VecGidSpalten.addElement(g.getBegriff("Show","Bezeichnung"));
		//VecGidSpalten.addElement(g.TabShow.getBezeichnung("Kennung"));
		//TabSpalten.moveFirst();
		int iSpalten=1;
		for(int i=0;i<VecEigenschaften.size();i++)
		{
			if (g.TabEigenschaften.posInhalt("AIC",VecEigenschaften.elementAt(i)))
			{
				VecGidSpalten.addElement(g.TabEigenschaften.getS("Bezeichnung"));
				iSpalten++;
			}
			//TabSpalten.moveNext();
		}
		Gid.setColumnButtons(VecGidSpalten);
		Gid.setNumColumns(iSpalten);*/

		bNodStammGeladen = false;
		//g.progInfo("LadeHierachie");
		if (bStammtypen)
                  LadeHierachie(true);
                //g.clockInfo("1.davor         ",lClock);
		GidDarstellung();
                //g.clockInfo("2.GidDarstellung",lClock);
		bGidChange=true;
		Gid_itemStateChanged();
                //g.clockInfo("3.Gid_itemState ",lClock);
                if (bStammtypen && isVisible())
                  ErmittleSttAnzahl();
//                else if (bSort)
//                  Gid.sortByColumn(0,Sort.sortMethod);
                //g.clockInfo("4.sortByColumn  ",lClock);
                g.clockInfo("RefreshGrid-"+CboHS.getSelectedBezeichnung()+"->"+(TabGruppenFilter.size()==0 ? "leer":TabGruppenFilter.getI(0,"AIC_ANSICHT")<0?"neu":"ok"),lClock);
		//g.progInfo("RefreshGrid-Ende:"+iStamm_Mom+"/"+iSTT_Mom);
	}//RefreshGrid

        private void ErmittleSttAnzahl()
        {
//        	g.fixtestError("ErmittleSttAnzahl:"+TabGruppenFilter.getVecSpalte("int2"));
          //long lClock=Static.get_ms();
          //g.progInfo("ErmittleAnzahl");
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select stt.aic_stammtyp,(select count(*) from stammview where aic_stammtyp=stt.aic_stammtyp and aic_mandant="+g.getMandant()+
              (iRolle>0?" and (aic_stammtyp<>"+iSttRolle+" or aic_rolle="+iRolle+")":"")+
              ") Anzahl from stammtyp stt where stt.aic_stammtyp"+Static.SQL_in(TabGruppenFilter.getVecSpalte("int2")),true);
          //g.clockInfo("ErmittleAnzahl-Stt ",lClock);
          Vector Vec2=Static.getMinus(TabGruppenFilter.getVecSpalte("int2"));
          if (Vec2.size()>0)
          {
            Tabellenspeicher Tab2=new Tabellenspeicher(g,"select aic_bewegungstyp,(select count(*) from bewview where aic_bewegungstyp=bew.aic_bewegungstyp and aic_mandant="+g.getMandant()+
                ") Anzahl from bewegungstyp bew where aic_bewegungstyp"+Static.SQL_in(Vec2),true);
            //Tab2.showGrid();
            for(Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
            {
              Tab.addInhalt("aic_stammtyp",-Tab2.getI("aic_bewegungstyp"));
              Tab.addInhalt("Anzahl",Tab2.getI("Anzahl"));
            }
          }
          //Tab.showGrid("Anzahl");
          //g.clockInfo("ErmittleAnzahl-Bew ",lClock);
          NodStt.removeChildren();
          Tabellenspeicher Tab3=new Tabellenspeicher(g,new String[]{"Aic","Knoten"});
          JCOutlinerFolderNode NodNeu=NodStt;
          for(TabGruppenFilter.moveFirst();!TabGruppenFilter.eof();TabGruppenFilter.moveNext())
          {
            int iStt=TabGruppenFilter.getI("int2");
            if (TabGruppenFilter.getI("int4")!=GruppenFilter.AUS)
              NodNeu=fillStyle(TabGruppenFilter.getPos()==0 || TabGruppenFilter.getI("ans_aic_ansicht")==0 ? NodStt:Tab3.posInhalt("Aic",TabGruppenFilter.getI("ans_aic_ansicht")) ?
                               (JCOutlinerFolderNode)Tab3.getInhalt("Knoten"):null,iStt,Tab.posInhalt("aic_stammtyp",iStt)?Tab.getI("Anzahl"):0);
            Tab3.addInhalt("Aic",TabGruppenFilter.getI("aic_ansicht"));
            Tab3.addInhalt("Knoten",NodNeu);
          }
          //g.clockInfo("ErmittleAnzahl-Rest",lClock);
          /*
          for(JCOutlinerNode Node=Gid.getNextNode(NodStt);Node!=null;Node=Gid.getNextNode(Node))
          {
            int iStt=((Integer)(((Vector)Node.getUserData()).elementAt(1))).intValue();
            //g.progInfo(Node+":"+iStt);
            if (Tab.posInhalt("aic_stammtyp",iStt))
              ((Vector)Node.getLabel()).setElementAt(1,new Integer(Tab.getI("Anzahl")));
          }*/
          Gid.folderChanged(NodStt);
          //g.clockInfo("ErmittleSttAnzahl",lClock);
        }

	private void GidDarstellung()
	{
		//g.progInfo(".GidDarstellung");
		if (!bStammtypen && !bNodStammGeladen)
			LadeHierachie(true);
		//Gid.setRootNode(new JCOutlinerNode(""));
		Gid.setNodeHeight(iFF*(bStammtypen?30:16)/100);
		Gid.setRootNode(bStammtypen?NodStt:NodStamm);
	}

	private void Events()
	{

        BtnEinstellung.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Einstellungsauswahl(ev);//FilterAny(BtnEinstellung.getLocationOnScreen());
			}
		});

        BtnSuche.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                suchen(BtnSuche.getLocationOnScreen());
            }
		});

        BtnSpotlight.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent ev)
                {
                  Spotlightdialog(BtnSpotlight.getLocationOnScreen());
                }
        });

		if ((iEnabled&1)>0)
			BtnDruckHS.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
                                  openDF(bChange?0:-Abf.iBegriff);
				}
			});

		/*BtnTable.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				ShowTable();
			}
		});*/

		BtnFilter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				if (TabGruppenFilter.posInhalt("int2",CboEbene.getSelectedAIC()))
				{
					if(TabGruppenFilter.getInhalt("aic_abfrage")==null)
						TabGruppenFilter.setInhalt("aic_abfrage",new ShowAbfrage(g,0,Abfrage.cstHS_Filter));
					All_Unlimited.Grunddefinition.DefAbfrage.get(g,(ShowAbfrage)TabGruppenFilter.getInhalt("aic_abfrage"),CboEbene.getSelectedAIC()).show();
                    DlgFilter.getRootPane().setDefaultButton(BtnRefresh2);
				}
				else
					g.printError("BtnFilter.addActionListener: Stammtyp "+iSTT_Mom+" in TabAbfrage nicht gefunden!",iBegriff);
			}
		});
		
		if (BtnAbfrage != null)
		  BtnAbfrage.addActionListener(new ActionListener()
		  {
			public void actionPerformed(ActionEvent ev)
			{
				int iStt=CboEbene.getSelectedAIC();
				if (TabGruppenFilter.posInhalt("int2",iStt))
				{
					ShowAbfrage Abf2=(ShowAbfrage)TabGruppenFilter.getInhalt("aic_abfrage");
					if(Abf2==null)
					{
						Abf2=new ShowAbfrage(g,0,Abfrage.cstAbfrage);
						TabGruppenFilter.setInhalt("aic_abfrage",Abf2);
					}
					if (iStt<0 && TabGruppenFilter.getI("int4")<4)
					{
						Abf2.iRolle=-iStt;
						iStt=g.RolleToStt(-iStt);
					}
					All_Unlimited.Grunddefinition.DefAbfrage.get(g,Abf2,iStt,null,false,-1).show();
//                    DlgFilter.getRootPane().setDefaultButton(BtnRefresh2);
				}
				else
					g.printError("BtnAbfrage.addActionListener: Stammtyp "+iSTT_Mom+" in TabAbfrage nicht gefunden!",iBegriff);
			}
		  });

		BtnSpaltenDef.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                          int iSttHaupt=TabGruppenFilter.posInhalt("int2",new Integer(iSTT_Mom)) ? TabGruppenFilter.getI("int1"):0;
                          TabGruppenFilter.posInhalt("int1",iSttHaupt);
                          Vector<Object> Vec=new Vector<Object>();
                          for (;!TabGruppenFilter.out() && TabGruppenFilter.getI("int1")==iSttHaupt;TabGruppenFilter.moveNext())
                            if (TabGruppenFilter.getI("int4")==GruppenFilter.EIN || TabGruppenFilter.getI("int4")==GruppenFilter.BEW || TabGruppenFilter.getI("int4")==GruppenFilter.BEWBEW
                                || TabGruppenFilter.getI("int4")==GruppenFilter.MF || TabGruppenFilter.getI("int4")==GruppenFilter.SUB)
				Vec.addElement(TabGruppenFilter.getInhalt("int2"));
                          //g.progInfo("VecStt="+Vec);
                          bChange=true;
                          if (Vec != null && Vec.size()>0)
                            All_Unlimited.Grunddefinition.DefAbfrage.get(g,Abf,CboEbene.getSelectedAIC(),Vec).show();
                          DlgFilter.getRootPane().setDefaultButton(BtnRefresh2);
				/*Abfrage fAbfrage=Abfrage.Zeigen(g,Formular.Stamm,iSTT_Mom,NodSpaltendef,self);
				if(fAbfrage != null && fAbfrage.isOkConfirm())
					RefreshGrid();*/
			}
		});

		if ((iEnabled&2)>0)
                {
                  BtnGruppenFilter.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                      Ansicht(true);
                    }
                  });
                  BtnAnsicht.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                      Ansicht(false);
                    }
                  });
                }

		/*BtnRefresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnRefresh.setEnabled(false);
				g.Refresh("");
				LadeHierachie();
				BtnRefresh.setEnabled(true);
			}
		});*/

		/*BtnInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnInfo.setEnabled(false);
				new Memo("SQL-Statements",g.getInfo(),this,g);
				//BtnInfo.setEnabled(true);
			}
		});*/


		/*BtnSpeichern.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnSpeichernClicked();
			}
		});

		BtnSchliessen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnSchliessenClicked();
			}
		});*/

                BtnMandant.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                popMandant.show((Component)ev.getSource(), 0, 24);
                        }
                });

                AuswahlErstellen();

                BtnSystem.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                popSystem.show((Component)ev.getSource(), 0, 24);
                        }
                });

                BtnHS.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                popHS.show((Component)ev.getSource(), 0, 24);
                        }
                });

		BtnInit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				InitParameter();
			}
		});

                BtnZRplus.addActionListener(new ActionListener()
                        {
                                public void actionPerformed(ActionEvent ev)
                                {
                                        changeZR(true);
                                }
                        });
                BtnZRminus.addActionListener(new ActionListener()
                        {
                                public void actionPerformed(ActionEvent ev)
                                {
                                        changeZR(false);
                                }
                        });


		//Zeitraum FomZeitraum=Zeitraum.get(g,true);
		//if g.(FomZR_Aic()

		//if (g.FomZeitraum==null)
		//	g.FomZeitraum=new Zeitraum(g);
		//g.progInfo("FomZeitraum="+FomZeitraum);

		BtnZeitraum.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Zeitraum.get(g).show();
			}
		});

                /*Action RefreshKeyAction = new AbstractAction() {
                  public void actionPerformed(ActionEvent e)
                  {
                    bNodStammGeladen = false;
                    RefreshGrid();
                  }
                };
                KeyStroke RefreshKeyStroke = KeyStroke.getKeyStroke((char)KeyEvent.VK_F12, false);
                InputMap inputMap = BtnRefresh.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
                ActionMap actionMap = BtnRefresh.getActionMap();
                if(inputMap != null && actionMap != null)
                {
                  inputMap.put(RefreshKeyStroke, "refresh");
                  actionMap.put("refresh", RefreshKeyAction);
                }*/

		BtnRefresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				bNodStammGeladen = false;
				RefreshGrid();
                                int iAnz=Gid.getNumColumns();
                                int iB[]=Gid.getHeader().getColumnWidths();
                                jclass.bwt.JCComponent[] labels = Gid.getHeader().getLabels();

                                for (int i=0;i<iAnz;i++)
                                  g.progInfo(i+".Spalte: "+Gid.getColumnWidth(i)+"/"+Gid.getColumnDisplayWidth(i)+"/"+iB[i]+"/"+labels[i]);
			}
		});

                BtnRefreshAll.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                bNodStammGeladen = false;
//                                g.fixtestError("vor allesSchliessen");
                                allesSchliessen(0);
//                                g.fixtestError("vor g.Refresh");
                                g.Refresh();
                                ShowAbfrage.refreshBerechtigung(g);
                                //Abfrage.free();
//                                g.fixtestError("vor readSpalten2");
                                Abfrage.readSpalten2(g,-2);
//                                g.fixtestError("fertig RefreshAll");
                                NodVorher=null;
                                bGidChange=true;
                                Gid_itemStateChanged();
                                /*int iBH=g.getBegriffAicS("Applikation",sApplikation);
                                int iPos=g.TabFormulare.getPos("Aic",iBH);
                                if (iPos>=0)
                                {
                                  Beenden(false);
                                  ((Hauptschirm)g.TabFormulare.getInhalt("Formular",iPos)).dispose();
                                  g.TabFormulare.clearInhalt(iPos);
                                  g.putTabFormulare(iBH,0,new Hauptschirm(sApplikation,g));
                                }*/
                                //RefreshGrid();
                        }
                });

                Action cancelKeyAction = new AbstractAction() {
                  /**
					 *
					 */
					private static final long serialVersionUID = -3465254972996464501L;

				public void actionPerformed(ActionEvent e)
                  {
                    Beenden(true);
                  }
                };
                /*Static.Escape(BtnBeenden,this,cancelKeyAction);

		BtnBeenden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Beenden();
			}
		});*/

		addWindowListener(new WindowListener()
		{
			public void windowClosed(WindowEvent e)
			{
			}
			public void windowOpened(WindowEvent e)
			{
			}
			public void windowClosing(WindowEvent e)
			{
                          Beenden(true);
			}
			public void windowIconified(WindowEvent e)
			{
			}
			public void windowDeiconified(WindowEvent e)
			{
			}
			public void windowActivated(WindowEvent e)
			{
                          int iStamm = 0;
                          if (iSTT_Mom>0)
                        	  iStamm=g.getSyncStamm(iSTT_Mom,iRolle);
//                          {
//                            int iPos=g.TabStammtypen.getPos("Aic", iSTT_Mom);
//                            if (iPos>=0)
//                              iStamm = g.TabStammtypen.getI(iPos,"Stamm");
//                          }
                          else
                        	  iStamm=g.getSyncBew(-iSTT_Mom);
//                          {
//                            int iPos=g.TabErfassungstypen.getPos("Aic", -iSTT_Mom);
//                            if (iPos>=0)
//                              iStamm = g.TabErfassungstypen.getI(iPos,"Pool");
//                          }
				//g.progInfo("HS: Activated: Stt="+iSTT_Mom+", Stamm="+iStamm);
				if(iStamm>0 && iStamm!=iStamm_Mom)
				{
					iStamm_Mom=iStamm;
                                        bStamm=true;
					//g.progInfo("HS: Activated Refresh");
					//RefreshGrid();
                                        NodStammMom=null;
                                        suche((JCOutlinerFolderNode)Gid.getRootNode(),null,0);
                                        Static.makeVisible(Gid,NodStammMom==null?Gid.getRootNode():NodStammMom,true);
				}
			}
			public void windowDeactivated(WindowEvent e)
			{
			}
		});

		/*CboTyp.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange() == ev.SELECTED)
				{
					FillCboFormular();
					EnableButtons();
				}
			}
		});*/

		/*CboFormular.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange() == ev.SELECTED)
				{
					if (CboFormular.getSelectedAIC()<=0)
					{
                                          if (BtnEditOk != null)
						BtnEditOk.setEnabled(CboFormular.getSelectedAIC()==0 && g.Def());
                                          if (Mmo != null)
						Mmo.setText("");
					}
					else
					{
						setToolTip();

						if (TabAktFormular.posInhalt("Stt",iSTT_Mom))
						{
							TabAktFormular.setInhalt("Formular",CboFormular.getSelectedAIC());
						}
						else
						{
							TabAktFormular.newLine();
                                                        TabAktFormular.setInhalt("Stt",iSTT_Mom);
							TabAktFormular.setInhalt("Formular",CboFormular.getSelectedAIC());
						}
						if (BtnEditOk != null)
						{
                                                        Enable2();
						}
					}
				}
			}
		});*/

		Gid.addItemListener(new JCOutlinerListener()
		{
			public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
			{

			}
			public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
                        {
//                          if (ev.getNewState() == BWTEnum.FOLDER_OPEN_ALL)
//                          {
//                            //g.progInfo("outlinerFolderStateChangeEnd:" + ev);
////                            if(bSort)
////                              Gid.sortByColumn(0, Sort.sortMethod);
//                          }
                        }

			public void outlinerNodeSelectBegin(JCOutlinerEvent ev)
			{
                          //g.progInfo("outlinerNodeSelectBegin:"+ev);
			}
			public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
                        {
                          //g.progInfo("outlinerNodeSelectEnd:"+ev);
                          g.setTooltip(bTooltip ? getInfo():null,Gid);
                        }

			public void itemStateChanged(JCItemEvent ev)
			{
                          //g.progInfo("itemStateChanged:"+ev);
				if(ev.getStateChange() == ItemEvent.SELECTED)
				{
					Gid_itemStateChanged();
				}
			}
		});

	}

        private String getInfo()
        {
          //boolean b=true;
          String s = "";
          JCOutlinerNode Nod=Gid.getSelectedNode();
          if (Nod==null || Nod.getLabel() instanceof String)
            return null;
          Vector Vec=(Vector)Nod.getLabel();
          String sLabels[]=Gid.getColumnLabels();
          for (int i = 0; i < Vec.size(); i++)
            //if ((TabSpalten.getI(i, "bits") & Abfrage.cstUnsichtbar) == 0)
            {
              Object sCell = Tabellenspeicher.getm(Vec.elementAt(i));//Sort.gets(Vec,i);
              if (!sCell.toString().trim().equals(Static.sLeer))
                s += "<p width=\"300px\"><b>" + Static.cin(sLabels[i], ":") + "</b> " + g.getHtmlPart(sCell.toString(),true)+"</p>";
            }
          if (Static.bInfoTT) g.fixInfo("getInfo:"+s);
          return s;
        }

        private void addSystem(String s,ActionListener A)
        {
          int iPos=g.getPosBegriff(s.equals("Import")?"Button":"Frame",s);
          if (iPos>=0 && g.BerechtigungS(iPos))
          {
            JMenuItem Mnu = new JMenuItem(g.getBegBez3(iPos));
            Mnu.setIcon(g.LoadImageIcon(iPos));
            Mnu.setFont(g.fontPopup);
            g.setTooltip(g.TabBegriffe.getS(iPos, "Tooltip"), Mnu);
            popSystem.add(Mnu);
            Mnu.setName(s);
            Mnu.addActionListener(A);
            iSAnz++;
          }
        }

        private void openSystem(String s)
        {
          if(s.equals("PersEinstellungen"))
            PersEinstellungen.get(g).show(this);
          else if(s.equals("System"))
            Systemeinstellung.get(g).show(this);
          else if(s.equals("Feiertag"))
            Feiertage.get(g).show(this);
          else if(s.equals("User Manager"))
            UserManager.get(g);
          else if(s.equals("Logging"))
        	  Logging.get(g);
          else if(s.equals("SyncStamm"))
        	  SyncStamm.start(g,null).show();
          else if(s.equals("Aufgabe"))
        	  DefAufgabe.get(g,0);
          else if(s.equals("Reinigung"))
            Reinigung.get(g);
          else if(s.equals("Prozesse"))
            Prozesse.get(g);
          else if(s.equals("Security"))
            Security.get(g);
          else if(s.equals("Verboten"))
        	  Verboten.get(g).show();
          else if(s.equals("Lizenz"))
              Lizenz.get(g);
          else if(s.equals("Benutzerpasswort"))
        	  /*if (Static.bJavaFX)
        		  PasswortFX.run(g);
        	  else*/
        		  new Passwort((JFrame)this,g).setPasswort("Benutzer",g.getBenutzer(),true);
          else if(s.equals("DefTerminal"))
            DefTerminal.get(g);
          else if(s.equals("Import"))
            Import.start(g, this).show2(iSTT_Mom);
        }

        private void allesSchliessen(int iM)
        {
          Beenden(false);
          int iMold=g.getMandant();
          if (iM>0)
            g.setMandant(iM);
          if (iMold!=iM)
          {
            Systemeinstellung.LoadDir(g,null,iM);
            g.fillBerechtigung(0);
            ShowAbfrage.refreshBerechtigungSmall(g);
          }
          for(int iPos=0;iPos<g.TabFormulare.size();)
          {
            Object Obj = g.TabFormulare.getInhalt("Formular",iPos);
            if(Obj instanceof EingabeFormular)
            {
              if (Obj!=null && ((Formular)Obj).thisFrame!=null)
                try
                {
                  ((Formular)Obj).thisFrame.dispose();
                }
                catch (Exception e2)
                {
                  Static.printError("EingabeFormular "+g.getBegBez(((Formular)Obj).getBegriff())+" nicht beendbar: "+e2);
                }
              g.TabFormulare.clearInhalt(iPos);
            }
            else if(Obj instanceof Hauptschirm)
            {
              ((Hauptschirm)Obj).dispose();
              g.TabFormulare.clearInhalt(iPos);
            }
            else
              iPos++;
          }
          freeAllSwing(g);
          TCalc.TabCalc=null;
          Object Obj=Hauptschirm.get(sApplikation,g);
          //g.putTabFormulare(g.getBegriffAicS("Applikation",sApplikation),0,Obj);
        }

        private void AuswahlErstellen()
        {
          ActionListener ALM=new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                int iM=Sort.geti(((JMenuItem)ev.getSource()).getName());
                allesSchliessen(Sort.geti(((JMenuItem)ev.getSource()).getName()));
              }
            };

            popMandant = new JPopupMenu();
            int iMAnz=0;
            ButtonGroup RadGroupM=new ButtonGroup();
            for(int iPos=0;iPos<g.TabMandanten.size();iPos++)
              if (g.isWriteMandant(g.TabMandanten.getI(iPos,"aic_mandant")))
              {
                //VecItem.addElement(new Combo(g.TabMandanten.getS("Bezeichnung"), g.TabMandanten.getI("aic_mandant"), g.TabMandanten.getS("Kennung"), 0));
            	int iMM=g.TabMandanten.getI(iPos,"aic_mandant");
                JRadioButtonMenuItem Mnu = new JRadioButtonMenuItem(g.getMandant(iMM,null));//g.TabMandanten.getS(iPos,"Bezeichnung"));
                RadGroupM.add(Mnu);
                Mnu.setFont(g.fontPopup);
                popMandant.add(Mnu);
                if (iMM==g.getMandant())
                  Mnu.setSelected(true);
                Mnu.setName(""+iMM);
                Mnu.addActionListener(ALM);
                iMAnz++;
              }
            if (iMAnz>1)
              g.setAnzahl(BtnMandant,iMAnz);
            else
            {
              BtnMandant.setVisible(false);
              //LblM.setVisible(false);
            }
            ActionListener ALS=new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                openSystem(((JMenuItem)ev.getSource()).getName());
              }
            };
            iSAnz=0;
            popSystem = new JPopupMenu();
            addSystem("DefTerminal",ALS);
            addSystem("PersEinstellungen",ALS);
            addSystem("System",ALS);
            addSystem("Feiertag",ALS);
            addSystem("Benutzerpasswort",ALS);
            if (g.Def())
            {
              addSystem("Logging",ALS);
              addSystem("SyncStamm",ALS);
            }
            addSystem("Aufgabe",ALS);
            if (g.UserManager())
              addSystem("User Manager",ALS);
            addSystem("Verboten",ALS);
            if (g.Verwaltung())
              addSystem("Lizenz",ALS);
            addSystem("Security",ALS);
            addSystem("Reinigung",ALS);
            addSystem("Prozesse",ALS);
            addSystem("Import",ALS);
            if (iSAnz>0)
              ;//g.setAnzahl(BtnSystem,iSAnz);
            else
              BtnSystem.setVisible(false);

            ActionListener ALH = new ActionListener()
              {
                public void actionPerformed(ActionEvent ev)
                {
                  setCursor(new Cursor(Cursor.WAIT_CURSOR));
                  String sHS=((JMenuItem)ev.getSource()).getName();
                  int iAppl=g.getBegriffAicS("Applikation",sHS);
                  int iPos=g.TabFormulare.getPos("Aic",iAppl);
                  if (iPos>=0)
                    ((Hauptschirm)g.TabFormulare.getInhalt("Formular",iPos)).setVisible(true);
                  else
                  {
                    Thread T=new Thread(new Runnable()
                    {
                      public void run()
                      {
                        String sHS=Thread.currentThread().getName();
                        Object Obj = new Hauptschirm(sHS, g);
                        g.putTabFormulare(g.getBegriffAicS("Applikation",sHS), 0, Obj);
                      }
                    });
                    T.setName(sHS);
                    T.start();
                  }
                  g.progInfo(" --------------- Haupschirm "+sHS+" aufrufen");
                  setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
              };
              popHS = new JPopupMenu();
              int iHAnz=0;
              Vector Vec=SQL.getVector("select beg_aic_begriff from begriff_Zuordnung where aic_begriff="+iBegriff+" order by reihenfolge",g);
              for (int i=0;i<Vec.size();i++)
              {
                int iPos=g.TabBegriffe.getPos("Aic",Sort.geti(Vec,i));
                if (g.BerechtigungS(iPos))
                {
                  JMenuItem Mnu = new JMenuItem(g.getBegBez3(iPos));
                  Mnu.setIcon(g.LoadImageIcon(iPos));
                  Mnu.setFont(g.fontPopup);
                  g.setTooltip(g.TabBegriffe.getS(iPos, "Tooltip"), Mnu);
                  popHS.add(Mnu);
                  Mnu.setName(g.TabBegriffe.getS(iPos, "Kennung"));
                  Mnu.addActionListener(ALH);
                  iHAnz++;
                }
              }
              if (iHAnz>0)
                g.setAnzahl(BtnHS,iHAnz);
              else
                BtnHS.setVisible(false);

        }

        private int addEinstellung()
        {
          int iAnz=0;
          if (TabRbt == null)
            TabRbt = new Tabellenspeicher(g, new String[] {"Aic", "Rbt"});
          if (TabRbt.getPos("Aic", TabHS.getI("Aic")) < 0 && !(TabHS.getS("DefBez").equals("") && TabHS.getS("Bezeichnung").equals("")) && (TabHS.getI("bits") & WEB)==0)
          {
            JRadioButtonMenuItem Rbt = new JRadioButtonMenuItem(Static.beiLeer(TabHS.getS("Bezeichnung"), TabHS.getS("DefBez")));
            Rbt.setFont(Global.fontPopup);
            TabRbt.addInhalt("Aic", TabHS.getI("Aic"));
            TabRbt.addInhalt("Rbt", Rbt);
            if (TabHS.getI("Aic") == iHS)
              Rbt.setSelected(true);
            g.setTooltip(TabHS.getM("TT"), Rbt);
            popEinStellung.add(Rbt);
            RadGroup.add(Rbt);
            iAnz++;
            Rbt.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                //g.progInfo("Rbt actionPerformed");
                //popM.setVisible(false);
                if (DlgFilter==null)
                  FilterAny(null,false);
                int iPos = TabRbt.getPos("Rbt", ev.getSource());
                if (iPos >= 0)
                  Refresh_HS(TabRbt.getI(iPos, "Aic"));
              }
            });
          }
          return iAnz;
        }


        private void Einstellungsauswahl(ActionEvent ev)
        {
          //final JPopupMenu popM = new JPopupMenu();
          //TabRbt=null;
          if (popEinStellung==null)
          {
            popEinStellung = new JPopupMenu();
            TabHS.sort("Bezeichnung",true);
            int iAnz=0;
            for (TabHS.moveFirst(); !TabHS.eof(); TabHS.moveNext())
              if ((TabHS.getI("bits") & AUSWAHL) > 0)
                iAnz+=addEinstellung();
            if (iAnz>0)
              popEinStellung.addSeparator();
            iAnz=0;
            for (TabHS.moveFirst(); !TabHS.eof(); TabHS.moveNext())
              if ((TabHS.getI("bits") & AUSWAHL) == 0)
                iAnz+=addEinstellung();
            //JMenu MnuAuswahl2 = new JMenu(g.getBegriff("Show", "Hauptschirm2"));
            //MnuAuswahl2.setFont(g.fontPopup);

            //popEinStellung.add(MnuAuswahl);
            if (BtnEinstellung.isEnabled())
            {
              if (iAnz>0)
                popEinStellung.addSeparator();
              g.addMenuItem("Einstellung", popEinStellung).addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent ev)
                {
                  FilterAny(null,true);
                }
              });
            }
          }
          popEinStellung.show((Component)ev.getSource(), 0, 24);
        }
        private void changeZR(boolean bPlus)
        {
          /*DateWOD DWvon = new DateWOD(g.getVon());
          DateWOD DWbis = new DateWOD(g.getBis());
          if(bPlus) {
            DWvon.next(Static.sZeitart);
            DWbis.next(Static.sZeitart);
          }
          else {
            DWvon.prev(Static.sZeitart);
            DWbis.prev(Static.sZeitart);
          }
          g.setVon(DWvon.toTimestamp());
          g.setBis(DWbis.toTimestamp());*/
          g.changeZR(bPlus ? "ZRplus":"ZRminus");
          Zeitraum.PeriodeToVec(g,g.getZA(0));
          //bNodStammGeladen = false;
          RefreshGrid();
        }

        private boolean posGF(int iStt)
        {
          TabGruppenFilter.posInhalt("int2",iStt);
          //g.fixInfo("--- posGF "+iStt+"->"+TabGruppenFilter.getPos());
          while (!TabGruppenFilter.eof() && TabGruppenFilter.getI("ANS_AIC_ANSICHT") != 0)
            TabGruppenFilter.posInhalt("AIC_ANSICHT",TabGruppenFilter.getI("ANS_AIC_ANSICHT"));
          return !TabGruppenFilter.eof();
        }

        private void Ansicht(boolean b)
        {
          //TabGruppenFilter.showGrid();
          //int iEig=((Integer)(((Vector)Gid.getSelectedNode().getUserData()).elementAt(2))).intValue();
          int iStt=CboStt.getSelectedAIC();
          if (iStt==0)
          {
            JCOutlinerFolderNode Nod = (JCOutlinerFolderNode)Gid.getSelectedNode();
            while (Nod.getLevel() > 1)
              Nod = Nod.getParent();
            iStt = ((Integer)(((Vector)Nod.getUserData()).elementAt(1))).intValue();
          }
          //g.progInfo("Ansicht: iStt="+iStt);
                                        //g.progInfo(""+Gid.getSelectedNode().getUserData());
                                        if (iStt!=0 && posGF(iStt)/* && TabGruppenFilter.posInhalt("int1",TabGruppenFilter.getI("int1"))*/)
                                        {
                                          GruppenFilter fGF = GruppenFilter.start(TabGruppenFilter,g);
                                          fGF.show();
                                          if (g.Abfrage() && fGF.isOkConfirm())
                                          {
                                            //g.testInfo("Refresh wegen Gruppenfilter");
                                            TabStyle.clearAll();
                                            //NodStt.removeChildren();
                                            if (bStammtypen)
                                            {
                                              //LadeHierachie();
                                              ErmittleSttAnzahl();
                                            }
                                            if (b)
                                              DlgFilter.getRootPane().setDefaultButton(BtnRefresh2);
                                            else
                                              getRootPane().setDefaultButton(BtnRefresh);
                                            //TabAbfrage2.clearAll();
                                          }

                                                //g.printInfo("Gruppenfilter von Hauptmaske aufgerufen");
                                                /*if(fGF.isOkConfirm())
                                                {
                                                        bNodStammGeladen = false;
                                                        //if (g.Debug())
                                                        //	TabGruppenFilter.showGrid("Gruppenfilter");
                                                        //LadeHierachie();
                                                        //bGidChange=false;
                                                        RefreshGrid();
                                                        //bGidChange=true;
                                                }*/
                                        }
                                        else
                                                g.printError("BtnGruppenFilter.addActionListener: TabGruppenFilter fehlt ein Parameter!",iBegriff);

        }

	public void Beenden(boolean bClose)
	{
		//g.progInfo("Hauptschirm-BtnBeenden");
                try
                {
                  int iPos=iBegriff>0 ? g.TabBegriffe.getPos("Aic", iBegriff):-1;
                  if (iPos<0)
                    Static.printError("Hauptschirm.Beenden: Begriff "+iBegriff+" nicht gefunden");
                  else
                  {
                    Parameter p = new Parameter(g, g.TabBegriffe.getS(iPos, "Kennung"));
                    /*for(TabAbfrage.moveFirst();!TabAbfrage.eof();TabAbfrage.moveNext())
                       {
                     int iAbf= TabAbfrage.getInhalt("Abfrage")==null?0:((Abfrage)TabAbfrage.getInhalt("Abfrage")).iAbfrage;
                     if(TabAbfrage2.posInhalt("int1",TabAbfrage.getI("Stammtyp")))
                      if (iAbf==0)
                       TabAbfrage2.clearInhalt();
                      else
                       TabAbfrage2.setInhalt("int2",new Integer(iAbf));
                     else
                      if (iAbf!=0)
                      {
                       TabAbfrage2.newLine();
                       TabAbfrage2.setInhalt("int1",TabAbfrage.getI("Stammtyp"));
                       TabAbfrage2.setInhalt("int2",new Integer(iAbf));
                      }
                       }
                       p.setMParameter("Abfrage",TabAbfrage2,bMandant);*/
                    g.setFenster(self, iBegriff,iFF);

                    if (iHS == 0)
                    {
                      iHS = SQL.getInteger(g, "select aic_hauptschirm from hauptschirm where kennung='*' and aic_begriff=" + iBegriff + " and aic_benutzer=" + g.getBenutzer());
                      int iBitsNew=3+(bStammtypen ? STT:0)+iVL+(bOffen ? OFFEN:0)+(bAustritt?AUSTRITT:0);
                      Save("", "*", 0, iBitsNew, "Default", "",0);
                    }
                    //hide();
                    int iBitsNew = 3 + (bStammtypen ? STT : 0) + iVL + (bOffen ? OFFEN : 0) +
                         (bDel ? DEL : 0) + (bAustritt ? AUSTRITT : 0) + (bBeginn ? BEGINN : 0) + (bSpalte1 ? SPALTE1 : 0) + (bTooltip ? TOOLTIP : 0); // + (CbxAnzeigen.isSelected() ? ANZEIGE : 0);
                    //int iForm=CboFormular.getSelectedAIC();
                    //g.progInfo("Form/Stamm="+iForm+"/"+iFormOld+" || "+iStamm_Mom+"/"+iStamm_Start);
                    if (iHS != iHSold || iStamm_Mom != iStamm_Start || iBitsNew != iBitsOld || iForm != iFormOld)
                    {
                      if ((iBitsOld & 7) < 3)
                        p.deleteAll();
                      //g.progInfo("Spaltendef:"+iHS+"/"+iBitsNew+"/"+iStamm_Mom+"/"+iForm);
                      p.setMParameter("Spaltendef", "", iHS, iBitsNew, iStamm_Mom, iForm, -1);
                    }
                    //p.setParameter("Cbx","",bAutoDarstellen ? 1:0,bStammtypen ? 1:0,bNurOrdner ? 1:0,0,true,false);
                    //p.setMParameter("GruppenFilter",TabGruppenFilter,bMandant);
                    p.free();
                  }
                }
                catch (Exception e)
                {
                  Static.printError("Hauptschirm.Beenden:"+e);
                  e.printStackTrace();
                }
                if (bClose)
                  setVisible(false);
	}

	/*private void setToolTip()
	{
		if (Mmo != null)
			Mmo.setText(g.TabBegriffe.posInhalt("Aic",CboFormular.getSelectedAIC())?g.TabBegriffe.getS("Info"):"fehlt!");
		//g.progInfo("Tooltip="+s);
		//BtnEdit.setToolTipText(s);
	}*/

	@SuppressWarnings("unchecked")
	private void DetailAufruf(boolean bImmer,int riForm,int iEig)
	{
          toFront();
          iForm=riForm;
          if (TabAktFormular.posInhalt("Stt",iSTT_Mom))
          {
            if (iForm==0)
              iForm=TabAktFormular.getI("Formular");
            else
            {
              TabAktFormular.setInhalt("Formular", iForm);
              int iPos=g.TabBegriffe.getPos("Aic", iForm);
              if (iPos>=0)
              {
                MnuLastForm.setText(g.TabBegriffe.getBezeichnungS(iForm));
                g.setTooltip(g.TabBegriffe.getS(iPos, "Tooltip"), MnuLastForm);
              }
              else
                Static.printError("Hauptschirm.DetailAufruf: Formular mit Aic="+iForm+" nicht gefunden!");
              //MnuLastForm.setToolTipText(g.TabBegriffe.getInfoS(iForm,"Info"));
              MnuLastForm.setVisible(true);
            }
          }
		//g.printInfo("DetailAufruf: "+bImmer+"/"+iForm);
		long lClock = Static.get_ms();
		Cursor CurVorher=getCursor();
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		//int iFormular=CboFormular.getSelectedAIC();
                boolean bNurOrdner2=true;/*bNurOrdner;
                if (!bNurOrdner && g.TabBegriffe.posInhalt("Aic",iForm) && (g.TabBegriffe.getL("bits")&Formular.cstNurOrdner)>0)
                  bNurOrdner2=true;*/
		if(bImmer || bStamm && (iForm > 0 ? g.TabFormulare.posInhalt("Aic",iForm):g.TabFormulare.posInhalt("Stt",iSTT_Mom)))
		{
			Vector<Object> Vec = /*CboTyp.getSelectedKennung().equals("Stammtyp") &&*/ bNurOrdner2 && bStamm && iStamm_Mom!=0 ? new Vector():null;
			if( Vec != null)
			{
				Vector Vec2 = Gid.getSelectedNode().getParent().getChildren();
				for(int i=0;i<Vec2.size();i++)
                                {
                                  int iAic=((Integer)((Vector)((JCOutlinerNode)Vec2.elementAt(i)).getUserData()).elementAt(0)).intValue();
                                  Vec.addElement(new Integer(iAic>0?iAic:-iAic));
                                }
			}
                        int iStamm = bStamm ? iStamm_Mom:0;
                        /*if (iSTT_Mom<0)
                        {
                          JCOutlinerFolderNode NodParent=Gid.getSelectedNode().getParent();
                          iStamm = ((Integer)((Vector)NodParent.getUserData()).elementAt(0)).intValue();
                        }*/
                        //int iForm=CboFormular.getSelectedAIC();
                        if (bStdForm || iForm>0)
                        {
                          String sBez=iForm > 0 ? g.TabBegriffe.getBezeichnungS(iForm):g.TabStammtypen.getBezeichnungS(-iSTT_Mom);
                          g.saveSqlTime("Fom1",0,-1,sBez,iBegriff,iStamm);
                          Vector Vec2=g.getSyncVec(iSTT_Mom);
                          if (Vec2 != null && Vec2.size()>1)
                            Vec=Vec2;
                          //if (!EingabeFormular.inBearbeitung(g,iForm))
                          //g.fixInfo("Parent="+Gid.getSelectedNode().getParent().getUserData());
                          EingabeFormular EF=EingabeFormular.HoleFormular(g, iForm > 0 ? iForm : -iSTT_Mom,bStammtypen?new Vector():Vec,
                              iStamm==0?-1:iStamm,iSTT_Mom<0,null,Sort.geti(Gid.getSelectedNode().getParent().getUserData(),0),false);
                          if (iEig>0)
                            EF.posEig(iEig);
                          if (!EF.bFP)
                        	  Static.centerComponent(EF.thisFrame, self);
                          g.saveSqlTime("Fom2",0,Static.get_ms()-lClock,sBez,iBegriff,iStamm);
                        }
                        //else
                        //  g.progInfo("iSTT_Mom="+iSTT_Mom+", iStamm_Mom="+iStamm_Mom+"("+Vec+")");
			g.clockInfo("EF_von_HS - "+g.TabBegriffe.getBezeichnungS(iForm),lClock);
		}
		setCursor(CurVorher);
	}

        private void openDF(int iDruck)
        {
          DruckFrage DF=DruckFrage.start(Gid,iRolle,0,iStamm_Mom,null,g,iDruck,iBegriff);
          //g.progInfo("openDF:"+iStamm_Mom+"/"+iSTT_Mom+"/"+Gid.getSelectedNode().getUserData());
          DF.setEbenen(TabGruppenFilter,Sort.geti(Gid.getSelectedNode().getUserData(),0)==0 ? 0:iSTT_Mom);
          DF.setAbfrage(Abf);
          DF.show();
        }

        private JCOutlinerFolderNode fillStyle(JCOutlinerFolderNode NodParent,int iVon,int iAnzahl)
        {
          //g.progInfo("HS.fillStyle:"+iVon);
          TabStyle.addInhalt("Stammtyp",new Integer(iVon));
          //TabGruppenFilter.push();
          //TabAbfrage.addInhalt("Abfrage",TabGruppenFilter.posInhalt("int2",iVon) ? new ShowAbfrage(g,TabGruppenFilter.getI("aic_abfrage"),Abfrage.cstHS_Filter):null);
          //TabGruppenFilter.pop();
          //if(TabAbfrage2.posInhalt("int1",iVon))
          //        g.progInfo(" LoadAbfrage_Style:"+iVon+"="+TabAbfrage2.getI("int2"));
          JCOutlinerNodeStyle StyFolder=iVon > 0 ? iRolle>0 && iSttRolle==iVon ? g.getRolleStyle(iRolle):g.getSttStyle(iVon):g.getStyle(g.getBewGif(-iVon));

          StyFolder.setFont(g.getOutFont(false));//g.fontStandard);
              String sBez=iVon>0 ? iVon==iSttRolle && iRolle>0 ? Static.beiLeer(g.TabRollen.getBezeichnungS(iRolle),g.TabRollen.getKennung(iRolle))
                                            :g.TabStammtypen.getBezeichnungS(iVon):g.TabErfassungstypen.getBezeichnungS(-iVon);
          //g.progInfo(sBez+":"+g.fontTitel);
          TabStyle.addInhalt("Style",StyFolder);
          JCOutlinerNodeStyle StyFolder2=new JCOutlinerNodeStyle(StyFolder);
          StyFolder2.setFont(g.getOutFont(true));//g.fontTitel);
          TabStyle.addInhalt("Style2",StyFolder2);
          if (NodParent==null)
            NodParent=(JCOutlinerFolderNode)Gid.getRootNode();
          NodParent=new JCOutlinerFolderNode((Object)new jclass.util.JCVector(new Object[] {sBez,new Integer(iAnzahl)}),NodParent);
          if (StyFolder2 != null)
                  NodParent.setStyle(StyFolder2);
          Vector<Integer> VecAIC = new Vector<Integer>();
          VecAIC.addElement(Static.Int0);
          VecAIC.addElement(new Integer(iVon));
          //VecAIC.addElement(TabSTT2.getInhalt("aic_eigenschaft")==null?Static.Int0:TabSTT2.getInhalt("aic_eigenschaft"));
          VecAIC.addElement(new Integer(TabGruppenFilter.getI("int3")));
          NodParent.setUserData(VecAIC);
          return NodParent;
        }

	/*private void LoadAbfrage_Style(int iVon)
	{
          //g.progInfo("HS.LoadAbfrage_Style");
          int i=iVon;
		JCOutlinerFolderNode NodParent=NodStt;
		while(iVon != 0)
		{
			g.TabStammtypen.posInhalt("Aic",new Integer(iVon));
			if (!TabStyle.posInhalt("Stammtyp",iVon) && g.existsStt(iVon))
                          NodParent=fillStyle(NodParent,iVon);
			iVon = g.TabStammtypen.getI("Darunter");
			//g.progInfo("LoadAbfrage_Style - "+g.TabStammtypen.getS("Bezeichnung")+": "+iVon);
		}
                for(TabGruppenFilter.moveFirst();!TabGruppenFilter.eof();TabGruppenFilter.moveNext())
                {
                  iVon=TabGruppenFilter.getI("int2");
                  if (TabGruppenFilter.getI("int1")==i && iVon < 0 && g.TabErfassungstypen.posInhalt("Aic", -iVon) && NodParent.getLevel()>0)
                    fillStyle(NodParent,iVon);
                }
		//TabAbfrage.showGrid("TabAbfrage");
	}*/

	private void setSTT_Icon(JCOutlinerFolderNode rNod,int riSTT,boolean bTitel,int iStamm)
	{
		JCOutlinerNodeStyle StyFolder = null;
		if (TabStyle.posInhalt("Stammtyp",riSTT))
			StyFolder=(JCOutlinerNodeStyle)TabStyle.getInhalt(bTitel?"Style2":"Style");
		else if (riSTT<0)
		{
			StyFolder=g.getStyle(g.getBewGif(-riSTT));
			TabStyle.newLine();
			TabStyle.setInhalt("Stammtyp",riSTT);
			TabStyle.setInhalt("Style",StyFolder);
			TabStyle.setInhalt("Style2",StyFolder);
		}
		else
		{
			StyFolder=iRolle>0 && iSttRolle==riSTT ? g.getRolleStyle(iRolle):g.getSttStyle(riSTT);
			if (StyFolder !=null)
				StyFolder.setFont(g.getOutFont(bTitel));//bTitel?g.fontTitel:g.fontStandard);
		}
		if (StyFolder !=null)
		{
			//StyFolder.setFont(bTitel?g.fontTitel:g.fontStandard);
		  if (riSTT==g.iSttANR && StyAustritt==null)
                  {
                    StyAustritt = new JCOutlinerNodeStyle(StyFolder);
                    StyAustritt.setForeground(Global.ColAustritt);
                  }
                  rNod.setStyle(riSTT!=g.iSttANR || ShowAbfrage.istDa(g,iStamm) ? StyFolder:StyAustritt);

			//rNod.setStyle(StyFolder);
			//if (bTitel)
			//	g.progInfo(rNod+":"+StyFolder.getFont());
		}
	}

        private boolean checkVorletzte(JCOutlinerNode NodNeu)
        {
          if ((Abf.iBits&Abfrage.cstVorletzteEbene)==0)
            return false;
          else
          {
            TabGruppenFilter.push();
            if (TabGruppenFilter.getI("int2") != Tabellenspeicher.geti(((Vector)NodNeu.getUserData()).elementAt(1)))
              TabGruppenFilter.posInhalt("int2",((Vector)NodNeu.getUserData()).elementAt(1));
            int i1=TabGruppenFilter.out() ? 0:TabGruppenFilter.getI("int1");
            TabGruppenFilter.moveNext();
            boolean b=!TabGruppenFilter.out() && TabGruppenFilter.getI("int1")==i1;
            //g.progInfo("NodNeu1="+NodNeu+"/"+NodNeu.getUserData()+"/"+TabGruppenFilter.getI("int2")+":"+b);
            if (b)
              TabGruppenFilter.moveNext();
            //g.progInfo("NodNeu2="+NodNeu+"/"+NodNeu.getUserData()+"/"+TabGruppenFilter.getI("int2")+":"+b);
            b=b && (TabGruppenFilter.out() || TabGruppenFilter.getI("int1")!=i1);
            TabGruppenFilter.pop();
            //g.progInfo("NodNeu3="+NodNeu+"/"+NodNeu.getUserData()+"/"+TabGruppenFilter.getI("int2")+":"+b);
            return b;//TabGruppenFilter.getI("int4") == GruppenFilter.BEWGRUPPE;
          }
        }

	@SuppressWarnings("unchecked")
	private JCOutlinerFolderNode fuelleHierachie(int iSTT,Tabellenspeicher Tab,JCOutlinerFolderNode NodDarueber)
	{
          //g.testInfo("fuelleHierachie "+g.getBez(iSTT));
		//Tab.showGrid(""+iSTT);
		//int iSTT = Tab.getI("AIC_Stammtyp");
		if (TabGruppenFilter.posInhalt("int2",new Integer(iSTT)) && TabGruppenFilter.getI("int4")==GruppenFilter.AUS)
		{
			TabKnoten.addInhalt("Stamm",Tab.getInhalt("AIC_Stamm"));
			TabKnoten.addInhalt("Knoten",NodDarueber);
			return NodDarueber;
		}
                if (!TabGruppenFilter.bof())
		  TabGruppenFilter.movePrevious();
                Vector<Integer> VecGruppierung=new Vector<Integer>();
		while (!TabGruppenFilter.bof() && (TabGruppenFilter.getI("int4")==GruppenFilter.GRUPPE || TabGruppenFilter.getI("int4")==GruppenFilter.BEWGRUPPE))
		{
			VecGruppierung.insertElementAt(new Integer(TabGruppenFilter.getI("int3")),0);
			TabGruppenFilter.movePrevious();
		}

		String s= /*NodDarueber==Gid.getSelectedNode()?"0":*/""+((Vector)NodDarueber.getUserData()).elementAt(0);
		//String s="";
		//boolean bSuchen = true;
                //g.progInfo("fuelleHierachie mitte");

    for(int i=0;i<VecGruppierung.size();i++)
		{
			//int iAic=((Integer)VecGruppierung.elementAt(i)).intValue();
			Integer iElement = Tab.getInt("V"+VecGruppierung.elementAt(i));
                        //g.testInfo("iElement="+iElement);
			//if (iElement != null)
			{
				s=s+'.'+iElement;
				int iPos = TabGruppierung.getposInhalt("Kennung",s);
				if (iPos==-1)
				{
					//g.printInfo(s);
						if (TabGruppenFilter.posInhalt("int3",VecGruppierung.elementAt(i)) &&
                                                    (iElement==null || TabGruppen.posInhalt("aic_stamm",iElement)))
						{
								Vector<String> VecSpalten = new Vector<String>();
								VecSpalten.addElement(iElement==null ? Static.sKein:TabGruppen.getS("Bez"));
								if (TabSpalten != null)
								{
									TabSpalten.moveFirst();
									if (!TabSpalten.isEmpty())
										TabSpalten.moveNext();
									for(;!TabSpalten.eof();TabSpalten.moveNext())
										VecSpalten.addElement(null);
								}
								JCOutlinerFolderNode NodNeu = new JCOutlinerFolderNode(VecSpalten);
								Image Gif=iElement!=null ? g.getGif(g.TabStammBild, iElement.intValue()):null;
                                                                if (iElement!=null && Gif != null) //g.TabStammBild.posInhalt("Aic", iElement))
                                                                  NodNeu.setStyle(g.getStyle(Gif));//(java.awt.Image)g.TabStammBild.getInhalt("Bild")));
                                                                else
                                                                  setSTT_Icon(NodNeu,TabGruppenFilter.getI("int2"),false,TabGruppen.getI("aic_stamm"));
								Vector<Integer> VecAIC = new Vector<Integer>();
								VecAIC.addElement(new Integer(iElement==null ? 0:TabGruppen.getI("aic_stamm")));
								VecAIC.addElement(new Integer(TabGruppenFilter.getI("int2")));
								VecAIC.addElement(Static.Int0);//new Integer(TabGruppenFilter.getI("int3")));
								NodNeu.setUserData(VecAIC);
								NodDarueber.addNode(NodNeu);
								NodDarueber=NodNeu;
                                                                //g.progInfo("iStamm_Mom="+iStamm_Mom+"/ iStamm="+TabGruppen.getI("aic_stamm"));
                                                                if (iElement!=null && iStamm_Mom==TabGruppen.getI("aic_stamm"))
                                                                {
                                                                  /*if(TabAktFormular.isEmpty() && iFormOld > 0) {
                                                                    TabAktFormular.addInhalt("Stt", TabGruppenFilter.getI("int2"));
                                                                    TabAktFormular.addInhalt("Formular", iFormOld);
                                                                  }*/

                                                                  NodStammMom = NodNeu;
                                                                  while(NodNeu.getLevel() > 0) {
                                                                    NodNeu.setState(BWTEnum.FOLDER_OPEN_ALL);
                                                                    //g.progInfo("Open:"+Nod);
                                                                    NodNeu = NodNeu.getParent();
                                                                  }
                                                                }
                                                                else if (!bOffen || checkVorletzte(NodNeu))//(Abf.iBits&Abf.cstVorletzteEbene)>0 && TabGruppenFilter.getI("int4")==GruppenFilter.BEWGRUPPE)
                                                                  NodNeu.setState(BWTEnum.FOLDER_CLOSED);
						}
						TabGruppierung.addInhalt("Kennung",s);
						TabGruppierung.addInhalt("Node",NodDarueber);

				}
				else
					NodDarueber = (JCOutlinerFolderNode)TabGruppierung.getInhalt("Node",iPos);
			}
		}
                //g.progInfo("fuelleHierachie nach Schleife");
		Vector<Object> VecSpalten = new Vector<Object>();
		Vector<Integer> VecAIC = new Vector<Integer>();
		int iStamm = iSTT<0 ? -Tab.getI("AIC_Bew_pool"):Tab.getI("AIC_Stamm");
		//if (iStamm<100)
		//	g.progInfo("Stamm="+iStamm+"/"+iStamm_Mom);
		//if(bStamm)
		//{
			if (TabSpalten==null || TabSpalten.isEmpty())
				VecSpalten.addElement(Tab.getInhalt("Bezeichnung"));
			VecAIC.addElement(new Integer(iStamm));
			//int i=-1;
			if (TabSpalten != null)
				for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                                  if((TabSpalten.getI("bits")&Abfrage.cstUnsichtbar)==0)
				{
					//boolean bGruppe=TabSpalten.getInhalt("Gruppe")!=null;
					//boolean bPeriode=(TabSpalten.getI("bits")&Abfrage.cstPeriode)>0  && Zeitraum.VecPerioden!=null;;
					//int iAnz=bGruppe ? ((Vector)TabSpalten.getInhalt("Gruppe")).size(): bPeriode ? Zeitraum.VecPerioden.size()-1:1;
					Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr");
					int iAnz=VecMehr==null?1:VecMehr.size();
          Object Obj2=null;
          String sDatentyp = TabSpalten.getS("Datentyp");         
				  for(int iPer=0;iPer<iAnz;iPer++)
					{
            if (iSTT>0 && sDatentyp.startsWith("Bew") && TabSpalten.getS("Kennung").indexOf('b')<0)
            {
//              if (iPer==0 && Tab.getPos()==0)
//                g.fixtestError("kein ermitteln bei Stt="+iSTT+"/"+TabSpalten.getS("Bezeichnung")+"/"+TabSpalten.getS("Kennung")+" da "+sDatentyp);
              VecSpalten.addElement(null);
            }
            else
            {
						  String sAic=VecMehr!=null ? "e"+TabSpalten.getS("Kennung2")+VecMehr.elementAt(iPer):TabSpalten.getS("Kennung");
              Object Obj=Abf.TabToScreen(g,sAic,Tab,TabSpalten,VecMehr!=null);
              if (!Check.isNull(Obj))
                Obj2=Obj;
              VecSpalten.addElement((Abf.iBits&Abfrage.cstWeiterfuehren)>0?Obj2:Obj);
            }
						//i++;
					}
					boolean bRowAvg=VecMehr != null && (TabSpalten.getI("bits3")&Abfrage.cstPerSchnitt)>0;
					boolean bRowSum=VecMehr != null && (TabSpalten.getI("bits")&Abfrage.cstPeriodensumme)>0 || bRowAvg;
					if(bRowSum)
					{
						double d=0;
						for(int iPer=VecSpalten.size()-iAnz;iPer<VecSpalten.size();iPer++)
							d+=Sort.getf(VecSpalten.elementAt(iPer));
						if (bRowAvg)
							d/=iAnz;
						VecSpalten.addElement(ShowAbfrage.doubleToScreen(g,d,TabSpalten));
					}
					if (!TabSpalten.isNull("Ergebnisart"))
					{
						//int iPos=VecSpalten.size()-1;
						int iLevel=NodDarueber.getLevel();
						if (iLevel>0)
						{
							JCOutlinerFolderNode Nodx=NodDarueber;
							for (int i2=0;i2<iLevel;i2++)
							{
								Vector<Object> Vec= (Vector)Nodx.getLabel();
								for(int iPer=VecSpalten.size()-iAnz-(bRowSum?1:0);iPer<VecSpalten.size();iPer++)
                                                                  if(Vec.size()>iPer)
                                                                    Vec.setElementAt(Abf.getSum(Vec.elementAt(iPer),VecSpalten.elementAt(iPer),TabSpalten.getF("Min"),TabSpalten.getF("Max"),true),iPer);
                                                                  else
                                                                    Vec.addElement(Abf.getSum(null,VecSpalten.elementAt(iPer),TabSpalten.getF("Min"),TabSpalten.getF("Max"),true));
									//Vec.setElementAt(ShowAbfrage.doubleToScreen(g,Sort.getf(Vec.elementAt(iPer))+Sort.getf(VecSpalten.elementAt(iPer)),TabSpalten),iPer);
								Nodx=Nodx.getParent();
							}
						}
					}
				}

		VecAIC.addElement(new Integer(iSTT));
		VecAIC.addElement(new Integer(iEigenschaft_Mom));
                //VecSpalten.setElementAt(0," "+VecSpalten.elementAt(0));  // 12.5.2004
		JCOutlinerFolderNode NodNeu = new JCOutlinerFolderNode(VecSpalten);

		setSTT_Icon(NodNeu,iSTT,false,iStamm);

		NodNeu.setUserData(VecAIC);
		//if (!VecElemente.contains(new Integer(iStamm)))
		JCOutlinerFolderNode Nod=NodDarueber;
                //g.progInfo("iStamm_Mom="+iStamm_Mom+"/ iStamm="+iStamm);
		if (iStamm_Mom==iStamm)
		{
                  if (iFormOld>0)
                  {
                    //TabAktFormular.showGrid("TabAktFormular");
                    if (TabAktFormular.isEmpty())
                    {
                      TabAktFormular.newLine();
                      TabAktFormular.setInhalt("Stt", iSTT);
                      TabAktFormular.setInhalt("Formular", iFormOld);
                    }
                    //else if (TabAktFormular.posInhalt("Stt", iSTT) && TabAktFormular.getI("Formular") == 0)
                    //  TabAktFormular.setInhalt("Formular", iFormOld);
                  }
			NodStammMom = NodNeu;
			while (Nod.getLevel()>0)
			{
				Nod.setState(BWTEnum.FOLDER_OPEN_ALL);
				//g.progInfo("Open:"+Nod);
				Nod=Nod.getParent();
			}
		}
                //g.progInfo(NodNeu+"/"+VecStt+"/"+Nod.getLevel());
                if (!bOffen /*&& (Nod.getLevel()>0 || VecStt.size()>1)*/ || checkVorletzte(NodNeu))
                  NodNeu.setState(BWTEnum.FOLDER_CLOSED);
                if (bDel && iStamm<0 && !Tab.isNull("pro_aic_protokoll"))
                {
                  JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle(NodNeu.getStyle());
                  StyFolder.setForeground(g.ColLoeschen);
                  NodNeu.setStyle(StyFolder);
                }
		NodDarueber.addNode(NodNeu);

		TabKnoten.addInhalt("Stamm",iStamm);
		TabKnoten.addInhalt("Knoten",NodNeu);
                //g.progInfo("fuelleHierachie Ende");
		return(NodNeu);
	}

	private boolean existsFormular(int riStt)
	{
		/*if (g.SuperUser())
			return true;
		else*/
		{
			SQL Qry=new SQL(g);
			String s="select formular.aic_begriff from formular "+g.join("begriff","formular")+" join begriff_zuordnung on begriff_zuordnung.aic_begriff=begriff.aic_begriff where beg_aic_begriff="+iBegriff+" and aic_stammtyp="+riStt+" order by reihenfolge";
			for(Qry.open(s);!Qry.eof();Qry.moveNext())
			{
                int iPos=g.TabBegriffe.getPos("Aic",Qry.getI("Aic_Begriff"));
				if (iPos>=0 && g.BerechtigungS(iPos))
				{
					Qry.free();
//					g.fixtestError("existsFormular "+riStt);
					return true;
				}
			}
			Qry.free();
                        g.testInfo("existsFormular für Stt"+riStt+": false");
            g.fixInfoT("not existsFormular "+riStt);
			return false;
		}
	}

        private void CheckOldGF()
        {
          if (bCheckOld)
            return;
          g.progInfo("CheckOldGF:"+VecStt);
          boolean bRefresh=false;
          if (TabSTT2.size()==1)//TabSTT2 != null)
            {
              int iSttO = TabSTT2.getI(0, "aic_stammtyp");
              int iPosO = g.TabStammtypen.getPos("Aic", iSttO);
              int iPosS = g.getPosFirstStt(iSttO);
              if (iPosS >= 0 && iPosO != iPosS)
              {
                int iSttLiz=g.TabStammtypen.getI(iPosS, "Aic");
                g.progInfo("HS"+iHS+"="+iSttHS+"/"+CboStt.getSelectedAIC()+"->"+iSttLiz);
                if (iSttHS !=iSttLiz  && (CboHS.getSelectedAIC()==0 || TabGruppenFilter.posInhalt("int2",iSttLiz) && TabGruppenFilter.getI("aic_ansicht")>0))
                {
                  if (g.bInfoGF)
                    new Tabellenspeicher(g,TabGruppenFilter).showGrid("iSttLiz="+iSttLiz,self);
                  iSttHS = g.TabStammtypen.getI(iPosS, "Aic");
                  g.testInfo("Ändere Stammtyp von " + g.TabStammtypen.getBezeichnungS(iSttO) + " auf " + g.TabStammtypen.getBezeichnungS(iSttHS));
                }
                if (CboStt.getSelectedAIC() != iSttHS)
                {
                  g.testInfo("Ändere Cbo-Stammtyp von " + g.TabStammtypen.getBezeichnungS(CboStt.getSelectedAIC()) + " auf " + g.TabStammtypen.getBezeichnungS(iSttHS));
                  CboStt.setSelectedAIC(iSttHS);
                }
                g.progInfo("HS_n="+iSttHS+"/"+CboStt.getSelectedAIC());
                checkCboStt();
                //g.fixInfo("HS3="+iSttHS+"/"+CboStt.getSelectedAIC()+"->"+iSttLiz);
              }
              if (VecStt != null && VecStt.size()==1 && iSttHS>0 && Sort.geti(VecStt,0)!=iSttHS && TabHS.posInhalt("Aic",iHS) && TabHS.getI("AIC_STAMMTYP")>0)
              {
                VecStt.setElementAt(iSttHS, 0);
                g.defInfo2(" !!!!! Zusatz-Refresh !!!!!       VecStt-Neu=" + VecStt);
                if (CboStt.getSelectedAIC() != iSttHS)
                  CboStt.setSelectedAIC(iSttHS);
                bRefresh=true;
                bCheckOld=true;
              }
            }
            else
            {
              for (int iPos=0;iPos<TabGruppenFilter.size();iPos++)
                if (TabSTT2.getPos("aic_stammtyp",TabGruppenFilter.getI(iPos,"int1"))<0)
                {
                  TabGruppenFilter.clearInhalt(iPos);
                  iPos--;
                }
            }
          if (VecStt == null)
            return;
          boolean bDel=false;
          if (g.bInfoGF)
            new Tabellenspeicher(g,TabGruppenFilter).showGrid("Vorher",self);
          //g.fixInfo("CheckOldGF: iHS="+iHS+"/"+CboHS.getSelectedAIC());
          if (CboHS.getSelectedAIC()>0 && iHS>0)
          for (TabGruppenFilter.moveFirst();!TabGruppenFilter.eof();)
          {
            int iInt1=TabGruppenFilter.getI("int1");
            //g.progInfo("VecStt="+VecStt+", iInt1="+iInt1);
            if (!VecStt.contains(iInt1)/* && iSttHS!=iInt1*/ || iSttHS>0 && TabGruppenFilter.getPos()>0 && TabGruppenFilter.isNull("ans_aic_ansicht") // löschen von weiteren bei fixen Stt
                || VecStt.size()==1 && TabGruppenFilter.getI("aic_ansicht")<0 && (TabGruppenFilter.getI("ans_aic_ansicht")<1 && TabGruppenFilter.getPos()>0 || bDel))
            {
              //g.progInfo("lösche "+TabGruppenFilter.getInhalt("int1")+"/"+TabGruppenFilter.getInhalt("int2")+
              //           "/"+TabGruppenFilter.getInhalt("int3")+"/"+TabGruppenFilter.getInhalt("int4"));
              bDel=true;
              g.fixtestInfo("lösche "+g.TabStammtypen.getBezeichnungS(TabGruppenFilter.getI("int2")));
              g.progInfo("                      !!!! clearInhalt bei CheckOldGF: Stt "+g.TabStammtypen.getBezeichnungS(iInt1)+" fehlt");
              TabGruppenFilter.clearInhalt();
            }
            else
            {
              //bNN=TabGruppenFilter.getI("aic_ansicht")>0;
              TabGruppenFilter.moveNext();
            }
          }
        if (g.bInfoGF)
          new Tabellenspeicher(g,TabGruppenFilter).showGrid("Nachher",self);
          if (bRefresh)
            RefreshGrid();
          bCheckOld=false;
        }

	private void LadeHierachie(boolean bVoll)
    {
		g.progInfo(".LadeHierachie");
		NodStammMom = null;
		Cursor CurVorher=getCursor();
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		//g.progInfo("Refresh LadeHierachie");
		//iStamm_Mom = -1;
		//bFilter= false;
                sFilterShow="";
		//NodStamm = new JCOutlinerFolderNode(null,g.sMandant);
                //NodStamm.setLabel(CbxAnzeigen.isSelected() ? CboHS.getSelectedBezeichnung():g.getMandant(0,"Bezeichnung"));
                NodStamm.setLabel(CboHS.getSelectedBezeichnung());
		NodStamm.removeChildren();
		jclass.util.JCVector Vec=new jclass.util.JCVector(new String[] {"0","0","0"});
		NodStamm.setUserData(Vec);

		JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
		Image Gif = new ImageIcon(getClass().getResource("/images/"+(Static.bND ? "Top_ND.png":"mandant.png"))).getImage();
		//if (Gif != null)
		//{
			StyFolder.setFolderClosedIcon(Gif);
			StyFolder.setFolderOpenIcon(Gif);
		//}
		StyFolder.setFont(g.getOutFont(true));//new Font(g.Mojave() ? "Lucida Sans":"Arial",Font.BOLD,14));
		NodStamm.setStyle(StyFolder);
		if (bNodSttGeladen)
                  NodStt.setLabel(g.getMandant(0,null));
                else
		{
			NodStt = new JCOutlinerFolderNode(null,g.getMandant(0,null));
			NodStt.setStyle(StyFolder);
			NodStt.setUserData(Vec);
		}

		Vector<Object> VecGruppen=new Vector<Object>();
		if (TabGruppenFilter != null)
		  for (TabGruppenFilter.moveFirst();!TabGruppenFilter.eof();TabGruppenFilter.moveNext())
		  {
			if (TabGruppenFilter.getI("int4")==GruppenFilter.GRUPPE || TabGruppenFilter.getI("int4")==GruppenFilter.BEWGRUPPE)
				VecGruppen.addElement(TabGruppenFilter.getInhalt("int2"));
		  }
                TabGruppen = VecGruppen.size()==0 ? null:new Tabellenspeicher(g,"select aic_stamm,kennung,bezeichnung bez"+g.AU_Bezeichnung2("Stamm","stammview")+" from stammview where aic_stammtyp"+Static.SQL_in(VecGruppen),true);
		//if (TabGruppen != null)
		//	TabGruppen.showGrid("Gruppen");
                /*if (TabSTT2 == null)
                {
                  TabSTT2 = new Tabellenspeicher(g,"select s.aic_stammtyp,s.kennung,s.aic_eigenschaft"+
                                                 " from stammtyp s " + g.join("applikation_zuordnung", "s", "stammtyp") +
                                                 " where applikation_zuordnung.aic_begriff=" + iBegriff + " order by reihenfolge", true);
                  CboStt.Clear(true);//setKein(true);
                  Vector<Integer> VecPos=new Vector<Integer>();
                  for (TabSTT2.moveFirst();!TabSTT2.eof();TabSTT2.moveNext())
                  {
                    int iVon = TabSTT2.getI("aic_stammtyp");
                    while(iVon != 0)
                    {
                      int iPos = g.TabStammtypen.getPos("Aic", iVon);
                      if (!VecPos.contains(iPos))
                      {
                        VecPos.addElement(iPos);
                        if (g.existsSttS(iPos))
                          CboStt.addItem(g.TabStammtypen.getS(iPos, "Bezeichnung"), g.TabStammtypen.getI(iPos, "Aic"), g.TabStammtypen.getS(iPos, "Kennung"));
                      }
                      iVon = TabSTT2.getI("aic_eigenschaft") == 0 ? 0 : g.TabStammtypen.getI(iPos, "Darunter");
                    }
                  }
                  CboStt.setKein(true);
                  CboStt.setSelectedAIC(iSttHS);
                  //if (TabSTT2.getAnzahlElemente(null)<2)
                  //  CboStt.setEnabled(false);
                  //CheckOldGF();
                }*/
                VecStt=new Vector<Integer>();
                //g.fixInfo(" ************* TabSTT2.size()="+TabSTT2.size());
                for (TabSTT2.moveFirst();!TabSTT2.eof();TabSTT2.moveNext())
                {
                  int iCboStt=CboStt.getSelectedAIC();
                  //int iPosS = g.getPosFirstStt(iCboStt>0 ? iCboStt:TabSTT2.getI("aic_stammtyp"));
                  int iSttO=iCboStt>0 ? iCboStt:TabSTT2.getI("aic_stammtyp");
                  int iPosO=g.TabStammtypen.getPos("Aic",iSttO);
                  int iPosS = g.getPosFirstStt(iSttO);
                  if (iPosS>=0 && iPosO != iPosS)
                  {
                    if (TabSTT2.size()>1)
                      continue;
                    int iSttN=g.TabStammtypen.getI(iPosS,"Aic");
                    g.testInfo("Ändere Stammtyp von "+g.TabStammtypen.getBezeichnungS(iSttO)+" auf "+g.TabStammtypen.getBezeichnungS(iSttN));
                    CboStt.setSelectedAIC(iSttN);
                    checkCboStt();
                  }
                  if (iPosS>=0 && (iCboStt==0 || TabSTT2.getPos()==0))
		  {
                  //g.progInfo("LadeHierachie:"+CboStt.getSelectedAIC()+"/"+TabSTT2.getI("aic_stammtyp"));
			//iAnzahl = 0;
                        int iVon = g.TabStammtypen.getI(iPosS,"Aic");
                        VecStt.addElement(new Integer(iVon));
                        //if (iCboStt==0)
                        //  TabSTT2.setInhalt("aic_stammtyp",iVon);
                        //TabSTT2.setInhalt("Bezeichnung",g.TabStammtypen.getS(iPosS,"Bezeichnung"));
			//g.progInfo(TabSTT2.getS("kennung"));
			if (iVon>0 && (iCboStt>0 || TabSTT2.getI("aic_eigenschaft")!=0 || existsFormular(iVon)))
			{
                          //if(!bNodSttGeladen || TabStyle.isEmpty())  // 10.11.2003
					//LoadAbfrage_Style(iVon);
				//g.progInfo("nach LoadAbfrage_Style");
                                //g.fixInfo("vor TabGruppenFilter hinzu:"+CboStt.getSelectedAIC()+"/"+CboHS.getSelectedAIC()+"/"+iHS);
				if (iSttHS==0 && !TabGruppenFilter.existInhalt("int1",new Integer(iVon)) && (/*CboStt.getSelectedAIC()==0 ||*/ CboHS.getSelectedAIC()==0 || TabSTT2.size()>1))
				{
					/*g.TabStammtypen.posInhalt("Aic",new Integer(iVon));
					if (!((Boolean)g.TabStammtypen.getInhalt("Ende")).booleanValue() && g.TabStammtypen.getI("Darunter") != 0)
						iVon = g.TabStammtypen.getI("Darunter");*/
                                  boolean bFirstGF=true;
                                  int iVon2=iVon;
                                  while(iVon2 != 0)
                                  {
                                          int iPos=g.TabStammtypen.getPos("Aic",iVon2);
                                          if (g.existsSttS(iPos))
                                          {
                                            TabGruppenFilter.addInhalt("int1", new Integer(TabSTT2.getI("aic_stammtyp"))); // ausgehender Stammtyp	//
                                            TabGruppenFilter.addInhalt("int2", new Integer(g.TabStammtypen.getI(iPos,"Aic"))); // momentaner Stammtyp  //
                                            TabGruppenFilter.addInhalt("int3", new Integer(TabSTT2.getI("aic_eigenschaft"))); // momentane Eigenschaft//
                                            TabGruppenFilter.addInhalt("int4",new Integer( /*TabSTT2.getI("aic_stammtyp")==g.TabStammtypen.getI("Aic") ? 2:*/GruppenFilter.EIN)); // 0..ausgeblendet, 1..eingeblendet, 2..immer, 3..Gruppe //
                                            TabGruppenFilter.addInhalt("aic_abfrage", null);
                                            TabGruppenFilter.addInhalt("ans_aic_ansicht",bFirstGF ? null:new Integer(iAnsicht));
                                            bFirstGF=false;
                                            iAnsicht--;
                                            TabGruppenFilter.addInhalt("aic_ansicht",iAnsicht);
                                          }
                                          iVon2 = TabSTT2.getI("aic_eigenschaft")==0 ? 0:g.TabStammtypen.getI(iPos,"Darunter");
                                          //g.progInfo(g.TabStammtypen.getS("Bezeichnung")+":"+iVon);
                                  }
				}
				//g.progInfo("TabGruppenFilter");
				//if (g.Prog())
				//	TabGruppenFilter.showGrid("TabGruppenFilter");
				if (!bStammtypen)
				{
					Vector<String> VecBez=new Vector<String>();
                                        int iStt=iVon;//TabSTT2.getI("aic_stammtyp");
                                        //g.progInfo("Hauptschirm-Stammtypen: "+iStt+"/"+iSttRolle+" ->"+iRolle);
                                        int iPosR=iStt==iSttRolle ? g.TabRollen.getPos("aic",iRolle):-1;
					VecBez.addElement(iPosR>=0 ? Static.beiLeer(g.TabRollen.getS(iPosR,"Bezeichnung"),g.TabRollen.getS(iPosR,"Kennung"))
                                             : Static.beiLeer(g.TabStammtypen.getS(iPosS,"Bezeichnung"),g.TabStammtypen.getS(iPosS,"Kennung")));
					int iAnz=Gid.getNumColumns();
					if (iAnz>1)
						for(int i=iAnz;i>1;i--)
							VecBez.addElement(null);
					JCOutlinerFolderNode Nod = new JCOutlinerFolderNode(VecBez,NodStamm);
					setSTT_Icon(Nod,iStt,true,0);
					Vector<Integer> VecAIC = new Vector<Integer>();
					VecAIC.addElement(new Integer(0));
					VecAIC.addElement(new Integer(iStt));
					VecAIC.addElement(new Integer(0)/*TabSTT2.getInhalt("aic_eigenschaft")*/);
					Nod.setUserData(VecAIC);
                                        if (!bOffen && iCboStt==0 && TabSTT2.getAnzahlElemente(null)>1)
                                          Nod.setState(BWTEnum.FOLDER_CLOSED);
                                        if (bVoll)
                                          Aufloesung(Nod,iStt);
					//Rekursion(0,0,Nod,true);
					bNodStammGeladen = true;
					//g.progInfo("GidDarstellung");
					GidDarstellung();
                                        //TabGruppenFilter.showGrid("Gruppenfilter");
                                        if (iVL != ALLES)
                                        {
                                          int iAns = 0;
                                          int iSttFilter = 0;
                                          for (TabGruppenFilter.posInhalt("int1", iVon); TabGruppenFilter.getI("int1") == iVon; TabGruppenFilter.moveNext())
                                            if (TabGruppenFilter.getI("int4") > GruppenFilter.AUS /*&& TabGruppenFilter.getI("int4")<GruppenFilter.BEW*/)
                                              iAns = TabGruppenFilter.getI("ANS_AIC_ANSICHT");
                                          //g.progInfo("iAns="+iAns+"/"+iSttFilter);
                                          int iPos2=TabGruppenFilter.getPos("ANS_AIC_ANSICHT",iAns);
                                          int iSttL=iPos2<0 ? 0:TabGruppenFilter.getI(iPos2,"int2");
                                          while (iAns != 0 && iSttFilter==0)
                                            if (TabGruppenFilter.posInhalt("AIC_ANSICHT", iAns))
                                            {
                                              iAns = TabGruppenFilter.getI("ANS_AIC_ANSICHT");
                                              if (TabGruppenFilter.getI("int4") > GruppenFilter.AUS)
                                                iSttFilter = TabGruppenFilter.getI("int2");
                                            }

                                          //iSttFilter=4;
                                          if (iVL == VOLLE)
                                            Ausblenden(Nod, iSttFilter, VOLLE,iSttL);
                                          else if (iVL == LEERE)
                                            if (iSttL>0)
                                              VolleAusblenden(Nod,iSttL);
                                            else
                                              Ausblenden(Nod, iSttFilter, LEERE,iSttL);
                                          else if (iVL == BEIDE)
                                            Ausblenden(Nod, iSttFilter, BEIDE,iSttL);
                                          //g.progInfo("Stammtyp=" + iSttFilter);
                                        }
				}
				//if (iAnzahl==0)
				//	fuelleHierachie(TabSTT2,NodStamm,false);
			}
                    }
                }
                CheckOldGF();
                if (!bVoll)
                  RefreshGrid();
                //new Tabellenspeicher(g,TabGruppenFilter).showGrid("Nachher2");
		///Abfrage.ClearVecEigPer(g);
		//Gid.setRootNode(NodStamm);
		bNodSttGeladen = true;
		//TabSTT2 = null; // weg 25.1.2007
                if (!sFilterShow.equals(""))
                  sFilterShow+="]";
		setTitle(sTitel+sFilterShow+" - "+g.getVonBis("dd.MM.yyyy",true));
		if (NodStammMom != null)
		{
			/*if(!isVisible())
			{
				show();
				g.progInfo("!!! show !!!");
				Static.sleep(1000);
				Static.makeVisible(Gid,NodStammMom);
				Static.sleep(1000);
			}*/
			Static.makeVisible(Gid,NodStammMom);
			//Gid.selectNode(NodStammMom,null);
			//g.printInfo("selectNode:"+NodStammMom);
			//Gid_itemStateChanged();
		}
		setCursor(CurVorher);
    }//LadeHierachie

    /*private void EinzelneAusblenden(JCOutlinerFolderNode Nod,int iStt)
    {
      g.progInfo("EinzelneAusblenden:"+iStt+"/"+Nod);
    }*/

    private int Anzahl(Vector Vec)
    {
      Vector<Object> Vec2 = new Vector<Object>();
      for(int i=0;i<Vec.size();i++)
      {
        Integer Int=(Integer)((Vector)((JCOutlinerNode)Vec.elementAt(i)).getUserData()).elementAt(1);
        if(!Vec2.contains(Int))
          Vec2.addElement(Int);
      }
      return Vec2.size();
    }

    private void Ausblenden(JCOutlinerFolderNode Nod,int iStt,int iArt,int iStt2)
    {
      int iStt1=Sort.geti(Nod.getParent().getUserData(),1);
      Vector Vec = Nod.getChildren();
      //g.testInfo("Ausblenden:"+iStt+"/"+iStt1+"/"+iStt2+"/"+Nod+"->"+(Vec==null ?"null":Vec.size()));
      int iSttM=Sort.geti(Nod.getUserData(),1);
      if(Vec != null && Vec.size()>0)
      {
        //g.progInfo("prüfe "+Nod+":"+Vec.size());
        boolean bStt=iStt == iSttM;
        if(!bStt || iStt1==0 && (iArt==VOLLE || iArt==BEIDE) || iArt==LEERE)
         for(int i = Vec.size()-1; i >-1; i--)
         {
          JCOutlinerFolderNode NodNeu = (JCOutlinerFolderNode)Vec.elementAt(i);
          //g.progInfo(i+":"+iStt+"/"+((Integer)((Vector)NodNeu.getUserData()).elementAt(1)).intValue());
          //if(iStt != ((Integer)((Vector)NodNeu.getUserData()).elementAt(1)).intValue())
            Ausblenden(NodNeu,iStt,iArt,iStt2);
        }
        if (Vec.size()==0 && (iArt==VOLLE || iArt==BEIDE)/* && Nod.getLevel()>1*/|| bStt && iArt==LEERE && Vec.size()>0 || iArt==BEIDE && bStt && Anzahl(Vec)<2)
        {
          g.testInfo("1: entferne "+Nod+" ("+Nod.getLevel()+") von "+Nod.getParent());
          if (Nod.getLevel()>1)
            Nod.getParent().removeChild(Nod);
        }
      }
      else if (iSttM != iStt2 && (iArt==VOLLE || iArt==BEIDE))// || iSttM==iStt2 && iArt==LEERE)
      {
        //g.testInfo("2: entferne "+Nod+" ("+Nod.getLevel()+") von "+Nod.getParent());
        if (Nod.getLevel()>1)
          Nod.getParent().removeChild(Nod);
      }
    }

    private void VolleAusblenden(JCOutlinerFolderNode Nod,int iStt)
    {
      //g.progInfo("VolleAusblenden:"+Nod+"/"+iStt);
      Vector Vec = Nod.getChildren();
      boolean bRemove=false;
      if (Vec != null && Vec.size()>0)
      {
        //g.progInfo("prüfe "+Nod+":"+Vec.size());
        for(int i = Vec.size()-1; i >-1; i--)
        {
          JCOutlinerFolderNode NodNeu = (JCOutlinerFolderNode)Vec.elementAt(i);
          //g.progInfo(i+":"+iStt+"/"+((Integer)((Vector)NodNeu.getUserData()).elementAt(1)).intValue());
          if(iStt != ((Integer)((Vector)NodNeu.getUserData()).elementAt(1)).intValue())
            VolleAusblenden(NodNeu,iStt);
          else
            bRemove=true;
        }
        if (bRemove || Vec.size()==0 && Nod.getLevel()>1)
        {
          Nod.getParent().removeChild(Nod);
          //g.progInfo("entferne "+Nod);
        }
      }
      /*else
      {
        //g.progInfo("entferne "+Nod+" von "+Nod.getParent());
        //Nod.getParent().removeChild(Nod);

      }*/
    }

    private String getM(int iStt)
    {
    	if (iStt<0)
    		iStt=g.RolleToStt(-iStt);
      int iPos=g.TabStammtypen.getPos("Aic",iStt);
      if (iPos>=0)
        if ((g.TabStammtypen.getI(iPos,"bits")&Global.cstCopy)>0)
          return g.getCopyMandanten(false);
        else
          return g.getReadMandanten();
      else
      {
    	  g.printError("Stammtyp "+iStt+" nicht gefunden",iBegriff);
    	  return " and aic_mandant=-1 ";
        //return "getM_Fehler";
      }
    }

    private String getRollen(int iSTT,int iRolle2)
    {
//      g.fixtestError("getRollen:"+iSTT+"/"+iSttRolle+" | "+iRolle2+"/"+iRolle+" | "+(Abf.iBits & Abfrage.cstSubrollen));
      if (iSTT<0 || iSttRolle==iSTT || iSttRolle==0 && g.RolleToStt(iRolle2)==iSTT)
      {
    	  if (iSTT<0)
    	  {
    		  g.fixtestError("getRollen: ändere Rolle "+iRolle2+" auf "+(-iSTT));
    		  iRolle2=-iSTT;
    		  
    	  }
        Vector VecRolle = (Abf.iBits & Abfrage.cstSubrollen) > 0 ? g.getVecRolle2(iRolle, false) : null;
        return VecRolle == null ? " and aic_Rolle=" + iRolle2 : " and aic_rolle" + Static.SQL_in(VecRolle);
      }
      else
        return " and aic_rolle is null";
    }

	private void Aufloesung(JCOutlinerFolderNode NodVorgaenger,int iSttVon)
	{
          if (g.Clock2()) clock=new Clock(g,this);
          long lClock3 = Clock.startClock(clock);
		TabKnoten = new Tabellenspeicher(g,new String[]{"Stamm","Knoten"});
		TabGruppierung = new Tabellenspeicher(g,new String[] {"Kennung","Node"});
		//Tabellenspeicher Tab;
		//JCOutlinerFolderNode Nod;
		//TabGruppenFilter.showGrid("TabGruppenFilter");
		//TabGruppenFilter.showGrid("TabGruppenFilter");
                SQL Qry=new SQL(g);
		for(TabGruppenFilter.posInhalt("int1",iSttVon);!TabGruppenFilter.eof() && TabGruppenFilter.getI("int1")==iSttVon;TabGruppenFilter.moveNext())
		{
			//g.progInfo("Stt="+TabGruppenFilter.getI("int2"));
			int iSize = VecEigenschaften.size();

			for (;TabGruppenFilter.getI("int4")==GruppenFilter.GRUPPE || TabGruppenFilter.getI("int4")==GruppenFilter.BEWGRUPPE;TabGruppenFilter.moveNext())
				if (!VecEigenschaften.contains(TabGruppenFilter.getInhalt("int3")))
					VecEigenschaften.addElement(TabGruppenFilter.getInhalt("int3"));

			int iSTT=TabGruppenFilter.getI("int2");
			TabGruppenFilter.push();
			//String sFilter= TabAbfrage.posInhalt("Stammtyp",iSTT)?TabAbfrage.getInhalt("Abfrage")==null?"":
                        String sFilter=TabGruppenFilter.isNull("aic_abfrage")?"":
				Abf.Check(VecEigenschaften,((ShowAbfrage)TabGruppenFilter.getInhalt("aic_abfrage")).NodBed.getChildren(),"",false/*iSTT<0?"p2.aic_bew_pool,bew_stamm.aic_stamm,bezeichnung":TabGruppenFilter.getI("int1")==iSTT?"aic_stamm":"p2.aic_stamm,sta_aic_stamm"*/);
			// ! ! !
			//String sFilter="";

			//g.progInfo("Stt="+iSTT+", Eig="+iEigenschaft_Mom+",Filter="+sFilter);
			boolean bBed=!sFilter.equals("");
                        if (bBed)
                        {
                          if (sFilterShow.equals(""))
                            sFilterShow=" [" + g.getBegriffS("Show", "Filter") + ": ";
                          else
                            sFilterShow+=", ";
                          sFilterShow+=iSTT>0?g.TabStammtypen.getBezeichnungS(iSTT):g.TabErfassungstypen.getBezeichnungS(-iSTT);
                        }
			//bFilter = bFilter || bBed;
			//g.progInfo("vorher:Stt"+iSTT+"VecEigenschaften="+VecEigenschaften);
			//TabKnoten.showGrid("TabKnoten");
			//java.util.Vector VecBS=g.SQL_CboStt(iSTT);
			//g.debugInfo(iSTT+":"+TabGruppenFilter.getI("int4")+"/"+VecBS);
                        //g.progInfo("Aufloesung");
                        String sStammview=(Abf.iBits&Abfrage.cstKeinStammZeitraum)>0 ? "stammview2":"stammview";
                        String sSelect="select * from (select "+(g.Oracle() ? "/*+ RULE */ ":"");
                        long lClock4 = Static.get_ms();
                        g.progInfo("Rolle="+iRolle+"/"+Abf.iRolle);
                        if (iRolle>0)
                          Abf.iRolle=iRolle;
			if (TabGruppenFilter.getI("int1")==iSTT)
			{
                          //g.progInfo("Aufloesung int1=iStt");
				//if (VecBS!=null)
				//	g.debugInfo("<"+VecBS.elementAt(2)+">");
				//boolean bAllBS=VecBS==null || !VecBS.elementAt(2).equals("");
				iEigenschaft_Mom=TabGruppenFilter.getI("int3");
                                Abf.sAnfang=null;
                                Vector VecStamm=g.getVecStamm(iBegriff,iSTT);
                                int iSttS=Abf.iStt;
                                Abf.iStt=iSTT;
                                g.defInfo2("Hole Anfang: "+g.getBez(iSTT));
                                Tabellenspeicher Tab=new Tabellenspeicher(g,Abf.checkJoker(sSelect+Abf.ZusaetzlicheSpalten(VecEigenschaften,Formular.Stamm,bBed)+",bezeichnung"+
                                    (g.Def()?",(select Kennung from mandant where aic_mandant=p2.aic_mandant) MK":"")+
                                    " from "+sStammview+" p2 where aic_stammtyp="+iSTT+getRollen(iSTT,Abf.iRolle)+getM(iSTT)+Abf.Ebenen()+sFilter+sSortiert),true);
                                Abf.checkHierarchie(TabSpalten,Tab);
                                if (Abfrage.is(Abf.iBits,Abfrage.cstCalc))
                                  Abf.checkCalc(Tab,TabSpalten);
				for (;!Tab.eof();Tab.moveNext())
                                  if ((VecStamm == null || VecStamm.contains(Tab.getInhalt("AIC_Stamm"))) && (iSTT!=g.iSttANR || bAustritt || ShowAbfrage.istDa(g,Tab.getI("AIC_Stamm"))))
                                    fuelleHierachie(iSTT,Tab,NodVorgaenger);
				Abf.iStt=iSttS;
			}
			else if (TabGruppenFilter.getI("int4")<2 && !TabKnoten.isEmpty())
			{
			  Vector Vec=TabKnoten.getVecSpalte("Stamm");
			  if (Vec.size()<10000 || g.ASA() || g.MySQL())
			  {
                            Abf.sAnfang="sta_aic_stamm,p2.aic_stamm";
                            int iSttS=Abf.iStt;
                            if (iSTT<0)
                            	iSTT=g.RolleToStt(-iSTT);
                            Abf.iStt=iSTT;
				String s=sSelect+Abf.ZusaetzlicheSpalten(VecEigenschaften,Formular.Stamm,bBed)+",bezeichnung"+(g.Def()?",(select Kennung from mandant where aic_mandant=p2.aic_mandant) MK":"")+
					" from poolview join "+sStammview+" p2 on p2.aic_stamm=poolview.aic_stamm where aic_stammtyp="+iSTT+getRollen(iSTT,Abf.iRolle)+
					" and "+g.in("sta_aic_stamm",Vec)+"and aic_eigenschaft="+iEigenschaft_Mom+getM(iSTT)+Abf.Ebenen()+sFilter+sSortiert;
				Abf.iStt=iSttS;
				//if (iSTT==4)
				//	g.progInfo(s);
                                g.defInfo2("Hole weiter: "+g.getBez(iSTT)+" mit "+Vec.size());
				Tabellenspeicher Tab=new Tabellenspeicher(g,Abf.checkJoker(s),true);
                                long lClock5 = Static.get_ms();
				Abf.checkHierarchie(TabSpalten,Tab);
                                g.clockInfo2("checkHierarchie "+g.getBez(iSTT),lClock5);
                                if (Abfrage.is(Abf.iBits,Abfrage.cstCalc))
                                  Abf.checkCalc(Tab,TabSpalten);
                                Vector VecStamm=g.getVecStamm(iBegriff,iSTT);
				for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
					if(TabKnoten.posInhalt("Stamm",Tab.getI("STA_AIC_Stamm")) && g.existsStt(iSTT)
                                            && (VecStamm == null || VecStamm.contains(Tab.getInhalt("AIC_Stamm"))) && (iSTT!=g.iSttANR || bAustritt || ShowAbfrage.istDa(g,Tab.getI("AIC_Stamm"))))
                                           fuelleHierachie(iSTT, Tab, (JCOutlinerFolderNode)TabKnoten.getInhalt("Knoten"));
			  }
			  else
			    g.printError(g.TabStammtypen.getBezeichnungS(TabGruppenFilter.getI("int2"))+" nicht auflösbar da darüber "+Vec.size()+" Werte");
			}
			else if ((TabGruppenFilter.getI("int4")==GruppenFilter.BEW || TabGruppenFilter.getI("int4")==GruppenFilter.BEWBEW) && !TabKnoten.isEmpty())
			{
                          boolean bBewBew=TabGruppenFilter.getI("int4")==GruppenFilter.BEWBEW;
                          //boolean bANR=(Abf.cstANR&Abf.iBits)>0;
                          boolean bKZR=(Abf.iBits&Abfrage.cstKeinZeitraum)>0 || (Abf.iBits&Abfrage.cstLDATE)>0;
                          //boolean bDel=(Abf.iBits&Abf.cstEntfernte)>0;
                          Abf.sAnfang="p2.aic_bew_pool,"+(bDel?"pro_aic_protokoll,":"")+(bBewBew?"-p2.bew2_aic_bew_pool ":/*bANR?"anr ":*/"")+"aic_stamm,gueltig Bezeichnung"+(g.Def()?",(select Kennung from mandant where aic_mandant=p2.aic_mandant) MK":"");
				String s=sSelect+Abf.ZusaetzlicheSpalten(VecEigenschaften,Formular.Bewegung,bBed)+" from "+
                                    (bDel ? bKZR ?"Bew_Pool":"BewView3": bKZR ?"BewView2":"BewView")+" p2"+(/*bANR ||*/ bBewBew ? "":g.join("bew_stamm","p2","bew_pool"))+" where aic_bewegungstyp="+(-TabGruppenFilter.getI("int2"))+
					(/*bANR ||*/ bBewBew ?"":" and aic_eigenschaft="+TabGruppenFilter.getI("int3"))+" and "+
                                        (bBewBew ?g.in("bew2_aic_bew_pool",Static.getMinus(TabKnoten.getVecSpalte("Stamm"))):g.in(/*bANR ? "anr":*/"aic_stamm",TabKnoten.getVecSpalte("Stamm")))+
                                        ((Abf.iBits&Abfrage.cstLDATE)>0?g.getLDATE():"")+
                                        (bDel?" and (pro_aic_protokoll is null or pro_aic_protokoll>=1"/*+g.getFirstProt()*/+" and (select count(aic_bew_pool) from bew_pool where bew_aic_bew_pool=p2.aic_bew_pool)=0)":"")+
                                        Abf.Ebenen()+sFilter+sSortiert;
				//g.progInfo(s);
                                g.defInfo2("Hole Bew: "+g.getBez(TabGruppenFilter.getI("int2")));
                                Tabellenspeicher Tab=new Tabellenspeicher(g,Abf.checkJoker(s),true);
                                //if (Abfrage.is(Abf.iBits,Abfrage.cstCalc))
                                  Abf.checkCalc(Tab,TabSpalten);
                              //g.progInfo("Aufloesung nach holen");
                              //if (g.Prog() && !Tab.isEmpty())
                                //Tab.showGrid("Tab");
                              //TabKnoten.showGrid("TabKnoten");
				for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
					if(TabKnoten.posInhalt("Stamm",Tab.getI("AIC_Stamm")))
                                          fuelleHierachie(iSTT, Tab, (JCOutlinerFolderNode)TabKnoten.getInhalt("Knoten"));
				//Tab.showGrid("Bew");
				//TabKnoten.showGrid("Knoten");
			}
                        else if (TabGruppenFilter.getI("int4")==GruppenFilter.MF || TabGruppenFilter.getI("int4")==GruppenFilter.SUB)
                        {
                          Abf.sAnfang="sta_aic_stamm,p2.aic_stamm";
                          boolean bMF=TabGruppenFilter.getI("int4")==GruppenFilter.MF;
                          String sStamm=bMF ? "AIC_Stamm":"STA_AIC_Stamm";
                          iEigenschaft_Mom=TabGruppenFilter.getI("int3");
                          Vector Vec=TabKnoten.getVecSpalte("Stamm");
                          String s=sSelect+Abf.ZusaetzlicheSpalten(VecEigenschaften,Formular.Stamm,bBed)+",bezeichnung"+(bMF ? ",poolview.aic_stamm MF":"")+
                                        " from poolview join "+sStammview+" p2 on p2.aic_stamm=poolview."+(bMF ? "STA_AIC_Stamm":"AIC_Stamm")+" where aic_stammtyp="+iSTT+getRollen(iSTT,Abf.iRolle)+
                                        " and "+g.in("poolview."+sStamm,Vec)+"and aic_eigenschaft="+iEigenschaft_Mom+getM(iSTT)+Abf.Ebenen()+sFilter+sSortiert;
                          g.defInfo2("Hole Sub: "+g.getBez(iSTT)+" mit "+Vec.size());
                          Tabellenspeicher Tab=new Tabellenspeicher(g,Abf.checkJoker(s),true);
                          //Tab.showGrid("Mehrfach/Sub");
                          Abf.checkCalc(Tab,TabSpalten);
                          for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                            if(TabKnoten.posInhalt("Stamm",Tab.getI(bMF?"MF":sStamm)))
                              fuelleHierachie(iSTT, Tab, (JCOutlinerFolderNode)TabKnoten.getInhalt("Knoten"));

                        }
                        Clock.add(clock,"Ebene",g.getBez(iSTT),lClock4);
                        //g.progInfo("Aufloesung pop");
			TabGruppenFilter.pop();
			//g.progInfo("Stt"+iSTT+"VecEigenschaften="+VecEigenschaften);
			VecEigenschaften.setSize(iSize);

		}
                Qry.free();
		//g.progInfo("Gruppenfilter fertig");
                Clock.showClock(g,clock,sTitel+" / "+CboHS.getSelectedBezeichnung(), lClock3);
	}

	private void EnableButtons()
	{
		//g.fixtestError("HS.EnableButtons");
		JCOutlinerNode NodFocus = Gid.getSelectedNode();
		int iLevel = NodFocus == null ? -1 : NodFocus.getLevel();
                //String sTyp=CboTyp.getSelectedKennung();
		//int iFom = CboFormular.getSelectedAIC();

		//BtnNeu.setEnabled(iLevel > 0 &&  (sTyp.equals("Stammtyp") || iFom > 0 && sTyp.equals("Bewegungstyp")));
                //boolean bBearbeiten=iLevel > 0 && iSTT_Mom != 0;// && (bStdForm || iFom>0 || g.Def() && iSTT_Mom>0);
		//BtnEdit.setEnabled(bBearbeiten); //iStamm_Mom > 0 && (sTyp.equals("Stammtyp") || iFom > 0 && sTyp.equals("Bewegungstyp")));
		//MnuBearbeiten.setEnabled(bBearbeiten);
		//BtnImport.setEnabled((iEnabled&4)>0 && iSTT_Mom > 0);
		BtnFilter.setEnabled(iLevel > 0);
		//g.progInfo("EnableButtons"+iLevel+"/"+NodFocus.getUserData());
		BtnGruppenFilter.setEnabled((iEnabled&2)>0 && iLevel > 0 && ((Integer)((Vector)NodFocus.getUserData()).elementAt(1)).intValue()>0 && TabGruppenFilter.size()>0);
        BtnAnsicht.setEnabled(BtnGruppenFilter.isEnabled());
		BtnSpaltenDef.setEnabled(iLevel > 0);
                //g.progInfo("iLevel="+iLevel+"-> BtnSpaltenDef="+BtnSpaltenDef.isEnabled());
		//BtnExport.setEnabled(iStamm_Mom > 0);
		MnuExport.setEnabled((iEnabled&4)>0 && bStamm && iStamm_Mom > 0);
		BtnDruckHS.setEnabled((iEnabled&1)>0 && !bStammtypen && iSTT_Mom != 0);//bStamm && iStamm_Mom > 0);
                MnuEMail.setVisible(iLevel>0 && EingabeFormular.getEMail((Vector)NodFocus.getLabel())!=null);
                MnuGPS.setVisible(iLevel>0 && EingabeFormular.getGPS((Vector)NodFocus.getLabel())!=null);
                MnuBild.setVisible(iLevel>0 && EingabeFormular.getBild((Vector)NodFocus.getLabel())!=null);
        if (g.Def() && CbxWeb!=null)
        {
        	boolean bWeb=CbxWeb.isSelected();
        	if (bWeb)
        	{
        		BtnFilter.setEnabled(false);
        		BtnSpaltenDef.setEnabled(false);
        	}
        	CboProg.setEnabled(bWeb);
        	if (BtnAbfrage != null) BtnAbfrage.setEnabled(bWeb);
            if (BtnRefresh2 != null)
            	BtnRefresh2.setEnabled(!bWeb);
        }
		//MnuDruck.setEnabled((iEnabled&1)>0 && bStamm && iStamm_Mom > 0);
		//BtnTable.setEnabled(g.Def() && sTyp.equals("Abfrage"));

	}

        /*private void Enable2()
	{
          //iEnable2++;
          //g.progInfo("Enable2:"+iEnable2);
          boolean bNO=((int)CboFormular.getSelectedFaktor()&Formular.cstNurOrdner)>0;
          if (bNO)
            CbxNurOrdner.setSelected(true);
          else if (!bStamm)
            CbxNurOrdner.setSelected(false);
          CbxNurOrdner.setEnabled(!bNO && bStamm);
          BtnEditOk.setEnabled((bStdForm || CboFormular.getSelectedAIC()>0 || g.Def() && iSTT_Mom>0) && (!bNO  || bStamm && CbxNurOrdner.isSelected()));
        }*/

	private void Gid_itemStateChanged()
	{
		if (bGidChange)
		{
//			g.fixtestError("Gid_itemStateChanged");
                        JCOutlinerNode NodMom = Gid.getSelectedNode();

                        if (!bStammtypen && NodMom != null && NodMom.getLevel()>1)
                        {
                          Object[] Nod=Gid.getSelectedNodes();
                          int iStt2=0;
                          Vector<Object> VecS=new Vector<Object>();
                          for(int i=0;i<Nod.length;i++)
                          {
                            Vector VecUD=(Vector)((JCOutlinerNode)Nod[i]).getUserData();
                            if (iStt2==0)
                              iStt2=((Integer)VecUD.elementAt(1)).intValue();
                            //int iStamm2=((Integer)VecUD.elementAt(0)).intValue();
                            if (iStt2==((Integer)VecUD.elementAt(1)).intValue())
                                VecS.addElement(VecUD.elementAt(0));
                            //g.progInfo("VecUD"+i+"="+VecUD);
                          }
                          g.progInfo("VecS="+VecS);
                          g.setSyncStamm(iStt2,VecS);
                        }
                        //g.progInfo(""+NodMom);
			if (NodMom!=NodVorher)
			{
				int iLevel=NodMom == null ? -1 : NodMom.getLevel();
				if (iLevel > 0)
				{
					//g.progInfo(""+NodMom.getUserData());
					int iStamm = ((Integer)((Vector)NodMom.getUserData()).elementAt(0)).intValue();
                                        bStamm = iStamm != 0;
                                        if (bStamm)
                                          iStamm_Mom = iStamm;
					iSTT_Mom = ((Integer)((Vector)NodMom.getUserData()).elementAt(1)).intValue();
					//sBezeichnung_Mom = CbxStammtypen.isSelected() || iLevel==1 ?"":""+((Vector)NodMom.getLabel()).elementAt(0);
					//sBezeichnung_Mom = Sort.gets(NodMom.getLabel());// instanceof Vector ? Static.sLeer+((Vector)NodMom.getLabel()).elementAt(0):NodMom.getLabel().toString();
					//g.debugInfo("sBezeichnung_Mom="+sBezeichnung_Mom);
					if (iSTT_Mom>0)
					{
					  int iPos=g.TabStammtypen.getPos("Aic",iSTT_Mom);
					  //bEnde = iLevel > 1 && (g.TabStammtypen.getI("bits")&g.cstEnde)>0;
					  bStdForm = iLevel > 0 && (g.TabStammtypen.getI(iPos,"bits")&Global.cstStdFormular)>0;
					}
					else
					  bStdForm=false;
                                        if (!bStammtypen)
                                        {
                                          JCOutlinerNode NodNeu = NodMom;
                                          while(NodNeu.getLevel() > 1) {
                                            int iSTT_Mom2 = ((Integer)((Vector)NodNeu.getUserData()).elementAt(1)).intValue();
                                            int iStamm2 = ((Integer)((Vector)NodNeu.getUserData()).elementAt(0)).intValue();
                                            g.setSyncStamm(iSTT_Mom2, iStamm2,iRolle);
                                            NodNeu = NodNeu.getParent();
                                          }
                                        }
					//g.progInfo(""+iStamm_Mom+'/'+iSTT_Mom+'/'+bEnde);
					//g.progInfo("Label="+NodMom.getLabel());
					//g.progInfo("Userdata="+NodMom.getUserData());
				}
				else
				{
					//iStamm_Mom = 0;
                                        bStamm = false;
					iSTT_Mom = 0;
					//bEnde = false;
					bStdForm = false;
				}
				//g.progInfo(sBezeichnung_Mom+":"+iStamm_Mom+'/'+iSTT_Mom+'/'+bEnde);
				//g.progInfo("FillCboErfassung");
				if (iSTT_Mom != iSTT_Last || bStammtypen!=bSttOld)
				{
                                  bSttOld=bStammtypen;
                                  //if (MnuBearbeiten != null)
                                  //  FillCboFormular2();
					if(TabAktFormular.posInhalt("Stt",iSTT_Mom)/* && TabAktFormular.getI("Formular")!=0*/)
					{
						//CboTyp.setSelectedAIC(TabAktFormular.getI("Typ"));
                                                iForm=TabAktFormular.getI("Formular");
                                                if (iForm>0)
                                                {
                                                  int iPos=g.TabBegriffe.getPos("Aic", iForm);
                                                  if (iPos>=0)
                                                  {
                                                    MnuLastForm.setText(g.getBegBez3(iPos));
                                                    g.setTooltip(g.TabBegriffe.getS(iPos,"Tooltip"),MnuLastForm);
                                                    //MnuLastForm.setToolTipText(g.TabBegriffe.getS(iPos, "Info"));
                                                  }
                                                  else
                                                    iForm=0;
                                                }
                                                //MnuLastForm.setVisible(iForm>0 && !TabAktFormular.isNull("Tab") && ((Tabellenspeicher)TabAktFormular.getInhalt("Tab")).getVecSpalte("aic_begriff").contains(new Integer(iForm)));
						//CboFormular.setSelectedAIC(TabAktFormular.getI("Formular"));
					}
                                        //else
                                        //  MnuLastForm.setVisible(false);
                                        if (MnuBearbeiten != null)
                                          FillCboFormular2();
					iSTT_Last =iSTT_Mom;
				}
				if (MnuExport!=null)
					EnableButtons();
				NodVorher = NodMom;
				/*if (bAutoDarstellen && iSTT_Mom>0)
				{
					DetailAufruf(false,0);
					//toFront();
					//g.progInfo("HS.toFront");
					//Gid.requestFocus();
				}*/
			}
			//g.debugInfo(sBezeichnung_Mom+"/"+iSTT_Mom+"/"+iStamm_Mom);
		}
	}
	
//	public static void freeFX(Global g)
//	{
//		for (int iPos=0;iPos<g.TabFormulare.size();iPos++)
//	  	  {
//	  		  Object Obj = g.TabFormulare.getInhalt("Formular",iPos);
//	  		  boolean b=Obj instanceof FormularFX && Obj != Static.FomStartFX;
//	  		  g.fixtestInfo((b?"free:":"see:")+g.TabFormulare.getI(iPos,"Aic")+"/"+Static.print(Obj));
//	  		  if (b)
//	  		  {
//	  			  ((FormularFX)Obj).free();
//	  			  iPos--;
//	  		  }
//	  	  }
////	  	  CheckFX.Tab=null;
//	}
	
//	public static void freeAll(Global g,boolean bJavaFX)
//	{
////		g.fixtestInfo("freeAll "+(bJavaFX ? "JavaFX":"Swing"));
////		if (bJavaFX)
////			SwingUtilities.invokeLater(new Runnable() {
////			    @Override 
////			    public void run() {
////			    	freeAllSwing(g);
////			    }
////			});
////		else
//			freeAllSwing(g);
////		if (bJavaFX)
////			freeAllFX(g);
////		else
////			Platform.runLater(() -> {
////				freeAllFX(g);
////	          });
//	}

        public static void freeAllSwing(Global g)
        {          
          GruppenFilter.free();
          All_Unlimited.Print.DruckFrage.free();
          Zeitraum.free(g);
          DefAbfrage.free();
          Darstellung.free();
          Schrift.free();
          CleanEig.free();
          Import.free();
          SyncStamm.free();
          DefModell.free();
          PersEinstellungen.free();
          Systemeinstellung.free();
          Updateeinstellung.free();
          Feiertage.free();
          Tsearch.free();
          Individuell.free();
//          ISQL.free();
          DefFormular.free();
          //DarstellungFX.free();
          //TCalc.free();
          DefModul.free();
          DefEigenschaft.free();
          Sprache2.free();
          DefTerminal.free();
          Reinigung.free();
          Prozesse.free();
          Lizenz.free();
          Security.free();
          UserManager.free();
          Zuordnung2.free();
          Berechtigung.free();
          Logging.free();
          CleanComputer.free();
          Versionsupdate.free();
          DefAbfrage2.free();
          Periodensperre.free();
          Abschluss.free();
          DefBew.free();
          DefStt.free();
          DefHtml.free();
          g.clearSync();
          Abfrage.free(g);
          Seitenlayout.free();
          Verboten.free();   
          DefAufgabe.free();
          DefPlanung.free();
          Druckdefinition.free();
//          Versionsupdate.free();
          JavaKonsole.setFenster(g);
          //JavaKonsole.free();
        }
        
//        public static void freeAllFX(Global g)
//        {
//    	  g.fixtestError("freeAllFX: Java FX wird nicht mehr unterstützt");
//        }

        public String toString()
        {
          return "Hauptschirm - "+g.TabBegriffe.getBezeichnungS(iBegriff);
        }

	/*private void BtnDeleteClicked()
	{
		JCOutlinerNode NodFocus = (JCOutlinerNode) Gid.getSelectedNode();
		int iStamm = ((Integer)((Vector)NodFocus.getUserData()).elementAt(0)).intValue();
		if (EingabeFormular.LoescheDaten(g,this,Formular.Stamm,iStamm,""+((Vector)NodFocus.getLabel()).elementAt(0)))
		{
			JCOutlinerFolderNode NodParent = NodFocus.getParent();
			NodParent.removeChild(NodFocus);
			Gid.folderChanged(NodParent);
		}
	}*/
}

    /*class NimbusCheckIconTest {
   public JMenuBar createMenuBar() {
     JCheckBoxMenuItem cbmi = new JCheckBoxMenuItem("checkIcon test");
     UIDefaults d = new UIDefaults();
     d.put("CheckBoxMenuItem[Enabled].checkIconPainter", new MyCheckBoxMenuItemPainter(MyCheckBoxMenuItemPainter.CHECKICON_ENABLED));
     d.put("CheckBoxMenuItem[MouseOver].checkIconPainter",new MyCheckBoxMenuItemPainter(MyCheckBoxMenuItemPainter.CHECKICON_MOUSEOVER));
     d.put("CheckBoxMenuItem[Enabled+Selected].checkIconPainter",new MyCheckBoxMenuItemPainter(MyCheckBoxMenuItemPainter.CHECKICON_ENABLED_SELECTED));
     d.put("CheckBoxMenuItem[MouseOver+Selected].checkIconPainter",new MyCheckBoxMenuItemPainter(MyCheckBoxMenuItemPainter.CHECKICON_SELECTED_MOUSEOVER));
     cbmi.putClientProperty("Nimbus.Overrides", d);
     cbmi.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
     JMenuBar menuBar = new JMenuBar();
     JMenu menu = new JMenu("A Menu");
     menuBar.add(menu);
     menu.add(new JCheckBoxMenuItem("default"));
     menu.add(cbmi);
     menuBar.add(menu);
     return menuBar;
   }
   public Container createContentPane() {
     JPanel contentPane = new JPanel(new BorderLayout());
     contentPane.setOpaque(true);
     contentPane.add(new JScrollPane(new JTextArea()));
     return contentPane;
   }
   private static void createAndShowGUI() {
     try{
       for(UIManager.LookAndFeelInfo laf: UIManager.getInstalledLookAndFeels())
         if("Nimbus".equals(laf.getName()))
           UIManager.setLookAndFeel(laf.getClassName());
     }catch(Exception e) {
       e.printStackTrace();
     }
     NimbusCheckIconTest demo = new NimbusCheckIconTest();
     JFrame f = new JFrame();
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     f.setJMenuBar(demo.createMenuBar());
     f.setContentPane(demo.createContentPane());
     f.setSize(320, 240);
     f.setLocationRelativeTo(null);
     f.setVisible(true);
   }
   public static void main(String[] args) {
     EventQueue.invokeLater(new Runnable() {
       public void run() { createAndShowGUI(); }
     });
   }
 }*/

    //copy: CheckBoxMenuItemPainter.java
    /*class MyCheckBoxMenuItemPainter extends AbstractRegionPainter {
      static final int CHECKICON_ENABLED_SELECTED   = 6;
      static final int CHECKICON_SELECTED_MOUSEOVER = 7;
      static final int CHECKICON_ENABLED            = 8;
      static final int CHECKICON_MOUSEOVER          = 9;
      private int state;
      private PaintContext ctx;
      public MyCheckBoxMenuItemPainter(int state) {
        super();
        this.state = state;
        this.ctx = new AbstractRegionPainter.PaintContext(
          new Insets(5, 5, 5, 5), new Dimension(9, 10), false, null, 1.0, 1.0);
      }
      @Override
      protected void doPaint(Graphics2D g, JComponent c,
                   int width, int height, Object[] eckey) {
        switch(state) {
          case CHECKICON_ENABLED:
            paintcheckIconEnabled(g);              break;
          case CHECKICON_MOUSEOVER:
            paintcheckIconMouseOver(g);            break;
          case CHECKICON_ENABLED_SELECTED:
            paintcheckIconEnabledAndSelected(g);   break;
          case CHECKICON_SELECTED_MOUSEOVER:
            paintcheckIconSelectedAndMouseOver(g); break;
        }
      }
      @Override
      protected final PaintContext getPaintContext() {
        return ctx;
      }
      private void paintcheckIconEnabled(Graphics2D g) {
          g.setPaint(Color.GREEN);
          g.drawOval( 0, 0, 10, 10 );
      }
      private void paintcheckIconMouseOver(Graphics2D g) {
          g.setPaint(Color.PINK);
          g.drawOval( 0, 0, 10, 10 );
      }
      private void paintcheckIconEnabledAndSelected(Graphics2D g) {
        g.setPaint(Color.ORANGE);
        g.fillOval( 0, 0, 10, 10 );
      }
      private void paintcheckIconSelectedAndMouseOver(Graphics2D g) {
        g.setPaint(Color.CYAN);
        g.fillOval( 0, 0, 10, 10 );
      }
}*/


