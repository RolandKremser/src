/*
    All_Unlimited-Allgemein-Formular.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.*;

import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
//import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.EF_Eingabe;
import All_Unlimited.Allgemein.Eingabe.GPS_Eingabe;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.Insets;
//import java.awt.Panel;
import java.awt.Point;

import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Allgemein.Eingabe.ComboSort;

import java.awt.event.ActionEvent;

import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Hauptmaske.AClient;
//import java.awt.event.KeyListener;
//import java.awt.event.KeyEvent;
//import All_Unlimited.Hauptmaske.ShowAbfrage;
import jclass.bwt.BWTEnum;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;

//import java.awt.Cursor;

public class Formular extends java.lang.Object
{
  public static final int cstNotResizeable= 0x0001;
  public static final int cstNotClose	  = 0x0002;
  public static final int cstAutoSave	  = 0x0004;
  public static final int cstStdFormular  = 0x0008;
  public static final int cstKopierbar	  = 0x0010;
  public static final int cstNurOrdner	  = 0x0020;
  public static final int cstBewVoll	  = 0x0040;
  public static final int cstKeinStamm	  = 0x0080;
  public static final int cstSynchron	  = 0x0100;
  public static final int cstVerteiler	  = 0x0200;
  public static final int cstRefresh      = 0x0400;
  public static final int cstMehrfach     = 0x0800;
  public static final int cstLogin        = 0x1000;
  //public static final int cstMonat        = 0x2000; s. unten
  public static final int cstKeinStammTitel=0x4000;
  public static final int cstOhneStamm    = 0x8000;
  public static final int cstKeinReadOnly =0x10000;
  public static final int cstPopup        =0x20000;
  public static final int cstNeuCheck     =0x40000;
  public static final int cstHGB          =0x80000;
  public static final int cstBewBew      =0x100000;
  public static final int cstSubRollen   =0x200000;
  public static final int cstRefresh2    =0x400000;
  public static final int cstStammFix    =0x800000;
  public static final int cstMultiselect=0x1000000;
  public static final int cstNbMulti    =0x2000000;
  public static final int cstRepaint    =0x4000000;
  public static final int cstKeinZR     =0x8000000;
//  public static final int cstJavaFX    =0x10000000; // noch in Reinigung //war cstTranslate
  public static final int cstAllSync   =0x20000000;
  public static final int cstInfo      =0x40000000;
  public static final long cstZR       =0x80002000l;
  public static final int cstMonat        = 0x2000;
  public static final long cstTag      =0x80000000l;
  public static final long cstWoche    =0x80002000l;
  
  // JavaFX-Radiobuttons entfernt (nur noch zum bits-Anzeigen da):
  public static final long cstSS      =0x600000000l; // StageStyle
  public static final long cstDec     =0x000000000l; //   Decorated
  public static final long cstUnDec   =0x200000000l; // 33  Undecorated
  public static final long cstTrans   =0x400000000l; // 34  Transparent
  public static final long cstUtil    =0x600000000l; //   Utility
//  public static final long cstOnTop   =0x800000000l; // 35 immmer im Vordergrund
  public static final long cstFullScr=0x1000000000l; // 36 schon mit Full-Screen starten
  public static final long cstFxArt  =0x12100000000l; // Art des FX-Formulars: Stage, PopOver, SubScene, Btn für Scene
  public static final long cstStage   = 0x000000000l; // Default normales Stage
  public static final long cstPopOver = 0x100000000l; // 32 PopOver über Formular-Knopf
  public static final long cstScene   =0x2000000000l; // 37 Kein eigenes Formular sondern nun eine Subscene
  public static final long cstBtnScene=0x2100000000l; // Toggle-Button zum Scene-Umschalten
  public static final long cstDialog =0x10000000000l; // 40;
  public static final long cstDrawer =0x10100000000l; // DrawerStack in Scene
  
  public static final long    cstFxHS =   0x4000000000l; // 38 neuer Hauptschirm für JavaFX: EingabeFormular mit Zusatzfunktionen
//  public static final long    cstMenu =   0x8000000000l; // 39 Menü oben
//  //public static final long  cstAP   =  0x20000000000l; // 41 auf AnchorPane statt BorderPane
//  public static final long cstFomTitel=  0x40000000000l; // 42 kein Formulartitel (auch keine Titelzeile)
//  public static final long cstNoDetach=  0x80000000000l; // 43 kein wegziehen (bei PopOver)
//  public static final long cstNoArrow=  0x100000000000l; // 44 kein Pfeil (bei PopOver)
//  public static final long cstKeinST=   0x200000000000l; // 45 ganzes Formular einhält keinen Stichtag
//  public static final long cstFertig=   0x400000000000l; // 46 Kennzeichnet fertige Formulare
//  public static final long cstCenter=   0x800000000000l; // 47 Zentriert das Formualar am Bildschirm
  public static final long cstTabFom=  0x1000000000000l; // 48 Subformular aus Tabellenspalte aufrufbar
  //public static final long cstTemplate=0x2000000000000l; // 49 für externes Web-Server-Formular (Template)
  public static final long cstPers=    0x4000000000000l; // 50 Persönlich für Web
  public static final long cstNotHandy=0x8000000000000l; // 51 nicht am Handy darstellen
  
  public static Tabellenspeicher TAB_Frames=null;

/* Für Eingabe-Formulare */
	public Formular(Global rg,int riFormular,JDialog FrmVor)
	{
		g = rg;
		//iTyp = rsTyp.equals("Stammtyp") ? Stamm:rsTyp.equals("Bewegungstyp") ? Bewegung:kein;
		iBegriff = riFormular;
                String sTypBez=null;
                int iPos=-1;
		if (riFormular<0)
		{
			iStammtyp =-riFormular;
			riFormular = g.getBegriffAicS("Frame","EingabeFormular");
                        iPos=g.TabBegriffe.getPos("Aic",riFormular);
		}
		else
		{
                        iPos=g.TabBegriffe.getPos("Aic",riFormular);
                        if (iPos<0)
                        {
                          g.printError("Formular " + riFormular + " nicht gefunden!");
                          return;
                        }
			iStammtyp = g.TabBegriffe.getI(iPos,"Stt");
			iProg = g.TabBegriffe.getI(iPos,"Prog");
			iBewegungstyp = g.TabBegriffe.getI(iPos,"Erf");
                        iRolle = g.TabBegriffe.getI(iPos,"Rolle");
                        if (iRolle>0)
                          sTypBez="Rol-"+g.TabRollen.getBezeichnungS(iRolle);
                        else if (iBewegungstyp>0)
                          sTypBez="Bew-"+g.TabErfassungstypen.getBezeichnungS(iBewegungstyp);
                        else
                          sTypBez="Stt-"+g.TabStammtypen.getBezeichnungS(iStammtyp);
			iTyp = iBewegungstyp > 0 ? Bewegung :Stamm;
			if (iTyp == Bewegung)
				iEigenschaft=SQL.getInteger(g,g.top("e.aic_eigenschaft from bewegungstyp join bew_zuordnung on bewegungstyp.aic_bewegungstyp=bew_zuordnung.aic_bewegungstyp join eigenschaft e on e.aic_eigenschaft=bew_zuordnung.aic_eigenschaft where aic_stammtyp="+iStammtyp+" and bewegungstyp.aic_bewegungstyp="+iBewegungstyp+" order by reihenfolge",1));
		}
                iBits=g.TabBegriffe.getL(iPos,"bits");//SQL.getInteger(g,"SELECT bits from begriff where aic_begriff="+riFormular);
                //g.fixInfo("iBits1="+iBits);
                if ((iBits&cstStammFix)>0)
                  iFixStamm=SQL.getInteger(g,"select aic_stamm from formular where aic_begriff="+iBegriff);
		if (FrmVor != null)
                  AU_Dialog=new JDialog(FrmVor,true);
                else
                {
                  AU_Frame = new JFrame();
                  int iPosB=g.TabBegriffe.getPos("Aic",iBegriff);
                  if (iPosB>=0)
                    AU_Frame.setName(g.TabBegriffe.getS(iPosB,"Kennung"));
                }
                //AU_Frame.setBackground(g.ColEF);
		setTitle(g.getDB_Name()+(g.Def()?sTypBez+" - ":"")+g.getBegBez2(iPos));
		UeberschriftsKennung=g.TabBegriffe.getS(iPos,"Kennung");
		//iFormNr=SQL.getInteger(g,"SELECT AIC_Formular from formular where aic_begriff="+riFormular);
                iFormNr=getForm(g,riFormular);
		Count.add(Count.Formular);
		initFormular();
	}

        public static int getForm(Global rg,int iBeg)
        {
          if (TAB_Frames!=null && TAB_Frames.posInhalt("aic_begriff",iBeg))
            return TAB_Frames.getI("aic_formular");
          if (TAB_Frames !=null)
            rg.testInfo("Formular von Begriff "+iBeg+" nicht gefunden!");
          TAB_Frames=new Tabellenspeicher(rg,"select aic_formular,aic_begriff,aic_eigenschaft,aic_abfrage,aic_modell,null Abf from formular",true);
          return TAB_Frames.posInhalt("aic_begriff",iBeg) ? TAB_Frames.getI("aic_formular"):0;
        }

        public static void clearForm()
        {
          if (TAB_Frames != null)
          {
            TAB_Frames.clearAll();
            TAB_Frames = null;
          }
        }

	/*public Formular(Global rg,int riStammtyp)
	{
		this(rg,riStammtyp,Stamm);
	}*/

	/*public Formular(Global rg,int riAic,int riTyp) // weg 6.8.2009
	{
		Count.add(Count.Formular);
		iTyp=riTyp==Mehrfach ? Stamm:riTyp;
		g = rg;
		g.progInfo(" F O R M U L A R : Stt="+riAic+", Typ="+riTyp);
		iStammtyp = riAic;
		SQL Qry = new SQL(g);
		Qry.open("SELECT AIC_Formular,bits,begriff.aic_begriff,Kennung FROM Formular join begriff on formular.aic_begriff=begriff.aic_begriff"+
                         " WHERE aic_bewegungstyp is null and aic_stammtyp="+iStammtyp+" and "+g.bit("bits",riTyp==Mehrfach?cstMehrfach:cstStdFormular));
		for(;(iFormNr==0) && !Qry.eof();Qry.moveNext())
		{
			if (g.TabBegriffe.posInhalt("Aic",Qry.getI("aic_begriff")) && g.Lizenz() && g.Berechtigung())
			{
				AU_Frame=new JFrame();
				iFormNr=Qry.getI("AIC_Formular");
                                g.progInfo("-----------> FormNr="+iFormNr+" (Typ="+riTyp+")");
				UeberschriftsKennung = Qry.getS("Kennung");
				iBegriff=Qry.getI("aic_begriff");
				iBits=Qry.getI("bits");
				setTitle((g.Def()?g.getDB_Name()+"EingabeFormular - ":"")+g.TabBegriffe.getS(Static.bDefBezeichnung ?"DefBezeichnung":"Bezeichnung"));
			}
		}
		Qry.free();
		if (iFormNr==0)
		{
			if(g.Def())
			{
				AU_Frame=new JFrame();
				iBegriff=-iStammtyp;
				UeberschriftsKennung = "EingabeFormular";
				bEingabeFormular = true;
			}
			else
			{
				new Message(Message.WARNING_MESSAGE,null,g).showDialog("keineBerechtigung");
				return;
			}
		}
		initFormular();
	}*/

/* Für AU_Formulare */
	public Formular(int riAic,Global rg)
	{
		//super("");
                AU_Frame=new JFrame();
                g = rg;
		iTyp = kein;
		//g.addInfo(" - - - - - - -   Formularausbau "+riAic+"   - - - - - - -");
		iFormNr = riAic;
		Count.add(Count.Formular);
                initFormular();
                g.SaveVerlauf(iBegriff,0,0);
	}

