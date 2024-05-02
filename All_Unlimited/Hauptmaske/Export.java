/*
    All_Unlimited-Hauptmaske-Export.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.Component;
//import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Vector;

import java.io.*;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;

import jclass.bwt.BWTEnum;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.SimpleFileFilter;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.DruckEingabe;
import All_Unlimited.Grunddefinition.DefAbfrage;
import All_Unlimited.Print.Drucken;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import All_Unlimited.Allgemein.GroupBox;
import All_Unlimited.Allgemein.Anzeige.Ampel;
import java.awt.Image;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//import java.awt.Insets;
//import java.awt.FlowLayout;

public class Export extends javax.swing.JFrame
{
    /**
	 *
	 */
	private static final long serialVersionUID = 8654389900994658549L;
	// add your data members here
	private Global g;
	private int iStt;
	private int iAbfrage=0;
  private int iDruck=0;
	private Vector VecStamm;
	private FileEingabe EdtFile;
	private Tabellenspeicher TabDaten;
	private Tabellenspeicher TabSpalten;
	private boolean bModell=false; // für Modell-Import (getrennt in Spalten)
	private boolean bImport=false; // für Import (oder Autoimport im Modell)
	private boolean bHochkomma=false;
	private boolean bCRLF=false;
        //private boolean bASCII=false;
	private int iAbfrageBegriff=0;
	private long iBits=0;
	private char cTrennzeichen=';';
	private Text EdtTrennzeichen=new Text(""+cTrennzeichen,3);
        //private char cDez=',';
        //private Text EdtDez=new Text(""+cDez,1);
	private ShowAbfrage Abf=null;
	private String sBez=null;
        private String sBez2=null;
        private boolean bDefExport;
        private String sFileOld="";
        private JCheckBox CbxImport=null;
        private JCheckBox CbxModell=null;
        private JCheckBox CbxHochkomma=null;
        private JCheckBox CbxCRLF=null;
        private JCheckBox CbxStammspalte=null;
        private JCheckBox CbxAnzeigen=null;
        private JCheckBox CbxKeineEinheit=null;
        private JCheckBox CbxKeineGruppe=null;
        //private JCheckBox CbxAuffuellen=null;
        private JRadioButton RadANSI=null;
        private JRadioButton RadASCII=null;
        private JRadioButton RadUTF16=null;
        private JRadioButton RadUTF8=null;
        private Text EdtCP=new Text("",10);
        private String sResult="";
        private boolean bFehler=false;
        private ComboSort CboStammtyp;
        private AbfrageEingabe CboAbfrage;
        private DruckEingabe CboDruck;
        private Export thisFrame;
        private JRadioButton Rad_h_auto;
        private JRadioButton Rad_h_dez;
        private JRadioButton Rad_h_min;
        private int iLast=0;
        private JFrame Fom=null;
        //private int iAbfrageOld=-1;

	public Export(Global rg,JFrame rFom,int riStt,Vector rVecStamm,int riAbfrage)
	{
		super(rg.getBegriffS("Dialog","Export"));
                thisFrame=this;
		g=rg;
		iStt=riStt;
        bDefExport=iStt==0;
        if (riAbfrage>0)
          iAbfrage=riAbfrage;
        else
          iDruck=-riAbfrage;
		VecStamm=rVecStamm;
		g.fixtestError("Export "+iAbfrage+"/"+iDruck+":"+VecStamm);

		build();
		Fom=rFom;
		if(Fom!=null)
			Static.centerComponent(this,Fom);
		setVisible(true);
	}

        /*private void addN(Component C,JPanel Pnl)
        {
          JPanel P=new JPanel(new BorderLayout(2,2));
          P.add("North",C);
          Pnl.add(P);
        }

        private JLabel getLabel(String s)
        {
          JLabel Lbl=new JLabel(g.getBegriffS("Label",s)+":");
          Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
          Lbl.setVerticalAlignment(SwingConstants.CENTER);
          Lbl.setFont(g.fontBezeichnung);
          Lbl.setForeground(g.ColBezeichnung);
          Lbl.setBorder(new EmptyBorder(new Insets(3,1,2,1)));
          return Lbl;
        }

        private void addN(String s,Component C,JPanel Pnl1,JPanel Pnl2)
        {
          addN(getLabel(s),Pnl1);
          addN(C,Pnl2);
        }*/

	private void build()
	{
		CbxImport=g.getCheckbox("fuer Import");
		if (CbxImport!=null && !g.Def())
			CbxImport.setEnabled(false);
		CbxModell=g.getCheckbox("fuer Modell");
		if (CbxModell!=null && !g.Def())
			CbxModell.setEnabled(false);
		CbxHochkomma=g.getCheckbox("mit Hochkomma",true);
		CbxCRLF=g.getCheckbox("mit CRLF",true);
                CbxStammspalte=g.getCheckbox("Stammspalte");
                CbxAnzeigen=g.getCheckbox("Inhalt");
                CbxKeineGruppe=g.getCheckbox("keine Gruppierung");
                CbxKeineEinheit=g.getCheckbox("keine_Einheit");
                //CbxAuffuellen=g.getCheckbox("auffuellen");
                ButtonGroup RadGroupF=new ButtonGroup();
                Rad_h_auto = g.getRadiobutton("h_auto");
                Rad_h_dez = g.getRadiobutton("h_dez");
                Rad_h_min = g.getRadiobutton("h_min");
                RadGroupF.add(Rad_h_auto);
                RadGroupF.add(Rad_h_dez);
                RadGroupF.add(Rad_h_min);
                ButtonGroup RadGroupZS=new ButtonGroup();
                RadANSI=g.getRadiobutton("ANSI");
                RadASCII=g.getRadiobutton("ASCII");
                RadUTF16=g.getRadiobutton("UTF16");
                RadUTF8=g.getRadiobutton("UTF8");
                RadGroupZS.add(RadANSI);
                RadGroupZS.add(RadASCII);
                RadGroupZS.add(RadUTF16);
                RadGroupZS.add(RadUTF8);
                if (Static.CP.equals("Cp1252"))
                  RadANSI.setSelected(true);
                else if (Static.CP.equals("Cp850"))
                  RadASCII.setSelected(true);
                else if (Static.CP.equals("UTF-16"))
                  RadUTF16.setSelected(true);
                else if (Static.CP.equals("UTF-8"))
                    RadUTF8.setSelected(true);
                else
                {
                  RadANSI.setSelected(false);
                  RadASCII.setSelected(false);
                  RadUTF16.setSelected(false);
                  RadUTF8.setSelected(false);
                  EdtCP.setText(Static.CP);
                }
                final ComboSort CboTyp = new ComboSort(g,ComboSort.kein);
                CboStammtyp  = new ComboSort(g);
		CboAbfrage = new AbfrageEingabe(g,this,false,true);
                CboDruck = new DruckEingabe(g,this);
                final ComboSort CboWaehrung  = new ComboSort(g);
                CboWaehrung.fillWaehrung(false,true);
                CboWaehrung.setPreferredSize(new Dimension(60,16));
                //CboWaehrung.setMinimumSize(new Dimension(40,16));
                CboWaehrung.setSelectedAIC(g.getWaehrung());
		//CboStammtyp.fillDefTable("Stammtyp",false);
                if (iStt==0)
                {
                  CboTyp.addItem(g.getBezeichnung("Tabellenname","STAMMTYP"),1,"Stammtyp",0);
                  CboTyp.addItem(g.getBezeichnung("Tabellenname","BEWEGUNGSTYP"),2,"Bewegungstyp",0);
                  CboTyp.addItemListener(new ItemListener ()
                  {
                        public void itemStateChanged(ItemEvent ev)
                        {
                                if(ev.getStateChange() == ItemEvent.SELECTED)
                                {
                                  fillCboStammtyp(CboStammtyp,CboTyp);
                                  int iStt=CboStammtyp.getSelectedAIC();
                                  fillCboAbfrage(iStt,CboAbfrage,CboTyp);
                                }
                        }
                  });
                }
                fillCboStammtyp(CboStammtyp,CboTyp);
                if (iStt>0)
                  CboStammtyp.setSelectedAIC(iStt);
                CboStammtyp.setEditable1(g.Def());
                fillCboAbfrage(CboStammtyp.getSelectedAIC(),CboAbfrage,CboTyp);
                ItemListener IL=new ItemListener ()
                {
                  public void itemStateChanged(ItemEvent ev) {
                    if(ev.getStateChange() == ItemEvent.SELECTED) {
                      Object Obj=ev.getSource();
                      if (Obj==CboAbfrage.Cbo)
                        CboDruck.Cbo.setSelectedAIC(0);
                      else if (Obj==CboDruck)
                        CboAbfrage.setSelectedAIC(0);
                      else if (Obj==CboStammtyp)
                        fillCboAbfrage(CboStammtyp.getSelectedAIC(),CboAbfrage,CboTyp);
                    }
                  }
                };

                CboAbfrage.Cbo.addItemListener(IL);
                CboDruck.Cbo.addItemListener(IL);
		CboStammtyp.addItemListener(IL);

		EdtFile = new FileEingabe(g);
		//EdtFile.SaveDialog(true);

		JPanel PnlL=new JPanel(new GridLayout(0,1));
                JPanel PnlR=new JPanel(new GridLayout(0,1));
                if (bDefExport)
                {
                  PnlL.add(CboTyp);
                  g.addN(CboStammtyp,PnlR);
                }
                else
                  g.addN("Stammtyp",CboStammtyp,PnlL,PnlR);
		g.addN("Abfrage",CboAbfrage,PnlL,PnlR);
		g.addN("Druck",CboDruck,PnlL,PnlR);
                JPanel PnlSub=new JPanel(new GridLayout());
                PnlSub.add(CboWaehrung);
                PnlSub.add(g.getLabel("Trennzeichen"));
                EdtTrennzeichen.setColumns(3);
                PnlSub.add(g.addTwo(EdtTrennzeichen,new JLabel()));
		g.addN("Waehrung",PnlSub,PnlL,PnlR);
		//addN("Trennzeichen",EdtTrennzeichen,PnlL,PnlR);
                g.addN("File",EdtFile,PnlL,PnlR);
		//g.addLabel4(PnlL,"Dezimalzeichen");
		//PnlR.add(EdtDez);
                loadParameter();
                JPanel PnlG=new JPanel(new GridLayout(1,2,5,0));
                PnlG.add(CbxModell);
                PnlG.add(CbxImport);
                PnlL.add(new JLabel(""));
                PnlR.add(PnlG);
                PnlG=new JPanel(new GridLayout(1,2,5,0));
                PnlG.add(CbxKeineEinheit);
                PnlG.add(CbxKeineGruppe);
                PnlL.add(new JLabel(""));
                PnlR.add(PnlG);
                PnlG=new JPanel(new GridLayout(1,2,5,0));
		PnlG.add(CbxHochkomma);
		PnlG.add(CbxCRLF);
                PnlL.add(new JLabel(""));
		PnlR.add(PnlG);
                PnlG=new JPanel(new GridLayout(1,2,5,0));
                PnlG.add(CbxStammspalte);
                PnlG.add(CbxAnzeigen);
                PnlL.add(new JLabel(""));
                PnlR.add(PnlG);

                JPanel PnlAll=new JPanel(new BorderLayout(2,2));
                JScrollPane Scroll=new JScrollPane(PnlAll);
                Scroll.setBorder(new EmptyBorder(new Insets(5,5,5,5)));
                getContentPane().add("Center",Scroll);

                GroupBox GroupF = new GroupBox(g.getBegriffS("Titel","Zeitformat"));
                GroupF.setFont(g.fontTitel);
                GroupF.add(Rad_h_auto);
                GroupF.add(Rad_h_dez);
                GroupF.add(Rad_h_min);
                Rad_h_auto.setSelected(true);
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
                JPanel PnlGroups=new JPanel(new BorderLayout(2,2));
                PnlGroups.add("North",GroupF);
                PnlGroups.add("Center",GroupZS);
                PnlAll.add("Center",PnlGroups);
                JPanel PnlN=new JPanel(new BorderLayout());
                PnlN.add("West",PnlL);
                PnlN.add("Center",PnlR);
                PnlAll.add("North",PnlN);

                //JButton BtnZR=g.getButton("Zeitraum");
                JButton BtnAbfrage=g.getButton("Abfrage");
                JButton BtnLoad=g.getButton("Load");
		JButton BtnShow=g.getButton("show");
		//JButton BtnOK=g.getButton("Ok");
                JButton BtnExport=g.getButton("Export");
		//JButton BtnAbbruch=g.getButton("Abbruch");
                JButton BtnBeenden=g.getButton("Beenden");
		JPanel PnlSouth=new JPanel(new GridLayout(1,0,2,2));//new FlowLayout(FlowLayout.RIGHT,3,2));
                //PnlSouth.add(BtnZR);
                PnlSouth.add(BtnAbfrage);
                //if (g.Def())
                  PnlSouth.add(BtnLoad);
		PnlSouth.add(BtnShow);
		PnlSouth.add(BtnExport);
		PnlSouth.add(BtnBeenden);
		PnlAll.add("South",PnlSouth);
		pack();

                /*BtnZR.addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev) {
                    Zeitraum.get(g).show();
                  }
                });*/

                BtnAbfrage.addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    int iBegriff = CboAbfrage.getSelectedAIC();
                    int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iBegriff);
                    DefAbfrage.get(g, new ShowAbfrage(g, iBegriff, Abfrage.cstBegriff), iStt).show(false);
                  }
                });

                BtnLoad.addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    try
    		    {
                      Static.CP= !EdtCP.getText().equals("") ? EdtCP.getText():RadASCII.isSelected() ? "Cp850":RadANSI.isSelected() ? "Cp1252":RadUTF8.isSelected() ? "UTF-8":RadUTF16.isSelected() ? "UTF-16":EdtCP.getText();
                      InputStreamReader FR = new InputStreamReader(new FileInputStream(new File(EdtFile.getValue())),Static.CP);
                      BufferedReader Buf=new BufferedReader(FR);
                      sResult="";
                      String s=Buf.readLine();
                      while (s != null)
                      {
                        sResult+=s+"\n";
                        s=Buf.readLine();
                      }
                      Buf.close();
                      showResult();
    		    }
                    catch (Exception e2)
                    {
                      Static.printError("Export.Load: "+e2);
                    }
                  }
                });

		BtnShow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                          int iW=g.getWaehrung();
                          g.setWaehrung(CboWaehrung.getSelectedAIC());
                          boolean bH_dez=g.bH_dez;
                          boolean bH_min=g.bH_min;
                          g.bH_dez=Rad_h_dez.isSelected();
                          g.bH_min=Rad_h_min.isSelected();

				iAbfrageBegriff=CboAbfrage.getSelectedAIC();
                                sBez=null;
				ErmittleTabTab2(null);
				JDialog Dlg=new JDialog((JFrame)null,CboAbfrage.Cbo.getSelectedBezeichnung(),true);
                int iSync=((iBits&Abfrage.cstFremdStamm)>0) && g.TabStammtypen.posInhalt("Aic",iStt) ? g.TabStammtypen.getI("Sync"):-1;            
                if((iBits&Abfrage.cstMengen)>0 || (iBits&Abfrage.cstKeinStamm)>0 && (iBits&Abfrage.cstFremdStamm)==0)
				{
					TabDaten= Abf.getDaten(iStt,0,VecStamm,Fom);
					AUOutliner GidGesamt = new AUOutliner();
					ShowAbfrage.initOutliner(g,GidGesamt);                 
					checkKeineEinheit();
					Abf.TabToOutliner(GidGesamt,TabDaten,null);
					Dlg.getContentPane().add("Center",GidGesamt);
				}
				else
				{
					JTabbedPane Pane = new JTabbedPane();
					for(int i=0;i<VecStamm.size();i++)
					{
						int iAic=((Integer)VecStamm.elementAt(i)).intValue();
                        if ((iBits&Abfrage.cstFremdStamm)>0)
                          g.setSyncStamm(iStt,iAic);
                        sBez=SQL.getString(g,"select bezeichnung from stammview2 where aic_rolle is null and aic_stamm="+iAic);
						TabDaten= Abf.getDaten(iStt,iAic,null,Fom);

						AUOutliner GidGesamt = new AUOutliner();
						ShowAbfrage.initOutliner(g,GidGesamt);
						checkKeineEinheit();
						Abf.TabToOutliner(GidGesamt,TabDaten,null);
						Pane.add(sBez,GidGesamt);
					}
					Dlg.getContentPane().add("Center",Pane);
				}
                            g.setWaehrung(iW);
                            g.bH_dez=bH_dez;
                            g.bH_min=bH_min;
                                if (iSync>-1)
                                  g.setSyncStamm(iStt,iSync);
				Dlg.setSize(600,400);
				Dlg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				Dlg.setVisible(true);
			}
		});

		BtnExport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                          bFehler=false;
                          int iW=g.getWaehrung();
                          g.setWaehrung(CboWaehrung.getSelectedAIC());
                          boolean bH_dez=g.bH_dez;
                          boolean bH_min=g.bH_min;
                          g.bH_dez=Rad_h_dez.isSelected();
                          g.bH_min=Rad_h_min.isSelected();
				bImport=CbxImport.isSelected();
				bModell=CbxModell.isSelected();
				bHochkomma=CbxHochkomma.isSelected();
				bCRLF=CbxCRLF.isSelected();
                                Static.CP= !EdtCP.getText().equals("") ? EdtCP.getText():RadASCII.isSelected() ? "Cp850":RadANSI.isSelected() ? "Cp1252":RadUTF8.isSelected() ? "UTF-8":RadUTF16.isSelected() ? "UTF-16":EdtCP.getText();
                                sBez=null;
				//Vector Vec = new Vector();
				iAbfrageBegriff=CboAbfrage.getSelectedAIC();
				ErmittleTabTab2(null);
                                sFileOld="";
				//cTrennzeichen=EdtTrennzeichen.getText().charAt(0);
				String sTrenn=EdtTrennzeichen.getText();
				cTrennzeichen=sTrenn.equals("")?0:sTrenn.equals("Tab")?9:sTrenn.charAt(0);
                                saveParameter();
                                int iSync=((iBits&Abfrage.cstFremdStamm)>0) && g.TabStammtypen.posInhalt("Aic",iStt) ? g.TabStammtypen.getI("Sync"):-1;

				if(CboDruck.Cbo.getSelectedAIC()==0 && ((iBits&Abfrage.cstMengen)>0 || (iBits&Abfrage.cstKeinStamm)>0 && (iBits&Abfrage.cstFremdStamm)==0))
				{
					TabDaten= Abf.getDaten(iStt,0,VecStamm,Fom);
					checkKeineEinheit();
					toCSV(EdtFile.getValue(),0,null,null);
				}
				else
				{
                                  int iDruck=CboDruck.Cbo.getSelectedAIC();
                                  java.sql.Timestamp dtVon=g.getVon();
                                  java.sql.Timestamp dtBis=g.getBis();
                                  int iPosD= iDruck>0 ? g.TabBegriffe.getPos("Aic",iDruck):-1;
                                  if (iPosD>=0 && (g.TabBegriffe.getI(iPosD,"bits")&Drucken.cstPntNewYear)>0)
                                  {
                                    DateWOD dwVon=new DateWOD(dtVon);
                                    dwVon.setMonth(1);
                                    dwVon.setDay1();
                                    g.setVon(dwVon.toTimestamp());
                                    dwVon.nextYear();
                                    g.setBis(dwVon.toTimestamp());
                                  }
                                  if (iDruck>0  && !EdtFile.getValue().equals(""))
                                  {
                                    File fil = new File(EdtFile.getValue());
                                    if (fil.exists() && new Message(Message.YES_NO_OPTION,thisFrame,g,0).showDialog("ersetzen",new String[] {EdtFile.getValue()}) == Message.YES_OPTION)
                                      fil.delete();
                                  }
                                  boolean bMenge=iPosD>=0 ? (g.TabBegriffe.getI(iPosD,"bits")&Drucken.cstPntMenge)>0:(iBits&Abfrage.cstMengen)>0;
                                  if (bMenge)
                                  {
                                    toCSV(EdtFile.getValue(),0,VecStamm,null);
                                  }
                                  else
                                  {
                                    Tabellenspeicher TabAbfragen=new Tabellenspeicher(g,new String[]{"Aic","Abfrage"});
                                    Gauge Gau = new Gauge(0, VecStamm.size(), "", g, true);
                                    for (int i = 0; i < VecStamm.size(); i++)
                                    {
                                      int iAic = ((Integer)VecStamm.elementAt(i)).intValue();
                                      if ((iBits & Abfrage.cstFremdStamm) > 0 || iPosD>=0 && (g.TabBegriffe.getI(iPosD,"bits")&Drucken.cstPntUseSync)>0)
                                        g.setSyncStamm(iStt, iAic);
                                      sBez2 = SQL.getString(g, "select bezeichnung from stammview2 where  aic_rolle is null and aic_stamm=" + iAic);
                                      sBez = CbxStammspalte.isSelected() ? sBez2 : null;
                                      Gau.setText(sBez2, i);
                                      if (iDruck == 0)
                                        TabDaten = Abf.getDaten(iStt, iAic, null,Fom);
                                      checkKeineEinheit();
                                      toCSV(EdtFile.getValue(), iAic,null,TabAbfragen);
                                      if ((iBits & Abfrage.cstAnhaengen) == 0)
                                        iBits += Abfrage.cstAnhaengen;
                                    }
                                    Gau.free();
                                  }
                                  g.setVonBis(dtVon,dtBis);
				}
                           g.setWaehrung(iW);
                           g.bH_dez=bH_dez;
                           g.bH_min=bH_min;
                            if (iSync>-1)
                              g.setSyncStamm(iStt,iSync);
                            Abf=null;
                            if (CbxAnzeigen.isSelected())
                              showResult();
                            else
                              new Message(Message.INFORMATION_MESSAGE,thisFrame,g).showDialog(bFehler ? "Export_fehlerhaft":"Export_fertig");
                            //dispose();
			}
		});

		BtnBeenden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				dispose();
			}
		});

	}
	
	private void checkKeineEinheit()
	{
		TabSpalten=Abf.getSpalten();
        if (CbxKeineEinheit.isSelected())
        {
          for (TabSpalten.moveFirst(); !TabSpalten.eof(); TabSpalten.moveNext())
            TabSpalten.setInhalt("bits", new Long(TabSpalten.getL("bits") | Abfrage.cstKeineEinheit));
        }
	}

        private void showResult()
        {
          JDialog DlgShow=new JDialog(this,EdtFile.getValue(),false);

          final JTextArea Edt=new JTextArea(sResult);
          JScrollPane PnlScroll = new JScrollPane(Edt);
          Edt.setFont(new java.awt.Font("Courier",0,11));
          final JLabel Lbl=new JLabel("Status");
          Edt.addKeyListener(new KeyListener()
          {
		public void keyPressed(KeyEvent e)
		{}
		public void keyReleased(KeyEvent e)
		{
		  int iPos=Edt.getCaretPosition();
		  int iZ=-1;
		  int iS=0;
		  try
		  {
		    int iLines=Edt.getLineCount();
		    for(int i=0;iZ<0 && i<iLines;i++)
		      if(Edt.getLineStartOffset(i)>iPos)
		      {
			iZ=i;
			iS=iPos-Edt.getLineStartOffset(iZ-1)+1;
		      }
		  }
		  catch(Exception e2){}
		  int i=iPos<sResult.length() ? sResult.charAt(iPos):-1;
                  Lbl.setText("Zeile="+iZ+", Spalte="+iS+", Zeichen="+i);
		}
		public void keyTyped(KeyEvent e)
		{}
          });
          DlgShow.getContentPane().add("Center",PnlScroll);
          DlgShow.getContentPane().add("South",Lbl);
          //DlgShow.getContentPane().setMaximumSize(new java.awt.Dimension(1000,700));
          DlgShow.pack();
          if (DlgShow.getWidth()>1000 || DlgShow.getHeight()>700)
          {
            int iWidth=Math.min(1000, DlgShow.getWidth());
            int iHeight=Math.min(700, DlgShow.getHeight());
            DlgShow.setSize(iWidth, iHeight);
          }
          DlgShow.setVisible(true);
        }

        private String Bedingung()
        {
          return " where abfrage.aic_begriff="+iAbfrage+" or"+g.bit("begriff.bits",Abfrage.cstExportierbar);//+(bDefExport?" and"+g.bit("begriff.bits",Abfrage.cstVersionsup):"");
        }

        private void fillCboStammtyp(ComboSort CboStammtyp,ComboSort CboTyp)
        {
          if (!bDefExport || CboTyp.getSelectedKennung().equals("Stammtyp"))
            CboStammtyp.fillCbo("select distinct stammtyp.aic_stammtyp,stammtyp.kennung"+g.AU_Bezeichnung("stammtyp")+" from stammtyp"+g.join2("begriff","stammtyp")+g.join2("abfrage","begriff")+Bedingung()+(bDefExport?" and aic_Bewegungstyp is null":""),"stammtyp",false,false);
          else if(CboTyp.getSelectedKennung().equals("Bewegungstyp"))
            CboStammtyp.fillCbo("select distinct Bewegungstyp.aic_Bewegungstyp,Bewegungstyp.kennung"+g.AU_Bezeichnung("Bewegungstyp")+" from Bewegungstyp"+g.join2("begriff","Bewegungstyp")+g.join2("abfrage","begriff")+Bedingung(),"Bewegungstyp",false,false);
        }

        private void fillCboAbfrage(int riStt,AbfrageEingabe CboAbfrage,ComboSort CboTyp)
        {
          boolean bGleich=riStt==iStt;
          iStt=riStt;
          g.progInfo("fillCboAbfrage: Stt="+iStt+"/"+bGleich);
          CboAbfrage.Cbo.Clear();
          SQL Qry=new SQL(g);
          String s= "select abfrage.aic_begriff,aic_benutzer,aic_benutzergruppe from abfrage"+g.join("begriff","abfrage")+Bedingung()+" and Tod is null"+
              (bDefExport && CboTyp.getSelectedKennung().equals("Bewegungstyp") ? " and aic_bewegungstyp="+iStt :(bDefExport?" and aic_bewegungstyp is null":"")+" and aic_stammtyp="+iStt)+
              (g.Def()?"":" and (aic_benutzergruppe is null or "+g.getAllBG()+")");
          for(Qry.open(s);!Qry.eof();Qry.moveNext())
          {
            int iPos=g.TabBegriffe.getPos("Aic",Qry.getI("Aic_Begriff"));
            if (Qry.getI("Aic_Begriff")==iAbfrage || g.Privileg(iPos,Qry.getI("aic_benutzer"),Qry.getI("aic_benutzergruppe")))
              CboAbfrage.Cbo.addItem(g.TabBegriffe.getS(iPos,"Bezeichnung"),g.TabBegriffe.getI(iPos,"Aic"),g.TabBegriffe.getS(iPos,"Kennung"));
          }
          Qry.free();
          CboAbfrage.Cbo.setKein(false);
          if (iAbfrage>0 && bGleich)
            CboAbfrage.setSelectedAIC(iAbfrage);
          CboDruck.fillDruck(0,iStt,0,null,true,true);
          CboDruck.Cbo.setKein(true);
          if (iDruck>0)
            CboDruck.Cbo.setSelectedAIC(iDruck);
        }

	private String CSV_Format(Object Obj,boolean bHK,boolean bAuto)
	{
	  if (bAuto && !bHK && !bHochkomma && Obj != null)
	  {
	    bHK= Obj instanceof String && !bModell &&((String)Obj).indexOf(cTrennzeichen)>-1;
	    //g.fixInfo("CSV_Format:"+Static.print(Obj)+" ->"+bHK);
	  }
		return Obj == null ? "" : Obj instanceof Date ? Static.DateTimeToString((Date)Obj):
			Obj instanceof Double || Obj instanceof Integer ? Obj.toString() : bHochkomma || bHK ? "\""+Obj+"\"" : ""+Obj;
	}

        @SuppressWarnings("unchecked")
        private Vector getOutlinerNodes(AUOutliner outliner)
        {
                outliner.folderChanged(outliner.getRootNode());
                JCOutlinerNode root=outliner.getRootNode();
                JCOutlinerNode curNode=root;
                Vector<JCOutlinerNode> vec=new Vector<JCOutlinerNode>();
                while((curNode=outliner.getNextNode(curNode))!=null)
                {
                  if (CbxKeineGruppe.isSelected() && curNode.getState()==-999)
                  {
                      Vector VecL = (Vector)curNode.getLabel();
                      //g.progInfo("State="+curNode.getState()+", Level="+curNode.getLevel());
                      JCOutlinerNode NodP=curNode.getParent();
                      for (int i=curNode.getLevel();i>1;i--)
                      {
                        VecL.setElementAt(Sort.gets(NodP.getLabel(), i-2), i-2);
                        NodP=NodP.getParent();
                      }
                  }
                  if (curNode.getParent().getState() != BWTEnum.FOLDER_CLOSED && (curNode.getState()==-999 || !CbxKeineGruppe.isSelected()))
                    vec.addElement(curNode);
                }
                return vec;
        }

        private void exportiere(String sFileName,int iAic,int iABits)
        {
          String s;
          AUOutliner GidGesamt =null;
          Frame f = null;
          boolean bDruck=CboDruck.Cbo.getSelectedAIC()>0;
          if (bDruck || (iBits&(Abfrage.cstTTO+Abfrage.cstAuswertung+Abfrage.cstDruckbar))>0)
                    {
                      GidGesamt = new AUOutliner();
                      ShowAbfrage.initOutliner(g, GidGesamt);
                      f = new Frame("");
                      f.add(GidGesamt);
                      f.setLocation(2000,2000);
                      f.addNotify();
                      //f.setVisible(true);
                      checkKeineEinheit();
//                      if (CbxKeineEinheit.isSelected())
//                      {
//                        for (TabSpalten.moveFirst(); !TabSpalten.eof(); TabSpalten.moveNext())
//                          TabSpalten.setInhalt("bits", new Long(TabSpalten.getL("bits") | Abfrage.cstKeineEinheit));
//                      }
                      Abf.TabToOutliner(GidGesamt, TabDaten, null);
                      //TabSpalten=Abf.getSpalten();
                      GidGesamt.folderChanged(GidGesamt.getRootNode());
                    }
                        try
                        {
                                java.io.FileOutputStream fos=new java.io.FileOutputStream(sFileName,(iBits & Abfrage.cstAnhaengen)>0 || bDruck);
                                if (iAic>0 && iAic!=iLast)
                                {
                                  fos.write(("\r\n" + sBez2 + "\r\n").getBytes(Static.CP));
                                  iLast=iAic;
                                }
                                sResult="";
                                if (cTrennzeichen>0 && (iBits & Abfrage.cstKeinTitel)==0 && (!sFileOld.equals(sFileName) || bDruck && (iABits & Drucken.cstBedST) == 0))
                                {
                                        //g.printInfo("toCSV-Titelzeile");
                                        sFileOld=sFileName;
                                        Vector VecTitel = GidGesamt==null ? Abf.getBezeichnung():new jclass.util.JCVector(GidGesamt.getColumnLabels());
                                        g.fixtestInfo("VecTitel="+VecTitel);
                                        //TabSpalten.moveFirst();
                                        if (sBez==null)
                                        {
                                                s=CSV_Format(VecTitel.elementAt(0),false,true);
                                                TabSpalten.moveNext();
                                        }
                                        else
                                                s=CSV_Format(g.getBegriffS("Show","Titel"),false,true);
                                        for(int i=sBez==null?1:0;i<VecTitel.size();i++)
                                                s += cTrennzeichen+CSV_Format(VecTitel.elementAt(i),false,true);
                                        s += bCRLF ? "\r\n":"\n";
                                        fos.write(s.getBytes(Static.CP));
                                        if (CbxAnzeigen.isSelected())
                                          sResult+=s;
                                }

                                //int iAnz = VecInhalt.size()>0 ? ((Vector)VecInhalt.elementAt(0)).size() : 0;
                                if (TabDaten==null)
                                        Static.printError("TabDaten existiert nicht!");
                                else if (GidGesamt ==null)
                                        for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
                                        {
                                                TabSpalten.moveFirst();
                                                if (sBez==null)
                                                {
                                                        s=cTrennzeichen>0 ? ExportSpalte():Static.FillSpace(ExportSpalte(),TabSpalten.getLaenge(TabSpalten));
                                                        TabSpalten.moveNext();
                                                }
                                                else
                                                  s=CSV_Format(sBez,false,true);
                                                //g.progInfo("Exportiere "+s);
                                                for(;!TabSpalten.eof();TabSpalten.moveNext())
                                                  if ((TabSpalten.getI("Bits")&Abfrage.cstUnsichtbar)==0)
                                                        s += cTrennzeichen>0 ? cTrennzeichen+ExportSpalte():Static.FillSpace(TabSpalten.getS("Datentyp"),ExportSpalte(),TabSpalten.getLaenge(TabSpalten));
                                                if (cTrennzeichen>0 || bCRLF)
                                                        s += bCRLF ? "\r\n":"\n";
                                                fos.write(s.getBytes(Static.CP));
                                                if (CbxAnzeigen.isSelected())
                                                  sResult+=s;
                                        }
                                else
                                {
                                  Vector vec=getOutlinerNodes(GidGesamt);
                                  for (int i=0; i<vec.size();i++)
                                  {
                                    Vector vec2=(Vector)((JCOutlinerNode)vec.elementAt(i)).getLabel();
                                    Object Obj=vec2.elementAt(0);
                                    String sSpalte=Obj==null ? "":Obj instanceof Image ? Ampel.getBezeichnung((Image)Obj):Sort.gets(Obj);
                                    s=(sBez==null?"":CSV_Format(sBez,false,true)+cTrennzeichen)+CSV_Format(sSpalte,false,Abf.iModell==0);
                                    for(int i2=1; i2<vec2.size();i2++)
                                    {
                                      Obj=vec2.elementAt(i2);
                                      sSpalte=Obj==null ? "":Obj instanceof Image ? Ampel.getBezeichnung((Image)Obj):Sort.gets(Obj);
                                      s += cTrennzeichen > 0 ? cTrennzeichen + CSV_Format(sSpalte, false, Abf.iModell == 0) : CSV_Format(sSpalte, false, Abf.iModell == 0);
                                    }
                                    //if (cTrennzeichen>0)
                                      s += bCRLF ? "\r\n":"\n";
                                    fos.write(s.getBytes(Static.CP));
                                    if (CbxAnzeigen.isSelected())
                                          sResult+=s;
                                  }
                                  f.dispose();
                                }
                                fos.close();
                        }
                        catch (java.io.IOException ex)
                        {
                          bFehler=true;
                          Static.printError("Tabellenspeicher.toCSV("+sFileName+"): IOException - "+ex);
                        }

        }

	private void toCSV(String sFileName,int iAic,Vector Vec,Tabellenspeicher TabAbfragen)
	{
            if (sFileName.equals(""))
            {
                    JFileChooser chooser = new JFileChooser();
                    SimpleFileFilter filefilter = new SimpleFileFilter(new String[] {"csv"},"Textdatei (*.csv)");
                    chooser.setMultiSelectionEnabled(false);
                    chooser.addChoosableFileFilter(filefilter);
                    chooser.setCurrentDirectory(new java.io.File("C:\\"));
                    chooser.setFileFilter(filefilter);
                    int option = chooser.showSaveDialog(null);
                    if(option == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile()!=null)
                            sFileName=chooser.getSelectedFile().getPath();
            }
            if (!sFileName.equals(""))
            {
              if (CboDruck.Cbo.getSelectedAIC()>0)
              {
                Tabellenspeicher TabAbschnitte=new Tabellenspeicher(g,"select abschnitt.bits,abschnitt.aic_raster,abschnitt.kennung,abschnitt.kennzeichen,abschnitt.aic_begriff,reihenfolge,Zahl,Begriff.bits bits2,aic_bewegungstyp " + g.AU_Bezeichnung("Abschnitt")+g.AU_Bild2("Abschnitt",Global.iSpSw)+" Filename from Druck_Zuordnung"+g.join("Abschnitt","Druck_Zuordnung")+" left outer"+g.join("begriff","Abschnitt")+" left outer"+g.join2("abfrage","begriff")+
                    " where Druck_Zuordnung.aic_begriff =" + CboDruck.Cbo.getSelectedAIC() + " order by Reihenfolge",true);
                iLast=0;
                for (TabAbschnitte.moveFirst();!TabAbschnitte.eof();TabAbschnitte.moveNext())
                {
                  int iABits=TabAbschnitte.getI("Bits");
                  if ((iABits & Drucken.cstNichtDrucken) == 0)
                  {
                    iAbfrageBegriff = TabAbschnitte.getI("aic_begriff");
                    g.sKennzeichen = TabAbschnitte.getS("Kennzeichen");
                    ErmittleTabTab2(TabAbfragen);
                    TabDaten = Abf.getDaten(iStt, iAic, Vec,Fom);
                    if (TabDaten != null && !(TabDaten.isEmpty() && (iABits & Drucken.cstLeere) == 0))
                      exportiere(sFileName, iAic,iABits); //TabAbschnitte.getPos()==0?iAic:0);
                    g.sKennzeichen = null;
                  }
                }
              }
              else
                exportiere(sFileName,0,0);
            }
	}

	private String ExportSpalte()
	{
		Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr");
		boolean bHK=(TabSpalten.getI("bits")&Abfrage.cstHochkomma )>0;
		//g.fixtestInfo(TabSpalten.getS("Bezeichnung")+"/"+TabSpalten.getS("Kennung")+": HK="+bHK);
		if (VecMehr==null)
			return CSV_Format(TabWertToObj(TabSpalten.getS("Kennung"),false),bHK,Abf.iModell==0);
		else
		{
			int iAnz=VecMehr==null?1:VecMehr.size();
			String s=CSV_Format(TabWertToObj("e"+TabSpalten.getS("Kennung2")+VecMehr.elementAt(0),true),bHK,Abf.iModell==0);
			for(int iPer=1;iPer<iAnz;iPer++)
				s+=cTrennzeichen+CSV_Format(TabWertToObj("e"+TabSpalten.getS("Kennung2")+VecMehr.elementAt(iPer),true),bHK,Abf.iModell==0);
			return s;
		}
	}

	private double MassFaktor(int riStamm)
	{
		if (riStamm==0)
			return 1.0;
		else
		{
			double d=Sort.getf(g.getMass(riStamm,true).elementAt(0));
			return d==0.0 ? 1.0:d;
		}
	}

	private Object TabWertToObj(String sAic,boolean bPeriode)
	{
          if ((iBits&(Abfrage.cstTTO+Abfrage.cstAuswertung+Abfrage.cstDruckbar))>0)
            return Abf.TabToScreen(g,sAic,TabDaten,TabSpalten,bPeriode);
		String sDatentyp=TabSpalten.getS("Datentyp");
		String s=sDatentyp.equals("Einheiten")?"Kennung2":"Kennung";
		//g.printInfo(TabSpalten.getS("Bezeichnung")+" ("+sDatentyp+")");
		String sKZ=TabSpalten.getS("Stil");
		if (sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("Zeit") || sKZ!=null && (sKZ.equals("von") || sKZ.equals("bis")) )
			return new Zeit((Date)TabDaten.getInhalt(TabSpalten.getS(s)),Static.beiLeer(TabSpalten.getS("Format"),"yyyy/MM/dd HH:mm:ss"));
		else if (sDatentyp.equals("BewVon_Bis"))
		{
			if (bModell)
				return ""+new Zeit((Date)TabDaten.getInhalt("V"+sAic),TabSpalten.getS("Format"))+cTrennzeichen
						+new Zeit((Date)TabDaten.getInhalt("B"+sAic),TabSpalten.getS("Format"))+cTrennzeichen+TabDaten.getI((bPeriode?"":"D")+sAic);
			else if (bImport)
				return "["+new Zeit((Date)TabDaten.getInhalt("V"+sAic),TabSpalten.getS("Format"))+"-"+new Zeit((Date)TabDaten.getInhalt("B"+sAic),TabSpalten.getS("Format"))+"|"+TabDaten.getI((bPeriode?"":"D")+sAic)+"]";
			else
				return new VonBis(bPeriode?null:TabDaten.getInhalt("V"+sAic),bPeriode?null:TabDaten.getInhalt("B"+sAic),TabDaten.getF((bPeriode?"":"D")+sAic),TabSpalten.getS("Format"),Abfrage.getLaenge(TabSpalten),g,TabSpalten.getI("Stamm"),(TabSpalten.getI("bits")&Abfrage.cstKeineEinheit)>0);
			//return new VonBis(TabDaten.getInhalt("V"+TabSpalten.getS(s)),TabDaten.getInhalt("B"+TabSpalten.getS(s)),TabDaten.getF("D"+TabSpalten.getS(s)),TabSpalten.getS("Format"),TabSpalten.getI("Laenge"),g.getFaktor(TabSpalten.getI("Stamm")));
		}
		else if (sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis"))
			return new VonBis(g,TabDaten.getInhalt("V"+TabSpalten.getS(s)),TabDaten.getInhalt("B"+TabSpalten.getS(s)),TabSpalten.getS("Format"));
		//else if (sDatentyp.equals("Filler"))
		//	return null;
		else if (sDatentyp.equals("Filler") || TabDaten.getInhalt((sDatentyp.equals("Einheiten")?"V":"")+TabSpalten.getS(s))==null)
			return "";
		else if (sDatentyp.equals("Mass") || sDatentyp.equals("BewMass") || sDatentyp.equals("CalcMass"))
			//return bImport?(Object)new Double(TabDaten.getF(TabSpalten.getS(s))):new Mass(g.getMass(TabSpalten.getI("Stamm")),TabDaten.getInhalt(TabSpalten.getS(s)),TabSpalten.getS("Format"));
			return new Zahl1(TabDaten.getF(TabSpalten.getS(s))/MassFaktor(TabSpalten.getI("Stamm")),Static.beiLeer(TabSpalten.getS("Format"),"x"));
		else if (sDatentyp.endsWith("Waehrung"))
                  return new Zahl1(g.getATS(TabDaten.getF(TabSpalten.getS(s))), TabSpalten.getS("Format"));
                else if (sDatentyp.equals("Double") || sDatentyp.equals("Farbe") || sDatentyp.equals("BenMass") || sDatentyp.equals("BewDauer") || sDatentyp.equals("BewZahl") || sDatentyp.equals("CalcField"))
			return new Zahl1(TabDaten.getF(TabSpalten.getS(s)),Static.beiLeer(TabSpalten.getS("Format"),"x"));
		else if (sDatentyp.equals("Integer") || sDatentyp.equals("SysAic"))
			return new Integer(TabDaten.getI(TabSpalten.getS(s)));
		else if (sDatentyp.equals("Einheiten"))
		{
			//g.progInfo("Einheiten:"+bCSV+"/"+getF("E"+Tab.getS(s))+"/"+getS("V"+Tab.getS(s)));
			Object Obj=(Object)(new Double(TabDaten.getF("E"+TabSpalten.getS(s)))+" "+TabDaten.getS("V"+TabSpalten.getS(s)));
                        //g.fixtestInfo("Einheiten-Export:"+Obj);
                        return Obj;
			//return bImport?(Object)(new Double(getF("E"+Tab.getS(s)))+" "+getS("V"+Tab.getS(s))):new Zahl1(getF(Tab.getS(s)),Tab.getS("Format"));
		}
		else if (sDatentyp.equals("Boolean") || sDatentyp.equals("BewBoolean"))
			return TabDaten.getB(TabSpalten.getS(s))?"J":"N";//Static.JaNein(getB(Tab.getS(s)));
		else if (sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Datum"))
			return new Zeit((Date)TabDaten.getInhalt(TabSpalten.getS(s)),Static.beiLeer(TabSpalten.getS("Format"),"yyyy/MM/dd"));
		else if (sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("Stringx"))
			return Static.replaceString(TabDaten.getM(TabSpalten.getS(s)),"\n"," ");
			//return new Memo1(TabDaten.getM(TabSpalten.getS(s)),TabSpalten.getI("Laenge"));
		else if (sDatentyp.equals("Hierarchie") && bImport)
			return g.TabStammtypen.getKennung(TabDaten.getI("w"+TabSpalten.getS(s).substring(1)))+"|"+TabDaten.getS(TabSpalten.getS(s));
		else
			return TabDaten.getInhalt(TabSpalten.getS(s));
	}

	private void ErmittleTabTab2(Tabellenspeicher TabAbfragen)
	{
          //if (Abf==null || iAbfrageBegriff != iAbfrageOld)
          if (TabAbfragen!=null && TabAbfragen.posInhalt("Aic",iAbfrageBegriff))
              Abf = (ShowAbfrage)TabAbfragen.getInhalt("Abfrage");
          else
          {
            Abf = new ShowAbfrage(g, iAbfrageBegriff, Abfrage.cstExport);
            if (TabAbfragen != null)
            {
              TabAbfragen.addInhalt("Aic", iAbfrageBegriff);
              TabAbfragen.addInhalt("Abfrage", Abf);
            }
          }
          iBits=Abf.iBits;
          TabSpalten=Abf.getSpalten();
	}

        private void loadParameter()
        {
          Parameter Para=new Parameter(g,"Export");
          String s=Para.getParameter("Option",true,true);
          if (Para.bGefunden)
          {
            EdtFile.getFileEditor().setText(s);
            CbxImport.setSelected((Para.int2&1)>0);
            CbxHochkomma.setSelected((Para.int2&2)>0);
            CbxCRLF.setSelected((Para.int2&4)>0);
            CbxStammspalte.setSelected((Para.int2&8)>0);
            CbxAnzeigen.setSelected((Para.int2&16)>0);
            CbxKeineEinheit.setSelected((Para.int2&32)>0);
            CbxKeineGruppe.setSelected((Para.int2&64)>0);
            //CbxAuffuellen.setSelected((Para.int2&128)>0);
            EdtTrennzeichen.setText(Para.int3==0?"":Para.int3==9?"Tab":Character.toString((char)Para.int3));
          }
          RadASCII.setSelected(Static.CP.equals("Cp850"));
          RadANSI.setSelected(Static.CP.equals("Cp1252"));
          RadUTF16.setSelected(Static.CP.equals("UTF-16"));
          RadUTF8.setSelected(Static.CP.equals("UTF-8"));
          Para.free();
        }

        private void saveParameter()
        {
                Parameter Para=new Parameter(g,"Export");
                String s=EdtFile.getValue();
                if (s.length() > 250)
                {
                  g.fixInfo("Export: Datei gekürzt von " + s.length() + " auf 250 Zeichen");
                  s = s.substring(0, 250);
                }
                Para.setParameter("Option",s,1,/*(CbxImport.isSelected()?1:0)+*/(CbxHochkomma.isSelected()?2:0)+(CbxCRLF.isSelected()?4:0)+(CbxStammspalte.isSelected()?8:0)
                                  +(CbxAnzeigen.isSelected()?16:0)+(CbxKeineEinheit.isSelected()?32:0)+(CbxKeineGruppe.isSelected()?64:0)/*+(CbxAuffuellen.isSelected()?128:0)*/,cTrennzeichen,0,true,true);
                Para.free();
        }

}

