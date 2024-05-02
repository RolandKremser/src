/*
    All_Unlimited-Hauptmaske-ShowAbfrage.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.Image;
//import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.event.ItemEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

//import javax.swing.AbstractAction;
//import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCItemEvent;
// import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import jclass.util.JCSortable;
import jclass.util.JCqsort;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Favorit;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.*;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Grunddefinition.Logging;
import jclass.bwt.JCOutlinerComponent;

import javax.swing.JSplitPane;

import All_Unlimited.Allgemein.Eingabe.AUOutliner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.JEditorPane;

public class ShowAbfrage extends All_Unlimited.Hauptmaske.Abfrage
{
    // add your data members here
	private Tabellenspeicher TabSpalten=null;
	private Calc calc=null;
	public Vector<Object> VecAbf=null;
	private Vector<Integer> VecAusrichtung=null;
	private Vector<String> VecBezeichnung=null;
	public Vector VecEigenschaften=null;
	private boolean bAlleMandanten=false;
	public String sSQL;
	private boolean bExport=false;
        public boolean bForExport=false;
        //private boolean bLeer=false;
        public boolean bRefresh=false;
        private boolean bHistory=false;
        private static Tabellenspeicher TabBFilter=null;
        private Tabellenspeicher TabMehr=null;
        //private Color Col=null;
        private boolean bSaveView=false;
        private String sANR=null;
        //private String sBind;
        public boolean bBeginn=false;
        public boolean bMulti=false;

        private boolean bDruck=false;
        private boolean bHtml=false;
//        private boolean bJavaFX=false;
        
        public Vector<Object> VecTST=null;
        public Tabellenspeicher TabD=null;
        public JCOutlinerFolderNode NodRoot=null;
        public Timestamp TSZeitraumVon=null;
        public Timestamp TSZeitraumBis=null;     
        public boolean bTest=false;

	public ShowAbfrage(Global g,String sKennung)
	{
		super(g,sKennung);
		if (Fehler())
			g.printError("ShowAbfrage: Kennung "+sKennung+" nicht gefunden!");
		else
		{
			SQL_String();
			TabSpalten.moveFirst();
			//VecAbf.addElement(new Tabellenspeicher(g,""+VecAbf.elementAt(2)+VecAbf.elementAt(3),true));
		}
	}

        public ShowAbfrage(Global g,int iBegriff,int iS2_Art,int iBG)
        {
          super(g,iBegriff,cstBegriff,true,iS2_Art,iBG,false);
          if (iAbfrage>0 && !Fehler())
            SQL_String();
        }

	public ShowAbfrage(Global g,int iAic,int iArt)
	{
	  this(g,false,iAic,iArt,false);
	}

	public ShowAbfrage(Global g,boolean bWeb2,int iAic,int iArt,boolean b)
	{
		super(g,iAic,iArt==cstExport?cstBegriff:iArt,b,bWeb2);
		bExport=iArt==cstExport;
		if (iAbfrage>0 && !Fehler())
			SQL_String();
	}

	public ShowAbfrage(Abfrage A)
	{
		super(A);
	}
	
//	public void setJavaFX()
//	{
//		bJavaFX=true;
//	}

        public void setDruck(boolean rbHtml)
        {
          bDruck=true;
          bHtml=rbHtml;
        }

        public void checkHS_Bezeichnung()
        {
          //boolean bNull=TabSpalten==null;
          if (TabSpalten!=null)
            return;
          TabSpalten=getAnzeigeSpalten(null);
          TabSpalten.moveFirst();
          TabSpalten.newLine();
          TabSpalten.setInhalt("Bezeichnung",g.getBegriffS("Show","Bezeichnung"));
          TabSpalten.setInhalt("Kennung","Bezeichnung");
          TabSpalten.setInhalt("Datentyp","Bezeichnung");
          TabSpalten.setInhalt("Format","dd.MM.yyyy");
          if (g.Def())
          {
            TabSpalten.newLine();
            TabSpalten.setInhalt("Bezeichnung",g.getBegriffS("Show","SysAic"));
            TabSpalten.setInhalt("Kennung","AIC_Stamm");
            TabSpalten.setInhalt("Datentyp","SysAic");

            TabSpalten.newLine();
            TabSpalten.setInhalt("Bezeichnung",g.getBegriffS("Show","Mandant"));
            TabSpalten.setInhalt("Kennung","MK");
            TabSpalten.setInhalt("Datentyp","Mandant");
          }
          //TabSpalten.showGrid("TabSpalten");
          TabSpalten.bInsert=false;

            VecBezeichnung=new Vector<String>();
            VecAusrichtung=new Vector<Integer>();
            VecBezeichnung.addElement(g.getBegriffS("Show", "Bezeichnung"));
            VecAusrichtung.addElement(new Integer(BWTEnum.TOPLEFT));
            if (g.Def())
            {
              VecBezeichnung.addElement(g.getBegriffS("Show", "SysAic"));
              VecAusrichtung.addElement(new Integer(BWTEnum.TOPRIGHT));
              VecBezeichnung.addElement(g.getBegriffS("Show", "Mandant"));
              VecAusrichtung.addElement(new Integer(BWTEnum.TOPLEFT));
            }

        }

        public Tabellenspeicher OutToTab(JCOutlinerNode Nod)
        {
          Vector VecChildren = Nod.getChildren();
          Tabellenspeicher Tab=new Tabellenspeicher(TabSpalten,g);
          for (int i=0;i<VecChildren.size();i++)
          {
            JCOutlinerNode OutChild = (JCOutlinerNode)VecChildren.elementAt(i);
            Vector VecO=(Vector)OutChild.getLabel();
            for(int iPos=0;iPos<TabSpalten.size();iPos++)
              Tab.addInhalt(TabSpalten.getS(iPos,"Kennung"),Static.fromImport(Sort.gets(VecO,iPos)));
          }
          return Tab;
        }

	public Tabellenspeicher getSpalten()
	{
		PeriodenCheck();
                if (TabSpalten != null)
                  TabSpalten.moveFirst();
		return TabSpalten;
	}

	@SuppressWarnings("unchecked")
	public boolean PeriodenCheck()
	{
		Vector VecPer=g.getVecPer(iVB);
		boolean b= VecPerioden != null && VecPer != null && (VecPerioden.size() != VecPer.size() || !VecPerioden.containsAll(VecPer)) || g.TabPerioden!=null && g.TabPerioden.size()>0 || bRefresh;
		if (b)
			SQL_String();
		return b;
	}

	public Vector getTitel()
	{
		return iModell>0 /* && Calc.Normal(g, iModell)*/ && (iBits&cstSumme)==0 ? null:(Vector)VecAbf.elementAt(1);
	}

        public Tabellenspeicher checkStammStichtag(int iStamm,Vector VecStamm)
        {
          if ((iBits & cstStammstichtag) > 0)
          {
            Vector Vec=TabSpalten.getVecSpalte("Kennung2");
            Vector<Integer> VecSpalten=new Vector<Integer>();
            for (int i=0;i<Vec.size();i++)
              VecSpalten.addElement(new Integer(Tabellenspeicher.geti(Vec.elementAt(i))));
            String sGueltig="";
            if ((iBits & cstKeinStammZeitraum)==0)
            {
              //String sVon = g.ASA() ? "von" : "(select von from zr)";
              //String sBis = g.ASA() ? "bis" : "(select bis from zr)";
              //SQL.von();
              sGueltig=" and ("+g.bis()+" is null or gultig_von is null or gultig_von < "+g.bis()+") and ("+g.von()+" is null or gueltig_bis is null or gueltig_bis > "+g.von()+")";
            }
            String sBed="aic_stamm"+((iBits&cstMengen)>0 ? Static.SQL_in(VecStamm):"="+iStamm);
            Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_stamm,aic_eigenschaft,spalte_double,sta_aic_stamm,gultig_von,gueltig_bis,aic_daten from stammpool where pro2_aic_protokoll is null and "+sBed+
                " and aic_eigenschaft"+Static.SQL_in(VecSpalten)+sGueltig+" order by aic_stamm,aic_eigenschaft,gultig_von",true);
            //Tab.showGrid("Stammstichtag");
            return Tab;
          }
          else
            return null;
        }

        private void checkSpalten(Tabellenspeicher TabSC, int riAbfrage)
        {
          //TabSC.showGrid("SC_"+sDefBez);
          //TabSpalten.showGrid("Spalten_"+sDefBez);
          //g.testInfo("checkSpalten mit "+ riAbfrage);
          boolean b=false;
          for (TabSC.posInhalt("Abfrage",riAbfrage);TabSC.getI("Abfrage")==riAbfrage && !TabSC.out();TabSC.moveNext())
          {
            if (TabSpalten.posInhalt("Aic",TabSC.getI("Aic")))
            {
              //g.testInfo(TabSC.getI("Aic")+":"+TabSpalten.getS("Bezeichnung")+"->"+TabSC.getS("Bezeichnung"));
              if (!TabSpalten.getS("Bezeichnung").equals(TabSC.getS("Bezeichnung")))
              {
                /*int i=VecBezeichnung.indexOf(TabSpalten.getS("Bezeichnung"));
                if (i>=0)
                  VecBezeichnung.setElementAt(TabSC.getS("Bezeichnung"),i);*/
                g.testInfo(TabSC.getI("Aic")+":"+TabSpalten.getS("Bezeichnung")+"->"+TabSC.getS("Bezeichnung"));
                TabSpalten.setInhalt("Bezeichnung", TabSC.getS("Bezeichnung"));
                b=true;
              }
              if (TabSpalten.getI("bits") != TabSC.getI("bits"))
              {
                g.fixtestInfo(TabSC.getI("Aic")+":"+TabSpalten.getS("bits")+"->"+TabSC.getS("bits"));
                TabSpalten.setInhalt("bits", TabSC.getI("bits"));
                b=true;
              }
            }
          }
          if (b)
            checkVecBezeichnung();
          if (b && Global.bInfoDruck)
            TabSpalten.showGrid("Spalten_"+sDefBez);
        }

        private String Bind(int i)
        {
          sBind=""+i;
          return "?";
        }
        
        public Tabellenspeicher getDaten0()
        {
        	return getDaten(0,0,null,false,false,null);
        }

        public Tabellenspeicher getDaten(int iStammtyp,int iStamm,Vector VecStamm,Object win)
        {
          return getDaten(iStammtyp,iStamm,VecStamm,false,false,win);
        }

        public void clearCalc()
        {
          calc=null;
        }
        
        public String toString()
        {
        	return sBez;
        }
        
    public void setYear(boolean bJahr)
    {
    	if (bJahr)
    	{
	  	  DateWOD DW=DateWOD.getNewYear(g.getVon(iVB));
	  	  Timestamp dtVon=DW.toTimestamp();
	  	  DW.nextYear();
	  	  Timestamp dtBis=DW.toTimestamp();
	  	  g.setVonBis(dtVon, dtBis,iVB);
	  	  Zeitraum.PeriodeToVec2(g,iVB,g.getZA(iVB),true,false);
	  	  PeriodenCheck();
	  	  //g.fixtestError("Ändere Zeitraum von "+TSZeitraumVon+"-"+TSZeitraumBis+" auf "+g.getVon()+"-"+g.getBis());
    	}
    }

	@SuppressWarnings("unchecked")
	public Tabellenspeicher getDaten(int iStammtyp,int iStamm,Vector VecStamm,boolean bUseVec,boolean bView2,Object win)
	{
		if (iBew>0 && !g.allowBew(iBew))
			return null;
		VecStamm=FilterCheck(VecStamm);
		// g.fixtestError("getDaten von "+sDefBez+":"+(iS2_Art==1 ? "pers":iS2_Art==2 ? "BG"+iBG_Soll:iS2_Art==3 ? "Jeder":"Original")+" mit Stt="+iStammtyp);
		g.checkVon();
          if (iStamm==0 && (iBits&cstMengen)>0 && VecStamm != null && VecStamm.size()>0)
            iStamm=Sort.geti(VecStamm,0);
          g.fixtestInfo("getDaten"+iAbfrage+":"+iStammtyp+"/"+iStamm+"/"+VecStamm+":"+((iBits&cstMengen)>0)+"/"+iModell+"/"+bUseVec+"/"+bView2);
          //g.progInfo("---------------------------------                                 getDaten:"+sBez+"/"+iStamm+"/"+VecStamm);
          TSZeitraumVon=g.getVon(iVB);
          TSZeitraumBis=g.getBis(iVB);
          if((iStamm==0 || iStamm==-1) && VecStamm==null && (iBits & cstKeinStamm)==0 && (!bTest || iModell==0))
          {
            g.defInfo2(sDefBez+": Stammsatz nicht angegeben");
            return null;
          }
          long lClock=Static.get_ms();
          bRefresh=false;
          boolean bJahr=(lBits2&cstAbfJahr)>0;
       	  setYear(bJahr);     
//            if (bJahr)
//          {
//        	  DateWOD DW=DateWOD.getNewYear(g.getVon(iVB));
//        	  Timestamp dtVon=DW.toTimestamp();
//        	  DW.nextYear();
//        	  Timestamp dtBis=DW.toTimestamp();
//        	  g.setVonBis(dtVon, dtBis,iVB);
//        	  Zeitraum.PeriodeToVec2(g,iVB,g.getZA(iVB),true,false);
//        	  PeriodenCheck();
//        	  //g.fixtestError("Ändere Zeitraum von "+TSZeitraumVon+"-"+TSZeitraumBis+" auf "+g.getVon()+"-"+g.getBis());
//          }
		if (iStamm==0 && VecStamm != null && VecStamm.size()>0)
			iStamm=((Integer)VecStamm.elementAt(0)).intValue();
          Tabellenspeicher Tab=checkStammStichtag(iStamm,VecStamm);
          if (Tab != null)
          {
            g.defInfo2(sDefBez+": checkStammStichtag ergibt null");
            return Tab;
          }
		if ((iBits&cstMengen)>0 && VecStamm==null)
			VecStamm=Static.AicToVec(iStamm);
                if ((iBits&cstMengen)>0)
                  VecMenge=VecStamm;
                iQry=iStamm;
                if (!g.SuperUser() && (lBits2&cstSbRefresh)>0)
                	checkStammBerechtigung(g);
		if (iModell>0)// && Calc.Normal(g, iModell))
		{
                  g.checkModelle();
                  int iPosM=g.TabModelle.getPos("aic_modell",iModell);
                  int iBitsM=iPosM<0 ? 0:g.TabModelle.getI(iPosM,"bits");
                  String sBez=g.TabModelle.getS(iPosM,"Bezeichnung");
                  g.testInfo("getDaten mit Modell:"+sBez);
                  if (iStamm<=0 && (iBitsM&Global.cstOhneStamm)==0 && (iBits & cstKeinStamm)==0)
                  {
                    g.defInfo2(sDefBez+": Modell nicht auf keinStamm gesetzt");
                    return null;
                  }
                  //if (iStamm<=0 && g.TabModelle.posInhalt("aic_modell",iModell) && (g.TabModelle.getI("bits")&Global.cstOhneStamm)==0)
                  //  return null;
                  Integer IntStamm=new Integer(iStamm);
                  if(VecStamm != null)
                    VecStamm=new Vector(VecStamm);
                  if(VecStamm != null && VecStamm.size()>1 && !VecStamm.elementAt(0).equals(IntStamm) && VecStamm.contains(IntStamm))
                  {
                    int iPos=VecStamm.indexOf(IntStamm);
                    g.testInfo("Stammsatz umkopieren:"+IntStamm+" ("+iPos+"->0");
                    VecStamm.remove(iPos);
                    VecStamm.insertElementAt(IntStamm,0);
                  }
                  Vector Vec=(iBits & cstMengen) > 0 ? VecStamm : Static.AicToVec(iStamm);
                  if (AClient.UseAServer(iBitsM))
                    Tab=TCalc.getAbfrage(g,iModell,iBitsM,Vec,iAbfrage,null);
                  else
                  {
                	  //g.fixtestError("Modellaufruf von getDaten:"+sBez+" mit Win="+Static.print(win));
                    if (calc == null)
                      calc = new Calc(win, g, iModell, false, Vec, false /*bBew*/, -1, this, 0,null,null,null,null,iVB);
                    else
                      calc.reCalc(Vec, true, iAbfrage,null,null,iVB,-1);
                    Tab = calc.getTab(iAbfrage);
                    checkSpalten(calc.getSpalten(),iAbfrage);
                  }
                  if (bWeb2 && iStt==g.iSttANR && (iBits&cstKeinAustritt)==0 && g.TabAustritt != null && !g.TabAustritt.isEmpty())
                      removeAustritt(g,Tab,iBew>0);
                  long lDauer=Static.get_ms()-lClock;
                  if (lDauer>200)
                	  g.SaveVerlaufFertig(g.SaveVerlauf(iBegriff,iStamm,0),lDauer,false);
                  clockCheck("Daten+",lClock,Tab);
                  return Tab;
		}
		//g.progInfo("Stt="+iStt+" ,Stammtyp="+iStammtyp+" ,Bew="+iBew);

		boolean b=iStt != iStammtyp && iBew==0 && iStammtyp !=0 && bSpalten && (iBits&cstKeinStamm)==0;
                //boolean b=false;
                //if (iStt != iStammtyp && iBew==0 && iStammtyp !=0 && bSpalten)
                //  clockCheck("Spezialfall mit "+((iBits&cstKeinStamm)==0),lClock-100000,null);

		if (iStt>0 && (iStammtyp<0 || iStammtyp>0 && iStt !=iStammtyp))
		{
			if (iStammtyp != 0)
				iStt=iStammtyp;
			SQL_String();
		}
		//g.progInfo("getDaten:"+b);
		//PeriodenCheck();
                boolean bBewBew=iStamm<0;
                if (bBewBew)
                  iStamm=-iStamm;
                sBind=null;
                Vector<Integer> VecBewPool=getVecBewPool(sANR,iStamm);
//                if (iBew>0 && (lBits2&cstSQL2)>0)
//                {
//                	VecBewPool=SQL.getVector("select aic_bew_pool from bewview where aic_bewegungstyp="+iBew+" and anr="+iStamm,g);
//                	g.fixtestError("vorab Bew ermittelt: "+VecBewPool);              	
//                }
                if (bView2)
                  sSQL="select * from auv_"+sDefBez+((iBits&cstKeinStamm)>0 ? "":" where "+(iBew>0?"anr":"aic_stamm")+"="+Bind(iStamm));
                else
                  sSQL=VecAbf.elementAt(b ? 4:2)+
					(b ? ",stammpool p where p2.aic_stammtyp="+iStt+" and p.sta_aic_stamm=p2.aic_stamm and p.pro2_aic_protokoll is null and p.aic_stamm="+Bind(iStamm)+Ebenen():
					(!bSpalten || !bBewBew && (iBits&cstKeinStamm)>0 && !bUseVec ?"":VecBewPool != null ? " and p2.aic_bew_pool"+Static.SQL_in(VecBewPool):(sANR != null ?" and "+sANR						
							:bBewBew?" and p2.BEW2_AIC_BEW_POOL":iStammtyp >= 0?" and aic_stamm":" and p2.aic_bew_pool") +((iBits&cstMengen)>0 || bUseVec ? Static.SQL_in(VecStamm):"="+Bind(iStamm)))+
                                        /*checkFast(TabSpalten)+*/((iBits&cstLDATE)>0 && iBew>0?g.getLDATE():"")+VecAbf.elementAt(3));
		//g.progInfo("Vec3="+VecAbf.elementAt(3));
                //g.fixtestInfo("    # # # # #   Abf "+sDefBez+":"+sSQL);
                String s=checkJoker(sSQL);
                g.progInfo("getDaten("+iStamm+") von "+sBez+":"+s);
                Tab=new Tabellenspeicher(g,s,sBind,true);
                if (Tab !=null)
                {
                  if (Tab.fehler())
                  Static.printError("Abfrage "+sDefBez+" ist fehlerhaft (Stamm="+iStamm+", Mandant="+g.getMandant(0,"Kennung")+")");
                  pruefeDoppelte(Tab);
                
                  checkGleiche(Tab);
                  TabSpalten.moveFirst();
                  if ((iBits&cstSumme)>0 && !TabSpalten.isNull("Format") && TabSpalten.getS("Datentyp").equals("CalcString"))
                  {
                    String sK1=Sort.gets(getTitel(),0);
                    if (Tab.getS(sK1).equals(""))
                      Tab.setInhalt(sK1,TabSpalten.getS("Format"));
                  }
                  if((iBits&cstCompress)>0 && (iBits&cstWeiterfuehren)==0)
                    Tab.compress(TabSpalten.getS("Kennung"),false);
                  //g.fixInfo("iBew="+iBew+", iStt="+iStt+", KeinAustritt="+(iBits&cstKeinAustritt));
                  if ((bWeb2 || iBew==0) && iStt==g.iSttANR && (iBits&cstSumme)==0 && (iBits&cstKeinAustritt)==0 && g.TabAustritt != null && !g.TabAustritt.isEmpty())
                    removeAustritt(g,Tab,iBew>0);
                  else if (iBew>0 && (g.getBewBits(iBew)&Global.cstBerechtigung)>0)
                    removeBew(Tab);
                  checkHierarchie(TabSpalten, Tab);
                  if (sSort != null)
                    Tab.sort(sSort, true);
                  checkFilter(Tab,TabSpalten);
                  if(is(iBits, cstCalc))
                  {
                    checkCalc(Tab,TabSpalten);
                    checkVecBezeichnung();
                  }
                  if ((lBits2&cstDelZw)>0)
                  	removeEmpty(Tab);
                  if((iBits&cstCompress)>0 && (iBits&cstWeiterfuehren)>0)
                    Tab.compress(TabSpalten.getS("Kennung"),true);
                  if((lBits2&cstCompress2)>0)
                	Tab.compress2(TabSpalten,g.TabCodes);
                  //g.fixtestInfo("bits="+iBits+", bits2="+lBits2);
                  //System.gc();
                  checkWeiter(TabSpalten,Tab);
                  //System.gc();
                  long lDauer=Static.get_ms()-lClock;
                  if (lDauer>200)
                	  g.SaveVerlaufFertig(g.SaveVerlauf(iBegriff,iStamm,0),lDauer,false);             
                  clockCheck("Daten-", lClock, Tab);
                }
                else
                  clockCheck("getDaten-Fehler", Long.MIN_VALUE/*lClock-100000*/, Tab);
                if (bJahr && iVB==0)
                {
                	g.setVonBis(TSZeitraumVon,TSZeitraumBis,iVB);
                	Zeitraum.PeriodeToVec(g,g.getZA(iVB),true);
                }
		return Tab;
	}

        public void setViewSQL()
        {
          bSaveView=true;
          int iModellOld=iModell;
          iModell=0;
          SQL_String();
          iModell=iModellOld;
          bSaveView=false;
          g.progInfo("VecAbf="+VecAbf);
          sSQL=""+VecAbf.elementAt(2)+VecAbf.elementAt(3);
        }

	@SuppressWarnings("unchecked")
	public Tabellenspeicher getEF_Daten(int iBeg,int iStammtyp,int riRolle,int iStammVor,int iStamm,int iBewSatz,boolean bVecAic,boolean bBewVec,boolean bFirma,Vector VecAic,String sTyp,int iEig,boolean bZwingend,boolean bKopierbar,Window win)
	{
//		g.fixtestError("getEF_Daten von "+sDefBez+": Stamm="+iStamm+", Vec="+VecAic+", Typ="+sTyp);
		if (iBew>0 && !g.allowBew(iBew))
			return null;
		VecAic=FilterCheck(VecAic);
		g.checkVon();
//          if (bFirma)
//            g.testInfo(" ----- getEF_Daten <"+sDefBez+">:"+iStt+"/"+iStammtyp+"/"+riRolle+"/"+VecAic+"/"+iStamm);
          //g.progInfo("getEF_Daten:"+sDefBez+"/"+iStamm+"/"+VecAic+", Stt/Rolle="+iStt+"/"+iStammtyp+"/"+riRolle+",          Bew="+iBewSatz);
		  boolean bHaupt=sTyp != null && sTyp.equals("Haupt") || bBewVec;
          if(iStamm<=0 && (iBits & cstKeinStamm)==0 && !bHaupt && ((iBits&cstMengen)==0 || VecAic==null) && !bBewVec)
            return null;
          long lClock=Static.get_ms();
          Tabellenspeicher Tab=checkStammStichtag(iStamm,VecAic);
          if (Tab != null)
            return Tab;
    	  TSZeitraumVon=g.getVon(iVB);
    	  TSZeitraumBis=g.getBis(iVB); 
          boolean bJahr=(lBits2&cstAbfJahr)>0;
          setYear(bJahr);
//          if (bJahr)
//          {           
//        	  DateWOD DW=DateWOD.getNewYear(g.getVon(iVB));
//        	  Timestamp dtVon=DW.toTimestamp();
//        	  DW.nextYear();
//        	  Timestamp dtBis=DW.toTimestamp();
//        	  g.setVonBis(dtVon, dtBis,iVB);
//        	  Zeitraum.PeriodeToVec2(g,iVB,"x",true,false);
//        	  PeriodenCheck();
//        	  //g.fixtestError("Ändere Zeitraum von "+TSZeitraumVon+"-"+TSZeitraumBis+" auf "+g.getVon()+"-"+g.getBis());
//          }
          Vector VecStamm=g.getVecStamm(iBeg,iStammtyp);
          if (riRolle>0)
          {
            int iPos=g.TabRollen.getPos("AIC",riRolle);
            if (!(iPos>=0 && g.TabRollen.getI(iPos,"Stt")==iStammtyp))
              riRolle=0;
          }
          iQry=iStamm;
          //g.fixtestError("getEF_Daten:"+sDefBez+"/"+iStamm+"/"+VecAic+", Stt/Rolle="+iStt+"/"+iStammtyp+"/"+riRolle+",          Bew="+iBewSatz);
          if (!g.SuperUser() && (lBits2&cstSbRefresh)>0)
          	checkStammBerechtigung(g);
		if (iModell>0)// && Calc.Normal(g, iModell))
		{
                  if (iStamm<0)
                    iStamm=0;
			Vector<Integer> Vec=(iBits&cstMengen)>0 ? VecAic:Static.AicToVec(iStamm);
			if (Vec == null)
			{
				//java.util.Vector VecBS = iBew==0 ? g.SQL_CboStt(iStt):null;
				Vec=SQL.getVector("select aic_stamm from stammview p2 where"+(iRolle==0 && riRolle==0 ? " aic_stammtyp="+iStammtyp+" and aic_rolle is null":
                                    " aic_rolle="+(riRolle>0? riRolle:iRolle))+(bFirma ?" and firma="+g.getFirma():"")+g.getReadMandanten(),g);
			}
                        Integer IntStamm=new Integer(iStamm);
                        if(Vec.size()>1 && !Vec.elementAt(0).equals(IntStamm) && Vec.contains(IntStamm))
                        {
                          Vec=new Vector(Vec);
                          Vec.remove(Vec.indexOf(IntStamm));
                          Vec.insertElementAt(IntStamm,0);
                        }
			//g.progInfo("getEF_Daten: Vec="+Vec+"/ zwingend="+bZwingend);
                        g.checkModelle();
                        int iPosM=g.TabModelle.getPos("aic_modell",iModell);
                        int iBitsM=iPosM<0 ? 0:g.TabModelle.getI(iPosM,"bits");
                        if (AClient.UseAServer(iBitsM))
                          Tab=TCalc.getAbfrage(g,iModell,iBitsM,Vec,iAbfrage,null);
                        else
                        {
                          if (calc == null)
                            calc = new Calc(win, g, iModell, false, Vec, 0, null, 0);
                          else
                            calc.reCalc(Vec, bZwingend);
                          Tab = calc.getTab(iAbfrage);
                        }
                        if (bWeb2 && iStt==g.iSttANR && (iBits&cstKeinAustritt)==0 && g.TabAustritt != null && !g.TabAustritt.isEmpty())
                            removeAustritt(g,Tab,iBew>0);
                        if (Tab!=null && iBew==0)
                          Tab.clearWithVec("aic_stamm",VecStamm);
                        long lDauer=Static.get_ms()-lClock;
                        if (lDauer>200)
                      	  g.SaveVerlaufFertig(g.SaveVerlauf(iBegriff,iStamm,iBewSatz>0 ? iBewSatz:0),lDauer,false);
                        clockCheck("EF_D+",lClock,Tab);
                        //Tab.showGrid(iBeg+"/"+iStammtyp+"/"+iStt);
                        return Tab;
		}
		//g.progInfo("getEF_Daten: VecPerioden="+VecPerioden+"/"+Zeitraum.VecPerioden);
		if (bKopierbar && !bAlleMandanten || iStt!=iStammtyp)
		{
			bAlleMandanten=bKopierbar;
			iStt=iStammtyp;
                        if (riRolle > 0)
                          iRolle=riRolle;
//                        else if ((lBits2&cstGF)>0)
//                          iRolle=0;
			SQL_String();
		}
                else if (riRolle>0 && iRolle != riRolle)
                {
                  iRolle=riRolle;
                  SQL_String();
                }
		else
			PeriodenCheck();
		Vector<Integer> VecBewPool=getVecBewPool(sANR,iStamm);
		String s=//(iBits&cstView2)>0 ? "select * from auv_"+sDefBez+" where anr="+iStamm:
                    VecAbf.elementAt(iStammVor>0 && iBew==0 ? 4:2)+((iBits & cstKeinStamm)==0 && bVecAic && !bBewVec ?(sANR != null?" and "+sANR:" and aic_stamm")+Static.SQL_in(VecAic):"")+
                                                (bFirma ? " and firma="+g.getFirma():"")+ // bei Frima auf aktuelle Firma einschränken
						(iStammVor>0?",stammpool p where p2.aic_stammtyp="+iStt+" and p.sta_aic_stamm=p2.aic_stamm and p.aic_eigenschaft="+iEig/*(sTyp.equals("Haupt")?iEigenschaft2:TabOutliner.getI("Eig"))*/+" and p.pro2_aic_protokoll is null and p.aic_stamm="+iStammVor:
//							(iBits & cstKeinStamm)==0 && (iBew>0||sTyp.equals("Anzeige")||sTyp.equals("Gruppe"))?(sANR != null?" and "+sANR+"=":" and aic_stamm=")+iStamm:"")+((iBits&cstLDATE)>0 && iBew>0?g.getLDATE():"")+VecAbf.elementAt(3);
						iBew>0 && iBewSatz != -2 && (iBits&cstEinzeldaten)>0 ? " and aic_bew_pool="+iBewSatz:bBewVec && iBew>0 && VecAic != null && VecAic.size()>0 && bHaupt ? " and aic_bew_pool"+Static.SQL_in(VecAic): // 2011
							VecBewPool != null ? " and p2.aic_bew_pool"+Static.SQL_in(VecBewPool):
       (iBits & cstKeinStamm)==0 && (iBew>0||sTyp.equals("Anzeige")||sTyp.equals("Gruppe"))? (sANR != null ? " and "+sANR:" and aic_stamm")+(bWeb2 && bHaupt ? VecStamm==null ? " is not null":Static.SQL_in(VecStamm):(iBits&cstMengen)>0?Static.SQL_in(VecAic):"="+iStamm):"")+
						/*checkFast(TabSpalten)+*/((iBits&cstLDATE)>0 && iBew>0?g.getLDATE():"")+VecAbf.elementAt(3);
		s=checkJoker(s);
		g.progInfo("getEF_Daten("+iStamm+") von "+sBez+":"+s);
                Tab=new Tabellenspeicher(g,s,true);
                //Tab.showGrid(sDefBez);
                //g.fixtestInfo("    # # # # #   EF-Abf "+sDefBez+":"+s);
                if (Tab.fehler())
                {
                  Static.printError("Abfrage " + sDefBez + " ist fehlerhaft (Stamm=" + iStamm + ", Mandant=" + g.getMandant(0, "Kennung") + ")");
                  return null;
                }
                pruefeDoppelte(Tab);
                checkGleiche(Tab);
                checkWeiter(TabSpalten,Tab);
                checkHierarchie(TabSpalten,Tab);
                checkFilter(Tab,TabSpalten);
                if (is(iBits,cstCalc))
                  checkCalc(Tab,TabSpalten);
                if ((lBits2&cstDelZw)>0)
                	removeEmpty(Tab);
                if (iBew==0)
                  Tab.clearWithVec("aic_stamm",VecStamm);
                if ((bWeb2 || iBew==0) && iStt==g.iSttANR && (iBits&cstKeinAustritt)==0 && !g.TabAustritt.isEmpty())
                  removeAustritt(g,Tab,iBew>0);
                else if (iBew>0 && (g.getBewBits(iBew)&Global.cstBerechtigung)>0)
                  removeBew(Tab);
                if((lBits2&cstCompress2)>0) // 3.3.2021 laut Kurt immer && bWeb2)
                	Tab.compress2(TabSpalten,g.TabCodes);
                //  Tab.clearWithTab(TabAustritt);
                long lDauer=Static.get_ms()-lClock;
                if (lDauer>200)
              	  g.SaveVerlaufFertig(g.SaveVerlauf(iBegriff,iStamm,iBewSatz>0 ? iBewSatz:0),lDauer,false);
                clockCheck("EF_D-",lClock,Tab);
                if (bJahr && iVB==0)
                {
                	g.setVonBis(TSZeitraumVon,TSZeitraumBis,iVB);
                	Zeitraum.PeriodeToVec2(g,iVB,g.getZA(iVB),true,false);
                }
		return Tab;
	}

        public static void removeAustritt(Global g,Tabellenspeicher Tab,boolean bBew)
        {
          if (g.getVon()==null || g.getBis()==null)
            return;
          for(Tab.moveFirst();!Tab.eof();)
         {
           /*boolean bDa=true;
           int iAic=Tab.getI("aic_stamm");
           if (Tab.posInhalt("aic_stamm",iAic))
             for(;bDa && !Tab.eof() && Tab.getI("aic_stamm")==iAic;Tab.moveNext())
               bDa=g.getVon().before(Tab.getTimestamp("aus")) || !Tab.isNull("ein") && g.getBis().after(Tab.getTimestamp("ein"));*/
           if (istDa(g,Tab.getI(bBew ? "anr":"aic_stamm")))
             Tab.moveNext();
           else
             Tab.clearInhalt();
         }
        }

      private void removeBew(Tabellenspeicher Tab)
      {
        //Tab.showGrid(sDefBez);
   	  if ((lBits2&cstNoSB)>0 && bWeb2)
   		  return;
	Vector Vec=g.getVecStamm2(g.getSttANR(iBew));
	//g.fixInfo("removeBew:"+iStt+"/"+iBew+"/"+Vec);
	if (Vec==null)
	  return;
        if (Tab.exists("anr"))
         for(Tab.moveFirst();!Tab.eof();)
         {
          if (Tab.getI("anr")==0 || Vec.contains(Tab.getInhalt("anr"))) // korr. 23.9.2014: 3025
            Tab.moveNext();
          else
          {
            //g.fixInfo("entfernte "+Tab.getInhalt("anr")+"/"+g.getStamm(Tab.getI("anr")));
            Tab.clearInhalt();
          }
         }
      }
      
      private void removeEmpty(Tabellenspeicher Tab)
      {
    	  Vector<String> Vec=new Vector<String>();
    	  Vector Vec2=getTitel();
    	  for(int i=0;i<TabSpalten.size();i++)
    		if ((TabSpalten.getI(i,"bits") & (Global.cstAlways * 0x10000)) > 0)
    		{
    			Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr",i);
                int iAnz=!bAlleSpalten && (TabSpalten.getI(i,"bits")&cstUnsichtbar)>0?0:VecMehr==null?1:VecMehr.size();
//                if (iAnz==0)
//                  i2++;
                for(int iPer=0;iPer<iAnz;iPer++)
                {
                  String s=Vec2 == null ? VecMehr != null ? "e" + TabSpalten.getS(i,"Kennung2") + VecMehr.elementAt(iPer) : TabSpalten.getS(i,"Kennung") : Vec2.elementAt(i + iPer).toString();
                  Vec.addElement(s);
                }
    		}
    	  g.fixtestInfo("removeEmpty:"+Vec);
    	  for (Tab.moveFirst();!Tab.eof();)
    	  {
    		  boolean bDel=true;
    		  for (int i=0;i<Vec.size();i++)
    		  {
    			String sSpaltBez=Sort.gets(Vec, i);
    			if (!Tab.exists(sSpaltBez))
    				sSpaltBez="v"+sSpaltBez.substring(1);
    			//g.fixtestInfo("prüfe Spalte "+sSpaltBez);
    			if (!Tab.isNull(sSpaltBez))
    			  bDel=false;
    		  }
    		  if (bDel)
    			  Tab.clearInhalt();
    		  else
    			  Tab.moveNext();
    	  }
        	  
      }

        public static boolean istDa(Global g,int iAic)
        {
          boolean bDa=true;
          if (g.getVon()==null || g.getBis()==null)
            return true;
          int iPos=g.TabAustritt.getPos("aic_stamm",iAic);
          //int iAnz=g.TabAustritt.size();
          if (iPos>=0)
            for(;bDa && iPos<g.TabAustritt.size() && g.TabAustritt.getI(iPos,"aic_stamm")==iAic;iPos++)
              bDa=g.getVon().before(g.TabAustritt.getTimestamp(iPos,"aus")) /*!g.TabAustritt.getTimestamp(iPos,"aus").before(g.getBis())*/
              || g.TabAustritt.getInhalt("ein",iPos)!=null && g.getBis().after(g.TabAustritt.getTimestamp(iPos,"ein"));
          return bDa;
        }

        private void clockCheck(String s,long lClock,Tabellenspeicher Tab)
        {
          boolean bFehler=lClock==Long.MIN_VALUE;
          lClock=bFehler ? 0:Static.get_ms()-lClock;
          if (bFehler || Static.lAbfAb==-1 || Static.lAbfAb>0 && lClock>=Static.lAbfAb)
          {
            //String sAbfrage=SQL.getString(g,"select defbezeichnung from begriff where aic_begriff="+iBegriff);
            String sWo=iBew>0 ? "Bew "+g.getBezeichnungS("Bewegungstyp",iBew):"Stt "+g.getBezeichnungS("Stammtyp",iStt);
            String s2=s +"/"+sDefBez+"/"+sWo+": " + lClock + " ms für "+(Tab==null ? "Fehler":Tab.getAnzahlElemente(null)+" Zeilen");
            g.defInfo(s2);
            g.saveSqlTime(s,0,lClock,sWo+"."+sDefBez,(Tab==null?-1:Tab.getAnzahlElemente(null)),iQry);
            /*if (lClock>59999)
            {
              g.diskInfo(s2);
              if (g.Def() && Tab !=null)
                Tab.showGrid(sWo+"."+sAbfrage+": " + lClock + " ms");
            }*/
          }
        }

        private String getSp()
        {
          if (is(iBits,cstDistinct))
          {
            String s="null aic_"+(iBew==0 ?"stamm":"Bew_Pool");
            for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
            {
              String sDT=TabSpalten.getS("Datentyp");
              String sK=TabSpalten.getS("Kennung2");
              int iDArt=TabSpalten.getI("bits3")&cstDatenHolen;
              if (VVonBis.contains(sDT))
                s+=",v"+sK+",b"+sK+(sDT.equals("BewVon_Bis")?",d"+sK:"");
              else if (sDT.endsWith("Bool3"))
                s+=",e"+sK+",v"+sK;
              else if (iDArt==cstNurAicD)
                s+=",d"+sK;
              else if (iDArt==cstDanachD)
                s+=",d"+sK+",null e"+sK;
              else
              {
            	  s+=",e"+sK;
	              if (VVerkn.contains(sDT))
	                s+=",v"+sK;
	              if (sDT.endsWith("Hierarchie"))
	                s+=",w"+sK;
              }
            }
            return s;
          }
          else
            return "*";
        }

        public void setSort(int riSort)
        {
          iSort=riSort;
          if (iSort>0)
            SQL_String();
        }

        public String PeriodenTitel(int i)
        {
          if (g.TabPerioden==null)
          {
//        	  String sZA=g.getZA(iVB);
//        	  Timestamp TS=g.getVecPer(iVB).elementAt(i);
//        	  String s=Static.FormatTS(TS,sZA);
//        	  g.fixtestError("PeriodenTitel "+i+": "+s+" bei "+sZA+"/"+TS);
//        	  return s;
            return Static.FormatTS(g.getVecPer(iVB).elementAt(i),g.getZA(iVB));
          }
          else
            return g.TabPerioden.getS(i,"Titel");
        }

        public boolean Sichtbar(int iPosS)
        {
          if (TabSpalten.size()<=iPosS)
        	  return false;
          return bAlleSpalten || (TabSpalten.getI(iPosS,"bits")&cstUnsichtbar)==0 && (bMulti || (TabSpalten.getI(iPosS,"bits2")&cstMulti)==0);
        }

        public void checkVecBezeichnung()
        {
          VecAusrichtung=new Vector<Integer>();
          VecBezeichnung=new Vector<String>();
          for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
            if(bAlleSpalten || Sichtbar(TabSpalten.getPos()) || (iBits&cstEinzeldaten)>0)
          {
                  for(int i=TabSpalten.getInhalt("mehr")==null?1:((Vector)TabSpalten.getInhalt("mehr")).size()+(PerZS() ? 1:0);i>0;i--)
                          VecAusrichtung.addElement(new Integer(g.getAlignment(TabSpalten.getS("Datentyp"),TabSpalten.getI("Ausrichtung"))));

                  if (TabSpalten.getInhalt("mehr")!=null)
                  {
                          int iAnz=((Vector)TabSpalten.getInhalt("mehr")).size();
                          if ((TabSpalten.getI("bits2")&cstVorZR)>0)
                          {
                            VecBezeichnung.addElement(TabSpalten.getS("Bezeichnung")+" -"+Static.FormatTS(new Date(g.getVon().getTime()-1),g.getZA(iVB)));
                            VecBezeichnung.addElement(TabSpalten.getS("Bezeichnung")+" "+Static.FormatTS(new Date(g.getBis().getTime()-1),g.getZA(iVB)));
                          }
                          else
                            for(int i=0;i<iAnz;i++)
                                  VecBezeichnung.addElement(TabSpalten.getInhalt("Gruppe") == null ? TabSpalten.getS("Bezeichnung")+" "+
                                                            ((TabSpalten.getI("bits2")&cstGanzesJahr)>0?DateWOD.DFS.getShortMonths()[i]+"_"+new SimpleDateFormat("yy").format(g.getVon()):PeriodenTitel(i)) :
                                                            ((jclass.bwt.JCOutlinerNode)((Vector)TabSpalten.getInhalt("Gruppe")).elementAt(i)).getLabel().toString());

                          if (PerZS())
                                  VecBezeichnung.addElement(BezSumme((TabSpalten.getI("bits3")&cstPerSchnitt)>0));
                  }
                  else
                          VecBezeichnung.addElement(TabSpalten.getS("Bezeichnung"));
          }
          if (!bExport)
                  for(int i=0;i<VecBezeichnung.size();i++)
                          VecBezeichnung.setElementAt(Static.cutString(VecBezeichnung.elementAt(i)),i);
          //g.progInfo("VecAusrichtung="+VecAusrichtung);
            //g.progInfo("VecBezeichnung="+VecBezeichnung);
        }
        
        private boolean PerZS() // liefert ob Zusatzspalte für Periodensumme nötig
        {
        	return (TabSpalten.getI("bits")&cstPeriodensumme)>0 || (TabSpalten.getI("bits3")&cstPerSchnitt)>0;
        }

        private String BezSumme(boolean bDS)
        {
          // TabSpalten.getS("Bezeichnung")+" "+g.getBegriffS("Show","Summe")
          String s=g.getBegriffS("Show",bDS?"Durchschnitt":"Summe");
          int iAnz=0;
          for (int i=0;i<TabSpalten.size();i++)
            if (PerZS())
              iAnz++;
          if (iAnz>1)
            s=TabSpalten.getS("Bezeichnung")+" "+s;
          return s;
        }

        public Tabellenspeicher refreshTabSpalten()
        {
          VecEigenschaften=new Vector();
          TabSpalten = getAnzeigeSpalten(VecEigenschaften);
          return TabSpalten;
        }

	public void SQL_String()
	{
          //g.fixtestInfo("SQL_String für "+sDefBez);
		//long lClock=Static.get_ms();
		//g.progInfo("SQL_String von "+iAbfrage);
		VecPerioden=null;
		VecAbf=new Vector<Object>();

		VecEigenschaften=new Vector();
		//JCOutlinerFolderNode NodSpalten=findNode(Nod,false);
		TabSpalten = getAnzeigeSpalten(VecEigenschaften);

		if (bSpalten)
                  checkVecBezeichnung();
		VecAbf.addElement(TabSpalten);
		if (iModell>0/* && Calc.Normal(g, iModell)*/ && !bHistory && (iBits&cstSumme)==0)
			return;

		//boolean bSumme= is(iBits,cstSumme);
		//boolean bStt=is(iBits,cstKeinStt);
		//boolean bDistinct=is(iBits,cstDistinct);
                boolean bKeinStt=/*iStt==0 ||*/ (iBits&Abfrage.cstKeinStt)>0;
                boolean bDel=bEntfernte || is(iBits,cstEntfernte);
        String sCheck2=Check(VecEigenschaften,NodVBed.getChildren()," ",true);
		String sCheck=bBedingung && !bHistory ? Check(VecEigenschaften,NodBed.getChildren(),"",false/*"p2.aic_"+(iBew==0?"stamm":"bew_pool,p2.gueltig"+(bDel?",pro_aic_protokoll":""))*/):"";
                //bVB=true;
                
                //g.fixtestInfo("VecEigenschaften nach Check:"+VecEigenschaften);
                //bVB=false;
                //if (!sCheck.equals(""))
                //  g.progInfo("ShowAbfrage.SQL_String: sCheck="+sCheck);
                //if (!sCheck2.equals(" "))
                //  g.progInfo("ShowAbfrage.SQL_String: sCheck2="+sCheck2);
		checkAbfJoker();
                boolean bCheck=!sCheck.equals("");
		//String sAbfrage= (iStamm==0?"":" and aic_stamm="+iStamm)+g.getReadMandanten(false)+sCheck;
		if (!bAlleMandanten)
		{
			if (iBew>0)
				bAlleMandanten=true;
			else
			{
				int iPos=g.TabStammtypen.getPos("Aic",iStt);
				bAlleMandanten=iPos<0 ? true:(g.TabStammtypen.getI(iPos,"bits")&Global.cstCopy)==0;
			}
		}
                boolean bLeer=(iBits&cstLeer)>0;
                String sNach=(/*bSaveView ? "":*/(lBits2&cstKeinMandant)>0 ? "":bAlleMandanten ? g.getReadMandanten(iBew):g.getCopyMandanten(false)/*" and aic_mandant="+g.getMandant()*/)+
                    (bDel && !bLeer ? iBew==0 ? " and aic_code="+g.getCodeAic("Anlage","verdichtet") : /*iBew>0 &&*/ bKeinStt ? "":
                     " and (pro_aic_protokoll is null or pro_aic_protokoll>=1"+/*g.getFirstProt()+(iBew>0 && bKeinStt ?")":*/" and (select count(aic_bew_pool) from bew_pool where bew_aic_bew_pool=p2.aic_bew_pool)=0)":"");

		//java.util.Vector VecBS = iBew==0 ? g.SQL_CboStt(iStt):null;
                sANR=null;
                if (/*(iBits&cstANR)>0 &&*/(iBits&cstKeinStamm)==0 && iBew>0)// && (Transact.iInfos&Transact.NO_SPEED)==0)
                {
                  sANR=g.getANR_BS(iBew,iStt);
                  // g.fixtestError("ANR bei "+iBew+"/"+iStt+": "+sANR);
                  if (sANR==null && !bLeer)
                    if (g.bISQL)
                      ;//Static.addError("ANR wird nicht verwendet");
                    else
                      g.defInfo2("ANR wird bei "+sDefBez+" nicht verwendet!");
                }
                if (is(iBits,cstLDATE) && iBew==0)
                  g.printError("LDATE bei Stammabfrage "+sDefBez+" nicht zulässig!",iBegriff);
                int iT=is(iBits,cstFirst)?iTop<2?1:iTop:-1;
                boolean bKZR=is(iBits,cstKeinZeitraum) || is(iBits,cstLDATE) && iBew>0 && !bSaveView;
                //g.fixtestInfo("VecEigenschaften1:"+VecEigenschaften);
                String sX=(iBew==0) ? ZusaetzlicheSpalten(VecEigenschaften,Formular.Stamm,bCheck)+(TabSpalten.isEmpty()?",bezeichnung":"")+
                        " from Stammview"+(is(iBits,cstKeinStammZeitraum) ? bDel? "4":"2":bDel? "3":iVB>0 ? ""+iVB:"")+" p2":
			                  ZusaetzlicheSpalten(VecEigenschaften,Formular.Bewegung,bCheck)+" from "+(bDel ? bKZR ?"Bew_Pool":"BewView"+(iVB>0 ? iVB+"d":"3"): bKZR ?"BewView2":iVB>0 ? "BewView"+iVB:"BewView")+
			                  " p2"+(is(iBits,cstKeinStamm) || iStt<0 || sANR != null ?"":g.join("Bew_Stamm","p2","Bew_Pool"))+" where p2.aic_bewegungstyp="+(bLeer?0:iBew);
                String sVor=g.topA(iT,is(iBits,cstDistinct)/*,(iBits&cstNoRule)==0*/)+getSp()+" from (SelecT "+sX;
                //g.fixtestInfo("VecEigenschaften2:"+VecEigenschaften);
                //for (int i=1;i<=iEbenen;i++)
                //  sNach+=") E"+i;
                sNach+=sCheck2+Ebenen()+sCheck;
		Vector<Object> Vec=null;
                Vector VecRolle=(iBits&cstSubrollen)>0 ? g.getVecRolle2(iRolle>0?iRolle:-iStt,false):null;
                String sRolle=VecRolle==null ? iRolle>0 ? " and aic_Rolle="+iRolle:" and aic_Rolle is Null":" and aic_rolle"+Static.SQL_in(VecRolle);
                //g.progInfo("sRolle:"+sRolle);
		String s = sVor+(iBew==0?" where "+(is(iBits,cstKeinStt) ? "1=1":"aic_stammtyp="+(bLeer ? 0 : iStt))+sRolle:"");
		if (bSpalten)
		{
			if (is(iBits,cstSumme))
			{
				Vec=new Vector<Object>();
				String sSum="";
				for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
				{
					int iErgArt=TabSpalten.getI("Ergebnisart");
                                        int iPos=iErgArt>0 ? g.TabCodes.getPos("Aic",iErgArt):-1;
					String sErgArt = iErgArt==0 || iPos<0 ? "sum":g.TabCodes.getS(iPos,"Kennung");
					//boolean bGruppe=TabSpalten.getInhalt("Gruppe") != null;
					//boolean bPeriode=(TabSpalten.getI("bits")&Abfrage.cstPeriode)>0  && Zeitraum.VecPerioden!=null;
					//int iAnz=bGruppe ? ((Vector)TabSpalten.getInhalt("Gruppe")).size():bPeriode ? Zeitraum.VecPerioden.size()-1:1;
					Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr");
					int iAnz=VecMehr==null?1:VecMehr.size();
					for(int i=0;i<iAnz;i++)
					{
						String sKennung=VecMehr!=null ? "e"+TabSpalten.getS("Kennung2")+VecMehr.elementAt(i):
							(TabSpalten.getS("Datentyp").equals("BewVon_Bis")?"d":"")+TabSpalten.getS("Kennung");
                                                //if (VecMehr==null)
                                                //      TabSpalten.setInhalt("Kennung",sErgArt+"_"+sKennung);
						sSum+=","+sErgArt+"("+sKennung+") "+sErgArt+"_"+sKennung;
						Vec.addElement(sErgArt+"_"+sKennung);
						//VecSpalten.addElement(Abfrage.TabToScreen(g,sAic,Tab,TabSpalten,bPeriode));
					}
					//String sKennung=(TabSpalten.getS("Datentyp").equals("BewVon_Bis")?"d":"")+TabSpalten.getS("Kennung");

				}
				s="select 1 sum"+sSum+" from ("+s;
				sNach+=") xxx";
				//g.progInfo("SQL_String.Vec="+Vec);
			}
			else
				sNach+=(bSaveView ? "":TabSpalten.isEmpty()?iBew==0?" order by Bezeichnung":"":
                                        iSort>0 && TabSpalten.posInhalt("Nummer",iSort) ? " order by "+TabSpalten.getS("Kennung"):Sortiert())+g.topE(iT);
		}
		else
			sNach+=iBew==0?" order by Bezeichnung"+g.topE(iT):"";
		VecAbf.addElement(Vec);  // 1
		//g.progInfo("s=<"+s+">");
		VecAbf.addElement(s);    // 2
		VecAbf.addElement(sNach);// 3
		VecAbf.addElement(sVor); // 4
                //g.fixtestInfo("sNach="+sNach);
		//g.testInfo("SQL_String:"+(Static.get_ms()-lClock)+"ms");
	}

	public int[] getAusrichtung()
	{
		return getAusrichtung(0);
	}

	public int[] getAusrichtung(int i1)
	{
          //g.progInfo("VecAusrichtung="+VecAusrichtung);
          if (VecAusrichtung==null)
            return null;
		int iAnz=VecAusrichtung.size();
		int iRet[] = new  int[iAnz+i1];
                for(int i=0;i<i1;i++)
                  iRet[i]=0;
		for(int i=i1;i<iAnz+i1;i++)
                  iRet[i]=VecAusrichtung.elementAt(i-i1).intValue();
		//g.progInfo("VecAusrichtung="+VecAusrichtung);
		return iRet;
	}

	@SuppressWarnings("unchecked")
	public Vector getBezeichnung()
	{
		//PeriodenCheck();
		return new Vector(VecBezeichnung);
	}
	
	public static Object doubleToScreen(Global g,double d, Tabellenspeicher TabSpalten)
	{
		return doubleToScreen(g,d,TabSpalten,0);
	}

	public static Object doubleToScreen(Global g,double d, Tabellenspeicher TabSpalten,int iMass)
	{
		//TODO bei Original-Spalten anders
        if (TabSpalten.getS("Format").endsWith("%"))
          return new Zahl1(d, TabSpalten.getS("Format"));
		String sDatentyp = TabSpalten.getS("Datentyp");
		String sKZ=TabSpalten.getS("Stil");
        boolean bKeineEinheit= (TabSpalten.getI("Bits")&cstKeineEinheit)>0;
		if (sDatentyp.equals("BewVon_Bis"))
			if (sKZ==null || !sKZ.equals("von") && !sKZ.equals("bis"))
			  return new VonBis(null,null,d,Static.beiLeer(TabSpalten.getS("Format"),"HH:mm"),getLaenge(TabSpalten),g,TabSpalten.getI("Stamm"),bKeineEinheit);
			else
			  return new Zeit(d,TabSpalten.getS("Format"));
		else if (sDatentyp.equals("Double") || sDatentyp.equals("BenMass") || sDatentyp.equals("BewDauer") || sDatentyp.startsWith("BewZahl") || sDatentyp.equals("CalcField"))
			return new Zahl1(d,TabSpalten.getS("Format"));
		else if (sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
			return new Waehrung(g.getVecWaehrung(bKeineEinheit,TabSpalten.getI("Stamm")),new Double(d),TabSpalten.getS("Format"));
		else if (sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcDauer"))
			return new Mass(g,g.getMass((iMass>0 && (TabSpalten.getI("bits2") & cstOrigEinh) > 0 ? iMass:TabSpalten.getI("Stamm")),bKeineEinheit),new Double(d),TabSpalten.getS("Format"));
		else if (sDatentyp.equals("Integer") || sDatentyp.equals("SysAic") || sDatentyp.equals("Aic") || sDatentyp.equals("BewCount") ||
			sDatentyp.equals("Boolean") || sDatentyp.equals("BewBoolean") || sDatentyp.equals("Vorgaenger") || sDatentyp.equals("Favorit"))
			return new Integer(new Double(d).intValue());
		else if (sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt") ||sDatentyp.equals("Datum"))
			return new Zeit(d,Static.beiLeer(TabSpalten.getS("Format"),"dd.MM.yyyy"));
		else if (sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("Zeit") || sDatentyp.equals("Timestamp"))
			return new Zeit(d,TabSpalten.getS("Format"));
		else
			return null;
	}

        public void showResult(Window Dlg)
        {
          iBits=iBits | cstKeinStamm;
          Tabellenspeicher Tab=getDaten(0,0,null,Dlg);
          if (Tab != null)
          {
            //Tab.showGrid(CboFilter.Cbo.getSelectedBezeichnung(), Dlg);
            JDialog DlgTab=Dlg instanceof JFrame ? new JDialog((JFrame)Dlg,Static.bDefBezeichnung ? sDefBez:sBez,true):new JDialog((JDialog)Dlg,Static.bDefBezeichnung ? sDefBez:sBez,true);
            DlgTab.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            AUOutliner GidGesamt = new AUOutliner();
            initOutliner(g, GidGesamt);
            TabToOutliner(GidGesamt, Tab, null);
            DlgTab.getContentPane().add("Center", GidGesamt);
            DlgTab.pack();
            Static.centerComponent(DlgTab,Dlg);
            DlgTab.setVisible(true);
          }
          else
            g.fixInfo(sDefBez+" liefert null!");
        }

	public static void initOutliner(Global g,AUOutliner Out)
	{
		JCOutlinerFolderNode NodRoot=new JCOutlinerFolderNode("");
		NodRoot.setStyle(g.getStyle(null));
		Out.setRootNode(NodRoot);//);
		//Out.setColumnLabelSortMethod(Sort.sortMethod);
		//Out.setBackground(java.awt.Color.white);
        Out.setBackground(g.ColBackground);
		Out.setRootVisible(false);
		//Out.setFont(g.fontStandard); //davor fontBezeichnung);
	}

        // Drucküberschrift zusammensetzen
        public String TabToString(Tabellenspeicher TabDaten)
        {
          String s=null;
          //TabSpalten.showGrid("TabSpalten in TabToString");
          if (TabDaten==null || TabDaten.isEmpty())
            return null;
          if (TabDaten.out())
            TabDaten.moveFirst();
          if (TabSpalten.size()==0 && TabDaten.exists("Bezeichnung"))
        	  return TabDaten.getS("Bezeichnung");
          for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
            if ((TabSpalten.getI("bits")&(cstUnsichtbar))==0) // 28.8.2018 cstSteuern entfernt
            {
              Object s2 = TabToScreen(g,TabSpalten.getS("Kennung"),TabDaten,TabSpalten,false);
              if(s2!=null)
                if(s==null)
                  s = s2.toString();
                else
                  s += " "+s2;
            }
          //TabDaten.showGrid("TabToString");
          //g.progInfo("TabToString="+s);
          return s;
        }

        private JCSortable sortDate = new JCSortable()
        {
                public long compare(Object obj1, Object obj2)
                {
                        long comparing = 0;
                        if(obj1==null || obj2==null)
                                comparing = obj1==null?JCSortable.LESS_THAN:JCSortable.GREATER_THAN;
                        else if(obj1 instanceof Date && obj2 instanceof Date)
                                comparing = ((Date)obj1).after((Date)obj2)?JCSortable.GREATER_THAN:JCSortable.LESS_THAN;
                        else
                                g.printError("ShowAbfrage.sortDate: Falscher Typ bei sortDate!",iBegriff);

                        return comparing;
                }
        };

        public Vector<Object> TabToOutliner(AUOutliner Out,Tabellenspeicher TabDaten,JCOutlinerFolderNode NodP)
        {
          return TabToOutliner(Out,TabDaten,NodP,null,1);
        }

        /*public void TabToOutliner(JCOutliner Out,Tabellenspeicher TabDaten,JCOutlinerFolderNode NodP,Vector VecE)
        {
          TabToOutliner(Out,TabDaten,NodP,VecE,1);
        }

        public void TabToOutliner(JCOutliner Out,Tabellenspeicher TabDaten,JCOutlinerFolderNode NodP,Vector VecE,int iSpalten)
        {
          TabToOutliner(Out,TabDaten,NodP,VecE,iSpalten,0);
        }*/
        
    private JCOutlinerNode addTabD(Vector Vec,int iAic,JCOutlinerFolderNode NodP,boolean bFolder,int iStt)
    {
    	JCOutlinerNode Nod=bFolder ? new JCOutlinerFolderNode((Object)Vec, NodP):new JCOutlinerNode(Vec, NodP);
    	if (Static.bND && bFolder)
        {
      	  g.fixtestError("Gruppiert: "+TabSpalten.getS("Datentyp")+"/"+TabSpalten.getS("Kennung")+", Stt="+iStt);
      	  Nod.setStyle(iStt>0 ? g.getSttStyle(iStt):g.getStyle(null));
        }
        
//    	if (bJavaFX && ((lBits2&cstNullWeg)==0 || iAic>0))
//    	{
//    		//g.fixtestError("addTabD: JavaFX="+bJavaFX+", NullWeg="+(lBits2&cstNullWeg)+", Aic="+iAic+", Level="+NodP.getLevel()+", Vec="+Vec);
//    		TabD.addInhalt("Vec", Vec);
//    		TabD.addInhalt("Aic", iAic);
//    		TabD.addInhalt("Node", Nod);
//    		if ((lBits2&cstNullWeg)>0)
//    		  while(NodP.getLevel()>0 && TabD.getPos("Node",NodP)<0)
//    			  NodP=NodP.getParent();
//    		TabD.addInhalt("P", NodP);
//    		TabD.addInhalt("Tree",null);
//    		TabD.addInhalt("Stt",iStt);
//    	}
    	return Nod;
    }

	@SuppressWarnings("unchecked")
	public Vector<Object> TabToOutliner(AUOutliner Out,Tabellenspeicher TabDaten,JCOutlinerFolderNode NodP,Vector VecE,int iSpalten)
	{
          Static.setMaxError(10);
          //TabSpalten.showGrid("TabSpalten");
          //if (g.Prog() && g.Debug())
          //  TabDaten.showGrid();
//          if (bJavaFX)
//        	  TabD=new Tabellenspeicher(g,new String[]{"Vec","Aic","Node","P","Tree","Stt"});
          boolean bEmpty=TabSpalten.isEmpty();
		if (bEmpty && iBew==0)
		{
			//Static.printError("Abfrage.TabToOutliner: keine Spalten vorhanden!");
			//return;
                        VecAusrichtung=new Vector<Integer>();
                        VecAusrichtung.addElement(new Integer(0));
                        VecBezeichnung=new Vector<String>();
                        VecBezeichnung.addElement(g.getBegriffS("Show","Bezeichnung"));
                        TabSpalten.newLine();
                        TabSpalten.setInhalt("Bezeichnung",g.getBegriffS("Show","Bezeichnung"));
                        TabSpalten.setInhalt("Kennung","Bezeichnung");
                        TabSpalten.setInhalt("Datentyp","Bezeichnung");
                        TabSpalten.setInhalt("bits",cstAnzeigen);
		}

		//JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
		//JCOutlinerFolderNode NodP=NodeRoot;
		if (Out==null)
			Out=new AUOutliner(new JCOutlinerFolderNode(""));
		   if (Out!=null)
		   {
                if (NodP==null)
                  NodP=(JCOutlinerFolderNode)Out.getRootNode();
                NodRoot=NodP;
                Out.setBackground(g.ColBackground);
                Out.setAllowMultipleSelections(true);
		   }
		//NodeRoot.removeChildren();
                if ((iBits&cstStammstichtag)>0)
                {
                  //TabDaten.showGrid();
                  jclass.util.JCVector VecST=new jclass.util.JCVector();
                  Static.addVector(VecST,TabDaten.getVecSpalte("gultig_von"));
                  Static.addVector(VecST,TabDaten.getVecSpalte("Gueltig_Bis"));
                  VecST.sort(sortDate);
                  VecTST=new Vector<Object>();
                  VecTST.addElement(g.getBegriffS("Show","Bezeichnung"));
                  for (int i=0;i<VecST.size();i++)
                    VecTST.addElement(new Zeit((Date)VecST.elementAt(i),"dd.MM.yyyy"));
                  if (Out!=null)
                  {
                    Out.setColumnButtons(new jclass.util.JCVector(VecTST));
                    Out.setNumColumns(VecTST.size());
                  }
                  Vector<Integer> VecStamm=TabDaten.getVecD("Aic_Stamm");
                  g.fixtestError("VecStamm bei Stammstichtag="+VecStamm);
                  TabDaten.sGruppe="aic_stamm";
                  TabDaten.sAIC="aic_eigenschaft";
                  for (int iS=0;iS<VecStamm.size();iS++)
                  {
                   int i2=0;                  
                   int iStamm=Sort.geti(VecStamm,iS);
                   for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                   {
                    Vector Vec = new Vector();
                    Vec.addElement(Static.replaceString(VecBezeichnung.elementAt(i2), "\n", " "));
                    int iEig=TabSpalten.getI("Kennung2");
                    String sDatentyp=TabSpalten.getS("Datentyp");
                    boolean bKeineEinheit= (TabSpalten.getI("Bits")&cstKeineEinheit)>0;
                    for (TabDaten.moveFirst();!TabDaten.out();TabDaten.moveNext())
                      if (TabDaten.isNull("Gultig_Von"))
                        TabDaten.setInhalt("Gultig_Von",new java.sql.Timestamp(0));
//                    if (g.Prog())
//                      TabDaten.showGrid("TabDaten");
                    if (TabDaten.posInhalt(iStamm,iEig))
                    {
                      Object Obj = null;
                      for (int i = 0; i < VecST.size(); i++)
                      {
                        Date dt = (Date)VecST.elementAt(i);
                        if (dt==null)
                          dt=new java.sql.Timestamp(0);
//                        g.progInfo("dt="+dt+", "+TabDaten.getInhalt("Gultig_Von")+"-"+TabDaten.getInhalt("Gueltig_Bis"));
                        if (dt!=null && TabDaten.isNull("Gultig_Von") || !TabDaten.isNull("Gueltig_Bis") && !TabDaten.getDate("Gueltig_Bis").after(dt))
                          TabDaten.moveNext();
                        if (TabDaten.getI("AIC_eigenschaft")==iEig && (dt==null && TabDaten.isNull("Gultig_Von") || TabDaten.getDate("Gultig_Von").equals(dt)))
                        {
                          if (sDatentyp.equals("Waehrung"))
                            Obj=new Waehrung(g.getVecWaehrung(bKeineEinheit,TabSpalten.getI("Stamm")), TabDaten.getInhalt("Spalte_Double"), TabSpalten.getS("Format"));
                          else if (sDatentyp.endsWith("Mass"))
                            Obj=new Mass(g,g.getMass(TabSpalten.getI("Stamm"),bKeineEinheit),new Double(TabDaten.getF("Spalte_Double")*g.getFaktor(TabDaten.getI("sta_aic_stamm"))),TabSpalten.getS("Format"));
                          else if (sDatentyp.equals("Double") || sDatentyp.equals("BenMass"))
                            Obj=new Zahl1(TabDaten.getInhalt("Spalte_Double"),TabSpalten.getS("Format"));
                          else if (sDatentyp.equals("Integer"))
                            Obj=TabDaten.getInt("Spalte_Double");
                          else if (sDatentyp.equals("Boolean"))
                            Obj=Static.JaNein(TabDaten.getI("Spalte_Double")>0);
                          else if (sDatentyp.equals("Gruppe") || sDatentyp.equals("Hierarchie"))
                            Obj=SQL.getString(g,"select bezeichnung from stammview where aic_rolle is null and aic_stamm="+TabDaten.getI("sta_aic_stamm"));
                          else if (sDatentyp.startsWith("String"))
                              Obj=SQL.getString(g,"select spalte_StringX from daten_StringX where aic_daten="+TabDaten.getI("aic_daten"));
//                          else if (sDatentyp.equals("StringSehrKurz") || sDatentyp.equals("Stringx") && TabDaten.getI("spalte_double")<=10)
//                            Obj=SQL.getString(g,"select spalte_StringSehrKurz from daten_StringSehrKurz where aic_daten="+TabDaten.getI("aic_daten"));
//                          else if (sDatentyp.equals("StringKurzOhne") || sDatentyp.equals("StringKurz") || sDatentyp.equals("Stringx") && TabDaten.getI("spalte_double")<=30)
//                            Obj=SQL.getString(g,"select spalte_stringkurz from daten_stringkurz where aic_daten="+TabDaten.getI("aic_daten"));
//                          else if (sDatentyp.equals("String60") || sDatentyp.equals("E-Mail") || sDatentyp.equals("FixDoku") || sDatentyp.equals("Stringx") && TabDaten.getI("spalte_double")<=60)
//                            Obj=SQL.getString(g,"select spalte_string60 from daten_string60 where aic_daten="+TabDaten.getI("aic_daten"));
//                          else if (sDatentyp.equals("StringLang") || sDatentyp.equals("WWW") || sDatentyp.equals("Memo") || sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") || sDatentyp.equals("Stringx") && TabDaten.getI("spalte_double")<=255)
//                            Obj=SQL.getString(g,"select spalte_stringlang from daten_stringlang where aic_daten="+TabDaten.getI("aic_daten"));
                          else
                            Obj=null;

                          Vec.addElement(Obj);
                        }
                        else
                          Vec.addElement((iBits & cstWeiterfuehren) > 0 ? Obj : null);
                      }
                    }
                    else if (sDatentyp.endsWith("Bezeichnung"))
                    {
                    	if (TabDaten.posInhalt("Aic_stamm",iStamm))
                    		Vec.addElement(g.getStamm(TabDaten.getI("Aic_Stamm")));
                    }
                    //JCOutlinerNode Nod =
                    addTabD(Vec,0,NodP,false,0);                  
                    //Nod.setStyle(StyFolder);
                    i2++;
                   }
                  }
                  return null;
                }// (iBits&cstStammstichtag)>0
                else if ((iBits&cstEinzeldaten)>0)
                {
                  boolean bMinMax=false;
                  for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                    if (TabSpalten.getF("Max")>0)
                      bMinMax=true;
                  JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
		  StyFolder.setFont(g.getOutFont(false));//g.fontStandard);
                  boolean bOhneST=(iBits&cstVerdichten)>0;
                  String[] sTitelZeile = bOhneST ? new String[]{g.getBegriffS("Show","Wert"),g.getBegriffS("Show","Bereich")}:
                      new String[] {g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Wert"),g.getBegriffS("Show","Bereich")};
                  if (Out!=null)
                  {
                   if ((iBits & cstKeinTitel)==0) // !!!
                    Out.setColumnButtons(sTitelZeile);
                   Out.setNumColumns(bMinMax ?3:2);
                   if (bMinMax)
                    Out.setColumnAlignment(2,BWTEnum.TOPCENTER);
                   Out.setColumnAlignment(1,VecAusrichtung.elementAt(0).intValue());
                  }
//                  else
//                  {
//                	  VecTST=new Vector<Object>();
//                	  for (int i=0;i<sTitelZeile.length-(bMinMax?0:1);i++)
//                		  VecTST.addElement(sTitelZeile[i]);
//                  }
                  int iMax=0;
                  for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                      iMax=Math.max(iMax,getLaengeB(TabSpalten));
                  if (iMax>0 && Out!=null)
                    Out.setColumnWidth(1,iMax);

                  TabDaten.moveFirst();
                  Vector Vec2=getTitel();
                  int i=0;
                  int i2=0;
                  for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                  {
                    Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr");
                    int iAnz=!bAlleSpalten && (TabSpalten.getI("bits")&cstUnsichtbar)>0?0:VecMehr==null?1:VecMehr.size();
                    if (iAnz==0)
                      i2++;
                    for(int iPer=0;iPer<iAnz;iPer++)
                    {
                      String s=Vec2 == null ? VecMehr != null ? "e" + TabSpalten.getS("Kennung2") + VecMehr.elementAt(iPer) : TabSpalten.getS("Kennung") : Vec2.elementAt(i + iPer).toString();
                      //g.progInfo("Kennung="+s+"/"+VecMehr+"/"+Vec2);
                      Object Obj=TabToScreen(g,s, TabDaten, TabSpalten, VecMehr != null || Vec2 != null);
                      //g.progInfo((String)VecBezeichnung.elementAt(i2)+","+s+":"+Obj);
                      if (Obj != null)
                      {
                        Vector Vec = new Vector();
                        if (!bOhneST)
                          Vec.addElement((TabSpalten.getI("bits3")&cstSpKeinTitel)>0?"":Static.replaceString(VecBezeichnung.elementAt(i2), "\n", " "));
                        Vec.addElement(Obj);
                        if(bMinMax)
                          Vec.addElement(TabSpalten.isNull("Max") ? "" :
                                         new Zahl1(TabSpalten.getF("Min"), TabSpalten.getS("Format")) + " - " +
                                         new Zahl1(TabSpalten.getF("Max"), TabSpalten.getS("Format")));
                        
                        {
                        	
                         JCOutlinerNode Nod = addTabD(Vec,0,NodP,false,0);
                         if(bMinMax && !TabSpalten.isNull("Max") /*&& Obj != null*/) {
                          double d = Sort.getf2(Obj);
                          boolean bMin = d < TabSpalten.getF("Min");
                          boolean bMax = d > TabSpalten.getF("Max");
                          if(bMin || bMax) {
                            JCOutlinerNodeStyle Sty = new JCOutlinerNodeStyle();
                            Sty.setFont(g.getOutFont(false));//g.fontStandard);
                            Color col = bMin ? Color.GREEN.darker() : Color.RED;
                            Sty.setForeground(col);
                            Nod.setStyle(Sty);
                          }
                          else
                            Nod.setStyle(StyFolder);
                        }
                        else if ((TabSpalten.getI("bits2")&(cstFett+cstFett2))>0)
                        {
                          JCOutlinerNodeStyle Sty = new JCOutlinerNodeStyle();
                          Sty.setFont((TabSpalten.getI("bits2")&cstFett2)>0 ? g.fontSumme2:g.fontSumme);
                          Sty.setForeground((TabSpalten.getI("bits2") & cstFett2) > 0 ? g.ColSumme2:g.ColSumme);
                          Nod.setStyle(Sty);
                        }
                        else
                          Nod.setStyle(StyFolder);
                       }
                      }
                      else if (getLaengeD(TabSpalten)!=0)
                      {
                        Vector Vec = new Vector();
                        if (!bOhneST)
                          Vec.addElement(Static.replaceString(VecBezeichnung.elementAt(i2), "\n", " "));
                        Vec.addElement(" ");//Obj);
                        JCOutlinerNode Nod = addTabD(Vec,0,NodP,false,0);
                        if ((TabSpalten.getI("bits2")&(cstFett+cstFett2))>0)
                        {
                          JCOutlinerNodeStyle Sty = new JCOutlinerNodeStyle();
                          Sty.setFont((TabSpalten.getI("bits2") & cstFett2) > 0 ? g.fontSumme2 : g.fontSumme);
                          Sty.setForeground((TabSpalten.getI("bits2") & cstFett2) > 0 ? g.ColSumme2 : g.ColSumme);
                          Nod.setStyle(Sty);
                        }
                        else
                          Nod.setStyle(StyFolder);
                      }
                      i2++;
                    }
                    i++;
                  }
                  return null;
                }// (iBits&cstEinzeldaten)>0

		// Gruppierung initialisieren
		Vector VecBez=new Vector(VecBezeichnung);
		int i1=0;
                boolean bVorletzte=(iBits&cstVorletzteEbene)>0;
                Vector Vec4=new Vector();
                Tabellenspeicher TabFilter=null;
                for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                {
                  if (TabSpalten.getI("STT")>0 && TabSpalten.getS("Kennung2").startsWith("m"))
                    Static.addVector(Vec4,TabDaten.getVecSpalte("V"+TabSpalten.getS("Kennung").substring(1,TabSpalten.getS("Kennung").lastIndexOf("_"))));
                  if ((TabSpalten.getI("bits2")&(cstErgaenzen+cstNurErste))>0 )
                  {
                    Tabellenspeicher Tab=null;
                    if (TabSpalten.getI("Filter")>0)
                    {
                      ShowAbfrage Abf=new ShowAbfrage(g,TabSpalten.getI("Filter"),Abfrage.cstAbfrage/*cstHS_Filter*/);
                      Abf.iBits=Abf.iBits|Abfrage.cstKeinStamm;
                      Tab=Abf.getDaten(Abf.iStt, 0, null,null);
                    }
                    TabDaten=checkNurErste(TabDaten,TabSpalten,Tab);
                  }
                  else if (TabSpalten.getI("Filter")>0 && (iBits&cstTTO)>0 && TabSpalten.getInhalt("Gruppe")==null && (TabSpalten.getI("bits")&cstSpLeer)==0)
                  {
                    if (TabFilter==null)
                      TabFilter=new Tabellenspeicher(g,new String[] {"Nr","Tab","Spalte","Abf"});
                    ShowAbfrage Abf=new ShowAbfrage(g,TabSpalten.getI("Filter"),Abfrage.cstAbfrage/*cstHS_Filter*/);
                    Abf.iBits=Abf.iBits|Abfrage.cstKeinStamm;
                    Tabellenspeicher Tab=Abf.getDaten(Abf.iStt, 0, null,null);
                    //Tab.showGrid();
                    TabFilter.addInhalt("Nr",TabSpalten.getI("Nummer"));
                    TabFilter.addInhalt("Tab",Tab);
                    TabFilter.addInhalt("Abf",Abf);
                    if ((TabSpalten.getI("bits")&Abfrage.cstSpRefresh)>0)
                    {
                      //TabFilter.addInhalt("Spalte","v" + TabSpalten.getS("Kennung2"));
                      TabFilter.addInhalt("Spalte",Abf.TabSpalten.getS("Kennung"));
                    }
                    else if (iBew==0)
                      TabFilter.addInhalt("Spalte","Aic_Stamm");
                    else
                    {
                      String sSpalte=null;
                      for (int iPos=0;iPos<g.TabEigenschaften.size() && sSpalte==null;iPos++)
                      {
                        if (g.TabEigenschaften.getI(iPos,"aic_stammtyp")==Abf.iStt && TabDaten.exists("v"+g.TabEigenschaften.getI(iPos,"AIC")))
                          sSpalte="v"+g.TabEigenschaften.getI(iPos,"AIC");
                      }
                      TabFilter.addInhalt("Spalte",sSpalte);
                    }
                    //TabFilter.showGrid("Filter");
                    //g.progInfo("Filter:"+TabSpalten.getI("Nummer")+"/"+TabSpalten.getI("Filter"));
                  }
                }
                if (!Vec4.isEmpty())
                {
                  //SQL Qry=new SQL(g);
                  TabMehr = new Tabellenspeicher(g, "select aic_stamm,aic_stammtyp from stamm where "+g.in("aic_stamm",Vec4), true);
                  //Qry.free();
                  //TabMehr.showGrid();
                }
		for(TabSpalten.moveFirst();(TabSpalten.getI("bits")&cstGruppiert)>0;TabSpalten.moveNext())
                  if (Sichtbar(TabSpalten.getPos()))
		{
			TabSpalten.setInhalt("Component",-1);
			//VecBez.removeElementAt(0);
			i1++;
		}
                /*bLeer=i1>0 && !TabSpalten.isNull("Ergebnisart") && (iBits&cstSumme)==0;
                if (i1>0 && !bVorletzte)
                {
                  boolean bVerdichtet = (iBits & cstVerdichten) > 0;
                  if (bVerdichtet && !bLeer)
                    VecBez.removeElementAt(i1);
                  for (int i = bLeer || bVerdichtet ? 1 : 0; i < i1; i++)
                    VecBez.removeElementAt(bLeer ? 1 : 0);
                }*/
                TabSpalten.moveLast();
                //if (TabSpalten.getS("Datentyp").equals("Farbe") && (TabSpalten.getI("Bits")&cstBild)>0) // 12.6.2015: Farb-Spalte nicht mehr entfernen
                //  VecBez.removeElementAt(VecBez.size()-1);
                Vector VecU=null;
                boolean bUmsortieren=(iBits&cstUmsortieren)>0;
                if (bUmsortieren)
                {
                  Vector<String> Vec2=new Vector<String>();
                  Vector Vec3=new Vector();
                  VecU=new Vector();
                  for(int i=0;i<VecBez.size();i++)
                  {
                    String s=(String)VecBez.elementAt(i);
                    String s2=s.indexOf(bExport?32:10)>-1?s.substring(s.indexOf(bExport?32:10)+1):s;
                    int i2=Vec2.lastIndexOf(s2);
                    if (i2==-1 || i2==i-1)
                    {
                      Vec2.addElement(s2);
                      Vec3.addElement(s);
                    }
                    else
                    {
                      Vec2.insertElementAt(s2, i2 + 1);
                      Vec3.insertElementAt(s, i2 + 1);
                    }
                    VecU.addElement(new Integer(i2>-1?i2:i));
                  }
                  VecBez=new Vector(Vec3);
                  //g.progInfo("Vec2="+Vec2+", VecU="+VecU);
                }
                //g.progInfo("------------------------------------------------------- VecE="+VecE+", iStt="+iStt);
                if (VecE != null)
                {
                  //int i=VecE.size()-1;
                  /*if (VecE.contains(new Integer(iStt)))
                    while (i>0 && Tabellenspeicher.geti(VecE.elementAt(i)) !=iStt)
                      i--;*/
                  for(int i=VecE.size()-1;i>=0;i--)
                    VecBez.insertElementAt(g.getBez(VecE.elementAt(i)),0);
                }
                //g.progInfo("VecBez="+VecBez);
                //if(bLeer)
                //  VecBez.insertElementAt("",i1-1);

		// Spaltenbeschriftung und breite
        if (Out!=null)
        	setOutlinerButtons(VecE==null? 0:VecE.size()/*bVorletzte ? 0:i1+(bLeer?-1:0)*/,TabSpalten,Out,VecBez,iSpalten);

		Vector Vec2=getTitel();
		JCOutlinerNodeStyle StyFolder = /*g.getNullStyle();*/new JCOutlinerNodeStyle();
		//StyFolder.setFont(g.fontStandard);
		if (iBegriff>0)
		  g.setSchrift2(g.TabBegriffe.getPos("Aic",iBegriff),StyFolder,g.getOutFont(false));//g.fontStandard);
                if (TabDaten==null)
                  return null;
                Vector<Integer> VecKNZW=new Vector<Integer>();
                boolean bSichtbar0=Sichtbar(0);
                int iAnzG=0;
                if ((lBits2&cstCompress2)>0)
                	for(int i=0;i<TabSpalten.size();i++)
                		if ((TabSpalten.getI(i,"bits")&cstGruppiert)>0) iAnzG++;
                for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
		{
                  Vector<Object> Vec=new Vector<Object>();
                  if (VecE != null)
                    for(int i=0;i<VecE.size();i++)
                      Vec.addElement("");
			// Gruppierung
                        TabSpalten.moveFirst();
                        if (!bSichtbar0)
                            TabSpalten.moveNext();
			for(;(TabSpalten.getI("bits")&cstGruppiert)>0 && (TabDaten.exists("v"+TabSpalten.getS("Kennung2")) ?
                            /*!Sichtbar(TabSpalten.getPos()) ||*/ TabSpalten.getI("Component")==TabDaten.getI("v"+TabSpalten.getS("Kennung2")):
                            /*!Sichtbar(TabSpalten.getPos()) ||*/ Static.Gleich(TabToScreen(g,TabSpalten.getS("Kennung"),TabDaten,TabSpalten,false),TabSpalten.getInhalt("Component")));TabSpalten.moveNext())
                          ;//g.fixInfo(TabSpalten.getS("Kennung2")+":"+TabSpalten.getI("Component")+"/"+TabDaten.getI("v"+TabSpalten.getS("Kennung2"))+"/"+Sichtbar(TabSpalten.getPos()));
			if((TabSpalten.getI("bits")&cstGruppiert)>0)
			{
                          if (bVorletzte)
                          {
                            NodP = (JCOutlinerFolderNode)Out.getRootNode();
                            TabSpalten.moveFirst();
                          }
                          else
                            while(NodP.getLevel()>TabSpalten.getPos()-(bSichtbar0?0:1))
                              //if (Sichtbar(NodP.getLevel()-1))
                                  NodP=NodP.getParent();
                          JCOutlinerFolderNode Nod = null;
                          //if (iPool==0)
                          for (;(TabSpalten.getI("bits")&cstGruppiert)>0;TabSpalten.moveNext())
                            if (Sichtbar(TabSpalten.getPos()))
                          {
                            Object Obj=TabToScreen(g,TabSpalten.getS("Kennung"),TabDaten,TabSpalten,false);
                            int iStt=TabSpalten.getI("Stt");
                            int iStamm=iStt<=0 || !TabDaten.exists("v"+TabSpalten.getS("Kennung2")) ? 0:TabDaten.getI("v"+TabSpalten.getS("Kennung2"));
                            if(bVorletzte)
                            {
                              Vec.addElement(Obj);
                              if (TabSpalten.getPos() == i1 - 1)
                            	  Nod=(JCOutlinerFolderNode)addTabD(Vec,iStamm,NodP,true,iStt);
                                //Nod = new JCOutlinerFolderNode((Object)Vec, NodP);
                            }
                            else
                            {
                                Vec=new Vector<Object>();
                                if (VecE != null)
                                  for(int i=0;i<VecE.size();i++)
                                    Vec.addElement("");
                              for (int i=0;i<TabSpalten.getPos();i++)
                                if (Sichtbar(i))
                                  Vec.addElement("");
                              Vec.addElement(Obj);
                              //Nod = new JCOutlinerFolderNode((Object)Vec, NodP);
                              Nod=(JCOutlinerFolderNode)addTabD(Vec,iStamm,NodP,true,iStt);
                              //g.fixInfo(sDefBez+":"+Vec);
                              //Nod.setUserData(TabDaten.getI("V"+TabSpalten.getS("Kennung").substring(1)));
                            }
                            int iPosC=!bUmsortieren && !TabSpalten.isNull("Ergebnisart") && (iBits&cstSumme)==0 ? g.TabCodes.getPos("Aic",TabSpalten.getI("Ergebnisart")):-1;
                            if (iPosC>=0 && !g.TabCodes.getS(iPosC,"Kennung").equals("list"))
                            {
                              boolean bGS=(iBits&cstKeineGesamtsumme)==0 && (TabSpalten.getI("bits2")&cstKeineGSumme)==0;
                              boolean b1S=(TabSpalten.getI("bits2")&cstKeine1Summe)==0;
                              int iLevel=NodP.getLevel();
                              JCOutlinerFolderNode Nodx=NodP;
                              for (int i2=b1S ? bGS ? -1:0:1;i2<iLevel;i2++)
                              {
                                      Vector<Object> VecLabel=null;
                                      if (!(Nodx.getLabel() instanceof Vector))
                                      {
                                              VecLabel=new Vector();
                                              VecLabel.addElement(Nodx.getLabel());
                                              for(int i=1;i<Vec.size();i++)
                                                      VecLabel.addElement(null);
                                              Nodx.setLabel(VecLabel);
                                      }
                                      else
                                              VecLabel= (Vector)Nodx.getLabel();
                                      //g.progInfo(VecLabel+"<- VecLabel, Vec->"+Vec);
                                      if (VecLabel.size()<Vec.size())
                                              for(int i=VecLabel.size();i<Vec.size();i++)
                                                      VecLabel.addElement(null);
                                      int iPer=Vec.size()-1;
                                      // g.fixtestError("getSum1 auf "+TabSpalten.getS("Bezeichnung")+"/"+TabSpalten.getS("kennung"));
                                      VecLabel.setElementAt(getSum(VecLabel.elementAt(iPer), Vec.elementAt(iPer), TabSpalten.getF("Min"),TabSpalten.getF("Max"),(TabSpalten.getI("bits")&cstKeineEinheit)==0), iPer);
                                      Nodx=Nodx.getParent();
                              }
                            }
                            //else
                            //  Nod = new JCOutlinerFolderNode(Obj, NodP);
                            if (((iBits & cstVerdichten) > 0 || (lBits2&cstCompress2)>0 && Nod.getLevel()==iAnzG || bVorletzte) && Nod != null)
                                Nod.setState(BWTEnum.FOLDER_CLOSED);
                              if (TabDaten.exists("v" + TabSpalten.getS("Kennung2")))
                              {
                                int iAic = TabDaten.getI("v" + TabSpalten.getS("Kennung2"));
                                if (Nod != null)
                                  if (!bVorletzte)
                                  {
                                    Image Gif = g.getGif(g.TabStammBild, iAic);
                                    Nod.setStyle(g.getStyle(Gif != null ? Gif : g.getSttGif(TabSpalten.getI("Stt"))));
                                  }
                                  else
                                    Nod.setStyle(StyFolder);
                                TabSpalten.setInhalt("Component", iAic);
                              }
                              else
                                TabSpalten.setInhalt("Component", Obj /*TabDaten.getS(TabSpalten.getS("Kennung"))*/);
                              if ((TabSpalten.getI("bits2")&(cstFett+cstFett2))>0)
                              {
                                JCOutlinerNodeStyle Sty = Nod.getStyle();
                                Sty.setFont((TabSpalten.getI("bits2") & cstFett2) > 0 ? g.fontSumme2 : g.fontSumme);
                                Sty.setForeground((TabSpalten.getI("bits2") & cstFett2) > 0 ? g.ColSumme2:g.ColSumme);
                                Nod.setStyle(Sty);
                              }
                              if (Nod != null)
                                NodP = Nod;
                          }
			}
			// Daten füllen
                        Vec=new Vector();
                        if (VecE != null)
                          for(int i=0;i<VecE.size();i++)
                            Vec.addElement("");
                        //if (bVorletzte)
                          for (int i=0;i<i1;i++)
                            Vec.addElement(null);
                        //else if (bLeer)
                        //  Vec.addElement(null);
                        int iCol=-1;
                        boolean bColSum=false;
                        Date date=null;
                        for (int iS=0;iS<iSpalten && !TabDaten.eof();iS++)
                        {
                          if (iSpalten>1)
                            TabSpalten.moveFirst();
                         int iPS=0;
                         int iPS2=0;
			 for(/*TabSpalten.moveFirst()*/;!TabSpalten.eof();TabSpalten.moveNext())
                          if(bAlleSpalten || Sichtbar(TabSpalten.getPos()))
			  {
				//g.fixInfo(TabDaten.getPos()+"/"+TabSpalten.getPos()+":"+TabSpalten.getS("Datentyp"));
				Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr");
				int iAnz=VecMehr==null?1:VecMehr.size();
				int i=Vec.size();
                                //g.progInfo("VecMehr="+VecMehr+" / Vec2="+Vec2);
				for(int iPer=0;iPer<iAnz;iPer++)
                                {
                                  boolean bObj2=false;
                                  Object Obj2=null;
                                  if (TabFilter != null && TabSpalten.getI("Filter")>0 && TabFilter.posInhalt("Nr",TabSpalten.getI("Nummer")))
                                  {
                                    Tabellenspeicher Tab=(Tabellenspeicher)TabFilter.getInhalt("Tab");
                                    if ((TabSpalten.getI("bits")&Abfrage.cstSpRefresh)>0)
                                    {
                                      bObj2=true;
                                      ShowAbfrage Abf=(ShowAbfrage)TabFilter.getInhalt("Abf");
                                      /*String s=Abf.Hier(TabDaten,TabSpalten,'v');
                                      if (s != null && Tab.posInhalt("aic_stamm",new Integer(s)))*/
                                      int iAic=TabDaten.getI("v"+TabSpalten.getS("Kennung2"));
                                      if (Tab.posInhalt("aic_stamm",iAic))
                                        Obj2=/*bJavaFX ? new Combo(Abf.TabToString(Tab),iAic,"",0):*/Abf.TabToString(Tab);
                                    }
                                    else
                                      bObj2=!Tab.posInhalt("aic_stamm",TabDaten.getI(TabFilter.getS("Spalte")));
                                  }
                                  Object Obj=bObj2 ? Obj2:TabToScreen(g,Vec2==null?VecMehr!=null ? "e"+TabSpalten.getS("Kennung2")+VecMehr.elementAt(iPer):TabSpalten.getS("Kennung"):Vec2.elementAt(i+iPer).toString(),TabDaten,TabSpalten,VecMehr!=null || Vec2 != null);
                                  if (!bUmsortieren || VecU==null || ((Integer)VecU.elementAt(i+iPer)).intValue()>=i+iPer-1)
                                    Vec.addElement(Obj);
                                  else
                                    Vec.insertElementAt(Obj,((Integer)VecU.elementAt(i+iPer)).intValue()+1);
                                }
                                if (bUmsortieren && (TabSpalten.getI("bits")&cstPeriode)>0)
                                  iPS++;
                                boolean bRowSum=VecMehr != null && (TabSpalten.getI("bits")&cstPeriodensumme)>0;
                                boolean bRowAvg=VecMehr != null && (TabSpalten.getI("bits3")&cstPerSchnitt)>0;
				if(bRowSum || bRowAvg)
				{
                                  iPS2++;
                                  double d = 0;
                                  if (bUmsortieren)
                                    for (i=0;i<iAnz;i++)
                                      d += Sort.getf(Vec.elementAt(Vec.size()-iPS2-i*iPS));
                                  else
                                    for (int iPer = Vec.size() - iAnz; iPer < Vec.size(); iPer++)
                                      d += Sort.getf(Vec.elementAt(iPer));
                                  Vec.addElement(doubleToScreen(g, bRowAvg ? d/iAnz:d, TabSpalten));
				}
                                int iPosC=!bUmsortieren && !TabSpalten.isNull("Ergebnisart") && (iBits&cstSumme)==0 ? g.TabCodes.getPos("Aic",TabSpalten.getI("Ergebnisart")):-1;
				if (Out!=null && iPosC>=0 && !g.TabCodes.getS(iPosC,"Kennung").equals("list"))
					{
						//int iPos=VecSpalten.size()-1;
						if (!Out.getRootVisible() && (iBits&cstKeineGesamtsumme)==0)
							Out.setRootVisible(true);
                                                boolean bGS=(iBits&cstKeineGesamtsumme)==0 && (TabSpalten.getI("bits2")&cstKeineGSumme)==0;
                                                boolean b1S=(TabSpalten.getI("bits2")&cstKeine1Summe)==0;
						int iLevel=NodP.getLevel();
						//if (iLevel>0)
						//{
							JCOutlinerFolderNode Nodx=NodP;
							for (int i2=b1S ? bGS ? -1:0:1;i2<iLevel;i2++)
							{
								Vector<Object> VecLabel=null;
								if (!(Nodx.getLabel() instanceof Vector))
								{
									VecLabel=new Vector();
									VecLabel.addElement(Nodx.getLabel());
									for(i=1;i<Vec.size();i++)
										VecLabel.addElement(null);
									Nodx.setLabel(VecLabel);
								}
								else
									VecLabel= (Vector)Nodx.getLabel();
                                                                //g.progInfo(VecLabel+"<- VecLabel, Vec->"+Vec);
								if (VecLabel.size()<Vec.size())
									for(i=VecLabel.size();i<Vec.size();i++)
										VecLabel.addElement(null);
								for(int iPer=Vec.size()-iAnz-(bRowSum || bRowAvg ? 1:0);iPer<Vec.size();iPer++)
                                {
                                  //g.progInfo("getSum"+iPer+":"+getSum(VecLabel.elementAt(iPer), Vec.elementAt(iPer), TabSpalten.getF("Min"),TabSpalten.getF("Max")));
                                  // g.fixtestError("getSum2 auf "+TabSpalten.getS("Bezeichnung")+"/"+TabSpalten.getS("kennung"));
                                  VecLabel.setElementAt(getSum(VecLabel.elementAt(iPer), Vec.elementAt(iPer), TabSpalten.getF("Min"),TabSpalten.getF("Max"),(TabSpalten.getI("bits")&cstKeineEinheit)==0), iPer);
                                  if (TabDaten.getPos()==0 && (TabSpalten.getI("bits3")&cstKNZW)>0 && !VecKNZW.contains(iPer))
                                	  VecKNZW.addElement(iPer);
                                }
								Nodx=Nodx.getParent();
							}
						//}
					}
                                if ((iBits&cstSumme)>0)
                                    ;
				else if (TabSpalten.getS("Datentyp").equals("BewDatum") && (iBits & Abfrage.cstFeiertage)>0 && TabSpalten.isNull("Anzeige")
                                  && TabSpalten.isNull("Gruppe") && TabDaten.getInhalt(TabSpalten.getS("Kennung")) instanceof Date)
					date=(Date)TabDaten.getInhalt(TabSpalten.getS("Kennung"));
				else if (/*!bJavaFX && */TabSpalten.getS("Datentyp").equals("Farbe") && (TabSpalten.getI("bits")&cstBild)>0)
                                {
                                  iCol = TabDaten.isNull(TabSpalten.getS("Kennung")) ? -1 : TabDaten.getI(TabSpalten.getS("Kennung"));
                                  bColSum=(TabSpalten.getI("bits2")&cstKeine1Summe)==0;
                                  //TabDaten.setInhalt(TabSpalten.getS("Kennung"),null);
                                }
			  }
                          if (iS<iSpalten-1)
                            TabDaten.moveNext();
                        }
                        if (bUmsortieren)
                        {
                          TabSpalten.moveLast();
                          if (!Out.getRootVisible() && (iBits&cstKeineGesamtsumme)==0)
                            Out.setRootVisible(true);
                          Vector<Object> VecLabel = NodP.getLabel() instanceof Vector ? (Vector)NodP.getLabel():new Vector<Object>();
                          if (VecLabel.size()<Vec.size())
                            for(int i=VecLabel.size();i<Vec.size();i++)
                              VecLabel.addElement(null);
                          // g.fixtestError("getSum3 auf "+TabSpalten.getS("Bezeichnung")+"/"+TabSpalten.getS("kennung"));
                          for (int i=1;i<Vec.size();i++)
                            VecLabel.setElementAt(getSum((i==1 ? Vec:VecLabel).elementAt(i), (i==1 ? VecLabel:Vec).elementAt(i), 0,0,true), i);
                          if (!(NodP.getLabel() instanceof Vector))
                            NodP.setLabel(VecLabel);
                          g.progInfo("VecLabel="+VecLabel);
                        }
                      int iSysAic = is(iBits,cstSumme) ? 0:iBew==0 ? TabDaten.getI("aic_stamm") : TabDaten.getI("aic_Bew_Pool");
                      
                      
                    	JCOutlinerNode Nod = addTabD(Vec,iSysAic,NodP,false,0);
                    	Nod.setStyle(getStyle(this,TabDaten,StyFolder,date,TabDaten.exists("ANR") ? TabDaten.getI("ANR"):0,iCol));
                        if (iCol != -1 && bColSum)
                        {
                          JCOutlinerFolderNode NodP2=NodP;
                          while (NodP2!=null)
                          {
                            //JCOutlinerNodeStyle Sty = NodP2.getStyle();
                            //if (Sty != null)
                            //{
                              NodP2.setStyle(g.setColor(NodP2.getStyle()==null ? new JCOutlinerNodeStyle():NodP2.getStyle(), new Color(iCol)));
                              g.testInfo("Umfärben von " + NodP2 + " auf " + iCol);
                            //}
                            NodP2=NodP2.getParent();
                          }
                        }
            			Nod.setUserData(new Integer(iSysAic));
                      
			//Nod.setStyle(bEntfernte && !TabDaten.isNull("pro_aic_protokoll")?g.StyDel:getStyle(StyFolder));
			

                        //if (iPool>0 && iPool==iSysAic)
                        //  Static.makeVisible(Out,Nod);
		}
                if (VecKNZW.size()>0)
                {
                	g.fixtestError("VecKNZW="+VecKNZW);
                	posAllNodes((JCOutlinerFolderNode)Out.getRootNode(),VecKNZW);
                    //JCOutlinerNode Nodx=Out.getRootNode();
//                    int iZeile=0;
//                    Nodx=Out.getNextNode(Nodx);
//                    while (Nodx!=null)
//                    {
//                    	iZeile++;
//                    	for (int iPos=0;iPos<VecKNZW.size();iPos++)
//                    	{
//                    		int iKNZW=Sort.geti(VecKNZW, iPos);
//                    		Vector<Object> VecNod=(Vector)Nodx.getLabel();
//                    		g.fixtestError("Zeile"+iZeile+": "+Nodx.getLabel()+"/"+VecNod.elementAt(iKNZW));
//                    	}
//                    	Nodx=Out.getNextNode(Nodx);
//                    }
                }
                int i=0;
                for(TabSpalten.moveFirst();!TabSpalten.out();TabSpalten.moveNext())
                {
                  if (Sichtbar(TabSpalten.getPos()))
                    i++;
                  if ((TabSpalten.getI("bits2")&cstSpSort)>0)
                    Out.sortByColumn(i-1,Sort.sortMethod,JCqsort.DESCENDING);
                }
                TabMehr=null;
                if (bEmpty)
                {
                  VecAusrichtung=null;
                  VecBezeichnung=null;
                  TabSpalten.clearAll();
                }
                Static.clearError();
                //if (bJavaFX)
                //	TabD.showGrid(true, "TabD");
                Object Obj=Out.getRootNode().getLabel();
                if (Obj==null || !(Obj instanceof Vector))
                		return null;
                else
                	return (Vector<Object>) Obj;
	}
	
	@SuppressWarnings("unchecked")
	private void posAllNodes(JCOutlinerFolderNode NodP,Vector VecKNZW)
	{
		boolean bFirst=true;
		Vector<JCOutlinerNode> Vec=NodP.getChildren();
		if (Vec != null)
			for (int i=0;i<Vec.size();i++)
			{
				if (Vec.elementAt(i) instanceof JCOutlinerFolderNode)
					posAllNodes((JCOutlinerFolderNode)Vec.elementAt(i),VecKNZW);
				else if (bFirst)
				{
					int iZeile=0;
					for (int iPos=0;iPos<VecKNZW.size();iPos++)
                	{
                		int iKNZW=Sort.geti(VecKNZW, iPos);
                		iZeile++;
                		Vector<Object> VecNod=(Vector)NodP.getLabel();//(Vector)Vec.elementAt(i).getLabel();
                		double dZ=Sort.getf(VecNod.elementAt(iKNZW));
                		if (dZ<0)
                		{
                			//g.fixtestError("Zeile"+iZeile+": "+VecNod+"/"+VecNod.elementAt(iKNZW)+"/"+dZ);
                			//VecNod.setElementAt(null, iKNZW);
                			subNodeValue(VecNod,iKNZW,dZ);
                			JCOutlinerFolderNode NodP2=NodP.getParent();
                			while (NodP2 != null && NodP2.getLabel() instanceof Vector)
                			{
                				//Vector<Object> VecNod2=(Vector)NodP2.getLabel();
                				subNodeValue((Vector<Object>)NodP2.getLabel(),iKNZW,dZ);
                				//VecNod2.setElementAt(getSum(VecNod2.elementAt(iKNZW),-dZ,0,0,false), iKNZW);
                				NodP2=NodP2.getParent();
                			}
                		}
                	}
					bFirst=false;
				}
			}
	}
	
	private void subNodeValue(Vector<Object> Vec,int iPos,double dZ)
	{
		Object Obj=Vec.elementAt(iPos);
		if (Obj instanceof Mass)
			((Mass)Obj).add2(-dZ);
		else if (Obj instanceof Waehrung)
			((Waehrung)Obj).add2(-dZ);
		else if (Obj instanceof Zahl1)
			((Zahl1)Obj).add2(-dZ);
		else
			Static.printError("KNZW-Aufsummieren mit "+Static.print(Obj)+" noch nicht möglich");
	}

	public JCOutlinerNodeStyle getStyle(ShowAbfrage Abf,Tabellenspeicher Tab,JCOutlinerNodeStyle StyFolder,Date date,int iANR,int iCol)
	{
		if ((Abf.iBits&cstSumme)>0)
			return StyFolder;
	if (Abf.bEntfernte && !Tab.isNull("pro_aic_protokoll"))
          return g.setColor(StyFolder,g.ColLoeschen);
        else if (Abf.iBew==0 && Abf.iStt==g.iSttANR)
        {
          /*if (StyAustritt==null)
          {
            StyAustritt = new JCOutlinerNodeStyle(StyFolder);
            StyAustritt.setForeground(g.ColAustritt);
          }*/
          return istDa(g,Tab.getI("aic_stamm")) ? StyFolder:g.setColor(StyFolder,g.ColAustritt);
        }
        else if ((Abf.iBits & Abfrage.cstFeiertage) == 0 || date == null)
        {
          /*if (iCol != -1)
          {
            StyFolder = new JCOutlinerNodeStyle(StyFolder);
            StyFolder.setForeground(new Color(iCol));
          }*/
          return iCol == -1 ? StyFolder:g.setColor(StyFolder,new Color(iCol));
        }
        else
        {
          DateWOD dw = new DateWOD(date);
          int iD=dw.getDay();
          Color Col=!g.Feiertag(dw,iANR).equals("") ? g.ColHoliday:
                  iD==Calendar.MONDAY ? g.ColMo2: iD==Calendar.TUESDAY ? g.ColDi2:iD==Calendar.WEDNESDAY ? g.ColMi2:iD==Calendar.THURSDAY ? g.ColDo2:
                  iD==Calendar.FRIDAY ? g.ColFr2:iD==Calendar.SATURDAY ? g.ColSa2:iD==Calendar.SUNDAY ? g.ColSo2:null;
          /*JCOutlinerNodeStyle Sty = null;
          if (Col != null)
          {
            Sty = new JCOutlinerNodeStyle(StyFolder);
            //Sty.setBackgroundSelected(Col);
            Sty.setForeground(Col);
          }*/
          return Col==null ? StyFolder:g.setColor(StyFolder,Col);
        }
	}

        /*private JCOutlinerNodeStyle getStyle(JCOutlinerNodeStyle Style)
        {
          if (Col==null)
            return Style;
          else
          {
            JCOutlinerNodeStyle StyleNew=new JCOutlinerNodeStyle(Style);
            StyleNew.setForeground(Col);
            return StyleNew;
          }
        }*/

        private void checkCount(String sArt,AddUp Obj1/* Parent */,Object Obj2/* Child */,double dMin,double dMax)
        {
          //g.fixtestError("checkCount vorher mit "+sArt+":"+Static.print(Obj1)+"/"+Static.print(Obj2));
          if (Obj2 instanceof AddUp)// && Obj2.hasValue())
            Obj1.add((AddUp)Obj2);
          else if (sArt.equals("count"))
            Obj1.add();
          else if (sArt.equals("count_distinct"))
            Obj1.add2(Obj2);
//          else if (Obj2==null)
//        	g.fixtestError(" !!! checkCount-Object ist null");
          else
          {
            double d=Sort.getf2(Obj2);
            int i=d>dMax ? 3:d<dMin ? 2:1;
            //g.fixtestError("checkCount mit "+sArt+": d="+d+", "+dMin+"-"+dMax+"->"+i);
            if (d!=0 && (sArt.equals("_count_max") && i==3 || sArt.equals("_count_min") && i==2 ||
                sArt.equals("_count_in") && i==1 || sArt.equals("_count_not_in") && i>1))
              Obj1.add();
            else
              setNull(Obj2);
          }
          //g.fixtestError("checkCount nachher:"+Obj1+"/"+Obj2);
        }

	public Object getSum(Object Obj1/* Parent */,Object Obj2/* Child */,double dMin,double dMax,boolean bEinheit)
	{
		if (Obj2==null || Obj2.equals(Static.sLeer))
                  return Obj1;
		double d=0;
		String sArt=null;
		if (TabSpalten.getI("Ergebnisart")==0)
			sArt="sum";
		else
		{
			int iPos=g.TabCodes.getPos("Aic",TabSpalten.getI("Ergebnisart"));
		  	sArt=g.TabCodes.getS(iPos,"Kennung");
		}
    // g.fixtestError("getSum "+sArt+":"+Obj1+"="+Static.className(Obj1)+"/"+Obj2+"="+Static.className(Obj2));
    if (sArt.equals("last"))
    {
      g.progInfo("last->"+Obj2+"/"+Static.className(Obj2)+" statt "+Obj1+"/"+Static.className(Obj1));
      return Obj2;
    }
                //g.progInfo(TabSpalten.getS("BEZEICHNUNG")+":"+sArt+"/"+Obj1+"("+Static.className(Obj1)+")/"+Obj2+"("+Static.className(Obj2)+")");
		if (sArt.equals("count") || sArt.equals("count_distinct") || sArt.startsWith("_count"))
                {
                  if (Obj1==null || !(Obj1 instanceof AddUp))
                    Obj1=new AddUp(bEinheit);//Obj2 instanceof AddUp ? ((AddUp)Obj2).bEinheit:true);
                  checkCount(sArt,(AddUp)Obj1,Obj2,dMin,dMax);
                  //    ((AddUp)Obj1).add();
                  return Obj1;
                  //return new Long((Obj1 instanceof Long ? TabSpalten.getl(Obj1) : 0) + 1);
                }
		else
		{
			if (Obj1==null)
			{
				if (sArt.equals("avg"))
					if (Obj2 instanceof Waehrung)
						return new Waehrung((Waehrung)Obj2);
					else if (Obj2 instanceof Mass)
						return new Mass((Mass)Obj2);
					else if (Obj2 instanceof Zahl1)
						return new Zahl1((Zahl1)Obj2);
					else if (Obj2 instanceof VonBis)
						return new VonBis((VonBis)Obj2);
					// else if (Obj1 != null)
					// 	g.printError("ShowAbfrage.getSum: avg mit "+Static.className(Obj1)+" nicht möglich",iBegriff);
				if (Obj2 instanceof Memo1 || Obj2 instanceof String)
					return Obj2;
				return doubleToScreen(g,Sort.getf(Obj2),TabSpalten,Obj2 instanceof Mass ? ((Mass)Obj2).getAIC():0);
			}
			double d1=Sort.getf(Obj1);
			double d2=Sort.getf(Obj2);
			if (sArt.equals("sum"))
				d=d1+d2;
			else if (sArt.equals("avg"))
			{
				if (Obj1 instanceof Waehrung)
					((Waehrung)Obj1).add(d2);
				else if (Obj1 instanceof Mass)
					((Mass)Obj1).add(d2);
				else if (Obj1 instanceof Zahl1)
					((Zahl1)Obj1).add(d2);
				else if (Obj1 instanceof VonBis)
					((VonBis)Obj1).add(d2);
				else if (Obj1 != null)
					g.printError("ShowAbfrage.getSum: avg mit "+Static.className(Obj1)+" nicht möglich",iBegriff);
                                //g.progInfo("Obj1="+Obj1);
				return Obj1;
			}
			else if (d1==0.0)
				d=d2;
			else if (sArt.equals("min"))
				d=Math.min(d1,d2);
			else if (sArt.equals("max"))
				d=Math.max(d1,d2);
			else
				g.printError("ShowAbfrage.getSum: mit "+sArt+" nicht möglich",iBegriff);
      // g.fixtestError("getSum "+sArt+":"+Obj1+"/"+Obj2+"-> d="+d+" (bei d1="+d1+", d2="+d2+")");
		}
                if ((iBits&cstUmsortieren)>0)
                 if (Obj2 instanceof VonBis)
                 {
                  Vector Vec=((VonBis)Obj2).getVec();
                  return new VonBis(null,null,d,Sort.gets(Vec,2),Sort.geti(Vec,3),g,Sort.geti(Vec,5),(TabSpalten.getI("Bits")&cstKeineEinheit)>0 /* mit Einheit !!!*/);
                 }
                //g.progInfo("getSum2:"+Obj1+"/"+Obj2+"->"+doubleToScreen(g,d,TabSpalten));
		return doubleToScreen(g,d,TabSpalten,Obj2 instanceof Mass ? ((Mass)Obj2).getAIC():0);
	}

        public void setOutlinerButtons(int i1,Tabellenspeicher TabSpalten,AUOutliner Out,Vector VecBez)
        {
          setOutlinerButtons(i1,TabSpalten,Out,VecBez,1);
        }

	public void setOutlinerButtons(int i1,Tabellenspeicher TabSpalten,AUOutliner Out,Vector VecBez,int iSpalten)
	{
          if (iSpalten<1)
            iSpalten=1;
          //if ((iBits & cstKeinTitel)==0)
          if (iSpalten == 1)
            Out.setColumnButtons(new jclass.util.JCVector(VecBez));
          else
          {
            Vector<String> Vec=new Vector<String>();
            for (int i=0;i<iSpalten;i++)
              for(int i2=0;i2<VecBez.size();i2++)
                Vec.addElement((String)VecBez.elementAt(i2));
            Out.setColumnButtons(new jclass.util.JCVector(Vec));
          }
          Out.setNumColumns(VecBez.size()*iSpalten);
          if (TabSpalten != null && !TabSpalten.isEmpty())
          {
                  Out.setColumnAlignments(getAusrichtung(i1));
                  int iFF=g.getFontFaktor();
                  int iPos=0;
                  for (int i=0;i<iSpalten;i++)
                    for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                      //if((TabSpalten.getI("bits")&cstUnsichtbar)==0)
                      if(Sichtbar(TabSpalten.getPos()))
                      {
                          int iL=getLaengeB(TabSpalten)*iFF/100;
                          Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr");
                          for(int iPer=0;iPer<(VecMehr==null?1:VecMehr.size()+(PerZS() ? 1:0));iPer++)
                          {
                            if (/*!bJavaFX && */TabSpalten.getS("Datentyp").equals("Farbe") && (TabSpalten.getI("bits")&cstBild)>0)
                              Out.setColumnWidth(iPos-i1,0);
                            else if (iL>0 && iPos-i1>=0)
                            {
                                    Out.setColumnWidth(iPos-i1,iL);//iL>0 ? iL:BWTEnum.NOVALUE);
                                    //g.progInfo("Out.setColumnWidth"+iPos+"->"+iL);
                            }
                            iPos++;
                          }
                      }
          }
	}

        public Tabellenspeicher getTabDruckbreite(int iE)
        {
          if (TabSpalten == null)
            return null;
          //Vector<Integer> Vec=new Vector<Integer>();
          Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Breite","Datentyp"});

          TabSpalten.moveFirst();
          if ((iBits&cstEinzeldaten)>0)
          {
            Tab.addInhalt("Breite",Static.Int0);
            Tab.addInhalt("Datentyp","x");
            int i=0;
            for(;!TabSpalten.eof();TabSpalten.moveNext())
              i=Math.max(i,getLaengeD(TabSpalten));
            Tab.addInhalt("Breite",new Integer(i));
            Tab.addInhalt("Datentyp","x");
          }
          else if (iE>0)
            for (int i=0;i<iE;i++)
            {
              Tab.addInhalt("Breite",Static.Int0);
              Tab.addInhalt("Datentyp","x");
            }
          //boolean bGruppe=(TabSpalten.getI("bits")&cstGruppiert)>0 && (iBits&cstVorletzteEbene)==0;
          //boolean bFirst=false;
          //int iGruppe=0;
          for(;!TabSpalten.eof();TabSpalten.moveNext())
          {
            //if (bGruppe && (TabSpalten.getI("bits")&cstGruppiert)==0)
            //  bFirst=true;
            //if (bFirst && bGruppe && !TabSpalten.isNull("Ergebnisart"))
            //  Vec.addElement(new Integer(-2));
                int iL=getLaengeD(TabSpalten);
                Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr");
                for(int iPer=0;iPer<(VecMehr==null?1:VecMehr.size()+(PerZS() ? 1:0));iPer++)
                  if (bAlleSpalten || (TabSpalten.getI("bits")&cstUnsichtbar)==0)
                  {
                    Tab.addInhalt("Breite",new Integer(iL));
                    Tab.addInhalt("Datentyp",TabSpalten.getInhalt("Datentyp"));
                  }
                //bGruppe=(TabSpalten.getI("bits")&cstGruppiert)>0 && (iBits&cstVorletzteEbene)==0;
            /*if (bGruppe)
              iGruppe++;
            if (iGruppe>0 && bFirst && (iBits&cstVerdichten)>0)
              for (int i=0;i<iGruppe;i++)
                Vec.removeElementAt(Vec.size()-1);
            bFirst=false;*/
          }
          //g.progInfo("getVecDruckbreite:"+Vec);
          return Tab;
        }

        private int getPosV(int iPos)
        {
          int iMom=0;
          for (int i=0;i<TabSpalten.size();i++)
          {
            if (Sichtbar(i))
              iMom++;
            if (iPos==iMom)
              return i;
          }
          return -2;
        }

        public double getX(int iPos)
        {
          return TabSpalten.getF(getPosV(iPos),"x");
        }

        public double getY(int iPos)
        {
          return TabSpalten.getF(getPosV(iPos),"y");
        }

        public int getBits2(int iPos)
        {
          //TabSpalten.setPos(iPos-1);
          return TabSpalten.getI(getPosV(iPos),"bits2");
        }



	/*
	public static Tabellenspeicher TabToTabScreen(Global g,Tabellenspeicher TabDaten,ShowAbfrage A)
	{
		// Ausstieg wenn keine Spalten existieren
		if (A.TabSpalten.isEmpty())
		{
			//Static.printError("Abfrage.TabToTabScreen: keine Spalten vorhanden!");
			//TabDaten.clearAll();
			return null;
		}

		Tabellenspeicher TabSpalten=A.TabSpalten;
		Vector Vec2=A.getTitel();

		Tabellenspeicher Tab= new Tabellenspeicher(g,A.VecBezeichnung);

		Vector Vec=new Vector();
		for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
		{
			Vec.removeAllElements();
			for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
			{
				Vector VecMehr=(Vector)TabSpalten.getInhalt("mehr");
				int iAnz=VecMehr==null?1:VecMehr.size();
				int i=Vec.size();
				for(int iPer=0;iPer<iAnz;iPer++)
					Vec.addElement(TabToScreen(g,Vec2==null?VecMehr!=null ? "e"+TabSpalten.getS("Kennung2")+VecMehr.elementAt(iPer):TabSpalten.getS("Kennung"):Vec2.elementAt(i+iPer).toString(),TabDaten,TabSpalten,VecMehr!=null || Vec2 != null));
				if(VecMehr != null && (TabSpalten.getI("bits")&cstPeriodensumme)>0)
				{
					double d=0;
					for(int iPer=Vec.size()-iAnz;iPer<Vec.size();iPer++)
						d+=Sort.getf(Vec.elementAt(iPer));
					Vec.addElement(doubleToScreen(g,d,TabSpalten));
				}
			}
			Tab.addLine(Vec);
		}
		//if (!bTest)
		//	TabDaten.clearAll();
		return Tab;
	}
	*/

       private Memo1 getMemo(Tabellenspeicher Tab,String sAic,int iL)
       {
         int iPos=sAic.lastIndexOf("_");
         String s=null;
         if (iPos>-1 || Tab.exists("aic_stamm"))
         {
        	 int iSatz=iPos==-1 ? Tab.getI("aic_stamm"):Tab.getI("v"+sAic.substring(1,iPos));
        	 s=SQL.getString(g,"select memo from daten_memo where aic_tabellenname="+g.iTabStamm+" and aic_fremd="+iSatz+" and aic_sprache="+g.getSprache());
         }
         return new Memo1(Static.beiLeer(s,Tab.getM(sAic)),iL);
       }

       /*private String Hier(Tabellenspeicher Tab, Tabellenspeicher TabSpalten,char c)
       {
            int iSoll=TabSpalten.getI("Stt");
            String s3=TabSpalten.getS("Kennung2");
            int iIst=Tab.getI("w"+s3);
            int iPos=s3.lastIndexOf("_");
            while(iSoll !=iIst && iPos>0)
            {
              s3=s3.substring(0,iPos);
              if (Tab.exists("w"+s3))
              {
                iIst = Tab.getI("w"+s3);
                iPos = s3.lastIndexOf("_");
              }
              else
              {
                iIst = 0;
                iPos = 0;
              }
              //g.progInfo(s3+"/"+iIst+"/"+iPos);
            }
            //g.progInfo("Ist/Soll="+iIst+"/"+iSoll+"="+Tab.getS(c+s3));
            return iIst==iSoll ? Tab.getS(c+s3):null;
      }*/

      private static void setNull(Object Obj)
      {
        if (Obj == null)
          return;
        else if (Obj instanceof Waehrung)
          ((Waehrung)Obj).setNull();
        else if (Obj instanceof Mass)
          ((Mass)Obj).setNull();
        else if (Obj instanceof Zahl1)
          ((Zahl1)Obj).setNull();
        else if (Obj instanceof VonBis)
          ((VonBis)Obj).setNull();
        else
          Static.printError("ShowAbfrage.setNull: Objekt "+Obj.getClass().getName()+" nicht unterstützt!");
      }
      
    public void setWeb()
    {
    	bWeb2=true;
    }
    
    public void clearWeb()
    {
    	bWeb2=false;
    }

	public Object TabToScreen(Global g,String sAic, Tabellenspeicher Tab, Tabellenspeicher TabSpalten,boolean bPeriode)
	{
		//if (bJavaFX)
		if (sAic.equals("") || sAic.startsWith("summe"))
		{
			double d=0;
			Vector Vec=(Vector)TabSpalten.getInhalt("mehr");
			//g.progInfo("TabToScreen:"+Vec);
			for(int i=0;i<Vec.size();i++)
				d+=Tab.getF((String)Vec.elementAt(i));
			return doubleToScreen(g,d,TabSpalten);
		}
        if (Tab.exists(sAic) && !Tab.isNull(sAic) && Tab.getInhalt(sAic) instanceof AddUp)
          return Tab.getInhalt(sAic);
		//else
		//	sAic=(String)oAic;
		String sDatentyp = TabSpalten.getS("Datentyp");
		if ((sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe")) && Tab.exists(sAic) && !Tab.isNull(sAic) && Tab.getS(sAic).equals(""))
		{
//			g.fixtestError("setze von Leer auf null");
			Tab.setInhalt(sAic, null);
		}
		//g.fixtestInfo("TabToScreen von "+TabSpalten.getS("Bezeichnung")+"/"+sAic+", Zeile "+Tab.getPos()+":"+bJavaFX+"/"+sDatentyp);//+"->"+Tab.getS(sAic));
//		if (sDatentyp.endsWith("Hierarchie"))
//			g.fixtestError(sDatentyp+":"+Tab.getS(sAic));
    int iDArt=TabSpalten.getI("bits3")&cstDatenHolen;
                if (sDatentyp.equals("Outliner"))
                  return Tab.getInhalt(sAic);
                if (g.Def() && sDatentyp.equals("Mandant") && sAic.startsWith("E"))
                  return g.getMandant(Tab.getI("v"+sAic.substring(1)), "null");
                if (sDatentyp.equals("Favorit") && iDArt==cstDefDaten)
                	return bWeb2 ? Favorit.is(g,Tab.getI(sAic)):new Favorit(g,Tab.getI(sAic));
                if (sDatentyp.equals("Formular") || sDatentyp.equals("Abfrage") || sDatentyp.equals("Modell"))
                	return Tab.getI(sAic);//new FxFom(Tab.getI(sAic),Tab.getI("aic_stamm"));//TabSpalten.getI("SubFom");
                if (TabSpalten.getI("Stt")>0 && TabMehr != null)
                {
                  String sNeu="V"+sAic.substring(1,sAic.lastIndexOf("_"));
                  //if (TabSpalten.getI("Stt")!=SQL.getInteger(g,"select aic_stammtyp from stamm where aic_stamm="+Tab.getI(sNeu)))
                  if (Tab.getI(sNeu)==0)
                    return null;
                  else if (!TabMehr.posInhalt("aic_Stamm",Tab.getI(sNeu)))
                  {
                    g.printError("TabToScreen: Stammsatz "+Tab.getI(sNeu)+" in TabMehr nicht gefunden!",iBegriff);
                    return "???";
                  }
                  else if (TabMehr.getI("AIC_Stammtyp") != TabSpalten.getI("Stt"))
                    return null;
                  //g.progInfo("sNeu="+sNeu);
                }
                int iAnzeige=TabSpalten.getI("Anzeige");
                if (iAnzeige>0)
                {
                    String sAnzeige=g.TabCodes.getKennung(iAnzeige);
                    //g.fixtestInfo(TabSpalten.getS("Bezeichnung")+":"+sDatentyp+"/"+sAnzeige);
                    if (sAnzeige.equals("currency"))
                      return g.TabWaehrung.posInhalt("aic_stamm",Tab.exists("V"+sAic.substring(1)) ? Tab.getI("V"+sAic.substring(1)):g.getWaehrung())?g.TabWaehrung.getS("Bezeichnung"):"";
                    else if (sAnzeige.equals("timestamp") || sAnzeige.equals("date"))
                      return new Zeit((Date)Tab.getInhalt((sAnzeige.equals("timestamp")?"T":"A")+sAic.substring(1)),TabSpalten.getS("Format"));
                    else if (sAnzeige.equals("zone"))
                    	return Tab.getI("Z"+sAic.substring(1));
                      //return new Zeit(Tab.getI("Z"+sAic.substring(1))*60000-new DateWOD(0).get(DateWOD.ZONE_OFFSET),TabSpalten.getS("Format"));
                    else if (sAnzeige.equals("user") || sAnzeige.equals("place") || sAnzeige.equals("os") || sAnzeige.equals("usage") || sAnzeige.equals("computer") || sAnzeige.startsWith("ldate") || sAnzeige.startsWith("AIC_"))
                      return Tab.getS((sAnzeige.equals("user")?'U':sAnzeige.equals("os")?'O':sAnzeige.equals("place")?'P':sAnzeige.equals("usage")?'Q':sAnzeige.equals("AIC_Daten")?'D':sAnzeige.equals("AIC_Stammpool") || sAnzeige.equals("AIC_Protokoll") ?'S':
                                       sAnzeige.equals("computer")?'C':sAnzeige.equals("ldate")?'L':sAnzeige.equals("ldate2")?"L2":sAnzeige.equals("ldate3")?"L3":'X')+sAic.substring(1));
                    else if (sAnzeige.startsWith("anr"))
                    {
                      int iAic=Tab.getI((sAnzeige.equals("anr")?"R":"R"+sAnzeige.substring(3)+"_"/*sAnzeige.equals("anr2")?"R2_":"R3_"*/)+sAic.substring(1));
                      if (iAic>0)
                      {
                        String s = g.getStamm(iAic);//SQL.getString(g,"select bezeichnung from stammview2 where aic_stamm="+iAic);
                        return s.equals("") ? "Aic="+iAic:s;
                      }
                      else
                        return "";
                    }
                }
                int iAuswert=TabSpalten.getI("Auswertformat");
                if (iAuswert>0)
                {
                  //String sAuswert=g.TabCodes.getKennung(iAuswert);
                  //g.progInfo("Auswertformat="+sAuswert);
                  String s=Tab.getAF(sAic,g.TabCodes.getKennung(iAuswert),getLaenge(TabSpalten));
                  if (s != null)
                    return s;
                }
                boolean bKeineEinheit= (TabSpalten.getI("Bits")&cstKeineEinheit)>0;
                //g.progInfo("TabToScreen:"+sAic+"/"+sDatentyp+":"+bPeriode);
		if (sAic.startsWith("count_"))
			return Tab.getInt(sAic);
		if (sDatentyp.equals("BewVon_Bis"))
		{
			//g.fixtestError("TabToScreen "+sDatentyp+":"+((iBits&cst0Werte)>0)+"/"+Tab.getF((bPeriode?"":"D")+sAic));
                  if (TabSpalten.getS("Format").endsWith("%"))
                    return Tab.proz((bPeriode?"":"D")+sAic,TabSpalten.getS("Format"));
                  else if ((iBits&cst0Werte)>0 && Tab.getF((bPeriode?"":"D")+sAic)==0 && (bPeriode || Tab.isNull("V" + sAic) || Tab.isNull("B" + sAic)))
                    return null;
                  else
                  {
                    //g.progInfo("VonBis-ermitteln:"+(bPeriode ? "" : "D") + sAic+"->"+Tab.getS((bPeriode ? "" : "D") + sAic));
                	  //TODO Von oder Bis
                	String sKZ=TabSpalten.getS("Stil");
        			//g.fixtestError("KZ="+sKZ+", Periode="+bPeriode+", "+Tab.getInhalt("V" + sAic)+"-"+Tab.getInhalt("B" + sAic));
                	//g.fixtestError(sDatentyp+", Web="+bWeb2+", Format="+TabSpalten.getS("Format"));
                	if (bWeb2 && TabSpalten.getS("Format").equals("-"))
                		return new Mass(g,g.getMass(TabSpalten.getI("Stamm"), bKeineEinheit), Tab.getF((bPeriode ? "" : "D") + sAic), null);
                	else if (sKZ==null || !sKZ.equals("von") && !sKZ.equals("bis"))
                      return new VonBis(bPeriode ? null : Tab.getInhalt("V" + sAic), bPeriode ? null : Tab.getInhalt("B" + sAic), Tab.getF((bPeriode ? "" : "D") + sAic)*((TabSpalten.getI("bits3")&cstInStunden)>0 ? 3600:1),
                                      Static.beiLeer(TabSpalten.getS("Format"), "HH:mm"), getLaenge(TabSpalten), g,TabSpalten.getI("Stamm"), bKeineEinheit,(TabSpalten.getI("bits2") & cstSpGMT)==0 ? 0:Tab.getI("Zone"));
                	else if (Tab.isNull(sAic))
                		return new Zeit(null,null);
                	else
                	  return new Zeit(((Date)Tab.getInhalt(sAic)).getTime() - ((TabSpalten.getI("bits2") & cstSpGMT)==0 ? 0:Tab.getI("Zone") * 60000), Static.beiLeer(TabSpalten.getS("Format"), "dd.MM.yyyy"));
                  }
		}
		if (sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis"))
			//VecSpalten.addElement(new Zeit((Date)Tab.getInhalt("V"+sAic),Static.beiLeer(TabSpalten.getS("Format"),"HH:mm"))+
			//	" - "+new Zeit((Date)Tab.getInhalt("B"+sAic),Static.beiLeer(TabSpalten.getS("Format"),"HH:mm")));
			return new VonBis(g,bPeriode?null:Tab.getInhalt("V"+sAic),bPeriode?null:Tab.getInhalt("B"+sAic),Static.beiLeer(TabSpalten.getS("Format"),"HH:mm"));
//		if ((sDatentyp.equals("Bool3") || sDatentyp.equals("BewBool3")) && bJavaFX)
//			return new Combo(Tab.getS(sAic),Tab.getI("V"+sAic.substring(1)),"",0);
        if ((sDatentyp.equals("Bool3") || sDatentyp.equals("BewBool3")) && (bWeb2 || TabSpalten.getI("Faktor")>0))
        {
          int iAic=Tab.getI("V"+sAic.substring(1));
          if (bWeb2)
          {
        	  try
        	  {
            	  if (iAic==0)
            		return g.TabAuswahl.getI(g.TabAuswahl.getPos("Aic_eigenschaft",TabSpalten.getI("Kennung2")),"Aic");
        	  }
        	  catch (Exception e)
        	  {
        		  g.printError("TabToScreen.Bool3: Eigenschaft "+TabSpalten.getS("Kennung2")+" nicht gefunden");
        	  }
        	  return iAic;//g.TabAuswahl.getI(iPos,"Nr");
          }
          int iPos=g.TabAuswahl.getPos("Aic",iAic);
          return iPos<0 || TabSpalten.getI("Faktor")!=g.TabAuswahl.getI(iPos,"Nr") ? null:"x";//g.TabAuswahl.getS(iPos,"Kennung");
        }
		if (sDatentyp.endsWith("Boolean") || sDatentyp.equals("Vorgaenger") || sDatentyp.equals("Favorit") && iDArt==cstDanachD)
                {
                  boolean b=TabSpalten.getF("Faktor") == -2 ? !Tab.getB(sAic) : Tab.getB(sAic);
                  if (bWeb2) return b;
                  String sF=TabSpalten.getS("Format");
                  Object Obj= sF.equals("") ? (iBits&cstEinzeldaten)>0 ? Static.JaNein(b):new JaNein(b):b ? sF:""; // Drehung spießt sich bei AUTable
                  int iL=getLaenge(TabSpalten);
                  return iL>0 && sF.equals("") && (""+Obj).length()>iL ? (""+Obj).substring(0,iL):Obj;
                }
                if (sDatentyp.equals("Passwort"))
                  return new PW1(Tab.getS(sAic));
                if (bWeb2 && (/*sDatentyp.equals("Bild2") ||*/ sDatentyp.equals("Doku")))
                {
                  String s=Tab.getS(sAic);
                  int iDaten=s!= null && s.length()>2 && s.indexOf('_')>0 ? Sort.geti(s.substring(0,s.indexOf('_'))):0;
                  if (iDaten==0 || s.lastIndexOf('.')<0)
                	  return null;         
                  
                  /*JSONObject jO=new JSONObject();
                  jO.put("aic", iDaten);
                  jO.put("name", s.substring(s.indexOf('_')+1));
              	  String sExt=s.substring(s.lastIndexOf('.')+1);
              	  if (sExt.equals("jpg") || sExt.equals("jpe"))
          			sExt="jpeg";      
              	  
//                  	if (sDatentyp.equals("Bild2"))
//                  	{
//                  		Image Img=SQL.getImage(g,iDaten);              
//                  		jO.put("data",Static.ImageToString(Img,sExt).substring(10));
//                  		jO.put("type", "image/"+sExt);
//                  	}
//                  	else
//                  	{
              		String sType=null;
              		if (sDatentyp.equals("Bild2") || sExt.equals("jpeg") || (sExt.equals("png")))
              			sType="image/"+sExt;
//              		else if (sExt.equals("pdf"))
//              			sType="application/"+sExt;
//            		else if (sExt.equals("jpeg") || (sExt.equals("png")))
//            			sType="image/"+sExt;
            		else 
            			sType="application/"+sExt;
                  	jO.put("type", sType);
                    if ((TabSpalten.getI("bits")&cstBild)>0)
                    {
                  		byte[] bAry=sDatentyp.equals("Bild2") ? SQL.getImage2(g,iDaten):SQL.getDoku2(g,iDaten);
                  		jO.put("data",Static.DokuToString(bAry,sType));
                  		jO.put("size",bAry.length);
                    }
                  	return jO;*/
                    return "[JSONObject]";
                }
                //if ((TabSpalten.getI("bits")&cstBild)>0)
                //  g.progInfo("Bild für "+);
                if ((TabSpalten.getI("bits")&cstBild)>0 && !sDatentyp.equals("Farbe"))
                {
                  String sSpalte=(sDatentyp.equals("Eintritt") || sDatentyp.endsWith("BewDatum") ? "M":sDatentyp.equals("SysAic") || bPeriode && iBew>0 ? "E" : "V") + sAic.substring(1);
                  //g.fixtestError("Bild gesetzt für Dt="+sDatentyp+":"+Tab.getI(sSpalte));
                  if (sDatentyp.equals("Text"))
                    return getMemo(Tab,sAic,getLaenge(TabSpalten));
                  //else if (sDatentyp.equals("Farbe"))
                  //{
                    //Col= Tab.isNull(sAic)?null:new Color(Tab.getI(sAic));
                  //  return null;
                  //}
                  else if (sDatentyp.equals("Ampel"))
                    return bWeb2 ? Ampel.getNr(Tab.getI("AIC_STAMM")):Ampel.set(g.getAmpel(TabSpalten.getI("Kennung2"),Tab.getI("AIC_STAMM")));
                  else if (sDatentyp.equals("Doku")) // Doku als Bild aktuell nicht zurücklieferbar
                	  return null;
                  else if (sDatentyp.equals("Bild2"))
                  {
                    String s=Tab.getS(sAic);
//                    if (bWeb2)
//                    	return s;
                    int iDaten=s!= null && s.length()>2 && s.indexOf("_")>0 ? Sort.geti(s.substring(0,s.indexOf("_"))):0;
                    Image Img=iDaten>0 ? SQL.getImage(g,iDaten,bWeb2):null;
                    return Img==null ? null:TabSpalten.getI("min")>0 && TabSpalten.getI("max")>0 ? BildEingabe.shrink(Img,TabSpalten.getI("min"),TabSpalten.getI("max"),Image.SCALE_SMOOTH):Img;
                  }
                  else if (sDatentyp.endsWith("Bild"))
                  {
                	  if (bWeb2)
                      	return Tab.getS(sAic);
                    Image Img= g.LoadImage(Tab.getS(sAic), Static.DirImageStamm);
                    g.progInfo("Bild "+Tab.getS(sAic)+":"+Img);
                    if (Img==null && !Tab.isNull(sAic))
                      return Static.cutString(Tab.getS(sAic).replaceAll("_"," "));
                    //if (TabSpalten.getI("min")>0 && TabSpalten.getI("max")>0)
                    return Img==null ? null:TabSpalten.getI("min")>0 && TabSpalten.getI("max")>0 ? BildEingabe.shrink(Img,TabSpalten.getI("min"),TabSpalten.getI("max"),Image.SCALE_SMOOTH):Img;//Img.getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING);
                    //return Img;
                  }
                  else if (Tab.exists(sSpalte) ? Tab.isNull(sSpalte):Tab.isNull("E"+sAic.substring(1)))
                    return null;
                  else if (sDatentyp.endsWith("Eintritt") || sDatentyp.endsWith("BewDatum"))
                    return Tab.getS(sSpalte);//new Zeit(new DateWOD(1970,Tab.getI(sSpalte),1).toTimestamp(),TabSpalten.getS("Format"));
                  else
                  {
                	int iAic=Tab.getI(sSpalte);
                	Ampel.check(g);
                	//g.fixtestError("Ampel für "+Tab.getI(sSpalte)+"/"+bJavaFX+"->"+Ampel.isAmpel(iAic));
                    if (Ampel.isAmpel(iAic))
                	   return bWeb2 ? iAic /*Ampel.getNr(iAic)*/:Ampel.setStamm(iAic);                 
                    else
                    {
                      Image Gif=g.getGif(g.TabStammBild, iAic);
                      if(Gif != null)//g.TabStammBild.posInhalt("Aic", Tab.getI(sSpalte)))
                        return Gif;//g.TabStammBild.getInhalt("Bild");
                    }
                  }
                }
                if ((TabSpalten.getI("bits2")&cstAIC_Bez)>0)
                {
                	String sX=sAic.substring(0,sAic.lastIndexOf('_'));
//                	g.fixtestError("cstAIC_Bez gesetzt bei "+sAic+"/"+sX);
                	int iAic=Tab.getI("v"+sX.substring(1));
                	if (iAic==0)
                		return null;
                	Combo C=new Combo(Static.beiLeer(Tab.getS(sAic),Tab.getS(sX)),iAic,"",0);
//                	g.fixtestError("AIC_Bez bei "+Tab.getI("aic_stamm")+": "+C.print());
                	return C;
//                	return new Combo(
                }
                if (sDatentyp.equals("Ampel"))
                  return bWeb2 ? null:Ampel.set(g.getAmpel(TabSpalten.getI("Kennung2"),Tab.getI("AIC_STAMM")));
                  //return Ampel.set(g,(int)Math.round(Math.random()*3));
		//g.progInfo(Tab.getInhalt(sAic)+":"+sDatentyp+"/"+bPeriode);
                /*if ((iBits&cstHierarchie)>0 && sDatentyp.equals("Hierarchie"))
                {
                  return Hier(Tab,TabSpalten,'e');
                }*/
                if (sDatentyp.equals("Farbe"))
        			return bForExport ? Tab.isNull(sAic) ? null:Tab.getI(sAic):Tab.isNull(sAic) && TabSpalten.getI("Farbe")==0 ? null:new Farbe(Tab.isNull(sAic) ? g.getColor(TabSpalten.getI("Farbe")):Tab.getI(sAic));
    			if (sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe"))
    	    {
    				boolean bLeer=Static.Leer(Tab.getS(sAic));
            String sAIC="V"+sAic.substring(1);
    				int iAic=Tab.exists(sAIC) ? Tab.getI(sAIC):0;
    	        	if (iAic==0)
    	        		if (bLeer)
    	        			return null;
    	        		else
    	        			return Tab.getS(sAic);
    	        	else
    	        		return new Combo(bLeer ? g.getStamm(iAic):Tab.getS(sAic),iAic,"",0);  
    	    }
    			else if (sDatentyp.equals("Filler") || !(sDatentyp.equals("GPS") && !Tab.isNull("B"+sAic.substring(1))) && (!Tab.exists(sAic) || Tab.getInhalt(sAic)==null) && !bPeriode && (iBits&cst0Werte)==0)
    				return null;
		//if (sDatentyp.endsWith("Land"))
		//	return g.TabLand.getBezeichnungS(Tab.getI(sAic));

		Double Dbl = Tab.getInhalt(sAic)==null ? new Double(0.0):Tab.getInhalt(sAic) instanceof Double ? TabSpalten.getF("Faktor") != 0.0 && Tab.getF(sAic) != 0.0 ? new Double(Tab.getF(sAic)*TabSpalten.getF("Faktor")):
			(Double)Tab.getInhalt(sAic):Tab.getInhalt(sAic) instanceof Number ? new Double(Sort.getf2(Tab.getInhalt(sAic))):null;
//		if (Dbl != null && (TabSpalten.getI("min")>0 || TabSpalten.getI("max")>0))
//		{
//			double d=Dbl.doubleValue();
//			g.fixtestError("vergleiche "+d+" mit "+TabSpalten.getI("min")+"-"+TabSpalten.getI("max"));
//			if (TabSpalten.getI("max")!=0 && d>TabSpalten.getI("max"))
//				Dbl=null;
//			if (TabSpalten.getI("min")!=0 && d<TabSpalten.getI("min"))
//				Dbl=null;
//		}

                if (TabSpalten.getS("Format").endsWith("%"))
                  return Tab.proz(sAic,TabSpalten.getS("Format"));

                if ((TabSpalten.getI("Bits")&cstPositiv)>0 && Dbl.doubleValue()<=0)
                  return null;

		if (sDatentyp.equals("Einheiten"))
		{
			//return (new Double(Tab.getF("E"+TabSpalten.getS(sAic)))+" "+Tab.getS("V"+TabSpalten.getS(sAic)));
			/*Vector<Comparable> Vec = new Vector<Comparable>();
			Vec.addElement(new Double(1));
			Vec.addElement(Tab.getS("V"+sAic.substring(1)));
			Vec.addElement(new Integer(0));
                        Mass m=new Mass(Vec,Dbl,TabSpalten.getS("Format"));
                        g.fixtestInfo("Vec-Einheiten="+Tab.getInhalt("e20")+"="+Dbl+" "+Tab.getS("V"+sAic.substring(1))+"->"+m+" bei "+Sort.gets2(Vec,','));
			return m;*/
                    return Dbl+" "+Tab.getS("V"+sAic.substring(1));
		}
		if (sDatentyp.equals("Double") || sDatentyp.equals("BenMass") || sDatentyp.equals("BewDauer") || sDatentyp.startsWith("BewZahl") || sDatentyp.equals("CalcField"))
		{
			//g.progInfo("TabToScreen: "+sDatentyp+"/"+Dbl+"/"+TabSpalten.getS("Format"));
			if ((TabSpalten.getI("bits3")&cstBool)>0)
				return Dbl.doubleValue()>0;
			return bForExport || bWeb2 ? Dbl:new Zahl1(Dbl,TabSpalten.getS("Format"));
		}
		if (sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
                {
                  if (bForExport || bWeb2)
                    return Dbl;
                  else if ((TabSpalten.getI("Bits")&cstEditierbar)>0)
                    return new Waehrung(g.getVecWaehrung2(bKeineEinheit,Tab.getI("V"+sAic.substring(1))), Dbl, TabSpalten.getS("Format"));
                  else //if (TabSpalten.getI("Stamm")>0)
                    return new Waehrung(g.getVecWaehrung(bKeineEinheit,TabSpalten.getI("Stamm")), Dbl, TabSpalten.getS("Format"));
                  //else
                  //  return new Zahl1(Dbl,TabSpalten.getS("Format"));
                }
		if (sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcDauer"))
                {
                  if (bForExport)// || bWeb2)
                  {
                    g.fixInfo("    ! ! !     "+sDatentyp+":"+Dbl+"/"+TabSpalten.getI("Stamm")+"/"+g.getFaktor(TabSpalten.getI("Stamm")));
                    double dF=TabSpalten.getI("Stamm") == 0 ? 0:g.getFaktor(TabSpalten.getI("Stamm"));
                    if (dF == 0 || Dbl==null)
                      return Dbl;
                    else if ((TabSpalten.getI("bits3")&Abfrage.cstHH_mm)>0)
                      return Static.hm(Dbl.doubleValue());
                    else
                      return Static.Round6(Dbl.doubleValue()/dF);
                  }
                  else
                    return new Mass(g,g.getMass(TabSpalten.getI("Stamm") == 0 || (TabSpalten.getI("bits2") & cstOrigEinh) > 0 ? Tab.getI("V" + sAic.substring(1)) : TabSpalten.getI("Stamm"), bKeineEinheit), Dbl, TabSpalten.getS("Format"));
                }
		if (sDatentyp.equals("Integer") || sDatentyp.equals("SysAic") || sDatentyp.equals("Aic") || sDatentyp.equals("Protokoll") || sDatentyp.equals("BewCount"))
			return Tab.getInt(sAic);
		//if (sDatentyp.equals("BewBool3"))
		//	return new JaNein(Tab.getB(sAic));
		if (sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt")
                    || sDatentyp.equals("Datum") || sDatentyp.equals("CalcDatum") || sDatentyp.equals("Stichtag"))
                {
                  if (Tab.getInhalt(sAic) instanceof Date)
                    return new Zeit((Date)Tab.getInhalt(sAic), Static.beiLeer(TabSpalten.getS("Format"), "dd.MM.yyyy"));
                  else
                    return Tab.getS(sAic);
                }
		if (sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("Zeit") || sDatentyp.equals("Timestamp") || sDatentyp.equals("entfernt"))
                {
                  if (!(Tab.getInhalt(sAic) instanceof Date))
                    return Tab.getS(sAic);
                  else if ((TabSpalten.getI("bits2") & cstSpGMT) > 0)
                  {
                    //g.progInfo(Tab.getInhalt(sAic)+"/"+Tab.getI("Zone"));
                    return new Zeit(((Date)Tab.getInhalt(sAic)).getTime() - Tab.getI("Zone") * 60000, TabSpalten.getS("Format"));
                  }
                  else
                    return new Zeit((Date)Tab.getInhalt(sAic), TabSpalten.getS("Format"));
                }
		if (sDatentyp.equals("Memo") || sDatentyp.equals("Text"))
                  if ((TabSpalten.getI("bits3")&cstHtml)>0)
                    return new Html(Tab.getM(sAic),bHistory ? " ":"\n");
                  else if (bDruck && bHtml)
                	return Tab.getM(sAic);
                  else if (bDruck && !bHtml)
                    return g.getHtmlPart(Tab.getM(sAic),false);
		//	return new Memo1(Tab.getM(sAic),getLaenge(TabSpalten));
                if(Tab.getInhalt(sAic) instanceof Memo1)
                {
                  String s=Static.forExport(Tab.getM(sAic),bForExport);
                  //g.fixtestError("Memo1 bei "+sDatentyp+":"+(s.length()<20 ? s:s.substring(0, 20)));
                  if ((TabSpalten.getI("bits2")&cstKeineLeerzeilen)>0)
                    s = Static.KillLeerzeilen(s,false);
                  return new Memo1(s, getLaenge(TabSpalten));
                }
		
                if (sDatentyp.equals("Rolle"))
                  return g.TabRollen.getBezeichnungS(Tab.getI(sAic));
//                if (sDatentyp.equals("BewHierarchie"))
//            		g.fixtestError(sDatentyp+":"+Tab.getS(sAic)+"/"+(iBits&cstHierarchie)+"/"+TabSpalten.getI("Stt")+"/"+(TabSpalten.getI("bits2")&cstStt));
                if (sDatentyp.equals("BewHierarchie") && (iBits&cstHierarchie)>0 && TabSpalten.getI("Stt")>0)
                  return Tab.getI("W"+sAic.substring(1))==TabSpalten.getI("Stt") || (TabSpalten.getI("bits2")&cstStt)==0 ? Tab.getS(sAic):null;
                if (sDatentyp.endsWith("Bezeichnung"))
                {
                  //g.testInfo(Tab.getS(sAic)+":"+Static.className(Tab.getInhalt(sAic)));
                  if(Tab.getInhalt(sAic) instanceof Date)
                    return new Zeit((Date)Tab.getInhalt(sAic), TabSpalten.getS("Format"));
                }
        if (sDatentyp.equals("GPS"))
        {
        	return new GPS(Tab, sAic);
        }
        if ((TabSpalten.getI("bits3")&cstBarcode)>0)
        	return new Barcode(Tab.getS(sAic));
       if (/*bJavaFX && */(iBits&cstSumme)==0 && (sDatentyp.equals("Gruppe") || sDatentyp.equals("BewStamm") || sDatentyp.equals("Hierarchie"))) // seit 10.8.2018 immer (nicht nur bei JavaFX) für setSync
       {
    	   //g.fixtestInfo(TabSpalten.getS("Bezeichnung")+"/"+sDatentyp+":"+Tab.getS(sAic)+"/"+Tab.getI("V"+sAic.substring(1)));
    	   String s=Tab.getS(sAic);
    	   int iL=getLaenge(TabSpalten);
    	   if (iL>0 && s.length()>iL) s=s.substring(0,iL);
    	   return new Combo(s,Tab.getI("V"+sAic.substring(1)),"",0);    
       }
                String s=Static.forExport(Tab.getS(sAic),bForExport);

                if ((TabSpalten.getI("bits2")&cstKeineLeerzeilen)>0)
                  s = Static.KillLeerzeilen(s,false);
		s=getLaenge(TabSpalten)>0 && s.length()>getLaenge(TabSpalten)?s.substring(0,getLaenge(TabSpalten)):s;
                if (bForExport && (TabSpalten.getI("bits")&cstHochkomma)>0)
                  s= Static.changeK1(s);//"\""+s+"\"";
                return /*(TabSpalten.getI("bits2")&cstUmbruch)>0 ? Static.cutString(s):*/s;
	}

        /*private String KillLeerzeilen(String s)
        {
          s = s.trim();
          int iV=0;
          int i=s.length();
          while (i != iV)
          {
            iV=i;
            s = s.replaceAll("\n\n", "\n");
            s = s.replaceAll("\n ", "\n");
            //s = s.replaceAll("\n", "          ");
            i=s.length();
          }
          //Static.replaceString
          return s;
        }*/

        // private void addDatentyp(String sDT)
        // {
        //   int iPos=g.TabEigenschaften.getPos("Datentyp",sDT);
        //   while(iPos>=0 && iPos<g.TabEigenschaften.size() && (g.TabEigenschaften.getI(iPos,"bits")&Global.cstDefault)==0)
        //     iPos=g.TabEigenschaften.getNextPos(iPos,"Datentyp",sDT);
        //   if (iPos>=0)
        //   {
        //     TabSp.newLine();
        //     Vector<Object> Vec=new Vector<Object>();
        //     Vec.addElement(g.TabEigenschaften.getInhalt("Aic",iPos));
        //     TabSp.setInhalt("Vec", Vec);
        //     TabSp.setInhalt("bits", cstAnzeigen);
        //     //TabSpalten.newLine();
        //     //TabSpalten.setInhalt("Bezeichnung",  g.TabEigenschaften.getS("Bezeichnung"));
        //     //TabSpalten.setInhalt("Kennung", g.TabEigenschaften.getS("Kennung"));
        //     //TabSpalten.setInhalt("Datentyp", sDT);
        //     //TabSpalten.setInhalt("bits", cstAnzeigen);
        //   }
        //   else
        //     g.defInfo("addDatentyp:"+sDT+" nicht gefunden!");
        //   //TabSpalten.showGrid();
        // }

        //@SuppressWarnings("unchecked")


        public static String PoolDaten(Global g,String s)
        {
          String sP=g.MS() ? "+":"||";
          if (s.equals("WWW") || s.equals("Memo") || s.equals("Pfad") || s.equals("Filename") || s.equals("StringKurzOhne") || s.equals("Passwort") || s.equals("E-Mail") || s.equals("FixDoku"))
            s = "Stringx";
//          else if (s.equals("StringKurzOhne") || s.equals("Passwort"))
//            s = "Stringx";//"StringKurz";
//          else if (s.equals("E-Mail") || s.equals("FixDoku"))
//            s = "Stringx";//"String60";

          if (s.equals("Gruppe") || s.equals("Hierarchie") || s.equals("Mehrfach"))
            return "(select bezeichnung from stammview2 where aic_stamm=sta_aic_stamm and aic_rolle is null)";
          else if (s.equals("StringSehrKurz") || s.equals("StringKurz") || s.equals("String60") || s.equals("StringLang") || s.equals("Stringx") || s.equals("Text"))
            return "(select spalte_"+s+" from daten_"+s+" where aic_daten=p.aic_daten)";
          else if (s.equals("Zeit"))
            return "(select Zeit_von from Zeit_von_bis where aic_daten=p.aic_daten)";
          else if (s.equals("von_bis") || s.equals("Auto_von_bis"))
            return "(select DATEFORMAT(Zeit_von,'dd.MM.yyyy HH:mm')"+sP+"' - '"+sP+"DATEFORMAT(Zeit_bis,'dd.MM.yyyy HH:mm') from Zeit_von_bis where aic_daten=p.aic_daten)";
          /*else if(s.equals("Stringx"))
            return "(case when spalte_double<11 then (select spalte_stringsehrkurz from daten_StringSehrKurz where aic_daten=p.aic_daten)"+
                " when spalte_double<31 then (select spalte_stringkurz from daten_StringKurz where aic_daten=p.aic_daten)"+
                " when spalte_double<61 then (select spalte_string60 from daten_String60 where aic_daten=p.aic_daten)"+
                " when spalte_double<256 then (select spalte_stringLang from daten_StringLang where aic_daten=p.aic_daten)"+
                " else (select spalte_text from daten_text where aic_daten=p.aic_daten) end)";*/
          else if(s.equals("Integer") || s.equals("Double") || s.equals("Boolean"))
            return "spalte_double";
          else if(s.equals("Mass") || s.equals("Waehrung"))
            return (g.MS() ?"cast (spalte_double as varchar)":"spalte_double")+sP+"' '"+sP+"(select bezeichnung from stammview2 where aic_stamm=sta_aic_stamm)";
          else if(s.startsWith("Bew"))
            return "'Datentyp "+s+"'";
          else if(s.equals("Eintritt") || s.equals("Datum"))
            return "gultig_von";
          else if(s.equals("Bild") || s.equals("Bild2") || s.equals("Doku"))
            return "(select filename from daten_"+(s.equals("Bild")?"image":s)+" where aic_daten=p.aic_daten)";
            //g.testInfo("ShowAbfrage.PoolDaten: "+s+" wird bei showHistory3 noch nicht unterstützt");
          else if(s.equals("Bool3"))
            return g.AU_Bezeichnung("auswahl","p.spalte_double","kennung");
          else
          {
            g.printError("ShowAbfrage.PoolDaten: "+s+" wird bei showHistory3 noch nicht unterstützt");
            return "aic_stammpool";
          }
        }

        // Stamm-History aus Outliner
        public static void showHistory3(Global g,Formular Fom,final int iStamm,Date dtEintritt)
        {
//          g.fixtestError("showHistory3");
          long lClock=Static.get_ms();
          final AUOutliner Gid = new AUOutliner();
          Gid.setRootNode(new JCOutlinerFolderNode(g.getStamm(iStamm)));//SQL.getString(g,"select bezeichnung from stammview2 where aic_stamm="+iStamm)));
          String [] sary = new String[]{g.getBegriffS("Show","von"),g.getBegriffS("Show","bis"),g.getBegriffS("Show","Daten"),g.getBegriffS("Show","Benutzer"),g.getBegriffS("Show","erzeugt")};
          Gid.setColumnButtons(sary);
          Gid.setNumColumns(sary.length);
          Gid.setBackground(g.ColBackground);
          //dtEintritt=SQL.getTimestamp(g,"select eintritt from stammview2 where aic_stamm="+iStamm);
          Vector Vec=SQL.getVector("select p.aic_eigenschaft from stammpool p"+g.join("eigenschaft","p")+g.join("begriff","eigenschaft")+
                                   " where pro2_aic_protokoll is null and begriff.kennung<>'Mehrfach' and (gueltig_bis is not null or gultig_von"+
                                   (dtEintritt==null?" is not null":">"+g.DateTimeToString(dtEintritt))+") and p.aic_stamm="+iStamm+
                                   " group by p.aic_eigenschaft",g);
          SQL Qry=new SQL(g);
          JCOutlinerNodeStyle StyStd = new JCOutlinerComponent().getDefaultNodeStyle();
          StyStd.setForeground(g.ColStandard);
          StyStd.setFont(g.getOutFont(false));//g.fontStandard);
          JCOutlinerNodeStyle StyDel = new JCOutlinerNodeStyle(StyStd);
          StyDel.setForeground(g.ColLoeschen);

          for(int i=0;i<Vec.size();i++)
          {
            Object ObjEig=Vec.elementAt(i);
            int iPos=g.TabEigenschaften.getPos("Aic",ObjEig);
            String sDatentyp=g.TabEigenschaften.getS(iPos,"Datentyp");
            if (g.existsEigenschaft(ObjEig) && !sDatentyp.equals("Datum"))
            {
              //g.progInfo("showHistory3:"+g.TabEigenschaften.getS("Bezeichnung")+"/"+sDatentyp);
              JCOutlinerFolderNode Nod = new JCOutlinerFolderNode(g.TabEigenschaften.getS(iPos,"Bezeichnung"), (JCOutlinerFolderNode)Gid.getRootNode());
              for(Qry.open("select gultig_von,gueltig_bis," + PoolDaten(g,sDatentyp) + " Daten,Benutzer.kennung Benutzer,Timestamp from stammpool p" +
                           " join protokoll p2 on p.aic_protokoll=p2.aic_protokoll"+g.join("logging","p2")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer" +
                           " where pro2_aic_protokoll is null  and p.aic_stamm=" + iStamm + " and p.aic_eigenschaft=" + ObjEig + " order by gultig_von");
                  !Qry.eof(); Qry.moveNext()) {
                Vector<Object> Vec1 = new Vector<Object>();
                DateWOD dt = new DateWOD(Qry.getTS("gultig_von"));
                Vec1.addElement(dt.Format("dd.MM.yyyy"));
                boolean bAkt=Qry.isNull("gueltig_bis");
                if (bAkt)
                  Vec1.addElement("");
                else
                {
                  dt = new DateWOD(Qry.getTS("gueltig_bis"));
                  //if(!dt.isNull())
                  dt.yesterday();
                  Vec1.addElement(dt.Format("dd.MM.yyyy"));
                }
                Vec1.addElement(sDatentyp.equals("Boolean") ? Static.JaNein(Qry.getB("Daten")) : Html.check(Qry.getS("Daten")));
                Vec1.addElement(Qry.getS("Benutzer"));
                Vec1.addElement(/*g.Oracle()?Qry.getS("Timestamp"):*/Qry.getS("Timestamp").substring(0, 16));
                JCOutlinerNode NodAkt=new JCOutlinerNode(Vec1, Nod);
                NodAkt.setStyle(bAkt ? StyStd:StyDel);
              }
            }
          }
          Qry.free();
          final javax.swing.JDialog DlgTab=new javax.swing.JDialog((javax.swing.JFrame)Fom.thisFrame,"History",false);
          DlgTab.getContentPane().add("Center",Gid);
          Gid.setColumnLabelSortMethod(Sort.sortMethod);
          Gid.sortByColumn(0,Sort.sortMethod,JCqsort.ASCENDING);
          DlgTab.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//          Static.Escape(null,DlgTab,null);
          DlgTab.pack();
          Static.centerComponent(DlgTab,(javax.swing.JFrame)Fom.thisFrame);
          g.clockInfo("showHistory3",lClock);
          DlgTab.setVisible(true);
        }

        /*private static Object checkHtml(String s)
        {
          if (s==null || !s.startsWith("<html>"))
            return s;
          else
            return new Html(s," ");
        }*/

        // Bewegungshistory aus Tabelle
        public void showHistory2(final Formular Fom,final int iPool,final int iModell2,final boolean bImport)
        {
//          g.fixtestError("showHistory2");
          //g.progInfo("showHistory2:"+iPool+"/"+iEig+"/"+sAnlage);
          long lClock=Static.get_ms();
          long l=iBits;
          iBits|=cstEntfernte+cstKeinStamm;
          //g.progInfo("Bits:"+l+"->"+iBits);
          TabSp.moveLast();
          TabSp.push();
          // addDatentyp("Benutzer");
          // addDatentyp("Timestamp");
          // addDatentyp("Anlage");
          // addDatentyp("Mandant");

          //addDatentyp("entfernt");
          //addDatentyp("LoeschBenutzer");
          bHistory=true;
          SQL_String();
          //sSQL=VecAbf.elementAt(2)+" and p2.aic_bew_pool";

          //Tabellenspeicher Tab=new Tabellenspeicher(g,sSQL+iPool+VecAbf.elementAt(3),true);
          int iVor=SQL.getInteger(g,"select bew_aic_bew_pool from bew_pool where aic_bew_pool="+iPool);
          Vector<Integer> VecAic=new Vector<Integer>();
          VecAic.addElement(new Integer(iPool));
          while (iVor>0)
          {
            //Tab.addTab(new Tabellenspeicher(g,sSQL+iVor+VecAbf.elementAt(3),true));
            VecAic.addElement(new Integer(iVor));
            iVor=SQL.getInteger(g,"select bew_aic_bew_pool from bew_pool where aic_bew_pool="+iVor);
          }
          Tabellenspeicher Tab=new Tabellenspeicher(g,VecAbf.elementAt(2)+" and p2.aic_bew_pool"+Static.SQL_in(VecAic)+Ebenen()+" order by aic_bew_pool desc",true);
          //Tab.showGrid("History");
          final AUOutliner GidGesamt = new AUOutliner();
          final AUOutliner GidSelect = new AUOutliner();
          GidSelect.addActionListener(new JCActionListener() {
            public void actionPerformed(JCActionEvent ev) {
              // g.fixtestError("Protokoll="+GidSelect.getSelectedNode());
              Logging.ProtInfo(g,Sort.geti(GidSelect.getSelectedNode().getLabel(), 12),Fom.thisJFrame());
            }
          }); 
          
//          //iH=50;
//          GidSelect.setMinimumSize(new Dimension(120,iH));
//          g.fixtestError("showHistory2 - setze Minimum auf:"+GidSelect.getMinimumSize());
          final JButton BtnUndel=g.getButton("Undelete");
          // Dimension D=new Dimension(120,24);
          // BtnUndel.setPreferredSize(D);
          final JEditorPane Memo = new JEditorPane();//new AUTextArea(g,0);
          Memo.setEditable(false);
          Memo.setContentType("text/html");
          BtnUndel.setEnabled(false);
          initOutliner(g,GidGesamt);
          final JSplitPane PnlC = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,GidGesamt,Memo);
          GidGesamt.addItemListener(new JCOutlinerListener()
          {
            public void itemStateChanged(JCItemEvent e) {
            }

            public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {
            }

            public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {
            }

            public void outlinerNodeSelectBegin(JCOutlinerEvent e) {
            }

            public void outlinerNodeSelectEnd(JCOutlinerEvent e)
            {
              int iAIC = GidGesamt.getSelectedNode().getLevel()>0 && GidGesamt.getSelectedNode().getUserData()!=null ? ((Integer)GidGesamt.getSelectedNode().getUserData()).intValue():0;
              String sBenutzerZeit = g.AU_Bezeichnung1("Benutzer","Protokoll")+" Benutzer_Prot"+g.AU_Bezeichnung1("Benutzer","Logging")+"Benutzer_Log,(select kennung from computer where aic_computer=logging.aic_computer) Computer,"+
                  "Timestamp,(select defbezeichnung from begriff where aic_begriff=PLACE) Ort,(select kennung from code where code.aic_code=protokoll.aic_code) Art,(select kennung from code where code.aic_code=Logging.aic_code) Anlage";
              String sStatus = ","+g.ifnull("pro_aic_protokoll","null","(select kennung from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer"+g.join2("protokoll","logging")+" where aic_protokoll=pro_aic_protokoll)")+" entfernt_von"+
                  ","+g.ifnull("pro_aic_protokoll","null","(select Timestamp from protokoll where aic_protokoll=pro_aic_protokoll)")+" entfernt_um";

              Tabellenspeicher Tab2=new Tabellenspeicher(g,"select aic_bew_pool,zone,gueltig"+sBenutzerZeit+sStatus+",Bew_Pool.aic_protokoll from Bew_Pool join protokoll on Bew_Pool.aic_protokoll=protokoll.aic_protokoll"+g.join("logging","protokoll")+" where aic_bew_pool="+iAIC,true);
              Tab2.showGrid(GidSelect);
              g.initOutliner(GidSelect,new String[] {"Aic","Zone","Datum","Benutzer_Prot","Benutzer_Log","Computer","Timestamp","Ort","Art","Anlage","entfernt_von","entfernt_um","Protokoll"});
//              iH+=10;
//              Dimension D=new Dimension(120,iH);
//              GidSelect.setMinimumSize(D);
//              GidSelect.setPreferredSize(D);
//              g.fixtestError("GidSelect - setze Minimum auf:"+GidSelect.getMinimumSize());
              //g.initOutliner(GidSelect,new String[] {"Aic","Datum","Benutzer","Computer","Timestamp","Art","entfernt_von","entfernt_um"});
              //g.progInfo("showHistory2: sAnlage="+sAnlage+", iAic="+iAIC+",Geloeschte="+g.Geloeschte()+",Abschluss="+g.getAbschlussDate(Fom.getBegriff(),iBew,0));
              if (/*sAnlage != null &&*/ g.Geloeschte() && Fom!=null)
              {
                Date dt=g.getAbschlussDate(/*Fom.getBegriff(),*/ iBew, 0);
                BtnUndel.setEnabled(iAIC > 0 && (dt == null || Tab2.isNull("gueltig") || dt.before(Tab2.getDate("gueltig"))) && !Tab2.isNull("entfernt_um"));
              }
              Tabellenspeicher.LineToMemo(GidGesamt,Memo,VecBezeichnung,PnlC);
              Tab.showColumnInTextfield(GidGesamt);
            }
          });
          //TabToOutliner(GidGesamt,Tab,null,null,1,iPool);
          Vector VecTitel = getTitel();
          GidGesamt.setColumnButtons(new jclass.util.JCVector(VecBezeichnung));
          GidGesamt.setNumColumns(VecBezeichnung.size());
          for (Tab.moveFirst();!Tab.out();Tab.moveNext())
          {
            Vector<Object> VecSpalten = new Vector<Object>();
            int i=0;
            for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
              if (Sichtbar(TabSpalten.getPos()))
              {
                Vector VecMehr = (Vector)TabSpalten.getInhalt("mehr");
                int iAnz = VecMehr == null ? 1 : VecMehr.size();
                for (int iPer = 0; iPer < iAnz; iPer++)
                {
                  String sAic = VecTitel == null ? VecMehr != null ? "e" + TabSpalten.getS("Kennung2") + VecMehr.elementAt(iPer) : TabSpalten.getS("Kennung") :VecTitel.elementAt(i).toString();
                  Object Obj = TabSpalten.getS("Datentyp").equals("Farbe") ? new JaNein(Tab.getInhalt(TabSpalten.getS("Kennung")) != null) : TabToScreen(g, sAic, Tab, TabSpalten, VecMehr != null /*|| bAbfSumme*/);
                  VecSpalten.addElement(Obj);
                  i++;
                }

              }
            JCOutlinerNode Nod = new JCOutlinerNode(VecSpalten, (JCOutlinerFolderNode)GidGesamt.getRootNode());
            Nod.setStyle(new JCOutlinerNodeStyle());
            int iSysAic = /*bAbfSumme ? 0:*/iBew==0 ? Tab.getI("aic_stamm") : Tab.getI("aic_Bew_Pool");
            Nod.setUserData(new Integer(iSysAic));
          }
          final javax.swing.JDialog DlgTab=new javax.swing.JDialog(Fom==null ? g.getFomLeer():(javax.swing.JFrame)Fom.thisFrame,"History",false);
          //JPanel PnlC=new JPanel(new java.awt.BorderLayout());
          //PnlC.add("Center",GidGesamt);
          //PnlC.add("South",Memo);
          PnlC.setOneTouchExpandable(true);
          PnlC.setResizeWeight(0.7);
          DlgTab.getContentPane().add("Center",PnlC);
          JPanel PnlN=new JPanel(new GridLayout());
          //PnlN.setMinimumSize(new Dimension(220,120));
          PnlN.setPreferredSize(new Dimension(250,75));
          PnlN.add(GidSelect);
          DlgTab.getContentPane().add("North",PnlN);
          JPanel PnlBtn=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
          //if (sAnlage != null)
            BtnUndel.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                int iAIC = ((Integer)GidGesamt.getSelectedNode().getUserData()).intValue();
                g.progInfo("Undelete:" + iAIC + ", aktiv:" + iPool);
                bRefresh=true;
                if (iPool != iAIC)
                  g.exec("update bew_pool set pro_aic_protokoll="+g.Protokoll("Undelete",0)+" where aic_bew_pool="+iPool);
                g.exec("update bew_pool set pro_aic_protokoll=null where aic_bew_pool="+iAIC);
                if (iModell2>0)
                {
                  Vector<Integer> VecSave=new Vector<Integer>();
                  VecSave.addElement(new Integer(iPool));
                  VecSave.addElement(new Integer(iAIC));
                  Calc c = new Calc(Fom==null ? g.getFomLeer():Fom.thisFrame, g, iModell2, false, VecSave, 0, null, 0);
                }
                if (bImport)
                  AClient.send_AServer(AClient.getImport(g),g);
                //Import.Undelete(g,iAIC, iPool,g.Protokoll(sAnlage,0/*unknown*/));
                DlgTab.dispose();
              }
            });
          JButton BtnHelp=g.getButton("Help_History");
//          JButton BtnBeenden=g.getButton("Beenden");
//          Action cancelKeyAction = new AbstractAction() {
//            /**
//			 *
//			 */
//			private static final long serialVersionUID = 919904372405692017L;
//
//			public void actionPerformed(ActionEvent e)
//            {
//              DlgTab.dispose();
//            }
//          };
//          Static.Escape(BtnBeenden,DlgTab,cancelKeyAction);
//          BtnBeenden.addActionListener(new ActionListener()
//          {
//            public void actionPerformed(ActionEvent e)
//            {
//              DlgTab.dispose();
//            }
//          });
          //if (sAnlage != null)
//            PnlBtn.add(BtnUndel);
          // BtnHelp.setPreferredSize(D);
//          BtnBeenden.setPreferredSize(D);
//          PnlBtn.add(BtnHelp);
//          PnlBtn.add(BtnBeenden);
//          DlgTab.getContentPane().add("South",PnlBtn);
          Tab.setTabBtn(1, BtnUndel);
          Tab.setTabBtn(2, BtnHelp);
          Tab.addSouth(DlgTab.getContentPane(), DlgTab, GidGesamt);
          Tab.refillCbo(VecBezeichnung);
          DlgTab.setSize(900,300);
          Static.centerComponent(DlgTab,Fom.thisFrame);
          //DlgTab.pack();
		//g.printSI("Show");
          //DlgTab.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		//g.clockInfo(TabAbfragen.getS("Bezeichnung"),lClock);

          iBits=l;
          TabSp.popClearInhalt();
          bHistory=false;
          SQL_String();
//          g.fixtestError("4: nach SQL_String");
          g.clockInfo("showHistory2 (Fom="+Fom+")",lClock);
          DlgTab.setVisible(true);
//          g.fixtestError("5: nach setVisible");
        }

        private static String SQL_History(Global g,int iEig,Vector<Integer> VecStamm,String s,int iBewPool,int iRolle,boolean bJavaFX)
        {
          //g.progInfo("SQL_History:"+iEig+"/"+iStamm+"/"+s+"/"+iBewPool);
          String sBenutzerZeit = ",Benutzer.kennung angelegt_von,Timestamp angelegt_um,(select kennung from code where code.aic_code=protokoll.aic_code) Art,(select defbezeichnung from begriff where place=aic_begriff) Ort";
          boolean bStammpool=!(s.startsWith("Bew") || s.endsWith("Bezeichnung") || s.endsWith("Kennung") || s.equals("Eintritt") || s.equals("Austritt") || s.equals("Rolle") || s.equals("EinAustritt") || s.equals("Mandant"));
          String sProtDel=(bStammpool ? "pro2":"pro")+"_aic_protokoll";
          String sStatus2 =","+g.ifnull("pro_aic_protokoll","null","(select kennung from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer"+g.join2("protokoll","logging")+" where aic_protokoll=pro_aic_protokoll)")+" beendet_von"+
              ","+g.ifnull("pro_aic_protokoll","null","(select Timestamp from protokoll where aic_protokoll=pro_aic_protokoll)")+" beendet_um";
          String sStatus = ","+g.ifnull(sProtDel,"null","(select kennung from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer"+g.join2("protokoll","logging")+" where aic_protokoll="+sProtDel+")")+" entfernt_von"+
              ","+g.ifnull(sProtDel,"null","(select Timestamp from protokoll where aic_protokoll="+sProtDel+")")+" entfernt_um"+
        		  (bJavaFX ? ",(case when "+sProtDel+" is null then null else 'tab_removed' end) Style":",(case when "+sProtDel+" is null then 0 else "+g.ColLoeschen.getRGB()+" end) Farbe");
          boolean bName=false;
          String sName = "";
          String sSonst = "";
          String sWhere = "";
          String sOrder = "";
          String sEnd = null;
          String sJoinEnd=null;

                        if (iBewPool<=0)
                        {
                        	bName=VecStamm.size()>1;
                        	sName=bName ? "(select Bezeichnung from stammview2 where aic_stamm=p.aic_stamm and aic_rolle is null) Name,":"";
                                sSonst = sBenutzerZeit+",gultig_von von,gueltig_bis bis"+(bStammpool?sStatus2:"")+(iBewPool==0 ? sStatus:"")+(bName || g.Def()?",aic_stammpool":"");
                                sWhere = " where p.aic_eigenschaft="+iEig+" and p.aic_stamm"+Static.SQL_in(VecStamm)+(iBewPool<0 ? " and pro2_aic_protokoll is null":"");
                                sOrder = bName?"":" order by p.aic_protokoll desc";
                                sEnd = " join protokoll on protokoll.aic_protokoll=p.aic_protokoll"+g.join("logging","protokoll")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+sWhere;
                                sJoinEnd=" join stammpool p on p.aic_daten=d.aic_daten"+sEnd;
                                sEnd = " stammpool p"+sEnd;
                        }
                        else
                        {
                                //TabEigenschaft.push();
                                //Date dtStichtag = TabEigenschaft.posInhalt("Datentyp","BewDatum") ? ((Datum)TabEigenschaft.getInhalt("Komponente")).getDateTime() : null;
                                //TabEigenschaft.pop();
                                Vector<Integer> Vec=new Vector<Integer>();
                                while (iBewPool>0)
                                {
                                  Vec.addElement(new Integer(iBewPool));
                                  iBewPool=SQL.getInteger(g,"select bew_aic_bew_pool from bew_pool where aic_bew_pool="+iBewPool);
                                }
                                sSonst = ",p.aic_bew_pool"+sBenutzerZeit+sStatus;
                                sWhere = " Bew_Pool p join protokoll on protokoll.aic_protokoll=p.aic_protokoll"+g.join("logging","protokoll")+
                                        " join benutzer on benutzer.aic_benutzer=logging.aic_benutzer where p.aic_bew_pool"+Static.SQL_in(Vec);
                                        //"bew_pool.aic_bewegungstyp="+iBew+" and p.aic_stamm="+iStamm+(dtStichtag==null ? "":" and gueltig='"+Static.DateTimeToString(dtStichtag)+"'");
                                sOrder = " order by p.aic_bew_pool desc";
                                sEnd= ","+sWhere+" and b.aic_bew_pool=p.aic_bew_pool and b.aic_eigenschaft="+iEig;
                                sJoinEnd=" join stammpool b on b.aic_daten=d.aic_daten"+sEnd;
                                if (!s.equals("BewMass") && !s.equals("BewWaehrung"))
                                  sEnd = (s.startsWith("Bew")?" b":" stammpool b")+sEnd;
                        }

                        if (s.equals("WWW") || s.equals("Memo") || s.equals("Pfad") || s.equals("Filename") || s.equals("StringKurzOhne") || s.equals("E-Mail") || s.equals("FixDoku"))
                                s = "Stringx";//"StringLang";
//                        else if (s.equals("StringKurzOhne"))
//                                s = "Stringx";//"StringKurz";
//                        else if (s.equals("E-Mail") || s.equals("FixDoku"))
//                                s = "Stringx";//"String60";
                        else if (s.equals("Auto_von_bis"))
                                s = "von_bis";
                        String sSQL= /*s.equals("Kennung") ?
                                "select aic_stamm nr,kennung from stamm where aic_stamm="+iStamm :*/
                        s.endsWith("Bezeichnung") || s.equals("Kennung") || s.equals("Eintritt") || s.equals("Austritt") || s.equals("EinAustritt") || s.equals("Rolle") || s.equals("Mandant") ? "select "+
                                (iRolle>=0 ? (s.endsWith("Bezeichnung") ? "Bezeichnung":s.equals("Kennung") ? "kennung2 Stamm_Kennung":s.equals("Eintritt") || s.equals("EinAustritt") ? "Eintritt":"Austritt"):
                                "p.Bezeichnung,p.kennung2 Kennung,p.ab,p.eintritt,p.austritt,(select kennung from rolle where aic_rolle=p.aic_rolle) Rolle,(select kennung from mandant where aic_mandant=p.aic_mandant) Mandant")+
                                ",(select bezeichnung from stammview where aic_rolle is null and aic_stamm=p.firma) Firma"+
                                ",(select kennung from code where aic_code=p.aic_code) Anlage"+sBenutzerZeit+sStatus+" from stamm_protokoll p,protokoll"+g.join("logging","protokoll")+
                                " join benutzer on benutzer.aic_benutzer=logging.aic_benutzer where p.aic_protokoll=protokoll.aic_protokoll and p.aic_stamm"+Static.SQL_in(VecStamm)+
                                (iRolle>=0?Abfrage.Rolle(iRolle):"")+sOrder :
                        /*s.equals("Stringx") && !Static.bStringX ?
                                "select (case"+
                                " when spalte_double<11 then (select spalte_stringsehrkurz from daten_StringSehrKurz where aic_daten=p.aic_daten)"+
                                " when spalte_double<31 then (select spalte_stringkurz from daten_StringKurz where aic_daten=p.aic_daten)"+
                                " when spalte_double<61 then (select spalte_string60 from daten_String60 where aic_daten=p.aic_daten)"+
                                " when spalte_double<256 then (select spalte_stringLang from daten_StringLang where aic_daten=p.aic_daten)"+
                                " else (select spalte_text from daten_text where aic_daten=p.aic_daten) end) daten"+sSonst+" from"+sEnd+sOrder :*/
                        //	"select stringx daten"+sSonst+" from stringXview join"+sBewEnd+sOrder :
                        s.startsWith("String") || s.equals("Text") ?
                                "select "+sName+"spalte_"+s+" Daten"+sSonst+" from daten_"+s+" d"+sJoinEnd+sOrder :
                        s.equals("Passwort") ?
                        		"select "+sName+"'***' Daten"+sSonst+" from daten_stringx d"+sJoinEnd+sOrder :
                        s.endsWith("Bild") || s.equals("Bild2") || s.equals("Doku") ?
                                "select "+sName+"filename Daten"+sSonst+" from daten_"+(s.equals("Bild")?"image":s)+" d"+sJoinEnd+sOrder :
                        s.equals("Integer") || s.equals("Double") ?
                                "select "+sName+"spalte_double Daten"+sSonst+" from"+sEnd+sOrder :
                        s.equals("Boolean") ? "select "+sName+"case spalte_double when 1 then '"+Static.sJa+"' when 0 then '"+Static.sNein+"' else '"+Static.sKein+"' end Daten"+sSonst+" from"+sEnd+sOrder :
                        s.equals("Bool3") ? "select "+sName+g.AU_Bezeichnung("auswahl","p.spalte_double","kennung")+" Auswahl"+sSonst+" from"+sEnd+sOrder:
                        s.equals("von_bis") ? "select "+sName+"Zeit_von,Zeit_bis"+sSonst+" from Zeit_von_bis d"+sJoinEnd+sOrder :
                        s.equals("Zeit") ? "select "+sName+"Zeit_von Zeit"+sSonst+" from Zeit_von_bis d"+sJoinEnd+sOrder :
                        s.equals("Datum") ? "select "+sName+"gultig_von Daten"+sSonst+" from"+sEnd+sOrder :
                        //s.equals("Stichtag") ? "select gultig_von von,gueltig_bis bis"+sBenutzerZeit+",count(*) Anzahl from stammpool p,protokoll join logging join benutzer where p.aic_protokoll=protokoll.aic_protokoll and pro2_aic_protokoll is null and p.aic_stamm="+iStamm+" group by gultig_von,gueltig_bis, Benutzer,Timestamp":
                        s.equals("Gruppe") || s.equals("Hierarchie") || s.equals("Mehrfach") || s.equals("Outliner") || s.equals("OutlinerM") || s.equals("Buttons") || s.equals("Radio") ?
                                "select "+sName+"Bezeichnung Daten"+sSonst+" from stammview2 s,stammpool p join protokoll on protokoll.aic_protokoll=p.aic_protokoll"+g.join("logging","protokoll")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+sWhere+" and p.sta_aic_stamm=s.aic_stamm and s.aic_rolle is null"+sOrder :
                        s.equals("BenMass") || s.equals("Mass") || s.equals("Waehrung") || s.equals("Einheiten") ?
                                "select "+sName+"spalte_double Wert,(select Bezeichnung from stammview2 where aic_stamm=sta_aic_stamm) Einheit"+sSonst+" from "+sEnd+sOrder : "" ;
                        if (s.equals("BewStamm") || s.equals("BewButtons") || s.equals("BewRadio"))
                                sSQL = "select "+sName+"Bezeichnung"+sSonst+" from stammview2"+g.join("bew_stamm","stammview2","stamm")+sEnd+" and stammview2.aic_rolle is null"+sOrder;
                        else if (s.equals("BewModul") || s.equals("BewModell") || s.equals("BewDruck") || s.equals("BewFormular"))
                                sSQL = "select "+sName+"begriff.kennung"+sSonst+" from begriff"+g.join2("bew_begriff","begriff")+sEnd+sOrder;
                        else if (s.equals("BewBoolean"))
                                sSQL = "select "+sName+"case spalte_boolean when 1 then '"+Static.sJa+"' when 0 then '"+Static.sNein+"' else '"+Static.sKein+"' end Daten"+sSonst+" from bew_boolean"+sEnd+sOrder;
                        else if (s.equals("BewBool3"))
                          sSQL = "select "+sName+"kennung"+sSonst+" from auswahl a join bew_aic b on a.aic_auswahl=b.aic_auswahl"+sEnd+sOrder;
                        else if (s.equals("BewDauer"))
                                sSQL = "select "+sName+"b.dauer"+sSonst+" from bew_von_bis"+sEnd+sOrder;
                        else if (s.equals("BewZahl") || s.equals("BewCount"))
                                sSQL = "select "+sName+"b.spalte_wert"+sSonst+" from bew_wert"+sEnd+sOrder;
                        else if (s.equals("BewDatum2"))
                                sSQL = "select "+sName+"b.von"+sSonst+" from bew_von_bis"+sEnd+sOrder;
                        else if (s.equals("BewDatum"))
                                sSQL = "select "+sName+"gueltig"+sSonst+" from"+sWhere+sOrder;
                        else if (s.equals("BewVon_Bis"))
                                sSQL = "select "+sName+"b.von,b.bis,b.dauer"+sSonst+" from Bew_von_bis"+sEnd+sOrder;
                        else if (s.equals("BewMass") || s.equals("BewWaehrung"))
                                sSQL = "select "+sName+"Spalte_Wert,Bezeichnung"+sSonst+" from stammview join bew_wert b on b.aic_stamm=stammview.aic_stamm"+sEnd+sOrder;
                        //g.progInfo("History-"+s+":\n"+sSQL);
          return bName ? "select * from ("+sSQL+") x order by Name,Aic_Stammpool desc":sSQL;
        }
        
        

        // Einzel-Stamm-History von einer Eigenschaft
    public static void showHistory(Global g,Object Fom,String sBezeichnung,int iEig,int iStamm,String s,Date dtStichtag,int iBewPool,int iRolle)
    {
    	g.fixtestError("showHistory von "+sBezeichnung+" ohne Vec aufgerufen");
    	showHistory(g,Fom,sBezeichnung,iEig,Static.AicToVec(iStamm),s,dtStichtag,iBewPool,iRolle);
    }
        
	public static void showHistory(Global g,Object Fom,String sBezeichnung,int iEig,Vector<Integer> VecStamm,String s,Date dtStichtag,int iBewPool,int iRolle)
	{
//		g.fixtestError("showHistory");
		if (VecStamm==null || VecStamm.size()==0)
			return;
		if (VecStamm.size()==1)
			sBezeichnung+=" "+g.getShow("von")+" "+g.getStamm(Sort.geti(VecStamm, 0));
          g.fixtestInfo("showHistory mit Eig="+iEig+" von Stamm="+VecStamm+" (Dt="+s+")");
		//if (TabEigenschaft.posInhalt("History",e.getSource()))
		//{
			//int i = TabEigenschaft.getI("Aic");
			//int i=iEigenschaft;
			//String s = TabEigenschaft.getS("Datentyp");
			//String s = sDatentyp;
          boolean bJavaFX=false;//Fom instanceof Stage || Fom instanceof FormularFX;
          if (s.equals("Stichtag"))
                        {
        	  			  if (!bJavaFX)
        	  				  showHistory3(g,(Formular)Fom,Sort.geti(VecStamm, 0),dtStichtag);
                          return;
                        }
            
			String sSQL=SQL_History(g,iEig,VecStamm,s,iBewPool,iRolle,bJavaFX);
      // g.fixtestError(sSQL);
			if (!sSQL.equals(""))
			{
			  Tabellenspeicher Tab=new Tabellenspeicher(g,sSQL,true);
                          if (Tab.exists("beendet_von"))
                          {
                            Tab.delIf("beendet_von", null);
                            Tab.delIf("beendet_um", null);
                          }
                          Tab.delIf("entfernt_von",null);
                          Tab.delIf("entfernt_um",null);
			  /*if (s.equals("Text"))
			  {
			    JDialog Dlg=new JDialog((JFrame)Fom.thisFrame,sBezeichnung,false);
			    final AUTextArea Memo =new AUTextArea(g,0);
			    Memo.Edt.setPreferredSize(new Dimension(1,40));
			    final AUOutliner Gid = new AUOutliner();
			    Tab.showGrid(Gid);
			    Gid.addItemListener(new JCOutlinerListener()
			    {
			      public void itemStateChanged(JCItemEvent e) {}
			      public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {}
			      public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {}
			      public void outlinerNodeSelectBegin(JCOutlinerEvent e) {}

			      public void outlinerNodeSelectEnd(JCOutlinerEvent e)
			      {
				Memo.setText("");
				if (Gid.getSelectedNode().getLevel()>0)
				{
				  Vector Vec=(Vector)Gid.getSelectedNode().getLabel();
                                  if (Vec != null && Vec.size()>0 && Vec.elementAt(0)!=null)
                                    Memo.setText(Tabellenspeicher.getm(Vec.elementAt(0)));
				}
			      }
			    });
			    //if (Gid.getSelectedNode()!=null)
			    {
			      Object Obj=Gid.getSelectedNode().getLabel();
			      Memo.setText(Obj==null ? "":Obj instanceof String ? (String)Obj: Sort.gets(Obj,0));
			    }
			    //JPanel PnlC=new JPanel(new java.awt.BorderLayout());
                            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,Gid,Memo);
                            splitPane.setOneTouchExpandable(true);
                            splitPane.setResizeWeight(0.5);
			    //PnlC.add("Center",Gid);
			    //PnlC.add("South",Memo);
			    Dlg.getContentPane().add("Center",splitPane);
			    Dlg.getContentPane().add("South",Static.BtnBeenden);
			    Static.Escape(Static.BtnBeenden,Dlg,null);
			    Dlg.pack();
			    Dlg.setVisible(true);
			  }
			  else*/
//              if (bJavaFX)
//            	  Tab.showGrid(Fom instanceof Stage ? (Stage)Fom:((FormularFX)Fom).AU_Stage, sBezeichnung);
//              else
			    Tab.showGrid(sBezeichnung,((Formular)Fom).thisFrame);
			}
		//}
	}

        private static long clockCheck(Global g,String s,long lClock)
        {
          long lClock2=Static.get_ms();
          if (lClock2>lClock)
            g.clockInfo(s,lClock);
          return lClock2;
        }

        public static void refreshBerechtigung(Global g)
        {
          long lClock=Static.get_ms();
          long lClock0=lClock;
          java.sql.Timestamp tsVon=g.getVon();
          //TODO von aus null setzten
          g.setVon(null); 
          checkErsatz(g);
          lClock=clockCheck(g,"checkErsatz",lClock);
          checkAustritt(g);
          lClock=clockCheck(g,"checkAustritt",lClock);
          checkStammBerechtigung(g);
          lClock=clockCheck(g,"checkStammBerechtigung",lClock);
          checkAbschluss(g);
          lClock=clockCheck(g,"checkAbschluss",lClock);
          //checkFeiertag(g);
          //clockCheck(g,"checkFeiertag",lClock);
          g.setVon(tsVon);
          //g.TabStamm.clearAll();
          g.LoadANR_BSt();
          g.clockInfo("refreshBerechtigung",lClock0);
        }

        public static void refreshBerechtigungSmall(Global g)
        {
          checkStammBerechtigung(g);
          checkAbschluss(g);
          g.LoadANR_BSt();
          //checkFeiertag(g);
        }

        private static void checkErsatz(Global g)
        {
          if (Static.sAbfrageErsatz != null)
          {
            g.testInfo("Login-Stamm="+g.getStamm());
            Tabellenspeicher Tab=new Tabellenspeicher(g,"select benutzer.aic_stamm Stamm,benutzer_zuordnung.* from benutzer"+g.join2("benutzer_zuordnung","benutzer")+" where geloescht is null and benutzer_zuordnung.aic_stamm="+g.getStamm(),true);
            if (!Tab.isEmpty())
            {
              ShowAbfrage Abf = new ShowAbfrage(g, Static.sAbfrageErsatz);
              Tabellenspeicher TabDaten=Abf.getDaten(0, 0, Tab.getVecSpalte("STAMM"),null);
              g.VecErsatz=TabDaten.getVecSpalte("aic_stamm");
              if (g.TestPC() && g.Info())
              {
                Tab.showGrid("Ersatz-Benutzer");
                TabDaten.showGrid("Ersatz-Daten");
              }
            }
          }
        }

        @SuppressWarnings("unchecked")
	private static void checkStammBerechtigung(Global g)
        {
          long lClock=Static.get_ms();
          if (TabBFilter==null)
            TabBFilter=new Tabellenspeicher(g,new String[] {"Abfrage","Daten","Stt"});
          else
            TabBFilter.clearAll();
          //g.TabBerechtigung.showGrid("TabBerechtigung");
          if (g.TabBerechtigung != null)
            for(int iPos=0;iPos<g.TabBerechtigung.size();iPos++)
            {
              Vector Vec=(Vector)g.TabBerechtigung.getInhalt("VecAbfrage",iPos);
              Vector VecStt=(Vector)g.TabBerechtigung.getInhalt("VecStt",iPos);
              Vector VecStamm=(Vector)g.TabBerechtigung.getInhalt("VecStamm",iPos);
              //g.testInfo(g.TabBerechtigung.getI("Aic")+":"+Vec+"/"+VecStt);
              //if(VecStt==null) entfernt am 8.10.2014 (3042)
              {
                VecStt=new Vector();
                VecStamm=new Vector();
                g.TabBerechtigung.setInhalt(iPos,"VecStt",VecStt);
                g.TabBerechtigung.setInhalt(iPos,"VecStamm",VecStamm);
              }
              if (Vec != null)
                for(int i=0;i<Vec.size();i++)
                {
                  int iAbf = ((Integer)Vec.elementAt(i)).intValue();
                  Vector Vec2;
                  if (TabBFilter.posInhalt("Abfrage",iAbf))
                  {
                    Vec2 = (Vector)TabBFilter.getInhalt("Daten");
                    Integer IntStt=TabBFilter.getInt("Stt");
                    if (!VecStt.contains(IntStt))
                      VecStt.addElement(IntStt);
                  }
                  else
                  {
                    ShowAbfrage Abf = new ShowAbfrage(g, iAbf, Abfrage.cstHS_Filter);
                    Abf.iBits=Abf.iBits|Abfrage.cstKeinStamm;
                    //g.testInfo("Stt="+Abf.iStt+"/"+VecStt);
                    Integer IntStt=new Integer(Abf.iStt);
                    if (!VecStt.contains(IntStt))
                      VecStt.addElement(IntStt);
                    Vec2=new Vector(Abf.getDaten(Abf.iStt,0,null,null).getVecSpalte("AIC_STAMM"));
                    TabBFilter.addInhalt("Abfrage",iAbf);
                    TabBFilter.addInhalt("Daten",Vec2);
                    TabBFilter.addInhalt("Stt",IntStt);
                  }
                  Static.addVector(VecStamm,Vec2);
                }
              //g.testInfo(g.TabBerechtigung.getI("Aic")+"|"+Vec+"/"+VecStt);
            }
          //g.TabBerechtigung.showGrid("TabBerechtigung");
          g.clockInfo("checkStammBerechtigung",lClock);
        }

        @SuppressWarnings("unchecked")
        public static void checkAbschluss(Global g)
        {
          if (g.TabMA !=null && !g.TabMA.isEmpty())
          {
            g.TabMA.refresh(g);
            g.checkAbschluss();
            for (int iPos=0;iPos<g.TabMA.size(); iPos++)
              if (g.TabMA.getI(iPos,"AIC_Abfrage") > 0)
              {
                int iAbf = g.TabMA.getI(iPos,"AIC_Abfrage");
                Vector Vec2;
                if (TabBFilter.posInhalt("Abfrage", iAbf))
                  Vec2 = (Vector)TabBFilter.getInhalt("Daten");
                else
                {
                  ShowAbfrage Abf = new ShowAbfrage(g, iAbf, Abfrage.cstHS_Filter);
                  Abf.iBits=Abf.iBits|Abfrage.cstKeinStamm;
                  Vec2 = new Vector(Abf.getDaten(Abf.iStt, 0, null,null).getVecSpalte("AIC_STAMM"));
                  TabBFilter.addInhalt("Abfrage", iAbf);
                  TabBFilter.addInhalt("Daten", Vec2);
                  TabBFilter.addInhalt("Stt",Abf.iStt);
                }
                g.TabMA.setInhalt(iPos,"Daten",Vec2);
              }
            //g.TabMA.showGrid("checkAbschluss");
          }
        }

        /*@SuppressWarnings("unchecked")
        private static void checkFeiertag(Global g)
        {

            for (int iPosL=0; iPosL<g.TabLand.size(); iPosL++)
              if (g.TabLand.getI(iPosL,"AIC_Abfrage") > 0)
              {
                int iAbf = g.TabLand.getI(iPosL,"AIC_Abfrage");
                Vector Vec2;
                if (TabBFilter.posInhalt("Abfrage", iAbf))
                  Vec2 = (Vector)TabBFilter.getInhalt("Daten");
                else
                {
                  ShowAbfrage Abf = new ShowAbfrage(g, iAbf, Abfrage.cstHS_Filter);
                  Abf.iBits=Abf.iBits|Abfrage.cstKeinStamm;
                  //System.gc();
                  Tabellenspeicher Tab=Abf.getDaten(Abf.iStt, 0, null);
                  //System.gc();
                  Vector Vec=Tab.getVecSpalte("AIC_STAMM");
                  //System.gc();
                  Vec2= new Vector(Vec);
                  //System.gc();
                  //Vec2 = new JCVector(Abf.getDaten(Abf.iStt, 0, null).getVecSpalte("AIC_STAMM"));
                  TabBFilter.addInhalt("Abfrage", iAbf);
                  TabBFilter.addInhalt("Daten", Vec2);
                  TabBFilter.addInhalt("Stt",Abf.iStt);
                }
                g.TabLand.setInhalt(iPosL,"ANR",Vec2);
              }
            //g.TabLand.showGrid("Land");
        }*/

        public static void checkAustritt(Global g)
        {
          g.testInfo("checkAustritt");
          g.TabAustritt=new Tabellenspeicher(g,"select aic_stamm,(select bezeichnung from stammview2 where aic_stamm=stammpool.aic_stamm and aic_rolle is null) Name"+
                                             ",gultig_von aus,gueltig_bis ein from stammpool where aic_eigenschaft="+g.iEigAustritt+
                                             " and pro2_aic_protokoll is null and gultig_von is not null order by aic_stamm",true);
          g.TabAustritt.setTitel("TabAustritt");
        }

}