/* Für konkrete Formulare (z.B. Zuordnung, Formularerstellung) */
	public Formular(String sKennung, Window FrmVor, Global glob)
	{
		//super("");
		if(FrmVor!=null)
			AU_Dialog=FrmVor instanceof JFrame ? new JDialog((JFrame)FrmVor,true):new JDialog((JDialog)FrmVor,true);
		else
                {
                  AU_Frame = new JFrame();
                  //AU_Frame.setForeground(Color.black);
                }
		bSystem=true;
		g=glob;
		//g.addInfo(" - - - - - - -   Formularausbau "+sKennung+"   - - - - - - -");
		UeberschriftsKennung=sKennung;
		Count.add(Count.Formular);
		initFormular();
                setTitle(g.getDB_Name()+getTitle());
                g.SaveVerlauf(iBegriff,0,0);
	}

	public void finalize()
	{
		g.printInfo("finalize:"+UeberschriftsKennung);
		Count.sub(Count.Formular);
	}

	private void initFormular()
	{
		//long lClock = Static.get_ms();
                //g.progInfo("ASA/MS/Oracle="+SQL.bASA+"/"+SQL.bMS+"/"+SQL.bOracle);
                iFF=g.getFontFaktor();
		boolean bOK = iFormNr != 0 && iStammtyp != 0;
		g.testInfo("iFormNr="+iFormNr+",iStammtyp="+iStammtyp+" ->"+bOK+", iFF="+iFF);

		TabKomponenten = new Tabellenspeicher(g,new String[] {"Kennung","Tab"});
		TabFormular = new Tabellenspeicher(g,new String[] {"Aic","Button","Formular","Kennung","Gruppe","Comp"});

		TabKomponenten.addInhalt("Kennung","Button");
		TabKomponenten.addInhalt("Kennung","frei");
		TabKomponenten.addInhalt("Kennung","Gruppe");
		TabKomponenten.addInhalt("Kennung","Checkbox");
		TabKomponenten.addInhalt("Kennung","Web");
		TabKomponenten.addInhalt("Kennung","JCTable");
		TabKomponenten.addInhalt("Kennung","Radiobutton");
		TabKomponenten.addInhalt("Kennung","Grafik");
		TabKomponenten.addInhalt("Kennung","Planung");
		TabKomponenten.addInhalt("Kennung","EFGroup");
		TabKomponenten.addInhalt("Kennung","Label");
		//TabKomponenten.addInhalt("Kennung","Abfrage");
		//String[] s1={"Kennung","Komponente"};
		for(int i=0;i<TabKomponenten.size();i++)
			TabKomponenten.addInhalt("Tab",new Tabellenspeicher(g,new String[] {"Kennung","Komponente","use"}));
		thisFrame=AU_Frame==null?(Window)AU_Dialog:(Window)AU_Frame;

		if (!bOK)
		{
			SQL Qry = new SQL(g);
			Qry.open("SELECT AIC_Formular,bits,Aic_Stammtyp,begriff.aic_begriff,Kennung"+(Static.bDefBezeichnung ?",DefBezeichnung":g.AU_Begriff())+" Bezeichnung FROM Formular join begriff on formular.aic_begriff=begriff.aic_begriff WHERE "+(iFormNr == 0 ? "Kennung='"+UeberschriftsKennung+"'" :"Aic_Formular="+iFormNr));
			if(!Qry.eof())
			{
				iFormNr = Qry.getI("AIC_Formular");
				iBits=Qry.getL("bits");
                                //g.fixInfo("iBits2="+iBits);
				if (iStammtyp == 0)
					iStammtyp = Qry.getI("Aic_Stammtyp");
				UeberschriftsKennung = Qry.getS("Kennung");
				if (iBegriff == 0)
					iBegriff=Qry.getI("aic_begriff");
				//setTitle(Qry.getS("Bezeichnung").equals("") ? UeberschriftsKennung : Qry.getS("Bezeichnung"));
				setTitle(bEingabeFormular ? (g.Def()?g.getDB_Name()+"EingabeFormular - ":"")+g.getBezeichnung("Stammtyp",iStammtyp):(Qry.getS("Bezeichnung").equals("") ? UeberschriftsKennung : Qry.getS("Bezeichnung")));
				//Qry.close();
				bOK = true;
				//g.printInfo(getTitle()+" gefunden!");
			}
			Qry.free();
		}
                if (iBegriff>0)
                  g.addUsed(iBegriff);
                else
                  g.progInfo("iBegriff=0 bei "+sFormularBezeichnung);
                if (iStammtyp > 0)
                {
                  TabAbf=new Tabellenspeicher(g,new String[] {"Pnl","Zeile","Aic","Komponente","Rbn","aktiv","Art","h","Spalten"});
                  TabSub=new Tabellenspeicher(g,new String[] {"Pnl","Aic","Art"});
                }

		if (bOK)
		{
			Laden();
			FormularEvent();
			//g.addInfo(" - - - - - - -   Formularausbau fertig   - - - - - - -");
			//g.printInfo(getTitle()+":"+(Static.get_ms()-lClock));
			if(iBits>-1 && (iBits & cstNotResizeable)>0)
			{
				if(AU_Frame==null)
					AU_Dialog.setResizable(false);
				else
					AU_Frame.setResizable(false);
			}
			if(iBits>-1 && (iBits & cstNotClose)>0)
			{
				g.defInfo2(sFormularBezeichnung+": DO_NOTHING_ON_CLOSE");
                                if(AU_Frame==null)
                                  AU_Dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				else
                                  AU_Frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			}
                        /*if (g.TestPC() && (iStammtyp > 0))
			{
				TabAbf.showGrid("TabAbf");
				TabMod.showGrid("TabMod");
			}*/
		}
		else
		{
			new Message(Message.WARNING_MESSAGE,null,g).showDialog("Formular",new String[] {iFormNr==0 ? UeberschriftsKennung:""+iFormNr});
			//thisFrame.hide();
		}
	}

	/*public String getHauptgruppe()
	{
		return  bEingabeFormular ? "Stammtyp"+iStammtyp:iStammtyp != 0 ? "Formular"+iFormNr : UeberschriftsKennung;
	}*/

	public void saveFenster()
	{
		//String s = bEingabeFormular ? "Stammtyp"+iStammtyp:iStammtyp != 0 ? "Formular"+iFormNr : UeberschriftsKennung;
		g.progInfo("Fenster >"+sFormularBezeichnung+"< speichern");
		g.setFenster(thisFrame,iBegriff,iDaten*2,0,iFF);
	}

        /*public void paint(Graphics gr)
        {
          thisFrame.paint(gr);
          g.testInfo("Size="+thisFrame.size());
        }*/

	private void FormularEvent()
	{
		thisFrame.addWindowListener(new WindowListener()
		{
			public void windowClosed(WindowEvent e)
			{
				/*TabFormular.moveFirst();
				while(!TabFormular.eof())
				{
					if(TabFormular.getInhalt("Formular")!=null)
					{
						//new Parameter(g,TabFormular.getS("Kennung")).setFenster((JFrame)TabFormular.getInhalt("Formular"));
						Object Obj = TabFormular.getInhalt("Formular");
						if(Obj instanceof Dialog)
							((Dialog)Obj).hide();
						else if(Obj instanceof Frame)
							((Frame)Obj).hide();
						else
							g.printError("Fehler in der Tabelle TabFormular!!! Der Datentyp in der Spalte \"Formular\" ist falsch!!!");
					}
					TabFormular.moveNext();
				}*/
			}
			public void windowOpened(WindowEvent e)
			{
			}
			public void windowClosing(WindowEvent e)
			{
                          //g.fixInfo(sFormularBezeichnung+" windowClosing Stt="+iStammtyp);
				if (iBits>-1  && (iBits & cstNotResizeable)==0)
					saveFenster();
				if (iBits>-1  && (iBits & cstNotClose)==0 && iStammtyp==0)
					thisFrame.setVisible(false);
			}
			public void windowIconified(WindowEvent e)
			{
			}
			public void windowDeiconified(WindowEvent e)
			{
                          //g.testInfo("Size="+thisFrame.size());
			}
			public void windowActivated(WindowEvent e)
			{
				if (Global.bLogout && iStammtyp==0 && iBewegungstyp==0 && !g.bLizenzFrei)
				{
//					g.fixtestError("Aktiviere "+sFormularBezeichnung);
//					TabFormular.showGrid(sFormularBezeichnung);
//					Tabellenspeicher Tab = getTab("Button");
					for (int i=0;i<TabFormular.size();i++)
					{
						int iAic=TabFormular.getI(i, "Aic");
						Object Obj=TabFormular.getInhalt("Button",i);
						int iPos=g.TabBegriffe.getPos("Aic",iAic);
//						g.fixtestError(" "+iAic+":"+TabFormular.getInhalt("Comp",i)+"/"+Obj);
						JComponent Btn=Obj instanceof JComponent ? (JComponent)Obj:null;
						if (Btn!=null)
							Btn.setEnabled(!TabFormular.isNull(i, "Comp") || g.BerechtigungS(iPos));
					}
				}
			}
			public void windowDeactivated(WindowEvent e)
			{
			}
		});
                if (g.Def())
                thisFrame.addComponentListener(new ComponentAdapter() {
                  public void componentResized(ComponentEvent e) {
                    if (Static.bShowSize)
                      setTitle(sFormularBezeichnung+" / "+thisFrame.getWidth()+" x "+thisFrame.getHeight());
                    //g.testInfo("Size="+thisFrame.size());
                  }
                });

	}

	private void Laden()
	{
		TabRadio = new Tabellenspeicher(g,new String[]{"ID","ButtonGroup"});
		//long lClock = Static.get_ms();
		TabDarstellung = new Tabellenspeicher(g,"select aic_darstellung,dar_aic_darstellung,aic_begriff,reihenfolge,x,y,w,h from darstellung2 where aic_formular="+iFormNr+" order by "+g.order("dar_aic_darstellung")+",reihenfolge",true,"Formular"+iFormNr);
                //if (g.Prog())
                //  TabDarstellung.showGrid(sFormularBezeichnung);
		//long lClock2= Static.get_ms();
		//g.printInfo("Darstellung"+iFormNr+":"+(lClock2-lClock));
		if(!TabDarstellung.eof())
		{
			//String s = bEingabeFormular ? "Stammtyp"+iStammtyp:iStammtyp != 0 ? "Formular"+iFormNr : UeberschriftsKennung;
			//g.printInfo("Fenster >"+s+"< laden");
            bFP=g.getFenster(thisFrame,iBegriff,iBits==-1  || Static.bStdSize || (iBits & cstNotResizeable)>0,
                                     TabDarstellung.getI("x"),TabDarstellung.getI("y"),TabDarstellung.getI("w"),TabDarstellung.getI("h"),iFF);
//            if (!bFP)
//            	g.fixtestError("Keine Fensterpos für "+g.getBegBez(iBegriff)+" mit "+thisFrame.getWidth()+"x"+thisFrame.getHeight());
                        if((iBits&cstHGB)>0)
                          thisJFrame().setMinimumSize(new java.awt.Dimension(TabDarstellung.getI("w"),TabDarstellung.getI("h")));
                        iDaten=g.getFensterBits(iBegriff)/2;
			//if(iBits==-1  || (iBits & cstNotResizeable)>0 || !g.getFenster(thisFrame,iBegriff))
			//	thisFrame.setBounds(TabDarstellung.getI("x"),TabDarstellung.getI("y"),TabDarstellung.getI("w"),TabDarstellung.getI("h"));

                        //g.printInfo("Fenstergröße laden:"+(Static.get_ms()-lClock2));
			Laden_Rekursion(TabDarstellung.getI("aic_darstellung"),AU_Frame==null?AU_Dialog.getContentPane():AU_Frame.getContentPane(),"Frame");
		}
	}

	private void Laden_Rekursion(int iAIC, Container Pnl, String sVorgaenger)
	{
		//Tabellenspeicher TabQry = new Tabellenspeicher(g,"SELECT begriff.aic_begriff,aic_darstellung,x,y,w,h,reihenfolge,darstellung.kennung,begriff.kennung begriff,begriffgruppe.kennung gruppe FROM Darstellung JOIN Begriff JOIN Begriffgruppe WHERE Dar_AIC_Darstellung = "+iAIC+" AND AIC_Formular="+iFormNr+" ORDER BY Reihenfolge");
		TabDarstellung.push();
		TabDarstellung.posInhalt("DAR_AIC_DARSTELLUNG",iAIC);
		while(!TabDarstellung.out() && TabDarstellung.getI("DAR_AIC_DARSTELLUNG") == iAIC)
		{
			int iPos=g.TabBegriffe.getPos("Aic",TabDarstellung.getI("AIC_BEGRIFF"));
                        if (iPos<0)
                        {
                          g.printError("Formular.Laden_Rekursion: Begriff "+TabDarstellung.getI("AIC_BEGRIFF")+" fehlt bei "+TabDarstellung.getI("aic_darstellung"),iBegriff);
                          if (g.Def())
                          {
                            g.exec("delete from darstellung where aic_darstellung=" + TabDarstellung.getI("aic_darstellung"));
                            g.exec("update begriff set ImportZeit=null where aic_begriff=" + iBegriff);
                          }
                        }
                        else
                        {
                          //g.TabBegriffgruppen.posInhalt("Aic",g.TabBegriffe.getI("Gruppe"));
                          sGruppe = g.TabBegriffgruppen.getKennung(g.TabBegriffe.getI(iPos, "Gruppe")); //g.TabBegriffgruppen.getS("Kennung");
                          String s = sGruppe.equals("Grafik") ? "Grafik." + g.TabBegriffe.getS(iPos, "Kennung") : sGruppe.equals("Panel") ? g.TabBegriffe.getS(iPos, "Kennung") : sGruppe;
                          //g.testInfo("   "+s+"."+g.TabBegriffe.getS(iPos,"DefBezeichnung"));
                          if (/*Static.bND &&*/ s.equals("Web"))
                        	  ;//g.fixtestError("Web bei ND nicht dargestellt:"+g.TabBegriffe.getS(iPos, "DefBezeichnung"));
                          else if (s.equals("Applikation") || s.equals("Button") || s.equals("Gruppe") || s.equals("Abfrage") || s.equals("Label") || s.equals("Bild") ||
                              s.equals("Frame") || s.equals("frei") || s.equals("Checkbox") || s.equals("Radiobutton")/* || s.equals("Planung")*/ || s.equals("Web"))
                            Fuelle(sVorgaenger, Pnl, iAIC, iPos);
                          else
                            Laden_Rekursion(TabDarstellung.getI("AIC_Darstellung"), Fuelle(sVorgaenger, Pnl, iAIC, iPos), s);
                        }
			TabDarstellung.moveNext();
		}
		TabDarstellung.pop();
	}

	public Container Fuelle(String sVorgaenger,Container Pnl,int iAIC,int iPos)
	{
		//g.printInfo("Fülle auf "+sVorgaenger+":"+sGruppe+"/"+g.TabBegriffe.getS("Kennung")+"/"+TabDarstellung.getS("reihenfolge"));
		if (Static.bND && sVorgaenger.startsWith("ToolBar"))
			Pnl.setBackground(Global.ColToolbar);
		else if (iStammtyp>0 && !sVorgaenger.equals("TabControl"))
            Pnl.setBackground(Static.ColEF);
        Container PnlNew = null;
		String sBezeichnung = g.getBegBez2(iPos); //g.getBegBez();
//		if(sGruppe.equals("Titel"))
//			g.fixtestError("Titel "+sBezeichnung+"/"+g.TabBegriffe.getI(iPos,"aic"));
		String sKennung = g.TabBegriffe.getS(iPos,"Kennung");
		//String sGruppe = TabQry.getS("gruppe");
		if(sGruppe.equals("Panel") || sGruppe.equals("Grafik"))
		{
			if(sKennung.equals("FlowLayout"))
                          PnlNew = new JPanel(new FlowLayout(TabDarstellung.getI("x"),TabDarstellung.getI("w"),TabDarstellung.getI("h")));
			else if(sKennung.equals("GridLayout"))
			{
                PnlNew = new JPanel(new GridLayout(TabDarstellung.getI("x"),TabDarstellung.getI("y"),TabDarstellung.getI("w"),TabDarstellung.getI("h")));
                if (sVorgaenger.startsWith("ToolBar"))
                {
//                	g.fixtestError("Umfärben von "+sKennung+" da Vorgänger="+sVorgaenger);
                	PnlNew.setBackground(Static.bND ? Global.ColToolbar:Static.ColEF);
                }
			}
            else if(sKennung.startsWith("ToolBar"))
            {
              PnlNew = new JToolBar(sKennung.equals("ToolBarH") ? JToolBar.HORIZONTAL:JToolBar.VERTICAL);
              if (Static.bND)// && sKennung.equals("ToolBarH"))
            	  PnlNew.setPreferredSize(new java.awt.Dimension(1,g.Mal2() ? 84:48));
              else
            	  PnlNew.setPreferredSize(new java.awt.Dimension(1,g.Mal2() ? 80:40));
              ((JToolBar)PnlNew).setOpaque(false);
              ((JToolBar)PnlNew).setFloatable(false);
              if (Static.bND)
              {
            	((JToolBar)PnlNew).setBackground(Global.ColToolbar);
            	((JToolBar)PnlNew).setOpaque(true);
              	//g.fixtestError("Formular-Farbe-Toolbar");
              }
            }
			else if(sKennung.equals("BorderLayout"))
                          PnlNew = new JPanel(new BorderLayout(TabDarstellung.getI("w"),TabDarstellung.getI("h")));
                        else if(sKennung.startsWith("SplitPane"))
                        {
                          PnlNew=new JSplitPane(sKennung.equals("SplitPaneH") ? JSplitPane.HORIZONTAL_SPLIT:JSplitPane.VERTICAL_SPLIT,true);
                          /*((JSplitPane)PnlNew).addKeyListener(new KeyListener()
                                        {
                                          public void keyPressed(KeyEvent e)
                                          {
                                            int iKey=e.getKeyCode();
                                            g.progInfo("JSplitPane.keyPressed:"+iKey);
                                            if (iKey==KeyEvent.VK_LEFT || iKey==KeyEvent.VK_UP)
                                              ((JSplitPane)e.getSource()).setDividerLocation(((JSplitPane)e.getSource()).getDividerLocation()-1);
                                            else if (iKey==KeyEvent.VK_RIGHT || iKey==KeyEvent.VK_DOWN)
                                              ((JSplitPane)e.getSource()).setDividerLocation(((JSplitPane)e.getSource()).getDividerLocation()+1);
                                          }

                                          public void keyReleased(KeyEvent e)
                                          {
                                            g.progInfo("JSplitPane.keyReleased");
                                          }

                                          public void keyTyped(KeyEvent e)
                                          {
                                            g.progInfo("JSplitPane.keyTyped");
                                          }
                                        });*/
                          ((JSplitPane)PnlNew).setOneTouchExpandable(true);
                          //((JSplitPane)PnlNew).setContinuousLayout(true);
                          PnlNew.setName(""+iAIC);
                          if (!Static.bStdSize)
                            g.getSplitPos((JSplitPane)PnlNew,iAIC);
                          ((JSplitPane)PnlNew).addPropertyChangeListener(new PropertyChangeListener() {
                            public void propertyChange(PropertyChangeEvent ev) {
                              if (ev.getPropertyName().equals(JSplitPane.LAST_DIVIDER_LOCATION_PROPERTY))
                              {
                                g.setSplitPos(Sort.geti(((JSplitPane)ev.getSource()).getName()),((JSplitPane)ev.getSource()).getDividerLocation());
                              }
                            }});
                          int iX = TabDarstellung.getI("x");
                          ((JSplitPane)PnlNew).setResizeWeight(iX>9 ? 0.99:iX/10.0);
                        }
                        else if(sKennung.equals("ScrollPane"))
                          if((iBits&cstHGB)>0)
                            PnlNew = new JPanel(null);
                          else
                          {
                            PnlNew = new JScrollPane();
                            ((JScrollPane)PnlNew).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                          }
                        else if(sKennung.equals("EmptyBorder"))
                        {
                          PnlNew = new JScrollPane();
                          int iX = TabDarstellung.getI("x");
                          int iY = TabDarstellung.getI("y");
                          int iH = TabDarstellung.getI("h");
                          int iW = TabDarstellung.getI("w");
                          ((JScrollPane)PnlNew).setBorder(new EmptyBorder(new Insets(iH+iY,iW+iX,iH,iW)));
                        }
			else if(sKennung.equals("GroupBox"))
			{
				PnlNew = new GroupBox();
				g.setSchrift2(iPos,PnlNew,null);
			}
            else if(sKennung.equals("GroupBox2"))
            {
              PnlNew = new GroupBox();
              if (Static.bND && sVorgaenger.startsWith("ToolBar"))
            	PnlNew.setBackground(Global.ColToolbar);
            }
			else if(sKennung.equals("TabControl"))
			{
				PnlNew = new JTabbedPane();
                                VecTC.addElement((JTabbedPane)PnlNew);
				g.setSchrift2(iPos,PnlNew,null);
			}
			else if(sKennung.equals("JCOutliner") || sKennung.equals("JCTable") || sKennung.equals("EFGroup") || sGruppe.equals("Grafik"))
			{
				PnlNew = new JPanel(new BorderLayout());
                                if (TabDarstellung.getI("w")>0 || TabDarstellung.getI("h")>0)
                                  ((JPanel)PnlNew).setPreferredSize(new java.awt.Dimension(TabDarstellung.getI("w"),TabDarstellung.getI("h")));
                                  //PnlNew.getPreferredSize().setSize(new java.awt.Dimension(TabDarstellung.getI("w"),TabDarstellung.getI("h")));
//                if (Static.bND)             
//                	((JPanel)PnlNew).setBorder(new MatteBorder(2, 2, 0, 0, Color.PINK));
				g.setSchrift2(iPos,PnlNew,null);
			}
		}
		else if(sGruppe.equals("Titel"))
		{
			PnlNew = new JPanel(new GridLayout());
		}
		else if(sGruppe.equals("Radiobutton"))
		{
			ButtonGroup RadGroup;
			Integer iID=TabDarstellung.getInt("x");
			if(TabRadio.posInhalt("ID",iID))
				RadGroup = (ButtonGroup)TabRadio.getInhalt("ButtonGroup");
			else
			{
				RadGroup=new ButtonGroup();
				TabRadio.addInhalt("ID",iID);
				TabRadio.addInhalt("ButtonGroup",RadGroup);
			}

			//Tabellenspeicher TabFrei = getTab("Radiobutton");
			PnlNew = g.getRadiobuttonS(iPos,true);//TabDarstellung.getI("AIC_Begriff"));
                        addDefEvent((JComponent)PnlNew);
			RadGroup.add((JRadioButton) PnlNew);
                        addTabFrei("Radiobutton",sKennung,PnlNew);
			//TabFrei.addInhalt("Kennung",sKennung);
			//TabFrei.addInhalt("Komponente",(JRadioButton)PnlNew);
                        //TabFrei.addInhalt("use",null);
		}
		else if(sGruppe.equals("Checkbox"))
		{
			//Tabellenspeicher TabFrei = getTab("Checkbox");
			PnlNew = g.getCheckboxS(iPos,true);
            addDefEvent((JComponent)PnlNew);
            addTabFrei("Checkbox",sKennung,PnlNew);
			//TabFrei.addInhalt("Kennung",sKennung);
			//TabFrei.addInhalt("Komponente",(AUCheckBox)PnlNew);
		}
                else if(sGruppe.equals("Modell") && g.isTod(TabDarstellung.getInhalt("AIC_Begriff")))
                  g.testInfo("Modell entfernt, da Tod:"+g.TabBegriffe.getBezeichnungS(TabDarstellung.getI("AIC_Begriff")));
		else if(sGruppe.equals("Frame") || sGruppe.equals("Applikation") || sGruppe.equals("Modell") || sGruppe.equals("Druck")
                        || sGruppe.equals("Formularmenge") || sGruppe.equals("Button") || sGruppe.equals("Web"))
		{
			if (sVorgaenger.equals("JCOutliner") || sVorgaenger.equals("JCTable") || sVorgaenger.equals("Planung"))
			  addSub(Pnl,TabDarstellung.getI("AIC_Begriff"),sGruppe);
			else
                        {
                          boolean bTb=sVorgaenger.startsWith("ToolBar");
                          int iReihe=TabDarstellung.getI("Reihenfolge");
                          if (bTb && iReihe>iReiheOld+1)
                            ((JToolBar)Pnl).addSeparator();
                          iReiheOld=iReihe;
                          PnlNew = newButton(sGruppe,bTb,TabDarstellung.getI("y"));
                          if (sGruppe.equals("Applikation") && !g.HS())
                            PnlNew.setEnabled(false);
                        }
		}
		else if(sGruppe.equals("Gruppe") || sGruppe.equals("Abfrage"))
		{
			//PnlNew = erzeuge_Gruppe(TabQry.getI("AIC_Begriff"));   // 24.1.2000 über EingabeFormular

			//PnlNew.setBackground(Color.cyan);
			//((JPanel)PnlNew).add(new JLabel(sBezeichnung));

			if(sVorgaenger.equals("JCOutliner") || sVorgaenger.equals("JCTable") || sVorgaenger.equals("EFGroup") || sVorgaenger.startsWith("Grafik."))
			{
				if(sGruppe.equals("Abfrage"))
				{
					//Tabellenspeicher TabOutline = getTab(sVorgaenger.startsWith("Grafik.")?"Grafik":sVorgaenger);
					int iAbfBegriff=TabDarstellung.getI("AIC_Begriff");
//					int iBew=g.TabBegriffe.getI_AIC("Erf", iAbfBegriff);
//					if (iBew>0 && !g.allowBew(iBew))
//						g.fixtestError("Bewegungstyp "+g.TabErfassungstypen.getBezeichnungS(iBew)+" ist verboten"); 
//					else 
						if (sVorgaenger.startsWith("Grafik."))
					{
						java.util.Vector<Object> Vec=new java.util.Vector<Object>();
						Vec.addElement(sVorgaenger.substring(7));
						Vec.addElement(new Integer(iAbfBegriff));
						//TabOutline.addInhalt("Kennung",Vec);
                                                addTabFrei("Grafik",Vec,Pnl);
					}
					else if (sVorgaenger.equals("JCOutliner") || sVorgaenger.equals("JCTable"))
                                          addAbf(iAIC,Pnl,iAbfBegriff,sVorgaenger,TabDarstellung.getI("h"),TabDarstellung.getI("x"));
                                        else
                                          addTabFrei(sVorgaenger,new Integer(iAbfBegriff),Pnl,TabDarstellung.getI("X"),TabDarstellung.getI("Y"));
					//TabOutline.addInhalt("Kennung",iAbfBegriff);
					g.setSchrift2(iPos,Pnl,null);
                                        Pnl.getFont();
                                        //g.progInfo("Schrift von "+iAbfBegriff+"->"+Pnl.getFont());
					//TabOutline.addInhalt("Komponente",Pnl);
				}
				else
					g.printError("Formular.Fuelle: "+sVorgaenger+" mit "+sGruppe+" wird nicht mehr unterstützt!",iBegriff);
				//new JCOutlinerNode(sBezeichnung,(JCOutlinerFolderNode)((JCOutliner)Pnl).getRootNode());
			}
			else
			{
				//Tabellenspeicher TabGruppe = getTab("Gruppe");
				PnlNew = new JPanel(new GridLayout());//1,0,2,0));
				//g.fixtestError("GridLayout für "+sGruppe+"."+sKennung);
				g.setSchrift2(iPos,PnlNew,null);
                                addTabFrei("Gruppe",TabDarstellung.getS("AIC_Begriff"),PnlNew,TabDarstellung.getI("X"),TabDarstellung.getI("Y"));
                                if (TabDarstellung.getI("w")>0 || TabDarstellung.getI("h")>0)
                                  ((JPanel)PnlNew).setPreferredSize(new java.awt.Dimension(TabDarstellung.getI("w")*iFF/100,TabDarstellung.getI("h")*iFF/100));
				//TabGruppe.addInhalt("Kennung",TabDarstellung.getS("AIC_Begriff"));
				//TabGruppe.addInhalt("Komponente",PnlNew);
			}
			//TabGruppe.addInhalt("Tabelle",null);
		}
		/*else if(sGruppe.equals("Abfrage"))
		{
			Tabellenspeicher TabOutline = getTab(sVorgaenger);
			TabOutline.addInhalt("Kennung",TabDarstellung.getS("AIC_Begriff"));
			g.setSchrift(TabDarstellung.getI("AIC_Begriff"),Pnl);
			TabOutline.addInhalt("Komponente",Pnl);
		}*/
		else if(sGruppe.equals("frei"))
		{
			//Tabellenspeicher TabFrei = getTab("frei");
		  //g.testInfo("          frei:"+sKennung);
			PnlNew = new JPanel(TabDarstellung.getI("x")>0 || TabDarstellung.getI("y")>0 ? new GridLayout(TabDarstellung.getI("x"),TabDarstellung.getI("y")):new GridLayout());
                        if (TabDarstellung.getI("w")>0 || TabDarstellung.getI("h")>0)
                          ((JPanel)PnlNew).setPreferredSize(new java.awt.Dimension(TabDarstellung.getI("w")*iFF/100,TabDarstellung.getI("h")*iFF/100));
			g.setSchrift2(iPos,PnlNew,null);
			//PnlNew.setBackground(Color.yellow);
			//((JPanel)PnlNew).add(new JLabel(sBezeichnung));
                        if (iStammtyp>0)
                          PnlNew.setBackground(Static.ColEF);
                        if (Pnl instanceof JToolBar && sKennung.startsWith("Zeitraum"))
                        {
                          if (g.WorkflowZR() || g.Def() && sKennung.equals("Zeitraum2"))
                          {
                            ButtonGroup RadGroup=new ButtonGroup();
                            JToggleButton BtnTag=g.getTButton("Tag",null);Pnl.add(BtnTag);addTabFrei("Button","Tag",BtnTag);RadGroup.add(BtnTag);
                            JToggleButton BtnWoche=g.getTButton("Woche",null);Pnl.add(BtnWoche);addTabFrei("Button","Woche",BtnWoche);RadGroup.add(BtnWoche);
                            JToggleButton BtnMonat=g.getTButton("Monat",null);Pnl.add(BtnMonat);addTabFrei("Button","Monat",BtnMonat);RadGroup.add(BtnMonat);
                            String sZeitart=g.getZA(0);
                            if (sZeitart.equals("Tag"))
                              BtnTag.setSelected(true);
                            else if (sZeitart.equals("Woche"))
                              BtnWoche.setSelected(true);
                            else if (sZeitart.equals("Monat"))
                              BtnMonat.setSelected(true);
                            JButton Btn=getButton2("Tb_ZRminus",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","ZRminus",Btn);
                            Btn=getButton2("Jetzt",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","Jetzt",Btn);
                            Btn=getButton2("Tb_ZRplus",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","ZRplus",Btn);
                            if ((iBits & cstZR)==0)
                            {
                              Btn=getButton2("Tb_ZRminus2",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","ZRminus2",Btn);
                              Btn=getButton2("Tb_ZRplus2",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","ZRplus2",Btn);
                            }
                            ((JToolBar)Pnl).addSeparator();
                            Btn=getButton2("Tb_Refresh",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","Refresh",Btn);
                          }
                          else
                          {
                            JButton Btn=getButton2("Tb_Zeitraum",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","Zeitraum",Btn);
                            Btn=getButton2("Tb_ZRminus",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","ZRminus",Btn);
                            Btn=getButton2("Jetzt",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","Jetzt",Btn);
                            Btn=getButton2("Tb_ZRplus",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","ZRplus",Btn);
                            ((JToolBar)Pnl).addSeparator();
                            Btn=getButton2("Tb_Refresh",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","Refresh",Btn);
                            Btn=getButton2("Tb_Suche",null,null,"Button");Pnl.add(Btn);addTabFrei("Button","Tb_Suche",Btn);
                          }
                          ((JPanel)PnlNew).setMaximumSize(new java.awt.Dimension(5,5));
                          if (Static.bND)
                        	  PnlNew.setBackground(Global.ColToolbar);
                          if (!sKennung.equals("Zeitraum3"))
                          {
                            ((JToolBar)Pnl).addSeparator();
                            ButtonGroup RadGroup = new ButtonGroup();
                            JToggleButton BtnAD = g.getTButton("alle_Daten", null);
                            //BtnAD.setFocusPainted(false);
                            Pnl.add(BtnAD);
                            addTabFrei("Button", "alle_Daten", BtnAD);
                            RadGroup.add(BtnAD);

                            JToggleButton BtnNV = g.getTButton("nur_Vector", null);
                            //BtnNV.setFocusPainted(false);
                            Pnl.add(BtnNV);
                            addTabFrei("Button", "nur_Vector", BtnNV);
                            RadGroup.add(BtnNV);
                            if (iStammtyp==g.iSttANR && iBewegungstyp==0)
                            {
                              JToggleButton BtnNF = g.getTButton("nur_Firma", null);
                              //BtnNF.setFocusPainted(false);
                              Pnl.add(BtnNF);
                              addTabFrei("Button", "nur_Firma", BtnNF);
                              RadGroup.add(BtnNF);
                            }
                          }
                        }
                        else
                        	addTabFrei("frei",sKennung,PnlNew);
			//TabFrei.addInhalt("Kennung",sKennung);
			//TabFrei.addInhalt("Komponente",(JPanel)PnlNew);
		}
		else if(sGruppe.equals("Label"))
		{
			PnlNew = g.getLabel2(iPos/*TabDarstellung.getI("AIC_Begriff")*/,(iBits&cstHGB)>0? SwingConstants.LEFT:TabDarstellung.getI("x"));
//			PnlNew.setName("L"+g.TabBegriffe.getI(iPos, "Aic"));
			g.setTooltip(g.TabBegriffe.getM(iPos,"Tooltip"), (JLabel)PnlNew);
//			g.fixtestError("Label:"+((JLabel)PnlNew).getText());
			addDefEvent((JComponent)PnlNew);
            addTabFrei("Label",sKennung,PnlNew);	
		}
		else if(sGruppe.equals("Bild"))
			PnlNew = g.getBild(iPos/*TabDarstellung.getI("AIC_Begriff")*/,(iBits&cstHGB)>0? SwingConstants.LEFT:TabDarstellung.getI("x"),iFF);
		else if(sGruppe.equals("Planung"))
		{
			//Tabellenspeicher TabPlanung = getTab("Planung");
			PnlNew = new JPanel(new GridLayout());
			g.setSchrift2(iPos,PnlNew,null);
			//PnlNew.setBackground(Color.yellow);
			//((JPanel)PnlNew).add(new JLabel(sBezeichnung));
                        addTabFrei("Planung",TabDarstellung.getInhalt("AIC_Begriff"),PnlNew,iAIC);
			//TabPlanung.addInhalt("Kennung",TabDarstellung.getI("AIC_Begriff"));
			//TabPlanung.addInhalt("Komponente",(JPanel)PnlNew);
		}
               
		if (PnlNew instanceof JLabel && (iBits&cstHGB)>0 && !sGruppe.equals("Bild"))
			((JLabel)PnlNew).setBounds(TabDarstellung.getI("x"),TabDarstellung.getI("y"),TabDarstellung.getI("w")*iFF/100,TabDarstellung.getI("h")*iFF/100);
        if(Static.bND && (sGruppe.equals("Gruppe") || sKennung.equals("EFGroup")))
			((JPanel)PnlNew).setBorder(new MatteBorder(2, 2, 0, 5,Static.bInfoExcept ? Color.PINK:Static.ColEF));
        		
		if(sVorgaenger.equals("Frame"))
		{
                  Pnl.add("Center",PnlNew);
		}
                else if(sVorgaenger.equals("ScrollPane") && (iBits&cstHGB)>0 && sGruppe.equals("Bild"))
                {
                  //g.fixInfo(sVorgaenger+"/"+sGruppe+"/"+sKennung);
                  thisJFrame().getContentPane().add(PnlNew);
                  thisJFrame().pack();
                }
                else if(sVorgaenger.equals("ScrollPane") && (iBits&cstHGB)==0 || sVorgaenger.equals("EmptyBorder"))
                {
                  //JScrollPane PnlSP = new JScrollPane(PnlNew);
                  //PnlSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                  //Pnl.add(PnlSP);
                  ((JScrollPane)Pnl).setViewportView(PnlNew);
                }
                else if(sVorgaenger.startsWith("SplitPane"))
                {
                  PnlNew.setMinimumSize(new java.awt.Dimension(100,50));
                  Pnl.add(PnlNew);
                }
		else if(sVorgaenger.equals("BorderLayout"))
		{
			//g.TabBegriffe.posInhalt("AIC",new Integer(TabDarstellung.getI("reihenfolge")));
			//g.printInfo(g.TabBegriffe.getS("Kennung"));
                        String sKen=g.TabBegriffe.getKennung(TabDarstellung.getI("reihenfolge"));
                        if (sKen.startsWith("Aic="))
                          g.printError("Begriff mit Aic="+TabDarstellung.getI("reihenfolge")+" nicht gefunden",iBegriff);
                        else
                          Pnl.add(sKen,PnlNew);
		}
		else if(sVorgaenger.equals("TabControl"))
		{
                  if (g.TitelBerechtigt(iPos))
                  {
                    //String sBez=sBezeichnung;
                      //sBez="<html><del>"+sBez+"</del></html>";
                    Pnl.add(sBezeichnung, PnlNew);
                    if (Global.bInfoJeder && !g.isJederS(iPos))
                      ((JTabbedPane)Pnl).setBackgroundAt(((JTabbedPane)Pnl).indexOfTab(sBezeichnung),Global.ColEF_Bez2);
                    g.setSchrift2(iPos, Pnl, Global.fontTitel);
                  }
                  else
                    g.fixInfo("ausblenden:"+sBezeichnung);
		}
		else if(sVorgaenger.equals("GroupBox"))
		{
                  if (g.TitelBerechtigt(iPos))
                  {
                    ((GroupBox)Pnl).setText(sGruppe.equals("Titel") || sGruppe.equals("Gruppe") ? sBezeichnung : null, Global.fontTitel);
                    g.setSchrift2(iPos, Pnl, Global.fontTitel);
                    if (Global.bInfoJeder && !g.isJederS(iPos))
                      Pnl.setBackground(Global.ColEF_Bez2);
                    Pnl.add(PnlNew);
                  }
                  else
                    g.fixInfo("ausblenden:"+sBezeichnung);
		}
                else if(sVorgaenger.equals("GroupBox2"))
                {
                  ((GroupBox) Pnl).setText(null,null);
                  Pnl.add(PnlNew);
                }
		else if(!sVorgaenger.equals("JCOutliner") && !sVorgaenger.equals("JCTable") && !sVorgaenger.equals("Planung") && !sVorgaenger.equals("EFGroup") && !sVorgaenger.startsWith("Grafik."))
		{
			// Fülle Seperator
			int i=1;
			if(!TabDarstellung.bof())
			{
				int iAic=TabDarstellung.getI("DAR_AIC_DARSTELLUNG");
				TabDarstellung.movePrevious();
				if (TabDarstellung.getI("DAR_AIC_DARSTELLUNG") == iAic)
					i=TabDarstellung.getI("Reihenfolge")+1;
				TabDarstellung.moveNext();
			}
			for(;i<TabDarstellung.getI("Reihenfolge");i++)
				  Pnl.add(new JLabel(""));
                        //g.fixInfo("Rest:"+sVorgaenger+"/"+sGruppe);
                        if((iBits&cstHGB)>0)
                          thisJFrame().getContentPane().add(PnlNew);
                        else if (!sGruppe.equals("Modell") || !g.isTod(TabDarstellung.getInhalt("AIC_Begriff")))
                            Pnl.add(PnlNew);

		}

		return (PnlNew);

	}

        public JFrame thisJFrame()
        {
          return (JFrame)thisFrame;
        }

        private void addAbf(int iDarstellung,Object Pnl,int iAbfBegriff,String sArt,int iH,int iSpalten)
        {
          int i=g.getAbfrage(iDarstellung);
          boolean bAktiv=!TabAbf.posInhalt("Pnl",Pnl);
          if (i>0)
            bAktiv=i==iAbfBegriff;
          TabAbf.newLine();
          TabAbf.setInhalt("Zeile",iDarstellung);
          TabAbf.setInhalt("Pnl",Pnl);
          TabAbf.setInhalt("Aic",iAbfBegriff);
          TabAbf.setInhalt("aktiv",new Boolean(bAktiv));
          TabAbf.setInhalt("Art",sArt);
          TabAbf.setInhalt("h",iH);
          TabAbf.setInhalt("Spalten",iSpalten==0?1:iSpalten);
        }

        public int getAnzAbf(Object Pnl)
        {
          int iRet=0;
          for (int i=0;i<TabAbf.size();i++)
            if (TabAbf.getInhalt("Pnl",i)==Pnl)
              iRet++;
          return iRet;
        }

        public int PnlToAic(Object Pnl)
        {
          int i=TabAbf.getPos("Pnl", Pnl);
          if (i<0)
            g.printError("Formular.PnlToAic: Pnl nicht gefunden",iBegriff);
          return i<0 ? -1:TabAbf.getI(i,"Zeile");
        }

        public int FirstAbfrage(int iZeile)
        {
          int i=TabAbf.getPos("Zeile", iZeile);
          if (i<0)
            g.printError("Formular.FirstAbfrage von Zeile "+iZeile+" nicht gefunden",iBegriff);
          return i<0 ? -1:TabAbf.getI(i,"Aic");
        }

        public Vector<Integer> getVec(String sArt,Object Pnl)
        {
        	if (TabSub.posInhalt("Pnl",Pnl))
        	{
        		Vector<Integer> Vec=new Vector<Integer>();
        		for (TabSub.moveFirst();!TabSub.eof();TabSub.moveNext())
        			if (TabSub.getInhalt("Pnl").equals(Pnl) && TabSub.getS("Art").equals(sArt) && g.BerechtigungS(g.TabBegriffe.getPos("Aic",TabSub.getInt("Aic"))))
        				Vec.addElement(TabSub.getInt("Aic"));
                        //g.fixtestInfo(sArt+":"+Vec);
        		return Vec;
        	}
        	else
        	  return null;
        }

        /*public Vector getVecFom(Object Pnl)
        {
        	if (TabSub.posInhalt("Pnl",Pnl))
        	{
        		Vector<Integer> Vec=new Vector<Integer>();
        		for (TabSub.moveFirst();!TabSub.eof();TabSub.moveNext())
        			if (TabSub.getInhalt("Pnl").equals(Pnl) && TabSub.getS("Art").equals("Frame"))
        				Vec.addElement(TabSub.getInt("Aic"));
        		return Vec;
        	}
        	else
        	  return null;
        }*/

        private void addSub(Object Pnl,int iBegriff,String sArt)
        {
          //boolean bAktiv=!TabAbf.posInhalt("Pnl",Pnl);
          //g.fixtestInfo("addSub:"+iBegriff+"/"+sArt+"/"+Pnl);
          TabSub.newLine();
          TabSub.setInhalt("Pnl",Pnl);
          TabSub.setInhalt("Aic",iBegriff);
          //TabAbf.setInhalt("aktiv",new Boolean(bAktiv));
          TabSub.setInhalt("Art",sArt);
        }

        public static void sort(Tabellenspeicher Tab)
        {
          if (Tab.min("use")<256*999)
            Tab.sort("use",true);
        }

        private void addTabFrei(String sTyp,Object ObjKennung,Object Obj,int iX,int iY)
        {
          Tabellenspeicher TabFrei = getTab(sTyp);
          TabFrei.addInhalt("Kennung",ObjKennung);
          TabFrei.addInhalt("Komponente",Obj);
          TabFrei.addInhalt("use",(iY==0?999:iY)*256+iX);
        }

        private void addTabFrei(String sTyp,Object ObjKennung,Object Obj)
        {
          Tabellenspeicher TabFrei = getTab(sTyp);
          TabFrei.addInhalt("Kennung",ObjKennung);
          TabFrei.addInhalt("Komponente",Obj);
          TabFrei.addInhalt("use",null);
        }

        private void addTabFrei(String sTyp,Object ObjKennung,Object Obj,int iDarstellung)
        {
          Tabellenspeicher TabFrei = getTab(sTyp);
          TabFrei.addInhalt("Kennung",ObjKennung);
          TabFrei.addInhalt("Komponente",Obj);
          TabFrei.addInhalt("use",iDarstellung);
        }

	private Container newButton(String sGruppe,boolean bTb,int iY)
	{
	  int iPos=g.TabBegriffe.getPos("Aic",TabDarstellung.getI("AIC_Begriff"));
          //if (bTb)
          //  g.progInfo("newButton "+bButton+"/"+bTb+":"+iPos+"/"+g.TabBegriffe.getS(iPos,"DefBezeichnung"));
	  Container PnlNew = bTb ? iY>0? g.getTButton(g.TabBegriffe.getS(iPos,"Kennung"),null):g.getButton2(iPos,null,null):
              g.getButtonS(iPos,(iBits&cstHGB)>0? SwingConstants.LEFT:TabDarstellung.getI("x"),true);
          if (bTb && iY>0)
          {
            ButtonGroup RadGroup;
            if(TabRadio.posInhalt("ID",iY))
              RadGroup = (ButtonGroup)TabRadio.getInhalt("ButtonGroup");
            else
            {
              RadGroup = new ButtonGroup();
              TabRadio.addInhalt("ID",iY);
              TabRadio.addInhalt("ButtonGroup",RadGroup);
            }
            RadGroup.add((JToggleButton)PnlNew);
          }
		//Container PnlNew = g.getButton(TabDarstellung.getI("AIC_Begriff"),(iBits&cstHGB)>0? SwingConstants.LEFT:TabDarstellung.getI("x"));
                if ((iBits&cstHGB)>0)
                {
                  ((JButton)PnlNew).setBounds(TabDarstellung.getI("x")*iFF/100, TabDarstellung.getI("y")*iFF/100, TabDarstellung.getI("w")*iFF/100, TabDarstellung.getI("h")*iFF/100);
                  //((JButton)PnlNew).setOpaque(false);
                  ((JButton)PnlNew).setContentAreaFilled(false);
                  ((JButton)PnlNew).setVerticalAlignment(SwingConstants.BOTTOM);
                }
                else if (TabDarstellung.getI("w")>0 || TabDarstellung.getI("h")>0)
                  ((JComponent)PnlNew).setPreferredSize(new java.awt.Dimension(TabDarstellung.getI("w")*iFF/100,TabDarstellung.getI("h")*iFF/100));
		String sBegriff = g.TabBegriffe.getS(iPos,"Kennung");
                int iAicMom=TabDarstellung.getI("AIC_Begriff");

		if(!sGruppe.equals("Button") && !sGruppe.equals("Web") || sBegriff.equals("Definition") || iTyp==kein && sBegriff.equals("Abfrage2")/* || sBegriff.equals("TestSS")*/)
		{
                  boolean b=true;
                  if (iStammtyp==0)
                    addDefEvent((JButton)PnlNew);
                  //g.TabBegriffe.push();
                  Vector<Integer> Vec=new Vector<Integer>();
                  if (sGruppe.equals("Formularmenge"))
                  {
                    // g.fixtestError("Formularmenge "+g.TabBegriffe.getS(iPos,"Kennung")+":");
                    //if (iStammtyp==0 || !SQL.getString(g,"select distinct g.kennung from begriff_zuordnung z join begriff b on z.beg_aic_begriff=b.aic_begriff"+ // 9.9.2011 entfernt für Formular-Menge aus EF
                    //    " join begriffgruppe g on b.aic_begriffgruppe=g.aic_begriffgruppe where z.aic_begriff="+iAicMom).equals("Frame"))  // für individuellen Formularaufruf (z.B. Entgelte, fixe Lohnart ...) aus EF nötig
                    //{
                      Tabellenspeicher Tab = new Tabellenspeicher(g,
                          "select b.aic_begriff,b.aic_begriffgruppe,b.aic_stammtyp,b.bits from begriff b join begriff_zuordnung z on b.aic_begriff=z.beg_aic_begriff where z.aic_begriff=" +
                          iAicMom + " order by z.reihenfolge", true, "Menge" + iAicMom);
                      b = false;
                      for (Tab.moveFirst();!Tab.eof(); Tab.moveNext())
                      {
                        iAicMom = Tab.getI("aic_begriff");
                        iPos=g.TabBegriffe.getPos("Aic",iAicMom);
                        //g.TabBegriffe.posInhalt("Aic", iAicMom);
                        sBegriff = g.TabBegriffe.getS(iPos,"Kennung");
                        sGruppe = g.TabBegriffgruppen.getKennung(Tab.getI("aic_begriffgruppe"));
                        if (sGruppe.equals("Applikation") && !g.HS())
                          b=false;
                        else
                          b = g.BerechtigungS(iPos) && !(iTyp == kein && Tab.getI("aic_stammtyp") > 0 && g.getStamm() == 0 && (Tab.getL("bits") & cstOhneStamm) == 0);
                        // g.fixtestError("xx    Menge-"+sGruppe+"-"+sBegriff+"="+b);
                        if (b)
                          Vec.addElement(new Integer(iAicMom));
                      }
                      b=Vec.size()>0;
                    //}
                    //g.fixtestInfo("Formularmenge:"+Vec+"->"+b);
                  }
                  if (Vec.size()==1)
                  {
                    iAicMom = Sort.geti(Vec, 0);
                    iPos = g.TabBegriffe.getPos("Aic", Sort.geti(Vec, 0));
                    sBegriff = g.TabBegriffe.getS(iPos,"Kennung");
                  }
                  //g.printInfo(2,sGruppe+"/"+sBegriff+"->"+iTyp+"/"+g.TabBegriffe.getI(iPos,"Stt")+"/"+g.getStamm()+"/"+(g.TabBegriffe.getI(iPos,"bits")&cstOhneStamm));
//                  if (iTyp == kein && g.isLogAll())
//                    PnlNew.setEnabled(true);
			if(((JButton)PnlNew).isEnabled() && b && !(iTyp == kein && g.TabBegriffe.getI(iPos,"Stt")>0 && g.getStamm()==0 && (g.TabBegriffe.getL(iPos,"bits")&cstOhneStamm)==0))
			{
                          //if (Vec.size()>1)
                          //  g.progInfo(sBegriff+": Vec="+Sort.gets2(Vec));
				long lBBits=g.TabBegriffe.getL(iPos,"bits");
				//g.fixtestInfo(sGruppe+"/"+sBegriff+":"+lBBits+"/"+((lBBits&cstJavaFX)>0));
				TabFormular.addInhalt("Aic",iAicMom);				
				TabFormular.addInhalt("Kennung",sBegriff);
				TabFormular.addInhalt("Formular",sGruppe.equals("Druck") ? lBBits:null);
                                //iFormulartyp= g.TabBegriffe.getI("Typ");
				TabFormular.addInhalt("Gruppe",g.TabBegriffe.getI(iPos,"Typ")==g.SystemFormular()?"System":!sGruppe.equals("Druck") && !sGruppe.equals("Modell") && g.TabBegriffe.getI(iPos,"Stt")>0 ?
						(g.TabBegriffe.getL(iPos,"bits")&cstOhneStamm)==0?"Eingabe":"Eingabe2":sGruppe);
                                TabFormular.addInhalt("Comp",Vec.size()>1 ? Vec:null);
//				if (sGruppe.equals("Frame") && (lBBits&cstJavaFX)>0)
//				{
//					//g.fixtestError("!!!   betrete JavaFX-Welt:");
//					//JFXPanel PnlNew2=new JFXPanel();
//					Panel PnlNew2=new Panel();
//					TabFormular.addInhalt("Button",PnlNew2);
//					//Platform.setImplicitExit(false);
////					final int iAicF=iAicMom;
//////					final int iX=TabDarstellung.getI("x");
//////					final int iY2=TabDarstellung.getI("y");
//////					final int iW=TabDarstellung.getI("w");
//////					final int iH=TabDarstellung.getI("h");
////					if ((iBits&cstHGB)>0)
////					{
////						PnlNew2.setLocation(TabDarstellung.getI("x"), TabDarstellung.getI("y"));
////						PnlNew2.setSize(TabDarstellung.getI("w"), TabDarstellung.getI("h"));	
////					}
//					//g.fixtestError("PnlNew2="+PnlNew2);
//					//Platform.runLater(() -> initFX(PnlNew2,iAicF));	
//					return PnlNew2;
//					//return new Panel();
//				}
//				else
				{
					if (sGruppe.equals("Modell") && !Global.bInfoJeder)
                    {
                      int iBits=g.TabBegriffe.getI(iPos, "bits");
                      if ((iBits & Global.cstThread) > 0)
                        ((JButton)PnlNew).setForeground(Global.ColThread);
                      if (AClient.UseAServer(iBits))
                        ((JButton)PnlNew).setFont(Global.fontAServer);
                    }
					TabFormular.addInhalt("Button",PnlNew);
				}
                                
			}
			else if((iBits&cstHGB)>0)
                        //  ((JButton)PnlNew).setText("");
                        {
				((JButton)PnlNew).setEnabled(false);
				((JButton)PnlNew).setBorderPainted(false);
			}
                        //g.TabBegriffe.pop();
		}
		else
		{
                  addTabFrei(sGruppe,sBegriff,PnlNew);
                  addDefEvent((JComponent)PnlNew);

			//Tabellenspeicher TabButton = getTab("Button");
			//TabButton.addInhalt("Kennung",sBegriff);
			//TabButton.addInhalt("Komponente",(JButton)PnlNew);
		}

		//TabFormular.showGrid();
		return (PnlNew);
	}
	
//	private void initFX(JFXPanel fxPanel,int iAic)
//	{
//		//g.fixtestInfo("initFX mit "+iAic);
//		Scene scene=null;
//		int iPos=g.TabBegriffe.getPos("Aic",iAic);
//		if (iPos>=0)
//		{
//			String sBez=g.getBegBez3(iPos);
//			Button Btn=new Button("FX:"+sBez);
//			Btn.setId(""+iAic);
//			//Btn.setDisable(false);
//			//Btn.toFront();
//			//g.fixtestError("!!!   mit Knopf ("+sBez+",ID="+Btn.getId()+") !");
//			VecBtn.addElement(Btn);
//			//g.fixtestInfo("Btn "+sBez+" von Pos "+iPos+" mit ID="+Btn.getId());
//			scene=new Scene(Btn);
//		}
//		else
//			scene=new Scene(new Label("Beg"+iAic));
//		fxPanel.setScene(scene);
//	}

        public Formular(Global rg,Window Fom)
        {
          g=rg;
          thisFrame=Fom;
        }

        public JButton getButtonM(String s)
        {
          JButton Btn=g.getButtonS(s);
          addDefEvent(Btn);
          return Btn;
        }

        public JButton getButton2(String s)
        {
          JButton Btn=g.getButton2(s);
          addDefEvent(Btn);
          return Btn;
        }
        
        public JButton getButton2(String sKennung,String s,ActionListener AL,String sBG)
        {
        	return getButton2(sKennung,null,s,AL,sBG);
        }

        public JButton getButton2(String sKennungNeu,String sKennung,String s,ActionListener AL,String sBG)
        {
          JButton Btn=g.getButton2(sKennungNeu,sKennung,s,AL,sBG);
          addDefEvent(Btn);
          return Btn;
        }

        public JButton getFormM(String s)
        {
          JButton Btn=g.getFrameS(s);
          addDefEvent(Btn);
          return Btn;
        }

        public AUCheckBox getCheckboxM(String s,boolean b)
        {
          AUCheckBox Cbx=g.getCheckbox(s, b);
          addDefEvent(Cbx);
          return Cbx;
        }

        public JRadioButton getRadiobuttonM(String s)
        {
          JRadioButton Rad=g.getRadiobutton(s);
          addDefEvent(Rad);
          return Rad;
        }
        
        public JLabel getLabelM(String s)
        {
          JLabel Lbl=g.getLabel(s);
          if (Lbl.getName() != null)
        	  addDefEvent(Lbl);
          return Lbl;
        }
        
        public JLabel getLabelR(String s)
        {
          JLabel Lbl=g.getLabelR(s);
          if (Lbl.getName() != null)
        	  addDefEvent(Lbl);
          return Lbl;
        }

        public void ToolTipEdit(JComponent Comp,String s)
        {
          //String s=;
//          g.fixtestError("ToolTipEdit:"+s);
          char c=s==null || s.length()<2 ? 0:s.charAt(0);
          if (c=='B' || c=='C' || c=='R' || c=='L')
          {
                  int iAicB=new Integer(s.substring(1)).intValue();
                  int iPos=g.TabBegriffe.getPos("Aic",iAicB);
                  String sBez=g.TabBegriffe.getS(iPos,"Bezeichnung");
                  String sTooltip=g.TabBegriffe.getM(iPos,"Tooltip");
                  Vector<String> VecMemo=new Vector<String>();
                  VecMemo.addElement(sBez);
                  VecMemo.addElement(sTooltip);
                  new Memo(VecMemo,0,g.getBegriffS("Show","Tooltip")+": "+g.TabBegriffe.getS(iPos,"Kennung"),thisFrame,g);
                  String sTooltipNew=VecMemo.elementAt(1);
                  String sBezNew=VecMemo.elementAt(0);
                  //g.fixInfo(sTooltipNew);
                  if (!sBez.equals(sBezNew))
                  {
                    g.TabBegriffe.setInhalt(iPos,"Bezeichnung",sBezNew.equals("")?g.TabBegriffe.getS(iPos,"DefBezeichnung"):sBezNew);
                    g.exec("delete from bezeichnung where aic_tabellenname="+Global.iTabBegriff+" and aic_sprache="+g.getSprache()+" and aic_fremd="+iAicB);
                    if (!sBezNew.equals(""))
                      g.exec("insert into bezeichnung (aic_tabellenname,aic_sprache,aic_fremd,Bezeichnung) values ("+Global.iTabBegriff+","+g.getSprache()+","+iAicB+",'"+sBezNew+"')");
                    if (c=='B' && (g.TabBegriffe.getB(iPos,"Combo") || g.TabBegriffe.getS(iPos,"BildFile").equals("")) && !(Comp.getParent() instanceof JToolBar))
                      ((JButton)Comp).setText(sBezNew);
                    else if (c=='R')
                      ((JRadioButton)Comp).setText(sBezNew);
                    else if (c=='C')
                      ((AUCheckBox)Comp).setText(sBezNew);
                    else if (c=='L')
                      ((JLabel)Comp).setText(sBezNew);
                  }
                  if (!sTooltip.equals(sTooltipNew))
                  {
                    g.TabBegriffe.setInhalt(iPos,"Tooltip",sTooltipNew);
                    g.setTooltip(sTooltipNew,Global.iTabBegriff,iAicB,0);
                  }
                  g.setTooltip2(sBezNew,sTooltipNew,Comp);
              }
              else if (c=='E' || c=='S')
              {
                int iAicE=new Integer(s.substring(1)).intValue();
                int iTab=g.TabTabellenname.getAic(c=='E' ? "EIGENSCHAFT":"SPALTE");

                int iPos=c=='E' ? g.TabEigenschaften.getPos("Aic",iAicE):-1;
                //String sBez=c=='E' ? g.TabEigenschaften.getS(iPos,"Bezeichnung"):"";
                String sBez= Comp instanceof AUCheckBox ? ((AUCheckBox)Comp).getText():((EF_Eingabe)Comp).getTitel();
                String sTooltip=SQL.getString(g,"select memo2 from tooltip where aic_tabellenname="+iTab+" and aic_sprache="+g.getSprache()+" and aic_fremd="+iAicE);//Comp.getToolTipText();
                if (c=='S' && (sTooltip==null || sTooltip.equals("")))
                {
                  c='E';
                  iTab=g.TabTabellenname.getAic("EIGENSCHAFT");
                  iAicE=SQL.getInteger(g,"select aic_eigenschaft from fixeigenschaft where aic_spalte="+iAicE);
                  sTooltip=SQL.getString(g,"select memo2 from tooltip where aic_tabellenname="+iTab+" and aic_sprache="+g.getSprache()+" and aic_fremd="+iAicE);
                  iPos=g.TabEigenschaften.getPos("Aic",iAicE);
                }
                g.testInfo(c+":"+iAicE+"->"+sTooltip);
                if (sTooltip==null)
                  sTooltip="";
                if (sTooltip.startsWith("<html>"))
                  sTooltip=sTooltip.substring(6,sTooltip.length()-7);
                //String sTooltip=g.TabEigenschaften.getM(iPos,"Tooltip");
                Vector<String> VecMemo=new Vector<String>();
                VecMemo.addElement(sBez);
                VecMemo.addElement(sTooltip);
                new Memo(VecMemo,0,g.getBegriffS("Show","Tooltip")+": "+(iPos<0?"":g.TabEigenschaften.getS(iPos,"Kennung")),thisFrame,g);
                String sTooltipNew=VecMemo.elementAt(1);
                String sBezNew=VecMemo.elementAt(0);
                //g.fixInfo(sTooltipNew);
                if (!sBez.equals(sBezNew))
                {
                  if (c=='E')
                    g.TabEigenschaften.setInhalt(iPos,"Bezeichnung",sBezNew.equals("")?g.TabEigenschaften.getS(iPos,"Kennung"):sBezNew);
                  g.exec("delete from bezeichnung where aic_tabellenname="+iTab+" and aic_sprache="+g.getSprache()+" and aic_fremd="+iAicE);
                  if (!sBezNew.equals(""))
                    g.exec("insert into bezeichnung (aic_tabellenname,aic_sprache,aic_fremd,Bezeichnung) values ("+iTab+","+g.getSprache()+","+iAicE+",'"+sBezNew+"')");
                  /*if (c=='B')
                    ((JButton)Comp).setText(sBezNew);
                  else if (c=='R')
                    ((JRadioButton)Comp).setText(sBezNew);
                  else if (c=='C')
                    ((AUCheckBox)Comp).setText(sBezNew);*/
                  if (Comp instanceof AUCheckBox)
                    ((AUCheckBox)Comp).setText(sBezNew);
                  else
                    ((EF_Eingabe)Comp).setTitel(sBezNew);
                  Comp.repaint();
                }
                if (!sTooltip.equals(sTooltipNew))
                {
                  if (c=='E')
                    g.TabEigenschaften.setInhalt(iPos,"Tooltip",sTooltipNew);
                  g.setTooltip(sTooltipNew,iTab,iAicE,0);
                  g.setTooltip(sTooltipNew,Comp);
                }
              }
        }

  public static void Rule_change(final Global g,JFrame Fom)
{
  //g.progInfo("Rule_change");
  final Text EdtArt=new Text("",21);
  final AUPasswort EdtPW=new AUPasswort();
  final JDialog Dlg=new JDialog(Fom,"Rule_change",true);
  JPanel PnlW=new JPanel(new GridLayout(0,1));
  g.addLabel4(PnlW,"Art");
  g.addLabel4(PnlW,"PW");
  JPanel PnlC=new JPanel(new GridLayout(0,1));
  g.addComp(PnlC,EdtArt);
  g.addComp(PnlC,EdtPW);
  JPanel PnlS=new JPanel(new GridLayout(1,0,2,2));
  JButton BtnOk = g.getButton("Ok");
  JButton BtnAbbruch = g.getButton("Abbruch");
  Dlg.getRootPane().setDefaultButton(BtnOk);
  PnlS.add(BtnOk);
  PnlS.add(BtnAbbruch);
  Dlg.getContentPane().add("West",PnlW);
  Dlg.getContentPane().add("Center",PnlC);
  Dlg.getContentPane().add("South",PnlS);
  BtnOk.addActionListener(new ActionListener()
  {
            public void actionPerformed(ActionEvent ev)
            {
              String s1=EdtArt.getText();
              String s2=new String(EdtPW.getPassword());
              checkNamePW(g,s1,s2);
              //Abfrage.TabSpalten2=null;
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
  Dlg.pack();
  Static.centerComponent(Dlg,Fom);
  Dlg.setVisible(true);
}
  
public static void checkNamePW(Global g,String s1,String s2)
{
	if (s1.equals("Def") && s2.equals("Isi"))
        g.setDef();
	else if (s1.equals("Prog") && s2.equals("K2"))
        g.setProg();
	else if (s1.equals("Verwaltung") && s2.equals("Fox"))
        g.setVerw();
      else if (s1.equals("Spezial") && s2.equals("Profi"))
         g.setSpezial();
      else if (s1.equals("Superuser") && s2.equals("All"))
         g.setSU();
      else if (s1.equals("User") && s2.equals("Manager"))
         g.setUM();
      else if (s1.equals("Normal") && s2.equals("User"))
         g.setNormal();
      else if (s1.equals("Test") && s2.equals("test"))
      {
        g.fixInfo("Test");
        Static.bTest = true;
      }
      else if (s1.equals("Test") && s2.equals("weg"))
      {
        g.fixInfo("kein Test");
        Static.bTest = false;
      }
      else if (s1.equals("TestDB") && s2.equals("set"))
      {
        g.fixInfo("Test-DB");
        g.bTestPC = true;
      }
      else if (s1.equals("TestDB") && s2.equals("weg"))
      {
        g.fixInfo("keine Test-DB");
        g.bTestPC = false;
      }
      else if (s1.equals("Test") && s2.equals("Info"))
      {
        g.fixInfo("testInfo aktiviert");
        Transact.bTest=true;
      }
      else if (s1.startsWith("Info"))
        checkInfo(g,s1,s2);
      else if (s1.equals("SQL"))
      {
        Static.bSQL = s2.equals("show");
        if (Transact.lQryAb<0)
          Transact.lQryAb=2000;
      }
      else if (s1.equals("iMinX"))
      {
    	  Static.iMinX=Sort.geti(s2);
    	  g.fixInfo("iMinX="+Static.iMinX);
      }
      else if (s1.equals("iMinY"))
      {
    	  Static.iMinY=Sort.geti(s2);
    	  g.fixInfo("iMinY="+Static.iMinY);
      }
//      else if (s1.equals("style"))
//      {
//    	  Static.bShowStyle=s2.equals("show");
//    	  g.fixInfo("ShowStyle="+Static.bShowStyle);  
//      }
//      else if (s1.equals("alert"))
//      {
//    	  Static.bAlert=s2.equals("set");
//    	  g.fixInfo("Alert="+Static.bAlert);  
//      }
//      else if (s1.equals("JavaFX"))
//    	  Static.bJavaFX=s2.equals("set");
      else if (s1.equals("x"))
      {
    	  Static.bX=s2.equals("set");
    	  g.fixInfo((Static.bX ? "set":"clear")+" x statt Ja/Nein");
      }
      else if (s1.equals("FX"))
      {
    	  GPS_Eingabe.bExtern=!s2.equals("set");
    	  g.fixInfo((GPS_Eingabe.bExtern ? "set":"clear")+" Web extern");
      }
      else if (s1.equals("Mojave"))
      {
    	  Static.bMojave=s2.equals("set");
    	  g.fixInfo((Static.bMojave ? "set":"clear")+" Mojave");
    	  g.LoadSchrift(true,g.getFontFaktor());
      }
      else if (s1.equals("ND"))
      {
    	  Static.setND(s2.equals("set"));
    	  g.fixInfo((Static.bND ? "set":"clear")+" new design");
      }
      else if (s1.equals("NI"))
      {
    	  Static.setNI(s2.equals("set"));
    	  g.fixInfo((Static.bNI ? "set":"clear")+" new image");
      }
      else if (s1.equals("WW"))
      {
        boolean b=g.bCC;
        g.bCC=s2.equals("set");
        g.fixInfo("WW:"+b+"->"+g.bCC);
      }
      else if (s1.equals("X11"))
      {
        boolean b=Static.bX11;
        Static.bX11=s2.equals("set");
        g.fixInfo("X11:"+b+"->"+Static.bX11);
      }
//      else if (s1.equals("BorderPane"))
//      {
//        boolean b=Static.bBorderPane;
//        Static.bBorderPane=s2.equals("set");
//        g.fixInfo("BorderPane:"+b+"->"+Static.bBorderPane);
//      }
      else if (s1.equals("html"))
      {
        boolean b=Static.bHtmlDruck;
        Static.bHtmlDruck=s2.equals("druck");
        g.fixInfo("HtmlDruck:"+b+"->"+Static.bHtmlDruck);
      }
      else if (s1.equals("web"))
      {
    	  Static.iWeb=s2.equalsIgnoreCase("immer") ? Static.IMMER:s2.equalsIgnoreCase("nie") ? Static.NIE:Static.AUTO;
    	  g.fixInfo("iWeb="+Static.iWeb);
      }
      else if (s1.equals("Font"))
        Static.sFont=s2;
      else if (s1.equals("PM"))
        g.bEnable=s2.equals("E");
      else if (s1.equals("Appl") && s2.equals("Isi"))
      {
        g.fixInfo("auf Appl gesetzt");
        g.bAppl=true;
      }
      else if (s1.equals("Basis") && s2.equals("B"))
      {
        g.fixInfo("auf Basis gesetzt");
        g.bBasis=true;
      }
      else if (s1.equals("Read") && s2.equals("Only"))
        g.setReadOnly();
      else if (s1.equals("Work") && s2.equals("Flow"))
        g.setWorkflow();
      else if (s1.equals("Work") && s2.equals("ZR"))
        g.setWorkflowZR();
      else if (s1.equals("Browser") && s2.length()==1)
        Static.cURL=s2.charAt(0);
      else if (s1.equals("clock2") && s2.startsWith("ab"))
      {
    	  int i=Sort.geti(s2.substring(2));
    	  if (i>0)
    	  {
    		  Transact.iClock2Ab=i;
    		  g.fixInfo("Clock2Ab="+Transact.iClock2Ab);
    	  }
      }
      else if (s1.equals("Qry") && s2.startsWith("ab"))
      {
    	  int i=Sort.geti(s2.substring(2));
    	  if (i>0)
    	  {
    		  Transact.lQryAb=i;
    		  g.fixInfo("QryAb="+Transact.lQryAb);
    	  }
      }
      else if (s1.equals("Abf") && s2.startsWith("ab"))
      {
    	  int i=Sort.geti(s2.substring(2));
    	  if (i>0)
    	  {
    		  Static.lAbfAb=i;
    		  g.fixInfo("AbfAb="+Static.lAbfAb);
    	  }
      }
      else if (s1.equals("Open") && s2.equals("JDK"))
      {
    	  Static.bOpenJDK=true;
    	  g.fixtestError("stelle auf OpenJDK");
      }
      else if (s1.equals("check") && s2.equals("ZR"))
      {
    	  Static.bCheckZR=true;
    	  g.fixtestError("CheckZR aktiviert");
      }
      else if (g.Def() && s1.startsWith("#"))
        g.changeBenutzer(s1.substring(1),s2);
      else if (g.Def() && s1.startsWith("*"))
        g.changeMandant(s1.substring(1),s2);
}

private static void checkInfo(Global g,String s1,String s2)
{
  boolean b=s1.equals("Info");
  if (s2.equals("Sync"))
    Global.bSyncInfo=b;
  else if (s2.equals("Bild"))
    Static.bInfoBild=b;
  else if (s2.equals("Leer"))
    Static.bInfoLeer=b;
  else if (s2.equals("Abfrage"))
    Global.bInfoAbfrage=b;
  else if (s2.equals("Druck"))
    Global.bInfoDruck=b;
  else if (s2.equals("Jeder")) // färbt nicht-Jeder-Eigenschaften/Tabs
    Global.bInfoJeder=b;
  else if (s2.equals("Tod")) // zeigt verwendung von Toden Begriffe/Eigenschaften
	Static.bInfoTod=b;
  else if (s2.equals("Event")) // zeigt Popupmenüs mit alter variante
    Global.bInfoEvent=b;
  else if (s2.equals("GF")) // schalten GruppenFilter-Info für Hauptschirm ein
    Global.bInfoGF=b;
  else if (s2.equals("DB"))
    Static.bShowDB=b;
  else if (s2.equals("TT"))
    Static.bInfoTT=b;
  else if (s2.equals("Except"))
	    Static.bInfoExcept=b;
  else if (s2.equals("Win"))
	    Static.bInfoWin=b;
  else if (s2.equals("Debug"))
	  g.setDebug(b);
  else if (s2.equals("SQL"))
	  Static.bInfoSQL=b;
  else if (s2.equals("E-Mail"))
	  Static.bInfoEMail=b;
  else
    return;
  g.fixInfo((b?"setzt":"keine")+" Info für "+s2);
}


        private void addDefEvent(JComponent Btn)
        {
          //if (/*!bImmer && */!g.Def())
          //  return;
          if (EventM==null)
            EventM=new MouseListener()
          {
            public void mousePressed(MouseEvent ev)
            {}

            public void mouseClicked(MouseEvent ev)
            {
              //g.fixInfo("Knopf gedrückt:"+s+"/"+ev.getButton());
              int iM=ev.getModifiers();
              if (/*bImmer &&*/ iM==5)
                Rule_change(g,thisJFrame());
              else if (g.Def())
              if (ev.getButton()==3 && ev.getModifiersEx()!= Global.iRM || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
              {
                ToolTipEdit((JComponent)ev.getSource(),((JComponent)ev.getSource()).getName());
              }
            }

            public void mouseEntered(MouseEvent ev)
            {}

            public void mouseExited(MouseEvent ev)
            {}

            public void mouseReleased(MouseEvent ev)
            {}
          };
          Btn.addMouseListener(EventM);
        }

	protected Tabellenspeicher getTab(String sKennung)
	{
		TabKomponenten.posInhalt("Kennung",sKennung);
		Tabellenspeicher Tab=TabKomponenten.out() ? null:(Tabellenspeicher)TabKomponenten.getInhalt("Tab");
		if (Tab==null)
			g.printError("Formular.getTab: Kann "+sKennung+" nicht finden!",iBegriff);
		else
			Tab.moveFirst();
		return(Tab);
	}

	public JComponent get(String sArt,String sKennung)
	{
		Tabellenspeicher Tab = sArt.equals("Formular") ? TabFormular:getTab(sArt);
                //Tab.showGrid(sArt);
		boolean b = Tab !=null && Tab.posInhalt("Kennung",sKennung);
		if (!b)
		{
			//Tab.showGrid(sArt);
             g.fehlInfo("~Formular <"+getTitle()+">: "+sArt+" <"+sKennung+"> ist nicht vorhanden!",bSystem);
		}
                else if (!sArt.equals("Formular"))
                  Tab.setInhalt("use",Boolean.TRUE);
		return b ? sArt.equals("Formular")?(JComponent)Tab.getInhalt("Button") : (JComponent)Tab.getInhalt("Komponente") : null;
	}

        private void showToMuch(String sArt)
        {
          Tabellenspeicher Tab = getTab(sArt);
          for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            String s=Tab.getS("Kennung");
            if (Tab.isNull("use") && !s.equals("-") && !s.startsWith("help"))
            {
              g.fehlInfo("~Formular <" + getTitle() + ">: " + sArt + " <" + s + "> wird nicht unterstützt!",bSystem);
              ((JComponent)Tab.getInhalt("Komponente")).setEnabled(false);
            }
          }
        }

        private void showToMuch()
        {
          //g.progInfo("showToMuch von "+getTitle()+"/"+getClass().getName());
          //if (g.Prog() && TabSub != null)
          //  TabSub.showGrid("TabSub");
          if (!getClass().getName().equals("All_Unlimited.Allgemein.Formular"))
          {
            showToMuch("Button");
            showToMuch("frei");
            showToMuch("Checkbox");
            showToMuch("Radiobutton");
          }
        }

	public JButton getFormularButton(String sKennung)
	{
		return (JButton)get("Formular",sKennung);
	}

	public JButton getButton(String sKennung)
	{
		JButton Btn=(JButton)get("Button",sKennung);
		if (Btn != null && (sKennung.equals("RefreshDB") || sKennung.equals("LogoutDB") || sKennung.equals("OkDB") || sKennung.equals("sendFT")) && Static.Leer(Static.sWeb))
		{
			Btn.setEnabled(false);
			return null;
		}
		return Btn;
	}

    public JButton getButton(String sKennung,String s,ActionListener AL)
    {
      JButton Btn = (JButton)get("Button", sKennung);
      Global.setAction(Btn, s==null ? sKennung:s, AL);
      return Btn;
    }

    public JToggleButton getTButton(String sKennung)
    {
      JComponent C=get("Button",sKennung);
      return C instanceof JToggleButton ? (JToggleButton)C:null;
    }

	public JPanel getFrei(String sKennung)
	{
		return (JPanel)get("frei",sKennung);
	}
	
	public void addFrei(String sKennung,JComponent C)
	{
		JPanel Pnl=(JPanel)get("frei",sKennung);
		if (Pnl != null)
			Pnl.add("Center",C);
		int iPos=g.getPosBegriff("frei", sKennung);
		if (Pnl != null && iPos>=0)
			g.setTooltip(g.TabBegriffe.getM(iPos,"Tooltip"),C);
	}
	
	public AUCheckBox getCheckbox(String sKennung)
	{
		return (AUCheckBox)get("Checkbox",sKennung);
	}
    public AUCheckBox getCheckbox0(String sKennung)
    {
            AUCheckBox Cbx=(AUCheckBox)get("Checkbox",sKennung);
            return Cbx==null ? new AUCheckBox():Cbx;
    }
    
    public void showLabel(String sKennung,boolean b)
    {
    	JLabel Lbl=(JLabel)get("Label",sKennung);
    	if (Lbl!=null)
    		Lbl.setVisible(b);
    }
    
    public JLabel getLabel(String sKennung)
	{
		return (JLabel)get("Label",sKennung);
	}

	public JRadioButton getRadiobutton(String sKennung)
	{
		return (JRadioButton)get("Radiobutton",sKennung);
	}

	public void setTitle(String s)
	{
          //lClock6=Static.get_ms();
		if (sFormularBezeichnung == null)
			sFormularBezeichnung = s;
          //lClock7=Static.get_ms();
		if(AU_Frame==null)
			AU_Dialog.setTitle(s);
		else
			AU_Frame.setTitle(s);
          //lClock8=Static.get_ms();
	}

	public String getTitle()
	{
		String s;

		if(AU_Frame==null)
			s=AU_Dialog.getTitle();
		else
			s=AU_Frame.getTitle();
		return(s);
	}

        public void setIconImage(Image Gif)
        {
          if (Gif != null && AU_Frame!=null)
              thisJFrame().setIconImage(Gif);
        }

	public void show()
	{
		//g.testInfo(getTitle()+":"+thisFrame.getSize()+"/"+thisFrame.isShowing()+"/"+thisFrame.isVisible()+"/"+(thisFrame.isShowing()?thisFrame.getLocationOnScreen():null));
		if (thisFrame.isShowing() && thisFrame.getLocationOnScreen().x<-1000)
		{
			//g.testInfo("vor:"+thisFrame.getLocation()+"/"+thisFrame.getLocationOnScreen());
			//Static.centerComponent(thisFrame,null);
			//thisFrame.setLocationOnScreen(thisFrame.getLocation().x,thisFrame.getLocation().y,thisFrame.getSize().width,thisFrame.getSize().height);
			((Frame)thisFrame).setState(Frame.NORMAL);
			//g.testInfo("nach:"+thisFrame.getLocation()+"/"+thisFrame.getLocationOnScreen());
		}
                showToMuch();
                //long lClock = Static.get_ms();
                //if (!thisFrame.isVisible())
                  thisFrame.setVisible(true);
                  if (thisFrame instanceof JFrame)
                    g.testInfo("show "+sFormularBezeichnung+":"+(thisFrame.getSize().getHeight()-thisJFrame().getRootPane().getSize().getHeight()));
                  //thisFrame.toFront();
                //g.progInfo("show "+getTitle()+":"+(Static.get_ms()-lClock)+" ms");
		/*
		if(AU_Frame==null)
			AU_Dialog.show();
		else
			AU_Frame.show();*/
	}

	public void hide()
	{
          saveFenster();
          thisFrame.setVisible(false);
		/*
		if(AU_Frame==null)
			AU_Dialog.hide();
		else
			AU_Frame.hide();*/
	}

public int Typ()
{
	return (iBits & cstStammFix)>0 ? Stamm:iTyp;
}

public int Bewegungstyp()
{
	return StammAusBew() ? 0:iBewegungstyp;
}

public int Eigenschaft()
{
	return (iBits & cstKeinStamm)>0 ? 0:iEigenschaft;
}

public int getBegriff()
{
	return iBegriff;
}

public int getStamm(int iStamm)
{
  if ((iBits & cstStammFix)>0 && iFixStamm>0)
    return iFixStamm;
  if (!isRefresh2() && iStamm<-2)
  {
    iStamm=SQL.getInteger(g, "select "+g.getANR_BS(iBewegungstyp,iStammtyp)+" from bew_pool where aic_bew_pool=" + ( -iStamm));
    g.progInfo("getStamm:"+iBewegungstyp+"/"+iStammtyp+"->"+iStamm);
  }
  return iStamm;
}

public boolean StammAusBew()
{
  return (iBits & cstStammFix)>0 && iFixStamm==0;
}

public boolean AutoSave()
{
	return (iBits & cstAutoSave)>0;
}

public boolean kopierbar()
{
	return (iBits & cstKopierbar)>0;
}

public boolean BewVoll()
{
	return (iBits & cstBewVoll)>0;
}

public boolean keinStamm()
{
	return (iBits & cstKeinStamm)>0;
}

public boolean Synchron()
{
	return (iBits & cstSynchron)>0;
}

public boolean Verteiler()
{
	return (iBits & cstVerteiler)>0;
}

public boolean isRefresh()
{
        return (iBits & cstRefresh)>0;
}

public boolean isRefresh2()
{
        return (iBits & cstRefresh2)>0;
}

public boolean Mehrfach()
{
        return (iBits & cstMehrfach)>0;
}

public boolean Login()
{
        return (iBits & cstLogin)>0;
}

public boolean Monat()
{
        return (iBits & cstZR)==cstMonat;
}
public boolean Woche()
{
  //g.fixInfo("Woche:"+iBits+"/"+cstZR+"/"+cstWoche);
  return (iBits & cstZR)==cstWoche;
}
public boolean Tag()
{
  return (iBits & cstZR)==cstTag;
}

public boolean keinStammTitel()
{
        return (iBits & cstKeinStammTitel)>0;
}

public boolean ReadOnly()
{
        return (iBits & cstKeinReadOnly)==0 && g.ReadOnly();
}

public boolean isNeuCheck()
{
  return (iBits & cstNeuCheck)>0;
}

public boolean isBewBew()
{
  return (iBits & cstBewBew)>0;
}

public boolean isMultiselect()
{
	return (iBits & cstMultiselect)>0;
}

public boolean isRepaint()
{
  return (iBits & cstRepaint)>0;
}

public boolean keinZR()
{
  return (iBits & cstKeinZR)>0;
}

public boolean AllSync()
{
  return (iBits & cstAllSync)>0;
}

public String getBez()
{
	return sFormularBezeichnung;
}

public void suchen(JCOutliner Gid,Point P)
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
            CboSuche.setFont(Global.fontBezeichnung);
//            ShowAbfrage Abf=(ShowAbfrage)TabOutliner.getInhalt("Abfrage");
//            bBeginn=true;//Abf.bBeginn;

	{
//		Vector VecGidSpalten = Abf.getBezeichnung();
		String[] sAry=Gid.getColumnLabels();
		for(int i=0;i<sAry.length;i++)
			CboSuche.addItem(""+sAry[i],i+1,"");
	}
	//CboSuche.setSelectedAIC(iSpalte);
	JPanel Pnl2 = new JPanel(new BorderLayout());
		Pnl2.add("West",CboSuche);
		final Text Edt=new Text(sSuchtext,20);
                    Edt.setFont(Transact.fontStandard);
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
                            if (Gid != null)
                            {
                              JCOutlinerFolderNode Nod = (JCOutlinerFolderNode)Gid.getRootNode();
                              if (suche(Gid,Nod, sSuchtext, CboSuche.getSelectedAIC() - 1))
                              {
                                bAb = true;
                                suche(Gid, Nod, sSuchtext, CboSuche.getSelectedAIC() - 1);
                              }
                            }
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

private boolean suche(JCOutliner Gid,JCOutlinerFolderNode Nod,String sText,int iPos)
{
	boolean bBeginn=true;
//      JCOutliner Gid=(JCOutliner)TabOutliner.getInhalt("Gid");
      int iStamm_Mom=0;
      if (/*Gid.getSelectedNode().getLevel()<2 &&*/ Gid.getSelectedNode().getUserData() instanceof Integer)
        iStamm_Mom=((Integer)Gid.getSelectedNode().getUserData()).intValue();
	boolean b=true;
	Vector Vec = Nod.getChildren();
	if (Vec != null)
	{
		for(int i=0;i<Vec.size() && b;i++)
		{
			JCOutlinerNode NodNeu=(JCOutlinerNode)Vec.elementAt(i);
                            if (NodNeu instanceof JCOutlinerFolderNode)
                              if (!suche(Gid,(JCOutlinerFolderNode)NodNeu,sText,iPos))
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
//						bkHE=true;
						Static.makeVisible(Gid,NodNeu);
						g.fixtestInfo("gefunden:"+NodNeu.getLabel());
                        //Gid.folderChanged(NodNeu.getParent());
//                        Hauptoutliner_Event((AUOutliner)Gid,true);
//                        bkHE=false;
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

protected void hideDlg(JDialog Dlg)
{
	Dlg.setVisible(false);
    thisJFrame().setEnabled(true);
    thisJFrame().toFront();
}

// add your data members here
public static final int kein=0;
public static final int Stamm=1;
public static final int Bewegung=2;
public static final int Mehrfach=3;
private int iTyp=Stamm; // sonst Meldung: java.lang.IllegalAccessError: All_Unlimited/Allgemein/Formular: field iTyp is inaccessible //

protected Global g;
private int iFormNr=0;
private boolean bEingabeFormular = false;
private Tabellenspeicher TabKomponenten;
protected Tabellenspeicher TabFormular;
public Tabellenspeicher TabAbf;
public Tabellenspeicher TabSub;
protected String UeberschriftsKennung;
public Window thisFrame;
public int iProg=0;
public int iStammtyp=0;
private int iBewegungstyp=0;
private int iEigenschaft =0;
public int iRolle=0;
private Tabellenspeicher TabDarstellung;
private String sGruppe;
private JFrame AU_Frame = null;
private JDialog AU_Dialog = null;
//private int iFormulartyp;
protected String sFormularBezeichnung=null;
//private String sTypBez=null;
private int iBegriff=0;
public long iBits=-1;
private int iFixStamm=-1;
private int iReiheOld=0;
private MouseListener EventM=null;
public boolean bCalc=false;
public boolean bCalc2=false;
public int iDaten=0; // 1..alle, 2..nur_Vector, 3..nur_Firma
public static final int ALLE=1;
public static final int VECTOR=2;
public static final int FIRMA=3;

protected int iFF=100;

//public long lClock6=0;
//public long lClock7=0;
//public long lClock8=0;
public boolean bFRefresh=false;

private Tabellenspeicher TabRadio = null;
public Vector<JTabbedPane> VecTC=new Vector<JTabbedPane>();
private boolean bSystem=false;
public boolean bFP=false; //ob Fensterpos gespeichert war
private JDialog DlgSuche=null;
private String sSuchtext="";
private boolean bAb=false;
//public Vector<Button> VecBtn=new Vector<Button>();
}

