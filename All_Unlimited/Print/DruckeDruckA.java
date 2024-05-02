package All_Unlimited.Print;

import java.awt.Font;
import java.awt.Image;
import java.util.Vector;

import jclass.bwt.BWTEnum;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Clock;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Anzeige.PW1;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
//import All_Unlimited.Allgemein.Diagramm;
//import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Zeitraum;
//import All_Unlimited.Web.webJSON;
import All_Unlimited.Hauptmaske.Calc;
import All_Unlimited.Hauptmaske.Import;
import All_Unlimited.Allgemein.Message;

import java.util.Date;
import java.util.Stack;
import All_Unlimited.Allgemein.SMTP2;
import java.sql.Timestamp;

public class DruckeDruckA implements Runnable
{

	private int iDruck;
	private Global g;
	private int iStammtyp;
	private Tabellenspeicher TabStamm;
	//boolean bKeinKopfFuss;
        private int iDFBits;
        private int iRaster;
        private int iLayout;
        //private boolean bQuerformat;
        private JCOutlinerFolderNode NodGroup;
        private Tabellenspeicher TabAbfragen=null;
        private String sPDF_File;
        private String sPDF_PW;
        private Vector VecE;
        private int iSort;
        private Clock clock=null;
        private Tabellenspeicher TabEMailA=null;
        private boolean bAdressenImmer=true;
        private Tabellenspeicher TabEMailD=null;
        private Tabellenspeicher TabEMailL=null;
        private Tabellenspeicher TabEMailS=null;
        private Tabellenspeicher TabPDF=null;
        private Tabellenspeicher TabPdfL=null;
        private int iBewPDF=0;
        private int iLastBis=0;
        private boolean bFehler=false;
        private int iAnz=0;
        private Tabellenspeicher TabFehler=null;
        private boolean bMeldung=false;
        private String sEMailName=null;
        private String sEMail=null;
        private String sPW=null;
        public boolean bThread=true;

	public DruckeDruckA(Global rg,int riDruck,Tabellenspeicher rTabStamm,String rsPDF_File,String rsPDF_PW,JCOutlinerFolderNode rNodGroup,
						int riDFBits,int riRaster,int riLayout,Vector rVecE,int riSort,int iVB)
	{
		if (rg.bTestLog)
			g=rg;
		else
			g=new Global(rg,true);
		//g.testInfo(" ------------ DruckeDruckA: "+riDruck+"/"+riStammtyp+"/"+rsKennung);
		//rTabStamm.showGrid("TabStamm");
		//iStammtyp=riStammtyp;
		TabStamm=rTabStamm;
		iDruck=riDruck;
                iDFBits=riDFBits;
                iRaster=riRaster;
                iLayout=riLayout;
                sPDF_File=rsPDF_File;
                sPDF_PW=rsPDF_PW;
                //bQuerformat=rbQuerformat;
                NodGroup=rNodGroup;
                VecE=rVecE;
                iSort=riSort;
                TabAbfragen=new Tabellenspeicher(rg,new String[]{"Aic","Abfrage"});
                if (iVB>0)
                {
                	g.setZA(0,g.getZA(iVB));
            		g.setVonBis(g.getVon(iVB),g.getBis(iVB));
                }
                else if (!Static.bX11 && rg.isConnected())
                  rg.unConnect();
		//this.bKeinKopfFuss=(iDFBits&DruckHS.cstKeinKopfFuss)>0;
	}

        private void addFehler(int iStamm,String sFehler,boolean bOk)
        {
          if (TabFehler==null)
            TabFehler=new Tabellenspeicher(g,new String[]{"Stammsatz","Fehler","Aic","ok","not"});
          if (TabFehler.posInhalt("Aic",iStamm))
          {
            TabFehler.addI(bOk?"Ok":"not",1);
            if (!bOk)
              TabFehler.setInhalt("Fehler",sFehler);
          }
          else
          {
            int iStammMom=iStammtyp>=0 ? iStamm:SQL.getInteger(g,"select anr from bew_pool where aic_bew_pool=?",0,""+iStamm);
            TabFehler.addInhalt("Stammsatz",iStammMom==0 ? g.getShow("alle_markierte"):g.getStamm(iStammMom));
            TabFehler.addInhalt("Fehler",sFehler);
            TabFehler.addInhalt("Aic",iStamm);
            TabFehler.addInhalt("ok",bOk?1:0);
            TabFehler.addInhalt("not",bOk?0:1);
          }
        }

        private void fillVecP(JCOutlinerFolderNode Nod,Vector<JCOutlinerFolderNode> VecP)
        {
          Vector Vec = Nod.getChildren();
          if (Vec != null)
          {
            for(int i=0;i<Vec.size();i++)
            {
              JCOutlinerNode NodNeu = (JCOutlinerNode)Vec.elementAt(i);
              if (NodNeu.getUserData() != null && Tabellenspeicher.geti(((Vector)NodNeu.getUserData()).elementAt(1))==iStammtyp)
              {
                JCOutlinerFolderNode NodP = NodNeu.getParent();
                //g.progInfo(i + ".:" + NodNeu + "/" + NodP);
                if (!VecP.contains(NodP))
                  VecP.addElement(NodP);
              }
              if (NodNeu instanceof JCOutlinerFolderNode)
                fillVecP((JCOutlinerFolderNode)NodNeu,VecP);
            }
          }

        }

        private Timestamp getNextTS(Tabellenspeicher TabSE)
        {
          Timestamp ts = null;
          for (TabSE.moveFirst(); !TabSE.out(); TabSE.moveNext())
            if (TabSE.getTimestamp("Datum").after(g.getVon()))
              return ts;
            else
              ts = TabSE.getTimestamp("Datum");
          return ts;
        }

