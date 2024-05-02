/*
    All_Unlimited-Hauptmaske-Import.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import jclass.bwt.JCOutliner;
import All_Unlimited.Allgemein.AUVector;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Anzeige.Farbe;
import All_Unlimited.Allgemein.Anzeige.GPS;
import All_Unlimited.Allgemein.Anzeige.HierarchieAnzeige;
import All_Unlimited.Allgemein.Anzeige.JaNein;
import All_Unlimited.Allgemein.Anzeige.Mass;
import All_Unlimited.Allgemein.Anzeige.Memo1;
import All_Unlimited.Allgemein.Anzeige.PW1;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Waehrung;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Anzeige.Html;
import All_Unlimited.Allgemein.Eingabe.AUBits;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUFarbe;
import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.AUEkitCore;
//import All_Unlimited.Allgemein.Eingabe.HtmlEingabe;
import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.EMail;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import All_Unlimited.Allgemein.Eingabe.GPS_Eingabe;
import All_Unlimited.Allgemein.Eingabe.MassEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.VonBisEingabe;
import All_Unlimited.Allgemein.Eingabe.WWW;
import All_Unlimited.Allgemein.Eingabe.WaehrungsEingabe;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Allgemein.Eingabe.Zahl2;
import All_Unlimited.Allgemein.Eingabe.RolleEingabe;

import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Graphics2D;

import All_Unlimited.Allgemein.GroupBox;

public class Import
{
	private static final long serialVersionUID = 1224960424807521694L;
	// add your data members here
	private Global g;
	private int iStt;
	//int iATS;
	private int iAbfrage=0;
	private int iAbfrageBegriff=0;
        private int iRolle=0;
	private String sFilename;
	private Tabellenspeicher TabDaten=null;
	private Tabellenspeicher TabSpalten=null;
	private Tabellenspeicher TabRef=null;
	private Vector VecBezeichnung;
	private Vector<String> VecBez=new Vector<String>();
        private FileEingabe EdtFile;
        private ComboSort CboTyp;
        private AbfrageEingabe CboAbfrage;
	private char cTrenn=';';
	private Text EdtTrennzeichen=new Text(""+cTrenn,1);
	private AUCheckBox CbxTitelzeile;
	private AUCheckBox CbxDefImport;
	//private AUCheckBox CbxMitFormat;
        private AUCheckBox CbxErgaenzen;
        private AUCheckBox CbxNurNeu;
        private AUCheckBox CbxMandant;
        private JRadioButton RadANSI=null;
        private JRadioButton RadASCII=null;
        private JRadioButton RadUTF16=null;
        private JRadioButton RadUTF8=null;
        private Text EdtCP=new Text("",10);
	private boolean bTitelzeile = true;
	//private boolean bMitFormat = false;
        private boolean bKeineAustritte = false;
        private boolean bDefImport=false;
        private boolean bMandant=false;
        private boolean bNurNeu=false;
        private static Import This=null;
        private ComboSort CboStammtyp;
        private boolean bBezOk=true;
        private JButton BtnShow;
	private	JButton BtnOK;
        private	JButton BtnZR;
        //private boolean bStart=true;
        private boolean bErgaenzen=false;
        private Abfrage AF=null;
        private Tabellenspeicher TabFehler=null;
        private Gauge Gau=null;
        public int iFehler=0;
        private boolean bFehler=false;
        private JFrame FomImport=null;
        private int iFF=100;
        //private int STICHTAG;

	//boolean bAuto=true;  // seht in iAbfrageBegriff: 0..Auto

        public static Import start(Global rg,JFrame Fom)
        {
          //rg.fixtestInfo("Import: Stt="+riStt);
          return This==null?new Import(rg,Fom):This;
        }

	private Import(Global rg,JFrame Fom)//,int riStt)
	{
		FomImport =new JFrame(rg.getBegriffS("Dialog","Import"));
                //if (riStt>0)
                //  iStt=riStt;
                g = rg;
                iFF=g.getFontFaktor();
                //g.fixtestInfo("erzeuge Import mit Stt="+iStt);
		//iATS=SQL.getInteger(g,"select aic_stamm from stamm join stammtyp where stammtyp.kennung='Waehrung' and stamm.kennung='ATS'");
		build();
		if(Fom!=null)
			Static.centerComponent(FomImport,Fom);
		This=this;
	}

        public void show2(int riStt)
        {
          //VecBezeichnung=Abfrage.FuelleVecBezeichnung(g,iStt);
          //if (iStt != 0)
          if (riStt>0 && iStt != riStt)
          {
            iStt = riStt;
            CboStammtyp.setSelectedAIC(iStt);
            fillCboAbfrage(iStt,CboAbfrage);
          }
          if (iStt == 0 || CboAbfrage.getSelectedAIC()==0)
          {
            BtnShow.setEnabled(false);
            BtnOK.setEnabled(false);
          }
          //g.progInfo(""+VecBezeichnung);
          FomImport.setVisible(true);
        }

        public static void free()
        {
          if (This != null)
          {
            This.g.testInfo("Import.free");
            This.FomImport.dispose();
            //iStt=0;
            This = null;
          }
        }

        /*public Import(Global rg,int riStt,int riAbfrage,String rsFilename,String rsTrenn,boolean rbErgaenzen)
        {
          this(rg,riStt,riAbfrage,rsFilename,rsTrenn,rbErgaenzen,false);
        }*/

	public Import(Global rg,int riStt,int riAbfrage,String rsFilename,String rsTrenn,boolean rbErgaenzen,boolean rbKeineAustritte,boolean rbTitel)
	{
		g=rg;
		iStt=riStt;
		iAbfrage=riAbfrage;
                iAbfrageBegriff=0;
		sFilename=rsFilename;
                //bMitFormat=rbMitFormat;
                bErgaenzen=rbErgaenzen;
                bKeineAustritte=rbKeineAustritte;
                bTitelzeile=rbTitel;
                iFehler=0;
                if (iStt>0)
                  VecBezeichnung=Abfrage.FuelleVecBezeichnung(g,iStt);
		ErmittleTabSpalten();
		cTrenn=rsTrenn==null || rsTrenn.equals("")?' ':rsTrenn.equals("Tab")?9:rsTrenn.charAt(0);
		if (ImportiereDaten())
                  Daten_in_die_Datenbank();
		//dispose();
                bErgaenzen=false;
	}

        public Import(Global rg,String sKennung,Tabellenspeicher Tab)
        {
          g=rg;
          int iPos=g.getPosBegriff("Abfrage",sKennung);
          if (iPos<0)
            Static.printError("Import: Abfrage "+sKennung+" nicht gefunden");
          else
          {
            iStt = g.TabBegriffe.getI(iPos, "Erf") > 0 ? -g.TabBegriffe.getI(iPos, "Erf") : g.TabBegriffe.getI(iPos, "Stt");
            iAbfrage = SQL.getInteger(g, "Select aic_abfrage from abfrage where aic_begriff=?", -1, "" + g.TabBegriffe.getI(iPos, "Aic"));
            if (iStt==0)
              Static.printError("Stammtyp bei Abfrage "+g.getBegBez2(iPos)+" ("+sKennung+") nicht ermittelbar, deshalb nicht importiert!");
            else
            {
              g.fixInfo("Import " + sKennung + ": Stt=" + iStt + ", Abfrage=" + iAbfrage);
              iAbfrageBegriff = 0;
              bErgaenzen = false;
              bKeineAustritte = false;
              bTitelzeile=true;
              bDefImport=true;
              iFehler = 0;
              if (iStt > 0)
                VecBezeichnung = Abfrage.FuelleVecBezeichnung(g, iStt);
              ErmittleTabSpalten();
              if (AF.iBew==0 && AF.iStt==0)
              {
                Static.printError("Stamm- und Bewegungstyp konnte bei Abfrage "+g.getBegBez2(iPos)+" ("+sKennung+") nicht ermittelt werden!");
              }
              else
              {
            	int iErrLast=Static.getError(); 
                sFilename = AF.sDefBez;
                TabDaten = Tab;
                for (TabDaten.moveFirst(); !TabDaten.out(); TabDaten.moveNext())
                  for (TabSpalten.moveFirst(); !TabSpalten.out(); TabSpalten.moveNext())
                    TabDaten.setInhalt(TabSpalten.getS("Kennung"), CheckDatentyp(TabDaten.getS(TabSpalten.getS("Kennung"))));
                //TabDaten.showGrid("TabDaten danach");
                Daten_in_die_Datenbank();
                int iErr=Static.getError()-iErrLast;
                if (iErr>0)
                	Static.printError("Import: Abfrage "+sKennung+" hat "+iErr+" Fehler verursacht");
              }
            }
          }
          //dispose();
        }

        private boolean LaengeOK()
        {
          int iAnz=0;
          for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
            for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
            {
              String sDT=TabSpalten.getS("Datentyp");
//              int iL= sDT.equals("StringSehrKurz")?10:sDT.startsWith("StringKurz") || sDT.equals("Passwort")?30:
//                  sDT.equals("String60")|| sDT.equals("E-Mail")?60:sDT.equals("Kennung")?20:sDT.equals("Bezeichnung")?50:1000;
              int iL = sDT.equals("Kennung") ? 40:sDT.equals("Bild") ? 100:sDT.equals("Text") ? 8000:255;
              if (TabDaten.getS(TabSpalten.getS("Kennung")).length()>iL)
                iAnz++;
            }
          g.progInfo(iAnz+" Daten sind zu lange");
          if (iAnz==0)
            return true;
          else
            return new Message(Message.YES_NO_OPTION, null, g).showDialog("abschneiden",new String[] {"" +iAnz}) == Message.YES_OPTION;
        }

	private boolean Vorschau()
	{
			ErmittleTabSpalten();
			if (!ImportiereDaten())
                          return false;
			JDialog DlgTab=new JDialog(FomImport,"Import-Vorschau",true);
			JTabbedPane Pane = new JTabbedPane();
			JCOutliner GidDaten = new JCOutliner();
			JCOutliner GidSpalten = new JCOutliner();
			JCOutliner GidRef = new JCOutliner();
			Pane.add(sFilename,GidDaten);
			Pane.add("Spalten",GidSpalten);
			Pane.add("Referenz",GidRef);
			for(TabRef.moveFirst();!TabRef.eof();TabRef.moveNext())
			{
				JCOutliner Gidx = new JCOutliner();
				Pane.add(TabRef.getS("Aic"),Gidx);
				((Tabellenspeicher)TabRef.getInhalt("Tab")).showGrid(Gidx);
			}
			TabDaten.showGrid(GidDaten);
			TabSpalten.showGrid(GidSpalten);
			TabRef.showGrid(GidRef);
			DlgTab.getContentPane().add("Center",Pane);
			DlgTab.setSize(600*iFF/100,400*iFF/100);
			g.printSI("Show");
			DlgTab.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			//g.clockInfo(TabAbfragen.getS("Bezeichnung"),lClock);
			DlgTab.setVisible(true);
                        return true;
	}

	private void build()
	{
		CboAbfrage = new AbfrageEingabe(g,FomImport,false,true);
		CboTyp = new ComboSort(g,ComboSort.kein);
                CboTyp.addItem(g.getBezeichnung("Tabellenname","STAMMTYP"),1,"Stammtyp",0);
                CboTyp.addItem(g.getBezeichnung("Tabellenname","BEWEGUNGSTYP"),2,"Bewegungstyp",0);
                  CboTyp.addItemListener(new ItemListener ()
                  {
                        public void itemStateChanged(ItemEvent ev)
                        {
                                if(ev.getStateChange() == ItemEvent.SELECTED)
                                {
                                  fillCboStammtyp(CboStammtyp,CboTyp);
                                  fillCboAbfrage(CboStammtyp.getSelectedAIC()*(CboTyp.getSelectedKennung().equals("Bewegungstyp")?-1:1),CboAbfrage);
                                }
                        }
                  });

		CboStammtyp  = new ComboSort(g);
		//CboStammtyp.fillDefTable("Stammtyp",false);
        fillCboStammtyp(CboStammtyp,CboTyp);
        CboStammtyp.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange() == ItemEvent.SELECTED && iStt!=CboStammtyp.getSelectedAIC())
				{
					fillCboAbfrage(CboStammtyp.getSelectedAIC()*(CboTyp.getSelectedKennung().equals("Bewegungstyp")?-1:1),CboAbfrage);
                                        /*if (iStt>0)
                                          VecBezeichnung=Abfrage.FuelleVecBezeichnung(g,iStt);
                                        //g.progInfo("Stt="+iStt+"-> VecBezeichnung="+VecBezeichnung);
                                        BtnShow.setEnabled(false);
                                        BtnOK.setEnabled(false);
					CboAbfrage.Clear();
					SQL Qry=new SQL(g);
					for(Qry.open("select abfrage.aic_begriff from abfrage"+SQL.join("begriff","abfrage")+Bedingung()+
                                                     (iStt<0 ? " and aic_bewegungstyp="+(-iStt) : " and (aic_stammtyp is null or aic_stammtyp="+iStt+")")+
                                                     (g.Def()?"":" and (aic_benutzergruppe is null or "+g.getAllBG()+")"));!Qry.eof();Qry.moveNext())
					{
						g.TabBegriffe.posInhalt("Aic",Qry.getI("Aic_Begriff"));
						CboAbfrage.addItem(g.TabBegriffe.getS("Bezeichnung"),g.TabBegriffe.getI("Aic"),g.TabBegriffe.getS("Kennung"));
					}
					Qry.free();*/
				}
			}
		});


		EdtFile = new FileEingabe(g);
		//EdtFile.SaveDialog(true);
		if (g.Prog())
			EdtFile.setValue("N:\\A\\ANR");
		CbxTitelzeile=g.getCheckbox("Titelzeile");
		CbxTitelzeile.setSelected(true);
		CbxDefImport=g.getCheckbox("DefImport");
