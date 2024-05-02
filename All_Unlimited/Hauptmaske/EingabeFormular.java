/*
    All_Unlimited-Hauptmaske-EingabeFormular.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
//import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Dimension;
//import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerComponent;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
//import jclass.bwt.JCOutliner2Listener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Anzeige.Ampel;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Anzeige.GPS;
import All_Unlimited.Allgemein.Anzeige.JaNein;
import All_Unlimited.Allgemein.Anzeige.Memo1;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AUBits;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUComboList;
import All_Unlimited.Allgemein.Eingabe.AUFarbe;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.AUEkitCore;
//import All_Unlimited.Allgemein.Eingabe.HtmlEingabe;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.EF_Eingabe;
import All_Unlimited.Allgemein.Eingabe.EMail;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import All_Unlimited.Allgemein.Eingabe.GPS_Eingabe;
import All_Unlimited.Allgemein.Eingabe.HierarchieEingabe;
import All_Unlimited.Allgemein.Eingabe.MassEingabe;
import All_Unlimited.Allgemein.Eingabe.RolleEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.VonBisEingabe;
import All_Unlimited.Allgemein.Eingabe.WWW;
import All_Unlimited.Allgemein.Eingabe.WaehrungsEingabe;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Allgemein.Eingabe.History;
import All_Unlimited.Allgemein.Eingabe.SpinBoxAuswahl;
import All_Unlimited.Allgemein.Eingabe.RadioAuswahl;
import All_Unlimited.Grunddefinition.DefFormular;
import All_Unlimited.Grunddefinition.DefModell;
import All_Unlimited.Grunddefinition.UserManager;
import All_Unlimited.Grunddefinition.DefEigenschaft;
import All_Unlimited.Grunddefinition.DefAbfrage;
import All_Unlimited.Grunddefinition.DefAbfrage2;
import All_Unlimited.Grunddefinition.DefAufgabe;
import All_Unlimited.Print.DruckAufrufA;
import All_Unlimited.Print.Druckdefinition;
import All_Unlimited.Print.Drucken;
import All_Unlimited.Print.Seitenlayout;

import javax.swing.JToolBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;
//import All_Unlimited.InstrumentationAgent;

//import java.util.GregorianCalendar;
//import java.util.Calendar;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;

import java.sql.Timestamp;

import All_Unlimited.Allgemein.Eingabe.MemoF;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;

public class EingabeFormular extends All_Unlimited.Allgemein.Formular
{

  // add your data members here
	Global g;
	boolean bOk = false;
	int iStamm = 0;
	int iStammOld = -1;
	boolean bReLoad=false;
	//boolean bZwingend=false;
	int iSatz = 0;
	int iSatzOld = -2;
        Timestamp tsVonOld=null;
        Timestamp tsBisOld=null;
	int iProt = 0;
	//int iFormular = 0;
	//int iStammtyp = 0;
	Tabellenspeicher TabEigenschaft;
	Tabellenspeicher TabOutliner;
        Tabellenspeicher TabButtons;
        Tabellenspeicher TabRadio;
        Tabellenspeicher TabED; // Einzeldaten
        Tabellenspeicher TabEinAus=null;
        Tabellenspeicher TabEinAus2=null;
        Tabellenspeicher TabJoker3=null;
        //Tabellenspeicher TabCalc=null;
        Tabellenspeicher TabFPop=null;
        Tabellenspeicher TabEinstellung=null;
        Tabellenspeicher TabAbfragenTitel=null;
	//Tabellenspeicher TabAbfrage;
	Vector<Integer> VecBezeichnung;
	Vector<Integer> VecAic;
	Vector<Integer> VecSelect=null;
	String sBezeichnung="";
	String sKennung="";
	String sSQL_Zeile;
        String sSQL_Zeile2=null;
	java.sql.Date dtEin = null;
	java.sql.Date dtAus = null;
        //java.sql.Date dtStT = null;
	java.sql.Timestamp dtGueltigBew = null;
	//boolean bBenutzerInfo = false;
	int iStamm2 = 0;
	int iEigenschaft2 = 0;
	//JCOutlinerFolderNode NodAbfrage = null;
	//ComboSort CboVon = null;
	boolean bRefreshEigenschaften = true;
	boolean bKopie = false;
        //boolean bNeu = false;
	long iClock1,iClock2=Static.get_ms();
	String sSave="";
	private int iMax=-1;
	private int iMom=-1;
	//ComboSort CboAbfrage = null;
	//JCheckBox CbxAlle = null;
	String sTitel="";
	int iWtOld=-1;
	boolean bHaupt=false;
	boolean bEntfernen=false;
	int iFO=0;
	boolean bFirst=true;
        //int iVerteiler=Integer.MAX_VALUE;
	long lClockSave=0;
	JCOutliner GidHaupt=null;
	//JCOutlinerNode NodHaupt=null;
        boolean bSpeichere=false;
        private EingabeFormular self=this;
        private JDialog DlgLogin;
        private JButton BtnLoginOk=null;
        private Datum EdtGueltig2=null;
        //private Datum EdtEintritt=null;
        //private Datum EdtAustritt=null;
        private Datum EdtStichtag=null;
        //private JPopupMenu popup;
        private JPopupMenu popup2;
        private Point P=null;
        private String sSuchtext="";
	//private int iSuchBits=1;
        private boolean bAb = true;
        //private JMenuItem MnuHistory;
        //private JMenuItem MnuSuchen;
        //private JMenuItem MnuMehrfach;
        //private boolean bOutEvent=true;
        private boolean bSaveRolle=false;
        private ActionListener AL1=null;
        private ActionListener AL2=null;
        private JButton BtnCheck=null;
        private JDialog FomNC=null;
        private AUOutliner OutNeu = new AUOutliner(new JCOutlinerFolderNode(""));
        private boolean bNeuCheck=false;
        private String sSqlNP;
        //private String sSqlNP2;
        private Vector VecNP;
        private boolean bSave=false;
        private boolean bSave0=false;
        //private JButton BtnSave=null;
        boolean bTabRefresh=true;
        //private Tabellenspeicher TabClock2=null;
        private Clock clock=null;
        private int iMandant=0;
        private int iMandantM=0;
        //private int iCPos=0;
        //private static Stack staFTP_Bild=null;
        private boolean bMulti=false;
        private Tabellenspeicher TabJoker2=null;
        private Tabellenspeicher TabSave=null;
        private ActionListener MActL=null;
        private AUTable Tbl=null;
        private ActionListener AL=null;
        private JLabel LblAbf=null;
        //private long lClockµ=0;
        private JToggleButton BtnAlleDaten=null;
        private JToggleButton BtnNurVector=null;
        private JToggleButton BtnNurFirma=null;
        private boolean bBewVec=false;
        private int iSubStt;
        private boolean bBeginn=false;
        private boolean bEnde=false;
        private boolean bCalcS=false;
        private boolean bkHE=false;
        private boolean bImport=false;

        private boolean bRefreshMom=false;
        private boolean bMakeVisible=true;
        private int iRA=0;
        private boolean bKeinNeu=false;
        private int iKnoten=0;
        private int iEigANR=0;
        private int iEigDST=0;
        private int iEigZone=0;
        private JLabel LblSD=null;
        private JDialog DlgSuche=null;
        private int iFormular=0;
        private JLabel LblUser=null;
        private JLabel LblMandant=null;
        private JButton BtnUser=null;
        private boolean bSync=false;

        public static AUVector VReadOnly=new AUVector(new String[] {"Neu","Kopie","Loeschen","Loesche Rolle","Speichern"});

	//DateWOD dtVon=null;

	//ComboSort CboModell;
	//JDialog Dlg;
	/*Datum dtVon = new Datum("yyyy-MM-dd");
	Datum dtBis = new Datum("yyyy-MM-dd");*/

        public static EingabeFormular HoleFormular(Global rg,int riFormular,Vector Vec,int riStamm,boolean rbBewVec)
        {
          return HoleFormular(rg,riFormular,Vec,riStamm,rbBewVec,null,0,false);
        }

        private static boolean inBearbeitung(Global rg,int riFormular)
        {
          int iPos=rg.TabFormulare==null ? -1:rg.TabFormulare.getPos("Aic",riFormular);
          if (iPos<0)
            return false;
          else
          {
            EingabeFormular EF=((EingabeFormular)rg.TabFormulare.getInhalt("Formular",iPos));
            if (EF.Modified())
            {
              EF.thisFrame.toFront();
              EF.Speicherfrage();
              //new Message(Message.WARNING_MESSAGE,(JFrame)EF.thisFrame,rg).showDialog("speichernZuerst");
              return true;
            }
            else
              return false;
          }
        }

	@SuppressWarnings("unchecked")
	public static EingabeFormular HoleFormular(Global rg,int riFormular,Vector Vec,int riStamm,boolean rbBewVec,JDialog FomVor,int riKnoten,boolean bSync)
	{
		  //rg.fixtestInfo("EingabeFormular.HoleFormular "+riFormular+", Stamm="+riStamm+", Knoten="+riKnoten);
		//long lClock = Static.get_ms();
                /*if (Vec != null && Vec.indexOf(riStamm)>0)
                {
                  Vec.remove(new Integer(riStamm));
                  Vec.insertElementAt(riStamm,0);
                }*/
           if (inBearbeitung(rg,riFormular))
             return (EingabeFormular)rg.TabFormulare.getInhalt("Formular",rg.TabFormulare.getPos("Aic",riFormular));
                if (riStamm==0)
                  riStamm=-1;
		rg.progInfo("                      --------------          EingabeFormular:"+riFormular+"/"+riStamm+"/"+rbBewVec+"/"+Vec);
		rg.printInfo(2,"Zeige EingabeFormular:"+riFormular);
                rg.SaveVerlauf(riFormular,riStamm>0?riStamm:0,riStamm<-1?-riStamm:0);
		//rg.progInfo("EF-Vec:"+Vec);
		rg.initSI();
		EingabeFormular EF;
                int iPos=rg.TabFormulare.getPos("Aic",riFormular);
		if(iPos>=0)
		{
			EF=((EingabeFormular)rg.TabFormulare.getInhalt("Formular",iPos));			
	        
                        EF.bBewVec=rbBewVec;
                        EF.iKnoten=riKnoten;
                        if (riStamm==-1 && (EF.iBits & cstOhneStamm)>0)
                          riStamm=0;
                        if (riStamm>0 && EF.Synchron() && EF.Bewegungstyp()==0)
                          rg.setSyncStamm(EF.iStammtyp,riStamm,EF.iRolle);
                        EF.MonatsCheck(rg);
                        if (EF.keinStamm())
                          EF.VecAic=null;
                        else if (EF.VecAic == null && Vec!=null || EF.VecAic !=null && (Vec==null || !EF.VecAic.containsAll(Vec) || Vec.size()!=EF.VecAic.size()))
                        {
                          //rg.testInfo("---------------------------------- Menge nun anders:" + Vec);
                          EF.VecAic=Vec;
                          if (Vec !=null)
                            EF.VecSelect=new Vector(Vec);
                          if (EF.TabOutliner.posInhalt("Eig",0))
                          {
                            //rg.fixInfo("rücksetzen");
                            EF.bRefreshEigenschaften=false;
                            JCOutliner Out=(JCOutliner)EF.TabOutliner.getInhalt("Gid");
                            Out.selectNode(Out.getRootNode(),null);
                            //if (!
                                EF.FuelleOutliner((JPanel)EF.TabOutliner.getInhalt("Pnl"),riStamm,false,true);//                       iNeu=-1;
                            EF.bRefreshEigenschaften=true;
                           }
                        }
                        else
                        {
                          if (EF.TabOutliner.posInhalt("Eig",0) && ZeiraumAnders(EF.TabOutliner,rg))
                            EF.FuelleOutliner((JPanel)EF.TabOutliner.getInhalt("Pnl"),riStamm,false,true);
                        }
                        //else
                        //  rg.testInfo("---------------------------------- Menge ist gleich geblieben");
			//EF.VecAic=EF.keinStamm()?null:Vec;
                        if (EF.iStammtyp>0 || riStamm != EF.iStamm)
                        {
                          boolean bChange=riStamm != EF.iStamm;
                          EF.FuelleEigenschaften(riStamm, true);
                          if (bChange)
                          {
                            //rg.progInfo("Titelzusatz HoleFormular");
                            EF.Titelzusatz("");
                          }
                          if (EF.Bewegungstyp()>0)
                            EF.bReLoad=true;
                        }
			EF.thisFrame.toFront();
                        // für Reisedaten nötig da hier lt. Joker auf Mitarbeiter eingeschränkt wird
                        if (EF.isRefresh() && riStamm == 0 && Vec == null)
                        {
                          rg.progInfo("  ----------------   ---------------------  Refresh bei null");
                          EF.Refresh();
                        }
                        //rg.printInfo("DetailAufruf:"+(Static.get_ms()-lClock));
                        //rg.printSI("EF-Std.");
		}
		else
		{
                        EF=new EingabeFormular(rg,riFormular,riStamm,Vec==null ? new Vector():Vec,FomVor,riKnoten,bSync);
                        EF.iFormular=riFormular;
//                        rg.fixtestError("neues Formular "+EF.iFormular+" mit Stamm="+riStamm);
                        if ((EF.iBits&Formular.cstInfo)==0)
                        {
                          EF.bBewVec = rbBewVec;
                          if (EF.thisFrame != null)
                          {
                            //rg.progInfo("Titelzusatz2 HoleFormular");
                            EF.Titelzusatz("");
                            rg.putTabFormulare(riFormular, EF.Typ() == Formular.Bewegung ? 0 : EF.iStammtyp, EF);
                          }
                          //EF.Refresh();
                        }
		}
		return EF;
	}

        public String toString()
        {
          return "EingabeFormular - "+g.TabBegriffe.getBezeichnungS(getBegriff());
        }

        private static boolean ZeiraumAnders(Tabellenspeicher Tab,Global g)
        {
          return !Tab.getInhalt("von").equals(g.getVon()) || !Tab.getInhalt("bis").equals(g.getBis());
        }

	//Mehrfach
	public static void HoleFormular(Global rg,int riForm,int riStamm,int riStamm2,int riEigenschaft2,String sTitel)
	{
		//long lClock = Static.get_ms();
		rg.initSI();
                int iPos=rg.TabFormulare.getPos("Aic",riForm);
		if(iPos>=0)
		{
			EingabeFormular EF = ((EingabeFormular)rg.TabFormulare.getInhalt("Formular",iPos));
                        //rg.progInfo("Titelzusatz3 HoleFormular");
                        EF.Titelzusatz(sTitel);
                        if (riStamm2 != EF.iStamm2)
                        {
                          EF.iStamm2 = riStamm2;
                          if (EF.TabOutliner.posInhalt("Eig",0))
                          {
                            EF.bRefreshEigenschaften=false;
                            //if (!
                            EF.FuelleOutliner((JPanel)EF.TabOutliner.getInhalt("Pnl"),riStamm,false,true);//                       iNeu=-1;
                            EF.bRefreshEigenschaften=true;
                          }
                        }
			EF.FuelleEigenschaften(riStamm,true);
		}
		else
		{
			EingabeFormular EF=new EingabeFormular(rg,riForm,riStamm2,riEigenschaft2,sTitel);
			if (EF.thisFrame!=null)
				rg.putTabFormulare(0,riForm,EF);
                        if (riStamm>0)
                          EF.FuelleEigenschaften(riStamm,true);
		}
		//rg.printInfo("DetailAufruf:"+(Static.get_ms()-lClock));
		//rg.printSI("EF-M.F.");
	}

        private String Rolle()
        {
          return bSaveRolle?" and aic_rolle="+iRolle:" and aic_rolle is null";
        }

        /*private String Rolle(int iRolleMom)
        {
          return iRolleMom > 0 ? " and aic_rolle=" + iRolleMom : " and aic_rolle is null";
        }*/

        private String DatenFilter()
        {
          if (iDaten==ALLE)
            return "";
          if (iDaten==VECTOR)
            return " "+g.getBegriffS("Show","nur_Ordner");
          if (iDaten==FIRMA)
            return " <"+g.getStamm(g.getFirma())+">";
          return " <->";
        }

	private void Titelzusatz(String rsTitel)
	{
          g.progInfo("xx                   Titelzusatz:"+rsTitel+"/"+iStamm);
          long lClock = Static.get_ms();
		if (thisFrame==null)
			return;
                if (keinStammTitel())
                  rsTitel="";
                else if (iStamm>0 && rsTitel.equals(""))
                  rsTitel=g.getStamm(iStamm);//SQL.getString(g,"select bezeichnung from stammview where aic_rolle is null and aic_stamm="+iStamm);
		sTitel= rsTitel;
                //rg.progInfo("***************************** Titelzusatz:"+sFormularBezeichnung+"/"+rsTitel+"/"+iStamm);
		//rg.printInfo(2,"Titelzusatz für "+iStammtyp+":"+rsTitel);
                //long lClock2 = Static.get_ms();
                String sAnz="";
		if (Typ()==Stamm)
		{
			iMax=bSaveRolle ? g.getAnzahlRolle(iRolle):g.getAnzahlStamm(iStammtyp);
                        if (iMax>-1)
                        {
                          //if(iMom < 0)
                            iMom = SQL.getInteger(g,"select count(*) from stammview where aic_stammtyp=" + iStammtyp +
                                                  " and aic_mandant=" + g.getMandant()+Rolle());
                          sAnz = " (" + iMom + "/" + (iMax == -1 ? "\u221E" : "" + iMax) + ")";
                        }
		}
		//rg.progInfo("Titelzusatz:"+rsTitel+"/"+iMom);
                //long lClock3 = Static.get_ms();
		setTitle(sFormularBezeichnung+DatenFilter()+(!rsTitel.equals("") ? " - "+rsTitel:"")+sAnz+(keinZR()?"":" : "+g.getVonBis("dd.MM.yyyy",true)));
//		g.fixtestError("Global="+InstrumentationAgent.getObjectSize(g)+", EF "+sFormularBezeichnung+"="+InstrumentationAgent.getObjectSize(this));
		if (LblSD!=null)
			LblSD.setText(new Zeit(g.getAbschlussP(iProg, iStamm),"dd.MM.yyyy").toString());
		int iM=0;
		if (LblMandant != null || BtnUser!=null)
		{
			iM=iStamm<=0 ? 0:SQL.getInteger(g, "select aic_mandant from stammview2 where aic_stamm=?", -1, ""+iStamm);
			if (BtnUser != null)
				BtnUser.setEnabled(iM==g.getMandant());
		}
		if (LblMandant != null)
			LblMandant.setText(iStamm<=0 ? "":g.getMandant(iM, null));
		if (LblUser != null)
			LblUser.setText(iStamm<=0 ? "":g.getShow("Benutzer")+": "+SQL.getString(g, "select kennung from benutzer where"+notDef()+"aic_stamm=?", ""+iStamm));
		
                //long lClock4 = Static.get_ms();
            Clock.add(clock,"Titelzusatz ",sTitel,lClock);
            //long lClock5 = Static.get_ms();
            //g.progInfo((lClock2-lClock)+",3="+(lClock3-lClock)+",6="+(lClock6-lClock)+",7="+(lClock7-lClock)+",8="+(lClock8-lClock)+"/"+",4="+(lClock4-lClock)+",5="+(lClock5-lClock));
	}

        /*private void clockInfo2(String sArt,String sAbfrage,long lClock)
        {
          if (g.Clock2())
          {
            g.clockInfo2(sArt+" "+sAbfrage,lClock);
            if (TabClock2 != null)
            {
              TabClock2.newLine();
              TabClock2.setInhalt("Art",sArt);
              TabClock2.setInhalt("Abfrage",sAbfrage);
              TabClock2.setInhalt("Zeit",(int)(Static.get_ms()-lClock));
            }
          }
        }*/

        private void MonatsCheck(Global rg)
        {
          //g.fixInfo("MonatsCheck:"+Monat()+"/"+Woche()+"/"+Tag());
          if (Monat() || Woche() || Tag())
          {
            //if (Monat())
            //    rg.setMonth(g.getVon());
            g.setAktDate(false);
            String sZeitart=Monat() ? "Monat":Woche() ? "Woche":"Tag";
            g.setZA(0, sZeitart);
            Zeitraum.PeriodeToVec(rg,sZeitart,true);
            //rg.progInfo("MonatsCheck:"+g.sZeitart+"/"+g.VecPerioden);
          }
        }

	// normaler Aufruf
	private EingabeFormular(Global rg,int riFormular,int riStamm,Vector Vec,JDialog FomVor,int riKnoten,boolean bSync)
	{
		super(rg,riFormular,FomVor);
                g=rg;
                iKnoten=riKnoten;
                //g.fixInfo("Übergebener Knoten:"+g.getStamm(iKnoten)+" ("+iKnoten+")");
                if (g.Clock2()) clock=new Clock(g,(JFrame)thisFrame);
                long lClock = Clock.startClock(clock);
                bRefreshMom=true;
                MonatsCheck(rg);
		//rg.add("E");
		Count.add(Count.EingabeFormular);
		//rg.debugInfo("F:"+riFormular+"/Stt:"+iStammtyp+"/Typ:"+iTyp+"/Eig:"+iEigenschaft);
                if (riStamm>0 && Synchron() && Bewegungstyp()==0)
                  g.setSyncStamm(iStammtyp,riStamm,iRolle);
		InitFormular(rg,riStamm,iStammtyp,Vec,bSync);
                bRefreshMom=false;
                Clock.showClock(g,clock,"EingabeFormular "+sFormularBezeichnung, lClock);
                if (thisFrame instanceof JDialog)
                  show();
	}

	/*public EingabeFormular(Global rg,int riStamm,int riStammtyp)
	{
		super(rg,riStammtyp,cstStdFormular);
                long lClock = Clock.startClock(clock);
                g=rg;
                Count.add(Count.EingabeFormular);
		InitFormular(rg,riStamm,riStammtyp,null);
                Clock.showClock(g,clock,"EingabeFormular "+sFormularBezeichnung, lClock);
	}*/

	// Aufruf von Outliner und Hierarchie
	/*private EingabeFormular(Global rg,int riStamm,int riStammtyp,Vector Vec)
	{
		super(rg,riStammtyp);
		//rg.add("Eo");
		Count.add(Count.EingabeFormular);
		InitFormular(rg,riStamm,riStammtyp,Vec);
	}*/

	/* Aufruf von ComboBox
	private EingabeFormular(Global rg,ComboSort rCboVon,int riStammtyp)
	{
		super(rg,riStammtyp);
		//rg.add("Ec");
		Count.add(Count.EingabeFormular);
		CboVon = rCboVon;
		InitFormular(rg,CboVon.getSelectedAIC(),riStammtyp);
	}*/

	// Aufruf von Mehrfach
	private EingabeFormular(Global rg,int iForm,int riStamm2,int riEigenschaft2,String sTitel)
	{
		super(rg,iForm,null);
		//rg.add("Em");
		Count.add(Count.EingabeFormular);
		iStamm2 = riStamm2;
		iEigenschaft2 = riEigenschaft2;
		InitFormular(rg,0,iStammtyp,null,false);
                //rg.progInfo("Titelzusatz EingabeFormular Mehrfach");
                Titelzusatz(sTitel);
	}

	public void finalize()
	{
		Count.sub(Count.EingabeFormular);
		super.finalize();
	}

	/* Aufruf mit Outliner
	public EingabeFormular(Global rg,int riStammtyp,JCOutlinerNode rNod)
	{
		super(rg,riStammtyp);
		NodAbfrage = rNod;
		InitFormular(rg,0,riStammtyp);
	}
	*/

	private void Ausgabe(String s)
	{
		//iClock1 = iClock2;
		//iClock2 = Static.get_ms();
		if (g != null)
                  g.printSI(s);
			//g.printInfo(2,s+":"+(iClock2-iClock1));
	}

	private Color getColor(boolean bGid)
	{
	  if (!bGid)
            return g.ColTable;
          else
          {
            int iPos=TabOutliner.getPos("Pnl", (JPanel)TabAbf.getInhalt("Pnl"));
            if (iPos>=0)
              return TabOutliner.getS(iPos,"Typ").equals("Haupt") ? g.ColHS:g.ColBackground;
            else
              return Color.BLACK;
          }
	}

	@SuppressWarnings("unchecked")
	private void InitFormular(Global rg,int riStamm,int riStammtyp,Vector Vec,boolean rbSync)
	{
          rg.progInfo("-------------------------------------- InitFormular ------------------------------------------");
		if (thisFrame==null)
			return;
		g = rg;
    bSync=rbSync;
                //thisFrame.setBackground(g.ColEF);
                //ComboSort.showClock(g,"vor Init");
                //printSI()
                if (riStamm==-1 && (iBits & cstOhneStamm)>0)
                  riStamm=0;
		Ausgabe("% InitFormular");
                //g.fixInfo(" --------------------------------- Daten vorher:"+iDaten);
                if (iDaten==0 || BtnNurVector==null)
                  iDaten=(iBits & cstNurOrdner)==0 /*|| Vec.size()==0*/ ? ALLE:VECTOR; // 13.9.2011: nicht umschalten wenn kein Wert
                if (Vec!=null && Vec.size()>=10000)
                  iDaten=ALLE;
                //g.fixInfo(" -------------------------------- Daten nachher:"+iDaten);
                //g.progInfo("InitFormular1:"+iStammtyp+"/"+iStamm);
		iStammtyp = riStammtyp;
		iStamm = getStamm(riStamm);
                //g.progInfo("InitFormular2:"+iStammtyp+"/"+iStamm);
		VecAic = keinStamm()?null:Vec;
                VecSelect = keinStamm() || Vec==null ? null:new Vector(Vec);
		//g.testInfo("VecAic="+VecAic);
                if (Typ()==Stamm)
                {
                  int iPos=g.TabStammtypen.getPos("aic",iStammtyp);
                  if (iPos>=0)
                    iEigANR=g.TabStammtypen.getI(iPos,"ANR_Eig");
                  if (iEigANR>0)
                    g.fixtestInfo("iEigANR="+iEigANR);
                }
                if (iRolle>0)
                {
                  int iPos=g.TabRollen.getPos("Aic", iRolle);
                  if (iPos>=0)
                      bSaveRolle = g.TabRollen.getI(iPos,"Stt") == iStammtyp;
                }
                TabED=new Tabellenspeicher(g,new String[] {"Abfrage","Kennung","Lbl"});
		TabOutliner=new Tabellenspeicher(g,new String[] {"Gruppe","Stt","Eig","Typ","Gid","Pnl","Abfrage","Bezeichnung","Timer","Tab","Nord","von","bis"});
		VecBezeichnung=Abfrage.FuelleVecBezeichnung(g,iStammtyp);
		//TabBezeichnung.showGrid();
		/*Image Gif = iRolle>0 ? g.getRolleGif(iRolle)://g.TabRollen.posInhalt("Aic",iRolle) ? (Image)g.TabRollen.getInhalt("Bild"): null:
                    Typ() == Bewegung ? g.getBewGif(Bewegungstyp())://g.TabErfassungstypen.posInhalt("Aic",Bewegungstyp()) ? (Image)g.TabErfassungstypen.getInhalt("Bild"): null:
			g.getSttGif(iStammtyp);//g.TabStammtypen.posInhalt("Aic",iStammtyp) ? (Image)g.TabStammtypen.getInhalt("Bild"): null;
                setIconImage(Gif);*/
		//if (Gif != null)
		//	((Frame)thisFrame).setIconImage(Gif);
		//setTitle("EingabeFormular - "+(iFormular==0 ? g.getBezeichnung("Stammtyp",iStammtyp) : getTitle())+(iStamm2!=0 ? " von "+iStamm2:""));

		TabEigenschaft = new Tabellenspeicher(g,new String[] {"Aic","Datentyp","Komponente","Bez","History","Stammtyp","Stamm","bits","bits2","bits3","EF","Spalte","bed"});
                //TabEigenschaft.setTitel("TabEigenschaft");
		Ausgabe("% vor Gruppen");
		/*JPanel Pnl = getFrei("Hauptgruppe");
		if (Pnl != null)
		{
			Pnl.add(new JLabel("!!! Hauptgruppe steht nicht mehr zur Verfügung !!!"));
			g.printError("!!! Hauptgruppe steht nicht mehr zur Verfügung !!!");
		}*/
			//FuelleGruppe(Pnl,new Tabellenspeicher(g,"select eigenschaft.aic_stamm,eigenschaft.aic_stammtyp,eigenschaft.aic_eigenschaft aic,eigenschaft.kennung,immer reihe from eigenschaft where immer is not null order by immer"));
                Tabellenspeicher TabGruppe = getTab("Gruppe");
                Tabellenspeicher TabEFGroup= getTab("EFGroup");
                //TabEFGroup.showGrid("EFGroup");
                //TabGruppe.showGrid("Gruppe");
                Vector VecGruppe=TabGruppe.getVecSpalte("Kennung");
                Tabellenspeicher TabGZ=null;
                if (!VecGruppe.isEmpty())
                {
                  //g.progInfo("Gruppe:"+VecGruppe);
                  TabGZ=new Tabellenspeicher(g,bSaveRolle?"select e.aic_eigenschaft aic,G.aic_begriff,g.reihenfolge reihe from Gruppe_zuordnung g join eigenschaft e on e.aic_eigenschaft=g.aic_eigenschaft join Rolle_zuordnung sz on sz.aic_eigenschaft=e.aic_eigenschaft where G.aic_begriff"+Static.SQL_in(VecGruppe)+" and sz.aic_Rolle="+iRolle+" order by g.reihenfolge":
                                Typ()==Stamm?"select e.aic_eigenschaft aic,G.aic_begriff,g.reihenfolge reihe from Gruppe_zuordnung g join eigenschaft e on e.aic_eigenschaft=g.aic_eigenschaft join stammtyp_zuordnung sz on sz.aic_eigenschaft=e.aic_eigenschaft where G.aic_begriff"+Static.SQL_in(VecGruppe)+" and sz.aic_stammtyp="+iStammtyp+/*g.getEigenschaften(false,"e")+*/" order by g.reihenfolge":
				Typ()==Bewegung?"select e.aic_eigenschaft aic,G.aic_begriff,g.reihenfolge reihe from Gruppe_zuordnung g join eigenschaft e on e.aic_eigenschaft=g.aic_eigenschaft join bew_zuordnung bz on bz.aic_eigenschaft=e.aic_eigenschaft where G.aic_begriff"+Static.SQL_in(VecGruppe)+" and bz.aic_bewegungstyp="+Bewegungstyp()+/*g.getEigenschaften(false,"e")+*/" order by g.reihenfolge":"",true,"EF_Gruppen"+iStammtyp+"_"+getBegriff());
                  //TabGZ.showGrid("TabGZ");
                  sort(TabGruppe);
                }
                sort(TabEFGroup);
                if (TabEFGroup.isEmpty() || TabGruppe.isEmpty() || TabEFGroup.max("use")<256)
                {
                  for (TabGruppe.moveFirst(); !TabGruppe.out(); TabGruppe.moveNext())
                    FuelleGruppe(false, null, (JPanel)TabGruppe.getInhalt("Komponente"), TabGZ, TabGruppe.getI("Kennung"), TabGruppe.getI("use") % 256);
                  for (TabEFGroup.moveFirst(); !TabEFGroup.out(); TabEFGroup.moveNext())
                    FuelleEFGroup((JPanel)TabEFGroup.getInhalt("Komponente"), TabEFGroup.getI("Kennung"), TabEFGroup.getI("use") % 256);
                }
                else
                {
                  int iMax=(int)Math.max(TabEFGroup.max("use"),TabGruppe.max("use"))/256;
                  for (int i=1;i<=iMax;i++)
                  {
                    for (TabGruppe.moveFirst(); !TabGruppe.out(); TabGruppe.moveNext())
                      if (TabGruppe.getI("use")/256==i)
                        FuelleGruppe(false, null, (JPanel)TabGruppe.getInhalt("Komponente"), TabGZ, TabGruppe.getI("Kennung"), TabGruppe.getI("use") % 256);
                    for (TabEFGroup.moveFirst(); !TabEFGroup.out(); TabEFGroup.moveNext())
                      if (TabEFGroup.getI("use")/256==i)
                        FuelleEFGroup((JPanel)TabEFGroup.getInhalt("Komponente"), TabEFGroup.getI("Kennung"), TabEFGroup.getI("use") % 256);
                  }
                  for (TabGruppe.moveFirst(); !TabGruppe.out(); TabGruppe.moveNext())
                    if (TabGruppe.getI("use")/256==0)
                      FuelleGruppe(false, null, (JPanel)TabGruppe.getInhalt("Komponente"), TabGZ, TabGruppe.getI("Kennung"), TabGruppe.getI("use") % 256);
                  for (TabEFGroup.moveFirst(); !TabEFGroup.out(); TabEFGroup.moveNext())
                    if (TabEFGroup.getI("use")/256==0)
                      FuelleEFGroup((JPanel)TabEFGroup.getInhalt("Komponente"), TabEFGroup.getI("Kennung"), TabEFGroup.getI("use") % 256);
                }
		JPanel Pnl = getFrei("Nebengruppe");
		if (Pnl != null)
			FuelleGruppe(false,null,Pnl,new Tabellenspeicher(g,"select eigenschaft.aic_stamm,eigenschaft.aic_stammtyp,eigenschaft.aic_eigenschaft aic,eigenschaft.kennung,reihenfolge reihe from "+
				(Typ()==Stamm?"stammtyp_zuordnung"+g.join("eigenschaft","stammtyp_zuordnung")+" where stammtyp_zuordnung.aic_stammtyp="+iStammtyp:
				 Typ()==Bewegung?"bew_zuordnung"+g.join("eigenschaft","bew_zuordnung")+" where bew_zuordnung.aic_bewegungstyp="+Bewegungstyp():"")+//g.getEigenschaften(false,"eigenschaft")+
				" order by reihenfolge",true),0,0);
                setzeZustand(); // setzt Elemente auf Editierbar (wenn erlaubt)
                addAL(); // erzeugt bei Action-Listener
                Pnl=getFrei("Tb_Abfrage_anzeigen");
                if (Pnl !=null)
                {
                  LblAbf=new JLabel(" ");
                  LblAbf.setFont(g.fontTitel);
                  java.awt.Container PPar=Pnl.getParent();
                  if (PPar instanceof JToolBar)
                  {
                    int iPos = ((JToolBar)PPar).getComponentIndex(Pnl);
                    PPar.remove(iPos);
                    PPar.add(LblAbf, iPos);
                  }
                  else
                    Pnl.add(LblAbf);
                }
                Ausgabe("% Gruppen");
                int iD=0;
                AUTable Komp=null;
                if (TabAbf != null)
                 for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
                  //if (TabAbf.getS("Art").equals("JCTable") && TabAbf.isNull("Komponente") || !TabAbf.getS("Art").equals("JCTable") && TabOutliner.getPos("Pnl",TabAbf.getInhalt("Pnl"))<0)
                  if (iD != TabAbf.getI("Zeile"))
                  {
                    iD=TabAbf.getI("Zeile");
                    if (TabAbf.getS("Art").equals("JCOutliner"))
                    {
                      FuelleOutliner((JPanel)TabAbf.getInhalt("Pnl"), TabAbf.getI("Aic"), true, false);
                      int iPos=TabOutliner.getPos("Pnl",TabAbf.getInhalt("Pnl"));
                      if(TabOutliner.getS(iPos,"Typ").equals("Haupt") && LblAbf != null)
                        LblAbf.setText(g.TabBegriffe.getBezeichnungS(((ShowAbfrage)TabOutliner.getInhalt("Abfrage",iPos)).iBegriff));
                      if ((((ShowAbfrage)TabOutliner.getInhalt("Abfrage",iPos)).iBits&Abfrage.cstEinzeldaten)==0 && !TabOutliner.getS(iPos,"Typ").equals("Haupt"))
                        addToolbar((JPanel)TabAbf.getInhalt("Pnl"),TabAbf.getI("Aic"),iPos,null);
                      if (TabAbf.getI("h")>0)
                      {
                        g.progInfo("Outlinerhöhe nun "+TabAbf.getI("h"));
                        Object Obj=TabOutliner.getInhalt("Gid",iPos);
                        if (Obj instanceof JCOutliner)
                          ((JCOutliner)Obj).setNodeHeight(TabAbf.getI("h"));
                      }
                    }
                    else
                    {
                      long lClock2 = Static.get_ms();
                      JPanel Pnl2=(JPanel)TabAbf.getInhalt("Pnl");
                      Vector VecMod=getVec("Modell",Pnl2);
                      int iAbf=g.getAbfrage(iD);
                      //g.progInfo("------------- "+iD+":"+iAbf+"/"+TabAbf.getI("Aic"));
                      if (iAbf==0)
                	iAbf=TabAbf.getI("Aic");
                      ActionListener evRefresh=new ActionListener()
                      {
                        public void actionPerformed(ActionEvent ev)
                        {
                          //g.fixInfo("Event von Tabelle ausgelöst");
                          Refresh();
                        }
                      };

                      Komp = new AUTable(this,iAbf,isBewBew()?-iSatz:iStamm,iStammtyp,g,isBewBew(),VecMod,iD,evRefresh);
                      checkJoker(Komp.A,Pnl2,g.ColTable,Komp);
                      addToolbar(Pnl2,iAbf,-1,Komp);
                      Pnl2.add("Center",Komp);
                      TabAbf.setInhalt("Komponente",Komp);
                      Clock.add(clock,"AUTable1",Komp.sDefBez,lClock2);
                    }
                  }
                  else if (TabAbf.isNull("Rbn"))
                  {
                    boolean bGid=TabAbf.getS("Art").equals("JCOutliner");
                    TabAbf.push();
                    JPanel Pnl2=(JPanel)TabAbf.getInhalt("Pnl");
                    //JPanel Pnl3=new JPanel(new FlowLayout());

                    /*Color Col=null;
                    if (!bGid)
                      Col=g.ColTable;
                    else
                    {
                      int iPos=TabOutliner.getPos("Pnl", (JPanel)TabAbf.getInhalt("Pnl"));
                      if (iPos>=0)
                	Col=TabOutliner.getS(iPos,"Typ").equals("Haupt") ? g.ColHS:g.ColBackground;
                      else
                	Col=Color.BLACK;
                    }*/
                    ButtonGroup RadGroup=new ButtonGroup();
                    if (TabAbf != null)
                     for (TabAbf.posInhalt("Pnl",Pnl2);!TabAbf.eof() && TabAbf.getInhalt("Pnl").equals(Pnl2); TabAbf.moveNext())
                    {
                      JRadioButton Rbn=new JRadioButton(g.TabBegriffe.getBezeichnungS(TabAbf.getI("Aic")));
                      Rbn.setBackground(getColor(bGid));//bGid ? g.ColBackground:g.ColTable);
                      Rbn.setFont(g.fontBezeichnung);
                      RadGroup.add(Rbn);
                      if (TabAbf.getB("aktiv"))
                      {
                        Rbn.setSelected(true);
                        //if (!bGid)
                        //  Komp=(AUTable)TabAbf.getInhalt("Komponente");
                      }
                      Rbn.addActionListener(new ActionListener()
                      {
                        public void actionPerformed(ActionEvent e)
                        {
                          if (TabAbf.posInhalt("Rbn", e.getSource()))
                          {
                            //if (TabOutliner.posInhalt("Pnl",(JPanel)TabAbf.getInhalt("Pnl")))
                            //  ((JPanel)TabAbf.getInhalt("Pnl")).remove((JCOutliner)TabOutliner.getInhalt("Gid"));
                            if (TabAbf.getS("Art").equals("JCOutliner"))
                            {
                              int iPos=TabOutliner.getPos("Pnl", (JPanel)TabAbf.getInhalt("Pnl"));
                              if (iPos>=0)
                              {
                        	//ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                        	bRefreshEigenschaften = false;
                        	int iSatz2=iSatz;
                        	int iStamm2=iStamm;
                        	TabOutliner.setInhalt(iPos,"Abfrage",new ShowAbfrage(g,TabAbf.getI("Aic"),Abfrage.cstBegriff));
                        	g.setAbfrage(PnlToAic(TabOutliner.getInhalt("Pnl",iPos)),TabAbf.getI("Aic"));
                        	FuelleOutliner((JPanel)TabAbf.getInhalt("Pnl"),0,false,true);
                        	bRefreshEigenschaften = true;
                        	if (TabOutliner.getS(iPos,"Typ").equals("Haupt"))
                        	{
                        	  g.testInfo("iSatz:"+iSatz2+"->"+iSatz+", iStamm:"+iStamm2+"->"+iStamm);
                        	  if (Typ() == Stamm)
                        	    iStamm=checkExistens(iStamm);
                        	  else
                        	    iSatz=checkExistens(iSatz);
                        	  if (iSatz2!=iSatz || iStamm2 != iStamm)
                        	    Refresh();
                        	}
                              }
                              else
                        	Static.printError("Pnl für Abfrage "+TabAbf.getI("Aic")+" nicht gefunden!");
                              //FuelleOutliner((JPanel)TabAbf.getInhalt("Pnl"),TabAbf.getI("Aic"),true,true);
                            }
                            else
                            {
                              g.progInfo("vor Save1");
                              if (((AUTable)TabAbf.getInhalt("Komponente")).Modified())
                                ((AUTable)TabAbf.getInhalt("Komponente")).Save(0,true,isBewBew()?-iSatz:iStamm);
                              ((AUTable)TabAbf.getInhalt("Komponente")).setAbfrage(TabAbf.getI("Aic"));
                              //checkJoker(TabAU.A,(JPanel)TabAbf.getInhalt("Pnl"),g.ColTable,TabAU);
                            }
                          }
                        }
                      });
                      //Pnl3.add(Rbn);
                      TabAbf.setInhalt("Rbn",Rbn);
                      TabAbf.setInhalt("Komponente",Komp);
                    }
                    /*if (bGid)
                    {
                      int iPos=TabOutliner.getPos("Pnl", Pnl2);
                      Vector Vec2=(Vector)TabOutliner.getInhalt("Nord",iPos);
                      Vec2.addElement(Pnl3);
                      //g.progInfo("2:"+TabOutliner.getS("Bezeichnung")+":"+Vec2.size());
                    }
                    else
                      Pnl2.add("North",Pnl3);*/
                    TabAbf.pop();
                  }
                TabOutliner.push();
                for (TabOutliner.moveFirst();!TabOutliner.eof();TabOutliner.moveNext())
                {
                  Vector<JComponent> Vec2=(Vector)TabOutliner.getInhalt("Nord");
                  //g.progInfo("3:"+TabOutliner.getS("Bezeichnung")+":"+Vec2.size());
                  if (Vec2.size()==1)
                    ((JPanel)TabOutliner.getInhalt("Pnl")).add(TabOutliner.getS("Typ").equals("Haupt") ? "West":"North",Vec2.elementAt(0));
                  else if (Vec2.size()>1)
                  {
                    JPanel PnlT=new JPanel(new GridLayout(0,1,0,0));
                    for (int i2=0;i2<Vec2.size();i2++)
                      if (Vec2.elementAt(i2) instanceof JPanel)
                        PnlT.add((JPanel)Vec2.elementAt(i2));
                    ((JPanel)TabOutliner.getInhalt("Pnl")).add("North",PnlT);
                  }
                }
                TabOutliner.pop();
                //TabAbf.showGrid("TabAbf");
                //TabOutliner.showGrid();
                //TabEigenschaft.showGrid("TabEigenschaft");
		Ausgabe("% FuelleAlleOutliner und JCTable");

                /*
		for (Tabellenspeicher TabJCTable= getTab("JCTable");!TabJCTable.out();TabJCTable.moveNext())
		{
			//g.progInfo("AUTable mit:"+iStamm+"/"+iStammtyp);
			AUTable TabAU = new AUTable(this,TabJCTable.getI("Kennung"),iStamm,iStammtyp,g);
			((JPanel)TabJCTable.getInhalt("Komponente")).add(TabAU);
			TabJCTable.setInhalt("Komponente",TabAU);
		}
		Ausgabe("% Fuelle Alle JCTable");*/

		for (Tabellenspeicher TabPlanung= getTab("Planung");!TabPlanung.out();TabPlanung.moveNext())
		{
			//g.progInfo("AUTable mit:"+iStamm+"/"+iStammtyp);
			//long lPDbits=SQL.getLong(g,"select b.bits from begriff b join abfrage a join planung p on a.aic_abfrage=p.aic_abfrage where p.aic_begriff="+TabPlanung.getI("Kennung"));
                        long lClock2 = Static.get_ms();
			Planung TabPl = new Planung(this,TabPlanung.getI("Kennung"),g,TabPlanung.getI("use"),(JPanel)TabPlanung.getInhalt("Komponente"));
			//((JPanel)TabPlanung.getInhalt("Komponente")).add(TabPl);
                        Clock.add(clock,"Planung1",TabPl.sBez,lClock2);
			/*if (!bHaupt && !bEntfernen && SQL.getString(g,"select code.kennung from planung join abfrage on planung.aic_abfrage=abfrage.aic_abfrage join begriff join code where planung.aic_begriff="+TabPlanung.getI("Kennung")).equals("Haupt"))
			{
				g.progInfo("!! Planung ist Haupt !!");
				bHaupt=true;
				TabPl.table.addTraverseCellListener(new jclass.table3.JCTraverseCellListener()
				{
				   public void traverseCell(jclass.table3.JCTraverseCellEvent e)
				   {
					  int iAIC = TabPl.iAicStamm;
					  g.progInfo("-> Planungs-Stamm-Aic="+iAIC);
					  FuelleEigenschaften(iAIC,false);
				   }
				});
			}*/
			TabPlanung.setInhalt("Komponente",TabPl);
		}
		Ausgabe("% Fuelle Alle Planung");

		/*for (Tabellenspeicher TabGrafik= getTab("Grafik");!TabGrafik.out();TabGrafik.moveNext())
		{
			Vector Vec2=(Vector)TabGrafik.getInhalt("Kennung");
			g.testInfo("Grafik mit:"+Vec2+"/"+iStammtyp+"/"+iStamm);
			//Static.printError("EingabeFormular.InitFormular: Diagramm ausgeblendet!");
			Diagramm Dia = new Diagramm(g,(JPanel)TabGrafik.getInhalt("Komponente"),(String)Vec2.elementAt(0),((Integer)Vec2.elementAt(1)).intValue(),iStammtyp,iStamm);
			TabGrafik.setInhalt("Komponente",Dia);
		}
		Ausgabe("% Fuelle Alle Grafik");*/


		/*JPanel PnlAbfrage = getFrei("Combo Abfragen");
		if (PnlAbfrage != null)
		{
			CboAbfrage = new ComboSort(g);
			PnlAbfrage.add(CboAbfrage);


			SQL Qry = new SQL(g);
			for(Qry.open("select abfrage.aic_begriff from abfrage join begriff where exportierbar=1 and "+(Typ()==Stamm?"aic_stammtyp="+iStammtyp:"aic_bewegungstyp="+Bewegungstyp()));!Qry.eof();Qry.moveNext())
			{
				g.TabBegriffe.posInhalt("Aic",Qry.getI("Aic_Begriff"));
				CboAbfrage.addItem(g.TabBegriffe.getS("Bezeichnung"),g.TabBegriffe.getI("Aic"),g.TabBegriffe.getS("Kennung"));
			}
			Qry.free();
		}
		CbxAlle = getCheckbox("Alle");
		*/
		/*
		JPanel PnlPlanung = getFrei("Planung");
		if (PnlPlanung != null)
		{
			PnlPlanung.add(new Planung(VecAic==null?SQL.getVector("select aic_stamm from stammview where aic_stammtyp="+iStammtyp+g.getReadMandanten(false),g):VecAic,g));
		}
		*/
                JPanel PnlZeitraum = getFrei("Zeitraum");
                if (PnlZeitraum != null)
		{
                  //JButton BtnZRplus=g.getButton("ZRplus");
                  //JButton BtnZRminus=g.getButton("ZRminus");
                  //JButton BtnZeitraum=g.getButton("Zeitraum");
                  //JButton BtnRefresh=g.getButton("Refresh");
                  /*BtnRefresh.setPreferredSize(new java.awt.Dimension(40,16));
                  BtnZRplus.setPreferredSize(new java.awt.Dimension(40,8));
                  BtnZeitraum.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent ev) {
                              Zeitraum.get(g).show();
                      }
                  });
                  BtnZRplus.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent ev) {
                              changeZR(true);
                      }
                  });
                  BtnZRminus.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent ev) {
                              changeZR(false);
                      }
                  });
                  BtnRefresh.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent ev) {
                              Refresh();
                      }
                  });*/
                  if (PnlZeitraum.getParent() instanceof JToolBar)
                  {
                    /*PnlZeitraum.getParent().add(g.getButton2("Refresh","R", AL2));
                    PnlZeitraum.getParent().add(g.getButton2("ZRminus","-", AL2));
                    PnlZeitraum.getParent().add(g.getButton2("Zeitraum","Zeitraum", AL));
                    PnlZeitraum.getParent().add(g.getButton2("ZRplus","+", AL2));*/
                  }
                  else
                  {
                    PnlZeitraum.setLayout(new GridLayout());
                      JPanel PnlZR = new JPanel(new GridLayout(2, 1));
                      PnlZR.add(BtnAddg("ZRplus")); //BtnZRplus);
                      PnlZR.add(BtnAddg("Jetzt"));
                      PnlZR.add(BtnAddg("ZRminus")); //BtnZRminus);
                    PnlZeitraum.add(PnlZR);
                    PnlZeitraum.add(BtnAddg("Zeitraum")); //BtnZeitraum);
                    PnlZeitraum.add(BtnAddg("Refresh")); //BtnRefresh);
                  }
                }
		JPanel PnlZeit = getFrei("Uhrzeit");
		if (PnlZeit != null)
		{
			final JLabel Lbl = new JLabel(new java.text.SimpleDateFormat("HH:mm:ss").format(new Date()));
			Lbl.setFont(PnlZeit.getFont());
			PnlZeit.add(Lbl);
			javax.swing.Timer timer;
			timer= new javax.swing.Timer(1000,new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					Lbl.setText(new java.text.SimpleDateFormat("HH:mm:ss").format(new Date()));
				}
			});
			timer.start();
		}
		JPanel PnlSD = getFrei("Sperrdatum");
		if (PnlSD != null)
		{
			LblSD = new JLabel("kein Datum");
			if (PnlSD.getFont() != null)
				LblSD.setFont(PnlSD.getFont());
			PnlSD.add(LblSD);
		}

		Events();
                //showToMuch();
		Ausgabe("% Events");
                TabEigenschaft.moveFirst();
                sSQL_Zeile = SQLEigenschaftsDaten(g.MS()/*||g.MySQL()*/?35:TabEigenschaft.getAnzahlElemente(null));
                if (!TabEigenschaft.eof())
                  sSQL_Zeile2 = SQLEigenschaftsDaten(TabEigenschaft.getAnzahlElemente(null));
                //g.progInfo(sSQL_Zeile);
                //g.progInfo(sSQL_Zeile2);
                Ausgabe("% SQL_Zeile");
                g.printInfo(2,"FuelleEigenschaften InitFormular");
                FuelleEigenschaften(iStamm,true);
                Ausgabe("% FuelleEigenschaften");


			thisFrame.addWindowListener(new WindowListener()
			{
				public void windowClosed(WindowEvent e){}
				public void windowOpened(WindowEvent e){}
				public void windowClosing(WindowEvent e)
				{
                                  if (iBits>-1  && (iBits & cstNotClose)>0)
                                    return;
                                  bEnde=true;
                                  Beenden(0);
                                  ResetAll();
                                  bEnde=false;
				}
				public void windowIconified(WindowEvent e) {}
				public void windowDeiconified(WindowEvent e) {}
				public void windowActivated(WindowEvent e)
				{
          if (bSync)
          {
            g.fixtestError("setSyncMA auf "+g.getStamm());
            g.setSyncStamm();
          }
                                  //g.fixInfo("       ----  windowActivated");
                                  g.progInfo("ZR:"+tsVonOld+"/"+tsBisOld+"->"+g.getVon()+"/"+g.getBis());
                                  boolean bNeuerZR=tsVonOld==null || tsVonOld.getTime()!=g.getVon().getTime() || tsBisOld.getTime()!=g.getBis().getTime();
                                  //g.fixInfo("windowActivated "+bNeuerZR+"/"+getTitle()+"/"+bFRefresh);
                                  if (!Modified() && bFRefresh)
                                  {
                                    //g.fixInfo("bFRefresh/bNeuerZR/Synchron/isRefresh2:"+bFRefresh+"/"+bNeuerZR+"/"+Synchron()+"/"+isRefresh2());
                                    g.fixtestInfo("Refresh wegen nach-Speichern-Modell");
                                    bFRefresh=false;
                                    Refresh();
                                  }
                                  else if (!Modified() && (bNeuerZR || Synchron() || isRefresh2()) && !g.Debug())
                                  {
                                    //g.fixInfo("windowActivated: "+e.getOldState()+"->"+e.getNewState()+", bCalc="+bCalc+", ZR="+g.getVonBis("dd.MM.yyyy",true));
                                    if (Static.bRefreshStop)
                                      Static.bRefreshStop = false;
                                    else if (thisFrame.isShowing() && !bCalc /*&& (!bFirst || iStamm==0 || Typ()==Bewegung)*/)
                                    {
                                      //g.fixtestInfo("1:iSatz="+iSatz+", iStamm="+iStamm+", iSatzOld="+iSatzOld);
                                      iSatzOld = iSatz;
                                      int iNeu = Multi() ? 0:checkSync(true);
                                      //g.fixtestInfo("2:iSatz="+iSatz+", iStamm="+iStamm+", iSatzOld="+iSatzOld);
                                      //if (iFreiTyp==1)
                                      //  iBtn=new DateWOD(g.getVon()).getMonth()-1;
                                      //g.fixInfo("                                     windowActivated: isShowing");

                                      if (iNeu > 0 && TabOutliner.posInhalt("Eig", 0))
                                      {
                                        //g.progInfo("windowActivated2");
                                        bRefreshEigenschaften = false;
                                        if (!FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"), iNeu, false, true))
                                          iNeu = -1;
                                        g.printInfo(2,"FuelleEigenschaften windowActivated");
                                        FuelleEigenschaften(iNeu, false);
                                        bRefreshEigenschaften = true;
                                      }
                                      else if (isRefresh2() && !g.Clock2() || iSatzOld != iSatz || bNeuerZR)
                                      {
                                        g.progInfo("windowActivated3");
                                        Refresh();
                                      }
                                      //g.fixtestInfo("3:iSatz="+iSatz+", iStamm="+iStamm+", iSatzOld="+iSatzOld);
                                    }
                                    //bFirst=false;
                                  }
                                  bFirst=false;
				}
				public void windowDeactivated(WindowEvent e) {}
			});
                        if (g.Def())
                          thisFrame.addMouseListener(new MouseListener()
                          {
                            public void mousePressed(MouseEvent ev)
                            {
                            }
                            public void mouseClicked(MouseEvent ev)
                            {
                              g.progInfo("mouseClicked:"+ev.getClickCount()+"* "+ev.getModifiers());
                              if(ev.getModifiers()==5 /*MouseEvent.BUTTON3_MASK und shift*/ && ev.getClickCount()>0)
                                DefFormular.get(g,getBegriff());
                                //g.progInfo("jetzt");
                            }
                            public void mouseEntered(MouseEvent ev) {}
                            public void mouseExited(MouseEvent ev)  {}
                            public void mouseReleased(MouseEvent ev){}
                          });
                      checkRepaint();
              //ComboSort.showClock(g,"nach Init");
              //thisFrame.validate();
                      //if (SpeichernVerboten())
                    	//  new Message(Message.WARNING_MESSAGE, null, g).showDialog("Speichern_nicht_mit_ALL");
	}

        private JLabel addLabelAbfrage(JPanel Pnl,int iPos,AUTable Komp)
        {
          int iAicMom= Komp==null ? ((ShowAbfrage)TabOutliner.getInhalt("Abfrage",iPos)).iBegriff:Komp.A.iBegriff;
          JLabel Lbl=new JLabel(g.TabBegriffe.getBezeichnungS(iAicMom)+" ");
          Lbl.setFont(g.fontTitel);
          if (TabAbfragenTitel==null)
            TabAbfragenTitel=new Tabellenspeicher(g,new String[] {"Lbl","Komp"});
          TabAbfragenTitel.addInhalt("Lbl",Lbl);
          TabAbfragenTitel.addInhalt("Komp",Komp==null ? Pnl:Komp);
          return Lbl;
        }

        @SuppressWarnings("unchecked")
        private void addToolbar(JPanel Pnl,int iAic,int iPosO,AUTable Komp)
        {
          JToolBar PnlTB=new JToolBar(JToolBar.HORIZONTAL);
          if (Komp != null || !Static.bND)
        	PnlTB.setOpaque(false);
          //int iAic=TabAbf.getI("Aic");
          //Pnl3.setBackground(getColor(bGid));
          PnlTB.setFloatable(false);
          int iAnzGes=0;
          // Abfragen-Auswahl
          int iAnz=getAnzAbf(Pnl);
          if (iAnz>1)
          {
            iAnzGes++;
            JButton Btn=getButton2("Tb_Abfrage", "A" + iAic, AL2,"Button");
            g.setAnzahl(Btn,iAnz);
            PnlTB.add(addLabelAbfrage(Pnl,iPosO,Komp));
            PnlTB.add(Btn);
          }
          if (iAic>0 && Komp==null)
          {
            //g.checkAbfragen();
            //int iPos=g.TabAbfragen.getPos("aic_begriff",iAic);
            // iCalc2=g.TabAbfragen.getI(iPos,"mod_aic_modell");
            if (Modell2(null,iAic)) //(iCalc2>0)
            {
              iAnzGes++;
              //g.fixInfo("addToolbar für " + g.getBegBez(iAic) + " (" + iAic + ")->" + g.TabAbfragen.getI(iPos, "mod_aic_modell"));
              PnlTB.add(getButton2("Calc2", "C"+iAic, AL2,"Button"));
            }
          }
          // Checkbox-Auswahl
          if (TabEinstellung != null && TabEinstellung.posInhalt("Panel",Pnl))
          {
            iAnzGes++;
            JButton Btn=getButton2("TB_Checkboxen", "E" + TabEinstellung.getPos(), AL2,"Button");
            g.setAnzahl(Btn,TabEinstellung.getI("Anzahl"));
            PnlTB.add(Btn);
          }
          if (iAnzGes>0)
            PnlTB.addSeparator(new Dimension(25,1));
          // New, Del, Save
          if (Komp != null && !ReadOnly())
          {
            iAnzGes++;
            if (Komp.bSperre)
            {
              PnlTB.add(Komp.BtnInfo);
              PnlTB.add(Komp.BtnEdit);
              PnlTB.addSeparator(new Dimension(25, 1));
            }
            if (Komp.bCalc)
            {
              PnlTB.add(Komp.BtnAlle);
              PnlTB.add(Komp.BtnCalc);
              PnlTB.addSeparator(new Dimension(25, 1));
            }
            PnlTB.add(Komp.BtnNew);
            PnlTB.add(Komp.BtnDelete);
            PnlTB.add(Komp.BtnSave);
            if (Komp.BtnSEp!=null)
            {
              PnlTB.addSeparator();
              PnlTB.add(Komp.BtnSEp);
              PnlTB.add(Komp.BtnSEm);
              PnlTB.addSeparator();
              PnlTB.add(Komp.BtnSEl);
              PnlTB.add(Komp.BtnSEe);
              PnlTB.add(Komp.BtnSEr);
            }
            PnlTB.addSeparator(new Dimension(25,1));
          }
          PnlTB.add(getButton2("Tb_Suche", "S" + iAic, AL2,"Button"));
          if (getVec("Frame",Pnl) != null)
          {
            //PnlTB.addSeparator(new Dimension(4, 4));
            iAnz=getVec("Frame",Pnl).size();
            //g.progInfo("Tb_Formular:"+iAnz);
            if (iAnz>0)
            {
              JButton Btn=getButton2("Tb_Formular", "F" + iAic, AL2,"Button");
              //if (Btn != null)
                g.setAnzahl(Btn,iAnz);
              //Btn.setMargin(g.inset0);
              //Btn.setText("<html><sup>"+iAnz+"</sup></html>");
              //Btn.setOpaque(false);
              iAnzGes++;
              PnlTB.add(Btn);
            }
          }
          if (getVec("Modell",Pnl) != null)
          {
            iAnz = getVec("Modell", Pnl).size();
            if (iAnz>0)
            {
              JButton Btn = g.getButton2("Modelle",null, "M" + iAic, AL2, "Button");
              g.setAnzahl(Btn, iAnz);
              Btn.setEnabled(!ReadOnly());
              iAnzGes++;
              PnlTB.add(Btn);
            }
          }
          if (getVec("Druck",Pnl) != null)
          {
            iAnz = getVec("Druck", Pnl).size();
            if (iAnz>0)
            {
              JButton Btn = g.getButton2("Druck",null, "D" + iAic, AL2, "Button");
              g.setAnzahl(Btn, iAnz);
              iAnzGes++;
              PnlTB.add(Btn);
            }
          }

          //g.fixInfo(g.getBegBez(iAic)+":"+getVec("Web",Pnl));
          /*if (getVec("Druck",Pnl) != null && getVec("Druck",Pnl).size()>0)
          {
            //PnlTB.addSeparator(new Dimension(4, 4));
            PnlTB.add(g.getButton2("Druck", "D" + iAic, AL2));
          }*/
          //if (getVec("Button",Pnl) != null && getVec("Button",Pnl).size()>0)
          for (TabSub.moveFirst();!TabSub.out();TabSub.moveNext())
          {
            /*PnlTB.addSeparator(new Dimension(4, 4));
            PnlTB.add(g.getButton2("help", "H" + iAic, AL2));
            PnlTB.addSeparator(new Dimension(8, 8));*/
            String sArt=TabSub.getS("Art");
            //if (bHaupt)
            //{
              //Vector Vec=getVec("Button",Pnl);
              //for (int i=0;i<Vec.size();i++)
              if (Pnl.equals(TabSub.getInhalt("Pnl")) && (/*sArt.equals("Modell") || sArt.equals("Druck") ||*/ sArt.equals("Button") || sArt.equals("Web")))
              {
                int iB=TabSub.getI("Aic");
                int iPos = g.TabBegriffe.getPos("Aic", iB);// Sort.geti(Vec,i));
                if (iPos<0)
                  Static.printError("Begriff "+iB+" nicht gefunden");
                else if (sArt.equals("Web"))
                  PnlTB.add(getButton2(g.TabBegriffe.getS(iPos,"Kennung"),"H"+g.TabBegriffe.getS(iPos,"URL"), AL,"Web"));
                else if (g.BerechtigungS(iPos))
                {
                  //int iURL = iPos<0 ? 0:g.TabBegriffe.getI(iPos,"URL");
                  String sKennung=g.TabBegriffe.getS(iPos,"Kennung");
                  PnlTB.addSeparator(new Dimension(4, 4));
                  iAnzGes++;
                  if (sArt.equals("Button") || sArt.equals("Web"))
                    PnlTB.add(getButton2(sKennung, /*iURL==0 ? */sKennung/*:"H"+iURL*/, AL,"Button"));
                  //else if (sArt.equals("Modell"))
                  //  PnlTB.add(g.getButton2(sKennung, "_M"+iB+","+iAic,AL,"Modell"));
                  else if (sArt.equals("Druck"))
                    PnlTB.add(g.getButton2(sKennung,null,"_D"+iB+","+iAic,AL,"Druck"));
                }
              }
            //}
          }
         if (iAnzGes>0)
          if (Komp==null)
          {
              int iPos = TabOutliner.getPos("Pnl", Pnl);
              Vector<JComponent> Vec2 = (Vector)TabOutliner.getInhalt("Nord", iPos);
              Vec2.addElement(PnlTB);    
              PnlTB.setBackground(Static.ColEF);
          }
          else
          {
            PnlTB.add(Komp.BtnDruck);
            Pnl.setBackground(g.ColTable);
            PnlTB.setBackground(g.ColTable);
            Pnl.add("North", PnlTB);
          }
         g.ColBack(PnlTB);
        }
        
        private void checkRepaint()
        {
          if (isRepaint())
          {
            new Thread(new Runnable()
                {
                  public void run() {
                    try {
                      //Static.sleep(200);
                      java.awt.Dimension dim=thisFrame.getSize();
                      thisFrame.setSize((int)dim.getWidth()-1,(int)dim.getHeight()-1);
                      Static.sleep(200);
                      thisFrame.setSize(dim);
                    }
                    catch(Exception e) {}
                }
              }).start();
          }
        }

        private int checkSync(boolean b)
        {
          int iNeu=0;
          if (Synchron())
          {
        	  /*if (bNeuCheck)
        		  g.fixtestError("kein CheckSync, da NeuCheck");
        	  else*/ if(Typ() == Stamm && b)// && !bNeuCheck)
            {
//              int iPos=g.TabStammtypen.getPos("Aic", iStammtyp);
//              int iS=iPos>=0 ? g.TabStammtypen.getI(iPos,"Stamm"):0;
            	int iS=g.getSyncStamm(iStammtyp,iRolle);
              if(iS != 0) {
                g.defInfo("Activated: Stt=" + iStammtyp + ", Stamm=" + iS);
                if(iSatz != iS) {
                  iSatz = iS;
                  iStamm = iSatz;
                  iNeu = iSatz;
                }
              }
            }
            else if(Typ() == Bewegung)
            {
//              int iPos=g.TabErfassungstypen.getPos("Aic", Bewegungstyp());
//              int iP=iPos>=0 ? g.TabErfassungstypen.getI(iPos,"Pool"):0;
            	int iP=g.getSyncBew(Bewegungstyp());
              if(iP != 0 && iSatz != iP)
              {
                g.defInfo("Activated "+sFormularBezeichnung+": Bew=" + Bewegungstyp() + ", Pool=" + iP);
                iSatz = iP;
                iNeu = iSatz;
              }
            }
          }
          return iNeu;
        }

	/*private void RefreshWochentage(DateWOD dtVon,JLabel LblDatum)
	{
		g.setVon(dtVon.toTimestamp());
		dtVon.tomorrow();
		g.setBis(dtVon.toTimestamp());
		LblDatum.setText(new java.text.SimpleDateFormat("dd.MM.yyyy").format(g.getVon()));
		for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
		{

			//g.testInfo("-Refresh:"+TabJCTable.getS("Kennung")+"/"+iStamm);
                    if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
			((AUTable)TabAbf.getInhalt("Komponente")).Refresh(isBewBew()?-iSatz:iStamm);
		}
                //g.progInfo("RefreshWochentage");
		Titelzusatz("");
	}*/

	/*private void FuelleAlleOutliner()
	{
		Tabellenspeicher TabJCOutliner= getTab("JCOutliner");
		TabJCOutliner.moveFirst();
		while (!TabJCOutliner.eof())
		{
			FuelleOutliner((JPanel)TabJCOutliner.getInhalt("Komponente"),TabJCOutliner.getI("Kennung"),true);
			TabJCOutliner.moveNext();
		}
		//TabJCOutliner.showGrid();
	}*/

	/*private String getAbfrageTitel(String sBegriff,String sTyp)
	{
		String s=sBegriff+(g.Def()?" ("+(sTyp.equals("Haupt")?'H':sTyp.equals("Mehrfach")?'M':sTyp.equals("Anzeige")?'A':
				sTyp.equals("Gruppe")?'G':sTyp.equals("Auswahl")?'W':sTyp.equals("Entfernen")?'L':'?')+")":"");
		return sTyp.equals("Haupt")||sTyp.equals("Entfernen") ? "* "+s+" *":s;
	}*/

        private void suchen(Point P)
	{
        if (DlgSuche !=null)
        	DlgSuche.dispose();
		DlgSuche=new JDialog((JFrame)thisFrame,g.getBegriffS("Dialog","Suche2"));
		P.translate(-100,-70);
                if(P.y<0)
                  P.y=0;
        DlgSuche.setLocation(P);

		JPanel Pnl = new JPanel(new BorderLayout(2,2));
		//final AUCheckBox CbxStart=g.getCheckbox("Volltextsuche",(iSuchBits&1)==0);
		//final AUCheckBox CbxSucheCase=g.getCheckbox("Case",(iSuchBits&4)>0);
		final ComboSort CboSuche=new ComboSort(g,ComboSort.kein);
                CboSuche.setFont(g.fontBezeichnung);
                ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                bBeginn=Abf.bBeginn;
                //Tabellenspeicher TabSpalten=Abf.getSpalten(); weg seit 17.4.
		/*if (TabSpalten == null || TabSpalten.isEmpty())
		{
			CboSuche.addItem(g.getBegriff("Show","Bezeichnung"),1,"");
		}
		else*/
		{
			Vector VecGidSpalten = Abf.getBezeichnung();
			for(int i=0;i<VecGidSpalten.size();i++)
				CboSuche.addItem(""+VecGidSpalten.elementAt(i),i+1,"");
		}
		//CboSuche.setSelectedAIC(iSpalte);
		JPanel Pnl2 = new JPanel(new BorderLayout());
			Pnl2.add("West",CboSuche/*new JLabel("Suchtext:")*/);
			final Text Edt=new Text(sSuchtext,20);
                        Edt.setFont(g.fontStandard);
			Pnl2.add("Center",Edt);
                JScrollPane Scroll=new JScrollPane(Pnl2);
                Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
		Pnl.add("North",Scroll);
		//Pnl2 = new JPanel(new FlowLayout());
		//Pnl2.add(CbxStart);
		//Pnl2.add(CbxSucheAb);
		//Pnl2.add(CbxSucheCase);
		//Pnl.add("Center",Pnl2);


		DlgSuche.getContentPane().add("North",Pnl);

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
				sSuchtext=Edt.getText();
				//bStartedMit=CbxStart.isSelected();
				bAb=false;
				//iSuchBits=(CbxStart.isSelected()?0:1)+2+(CbxSucheCase.isSelected()?4:0);
                                TabOutliner.push();
				//iSpalte=CboSuche.getSelectedAIC();
                                if (!TabOutliner.isNull("Gid"))
                                {
                                  JCOutlinerFolderNode Nod = (JCOutlinerFolderNode)((JCOutliner)TabOutliner.getInhalt("Gid")).getRootNode();
                                  if (suche(Nod, sSuchtext, CboSuche.getSelectedAIC() - 1))
                                  {
                                    bAb = true;
                                    suche(Nod, sSuchtext, CboSuche.getSelectedAIC() - 1);
                                  }
                                }
                                TabOutliner.pop();
				//Dlg.dispose();
			}
		});
		JButton BtnDlgBeenden=g.getButton("Beenden");
		BtnDlgBeenden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				DlgSuche.dispose();
			}
		});
		//Pnl.add(BtnDlgSuche);
                Pnl.add(BtnDlgWeiter);
                //Pnl.add(BtnDlgHelp);
		Pnl.add(BtnDlgBeenden);
		DlgSuche.getContentPane().add("South",Pnl);
		DlgSuche.pack();
		DlgSuche.setVisible(true);
        Edt.requestFocus();
	}

        private boolean suche(JCOutlinerFolderNode Nod,String sText,int iPos)
	{
          JCOutliner Gid=(JCOutliner)TabOutliner.getInhalt("Gid");
          int iStamm_Mom=0;
          if (Gid.getSelectedNode().getLevel()<2 && Gid.getSelectedNode().getUserData() instanceof Integer)
            iStamm_Mom=((Integer)Gid.getSelectedNode().getUserData()).intValue();
		boolean b=true;
		Vector Vec = Nod.getChildren();
		if (Vec != null)
		{
			for(int i=0;i<Vec.size() && b;i++)
			{
				JCOutlinerNode NodNeu=(JCOutlinerNode)Vec.elementAt(i);
				//if (NodNeu.getLevel()<4)
				//  g.progInfo(NodNeu.getLevel()+"_"+i+":"+NodNeu.getLabel()+"/"+NodNeu.getUserData());
				//if (((Integer)NodNeu.getUserData()).intValue()>0)
                                if (NodNeu instanceof JCOutlinerFolderNode)
                                  if (!suche((JCOutlinerFolderNode)NodNeu,sText,iPos))
                                  {
                                    ((JCOutlinerFolderNode)NodNeu).setState(BWTEnum.FOLDER_OPEN_ALL);
                                    return false;
                                  }
                                if(((Vector)NodNeu.getLabel()).size()>iPos)
				{
					if (bAb)
					{
						String sMom=Sort.gets(((Vector)NodNeu.getLabel()).elementAt(iPos));
						//if((iSuchBits&4)==0)
						//{
							sMom=sMom.toUpperCase();
							sText=sText.toUpperCase();
						//}
						if(bBeginn ? sMom.startsWith(sText):sMom.indexOf(sText)>-1)
						{
							bkHE=true;
							Static.makeVisible(Gid,NodNeu);
							g.fixtestInfo("gefunden:"+NodNeu.getLabel());
                            //Gid.folderChanged(NodNeu.getParent());
                            Hauptoutliner_Event((AUOutliner)Gid,true);
                            bkHE=false;
							return false;
						}
					}
					else if(iStamm_Mom>0 && iStamm_Mom == ((Integer)NodNeu.getUserData()).intValue())
						bAb=true;
				}
				//else
				//	b=suche(NodNeu,sText,iPos);
				if (!b)
                                {
                                  //NodNeu.setState(BWTEnum.FOLDER_OPEN_ALL);
                                  Gid.folderChanged(NodNeu.getParent());
                                }
			}

		}//iSTT_Mom
		return b;

	}

        private void showPopup3(MouseEvent ev) // Popup von Modell-Knopf
        {
          //long lClock = Static.get_ms();
          final JPopupMenu popup3 = new JPopupMenu();
          g.addMenuItem("DefModell",popup3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              DefModell.get(g, TabFormular.getI("Aic")).show();
            }});
          g.addMenuItem("Modell neu",popup3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              Modellberechnung((JButton)TabFormular.getInhalt("Button"),false,true);
            }});
          g.addMenuItem("Debug Modell",popup3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              Modellberechnung((JButton)TabFormular.getInhalt("Button"),true,false);
            }});
          g.addMenuItem("Debug Modell neu",popup3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              Modellberechnung((JButton)TabFormular.getInhalt("Button"),true,true);
            }});
          g.addMenuItem("set Tooltip",popup3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              ToolTipEdit((JComponent)TabFormular.getInhalt("Button"),"B"+TabFormular.getI("Aic"));
            }});
          popup3.show((Component)ev.getSource(), ev.getX(), ev.getY());
          //g.clockInfo2("Popup",lClock);

        }

        private void showPopup(MouseEvent ev) // Popup von Outliner
        {
          long lClock = Static.get_ms();
          //if (popup == null)
          //{
            final JPopupMenu popup = new JPopupMenu();
            popup.setLabel("Eingabeformular");
            if (ev==null)
              g.addMenuItem("Close",popup).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev)
              {
                popup.setVisible(false);
              }
            });
            ShowAbfrage A = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
            //g.fixInfo("showPopup: A="+A.sDefBez);
            Vector VecFom=getVec("Frame",TabOutliner.getInhalt("Pnl"));
            if (TabOutliner.getS("Typ").equals("Mehrfach") && iStamm>0 && (VecFom==null || VecFom.size()==0))
            {
                JMenu MnuSub = g.addMenu("Tb_Formular",popup);
                //popup.add(MnuSub);
                popup.addSeparator();
                int iStt2=A.iStt;
                TabFPop = new Tabellenspeicher(g,"select aic_begriff,bits,null popup from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Frame")+
                " and aic_stammtyp=" + iStt2 +" order by defbezeichnung", true);
                //TabFPop.showGrid("Mehrfach");
                for (TabFPop.moveFirst(); !TabFPop.eof(); TabFPop.moveNext())
                  addMnuFormular(MnuSub,4,-1);
            }
            Vector VecMod=getVec("Modell",TabOutliner.getInhalt("Pnl"));
            if (VecMod != null && VecMod.size()>0 && !ReadOnly())
            {
            	JMenu MnuSub = g.addMenu("Modelle",popup);
            	//popup.add(MnuSub);
            	for (int i=0;i<VecMod.size();i++)
            		addMnuModell(MnuSub,Sort.geti(VecMod,i));
            }
            if (VecFom != null && VecFom.size()>0)
            {
            	JMenu MnuSub = g.addMenu("Tb_Formular",popup);
            	//popup.add(MnuSub);
            	for (int i=0;i<VecFom.size();i++)
                      addMnuSubFormular(MnuSub,Sort.geti(VecFom,i));
            }
            Vector VecDruck=getVec("Druck",TabOutliner.getInhalt("Pnl"));
            if (VecDruck != null && VecDruck.size()>0)
            {
                JMenu MnuSub = g.addMenu("Druck",popup);
                //popup.add(MnuSub);
                for (int i=0;i<VecDruck.size();i++)
                  addMnuDruck(MnuSub,Sort.geti(VecDruck,i));
            }
            if (!TabOutliner.getS("Typ").equals("Haupt") && !TabOutliner.getS("Typ").equals("Mehrfach"))
             g.addCbxItem("Alle",popup,A.bAlle).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                //g.fixInfo("Alle angeklickt");
            	JCOutliner Out=(JCOutliner)TabOutliner.getInhalt("Gid");
            	ShowAbfrage A = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                A.bAlle = !A.bAlle;
            	if (A.bAlle)
            	{
            	  java.awt.Event ev2=new java.awt.Event(null,0,null);
                  JCOutlinerNode Nod=Out.getSelectedNode();
                  ev2.modifiers=java.awt.Event.CTRL_MASK;
                  Out.selectNode(Nod,ev2);
                  //Out.selectNode(Out.getRootNode(),ev2);
                  Static.makeVisible(Out,Nod);
                  //g.fixtestError("makeVisible1:"+iStamm+"/"+Nod);
                  ev2.modifiers=java.awt.Event.SHIFT_MASK;
                  for (JCOutlinerNode NodeAlle = Out.getNextNode(Out.getRootNode()); NodeAlle != null; NodeAlle = Out.getNextNode(NodeAlle))
                    Out.selectNode(NodeAlle,ev2);
                  Out.selectNode(Nod,ev2);
          	}
          	else
          	{
            	  java.awt.Event ev2=new java.awt.Event(null,0,null);
                  JCOutlinerNode Nod=Out.getSelectedNode();
                  Out.selectNode(Out.getRootNode(),ev2);
                  Static.makeVisible(Out,Nod);
                  //g.fixtestError("makeVisible2:"+iStamm+"/"+Nod);
                  //ev2.modifiers=java.awt.Event.CTRL_MASK;
                  Out.selectNode(Nod,ev2);
                  Static.makeVisible(Out,Nod);
            	}
                int iPosOut=TabOutliner.getPos("Eig",0);
                if (iPosOut>=0)
                  VecSelect = g.getAics((JCOutliner)TabOutliner.getInhalt("Gid",iPosOut));
              }
            });
            /*g.addMenuItem("Zeitraum",popup).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                //getSelected((JCOutliner)TabOutliner.getInhalt("Gid"),true);
        	Zeitraum.get(g).show();
              }});*/
            popup.addSeparator();
            g.addMenuItem("Refresh",popup,"RefreshO",AL);
            if (g.Def() && A.iModell>0)
              g.addMenuItem("Debug-Refresh",popup,"RefreshD",AL);
//            if (g.Def() && A.iModell2>0)
//                g.addMenuItem("Debug2-Refresh",popup,"RefreshD2",AL);
            /*if (A.iBew==0)
             g.addMenuItem("FullRefresh",popup).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                ShowAbfrage.refreshBerechtigung(g);
                Refresh();
              }
            });*/
            boolean bHaupt=TabOutliner.getS("Typ").equals("Haupt");
            if (g.Def()) // && bHaupt) // seit 5.7.2023 überall
            {
            	g.addMenuItem("FullRefresh",popup).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                      ShowAbfrage.refreshBerechtigung(g);
                      thisJFrame().dispose();
                      int iPos=g.TabFormulare.getPos("Aic",iFormular);
                      if (iPos>=0)
                    	  g.TabFormulare.clearInhalt(iPos);
                      HoleFormular(g,iFormular,VecAic,iStamm,false);
                      //Refresh();
                    }
                  });
            }
            if (!bHaupt && A.iBew>0)
             g.addMenuItem("Refresh ohne ZR",popup).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                bRefreshEigenschaften = false;
                java.sql.Timestamp tsVon=g.getVon();
                java.sql.Timestamp tsBis=g.getBis();
                g.setVonBis(null,null);
                //? g.setAbfrage(getBegriff(),TabOutliner.getI("Gruppe"),((Abfrage)TabOutliner.getInhalt("Abfrage")).iBegriff);
                FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
                //SummeBilden((AUOutliner)TabOutliner.getInhalt("Gid"));
                g.setVonBis(tsVon,tsBis);
                bRefreshEigenschaften = true;
              }
            });
            if (g.Def() && bHaupt)
            {
              if (/*Static.bDefBezeichnung ||*/ (A.iBits&Abfrage.cstKeinSave)==0)
        	g.addMenuItem("Speichern",popup,"Speichern",AL);
              if (/*Static.bDefBezeichnung ||*/ (A.iBits&Abfrage.cstKeinNeu)==0)
        	g.addMenuItem("Neu",popup,"Neu",AL);
            }
            g.addMenuItem("checkStamm",popup,"checkStamm",AL);
            if (g.Def() && (A.iBits&Abfrage.cstKeinDel)==0)
            {
      		g.addMenuItem("Loeschen",popup,"LoeschenO",AL);
      		if (A.iBew>0)
                  g.addMenuItem("Undelete",popup,"Undelete",AL);
            }

            if (A.iBew==0 && A.iStt>0 && bHaupt)
            {
              int iPosS=g.TabStammtypen.getPos("Aic",A.iStt);
              if (Static.bDefBezeichnung)//iPosS>=0 && (g.TabStammtypen.getI(iPosS,"bits")&Global.cstCopy)>0)
                g.addMenuItem("Kopie",popup).addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    KopiereOut((JCOutliner)TabOutliner.getInhalt("Gid"),TabOutliner.getS("Typ").equals("Haupt"),((ShowAbfrage)TabOutliner.getInhalt("Abfrage")).iBegriff);
                  }
                });
              if (iPosS>=0 && (g.TabStammtypen.getI(iPosS,"bits")&Global.cstHA)>0)
              {
                popup.addSeparator();
                g.addMenuItem("HA1",popup).addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    HA(false,false);
                  }
                });
                g.addMenuItem("HA2",popup).addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    HA(true,false);
                  }
                });
              }
              if (iPosS>=0 && (g.TabStammtypen.getI(iPosS,"bits")&Global.cstBFilter)>0)
              {
                popup.addSeparator();
                g.addMenuItem("BFilter",popup).addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    HA(false,true);
                  }
                });
              }
              if (VecBezeichnung.size()>0)
              {
                g.addMenuItem("CalcBez",popup).addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    CalcAllBez();
                  }
                });
              }
            }
            popup.addSeparator();
            /*if (!TabOutliner.getS("Typ").equals("Haupt"))
            {
              //if(g.isAbfrageAktiv(getBegriff(), TabOutliner.getI("Gruppe")))
              if(g.isAbfrageAktiv(PnlToAic(TabOutliner.getInhalt("Pnl"))))
                g.addMenuItem("ausblenden", popup).addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    bRefreshEigenschaften = false;
                    //g.setAbfrageWeg(getBegriff(), TabOutliner.getI("Gruppe"), 1);
                    g.AbfrageAusblenden(PnlToAic(TabOutliner.getInhalt("Pnl")),((Abfrage)TabOutliner.getInhalt("Abfrage")).iBegriff,true);
                    FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"), 0, false, true);
                    bRefreshEigenschaften = true;
                  }
                });
              //if(!g.isAbfrageAktiv(getBegriff(), TabOutliner.getI("Gruppe")))
              //if(!g.isAbfrageAktiv(PnlToAic(TabOutliner.getInhalt("Pnl"))));
              else
                g.addMenuItem("einblenden", popup).addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ev) {
                    bRefreshEigenschaften = false;
                    //g.setAbfrageWeg(getBegriff(), TabOutliner.getI("Gruppe"), 0);
                    g.AbfrageAusblenden(PnlToAic(TabOutliner.getInhalt("Pnl")),((Abfrage)TabOutliner.getInhalt("Abfrage")).iBegriff,false);
                    FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"), 0, false, true);
                    bRefreshEigenschaften = true;
                  }
                });
            }*/
            if (ev==null || !(ev.getSource() instanceof JLabel))
            {
              g.addMenuItem("Tb_Suche",popup).addMouseListener(new MouseListener()
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
            }
            if (TabOutliner.getInhalt("Gid") instanceof JCOutliner)
            {
              JCOutlinerNode Nod = ((JCOutliner)TabOutliner.getInhalt("Gid")).getSelectedNode();
              if(Nod != null && Nod.getLabel() instanceof Vector) {
                final String s = getEMail((Vector)Nod.getLabel());
                if(s != null) {
                  g.addMenuItem("E-Mail", popup).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      g.progInfo("E-mail="+s);
                      Static.OpenURL("mailto:" + s);
                    }
                  });
                }
                final String s2 = getBild((Vector)Nod.getLabel());
                if(s2 != null) {
                  g.addMenuItem("Bild", popup).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      g.progInfo("URL="+s2);
                      Static.OpenURL(s2);
                    }
                  });
                }
                final String s3 = getMemo((Vector)Nod.getLabel());
                if(s3 != null) {
                  g.addMenuItem("Memo", popup).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      g.progInfo("Memo:"+s3);
                      MemoF memF = new MemoF((JFrame)thisFrame, Sort.gets(sBezeichnung), g, false,false,0);
                      memF.Txt.setText(s3);
                      memF.show2();
                    }
                  });
                }
                final String s4 = getGPS((Vector)Nod.getLabel());
                if(s4 != null) {
                  g.addMenuItem("GPS", popup).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      Static.OpenURL(s);
                    }
                  });
                }             
              }
            }
            if (TabOutliner.getInhalt("Gid") instanceof AUOutliner && A.getSpalten().getVecSpalte("Kennung2").contains(""+g.iTimerModell))
            {
              g.addMenuItem("Calc", popup).addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                  JCOutlinerNode Nod = ((JCOutliner)TabOutliner.getInhalt("Gid")).getSelectedNode();
                  if(Nod != null && Nod.getLevel()>0)
                    TCalc.TimerBerechnung(g, self,Tabellenspeicher.geti(Nod.getUserData()));
                }
              });
            }
            if (TabOutliner.getInhalt("Gid") instanceof AUOutliner && Modell2(A,0))
            {
              g.addMenuItem("Calc2", popup, null, AL);
            }
            if ((A.iBitsO&Abfrage.cstTTO)>0)
              g.addMenuItem("open down",popup,null,AL);
            if (A.iBew==0 && (A.iBitsO&Abfrage.cstKeinAustritt)==0 && A.iStt==g.iSttANR && iRolle>0)
            {

              boolean b=g.isAbfrageP(PnlToAic(TabOutliner.getInhalt("Pnl")),Global.VAUS,(A.iBits&Abfrage.cstKeinAustritt)>0);
              g.addCbxItem("Austritt",popup,b).addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        	  ShowAbfrage A = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
        	  if ((A.iBits&Abfrage.cstKeinAustritt)>0)
        	    A.iBits-=Abfrage.cstKeinAustritt;
        	  else
        	    A.iBits+=Abfrage.cstKeinAustritt;
        	  g.AbfrageP(PnlToAic(TabOutliner.getInhalt("Pnl")),((Abfrage)TabOutliner.getInhalt("Abfrage")).iBegriff,(A.iBits&Abfrage.cstKeinAustritt)>0,Global.VAUS);
        	}
              });
            }
            /*addMenuItem("ZRplus",popup).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                changeZR(true);
              }
            });
            addMenuItem("ZRminus",popup).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                changeZR(false);
              }
            });*/

            //ShowAbfrage Abf = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
            if(g.Geloeschte() && A.iBew>0 && A.iModell==0)
            {
                  /*JCheckBoxMenuItem Mnu = new JCheckBoxMenuItem(g.getBegriffS("Checkbox", "tab_sdel"), A.bEntfernte);
                  Mnu.setFont(g.fontStandard);
                  popup.add(Mnu);*/
                  g.addCbxItem("tab_sdel",popup,A.bEntfernte).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                      ShowAbfrage A = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                      A.bEntfernte = ((JCheckBoxMenuItem)e.getSource()).isSelected();
                      A.SQL_String();
                      bRefreshEigenschaften = false;
                      //g.setAbfrage(getBegriff(),TabOutliner.getI("Gruppe"),((Abfrage)TabOutliner.getInhalt("Abfrage")).iBegriff);
                      FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"), 0, false, true);
                      //SummeBilden((AUOutliner)TabOutliner.getInhalt("Gid"));
                      bRefreshEigenschaften = true;
                    }
                  });
            }
            g.addCbxItem("Beginnsuche",popup,A.bBeginn).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                      ShowAbfrage A = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                      A.bBeginn = ((JCheckBoxMenuItem)e.getSource()).isSelected();
                    }
                  });
            popup.addSeparator();
            if ((ev==null || !(ev.getSource() instanceof JLabel)) && g.History())
            {
              g.addMenuItem("History",popup).addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                  Static.bRefreshStop=true;
                  ShowAbfrage Abf = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                  JCOutlinerNode Nod = ((JCOutliner)TabOutliner.getInhalt("Gid")).getSelectedNode();
                  int iPool = Nod == null || Nod.getLevel() == 0 || Nod.getUserData()==null ? 0 : ((Integer)Nod.getUserData()).intValue();
                  if (iPool > 0)
                    if (Abf.iBew > 0)
                      Abf.showHistory2(self, iPool,Abf.iModell2,(Abf.iBits & Abfrage.cstStempelimport)>0);
                    else
                      ShowAbfrage.showHistory3(g, self, iPool, null);
                }
              });
              g.addMenuItem("History_ZR",popup).addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                	int iPMin=SQL.getInteger(g,"select min(aic_protokoll) from protokoll where timestamp>="+g.getVonSql());
                	if (iPMin==0)
                    	return;
                	int iPMax=SQL.getInteger(g,"select max(aic_protokoll) from protokoll where timestamp<="+g.getBisSql());
                	Vector<Integer> Vec = g.getAllAics((JCOutliner)TabOutliner.getInhalt("Gid"));
                	g.fixtestInfo("History von "+iPMin+" bis "+iPMax+" für "+Vec);
                	Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from (select s.aic_stamm,s.aic_protokoll,(select bezeichnung from stammview2 where aic_stamm=s.aic_stamm and aic_rolle is null) Stamm"+
                	g.AU_Bezeichnung1("Eigenschaft", "s")+" Eigenschaft,s.aic_eigenschaft,s.aic_stammpool,s.gultig_von,p.timestamp,(select timestamp from protokoll where aic_protokoll=s.pro2_aic_protokoll) entfernt"+
                			" from stammpool s join protokoll p on s.aic_protokoll=p.aic_protokoll"+
                			" where s.aic_stamm "+Static.SQL_in(Vec)+" and p.aic_protokoll>="+iPMin+" and p.aic_protokoll<="+iPMax+") x order by Stamm,Eigenschaft,aic_stammpool desc",true);
                	String[] sAry=new String[] {"Stamm","Eigenschaft","Datentyp","zuvor","Daten","gueltig_ab","Change","entfernt"};
                	Tabellenspeicher Tab2=new Tabellenspeicher(g,sAry);
                	g.setTitel(Tab2, sAry);
                	if (iRolle==g.iRolMA)
                		Tab2.setTitel("Stamm", g.getShow("Mitarbeiter"));
                	for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                	{
                		String sDt=g.TabEigenschaften.getS_AIC("Datentyp", Tab.getI("aic_eigenschaft"));
                		String s=sDt.equals("Datum") /*|| sDt.equals("Mehrfach")*/ ? null:
                				SQL.getString(g,"select "+ShowAbfrage.PoolDaten(g,sDt)+" from stammpool p where aic_stammpool=?", Tab.getS("aic_stammpool"));
                		Tab2.addInhalt("Stamm", Tab.getS("Stamm"));
                		Tab2.addInhalt("Eigenschaft", Tab.getS("Eigenschaft"));
                		Tab2.addInhalt("Datentyp", sDt);
                		// TODO History_ZR: eventuell weitere berücksichtigen
                		Tab2.addInhalt("Daten", sDt.equals("Datum") ? new Zeit(Tab.getTimestamp("gultig_von"),"dd.MM.yyyy"):sDt.equals("Boolean") ? Static.JaNein(s.equals("1")):sDt.equals("Text") ? g.getHtmlPart(s,false).trim():s);
                		String s2=//sDt.equals("Mehrfach") ? null:
            				SQL.getString(g,"select "+ShowAbfrage.PoolDaten(g,sDt)+" from stammpool p where aic_stamm="+Tab.getI("aic_stamm")+" and aic_eigenschaft="+Tab.getI("aic_eigenschaft")+" and pro2_aic_protokoll="+Tab.getI("aic_protokoll"));
                		Tab2.addInhalt("zuvor", /*sDt.equals("Datum") ? new Zeit(Tab.getTimestamp("gultig_von"),"dd.MM.yyyy"):*/sDt.equals("Boolean") ? Static.JaNein(s2.equals("1")):sDt.equals("Text") ? g.getHtmlPart(s2,false).trim():s2);
                		Tab2.addInhalt("gueltig_ab", new Zeit(Tab.getTimestamp("gultig_von"),"dd.MM.yyyy"));
                		Tab2.addInhalt("Change", new Zeit(Tab.getTimestamp("timestamp"),"dd.MM.yyyy"));
                		Tab2.addInhalt("entfernt", new Zeit(Tab.getTimestamp("entfernt"),"dd.MM.yyyy"));
                	}
                	Tab2.showGrid("History "+g.getVonBis("dd.MM.yyyy",true));
                }
              });
            }
            //if (!TabOutliner.getS("Typ").equals("Haupt"))
             g.addMenuItem("Abfrage easy",popup).addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent ev)
                {
                  ShowAbfrage Abf = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                  DefAbfrage2 DefAbf=DefAbfrage2.get(g,DefAbfrage2.OUTLINER,Abf,(JFrame)thisFrame);
                  if (DefAbf.bOk)
                  {
                    Abf = DefAbf.A;
                    TabOutliner.setInhalt("Abfrage",Abf);
                    Abf.SQL_String();
                    refreshOutliner(false);
                  }
                }
              });
            if (g.Abfrage())
            {
              if (g.Def() || (A.iBits & Abfrage.cstNoChange) == 0)
	              g.addMenuItem("Abfrage",popup).addActionListener(new ActionListener()
	              {
	                public void actionPerformed(ActionEvent ev)
	                {
	                  ShowAbfrage Abf = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
//	                  if (g.Def() || (Abf.iBits & Abfrage.cstNoChange) == 0)
	                    DefAbfrage.get(g, Abf, Abf.iBew == 0 ? Abf.iStt : -Abf.iBew).show();
	                }
	              });

              if (g.Def())
                g.addMenuItem("Abfrage2", popup).addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    ShowAbfrage Abf = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                    if (g.Def() || (Abf.iBits & Abfrage.cstNoChange) == 0)
                      DefAbfrage.get(g, Abf, Abf.iBew == 0 ? Abf.iStt : -Abf.iBew, null, false, 1).show();
                  }
                });

              g.addMenuItem("Init",popup).addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent ev)
                {
                    bRefreshEigenschaften = false;
                    int iBegriff=TabOutliner.getI("Gruppe");
                    //? g.setAbfrage(getBegriff(),iBegriff,iBegriff);
                    JPanel Pnl=(JPanel)TabOutliner.getInhalt("Pnl");
                    int iD2=PnlToAic(Pnl);
                    g.setAbfrage(iD2,FirstAbfrage(iD2));
                    TabOutliner.setInhalt("Abfrage",new ShowAbfrage(g,iBegriff,Abfrage.cstBegriff));
                    FuelleOutliner(Pnl,0,false,true);
                    bRefreshEigenschaften = true;
                }
              });
            }

            /*addMenuItem("PersEinstellungen",popup).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                PersEinstellungen.get(g).show((JFrame)thisFrame);
              }
            });
            addMenuItem("Zeitraum",popup).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                Zeitraum.get(g).show((JFrame)thisFrame);
              }
            });*/

            /*if (TCalc.IsAlive())
              g.addMenuItem("AServer", popup).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                  TCalc.showAServer(g);
                }
              });*/

            if (g.Def())
            {
              popup.addSeparator();
              ActionListener ALD=new ActionListener()
              {
                public void actionPerformed(ActionEvent ev)
                {
                  String s=ev.getActionCommand();
                  if (s.equals("DruckO"))
                    ((JCOutliner)TabOutliner.getInhalt("Gid")).print();
                  else if (s.equals("Druck") || s.equals("DruckQ"))
                    All_Unlimited.Print.DruckHS.printOutliner(g,"Hauptschirm",s.equals("DruckQ"),(JCOutliner)TabOutliner.getInhalt("Gid"));
                }
              };
              g.addMenuItem("Druck Original",popup,"DruckO").addActionListener(ALD);
              g.addMenuItem("Druck",popup,"Druck").addActionListener(ALD);
              g.addMenuItem("Druck Quer",popup,"DruckQ").addActionListener(ALD);

              JMenu MnuTab = new JMenu("Tabellenspeicher");
              popup.add(MnuTab);
              addPopupForTab(MnuTab, "Spalten");
              addPopupForTab(MnuTab, "Daten");
              addPopupForTab(MnuTab, "Joker");
              MnuTab.addSeparator();
              addPopupForTab(MnuTab, "Thread");
              addPopupForTab(MnuTab, "Eigenschaft");
              addPopupForTab(MnuTab, "Outliner");
              addPopupForTab(MnuTab, "Einzeldaten");
              addPopupForTab(MnuTab, "TabAbf");
              addPopupForTab(MnuTab, "Buttons");
              addPopupForTab(MnuTab, "Radiobuttons");
              addPopupForTab(MnuTab, "Ein- und Austritte");
              addPopupForTab(MnuTab, "Ein- und Austritte 2");
              //addPopupForTab(MnuTab, "Abfragen");
              addPopupForTab(MnuTab, "TabSub");
              addPopupForTab(MnuTab, "Filter");
              addPopupForTab(MnuTab, "pers. Abfragen");
              //addPopupForTab(MnuTab, "pers. Joker");
              addPopupForTab(MnuTab, "TabCalc");
              addPopupForTab(MnuTab, "TabStamm");
              addPopupForTab(MnuTab, "Formular");
              addPopupForTab(MnuTab, "TAB_Frames");
              if (Typ() == Stamm)
              {
                MnuTab.addSeparator();
                addPopupForTab(MnuTab, "Bewegung");
              }
              //Abf = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
//              if (bHaupt)
//            	  g.fixtestError("iModell="+A.iModell+", iModell2="+A.iModell2);
              if (A.iModell>0)
              {
        	JMenuItem Mnu=new JMenuItem("M "+g.getModellBez(A.iModell));
        	popup.add(Mnu);
        	Mnu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                  ShowAbfrage Abf = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                  DefModell.get(g, g.ModellToBegriff(Abf.iModell)).show();
                }});
              }
              if (A.iModell2>0)
              {
                JMenuItem Mnu=new JMenuItem("M "+g.getModellBez(A.iModell2));
                popup.add(Mnu);
                Mnu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                  ShowAbfrage Abf = (ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                  DefModell.get(g, g.ModellToBegriff(Abf.iModell2)).show();
                }});
              }
              JMenuItem MnuSelf=new JMenuItem("F "+g.getBegBez(getBegriff()));
              popup.add(MnuSelf);
              MnuSelf.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                DefFormular.get(g,getBegriff());
              }});
              int iPosTF=TAB_Frames.getPos("aic_begriff",getBegriff());
              int iModell=iPosTF<0 ? -1:TAB_Frames.getI(iPosTF,"AIC_Modell");
              if (iModell>0)
              {
                g.checkModelle();
                int iPos=g.TabModelle.getPos("aic_modell", iModell);
                if (iPos>=0)
                {
	                int iModellBegriff=g.TabModelle.getI(iPos,"aic_begriff");
	                JMenuItem MnuModell=new JMenuItem("M "+g.getBegBez(iModellBegriff));
	                MnuModell.setActionCommand(""+iModellBegriff);
	                popup.add(MnuModell);
	                MnuModell.addActionListener(new ActionListener()
	                {
	                  public void actionPerformed(ActionEvent ev)
	                  {
	                    DefModell.get(g, Sort.geti(ev.getActionCommand())).show();
	                  }
	                });
                }
              }
              popup.add(new JMenuItem("A "+(Static.bDefBezeichnung ? A.sDefBez:A.sBez)));
            }
          //}
          if (ev==null)
            popup.show((JCOutliner)TabOutliner.getInhalt("Gid"),0,0);
          else
            popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
          g.clockInfo2("Popup",lClock);
        }

        private boolean Modell2(Abfrage A,int iAic)
        {
          if (A==null)
          {
            g.checkAbfragen();
            int iPos=g.TabAbfragen.getPos("aic_begriff",iAic);
            return iPos>=0 && g.TabAbfragen.getI(iPos,"mod_aic_modell")>0 && (g.TabAbfragen.getL(iPos,"Bits")&A.cstNachSave)==0;
          }
          else
            return A.iModell2>0 && (A.iBits&A.cstNachSave)==0;
        }

        private void Calc2()
        {
          ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
          g.fixInfo("berechne "+g.getModellBez(Abf.iModell2));
          AUOutliner Gid=(AUOutliner)TabOutliner.getInhalt("Gid");
          Calc calc = new Calc(thisFrame, g, Abf.iModell2, false, Static.AicToVec(iStamm), -1, null, 0);
          Tabellenspeicher Tab = calc.getTab(Abf.iAbfrage);
          Abf.TabToOutliner(Gid,Tab,null,null,1);
          Gid.setBackground(Static.ColEF);
          Gid.folderChanged(Gid.getRootNode());
        }

        private void refreshOutliner(boolean bDebug)
        {
          bRefreshEigenschaften = false;
          Abfrage Abf=(Abfrage)TabOutliner.getInhalt("Abfrage");
          g.setAbfrage(PnlToAic(TabOutliner.getInhalt("Pnl")),Abf.iBegriff);
          if (Abf.iModell>0)
          {
        	  int iModBegriff=g.ModellToBegriff(Abf.iModell);
        	  if (bDebug)
          		  Aufruf.addDebugModell(iModBegriff);
//          	  	else
//          		  Aufruf.removeDebugModell(iModBegriff);
          }
//          if (bDebug) g.setDebug(true);
          FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
//          if (bDebug) g.setDebug(false);
          bRefreshEigenschaften = true;
        }

        public static String getEMail(Vector Vec)
        {
          for(int i=0;i<Vec.size();i++)
            if (Vec.elementAt(i)!=null && Vec.elementAt(i) instanceof String && Vec.elementAt(i).toString().indexOf('@')>0)
            {
              return Vec.elementAt(i).toString();
            }
          return null;
        }
        
        public static String getGPS(Vector Vec)
        {
          for(int i=0;i<Vec.size();i++)
            if (Vec.elementAt(i)!=null && Vec.elementAt(i) instanceof GPS)
            {
              return ((GPS)Vec.elementAt(i)).getURL();
            }
          return null;
        }

        private static boolean isBild(String s)
        {
         s=s.toUpperCase();
         return s.endsWith(".JPG") || s.endsWith(".GIF") || s.endsWith(".TIF");
        }

        public static String getBild(Vector Vec)
        {
          for(int i=0;i<Vec.size();i++)
            if (Vec.elementAt(i)!=null && Vec.elementAt(i) instanceof String && isBild(Vec.elementAt(i).toString()))
            {
              String s=Vec.elementAt(i).toString();
              return s.indexOf(java.io.File.separator)>0?s:Static.DirImageStamm+s;
            }
          return null;
        }

        public static String getMemo(Vector Vec)
        {
          for(int i=0;i<Vec.size();i++)
            if (Vec.elementAt(i) != null && Vec.elementAt(i) instanceof Memo1)
              return ((Memo1)Vec.elementAt(i)).getValue();
          return null;
        }

        private void addPopup(Component Gid)
        {
          if(Gid instanceof AUOutliner)
          {
            ((AUOutliner)Gid).getOutliner().addMouseListener(new MouseListener()
            {
              public void mousePressed(MouseEvent ev)
              {}

              public void mouseClicked(MouseEvent ev)
              {
                //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                {
                  if (TabOutliner.posInhalt("Gid", ((JCOutlinerComponent)ev.getSource()).getParent()))
                  {
                    /*MnuSuchen.setVisible(true);
                    MnuHistory.setVisible(true);
                    MnuMehrfach.setVisible(TabOutliner.getS("Typ").equals("Mehrfach"));
                    popup.show((Component)ev.getSource(), ev.getX(), ev.getY());*/
                      showPopup(ev);
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
            ((AUOutliner)Gid).getOutliner().addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{}
			public void keyReleased(KeyEvent e)
			{
			  if (e.getKeyCode()==Global.iPopkey && TabOutliner.posInhalt("Gid", ((JCOutlinerComponent)e.getSource()).getParent()))
			    showPopup(null);
			}
			public void keyTyped(KeyEvent e)
			{}
		});
          }
          else
            Gid.addMouseListener(new MouseListener()
            {
              public void mousePressed(MouseEvent ev)
              {}

              public void mouseClicked(MouseEvent ev)
              {
                //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                {
                  if (TabOutliner.posInhalt("Gid", ev.getSource()))
                  {
                    /*MnuSuchen.setVisible(false);
                    MnuHistory.setVisible(false);
                    MnuMehrfach.setVisible(false);
                    popup.show((Component)ev.getSource(), ev.getX(), ev.getY());*/
                      showPopup(ev);
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

        private void addMnuDruck(JComponent MnuSub,int iAic)
        {
          int iPos = g.TabBegriffe.getPos("Aic", iAic);
          if(iPos >= 0) {
            JMenuItem Mnu = new JMenuItem(g.TabBegriffe.getS(iPos, Static.bDefBezeichnung ? "DefBezeichnung" : "Bezeichnung"));
            Mnu.setFont(g.fontStandard);
            Mnu.setActionCommand("" + iAic);
            MnuSub.add(Mnu);
            Mnu.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                //Tbl=null;
                druck(Sort.geti(ev.getActionCommand()));
              }
            });
          }
        }

        private void druck(int iDruckBegriff)
        {
          //setDialog();
          int iPos=g.TabBegriffe.getPos("Aic",iDruckBegriff);
                int iDBits=g.TabBegriffe.getI(iPos,"bits");
            //boolean b=(iDBits & Drucken.cstPntBewegung)==0;
                boolean bDirekt=(iDBits & Drucken.cstPntDirekt)>0;
                AUOutliner Gid=Tbl==null ? (AUOutliner)TabOutliner.getInhalt("Gid"):bDirekt ? null:Tbl.getGid();
                  ShowAbfrage Abf=Tbl==null ? (ShowAbfrage)TabOutliner.getInhalt("Abfrage"):Tbl.A;
                  boolean bBewD=g.TabBegriffe.getI(iPos,"Erf")>0;
                  //AUOutliner GidDF=Typ()==Bewegung && b || !TabOutliner.posInhalt("Eig",0) ?
                  //    null:(AUOutliner)TabOutliner.getInhalt("Gid");
                  //Vector Vec=getSelected(Gid,true);
                  Vector<Integer> Vec = Gid==null ? bBewD && bDirekt && (iDBits & Drucken.cstPntMenge)==0 ? Tbl.getVecAIC():Tbl.getAllAICs():/*getSelected(Gid,false);*/g.getAics(Gid);
                  //g.fixtestInfo("Vec (für Druck)="+Vec+", Bew="+Abf.iBew);
                  boolean bBew=Abf.iBew>0 && (Abf.iBits&Abfrage.cstDistinct)==0;
                  if (bDirekt)
                  {
                    DruckAufrufA daa = new DruckAufrufA(g,0);
                    int iDF_Bits = Drucken.cstSeitenvorschau + ((iDBits & Drucken.cstPntStammLinks) > 0 ? Drucken.cstStammLinks:0)+
                        ((iDBits&Drucken.cstPntSeitenumbruch)>0 ? Drucken.cstSeitenumbruch:0)+((iDBits&Drucken.cstPntPeriode)>0 ? Drucken.cstPeriode:0);
                    //int iAic=Abf.iBew>0 /*|| iStammtyp==g.iSttANR*/ ? g.getStamm():iStamm;
                    int iEig=!bBew ? 0:SQL.getInteger(g,"select z.aic_eigenschaft from bew_zuordnung z join eigenschaft e on z.aic_eigenschaft=e.aic_eigenschaft where z.aic_bewegungstyp="+Abf.iBew+" and e.aic_stammtyp="+g.TabBegriffe.getI(iPos,"Stt"));
                    //g.testInfo("Direktdruck: Eig="+iEig+", Bew="+Abf.iBew+", Stt="+g.TabBegriffe.getI(iPos,"Stt"));
                    Tabellenspeicher TabStamm=getTabStamm2(Vec,bBewD || (iDBits & Drucken.cstPntBewZR)>0  ? -1: iEig);
                    //TabStamm.showGrid("TabStamm von EF");
                    daa.druckeDruck(iDruckBegriff, /*b ? Abf.iStt:-Abf.iBew,*/ TabStamm,null,null,null, iDF_Bits,0,null,0,0);
                  }
                  else
                  {
                    //if (Tbl!=null) g.select(Sort.geti(Tbl.getVecAIC(),0),Gid);
                    int iMom=Sort.geti(Tbl==null ? Vec:Tbl.getVecAIC(),0);
                    All_Unlimited.Print.DruckFrage.start(Gid,iRolle,bBew ? -Abf.iBew : Abf.iStt,iMom,null, g, iDruckBegriff, getBegriff()).show();
                  }

        }

        private Tabellenspeicher getTabStamm2(Vector Vec, int iEig)
        {
          Tabellenspeicher TabStamm=new Tabellenspeicher(g,iEig==-1 ? "select distinct aic_bew_pool Aic,Bezeichnung from bew_pool join stammview2 on bew_pool.ANR=stammview2.aic_stamm where aic_bew_pool"+Static.SQL_in(Vec):
            iEig==0 ? "select aic_stamm Aic,Bezeichnung from stammview2 where aic_stamm"+Static.SQL_in(Vec)+" and aic_rolle is null":
            "select distinct bew_stamm.aic_stamm Aic,Bezeichnung from bew_stamm join stammview2 on bew_stamm.aic_stamm=stammview2.aic_stamm"+
            " and aic_rolle is null where aic_bew_pool"+Static.SQL_in(Vec)+" and aic_eigenschaft="+iEig, true);

          //if (g.TestPC())
          //  TabStamm.showGrid();
          return TabStamm;
        }

        private Vector<Integer> getVecMom()
        {
          Vector<Integer> Vec=new Vector<Integer>();
          Vec.addElement(iStamm);
          return Vec;
        }

        private boolean berechne(int iModBegriff,char c,boolean bAutoSave,int iAic)
        {
          g.fixtestInfo("berechne:"+iModBegriff+c);
          boolean bDebug=c=='+' || c=='x';
          boolean bKeinReCalc=c=='N' || c=='x';
          //g.checkModelle();
          int iMbits = g.getModellBits(iModBegriff,false);
          //int iPos = g.TabModelle.getPos("Aic_Begriff", iModBegriff);
          //int iMbits = g.TabModelle.getI(iPos, "bits");
          int iPosB = g.TabBegriffe.getPos("Aic", iModBegriff);
          String sBez=g.getBegBez2(iPosB);
          g.fixtestInfo("vor Test-Prüfung");
          if (!g.bTestPC && (iMbits&Global.cstNurTest)>0)
          {
        	  //TODO Meldung wenn keine Test-Datenbank
        	  new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("nurTest");
        	  return false;
          }
          if((iMbits&Global.cstKeineFrage)>0 || new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Berechnen",
              new String[] {sBez+" "+g.getBegriffS("Show", "fuer")+" "+g.getVonBis("dd.MM.yyyy",false)}) == Message.YES_OPTION)
          {
            thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            long lClock=Static.get_ms();
            bCalc=true;
            //setDialog();
            boolean bMSave=(iMbits&Global.cstSave)>0;
            //g.fixInfo("berechne "+iModBegriff+"/"+c+"/"+bCalc+"/"+bMSave);
            if (bMSave)
            {
              bCalc = SpeichereDaten(bAutoSave);
              if (bCalc)
                ResetModified();
              else
              {
                thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                bCalc=false;
                return false;
              }
            }
            if (bCalc && (!bMSave || bCalcS))
            {
              //g.fixInfo("berechne: "+g.TabBegriffe.getBezeichnungS(iModBegriff)+c);
                int iSttB = g.TabBegriffe.getI(iPosB, "Stt"); // Stammtyp des Modells
                int iBewB= g.TabBegriffe.getI(iPosB,"Erf");   // Bewegungstyp des Modells
                ShowAbfrage Abf = Tbl == null ? (ShowAbfrage)TabOutliner.getInhalt("Abfrage") : Tbl.A;
                int iEig = Abf==null || Abf.iBew == 0 || iBewB>0 ? 0 :SQL.getInteger(g,"select z.aic_eigenschaft from bew_zuordnung z join eigenschaft e on z.aic_eigenschaft=e.aic_eigenschaft where z.aic_bewegungstyp=" +
                                       Abf.iBew + " and e.aic_stammtyp=" + iSttB);
                JCOutliner Gid = Tbl == null ? (JCOutliner)TabOutliner.getInhalt("Gid") : null;
                if (Tbl==null && Gid==null)
                {
                	if (TabOutliner.posInhalt("Eig",0))
                		Gid=(JCOutliner)TabOutliner.getInhalt("Gid");
                }
                int iMSatz = bMSave ? iSatz:Tbl == null ? Sort.geti(Gid.getSelectedNode().getUserData()) : Tbl.getAIC(Tbl.iOldY);
                //g.fixtestInfo("berechne: "+g.TabBegriffe.getBezeichnungS(iModBegriff)+c+" mit Satz="+iMSatz);
                //ShowAbfrage Abf=Tbl==null ? (ShowAbfrage)TabOutliner.getInhalt("Abfrage"):Tbl.A;
                Vector<Integer> VecMSelect = bMSave ? Static.AicToVec(iSatz):Gid == null ? Tbl.getAllAICs() : /*getSelected(Gid,false);*/ g.getAics(Gid);
                Tabellenspeicher TabStamm = iBewB>0 ? null:getTabStamm2(VecMSelect, iEig);
                //if (TabStamm != null && g.TestPC())
                //  TabStamm.showGrid("TabStamm");
                boolean bMenge = (iMbits & Global.cstMenge) > 0;
//                if(bDebug)g.setDebug(true);
                if (bDebug)
          		  Aufruf.addDebugModell(iModBegriff);
//          	  	else
//          		  Aufruf.removeDebugModell(iModBegriff);
                Vector VecM = (iSttB==iStammtyp || iSttB==0) && Bewegungstyp()==0 && iBewB==0 && !bMenge ? getVecMom():iBewB>0 ? VecMSelect:TabStamm.getVecSpalte("Aic");
                //g.fixtestInfo("VecM="+VecM);
                //if (g.TestPC())
                //  g.fixInfo("berechne:"+VecM);
                if ((VecM.size()==0 || VecM.elementAt(0)==null) && (iMbits&Global.cstOhneStamm)==0)
                {
                  g.testInfo("Abbruch da kein Wert gewählt");
                  thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                  new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("keinWert");
                  thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                  return false;
                }
//                new Thread(new Runnable()
//				{
//					public void run()
//					{
						//g.fixtestError("Berechne Modell in Thread1");
						int iAicNeu = -1;		 
//						int iMbits = g.getModellBits(iModBegriff,false);
						if(bKeinReCalc && (iMbits & Global.cstRecalc) > 0)
			                  iMbits -= Global.cstRecalc;
			                if(bDebug && (iMbits & Global.cstThread) > 0)
			                  iMbits -= Global.cstThread;
		                for(int i = bMenge ? VecM.size() - 1 : 0; i < VecM.size(); i++)
		                  iAicNeu = TCalc.Berechnen(g, iModBegriff, iMbits, bMenge ? iMSatz : Sort.geti(VecM, i) /*bBew ? iSatz:iStamm*/,
		                                                bMenge ? VecM : null /*bMulti ? VecSelect:VecAic*/, self, bMSave ? iProt:0,null);
//		                if(bDebug)g.setDebug(false);
		                if((iMbits & Global.cstRefreshM) > 0 && iAicNeu >= 0 )
		                  if (iAic==0)
		                    Refresh();
		                  else
		                  {
		                    int iPos=TabAbf.getPos("Aic",iAic);
		                    Tbl=iPos>=0 && (TabAbf.getS(iPos,"Art").equals("JCTable")) ? (AUTable)TabAbf.getInhalt("Komponente",iPos) : null;
		                    if (Tbl==null)
		                    {
		                      TabOutliner.posInhalt("Pnl", TabAbf.getInhalt("Pnl", iPos));
		                      refreshOutliner(false);
		                    }
		                    else
		                      Tbl.Refresh();
		                  }
		                //bCalc=false;
		                g.saveSqlTime("Calc2",0,Static.get_ms()-lClock,sBez,getBegriff(),iMSatz);
					}
//				}).start();
//            }
            bCalc=false;
            thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          }
          return true;
        }

    private void addMnuModell(JComponent MnuSub,int iAic)
	{
	  int iPos=g.TabBegriffe.getPos("Aic",iAic);
	  if (iPos>=0 && g.BerechtigungS(iPos))
	  {
            if (MActL==null)
             MActL=new ActionListener()
             {
              public void actionPerformed(ActionEvent ev)
              {
                String s=ev.getActionCommand();
                int iModBegriff=Sort.geti(s.substring(1));
                char c=s.charAt(0);
                if (c=='D')
                  DefModell.get(g, iModBegriff).show();
                else if (c=='T')
                  ToolTipEdit((JComponent)((JMenuItem)ev.getSource()).getParent(),"B"+iModBegriff);
                else
                {
                  berechne(iModBegriff,c,true,0);
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
            Mnu.setFont(g.fontStandard);
            g.setTooltip(g.TabBegriffe.getS(iPos,"Tooltip"),Mnu);
            //if (MnuSub instanceof JPopupMenu)
            boolean b=true;
            g.checkModelle();
            int iPosM=g.TabModelle.getPos("aic_begriff", iAic);
            int iMbits = g.TabModelle.getI(iPosM, "bits");
            if ((iMbits&g.cstMSperre)>0)
            {
            	Date dt=g.getAbschlussP(iProg,iStamm);
            	g.fixtestInfo("Abschlussdatum für "+Mnu.getText()+"="+dt);
            	b=dt==null || !dt.after(g.getVon());
            }
            else if (!g.bTestPC && (iMbits&Global.cstNurTest)>0)
            	b=false;
            if (b)
            	MnuSub.add(Mnu);
      }
    }

        private void addMnuSubFormular(JComponent MnuSub,int iAic)
	{
          //g.TabBegriffe.push();
	  int iPos=g.TabBegriffe.getPos("Aic",iAic);
	  if (iPos>=0 && g.BerechtigungS(iPos))
	  {
	    JMenuItem Mnu = new JMenuItem(g.TabBegriffe.getS(iPos,Static.bDefBezeichnung?"DefBezeichnung":"Bezeichnung"));
	    Mnu.setFont(g.fontStandard);
	    Mnu.setActionCommand(""+iAic);
            g.setTooltip(g.TabBegriffe.getS(iPos,"Tooltip"),Mnu);
	    MnuSub.add(Mnu);
	    if (FormOk(((JCOutliner)TabOutliner.getInhalt("Gid")),(ShowAbfrage)TabOutliner.getInhalt("Abfrage"),iPos))
  	      Mnu.addActionListener(new ActionListener()
  	      {
                public void actionPerformed(ActionEvent ev)
                {
            	  //g.progInfo("SubFormular "+ev.getActionCommand());
            	  JCOutliner Gid=Tbl==null ? (JCOutliner)TabOutliner.getInhalt("Gid"):null;
            	  if (Tbl==null && Gid==null)
                  {
                  	if (TabOutliner.posInhalt("Eig",0))
                  		Gid=(JCOutliner)TabOutliner.getInhalt("Gid");
                  }
//            	  g.fixtestError("Gid="+Gid);
//            	  g.fixtestError("Gid-Root="+Gid.getRootNode());
            	  int iZAic=Tbl==null ? Gid==null || Gid.getRootNode().getChildren()==null ? 0:Sort.geti(Gid.getSelectedNode().getUserData()):Tbl.getAIC(Tbl.iOldY);
            	  //g.fixtestError("iZAic="+iZAic);
              	  ShowAbfrage Abf=Tbl==null ? (ShowAbfrage)TabOutliner.getInhalt("Abfrage"):Tbl.A;
              	  int iAic=Sort.geti(ev.getActionCommand());
            	  int iPos=g.TabBegriffe.getPos("Aic",iAic);
            	  Vector Vec=Tbl!=null ? Tbl.getAllAICs():Gid==null || Gid.getRootNode().getChildren()==null ? null:getSelected(Gid,false);
            	  Vector Vec2=null;
                  int iSttForm=g.TabBegriffe.getI(iPos,"Stt");
                  boolean bGleicherStt=iSttForm==iStammtyp;
            	  boolean bFomStt=g.TabBegriffe.getI(iPos,"Erf")==0 || g.TabBegriffe.getI(iPos,"Erf") !=Abf.iBew; // Ziel ist Stammformular oder andere Bewegung
            	  if (Abf != null && Abf.iBew>0 && bFomStt)
              	  {
              	    int iEig=SQL.getInteger(g,"select z.aic_eigenschaft from bew_zuordnung z join eigenschaft e on z.aic_eigenschaft=e.aic_eigenschaft where z.aic_bewegungstyp="+Abf.iBew+" and e.aic_stammtyp="+iSttForm);
              	    iZAic=SQL.getInteger(g,"select aic_stamm from bew_stamm where aic_bew_pool="+iZAic+" and aic_eigenschaft="+iEig);
              	    if (Vec != null && Vec.size()>0)
              	      Vec2=SQL.getVector("select aic_stamm from bew_stamm where aic_bew_pool"+Static.SQL_in(Vec)+" and aic_eigenschaft="+iEig, g);
              	  }
            	  //Global.fixInfo("Formularaufruf:"+g.TabBegriffe.getS(iPos,"DefBezeichnung")+":"+iZAic+"/"+Vec2+"/"+Vec);
            	  boolean bBew=Abf != null && Abf.iBew>0;
                  if ((g.TabBegriffe.getI(iPos,"bits")&Formular.cstMehrfach)>0)
                    HoleFormular(g,iAic,iZAic,iStamm,TabOutliner.getI("Eig"),sBezeichnung);
                  else
                    HoleFormular(g,iAic,Abf==null ? null:!bFomStt || !bBew && iSttForm == Abf.iStt ? Vec : Vec2,bFomStt ? bGleicherStt ? iStamm:iZAic:iZAic>0?-iZAic:iStamm,bBew && !bFomStt && iZAic>0);
                }
              });
	    else
	      Mnu.setEnabled(false);
	  }
	  //g.TabBegriffe.pop();
    	}

        /*private void addMnuSubHelp(JComponent MnuSub,int iAic)
        {
          //g.TabBegriffe.push();
          int iPos = g.TabBegriffe.getPos("Aic", iAic);
          int iURL = iPos<0 ? 0:g.TabBegriffe.getI(iPos,"URL");
          if (iURL > 0)
          {
            JMenuItem Mnu = new JMenuItem(g.TabBegriffe.getS(iPos, Static.bDefBezeichnung ? "DefBezeichnung" : "Bezeichnung"));
            Mnu.setFont(g.fontStandard);
            Mnu.setActionCommand("" + iAic);
            g.setTooltip(g.TabBegriffe.getS(iPos,"Tooltip"),Mnu);
            MnuSub.add(Mnu);
            Mnu.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                g.OpenURL(g.TabBegriffe.getI(g.TabBegriffe.getPos("Aic",Sort.geti(ev.getActionCommand())),"URL"));
              }
            });
          }
        }*/

        private Vector getSelected(JCOutliner Gid,boolean bDruck)
        {
          Vector<Integer> Vec=new Vector<Integer>();
      	  JCOutlinerNode[] Nodes=Gid.getSelectedNodes();
          //g.testInfo("getSelected: Anzahl Nodes="+Nodes.length);
      	  if (bDruck || Nodes.length>1)
      	    for (int i=0;i<Nodes.length;i++)
            {
              Integer Int=(Integer)Nodes[i].getUserData();
              if (!Vec.contains(Int))
                Vec.addElement(Int);
            }
      	  else
      	  {
      	    JCOutlinerNode Nod=Gid.getSelectedNode();
      	    if (Nod==null || Nod.getLevel()==0)
      	      return null;
      	    else
      	    {
      	      Vector Vec2=Nod.getParent().getChildren();
              if (Vec2 != null)
                for (int i=0;i<Vec2.size();i++)
                  Vec.addElement((Integer)((JCOutlinerNode)Vec2.elementAt(i)).getUserData());
      	    }
      	  }
          //g.testInfo("getSelected:"+Sort.gets2(Vec));
      	  return Vec;
          //return Multi() ? VecSelect : VecAic;
        }

        private void addMnuFormular(JMenu MnuSub,int iNr,int iPosStt)
        {
          long lBits=iNr>0 ?TabFPop.getL("bits"):0;
          if (/*(lBits&cstJavaFX)>0 ||*/ iNr==4 && (lBits&cstMehrfach)==0 || iNr==1 && (lBits&cstStdFormular)==0 || iNr>1 && iNr<4 && (lBits&cstStdFormular)>0
              || iNr==2 && (lBits&cstPopup)==0 || (iNr==1 || iNr==3) && (lBits&cstPopup)>0)
              return;
          int iPos=iNr==0 ? -1:g.TabBegriffe.getPos("Aic",TabFPop.getI("aic_begriff"));
          if (iNr==0 || iPos>=0 && g.BerechtigungS(iPos))
          {
            JMenuItem Mnu = new JMenuItem(iNr==0 ? g.TabStammtypen.getS(iPosStt,"Bezeichnung"):g.TabBegriffe.getS(iPos,Static.bDefBezeichnung?"DefBezeichnung":"Bezeichnung"));
            Mnu.setFont(g.fontStandard);
            if (iNr>0)
              TabFPop.setInhalt("popup", Mnu);
            MnuSub.add(Mnu);
            if (iNr ==0)
            {
              Mnu.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent ev)
                {
                  int iStamm=Check.getI(TabEigenschaft.getInhalt("Komponente"));
                  HoleFormular(g, -TabEigenschaft.getI("Stammtyp"), Static.AicToVec(iStamm), iStamm,false);
                }
              });
            }
            else if (iNr < 4)
            {
              Mnu.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent ev)
                {
                  int iStamm=Check.getI(TabEigenschaft.getInhalt("Komponente"));
                  if (TabFPop.posInhalt("popup", ev.getSource()))
                    HoleFormular(g, TabFPop.getI("Aic_begriff"), Static.AicToVec(iStamm), iStamm,false);
                }
              });
            }
            else // Mehrfach bei 4
            {
              Mnu.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent ev)
                {
                  if (TabFPop.posInhalt("popup", ev.getSource()))
                  {
                    JCOutlinerNode Nod = ((JCOutliner)TabOutliner.getInhalt("Gid")).getSelectedNode();
                    int iTel = Nod == null || Nod.getLevel() == 0 ? 0 : ((Integer)Nod.getUserData()).intValue();
                    HoleFormular(g, TabFPop.getI("Aic_begriff"), iTel, iStamm, TabOutliner.getI("Eig"), sBezeichnung);
                  }
                }
              });
            }
          }
        }

        private void showPopup2(MouseEvent ev)
        {
          long lClock = Static.get_ms();
          popup2 = new JPopupMenu();
          popup2.setLabel("Eingabeformular2");
          if (!TabEigenschaft.posInhalt("EF",ev.getSource()))
            return;
          Object Komp=TabEigenschaft.getInhalt("Komponente");
          if (Komp instanceof HierarchieEingabe || Komp instanceof AUComboList || Komp instanceof Datum || Komp instanceof FileEingabe)
          {
            g.addMenuItem("Auswahl",popup2).addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                //popup2.setVisible(false);
                Object Komp=TabEigenschaft.getInhalt("Komponente");
                if (Komp instanceof HierarchieEingabe)
                  ((HierarchieEingabe)Komp).openList();
                else if (Komp instanceof AUComboList)
                  ((AUComboList)Komp).OpenList();
                else if (Komp instanceof Datum)
                  ((Datum)Komp).OpenKalender();
                else if (Komp instanceof FileEingabe)
                  ((FileEingabe)Komp).OpenList();
              }
            });
          }
          if (Komp instanceof EMail || Komp instanceof WWW || Komp instanceof FileEingabe)
          {
            g.addMenuItem("aufrufen",popup2).addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                //popup2.setVisible(false);
                Object Komp=TabEigenschaft.getInhalt("Komponente");
                if (Komp instanceof EMail)
                  ((EMail)Komp).MailTo();
                else if (Komp instanceof WWW)
                  ((WWW)Komp).OpenURL();
                else if (Komp instanceof FileEingabe)
                  ((FileEingabe)Komp).openFile();
              }
            });
          }
          if (g.SuperUser() && (TabEigenschaft.getS("Datentyp").equals("FixBild") || TabEigenschaft.getS("Datentyp").equals("FixDoku")))
          {
            g.addMenuItem("Parameter",popup2).addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                FTP.setParameter(g,(JFrame)thisFrame,TabEigenschaft.getS("Datentyp").equals("FixBild") ? "FTP_Bild":"FTP_Doku");
              }
            });
          }
          if (Komp instanceof BildEingabe || Komp instanceof AUFarbe)
          {
            g.addMenuItem("Loeschen",popup2).addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                Object Obj=TabEigenschaft.getInhalt("Komponente");
                if (Obj instanceof BildEingabe)
                  ((BildEingabe)Obj).Delete();
                else if (Obj instanceof AUFarbe)
                  ((AUFarbe)Obj).Delete();
              }
            });
          }
          int iStt2=Komp instanceof HierarchieEingabe ? ((HierarchieEingabe)Komp).getValueStt(): TabEigenschaft.getI("Stammtyp");
          if (iStt2>0)
          {
           int iPosEig=g.TabEigenschaften.getPos("Aic",TabEigenschaft.getI("Aic"));
           if (iPosEig>=0)
           {
            JMenu MnuSub = g.addMenu("Tb_Formular",popup2);//new JMenu(g.getBegriff("Show", "SubFormular"));
            //MnuSub.setFont(g.fontStandard);
            //popup2.add(MnuSub);
            //g.progInfo(ev.getSource()+"/"+Static.className(ev.getSource()));
            //g.TabStammtypen.push();
            int iPosStt=g.TabStammtypen.getPos("Aic",iStt2);
            boolean bStdForm = iPosStt<0 ? false:(g.TabStammtypen.getI(iPosStt,"bits")&Global.cstStdFormular)>0;
            if (bStdForm)
            {
              addMnuFormular(MnuSub, 0,iPosStt);
            }
            else
            {
              int iRolleEig = g.TabEigenschaften.getI(iPosEig,"Rolle");
              //g.progInfo("showPopup:"+TabEigenschaft.getS("Kennung")+"/"+TabEigenschaft.getS("Datentyp")+"/"+TabEigenschaft.getI("Stammtyp"));
              TabFPop = new Tabellenspeicher(g, "select aic_begriff,bits,null popup from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Frame") +
                                             (iRolleEig > 0 ? " and aic_rolle=" + iRolleEig : " and aic_stammtyp=" + iStt2) + " order by defbezeichnung", true);
              for (TabFPop.moveFirst(); !TabFPop.eof(); TabFPop.moveNext())
                addMnuFormular(MnuSub, 1,-1);
              MnuSub.addSeparator();
              for (TabFPop.moveFirst(); !TabFPop.eof(); TabFPop.moveNext())
                addMnuFormular(MnuSub, 2,-1);
              /*if (g.Prog())
              {
                MnuSub.addSeparator();
                for(TabFPop.moveFirst(); !TabFPop.eof(); TabFPop.moveNext())
                  addMnuFormular(MnuSub, 3,-1);
              }*/
            }
            //g.TabStammtypen.pop();
           }
          }
          if (g.History())
          {
            g.addMenuItem("History", popup2).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                Static.bRefreshStop = true;
                int iRolle2=g.TabEigenschaften.getI_AIC("Rolle",TabEigenschaft.getI("Aic"));
                ShowAbfrage.showHistory(g, self, TabEigenschaft.getS("Bez"), TabEigenschaft.getI("Aic"), Multi() ? VecSelect:Static.AicToVec(iStamm), TabEigenschaft.getS("Datentyp"),
                                        Typ() == Bewegung && TabEigenschaft.posInhalt("Datentyp", "BewDatum") ?
                                        ((Datum)TabEigenschaft.getInhalt("Komponente")).getDateTime() : null,
                                        Bewegungstyp() == 0 ? 0 : iSatz,iRolle2==iRolle ? -1:iRolle2);
              }
            });
            AUVector VDtEdit=new AUVector(new String[] {"Hierarchie","Gruppe","Eintritt","Austritt","Bezeichnung","Boolean","Double"});
            String sDt=TabEigenschaft.getS("Datentyp");
            if (g.Def() && VDtEdit.contains(sDt))
             g.addMenuItem("History-Edit", popup2).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                //g.progInfo("History-Edit:" + TabEigenschaft.getS("Bez"));
                String sDt=TabEigenschaft.getS("Datentyp");
                int iEig=TabEigenschaft.getI("Aic");
                if (sDt.equals("Eintritt") || sDt.equals("Austritt") || sDt.equals("Bezeichnung"))
                  iEig=g.TabEigenschaften.getI_AIC("Rolle",TabEigenschaft.getI("Aic"));
                new History(g, (JFrame)thisFrame,TabEigenschaft.getS("Bez"),iEig, iStamm, sDt,Check.clone(TabEigenschaft.getInhalt("Komponente")));
              }
            });
          }

            popup2.addSeparator();
            //JMenuItem MnuBeenden = new JMenuItem(g.getBegriff("Button", "Beenden"));
            g.addMenuItem(g.Def() ? "Tooltip bearbeiten":"Tooltip ansehen",popup2).addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                ToolTipEdit((JComponent)TabEigenschaft.getInhalt("EF"),TabEigenschaft.isNull("Spalte") ?"E"+TabEigenschaft.getI("Aic"):"S"+TabEigenschaft.getI("Spalte"));
              }
            });

          if (g.Def())
          {
            JMenuItem Mnu=new JMenuItem(g.TabEigenschaften.getKennung(TabEigenschaft.getI("Aic")));
            popup2.add(Mnu);
            Mnu.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                DefEigenschaft.get(g,TabEigenschaft.getI("Aic"));
              }
            });
            Mnu=new JMenuItem(TabEigenschaft.getS("Datentyp"));
            popup2.add(Mnu);
            Mnu.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                JCOutliner Grid=TabEigenschaft.Grid;
                TabEigenschaft.showGrid("Eigenschaften");
                //A.TabSpalten.showGrid("Spalten",A.Dlg);//,false);
                if (Grid==null)
                TabEigenschaft.Grid.addActionListener(new JCActionListener()
                {
                  public void actionPerformed(JCActionEvent ev)
                  {
                    JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
                    posEig(Sort.geti(Nod.getLabel(), 0));
                    TabEigenschaft.FrmGrid.setVisible(false);
                  }
                });
              }
            });
          }
          popup2.show((Component)ev.getSource(), ev.getX(), ev.getY());
          g.clockInfo2("Popup",lClock);
        }

        public void posEig(int iAic)
        {
          int iPos=TabEigenschaft.getPos("Aic",iAic);
          g.testInfo("Aic=" + iAic + ":" + TabEigenschaft.getS(iPos, "Bez") + " (Pos=" + iPos + ")");
          Component C = (Component)TabEigenschaft.getInhalt("Komponente", iPos);
        //C.requestFocusInWindow();
          boolean b = C.isShowing();
        //g.fixInfo("isShowing:"+b);
          if (b)
            C.requestFocus();
          else
          {
            int iAnz = 10;
            Component CP = C;
            while (CP != null && iAnz > 0)
            {
              iAnz--;
              CP = CP.getParent();
              if (CP instanceof JTabbedPane)
              {
                JTabbedPane TB = (JTabbedPane)CP;
                int iTab = 0;
                while (!C.isShowing())
                {
                  TB.setSelectedIndex(iTab);
                  iTab++;
                }
                C.requestFocus();
              }
              g.testInfo("C=" + Static.print(CP));
            }
          }
        }

        private void addPopup2(Component Comp)
        {
          Comp.addMouseListener(new MouseListener()
            {
              public void mousePressed(MouseEvent ev)
              {}

              public void mouseClicked(MouseEvent ev)
              {

                //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                {
                  showPopup2(ev);
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

        public static void OeffneAlle(JCOutliner Gid,JCOutlinerFolderNode Nod,int iEbene)
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
                    if (Vec.elementAt(i) instanceof JCOutlinerFolderNode)
                      OeffneAlle(Gid,(JCOutlinerFolderNode)Vec.elementAt(i), iEbene + 1);
                  }
                  if (iEbene==0)
                    Gid.folderChanged(Gid.getRootNode());
                }
        }

        public void selectAll()
        {
          if (TabOutliner.posInhalt("Eig",0))
          {
            bSave=true;
            AUOutliner Out=(AUOutliner)TabOutliner.getInhalt("Gid");
            bCalc=true;
            //boolean bHaupt=TabOutliner.getS("Typ").equals("Haupt");
//            g.fixtestError("selectAll: Out="+Out);
//            if (Out != null)
//            	g.fixtestError("selectAll: Root="+Out.getRootNode());
            if (Out == null || Out.getRootNode() == null || Out.getRootNode().getChildren() == null)
            	return;
            //if (Out.getRootNode() != null)
            	g.fixtestInfo("selectAll: Children="+Out.getRootNode().getChildren());
            int iMom=Out.getSelectedNodes()==null ? 0:Out.getSelectedNodes().length;
            int iMax=Out.getRootNode().getChildren().size();
            g.testInfo("selectAll: "+iMom+" von "+iMax);
            if (iMom<iMax)
            {
              java.awt.Event ev2 = new java.awt.Event(null, 0, null);
              //JCOutlinerNode Nod = Out.getSelectedNode();
              //Out.selectNode(Out.getRootNode(),ev2);
              ev2.modifiers = java.awt.Event.SHIFT_MASK;
              for (JCOutlinerNode NodeAlle = Out.getNextNode(Out.getRootNode()); NodeAlle != null; NodeAlle = Out.getNextNode(NodeAlle))
                Out.selectNode(NodeAlle, ev2);
              if (Static.bND)
            	  Out.setBackground(Color.WHITE);
              //if (Nod.getLevel()>0)
              //  Out.selectNode(Nod, ev2);
              //g.fixInfo("Alle: Nod="+Nod);
            }
            else
            {
              java.awt.Event ev2=new java.awt.Event(null,0,null);
              JCOutlinerNode Nod=Out.getSelectedNode();
              Out.selectNode(Out.getRootNode(),ev2);
              ev2.modifiers=java.awt.Event.CTRL_MASK;
              Out.selectNode(Nod,ev2);
              if (Static.bND)
            	  Out.setBackground(g.ColHS);
            }
            bSave=false;
            bCalc=false;
            //g.fixInfo("###################### selectAll");
            Hauptoutliner_Event(Out,true);
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
                Tabellenspeicher.showGrid(((ShowAbfrage)TabOutliner.getInhalt("Abfrage")).getSpalten(), "Spalten", s2,thisFrame);
                Tabellenspeicher.showGrid((Tabellenspeicher)TabOutliner.getInhalt("Tab"), "Daten", s2,thisFrame);
                Tabellenspeicher.showGrid(((ShowAbfrage)TabOutliner.getInhalt("Abfrage")).TabJoker, "Joker", s2,thisFrame);
                Tabellenspeicher.showGrid(g.TabThread, "Thread", s2,thisFrame);
                Tabellenspeicher.showGrid(TabEigenschaft, "Eigenschaft", s2,thisFrame);
                Tabellenspeicher.showGrid(TabOutliner, "Outliner", s2,thisFrame);
                Tabellenspeicher.showGrid(TabED, "Einzeldaten", s2,thisFrame);
                Tabellenspeicher.showGrid(TabAbf, "TabAbf", s2,thisFrame);
                Tabellenspeicher.showGrid(TabButtons, "Buttons", s2,thisFrame);
                Tabellenspeicher.showGrid(TabRadio, "Radiobuttons", s2,thisFrame);
                Tabellenspeicher.showGrid(TabEinAus, "Ein- und Austritte", s2,thisFrame);
                Tabellenspeicher.showGrid(TabEinAus2, "Ein- und Austritte 2", s2,thisFrame);
                //Tabellenspeicher.showGrid(g.TabUserAbfragen, "Abfragen", s2);
                Tabellenspeicher.showGrid(TabSub, "TabSub", s2,thisFrame);
                Tabellenspeicher.showGrid(g.TabFilter, "Filter", s2,thisFrame);
                Tabellenspeicher.showGrid(g.TabPersAbfragen, "pers. Abfragen", s2,thisFrame);
                //Tabellenspeicher.showGrid(g.TabPersJoker, "pers. Joker", s2);
                Tabellenspeicher.showGrid(TCalc.TabCalc, "TabCalc", s2,thisFrame);
                Tabellenspeicher.showGrid(g.TabStamm,"TabStamm",s2,thisFrame);
                Tabellenspeicher.showGrid(TabFormular, "Formular", s2,thisFrame);
                Tabellenspeicher.showGrid(TAB_Frames, "TAB_Frames", s2,thisFrame);
                if (s2.equals("Bewegung"))
                {
                  new Tabellenspeicher(g, "select aic_bewegungstyp Aic"+g.AU_Bezeichnung3("Bewegungstyp","bewview2")+
                                       ",count(*) Anzahl from bewview2 where anr="+iStamm+" group by aic_bewegungstyp", true).showGrid("Bewegung von " + g.getStamm(iStamm));
                }
                //Tabellenspeicher.showGrid(TabFormular, "Bewegung", s2);
              }
            };
          MnuTab.addActionListener(AL1);
        }

        private void addAL()
        {
          if (AL==null)
            AL=new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    String s = ev.getActionCommand();
                    g.fixtestInfo("Action="+s);
                    if (s.equals("Ok") || s.equals("Abbruch") || s.equals("Beenden"))
                      Beenden(s.equals("Ok") ? 1:s.equals("Abbruch") ? 2:0);
                    else if (s.equals("Neu"))
                      neu(false);
                    else if (s.equals("Kopie") || s.equals("Kopie2"))
                      if (bEntfernen || s.equals("Kopie2"))
                      {
                        if (TabOutliner.posInhalt("Eig",0))
                          KopiereOut((JCOutliner)TabOutliner.getInhalt("Gid"), true,((ShowAbfrage)TabOutliner.getInhalt("Abfrage")).iBegriff);
                      }
                      else
                        neu(true);
                    else if (s.equals("checkStamm") && TabOutliner.posInhalt("Eig",0))
                    	checkStamm((JCOutliner)TabOutliner.getInhalt("Gid"));
                    else if (s.startsWith("Loesche"))// || s.startsWith("destroy"))
                    {
                      boolean b=false;
                      String sBez=Typ() == Stamm ? sBezeichnung:dtGueltigBew==null ? "null":new Zeit(dtGueltigBew,"").toString();                     
                      if (s.equals("Loeschen") || s.equals("LoeschenO"))
                      {
                        //b=LoescheDaten((JFrame)thisFrame,Typ(),iSatz,Typ() == Stamm ? sBezeichnung:new Zeit(dtGueltigBew,"").toString());
                    	if (s.equals("LoeschenO") || TabOutliner.posInhalt("Eig",0))
                          LoescheOut((JCOutliner)TabOutliner.getInhalt("Gid"),((ShowAbfrage)TabOutliner.getInhalt("Abfrage")).iBew);
                        else
                          b=LoescheDaten((JFrame)thisFrame,Typ(),iSatz,sBez);
                      }
                      else if (s.equals("Loesche Rolle"))
                        b=LoescheRolle((JFrame)thisFrame,Typ(),iSatz,sBez);
//                      else if (s.equals("destroy role"))
//                          b=DestroyRole((JFrame)thisFrame,Typ(),iSatz,sBez,false);
//                      else if (s.equals("destroy data"))
//                          b=DestroyRole((JFrame)thisFrame,Typ(),iSatz,sBez,true);
                      if (b)
                      {
                        ResetAll();
                              if (Typ() == Stamm && VecAic != null)
                                VecAic.removeElement(new Integer(iStamm));
                              if (TabOutliner.posInhalt("Eig",0))
                                FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),iSatz,false,true);
                      }
                    }
                    else if (s.equals("Undelete"))
                      UndelOut((JCOutliner)TabOutliner.getInhalt("Gid"));
                    else if (s.equals("Speichern"))
                    {
                           bReLoad=true;
                           if (isNeuCheck() && iSatz==0)
                           {
                             //BtnSave.setEnabled(true);
                             if (NeuCheck(true))
                               SpeichereDatenM(true);
                             else
                               bReLoad=false;
                           }
                           else
                             SpeichereDatenM(true);
                    }
                    //else if (s.equals("SpeichernO"))
                    //  SpeichereDatenM(true);
                    else if (s.equals("Ruecksetzen"))
                      FuelleEigenschaften(iSatz,false);
                    else if (s.equals("Druck"))
                    {
                      AUOutliner GidDF=TabOutliner.posInhalt("Eig",0)?(AUOutliner)TabOutliner.getInhalt("Gid"):null;
                      All_Unlimited.Print.DruckFrage.start(GidDF,iRolle,Typ()==Bewegung?-Bewegungstyp():iStammtyp,iSatz,null,g,0,getBegriff()).show();
                    }
                    else if (s.equals("DruckMenge"))
                      All_Unlimited.Print.DruckFrage.start(null,iRolle,Typ()==Bewegung?-Bewegungstyp():iStammtyp,iSatz,VecAic,g,0,getBegriff()).show();
                    else if (s.equals("Druck2"))
                      All_Unlimited.Print.DruckFrage.start(null,iRolle,iStammtyp,iStamm,null,g,0,getBegriff()).show();
                    else if (s.equals("Export"))
                      new Export(g, (JFrame)thisFrame, iStammtyp, VecSelect/*Static.AicToVec(iStamm)*/,0);
                    else if (s.equals("WEB"))
                    {
                      if (TabEigenschaft.posInhalt("Datentyp","WWW"))
                        Static.OpenURL(((WWW)TabEigenschaft.getInhalt("Komponente")).getValue());
                      else
                        new Message(Message.INFORMATION_MESSAGE,(JFrame)thisFrame,g).showDialog("Web");
                    }
                    else if (s.equals("Zeitraum"))
                      Zeitraum.get(g).show();
                    //else if (s.equals("HA"))
                    //  HA(false);
                    else if (s.equals("Tag") || s.equals("Woche") || s.equals("Monat"))
                      changeZA(s);
                    else if (s.startsWith("ZRplus") || s.startsWith("ZRminus"))
                      changeZR(s,true);
                    else if (s.startsWith("Jetzt"))
                      Jetzt();
                    else if (s.equals("Refresh"))
                      Refresh();
                    else if (s.equals("RefreshO") || s.equals("RefreshD"))
                      refreshOutliner(s.equals("RefreshD"));
                    else if (s.equals("WW"))
                      WW.start(g,(JFrame)thisFrame).show2();
                    else if (s.equals("DruckConfig"))
                      Seitenlayout.get(g,new ComboSort(g)).show();
                    else if (s.equals("user"))
                    	BenutzerAnlegen();
                    else if (s.equals("SyncStamm"))
                      SyncStamm.start(g,null).show();
                    else if (s.equals("Abschluss"))
                      Abschluss.get(g).show();
                    else if (s.equals("Aufgabe"))
                        DefAufgabe.get(g,0).show();
                    else if (s.equals("Check"))
                    {
                      //BtnSave.setEnabled(false);
                      NeuCheck(false);
                    }
                    else if (s.equals("alle_Daten") || s.equals("nur_Vector") || s.equals("nur_Firma"))
                    {
                      if (Modified())
                      {
                        if (iDaten==ALLE && !s.equals("alle_Daten"))//if (bAlle && s.equals("nur_Vector"))
                          BtnAlleDaten.doClick();
                        else if(iDaten==VECTOR && !s.equals("nur_Vector"))//if (!bAlle && s.equals("alle_Daten"))
                          BtnNurVector.doClick();
                        else if(iDaten==FIRMA && !s.equals("nur_Firma"))
                          BtnNurFirma.doClick();
                        //((JToggleButton)ev.getSource()).setFocusPainted(false);//((JToggleButton)ev.getSource()).transferFocusBackward();
                      }
                      else
                      {
                        long lClock = Static.get_ms();
                        //((JToggleButton)ev.getSource()).setFocusPainted(true);
                        iDaten = s.equals("alle_Daten") ? ALLE:s.equals("nur_Vector") ? VECTOR:FIRMA;
                        g.progInfo("-------  vor " + s + ":" + bBewVec + "/" + VecAic+"/"+iStamm);
                        //int iSatzOld=iSatz;
                        bCalc=true;
                        int iPos0=TabOutliner.getPos("Eig",0);
                        AUOutliner GidHO = iPos0<0 ? null:(AUOutliner)TabOutliner.getInhalt("Gid",iPos0);
                        Vector<Integer> VecSel=GidHO==null ? null:g.getAics(GidHO);
                        if (iPos0>=0)
                          FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl",iPos0), iSatz, false, true);
                        else
                        {
                          refreshPlanung(true, false);
                          TabOutliner.push();
                          for (TabOutliner.moveFirst();!TabOutliner.out();TabOutliner.moveNext())
                            FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),bMulti ? -1:iSatz,false,true);
                          TabOutliner.pop();
                        }
                        bCalc=false;
                        g.progInfo("------- nach " + s + ":" + bBewVec + "/" + VecAic+"/"+iStamm);

                        if (GidHO!= null && GidHO.getSelectedNode().getLevel()==0)
                        {
                          iStamm=-1;
                          Refresh();
                        }
                        else
                          Titelzusatz("");
                        Vector<Integer> VecAll=g.getAllAics(GidHO);
                        if (VecSel==null || VecAll.containsAll(VecSel))
                          g.testInfo(s+" ok: "+VecSel+"/"+VecAll);
                        else
                        {
                          GidHO.selectNode(GidHO.getRootNode());
                          Refresh();
                          g.testInfo(s+" Refresh: "+g.getAics(GidHO)+"/"+VecAll);
                        }
                        g.clockInfo(s, lClock);
                      }
                    }
                    else if (s.equals("alle"))
                      selectAll();
                    else if (s.equals("open down"))
                      OeffneAlle((JCOutliner)TabOutliner.getInhalt("Gid"),null,0);
                    else if (s.equals("Calc2"))
                      Calc2();
                    else if (s.equals("ParaSMTP"))
                    	UserManager.setParaSMTP(g,thisJFrame());
                    else if (s.startsWith("H"))
                    {
                      ;//g.OpenURL(Sort.geti(s.substring(1)));
                    }
                    else if (s.startsWith("_"))
                    {
                      int iB=Sort.geti(s.substring(2,s.indexOf(",")));
                      int iAic=Sort.geti(s.substring(s.indexOf(",")+1));
                      g.progInfo("Drucke "+iB+" aus "+iAic);
                      int iPos=TabAbf.getPos("Aic",iAic);
                      Tbl=iPos>=0 && (TabAbf.getS(iPos,"Art").equals("JCTable")) ? (AUTable)TabAbf.getInhalt("Komponente",iPos) : null;
                      if (Tbl==null)
                        TabOutliner.posInhalt("Pnl",TabAbf.getInhalt("Pnl",iPos));
                      if (s.startsWith("_D"))
                        druck(iB);
                      else if (s.startsWith("_M"))
                        berechne(iB,'-',true,iAic);
                      else
                        Static.printError("EingabeFormular.addAL:"+s+" wird nicht unterstützt!");
                    }
                    else
                      Static.printError("EingabeFormular.addAL:"+s+" wird nicht unterstützt!");
                  }
                };

          if (AL2==null)
            AL2 = new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                String s=ev.getActionCommand();
                //g.fixtestInfo("AL2:"+s);
                int iAic=s.length()>1 ? Sort.geti(s.substring(1)):0;
                char c=s.charAt(0);
                boolean bRefresh=false;
                final int iPos=iAic>0 ? TabAbf.getPos("Aic",iAic):-1;
                //if (iAic>0)
                //  iPos=TabAbf.getPos("Aic",iAic);
                Tbl=iPos>=0 && (TabAbf.getS(iPos,"Art").equals("JCTable")) ? (AUTable)TabAbf.getInhalt("Komponente",iPos) : null;
                //g.fixtestInfo(iAic+":"+Tbl);
                P = ((Component)ev.getSource()).getLocationOnScreen();
                if (c=='-' || c == '+')
                {
                  changeZR(c == '+' ? "ZRplus":"ZRminus", false);
                  bRefresh=true;
                }
                //else if (c=='Z')
                //  Zeitraum.get(g).show();
                else if (c=='R')
                  bRefresh=true;
                else if (c=='S')
                {
                  if (Tbl==null)
                  {
                    if (iPos<0)
                      TabOutliner.posInhalt("Eig",0);
                    else
                      TabOutliner.posInhalt("Pnl", (JPanel)TabAbf.getInhalt("Pnl", iPos));
                    suchen(P);
                  }
                  else
                    Tbl.suchen(P);
                }
                else if (c=='C' && iPos>=0)
                {
                  TabOutliner.posInhalt("Pnl", (JPanel)TabAbf.getInhalt("Pnl", iPos));
                  Calc2();
                }
                else if (c=='E')
                {
                  JDialog Dlg=(JDialog)TabEinstellung.getInhalt("Dialog",iAic);
                  Dlg.pack();
                  Static.centerComponent(Dlg, thisFrame);
                  Dlg.setVisible(true);
                }
                else if (c=='A')
                {
                  JPanel Pnl2=null;
                  if (iPos<0)
                  {
                    if (TabOutliner.posInhalt("Eig",0))
                      Pnl2=(JPanel)TabOutliner.getInhalt("Pnl");
                  }
                  else
                    Pnl2=(JPanel)TabAbf.getInhalt("Pnl",iPos);
                  if (Tbl==null)
                    TabOutliner.posInhalt("Pnl", Pnl2);
                  JPopupMenu popM=new JPopupMenu();
                  ButtonGroup RadGroup=new ButtonGroup();
                  int iAicMom= Tbl==null ? ((ShowAbfrage)TabOutliner.getInhalt("Abfrage")).iBegriff:Tbl.A.iBegriff;
                  for (int i=TabAbf.getPos("Pnl",Pnl2);i<TabAbf.size() && TabAbf.getInhalt("Pnl",i).equals(Pnl2); i++)
                  {
                    int iAic2=TabAbf.getI(i,"Aic");
                    JRadioButtonMenuItem Mnu = new JRadioButtonMenuItem(g.TabBegriffe.getBezeichnungS(iAic2));
                    /*Mnu.putClientProperty(JRadioButtonMenuItem.SELECTED_ICON_CHANGED_PROPERTY, Boolean.TRUE);
                    Mnu.putClientProperty(JRadioButtonMenuItem. ICON_CHANGED_PROPERTY, Boolean.TRUE);
                    Mnu.setIcon(AUCheckBox.unchecked);
                    Mnu.setSelectedIcon(AUCheckBox.checked);*/
                    //Mnu.setPressedIcon(AUCheckBox.checked);
                    Mnu.setFont(g.fontStandard);
                    Mnu.setActionCommand(""+iAic2);
                    if (iAicMom==iAic2)
                      Mnu.setSelected(true);
                    int iBew=g.TabBegriffe.getI_AIC("Erf", iAic2);
					if (iBew>0 && !g.allowBew(iBew))
						Mnu.setEnabled(false);
                    //g.setTooltip(g.TabBegriffe.getS(iPos,"Tooltip"),Mnu);
                    RadGroup.add(Mnu);
                    if (getAnzAbf(Pnl2)<2)
                      Mnu.setEnabled(false);
                    else
                      Mnu.addActionListener(new ActionListener()
                      {
                        public void actionPerformed(ActionEvent ev)
                        {
                          int iAic = Sort.geti(ev.getActionCommand());
                          //AUTable Tbl2=Tbl;//(AUTable)TabAbf.getInhalt("Komponente",iPos);
                          int iPos=-1;
                          if (Tbl==null)
                          {
                            TabOutliner.setInhalt("Abfrage",new ShowAbfrage(g,iAic,Abfrage.cstBegriff));
                            g.setAbfrage(PnlToAic(TabOutliner.getInhalt("Pnl")),iAic);
                            FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
                            iPos=TabAbfragenTitel==null ? -1:TabAbfragenTitel.getPos("Komp",TabOutliner.getInhalt("Pnl"));
                          }
                          else
                          {
                            g.progInfo("vor Save2");
                            if (Tbl.Modified())
                              Tbl.Save(0, true, isBewBew() ? -iSatz : iStamm);
//                            g.fixtestError("vor setAbfrage "+iAic);
                            Tbl.setAbfrage(iAic);
                            iPos=TabAbfragenTitel.getPos("Komp",Tbl);
                          }
                          if (iPos>=0)
                          {
                            JLabel Lbl = (JLabel)TabAbfragenTitel.getInhalt("Lbl", iPos);
                            Lbl.setText(g.TabBegriffe.getBezeichnungS(iAic)+" ");
                            Lbl.revalidate();
                          }
                          else if (LblAbf != null)
                            LblAbf.setText(g.TabBegriffe.getBezeichnungS(iAic));
                        }
                      });
                    popM.add(Mnu);
                  }
                  popM.addSeparator();
                  if (g.Abfrage())
                  {
                    g.addMenuItem("Abfrage", popM).addActionListener(new ActionListener()
                    {
                      public void actionPerformed(ActionEvent ev)
                      {
                        //AUTable Tbl=(AUTable)TabAbf.getInhalt("Komponente",iPos);
                        ShowAbfrage Abf = Tbl==null ? (ShowAbfrage)TabOutliner.getInhalt("Abfrage"):Tbl.A;
                        if (g.Def() || (Abf.iBits & Abfrage.cstNoChange) == 0)
                          DefAbfrage.get(g, Abf, Abf.iBew == 0 ? Abf.iStt : -Abf.iBew).show();
                      }
                    });

                    if (g.Def())
                      g.addMenuItem("Abfrage2", popM).addActionListener(new ActionListener()
                      {
                        public void actionPerformed(ActionEvent ev)
                        {
                          //AUTable Tbl=(AUTable)TabAbf.getInhalt("Komponente",iPos);
                          ShowAbfrage Abf = Tbl==null ? (ShowAbfrage)TabOutliner.getInhalt("Abfrage"):Tbl.A;
                          //if (g.Def() || (Abf.iBits & Abfrage.cstNoChange) == 0)
                            DefAbfrage.get(g, Abf, Abf.iBew == 0 ? Abf.iStt : -Abf.iBew, null, false, 1).show();
                        }
                      });

                    g.addMenuItem("Init", popM).addActionListener(new ActionListener()
                    {
                      public void actionPerformed(ActionEvent ev)
                      {
                        bRefreshEigenschaften = false;
                        int iBegriff = TabOutliner.getI("Gruppe");
                        //? g.setAbfrage(getBegriff(),iBegriff,iBegriff);
                        JPanel Pnl = (JPanel)TabOutliner.getInhalt("Pnl");
                        int iD2 = PnlToAic(Pnl);
                        g.setAbfrage(iD2, FirstAbfrage(iD2));
                        TabOutliner.setInhalt("Abfrage", new ShowAbfrage(g, iBegriff, Abfrage.cstBegriff));
                        FuelleOutliner(Pnl, 0, false, true);
                        bRefreshEigenschaften = true;
                      }
                    });
                  }

                  popM.show((Component)ev.getSource(), 0, 20);
                }
                else if (c=='H')
                  g.fixtestInfo("folgende Seite aufrufen:"+s.substring(1));
                else if (c=='F' || c=='M' || c=='D')// || c=='H')
                {
                  if (iPos<0)
                    TabOutliner.posInhalt("Eig",0);
                  JPanel Pnl2=iPos<0 ? (JPanel)TabOutliner.getInhalt("Pnl"):(JPanel)TabAbf.getInhalt("Pnl",iPos);
                  if (Tbl==null && iPos>=0)
                    TabOutliner.posInhalt("Pnl", Pnl2);
                  Vector VecM=getVec(c=='F' ? "Frame":c=='M' ? "Modell":c=='D' ? "Druck":c=='H' ? "Button":null,Pnl2);
                  JPopupMenu popM=new JPopupMenu();
                  for (int i=0;i<VecM.size();i++)
                    if (c=='F')
                      addMnuSubFormular(popM,Sort.geti(VecM,i));
                    else if (c=='M')
                      addMnuModell(popM,Sort.geti(VecM,i));
                    else if (c=='D')
                      addMnuDruck(popM,Sort.geti(VecM,i));
                    //else if (c=='H')
                    //  addMnuSubHelp(popM,Sort.geti(VecM,i));
                  popM.show((Component)ev.getSource(), 0, 20);
                }
                else
                  Static.printError("EingabeFormular.addAL (AL2):"+s+" wird nicht unterstützt");
                if (bRefresh)
                  if (Tbl==null)
                    if (iAic==0)
                      Refresh();
                    else
                    {
                      TabOutliner.posInhalt("Pnl",(JPanel)TabAbf.getInhalt("Pnl",iPos));
                      refreshOutliner(false);
                    }
                  else
                    Tbl.Refresh();

              }
            };

        }
        
      private void  NeuerBenutzer(JDialog Dlg,String sBez,String sKen,int iV,int iS,int iSp,int iLand,String sTel,String sEMail)
      {
//    	  g.fixtestError("Benutzer "+sBez+"/"+sKen+" mit Vorlage "+iV);
    	  SQL Qry=new SQL(g);
    	  
    	  	Qry.add("Kennung", sKen);
    	  	Qry.add("AIC_Stamm",iS);
    	  	Qry.add("AIC_Mandant",g.getMandant());
    	  	int iBits=SQL.getInteger(g, "select bits from benutzer where aic_benutzer="+iV)-g.cstVorlage;
	  	 	Qry.add("Bits",iBits);
	  	 	Qry.add0("AIC_Sprache",iSp);
	  	 	Qry.add0("AIC_Land",iLand);
	  	 	Qry.add0("AIC_Benutzergruppe",SQL.getInteger(g, "select AIC_Benutzergruppe from benutzer where aic_benutzer="+iV));
	  		Qry.add("aic_logging",g.getLog());
	  		Qry.add("tel",sTel);
	  		Qry.add("e_mail",sEMail);
	  		Qry.addnow("seit");
	  		int iB=Qry.insert("Benutzer",true);
//	  		int iPWArt=iBits & Global.cstPW;
			if ((iBits & Global.cstPW)==Global.cstPW_MD5B)
			{
				Qry.add("Passwort",g.PasswordConvert(Static.sDefaultPW, Global.PWVH, iB));
				Qry.update("Benutzer", iB);
			}
			g.SaveDefVerlauf(getBegriff(), "B"+iB+": Neuer Benutzer "+sKen+(iV>0 ? " mit Vorlage="+iV:""));
			g.setBezeichnung("", sBez, g.TabTabellenname.getAic("BENUTZER"), iB, 1);
			
			Tabellenspeicher Tab=new Tabellenspeicher(g,"select z.* from benutzer_zuordnung z where aic_benutzer="+iV,true);
			for(Tab.moveFirst();!Tab.out();Tab.moveNext())
			{
				Qry.add("aic_benutzer", iB);
				int iBG=Tab.getI("aic_benutzergruppe");
				Qry.add("aic_benutzergruppe", iBG);
				Qry.add0("aic_abfrage", Tab.getI("aic_abfrage"));
				Qry.add("Nr", Tab.getI("Nr"));
				Qry.add0("aic_stamm", Tab.getI("aic_stamm"));
				Qry.add0("sta_aic_stamm", Tab.getI("sta_aic_stamm"));
				Qry.add("bits", Tab.getI("bits"));
				Qry.insert("benutzer_zuordnung", false);
			}
			if (iB>0)
				new Message(Message.INFORMATION_MESSAGE,Dlg,g,10).showDialog("newUser");
      }
      
      private String notDef()
      {
    	  return " Geloescht IS NULL AND"+g.bits("Benutzer.Bits",Global.cstSuperUser+Global.cstTest)+"=0 AND AIC_Mandant="+g.getMandant()+" AND ";
      }
      
      private String getS(int iEig)
      {
//    	  g.fixtestError("Ermittle Daten für Eigenschaft "+iEig);
    	  if (TabEigenschaft.posInhalt("Aic", iEig))
    	  {
//    		  if (TabEigenschaft.getInhalt("Komponente") instanceof Text)
    			  return Check.getS(TabEigenschaft.getInhalt("Komponente"));
    	  }
    	  return "";
      }
        
      private void BenutzerAnlegen()
      {
    	  if (iStamm<=0)
    		  return;
//    	  g.fixtestError("Benutzer "+iStamm+" anlegen prüfen");
    	  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_benutzer,kennung,tel,e_mail,aic_sprache,aic_land,bits,passwort"+g.AU_Bezeichnung("benutzer")+" from benutzer where"+notDef()+"aic_stamm="+iStamm,true);
    	  boolean bNew=Tab.isEmpty();
    	  JDialog Dlg=new JDialog(thisJFrame(),g.getShow(bNew ? "newUser":"changeUser"),true);
    	  JPanel Pnl=new JPanel(new BorderLayout(2, 2));
    	  JScrollPane LM=new JScrollPane();
    	  LM.setBorder(new EmptyBorder(5,5,5,5));
    	  LM.setViewportView(Pnl);
    	  Dlg.setContentPane(LM);
    	  int iB=bNew ? 0:Tab.getI("aic_benutzer");
    	  if (bNew)
    		  Tab=new Tabellenspeicher(g,"select bezeichnung,null kennung,null tel,null e_mail from stammview2 where aic_rolle is null and aic_stamm="+iStamm,true);
    	  String sTel=Tab.getS("Tel");
    	  String sEMail=Tab.getS("E_Mail");
    	  if (bNew || Static.Leer(sTel))
    		  sTel=getS(g.iEigTel);
    	  if (bNew || Static.Leer(sEMail))
    		  sEMail=getS(g.iEigEMail);
    	  Text TxtBez=new Text(/*bNew ? g.getStamm(iStamm):*/Tab.getS("Bezeichnung"),100);
    	  Text TxtKennung=new Text(bNew ? sEMail:Tab.getS("Kennung"),40,Text.KENNUNG2);
    	  Text TxtTel=new Text(sTel,98);TxtTel.setAktText(Tab.getS("Tel"));
    	  Text TxtEMail=new Text(sEMail,98);TxtEMail.setAktText(Tab.getS("E_Mail"));
    	  ComboSort CboSprache=new ComboSort(g);
    	  CboSprache.fillDefTable("Sprache", true);
    	  CboSprache.setSelectedAIC(bNew ? g.getSprache():Tab.getI("Aic_Sprache"));
    	  ComboSort CboLand=new ComboSort(g);
    	  CboLand.fillDefTable("Land", true);
    	  CboLand.setSelectedAIC(bNew ? g.getLand():Tab.getI("Aic_Land"));
    	  ComboSort CboVorlage=bNew ? new ComboSort(g):null;
    	    
    	  JPanel PnlW=new JPanel(new GridLayout(0,1,2,2));
    	    g.addLabel(PnlW,"Bezeichnung",TxtBez);
    	    g.addLabel(PnlW,"Kennung",TxtKennung);
    	    g.addLabel(PnlW,"Sprache",CboSprache);
    	    g.addLabel(PnlW,"Land",CboLand);
    	    if (g.Def())
    	    {
    	      g.addLabel(PnlW,"Tel",TxtTel);   	      
    	    }
          g.addLabel(PnlW,"E-Mail",TxtEMail);
    	    if (bNew)
    	    	g.addLabel(PnlW,"Vorlage",CboVorlage);
    	  Pnl.add("West",PnlW);
      	  JPanel PnlC=new JPanel(new GridLayout(0,1,2,2));
      	    	
      	    PnlC.add(TxtBez);
      	    PnlC.add(TxtKennung);
      	    PnlC.add(CboSprache);
      	    PnlC.add(CboLand);
      	    if (g.Def())
      	    {
      	      PnlC.add(TxtTel);     	      
      	    }
            PnlC.add(TxtEMail);
      	    if (bNew)
      	    {
      	      CboVorlage.fillCbo("select aic_benutzer,kennung"+g.AU_Bezeichnung("benutzer")+" from benutzer where"+g.bit("bits", Global.cstVorlage)+" and aic_mandant="+g.getMandant(), "Benutzer", true, false);
      	      PnlC.add(CboVorlage);
      	    }
      	  Pnl.add("Center",PnlC);
      	JButton BtnNewPassword = g.getButton("new password");
          JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,5,2));
	          ActionListener AL=new ActionListener()
	          {
	            public void actionPerformed(ActionEvent ev)
	            {
	              String s = ev.getActionCommand();
	              if (s.equals("Reset"))
	              {
	            	  String sPW=g.PasswordConvert(Static.sDefaultPW,Global.PWVH,iB);
			          	g.exec("update benutzer set passwort='"+sPW+"',aic_logging="+g.getLog()+" where aic_benutzer="+iB);
			          	BtnNewPassword.setEnabled(false);
			          	g.SaveDefVerlauf(getBegriff(), "B"+iB+": reset PW");
			          	new Message(Message.INFORMATION_MESSAGE,Dlg,g,10).showDialog("New Password",new String[]{Static.sDefaultPW});
			      }
	              else if (s.equals("History"))
	              {
	            	  UserManager.showHistory(g,iB,TxtKennung.getText(),Dlg,null);
//	            	  new Tabellenspeicher(g,"select (select b.kennung from benutzer b join logging l on b.aic_benutzer=l.aic_benutzer where l.aic_logging=v.aic_logging) wer,v.Timestamp wann,(select defbezeichnung from begriff where aic_begriff=v.aic_begriff) wo"+
//	              ",v.Tat was from defverlauf v where v.tat like 'B"+iB+":%'",true).showGrid("Benutzer "+TxtKennung.getText(),Dlg);
	              }
	              else if (s.equals("Abbruch"))
	            	Dlg.dispose();
	              else if (s.equals("Ok"))
	              {          	 
	             	 if (TxtKennung.isNull() || bNew && CboVorlage.isNull())
	             	 {
	     				 new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog(TxtKennung.isNull() ? "KennungLeer":"VorlageFehlt");
	     				 return;
	     			 }   
	             	 String sKen=TxtKennung.getText();
	             	 String sK=Static.StringForSQL(TxtKennung.getText(), 40);
	             	 if ((bNew || TxtKennung.Modified()) && (sKen.indexOf(' ')>=0 || sKen.length()<Static.iBenML))
	             	 {
                        String sWarning="KennungLeer"+(sKen.equals("")?"":sKen.length()<Static.iBenML ? "_zu_kurz":"zeichen");
//                        new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog(sWarning,new String[] {sKen});
                        Passwort.PW_Message(g,Dlg,sWarning);
	     				return;
	             	 }
	             	 if (SQL.exists(g,"select aic_Benutzer from  Benutzer WHERE"+(bNew ? "":" aic_Benutzer<> "+iB+" and")+" Kennung="+sK))
	     			 {
	     				 new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog("KennungVorhanden",new String[] {sKen});
	     				 return;
	     			 }  
	     			 int iSp=CboSprache.getSelectedAIC();
	     			 int iLand=CboLand.getSelectedAIC();
	             	 if (bNew)
	             		 NeuerBenutzer(Dlg,TxtBez.getText(),sKen,CboVorlage.getSelectedAIC(),iStamm,iSp,iLand,TxtTel.getText(),TxtEMail.getText());
	             	 else
	             	 {      
	             		 if (TxtKennung.Modified() || CboSprache.Modified() || CboLand.Modified() || TxtTel.Modified() || TxtEMail.Modified())
	             		 {
	             			 String sTel=TxtTel.isNull() ? null:Static.StringForSQL(TxtTel.getText(), 98);
	             			 String sEMail=TxtTel.isNull() ? null:Static.StringForSQL(TxtEMail.getText(), 98);
	             			 g.exec("update benutzer set kennung="+sK+",aic_logging="+g.getLog()+",aic_sprache="+(iSp==0 ? "null":""+iSp)+",aic_Land="+(iLand==0 ? "null":""+iLand)+
	             					 (sTel!=null ? ",Tel="+sTel:"")+(sEMail!=null ? ",E_Mail="+sEMail:"")+" where aic_benutzer="+iB);
	             		 }
//	             		 if (CboSprache.Modified())
//	             			 g.exec("update benutzer set aic_sprache="+(iSp==0 ? "null":""+iSp)+" where aic_benutzer="+iB);
//	             		 if (CboLand.Modified())
//	             			 g.exec("update benutzer set aic_Land="+(iLand==0 ? "null":""+iLand)+" where aic_benutzer="+iB);
	             		g.SaveDefVerlauf(getBegriff(), "B"+iB+": geändert:"+(TxtKennung.Modified() ? " Kennung":"")+(CboSprache.Modified() ? " Sprache":"")+(CboLand.Modified() ? " Land":"")+
	             				(TxtBez.Modified() ? " Bez":"")+(TxtTel.Modified() ? " Tel":"")+(TxtEMail.Modified() ? " E-Mail":""));
	             		 if (TxtBez.Modified())
	             			 g.setBezeichnung("", TxtBez.getText(), g.TabTabellenname.getAic("BENUTZER"), iB, 1);
	             		 new Message(Message.INFORMATION_MESSAGE,Dlg,g,10).showDialog("changeUser");
	             	 }
//	             	 g.fixtestError("Benutzer speichern");
	             	 Dlg.dispose();
	              }
	            }
	          };
           JButton BtnOk = g.getButton("Ok","Ok",AL);
           JButton BtnAbbruch = g.getButton("Abbruch","Abbruch",AL);
           if (g.Def() && !bNew)
        	   PnlS.add(g.getButton("History","History",AL));
           PnlS.add(BtnNewPassword);
           int iPW_Art=bNew ? 0:Tab.getI("Bits") & Global.cstPW;
           if (!bNew && (iPW_Art==Global.cstPW_MD5B || iPW_Art==Global.cstPW_LTOKEN || iPW_Art==Global.cstPW_EMAIL || iPW_Art==Global.cstPW_KOMBI) && !Tab.getS("passwort").equals(g.PasswordConvert(Static.sDefaultPW,Global.PWVH,iB)))
        	   g.BtnAdd(BtnNewPassword,"Reset",AL);      	   
           else
        	   BtnNewPassword.setEnabled(false);         
           PnlS.add(BtnOk);
           PnlS.add(BtnAbbruch);
           Dlg.getRootPane().setDefaultButton(BtnOk);
           Pnl.add("South",PnlS);
          Dlg.pack();
          Static.centerComponent(Dlg, thisJFrame());
          Dlg.setVisible(true);
      }

      private void HA(boolean bAlle,boolean bBFilter)
      {
        /*if (bBFilter)
        {
          g.fixInfo("wird noch nicht unterstützt");
          return;
        }*/
        //int iAic=iStamm;
        //g.fixInfo("Stamm="+iAic);
        //String s="select distinct begriff.aic_begriff,defbezeichnung,begriff.kennung from begriff join abfrage a on begriff.aic_begriff=a.aic_begriff"+
        //    " join spalte s on a.aic_abfrage=s.aic_abfrage join spalte_zuordnung z on s.aic_spalte=z.aic_spalte and z.aic_stamm="+iStamm;
        //String s="select distinct druck.defbezeichnung Druck"+g.AU_Bezeichnung1("Stammtyp","druck")+" Stammtyp"+g.AU_Bezeichnung1("Abschnitt","ab")+" Abschnitt,b.defbezeichnung Abfrage,b.aic_begriff from begriff druck"+
            //" join druck_zuordnung dz on druck.aic_begriff=dz.aic_begriff join abschnitt ab on dz.aic_abschnitt=ab.aic_abschnitt"+
            //" join begriff b on ab.aic_begriff=b.aic_begriff join abfrage a on b.aic_begriff=a.aic_begriff"+
        String s=bBFilter ? "select distinct begriff.aic_begriff from begriff join abfrage a on begriff.aic_begriff=a.aic_begriff join bedingung b on a.aic_abfrage=b.aic_abfrage where vergleichswert=?":
            "select distinct dz.aic_begriff druck,a.aic_begriff,a.aic_abfrage from druck_zuordnung dz join abschnitt ab on dz.aic_abschnitt=ab.aic_abschnitt join abfrage a on ab.aic_begriff=a.aic_begriff"+
            " join spalte s on a.aic_abfrage=s.aic_abfrage join spalte_zuordnung z on s.aic_spalte=z.aic_spalte and z.aic_stamm"+(bAlle?"typ=?":"=?");
        g.progInfo(s);
        Tabellenspeicher Tab=new Tabellenspeicher(g,s,bAlle?""+iStammtyp:""+iStamm,true);
        String sTitel=bAlle?g.TabStammtypen.getBezeichnungS(iStammtyp):g.getStamm(iStamm);
        final AUOutliner Out=new AUOutliner(null);
        String [] s2 = new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBezeichnung("Tabellenname","STAMMTYP"),g.getBegriffS("Show","Aic"),g.getBegriffS("Checkbox","Jeder")};
        Out.setColumnButtons(s2);
        Out.setNumColumns(s2.length-(bBFilter?1:0));
        Out.setRootVisible(false);

        //Tab.showGrid(Out,"aic_begriff");
        int iAbfrage=0;
        JCOutlinerFolderNode NodP=null;
        JCOutlinerNodeStyle StyleDruck=g.getBGStype("Druck");
        JCOutlinerNodeStyle StyleAbfrage=g.getBGStype("Abfrage");
        for(Tab.moveFirst();!Tab.out();Tab.moveNext())
        {
          if (iAbfrage!=Tab.getI("aic_begriff"))
          {
            iAbfrage=Tab.getI("aic_begriff");
            int iPos=g.TabBegriffe.getPos("Aic",iAbfrage);
            if (iPos>=0)
            {
              Vector<Object> VecVisible = new Vector<Object>();
              VecVisible.addElement(g.getBegBez2(iPos));
              VecVisible.addElement(g.TabBegriffe.getS(iPos,"Kennung"));
              VecVisible.addElement(g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt")));
              VecVisible.addElement(iAbfrage);
              if (!bBFilter)
                VecVisible.addElement(Static.JaNein(Abfrage.getBA(g,"2c").getPos("aic_abfrage",Tab.getI("aic_abfrage"))>=0));
              NodP=new JCOutlinerFolderNode(VecVisible,(JCOutlinerFolderNode)Out.getRootNode());
              NodP.setUserData(bBFilter ? -iAbfrage:iAbfrage);
              NodP.setStyle(StyleAbfrage);
              NodP.setState(BWTEnum.FOLDER_CLOSED);
            }
            else
              Static.printError("Abfrage mit Begriff "+iAbfrage+" nicht gefunden!");
          }
          if (!bBFilter)
          {
            int iDruck = Tab.getI("druck");
            int iPos = g.TabBegriffe.getPos("Aic", iDruck);
            if (iPos >= 0)
            {
              Vector<Object> VecVisible = new Vector<Object>();
              VecVisible.addElement(g.getBegBez2(iPos));
              VecVisible.addElement(g.TabBegriffe.getS(iPos, "Kennung"));
              VecVisible.addElement(g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos, "Stt")));
              VecVisible.addElement(iDruck);
              JCOutlinerNode Nod = new JCOutlinerNode(VecVisible, NodP);
              Nod.setUserData(iDruck);
              Nod.setStyle(StyleDruck);
            }
            else
              Static.printError("Druck mit Begriff " + iAbfrage + " nicht gefunden!");
          }
        }
        //Tab.showGrid(sTitel,thisFrame,false,"aic_begriff");
        /*Tab.Grid.addActionListener(new JCActionListener()
        {
            public void actionPerformed(JCActionEvent ev)
            {
              JCOutlinerNode Nod = ((JCOutliner)ev.getSource()).getSelectedNode();
              int iAic=Sort.geti(Nod.getUserData());
              //g.fixInfo("Aic von Abfrage="+iAic);
              ShowAbfrage Abf = new ShowAbfrage(g, iAic, Abfrage.cstBegriff);
              DefAbfrage2.get(g, DefAbfrage2.DRUCK, Abf, (JFrame)thisFrame);
            }
        });*/
        final JDialog Dlg=new JDialog(thisJFrame(),g.getBegriffS("Dialog","HA"+(bAlle?2:1))+" "+sTitel,false);
        Dlg.getContentPane().add("Center",Out);
          //Dimension D=new Dimension(120,24);
          JButton BtnOk = g.getButton("Edit");//"Abfrage easy");
          JButton BtnAbbruch = g.getButton("Beenden");
          Dlg.getRootPane().setDefaultButton(BtnOk);
        JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
          Pnl.add(BtnOk);
          Pnl.add(BtnAbbruch);
          Dlg.getContentPane().add("South",Pnl);
          BtnOk.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              JCOutlinerNode Nod = Out.getSelectedNode();
              int iAic=Sort.geti(Nod.getUserData());
              //g.fixInfo("Aic von Abfrage="+iAic);
              if (iAic<0)
              {
                ShowAbfrage Abf = new ShowAbfrage(g, -iAic, Abfrage.cstBegriff);
                DefAbfrage.get(g, Abf, Abf.iBew == 0 ? Abf.iStt : -Abf.iBew).show();
              }
              else if (Nod.getLevel()==1)
              {
                ShowAbfrage Abf = new ShowAbfrage(g, iAic, Abfrage.cstBegriff);
                DefAbfrage2.get(g, DefAbfrage2.DRUCK, Abf, (JFrame)thisFrame);
              }
              else if (g.Def() && Nod.getLevel()==2)
              {
                Druckdefinition.get(g, true, iAic, g.TabBegriffe.getI_AIC("Stt",iAic));
              }
            }
          });
          Action cancelKeyAction = new AbstractAction()
          {
            private static final long serialVersionUID = 2477022925733839631L;

            public void actionPerformed(ActionEvent e)
            {
              Dlg.dispose();
            }
          };
          Static.Escape(BtnAbbruch,Dlg,cancelKeyAction);
          BtnAbbruch.addActionListener(cancelKeyAction);

          Dlg.pack();
          Dlg.setVisible(true);
          Out.sortByColumn(0, Sort.sortMethod);
      }

      @SuppressWarnings("unchecked")
      private void checkJoker(Abfrage Abf,JPanel Pnl,Color Col,AUTable TabAU)
      {
        if (Abf.TabJoker != null)
  	{
  	 JPanel PnlT=new JPanel(new GridLayout(0,1,0,0));
  	 if (TabJoker2==null)
  	   TabJoker2=new Tabellenspeicher(g,new String[] {"Komp","Pnl"});
  	 //int iPos=TabAU!=null ? -1:TabOutliner.getPos("Pnl", Pnl);
  	 //Vector<JComponent> Vec2=iPos>=0 ? (Vector)TabOutliner.getInhalt("Nord",iPos):null;
         if (TabEinstellung==null)
           TabEinstellung= new Tabellenspeicher(g,new String[] {"Dialog","Panel","Anzahl"});
         TabEinstellung.newLine();
         TabEinstellung.setInhalt("Panel",Pnl);
         final JDialog DlgES=new JDialog(thisFrame,g.getBegriffS("Dialog","Auswahl")+" "+(Static.bDefBezeichnung ? Abf.sDefBez:Abf.sBez));
         DlgES.setMinimumSize(new Dimension(200,150));
         TabEinstellung.setInhalt("Dialog",DlgES);
         JScrollPane Scroll=new JScrollPane(PnlT);
         Scroll.setBorder(new EmptyBorder(new Insets(5,5,0,5)));
         DlgES.getContentPane().add("Center", Scroll);
         JButton BtnBeenden=g.getButton("Beenden");
         Action cancelKeyAction = new AbstractAction() {
            private static final long serialVersionUID = 8745483376711318269L;
            public void actionPerformed(ActionEvent e) {
              DlgES.setVisible(false);
            }
          };
          Static.Escape(BtnBeenden,DlgES,cancelKeyAction);
         BtnBeenden.addActionListener(cancelKeyAction);
         JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,5,2));
         //JToolBar PnlTB=new JToolBar(JToolBar.HORIZONTAL);
         //PnlTB.setOpaque(false);
         //PnlTB.setFloatable(false);
         //PnlTB.add(new JPanel());
         PnlS.add(BtnBeenden);
         DlgES.getContentPane().add("South",PnlS);
         /*JPanel PnlButton = new JPanel(new GridLayout(1, 2, 2, 2));
         PnlButton.add(g.getButton("Ok"));
         PnlButton.add(g.getButton("Abbruch"));
         DlgES.getContentPane().add("South", PnlButton);*/
         //Abf.TabJoker.showGrid("TabJoker2");
         //g.progInfo("           ------------------ checkJoker Mandant="+g.getMandant());
  	 for(Abf.TabJoker.moveFirst();!Abf.TabJoker.out() && Abf.TabJoker.isNull("Komponente");Abf.TabJoker.moveFirst())
  	 {
  	  int iEig2=Abf.TabJoker.getI("Eigenschaft");
  	  int iAbf2=Abf.TabJoker.getI("Abfrage");
  	  int iPos2=g.TabEigenschaften.getPos("Aic", iEig2);
          /*g.testInfo("checkJoker: Eig="+iEig2+", Abf="+iAbf2+", Pos="+iPos2);
          if (iPos2<0)
          {
            Static.printError("checkJoker nicht durchführbar");
            return;
          }*/
          int iSttE=g.TabEigenschaften.getI(iPos2, "aic_stammtyp");
          int iRolleE=g.TabEigenschaften.getI(iPos2, "Rolle");
          String sT=(String)Abf.TabJoker.getInhalt("Bez");
          Tabellenspeicher Tab=null;
          ShowAbfrage Abf2=null;
          //g.progInfo("iAbf2="+iAbf2+", iSttE="+iSttE);
          if (iAbf2==0)
            Tab=new Tabellenspeicher(g,"select aic_stamm"+g.AU_Bezeichnung2("Stamm","v.aic_stamm","v.Bezeichnung")+" bezeichnung from stammview v where aic_stammtyp="+iSttE+Abfrage.Rolle(iRolleE)+g.getReadMandanten(false,"v"),true);
          else
          {
            Abf2=new ShowAbfrage(g,iAbf2,Abfrage.cstAbfrage);
            Tab=Abf2.getDaten(Abf2.iStt, 0, null,thisFrame);
          }
          //if (g.Prog())
          //  Tab.showGrid("checkJoker");
          if (Tab.isEmpty())
          {
            Tab.addInhalt("aic_stamm",-1);
            Tabellenspeicher TabSpalten=null;
            if (iAbf2!=0)
            {
              TabSpalten = Abf2.getSpalten();
              TabSpalten.moveFirst();
              //TabSpalten.showGrid();
            }
            Tab.addInhalt(iAbf2==0 ? "bezeichnung":TabSpalten.getS("Kennung"),"*");
          }
          TabEinstellung.setInhalt("Anzahl",Tab.size());
          //JPanel PnlTop=new JPanel(/*TabAU != null ? new GridLayout(0,1):*/new FlowLayout(sT != null ? FlowLayout.LEFT:FlowLayout.CENTER));

          /*if (sT != null)
          {
            JPanel PnlB=new JPanel(new BorderLayout(5,0));
            JLabel Lbl=new JLabel(sT+":");
            Lbl.setFont(g.fontTitel);
            PnlB.setBackground(Col);
            PnlB.add("West",Lbl);
            PnlB.add("Center",PnlTop);
            if (Vec2==null)
              PnlT.add(PnlB);
            else
              Vec2.addElement(PnlB);
          }
          else if (Vec2==null)
            PnlT.add(PnlTop);
          else
            Vec2.addElement(PnlTop);*/
          //if (TabAU!=null)
          //  g.progInfo("1:"+TabOutliner.getS(iPos2,"Bezeichnung")+":"+Vec2.size());
            //Color Col=sTyp==null ? g.ColTable:sTyp.equals("Haupt") ? g.ColHS:g.ColBackground;
          //PnlTop.setBackground(Col);
          String sKennung=Abf.TabJoker.getS("Kennung");
          boolean bCbx=Abf.TabJoker.getB("Cbx");
          Abf.TabJoker.clearInhalt();
          //Tab.showGrid("Tab");
          ButtonGroup RadGroup=bCbx ? null:new ButtonGroup();
          for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            javax.swing.JToggleButton C=null;
            String sBez=null;
            try
            {
              sBez=Tab.getS(1);
            }
            catch (Exception e)
            {
              sBez="???";
              g.progInfo("checkJoker: Bezeichnung nicht ermittelbar:"+e);
            }
            if (bCbx)
              C=new AUCheckBox(true,sBez);
            else
            {
              C=new JRadioButton(sBez,Tab.bof());
              RadGroup.add(C);
            }
            C.setMargin(g.inset0);
            if (TabAU!=null)
              C.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        	  int iPos=TabJoker2.getPos("Komp",ev.getSource());
                  ((AUTable)TabJoker2.getInhalt("Pnl", iPos)).Refresh();
        	}});
            else
              C.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        	  int iPos=TabJoker2.getPos("Komp",ev.getSource());
        	  FuelleOutliner((JPanel)TabJoker2.getInhalt("Pnl", iPos),0,false,true);
          	  //FuelleOutliner((JPanel)((JComponent)ev.getSource()).getParent().getParent().getParent(),0,false,true);
        	}});
            //C.setBackground(Col);
            C.setFont(g.fontStandard);
            Abf.TabJoker.addInhalt("Kennung",sKennung);
            Abf.TabJoker.addInhalt("Eigenschaft",iEig2);
            Abf.TabJoker.addInhalt("Cbx",new Boolean(bCbx));
            Abf.TabJoker.addInhalt("Komponente",C);
            Abf.TabJoker.addInhalt("Stamm",Tab.getI("aic_stamm"));
            Abf.TabJoker.addInhalt("Abfrage",iAbf2);
            Abf.TabJoker.addInhalt("Bez",sT);
            TabJoker2.addInhalt("Komp",C);
            TabJoker2.addInhalt("Pnl",TabAU != null ? TabAU:Pnl);
            PnlT.add(C);
          }

	 }
         //DlgES.pack();
         //Abf.TabJoker.showGrid("TabJoker");

  	  /*if (TabAU!=null)
            {
              //Pnl.add("North",PnlT);
              Component[] C=Pnl.getComponents();
              for (int i=0;i<C.length;i++)
                if (C[i] instanceof JToolBar)
                  ((JToolBar)C[i]).add(PnlT);
            }*/
	}
        else if (Abf.iJokerZahl>0)
        {
          if (TabJoker3==null)
  	   TabJoker3=new Tabellenspeicher(g,new String[] {"Abfrage","Komp","Anzeige"});
          JSlider Edt=new JSlider(0,Abf.iJokerZahl,Abf.iJokerZahl);
          JButton Btn=new JButton(""+Abf.iJokerZahl);
          Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
              int iPos=TabJoker3.getPos("Anzeige",ev.getSource());
              if (iPos>=0)
              {
                TabOutliner.posInhalt("Abfrage", (Abfrage)TabJoker3.getInhalt("Abfrage", iPos));
                refreshOutliner(false);
              }
            }
          });
          TabJoker3.addInhalt("Abfrage",Abf);
          TabJoker3.addInhalt("Komp",Edt);
          TabJoker3.addInhalt("Anzeige",Btn);
          JPanel PnlSL=new JPanel(new BorderLayout(3,3));
          PnlSL.add("Center",Edt);
          PnlSL.add("East",Btn);
          Pnl.add("North",PnlSL);
          Edt.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e)
            {
              int i=((JSlider)e.getSource()).getValue();
              //g.progInfo("Slider="+i);
              int iPos=TabJoker3.getPos("Komp",e.getSource());
              if (iPos>=0)
              {
                ((JButton)TabJoker3.getInhalt("Anzeige",iPos)).setText(""+i);
                ((Abfrage)TabJoker3.getInhalt("Abfrage",iPos)).iJokerZahl=i;
              }
              //Abf.iJokerZahl=i;
            }
          });
        }
        //else
        //  Pnl.add(TabAU != null ? "West":"North",new JPanel());
      }

      @SuppressWarnings("unchecked")
	private boolean FuelleOutliner(JPanel Pnl,int riGruppe,boolean rbFirst,boolean bZwingend)
	{
		long lClock = Static.get_ms();
		iFO++;
                //g.fixtestError("**FuelleOutliner:"+Pnl.hashCode()+"/"+riGruppe+"/"+rbFirst+"/"+bZwingend);
		/*if (g.Prog())
		{
			int iGruppe2=riGruppe==0?TabOutliner.posInhalt("Pnl",Pnl)?TabOutliner.getI("Gruppe"):-1:riGruppe;
			;
			//String sOutBez=g.TabBegriffe.getS("Bezeichnung");
			g.progInfo("FuelleOutliner:"+(g.TabBegriffe.posInhalt("Aic",new Integer(iGruppe2)) ? g.TabBegriffe.getS("Bezeichnung"):"???")+
                                   "/"+riGruppe+"->"+(riGruppe==0?TabOutliner.getS("Typ"):"Neu"));
		}*/
		//long lClock = Static.get_ms();
		AUOutliner Gid=null;
		//JButton BtnAnzahl=null;
                //JButton BtnMF=null;
		//JCOutlinerFolderNode NodAbfrage=null;
		int iStt=0;
		int iBew=0;
		String sTyp="";
		//int iEig2=iEigenschaft2;
		//int iStammVor=iStamm2;
		int iStammWahl=0;
                //boolean bDarstellen=true;
		//boolean bMehrfach=false;
		if(rbFirst)
		{
			int iEig=0;
			//g.debugInfo("FuelleOutliner "+iGruppe);
			int iPos=g.TabBegriffe.getPos("Aic",new Integer(riGruppe));
			int iPosG=g.TabBegriffgruppen.getPos("Aic",g.TabBegriffe.getInhalt("Gruppe",iPos));
			String sBG=iPosG>=0?g.TabBegriffgruppen.getS(iPosG,"Kennung"):"";
			if (!sBG.equals("Abfrage"))
				g.printError("EingabeFormular.FuelleOutliner: keine Abfrage",getBegriff());
			iStt = g.TabBegriffe.getI(iPos,"Stt");
			iBew = g.TabBegriffe.getI(iPos,"Erf");
                        long lBits=g.TabBegriffe.getL(iPos,"bits");
                        boolean bEinzeldaten=(lBits&Abfrage.cstEinzeldaten)>0 && (lBits&Abfrage.cstTTO)==0;
                        //boolean bKeineLeiste=true; //(lBits&Abfrage.cstKeineLeiste)>0;
			sTyp= g.TabCodes.getKennung(g.TabBegriffe.getI(iPos,"Typ"));
			String sBegriff=g.TabBegriffe.getS(iPos,"Bezeichnung");
                        //Color Col=sTyp.equals("Haupt")?g.ColHS:g.ColBackground;
			if (sTyp.equals("Haupt") && bHaupt || sTyp.equals("Entfernen") && bEntfernen)
			{
				iEig = -riGruppe;
				g.printError("EingabeFormular.FuelleOutliner: Tabelle "+sBegriff+" von "+sTyp+" auf Anzeige geändert!",getBegriff());
				sTyp= "Anzeige";
			}

			if (sTyp.equals("Haupt"))
				bHaupt=true;
			else if (sTyp.equals("Entfernen"))
				bEntfernen=true;
			else if(sTyp.equals("Anzeige") || sTyp.equals("Gruppe"))	//(iBew != 0)
				iEig = -riGruppe;
			else if(sTyp.equals("Mehrfach") || sTyp.equals("Auswahl"))	//(iStt != 0)
			{
				SQL Qry = new SQL(g);
				Qry.open("select e.aic_eigenschaft from begriff"+g.join("eigenschaft","e","Begriff","begriff")+" join "+
                                         (sTyp.equals("Auswahl") && Bewegungstyp()>0?"bew_zuordnung z where z.aic_bewegungstyp="+Bewegungstyp():"stammtyp_zuordnung z on z.aic_eigenschaft=e.aic_eigenschaft where z.aic_stammtyp="+iStammtyp)+
                                         " and e.aic_stammtyp="+iStt+" and begriff.kennung"+(sTyp.equals("Mehrfach")?"=":"<>")+"'Mehrfach' order by z.reihenfolge");
				//TabEigenschaft.showGrid();
				while (iEig==0 && !Qry.eof())
				{
					if (!TabEigenschaft.existInhalt("Aic",new Integer(Qry.getI("aic_eigenschaft"))))
						iEig=Qry.getI("aic_eigenschaft");
					Qry.moveNext();
				}
				Qry.free();
				if (iEig == 0)
				{
					iEig = -riGruppe;
					g.printError("EingabeFormular.FuelleOutliner: Tabelle "+sBegriff+" von "+sTyp+" auf Anzeige geändert!",getBegriff());
					sTyp= "Anzeige";
				}
			}
			Gid=new AUOutliner();//new JCOutlinerFolderNode("JCOutliner"));
                        ShowAbfrage.initOutliner(g,Gid);
                        Gid.setColumnLabelSort((lBits&Abfrage.cstNoSort)==0);
                        //Gid.setMinimumSize(new Dimension(40,15));
                        //Gid.setRootNode(new JCOutlinerFolderNode(null));
			/*Gid.setFont(g.fontBezeichnung);
			Gid.setRootNode(new JCOutlinerFolderNode(""));
			Gid.setColumnLabelSortMethod(Sort.sortMethod);
			Gid.setRootVisible(false);//sTyp.equals("Auswahl"));*/

			Gid.setAllowMultipleSelections(sTyp.equals("Anzeige") || sTyp.equals("Entfernen") || isMultiselect() && sTyp.equals("Haupt"));
			//Pnl.add("Center",Gid);
			//JPanel PnlNorth = new JPanel(new BorderLayout());
			//JPanel PnlEast = new JPanel(new GridLayout());

			//g.printInfo("Gruppe="+g.TabBegriffe.getS("Bezeichnung"));
			iBew = iBew==0 && iStt==0 ? Bewegungstyp():iBew;
			iStt = iStt==0 ? iStammtyp:iStt;
			//g.TabEigenschaften.showGrid();
			//g.debugInfo(""+iEig+"/"+Typ()+"/"+Bewegung);
			//if (iEig>0)
			//	g.TabEigenschaften.posInhalt("Aic",iEig);
			//g.debugInfo(g.TabEigenschaften.getS("Bezeichnung")+":"+g.TabEigenschaften.getPos()+"/"+iEigenschaft+"/"+iEig);
			//JButton BtnStammtyp = new JButton(iEig<0 ? sBegriff:iEig==0 ? g.getBezeichnung("Stammtyp",iStt):g.TabEigenschaften.posInhalt("Aic",iEig) ? g.TabEigenschaften.getS("Bezeichnung"):"Eig"+iEig);

			//sBegriff=sBegriff+(g.Def()?" ("+(sTyp.equals("Haupt")?'H':sTyp.equals("Mehrfach")?'M':sTyp.equals("Anzeige")?'A':
			//	sTyp.equals("Auswahl")?'W':sTyp.equals("Entfernen")?'L':'?')+")":"");
			//JButton BtnStammtyp = new JButton(sTyp.equals("Haupt")||sTyp.equals("Entfernen")?"* "+sBegriff+" *":sBegriff);
			/*JButton BtnStammtyp = new JButton(getAbfrageTitel(sBegriff,sTyp));
			BtnStammtyp.setFont(g.fontBezeichnung);
                        BtnStammtyp.setMargin(g.inset);
                        BtnStammtyp.setBackground(Col);*/
			//JButton BtnInit=g.getButton("Init");
                        ///BtnInit.setMargin(g.inset);
                        //BtnInit.setBackground(Col);
			/*BtnAnzahl = new JButton("xx");
                        BtnAnzahl.setMargin(g.inset);
                        BtnAnzahl.setBackground(Col);
			BtnAnzahl.setFont(g.fontBezeichnung);*/

                        boolean bExist=TabOutliner.posInhalt("Pnl",Pnl);
                        if (bExist)
                        {
                          TabOutliner.setInhalt("Gruppe", riGruppe);
                          TabOutliner.setInhalt("Typ", sTyp);
                        }
                        else
                        {
                          TabOutliner.newLine();
                          TabOutliner.setInhalt("Pnl", Pnl);
                          TabOutliner.setInhalt("Gruppe", riGruppe);
                          TabOutliner.setInhalt("Typ", sTyp);
                          TabOutliner.setInhalt("Nord",new Vector());
                          TabOutliner.setInhalt("von",g.getVon());
                          TabOutliner.setInhalt("bis",g.getBis());
                        }
			//JCOutlinerFolderNode Nod=Abfrage.InitNode(g,true);
			//SQL Qry = new SQL(g);
			//Qry.open("Select Modell.Aic_Begriff,Aic_Abfrage,bits from modell right outer join Abfrage join begriff where abfrage.aic_begriff="+riGruppe);
			//new Abfrage(g,Qry.getI("Aic_Abfrage"),Nod);

			//int iAbfNeu=g.getAbfrage(getBegriff(),riGruppe);
                        int iAbfNeu=g.getAbfrage(PnlToAic(Pnl));
                        if (iAbfNeu==0)
                          iAbfNeu=riGruppe;
                        else if (iAbfNeu != riGruppe)
			{
				sBegriff=g.TabBegriffe.getBezeichnungS(iAbfNeu);
				//BtnStammtyp.setText(getAbfrageTitel(sBegriff,sTyp));
			}
			ShowAbfrage Abf=new ShowAbfrage(g,iAbfNeu,Abfrage.cstBegriff);
			checkJoker(Abf,Pnl,sTyp.equals("Haupt") ? g.ColHS:g.ColBackground,null);
                        if (sTyp.equals("Haupt"))
                          bKeinNeu=(Abf.iBits&Abf.cstKeinNeu)>0;
                        //if ((Abf.iBits&Abf.cstFeiertage)>0 && !Static.bDefBezeichnung)
                        //  Gid.setBackground(Gid.getBackground().brighter());
                        if (Abf.Fehler())
			{
				g.printError("Abfrage mit Begriff "+iAbfNeu+" nicht gefunden!",getBegriff());
				if(iAbfNeu != riGruppe)
					Abf=new ShowAbfrage(g,riGruppe,Abfrage.cstBegriff);
			}
                        if (bEinzeldaten)
                        {
                          JPanel PnlWest=new JPanel(new GridLayout(0,1));
                          int iPos2=TabAbf.getPos("Aic",Abf.iBegriff);
                          int iSpalten=iPos2<0 ? 1:TabAbf.getI(iPos2,"Spalten");
                          JPanel PnlCenter=new JPanel(new GridLayout(0,Static.bTest ? iSpalten:1));
                          JPanel Pnl2 = new JPanel(new BorderLayout(0,0));
                          //Pnl2.setBorder(new EmptyBorder(4,2,2,2));
                          Pnl2.setBackground(Static.ColEF);
                          Pnl.add(Pnl2);
                          if (!Static.bTest)
                            Pnl2.add("West",PnlWest);
                          Pnl2.add("Center",PnlCenter);
                          Tabellenspeicher TabSpalten = Abf.getSpalten();
                          //JPanel Pnl11;
                          //JPanel Pnl22;
                          boolean bEFirst=true;
                          for (TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                            if ((TabSpalten.getI("Bits")&Abfrage.cstUnsichtbar)==0)
                          {
                            //Pnl11 = new JPanel(new BorderLayout(0, 0));
                            //Pnl22 = new JPanel(new BorderLayout(0, 0));
                            //String s=TabSpalten.getS("Bezeichnung");
                            JLabel Lbl=new JLabel(TabSpalten.getS("Datentyp").equals("Filler") || (TabSpalten.getI("bits3")&Abfrage.cstSpKeinTitel)>0  ? "":" "+TabSpalten.getS("Bezeichnung")+": ");
                            Color Col=g.setColor(Color.BLACK,TabSpalten.getI("Farbe"));
                            Lbl.setForeground(Col);
                            boolean bEdit=!ReadOnly() && (TabSpalten.getI("bits")&Abfrage.cstEditierbar)>0 && Abf.iStt==iStammtyp && Abf.iBew==Bewegungstyp() && TabSpalten.getS("Kennung").indexOf("_")<0;
                            if (bEFirst && !Static.bTest && !bEdit)
                            {
                              TabOutliner.setInhalt("Gid", Lbl);
                              addPopup(Lbl);
                              bEFirst = false;
                            }
                            JLabel Lbl2=new JLabel(""/*TabSpalten.getS("Kennung")*/);
                            //Lbl.setFont(Pnl.getFont() == g.fontStandard ? g.fontBezeichnung : Pnl.getFont());
                            Lbl.setFont(g.fontBezeichnung);
                            Lbl2.setFont(Pnl.getFont());
                            Lbl2.setForeground(Col);
                            //Lbl.setBorder(new EmptyBorder(4, 0, 0, 0));
                            //Lbl2.setBorder(new EmptyBorder(4, 0, 0, 0));
                            int iAlign=g.getAlignment(TabSpalten.getS("Datentyp"),TabSpalten.getI("Ausrichtung"));
                            Lbl2.setHorizontalAlignment(iAlign==BWTEnum.TOPCENTER ? SwingConstants.CENTER:iAlign==BWTEnum.TOPRIGHT ? SwingConstants.RIGHT:SwingConstants.LEFT);
                            if (Static.bTest)
                              Lbl2.setVerticalAlignment(SwingConstants.BOTTOM);
                            //Lbl2.setHorizontalAlignment(SwingConstants.LEFT);
                            //Lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
                            //Pnl11.add("North", Lbl);
                            //Pnl22.add("North", Lbl2);
                            TabED.addInhalt("Abfrage",Abf.iAbfrage);
                            TabED.addInhalt("Kennung",TabSpalten.getS("Kennung"));
                            Text Edt=null;
                            if (bEdit)
                            {
                              Edt=new Text("",30);
                              TabED.addInhalt("Lbl", Edt);
                            }
                            else
                              TabED.addInhalt("Lbl",Lbl2);
                            PnlWest.setBackground(Static.ColEF);
                            PnlCenter.setBackground(Static.ColEF);
                            //PnlWest.add(Pnl11);
                            //PnlCenter.add(Pnl22);
                            if (!Static.bTest && !bEdit)
                            {
                              PnlWest.add(Lbl);
                              PnlCenter.add(Lbl2);
                            }
                            else
                            {
                              if (!Static.bTest)
                                PnlWest.add(new JLabel());
                              if (bEdit)
                              {
                                JPanel PnlN=new JPanel(new BorderLayout());
                                addEig(true, null, TabSpalten, PnlN, TabSpalten.getS("Datentyp"));
                                PnlCenter.add(PnlN);
                              }
                              else
                              {
                                EF_Eingabe EF = new EF_Eingabe(g, bEdit ? Edt : Lbl2, "", TabSpalten.getS("Bezeichnung"), bEdit, false, TabSpalten.getM("Tooltip"),
                                    TabSpalten.getS("Datentyp"));
                                /*JPanel PnlN=new JPanel(new BorderLayout());
                                PnlN.add("North",EF);
                                PnlN.add("Center",new JLabel("x"));
                                PnlN.add("South",new JLabel("-"));
                                PnlCenter.add(PnlN);*/
                                PnlCenter.add(EF);
                                if (bEFirst)
                                {
                                  TabOutliner.setInhalt("Gid", EF); //Lbl);
                                  addPopup(EF);
                                  bEFirst = false;
                                }
                              }
                            }
                          }
                        }
                        else if (!bExist)
                        {
                          TabOutliner.setInhalt("Gid",Gid);
                          Pnl.add("Center", Gid);
                          Gid.setFocusable(false);
                          addPopup(Gid);
                        }
                        else
                          Gid=(AUOutliner)TabOutliner.getInhalt("Gid");

			//int iModell=Qry.getI("Aic_Begriff");
			//TabOutliner.addInhalt("Modell",iModell==0? null:Typ() == Stamm && Abfrage.is(Qry.getI("bits"),Abfrage.cstMengen) ?
			//	new Calc(null,g,iModell,VecAic==null?SQL.getVector("select aic_stamm from stammview where aic_stammtyp="+iStammtyp+g.getReadMandanten(false),g):VecAic,false):new Calc(null,g,iModell,iStamm));
                        /*if (sTyp.equals("Mehrfach") || iBew>0 && Abf.iModell==0)
                        {
                          BtnMF = sTyp.equals("Mehrfach") ? g.getButton("*"):g.getButton("H");
                          ///BtnMF.setMargin(g.inset);
                          BtnMF.setBackground(Col);
                          //PnlEast.add(BtnMF);
                        }*/
                        //PnlEast.add(BtnAnzahl);
                        if (bExist)
                        {
                          TabOutliner.setInhalt("Abfrage", Abf);
                          TabOutliner.setInhalt("Stt", iStt);
                          TabOutliner.setInhalt("Eig", iEig);
                          TabOutliner.setInhalt("Bezeichnung", sBegriff);
                        }
                        else
                        {
                          TabOutliner.setInhalt("Abfrage", Abf);
                          TabOutliner.setInhalt("Stt", iStt);
                          //TabOutliner.addInhalt("Bew",iBew);
                          TabOutliner.setInhalt("Eig", iEig);
                          //TabOutliner.addInhalt("bits",Abf.iBits);
                          //TabOutliner.addInhalt("Stamm",-1);
                          TabOutliner.setInhalt("Bezeichnung", sBegriff);
                          TabOutliner.setInhalt("Timer",null);
                          TabOutliner.setInhalt("Tab",null);
                          TabOutliner.posInhalt("Eig", iEig);
                        }
                        if (Abf.iTop>0 && (Abf.iBits&Abfrage.cstFirst)==0)
                        {
                          javax.swing.Timer timer;
                          timer = new javax.swing.Timer(Abf.iTop*1000, new ActionListener()
                          {
                            public void actionPerformed(ActionEvent ev)
                            {
                              if (thisFrame.isVisible())
                              {
                                bRefreshEigenschaften = false;
                                TabOutliner.push();
                                if (TabOutliner.posInhalt("Timer", ev.getSource()))
                                {
                                  g.progInfo("Refresh "+TabOutliner.getS("Bezeichnung"));
                                  //? g.setAbfrage(getBegriff(), TabOutliner.getI("Gruppe"), ((Abfrage)TabOutliner.getInhalt("Abfrage")).iBegriff);
                                  FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"), 0, false, true);
                                }
                                //SummeBilden((AUOutliner)TabOutliner.getInhalt("Gid"));
                                TabOutliner.pop();
                                bRefreshEigenschaften = true;
                                checkRepaint();
                              }
                            }
                          });
                          TabOutliner.setInhalt("Timer",timer);
                          timer.start();
                        }
			//g.printInfo(2,sBegriff+": Modell="+Abf.iModell+", bits="+Abf.iBits);
			//Qry.free();
                        /*if (Static.bBilder && g.TabStammtypen.posInhalt("Aic",iStt))
                        {
                          Image Gif=(Image)g.TabStammtypen.getInhalt("Bild");
                          if (Gif != null)
                            BtnStammtyp.setIcon(new ImageIcon(Gif));
                        }
			BtnStammtyp.setToolTipText(g.getBegriff("Button","Refresh"));
			BtnStammtyp.addActionListener(new ActionListener()		// Refresh-Knopf
			{
				public void actionPerformed(ActionEvent ev)
				{
					//g.TabEigenschaften.showGrid();
					//g.printInfo(">>Speicher:"+Runtime.getRuntime().freeMemory()+"/"+Runtime.getRuntime().totalMemory());
					bRefreshEigenschaften = false;
                                        if (TabOutliner.posInhalt("Btn1",ev.getSource()))
					{
                                          String sBegriff=g.TabBegriffe.getBezeichnung(((Abfrage)TabOutliner.getInhalt("Abfrage")).iBegriff);
                                          //g.TabCodes.posInhalt("Aic",g.TabBegriffe.getI("Typ"));
                                          //String sTyp= g.TabCodes.getS("Kennung");
                                          //TabOutliner.setInhalt("Typ",sTyp);
                                          String sTyp=TabOutliner.getS("Typ");
                                          ((JButton)ev.getSource()).setText(getAbfrageTitel(sBegriff,sTyp));

						g.setAbfrage(getBegriff(),TabOutliner.getI("Gruppe"),((Abfrage)TabOutliner.getInhalt("Abfrage")).iBegriff);
						FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
					}
					bRefreshEigenschaften = true;
				}
			});*/
			/*BtnInit.addActionListener(new ActionListener()		// Init-Knopf
			{
				public void actionPerformed(ActionEvent ev)
				{
					bRefreshEigenschaften = false;
					if (TabOutliner.posInhalt("Init",ev.getSource()))
					{
						int iBegriff=TabOutliner.getI("Gruppe");
						g.setAbfrage(getBegriff(),iBegriff,iBegriff);
						TabOutliner.setInhalt("Abfrage",new ShowAbfrage(g,iBegriff,Abfrage.cstBegriff));
						FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
					}
					bRefreshEigenschaften = true;
				}
			});*/
			//PnlNorth.add("Center",BtnStammtyp);
			/*if ((Abf.iBits&Abf.cstSuchen)>0)
			{
				Text EdtSuche=new Text(g.getJoker(),30);
				TabOutliner.setInhalt("Suche",EdtSuche);
				//PnlNorth.add("South",EdtSuche);
				EdtSuche.addKeyListener(new KeyListener()
				{
					public void keyPressed(KeyEvent e)
					{}
					public void keyReleased(KeyEvent e)
					{}
					public void keyTyped(KeyEvent e)
					{
						char cKey=e.getKeyChar();
						if(cKey==10)
						{
							e.consume();
							g.setStringJoker(((Text)e.getSource()).getText()+"%");
							//bRefreshEigenschaften = false;
							if (TabOutliner.posInhalt("Suche",e.getSource()))
							{
								g.setAbfrage(getBegriff(),TabOutliner.getI("Gruppe"),((Abfrage)TabOutliner.getInhalt("Abfrage")).iBegriff);
								FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
							}
							//bRefreshEigenschaften = true;
						}
					}
				});

			}*/
			//PnlEast.add(BtnInit);

			if(sTyp.equals("Mehrfach") && Typ()==Bewegung || sTyp.equals("Auswahl"))
			{
                          TabEigenschaft.newLine();
				TabEigenschaft.setInhalt("Aic",iEig);
				TabEigenschaft.setInhalt("Datentyp",sTyp.equals("Mehrfach")?"OutlinerM":"Outliner");
				TabEigenschaft.setInhalt("Komponente",Gid);
				TabEigenschaft.setInhalt("Bez",g.TabEigenschaften.getBezeichnungS(iEig));
				TabEigenschaft.setInhalt("History",null);
				TabEigenschaft.setInhalt("Stammtyp",iStt);
                                //TabEigenschaft.setInhalt("Rolle",g.TabEigenschaften.getInhalt("Rolle"));
				TabEigenschaft.setInhalt("Stamm",null);
				TabEigenschaft.setInhalt("bits",null);
				TabEigenschaft.setInhalt("bits2",null);
				TabEigenschaft.setInhalt("bits3",null);
				TabEigenschaft.setInhalt("EF",null);
                                TabEigenschaft.setInhalt("Spalte",null);
                                TabEigenschaft.setInhalt("bed",null);
				/*if (sTyp.equals("Auswahl"))
				{
					JButton Btn = g.getButton("*");//= new JButton("...");
					//PnlEast.add(Btn);
                                        Btn.setBackground(g.ColEF);
					TabEigenschaft.setInhalt("Sub",Btn);
					Btn.addActionListener( new ActionListener()	// Knopf für Sub-Formular
					{
						public void actionPerformed( ActionEvent e)
						{
							long lClock0 = Static.get_ms();
							if (TabEigenschaft.posInhalt("Sub",e.getSource()))
								EingabeFormular.HoleFormular(g,0,TabEigenschaft.getI("Stammtyp"),null,0);
							g.clockInfo("SubFormular - "+TabEigenschaft.getS("Kennung"),lClock0);
						}
					});
				}
				else
					TabEigenschaft.setInhalt("Sub",null);*/
				/*if (g.History())
				{
					JButton BtnH = g.getButton("H");
                                        BtnH.setFocusable(false);
					//PnlEast.add(BtnH);
					TabEigenschaft.setInhalt("History",BtnH);
					//BtnH.setMargin(inset);
					BtnH.addActionListener( new ActionListener()
					{
						public void actionPerformed( ActionEvent e)
						{
							if (TabEigenschaft.posInhalt("History",e.getSource()))
							{
								ShowAbfrage.showHistory(g,self,TabEigenschaft.getS("Bez"),TabEigenschaft.getI("Aic"),iStamm,TabEigenschaft.getS("Datentyp"),
									Typ()==Bewegung && TabEigenschaft.posInhalt("Datentyp","BewDatum") ? ((Datum)TabEigenschaft.getInhalt("Komponente")).getDateTime() : null,Bewegungstyp());
							//showHistory(e);
							}
						}
					});
				}
				else*/

			}

			/*if(sTyp.equals("Mehrfach"))
			{
				BtnMF.addActionListener( new ActionListener()	// Mehrfachauswahl-Knopf
				{
					public void actionPerformed( ActionEvent ev)
					{
						if (TabOutliner.posInhalt("MF",ev.getSource()))
						{
							if (iStamm==0)
								new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("StammFehlt");
							else
                                                        {
                                                          JCOutlinerNode Nod=((JCOutliner)TabOutliner.getInhalt("Gid")).getSelectedNode();
                                                          int iTel= Nod==null || Nod.getLevel()==0 ? 0:((Integer)Nod.getUserData()).intValue();
                                                          EingabeFormular.HoleFormular(g, TabOutliner.getI("Stt"),iTel,iStamm,TabOutliner.getI("Eig"),sBezeichnung);
                                                        }
						}
					}
				});
				BtnMF.setToolTipText(g.getBegriff("Button","Eingabe"));
			}
                        else if (iBew>0 && Abf.iModell==0)
                        {
                          BtnMF.addActionListener( new ActionListener()	// Mehrfachauswahl-Knopf
                          {
                            public void actionPerformed( ActionEvent ev)
                            {
                              if (TabOutliner.posInhalt("MF",ev.getSource()))
                              {
                                ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
                                JCOutlinerNode Nod=((JCOutliner)TabOutliner.getInhalt("Gid")).getSelectedNode();
                                int iPool= Nod==null || Nod.getLevel()==0 ? 0:((Integer)Nod.getUserData()).intValue();
                                if (iPool>0)
                                  Abf.showHistory2(self,iPool,0,null);
                              }
                            }
                          });
                          BtnMF.setToolTipText(g.getBegriff("Button","H"));
                        }*/
			/*if(g.Abfrage() && (!sTyp.equals("Haupt") || iStamm2==0))	//nicht bei Hauptliste von Nebenfenster
			{
				BtnAnzahl.addActionListener(new ActionListener()	// Abfrage-Knopf
				{
					public void actionPerformed(ActionEvent ev)
					{
						if (TabOutliner.posInhalt("Btn2",ev.getSource()))
						{
							ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
							if (g.Def() || (Abf.iBits&Abf.cstNoChange)==0)
								All_Unlimited.Grunddefinition.DefAbfrage.get(g,Abf,Abf.iBew==0?Abf.iStt:-Abf.iBew).show();

							Abfrage Abf= Abfrage.Zeigen(g,TabOutliner.getI("Bew")!=0 ? Bewegung:Stamm,TabOutliner.getI("Bew")!=0 ? TabOutliner.getI("Bew"):TabOutliner.getI("Stt"),(JCOutlinerFolderNode)TabOutliner.getInhalt("Node"),(JFrame)thisFrame);
							if (Abf != null && Abf.isOkConfirm())
							{
								if (TabOutliner.getInhalt("Modell") != null)
									TabOutliner.setInhalt("Modell",new Calc(null,g,SQL.getInteger(g,"Select Modell.Aic_Begriff from Abfrage join Modell where abfrage.aic_begriff="+TabOutliner.getI("Gruppe")),iStamm));
								FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
							}
							//g.printInfo("fertig");
						}
					}
				});
				BtnAnzahl.setToolTipText(g.getBegriff("Button","Abfrage"));
			}*/
			//PnlNorth.add("East",PnlEast);
                        //if (!bKeineLeiste & !bEinzeldaten)
                        //  Pnl.add("North",PnlNorth);

		}
		else	// Alle weiteren Aufrufe
		{
			if (TabOutliner.posInhalt("Pnl",Pnl))
			{
				Gid=TabOutliner.getInhalt("Gid") instanceof AUOutliner ? (AUOutliner)TabOutliner.getInhalt("Gid"):null;
				//BtnAnzahl=(JButton)TabOutliner.getInhalt("Btn2");
				//NodAbfrage=(JCOutlinerFolderNode)TabOutliner.getInhalt("Node");
				//g.printInfo("NodAbfrage="+NodAbfrage);
				iStt=TabOutliner.getI("Stt");
				//iBew=TabOutliner.getI("Bew");
				//iEig=TabOutliner.getI("Eig");
				sTyp=TabOutliner.getS("Typ");
				//iStammWahl= iTyp==Bewegung && iEig == 0 ? 0:iGruppe; //bei Hauptliste von Bewegung nichts vorwählen
				iStammWahl = riGruppe;
				//iGruppe=TabOutliner.getI("Gruppe");
				//g.TabBegriffe.posInhalt("Aic",TabOutliner.getInhalt("Gruppe"));
				//g.printInfo(2," ..refresh Outliner: "+TabOutliner.getS("Bezeichnung"));
				//g.progInfo("weitere Aufrufe:"+g.TabBegriffe.getS("Bezeichnung")+": Typ="+sTyp+",Eig2="+iEigenschaft2);
				if (sTyp.equals(""))
				{
					g.printError("EingabeFormular.FuelleOutliner: Typ von "+TabOutliner.getS("Bezeichnung")+" nicht definiert!",getBegriff());
					return false;
				}
				//bMehrfach=iEig>0 && g.TabEigenschaften.posInhalt("Aic",iEig) && g.TabEigenschaften.getS("Datentyp").equals("Mehrfach");
			}
			else
			{
				g.printError("EingabeFormular.FuelleOutliner: Pnl "+Pnl+" von FuelleOutliner nicht gefunden!",getBegriff());
				return false;
			}
		}

		//             //
		//   I M M E R //
		//             //

		//g.printInfo("0:"+Pnl.getComponent(0));
		//g.printInfo("1:"+((JPanel)Pnl.getComponent(1)).getComponent(0));
		//g.printInfo("2:"+((JPanel)Pnl.getComponent(1)).getComponent(1));
		//if (bDarstellen)
		//{
			/*JFrame F = new JFrame(""+iEig);
			F.getContentPane().add("Center",new JCOutliner((JCOutlinerNode)((JCOutlinerFolderNode)TabOutliner.getInhalt("Node")).clone()));
			F.show();*/
			/*if(sTyp.equals("Mehrfach"))
			{
				iStammVor=iStamm;
				//iEig2=iEig;
			}*/
			ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
			boolean bVA1=(Abf.iBits&Abfrage.cstKeinAustritt)>0;
                        if (!bVA1)
                        {
                          boolean bVA2 = g.isAbfrageP(PnlToAic(TabOutliner.getInhalt("Pnl")), Global.VAUS, bVA1) || iRolle==0;
                          if(bVA1 != bVA2)
                            if(bVA2)
                              Abf.iBits += Abfrage.cstKeinAustritt;
                            else
                              Abf.iBits -= Abfrage.cstKeinAustritt;
                        }
			//if(Abfrage.is(Abf.iBits,Abfrage.cstVerteiler))
			//	iVerteiler=TabOutliner.getI("Eig");
			boolean bKeinStamm=Abfrage.is(Abf.iBits,Abfrage.cstKeinStamm);
			int iStammVor = sTyp.equals("Mehrfach") ? iStamm<=0 ? 999999999:iStamm:iStamm2;
			boolean bVecAic = iDaten==VECTOR && iStammVor==0 && Abf.iBew==0 && (bKeinStamm || !sTyp.equals("Anzeige"));
			/*if (bVecAic)
				g.testInfo("-FuelleOutliner:"+TabOutliner.getS("Bezeichnung")+" - VecAic wird verwendet");
			else
			{
				int iStammNew=iStammVor>0?iStammVor:bKeinStamm?0:iStamm;
				g.progInfo(" !!! "+iStammVor+"/"+iStamm+"=="+TabOutliner.getI("Stamm")+"->"+iStammNew);
				if (bZwingend || iStammNew !=TabOutliner.getI("Stamm"))
					TabOutliner.setInhalt("Stamm",iStammNew);
				else
				{
					iFO--;
					g.testInfo("-FuelleOutliner:"+TabOutliner.getS("Bezeichnung")+" - Stamm ist gleich!  Zeit:"+(Static.get_ms()-lClock));
					return;
				}
			}*/

			JCOutlinerFolderNode NodRoot = null;
                        if (Gid != null)
                        {
                          NodRoot = (JCOutlinerFolderNode)Gid.getRootNode();
                          if (!rbFirst || bZwingend)
                            NodRoot.removeChildren();
                        }
			//g.printInfo("iStammVor="+iStammVor);
			//int iAbfrage=0;
			Tabellenspeicher TabSpalten = null;
			if(!sTyp.equals("Mehrfach") || iStamm != 0)	// Mehrfach ohne Stamm nicht möglich
			{
				Tabellenspeicher Tab;
				TabSpalten = Abf.getSpalten();
				//if (g.Prog())
				//	TabSpalten.showGrid("Spalten");
				Vector VecTitel = Abf.getTitel();
                                if (sTyp.equals("Haupt"))
                                {
                                  bImport=(Abf.iBits&Abfrage.cstStempelimport)>0;
                                  //g.fixInfo("bImport="+bImport);
                                }
                                //boolean bAktiv=g.isAbfrageAktiv(getBegriff(), TabOutliner.getI("Gruppe"));
                                boolean bEinzeldaten=(Abf.iBits&Abfrage.cstEinzeldaten)>0 && (Abf.iBits&Abfrage.cstTTO)==0;
                                //g.fixInfo(TabOutliner.getS("Bezeichnung")+":"+sTyp+"/"+bEinzeldaten);
                                boolean bAktiv=sTyp.equals("Haupt") || (bEinzeldaten || g.isAbfrageAktiv(PnlToAic(TabOutliner.getInhalt("Pnl")))) &&
                                    (((JPanel)TabOutliner.getInhalt("Pnl")).isShowing() || (Transact.iInfos&Transact.NO_SPEED)>0);
                                if (Gid != null)
                                {
                                  if ((Abf.iBits&Abfrage.cstEinzeldaten)==0)
                                  {
                                    Abf.bMulti=bMulti;
                                    //g.fixInfo(Abf.sDefBez+".set Multi1:"+bMulti);
                                    Abf.checkVecBezeichnung();//bMulti);
                                    Abf.setOutlinerButtons(0, TabSpalten, Gid, Abf.getBezeichnung());
                                  }
                                  //boolean bFT=(Abf.iBits&Abf.cstFeiertage)>0 && !Static.bDefBezeichnung;
                                  if (sTyp.equals("Haupt"))
                                    Gid.setBackground(/*bFT ? g.ColHS.brighter():*/g.ColHS);
                                  else
                                    Gid.setBackground(bAktiv ? /*bFT ? g.ColBackground.brighter():*/g.ColBackground:g.ColHide);
                                }
                                else if (bEinzeldaten)
                                {
                                  Color Col=bAktiv ? g.ColBackground:g.ColHide;
                                  //g.fixInfo(TabOutliner.getS("Bezeichnung")+":"+TabOutliner.getInhalt("Gid")+"->"+Col);
                                  Object Obj=TabOutliner.getInhalt("Gid");
                                  if (Obj instanceof EF_Eingabe)
                                    ((EF_Eingabe)Obj).setBackground(Col);
                                  else if(Obj instanceof JLabel)
                                    ((JLabel)Obj).setBackground(Col);
                                }
                                int iPosO=TabOutliner.getPos("Eig",0);
                                if (iPosO>=0)
                                {
                                  AUOutliner GidHO = (AUOutliner)TabOutliner.getInhalt("Gid",iPosO);
                                  VecSelect = g.getAics(GidHO);
                                }
                                else if (!sTyp.equals("Haupt") && (Abf.iBits&Abf.cstMengen)>0)
                                {
                                  VecSelect=iDaten==ALLE || iDaten==FIRMA ? SQL.getVector("select aic_stamm from stammview p2 where aic_stammtyp="+iStammtyp+
                                      (iDaten==FIRMA ?" and firma="+g.getFirma():"")+Rolle()+g.getReadMandanten(),g):new Vector<Integer>(VecAic);
                                  Vector VecStamm=g.getVecStamm(getBegriff(),iStammtyp);
                                  if (VecStamm != null && VecSelect !=null)
                                	  for (int i=VecSelect.size()-1;i>=0;i--)
                                		  if (!VecStamm.contains(VecSelect.elementAt(i)))
                                			  VecSelect.remove(i);
                                  //g.fixtestError("VecSelect neu ermitteln für "+Abf.sDefBez+": "+VecSelect);
                                }
                                g.progInfo(Abf.sDefBez+":"+sTyp+"/"+VecSelect);
                                if (Modell2(Abf,0))
                                {
                                  Tab = null;
                                  Gid.setBackground(g.ColHide);
                                }
                                else
                                  Tab=!bAktiv || rbFirst && !bZwingend && !sTyp.equals("Haupt") && !sTyp.equals("Entfernen") && (Abf.iBits&Abfrage.cstSynchron)==0 ? null:
                                	Abf.getEF_Daten(getBegriff(),Abf.iStt==0 ? iStammtyp:Abf.iStt,iRolle,iStammVor,bMulti?-1:iStamm,Typ() == Bewegung?iSatz:-2,bVecAic,bBewVec,iDaten==FIRMA && sTyp.equals("Haupt"),
                                                        !sTyp.equals("Haupt") ? VecSelect:iDaten==VECTOR  ? VecAic:null,sTyp,sTyp.equals("Haupt")?iEigenschaft2:TabOutliner.getI("Eig"),bZwingend,kopierbar(),thisFrame);
                                if (g.Def())
                                  TabOutliner.setInhalt("Tab",Tab);
                                TabOutliner.setInhalt("von",g.getVon());
                                TabOutliner.setInhalt("bis",g.getBis());
                                //String sTest=TabOutliner.getS("Bezeichnung");
                                if ((Abf.iBits&Abfrage.cstAusblenden)>0)
                                {
                                  boolean b=Tab!=null && !Tab.isEmpty() || g.Def() && Static.bDefBezeichnung;
                                  //g.fixInfo(" ************** Ausblenden: Visible="+b);
                                  Pnl.setVisible(b);
                                  //Pnl.getParent().setVisible(b);
                                  //Pnl.getParent().getParent().setVisible(b);
                                }
                                //g.progInfo("Tab="+Tab);
                                //if (Gid != null && !Gid.isEnabled())
                                //{
                                  //Gid.setEnabled(true);
                                  //Gid.setBackground(g.ColBackground);
                                //}
                                if (Tab==null || !rbFirst &&Tab.isEmpty())
				{
                                  //g.fixInfo("Tab=null:"+TabOutliner.getS("Bezeichnung"));
                                  if (bEinzeldaten)
                                  {
                                    //g.testInfo("bEinzeldaten löschen");
                                    //TabED.showGrid("TabED");
                                    for(TabED.posInhalt("Abfrage",Abf.iAbfrage);!TabED.eof() && TabED.getI("Abfrage")==Abf.iAbfrage;TabED.moveNext())
                                    {
                                      //g.testInfo("lösche "+TabED.getInhalt("Lbl"));
                                      Object Obj=TabED.getInhalt("Lbl");
                                      if (Obj instanceof JLabel)
                                      {
                                        ((JLabel)TabED.getInhalt("Lbl")).setText("");
                                        ((JLabel)TabED.getInhalt("Lbl")).setIcon(null);
                                      }
                                      else
                                        ((Text)TabED.getInhalt("Lbl")).setText("");
                                    }
                                  }
                                  else
                                  {
                                    //g.testInfo("keine Einzeldaten");
                                    Gid.setRootVisible(false);
                                    ((JCOutlinerFolderNode)Gid.getRootNode()).removeChildren();
                                    if ((Abf.iBits&Abfrage.cstEinzeldaten)==0)
                                    {
                                      //g.testInfo("OutlinerEvents");
                                      OutlinerEvents(TabSpalten, rbFirst, sTyp);
                                    }
                                    if (Tab==null)
                                    {
                                      //g.progInfo(" ----------- Disable "+Abf.sDefBez);
                                      Gid.setBackground(g.ColHide);
                                      //Gid.setEnabled(false);
                                    }
                                  }

                                  //g.printInfo(1,"Abbruch, da Tabelle = null");
                                  //g.testInfo("Abbruch, da Tabelle = null:"+TabOutliner.getS("Bezeichnung"));
                                  Clock.add(clock,"Out",TabOutliner.getS("Bezeichnung"),lClock);
                                  return false;
				}
                                else if (sTyp.equals("Gruppe") || (Abf.iBits&Abfrage.cstTTO)>0)
                                {
                                  ShowAbfrage.initOutliner(g,Gid);
                                  //Tab.showGrid("Gruppe");
                                  int iPos=TabAbf.getPos("Aic",Abf.iBegriff);
                                  int iSpalten=iPos<0 ? 1:TabAbf.getI(iPos,"Spalten");
                                  //Abf.bMulti=bMulti;
                                  //g.fixInfo(Abf.sDefBez+".set Multi2:"+bMulti);
                                  Abf.TabToOutliner(Gid,Tab,null,null,iSpalten);
                                  Gid.folderChanged(NodRoot);
                                  //Gid.setAllowMultipleSelections(true);
                                  //Gid.repaint();
                                  //Tab.showGrid("Daten");
                                  //TabSpalten.showGrid("Spalten");
                                  Clock.add(clock,"Out",TabOutliner.getS("Bezeichnung"),lClock);
                                  return false;
                                }

				//if (g.Prog())
				//	Tab.showGrid("Tab-Abfrage"+iAbfrage);
				Vector<Object> VecSpalten;
				int iAnzahl=0;
				if (!sTyp.equals("Mehrfach") && iStammWahl == 0 && !rbFirst && !bEinzeldaten)
				{
					JCOutlinerNode NodFocus = Gid.getSelectedNode();
					iStammWahl = NodFocus == null || NodFocus.getLevel()==0 ? 0 : Sort.geti(NodFocus.getUserData());//((Integer)NodFocus.getUserData()).intValue();
				}

				JCOutlinerNode NodSelect=null;
				Tab.moveFirst();

				JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();//g.getSttStyle(iStt);
				//JCOutlinerNodeStyle StyAustritt=null;
				if (StyFolder == null)
					StyFolder = new JCOutlinerComponent().getDefaultNodeStyle();
				//StyFolder.setShortcut(true);	//	!!!
				StyFolder.setForeground(g.ColStandard);
				//StyFolder.setFont(g.fontStandard);
				//g.setSchrift(Abf.iBegriff,StyFolder);
				g.setSchrift2(g.TabBegriffe.getPos("Aic",Abf.iBegriff),StyFolder,g.getOutFont(false));//g.fontStandard);
				//JCOutlinerNodeStyle StyHoliday = new JCOutlinerNodeStyle(StyFolder);
				//StyHoliday.setForeground(g.ColHoliday);
                                //JCOutlinerNodeStyle StyDel = new JCOutlinerNodeStyle(StyFolder);
                                //StyDel.setForeground(g.ColLoeschen);
                                boolean bFertig=bEinzeldaten && (Abf.iBits&Abfrage.cstSynchron)==0;
                                boolean bWertOk=true;
                                java.awt.Event ev=null;
                                //if (bEinzeldaten)
                                //    Tab.showGrid(Abf.sDefBez);
                                if (bMulti)
                                {
                                  ev=new java.awt.Event(null,0,null);
                                  ev.modifiers=java.awt.Event.CTRL_MASK;
                                }
                                //Tabellenspeicher TabSync=null;
                                if (AllSync() && Abf.iBew>0)
                                {
                                  Abf.TabSync=new Tabellenspeicher(g,"select aic_bew_pool,anr from bew_pool where aic_bew_pool"+Static.SQL_in(Tab.getVecSpalte("aic_bew_pool")),true);
                                  Abf.TabSync.posInhalt("anr",g.getSyncStamm(g.iSttANR,g.iRolMA));
                                  //TabSync.showGrid("TabSync");
                                }
				while (!Tab.eof())
				{
					int iCol=-1;
					boolean bAbfSumme=Abfrage.is(Abf.iBits,Abfrage.cstSumme);
					int iSysAic = bAbfSumme ? 0:Abf.iBew==0 ? Tab.getI("aic_stamm") : Tab.getI("aic_Bew_Pool");
                                        if (Abf.iBew>0 && (Abf.iBits&Abfrage.cstDistinct)>0 && (Abf.iBits&Abfrage.cstEinzeldaten)==0) //Typ() == Stamm && sTyp.equals("Haupt"))
                                        {
                                          TabSpalten.moveFirst();
                                          int iSys=Tab.getI("v"+TabSpalten.getS("Kennung2"));
                                          //g.progInfo("ändere SysAic von "+iSysAic+" auf "+iSys);
                                          iSysAic=iSys;
                                        }
					VecSpalten = new Vector<Object>();
					Date date=null;
					if (bEinzeldaten)
					{
					  TabED.posInhalt("Abfrage",Abf.iAbfrage);
					  if ((Abf.iBits&Abfrage.cstSynchron)>1)
					  {
					    bWertOk=false;
					    if (Abf.iBew>0)
					    {
					      int iPos=g.TabErfassungstypen.getPos("Aic", Abf.iBew);
					      if (iPos>=0)
						bWertOk=g.TabErfassungstypen.getI(iPos,"Pool")==iSysAic;
					    }
					    else if (Abf.iBew==0)
					    {
					      int iPos=g.TabStammtypen.getPos("Aic", Abf.iStt);
					      if (iPos>=0)
						bWertOk=g.TabStammtypen.getI(iPos,"Stamm")==iSysAic;
					    }
					    bFertig=bWertOk;
					  }
					  else if (Abf.iBew>0 && Abf.iBew==Bewegungstyp())
					  {
					    bFertig=iSysAic==iSatz;
					  }
					}
					int i=0;
					for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
					  if ((TabSpalten.getI("Bits")&Abfrage.cstUnsichtbar)==0 && ((TabSpalten.getI("Bits2")&Abfrage.cstMulti)==0 || bMulti))
					  {
						//boolean bGruppe=TabSpalten.getInhalt("Gruppe")!=null;
						//boolean bPeriode=(TabSpalten.getI("bits")&Abfrage.cstPeriode)>0  && Zeitraum.VecPerioden!=null;;
						//int iAnz=bGruppe ? ((Vector)TabSpalten.getInhalt("Gruppe")).size(): bPeriode ? Zeitraum.VecPerioden.size()-1:1;
						Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr");
						int iAnz=VecMehr==null?1:VecMehr.size();
						//int i=VecSpalten.size();
						for(int iPer=0;iPer<iAnz;iPer++)
						{
							String sAic=VecTitel==null?VecMehr!=null ? "e"+TabSpalten.getS("Kennung2")+VecMehr.elementAt(iPer):TabSpalten.getS("Kennung"):VecTitel.elementAt(i).toString();
                                                        Object Obj=TabSpalten.getS("Datentyp").equals("Farbe") ? new JaNein(Tab.getInhalt(TabSpalten.getS("Kennung"))!=null):
								Abf.TabToScreen(g,sAic,Tab,TabSpalten,VecMehr!=null || bAbfSumme);
                                                        if (bEinzeldaten && bWertOk)
                                                        {
                                                          Object Obj2=TabED.getInhalt("Lbl");
                                                          if (Obj2 instanceof JLabel)
                                                          {
                                                            if (Obj == null)
                                                            {
                                                              ((JLabel)Obj2).setIcon(null);
                                                              ((JLabel)Obj2).setText("");
                                                            }
                                                            else if (Obj instanceof Image)
                                                              ((JLabel)Obj2).setIcon(new ImageIcon((Image)Obj));
                                                            else if (Obj2 instanceof JLabel)
                                                              ((JLabel)Obj2).setText("" + Obj);
                                                          }
                                                          //else
                                                          //  ((Text)Obj2).setText(""+Obj);
                                                          //g.progInfo("setze Einzeldaten von "+TabED.getS("Kennung")+" auf "+Obj);
                                                          TabED.moveNext();
                                                        }
                                                        else
                                                          VecSpalten.addElement(Obj);
                                                        i++;
						}
						boolean bRowAvg=VecMehr != null && (TabSpalten.getI("bits3")&Abfrage.cstPerSchnitt)>0;
						if(VecMehr != null && (TabSpalten.getI("bits")&Abfrage.cstPeriodensumme)>0 || bRowAvg)
						{
							double d=0;
							for(int iPer=VecSpalten.size()-iAnz;iPer<VecSpalten.size();iPer++)
								d+=Sort.getf(VecSpalten.elementAt(iPer));
							if (bRowAvg)
								d/=iAnz;
							VecSpalten.addElement(ShowAbfrage.doubleToScreen(g,d,TabSpalten));
						}
						if (TabSpalten.getS("Datentyp").equals("BewDatum"))
							date=(Date)Tab.getInhalt(TabSpalten.getS("Kennung"));
						else if (TabSpalten.getS("Datentyp").equals("Farbe"))
							iCol=Tab.getI(TabSpalten.getS("Kennung"));
                                          }
                                          else
                                            i+=TabSpalten.isNull("mehr")?1:((Vector)TabSpalten.getInhalt("mehr")).size();
                                        if (NodRoot != null)
                                        {
                                          JCOutlinerNode Nod = new JCOutlinerNode(VecSpalten, NodRoot);
                                          //if (StyFolder !=null)
                                          //	Nod.setStyle(StyFolder);
                                          Nod.setStyle(Abf.getStyle(Abf,Tab,StyFolder,date,Tab.exists("ANR") ? Tab.getI("ANR"):0,iCol));
                                          /*if (Abf.bEntfernte && !Tab.isNull("pro_aic_protokoll"))
                                            Nod.setStyle(g.StyDel);
                                          else if (Abf.iBew==0 && Abf.iStt==g.iSttANR)
                                          {
                                            if (StyAustritt==null)
                                            {
                                              StyAustritt = new JCOutlinerNodeStyle(StyFolder);
                                              StyAustritt.setForeground(g.ColAustritt);
                                            }
                                            Nod.setStyle(ShowAbfrage.istDa(g,Tab.getI("aic_stamm")) ? StyFolder:StyAustritt);
                                          }
                                          else if ((Abf.iBits & Abfrage.cstFeiertage) == 0 || date == null)
                                          {
                                            if (iCol != -1)
                                            {
                                              StyFolder = new JCOutlinerNodeStyle(StyFolder);
                                              StyFolder.setForeground(new Color(iCol));
                                            }
                                            Nod.setStyle(StyFolder);
                                          }
                                          else
                                          {
                                            DateWOD dw = new DateWOD(date);
                                            int iD=dw.getDay();
                                            Color Col=!g.Feiertag(dw).equals("") ? g.ColHoliday:
                                                    iD==Calendar.MONDAY ? g.ColMo2: iD==Calendar.TUESDAY ? g.ColDi2:iD==Calendar.WEDNESDAY ? g.ColMi2:iD==Calendar.THURSDAY ? g.ColDo2:
                                                    iD==Calendar.FRIDAY ? g.ColFr2:iD==Calendar.SATURDAY ? g.ColSa2:iD==Calendar.SUNDAY ? g.ColSo2:null;
                                            JCOutlinerNodeStyle Sty = null;
                                            if (Col != null)
                                            {
                                              Sty = new JCOutlinerNodeStyle(StyFolder);
                                              //Sty.setBackgroundSelected(Col);
                                              Sty.setForeground(Col);
                                            }
                                            Nod.setStyle(Col==null ? StyFolder:Sty);
                                          }*/

                                          Nod.setUserData(new Integer(iSysAic));
                                          if (bMulti && VecSelect.contains(iSysAic))
                                          {
                                            Gid.selectNode(Nod,ev);
                                          }
                                          else if (Abf.TabSync!=null)
                                          {
                                            if (iSysAic==Abf.TabSync.getI("aic_bew_pool"))
                                              NodSelect = Nod;
                                          }
                                          else if (!sTyp.equals("Mehrfach") && iStammWahl == iSysAic)
                                          {
                                            NodSelect = Nod;
                                          }
                                        }
					iAnzahl++;
                                        if (bFertig)
                                          Tab.moveLast();
                                        else
                                          Tab.moveNext();
				}
				//BtnAnzahl.setText(""+iAnzahl);
				//g.progInfo("Gid.getNodeHeight2="+Gid.getNodeHeight());
				/*if ((Abf.iBits&Abf.cstMZ)>0)
				{
					Gid.setNodeHeight(50);
					//Gid.setColumnAlignments(Abf.getAusrichtung());
				}*/
//				g.fixtestError("FuelleOutliner bei "+TabOutliner.getS("Bezeichnung"));
				if(iAnzahl>0 && NodSelect == null)
				{
//					g.fixtestError("iStammWahl="+iStammWahl+", iAnzahl="+iAnzahl+", NodSelect="+NodSelect);
					if (Gid != null)
                                          Gid.folderChanged(NodRoot);
                                        //g.progInfo("xxxxxxxxxxxxxx           iStammWahl="+iStammWahl+", iSatz="+iSatz);
                                        if (iStammWahl>0)
                                        {
                                          Clock.add(clock,"Out",TabOutliner.getS("Bezeichnung"),lClock);
                                          SummeBilden(Gid);
//                                          g.fixtestError("Summe bilden und return bei "+TabOutliner.getS("Bezeichnung"));
                                          return false;
                                        }
					//g.progInfo("folderChanged");
				}
				if(NodSelect != null && (((Frame)thisFrame).isVisible()) && !bMulti)// && !StammAusBew())
				{
				  Static.makeVisible(Gid, NodSelect,true);
				  //g.fixtestError("makeVisible3:"+iStamm+"/"+NodSelect);
				}
			}
                        else
                          Gid.repaint();
//TODO PRÜFEN ob OutlinerEvents immer
                        if(!bZwingend)
                          OutlinerEvents(TabSpalten,rbFirst,sTyp);
                        //g.progInfo("  ---------------------------------- <"+TabOutliner.getS("Bezeichnung")+">: rbFirst="+rbFirst+", Typ="+sTyp+", Synchron="+(Abf.iBits&Abf.cstSynchron));
                        /*g.progInfo(" xxxxxxxxxxxxxxxxxxxxxxxxxxx       "+Gid.getName());
                        Gid.setName("x");
                        g.progInfo(" xxxxxxxxxxxxxxxxxxxxxxxxxxx       "+Gid.getName());*/
                        if (Gid != null && !Gid.getName().endsWith("+"))
                        {
                          Gid.setName(Gid.getName()+"+");
                          //Knoten();
                          g.progInfo("   xxxxxxxxxxxxxxxx   JCOutliner="+Gid.getName()+"/"+sTyp);
			  if (sTyp.equals("Haupt"))
				Gid.addItemListener(new JCOutlinerListener()
				{
					public void itemStateChanged(JCItemEvent e) {                         						
//						if (DlgSuche !=null)
//							DlgSuche.dispose();
//						g.fixtestInfo("GidHaupt.itemStateChanged:"+e.getItem()+"/"+e.getSource()+"/"+e.getStateChange());
					}

					public void outlinerFolderStateChangeBegin(JCOutlinerEvent e){}
					public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)  {}

					public void outlinerNodeSelectBegin(JCOutlinerEvent e){
                                          //g.fixInfo("outlinerNodeSelectBegin"+e.getStateChange()+":"+((JCOutliner)e.getSource()).getSelectedNode()+"/"+((JCOutliner)e.getSource()).getFocusNode());
                                          if (Modified())
                                          {
                                            e.setAllowChange(false);
                                            if (((JCOutliner)e.getSource()).getSelectedNode() != null)
                                            {
                                              //new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("speichernZuerst");
                                              Speicherfrage();
                                              ((JCOutliner)e.getSource()).setFocusNode(((JCOutliner)e.getSource()).getSelectedNode(),null);
                                            }
                                          }
                                        }

					public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
					{
                                          //g.fixInfo("outlinerNodeSelectEnd"+ev.getStateChange()+":"+((JCOutliner)ev.getSource()).getSelectedNode());
                                          /*if (Modified())
                                          {
                                            g.fixInfo("es gibt Änderungen");
                                          }
                                          else*/
											//g.fixtestError("outlinerNodeSelectEnd: bkHE="+bkHE);
						//g.fixtestError("Selectiert von Haupt:"+g.getAics((JCOutliner)ev.getSource()));
                                            if (!bkHE)
                                            {              
                                            	if (DlgSuche !=null)
                        							DlgSuche.dispose();
                                              Hauptoutliner_Event((AUOutliner)ev.getSource(),ev.getStateChange() == ItemEvent.SELECTED);
                                            }
					}
				});
                        else if (AllSync())
                          Gid.addItemListener(new JCItemListener()
                          {
                            public void itemStateChanged(JCItemEvent ev)
                            {
                              Object Obj = ((AUOutliner)ev.getSource()).getSelectedNode().getUserData();
                              TabOutliner.posInhalt("Gid", ev.getSource());
                              ShowAbfrage Abf = ((ShowAbfrage)TabOutliner.getInhalt("Abfrage"));
                              if (Abf.TabSync != null)
                              {
                                if (Abf.TabSync.posInhalt("aic_bew_pool",Obj))
                                {
                                  g.progInfo("setSyncStamm2");
                                  g.setSyncStamm(g.iSttANR, Abf.TabSync.getI("anr"),g.iRolMA);
                                }
                              }
                            }
                          });
			else if ((Abf.iBits&Abfrage.cstSynchron)>0)
				Gid.addItemListener(new JCItemListener()
				{
					public void itemStateChanged(JCItemEvent ev)
					{
                                          if (ev.getStateChange() == ItemEvent.SELECTED)
                                          {
                                            Object Obj = ((AUOutliner)ev.getSource()).getSelectedNode().getUserData();
                                            if (Obj != null && Obj instanceof Integer)
                                            {
                                              int iAic = ((Integer)Obj).intValue();
                                              if (iAic > 0)
                                              {
                                                TabOutliner.posInhalt("Gid", ev.getSource());
                                                ShowAbfrage Abf = ((ShowAbfrage)TabOutliner.getInhalt("Abfrage"));
                                                //g.testInfo("vor Sync:"+iAic+" Bew="+Abf.iBew+", Typ="+TabOutliner.getS("Typ")+"/"+Typ());
                                                if (Abf.iBew == 0)// || TabOutliner.getS("Typ").equals("Haupt") && Typ() == Stamm)
                                                {
                                                  //if (Abf.iBew == 0)
                                                  //{
                                                    g.progInfo("setSyncStamm3");
                                                    g.setSyncStamm(Abf.iStt, iAic,iRolle);
                                                    if ((Abf.lBits2&Abf.cstkeinSyncVec)==0)
                                                    	g.setSyncStamm(Abf.iStt, Static.AicToVec(iAic));
                                                  //}
                                                  /*else
                                                  {
                                                    int iStamm=SQL.getInteger(g,"select anr from bew_pool where aic_bew_pool=?",-1,""+iAic);
                                                    g.defInfo(sFormularBezeichnung + ": Setze Stt " + iStammtyp + " auf Pool " + iStamm);
                                                    g.setSyncStamm(iStammtyp, iStamm);
                                                    g.setSyncStamm(iStammtyp, Static.AicToVec(iStamm));
                                                  }*/
                                                }
                                                else if (Abf.iBew > 0)
                                                {
                                                  int iPos = g.TabErfassungstypen.getPos("Aic", Abf.iBew);
                                                  if (iPos >= 0)
                                                  {
                                                    g.defInfo(sFormularBezeichnung + ": Setze Bew " + Abf.iBew + " auf Pool " + iAic);
                                                    g.TabErfassungstypen.setInhalt(iPos, "Pool", iAic);
                                                  }
                                                }
                                              }
                                            }
                                          }
					}
				});
                            }
		//g.testInfo("*"+iFO+"*"+getTitle()+": refresh Outliner "+TabOutliner.getS("Bezeichnung")+": Stt="+iStt+", Bew="+Abf.iBew+", Zeit:"+(Static.get_ms()-lClock));
                if (bZwingend)
                  SummeBilden(Gid);
                Clock.add(clock,"Out",TabOutliner.getS("Bezeichnung"),lClock);
                return true;
	}//FuelleOutliner

        private void Hauptoutliner_Event(AUOutliner GidHO,boolean bSelect)
        {
//        	g.fixtestError("Hauptoutliner_Event");
          //if (bOutEvent)
          //lClockµ=Static.get_µs()-lClockµ;
          //g.fixInfo("*outlinerNodeSelectEnd:"+lClockµ);
          if ( /*ev.getStateChange() == ItemEvent.SELECTED &&*/!bCalc)
          {
//        	  g.fixtestError("  in if von Hauptoutliner_Event");
            long lClock = Clock.startClock(clock);
            bRefreshMom=true;
          //TODO Sync laut Spalten setzen
            int iPos=TabOutliner.getPos("Gid", GidHO);
            ShowAbfrage Abf = ((ShowAbfrage)TabOutliner.getInhalt("Abfrage",iPos));
            Tabellenspeicher TabSpalten= Abf.getSpalten();
            for(TabSpalten.moveFirst();!TabSpalten.out();TabSpalten.moveNext())
            	if ((TabSpalten.getI("bits2")&Abfrage.cstSetSync)>0)
            	{
            		g.setSyncStamm(TabSpalten.getI("Stt"),Sort.geti(GidHO.getSelectedNode().getLabel(), TabSpalten.getPos()),0);
            		//g.fixtestError("Sync gehört gesetzt: Stt="+TabSpalten.getI("Stt")+": "+Static.print(((Vector)GidHO.getSelectedNode().getLabel()).elementAt(TabSpalten.getPos())));
            	}
            
            Vector<Integer> VecAics=g.getAics(GidHO);
            Object Obj = GidHO.getFocusNode()==null ? null:GidHO.getFocusNode().getUserData();
            if (isMultiselect() && (VecAics == null || Obj==null || !VecAics.contains(Obj)))
              return;
//            g.fixtestError("  nach return bei Hauptoutliner_Event");
            //g.testInfo("******************++ Haupt1:itemStateChanged="+((AUOutliner)ev.getSource()).getSelectedNode().getUserData()+"/"+VecSelect);

            Obj = GidHO.getSelectedNode().getUserData();
            String s = Obj == null ? "" :"" + ((Vector)GidHO.getSelectedNode().getLabel()).elementAt(0);
            Integer iAic = ((Integer)Obj);
            
            if (isMultiselect())
            {
              VecSelect = g.getAics(GidHO);
              //Object Obj2=GidHO.getFocusNode().getUserData();
              //g.progInfo("--------------xxxxxxxxxxxx----------- "+Obj2+"/"+VecSelect+"/"+VecSelect.contains(Obj2));
              /*new Vector<Integer>();
                                                              JCOutlinerNode[] Nodes=((AUOutliner)ev.getSource()).getSelectedNodes();
                                                              for (int i=0;i<Nodes.length;i++)
                                                                      VecSelect.addElement((Integer)Nodes[i].getUserData());*/
             //Gid.folderChanged(Gid.getRootNode());
             //((AUOutliner)ev.getSource()).repaint();
             //g.progInfo("VecSelect="+VecSelect);
            }
            else
              VecSelect = iAic == null ? iDaten==ALLE || VecAic == null ? null : new Vector<Integer>(VecAic) : Static.AicToVec(iAic);
            if (Synchron() && (thisFrame.isVisible())) // || !isMultiselect())) // 19.10. entfernt weil Fibu nicht mehr geht
              if (Typ() == Stamm)
              {
                //int iPos=g.TabStammtypen.getPos("Aic", iStammtyp);
                //g.setSyncStamm(iStammtyp,iAic);
                if ( /*iPos>=0 &&*/iAic != null && Tabellenspeicher.geti(iAic) > 0)
                {
                  //g.progInfo("Stt "+iStammtyp+": Sync-Stamm="+iAic);
                  //int iPosO=TabOutliner.getPos("Gid", ev.getSource());
                  //ShowAbfrage Abf = ((ShowAbfrage)TabOutliner.getInhalt("Abfrage",iPosO));
                  //if (Abf.iBew>0)
                  //  iStamm=SQL.getInteger(g,"select anr from bew_pool where aic_bew_pool=?",-1,""+iAic);
                  //g.progInfo("setSyncStamm1");
                  g.setSyncStamm(iStammtyp, /*Abf.iBew>0?iStamm:*/ iAic,iRolle);
                  //if (Abf.iBew==0)
                  if ((Abf.lBits2 & Abfrage.cstkeinSyncVec)==0)
                	  g.setSyncStamm(iStammtyp, isMultiselect() ? VecSelect : Static.AicToVec(iAic)); //Abf.iBew>0?iStamm:iAic));
                  //g.TabStammtypen.setInhalt(iPos,"Stamm",Abf.iBew>0?iStamm:iAic);
                  //if (iAic != null)
                  //g.setSyncStamm(iStammtyp, isMultiselect() ? VecSelect:Static.AicToVec(Abf.iBew>0?iStamm:iAic));
                }
              }
              else if (Typ() == Bewegung && iAic != null)
              {
                int iPosB = g.TabErfassungstypen.getPos("Aic", Bewegungstyp());
                if (iPosB >= 0)
                {
                  g.defInfo(sFormularBezeichnung + ": Setze Bew " + Bewegungstyp() + " auf Pool " + iAic);
                  g.TabErfassungstypen.setInhalt(iPosB, "Pool", iAic);
                }
              }
            //int iPos=TabOutliner.getPos("Gid", GidHO);
            if (iPos>-1)
            {
              //ShowAbfrage Abf = ((ShowAbfrage)TabOutliner.getInhalt("Abfrage",iPos));
              // if (Abf.iDtJ > 0 && GidHO.getFocusNode()!=null)
              // {
              //   Object Obj2 = ((Vector)GidHO.getFocusNode().getLabel()).elementAt(Abf.iDtJ);
              //   if (Obj2 instanceof Zeit)
              //   {
              //     g.dtStichtag = ((Zeit)Obj2).getDate();
              //     g.progInfo("Joker=" + g.dtStichtag);
              //     bReLoad=true;
              //   }
              //   else
              //     Static.printError("EingabeFormular.Hauptoutliner_Event: "+Static.print(Obj2)+" statt Zeit");
              // }
            }
            if (bRefreshEigenschaften && bSelect)
            {
              if (Obj != null && Obj instanceof Integer)
              {
                g.saveSqlTime("Fom1", 0, -1, sFormularBezeichnung, getBegriff(), iAic.intValue());
                g.printInfo(2,"FuelleEigenschaften Hauptoutliner_Event");
                bMakeVisible=false;
                FuelleEigenschaften(iAic.intValue(), StammAusBew());
                bMakeVisible=true;
                g.saveSqlTime("Fom2", 0, Static.get_ms() - lClock, sFormularBezeichnung, getBegriff(), iStamm);
              }
            }
            bRefreshMom=false;
            Clock.showClock(g, clock, "  EF Anwahl - " + s, lClock);
          }
          //g.fixInfo("******************++ Haupt2:itemStateChanged="+Sort.gets2(getAics((AUOutliner)ev.getSource())));
        }

	private void OutlinerEvents(Tabellenspeicher TabSpalten,boolean rbFirst,String sTyp)
	{
		//TabOutliner.push();
		//TabOutliner.showGrid("TabOutliner");
		//TabOutliner.pop();
		//g.progInfo("OutlinerEvents: TabOutliner.pos="+TabOutliner.getPos());
		//g.progInfo("*** OutlinerEvents "+TabOutliner.getS("Bezeichnung")+" ,Spalten:"+(TabSpalten != null)+" ,Abfrage:"+(TabOutliner.getInhalt("Abfrage")!=null));
		//g.progInfo("bits="+TabOutliner.getI("bits")+"/"+(TabSpalten==null));
//		g.fixtestError("OutlinerEvents bei "+TabOutliner.getS("Bezeichnung"));
		Abfrage Abf=(Abfrage)TabOutliner.getInhalt("Abfrage");
		if (Abf!=null && (Abf.iBits&(Abfrage.cstSumme+Abfrage.cstTTO+Abfrage.cstEinzeldaten+Abfrage.cstKeineGesamtsumme))==0/*!Abfrage.is(Abf.iBits,Abfrage.cstSumme)*/ && TabSpalten != null)
				{
					boolean bSumme=false;
					for(TabSpalten.moveFirst();!TabSpalten.eof() && !bSumme;TabSpalten.moveNext())
						bSumme = TabSpalten.getI("Ergebnisart")>0;

					// Einzelsummen
					if (bSumme)
					{
                                         if (TabOutliner.getInhalt("Gid") instanceof AUOutliner)
                                         {
                                           AUOutliner Gid = (AUOutliner)TabOutliner.getInhalt("Gid");
                                           if (rbFirst && sTyp.equals("Anzeige"))
                                           {
                                             g.progInfo("****Event für Summenbildung:" + TabOutliner.getS("Bezeichnung"));
                                             Gid.addItemListener(new JCOutlinerListener()
                                             {
                                               public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev) {}
                                               public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)   {}
                                               public void outlinerNodeSelectBegin(JCOutlinerEvent ev)        {}
                                               public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
                                               {
                                                 SummeBilden((AUOutliner)ev.getSource());
                                               }
                                               public void itemStateChanged(JCItemEvent ev)                   {}
                                             });
                                           }
                                           SummeBilden(Gid);
                                         }
					}
				}
	}

	private void SummeBilden(AUOutliner Gid)
	{
		if (Gid==null)
          return;
		TabOutliner.posInhalt("Gid",Gid);
		//g.fixtestError("SummeBilden von "+TabOutliner.getS("Bezeichnung"));
		if (Gid.getRootNode().getChildren()==null)
        {
          Gid.setRootVisible(false);
          return;
        }
        
        ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
        if (Abf==null || (Abf.iBits&(Abfrage.cstSumme+Abfrage.cstTTO+Abfrage.cstEinzeldaten+Abfrage.cstKeineGesamtsumme))>0)
            return;
//        g.fixtestError("***Summe bilden:"+TabOutliner.getS("Bezeichnung"));
		Tabellenspeicher TabSpalten= Abf.getSpalten();
		Object[] Nod2=!Gid.getAllowMultipleSelections() || Gid.getSelectedNode()==null || Gid.getSelectedNode().getLevel()==0 ?
                    Gid.getRootNode().getChildren()==null?null:Gid.getRootNode().getChildren().getArrayCopy():Gid.getSelectedNodes();
                boolean bSumme=false;
                for(TabSpalten.moveFirst();!TabSpalten.eof() && !bSumme;TabSpalten.moveNext())
                        bSumme = TabSpalten.getI("Ergebnisart")>0;
		Gid.setRootVisible(Nod2!=null && bSumme);
		if (Nod2==null || !bSumme)
			return;
		if (bSumme)
		{
			Gid.setRootVisible(true);
			Vector<Object> VecSpalten = new Vector<Object>();
			TabSpalten.moveFirst();
			//if (sTyp.equals("Haupt") && iBew==0)
			//{
			//	VecSpalten.addElement(g.getBezeichnung("Stammtyp",iStt));
			//	TabSpalten.moveNext();
			//}
                        Object[] Nod=Nod2.clone();
                        int iN=1;
                        Object NodMom=Nod[0];
                        for(int i=1;i<Nod2.length;i++)
                        {
                          if (Nod2[i] !=NodMom)
                          {
                            iN++;
                            NodMom=Nod2[i];
                            Nod[iN-1]=Nod2[i];
                          }
                        }
                        //for(int i=0;i<iN;i++)
                        //  g.progInfo(i+"="+Nod[i]+Nod[i].hashCode());
			for(;!TabSpalten.eof();TabSpalten.moveNext())
			{
				if(TabSpalten.getI("Ergebnisart")>0 && iN>0 && (TabSpalten.getI("bits")&(Abfrage.cstPeriode+Abfrage.cstUnsichtbar))==0)	// bei Periode noch Probleme
				{
					//g.TabCodes.posInhalt("Aic",TabSpalten.getI("Ergebnisart"));
					String sErgebnisart = g.TabCodes.getKennung(TabSpalten.getI("Ergebnisart"));
					//String sDatentyp = TabSpalten.getS("Datentyp");
					int iPos=TabSpalten.getPos();
                                        Vector Vec=(Vector)((JCOutlinerNode)Nod[0]).getLabel();
					double d=sErgebnisart.equals("count") || Vec.size()<=iPos ? 0.0:Sort.getf(Vec.elementAt(iPos));
					//String sSpalte=(sDatentyp.equals("BewVon_Bis")? "D":"")+TabSpalten.getS("Kennung");
					if (sErgebnisart.equals("sum"))
						for(int i=1;i<iN;i++)
							d+=Sort.getf(((Vector)((JCOutlinerNode)Nod[i]).getLabel()).elementAt(iPos));
					else if (sErgebnisart.equals("min"))
						for(int i=1;i<iN;i++)
						{
							d=Math.min(Sort.getf(((Vector)((JCOutlinerNode)Nod[i]).getLabel()).elementAt(iPos)),d);
							//g.progInfo(i+":"+d);
						}
					else if (sErgebnisart.equals("max"))
						for(int i=1;i<iN;i++)
							d=Math.max(Sort.getf(((Vector)((JCOutlinerNode)Nod[i]).getLabel()).elementAt(iPos)),d);
					else if (sErgebnisart.equals("avg"))
					{
						for(int i=1;i<iN;i++)
							d+=Sort.getf(((Vector)((JCOutlinerNode)Nod[i]).getLabel()).elementAt(iPos));
						d/=iN;
					}
					else if (sErgebnisart.equals("count"))
						d=iN;
					else
						g.printError("EingabeFormular.FuelleOutliner: Ergebnisart <"+sErgebnisart+"> steht noch nicht zur Verfügung!",getBegriff());
                                        //g.progInfo(TabSpalten.getS("Bezeichnung")+"("+TabSpalten.getPos()+"):"+d+"/"+sErgebnisart);
					VecSpalten.addElement(sErgebnisart.equals("count")?new Zahl1(d,"#"):ShowAbfrage.doubleToScreen(g,d,TabSpalten));
				}
				else
					VecSpalten.addElement("");
			}
			Gid.getRootNode().setLabel(VecSpalten);
			Gid.getRootNode().setUserData(Static.Int0);
			//Gid.folderChanged(Gid.getRootNode());
		}
	}//SummeBilden

        /*private boolean EA_Rolle(int iAic)
        {
          int iPos=g.TabEigenschaften.getPos("Aic",iAic);
          return iPos>=0 && iRolle != g.TabEigenschaften.getI(iPos,"Rolle");
        }*/

	private String SQLEigenschaftsDaten(int iMax)
	{
		//Ausgabe("vor SQLEigenschaftsDaten");
		String s="";
		String sPool1 = Typ()==Stamm ? "p.aic_stamm=s.aic_stamm":Typ()==Bewegung?"p.aic_bew_pool=b.aic_bew_pool":"";
		String sPool = sPool1+" and p.aic_eigenschaft=";
		int iPos =TabEigenschaft.getPos()+1;
                int iAnzx=0;

		while(!TabEigenschaft.eof() && TabEigenschaft.getPos()<iMax)
		{
                  //int iAic=TabEigenschaft.getI("Aic");
		  int iPos2=g.TabEigenschaften.getPos("Aic",TabEigenschaft.getI("Aic"));

			String sDatentyp = TabEigenschaft.getS("Datentyp");
                        boolean bCL=TabEigenschaft.getInhalt("Komponente") instanceof AUComboList;
			//g.progInfo(iPos+":"+sDatentyp+"/"+TabEigenschaft.getS("Bez"));
			if (sDatentyp.equals("WWW") || sDatentyp.equals("Memo") || sDatentyp.equals("Pfad") || sDatentyp.equals("Filename"))
				sDatentyp = "Stringx";//"StringLang";
			else if (sDatentyp.equals("StringKurzOhne") || sDatentyp.equals("Passwort"))
				sDatentyp = "Stringx";//"StringKurz";
			else if (sDatentyp.equals("E-Mail") || sDatentyp.equals("FixDoku"))
				sDatentyp = "Stringx";//"String60";
			else if (sDatentyp.equals("Auto_von_bis"))
				sDatentyp = "von_bis";

            /*if ((TabEigenschaft.getI("bits")&Abfrage.cstDialog)>0)
                 ;
            else*/ if (sDatentyp.equals("Bezeichnung2"))
            	s +=g.AU_Bezeichnung1("Stamm","s")+" d"+iPos;
			else if (sDatentyp.equals("StringSehrKurz") || sDatentyp.equals("StringKurz") || sDatentyp.equals("String60") || sDatentyp.equals("StringLang")
                                 || sDatentyp.equals("Text") || sDatentyp.equals("Stringx"))
				s += ",(select spalte_"+sDatentyp+" from poolview p join daten_"+sDatentyp+" d on d.aic_daten=p.aic_daten where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			/*else if (sDatentyp.equals("Stringx"))
            {
				s += ",(select stringx from stringXview p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
                iAnzx++;
            }*/
            else if(sDatentyp.equals("Boolean") || sDatentyp.equals("Bool3") || sDatentyp.equals("Integer") || sDatentyp.equals("Double") || sDatentyp.equals("Farbe") || sDatentyp.equals("Bits") || sDatentyp.equals("Land") || sDatentyp.equals("Aufgabe") || sDatentyp.equals("Status"))
				s += ",(select spalte_double from poolview p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("Datum"))
				s += ",(select p.gultig_von from poolview2 p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if (sDatentyp.equals("Zeit"))
				s += ",(select Zeit_von from poolview p join Zeit_von_bis d on d.aic_daten=p.aic_daten where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("Gruppe") || sDatentyp.equals("Radio") || sDatentyp.equals("Outliner") && Typ()==Stamm || sDatentyp.equals("Hierarchie") && bCL)
                        {
                          s += ",(select p.sta_aic_stamm from poolview p where " + sPool + TabEigenschaft.getS("Aic") + ") d" + iPos;
                          if (Static.ComboLeer())
                          {
                            //g.fixInfo(iPos+"."+TabEigenschaft.getS("Bez")+":"+TabEigenschaft.getI("Stammtyp")+"/"+g.Translate(TabEigenschaft.getI("Stammtyp")));
                            s += ",(select bezeichnung from poolview p join stammview2 v on v.aic_stamm=p.sta_aic_stamm where " + sPool + TabEigenschaft.getS("Aic") + " and aic_rolle is null) e" + iPos;
                            s += (g.Translate(TabEigenschaft.getI("Stammtyp")) ? ",(select bezeichnung from bezeichnung b2 join poolview p on b2.aic_tabellenname="+g.iTabStamm+
                                  " and b2.aic_fremd=p.sta_aic_stamm and aic_sprache="+g.getSprache()+" where " + sPool + TabEigenschaft.getS("Aic") + ")":",null")+" f"+iPos;
                          }
                        }
			//else if(sDatentyp.equals("Mehrfach"))
			//	s += ",(select count(*) from poolview p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if (sDatentyp.equals("Einheiten") || sDatentyp.equals("Mass") || sDatentyp.equals("BenMass"))
				s += ",(select p.sta_aic_stamm from poolview p where "+sPool+TabEigenschaft.getS("Aic")+") e"+iPos+","+
					"(select spalte_Double from poolview p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if (sDatentyp.equals("Waehrung"))
                          if (g.WW())
                            s += ",(select p.spalte_double from poolview p where p.sta_aic_stamm="+g.getWaehrung()+" and "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
                          else
                            s += ",(select p.spalte_double/p4.spalte_double from poolview p, poolview p4 where p4.aic_eigenschaft="+g.iEigEurofaktor+" and p4.aic_stamm=p.sta_aic_stamm and "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("Hierarchie"))
				s += ",(select p.sta_aic_stamm from poolview p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos+
                                (!g.ASA() ? ",(select v.aic_stammtyp from poolview p join stamm v on v.aic_stamm=p.sta_aic_stamm where "+sPool+TabEigenschaft.getS("Aic")+") e"+iPos:
					",(select v.aic_stammtyp from stamm v where d"+iPos+"=v.aic_stamm) e"+iPos);
			else if (sDatentyp.equalsIgnoreCase("von_bis"))
				s += ",(select Zeit_von from poolview p join Zeit_von_bis d on d.aic_daten=p.aic_daten where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos+","+
					"(select Zeit_bis from poolview p join Zeit_von_bis d on d.aic_daten=p.aic_daten where "+sPool+TabEigenschaft.getS("Aic")+") e"+iPos;
			else if (sDatentyp.endsWith("Bild") || sDatentyp.equals("Bild2") || sDatentyp.equals("Doku"))
				s += ",(select filename from poolview p join daten_"+(sDatentyp.equals("Bild")?"image":sDatentyp)+" d on d.aic_daten=p.aic_daten where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;

			else if(sDatentyp.equals("BewBoolean"))
				s += ",(select p.Spalte_Boolean from Bew_Boolean p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
                        else if(sDatentyp.equals("BewStamm")  || sDatentyp.equals("BewHierarchie") && bCL || sDatentyp.equals("BewRadio") || (sDatentyp.equals("Outliner") || sDatentyp.equals("OutlinerM")) && Typ()==Bewegung)
                        {
                              s += ",(select p.aic_stamm from Bew_Stamm p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
                              if (Static.ComboLeer())
                              {
                                //g.fixInfo(iPos+"."+TabEigenschaft.getS("Bez")+":"+TabEigenschaft.getI("Stammtyp")+"/"+g.Translate(TabEigenschaft.getI("Stammtyp")));
                                s += ",(select bezeichnung from Bew_Stamm p join stammview2 v on v.aic_stamm=p.aic_stamm where " + sPool + TabEigenschaft.getS("Aic") + " and aic_rolle is null) e" + iPos;
                                s += (g.Translate(TabEigenschaft.getI("Stammtyp")) ? ",(select bezeichnung from bezeichnung b2 join Bew_Stamm p on b2.aic_tabellenname=" + g.iTabStamm +
                                      " and b2.aic_fremd=p.aic_stamm and aic_sprache=" + g.getSprache() + " where " + sPool + TabEigenschaft.getS("Aic") + ")" : ",null") + " f" +iPos;
                              }
                        }
			else if(sDatentyp.equals("BewHierarchie"))
				s += ",(select p.aic_stamm from Bew_Stamm p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos+
                                (!g.ASA() ? ",(select v.aic_stammtyp from Bew_Stamm p join stamm v on v.aic_stamm=p.aic_stamm where "+sPool+TabEigenschaft.getS("Aic")+") e"+iPos:
					",(select v.aic_stammtyp from stamm v where d"+iPos+"=v.aic_stamm) e"+iPos);
			else if(sDatentyp.equals("BewModul") || sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular"))
				s += ",(select p.aic_begriff from Bew_Begriff p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("BewLand") || sDatentyp.equals("BewHoliday") || sDatentyp.equals("BewBool3"))
				s += ",(select p.aic from Bew_Aic p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("BewDatum2"))
				s += ",(select p.von from Bew_von_bis p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("BewDauer"))
				s += ",(select p.Dauer from Bew_von_bis p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("BewZahl"))
				s += ",(select p.Spalte_wert from Bew_Wert p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("BewVon_Bis"))
				s += ",(select p.von from Bew_von_bis p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos+","+
					"(select p.bis from Bew_von_bis p where "+sPool+TabEigenschaft.getS("Aic")+") e"+iPos+","+
					"(select p.dauer from Bew_von_bis p where "+sPool+TabEigenschaft.getS("Aic")+") f"+iPos;
			else if(sDatentyp.equals("GPS"))
				s += ",(select aic_daten from poolview p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("BewMass"))
				s += ",(select p.spalte_wert from Bew_Wert p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos+","+
					"(select p.aic_stamm from Bew_Wert p where "+sPool+TabEigenschaft.getS("Aic")+") e"+iPos;
			else if(sDatentyp.equals("BewWaehrung"))
                          if (g.WW())
				s += ",(select spalte_Wert from bew_Wert p where aic_stamm="+g.getWaehrung()+" and "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
                          else
				s += ",(select spalte_Wert/(select spalte_double from poolview p4 where p4.aic_stamm=p.aic_stamm and p4.aic_eigenschaft="+g.iEigEurofaktor+
							") from bew_Wert p where "+sPool+TabEigenschaft.getS("Aic")+") d"+iPos;
			else if(sDatentyp.equals("Aic"))
				s += ",number(*) d"+iPos;
			else if (sDatentyp.equals("Timestamp"))
				s += ",(select timestamp from "+(Typ()==Stamm ? "stamm_protokoll":"bew_pool")+" p,protokoll p4 where p.aic_protokoll=p4.aic_protokoll and "+sPool1+(Typ()==Stamm ? " and p.aic_code="+g.getBegriffAicS("Status","new"):Static.sLeer)+") d"+iPos;
			else if (sDatentyp.equals("Benutzer"))
				s += ",(select benutzer.kennung from "+(Typ()==Stamm ? "stamm_protokoll":"bew_pool")+" p,protokoll p4"+g.join("logging","p4")+g.join("Benutzer","logging")+" where p.aic_protokoll=p4.aic_protokoll and "+sPool1+(Typ()==Stamm ? " and p.aic_code="+g.getBegriffAicS("Status","new"):Static.sLeer)+") d"+iPos;
			else if (sDatentyp.equals("Anlage"))
				s += ",(select code.kennung from "+(Typ()==Stamm ? "stamm_protokoll":"bew_pool")+" p,protokoll p4"+g.join("code","p4")+" where p.aic_protokoll=p4.aic_protokoll and "+sPool1+(Typ()==Stamm ? " and p.aic_code="+g.getBegriffAicS("Status","new"):Static.sLeer)+") d"+iPos;
			else if (sDatentyp.equals("User"))
				s += ",("+g.top("benutzer.kennung from benutzer where benutzer.aic_stamm=s.aic_stamm",1)+") d"+iPos;
			else if (sDatentyp.equals("Mandant"))
                        {
                          if (Typ() == Stamm)
                            s += ",(select aic_Mandant from stammview p where " + sPool1 +Abfrage.Rolle(iRolle)+") v" + iPos;
                          else
                            s += ",(select min(aic_Mandant) from bew_pool p where " + sPool1 + ") v" + iPos;
                        }
                        else if ((sDatentyp.equals("Eintritt") || sDatentyp.equals("EinAustritt")) /*&& EA_Rolle(TabEigenschaft.getI("Aic"))*/)
                        {
                          //g.progInfo("  ->>>>>>>>>>>>>>>> "+g.TabEigenschaften.getBezeichnungS(TabEigenschaft.getI("Aic"))+":"+g.TabEigenschaften.getI(iPos2, "Rolle"));
                          s += ",(select eintritt from stammview2 p where " + sPool1 + Abfrage.Rolle(g.TabEigenschaften.getI(iPos2, "Rolle")) + ") d" + iPos;
                        }
                        else if (sDatentyp.equals("Austritt") /*&& EA_Rolle(TabEigenschaft.getI("Aic"))*/)
                          s += ",(select austritt from stammview2 p where "+sPool1+Abfrage.Rolle(g.TabEigenschaften.getI(iPos2,"Rolle"))+") d"+iPos;
			else if((sDatentyp.equals("SysAic") || sDatentyp.endsWith("Bezeichnung") || sDatentyp.equals("Kennung") || sDatentyp.equals("Buttons") || sDatentyp.equals("OutlinerM")) && Typ()==Stamm ||
                                 sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Stichtag") || sDatentyp.equals("Rolle") ||
				((sDatentyp.equals("BewDatum") || sDatentyp.endsWith("Buttons")) && Typ()== Bewegung))
				;
			else
				g.printError("EingabeFormular.SQLEigenschaftsDaten unterstützt folgenden Datentyp nicht: "+sDatentyp,getBegriff());
			iPos++;
			TabEigenschaft.moveNext();
		}
                g.defInfo((iPos>40?" ! ! !                           ! ! ! ":"")+"Anzahl der Eigenschaften="+(iPos-1)+" Stringx="+iAnzx);
		return s;
	}

        /*private String Rolle(int iRolle)
        {
          return "aic_rolle"+(iRolle==0 ? " is null":"="+iRolle);
        }*/

	private int checkExistens(int iAic/*, int iAic2*/)
	{
          g.progInfo("checkExistens "+iAic);
		int iNeu=iAic;
		if (TabOutliner.posInhalt("Eig",0))
		{
                  iNeu=-1;
                  AUOutliner Gid = ((AUOutliner)TabOutliner.getInhalt("Gid"));
                  if(Gid.getRootNode() != null && iAic>0)
                  {
                    Vector Vec= Gid.getRootNode().getChildren();
                    if (Vec != null)
                      for(int i2=0;i2<Vec.size() && iNeu<=0;i2++)
                      {
                        if (iAic==0 || iAic==-1 && iNeu==-1)
                          iNeu=((Integer)((JCOutlinerNode)Vec.elementAt(i2)).getUserData()).intValue();
                        else if(((Integer)((JCOutlinerNode)Vec.elementAt(i2)).getUserData()).intValue()==iAic)
                          iNeu=iAic;
                        //if (iNeu != iAic)
                        //  g.progInfo("       ----------------        checkExistens:"+iNeu+" statt "+iAic);
                      }				//Gid.selectNode((JCOutlinerNode)Vec.elementAt(i2));
                  }
                  if (iNeu==0 && VecAic != null && VecAic.contains(new Integer(iAic)))
                  {
                    bRefreshEigenschaften = false;
                    int iSatzSave=iSatz;
                    int iStammSave=iStamm;
                    int iSatzOldSave=iSatzOld;
                    FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"), iAic, false, false);
                    iSatz=iSatzSave;
                    iStamm=iStammSave;
                    iSatzOld=iSatzOldSave;
                    bRefreshEigenschaften = true;
                    iNeu=iAic;
                  }
                  if (iNeu==0 && VecAic == null)
                    iNeu=iAic;
		}
		if (iAic != iNeu)
                  g.progInfo("checkExistens:"+iAic+" -> "+iNeu);
		return iNeu;
	}

        private void getLogin()
        {
          if (DlgLogin==null)
          {
            DlgLogin = new JDialog((JFrame)thisFrame, "Login", true);
            JPanel PnlRight = new JPanel(new GridLayout(0, 1, 2, 2));
            JPanel PnlLeft = new JPanel(new GridLayout(0, 1, 2, 2));
            JPanel PnlButton = new JPanel(new GridLayout(1, 2, 2, 2));
            BtnLoginOk = g.getButton("Ok");
            //JButton BtnAbbruch = g.getButton("Abbruch");
            final Text EdtName = new Text("", 20);
            final AUPasswort EdtPasswort = new AUPasswort();
            PnlButton.add(new JLabel());
            PnlButton.add(BtnLoginOk);
            //PnlButton.add(BtnAbbruch);
            PnlRight.add(new JLabel("Name:"));
            PnlRight.add(new JLabel("Passwort:"));
            PnlLeft.add(EdtName);
            PnlLeft.add(EdtPasswort);
            DlgLogin.getContentPane().add("West", PnlRight);
            DlgLogin.getContentPane().add("Center", PnlLeft);
            DlgLogin.getContentPane().add("South", PnlButton);
            DlgLogin.getRootPane().setDefaultButton(BtnLoginOk);
            BtnLoginOk.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                g.Login(thisJFrame(),EdtName.getText(), new String(EdtPasswort.getPassword()));
                EdtName.setText("");
                EdtName.requestFocus();
                EdtPasswort.setText("");
                DlgLogin.setVisible(false);
              }
            });
            /*BtnAbbruch.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ev) {
                DlgLogin.hide();
              }
            });*/
            DlgLogin.pack();
            Static.centerComponent(DlgLogin, null);
          }
          DlgLogin.setVisible(true);
        }

  private boolean Multi()
  {
    boolean b= isMultiselect() && VecSelect != null && VecSelect.size()>1;
    //g.fixInfo("Multi="+b);
    return b;
  }

  @SuppressWarnings("unchecked")
  public void FuelleEigenschaften(int riSatz,boolean bStamm)
  {
    //g.fixInfo("--- FuelleEigenschaften "+riSatz+"/"+bStamm+"/"+bNeuCheck);
	  if (riSatz<-2)
	  {
            int iStammVor=iStamm;
            iStamm = getStamm(riSatz);
            if (iStamm != iStammVor)
              Refresh();
	    riSatz=-riSatz;
            bStamm=false;
	    //iStamm=0;
	    //iSatz=0;
	    //iSatzOld=-1;
	  }
          //g.progInfo("                  FuelleEigenschaften ---------------------- FuelleEigenschaften:"+riSatz+"/"+bStamm);
          //g.testInfo("--- FuelleEigenschaften:"+bReLoad+iStamm+"/"+iSatz+"->"+riSatz+"/"+bStamm);
          g.progInfo(sFormularBezeichnung+"-FuelleEigenschaften1:"+iStamm+"/"+iSatz+"/"+riSatz+"/"+bStamm);
          if (Login() && bStamm)
          {
            if (thisFrame.isVisible())
              hide();
            g.Logout(true);
            while (g.getStamm()==0)
              getLogin();
            riSatz=g.getStamm();
          }
	  	thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		boolean bZwingend = bReLoad;
                if (!bNeuCheck)
                {
                  if (bBewVec && riSatz > 0 || bHaupt && (Typ() == Stamm && riSatz > 0 || bStamm && (riSatz == 0 /* || Typ()== Bewegung*/)))
                  //if (riSatz > 0)  // 2011
                    riSatz = checkExistens(riSatz /*,bStamm && Typ()== Bewegung ? 0:riSatz*/);
                  //g.progInfo(sFormularBezeichnung+"-FuelleEigenschaften2:"+iStamm+"/"+iSatz+"/"+riSatz+"/"+bHaupt);
                  //if (!((JFrame)thisFrame).isVisible())
                  //  bReLoad=true;
                  //g.progInfo("FuelleEigenschaften: bReLoad="+bReLoad+",iSatzOld="+iSatzOld+",bStamm="+bStamm);

                  if (bReLoad)
                    bReLoad = false;
                  /*else*/ if (iSatzOld > -1 && !bStamm && !bKopie && !bMulti && !bSave)
                  {
                    //SpeichereDatenM(AutoSave());
                    //Static.printError("Speichern bei FuelleEigenschaften wird nicht mehr unterstützt");
                    if (TabAbf != null)
                      for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
                        if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
                           ((AUTable)TabAbf.getInhalt("Komponente")).clear();

                  }
                }
                bMulti=Multi();

          boolean bRefreshTab=bStamm  && riSatz != iStamm || /*Typ()== Stamm &&*/ riSatz != iSatzOld || isRefresh() ||
              tsVonOld==null || tsVonOld.getTime()!=g.getVon().getTime() || tsBisOld.getTime()!=g.getBis().getTime();
          boolean bAnders=bStamm ? riSatz != iStamm:false;
          iStamm = Typ()==Stamm || bStamm ? getStamm(riSatz) :iStamm;
          iSatz  = Login()?0:Typ()==Stamm || !bStamm ? getStamm(riSatz):-1;
          //if (iStamm==-1)
          //  iSatz=-1;
          /*int iNeu=*/checkSync(false);
          g.progInfo(sFormularBezeichnung+"-FuelleEigenschaften3:"+iStamm+"/"+iSatz+"/"+bAnders+"/"+bRefreshTab);
	  if (bTabRefresh && (bRefreshTab || isBewBew() || bMulti))
	  {
             if (TabAbf != null)
		  for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
		  {
			//g.progInfo("-Refresh:"+TabJCTable.getS("Kennung")+"/"+riSatz);
                        //long lClock = Static.get_ms();
                        if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
                        {
                          //g.progInfo("--------- Refresh2 Tabelle "+((AUTable)TabAbf.getInhalt("Komponente")).sBez);
                          long lClock = Static.get_ms();
                          AUTable Table=(AUTable)TabAbf.getInhalt("Komponente");
                          if (Table==null)
                            g.fixtestInfo("Tabelle " + g.TabBegriffe.getBezeichnungS(TabAbf.getI("Aic")) + " ist null");
                          else if (Table.Menge())
                            Table.Refresh(iDaten==ALLE || iDaten==FIRMA ? SQL.getVector("select aic_stamm from stammview p2 where aic_stammtyp="+iStammtyp+
                                (iDaten==FIRMA ?" and firma="+g.getFirma():"")+Rolle()+g.getReadMandanten(),g):VecAic,iKnoten);
                          else
                            Table.Refresh(bMulti ? -1:isBewBew() ? -iSatz : iStamm);
                          if (Table!=null)
                            Clock.add(clock,"AUTable",Table.sDefBez,lClock);
                        }
                        /*if (g.Prog())
                        {
                          lClock = Static.get_ms() - lClock;
                          g.progInfo("Refresh AUTable <"+g.TabBegriffe.getBezeichnung(TabJCTable.getI("Kennung"))+">:"+lClock+" ms");
                        }*/
		  }
                  refreshPlanung(true,false);
		  /*for (Tabellenspeicher TabPlanung= getTab("Planung");!TabPlanung.out();TabPlanung.moveNext())
		  {
			//g.progInfo("-Refresh:"+TabPlanung.getS("Kennung")+"/"+iStamm);
                        long lClock = Static.get_ms();
			Planung P=(Planung)TabPlanung.getInhalt("Komponente");
                        Vector VecPStamm=g.getVecStamm(getBegriff(),iStammtyp);
			P.Refresh(P.Menge()? iDaten==ALLE || iDaten==FIRMA ? VecPStamm!=null ? VecPStamm:SQL.getVector("select aic_stamm from stammview p2 where aic_stammtyp="+iStammtyp+
                            (iDaten==FIRMA ?" and firma="+g.getFirma():"")+Rolle()+g.getReadMandanten(),g)
				:VecAic:Static.AicToVec(bMulti ? -1:iStamm),bMulti ? -1:iStamm,false);
                      Clock.add(clock,"Planung",((Planung)TabPlanung.getInhalt("Komponente")).sBez,lClock);
		  }*/
	  }
	  else if (VecAic !=null)
	  	for (Tabellenspeicher TabPlanung= getTab("Planung");!TabPlanung.out();TabPlanung.moveNext())
		  {
			Planung P=(Planung)TabPlanung.getInhalt("Komponente");
			if (P.Menge())
				P.VecNeu=new Vector(VecAic);
		  }

	  EnableModelle();

	  bAnders = bAnders || iSatzOld !=iSatz;
          boolean bNeuerZR=tsVonOld==null || tsVonOld.getTime()!=g.getVon().getTime() || tsBisOld.getTime()!=g.getBis().getTime();
          //g.fixInfo("bAnders="+bAnders+", bNeuerZR="+bNeuerZR);
          bAnders = bAnders || bNeuerZR;
	  g.progInfo("*** FuelleEigenschaften: bAnders="+bAnders+",iStamm="+iStamm+",iSatz="+iSatz+" (riSatz="+riSatz+", bStamm="+bStamm+")");
          //Knoten();
          g.printInfo(2,"FuelleEigenschaften-iStamm="+iStamm+" / iSatz="+iSatz+" / ausführen:"+bAnders);

          if (isRefresh() || bAnders)
          //{
            //g.progInfo("isRefresh");
            //g.progInfo("Titelzusatz isRefresh");
            Titelzusatz("");
          //}

    if (bZwingend || bAnders || bStamm && iEigenschaft2 != 0 || isRefresh() || bMulti)
	  {
		//bZwingend=false;
	  boolean bLeer = iSatz <= 0 || bMulti;
    if (BtnCheck != null)
      BtnCheck.setEnabled(bLeer && !bMulti);
		bKopie = bLeer && bKopie && !bMulti;
                //Knoten();
		TabOutliner.push();
		if (TabOutliner.posInhalt("Eig",0))	// Hauptmaske wird bei Neuaufruf bei Bewegungsmaske und bei Mehrfachsubfenster gefüllt
		{
			bRefreshEigenschaften = false;
			if ((Typ()==Bewegung || TabOutliner.getS("Typ").equals("Entfernen") || iEigenschaft2!=0/* || VecAic != null*/) && bStamm && !StammAusBew())
				FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),bMulti ? -1:iSatz,false,isRefresh());
                        AUOutliner Gid = ((AUOutliner)TabOutliner.getInhalt("Gid"));
                        if (Typ()==Bewegung && bStamm && iSatz==0 && !bMulti)
                        {
                          Vector Vec= Gid.getRootNode().getChildren();
                          if (Vec != null)
                          {
                            iSatz=((Integer)((JCOutlinerNode)Vec.elementAt(0)).getUserData()).intValue();
                            bLeer=false;
                          }
                          //g.progInfo("*/*/*/*/* ersten markieren:"+iSatz );
                        }
                  //if (StammAusBew())
                  //  bLeer=false;
      if (bMulti)
      	g.progInfo("***** Multi *****");
      else if (bLeer)
			{
				Gid.selectNode(Gid.getRootNode());
				g.progInfo("***** leer *****");
                                //Knoten();
				//NodHaupt=Gid.getRootNode();
			}
			else
			{
				if(Gid.getRootNode() != null)
				{
					Vector Vec= Gid.getRootNode().getChildren();
					if (Vec != null)
						for(int i2=0;i2<Vec.size();i2++)
							if(((Integer)((JCOutlinerNode)Vec.elementAt(i2)).getUserData()).intValue()==iSatz)
							{
								GidHaupt=Gid;
                                                                if (bMakeVisible)
                                                                {
                                                                  //NodHaupt = (JCOutlinerNode)Vec.elementAt(i2);
                                                                  Static.makeVisible(GidHaupt, (JCOutlinerNode)Vec.elementAt(i2));
                                                                  //g.fixtestError("makeVisible4:"+iStamm+"/"+(JCOutlinerNode)Vec.elementAt(i2));
                                                                }
                                                                //g.progInfo("makeVisible2:"+NodHaupt);
							}
				}
			}
			bRefreshEigenschaften = true;
		}
                //g.progInfo("bTabRefresh="+bTabRefresh);
                if (bTabRefresh)
		for(TabOutliner.moveFirst();!TabOutliner.eof();TabOutliner.moveNext())
		{
                  //g.progInfo("Outliner:"+TabOutliner.getS("Typ")+"/"+TabOutliner.getI("Eig"));
			if(/*(bStamm || Typ()==Stamm) &&*/ TabOutliner.getI("Eig")<0 || TabOutliner.getS("Typ").equals("Mehrfach"))			// Anzeigefenster werden immer neu gefüllt
				//TabOutliner.getI("Eig")>0 && TabOutliner.getS("Typ").equals("Mehrfach") && bStamm && Typ()==Bewegung)
				FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,isRefresh());
			/*else
			{
				//g.TabBegriffe.posInhalt("Aic",TabOutliner.getI("Gruppe"));
				g.printInfo(2,(g.TabEigenschaften.posInhalt("Aic",TabOutliner.getI("Eig"))?g.TabEigenschaften.getS("Bezeichnung"):"Hauptliste")+
					" ->kein Refresh von "+TabOutliner.getS("Bezeichnung"));
				g.testInfo(" ->kein Refresh von "+TabOutliner.getS("Bezeichnung"));
			}*/
		}
		TabOutliner.pop();
		//SQL Qry = new SQL(g);
                Tabellenspeicher Qry=null;
		boolean bOpen = false;
		if (bLeer)
                {
                        sBezeichnung = "";
                        sKennung = "";
                        dtEin = null;
                        dtAus = null;
                        //dtStT = null;
                        dtGueltigBew = null;
                }
                else
		{
			long lClock = Static.get_ms();
			//Ausgabe("vor open");
			if (!bLeer)
			{
                          g.progInfo("Hole Daten für "+iSatz);
                          if (sSQL_Zeile==null)
                          {
                            g.fixtestInfo("sSQL_Zeile ist null");
                            sSQL_Zeile="";
                          }
                          boolean b=Typ()==Stamm && iStammtyp==g.iSttANR && !keinZR()?SQL.exists(g,"select aic_stamm from stammview where aic_stamm=?"+Rolle(),""+iStamm):false;
			  String sQry=Typ()==Stamm ?(g.Oracle() ? "select /*+ RULE */":"select")+" aic_mandant,kennung,bezeichnung,eintritt,austritt,firma,ab"+sSQL_Zeile+" from stammview"+(b?"":"2")+" s where aic_stamm=?"+
                            (bSaveRolle && !bNeuCheck ?" and aic_rolle="+iRolle:" and aic_rolle is null"):Typ()==Bewegung ?"select gueltig"+sSQL_Zeile+" from Bew_Pool b where aic_Bew_Pool=?":"";
                          Qry=new Tabellenspeicher(g,sQry,""+iSatz,true);

                          if (sSQL_Zeile2 != null)
                          {
                            g.progInfo(sSQL_Zeile2);
                            String sQry2 = Typ() == Stamm ? "select null x" + sSQL_Zeile2 + " from stammview2 s where aic_stamm=?" +
                                (bSaveRolle && !bNeuCheck ? " and aic_rolle=" + iRolle : " and aic_rolle is null") : Typ() == Bewegung ?
                                "select null x" + sSQL_Zeile2 + " from Bew_Pool b where aic_Bew_Pool=?" : "";
                            Qry.addSpalten(new Tabellenspeicher(g, sQry2, ""+iSatz, true), 1);
                          }
                          bOpen = Qry != null;
                          Clock.add(clock, "Daten", sFormularBezeichnung, lClock);
			}
			if (bOpen)
			{
				bLeer =	Qry.eof();
				if (bLeer || Typ()==Bewegung)
				{
					sBezeichnung = "";
					sKennung = "";
					dtEin = null;
					dtAus = null;
                                        //dtStT = null;
					dtGueltigBew = bLeer ? null:Qry.getTimestamp("gueltig");
				}
				else
				{
					sBezeichnung = Qry.getS("Bezeichnung");
					sKennung =	Qry.getS("Kennung");
					dtEin =	Qry.getD("Eintritt");
					dtAus = Qry.getD("Austritt");
                                        //dtStT = Qry.getD("Ab");
					dtGueltigBew = null;
					//g.printInfo("FuelleEigenschaften: nach Open:"+(Static.get_ms()-lClock));
				}
			}
			else
				bLeer = true;
		}
                if (Typ()!=Bewegung && iEigenschaft2 ==0)
                  Titelzusatz(bLeer?"---":sBezeichnung);
                //long lClock2 = Static.get_ms();
                if (TabRadio !=null)
                  for(TabRadio.moveFirst();!TabRadio.eof();TabRadio.moveNext())
                  {
                    JRadioButton Rad=((JRadioButton)TabRadio.getInhalt("Btn"));
                    if (TabRadio.getI("Aic") == 0)
                    {
                      Rad.setSelected(true);
                    }
                    else if (Rad.isSelected())
                    {
                      ((ButtonGroup)TabRadio.getInhalt("Grp")).remove(Rad);
                      Rad.setSelected(false);
                      ((ButtonGroup)TabRadio.getInhalt("Grp")).add(Rad);
                    }
                    ComboSort Cbo2 = ((AUComboList)TabRadio.getInhalt("Cbo")).getComboBox();
                    Cbo2.setSelectedAIC(0);
                  }
                //g.clockInfo("TabRadio-Clean",lClock2);
                      //((JRadioButton)TabRadio.getInhalt("Btn")).setText(((JRadioButton)TabRadio.getInhalt("Btn")).getText()+"*");
		int iPos =1;
		TabEigenschaft.moveFirst();
                //Qry.showGrid();
	  if(!bKopie)
		while(!TabEigenschaft.eof())
		{
			//long lClockD = Static.get_ms();

                        //boolean bEnable=iSatz>=0;
                        //boolean bEdit=!g.ReadOnly() && ((TabEigenschaft.getI("bits")&Abfrage.cstAnzeigen)==0 || (TabEigenschaft.getI("bits")&Abfrage.cstEditierbar)>0);
                        //g.progInfo("Datentyp="+TabEigenschaft.getS("Datentyp")+"/ Kennung="+TabEigenschaft.getS("Bez"));
                        String sDatentyp = TabEigenschaft.getS("Datentyp");
			//if ((Component)TabEigenschaft.getInhalt("Komponente") != null && !sDatentyp.startsWith("Bild"))
                          ((Component)TabEigenschaft.getInhalt("Komponente")).setEnabled(iSatz>=0);
                        //Check.setEditable(TabEigenschaft.getInhalt("Komponente"),bEdit && !sDatentyp.startsWith("Calc"));
			if (Typ()==Bewegung)
			{
				sDatentyp=sDatentyp.equals("BewBool3") ? "Bool3":sDatentyp.equals("BewBoolean") ? "Boolean":
					/*sDatentyp.equals("BewVon_Bis") ? "von_bis":*/
					//sDatentyp.equals("BewMass") ? "Mass":
					//sDatentyp.equals("BewWaehrung") ? "Waehrung":
					sDatentyp.equals("BewDauer") || sDatentyp.equals("BewZahl") ? "Double":sDatentyp;
			}
                        /*if ((TabEigenschaft.getI("bits")&Abfrage.cstDialog)>0)
                          ;
                        else*/ if (sDatentyp.endsWith("Bezeichnung") || sDatentyp.equals("Bezeichnung2") || sDatentyp.equals("Kennung") ||
                        		sDatentyp.equals("StringSehrKurz") || sDatentyp.equals("StringKurz") || sDatentyp.equals("StringKurzOhne") ||
                        		sDatentyp.equals("String60") || sDatentyp.equals("StringLang") || sDatentyp.equals("Stringx"))
			{
				//String sSQL="select first spalte_"+sDatentyp+
				//	" from daten_"+sDatentyp+" join daten join stammpool p join stamm on p.aic_stamm=stamm.aic_stamm where aic_eigenschaft="+TabEigenschaft.getI("Aic")+
				//	" and aic_stammtyp="+iStammtyp+" and aic_mandant="+g.getMandant()+" order by daten.aic_daten desc";
				//g.progInfo(sSQL);
                                //if ((TabEigenschaft.getI("Bits")&Abfrage.cstAutoInc)>0)
                                //  g.testInfo("AutoInc: Eig="+TabEigenschaft.getI("Aic")+"/ Stt="+iStammtyp);
				String sDT=sDatentyp.equals("StringKurzOhne") ? "StringKurz":sDatentyp;
				String s = bLeer ? (TabEigenschaft.getI("Bits")&Abfrage.cstAutoInc)>0 ? Static.addToString(SQL.getString(g,g.top("spalte_"+sDT+
					" from daten_"+sDT+" d join stammpool p on d.aic_daten=p.aic_daten join "+(Bewegungstyp()==0 ? "stammview2 v on p.aic_stamm=v.aic_stamm where v.aic_stammtyp="+iStammtyp:
                    "bew_pool on p.aic_bew_pool=bew_pool.aic_bew_pool where aic_bewegungstyp="+Bewegungstyp())+" and aic_eigenschaft="+TabEigenschaft.getI("Aic")+
                    " and aic_mandant="+g.getMandant()+" and pro2_aic_protokoll is null and spalte_"+sDT+" is not null order by spalte_"+sDT+" desc",1))):"" :
					sDatentyp.endsWith("Bezeichnung") ? sBezeichnung : sDatentyp.equals("Kennung") ? sKennung : Qry.getM("D"+iPos);
				if (TabEigenschaft.getInhalt("Komponente") instanceof Text)//sDatentyp.endsWith("Bezeichnung") || sDatentyp.equals("Kennung"))
					((Text)TabEigenschaft.getInhalt("Komponente")).setText(s);
				else
				{
					/*if (!bLeer &&(sDatentyp.equals("StringLang")))
						((ComboSort)TabEigenschaft.getInhalt("Komponente")).fillDaten(TabEigenschaft.getI("Aic"),"StringLang",iStamm,0);
					else if (!bLeer && sDatentyp.equals("StringKurzOhne"))
						((ComboSort)TabEigenschaft.getInhalt("Komponente")).fillDaten(TabEigenschaft.getI("Aic"),"StringKurz",iStamm,0);*/
                                  //System.err.println(TabEigenschaft.getS("Bez")+"/"+TabEigenschaft.getS("Datentyp")+":"+
                                  //           ((ComboSort)TabEigenschaft.getInhalt("Komponente")).getSelectedItem()+"->"+s);

					((ComboSort)TabEigenschaft.getInhalt("Komponente")).setSelectedItem(s);
				}

			}
                        else if (sDatentyp.equals("Rolle") && Typ()==Stamm)
                        {
                          TabEinAus=new Tabellenspeicher(g,"select aic_rolle,bezeichnung,eintritt,austritt from stammview2 where aic_stamm="+iSatz,true);
                          ((RolleEingabe)TabEigenschaft.getInhalt("Komponente")).setSelectedAIC(0);
                        }
			else if(sDatentyp.equals("Passwort"))
			{
				((AUPasswort)TabEigenschaft.getInhalt("Komponente")).setValue(bLeer ? "" : Qry.getM("D"+iPos));
			}
			else if(sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") || sDatentyp.equals("FixDoku") || sDatentyp.equals("Doku"))
			{
				((FileEingabe)TabEigenschaft.getInhalt("Komponente")).setValue(bLeer ? "" : Qry.getM("D"+iPos));
			}
			else if(sDatentyp.equals("Farbe"))
			{
				((AUFarbe)TabEigenschaft.getInhalt("Komponente")).setValue(bLeer ? 0 : Qry.getI("D"+iPos));
			}
			else if(sDatentyp.equals("Bits"))
			{
				((AUBits)TabEigenschaft.getInhalt("Komponente")).setValue(bLeer ? 0 : Qry.getI("D"+iPos));
			}
			else if(sDatentyp.equals("E-Mail"))
			{
				((EMail)TabEigenschaft.getInhalt("Komponente")).setValue(bLeer ? "" : Qry.getM("D"+iPos));
			}
			else if(sDatentyp.equals("WWW"))
			{
				((WWW)TabEigenschaft.getInhalt("Komponente")).setValue(bLeer ? "" : Qry.getM("D"+iPos));
			}
			else if(sDatentyp.endsWith("Bild") || sDatentyp.equals("Bild2"))
			{
				String sFile = bLeer ? "":Qry.getM("D"+iPos);
				//Vector Vec = new Vector();
				//Vec.addElement(sFile);
                                //String sMandant= g.getMandant(bLeer?0:Qry.getI("AIC_Mandant"),"Kennung");//SQL.getString(g,"select mandant.kennung from mandant join stamm where aic_stamm="+iStamm);
				//g.progInfo("Bild="+sFile+", Mandant="+sMandant);
                                //Vec.addElement(g.LoadImage(sMandant+"\\"+sFile));
				//((BildEingabe)TabEigenschaft.getInhalt("Komponente")).setValue(sFile.equals("")?null:g.LoadImage(sFile,Static.DirImageStamm),sFile,sMandant);
                                ((BildEingabe)TabEigenschaft.getInhalt("Komponente")).setValue(sFile);
                                    //g.LoadImage((!Static.bImgM || sFile.startsWith("file:\\")?"":Static.DirImageStamm)+sFile),sFile,sMandant);
				/*
				JButton Btn = ((JButton)TabEigenschaft.getInhalt("Sub"));
				if(Vec.elementAt(1)!=null)
				{
					Btn.setText("");
					Btn.setIcon(new ImageIcon(((Image)Vec.elementAt(1))));
				}
				else
				{
					Btn.setIcon(null);
					Btn.setText("<leer>");
				}*/
			}
			else if(sDatentyp.equals("Memo") || sDatentyp.equals("Text"))
			{
				String s = bLeer ? "" :Qry.getM("D"+iPos);
                                Object Obj=TabEigenschaft.getInhalt("Komponente");
                                if (Obj instanceof AUTextArea)
                                  ((AUTextArea)Obj).setText(s);
                                else if (Obj instanceof AUEkitCore)
                                    ((AUEkitCore)Obj).setText(s);
//                                else if (Obj instanceof HtmlEingabe)
//                                  ((HtmlEingabe)Obj).setText(s);
			}
			/*else if(sDatentyp.equals("Mehrfach"))
			{
				int i = bLeer ? 0 : Qry.getI("D"+iPos);
				//((JButton)TabEigenschaft.getInhalt("Sub")).setEnabled(!bLeer);
				((JLabel)TabEigenschaft.getInhalt("Komponente")).setText(""+i+" "+g.getBegriff("Show","Elemente"));
			}*/
			else if(sDatentyp.equals("Aic"))
			{
				int i = bLeer ? Typ()==Stamm ? iMom+1 : 0 : Qry.getI("D"+iPos);
				((JLabel)TabEigenschaft.getInhalt("Komponente")).setText(""+i);
			}
			else if(sDatentyp.equals("SysAic"))
			{
				((JLabel)TabEigenschaft.getInhalt("Komponente")).setText(""+(bLeer ? 0 : iSatz));
			}
			else if(sDatentyp.equals("Anlage") || sDatentyp.equals("Benutzer") || sDatentyp.equals("User") || sDatentyp.equals("Timestamp"))
			{
				((JLabel)TabEigenschaft.getInhalt("Komponente")).setText(bLeer ? "" : Qry.getS("D"+iPos));
			}
                        else if(sDatentyp.equals("Mandant"))
                        {
                                //((JLabel)TabEigenschaft.getInhalt("Komponente")).setText(""+(bLeer ? 0 : iSatz));
                                int i = bLeer ? g.getMandant():Qry.getI("V"+iPos);
				((ComboSort)TabEigenschaft.getInhalt("Komponente")).setSelectedAIC(i);
                        }
			else if(sDatentyp.equals("Datum") || sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt"))
			{
                          //g.progInfo(" <<<<<<<<<<<<< "+sDatentyp+"/"+g.TabEigenschaften.getBezeichnungS(TabEigenschaft.getI("Aic"))+"/"+EA_Rolle(TabEigenschaft.getI("Aic"))+"/"+dtEin);
                          	java.util.Date dt = bLeer ? null : /*EA_Rolle(TabEigenschaft.getI("Aic")) ?*/ Qry.getD("D"+iPos);/* : sDatentyp.equals("Austritt") ? dtAus :
                                    (sDatentyp.equals("Eintritt") || sDatentyp.equals("EinAustritt"))  ? dtEin : Qry.getD("D"+iPos);*/
                                //if (!bLeer && iRolle>0 && sDatentyp.equals("Stichtag"))
                                //  dt=SQL.getTimestamp(g,"select eintritt from stammview2 where aic_rolle is null and aic_stamm="+iStamm);
				((Datum)TabEigenschaft.getInhalt("Komponente")).setDate(dt);
				//if (sDatentyp.equals("Stichtag"))
				//	((Datum)TabEigenschaft.getInhalt("Komponente")).setEnabled(!bLeer);
			}
                        else if(sDatentyp.equals("Zeit"))
                        {
                          java.sql.Timestamp dt = bLeer ? null :Qry.getTimestamp("D"+iPos);
                          ((Datum)TabEigenschaft.getInhalt("Komponente")).setDate(dt);
                        }
                        else if(sDatentyp.equals("Stichtag"))
                        {
                          ((Datum)TabEigenschaft.getInhalt("Komponente")).setDate(null);//dtStT);
                          //  ((Datum)TabEigenschaft.getInhalt("Komponente")).setEnabled(!bLeer);
                        }
			else if(sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2"))
			{
                          int iBits=TabEigenschaft.getI("bits");
				java.sql.Timestamp dt = bLeer ? (iBits&(Global.cstKeinAutoDate*0x10000+Abfrage.cstKeinAutoDate))==0 && iSatz==0 ? new java.sql.Timestamp(new Date().getTime()) : null :
					sDatentyp.equals("BewDatum") ? dtGueltigBew :  Qry.getTimestamp("D"+iPos);
                                if (bLeer && (iBits&Abfrage.cstAnzeigen)>0 && (iBits&Abfrage.cstNeu)==Abfrage.cstNimmSync)
                                  ((Datum)TabEigenschaft.getInhalt("Komponente")).setDate(new Date(new DateWOD(g.getBis()).yesterday()),false,true);
                                else if (!bLeer || (iBits&Abfrage.cstAnzeigen)==0 || (iBits&Abfrage.cstNeu)==Abfrage.cstKeinAutoLast || (iBits&Abfrage.cstKeinAutoDate)==0)
                                  ((Datum)TabEigenschaft.getInhalt("Komponente")).setDate(dt);
			}
			else if(sDatentyp.equals("Double"))
			{
				double d = bLeer ? 0 :Qry.getF("D"+iPos);
				((Zahl)TabEigenschaft.getInhalt("Komponente")).setValue(d);
			}
			else if(sDatentyp.equals("Integer"))
			{
				int i = bLeer ? 0 :Qry.getI("D"+iPos);
				((Zahl)TabEigenschaft.getInhalt("Komponente")).setValue(i);
			}
			else if (sDatentyp.equalsIgnoreCase("Boolean"))
			{
                          int iBits=TabEigenschaft.getI("bits");
				boolean b = bLeer ? (iBits&Abfrage.cstAnzeigen)>0 && (iBits&Abfrage.cstNeu)==Abfrage.cstLimit :Qry.getB("D"+iPos);
				((AUCheckBox)TabEigenschaft.getInhalt("Komponente")).setSelected(b);
			}
                        else if (sDatentyp.equalsIgnoreCase("Bool3"))
                        {
                          Object Obj=TabEigenschaft.getInhalt("Komponente");
                          if (Obj instanceof SpinBoxAuswahl)
                            ((SpinBoxAuswahl)Obj).setAic(bLeer ? 0:Qry.getI("D"+iPos));
                          else if (Obj instanceof RadioAuswahl)
                            ((RadioAuswahl)Obj).setAic(bLeer ? 0:Qry.getI("D"+iPos));
                        }
			else if(sDatentyp.equals("Outliner") ||  sDatentyp.equals("OutlinerM") && Typ()==Bewegung)
			{
				int i = bLeer ? 0:Qry.getI("D"+iPos);
                                //g.progInfo("OutlinerM:"+i);
                                AUOutliner Out=(AUOutliner)TabEigenschaft.getInhalt("Komponente");
                                Out.selectNode(Out.getRootNode());
				Vector Vec=Out.getRootNode().getChildren();
				if (Vec != null)
					for(int i2=0;i2<Vec.size();i2++)
						if(((Integer)((JCOutlinerNode)Vec.elementAt(i2)).getUserData()).intValue()==i)
							Out.selectNode((JCOutlinerNode)Vec.elementAt(i2));
			}
			else if(sDatentyp.equals("OutlinerM") && Typ()==Stamm)
			{
				g.printError("EingabeFormular.FuelleEigenschaften: OutlinerM mit Stamm gibt es nicht mehr!",getBegriff());
				//if(TabOutliner.posInhalt("Eig",TabEigenschaft.getI("Aic")))
				//	FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false);
			}
                        else if(sDatentyp.endsWith("Radio"))
                        {
                          int i = bLeer ? 0:Qry.getI("D"+iPos);
                          if (i>0 && TabRadio.posInhalt("Aic", i))
                          {
                            ((JRadioButton)TabRadio.getInhalt("Btn")).setSelected(true);
                            ComboSort Cbo2=((AUComboList)TabRadio.getInhalt("Cbo")).getComboBox();
                            Cbo2.setSelectedAIC(i);
                          }
                        }
			else if(TabEigenschaft.getInhalt("Komponente") instanceof AUComboList)//sDatentyp.equals("Gruppe") || sDatentyp.equals("BewStamm"))
			{
                          int iBits=TabEigenschaft.getI("bits");
                          int i = iSatz<0 ? 0: bLeer ? (iBits&Abfrage.cstNeu)==Abfrage.cstNimmSync ? g.getSyncStamm(TabEigenschaft.getI("Stammtyp")):TabEigenschaft.getI("Stamm"):
                              Qry.exists("D"+iPos) ? Qry.getI("D"+iPos):0;
                          //g.progInfo(TabEigenschaft.getS("Bez")+":"+i+"/"+iBits);
                          String sK=bLeer || !Static.ComboLeer() ? null:Qry.getM("E"+iPos);
                          String sB=bLeer || !Static.ComboLeer() ? null:Qry.getM("F"+iPos);
                                /*if ((iBits&Abfrage.cstNeu)==Abfrage.cstNimmSync)
                                  g.progInfo("cstNimmSync: Eig-Stt="+TabEigenschaft.getI("Stammtyp")+"->"+g.TabStammtypen.getI("AIC")+"/"+g.TabStammtypen.getI("Stamm")+"/"+i+
                                      ", Eig="+TabEigenschaft.getI("Aic")+"/"+Eigenschaft()+", iStamm="+iStamm);*/
                          //if (TabEigenschaft.getI("Stammtyp")==24)
                          //  g.progInfo("Geschlecht");
				if((iBits&Abfrage.cstSpRefresh)>0)
                                {
                                  g.progInfo("**************** Spaltenrefresh von "+TabEigenschaft.getS("Bez"));
                                  int iAic=((AUComboList)TabEigenschaft.getInhalt("Komponente")).getComboBox().getSelectedAIC();
                                  ((AUComboList)TabEigenschaft.getInhalt("Komponente")).fillCbo(true);
                                  ((AUComboList)TabEigenschaft.getInhalt("Komponente")).getComboBox().setSelectedAIC(iAic);
                                }
                                if(TabEigenschaft.getI("Aic")==Eigenschaft())
                                  ((AUComboList)TabEigenschaft.getInhalt("Komponente")).getComboBox().setSelectedAIC(iStamm);
                                else if (iSatz<0 || !bLeer || (iBits&Abfrage.cstAnzeigen)==0 || (iBits&Abfrage.cstNeu)!=Abfrage.cstLast)
                                {
                                  if (bLeer && sDatentyp.endsWith("Hierarchie"))
                                    ;//((AUComboList)TabEigenschaft.getInhalt("Komponente")).getComboBox().setAktAIC(0); !!! 3.2.2012 weg wegen Modified-Prüfung bei Hauptliste
                                  else
                                    ((AUComboList)TabEigenschaft.getInhalt("Komponente")).getComboBox().setSelectedAIC( /*bLeer?0:*/i, sK, sB,true);
                                }
                                if (bLeer && (iBits&Abfrage.cstAnzeigen)>0 && (iBits&Abfrage.cstEditierbar)==0 && i==0 && iSatz==0)
                                  ((AUComboList)TabEigenschaft.getInhalt("Komponente")).getComboBox().setFirst();
			}
			else if(sDatentyp.equals("BewModul") || sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular")
			    || sDatentyp.endsWith("Land") || sDatentyp.equals("BewHoliday") || sDatentyp.equals("Aufgabe") || sDatentyp.equals("Status"))
			{
				int i = bLeer ? 0/*TabEigenschaft.getI("Stamm")*/:Qry.getI("D"+iPos);
				((ComboSort)TabEigenschaft.getInhalt("Komponente")).setSelectedAIC(i);
			}
			else if (sDatentyp.equals("GPS"))
			{
				int i = bLeer ? 0:Qry.getI("D"+iPos);
				if (i>0)
				{
					double dLat=SQL.getDouble(g,"select lat from Daten_GPS where aic_daten="+i);
					double dLng=SQL.getDouble(g,"select lng from Daten_GPS where aic_daten="+i);
					String sName=SQL.getString(g,"select name from Daten_GPS where aic_daten="+i);
					int iMap=SQL.getInteger(g,"select map from Daten_GPS where aic_daten="+i);
					((GPS_Eingabe)TabEigenschaft.getInhalt("Komponente")).set(dLat, dLng,sName,iMap);
				}
				else
					((GPS_Eingabe)TabEigenschaft.getInhalt("Komponente")).setZero();
			}
			else if (sDatentyp.equals("Hierarchie") || sDatentyp.equals("BewHierarchie"))
			{
				if (bLeer)
					;//((HierarchieEingabe)TabEigenschaft.getInhalt("Komponente")).setStamm(0); !!! 3.2.2012 weg wegen Modified-Prüfung bei Hauptliste
				else
				{
					int i1 = Qry.getI("E"+iPos);
					int i2 = Qry.getI("D"+iPos);
					//g.progInfo("Hierarchie "+TabEigenschaft.getS("Bez")+":"+i1+"/"+i2);
                                        if (TabEigenschaft.getI("Aic")==g.iEigFirma)
                                          ((HierarchieEingabe)TabEigenschaft.getInhalt("Komponente")).setFirma(bLeer?0:Qry.getI("Firma"));
					((HierarchieEingabe)TabEigenschaft.getInhalt("Komponente")).setValue(i1,i2);
				}
			}
			else if (sDatentyp.equals("Waehrung") || sDatentyp.equals("BewWaehrung"))
			{
				//int i = bLeer || Qry.getF("D"+iPos)==0.0 ? g.getWaehrung():Qry.getI("E"+iPos);
				//double d = bLeer ? 0.0:Qry.getF("D"+iPos);
				((WaehrungsEingabe)TabEigenschaft.getInhalt("Komponente")).setValue(g.getATS(bLeer ? 0.0:Qry.getF("D"+iPos)),g.getWaehrung());
			}
			else if (sDatentyp.equals("Mass") || sDatentyp.equals("BewMass") || sDatentyp.equals("Einheiten") || sDatentyp.equals("BenMass"))
			{
				//g.progInfo("vor "+sDatentyp);
				int i = bLeer || Qry.getF("D"+iPos)==0.0 && TabEigenschaft.getI("Stamm")>0 ? TabEigenschaft.getI("Stamm"):Qry.getI("E"+iPos);
				//g.progInfo("nach "+sDatentyp+":"+i);
				double d = bLeer ? 0:Qry.getF("D"+iPos);
				((MassEingabe)TabEigenschaft.getInhalt("Komponente")).setValue(d,i);
			}
			else if (sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("BewVon_Bis"))
			{
				Date dt1 = bLeer ? sDatentyp.equals("Auto_von_bis") ? new Date():null:Qry.getTimestamp("D"+iPos);
				Date dt2 = bLeer ? null:Qry.getTimestamp("E"+iPos);
				double dDauer = !bLeer && sDatentyp.equals("BewVon_Bis") ? Qry.getF("F"+iPos):0.0;
				((VonBisEingabe)TabEigenschaft.getInhalt("Komponente")).setValue(dt1, dt2, dDauer==0.0 ? dt1!=null && dt2!=null?(dt2.getTime()-dt1.getTime())/1000.0:0.0:dDauer);
				Vector Vec = new Vector();
				Vec.addElement(bLeer ? null:dt1);
				Vec.addElement(dt2);
			}
			iPos++;
			//g.debugInfo("Pos"+Static.VorNull(iPos)+","+sDatentyp+":"+(Static.get_ms()-lClockD));
			TabEigenschaft.moveNext();
		}
		//Qry.free();
		iSatzOld = iSatz;

	  }
          tsVonOld=g.getVon();
          tsBisOld=g.getBis();
          //Knoten();
          g.progInfo(" ------------------------------------ Aktueller Stand: "+iSatz+"/"+tsVonOld+" - "+tsBisOld);

		//show();
		if (iSatz == 0)
		{
			TabEigenschaft.moveFirst();
			if (!TabEigenschaft.eof())
				((Component)TabEigenschaft.getInhalt("Komponente")).requestFocus();
		}

		//TabEigenschaft.showGrid();

		g.printSI("fill"+Static.rightString(""+riSatz,5));

		/* 2.12.2004 ausgeklammert, da Probleme nach Refresh bei 2 Werten
                if (GidHaupt != null && NodHaupt != null)
		{
			bRefreshEigenschaften=false;
                        Static.makeVisible(GidHaupt,NodHaupt);
                        g.progInfo("makeVisible3:"+NodHaupt);
			bRefreshEigenschaften=true;
		}*/
                //bRefreshEigenschaften=false;
                //g.progInfo("1: iSatz="+iSatz);
                if (bTabRefresh) // bei Refresh nicht 2x refreshen
                  RefreshFormButtons();
                //g.progInfo("2: iSatz="+iSatz);
		thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                bkHE=true;
                //g.fixInfo("thisFrame="+Static.print(thisFrame));
                //g.fixInfo("* "+iSatz+"/"+iStamm);
                if (thisFrame instanceof JFrame)// && !bNeuCheck)
                  show();
                //g.fixInfo("** "+iSatz+"/"+iStamm);
                bkHE=false;
                //g.progInfo("3: iSatz="+iSatz);
                if ((Transact.iInfos&Transact.NO_SPEED)==0)
                  checkHide();
                //g.fixInfo("*** "+iSatz+"/"+iStamm);
                //g.progInfo("4: iSatz="+iSatz);
                if (riSatz>0)
                  for(TabOutliner.moveFirst();!TabOutliner.eof();TabOutliner.moveNext())
                    if(TabOutliner.getS("Typ").equals("Haupt") && TabOutliner.getInhalt("Gid") != null)
                    {
                      //g.fixInfo("Gid1="+Static.print(TabOutliner.getInhalt("Gid")));
                      if (!bMulti)
                      {
                        Static.makeVisible((JCOutliner)TabOutliner.getInhalt("Gid"),null,true);
                        //g.fixtestError("makeVisible5:"+iStamm);
                      }
                      //g.fixInfo("Gid2="+Static.print(TabOutliner.getInhalt("Gid")));
                      if (TabOutliner.getInhalt("Gid") != null)
                        ((JCOutliner)TabOutliner.getInhalt("Gid")).requestFocus();
                    }
                //g.fixInfo("**** "+iSatz+"/"+iStamm);
                EnableBedingt();
                //g.progInfo("5: iSatz="+iSatz);
                //bRefreshEigenschaften=true;
                //g.fixInfo("--- FuelleEigenschaften fertig");
                //g.fixInfo("***** "+iSatz+"/"+iStamm);
	}

        private void refreshPlanung(boolean bImmer,boolean bRefresh)
        {
          g.testInfo("   !!!   refreshPlanung "+bImmer+"  !!!");
          for (Tabellenspeicher TabPlanung= getTab("Planung");!TabPlanung.out();TabPlanung.moveNext())
          {
            long lClock = Static.get_ms();
            Object Obj=TabPlanung.getInhalt("Komponente");
            Planung P=Obj instanceof Planung ? (Planung)TabPlanung.getInhalt("Komponente"):null;
            if (P!=null && (bImmer || !P.table.isEnabled() && P.isShowing()))
            {
              //Vector VecPStamm = g.getVecStamm(getBegriff(), iStammtyp);
              P.Refresh(P.Menge() ? iDaten == ALLE || iDaten == FIRMA ? /*VecPStamm != null ? VecPStamm :*/SQL.getVector("select aic_stamm from stammview p2 where aic_stammtyp=" + iStammtyp +
                                      (iDaten == FIRMA ? " and firma=" + g.getFirma() : "") + Rolle() + g.getReadMandanten(), g) : VecAic/*check(VecAic,VecPStamm)*/ : Static.AicToVec(bMulti ? -1 : iStamm),bMulti ? -1 : iStamm, bRefresh);
              Clock.add(clock, "Planung", ((Planung)TabPlanung.getInhalt("Komponente")).sBez, lClock);
            }
          }
        }

        /*private void Knoten()
        {
          int iPos=TabOutliner.getPos("Eig",0);
          if (iPos>=0)
          {
            JCOutliner Gid = (JCOutliner)TabOutliner.getInhalt("Gid",iPos);
            g.progInfo("Selectiert:"+iStamm+"/"+Gid.getSelectedNode());
          }
        }*/

        private void EnableModelle()
        {
          boolean bMulti=Multi();
//          g.fixtestError("EnableModelle: iStamm="+iStamm+", Multi="+bMulti);
          for(TabFormular.moveFirst();!TabFormular.eof();TabFormular.moveNext())
          {
        	//g.fixtestError("EnableModelle von "+g.getBegBez(TabFormular.getI("Aic")));
            if (TabFormular.getS("Gruppe").equals("Modell"))
              if (ReadOnly())
              {
                ((JButton)TabFormular.getInhalt("Button")).setEnabled(false);
                g.fixtestError("EnableModelle von "+g.getBegBez(TabFormular.getI("Aic"))+": false da ReadOnly");
              }
              else
              {
                g.checkModelle();
                int iPosM=g.TabModelle.getPos("aic_begriff", TabFormular.getI("Aic"));
                //int iMbits = 0;
                //int iPos = g.TabBegriffe.getPos("Aic", TabFormular.getI("Aic"));
                //if (iPosM >= 0)
                JButton Btn=((JButton)TabFormular.getInhalt("Button"));
                //g.fixtestError("prüfe "+Btn.getText());
                int iMbits = g.TabModelle.getI(iPosM, "bits");
                if ((iMbits&g.cstMSperre)>0)
                {
                	//Btn.setBackground(Color.YELLOW);
                	Date dt=g.getAbschlussP(iProg,iStamm);
                	g.fixtestInfo("Abschlussdatum für "+Btn.getText()+"="+dt+", von="+g.getVon());
                	Btn.setEnabled(dt==null || !dt.after(g.getVon())); // exkl. deshalb nicht: dt.before(g.getVon()));
                }
                else if (!g.bTestPC && (iMbits&Global.cstNurTest)>0)
                	Btn.setEnabled(false);
//                else if ((iMbits&Global.cstAmpel)>0 && g.TabModelle.getI(iPosM,"AIC_Abfrage")>0)
//                {
//                	Btn.setEnabled(true);
//                	g.fixtestError("EnableModelle: Enable weil Ampel");
//                }
                else if (bMulti || g.TabModelle.getI(iPosM,"AIC_Abfrage")==0 || (iMbits & g.cstMDialog)>0)
                {
                  boolean b = (iMbits & (Global.cstOhneStamm /*+ Global.cstSave*/)) > 0 ||
                      (bMulti ? (iMbits & Global.cstMenge) > 0 : (iMbits & Global.cstBew) > 0 ? iSatz > 0 : iStamm > 0);
                  if (b && (iMbits & Global.cstNbAServer) > 0 && AClient.UseAServer2())
                    b = false;
                  Btn.setEnabled(!bCalc2 && b);
                  //g.fixtestError("EnableModelle von "+g.getBegBez(TabFormular.getI("Aic"))+": "+b+"/"+bCalc2);
                }
                if ((iMbits & Global.cstErgebnis) > 0)
                {
                  int iPos = g.TabBegriffe.getPos("Aic", TabFormular.getI("Aic"));
                  Btn.setText(g.getBegBez2(iPos));
                }
              }
          }
          //EnableBedingt();
        }

        private void EnableBedingt()
        {
          if (TabEigenschaft.sum("bed")==0 || g.VecSttBed.isEmpty())
            return;
          //g.fixtestInfo("prüfe auf bedingte:"+g.VecSttBed);
          Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Eig","Stt","Wert"});
          for (int iPos=0;iPos<TabEigenschaft.size();iPos++)
            if (g.VecSttBed.contains(TabEigenschaft.getI(iPos,"Stammtyp")))
            {
              Tab.addInhalt("Eig",TabEigenschaft.getI(iPos,"Aic"));
              Tab.addInhalt("Stt",TabEigenschaft.getI(iPos,"Stammtyp"));
              Tab.addInhalt("Wert",Check.getI(TabEigenschaft.getInhalt("Komponente",iPos)));
            }
          //Tab.showGrid("Tab");
          for (int iPos=0;iPos<TabEigenschaft.size();iPos++)
            if (TabEigenschaft.getI(iPos,"bed")>0)
              ((JComponent)TabEigenschaft.getInhalt("Komponente",iPos)).setEnabled(Tab.posInhalt("Wert",TabEigenschaft.getI(iPos,"bed")));
              //((JComponent)TabEigenschaft.getInhalt("EF",iPos)).setVisible(Tab.posInhalt("Wert",TabEigenschaft.getI(iPos,"bed")));
        }

        /*private void setDialog()
        {
            for(TabEigenschaft.moveFirst();!TabEigenschaft.eof();TabEigenschaft.moveNext())
              if ((TabEigenschaft.getI("Bits")&Abfrage.cstDialog)>0)
                g.setDialog(TabEigenschaft.getI("Aic"),getObject(TabEigenschaft.getInhalt("Komponente"),true));
        }*/

        private void BtnAddT(String s)
        {
          JToggleButton Btn=getTButton(s);
          if (Btn != null)
          {
            if (s.equals("alle_Daten"))
              BtnAlleDaten=Btn;
            if (s.equals("nur_Vector"))
              BtnNurVector=Btn;
            if (s.equals("nur_Firma"))
              BtnNurFirma=Btn;
            Btn.setActionCommand(s);
            Btn.addActionListener(AL);
          }
        }

        private void BtnAdd(String s)
        {
          BtnAdd(s,s);
          /*JButton Btn=getButton(s);
          if (Btn != null)
          {
            Btn.setActionCommand(s);
            Btn.addActionListener(AL);
          }*/
        }

        private void BtnAdd(String s,String s2)
        {
          JButton Btn=getButton(s);
          if (Btn != null)
            if (ReadOnly() && VReadOnly.contains(s2))
              Btn.setEnabled(false);
            else
            {
              Btn.setActionCommand(s2);
              Btn.addActionListener(AL);
              if (s.equals("user"))
            	  BtnUser=Btn;
            }
        }

        private JButton BtnAdd2(String s,String sA)
        {
          JButton Btn=getButton(s);
          if (Btn != null)
          {
            Btn.setActionCommand(sA);
            Btn.addActionListener(AL2);
            if (sA.equals("F") || sA.equals("M") || sA.equals("A"))
            {
              int iPos = TabOutliner.getPos("Eig", 0);
              int iAnz = sA.equals("A") ? getAnzAbf(TabOutliner.getInhalt("Pnl", iPos)):0;
              if (iAnz==0)
              {
                Vector Vec=sA.equals("A") ? null:getVec(sA.equals("F") ? "Frame" : "Modell", TabOutliner.getInhalt("Pnl", iPos));
                if (Vec==null)
                {
                  if (sA.equals("M"))
                    Static.printError("keine Modelle zugeordnet");
                  else if (sA.equals("F"))
                    Static.printError("keine Formulare zugeordnet");
                }
                else
                  iAnz=Vec.size();
              }
              if (iAnz>0)
                g.setAnzahl(Btn, iAnz);
              else
                Btn.setVisible(false);
            }
          }
          return Btn;
        }

        private JButton BtnAddg(String s)
        {
          JButton Btn=g.getButton(s);
          if (Btn != null)
          {
            if (s.equals("ZRplus") || s.equals("ZRminus"))
              Btn.setPreferredSize(new java.awt.Dimension(40,8));
            Btn.setActionCommand(s);
            Btn.addActionListener(AL);
          }
          return Btn;
        }

        private void checkHide()
        {
          long lClock = bRefreshMom ? 0:Clock.startClock(clock);
          // Outliner prüfen
          for (TabOutliner.moveFirst();!TabOutliner.out();TabOutliner.moveNext())
          {
            Object Obj=TabOutliner.getInhalt("Gid");
            AUOutliner Gid=Obj instanceof AUOutliner ? (AUOutliner)Obj:null;
            EF_Eingabe EF=Obj instanceof EF_Eingabe ? (EF_Eingabe)Obj:null;
            JLabel Lbl=Obj instanceof JLabel ? (JLabel)Obj:null;
            //g.fixInfo(TabOutliner.getS("Bezeichnung")+":"+Gid==null ?"null":""+((JPanel)TabOutliner.getInhalt("Pnl")).isShowing())+"/"+TabOutliner.getInhalt("Gid"));
            if (((JPanel)TabOutliner.getInhalt("Pnl")).isShowing() && (Gid != null || EF!= null || Lbl!= null))
            {
              //g.fixInfo(TabOutliner.getS("Bezeichnung")+":"+(Gid== null ? EF.getBackground(): Gid.getBackground())+"/"+g.ColHide);
              //g.fixInfo(TabOutliner.getS("Bezeichnung")+":"+Gid+"/"+EF+"/"+Lbl);
              if ((Gid!= null ? Gid.getBackground() :EF != null ? EF.getBackground(): Lbl != null ? Lbl.getBackground():null) == g.ColHide)
                FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"), 0, false, true);
              else if (AllSync() && ((Abfrage)TabOutliner.getInhalt("Abfrage")).TabSync != null)
              {
                Tabellenspeicher TabSync = ((Abfrage)TabOutliner.getInhalt("Abfrage")).TabSync;
                TabSync.posInhalt("anr", g.getSyncStamm(g.iSttANR,g.iRolMA));
                g.progInfo("Sync auf " + TabSync.getI("aic_bew_pool"));
                g.select(TabSync.getI("aic_bew_pool"), (AUOutliner)TabOutliner.getInhalt("Gid"));
              }
            }
          }
          // Tabellen-Eingabe prüfen
          if (TabAbf != null)
           for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
          {
            AUTable Tbl=(AUTable)TabAbf.getInhalt("Komponente");
            //if (TabAbf.getS("Art").equals("JCTable"))
            //  g.progInfo("Tabelle "+TabAbf.getI("Aic")+":"+Tbl.table.isEnabled()+"/"+((JPanel)TabAbf.getInhalt("Pnl")==null ?"":""+((JPanel)TabAbf.getInhalt("Pnl")).isShowing()));
            if (Tbl!=null && TabAbf.getS("Art").equals("JCTable") && !Tbl.bAktiv && ((JPanel)TabAbf.getInhalt("Pnl"))!= null && ((JPanel)TabAbf.getInhalt("Pnl")).isShowing())
            {
              long lClock2 = Static.get_ms();
              if (Tbl.Menge())
                Tbl.Refresh(iDaten==ALLE || iDaten==FIRMA ? SQL.getVector("select aic_stamm from stammview p2 where aic_stammtyp="+iStammtyp+
                    (iDaten==FIRMA ?" and firma="+g.getFirma():"")+Rolle()+g.getReadMandanten(),g):VecAic,iKnoten);
              else
                Tbl.Refresh();//setHide();
              //((AUTable)TabAbf.getInhalt("Komponente")).Refresh(bMulti ? -1 : isBewBew() ? -iSatz : iStamm);
              Clock.add(clock, "AUTable", ((AUTable)TabAbf.getInhalt("Komponente")).sDefBez, lClock2);
            }
          }
          //Planungen prüfen
          //g.fixInfo("   !!!   Planung in checkHide   !!!");
          refreshPlanung(false,true);
          for(int iPos=0;iPos<TabEigenschaft.size();iPos++)
            if (TabEigenschaft.getInhalt("Komponente",iPos) instanceof BildEingabe)
              ((BildEingabe)TabEigenschaft.getInhalt("Komponente",iPos)).showImageOnButton();
              //g.fixInfo(TabEigenschaft.getS(iPos,"Bez")+":"+((BildEingabe)TabEigenschaft.getInhalt("Komponente",iPos)).isShowing());
          /*
          for (Tabellenspeicher TabPlanung = getTab("Planung"); !TabPlanung.out(); TabPlanung.moveNext())
          {
            Planung P=(Planung)TabPlanung.getInhalt("Komponente");
            if (P!=null && !P.table.isEnabled() && P.isShowing())
            {
              long lClock2 = Static.get_ms();
              Vector VecPStamm=g.getVecStamm(getBegriff(),iStammtyp);
              P.Refresh(P.Menge() ? VecAic == null ? VecPStamm!=null ? VecPStamm:SQL.getVector("select aic_stamm from stammview p2 where aic_stammtyp=" + iStammtyp + g.getReadMandanten(), g)
                        : VecAic : Static.AicToVec(bMulti ? -1 : iStamm), bMulti ? -1 : iStamm, true);
              Clock.add(clock, "Planung", ((Planung)TabPlanung.getInhalt("Komponente")).sBez, lClock2);
            }
          }*/
          if (!bRefreshMom)
            Clock.showClock(g,clock,"                                     checkHide",lClock);
        }

	private void Events()
	{
          ChangeListener CL=new ChangeListener ()
          {
                  public void stateChanged(ChangeEvent ev)
                  {
                          checkHide();
                  }
          };

          if ((Transact.iInfos&Transact.NO_SPEED)==0)
           for (int i=0;i<VecTC.size();i++)
            VecTC.elementAt(i).addChangeListener(CL);
		TabFormular.moveFirst();
		while(!TabFormular.eof())
		{
			((JButton)TabFormular.getInhalt("Button")).addActionListener(new ActionListener()
			{
				@SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent e)
				{
                                  g.testInfo("getModifiers="+e.getModifiers());
                                  if (e.getModifiers()==24) // Alt
                                    return;
                                  //if (e.getModifiers()==17 e.getButton()==3 || e.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                                  //  return;
					if (TabFormular.posInhalt("Button",e.getSource()))
                                        {
                                          String sTyp=TabFormular.getS("Gruppe");
                                          g.progInfo("Typ="+sTyp);
                                          Vector VecMenge=(Vector)TabFormular.getInhalt("Comp");
                        if (VecMenge != null && VecMenge.size()>1)
                        {
//                        	g.fixtestError("es gibt eine Menge von "+((JButton)TabFormular.getInhalt("Button")).getText());
                        	JPopupMenu popup = new JPopupMenu();
                        	for (int i=0;i<VecMenge.size();i++)
                            {
                              int iAic=Sort.geti(VecMenge,i);
                              int iBPos=g.TabBegriffe.getPos("Aic", iAic);
                              if (iBPos>=0)
                              {
                            	  String sBez=g.getBegBez2(iBPos);
	                              JMenuItem MnuEF = new JMenuItem(sBez);//TabBegriffe.getBezeichnungS(iAic));
//	                              String sTT=g.TabBegriffe.getS(iBPos,"Tooltip");
//	                              if (!sTT.equals(""))
//	                                g.setTooltip(sTT,MnuEF);
	                              MnuEF.setFont(g.fontStandard);
	                              MnuEF.setActionCommand(""+iAic);
	                              int iGruppe=g.TabBegriffe.getI(iBPos,"Gruppe");
	                              if (iGruppe==g.TabBegriffgruppen.getAic("Frame"))
	                            	  addMnuSubFormular(popup,iAic);
	                              else if (iGruppe==g.TabBegriffgruppen.getAic("Modell"))
	                            	  addMnuModell(popup,iAic);      
	                              else if (iGruppe==g.TabBegriffgruppen.getAic("Druck"))
	                            	  addMnuDruck(popup,iAic);    
	                              else
	                            	  popup.add(MnuEF);
                              }                          
                            }
                            popup.show((JButton)TabFormular.getInhalt("Button"), 0, 0);
                        }
                        else if (sTyp.startsWith("Eingabe") || sTyp.equals("Formularmenge"))
						{
                                                  long lClock = Static.get_ms();
							g.progInfo("VecAic="+VecAic+"/ Stt="+iStammtyp);
                                                        int iFormular=0;
                                                        if (sTyp.startsWith("Eingabe"))
                                                          iFormular=TabFormular.getI("Aic");
                                                        else if (sTyp.equals("Formularmenge") && iStamm>0)
                                                        {
                                                          int iAic=TabFormular.getI("Aic");
                                                          Vector Vec=SQL.getVector("select sta_aic_stamm from poolview where sta_aic_stamm is not null and aic_stamm="+iStamm,g);
                                                          //g.progInfo("Formularmenge: Stamm="+iStamm+", Aic="+iAic+", Vec="+Vec);
                                                          Tabellenspeicher Tab=new Tabellenspeicher(g,"select f.aic_begriff,f.aic_stamm from formular f join begriff_zuordnung z on f.aic_begriff=z.beg_aic_begriff where z.aic_begriff="+iAic,true);
                                                          //if (g.Prog())
                                                          //  Tab.showGrid("Formularmenge für "+g.TabBegriffe.getBezeichnung(iAic));
                                                          for (Tab.moveFirst();!Tab.eof() && iFormular==0;Tab.moveNext())
                                                          {
                                                            int iPos=g.TabBegriffe.getPos("Aic",Tab.getI("aic_begriff"));
                                                            if (iPos>=0 && (g.TabBegriffe.getL(iPos,"bits")&cstStammFix)==0 && (Tab.isNull("aic_stamm") && g.BerechtigungS(iPos) || Vec.contains(Tab.getInhalt("aic_stamm"))))
                                                              iFormular=Tab.getI("aic_begriff");
                                                          }
                                                          //g.progInfo("Formularmenge: iFormular="+iFormular);
                                                        }
                                                        if (iFormular==0)
                                                        {
                                                          new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("keineFormular");
                                                          return;
                                                        }
                                                        int iPos=g.TabBegriffe.getPos("Aic",iFormular);
							iSubStt=iPos>=0 ? g.TabBegriffe.getI(iPos,"Stt"):0;
							boolean bOhne=true;
							//g.progInfo("iSubStt="+iSubStt+", Verteiler="+iVerteiler);
							if(Verteiler())
							{
								//int iSubStt=SQL.getInteger(g,"select aic_stammtyp from begriff where aic_begriff="+iFormular);
								AUOutliner Gid=null;
								AUTable Tab=null;
								boolean bBew=false;
								int iEig=0;
								for (TabOutliner.moveFirst();!TabOutliner.out() && Gid==null;TabOutliner.moveNext())
								{
									ShowAbfrage A=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
									if ((A.iBits&Abfrage.cstVerteiler)>0)// && (A.iBew>0 || iSubStt==A.iStt || A.iStt==0))
									{
										Tabellenspeicher TabSpalten=A.getSpalten();
										for(TabSpalten.moveFirst();!TabSpalten.eof() && Gid==null;TabSpalten.moveNext())
										{
											if((TabSpalten.getI("bits")&Abfrage.cstSpVerteiler)>0 && TabSpalten.getI("Stt")==iSubStt)
											{
												Gid=((AUOutliner)TabOutliner.getInhalt("Gid"));
												iEig=TabSpalten.getI("Kennung2");
												bBew=A.iBew>0;
											}
										}
										if (Gid==null && A.iBew==0 && iSubStt==A.iStt)
										{
											Gid=((AUOutliner)TabOutliner.getInhalt("Gid"));
										}
									}
								}

								if (Gid == null)
								{
                                                                   if (TabAbf != null)
									for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
                                                                          if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
                                                                          {
										//g.progInfo("-Refresh:"+TabJCTable.getS("Kennung")+"/"+riSatz);
										ShowAbfrage A=((AUTable)TabAbf.getInhalt("Komponente")).A;
										/*if ((A.iBits&A.cstVerteiler)>0 && (A.iBew>0 || iSubStt==A.iStt || A.iStt==0))
										{
											Tab=(AUTable)TabJCTable.getInhalt("Komponente");
											bBew=A.iBew>0;
										}*/
										if ((A.iBits&Abfrage.cstVerteiler)>0)// && (A.iBew>0 || iSubStt==A.iStt || A.iStt==0))
										{
											Tabellenspeicher TabSpalten=A.getSpalten();
											for(TabSpalten.moveFirst();!TabSpalten.eof() && Tab==null;TabSpalten.moveNext())
											{
												if((TabSpalten.getI("bits")&Abfrage.cstSpVerteiler)>0 && TabSpalten.getI("Stt")==iSubStt)
												{
													Tab=(AUTable)TabAbf.getInhalt("Komponente");
													iEig=TabSpalten.getI("Kennung2");
													bBew=A.iBew>0;
												}
											}
											if (Tab==null && A.iBew==0 && iSubStt==A.iStt)
											{
												Tab=(AUTable)TabAbf.getInhalt("Komponente");
											}
										}
                                                                          }
								}
								if (g.TabBegriffe.getI(iPos,"Erf")==0 && (Gid != null || Tab != null))
								{
									//if (TabOutliner.posInhalt("Eig",iVerteiler))	// Hauptmaske wird bei Neuaufruf bei Bewegungsmaske und bei Mehrfachsubfenster gefüllt
									//{
										//boolean bBew=(Gid != null ? ((Abfrage)TabOutliner.getInhalt("Abfrage")):Tab.A).iBew>0;
										Vector<Integer> VecSubStamm=new Vector<Integer>();
										//AUOutliner Gid = ((AUOutliner)TabOutliner.getInhalt("Gid"));
										int iSubStamm=0;
										if (Gid != null)
										{
											JCOutlinerNode NodFocus = Gid.getSelectedNode();
											iSubStamm = NodFocus == null || NodFocus.getLevel()==0 ? 0 : ((Integer)NodFocus.getUserData()).intValue();

											if(Gid.getRootNode() != null)
											{
												Vector Vec= Gid.getRootNode().getChildren();
												if (Vec != null)
													for(int i2=0;i2<Vec.size();i2++)
														VecSubStamm.addElement((Integer)((JCOutlinerNode)Vec.elementAt(i2)).getUserData());
											}
										}
										else
										{
											iSubStamm=Tab.getAIC(Tab.iOldY).intValue();
											VecSubStamm=Tab.getAllAICs();
										}
										if (bBew)
											iSubStamm=SQL.getInteger(g,"select aic_stamm from bew_stamm where aic_bew_pool="+iSubStamm+" and aic_eigenschaft="+iEig);
										if (VecSubStamm != null && !VecSubStamm.isEmpty() && bBew)
											VecSubStamm = SQL.getVector("select distinct aic_stamm from bew_stamm where aic_bew_pool"+Static.SQL_in(VecSubStamm)+"and aic_eigenschaft="+iEig,g);
										g.progInfo("Verteiler:"+VecSubStamm+"/"+iSubStamm);
                                                                                if (!g.Def() || TabFormular.isNull("Comp") || !(TabFormular.getInhalt("Comp") instanceof Vector) || ((Vector)TabFormular.getInhalt("Comp")).size()<2)
                                                                                {
                                                                                  HoleFormular(g, iFormular, VecSubStamm,
                                                                                      iSubStamm == 0 && VecSubStamm != null && VecSubStamm.size() > 0 ?
                                                                                      VecSubStamm.elementAt(0).intValue() : iSubStamm, false);
                                                                                  bOhne = false;
                                                                                }
									//}
								}
								//else if (iSubStt==iStammtyp)
								//	EingabeFormular.HoleFormular(g,iFormular,iStammtyp,VecAic,iStamm);
							}
							//else
							//	EingabeFormular.HoleFormular(g,iFormular,TabEigenschaft.getI("Stammtyp"),iSubStt==iStammtyp?VecAic:null,iStamm);
                                        if (bOhne)
                                        {
                                          //g.progInfo("Formularmenge:bOhne:"+TabFormular.getInhalt("Comp"));
                                          if (!TabFormular.isNull("Comp") && TabFormular.getInhalt("Comp") instanceof Vector)
                                          {
                                            JPopupMenu popup = new JPopupMenu();
                                            Vector Vec = (Vector)TabFormular.getInhalt("Comp");
                                            for (int i = 0; i < Vec.size(); i++)
                                            {
                                              int iAic = Sort.geti(Vec, i);                                           
                                              JMenuItem MnuEF = new JMenuItem(g.TabBegriffe.getBezeichnungS(iAic));
                                              String sTT = g.TabBegriffe.getInfoS(iAic, "Tooltip");
                                              if (!sTT.equals(""))
                                                g.setTooltip(sTT, MnuEF);
                                              MnuEF.setFont(g.fontStandard);
                                              MnuEF.setActionCommand("" + iAic);
                                              popup.add(MnuEF);
                                              MnuEF.addActionListener(new ActionListener()
                                              {
                                                public void actionPerformed(ActionEvent ev)
                                                {
                                                  HoleFormular(g, Sort.geti(ev.getActionCommand()), iSubStt == iStammtyp ? Multi() ? VecSelect : VecAic : null, iSubStt == iStammtyp ? iStamm : 0,bBewVec);
                                                }
                                              });
                                            }
                                            popup.show((JButton)TabFormular.getInhalt("Button"), 0, 0);
                                          }
                                          else
                                            HoleFormular(g, iFormular, iSubStt == iStammtyp ? Multi() ? VecSelect : VecAic : null, iSubStt == iStammtyp ? iStamm : 0,bBewVec);
                                        }
                                        g.clockInfo2("EF-Sub",lClock);
                                    }
		else if (sTyp.equals("Modell"))
		{
		  Modellberechnung((JButton)e.getSource(),e.getModifiers()==17,false); // shift
		}
              else if (sTyp.equals("Druck"))
              {
                //setDialog();
                int iPos=g.TabBegriffe.getPos("Aic",TabFormular.getI("Aic"));
                int iDBits=g.TabBegriffe.getI(iPos,"bits");
                int iBew=g.TabBegriffe.getI(iPos,"Erf");
                //g.progInfo("iDruckBits="+iDruckBits);
                //boolean b=(iDBits & Drucken.cstPntBewegung)==0;
                boolean b=iBew==0 || iBew != Bewegungstyp();
                g.progInfo("iDruckBits="+iDBits+", Bew="+iBew+" -> "+b);
                AUOutliner GidDF=Typ()==Bewegung && b || !TabOutliner.posInhalt("Eig",0) ? null:(AUOutliner)TabOutliner.getInhalt("Gid");
                if ((iDBits & Drucken.cstPntDirekt)==0)
                {
                  All_Unlimited.Print.DruckFrage.start(GidDF,iRolle, Typ() == Bewegung && !b ? -Bewegungstyp() : iStammtyp,
                      b ? iStamm : iSatz,null, g, TabFormular.getI("Aic"), getBegriff()).show();
                }
                else // Direktdruck
                {
                  DruckAufrufA daa = new DruckAufrufA(g,0);
                  int iDF_Bits = Drucken.cstSeitenvorschau + ((iDBits & Drucken.cstPntStammLinks) > 0 ? Drucken.cstStammLinks:0)+
                      ((iDBits&Drucken.cstPntSeitenumbruch)>0 ? Drucken.cstSeitenumbruch:0)+((iDBits&Drucken.cstPntPeriode)>0 ? Drucken.cstPeriode:0);

                  //Tabellenspeicher TabStamm=new Tabellenspeicher(g,new String[] {"Aic","Bezeichnung"});
                  int iAic=Typ() == Bewegung /*|| iStammtyp==g.iSttANR*/ ? g.getStamm():iStamm;
                  Tabellenspeicher TabStamm=getTabStamm(iAic);
                  //TabStamm.addInhalt("Aic",iAic);
                  //TabStamm.addInhalt("Bezeichnung",SQL.getString(g,"select bezeichnung from stammview where aic_rolle is null and aic_stamm="+iAic));
                  //if (g.TestPC())
                  //  TabStamm.showGrid("TabStamm");
                  /*if (b)
                    for (TabStamm.moveFirst();!TabStamm.eof();TabStamm.moveNext())
                    	TabStamm.setInhalt("Aic",-TabStamm.getI("Aic"));*/
                  daa.druckeDruck(TabFormular.getI("Aic"), /*b ? iStammtyp:-Bewegungstyp(),*/ TabStamm,null,null,null, iDF_Bits,0,null,0,0);
                }
              }

              else
            {
              String sG=TabFormular.getS("Gruppe");
              String sK=TabFormular.getS("Kennung");
              if(sG.equals("System") && (sK.equals("Abschluss") || sK.equals("Aufgabe") || sK.equals("SyncStamm")))
                ;
              else
                g.printError("EingabeFormular.Events: Aufruf von " + sG + " / "+sK+" nicht möglich",getBegriff());
            }
          }
				}
			});
			if (g.Def())
			  ((JButton)TabFormular.getInhalt("Button")).addMouseListener(new MouseListener()
		            {
		              public void mousePressed(MouseEvent ev)
		              {}

		              public void mouseClicked(MouseEvent ev)
		              {
		                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
		                {
		                  thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		                  if (TabFormular.posInhalt("Button",ev.getSource()))
		                  {
		                    //g.progInfo(" ------ Mausklick rechts ----- : "+TabFormular.getS("Gruppe")+"/"+TabFormular.getS("AIC"));
		                    int iAic=TabFormular.getI("Aic");
		                    if (TabFormular.getS("Gruppe").equals("Modell"))
		                      showPopup3(ev);//DefModell.get(g, iAic).show();
		                    else if (TabFormular.getS("Gruppe").startsWith("Eingabe"))
		                      DefFormular.get(g,iAic);
		                    else if (TabFormular.getS("Gruppe").startsWith("Druck"))
		                      Druckdefinition.get(g, true, iAic, g.getStt(iAic));
		                  }
		                  thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		                }
		              }

		              public void mouseEntered(MouseEvent ev)
		              {}

		              public void mouseExited(MouseEvent ev)
		              {}

		              public void mouseReleased(MouseEvent ev)
		              {}
		            });
			TabFormular.moveNext();
		}

                JButton BtnBeenden=getButton("Beenden");
                if (BtnBeenden != null)
                {
                  Action cancelKeyAction = new AbstractAction() {
                    private static final long serialVersionUID = 8745483376711318268L;
                    public void actionPerformed(ActionEvent e) {
                      Beenden(0);
                    }
                  };
                  Static.Escape(BtnBeenden,thisFrame,cancelKeyAction);
                }

                BtnAdd("Ok");
		BtnAdd("Abbruch");
                BtnAdd("Beenden");
                BtnAdd("Neu");
                BtnAdd("Tb_Alle","alle");
                BtnAdd("Tb_Neu","Neu");
		BtnAdd("Kopie");
                BtnAdd("Kopie_Stamm","Kopie2");
                BtnAdd("Loeschen");
                BtnAdd("Tb_Loeschen","Loeschen");
		BtnAdd("Loesche Rolle");
//		BtnAdd("destroy role");
//		BtnAdd("destroy data");
                BtnAdd("Speichern");
                BtnAdd("Tb_Speichern","Speichern");
		BtnAdd("Ruecksetzen");
                BtnAdd("Druck");
                BtnAdd("Tb_Drucken","Druck");
                BtnAdd("Druck2");
                BtnAdd("DruckMenge");
		BtnAdd("Export");
                BtnAdd("WEB");
                BtnAdd("Zeitraum");
                BtnAdd("Tb_Zeitraum","Zeitraum");
                //BtnAdd("horiz._Aufl.", "HA");
                BtnAddT("Tag");
                BtnAddT("Woche");
                BtnAddT("Monat");
                BtnAdd("ZRplus");
                BtnAdd("ZRminus");
                BtnAdd("Tb_ZRplus","ZRplus");
                BtnAdd("Tb_ZRminus","ZRminus");
                BtnAdd("ZRplus2");
                BtnAdd("ZRminus2");
                BtnAdd("Jetzt");
		BtnAdd2("Refresh","R");
                BtnAdd2("Tb_Refresh","R");
                BtnAddT("alle_Daten");
                BtnAddT("nur_Vector");
                BtnAddT("nur_Firma");
                if (BtnAlleDaten !=null && iDaten==ALLE)
                  BtnAlleDaten.setSelected(true);
                else if (BtnNurVector !=null && iDaten==VECTOR)
                  BtnNurVector.setSelected(true);
                else if (BtnNurFirma !=null && iDaten==FIRMA)
                  BtnNurFirma.setSelected(true);
                if (BtnNurVector !=null && VecAic!=null && VecAic.size()>=10000)
                  BtnNurVector.setEnabled(false);
                JButton BtnSuche=BtnAdd2("Tb_Suche","S");
                if (BtnSuche != null)
                {
                  if (TabOutliner.getPos("Eig",0)==-1)
                    BtnSuche.setVisible(false);
                }
                BtnAdd2("Tb_Formular", "F");
                BtnAdd2("Modelle", "M");
                BtnAdd2("Tb_Abfrage", "A");
                BtnAdd("WW");
                BtnAdd("DruckConfig");
                BtnAdd("user");
                BtnAdd("ParaSMTP");
                LblMandant=getLabel("Mandant");
                LblUser=getLabel("User");
                if (LblMandant!=null)
                	LblMandant.setText("All (1)");
                if (LblUser!=null)
                	LblUser.setText("xy");
                Static.addActionListener(getFormularButton("SyncStamm"),new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					SyncStamm.start(g,null).show(); //üt
				}
			});
                Static.addActionListener(getFormularButton("Abschluss"),new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                Abschluss.get(g).show(); //üt
                        }
                });
                
                Static.addActionListener(getFormularButton("Aufgabe"),new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                DefAufgabe.get(g,0).show(); //üt
                        }
                });

                if (g.History())
                {
                  JButton BtnH=getButton("Stamm-History");
                  if (BtnH !=null)
                  {
                    //BtnH.setFocusable(false);
                    BtnH.setEnabled(true);
                    BtnH.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent ev) {
                        ShowAbfrage.showHistory3(g, self, iStamm,dtEin);
                      }
                    });
                  }
                }
		BtnCheck=getButton("Check");
                Static.addActionListener(BtnCheck,new ActionListener()
                    {
                            public void actionPerformed(ActionEvent ev)
                            {
                              //BtnSave.setEnabled(false);
                              NeuCheck(false); //üt
                            }
                    });
               /*if (BtnCheck !=null || isNeuCheck())
                 BtnSave = g.getButton("Speichern");*/
               if (ReadOnly() && getButton("Speichern") != null)
                 getButton("Speichern").setEnabled(false);
	}//Events

      private void Modellberechnung(JButton Btn,boolean bDebug,boolean bKeinReCalc)
      {
//    	  g.fixtestError("Modellberechnung "+Btn.getText());
        int iPos=g.TabBegriffe.getPos("Aic",TabFormular.getI("Aic"));
                  if (iPos>=0)
                  {
                        Btn.setEnabled(false);
                        String s=g.getBegBez2(iPos);
                        g.fixtestInfo(Save.now()+"->Berechnung "+s+" ausgelöst");
                        int iMbits=g.TabBegriffe.getI(iPos,"bits");
                        //int pane = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Berechnen",new String[] {"<"+s+">"});
                        if (!g.bTestPC && (iMbits&Global.cstNurTest)>0)
                        {
                      	  new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("nurTest");
                      	  return;
                        }
                        if((iMbits&Global.cstKeineFrage)>0 || new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Berechnen",
                            new String[] {s+" "+g.getBegriffS("Show", "fuer")+" "+g.getVonBis("dd.MM.yyyy",false)}) == Message.YES_OPTION)
                        {
                          thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
//                          g.fixtestError("Cursor geändert");
//                          Static.sleep(2000);
                          long lClock=Static.get_ms();
                          g.saveSqlTime("Calc1",0,-1,s,getBegriff(),iStamm);
                          bCalc=true;
                          //setDialog();
                          boolean bMSave=(iMbits&Global.cstSave)>0;
                          if (bMSave)
                          {
                            bCalc = SpeichereDaten(true);
                            if (bCalc)
                              ResetModified();
                          }
                          if (bCalc && (!bMSave || bCalcS))
                          {
                            
//                            new Thread(new Runnable()
//            				{
//            					public void run()
//            					{
            						//g.fixtestError("Berechne Modell in Thread2");
            						boolean bBew=(iMbits&Global.cstBew)>0;
                                    
                                    Vector Vec=!isMultiselect() && (iMbits&Global.cstMenge)>0 ? getVecHaupt():null;
                                    if (Vec==null)
                                      Vec=VecAic;
                                    if (g.Prog())
                                    {
                                      g.progInfo("Vec   =" + Vec);
                                      g.progInfo("VecAic=" + VecAic);
                                    }
//            						int iMbits=g.TabBegriffe.getI(iPos,"bits");
		                            if (bKeinReCalc && (iMbits&Global.cstRecalc)>0)
		                              iMbits-=Global.cstRecalc;
		                            boolean bDebug2=g.Def() && bDebug; // shift
//		                            if (bDebug2) g.setDebug(true);
		                            int iModBegriff=g.TabBegriffe.getI(iPos,"Aic");
		                            if (bDebug2)
	                        		  Aufruf.addDebugModell(iModBegriff);
//	                        	  	else
//	                        		  Aufruf.removeDebugModell(iModBegriff);
		                            if(bDebug2 && (iMbits & Global.cstThread) > 0)
		                              iMbits -= Global.cstThread;
		                            if ((iMbits&Global.cstThread)>0)
		                            {
		                              bCalc2=true;
		                              EnableModelle();
		                              //RefreshFormButtons();
		                            }
		                            int iAicNeu=TCalc.Berechnen(g,iModBegriff,iMbits,bBew ? iSatz:iStamm,isMultiselect() ? VecSelect:Vec,self,bMSave ? iProt:0,Btn);
		                            if ((iMbits&Global.cstErgebnis)>0)
		                              Btn.setText(TCalc.sErgebnis);
		                            //iBtn=-1;
//		                            if (bDebug2) g.setDebug(false);
		                            if (iAicNeu>0)
		                            {
		                              iStamm = iAicNeu;
		                              iSatz=iStamm;
		                            }
		                            if ((iMbits&Global.cstThread)==0)
		                            {
		                              if ((iMbits & Global.cstRefreshM) > 0 && iAicNeu >= 0)
		                                Refresh();
		                              else
		                                Btn.setEnabled(true);
		                              g.saveSqlTime("Calc2",0,Static.get_ms()-lClock,s,getBegriff(),iStamm);
		                              g.fixtestInfo(Save.now()+"->Berechnung "+s+" fertiggestellt");
		                            }
		                            else
		                              g.fixtestInfo(Save.now()+"->Berechnung "+s+" weitergeleitet");
		                            bCalc=false;
//		                            thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//            					}
//            				}).start();
		                            
                          }
//                          else
                        	  thisFrame.setCursor(Cursor.getDefaultCursor());// new Cursor(Cursor.DEFAULT_CURSOR));
                        }
                        else
                          Btn.setEnabled(true);
                  }

      }

      private Vector getVecHaupt()
      {
        int iPos=TabOutliner.getPos("Eig",0);
        if (iPos<0)
          return null;
        else
        {
          Vector<Integer> Vec=new Vector<Integer>();
          JCOutliner Out=(JCOutliner)TabOutliner.getInhalt("Gid",iPos);
          Vector VecC=Out.getRootNode().getChildren();
          for (int i=0;i<VecC.size();i++)
            Vec.addElement((Integer)((JCOutlinerNode)VecC.elementAt(i)).getUserData());
          return Vec;
        }
      }

      private void Speicherfrage()
      {
        //if (g.Prog() || g.TestPC())
        //    new Exception().printStackTrace();
        if (bNeuCheck)
          return;
        int iMO=bKeinNeu && iSatz<=0 ? Message.NO_OPTION:new Message(Message.YES_NO_CANCEL_OPTION, (JFrame)thisFrame, g).showDialog("speichernZuerst");
        if (iMO == Message.YES_OPTION)
          SpeichereDatenM(true);
        else if (iMO == Message.NO_OPTION)
          ResetAll();
      }

      private void neu(boolean rbKopie)
      {
        if (Modified())
        {
          //new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("speichernZuerst");
          Speicherfrage();
          return;
        }

        bKopie = rbKopie;
        TabEigenschaft.moveFirst();
        if (!TabEigenschaft.eof())
          ((Component)TabEigenschaft.getInhalt("Komponente")).requestFocus();
        if (Synchron())
            if(Typ()==Stamm)
            {
              int iPos=g.TabStammtypen.getPos("Aic",iStammtyp);
              if (iPos>=0)
        	g.TabStammtypen.setInhalt(iPos,"Sync",null);
            }
            else if(Typ()==Bewegung)
            {
              int iPos=g.TabErfassungstypen.getPos("Aic",Bewegungstyp());
              if (iPos>=0)
        	g.TabErfassungstypen.setInhalt(iPos,"Pool",null);
            }
        //g.fixInfo("FuelleEigenschaften neu");
        FuelleEigenschaften(0,false);
      }

      private void Jetzt()
      {
        g.setAktDate(true);
        Zeitraum.PeriodeToVec(g,g.getZA(0),true);
        Refresh();
      }

      private void changeZA(String s)
      {
        g.changeZA(s);
        Refresh();
      }

      private void changeZR(String s,boolean bRefreshAll)
      {
        /*DateWOD DWvon=new DateWOD(g.getVon());
        DateWOD DWbis=new DateWOD(g.getBis());
        if (s.startsWith("ZRplus"))
        {
          if (s.equals("ZRplus"))
            DWvon.next(Static.sZeitart);
          DWbis.next(Static.sZeitart);
        }
        else
        {
          DWbis.prev(Static.sZeitart);
          if (s.equals("ZRminus") || DWbis.equals(DWvon))
              DWvon.prev(Static.sZeitart);
        }
        g.setVonBis(DWvon.toTimestamp(),DWbis.toTimestamp());*/
        g.changeZR(s);
        if (bRefreshAll)
          Refresh();
      }

      private boolean NeuCheck(boolean rbSave)
      {
        if (FomNC==null)
        {
          //g.progInfo("NeuCheck: Stammtyp="+iStammtyp);
          FomNC = new JDialog((JFrame)thisFrame,g.getBegriffS("Dialog","Neu-Auswahl"),true);
          JPanel Pnl = new JPanel(new GridLayout(0,4,2,2));
          CheckNPAbfrage();
          //OutNeu.setRootVisible(false);
          OutNeu.getRootNode().setUserData(Static.Int0);
          FomNC.getContentPane().add("Center", OutNeu);
          ActionListener ALDP=new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              String s = ev.getActionCommand();
              if (s.equals("DP_neu") || s.equals("DP_save"))
              {
                int iAic=s.equals("DP_neu") ?0:((Integer)OutNeu.getSelectedNode().getUserData()).intValue();
                bSave0 = iAic == 0;
                FomNC.setVisible(false);
                if (iAic > 0)
                {
                  bReLoad = true;
                  bNeuCheck = true;
                  g.setSyncStamm(iStammtyp, iAic,iRolle);
                  //g.fixInfo("FuelleEigenschaften NeuCheck"+iAic);
                  FuelleEigenschaften(iAic, true);
                  bNeuCheck = false;
                  new Message(Message.INFORMATION_MESSAGE, (JFrame)thisFrame, g).showDialog("Anpassen_Speichern");
                }
              }
              else if (s.equals("DP_show"))
              {
                int iFom=SQL.getInteger(g,"select aic_begriff from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Frame")+
                  " and aic_stammtyp="+iStammtyp+" and "+g.bit("bits",Formular.cstInfo));
                //g.progInfo("Aic="+Sort.geti(OutNeu.getSelectedNode().getUserData())+", Vec="+g.getAllAics(OutNeu));
                EingabeFormular.HoleFormular(g, iFom,g.getAllAics(OutNeu),Sort.geti(OutNeu.getSelectedNode().getUserData()),false,FomNC,0,bSync);
              }
              else if (s.equals("DP_Abbruch"))
                FomNC.setVisible(false);
            }
          };
          JButton BtnNeu = g.getButton("DP_neu","DP_neu",ALDP);
          final JButton BtnSave = g.getButton("DP_save","DP_save",ALDP); //nur wenn gewählt
          final JButton BtnShow = g.getButton("DP_show","DP_show",ALDP); //nur wenn gewählt
          JButton BtnAbbruch = g.getButton("DP_Abbruch","DP_Abbruch",ALDP);
          BtnSave.setEnabled(false);
          BtnShow.setEnabled(false);
          OutNeu.addItemListener(new JCOutlinerListener()
          {
             public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev) {}
             public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)   {}
             public void outlinerNodeSelectBegin(JCOutlinerEvent ev)        {}
             public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
             {
               int iL=OutNeu.getSelectedNode().getLevel();
               BtnSave.setEnabled(iL>0);
               BtnShow.setEnabled(iL>0);
             }
             public void itemStateChanged(JCItemEvent ev)                   {}
          });

          FomNC.getRootPane().setDefaultButton(BtnShow);
          Pnl.add(BtnNeu);
          Pnl.add(BtnSave);
          Pnl.add(BtnShow);
          Pnl.add(BtnAbbruch);
          /*BtnSave.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              //g.progInfo("Save");
              // Show
              int iAic=((Integer)OutNeu.getSelectedNode().getUserData()).intValue();
              bSave0=iAic==0;
              FomNC.setVisible(false);
              if (iAic>0)
              {
                bReLoad = true;
                bNeuCheck=true;
                FuelleEigenschaften(iAic, true);
                bNeuCheck=false;
                new Message(Message.INFORMATION_MESSAGE, (JFrame)thisFrame, g).showDialog("Anpassen_Speichern");
              }
              // Save
            }
          });
          BtnShow.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              FomNC.setVisible(false);
              int iAic=((Integer)OutNeu.getSelectedNode().getUserData()).intValue();
              if (iAic>0)
              {
                bReLoad = true;
                bNeuCheck=true;
                FuelleEigenschaften(iAic, true);
                bNeuCheck=false;
              }
            }
          });*/


          FomNC.getContentPane().add("South", Pnl);
          FomNC.setSize(700, 500);
          Static.centerComponent(FomNC, (JFrame)thisFrame);
        }
        bSave0=false;
        if (fillNeuOutliner())
          FomNC.setVisible(true);
        else if (rbSave && new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("nicht_gefunden_speichern")==Message.YES_OPTION)
          return true;
        return bSave0;
      }

      private String CheckBedingung()
      {
        //g.progInfo("CheckBedingung");
        String s=null;
        for(int i=0;i<VecNP.size();i++)
        {
          Integer IntEig=(Integer)VecNP.elementAt(i);
          int iPos=g.TabEigenschaften.getPos("Aic", IntEig);
          if (iPos>=0)
          {
            int iEigBit = g.TabEigenschaften.getI(iPos,"Bits");
            if (((iEigBit & Global.cstNP) == Global.cstNPklar || (iEigBit & Global.cstNP) == Global.cstNPzwang) &&
                TabEigenschaft.posInhalt("Aic", IntEig) && !Check.isNull(TabEigenschaft.getInhalt("Komponente")))
            {
              String sDatentyp=TabEigenschaft.getS("Datentyp");
              Object Obj=TabEigenschaft.getInhalt("Komponente");
              //g.progInfo(g.TabEigenschaften.getS("Bezeichnung") + "/" + sDatentyp /*+ "=" + Check.getS(Obj)*/);
              if (s==null)
                s=" where (";
              else
                s+=" and (";
              if (sDatentyp.equals("Gruppe"))
                s+="v"+IntEig+"="+Check.getI(Obj)+" or v"+IntEig+" is null)";
              else if (sDatentyp.equals("Datum") || sDatentyp.equals("Eintritt"))
                s+="e"+IntEig+"="+g.DateTimeToString(((Datum)Obj).getDate())+" or e"+IntEig+" is null)";
              else
                s+="e"+IntEig+"='"+Check.getS(Obj)+"' or e"+IntEig+" is null)";
            }
          }
        }
        return s==null ? "":s;
      }

      @SuppressWarnings("unchecked")
	private void CheckNPAbfrage()
      {
        Abfrage Abf=new Abfrage(g,0,Abfrage.cstAbfrage);
        VecNP=new Vector();
        jclass.util.JCVector VecBezeichnung=new jclass.util.JCVector();
        VecBezeichnung.addElement(g.getBegriffS("Show","Bezeichnung"));
        VecBezeichnung.addElement("%");
        for(TabEigenschaft.moveFirst();!TabEigenschaft.eof();TabEigenschaft.moveNext())
        {
          int iPos=g.TabEigenschaften.getPos("Aic",TabEigenschaft.getI("Aic"));
          if (iPos>=0)
          {
            int iEigBit=g.TabEigenschaften.getI(iPos,"Bits");
            if ((iEigBit&Global.cstNP)>0)
            {
              //g.progInfo(g.TabEigenschaften.getS("Bezeichnung") + ":" + ((iEigBit & g.cstNP) / (g.cstNP / 3)));
              VecNP.addElement(g.TabEigenschaften.getInhalt("Aic",iPos));
              VecBezeichnung.addElement(g.TabEigenschaften.getS(iPos,"Bezeichnung"));
            }
          }
        }
        OutNeu.setColumnButtons(VecBezeichnung);
        OutNeu.setNumColumns(VecBezeichnung.size());
        OutNeu.setColumnAlignment(1,BWTEnum.MIDDLERIGHT);
        Abf.sAnfang="p2.aic_stamm,p2.bezeichnung";
        sSqlNP=" from (SELECT AIC_Stamm Aic,null proz,"+Abf.ZusaetzlicheSpalten(VecNP,Formular.Stamm,true)+
            " FROM Stammview p2 WHERE AIC_ROLLE is null and AIC_Stammtyp="+iStammtyp+g.getReadMandanten()+Abf.Ebenen();
        //sSqlNP2=Abf.Ebenen();
      }

      private int prozNP(Tabellenspeicher Tab)
      {
        //g.progInfo("prozNP-Anfang");
        double iAnz=0;
        double iOk=0;
        for(int i=0;i<VecNP.size();i++)
        {
          Integer IntEig=(Integer)VecNP.elementAt(i);
          if (TabEigenschaft.posInhalt("Aic", IntEig) && !Check.isNull(TabEigenschaft.getInhalt("Komponente")))
          {
            //iAnz+=2;
            iAnz++;
            String sDatentyp=TabEigenschaft.getS("Datentyp");
            boolean bDatum=sDatentyp.equals("Datum") || sDatentyp.equals("Eintritt");
            Object Obj=TabEigenschaft.getInhalt("Komponente");
            //System.err.println(TabEigenschaft.getS("Bez")+":"+sDatentyp+"/"+Obj);
            boolean bOk2=(sDatentyp.equals("Gruppe") && Check.getI(Obj)==Tab.getI("v"+IntEig) ||
                bDatum && ((Datum)Obj).getDate().equals(Tab.getDate("e"+IntEig)) ||
                !sDatentyp.equals("Gruppe") && !bDatum && Check.getS(Obj).equals(Tab.getS("e"+IntEig)));
            if (bOk2)
            {
              int iPos=g.TabEigenschaften.getPos("Aic",IntEig);
              if (iPos>=0 && (g.TabEigenschaften.getI(iPos,"Bits")&Global.cstNP)==Global.cstNPklar)
        	return 200;
            }
            //g.progInfo("prozNP1:"+IntEig+"/"+(g.TabEigenschaften.getI("Bits")&g.cstNP));
            if (bOk2)
              iOk++;//iOk+=2;
            else
            {
              int iPos=g.TabEigenschaften.getPos("Aic",IntEig);
              if (iPos>=0 && (g.TabEigenschaften.getI(iPos,"Bits")&Global.cstNP)==Global.cstNPhoch)
              {
               //g.progInfo("prozNP2:"+Tab.getS("e"+IntEig)+"/"+Check.getS(Obj));
               if(!Tab.isNull("e"+IntEig) && Tab.getS("e"+IntEig).toUpperCase().startsWith(Check.getS(Obj).toUpperCase()))
                   iOk+=Check.getS(Obj).length()*1.0/Tab.getS("e"+IntEig).length();
              }
            }
          }
        }
        //g.progInfo("prozNP-Ende");
        return iAnz==0 ? 0:(int)Math.round(100*iOk/iAnz);
      }

      private boolean fillNeuOutliner()
      {
        JCOutlinerFolderNode NodeRoot=(JCOutlinerFolderNode)OutNeu.getRootNode();
        NodeRoot.removeChildren();
        Tabellenspeicher Tab=new Tabellenspeicher(g,"SELect *"+sSqlNP+CheckBedingung(),true);
        //Tab.showGrid();
        for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
          Tab.setInhalt("proz",prozNP(Tab));
        Tab.sort("proz",false);
        Tab.moveFirst();
        /*if (Tab.getI("proz")>100)
        {
          bReLoad = true;
          bNeuCheck=true;
          FuelleEigenschaften(Tab.getI("aic_stamm"), true);
          bNeuCheck=false;
          return false;
        }*/
        int iProzOld=0;
        int iAnz=0;
        if (Tab.getAnzahlElemente(null)>0 && Tab.getI("proz")>35)
          for (; !Tab.eof(); Tab.moveNext())
          {
            if (Tab.getI("proz") != iProzOld)
            {
              iProzOld = Tab.getI("proz");
              iAnz++;
            }
            if (iAnz > 0 && Tab.getI("proz") <= 35 || iAnz > 2)
              return true;
            Vector<Object> VecSpalten = new Vector<Object>();
            VecSpalten.addElement(Tab.getS("bezeichnung"));
            VecSpalten.addElement(Tab.getInhalt("proz"));
            for (int i = 0; i < VecNP.size(); i++)
              VecSpalten.addElement(Format(Tab,Sort.geti(VecNP,i)));
            JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
            Node.setUserData(Tab.getInhalt("aic_stamm"));
          }
        else
          return false;
        OutNeu.folderChanged(NodeRoot);
        OutNeu.selectNode(NodeRoot,null);
        return true;
      }

      private Object Format(Tabellenspeicher Tab,int iEig)
      {
        String sDt=g.TabEigenschaften.getInfoS(iEig,"Datentyp");
        //g.progInfo(sDt+"/"+iEig+":"+Tab.getS("E"+iEig));
        if (sDt.equals("Datum") || sDt.equals("Eintritt"))
          return new Zeit((Date)Tab.getInhalt("E"+iEig),"dd.MM.yyyy");
        else
          return Tab.getS("E"+iEig);
      }

      private Object getObject(Object Obj,boolean bDlg)
        {
          if(Obj instanceof AUTextArea)
            return ((AUTextArea)Obj).getValue();
          else if(Obj instanceof AUEkitCore)
              return ((AUEkitCore)Obj).getValue();
//          else if(Obj instanceof HtmlEingabe)
//            return ((HtmlEingabe)Obj).getValue();
          else if(Obj instanceof ComboSort)
            return ((ComboSort)Obj).getSelectedItem();
          else if(Obj instanceof Text)
            return ((Text)Obj).getText();
          else if(Obj instanceof HierarchieEingabe)
            return ((HierarchieEingabe)Obj).getCombo();
          else if(Obj instanceof AUComboList)
            return ((AUComboList)Obj).getComboBox().getCombo();
          else if(Obj instanceof RolleEingabe)
            return ((RolleEingabe)Obj).Cbo.getCombo();
          else if(Obj instanceof VonBisEingabe)
            return ((VonBisEingabe)Obj).getVonBis();
          else if(Obj instanceof MassEingabe)
            return ((MassEingabe)Obj).getAbsValue();
          else if(Obj instanceof WaehrungsEingabe)
            return ((WaehrungsEingabe)Obj).getAbsValue();
          else if(Obj instanceof AUPasswort)
                  return ((AUPasswort)Obj).getValue();
          else if(Obj instanceof EMail)
                  return ((EMail)Obj).getValue();
          else if(Obj instanceof FileEingabe)
                  return ((FileEingabe)Obj).getValue();
          else if(Obj instanceof GPS_Eingabe)
              return ((GPS_Eingabe)Obj).getName();
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
          else if (Obj instanceof Datum)
                  return ((Datum)Obj).getDateTime();
          else if (Obj instanceof AUCheckBox)
            return bDlg ? new Boolean(((AUCheckBox)Obj).isSelected()):((AUCheckBox)Obj).toString();
          else  if (Obj instanceof Zahl)
            return new Double(((Zahl)Obj).doubleValue());
          else if (Obj instanceof AUFarbe)
            return Long.toHexString(((AUFarbe)Obj).getValue());
          else if (Obj instanceof AUOutliner)
            return Sort.geti(((AUOutliner)Obj).getSelectedNode().getUserData());
          else if (Obj instanceof SpinBoxAuswahl)
            return ((SpinBoxAuswahl)Obj).getValue();
          else if (Obj instanceof RadioAuswahl)
            return ((RadioAuswahl)Obj).getValue();
          else
          {
                  g.printError("EingabeFormular.getObject: "+Static.className(Obj)+" steht nicht zur Verfügung!",getBegriff());
                  return ""+Obj;
          }
        }

      private void Refresh()
      {
        iRA++;
        //JCOutliner Gid2 = TabOutliner.posInhalt("Eig",0) ? (JCOutliner)TabOutliner.getInhalt("Gid"):null;
        //g.progInfo(iRA+". Refresh:"+iStamm+"/"+Gid2.getSelectedNode());
        g.defInfo2("                                     Refresh  ---------------------------------  Refresh "+iRA+":"+g.getVonBis("dd.MM.yyyy",true));
        if (Modified() && !SpeichereDatenM(AutoSave()))
            return;

        long lClock = Clock.startClock(clock);
        bRefreshMom=true;
        if (TabOutliner == null)
          return;
        bMulti=Multi();
        MonatsCheck(g);
        if (iStamm<0)
          iStamm=iStammOld;
        if (Typ()==Stamm && iRolle>0 && iStamm>0 && !keinZR())
        {
          if (!SQL.exists(g,"select aic_stamm from stammview where aic_rolle="+iRolle+" and aic_stamm=?",""+iStamm))
          {
            iStammOld = iStamm;
            iStamm = -1;
          }
        }
        /*if (iFreiTyp==1)
          {
            if (iBtn<0)
              iBtn=new DateWOD(g.getVon()).getMonth()-1;
            Btn[iWtOld].setBackground(g.ColTable);
            Btn[iBtn].setBackground(g.ColSelect);
            DateWOD dt = new DateWOD(g.getVon());
            iWtOld = iBtn;
            dt.setMonth(iWtOld + 1);
            g.setVon(dt.toTimestamp());
            g.setMonth();
            Static.sZeitart = "Monat";
            Zeitraum.PeriodeToVec(g, Static.sZeitart, true);
          }*/

        //g.progInfo("Refresh: vorher="+Zeitraum.VecPerioden);
        //if (Bewegungstyp()>0) // seit 23.11.2005 (V 4.08.4) wieder wie V 4.08.1
          bTabRefresh=false;
          bCalc=true;
          for (TabOutliner.moveFirst();!TabOutliner.out();TabOutliner.moveNext())
          {
                TabOutliner.push();
                //boolean bKR=(((ShowAbfrage)TabOutliner.getInhalt("Abfrage")).iBits & Abfrage.cstKeinRefresh)==0;
                //g.progInfo("--------------------------------------------------------------- Refresh "+TabOutliner.getS("Bezeichnung")+":"+bZR);
                //if (bAlle || isRefresh() || bKR)
                  FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),bMulti/* && !TabOutliner.getS("Typ").equals("Haupt")*/ ? -1:iSatz,false,true);
                //g.progInfo("Refresh2="+Zeitraum.VecPerioden);
                TabOutliner.pop();
          }
        //if (!bHaupt)
                //Titelzusatz("");
          bCalc=false;
        //g.progInfo("Refresh3="+Zeitraum.VecPerioden);
        if (Typ()==Stamm)
        {
                int iStammSave=iStamm;
                bReLoad=true;
                //FuelleEigenschaften(0,true);
                //bReLoad=true;
                g.printInfo(2,"FuelleEigenschaften Refresh");
                FuelleEigenschaften(iStammSave,true);
        }
        else
        {
          JCOutliner Gid = TabOutliner.posInhalt("Eig",0) ? (JCOutliner)TabOutliner.getInhalt("Gid"):null;
          FuelleEigenschaften(Gid==null ? -1:Sort.geti(Gid.getSelectedNode().getUserData()), StammAusBew());
        }
        //g.progInfo("Refresh4=");//+Zeitraum.VecPerioden);
        if (TabAbf != null)
         for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
          if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
          {
            //if (bAlle || isRefresh() || (((AUTable)TabAbf.getInhalt("Komponente")).getBits() & Abfrage.cstKeinRefresh)==0)
            //{
              long lClock2 = Static.get_ms();
              //g.progInfo("--------- Refresh Tabelle "+((AUTable)TabAbf.getInhalt("Komponente")).sDefBez);
              AUTable Table=(AUTable)TabAbf.getInhalt("Komponente");
              if (Table.Menge())
                Table.Refresh(iDaten==ALLE || iDaten==FIRMA ? SQL.getVector("select aic_stamm from stammview p2 where aic_stammtyp="+iStammtyp+
                    (iDaten==FIRMA ?" and firma="+g.getFirma():"")+Rolle()+g.getReadMandanten(),g):VecAic,iKnoten);
              else
                Table.Refresh(bMulti ? -1:isBewBew() ? -iSatz : iStamm);
              Clock.add(clock,"AUTable",Table.sDefBez,lClock2);
            //}
          }
        refreshPlanung(true,true);
        /*g.fixInfo("   !!!   Planung in Refresh   !!!");
        for (Tabellenspeicher TabPlanung= getTab("Planung");!TabPlanung.out();TabPlanung.moveNext())
        {
          long lClock2 = Static.get_ms();
                Planung P=(Planung)TabPlanung.getInhalt("Komponente");
                Vector VecPStamm=g.getVecStamm(getBegriff(),iStammtyp);
                P.Refresh(P.Menge()?VecAic==null?VecPStamm!=null ? VecPStamm:SQL.getVector("select aic_stamm from stammview p2 where aic_stammtyp="+iStammtyp+g.getReadMandanten(),g)
                        :VecAic:Static.AicToVec(bMulti ? -1:iStamm),bMulti ? -1:iStamm,true);
              Clock.add(clock,"Planung",((Planung)TabPlanung.getInhalt("Komponente")).sBez,lClock2);
        }*/

        RefreshFormButtons();
        //g.progInfo("Refresh: nachher="+Zeitraum.VecPerioden);
        EnableModelle();
        bTabRefresh=true;
        iSatzOld = iSatz;
        tsVonOld=g.getVon();
        tsBisOld=g.getBis();
        //Knoten();
        //EnableBedingt();
        g.progInfo(" ---------------------------- Aktueller Stand2: "+iSatz+"/"+tsVonOld+" - "+tsBisOld);
        bRefreshMom=false;
        Clock.showClock(g,clock,"                                     Refresh",lClock);
      }

      private void RefreshFormButtons()
      {
	boolean bMulti=Multi();
        //g.saveSqlTime("RF1",0,-1,sTitel,getBegriff(),0);
        //g.lQryAb=0;
	//g.progInfo("----------------------------------------- RefreshFormButtons:"+iStamm+"/"+bMulti);
        long lClock2 = Static.get_ms();
        for(TabFormular.moveFirst();!TabFormular.eof();TabFormular.moveNext())
          if (TabFormular.getS("Gruppe").startsWith("Eingabe"))
          {
            //if (TabFormular.isNull("Eig"))
            //  TabFormular.setInhalt("Eig",SQL.getInteger(g,"select aic_eigenschaft from formular where aic_begriff="+TabFormular.getI("Aic")));
            int iPosTF=TAB_Frames.getPos("aic_begriff",TabFormular.getI("Aic"));
            if (iPosTF>=0)
            {
              long lClockMikro2=Static.get_Mikro_s();
              // Ampeln setzen
              if (TAB_Frames.getI(iPosTF,"AIC_Eigenschaft") > 0)
              {
                int iPos=g.TabEigenschaften.getPos("Aic",TAB_Frames.getI(iPosTF,"AIC_Eigenschaft"));
                String sDT=g.TabEigenschaften.getS(iPos,"Datentyp");
                Image Img=null;
                if (!bMulti)
                {
                	//g.fixtestError("RefreshFormButtons: Beg="+TabFormular.getI("Aic")+"->"+sDT);
                  if (sDT.equals("Ampel"))
                    Img = Ampel.set(g.getAmpel(TAB_Frames.getI(iPosTF,"AIC_Eigenschaft"), iStamm));
                  else if (sDT.equals("BewStamm"))
                  {
                    long lClockMikro=Static.get_Mikro_s();
                    //&& g.TabStammBild.posInhalt("Aic", SQL.getInteger(g,"select s.aic_stamm from bew_stamm s"+g.join("bewview","s","bew_pool")+g.join("bew_stamm","s2","bewview","bew_pool")+" where s.aic_eigenschaft="+TAB_Frames.getI(iPosTF,"AIC_Eigenschaft")+" and s2.aic_stamm="+iStamm)))
                    String s="select s.aic_stamm from bew_stamm s join bewview on bewview.aic_bew_pool=s.aic_bew_pool where s.aic_eigenschaft="+TAB_Frames.getI(iPosTF,"AIC_Eigenschaft")+" and bewview.anr=?";
                    if (iStammtyp!=g.iSttANR)
                      s="select s.aic_stamm from bew_stamm s"+g.join("bewview","s","bew_pool")+g.join("bew_stamm","s2","bewview","bew_pool")+" where s.aic_eigenschaft="+TAB_Frames.getI(iPosTF,"AIC_Eigenschaft")+" and s2.aic_stamm=?";
                    //g.progInfo(s);
                    Img = g.getGif(g.TabStammBild, SQL.getInteger(g,s,0,""+iStamm));
                    if (Img==null)
                    	Img=Ampel.set(0);
                    if (g.Clock2())
                      g.clockMikroInfo(" ... BewStamm "+g.TabBegriffe.getBezeichnungS(TabFormular.getI("Aic")),lClockMikro);
                    //Img = (Image)g.TabStammBild.getInhalt("Bild");
                  }
                }
                if (Img != null)
                  ((JButton)TabFormular.getInhalt("Button")).setIcon(new ImageIcon(Img));
              }
              // Aufruf-Prüfung
              if (TAB_Frames.getI(iPosTF,"AIC_Abfrage") > 0 && !bMulti)
              {
                long lClockMikro=Static.get_Mikro_s();
                ShowAbfrage Abf=(ShowAbfrage)TAB_Frames.getInhalt("Abf",iPosTF);
                if (Abf==null)//TabFormular.isNull("Comp"))
                {
                  Abf = new ShowAbfrage(g, TAB_Frames.getI(iPosTF, "AIC_Abfrage"), Abfrage.cstAbfrage);
                  TAB_Frames.setInhalt(iPosTF,"Abf",Abf);
                }
                Tabellenspeicher Tab=Abf.getDaten(0,iStamm>0 ? iStamm:0,null,thisFrame);
                //Tab.showGrid(""+iStamm);
                boolean b=Tab != null && !Tab.isEmpty();
                ((JButton)TabFormular.getInhalt("Button")).setEnabled(b);
                //g.fixtestError("RefreshFormButtons.Aufruf von "+g.getBegBez(TabFormular.getI("Aic"))+":"+b);
                if (g.Clock2())
                  g.clockMikroInfo(" ... Abfrage "+g.TabBegriffe.getBezeichnungS(TabFormular.getI("Aic")),lClockMikro);
              }
              else
              {
                //g.TabBegriffe.push();
                int iPos=g.TabBegriffe.getPos("Aic",TabFormular.getI("Aic"));
                //boolean bSttGleich=Bewegungstyp()==0 && g.TabBegriffe.getI("Erf")==0 && iStammtyp==g.TabBegriffe.getI("Stt");
                //boolean bMS= bMulti && bSttGleich && (g.TabBegriffe.getI("bits")&cstNbMulti)==0;
                //((JButton)TabFormular.getInhalt("Button")).setEnabled(g.Berechtigung() && ((g.TabBegriffe.getI("bits")&cstOhneStamm)>0 || bMS || !bMulti && iStamm>0));
                ((JButton)TabFormular.getInhalt("Button")).setEnabled(FormOk(null,null,iPos));
                //g.TabBegriffe.pop();
              }
              if (g.Clock2())
                  g.clockMikroInfo("Refresh Formular-Knopf "+g.TabBegriffe.getBezeichnungS(TabFormular.getI("Aic")),lClockMikro2);
            }
            else
              g.printError("EingabeFormular.RefreshFormButtons: Formular " + TabFormular.getI("Aic") + " nicht gefunden!",getBegriff());
          }
          else if (TabFormular.getS("Gruppe").equals("Modell"))
          {
            g.checkModelle();
            int iPos=g.TabModelle.getPos("aic_begriff", TabFormular.getI("Aic"));
            if (bCalc2)
              ((JButton)TabFormular.getInhalt("Button")).setEnabled(false);
            else if (iPos>=0)
            {
              int iEig=g.TabModelle.getI(iPos,"AIC_Eigenschaft");
              if (iEig > 0)
              {
                long lClockMikro=Static.get_Mikro_s();
                int iPosEig=g.TabEigenschaften.getPos("Aic",iEig);
                String sDT=g.TabEigenschaften.getS(iPosEig,"Datentyp");
                Image Img=null;
                if (sDT.equals("Ampel"))
                  Img = Ampel.set(g.getAmpel(iEig, iStamm));
                else if (sDT.equals("BewStamm"))
                {
                  //&& g.TabStammBild.posInhalt("Aic", SQL.getInteger(g,"select s.aic_stamm from bew_stamm s"+g.join("bewview","s","bew_pool")+g.join("bew_stamm","s2","bewview","bew_pool")+" where s.aic_eigenschaft="+iEig+" and s2.aic_stamm="+iStamm)))
                  //Img = (Image)g.TabStammBild.getInhalt("Bild");
                  Img = g.getGif(g.TabStammBild,SQL.getInteger(g,"select s.aic_stamm from bew_stamm s"+g.join("bewview","s","bew_pool")+g.join("bew_stamm","s2","bewview","bew_pool")+" where s.aic_eigenschaft="+iEig+" and s2.aic_stamm="+iStamm));
                }
                if (Img != null)
                  ((JButton)TabFormular.getInhalt("Button")).setIcon(new ImageIcon(Img));
                if (g.Clock2())
                  g.clockMikroInfo("Refresh Modell-Knopf "+g.TabBegriffe.getBezeichnungS(TabFormular.getI("Aic")),lClockMikro);
              }
              int iMbits = g.TabModelle.getI(iPos, "bits");
              if (g.TabModelle.getI(iPos,"AIC_Abfrage") > 0 && !bMulti && (iMbits & g.cstMDialog)==0)
              {
                long lClockMikro=Static.get_Mikro_s();
                ShowAbfrage Abf=(ShowAbfrage)g.TabModelle.getInhalt("Abf",iPos);
                if (Abf==null)
                {
                  Abf = new ShowAbfrage(g, g.TabModelle.getI(iPos, "AIC_Abfrage"), Abfrage.cstAbfrage);
                  g.TabModelle.setInhalt(iPos,"Abf",Abf);
                }
                Tabellenspeicher Tab=Abf.getDaten(0,iStamm>0 ? iStamm:0,null,thisFrame);
                //Tab.showGrid(""+iStamm);
//                int iMbits = g.TabModelle.getI(iPos, "bits");       
                boolean b=(iMbits&g.cstAmpel)>0 || Tab != null && !Tab.isEmpty();
                ((JButton)TabFormular.getInhalt("Button")).setEnabled(b);
//                g.fixtestError("RefreshFormButtons.Modell von "+g.getBegBez(TabFormular.getI("Aic"))+":"+b);
                if ((iMbits&g.cstAmpel)>0)
                {
                	int iStatus=Tab==null || Tab.size()==0 ? 0:Tab.getI("v"+Abf.getSpalten().getS("Kennung2"));
                	//g.fixtestError("Ampel-Status für "+((JButton)TabFormular.getInhalt("Button")).getText()+"="+iStatus+" bei v"+Abf.getSpalten().getS("Kennung2"));
                	((JButton)TabFormular.getInhalt("Button")).setIcon(new ImageIcon(Ampel.setStamm(iStatus)));
                }
                if (g.Clock2())
                  g.clockMikroInfo(" ... Abfrage "+g.TabBegriffe.getBezeichnungS(TabFormular.getI("Aic")),lClockMikro);
              }
            }
            else
              g.printError("EingabeFormular.RefreshFormButtons: Modell " + TabFormular.getI("Aic") + " nicht gefunden!",getBegriff());
          }
        Clock.add(clock,"Formulare","",lClock2);
        //g.saveSqlTime("RF2",0,-1,sTitel,getBegriff(),0);
        //g.lQryAb=1000;
      }

      private boolean FormOk(JCOutliner Gid,ShowAbfrage Abf,int iPos)
      {
	int iStt2=Abf==null ? iStammtyp:Abf.iStt;
	int iBew2=Abf==null ? Bewegungstyp():Abf.iBew;
        int iPosOut= Gid==null ? TabOutliner.getPos("Eig",0):-2;
        if (iPosOut>=0)
          Gid=(JCOutliner)TabOutliner.getInhalt("Gid",iPosOut);
	JCOutlinerNode Nod=Gid==null ? null:Gid.getSelectedNode();
	boolean bMulti2= Nod==null ? bMulti:Gid.getSelectedNodes().length>1;
	//int iSelect=iPosOut==-1 ? iStamm:Nod==null || Nod.getLevel()==0 ? 0:Sort.geti(Nod.getUserData());
	boolean bSttGleich= iBew2==0 && g.TabBegriffe.getI(iPos,"Erf")==0 && iStt2==g.TabBegriffe.getI(iPos,"Stt") || Abf != null && iBew2>0;
        boolean bMSok= !bMulti2 /*&& bSttGleich*/ || (g.TabBegriffe.getI(iPos,"bits")&cstNbMulti)==0;
        //g.testInfo("FormOk:"+bMulti2+"/"+bSttGleich+"->"+bMS);
        boolean b= bMSok && (g.Def() && Static.bDefBezeichnung || g.BerechtigungS(iPos) && ((g.TabBegriffe.getI(iPos,"bits")&cstOhneStamm)>0 || !bSttGleich || iStamm>0));//bMS || !bMulti2 && iSelect>0);
        //g.fixtestError("FormOk von "+g.getBegBez2(iPos)+" -> "+b+" bei Stamm="+iStamm);
        return b;
      }

      private boolean SpeichereDatenM(boolean bAutoSave)
      {
    	  if (!Modified())
    		  return true;
        //g.progInfo("SpeichereDatenM:"+bAutoSave);
        //TAB_Frames.showGrid("TAB_Frames");
        //g.testInfo("suche in TAB_Frames:"+getBegriff());
        int iPosTF=TAB_Frames.getPos("aic_begriff",getBegriff());
        int iModell=iPosTF<0 ?0:TAB_Frames.getI(iPosTF,"AIC_Modell");
        //g.testInfo("-> iModell="+iModell);
        if (iModell==0)
          return SpeichereDaten(bAutoSave);
        else
        {
          g.checkModelle();
          TabOutliner.posInhalt("Eig",0);
          int iPos=g.TabModelle.getPos("aic_modell", iModell);
          boolean b=berechne(g.TabModelle.getI(iPos,"aic_begriff"),'-',bAutoSave,0);
          //g.fixInfo("SpeichereDatenM:"+b+"/"+bCalcS+"/"+bCalc);
          return b;
        }
      }

      public void Beenden(int iArt)
      {
//        g.fixInfo("Beenden: Stamm="+iStamm);
        try
        {
          if (iArt < 2 && !ReadOnly())
          {
            bReLoad = false;
            if (iArt==-1)
              bEnde=true;
            /*if (iArt == 1)
              SpeichereDaten(true);
            else
            {*/
            boolean bReset=iArt == 0 && (bSpeichere || g.getMandant()==1) || iStamm==-1; // Beenden
            if (bReset && !bSpeichere)
            {
              //ResetModified();
              ResetAll();
              iStamm=-1;
            }
            boolean b=bReset ? true:SpeichereDatenM(iArt == 1 ? true:AutoSave());
            //g.fixInfo("->"+b+"/"+bSpeichere);
            bReLoad = true;
            if (!b)
              return;
            //}
          }
          else // Abbruch
          {
            //ResetModified();
            ResetAll();
            iStamm=-1;
          }
        }
        catch(Exception e)
        {
          e.printStackTrace();
          Static.printError("EingabeFormular.Beenden:"+e);
          bReLoad = true;
        }
        bOk=iArt==1;
        boolean b1=thisFrame.isVisible();
        //g.fixInfo("Beenden Visible="+b1);

        if (!b1)
          thisFrame.setVisible(true);
        boolean b=false;
        if (TabAbf != null)
        {
          for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
            if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
              b=b|((AUTable)TabAbf.getInhalt("Komponente")).saveColumns();
        }
        for (Tabellenspeicher TabJCTable= getTab("Planung");!TabJCTable.out();TabJCTable.moveNext())
          ((Planung)TabJCTable.getInhalt("Komponente")).setInfo();

        if (iArt<0)
          return;
//        else if (b)
//          AUTable.readColumns(g);
        if (Login())
        {
          saveFenster();
          if (g.TabFormulare.posInhalt("Aic",getBegriff()))
              g.TabFormulare.clearInhalt();
          ((JFrame)thisFrame).dispose();
        }
        else
          hide();
      }

	private void FuelleEFGroup(JPanel Pnl,int iBegriff,int iSpalten)
	{
		ShowAbfrage Abf=new ShowAbfrage(g,iBegriff,Abfrage.cstBegriff);
		//Abf.getSpalten().showGrid("EFGroup"+iBegriff);
                FuelleGruppe(true,g.getFont(Abf.iBegriff),Pnl,Abf,0,iSpalten);
		//Abf.getDaten();
	}

        private JComponent addEig(boolean bAbfrage,Font font,Tabellenspeicher Tab,JPanel Pnl22,String sDatentyp)
        {
          int iAic=bAbfrage ? Tab.getI("Kennung2"):Tab.getI("Aic");
          int iPosE=g.TabEigenschaften.getPos("Aic", iAic);
          //String sDatentyp = g.TabEigenschaften.getS(iPosE,"Datentyp");
          TabEigenschaft.newLine();
                      TabEigenschaft.setInhalt("Aic", iAic);
                      //TabEigenschaft.posInhalt("Aic", iAic);

                      String sEig = bAbfrage ? Tab.getS("Bezeichnung") : g.TabEigenschaften.getS(iPosE,"Bezeichnung");
                      String sFormat = bAbfrage ? Tab.getS("Format") : g.TabEigenschaften.getS(iPosE,"Format");
                      int iLaenge= bAbfrage ? Abfrage.getLaenge(Tab) : g.TabEigenschaften.getI(iPosE,"Laenge");
                      TabEigenschaft.setInhalt("Stammtyp",g.TabEigenschaften.getI(iPosE,"aic_stammtyp"));
                      //TabEigenschaft.setInhalt("Rolle",g.TabEigenschaften.getI("Rolle"));
                      TabEigenschaft.setInhalt("Stamm", g.TabEigenschaften.getI(iPosE,"aic_stamm"));
                      TabEigenschaft.setInhalt("bits",new Integer(bAbfrage ? Tab.getI("bits") :(g.TabEigenschaften.getI(iPosE,"bits") & 0xff) * 0x10000));
                      TabEigenschaft.setInhalt("bits2",bAbfrage ? Tab.getInhalt("bits2"):Static.Int0);
                      TabEigenschaft.setInhalt("bits3",bAbfrage ? Tab.getInhalt("bits3"):Static.Int0);
                      TabEigenschaft.setInhalt("Datentyp", sDatentyp);
                      if (Typ() == Bewegung)
                      {
                        sDatentyp = sDatentyp.equals("BewBool3") ? "Bool3": sDatentyp.equals("BewBoolean") ? "Boolean" :
                            /*sDatentyp.equals("BewVon_Bis") ? "von_bis":*/
                            //sDatentyp.equals("BewMass") ? "Mass":
                            //sDatentyp.equals("BewWaehrung") ? "Waehrung":
                            sDatentyp.equals("BewDauer") || sDatentyp.equals("BewZahl") ? "Double" : sDatentyp;
                      }

                      /*JLabel Lbl = new JLabel((Tab.getAnzahlElemente(null) == 1 || riMom>0) && (sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.endsWith("Buttons")) ? "" :
                                              " " + sEig + ":");
                      Lbl.setFont(Pnl.getFont() == g.fontStandard ? g.fontBezeichnung : Pnl.getFont());
                      Lbl.setBorder(new EmptyBorder(4, 0, 0, 0));
                      if (Static.bMemo)
                      {
                        String sInfo = g.TabEigenschaften.getS("Info");
                        if (!sInfo.equals(""))
                          Lbl.setToolTipText(sInfo);
                      }*/
                      TabEigenschaft.setInhalt("Bez", sEig);
                      //Pnl11.add(sNorth, Lbl);
                      //addPopup2(Lbl);
                      /*if (g.History() && !sDatentyp.equals("Aic") && !sDatentyp.equals("SysAic") &&
                          !sDatentyp.equals("Timestamp") && !sDatentyp.equals("Anlage") && !sDatentyp.equals("Benutzer") &&
                          !sDatentyp.equals("Mandant") && !sDatentyp.equals("Memo") && !sDatentyp.equals("Text") && !sDatentyp.equals("Bild"))
                      {
                        JButton BtnH = g.getButton("H");
                        BtnH.setFocusable(false);
                        TabEigenschaft.setInhalt("History", BtnH);
                        ///BtnH.setMargin(g.inset);
                        BtnH.setFont(Pnl.getFont());
                        BtnH.setBackground(g.ColEF);
                        BtnH.addActionListener(new ActionListener() {
                          public void actionPerformed(ActionEvent e) {
                            if (TabEigenschaft.posInhalt("History", e.getSource()))
                            {
                              ShowAbfrage.showHistory(g,self, ((JLabel)TabEigenschaft.getInhalt("Label")).getText(),TabEigenschaft.getI("Aic"), iStamm,TabEigenschaft.getS("Datentyp"),
                                                      Typ() == Bewegung && TabEigenschaft.posInhalt("Datentyp", "BewDatum") ? (Date)( (Datum) TabEigenschaft.getInhalt("Komponente")).getDateTime() :
                                                      TabEigenschaft.getS("Datentyp").equals("Stichtag")?dtEin:null, Bewegungstyp());
                            }
                          }
                        });
                        Pnl33.add(sNorth, BtnH);
                      }
                      else*/ //if (!sDatentyp.equals("Memo") && !sDatentyp.equals("Text") && !sDatentyp.endsWith("Bild"))
                      //  TabEigenschaft.setInhalt("History", null);
                      JComponent Edt=null;
                      if (sDatentyp.equals("StringSehrKurz") || sDatentyp.equals("StringKurz") || sDatentyp.equals("StringKurzOhne") || sDatentyp.equals("String60") ||
                          sDatentyp.equals("StringLang") || sDatentyp.equals("Stringx") || sDatentyp.endsWith("Bezeichnung") || sDatentyp.equals("Bezeichnung2") || sDatentyp.equals("Kennung"))
                      {

                        if (sDatentyp.endsWith("Bezeichnung") || sDatentyp.equals("Bezeichnung2") || sDatentyp.equals("Kennung"))
                        {
                          int iMax=sDatentyp.equals("Bezeichnung2") ? 255:sDatentyp.endsWith("Bezeichnung") ? 255 : 98;
                          Edt = new Text("", iLaenge <= 0  || iLaenge>iMax ? iMax : iLaenge,sDatentyp.equals("Kennung") ? Text.KENNUNG:0);
                        }
                        else {

                          //g.progInfo("bAbfrage="+bAbfrage+"/ bits="+(bAbfrage?Tab.getS("bits"):"null"));

                          //long lClock = Static.get_ms();
                          //int iL = g.TabEigenschaften.getI("Laenge");
                          if (iLaenge < 1)
                            iLaenge = sDatentyp.equals("StringSehrKurz") ? 10 : sDatentyp.equals("StringKurz") || sDatentyp.equals("StringKurzOhne") ? 30 :
                                sDatentyp.equals("String60") ? 60 : sDatentyp.equals("StringLang") || sDatentyp.equals("Stringx") ? 100 : -1;
                          //if ((Transact.iInfos&Transact.FILL)==0 || sDatentyp.equals("StringLang") || sDatentyp.equals("StringKurzOhne") || sDatentyp.equals("Stringx"))
                          //{
                            Edt = new Text("",iLaenge,(TabEigenschaft.getI("bits2")&Abfrage.cstZiffern)>0 ? Text.ZAHL:(TabEigenschaft.getI("bits2")&Abfrage.cstBuchZahl)>0 ? Text.SONDER:0);
                            //((ComboSort)Edt).setEditable(iLaenge);
                          /*}
                          else
                          {
                            Edt = new ComboSort(g);
                            ((ComboSort)Edt).fillDaten(iAic, iLaenge);
                          }*/
                          //((ComboSort) Edt).firePopupMenuWillBecomeInvisible();
                          //((ComboSort)Edt).setSorted(true);
                          /*g.printInfo(0,
                                      sEig + ":" + (Static.get_ms() - lClock) + " (" +
                                      ( (ComboSort) Edt).getNumber() + ')');*/
                          //if (bAbfrage && (Tab.getI("bits") & Abfrage.cstEditierbar) == 0)
                          //  Edt.setEnabled(false);
                        }
                        //Edt.setFont(Pnl.getFont());
                        //Pnl22.add(sNorth, Edt);

                        //TabEigenschaft.setInhalt("Sub", null);
                      }
                      else if (sDatentyp.equals("WWW"))
                        Edt = new WWW(g);
                      else if (sDatentyp.equals("E-Mail"))
                        Edt = new EMail(iLaenge==0 ? 60 :Math.min(iLaenge,60), g);
                      else if (sDatentyp.equals("Passwort"))
                        Edt = new AUPasswort(iLaenge);
                      else if (sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") || sDatentyp.equals("FixDoku") || sDatentyp.equals("Doku"))
                      {
                        Edt = new FileEingabe(g, !sDatentyp.equals("Pfad"),true,TabEigenschaft.getI("Aic"),(TabEigenschaft.getI("bits2")&Abfrage.cstSpNotSave)==0 && !ReadOnly());
                        ((FileEingabe)Edt).setFTP(sDatentyp.equals("FixDoku"));
                        ((FileEingabe)Edt).setDB(sDatentyp.equals("Doku"));
                      }
                      else if (sDatentyp.equals("Farbe"))
                        Edt = new AUFarbe( (JFrame) thisFrame, g, 0);
                      else if (sDatentyp.equals("Bits"))
                        Edt = new AUBits(g.TabEigenschaften.getI(iPosE,"aic_stammtyp"), g);
                      else if (sDatentyp.endsWith("Bild") || sDatentyp.equals("Bild2"))
                      {
                        BildEingabe Btn = new BildEingabe(thisFrame, g);
                        Btn.setFTP(sDatentyp.equals("FixBild"));
                        Btn.setDB(sDatentyp.equals("Bild2"));

                        Btn.setScale(400, 300);
                        Edt=Btn;
                        //Pnl22.add(sNorth, Btn);
                        //TabEigenschaft.setInhalt("Komponente", Btn);
                        //TabEigenschaft.setInhalt("Sub", null);
                        if (!bAbfrage || (Tab.getI("bits") & Abfrage.cstEditierbar) >0)
                          Btn.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                              if (ReadOnly())
                                if (TabEigenschaft.posInhalt("Datentyp", "WWW"))
                                  Static.OpenURL("" + ((WWW)TabEigenschaft.getInhalt("Komponente")).getValue());
                                else
                                  new Message(Message.INFORMATION_MESSAGE, (JFrame)thisFrame, g).showDialog("Web");
                              else
                              { //if (TabEigenschaft.posInhalt("Komponente",e.getSource()))
                                ((BildEingabe)e.getSource()).LadeBild();
                              }
                            }
                          });
                        //JButton BtnH = g.History() ? g.getButton("H"):null;
                        //BtnH.setFocusable(false);
                        //TabEigenschaft.setInhalt("History", null);//BtnH);
                        //JPanel Pnl333=new JPanel(new GridLayout(0,1));
                        if (!ReadOnly() && (!bAbfrage || (Tab.getI("bits") & Abfrage.cstEditierbar) >0))
                        {
                          //JButton BtnD = g.getButton("D");
                          ///BtnD.setMargin(g.inset);
                          //BtnD.setFont(Pnl.getFont());
                          //Pnl333.add(BtnD); // !!!
                          //Btn.setDelete(BtnD);
                          Btn.setEditable(true);
                        }
                        else
                          Btn.setEditable(false);
                        //Pnl33.add(sNorth,Pnl333);
                        /*if (BtnH != null)
                        {
                          BtnH.setBackground(g.ColEF);
                          ///BtnH.setMargin(g.inset);
                          BtnH.setFont(Pnl.getFont());
                          //Pnl333.add(BtnH); // !!!
                          BtnH.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                              if (TabEigenschaft.posInhalt("History", e.getSource()))
                              {
                                ShowAbfrage.showHistory(g,self, ((JLabel)TabEigenschaft.getInhalt("Label")).getText(), TabEigenschaft.getI("Aic"), iStamm, TabEigenschaft.getS("Datentyp"),
                                                        Typ() == Bewegung && TabEigenschaft.posInhalt("Datentyp", "BewDatum") ?
                                                        ( (Datum) TabEigenschaft.getInhalt("Komponente")).getDateTime() : null, Bewegungstyp());
                              }
                            }
                          });
                        }*/
                      }
                      else if (sDatentyp.equals("Memo") || sDatentyp.equals("Text"))
                      {
                        if (bAbfrage && (Tab.getI("bits3") & Abfrage.cstHtml)>0)
                        {
                        	//if (Static.bJavaFX)
                        	//	Edt= new HtmlEingabe(g,thisJFrame(),7);
                        	//else
                        		Edt= new AUEkitCore(g,thisJFrame(),7);
                        }
                        else
                        {
                          Edt = new AUTextArea(g, 7); //+(bAbfrage && (Tab.getI("bits3") & Abfrage.cstHtml)>0?AUTextArea.HTML:0));
                          if (iLaenge>0)
                        	  ((AUTextArea)Edt).setMaxLength(iLaenge);
                        }
                          //Edt.setFont(Pnl.getFont());
                          //Pnl22.add("Center", Edt);
                          //if (Tab.getAnzahlElemente(null) == 1)
                          //g.fixInfo("Parent="+Static.className(Pnl.getParent().getParent()));
                        JMenuItem MnuH=Edt instanceof AUTextArea ? ((AUTextArea)Edt).MnuH:/*Edt instanceof HtmlEingabe ?((HtmlEingabe)Edt).MnuH:*/Edt instanceof AUEkitCore ?((AUEkitCore)Edt).MnuH:null;

                          TabEigenschaft.setInhalt("History", MnuH);

                         if (g.History())
                          MnuH.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                              if (TabEigenschaft.posInhalt("History", e.getSource()))
                              {
                                //if (iSatz>0)
                                ShowAbfrage.showHistory(g,self, TabEigenschaft.getS("Bez"), TabEigenschaft.getI("Aic"), iStamm, TabEigenschaft.getS("Datentyp"),
                                                        Typ() == Bewegung && TabEigenschaft.posInhalt("Datentyp", "BewDatum") ?
                                                        ( (Datum) TabEigenschaft.getInhalt("Komponente")).getDateTime() : null, Bewegungstyp()==0 ? 0:iSatz,-1);
                              }
                            }
                          });

                        //TabEigenschaft.setInhalt("Komponente", Edt);
                        //TabEigenschaft.setInhalt("Sub", null);
                      }
                      else if (sDatentyp.equals("Mandant"))
                      {
                        Edt = new ComboSort(g);
                        ((ComboSort)Edt).fillDefTable("Mandant",false);
                      }
                      else if (sDatentyp.equals("Aic") || sDatentyp.equals("SysAic") || sDatentyp.equals("Timestamp") ||
                               sDatentyp.equals("Anlage") || sDatentyp.equals("Benutzer") || sDatentyp.equals("User"))
                        Edt = new JLabel(sDatentyp);
                      else if (sDatentyp.equals("Mehrfach"))
                      {
                        g.printError(getTitle()+": Mehrfach ohne Outliner wird nicht mehr unterstützt",getBegriff());
                        Edt = new JLabel("!!!");
                      }
                      else if (sDatentyp.endsWith("Radio"))
                      {
                        AUComboList Cbo = new AUComboList(null,getBegriff(),getBegriff(), iAic, g.TabEigenschaften.getI(iPosE,"aic_stammtyp"), g, bAbfrage ? Tab.getI("Filter") :
                                                            g.TabEigenschaften.getI(iPosE,"Abfrage"), (TabEigenschaft.getI("bits") & (Global.cstKeinAutoDate * 0x10000 + Abfrage.cstKeinAutoDate)) == 0,
                                                            (TabEigenschaft.getI("bits") & (Global.cstAlways * 0x10000)) == 0,false,false);
                        JPanel PnlTemp = new JPanel(new GridLayout(iLaenge==0?1:0,iLaenge));
                        PnlTemp.setBackground(Static.ColEF);
                        Vector Vec=Cbo.getComboBox().getItems();
                        if (TabRadio==null)
                            TabRadio = new Tabellenspeicher(g,new String[] {"Aic","Cbo","Btn","Grp"});
                        ButtonGroup RadGroup=new ButtonGroup();
                        /*TabRadio.addInhalt("Aic",0);
                        TabRadio.addInhalt("Cbo",Cbo);
                        JRadioButton Rad = new JRadioButton();
                        RadGroup.add(Rad);
                        TabRadio.addInhalt("Btn",Rad);*/

                        for(int i=0;i<Vec.size();i++)
                        {
                            Combo combo=(Combo)Vec.elementAt(i);
                            int iAicStamm=combo.getAic();

                              //Image Img = g.TabStammBild.posInhalt("Aic", iAicStamm) ? (Image)g.TabStammBild.getInhalt("Bild") : null;
                              JRadioButton Rad = new JRadioButton(combo.getBezeichnung());
                              g.setRadiobuttonIcon(Rad);
                              Rad.setBackground(Static.ColEF);
                              //g.setSchrift(0,Rad);
                              RadGroup.add(Rad);
                              //Btn.setToolTipText(combo.getBezeichnung());
                              TabRadio.addInhalt("Aic",iAicStamm);
                              TabRadio.addInhalt("Cbo",Cbo);
                              TabRadio.addInhalt("Btn",Rad);
                              TabRadio.addInhalt("Grp",RadGroup);
                              Rad.addActionListener(new ActionListener()
                              {
                                public void actionPerformed(ActionEvent e)
                                {
                                  if (TabRadio.posInhalt("Btn", e.getSource()))
                                  {
                                    ComboSort Cbo2=((AUComboList)TabRadio.getInhalt("Cbo")).getComboBox();
                                    int iCboOld=Cbo2.getSelectedAIC();
                                    Cbo2.setSelectedAIC(TabRadio.getI("Aic"));
                                    Cbo2.setAktAIC(iCboOld);
                                  }
                                }
                              });
                              //if (iAicStamm>0)
                                g.addComp(PnlTemp,Rad);
                                //PnlTemp.add(Rad);
                        }
                        Edt=PnlTemp;
                        //Pnl22.add(sNorth, PnlTemp);
                        TabEigenschaft.setInhalt("Komponente", Cbo);
                        //TabEigenschaft.setInhalt("Sub", null);
                      }
                      else if (sDatentyp.endsWith("Buttons"))
                      {
                           AUComboList Cbo = new AUComboList(null,getBegriff(),getBegriff(), iAic, g.TabEigenschaften.getI(iPosE,"aic_stammtyp"), g, bAbfrage ? Tab.getI("Filter") :
                                                            g.TabEigenschaften.getI(iPosE,"Abfrage"), (TabEigenschaft.getI("bits") & (Global.cstKeinAutoDate * 0x10000 + Abfrage.cstKeinAutoDate)) == 0,
                                                            (TabEigenschaft.getI("bits") & (Global.cstAlways * 0x10000)) == 0,false,false);
                          JPanel PnlTemp = new JPanel(new GridLayout(iLaenge==0?1:0,iLaenge));
                          Vector Vec=Cbo.getComboBox().getItems();
                          if (TabButtons==null)
                            TabButtons = new Tabellenspeicher(g,new String[] {"Aic","Cbo","Btn"});
                          for(int i=0;i<Vec.size();i++)
                          {
                            Combo combo=(Combo)Vec.elementAt(i);
                            int iAicStamm=combo.getAic();
                            if (iAicStamm>0)
                            {
                              Image Img = g.getGif(g.TabStammBild, iAicStamm);//g.TabStammBild.posInhalt("Aic", iAicStamm) ? (Image)g.TabStammBild.getInhalt("Bild") : null;
                              JButton Btn = Img == null ? new JButton(combo.getBezeichnung()) : new JButton(new ImageIcon(Img));
                              //g.setSchrift(0,Btn,g.fontButton);
                              Btn.setFont(font==null ? g.fontButton:font/*new java.awt.Font("SansSerif",Font.BOLD,12)*/);
                              Btn.setToolTipText(combo.getBezeichnung());
                              TabButtons.addInhalt("Aic",iAicStamm);
                              TabButtons.addInhalt("Cbo",Cbo);
                              TabButtons.addInhalt("Btn",Btn);
                              Btn.addActionListener(new ActionListener()
                              {
                                public void actionPerformed(ActionEvent e)
                                {
                                  if (TabButtons.posInhalt("Btn", e.getSource()))
                                  {
                                    ComboSort Cbo2=((AUComboList)TabButtons.getInhalt("Cbo")).getComboBox();
                                    Cbo2.setSelectedAIC(TabButtons.getI("Aic"));
                                    Cbo2.setAktAIC(0);
                                    SpeichereDatenM(true);
                                    //g.progInfo("Button auf:"+Cbo2.Modified()+"/"+Cbo2.getSelectedAIC());
                                    Cbo2.setSelectedAIC(0);
                                  }
                                }
                              });
                              //g.addComp(PnlTemp,Btn);
                              //PnlTemp.setFont(new java.awt.Font("SansSerif",Font.BOLD,20));
                              PnlTemp.add(Btn);
                            }
                          }
                          Edt=PnlTemp;
                          //Pnl22.add(sNorth, PnlTemp);
                          TabEigenschaft.setInhalt("Komponente", Cbo);
                          //TabEigenschaft.setInhalt("Sub", null);
                      }
                      else if (sDatentyp.equals("Gruppe") || sDatentyp.equals("BewStamm"))
                      {
//                    	  g.fixtestError("EF bei "+sDatentyp+": "+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+", bAbf="+bAbfrage);
//                    	  if (bAbfrage)
//                    		  Tab.showGrid("Tab bei "+g.TabEigenschaften.getS(iPosE,"Bezeichnung"));
                          AUComboList Cbo = new AUComboList(null,getBegriff(),getBegriff(), iAic, g.TabEigenschaften.getI(iPosE,"aic_stammtyp"), g, bAbfrage ? Tab.getI("Filter") :
                                                            g.TabEigenschaften.getI(iPosE,"Abfrage"), (TabEigenschaft.getI("bits") & (Global.cstKeinAutoDate * 0x10000 + Abfrage.cstKeinAutoDate)) == 0,
                                                            (TabEigenschaft.getI("bits") & (Global.cstAlways * 0x10000)) == 0,true,false);
                          Cbo.getComboBox().bCombo2=bAbfrage && (Tab.getI("bits") & Abfrage.cstShow)==Abfrage.cstCombo2;
                          if (iEigZone==0 && g.VecEigZone.contains(iAic))
      	                	iEigZone=iAic;
                          if (sDatentyp.equals("BewStamm") && iAic == Eigenschaft())
                          {
                            Cbo.getComboBox().setSelectedAIC(iStamm);
                            Cbo.setEnabled(false);
                          }
                          if (g.VecSttBed.contains(g.TabEigenschaften.getI(iPosE,"aic_stammtyp")))
                          {
                            g.fixtestInfo("Event für "+g.TabEigenschaften.getS(iPosE,"Bezeichnung"));
                            Cbo.getComboBox().addItemListener(new ItemListener ()
                            {
                              public void itemStateChanged(ItemEvent ev)
                              {
                                if (ev.getStateChange() == ItemEvent.SELECTED)
                                  EnableBedingt();
                              }
                            });
                          }
                          Edt=Cbo;
                      }
                      else if (sDatentyp.equals("BewModul") || sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular")
                          || sDatentyp.endsWith("Land") || sDatentyp.equals("BewHoliday") || sDatentyp.equals("Aufgabe") || sDatentyp.equals("Status"))
                      {
                        ComboSort Cbo = new ComboSort(g);
                        if (sDatentyp.endsWith("Land"))
                                Cbo.fillDefTable("Land",true);
                        else if (sDatentyp.equals("Aufgabe"))
                        	Cbo.fillBegriffTable("Aufgabe",true);
                        else if (sDatentyp.equals("Status"))
                            Cbo.fillDefTable("Status",true);
                        else if (sDatentyp.equals("BewHoliday"))
                                Cbo.fillDefTable("Feiertag",true);
                        else
                                Cbo.fillBegriffTable(sDatentyp.equals("BewModul") ? "Modul" :sDatentyp.equals("BewModell") ? "Modell" :
                                             sDatentyp.equals("BewDruck") ? "Druck" :"Frame", true);
                        Edt=Cbo;
                      }
                      else if (sDatentyp.equals("GPS"))
                      {
                    	  Edt=new GPS_Eingabe(g);                 	  
                      }
                      else if (sDatentyp.equals("Rolle"))
                      {
                        RolleEingabe Cbo = new RolleEingabe(g,thisFrame);
                        Cbo.fillRolle(iStammtyp,true);
                        Cbo.setSelectedAIC(0);
                        Cbo.Cbo.addItemListener(new ItemListener ()
                        {
                          public void itemStateChanged(ItemEvent ev)
                          {
                            if (ev.getStateChange() == ItemEvent.SELECTED && !TabEinAus.isEmpty())
                            {
                              int iAic=((ComboSort)ev.getSource()).getSelectedAIC();
                              while (!TabEinAus.posInhalt("aic_rolle",iAic==0 ? null:new Integer(iAic)))
                                iAic=g.getVorRolle(iAic);
                              //if (TabEinAus.posInhalt("aic_rolle",iAic==0 ? null:new Integer(iAic)))
                              //{
                                TabEigenschaft.push();
                                if (TabEigenschaft.posInhalt("Datentyp", "Eintritt"))
                                  ((Datum)TabEigenschaft.getInhalt("Komponente")).setDate(TabEinAus.getD("Eintritt"));
                                //if (TabEigenschaft.posInhalt("Datentyp", "Stichtag"))
                                //  ((Datum)TabEigenschaft.getInhalt("Komponente")).setDate(TabEinAus.getD("Eintritt"));
                                if (TabEigenschaft.posInhalt("Datentyp", "Austritt"))
                                  ((Datum)TabEigenschaft.getInhalt("Komponente")).setDate(TabEinAus.getD("Austritt"));
                                TabEigenschaft.pop();
                                //g.progInfo("Rolle="+iAic+" -> "+TabEinAus.getD("Eintritt")+" - "+TabEinAus.getD("Austritt"));
                              //}
                            }
                          }
                        });

                        Edt=Cbo;
                      }
                      else if (sDatentyp.equals("Datum") || sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt")
                               || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Stichtag"))
                      {
                        Edt = new Datum(g, Static.beiLeer(sFormat, "dd.MM.yyyy"));
                        if (sDatentyp.equals("Stichtag"))
                          EdtStichtag=(Datum)Edt;
                        if (sDatentyp.equals("Austritt") && iStammtyp==g.iSttANR)// && GleicheRolle(TabEigenschaft.getI("Aic")))
                          checkEinAus2((Datum)Edt,false);
                        else if (sDatentyp.equals("Eintritt") && iStammtyp==g.iSttANR)// && GleicheRolle(TabEigenschaft.getI("Aic")))
                          checkEinAus2((Datum)Edt,true);
                      }
                      else if (sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("Zeit"))
                      {
                        Edt = new Datum(g, Static.beiLeer(sFormat, "dd.MM.yyyy HH:mm"));
                        if (bAbfrage && (Tab.getI("bits") & Abfrage.cstGueltig2) > 0)
                          EdtGueltig2=(Datum)Edt;
                        if (iEigDST==0 && g.VecEigDST.contains(iAic))
    	                	iEigDST=iAic;
                      }
                      else if (sDatentyp.equals("Double") || sDatentyp.equals("Integer"))
                      {
                        Edt = new Zahl(0.0,iLaenge==0 && sDatentyp.equals("Double") ?-1:iLaenge);
                        //Edt.setFont(Pnl.getFont());
                        ((Zahl)Edt).setMax( (Integer) g.TabEigenschaften.getInhalt("max",iPosE));
                      }
                      else if (sDatentyp.equals("Boolean"))
                        Edt = new AUCheckBox(sEig);
                      else if (sDatentyp.equals("Bool3"))
                        Edt = bAbfrage && (Tab.getI("bits") & Abfrage.cstShow)==Abfrage.cstRadio ? new RadioAuswahl(g,iAic) : new SpinBoxAuswahl(g,iAic);
                      else if (sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("BewVon_Bis"))
                        Edt = new VonBisEingabe(null, null, sFormat, g,g.TabEigenschaften.getI(iPosE,"aic_stamm"),iLaenge);
                      else if (sDatentyp.equals("Hierarchie") || sDatentyp.equals("BewHierarchie"))
                      {
                        if (/*(TabEigenschaft.getI("bits") & Abfrage.cstDialog) == 0 &&*/ ComboSort.einStt(g,g.TabEigenschaften.getI(iPosE,"aic_stammtyp"),iStammtyp) ||
                            bAbfrage && (Tab.getI("bits2")&Abfrage.cstStt)>0)
                          Edt = new AUComboList(null,getBegriff(),getBegriff(), iAic, bAbfrage ? Tab.getI("Stt"):g.TabEigenschaften.getI(iPosE,"aic_stammtyp"), g, bAbfrage ? Tab.getI("Filter") : g.TabEigenschaften.getI(iPosE,"Abfrage"),
                                                (TabEigenschaft.getI("bits") & (Global.cstKeinAutoDate * 0x10000 + Abfrage.cstKeinAutoDate)) == 0, (TabEigenschaft.getI("bits") & (Global.cstAlways * 0x10000)) == 0,true,false);
                        else
                          Edt = new HierarchieEingabe(getBegriff(),g.TabEigenschaften.getI(iPosE,"aic_stammtyp"), /*(TabEigenschaft.getI("bits") & Abfrage.cstDialog) == 0 ?*/ iStammtyp /*: 0*/, iAic, g);
                      }
                      else if (sDatentyp.equals("Waehrung") || sDatentyp.equals("BewWaehrung") || sDatentyp.equals("Mass") ||
                               sDatentyp.equals("BewMass") || sDatentyp.equals("Einheiten") || sDatentyp.equals("BenMass"))
                      {
                        Edt = sDatentyp.equals("Waehrung") || sDatentyp.equals("BewWaehrung") ? (javax.swing.JPanel)new WaehrungsEingabe(0.0,0, g,true,iLaenge==0?2:iLaenge) :
                            sDatentyp.equals("Mass") || sDatentyp.equals("BewMass") ? new MassEingabe(g.TabEigenschaften.getI(iPosE,"aic_stamm"), g,true,true,iLaenge,sFormat,(TabEigenschaft.getI("bits3")&Abfrage.cstInStunden)>0) : 
                            sDatentyp.equals("Einheiten") || sDatentyp.equals("BenMass") ? new MassEingabe(g.TabEigenschaften.getI(iPosE,"aic_stammtyp"), g, false,true,-1,null,false) : null;
                      }
                      else
                      {
                          g.printError("EingabeFormular.FuelleGruppe: Datentyp <" +sDatentyp +"> wird bei FuelleGruppe noch nicht unterstützt!",getBegriff());
                          Edt=new JLabel("Anders!!!");
                      }
                      //boolean bEdit=!g.ReadOnly() && ((TabEigenschaft.getI("bits")&Abfrage.cstAnzeigen)==0 || (TabEigenschaft.getI("bits")&Abfrage.cstEditierbar)>0);
                        EF_Eingabe EF = new EF_Eingabe(g, Edt, "",sEig, true/*bEdit*/, (TabEigenschaft.getI("bits")&(Global.cstAlways*0x10000))>0,
                            bAbfrage ? Tab.getM("Tooltip"):g.TabEigenschaften.getM(iPosE,"Tooltip"),sDatentyp); // !!! kein Text übergeben
                        if (!(Edt instanceof AUCheckBox))
                          Edt.setFont(g.fontStandard);//Pnl.getFont()); !!!
                        addPopup2(Edt instanceof AUCheckBox ? Edt : EF);
                        if (g.bInfoJeder && iPosE>=0)
                        {
                          Color Col=g.getColLizEig(iPosE);
                          if (Col != null)
                            EF.setBackground(Col);
                          //g.testInfo("Lizenzierbar:"+g.TabEigenschaften.getS(iPosE,"Bezeichnung"));
                        }
                        if (Edt instanceof AUCheckBox)
                        {
                          JPanel PnlCbx=new JPanel(new FlowLayout(FlowLayout.LEFT));
                          if (g.bInfoJeder && iPosE>=0)
                          {
                            Color Col=g.getColLizEig(iPosE);
                            PnlCbx.setBackground(Col != null ? Col:Static.ColEF);
                          }
                          else
                            PnlCbx.setBackground(Static.ColEF);
                          PnlCbx.add(EF);
                          Pnl22.add("Center",PnlCbx);
                        }
                        else if (sDatentyp.equals("Bild") || sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("Farbe") && !Static.bND || sDatentyp.equals("Radio") || sDatentyp.equals("Buttons"))
                          Pnl22.add(/* || sDatentyp.equals("Boolean") ?*/ "Center" /*: sNorth*/, EF);
                        else
                        {
                          //Pnl22 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                          Pnl22.add("North",EF);
                        }
                        TabEigenschaft.setInhalt("EF", Edt instanceof AUCheckBox ? Edt : EF);
                        if (!sDatentyp.endsWith("Radio") && !sDatentyp.endsWith("Buttons"))
                          TabEigenschaft.setInhalt("Komponente", Edt);
                        TabEigenschaft.setInhalt("Spalte",bAbfrage ? new Integer(Tab.getI("Aic")):null);
                        TabEigenschaft.setInhalt("bed",bAbfrage ? Tab.getI("Rel"):0);
                        Pnl22.setBackground(Static.ColEF);
                        return Edt;
        }

	private void FuelleGruppe(boolean bAbfrage,Font font,JPanel Pnl,Object Obj,int riMom,int iSpalten)
	{
          long lClock = Static.get_ms();
          //g.testInfo("FuelleGruppe "+Pnl.hashCode());
		//Tab.showGrid(sGruppe);
                ShowAbfrage Abf= bAbfrage ? (ShowAbfrage)Obj:null;
                Tabellenspeicher Tab=bAbfrage ? Abf.getSpalten():(Tabellenspeicher)Obj;
                //Tab.showGrid(""+riMom);
                JPanel PnlCenter = new JPanel(iSpalten<10 ?new GridLayout(0,iSpalten>1? iSpalten:1,Static.bND ? 3:0,0):new FlowLayout(FlowLayout.LEFT));
		PnlCenter.setBackground(Static.ColEF);

		//JPanel Pnl2 = new JPanel(new BorderLayout(0,0));
                //Pnl2.setBorder(new EmptyBorder(4,2,2,2));
                //Pnl2.setBorder(new EmptyBorder(0,0,0,0));
                //Pnl2.setBackground(g.ColEF);
                //Pnl2.add("Center",PnlCenter);
		JPanel Pnl22;
		//String sNorth="North";
		int iReihe=0;
                //boolean bMemo=false;
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
                  int iAic=bAbfrage ? Tab.getI("Kennung2"):Tab.getI("Aic");
                  if (g.TabEigenschaften.getInfoS(iAic,"Datentyp").equals("Filler"))
                    PnlCenter.add(new JLabel());
                  else if (!TabEigenschaft.posInhalt("Aic",iAic) && (riMom==0 || riMom==Tab.getI("aic_begriff")) && g.existsEigenschaft(new Integer(iAic)))
                  {
                    int iPosE=g.TabEigenschaften.getPos("Aic", iAic);
                    String sDatentyp = g.TabEigenschaften.getS(iPosE,"Datentyp");
                    if (bAbfrage && (Tab.getI("bits") & Abfrage.cstShow)==Abfrage.cstButtons)
                    {
                      if (sDatentyp.equals("Gruppe"))
                        sDatentyp="Buttons";
                      else if (sDatentyp.equals("BewStamm"))
                        sDatentyp="BewButtons";
                      else
                        g.printError("EingabeFormular.FuelleGruppe: "+sDatentyp+" wird nicht für Buttons unterstützt!",getBegriff());
                    }
                    else if (bAbfrage && (Tab.getI("bits") & Abfrage.cstShow)==Abfrage.cstRadio)
                    {
                      if (sDatentyp.equals("Gruppe"))
                        sDatentyp="Radio";
                      else if (sDatentyp.equals("BewStamm"))
                        sDatentyp="BewRadio";
                      else if (!sDatentyp.endsWith("Bool3"))
                        g.printError("EingabeFormular.FuelleGruppe: "+sDatentyp+" wird nicht für Radiobuttons unterstützt!",getBegriff());
                    }
                    if (g.Def() || !sDatentyp.equals("Kennung"))
                    {
                      Pnl22 = sDatentyp.equals("Bild") || sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("Farbe") || sDatentyp.equals("Radio") || sDatentyp.equals("Buttons") ?
                          new JPanel(new BorderLayout(0, 0)):new JPanel(new FlowLayout(FlowLayout.LEFT));
                      if (!bAbfrage && iSpalten>1)
                      {
                        for (int i = 0; i < (Tab.getI("Reihe") - iReihe - 1) % iSpalten; i++)
                          PnlCenter.add(new JLabel());
                        iReihe = Tab.getI("Reihe");
                      }

                        JComponent Edt=addEig(bAbfrage,font,Tab,Pnl22,sDatentyp);
                        //bMemo=Edt instanceof AUTextArea;
                        PnlCenter.add((Edt instanceof AUTextArea/* || Edt instanceof HtmlEingabe*/ || Edt instanceof AUEkitCore) && (Pnl.getParent() instanceof GroupBox || Pnl.getParent().getParent() instanceof GroupBox) ? Edt:Pnl22);
                    }
                  }
		}
                Pnl.add(PnlCenter);
                Clock.add(clock,"Gruppe",riMom>0 ? g.TabBegriffe.getBezeichnungS(riMom):"",lClock);
	}

        /*private boolean changed(int iR)
        {
          if (iR==0 || TabEinAus2==null)
            return false;
          return TabEinAus2.posInhalt("Rolle",iR) && !TabEinAus2.isNull("Eintritt") && ((Datum)TabEinAus2.getInhalt("Eintritt")).Modified();
        }

        private boolean EinAusChange()
        {
          if (TabEinAus2==null)
            return false;
          else
          {
            boolean b=false;
            for(TabEinAus2.moveFirst();!TabEinAus2.out();TabEinAus2.moveNext())
              b=b || !TabEinAus2.isNull("Eintritt") && ((Datum)TabEinAus2.getInhalt("Eintritt")).Modified()
                  || !TabEinAus2.isNull("Austritt") && ((Datum)TabEinAus2.getInhalt("Austritt")).Modified();
            return b;
          }
        }*/

        private void checkEinAus2(Datum Edt,boolean b)
        {
          if (TabEinAus2==null)
            TabEinAus2= new Tabellenspeicher(g,new String[] {"Edt","Rolle","Eintritt","Austritt"});
          int iPos=g.TabEigenschaften.getPos("Aic",TabEigenschaft.getI("Aic"));
          int iRolleMom=iPos<0 ? 0:g.TabEigenschaften.getI(iPos,"Rolle");
          if (iRolleMom >0)
          {
            if (!TabEinAus2.posInhalt("Rolle",iRolleMom))
            {
              TabEinAus2.newLine();
              TabEinAus2.setInhalt("Rolle", iRolleMom);
            }
            TabEinAus2.setInhalt(b ? "Eintritt":"Austritt",Edt);
            if (b)
            {
              TabEinAus2.setInhalt("Edt",Edt.Edt);
              Edt.Edt.addFocusListener(new FocusListener()
              {
                public void focusGained(FocusEvent fe) {}
                public void focusLost(FocusEvent fe)
                {
                  int iPos=TabEinAus2.getPos("Edt",fe.getSource());
                  Datum EdtEin=(Datum)TabEinAus2.getInhalt("Eintritt",iPos);
                  Datum EdtAus=(Datum)TabEinAus2.getInhalt("Austritt",iPos);
                  if (EdtStichtag !=null)
                    if (EdtEin.Modified())
                    {
                      if (EdtStichtag.getDate()!=null)
                      {
                        EdtStichtag.setDate(null);
                        new Message(Message.WARNING_MESSAGE,thisJFrame(),g).showDialog("Stichtag_entfernt");
                      }
                      EdtStichtag.setEnabled(false);
                      if (EdtAus != null)
                        EdtAus.setDate2(null);
                    }
                    else
                      EdtStichtag.setEnabled(true);
                }
              });
            }
          }
        }

        private void setzeZustand()
        {
          for (TabEigenschaft.moveFirst();!TabEigenschaft.eof();TabEigenschaft.moveNext())
          {
            boolean bEdit=!ReadOnly() && ((TabEigenschaft.getI("bits")&Abfrage.cstAnzeigen)==0 || (TabEigenschaft.getI("bits")&Abfrage.cstEditierbar)>0);
            String sDatentyp = TabEigenschaft.getS("Datentyp");
            Check.setEditable(TabEigenschaft.getS("Bez"),TabEigenschaft.getInhalt("Komponente"),bEdit && !sDatentyp.startsWith("Calc"));
            /*if ((TabEigenschaft.getI("bits")&(g.cstAlways*0x10000))>0)
            {
              Component C=((Component)TabEigenschaft.getInhalt("Komponente"));
              C.setBackground(g.ColZwingend);
            }*/
          }
        }

	/*private void Kopiere()
	{
		Vector<Integer> Vec=null;
		if (TabOutliner.posInhalt("Eig",0))
		{
		  Vec=g.getAics((AUOutliner)TabOutliner.getInhalt("Gid"));

		}
		if (Vec != null && !Vec.isEmpty())
		{
			g.exec("update stamm_protokoll set aic_mandant="+g.getMandant()+" where aic_stamm"+Static.SQL_in(Vec));
                        g.exec("update stamm set kennung=null where aic_stamm"+Static.SQL_in(Vec));
			new Message(Message.INFORMATION_MESSAGE,(JFrame)thisFrame,g).showDialog("Kopiert",new String[] {""+Vec.size()});
		}
                FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
	}*/

        private Tabellenspeicher getTabStamm(int riSatz)
        {
          Tabellenspeicher TabStamm=new Tabellenspeicher(g,new String[] {"Aic","Bezeichnung"});

          if (bEntfernen || bMulti)
          {
                  if (TabOutliner.posInhalt("Eig",0))
                  {

                          AUOutliner Gid=(AUOutliner)TabOutliner.getInhalt("Gid");
                          JCOutlinerNode[] Nodes=Gid.getSelectedNodes();
                          for(int i=0;i<Nodes.length;i++)
                          {
                            Object Obj=Nodes[i].getUserData();
                            if (!TabStamm.posInhalt("Aic",Obj))
                            {
                              TabStamm.addInhalt("Aic", Obj);
                              TabStamm.addInhalt("Bezeichnung", ((Vector)Nodes[i].getLabel()).elementAt(0));
                            }
                          }
                  }
          }
          else
          {
          	TabStamm.addInhalt("Aic",riSatz);
          	TabStamm.addInhalt("Bezeichnung",Typ() == Stamm ? g.getStamm(riSatz):"");//SQL.getString(g, "select bezeichnung from stammview2 where aic_rolle is null and aic_stamm="+riSatz):"");
          }
          return TabStamm;
        }

        private boolean LoescheRolle(JFrame Fom,int riTyp,int riSatz,String s)
        {
          Tabellenspeicher TabStamm=getTabStamm(riSatz);
          String sBed=TabStamm.isEmpty() ? "="+riSatz:Static.SQL_in(TabStamm.getVecSpalte("Aic"));
          boolean b=false;
          int iRolle3 = riTyp != Stamm ? 0 : TabEigenschaft.posInhalt("Datentyp", "Rolle") ? ((RolleEingabe)TabEigenschaft.getInhalt("Komponente")).getSelectedAIC() : iRolle;
          if (iRolle3>0 && (riSatz>0 && !bEntfernen || !TabStamm.isEmpty()))
          {
              int pane = new Message(Message.YES_NO_OPTION, Fom, g).showDialog("Loeschen", new String[] {bEntfernen ? "" + TabStamm.getVecSpalte("Bezeichnung") : s});
              if (pane == Message.YES_OPTION)
              {
                iProt = g.Protokoll("Entfernen",getBegriff());
                Vector<Integer> Vec2 = iRolle3>0 ? g.getVecRolle3(iRolle3):null;
                g.exec("update Stamm_Protokoll set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and aic_stamm"+sBed+" and aic_rolle"+Static.SQL_in(Vec2));
                g.exec("update Stamm_Protokoll set aic_Code="+ g.getBegriffAicS("Status","del") +" where aic_stamm"+sBed+" and aic_rolle"+Static.SQL_in(Vec2));
                b=true;
                g.SaveVerlauf(g.getBegriffAicS("Button","Loesche Rolle"),riSatz,0,iRolle3,"Rolle von "+s+" deaktiviert",0);
              }
          }
          return b;
        }
        
//        private boolean DestroyRole(JFrame Fom,int riTyp,int riSatz,String s,boolean bAll)
//        {
//            Tabellenspeicher TabStamm=getTabStamm(riSatz);
//            String sBed=TabStamm.isEmpty() ? "="+riSatz:Static.SQL_in(TabStamm.getVecSpalte("Aic"));
//            g.fixtestError("DestroyRole"+sBed);
//            boolean b=false;
//            int iRolle3 = riTyp != Stamm ? 0 : TabEigenschaft.posInhalt("Datentyp", "Rolle") ? ((RolleEingabe)TabEigenschaft.getInhalt("Komponente")).getSelectedAIC() : iRolle;
//            if ((iRolle3>0 || bAll) && (riSatz>0 && !bEntfernen || !TabStamm.isEmpty()))
//            {
//                int pane = new Message(Message.YES_NO_OPTION, Fom, g).showDialog("Destroy", new String[] {bEntfernen ? "" + TabStamm.getVecSpalte("Bezeichnung") : s});
//                if (pane == Message.YES_OPTION)
//                {
//                  //iProt = g.Protokoll("Entfernen",getBegriff());
//                  g.SaveVerlauf(g.getBegriffAicS("Button", bAll ? "destroy data":"destroy role"),riSatz,0);
//                  //Vector<Integer> Vec2 = iRolle3>0 ? g.getVecRolle3(iRolle3):null;
//                  g.exec("delete from Stamm_Protokoll where aic_stamm"+sBed+" and aic_rolle"+(bAll ? " is not null":Static.SQL_in(g.getVecRolle3(iRolle3))));
//                  if (bAll)
//                  {
//                	  int iAnz=g.exec("delete from Stammpool where aic_stamm"+sBed);
//                	  if (iAnz>0)
//                		  g.diskInfo(iAnz+" Stammpool von "+s+" entfernt");
//                	  iProt = g.Protokoll("Entfernen",getBegriff());
//                	  g.exec("delete from Stamm_Protokoll where pro_aic_protokoll is not null and aic_stamm"+sBed);
//                	  g.exec("update Stamm_Protokoll set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and aic_stamm"+sBed);
//                	  g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and anr"+sBed);
//                  }
//                  b=true;
//                }
//            }
//            return b;
//          }

	private boolean LoescheDaten(JFrame Fom,int riTyp,int riSatz,String s)
	{
		Tabellenspeicher TabStamm=getTabStamm(riSatz);
                String sBed=TabStamm.isEmpty() ? "="+riSatz:Static.SQL_in(TabStamm.getVecSpalte("Aic"));
                String sBed2=" where aic_eigenschaft not in (select aic_eigenschaft from eigenschaft where aic_begriff="+g.getBegriffAicS("Datentyp","Mehrfach")+") and sta_aic_stamm"+sBed;
                String sRolle=riTyp == Stamm ? " and aic_rolle is null":"";
		boolean b=false;
		if (riSatz>0 && !bEntfernen || !TabStamm.isEmpty())
		{
			if (!g.AllUnlimited() && SQL.getInteger(g,"Select aic_mandant from "+(riTyp == Stamm?"stammview2 where aic_stamm":"bew_pool where aic_bew_pool")+sBed+sRolle)!=g.getMandant())
				new Message(Message.WARNING_MESSAGE,Fom,g).showDialog("MandantFalsch");
                        else if (riTyp == Stamm && SQL.getInteger(g,"Select count(*) from poolview2"+sBed2)!=0)
                          new Tabellenspeicher(g,"select s2.bezeichnung Stammsatz,s1.bezeichnung verwendet_bei,gultig_von ab from stammview2 s1 join poolview2 p on s1.aic_stamm=p.aic_stamm and s1.aic_rolle is null"+
                                               " join stammview2 s2 on s2.aic_stamm=p.sta_aic_stamm and s2.aic_rolle is null"+sBed2,true).showGrid("Löschen nicht möglich");
			else if (riTyp == Stamm && SQL.getInteger(g,"Select count(*) from bew_stamm"+g.join("bew_pool","bew_stamm")+" where pro_aic_protokoll is null and aic_stamm"+sBed)!=0)
				new Message(Message.WARNING_MESSAGE,Fom,g).showDialog("BewegungVorhanden");
			else
			{
				SQL Update = new SQL(g);
				/*int i = riTyp == Stamm ? Update.getInteger("select count(aic_stammpool) from stammpool where pro2_aic_protokoll is null and aic_stamm"+sBed+" or sta_aic_stamm"+sBed):
						riTyp == Bewegung ? Update.getInteger("select(select count(*) from stammpool where pro2_aic_protokoll is null and aic_bew_pool"+sBed+
								")+(select count(*) from bew_von_bis where aic_bew_pool"+sBed+")+(select count(*) from bew_stamm where aic_bew_pool"+sBed+
								")+(select count(*) from bew_wert where aic_bew_pool"+sBed+")+(select count(*) from bew_boolean where aic_bew_pool"+sBed+")"+(g.Oracle() ? " from dual":"")):0;*/
				//int pane = new Message(Message.YES_NO_OPTION,Fom,g).showDialog("Loeschen",new String[] {"<"+(bEntfernen?""+TabStamm.getVecSpalte("Bezeichnung"):s)+" ("+i+")>"});
				int pane = new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,Fom,g).showDialog("Loeschen2",TabStamm);
				if(pane == Message.YES_OPTION)
				{
					iProt = g.Protokoll("Entfernen",getBegriff());
                                        if(riTyp == Stamm)
					{
						Update.exec("update Stamm_Protokoll set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and aic_stamm"+sBed);
						Update.exec("update Stamm_Protokoll set aic_Code="+ g.getBegriffAicS("Status","del") +" where aic_stamm"+sBed);
						Update.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and aic_stamm"+sBed+" or sta_aic_stamm"+sBed);
					}
					else if(riTyp == Bewegung)
					{
						Update.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and aic_bew_pool"+sBed);
						if ((g.getBewBits(Bewegungstyp())&Global.cstBewBew)>0)
						{
							Vector<Integer> VecBewBew=SQL.getVector("select distinct bew2_aic_bew_pool from bew_pool where aic_Bew_Pool"+sBed,g);
							if (VecBewBew.size()>0)
								g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_bew_pool"+Static.SQL_in(VecBewBew)+" and pro_aic_protokoll is null");
						}
						Update.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_bew_pool"+sBed);
					}
					g.progInfo("Lösche "+sBed+"/"+riTyp);
					b=true;
				}
				Update.free();
			}
		}
		return b;
	}

	private void UndelOut(JCOutliner Out)
	{
	  Vector Vec=g.getAics(Out);
	  int pane = new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,(JFrame)thisFrame,g).showDialog("Undel",Tabellenspeicher.getSelected(g,Out));
	  if(pane == Message.YES_OPTION)
	  {
	    g.exec("update bew_pool set pro_aic_protokoll=null where aic_bew_pool"+Static.SQL_in(Vec));
	    FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
	  }
	}

        private void KopiereOut(JCOutliner Out,boolean bHaupt,int iBeg)
        {
          if (g.getMandant()==1 && kopierbar())
            return;
          Vector Vec=g.getAics(Out);
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_stamm,bezeichnung,kennung,aic_stammtyp from stammview2 where aic_rolle is null and "+g.in("aic_stamm",Vec)/*+" and aic_mandant<>"+g.getMandant()*/,true);
          if (Tab.size()>0)
          {
            int pane = new Message(Message.YES_NO_OPTION + Message.SHOW_TAB, (JFrame)thisFrame, g).showDialog("Kopie", Tabellenspeicher.getSelected(g,Out));
            if (pane == Message.YES_OPTION)
            {
              SQL Qry = new SQL(g);
              int iProt=g.Protokoll("Eingabe",iBeg); // vor 9.10.2012: "Kopie",getBegriff());
              Tabellenspeicher TabBew=new Tabellenspeicher(g,new String[] {"Aic","Bezeichnung","Anzahl"});
              //Vector VecBew=SQL.getVector("select aic_bewegungstyp from bewegungstyp where "+g.bit("bewbits",g.cstMitkopieren),g);
              for (Tab.moveFirst(); !Tab.out(); Tab.moveNext())
              {
                int iStt=Tab.getI("aic_stammtyp");
                Qry.add("aic_stammtyp", iStt);
                //if (!Tab.isNull("Kennung"))
                //  Qry.add("Kennung", Tab.getS("Kennung"));
                int iAicOld=Tab.getI("aic_stamm");
                Tabellenspeicher Tab2 = new Tabellenspeicher(g, "select * from stamm_protokoll where pro_aic_protokoll is null and weg is null and aic_stamm=" + iAicOld, true);
                boolean bMc=Tab2.getI("aic_mandant")!=g.getMandant(); // Mandant verändert (copy/changed)
                
                int iAic = Qry.insert("Stamm", true);
                if (bHaupt && VecAic != null)
                  VecAic.addElement(iAic);
                for (Tab2.moveFirst(); !Tab2.out(); Tab2.moveNext())
                {
                  Qry.add("aic_stamm", iAic);
                  Qry.add("aic_protokoll", iProt);
                  Qry.add("Bezeichnung",(bMc ? "":g.getShow("Kopie")+" ")+Tab2.getS("Bezeichnung"));
                  Qry.add("Eintritt",Tab2.getDate("Eintritt"));
                  Qry.add("Austritt",Tab2.getDate("Austritt"));
                  Qry.add("aic_code",g.getBegriffAicS("Status", "copy"));
                  Qry.add0("aic_rolle",Tab2.getI("aic_rolle"));
                  //Qry.add("Nr",Tab2.getI("Nr"));
                  Qry.addNr(iAic);
                  Qry.add("aic_mandant",g.getMandant());
                  //Qry.add("Ab",Tab2.getDate("Ab"));
                  //Qry.add("Weg",Tab2.getDate("Weg"));
                  Qry.add0("Firma",Tab2.getI("Firma"));
                  Qry.add0("ANR",Tab2.getI("ANR"));
                  Qry.add("aic_stammtyp2", iStt);
                  if (bMc)
                      Qry.add("sta_aic_stamm2",iAicOld);
                  Qry.insert("stamm_protokoll",false);
                }
                Tab2 = new Tabellenspeicher(g, "select * from stammpool where pro2_aic_protokoll is null and aic_stamm=" + iAicOld, true);
                Tabellenspeicher Tab3=null;
                if (bMc)
                {
                  Vector Vec3 = Tab2.toVecAic("sta_aic_stamm");
                  if(Vec3.size() > 0) {
                    g.testInfo("Vec3=" + Static.print(Vec3));
                    Tab3 = new Tabellenspeicher(g, "select * from stammview2 where aic_mandant=" + g.getMandant() + " and" + g.in("vg", Vec3), true);
                    //Tab3.showGrid("Tab3");
                  }
                }
                for (Tab2.moveFirst(); !Tab2.out(); Tab2.moveNext())
                {
                  int iEig=Tab2.getI("aic_eigenschaft");
                  int iPosE=g.TabEigenschaften.getPos("Aic",iEig);
                  String sDt=iPosE<0 ? null:g.TabEigenschaften.getS(iPosE,"Datentyp");
                  int iEigBits=g.TabEigenschaften.getI(iPosE,"bits");
                  if (iPosE>=0 && (iEigBits & g.cstEindeutig)==0 && (iEigBits & g.cstKeinCopy)==0 && !sDt.equals("Mehrfach") && iEig!=g.iEigAustritt)
                  {
                    Qry.add0("aic_Daten", Tab2.getI("aic_Daten"));
                    Qry.add("aic_protokoll", iProt);
                    Qry.add("aic_stamm", iAic);
                    Qry.add("aic_eigenschaft", iEig);
                    if (Tab3!=null && Tab3.posInhalt("vg",Tab2.getI("sta_aic_stamm")))
                      Qry.add("sta_aic_stamm",Tab3.getI("aic_stamm"));
                    else
                      Qry.add0("sta_aic_stamm", Tab2.getI("sta_aic_stamm"));
//                    Qry.add0("reihenfolge", Tab2.getI("reihenfolge"));
                    Qry.add("gultig_von", Tab2.getDate("gultig_von"));
                    Qry.add("gueltig_bis", Tab2.getDate("gueltig_bis"));
                    if(!Tab2.isNull("spalte_double"))
                      Qry.add("spalte_double", Tab2.getF("spalte_double"));
                    Qry.insert("stammpool", false);
                  }
                  else
                    g.fixInfo("Eigenschaft "+(iPosE<0 ? iEig+" nicht gefunden":g.TabEigenschaften.getS(iPosE,"Bezeichnung")+" muss eindeutig sein"));
                }
                g.fixtestInfo("Stamm0="+iStamm);
                if (bHaupt)
                {
                  iStamm=iAic;
                  if (Synchron()) g.setSyncStamm(iStammtyp, iStamm,iRolle);
                }
                //g.fixtestInfo("Stamm1="+iStamm);
                //g.progInfo("VecBew="+VecBew);
                for (int iPosB=0;iPosB<g.TabErfassungstypen.size();iPosB++)
                if (g.getANRc(iPosB,iStt)!=null)
                {
                  Tab2 = new Tabellenspeicher(g, "select * from bew_pool where pro_aic_protokoll is null and aic_bewegungstyp="+g.TabErfassungstypen.getI(iPosB,"Aic")+//g.in("aic_bewegungstyp",VecBew)+
                                              " and "+g.getANRc(iPosB,iStt)+"="+iAicOld,true);
                  //if (g.Prog())
                  //  Tab2.showGrid("Tab-Bew");
                  for (Tab2.moveFirst(); !Tab2.out(); Tab2.moveNext())
                  {
                    int iAicB=Tab2.getI("aic_bewegungstyp");
                    if (TabBew.posInhalt("Aic",iAicB))
                      TabBew.addI("Anzahl",1);
                    else
                    {
                      TabBew.addInhalt("Aic",iAicB);
                      TabBew.addInhalt("Bezeichnung",g.TabErfassungstypen.getS(iPosB,"Bezeichnung"));
                      TabBew.addInhalt("Anzahl",1);
                    }
                    Qry.add("aic_protokoll", iProt);
                    Qry.add("aic_bewegungstyp", iAicB);
                    Qry.add("GUELTIG",Tab2.getTimestamp("GUELTIG"));
                    Qry.add("aic_mandant",g.getMandant());
                    Qry.add0("ANR",iAicOld==Tab2.getI("ANR") ? iAic:Tab2.getI("ANR"));
                    Qry.add("LDATE",Tab2.getI("LDATE"));
                    Qry.add("LDATE2",Tab2.getI("LDATE2"));
                    Qry.add("LDATE3",Tab2.getI("LDATE3"));
                    Qry.add("BOOL1",Tab2.getI("BOOL1"));
                    Qry.add("BOOL2",Tab2.getI("BOOL2"));
                    Qry.add("LDateVon",Tab2.getI("LDateVon"));
                    Qry.add("LDateBis",Tab2.getI("LDateBis"));
                    if (g.hasZone(iAicB))
                    	Qry.add("ZONE",Tab2.getI("ZONE"));
                    for (int i=2;i<10;i++)
                      Qry.add0("ANR"+i,iAicOld==Tab2.getI("ANR"+i) ? iAic:Tab2.getI("ANR"+i));
                    int iAicBew=Qry.insert("bew_pool",true);
                    int iBewOld=Tab2.getI("aic_bew_pool");
                    Tab3 = new Tabellenspeicher(g, "select * from bew_stamm where aic_bew_pool="+iBewOld,true);
                    Tabellenspeicher Tab4=null;
                    if (bMc && Tab3.size()>0)
                    {
                      Tab4 = new Tabellenspeicher(g, "select * from stammview2 where aic_mandant=" + g.getMandant() + " and" +g.in("vg",Tab3.toVecAic("aic_stamm")),true);
                      //Tab4.showGrid("Tab4");
                    }
                    for (Tab3.moveFirst(); !Tab3.out(); Tab3.moveNext())
                    {
                      int iRel=Tab3.getI("aic_stamm");
                      /*Qry.add("aic_bew_pool",iAicBew);
                      Qry.add("aic_eigenschaft",Tab3.getI("aic_eigenschaft"));
                      Qry.add("aic_stamm",iRel==iAicOld ? iAic:Tab4!=null && Tab4.posInhalt("vg",iRel) ? Tab4.getI("aic_stamm"):iRel);
                      Qry.insert("bew_stamm",false);*/
                      Import.insertBewStamm(g,iAicB,iAicBew,Tab3.getI("aic_eigenschaft"),iRel==iAicOld ? iAic:Tab4!=null && Tab4.posInhalt("vg",iRel) ? Tab4.getI("aic_stamm"):iRel);
                    }
                    Tab3 = new Tabellenspeicher(g, "select * from bew_wert where aic_bew_pool="+iBewOld,true);
                    for (Tab3.moveFirst(); !Tab3.out(); Tab3.moveNext())
                    {
                      Qry.add("aic_bew_pool",iAicBew);
                      Qry.add("aic_eigenschaft",Tab3.getI("aic_eigenschaft"));
                      Qry.add0("aic_stamm",Tab3.getI("aic_stamm"));
                      Qry.add("spalte_wert",Tab3.getF("spalte_wert"));
                      Qry.add("Grundwert",Tab3.getF("Grundwert"));
                      Qry.insert("bew_wert",false);
                    }
                    Tab3 = new Tabellenspeicher(g, "select * from bew_boolean where aic_bew_pool="+iBewOld,true);
                    for (Tab3.moveFirst(); !Tab3.out(); Tab3.moveNext())
                    {
                      Qry.add("aic_bew_pool",iAicBew);
                      Qry.add("aic_eigenschaft",Tab3.getI("aic_eigenschaft"));
                      Qry.add("spalte_boolean",Tab3.getB("spalte_boolean"));
                      Qry.insert("bew_boolean",false);
                    }
                    Tab3 = new Tabellenspeicher(g, "select * from bew_von_bis where aic_bew_pool="+iBewOld,true);
                    for (Tab3.moveFirst(); !Tab3.out(); Tab3.moveNext())
                    {
                      Qry.add("aic_bew_pool",iAicBew);
                      Qry.add("aic_eigenschaft",Tab3.getI("aic_eigenschaft"));
                      Qry.add("VON",Tab3.getTimestamp("VON"));
                      Qry.add("BIS",Tab3.getTimestamp("BIS"));
                      Qry.add("Dauer",Tab3.getF("Dauer"));
                      Qry.insert("bew_von_bis",false);
                    }
                    Tab3 = new Tabellenspeicher(g, "select * from stammpool where aic_bew_pool="+iBewOld,true);
                    for (Tab3.moveFirst(); !Tab3.out(); Tab3.moveNext())
                    {
                      Qry.add0("aic_Daten",Tab3.getI("aic_Daten"));
                      Qry.add("aic_protokoll", iProt);
                      Qry.add("aic_bew_pool", iAicBew);
                      Qry.add("aic_eigenschaft",Tab3.getI("aic_eigenschaft"));
                      Qry.add0("sta_aic_stamm",Tab3.getI("sta_aic_stamm"));
//                      Qry.add0("reihenfolge",Tab3.getI("reihenfolge"));
                      Qry.add("gultig_von",Tab3.getDate("gultig_von"));
                      Qry.add("gueltig_bis",Tab3.getDate("gueltig_bis"));
                      if (!Tab3.isNull("spalte_double"))
                        Qry.add("spalte_double",Tab3.getF("spalte_double"));
                      Qry.insert("stammpool",false);
                    }
                    Tab3 = new Tabellenspeicher(g, "select * from bew_begriff where aic_bew_pool="+iBewOld,true);
                    for (Tab3.moveFirst(); !Tab3.out(); Tab3.moveNext())
                    {
                      Qry.add("aic_bew_pool",iAicBew);
                      Qry.add("aic_eigenschaft",Tab3.getI("aic_eigenschaft"));
                      Qry.add("aic_begriff",Tab3.getI("aic_begriff"));
                      Qry.insert("bew_begriff",false);
                    }

                  }
                }
              }
              if (Static.bDefBezeichnung)
                TabBew.showGrid("Bewegungen kopiert");
              Qry.free();
              //g.fixtestInfo("Stamm2="+iStamm);
              if (bHaupt)
                Refresh();
              g.fixtestInfo("Stamm3="+iStamm);
            }
          }
        }
        
    private Tabellenspeicher getStammVorhanden(String sBed)
    {
    	g.fixtestError("getStammVorhanden mit "+sBed);
    	String sBed2=" where aic_eigenschaft not in (select aic_eigenschaft from eigenschaft where aic_begriff="+g.getBegriffAicS("Datentyp","Mehrfach")+") and sta_aic_stamm"+sBed;
//    	g.fixInfo("prüfe in Stammpool");
    	Tabellenspeicher Tab=new Tabellenspeicher(g,"select p.sta_aic_stamm Stammsatz,'Stamm' Art,s1.bezeichnung verwendet_bei"+g.AU_Bezeichnung1("Stammtyp","s1")+" Stammtyp,null Bewegungstyp,eintritt ab_Anzahl from stammview2 s1 join poolview2 p"+
    			" on s1.aic_stamm=p.aic_stamm and s1.aic_rolle is null"+sBed2+" order by verwendet_bei",true);
//    	g.fixInfo("prüfe in BewPool");
    	Tab.addTab(new Tabellenspeicher(g,"Select bew_stamm.aic_stamm Stammsatz,'Bew'"+g.AU_Bezeichnung1("Bewegungstyp","p")+" verwendet_bei,null Stammtyp"+g.AU_Bezeichnung1("bewegungstyp","p")+" Bewegungstyp,count(*) ab_Anzahl from bew_stamm"+
    			" join bew_pool p on bew_stamm.aic_bew_pool=p.aic_bew_pool where p.pro_aic_protokoll is null and bew_stamm.aic_stamm"+sBed+" group by bew_stamm.aic_stamm,aic_bewegungstyp order by verwendet_bei",true));
//    	g.fixInfo("prüfe als Einheit");
    	Tab.addTab(new Tabellenspeicher(g,"select s.aic_stamm Stammsatz,'Einheit' Art,b.defbezeichnung"+g.AU_Bezeichnung1("Stammtyp","b")+" Stammtyp"+g.AU_Bezeichnung1("bewegungstyp","b")+" Bewegungstyp,count(*) ab_Anzahl from begriff b join abfrage a on b.aic_begriff=a.aic_begriff"+
    			" join spalte s on s.aic_abfrage=a.aic_abfrage where s.aic_stamm"+sBed+" group by b.aic_stammtyp,b.aic_bewegungstyp,s.aic_stamm,defbezeichnung"/*+" order by defbezeichnung"*/,true));
//    	g.fixInfo("prüfe in Spalte");
    	Tab.addTab(new Tabellenspeicher(g,"select s.sta_aic_stamm Stammsatz,'bedingt' Art,b.defbezeichnung"+g.AU_Bezeichnung1("Stammtyp","b")+" Stammtyp"+g.AU_Bezeichnung1("bewegungstyp","b")+" Bewegungstyp,count(*) ab_Anzahl from begriff b join abfrage a on b.aic_begriff=a.aic_begriff"+
    			" join spalte s on s.aic_abfrage=a.aic_abfrage where s.sta_aic_stamm"+sBed+" group by b.aic_stammtyp,b.aic_bewegungstyp,s.sta_aic_stamm,defbezeichnung",true));
    	if (g.MySQL())
    	{
//    		g.fixInfo("prüfe in Bedingung");      	
    	  Tab.addTab(new Tabellenspeicher(g,"select Vergleichswert Stammsatz,'Bedingung' Art,b.defbezeichnung"+/*g.AU_Bezeichnung1("Stammtyp","b")+*/",null Stammtyp"/*+g.AU_Bezeichnung1("bewegungstyp","b")*/+",null Bewegungstyp,count(*) ab_Anzahl from begriff b join abfrage a on b.aic_begriff=a.aic_begriff"+
    			" join bedingung s on s.aic_abfrage=a.aic_abfrage where vergleichswert"+sBed+" group by defbezeichnung",true));
    	}
    	g.setTitel(Tab, new String[] {"Stammsatz","Art","verwendet_bei","ab_Anzahl"});
    	for (int i=0;i<Tab.size();i++)
    		Tab.setInhalt(i,"Stammsatz",g.getStamm(Tab.getI(i,"Stammsatz")));
    	return Tab;
    }
    
    private void checkStamm(JCOutliner Out)
    {
    	Vector Vec=g.getAics(Out);
  	    int i1=Vec.size()==0 ? 0:Sort.geti(Vec,0);
  	    String sBed=Vec.size()>1 ? Static.SQL_in(Vec):"="+i1;
  	    getStammVorhanden(sBed).showGrid(Vec.size()<2 ? g.getStamm(i1):g.getShow("vorhanden"),(JFrame)thisFrame,false);
    }

	private void LoescheOut(JCOutliner Out,int iBew)
	{
	  Vector Vec=g.getAics(Out);
	  int i1=Vec.size()==0 ? 0:Sort.geti(Vec,0);
		//Tabellenspeicher TabStamm=getTabStamm(riSatz);
                String sBed=Vec.size()>1 ? Static.SQL_in(Vec):"="+i1;
                boolean bStt=iBew==0;
                String sRolle=bStt ? " and aic_rolle is null":"";
		if (i1>0)
		{
			if (!g.AllUnlimited() && SQL.getInteger(g,"Select aic_mandant from "+(bStt ?"stammview2 where aic_stamm":"bew_pool where aic_bew_pool")+sBed+sRolle)!=g.getMandant())
				new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("MandantFalsch");
            else 
            {
            	Tabellenspeicher Tab=bStt ? getStammVorhanden(sBed):null;
              if (bStt && !Tab.isEmpty())// SQL.getInteger(g,"Select count(*) from poolview2"+sBed2)!=0)
                Tab.showGrid("Löschen nicht möglich",(JFrame)thisFrame,false);
//			  else if (bStt && SQL.getInteger(g,"Select count(*) from bew_stamm"+g.join("bew_pool","bew_stamm")+" where pro_aic_protokoll is null and aic_stamm"+sBed)!=0)
//				new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("BewegungVorhanden");
			  else
			  {
				/*int i = riTyp == Stamm ? Update.getInteger("select count(aic_stammpool) from stammpool where pro2_aic_protokoll is null and aic_stamm"+sBed+" or sta_aic_stamm"+sBed):
						riTyp == Bewegung ? Update.getInteger("select(select count(*) from stammpool where pro2_aic_protokoll is null and aic_bew_pool"+sBed+
								")+(select count(*) from bew_von_bis where aic_bew_pool"+sBed+")+(select count(*) from bew_stamm where aic_bew_pool"+sBed+
								")+(select count(*) from bew_wert where aic_bew_pool"+sBed+")+(select count(*) from bew_boolean where aic_bew_pool"+sBed+")"+(g.Oracle() ? " from dual":"")):0;*/
				//int pane = new Message(Message.YES_NO_OPTION,Fom,g).showDialog("Loeschen",new String[] {"<"+(bEntfernen?""+TabStamm.getVecSpalte("Bezeichnung"):s)+" ("+i+")>"});
				int pane = new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,(JFrame)thisFrame,g).showDialog("Loeschen2",Tabellenspeicher.getSelected(g,Out));
				if(pane == Message.YES_OPTION)
				{
					iProt = g.Protokoll("Entfernen",getBegriff());
                    if(bStt)
					{
						g.exec("update Stamm_Protokoll set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and aic_stamm"+sBed);
						g.exec("update Stamm_Protokoll set aic_Code="+ g.getBegriffAicS("Status","del") +" where aic_stamm"+sBed);
						g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and aic_stamm"+sBed+" or sta_aic_stamm"+sBed);
					}
					else
					{
						if ((g.getBewBits(iBew)&Global.cstBewBew)>0)
						{
							Vector<Integer> VecBewBew=SQL.getVector("select distinct bew2_aic_bew_pool from bew_pool where aic_Bew_Pool"+sBed,g);
							if (VecBewBew.size()>0)
								g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_bew_pool"+Static.SQL_in(VecBewBew)+" and pro_aic_protokoll is null");
						}
						g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and aic_bew_pool"+sBed);
						g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_bew_pool"+sBed);
					}
					g.progInfo("Lösche "+sBed+"/"+bStt);
					FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
				}
			  }
            }
		}
	}

	private String ErmittleBezeichnung()
	{
		//g.printInfo("ErmittleBerechneteBezeichnung");
                String s = "";
                boolean b=false;
		if (VecBezeichnung.isEmpty())
                {
                  b=TabEigenschaft.posInhalt("Datentyp", "Bezeichnung") || TabEigenschaft.posInhalt("Datentyp", "Bezeichnung2");
                  s=b ? ((Text)TabEigenschaft.getInhalt("Komponente")).getText() : sBezeichnung;
                  //g.progInfo("ErmittleBezeichnung:"+b+"/"+s);
                  return b && Check.Modified(TabEigenschaft.getInhalt("Komponente")) ? s:"-";
                  //g.progInfo("ErmittleBezeichnung:"+Check.Modified(TabEigenschaft.getInhalt("Komponente"))+"/"+s);
                  //return s;
                }
		String st= "";
		for (int i=0;i<VecBezeichnung.size();i++)
		{
			if (VecBezeichnung.elementAt(i) == null)
				st = st + " ";
			else
			{
				int iPosE=g.TabEigenschaften.getPos("Aic",VecBezeichnung.elementAt(i));
				String sDatentyp = g.TabEigenschaften.getS(iPosE,"Datentyp");
				//g.printInfo("*"+i+":"+VecBezeichnung.elementAt(i)+sDatentyp);
				//g.printInfo(g.TabEigenschaften.getS("Bezeichnung")+'('+sDatentyp+')');
				if (sDatentyp.equals("Trennzeichen"))
					st = st+g.TabEigenschaften.getS(iPosE,"Bezeichnung");
				else if (sDatentyp.equals("StringSehrKurz") || sDatentyp.equals("StringKurz") || sDatentyp.equals("StringKurzOhne")
					|| sDatentyp.equals("String60") || sDatentyp.equals("Stringx") || sDatentyp.equals("Gruppe"))
				{
					if (TabEigenschaft.posInhalt("Aic",VecBezeichnung.elementAt(i)))
					{
						//String sInhalt = ""+(sDatentyp.equals("Gruppe")?((AUComboList)TabEigenschaft.getInhalt("Komponente")).getComboBox().getSelectedItem():
						//	((ComboSort)TabEigenschaft.getInhalt("Komponente")).getSelectedItem());
                                                b=b || Check.Modified(TabEigenschaft.getInhalt("Komponente"));
                                                String sInhalt =Check.getS(TabEigenschaft.getInhalt("Komponente"));
						if (!sInhalt.equals(""))
                                                  s = s.equals("")?sInhalt:s+st+sInhalt;
						st="";
					}
				}
				else if (sDatentyp.equals("Timestamp"))
				{
                                  b=true;
					s = s+st+SQL.getString(g,iStamm == 0 ? "select Timestamp from Protokoll where aic_protokoll="+iProt:
						"select Timestamp from Protokoll join stamm_protokoll on Protokoll.aic_protokoll=stamm_protokoll.aic_protokoll where aic_stamm="+iStamm+" and aic_Code="+g.getBegriffAicS("Status","new"));
					st="";
				}
				else
					g.printError("EingabeFormular.ErmittleBezeichnung: CalcBezeichnung mit Datentyp <"+sDatentyp+"> noch nicht möglich!",getBegriff());
			}
		}
		//g.printInfo("CalcBezeichnung->"+s);
                s=s.trim();
		//if (s.length() > 50)
		//	s = s.substring(0,50);
		return b ? s:"-";
	}

        private void CalcAllBez()
        {
          g.fixtestInfo("VecBezeichnung=" + VecBezeichnung);
          Abfrage Abf=new Abfrage(g,0,Abfrage.cstAbfrage);
          Abf.sAnfang="p2.aic_stamm,p2.bezeichnung";
          sSqlNP=" from (SELECT AIC_Stamm Aic,null CalcBez,"+Abf.ZusaetzlicheSpalten(VecBezeichnung,Formular.Stamm,true)+
            " FROM Stammview p2 WHERE AIC_ROLLE is null and AIC_Stammtyp="+iStammtyp+g.getReadMandanten()+Abf.Ebenen();
          Tabellenspeicher Tab=new Tabellenspeicher(g,"SELect *"+sSqlNP,true);
          for (Tab.moveFirst();!Tab.out();Tab.moveNext())
          {
            String st= "";
            for (int i=0;i<VecBezeichnung.size();i++)
            {
              if (VecBezeichnung.elementAt(i) == null)
                st = st + " ";
              else
              {
                int iPosE=g.TabEigenschaften.getPos("Aic",VecBezeichnung.elementAt(i));
                String sDatentyp = g.TabEigenschaften.getS(iPosE,"Datentyp");
                if (sDatentyp.equals("Trennzeichen"))
                  st+=g.TabEigenschaften.getS(iPosE,"Bezeichnung");
                else
                  st+=Tab.getS("E"+VecBezeichnung.elementAt(i));
              }
            }
            Tab.setInhalt("CalcBez",st);
          }
          Tab.showGrid();
        }

	private boolean SpeicherBewTyp(String rsDatentypVergleich,String rsSpalte,String rsSpalte2,String rsTabelle,Object Obj,int riSatz,int riAic,String rsDatentyp,SQL Insert)
	{
		boolean b = rsDatentyp.equals(rsDatentypVergleich);
		if (b)// && !Check.isNull(Obj))
		{
			//g.debugInfo(">"+rsDatentypVergleich+":"+Obj.getClass());
			Insert.add("aic_Bew_Pool",riSatz);
			Insert.add("aic_Eigenschaft",riAic);
			if(Obj instanceof AUCheckBox)
				Insert.add(rsSpalte,((AUCheckBox)Obj).isSelected());
			else if(Obj instanceof ComboSort)
				Insert.add(rsSpalte,((ComboSort)Obj).getSelectedAIC());
			else if(Obj instanceof AUComboList)
				Insert.add(rsSpalte,((AUComboList)Obj).getComboBox().getSelectedAIC());
			else if(Obj instanceof HierarchieEingabe)
				Insert.add(rsSpalte,((HierarchieEingabe)Obj).getValueStamm());
			else if(Obj instanceof Zahl)
				Insert.add(rsSpalte,((Zahl)Obj).doubleValue());
			else if(Obj instanceof MassEingabe)
			{
				Insert.add(rsSpalte,((MassEingabe)Obj).getValue());
				Insert.add(rsSpalte2,((MassEingabe)Obj).getMass());
			}
			else if(Obj instanceof WaehrungsEingabe)
			{
				Insert.add(rsSpalte,((WaehrungsEingabe)Obj).getValue());
				Insert.add(rsSpalte2,((WaehrungsEingabe)Obj).getWaehrung());
			}
                        else if(Obj instanceof SpinBoxAuswahl)
                          Insert.add(rsSpalte,((SpinBoxAuswahl)Obj).getAic());
                        else if(Obj instanceof RadioAuswahl)
                          Insert.add(rsSpalte,((RadioAuswahl)Obj).getAic());
			else if(Obj instanceof AUOutliner)
				Insert.add(rsSpalte,((Integer)((AUOutliner)Obj).getSelectedNode().getUserData()).intValue());
			else if(Obj instanceof VonBisEingabe)
			{
				Vector Vec = ((VonBisEingabe)Obj).getValue();
				java.sql.Timestamp dtBewVon= (java.sql.Timestamp)Vec.elementAt(0);
				java.sql.Timestamp dtBewBis= (java.sql.Timestamp)Vec.elementAt(1);
				double dDauer = Sort.getf(Vec.elementAt(2));
				if (dtBewVon != null)
					Insert.add("von",dtBewVon);
				if (dtBewBis != null)
					Insert.add("bis",dtBewBis);
				Insert.add("dauer",dtBewVon==null && dtBewBis==null ? dDauer:dtBewVon==null || dtBewBis==null?0.0:(dtBewBis.getTime()-dtBewVon.getTime())/1000.0);
			}
			else if(Obj instanceof Datum)
				if(!((Datum)Obj).isNull())
					Insert.add(rsSpalte,((Datum)Obj).getDateTime());
			else
				g.printError("EingabeFormular.SpeicherBewTyp: Falscher Objekttyp in SpeicherBewTyp",getBegriff());

			if (rsDatentyp.equals("BewDatum2"))
				Insert.add("dauer",0);

			Insert.insert(rsTabelle,false);
                        //if (Obj instanceof AUComboList && g.isEigANR(riAic))
                        //  g.exec("update bew_pool set anr="+((AUComboList)Obj).getComboBox().getSelectedAIC()+" where aic_bew_pool="+riSatz);
			sSave=sSave+TabEigenschaft.getS("Bez")+'\n';
			g.saveInfo("{save} "+TabEigenschaft.getS("Bez"));
		}
		return b;
	}

	private boolean AnyChanged()// boolean bImmer)
	{
          TabSave=null;
          if (bFirst)
            return false;

		boolean b= (bKopie /*|| bImmer*/)/* && iSatz == 0*/ && !TabEigenschaft.isEmpty();
                if (b)
                  return b;
		//if(bImmer || Typ() == Stamm)
			for(TabEigenschaft.moveFirst();/*!b &&*/!TabEigenschaft.eof();TabEigenschaft.moveNext())
                          //if ((TabEigenschaft.getI("bits")&Abfrage.cstDialog)==0)
                          {
                            b = Check.Modified(TabEigenschaft.getInhalt("Komponente"));
                            if (b)
                            {
                              g.saveInfo("verändert:" + TabEigenschaft.getS("Bez") + "/" + TabEigenschaft.getS("Datentyp"));
                              //if (bImmer)
                              {
                                if (TabSave==null)
                                {
                                  if (g.Def())
                                  {
                                    TabSave = new Tabellenspeicher(g, new String[] {"Bezeichnung", "Datentyp", "Wert"});
                                    TabSave.setTitel("Datentyp", g.getBegriffS("Show", "Datentyp"));
                                  }
                                  else
                                    TabSave = new Tabellenspeicher(g, new String[] {"Bezeichnung", "Wert"});
                                  TabSave.setTitel("Bezeichnung",g.getBegriffS("Show","Bezeichnung"));
                                  TabSave.setTitel("Wert",g.getBegriffS("Show","Wert"));
                                }
                                //TabSave.addInhalt("aic",TabEigenschaft.getI("Aic"));

                                TabSave.addInhalt("Bezeichnung",TabEigenschaft.getS("Bez"));
                                if (g.Def())
                                  TabSave.addInhalt("Datentyp",TabEigenschaft.getS("Datentyp"));
                                TabSave.addInhalt("Wert",getObject(TabEigenschaft.getInhalt("Komponente"),false));
                              }
                            }
                          }
                        //if (b && g.Prog())
                        //  TabEigenschaft.showGrid("TabEigenschaft");
		return TabSave != null;
	}

        private void ResetModified()
        {
          //g.fixInfo("ResetModified");
          for(TabEigenschaft.moveFirst();!TabEigenschaft.eof();TabEigenschaft.moveNext())
              //if ((TabEigenschaft.getI("bits")&Abfrage.cstDialog)==0)
              {
                if (Check.Modified(TabEigenschaft.getInhalt("Komponente")))
                {
//                  g.fixtestError("Reset "+TabEigenschaft.getS("Bez"));
                  Check.Reset(TabEigenschaft.getInhalt("Komponente"));
                }
              }
        }

        private void Reset2Modified()
        {
          //g.fixInfo("ResetModified2");
          for(TabEigenschaft.moveFirst();!TabEigenschaft.eof();TabEigenschaft.moveNext())
              //if ((TabEigenschaft.getI("bits")&Abfrage.cstDialog)==0)
              {
                if (Check.Modified(TabEigenschaft.getInhalt("Komponente")))
                  Check.Reset2(TabEigenschaft.getInhalt("Komponente"));
              }
        }

        private void ResetAll()
        {
          //g.fixInfo("ResetAll");
          Reset2Modified();
          for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
            if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
              ((AUTable)TabAbf.getInhalt("Komponente")).Reset();
          for (Tabellenspeicher TabJCTable= getTab("Planung");!TabJCTable.out();TabJCTable.moveNext())
          {
            Planung P=(Planung)TabJCTable.getInhalt("Komponente");
            //P.bSaveMom = true;
            P.Reset();
          }
        }

	private Vector<String> Fehlerliste()
	{
		Vector<String> Vec = new Vector<String>();
                int iPosEin=getPosEintritt();//TabEigenschaft.getPos();
                Date dtEinNeu = iPosEin<0 ? dtEin:((Datum)TabEigenschaft.getInhalt("Komponente",iPosEin)).getDate();
                int iPosAus=getPosAustritt();
                Date dtAusNeu = iPosAus<0 ? dtAus:((Datum)TabEigenschaft.getInhalt("Komponente",iPosAus)).getDate();
                //g.testInfo("iPosEin="+iPosEin+", iPosAus="+iPosAus);
                //if (g.getBis()!=null && TabEigenschaft.posInhalt("Datentyp","Eintritt") && g.TabEigenschaften.posInhalt("Aic",TabEigenschaft.getInhalt("Aic"))
		//	&& !((Datum)TabEigenschaft.getInhalt("Komponente")).isNull() && !g.getBis().after(((Datum)TabEigenschaft.getInhalt("Komponente")).getDate()))
                Datum EdtEintritt=iPosEin<0 ?null:(Datum)TabEigenschaft.getInhalt("Komponente",iPosEin);
                Datum EdtAustritt=iPosAus<0 ?null:(Datum)TabEigenschaft.getInhalt("Komponente",iPosAus);
                //g.progInfo("EdtEintritt="+EdtEintritt);
                //int iPosFirma=TabEigenschaft.getPos("Aic",g.iEigFirma);
                /*if (Typ() == Stamm && iPosFirma>=0 && Check.Modified(TabEigenschaft.getInhalt("Komponente",iPosFirma)) && Check.getI(TabEigenschaft.getInhalt("Komponente",iPosFirma))==0)
                {
                  int iAnz=SQL.getInteger(g,"select count(*) from poolview2 where sta_aic_stamm=?",-1,""+iStamm);
                  if (iAnz>0)
                    Vec.addElement(g.TabEigenschaften.getBezeichnungS(TabEigenschaft.getI(iPosFirma,"Aic"))+" "+g.getShow("nicht entfernbar da")+" "+iAnz+"x "+g.getShow("verwendet"));
                }*/
                if (iStammtyp==g.iSttANR && (EdtEintritt != null && EdtEintritt.Modified()))// && EdtStichtag != null && (EdtStichtag.Modified() || !EdtStichtag.isEnabled())))
                {
                  Date dt=SQL.getTimestamp(g,"select max(austritt) from stamm_protokoll where pro_aic_protokoll is null and austritt is not null and aic_stamm="+iStamm+Rolle());
                  //g.progInfo("EdtEintritt="+EdtEintritt.Modified()+", dt="+dt);
                  if (dt != null)
                    Vec.addElement(g.getShow("Wiedereintritt")+" "+g.getShow(EdtStichtag != null && !EdtStichtag.isNull() && !dt.before(EdtStichtag.getDate()) ? "liegt ausserhalb":"gesperrt"));
                }
                if (EdtAustritt != null && EdtAustritt.Modified() && EdtStichtag != null && !EdtStichtag.isNull())
                  Vec.addElement("["+g.TabEigenschaften.getBezeichnungS(TabEigenschaft.getI(iPosAus,"Aic"))+"] "+g.getShow("Stichtag nicht erlaubt"));
                if (g.getBis()!=null && dtEinNeu!=null && !g.getBis().after(dtEinNeu) && iPosEin>=0 && g.TabEigenschaften.getPos("Aic",TabEigenschaft.getI(iPosEin,"Aic"))>=0)
                  Vec.addElement(g.TabEigenschaften.getBezeichnungS(TabEigenschaft.getI(iPosEin,"Aic"))+" "+g.getShow("liegt ausserhalb"));
                if (g.getVon()!=null && dtAusNeu!=null && dtAusNeu.before(g.getVon()) && iPosAus>=0 && g.TabEigenschaften.getPos("Aic",TabEigenschaft.getI(iPosAus,"Aic"))>=0)
                  Vec.addElement(g.TabEigenschaften.getBezeichnungS(TabEigenschaft.getI(iPosAus,"Aic"))+" "+g.getShow("liegt ausserhalb"));
                //Vec.addElement("nichts");
                //Date dtStichtag = TabEigenschaft.posInhalt("Datentyp", "Stichtag") ? ((Datum)TabEigenschaft.getInhalt("Komponente")).getDate() : null;
                Date dtStichtag = EdtStichtag == null ? null:EdtStichtag.getDate();
                boolean bModST= dtStichtag==null ? false:Check.Modified(EdtStichtag);
		/*if (dtEinNeu!=null && dtStichtag != null && dtStichtag.before(dtEinNeu) && g.TabEigenschaften.posInhalt("Aic",TabEigenschaft.getInhalt("Aic")))
                  Vec.addElement(g.TabEigenschaften.getS("Bezeichnung")+" "+g.getShow("liegt ausserhalb"));*/
                Object oGueltigkeit=EdtGueltig2 == null ? null:new Zeit(EdtGueltig2.getDateTime(),"dd/MM/yyyy HH:mm:ss");
                if (oGueltigkeit != null && !Check.Vorhanden(g,iStamm,iRolle,(Zeit)oGueltigkeit))
                  Vec.addElement(" "+g.getStamm(iStamm)/*SQL.getString(g,"select bezeichnung from stammview2 where aic_rolle is null and aic_stamm="+iStamm)*/+" "+g.getShow("ist nicht vorhanden"));
                //if (iPosE>=0 && !Check.inRange(TabEigenschaft.getInhalt("Komponente",iPosE),null,null))
                //    Vec.addElement(g.TabEigenschaften.getBezeichnungS(TabEigenschaft.getI(iPosE,"Aic"))+" "+g.getShow("ueber 30 Tage geaendert"));
		for(TabEigenschaft.moveFirst();!TabEigenschaft.eof();TabEigenschaft.moveNext())
		{
                  //if (TabEigenschaft.getS("Datentyp").equals("Stichtag"))
                  //  g.progInfo("prüfe Stichtag");
                  String sDT=TabEigenschaft.getS("Datentyp");
			if (/*(TabEigenschaft.getI("bits")&Abfrage.cstDialog)==0 &&*/ (iSatz==0 || sDT.equals("Stichtag") || sDT.equals("Rolle") || Check.Modified(TabEigenschaft.getInhalt("Komponente"))))
			{
                int iAic=TabEigenschaft.getI("Aic");
				int iPosE=g.TabEigenschaften.getPos("Aic",iAic);
                Object Obj=TabEigenschaft.getInhalt("Komponente");
				if ((TabEigenschaft.getI("bits")&(Global.cstAlways*0x10000))>0 && Check.isNull(Obj))
					Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+g.getShow("benoetigt Wert"));
                else if(sDT.equals("Eintritt") && Check.isNull(Obj) && Check.Modified(TabEigenschaft.getInhalt("Komponente")))
                  Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+g.getShow("nicht loeschbar"));
                else if(sDT.equals("Eintritt") && dtEinNeu != null && !Check.isNull(Obj) && ((Datum)Obj).getDate().after(dtEinNeu))
                  Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+g.getShow("nach Eintritt"));
                if ((TabEigenschaft.getI("bits")&(Global.cstEindeutig*0x10000))>0 && Check.nichtEindeutig(g,Obj,TabEigenschaft.getS("Datentyp"),iAic,iStammtyp))
					Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+g.getShow("ist nicht eindeutig"));
				if ((TabEigenschaft.getI("bits")&(Abfrage.cstGueltig))>0 && Check.Monatsabschluss(g/*,getBegriff()*/,Bewegungstyp(),iStamm,Obj,null))
					Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+g.getShow("schon abgeschlossen"));
				if (!Check.isValid(Obj))
					Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+g.getShow("ist nicht gueltig"));
				if (!Check.inRange(Obj,g.TabEigenschaften.getInt(iPosE,"min"),g.TabEigenschaften.getInt(iPosE,"max")))
					Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+g.getShow("liegt ausserhalb")+" "+g.TabEigenschaften.getI(iPosE,"min")+"-"+g.TabEigenschaften.getI(iPosE,"max"));
				if (iSatz>0 && (TabEigenschaft.getI("bits2")&Abfrage.cstStichtag)>0 && (Check.wasNull(Obj) && iAic!=g.iEigAustritt ? dtStichtag==null : !bModST && !Check.isNull(Obj)))
				  Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+g.getShow("Stichtag fehlt"));
                if(Check.zuLang(Check.getS2(Obj),sDT,0))
                  Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+(Check.getS2(Obj).length()-Check.getMaxLaenge(TabEigenschaft.getS("Datentyp"),0))+" "+g.getShow("zu lang"));
                if(g.TabEigenschaften.getS(iPosE,"Kennung").startsWith("PV_IBAN") && !Calc.IBANok(g,Check.getS2(Obj)))
                  Vec.addElement("["+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"] "+g.getShow("falsch"));
                if (oGueltigkeit != null)
                {

                  if ((sDT.equals("BewStamm") || sDT.equals("BewHierarchie") || sDT.equals("Gruppe") || sDT.equals("Hierarchie")) &&
                      !Check.Vorhanden(g,Check.getI(TabEigenschaft.getInhalt("Komponente")),g.TabEigenschaften.getI(iPosE,"Rolle"),(Zeit)oGueltigkeit))
                    Vec.addElement(" "+g.TabEigenschaften.getS(iPosE,"Bezeichnung")+" "+g.getShow("ist nicht vorhanden"));
              }

			}
		}
		return Vec;
	}

	/*private String VecToString(Vector Vec)
	{
		String s="";
		for (int i=0;i<Vec.size();i++)
		{
			s+="\n"+(i+1)+".:"+Vec.elementAt(i);
		}
		return s;
	}*/

        private void MandantFrage()
        {
          g.progInfo("MandantFrage:"+iMandantM+"/"+iMandant);
          if (new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("change_Mandant",
              new String [] {g.TabMandanten.getBezeichnungS(iMandantM),g.TabMandanten.getBezeichnungS(iMandant)}) == Message.NO_OPTION)
          iMandant=iMandantM;
        }

    private boolean SpeichernVerboten()
    {
    	return g.getMandant()==1 && !g.bAppl && !g.bBasis && (Typ() == Bewegung && (g.getBewBits(Bewegungstyp())&(Global.cstDefinition+Global.cstMandantIgnorieren))==0
                || Typ() == Stamm && (g.getSttBits(iStammtyp)&Global.cstSttDef)==0);
    }

	private boolean SpeichereDaten(boolean bAutoSave)
	{
          g.fixtestError("SpeichereDaten "+bAutoSave+", lClock="+lClockSave+"/"+Static.get_ms());
          bCalcS=false;
          if (bKeinNeu && iSatz==0)
          {
            g.fixtestInfo("speichere nicht da keinNeu gesetzt");
            return true;
          }
          if (iSatz<0 || bMulti)
          {
            g.fixtestInfo("speichere nicht da Satz<0!");
            return true;
          }
          //if (!bAutoSave/* && !Static.bSpeichernFrage*/)
          //  return false;
          if(bSpeichere)
          {
            g.fixInfo("speichere gerade!");
            return false;
          }
          if (Static.get_ms()-lClockSave<50)
          {
                  g.fixInfo("kein Speichern da Zeit zu kurz: "+(Static.get_ms()-lClockSave));
                  return false;
          }
          //g.progInfo("********** SpeichereDaten:"+(Static.get_ms()-lClockSave));
          if (ReadOnly())
          {
                  g.printInfo("Keine Speicher-Berechtigung");
                  return false;
          }
          if (SpeichernVerboten() && Modified())
          {
            if (bEnde)
              g.fixInfo("! Formular "+sFormularBezeichnung+" beendet ohne Speicherung");
            else
            {
              new Message(Message.WARNING_MESSAGE, null, g).showDialog("Speichernsperre");
              g.printError("EingabeFormular.SpeichereDaten: Speichern mit All nicht möglich",getBegriff());
              //FuelleEigenschaften(0, true);
            }
            return false;
          }

          if (iStamm>0 && Typ() == Stamm && iStammtyp==g.iSttANR)
          {
            int iPosEin=getPosEintritt();//TabEigenschaft.getPos();
            if (iPosEin>=0 && ((Datum)TabEigenschaft.getInhalt("Komponente",iPosEin)).Modified() && !((Datum)TabEigenschaft.getInhalt("Komponente",iPosEin)).wasNull())
               if (bEnde || new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Eintritt_geaendert") == Message.NO_OPTION)
                 return false;

             int iPos=TabEigenschaft.getPos("Stammtyp",g.iSttANR);
             //if (iPos>=0 && (TabEigenschaft.getI(iPos,"bits")&Abfrage.cstDialog)>0)
             //  iPos=-1;
             if (iPos<0)
               iPos=TabEigenschaft.getPos("Aic",g.iEigFirma);
             if (iPos>=0)
             {
               if (Check.Modified(TabEigenschaft.getInhalt("Komponente", iPos)) && Check.getI(TabEigenschaft.getInhalt("Komponente", iPos))==0)
                 if (bEnde || new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Firma_auf_0") == Message.NO_OPTION)
                   return false;
             }
          }
          bSpeichere=true;
          boolean bSpeichern=false;
          Vector<String> Vec= Fehlerliste();
          String s=Static.VecToString(Vec);
          if (TabAbf != null)
                for (TabAbf.moveFirst();!TabAbf.out();TabAbf.moveNext())
                  if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
                  {
                    Vector<String> Vec2=((AUTable)TabAbf.getInhalt("Komponente")).FehlerListe();
                    //g.progInfo(((AUTable)TabAbf.getInhalt("Komponente")).sDefBez+":"+Vec2.size());
                    if (Vec2.size()>0)
                    {
                      //Vec.addElement(((AUTable)TabAbf.getInhalt("Komponente")).sDefBez+":");
                      s+="\n"+((AUTable)TabAbf.getInhalt("Komponente")).sDefBez+":";
                      for (int i=0;i<Vec2.size();i++)
                        s+="\n  "+(i+1)+". "+Vec2.elementAt(i);
                      Static.addVectorS(Vec,Vec2);
                    }
                  }
                if (Vec.size()>0)
                {
                  boolean b=false;
                  if (bEnde || !bAutoSave)
                    b=new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Fehlerliste2", new String[] {s}) == Message.YES_OPTION;
                  else
                    new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("Fehlerliste", new String[] {s});
                  if (b)
                    ResetAll();
                  //else
                  bSpeichere=false;
                  return b;
                }
		if (AnyChanged()) //bAutoSave am 27.2.2014 entfernt, gibt keinen Sinn
		{
                  if (TabEigenschaft.posInhalt("Datentyp", "Rolle") && ((RolleEingabe)TabEigenschaft.getInhalt("Komponente")).getSelectedAIC()==0 &&
                    new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Speichern_ohne_Rolle") == Message.NO_OPTION)
                  {
                    bSpeichere=false;
                    return false;
                  }
			//else
                        //{
                          int iRolle3 = TabEigenschaft.posInhalt("Datentyp", "Rolle") ? ((RolleEingabe)TabEigenschaft.getInhalt("Komponente")).getSelectedAIC() : 0;
                          if (iSatz == 0 || iRolle3>0) // Neu
                          {
                            if (bSaveRolle || iRolle3>0)
                            {
                              Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_rolle,count(*) Anzahl from stammview where aic_stammtyp=" + iStammtyp + " and aic_mandant=" + g.getMandant() + " group by aic_rolle", true);
                              //if (g.Prog())
                              //  Tab.showGrid("Tab Rollen");
                              Tabellenspeicher Tab2= iSatz>0 ? new Tabellenspeicher(g,"select aic_rolle from stammview2 where aic_stamm="+iSatz,true):null;
                              Vector Vec2 = g.getVecRolle(iRolle3>0 ? iRolle3:iRolle);
                              s = "";
                              int iAnz = Tab.posNull("aic_rolle",true) ? Tab.getI("Anzahl") : 0;
                              int iMax2 = g.getAnzahlStamm(iStammtyp);
                              String sSttBez=g.TabStammtypen.getBezeichnungS(iStammtyp);
                              g.fixtestInfo(sSttBez + ":" + iAnz + " von " + iMax2);
                              if (iSatz == 0 && iMax2 != -1 && iAnz >= iMax2)
                                s = "\n" + g.getBezeichnung("Tabellenname", "STAMMTYP") + " " + sSttBez;
                              for (int i = 0; i < Vec2.size(); i++)
                              {
                                int iRolle2 = Tabellenspeicher.geti(Vec2.elementAt(i));
                                iAnz = Tab.posInhalt("aic_rolle", iRolle2) ? Tab.getI("Anzahl") : 0;
                                iMax2 = g.getAnzahlRolle(iRolle2);
                                g.progInfo(g.TabRollen.getBezeichnungS(iRolle2) + ":" + iAnz + " von " + iMax2);
                                if ((Tab2==null || !Tab2.posInhalt("aic_rolle",iRolle2)) && iMax2 != -1 && iAnz >= iMax2)
                                  s += "\n" + g.getBezeichnung("Tabellenname", "ROLLE") + " " + g.TabRollen.getBezeichnungS(iRolle2);
                              }
                              iMandantM=SQL.getInteger(g, "Select aic_mandant from stammview2 where aic_stamm=" + iStamm + (iRolle3>0 ? Abfrage.Rolle(iRolle3):Rolle()));
                              iMandant=g.getMandant();
                              //g.fixInfo("Mandant="+iMandantM+"/"+iMandant);
                              if (s.equals(""))
                                bSpeichern = true;
                              else
                                new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("LizenzErreicht", new String[]
                                    {s});
                            }
                            else if (Typ() == Bewegung || iMax == -1 || iMax > SQL.getInteger(g,
                                "select count(*) from stammview where aic_stammtyp=" + iStammtyp + " and aic_mandant=" + g.getMandant() + Rolle()))
                              bSpeichern = true;
                            else
                              new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("AnzahlErreicht");
                          }
                          else
                          {
                            if (Typ() == Bewegung)
                              bSpeichern = true;
                            else
                            {
                              iMandantM=SQL.getInteger(g, "Select aic_mandant from stammview2 where aic_stamm=" + iStamm + Rolle());
                              iMandant=g.getMandant();
                              if (iMandantM==0)
                            	  iMandantM=iMandant;
                              //g.fixInfo("Mandant="+iMandantM+"->"+iMandant);
                              if (TabEigenschaft.posInhalt("Datentyp", "Mandant"))
                                iMandant=((ComboSort)TabEigenschaft.getInhalt("Komponente")).getSelectedAIC();
                              //g.progInfo("Mandant:"+iMandantM+"->"+iMandant);
                              if (iMandantM==iMandant || g.isWriteMandant(iMandantM))
                                bSpeichern = true;
                              //else if (g.isWriteMandant(iMandantM))
                              //{
                                /*if (new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("change_Mandant",
                                    new String [] {g.TabMandanten.getBezeichnung(iMandantM),g.TabMandanten.getBezeichnung(iMandant)}) == Message.NO_OPTION)
                                  iMandant=iMandantM;*/
                              //  bSpeichern = true;
                              //}
                              else
                                new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("MandantFalsch");
                            }
                          }
                        //}
		}
		//else
		//	g.saveInfo("Keine Änderungen bei direkter Eingabe");

		boolean bTabSave=false;
                if (TabAbf != null)
                 for (TabAbf.moveFirst();!bTabSave && !TabAbf.out();TabAbf.moveNext())
                  if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
			bTabSave=((AUTable)TabAbf.getInhalt("Komponente")).Modified();
		boolean bPlanSave=false;
		for (Tabellenspeicher TabJCTable= getTab("Planung");!bPlanSave && !TabJCTable.out();TabJCTable.moveNext())
			bPlanSave=((Planung)TabJCTable.getInhalt("Komponente")).Modified();
//		g.fixtestError("Gruppe,AUTable,Planung speichern:"+bSpeichern+"/"+bTabSave+"/"+bPlanSave);
		//boolean b=false;
		if ((bSpeichern || bTabSave || bPlanSave) && (bAutoSave || new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,(JFrame)thisFrame,g).showDialog("Speichern",TabSave) == Message.YES_OPTION))
		{
			if (bSpeichern)
			{
                          lClockSave=Static.get_ms();
//                          g.fixtestError("setze ClockSave1:"+lClockSave);
                          //bCalcS=true;
                          Speichere();
			}
                        g.progInfo("vor Save3");
                        //TabAbf.showGrid("TabAbf");
			if (bTabSave)
                         if (TabAbf != null)
                          for (TabAbf.moveFirst();!TabAbf.out() && bTabSave;TabAbf.moveNext())
                            if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
					bTabSave=((AUTable)TabAbf.getInhalt("Komponente")).Save(iProt,false,isBewBew()?-iSatz:iStamm);
			if (bPlanSave)
				for (Tabellenspeicher TabJCTable= getTab("Planung");!TabJCTable.out();TabJCTable.moveNext())
					((Planung)TabJCTable.getInhalt("Komponente")).Save();
                    //b=true;
		}
		else
		{
                  ResetAll();
                  //b=true;
		}
                bCalcS=true;
		//iProt = 0;
		lClockSave=Static.get_ms();
//		g.fixtestError("setze ClockSave2:"+lClockSave);
		bSpeichere=false;
                return true;
	}

        private boolean Modified()
        {
          if (iSatz<0 || bFirst)
            return false;
          boolean b=AnyChanged();
          if (TabAbf != null)
            for (TabAbf.moveFirst();!b && !TabAbf.out();TabAbf.moveNext())
              if (TabAbf.getS("Art").equals("JCTable") && TabAbf.getB("aktiv"))
              {
                b = ((AUTable)TabAbf.getInhalt("Komponente")).Modified();
                if (b)
                  g.saveInfo("verändert: JCTable "+TabAbf.getI("Aic"));
              }
            for (Tabellenspeicher TabJCTable= getTab("Planung");!b && !TabJCTable.out();TabJCTable.moveNext())
                        b=((Planung)TabJCTable.getInhalt("Komponente")).Modified();
          return b;
        }

        private boolean GleicheRolle2(int iPos)
        {
          iPos=g.TabEigenschaften.getPos("Aic",TabEigenschaft.getI(iPos,"Aic"));
          if (iPos>=0)//g.TabEigenschaften.posInhalt("Aic",iAic))
            return /*(g.TabEigenschaften.getI(iPos,"bits")&Global.cstEAneu)==0 ||*/ iRolle == g.TabEigenschaften.getI(iPos,"Rolle");
          else
            return true;
        }

        private boolean GleicheRolle2(int iPos,int iRolle2)
        {
          iPos=g.TabEigenschaften.getPos("Aic",TabEigenschaft.getI(iPos,"Aic"));
          if (iPos>=0)//g.TabEigenschaften.posInhalt("Aic",iAic))
            return /*(g.TabEigenschaften.getI(iPos,"bits")&Global.cstEAneu)==0 ||*/ iRolle2 == g.TabEigenschaften.getI(iPos,"Rolle");
          else
            return true;
        }

        private int getPosEintritt()
        {
          for (int iPos=0;iPos<TabEigenschaft.size();iPos++)
          {
            if ((TabEigenschaft.getS(iPos,"Datentyp").equals("Eintritt") || TabEigenschaft.getS(iPos,"Datentyp").equals("EinAustritt")) && GleicheRolle2(iPos))
              return iPos;
          }
          return -1;
          //return TabEigenschaft.posInhalt("Datentyp","Eintritt") ? ((Datum)TabEigenschaft.getInhalt("Komponente")).getDate() :
          //		TabEigenschaft.posInhalt("Datentyp","EinAustritt") ? ((Datum)TabEigenschaft.getInhalt("Komponente")).getDate() : dtEin;
        }

        private int getPosAustritt()
        {
          for (int iPos=0;iPos<TabEigenschaft.size();iPos++)
          {
            if ((TabEigenschaft.getS(iPos,"Datentyp").equals("Austritt") || TabEigenschaft.getS(iPos,"Datentyp").equals("EinAustritt")) && GleicheRolle2(iPos))
              return iPos;
          }
          return -1;
        }

        private int getPosAustritt(int iRolle2)
        {
          for (int iPos=0;iPos<TabEigenschaft.size();iPos++)
          {
            if ((TabEigenschaft.getS(iPos,"Datentyp").equals("Austritt") || TabEigenschaft.getS(iPos,"Datentyp").equals("EinAustritt")) && GleicheRolle2(iPos,iRolle2))
              return iPos;
          }
          return -1;
        }
        
    private Object getObj(int iEig)
    {
    	int iPos=TabEigenschaft.getPos("Aic",iEig);
    	return iPos<0 ? null:TabEigenschaft.getInhalt("Komponente",iPos);
    }

	private boolean Speichere()
	{
          g.fixtestInfo("Speichere "+iSatz);
          bSave=true;
		/*
		if (bFrage)
		{
			int pane = new Message(Message.YES_NO_OPTION,(Frame)thisFrame,g).showDialog("Speichern");
			if(pane == Message.NO_OPTION)
				return false;
		}*/
		//bZwingend=true;
		//g.progInfo("Speichere");
		Ausgabe("EF-vor SpeichereDaten");
                long lClock2 = Static.get_ms();
                int iError=g.getFehler();
		/*if (g.Debug())
			TabEigenschaft.showGrid();*/
		//Protokoll füllen
		//g.printInfo("SpeichereDaten");
                g.start();
		iProt = g.Protokoll("Eingabe",getBegriff());

		//g.debugInfo("Protokoll="+iProt);
                boolean bVAustritt=false;
		sSave="";
		SQL Insert = new SQL(g);
    g.checkAus("EingabeFormular "+getTitle());
		Date dtStichtag=null;
                boolean b1970=false;
		boolean bStichtag=false;
                int iPosS = Typ() == Stamm ? g.TabStammtypen.getPos("Aic",iStammtyp):-1;
                boolean bSttUseStichtag=iPosS>=0 && (g.TabStammtypen.getI(iPosS,"bits")&Global.cstKeinStichtag)==0;
		boolean bNeu = iSatz == 0;
                //boolean bNeu2=bNeu;
		boolean bChangeDate = false;
		Tabellenspeicher TabRest=null;
                if (iMandant==0)
                  iMandant=g.getMandant();
                String sBezNeu = null;
                int iFirma=0;
                int iFirmaOld=0;
		if (Typ() == Stamm)
		{
			sBezNeu = ErmittleBezeichnung();
                        //TabEigenschaft.showGrid();
                        int iPosEin=getPosEintritt();//TabEigenschaft.getPos();
                        Date dtEinNeu = iPosEin<0 ? dtEin:((Datum)TabEigenschaft.getInhalt("Komponente",iPosEin)).getDate();

                        boolean bEinChange=!Static.Gleich(dtEinNeu,dtEin);
                        boolean bMandantChanged=bNeu;
                        if (TabEigenschaft.posInhalt("Datentyp", "Mandant"))
                        {
                          iMandant = ((ComboSort)TabEigenschaft.getInhalt("Komponente")).getSelectedAIC();
                          bMandantChanged = bNeu || ((ComboSort)TabEigenschaft.getInhalt("Komponente")).Modified();
                        }
                        int iRolle2=TabEigenschaft.posInhalt("Datentyp", "Rolle") ? ((RolleEingabe)TabEigenschaft.getInhalt("Komponente")).getSelectedAIC() : 0;
                        if (iRolle2>0)
                          ((RolleEingabe)TabEigenschaft.getInhalt("Komponente")).setSelectedAIC(0);
                        if (bSttUseStichtag)
                        {
                          Datum dt=TabEigenschaft.posInhalt("Datentyp", "Stichtag") ? (Datum)TabEigenschaft.getInhalt("Komponente"):null;
                          if (dt != null)
                          {
                            //if (!dt.Modified() && bEinChange && (dtEinNeu == null || dtEin==null || dtEin.after(dtEinNeu))) // 9.6.2010 entfernt
                            //  dt.setDate(dtEinNeu);
                            dtStichtag = dt.getDate();
                          }
                          if (dtStichtag==null)
                          {
                            dtStichtag = Static.dt1970;
                            b1970=true;
                          }
                          g.progInfo("----------------------------------------------------------------");
                          g.progInfo("Stichtag="+dtStichtag);
                          //bSttUseStichtag=!TabEigenschaft.out();
                          bStichtag = dtStichtag != null;
                          //if (!bStichtag)
                          //  dtStichtag = dtEinNeu;
                        }
                        int iPosAus=getPosAustritt();
                        Date dtAusNeu = iPosAus<0 ? dtAus:((Datum)TabEigenschaft.getInhalt("Komponente",iPosAus)).getDate();
                          //Date dtAusNeu = TabEigenschaft.posInhalt("Datentyp","Austritt") ? ((Datum)TabEigenschaft.getInhalt("Komponente")).getDate() :
			  //	TabEigenschaft.posInhalt("Datentyp","EinAustritt") ? ((Datum)TabEigenschaft.getInhalt("Komponente")).getDate() : dtAus;
                          //g.progInfo("sBezNeu="+sBezNeu+",dtStichtag="+dtStichtag+"/"+bStichtag);
                          //g.progInfo("Austritt:"+dtAus+" -> "+dtAusNeu);
			//Stamm- bei Veränderung von Kennung
                        g.progInfo("sBezNeu="+sBezNeu);
			if((sBezNeu.equals("") || sBezNeu.equals("-")) && bNeu)
                        {
                          g.rollback();
                          new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("BezeichnungFehlt");
                          bSave=false;
                          return false;
                        }
			else
			{
				String sKennungNeu = TabEigenschaft.posInhalt("Datentyp","Kennung") ? ((Text)TabEigenschaft.getInhalt("Komponente")).getText():"";
				boolean bKenChange=TabEigenschaft.out() ? false:((Text)TabEigenschaft.getInhalt("Komponente")).Modified();
				if (bNeu)
				{
					g.progInfo("Neuer Stammsatz");
					sKennung = sKennungNeu;//TabEigenschaft.posInhalt("Datentyp","Kennung") ? ((Text)TabEigenschaft.getInhalt("Komponente")).getText():"";
					Insert.add("aic_stammtyp",iStammtyp);
					iStamm = Insert.insert("Stamm",true);
					if (Synchron()) g.setSyncStamm(iStammtyp, iStamm,iRolle);
					if (VecAic != null)
						VecAic.addElement(new Integer(iStamm));
					//g.debugInfo("Stamm="+iStamm);
					iSatz = iStamm;
					/*
					if (CboVon != null)
					{
						int i=CboVon.getSelectedAIC();
						CboVon.addItem(sBezNeu,iStamm,sKennung);
						CboVon.setSelectedAIC(i);
					}*/
					//if (g.Prog())
					//	g.TabComboboxen.showGrid();
					for(int i=0;i<g.TabComboboxen.size();i++)
						if (g.TabComboboxen.getI(i,"Stt")==iStammtyp)
						{
							ComboSort Cbo = (ComboSort)g.TabComboboxen.getInhalt("Cbo",i);
							int iAic=Cbo.getSelectedAIC();
							Cbo.addItem(sBezNeu,iStamm,sKennung);
							Cbo.setSelectedAIC(iAic);
						}
                                        g.fixtestInfo("iStamm2="+iStamm2);
					if (iStamm2 != 0)
					{
						Insert.add("Aic_Protokoll",iProt);
						//Insert.add("Aic_Stamm",iStamm);
						Insert.add("Aic_Stamm",iStamm2);
						Insert.add("Aic_Eigenschaft",iEigenschaft2);
						//Insert.add("STA_Aic_Stamm",iStamm2);
						Insert.add("STA_Aic_Stamm",iStamm);
            if (bSttUseStichtag)
              Insert.add("GULTIG_VON",dtStichtag);
						Insert.insert("StammPool",false);
					}
				}
				//else if (TabEigenschaft.posInhalt("Datentyp","Kennung"))
				//	bKenChange=((Text)TabEigenschaft.getInhalt("Komponente")).Modified();
						/*&& !((Text)TabEigenschaft.getInhalt("Komponente")).getText().equals(sKennung))
				{
                                  g.progInfo("Kennung ändern");
					Insert.add("Kennung",((Text)TabEigenschaft.getInhalt("Komponente")).getText());
					Insert.update("Stamm",iStamm);
					//Insert.clear();
				}*/
				//Stamm-Protokoll bei Veränderung von Bezeichnung,Eintritt,Austritt
				//g.printInfo(">"+sBezNeu+"/"+sBezeichnung+"<"+dtEinNeu+"/"+dtEin+">");
                                if (dtEinNeu != null && dtAusNeu != null && dtAusNeu.before(dtEinNeu))
                                {
                                  dtAusNeu=null;
                                  new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("Austritt_fehlerhaft");
                                }
                                iFirma=bNeu ? 0:SQL.getInteger(g,"select firma from stammview2 where aic_stamm=?",0,""+iStamm);
                                iFirmaOld=iFirma;
                                if (iStammtyp==g.iSttFirma)
                                  iFirma=iStamm;
                                else
                                {
                                  //if (bNeu)
                                  //{
                                    int iPos=iStammtyp==g.iSttANR ? -1:TabEigenschaft.getPos("Stammtyp",g.iSttANR);
                                    if (iPos<0)
                                      iPos=TabEigenschaft.getPos("Aic",g.iEigFirma);
                                    if (iPos>=0)
                                      g.progInfo("Eig="+TabEigenschaft.getS(iPos,"Bez")+"/"+iPos);
                                    int iAic=iPos<0 ? 0:Check.getI(TabEigenschaft.getInhalt("Komponente",iPos));
                                    if (iPos>=0)
                                    {
                                      iFirma=0;
                                      g.testInfo("Aic-Link="+iAic+", Pos="+iPos);
                                      if (iAic>0)
                                      {
                                        Tabellenspeicher TabFirma = new Tabellenspeicher(g, "select aic_stamm,bezeichnung from stammview2 where aic_stammtyp=" + g.iSttFirma, true);
                                        Tabellenspeicher TabH = new Tabellenspeicher(g, "select aic_stamm,sta_aic_stamm from poolview2 where aic_eigenschaft=" + g.iEigFirma, true);
                                        if (TabFirma.posInhalt("aic_stamm", iAic))
                                        {
                                          iFirma = TabFirma.getI("aic_stamm");
                                          g.progInfo("verwende Firma:" + TabFirma.getS("Bezeichnung"));
                                        }
                                        while (iFirma == 0 && TabH.posInhalt("aic_stamm", iAic))
                                        {
                                          iAic = TabH.getI("sta_aic_stamm");
                                          if (TabFirma.posInhalt("aic_stamm", iAic))
                                          {
                                            iFirma = TabFirma.getI("aic_stamm");
                                            g.progInfo("verwende Firma:" + TabFirma.getS("Bezeichnung"));
                                          }
                                        }
                                      }
                                      g.progInfo("Firma von "+iFirmaOld+" -> "+iFirma);
                                    }
                                  //}
                                  //else
                                  //  iFirma=SQL.getInteger(g,"select firma from stammview2 where aic_stamm="+iStamm);
                                }
                                //int iPNr=0;
                                g.progInfo("neues Austrittsdatum");
                                //g.fixInfo("dtAusNeu="+dtAusNeu+", dtAus="+dtAus);
                                if (!bNeu && dtAusNeu != null && !Static.Gleich(dtAusNeu,dtAus))  // neues Austrittsdatum
                                {
                                  boolean bAlle=iRolle2==0 && iRolle==0;
                                  Vector VecRolle=bAlle ? null:g.getVecRolle3(iRolle2>0?iRolle2:iRolle);
                                  g.progInfo("VecRolle (darunter)="+VecRolle);
                                  //int i=0;
                                  if (bAlle || !VecRolle.isEmpty())
                                  {
                                    Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_protokoll,bezeichnung,Eintritt,Austritt,ab,weg,aic_rolle,anr,Firma from stamm_protokoll where pro_aic_protokoll is null and aic_stamm="+iStamm+" and aic_rolle"+(bAlle ?">0":Static.SQL_in(VecRolle)),true);
                                    for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                                      if (Tab.isNull("Austritt") || dtAusNeu.before(Tab.getDate("Austritt")))
                                      {
                                        g.progInfo("Teil1: Rolle="+Tab.getI("aic_Rolle")+":"+Tab.getDate("Austritt")+"->"+dtAusNeu);
                                        Insert.exec("update Stamm_Protokoll set pro_aic_protokoll=" + iProt + " where pro_aic_protokoll is null and aic_stamm=" + iStamm +
                                            " and aic_rolle=" + Tab.getI("aic_Rolle")+" and aic_protokoll="+Tab.getI("aic_protokoll"));
                                        Insert.add("Aic_Stamm", iStamm);
                                        //iPNr++;
                                        Insert.add("aic_protokoll", iProt);
                                        //Insert.add("Nr", iPNr);
                                        Insert.addNr(iStamm);
                                        Insert.add("Bezeichnung", Tab.getS("bezeichnung"));
                                        Insert.add("Eintritt", Tab.getDate("Eintritt"));
                                        Insert.add("Austritt", dtAusNeu);
                                        Insert.add("Ab", Tab.getDate("Ab"));
                                        Insert.add("Weg", Tab.getDate("Weg"));
                                        Insert.add("Aic_Code", g.getBegriffAicS("Status", "change"));
                                        Insert.add("AIC_ROLLE", Tab.getI("aic_Rolle"));
                                        Insert.add("aic_mandant",iMandant);
                                        Insert.add0("Firma",Tab.isNull("Weg") ? iFirma:Tab.getI("Firma"));
                                        Insert.add0("ANR",Tab.getI("anr"));
                                        Insert.add("aic_stammtyp2",iStammtyp);
                                        Insert.add("Kennung2", sKennungNeu);
                                        Insert.insert("Stamm_Protokoll", false);
                                        //g.fixtestInfo("Stamm_Protokoll: setzte ANR="+Tab.getI("anr")+" bei Stamm "+iStamm);
                                      }
                                  }
                                }
                                g.progInfo("Änderung Bezeichnung, Eintritt, Austritt: "+sBezNeu+"/"+sBezeichnung+", "+dtEinNeu+"/"+dtEin+", "+dtAusNeu+"/"+dtAus+", "+iRolle2);
                                boolean bBezChange=!sBezNeu.equals("-");
                                if (sBezNeu.equals("") || sBezNeu.equals("-"))
                                  sBezNeu=sBezeichnung;
                                g.fixtestInfo("Änderung "+sBezNeu+":"+bBezChange+"/"+bKenChange+"/"+bEinChange+", Firma: "+iFirmaOld+"->"+iFirma+", Mandant="+bMandantChanged);
                                boolean bAusChange=!Static.Gleich(dtAusNeu,dtAus);
				if (!sBezNeu.equals("") && (bBezChange || bKenChange || bEinChange || bAusChange || bMandantChanged || iRolle2>0 || iFirma!=iFirmaOld))
				{
					g.saveInfo("neues Stammprotokoll:"+(bBezChange ? " <"+sBezeichnung+"> -> <"+sBezNeu+">":"")+(bKenChange ? " Kennung: <"+sKennungNeu+">":"")+
                                                   (bEinChange ? " ein: <"+dtEin+"> -> <"+dtEinNeu+">":"")+(bAusChange ? " aus: <"+dtAus+"> -> <"+dtAusNeu+">":"")+
                                                   (bMandantChanged ? " M: "+iMandantM+" -> "+iMandant:""));
                                        if (iRolle2==0 && bSaveRolle)
                                          iRolle2=iRolle;
                                        if (!bMandantChanged && iMandant != iMandantM && iMandantM>0)
                                          MandantFrage();
                                        int iANR=0;
                                        if (iEigANR>0 && !bNeu)
                                        {
                                          iANR = SQL.getInteger(g, "select anr from stammview2 where aic_stamm=?", 0, "" + iStamm);
                                          g.fixtestInfo("verwende ANR=" + iANR + " bei Stamm " + iStamm);
                                        }
                                        Vector VecRolle=iRolle2>0?g.getVecRolle(iRolle2):null;
                                        g.progInfo("VecRolle="+VecRolle+"/"+g.getVecRolle2(iRolle2,true)+"/"+g.getVecRolle3(iRolle2));
					Tabellenspeicher Tab=bNeu /*|| VecRolle==null*/ ? null:new Tabellenspeicher(g,"select bezeichnung,Kennung,Ab,Eintritt,Austritt,aic_rolle,null sav,aic_mandant from stammview2 where aic_stamm="+iStamm,true);
                                        Vector VecR=g.getVecRolleStt(iStammtyp);
                                        int iAnzSP=VecR.size()+1;//VecRolle != null ? VecRolle.size()+1:1;//(bNeu ? 1:0):1;
                                        int i=0;
                                        //int iWeg=0;
                                        for(;i<iAnzSP;i++)
                                        {
                                          int iRolleMom=i < VecR.size() ? Sort.geti(VecR,i):0;//iRolle2 > 0 && i < VecRolle.size() ? Tabellenspeicher.geti(VecRolle.elementAt(i)):0;
                                          //g.progInfo(i+": Rolle="+iRolleMom);
                                          boolean bInsert=bNeu;
                                          int iAnz=0;
                                          if(!bNeu && (bBezChange || bKenChange || VecRolle!=null && VecRolle.contains(iRolleMom) || iFirma!=iFirmaOld))
                                          {
                                            //boolean bPos=Tab.posInhalt("AIC_Rolle",iRolleMom);
                                            //g.progInfo("--------------- Teste Rolle "+iRolleMom);
                                            if (Tab==null || Tab.posInhalt("AIC_Rolle",iRolleMom==0 ?null:new Integer(iRolleMom)))
                                            {
                                              //if (Tab != null)
                                              //  Tab.showGrid("Tab");
                                              //g.progInfo(iRolleMom+"/"+iRolle2+":"+dtEinNeu+"/"+/*Tab.getDate("Eintritt")+"/"+dtEinNeu.before(Tab.getDate("Eintritt"))+"/"+dtAusNeu+"/"+Tab.getDate("Austritt")+"/"+*/changed(iRolleMom));
                                              bInsert= Tab==null || !Tab.getS("Bezeichnung").equals(sBezNeu) || !Tab.getS("Kennung").equals(sKennungNeu) || dtEinNeu==null && !Tab.isNull("Eintritt") || dtAusNeu==null && !Tab.isNull("Austritt") ||
                                                  dtEinNeu!=null && Tab.isNull("Eintritt") && iRolleMom==iRolle2 || iMandant!=Tab.getI("aic_mandant") || iFirma!=iFirmaOld ||//changed(iRolleMom) ||
                                                  dtEinNeu!=null && !Tab.isNull("Eintritt") && (dtEinNeu.before(Tab.getDate("Eintritt")) ||
                                                  iRolleMom==iRolle2 && (!dtEinNeu.equals(Tab.getDate("Eintritt")) || !Static.Gleich(dtAusNeu,Tab.getDate("Austritt"))));
                                              if (bInsert)
                                              {
                                                if (bStichtag)
                                                {
                                                  int iPos=TabEinAus2 == null ? -1:TabEinAus2.getPos("Rolle",iRolleMom);
                                                  Datum EdtEin=iPos<0 ? null:(Datum)TabEinAus2.getInhalt("Eintritt");
                                                  iAnz=g.exec("update Stamm_Protokoll set pro_aic_protokoll=" + iProt + " where pro_aic_protokoll is null"+
                                                      (EdtEin!= null && EdtEin.Modified() ? " and austritt is null":b1970?"":" and ab>="+ g.DateTimeToString(dtStichtag))
                                                    + " and aic_stamm=" + iStamm +Abfrage.Rolle(iRolleMom));
                                                  if (iAnz==0)
                                                    /*iWeg=*/iAnz=g.exec("update Stamm_Protokoll set "+(dtStichtag.before(Static.dt0) ? "pro_aic_protokoll="+iProt:"weg=" + g.DateTimeToString(dtStichtag)) +
                                                        " where weg is null and pro_aic_protokoll is null and aic_stamm=" + iStamm +Abfrage.Rolle(iRolleMom));
                                                }
                                                else
                                                    iAnz=g.exec("update Stamm_Protokoll set pro_aic_protokoll=" + iProt + " where pro_aic_protokoll is null and aic_stamm=" + iStamm +Abfrage.Rolle(iRolleMom));
                                              }
                                            }
                                            else
                                            {
                                              bInsert = true;
                                              if (iRolle2>0 && iRolle2==iRolleMom)
                                                new Message(Message.INFORMATION_MESSAGE,(JFrame)thisFrame,g).showDialog("SaveRolle",new String[] {g.TabRollen.getBezeichnungS(iRolle2),sBezNeu});
                                                //g.progInfo(" --------------------------------------------------------- Erstelle Rolle "+g.TabRollen.getBezeichnung(iRolle2));
                                            }
                                          }
                                          //g.progInfo("Save Rolle "+iRolleMom+":"+bInsert);
                                          if (bInsert && (iRolleMom==0 || iAnz>0 || VecRolle!=null && VecRolle.contains(iRolleMom)))
                                          {
                                            //g.progInfo("Teil1: Rolle="+iRolleMom+":"+/*Tab.getDate("Austritt")+"->"+*/dtAusNeu);
                                            Insert.add("Aic_Stamm", iStamm);
                                            Insert.add("aic_protokoll", iProt);
                                            //iPNr++;
                                            //Insert.add("Nr", iPNr);
                                            Insert.addNr(iStamm);
                                            Insert.add("Bezeichnung", sBezNeu);
                                            if (iRolle==iRolleMom)
                                            {
                                              Insert.add("Eintritt", dtEinNeu);
                                              Insert.add("Austritt", dtAusNeu);
                                              Insert.add("Ab",bStichtag ? dtStichtag:dtEinNeu);
                                            }
                                            else if (Tab != null)
                                            {
                                              Insert.add("Eintritt", Tab.getDate("Eintritt"));
                                              Insert.add("Austritt", Tab.getDate("Austritt"));
                                              Insert.add("Ab", bStichtag ? dtStichtag:Tab.getDate("Ab"));
                                            }
                                            String sCode=bNeu ? iRolle2==iRolleMom ? "new" : "copy" : "change";
                                            //g.progInfo("Code="+sCode);
                                            Insert.add("Aic_Code", g.getBegriffAicS("Status",sCode));
                                            if (iRolleMom>0)
                                              Insert.add("AIC_ROLLE", iRolleMom);
                                            Insert.add("aic_mandant",iMandant);
                                            Insert.add0("Firma",iFirma);
                                            if (iANR>0)
                                              Insert.add("ANR", iANR);
                                            Insert.add("aic_stammtyp2",iStammtyp);
                                            Insert.add("Kennung2", sKennungNeu);
                                            Insert.insert("Stamm_Protokoll", false);
                                            if (Tab!=null && Tab.posInhalt("AIC_Rolle",iRolleMom))
                                              Tab.setInhalt("sav",Boolean.TRUE);
                                          }
                                        }
                                        //                      !!! weg ab V 5.07 !!!
                                        //if (Tab!=null)
                                        //  Insert.exec("update Stamm_Protokoll set Bezeichnung='"+sBezNeu+"' where pro_aic_protokoll is null and aic_stamm=" + iStamm);

                                        /*  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                                            if(!Tab.getB("sav") && !Tab.getS("Bezeichnung").equals(sBezNeu))
                                            {
                                              g.progInfo("Zusatz-Save Rolle "+Tab.getI("aic_rolle"));
                                              Insert.exec("update Stamm_Protokoll set pro_aic_protokoll=" + iProt + " where pro_aic_protokoll is null and aic_stamm=" + iStamm +
                                                  (Tab.isNull("aic_rolle")  ? " and aic_rolle is null":" and aic_rolle=" + Tab.getI("aic_rolle")));
                                              Insert.add("Aic_Stamm", iStamm);
                                              Insert.add("aic_protokoll", iProt);
                                              iPNr++;
                                              Insert.add("Nr", iPNr);
                                              Insert.add("Bezeichnung", sBezNeu);
                                              Insert.add("Eintritt", Tab.getDate("Eintritt"));
                                              Insert.add("Austritt", Tab.getDate("Austritt"));
                                              Insert.add("Aic_Code", g.getBegriffAic("Status", "change"));
                                              if(!Tab.isNull("aic_rolle"))
                                                Insert.add("AIC_ROLLE", Tab.getI("aic_rolle"));
                                              Insert.insert("Stamm_Protokoll", false);
                                            }*/
					if (bBezChange)
					{
						sSave+="\nBezeichnung";
						g.saveInfo("{save} >Bezeichnung");
					}
					if (bEinChange)
					{
						bChangeDate=true;
						sSave+="\nEintritt";
						g.saveInfo("{save} Eintritt");
                                                java.sql.Timestamp ts=SQL.getTimestamp(g,"select max(austritt) from stamm_protokoll where aic_stamm="+iStamm+Rolle()+" and pro_aic_protokoll is null and austritt is not null");
                                                //if (ts !=null && dtEin != null)
                                                //  g.progInfo(dtEinNeu+"/"+dtEin+" after "+ts+":"+dtEin.after(ts));
                                                if (dtEinNeu != null && dtEin != null/* && iWeg==0*/&& (ts == null || dtEin.after(ts)))
                                                {
                                                  Tabellenspeicher Tab2=new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("eigenschaft","stammpool")+" Eigenschaft,gultig_von von,gueltig_bis bis from stammpool where aic_stamm="+iStamm+
                                                       " and pro2_aic_protokoll is null and gultig_von>"+ g.DateTimeToString(dtEin),true);
                                                   if (Tab2.isEmpty())
                                                     g.progInfo(g.exec("update stammpool set gultig_von=" + g.DateTimeToString(dtEinNeu) + " where aic_stamm=" + iStamm +
                                                       " and gultig_von=" + g.DateTimeToString(dtEin)) + " Eigenschafts-Stichtage geändert");
                                                   else
                                                     Tab2.showGrid("Änderungen danach",thisFrame);
                                                }
                                                if (dtEinNeu != null)
                                                  dtEin=new java.sql.Date(dtEinNeu.getTime());
					}
					if (bAusChange)
					{
						bChangeDate=true;
						sSave+="\nAustritt";
						g.saveInfo("{save} >Austritt");
					}
				}
                                if (!sBezNeu.equals(""))
                                  sBezeichnung=sBezNeu;
				if (Typ()!=Bewegung && iEigenschaft2 ==0)
                                {
                                  iMom=-1;
                                  Titelzusatz(sBezeichnung);
                                }
			}
			//g.progInfo("Speichere Stamm:1.Teil");
		}
		else if (Typ() == Bewegung)
		{
			//g.progInfo("Typ=Bewegung, Neu="+bNeu+)
			if (!bNeu)
               Insert.exec("update bew_pool set pro_aic_protokoll="+iProt+" where aic_bew_pool="+iSatz);
            iSatzOld = iSatz;
			dtStichtag = TabEigenschaft.posInhalt("Datentyp","BewDatum") ? ((Datum)TabEigenschaft.getInhalt("Komponente")).getDateTime() : null;
			Insert.add("aic_bewegungstyp",Bewegungstyp());
			if (dtStichtag != null)
			{
                          java.sql.Timestamp ts=((Datum)TabEigenschaft.getInhalt("Komponente")).getDateTime();
                          Insert.add("Gueltig",ts);
                          Insert.add("LDATE",Static.DateToInt(ts));
				sSave="\nGueltig";
				g.saveInfo("{save} >Gueltig");
			}
                        else if (BewVoll())
                        {
                          java.sql.Timestamp ts=SQL.getTimestamp(g, "select gueltig from bew_pool where aic_bew_pool=" + iSatzOld);
                          Insert.add("Gueltig", ts);
                          Insert.add("LDATE",/*ts==null ? Static.Int0:*/Static.DateToInt(ts));
                        }
                        else
                          Insert.add("LDATE",0);
                        Insert.add("LDATE2",0);
                        Insert.add("LDATE3",0);
                        Insert.add("BOOL1",0);
                        Insert.add("BOOL2",0);
                        Insert.add("LDateVon",0);
                        Insert.add("LDateBis",0);
                        //if (g.isEigANR(Eigenschaft()))
                        //  Insert.add("ANR",iStamm);
			Insert.add("aic_protokoll",iProt);
			Insert.add("aic_mandant",iMandant);
			if (g.hasZone(Bewegungstyp()))
			{
				int iZone=-1;
	//			g.fixtestError("iEigDST="+iEigDST+", iEigZone="+iEigZone+", neu="+bNeu);
				if (iEigDST>0 && (bNeu || iEigZone>0))
	            {
	            	Object oDST = getObj(iEigDST);
	                Timestamp dtDST = oDST != null && oDST instanceof Datum ? ((Datum)oDST).getDateTime():null;
	                String sZone=null;
	                if (iEigZone>0)
	                {
	                	Object oZone = getObj(iEigZone);
	//                	if (oZone != null)
	//                		g.fixtestError("Zone-Eig:"+Static.print(oZone));
	                	AUComboList CboZone=oZone != null && oZone instanceof AUComboList ? (AUComboList)oZone:null;
	                	sZone=CboZone != null && CboZone.getAIC()>0 ? g.getZone(CboZone.getAIC()):null;
	                	if (CboZone != null)
	                		g.fixtestError("CboZone="+sZone+" "+((Combo)CboZone.getComboBox().getSelectedItem()).print());
	                	if (bNeu && Static.Leer(sZone))
	                		sZone=g.getZoneS();//"CET";
	                }
	                else
	                	sZone=g.getZoneS();//"CET";
	                if (Static.Leer(sZone))
	                	g.printError("Zone-Spalte hat keinen Wert",getBegriff());
	                else if (dtDST==null)
	                	g.printError("DST-Spalte hat keinen Wert",getBegriff());
	                else
	                  iZone=DateWOD.getZone(sZone,dtDST);
	            }
	            if (iZone!=-1)
	              Insert.add("Zone",iZone);
	            else if (bNeu)
	              Insert.add("Zone",g.getZone());
	            else
	              Insert.add("Zone",SQL.getInteger(g,"select zone from bew_pool where aic_bew_pool="+iSatzOld));
			}
			if(!bNeu)
				Insert.add("bew_aic_bew_pool",iSatz);
			iSatz = Insert.insert("Bew_Pool",true);
                        if (!bNeu && isBewBew())
                          g.exec("update bew_pool set bew2_aic_bew_pool="+iSatz+" where bew2_aic_bew_pool="+iSatzOld);
                        sBezNeu="Bew "+iSatz;
			//g.debugInfo("Satz="+iSatz);
			if (!bNeu && Bewegungstyp()>0 && (BewVoll() || (g.getBewBits(Bewegungstyp())&Global.cstBewVoll)>0))
			{
				TabRest=Import.getTabRest(g,true, Bewegungstyp());
				//TabRest=new Tabellenspeicher(g,"select aic_eigenschaft aic,null saved from bew_zuordnung where aic_bewegungstyp="+Bewegungstyp()+" order by reihenfolge",true);
				if (!keinStamm() && iStamm>0 && TabRest.posInhalt("aic",Eigenschaft()))
					TabRest.setInhalt("saved",Boolean.TRUE);
			}
			//bNeu = true;
			if(!keinStamm() && iStamm>0)
			{
                          Import.insertBewStamm(g,Bewegungstyp(),iSatz,Eigenschaft(),iStamm);
                          g.progInfo("Hauptstamm "+iStamm+" auf Eigenschaft "+Eigenschaft()+" und Satz "+iSatz+" speichern");
				/*Insert.add("aic_Bew_Pool",iSatz);
				Insert.add("aic_Eigenschaft",Eigenschaft());
				Insert.add("aic_Stamm",iStamm);
				Insert.insert("Bew_Stamm",false);*/
			}
		}
		else
			dtStichtag = null;
        //g.progInfo("weiteres in Stamm_protokoll");
	if (iSatz!=0)
	{

		//Daten speichern
		TabEigenschaft.moveFirst();
		while (!TabEigenschaft.eof())
		{
			String sDatentyp = (String)TabEigenschaft.getInhalt("Datentyp");
			boolean bVeraendert = /*(TabEigenschaft.getI("bits")&Abfrage.cstDialog)==0 &&*/  (bNeu && !Check.isNull(TabEigenschaft.getInhalt("Komponente"))
                            || Typ() == Bewegung || Check.Modified(TabEigenschaft.getInhalt("Komponente")));

                        if ((sDatentyp.equals("Eintritt") || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Austritt")) && bVeraendert /*&& EA_Rolle(TabEigenschaft.getI("Aic"))*/)
                        {
                          int iPosEig=g.TabEigenschaften.getPos("Aic",TabEigenschaft.getI("Aic"));
                          int iRolleMom=g.TabEigenschaften.getI(iPosEig,"Rolle");
                          g.progInfo("Eintritt geändert bei "+TabEigenschaft.getS("Bez")+" (Rolle="+iRolleMom+")");
                          Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from stamm_protokoll where aic_stamm="+iStamm+Abfrage.Rolle(iRolleMom)+" and pro_aic_protokoll is null",true);
                          //if (g.Prog())
                          //  Tab.showGrid("Tab");
                          if (Tab==null || Tab.isEmpty())
                          {
                            Insert.add("AIC_STAMM",iStamm);
                            Insert.add("AIC_PROTOKOLL",iProt);
                            Insert.add("Bezeichnung",sBezeichnung);
                            Insert.add0("AIC_ROLLE",iRolleMom);
                            Insert.add("AIC_CODE",g.getBegriffAicS("Status", bNeu ? "new" : "change"));
                            g.progInfo("1:"+sBezeichnung+"/"+iRolleMom+":"+((Datum)TabEigenschaft.getInhalt("Komponente")).getDate());
                            if (!sDatentyp.equals("Austritt"))
                              Insert.add("EINTRITT",((Datum)TabEigenschaft.getInhalt("Komponente")).getDate());
                            if (!sDatentyp.equals("Eintritt"))
                              Insert.add("AUSTRITT",((Datum)TabEigenschaft.getInhalt("Komponente")).getDate());
                            if (bStichtag)
                              Insert.add("Ab",dtStichtag);
                            //Insert.add("NR",-iRolleMom);
                            Insert.addNr(iStamm);
                            Insert.add("aic_mandant",iMandant);
                            Insert.add0("Firma",iFirma);
                            Insert.add("aic_stammtyp2",iStammtyp);
                            //Insert.add("Kennung2", sKennungNeu);
                            Insert.insert("STAMM_PROTOKoll",false);
                          }
                          else if (iProt != Tab.getI("aic_protokoll")) // Geburtstag ändern
                          {
                            //if (!sDatentyp.equals("Austritt") || iRolleMom!=0)
                            {
                              g.progInfo("2:" + sBezeichnung + "/" + iRolleMom + ":" + ((Datum)TabEigenschaft.getInhalt("Komponente")).getDate());
                              String sWhere=" aic_stamm=" + iStamm + Abfrage.Rolle(iRolleMom) + " and pro_aic_protokoll is null";
                              g.fixtestInfo(g.exec("delete from stamm_protokoll where aic_protokoll="+iProt+" and"+sWhere)+" stamm_protokoll gelöscht");
                              g.exec("update stamm_protokoll set pro_aic_protokoll=" + iProt + " where"+sWhere);
                              Insert.add("AIC_STAMM", iStamm);
                              Date dtEin2 = sDatentyp.equals("Austritt") ? Tab.getDate("Eintritt") : ((Datum)TabEigenschaft.getInhalt("Komponente")).getDate();
                              Insert.add("AIC_PROTOKOLL", iProt);
                              if (iRolleMom > 0 && dtEin2 == null && !sDatentyp.equals("Austritt"))
                                Insert.add("PRO_AIC_PROTOKOLL", iProt);
                              Insert.add("Bezeichnung", sBezeichnung);
                              Insert.add0("AIC_ROLLE", iRolleMom);
                              Insert.add("AIC_CODE", g.getBegriffAicS("Status", "change"));
                              Insert.add("EINTRITT", dtEin2);
                              int iPosA = sDatentyp.equals("Eintritt") || sDatentyp.equals("EinAustritt") ? getPosAustritt(iRolleMom) : -1;
                              Insert.add("AUSTRITT",
                                         iPosA >= 0 ? ((Datum)TabEigenschaft.getInhalt("Komponente", iPosA)).getDate() : sDatentyp.equals("Eintritt") ? Tab.getDate("Austritt") :
                                         ((Datum)TabEigenschaft.getInhalt("Komponente")).getDate());
                              //Insert.add("NR", Tab.getI("Nr"));
                              Insert.addNr(iStamm);
                              Insert.add("aic_mandant", iMandant);
                              Insert.add0("Firma", iFirma); // bis V 5.09.26: Tab.getI("Firma"));
                              Insert.add0("ANR",Tab.getI("ANR"));
                              Insert.add("aic_stammtyp2",iStammtyp);
                              Insert.insert("STAMM_PROTOKOll", false);
                              g.fixtestInfo("Save bei Geburtstag: setze ANR auf "+Tab.getI("ANR")+" bei Stamm "+iStamm);
                            }
                          }
                          else
                            g.exec("update stamm_protokoll set "+(sDatentyp.equals("Austritt")?"":"EINTRITT="+g.DateTimeToString(((Datum)TabEigenschaft.getInhalt("Komponente")).getDate()))+
                                   (sDatentyp.equals("EinAustritt")?",":"")+(sDatentyp.equals("Eintritt")?"":"AUSTRITT="+g.DateTimeToString(((Datum)TabEigenschaft.getInhalt("Komponente")).getDate()))+
                                   " where aic_stamm="+iStamm+Abfrage.Rolle(iRolleMom)+" and pro_aic_protokoll is null");
                          if (sDatentyp.equals("Austritt") && iRolleMom==0 && !Check.isNull(TabEigenschaft.getInhalt("Komponente")))
                          {
                            String sBed=" where aic_stamm="+iStamm+" and pro_aic_protokoll is null and austritt is null and aic_Protokoll<>"+iProt;
                            Tab=new Tabellenspeicher(g,"select * from stamm_protokoll"+sBed,true);
                            g.exec("update stamm_protokoll set pro_aic_protokoll="+iProt+sBed);
                            for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                            {
                              //g.fixInfo("    ----    Austritt bei "+Tab.getI("AIC_ROLLE")+" ändern !!!");
                              Insert.add("AIC_STAMM", iStamm);
                              Insert.add("AIC_PROTOKOLL",iProt);
                              Insert.add("Bezeichnung",Tab.getS("Bezeichnung"));
                              Insert.add("Eintritt", Tab.getDate("Eintritt"));
                              Insert.add("AUSTRITT",((Datum)TabEigenschaft.getInhalt("Komponente")).getDate());
                              Insert.add("AIC_CODE",Tab.getI("AIC_CODE"));
                              Insert.add0("AIC_ROLLE",Tab.getI("AIC_ROLLE"));
                              //Insert.add("NR",-Tab.getI("AIC_ROLLE"));
                              Insert.addNr(iStamm);
                              Insert.add("aic_mandant",iMandant);
                              Insert.add("Ab", Tab.getDate("Ab"));
                              Insert.add("Weg", Tab.getDate("Weg"));
                              Insert.add0("Firma",iFirma); // bis V 5.09.26: Tab.getI("Firma"));
                              Insert.add0("ANR",Tab.getI("ANR"));
                              Insert.add("aic_stammtyp2",iStammtyp);
                              Insert.insert("STAMM_PROTOKOLL",false);
                              g.fixtestInfo("Save bei Austritt: setze ANR auf "+Tab.getI("ANR")+" bei Stamm "+iStamm);
                            }
                          }
                          sSave+='\n'+TabEigenschaft.getS("Bez");
                          g.saveInfo("{save} "+TabEigenschaft.getS("Bez"));
                        }
            if (sDatentyp.equals("Bezeichnung2") && bVeraendert)
            	g.setBezeichnung(((Text)TabEigenschaft.getInhalt("Komponente")).getOld(), ((Text)TabEigenschaft.getInhalt("Komponente")).getText(), g.iTabStamm, iStamm, g.getSprache());
            else if (!sDatentyp.equals("Kennung") && !sDatentyp.endsWith("Bezeichnung") &&!sDatentyp.equals("Eintritt") &&!sDatentyp.equals("Austritt")
				&&!sDatentyp.equals("Stichtag") &&!sDatentyp.equals("EinAustritt") &&!sDatentyp.equals("Mandant") && bVeraendert)
			{
				int iAic = TabEigenschaft.getI("Aic");
				if (TabRest != null && TabRest.posInhalt("aic",iAic))
					TabRest.setInhalt("saved",Boolean.TRUE);
				//g.progInfo("Verändert oder Bewegung: "+TabEigenschaft.getInhalt("Komponente").getClass().getName()+" / "+sDatentyp+" / "+TabEigenschaft.getS("Bez"));
				//boolean bDatenNotwendig = false;
				//int iNr = 0;
				if (sDatentyp.startsWith("Bew") || sDatentyp.startsWith("Outliner") && Typ() == Bewegung)
				{
					Object Obj = TabEigenschaft.getInhalt("Komponente");
                                        if (!Check.isNull(Obj))
                                        {
                                          if (sDatentyp.equals("BewStamm") || sDatentyp.equals("BewButtons") || sDatentyp.equals("BewRadio")
                                              || sDatentyp.equals("BewHierarchie") || sDatentyp.startsWith("Outliner"))
                                          {
                                            if (iAic != Eigenschaft())// && !Check.isNull(Obj))
                                              Import.insertBewStamm(g, Bewegungstyp(), iSatz, iAic, Check.getI(Obj));
                                          }
                                          else if (sDatentyp.equals("BewDatum2"))
                                            Import.insertBewDatum2(g, Bewegungstyp(), iSatz, iAic, ((Datum)Obj).getDateTime());
                                          else if (sDatentyp.equals("BewBoolean"))
                                            Import.insertBewBool(g, Bewegungstyp(), iSatz, iAic);
                                          else if (sDatentyp.equals("BewBool3"))
                                            Import.insertBewBool3(g, Bewegungstyp(), iSatz, iAic, -((SpinBoxAuswahl)Obj).getAic());
                                          else if (sDatentyp.equals("BewVon_Bis") &&  Obj instanceof VonBisEingabe)
	                              			{
	                              				Vector Vec = ((VonBisEingabe)Obj).getValue();
	                              				java.sql.Timestamp dtBewVon= (java.sql.Timestamp)Vec.elementAt(0);
	                              				java.sql.Timestamp dtBewBis= (java.sql.Timestamp)Vec.elementAt(1);
	                              				double dDauer = Sort.getf(Vec.elementAt(2));
	                                        	Import.insertBewVonBis(g,Bewegungstyp(), iSatz, iAic,dtBewVon,dtBewBis,dDauer);
	                              			}
                                          else if (!( //SpeicherBewTyp("BewBool3","Spalte_Boolean","","Bew_Boolean",true,Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              //SpeicherBewTyp("BewBoolean","Spalte_Boolean","","Bew_Boolean",Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              //SpeicherBewTyp("BewBool3","Aic","","Bew_Aic",Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              //SpeicherBewTyp("BewStamm","Aic_Stamm","","Bew_Stamm",false,Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              //SpeicherBewTyp("BewButtons","Aic_Stamm","","Bew_Stamm",false,Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              //SpeicherBewTyp("BewRadio","Aic_Stamm","","Bew_Stamm",false,Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              //SpeicherBewTyp("BewHierarchie","Aic_Stamm","","Bew_Stamm",false,Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              SpeicherBewTyp("BewModul", "Aic_Begriff", "", "Bew_Begriff", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              SpeicherBewTyp("BewModell", "Aic_Begriff", "", "Bew_Begriff", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              SpeicherBewTyp("BewLand", "Aic", "", "Bew_Aic", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              SpeicherBewTyp("BewHoliday", "Aic", "", "Bew_Aic", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              SpeicherBewTyp("BewDruck", "Aic_Begriff", "", "Bew_Begriff", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              SpeicherBewTyp("BewFormular", "Aic_Begriff", "", "Bew_Begriff", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              //SpeicherBewTyp("Outliner","Aic_Stamm","","Bew_Stamm",false,Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              //SpeicherBewTyp("OutlinerM","Aic_Stamm","","Bew_Stamm",false,Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              //SpeicherBewTyp("BewDatum2","von","","Bew_von_bis",Obj,iSatz,iAic,sDatentyp,Insert) ||
                                              SpeicherBewTyp("BewDauer", "Dauer", "", "Bew_von_bis", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              SpeicherBewTyp("BewZahl", "Spalte_Wert", "", "Bew_Wert", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              SpeicherBewTyp("BewMass", "Spalte_Wert", "aic_stamm", "Bew_Wert", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              SpeicherBewTyp("BewVon_Bis", "von", "bis", "Bew_von_bis", Obj, iSatz, iAic, sDatentyp, Insert) ||
                                              SpeicherBewTyp("BewWaehrung", "Spalte_Wert", "aic_stamm", "Bew_Wert", Obj, iSatz, iAic, sDatentyp, Insert)))
                                            g.progInfo("Speichere mit SpeicherBewTyp nicht:" + sDatentyp + "/" + iAic);
                                        }
				}
				else
				{
					Date dtBis=null;
          boolean bSaveOk=true;
          //g.progInfo("---------------- bSaveOk1:"+bSaveOk+"/"+TabEigenschaft.getI("Aic")+"/"+TabEigenschaft.getS("Bez")+"/"+Check.isNull(TabEigenschaft.getInhalt("Komponente")));
          boolean bEigUseStichtag=(g.TabEigenschaften.getI_AIC("bits",iAic)&Global.cstKeinEigDate)==0;
          int iNr=0;
          int iGr=0;
          if (!Check.isNull(TabEigenschaft.getInhalt("Komponente")))
          {
            iNr=Import.getAicDaten(g,sDatentyp,TabEigenschaft.getInhalt("Komponente"),iAic);
            if (sDatentyp.equals("Gruppe") || sDatentyp.equals("Buttons") || sDatentyp.equals("Radio"))
              iGr=((AUComboList)TabEigenschaft.getInhalt("Komponente")).getComboBox().getSelectedAIC();							
						else if (sDatentyp.equals("Hierarchie"))
							iGr=Check.getI(TabEigenschaft.getInhalt("Komponente"));
						else if (sDatentyp.equals("Outliner") && Typ() == Stamm)
							iGr=((Integer)((AUOutliner)TabEigenschaft.getInhalt("Komponente")).getSelectedNode().getUserData()).intValue();
						else if ((sDatentyp.equals("Einheiten") || sDatentyp.equals("BenMass") || sDatentyp.equals("Mass"))
							&& ((MassEingabe)TabEigenschaft.getInhalt("Komponente")).getMass()!=0)
							iGr=((MassEingabe)TabEigenschaft.getInhalt("Komponente")).getMass();
						else if (sDatentyp.equals("Waehrung")
							&& ((WaehrungsEingabe)TabEigenschaft.getInhalt("Komponente")).getWaehrung()!=0)
							iGr=((WaehrungsEingabe)TabEigenschaft.getInhalt("Komponente")).getWaehrung();
            // g.fixtestError(sDatentyp+"/"+TabEigenschaft.getS("Bez")+" -> Nr="+iNr+", Gr="+iGr);
          }
          boolean bGleich=false;
          Date dtStichtag2=dtStichtag;
					if (Typ() == Stamm && !bNeu)
					  if (bStichtag && bEigUseStichtag)
					  {
              // g.fixError("Eig "+iAic+": "+bStichtag+"/"+bEigUseStichtag);
              // int iCount=-1;
              // if (b1970)
              // {
                int iCount=SQL.getInteger(g,"select count(*) from stammpool where pro2_aic_protokoll is null and aic_stamm="+iStamm+" and aic_eigenschaft="+iAic+" and gultig_von>"+g.DateTimeToString(dtStichtag));
                if (iCount>0)
                {
                  int iMO=sDatentyp.equals("Datum") ? Message.YES_OPTION:
                      new Message(Message.YES_NO_CANCEL_OPTION,(JFrame)thisFrame,g).showDialog("Stichtag_danach",new String[] {""+iCount,g.TabEigenschaften.getBezeichnungS(iAic)});
                  TabEigenschaft.posInhalt("Aic",iAic);
                  if (iMO == Message.CANCEL_OPTION)
                  {
                    g.rollback();
                    bSave=false;
                    return false;
                  }
                  if (iMO == Message.YES_OPTION)
                    g.exec("update stammpool set pro2_aic_protokoLl="+iProt+" where pro2_aic_protokoll is null and aic_stamm="+iStamm+" and aic_eigenschaft="+iAic+(b1970?"":" and gultig_von>="+g.DateTimeToString(dtStichtag)));
                  else
                    iCount=-1;
                }
              // }
              Tabellenspeicher TabVergleich=new Tabellenspeicher(g,"select aic_daten,sta_aic_stamm,Spalte_Double,gultig_von,gueltig_bis,aic_stammpool from stammpool where pro2_aic_protokoll is null and aic_stamm="+iStamm+" and aic_eigenschaft="+iAic+" order by "+g.orderD("gultig_von"),true);
              // TabVergleich.showGrid("Vergleich "+TabEigenschaft.getS("Bez"));
              long lStichtag=dtStichtag.getTime();
              int iMomPool=0;
              // long lLastV=0;
              Date dtVonG=null;
              Date dtBisG=null;
              Vector<Integer> VecP=null;
              for(TabVergleich.moveFirst();!TabVergleich.eof();TabVergleich.moveNext())
              {
                //g.progInfo("gultig_von="+TabVergleich.getInhalt("gultig_von")+"/"+TabVergleich.getDate("gultig_von"));
                Date dtVon=TabVergleich.getDate("gultig_von");
                long lVon=dtVon==null ? Long.MIN_VALUE : dtVon.getTime();
                // g.progInfo("lStichtag="+lStichtag+", lVon="+lVon+", iMomPool="+iMomPool);
                bGleich=iNr>0 && iNr==TabVergleich.getI("aic_daten") || iGr>0 && iGr==TabVergleich.getI("sta_aic_stamm") && TabVergleich.isNull("Spalte_Double");
                if (bGleich)
                {
                  if (VecP==null)
                  {
                    VecP=new Vector<Integer>();
                    iMomPool=TabVergleich.getPos()==0 ? 0:TabVergleich.getI(TabVergleich.getPos()-1,"aic_stammpool");
                    dtVonG=TabVergleich.getDate("gultig_von");
                    for (int iP=TabVergleich.getPos();bGleich && iP<TabVergleich.size();iP++)
                    {
                      bGleich=iNr>0 && iNr==TabVergleich.getI(iP,"aic_daten") || iGr>0 && iGr==TabVergleich.getI(iP,"sta_aic_stamm") && TabVergleich.isNull(iP,"Spalte_Double");
                      if (bGleich && (dtBisG==null || dtBisG.equals(TabVergleich.getDate(iP,"gultig_von"))))
                      {
                        dtBisG=TabVergleich.getDate(iP,"gueltig_bis");
                        VecP.addElement(TabVergleich.getI(iP,"aic_stammpool"));
                      }
                    }
                    bGleich=true;
                  }
                  
                }
                else
                {
                  // lLastV=lVon;
                  VecP=null;
                }
                
                if (lStichtag==lVon)
                {
                  if (!bGleich)
                    Insert.exec("update stammpool set pro2_aic_protokoll="+iProt+" where aic_stammpool="+TabVergleich.getI("aic_stammpool"));
                  TabVergleich.moveLast();
                  if (iCount==-1 && VecP==null)
                    dtBis=SQL.getTimestamp(g,"select min(gultig_von) from stammpool where pro2_aic_protokoll is null and aic_stamm="+iStamm+" and aic_eigenschaft="+iAic+" and gultig_von>"+g.DateTimeToString(dtVon));
                  g.progInfo("Stichtag ist gleich");
                  iMomPool=0;
                }
                else if (lVon>lStichtag)
                {
                  dtBis=dtVon;
                  TabVergleich.moveLast();
                  // g.fixtestError("Bis="+dtBis);
                  bGleich=false;
                  // g.exec("update stammpool set pro_aic_protokoll="+iProt+",gueltig_bis="+g.DateTimeToString(dtStichtag)+" where pro2_aic_protokoll is null and aic_stamm="+iStamm+" and aic_eigenschaft="+iAic+" and gueltig_bis="+g.DateTimeToString(dtVon));
                }
                else if (TabVergleich.isNull("gueltig_bis") || TabVergleich.getDate("gueltig_bis").getTime()>lStichtag)
                  iMomPool=TabVergleich.getI("aic_stammpool");
                                                          //else
                                                          //  g.progInfo("!!!!!!!!!!!!!!!! Gueltig_bis schon beschrieben mit "+TabVergleich.getS("gueltig_bis"));
              }
              // g.fixtestError(dtVonG+"-"+dtBisG+"/"+VecP);
              if (VecP != null)
              {
                dtBis=dtBisG;
                // g.fixtestError(dtVonG+"/"+dtStichtag+" -> before="+dtVonG.before(dtStichtag));
                if (dtVonG.before(dtStichtag))
                  dtStichtag2=dtVonG;
                // g.fixtestError(dtStichtag2+"->"+dtVonG.equals(dtStichtag2)+"/"+VecP);
                if (VecP.size()>1 || !dtVonG.equals(dtStichtag2))
                {
                  g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where aic_stammpool"+Static.SQL_in(VecP));
                  bGleich=false;
                  if (iMomPool>0 && !dtVonG.equals(dtStichtag2))
                    g.exec("update stammpool set pro_aic_protokoll="+iProt+",gueltig_bis="+g.DateTimeToString(dtStichtag2)+" where aic_stammpool="+iMomPool);
                }
              }
              else if (iMomPool>0)
              {
                  if (b1970)
                    g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where aic_stammpool="+iMomPool);
                  // else if (bGleich)
                  //   g.fixtestError("Gleich bei "+iMomPool+" / "+SQL.getTimestamp(g, "select gultig_von from stammpool where aic_stammpool="+iMomPool));
                  else if (!bGleich)
                  {
                    Insert.add("pro_aic_protokoll", iProt);
                    if (dtStichtag != null && dtStichtag.before(Static.dt0))
                      Insert.add("pro2_aic_protokoll", iProt);
                    Insert.add("gueltig_bis", dtStichtag);
                    Insert.update("StammPool", iMomPool);
                  }
              }
					  }
					  else
              {
                if (bSttUseStichtag && bEigUseStichtag && SQL.getInteger(g,"select count(*) from stammpool where pro2_aic_protokoll is null and aic_stamm="+iStamm+" and aic_eigenschaft="+iAic)>1)
                  bSaveOk=new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Stammstichtag") == Message.YES_OPTION;
                if (bSaveOk)
                  Insert.exec("update stammpool set pro2_aic_protokolL="+iProt+" where pro2_aic_protokoll is null and aic_stamm="+iStamm+" and aic_eigenschaft="+iAic);
              }
            if (iAic==g.iEigAustritt)
              bVAustritt=true;
					//if (sDatentyp.equals("StringKurzOhne"))
					//	sDatentyp = "StringKurz";

					/*if (sDatentyp.startsWith("String") ? !((ComboSort)TabEigenschaft.getInhalt("Komponente")).getSelectedItem().equals(""):
						sDatentyp.equals("Memo") || sDatentyp.equals("Text") ? !((JTextArea)TabEigenschaft.getInhalt("Komponente")).getText().equals(""):
						sDatentyp.equals("Gruppe") ? ((ComboSort)TabEigenschaft.getInhalt("Komponente")).getSelectedAIC() != 0:true)*/
                                        //g.progInfo("---------------- bSaveOk3:"+bSaveOk+"/"+TabEigenschaft.getI("Aic")+"/"+TabEigenschaft.getS("Bez")+"/"+Check.isNull(TabEigenschaft.getInhalt("Komponente")));
					if(bSaveOk && !Check.isNull(TabEigenschaft.getInhalt("Komponente")) && !bGleich)
					{
						g.saveInfo("Speichere tatsächlich:"+sDatentyp+"/"+TabEigenschaft.getS("Bez"));
						sSave+='\n'+TabEigenschaft.getS("Bez");
						g.saveInfo("{save} "+TabEigenschaft.getS("Bez"));

						//Stamm-Pool
						if (iNr > 0)
							Insert.add("Aic_Daten",iNr);
						Insert.add("Aic_Protokoll",iProt);
						if (Typ() == Stamm)
							Insert.add("Aic_Stamm",iStamm);
						else if(Typ() == Bewegung)
							Insert.add("Aic_Bew_Pool",iSatz);
						Insert.add("Aic_Eigenschaft",iAic);
            if (iGr>0)
            {
              Insert.add("STA_Aic_Stamm",iGr);
              if (iEigANR==iAic)
                g.exec("update stamm_protokoll set anr="+iGr+" where aic_stamm="+iStamm);
            }
						// if (sDatentyp.equals("Gruppe") || sDatentyp.equals("Buttons") || sDatentyp.equals("Radio"))
						// {
            //     int iAic2=((AUComboList)TabEigenschaft.getInhalt("Komponente")).getComboBox().getSelectedAIC();
						// 		Insert.add("STA_Aic_Stamm",iAic2);
            //     if (iEigANR==iAic)
            //       g.exec("update stamm_protokoll set anr="+iAic2+" where aic_stamm="+iStamm);
						// }
						// else if (sDatentyp.equals("Hierarchie"))
						// 	Insert.add("STA_Aic_Stamm",Check.getI(TabEigenschaft.getInhalt("Komponente")));
						// else if (sDatentyp.equals("Outliner") && Typ() == Stamm)
						// 	Insert.add("STA_Aic_Stamm",((Integer)((AUOutliner)TabEigenschaft.getInhalt("Komponente")).getSelectedNode().getUserData()).intValue());
						// else if ((sDatentyp.equals("Einheiten") || sDatentyp.equals("BenMass") || sDatentyp.equals("Mass"))
						// 	&& ((MassEingabe)TabEigenschaft.getInhalt("Komponente")).getMass()!=0)
						// 		Insert.add("STA_Aic_Stamm",((MassEingabe)TabEigenschaft.getInhalt("Komponente")).getMass());
						// else if (sDatentyp.equals("Waehrung")
						// 	&& ((WaehrungsEingabe)TabEigenschaft.getInhalt("Komponente")).getWaehrung()!=0)
						// 		Insert.add("STA_Aic_Stamm",((WaehrungsEingabe)TabEigenschaft.getInhalt("Komponente")).getWaehrung());

						if (sDatentyp.equalsIgnoreCase("Datum"))
							Insert.add("GULTIG_VON",((Datum)TabEigenschaft.getInhalt("Komponente")).getDate());
						else if (bSttUseStichtag && bEigUseStichtag)
							Insert.add("GULTIG_VON",dtStichtag2);
						if (dtBis !=null)
						{
							Insert.add("GUELTIG_BIS",dtBis);
							Insert.add("Pro_Aic_Protokoll",iProt);
						}

						if (sDatentyp.equals("Boolean"))
							Insert.add("Spalte_Double",((AUCheckBox)TabEigenschaft.getInhalt("Komponente")).isSelected());
                                                else if (sDatentyp.equals("Bool3"))
							Insert.add("Spalte_Double",Check.getI(TabEigenschaft.getInhalt("Komponente")));
						else if (sDatentyp.equals("Integer"))
							Insert.add("Spalte_Double",((Zahl)TabEigenschaft.getInhalt("Komponente")).intValue());
						else if (sDatentyp.equals("Double"))
							Insert.add("Spalte_Double",((Zahl)TabEigenschaft.getInhalt("Komponente")).doubleValue());
						else if (sDatentyp.equals("Farbe"))
							Insert.add("Spalte_Double",((AUFarbe)TabEigenschaft.getInhalt("Komponente")).getValue());
						else if (sDatentyp.equals("Bits"))
							Insert.add("Spalte_Double",((AUBits)TabEigenschaft.getInhalt("Komponente")).getValue());
						else if (sDatentyp.equals("Einheiten") || sDatentyp.equals("BenMass") || sDatentyp.equals("Mass"))
							Insert.add("Spalte_Double",((MassEingabe)TabEigenschaft.getInhalt("Komponente")).getValue());
						else if (sDatentyp.equals("Waehrung"))
							Insert.add("Spalte_Double",((WaehrungsEingabe)TabEigenschaft.getInhalt("Komponente")).getValue());
						else if (sDatentyp.equals("Stringx"))
							Insert.add("Spalte_Double",Import.getString(TabEigenschaft.getInhalt("Komponente")).length());
						else if (sDatentyp.equals("Land") || sDatentyp.equals("Aufgabe") || sDatentyp.equals("Status"))
							Insert.add("Spalte_Double",((ComboSort)TabEigenschaft.getInhalt("Komponente")).getSelectedAIC());
						Insert.insert("StammPool",false);
					}
				}

			}
			TabEigenschaft.moveNext();
		}
                if (iFirma!=iFirmaOld && !bNeu && Typ() == Stamm)
                {
                  int iAnz=SQL.getInteger(g,"select count(*) from poolview2 where sta_aic_stamm=?",-1,""+iStamm);
                  if (iAnz>0)
                  {
                    g.fixInfo("FirmaRebuild da "+iAnz+" darunter");
                    Version.FirmaRebuild(g,iStamm);
                  }
                }
                Import.saveRest(g,Insert,TabRest,iSatzOld,iSatz,Bewegungstyp(),iProt);
		/*if (TabRest != null)
		{
			//if (g.Prog())
			//	TabRest.showGrid("Rest");
			for(TabRest.moveFirst();!TabRest.eof();TabRest.moveNext())
                        {
                          int iPosE=TabRest.getB("saved") ? -1:g.TabEigenschaften.getPos("Aic",TabRest.getI("Aic"));
				if(iPosE>=0)
				{
					String sTyp=g.TabEigenschaften.getS(iPosE,"Datentyp");
					//g.progInfo("Check Rest:"+sTyp+"/"+g.TabEigenschaften.getS("Bezeichnung")+" ("+TabRest.getI("Aic")+")");
					if (sTyp.equals("BewStamm") || sTyp.equals("BewHierarchie") || sTyp.equals("BewButtons") || sTyp.equals("BewRadio"))
					{
						int iBewStamm=SQL.getInteger(g,"select aic_stamm from bew_stamm where aic_bew_pool="+iSatzOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
						//g.progInfo("iBewStamm="+iBewStamm);
						if (iBewStamm>0)
						{
                                                  Import.insertBewStamm(g,Bewegungstyp(),iSatz,TabRest.getI("Aic"),iBewStamm);
						}
					}
					else if (sTyp.equals("BewBoolean"))
					{
						int iBewBoolean=SQL.getInteger(g,"select spalte_boolean from bew_boolean where aic_bew_pool="+iSatzOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
						if (iBewBoolean>0)
						{
                                                  Import.insertBewBool(g,Bewegungstyp(),iSatz,TabRest.getI("Aic"));
						}
					}
					else if (sTyp.equals("BewLand") || sTyp.equals("BewHoliday") || sTyp.equals("BewBool3"))
					{
						int iAic=SQL.getInteger(g,"select aic from bew_aic where aic_bew_pool="+iSatzOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
						if (iAic>0)
						{
                                                  Import.insertBewBool3(g,Bewegungstyp(),iSatz,TabRest.getI("Aic"),-iAic);
						}
					}
					else if (sTyp.equals("BewModul") || sTyp.equals("BewModell") || sTyp.equals("BewDruck") || sTyp.equals("BewFormular"))
					{
						int iBegriff=SQL.getInteger(g,"select aic_begriff from bew_begriff where aic_bew_pool="+iSatzOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
						if (iBegriff>0)
						{
							Insert.add("aic_Bew_Pool",iSatz);
							Insert.add("aic_Eigenschaft",TabRest.getI("Aic"));
							Insert.add("aic_begriff",iBegriff);
							Insert.insert("bew_begriff",false);
						}
					}
					else if (sTyp.equals("BewZahl") || sTyp.equals("BewMass") || sTyp.equals("BewWaehrung"))
					{
						double dBewZahl=SQL.getDouble(g,"select spalte_wert from Bew_Wert where aic_bew_pool="+iSatzOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
						if (dBewZahl!=0.0)
						{
							Insert.add("aic_Bew_Pool",iSatz);
							Insert.add("aic_Eigenschaft",TabRest.getI("Aic"));
							Insert.add0("aic_stamm",SQL.getInteger(g,"select aic_stamm from Bew_Wert where aic_bew_pool="+iSatzOld+" and aic_eigenschaft="+TabRest.getI("Aic")));
							Insert.add("spalte_wert",dBewZahl);
							Insert.insert("Bew_Wert",false);
						}
					}
					else if (sTyp.equals("BewDatum2") || sTyp.equals("BewDauer") || sTyp.equals("BewVon_Bis"))
					{
						//double dBewZahl=SQL.getInteger(g,"select spalte_wert from Bew_Wert where aic_bew_pool="+iSatzOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
						Insert.open("select von,bis,Dauer from Bew_von_bis where aic_bew_pool="+iSatzOld+" and aic_eigenschaft="+TabRest.getI("Aic"));
						if (!Insert.eof())
						{
                                                  if (sTyp.equals("BewDatum2"))
                                                    Import.insertBewDatum2(g,Bewegungstyp(),iSatz,TabRest.getI("Aic"),Insert.getTS("von"));
                                                  else
                                                  {
                                                    java.sql.Timestamp dtBewVon = Insert.getTS("von");
                                                    java.sql.Timestamp dtBewBis = Insert.getTS("bis");
                                                    double dBewDauer = Insert.getF("Dauer");
                                                    Insert.add("aic_Bew_Pool", iSatz);
                                                    Insert.add("aic_Eigenschaft", TabRest.getI("Aic"));
                                                    Insert.add("von", dtBewVon);
                                                    Insert.add("bis", dtBewBis);
                                                    Insert.add("Dauer", dBewDauer);
                                                    Insert.insert("Bew_von_bis", false);
                                                  }
						}
					}
                                       
				}
                        }
			Tabellenspeicher TabRest2=new Tabellenspeicher(g,"select * from stammpool where pro2_aic_protokoll is null and aic_bew_pool="+iSatzOld,true);
			//TabRest2.showGrid("Rest2");
			for(TabRest2.moveFirst();!TabRest2.eof();TabRest2.moveNext())
				if(TabRest.posInhalt("Aic",TabRest2.getI("Aic_eigenschaft")) && !TabRest.getB("saved"))
				{
					Insert.add("aic_Bew_Pool",iSatz);
					Insert.add("aic_Eigenschaft",TabRest.getI("Aic"));
					Insert.add0("aic_Daten",TabRest2.getI("Aic_Daten"));
					Insert.add0("sta_aic_Stamm",TabRest2.getI("sta_aic_Stamm"));
					if (TabRest2.getInhalt("Spalte_Double") != null)
						Insert.add("Spalte_Double",TabRest2.getF("Spalte_Double"));
					Insert.add("Aic_Protokoll",iProt);
					Insert.insert("Stammpool",false);
				}

                }*/
                iError=g.getFehler()-iError;
                if (iError>0)
                {
                  g.rollback();
                  new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("Rollback");
                }
                else
                {
                  g.commit();
                  if (bImport)
                    AClient.send_AServer(AClient.getImport(g),g);
                }
		Insert.free();
                g.clockInfo("save "+sBezNeu,lClock2);
		Ausgabe("EF-SpeichereDaten");
		if (Static.bSpeichernAnzeigen && !sSave.equals(""))
			new Message(Message.INFORMATION_MESSAGE,(JFrame)thisFrame,g).showDialog("SaveInfo",new String[] {sSave});
                if (bNeu && Typ() == Stamm)
                  g.addToVecStamm(iStammtyp,iStamm);
                if (bVAustritt)
                  ShowAbfrage.checkAustritt(g);
                if (EdtStichtag!=null)
                  EdtStichtag.setEnabled(true);
                ResetModified();
                if(bNeu && Synchron() && Typ()==Stamm)
                {
                  int iPos = g.TabStammtypen.getPos("Aic", iStammtyp);
                  if (iPos >= 0)
                    g.TabStammtypen.setInhalt(iPos, "Sync", iStamm);
                }
        if (Typ()==Stamm && iStammtyp==g.iSttZone)
        	g.clearZone();
		if (bCalc)
                  ;
                else if (Login())
                  FuelleEigenschaften(0,true);
                else //if (bReLoad) // 20.5.2010 weg
		{
                  g.progInfo("Save-ReLoad1");
                  bCalc=!bNeu; // für Sync nach Neuerfassung nötig
                  //bOutEvent=bNeu2;
                  if ((bNeu || Typ()==Bewegung || bChangeDate) && TabOutliner.posInhalt("Eig",0))
                  {
                    if (bNeu || bChangeDate)
                      FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"), iSatz, false, true);
                    else
                      ((JCOutliner)TabOutliner.getInhalt("Gid")).getSelectedNode().setUserData(new Integer(iSatz));
                  }
                  //if (bNeu2)
                    FuelleEigenschaften(iSatz,false);
                  bCalc=false;
                  //bOutEvent=true;
		}
                /*else // 20.5.2010 weg
                {
                  bReLoad = true;
                   g.progInfo("Save-ReLoad2");
                  if (Typ()==Bewegung && TabOutliner.posInhalt("Eig",0))
                    FuelleOutliner((JPanel)TabOutliner.getInhalt("Pnl"),0,false,true);
                }*/
		Ausgabe("EF-FuelleOutliner");
	}
        bSave=false;
	return true;
	}

}

