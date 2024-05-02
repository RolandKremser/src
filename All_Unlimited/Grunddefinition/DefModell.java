/*
    All_Unlimited-Grunddefinition-Modell.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Optional;
import java.util.Stack;
import java.util.Vector;

//import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JFileChooser;
//import javax.swing.JScrollPane;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerComponent;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Allgemein.AUVector;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.SyncStamm;
import All_Unlimited.Allgemein.Tabellenspeicher;
//import All_Unlimited.Allgemein.GroupBox;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Anzeige.JaNein;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.EigenschaftsEingabe;
import All_Unlimited.Allgemein.Eingabe.StammEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.Aufruf;
import All_Unlimited.Hauptmaske.Calc;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Zeitraum;

import javax.swing.JSplitPane;

import All_Unlimited.Allgemein.Eingabe.MemoF;
import All_Unlimited.Hauptmaske.TCalc;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.SpinBox;

import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;

import java.awt.FlowLayout;

import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;

public class DefModell extends Formular
{
  private static AUVector VecZR=new AUVector(new String[] {"init vonbis","delete vonbis","date to von","date to bis","next vb","prev vb","set quarter","next bis","set vonbis"});
  private static AUVector VecQry=new AUVector(new String[] {"init Aic","init real aic","copy to QryAic","change Aic","swap QryAic","get QryAic","get real QryAic","get user-stamm"});
  private static AUVector VecPush=new AUVector(new String[] {"push","pop"});

  public static DefModell get(Global glob,int riBegriff)
  {
    if (Self==null)
      new DefModell(glob,riBegriff);
    else
    {
      int iMod=glob.BegriffToModell(riBegriff);
      if (iMod != Self.iModellF)
      {
	Self.iModellF=iMod;
      //if (Self.iModellF>0)
	Self.fillOutModell();
	if (!Self.bAlleSpalte)
	  Self.fillSpalten();
      }
    }
    return Self;
  }
  
  private void showGloAbf()
  {
	  g.TabGAbf.showGrid("TabGAbf",thisJFrame());
	  g.TabGAbf.Grid.addActionListener(new JCActionListener()
	  {
			public void actionPerformed(JCActionEvent ev)
			{
				JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
				Tabellenspeicher Tab=(Tabellenspeicher)((Vector)Nod.getLabel()).elementAt(4);
				if (Tab != null)
					Tab.showGrid("Globale Abfrage "+g.getDefBez(Sort.geti(Nod.getLabel(), 0)),(JDialog)g.TabGAbf.FrmGrid);
			}
	  });
  }

  private DefModell(Global glob,int riBegriff)
	{
		super("Modell",null,glob);
                g=glob;
                g.fixtestInfo("DefModell.create:"+riBegriff);
		iModellF=g.BegriffToModell(riBegriff);
		Self=this;
		//g.checkModelle();
		//iModellF=g.BegriffToModell(riBegriff);
                iTabBefehl=g.TabTabellenname.getAic("BEFEHL");
		TabStamm=new Tabellenspeicher(g,new String[] {"Aic","Bezeichnung"});
		DatVon = new Datum(g,"yyyy-MM-dd HH:mm:ss");
  		DatBis = new Datum(g,"yyyy-MM-dd HH:mm:ss");

		//Para = new Parameter(g,"Modell");

		TabModell = new Tabellenspeicher (g,new String[] {"AIC","Bezeichnung","Kennung","Periode","Node","Kennzeichen","Aic_Begriff"});
		TabSpalten = new Tabellenspeicher(g,new String[] {"AIC","Bezeichnung","Abfrage","Stt","Node","Datentyp","kZR"});

//		OutBuild.setCopy(true);
		OutBuild.setBackground(Color.white);
        OutBuild.setNodeIndent(10);
		OutModell.setBackground(Color.white);
		OutAnweisung.setBackground(Color.white);
		OutBedingung.setBackground(Color.white);
		OutSpalten.setBackground(Color.white);
                OutModell.setRootNode(new JCOutlinerFolderNode(""));
                OutAnweisung.setRootNode(new JCOutlinerFolderNode(""));
                OutBedingung.setRootNode(new JCOutlinerFolderNode(""));
                OutBuild.setRootNode(new JCOutlinerFolderNode(""));
                OutSpalten.setRootNode(new JCOutlinerFolderNode(""));
		OutBuild.setRootVisible(false);
		OutModell.setRootVisible(false);
		OutAnweisung.setRootVisible(false);
		OutBedingung.setRootVisible(false);
		OutSpalten.setRootVisible(false);

		OutModell.setColumnLabelSortMethod(Sort.sortMethod);
		OutAnweisung.setColumnLabelSortMethod(Sort.sortMethod);
		OutBedingung.setColumnLabelSortMethod(Sort.sortMethod);
		OutSpalten.setColumnLabelSortMethod(Sort.sortMethod);

		OutBuild.setColumnLabelSort(false);
		OutBuild.setAllowMultipleSelections(true);
		String [] s = new String[]{g.getShow("DefBezeichnung"),g.getShow("Kennung"),g.getShow("Periode"),g.getShow("Aic"),g.getShow("Change"),
                    g.getBezeichnung("Tabellenname","STAMMTYP"),g.getBegriffS("Checkbox","Jeder"),g.getShow("Programm"),g.getBegriffS("Checkbox","Knopf"),g.getShow("Modul"),
                    g.getShow("Bezeichnung"),g.getShow("KennzeichenM"),g.getShow("Aic_Begriff"),g.getShow("Web"),g.getShow("Ampel"),g.getShow("Abfrage"),g.getShow("lokG")};
		OutModell.setColumnButtons(s);
		OutModell.setNumColumns(s.length);
                OutModell.setColumnAlignment(3,BWTEnum.MIDDLERIGHT);
                OutModell.setFont(Global.fontModell);
		s = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Aic"),g.getShow("Anzahl")};
		OutAnweisung.setColumnButtons(s);
		OutAnweisung.setNumColumns(s.length);
                OutAnweisung.setColumnAlignment(2,BWTEnum.MIDDLERIGHT);
                OutAnweisung.setFont(Global.fontModell);
		OutBedingung.setColumnButtons(s);
		OutBedingung.setNumColumns(s.length);
                OutBedingung.setColumnAlignment(2,BWTEnum.MIDDLERIGHT);
                OutBedingung.setFont(Global.fontModell);
                
		s = new String[] {g.getShow("Bezeichnung"),g.getShow("Aic"),g.getShow("Datentyp"),g.getShow("Anzahl"),g.getBezeichnung("Tabellenname","STAMMTYP"),g.getBegriffS("Checkbox","Gruppiert"),g.getShow("Sortiert"),
                    g.getBegriffS("Checkbox","kein_Stammsatz"),g.getBegriffS("Checkbox","kein_Bew-Zeitraum"),g.getBegriffS("Checkbox","kein_Refresh"),g.getBegriffS("Checkbox","Distinct")};
		OutSpalten.setColumnButtons(s);
		OutSpalten.setNumColumns(s.length);
        OutSpalten.setColumnAlignment(1,BWTEnum.MIDDLERIGHT);
        OutSpalten.setColumnAlignment(3,BWTEnum.MIDDLERIGHT);
        OutSpalten.setColumnAlignment(5,BWTEnum.MIDDLECENTER);
        OutSpalten.setColumnAlignment(6,BWTEnum.MIDDLECENTER);
        OutSpalten.setColumnAlignment(7,BWTEnum.MIDDLECENTER);
        OutSpalten.setColumnAlignment(8,BWTEnum.MIDDLECENTER);
        OutSpalten.setColumnAlignment(9,BWTEnum.MIDDLECENTER);
        OutSpalten.setColumnAlignment(10,BWTEnum.MIDDLECENTER);
        OutSpalten.setFont(Global.fontModell);
        
		sBuild = new String[] {g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Eingabe"),g.getBegriffS("Show","Refresh"),g.getBegriffS("Show","Bits"),g.getBegriffS("Show","Stt-Bew"),g.getBegriffS("Show","Abfrage"),g.getBegriffS("Show","Spalte"),g.getBegriffS("Show","Nr"),g.getBegriffS("Show","Memo")};
		OutBuild.setColumnButtons(sBuild);
		OutBuild.setNumColumns(sBuild.length);
        OutBuild.setColumnAlignment(7,BWTEnum.MIDDLERIGHT);
        OutBuild.setColumnAlignment(8,BWTEnum.MIDDLERIGHT);
        OutBuild.setFont(Global.fontModell);

		CboPeriode = new ComboSort(g);
		//if(g.TabStammtypen.posInhalt("Kennung","PERIODE"))
			CboPeriode.fillStammTable(g.iSttPeriode,true);
		//else
		//	CboPeriode.setKein(true);
                CboEigenschaft = new EigenschaftsEingabe(g,thisFrame);
                CboEigenschaft.Cbo.fillCbo("select e.aic_eigenschaft,e.kennung,"+g.concat(g.concat(g.AU_Bezeichnungo("Eigenschaft","e"),"' ('"),g.concat("begriff.kennung","')'"))+
                    " bezeichnung from eigenschaft e"+g.join("begriff","e")+" where begriff.kennung='Ampel' or begriff.kennung='BewStamm' and e.aic_stammtyp="+g.iSttLight,"eigenschaft",true,false);

		CboModul = new ComboSort(g);
		CboModul.fillBegriffTable("Modul",true);
//		CboMandant = new ComboSort(g);
//		CboMandant.fillDefTable("Mandant",false);

		CbxPeriode = getCheckboxM("Periode",false);
    CbxEF = getCheckboxM("EF",false);
    CbxZRhold = getCheckboxM("Zeitraum bleibt",false);
    CbxMenge = getCheckboxM("Menge",false);
    CbxKeineFrage = getCheckboxM("keine Frage",false);
    CbxRefresh = getCheckboxM("Refresh",false);
    CbxOhneStamm = getCheckboxM("kein_Stammsatz",false);
    CbxRecalc = getCheckboxM("Recalc",false);
    CbxAServer = getCheckboxM("AServer",false);
    //CbxAntwort = getCheckboxM("Antwort",false);
    CbxKnopf = getCheckboxM("Knopf",false);
    CbxSave = getCheckboxM("Save",false);
    //CbxnachSave = getCheckboxM("nach_Save",false);
    CbxThread = getCheckboxM("Thread",false);
    CbxBew = getCheckboxM("Bew3",false);
    CbxCommit = getCheckboxM("commit",false);
    CbxAbbruch = getCheckboxM("Abbruch",false);
    CbxUseQry = getCheckboxM("use Qry",false);
    CbxMitSo = getCheckboxM("mit So",false);
    CbxJokerStt = getCheckboxM("Joker gekapselt",false);
    CbxDelJS = getCheckboxM("DelJS",false);
    CbxNbAServer = getCheckboxM("nicht bei AServer",false);
    CbxDefImport = getCheckboxM("bei_DefImport",false);
    CbxMDialog = getCheckboxM("ModellDialog",false);
    CbxMDialog.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			checkCboAbfrage();			
		}
	});
    CbxEigenG = getCheckboxM("eigener ZR",false);
    CbxAmpel = getCheckboxM("Ampel",false);
    CbxAmpelWeb = getCheckboxM("AmpelWeb",false);
    CbxWeiter = getCheckboxM("weiterM",false);
    CbxErgebnis = getCheckboxM("Ergebnisknopf",false);
    CbxMassExport = getCheckboxM("MassExport",false);
    CbxDruckZR = getCheckboxM("DruckZR",false);
    CbxClean = getCheckboxM("Reinigung",false);
    CbxSperre = getCheckboxM("Sperre3",false);
    CbxNurTest = getCheckboxM("nur Test",false);
    CbxTod = getCheckboxM("Tod",false);

		CboBewegungstyp = new ComboSort(g);
		CboBewegungstyp.fillDefTable("Bewegungstyp",true);
		CboStammtyp = new ComboSort(g);
		CboStammtyp.fillDefTable("Stammtyp",true);
		CboProg = new ComboSort(g);
		CboProg.fillBegriffTable(g.TabBegriffgruppen.getAic("Programm"),true,true);
                CboAbfrage = new AbfrageEingabe(g,thisFrame,true,false);
                CboAbfrage.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("bits",Abfrage.cstFilter),"Abfrage",true);
                CboAbfrage2 = new AbfrageEingabe(g,thisFrame,true,false);
                CboAbfrage2.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("abits2",Abfrage.cstModFarbe),"Abfrage",true);
                CboAbfWebDialog = new AbfrageEingabe(g,thisFrame,true,false);
                CboAbfWebDialog.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("abits2",Abfrage.cstModellDlg),"Abfrage",true);
                CboAbfWebAmpel = new AbfrageEingabe(g,thisFrame,true,false);
                CboAbfWebAmpel.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("bits",Abfrage.cstFilter),"Abfrage",true);
                SB_MaxB.setMinimum(0);
                BtnBild = new BildEingabe((JFrame)thisFrame,g);
                BtnBild.activateEvent();
                BtnBildFX = new BildEingabe((JFrame)thisFrame,g);
                BtnBildFX.activateEvent();
                CbxCombo=getCheckboxM("combo",false);
                CbxJeder=getCheckboxM("Jeder",false);
                CbxWeb=getCheckboxM("Web",false);
		//CboStammsatz = new ComboSort(g);
		//CboStammsatz.fillStammTable(CboStammtyp.getSelectedAIC(),true);
		SteStamm = new StammEingabe(thisFrame,g);
		/*Para.getParameter("Option",true,false);
		SpnAbbruch.setValue(Para.int1);
		SpnDebug.setValue(Para.int2);
		SpnTabellen.setIntValue(Para.int3);
                iDBits=Para.int3;
                if (iDBits==0)
                  iDBits=0x0FBF;
                CbxDd.setSelected((iDBits&Calc.DD)>0);
                CbxDdt.setSelected((iDBits&Calc.DDT)>0);
                CbxDb.setSelected((iDBits&Calc.DB)>0);
                CbxDs.setSelected((iDBits&Calc.DS)>0);
                CbxDi.setSelected((iDBits&Calc.DI)>0);
                CbxDQry.setSelected((iDBits&Calc.DQRY)>0);
                CbxDBew.setSelected((iDBits&Calc.DBEW)>0);
                CbxDMass.setSelected((iDBits&Calc.DMASS)>0);
                CbxDStt.setSelected((iDBits&Calc.DSTT)>0);
                CbxDPos.setSelected((iDBits&Calc.DPOS)>0);
                CbxDvon.setSelected((iDBits&Calc.DVON)>0);
                CbxDbis.setSelected((iDBits&Calc.DBIS)>0);
                CbxDd2.setSelected((iDBits&Calc.DD2)>0);
                CbxDdt2.setSelected((iDBits&Calc.DDT2)>0);
                CbxDST.setSelected((iDBits&Calc.DST)>0);
                CbxDs2.setSelected((iDBits&Calc.DS2)>0);
                CbxDi2.setSelected((iDBits&Calc.DI2)>0);
                CbxDQry2.setSelected((iDBits&Calc.DQRY2)>0);
                CbxDBew2.setSelected((iDBits&Calc.DBEW2)>0);
                CbxDTrenn.setSelected((iDBits&Calc.DTRENN)>0);
                CbxDJokerDT.setSelected((iDBits&Calc.DJDT)>0);
                CbxDJokerI.setSelected((iDBits&Calc.DJI)>0);
                CbxDJokerS.setSelected((iDBits&Calc.DJS)>0);
                CbxDVec.setSelected((iDBits&Calc.DVEC)>0);

                CbxDZone.setSelected((iDBits&Calc.DZONE)>0);
                CbxDVSa.setSelected((iDBits&Calc.DVSA)>0);
                CbxDVecS.setSelected((iDBits&Calc.DVECS)>0);
                CbxDSave.setSelected((iDBits&Calc.DSAVE)>0);

		SpnPause.setValue(Para.int4);*/

		//SpnAbbruch.setMinimum(-1);
		//SpnDebug.setMinimum(-1);
		//SpnTabellen.setMinimum(-1);
		//SpnPause.setMinimum(-1);

		CbxDebug = getCheckboxM("Debug",false);
		CbxDebug.setSelected(true);

		CbxTimer = getCheckboxM("Timer2",false);
		// CbxNeu = getCheckboxM("Neu",true);

		Build();
                /*thisFrame.addWindowListener(new WindowListener()
                {
                  public void windowClosed(WindowEvent e) {}
                  public void windowOpened(WindowEvent e) {}
                  public void windowClosing(WindowEvent e) {
                  }
                  public void windowIconified(WindowEvent e) { g.progInfo("windowIconified"); }
                  public void windowDeiconified(WindowEvent e) {}
                  public void windowActivated(WindowEvent e) {
                    if (bNA)
                    {
                      bNA=false;
                      g.progInfo("windowActivated");
                      //thisFrame.toFront();
                      FomBuild.toBack();
                      FomSpalten.toBack();
                      bNA=true;
                    }
                  }
                  public void windowDeactivated(WindowEvent e) {}
                });*/

                LoadParameter();
		fillOutliners();

		int iPos=g.getPosBegriff("Show","Ja");
		Image Img = g.LoadImage(g.TabBegriffe.getS(iPos,"BildFile"));
		if (Img != null)
		{
			StyJa = new JCOutlinerNodeStyle();
			StyJa.setFolderClosedIcon(Img);
			StyJa.setFolderOpenIcon(Img);
		}

		Img=null;
		iPos=g.getPosBegriff("Show","Nein");
		Img = g.LoadImage(g.TabBegriffe.getS(iPos,"BildFile"));
		if (Img != null)
		{
			StyNein = new JCOutlinerNodeStyle();
			StyNein.setFolderClosedIcon(Img);
			StyNein.setFolderOpenIcon(Img);
		}

		sNicht = g.getBegriffS("Show","not");

                ActionListener AL1=new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    String s = e.getActionCommand();
                    g.progInfo("DefModell.Action=" + s);
                    if (s.equals("Neu"))
                      BtnNeu();
                    else if (s.equals("NeuOk") || s.equals("NeuAbbruch"))
                    {
                      if (s.equals("NeuAbbruch") || Neu())
                      {
                        if (s.equals("NeuOk"))
                        {
                          if (NodeModell == null)
                            bGeaendert = true;
                          EnableButtons();
                        }
                        hideDlg(FrmNeu);
                        EdtKennung.setText("");
                        EdtKennzeichen.setText("");
                        EdtAlias.setText("");
                        EdtDefBezeichnung.setText("");
                        EdtBildname.setText("");
                        EdtFarbe.setText("");
                        EdtBezeichnung.setText("");
                        CboProg.setSelectedAIC(0);
                        CboStammtyp.setSelectedAIC(0);
                        CboBewegungstyp.setSelectedAIC(0);
                        CboAbfrage.setSelectedAIC(0);
                        CboAbfrage2.setSelectedAIC(0);
                        CboAbfWebDialog.setSelectedAIC(0);
                        CboAbfWebAmpel.setSelectedAIC(0);
                        SB_MaxB.setValue(0);
                        BtnBild.Delete();
                        BtnBildFX.Delete();
                        CbxJeder.setSelected(false);
                        CbxCombo.setSelected(false);
                        CbxWeb.setSelected(false);
                        EdtMemo.setText("");
                        EdtTooltip.setText("");
                        NodKopie = null;
                      }
                    }
                    else if (s.equals("Back"))
                    {
                      // Vector VecInvisible = (Vector)((JCOutlinerFolderNode)OutModell.getSelectedNode()).getUserData();
                      g.exec("update begriff set importzeit=null where aic_begriff="+Sort.geti(NodeModell.getUserData(),4));
                      LblImportzeit.setText("-");
                    }
                    else if (s.equals("Edit"))
                      BtnBearbeiten(false);
                    else if (s.equals("EditModell"))
                      EditModell();
                    else if (s.equals("Hinzu"))
                    {
                      Hinzufuegen();
                      EnableButtons();
                    }
                    else if (s.equals("Ersetzen"))
                    {
                      Ersetzen();
                      EnableButtons();
                    }
                    else if (s.equals("Del"))
                    {
                      Entfernen();
                      EnableButtons();
                    }
                    else if (s.equals("Einstellung"))
                      Einstellung();
                    else if (s.equals("Druck"))
                      All_Unlimited.Print.DruckHS.printOutliner(g,"Modell",true,OutBuild);
                    else if (s.equals("Save"))
                    {
                            if (Speichern(0))
                              bGeaendert=false;
                            EnableButtons();
                    }
                    else if (s.equals("Zeitraum"))
                      Zeitraum.get(g).show();
                    else if (s.equals("Beenden"))
                    {
                      if(g.Def() && bGeaendert && iAnzFL==0)
                           if(new Message(Message.YES_NO_OPTION,null,g).showDialog("Speichern") == Message.YES_OPTION)
                           {
                             if (Speichern(0))
                               bGeaendert=false;
                             EnableButtons();
                           }
                           else
                           {
                            checkSperre();
                            Laden(null, true,null,0);
                           }
                            SaveParameter();
                            hide();
                            FomBuild.setVisible(false);
                            FomSpalten.hide();
                    }
                    else if (s.equals("Rauf") || s.equals("Runter"))
                    {
                          Verschieben(OutBuild.getSelectedNodes(),s.equals("Rauf"));
                          EnableButtons();
                    }
                    else if (s.equals("InfoBreak"))
                    	InfoBreak();
                    else if (s.equals("DelBreak"))
                    {
                          Aufruf.VecBreak=null;
                          Aufruf.VecDebug=null;
                          Aufruf.VecSpalten=null;
                          Aufruf.VecVar=null;
                          Aufruf.VecEingabe=null;
                          Aufruf.VecBefehl=null;
                          Aufruf.VecModelle=null;
                          BtnDelBreak.setEnabled(false);
                          Laden(null,false,null,0);
                    }
                    else if (s.equals("alle Modelle") || s.equals("Modelle in Module") || s.equals("nur Submodelle") || s.equals("Submodellehierarchie"))
                    {
                      fillModelle(s);
                    }
                    else if (s.equals("CbxNot") && bGeaendert)
                      Not(OutBuild.getSelectedNode());
                    else if (s.equals("Abfrage"))
                      DefAbfrageAufrufen(true);
                    else if (s.equals("Refresh2"))
                    {
                      if (OutBuild.getRootNode().getLabel() instanceof Vector)
                      {
                        boolean b = CbxSyncSpalte.isSelected();
                        CbxSyncSpalte.setSelected(false);
                        bRefresh=true;
                        Laden(null, false, null, 0);
                        CbxSyncSpalte.setSelected(b);
                      }
                    }
                    else if (s.equals("SyncStamm"))
                      SyncStamm.start(g,null).show();
                    else if (s.equals("VarG") && Calc.TabV != null)
                    	Calc.TabV.showGrid("TabV");
                    else if (s.equals("AbfG") && g.TabGAbf != null)
                    	showGloAbf();
                    else if (s.equals("Aufruf"))
                      Aufruf();
                    else if (s.equals("AufrufAbbruch"))
                      FrmAufruf.setVisible(false);
                    else if (s.equals("Init"))
                      TabStamm.clearAll();
                    else if (s.equals("Und"))
                    {
                      TabStamm.addInhalt("Aic",SteStamm.getStamm());
                      TabStamm.addInhalt("Bezeichnung",SteStamm.getBezeichnung());
                    }
                    else if (s.equals("Info"))
                      TabStamm.showGrid("Stamm",FrmAufruf);
                    else if (s.equals("InitFrame"))
                      InitFrame();
                    else if (s.equals("CbxZusammen"))
                      Zusammen();
                    else if (s.equals("TSearch"))
                    	Tsearch.get(g,Sort.gets(OutModell.getSelectedNode().getLabel(),1));
                  }
                };
                g.CbxAdd(CbxZusammen,"CbxZusammen",AL1);
                //g.CbxAdd(CbxNurAic,"CbxNurAic",AL1);
                g.BtnAdd(BtnNeu,"Neu",AL1);
                g.BtnAdd(BtnNeuOk,"NeuOk",AL1);
                g.BtnAdd(BtnBack,"Back",AL1);
                g.BtnAdd(BtnNeuAbbruch,"NeuAbbruch",AL1);
                g.BtnAdd(BtnBearbeiten,"Edit",AL1);
		//g.BtnAdd(BtnBearbeitenModell,"EditModell",AL1);
                g.BtnAdd(BtnHinzufuegen,"Hinzu",AL1);
                g.BtnAdd(BtnErsetzen,"Ersetzen",AL1);
                g.BtnAdd(BtnEntfernen,"Del",AL1);
                g.BtnAdd(getButton("Einstellung"),"Einstellung",AL1);
                g.BtnAdd(BtnAlle,"alle Modelle",AL1);
                g.BtnAdd(BtnSub,"nur Submodelle",AL1);
                //g.BtnAdd(BtnDruck,"Druck",AL1);
                g.BtnAdd(BtnSpeichern,"Save",AL1);
                g.BtnAdd(getButton("Zeitraum"),"Zeitraum",AL1);
                g.BtnAdd(getFormularButton("translate-search"),"TSearch",AL1);
                g.BtnAdd(BtnBeenden,"Beenden",AL1);

		TxtMemoBefehl.Edt.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent ev)
			{
			}
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent ev)
			{
                                if (((Vector)AktBuildNode.getUserData()).size()>4)
                                  ((Vector)AktBuildNode.getUserData()).setElementAt(TxtMemoBefehl.getValue(),4);
                                if (((Vector)AktBuildNode.getLabel()).size()>9)
                                  ((Vector)AktBuildNode.getLabel()).setElementAt(TxtMemoBefehl.getValue(),9);
                                setBitChange(AktBuildNode,true);
				//bGeaendert=true;
				//EnableButtons();
			}
			public void keyTyped(KeyEvent ev)
			{
			}
		});

		/*TxtTitelBefehl.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent ev)
			{
			}
			public void keyReleased(KeyEvent ev)
			{
				((Vector)AktBuildNode.getUserData()).setElementAt(TxtTitelBefehl.getText(),3);
				//bGeaendert=true;
				//EnableButtons();
			}
			public void keyTyped(KeyEvent ev)
			{
			}
		});*/

		/*OutModell.addItemListener(new JCItemListener()
		{
			public void itemStateChanged(JCItemEvent ev)
			{
                          //g.testInfo("Node nun:"+ev.getItem());
				if(ev.getStateChange()==ItemEvent.SELECTED)
				{
					selectedNode("Modell");
                                        OutModell.setToolTipText(null);
                                        g.setTooltip(Sort.gets(OutModell.getSelectedNode().getUserData(),8),OutModell);
                                        //OutModell.setToolTipText(Sort.gets(OutModell.getSelectedNode().getUserData(),8));
					EnableButtons();
				}
			}
		});*/

                JCItemListener IL_Out=new JCItemListener()
                {
                        public void itemStateChanged(JCItemEvent ev)
                        {
                                if(ev.getStateChange()==ItemEvent.SELECTED)
                                {
                                  JCOutliner Out=(JCOutliner)ev.getSource();
                                  if (Out!=OutSpalten)
                                    selectedNode(Out==OutBedingung ? "Bedingung":Out==OutAnweisung?"Anweisung":"Modell");
                                  if (Out==OutModell)
                                  {
                                    OutModell.setToolTipText(null);
                                    g.setTooltip(Sort.gets(OutModell.getSelectedNode().getUserData(),8),OutModell);
                                  }
                                  EnableButtons();
                                }
                        }
                };
                OutModell.addItemListener(IL_Out);
                OutAnweisung.addItemListener(IL_Out);
                OutBedingung.addItemListener(IL_Out);
                OutSpalten.addItemListener(IL_Out);

		OutBuild.addItemListener(new JCOutlinerListener ()
		{
			public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
			{
                          //g.progInfo("OutBuild.outlinerNodeSelectEnd");
                          AktBuildNode = OutBuild.getSelectedNode();
                          if (AktBuildNode != null && AktBuildNode.getLevel()>0)
                          {
				//AktBuildNode = OutBuild.getSelectedNode();
                                Vector VecInvisible = (Vector)AktBuildNode.getUserData();
                                //g.fixInfo("VecInvisible="+VecInvisible);
                                String sMemo=(String)VecInvisible.elementAt(4);
                                TxtMemoBefehl.setText(sMemo);
				if(!CbxKeinTooltip.isSelected())
				{
					int iMBits=Sort.geti(VecInvisible,5);
                                        //TxtTitelBefehl.setText((String)VecInvisible.elementAt(3));
                                        Vector Vec = (Vector)AktBuildNode.getLabel();
                                        String sSpalte = "";
                                        String sEingabe=(String)VecInvisible.elementAt(3);
                                        if (sEingabe!= null && !sEingabe.equals(""))
                                          sSpalte += "\n<b>"+((iMBits&Calc.VAR)>0 ? "var":"Eingabe")+": </b>"+sEingabe;
                                        int iSpalte = Sort.geti(VecInvisible, 1);
                                        if (iSpalte > 0)
                                        {
                                          int iPos = TabSpalten.getPos("Aic", iSpalte);
                                          if (iPos >= 0)
                                            sSpalte += "\n<b>Abfrage: </b>" + TabSpalten.getS(iPos, "Abfrage") + "\nSpalte: " + TabSpalten.getS(iPos, "Bezeichnung")+"<br>Typ: " + TabSpalten.getS(iPos, "Stt");
                                        }
                                        if (sMemo!= null && !sMemo.equals(""))
                                            sSpalte += "\n<h4>Memo:</h4><tt><p width=\"400px\">"+sMemo/*g.breakString(sMemo,60)*/+"</p></tt>";
                                        g.setTooltip("<h3>"+Sort.gets(Vec, 0) + " (" + Sort.gets(Vec, 1) + ")</h3>" + sSpalte, OutBuild);
				}
				else
				{
					g.setTooltip(null,OutBuild);
				}
                                CbxNot.setSelected(Sort.gets(VecInvisible,2).equals("While") && VecInvisible.elementAt(6)!=null);
                            }
                          		if (bGeladen)
                          		{
	                                if(CbxSyncBefehl.isSelected())
	                                  SynchronBefehl(AktBuildNode,false);
	                                if(CbxSyncSpalte.isSelected())
	                                  SynchronSpalte(AktBuildNode);
	                                if(CbxSyncVar.isSelected())
		                              SynchronVar(AktBuildNode);
	                                if(CbxSyncBits.isSelected())
	                                  SynchronBits(AktBuildNode);
                          		}
                                String s = "";
                                if (AktBuildNode != null && AktBuildNode != OutBuild.getRootNode())
                                {
                                  JCOutlinerFolderNode NodP = AktBuildNode.getParent();
                                  while (NodP.getLevel() > 0) // NodP != OutBuild.getRootNode())
                                  {
                                    s = NodP.getLabel() + "\n" + s;
                                    NodP = NodP.getParent();
                                  }
                                }
                                MemoKnoten.setText(s);
				EnableButtons();
			}
			public void outlinerNodeSelectBegin(JCOutlinerEvent ev)   {}
			public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev) {
                          if (bGeaendert)
                          {
//                            Kommentar();
                            EnableButtons();
                          }
                          else
                          {
                            JCOutlinerNode Nod=OutBuild.getSelectedNode();
                            makeKommentarStyle(Nod, Nod.getState()==BWTEnum.FOLDER_CLOSED,true);
                          }
                        }
			public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev) {}
			public void itemStateChanged(JCItemEvent ev) {}
		});

                  /*OutModell.addMouseMotionListener(new MouseMotionListener()
                  {
                          public void mouseDragged(MouseEvent ev)
                          {

                          }
                          public void mouseMoved(MouseEvent ev)
                          {
                            java.awt.Point P=ev.getPoint();
                            g.fixInfo("mouseMoved:"+OutModell.getComponentAt(P));
                          }
                  });*/

		OutModell.addItemListener(new JCOutlinerListener ()
		{
			public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
			{
			}

			public void outlinerNodeSelectBegin(JCOutlinerEvent ev)
			{
                          //g.progInfo("OutModell.outlinerNodeSelectBegin:"+ev.getNode());
			}

			public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
			{
			}

			public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
			{
				//g.progInfo("OutModell.outlinerFolderStateChangeBegin");
				//if(!CbxSchliessbar.isSelected())
				{
					//if(!bGeaendert)
					//	Laden((JCOutlinerFolderNode)OutModell.getSelectedNode(),false,null,0);
                                        EditModell();
					ev.setAllowChange(false);
				}
			}

			public void itemStateChanged(JCItemEvent ev)
			{
			}
		});

		OutBuild.addActionListener(new JCActionListener()
		{
			public void actionPerformed(JCActionEvent ev)
			{
				JCOutlinerNode NodeSelected = OutBuild.getSelectedNode();
                                //g.progInfo("OutBuild.actionPerformed");
				if (bGeaendert)
				{
					Kommentar();
					bGeaendert=true;
					EnableButtons();
				}
                                else
                                {
                                  if(NodeSelected!=OutBuild.getRootNode() && ((String)((Vector)NodeSelected.getUserData()).elementAt(2)).equals("Modell") /*&& !((Boolean)((Vector)NodeSelected.getUserData()).elementAt(5)).booleanValue()*/)
                                  {
                                    Laden(NodeSelected, false,null,0);
                                  }
                                  else
                                    setBreak(0);
                                }
			}
		});

                g.BtnAdd(BtnRauf,"Rauf",AL1);
                g.BtnAdd(BtnRunter,"Runter",AL1);

		BtnZurueck.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                          if (bGeaendert)
                          {
                            int pane = new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("verwerfen", new String[] {});
                            if(pane == Message.YES_OPTION)
                            {
                              checkSperre();
                              Laden(null, true,null,0);
                            }
                          }
                          else
                          {
                            StkHistory.pop();
                            Laden(StkHistory.elementAt(StkHistory.size() - 1), true,null,0);
                          }
			}
		});

                BtnEdit.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          JCOutlinerFolderNode Node = (JCOutlinerFolderNode)OutBuild.getRootNode();
                          int iModell=Sort.geti(Node.getUserData(),0);
                          if (iModell==0)
                          {
                            if (((JCOutlinerFolderNode)OutSpalten.getRootNode()).getChildren()==null)
                              fillSpalten();
                            EditModell();
                            Node = (JCOutlinerFolderNode)OutBuild.getRootNode();
                            iModell=Sort.geti(Node.getUserData(),0);
                          }
                          int iBegriff=g.ModellToBegriff(iModell);//((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue());
                            //SQL.getInteger(g,"SELECT AIC_Begriff FROM Modell WHERE AIC_Modell="+iAIC_Modell);
                          //g.progInfo("Sperre:"+iAIC_Modell+"/"+iBegriff);
                          if (g.exec("update begriff set log_aic_logging="+g.getLog()+" where log_aic_logging is null and aic_begriff="+iBegriff)==1)
                          {
                            g.defInfo("Modell " + g.TabBegriffe.getBezeichnungS(iBegriff) + " ("+iBegriff+") gesperrt");
//                            if (iBegriff != iLast || iLastLog != SQL.getInteger(g,"select aic_logging from begriff where aic_begriff="+iLast))
                                Laden(null,false,null,0);
                            bGeaendert = true;
                            EnableButtons();
                          }
                          else
                          {
                            String sUser = SQL.getString(g,"select benutzer.kennung from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer" +
                                " join begriff on logging.aic_logging=begriff.log_aic_logging where aic_begriff=" +iBegriff);
                            new Message(Message.WARNING_MESSAGE, null, g).showDialog("Modell_gesperrt", new String[]{sUser});
                          }
                        }
                });
                g.BtnAdd(BtnInfoBreak,"InfoBreak",AL1);
                g.BtnAdd(BtnDelBreak,"DelBreak",AL1);
                g.CbxAdd(CbxNot,"CbxNot",AL1);
                g.BtnAdd(BtnAbfrage,"Abfrage",AL1);
                g.BtnAdd(BtnRefresh2,"Refresh2",AL1);
                g.BtnAdd(BtnSyncStamm,"SyncStamm",AL1);
                g.BtnAdd(BtnVarG,"VarG",AL1);
                g.BtnAdd(BtnAbfG,"AbfG",AL1);
                g.BtnAdd(BtnInitFrame,"InitFrame",AL1);
                g.BtnAdd(BtnAufruf,"Aufruf",AL1);

                ActionListener AL2=new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    String s = e.getActionCommand();
                    // g.progInfo("DefModell.AL2=" + s);
                    if (s.equals("reCalc"))
                    {
                      long lClock=Static.get_ms();
                      Vector<Integer> VecStamm = CbxNS.isSelected() ? Static.AicToVec(ZahlBew.intValue()):
                      TabStamm.isEmpty() ? Static.AicToVec(SteStamm.getStamm()) : TabStamm.getVecSpalteI("Aic");
                      FrmAufruf.setVisible(false);
                      Static.bWeb=CbxWebTest.isSelected();
                      Calc.bPause=false;
                      g.setVon(DatVon.getDateTime());
                      g.setBis(DatBis.getDateTime());
                      if (CbxDebug.isSelected())
                      {
                        g.setDebug(true);
                        int iPos=g.TabModelle.getPos("aic_modell", iModell);
                        if (iPos>=0)
                          Aufruf.addDebugModell(g.TabModelle.getI(iPos,"aic_begriff"));
                      }
                      AktCalc.reCalc(VecStamm/*,CbxNS.isSelected()*/, true);
                      g.setDebug(false);
                      Static.bWeb=false;
                      g.clockInfo("reCalc ",lClock);
                    }
                    else if (s.equals("weiter"))
                    {
                      long lClock=Static.get_ms();
                      FrmAufruf.setVisible(false);
                      Static.bWeb=CbxWebTest.isSelected();
                      g.setDebug(CbxDebug.isSelected());
                      AktCalc.weiter(AktCalc.TabAbfrage,bJa);
                      g.setDebug(false);
                      bWeiter=false;
                      Static.bWeb=false;
                      g.clockInfo("weiter ",lClock);
                    }
                    else if (s.equals("MD"))
                    {
                      bJa=AktCalc.MD_Aufruf();
                      bWeiter=bJa;
                      BtnMD.setEnabled(!bWeiter);
                      BtnWeiter.setEnabled(bWeiter);
                    }
                    else if (s.equals("Frage"))
                    {
                    	int iM=JOptionPane.showConfirmDialog(FrmAufruf, AktCalc.getInfo(),"Modell-Frage",JOptionPane.YES_NO_OPTION);
                    	bJa=iM==JOptionPane.YES_OPTION;
                    	BtnFrage.setEnabled(false);
                    	BtnWeiter.setEnabled(true);
                    }
                    else
                      g.fixtestError(s+" wird von DefModell.AL2 nicht unterstützt");
                  }
                };

                g.BtnAdd(BtnReCalc,"reCalc",AL2);
                g.BtnAdd(BtnWeiter,"weiter",AL2);
                g.BtnAdd(BtnMD,"MD",AL2);
                g.BtnAdd(BtnFrage,"Frage",AL2);

		BtnAufrufOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
			  JCOutlinerFolderNode Node = (JCOutlinerFolderNode)OutBuild.getRootNode();
			  final int iAIC_Modell = iModell>0 ? iModell:((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
			  	Calc.Msg=new Message(Message.CANCEL_OPTION/*+Message.UNMODAL*/,(JFrame)thisFrame,g,-2);
			  	Calc.Msg.showDialog("rechne",new String[] {g.TabModelle.getBezeichnungS(iAIC_Modell)});
			   	new Thread(new Runnable()
                                {
                                  public void run()
                                  {
                                    try
                                    {
//                                    	g.fixtestError("Name/PW="+EdtName.getText()+"/"+EdtPW.getValue());
                                      String sBez=g.TabModelle.getBezeichnungS(iAIC_Modell);
                                      int iPos=g.TabModelle.getPos("aic_modell", iAIC_Modell);
                                      int iMaxB=1000000;
                                      int iHM=iPos>=0 ? g.TabModelle.getI(iPos,"aic_begriff"):0;
                                      if (iPos>=0 && g.TabModelle.getI(iPos,"Max_B")>0)
                                    	  iMaxB=g.TabModelle.getI(iPos,"Max_B");
//                                      g.fixtestError("maxB="+iMaxB);
                                      Calc.bPause=false;                                    
//                                      boolean bDebug = g.Debug();
                                      java.sql.Timestamp dVon = g.getVon();
                                      java.sql.Timestamp dBis = g.getBis();
                                      // boolean bReCalc=iAIC_Modell == iAIC_ModellOld && !CbxNeu.isSelected();
                                      String sName=EdtName.getText();
                                      // if (bReCalc && sName.length()>0)
                                    	//   new Message(Message.WARNING_MESSAGE, null, g).showDialog("Name_bei_ReCalc");
                                    	  //Static.printError("Name bei ReCalc nicht möglich!");
                                      boolean bName=/*!bReCalc &&*/ !sName.isEmpty();
                                      Global g2=!bName ? g:new Global(g, !bName);
                                      Static.bWeb=CbxWebTest.isSelected();
                                      if (!bName || g2.Login(thisJFrame(),sName, null)) //EdtPW.getValue()))
                                      {
                                        if (bName)
                                    		  ShowAbfrage.refreshBerechtigung(g2);
	                                      g2.setDebug(CbxDebug.isSelected());
                                    	  if (CbxDebug.isSelected())
                                    		  Aufruf.addDebugModell(iHM);
//                                    	  else
//                                    		  Aufruf.removeDebugModell(iHM);
	                                      //JCOutlinerFolderNode Node = (JCOutlinerFolderNode)OutBuild.getRootNode();
	                                      //int iAIC_Modell = iModell>0 ? iModell:((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
	                                      g2.setVon(DatVon.getDateTime());
	                                      g2.setBis(DatBis.getDateTime());
                                        g.fixtestError((/*bReCalc ? "reCalc ":*/"neu ")+ g.getBegBez(iHM)+":"+g2.getVB());
                                    	  // g.fixtestError("ZR2="+g2.getVB());
	                                      
	                                      FrmAufruf.setVisible(false);
	                                      //g.progInfo(iAIC_Begriff+","+Static.AicToVec(CboStammsatz.getSelectedAIC())+","+CbxTimer.isSelected());
	                                      Vector VecStamm = CbxNS.isSelected() ? Static.AicToVec(ZahlBew.intValue()):
	                                	  TabStamm.isEmpty() ? Static.AicToVec(SteStamm.getStamm()) : TabStamm.getVecSpalte("Aic");
	                                      long lClock=Static.get_ms();
	                                      // if(bReCalc)
	                                      // {
	                                      //   AktCalc.reCalc(VecStamm/*,CbxNS.isSelected()*/, true);
	                                      //   g2.clockInfo("reCalc "+sBez,lClock);
	                                      // }
	                                      // else
	                                      //{
	                                    	  g.fixtestInfo("------------- vor Modellberechnung von "+sBez);
	//                                    	  if (CbxJavaFx.isSelected())
	//                                    		  Platform.runLater(() ->
	//                                    		  	AktCalc = new Calc(newStage(sBez), g, iAIC_Modell, false, VecStamm, CbxTimer.isSelected()?1000000:0,null,0));
	//                                    	  else
	                                    		  AktCalc = new Calc((JFrame)thisFrame, g2, iAIC_Modell, false, VecStamm,0,false, CbxTimer.isSelected()?iMaxB:0,null,0,null,null,null,null,0,-1,null,Optional.of(ZahlBBeg.intValue()));
	                                    	  g.fixtestInfo("------------- nach Modellberechnung von "+sBez);
	                                        iAIC_ModellOld = iAIC_Modell;
                                          g2.setDebug(false);
	                                        g.clockInfo("Calc "+sBez,lClock);
	                                      //}
	                                      String sFehler = AktCalc.Fehler();
	                                      if (sFehler != null)
	                                        System.err.println("Fehler bei Modell:"+sFehler);
	                                      
	                                      //AktCalc=null;
//	                                      g.fixtestError("vor ausloggen:"+g2.getLog()+"/"+g.getLog());
	                                      if (bName) 
	                                    	  g2.Logout();
	                                      // else if (CbxNeu.isSelected())
	                                    	//   g2.unConnect();
	                                      else
	                                      {
	                                    	  g.setVon(dVon);
	                                    	  g.setBis(dBis);
//	                                    	  g.setDebug(bDebug);
	                                      }
//	                                      g.fixtestError("nach ausloggen:"+g2.getLog()+"/"+g.getLog());
                                      }
                                      else if (bName)
                                    	  new Message(Message.WARNING_MESSAGE, null, g).showDialog("Einloggfehler", new String[] {sName});
                                      Static.bWeb=false;
                                    	  //Static.printError("Einloggen mit "+sName+" nicht möglich!");
                                      if (Calc.Msg != null)
                                	    Calc.Msg.dispose();
                                    }
                                    catch(Exception e) {
                                      Static.printError("Modellfehler:" + e);
                                    }
                                  }
                                }).start();
                                if (g.Mac())
                                  Calc.Msg.setLocation(0,2000);
			   	Calc.Msg.setVisible(true);
			   	Calc.bPause=true;
			   	/*int i=0;
			   	while (Calc.Msg != null)
			   	{
			   	  i++;
			   	  g.testInfo(" vor setVisible "+i);
			   	  Calc.Msg.setVisible(true);
			   	  g.testInfo("nach setVisible "+i);
			   	  Calc.bPause=true;
			   	  while (Calc.bPause)
			   	    Static.sleep(1000);
			   	}*/
			}
		});
                g.BtnAdd(BtnAufrufAbbruch,"AufrufAbbruch",AL1);

		CboModul.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange()==ItemEvent.SELECTED)
					fillOutModell();
			}
		});

		/*CbxHierarchie.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			  iModellF=-1;
			  fillOutModell();
			}
		});*/

		g.BtnAdd(BtnInit,"Init",AL1);
                g.BtnAdd(BtnUnd,"Und",AL1);
                g.BtnAdd(BtnInfo,"Info",AL1);

		addPopup();
                g.BtnAdd(FomSpalten.getButton("Refresh"),"RefreshBtn",AL);
                g.BtnAdd(getButton("Refresh"),"RefreshBtn",AL);
		show();
		EnableButtons();
	}
  
  private void InfoBreak()
  {
	g.fixInfo("VecModelle="+Aufruf.VecModelle);
	if (Aufruf.VecBefehl != null)
  	  g.fixInfo("VecBefehl="+Aufruf.VecBefehl);
  	if (Aufruf.VecSpalten != null)
  	  g.fixInfo("VecSpalten="+Aufruf.VecSpalten);
  	if (Aufruf.VecVar != null)
  	  g.fixInfo("VecVar="+Aufruf.VecVar);
  	if (Aufruf.VecEingabe != null)
  	  g.fixInfo("VecEingabe="+Aufruf.VecEingabe);	
  	g.fixInfo("VecBreak="+Aufruf.VecBreak);
  	if (Aufruf.VecDebug != null)
  		g.fixInfo("VecDebug="+Aufruf.VecDebug);
  }
  
//  private Stage newStage(String sTitel)
//  {
//	  Stage Stg=new Stage();
//	  Stg.setTitle(sTitel);
//	  BorderPane root=new BorderPane();
//	  root.setCenter(new Label(" wird ausgeführt .."));
//	  Scene scene=new Scene(root);
//	  Stg.setMinWidth(300);
//	  Stg.setMinHeight(100);
//	  Stg.setScene(scene);
//	  Stg.show();
//	  return Stg;
//  }
  
  private void Zusammen(AUOutliner Out)
  {
	  if (CbxZusammen.isSelected())
      {
        iEinBits -=iEinBits&(EIN+TYP+ABF);
        
      }
      else
      {
        iEinBits |= EIN + TYP + ABF;
        Out.setColumnDisplayWidth(2,50);
        Out.setColumnDisplayWidth(5,80);
        Out.setColumnDisplayWidth(6,120);
      }
	  for (int i = 1; i < Out.getColumnLabels().length; i++)
          if ((iEinBits & 1 << i) == 0)
        	Out.setColumnDisplayWidth(i,0);
  }

        private void Zusammen()
        {
        	Zusammen(OutBuild);
          if (!bGeaendert && OutBuild.getRootNode().getLabel() instanceof Vector)
            Laden(null,false,null,0);
        }

        public void InitFrame()
        {
          Rectangle DimS=Static.getMonRec(thisFrame);//Toolkit.getDefaultToolkit().getScreenSize();
          int iH=DimS.height-35;
          if (iH>1500) iH=1500;
          FomBuild.setLocation(DimS.x+DimS.width/2, DimS.y);
          FomBuild.setSize(DimS.width/2,iH);
          FomSpalten.thisFrame.setLocation(DimS.x+DimS.width/4, DimS.y);
          FomSpalten.thisFrame.setSize(DimS.width/4-20,iH);
          thisFrame.setLocation(DimS.x, DimS.y);
          thisFrame.setSize(DimS.width/4-20,iH);
          //g.fixInfo("xx neu");
          OutBuild.setVisible(false);
          OutBuild.setColumnDisplayWidth(0,250); // Bezeichnung
          OutBuild.setColumnDisplayWidth(1,60);  // Kennung
          OutBuild.setColumnDisplayWidth(2,CbxZusammen.isSelected()?0:40);  // Eingabe
          OutBuild.setColumnDisplayWidth(3,40);  // Refresh
          OutBuild.setColumnDisplayWidth(4,40);  // Bits
          OutBuild.setColumnDisplayWidth(5,CbxZusammen.isSelected()?0:80);  // Stt-Bew
          OutBuild.setColumnDisplayWidth(6,CbxZusammen.isSelected()?0:110); // Abfrage
          OutBuild.setColumnDisplayWidth(7,150+(CbxZusammen.isSelected()?CbxNurAic.isSelected()?40:200:0)); // Spalte
          OutBuild.setColumnDisplayWidth(8,33);  // Nr
          OutBuild.setColumnDisplayWidth(9,40);  // Memo
          for (int i = 1; i < OutBuild.getColumnLabels().length; i++)
            if ((iEinBits & 1 << i) == 0)
              OutBuild.setColumnDisplayWidth(i,0);
          OutBuild.setVisible(true);
        }

        private void fillModelle(String s)
        {
        	//g.fixtestError("fillModelle "+s);
          if ((s.equals("nur Submodelle") || s.equals("selektierte Modelle")) && OutModell.getSelectedNode()==null)
            return;
            iModellF = s.equals("alle Modelle") || s.equals("nur Web-Modelle") || s.equals("Modelle in Module") || s.equals("nur Tod") ? 0: Sort.geti(OutModell.getSelectedNode().getUserData(),0);
            bHierachie=s.equals("Submodellehierarchie");
            bSubModelle=s.equals("nur Submodelle") || bHierachie;
            VecModelleF=s.equals("selektierte Modelle") || bSubModelle ? g.getAics(OutModell,0):null;
            g.fixtestInfo(s+"-> iModellF="+iModellF+", VecModelleF="+VecModelleF);
            bModule = s.equals("Modelle in Module");
            bTod=s.equals("nur Tod");
            bWeb=s.equals("nur Web-Modelle");
            fillOutModell();
            if (bSubModelle || s.equals("selektierte Modelle"))
              fillSpalten();
        }

        private void addCbx(JPanel Pnl,String s,int i,int iPos)
        {
          AUCheckBox Cbx = getCheckboxM(s, (iEinBits & i) > 0);
          if (iPos>0)
            Pnl.add(VecZB.elementAt(iPos-1));
          Pnl.add(Cbx);
          TabEin.newLine();
          TabEin.setInhalt("Cbx", Cbx);
          TabEin.setInhalt("bit", i);
        }

      private void Einstellung()
      {
        if (Dlg==null)
        {
          Dlg = new JDialog((JFrame)thisFrame, g.getBegriffS("Dialog", "Einstellungen"));
          JPanel Pnl = new JPanel(new GridLayout(0, 2, 0, 0));
          JPanel Pnl2 = new JPanel(new GridLayout(0, 1, 0, 0));
          JTabbedPane TP=new JTabbedPane();
          TP.add(g.getBegriffS("Show","Modell Befehle"),Pnl);
          TP.add(g.getBegriffS("Show","Spalten"),Pnl2);
          Dlg.getContentPane().add("Center", TP);
          if (TabEin == null)
            TabEin = new Tabellenspeicher(g, new String[] {"Cbx", "bit"});
          else
            TabEin.clearAll();
          for (int i=0;i<10;i++)
            VecZB.addElement(new Zahl(0));
          addCbx(Pnl, "Bezeichnung", BEZ,1);
          addCbx(Pnl, "Kennung", KEN,2);
          addCbx(Pnl, "Eingabe", EIN,3);
          addCbx(Pnl, "Refresh", RF,4);
          addCbx(Pnl, "Bits", BITS,5);
          addCbx(Pnl, "Stamm-BewTyp", TYP,6);
          addCbx(Pnl, "Abfrage3", ABF,7);
          addCbx(Pnl, "Spalte", SP,8);
          addCbx(Pnl, "Nr", NR,9);
          addCbx(Pnl, "Memo", MEMO,10);

          //addCbx(Pnl2, "Bezeichnung", BEZ2);
          addCbx(Pnl2, "Aic", AIC,0);
          addCbx(Pnl2, "Datentyp", DT,0);
          addCbx(Pnl2, "Anzahl", ANZ,0);
          addCbx(Pnl2, "Stammtyp", STT,0);
          addCbx(Pnl2, "Gruppiert", GRUP,0);
          addCbx(Pnl2, "Sortiert", SORT,0);
          addCbx(Pnl2, "kein_Stammsatz", NOS,0);
          addCbx(Pnl2, "kein_Bew-Zeitraum", NOZ,0);
          addCbx(Pnl2, "kein_Refresh", NOR,0);
          addCbx(Pnl2, "Distinct", DIST,0);         
          JPanel PnlButton = new JPanel(new GridLayout(1, 2, 2, 2));
          final JButton BtnOk = g.getButton("Ok");
          JButton BtnAbbruch = g.getButton("Abbruch");
          final SpinBox EdtER=new SpinBox();
          EdtER.setIntValue(OutBuild.getNodeIndent());
          ActionListener ALx = new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              if (ev.getSource() == BtnOk)
              {
                iEinBits = 0;
                for (TabEin.moveFirst(); !TabEin.out(); TabEin.moveNext())
                {
                  if (((AUCheckBox)TabEin.getInhalt("Cbx")).isSelected())
                    iEinBits += TabEin.getI("bit");
                }
              }
              for (int i = 0; i < OutBuild.getColumnLabels().length; i++)
              {
                //int iWx=(iEinBits & 1 << i) > 0 ? i < 4 ? (iW[i + 24] > 0 ? iW[i + 24] : 50) : i < 8 ? (iW[i + 28] > 0 ? iW[i + 28] : 50) : (iW[i + 33] > 0 ? iW[i + 33] : 50) : 0;
                //int iWx=(iEinBits & 1 << i) > 0 ? (iW[i + 24] > 30 ? iW[i + 24] : 80) : 0;
                //g.progInfo("B.setColumnDisplayWidth:"+i+"="+iWx);
                boolean b=(iEinBits & 1 << i) > 0;
                //if (b)
                  OutBuild.setColumnWidth(i, VecZB.elementAt(i).intValue());
                OutBuild.setColumnDisplayWidth(i, b ? VecZB.elementAt(i).intValue():0);
              }
              for (int i = 1; i < OutSpalten.getColumnLabels().length; i++)
                  OutSpalten.setColumnDisplayWidth(i, (iEinBits & 1 << (i+12)) > 0 ? /*i < 4 &&*/ iW[i + 8] > 0 ? iW[i + 8] /*: i < 8 && iW[i + 12] > 0 ? iW[i + 12]*/ : -999 : 0);
              OutBuild.setNodeIndent(EdtER.getIntValue());
              Dlg.setVisible(false);
            }
          };
          BtnOk.addActionListener(ALx);
          BtnAbbruch.addActionListener(ALx);
          PnlButton.add(BtnOk);
          PnlButton.add(BtnAbbruch);
          Dlg.getContentPane().add("South", PnlButton);
          JPanel PnlN=new JPanel(new FlowLayout());
          PnlN.add(new JLabel("Einrückung:"));
          PnlN.add(EdtER);
          Dlg.getContentPane().add("North",PnlN);
          Dlg.pack();
        }
        for (int i=0;i<10;i++)
        {
          VecZB.elementAt(i).setValue(OutBuild.getColumnDisplayWidth(i)==0? OutBuild.getColumnWidth(i):OutBuild.getColumnDisplayWidth(i));
          g.fixInfo(i+"/"+OutBuild.getColumnDisplayWidth(i)+"/"+OutBuild.getColumnWidth(i));
        }
        Static.centerComponent(Dlg, thisFrame);
        Dlg.setVisible(true);
      }

  	private void showInfo(JCOutlinerNode Node,int iArt,Window WinModal) //boolean bVar)
  	{
  		int iSub=0;
  		String sTitel=null;
  		if (iArt<0)
  		{
  			iSub=Sort.geti(Node.getLabel(),1);
  			sTitel=Sort.gets(Node.getLabel(), 3);
  		}
//  		g.fixtestError("showInfo "+iArt+"/"+Node);
  	  String sVarTxt=TxtVar.getText();
      String sVar=iArt==VAR ? " and "+(sVarTxt.equals(Static.sLeer) ? g.bit("MBits",Calc.VAR):"var like '"+sVarTxt+"'"):
    	  iArt==ART ? " and "+g.bitis("MBits", Calc.ART, RadEmpty.isSelected() ? Calc.EMPTY:RadVec.isSelected() ? Calc.USE_VEC : RadVecBew.isSelected() ? Calc.VEC_BEW:Calc.NORMAL):"";
      int iVA=iSub==0 ? 0 : iArt==-22 ? Calc.C_ART : iArt==-24 ? Calc.M_ART : iArt==-4 ? Calc.ART:Calc.VART;
      int iVG=iSub==0 ? 0:iArt==-1 ? iSub==2?Calc.IMMER:Calc.NIE : iArt==-22 ? iSub==2?Calc.CONCAT:iSub==3?Calc.CWS:Calc.INIT: iArt==-24 ? iSub==2?Calc.ADD:iSub==3?Calc.SUB:Calc.MINIT: 
    	  iArt==-4 ? iSub==2?Calc.EMPTY:iSub==4?Calc.USE_VEC:Calc.VEC_BEW : iArt==-20 ? iSub==2?Calc.VICH:iSub==3?Calc.V_R:Calc.VALL : Calc.PERM;
      g.fixtestError("showInfo "+iArt+"/"+iSub+"/"+Node+"->"+iVG);
      String sSQL=null;
      if (iArt==USE_JOK)
      {
    	  int iPos=g.TabBegriffe.getPos("Aic", g.ModellToBegriff(iModell));
    	  String sSttK=g.TabStammtypen.getKennung(g.TabBegriffe.getI(iPos,"Stt"));
//    	  g.fixtestError("Stt-Kennung für JokerStt="+sSttK);
    	  sSQL="select distinct null Abfrage,null Stt, null Bew,defbezeichnung Modell" +
  		  	    ",abfrage.aic_begriff AbfBeg,begriff.aic_begriff,b.aic_modell aic from begriff"+g.join2("modell","begriff")+" join befehl2 b on modell.aic_modell=b.aic_modell"+
    			" join spalte on spalte.aic_spalte=b.aic_spalte left outer join abfrage on spalte.aic_abfrage=abfrage.aic_abfrage join bedingung b2 on abfrage.aic_abfrage=b2.aic_abfrage and vergleichswert='*JokerStt "+sSttK+"*'"+
  		  	    " where not hide and"+g.in("b.aic_modell",TabModell.getVecSpalte("AIC"));
      }
      else
      {
    	  if (iArt>=0 && (Node==null || !(Node.getUserData() instanceof Vector)))
    		  return;
    	  sSQL="select aic_befehl+1-(select min(aic_befehl) from befehl2 where aic_modell=b.aic_modell) Zeile,defbezeichnung Modell,null Spalte,null Datentyp,b.var,null VArt" +
//    			  g.bei("b.mbits",Calc.VAR,"var")+(g.MySQL() ?",(mbits&"+Calc.VART+")/"+Calc.VICH+"+1 VArt":",null x")+
    			  ",b.eingabe,b.aic_modell aic,begriff.aic_begriff,b.aic_spalte,null aufSpalte,null _save,b.hide"+//g.bei("b.mbits",Calc.SPALTE,"aufSpalte")+g.bei("b.mbits",Calc.SAVE,"_save")+",(case when b.hide=1 then 'x' else '' end) hide"+
    			  ",null Abfrage,null Stt, null Bew,abfrage.aic_begriff AbfBeg,aic_befehl"+(iArt<0 ? ",(select kennung from code where b.aic_code=code.aic_code) Befehl":"")+",null alle,null Gruppe,mbits"+
//    			  g.bei("b.mbits",Calc.ALLE,"alle")+g.bei("b.mbits",Calc.GRUPPE,"Gruppe")+
    			  " from begriff"+g.join2("modell","begriff")+" join befehl2 b on modell.aic_modell=b.aic_modell"+
    	  			  " left outer join spalte on spalte.aic_spalte=b.aic_spalte left outer join abfrage on spalte.aic_abfrage=abfrage.aic_abfrage"+
    			  (iArt<0 ? " where"+(iSub>0 ? g.bits("mbits",iVA)+"="+iVG:g.bit("mbits",(long)Math.pow(2,-iArt))):
    	  			  " where b.aic_code=" +((Vector)Node.getUserData()).elementAt(0)+(iArt==USE_MOD ? " and"+g.in("b.aic_modell",TabModell.getVecSpalte("AIC")):"")+(iArt==EINGABE ? " and eingabe like '"+TxtEingabe.getText()+"'":"")+sVar);
      }
  	  Tabellenspeicher Tab=new Tabellenspeicher(g,sSQL,true);
  	  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
  	  {
  	    if (iArt!=USE_JOK && !Tab.isNull("aic_spalte"))
  	    {
  	      int iPos=TabSpalten.getPos("AIC",Tab.getI("aic_spalte"));
  	      if (iPos>=0)
  	      {
	  		Tab.setInhalt("Spalte", TabSpalten.getS(iPos,"Bezeichnung"));
	  		Tab.setInhalt("Datentyp", TabSpalten.getS(iPos,"Datentyp"));
  	      }
  	      else
  	    	  Tab.setInhalt("Spalte","???");
  	    }
  	    else if (iArt!=USE_JOK)
  	    {
  	    	Tab.setInhalt("aic_spalte",Static.sLeer);
  	    	Tab.setInhalt("Spalte",Static.sLeer);
  	    	Tab.setInhalt("Datentyp",Static.sLeer);
  	    }
  	    if (!Tab.isNull("AbfBeg"))
  	    {
  	    	int iPos=g.TabBegriffe.getPos("Aic", Tab.getI("AbfBeg"));
  	    	if (iPos>=0)
  	    	{
  	    		Tab.setInhalt("Abfrage", g.TabBegriffe.getS(iPos,"DefBezeichnung"));
  	    		int iStt=g.TabBegriffe.getI(iPos,"Stt");
  	    		int iBew=g.TabBegriffe.getI(iPos,"Erf");
  	    		Tab.setInhalt("Stt", iStt>0 ? g.TabStammtypen.getBezeichnungS(iStt):"-");
  	    		Tab.setInhalt("Bew", iBew>0 ? g.TabErfassungstypen.getBezeichnungS(iBew):"-");
  	    	}
  	    }
  	    else
  	    {
  	    	Tab.setInhalt("AbfBeg",Static.sLeer);
  	    	Tab.setInhalt("Abfrage",Static.sLeer);
  	    	Tab.setInhalt("Stt",Static.sLeer);
  	    	Tab.setInhalt("Bew",Static.sLeer);
  	    }
//  	    String sE=Tab.getS("Eingabe");
//  	    if (sE.equals(Static.sLeer) || (Tab.getI("mbits")&Calc.VAR)==0)
//  	    {
//  	    	Tab.setInhalt("var",Static.sLeer);	
//  	    }
//  	    else
//  	    {
//  	    	int iPos=sE.indexOf("=");
//            String sV=iPos<0 ? sE+"":sE.substring(0,iPos);
//            String sEingabe=iPos<0 ? Static.sLeer:sE.substring(iPos+1);
//            Tab.setInhalt("var",sV);
//            Tab.setInhalt("Eingabe",sEingabe);
//  	    }
  	    Tab.setInhalt("VArt",(Tab.getI("mbits")&Calc.VART)/Calc.VICH+1);
  	    Tab.setInhalt("aufSpalte", Static.JaNein2((Tab.getI("mbits")&Calc.SPALTE)>0));
  	    Tab.setInhalt("_save", Static.JaNein2((Tab.getI("mbits")&Calc.SAVE)>0));
  	    Tab.setInhalt("hide", Static.JaNein2(Tab.getB("hide")));
  	    Tab.setInhalt("alle", Static.JaNein2((Tab.getI("mbits")&Calc.ALLE)>0));
  	    Tab.setInhalt("Gruppe", Static.JaNein2((Tab.getI("mbits")&Calc.GRUPPE)>0));
  	  }
  	  Tab.showGrid(iArt<0 ? "Befehle mit bit " + sTitel: Sort.gets(Node.getLabel())+(iArt==EINGABE ? " mit E="+TxtEingabe.getText():""),WinModal);
          Tab.Grid.getOutliner().addMouseListener(new MouseListener()
          {
            public void mousePressed(MouseEvent ev) {}

            public void mouseClicked(MouseEvent ev)
            {
              if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
              {
                //selectAbschnitt(Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(), 0));
                int iPos=TabModell.getPos("AIC",Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(), 7));//g.BegriffToModell(
                if (iPos>=0)
                {
                  ((JTabbedPane)OutModell.getParent().getParent().getParent()).setSelectedIndex(0);
                  JCOutlinerNode Nod=(JCOutlinerNode)TabModell.getInhalt("Node",iPos);
                  openUp(Nod);
                  Static.makeVisible(OutModell, Nod);
                }
              }
              else if (ev.getButton() == 1 && ev.getClickCount()> 1)
              {
            	  JCOutlinerNode Nod=((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode();
            	  //g.fixtestError("doppelklick bei "+Nod.getLabel());           	  
            	  // Laden von Modell-Befehle und positionieren auf Zeile
            	  Vector<Object> VecVisible = new Vector<Object>();
            	  VecVisible.addElement(g.getDefBez(Sort.geti(Nod.getLabel(), 8)));
            	  Vector<Object> VecInvisible = new Vector<Object>();
            	  VecInvisible.addElement(Sort.geti(Nod.getLabel(), 7));
//            	  g.fixtestError("VecV="+VecVisible+", VecI="+VecInvisible);
//            	  g.fixtestError("Nod="+Nod.getLabel());
            	  JCOutlinerFolderNode Nodx=new JCOutlinerFolderNode(VecVisible);
            	  Nodx.setUserData(VecInvisible);
            	  Laden(Nodx,false,null,-Sort.geti(Nod.getLabel(), 17));
              }
            }

            public void mouseEntered(MouseEvent ev)  {}
            public void mouseExited(MouseEvent ev) {}
            public void mouseReleased(MouseEvent ev) {}
          });

  	}

        private void Aufruf()
        {
          DatVon.setDate(g.getVon());
          DatBis.setDate(g.getBis());
          CbxNS.setSelected((iMBits&(Global.cstBew/*+Global.cstnachSave*/))>0);
          Static.centerComponent(FrmAufruf, thisFrame);
          if (iModellA != iModell)
          {
            iModellA=iModell;
            AktCalc=null;
          }
          int iBegriff=g.ModellToBegriff(iModell);
          int iPos=g.TabBegriffe.getPos("Aic",iBegriff);
          SteStamm.setStt(g.TabBegriffe.getI(iPos,"Stt"));
          if (SteStamm.getStt()>0)
            SteStamm.setStamm(g.getSyncStamm(SteStamm.getStt()));
          FrmAufruf.setTitle(g.getBezeichnung("Tabellenname", "Modell") + ": "+g.getBegBez2(iPos));
          BtnReCalc.setEnabled(AktCalc != null);
          BtnWeiter.setEnabled(AktCalc != null && AktCalc.getMsgArt()>3 && bWeiter);
          BtnMD.setEnabled(AktCalc != null && AktCalc.getMsgArt()==4 && !bWeiter);  
          BtnFrage.setEnabled(AktCalc != null && AktCalc.getMsgArt()==5 && !bWeiter); 
//          g.fixtestError("Aufruf: Begriff="+iBegriff+"-> Pos="+iPos+" -> web="+g.TabBegriffe.getB(iPos,"Web"));
          CbxWebTest.setSelected(g.TabBegriffe.getB(iPos,"Web"));
          FrmAufruf.setVisible(true);
        }

        private void show_Spalte_der_Modelle(int iSpalte,String s,String sEin)
        {
        	//g.fixtestError("show_Spalte_der_Modelle:"+s);
        	String sText=sEin==null ? TxtEingabe.getText():sEin;
//        	boolean bE=s.startsWith("E=");
        	boolean bV=s.startsWith("V=");
        	String sW=(bV ? "var":"Eingabe")+"='"+sText+"'";
        			//bV ? g.bit("mbits",Calc.VAR)+" and (eingabe='"+sText+"' or eingabe like'"+sText+"=%')":bE ? "("+g.bit("mbits",Calc.VAR)+" and eingabe like '%="+sText+"' or"+g.not("mbits",Calc.VAR)+" and eingabe='"+sText+"')":
//        		"(eingabe='"+sText+"' or eingabe like'"+sText+"=%' or eingabe like '%$"+sText+"%')";
          Tabellenspeicher Tab=iSpalte != 0 ? DefAbfrage.getModelle_der_Spalte(g,iSpalte,TabModell.getVecSpalte("AIC")):
          new Tabellenspeicher(g,"select defbezeichnung,begriff.kennung,(select kennung from code where aic_code=befehl2.aic_code) Befehl,Eingabe"+
                  ",aic_befehl+1-(select min(aic_befehl) from befehl2 where aic_modell=modell.aic_modell) Zeile,befehl2.aic_modell,aic_befehl,befehl2.hide,Var,mbits Art"+g.AU_Memo("Befehl","befehl2")+
        		  ",(case when befehl2.hide>0 then "+Global.ColTod.getRGB()+" else 0 end) FARBE from befehl2"+
                  " join modell on befehl2.aic_modell=modell.aic_modell"+g.join("begriff","modell")+" where "+sW+
                  " and"+g.in("befehl2.aic_modell",TabModell.getVecSpalte("AIC")),true);
//          g.fixtestError(s+":"+Tab.getSQL());
          if (Tab.size()>0 && iSpalte==0)//Tab.exists("Var"))
        	  for (int i=0;i<Tab.size();i++)
        	  {
        		  int iMBits=Tab.getI(i,"Art");
        		  if ((iMBits & Calc.VAR)>0)
        		  {
        			  String sE=Tab.getS(i,"Eingabe");
//        			  int iPos=sE.indexOf("=");
//                      String sVar=iPos<0 ? sE+"":sE.substring(0,iPos);
//                      String sEingabe=iPos<0 ? null:sE.substring(iPos+1);
                      Tab.setInhalt(i, "Eingabe", g.encodeMath(sE));
//                      Tab.setInhalt(i, "Var", sVar);
                      int iVArt=iMBits&Calc.VART;
                      Tab.setInhalt(i, "Art",(iVArt==Calc.VICH ? "2":iVArt==Calc.V_R ? "3":iVArt==Calc.VALL ? "4":"1")+((iMBits&Calc.PERM)==0 ? "":"p"));
        		  }
        		  else
        			  Tab.setInhalt(i, "Art",null);
        	  }
          //Tab.showGrid(TxtEingabe.getText());
          final JDialog FomGid = new JDialog((JFrame)FomSpalten.thisFrame,s, false);
          final AUOutliner Grid = new AUOutliner();
          FomGid.getContentPane().add("Center", Grid);
          Tab.showGrid(Grid);
          FomGid.setSize(700, 400);
          Grid.addActionListener(new JCActionListener() {
            public void actionPerformed(JCActionEvent ev) {
              JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
              Vector<Integer> Vec=new Vector<Integer>();
              //g.testInfo("Nod="+Nod.getLabel());
              Vec.addElement(Sort.geti(Nod.getLabel(),iSpalte==0 ? 5:9));
              Nod.setUserData(Vec);
              iBefehl=Sort.geti(Nod.getLabel(),iSpalte==0 ? 6:10);
//              g.fixtestError("Befehl="+iBefehl+", Vec="+Vec);
              Laden(Nod,false,null,0);
              //if(Tab.FrmGrid != null)
              //  Tab.FrmGrid.dispose();
              //Tab.showGrid("Abf.:" + ((Vector)Nod.getLabel()).elementAt(1), bModal ? FomGid:null);
              //Tab.showGrid("Abf.:"+((Vector)Nod.getLabel()).elementAt(1),FomGid);//,false);
            }
          });
          Static.centerComponent(FomGid,FomSpalten.thisFrame);
          FomGid.toFront();
          FomGid.setVisible(true);
        }

        @SuppressWarnings("unchecked")
	private void addPopup()
        {
          /* ------------------------------------- Popup-Menü für Spalte ------------------------------------------------ */

	  MnuClose2 = g.addMenuItem("Close", popSpalte);
          MnuClose2.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              popSpalte.setVisible(false);
              MnuClose2.setVisible(false);
            }
          });
          MnuClose2.setVisible(false);
          MnuSpaltenInfo = new JMenu(g.getBegriffS("Show", "Inhalt"));
          MnuSpaltenInfo.setFont(g.fontStandard);
          popSpalte.add(MnuSpaltenInfo);
          MnuSpaltenInfo.setVisible(false);
          AL=new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              String s = ev.getActionCommand();
              // -------------------------------------------- popup für Modell    -------------
              if (s.equals("Close1"))
              {
                popModell.setVisible(false);
                MnuClose1.setVisible(false);
              }
              else if (s.equals("Bearbeiten Modell"))
                EditModell();
              else if (s.equals("Zeige Modell"))
              {
                 if (((JCOutlinerFolderNode)OutModell.getSelectedNode()).getLevel()>0)
                   ZeigeModell(0,true);
              }
              else if (s.equals("Aufruf"))
                Aufruf();
              else if (s.equals("Info Modell"))
              {
                JCOutlinerFolderNode NodeModell = (JCOutlinerFolderNode)OutModell.getSelectedNode();
                if (NodeModell!=null && NodeModell.getLevel()>0)
                  Info(NodeModell,true);
              }
              else if (s.equals("Modellbits"))
                ShowModellBits();
              else if (s.equals("Edit") || s.equals("Kopie"))
                BtnBearbeiten(s.equals("Kopie"));
              else if (s.equals("Neu"))
                BtnNeu();
              else if (s.equals("Loeschen"))
                Loeschen();
              else if (s.equals("alle Modelle") || s.equals("nur Web-Modelle") || s.equals("selektierte Modelle") || s.equals("Modelle in Module") || s.equals("nur Submodelle") || s.equals("Submodellehierarchie") || s.equals("nur Tod"))
                fillModelle(s);

              // -------------------------------------------- popup für Spalten   -------------
              else if (s.equals("show"))
              {
                JCOutlinerNode NodeSpalte = OutSpalten.getSelectedNode();
                if (NodeSpalte.getLevel()==4)
                  show_Spalte_der_Modelle(Sort.geti(NodeSpalte.getUserData(),0),""+NodeSpalte.getLabel(),null);
                else if (NodeSpalte.getLevel()==3)
                  show_Spalte_der_Modelle(-Sort.geti(NodeSpalte.getUserData(),0),""+NodeSpalte.getLabel(),null);
//                  DefAbfrage.InfoAbfrage(g,Sort.geti(NodeSpalte.getUserData(),0)).showGrid(""+NodeSpalte.getLabel());
              }
              else if (s.startsWith("show_Eingabe"))
              {
            	  String sVar=null;
            	  if (s.equals("show_Eingabe2"))
            	  {
	            	  Vector Vec=(Vector)OutAkt.getSelectedNode().getUserData();
	                  int iBits = Sort.geti(Vec, 5);
	                  if((iBits & Calc.VAR) > 0)
	                	  sVar=(String)Vec.elementAt(3);
            	  }
            	  else
            		  sVar=TxtEingabe.getText();
            	  if (sVar != null)
            	  {
            		  if (s.equals("show_Eingabe2"))
            		  {
	            		  int iPos=sVar.indexOf("=");
	            		  if (iPos>0)
	            			  sVar=sVar.substring(0,iPos);
	            		  g.fixtestError(s+":"+sVar);
            		  }
            		  show_Spalte_der_Modelle(0,"E="+sVar,sVar);
            	  }
              }
              else if (s.startsWith("show_var2"))
              {
            	  String sVar=TxtVar.getText();
            	  show_Spalte_der_Modelle(0,"V="+sVar,sVar);
              }
              else if (s.startsWith("show_Ges"))
              {
                Tabellenspeicher Tab=new Tabellenspeicher(g,"select var,eingabe,count(*) Anzahl,null e from (select Eingabe,var from befehl2 where (var is not null or eingabe is not null) and hide=0 and"+(s.equals("show_Gesamt") ? "": g.bit("mbits", Calc.VAR)+" and")+
                		(s.equals("show_Gesamt") ? g.in("befehl2.aic_modell",TabModell.getVecSpalte("AIC")):" mbits&0x"+Calc.VALL+">"+Calc.VLOK)+
                        ") x group by var,Eingabe order by var desc,eingabe",true);
                for (int i=0;i<Tab.size();i++)
                {
                	String sE=g.encodeMath(Tab.getS(i,"eingabe"));
                	int iP=sE.indexOf("=");
                	if (iP>=0 && !Static.Leer(Tab.getS(i,"var")))
                	{
                		int iAnz=Tab.getI(i,"Anzahl");
                		String sE2=sE.substring(iP+1);
//                		g.fixtestError("var mit E="+sE2+", Anzahl="+iAnz+", "+Tab.getS(i,"var"));
                		String sV=sE.substring(0,iP);
                		int i2=Tab.getPos("Eingabe",sV);
                		if (i2>=0 && !Static.Leer(Tab.getB(i2,"var")))
                		{
                			Tab.setInhalt(i2,"Anzahl", iAnz+Tab.getI(i2,"Anzahl"));
                			Tab.clearInhalt(i);
                			i--;
                			String sE1=Tab.getS(i2,"e");               			
                			Tab.setInhalt(i2,"e",Static.Leer(sE1) ? sE2:sE1+", "+sE2);
                		}
                		else
                		{
                			Tab.setInhalt(i, "Eingabe",sV);
                			Tab.setInhalt(i,"e",sE2);
                		}
                	}
                }
                JDialog FomGid = new JDialog((JFrame)thisFrame, "Gesamt-Variablen", false);
                AUOutliner Grid = new AUOutliner();
                FomGid.getContentPane().add("Center", Grid);
                Tab.showGrid(Grid);
                FomGid.setSize(600, 400);
                Grid.addActionListener(new JCActionListener() {
                  public void actionPerformed(JCActionEvent ev)
                  {
                    JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
                    String s=Sort.gets(Nod.getLabel(),0);
//                    TxtEingabe.setText(s);
                    show_Spalte_der_Modelle(0,"V="+s,s);
                  }
                });
                Static.centerComponent(FomGid,(JFrame)thisFrame);
                FomGid.setVisible(true);
              }
              else if (s.equals("RefreshBtn") || s.equals("Refresh") || s.equals("RefreshAll"))
              {
                bAlleSpalte=s.equals("RefreshAll");
                if (!s.equals("RefreshBtn") || !g.Prog() || (new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Spalten_erneuern")==Message.YES_OPTION))
                  fillSpalten();
              }
              else if (s.equals("Abfrage") || s.equals("Abfrage2"))
                DefAbfrageAufrufen(s.equals("Abfrage"));
              // -------------------------------------------- popup für Anweisung -------------
              else if (s.startsWith("show_Befehl"))
                showInfo(OutAnweisung.getSelectedNode(),s.equals("show_Befehl5") ? USE_JOK:s.equals("show_Befehl4") ? USE_MOD:s.equals("show_Befehl3") ? ART
                		:s.equals("show_Befehl2") ? VAR:s.equals("show_Befehl1") ? EINGABE:0,thisFrame);
              else if (s.equals("edit_Befehl"))
                edit(OutAnweisung.getSelectedNode(),false,false);
              else if (s.equals("neu_Gruppe"))
                edit(null,true,false);
              // -------------------------------------------- popup für Bedingung -------------
              else if (s.startsWith("show_Bedingung"))
                showInfo(OutBedingung.getSelectedNode(),s.equals("show_Bedingung2") ? VAR:s.equals("show_Bedingung4") ? USE_MOD:0,thisFrame);
              else if (s.equals("edit_Bedingung"))
                edit(OutBedingung.getSelectedNode(),false,true);

              // -------------------------------------------- popup für Build -----------------
              else if (s.equals("Kommentar"))
              {
                Kommentar();
                EnableButtons();
              }
              else if (s.equals("setBreakVar") || s.equals("setBreak") || s.equals("setDebug") || s.equals("setNot") || s.equals("setBreakEingabe"))
                setBreak(s.equals("setBreak") ? 1:s.equals("setDebug") ? 2:s.equals("setNot") ? 3:s.equals("setBreakVar") ? 4:s.equals("setBreakEingabe") ? 5:0);
              else if (s.equals("setBreakSpalte"))
                setBreakSpalte();
              else if (s.equals("setBreakBefehl"))
                  setBreakBefehl();
              else if (s.equals("setBreakVar2"))
                  setBreakVar();
              else if (s.equals("InfoBreakBefehl"))
              	g.fixInfo("VecBefehl="+Aufruf.VecBefehl);
              else if (s.equals("InfoBreak"))
            	  InfoBreak();
              else if (s.equals("Memo"))
              {
                Vector Vec = (Vector)OutAkt.getSelectedNode().getUserData();
                MemoF mem = OutAkt==OutBuild ? new MemoF(FomBuild, "" + OutAkt.getSelectedNode().getLabel(), g):new MemoF(DlgM, "" + OutAkt.getSelectedNode().getLabel(), g);
                mem.Txt.setText((String)Vec.elementAt(4));
                mem.show2();
                if (mem.bOk)
                {
                  //g.progInfo("ok=" + mem.bOk + " -> " + mem.Txt.getValue());
                  ((Vector)AktBuildNode.getUserData()).setElementAt(mem.Txt.getValue(),4);
                  ((Vector)AktBuildNode.getLabel()).setElementAt(mem.Txt.getValue(),9);
                }
              }
              else if (s.equals("Knoten_faerben"))
                faerben(OutAkt.getSelectedNode());
              else if (s.equals("Befehlbits"))
            	  ShowBefehlBits();
            }
          };
	  g.addMenuItem("show",popSpalte).addActionListener(AL); // anzeigen
	  g.addMenuItem("show_var2",popSpalte).addActionListener(AL); 
      g.addMenuItem("show_Eingabe",popSpalte).addActionListener(AL); //Anzeige akt. Eingabe
      g.addMenuItem("show_Gesamt",popSpalte).addActionListener(AL);
      g.addMenuItem("show_GesVarG",popSpalte).addActionListener(AL);
	  g.addMenuItem("Refresh",popSpalte).addActionListener(AL);
	  g.addMenuItem("RefreshAll",popSpalte).addActionListener(AL);
          MnuBreakSpalte = g.addMenuItem("setBreakSpalte", popSpalte);
          MnuBreakSpalte.addActionListener(AL);
      g.addMenuItem("setBreakVar", popSpalte,"setBreakVar2",AL);
      g.addMenuItem("setBreakEingabe", popSpalte,"setBreakEingabe",AL);
	  g.addMenuItem("Abfrage",popSpalte).addActionListener(AL);
	  g.addMenuItem("Abfrage2",popSpalte).addActionListener(AL);
	  popSpalte.addSeparator();
	  g.addMenuItem("Befehlbits",popSpalte).addActionListener(AL);

	  OutSpalten.getOutliner().addMouseListener(new MouseListener()
          {
            public void mousePressed(MouseEvent ev) {}

            public void mouseClicked(MouseEvent ev)
            {
              fillSpalteninfo();
              if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)  && g.Def())
        	popSpalte.show((Component)ev.getSource(), ev.getX(), ev.getY());
            }

            public void mouseEntered(MouseEvent ev) {}
            public void mouseExited(MouseEvent ev) {}
            public void mouseReleased(MouseEvent ev) {}
          });
	  OutSpalten.getOutliner().addKeyListener(new KeyListener()
  	  {
  		public void keyPressed(KeyEvent e)
  		{}
  		public void keyReleased(KeyEvent e)
  		{
  		  if (e.getKeyCode()==Global.iPopkey)
  		  {
  		    MnuClose2.setVisible(true);
                    fillSpalteninfo();
  		    popSpalte.show((Component)e.getSource(),0,0);
  		  }
  		}
  		public void keyTyped(KeyEvent e)
  		{}
  	  });

          /* ------------------------------------- Popup-Menü für Anweisung ------------------------------------------------ */
          g.addMenuItem("show",popBefehl,"show_Befehl").addActionListener(AL);
          g.addMenuItem("show_Eingabe",popBefehl,"show_Befehl1").addActionListener(AL);
          g.addMenuItem("show_var",popBefehl,"show_Befehl2").addActionListener(AL);
          g.addMenuItem("show_Abf",popBefehl,"show_Befehl3").addActionListener(AL);
          g.addMenuItem("show_Mod",popBefehl,"show_Befehl4").addActionListener(AL);
          g.addMenuItem("show_JokerStt",popBefehl,"show_Befehl5").addActionListener(AL);
          popBefehl.addSeparator();
          g.addMenuItem("Edit",popBefehl,"edit_Befehl").addActionListener(AL);
          g.addMenuItem("Neu",popBefehl,"neu_Gruppe").addActionListener(AL);
          popBefehl.addSeparator();
          g.addMenuItem("setBreakBefehl",popBefehl,"setBreakBefehl").addActionListener(AL);
          g.addMenuItem("InfoBreakBefehl",popBefehl,"InfoBreakBefehl").addActionListener(AL);
          g.addMenuItem("info_break",popBefehl,"InfoBreak").addActionListener(AL);
            OutAnweisung.getOutliner().addMouseListener(new MouseListener()
            {
              public void mousePressed(MouseEvent ev) {}

              public void mouseClicked(MouseEvent ev)
              {
                if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)  && g.Def())
                  popBefehl.show((Component)ev.getSource(), ev.getX(), ev.getY());
              }

              public void mouseEntered(MouseEvent ev) {}
              public void mouseExited(MouseEvent ev) {}
              public void mouseReleased(MouseEvent ev) {}
            });
          /* ------------------------------------- Popup-Menü für Anweisung ------------------------------------------------ */
          g.addMenuItem("show",popBedingung,"show_Bedingung").addActionListener(AL);
          g.addMenuItem("show_var",popBedingung,"show_Bedingung2").addActionListener(AL);
          g.addMenuItem("show_Mod",popBedingung,"show_Bedingung4").addActionListener(AL);
          g.addMenuItem("Edit",popBedingung,"edit_Bedingung").addActionListener(AL);
          OutBedingung.getOutliner().addMouseListener(new MouseListener()
            {
              public void mousePressed(MouseEvent ev) {}

              public void mouseClicked(MouseEvent ev)
              {
                if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)  && g.Def())
                  popBedingung.show((Component)ev.getSource(), ev.getX(), ev.getY());
              }

              public void mouseEntered(MouseEvent ev) {}
              public void mouseExited(MouseEvent ev) {}
              public void mouseReleased(MouseEvent ev) {}
            });

	  /* ------------------------------------- Popup-Menü für Modell ------------------------------------------------ */

	  MnuClose1 = g.addMenuItem("Close", popModell,"Close1");
          MnuClose1.addActionListener(AL);
          MnuClose1.setVisible(false);

	  g.addMenuItem("Bearbeiten Modell",popModell).addActionListener(AL);
	  g.addMenuItem("Zeige Modell",popModell).addActionListener(AL);
          g.addMenuItem("Aufruf",popModell).addActionListener(AL);
	  g.addMenuItem("Info",popModell,"Info Modell").addActionListener(AL);	  
          g.addMenuItem("DefVerlauf",popModell).addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              if (((JCOutlinerFolderNode)OutModell.getSelectedNode()).getLevel()>0)
              {
                int iAIC_Begriff = ((Integer)((Vector)OutModell.getSelectedNode().getUserData()).elementAt(4)).intValue();
                int iAIC_Modell = Sort.geti(OutModell.getSelectedNode().getUserData(), 0);
                final Tabellenspeicher Tab=new Tabellenspeicher(g, "select aic_defverlauf,tat,timestamp,(select benutzer.kennung from benutzer join logging l on benutzer.aic_benutzer=l.aic_benutzer where l.aic_logging=v.aic_logging) Benutzer" +
                                     ",(select count(*) from befehl where aic_defverlauf=v.aic_defverlauf) Zeilen,(select count(*) from befehl where aic_defverlauf=v.aic_defverlauf and hide=1) hide"+
                                     ",(select count(*) from befehl where aic_defverlauf=v.aic_defverlauf and"+g.bit("MBits", Calc.CHANGE)+") changed from defverlauf v where aic_begriff=" + iAIC_Begriff+" order by timestamp desc", true);
                int iAnzN=SQL.getInteger(g, "select count(*) from befehl where aic_defVerlauf is null and aic_modell="+iAIC_Modell);
                if (iAnzN>0)
                {
                  int iPos=Tab.newLine();
                  Tab.setInhalt(iPos, "aic_defverlauf", 0);
                  Tab.setInhalt(iPos, "Zeilen", iAnzN);
                  Tab.setInhalt(iPos,"timestamp",SQL.getTimestamp(g,"select importzeit from begriff where aic_begriff="+iAIC_Begriff));
                }
                final JDialog FomGid = new JDialog((Frame)thisFrame, "DefVerlauf", false);
  	      	final AUOutliner Grid = new AUOutliner();
  	      	FomGid.getContentPane().add("Center", Grid);
  	      	JPanel PnlSouth=new JPanel(new GridLayout());
                JButton BtnExport=g.getButton("Export");
  	      	PnlSouth.add(BtnExport);
                BtnExport.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      JCOutlinerNode Nod = Grid.getSelectedNode();
                      ZeigeModell(Sort.geti(Nod.getLabel(),0),false);
                    }
	          });
  	      	JButton BtnUnDel=g.getButton("Undelete");
  	      	PnlSouth.add(BtnUnDel);
  	      	BtnUnDel.addActionListener(new ActionListener()
	          {
	            public void actionPerformed(ActionEvent ev)
	            {
	              JCOutlinerNode Nod = Grid.getSelectedNode();
	    	        int i=Sort.geti(Nod.getLabel(),0);
                int iZ=Sort.geti(Nod.getLabel(),4);
	    	        if (iZ>0)
	    	        {
                          int iAIC_Begriff = Sort.geti(OutModell.getSelectedNode().getUserData(), 4);
                          int iAIC_Modell = Sort.geti(OutModell.getSelectedNode().getUserData(), 0);
                          int iAic = g.SaveDefVerlauf(iAIC_Begriff, "undelete");
                          g.exec("update befehl set def_aic_defverlauf=" + iAic + " where def_aic_defverlauf is null and aic_modell=" + iAIC_Modell);
                          g.exec("update befehl set aic_defverlauf=" + iAic + ",def_aic_defverlauf=null where aic_defverlauf"+(i==0 ? " is null":"=" + i)+" and aic_modell=" + iAIC_Modell);
                          Tab.refresh(g);
                          Tab.showGrid(Grid);
	    	        }
                else
                  g.fixtestError("nichts zum wiederherstellen, da keine Zeile vorhanden");
	            }
	          });
  	      	JButton BtnDel=g.getButton("Loeschen");
  	        PnlSouth.add(BtnDel);
  	      	BtnDel.addActionListener(new ActionListener()
	          {
	            public void actionPerformed(ActionEvent ev)
	            {
	              JCOutlinerNode Nod = Grid.getSelectedNode();
	    	      int i=Sort.geti(Nod.getLabel(),0);
                      if (i==Tab.getI(0,"aic_defverlauf"))
                        g.testInfo("erstes nicht löschbar");
                      else if (i > 0)
                        {
                          if (Sort.geti(Nod.getLabel(),4)==0)
                            g.testInfo("nichts zum löschen");
                          else
                          {
                            g.exec("delete from befehl where aic_defverlauf=" + i);
                            Tab.refresh(g);
                            Tab.showGrid(Grid);
                          }
                        }
                      else
                        g.testInfo("nichts angewählt");
	            }
	          });
  	      	JButton BtnEnd=g.getButton("Beenden");
  	      	PnlSouth.add(BtnEnd);
  	      	BtnEnd.addActionListener(new ActionListener()
  	          {
  	            public void actionPerformed(ActionEvent ev)
  	            {
  	              FomGid.dispose();
  	            }
  	          });
  	      	FomGid.getContentPane().add("South",PnlSouth);
  	      	Tab.showGrid(Grid);
  	      	FomGid.setSize(600, 300);
  	      	Static.centerComponent(FomGid,thisFrame);
  	      	Grid.addActionListener(new JCActionListener() {
    	         public void actionPerformed(JCActionEvent ev) {
    	          JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
    	          //int i=Sort.geti(Nod.getLabel(),0);
//    	          g.fixtestError("ZeigeModell aus History:"+Sort.geti(Nod.getLabel(),0));
    	          ZeigeModell(Sort.geti(Nod.getLabel(),0),true);
    	          //Tabellenspeicher Tab = new Tabellenspeicher(g,"select defbezeichnung,kennung,b.aic_begriff from begriff b join Modell m on b.aic_begriff=m.aic_begriff where "+g.bit("b.bits",l),true);
    	          //Tab.showGrid("Modelle mit bit " + i, FomGid);
    	         }
    	        });
  	      	//Tab.refresh();
  	      	FomGid.setVisible(true);	        
              }
            }
          });
          g.addMenuItem("Modellbits",popModell).addActionListener(AL);
          popModell.addSeparator();
          g.addMenuItem("Edit",popModell).addActionListener(AL);
          g.addMenuItem("Kopie",popModell).addActionListener(AL);
          MnuNeu=g.addMenuItem("Neu",popModell);
          MnuNeu.addActionListener(AL);
          MnuDel=g.addMenuItem("Loeschen",popModell);
          MnuDel.addActionListener(AL);
	  popModell.addSeparator();
	    g.addMenuItem("alle Modelle",popModell).addActionListener(AL);
	    g.addMenuItem("nur Web-Modelle",popModell).addActionListener(AL);
	    g.addMenuItem("selektierte Modelle",popModell).addActionListener(AL);
          g.addMenuItem("Modelle in Module",popModell).addActionListener(AL);
	  /*g.addMenuItem("Hierarchie",popModell).addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              iModellF=-1;
              fillOutModell();
            }
          });*/
	  g.addMenuItem("nur Submodelle",popModell).addActionListener(AL);
          g.addMenuItem("Submodellehierarchie",popModell).addActionListener(AL);
          g.addMenuItem("nur Tod",popModell).addActionListener(AL);
	  popModell.addSeparator();
          JMenu MnuTab = new JMenu("Tabellenspeicher");
          popModell.add(MnuTab);
          addPopupForTab(MnuTab, "Modell");
          addPopupForTab(MnuTab, "Code");
          addPopupForTab(MnuTab, "Spalten");

	  OutModell.getOutliner().addMouseListener(new MouseListener()
          {
            public void mousePressed(MouseEvent ev)
            {}

            public void mouseClicked(MouseEvent ev)
            {
              //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
              if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM) && g.Def())
        	popModell.show((Component)ev.getSource(), ev.getX(), ev.getY());
              /*else if (ev.getModifiers() == MouseEvent.BUTTON1_MASK && ev.getClickCount() == 2)
              {
                if(!CbxSchliessbar.isSelected())
		{
			if(!bGeaendert)
				Laden((JCOutlinerFolderNode)OutModell.getSelectedNode(),false);
			//ev.setAllowChange(false);
		}
              }*/
            }

            public void mouseEntered(MouseEvent ev)
            {}

            public void mouseExited(MouseEvent ev)
            {}

            public void mouseReleased(MouseEvent ev)
            {}
          });
	  OutModell.getOutliner().addKeyListener(new KeyListener()
  	  {
  		public void keyPressed(KeyEvent e)
  		{}
  		public void keyReleased(KeyEvent e)
  		{
  		  if (e.getKeyCode()==Global.iPopkey)
  		  {
  		    MnuClose1.setVisible(true);
  		    popModell.show((Component)e.getSource(),0,0);
  		  }
  		}
  		public void keyTyped(KeyEvent e)
  		{}
  	  });

          /* ------------------------------------- Popup-Menü für Build ------------------------------------------------ */

          MnuClose3 = g.addMenuItem("Close", popBuild);
          MnuClose3.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              popBuild.setVisible(false);
              MnuClose3.setVisible(false);
            }
          });
          MnuClose3.setVisible(false);
          g.addMenuItem("show_Eingabe",popBuild,"show_Eingabe2").addActionListener(AL);
          ActionListener ALC=new ActionListener()
          {
              public void actionPerformed(ActionEvent ev)
              {
                AktBuildNode = OutAkt.getSelectedNode();
                String s = ev.getActionCommand();
                if (s.equals("SyncBefehl") || s.equals("SyncModell"))
                	SynchronBefehl(AktBuildNode,s.equals("SyncModell"));
                else if (s.equals("SyncSpalte")) 
                	SynchronSpalte(AktBuildNode);
                else if (s.equals("SyncVar")) 
                	SynchronVar(AktBuildNode);
                else if (s.equals("SyncBits")) 
                	SynchronBits(AktBuildNode);
              }
            };
          g.addMenuItem("Checkbox","SyncBefehl",popBuild,null,ALC);
          g.addMenuItem("Checkbox","SyncSpalte",popBuild,null,ALC);
          g.addMenuItem("Checkbox","SyncVar",popBuild,null,ALC);
          g.addMenuItem("Checkbox","SyncBits",popBuild,null,ALC);
          g.addMenuItem("Checkbox","SyncModell",popBuild,null,ALC);
          MnuKommentar = g.addMenuItem("Kommentar", popBuild);
          MnuKommentar.addActionListener(AL);
          MnuBreak = g.addMenuItem("setBreak", popBuild);
          MnuBreak.addActionListener(AL);
          MnuBreakVar = g.addMenuItem("setBreakVar", popBuild);
          MnuBreakVar.addActionListener(AL);
          MnuBreakEingabe = g.addMenuItem("setBreakEingabe", popBuild);
          MnuBreakEingabe.addActionListener(AL);
          MnuDebug = g.addMenuItem("setDebug", popBuild);
          MnuDebug.addActionListener(AL);
          MnuNothing = g.addMenuItem("setNot", popBuild);
          MnuNothing.addActionListener(AL);
          g.addMenuItem("Memo", popBuild).addActionListener(AL);
          g.addMenuItem("Knoten_faerben", popBuild).addActionListener(AL);

          OutBuild.getOutliner().addMouseListener(new MouseListener()
          {
            public void mousePressed(MouseEvent ev) {}
            public void mouseClicked(MouseEvent ev)
            {
                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                {
                	OutAkt=OutBuild;
                	popBuild.show((Component)ev.getSource(), ev.getX(), ev.getY());
                }
            }
            public void mouseEntered(MouseEvent ev) {}
            public void mouseExited(MouseEvent ev)  {}
            public void mouseReleased(MouseEvent ev){}
          });
          OutBuild.getOutliner().addKeyListener(new KeyListener()
          {
                public void keyPressed(KeyEvent e)
                {}
                public void keyReleased(KeyEvent e)
                {
                  if (e.getKeyCode()==Global.iPopkey)
                  {
                    MnuClose3.setVisible(true);
                    OutAkt=OutBuild;
                    popBuild.show((Component)e.getSource(),0,0);
                  }
                }
                public void keyTyped(KeyEvent e)
                {}
          });
        }

        private void fillGruppe()
        {
          CboAnwGruppe.fillCbo("SELECT aic_BEGRIFFGRUPPE,kennung" + g.AU_Bezeichnung("BEGRIFFGRUPPE") + " FROM BEGRIFFGRUPPE where anweisung=1", "BEGRIFFGRUPPE", false, false);
        }

        @SuppressWarnings("unchecked")
        private void edit(JCOutlinerNode Nod,boolean bGruppe,boolean bBedingung)
        {
          if (DlgParameter==null)
          {
            DlgParameter = new JDialog((JFrame)thisFrame, true);

            JPanel PnlEingabe = new JPanel(new BorderLayout());
            JPanel PnlLabel = new JPanel(new GridLayout(0, 1, 2, 2));
            JPanel PnlEdit = new JPanel(new GridLayout(0, 1, 2, 2));
            g.addLabel(PnlLabel, " " + g.getBegriffS("Show", "Bezeichnung"));
            TxtAnwBezeichnung = new Text("", 100);
            PnlEdit.add(TxtAnwBezeichnung);
            g.addLabel(PnlLabel, " " + g.getBegriffS("Show", "Kennung"));
            TxtAnwKennung = new Text("", 40, Text.KENNUNG);
            PnlEdit.add(TxtAnwKennung);
            CboAnwGruppe=new ComboSort(g);
            fillGruppe();
            g.addLabel(PnlLabel, " " + g.getBegriffS("Show", "Anweisungsgruppe"));
            PnlEdit.add(CboAnwGruppe);
            PnlEingabe.add("Center", PnlEdit);
            PnlEingabe.add("West", PnlLabel);
            JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,3,2));
             ActionListener ALP=new ActionListener()
             {
               public void actionPerformed(ActionEvent ev)
               {
                 String s=((JButton)ev.getSource()).getActionCommand();
                 if (s.equals("ok"))
                 {
                   if (CboAnwGruppe.isVisible() && !CboAnwGruppe.isEnabled()) // Begriffgruppe ändern
                   {
                     int iBG=NodAnw==null ? 0:Sort.geti(NodAnw.getUserData());
                     if (iBG==0)
                     {
                       String sKennung = TxtAnwKennung.getText();
                       if (sKennung.equals(""))
                       {
                         Message.showWarnung(g, (JFrame)thisFrame, "KennungLeer");
                         return;
                       }
                       else if (SQL.exists(g, "select aic_begriffgruppe from Begriffgruppe WHERE Kennung='" + sKennung + "'"))
                       {
                         Message.showWarnung(g, (JFrame)thisFrame, "KennungVorhanden");
                         return;
                       }
                       else
                       {
                         SQL Qry = new SQL(g);
                         Qry.add("Kennung", sKennung);
                         Qry.add("Code", 1);
                         Qry.add("Anweisung", 1);
                         iBG = Qry.insert("BEGRIFFGRUPPE", true);
                       }
                     }
                     int iTabBG = g.TabTabellenname.getAic("BEGRIFFGRUPPE");
                     if (TxtAnwBezeichnung.Modified())
                       g.setBezeichnung(TxtAnwBezeichnung.getOld(), TxtAnwBezeichnung.getText(), iTabBG, iBG, 0);
                     fillGruppe();
                     g.fillTabBegriffgruppen();
                     if (MemoAnw.Modified())
                       g.setMemo(MemoAnw.getValue(), "", iTabBG, iBG, 0);
                   }
                   else  // Code ändern
                   {
                	 String sDV=(CboAnwGruppe.isVisible()?"Befehl":"Bedingung")+" "+TxtAnwKennung.getText()+":";                    
                     Vector VecUD = (Vector)NodAnw.getUserData();
                     int iAic = Sort.geti(VecUD, 0);
                     //g.progInfo("Aic="+iAic);
                     int iTabCode = g.TabTabellenname.getAic("CODE");
                     if (TxtAnwBezeichnung.Modified())
                     {
                       //g.progInfo("Bezeichnung="+TxtAnwBezeichnung.getText());
                       g.setBezeichnung(TxtAnwBezeichnung.getOld(), TxtAnwBezeichnung.getText(), iTabCode, iAic, 0);
                       ((Vector)NodAnw.getLabel()).setElementAt(TxtAnwBezeichnung.getText(), 0);
                       sDV+="Bez ";
                     }
                     //TxtBezeichnung.setText(TxtBezeichnung.getText());
                     if (MemoAnw.Modified())
                     {
                       //g.progInfo("Memo="+MemoAnw.getValue());
                       g.setMemo(MemoAnw.getValue(), "", iTabCode, iAic, 0);
                       VecUD.setElementAt(MemoAnw.getValue(), 1);
                       TxtMemoModell.setText(MemoAnw.getValue());
                       sDV+="Memo ";
                     }
                     if (CboAnwGruppe.isVisible() && CboAnwGruppe.Modified())
                     {
                       g.exec("update code set aic_begriffgruppe=" + CboAnwGruppe.getSelectedAIC() + " where aic_code=" + iAic);
                       sDV+="Gruppe ";
                       fillBefehle(iAic);
                     }
                     g.SaveDefVerlauf(getBegriff(), sDV+"geändert");
                   }
                 }
                 DlgParameter.setVisible(false);
               }
             };
            JButton BtnOk = g.getButton("Ok", "ok", ALP);
            JButton BtnAbbruch = g.getButton("Abbruch", "cancel", ALP);
            PnlS.add(BtnOk);
            PnlS.add(BtnAbbruch);
            DlgParameter.getContentPane().add("North", PnlEingabe);
            DlgParameter.getContentPane().add("Center", MemoAnw);
            DlgParameter.getContentPane().add("South", PnlS);
            DlgParameter.setSize(400, 400);
            Static.centerComponent(DlgParameter, thisFrame);
          }
          if (bGruppe)
          {
            TxtAnwBezeichnung.setText("");
            TxtAnwKennung.setText("");
            TxtAnwKennung.setEditable(true);
            MemoAnw.setText("");
            CboAnwGruppe.setVisible(true);
            CboAnwGruppe.setEnabled(false);
            NodAnw = null;
            DlgParameter.setTitle("neue Gruppe");
            DlgParameter.setVisible(true);
          }
          else if (Nod.getLevel()>0)
          {
            TxtAnwBezeichnung.setText(Sort.gets(Nod.getLabel(), 0));
            TxtAnwKennung.setText(Sort.gets(Nod.getLabel(), 1));
            TxtAnwKennung.setEditable(false);
            if (Nod.getLevel()>1)
            {
              MemoAnw.setText(Sort.gets(Nod.getUserData(), 1));
              //g.progInfo("direkt:"+Nod.getUserData());
              //g.progInfo("darüber:"+Nod.getParent().getUserData());
              CboAnwGruppe.setVisible(!bBedingung);
              if (!bBedingung)
              {
                CboAnwGruppe.setEnabled(true);
                CboAnwGruppe.setSelectedAIC(Sort.geti(Nod.getParent().getUserData()));
              }
            }
            else
            {
              MemoAnw.setText(SQL.getString(g,"select Memo FROM Daten_Memo WHERE AIC_TABELLENNAME="+g.TabTabellenname.getAic("BEGRIFFGRUPPE")+" and aic_sprache="+g.getSprache()+" and aic_fremd=?",Sort.gets(Nod.getUserData())));
              CboAnwGruppe.setVisible(true);
              CboAnwGruppe.setEnabled(false);
            }
            NodAnw = Nod;
            DlgParameter.setTitle((Nod.getLevel()==2 ?bBedingung?"Bedingung":"Befehl":"Gruppe")+" bearbeiten");
            DlgParameter.setVisible(true);
          }
        }

        private void faerben(JCOutlinerNode Nod)
        {
          JCOutlinerNodeStyle StyNode = new JCOutlinerNodeStyle(Nod.getStyle()==null?(new JCOutlinerComponent()).getDefaultNodeStyle():Nod.getStyle());
          StyNode.setForeground(Color.BLUE);
          Nod.setStyle(StyNode);
          Vector VecNodes =null;
          if (Nod instanceof JCOutlinerFolderNode)
            VecNodes=Nod.getChildren();
          if (VecNodes != null && VecNodes.size()>0)
            for(int i=0;i<VecNodes.size();i++)
              faerben((JCOutlinerNode)VecNodes.elementAt(i));
          OutBuild.folderChanged(Nod);
        }

        private void fillSpalteninfo()
        {
          Object Obj=OutSpalten.getSelectedNode().getLabel();
          if (Obj instanceof Vector)
          {
            String[] ObjT=OutSpalten.getColumnLabels();
            MnuSpaltenInfo.setVisible(true);
            MnuSpaltenInfo.removeAll();
            for (int i = 0; i < ((Vector)Obj).size(); i++)
              if (((Vector)Obj).elementAt(i) != null)
                MnuSpaltenInfo.add(ObjT[i]+"="+Sort.gets(Obj, i));
          }
          else
            MnuSpaltenInfo.setVisible(false);
        }
        
    public static boolean Zeige(int iBefehl,JDialog Dlg)
    {
//    	Self.g.fixtestError("Zeige "+iBefehl+" auf "+Dlg);
    	boolean b=Self!=null && iBefehl>0;
    	if (b)
    		Self.ZeigeModell(-iBefehl,true,Dlg);
    	return b;
    }
    
    private void ZeigeModell(int iDef,boolean bShow)
    {
    	ZeigeModell(iDef,bShow,null);
    }
    
    public static void freeDlg()
    {
    	if (Self==null)
    		return;
//    	Self.g.fixtestError("DefModell.freeDlg: "+Self.iDM+"/"+Self.DlgM);
    	Self.OutM=null;
    	if (Self.DlgM != null)
    	{
    		
    		Self.DlgM.dispose();
    	}
    	Self.DlgM=null;
    }
    
    public static void setDlgM()
    {
    	if (Self != null && Self.iDM>0 && Self.DlgM != null)
			Self.g.setFenster(Self.DlgM,Self.iDM,-1);
    }

	private void ZeigeModell(int iDef,boolean bShow,JDialog Dlg)
	{
	  JCOutlinerFolderNode Nod=(JCOutlinerFolderNode)OutModell.getSelectedNode();
	  
	  AUOutliner Out = null;
	  if (OutM==null || Dlg==null)
	  {
		  Out=new AUOutliner(new JCOutlinerFolderNode(""));
	      Out.setCopy(true);
		  //String[] s = new String[] {g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Spalte"),g.getBegriffS("Show","Nr"),g.getBegriffS("Show","Memo")};
	          //String[] s = new String[] {g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Eingabe"),g.getBegriffS("Show","Stt-Bew"),g.getBegriffS("Show","Abfrage"),g.getBegriffS("Show","Spalte"),g.getBegriffS("Show","Nr"),g.getBegriffS("Show","Memo")};
		  Out.setBackground(Color.white);
		  Out.setRootVisible(false);
		  Out.setColumnLabelSort(false);
		  Out.setColumnButtons(sBuild);
		  Out.setNumColumns(sBuild.length);
	      Out.setColumnAlignment(6,BWTEnum.TOPRIGHT);
	      Out.setFont(Global.fontModell);
	      Out.setAllowMultipleSelections(true);
	      Out.setBackground(Global.ColHS);
	      for (int i=0;i<10;i++)
	        Out.setColumnWidth(i,OutBuild.getColumnWidth(i));
	      Zusammen(Out);
	      if (Dlg != null)
	      {
	    	  OutM=Out;
	    	  Out.getOutliner().addMouseListener(new MouseListener()
	          {
	              public void mousePressed(MouseEvent ev) {}
	              public void mouseClicked(MouseEvent ev)
	              {
	                  if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
	                  {
	                	  OutAkt=OutM;
	                	  popBuild.show((Component)ev.getSource(), ev.getX(), ev.getY());
	                  }
	              }
	              public void mouseEntered(MouseEvent ev) {}
	              public void mouseExited(MouseEvent ev)  {}
	              public void mouseReleased(MouseEvent ev){}
	            });
	    	  
//	    	  g.addMenuItem("show_Eingabe",popSpalte).addActionListener(AL);
	      }
	  }
	  else
	  {
		  Out=OutM;
		  ((JCOutlinerFolderNode)Out.getRootNode()).removeChildren();
	  }
          Laden(Nod, false, Out, iDef);
          if (Dlg != null)
          {
        	  if (DlgM==null)
        	  {
	        	  DlgM=new JDialog(Dlg,"Modell " + Sort.gets(Nod.getLabel()) + (iDef > 0 ? " / " + iDef : ""));
	        	  DlgM.getContentPane().add("Center", Out);
	        	  DlgM.pack();
	        	  iDM=g.getBegriffAicS("Dialog","Modell");
	        	  if (iDM>0)
	        		  g.getFenster(DlgM, iDM, false, 0, 0, 800, 600, g.getFontFaktor());
        	  }
//        	  else
//        		  DlgM.getContentPane().add("Center", Out);
        	  DlgM.setVisible(true);
          }
          else if (bShow)
          {
            JFrame Fom = new JFrame("Modell " + Sort.gets(Nod.getLabel()) + (iDef > 0 ? " / " + iDef : ""));
            Fom.getContentPane().add("Center", Out);
            Fom.pack();
            Fom.setVisible(true);
            //g.fixtestError("vor makeVisible");
//            g.fixtestError("NodSel3="+NodSel.getLabel());
//	        Static.makeVisible(Out,NodSel);
          }
          else
          {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showSaveDialog(null);
            if(option == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile()!=null)
            try
            {
              String sFileName=chooser.getSelectedFile().getPath();
              java.io.FileOutputStream fos=new java.io.FileOutputStream(sFileName,false);
              String[] s2 = Out.getTree();
              for(int i = 0; s2.length > i; i++)
                fos.write((s2[i]+"\r\n").getBytes(Static.CP));
              fos.close();
            }
            catch (java.io.IOException ex)
            {
                    Static.printError("DefModell.ZeigeModell: IOException - "+ex);
            }

          }
	}

	private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
	  {
	    Tab2.addInhalt("Nr",i);
	    Tab2.addInhalt("Kennung",sConst);
	    Tab2.addInhalt("Bezeichnung",sBez==null?"":i<0 ? g.getShow(sBez):g.getBegriffS("Checkbox",sBez));
	    Tab2.addInhalt("Anzahl",SQL.getInteger(g,i==-13 ? "select count(*) from daten_bild join modell on aic_tabellenname="+Global.iTabBegriff+" and aic_fremd=modell.aic_begriff"
	    		:"select count(*) from begriff b join Modell m on b.aic_begriff=m.aic_begriff where "+(i<0 ? sConst+" is not null":g.bit("b.bits",l))));
	  }
	
	private void addBBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
	  {
	    Tab2.addInhalt("Nr",i);
	    Tab2.addInhalt("Sub",0);
	    Tab2.addInhalt("Kennung",sConst);
	    Tab2.addInhalt("Bezeichnung",sBez==null?"":i<0 ? g.getShow(sBez):g.getBegriffS("Checkbox",sBez));
	    Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from befehl2 where"+g.bit("mbits",l)));
	  }
	
	private void addBBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,long l2,int i,int iSub)
    {
	  int iAnz=SQL.getInteger(g,"select count(*) from befehl2 where"+g.bits("mbits",l)+"="+l2);
	  if (iAnz>0)
	  {
	      Tab2.addInhalt("Nr",i);
	      Tab2.addInhalt("Sub",iSub);
	      Tab2.addInhalt("Kennung",sConst);
	      Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Radiobutton",sBez));
	      Tab2.addInhalt("Anzahl",iAnz);
	  }
    }
	
	private void ShowBefehlBits()
	{
//		g.fixtestError("ShowBefehlBits");
		Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr","Sub","Kennung","Bezeichnung","Anzahl"});
//		addBBit(Tab2, "SCHLEIFE","Schleife", Calc.SCHLEIFE, 0);
		//Refresh
		addBBit(Tab2, "IMMER","immer",Calc.REFRESH,Calc.IMMER,1,2);
		addBBit(Tab2, "NIE","nie",Calc.REFRESH,Calc.NIE,1,3);
		addBBit(Tab2, "del:Refresh4",null,Calc.REFRESH,Calc.REFRESH,1,4);
		//TODO Orig.
		//concat
		addBBit(Tab2, "CONCAT","Concat",Calc.C_ART,Calc.CONCAT,22,2);
		addBBit(Tab2, "CWS","CWS",Calc.C_ART,Calc.CWS,22,3);
		addBBit(Tab2, "INIT","Minit",Calc.C_ART,Calc.INIT,22,4);
		//add/sub
		addBBit(Tab2, "ADD","add",Calc.M_ART,Calc.ADD,24,2);
		addBBit(Tab2, "SUB","sub",Calc.M_ART,Calc.SUB,24,3);
		addBBit(Tab2, "MINIT","Minit",Calc.M_ART,Calc.MINIT,24,4);
		//Bits
		addBBit(Tab2, "GRUPPE","nimm_Gruppe", Calc.GRUPPE, 8);
		addBBit(Tab2, "SAVE","BSave", Calc.SAVE, 9);
		addBBit(Tab2, "SPALTE","auf_Spalte", Calc.SPALTE, 10);
		addBBit(Tab2, "REG","auf_Register", Calc.REG, 11);
		addBBit(Tab2, "ALLE","alle_Zeilen", Calc.ALLE, 14);
		addBBit(Tab2, "THREAD","Thread", Calc.THREAD, 15);
		addBBit(Tab2, "SUM","Summe", Calc.SUM, 18);
		// Abfrageart
		addBBit(Tab2, "EMPTY","Empty",Calc.ART,Calc.EMPTY,4,2);
		addBBit(Tab2, "del:SYNC",null,Calc.ART,0x20,4,3);
		addBBit(Tab2, "USE_VEC","Vec",Calc.ART,Calc.USE_VEC,4,4);
		addBBit(Tab2, "VEC_BEW","VecBew",Calc.ART,Calc.VEC_BEW,4,5);
		addBBit(Tab2, "del:Art6",null,Calc.ART,0x50,4,6);
		addBBit(Tab2, "del:Art7",null,Calc.ART,0x60,4,7);
		addBBit(Tab2, "del:Art8",null,Calc.ART,0x70,4,8);
		// Abfrage-Zeitraum
		addBBit(Tab2, "KSZR","kein Stamm-Zeitraum", Calc.KSZR, 16);
		addBBit(Tab2, "KBZR","kein Bew-Zeitraum", Calc.KBZR, 17);
		// Variable
		addBBit(Tab2, "VAR","var", Calc.VAR, 3);
		addBBit(Tab2, "VICH","ich",Calc.VART,Calc.VICH,20,2);
		addBBit(Tab2, "V_R","read",Calc.VART,Calc.V_R,20,3);
		addBBit(Tab2, "VALL","alle",Calc.VART,Calc.VALL,20,4);
		addBBit(Tab2, "PERM","permanent", Calc.PERM, 19);
		
		final JDialog FomGid = new JDialog(FomSpalten.thisJFrame(), "Befehlbits", false);
	      AUOutliner Grid = new AUOutliner();
	      FomGid.getContentPane().add("Center", Grid);
	      Tab2.showGrid(Grid);
	      FomGid.setSize(420, 650);
	      Static.centerComponent(FomGid,FomSpalten.thisFrame);
	      
	      Grid.addActionListener(new JCActionListener() {
		        public void actionPerformed(JCActionEvent ev) {
		          JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
		          int i=Sort.geti(Nod.getLabel(),0);
//		          int i2=Sort.geti(Nod.getLabel(),1);
//		          long l=(long)Math.pow(2,i);
//		          Tabellenspeicher Tab = new Tabellenspeicher(g,"select * from befehl2 where"+g.bit("mbits",l),true);
//		          Tab.showGrid("Befehle mit bit " + i, FomGid);
		          //TODO Modelle mit Befehlsbit auflisten
		          showInfo(Nod,-i,FomGid);
		        }
	      });
	      FomGid.setVisible(true);
	}

	private void ShowModellBits()
	  {
	      Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr", "Kennung","Bezeichnung","Anzahl"});
	      addBit(Tab2, "Bilder","Bilder", 0, -13);
	      addBit(Tab2, "AIC_Bewegungstyp","Bewegungstyp", 0, -12);
	      addBit(Tab2, "AIC_Stammtyp","Stammtyp", 0, -11);
	      addBit(Tab2, "AIC_Stamm","Periode", 0, -10);
	      addBit(Tab2, "Homepage","Farbe2", 0, -9);
	      addBit(Tab2, "Bildname","Bild", 0, -8);
	      addBit(Tab2, "Max_B","maxBefehle", 0, -7);
	      addBit(Tab2, "Farbe","Farbe", 0, -6);
	      addBit(Tab2, "ALIAS","Alias", 0, -5);
	      addBit(Tab2, "AIC_ABFRAGE","Abfrage", 0, -4);
	      addBit(Tab2, "AIC_EIGENSCHAFT","Ampel", 0, -3);
	      addBit(Tab2, "KENNZEICHEN","KennzeichenM", 0, -2);
	      addBit(Tab2, "WEB","Web", 0, -1);
	      addBit(Tab2, "cstPeriodeM","Periode", Global.cstPeriodeM, 0);
	      addBit(Tab2, "cstEF","EF", Global.cstEF, 1);
	      addBit(Tab2, "cstZRhold","Zeitraum bleibt", Global.cstZRhold, 2);
	      addBit(Tab2, "cstMenge","Menge", Global.cstMenge, 3);
	      addBit(Tab2, "cstKeineFrage","keine Frage", Global.cstKeineFrage, 4);
	      addBit(Tab2, "cstRefreshM","Refresh", Global.cstRefreshM, 5);
	      addBit(Tab2, "cstOhneStamm","kein_Stammsatz", Global.cstOhneStamm, 6);
	      addBit(Tab2, "cstRecalc","Recalc", Global.cstRecalc, 7);
	      addBit(Tab2, "cstAServer","AServer", Global.cstAServer, 8);
	      //addBit(Tab2, "cstAntwort","Antwort", 0x0200, 9);
	      addBit(Tab2, "cstNurTest","nur Test", Global.cstNurTest, 9);
          addBit(Tab2, "cstKnopf","Knopf", Global.cstKnopf, 10);
	      addBit(Tab2, "cstSave","Save", Global.cstSave, 11);
	      addBit(Tab2, "del:cstnachSave","nach_Save", 0x1000/*g.cstnachSave*/, 12);
	      addBit(Tab2, "cstThread","Thread", Global.cstThread, 13);
	      addBit(Tab2, "cstBew","Bew3", Global.cstBew, 14);
	      addBit(Tab2, "cstCommit","commit", Global.cstCommit, 15);
	      addBit(Tab2, "cstAbbruch","Abbruch", Global.cstAbbruch, 16);
          addBit(Tab2, "cstUseQry","use Qry", Global.cstUseQry, 17);
          addBit(Tab2, "cstMitSo","mit So", Global.cstMitSo, 18);
          addBit(Tab2, "cstJokerStt","Joker gekapselt", Global.cstJokerStt, 19);
          addBit(Tab2, "cstErgebnis","Ergebnisknopf", Global.cstErgebnis, 20);
          addBit(Tab2, "cstMassExport","MassExport", Global.cstMassExport, 21);
          addBit(Tab2, "cstDruckZR","DruckZR", Global.cstDruckZR, 22);
          addBit(Tab2, "cstDelJS","DelJS", Global.cstDelJS, 23);
          addBit(Tab2, "cstNbAServer","nicht bei AServer", Global.cstNbAServer, 24);
          addBit(Tab2, "cstDefImport","bei_DefImport", Global.cstDefImport, 25);
          addBit(Tab2, "cstMDialog","ModellDialog", Global.cstMDialog, 26);
          addBit(Tab2, "cstEigenG","eigener ZR", Global.cstEigenG, 27);
          addBit(Tab2, "cstAmpel","Ampel", Global.cstAmpel, 28);
          addBit(Tab2, "cstClean","Reinigung", Global.cstClean, 29);
          addBit(Tab2, "cstMSperre","Sperre3", Global.cstMSperre, 30);
          addBit(Tab2, "cstAmpelWeb","AmpelWeb", Global.cstAmpelWeb, 31);
          addBit(Tab2, "cstWeiter","weiterM", Global.cstWeiter, 32);

	      final JDialog FomGid = new JDialog((Frame)thisFrame, "Modellbits", false);
	      AUOutliner Grid = new AUOutliner();
	      FomGid.getContentPane().add("Center", Grid);
	      Tab2.showGrid(Grid);
	      FomGid.setSize(500, 600);
	      Static.centerComponent(FomGid,thisFrame);
	      Grid.addActionListener(new JCActionListener() {
	        public void actionPerformed(JCActionEvent ev) {
	          JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
	          int i=Tabellenspeicher.geti(((Vector)Nod.getLabel()).elementAt(0));
	          long l=(long)Math.pow(2,i);
	          String sSp=i<-1 ? Sort.gets(Nod.getLabel(),1):null;
	          String sSpalte=sSp;
	          String sSpB=Sort.gets(Nod.getLabel(),2);
	          if (i==-12 || i==-11 || i==-3)
	          {
	        	  String sTab=sSpalte.substring(4);
	        	  String sV=i==-3 ? "=m.":"=b.";
	        	  sSpalte="(select kennung from "+sTab+" where aic_"+sTab+sV+sSpalte+") "+ sSpB;
	          }
	          else if (i==-10) // Periode
	        	  sSpalte="(select kennung from stammview2 where aic_stamm=m."+sSpalte+") "+ sSpB;
	          else if (i==-13) // Bilder
	          {
	        	  sSpalte="(select filename from daten_bild where aic_tabellenname="+Global.iTabBegriff+" and aic_fremd=b.aic_begriff and aic_zustand="+Global.iSpSw+") "+ sSpB+
	        			  ",(select filename from daten_bild where aic_tabellenname="+Global.iTabBegriff+" and aic_fremd=b.aic_begriff and aic_zustand="+Global.iSpFX+") "+ sSpB+"FX";
	        	  sSp="(select count(*) from daten_bild where aic_tabellenname="+Global.iTabBegriff+" and aic_fremd=b.aic_begriff)>0";
	          }
//	          String sSpalte=i>-2 ? null:i==-2 ? "KENNZEICHEN":i==-3 ? "AIC_EIGENSCHAFT":i==-4 ? "AIC_ABFRAGE":i==-5 ? "Alias":i==-6 ? "Farbe":i==-7 ? "Max_B":i==-8 ? "Bildname":i==-9 ? "Homepage":"xx";
	          Tabellenspeicher Tab = new Tabellenspeicher(g,"select defbezeichnung,kennung,b.aic_begriff,b.web"+(sSpalte==null ? "":","+sSpalte)+" from begriff b join Modell m on b.aic_begriff=m.aic_begriff where "+
	        		  (i==-13 ? sSp:i<0 ? (i==-1 ? "WEB":sSp)+" is not null":g.bit("b.bits",l)),true);
	          Tab.showGrid("Modelle mit bit " + i, FomGid);
                  Tab.Grid.getOutliner().addMouseListener(new MouseListener()
                  {
                    public void mousePressed(MouseEvent ev)
                    {}

                    public void mouseClicked(MouseEvent ev)
                    {
                      if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
                      {
                        int iPos = TabModell.getPos("AIC", g.BegriffToModell(Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(), 2)));
                        if (iPos >= 0)
                        {
                          JCOutlinerNode Nod = (JCOutlinerNode)TabModell.getInhalt("Node", iPos);
                          openUp(Nod);
                          Static.makeVisible(OutModell, Nod);
                        }
                      }
                    }

                    public void mouseEntered(MouseEvent ev) {}
                    public void mouseExited(MouseEvent ev) {}
                    public void mouseReleased(MouseEvent ev) {}
                  });
	        }
	      });
	      FomGid.setVisible(true);
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
                Tabellenspeicher.showGrid(TabModell, "Modell", s2,thisFrame);
                Tabellenspeicher.showGrid(TabSpalten, "Spalten", s2,thisFrame);
                Tabellenspeicher.showGrid(TabCode, "Code", s2,thisFrame);
              }
            };
          MnuTab.addActionListener(AL1);
        }

        /*private int getModellBegriff(int iModell)
        {
          //g.checkModelle();
          int iPos=g.TabModelle.getPos("aic_modell",iModell);
          if (iPos>=0)
            return g.TabModelle.getI(iPos,"aic_begriff");
          else
          {
            Static.printError("DefModell.getModellBegriff: Modell "+iModell+" nicht gefunden!");
            return -1;
          }
        }*/

        private void checkSperre()
        {
          //g.progInfo("DefModell.checkSperre:"+bGeaendert);
          if (bGeaendert)
          {
            JCOutlinerFolderNode Node = (JCOutlinerFolderNode)OutBuild.getRootNode();
            int iBegriff=g.ModellToBegriff(((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue());//SQL.getInteger(g,"SELECT AIC_Begriff FROM Modell WHERE AIC_Modell="+iAIC_Modell);
            g.exec("update begriff set log_aic_logging=null where aic_begriff="+iBegriff);
            g.defInfo("Modell " + g.TabBegriffe.getBezeichnungS(iBegriff) + " ("+iBegriff+") entsperrt");
            //g.progInfo(iAIC_Modell+"/"+iBegriff);
          }
        }

        private void DefAbfrageAufrufen(boolean b1)
        {
          JCOutlinerNode NodeSpalte = OutSpalten.getSelectedNode();
          int iDefStt=0;
          int iDefAbf=0;
          if (NodeSpalte != null)
          {
            if (NodeSpalte.getLevel()==4)
              NodeSpalte=NodeSpalte.getParent();
            if (NodeSpalte.getLevel()==3)
            {
              iDefAbf=Tabellenspeicher.geti(((Vector)NodeSpalte.getUserData()).elementAt(0));
              NodeSpalte = NodeSpalte.getParent();
            }
            if (NodeSpalte.getLevel()==2)
            {
              Vector Vec=(Vector)NodeSpalte.getUserData();
              char c=Vec.elementAt(0).toString().charAt(0);
              iDefStt=c=='S'? Tabellenspeicher.geti(Vec.elementAt(1)) : c=='B' ? -Tabellenspeicher.geti(Vec.elementAt(1)):0;
            }
            //g.testInfo("Abfrage="+iDefAbf+", Stt="+iDefStt);
          }
          DefAbfrage.get(g,new ShowAbfrage(g,iDefAbf,Abfrage.cstAbfrage),iDefStt,null,false,b1 ? 0:1).show(false);
        }

        public static void free()
        {
          if (FomBuild != null)
            FomBuild.dispose();
          if (FomSpalten != null)
            FomSpalten.thisFrame.dispose();
          if (Self!=null)
          {
            Self.g.fixtestInfo("DefModell.free");
            Self.thisFrame.dispose();
            Self = null;
          }
        }

        public void show()
        {
          super.show();
          if (Self==null)
          {
            Self=this;
            Self.g.fixtestInfo("DefModell.show");
          }
          FomBuild.setVisible(true);
          FomBuild.toFront();
          FomSpalten.show();
        }

        private void openUp(JCOutlinerNode Nod)
        {
          if (Nod != null)
          {
            JCOutlinerFolderNode NodP = Nod.getParent();
            while (NodP != null)
            {
              NodP.setState(BWTEnum.FOLDER_OPEN_ALL);
              NodP = NodP.getParent();
            }
          }
        }

        private void SynchronBefehl(JCOutlinerNode Out,boolean bModell)
        {
//        	g.fixtestError("SynchronBefehl "+bModell);
          Vector Vec=(Vector)Out.getUserData();
          //g.progInfo("Synchron:"+Vec);
          if (Vec.elementAt(0) != null)
          {
            String s=(String)Vec.elementAt(2);
            String sK=CbxBez.isSelected() ? "Bezeichnung":"Kennung";
            //CbxNot.setSelected(false);
            if (s.equals("Modell")/* && bModell*/)
            {
              if (!bModell)
                return;
              TabModell.posInhalt("Aic",Vec.elementAt(0));
              TxtCode.setText("M:"+TabModell.getS(sK));
              JCOutlinerNode Nod=(JCOutlinerNode)TabModell.getInhalt("Node");
              openUp(Nod);
              ((JTabbedPane)OutModell.getParent().getParent().getParent()).setSelectedIndex(0);
              Static.makeVisible(OutModell, Nod);
            }
            else if (!bModell)
            {
              if(s.equals("If")) {
                TabCode.posInhalt("Aic", Vec.elementAt(0));
                TxtCode.setText("I:" + TabCode.getS(sK));
                JCOutlinerNode Nod = (JCOutlinerNode)TabCode.getInhalt("Node");
                openUp(Nod);
                try
                {
                  //((JTabbedPane)OutModell.getParent().getParent().getParent()).setSelectedIndex(1);
                  ((JTabbedPane)OutAnweisung.getParent().getParent().getParent()).setSelectedIndex(2);
                }
                catch(Exception e) { };
                Static.makeVisible(OutBedingung, Nod);
              }
              else if(s.equals("While")) {
                //CbxNot.setSelected(Vec.elementAt(6)!=null);
                TabCode.posInhalt("Aic", Vec.elementAt(0));
                TxtCode.setText("W:" + TabCode.getS(sK));
                JCOutlinerNode Nod = (JCOutlinerNode)TabCode.getInhalt("Node2");
                openUp(Nod);
                try
                {
                  //((JTabbedPane)OutModell.getParent().getParent().getParent()).setSelectedIndex(1);
                  ((JTabbedPane)OutAnweisung.getParent().getParent().getParent()).setSelectedIndex(2);
                }
                catch(Exception e) { };
                Static.makeVisible(OutBedingung, Nod);
              }
              else {
                TabCode.posInhalt("Aic", Vec.elementAt(0));
                TxtCode.setText(TabCode.getS(sK));
                JCOutlinerNode Nod = (JCOutlinerNode)TabCode.getInhalt("Node");
                openUp(Nod);
                /*g.progInfo("1:"+Static.print(OutBedingung.getParent()));
                g.progInfo("2:"+Static.print(OutBedingung.getParent().getParent()));
                g.progInfo("3:"+Static.print(OutBedingung.getParent().getParent().getParent()));
                g.progInfo("4:"+Static.print(OutBedingung.getParent().getParent().getParent().getParent()));
                g.progInfo("5:"+Static.print(OutBedingung.getParent().getParent().getParent().getParent().getParent()));
                g.progInfo("1a:"+Static.print(OutAnweisung.getParent()));
                g.progInfo("2a:"+Static.print(OutAnweisung.getParent().getParent()));
                g.progInfo("3a:"+Static.print(OutAnweisung.getParent().getParent().getParent()));
                g.progInfo("4a:"+Static.print(OutAnweisung.getParent().getParent().getParent().getParent()));
                g.progInfo("5a:"+Static.print(OutAnweisung.getParent().getParent().getParent().getParent().getParent()));
                g.progInfo("6a:"+Static.print(OutAnweisung.getParent().getParent().getParent().getParent().getParent().getParent()));
                g.progInfo("7a:"+Static.print(OutAnweisung.getParent().getParent().getParent().getParent().getParent().getParent().getParent()));
                g.progInfo("1b:"+Static.print(OutModell.getParent()));
                g.progInfo("2b:"+Static.print(OutModell.getParent().getParent()));
                g.progInfo("3b:"+Static.print(OutModell.getParent().getParent().getParent()));
                g.progInfo("4b:"+Static.print(OutModell.getParent().getParent().getParent().getParent()));
                g.progInfo("5b:"+Static.print(OutModell.getParent().getParent().getParent().getParent().getParent()));*/
                try
                {
                  //((JTabbedPane)OutModell.getParent().getParent().getParent()).setSelectedIndex(1);
                  ((JTabbedPane)OutAnweisung.getParent().getParent().getParent()).setSelectedIndex(1);
                }
                catch(Exception e) { };
                Static.makeVisible(OutAnweisung, Nod);
              }
            }
          }
        }

        private void SynchronBefehl2(String sK,boolean bDown)
        {
//        	g.fixtestError("SynchronBefehl2: "+sK+", down="+bDown);
          boolean bMemo=sK.startsWith("Memo:");
          boolean bModell=sK.startsWith("M:");
          boolean bIf=sK.startsWith("I:");
          boolean bWhile=sK.startsWith("W:");
          boolean bEig=sK.startsWith("E:");
          sK=bMemo? sK.substring(5):bModell || bWhile || bIf || bEig ? sK.substring(2):sK;
          String sKT=CbxBez.isSelected() ? "Bezeichnung":"Kennung";
          boolean bVoll=CbxVoll.isSelected();
          boolean bCase=CbxCase.isSelected();
          if (bMemo)
          {
            long lClock=Static.get_ms();
            sK=(bVoll?"%":"")+sK+"%";
            Tabellenspeicher Tab=new Tabellenspeicher(g,"select modell.aic_modell aic,defbezeichnung,aic_befehl+1-(select min(aic_befehl) from befehl2 where aic_modell=modell.aic_modell) Zeile,memo from begriff"+
                g.join2("modell","begriff")+" join befehl on befehl.aic_modell=modell.aic_modell join daten_memo on aic_tabellenname="+iTabBefehl+" and aic_fremd=aic_befehl and memo like '"+sK+"'",true);
            Tab.addTab(new Tabellenspeicher(g,"select modell.aic_modell aic,defbezeichnung,null Zeile,memo from modell"+g.join("begriff","modell")+
                " join daten_memo on aic_tabellenname="+Global.iTabBegriff+" and aic_fremd=begriff.aic_begriff and memo like '"+sK+"'",true));
            Tab.showGrid(sK);
            //Tab.Grid.getOutliner().setNodeHeight(50);
            Tab.Grid.getOutliner().addMouseListener(new MouseListener()
            {
             public void mousePressed(MouseEvent ev) {}

             public void mouseClicked(MouseEvent ev)
             {
              if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
              {
                int iPos=TabModell.getPos("AIC",Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(), 0));
                if (iPos>=0)
                {
                  ((JTabbedPane)OutModell.getParent().getParent().getParent()).setSelectedIndex(0);
                  JCOutlinerNode Nod=(JCOutlinerNode)TabModell.getInhalt("Node",iPos);
                  openUp(Nod);
                  Static.makeVisible(OutModell, Nod);
                }
              }
             }
             public void mouseEntered(MouseEvent ev)  {}
             public void mouseExited(MouseEvent ev) {}
             public void mouseReleased(MouseEvent ev) {}
            });
            g.clockInfo("Memo <"+sK+"> suchen",lClock);
          }
          else if (bModell)
          {
        	  if (CbxAic.isSelected())
        		  iMPos=TabModell.getPos("Aic_Begriff",Sort.geti(sK));
        	  else
        		  iMPos=bDown ? TabModell.posLikeD(iMPos,sKT,sK,bVoll,bCase):TabModell.posLikeU(iMPos,sKT,sK,bVoll,bCase);
            if (iMPos>-1)
            {
              ((JTabbedPane)OutModell.getParent().getParent().getParent()).setSelectedIndex(0);
              JCOutlinerNode Nod=(JCOutlinerNode)TabModell.getInhalt("Node",iMPos);
              openUp(Nod);
              Static.makeVisible(OutModell, Nod);
            }
          }
          else if (bEig)
          {
            iEPos=bDown ? TabSpalten.posLikeD(iEPos,"Bezeichnung",sK,bVoll,bCase):TabSpalten.posLikeU(iEPos,"Bezeichnung",sK,bVoll,bCase);
            if (iEPos>-1)
            {
              JCOutlinerNode Nod=(JCOutlinerNode)TabSpalten.getInhalt("Node",iEPos);
              openUp(Nod);
              Static.makeVisible(OutSpalten, Nod);
            }
          }
          else
          {
            iBPos=bDown ? TabCode.posLikeD(iBPos,sKT,sK,bVoll,bCase):TabCode.posLikeU(iBPos,sKT,sK,bVoll,bCase);
            if (iBPos>-1)
            {
              JCOutlinerNode Nod=(JCOutlinerNode)TabCode.getInhalt(bWhile?"Node2":"Node",iBPos);
              openUp(Nod);
              if (bIf || bWhile)
                ((JTabbedPane)OutAnweisung.getParent().getParent().getParent()).setSelectedIndex(2);
              else
                ((JTabbedPane)OutAnweisung.getParent().getParent().getParent()).setSelectedIndex(1);
              Static.makeVisible(bIf || bWhile ? OutBedingung:OutAnweisung, Nod);
            }
          }
        }

//        private void SynchronBefehl(String sK)
//        {
//          boolean bModell=sK.startsWith("M:");
//          boolean bWhile=sK.startsWith("W:");
////          g.fixtestError("SynchronBefehl: "+sK+", Modell="+bModell);
//          sK=bModell || bWhile ? sK.substring(2):sK;
//          String sKT=CbxAic.isSelected() ? "Aic_Begriff":CbxBez.isSelected() ? "Bezeichnung":"Kennung";
//          if (bModell)
//          {
////        	g.fixtestError("pos:"+sKT+" bei "+(CbxAic.isSelected() ? Sort.geti(sK):sK));
//            if (TabModell.posInhalt(sKT,CbxAic.isSelected() ? Sort.geti(sK):sK))
//            {
//              JCOutlinerNode Nod=(JCOutlinerNode)TabModell.getInhalt("Node");
//              openUp(Nod);
//              Static.makeVisible(OutModell, Nod);
//            }
//            //TabModell.showGrid("TabModell");
//          }
//          else if (TabCode.posInhalt(sKT,sK))
//          {
//            JCOutlinerNode Nod=(JCOutlinerNode)TabCode.getInhalt("Node");
//            openUp(Nod);
//            if (TabCode.isNull("Node2"))
//              Static.makeVisible(OutAnweisung, Nod);
//            else if (bWhile)
//              Static.makeVisible(OutBedingung, (JCOutlinerNode)TabCode.getInhalt("Node2"));
//            else
//              Static.makeVisible(OutBedingung, Nod);
//          }
//        }

        private void fillVarEingabe(String sE,String sVar)
        {
        	CbxVar.setSelected(!Static.Leer(sVar));
        	TxtEingabe.setText(sE);
        	TxtVar.setText(sVar);
//        	if (s == null)
//                TxtEingabe.setText("");
//            else
//                TxtEingabe.setText(s);
////        	if (PnlVar !=null)
//        		if (bVar && s!=null)
//	        	{
//	        		int i=s.indexOf('=');
//	        		if (i<0)
//	        		{
//	        			TxtVar.setText(s);
//	        			TxtEingabe.setText("");
//	        		}
//	        		else
//	        		{
//	        			TxtVar.setText(s.substring(0,i));
//	        			TxtEingabe.setText(s.substring(i+1));
//	        		}
//	        	}
//        		else
//        			TxtVar.setText("");
        }
        
        private void SynchronVar(JCOutlinerNode Out)
        {
        	Vector Vec=(Vector)Out.getUserData();
        	int iBits = Sort.geti(Vec, 5);
            fillVarEingabe((String)Vec.elementAt(3),Sort.gets(Vec, 9));//(iBits & Calc.VAR) > 0);
            CbxPerm.setSelected((iBits & Calc.PERM) > 0);
            int iVArt=iBits & Calc.VART;
            switch(iVArt)
            {
            	case Calc.VLOK:RadVlokal.setSelected(true);break;
            	case Calc.VICH:RadVich.setSelected(true);break;
            	case Calc.V_R:RadVread.setSelected(true);break;
            	case Calc.VALL:RadValle.setSelected(true);break;
            }
        }
        
        private void SynchronSpalte(JCOutlinerNode Out)
        {
          Vector Vec=(Vector)Out.getUserData();
          
          String s2=""+Vec.elementAt(2);
          if (Vec.elementAt(1) != null)
          {
            TabSpalten.posInhalt("Aic",Vec.elementAt(1));
            JCOutlinerNode Nod=(JCOutlinerNode)TabSpalten.getInhalt("Node");
            openUp(Nod);
            OutSpalten.folderChanged(OutSpalten.getRootNode());
            Static.makeVisible(OutSpalten,Nod);
          }
          else if (!s2.equals("If-Ja") && !s2.equals("If-Nein"))
          {
            JCOutlinerNode Nod=OutSpalten.getSelectedNode();
            if (Nod !=null && Nod.getLevel()>3)
              Static.makeVisible(OutSpalten,Nod.getParent());
          }
        }     

        private void SynchronBits(JCOutlinerNode Out)
        {
          Vector Vec=(Vector)Out.getUserData();
          int iBits = Sort.geti(Vec, 5);
          //g.fixtestError("SynchronBits:"+iBits);

          RadAuto.setSelected((iBits & Calc.REFRESH) == Calc.AUTO);
          RadImmer.setSelected((iBits & Calc.REFRESH) == Calc.IMMER);
          RadNie.setSelected((iBits & Calc.REFRESH) == Calc.NIE);

          CbxGruppe.setSelected((iBits & Calc.GRUPPE) > 0);
          CbxSpalte.setSelected((iBits & Calc.SPALTE) > 0);
          CbxReg.setSelected((iBits & Calc.REG) > 0);
          CbxBSave.setSelected((iBits & Calc.SAVE) > 0);
          CbxAlle.setSelected((iBits & Calc.ALLE) > 0);
          CbxThread2.setSelected((iBits & Calc.THREAD) > 0);

          RadNormal.setSelected((iBits & Calc.ART) == Calc.NORMAL);
          RadEmpty.setSelected((iBits & Calc.ART) == Calc.EMPTY);
          //RadSync.setSelected((iBits&Calc.ART)==Calc.SYNC);
          RadVec.setSelected((iBits & Calc.ART) == Calc.USE_VEC);
          RadVecBew.setSelected((iBits & Calc.ART) == Calc.VEC_BEW);
          
          CbxKSZR.setSelected((iBits & Calc.KSZR) > 0);
          CbxKBZR.setSelected((iBits & Calc.KBZR) > 0);
          
          CbxSum.setSelected((iBits & Calc.SUM) > 0);
          
          RadKein.setSelected((iBits & Calc.C_ART) == 0);
          RadConcat.setSelected((iBits & Calc.C_ART) == Calc.CONCAT);
          RadCWS.setSelected((iBits & Calc.C_ART) == Calc.CWS);
          RadInit.setSelected((iBits & Calc.C_ART) == Calc.INIT);
          
          RadMkein.setSelected((iBits & Calc.M_ART) == 0);
          RadMadd.setSelected((iBits & Calc.M_ART) == Calc.ADD);
          RadMsub.setSelected((iBits & Calc.M_ART) == Calc.SUB);
          RadMinit.setSelected((iBits & Calc.M_ART) == Calc.MINIT);
        }

	private void Build()
	{
		CbxSyncBefehl = getCheckbox("SyncBefehl");
		CbxSyncSpalte = getCheckbox("SyncSpalte");
        CbxSyncVar = getCheckbox("SyncVar");
        if(CbxSyncVar==null) CbxSyncVar = new AUCheckBox();
        CbxSyncBits = getCheckbox0("SyncBits");
		//if(CbxSyncBits==null) CbxSyncBits = new AUCheckBox();
                CbxZusammen = getCheckbox0("zusammenfassen");
		//if(CbxZusammen==null) CbxZusammen = new AUCheckBox();
                CbxNurAic = getCheckbox0("nur_Aic");
		//if(CbxNurAic==null) CbxNurAic = new AUCheckBox();
                CbxColMemo = getCheckbox("ColMemo");
		//if(CbxColMemo==null) CbxColMemo = new JCheckBox();
                CbxColSpalte = getCheckbox("ColSpalte");
                CbxColEingabe = getCheckbox0("ColEingabe");
                CbxColVar = getCheckbox0("ColVar");
		//if(CbxColEingabe==null) CbxColEingabe = new AUCheckBox();
                CbxColBefehl = getCheckbox("ColBefehl");
		//if(CbxColBefehl==null) CbxColBefehl = new JCheckBox();
                //CbxDatentyp = getCheckbox("Datentyp");
		//if(CbxDatentyp==null) CbxDatentyp = new JCheckBox();
                //CbxColKeinZeitraum = getCheckbox("ColKeinZeitraum");
		//if(CbxColKeinZeitraum==null) CbxColKeinZeitraum = new JCheckBox();
                CbxColQry = getCheckbox("ColQry");
		//if(CbxColQry==null) CbxColQry = new JCheckBox();
                CbxColZR = getCheckbox("ColZR");
		//if(CbxColZR==null) CbxColZR = new JCheckBox();
                CbxColPush = getCheckbox("ColPush");
                CbxKeinTooltip = getCheckbox0("kein_Tooltip");
                CbxKeinTooltip.setSelected(true);
                //if(CbxKeinTooltip==null) CbxKeinTooltip = new AUCheckBox();
		//if(CbxColPush==null) CbxColPush = new JCheckBox();
		CbxBez = getCheckbox("Bezeichnung");
		CbxAic = getCheckbox("Aic");
		if(CbxAic==null) CbxAic = new AUCheckBox();
		//if(CbxBez==null) CbxBez = new JCheckBox();
                CbxVoll = getCheckbox("Volltextsuche");
                //if(CbxVoll==null) CbxVoll = new JCheckBox();
                CbxCase = getCheckbox("Case");
                //if(CbxCase==null) CbxCase = new JCheckBox();
                //if(CbxOriginalBez==null) CbxOriginalBez = new JCheckBox();
                CbxNot = getCheckbox("not");
                //CbxSortNow = getCheckbox("sort now");
                //if(CbxSortNow==null) CbxSortNow = new AUCheckBox();

                //CbxCache = getCheckbox("cache");
                //if(CbxNot==null) CbxNot = new JCheckBox();
                JPanel PnlEingabe = getFrei("Eingabe");
                if (PnlEingabe != null)
                {
                  TxtCode.setFont(Global.fontModell);
                  TxtCode.setToolTipText(Global.toHtml("suche nach \n Bedingung (If) mit I:\n Schleife (While) mit W:\n Modell mit M:\n Spalte mit E:\n Memo mit Memo:"));
                  PnlEingabe.add(TxtCode);
                  TxtCode.addKeyListener(new KeyListener()
                  {
			public void keyPressed(KeyEvent ev)
			{
			}
			public void keyReleased(KeyEvent ev)
			{
			  int iK=ev.getKeyCode();
			  if (iK==KeyEvent.VK_DOWN || iK==KeyEvent.VK_ENTER)
			    SynchronBefehl2(TxtCode.getText(),true);
			  else if (iK==KeyEvent.VK_UP)
			    SynchronBefehl2(TxtCode.getText(),false);
			}
			public void keyTyped(KeyEvent ev)
			{
			}
                  });
//                  TxtCode.addFocusListener(new FocusListener()
//                  {
//                    public void focusGained(FocusEvent fe) {}
//                    public void focusLost(FocusEvent fe) {
//                    	g.fixtestError("TxtCode focusLost");
//                      SynchronBefehl(TxtCode.getText());
//                    }
//                  });
                }
                //CbxHierarchie.setSelected(true);
                //BtnShow = getButton("show");
                //if(BtnShow==null) BtnShow=new JButton();
		BtnNeu = getButton("Neu");
		//if(BtnNeu==null) BtnNeu=new JButton();
                //BtnKopie = getButton("Kopie");
                //if(BtnKopie==null) BtnKopie=new JButton();
		BtnBearbeiten = getButton("Edit");
                if (BtnBearbeiten==null)
                  BtnBearbeiten=getButton("Parameter");

		if(BtnBearbeiten==null) BtnBearbeiten=new JButton();
		//BtnBearbeitenModell = getButton("Bearbeiten Modell");
		//if(BtnBearbeitenModell==null) BtnBearbeitenModell=new JButton();
		BtnSpeichern = getButton("Speichern");
		//if(BtnSpeichern==null) BtnSpeichern=new JButton();
		BtnBeenden = getButton("Beenden");
                BtnAlle = getButton("alle_Daten");
                BtnSub = getButton("nur_Vector");
		//if(BtnBeenden==null) BtnBeenden=new JButton();
		//BtnLoeschen = getButton("Loeschen");
		//if(BtnLoeschen==null) BtnLoeschen=new JButton();
		BtnHinzufuegen = getButton(">");
		if(BtnHinzufuegen==null) BtnHinzufuegen=new JButton();
		BtnEntfernen = getButton("<");
		if(BtnEntfernen==null) BtnEntfernen=new JButton();
		BtnRauf = getButton("Pfeil oben");
		if(BtnRauf==null) BtnRauf=new JButton();
		BtnRunter = getButton("Pfeil unten");
		if(BtnRunter==null) BtnRunter=new JButton();
		BtnErsetzen = getButton("Ersetzen");
		if(BtnErsetzen==null) BtnErsetzen=new JButton();
		BtnZurueck = getButton("Zurueck");
		if(BtnZurueck==null) BtnZurueck=new JButton();
                BtnEdit = getButton("editieren");
		if(BtnEdit==null) BtnEdit=new JButton();
        BtnInfoBreak = getButton("info_break");
        if(BtnInfoBreak==null) BtnInfoBreak=new JButton();
        BtnDelBreak = getButton("del_break");
		if(BtnDelBreak==null) BtnDelBreak=new JButton();
		//BtnNot = getButton("Not");
		//if(BtnNot==null) BtnNot=new JButton();
		BtnAufruf = getButton("Aufruf");
		if(BtnAufruf==null) BtnAufruf = new JButton();
		BtnAbfrage = getFormularButton("Abfrage");
		if(BtnAbfrage==null) BtnAbfrage = new JButton();
                //BtnAbfrage2 = getButton("Abfrage2");
		//if(BtnAbfrage2==null) BtnAbfrage2 = new JButton();
                //BtnDruck = getButton("Druck");
		//if(BtnDruck==null) BtnDruck = new JButton();
		//BtnRefresh = g.getButton("Refresh");
		//if(BtnRefresh==null) BtnRefresh = new JButton();
                BtnRefresh2 = getButton("Refresh2");
		if(BtnRefresh2==null) BtnRefresh2 = new JButton();
                BtnSyncStamm = getFormularButton("SyncStamm");
                BtnVarG = getButton("VarG");
                BtnAbfG = getButton("AbfG");
                //if(BtnSyncStamm==null) BtnSyncStamm = new JButton();
                BtnInitFrame = getButton("Init");
                if(BtnInitFrame==null) BtnInitFrame = new JButton();

		JPanel Pnl_Outliner_Modell = getFrei("Outliner Modell");
		JPanel Pnl_Outliner_Anweisung = getFrei("Outliner Anweisung");
		JPanel Pnl_Outliner_Bedingung = getFrei("Outliner Bedingung");
		//JPanel Pnl_Outliner_Spalten = getFrei("Outliner Spalten");
		//JPanel Pnl_Outliner = getFrei("Outliner");
		JPanel Pnl_Memo = getFrei("Memo");
		//JPanel Pnl_Titel = getFrei("Titel");
		JPanel Pnl_Memo2 = getFrei("Memo2");
		JPanel Pnl_Applikation = getFrei("Combo Modul");


		if(Pnl_Applikation!=null)
		{
			Pnl_Applikation.setLayout(new BorderLayout());
			Pnl_Applikation.add("Center",CboModul);
		}
		if(Pnl_Outliner_Modell!=null)
		{
			Pnl_Outliner_Modell.setLayout(new BorderLayout());
			OutModell.setAllowMultipleSelections(true);
			Pnl_Outliner_Modell.add("Center",OutModell);
		}
		if(Pnl_Outliner_Anweisung!=null)
		{
			Pnl_Outliner_Anweisung.setLayout(new BorderLayout());
			Pnl_Outliner_Anweisung.add("Center",OutAnweisung);
		}
		if(Pnl_Outliner_Bedingung!=null)
		{
			Pnl_Outliner_Bedingung.setLayout(new BorderLayout());
			Pnl_Outliner_Bedingung.add("Center",OutBedingung);
		}
		/*if(Pnl_Outliner_Spalten!=null)
		{
			Pnl_Outliner_Spalten.setLayout(new BorderLayout());
			Pnl_Outliner_Spalten.add("Center",OutSpalten);
		}*/
		//if(Pnl_Outliner!=null)
		//{
			//Pnl_Outliner.setLayout(new BorderLayout());
                        FomBuild=new JFrame("Modell-Befehle");
                        FomBuild.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        //FomSpalten.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        MemoKnoten.setEditable(false);
                        MemoKnoten.setText("\n\n\n");
                        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,OutBuild,MemoKnoten);
                        splitPane.setOneTouchExpandable(true);
                        splitPane.setResizeWeight(0.9);
			FomBuild.getContentPane().add("Center",splitPane);
                        //FomBuild.getContentPane().add("South",MemoKnoten);
                        FomBuild.pack();

                        FomSpalten=new Formular("Modell-Spalten",null,g);
                        RadAuto = FomSpalten.getRadiobutton("auto");
                        RadImmer = FomSpalten.getRadiobutton("immer");
                        RadNie = FomSpalten.getRadiobutton("nie");
                        
                        CbxVar = FomSpalten.getCheckbox("var");
                        RadVlokal=FomSpalten.getRadiobutton("lokal");
                        RadVich=FomSpalten.getRadiobutton("ich");
                        RadVread=FomSpalten.getRadiobutton("read");
                        RadValle=FomSpalten.getRadiobutton("alle");
                        CbxPerm = FomSpalten.getCheckbox("permanent");
                        
                        CbxGruppe = FomSpalten.getCheckbox("nimm_Gruppe");
                        CbxBSave = FomSpalten.getCheckbox("BSave");
                        CbxSpalte = FomSpalten.getCheckbox("auf_Spalte");
                        CbxReg = FomSpalten.getCheckbox0("auf_Register");
                        CbxAlle = FomSpalten.getCheckbox0("alle_Zeilen");
                        CbxThread2 = FomSpalten.getCheckbox0("Thread");
                        RadNormal = FomSpalten.getRadiobutton("Normal");
                        RadEmpty = FomSpalten.getRadiobutton("Empty");
                        RadVec = FomSpalten.getRadiobutton("Vec");
                        RadVecBew = FomSpalten.getRadiobutton("VecBew");
                        
                        RadKein = FomSpalten.getRadiobutton("kein");
                        RadConcat = FomSpalten.getRadiobutton("Concat");
                        RadCWS = FomSpalten.getRadiobutton("CWS");
                        RadInit = FomSpalten.getRadiobutton("Init");
                        
                        RadMkein = FomSpalten.getRadiobutton("Mkein");
                        RadMadd = FomSpalten.getRadiobutton("add");
                        RadMsub = FomSpalten.getRadiobutton("sub");
                        RadMinit = FomSpalten.getRadiobutton("Minit");
                        
                        CbxKSZR = FomSpalten.getCheckbox0("kein_Stamm-Zeitraum");
                        CbxKBZR = FomSpalten.getCheckbox0("kein_Bew-Zeitraum");
                        CbxSum = FomSpalten.getCheckbox0("SummeB");
                        if (CbxKSZR==null) CbxKSZR=new AUCheckBox();
                        if (CbxKBZR==null) CbxKBZR=new AUCheckBox();
                        if (CbxSum==null) CbxSum=new AUCheckBox();
                        if (CbxPerm==null) CbxPerm=new AUCheckBox();
                        if (RadVlokal==null) RadVlokal=new JRadioButton();
                        if (RadVich==null) RadVich=new JRadioButton();
                        if (RadVread==null) RadVread=new JRadioButton();
                        if (RadValle==null) RadValle=new JRadioButton();
                        if (RadKein==null) RadKein=new JRadioButton();
                        if (RadInit==null) RadInit=new JRadioButton();
                        if (RadConcat==null) RadConcat=new JRadioButton();
                        if (RadCWS==null) RadCWS=new JRadioButton();
                        if (RadMkein==null) RadMkein=new JRadioButton();
                        if (RadMinit==null) RadMinit=new JRadioButton();
                        if (RadMadd==null) RadMadd=new JRadioButton();
                        if (RadMsub==null) RadMsub=new JRadioButton();
                        RadVlokal.setSelected(true);
                        JPanel PnlVar=FomSpalten.getFrei("Var");
                        if (PnlVar != null)
                        	PnlVar.add(TxtVar);
                        FomSpalten.getFrei("Eingabe").add(TxtEingabe);
//                        boolean bVar=PnlVar != null;
                        TxtEingabe.setToolTipText(Global.toHtml(//(bVar ?"":"Bei var wird nur Teil vor = für Variable, Teil danach für Eingabe verwendet\nz.B. Z=5 bedeutet Variable Z mit Eingabe 5\n")+
                        		"Sonderzeichen können auch wie folgt eingegegeben werden:\n!= .. ≠\n!&lt; .. ≥\n!&gt; .. ≤\n!S .. ∑\n!U .. ∞"));
                        CbxOriginalBez = FomSpalten.getCheckbox("Original");
                        FomSpalten.getFrei("Outliner").add(OutSpalten);

		if(Pnl_Memo!=null)
		{
		  TxtMemoModell = new AUTextArea(g,0);
			TxtMemoModell.Edt.setRows(5);
			TxtMemoModell.Edt.setEditable(false);
			Pnl_Memo.setLayout(new BorderLayout(2,2));
			Pnl_Memo.add("Center",TxtMemoModell);
		}
		if(Pnl_Memo2!=null)
		{
		  TxtMemoBefehl = new AUTextArea(g,0);
			TxtMemoBefehl.Edt.setRows(5);
			Pnl_Memo2.setLayout(new BorderLayout(2,2));
			Pnl_Memo2.add("Center",TxtMemoBefehl);
		}
		/*if(Pnl_Titel!=null)
		{
			Pnl_Titel.setLayout(new BorderLayout(2,2));
			Pnl_Titel.add("Center",TxtTitelBefehl);
		}*/

		FrmNeu = new JDialog((Frame)thisFrame,false);
		FrmNeu.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		FrmNeu.getContentPane().setLayout(new BorderLayout(2,2));
		JPanel Pnl2 = new JPanel(new GridLayout(0,1,2,2));
		//JPanel Pnl3 = new JPanel(new BorderLayout(2,2));
		g.addLabel(Pnl2,"DefBezeichnung",EdtDefBezeichnung,"1");
		g.addLabel(Pnl2,"Bezeichnung",EdtBezeichnung,"1");
		g.addLabel(Pnl2,"Kennung",EdtKennung,"1");
		g.addLabel(Pnl2,"KennzeichenM",EdtKennzeichen,"1");
		g.addLabel(Pnl2,"Alias",EdtAlias,"1");
		g.addLabel(Pnl2,"Periode",CboPeriode,"1");
		g.addLabel(Pnl2,"Ampel",CboEigenschaft,"1");
//		g.addLabel(Pnl2,g.getBezeichnung("Tabellenname","Mandant"),CboMandant,"1");
		g.addLabel(Pnl2,g.TabBegriffgruppen.getBezeichnungS("Programm"),CboProg,"1");
		g.addLabel(Pnl2,g.getBezeichnung("Tabellenname","STAMMTYP"),CboStammtyp,"1");
		g.addLabel(Pnl2,g.getBezeichnung("Tabellenname","BEWEGUNGSTYP"),CboBewegungstyp,"1");
		g.addLabel(Pnl2,g.getShow("Abfrage"),CboAbfrage,"1");
		g.addLabel(Pnl2,g.getShow("Abfrage2"),CboAbfrage2,"1");
    g.addLabel(Pnl2,g.getShow("ReserveAbf1"),CboAbfWebDialog,"1");
    g.addLabel(Pnl2,g.getShow("ReserveAbf2"),CboAbfWebAmpel,"1");
		g.addLabel(Pnl2,g.getShow("maxBefehle"),SB_MaxB,"1");
		g.addLabel(Pnl2,"Bild",EdtBildname,"1");
		g.addLabel(Pnl2,"Farbe2",EdtFarbe,"1");
      //  Pnl2.add(CbxJeder);
		//Pnl2.add(new JLabel(g.getBegriffS("Show","falsche Periode")+":"));
    		//1
    	//2
    	//3
    	//4
    	//5
    	//6
    //Pnl2.add(CbxExtern);
       	//7
    	//8
             //9
         //10
        //11
         //12
               //13
     // 14
    //Pnl3.add("West",Pnl2);
//		Pnl2 = new JPanel(new GridLayout(0,1,2,2));
//		Pnl2.add(EdtDefBezeichnung);
//        Pnl2.add(EdtBezeichnung);
//        Pnl2.add(EdtKennung);
//        Pnl2.add(EdtKennzeichen);
//        Pnl2.add(EdtAlias);
//		Pnl2.add(CboPeriode);
//		Pnl2.add(CboEigenschaft);
//		Pnl2.add(CboMandant);
//		Pnl2.add(CboProg);
//        Pnl2.add(CboStammtyp);
//        Pnl2.add(CboBewegungstyp);
//        Pnl2.add(CboAbfrage);
//        Pnl2.add(BtnBild);
//        Pnl2.add(BtnBildFX);
        JPanel PnlCbxFix = new JPanel(new GridLayout(1,0,2,2));
        PnlCbxFix.add(CbxJeder);
        PnlCbxFix.add(CbxCombo);
        PnlCbxFix.add(CbxAmpel);
        PnlCbxFix.add(CbxAmpelWeb);
        PnlCbxFix.add(CbxWeiter);
        PnlCbxFix.add(CbxWeb);
        PnlCbxFix.add(CbxTod);
        Pnl2.add(PnlCbxFix);//g.add4(CbxCombo,CbxFxCombo,CbxWeb,CbxTod));
		//Pnl2.add(CboPeriode2);
        //Pnl3.add("Center",Pnl2);
	FrmNeu.getContentPane().add("North",Pnl2);
    JPanel PnlCbx = new JPanel(new GridLayout(0,2,2,2));
      PnlCbx.add(CbxEF); PnlCbx.add(CbxKnopf);		//1
      PnlCbx.add(CbxRecalc); PnlCbx.add(CbxZRhold);	//2
      PnlCbx.add(CbxOhneStamm); PnlCbx.add(CbxMenge);		//3
      PnlCbx.add(CbxKeineFrage); PnlCbx.add(CbxRefresh);	//4
      PnlCbx.add(CbxPeriode); PnlCbx.add(CbxSave);		//5
      PnlCbx.add(CbxAServer); PnlCbx.add(CbxUseQry);	//6  Pnl2.add(CbxAntwort);	
      PnlCbx.add(new JLabel()); PnlCbx.add(CbxBew);		//7
      PnlCbx.add(CbxCommit); PnlCbx.add(CbxAbbruch);	//8
      PnlCbx.add(CbxMitSo); PnlCbx.add(CbxJokerStt);      //9
      PnlCbx.add(CbxErgebnis); PnlCbx.add(CbxDelJS);         //10
      PnlCbx.add(CbxMassExport); PnlCbx.add(CbxDruckZR);       //11
      PnlCbx.add(CbxNbAServer); PnlCbx.add(CbxThread);        //12
      PnlCbx.add(CbxMDialog); PnlCbx.add(CbxDefImport);     //13
      PnlCbx.add(CbxEigenG); PnlCbx.add(CbxClean);//14
      PnlCbx.add(CbxNurTest); PnlCbx.add(CbxSperre); //15
      //PnlCbx.add(new JLabel()); PnlCbx.add(CbxTod);  // 14
    
//	JPanel PnlMemo=new JPanel(new GridLayout(2,0));
//	JSplitPane SpP=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,PnlCbx,PnlMemo);
	JTabbedPane PnlTab=new JTabbedPane();
	  PnlTab.add(g.getShow("Bits"),PnlCbx);
	    EdtMemo.Edt.setRows(5);
	  PnlTab.add(g.getShow("Memo"),EdtMemo);
      EdtTooltip.Edt.setRows(5);
    PnlTab.add(g.getShow("Tooltip"),EdtTooltip);
     JPanel PnlBilder = new JPanel(new GridLayout(0,1,2,2));
      g.addLabel(PnlBilder,"Bild_Swing",BtnBild,"0");
      g.addLabel(PnlBilder,"Bild_FX",BtnBildFX,"0");
    PnlTab.add(g.getShow("Bilder"),PnlBilder);
      JPanel PnlInfo = new JPanel(new BorderLayout(2,2));
      JPanel PnlInfoN = new JPanel(new BorderLayout(2,2));
      JPanel PnlInfoW = new JPanel(new GridLayout(0, 1));
      JPanel PnlInfoC = new JPanel(new GridLayout(0, 1));
      JPanel PnlInfoE = new JPanel(new GridLayout(0, 1));
      g.addWCE(PnlInfoN,PnlInfoW,PnlInfoC,PnlInfoE);
      PnlInfo.add("North",PnlInfoN);
      PnlInfoW.add(g.getLabel("aic_Begriff"));PnlInfoC.add(LblBegriff);PnlInfoE.add(new JLabel());
      PnlInfoW.add(g.getLabel("aic_Modell"));PnlInfoC.add(LblModell);PnlInfoE.add(new JLabel());
      PnlInfoW.add(g.getLabel("angelegt"));PnlInfoC.add(LblAngelegt);PnlInfoE.add(new JLabel());
      PnlInfoW.add(g.getLabel("von"));PnlInfoC.add(LblVon);PnlInfoE.add(new JLabel());
      BtnBack=g.getButton("Back");
      PnlInfoW.add(g.getLabel("Importzeit"));PnlInfoC.add(LblImportzeit);PnlInfoE.add(BtnBack);      
      // PnlInfoW.add(new JLabel());PnlInfoC.add(BtnBack);
      // g.addWCE(PnlInfo,new JLabel("aic_Begriff"),LblBegriff,null);
      // g.addWCE(PnlInfo,new JLabel("aic_Modell"),LblModell,null);
      // g.addWCE(PnlInfo,new JLabel("angelegt"),LblAngelegt,null);
      // g.addWCE(PnlInfo,new JLabel("von"),LblVon,null);
      // g.addWCE(PnlInfo,new JLabel("Importzeit"),LblImportzeit,null);
    PnlTab.add(g.getShow("Info"),PnlInfo);

	FrmNeu.getContentPane().add("Center",PnlTab);

		JPanel Pnl = new JPanel(new GridLayout(1,0,2,2));
		BtnNeuOk = g.getButton("Ok");
        FrmNeu.getRootPane().setDefaultButton(BtnNeuOk);
		Pnl.add(BtnNeuOk);
		BtnNeuAbbruch = g.getButton("Abbruch");
		Pnl.add(BtnNeuAbbruch);
		FrmNeu.getContentPane().add("South",Pnl);
		FrmNeu.setSize(400,760);

		BtnUnd = g.getButton("Und");
		BtnInit = g.getButton("Init");
		BtnInfo = g.getButton("Info");

		FrmAufruf = new JDialog((Frame)thisFrame,true);
		FrmAufruf.getContentPane().setLayout(new BorderLayout(2,2));
		Pnl = new JPanel(new GridLayout(0,1,2,2));
		  g.addLabel2(Pnl,"Name");
//		  g.addLabel2(Pnl,"PW");
      g.addLabel2(Pnl,"Datum von");
      g.addLabel2(Pnl,"Datum bis");
      g.addLabel2(Pnl,"Stamm");
		  CbxNS=getCheckboxM("nach_Save",false);
		  ZahlBew=new Zahl(0);
		  ZahlBBeg=new Zahl(0);
		  Pnl.add(CbxNS);
		  Pnl.add(BtnInfo);
      CbxWebTest=getCheckboxM("Web",false);
      Pnl.add(new JLabel());
      Pnl.add(CbxTimer);
		  Pnl.add(new JLabel());
      FrmAufruf.getContentPane().add("West",Pnl);
		Pnl = new JPanel(new GridLayout(0,1,2,2));
		  Pnl.add(EdtName);
//		  Pnl.add(EdtPW);
		  Pnl.add(DatVon);
		  Pnl.add(DatBis);
		  Pnl.add(SteStamm);
		   JPanel PnlSub = new JPanel(new GridLayout(1,0,2,2));
		   PnlSub.add(ZahlBew);
		   PnlSub.add(g.getLabelR("BerBegriff"));
		   PnlSub.add(ZahlBBeg);
		  Pnl.add(PnlSub);
		    Pnl2 = new JPanel(new GridLayout(0,2,2,2));
		    Pnl2.add(BtnUnd);
		    Pnl2.add(BtnInit);
		  Pnl.add(Pnl2);
          Pnl.add(new JLabel());
          Pnl.add(CbxDebug);
		  Pnl.add(CbxWebTest);
		/*Pnl.add(SpnAbbruch);
		Pnl.add(SpnDebug);
		//Pnl.add(SpnTabellen);
		Pnl.add(SpnPause);*/

		FrmAufruf.getContentPane().add("Center",Pnl);
		BtnAufrufOk = g.getButton("Aufruf");
    BtnReCalc = g.getButton("reCalc");
    BtnWeiter = g.getButton("Weiter");
    BtnMD = g.getButton("Modelldialog");
    BtnFrage = g.getButton("Frage");
    FrmAufruf.getRootPane().setDefaultButton(BtnAufrufOk);
		BtnAufrufAbbruch = g.getButton("Abbruch");
		Pnl = new JPanel(new GridLayout(1,0,2,2));
		Pnl.add(BtnAufrufOk);
		Pnl.add(BtnMD);
		Pnl.add(BtnFrage);
		Pnl.add(BtnWeiter);
		Pnl.add(BtnReCalc);
		Pnl.add(BtnAufrufAbbruch);
		FrmAufruf.getContentPane().add("South",Pnl);
		//FrmAufruf.setSize(380,260);
		FrmAufruf.pack();
	}

        /*private void addRG(JRadioButton Rad,GroupBox Pnl,ButtonGroup GB)
        {
          GB.add(Rad);
          Pnl.add(Rad);
        }*/

	private void fillOutModell()
	{
		long lClock=Static.get_ms();
		//g.fixtestError("fillOutModell: Web="+bWeb+", Tod="+bTod+" Modell="+iModellF);
		SQL Qry = new SQL(g);
		//g.printInfo("fillOutModell()");
		JCOutlinerFolderNode NodRoot = (JCOutlinerFolderNode)OutModell.getRootNode();
		JCOutlinerNode NodeSelected = OutModell.getSelectedNode();
		int iSelect = NodeSelected==null || NodeSelected==NodRoot?0:((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue();
		NodRoot.removeChildren();
		TabModell.clearAll();
        if (TabModule==null)
          TabModule=new Tabellenspeicher(g,"SELECT distinct z.* from begriff_zuordnung z join begriff b2 on b2.aic_begriff=z.aic_begriff and b2.aic_begriffgruppe="+
                                       g.TabBegriffgruppen.getAic("Modul")+" order by beg_aic_begriff",true);
        //TabModule.showGrid("TabModule");
		//if(!CbxHierarchie.isSelected())
		if (iModellF==0 || VecModelleF!=null && !bSubModelle)
		{
                  TabInsert=null;
			if(Qry.open("SELECT Modell.aic_modell,modell.aic_stamm,modell.aic_eigenschaft,modell.aic_abfrage,b.*"+g.AU_Memo2("Begriff","b")+g.AU_Tooltip("Begriff","b")+g.AU_Bezeichnung2("Begriff","b")+
                                    ",(select ein from logging where aic_logging=b.aic_logging) Log,b.aic_stammtyp Stt, b.Tod,("+
                                    (CboModul.getSelectedAIC()==0 ? "0":"select min(aic_begriff) from begriff_zuordnung where beg_aic_begriff=b.aic_begriff and aic_begriff="+CboModul.getSelectedAIC())+
                                    ") aic_modul FROM Modell"+g.join("Begriff","b","Modell","Begriff")+
                                    (bModule ? " where (select count(z.aic_begriff) from begriff_zuordnung z join begriff b2 on b2.aic_begriff=z.aic_begriff and b2.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul")+
                                     " where z.beg_aic_begriff=b.aic_begriff)>0":bWeb ? " where b.web=1":bTod ? " where b.Tod=1":VecModelleF!=null ?" where"+g.in("modell.aic_modell", VecModelleF):"")+" order by Log desc"))//+(CbxSortNow.isSelected()?"Log desc":"defbezeichnung")))//WHERE AIC_Mandant="+g.getMandant()))
			{
				//g.TabBegriffgruppen.posInhalt("Kennung","Modell");
				g.fixtestInfo("fillOutModell1:"+iModellF+"/"+VecModelleF);
				for(;!Qry.eof();Qry.moveNext())
				{
					if(CboModul.getSelectedAIC()==0 || CboModul.getSelectedAIC()==Qry.getI("AIC_Modul"))
					{
						TabModell.addInhalt("AIC",Qry.getI("AIC_Modell"));
						TabModell.addInhalt("AIC_Begriff",Qry.getI("AIC_Begriff"));
//                        TabModell.addInhalt("Bezeichnung", Static.beiLeer(Qry.getS("Bezeichnung"),Qry.getS("DefBezeichnung")));
						TabModell.addInhalt("Bezeichnung",Qry.getS("DefBezeichnung"));
						TabModell.addInhalt("Kennung",Qry.getS("Kennung"));
                                                String sPeriode=CboPeriode.getBezeichnung(Qry.getI("AIC_Stamm"));
                        TabModell.addInhalt("Periode","Periode "+sPeriode+((Qry.getL("bits")&Global.cstZRhold)>0?", Zeitraum bleibt":""));
                                                // Modelle füllen
						Vector<Comparable> VecVisible = new Vector<Comparable>();
						Vector<Comparable> VecInvisible = new Vector<Comparable>();
						VecVisible.addElement(Qry.getS("DefBezeichnung"));
						VecVisible.addElement(Qry.getS("Kennung"));
						VecVisible.addElement(sPeriode);
                        VecVisible.addElement(Qry.getInt("AIC_Modell"));
                        VecVisible.addElement(Qry.getD("Log"));
                        VecVisible.addElement(Qry.getI("Stt")==0?"":g.TabStammtypen.getBezeichnungS(Qry.getI("Stt")));
                        VecVisible.addElement(Static.JaNein2(g.isJeder(Qry.getInt("AIC_Begriff"))));
                        VecVisible.addElement(Qry.getI("prog")==0 ? "":CboProg.getBezeichnung(Qry.getI("prog")));
                        VecVisible.addElement(Static.JaNein2((Qry.getL("bits")&Global.cstKnopf)>0));
                        VecVisible.addElement(Module(Qry.getI("AIC_Begriff")));
                        VecVisible.addElement(Qry.getS("Bezeichnung"));
                        VecVisible.addElement(Qry.getS("Kennzeichen"));
                        VecVisible.addElement(Qry.getI("AIC_Begriff"));
                        VecVisible.addElement(Static.JaNein2(Qry.getB("Web")));
                        VecVisible.addElement(g.TabEigenschaften.getBezeichnungS(Qry.getI("AIC_Eigenschaft"))); // Ampel
                        VecVisible.addElement(Qry.getI("aic_Abfrage")==0 ? "":g.getDefBez(g.AbfToBeg(Qry.getI("aic_Abfrage")))); // Abfrage
                        VecVisible.addElement(Static.JaNein2((Qry.getL("bits")&Global.cstEigenG)>0));
						VecInvisible.addElement(Qry.getInt("AIC_Modell"));
						VecInvisible.addElement(Qry.getS("Memo"));
						VecInvisible.addElement(Qry.getInt("AIC_Stamm"));
						VecInvisible.addElement(Qry.getL("bits"));
						VecInvisible.addElement(Qry.getInt("AIC_Begriff"));
						//VecInvisible.addElement(Qry.getInt("AIC_Code"));
						VecInvisible.addElement(Qry.getInt("AIC_Modul"));
						VecInvisible.addElement(Qry.getInt("AIC_Mandant"));
                                                VecInvisible.addElement(Qry.getInt("AIC_Eigenschaft"));
                                                VecInvisible.addElement(Qry.getS("TT"));
						JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,NodRoot);
                                                TabModell.addInhalt("Node",Node);
						Node.setUserData(VecInvisible);
						if(StyModell!=null)
                                                  Node.setStyle(Qry.getB("Tod") ? StyModellTod:StyModell);
						if(iSelect==Qry.getI("AIC_Modell"))
                                                  Static.makeVisible(OutModell,Node);
					}
				}

				Qry.close();
			}
			else
				Static.printError("Modell.fillOutliners(): SQL-Fehler beim Laden der Modelle");
		}
		else
		{
			g.fixtestInfo("fillOutModell2:"+iModellF+"/"+iSelect);
		  Tabellenspeicher TabModelle = new Tabellenspeicher(g,"SELECT b.*,modell.aic_modell,modell.aic_stamm,modell.aic_eigenschaft"+
                      g.AU_Memo2("Begriff","b")+g.AU_Tooltip("Begriff","b")+g.AU_Bezeichnung2("Begriff","b")+
                      ",(select ein from logging where aic_logging=b.aic_logging) Log,null Angelegt,b.aic_stammtyp Stt"+
                      //",("+g.top("aic_begriff from begriff_zuordnung where beg_aic_begriff=b.aic_begriff",1)+") aic_modul"+
                      " FROM Modell"+g.join("Begriff","b","Modell","Begriff"),true);
			Tabellenspeicher Tab = new Tabellenspeicher(g,"select distinct m1.aic_modell von,m2.aic_modell nach from modell m1,befehl2,modell m2 where m1.aic_modell=befehl2.aic_modell and m2.aic_modell=befehl2.mod_aic_modell order by von",true);
			TabInsert = new Tabellenspeicher(g,new String[]{"AIC","Node"});
                        //VecModelle=new Vector();
			Vector VecModelle = TabModelle.getVecSpalte("aic_modell");
			if (iModellF<0)
    			  for(int i=0;i<VecModelle.size();i++)
    			  {
    				TabModelle.posInhalt("AIC_Modell",(Integer)VecModelle.elementAt(i));
    				if(TabModelle.getI("AIC_Modul")==CboModul.getSelectedAIC())
    					insert(Tab,NodRoot,((Integer)VecModelle.elementAt(i)).intValue(),TabModelle,iSelect);
    			  }
			else if (VecModelleF!=null && VecModelleF.size()>1)
				for(int i=0;i<VecModelleF.size();i++)
				  insert(Tab,NodRoot,Sort.geti(VecModelleF, i),TabModelle,0);
			else
			  insert(Tab,NodRoot,iModellF,TabModelle,0);
		}

		OutModell.folderChanged(NodRoot);
		//TabModell.showGrid("TabModell");
		Qry.free();
		g.clockInfo("fillOutModell",lClock);
	}

private String Module(int iAic)
{
  if (TabModule.posInhalt("beg_aic_begriff",iAic))
  {
    String s=g.TabBegriffe.getKennung(TabModule.getI("aic_begriff"));
    for(TabModule.moveNext();!TabModule.out() && TabModule.getI("beg_aic_begriff")==iAic;TabModule.moveNext())
      s+=","+g.TabBegriffe.getKennung(TabModule.getI("aic_begriff"));
    return s;
  }
  else
    return "";
}

private void insert(Tabellenspeicher Tab, JCOutlinerFolderNode NodeParent,int iAIC,Tabellenspeicher TabModelle, int iSelect)
{
	if(bHierachie && TabInsert.posInhalt("AIC",iAIC))
	{
		if(((JCOutlinerFolderNode)TabInsert.getInhalt("Node")).getLevel()==1)
		{
			((JCOutlinerFolderNode)TabInsert.getInhalt("Node")).getParent().removeChild((JCOutlinerFolderNode)TabInsert.getInhalt("Node"));
			((JCOutlinerFolderNode)TabInsert.getInhalt("Node")).setParent(NodeParent);
		}
	}

	if(TabInsert.posInhalt("AIC",iAIC)?NodeParent!=OutModell.getRootNode() && ((Integer)((Vector)NodeParent.getUserData()).elementAt(0)).intValue()!=iAIC && ((JCOutlinerFolderNode)TabInsert.getInhalt("Node")).getLevel()>1:true)
	{
          TabModelle.posInhalt("Aic_Modell",iAIC);
          if (bHierachie || !TabInsert.posInhalt("AIC",iAIC))
          {
            // Modelle füllen
		Vector<Comparable> VecVisible = new Vector<Comparable>();
		Vector<Comparable> VecInvisible = new Vector<Comparable>();
		VecVisible.addElement(TabModelle.getS("DefBezeichnung"));
		VecVisible.addElement(TabModelle.getS("Kennung"));
		VecVisible.addElement(CboPeriode.getBezeichnung(TabModelle.getI("AIC_Stamm")));
        VecVisible.addElement(TabModelle.getInt("AIC_Modell"));
        VecVisible.addElement(TabModelle.getD("Log"));
        VecVisible.addElement(TabModelle.getI("Stt")==0?"":g.TabStammtypen.getBezeichnungS(TabModelle.getI("Stt")));
        VecVisible.addElement(Static.JaNein2(g.isJeder(TabModelle.getInt("AIC_Begriff"))));
        VecVisible.addElement(TabModelle.getI("prog")==0 ? "":CboProg.getBezeichnung(TabModelle.getI("prog")));
        VecVisible.addElement(Static.JaNein2((TabModelle.getL("bits")&Global.cstKnopf)>0));
        VecVisible.addElement(Module(TabModelle.getI("AIC_Begriff")));
        VecVisible.addElement(TabModelle.getS("Bezeichnung"));
        VecVisible.addElement(TabModelle.getS("Kennzeichen"));
        VecVisible.addElement(TabModelle.getI("AIC_Begriff"));
        int iPosB=g.TabBegriffe.getPos("AIC",TabModelle.getI("AIC_Begriff"));
        VecVisible.addElement(iPosB<0 ? "???":Static.JaNein2(g.TabBegriffe.getB(iPosB,"Web")));
        VecVisible.addElement(g.TabEigenschaften.getBezeichnungS(TabModelle.getI("AIC_Eigenschaft"))); // Ampel
        VecVisible.addElement("Abfrage");//Qry.getI("aic_Abfrage")==0 ? "":g.getBegBez(g.AbfToBeg(Qry.getI("aic_Abfrage")))); // Abfrage
        VecVisible.addElement(iPosB<0 ? "???":Static.JaNein2((g.TabBegriffe.getL(iPosB,"bits")&Global.cstEigenG)>0));
        
		VecInvisible.addElement(TabModelle.getInt("AIC_Modell"));
		VecInvisible.addElement(TabModelle.getM("Memo"));
		VecInvisible.addElement(TabModelle.getInt("AIC_Stamm"));
		VecInvisible.addElement(TabModelle.getL("bits"));
		VecInvisible.addElement(TabModelle.getInt("AIC_Begriff"));
		VecInvisible.addElement(Static.Int0);//Qry.getInt("AIC_Modul"));
		VecInvisible.addElement(TabModelle.getInt("AIC_Mandant"));
        VecInvisible.addElement(TabModelle.getInt("AIC_Eigenschaft"));
        VecInvisible.addElement(TabModelle.getM("TT"));
		JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,bHierachie ? NodeParent:(JCOutlinerFolderNode)OutModell.getRootNode());
		TabModell.addInhalt("AIC", TabModelle.getInt("AIC_Modell"));
		TabModell.addInhalt("Bezeichnung", /*Static.beiLeer(TabModelle.getS("Bezeichnung"),*/TabModelle.getS("DefBezeichnung"));
		TabModell.addInhalt("Kennung", TabModelle.getS("Kennung"));
		TabModell.addInhalt("Periode","Periode "+CboPeriode.getBezeichnung(TabModelle.getI("AIC_Stamm"))+((TabModelle.getL("bits")&Global.cstZRhold)>0?", Zeitraum bleibt":""));
		TabModell.addInhalt("Node",Node);
		TabModell.addInhalt("Kennzeichen", TabModelle.getS("Kennzeichen"));
		Node.setUserData(VecInvisible);
		if(StyModell!=null)
			Node.setStyle(TabModelle.getB("Tod") ? StyModellTod:StyModell);

		if(iSelect==TabModelle.getI("AIC_Modell"))
			OutModell.selectNode(Node,null);

		//Node.setState(BWTEnum.FOLDER_CLOSED);

		TabInsert.addInhalt("AIC",iAIC);
		TabInsert.addInhalt("Node",Node);

		for(Tab.posInhalt("von",iAIC);!Tab.eof() && !Tab.out() && iAIC==Tab.getI("von");Tab.moveNext())
		{
			Tab.push();
			insert(Tab,Node,Tab.getI("nach"),TabModelle,iSelect);
			Tab.pop();
		}
          }
	}
}

	private void fillOutliners()
	{
		SQL Qry = new SQL(g);

		int iPosBG=g.TabBegriffgruppen.getPos("Kennung","Modell");
		Image Img = g.LoadImage(g.TabBegriffgruppen.getS(iPosBG,"Filename"));
		if (Img != null)
		{
			StyModell = new JCOutlinerNodeStyle();
			StyModell.setFolderClosedIcon(Img);
			StyModell.setFolderOpenIcon(Img);
			StyModell.setItemIcon(Img);
                        StyModellTod=new JCOutlinerNodeStyle(StyModell);
                        StyModellTod.setForeground(Global.ColLoeschen);
		}

		fillOutModell();


		int iPos=g.getPosBegriff("Show","If");
		Img = g.LoadImage(g.TabBegriffe.getS(iPos,"BildFile"));
		if (Img != null)
		{
			StyIf = new JCOutlinerNodeStyle();
			StyIf.setFolderClosedIcon(Img);
			StyIf.setFolderOpenIcon(Img);
		}
		iPos=g.getPosBegriff("Show","While");
		Img = g.LoadImage(g.TabBegriffe.getS(iPos,"BildFile"));
		if (Img != null)
		{
			StyWhile = new JCOutlinerNodeStyle();
			StyWhile.setFolderClosedIcon(Img);
			StyWhile.setFolderOpenIcon(Img);
		}

                fillBefehle(0);
                if (Static.bDefBezeichnung || iModellF>0)
                  fillSpalten(); // 8.6. ausgeblendet damit es schneller geht

         Qry.free();
 }

private void fillBefehle(int iCode)
 {
	long lClock=Static.get_ms();
   JCOutlinerFolderNode NodRoot = (JCOutlinerFolderNode)OutBedingung.getRootNode();
   NodRoot.removeChildren();
   JCOutlinerFolderNode NodeIf = new JCOutlinerFolderNode(g.getBegriffS("Show", "If"), NodRoot);
   NodeIf.setUserData("If");
   NodeIf.setState(BWTEnum.FOLDER_CLOSED);
   if (StyIf != null)
     NodeIf.setStyle(StyIf);
   JCOutlinerFolderNode NodeWhile = new JCOutlinerFolderNode(g.getBegriffS("Show", "While"), NodRoot);
   NodeWhile.setUserData("While");
   NodeWhile.setState(BWTEnum.FOLDER_CLOSED);
   if (StyWhile != null)
     NodeWhile.setStyle(StyWhile);
   OutAnweisung.setNumColumns(Static.bDefShow ? 4:3);
   NodRoot = (JCOutlinerFolderNode)OutAnweisung.getRootNode();
   NodRoot.removeChildren();
   String sAnzahl = Static.bDefShow && g.Def() ? ",(select count(*) from befehl2 where aic_code=Code.aic_code) Anzahl":"";
   TabCode = new Tabellenspeicher(g,"SELECT AIC_Code AIC,Code.Kennung,Code.AIC_Begriffgruppe Gruppe"+g.AU_Memo("Code")+g.AU_Bezeichnung("Code")+sAnzahl+",null Node,null Node2 FROM Code"+g.join("begriffgruppe","g","Code","begriffgruppe")+" where g.kennung='Bedingung' or Anweisung=1 ORDER BY Gruppe,AIC",true);
		//TabCode.showGrid("Code");

		for(int iPosBG=0;iPosBG<g.TabBegriffgruppen.size();iPosBG++)
		{
			if(g.TabBegriffgruppen.getB(iPosBG,"Anweisung")==true)
			{

				JCOutlinerNodeStyle StyNode = null;
				Image ImgAnw = g.LoadImage(g.TabBegriffgruppen.getS(iPosBG,"Filename"));
				if (ImgAnw != null)
				{
					StyNode = new JCOutlinerNodeStyle();
					StyNode.setFolderClosedIcon(ImgAnw);
					StyNode.setFolderOpenIcon(ImgAnw);
					StyNode.setItemIcon(ImgAnw);
				}

				Vector<Object> VecGruppeVisible = new Vector<Object>();
				VecGruppeVisible.addElement(g.TabBegriffgruppen.getInhalt("Bezeichnung",iPosBG));
				VecGruppeVisible.addElement(g.TabBegriffgruppen.getInhalt("Kennung",iPosBG));
                                VecGruppeVisible.addElement(g.TabBegriffgruppen.getInt(iPosBG,"AIC"));
				JCOutlinerFolderNode NodGruppe = new JCOutlinerFolderNode((Object)VecGruppeVisible,NodRoot);
                                NodGruppe.setUserData(g.TabBegriffgruppen.getInt(iPosBG,"AIC"));
				NodGruppe.setState(BWTEnum.FOLDER_CLOSED);
				if(StyNode!=null)
					NodGruppe.setStyle(StyNode);
				int iAIC_Begriffgruppe=g.TabBegriffgruppen.getI(iPosBG,"AIC");
				TabCode.posInhalt("Gruppe",iAIC_Begriffgruppe);
				while(!TabCode.eof() && iAIC_Begriffgruppe==TabCode.getI("Gruppe"))
				{
                                  // Befehle füllen
					Vector<Comparable> VecVisible = new Vector<Comparable>();
					Vector<Comparable> VecInvisible = new Vector<Comparable>();
					VecVisible.addElement(TabCode.getS("Bezeichnung"));
					VecVisible.addElement(TabCode.getS("Kennung"));
                    VecVisible.addElement(TabCode.getInt("AIC"));
                    if (Static.bDefShow)
                    	VecVisible.addElement(TabCode.getInt("Anzahl"));
					VecInvisible.addElement(new Integer(TabCode.getI("AIC")));
					VecInvisible.addElement(TabCode.getM("Memo"));

					JCOutlinerNode Node = new JCOutlinerNode((Object)VecVisible,NodGruppe);
                                        TabCode.setInhalt("Node",Node);
					Node.setUserData(VecInvisible);
					if(StyNode!=null)
						Node.setStyle(StyNode);
					if (Calc.Bdep.contains(TabCode.getS("Kennung")))
                    	makeBreakStyle(Node, true, Global.ColDep);
					else if (Calc.Bdead.contains(TabCode.getS("Kennung")))
                    	makeBreakStyle(Node, true, Color.RED);
                        if (TabCode.getI("AIC")==iCode)
                        {
                          NodGruppe.setState(BWTEnum.FOLDER_OPEN_ALL);
                          Static.makeVisible(OutAnweisung,Node);
                        }
					TabCode.moveNext();
				}
			}
			else if(g.TabBegriffgruppen.getS(iPosBG,"Kennung").equalsIgnoreCase("Bedingung"))
			{
				int iAIC_Begriffgruppe=g.TabBegriffgruppen.getI(iPosBG,"AIC");
				TabCode.posInhalt("Gruppe",iAIC_Begriffgruppe);
				while(!TabCode.eof() && iAIC_Begriffgruppe==TabCode.getI("Gruppe"))
				{
                                  // Bedingungen füllen
					Vector<Comparable> VecVisible = new Vector<Comparable>();
					Vector<Comparable> VecInvisible = new Vector<Comparable>();
					VecVisible.addElement(TabCode.getS("Bezeichnung"));
					VecVisible.addElement(TabCode.getS("Kennung"));
                    VecVisible.addElement(TabCode.getInt("AIC"));
                    if (TabCode.exists("Anzahl")) //Static.bDefShow && g.Prog())
                    	VecVisible.addElement(TabCode.getInt("Anzahl"));
					VecInvisible.addElement(new Integer(TabCode.getI("AIC")));
					VecInvisible.addElement(TabCode.getM("Memo"));

					JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,NodeIf);
                                        TabCode.setInhalt("Node",Node);
					Node.setUserData(VecInvisible);
					if(StyIf!=null)
						Node.setStyle(StyIf);
					Color Col=null;
					if (Calc.Bdep.contains(TabCode.getS("Kennung")))
                    	Col=Global.ColDep;
					else if (Calc.Bdead.contains(TabCode.getS("Kennung")))
					{
                    	Col=Color.RED;
//                    	g.fixtestError("verbotene Bedingung:" +TabCode.getS("Kennung")+"/"+Node.getLabel());
					}
					if (Col != null)
						makeBreakStyle(Node, true, Col);
					Node = new JCOutlinerFolderNode((Object)VecVisible,NodeWhile);
                                        TabCode.setInhalt("Node2",Node);
					Node.setUserData(VecInvisible);
					if(StyWhile!=null)
						Node.setStyle(StyWhile);
					if (Col != null)
						makeBreakStyle(Node, true, Col);
					TabCode.moveNext();
				}
			}
		}
		OutBedingung.folderChanged(NodRoot);
		OutAnweisung.folderChanged(OutAnweisung.getRootNode());
		g.clockInfo("fillBefehle",lClock);
	}

	@SuppressWarnings("unchecked")
	private void fillSpalten()
	{
          long lClock=Static.get_ms();
          int iAicSp=0;
          JCOutlinerNode NodSp=OutSpalten.getSelectedNode();
          if (NodSp != null && NodSp.getLevel()==4)
            iAicSp=Sort.geti(NodSp.getUserData(),0);
		SQL Qry = new SQL(g);
		JCOutlinerFolderNode NodRoot = (JCOutlinerFolderNode)OutSpalten.getRootNode();
		NodRoot.removeChildren();
                Vector VecAbfragen=null;
                if (TabInsert != null && !bAlleSpalte)
                  VecAbfragen=SQL.getVector("select distinct aic_abfrage from spalte"+g.join2("befehl2","spalte")+" where aic_modell"+Static.SQL_in(TabInsert.getVecSpalte("Aic")),g);
                g.progInfo("VecAbfragen="+VecAbfragen);
                boolean bClose=VecAbfragen==null || VecAbfragen.size()>(g.Prog() ? 5:0); // (iModellF==0 || bAlleSpalte)
		String sSQL="SELECT begriff.aic_code Typ, Begriff.bits,begriff.aic_Stammtyp Stt,begriff.aic_bewegungstyp Erf,begriff.aic_Rolle rol,spalte.aic_spalte,spalte.aic_stammtyp,spalte.bits Spaltenbits,spalte.bits2,spalte.soritiert sortiert,(select count(*) from befehl2 where aic_spalte=spalte.aic_spalte"+
                            (VecAbfragen==null?"":" and "+g.in("aic_modell",TabInsert.getVecSpalte("Aic")))+") Anzahl, spalte.kennung Spalte_Ken,abfrage.aic_abfrage, begriff.kennung Abfrage_Ken "+g.AU_Bezeichnung2("Spalte")+" Spalte_Bez,Begriff.DefBezeichnung Abfrage_Bez FROM Spalte"+g.join("Abfrage","Spalte")+g.join("Begriff","Abfrage")+
                            //" WHERE (spalte.bits2 is null or "+g.bits("spalte.bits2",Abfrage.cstWeg)+"=0) and "
                            " WHERE "+(VecAbfragen==null ? g.bit("Begriff.bits",Abfrage.cstNurModell):"(begriff.aic_logging="+g.getLog()+" and "+g.bit("Begriff.bits",Abfrage.cstNurModell)+" or"+g.in("abfrage.aic_abfrage",VecAbfragen)+")")+" ORDER BY Erf,Stt,Begriff.DefBezeichnung,Begriff.aic_begriff,Spalte.reihenfolge";
                g.progInfo(sSQL);
                if(Qry.open(sSQL))
		{
			Tabellenspeicher TabFixEigenschaft = new Tabellenspeicher(g,"SELECT AIC_Spalte, CASE Richtung WHEN 1 THEN -AIC_Eigenschaft ELSE AIC_Eigenschaft END Eigenschaft FROM FixEigenschaft WHERE AIC_Spalte IS NOT NULL ORDER BY AIC_FixEigenschaft",true);

			Tabellenspeicher TabAbfrage = new Tabellenspeicher(g,new String[] {"AIC","Node"});
			//String sSttKennung="";
			JCOutlinerFolderNode NodPar=null;
			Tabellenspeicher TabKnoten = new Tabellenspeicher(g,new String[]{"Art","Knoten"});
                        TabSpalten.clearAll();
                        //boolean bKZR=false;
                        JCOutlinerNodeStyle StyStd = new JCOutlinerComponent().getDefaultNodeStyle();
                        StyStd.setForeground(Color.BLACK);
                        JCOutlinerNodeStyle StyKZR = new JCOutlinerNodeStyle(StyStd);
                        StyKZR.setForeground(Color.RED);

			for(;!Qry.eof();Qry.moveNext())
			{
                boolean bKZR=false;
				JCOutlinerFolderNode NodAbfrage;
                // Spalten füllen
				Vector<Object> VecVisible;
				Vector<Object> VecInvisible;
                TabAbfrage.posInhalt("AIC",Qry.getI("aic_abfrage"));
                boolean bGruppiert=(Qry.getI("Spaltenbits")&Abfrage.cstGruppiert)>0;
                //boolean bSort=(Qry.getI("bits2")&Abfrage.cstSortDesc)>0;
                int iSort=Qry.getI("sortiert");
				if(!TabAbfrage.out())
                {
                      NodAbfrage = (JCOutlinerFolderNode)TabAbfrage.getInhalt("Node");
                      Vector<Integer> VecPar=(Vector)NodAbfrage.getLabel();
                      VecPar.setElementAt(new Integer(Qry.getI("Anzahl")+(VecPar.elementAt(3)).intValue()),3);
                      if (bGruppiert)
                        VecPar.setElementAt(new Integer(Sort.geti(VecPar,5)+1),5);
                      if (iSort>0)
                        VecPar.setElementAt(new Integer(Sort.geti(VecPar,6)+1),6);
                }
				else
				{
					VecVisible = new Vector<Object>();
					VecInvisible = new Vector<Object>();
					VecVisible.addElement(Static.beiLeer(Qry.getS("Abfrage_Bez"),Qry.getS("Abfrage_Ken")));
                    //int iAic=Qry.getI("aic_abfrage");
                    VecVisible.addElement(Qry.getInt("aic_abfrage"));
                    String sANR=Qry.getI("Erf")==0 || Qry.getI("Stt")==0 ? null:g.getANR_BS(Qry.getI("Erf"),Qry.getI("Stt"));
                    VecVisible.addElement(sANR==null ? Static.sLeer:g.TabEigenschaften.getBezeichnungS(g.getEigANR(Qry.getI("Erf"), sANR)));
                    /*if (Qry.isNull("rol"))
                      VecVisible.addElement(null);
                    else
                      VecVisible.addElement(g.TabRollen.getBezeichnung(Qry.getI("rol")));*/
                    VecVisible.addElement(Qry.getInt("Anzahl"));
                    VecVisible.addElement(Qry.isNull("Stt")?null:g.TabStammtypen.getBezeichnungS(Qry.getI("Stt")));
                    VecVisible.addElement(bGruppiert ? new Integer(1):null);
                    VecVisible.addElement(/*new JaNein(iSort>0));*/iSort>0 ? iSort/*new Integer(1)*/:null);

					long lBits=Qry.getL("bits");
                    VecVisible.addElement(new JaNein((lBits&Abfrage.cstKeinStamm)>0));
                    bKZR=/*Qry.getI("Erf")>0 ?*/ (lBits&Abfrage.cstKeinZeitraum)>0 /*: (lBits&Abfrage.cstKeinStammZeitraum)>0*/;
                    boolean bKR=(lBits&Abfrage.cstKeinRefresh)>0;
                    VecVisible.addElement(new JaNein(bKZR));
                    VecVisible.addElement(new JaNein(bKR));
                    VecVisible.addElement(new JaNein((lBits&Abfrage.cstDistinct)>0));

                    
                    VecInvisible.addElement(new Integer(Qry.getI("aic_abfrage")));

					NodAbfrage = new JCOutlinerFolderNode((Object)VecVisible);
					NodAbfrage.setUserData(VecInvisible);
                                        NodAbfrage.setStyle(bKZR || bKR ? StyKZR:StyStd);
					if (bClose)
					  NodAbfrage.setState(BWTEnum.FOLDER_CLOSED);

					TabAbfrage.addInhalt("AIC",new Integer(Qry.getI("aic_abfrage")));
					TabAbfrage.addInhalt("Node",NodAbfrage);

					char Char=' ';
					int iAICArt=0;

					if(Qry.getI("Stt")==0 && Qry.getI("Erf")==0)
					{
						Char='A';
						iAICArt=Qry.getI("Typ");
					}
					else if(Qry.getI("Erf")==0)
					{
                                          //if (Qry.getI("rol")==0)
                                          {
                                            Char = 'S';
                                            iAICArt = Qry.getI("Stt");
                                          }
                                          /*else
                                          {
                                            Char = 'R';
                                            iAICArt = Qry.getI("rol");
                                          }*/
					}
					else
					{
						Char='B';
						iAICArt=Qry.getI("Erf");
					}

					if(TabKnoten.posInhalt("Art",""+Char+iAICArt))
					{
						NodPar = (JCOutlinerFolderNode)TabKnoten.getInhalt("Knoten");
					}
					else
					{
						//g.TabBegriffe.push();
						JCOutlinerFolderNode NodArt = null;
						if(TabKnoten.posInhalt("Art",""+Char))
						{
							NodArt = (JCOutlinerFolderNode)TabKnoten.getInhalt("Knoten");
						}
						else
						{
							NodArt = new JCOutlinerFolderNode(Char=='A'?g.getBegriffS("Show","Allgemein"):Char=='S'?g.getBezeichnung("Tabellenname","STAMMTYP"):g.getBezeichnung("Tabellenname","BEWEGUNGSTYP"),NodRoot);
							if (bClose)
							  NodArt.setState(BWTEnum.FOLDER_CLOSED);
							TabKnoten.addInhalt("Art",""+Char);
							TabKnoten.addInhalt("Knoten",NodArt);
						}
	                    VecVisible = new Vector<Object>();
	                    VecVisible.addElement(Char=='A'&&iAICArt==0?Static.sKein:Char+" "+(Char=='A'?g.getDefBez(iAICArt):g.getBezeichnungS(Char=='S'?"Stammtyp":Char=='B'?"Bewegungstyp":"???",iAICArt)));
	                    VecVisible.addElement(""+Char+iAICArt);
	                    VecVisible.addElement(null);
	                    VecVisible.addElement(Static.Int0);
	                    VecVisible.addElement(null);
	                    VecVisible.addElement(null);
	                    VecVisible.addElement(null);
	                    VecVisible.addElement(null);
	                    VecVisible.addElement(null);
	                    //VecVisible.addElement(new Integer(0));
						NodPar = new JCOutlinerFolderNode((Object)VecVisible,NodArt);
                        Vector<Object> VecInvisible2 = new Vector<Object>();
                        VecInvisible2.addElement(""+Char);
                        VecInvisible2.addElement(new Integer(iAICArt));
                        NodPar.setUserData(VecInvisible2);
                        if (bClose)
                        	NodPar.setState(BWTEnum.FOLDER_CLOSED);
						TabKnoten.addInhalt("Art",""+Char+iAICArt);
						TabKnoten.addInhalt("Knoten",NodPar);
                        if (Static.bDefBezeichnung)
                        {
                          Image ImgElement = !Static.bDefBezeichnung ? null : Char == 'S' ? g.getSttGif(Qry.getI("Stt")) : Char == 'B' ? g.getBewGif(Qry.getI("Erf")) : null;
                          if (ImgElement != null)
                          {
                            JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
                            StyFolder.setFolderClosedIcon(ImgElement);
                            StyFolder.setFolderOpenIcon(ImgElement);
                            NodPar.setStyle(StyFolder);
                          }
                        }
						//g.TabBegriffe.pop();
					}

					NodPar.addNode(NodAbfrage);

				}
                                Vector<Integer> VecPar=(Vector)NodPar.getLabel();
                                //if ((VecPar.elementAt(1)).intValue()<Qry.getI("aic_abfrage"))
                                //  VecPar.setElementAt(Qry.getInt("aic_abfrage"),1);
                                VecPar.setElementAt(new Integer(Qry.getI("Anzahl")+(VecPar.elementAt(3)).intValue()),3);
                                if (bGruppiert)
                                  VecPar.setElementAt(new Integer(Sort.geti(VecPar,5)+1),5);
                                if (iSort>0)
                                  VecPar.setElementAt(new Integer(Sort.geti(VecPar,6)+1),6);
				String sSpalte_Bez = Qry.getS("Spalte_Bez").trim();
				// String sSpalte_Ken = Qry.getS("Spalte_Ken").trim();
				Integer iAIC_Spalte = new Integer(Qry.getI("aic_spalte"));
				Vector<Object> VecFixEigenschaft = null;
				//if(sSpalte_Bez.equals("") && sSpalte_Ken.equals(""))
				{
					VecFixEigenschaft = new Vector<Object>();

					for(TabFixEigenschaft.posInhalt("AIC_Spalte",iAIC_Spalte);!TabFixEigenschaft.out() && TabFixEigenschaft.getI("AIC_Spalte")==iAIC_Spalte.intValue();TabFixEigenschaft.moveNext())
						VecFixEigenschaft.addElement(TabFixEigenschaft.getInhalt("Eigenschaft"));
				}
				sSpalte_Bez = CbxOriginalBez.isSelected() || sSpalte_Bez.equals("") /*&& sSpalte_Ken.equals("")*/ ?
                                    All_Unlimited.Hauptmaske.Abfrage.getEigenschaftBezeichnung(g,VecFixEigenschaft):sSpalte_Bez;
                                    //!sSpalte_Bez.equals("")?sSpalte_Bez:sSpalte_Ken;
				TabSpalten.addInhalt("AIC",iAIC_Spalte);
				TabSpalten.addInhalt("Bezeichnung",sSpalte_Bez);
				TabSpalten.addInhalt("Abfrage",((Vector)NodAbfrage.getLabel()).elementAt(0));
				TabSpalten.addInhalt("Stt",((Vector)NodPar.getLabel()).elementAt(0));

				VecVisible = new Vector<Object>();
				VecInvisible = new Vector<Object>();
				VecVisible.addElement(sSpalte_Bez);
                                VecVisible.addElement(Qry.getInt("aic_spalte"));
                                int iEig=Sort.geti(VecFixEigenschaft.elementAt(VecFixEigenschaft.size()-1));
                                int iPosE=g.TabEigenschaften.getPos("Aic", iEig);
                                int iSttE=iPosE<0 ? 0:g.TabEigenschaften.getI(iPosE,"aic_stammtyp");
                                String sDatentyp=iPosE<0 ? null:g.TabEigenschaften.getS(iPosE,"Datentyp");//g.getDatentyp();
                                if (iPosE<0)
                                  g.fixtestError("Datentyp fehlt bei "+Qry.getInt("aic_spalte")+"/"+VecFixEigenschaft);
                                TabSpalten.addInhalt("Datentyp",sDatentyp);
                                TabSpalten.addInhalt("kZR",new Boolean(bKZR));
                                VecVisible.addElement(sDatentyp);
                                VecVisible.addElement(Qry.getInt("Anzahl"));
                                VecVisible.addElement(Qry.isNull("aic_stammtyp") && iSttE==0 ? Static.sLeer:g.TabStammtypen.getBezeichnungS(Qry.isNull("aic_stammtyp") ? iSttE:Qry.getI("aic_stammtyp")));
                                VecVisible.addElement(iSort>0 ? new JaNein(bGruppiert):null);
                                VecVisible.addElement(iSort>0 ? iSort:null);
                                VecVisible.addElement(null);
                                VecVisible.addElement(null);
                                VecVisible.addElement(null);
                                VecVisible.addElement(null);                             
                                //VecVisible.addElement(null);
				VecInvisible.addElement(iAIC_Spalte);
                                if (sDatentyp.equals("Filler"))
                                  TabSpalten.addInhalt("Node",null);
                                else
                                {
                                  JCOutlinerNode Node = new JCOutlinerNode((Object)VecVisible, NodAbfrage);
                                  TabSpalten.addInhalt("Node", Node);
                                  Node.setUserData(VecInvisible);
                                  int iPos= g.getPosBegriff("Datentyp",sDatentyp);
                                  Image Gif = iPos<0 ? null:g.LoadImage(g.TabBegriffe.getS(iPos,"BildFile"));
                                  if (Gif != null)
                                  {
                                    JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
                                    StyFolder.setItemIcon(Gif);
                                    Node.setStyle(StyFolder);
                                    Node.getStyle().setForeground((Qry.getI("bits2")&Abfrage.cstWeg)>0 ? Color.LIGHT_GRAY:bKZR?Color.RED:Color.BLACK);
                                    if (Aufruf.VecSpalten!=null && Aufruf.VecSpalten.contains(iAIC_Spalte))
                                      makeBreakStyle(Node, true, Global.ColBreakpoint);
                                  }
                                }
			}
			OutSpalten.folderChanged(NodRoot);

		}
		else
			Static.printError("Modell.fillSpalten(): SQL-Fehler beim Laden der Spalten");
		Qry.free();
                if (iAicSp>0)
                {
                  TabSpalten.posInhalt("Aic", iAicSp);
                  JCOutlinerNode Nod = (JCOutlinerNode)TabSpalten.getInhalt("Node");
                  openUp(Nod);
                  OutSpalten.folderChanged(OutSpalten.getRootNode());
                  Static.makeVisible(OutSpalten, Nod);
                }
                g.clockInfo("fillSpalten",lClock);
		//if (g.Prog())
		//	TabSpalten.showGrid();
	}

	private boolean Neu()
	{
		boolean bOk = false;
		int iPosBG=g.TabBegriffgruppen.getPos("KENNUNG","Modell");
                int iAIC_Begriff = NodeModell==null ? 0:((Integer)((Vector)NodeModell.getUserData()).elementAt(4)).intValue();
                if (Message.KennungWarnung(g,EdtKennung.getText(),iAIC_Begriff,g.TabBegriffgruppen.getI(iPosBG,"AIC"),FrmNeu))
                  return false;
                String sFehler=EdtKennung.Fehler(true);
                if (sFehler != null)
                {
                	new Message(Message.WARNING_MESSAGE, thisJFrame(),g,10).showDialog("Kennung"+sFehler);
                    return false;
                }
                /*if (EdtKennung.isNull())
                {
                  new Message(Message.ERROR_MESSAGE, null,g).showDialog("KennungLeer");
                  return false;
                }
                else if(SQL.exists(g,"select aic_modell from  Modell JOIN Begriff WHERE begriff.aic_begriff<> "+iAIC_Begriff+" and Begriff.Kennung='"+EdtKennung.getText()+"' AND Begriff.AIC_Mandant="+g.getMandant()))
                {
                  new Message(Message.WARNING_MESSAGE,null,g).showDialog("KennungVorhanden");
                  return false;
                }*/

		if(NodeModell==null)
		{
          SQL Qry = new SQL(g);
					Qry.add("Kennung",EdtKennung.getText());
					Qry.add("Kennzeichen",EdtKennzeichen.getText());
					Qry.add("Alias",EdtAlias.getText());
          Qry.add("DefBezeichnung",EdtDefBezeichnung.getText());
          Qry.add("Bildname",EdtBildname.getText());
          Qry.add("Homepage",EdtFarbe.getText());
          Qry.add0("Prog",CboProg.getSelectedAIC());
          Qry.add0("AIC_Stammtyp",CboStammtyp.getSelectedAIC());
          Qry.add0("AIC_Bewegungstyp",CboBewegungstyp.getSelectedAIC());
					Qry.add("AIC_Begriffgruppe",g.TabBegriffgruppen.getI(iPosBG,"AIC"));
//					Qry.add("AIC_Mandant",CboMandant.getSelectedAIC());
					long iBits=(CbxPeriode.isSelected()?Global.cstPeriodeM:0)+(CbxEF.isSelected()?Global.cstEF:0)+(CbxZRhold.isSelected()?Global.cstZRhold:0)
                                            +(CbxMenge.isSelected()?Global.cstMenge:0)+(CbxKeineFrage.isSelected()?Global.cstKeineFrage:0)+(CbxRefresh.isSelected()?Global.cstRefreshM:0)
                                            +(CbxOhneStamm.isSelected()?Global.cstOhneStamm:0)+(CbxRecalc.isSelected()?Global.cstRecalc:0)
                                            +(CbxAServer.isSelected()?Global.cstAServer:0)//+(CbxAntwort.isSelected()?Global.cstAntwort:0)
                                            +(CbxKnopf.isSelected()?Global.cstKnopf:0)+(CbxSave.isSelected()?Global.cstSave:0)+(CbxThread.isSelected()?Global.cstThread:0)
                                            //+(CbxnachSave.isSelected()?Global.cstnachSave:0)
                                            +(CbxBew.isSelected()?Global.cstBew:0)+(CbxCommit.isSelected()?Global.cstCommit:0)+(CbxAbbruch.isSelected()?Global.cstAbbruch:0)
                                            +(CbxUseQry.isSelected()?Global.cstUseQry:0)+(CbxMitSo.isSelected()?Global.cstMitSo:0)+(CbxJokerStt.isSelected()?Global.cstJokerStt:0)
                                            +(CbxErgebnis.isSelected()?Global.cstErgebnis:0)+(CbxMassExport.isSelected()?Global.cstMassExport:0)
                                            +(CbxDruckZR.isSelected()?Global.cstDruckZR:0)+(CbxDelJS.isSelected()?Global.cstDelJS:0)+(CbxNbAServer.isSelected()?Global.cstNbAServer:0)
                                            +(CbxDefImport.isSelected()?Global.cstDefImport:0)+(CbxMDialog.isSelected()?Global.cstMDialog:0)+(CbxEigenG.isSelected()?Global.cstEigenG:0)
                                            +(CbxAmpel.isSelected()?Global.cstAmpel:0)+(CbxClean.isSelected()?Global.cstClean:0)+(CbxNurTest.isSelected()?Global.cstNurTest:0)+(CbxSperre.isSelected()?Global.cstMSperre:0)
                                            +(CbxAmpelWeb.isSelected()?Global.cstAmpelWeb:0)+(CbxWeiter.isSelected()?Global.cstWeiter:0);
					Qry.add("bits",iBits);
          if (CbxJeder.isSelected())
            Qry.add("Jeder",1);
          if (CbxCombo.isSelected())
            Qry.add("Combo",1);
          if (CbxWeb.isSelected())
              Qry.add("Web",1);
          Qry.add("aic_logging",g.getLog());
					iAIC_Begriff = Qry.insert("Begriff",true);
          if (NodKopie == null)
              g.SaveDefVerlauf(iAIC_Begriff,"neu");
					g.putTabBegriffe(g.TabBegriffgruppen.getI(iPosBG,"AIC"),iAIC_Begriff,EdtKennung.getText(),EdtBezeichnung.getText(),EdtDefBezeichnung.getText(),BtnBild.getFilename(),0,null,-1,CboProg.getSelectedAIC(),
                                                         CboStammtyp.getSelectedAIC(),CboBewegungstyp.getSelectedAIC(),-1,iBits,CbxCombo.isSelected(),CbxWeb.isSelected(),0,null,CbxTod.isSelected(),EdtTooltip.getValue(),
                                                         EdtKennzeichen.getText(),EdtAlias.getText(),BtnBildFX.getFilename(),null,true);
          g.setJeder(new Integer(iAIC_Begriff),CbxJeder.isSelected());
          Qry.add("AIC_Begriff",iAIC_Begriff);
					//if(CboPeriode.getSelectedAIC()>0)
          Qry.add0("AIC_Stamm",CboPeriode.getSelectedAIC());
          Qry.add0("AIC_Eigenschaft",CboEigenschaft.getSelectedAIC());
          Qry.add0("AIC_Abfrage",CboAbfrage.getSelectedAIC());
          Qry.add0("Farbe",CboAbfrage2.getSelectedAIC());
          Qry.add0("Max_B",SB_MaxB.getIntValue());
					//if(CboPeriode2.getSelectedAIC()>0)
					//	Qry.add("AIC_Code",CboPeriode2.getSelectedAIC());
					int iAIC_Modell=Qry.insert("Modell",true);
					//g.progInfo("Neues Modell"+iAIC_Modell+"/"+iAIC_Begriff);
          Qry.free();
          int iAic=CboAbfWebDialog.getSelectedAIC();
          if (iAic>0)
          {
            int iWD=g.getCodeAic("Zuordnungsart", "WebDialog");
            g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff,Art) values ("+iAIC_Begriff+","+g.AbfToBeg(iAic)+","+iWD+")");
          }
          iAic=CboAbfWebAmpel.getSelectedAIC();
          if (iAic>0)
          {
            int iWA=g.getCodeAic("Zuordnungsart", "WebAmpel");
            g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff,Art) values ("+iAIC_Begriff+","+g.AbfToBeg(iAic)+","+iWA+")");
          }
          if (g.TabModelle != null)
          {
            g.TabModelle.addInhalt("aic_modell",iAIC_Modell);
            g.TabModelle.addInhalt("aic_begriff",iAIC_Begriff);
            if (CboPeriode.getSelectedAIC() == 0)
            {
              g.TabModelle.addInhalt("iPeriode", null);
              g.TabModelle.addInhalt("sPeriode", null);
            }
            else
            {
              g.TabModelle.addInhalt("iPeriode", CboPeriode.getSelectedAIC());
              g.TabModelle.addInhalt("sPeriode", CboPeriode.getSelectedKennung());
            }
            g.TabModelle.addInhalt("bits",iBits);
            g.TabModelle.addInhalt("Bezeichnung",EdtDefBezeichnung.getText());
            g.TabModelle.addInhalt0("aic_eigenschaft",CboEigenschaft.getSelectedAIC());
            g.TabModelle.addInhalt("AIC_Abfrage",CboAbfrage.getSelectedAIC());
            g.TabModelle.addInhalt("Max_B",SB_MaxB.getIntValue());
            g.TabModelle.addInhalt("Farbe",CboAbfrage2.getSelectedAIC());
            g.TabModelle.addInhalt("Abf",null);
          }

					if(iAIC_Modell!=-1)
					{
						//if(g.TabTabellenname.posInhalt("Kennung","BEGRIFF"))
						{
							if(EdtMemo.Modified())
								g.setMemo(EdtMemo.getValue(),"Modell",Global.iTabBegriff,iAIC_Begriff,0);
                                                        if(EdtTooltip.Modified())
								g.setTooltip(EdtTooltip.getValue(),Global.iTabBegriff,iAIC_Begriff,0);
                                                        String sBez=EdtBezeichnung.getText();
                                                        if (!sBez.equals("") && !sBez.equals(EdtDefBezeichnung.getText()))
                                                          g.setBezeichnung("",sBez,Global.iTabBegriff,iAIC_Begriff,0);
                                                        if (BtnBild.getFilename()!=null && !BtnBild.getFilename().equals(""))
                                                          g.setImage(BtnBild.getFilename(),Global.iTabBegriff,iAIC_Begriff,Global.iSpSw);
                                                        if (BtnBildFX.getFilename()!=null && !BtnBildFX.getFilename().equals(""))
                                                            g.setImage(BtnBildFX.getFilename(),Global.iTabBegriff,iAIC_Begriff,Global.iSpFX);
							//g.setBezeichnung("",EdtBezeichnung.getText(),g.TabTabellenname.getI("AIC"),iAIC_Begriff,0);
                                                        //g.testInfo("new -> Date="+new Date());
							Vector<Object> VecVisible = new Vector<Object>();
							Vector<Comparable> VecInvisible = new Vector<Comparable>();
							VecVisible.addElement(EdtDefBezeichnung.getText());
							VecVisible.addElement(EdtKennung.getText());
							VecVisible.addElement(CboPeriode.getSelectedItem());
                            VecVisible.addElement(new Integer(iAIC_Modell));
                            VecVisible.addElement(new java.sql.Date(new Date().getTime()));
                            VecVisible.addElement(CboStammtyp.getSelectedBezeichnung());
                            VecVisible.addElement(Static.JaNein2(CbxJeder.isSelected()));
                            VecVisible.addElement(CboProg.getSelectedBezeichnung());
                            VecVisible.addElement(Static.JaNein2(CbxKnopf.isSelected()));
                            VecVisible.addElement(null); // Module
                            VecVisible.addElement(EdtBezeichnung.getText());
                            VecVisible.addElement(EdtKennzeichen.getText());
                            VecVisible.addElement(iAIC_Begriff); // Aic
                            VecVisible.addElement(Static.JaNein2(CbxWeb.isSelected())); // Web
                            VecVisible.addElement(CboEigenschaft.Cbo.getSelectedBezeichnung()); // Ampel
                            VecVisible.addElement(CboAbfrage.Cbo.getSelectedBezeichnung()); // Abfrage
                            VecVisible.addElement(Static.JaNein2(CbxEigenG.isSelected())); // lokG
                            
							VecInvisible.addElement(iAIC_Modell); // 0
                            VecInvisible.addElement(EdtMemo.getValue());       // 1
							VecInvisible.addElement(CboPeriode.getSelectedAIC()); // 2
							VecInvisible.addElement((CbxPeriode.isSelected()?Global.cstPeriodeM:0)+(CbxEF.isSelected()?Global.cstEF:0)+(CbxZRhold.isSelected()?Global.cstZRhold:0)
                                                          +(CbxMenge.isSelected()?Global.cstMenge:0)+(CbxKeineFrage.isSelected()?Global.cstKeineFrage:0)+(CbxRefresh.isSelected()?Global.cstRefreshM:0)
                                                          +(CbxOhneStamm.isSelected()?Global.cstOhneStamm:0)+(CbxRecalc.isSelected()?Global.cstRecalc:0)+(CbxAServer.isSelected()?Global.cstAServer:0)
                                                          /*+(CbxAntwort.isSelected()?Global.cstAntwort:0)*/+(CbxKnopf.isSelected()?Global.cstKnopf:0)+(CbxSave.isSelected()?Global.cstSave:0)+(CbxThread.isSelected()?Global.cstThread:0)
                                                          //+(CbxnachSave.isSelected()?Global.cstnachSave:0)
                                                          +(CbxBew.isSelected()?Global.cstBew:0)+(CbxCommit.isSelected()?Global.cstCommit:0)+(CbxAbbruch.isSelected()?Global.cstAbbruch:0)
                                                          +(CbxUseQry.isSelected()?Global.cstUseQry:0)+(CbxMitSo.isSelected()?Global.cstMitSo:0)+(CbxJokerStt.isSelected()?Global.cstJokerStt:0)
                                                          +(CbxErgebnis.isSelected()?Global.cstErgebnis:0)+(CbxMassExport.isSelected()?Global.cstMassExport:0)
                                                          +(CbxDruckZR.isSelected()?Global.cstDruckZR:0)+(CbxDelJS.isSelected()?Global.cstDelJS:0)+(CbxNbAServer.isSelected()?Global.cstNbAServer:0)
                                                          +(CbxDefImport.isSelected()?Global.cstDefImport:0)+(CbxMDialog.isSelected()?Global.cstMDialog:0)+(CbxEigenG.isSelected()?Global.cstEigenG:0)
                                                          +(CbxAmpel.isSelected()?Global.cstAmpel:0)+(CbxClean.isSelected()?Global.cstClean:0)+(CbxNurTest.isSelected()?Global.cstNurTest:0)+(CbxSperre.isSelected()?Global.cstMSperre:0)
                                                          +(CbxAmpelWeb.isSelected()?Global.cstAmpelWeb:0)+(CbxWeiter.isSelected()?Global.cstWeiter:0));
							VecInvisible.addElement(iAIC_Begriff); // 4
							VecInvisible.addElement(0);            // 5
							VecInvisible.addElement(null);//CboMandant.getSelectedAIC()); // 6
                            VecInvisible.addElement(CboEigenschaft.getSelectedAIC()); // 7
                            VecInvisible.addElement(EdtTooltip.getValue());     // 8
							//VecInvisible.addElement(new Integer(CboPeriode2.getSelectedAIC()));
							JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutModell.getRootNode());
							Node.setUserData(VecInvisible);
							if(StyModell!=null)
								Node.setStyle(StyModell);
							OutModell.folderChanged(OutModell.getRootNode());
							if (!bGeaendert)
							{
								OutModell.selectNode(Node,null);
								OutModell.makeNodeVisible(Node);
								if (NodKopie != null)
                                                                {
                                                                  Laden(NodKopie,false,null,0);
                                                                  Speichern(iAIC_Modell);
                                                                }
                                                                Laden(Node,false,null,0);
							}
						}
						bOk=true;
					}
					else
						Static.printError("Modell.Neu(): Fehler beim Anlegen eines Modells!!!");
		}
		else
		{
			//g.progInfo("UserData="+NodeModell.getUserData());
			int iAIC_Modell = ((Integer)((Vector)NodeModell.getUserData()).elementAt(0)).intValue();
			//int iAIC_Begriff = ((Integer)((Vector)NodeModell.getUserData()).elementAt(4)).intValue();
			//int iAIC_Modul= ((Integer)((Vector)NodeModell.getUserData()).elementAt(5)).intValue();
                        SQL Qry = new SQL(g);
			Qry.add("Kennung",EdtKennung.getText());
			Qry.add("Kennzeichen",EdtKennzeichen.getText());
			Qry.add("Alias",EdtAlias.getText());
      Qry.add("DefBezeichnung",EdtDefBezeichnung.getText());
      Qry.add("Bildname",EdtBildname.getText());
      Qry.add("Homepage",EdtFarbe.getText());
      Qry.add0("Prog",CboProg.getSelectedAIC());
      Qry.add0("AIC_Stammtyp",CboStammtyp.getSelectedAIC());
      Qry.add0("AIC_Bewegungstyp",CboBewegungstyp.getSelectedAIC());
			Qry.add("AIC_Begriffgruppe",g.TabBegriffgruppen.getI(iPosBG,"AIC"));
//			Qry.add("AIC_Mandant",CboMandant.getSelectedAIC());
                        long iBits=(CbxPeriode.isSelected()?Global.cstPeriodeM:0)+(CbxEF.isSelected()?Global.cstEF:0)+(CbxZRhold.isSelected()?Global.cstZRhold:0)+
                            (CbxMenge.isSelected()?Global.cstMenge:0)+(CbxKeineFrage.isSelected()?Global.cstKeineFrage:0)+(CbxRefresh.isSelected()?Global.cstRefreshM:0)+
                            (CbxOhneStamm.isSelected()?Global.cstOhneStamm:0)+(CbxRecalc.isSelected()?Global.cstRecalc:0)+(CbxAServer.isSelected()?Global.cstAServer:0)+
                            /*(CbxAntwort.isSelected()?Global.cstAntwort:0)*/+(CbxKnopf.isSelected()?Global.cstKnopf:0)+(CbxSave.isSelected()?Global.cstSave:0)+(CbxThread.isSelected()?Global.cstThread:0)+
                            //(CbxnachSave.isSelected()?Global.cstnachSave:0)
                            (CbxBew.isSelected()?Global.cstBew:0)+(CbxCommit.isSelected()?Global.cstCommit:0)+(CbxAbbruch.isSelected()?Global.cstAbbruch:0)
                            +(CbxUseQry.isSelected()?Global.cstUseQry:0)+(CbxMitSo.isSelected()?Global.cstMitSo:0)+(CbxJokerStt.isSelected()?Global.cstJokerStt:0)
                            +(CbxErgebnis.isSelected()?Global.cstErgebnis:0)+(CbxMassExport.isSelected()?Global.cstMassExport:0)
                            +(CbxDruckZR.isSelected()?Global.cstDruckZR:0)+(CbxDelJS.isSelected()?Global.cstDelJS:0)+(CbxNbAServer.isSelected()?Global.cstNbAServer:0)
                            +(CbxDefImport.isSelected()?Global.cstDefImport:0)+(CbxMDialog.isSelected()?Global.cstMDialog:0)+(CbxEigenG.isSelected()?Global.cstEigenG:0)
                            +(CbxAmpel.isSelected()?Global.cstAmpel:0)+(CbxClean.isSelected()?Global.cstClean:0)+(CbxNurTest.isSelected()?Global.cstNurTest:0)+(CbxSperre.isSelected()?Global.cstMSperre:0)
                            +(CbxAmpelWeb.isSelected()?Global.cstAmpelWeb:0)+(CbxWeiter.isSelected()?Global.cstWeiter:0);
//            g.fixtestError("Sperre="+CbxSperre.isSelected()+", bits="+Long.toHexString(iBits));
			Qry.add("bits",iBits);
      if (CbxJeder.isSelected())
        Qry.add("Jeder",1);
      else
        Qry.add2("Jeder",null);
      if (CbxCombo.isSelected())
        Qry.add("Combo",1);
      else
        Qry.add2("Combo",null);
      if (CbxWeb.isSelected())
          Qry.add("Web",1);
        else
          Qry.add2("Web",null);
      Qry.add0("Tod",CbxTod.isSelected());
			Qry.add("aic_logging",g.getLog());
			Qry.update("Begriff",iAIC_Begriff);
			g.SaveDefVerlauf(iAIC_Begriff,"Parameter");
			Qry.add("AIC_Begriff",iAIC_Begriff);
			Qry.add0("AIC_Stamm",CboPeriode.getSelectedAIC());
      Qry.add0("AIC_Eigenschaft",CboEigenschaft.getSelectedAIC());
      Qry.add0("AIC_Abfrage",CboAbfrage.getSelectedAIC());
      Qry.add0("Farbe",CboAbfrage2.getSelectedAIC());
      Qry.add0("Max_B",SB_MaxB.getIntValue());
			/*if(CboPeriode2.getSelectedAIC()!=0)
				Qry.add("AIC_Code",CboPeriode2.getSelectedAIC());
			else
				Qry.add("AIC_Code","");*/
			if(Qry.update("Modell",iAIC_Modell))// && g.TabTabellenname.posInhalt("Kennung","BEGRIFF"))
			{
        if (CboAbfWebDialog.Cbo.Modified())
        {
          int iWD=g.getCodeAic("Zuordnungsart", "WebDialog");
          g.exec("delete from begriff_zuordnung where aic_begriff="+iAIC_Begriff+" and Art="+iWD);
          int iAic=CboAbfWebDialog.getSelectedAIC();
    	    if (iAic>0)
    		    g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff,Art) values ("+iAIC_Begriff+","+g.AbfToBeg(iAic)+","+iWD+")");
        }
        if (CboAbfWebAmpel.Cbo.Modified())
        {
          int iWA=g.getCodeAic("Zuordnungsart", "WebAmpel");
          g.exec("delete from begriff_zuordnung where aic_begriff="+iAIC_Begriff+" and Art="+iWA);
          int iAic=CboAbfWebAmpel.getSelectedAIC();
    	    if (iAic>0)
    		    g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff,Art) values ("+iAIC_Begriff+","+g.AbfToBeg(iAic)+","+iWA+")");
        }
				if(EdtMemo.Modified())
					g.setMemo(EdtMemo.getValue(),"Modell",Global.iTabBegriff,iAIC_Begriff,0);
                                if(EdtTooltip.Modified())
                                  g.setTooltip(EdtTooltip.getValue(),Global.iTabBegriff,iAIC_Begriff,0);
                                g.putTabBegriffe(g.TabBegriffgruppen.getI(iPosBG,"AIC"),iAIC_Begriff,EdtKennung.getText(),EdtBezeichnung.getText(),EdtDefBezeichnung.getText(),BtnBild.getFilename(),0,null,-1,CboProg.getSelectedAIC(),
                                                 CboStammtyp.getSelectedAIC(),CboBewegungstyp.getSelectedAIC(),0/* Rolle */,iBits,CbxCombo.isSelected(),CbxWeb.isSelected(),0,null/* Hotkey */,CbxTod.isSelected(),EdtTooltip.getValue(),
                                                		 EdtKennzeichen.getText(),EdtAlias.getText(),BtnBildFX.getFilename(),null,false);
                                g.setJeder(new Integer(iAIC_Begriff),CbxJeder.isSelected());
                                int iPos=g.TabModelle == null ? -1:g.TabModelle.getPos("aic_modell",iAIC_Modell);
                                if (iPos>=0)
                                {
                                  if (CboPeriode.getSelectedAIC() == 0)
                                  {
                                    g.TabModelle.setInhalt(iPos,"iPeriode", null);
                                    g.TabModelle.setInhalt(iPos,"sPeriode", null);
                                  }
                                  else
                                  {
                                    g.TabModelle.setInhalt(iPos,"iPeriode", CboPeriode.getSelectedAIC());
                                    g.TabModelle.setInhalt(iPos,"sPeriode", CboPeriode.getSelectedKennung());
                                  }
                                  g.TabModelle.setInhalt(iPos,"bits",iBits);
                                  g.TabModelle.setInhalt(iPos,"Bezeichnung",EdtDefBezeichnung.getText());
                                  g.TabModelle.setInhalt0(iPos,"aic_eigenschaft",CboEigenschaft.getSelectedAIC());
                                  g.TabModelle.setInhalt0("Max_B",SB_MaxB.getIntValue());
                                  g.TabModelle.setInhalt0("Farbe",CboAbfrage2.getSelectedAIC());
                                }
                                String sBez=EdtBezeichnung.getText();
                                if (!sBez.equals("") && !sBez.equals(EdtDefBezeichnung.getText()))
                                  g.setBezeichnung("",sBez,Global.iTabBegriff,iAIC_Begriff,0);
                                if (BtnBild.Modified())
                                  g.setImage(BtnBild.getFilename(),Global.iTabBegriff,iAIC_Begriff,Global.iSpSw);
                                if (BtnBildFX.Modified())
                                    g.setImage(BtnBildFX.getFilename(),Global.iTabBegriff,iAIC_Begriff,Global.iSpFX);
				//g.setBezeichnung((String)((Vector)NodeModell.getLabel()).elementAt(0),EdtBezeichnung.getText(),g.TabTabellenname.getI("AIC"),iAIC_Begriff,0);
                                //g.testInfo("change -> Date="+new Date());
				Vector<Object> VecVisible = new Vector<Object>();
				Vector<Object> VecInvisible = new Vector<Object>();
				VecVisible.addElement(EdtDefBezeichnung.getText());
				VecVisible.addElement(EdtKennung.getText());
				VecVisible.addElement(CboPeriode.getSelectedItem());
                                VecVisible.addElement(new Integer(iAIC_Modell));
                                VecVisible.addElement(new java.sql.Date(new Date().getTime()));
                                VecVisible.addElement(CboStammtyp.getSelectedBezeichnung());
                                VecVisible.addElement(Static.JaNein2(CbxJeder.isSelected()));
                                VecVisible.addElement(CboProg.getSelectedBezeichnung());
                                VecVisible.addElement(Static.JaNein2(CbxKnopf.isSelected()));
                                VecVisible.addElement(null);
                                VecVisible.addElement(EdtBezeichnung.getText());
                                VecVisible.addElement(EdtKennzeichen.getText());
                                VecVisible.addElement(iAIC_Begriff); // Aic
                                VecVisible.addElement(Static.JaNein2(CbxWeb.isSelected())); // Web
                                VecVisible.addElement(CboEigenschaft.Cbo.getSelectedBezeichnung()); // Ampel
                                VecVisible.addElement(CboAbfrage.Cbo.getSelectedBezeichnung()); // Abfrage
                                VecVisible.addElement(Static.JaNein2(CbxEigenG.isSelected())); // lokG
				VecInvisible.addElement(new Integer(iAIC_Modell));
				VecInvisible.addElement(EdtMemo.getValue());
				VecInvisible.addElement(new Integer(CboPeriode.getSelectedAIC()));
				VecInvisible.addElement((CbxPeriode.isSelected()?Global.cstPeriodeM:0)+(CbxEF.isSelected()?Global.cstEF:0)+(CbxZRhold.isSelected()?Global.cstZRhold:0)+
                                  (CbxMenge.isSelected()?Global.cstMenge:0)+(CbxKeineFrage.isSelected()?Global.cstKeineFrage:0)+(CbxRefresh.isSelected()?Global.cstRefreshM:0)+
                                  (CbxOhneStamm.isSelected()?Global.cstOhneStamm:0)+(CbxRecalc.isSelected()?Global.cstRecalc:0)+(CbxAServer.isSelected()?Global.cstAServer:0)+
                                  /*(CbxAntwort.isSelected()?Global.cstAntwort:0)+*/(CbxKnopf.isSelected()?Global.cstKnopf:0)+(CbxSave.isSelected()?Global.cstSave:0)+(CbxThread.isSelected()?Global.cstThread:0)+
                                  //(CbxnachSave.isSelected()?Global.cstnachSave:0)+
                                  (CbxBew.isSelected()?Global.cstBew:0)+(CbxCommit.isSelected()?Global.cstCommit:0)+(CbxAbbruch.isSelected()?Global.cstAbbruch:0)
                                                      +(CbxUseQry.isSelected()?Global.cstUseQry:0)+(CbxMitSo.isSelected()?Global.cstMitSo:0)+(CbxJokerStt.isSelected()?Global.cstJokerStt:0)
                                                      +(CbxErgebnis.isSelected()?Global.cstErgebnis:0)+(CbxMassExport.isSelected()?Global.cstMassExport:0)
                                                      +(CbxDruckZR.isSelected()?Global.cstDruckZR:0)+(CbxDelJS.isSelected()?Global.cstDelJS:0)+(CbxNbAServer.isSelected()?Global.cstNbAServer:0)
                                                      +(CbxDefImport.isSelected()?Global.cstDefImport:0)+(CbxMDialog.isSelected()?Global.cstMDialog:0)+(CbxEigenG.isSelected()?Global.cstEigenG:0)
                                                      +(CbxAmpel.isSelected()?Global.cstAmpel:0)+(CbxClean.isSelected()?Global.cstClean:0)+(CbxNurTest.isSelected()?Global.cstNurTest:0)+(CbxSperre.isSelected()?Global.cstMSperre:0)
                                                      +(CbxAmpelWeb.isSelected()?Global.cstAmpelWeb:0)+(CbxWeiter.isSelected()?Global.cstWeiter:0));
				VecInvisible.addElement(new Integer(iAIC_Begriff));
				VecInvisible.addElement(new Integer(0));
				VecInvisible.addElement(null);//CboMandant.getSelectedAIC()));
                                VecInvisible.addElement(new Integer(CboEigenschaft.getSelectedAIC()));
                                VecInvisible.addElement(EdtTooltip.getValue());     // 8
				//VecInvisible.addElement(new Integer(CboPeriode2.getSelectedAIC()));
				NodeModell.setLabel(VecVisible);
				NodeModell.setUserData(VecInvisible);
                                /*if (CbxTod.isSelected())
                                {
                                  NodeModell.getParent().removeChild(NodeModell);
                                }*/
                                NodeModell.setStyle(CbxTod.isSelected() ? StyModellTod:StyModell);
				OutModell.folderChanged(OutModell.getRootNode());

				TxtMemoModell.setText(EdtMemo.getValue());
				bOk=true;
			}
                        Qry.free();
		}
		return(bOk);
	}

	private Tabellenspeicher Info(JCOutlinerFolderNode Nod,boolean bShow)
        {
	  //JCOutlinerFolderNode NodModell = (JCOutlinerFolderNode)OutModell.getSelectedNode();
	  int iAIC_Modell = ((Integer)((Vector)Nod.getUserData()).elementAt(0)).intValue();
	  int iAIC_Begriff = ((Integer)((Vector)Nod.getUserData()).elementAt(4)).intValue();
	  Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct 'Modell' Art,defbezeichnung,begriff.kennung,begriff.aic_begriff"+g.bei("befehl2.MBits",Calc.THREAD,"Thread")+" from begriff"+
                                                    g.join2("modell","begriff")+", befehl2 where modell.aic_modell=befehl2.aic_modell and befehl2.mod_aic_modell="+iAIC_Modell,true);
          new Tabellenspeicher(g,"select 'Abfrage' Art,defbezeichnung,begriff.kennung,begriff.aic_begriff,null Thread from begriff"+
                               g.join2("abfrage","begriff")+" where abfrage.aic_modell="+iAIC_Modell+" or abfrage.mod_aic_modell="+iAIC_Modell,true).addTo(Tab,true);
          new Tabellenspeicher(g,"select 'Formular' Art,defbezeichnung,begriff.kennung,begriff.aic_begriff,null Thread from darstellung"+
                               g.join("formular","darstellung")+g.join("begriff","formular")+" where darstellung.aic_begriff="+iAIC_Begriff,true).addTo(Tab,true);
          new Tabellenspeicher(g,"select 'Formular' Art,defbezeichnung,begriff.kennung,begriff.aic_begriff,null Thread from formular"+
                               g.join("begriff","formular")+" where formular.aic_modell="+iAIC_Modell,true).addTo(Tab,true);
          new Tabellenspeicher(g, "select 'Modul' Art, defBezeichnung,kennung,begriff.aic_begriff,null Thread from begriff join begriff_zuordnung z on z.aic_begriff=begriff.aic_begriff"+
              " and aic_begriffgruppe=" +g.TabBegriffgruppen.getAic("Modul") + " and z.beg_aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
          new Tabellenspeicher(g, "select 'Druck' Art, defBezeichnung,kennung,begriff.aic_begriff,null Thread from begriff join begriff_zuordnung z on z.aic_begriff=begriff.aic_begriff"+
              " and aic_begriffgruppe=" +g.TabBegriffgruppen.getAic("Druck") + " and z.beg_aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
		//String s1 = Qry.list("begriff.kennung","begriff"+SQL.join2("modell","begriff")+", befehl where modell.aic_modell=befehl.aic_modell and befehl.mod_aic_modell="+iAIC_Modell);
		//String s2 = Qry.list("begriff.kennung","begriff"+SQL.join2("abfrage","begriff")+SQL.join("modell","abfrage")+" where modell.aic_modell="+iAIC_Modell);
		//String s3 = Qry.list("begriff.kennung","darstellung"+SQL.join("formular","darstellung")+SQL.join("begriff","formular")+" where darstellung.aic_begriff="+iAIC_Begriff);
		//String sGes = (s1.equals(" ")?"":"\nModell:"+s1)+(s2.equals(" ")?"":"\nAbfragen:"+s2)+(s3.equals(" ")?"":"\nFormular:"+s3);
          if (bShow)
          {
            Tab.showGrid(Sort.gets(Nod.getLabel()),thisFrame);
            Tab.Grid.getOutliner().addMouseListener(new MouseListener()
            {
              public void mousePressed(MouseEvent ev)
              {}

              public void mouseClicked(MouseEvent ev)
              {
                if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
                {
                  //selectAbschnitt(Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(), 0));
                  int iModellMom = g.BegriffToModell(Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(), 3));
                  int iPos = iModellMom == 0 ? -1 : TabModell.getPos("AIC", iModellMom); //g.BegriffToModell(
                  if (iPos >= 0)
                  {
                    ((JTabbedPane)OutModell.getParent().getParent().getParent()).setSelectedIndex(0);
                    JCOutlinerNode Nod = (JCOutlinerNode)TabModell.getInhalt("Node", iPos);
                    openUp(Nod);
                    Static.makeVisible(OutModell, Nod);
                  }
                }
              }

              public void mouseEntered(MouseEvent ev)
              {}

              public void mouseExited(MouseEvent ev)
              {}

              public void mouseReleased(MouseEvent ev)
              {}
            });
          }
          return Tab;
        }
	
	private void checkCboAbfrage()
	{
		boolean bMD=CbxMDialog.isSelected();
		if (bMD==bCAF)
		{
//			g.fixtestError("fill CboAbfrage:"+(bMD ? "ModellDialog":"Filter"));
			if (bMD)
				CboAbfrage.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("abits2",Abfrage.cstModellDlg),"Abfrage",true);
			else
				CboAbfrage.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("bits",Abfrage.cstFilter),"Abfrage",true);
			bCAF=!bMD;
		}
	}

        private void BtnBearbeiten(boolean bKopie)
	{
          EdtKennung.setEditable(bKopie);
          //g.progInfo("BtnBearbeiten");
          NodeModell = (JCOutlinerFolderNode)OutModell.getSelectedNode();
          NodKopie = bKopie ? (JCOutlinerFolderNode)OutModell.getSelectedNode():null;
          Vector VecVisible = (Vector)NodeModell.getLabel();
          Vector VecInvisible = (Vector)NodeModell.getUserData();
          int iModell=Sort.geti(NodeModell.getUserData(),0);
          int iBegriff=Sort.geti(NodeModell.getUserData(),4);//g.ModellToBegriff(iModell);
          if (bKopie) NodeModell=null;
          //g.progInfo("VecVisible:"+VecVisible);
          //g.progInfo("VecInvisible:"+VecInvisible);

          Static.centerComponent(FrmNeu,thisFrame);
          EdtKennung.setText(bKopie ?"":(String)VecVisible.elementAt(1));
          EdtKennzeichen.setText((String)VecVisible.elementAt(11));
          EdtAlias.setText(g.TabBegriffe.getS_AIC("Alias", iBegriff));
          String sDefBez=(bKopie ? "Kopie ":"")+(String)VecVisible.elementAt(0);
          EdtDefBezeichnung.setText(sDefBez);
          EdtBildname.setText(iBegriff<=0 ? "":SQL.getString(g, "select bildname from begriff where aic_begriff=?",""+iBegriff));
          EdtFarbe.setText(iBegriff<=0 ? "":SQL.getString(g, "select homepage from begriff where aic_begriff=?",""+iBegriff));
          int iPos=iBegriff>0 ? g.TabBegriffe.getPos("Aic",iBegriff):-1;
          String sBez=iPos<0 ? "":g.TabBegriffe.getS(iPos,"Bezeichnung");
          EdtBezeichnung.setText(sDefBez.equals(sBez) ? "":sBez);
          String sBild= iPos<0 ? null:g.TabBegriffe.getS(iPos,"BildFile");
          BtnBild.setValue(g.LoadImage(sBild),sBild);
          String sBildFX= iPos<0 ? null:g.TabBegriffe.getS(iPos,"BildFX");
          BtnBildFX.setValue(g.LoadImage(sBildFX),sBildFX);
          CboProg.setSelectedAIC(iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Prog"));
          CboStammtyp.setSelectedAIC(iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Stt"));
          CboBewegungstyp.setSelectedAIC(iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Erf"));
          iMBits=Tabellenspeicher.getl(VecInvisible.elementAt(3));
          CbxMDialog.setSelected((iMBits & Global.cstMDialog)>0);
          checkCboAbfrage();
          CboAbfrage.setSelectedAIC(SQL.getInteger(g,"select aic_abfrage from modell where aic_modell=?",0,""+iModell));
          CboAbfrage2.setSelectedAIC(SQL.getInteger(g,"select farbe from modell where aic_modell=?",0,""+iModell));
          int iWD=g.getCodeAic("Zuordnungsart", "WebDialog");
          int iWA=g.getCodeAic("Zuordnungsart", "WebAmpel");
          CboAbfWebDialog.setSelectedAIC(SQL.getInteger(g,"select aic_abfrage from abfrage join begriff_zuordnung z on z.beg_aic_begriff=abfrage.aic_begriff join begriff b on z.beg_aic_begriff=b.aic_begriff and z.aic_begriff="+iBegriff+" and z.Art="+iWD));
          CboAbfWebAmpel.setSelectedAIC(SQL.getInteger(g,"select aic_abfrage from abfrage join begriff_zuordnung z on z.beg_aic_begriff=abfrage.aic_begriff join begriff b on z.beg_aic_begriff=b.aic_begriff and z.aic_begriff="+iBegriff+" and z.Art="+iWA));
          SB_MaxB.setValue(SQL.getInteger(g,"select MAX_B from modell where aic_modell=?",0,""+iModell));
          CbxCombo.setSelected(iPos<0 ? false:g.TabBegriffe.getB(iPos,"Combo"));
          CbxJeder.setSelected(iPos<0 ? false: g.isJeder(new Integer(iBegriff)));
          CbxWeb.setSelected(iPos<0 ? false:g.TabBegriffe.getB(iPos,"Web"));
          CboPeriode.setSelectedAIC(Sort.geti(VecInvisible,2));
          CboEigenschaft.setSelectedAIC(Sort.geti(VecInvisible,7));
          //int iBits=((Integer)VecInvisible.elementAt(3)).intValue();
          CbxPeriode.setSelected((iMBits & Global.cstPeriodeM)>0);
          CbxEF.setSelected((iMBits & Global.cstEF)>0);
          CbxZRhold.setSelected((iMBits & Global.cstZRhold)>0);
          CbxMenge.setSelected((iMBits & Global.cstMenge)>0);
          CbxKeineFrage.setSelected((iMBits & Global.cstKeineFrage)>0);
          CbxRefresh.setSelected((iMBits & Global.cstRefreshM)>0);
          CbxOhneStamm.setSelected((iMBits & Global.cstOhneStamm)>0);
          CbxRecalc.setSelected((iMBits & Global.cstRecalc)>0);
          CbxAServer.setSelected((iMBits & Global.cstAServer)>0);
          //CbxAntwort.setSelected((iBits & Global.cstAntwort)>0);
          CbxKnopf.setSelected((iMBits & Global.cstKnopf)>0);
          CbxSave.setSelected((iMBits & Global.cstSave)>0);
          //CbxnachSave.setSelected((iMBits & Global.cstnachSave)>0);
          CbxThread.setSelected((iMBits & Global.cstThread)>0);
          CbxBew.setSelected((iMBits & Global.cstBew)>0);
          CbxCommit.setSelected((iMBits & Global.cstCommit)>0);
          CbxAbbruch.setSelected((iMBits & Global.cstAbbruch)>0);
          CbxUseQry.setSelected((iMBits & Global.cstUseQry)>0);
          CbxMitSo.setSelected((iMBits & Global.cstMitSo)>0);
          CbxJokerStt.setSelected((iMBits & Global.cstJokerStt)>0);
          CbxDelJS.setSelected((iMBits & Global.cstDelJS)>0);
          CbxNbAServer.setSelected((iMBits & Global.cstNbAServer)>0);
          CbxDefImport.setSelected((iMBits & Global.cstDefImport)>0);
          
          CbxEigenG.setSelected((iMBits & Global.cstEigenG)>0);
          CbxAmpel.setSelected((iMBits & Global.cstAmpel)>0);
          CbxErgebnis.setSelected((iMBits & Global.cstErgebnis)>0);
          CbxMassExport.setSelected((iMBits & Global.cstMassExport)>0);
          CbxDruckZR.setSelected((iMBits & Global.cstDruckZR)>0);
          CbxClean.setSelected((iMBits & Global.cstClean)>0);
          CbxSperre.setSelected((iMBits & Global.cstMSperre)>0);
          CbxNurTest.setSelected((iMBits & Global.cstNurTest)>0);
          CbxAmpelWeb.setSelected((iMBits & Global.cstAmpelWeb)>0);
          CbxWeiter.setSelected((iMBits & Global.cstWeiter)>0);
          //CbxTod.setVisible(true);
          CbxTod.setSelected(g.TabBegriffe.getB(iPos,"Tod"));
          EdtMemo.setText((String)VecInvisible.elementAt(1));
          EdtTooltip.setText((String)VecInvisible.elementAt(8));

          LblBegriff.setText(""+iBegriff);
          LblModell.setText(""+iModell);
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,importzeit,(select ein from logging where aic_logging=begriff.aic_logging) angelegt,"+
            "(select kennung from benutzer join logging on benutzer.aic_benutzer=logging.aic_benutzer where logging.aic_logging=begriff.aic_logging) von from begriff where aic_begriff="+iBegriff,true);
          LblAngelegt.setText(Tab.getS("angelegt"));
          LblVon.setText(Tab.getS("von"));
          LblImportzeit.setText(Tab.getS("Importzeit"));
//          CboMandant.setSelectedAIC(((Integer)VecInvisible.elementAt(6)).intValue());
          FrmNeu.setTitle(g.getBegriffS("Show",bKopie?"Kopie":"Bearbeiten"));
          FrmNeu.setVisible(true);
          thisJFrame().setEnabled(false);
	}

	private void BtnNeu()
	{
	  //	CbxSynchron.setSelected(false);
	  /*CbxSyncBefehl.setSelected(false);
	  CbxSyncSpalte.setSelected(false);
          CbxSyncBits.setSelected(false);*/
	  EdtKennung.setEditable(true);
	  NodeModell=null;
	  CbxPeriode.setSelected(false);
	  CbxEF.setSelected(false);
	  CbxZRhold.setSelected(false);
	  CbxMenge.setSelected(false);
	  CbxKeineFrage.setSelected(false);
	  CbxRefresh.setSelected(false);
	  CbxOhneStamm.setSelected(false);
	  CbxRecalc.setSelected(false);
	  CbxAServer.setSelected(false);
	  //CbxAntwort.setSelected(false);
	  CbxKnopf.setSelected(false);
	  CbxSave.setSelected(false);
	  //CbxnachSave.setSelected(false);
	  CbxThread.setSelected(false);
	  CbxBew.setSelected(false);
	  CbxCommit.setSelected(false);
	  CbxAbbruch.setSelected(false);
          CbxUseQry.setSelected(false);
          CbxMitSo.setSelected(false);
          CbxJokerStt.setSelected(false);
          CbxDelJS.setSelected(false);
          CbxNbAServer.setSelected(false);
          CbxDefImport.setSelected(false);
          CbxMDialog.setSelected(false);
          CbxEigenG.setSelected(false);
          CbxAmpel.setSelected(false);
          CbxErgebnis.setSelected(false);
          CbxMassExport.setSelected(false);
          CbxDruckZR.setSelected(false);
          CbxClean.setSelected(false);
          CbxSperre.setSelected(false);
          CbxNurTest.setSelected(false);
          CbxAmpelWeb.setSelected(false);
          CbxWeiter.setSelected(false);
//	  CboMandant.setSelectedAIC(g.getMandant());
	  CboPeriode.setSelectedKennung("offen");
	  CboEigenschaft.setSelectedAIC(0);
          CbxTod.setSelected(false);
          CbxJeder.setSelected(true);
          CbxWeb.setSelected(false);
          //CbxTod.setVisible(false);
	  Static.centerComponent(FrmNeu,thisFrame);
	  FrmNeu.setTitle(g.getBegriffS("Show","Neu"));
	  FrmNeu.setVisible(true);
	  thisJFrame().setEnabled(false);
	}

	private void Loeschen()
	{
	  JCOutlinerFolderNode NodModell = (JCOutlinerFolderNode)OutModell.getSelectedNode();
	  if (NodModell==null || NodModell.getLevel()==0)
	    return;
	  int iAIC_Modell = ((Integer)((Vector)NodModell.getUserData()).elementAt(0)).intValue();
	  int iAIC_Begriff = ((Integer)((Vector)NodModell.getUserData()).elementAt(4)).intValue();
	  //g.progInfo("Lösche Modell"+iAIC_Modell+"/"+iAIC_Begriff);
	  Tabellenspeicher Tab=Info(NodModell,false);
		if(Tab.isEmpty())
		{
			String[] s = new String[] {Sort.gets(NodModell.getLabel())};
			if(new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",s)==Message.YES_OPTION)
			{
			  SQL Qry = new SQL(g);

				if(/*Qry.exec("DELETE FROM Bezeichnung FROM Bezeichnung JOIN Tabellenname WHERE Tabellenname.Kennung='Begriff' AND AIC_Fremd="+iAIC_Begriff) &&
				   Qry.exec("DELETE FROM Daten_Memo FROM Daten_Memo JOIN Tabellenname WHERE Tabellenname.Kennung='Begriff' AND AIC_Fremd="+iAIC_Begriff) &&
				   Qry.exec("DELETE FROM Daten_Bild FROM Daten_Bild JOIN Tabellenname WHERE Tabellenname.Kennung='Begriff' AND AIC_Fremd="+iAIC_Begriff) &&*/
                                   Qry.deleteFrom("Befehl","Befehl WHERE MOD_AIC_Modell="+iAIC_Modell)>-1 &&
				   Qry.deleteFrom("Befehl","Befehl WHERE AIC_Modell="+iAIC_Modell)>-1 &&
				   Qry.deleteFrom("Modell","Modell WHERE AIC_Modell="+iAIC_Modell)>-1 &&
				   Qry.exec("DELETE FROM Begriff_Zuordnung WHERE Beg_AIC_Begriff="+iAIC_Begriff) &&
				   Qry.exec("DELETE FROM bew_Begriff WHERE AIC_Begriff="+iAIC_Begriff) &&
				   Qry.deleteFrom("Begriff","Begriff WHERE AIC_Begriff="+iAIC_Begriff,Global.iTabBegriff)>-1)
				{
                                  g.TabBegriffe.clearInhaltS("AIC",new Integer(iAIC_Begriff));
					NodModell.getParent().removeChild(NodModell);
					OutModell.folderChanged(OutModell.getRootNode());
				}
				else
					Static.printError("Modell.Loeschen(): Fehler beim Löschen!!!");
			  Qry.free();
			}
		}
		else
			new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,(JFrame)thisFrame,g).showDialog("BereitsVerwendet",new String[] {Sort.gets(NodModell.getLabel())},Tab);
	}

        private void EditModell()
        {
          if (bGeaendert)
          {
            if (new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("verwerfen", new String[] {})  == Message.YES_OPTION)
              checkSperre();
            else
              return;
          }
//          if (((JCOutlinerFolderNode)OutModell.getSelectedNode()).getLevel()>0)
            Laden(((JCOutlinerFolderNode)OutModell.getSelectedNode()).getLevel()>0 ? (JCOutlinerFolderNode)OutModell.getSelectedNode():null,false,null,0);
        }

	@SuppressWarnings("unchecked")
	private JCOutlinerNode Laden(JCOutlinerNode NodeRoot,boolean bZurueck,JCOutliner rOutBuild,int iDef)
	{
		if (iDef==0 && rOutBuild==null && !bZurueck && NodeRoot==null && !bRefresh)
		{
//			g.fixtestError("prüfe Laden "+(NodeRoot==null ? "null":NodeRoot.getLabel())+":"+iDef+"/"+bZurueck+"/"+(rOutBuild==null)+"/ Befehl="+iBefehl);
			int iM=Sort.geti(OutBuild.getRootNode().getUserData(),0);
			int iB=SQL.getInteger(g, "select min(aic_befehl) from befehl2 where aic_modell="+iM);
//			g.fixtestError("iM="+iM+", iB="+iB+", iBlast="+iBlast);
			if (iB==iBlast)
			{
//				g.fixtestError("nicht Laden, da iM="+iM+", iB="+iB+", iBlast="+iBlast);
				return null;
			}
			else
				g.fixtestError("doch Laden, da iM="+iM+", iB="+iB+", iBlast="+iBlast);
			
		}
//		else
//			g.fixtestError("Laden "+(NodeRoot==null ? "null":NodeRoot.getLabel())+":"+iDef+"/"+bZurueck+"/"+(rOutBuild==null)+"/ Befehl="+iBefehl);
		bRefresh=false;
		bGeladen=false;
          JCOutlinerNode NodSelect=null;
//          if (iDef>0)
//        	  iBefehl=0;
          boolean bSel=false;
          boolean bNormal=rOutBuild==null && iDef<=0;
          if (bNormal)
            rOutBuild=OutBuild;
          if (iDef<0)
          {
        	  iBefehl=-iDef;
        	  NodeRoot=null;
          }
          else if (NodeRoot == null)
          {
            NodeRoot = rOutBuild.getRootNode();
            JCOutlinerNode Nod=rOutBuild.getSelectedNode();
            //g.progInfo("Laden:"+Nod+(Nod != null ?"/"+Nod.getUserData():""));
            if (Nod != null && Nod.getUserData() != null && (((Vector)Nod.getUserData()).size()<8 || Sort.geti(Nod.getUserData(),7)==0))
              Nod=Nod.getParent();
            if (iDef==0)
            	iBefehl=Nod==null || Nod.getUserData()==null ? 0:Sort.geti(Nod.getUserData(),7);
          }
          else if(bZurueck)
                  iBefehl=Tabellenspeicher.geti(StaBefehl.pop());
          else if (bNormal)
	      {
	          StkHistory.push(NodeRoot);
	          JCOutlinerNode Nod=rOutBuild.getSelectedNode();
	          if (/*rOutBuild.getRootNode().getChildren() != null &&*/ Nod==null)
	            Nod=rOutBuild.getRootNode();
	          StaBefehl.push(Nod==null || Nod.getUserData()==null ? Static.Int0:((Vector)Nod.getUserData()).elementAt(7));
	      }
                int iSpalte=0;
                int iCode=0;
                Vector<Object> VecSpalte=null;
                if (CbxColSpalte.isSelected())
                {
                  JCOutlinerNode NodeSpalte = OutSpalten.getSelectedNode();
                  if (NodeSpalte.getLevel() == 4)
                    iSpalte = Tabellenspeicher.geti(((Vector)NodeSpalte.getUserData()).elementAt(0));
                  else if (NodeSpalte.getLevel() == 3)
                  {
                    VecSpalte=new Vector<Object>();
                    Vector VecNodes =NodeSpalte.getChildren();
                    for(int i=0;VecNodes!=null && i<VecNodes.size();i++)
                      VecSpalte.addElement(((Vector)((JCOutlinerNode)VecNodes.elementAt(i)).getUserData()).elementAt(0));
                  }
                }
                if (CbxColBefehl.isSelected())
                {
                  JCOutlinerNode NodeBefehl = OutAnweisung.getSelectedNode();
                  if (NodeBefehl==null || NodeBefehl.getLevel()==0)
                    NodeBefehl = OutBedingung.getSelectedNode();
                  if (NodeBefehl.getLevel() == 2)
                    iCode = Tabellenspeicher.geti(((Vector)NodeBefehl.getUserData()).elementAt(0));
                }
                //g.progInfo("StaBefehl="+StaBefehl+", iBefehl="+iBefehl);
                int iModell=NodeRoot==null ? SQL.getInteger(g, "select aic_modell from befehl where aic_befehl="+iBefehl):Sort.geti(NodeRoot.getUserData(), 0);//((Integer)VecInvisible.elementAt(0)).intValue();
                iBlast=SQL.getInteger(g, "select min(aic_befehl) from befehl2 where aic_modell="+iModell);
                iLast=g.ModellToBegriff(iModell);
                Vector<String> VecVisible = null;
                if (NodeRoot==null)
                {
                	VecVisible = new Vector<String>();
                	VecVisible.addElement(g.getDefBez(iLast));
                }
                else if (NodeRoot.getLabel() instanceof String)
                {
                	g.fixtestError("NodeRoot ist String:"+NodeRoot.getLabel());
                }
                else
                {
                  VecVisible = new Vector<String>((Vector)NodeRoot.getLabel());
                  if (VecVisible.size()>6)
                      VecVisible.setElementAt(null,6);
                    if (VecVisible.size()>7)
                      VecVisible.setElementAt(null,7);
                }
                //g.progInfo("             VecVisible="+VecVisible+"/"+NodeRoot.getLabel());
                //for (int i=0;i<VecVisible.size();i++)
                //  g.progInfo("                 "+i+"="+VecVisible.elementAt(0));
                Vector<Object> VecInvisible = new Vector<Object>();
                /*if (VecVisible.size()>2)
                  VecVisible.setElementAt(null,2);
                else
                  VecVisible.addElement(null);
                if (VecVisible.size()>5)
                  VecVisible.setElementAt(null,5);*/

                
		VecInvisible.addElement(iModell);//((Vector)NodeRoot.getUserData()).elementAt(0));
		VecInvisible.addElement(null);
		VecInvisible.addElement("Modell");
        VecInvisible.addElement(null);
        VecInvisible.addElement(null);
        VecInvisible.addElement(null);
        VecInvisible.addElement(null);
        VecInvisible.addElement(null);
        VecInvisible.addElement(null);
        VecInvisible.addElement(null);
        JCOutlinerFolderNode NodeModell = new JCOutlinerFolderNode(VecVisible);
		NodeModell.setUserData(VecInvisible);
		if (NodeRoot != null)
			NodeModell.setStyle(NodeRoot.getStyle());
		if (bNormal)
		  FomBuild.setTitle("Modell "+NodeModell);
		if (rOutBuild != null)
		{
	        rOutBuild.setVisible(false);
			rOutBuild.setRootNode(NodeModell);
			rOutBuild.setRootVisible(true);
		}
                
                //g.testInfo("VecInvisible="+VecInvisible);
//                iLast=g.ModellToBegriff(iModell);
                iLastLog=SQL.getInteger(g,"select aic_logging from begriff where aic_begriff="+iLast);
		Tabellenspeicher TabLoad = new Tabellenspeicher(g,"SELECT hide"+g.AU_Memo("Befehl")+",MBits,AIC_Befehl,Bef_AIC_Befehl,Bef2_AIC_Befehl,AIC_Spalte,Eingabe,Var,"+
                  "Mod_AIC_Modell,Befehl.AIC_Code, Begriffgruppe.AIC_Begriffgruppe,Begriffgruppe.Kennung,Begriffgruppe.Anweisung,null NodeJa,null NodeNein FROM Befehl LEFT OUTER"+
                  g.join("Code","befehl")+" LEFT OUTER"+g.join("Begriffgruppe","Code")+" WHERE AIC_Modell="+iModell+" and "+(iDef>0 ? "aic_defverlauf="+iDef:"def_aic_defverlauf is null")+" ORDER BY AIC_Befehl",true);
		int iAicDiff=TabLoad.getI("AIC_Befehl")-1;
                for(;!TabLoad.eof();TabLoad.moveNext())
		{
			String sArt = TabLoad.getB("Anweisung")?"Anweisung":TabLoad.getS("Kennung");
			if(sArt.equals("Bedingung"))
				sArt = (TabLoad.getI("MBits")&Calc.SCHLEIFE)>0?"While":"If";
			Integer iMod_AIC_Modell = (Integer)TabLoad.getInhalt("MOD_AIC_Modell");
                        // Einzelbefehle füllen
			VecVisible = new Vector<String>();
			VecInvisible = new Vector<Object>();
                        if (TabLoad.getB("hide") && (TabLoad.getI("MBits")&Calc.HIDE)==0)
                        {
                          //g.defInfo("setzte hide nach bei Zeile "+(TabLoad.getI("AIC_Befehl")-iAicDiff));
                          TabLoad.setInhalt("MBits",TabLoad.getI("MBits")+Calc.HIDE);
                        }
			/* 0 */VecInvisible.addElement(iMod_AIC_Modell!=null?iMod_AIC_Modell:TabLoad.getInhalt("AIC_Code"));
			/* 1 */VecInvisible.addElement(TabLoad.getInhalt("AIC_Spalte"));
			/* 2 */VecInvisible.addElement(iMod_AIC_Modell==null?sArt:"Modell");
			/* 3 */VecInvisible.addElement(g.encodeMath(TabLoad.getInhalt("Eingabe")));
			/* 4 */VecInvisible.addElement(TabLoad.getM("Memo"));
                        long iMBits=TabLoad.getL("MBits");
//            g.fixtestInfo("Laden:"+TabLoad.getI("AIC_Befehl")+"->"+iMBits);
            /* 5 */VecInvisible.addElement(iMBits);//new Boolean(TabLoad.getB("hide")));
            /* 6 */VecInvisible.addElement(null);//sArt.equals("While") ? null:TabLoad.getS("Var"));
            /* 7 */VecInvisible.addElement(TabLoad.getInhalt("AIC_Befehl"));
            /* 8 */VecInvisible.addElement(null);
            /* 9 */VecInvisible.addElement(TabLoad.getS("Var"));
			//String sBez = "";
                        boolean bZ=CbxZusammen.isSelected();
                        //boolean bAic=CbxNurAic.isSelected();
                        int iPosC=-1;
			if(iMod_AIC_Modell!=null)
			{
				if(TabModell.posInhalt("AIC",iMod_AIC_Modell))
				{
					VecVisible.addElement(TabModell.getS("Bezeichnung"));
					VecVisible.addElement(TabModell.getS("Kennung"));
                    VecVisible.addElement(null);
                    VecVisible.addElement(null);
                    VecVisible.addElement(null);
                    VecVisible.addElement(TabModell.getS("Periode"));
                    VecVisible.addElement(null);
                    VecVisible.addElement(null);
                    //VecVisible.addElement(TabModell.getS("Kennzeichen"));
				}
			}
			else
			{
                iPosC=g.TabCodes.getPos("AIC",TabLoad.getInhalt("AIC_Code"));
				if(iPosC>=0)
				{
					if (NodSelect==null && (iMBits&Calc.CHANGE)>0)
						bSel=true;
                                  VecVisible.addElement(g.TabCodes.getS(iPosC,"Bezeichnung")+(bZ?((iMBits&Calc.CHANGE)==0 ? " ":"*")+((iMBits&Calc.SUM)==0 ? "":"∑")+((iMBits&Calc.GRUPPE)==0 ? "":"G")+
                                		  ((iMBits&Calc.KSZR)==0 ? "":"kS")+((iMBits&Calc.KBZR)==0 ? "":"kB")+((iMBits&Calc.SAVE)==0 ? "":"s")+((iMBits&Calc.SPALTE)==0 ? "":"+")+((iMBits&Calc.REG)==0 ? "":"R")+((iMBits&Calc.ALLE)==0 ? "":"A")+((iMBits&Calc.THREAD)==0 ? "":"T"):""));
                                  VecVisible.addElement(g.TabCodes.getS(iPosC,"Kennung"));
//                   if (bSel)
//                	   g.fixtestError("geändert:"+VecVisible);
				}
			}
                        //boolean bKZR=false;
                        if(iMod_AIC_Modell==null)
                        {
                          int iPos=TabLoad.getInhalt("AIC_Spalte")==null ? -2:TabSpalten.getPos("AIC",TabLoad.getInhalt("AIC_Spalte"));
                          boolean bVar=(iMBits&Calc.VAR)>0;
                          String sE=TabLoad.getS("Eingabe");
                          if (bVar)
                        	  sE=TabLoad.getS("Var")+(Static.Leer(sE) ? Static.sLeer:"="+sE);
                          SpaltenSetzen(VecVisible,iPos<0 ? null:(JCOutlinerNode)TabSpalten.getInhalt("Node",iPos),g.encodeMath(sE),bVar,iPos==-1);
                        }
                        /*{
                          VecVisible.addElement(TabLoad.getS("Eingabe"));
                          VecVisible.addElement(null); // Refresh
                          VecVisible.addElement(null); // Bits
                        }
                        //int iBits=TabLoad.getI("MBits");
                        if(TabLoad.getInhalt("AIC_Spalte")!=null)
			{
				if (TabSpalten.posInhalt("AIC",TabLoad.getInhalt("AIC_Spalte")))
                                {
                                  bKZR=TabSpalten.getB("kZR");
                                  //if (CbxDatentyp.isSelected())
                                  //  VecVisible.setElementAt(TabSpalten.getS("Datentyp"), 1);

                                  VecVisible.addElement(TabSpalten.getS("Stt"));
                                  VecVisible.addElement(TabSpalten.getS("Abfrage"));
                                  VecVisible.addElement((bZ? TabSpalten.getS("Stt")+"."+TabSpalten.getS("Abfrage").toUpperCase()+".":"")+TabSpalten.getS("Bezeichnung")+(!bZ || TabLoad.isNull("Eingabe")?"":" / "+((iMBits&Calc.VAR)==0 ?"E=":"var=")+TabLoad.getS("Eingabe")));
                                }
                                else
                                {
                                  VecVisible.addElement(null);
                                  VecVisible.addElement(null);
                                  VecVisible.addElement("!!! Spalte nicht gefunden !!!");
                                }
			}
                        else if (iMod_AIC_Modell==null)
                        {
                          VecVisible.addElement(null); // Typ
                          VecVisible.addElement(null); // Abfrage
                          VecVisible.addElement(!bZ || TabLoad.isNull("Eingabe") ? "":((iMBits&Calc.VAR)==0 ?"E=":"var=")+TabLoad.getS("Eingabe"));  // Spalte
                        }*/

                        VecVisible.addElement(""+/*iDef>0?null:*/new Integer(TabLoad.getI("AIC_Befehl")-iAicDiff));
                        VecVisible.addElement(TabLoad.getS("Memo"));

			JCOutlinerFolderNode NodeParent = null;

			if(TabLoad.getInhalt("BEF_AIC_Befehl")!=null)
			{
				TabLoad.push();
				TabLoad.posInhalt("AIC_Befehl",TabLoad.getInhalt("BEF_AIC_Befehl"));
				NodeParent = (JCOutlinerFolderNode)TabLoad.getInhalt("NodeJa");
				TabLoad.pop();
			}
			else if(TabLoad.getInhalt("BEF2_AIC_Befehl")!=null)
			{
				TabLoad.push();
				TabLoad.posInhalt("AIC_Befehl",TabLoad.getInhalt("BEF2_AIC_Befehl"));
				NodeParent = (JCOutlinerFolderNode)TabLoad.getInhalt("NodeNein");
				TabLoad.pop();
			}
			else
				NodeParent = NodeModell;

                        JCOutlinerNode Node;
			if(sArt.equals("If"))
			{

				Node = new JCOutlinerFolderNode((Object)VecVisible,NodeParent);
                Node.setUserData(VecInvisible);
//                g.fixtestError("IF-Node:"+VecVisible+"/"+VecInvisible);
                if (TabLoad.getI("AIC_Befehl")==iBefehl || bSel)
                {
                  NodSelect = Node;
                  //g.progInfo("NodSelect=" + NodSelect);
                  bSel=false;
                }
                if(StyIf!=null)
					Node.setStyle(StyIf);

				if(TabLoad.getB("hide"))
				{
					//makeKommentarStyle(Node,true);
                                        if (iDef>0 && (iMBits&Calc.CHANGE)==0)
                                          NodeParent.removeChild(Node);
					((JCOutlinerFolderNode)Node).setState(BWTEnum.FOLDER_CLOSED);
				}

				VecVisible = new Vector<String>();
				VecInvisible = new Vector<Object>();
				VecVisible.addElement(Static.sJa);
				VecInvisible.addElement(null);
				VecInvisible.addElement(null);
				VecInvisible.addElement("If-Ja");
				VecInvisible.addElement("");
				VecInvisible.addElement("");
                VecInvisible.addElement(null);
                VecInvisible.addElement(null);
                VecInvisible.addElement(null);
                VecInvisible.addElement(null);
                VecInvisible.addElement(null);

				JCOutlinerFolderNode NodeJa = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)Node);
				NodeJa.setUserData(VecInvisible);
				if(StyJa!=null)
					NodeJa.setStyle(StyJa);


				VecVisible = new Vector<String>();
				VecInvisible = new Vector<Object>();
				VecVisible.addElement(Static.sNein);
				VecInvisible.addElement(null);
				VecInvisible.addElement(null);
				VecInvisible.addElement("If-Nein");
				VecInvisible.addElement("");
				VecInvisible.addElement("");
                VecInvisible.addElement(null);
                VecInvisible.addElement(null);
                VecInvisible.addElement(null);
                VecInvisible.addElement(null);
                VecInvisible.addElement(null);

				JCOutlinerFolderNode NodeNein = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)Node);
				NodeNein.setUserData(VecInvisible);
				if(StyNein!=null)
					NodeNein.setStyle(StyNein);

				TabLoad.setInhalt("NodeJa",NodeJa);
				TabLoad.setInhalt("NodeNein",NodeNein);

			}
			else if(sArt.equals("While"))
			{
				//VecInvisible.addElement(null);
                                Node = new JCOutlinerFolderNode((Object)VecVisible,NodeParent);
                                Node.setUserData(VecInvisible);
                                if (TabLoad.getI("AIC_Befehl")==iBefehl || bSel)
                                {
                                  NodSelect = Node;
                                  //g.progInfo("NodSelect=" + NodSelect);
                                  bSel=false;
                                }
				if(StyWhile!=null)
					Node.setStyle(StyWhile);

				TabLoad.moveNext();
				if(!TabLoad.eof())
				{
					boolean bNot=TabLoad.getInhalt("BEF2_AIC_Befehl")!=null;
					if(bNot)
						Not(Node);
					TabLoad.movePrevious();
					TabLoad.setInhalt(bNot?"NodeNein":"NodeJa",Node);
				}

				if(TabLoad.getB("hide"))
				{
					//makeKommentarStyle(Node,true);
                                        if (iDef>0 && (iMBits&Calc.CHANGE)==0)
                                          NodeParent.removeChild(Node);
					((JCOutlinerFolderNode)Node).setState(BWTEnum.FOLDER_CLOSED);
				}
			}
			else
			{
                          Node = new JCOutlinerNode(VecVisible,NodeParent);
                          Node.setUserData(VecInvisible);
                          if (TabLoad.getI("AIC_Befehl")==iBefehl || bSel)
                          {
                            NodSelect = Node;
//                            g.fixtestError("NodSelect="+NodSelect);
                            bSel=false;
                          }                    	  

				JCOutlinerNodeStyle StyNode = null;
				if(iMod_AIC_Modell==null)
				{
					int iPosBG=g.TabBegriffgruppen.getPos("AIC",TabLoad.getI("AIC_Begriffgruppe"));
                                        if (iPosBG<0)
                                          Static.printError("Begriffgruppe "+TabLoad.getI("AIC_Begriffgruppe")+" bei Befehl "+TabLoad.getI("AIC_Befehl")+" nicht gefunden");
					Image Img = iPosBG<0 ? null:g.LoadImage(g.TabBegriffgruppen.getS(iPosBG,"Filename"));
					if (Img != null)
					{
						StyNode = new JCOutlinerNodeStyle();
						StyNode.setFolderClosedIcon(Img);
						StyNode.setFolderOpenIcon(Img);
						StyNode.setItemIcon(Img);
					}
				}
				else
					StyNode = StyModell;

				if(StyNode!=null)
					Node.setStyle(StyNode);

				//makeKommentarStyle(Node,TabLoad.getB("hide"));
			}
                        if (iDef>0 && (iMBits&Calc.CHANGE)>0)
                          setChange(Node,false);
                        else
                          setBitChange(Node,false);
                        setBits(Node,false);
                        String sBefehl=iPosC<0 ? "":g.TabCodes.getS(iPosC,"Kennung");
                        if(TabLoad.getB("hide"))
                        {
                          if (iDef>0 && (iMBits&Calc.CHANGE)==0)
                            NodeParent.removeChild(Node);
                          makeKommentarStyle(Node, true,false);
                        }
                        else if (Calc.Bdep.contains(sBefehl))
                        	makeBreakStyle(Node, true, Global.ColDep);
                        else if (Calc.Bdead.contains(sBefehl))//TabCode.getS("Kennung")))
                        	makeBreakStyle(Node, true, Color.RED);
                        else if (CbxColMemo.isSelected() && !TabLoad.isNull("Memo"))
                          makeBreakStyle(Node, true, Global.ColMemo);
                        //else if (CbxColKeinZeitraum.isSelected() && bKZR)
//                        else if (
//                          makeBreakStyle(Node, true, g.ColKeinZR);
                        else if (CbxColZR.isSelected() && iMod_AIC_Modell==null && VecZR.contains(sBefehl))
                          makeBreakStyle(Node, true, Global.ColZR);
                        else if (CbxColPush.isSelected() && iMod_AIC_Modell==null && VecPush.contains(sBefehl))
                          makeBreakStyle(Node, true, Global.ColPush);
                        else if (CbxColQry.isSelected() && iMod_AIC_Modell==null && VecQry.contains(sBefehl))
                          makeBreakStyle(Node, true, Global.ColQRY);
                        else if (CbxColSpalte.isSelected() && !TabLoad.isNull("AIC_Spalte") && (TabLoad.getI("AIC_Spalte")==iSpalte || VecSpalte != null && VecSpalte.contains(TabLoad.getInhalt("AIC_Spalte"))))
                          makeBreakStyle(Node, true, Global.ColSpalte);
                        else if (CbxColEingabe.isSelected() && (!TxtEingabe.isNull() && TxtEingabe.getText().equals(g.encodeMath(TabLoad.getS("Eingabe"))) || TxtEingabe.isNull() && !TabLoad.getS("Eingabe").equals("")))
                          makeBreakStyle(Node, true, Global.ColSpalte);
                        else if (CbxColVar.isSelected() && (!TxtVar.isNull() && TxtVar.getText().equals(TabLoad.getS("Var")) || TxtVar.isNull() && !TabLoad.getS("Var").equals("")))
                            makeBreakStyle(Node, true, Global.ColSpalte);
                        else if (CbxColBefehl.isSelected() && !TabLoad.isNull("AIC_Code") && TabLoad.getI("AIC_Code")==iCode)
                          makeBreakStyle(Node, true, Global.ColBefehl);
                        else
                        {
                          if (Aufruf.VecBreak != null && Aufruf.VecBreak.contains(TabLoad.getInhalt("AIC_Befehl")))
                          {
                            makeBreakStyle(Node, true, Global.ColBreakpoint);
                            VecInvisible.setElementAt("B",8);
                          }
                          else if (Aufruf.VecDebug != null && Aufruf.VecDebug.contains(TabLoad.getInhalt("AIC_Befehl")))
                          {
                            makeBreakStyle(Node, true, Global.ColDebug);
                            VecInvisible.setElementAt("D",8);
                          }
                        }
		}
//                if (NodSelect!=null)
//                {
//                	g.fixtestError("NodSel2="+NodSelect.getLabel());
//                	rOutBuild.folderChanged(NodSelect.getParent());
//                	rOutBuild.selectNode(NodSelect, null);  
//                }
                //else
                if (rOutBuild != null)
                {
	                rOutBuild.setVisible(true);
	                boolean bRN=rOutBuild.getRootNode() != null;
	                if (NodSelect!=null)// && iDef==0)
	                  Static.makeVisible(rOutBuild,NodSelect,false);
	                else if (bRN)
	                  rOutBuild.folderChanged(rOutBuild.getRootNode());
	                if (bNormal && bRN)
	                {
	                  bGeaendert = false;
	                  JCOutlinerNodeStyle Sty=rOutBuild.getRootNode().getStyle()==null ? rOutBuild.getDefaultNodeStyle():new JCOutlinerNodeStyle(rOutBuild.getRootNode().getStyle());
	                  Sty.setForeground(Color.red.darker());
	                  rOutBuild.getRootNode().setStyle(Sty);
	                  //g.progInfo("Normal");
	                }
                }
        if (iDef==0)
        	EnableButtons();
        bGeladen=true;
		return NodSelect;
	}

        /*private String addS(int iBits,int iBit,String s)
        {
          return (iBits&iBit)==0 ? "":g.getBegriffS("Show","var")+" ";
        }*/

	private void selectedNode(String sOutliner)
	{
		if(!bSelectedNode)
		{
			bSelectedNode=true;
			sAktOutliner=sOutliner;
			if(sOutliner.equals("Modell"))
			{
				OutAnweisung.selectNode(OutAnweisung.getRootNode(),null);
				OutBedingung.selectNode(OutBedingung.getRootNode(),null);
                                int iLevel=OutModell.getSelectedNode().getLevel();
				TxtMemoModell.setText(iLevel>0?(String)((Vector)OutModell.getSelectedNode().getUserData()).elementAt(1):"");
                                iModell=iLevel>0 ? Sort.geti((Vector)OutModell.getSelectedNode().getUserData(),0):0;
                                iMBits=Sort.geti((Vector)OutModell.getSelectedNode().getUserData(),3);
			}
			else if(sOutliner.equals("Anweisung"))
			{
				OutModell.selectNode(OutModell.getRootNode(),null);
				OutBedingung.selectNode(OutBedingung.getRootNode(),null);
				TxtMemoModell.setText(OutAnweisung.getSelectedNode().getLevel()==2?(String)((Vector)OutAnweisung.getSelectedNode().getUserData()).elementAt(1):"");
			}
			else if(sOutliner.equals("Bedingung"))
			{
				OutModell.selectNode(OutModell.getRootNode(),null);
				OutAnweisung.selectNode(OutAnweisung.getRootNode(),null);
				TxtMemoModell.setText(OutBedingung.getSelectedNode().getLevel()==2?(String)((Vector)OutBedingung.getSelectedNode().getUserData()).elementAt(1):"");
			}
			bSelectedNode=false;
		}
	}

        private void SpaltenSetzen(Vector<String> VecVisible,JCOutlinerNode NodeSpalte,String sEingabe,boolean bVar,boolean bFehlt)
        {
          boolean bZ=CbxZusammen.isSelected();
          boolean bAic=CbxNurAic.isSelected();
          VecVisible.addElement(sEingabe);
          VecVisible.addElement(null);
          VecVisible.addElement(null);
          if (NodeSpalte!=null && NodeSpalte.getLevel()==4)
          {
            VecVisible.addElement(Sort.gets(NodeSpalte.getParent().getParent().getLabel()));
            VecVisible.addElement(Sort.gets(NodeSpalte.getParent().getLabel()));
            if (bZ)
              VecVisible.addElement(Sort.gets(NodeSpalte.getParent().getParent().getLabel(),bAic?1:0)+"."+Sort.gets(NodeSpalte.getParent().getLabel(),bAic?1:0).toUpperCase()+"."+
                  Sort.gets(NodeSpalte.getLabel())+(sEingabe.equals("")?"":" / "+(bVar ? "var=":"E=")+sEingabe));
            else
              VecVisible.addElement(Sort.gets(NodeSpalte.getLabel()));
          }
          else
          {
            VecVisible.addElement(null);
            VecVisible.addElement(null);
            VecVisible.addElement((bFehlt ? "!!! Spalte nicht gefunden !!! ":"")+(!bZ || sEingabe.equals("") ? "":(bVar ? "var=":"E=")+sEingabe));
          }
        }
    
    private String getEingabe()
    {
    	String sEingabe=TxtEingabe.getText();
//        if (PnlVar != null)
        {
        	if (!TxtVar.isNull() && !CbxVar.isSelected())
        		CbxVar.setSelected(true);
        	if (CbxVar.isSelected())
        		if (sEingabe.length()==0)
        			sEingabe=TxtVar.getText();
        		else
        			sEingabe=TxtVar.getText()+"="+sEingabe;
        }
        return sEingabe;
    }

	@SuppressWarnings("unchecked")
	private void Hinzufuegen()
	{
		JCOutlinerNode NodeParent = OutBuild.getSelectedNode();
		String sArtParent = (String)((Vector)NodeParent.getUserData()).elementAt(2);
		if(sAktOutliner.equals("Anweisung"))
		{
			JCOutlinerNode NodeAnw = OutAnweisung.getSelectedNode();
			Vector<String> VecVisible = new Vector<String>((Vector)NodeAnw.getLabel());
            VecVisible.removeElementAt(2);
            if (VecVisible.size()>2)
            	VecVisible.removeElementAt(2);
                        Vector VecInvisible = new Vector();
                        VecInvisible.addElement(((Vector)NodeAnw.getUserData()).elementAt(0));
                        JCOutlinerNode NodeSpalte = OutSpalten.getSelectedNode();
                        String sEingabe=getEingabe();
                        SpaltenSetzen(VecVisible,NodeSpalte,sEingabe,CbxVar.isSelected(),false);
                        VecVisible.addElement("x");
                        VecVisible.addElement("");
                        //g.testInfo(sAktOutliner+":"+VecVisible.size());
            VecInvisible.addElement(NodeSpalte.getLevel()==4?((Vector)NodeSpalte.getUserData()).elementAt(0):null);
			VecInvisible.addElement(sAktOutliner);
			VecInvisible.addElement(TxtEingabe.getText());
			VecInvisible.addElement("");
			VecInvisible.addElement(new Boolean(false));
			VecInvisible.addElement(null);
			VecInvisible.addElement(0);
			VecInvisible.addElement(null);
			VecInvisible.addElement(TxtVar.getText());
			JCOutlinerNode Node=null;

			if((NodeParent!=OutBuild.getRootNode() && sArtParent.equals("Modell")) || sArtParent.equals("Anweisung") || sArtParent.equals("If"))
			{
				Node = new JCOutlinerNode(VecVisible,NodeParent.getParent());
				Vector VecChildren = NodeParent.getParent().getChildren();
				VecChildren.removeElement(Node);
				VecChildren.insertElementAt(Node,VecChildren.indexOf(NodeParent));
			}
			else
				Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)NodeParent);
                        Node.setUserData(VecInvisible);
                        setChange(Node,true);
                        /*setBit(Node,0,0,true);*/
			JCOutlinerNodeStyle Sty=new JCOutlinerNodeStyle(NodeAnw.getStyle()==null ? new JCOutlinerComponent().getDefaultNodeStyle():NodeAnw.getStyle());
			Sty.setForeground(Color.red.darker());
			Sty.setFont(Global.fontModell);
			Node.setStyle(Sty);

		}
		else if(sAktOutliner.equals("Modell"))
		{
			JCOutlinerNode NodeMod = OutModell.getSelectedNode();
			Vector VecVisible = new Vector((Vector)NodeMod.getLabel());
                        Vector VecInvisible = new Vector();

			VecVisible.setElementAt(null,2);
			VecVisible.setElementAt("x",3);
            VecVisible.setElementAt("",4);
            VecVisible.setElementAt("",5);
            VecVisible.setElementAt("",6);
            VecVisible.setElementAt("",7);
            //g.testInfo(sAktOutliner+":"+VecVisible.size());
			VecInvisible.addElement(((Vector)NodeMod.getUserData()).elementAt(0));
			VecInvisible.addElement(null);
			VecInvisible.addElement(sAktOutliner);
			VecInvisible.addElement("");
			VecInvisible.addElement("");
			VecInvisible.addElement(new Boolean(false));

			JCOutlinerNode Node = null;

			if((NodeParent!=OutBuild.getRootNode() && sArtParent.equals("Modell")) || sArtParent.equals("Anweisung") || sArtParent.equals("If"))
			{
				Node = new JCOutlinerNode(VecVisible,NodeParent.getParent());
				Vector VecChildren = NodeParent.getParent().getChildren();
				VecChildren.removeElement(Node);
				VecChildren.insertElementAt(Node,VecChildren.indexOf(NodeParent));
			}
			else
				Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)NodeParent);
			Node.setUserData(VecInvisible);
			JCOutlinerNodeStyle Sty=new JCOutlinerNodeStyle(NodeMod.getStyle());
			Sty.setForeground(Color.red.darker());
			Sty.setFont(g.fontModell);
			Node.setStyle(Sty);
                        setChange(Node,false);
			//Node.setStyle(NodeMod.getStyle());
		}
		else if(sAktOutliner.equals("Bedingung"))
		{

			JCOutlinerNode NodeBed = OutBedingung.getSelectedNode();
			JCOutlinerNode NodeSpalte = OutSpalten.getSelectedNode();
			Vector VecVisible = new Vector((Vector)NodeBed.getLabel());
            VecVisible.removeElementAt(2);
            if (VecVisible.size()>2)
            	VecVisible.removeElementAt(2);
			Vector VecInvisible = new Vector();
			String sArt = (String)NodeBed.getParent().getUserData();

			//VecVisible.addElement(NodeSpalte.getLevel()==4?((Vector)NodeSpalte.getParent().getLabel()).elementAt(0)+"."+((Vector)NodeSpalte.getLabel()).elementAt(0):null);
                        String sEingabe=getEingabe();
                        //int iPos=2;
                        //if (VecVisible.size()>iPos)
                        //  VecVisible.setElementAt(Obj,iPos);
                        //else
                        SpaltenSetzen(VecVisible,NodeSpalte,sEingabe,CbxVar.isSelected(),false);
                        VecVisible.addElement("x");
                        VecVisible.addElement("");
                        //g.testInfo(sAktOutliner+":"+VecVisible.size());

			VecInvisible.addElement(((Vector)NodeBed.getUserData()).elementAt(0));
			VecInvisible.addElement(NodeSpalte.getLevel()==4?((Vector)NodeSpalte.getUserData()).elementAt(0):null);
			VecInvisible.addElement(sArt);
			VecInvisible.addElement(TxtEingabe.getText());
			VecInvisible.addElement("");
			VecInvisible.addElement(new Boolean(false));
			VecInvisible.addElement(null);
			VecInvisible.addElement(0);
			VecInvisible.addElement(null);
			VecInvisible.addElement(TxtVar.getText());
			if(sArt.equals("While"))
				VecInvisible.addElement(null);

			JCOutlinerFolderNode Node = null;

			if((NodeParent!=OutBuild.getRootNode() && sArtParent.equals("Modell")) || sArtParent.equals("Anweisung") || sArtParent.equals("If"))
			{
				Node = new JCOutlinerFolderNode((Object)VecVisible,NodeParent.getParent());
				Vector VecChildren = NodeParent.getParent().getChildren();
				VecChildren.removeElement(Node);
				VecChildren.insertElementAt(Node,VecChildren.indexOf(NodeParent));
			}
			else
				Node = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)NodeParent);
			Node.setUserData(VecInvisible);
			JCOutlinerNodeStyle Sty=new JCOutlinerNodeStyle(NodeBed.getStyle());
			Sty.setForeground(Color.red.darker());
			Sty.setFont(g.fontModell);
			Node.setStyle(Sty);
                        setChange(Node,true);
			//Node.setStyle(NodeBed.getStyle());

			if(sArt.equals("If"))
			{
				VecVisible = new Vector();
				VecInvisible = new Vector();
				VecVisible.addElement(Static.sJa);
				VecInvisible.addElement(null);
				VecInvisible.addElement(null);
				VecInvisible.addElement("If-Ja");
				VecInvisible.addElement("");
				VecInvisible.addElement("");

				JCOutlinerFolderNode NodeJa = new JCOutlinerFolderNode((Object)VecVisible,Node);
				NodeJa.setUserData(VecInvisible);
				if(StyJa!=null)
					NodeJa.setStyle(StyJa);

				VecVisible = new Vector();
				VecInvisible = new Vector();
				VecVisible.addElement(Static.sNein);
				VecInvisible.addElement(null);
				VecInvisible.addElement(null);
				VecInvisible.addElement("If-Nein");
				VecInvisible.addElement("");
				VecInvisible.addElement("");

				JCOutlinerFolderNode NodeNein = new JCOutlinerFolderNode((Object)VecVisible,Node);
				NodeNein.setUserData(VecInvisible);
				if(StyNein!=null)
					NodeNein.setStyle(StyNein);
			}

		}
        TxtEingabe.setText("");
//        if (PnlVar != null)
//        	TxtVar.setText("");
		OutBuild.folderChanged(OutBuild.getRootNode());
	}

	@SuppressWarnings("unchecked")
	private void Ersetzen()
	{
		JCOutlinerNode Node = OutBuild.getSelectedNode();
		JCOutlinerNode NodeAnw=null;
		Vector<String> VecVisible = new Vector<String>();
		Vector<Object> VecInvisible = new Vector<Object>((Vector)Node.getUserData());
                if(sAktOutliner.equals("Anweisung"))
		{
			NodeAnw = OutAnweisung.getSelectedNode();
			JCOutlinerNode NodeSpalte = OutSpalten.getSelectedNode();
			//Vector VecVisible = new Vector((Vector)NodeAnw.getLabel());
			int iPosC=g.TabCodes.getPos("AIC",((Vector)NodeAnw.getUserData()).elementAt(0));
                        if (iPosC>=0)
			{
				VecVisible.addElement(g.TabCodes.getS(iPosC,"Bezeichnung"));
				VecVisible.addElement(g.TabCodes.getS(iPosC,"Kennung"));
			}
                        String sEingabe=getEingabe();
                        SpaltenSetzen(VecVisible,NodeSpalte,sEingabe,CbxVar.isSelected(),false);
                        VecInvisible.setElementAt(((Vector)NodeAnw.getUserData()).elementAt(0),0);
			VecInvisible.setElementAt(NodeSpalte.getLevel()==4?((Vector)NodeSpalte.getUserData()).elementAt(0):null,1);
			VecInvisible.setElementAt(sAktOutliner,2);
            VecInvisible.setElementAt(TxtEingabe.getText(),3);
            VecInvisible.setElementAt(TxtVar.getText(),9);
		}
		else if(sAktOutliner.equals("Modell"))
		{
		  	NodeAnw = OutModell.getSelectedNode();
			//Vector VecVisible = new Vector((Vector)NodeMod.getLabel());
			if(TabModell.posInhalt("AIC",((Vector)NodeAnw.getUserData()).elementAt(0)))
			{
				VecVisible.addElement(TabModell.getS("Bezeichnung"));
				VecVisible.addElement(TabModell.getS("Kennung"));
			}

			if (VecVisible.size()==2)
              VecVisible.addElement(null);
            else if (VecVisible.size()>2)
              VecVisible.setElementAt(null,2);
            VecInvisible.setElementAt(((Vector)NodeAnw.getUserData()).elementAt(0),0);
			VecInvisible.setElementAt(null,1);
			VecInvisible.setElementAt(sAktOutliner,2);
		}
		else if(sAktOutliner.equals("Bedingung"))
		{
		  	NodeAnw = OutBedingung.getSelectedNode();
			JCOutlinerNode NodeSpalte = OutSpalten.getSelectedNode();
			//Vector VecVisible = new Vector((Vector)NodeBed.getLabel());
                        boolean bNot=CbxNot.isSelected();
                        int iPosC=g.TabCodes.getPos("AIC",((Vector)NodeAnw.getUserData()).elementAt(0));
                        if(iPosC>=0)
			{
				VecVisible.addElement((bNot ? sNicht+" ":"")+g.TabCodes.getS(iPosC,"Bezeichnung"));
				VecVisible.addElement(g.TabCodes.getS(iPosC,"Kennung"));
			}
			String sArt = (String)NodeAnw.getParent().getUserData();
                        String sEingabe=getEingabe();
                        SpaltenSetzen(VecVisible,NodeSpalte,sEingabe,CbxVar.isSelected(),false);

                        /*if (sEingabe.equals(""))
                          VecVisible.addElement(NodeSpalte.getLevel()==4?(((Vector)NodeSpalte.getParent().getParent().getLabel()).elementAt(0)+".")+((Vector)NodeSpalte.getParent().getLabel()).elementAt(0)+"."+((Vector)NodeSpalte.getLabel()).elementAt(0):null);
                        else*/

            VecInvisible.setElementAt(((Vector)NodeAnw.getUserData()).elementAt(0),0);
			VecInvisible.setElementAt(NodeSpalte.getLevel()==4?((Vector)NodeSpalte.getUserData()).elementAt(0):null,1);
			VecInvisible.setElementAt(sArt,2);
            VecInvisible.setElementAt(TxtEingabe.getText(),3);
            VecInvisible.setElementAt(TxtVar.getText(),9);
			if(sArt.equals("While"))
				VecInvisible.setElementAt(bNot ? g.TabCodes.getS(iPosC,"Bezeichnung"):null,6);
		}
		VecVisible.addElement(""+((Vector)Node.getLabel()).elementAt(8));
                VecVisible.addElement(""+((Vector)Node.getLabel()).elementAt(9));
                Node.setLabel(VecVisible);
		Node.setUserData(VecInvisible);
                //setBit(Node,0,0,true);
                if(sAktOutliner.equals("Anweisung"))
                {
                  JCOutlinerNodeStyle Sty = new JCOutlinerNodeStyle(NodeAnw.getStyle()==null ? new JCOutlinerComponent().getDefaultNodeStyle():NodeAnw.getStyle());
                  Node.setStyle(Sty);
                }
		//Sty.setForeground(Color.red.darker());
                //g.progInfo(Node.getStyle()+"/"+Sty);
		//Node.setStyle(Sty);
                //Static.makeVisible(OutBuild,Node);
//                g.fixtestError("VecInvisible1="+VecInvisible);
                setChange(Node,true);
                if (!CbxSyncVar.isSelected())
                {
                  TxtEingabe.setText("");
//                  if (PnlVar != null)
                	  TxtVar.setText("");
                }
                //setBit(Node, Calc.CHANGE, Calc.CHANGE, true);
		//makeKommentarStyle(Node,((Boolean)((Vector)Node.getUserData()).elementAt(5)).booleanValue());
		//OutBuild.folderChanged(Node);,
//                g.fixtestError("VecInvisible2="+VecInvisible);
	}

        private void setChange(JCOutlinerNode Node,boolean bSet)
        {
          JCOutlinerNodeStyle Sty=new JCOutlinerNodeStyle(Node.getStyle()==null ? new JCOutlinerComponent().getDefaultNodeStyle():Node.getStyle());
          Sty.setForeground(Color.red.darker());
          Sty.setFont(g.fontModell);
          Node.setStyle(Sty);
          if (!bSet)
            setBitChange(Node, true);
          setBits(Node,bSet);//true);
//          g.fixtestError("setBits von setChange bei "+Node+"/"+bSet);
          //Static.makeVisible(OutBuild,Node);
        }

	private void Entfernen()
	{
		JCOutlinerNode[] Node = OutBuild.getSelectedNodes();
		JCOutlinerFolderNode NodeParent = Node[0].getParent();
                OutBuild.selectNode(OutBuild.getPreviousNode(Node[0]),null);
                for (int i=0;i<Node.length;i++)
                  NodeParent.removeChild(Node[i]);
		OutBuild.folderChanged(NodeParent);
	}

	private void EnableButtons()
	{
		if(!bEnableButtons)
		{
			bEnableButtons=true;
//			g.fixtestError("EnableButtons");			
			JCOutlinerNode Node = OutModell.getSelectedNode();
			boolean bLoeschenBearbeiten = Node!=null && Node != OutModell.getRootNode();
			//BtnLoeschen.setEnabled(g.Def() && bLoeschenBearbeiten);
			BtnBearbeiten.setEnabled(g.Def() && bLoeschenBearbeiten);
			//BtnBearbeitenModell.setEnabled(bLoeschenBearbeiten);
			//BtnSpeichern.setEnabled(g.Def() && OutBuild.getRootVisible());
			BtnNeu.setEnabled(g.Def() && !bGeaendert);
			MnuNeu.setEnabled(g.Def() && !bGeaendert);
			MnuDel.setEnabled(g.Def() && !bGeaendert);
                        MnuKommentar.setVisible(bGeaendert);
                        MnuBreak.setVisible(!bGeaendert);
                        MnuBreakVar.setVisible(!bGeaendert);
                        MnuBreakEingabe.setVisible(!bGeaendert);
                        MnuBreakSpalte.setVisible(OutSpalten!=null && OutSpalten.getSelectedNode()!= null && OutSpalten.getSelectedNode().getLevel()>2);
                        MnuDebug.setVisible(!bGeaendert);
                        MnuNothing.setVisible(!bGeaendert);
                        BtnRefresh2.setEnabled(!bGeaendert);// && bLoeschenBearbeiten);

			Node = OutBuild.getSelectedNode();
			String sArt = Node!=null?Node == OutBuild.getRootNode()?"Root":(String)((Vector)Node.getUserData()).elementAt(2):"";
			int iAnz=OutBuild.getSelectedNodes().length;
			BtnHinzufuegen.setEnabled(bGeaendert && iAnz==1 && OutBuild.getRootVisible() && !(sAktOutliner.equals("Anweisung")&&OutAnweisung.getSelectedNode().getLevel()<2) && !(sAktOutliner.equals("Bedingung")&&OutBedingung.getSelectedNode().getLevel()<2));
			BtnEntfernen.setEnabled(bGeaendert && OutBuild.getRootVisible() == true && sArt!=null && !sArt.equals("Root") && !sArt.equals("If-Ja") && !sArt.equals("If-Nein"));
			JCOutlinerNode NodBuild = OutBuild.getSelectedNode();
			BtnRauf.setEnabled(bGeaendert && Static.allow_up_down(NodBuild,true));
			BtnRunter.setEnabled(bGeaendert && Static.allow_up_down(NodBuild,false,iAnz));

			JCOutlinerNode NodeBed = OutBedingung.getSelectedNode();
			JCOutlinerNode NodeAnw = OutAnweisung.getSelectedNode();
			boolean bDead=false;
			boolean bConcat=false;
			boolean bConcat2=false;
			boolean bAdd=false;
			if (NodeAnw != null && NodeAnw.getLevel()==2 || NodeBed != null && NodeBed.getLevel()==2)
			{
//				g.fixInfo("Befehl="+NodeAnw.getLabel());
				String sBefehl=Sort.gets(NodeBed != null && NodeBed.getLevel()==2 ? NodeBed.getLabel():NodeAnw.getLabel(), 1);
				bDead=Calc.Bdead.contains(sBefehl);
				bConcat=Calc.Bconcat.contains(sBefehl);
				bConcat2=Calc.Bconcat2.contains(sBefehl);
				bAdd=Calc.Badd.contains(sBefehl);
				if (bDead)
					BtnHinzufuegen.setEnabled(false);
				if (!bConcat && !bConcat2 && !RadKein.isSelected())
					RadKein.setSelected(true);
				else if (bConcat && RadKein.isSelected())
					RadInit.setSelected(true);
				else if (bConcat2 && RadKein.isSelected())
					RadConcat.setSelected(true);
				if (!bAdd && !RadMkein.isSelected())
					RadMkein.setSelected(true);
				else if (bAdd && RadMkein.isSelected())
					RadMinit.setSelected(true);
			}
			RadConcat.setEnabled(bConcat || bConcat2);
			RadCWS.setEnabled(bConcat || bConcat2);
			RadInit.setEnabled(bConcat || bConcat2);
			RadMadd.setEnabled(bAdd);
			RadMsub.setEnabled(bAdd);
			RadMinit.setEnabled(bAdd);
			boolean bNode=Node!=null && Node!=OutBuild.getRootNode();
                        //g.progInfo("EnableButtons:"+sArt+"/"+bNode);
                        BtnErsetzen.setEnabled(bGeaendert && !bDead && iAnz==1 && !sArt.equals("Root")&&(sAktOutliner.equals("Bedingung")?sArt.equals(NodeBed.getLevel()==2?NodeBed.getParent().getUserData().toString():""):NodeAnw!=null && sAktOutliner.equals("Anweisung")?NodeAnw.getLevel()!=1 && sArt.equals("Anweisung"):Node!=null && sArt.equals("Modell")));
			boolean bMemoBefehl = bNode && !sArt.equals("If-Ja") && !sArt.equals("If-Nein");
			//g.progInfo("bMemoBefehl="+bMemoBefehl);
                        TxtMemoBefehl.setEditable(bGeaendert && bMemoBefehl && iAnz==1);
			//TxtTitelBefehl.setEditable(bGeaendert && bMemoBefehl);

			BtnSpeichern.setEnabled(g.Def() && bGeaendert);
                        BtnEdit.setEnabled(g.Def() && !bGeaendert);
                        BtnDelBreak.setEnabled(!bGeaendert && (Aufruf.VecBreak != null || Aufruf.VecDebug != null || Aufruf.VecSpalten != null || Aufruf.VecBefehl != null || Aufruf.VecVar != null || Aufruf.VecEingabe != null));
//                        BtnInfoBreak.setEnabled(Aufruf.VecBefehl != null || Aufruf.VecSpalten != null || Aufruf.VecVar != null);
			BtnZurueck.setEnabled(bGeaendert || StkHistory.size()>1);

			CbxNot.setEnabled(bGeaendert && sArt!=null && (sArt.equals("If") || sArt.equals("While")));
                        //CbxVar.setEnabled(bGeaendert);
                        //CbxCache.setEnabled(bGeaendert);
                        /*RadAuto.setEnabled(bGeaendert && bNode);
                        RadImmer.setEnabled(bGeaendert && bNode && !sArt.equals("While"));
                        RadNie.setEnabled(bGeaendert && bNode);
                        RadNormal.setEnabled(bGeaendert && bNode);
                        RadEmpty.setEnabled(bGeaendert && bNode);
                        //RadSync.setEnabled(bGeaendert);
                        RadVec.setEnabled(bGeaendert && bNode);
                        RadVecBew.setEnabled(bGeaendert && bNode);*/
                        //g.fixInfo("CbxNot-Enabled:"+CbxNot.isEnabled());
			BtnAufruf.setEnabled(OutBuild.getRootVisible() && !bGeaendert);
			if (!BtnAufruf.isEnabled())
				iAIC_ModellOld=-1;
			OutBuild.setCopy(bGeaendert);
			bEnableButtons=false;
		}
	}

	private boolean Speichern(int iAic)
	{
          TCalc.TabCalc=null;
          if (Static.cache())
            Static.clearCache();
          JCOutlinerNode Nod=OutBuild.getSelectedNode();
          iBefehl=Nod==null || Nod.getUserData()==null ? 0:Sort.geti(Nod.getUserData(),7);
		JCOutlinerFolderNode Node = (JCOutlinerFolderNode)OutBuild.getRootNode();
		int iAIC_Modell = iAic>0 ? iAic:((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
                long lClock=Static.get_ms();
                int iFehler=g.getFehler();
                VecFehler=new Vector<String>();
                VecWarnung=new Vector<String>();
                iZeile=0;
                g.start();
                try
                {
                  int iBegriff = SQL.getInteger(g, "SELECT AIC_Begriff FROM Modell WHERE AIC_Modell=" + iAIC_Modell);
                  int iDef=g.SaveDefVerlauf(iBegriff, iAic>0 ?"kopie":"geändert");

                  SQL Qry = new SQL(g);
                  int iDefAlt=SQL.getInteger(g,"select distinct aic_defverlauf from befehl2 where aic_modell=?",-1,""+iAIC_Modell);
                  g.progInfo("iAIC_Modell="+iAIC_Modell+", iDefAlt="+iDefAlt);
                  // if (iDefAlt>0)
                    g.exec("update Befehl set def_aic_defverlauf="+iDef+" where def_aic_defverlauf is null and aic_modell=" + iAIC_Modell);
                  // else
                  // {
                  //   //Qry.deleteFrom2("Daten_Memo","Daten_Memo, Befehl WHERE aic_Tabellenname="+iTabBefehl+" AND AIC_Fremd=AIC_Befehl AND AIC_Modell="+iAIC_Modell);
                  //   int iAnz=Qry.deleteFrom("Befehl", "Befehl WHERE AIC_Modell=" + iAIC_Modell, iTabBefehl);
                  //   if (iAnz>0)
                  //     g.testInfo(iAnz+" alte Modellzeilen gelöscht");
                  // }
                  Qry.add("aic_logging", g.getLog());
                  Qry.update("Begriff", iBegriff);
                  //g.progInfo("Speichere Modell"+iAIC_Modell+"/"+iBegriff);
                  Qry.free();
                  Speichern_Rekursiv(new Integer(iAIC_Modell), Node, null);
                  g.exec("update Befehl set aic_defverlauf="+iDef+" where def_aic_defverlauf is null and aic_modell=" + iAIC_Modell);
                  boolean bTrotzdem=false;
                  if (VecFehler.size()>0 && iAnzFL>2 || VecWarnung.size()>0 && VecFehler.size()==0)
                  {
                	  Vector<String> Vec=new Vector<String>();
                	  Static.addVectorS(Vec, VecFehler);
                	  Static.addVectorS(Vec, VecWarnung);
                    bTrotzdem = new Message(Message.YES_NO_OPTION, null, g).showDialog("TrotzFehlerSpeichern", new String[] {Static.VecToString(Vec)}) == Message.YES_OPTION;
                    if (bTrotzdem)
                      g.exec("update DEFVERLAUF set tat=' mit Fehler' where aic_defverlauf="+iDef);
                  }
                  if (g.getFehler()-iFehler == 0 && VecFehler.size()==0 || bTrotzdem)
                  {
                    g.commit();
                    iAnzFL=0;
                    g.checkModelle();
                    g.clockInfo("save Modell " + g.TabModelle.getBezeichnungS(iAIC_Modell), lClock);
                    checkSperre();
                    Laden(null, true, null, 0);
                    return true;
                  }
                  else
                  {
                    g.rollback();
                    Static.printError(g.TabModelle.getBezeichnungS(iAIC_Modell)+":"+(g.getFehler()-iFehler+VecFehler.size())+" Fehler beim Speichern aufgetreten!");
                    if (VecFehler.size()>0)
                    {
                      iAnzFL++;
                      new Message(Message.WARNING_MESSAGE, thisJFrame(), g).showDialog("Fehlerliste", new String[] {Static.VecToString(VecFehler)});
                      MemoF memF = new MemoF(null, "Fehlerliste", g, false,false,0);
                      memF.Txt.setText(Static.VecToString(VecFehler));
                      memF.show2();
                    }
                    return false;
                  }
                }
                catch(Exception e)
                {
                  g.rollback();
                  e.printStackTrace();
                  String s=g.TabModelle.getBezeichnungS(iAIC_Modell);
                  Static.printError("DefModell.Speichern("+s+"): Fehler beim Speichern:"+e);
                  new Message(Message.WARNING_MESSAGE, thisJFrame(), g).showDialog("nicht_gespeichert", new String[]{s});
                  return false;
                }
                //Laden((JCOutlinerFolderNode)OutBuild.getRootNode(),false);
	}

	@SuppressWarnings("unchecked")
	private void Speichern_Rekursiv(Integer iAIC_Modell, JCOutlinerFolderNode NodeRoot, Integer iAIC_Befehl)
	{
		//g.fixtestError("Speichern_Rekursiv "+iAIC_Befehl);
		String sArtRoot = (String)((Vector)NodeRoot.getUserData()).elementAt(2);
		Vector VecNodes = NodeRoot.getChildren();
		SQL Qry = new SQL(g);
		Qry.setNachher();
		for(int i=0;VecNodes!=null && i<VecNodes.size();i++)
		{
			JCOutlinerNode Node = (JCOutlinerNode) VecNodes.elementAt(i);
			Vector<Object> VecInvisible = (Vector)Node.getUserData();
//			g.fixtestError("VecInvisible="+VecInvisible);

			String sArt = (String)VecInvisible.elementAt(2);
			Integer iAIC_Spalte = (Integer)VecInvisible.elementAt(1);
			Integer iAIC_Node = (Integer)VecInvisible.elementAt(0);
            iZeile++;
			Qry.add("AIC_Modell",iAIC_Modell);
            String sEingabe=Sort.gets(VecInvisible, 3);// (String)VecInvisible.elementAt(3);
            boolean bEingabe=!Static.Leer(sEingabe);// != null && !sEingabe.equals("");
            String sVar=Sort.gets(VecInvisible, 9);
            boolean bVar=!Static.Leer(sVar);
            if (bEingabe)
            	Qry.add("Eingabe",g.decodeMath(sEingabe));
            if (bVar)
            	Qry.add("Var", sVar);
            int iBits=Sort.geti(VecInvisible,5);
            if (sArt.equals("While"))
            	iBits|=Calc.SCHLEIFE;
			Qry.add("hide",(iBits&Calc.HIDE)>0); //((Boolean)VecInvisible.elementAt(5)).booleanValue());
			int iMBits=iBits&0xfffefff;
//			g.fixtestError("Zeile "+i+":"+iBits+"->"+iMBits);
            Qry.add("mbits",iMBits);
			if(sArt.equals("Modell"))
				Qry.add("MOD_AIC_Modell",iAIC_Node);
			else
            {
              Qry.add("AIC_Code", iAIC_Node);
               if ((iBits&Calc.HIDE)==0)
               {
                 String sBefehl = Sort.gets(Node.getLabel(), 1);
                 String sZ=g.getShow("Zeile")+" ";
                 String sBefehl2=Sort.gets(Node.getLabel(), 0)+" ("+sBefehl+")";
                 g.fixtestError(sZ+ iZeile+":"+sBefehl+"/"+iAIC_Spalte);
                 if(Calc.BoSpalte.contains(sBefehl) && iAIC_Spalte != null)
                  VecFehler.addElement(sZ+ iZeile + " bei Befehl " + sBefehl2 + ": "+g.getShow("Spalte_verboten"));
                 else if(Calc.BmSpalte.contains(sBefehl) && iAIC_Spalte == null)
                  VecFehler.addElement(sZ+ iZeile + " bei Befehl " + sBefehl2 + ": "+g.getShow("Spalte_erforderlich"));
                 else if (sBefehl.equals("write data record") && AbfrageNotSave(iAIC_Spalte))
                  VecFehler.addElement(sZ+ iZeile + " bei Befehl " + sBefehl2 + ": Abfrage dafür nicht erlaubt");
                 else if(Calc.BmEingabe.contains(sBefehl) && !bEingabe)
                     VecFehler.addElement(sZ + iZeile + " bei Befehl " + sBefehl2 + ": "+g.getShow("Eingabe_erforderlich"));
                 else if(!Calc.BkSpalte.contains(sBefehl) && iAIC_Spalte != null && (bEingabe || bVar))
                   VecFehler.addElement(sZ + iZeile + " bei Befehl " + sBefehl2 + ": "+g.getShow("Kombination_verboten"));
                 else if((iBits & Calc.VAR) > 0 && !sBefehl.equals("clear") && !bVar)
                   VecFehler.addElement(sZ + iZeile + " bei Befehl " + sBefehl2 + ": var ohne Variable verboten");
                 else if (Calc.Bdead.contains(sBefehl))
                   VecFehler.addElement(sZ + iZeile + " Befehl " + sBefehl2 + " "+g.getShow("verboten"));
                 else if (Calc.Bdep.contains(sBefehl))
                   VecWarnung.addElement(sZ + iZeile + " Befehl " + sBefehl2 + " sollte nicht mehr verwendet werden");
                 else if((iBits & Calc.SPALTE) > 0 && iAIC_Spalte == null)
                   VecFehler.addElement(sZ + iZeile + " Befehl " + sBefehl2 + " auf Spalte ohne Spalte nicht erlaubt");
               }
            }
			if(iAIC_Spalte!=null)
                          Qry.add("AIC_Spalte",iAIC_Spalte);

                        //Qry.add("MBits",sArt.equals("While")?Calc.SCHLEIFE:0);

			if(sArtRoot.equals("If-Ja"))
				Qry.add("BEF_AIC_Befehl",iAIC_Befehl);
			else if(sArtRoot.equals("If-Nein"))
				Qry.add("BEF2_AIC_Befehl",iAIC_Befehl);
			else if(sArtRoot.equals("While"))
				Qry.add(((Vector)NodeRoot.getUserData()).elementAt(6)==null?"BEF_AIC_Befehl":"BEF2_AIC_Befehl",iAIC_Befehl);
                        boolean bMemo=!((String)VecInvisible.elementAt(4)).equals("");
			int iAIC = Qry.insert("Befehl",bMemo || sArt.equals("If") || sArt.equals("While") || (iBefehl>0 && iBefehl==Sort.geti(VecInvisible,7)));
			if(bMemo)
				g.setMemo((String)VecInvisible.elementAt(4),((String)VecInvisible.elementAt(4)).equals("")?"":"Titel",iTabBefehl,iAIC,0);
                        int iAnz=VecInvisible.size();
                        if (iAnz<9)
                        {
                          for (int i2=iAnz;i2<9;i2++)
                            VecInvisible.addElement(null);
                        }
                        VecInvisible.setElementAt(new Integer(iAIC),7);
			if(sArt.equals("If"))
			{
				Vector VecBed = Node.getChildren();

				Speichern_Rekursiv(iAIC_Modell,(JCOutlinerFolderNode)VecBed.elementAt(0),new Integer(iAIC));
				Speichern_Rekursiv(iAIC_Modell,(JCOutlinerFolderNode)VecBed.elementAt(1),new Integer(iAIC));
			}
			else if(sArt.equals("While"))
				Speichern_Rekursiv(iAIC_Modell,(JCOutlinerFolderNode)Node,new Integer(iAIC));
                        //g.progInfo("VecInvisible="+VecInvisible);
		}
		Qry.free();
	}

  private boolean AbfrageNotSave(int iSp)
  {
    g.fixtestError("AbfrageNotSave mit Spalte "+iSp);
    long lBits=SQL.getLong(g, "select b.bits from spalte s join abfrage a on s.aic_spalte=? and s.aic_abfrage=a.aic_abfrage join begriff b on a.aic_begriff=b.aic_begriff",""+iSp);
    return (lBits&Abfrage.cstSumme)>0 || (lBits&Abfrage.cstDistinct)>0;
  }

	@SuppressWarnings("unchecked")
	private void Verschieben(JCOutlinerNode[] NodSelected, boolean bRauf)
	{
		Vector<JCOutlinerNode> VecNodes = NodSelected[0].getParent().getChildren();
		int iPos=VecNodes.indexOf(NodSelected[0]);
		int iPos2=iPos+NodSelected.length;

		JCOutlinerNode NodAndere= VecNodes.elementAt(bRauf ? iPos-1:iPos2);
		VecNodes.remove(NodAndere);
		if (bRauf && iPos2>VecNodes.size())
		  VecNodes.addElement(NodAndere);
		else
		  VecNodes.insertElementAt(NodAndere, bRauf ? iPos2-1:iPos);

		Vector VecSelectedData = (Vector)NodSelected[0].getUserData();
		if(((String)VecSelectedData.elementAt(2)).equals("If-Ja") || ((String)VecSelectedData.elementAt(2)).equals("If-Nein"))
		{
		  //int iPos=VecNodes.indexOf(NodSelected);
		  //JCOutlinerNode NodAndere=(JCOutlinerNode) VecNodes.elementAt(iPos+(bRauf?-1:1));
			Vector<Object> VecAndereData = (Vector)NodAndere.getUserData();
			Object oSelectedArt = VecSelectedData.elementAt(2);
			VecSelectedData.setElementAt(VecAndereData.elementAt(2),2);
			VecAndereData.setElementAt(oSelectedArt,2);

			VecSelectedData = (Vector)NodSelected[0].getLabel();
			VecAndereData = (Vector)NodAndere.getLabel();
			oSelectedArt = VecSelectedData.elementAt(0);
			VecSelectedData.setElementAt(VecAndereData.elementAt(0),0);
			VecAndereData.setElementAt(oSelectedArt,0);
		}

		OutBuild.folderChanged(NodSelected[0].getParent());

	}

        @SuppressWarnings("unchecked")
        private void setBreak(int i)
        {
          JCOutlinerNode AktBuildNode = OutAkt==null ? null:OutAkt.getSelectedNode();
          if(AktBuildNode!=null && AktBuildNode!=OutAkt.getRootNode())
          {
            if (AktBuildNode instanceof JCOutlinerFolderNode)
              ((JCOutlinerFolderNode)AktBuildNode).setState(BWTEnum.FOLDER_OPEN_ALL);
            Vector<Object> VecInvisible = (Vector)AktBuildNode.getUserData();
            Object ObjStatus=VecInvisible.elementAt(8);
            Integer IntAic=(Integer)VecInvisible.elementAt(7);
            String sEingabe=(String)VecInvisible.elementAt(3);
            String sVar=(String)VecInvisible.elementAt(9);
            //g.progInfo("Aic="+VecInvisible.elementAt(7)+"/"+ObjStatus);
            if (i==1 || ObjStatus ==null && i==0)
            {
              makeBreakStyle(AktBuildNode,true,g.ColBreakpoint);
              VecInvisible.setElementAt("B",8);
              if (Aufruf.VecDebug!=null)
                Aufruf.VecDebug.removeElement(IntAic);
              if (Aufruf.VecBreak==null)
                Aufruf.VecBreak=new Vector<Integer>();
              if (!Aufruf.VecBreak.contains(IntAic))
                Aufruf.VecBreak.addElement(IntAic);
            }
            else if (i==2 || ObjStatus !=null && ObjStatus.equals("B") && i==0)
            {
              makeBreakStyle(AktBuildNode,true,g.ColDebug);
              VecInvisible.setElementAt( "D",8);
              if (Aufruf.VecDebug==null)
                Aufruf.VecDebug=new Vector<Integer>();
              if (!Aufruf.VecDebug.contains(IntAic))
                Aufruf.VecDebug.addElement(IntAic);
              if (Aufruf.VecBreak!=null)
                Aufruf.VecBreak.removeElement(IntAic);
            }
            else if (i==3 || ObjStatus !=null && ObjStatus.equals("D") && i==0)
            {
              makeBreakStyle(AktBuildNode,false,null);
              VecInvisible.setElementAt(null,8);
              if (Aufruf.VecDebug!=null)
              {
                Aufruf.VecDebug.removeElement(IntAic);
                if (Aufruf.VecDebug.size()==0)
                  Aufruf.VecDebug=null;
              }
              if (Aufruf.VecBreak!=null)
              {
                Aufruf.VecBreak.removeElement(IntAic);
                if (Aufruf.VecBreak.size()==0)
                  Aufruf.VecBreak=null;
              }
              if (Aufruf.VecVar!=null)
              {
                Aufruf.VecVar.removeElement(sVar);
                if (Aufruf.VecVar.size()==0)
                  Aufruf.VecVar=null;
              }
            }
            else if (i==4 || ObjStatus !=null && ObjStatus.equals("V") && i==0)
            {
            	makeBreakStyle(AktBuildNode,true,g.ColVar);
                //VecInvisible.setElementAt( "V",8);
                if (Aufruf.VecVar==null)
                  Aufruf.VecVar=new Vector<String>();           
                if (!Aufruf.VecVar.contains(sVar))
                  Aufruf.VecVar.addElement(sVar);
            }
            else if (i==5 || ObjStatus !=null && ObjStatus.equals("E") && i==0)
            {
            	makeBreakStyle(AktBuildNode,true,g.ColVar);
                //VecInvisible.setElementAt( "V",8);
                if (Aufruf.VecEingabe==null)
                  Aufruf.VecEingabe=new Vector<String>();           
                if (!Aufruf.VecEingabe.contains(sEingabe))
                  Aufruf.VecEingabe.addElement(sEingabe);
            }
//            g.fixtestError("VecDebug="+Aufruf.VecDebug+", VecBreak="+Aufruf.VecBreak+", VecVar="+Aufruf.VecVar);
            EnableButtons();
          }
        }

        private void setBreakSpalte(int iSpalte,JCOutlinerNode AktSpalteNode)
        {
          if (iSpalte>0)
          {
            g.defInfo("setBreakSpalte:"+iSpalte);
            if (Aufruf.VecSpalten == null)
              Aufruf.VecSpalten = new Vector<Integer>();
            if (!Aufruf.VecSpalten.contains(iSpalte))
                Aufruf.VecSpalten.addElement(new Integer(iSpalte));
            makeBreakStyle(AktSpalteNode, true, g.ColBreakpoint);
            EnableButtons();
          }
        }
        
        private void setBreakBefehl(String sBefehl,JCOutlinerNode AktSpalteNode)
        {
          if (!Static.Leer(sBefehl))
          {
            g.fixtestError("setBreakBefehl:"+sBefehl);
            if (Aufruf.VecBefehl == null)
              Aufruf.VecBefehl = new Vector<String>();
            if (!Aufruf.VecBefehl.contains(sBefehl))
                Aufruf.VecBefehl.addElement(sBefehl);
            makeBreakStyle(AktSpalteNode, true, g.ColBreakpoint);
            EnableButtons();
          }
        }
        
        private void setBreakEingabe()
        {
        	String sEingabe=TxtEingabe.getText();
        	if (Aufruf.VecEingabe == null)
                Aufruf.VecEingabe = new Vector<String>();
              if (!Aufruf.VecEingabe.contains(sEingabe))
                  Aufruf.VecEingabe.addElement(sEingabe);
              EnableButtons();
        }
        
        private void setBreakVar()
        {
        	String sEingabe=TxtVar.getText();
        	if (Aufruf.VecVar == null)
                Aufruf.VecVar = new Vector<String>();
              if (!Aufruf.VecVar.contains(sEingabe))
                  Aufruf.VecVar.addElement(sEingabe);
              EnableButtons();
        }

        @SuppressWarnings("unchecked")
        private void setBreakSpalte()
        {
          JCOutlinerNode AktSpalteNode = OutSpalten.getSelectedNode();
          int iLevel=AktSpalteNode.getLevel();
          //g.fixInfo("setBreakSpalte Level="+iLevel);
          if (iLevel==4)
            setBreakSpalte(Sort.geti((Vector)AktSpalteNode.getUserData(), 0),AktSpalteNode);
          else
          {
            Vector<JCOutlinerNode> VecNodes=AktSpalteNode.getChildren();
            if (VecNodes != null && VecNodes.size()>0)
              for(int i=0;i<VecNodes.size();i++)
                setBreakSpalte(Sort.geti((Vector)VecNodes.elementAt(i).getUserData(), 0),AktSpalteNode);
          }
        }
        
        private void setBreakBefehl()
        {
        	JCOutlinerNode AktNode = OutAnweisung.getSelectedNode();
        	int iLevel=AktNode.getLevel();
        	if (iLevel==2)
        		setBreakBefehl(Sort.gets((Vector)AktNode.getLabel(), 1),AktNode);
        }

	@SuppressWarnings("unchecked")
	private void Kommentar()
	{
          JCOutlinerNode[] Nodes=OutBuild.getSelectedNodes();
          for (int i=0;i<Nodes.length;i++)
          {
            JCOutlinerNode NodeSelected = Nodes[i];//OutBuild.getSelectedNode();
            if (NodeSelected != null)
            {
              Vector<Object> VecInvisible = (Vector)NodeSelected.getUserData();
              String sArt = (String)VecInvisible.elementAt(2);

              if ((OutBuild.getRootNode() == NodeSelected || sArt.equals("If-Ja") || sArt.equals("If-Nein")) && NodeSelected.getState() != BWTEnum.FOLDER_OPEN_ALL)
                ((JCOutlinerFolderNode)NodeSelected).setState(BWTEnum.FOLDER_OPEN_ALL);

              if (OutBuild.getRootNode() != NodeSelected && !sArt.equals("If-Ja") && !sArt.equals("If-Nein"))
              {
                int iBits=Sort.geti(VecInvisible,5);
                boolean bKommentar = (iBits&Calc.HIDE)==0;//!((Boolean)VecInvisible.elementAt(5)).booleanValue();
                VecInvisible.setElementAt((iBits&0xfffefff)+(bKommentar?Calc.HIDE:0), 5);
                makeKommentarStyle(NodeSelected, bKommentar,false);
              }
            }
            setBitChange(NodeSelected,true);
          }
	}

        private void makeBreakStyle(JCOutlinerNode Node, boolean bBreak,Color Col)
        {
                //Font FontStandard = OutBuild.getFont();
                JCOutlinerNodeStyle StyNode = new JCOutlinerNodeStyle(Node.getStyle()==null?(new JCOutlinerComponent()).getDefaultNodeStyle():Node.getStyle());
                StyNode.setForeground(bBreak?Col:OutBuild.getForeground());
                //StyNode.setFont(new Font(g.fontModell.getName(),/*bBreak?Font.BOLD:*/g.fontModell.getStyle(),g.fontModell.getSize()));
                Node.setStyle(StyNode);
        }

	private void makeKommentarStyle(JCOutlinerNode Node, boolean bKommentar,boolean bExtern)
	{
		//Font FontStandard = OutBuild.getFont();
		JCOutlinerNodeStyle StyNode = new JCOutlinerNodeStyle(Node.getStyle()==null?(new JCOutlinerComponent()).getDefaultNodeStyle():Node.getStyle());
                StyNode.setForeground(bKommentar? bExtern ? Color.green:g.ColRem:OutBuild.getForeground());
		StyNode.setFont(bKommentar ? g.fontRem:g.fontModell);//new Font(g.fontModell.getName(),bKommentar?Font.ITALIC+(bExtern?Font.BOLD:0):g.fontModell.getStyle(),g.fontModell.getSize()));
		Node.setStyle(StyNode);		
	}

	@SuppressWarnings("unchecked")
	private void Not(JCOutlinerNode Node)
	{
		Vector<String> VecVisible = (Vector)Node.getLabel();
		Vector<String> VecInvisible = (Vector)Node.getUserData();
		String sArt = VecInvisible.elementAt(2);

		if(sArt.equals("If"))
			Verschieben(new JCOutlinerNode[]{(JCOutlinerNode)Node.getChildren().elementAt(0)},false);
		else
		{
			String sVisible = VecVisible.elementAt(0);
			String sInvisible = VecInvisible.elementAt(6);

			if(sInvisible==null)
			{
				VecInvisible.setElementAt(sVisible,6);
				VecVisible.setElementAt(sNicht+" "+sVisible,0);
			}
			else
			{
				VecVisible.setElementAt(sInvisible,0);
				VecInvisible.setElementAt(null,6);
			}

			OutBuild.folderChanged(Node);
		}
	}

        @SuppressWarnings("unchecked")
        private void setBitChange(JCOutlinerNode Node,boolean b)
        {
          Vector<Object> VecInvisible = (Vector)Node.getUserData();
          int iMBits=(Sort.geti(VecInvisible,5)&(0xfffffff-Calc.CHANGE))+(b?Calc.CHANGE:0);
          VecInvisible.setElementAt(new Integer(iMBits),5);
          if (b)
          {
        	  Vector<Object> VecVisible=(Vector)Node.getLabel();
        	  String sBits=Sort.gets(VecVisible, 4);
        	  if (sBits==null) sBits="";
        	  if (sBits.length()==0 || !sBits.startsWith("*"))
        		  VecVisible.setElementAt("*"+sBits,4);
          }
        }

        @SuppressWarnings("unchecked")
        private void setBits(JCOutlinerNode Node,boolean bSet)//,int iBits,int iBit,boolean b)
        {
          Vector<String> VecVisible = (Vector)Node.getLabel();
          Vector<Object> VecInvisible = (Vector)Node.getUserData();
          if (VecInvisible.size()<6)
            return;
          int iBBits=Sort.geti(VecInvisible,5);//&(0xffff-iBits))+(b?iBit:0); // Befehl-Bits
          boolean bSpalte=VecInvisible.elementAt(1)!=null;
//          g.fixtestError("setBits:"+bSet+"/"+bSpalte+" bei "+VecVisible);
          if (bSet)
          {
            String sArt = Sort.gets(VecInvisible,2);
            if (!bSpalte || sArt.equals("While") && RadImmer.isSelected())
              RadAuto.setSelected(true);
            iBBits=(iBBits&(Calc.SCHLEIFE+(bSet ? 0:Calc.HIDE)))+(CbxVar.isSelected() ? Calc.VAR:0)+(CbxPerm.isSelected() ? Calc.PERM:0)+(RadVich.isSelected() ? Calc.VICH:RadVread.isSelected() ? Calc.V_R:RadValle.isSelected() ? Calc.VALL:Calc.VLOK)
            		+(RadImmer.isSelected() ? Calc.IMMER:RadNie.isSelected() ? Calc.NIE:Calc.AUTO)
                +(CbxGruppe.isSelected() ? Calc.GRUPPE:0)+(CbxBSave.isSelected() ? Calc.SAVE:0)+(CbxSpalte.isSelected() ? Calc.SPALTE:0)+(CbxReg.isSelected() ? Calc.REG:0)+(CbxAlle.isSelected() ? Calc.ALLE:0)+(CbxThread2.isSelected() ? Calc.THREAD:0)
                +(RadEmpty.isSelected() ? Calc.EMPTY:RadVec.isSelected() ? Calc.USE_VEC : RadVecBew.isSelected() ? Calc.VEC_BEW:Calc.NORMAL)+Calc.CHANGE
                +(CbxKSZR.isSelected() ? Calc.KSZR:0)+(CbxKBZR.isSelected() ? Calc.KBZR:0)+(CbxSum.isSelected() ? Calc.SUM:0)+(RadConcat.isSelected() ? Calc.CONCAT:RadCWS.isSelected() ? Calc.CWS:RadInit.isSelected() ? Calc.INIT:0)
                +(RadMadd.isSelected() ? Calc.ADD:RadMsub.isSelected() ? Calc.SUB:RadMinit.isSelected() ? Calc.MINIT:0);
//            g.fixtestError("setBits:"+Sort.gets(VecVisible,0)+"="+Integer.toHexString(iBBits));
          }
          VecInvisible.setElementAt(new Integer(iBBits),5);
          int iArt=iBBits&Calc.ART;
          int iVArt=iBBits&Calc.VART;
          if (VecVisible.size()<5)
            return;
          VecVisible.setElementAt(((iBBits&Calc.CHANGE)==0 ? "":"*")+((iBBits&Calc.SUM)==0 ? "":"S")+((iBBits&Calc.VAR)==0 ? "":"v"+(iVArt==Calc.VICH ? "2":iVArt==Calc.V_R ? "3":iVArt==Calc.VALL ? "4":"1")+((iBBits&Calc.PERM)==0 ? "":"p"))
        		  +((iBBits&Calc.GRUPPE)==0 ? "":"G")+((iBBits&Calc.SAVE)==0 ? "":"s")+
                                  ((iBBits&Calc.SPALTE)==0 ? "":"+")+((iBBits&Calc.REG)==0 ? "":"R")+((iBBits&Calc.ALLE)==0 ? "":"A")+((iBBits&Calc.THREAD)==0 ? "":"T")+((iBBits&Calc.KSZR)==0 ? "":"kS")+((iBBits&Calc.KBZR)==0 ? "":"kB")+
                                  ((iBBits&Calc.C_ART)==0 ? "":(iBBits&Calc.C_ART)==Calc.CONCAT ? "C":(iBBits&Calc.C_ART)==Calc.CWS ? "CW":"I")+
                                  ((iBBits&Calc.M_ART)==0 ? "":(iBBits&Calc.M_ART)==Calc.ADD ? "+":(iBBits&Calc.M_ART)==Calc.SUB ? "-":".")+
            (iArt==Calc.NORMAL ? "":" "+g.getBegriffS("Show",iArt==Calc.EMPTY ? "empty":/*iArt==Calc.SYNC ? "sync":*/iArt==Calc.USE_VEC ? "Vec":iArt==Calc.VEC_BEW ? "VecBew":"-")),4);
//          g.fixtestError("setBits bei "+VecVisible.elementAt(0)+"->"+VecVisible.elementAt(4));
          int iRF=iBBits&Calc.REFRESH;
          VecVisible.setElementAt(bSpalte?(iRF==Calc.AUTO?"":g.getBegriffS("Show",iRF==Calc.IMMER?"immer":iRF==Calc.NIE?"nie":"-")):"",3);
          if (bSet)
            OutBuild.folderChanged(Node);
        }

        private void LoadParameter()
        {
          Parameter Para = new Parameter(g,"DefModell");
          Para.getParameter("Modell",true,false);
          if(Para.bGefunden)
          {
            OutModell.setColumnWidth(0,Para.int1);
            OutModell.setColumnWidth(1,Para.int2);
            OutModell.setColumnWidth(2,Para.int3);
            OutModell.setColumnWidth(3,Para.int4);
            iW[0]=Para.int1;
            iW[1]=Para.int2;
            iW[2]=Para.int3;
            iW[3]=Para.int4;
          }
          Para.getParameter("Modell2",true,false);
          if(Para.bGefunden)
          {
            OutModell.setColumnWidth(4,Para.int1);
            OutModell.setColumnWidth(5,Para.int2);
            OutModell.setColumnWidth(6,Para.int3);
            OutModell.setColumnWidth(7,Para.int4);
            iW[36]=Para.int1;
            iW[37]=Para.int2;
            iW[38]=Para.int3;
            iW[39]=Para.int4;
            g.progInfo("36-39:"+iW[36]+"/"+iW[37]+"/"+iW[38]+"/"+iW[39]);
          }
          Para.getParameter("Code",true,false);
          if(Para.bGefunden)
          {
            OutAnweisung.setColumnWidth(0,Para.int1);
            OutAnweisung.setColumnWidth(1,Para.int2);
            OutBedingung.setColumnWidth(0,Para.int3);
            OutBedingung.setColumnWidth(1,Para.int4);
            iW[4]=Para.int1;
            iW[5]=Para.int2;
            iW[6]=Para.int3;
            iW[7]=Para.int4;
          }
          Para.getParameter("Abfrage",true,false);
          if(Para.bGefunden)
          {
            OutSpalten.setColumnWidth(0,Para.int1);
            OutSpalten.setColumnWidth(1,Para.int2);
            if (Para.int3>0)
              OutSpalten.setColumnWidth(2,Para.int3);
            if (Para.int4>-1)
              OutSpalten.setColumnWidth(3,Para.int4);
            iW[8]=Para.int1;
            iW[9]=Para.int2;
            iW[10]=Para.int3;
            iW[11]=Para.int4;
          }
          Para.getParameter("Abfrage2",true,false);
          if(Para.bGefunden)
          {
            OutSpalten.setColumnWidth(4,Para.int1);
            OutSpalten.setColumnWidth(5,Para.int2);
            OutSpalten.setColumnWidth(6,Para.int3);
            OutSpalten.setColumnWidth(7,Para.int4);
            iW[12]=Para.int1;
            iW[13]=Para.int2;
            iW[14]=Para.int3;
            iW[15]=Para.int4;
          }
          Para.getParameter("Build3",true,false);
          boolean bNeu=!Para.bGefunden || Para.int1!=1;
          if(bNeu)
          {
            iW[24]=250; // Bezeichnung
            iW[25]=60;  // Kennung
            iW[26]=40;  // Eingabe
            iW[27]=40;  // Refresh
            iW[28]=40;  // Bits
            iW[29]=80;  // Stt-Bew
            iW[30]=110; // Abfrage
            iW[31]=150; // Spalte
            iW[32]=33;  // Nr
            iW[33]=40;  // Memo
            for (int i=0;i<10;i++)
              OutBuild.setColumnDisplayWidth(i,iW[i+24]);
            iW[35]=0; // Ver
            iW[44]=10; // Einrückung
          }
          else
          {
            OutBuild.setColumnDisplayWidth(8,Para.int2>200 ? 30:Para.int2);  // Nr
            OutBuild.setColumnDisplayWidth(9,Para.int3);  // Memo
            iW[35]=Para.int1;
            iW[32]=Para.int2;
            iW[33]=Para.int3;
            iW[44]=Para.int4;
            Para.getParameter("Build",true,false);
            OutBuild.setColumnDisplayWidth(0,Para.int1<150 ? 250:Para.int1); // Bezeichnung
            OutBuild.setColumnDisplayWidth(1,Para.int2>200 ? 60:Para.int2);  // Kennung
            OutBuild.setColumnDisplayWidth(2,Para.int3>400 ? 40:Para.int3); // Eingabe
            OutBuild.setColumnDisplayWidth(3,Para.int4>200 ? 40:Para.int4); // Refresh
            iW[24]=Para.int1;
            iW[25]=Para.int2;
            iW[26]=Para.int3;
            iW[27]=Para.int4;

            Para.getParameter("Build2",true,false);
            OutBuild.setColumnDisplayWidth(4,Para.int1>200 ? 40:Para.int1);
            OutBuild.setColumnDisplayWidth(5,Para.int2);
            OutBuild.setColumnDisplayWidth(6,Para.int3);
            OutBuild.setColumnDisplayWidth(7,Para.int4);
            iW[28]=Para.int1;
            iW[29]=Para.int2;
            iW[30]=Para.int3;
            iW[31]=Para.int4;

            g.progInfo("1:"+iW[24]+"/"+iW[25]+"/"+iW[26]+"/"+iW[27]);
            g.progInfo("2:"+iW[28]+"/"+iW[29]+"/"+iW[30]+"/"+iW[31]);
            g.progInfo("3:"+iW[32]+"/"+iW[33]+"/"+iW[34]+"/"+iW[35]);
          }
          if (iW[44]>0)
            OutBuild.setNodeIndent(iW[44]);
          for (int i=0;i<10;i++)
            OutBuild.setColumnWidth(i,OutBuild.getColumnDisplayWidth(i));

          Para.getParameter("Fenster",false,true);
          if(Para.bGefunden)
          {
            FomBuild.setBounds(Para.int1,Para.int2,Para.int3,Para.int4);
            iW[16]=Para.int1;
            iW[17]=Para.int2;
            iW[18]=Para.int3;
            iW[19]=Para.int4;
          }
          else
          {
            Dimension DimS=Toolkit.getDefaultToolkit().getScreenSize();
            FomBuild.setBounds(DimS.width*2/3,0,400,DimS.height-20);
          }
          Para.getParameter("Fenster2",false,true);
          if(Para.bGefunden)
          {
            FomSpalten.thisFrame.setBounds(Para.int1,Para.int2,Para.int3,Para.int4);
            iW[40]=Para.int1;
            iW[41]=Para.int2;
            iW[42]=Para.int3;
            iW[43]=Para.int4;
          }
          else
          {
            Dimension DimS=Toolkit.getDefaultToolkit().getScreenSize();
            FomSpalten.thisFrame.setBounds(DimS.width/3,0,400,DimS.height-20);
          }
          /*Para.getParameter("Befehl",true,false);
          if(Para.bGefunden)
          {
            OutBuild.setColumnWidth(0,Para.int1);
            OutBuild.setColumnWidth(1,Para.int2);
            OutBuild.setColumnWidth(2,Para.int3);
            //OutBuild.setColumnWidth(3,Para.int4);
            iW[12]=Para.int1;
            iW[13]=Para.int2;
            iW[14]=Para.int3;
            iW[15]=Para.int4;
          }*/

          Para.getParameter("Optionen",true,false);
          if(Para.bGefunden)
          {
            CbxSyncSpalte.setSelected((Para.int1&cstSyncSpalte)>0);
            CbxSyncVar.setSelected((Para.int1&cstSyncVar)>0);
            CbxSyncBefehl.setSelected((Para.int1&cstSyncBefehl)>0);
            CbxColMemo.setSelected((Para.int1&cstColMemo)>0);
            CbxColSpalte.setSelected((Para.int1&cstColSpalte)>0);
            //CbxDatentyp.setSelected((Para.int1&16)>0);
            CbxColZR.setSelected((Para.int1&cstColZR)>0);
            CbxColQry.setSelected((Para.int1&cstColQry)>0);
            //CbxColKeinZeitraum.setSelected((Para.int1&128)>0);
            CbxOriginalBez.setSelected((Para.int1&cstOriginalBez)>0);
            CbxColPush.setSelected((Para.int1&cstColPush)>0);
            CbxBez.setSelected((Para.int1&cstBez)>0);
            CbxColBefehl.setSelected((Para.int1&cstColBefehl)>0);
            CbxColEingabe.setSelected((Para.int1&cstColEingabe)>0);
            CbxColVar.setSelected((Para.int1&cstColVar)>0);
            CbxSyncBits.setSelected((Para.int1&cstSyncBits)>0);
            CbxZusammen.setSelected((Para.int1&cstZusammen)>0);
            CbxNurAic.setSelected((Para.int1&cstNurAic)>0);
            CbxKeinTooltip.setSelected((Para.int1&cstKeinTooltip)>0);
            //CbxSortNow.setSelected((Para.int1&cstSortNow)>0);
            iW[20]=Para.int1;
            iW[21]=Para.int2;
            iW[22]=Para.int3;
            iW[23]=Para.int4;
            if (!bNeu && Para.int2>0)
              iEinBits=Para.int2;
            //if (Para.int2>0)
            //  OutBuild.setColumnWidth(4,Para.int2);
            if (Para.int3>0)
              OutAnweisung.setColumnWidth(2,Para.int3);
            if (Para.int4>0)
              OutBedingung.setColumnWidth(2,Para.int4);
          }
          Para.free();
          for(int i=1;i<OutBuild.getColumnLabels().length;i++)
            if ((iEinBits&1<<i)==0)
            {
              int iWx=0;
              /*if (i<4)
                iWx=(iEinBits & 1 << i) > 0 ? iW[i + 24] > 0 ? iW[i + 24] : -999 : 0;
              else if(i<8)
                iWx=(iEinBits & 1 << i) > 0 ? iW[i + 32 - 4] > 0 ? iW[i + 32 - 4] : -999 : 0;
              else
                iWx=(iEinBits & 1 << i) > 0 ? iW[i + 41 - 8] > 0 ? iW[i + 41 - 8] : -999 : 0;*/
              iWx=(iEinBits & 1 << i) > 0 ? iW[i + 24] > 0 ? iW[i + 24] : -999 : 0;
              g.progInfo("B.setColumnDisplayWidth:"+i+"="+iWx);
              OutBuild.setColumnDisplayWidth(i,iWx);
            }
          for(int i=1;i<OutSpalten.getColumnLabels().length;i++)
            if ((iEinBits&1<<(i+12))==0)
            {
              //int iWx=(iEinBits & 1 << (i + 12)) > 0 ? i < 4 && iW[i + 8] > 0 ? iW[i + 8] : i < 8 && iW[i + 12] > 0 ? iW[i + 12] : -999 : 0;
              int iWx=(iEinBits & 1 << (i + 12)) > 0 ? iW[i + 8] > 0 ? iW[i + 8] : -999 : 0;
              g.progInfo("Sp.setColumnDisplayWidth:"+i+"="+iWx);
              OutSpalten.setColumnDisplayWidth(i, iWx);
            }
        }

        private void SaveParameter()
        {
          int iM1=OutModell.getColumnWidth(0);
          int iM2=OutModell.getColumnWidth(1);
          int iM3=OutModell.getColumnWidth(2);
          int iM4=OutModell.getColumnWidth(3);
          int iM5=OutModell.getColumnWidth(4);
          int iM6=OutModell.getColumnWidth(5);
          int iM7=OutModell.getColumnWidth(6);
          int iM8=OutModell.getColumnWidth(7);

          int iA1=OutAnweisung.getColumnWidth(0);
          int iA2=OutAnweisung.getColumnWidth(1);
          int iA3=OutAnweisung.getColumnWidth(2);
          int iBe1=OutBedingung.getColumnWidth(0);
          int iBe2=OutBedingung.getColumnWidth(1);
          int iBe3=OutBedingung.getColumnWidth(2);

          int iS1=OutSpalten.getColumnWidth(0);
          int iS2=OutSpalten.getColumnWidth(1);
          int iS3=OutSpalten.getColumnWidth(2);
          int iS4=OutSpalten.getColumnWidth(3);
          int iS5=OutSpalten.getColumnWidth(4);
          int iS6=OutSpalten.getColumnWidth(5);
          int iS7=OutSpalten.getColumnWidth(6);
          int iS8=OutSpalten.getColumnWidth(7);

          int iO1=OutBuild.getColumnWidth(0);
          int iO2=OutBuild.getColumnWidth(1);
          int iO3=OutBuild.getColumnWidth(2);
          int iO4=OutBuild.getColumnWidth(3);
          int iO5=OutBuild.getColumnWidth(4);
          int iO6=OutBuild.getColumnWidth(5);
          int iO7=OutBuild.getColumnWidth(6);
          int iO8=OutBuild.getColumnWidth(7);
          int iO9=OutBuild.getColumnWidth(8);
          int iO10=OutBuild.getColumnWidth(9);

          int iVer=1;
          int iNI=OutBuild.getNodeIndent();

          Rectangle rv=FomBuild.getBounds();
          /*int iB1=rv.x;
          int iB2=rv.y;
          int iB3=rv.width;
          int iB4=rv.height;*/
          Rectangle rv2=FomSpalten.thisFrame.getBounds();
          /*int iB1=OutBuild.getColumnWidth(0);
          int iB2=OutBuild.getColumnWidth(1);
          int iB3=OutBuild.getColumnWidth(2);*/
          //int iB4=OutBuild.getColumnWidth(3);
          int iBits=(CbxSyncSpalte.isSelected()?cstSyncSpalte:0)+(CbxSyncBefehl.isSelected()?cstSyncBefehl:0)+(CbxColMemo.isSelected()?cstColMemo:0)+
              (CbxColSpalte.isSelected()?cstColSpalte:0)/*+(CbxDatentyp.isSelected()?16:0)*/+(CbxColZR.isSelected()?cstColZR:0)+(CbxColQry.isSelected()?cstColQry:0)+
              /*(CbxColKeinZeitraum.isSelected()?128:0)+*/(CbxOriginalBez.isSelected()?cstOriginalBez:0)+(CbxColPush.isSelected()?cstColPush:0)+(CbxBez.isSelected()?cstBez:0)+
              (CbxColBefehl.isSelected()?cstColBefehl:0)+(CbxColEingabe.isSelected()?cstColEingabe:0)+(CbxSyncBits.isSelected()?cstSyncBits:0)+(CbxZusammen.isSelected()?cstZusammen:0)+
              (CbxNurAic.isSelected()?cstNurAic:0)+(CbxKeinTooltip.isSelected()? cstKeinTooltip:0)+(CbxSyncVar.isSelected()?cstSyncVar:0)+(CbxColVar.isSelected()?cstColVar:0);

          Parameter Para = new Parameter(g,"DefModell");
          if (iM1!=iW[0] || iM2!=iW[1] || iM3!=iW[2] || iM4!=iW[3])
            Para.setParameter("Modell","",iM1,iM2,iM3,iM4,true,false);
          if (iM5!=iW[36] || iM6!=iW[37] || iM7!=iW[38] || iM8!=iW[39])
            Para.setParameter("Modell2","",iM5,iM6,iM7,iM8,true,false);
          if (iA1!=iW[4] || iA2!=iW[5] || iBe1!=iW[6] || iBe2!=iW[7])
            Para.setParameter("Code","",iA1,iA2,iBe1,iBe2,true,false);
          if (iS1!=iW[8] || iS2!=iW[9] || iS3!=iW[10] || iS4!=iW[11])
            Para.setParameter("Abfrage","",iS1,iS2,iS3,iS4,true,false);
          if (iS5!=iW[12] || iS6!=iW[13] || iS7!=iW[14] || iS8!=iW[15])
            Para.setParameter("Abfrage2","",iS5,iS6,iS7,iS8,true,false);
          if (iBits!=iW[20] || iEinBits!=iW[21] || iA3!=iW[22] || iBe3!=iW[23])
            Para.setParameter("Optionen","",iBits,iEinBits,iA3,iBe3,true,false);
          if (rv.x!=iW[16] || rv.y!=iW[17] || rv.width!=iW[18] || rv.height!=iW[19])
            Para.setParameter("Fenster","",rv.x,rv.y,rv.width,rv.height,false,true);
          if (iO1!=iW[24] || iO2!=iW[25] || iO3!=iW[26] || iO4!=iW[27])
            Para.setParameter("Build","",iO1,iO2,iO3,iO4,true,false);
          if (iO5!=iW[28] || iO6!=iW[29] || iO7!=iW[30] || iO8!=iW[31])
            Para.setParameter("Build2","",iO5,iO6,iO7,iO8,true,false);
          if (iVer!=iW[35] || iO9!=iW[32] || iO10!=iW[33] || iNI!=iW[44])
            Para.setParameter("Build3","",iVer,iO9,iO10,iNI,true,false);
          if (rv2.x!=iW[40] || rv2.y!=iW[41] || rv2.width!=iW[42] || rv2.height!=iW[43])
            Para.setParameter("Fenster2","",rv2.x,rv2.y,rv2.width,rv2.height,false,true);
          //if (iB1!=iW[12] || iB2!=iW[13] || iB3!=iW[14] || iB4!=iW[15])
          //  Para.setParameter("Befehl","",iB1,iB2,iB3,-1,true,false);

          Para.free();
        }

   // add your data members here
   private static final int BEZ=   1;
   private static final int KEN=   1<<1;
   private static final int EIN=   1<<2;
   private static final int RF=    1<<3;
   private static final int BITS=  1<<4;
   private static final int TYP=   1<<5;
   private static final int ABF=   1<<6;
   private static final int SP=    1<<7;
   private static final int NR=    1<<8;
   private static final int MEMO=  1<<9;

   //private static final int BEZ2= 1<<12;
   private static final int AIC=  1<<13;
   private static final int DT=   1<<14;
   private static final int ANZ=  1<<15;
   private static final int STT=  1<<16;
   private static final int GRUP= 1<<17;
   private static final int SORT= 1<<18;
   private static final int NOS=  1<<19;
   private static final int NOZ=  1<<20;
   private static final int NOR=  1<<21;
   private static final int DIST= 1<<22;

   private static final int cstSyncSpalte=1;
   private static final int cstSyncBefehl=2;
   private static final int cstColMemo   =4;
   private static final int cstColSpalte =8;
   private static final int cstDatentyp=1<<4;
   private static final int cstColZR=   1<<5;
   private static final int cstColQry=  1<<6;
   private static final int cstColKeinZeitraum=1<<7;
   private static final int cstOriginalBez=1<<8;
   private static final int cstColPush=    1<<9;
   private static final int cstBez=       1<<10;
   private static final int cstColBefehl= 1<<11;
   private static final int cstColEingabe=1<<12;
   private static final int cstSyncBits=  1<<13;
   private static final int cstZusammen=  1<<14;
   private static final int cstNurAic=    1<<15;
   private static final int cstKeinTooltip=1<<16;
   private static final int cstSyncVar=   1<<17;
   private static final int cstColVar=    1<<18;
   //private static final int cstSortNow=   1<<17;
   
   private static final int EINGABE=1;
   private static final int VAR=2; // 0x0008;
   private static final int ART=3; // 0x0070;
   private static final int USE_MOD=4;
   private static final int USE_JOK=5;

   private Global g;
   private ActionListener AL1=null;
   private int iTabBefehl;
   private AUOutliner OutModell = new AUOutliner();
   private AUOutliner OutAnweisung = new AUOutliner();
   private AUOutliner OutBedingung = new AUOutliner();
   private AUOutliner OutSpalten = new AUOutliner();
   private AUOutliner OutBuild = new AUOutliner();
   private AUOutliner OutAkt = null;
   private AUTextArea MemoKnoten = new AUTextArea();
   private static JFrame FomBuild=null;
   private static Formular FomSpalten=null;
//   private JPanel PnlVar=null;

   private Tabellenspeicher TabModell;
   private Tabellenspeicher TabModule=null;
   private Tabellenspeicher TabSpalten;
   private Tabellenspeicher TabCode;

   //private JButton BtnShow;
   private JButton BtnNeu;
   //private JButton BtnKopie;
   //private JButton BtnLoeschen;
   private JButton BtnBearbeiten;
   //private JButton BtnBearbeitenModell;
   private JButton BtnSpeichern;
   private JButton BtnBeenden;
   private JButton BtnBack;
   private JButton BtnNeuOk;
   private JButton BtnNeuAbbruch;
   private JButton BtnHinzufuegen;
   private JButton BtnEntfernen;
   private JButton BtnRauf;
   private JButton BtnRunter;
   private JButton BtnErsetzen;
   private JButton BtnZurueck;
   private JButton BtnEdit;
   private JButton BtnInfoBreak;
   private JButton BtnDelBreak;
   //private JButton BtnNot;
   private JButton BtnAufruf;
   private JButton BtnAufrufOk;
   private JButton BtnReCalc;
   private JButton BtnMD;
   private JButton BtnFrage;
   private boolean bJa=false;
   private JButton BtnWeiter;
   private JButton BtnAufrufAbbruch;
   //private JButton BtnDruck;
   private JButton BtnAbfrage;
   //private JButton BtnAbfrage2;
   //private JButton BtnRefresh;
   private JButton BtnRefresh2;
   private JButton BtnSyncStamm;
   private JButton BtnVarG=null;
   private JButton BtnAbfG=null;
   private JButton BtnInitFrame;

   private JButton BtnUnd;
   private JButton BtnInit;
   private JButton BtnInfo;

   private JButton BtnAlle;
   private JButton BtnSub;

   private AUTextArea TxtMemoModell;
   private AUTextArea TxtMemoBefehl;
   //private Text TxtTitelBefehl = new Text("",40);

   private JCheckBox CbxDebug;

   private JDialog FrmNeu;
   private Text EdtDefBezeichnung = new Text("",255);
   private Text EdtBildname = new Text("",50);
   private Text EdtFarbe = new Text("",50);
   private Text EdtBezeichnung = new Text("",255);
   private Text EdtKennung = new Text("",98,Text.KENNUNG);
   private Text EdtKennzeichen = new Text("",40,Text.KENNUNG);
   private Text EdtAlias = new Text("",40);
   private BildEingabe BtnBild;
   private BildEingabe BtnBildFX;
   private JCheckBox CbxJeder;
   private JCheckBox CbxWeb;
   private JCheckBox CbxCombo;
   //private ComboSort CboPeriode2;
   private ComboSort CboPeriode;
   private EigenschaftsEingabe CboEigenschaft;
   private ComboSort CboModul;
//   private ComboSort CboMandant;
   private JCheckBox CbxPeriode;
   private JCheckBox CbxEF;
   private JCheckBox CbxZRhold;
   private JCheckBox CbxMenge;
   private JCheckBox CbxKeineFrage;
   private JCheckBox CbxRefresh;
   private JCheckBox CbxOhneStamm;
   private JCheckBox CbxRecalc;
   private JCheckBox CbxAServer;
   //private JCheckBox CbxAntwort;
   private JCheckBox CbxKnopf;
   private JCheckBox CbxSave;
   //private JCheckBox CbxnachSave;
   private JCheckBox CbxThread;
   private JCheckBox CbxNS;
   private Zahl ZahlBew;
   private Zahl ZahlBBeg;
   private JCheckBox CbxCommit;
   private JCheckBox CbxAbbruch;
   private JCheckBox CbxUseQry;
   private JCheckBox CbxMitSo;
   private JCheckBox CbxJokerStt;
   private JCheckBox CbxDelJS;
   private JCheckBox CbxNbAServer;
   private JCheckBox CbxDefImport;
   private JCheckBox CbxMDialog;
   private JCheckBox CbxEigenG;
   private JCheckBox CbxAmpel;
   private JCheckBox CbxErgebnis;
   private JCheckBox CbxMassExport;
   private JCheckBox CbxDruckZR;
   private JCheckBox CbxClean;
   private JCheckBox CbxSperre;
   private JCheckBox CbxNurTest;
   private JCheckBox CbxTod;
   private JCheckBox CbxAmpelWeb;
   private JCheckBox CbxWeiter;
   private AUTextArea EdtMemo = new AUTextArea();
   private AUTextArea EdtTooltip = new AUTextArea();
   private JLabel LblBegriff= new JLabel();
   private JLabel LblModell= new JLabel();
   private JLabel LblAngelegt= new JLabel();
   private JLabel LblVon= new JLabel();
   private JLabel LblImportzeit= new JLabel();

   private JDialog FrmAufruf;
   private Text EdtName=new Text("",25);
//   private AUPasswort EdtPW=new AUPasswort();
   private Datum DatVon;
   private Datum DatBis;
   private ComboSort CboBewegungstyp;
   private ComboSort CboProg;
   private ComboSort CboStammtyp;
   private AbfrageEingabe CboAbfrage;
   private AbfrageEingabe CboAbfrage2;
   private AbfrageEingabe CboAbfWebDialog;
   private AbfrageEingabe CboAbfWebAmpel;
   private SpinBox SB_MaxB=new SpinBox();
   private boolean bCAF=true;
   //private ComboSort CboStammsatz;
   private AUCheckBox CbxBew;
   private StammEingabe SteStamm;
   private Tabellenspeicher TabStamm;
   /*private Zahl SpnAbbruch = new Zahl(0);
   private Zahl SpnDebug = new Zahl(0);
   private Zahl SpnPause = new Zahl(0);*/
   private AUCheckBox CbxTimer;
  //  private AUCheckBox CbxNeu;
   private AUCheckBox CbxWebTest;
   //private JCheckBox CbxHierarchie;
   //private JCheckBox CbxSchliessbar;
   //private JCheckBox CbxSynchron;
   private AUCheckBox CbxSyncSpalte;
   private AUCheckBox CbxSyncVar;
   private AUCheckBox CbxSyncBefehl;
   private AUCheckBox CbxSyncBits;
   private AUCheckBox CbxZusammen;
   private AUCheckBox CbxNurAic;
   //private AUCheckBox CbxSortNow;
   private AUCheckBox CbxColMemo;
   private AUCheckBox CbxColSpalte;
   private AUCheckBox CbxColEingabe;
   private AUCheckBox CbxColVar;
   private AUCheckBox CbxColBefehl;
   //private AUCheckBox CbxDatentyp;
   //private AUCheckBox CbxColKeinZeitraum;
   private AUCheckBox CbxColQry;
   private AUCheckBox CbxColZR;
   private AUCheckBox CbxOriginalBez;
   private AUCheckBox CbxColPush;
   private AUCheckBox CbxKeinTooltip;
   private AUCheckBox CbxAic;
   private AUCheckBox CbxBez;
   private AUCheckBox CbxVoll;
   private AUCheckBox CbxCase;
   private AUCheckBox CbxNot;
   private AUCheckBox CbxVar;
   private JRadioButton RadVlokal;
   private JRadioButton RadVich;
   private JRadioButton RadVread;
   private JRadioButton RadValle;
   private AUCheckBox CbxPerm;
   private AUCheckBox CbxGruppe;
   private AUCheckBox CbxBSave;
   private AUCheckBox CbxSpalte;
   private AUCheckBox CbxReg;
   private AUCheckBox CbxAlle;
   private AUCheckBox CbxThread2;
   private AUCheckBox CbxKSZR;
   private AUCheckBox CbxKBZR;
   private AUCheckBox CbxSum;
   //private AUCheckBox CbxCache;
   private JRadioButton RadAuto;
   private JRadioButton RadImmer;
   private JRadioButton RadNie;

   private JRadioButton RadNormal;
   private JRadioButton RadEmpty;
   //private JRadioButton RadSync;
   private JRadioButton RadVec;
   private JRadioButton RadVecBew;
   
   private JRadioButton RadKein;
   private JRadioButton RadConcat;
   private JRadioButton RadCWS;
   private JRadioButton RadInit;
   
   private JRadioButton RadMkein;
   private JRadioButton RadMadd;
   private JRadioButton RadMsub;
   private JRadioButton RadMinit;

   private Text TxtEingabe = new Text("",255,Text.EINGABE);
   private Text TxtVar = new Text("",255,Text.EINGABE);
   private Text TxtCode = new Text("",62);
  // private JCheckBox CbxSc;

   //private Parameter Para;

   private boolean bSelectedNode=false;
   private boolean bEnableButtons=false;
   private String sAktOutliner;

   private JCOutlinerFolderNode NodeModell=null;
   private JCOutlinerNode AktBuildNode=null;

   private JCOutlinerNodeStyle StyModell = null;
   private JCOutlinerNodeStyle StyModellTod = null;
   private JCOutlinerNodeStyle StyIf = null;
   private JCOutlinerNodeStyle StyWhile = null;
   private JCOutlinerNodeStyle StyJa = null;
   private JCOutlinerNodeStyle StyNein = null;

   private String sNicht;
   private boolean bGeaendert = false;

   private Stack<JCOutlinerNode> StkHistory = new Stack<JCOutlinerNode>();
   private Calc AktCalc=null;
   private int iAIC_ModellOld=-1;
   private int iLast=0;
   private int iLastLog=0;

   private Tabellenspeicher TabInsert = null;
   private Stack<Object> StaBefehl=new Stack<Object>();
   private JPopupMenu popModell= new JPopupMenu();
   private JPopupMenu popSpalte= new JPopupMenu();
   private JPopupMenu popBefehl= new JPopupMenu();
   private JPopupMenu popBedingung= new JPopupMenu();
   private JPopupMenu popBuild= new JPopupMenu();
   private int iModellF=0;
   //private boolean bAlles=Toolkit.getDefaultToolkit().getScreenSize().width>1024;
   private int[] iW = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
   private int iModell=0;
   private int iModellA=0; // zuletzt aufgerufenes Modell
   private boolean bAlleSpalte=false;
   private static DefModell Self=null;
   private JMenu MnuSpaltenInfo;
   private JMenuItem MnuClose1;
   private JMenuItem MnuClose2;
   private JMenuItem MnuClose3;
   private JMenuItem MnuNeu;
   private JMenuItem MnuDel;
   private JMenuItem MnuKommentar;
   private JMenuItem MnuBreak;
   private JMenuItem MnuBreakVar;
   private JMenuItem MnuBreakEingabe;
   private JMenuItem MnuBreakSpalte;
   private JMenuItem MnuDebug;
   private JMenuItem MnuNothing;
   private int iMPos=-1;
   private int iEPos=-1;
   private int iBPos=-1;
   //private int iIPos=-1;
   //private int iWPos=-1;
   private long iMBits=0;
   private int iBefehl=-1;
   private boolean bHierachie=false;
   private boolean bModule=false;
   private boolean bTod=false;
   private boolean bWeb=false;
   private boolean bSubModelle;
   private Vector<Integer> VecModelleF=null;
   private JCOutlinerNode NodKopie=null;
   private Tabellenspeicher TabEin=null;
   private int iEinBits=(1<<22)-1;
   private JDialog Dlg=null;
   private Vector<String> VecFehler=null;
   private Vector<String> VecWarnung=null;
   private int iZeile=0;

   private JDialog DlgParameter;
   private Text TxtAnwKennung;
   private Text TxtAnwBezeichnung;
   private ComboSort CboAnwGruppe;
   private AUTextArea MemoAnw = new AUTextArea();
   private JCOutlinerNode NodAnw=null;
   private String[] sBuild;
   private ActionListener AL;
   private int iAnzFL=0;
   private Vector<Zahl> VecZB=new Vector<Zahl>();
   
   private boolean bGeladen=false;
   private JDialog DlgM=null;
   private AUOutliner OutM=null;
   private int iDM=0; // Begriff-Aic für Dialog-Modell
   private int iBlast=-1;
   private boolean bRefresh=false;
   private boolean bWeiter=false;

  //private boolean bNA=true;
   //private Vector VecModelle=null;
   //private int iDBits=0;
   /*private int iM=0;
   private int iC=1;
   private int iA=2;
   private int iB=0;*/
}