        private void setSE_Abf(ShowAbfrage A,int iANR)
        {
          //g.fixInfo("Stundenerfassungsumwandlung für " + g.getStamm(iANR));
          Tabellenspeicher TabSE = new Tabellenspeicher(g, "select aic_spalten2,reihe,aic_spalte,Titel,bits,aic_stamm,aic_begriff,aic_farbe,Datum,LAENGE from spalten2 where aic_abfrage=" +
              A.iAbfrage + " and ANR=" + iANR + " order by Datum,reihe,aic_spalten2", true);
          TabSE.sGruppe="Datum";
          Timestamp dt_Old=getNextTS(TabSE);
          int iSpalte=0;
          Vector<Integer> Vec = null;
          boolean bPos=false;
          for(TabSE.posInhalt("Datum",dt_Old);!TabSE.out() && TabSE.getTimestamp("Datum").equals(dt_Old);TabSE.moveNext())
          {
            //boolean bNeueSpalte=;
            if (iSpalte != TabSE.getI("aic_spalte"))
            {
              //delRest(iSpalte);
              iSpalte = TabSE.getI("aic_spalte");
              //Vec = null;
              bPos = A.TabSp.posInhalt("aic_spalte", iSpalte);
              if (bPos && TabSE.getI("reihe") != A.TabSp.getI("Reihenfolge"))
              {
                g.fixtestInfo("Reihenfolge ändern von " + iSpalte + "/" + A.TabSp.getI("Reihenfolge") + " auf " + TabSE.getI("reihe"));
                int iNr = A.TabSp.getI("Nummer");
                A.TabSp.setInhalt("Reihenfolge", TabSE.getI("reihe"));
                A.TabSp.sort("Reihenfolge", true);
                A.TabSp.posInhalt("Nummer", iNr);
              }
            }
            else
            {
              A.TabSp.moveNext();
              boolean bCopy = true;
              if (A.TabSp.out())
                A.TabSp.moveLast();
              else if (A.TabSp.getI("aic_spalte") != iSpalte)
                A.TabSp.movePrevious();
              else
                bCopy = false;
              if (bCopy)
                A.TabSp.copyLine2();
            }
            if (bPos)
            {
              int iRel = TabSE.getI("aic_stamm");
              if (iRel > 0)
                g.fixtestInfo("Pos" + TabSE.getI("reihe") + ": Rel=" + iRel + " (" + TabSE.getS("Titel") + ")");
              if (iRel > 0 && Vec == null)Vec = new Vector<Integer>();
              if (iRel > 0 && Vec.contains(iRel))
              {
                A.TabSp.clearInhalt();
                TabSE.clearInhalt2();
                //g.fixInfo(TabSE.getS("Titel") + " doppelt und wird deshalb gelöscht");
              }
              else
              {
                if (iRel > 0)Vec.addElement(iRel);
                A.TabSp.setInhalt("Bezeichnung", TabSE.getS("Titel"));
                A.TabSp.setInhalt("rel", iRel);
                A.TabSp.setInhalt("aic_begriff", TabSE.getI("aic_begriff"));
                A.TabSp.setInhalt("aic_farbe", TabSE.getI("aic_farbe"));
                if (!TabSE.isNull("laenge"))
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
          //delRest(iSpalte);
          A.SQL_String();
        }

	@SuppressWarnings("unchecked")
	public void run()
	{
          if (g.Clock2()) clock=new Clock(g,null);
          long lClock2 = Clock.startClock(clock);
//          VecPDF=null;
          boolean bH_dez=g.bH_dez;
          boolean bH_min=g.bH_min;
          g.bH_dez=(iDFBits&Drucken.cstART_H)==Drucken.cstH_DEZ;
          g.bH_min=(iDFBits&Drucken.cstART_H)==Drucken.cstH_MIN;
          boolean bAIO=(iDFBits & Drucken.cstPb) > 0 && (iDFBits & Drucken.cstAIO) > 0;
          int iPos=g.TabBegriffe.getPos("AIC",iDruck);
		//Image Img=g.TabBegriffe.getS("BildFile").equals("") ? null : g.LoadImage(g.TabBegriffe.getS("BildFile"));
		int iBew=g.TabBegriffe.getI(iPos,"Erf");
                iStammtyp=iBew>0 ? -iBew:g.TabBegriffe.getI(iPos,"Stt");
		long iDruckBits=g.TabBegriffe.getL(iPos,"bits");
		Font foDruck=null;//g.fontTitel;
		int iPosS= g.TabBegriffe.getI(iPos,"Schrift")>0 ? g.TabSchrift.getPos("aic",g.TabBegriffe.getI(iPos,"Schrift")):-1;
		if (iPosS>=0)
		  foDruck=(Font)g.TabSchrift.getInhalt("schrift",iPosS);
                String sDrucktitel1=g.TabBegriffe.getS(iPos,"bezeichnung");
                boolean bKeinDrucktitel=(iDruckBits&Drucken.cstPntKeinDrucktitel)>0;
		boolean bTagesanzeige=(iDruckBits&Drucken.cstPntTagesanzeige)>0;
                boolean bZeitraumtitel=false;
                boolean bStammtitel=false;
                int iModell=SQL.getInteger(g,"select beg_aic_begriff from begriff_zuordnung z join begriff b on z.beg_aic_begriff=b.aic_begriff and z.aic_begriff="+iDruck+
                                                " and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell"));
                Tabellenspeicher TabAbschnitte=new Tabellenspeicher(g,"select abschnitt.aic_abschnitt,abschnitt.bits,abschnitt.aic_raster,abschnitt.kennung,abschnitt.kennzeichen,abschnitt.aic_begriff,reihenfolge,Zahl,Begriff.bits bits2,aic_bewegungstyp " + g.AU_Bezeichnung("Abschnitt")+g.AU_Bild2("Abschnitt",Global.iSpSw)+" Filename from Druck_Zuordnung"+g.join("Abschnitt","Druck_Zuordnung")+" left outer"+g.join("begriff","Abschnitt")+" left outer"+g.join2("abfrage","begriff")+" where Druck_Zuordnung.aic_begriff =" + iDruck + " order by Reihenfolge",true);
                if (TabAbschnitte.size()>1)
                {
                  if ((iDFBits&Drucken.cstGruppiert)>0) iDFBits-=Drucken.cstGruppiert;
                  //if ((iDFBits&Drucken.cstNurSumme)>0) iDFBits-=Drucken.cstNurSumme;
                  if ((iDFBits&Drucken.cstKeineSumme)>0) iDFBits-=Drucken.cstKeineSumme;
                  if ((iDFBits&Drucken.cstLetzteGruppeWeg)>0) iDFBits-=Drucken.cstLetzteGruppeWeg;
                }
                boolean bAlle=(iDruckBits&Drucken.cstPntMenge)>0;//(NodGroup==null || (iDFBits&Drucken.cstGruppiert)==0) && (iDruckBits&Drucken.cstPntUseSync)==0;

                Vector VecG=null;
                if ((iDFBits&Drucken.cstSeiteProGruppe)>0)
                {
                  VecG=new Vector();
                  fillVecP(NodGroup,VecG);
                  //g.progInfo(VecG.size()+":"+VecG);
                }

		//boolean bDrucktitelLinks=(iDruckBits&Drucken.cstPntDrucktitelLinks)>0;
                int iReiheOld=0;

//                int iGaugeCount=0;
		int iSync=-1;
                for(TabAbschnitte.moveFirst();!TabAbschnitte.eof();TabAbschnitte.moveNext())
                {
                  long lBits2=TabAbschnitte.getL("bits2");
                  //bAlle=bAlle && ((lBits2&Abfrage.cstMengen)>0);// || (lBits2&Abfrage.cstKeinStamm)>0 && (lBits2&Abfrage.cstFremdStamm)==0);
                  bZeitraumtitel=bZeitraumtitel || (lBits2&Abfrage.cstKeinZeitraum)==0;
                  if ((lBits2&Abfrage.cstFremdStamm)>0)
                  {
                    iPos=g.TabStammtypen.getPos("Aic",iStammtyp);
                    iSync= iPos>=0 ? g.TabStammtypen.getI(iPos,"Sync"):-1;
                  }
                }
                if((iDruckBits & Drucken.cstPntKeinStammtitel)==0)
                    bStammtitel=!bAlle;
                boolean bPeriode=(iDFBits&Drucken.cstPeriode)>0;
                java.sql.Timestamp dtVon=g.getVon();
                java.sql.Timestamp dtBis=g.getBis();
                if ((iDruckBits&Drucken.cstPntNewYear)>0)
                {
                  DateWOD dwVon=new DateWOD(dtVon);
                  dwVon.setMonth(1);
                  dwVon.setDay1();
                  g.setVon(dwVon.toTimestamp());
                  dwVon.nextYear();
                  g.setBis(dwVon.toTimestamp());
                  if (bThread)
                	  g.zrThread(Thread.currentThread());
                }
                String sZA=g.getZA(0);
                long iZA=iDruckBits&Drucken.cstPntZeitart;
                String sZA2=iZA==Drucken.cstPntZaTag?"Tag":iZA==Drucken.cstPntZaWoche?"Woche":iZA==Drucken.cstPntZaMonat?"Monat":
                                        iZA==Drucken.cstPntZaQuartal?"Quartal":iZA==Drucken.cstPntZaJahr?"Jahr":sZA;
                g.setZA(0,sZA2);
                Zeitraum.PeriodeToVec(g,sZA2,true);
                Vector VecPerC=bPeriode ? (Vector)g.getVecPer(0).clone():null;
                //g.progInfo("VecPerioden1="+Zeitraum.VecPerioden);
                //Vector VecPer=bPeriode ? new Vector(g.VecPerioden):null;
                //g.progInfo("VecPerioden2="+VecPer+"/"+bPeriode);
                //boolean bFirstStamm=true;
                //int iMax=bPeriode?VecPer.size()-1:1;
                if ((iDFBits&Drucken.cstPDF_Art)==Drucken.cstEMail)
                {
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,kennung,bits from abschnitt where "+g.bit("bits",Drucken.cstImmer),true);
                  //Tab.showGrid("TabImmer");
                  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                  {
                    ShowAbfrage Abf = new ShowAbfrage(g, Tab.getI("aic_begriff"), Abfrage.cstBegriff);
                    if ((Tab.getI("Bits") & Drucken.cstArt) == Drucken.cstEMail_D)
                      TabEMailD = Abf.getDaten(iStammtyp, 0, TabStamm.getVecSpalte("Aic"),null);
                    else if ((Tab.getI("Bits") & Drucken.cstArt) == Drucken.cstEMail_S)
                      TabEMailS = Abf.getDaten(iStammtyp, 0, TabStamm.getVecSpalte("Aic"),null);
                    else if ((Tab.getI("Bits") & Drucken.cstArt) == Drucken.cstPDF_Save)
                    {
                      TabPDF = Abf.getSpalten();//getDaten(iStammtyp, 0, TabStamm.getVecSpalte("Aic"),null);
                      iBewPDF=Abf.iBew;
                    }
                  }
                  if (Static.bDefShow && TabEMailD!=null)
                	  TabEMailD.showGrid("TabEMailD1");
                }
                int iPmax=VecG == null ? 1:VecG.size();
                //Gauge gauge=new Gauge(0,iMax*iPmax*(bAlle ? 1:TabStamm.getAnzahlElemente(null)),"",g,true);
                DruckHS dh = null;
                Vector <DruckHS> VecDH=null;
                if ((iDruckBits & Drucken.cstPntFarbe)>0)
					iDruckBits-=Drucken.cstPntFarbe;
                try
                {
                  //TabStamm.showGrid("Stamm");
                 boolean bED=(iDruckBits&Drucken.cstPntED)>0;                
                 if (bED) VecDH =new Vector <DruckHS>();
                  
                 for (TabStamm.moveFirst();!TabStamm.out();)
                 {
                  //g.progInfo("aic="+TabStamm.getI("aic"));
                  Clock.add(clock, "Vorbereitung", "Abschnitte", -1);
                  dh = new DruckHS(g, sDrucktitel1, iLayout, iDruckBits | iDFBits&(Drucken.cstDruckerauswahl + Drucken.cstDFFarbe),TabStamm.getI("aic"));
                  Clock.add(clock, "Vorbereitung", "Druck", -1);
                  dh.LoadRaster(-1);
                  //dh.TabRaster = new Tabellenspeicher(g,
                  //    "select aic_raster,kennung " + g.AU_Bezeichnung("raster") + ",aic_schrift,sch_aic_schrift,sch2_aic_schrift,sch3_aic_schrift,sch4_aic_schrift,sch5_aic_schrift,sch6_aic_schrift,bits from raster", true);
                  Clock.add(clock, "Vorbereitung", "Raster", -1);
                  Calc calc=null;
                  //for (int i = 0; i < iMax; i++)
                  {
                    //g.progInfo("ab "+Zeitraum.VecPerioden.elementAt(i));
                    //if (bPeriode)
                    //  g.setVonBis((java.sql.Timestamp)VecPer.elementAt(i),(java.sql.Timestamp)VecPer.elementAt(i + 1));
                    Tabellenspeicher TabPges=null;
                    for (int iP = 0; iP < iPmax; iP++)
                    {
                      //if (!bAlle)
                      long lClock = Static.get_ms();
                      boolean bFirstStamm = true;
                      if (VecG != null)
                      {
                        Vector VecC = ((JCOutlinerFolderNode)VecG.elementAt(iP)).getChildren();
                        TabStamm.clearAll();
                        for (int iC = 0; iC < VecC.size(); iC++)
                        {
                          JCOutlinerNode NodC = (JCOutlinerNode)VecC.elementAt(iC);
                          if (Tabellenspeicher.geti(((Vector)NodC.getUserData()).elementAt(1)) == iStammtyp)
                          {
                            TabStamm.addInhalt("Aic", ((Vector)NodC.getUserData()).elementAt(0));
                            TabStamm.addInhalt("Bezeichnung", ((Vector)NodC.getLabel()).elementAt(0));
                          }
                        }
                      }
                      boolean bZRT2=(iDruckBits&Drucken.cstPntKeinZRtitel)==0;
                      //boolean bStT2=(iDruckBits&Drucken.cstPntKeinStammtitel)==0;
                      //g.testInfo("   ----  "+sDrucktitel1+" -    Stamm-Titel:"+(bStammtitel)+" -> "+bStT2);
                      g.testInfo("   ----  "+sDrucktitel1+" - Zeitraum-Titel:"+(bZeitraumtitel && !bKeinDrucktitel)+" -> "+bZRT2);
                      //String sDrucktitel = bKeinDrucktitel ? null : sDrucktitel1 + (!bKeinDrucktitel && bZeitraumtitel ?
                      //g.fixInfo("Gruppe="+((JCOutlinerFolderNode)VecG.elementAt(iP)).getLabel());
                      if ((iDruckBits & Drucken.cstPntUseSync) > 0)
                      {
                        g.setSyncStamm(iStammtyp, TabStamm.getVecSpalte("AIC"));
                      }
                      if (bThread)
                    	  g.maxThread(TabStamm.size());
                      //if (g.Prog())
                      //  TabStamm.showGrid("TabStamm");
                      boolean bBewZR=(iDruckBits & Drucken.cstPntBewZR)>0;
                      Tabellenspeicher TabBewZR=bBewZR ?new Tabellenspeicher (g,"select aic_bew_pool Aic,anr,gueltig from bew_pool where aic_bew_pool"+Static.SQL_in(TabStamm.getVecSpalte("AIC")),true):null;
                      //if (bBewZR)
                      //  TabBewZR.showGrid("TabBewZR");
                      if (!bED)
                    	  TabStamm.moveFirst();
                      int iStammV=TabStamm.getI("AIC");
                      for (; !TabStamm.eof() && (!bED || TabStamm.getI("AIC")==iStammV) /*&& !gauge.bEscape*/; TabStamm.moveNext())
//                      while (!TabStamm.eof())
                      {
                        //gauge.setText((iMax == 1 ? "" : g.getVonBis("", false) + " ") + (iPmax == 1 ? "" : VecG.elementAt(iP) + " ") + (bAlle ? "" : TabStamm.getS("bezeichnung")),iGaugeCount);
                    	  if (bThread)
                    		  g.momThread(TabStamm.getPos(),TabStamm.getS("Bezeichnung"));
                        //g.fixInfo("Stamm="+TabStamm.getS("Bezeichnung"));
                        long lClockV = Static.get_ms();
                        int iVerlauf=g.SaveVerlauf(iDruck, iBew > 0 || bBewZR ? 0 : TabStamm.getI("AIC"), bBewZR || iBew > 0 ? TabStamm.getI("AIC") : 0);
                        int iStamm=TabStamm.getI("AIC");
                        if (TabBewZR != null)
                        {
                          if (TabBewZR.posInhalt("Aic",iStamm))
                          {
                            iStamm=TabBewZR.getI("ANR");
                            g.setVonBis(TabBewZR.getTimestamp("gueltig"),TabBewZR.getTimestamp("gueltig"));
                            Zeitraum.PeriodeToVec(g,"Monat",true);
                            TabStamm.setInhalt("AIC",iStamm);
                          }
                        }
                        //Static.sleep(2000);
                        if ((iDruckBits & Drucken.cstPntUseSync) > 0)
                        {
                          g.setSyncStamm(iStammtyp, iStamm);
                        }
                        else if (iBew > 0)
                        {
                          g.initJokerBew();
                          g.addJokerBew(TabStamm.getI("AIC"));
                        }
                        if (iModell > 0)
                          if (calc == null)
                            calc = new Calc(null, g, iModell, true, Static.AicToVec(TabStamm.getI("AIC")), -1, null, 0);
                          else
                            calc.reCalc(TabStamm.getI("AIC"), true);
                        g.fixInfo("              TabPerioden="+g.TabPerioden+",           VecPerioden="+VecPerC);
                        Vector VecPer = bPeriode && g.TabPerioden == null && iModell==0 ? bBewZR ? new Vector(g.getVecPer(0)):VecPerC : null;
                        int iMax = VecPer!=null ? VecPer.size() - 1 : g.TabPerioden==null && iModell==0 ? 1:g.TabPerioden==null ? 0:g.TabPerioden.size();
                        g.fixInfo("************  iMax="+iMax+", VecPer="+VecPer);
                        for (int i = 0; i < iMax; i++)
                        {
                          if (bPeriode)
                          {
                            if (VecPer!=null)
                              g.setVonBis((java.sql.Timestamp)VecPer.elementAt(i), (java.sql.Timestamp)VecPer.elementAt(i + 1));
                            else if (g.TabPerioden!=null)
                              g.setVonBis(g.TabPerioden.getTimestamp(i,"von"),g.TabPerioden.getTimestamp(i,"bis"));
                            //g.fixInfo("ZR="+g.getVonBis2());
                            if (bThread)
                            	g.zrThread(Thread.currentThread());
                          }
                          String sDrucktitel= (bKeinDrucktitel ? "":sDrucktitel1)+(!bKeinDrucktitel && bZRT2 ?
                              " " + g.getBegriffS("Show", "fuer") + " " : "") + (bZRT2 ? g.getVonBis("dd.MM.yyyy", bTagesanzeige) : "");
                          if (sDrucktitel.equals(""))
                            sDrucktitel = null;
                          if (VecG != null && !bAIO)
                            sDrucktitel = (sDrucktitel == null ? "" : sDrucktitel + " - ") + Sort.gets(((JCOutlinerNode)VecG.elementAt(iP)).getLabel());
                          dh.setDTitel(sDrucktitel, (iDruckBits & Drucken.cstPntDrucktitelLinks) > 0, foDruck);

                          //g.setSyncStamm(iStammtyp, Static.AicToVec(TabStamm.getI("AIC")));
                          TabAbschnitte.moveFirst();
                          String sTitelStamm = !bStammtitel/* || (TabAbschnitte.getL("bits2") & Abfrage.cstMengen) > 0*/ || (iDFBits & Drucken.cstGruppiert) > 0 ? null :
                              TabStamm.getInhalt("bezeichnung") instanceof Vector ? "" + ((Vector)TabStamm.getInhalt("bezeichnung")).elementAt(0) : TabStamm.getS("bezeichnung");
                          dh.setSTitel(sTitelStamm, (iDFBits & Drucken.cstStammLinks) > 0);
                          if ((iDFBits & Drucken.cstGruppiert) > 0 || (TabAbschnitte.getL("bits2") & Abfrage.cstMengen) > 0 && (TabAbschnitte.getI("Bits") & Drucken.cstArt) == Drucken.cstNormal)
                            TabStamm.moveLast();
                          boolean bFirstAbschnitt = true;
//                          if ((iDFBits&Drucken.cstEMail)>0 && bAdressenImmer)
//                            checkEMailA(null,sDrucktitel);
                          if (Static.bDefShow)
                          {
                        	  if (TabEMailA!=null) TabEMailA.showGrid("TabEMailA1");                         
                        	  if (TabEMailL!=null) TabEMailL.showGrid("TabEMailL1");
                          }
                          //TabAbschnitte.showGrid("TabAbschnitte"); //TODO Testausgabe
                          //g.fixtestInfo("Anzahl Abschnitte="+TabAbschnitte.size()+", eof="+TabAbschnitte.eof()+", Zeile="+TabAbschnitte.getPos());
                          for (; !TabAbschnitte.eof(); TabAbschnitte.moveNext())
                          //if ((TabAbschnitte.getI("Bits") & Drucken.cstArt)!=Drucken.cstDiagramm || (iDFBits & Drucken.cstKeinDiagramm) == 0)
                          {
                            //ShowAbfrage Abf=new ShowAbfrage(g,TabAbschnitte.getI("aic_begriff"),ShowAbfrage.cstBegriff);
                            g.sKennzeichen=TabAbschnitte.getS("Kennzeichen");
                            int iReihe = TabAbschnitte.getI("Reihenfolge");
                            if (iReihe > iReiheOld + 1)
                              dh.addSpace(iReihe - iReiheOld - 1);
                            iReiheOld = iReihe;
                            ShowAbfrage Abf;
                            if (TabAbfragen.posInhalt("Aic", TabAbschnitte.getI("aic_begriff")))
                              Abf = (ShowAbfrage)TabAbfragen.getInhalt("Abfrage");
                            else
                            {
                              Abf = new ShowAbfrage(g, TabAbschnitte.getI("aic_begriff"), Abfrage.cstBegriff);
                              Abf.setSort(iSort);
                              Abf.setDruck((TabAbschnitte.getI("Bits")&Drucken.cstArt)==Drucken.cstHtmlDirekt);//((iDruckBits&Drucken.cstPntHtml)>0 || Static.bHtmlDruck);
                              TabAbfragen.addInhalt("Aic", TabAbschnitte.getI("aic_begriff"));
                              TabAbfragen.addInhalt("Abfrage", Abf);
                            }
                            dh.dZahl = TabAbschnitte.getF("Zahl");
                            if ((TabAbschnitte.getI("Bits") & Drucken.cstRN) == Drucken.cstNeben)
                            {
                              dh.bRechts = TabStamm.getPos() % 2 == 1;
                              //dh.checkY();
                            }
                            g.progInfo("Abschnitt=" + TabAbschnitte.getS("Bezeichnung"));
                            if ((TabAbschnitte.getI("Bits") & Drucken.cstNichtDrucken) > 0 || !g.bTestdruck && (TabAbschnitte.getI("Bits") & Drucken.cstTest) > 0 || ((TabAbschnitte.getI("Bits") & Drucken.cstNurEMail) > 0) && ((iDFBits&Drucken.cstPDF_Art)!=Drucken.cstEMail))
                              ;
                            else if ((TabAbschnitte.getI("Bits") & Drucken.cstArt) == Drucken.cstEMail_A) // E-Mail-Adressen für Empfänger
                            {
                              if (bAdressenImmer)
                              {
                                TabEMailA=null;
                                bAdressenImmer=false;
                              }
                              checkEMailA(Abf, sDrucktitel);
                              if (Static.bDefShow && TabEMailL!=null)
                              {
                            	  if (TabEMailA!=null) TabEMailA.showGrid("TabEMailA2"); 
                            	  if (TabEMailL!=null) TabEMailL.showGrid("TabEMailL2");
                              }
                            }
                            else if ((TabAbschnitte.getI("Bits") & Drucken.cstArt) == Drucken.cstEMail_D) // E-Mail-Daten von Sender für Versand
                            {
                              if (TabEMailD==null && (iDFBits&Drucken.cstPDF_Art)==Drucken.cstEMail)
                                TabEMailD = Abf.getDaten(iStammtyp, 0, TabStamm.getVecSpalte("Aic"),null);
                              if (Static.bDefShow && TabEMailD!=null)
                            	  TabEMailD.showGrid("TabEMailD2");
                            }
                            else if ((TabAbschnitte.getI("Bits") & Drucken.cstArt) == Drucken.cstEMail_S) // E-Mail-Signatur
                            {
                              if (TabEMailS==null && (iDFBits&Drucken.cstPDF_Art)==Drucken.cstEMail)
                                TabEMailS = Abf.getDaten(iStammtyp, 0, TabStamm.getVecSpalte("Aic"),null);
                              if (Static.bDefShow && TabEMailS!=null)
                            	  TabEMailS.showGrid("TabEMailS");
                            }
                            else if ((TabAbschnitte.getI("Bits") & Drucken.cstArt) == Drucken.cstPDF_Save) // PDF-Speichern
                            {
                            	//checkEMailA(Abf, sDrucktitel);
                            	int iPDF_Art=iDFBits&Drucken.cstPDF_Art;
                            	if (TabPdfL==null && iPDF_Art==Drucken.cstPDF_DB)
                                {
                              	  String[] sT=new String[] {"Aic","Name","Filename","von","bis","Titel","Fehler"};
                              	  TabPdfL=new Tabellenspeicher(g, sT);
                                    for(int iT=0;iT<sT.length;iT++)
                                  	  TabPdfL.setTitel(sT[iT], g.getBegriffS("Show", sT[iT]));
                                }
                            	if (TabPdfL!=null)
                                {
                              	  TabPdfL.newLine();
                              	  TabPdfL.setInhalt("Aic",TabStamm.getI("AIC"));
                              	  TabPdfL.setInhalt("Name",g.getStamm(TabStamm.getI("AIC")));
                              	  TabPdfL.setInhalt("von",iLastBis+1);
                              	  TabPdfL.setInhalt("Titel",sDrucktitel);  
                                }
                            	if ((iDFBits&Drucken.cstPDF_Art)==Drucken.cstPDF_DB)
                            	{
	                              if (TabPDF==null)
	                                TabPDF = Abf.getSpalten();//Abf.getDaten(iStammtyp, 0, TabStamm.getVecSpalte("Aic"),null);
	                              iBewPDF=Abf.iBew;
	                              g.fixtestError("Bewegung für PDF-Druck:"+iBewPDF);
	                              if (Static.bDefShow && TabPDF!=null)
	                            	  TabPDF.showGrid("TabPDF");
                            	}
                            }
                            else if ((TabAbschnitte.getI("Bits") & Drucken.cstArt) == Drucken.cstBedingung)
                            {
                              bMeldung=true;
                              String s = Abf.TabToString(Abf.getDaten(iStammtyp, TabStamm.getI("AIC"), null,null));
                              if (s != null)
                              {
                                Static.printError(s);
                                addFehler(TabStamm.getI("AIC"),s,false);
                                //new Message(Message.WARNING_MESSAGE, null, g).showDialog("Druckfehler", new String[] {s});
                                bFehler=true;
                                TabAbschnitte.moveLast();
                              }
                            }
                            else if ((TabAbschnitte.getI("Bits") & Drucken.cstArt) == Drucken.cstPDF_Direkt)
                            {
//                            	if (VecPDF==null)
//                            		VecPDF=new Vector<String>();
                            	Tabellenspeicher Tab=Abf.getDaten(iStammtyp, TabStamm.getI("Aic"), TabStamm.getVecSpalte("Aic"),null);
                            	String sSpalte=Abf.getSpalten().getS(0,"Kennung");
                            	for (int iD=0;iD<Tab.size();iD++)
                            	{
                            		String sFile=SQL.getDoku(g, Tab.getS(iD,sSpalte));
//                            		g.fixtestError("PDF_Direkt:"+sFile);
                            		if (Static.Leer(sFile))
                            				;
                            		else if (sFile.toUpperCase().endsWith("PDF"))
                            			dh.addPDF(sFile);
                            		else
                            		{
                            			dh.newPage();
                            			Image Img=g.LoadImage(sFile);
                            			dh.setBack(Img,false);
                            			//Static.printError("DruckeDruckA-PDF: "+sFile+" kann aktuell nicht gedruckt werden");
                            		}
                            	}
                            	//g.fixtestError("VecPDF="+VecPDF);
                            }
                            else if ((TabAbschnitte.getI("Bits") & Drucken.cstArt) == Drucken.cstIstUeberschrift)
                              dh.setSTitel(Abf.TabToString(Abf.getDaten(iStammtyp, TabStamm.getI("AIC"), null,null)),(iDFBits & Drucken.cstStammLinks) > 0);
                            /*else if ((TabAbschnitte.getI("Bits") & Drucken.cstArt)==Drucken.cstDiagramm)
                                                         {
                              int iRasterMom = iRaster > 0 && (TabAbschnitte.getI("Bits") & Drucken.cstFixRaster) == 0 ? iRaster : TabAbschnitte.getI("aic_raster");
                              Font foAbschnitt = dh.sucheRaster(iRasterMom) ? g.getSchrift(dh.TabRaster.getI("Sch2_aic_Schrift"),g.fontTitel):g.fontTitel;
                              Font foStd = g.getSchrift(dh.TabRaster.getI("aic_Schrift"),g.fontStandard);

                              dh.setATitel((TabAbschnitte.getI("Bits") & Drucken.cstUeberschrift) > 0 ? TabAbschnitte.getS("bezeichnung") : null, true
                                           , foAbschnitt, (TabAbschnitte.getI("Bits") & Drucken.cstKeinAbstand) == 0);
                              dh.printTitel(bFirstAbschnitt && !bFirstStamm && (iDFBits & Drucken.cstSeitenumbruch) > 0, bFirstAbschnitt && bFirstStamm, bFirstAbschnitt, false);
                              dh.printTitel(false, false, false, true);
                              if ((TabAbschnitte.getI("bits2") & Abfrage.cstMengen) > 0)
                              {
                                Diagramm.addDiagramm(g, sKennung, Abf.iBegriff, iStammtyp, TabStamm.getVecSpalte("AIC"), TabAbschnitte.getS("bezeichnung"), iDFBits, dh, foStd);
                              }
                              else
                              {
                                Vector vecHilfe = new Vector();
                                vecHilfe.addElement(new Integer(TabStamm.getI("aic")));
                                Diagramm.addDiagramm(g, sKennung, Abf.iBegriff, iStammtyp, vecHilfe, TabAbschnitte.getS("bezeichnung"), iDFBits, dh, foStd);
                              }
                                                         }*/
                            else
                            {
                              Image Img = TabAbschnitte.getS("Filename").equals("") ? null : SQL.getImageA(g, TabAbschnitte.getI("aic_abschnitt"));//g.LoadImage(TabAbschnitte.getS("Filename"));
                              if (((TabAbschnitte.getI("Bits") & Drucken.cstNeueSeite) > 0 || Img != null) && !(bFirstAbschnitt && bFirstStamm))
                                dh.newPage();
                              if (Img != null)
                                dh.setBack(Img,true);
                              //Tabellenspeicher TabSpalten=Abf.getSpalten();
                              if ((TabAbschnitte.getL("bits2") & Abfrage.cstFremdStamm) > 0)
                              {
                                g.setSyncStamm(iStammtyp, TabStamm.getI("AIC"));
                              }
                              Tabellenspeicher TabDaten = null;
                              if((iDFBits & Drucken.cstGruppiert) == 0)
                              {
                                String sTabTyp = g.TabCodes.getKennung(g.TabBegriffe.getI_AIC("Typ", Abf.iBegriff));
                                if (sTabTyp.endsWith("erf"))
                                {
                                  setSE_Abf(Abf,TabStamm.getI("AIC"));
                                }
//                                TabStamm.showGrid("TabStamm");
                                if (!bAlle || (Abf.iBits & Abfrage.cstMengen) > 0 || (Abf.iBits & Abfrage.cstPlanung) == Abfrage.cstPlanungD)
                                  TabDaten = Abf.getDaten(bBewZR ? g.iSttANR : iStammtyp, TabStamm.getI("AIC"),
                                    (TabAbschnitte.getL("bits2") & Abfrage.cstMengen) > 0 ? TabStamm.getVecSpalte("AIC") : null,null);
                                else
                                  for (TabStamm.moveFirst(); !TabStamm.eof(); TabStamm.moveNext())
                                  {
                                	if (bThread)
                                		g.momThread(TabStamm.getPos(),TabStamm.getS("Bezeichnung"));
                                    Tabellenspeicher TabNeu=Abf.getDaten(bBewZR ? g.iSttANR : iStammtyp, TabStamm.getI("AIC"),null,null);
                                    if (TabDaten==null)
                                      TabDaten=new Tabellenspeicher(g,TabNeu);
                                    else
                                      TabDaten.addTab(new Tabellenspeicher(g,TabNeu));
                                    //Static.sleep(1000);
                                  }
                              }
                              //TabDaten.showGrid("TabDaten");
                              if (Abf != null && Abf.sStammTitel != null)
                                dh.setSTitel(Abf.sStammTitel, (iDFBits & Drucken.cstStammLinks) > 0);
                              boolean bLeer=TabDaten == null || TabDaten.isEmpty() && (TabAbschnitte.getI("Bits") & Drucken.cstLeere) == 0;
                              //g.fixInfo(TabAbschnitte.getS("Bezeichnung")+":"+bLeer);
                              if (bLeer)
                                addFehler((Abf.iBits & Abfrage.cstMengen) > 0 ? 0:TabStamm.getI("AIC"),g.getShow("leere_Daten"),false);
                              else
                              {
                                iAnz++;
                                addFehler((Abf.iBits & Abfrage.cstMengen) > 0 ? 0:TabStamm.getI("AIC"),g.getShow("ok"),true);
                              }
                              if (!bLeer && (TabAbschnitte.getI("Bits") & Drucken.cstNurMitTitel)>0)
                              {
                                Vector Vec=Abf.getTitel();
                                g.testInfo(" ---------xxx-------------- Vec="+Vec);
                                String sSpalte=Vec==null ? Abf.getSpalten().getS("Kennung"):Sort.gets(Vec,0);
                                //if (Vec==null)
                                //  Tabellenspeicher TabSpalten = Abf.getSpalten();
                                //TabSpalten.moveFirst();
                                //TabDaten.moveFirst();
                                g.testInfo(" ---------xxx-------------- Spalte="+sSpalte+" -> <"+TabDaten.getS(sSpalte)+">");
                                bLeer=TabDaten.getS(sSpalte).equals("");
                              }
                              if ((iDFBits & Drucken.cstGruppiert) > 0 || !bLeer || TabPges!=null)//TabDaten != null && (!TabDaten.isEmpty() || (TabAbschnitte.getI("Bits") & Drucken.cstLeere) > 0))
                              {
                                int iRasterMom = iRaster > 0 && (TabAbschnitte.getI("Bits") & Drucken.cstFixRaster) == 0 ? iRaster : TabAbschnitte.getI("aic_raster");
                                Font foAbschnitt = dh.getFontA(iRasterMom,(iDFBits & Drucken.cstPb) > 0);//sucheRaster(iRasterMom) ? g.getSchrift(dh.TabRaster.getI("Sch2_aic_Schrift"), g.fontTitel) : g.fontTitel;
                                dh.setATitel((TabAbschnitte.getI("Bits") & Drucken.cstUeberschrift) > 0 && (TabAbschnitte.getI("Bits") & Drucken.cstArt) != Drucken.cstFixPos ?
                                             TabAbschnitte.getS("bezeichnung") : null, true
                                             , foAbschnitt, (TabAbschnitte.getI("Bits") & Drucken.cstKeinAbstand) == 0);
                                if (bFirstAbschnitt && !bFirstStamm)
                                  dh.addSpace(1);
                                //g.fixtestInfo("vor printTitel "+bFirstAbschnitt+"/"+bFirstStamm+"/"+iP+","+i+","+((iDFBits&Drucken.cstSeitenumbruch)>0));
                                if (bFirstAbschnitt && (!bAIO || iP==iPmax-1))
                                  dh.printTitel(bFirstAbschnitt && (bFirstStamm && (iP > 0 || i > 0) || !bFirstStamm) && (iDFBits & Drucken.cstSeitenumbruch) > 0,
                                                bFirstAbschnitt && bFirstStamm, bFirstAbschnitt, false);
                                AUOutliner GidGesamt = new AUOutliner();
                                ShowAbfrage.initOutliner(g, GidGesamt);
                                Tabellenspeicher TabBreite = Abf.getTabDruckbreite(VecE == null ? 0 : VecE.size());
                                if (g.Def() && (TabAbschnitte.getI("Bits") & Drucken.cstTestfill) > 0)
                                {
                                  Tabellenspeicher TabSpalten = Abf.getSpalten();
                                  for (TabSpalten.moveFirst(); !TabSpalten.out(); TabSpalten.moveNext())
                                    if (TabDaten.isNull(TabSpalten.getS("kennung")))
                                      TabDaten.setInhalt(TabSpalten.getS("kennung"), TabSpalten.getI("kennung2"));
                                  //if (g.Prog())
                                  //  TabDaten.showGrid();
                                }
                                if ((iDFBits & Drucken.cstGruppiert) > 0)
                                {
                                  //GidGesamt.setRootNode((JCOutlinerFolderNode)NodGroup.clone());
                                  GidGesamt.setRootNode(NodGroup);
                                  DruckHS.showOutliner(GidGesamt);
                                  int iAic = 0;
                                  //GidGesamt.folderChanged(GidGesamt.getRootNode());
                                  for (JCOutlinerFolderNode Nod = (JCOutlinerFolderNode)GidGesamt.getRootNode(); Nod != null; Nod = (JCOutlinerFolderNode)GidGesamt.getNextNode(Nod))
                                  {
                                    //g.progInfo("showPrint1:"+Nod+"/"+Nod.getLevel()+"/"+Nod.getUserData()+"/"+iStammtyp);
                                    if (Nod.getLevel() == 0)
                                      ;
                                    else if (iStammtyp == ((Integer)((Vector)Nod.getUserData()).elementAt(1)).intValue())
                                    {
                                      if (iAic == 0)
                                        for (int i2 = (Abf.iBits & Abfrage.cstVerdichten) == 0 ? 0 : 1; i2 < Nod.getLevel(); i2++)
                                        {
                                          g.progInfo("Hinzu:" + i2);
                                          TabBreite.setPos(0);
                                          TabBreite.bInsert = true;
                                          TabBreite.addInhalt("Breite", Static.Int0);
                                          TabBreite.addInhalt("Datentyp", "x");
                                        }
                                      Nod.setState((Abf.iBits & Abfrage.cstVerdichten) == 0 ? BWTEnum.FOLDER_OPEN_ALL : BWTEnum.FOLDER_CLOSED);
                                      iAic = ((Integer)((Vector)Nod.getUserData()).elementAt(0)).intValue();
                                      //g.progInfo("showPrint2:"+Nod+"/"+iAic);
                                      Vector Vec = (Vector)((Vector)Nod.getLabel()).clone();
                                      //for (int i3=1;i3<Vec.size();i3++)
                                      //  Vec.setElementAt(i3,null);
                                      Nod.setLabel(Vec);
                                      if ((TabAbschnitte.getL("bits2") & Abfrage.cstFremdStamm) > 0)
                                      {
                                        g.setSyncStamm(iStammtyp, iAic);
                                        g.setSyncStamm(iStammtyp, Static.AicToVec(iAic));
                                      }
                                      TabDaten = Abf.getDaten(iStammtyp, iAic, null,null);
                                      if (TabDaten != null && !TabDaten.isEmpty())
                                      {
                                        //TabDaten.showGrid(Nod.getLabelString());
                                        Abf.TabToOutliner(GidGesamt, TabDaten, (iDFBits & Drucken.cstLetzteGruppeWeg) > 0 ? Nod.getParent() : Nod, VecE, 1);
                                      }
                                      //else
                                      //  g.fixInfo("!!! "+Nod.getLabelString()+" ist leer");
                                      if ((iDFBits & Drucken.cstLetzteGruppeWeg) > 0)
                                        Nod.getParent().removeChild(Nod);
                                    }
                                    else
                                    {
                                      Vector Vec = (Vector)((Vector)Nod.getLabel()).clone();
                                      //for (int i3=1;i3<Vec.size();i3++)
                                      //  Vec.setElementAt(i3,null);
                                      Nod.setLabel(Vec);
                                    }
                                  }
                                }
                                else
                                  Abf.TabToOutliner(GidGesamt, TabDaten, null);
                                GidGesamt.folderChanged(GidGesamt.getRootNode());
                                DruckHS.showOutliner(GidGesamt);
                                //TabDaten.showGrid("Daten");
                                g.progInfo("Abschnitt " + TabAbschnitte.getS("bezeichnung"));
                                boolean bPlanung=(Abf.iBits & Abfrage.cstPlanung) == Abfrage.cstPlanungD;
                                if (bPlanung)
                                {
                                  String sAbt=bAIO ? Sort.gets(((JCOutlinerNode)VecG.elementAt(iP)).getLabel()) : null;
                                  Tabellenspeicher TabP = bLeer ? null :dh.addPlanung(TabStamm, TabDaten, Abf.getSpalten(), (Abf.iBits & Abfrage.cstMengen) > 0,
                                      TabAbschnitte.getI("Bits"), iDFBits,sAbt);//,TabPges==null ? 1:(int)TabPges.max("0"));
                                  //if (TabP!=null && bAIO)
                                  //  new Tabellenspeicher(g,TabP).showGrid("TabP:"+sAbt);
                                  if (TabPges==null)
                                    TabPges=TabP;
                                  else if (TabP!=null)
                                  {
                                    TabP.clearInhalt(0);
                                    TabPges.addTab(TabP);
                                  }
                                  //if (TabPges!=TabP)
                                  //  new Tabellenspeicher(g,TabPges).showGrid("TabPges:"+sAbt);
                                }
                                if (!bAIO || iP==iPmax-1)
                                {
                                  if (bAIO && dh.TabGesSum!=null)
                                    TabPges.addTabS(dh.TabGesSum);
                                  dh.addOutliner(bPlanung ? (Object)TabPges : GidGesamt, Abf, iRasterMom, TabAbschnitte.getI("Bits"), iDFBits, TabBreite);
                                  //TabPges=null;
                                  dh.TabGesSum=null;
                                }
                                if (!bAIO)
                                  TabPges=null;
                                bFirstAbschnitt = false;
                                bFirstStamm = false;
                              }
                              Clock.add(clock, "Abschnitt", TabAbschnitte.getS("bezeichnung"), -1);
                            }
                            g.sKennzeichen = null;
                            //else
                            //  Static.printError(sTitelAbschnitt+" ist null!");
                          }  // for (; !TabAbschnitte.eof(); TabAbschnitte.moveNext())
                          if (!bFehler && (iDruckBits & Drucken.cstPntUnterschrift) != 0)
                            dh.setUnterschrift();
                          bFehler=false;
                          if (bAlle)
                            TabStamm.moveLast();
//                          iGaugeCount++;
                          //if(bMengen)
                          //  iGaugeCount=gauge.getMax();
                          g.clockInfo("Druck - " + sDrucktitel+" - "+TabStamm.getS("bezeichnung")+Static.Mem(g.SuperUser()), lClock);
                          lClock = Static.get_ms();
                          if (bAIO)
                            i=iMax;
                        }
                        g.SaveVerlaufFertig(iVerlauf,Static.get_ms()-lClockV,false);
                        if (TabEMailA!=null && TabEMailA.posInhalt("Aic_Stamm",TabStamm.getI("AIC")))
                        {
                          iLastBis=dh.pages();
                          TabEMailL.setInhalt("bis", iLastBis);
                        }
                        if (TabPdfL!=null)
                        {
                        	iLastBis=dh.pages();                  
                        	TabPdfL.setInhalt("bis", iLastBis);
                        }
//                        if (!bED)
//                        	TabStamm.moveNext();
                      }
                    }
                  }
                  if (bED && iAnz>0)
                  {
                	  VecDH.addElement(dh);
                	  dh.printReady();
                	  iAnz=0;
                  }
                 }
                }
                catch (Exception e)
                {
                  g.testInfo("Exception:"+e);
                  //if (g.TestPC())
                    e.printStackTrace();
                  //dh=null;
                  bFehler=true;
                  //Drucken.bAktiv=false;
                }
//                g.fixInfo("Druck aufbereitet");
                g.checkVon();
                //if (bPeriode)
                //{
                  g.setVonBis(dtVon,dtBis);
                  g.bTestdruck=false;
                  g.setZA(0,sZA);
                  Zeitraum.PeriodeToVec(g,sZA,true);
                //}
                //if ((iDruckBits&Drucken.cstPntUseSync)>0)
                //      g.setSyncStamm(iStammtyp, TabStamm.getVecSpalte("AIC"));
                if (iSync>-1)
                  g.setSyncStamm(iStammtyp,iSync);
                g.bH_dez=bH_dez;
                g.bH_min=bH_min;
                if (g.bInfoDruck && TabFehler!=null)
                  TabFehler.showGrid("TabFehler");
                if (TabFehler != null && Static.bX11 && (VecDH==null || VecDH.size()==0))
                {
                  Tabellenspeicher TabF2=new Tabellenspeicher(g,new String[]{"Stammsatz","Fehler"});
                  TabF2.setTitel("Stammsatz", g.getShow("Stammsatz"));
                  TabF2.setTitel("Fehler", g.getShow("Fehler"));
                  for(TabFehler.moveFirst();!TabFehler.out();TabFehler.moveNext())
                    if (TabFehler.getI("ok")==0)
                    {
                      TabF2.addInhalt("Stammsatz",TabFehler.getS("Stammsatz"));
                      TabF2.addInhalt("Fehler",TabFehler.getS("Fehler"));
                    }
                  if (!TabF2.isEmpty())
                	if (bMeldung)
                		new Message(Message.WARNING_MESSAGE + Message.SHOW_TAB, null, g).showDialog("Druckfehler2", TabF2);
                	else if (iAnz==0)
                		new Message(Message.WARNING_MESSAGE, null, g).showDialog("Druck_leer");
                	else
                		g.fixtestInfo(iAnz+" Abschnitte gedruckt");
                }
//                g.fixInfo("Fehler gemeldet");
                  //TabFehler.showGrid("Fehler");
                //gauge.setText("fertig",gauge.getMax());
                if (dh != null && !bFehler && (iAnz>0 || VecDH!=null && VecDH.size()>0))
                {
                  if (VecDH==null)
                	g.fixtestInfo("Abschnitte: "+iAnz);
                  else
                	  g.fixtestInfo("Drucke: "+VecDH.size());
                  if ((iDFBits&Drucken.cstPDF_Art)==Drucken.cstPDF)
                  {
                    sPDF_File=dh.printPDF(sPDF_File,1,dh.pages(),sPDF_PW,VecDH);
                    if (sPDF_File != null && (iDFBits & Drucken.cstSeitenvorschau) > 0)
                      g.openFile(sPDF_File);
                  }
                  else if ((iDFBits&Drucken.cstPDF_Art)==Drucken.cstEMail)
                  {
                    if (TabEMailD != null)
                      TabEMailD.moveFirst();
                    if (TabEMailA != null && TabEMailD != null && !TabEMailA.isEmpty() && !TabEMailD.isEmpty() && !TabEMailD.isNull(1) && !TabEMailD.isNull(2))
                    {
                      //TabEMailL.showGrid("TabEMailL");
                      for (TabEMailL.moveFirst(); !TabEMailL.out(); TabEMailL.moveNext())
                      {
                        String sEMail = TabEMailL.getS("E-Mail");
                        if (Static.Leer(sEMail) || sEMail.indexOf("@") < 1)
                          TabEMailL.setInhalt("Fehler", g.getBegriffS("Show","keine")+" E-Mail-"+g.getBegriffS("Show","Adresse"));
                        else
                        {
                          String sFile = Static.getTemp() + iDruck + "_" + TabEMailL.getS("Aic") + ".pdf";
                          dh.printPDF(sFile, TabEMailL.getI("von"), TabEMailL.getI("bis"),TabEMailL.isNull("PW") ? sPDF_PW:((PW1)TabEMailL.getInhalt("PW")).getPW(),VecDH);
                          String sError = SendeEMail(sFile, TabEMailL.getS("Name"), sEMail, sDrucktitel1, TabEMailL.getS("Titel"));
                          if (sError != null)
                            TabEMailL.setInhalt("Fehler", sError);
                        }
                        //g.fixtestInfo("sende "+sFile+" per E-Mail von " + TabEMailD.getS(3) + " {" + TabEMailD.getS(2) + "} an " + TabEMailL.getS("Name") + " {" + TabEMailL.getS("E-Mail") + "}");
                      }
                      new Message(Message.INFORMATION_MESSAGE + Message.SHOW_TAB + Message.UNMODAL, null, g).showDialog("E-Mails_versendet", new String[] {sDrucktitel1}, TabEMailL);
                      //String sFile=Static.getTemp()+iDruck+".pdf";
                    }
                    else
                      new Message(Message.WARNING_MESSAGE, null, g).showDialog("E-Mail-Daten_fehlen");
                  }
                  else if (iBewPDF>0 && (iDFBits&Drucken.cstPDF_Art)==Drucken.cstPDF_DB)
                  {      	  
//                	  TabStamm.moveFirst();
//                	  int iANR=TabStamm.getI("Aic");
                	  if (Static.bDefShow)
                		  TabPdfL.showGrid("TabPdfL");
                	  if (TabPdfL != null)
                	    for (TabPdfL.moveFirst(); !TabPdfL.out(); TabPdfL.moveNext())
                	    {
                		  int iANR=TabPdfL.getI("Aic");
	                	  String sFile = Static.getTemp() + iDruck + "_" + iANR +"_"+g.getVB()+ ".pdf";
	                	  String sPDF_File2=dh.printPDF(sFile,TabPdfL.getI("von"), TabPdfL.getI("bis"),null,VecDH);
	                	  TabPdfL.setInhalt("Filename", sPDF_File2);
	                	  //g.fixtestError("PDF-Datei:"+sPDF_File2+" (von "+sPDF_File+")");
	                	  int iEigANR=TabPDF.posInhalt("Datentyp","BewStamm") ? TabPDF.getI("Kennung2"):0;
	                	  int iEigVB=TabPDF.posInhalt("Datentyp","BewVon_Bis") ? TabPDF.getI("Kennung2"):0;
	                	  int iEigDruck=TabPDF.posInhalt("Datentyp","BewDruck") ? TabPDF.getI("Kennung2"):0;
	                	  int iEigDatum2=TabPDF.posInhalt("Datentyp","BewDatum2") ? TabPDF.getI("Kennung2"):0;
	                	  int iEigDatum=TabPDF.posInhalt("Datentyp","BewDatum") ? TabPDF.getI("Kennung2"):0;
	                	  int iEigDoku=TabPDF.posInhalt("Datentyp","Doku") ? TabPDF.getI("Kennung2"):0;
	                	  g.fixtestError("ANR="+iEigANR+", VB="+iEigVB+", Doku="+iEigDoku);
	                	  SQL Qry=new SQL(g);
	                	  Qry.add("aic_bewegungstyp", iBewPDF);
	                	  int iProt=g.Protokoll("Druck",iDruck);
	                	  Qry.add("aic_protokoll", iProt);
	                	  //Qry.add("ANR", iANR);
	                	  Qry.add("aic_mandant", g.getMandant());
	                	  if (iEigDatum>0)
	                	  {
	                		  Date dt=g.getVortag(g.getBis());
	                		  Qry.add("Gueltig", dt);
	                		  Qry.add("LDATE", Static.DateToInt(dt));
	                		  Qry.add("Zone", g.getZone());
	                	  }
	                	  int iPool=Qry.insert("Bew_Pool",true);
	                	  if (iEigANR>0)
	                		  Import.insertBewStamm(g, iBewPDF, iPool, iEigANR, iANR);
	                	  if (iEigDruck>0)
	                	  {
	                		  Qry.add("aic_bew_pool", iPool);
	                		  Qry.add("aic_eigenschaft", iEigDruck);
	                		  Qry.add("aic_begriff", iDruck);
	                		  Qry.insert("Bew_Begriff", false);
	                	  }
	                	  if (iEigVB>0)
	                	  {
	                		  Qry.add("aic_bew_pool", iPool);
	                		  Qry.add("aic_eigenschaft", iEigVB);
	                		  Qry.add("von", g.getVon());
	                		  Qry.add("bis", g.getBis());
	                		  Qry.add("Dauer", 0);
	                		  Qry.insert("Bew_Von_Bis", false);
	                	  }
	                	  if (iEigDatum2>0)
	                	  {
	                		  Qry.add("aic_bew_pool", iPool);
	                		  Qry.add("aic_eigenschaft", iEigDatum2);
	                		  Qry.add("von", g.getVortag(g.getBis()));
	                		  Qry.add("Dauer", 0);
	                		  Qry.insert("Bew_Von_Bis", false);
	                	  }
	                	  if (iEigDoku>0)
	                	  {
	                		  int iDaten=Import.getAicDaten(g,"Doku",sPDF_File2,iEigDoku);
	                		  Qry.add("Aic_Bew_Pool", iPool);
	                		  Qry.add("aic_eigenschaft", iEigDoku);
	                		  Qry.add("Aic_Daten", iDaten);
	                		  Qry.add("aic_protokoll",iProt);
	                		  Qry.insert("Stammpool", false);
	                	  }
                	    }
                	  //new Message(Message.INFORMATION_MESSAGE, null, g).showDialog("PDFs_gespeichert");
                	  new Message(Message.INFORMATION_MESSAGE + Message.SHOW_TAB + Message.UNMODAL, null, g).showDialog("E-PDFs_gespeichert", new String[] {sDrucktitel1}, TabPdfL);
                  }
                  else if ((iDFBits & Drucken.cstSeitenvorschau) > 0)
                  {
                	  //g.fixInfo("vor Vorschau");
                	  //Thread.currentThread().setDaemon(true);
                	  //Thread.currentThread().stop();
//                	g.fixInfo("vor Vorschau");
                	if (VecDH == null)
                		dh.vorschau();
                	else
                		for (int i=0;i<VecDH.size();i++)
                			VecDH.elementAt(i).vorschau();
//                    g.fixInfo("nach Vorschau");
                    //g.fixInfo("nach Vorschau");
                    Clock.add(clock, "Vorschau", "", -1);
                  }
                  else
                  {
                    dh.print();
                    Clock.add(clock, "Druck", "", -1);
                  }
                }
                Clock.showClock(g, clock, (bFehler?"Fehler bei ":"")+"Druck - " + sDrucktitel1, lClock2);
                if (Static.bX11 && bThread)
                    g.endThread(Thread.currentThread());
                if (g.bTestLog)
                	g.Logout();
                else
                	g.unConnect();
                //g.fixInfo("nach unConnect");
                
//                if (VecPDF != null)
//                	for (int i=0;i<VecPDF.size();i++)
//                		Static.PrintFile(g,Sort.gets(VecPDF, i));
                //Thread.currentThread().stop();
	}

        private void checkEMailA(ShowAbfrage Abf,String sDrucktitel)
        {
          //g.fixtestError("CheckEMailA");
          Tabellenspeicher TabEASpalten=null;
          int iPDF_Art=iDFBits&Drucken.cstPDF_Art;
          if (TabEMailA==null && Abf!=null && iPDF_Art==Drucken.cstEMail)
          {
            TabEMailA = Abf.getDaten(iStammtyp, 0, TabStamm.getVecSpalte("Aic"),null);
            String[] sT=new String[] {"Aic","Name","E-Mail","von","bis","Titel","Fehler","PW"};
            TabEASpalten=Abf.getSpalten();
            TabEASpalten.moveFirst();
            sEMailName=TabEASpalten.getS("Kennung");
            TabEASpalten.moveNext();
            sEMail=TabEASpalten.getS("Kennung");
            TabEASpalten.moveNext();
            if (!TabEASpalten.out())
            	sPW=TabEASpalten.getS("Kennung");
            TabEMailL=new Tabellenspeicher(g, sT);
            for(int i=0;i<sT.length;i++)
              TabEMailL.setTitel(sT[i], g.getBegriffS("Show", sT[i]));
            //TabEMailA.showGrid("TabEMailA");
          }
          //g.fixtestInfo("E-Mail dazu für "+TabStamm.getS("Bezeichnung")+"("+TabStamm.getI("AIC")+")");
          if (TabEMailL != null && /*sDrucktitel!=null &&*/ TabEMailA.posInhalt("Aic_Stamm",TabStamm.getI("AIC"))) // 11.5.2018 Prüfung auf Drucktitel wegen Druck "Rechnung per E-Mail" entfernt
          {
            TabEMailL.newLine();
            TabEMailL.setInhalt("Aic",TabStamm.getI("AIC"));
            TabEMailL.setInhalt("Name",TabEMailA.getS(sEMailName));
            TabEMailL.setInhalt("E-Mail",TabEMailA.getS(sEMail));
            if (sPW!=null && !TabEMailA.isNull(sPW))
            	TabEMailL.setInhalt("PW",new PW1(TabEMailA.getS(sPW)));
            //g.fixtestInfo("-> E-Mail="+TabEMailA.getS(2));
            TabEMailL.setInhalt("von",iLastBis+1);//dh.pages());
            TabEMailL.setInhalt("Titel",sDrucktitel);            
          }       
        }


        private String SendeEMail(String sFile,String sName,String sEMail,String sDrucktitel1,String sDrucktitel)
        {
          Stack<String> Sta=new Stack<String>();
          if (!TabEMailD.isNull(5))
          {
            Sta.push(TabEMailD.getS(5));
            Sta.push(TabEMailD.getS(4));
          }
          String sNameAbs=Static.beiLeer(TabEMailD.getS(3),"All_Unlimited");
          Sta.push(TabEMailD.getS(2));
          Sta.push(sNameAbs);
          Sta.push(TabEMailD.getS(1));
          SMTP2 smtp=new SMTP2(g,Sta);
          Sta.push(sEMail);
          Sta.push(sName);
          smtp.sendTo(Sta);
          Sta.push(TabEMailD.getS(2));
          Sta.push(sNameAbs);
          smtp.sendBCC(Sta);
          smtp.subject(sDrucktitel1);
          smtp.sendText(/*"anbei "+*/sDrucktitel+"<br><br>");
          //g.fixtestInfo("TabEMailS="+TabEMailS);
          if (TabEMailS!=null)
          {
            TabEMailS.moveFirst();
        	//g.fixtestInfo("TabEMailS: Fehler="+TabEMailS.fehler()+"/ out="+TabEMailS.out()+", Aic="+TabEMailS.getS(0));
        	//g.fixtestInfo("File="+TabEMailS.getS(1)+", Text="+TabEMailS.getS(2));
            String s=null;
            if (!TabEMailS.isNull(1)) // Signatur-Bild
            {
              s=Static.ImageToString(Calc.getImage(g,TabEMailS.getS(1)), Calc.getExt(TabEMailS.getS(1)));
              if (s!=null)
                s+= "\" />";
            }
            if (s!=null || !TabEMailS.isNull(2))
              smtp.sendText((s!=null ? s:TabEMailS.getM(2))+"<br><br>");
              //smtp.attach(TabEMailS.getS(1));
          }
          smtp.attach(sFile);
          smtp.quit();
          Static.sleep(Static.iESleep*1000);
          new java.io.File(sFile).delete();
          return smtp.getError();
        }

}