//		if (CbxDefImport!=null)
//			CbxDefImport.setVisible(g.Def());
		//CbxMitFormat=g.getCheckbox("mit Format");
                CbxErgaenzen=g.getCheckbox("ergaenzen");
                CbxNurNeu=g.getCheckbox("nurNeu");
                CbxMandant=g.getCheckbox("Mandant");
                ButtonGroup RadGroup=new ButtonGroup();
                RadANSI=g.getRadiobutton("ANSI");
                RadASCII=g.getRadiobutton("ASCII");
                RadUTF16=g.getRadiobutton("UTF16");
                RadUTF8=g.getRadiobutton("UTF8");
                RadGroup.add(RadANSI);
                RadGroup.add(RadASCII);
                RadGroup.add(RadUTF16);
                RadGroup.add(RadUTF8);
                if (Static.CP.equals("Cp1252"))
                  RadANSI.setSelected(true);
                else if (Static.CP.equals("Cp850"))
                  RadASCII.setSelected(true);
                else if (Static.CP.equals("UTF-16"))
                  RadUTF16.setSelected(true);
                else if (Static.CP.equals("UTF-8"))
                  RadUTF8.setSelected(true);
                else
                  EdtCP.setText(Static.CP);
		JPanel PnlL=new JPanel(new GridLayout(0,1,2,2));
		JPanel PnlR=new JPanel(new GridLayout(0,1,2,2));
		PnlL.add(CboTyp);
		PnlR.add(CboStammtyp);
		g.addLabel4(PnlL,"Abfrage");
		PnlR.add(CboAbfrage);
		g.addLabel4(PnlL,"File");
		PnlR.add(EdtFile);
		g.addLabel4(PnlL,"Trennzeichen");
                JPanel PnlSub=new JPanel(new GridLayout());
                EdtTrennzeichen.setColumns(3);
                PnlSub.add(g.addTwo(EdtTrennzeichen,new JLabel()));
                //PnlSub.add(new JLabel());
                PnlSub.add(CbxTitelzeile);
                PnlSub.add(CbxErgaenzen);
                PnlSub.add(CbxNurNeu);
                PnlSub.add(CbxMandant);
                if (g.Def() && Static.bDefShow && CbxDefImport!=null)
                	PnlSub.add(CbxDefImport);
		PnlR.add(PnlSub);
                /*g.addLabel4(PnlL,"Zeichensatz");
                 JPanel PnlP=new JPanel(new GridLayout(1,0));
                 PnlP.add(RadANSI);
                 PnlP.add(RadASCII);
                 PnlP.add(RadUTF16);
                 PnlP.add(EdtCP);
                PnlR.add(PnlP);*/
                GroupBox GroupZS = new GroupBox(g.getBegriffS("Label","Zeichensatz"));
                GroupZS.setFont(g.fontTitel);
                JPanel PnlZS=new JPanel(new BorderLayout(2,2));
                GroupZS.add(PnlZS);
                JPanel PnlZSN=new JPanel(new GridLayout(1,0,2,0));
                 PnlZSN.add(RadUTF8);
                 PnlZSN.add(RadANSI);
                 PnlZSN.add(RadASCII);
                 PnlZSN.add(RadUTF16);
                PnlZS.add("North",PnlZSN);
                PnlZS.add("Center",EdtCP);
                PnlZS.add("West",g.getLabel("alternativer Zeichensatz"));

                JPanel PnlAll=new JPanel(new BorderLayout(2,2));
                 JPanel PnlN=new JPanel(new BorderLayout(2,2));
                 PnlN.add("West",PnlL);
                 PnlN.add("Center",PnlR);
                PnlAll.add("North",PnlN);
                PnlAll.add("Center",GroupZS);
		JScrollPane Scroll=new JScrollPane(PnlAll);
                Scroll.setBorder(new EmptyBorder(new Insets(5,5,5,5)));
		FomImport.getContentPane().add("Center",Scroll);

                //JToolBar PnlTB=new JToolBar(JToolBar.HORIZONTAL);
                //PnlTB.setOpaque(false);
                //PnlTB.setFloatable(false);
                JPanel PnlTB=new JPanel(new GridLayout(1,0,2,2));
                ActionListener AL=new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev) {
                    String s=ev.getActionCommand();
                    if (s.equals("Zeitraum"))
                      Zeitraum.get(g).show();
                    else if (s.equals("show"))
                    {
                      set();
                      BtnOK.setEnabled(Vorschau());
                    }
                    else if (s.equals("Abbruch"))
                      FomImport.setVisible(false);
                    else if (s.equals("Ok"))
                    {
                              set();
                              bErgaenzen = CbxErgaenzen.isSelected();
                              boolean bRead=false;
                              //if (g.Debug())
                              //	bRead=Vorschau();
                              //else
                              //{
                                      ErmittleTabSpalten();
                                      bRead=ImportiereDaten();
                              //}

                              if (bRead && LaengeOK())
                              {
                                //g.start();
                                boolean b=false;
                                bFehler=false;
                                try
                                {
                                  b=Daten_in_die_Datenbank();
                                }
                                catch (Exception e)
                                {
                                  e.printStackTrace();
                                  Static.printError("Import.Daten_in_die_Datenbank: " + e);
                                  bFehler=true;
                                }
                                //if (b)
                                //  g.commit();
                                //else
                                if (!b)
                                {
                                  //g.rollback();
                                  if (Gau != null)
                                  {
                                    Gau.setText("Error", Gau.getMax());
                                    Gau.free();
                                  }
                                  new Message(Message.WARNING_MESSAGE,null,g).showDialog(bFehler ? "Importfehler":"EindeutigFehlt");
                                }
                              }

                              BtnOK.setEnabled(false);
                              EdtFile.setValue(EdtFile.getPath());
                              bErgaenzen = false;
                              FomImport.setVisible(false);
                      }
                  }
                };

                BtnZR=g.getButton("Zeitraum","Zeitraum",AL);
		BtnShow=g.getButton("show","show",AL);
		BtnOK=g.getButton("Ok","Ok",AL);
		JButton BtnAbbruch=g.getButton("Abbruch","Abbruch",AL);
		//JPanel PnlSouth=new JPanel(new GridLayout());
                PnlTB.add(BtnZR);
                //PnlTB.addSeparator();
                PnlTB.add(BtnShow);
                //PnlTB.add(new JPanel());
		PnlTB.add(BtnOK);
                //PnlTB.addSeparator();
		PnlTB.add(BtnAbbruch);
                JPanel PnlS=new JPanel(new GridLayout());
                PnlS.add(new JLabel());
                PnlS.add(PnlTB);
		//getContentPane().add("South",PnlS);
                PnlAll.add("South",PnlS);
		FomImport.setSize(600*iFF/100,250*iFF/100);

                CboAbfrage.Cbo.addItemListener(new ItemListener ()
                {
                        public void itemStateChanged(ItemEvent ev)
                        {
                                if(ev.getStateChange() == ItemEvent.SELECTED)
                                {
                                  boolean bOk=CboAbfrage.getSelectedAIC()>0;
                                  BtnShow.setEnabled(bOk);
                                  BtnOK.setEnabled(false);
                                }
                      }
              });
              fillCboAbfrage(CboStammtyp.getSelectedAIC(),CboAbfrage);
        		
	}

        private void set()
        {
          sFilename=EdtFile.getValue();
          iAbfrageBegriff=CboAbfrage.getSelectedAIC();
          cTrenn=EdtTrennzeichen.getText().charAt(0);
          bTitelzeile = CbxTitelzeile.isSelected();
          bDefImport=CbxDefImport.isSelected();
          bMandant=CbxMandant.isSelected();
          bNurNeu=CbxNurNeu.isSelected();
          Static.CP= !EdtCP.getText().equals("") ? EdtCP.getText():RadASCII.isSelected() ? "Cp850":RadANSI.isSelected() ? "Cp1252":RadUTF16.isSelected() ? "UTF-16":"UTF-8";
          //bMitFormat  = CbxMitFormat.isSelected();
        }

        private String Bedingung()
        {
          return " where"+g.bit("Begriff.bits",Abfrage.cstImportierbar)+" and (begriff.tod is null or begriff.tod=0)"/*+(bDefExport?" and"+SQL.bit("begriff.bits",Abfrage.cstVersionsup):"")*/;
        }

        private void fillCboStammtyp(ComboSort CboStammtyp,ComboSort CboTyp)
        {
          if (CboTyp.getSelectedKennung().equals("Stammtyp"))
            CboStammtyp.fillCbo("select distinct stammtyp.aic_stammtyp,stammtyp.kennung"+g.AU_Bezeichnung("stammtyp")+" from stammtyp"+g.join2("begriff","stammtyp")+g.join2("abfrage","begriff")+Bedingung()/*+(bDefExport?" and aic_Bewegungstyp is null":"")*/,"stammtyp",false,false);
          else if(CboTyp.getSelectedKennung().equals("Bewegungstyp"))
            CboStammtyp.fillCbo("select distinct Bewegungstyp.aic_Bewegungstyp,Bewegungstyp.kennung"+g.AU_Bezeichnung("Bewegungstyp")+" from Bewegungstyp"+g.join2("begriff","Bewegungstyp")+g.join2("abfrage","begriff")+Bedingung(),"Bewegungstyp",false,false);
        }

        private void fillCboAbfrage(int riStt,AbfrageEingabe CboAbfrage)
        {
          iStt=riStt;
          if (iStt>0)
            VecBezeichnung=Abfrage.FuelleVecBezeichnung(g,iStt);
          //g.progInfo("Stt="+iStt+"-> VecBezeichnung="+VecBezeichnung);
          BtnShow.setEnabled(false);
          BtnOK.setEnabled(false);
          CboAbfrage.Cbo.Clear();
          SQL Qry=new SQL(g);
          for(Qry.open("select abfrage.aic_begriff,aic_benutzergruppe,aic_benutzer from abfrage"+g.join("begriff","abfrage")+Bedingung()+
                       (iStt<0 ? " and aic_bewegungstyp="+(-iStt) : " and aic_bewegungstyp is null and aic_stammtyp="+iStt)+
                       (g.Def()?"":" and (aic_benutzergruppe is null or "+g.getAllBG()+")"));!Qry.eof();Qry.moveNext())
          {
                  int iPos=g.TabBegriffe.getPos("Aic",Qry.getI("Aic_Begriff"));
                  if (g.Privileg(iPos,Qry.getI("aic_benutzer"),Qry.getI("aic_benutzergruppe")))
                    CboAbfrage.Cbo.addItem(g.TabBegriffe.getS(iPos,"Bezeichnung"),g.TabBegriffe.getI(iPos,"Aic"),g.TabBegriffe.getS(iPos,"Kennung"));
          }
          Qry.free();
          CboAbfrage.Cbo.setKein(false);
          BtnShow.setEnabled(CboAbfrage.getSelectedAIC()>0);
        }

	private boolean ImportiereDaten()
	{
		/*Vector Vec=(Vector)TabSpalten.getVecSpalte("Kennung").clone();
		Vec.addElement("AIC");
		for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
			if(TabSpalten.getI("STT")>0)
				Vec.addElement("STT"+TabSpalten.getI("STT"));
		TabDaten=new Tabellenspeicher(g,Vec);*/
		//TabDaten=new Tabellenspeicher(g,(Vector)TabSpalten.getVecSpalte("Kennung"));
                TabDaten=new Tabellenspeicher(TabSpalten,g);
		//char cTrenn=EdtTrennzeichen.getText().charAt(0);
		//int iWC=0;
		try
		{
                  InputStreamReader FR=new InputStreamReader(new FileInputStream(new File(sFilename)),Static.CP);
                  g.fixInfo("Encoding="+FR.getEncoding());
			BufferedReader Buf=new BufferedReader(FR);
			if (bTitelzeile)
				Buf.readLine();
			String s=Buf.readLine();//new String(Buf.readLine().getBytes("Cp1252"),"Cp1252");
			while (s != null /*&& iWC<3*/)
			{
				//iWC++;
				//g.progInfo(iWC+":"+s);
				TabDaten.newLine();
				int l=s.length();
				boolean bFirst=true;
				boolean bUse=true;
				int iStart=0;
				int iStop=-1;
				TabSpalten.moveFirst();
				for(int i=0;i<l && !TabSpalten.eof();i++)
				{
					char c=s.charAt(i);
					if (c=='"')
					{
						if (bUse)
							iStart=i+1;
						else
							iStop=i;
						bUse=!bUse;
					}
					else if (c==cTrenn && bUse)
					{
						if (!bFirst)
						//	TabSpalten.moveFirst();
						//else
							TabSpalten.moveNext();
						bFirst=false;
						if (iStop<iStart)
							iStop=i;
						String s3=s.substring(iStart,iStop);
						/*if(iStop==iStart)
							g.progInfo("leer");
						else*/
						if(!TabSpalten.eof())
						{
                                                  Object Obj=null;
                                                  if(iStop!=iStart)
                                                  {
                                                    Obj = CheckDatentyp(s3);
                                                    if (Obj != null)
                                                      TabDaten.setInhalt(TabSpalten.getS("Kennung"), Obj);
                                                  }
                                                      if ((TabSpalten.getI("bits2")&Abfrage.cstImportBed)>0)
                                                      {
                                                        Vector Vec=AF.getBed();
                                                        String sBed=(String)Vec.elementAt(2);
                                                        boolean bOk=true;
                                                        if (sBed.equals("like") && Obj!=null)
                                                        {
                                                          String sV=ohneHK(Vec, 3);
                                                          if (sV.endsWith("%"))
                                                            bOk = (""+Obj).startsWith(sV.substring(0,sV.length()-1));
                                                          else
                                                            bOk = Obj.equals(sV);
                                                        }
                                                        else if (sBed.equals("not like") && Obj!=null)
                                                        {
                                                          String sV=ohneHK(Vec, 3);
                                                          if (sV.endsWith("%"))
                                                            bOk = !(""+Obj).startsWith(sV.substring(0,sV.length()-1));
                                                          else
                                                            bOk = !Obj.equals(sV);
                                                        }
                                                        else if (sBed.equals("is null"))
                                                          bOk=Obj==null || Obj.equals("");
                                                        else if (sBed.equals("is not null"))
                                                          bOk=Obj!=null && !Obj.equals("");
                                                        else
                                                          bOk=false;
                                                        if (!bOk)
                                                        {
                                                          TabDaten.clearInhalt();
                                                          TabSpalten.moveLast();
                                                        }
                                                        //g.fixInfo("ImportBedingung bei "+Obj+"/"+AF.getBed());
                                                      }
						}
						//g.progInfo(TabSpalten.getS("Bezeichnung")+":"+s3+" ("+TabSpalten.getS("Datentyp")+")");
						iStart=i+1;
					}
				}
				if (!bFirst)
					TabSpalten.moveNext();
				if (iStop<iStart)
					iStop=l;
				if(iStop!=iStart && !TabSpalten.eof())
				{
					Object Obj=CheckDatentyp(s.substring(iStart,iStop));
					if (Obj != null)
						/*if(Obj instanceof VonBis)
						{
							TabDaten.setInhalt("v"+TabSpalten.getS("Kennung"),((VonBis)Obj).getVon());
							TabDaten.setInhalt("b"+TabSpalten.getS("Kennung"),((VonBis)Obj).getBis());
						}
						else*/
							TabDaten.setInhalt(TabSpalten.getS("Kennung"),Obj);
				}
				/*StringTokenizer s2=new StringTokenizer(s,EdtTrennzeichen.getText());
				//int i2=0;
				TabSpalten.moveFirst();
				while (s2.hasMoreTokens())
				{
					String s3=s2.nextToken();
					if (s3.startsWith("\""))
						s3=s3.substring(1,s3.length()-1);
					TabDaten.setInhalt(TabSpalten.getS("Kennung"),CheckDatentyp(s3,TabSpalten.getS("Datentyp")));
					//g.progInfo(TabSpalten.getS("Bezeichnung")+":"+s3+" ("+TabSpalten.getS("Datentyp")+")");
					//i2++;
					TabSpalten.moveNext();
				}*/
                                s=Buf.readLine();
                                //if (s != null)
				//s=new String(s.getBytes("Cp1252"),"Cp1252");
			}
			Buf.close();
                        //if (g.Prog())
                        //  TabDaten.showGrid("Daten");
                        return true;

		}catch(Exception io)
		{
			Static.printError("Import.ImportiereDaten(): Exception - "+io);
			g.printStackTrace(io);
                        if (iAbfrageBegriff>0 && FomImport!=null)
                          new Message(Message.ERROR_MESSAGE,FomImport,g,20).showDialog("ImportFehler",new String[] {"\n"+io+"\nbei Spalte:"+TabSpalten.getS("Bezeichnung")+"("+TabSpalten.getS("Datentyp")+")"});
                        return false;
		}
		//Tab.moveFirst();
		//if (g.Prog())
		//	TabDaten.showGrid("Daten");
	}

        private String ohneHK(Vector Vec,int i)
        {
          String s=(String)Vec.elementAt(i);
          return s.substring(1,s.length()-1);
        }

	private Double CheckMitFormat(String s)
	{
          return new Double(Sort.convertKomma(s));//.replace(',','.'));
		//return new Double(new java.text.DecimalFormat(Static.beiLeer(TabSpalten.getS("Format"),"0.0###########")).parse(s,new java.text.ParsePosition(0)).doubleValue());
	}

	private Object CheckDatentyp(String s)
	{
		if (s!=null && (TabSpalten.getI("bits") & Abfrage.cstHochkomma) > 0)
                  if (bDefImport)
                    s=Static.changeK2(s);
                  else if (s.length()>2 && s.startsWith("\"") && s.endsWith("\""))
                    s=s.substring(1, s.length()-1);
		String sDatentyp=TabSpalten.getS("Datentyp");
//		g.fixtestError("CheckDatentyp "+sDatentyp+":"+s);
		if (s.equals("") || sDatentyp.equals("Filler"))
                  return null;
                else if (TabSpalten.getS("Kennung").startsWith("A") || sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Datum") || sDatentyp.equals("Stichtag"))
                  return s.equals("0") ? null:new SimpleDateFormat(Static.beiLeer(TabSpalten.getS("Format"),"yyyy/MM/dd")).parse(s,new ParsePosition(0));
                else if (sDatentyp.equals("Boolean") && (g.MS() || g.Oracle()))
                  return new Double(!s.startsWith("N") ? 1:0);
                //else if (sDatentyp.equals("Bool"))
                //  return new Double(g.getAuswahlAic(g.getAuswahlPosB(iEig,s)));
                else if (sDatentyp.endsWith("Boolean"))
                  return new Boolean(!s.startsWith("N"));
		//else if (sDatentyp.equals("Integer"))
		//	return new Integer(s);
		else if (sDatentyp.equals("Double") || sDatentyp.equals("Integer") || sDatentyp.equals("Farbe")
                         || sDatentyp.equals("Waehrung") || sDatentyp.equals("BewWaehrung")
                         || sDatentyp.equals("BewDauer") || sDatentyp.endsWith("BewZahl"))
			return CheckMitFormat(s);
                else if (sDatentyp.equals("SysAic"))
                  return new Integer(s);
		//else if (sDatentyp.equals("Waehrung"))
		//	return Double.valueOf(s);
			//return new Waehrung(g.getVecWaehrung(),TabSpalten.getS("Format"));
                //else if (sDatentyp.equals("BewMass"))
		//	return new Mass(g.getMass(TabSpalten.getI("Stamm"),(TabSpalten.getI("bits")&Abfrage.cstKeineEinheit)>0),CheckMitFormat(s),null);
		else if (sDatentyp.equals("Mass") || sDatentyp.equals("BewMass"))
                  return new Mass(g,g.getMass(TabSpalten.getI("Stamm"), (TabSpalten.getI("bits") & Abfrage.cstKeineEinheit) > 0), new Double(CheckMitFormat(s).doubleValue()*g.getFaktor(TabSpalten.getI("Stamm"))), TabSpalten.getS("Format"));
		else if (sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("Zeit"))
			return s.equals("0") ? null:new SimpleDateFormat(Static.beiLeer(TabSpalten.getS("Format"),"yyyy/MM/dd HH:mm:ss")).parse(s,new ParsePosition(0));
		else if (sDatentyp.equals("Einheiten"))
		{
			int iPos=s.indexOf(" ");
			if (iPos>=0)
			{
				Double Dbl=CheckMitFormat(s.substring(0,iPos));
				String sKen=s.substring(iPos+1);
				Vector<Object> Vec=new Vector<Object>();
				Vec.addElement(new Double(1));
				Vec.addElement(sKen);
				Vec.addElement(new Integer(SQL.getInteger(g,"select s.aic_stamm from stammtyp stt join stammview2 s on stt.aic_stammtyp=s.aic_stammtyp where stt.kennung='SI-Einheit' and s.kennung='"+sKen+"'")));
				return new Mass(g,Vec,Dbl,null);
				/*String sVon=s.substring(0,iPos);
				String sBis=s.substring(iPos+3);
				g.progInfo("<"+sVon+"> - <"+sBis+">");
				Date DtVon=new SimpleDateFormat(TabSpalten.getS("Format")).parse(sVon,new ParsePosition(0));
				Date DtBis=new SimpleDateFormat(TabSpalten.getS("Format")).parse(sBis,new ParsePosition(0));
				return new VonBis(DtVon,DtBis,TabSpalten.getS("Format"));*/
			}
			else
				return null;
		}
		else if (sDatentyp.equals("GPS"))
		{
			if (s.startsWith("["))
				return new GPS(s);
			else
				return null;
		}
		else if (sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("BewVon_Bis"))
		{
                  String sFormat=TabSpalten.getS("Format");
                  String sKZ=TabSpalten.getS("Stil");
                  if (sFormat.equals("-"))
                  {
                    return new VonBis(null,null,CheckMitFormat(s).doubleValue()*g.getFaktor(TabSpalten.getI("Stamm")),sFormat,Abfrage.getLaenge(TabSpalten),g,TabSpalten.getI("Stamm"), (TabSpalten.getI("bits") & Abfrage.cstKeineEinheit) > 0);
                  }
                  if (sKZ != null && (sKZ.equals("von") || sKZ.equals("bis") || sKZ.equals("Dauer")) )
                  {
                	  if (sKZ.equals("Dauer"))
                		  return s==null || s.length()==0 ? null:Double.parseDouble(s);
                	  else
                		  return new SimpleDateFormat(sFormat).parse(s, new ParsePosition(0));
                  }
                  else if (s.startsWith("["))
                  {
                    s=s.substring(1,s.length()-1);
                    int iPos = s.indexOf("-");
                    if (iPos >= 0)
                    {
                      String sVon = s.substring(0, iPos);
                      String sBis = s.substring(iPos + 1);
                      int iPos2= s.indexOf("|");
                      String sDauer=null;
                      if (iPos2>0)
                      {
                    	 sBis=s.substring(iPos+1, iPos2);
                    	 sDauer=s.substring(iPos2 + 1);
                      }
                      g.fixtestInfo("<" + sVon + "> - <" + sBis + ">"+(sDauer==null ? "":" dauer="+sDauer));
                      Date DtVon = new SimpleDateFormat(sFormat).parse(sVon, new ParsePosition(0));
                      Date DtBis = new SimpleDateFormat(sFormat).parse(sBis, new ParsePosition(0));
                      double dDauer=sDauer==null ? 0:Double.parseDouble(sDauer);
                      if (sDauer!=null)
                    	  return new VonBis(g,DtVon, DtBis,dDauer,sFormat);
                      else
                    	  return new VonBis(g,DtVon, DtBis, sFormat);
                    }
                    else
                      return null;
                  }
                  else
                  {
                    int iPos = s.indexOf(" - ");
                    boolean b=iPos>=0;
                    if (!b)
                      iPos=s.indexOf("-");
                    if (iPos >= 0)
                    {
                      String sVon = s.substring(0, iPos);
                      String sBis = s.substring(iPos + (b?3:1));
                      if (sBis.length()<sFormat.length() && sVon.length()==sFormat.length())
                        sBis=sVon.substring(0,sFormat.indexOf("H"))+sBis;
                      g.progInfo("<" + sVon + "> - <" + sBis + ">");
                      if (sVon.length()!=sFormat.length() || sBis.length()!=sFormat.length())
                      {
                        Static.printError("Import: Zeile "+(TabDaten.getPos()+1)+" Spalte "+TabSpalten.getS("Bezeichnung")+": Format "+sFormat+" passt nicht zu Daten");
                        return null;
                      }
                      Date DtVon = new SimpleDateFormat(sFormat).parse(sVon, new ParsePosition(0));
                      Date DtBis = new SimpleDateFormat(sFormat).parse(sBis, new ParsePosition(0));
                      return new VonBis(g,DtVon, DtBis, sFormat);
                    }
                    else
                      return null;
                  }
		}
		else if (sDatentyp.equals("Hierarchie"))
		{
			if (s.indexOf("|")>0)
				return new HierarchieAnzeige(g,s);
			else
				return s;
		}
		else if (sDatentyp.equals("Zeit"))
			return new SimpleDateFormat("HH:mm:ss").parse(s,new ParsePosition(0));
		//else if (sDatentyp.equals("Stringx"))
                //  return s;
        else if (sDatentyp.equals("Text"))
          return new Memo1(s);
        else
			return s;
	}

	private void ErmittleTabSpalten()
	{
		TabRef=new Tabellenspeicher(g,new String[] {"Aic","Tab","Eig1","Eig2"});
		//JCOutlinerFolderNode Nod = Abfrage.InitNode(g,true);
		if (iAbfrageBegriff==0 && iAbfrage>0)
			;//Qry.open("Select aic_abfrage,bits,modell.aic_begriff from modell right outer"+SQL.join2("abfrage","modell")+SQL.join("begriff","abfrage")+" where aic_abfrage="+iAbfrage);
		else if(iAbfrageBegriff>0)
		{
                  /*SQL Qry=new SQL(g);
                  String s="Select aic_abfrage,bits,modell.aic_begriff from modell right outer"+g.join2("abfrage","modell")+g.join("begriff","abfrage")+" where abfrage.aic_begriff="+iAbfrageBegriff;
                  Qry.open(s);
                  iAbfrage=Qry.getI("aic_abfrage");
                  g.fixInfo(iAbfrage+":"+s);
                  Qry.free();*/
                    iAbfrage=SQL.getInteger(g,"Select aic_abfrage from abfrage where aic_begriff=?",-1,""+iAbfrageBegriff);
                    g.progInfo("Abfrage="+iAbfrage+" bei "+iAbfrageBegriff);
		}
		else
		{
			Static.printError("Import.ErmittleTabSpalten: Abfrage und Begriff sind 0");
			return;
		}
		g.debugInfo("Abfrage="+iAbfrage+", Begriff="+iAbfrageBegriff);
		//int iBew=Qry.getI("aic_bewegungstyp");
		//boolean bKeinZR=Abfrage.is(Qry.getI("bits"),Abfrage.cstKeinZeitraum);
		//boolean bKeinStamm=Abfrage.is(Qry.getI("bits"),Abfrage.cstKeinStamm);
		//if (iAbfrageBegriff==0)	// Auto-Import über Modellberechnung
		//	bTitelzeile=!Abfrage.is(Qry.getL("bits"),Abfrage.cstKeinTitel);
		//int iModell=Qry.getI("aic_begriff");
		AF=new Abfrage(g,iAbfrage,Abfrage.cstAbfrage);
                iRolle=AF.iRolle;
                if (iAbfrageBegriff==0)	// Auto-Import über Modellberechnung
                {
                  bTitelzeile = bTitelzeile && (AF.iBits & Abfrage.cstKeinTitel) == 0;
                  iAbfrageBegriff=SQL.getInteger(g,"Select aic_begriff from abfrage where aic_abfrage=?",-1,""+iAbfrage);
                }
                g.fixtestInfo("ErmittleTabSpalten für "+AF.sDefBez+": Begriff="+iAbfrageBegriff+", Abfrage="+iAbfrage+", Titelzeile="+bTitelzeile);
		Vector<Integer> Vec1=new Vector<Integer>();
		//JCOutlinerFolderNode NodSpalten=Abfrage.findNode(Nod,false);
		TabSpalten=AF.getAnzeigeSpalten(Vec1);
		//if (g.Debug())
		//	TabSpalten.showGrid("Spalten");
		TabRef.addInhalt("Aic",0);
		TabRef.addInhalt("Eig1",null);
		TabRef.addInhalt("Eig2",null);
                AF.sAnfang=AF.iBew==0?"p2.aic_stamm,Bezeichnung,Eintritt,Austritt":null;
                String sCheck=AF.iBew>0 ? AF.checkJoker(AF.Check(Vec1,AF.NodBed.getChildren(),"",false)):"";
                String sCheck2=AF.iBew>0 ? AF.checkJoker(AF.Check(Vec1,AF.NodVBed.getChildren()," ",true)):"";
		TabRef.addInhalt("Tab",new Tabellenspeicher(g,"select * from (Select "+((AF.iBew==0) ?
			AF.ZusaetzlicheSpalten(Vec1,Formular.Stamm,false)+" from stammview"+((AF.iBits&Abfrage.cstKeinStammZeitraum)>0 ?"2":"")+" p2 where aic_stammtyp="+iStt+" and aic_rolle is null"/*+(iRolle>0?"="+iRolle:" is null")*/+(bMandant ? " and aic_mandant="+g.getMandant():g.getMandanten(iStt))+AF.Ebenen():
			AF.ZusaetzlicheSpalten(Vec1,Formular.Bewegung,false)+" from BewView"+((AF.iBits&Abfrage.cstKeinZeitraum)>0 ?"2":"")+" p2 where p2.aic_bewegungstyp="+AF.iBew+(bMandant ? " and aic_mandant="+g.getMandant():g.getReadMandanten(AF.iBew))+sCheck2+AF.Ebenen()+sCheck),true));
			//(bDefImport ? " and aic_mandant=1":g.getReadMandanten(AF.iBew))+AF.Ebenen()+sCheck),true));
		VecBez.removeAllElements();
		for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
		{
			String sDatentyp=TabSpalten.getS("Datentyp");
//			g.fixInfo("Spalte "+(TabSpalten.getPos()+1)+": "+sDatentyp+"/"+TabSpalten.getI("Stt"));
            if(TabSpalten.getI("Stt")>0 && !sDatentyp.equals("Einheiten")  && !sDatentyp.equals("Mass") && !sDatentyp.equals("Waehrung") && TabSpalten.isNull("Anzeige"))
			{
				String s=TabSpalten.getS("Kennung2");
                                boolean bMF=s.startsWith("m");
                                if (bMF)
                                  s=s.substring(1);
				int iPos=s.indexOf('_');
                                //if (iPos==s.indexOf("_H"))
                                //  iPos==-1;
				Vec1=new Vector<Integer>();
				Integer Int1=new Integer(iPos==-1?s:s.substring(0,iPos));
                                if (iPos>0)
                                  Vec1.addElement(new Integer(s.substring(iPos+1)));
				Vector<Integer> Vec2=null;
                                int iPosE=g.TabEigenschaften.getPos("Aic",Int1);
				if (iPosE>=0 && g.TabEigenschaften.getS(iPosE,"Datentyp").equals("Hierarchie"))
				{
					Vec2=new Vector<Integer>();
					int iVon=TabSpalten.getI("Stt");
                                        //g.progInfo("Hierachie1:"+iVon+"/"+iStt);
					while(iVon != 0 && iStt != iVon)
					{
						int iPosS=g.TabStammtypen.getPos("Aic",new Integer(iVon));
						iVon = g.TabStammtypen.getI(iPosS,"Darunter");
                                                //g.progInfo("Hierachie2:"+iVon+"/"+iStt);
						if (((g.TabStammtypen.getI(iPosS,"bits")&Global.cstEnde)==0 || iStt==0) && g.existsSttS(iPosS))
							Vec2.addElement(new Integer(g.TabStammtypen.getI(iPosS,"Aic")));
					}
                                        //g.progInfo("Vec2="+Vec2);
				}

				TabRef.addInhalt("Aic",TabSpalten.getI("Nummer"));
                                AF.sAnfang="p2.aic_stamm,p2.aic_stammtyp";
                                String sSQL=(iPos>0 ? "select * from (Select "+AF.ZusaetzlicheSpalten(Vec1,Formular.Stamm,false):"select aic_stamm,Bezeichnung")+
                                    " from stammview p2 where aic_stammtyp"+(Vec2==null ? "="+TabSpalten.getI("Stt"):Static.SQL_in(Vec2))+
                                    " and "+Rolle(TabSpalten.getI("Stt")==AF.iStt && AF.iBew>0 ? iRolle:0)+
                                    (bMandant ? " and aic_mandant="+g.getMandant():g.getMandanten(TabSpalten.getI("Stt")))+(iPos>0 ? AF.Ebenen():"");
                                g.progInfo(sSQL);
				TabRef.addInhalt("Tab",new Tabellenspeicher(g,sSQL,true));
				TabRef.addInhalt("Eig1",/*(bMF?"m":"")+*/(bMF?-1:1)*Int1.intValue());
				TabRef.addInhalt("Eig2",iPos>0 ? "E"+s.substring(iPos+1):"Bezeichnung");
                                //TabRef.showGrid("Ref");
                                //return;
			}
			else if (sDatentyp.endsWith("Bezeichnung"))
				VecBez.addElement(TabSpalten.getS("Kennung"));
		}
		if (iStt<0)
			return;
		if (VecBez.size()>0)
			bBezOk=true;
		else
		{
            g.fixInfo("ErmittleTabSpalten "+AF.sBez+": Bew="+AF.iBew+", Stt="+iStt+", VecBezeichnung="+VecBezeichnung+", VecBez="+VecBez);
            bBezOk=iStt>0 ? VecBezeichnung.isEmpty():false;
            if(AF.iBew==0 && iStt>0 && !VecBezeichnung.isEmpty() && VecBez.isEmpty())
                  for(int i=0;i<VecBezeichnung.size();i++)
                    if (VecBezeichnung.elementAt(i)==null)
                      VecBez.addElement(null);
                    else if (TabSpalten.posInhalt("Kennung2",""+VecBezeichnung.elementAt(i)))
                    {
                      VecBez.addElement(TabSpalten.getS("Kennung"));
                      bBezOk=true;
                    }
//                    else
//                      bBezOk=false;
		}
	}

        private boolean Daten_in_die_Datenbank()
        {
          return iStt>0 ? StammDaten_in_die_Datenbank():BewDaten_in_die_Datenbank();
        }

        private boolean BewDaten_in_die_Datenbank()
        {
          TabRef.moveFirst();
          Tabellenspeicher Tab0=(Tabellenspeicher)TabRef.getInhalt("Tab");
          int iProt = g.Protokoll("Import",iAbfrageBegriff);
          SQL.update(g,"bew_pool","pro_aic_protokoll="+iProt,Tab0.getVecSpalte("aic_bew_pool"));
          SQL Qry=new SQL(g);
          //String s="update bew_pool set pro_aic_protokoll="+iProt+" where "+Qry.in("aic_bew_pool",Tab0.getVecSpalte("aic_bew_pool"));
          //Qry.exec(s);

          String sGueltig=null;
          for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
          {
            String sDatentyp = TabSpalten.getS("Datentyp");
            if (sDatentyp.equals("BewDatum"))
              sGueltig = TabSpalten.getS("Kennung");
          }
          TabFehler=new Tabellenspeicher(g,new String[] {"Zeile","Kennung","Fehler"});
          for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
          {
            if (!bDefImport)
              g.start();
            boolean bZeilenFehler=false;
            Qry.add("aic_protokoll",iProt);
            Qry.add("aic_bewegungstyp",-iStt);
            Qry.add("aic_mandant",g.getMandant());
            if (sGueltig != null)
            {
              Qry.add("Gueltig", TabDaten.getDate(sGueltig));
              Qry.add("LDATE",Static.DateToInt(TabDaten.getDate(sGueltig)));
            }
            else
              Qry.add("LDATE",0);
            Qry.add("LDATE2",0);
            Qry.add("LDATE3",0);
            Qry.add("BOOL1",0);
            Qry.add("BOOL2",0);
            Qry.add("LDateVon",0);
            Qry.add("LDateBis",0);
            if (g.hasZone(-iStt))
            	Qry.add("Zone",g.getZone());
            int iPool=Qry.insert("Bew_Pool",true);
            Date dtVon=null;
            double dDauer=0;
            for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
              if ((TabSpalten.getI("bits2")&Abfrage.cstSpNotSave)==0)
            {
              String sDatentyp = TabSpalten.getS("Datentyp");
              String sSpalte = TabSpalten.getS("Kennung");
              if (sDatentyp.equals("Filler"))
                ;
              else if(TabSpalten.getI("Stt")>0 && !sDatentyp.equals("Einheiten")  && !sDatentyp.equals("Mass") && !sDatentyp.equals("Waehrung"))
              {
                TabRef.posInhalt("Aic",TabSpalten.getI("Nummer"));
                int iAic=0;
                if (!TabDaten.getS(sSpalte).equals(""))
                {
                        Tabellenspeicher Tab=((Tabellenspeicher)TabRef.getInhalt("Tab"));
                        iAic=Tab.posInhalt(TabRef.getS("Eig2"),TabDaten.getInhalt(sSpalte))?Tab.getI("Aic_Stamm"):0;
                }
                //g.progInfo(TabSpalten.getS("Bezeichnung")+":"+iAic);
                int iRefEig=TabRef.getI("Eig1");
                TabRef.moveFirst();
                if (iAic==0 && !TabDaten.getS(sSpalte).equals(""))
                {
                  Static.printError(TabSpalten.getS("Bezeichnung") + " <" + TabDaten.getS(sSpalte) + "> nicht gefunden!");
                  addFehler(TabDaten.getPos() + 1, TabDaten.getS(sSpalte), "nicht gefunden");
                }
                else if (iAic>0)
                {
                  //g.fixInfo(TabDaten.getPos() + ": aic von " + TabDaten.getS(sSpalte) + "=" + iAic);
                  insertBewStamm(g,-iStt,iPool,iRefEig,iAic);
                  /*Qry.add("aic_stamm",iAic);
                  Qry.add("aic_eigenschaft",iRefEig);
                  Qry.add("aic_Bew_Pool",iPool);
                  Qry.insert("Bew_Stamm",false);*/

                  //if (g.isEigANR(iRefEig))
                  //  g.exec("update bew_pool set anr="+iAic+" where aic_bew_pool="+iPool);
                }
                //g.testInfo(AF.iStt+"/"+TabSpalten.getI("Stt")+"/"+sSpalte+":"+TabDaten.getS(sSpalte));
                if (iAic==0 && AF.iStt==TabSpalten.getI("Stt"))// && (AF.iBits&Abfrage.cstKeinStamm)==0)
                {
                  Static.printError("Zeile "+(TabDaten.getPos()+1)+": Hauptrelation "+TabDaten.getS(sSpalte)+" fehlt");
                  addFehler(TabDaten.getPos() + 1, TabDaten.getS(sSpalte), "Hauptrelation fehlt");
                  bFehler=true;
                  iFehler=3;
                  bZeilenFehler=true;
                }
                //else if(bNeu || !Static.Gleich(TabDaten.getInhalt(sSpalte),((Tabellenspeicher)TabRef.getInhalt("Tab")).getInhalt(sSpalte)))
                //        Save(g,iAic==0?null:new Integer(iAic),iRefEig,true,iStamm,iProt,bNeu,null);
              }
              else if (!sDatentyp.startsWith("Bew"))
                Save(g,TabDaten.getInhalt(sSpalte),TabSpalten.getI("Kennung2"),false,iPool,iProt,true,null,bErgaenzen);
              else if (sDatentyp.equals("BewZahl"))
              {
                if(TabDaten.getF(sSpalte) != 0.0)
                  Qry.exec("insert into bew_Wert (aic_bew_pool,aic_eigenschaft,spalte_wert) values (" + iPool + "," + TabSpalten.getI("Kennung2") + "," +TabDaten.getF(sSpalte) + ")");
              }
              else if (sDatentyp.equals("BewMass"))
              {
                if (TabDaten.getF(sSpalte) !=0.0)
                  Qry.exec("insert into bew_wert (aic_bew_pool,aic_eigenschaft,spalte_Wert,aic_stamm,grundwert) values ("+iPool+","+TabSpalten.getI("Kennung2")+","+
                           TabDaten.getF2(sSpalte)+","+TabSpalten.getI("Stamm")+","+(TabDaten.getF2(sSpalte)*g.getFaktor(TabSpalten.getI("Stamm")))+")");
              }
              else if (sDatentyp.equals("BewWaehrung"))
              {
                if (TabDaten.getF(sSpalte) !=0.0)
                   Qry.exec("insert into bew_wert (aic_bew_pool,aic_eigenschaft,spalte_Wert,aic_stamm) values (" + iPool + "," + TabSpalten.getI("Kennung2") + "," +
                            TabDaten.getF(sSpalte) + "," + (TabSpalten.getI("Stamm")==0 ? g.getWaehrung():TabSpalten.getI("Stamm")) + ")");

              }
              else if (sDatentyp.equals("BewDatum2"))
              {
                insertBewDatum2(g,-iStt,iPool,TabSpalten.getI("Kennung2"),TabDaten.getDate(sSpalte));
              }
              else if (sDatentyp.equals("BewBoolean"))
              {
                if (TabDaten.getB(sSpalte))
                  insertBewBool(g,-iStt,iPool,TabSpalten.getI("Kennung2"));
                  //Qry.exec("insert into bew_boolean (aic_bew_pool,aic_eigenschaft,spalte_boolean) values ("+iPool+","+TabSpalten.getI("Kennung2")+",1)");
              }
              else if (sDatentyp.equals("BewBool3"))
              {
                if (!TabDaten.isNull(sSpalte) && !TabDaten.getS(sSpalte).equals(Static.sLeer))
                  insertBewBool3(g,-iStt,iPool,TabSpalten.getI("Kennung2"),-g.getAuswahlAic(g.getAuswahlPosB(TabSpalten.getI("Kennung2"),TabDaten.getS(sSpalte))));
                  //Qry.exec("insert into Bew_Aic (aic_bew_pool,aic_eigenschaft,Aic) values ("+iPool+","+TabSpalten.getI("Kennung2")+","+g.getAuswahlAic(g.getAuswahlPosB(TabSpalten.getI("Kennung2"),TabDaten.getS(sSpalte)))+")");
              }
              else if (sDatentyp.equals("BewVon_Bis"))
              {
                VonBis vb=TabDaten.getInhalt(sSpalte) instanceof VonBis ? (VonBis)TabDaten.getInhalt(sSpalte):null;
                String sKZ=TabSpalten.getS("Stil");
                //g.fixtestError("Import BewVonBis:"+vb+"/"+sKZ+"/"+TabDaten.getInhalt(sSpalte));
                if (TabSpalten.getS("Format").equals("-"))
                {
                  Qry.exec("insert into bew_von_bis (aic_bew_pool,aic_eigenschaft,dauer) values (" + iPool + "," + TabSpalten.getI("Kennung2") + "," + vb.getSeconds() + ")");
                }
                else if (sKZ != null && (sKZ.equals("von") || sKZ.equals("bis") || sKZ.equals("Dauer")) )
                {
                	if (sKZ.equals("von"))
                		dtVon=TabDaten.getDate(sSpalte);
                	if (sKZ.equals("Dauer"))
                		dDauer=TabDaten.getF(sSpalte);
                	if (sKZ.equals("bis"))
                	{
                		Date dtBis=TabDaten.getDate(sSpalte);
                		if (dDauer==0)
                			dDauer=(dtBis.getTime() - dtVon.getTime())/1000.0;
                		insertBewVonBis(g,-iStt,iPool,TabSpalten.getI("Kennung2"),dtVon,dtBis,dDauer);
                	}
                }
                else if (vb!=null)
                {
                  double d = vb.hasFaktor() ? vb.getSeconds():(vb.getBis().getTime() - vb.getVon().getTime()) /1000.0;
                  insertBewVonBis(g,-iStt,iPool,TabSpalten.getI("Kennung2"),vb.getVon(),vb.getBis(),d);
//                  Qry.exec("insert into bew_von_bis (aic_bew_pool,aic_eigenschaft,von,bis,dauer) values (" + iPool + "," + TabSpalten.getI("Kennung2") + "," +
//                           g.DateTimeToString(vb.getVon()) + "," + g.DateTimeToString(vb.getBis()) + "," + String.valueOf(d) + ")");
                }
              }
              else if (!sDatentyp.equals("BewDatum"))
                Static.printError("Import.BewDaten_in_die_Datenbank: Datentyp <"+sDatentyp+"> wird noch nicht unterstützt!");
            }
            if (!bDefImport)
            	g.commit2(bZeilenFehler);
          }
          Qry.free();
          if (!bDefImport && !TabFehler.isEmpty() && Static.bX11)
            TabFehler.showGrid(sFilename);
          return true;
        }

        private boolean GleicheRolle(int iAic)
        {
          int iPos=g.TabEigenschaften.getPos("Aic",iAic);
          if (iPos>=0)
            return /*(g.TabEigenschaften.getI(iPos,"bits")&Global.cstEAneu)==0 ||*/ iRolle == g.TabEigenschaften.getI(iPos,"Rolle");
          else
            return true;
        }

        private static boolean EA_Rolle(Global g,int iAic,int iRolle)
        {
          int iPos=g.TabEigenschaften.getPos("Aic",iAic);
          return iPos>=0 && /*(g.TabEigenschaften.getI(iPos,"bits")&Global.cstEAneu)>0 &&*/ iRolle != g.TabEigenschaften.getI(iPos,"Rolle");
        }

        public static String Rolle(int iRolle)
        {
          return "aic_rolle"+(iRolle==0 ? " is null":"="+iRolle);
        }

        public static String insertBewStamm(Global g,SQL Qry,int iBew,int iPool,int iEig,int iStamm,boolean b) // für Planung
        {
          if (iStamm>0)
          {
            g.progInfo("insertBewStamm:"+iStamm+" bei "+iEig+"/"+iBew);
            Qry.exec2("insert into bew_stamm (aic_Bew_Pool,aic_Eigenschaft,aic_Stamm) values (" + iPool + "," + iEig + "," + iStamm + ")", b);
            String sANR=g.getANRS(iBew, iEig,0);
            if(sANR != null)
              Qry.exec2("update bew_pool set " + sANR + "=" + iStamm + " where aic_bew_pool=" + iPool, b);
            return sANR;
          }
          else
          {
            g.defInfo2("Import.insertBewStamm: Stamm="+iStamm+" bei "+g.TabErfassungstypen.getBezeichnungS(iBew)+"/"+g.TabEigenschaften.getBezeichnungS(iEig));
            return null;
          }
        }

        public static String insertBewStamm(Global g,int iBew,int iPool,int iEig,int iStamm)
        {
          if (iStamm>0)
          {
            //g.progInfo("insertBewStamm2:"+iStamm+" bei "+iEig+"/"+iBew);
            String s="insert into bew_stamm (aic_Bew_Pool,aic_Eigenschaft,aic_Stamm) values (" + iPool + "," + iEig + "," + iStamm + ")";
            String sANR=g.getANRS(iBew, iEig,0);
            setIndex(g,s,iPool,sANR,iStamm,0);
            return sANR;
          }
          else
          {
            g.defInfo2("Import.insertBewStamm: Stamm="+iStamm+" bei "+g.TabErfassungstypen.getBezeichnungS(iBew)+"/"+g.TabEigenschaften.getBezeichnungS(iEig));
            return null;
          }
        }

        public static void delBewStamm(Global g,int iBew,int iPool,int iEig)
        {
          String s="delete from bew_stamm where aic_bew_pool=" + iPool + " and aic_eigenschaft=" + iEig;
          String sANR=g.getANRS(iBew, iEig,0);
          if(sANR != null)
          {
            if (g.Oracle())
            {
              g.exec(s);
              s = "";
            }
            else
              s += ";";
            s += "update bew_pool set " + sANR + "=null where aic_bew_pool=" + iPool;
          }
          g.exec(s);
        }
        
        private static Date getDate(Object date0)
        {
        	return date0==null ? null:date0 instanceof Zeit ? ((Zeit)date0).getDate():(java.util.Date)date0;
        }

        public static void insertBewDatum2(Global g,int iBew,int iPool,int iEig,Object dt)
        {
        	
          String s="insert into bew_von_bis (aic_bew_pool,aic_eigenschaft,von,dauer) values (" + iPool + "," + iEig + "," +g.DateTimeToString(getDate(dt)) + ",0)";
          String sLDATE=g.getLDATE2(iBew,iEig);
          setIndex(g,s,iPool,sLDATE,Static.DateToInt(getDate(dt)),0);
        }
        
        public static void insertBewVonBis(Global g,int iBew,int iPool,int iEig,Object dtVon,Object dtBis,double dDauer)
        {
        	
          String s="insert into bew_von_bis (aic_bew_pool,aic_eigenschaft,von,bis,dauer) values (" + iPool + "," + iEig + "," +g.DateTimeToString(getDate(dtVon)) + "," +g.DateTimeToString(getDate(dtBis)) + ","+dDauer+")";
          //String sLDATE=g.getLDATE2(iBew,iEig);
          boolean b=g.isVB(iBew,iEig);
          setIndex(g,s,iPool,b ? "VB":null,Static.DateToInt(getDate(dtVon)),Static.DateToInt(getDate(dtBis)));
        }

        public static void delBewDatum2(Global g,int iBew,int iPool,int iEig)
        {
          String s="delete from bew_von_bis where aic_bew_pool=" + iPool + " and aic_eigenschaft=" + iEig;
          String sLDATE=g.getLDATE2(iBew,iEig);
          setIndex(g,s,iPool,sLDATE,0,0);
        }
        
        public static void delBewVonBis(Global g,int iBew,int iPool,int iEig)
        {
          String s="delete from bew_von_bis where aic_bew_pool=" + iPool + " and aic_eigenschaft=" + iEig;
          boolean b=g.isVB(iBew,iEig);
          setIndex(g,s,iPool,b ? "VB":null,0,0);
        }

        public static void insertBewBool(Global g,int iBew,int iPool,int iEig)
        {
          String s="insert into bew_Boolean (aic_bew_pool,aic_eigenschaft,spalte_boolean) values (" + iPool + "," + iEig + ",1)";
          String sBOOL=g.getBOOL(iBew,iEig);
          setIndex(g,s,iPool,sBOOL,1,0);
        }

        public static void delBewBool(Global g,int iBew,int iPool,int iEig)
        {
          String s="delete from bew_Boolean where aic_bew_pool=" + iPool + " and aic_eigenschaft=" + iEig;
          String sBOOL=g.getBOOL(iBew,iEig);
          setIndex(g,s,iPool,sBOOL,0,0);
        }

        public static void insertBewBool3(Global g,int iBew,int iPool,int iEig,int iNr)
        {
          int iAic=-iNr;
          if (iNr<0)
            ;//iNr=g.getAuswahlNr(iAic);
          else
            iAic=g.getAuswahlAic(g.getAuswahlPos(""+iNr, iEig));
          //int iPos=g.getAuswahlPos(""+iNr, iEig);
          String s="insert into Bew_Aic (aic_bew_pool,aic_eigenschaft,aic) values (" + iPool + "," + iEig + ","+iAic+")";
          String sBOOL=g.getBOOL(iBew,iEig);
          setIndex(g,s,iPool,sBOOL,iAic,0);
        }

        public static void delBewBool3(Global g,int iBew,int iPool,int iEig)
        {
          String s="delete from Bew_Aic where aic_bew_pool=" + iPool + " and aic_eigenschaft=" + iEig;
          String sBOOL=g.getBOOL(iBew,iEig);
          setIndex(g,s,iPool,sBOOL,0,0);
        }

        private static void setIndex(Global g,String s,int iPool,String sIndex,int iWert,int iWert2)
        {
          if(sIndex != null)
          {
            if (g.Oracle())
            {
              g.exec(s);
              s = "";
            }
            else
              s += ";";
            if (sIndex.equals("VB"))
            	s += "update bew_pool set LDateVon="+iWert+",LDateBis="+iWert2+" where aic_bew_pool=" + iPool;
            else
            	s += "update bew_pool set " + sIndex + "="+iWert+" where aic_bew_pool=" + iPool;
          }
          g.exec(s);
        }

        private void addFehler(int i,String sKennung,String sFehler)
        {
          TabFehler.addInhalt("Zeile",i);
          TabFehler.addInhalt("Kennung",sKennung);
          TabFehler.addInhalt("Fehler",sFehler);
        }

	private boolean StammDaten_in_die_Datenbank()
	{
		int iMax=iRolle>0 ? g.getAnzahlRolle(iRolle):g.getAnzahlStamm(iStt);
		int iMom=0;
		if(iMax!=-1)
		{
			iMom=SQL.getInteger(g,"select count(*) from stammview where aic_stammtyp="+iStt+" and aic_rolle"+(iRolle>0?"="+iRolle:" is null")+" and aic_mandant="+g.getMandant());
			if (iMax<iMom)
			{
				g.testInfo("AnzahlUeberschritten:"+iMom+"/"+iMax);
                                iFehler=1;
				new Message(Message.WARNING_MESSAGE,(JFrame)null,g,20).showDialog("AnzahlUeberschritten");
				return true;
			}
		}
		g.testInfo("Daten_in_die_Datenbank:"+iMom+"/"+iMax);
		//g.bSaveExec=true;
		g.printExec("Stamm-Import: "+sFilename,true);
		int iProt = g.Protokoll("Import",iAbfrageBegriff);
		String sRef=null;
		String sEintritt=null;
		String sAustritt=null;
                String sStichtag=null;
                String sPasswort=null;
                String sKennung=null;
                String sFirma=null;
		//boolean bKennung=false;
                //STICHTAG=g.getBegriffAicS("Anzeigeart","date");
                //g.progInfo("STICHTAG-Aic="+STICHTAG);
		for(TabSpalten.moveFirst();/*sRef==null && */!TabSpalten.eof();TabSpalten.moveNext())
		{
			String sDatentyp=TabSpalten.getS("Datentyp");
			if(sRef==null && TabSpalten.getI("Stt")==0 && (TabSpalten.getI("bits")&(Global.cstEindeutig*0x10000))>0)
			{
				//bKennung=sDatentyp.equals("Kennung");
				sRef=TabSpalten.getS("Kennung");
			}
                        if (sDatentyp.equals("Kennung") && TabSpalten.getI("Stt")==0)
                          sKennung=TabSpalten.getS("Kennung");
            else if (sDatentyp.equals("Eintritt") && GleicheRolle(TabSpalten.getI("Kennung2")))
				sEintritt=TabSpalten.getS("Kennung");
                        else if (sDatentyp.equals("EinAustritt") && GleicheRolle(TabSpalten.getI("Kennung2")))
                        {
                          sEintritt = TabSpalten.getS("Kennung");
                          sAustritt=TabSpalten.getS("Kennung");
                        }
                        else if (sDatentyp.equals("Stichtag"))
				sStichtag=TabSpalten.getS("Kennung");
			else if (sDatentyp.equals("Austritt") && GleicheRolle(TabSpalten.getI("Kennung2")))
				sAustritt=TabSpalten.getS("Kennung");
                        else if (sDatentyp.equals("BenutzerPasswort"))
				sPasswort=TabSpalten.getS("Kennung");
                        else if (TabSpalten.getS("Kennung2").startsWith(""+g.iEigFirma+"_"))
                          sFirma=TabSpalten.getS("Kennung2");
                        else if (sFirma==null && TabSpalten.getI("Stt")==g.iSttANR)
                          sFirma=TabSpalten.getS("Kennung2");
		}
//		g.fixtestError("Ref="+sRef+", Kennung="+sKennung);		
                TabFehler=new Tabellenspeicher(g,new String[] {"Zeile","Kennung","Fehler"});
		if (sRef==null)
                {
                  iFehler=2;
                  return false;
                }
		g.testInfo("sRef="+sRef);
		//String sBez=sRef; // !!! gehört durch Bezeichnungs-Kennung ersetzt
                Gau=!Static.bX11 ? null:new Gauge(0,TabDaten.getAnzahlElemente(null),"",g,false);
                Tabellenspeicher TabFirma=null;
                Tabellenspeicher TabH=null;
                if (sFirma != null)
                {
                  TabFirma=new Tabellenspeicher(g,"select aic_stamm,bezeichnung from stammview2 where aic_stammtyp="+g.iSttFirma,true);
                  TabH=new Tabellenspeicher(g,"select aic_stamm,sta_aic_stamm from poolview2 where aic_eigenschaft="+g.iEigFirma,true);
                }
		for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
		{
                  int iFirma=0;
			if(TabDaten.getInhalt(sRef)==null)
                          addFehler(TabDaten.getPos()+1,null,"Kennung leer");
				//g.testInfo("Kennung von Zeile "+(TabDaten.getPos()+1)+" ist leer!");
			else
			{
                          TabRef.moveFirst();
                          //g.progInfo("Import: suche "+TabDaten.getInhalt(sRef));
                          //TabDaten.bAusgabe=true;
                          Tabellenspeicher Tab0 = (Tabellenspeicher)TabRef.getInhalt("Tab");
                          boolean bNeu = !Tab0.posInhalt(sRef, TabDaten.getInhalt(sRef));
//                          g.fixInfo("Zeile "+(TabDaten.getPos()+1)+": "+TabDaten.getS(sRef)+" -> "+(bNeu ? "neu":"vorhanden")+", bBezOk="+bBezOk+", bKeineAustritte="+bKeineAustritte);                      
                          if (bNeu && (bKeineAustritte && TabDaten.getDate(sAustritt)!=null || !bBezOk))
                            continue;
                          if (bNurNeu && !bNeu)
                        	continue; 
                          if (!bNeu && Tab0.count(sRef, TabDaten.getInhalt(sRef)) > 1)
                            addFehler(TabDaten.getPos() + 1, TabDaten.getS(sRef), Tab0.count(sRef, TabDaten.getInhalt(sRef)) + "x vorhanden");
                          else
                          {
                            //TabDaten.bAusgabe=false;
                            if (bNeu && iMax != -1)
                            {
                              iMom++;
                              g.testInfo("momentan:" + iMom + "/" + iMax);
                              if (iMax < iMom)
                              {
                                addFehler(TabDaten.getPos() + 1, TabDaten.getS(sRef), "Anzahl erreicht");
                                iFehler=1;
                                g.testInfo("AnzahlErreicht:" + iMom + "/" + iMax);
                                if (Gau != null) Gau.setText("fertig", Gau.getMax());
                                new Message(Message.WARNING_MESSAGE, (JFrame)null, g, 20).showDialog("AnzahlErreicht");
                                return true;
                              }
                            }
                            int iStamm = bNeu ? 0 : Tab0.getI("Aic_Stamm");
                            //g.testInfo(TabDaten.getS(sRef)+": Aic="+iStamm+" , Anz="+iMom);
                            SQL Insert = new SQL(g);
                            if (bNeu)
                            {
//                            	g.fixInfo(TabDaten.getS(sRef)+" ist neu!");
                              // Stamm
                              Insert.add("aic_stammtyp", iStt);
                              //if (sKennung != null)
                              //  Insert.add("Kennung", TabDaten.getS(sKennung));
                              iStamm = Insert.insert("Stamm", true);
                              Tab0.newLine();
                              Tab0.setInhalt("aic_stamm", iStamm);
                              Tab0.setInhalt(sRef, TabDaten.getInhalt(sRef));
                              //if (g.Prog())
                              //  Tab0.showGrid();
                            }
                            /*else if (sKennung != null && !TabDaten.getS(sKennung).equals("") && Insert.getString("select kennung from stamm where aic_stamm=" + iStamm)==null)
                               //Insert.getString("select kennung from stamm where aic_stamm=" + iStamm).equals(""))
                            {
                              Insert.add("Kennung", TabDaten.getS(sKennung));
                              Insert.update("Stamm", iStamm);
                            }*/
                            if (iStt==g.iSttFirma)
                              iFirma=iStamm;
                            else if (sFirma != null)
                            {
                              TabSpalten.posInhalt("Kennung2",sFirma);
                              TabRef.posInhalt("Aic", TabSpalten.getI("Nummer"));
                              int iAic = 0;
                              String sSpalte = TabSpalten.getS("Kennung");
                              if (!TabDaten.getS(sSpalte).equals(""))
                              {
                                Tabellenspeicher Tab = ((Tabellenspeicher)TabRef.getInhalt("Tab"));
                                iAic = Tab.posInhalt(TabRef.getS("Eig2"), TabDaten.getInhalt(sSpalte)) ? Tab.getI("Aic_Stamm") : 0;
                              }
                              if (iAic>0)
                              {
                                while (iFirma==0 && TabH.posInhalt("aic_stamm",iAic))
                                {
                                  iAic=TabH.getI("sta_aic_stamm");
                                  if (TabFirma.posInhalt("aic_stamm",iAic))
                                  {
                                    iFirma=TabFirma.getI("aic_stamm");
                                    g.progInfo("verwende Firma:"+TabFirma.getS("Bezeichnung"));
                                  }
                                }
                              }
                            }
                            // Stamm_Protokoll
                            String sBez = VecBez.isEmpty() ? TabDaten.getS(sRef) : getBezeichnung();
                            String sKen= sKennung != null ? TabDaten.getS(sKennung):null;
                            if (bNeu)
                              Tab0.setInhalt("Bezeichnung", sBez);
                            Date dtEin = sEintritt == null ? Tab0.getDate("Eintritt") : TabDaten.getDate(sEintritt);
                            Date dtAus = sAustritt == null ? Tab0.getDate("Austritt") : TabDaten.getDate(sAustritt);
                            Date dtStichtag = sStichtag == null ? null : TabDaten.getDate(sStichtag);
                            if (Static.Leer(sBez))
                            	bBezOk=false;
                            if (!bNeu && !bBezOk)
                              sBez = Tab0.getS("Bezeichnung");
                            if (Gau != null) Gau.setText(sBez, TabDaten.getPos());
                            if (bNeu || iRolle == 0 && (!sBez.equals(Tab0.getS("Bezeichnung")) || (sKen!=null && !sKen.equals(Tab0.getS(sKennung))) || sEintritt != null && !Static.Gleich(dtEin, Tab0.getDate("Eintritt"))
                                || sAustritt != null && !Static.Gleich(dtAus, Tab0.getDate("Austritt"))))
                            {
                              Vector VecRolle = iRolle > 0 ? g.getVecRolle(iRolle) : null;
                              if (!bNeu)
                                Insert.exec("update Stamm_Protokoll set pro_aic_protokoll=" + iProt + " where pro_aic_protokoll is null and aic_stamm=" + iStamm +
                                    " and aic_rolle is null");
                              int iAnzSP = bNeu && VecRolle != null ? VecRolle.size() + 1 : 1;
                              for (int i = 0; i < iAnzSP; i++)
                              {
                                Insert.add("Aic_Stamm", iStamm);
                                Insert.add("aic_mandant", g.getMandant());
                                Insert.add0("Firma",iFirma);
                                //TODO ANR auch übernehmen
                                Insert.add("aic_protokoll", iProt);
                                Insert.addNr(iStamm);
                                Insert.add("Bezeichnung", sBez);
                                if (i == 0)
                                {
                                  if (dtEin != null)
                                    Insert.add("Eintritt", dtEin);
                                  if (dtAus != null)
                                    Insert.add("Austritt", dtAus);
                                }
                                Insert.add("Aic_Code", g.getBegriffAicS("Status", bNeu ? i == 0 ? "new" : "copy" : "change"));
                                if (iRolle > 0 && i < VecRolle.size())
                                  Insert.add("AIC_ROLLE", VecRolle.elementAt(i));
                                Insert.add("aic_stammtyp2", iStt);
                                if (sKennung != null)
                                    Insert.add("Kennung2", sKen);
                                Insert.insert("Stamm_Protokoll", false);
                              }
                            }
                            else if (iRolle > 0)
                            {
                              Vector VecRolle = g.getVecRolle(iRolle);
                              Tabellenspeicher Tab = new Tabellenspeicher(g,"select bezeichnung,Eintritt,Austritt,aic_rolle,ANR from stammview2 where aic_stamm=" + iStamm + " and aic_rolle" + Static.SQL_in(VecRolle), true);
                              for (int i = 0; i < VecRolle.size(); i++)
                              {
                                int iRolleMom = Tabellenspeicher.geti(VecRolle.elementAt(i));
                                boolean bVorhanden = Tab.posInhalt("aic_rolle", iRolleMom);
                                boolean bAnders2 = iRolle == iRolleMom && bVorhanden &&
                                    (bBezOk && !sBez.equals(Tab.getS("Bezeichnung")) || sEintritt != null && !Static.Gleich(dtEin, Tab.getDate("Eintritt"))
                                     || sAustritt != null && !Static.Gleich(dtAus, Tab.getDate("Austritt")));
                                if (!bVorhanden || bAnders2)
                                {
                                  if (bAnders2)
                                    Insert.exec("update Stamm_Protokoll set pro_aic_protokoll=" + iProt + " where pro_aic_protokoll is null and aic_stamm=" + iStamm +
                                        " and aic_rolle=" + iRolleMom);
                                  Insert.add("Aic_Stamm", iStamm);
                                  Insert.add("aic_mandant", g.getMandant());
                                  Insert.add0("Firma",iFirma);
                                  Insert.add0("ANR",Tab.getI("ANR"));
                                  Insert.add("aic_protokoll", iProt);
                                  Insert.addNr(iStamm);
                                  Insert.add("Bezeichnung", bBezOk ? sBez:Tab.getS("Bezeichnung"));
                                  if (bAnders2 && sEintritt == null && !Tab.isNull("Eintritt"))
                                    Insert.add("Eintritt", Tab.getDate("Eintritt"));
                                  else if (dtEin != null)
                                    Insert.add("Eintritt", dtEin);
                                  if (bAnders2 && sAustritt == null && !Tab.isNull("Austritt"))
                                    Insert.add("Austritt", Tab.getDate("Austritt"));
                                  else if (dtAus != null)
                                    Insert.add("Austritt", dtAus);
                                  Insert.add("Aic_Code", g.getBegriffAicS("Status", iRolle != iRolleMom ? "copy" : "change"));
                                  Insert.add("AIC_ROLLE", iRolleMom);
                                  Insert.add("aic_stammtyp2", iStt);
                                  if (sKennung != null)
                                      Insert.add("Kennung2", TabDaten.getS(sKennung));
                                  Insert.insert("Stamm_Protokoll", false);
                                }
                              }
                            }

                            Insert.free();

                            for (TabSpalten.moveFirst(); !TabSpalten.eof(); TabSpalten.moveNext())
                              if ((TabSpalten.getI("bits2") & Abfrage.cstSpNotSave) == 0)
                              {
                                String sDatentyp = TabSpalten.getS("Datentyp");
                                String sSpalte = TabSpalten.getS("Kennung");
                                Date dtStichtag2=getStichtag(dtStichtag,TabSpalten.getS("Kennung2"),TabDaten);
                                if (sDatentyp.equals("Filler"))
                                  ;
                                else if (TabSpalten.getI("Stt") > 0 && !sDatentyp.equals("Einheiten") && !sDatentyp.equals("Mass") && !sDatentyp.equals("Waehrung"))
                                {
                                  //if (!TabDaten.getS(sSpalte).equals(""))
                                  //{
                                  TabRef.posInhalt("Aic", TabSpalten.getI("Nummer"));
                                  int iAic = 0;
                                  if (TabDaten.getInhalt(sSpalte) instanceof HierarchieAnzeige)
                                	  iAic=((HierarchieAnzeige)TabDaten.getInhalt(sSpalte)).getValueStamm();
                                  else if (!TabDaten.getS(sSpalte).equals(""))
                                  {
                                    Tabellenspeicher Tab = ((Tabellenspeicher)TabRef.getInhalt("Tab"));
                                    iAic = Tab.posInhalt(TabRef.getS("Eig2"), TabDaten.getInhalt(sSpalte)) ? Tab.getI("Aic_Stamm") : 0;
                                  }
                                  //g.progInfo(TabSpalten.getS("Bezeichnung")+":"+iAic);
                                  int iRefEig = TabRef.getI("Eig1");
                                  TabRef.moveFirst();
                                  String sWert = TabDaten.getS(sSpalte);
                                  if (iAic == 0 && !sWert.equals(""))
                                  {
                                    if (sDatentyp.equals("Bezeichnung"))
                                    {
                                      iAic = SQL.getInteger(g, "select aic_stamm from stammview2 where aic_stammtyp=" + TabSpalten.getI("Stt") +
                                          " and bezeichnung='" + sWert + "'");
                                      if (iAic == 0)
                                      {
                                        SQL Qry = new SQL(g);
                                        Qry.add("aic_stammtyp", TabSpalten.getI("Stt"));
                                        iAic = Qry.insert("stamm", true);
                                        Qry.add("aic_stamm", iAic);
                                        Qry.add("aic_mandant", g.getMandant());
                                        Qry.add0("Firma",iFirma);
                                        Qry.add("Bezeichnung", sWert);
                                        Qry.add("aic_protokoll", iProt);
                                        Qry.add("aic_code", g.getBegriffAicS("Status", "new"));
                                        Qry.addNr(iAic);
                                        Qry.add("aic_stammtyp2", TabSpalten.getI("Stt"));
                                        if (sKennung != null)
                                            Insert.add("Kennung2", TabDaten.getS(sKennung));
                                        Qry.insert("stamm_protokoll", false);
                                      }
                                      Save(g, new Integer(iAic), iRefEig, true, iStamm, iProt, bNeu, dtStichtag2,bErgaenzen);
                                    }
                                    else
                                      Static.printError(TabSpalten.getS("Bezeichnung") + " <" + sWert + "> nicht gefunden!");
                                  }
                                  else if (bNeu || !Static.Gleich(TabDaten.getInhalt(sSpalte), ((Tabellenspeicher)TabRef.getInhalt("Tab")).getInhalt(sSpalte)))
                                    Save(g, iAic == 0 ? null : new Integer(iAic), iRefEig, true, iStamm, iProt, bNeu, dtStichtag2,bErgaenzen);
                                  //}
                                }
                                else if (sDatentyp.equals("Benutzer"))
                                {
                                  String sBenKennung=TabDaten.getS(sSpalte);
                                  if (Static.Leer(sBenKennung))
                                	  addFehler(TabDaten.getPos()+1,sBenKennung,"Benutzer-Kennung leer");
                                  else if (!Text.isKennung2(sBenKennung,false))
                                	  addFehler(TabDaten.getPos()+1,sBenKennung,"Benutzer-Kennung-Umlaute");
                                  //TODO Fehlerprüfung BenutzerKennung
                                  else
                                	  SaveBenutzer(g, sBenKennung, sPasswort == null ? null : TabDaten.getS(sPasswort), iStamm, sBez);
                                }
                                else if (sDatentyp.equals("Mass"))
                                {
                                  Tabellenspeicher Tab = ((Tabellenspeicher)TabRef.getInhalt("Tab"));
                                  Mass mass = new Mass(g,g.getMass(TabSpalten.getI("Stamm") == 0 ? Tab.getI("V" + sSpalte.substring(1)) : TabSpalten.getI("Stamm"),
                                      (TabSpalten.getI("bits") & Abfrage.cstKeineEinheit) > 0), new Double(Tab.getF(sSpalte)), TabSpalten.getS("Format"));
                                  if (bNeu || !Static.Gleich(TabDaten.getInhalt(sSpalte), mass))
                                    Save(g, TabDaten.getInhalt(sSpalte), TabSpalten.getI("Kennung2"), true, iStamm, iProt, bNeu, dtStichtag2,bErgaenzen);
                                }
                                else if (!sDatentyp.endsWith("Bezeichnung") && !sDatentyp.equals("Kennung") && !sDatentyp.equals("Stichtag") && !sDatentyp.equals("BenutzerPasswort")
                                          && ((!sDatentyp.equals("Eintritt") && !sDatentyp.equals("Austritt") && !sDatentyp.equals("EinAustritt")) || EA_Rolle(g, TabSpalten.getI("Kennung2"), iRolle)))
                                  if (bNeu || !Static.Gleich(TabDaten.getInhalt(sSpalte), ((Tabellenspeicher)TabRef.getInhalt("Tab")).getInhalt(sSpalte)))
                                    Save(g, TabDaten.getInhalt(sSpalte), TabSpalten.getI("Kennung2"), true, iStamm, iProt, bNeu, dtStichtag2,bErgaenzen);
                              }
                          }
                        }
		}
                if (Gau != null) Gau.setText("fertig",Gau.getMax());
                if (!bDefImport && !TabFehler.isEmpty() && Static.bX11)
                  TabFehler.showGrid(sFilename);
		//g.bSaveExec=false;
                if (Gau != null) Gau.free();
		return true;
	}

        private Date getStichtag(Date dtStichtag,String sSpalte,Tabellenspeicher TabDaten)
        {
          /*int iAnz=TabSpalten.getAnzahlElemente(null);
          for (int i=0;i<iAnz;i++)
            if (TabSpalten.getI("Anzeige")==STICHTAG &&)*/
          int iPos=sSpalte.indexOf('_');
          if (iPos>0)
            sSpalte=sSpalte.substring(0,iPos);
          if (TabDaten.exists("A"+sSpalte))
            return TabDaten.getDate("A"+sSpalte);
          else
            return dtStichtag;
        }

	private void SaveBenutzer(Global g,String sKennung,String sPW,int iStamm,String sBezeichung)
	{
          int iAIC_Benutzer=SQL.getInteger(g,"select aic_benutzer from benutzer where kennung='"+sKennung+"' and aic_mandant="+g.getMandant());
		//if (!SQL.exists(g,"select aic_benutzer from benutzer where kennung='"+sKennung+"' and aic_mandant="+g.getMandant()))
		//{
			SQL Qry=new SQL(g);
			Qry.add("Kennung",sKennung);
//			Qry.add("Passwort",g.PasswordConvert(sPW==null ? Static.sDefaultPW:sPW,g.PWVH,0));
			Qry.add("Aic_mandant",g.getMandant());
			Qry.add("Aic_stamm",iStamm);
			Qry.add("Bits",Global.cstPW_MD5B);
			Qry.add("aic_logging",g.getLog());
            if (iAIC_Benutzer>0)
              Qry.update("Benutzer",iAIC_Benutzer);
            else
            {
              Qry.addnow("PW_Date");
              Qry.addnow("seit");
              iAIC_Benutzer=Qry.insert("Benutzer",true);
              Qry.add("Passwort",g.PasswordConvert(sPW==null ? Static.sDefaultPW:sPW,g.PWVH, iAIC_Benutzer));
          	  Qry.update("Benutzer",iAIC_Benutzer);
            }
            Qry.free();
			g.TabTabellenname.posInhalt("Kennung","BENUTZER");
			g.setBezeichnung("",sBezeichung,g.TabTabellenname.getI("AIC"),iAIC_Benutzer,0);
		//}
	}

	private String getBezeichnung() // ermittelt die zusammengesetzte Bezeichnung
	{
		String s = "";
		String st= "";
		for (int i=0;i<VecBez.size();i++)
		{
			if (VecBez.elementAt(i) == null)
				st = st + " ";
			else
			{
				String sInhalt = TabDaten.getS(VecBez.elementAt(i));
				if (!sInhalt.equals(""))
					s = s+st+sInhalt;
				st="";
			}
		}
                s=s.trim();
		if (s.length() > 250)
			s = s.substring(0,250);
		return s;
	}

	public static String getString(Object Obj)
	{
		if(Obj instanceof AUTextArea)
			return ((AUTextArea)Obj).getValue();
		else if(Obj instanceof AUEkitCore)
			return ((AUEkitCore)Obj).getValue();
//		else if(Obj instanceof HtmlEingabe)
//			return ((HtmlEingabe)Obj).getValue();
		else if(Obj instanceof JPasswordField)
			return new String(((JPasswordField)Obj).getPassword());
		else if(Obj instanceof PW1)
			return ((PW1)Obj).getPW();
		else if(Obj instanceof EMail)
			return ((EMail)Obj).getValue();
		else if(Obj instanceof FileEingabe)
			return ((FileEingabe)Obj).getValue();
		else if(Obj instanceof WWW)
			return ((WWW)Obj).getValue();
		else if(Obj instanceof ComboSort)
			return ""+((ComboSort)Obj).getSelectedItem();
		else if(Obj instanceof Memo1)
			return ((Memo1)Obj).getValue();
		else if(Obj instanceof String)
			return (String)Obj;
		else if(Obj instanceof BildEingabe)
			return ((BildEingabe)Obj).getFilename();
                else if(Obj instanceof Text)
			return ((Text)Obj).getText();
		else
		{
			Static.printError("Import.getString: "+Static.className(Obj)+" steht nicht zur Verfügung!");
			return ""+Obj;
		}
	}

	public static int getAicDaten(Global g,String sDatentyp,Object Obj,int iEig)
	{
		String sTab=sDatentyp.startsWith("String") || sDatentyp.equals("Text") || sDatentyp.equals("Bild2") || sDatentyp.equals("Doku") || sDatentyp.equals("GPS") ? sDatentyp.equals("StringKurzOhne") ? "Stringx"/*30*/:sDatentyp:
			sDatentyp.equals("E-Mail") || sDatentyp.equals("FixDoku") ? "Stringx"/*60*/:sDatentyp.equals("Passwort") ? "Stringx"/*30*/:
			sDatentyp.equals("WWW") || sDatentyp.equals("Memo") || sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") ? "Stringx"/*255*/:
			sDatentyp.endsWith("Bild") ? "Image":
			sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("Zeit") ? "Zeit":null;
		if (sTab==null)
			return 0;
		if (sTab.equals("Zeit"))
		{
			SQL Insert = new SQL(g);
			int iNr = Insert.insert("Daten",true);

			Date dtVon=Obj instanceof VonBis ? ((VonBis)Obj).getVon():Obj instanceof VonBisEingabe ? ((VonBisEingabe)Obj).getVon():
				Obj instanceof Datum ? ((Datum)Obj).getDateTime() :Obj instanceof Date ? ((Date)Obj):Obj instanceof Zeit ? ((Zeit)Obj).getDate():null;
			Date dtBis=Obj instanceof VonBis ? ((VonBis)Obj).getBis():Obj instanceof VonBisEingabe ? ((VonBisEingabe)Obj).getBis():null;
			//g.fixtestError("getAicDaten von Zeit:"+dtVon+"/"+dtVon+" bei "+Static.print(Obj)+":"+iNr);
			Insert.add("Aic_Daten",iNr);			
			if (dtVon instanceof Date)
                          Insert.add("Zeit_von",dtVon);
                        else
                          Insert.add("Zeit_von",(java.sql.Timestamp)dtVon);
			if (sDatentyp.equals("Auto_von_bis") && dtBis==null)
			{
				int iPosE=g.TabEigenschaften.getPos("Aic",iEig);
				Datum dt = new Datum(g,Static.beiLeer(g.TabEigenschaften.getS(iPosE,"Format"),"yyyy/MM/dd HH:mm:ss"));
				dt.setDate(new Date());
				Insert.add("Zeit_bis",dt.getDateTime());
			}
			else if (dtBis != null)
				Insert.add("Zeit_bis",(java.sql.Timestamp)dtBis);
			Insert.insert("Zeit_von_bis",false);
            Insert.free();
			return iNr;
		}
                else if (sTab.equals("Bild2") || sTab.equals("Doku"))
                {
                  SQL Insert = new SQL(g);
                  int iNr = 0;
                  //g.fixtestInfo("Save "+sTab+": "+Static.print(Obj));
                  if (Obj instanceof FileEingabe)
                    iNr=((FileEingabe)Obj).getDatenAic();
                  else if(sTab.equals("Doku") && Obj instanceof String)
                  {
                    String s=(String)Obj;
                    if (s.indexOf("_")>0 && s.indexOf(File.separator)<0)
                      iNr=Integer.parseInt(s.substring(0,s.indexOf("_")));
                  }
                  if (iNr==0)
                  {
                    iNr = Insert.insert("Daten", true);
                    Insert.free();
                    String s = getString(Obj);
                    if (s != null && !s.equals(Static.sLeer))
                    {
                      if (s.indexOf(File.separator) < 0 && s.indexOf(":") < 0)
                      {
                    	  g.printError(sTab+" "+s+" nicht speicherbar, da kein Pfad vorhanden");
                    	  return 0;
//                        s = (sTab.equals("Bild2") ? Static.DirImageStamm : Static.DirDoku) + s;
                      }
                      if (!saveImage(g,sTab,s,iNr))
                      {
                    	  Insert.exec("delete from daten where aic_daten="+iNr);
                    	  return 0;
                      }
                    }
                  }
                  return iNr;
                }
                else if (sTab.equals("GPS"))
                {
                	int iNr = 0;
                	if (Obj instanceof GPS)
                	{
                		iNr=((GPS)Obj).getDaten();
                		if (iNr>0)
                			return iNr;
                	}
                	else if (Obj instanceof GPS_Eingabe)
                		Obj=new GPS((GPS_Eingabe)Obj);               	
                	if (Obj !=null && Obj instanceof GPS)
                	{
                		String sName=((GPS)Obj).getName();
                		int iMaps=((GPS)Obj).getMap();
                		double dLat=((GPS)Obj).getLat();
                		double dLng=((GPS)Obj).getLng();
                		iNr=SQL.getInteger(g, "select aic_daten from daten_GPS where abs(lat-"+dLat+")<1e-5 and abs(lng-"+dLng+")<1e-5 and name="+Static.StringForSQL(sName,50)+" and map="+iMaps);
                		if (iNr==0)
                		{
		                	SQL Insert = new SQL(g);
		                	iNr = Insert.insert("Daten",true);
		                	Insert.add("Aic_Daten",iNr);                	
		                	Insert.add("lat",dLat);
		                	Insert.add("lng",dLng);
		                	Insert.add("name",sName,50);
		                	Insert.add("map",iMaps);	                	
		                	Insert.insert("Daten_GPS",false);
		                	Insert.free();
                		}
                		else
                			g.fixtestError("GPS gefunden: "+iNr);
                	}
                	return iNr;
                }
		else
		{
			String s=getString(Obj);
			/*if (sTab.equals("Stringx") && !Static.bStringX)
			{
				int iL=s.length();
				sTab=iL<11?"StringSehrKurz":iL<31?"StringKurz":iL<61?"String60":iL<256?"StringLang":"Text";
			}*/
			int iLmax=sTab.equals("Image") ? 100:sTab.equals("Stringx") ? 255:Static.iMemoMax;
			String sSpalte=sTab.equals("Image") ? "Filename":"Spalte_"+sTab;
			String sSpalteW=sDatentyp.equals("Text") && g.MS() ? "CONVERT(VARCHAR,"+sSpalte+")":sSpalte;
			String s2=g.S(s,iLmax);
			Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_daten,"+sSpalte+" Inhalt from daten_"+sTab+" where "+sSpalteW+"="+s2,true);
            if (Tab.posInhalt("Inhalt",new All_Unlimited.Allgemein.Anzeige.Memo1(s)))
			{
				g.progInfo(sDatentyp+": Datensatz "+Tab.getI("aic_daten")+" verwendet!");
				return Tab.getI("aic_daten");
			}

			SQL Insert = new SQL(g);
			int iNr = Insert.insert("Daten",true);

			Insert.add("Aic_Daten",iNr);
			Insert.add2(sSpalte,s2);
			Insert.insert("Daten_"+sTab,false);
			if (s != null && s.length()>iLmax)
				g.fixtestError("Import.getAicDaten: "+s.substring(0,30)+"..("+sTab+") gekürzt von "+s.length()+" auf "+iLmax);
//			if (g.ASA() && sTab.equals("Text"))
//				g.writetext("Daten_Text","Spalte_Text","AIC_Daten="+iNr,s);
            Insert.free();
			return iNr;
		}

	}

        public static boolean isNull(Object Obj)
        {
                boolean bIsNull;
                if(Obj==null)
                        bIsNull = true;
                else if(Obj instanceof Boolean)
                        bIsNull = !((Boolean)Obj).booleanValue();
                else if(Obj instanceof java.sql.Timestamp || Obj instanceof java.util.Date || Obj instanceof Image)
                        bIsNull = false;
                else if(Obj instanceof Double)
                        bIsNull = ((Double)Obj).isNaN() || ((Double)Obj).isInfinite() || ((Double)Obj).doubleValue()==0.0;
                else if(Obj instanceof JCheckBox)
                        bIsNull = !((JCheckBox)Obj).isSelected();
                else if(Obj instanceof Datum)
                        bIsNull = ((Datum)Obj).isNull();
                else if(Obj instanceof DateWOD)
                    bIsNull = ((DateWOD)Obj).isNull();
                else if(Obj instanceof ComboSort)
                        bIsNull = ((ComboSort)Obj).isNull();
                else if(Obj instanceof AUTextArea)
                        bIsNull = ((AUTextArea)Obj).isNull();
                else if(Obj instanceof AUEkitCore)
                    bIsNull = ((AUEkitCore)Obj).isNull();
//                else if(Obj instanceof HtmlEingabe)
//                        bIsNull = ((HtmlEingabe)Obj).isNull();
                else if(Obj instanceof Zahl)
                        bIsNull = ((Zahl)Obj).isNull();
                else if(Obj instanceof Zahl2)
                        bIsNull = ((Zahl2)Obj).Edt.isNull();
                else if(Obj instanceof MassEingabe)
                        bIsNull = ((MassEingabe)Obj).isNull();
                else if(Obj instanceof WaehrungsEingabe)
                        bIsNull = ((WaehrungsEingabe)Obj).isNull();
                else if(Obj instanceof VonBisEingabe)
                        bIsNull = ((VonBisEingabe)Obj).isNull();
                else if(Obj instanceof BildEingabe)
                        bIsNull = ((BildEingabe)Obj).isNull();
                else if(Obj instanceof RolleEingabe)
                        bIsNull = ((RolleEingabe)Obj).isNull();
                else if(Obj instanceof GPS)
                    bIsNull = ((GPS)Obj).isNull();
                else if(Obj instanceof GPS_Eingabe)
                    bIsNull = ((GPS_Eingabe)Obj).isNull();
                else if(Obj instanceof Text)
                        bIsNull = ((Text)Obj).isNull();
                else if(Obj instanceof WWW)
                        bIsNull = ((WWW)Obj).isNull();
                else if(Obj instanceof AUCheckBox)
                        bIsNull = ((AUCheckBox)Obj).isNull();
                else if(Obj instanceof JCOutliner)
                        bIsNull = ((JCOutliner)Obj).getSelectedNode().getLevel()==0;
                else if(Obj instanceof Vector)
                        bIsNull = ((Vector)Obj).size()==0 || ((Vector)Obj).elementAt(0).equals("");
                else if(Obj instanceof EMail)
                        bIsNull = ((EMail)Obj).isNull();
                else if(Obj instanceof FileEingabe)
                        bIsNull = ((FileEingabe)Obj).getValue().equals("");
                else if(Obj instanceof String)
                        bIsNull = ((String)Obj).equals("");
                else if(Obj instanceof Farbe)
                    bIsNull = ((Farbe)Obj).intValue()==0;
                else if(Obj instanceof Integer)
                        bIsNull = ((Integer)Obj).intValue()==0;
                else if(Obj instanceof Long)
                        bIsNull = ((Long)Obj).longValue()==0;
                else if(Obj instanceof Combo)
                        bIsNull = ((Combo)Obj).getAic()==0;
                else if(Obj instanceof VonBis)
                        bIsNull = ((VonBis)Obj).isNull();
                else if(Obj instanceof Zahl1)
                        bIsNull = ((Zahl1)Obj).isNull();
                else if(Obj instanceof JaNein)
                {
                        Boolean b = ((JaNein)Obj).getValue();
                        bIsNull = b==null || !b.booleanValue();
                }
                else if(Obj instanceof Zeit)
                        bIsNull = ((Zeit)Obj).isNull();
                else if(Obj instanceof Mass)
                        bIsNull = ((Mass)Obj).isNull();
                else if(Obj instanceof Waehrung)
                        bIsNull = ((Waehrung)Obj).isNull();
                else if(Obj instanceof HierarchieAnzeige)
                        bIsNull = ((HierarchieAnzeige)Obj).getValueStamm()==0;
                else if(Obj instanceof Memo1)
                        bIsNull = ((Memo1)Obj).isNull();
                else if(Obj instanceof AUFarbe)
                        bIsNull = ((AUFarbe)Obj).isNull();
                else if(Obj instanceof AUBits)
                        bIsNull = ((AUBits)Obj).isNull();
                else if(Obj instanceof AUPasswort)
                        bIsNull = ((AUPasswort)Obj).isNull();
                else if(Obj instanceof PW1)
                        bIsNull = ((PW1)Obj).isNull();
                else if(Obj instanceof Html)
                        bIsNull = ((Html)Obj).isNull();
                else if(Obj instanceof JTextField)
                  bIsNull = ((JTextField)Obj).getText().equals("");
                else if (Obj instanceof javax.swing.JLabel)
                  bIsNull = true;
                else
                {
                        Static.printError("Import.isNull(): Objekt "+Obj.getClass().getName()+" kann nicht auf null geprüft werden");
                        return true;
                }
                //System.out.println("isNull:"+Obj+"/"+Static.className(Obj)+"->"+bIsNull);
                return bIsNull;
        }

	public static void Save(Global g,Object Obj,int iEig,boolean bStamm,int iAic,int iProt,boolean bNeu,Date dtStichtag,boolean bErgaenzen)
	{
//		g.fixtestError("Import.Save:"+Obj+", Eig="+iEig+", Stamm="+iAic+"/"+bErgaenzen+"  bei Stichtag="+dtStichtag);
      if (bErgaenzen && isNull(Obj))
        return;
      boolean bMF=iEig<0;
      if (bMF)
        iEig=-iEig;
      int iPosE=g.TabEigenschaften.getPos("Aic",iEig);
      String sDatentyp=iPosE>=0 ? g.TabEigenschaften.getS(iPosE,"Datentyp"):"";
      //g.fixtestError("Save "+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"/"+sDatentyp+":"+Obj+"/"+iAic+"/"+iProt);
      if (sDatentyp.equals("Stichtag") || sDatentyp.equals("Filler") || sDatentyp.startsWith("Calc"))
        return;
      String sRef=(bStamm?"aic_stamm=":"aic_bew_pool=")+iAic;
      Date dtBis=null;
      boolean bDelStammPool=false;
      boolean bStammProt=sDatentyp.equals("Bezeichnung") || sDatentyp.equals("Kennung") || sDatentyp.equals("Eintritt") || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Austritt");

	  boolean bDatenNotwendig = sDatentyp.startsWith("String") || sDatentyp.equals("Text") || sDatentyp.endsWith("Bild") || sDatentyp.equals("Bild2") || sDatentyp.equals("von_bis") || sDatentyp.equals("GPS") ||
				sDatentyp.equals("Zeit") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("Memo") || sDatentyp.equals("E-Mail") || sDatentyp.equals("FixDoku") || sDatentyp.equals("Doku") ||
				sDatentyp.equals("Passwort") || sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") || sDatentyp.equals("WWW");
			if (!bDatenNotwendig && !bStammProt && !sDatentyp.equals("Gruppe") && !sDatentyp.equals("Hierarchie") && !sDatentyp.equals("Datum") && //!sDatentyp.equals("Bezeichnung") && !sDatentyp.equals("Kennung") &&
				!sDatentyp.equals("Boolean") && !sDatentyp.equals("Bool3") && !sDatentyp.equals("Integer") && !sDatentyp.equals("Double") && !sDatentyp.equals("Farbe") && !sDatentyp.equals("Mehrfach") &&
                                !sDatentyp.equals("Mass") && !sDatentyp.equals("Einheiten") && !sDatentyp.equals("Waehrung"))
			{
				Static.printError("Import.Save: Datentyp <"+sDatentyp+","+iEig+"> wird noch nicht unterstützt!");
				return;
			}

			int iNr=bDatenNotwendig && !isNull(Obj) ? getAicDaten(g,sDatentyp,Obj,iEig):0;
      int iGr=0;
      if (bMF || sDatentyp.equals("Gruppe") || sDatentyp.equals("Hierarchie"))
        iGr=bMF ? iAic:Sort.geti(Obj);
      // g.fixtestError(sDatentyp+"/"+iEig+" -> Nr="+iNr);
    boolean bGleich=false;
		if (!bNeu && !bStammProt)
                  if (dtStichtag!=null && !sDatentyp.equalsIgnoreCase("Datum"))
                  {
                      Tabellenspeicher TabVergleich=new Tabellenspeicher(g,"select aic_daten,sta_aic_stamm,Spalte_Double,gultig_von,gueltig_bis,aic_stammpool from stammpool where pro2_aic_protokoll is null and "+sRef+" and aic_eigenschaft="+iEig+" order by "+g.orderD("gultig_von"),true);
                      //TabVergleich.showGrid("Vergleich "+TabEigenschaft.getS("Kennung"));
                      long lStichtag=dtStichtag.getTime();
                      int iMomPool=0;
                      SQL Insert = new SQL(g);
                      for(TabVergleich.moveFirst();!TabVergleich.eof();TabVergleich.moveNext())
                      {
                              //g.progInfo("gultig_von="+TabVergleich.getInhalt("gultig_von")+"/"+TabVergleich.getDate("gultig_von"));
                              Date dtVon=TabVergleich.getDate("gultig_von");
                              long lVon=dtVon==null ? Long.MIN_VALUE : dtVon.getTime();
                              g.progInfo("lStichtag="+lStichtag+", lVon="+lVon+", iMomPool="+iMomPool);
                              bGleich=iNr>0 && iNr==TabVergleich.getI("aic_daten") || iGr>0 && iGr==TabVergleich.getI("sta_aic_stamm") && TabVergleich.isNull("Spalte_Double");
                              if (lStichtag==lVon)
                              {
                                if (bGleich) return;
                                Insert.exec("update stammpool set pro2_aic_protokoll="+iProt+" where aic_stammpool="+TabVergleich.getI("aic_stammpool"));
                                TabVergleich.moveLast();
                                dtBis=SQL.getTimestamp(g,"select min(gultig_von) from stammpool where pro2_aic_protokoll is null and "+sRef+" and aic_eigenschaft="+iEig+" and gultig_von>"+g.DateTimeToString(dtVon));
                                //g.progInfo("Stichtag ist gleich");
                                iMomPool=0;
                              }
                              else if (lVon>lStichtag)
                              {
                                      dtBis=dtVon;
                                      TabVergleich.moveLast();
                                      g.progInfo("Bis="+dtBis);
                                      bGleich=false;
                                      // g.exec("update stammpool set pro_aic_protokoll="+iProt+",gueltig_bis="+g.DateTimeToString(dtStichtag)+" where pro2_aic_protokoll is null and "+sRef+" and aic_eigenschaft="+iEig+" and gueltig_bis="+g.DateTimeToString(dtVon));
                              }
                              else if (TabVergleich.isNull("gueltig_bis") || TabVergleich.getDate("gueltig_bis").getTime()>lStichtag)
                                      iMomPool=TabVergleich.getI("aic_stammpool");
                      }
                      if (iMomPool>0)
                      {
                        if (bGleich) return;
                        Insert.add("pro_aic_protokoll",iProt);
                        if (dtStichtag != null && dtStichtag.before(Static.dt0))
                          Insert.add("pro2_aic_protokoll", iProt);
                        Insert.add("gueltig_bis",dtStichtag);
                        Insert.update("StammPool",iMomPool);
                      }
                      Insert.free();
                  }
                  else if (!bStammProt)
                	  bDelStammPool=true;
                
		if (isNull(Obj) && bDelStammPool)
		{
			if (bStamm)
        g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and "+sRef+" and aic_eigenschaft="+iEig);
      else
        g.exec("delete from stammpool where "+sRef+" and aic_eigenschaft="+iEig);
		}
		if (!isNull(Obj) || sDatentyp.equals("Kennung"))
		{
			if (sDatentyp.equals("SysAic"))
                          return;

                        if (bStammProt && EA_Rolle(g,iEig,-1))
                        {
                          SQL Insert = new SQL(g);
                          g.progInfo("Eintritt geändert bei "+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+" (Rolle="+g.TabEigenschaften.getI(iPosE,"Rolle")+")");
                          Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from stamm_protokoll where aic_stamm="+iAic+" and "+Rolle(g.TabEigenschaften.getI(iPosE,"Rolle"))+" and pro_aic_protokoll is null",true);
                          if (Tab==null || Tab.isEmpty())
                          {
                            Insert.add("AIC_STAMM",iAic);
                            Insert.add("aic_mandant",g.getMandant());
                            //Insert.add0("Firma",iFirma);
                            Insert.add("AIC_PROTOKOLL",iProt);
                            Insert.add("Bezeichnung",sDatentyp.equals("Bezeichnung") ? ""+Obj:SQL.getString(g,"select bezeichnung from stammview2 where aic_rolle is null and aic_stamm="+iAic));
                            Insert.add0("AIC_ROLLE",g.TabEigenschaften.getI(iPosE,"Rolle"));
                            Insert.add("AIC_CODE",g.getBegriffAicS("Status", bNeu ? "new" : "change"));
                            if (sDatentyp.equals("Eintritt") || sDatentyp.equals("EinAustritt"))
                              Insert.add("EINTRITT",getDate(Obj));
                            if (sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt"))
                              Insert.add("AUSTRITT",getDate(Obj));
                            if (sDatentyp.equals("Kennung"))
                              Insert.add("Kennung2",""+Obj);
                            Insert.addNr(iAic);
                            Insert.add("aic_stammtyp2", SQL.getString(g,"select aic_stammtyp from stamm where aic_stamm="+iAic));
                            Insert.insert("STAMM_PROTOKOLL",false);
                          }
                          else if (iProt != Tab.getI("aic_protokoll"))
                          {
                            g.exec("update stamm_protokoll set pro_aic_protokoll="+iProt+" where aic_stamm="+iAic+" and "+Rolle(g.TabEigenschaften.getI(iPosE,"Rolle"))+" and pro_aic_protokoll is null");
                            Insert.add("AIC_STAMM",iAic);
                            Insert.add("aic_mandant",g.getMandant());
                            Insert.add0("Firma",Tab.getI("Firma"));
                            Insert.add0("ANR",Tab.getI("ANR"));
                            Insert.add("AIC_PROTOKOLL",iProt);
                            Insert.add("Bezeichnung",sDatentyp.equals("Bezeichnung") ? ""+Obj:Tab.getS("Bezeichnung"));
                            Insert.add0("AIC_ROLLE",g.TabEigenschaften.getI(iPosE,"Rolle"));
                            Insert.add("AIC_CODE",g.getBegriffAicS("Status","change"));
                            Insert.add("EINTRITT",sDatentyp.equals("Eintritt") || sDatentyp.equals("EinAustritt") ? getDate(Obj):Tab.getDate("Eintritt"));
                            Insert.add("AUSTRITT",sDatentyp.equals("EinAustritt") || sDatentyp.equals("Austritt") ? getDate(Obj):Tab.getDate("Austritt"));
                            Insert.add("Kennung2",sDatentyp.equals("Kennung") ? ""+Obj:Tab.getS("Kennung2"));
                            Insert.addNr(iAic);
                            Insert.add("aic_stammtyp2", Tab.getI("aic_stammtyp2"));
                            Insert.insert("STAMM_PROTOKOLL",false);
                          }
                          else
                            g.exec("update stamm_protokoll set "+(sDatentyp.equals("Bezeichnung") ? "Bezeichnung="+Static.StringForSQL((String)Obj,255):sDatentyp.equals("Kennung") ? "KENNUNG2="+Static.StringForSQL((String)Obj,40):
                            	sDatentyp.equals("Eintritt") || sDatentyp.equals("EinAustritt") ?"EINTRITT="+g.DateTimeToString(getDate(Obj)):"")+
                                   (sDatentyp.equals("EinAustritt")?",":"")+(sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt")?"AUSTRITT="+g.DateTimeToString(getDate(Obj)):"")+
                                   " where aic_stamm="+iAic+" and "+Rolle(g.TabEigenschaften.getI(iPosE,"Rolle"))+" and pro_aic_protokoll is null");
                          return;
                        }


			//g.progInfo(iEig+":"+Obj+" ("+sDatentyp+")");
			//if (sDatentyp.equals("StringKurzOhne"))
			//	sDatentyp = "StringKurz";
			// Daten
			//int iNr=0;

			//Stamm-Pool
			SQL Insert = new SQL(g);
			if (iNr > 0)
				Insert.add("Aic_Daten",iNr);
			Insert.add("Aic_Protokoll",iProt);
                        int iStamm=bMF ? Sort.geti(Obj):iAic;
			Insert.add(bStamm?"Aic_Stamm":"Aic_Bew_Pool",iStamm);
			Insert.add("Aic_Eigenschaft",iEig);

                        if (sDatentyp.equalsIgnoreCase("Datum"))
                                Insert.add("GULTIG_VON",Tabellenspeicher.getdate(Obj));
                        else
                                Insert.add("GULTIG_VON",dtStichtag);

			if (bMF || sDatentyp.equals("Gruppe") || sDatentyp.equals("Hierarchie"))
			{
//				g.fixtestError("Speichere "+sDatentyp+":"+Static.print(Obj));
                          int iAicMom=bMF ? iAic:Sort.geti(Obj);
                          Insert.add("STA_Aic_Stamm",iAicMom);
                          if (bStamm && sDatentyp.equals("Gruppe"))
                          {
                            int iStammtyp=SQL.getInteger(g,"select aic_stammtyp from stamm where aic_stamm=?",0,""+iStamm);
                            int iPos = g.TabStammtypen.getPos("aic", iStammtyp);
                            int iEigANR = iPos < 0 ? 0 : g.TabStammtypen.getI(iPos, "ANR_Eig");
                            g.fixtestInfo("Import Gruppe: Stt="+iStammtyp+", Pos="+iPos+", iEigANR="+iEigANR);
                            if (iEigANR == iEig)
                              g.exec("update stamm_protokoll set anr=" + iAicMom + " where aic_stamm=" + iStamm);
                          }
			}
			//else if ((sDatentyp.equals("Einheiten") || sDatentyp.equals("BenMass") || sDatentyp.equals("Mass"))
			//	&& ((MassEingabe)TabEigenschaft.getInhalt("Komponente")).getMass()!=0)
			//		Insert.add("STA_Aic_Stamm",((MassEingabe)TabEigenschaft.getInhalt("Komponente")).getMass());
			else if (sDatentyp.equals("Boolean"))
				Insert.add("Spalte_Double",Tabellenspeicher.getb(Obj));
                        else if (sDatentyp.equals("Bool3") && Obj instanceof String)
                          Insert.add0("Spalte_Double",g.getAuswahlAic(g.getAuswahlPosB(iEig,""+Obj)));
			//else if (sDatentyp.equals("Integer"))
			//	Insert.add("Spalte_Double",(Integer)Obj);
			else if (sDatentyp.equals("Double") || sDatentyp.equals("Integer") || sDatentyp.equals("Farbe") || sDatentyp.equals("Bool3"))
				Insert.add("Spalte_Double",Sort.getf(Obj));
			else if (sDatentyp.equals("Stringx"))
				Insert.add("Spalte_Double",Import.getString(Obj).length());
			else if (sDatentyp.equals("Mass") || sDatentyp.equals("Einheiten"))
			{
                          if (Obj instanceof Mass)
                          {
                            Insert.add("Spalte_Double", ((Mass)Obj).getAbsValue());
                            if(((Mass)Obj).getAIC() > 0)
                              Insert.add("STA_Aic_Stamm", ((Mass)Obj).getAIC());
                          }
                          else
                          {
                            double dFaktor = SQL.getDouble(g,"select spalte_double from stammpool where aic_stamm="+g.TabEigenschaften.getI(iPosE,"aic_stamm")+" and pro2_aic_protokoll is null and aic_eigenschaft="+g.iEigFaktor);
                            if (dFaktor !=0.0)
                            {
                              Insert.add("Spalte_Double", Sort.getf(Obj)/dFaktor);
                              Insert.add("STA_Aic_Stamm", g.TabEigenschaften.getI(iPosE,"aic_stamm"));
                            }
                            else
                              Static.printError("Import.Save: Faktor für Mass fehlt");
                          }
			}
			else if (sDatentyp.equals("Waehrung"))
			{
				Insert.add("Spalte_Double",Sort.getf(Obj));
				Insert.add("STA_Aic_Stamm",g.getWaehrung());
			}
			//else if (sDatentyp.equals("Einheiten") || sDatentyp.equals("BenMass") || sDatentyp.equals("Mass"))
			//	Insert.add("Spalte_Double",((MassEingabe)TabEigenschaft.getInhalt("Komponente")).getValue());
                        if (dtBis !=null)
                        {
                          Insert.add("GUELTIG_BIS",dtBis);
                          Insert.add("Pro_Aic_Protokoll",iProt);
                        }
            if (bDelStammPool)
              if (bStamm)
                g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and "+sRef+" and aic_eigenschaft="+iEig);
        	  else
        		g.exec("delete from stammpool where "+sRef+" and aic_eigenschaft="+iEig);
			Insert.insert("StammPool",false);

			// Daten_xxx
			/*if (bDatenNotwendig)
			{
				//Daten
				//Insert.clear();
				Insert.add("Aic_Daten",iNr);
				if (sDatentyp.startsWith("String"))
				{
					Insert.add("Spalte_"+sDatentyp,""+Obj);
					Insert.insert("Daten_"+sDatentyp,false);
				}
				else if (sDatentyp.equals("WWW") || sDatentyp.equals("Memo") || sDatentyp.equals("Pfad"))
				{
					Insert.add("Spalte_StringLang",""+Obj);
					Insert.insert("Daten_StringLang",false);
				}
				else if (sDatentyp.equals("E-Mail"))
				{
					Insert.add("Spalte_String60",""+Obj);
					Insert.insert("Daten_String60",false);
				}
				else if (sDatentyp.equals("Text"))
				{
					Insert.insert("Daten_Text",false);
					g.writetext("Daten_Text","Spalte_Text","AIC_Daten="+iNr,""+Obj);
				}
				else if (sDatentyp.equals("Bild"))
				{
					Insert.add("Filename",""+Obj);
					Insert.insert("Daten_Image",false);
				}
				else if (sDatentyp.equals("Zeit"))
				{
					Insert.add("Zeit_von",""+Obj);
					Insert.insert("Zeit_von_bis",false);
				}
				else if (sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis"))
				{
					Insert.add("Zeit_von",((VonBis)Obj).getVon());
					Insert.add("Zeit_bis",((VonBis)Obj).getBis());
					Insert.insert("Zeit_von_bis",false);
				}
			}*/
			Insert.free();
		}
	}

        public static int Undelete(Global g,int iMom,/*int iAlt,*/int iProt,boolean bDel)
                {
        		  if (iMom==0)
        			  return 0;
                  if (bDel)
                    g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_bew_pool="+iMom);

                  //Vector<String> Vec=(iBits&cstBewVoll)>0 ? null:new Vector(TabSpalten.getVecSpalte("Kennung2"));
                  //if (Vec != null)
                  //  Vec.addElement(""+iEig);
                  //g.progInfo("Vec-Eig="+Vec);
                  //boolean bGueltig= Vec==null || TabSpalten.posInhalt("Datentyp","BewDatum");
                  //BEW_POOL
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_bewegungstyp,gueltig,aic_mandant,ldate,ldate2,ldate3,bew2_aic_bew_pool,zone,ldateVon,ldateBis,bool1,bool2 from bew_pool where aic_bew_pool="+iMom,true);
                  if (Tab.isEmpty())
                	  return 0;
                  SQL Insert=new SQL(g);
                  Insert.add("aic_protokoll",iProt);
                  int iBew2=Tab.getI("aic_bewegungstyp");
                  Insert.add("aic_bewegungstyp",iBew2);
                  //if (bGueltig && !Tab.isNull("gueltig"))
                    Insert.add("Gueltig",Tab.getTimestamp("gueltig"));
                  Insert.add("aic_mandant",Tab.getI("aic_mandant"));
                  if (g.hasZone(iBew2))
                	  Insert.add("zone",Tab.getI("zone"));
                  if (bDel)
                    Insert.add("bew_aic_bew_pool",iMom);
                  //if (!Tab.isNull("anr"))
                  //  Insert.add("anr",Tab.getI("anr"));
                  if (!Tab.isNull("ldate"))
                    Insert.add("ldate",Tab.getI("ldate"));
                  if (!Tab.isNull("ldate2"))
                    Insert.add("ldate2",Tab.getI("ldate2"));
                  if (!Tab.isNull("ldate3"))
                    Insert.add("ldate3",Tab.getI("ldate3"));
                  if (!Tab.isNull("bool1"))
                      Insert.add("bool1",Tab.getI("bool1"));
                  if (!Tab.isNull("bool2"))
                      Insert.add("bool2",Tab.getI("bool2"));
                  if (!Tab.isNull("ldateVon"))
                      Insert.add("ldateVon",Tab.getI("ldateVon"));
                  if (!Tab.isNull("ldateBis"))
                      Insert.add("ldateBis",Tab.getI("ldateBis"));
                  if (!Tab.isNull("bew2_aic_bew_pool"))
                    Insert.add("bew2_aic_bew_pool",Tab.getI("bew2_aic_bew_pool"));
                  int iNeu=Insert.insert("bew_pool",true);
                  //BEW_STAMM
                  Tab=new Tabellenspeicher(g,"select aic_eigenschaft,aic_stamm from BEW_STAMM where aic_bew_pool="+iMom,true);
                  //Tab.showGrid("BEW_STAMM");
                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    //if (Vec==null || Vec.contains(Tab.getS("aic_eigenschaft")))
                    {
                      Import.insertBewStamm(g,iBew2,iNeu,Tab.getI("aic_eigenschaft"),Tab.getI("aic_stamm"));
                      /*Insert.add("aic_bew_pool",iNeu);
                      Insert.add("aic_eigenschaft",Tab.getI("aic_eigenschaft"));
                      Insert.add("aic_stamm",Tab.getI("aic_stamm"));
                      Insert.insert("BEW_STAMM",false);*/
                    }
                    //else
                    //  g.progInfo("nicht "+Tab.getS("aic_eigenschaft"));
                  }
                  //BEW_VON_BIS
                  Tab=new Tabellenspeicher(g,"select aic_eigenschaft,von,bis,dauer from BEW_VON_BIS where aic_bew_pool="+iMom,true);
                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    //if (Vec==null || Vec.contains(Tab.getS("aic_eigenschaft")))
                	{
                      Insert.add("aic_bew_pool",iNeu);
                      Insert.add("aic_eigenschaft",Tab.getI("aic_eigenschaft"));
                      Insert.add("von",Tab.getTimestamp("von"));
                      Insert.add("bis",Tab.getTimestamp("bis"));
                      Insert.add("dauer",Tab.getF("dauer"));
                      Insert.insert("BEW_VON_BIS",false);
                    }
                  }
                  //BEW_BOOLEAN
                  Tab=new Tabellenspeicher(g,"select aic_eigenschaft,spalte_boolean from BEW_BOOLEAN where aic_bew_pool="+iMom,true);
                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    //if (Vec==null || Vec.contains(Tab.getS("aic_eigenschaft")))
                    {
                      Insert.add("aic_bew_pool",iNeu);
                      Insert.add("aic_eigenschaft",Tab.getI("aic_eigenschaft"));
                      Insert.add("spalte_boolean",Tab.getI("spalte_boolean"));
                      Insert.insert("BEW_BOOLEAN",false);
                    }
                  }
                  //BEW_WERT
                  Tab=new Tabellenspeicher(g,"select aic_eigenschaft,aic_stamm,spalte_wert,grundwert from BEW_WERT where aic_bew_pool="+iMom,true);
                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    //if (Vec==null || Vec.contains(Tab.getS("aic_eigenschaft")))
                    {
                      Insert.add("aic_bew_pool",iNeu);
                      Insert.add("aic_eigenschaft",Tab.getI("aic_eigenschaft"));
                      Insert.add0("aic_stamm",Tab.getI("aic_stamm"));
                      Insert.add("spalte_wert",Tab.getF("spalte_wert"));
                      Insert.add("grundwert",Tab.getF("grundwert"));
                      Insert.insert("BEW_WERT",false);
                    }
                  }
                  //BEW_SPALTE
                  Tab=new Tabellenspeicher(g,"select aic_eigenschaft,aic_stamm,wert,grundwert,sta_aic_stamm from BEW_SPALTE where aic_bew_pool="+iMom,true);
                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    //if (Vec==null || Vec.contains(Tab.getS("aic_eigenschaft")))
                    {
                      Insert.add("aic_bew_pool",iNeu);
                      Insert.add("aic_eigenschaft",Tab.getI("aic_eigenschaft"));
                      Insert.add0("aic_stamm",Tab.getI("aic_stamm"));
                      Insert.add("wert",Tab.getF("wert"));
                      Insert.add("grundwert",Tab.getF("grundwert"));
                      Insert.add0("sta_aic_stamm",Tab.getI("sta_aic_stamm"));
                      //TODO Grundwert übernehmen
                      Insert.insert("BEW_SPALTE",false);
                    }
                  }
                  //BEW_BEGRIFF
                  Tab=new Tabellenspeicher(g,"select aic_eigenschaft,aic_begriff from BEW_BEGRIFF where aic_bew_pool="+iMom,true);
                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    //if (Vec==null || Vec.contains(Tab.getS("aic_eigenschaft")))
                    {
                      Insert.add("aic_bew_pool",iNeu);
                      Insert.add("aic_eigenschaft",Tab.getI("aic_eigenschaft"));
                      Insert.add("aic_begriff",Tab.getI("aic_begriff"));
                      Insert.insert("BEW_BEGRIFF",false);
                    }
                  }
                  //STAMMPOOL
                  Tab=new Tabellenspeicher(g,"select aic_eigenschaft,aic_daten,sta_aic_stamm,spalte_double from STAMMPOOL where pro2_aic_protokoll is null and aic_bew_pool="+iMom,true);
                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    //if (Vec==null || Vec.contains(Tab.getS("aic_eigenschaft")))
                    {
                      Insert.add("aic_bew_pool",iNeu);
                      Insert.add("aic_eigenschaft",Tab.getI("aic_eigenschaft"));
                      Insert.add("aic_protokoll",iProt);
                      Insert.add0("aic_daten",Tab.getI("aic_daten"));
                      Insert.add0("sta_aic_stamm",Tab.getI("sta_aic_stamm"));
                      if(!Tab.isNull("spalte_double"))
                        Insert.add("spalte_double",Tab.getF("spalte_double"));
                      Insert.insert("STAMMPOOL",false);
                    }
                  }
                  Insert.free();
                  return iNeu;
        }
        
     // Restspeicherung
    	public static Tabellenspeicher getTabRest(Global g,boolean b,int iBew)
    	{
    		if (b)
    			return new Tabellenspeicher(g,"select aic_eigenschaft aic,null saved,null Dt,null Bez from bew_zuordnung where aic_bewegungstyp="+iBew+" order by reihenfolge",true);
    		else 
    			return null;
    	}
    	
    	public static void saveRest(Global g,SQL Insert,Tabellenspeicher TabRest,int iAIC_BewOld,int iAIC_Bew,int iAIC_Bewegungstyp,int iProt)
    	{
    		if (TabRest != null)
			{
    			g.fixtestInfo("***   Rest-Speicherung von "+iAIC_Bew+":   ***");
    			long lClock2=Static.get_Mikro_s();
				//SQL Insert=new SQL(g);
				//if (g.Prog())
				//	TabRest.showGrid("Rest");
				for(TabRest.moveFirst();!TabRest.eof();TabRest.moveNext())
                {
                  int iPosE=TabRest.getB("saved") ? -1:g.TabEigenschaften.getPos("Aic",TabRest.getI("Aic"));
					if(iPosE>=0)
					{
						String sTyp=g.TabEigenschaften.getS(iPosE,"Datentyp");
                                                            //g.fixtestInfo("Rest: Datentyp="+sTyp);
						//g.progInfo("Check Rest:"+sTyp+"/"+g.TabEigenschaften.getS("Bezeichnung")+" ("+TabRest.getI("Aic")+")");
						if (sTyp.equals("BewStamm") || sTyp.equals("BewHierarchie"))
						{
							int iBewStamm=SQL.getInteger(g,"select aic_stamm from bew_stamm where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
							//g.progInfo("iBewStamm="+iBewStamm);
							if (iBewStamm>0)
                              Import.insertBewStamm(g,iAIC_Bewegungstyp,iAIC_Bew,TabRest.getI("Aic"),iBewStamm);
						}
						else if (sTyp.equals("BewBoolean"))
						{
							int iBewBoolean=SQL.getInteger(g,"select spalte_boolean from bew_boolean where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
							if (iBewBoolean>0)
                              Import.insertBewBool(g,iAIC_Bewegungstyp,iAIC_Bew,TabRest.getI("Aic"));
						}
                        else if (sTyp.equals("BewBool3"))
                        {
                          int iAic=SQL.getInteger(g,"select Aic from bew_Aic where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
                          if (iAic>0)
                            Import.insertBewBool3(g,iAIC_Bewegungstyp,iAIC_Bew,TabRest.getI("Aic"),-iAic);                     
                        }
						else if (sTyp.equals("BewModell") || sTyp.equals("BewDruck") || sTyp.equals("BewFormular") || sTyp.equals("BewModul"))
						{
							int iBegriff=SQL.getInteger(g,"select spalte_boolean from bew_boolean where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
							if (iBegriff>0)
							{
								Insert.add("aic_Bew_Pool",iAIC_Bew);
								Insert.add("aic_Eigenschaft",TabRest.getI("Aic"));
								Insert.add("aic_begriff",iBegriff);
								Insert.insert("bew_begriff",false);
							}
						}
						else if (sTyp.equals("BewLand") || sTyp.equals("BewHoliday") || sTyp.equals("BewBG") || sTyp.equals("BewUser"))
						{
							int iAic=SQL.getInteger(g,"select aic from bew_aic where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
							if (iAic>0)
							{
								Insert.add("aic_Bew_Pool",iAIC_Bew);
								Insert.add("aic_Eigenschaft",TabRest.getI("Aic"));
								Insert.add("aic",iAic);
								Insert.insert("bew_aic",false);
							}
						}
						else if (sTyp.equals("BewZahl") || sTyp.equals("BewMass") || sTyp.equals("BewWaehrung"))
						{
							//double dBewZahl=SQL.getDouble(g,"select spalte_wert from Bew_Wert where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
							Tabellenspeicher Tab=new Tabellenspeicher(g,"select spalte_wert,aic_stamm,grundwert from Bew_Wert where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic"),true);							
							if (Tab.moveFirst() && Tab.size()>0 && Tab.getF("spalte_wert")!=0)
							{
								Insert.add("aic_Bew_Pool",iAIC_Bew);
								Insert.add("aic_Eigenschaft",TabRest.getI("Aic"));
								Insert.add0("aic_stamm",Tab.getI("aic_stamm"));//SQL.getInteger(g,"select aic_stamm from Bew_Wert where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic")));
								Insert.add("spalte_wert",Tab.getF("spalte_wert"));
								Insert.add("Grundwert",Tab.getF("Grundwert"));
								Insert.insert("Bew_Wert",false);
							}
						}
						else if (sTyp.equals("BewZahl2") || sTyp.equals("BewMass2") || sTyp.equals("BewWaehrung2"))
						{
							Vector Vec=SQL.getVector("select aic_stamm from bew_spalte where aic_bew_pool="+iAIC_Bew+" and aic_eigenschaft="+TabRest.getI("Aic"), g);
							Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_stamm,wert,grundwert,sta_aic_stamm from bew_spalte where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic")+
									(Vec.size()>0 ? " and aic_stamm not"+Static.SQL_in(Vec):""),true);
							for (Tab.moveFirst();!Tab.out();Tab.moveNext())
							{
								Insert.add("aic_Bew_Pool",iAIC_Bew);
								Insert.add("aic_Eigenschaft",TabRest.getI("Aic"));
								Insert.add0("aic_stamm",Tab.getI("aic_stamm"));
								Insert.add("spalte_wert",Tab.getF("wert"));
								Insert.add("grundwert",Tab.getF("grundwert"));
								Insert.add0("sta_aic_stamm",Tab.getI("sta_aic_stamm"));
								//TODO Grundwert speichern
								Insert.insert("bew_spalte",false);
							}
						}
                        else if (Abfrage.VRel.contains(sTyp) /*|| sTyp.equals("BewBew") || sTyp.equals("BewDatum")*/ || sTyp.equals("BewBits"))
                        {
                          Static.printError("Import.saveRest: Datentyp "+sTyp+" für "+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+" wird noch nicht unterstützt");// !!!
                        }
						else if (sTyp.equals("BewDatum2") || sTyp.equals("BewDauer") || sTyp.equals("BewVon_Bis"))
						{
							Insert.open("select von,bis,Dauer from Bew_von_bis where aic_bew_pool="+iAIC_BewOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
							if (!Insert.eof())
							{
                                  if (sTyp.equals("BewDatum2"))
                                    insertBewDatum2(g,iAIC_Bewegungstyp,iAIC_Bew,TabRest.getI("Aic"),Insert.getTS("von"));
                                  else
                                	insertBewVonBis(g,iAIC_Bewegungstyp,iAIC_Bew,TabRest.getI("Aic"),Insert.getTS("von"),Insert.getTS("bis"),Insert.getF("Dauer"));
                                      
//                                  {
//                                    Timestamp dtBewVon = Insert.getTS("von");
//                                    Timestamp dtBewBis = Insert.getTS("bis");
//                                    double dBewDauer = Insert.getF("Dauer");
//                                    Insert.add("aic_Bew_Pool", iAIC_Bew);
//                                    Insert.add("aic_Eigenschaft", TabRest.getI("Aic"));
//                                    Insert.add("von", dtBewVon);
//                                    Insert.add("bis", dtBewBis);
//                                    Insert.add("Dauer", dBewDauer);
//                                    Insert.insert("Bew_von_bis", false);
//                                  }
							}
						}
						/*else if (!sTyp.equals("BewDatum") && !sTyp.endsWith("Benutzer") && !sTyp.equals("Anlage") && !sTyp.equals("Filler") && !sTyp.equals("Timestamp")
							 && !sTyp.equals("Aic") && !sTyp.equals("SysAic") && !sTyp.equals("entfernt"))
							g.progInfo("Kopie von "+sTyp+" wird noch nicht unterstützt!");*/
					}
                }

				Tabellenspeicher TabRest2=new Tabellenspeicher(g,"select * from stammpool where pro2_aic_protokoll is null and aic_bew_pool="+iAIC_BewOld,true);
				//TabRest2.showGrid("Rest2");
				for(TabRest2.moveFirst();!TabRest2.eof();TabRest2.moveNext())
					if(TabRest.posInhalt("Aic",TabRest2.getI("Aic_eigenschaft")) && !TabRest.getB("saved"))
					{
						Insert.add("aic_Bew_Pool",iAIC_Bew);
						Insert.add("aic_Eigenschaft",TabRest.getI("Aic"));
						Insert.add0("aic_Daten",TabRest2.getI("Aic_Daten"));
						Insert.add0("sta_aic_Stamm",TabRest2.getI("sta_aic_Stamm"));
						if (TabRest2.getInhalt("Spalte_Double") != null)
							Insert.add("Spalte_Double",TabRest2.getF("Spalte_Double"));
						Insert.add("Aic_Protokoll",iProt);
						Insert.insert("Stammpool",false);
					}
				g.fixtestInfo("Restspeicherung "+iAIC_Bew+":"+Static.Mikro(lClock2));
			}
    	}
    	
    		 
    		public static BufferedImage resizeImage(BufferedImage bufferedImageInput,int iW)
    		{
    			if (bufferedImageInput==null)
    				return null;
    			int resizeWidth=0;
    			int resizeHeight=0;
    			if (bufferedImageInput.getWidth()>iW)
	    		{
					resizeWidth=iW;
					resizeHeight=(int) (bufferedImageInput.getHeight()*iW/bufferedImageInput.getWidth());
	    		}
    			else
    				return null;
				BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,resizeHeight, bufferedImageInput.getType());
				
				Graphics2D g2d = bufferedImageOutput.createGraphics();
				g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
				g2d.dispose();
				return bufferedImageOutput;
    		}
    		
    		public static AUVector VBild=new AUVector(new String[] {"jpg","jpeg","png","gif","bmp","tif","tiff"});
    		
    		public static BufferedImage ImageToMini(Global g,int iDaten, Image Img,String sFile,boolean bMini)
    		{
//    			g.fixtestError("ImageToMini:"+sFile);
    			int iP=sFile.lastIndexOf('.');
    			String sExt=iP<0 ? "xxx":sFile.substring(iP+1).toLowerCase();
    			if (!VBild.contains(sExt))
    			{
    				g.exec("update daten_bild2 set filename='"+sFile+".jpg' where aic_daten="+iDaten);
    				g.fixtestError("ImageToMini: Umbenannt auf "+sFile+".jpg");
    				sExt="jpg";
    			}
    			if (sExt.equals("xxx") || sExt.equals("gif"))
    			{
    				if (bMini)
    					g.exec("update daten_bild2 set mini=bild2 where aic_daten="+iDaten);
    				return null;
    			}
    			else
    			{
	    			BufferedImage img1=Static.imageToBufferedImage(sFile,Img);//((ToolkitImage) Img).getBufferedImage();
	    			if (bMini && img1==null)
	    			{
	    				g.exec("delete from daten_bild2 where aic_daten="+iDaten);
	    				g.printError("Bild "+sFile+" von Daten_Bild2 entfernt, da kein Bild");
	    				return null;
	    			}
	        		BufferedImage img = resizeImage(img1,bMini ? 150:1200);
	        		if(img==null)
	        		{
	        			if (bMini)
	        				g.exec("update daten_bild2 set mini=bild2 where aic_daten="+iDaten);
	    				return null;
	        		}
	        		if (!g.SaveImage(img,sFile,"update daten_bild2 set "+(bMini ? "mini":"bild2")+"=? where aic_daten="+iDaten,sExt))
	        			img=null;
//	        		try
//	        		{
//	        			String sFile2=Static.getTemp()+iDaten+(bMini ? "_thumb.jpg":"_thumb2.jpg");
//	        			File file=new File(sFile2);
//	        			ImageIO.write(img, sExt, file);
//	        			g.UpdateMini(sFile2,"update daten_bild2 set "+(bMini ? "mini":"bild2")+"=? where aic_daten="+iDaten);
//	        			file.delete();
//	        		}
//	        		catch (Exception e) {
//	        			img=null;
//	        			g.printStackTrace(e);
//	        		}
	        		return img;
    			} 		
    		}
    		
    		public static BufferedImage ImageToMini2(Global g,int iDaten, Image Img,String sFile)
    		{
//    			g.fixInfo("ImageToMini2: "+sFile);
    			int iP=sFile.lastIndexOf('.');
    			String sExt=iP<0 ? "xxx":sFile.substring(iP+1).toLowerCase();
    			if (!VBild.contains(sExt))
    			{
    				sExt="xxx";
    				g.fixtestError("ImageToMini2: kein Bild: "+sFile);
    			}
    			if (sExt.equals("xxx") || sExt.equals("gif"))
    			{
    				return null;
    			}
    			else
    			{
    			  try
    			  {
	    			BufferedImage img1=Static.imageToBufferedImage(sFile,Img);//((ToolkitImage) Img).getBufferedImage();
	        		BufferedImage img = resizeImage(img1,1000);
	        		if(img==null)
	        		{
	    				return null;
	        		}
	        		if (!g.SaveImage(img,sFile,"update daten_doku set doku=? where aic_daten="+iDaten,sExt))
	        			img=null;
	        		return img;
    			  }
    			  catch(Exception e)
    			  {
    				  g.printError("Fehler bei ImageToMini2 mit "+sFile);
    				  g.printStackTrace(e);
    				  return null;
    			  }
    			} 		
    		}
    		
    		private static boolean saveImage(Global g,String sTab,String sFile,int iNr)
    		{
    			int iP=sFile.lastIndexOf('.');
    			String sExt=iP<0 ? "xxx":sFile.substring(iP+1).toLowerCase();
    			boolean bOk=false;			
    			if (!VBild.contains(sExt))
    			{
    				if (sTab.equals("Bild2"))
    					new Message(Message.WARNING_MESSAGE, null, g).showDialog("Bild_Ext_falsch", new String[] {sExt});
    				else
    				{
    					bOk=g.insertFile(iNr, sFile, "insert into Daten_" + sTab + " (AIC_Daten,filename," + sTab + ") values (?,?,?)");
    					if (!bOk)
    						new Message(Message.WARNING_MESSAGE, null, g).showDialog("Datei_zu_gross", new String[] {sFile});
    				}
    				return bOk;
    			}
    			else
    			{
    				if (sFile.indexOf(":")<3)
    						sFile="file:"+(sFile.startsWith("/") ?"":"/")+sFile;
    				g.fixtestError("saveImage: "+sFile);
    				Image Img=g.LoadImage(sFile);
    				if (Img==null)
    				{
//    					Static.printError("saveImage: "+sFile+" ist kein Bild");
    					new Message(Message.WARNING_MESSAGE, null, g).showDialog("kein_Bild", new String[] {sFile});
    					return false;
    				}
    				BufferedImage img1=Static.imageToBufferedImage(sFile,Img);
//    				BufferedImage imgMini = scale(img1,150);
    				BufferedImage imgN = resizeImage(img1,sTab.equals("Bild2") ? 1200:1000);
    				if (imgN != null)
    				{
    					String sFileN=Static.getTemp()+iNr+"_thumb2.jpg";
	        			File file=new File(sFileN);
	        			try
		        		{
		        			ImageIO.write(imgN, sExt, file);
		        			bOk=g.insertFile(iNr, sFile, sFileN, null, "insert into Daten_" + sTab + " (AIC_Daten,filename," + sTab + ") values (?,?,?)");
		        		}
		        		catch (Exception e) {}
//	        			g.UpdateMini(sFile2,"update daten_doku set doku=? where aic_daten="+iDaten);
	        			file.delete();
    				}
    				else
    					bOk=g.insertFile(iNr, sFile, "insert into Daten_" + sTab + " (AIC_Daten,filename," + sTab + ") values (?,?,?)");
    				if (bOk && sTab.equals("Bild2"))
    					ImageToMini(g,iNr,Img,sFile,true);
    				if (!bOk)
						new Message(Message.WARNING_MESSAGE, null, g).showDialog("Bild_nicht_speicherbar", new String[] {sFile});
    				return bOk;
    			}
    		}

}

