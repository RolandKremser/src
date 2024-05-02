package All_Unlimited.Print;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
//import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.Barcode;
import All_Unlimited.Allgemein.Anzeige.Memo1;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.TMZ;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Allgemein.AUVector;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import java.util.Date;
import java.util.Iterator;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
//import com.lowagie.text.pdf.PdfFileSpecification;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfObject;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
//import com.lowagie.text.pdf.draw.DottedLineSeparator;
import com.lowagie.text.Document;
//import com.lowagie.text.List;
import com.lowagie.text.Rectangle;
import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import All_Unlimited.Allgemein.SimpleFileFilter;
import javax.swing.JFileChooser;

public class DruckHS
{
  private PrintManagerA pm;
	private Global g;
	//private Font foTitel=new Font("TimesRoman",Font.BOLD,14);
	private Color coTitel=Color.black;
	private int iHSpace=5;                          //Abstand zwischen Tabellen und Tabellen oder Überschrift
        //public int iVSpace=3;
        private String sDTitel;
        private String sDTitelOld="?";
        private String sSTitel;
        private String sATitel;
        private boolean bDTitel=false;
        private boolean bSTitel=false;
        private boolean bATitel=false;
        private boolean bAAbstand=false;
        private Font foDTitel=null;
        private Font foATitel;
        private Tabellenspeicher TabRaster;
        private boolean bQuer=false;
        boolean bRechts=false;
        private int iYstart=0;
        private int iYende=0;
        private int iABits=0;
        public Tabellenspeicher TabFarbe=null;
        public double dZahl=0.0;
        private boolean bErsteZeile=false;
        private boolean bSU=false;
        private static int iFirma=0;
        private int iRN0=-1; // Rechts-Neben-Vorher
        private int iZeileP=1;
        public Tabellenspeicher TabGesSum=null;
        private int iPosLast=-1;
        private String sLast=null;

        public static AUVector DtVB=new AUVector(new String[] {"von_bis","Auto_von_bis","BewVon_Bis"});
        public static AUVector DtDatum=new AUVector(new String[] {"Datum","Eintritt","Austritt","BewDatum","BewDatum2","EinAustritt"});
        public static AUVector DtZahl=new AUVector(new String[] {"Integer","Double","Mass","Waehrung","BewMass","BewWaehrung","BewDauer"});

        /**
         * erzeugt DruckHS
         */

        public static void printOutliner(final Global g,final String rsTitel,final boolean rbQuer,final JCOutliner Out)
        {
          new Thread(new Runnable()
          {
            public void run()
            {
              DruckHS dh = new DruckHS(g, rsTitel, 0,(rbQuer ? Drucken.cstPntQuerformat:0),0);
              //dh.setDTitel(rsTitel, true, null);
//              dh.pm.setFont(dh.foDTitel);
//              dh.setTitle(rsTitel, PrintManagerA.CENTER);
              dh.printOutliner(Out);
              dh.vorschau();
            }
          }).start();
        }

	public DruckHS(Global rg,String sTitel,int iLayout,long iDruckBits,int iStamm)
	{
		g=rg;
                if (iStamm>0)
                    iFirma=SQL.getInteger(g,"select firma from stammview2 where aic_stamm="+iStamm);
                g.testInfo("---------------- Stamm="+iStamm+" -> Firma="+iFirma);
                if (iLayout==0)
                {
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_layout,aic_benutzer,aic_mandant,aic_stamm from layout where (aic_mandant is null or aic_mandant=" +
                      g.getMandant() + ") and" + g.bit("bits", Drucken.cstLayStd),true);
                  int i=0;

                  iFirma=getFirma(g);
                  int iB=g.getBenutzer();
                  if (!Tab.isEmpty())
                    while (iLayout==0)
                    {
                      i++;
                      for (Tab.moveFirst(); !Tab.out() && iLayout == 0; Tab.moveNext())
                        if (i>4 || Tab.getI("aic_mandant")==g.getMandant() && (i==1 && iB==Tab.getI("aic_benutzer") && iFirma==Tab.getI("aic_stamm")
                            || i==3 && iB==Tab.getI("aic_benutzer") && Tab.isNull("aic_stamm")
                            || i==2 && Tab.isNull("aic_benutzer") && iFirma==Tab.getI("aic_stamm")
                            || i==4 && Tab.isNull("aic_benutzer") && Tab.isNull("aic_stamm")))
                          iLayout=Tab.getI("aic_layout");
                    }
                  //iLayout = SQL.getInteger(rg,"select aic_layout from layout where (aic_mandant is null or aic_mandant=" + g.getMandant() + ") and" + g.bit("bits", Drucken.cstLayStd));
                }
                bQuer=(iDruckBits&Drucken.cstPntQuerformat)>0;
//                rg.LoadSchrift(false,100);
//                g.clockInfo("vor new PrintManagerA");
		pm=new PrintManagerA(sTitel,iLayout,iDruckBits,g);
//                g.clockInfo("nach new PrintManagerA");
		newPage();
//                g.clockInfo("newPage");
		getOptions(iLayout,iDruckBits);
	}

        public void newPage()
        {
          iYstart=0;
          iYende=0;
          bSU=true;
          pm.newP();
        }

        public void setDTitel(String s,boolean b,Font fo)
        {
          sDTitel=s;
          bDTitel=b;
          if (fo != null)
            foDTitel=fo;
          else if (foDTitel==null)
            foDTitel=g.fontTitel;
        }

        public void setSTitel(String s,boolean b)
        {
          sSTitel=s;
          bSTitel=b;
          //g.progInfo("setSTitel:"+s);
        }

        public void setATitel(String s,boolean b,Font fo,boolean bAbstand)
        {
          sATitel=s;
          bATitel=b;
          foATitel=fo;
          bAAbstand=bAbstand;
          //if (foDTitel==null)
          //  foDTitel=foATitel;
        }

        public void addSpace(int iCm)
        {
          pm.iCurY+=iCm * 720 / 25.4;
        }

        public void printTitel(boolean bUmbruch,boolean bDrucktitel,boolean bStammtitel,boolean bAbschnitttitel)
        {
          //g.fixtestInfo("printTitel: "+bUmbruch+"/"+(bDrucktitel?sDTitel:bStammtitel?sSTitel:bAbschnitttitel?sATitel:"--"));
          //if((pm.iCurY+2*iHSpace+iTitelHeight+iBezHeight+iZwSummenHeight+10)>(pm.getIY()+pm.getIHeight()))
          //if ( (pm.iCurY + 2 * iHSpace + 40) > (pm.getIY() + pm.getIHeight()))
          double dOld=pm.dFaktor;
          pm.dFaktor=1.0;
          if (!bRechts && (pm.iCurY + 80) > (pm.getIY() + pm.getIHeight()))
            bUmbruch=true;
          //g.progInfo("printTitel:"+bStammtitel+"/"+bAbschnitttitel+"/"+sSTitel+":"+pm.iCurY+"/"+bUmbruch+" | "+(pm.getIY()+pm.getIHeight()));
          bErsteZeile=bUmbruch;
          if(bUmbruch)
            newPage();
          pm.setBGColor(null);
          pm.setColor(Color.BLACK);
          if ((bDrucktitel || bUmbruch || (sDTitel != null && !sDTitel.equals(sDTitelOld))) && (sDTitel != null || sSTitel != null))
          {
            sDTitelOld=sDTitel;
            if (foDTitel == null)
                Static.printError("foDTitel ist null");
              else
                pm.setFont(foDTitel);
            if (sDTitel != null)
              setTitle(sDTitel,bDTitel ? PrintManagerA.LEFT : PrintManagerA.CENTER);
          }
            if ((bStammtitel || bUmbruch) && sSTitel != null)
            {
              if ((iABits&Drucken.cstRN)==Drucken.cstNeben && !bRechts)
                iYstart=pm.iCurY;
              pm.setFont(foDTitel);
              setTitle(sSTitel, bSTitel ? PrintManagerA.LEFT : PrintManagerA.CENTER);
            }
          if (bAAbstand && dZahl>0)
              pm.iCurY += dZahl*72/25.4;
          if (bAbschnitttitel)
          {
            if (bAAbstand && dZahl==0)
              pm.iCurY += 10;
            if (sATitel != null)
            {
              if (foATitel == null)
                Static.printError("foATitel ist null");
              else
                pm.setFont(foATitel);
              setTitle(sATitel, bATitel ? PrintManagerA.LEFT : PrintManagerA.CENTER);
            }
          }
          pm.dFaktor=dOld;
        }

        public void LoadRaster(int iAIC_Raster)
        {
          if (TabRaster==null)
            TabRaster=new Tabellenspeicher(g,"select aic_raster,kennung "+g.AU_Bezeichnung("raster")+
                                           ",aic_schrift,sch_aic_schrift,sch2_aic_schrift,sch3_aic_schrift,sch4_aic_schrift,sch5_aic_schrift,sch6_aic_schrift,bits from raster"+
                                           (iAIC_Raster<0 ? "":" where "+(iAIC_Raster>0 ? "aic_raster="+iAIC_Raster:g.bit("bits",Drucken.cstStandard))),true);
        }

        private boolean sucheRaster(int iAICFormat,boolean bKartei)
        {
          LoadRaster(-1);
          if(iAICFormat>0 && TabRaster.posInhalt("aic_raster", iAICFormat))
            return true;
          else
          {
            if (iAICFormat>0)
              Static.printError("DruckHS: Raster " + iAICFormat + " nicht gefunden!");
            for (TabRaster.moveFirst(); !TabRaster.eof(); TabRaster.moveNext())
              if ((TabRaster.getI("bits") & Drucken.cstStandard) > 0 && bKartei == (TabRaster.getI("bits") & Drucken.cstKartei) > 0)
                return true;
            Static.printError("DruckHS: Standard-Raster nicht gefunden!");
          }
          return false;
        }

        public Font getFontA(int iRasterMom,boolean bKartei)
        {
          return sucheRaster(iRasterMom,bKartei) ? g.getSchrift(TabRaster.getI("Sch2_aic_Schrift"), g.fontTitel) : g.fontTitel;
        }
        /**
         * druckt übergebenen Outliner
         */

        public void checkY()
        {
          //g.fixInfo("checkY:"+(iABits&Drucken.cstRN)+"/"+iRN0);
          if (bRechts && ((iABits&Drucken.cstRN)==Drucken.cstRechts1 && iRN0==Drucken.cstLinks || (iABits&Drucken.cstRN)==Drucken.cstNeben))
            pm.iCurY=iYstart;
          else
            iYstart=pm.iCurY;
        }

        public void checkY2()
        {
          if (bRechts)// || (iABits&Drucken.cstZeilenende)>0)
            iYende=pm.iCurY;
          else
            if (iYende>pm.iCurY)
              pm.iCurY=iYende;

        }

        public void printOutliner(JCOutliner Out)
        {
          long lClock = Static.get_ms();
          String sT=Sort.gets(Out.getRootNode().getLabel());
          setDTitel(sT,false,g.fontTitel);
          Vector vec=getOutlinerNodes(Out); // Out.getVisibleNodes();
          String sLabels[]=Out.getColumnLabels();
          //for(int i=0;i<sLabels.length;i++)
          //  g.progInfo("                            sLabel"+i+"="+sLabels[i]);
          int iAlign[]=Out.getColumnAlignments();
//          for(int i=0;i<iAlign.length;i++)
//            g.fixtestError("                            Align von "+sLabels[i]+"("+i+") = "+iAlign[i]);
          int iWidth[]=Out.getColumnWidths();
          //Out.getColumnDisplayWidths()
          for(int i=0;i<iWidth.length;i++)
        	  iWidth[i]=Out.getColumnDisplayWidth(i);
            //g.fixtestError("                            Width von "+sLabels[i]+"("+i+") ="+iWidth[i]+"/"+Out.getColumnDisplayWidth(i));
          //int iAnz=Out.getNumColumns();
          //g.progInfo("                              iAnz="+iAnz);
          int iGesW=0;
          for (int i = 0; i < sLabels.length; i++)
            if (iWidth[i]>10)
              iGesW+=iWidth[i];
          double dDruckBreite=pm.getIWidth(false);
          pm.dFaktor = dDruckBreite > iGesW ? 1.0 : dDruckBreite / iGesW;
          if(pm.dFaktor < 1.0)
            for(int i = 0; i < sLabels.length; i++)
              if (iWidth[i]>10)
                 iWidth[i]=(int)Math.floor(iWidth[i] * pm.dFaktor);

          boolean bFirst=true;
          for (int i=0;i<vec.size();i++)
          {
            if (bFirst || pm.iCurY+16>pm.iFootY)
            {
              printTitel(false,true,false,false);
              int iX=(int)pm.getIX(false);
              int iY = pm.iCurY + 5;
              pm.setFont(g.fontTitel);
              for (int i2 = 0; i2 < sLabels.length; i2++)
                if (iWidth[i2]>10)
              {
                pm.drawSpalte(sLabels[i2], iX, iY, iAlign[i2] == 1 ? PrintManagerA.CENTER : iAlign[i2] == 2 ? PrintManagerA.RIGHT : PrintManagerA.LEFT, 0, iWidth[i2], true,false);
                //g.progInfo("Pos/Breite bei "+i2+":"+iX+"/"+iWidth[i2]);
                iX += /*iWidth[i2] < 0 ? 40 :*/ iWidth[i2];
              }
              /*if (bFirst)
              {
                iX=(int)pm.getIX(false);
                iY=pm.iCurY;
                Vector Vec2=Sort.getVec(Out.getRootNode().getLabel());
                Font font=Out.getRootNode().getStyle() == null ? Out.getFont():Out.getRootNode().getStyle().getFont();
                pm.setFont(font==null ? g.fontStandard:font);
                for (int i2=0;i2<Vec2.size();i2++)
                  if (iWidth[i2]>10)
                {
                  Object Obj = Vec2.elementAt(i2);
                  if (Obj != null)
                    pm.drawSpalte("" + Obj, iX, iY,
                                  iAlign[i2] == 1 ? PrintManagerA.CENTER : iAlign[i2] == 2 ? PrintManagerA.RIGHT : PrintManagerA.LEFT, 0, iWidth[i2], true);
                  iX +=  iWidth[i2];
                }
              }*/
              bFirst=false;
            }
            JCOutlinerNode Nod=(JCOutlinerNode)vec.elementAt(i);
            int iX=(int)pm.getIX(false);
            int iY=pm.iCurY;
            Image Img=Nod.getStyle()==null ? null:Nod instanceof JCOutlinerFolderNode ? Nod.getStyle().getFolderOpenIcon():Nod.getStyle().getItemIcon();
            if (Img != null)
              pm.drawImage(Img,iX + Nod.getLevel()*10-12,iY+3,(int)(12*pm.dFaktor),(int)(12*pm.dFaktor),PrintManagerA.LEFT);
            Vector Vec2=Sort.getVec(Nod.getLabel());
            Font font=Nod.getStyle() == null ? Out.getFont():Nod.getStyle().getFont();
            Color col=Nod.getStyle() == null ? null:Nod.getStyle().getForeground();
            pm.setFont(font==null ? g.fontStandard:font);
            pm.setColor(col==null ? Color.BLACK:col);
            for (int i2=0;i2<Vec2.size();i2++)
              if (iWidth[i2]>10)
            {
              Object Obj=Vec2.elementAt(i2);
              //g.progInfo(col+":"+Obj);
              if (Obj != null)
                pm.drawSpalte("" + Obj, iX + (i2==0 ? Nod.getLevel()*10:0), iY,
                            iAlign[i2] == 1 ? PrintManagerA.CENTER : iAlign[i2] == 2 ? PrintManagerA.RIGHT : PrintManagerA.LEFT, 0, iWidth[i2], true,false);
              iX+=/*iWidth[i2]<0 ? 40:*/iWidth[i2];
            }
          }
          g.clockInfo("printOutliner "+sT,lClock);
        }

        private Color getColor(String s,int iPos,Color C)
        {
          //g.TabFarbe.posInhalt("AIC", g.TabSchrift.getInhalt("Farbe",iPosS)) ? (Color) g.TabFarbe.getInhalt("Farbe"):Color.BLACK;
          if (iPos<0)
            return C;
          else
          {
            int iAic=g.TabSchrift.getI(iPos,s);
            if (iAic==0)
              return C;
            else
            {
              iPos=g.TabFarbe.getPos("AIC",iAic);
              return iPos<0 ? C:(Color) g.TabFarbe.getInhalt("Farbe",iPos);
            }
          }
        }

	public void addOutliner(Object Obj,ShowAbfrage Abf,int iAICFormat,int riABits,int iDFBits,Tabellenspeicher TabBreite)
	{
          //g.printInfo("addOutliner1:"+sSTitel+":"+pm.iCurY+"/"+bRechts);
          int iYvor=pm.iCurY;
          bSU=false;
		//Vector vecDaten=makeTable(outliner);
                //long lClock = Static.get_ms();
                iABits=riABits;
                if (iABits==-1)
                  iABits=Abf==null || (Abf.iBits&Abfrage.cstKeineGesamtsumme)==0 ? Drucken.cstGesamtsumme:0;
                boolean bSchichtTMZ=(iABits&Drucken.cstArt)==Drucken.cstSchichtTMZ || (iDFBits & (Drucken.cstTMZ+Drucken.cstPb)) == Drucken.cstTMZ+Drucken.cstPb;
                if (bSchichtTMZ && (iABits&Drucken.cstGesamtsumme)==0)
                  iABits+=Drucken.cstGesamtsumme;

                Tabellenspeicher TabDaten=null;
                if (Obj instanceof JCOutliner)
                {
                  JCOutliner outliner=(JCOutliner)Obj;
                  JCOutlinerNode root = outliner.getRootNode();
                  if (Static.bX11 && (root == null || outliner.getNextNode(root) == null))
                    return;
                  TabFarbe=null;
                  TabDaten = makeTable(outliner, iABits,Abf); //(Tabellenspeicher)vecDaten.elementAt(0);
                }
                else
                  TabDaten = (Tabellenspeicher)Obj;
                boolean bES=(iABits&Drucken.cstKeineSumme)==0 && (iDFBits&Drucken.cstKeineSumme)==0;
                boolean bGS=(iABits&Drucken.cstGesamtsumme)>0 && (iDFBits&Drucken.cstKeineSumme)==0;
                boolean bHS=(iABits&(Drucken.cstkeineHauptsumme+Drucken.cstKeineSumme))==0 && (iDFBits&Drucken.cstKeineSumme)==0;
                if (!bES || !bGS || !bHS)
                {
                  for(TabDaten.moveFirst();!TabDaten.eof();)
                  {
                    String sArt=TabDaten.getS("0");
                    if(!bES && sArt.equals("Z") || !bHS && sArt.equals("H") || !bGS && sArt.equals("S"))
                      TabDaten.clearInhalt();
                    else
                      TabDaten.moveNext();
                  }
                  TabDaten.moveFirst();
                }
                boolean bCol=TabDaten.exists("color");
                //if (g.Prog())
                if (g.bInfoDruck)
                  TabDaten.showGrid(sATitel+"1");
		//int iAnzSpalten=TabDaten.getAnzahlSpalten();//((Integer)vecDaten.elementAt(1)).intValue();
                Font foSpalte=g.fontStandard;//new Font("SansSerif",Font.PLAIN,8);
                Font foBez=g.fontBezeichnung;//new Font("SansSerif",Font.PLAIN,8);

		//Font foSperr=g.fontStandard;
                Font foZwSummen=g.fontStandard;
                Font foSummen=g.fontStandard;//new Font("SansSerif",Font.PLAIN,8);
                Font foNegativ=g.fontStandard;

                Color coBGSpalte=Color.white;
                Color coBGBez=Color.white;
                //Color coBGSperr=Color.white;
		Color coBGZwSummen=Color.white;
                Color coBGSummen=Color.white;
                Color coBGNegativ=Color.white;

		Color coSpalte=Color.black;
		Color coBez=Color.black;
                //Color coSperr=Color.black;
		Color coZwSummen=Color.black;
                Color coSummen=Color.black;
                Color coNegativ=Color.black;//Color.red;

		boolean bHorLines=false;
		boolean bVerLines=false;
                boolean bTitellinie=true;
                boolean bZwOben=false;
                boolean bZwUnten=false;
                boolean bSummeOben=true;
                boolean bSummeUnten=true;
                boolean bRahmen=false;
                boolean bRahmenTitel=false;
                boolean bFarbe=false;
                boolean bSpaltenAbstand=false;
                int iBits=0;
                long lBits=Abf==null?0:Abf.iBits;
                int iX0=0;
		if(sucheRaster(iAICFormat,(iDFBits & Drucken.cstPb) > 0))
                  iAICFormat=TabRaster.getI("aic_raster");
		//{
			if(iAICFormat>0)//sucheRaster(iAICFormat))
			{
			  int iPosS=g.TabSchrift.getPos("AIC",TabRaster.getI("aic_schrift"));
				foSpalte=iPosS>=0 ? (Font)g.TabSchrift.getInhalt("Schrift",iPosS):g.fontStandard;
                                coSpalte=getColor("Farbe",iPosS,Color.BLACK);
                                coBGSpalte=getColor("FarbeHG",iPosS,Color.WHITE);
                              iPosS=g.TabSchrift.getPos("AIC",TabRaster.getI("sch_aic_schrift"));
                                foBez=iPosS>=0 ? (Font)g.TabSchrift.getInhalt("Schrift",iPosS):g.fontBezeichnung;
                                coBez=getColor("Farbe",iPosS,Color.BLACK);
                                coBGBez=getColor("FarbeHG",iPosS,Color.WHITE);

                                //foSperr=g.TabSchrift.posInhalt("AIC",TabRaster.getI("sch3_aic_schrift")) ? (Font)g.TabSchrift.getInhalt("Schrift"):foSpalte;
                                //coSperr=g.TabSchrift.pos() && g.TabFarbe.posInhalt("AIC", g.TabSchrift.getInhalt("Farbe")) ? (Color) g.TabFarbe.getInhalt("Farbe"):Color.BLACK;
                                //coBGSperr=g.TabSchrift.pos() && g.TabFarbe.posInhalt("AIC", g.TabSchrift.getInhalt("FarbeHG")) ? (Color) g.TabFarbe.getInhalt("Farbe"):Color.WHITE;
                              iPosS=g.TabSchrift.getPos("AIC",TabRaster.getI("sch4_aic_schrift"));
                                foZwSummen=iPosS>=0 ? (Font)g.TabSchrift.getInhalt("Schrift",iPosS):foSpalte;
                                coZwSummen=getColor("Farbe",iPosS,Color.BLACK);
                                coBGZwSummen=getColor("FarbeHG",iPosS,Color.WHITE);
                              iPosS=g.TabSchrift.getPos("AIC",TabRaster.getI("sch5_aic_schrift"));
                                foSummen=iPosS>=0 ? (Font)g.TabSchrift.getInhalt("Schrift",iPosS):foSpalte;
                                coSummen=getColor("Farbe",iPosS,Color.BLACK);
                                coBGSummen=getColor("FarbeHG",iPosS,Color.WHITE);
                              iPosS=g.TabSchrift.getPos("AIC",TabRaster.getI("sch6_aic_schrift"));
                                foNegativ=iPosS>=0 ? (Font)g.TabSchrift.getInhalt("Schrift",iPosS):foSpalte;
                                coNegativ=getColor("Farbe",iPosS,Color.BLACK);//Color.RED;
                                coBGNegativ=getColor("FarbeHG",iPosS,Color.WHITE);

                                iBits=TabRaster.getI("bits");
				bHorLines=(iBits&Drucken.cstAllgemeinHorizontallinien)>0;
				bVerLines=(iBits&Drucken.cstAllgemeinVertikallinien)>0;
                                bTitellinie=(iBits&Drucken.cstLU3)>0;
                                bZwOben=(iBits&Drucken.cstLO1)>0;
                                bZwUnten=(iBits&Drucken.cstLU1)>0;
                                bSummeOben=(iBits&Drucken.cstLO2)>0;
                                bSummeUnten=(iBits&Drucken.cstLU2)>0;
                                bRahmen=(iBits&Drucken.cstRahmen)>0;
                                bRahmenTitel=(iBits&Drucken.cstRahmen2)>0;
                                bFarbe=(iBits&Drucken.cstHG_Farbe)>0 && TabFarbe != null;
                                bSpaltenAbstand=(iBits&Drucken.cstSpaltenAbstand)>0;
                                //g.progInfo("bTitellinie="+bTitellinie+", bRahmenTitel="+bRahmenTitel);
			}
                        //else
                        //  Static.printError("DruckHS: Raster "+iAICFormat+" nicht gefunden");

		//}
                //if ((iDFBits & Drucken.cstGesamtrahmen)>0)
                //  pm.makeFrame(true,true);
                //Font foBez=new Font("Courier New",Font.BOLD,11);
                pm.setBGColor(null);
		//pm.setFont(foTitel);
		//int iTitelHeight=pm.stringHeight();
		pm.setFont(foZwSummen);
		int iZwSummenHeight=pm.stringHeight();
		pm.setFont(foSpalte);
		int iSpalteHeight=pm.stringHeight();
		pm.setFont(foBez);
		//int iBezHeight=pm.stringHeight()*2;
                //int iAlignStammTitel=(iDruckBits & Drucken.cstPntStammLinks)>0 || (iDFBits & Drucken.cstStammLinks)>0 ? PrintManagerA.LEFT:PrintManagerA.CENTER;
                //int iABits=TabAbschnitt != null ? TabAbschnitt.getI("Bits"):0;
                //g.progInfo("Abschnitts-Bits="+iABits);
                //int iAlignAbschnittTitel=/*(iABits & Drucken.cstUeberschriftLinks)>0?*/PrintManagerA.LEFT;//:PrintManagerA.CENTER;
                if ((iABits & Drucken.cstRN)==Drucken.cstRechts1 || (iABits & Drucken.cstRN)==Drucken.cstRechts2)
                  bRechts=true;
                checkY2();
                //if (sATitel == null)
                //  checkY();
                //g.printInfo("--- vor printTitel:"+bRechts+"/"+pm.iCurY);
                printTitel(false,false,false,true);
                if (pm.iCurY<(int)pm.getIY()+5)
                  pm.iCurY=(int)pm.getIY()+5;
                if (sATitel == null && (iABits&Drucken.cstRN)!=Drucken.cstNeben)
                  checkY();
                /*
                if((pm.iCurY+2*iHSpace+iTitelHeight+iBezHeight+iZwSummenHeight+10)>(pm.getIY()+pm.getIHeight()))
		{
			pm.newPage();
			if(sTitelAbf!=null&&!sTitelAbf.trim().equals(""))
				setTitle(sTitelAbf,iAlignStammTitel);
		}
                boolean bTitelStamm=sTitelStamm!=null && !sTitelStamm.trim().equals("");
                if ((iABits & Drucken.cstKeinAbstand)==0)
                    pm.iCurY+=10;
		if(bTitelStamm)
			setTitle(sTitelStamm,iAlignAbschnittTitel);
                */

		int iY=pm.iCurY==0?(int)pm.getIY()+5:pm.iCurY+iHSpace;
		//int iX=pm.iCurX==0?(int)pm.getIX():pm.iCurX;
		int iX=(int)pm.getIX(bRechts);
		//int iXStart=iX;
		int iTabWidth=0;
		int iYSave=iY;
                boolean bNurSumme=(iDFBits&Drucken.cstNurSumme)>0 || (iABits & Drucken.cstNurSummeA)>0;
                //TabBreite.showGrid("Widths");
                Vector<Integer> VecWidths=getColumnWidths(TabDaten,foSpalte,foBez,foZwSummen,foSummen,TabBreite,bNurSumme,bSpaltenAbstand,(iDFBits & (Drucken.cstMEMO2+Drucken.cstPb)) > Drucken.cstPb,(iBits&Drucken.cstDoppelt)>0);
                //bSpaltenAbstand=false;
                //g.fixtestInfo("VecWidths="+VecWidths);
		if (g.bInfoDruck)
                {
                  //TabBreite.showGrid("Widths");
                  g.fixInfo("VecWidths="+VecWidths);
                  //new Tabellenspeicher(g,TabDaten).showGrid("TabDaten2");
                }
                //g.fixInfo("1");
                int iSpalten=TabDaten.getAnzahlSpalten() - (bCol ? 3:2);
                if ((iABits&Drucken.cstArt)!=Drucken.cstFixPos)
                {
                  for(int i = 0; i < iSpalten; i++)
                    iTabWidth += Tabellenspeicher.geti(VecWidths.elementAt(i));
                  double dDruckBreite=pm.getIWidth(bRechts);
                  double dPlus=0;
                  if ((iABits&Drucken.cstBlocksatz)>0 && dDruckBreite>iTabWidth)
                    for(int i = 0; i < VecWidths.size(); i++)
                    {
                      double d=(dDruckBreite - iTabWidth) / VecWidths.size()+dPlus;
                      int iP=(int)Math.round(d);
                      dPlus=d-iP;
                      VecWidths.setElementAt(new Integer(Sort.geti(VecWidths.elementAt(i)) + iP), i);
                    }
                  pm.dFaktor = dDruckBreite > iTabWidth ? 1.0 : dDruckBreite / iTabWidth;
                  //g.progInfo("Breite:"+iTabWidth+"/"+pm.getIWidth()+"->"+(pm.dFaktor));
                  if(pm.dFaktor < 1.0) {
                    for(int i = 0; i < VecWidths.size(); i++)
                      VecWidths.setElementAt(new Integer((int)Math.floor(Sort.geti(VecWidths.elementAt(i)) * pm.dFaktor)), i);
                  }
                }
                else
                  pm.dFaktor=1.0;
                //g.fixInfo("2");
		//int iWriteableHeight=(int)pm.getIHeight()-10-(iY-(int)pm.getIY());  //Berechnet beschreibbare Hoehe der Seite (-10 zur Sicherheit)
                //int iWriteableHeight=(int)pm.getIHeight()-pm.iCurY+(int)pm.getIY()+40;
                //g.progInfo("iY="+iY+", pm.iCurY="+pm.iCurY+", pm.getIY="+pm.getIY()+",iWriteableHeight="+iWriteableHeight);
		//int iRightSites=0;         		//Zählt mit bei Seiten nach rechts
		//0int iSpHeight=0;           		//Rechnet die Spaltenhoehe mit
		int iTabPos=0;             		//Zum Speichern der Position in TabDaten
		TabDaten.moveFirst();
                boolean bDirekt= (iABits&Drucken.cstArt)==Drucken.cstHtmlDirekt;
                pm.bHtmlOk= pm.bHtml && bDirekt;
                //g.fixInfo("----------------- bHtmlOk="+pm.bHtmlOk);
		//if(iBezHeight+iSpalteHeight>=iWriteableHeight)
		//{
		//	pm.newPage();
		//	iY=iYSave=(int)pm.getIY()+5;
		//	iWriteableHeight=(int)pm.getIHeight();//-(iY-(int)pm.getIY());
		//}
                //if (g.Prog())
                //  TabDaten.showGrid();
                if (g.bInfoDruck)
                  new Tabellenspeicher(g,TabDaten).showGrid(sATitel+"2");
                //int iSpalten=TabDaten.getAnzahlSpalten()-1;
                //g.fixInfo("3");
                //int iAnzahl=0;
		while(!TabDaten.eof())// && iAnzahl<300)
		{
                  //iAnzahl++;
                  //g.fixInfo("Zeile "+iAnzahl+","+TabDaten.fehler()+","+iSpalten);
			//System.out.println("org"+TabDaten.getPos());
			
            for(int i=1;i<=iSpalten;i++)
			{

				//System.out.println(">"+i);
				TabDaten.setPos(iTabPos);
                                // 14.6. ausgeblendet
				/*if(iX+TabWidths.getI(""+i)>pm.getIX()+pm.getIWidth())              //Für Seiten nach rechts
				{
                                  boolean b=pm.curPage()==pm.countPages();
                                  printTitel(b,true,true,true);
                                  if (!b)
                                    pm.nextPage();
					//System.out.println("Raender ueberschritten!!!->CountP:"+pm.countPages()+"/CurP:"+pm.curPage());
                                        /*
					boolean bTitelAbf=sTitelAbf!=null&&!sTitelAbf.trim().equals("");
					int iYHilfe=iY;
					if(pm.curPage()<pm.countPages())
					{
						pm.nextPage();
						if(bTitelStamm)
						{
							iY-=(2*iHSpace+iTitelHeight);
							pm.iCurY=iY;
							setTitle(sTitelStamm,iAlignAbschnittTitel);
							iY=iYHilfe;
						}
					}
					else
					{
						pm.newPage();

						//if(bTitelAbf)
						//{
						//	iY-=iHSpace-iTitelHeight;
						//}
						//if(bTitelStamm)
						//{
						//	iY-=iHSpace-iTitelHeight;
						//}

						if(bTitelAbf)
							setTitle(sTitelAbf,iAlignStammTitel);
						if(bTitelStamm)
							setTitle(sTitelStamm,iAlignAbschnittTitel);
						if(bTitelAbf||bTitelStamm)
							iY=iYHilfe;



					}
                                   */
				/*	iRightSites++; // 14.6. ausgeblendet
					iX=(int)pm.getIX();

				}*/
				//0iSpHeight=0;
				pm.setFont(foBez);
				pm.setColor(coBez);
				pm.setBGColor(coBGBez);

                                // Spalten-Bezeichnungs-Zeile ausgeben
				TabDaten.push();
				TabDaten.moveFirst();
                                String s=TabDaten.getS(""+i);
                                String sLeer=TabDaten.getS("1").indexOf("\n")>-1 ?" \n ":" ";
                                if (!s.equals(""))
                                {
                                  char c=s.charAt(0);
                                  if (c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5')
                                    s=s.substring(1);
                                }
                                else
                                  s=sLeer;
				//pm.drawString(s,iX,iY,(bVerLines?PrintManagerA.LLINE+PrintManagerA.RLINE:0)+(bHorLines?PrintManagerA.BLINE+PrintManagerA.ULINE:0),TabWidths.getI(""+i));

                                // Druck Spalten-Überschrift
                                int iVert=(bVerLines && i>1 || bRahmen && i==1 ? PrintManagerA.LLINE:0)+
                                            (bRahmen && i==iSpalten ? PrintManagerA.RLINE:0)+
                                            ((iBits&Drucken.cstDoppelt)>0 ? PrintManagerA.DH:0);
                                int iWidth=Tabellenspeicher.geti(VecWidths.elementAt(i-1));
                                if (iWidth<4) iWidth=0;
                                if ((lBits & Abfrage.cstKeinTitel)==0 && ((iABits&Drucken.cstArt)!=Drucken.cstFixPos) && (bErsteZeile || (iABits&Drucken.cstBedST)==0))
                                {
                                  char c1=TabDaten.getS(""+i).equals("")?'0':TabDaten.getS(""+i).charAt(0);
                                  int iAlign=c1=='1' || c1=='4' ? PrintManagerA.CENTER:c1=='2' || c1=='5' ? PrintManagerA.RIGHT:PrintManagerA.LEFT;
                                  if (bFarbe && TabFarbe!=null)
                                    pm.setBGColor(TabFarbe.posInhalt(new Integer(-1),new Integer(i)) ? new Color(TabFarbe.getI("Farbe")):coBGBez/*.brighter()*/);
                                  pm.drawSpalte(iWidth>0 ?s:sLeer,iX,iY,iAlign/*PrintManagerA.CENTER*/,iVert+(bRahmen?PrintManagerA.BLINE:0)+(bTitellinie||bRahmenTitel?PrintManagerA.TLINE:0),iWidth,bSpaltenAbstand,false);
                                  //pm.iCurY+=3;
                                }
                                else
                                  pm.iCurY=iY;
                                if (i==1)
                                  iX0=iX;
                                if (bRahmenTitel && i==iSpalten)
                                {
                                  //int iW=0;
                                  //for(int i2=1;i2<TabDaten.getAnzahlSpalten()-1;i2++)
                                  //  iW+=TabWidths.getI(""+i2);
                                  pm.drawRect(iX0, iY, iWidth+iX-iX0, pm.iCurY - iY-1);
                                  //pm.iCurY++;
                                }
                                //g.progInfo("Überschrift"+i+":"+iX+"/"+iY+"/"+TabWidths.getI(""+i)+"/"+pm.iCurY);
                                TabDaten.pop();

				pm.setColor(coSpalte);
				//0iSpHeight+=iBezHeight;
				//if(i==1)
				//	System.out.println("iSpHeight:"+iSpHeight);
				iY=pm.iCurY;
                                TabDaten.moveNext();
				boolean bRun=true;
				boolean bBreak=false;

				while(bRun)
				{
					//g.fixtestError("bDirekt="+bDirekt+", bHtml="+pm.bHtmlOk+", Pos="+TabDaten.getPos());			
					bBreak=false;
					if (pm.bHtmlOk && TabDaten.getPos()>1)
					{
						//bBreak=true;
						newPage();				
					}
					                    Object ObjArt=TabDaten.getInhalt("0");
					String sArt=ObjArt instanceof String ? TabDaten.getS("0"):"";
                    int iZeile=ObjArt instanceof Integer ? Integer.parseInt(""+ObjArt):0;
                    Object ObjD=TabDaten.getInhalt(""+i);
                    Image Img=ObjD instanceof Image ? (Image)ObjD:null;
                    boolean bBarcode= ObjD instanceof Barcode;
                    String sBC= bBarcode ? ((Barcode)ObjD).toString():null;
					String sSpalte=ObjD instanceof String ?(String)ObjD:null;
//					g.fixtestError("I/B/S="+(Img!=null)+"/"+bBarcode+"/"+sSpalte);
                                        //if (sSpalte == null && Img==null)
                                        //  g.progInfo("Datensatz:"+TabDaten.getPos()+"/"+i+" ist "+Static.className(ObjD));
					if(sSpalte==null || sSpalte.equals("") || sSpalte.equals("null") || iWidth==0)
						sSpalte=" ";
                    Color col=bCol && !TabDaten.out() && !TabDaten.isNull("color") ? (Color)TabDaten.getInhalt("color"):null;
                    boolean bLine=TabDaten.getS("LINES").equals("L");
                                        //if(!bLine)
					//	sSpalte=sSpalte+"*noline*";
                    boolean bZwSumme=sArt.equals("Z") || sArt.equals("H");
                    boolean bAusgabe=(bZwSumme && (iABits&Drucken.cstKeineSumme)==0 && (iDFBits&Drucken.cstKeineSumme)==0 ||
                                            sArt.equals("S") && (iABits&Drucken.cstGesamtsumme)>0 && (iDFBits&Drucken.cstKeineSumme)==0 ||
                                            sArt.equals("") && !bNurSumme);
					int iSpHeight=bAusgabe ? bZwSumme ? iZwSummenHeight:iSpalteHeight:0;
					//if(i==1)
					//	System.out.println("1:iSpHeight:"+iSpHeight+"/iWriteableHeight:"+iWriteableHeight);
					//if(iSpHeight>=iWriteableHeight)

                    if (iY+iSpHeight+5>pm.iFootY)
					{
						bBreak=true;
						sSpalte="";
					}

                                        //int iPos=TabDaten.getPos();
					if(!bBreak)
						TabDaten.moveNext();

					//while(!TabDaten.eof()&&TabDaten.getS("0").equals(sArt)&&!bBreak)//&&iSpHeight<iWriteableHeight)
                                        //{  // 21.11. Schleife entfernt
					/*	iSpHeight+=(sArt.startsWith("Z"))?iZwSummenHeight:iSpalteHeight;
						//if(i==1)
						//	System.out.println("2:iSpHeight:"+iSpHeight+"/iWriteableHeight:"+iWriteableHeight);
						if(iSpHeight>=iWriteableHeight)
						{
							bBreak=true;
							break;
						}
						String sZeile=TabDaten.getS(""+i);
						if(sZeile.equals(""))
							sZeile=" ";
						sSpalte+="\n"+sZeile+(!TabDaten.getS("LINES").equals("L")?"*noline*":"");
						TabDaten.moveNext();*/
					//}
					//System.out.println(">>"+i);
                                        boolean bGesSumme=sArt.equals("S");//|| Abf != null && (Abf.getBits2(i) & Abfrage.cstFett)>0;
                                        //System.out.println(bZwSumme ? "ZwSumme":bGesSumme ? "GesSumme":"Normal");
                                        pm.setFont(bZwSumme?foZwSummen:bGesSumme?foSummen:sSpalte.startsWith("-")?foNegativ:foSpalte);
                                        //0iSpHeight+=bAusgabe ? sArt.startsWith("Z") ? 3:1:0;
                                        //if (bZwSumme)
                                        //  g.fixInfo("Zwischensumme:"+sSpalte);
                                        if (col != null)
                                          pm.setColor(col);
                                        else
                                          pm.setColor(bZwSumme?coZwSummen:bGesSumme?coSummen:sSpalte.startsWith("-")?coNegativ:coSpalte);
                                        pm.setBGColor(bZwSumme?coBGZwSummen:bGesSumme?coBGSummen:sSpalte.startsWith("-")?coBGNegativ:coBGSpalte);
                                        //if (bZwSumme)
                                        //  g.fixInfo("Nach setColor");
					/*if(sArt.startsWith("Z"))
						pm.setBGColor(coBGZwSummen);
					else
						pm.setBGColor(coBGSpalte);*/
					//if(sSpalte.endsWith("*noline*"))
					//	sSpalte=sSpalte.substring(0,sSpalte.length()-8);

                                        TabDaten.push();
                                        TabDaten.moveFirst();
                                        char c1=TabDaten.getS(""+i).equals("")?'0':TabDaten.getS(""+i).charAt(0);
                                        TabDaten.pop();
                                        int iAlign=c1=='1' || c1=='4' ? PrintManagerA.CENTER:c1=='2' || c1=='5' ? PrintManagerA.RIGHT:PrintManagerA.LEFT;
                                        if (bAusgabe)// && iWidth>0)
                                        {
                                          if (iWidth<1)
                                            sSpalte=" ";
                                          int iPlus=bRahmen && TabDaten.getPos()==2 && (lBits & Abfrage.cstKeinTitel)>0 ? PrintManagerA.BLINE:0;
                                          //g.progInfo(bRahmen+"/"+TabDaten.getPos()+"/"+(lBits & Abfrage.cstKeinTitel)+"->"+iPlus);
                                          if ((iABits&Drucken.cstArt)==Drucken.cstFixPos)
                                          {
                                            iX=(int)(Abf.getX(i) * 72 / 25.4);
                                            iY=(int)(Abf.getY(i) * 72 / 25.4);
                                          }
                                          /*else if (bSpaltenAbstand)
                                          {
                                            iX+=4;
                                            iWidth-=4;
                                          }*/

                                          if(bZwSumme) // Zwischensumme
                                            pm.drawSpalte(sSpalte,iX,iY,iAlign,iVert+(bZwOben && !bNurSumme?PrintManagerA.BLINE:iPlus)+(bZwUnten && !bNurSumme?PrintManagerA.DLINE:bHorLines && bLine?PrintManagerA.ULINE:0),iWidth,bSpaltenAbstand,false);
                                          else if (sArt.equals("S"))//Summe
                                            pm.drawSpalte(sSpalte,iX,iY,iAlign,iVert+(bSummeOben?PrintManagerA.BLINE:iPlus)+(bSummeUnten?PrintManagerA.DLINE:bHorLines && bLine?PrintManagerA.ULINE:0),iWidth,bSpaltenAbstand,false);
                                          else if (sArt.equals("")) // Einzelspalte
                                          {
                                            if (bFarbe)
                                              pm.setBGColor(TabFarbe.posInhalt(new Integer(iZeile),new Integer(i)) ? new Color(TabFarbe.getI("Farbe")):null/*.brighter()*/);
                                            //g.progInfo(sSpalte+":"+iPos+"/"+i);
                                            if ((iABits&Drucken.cstStrecken)>0)
                                              pm.drawStreck(sSpalte,Abf.getX(i),Abf.getY(i),dZahl==0 ? 5.05:dZahl,iAlign, iWidth);
                                            else if (iX>0 || iY>0)
                                              pm.drawSpalte(sSpalte, iX, iY, iAlign, iVert + iPlus + (bHorLines && bLine || bRahmen && TabDaten.eof() ? PrintManagerA.ULINE : 0), iWidth,bSpaltenAbstand,false);
                                            if (bFarbe)
                                              pm.setBGColor(null);
                                            //g.progInfo(sSpalte+":"+iVert+"/"+iPlus+"/"+TabDaten.isLast());
                                          }
                                          if (Img !=null || bBarcode)
                                          {
                                            int iOld=pm.iCurY;
                                            if (bBarcode)
                                            {
                                            	if (!Static.bDefShow) 
                                            		pm.setFont(g.fontBarcode);
                                            	boolean bDZH=(iBits&Drucken.cstDoppelt)>0;
                                            	pm.drawSpalte(sBC, iX+2, iY-(bDZH ? 12:1), iAlign, bDZH ? PrintManagerA.DH:0, iWidth,bSpaltenAbstand,true);
//                                            	g.fixtestError("Barcode: "+sBC);
                                            }
                                            else
                                            {
	                                            javax.swing.ImageIcon Img2=new javax.swing.ImageIcon(Img);
	                                            //g.progInfo("Image-Größe:"+Img2.getIconWidth()+"x"+Img2.getIconHeight());;
	                                            int iF= Img2.getIconHeight()/50+1;
	                                            //g.progInfo("Image-auf Größe:"+pm.stringHeight()*iF+"x"+iWidth);
	                                            pm.drawImage(Img,iX,iY,iWidth,pm.stringHeight()*iF,iAlign);
                                            }
                                            pm.iCurY=iOld;
                                          }
                                        }
                                        //g.testInfo(iX+"/"+iY+"/"+sArt+":"+sSpalte);
					//if ((iBits&Drucken.cstDoppelt)>0)
                                        //  pm.iCurY+=pm.stringHeight();
                                        if (sArt.equals("H"))
                                          pm.iCurY+=5;
                                        iY=pm.iCurY;
                                        //g.progInfo("iY="+iY);
					if(TabDaten.eof()||bBreak)
					{
						bRun=false;
					}


				}

				iY=iYSave;
				iX+=iWidth;
				if(i<iSpalten)
				{}	//TabDaten.moveFirst();
				else
				{
					if(bBreak)
					{

						//System.out.println("newPage");

						//pm.newP();
						iTabPos=TabDaten.getPos()-1;
						iX=(int)pm.getIX(bRechts);
						//iYSave=(int)pm.getIY()+5;
                                                printTitel(true,true,true,true);
                                                //g.progInfo("------ bBreak:"+iY+"/"+iYSave+"/"+pm.iCurY+"/"+pm.getIY()+"+"+iHSpace);
                                                iY=iYSave=(pm.iCurY>0 ? pm.iCurY:(int)pm.getIY())+iHSpace;
                                                //iY=iYSave=pm.iCurY+iHSpace;

                                                //iWriteableHeight=(int)pm.getIHeight();//-(iY-(int)pm.getIY());
                                                /*
						boolean bTitelAbf=sTitelAbf!=null&&!sTitelAbf.trim().equals("");
						if(bTitelAbf)
							setTitle(sTitelAbf,iAlignStammTitel);
						if(bTitelStamm)
							setTitle(sTitelStamm,iAlignAbschnittTitel);
						iWriteableHeight=(int)pm.getIHeight()-10;//-(iY-(int)pm.getIY());
						if(bTitelAbf||bTitelStamm)
						{
							iY=iYSave=pm.iCurY+iHSpace;
							iWriteableHeight-=(pm.iCurY-pm.getIY());
						}
						else*/
						//	iY=iYSave;


						//TabDaten.moveLast();
						//TabDaten.moveNext();
						//System.out.println("Umbruch!");
						//iRightSites=0;
					}
					else
					{
						//System.out.println(":)>Ende/"+iSpHeight+"/iH:"+iWriteableHeight);
					}
				}



				//iX+=TabWidths.getI(""+i);

			}
                        //System.out.println("iRightSites:"+iRightSites);
                        //iTabPos++;
			//System.out.println("---->ENDE!!!!!!!");
		}
                //g.fixInfo("4");
		/*for(int j=0;j<iRightSites;j++)
			pm.previousPage();*/
                if (!bSU && iYvor>pm.iCurY)
                  pm.iCurY=iYvor;
                //g.printInfo("addOutliner2:"+sSTitel+":"+pm.iCurY+"/"+bRechts);
                bRechts=false;
                iRN0=iABits & Drucken.cstRN;
                pm.bHtmlOk=false;
		//pm.startPrint();
		//System.out.println("open:"+outliner.getNumNodes()+"/Level:"+iLevel);
                //g.clockInfo("DruckHS - "+sDTitel+"/"+sATitel,lClock);
	}

        public double Pause(double d,boolean bSchicht,Tabellenspeicher TabZeitart,Vector VecStt)
        {
          if (bSchicht)
            bSchicht=TabZeitart.getB("prod") || VecStt.size()>1 && TabZeitart.getI("aic_Stammtyp")==Sort.geti(VecStt,1);
          int iPause=!bSchicht || TabZeitart.isNull("TM_Pause") ? 1800:(int)(TabZeitart.getF("TM_Pause")*60);
          if (iPause<300)
            iPause*=60;
          int iAb=!bSchicht || TabZeitart.isNull("TM_ab") ? 6*3600:(int)(TabZeitart.getF("TM_ab")*3600);
          //g.fixInfo("Pause: bSchicht="+bSchicht+", iPause="+iPause+", iAb="+(iAb/3600)+" ,d="+(d/3600));
          if (bSchicht && d>iAb+29)
            d-=iPause;
          return d;
        }

        @SuppressWarnings("unchecked")
	public Tabellenspeicher addPlanung(Tabellenspeicher TabStamm,Tabellenspeicher TabDaten,Tabellenspeicher TabSp,boolean bMenge,int iABits,int iDFBits,String sAbt)//,int iZeilen)
        {
          //Tabellenspeicher TabSp=A.getSpalten();
          boolean bSumme=(iDFBits & Drucken.cstKeineSumme) == 0;
          boolean bPb=(iDFBits & Drucken.cstPb) > 0;
          if ((iABits&Drucken.cstKeineSumme)>0)
            bSumme=false;
          boolean bGesSumme=bSumme && (iABits&Drucken.cstGesamtsumme)>0;
          boolean bSchicht=(iABits&Drucken.cstArt)==Drucken.cstSchicht2 || (iABits&Drucken.cstArt)==Drucken.cstSchichtTMZ; //(iABits&Drucken.cstSchicht2)>0;
          boolean bSchichtTMZ=(iABits&Drucken.cstArt)==Drucken.cstSchichtTMZ || bPb && (iDFBits & Drucken.cstTMZ) > 0;
          //if (bSchichtTMZ && (iABits&Drucken.cstGesamtsumme)==0)
          //  iABits+=Drucken.cstGesamtsumme;
          boolean bAZ=bPb && (iDFBits & Drucken.cstPAZ) > 0;
          boolean bPPS=bPb && (iDFBits & Drucken.cstPPS) > 0;
          boolean bIst=bPb && (iDFBits & Drucken.cstIST) > 0;
          boolean bSoll=bPb && (iDFBits & Drucken.cstSOLL) > 0;
          boolean bDiff=bPb && (iDFBits & Drucken.cstDIFF) > 0;
          boolean bPause=bSchicht && !bPb || bPb && (iDFBits & Drucken.cstPAUSE) > 0;
          g.fixtestInfo("Pause "+(bPause?"abziehen":"ignorieren"));
          boolean bMemo2=bPb && (iDFBits & Drucken.cstMEMO2) > 0;
          boolean bVB=bSchicht && !bPb || bPb && (iDFBits & Drucken.cstVB) > 0;
          //boolean bAIO=bPb && (iDFBits & Drucken.cstAIO) > 0;
          //g.progInfo("addPlanung: TMZ="+bSchichtTMZ+", AZ="+bAZ+", Ist="+bIst+", Soll="+bSoll+", Diff="+bDiff+" Pause="+bPause+" Memo2="+bMemo2);
          Tabellenspeicher TabZeitart=null;
          if (sAbt==null || TabFarbe==null)
          {
            TabFarbe = new Tabellenspeicher(g, new String[] {"Zeile", "Spalte", "Farbe"});
            iZeileP=1;
          }
          TabFarbe.sGruppe="Zeile";
          TabFarbe.sAIC="Spalte";
          String sMA="";
          String sMA2="";
          String sZA="";
          String sZA2=null;
          String sZA3=null;
          String sText=null;
          String sSaldo=null;
          String sDatum="";
          String sMass="";
          String sVonBis=null;
          String sVonBis2=null;
          String sBool3=null;
          String sFarbe=null;
          boolean bDtDatum=false;
          boolean bMass=false;
          boolean bStunde=false;
          //boolean bVonBis=false;
          int iMass=0;
          int iMass2=0;
          int iStellen=2;
          int iDtStamm=0;
          Vector VecStt=new Vector();
		for(TabSp.moveFirst();!TabSp.eof();TabSp.moveNext())
		{
                  String sDatentyp=TabSp.getS("datentyp");
			if(sDatentyp.equals("BewStamm"))
			{
				if(iDtStamm==0)
                                {
                                  sMA = TabSp.getS("kennung");
                                  sMA2="v"+TabSp.getS("kennung2");
                                }
				else if(iDtStamm==1)
					sZA="v"+TabSp.getS("kennung2");
                                else if(iDtStamm==2)
					sZA2="v"+TabSp.getS("kennung2");
                                else if(iDtStamm==3)
					sZA3="v"+TabSp.getS("kennung2");
                                if (iDtStamm>0)
                                  VecStt.addElement(new Integer(TabSp.getI("STT")));
				iDtStamm++;
			}
			else if(sDatentyp.equals("BewDatum")&&!bDtDatum)
			{
				sDatum=TabSp.getS("kennung");
				bDtDatum=true;
			}
                        else if(sDatentyp.equals("BewBool3")&& sBool3==null)
                          sBool3="v"+TabSp.getS("kennung").substring(1);
                        else if(sDatentyp.equals("Farbe")&& sFarbe==null)
                          sFarbe=TabSp.getS("kennung");
                        else if(!bMass && (sDatentyp.equals("BewMass") || sDatentyp.equals("BewVon_Bis")))
                        {
                          if (sVonBis==null)
                          {
                            sMass = (sDatentyp.equals("BewVon_Bis") ? "d" : "") + TabSp.getS("kennung");
                            bMass = !bSchicht;
                            bStunde = TabSp.getI("Stamm") == g.iAicStunde;
                          }
                          else
                            bMass=true;
                          if ((sVonBis==null || bSchicht) && sDatentyp.equals("BewVon_Bis"))
                          {
                            //bVonBis=true;
                            if (sVonBis==null)
                            {
                              sVonBis = TabSp.getS("kennung2");
                              iMass = TabSp.getI("Stamm");
                              if (iMass > 0)
                                iStellen = Abfrage.getLaenge(TabSp);
                            }
                            else
                              sVonBis2 = TabSp.getS("kennung2");
                            //g.progInfo("---------------- iMass="+iMass+", sVonBis="+sVonBis+", sVonBis2="+sVonBis2);
                          }
                          else
                            iMass2=TabSp.getI("Stamm");
                        }
                        else if (sDatentyp.equals("Text"))
                        {
                          sText=TabSp.getS("kennung");
                        }
                        else if (sDatentyp.equals("Double"))
                        {
                          sSaldo=TabSp.getS("kennung");
                        }
		}
                if (sVonBis!=null)
                  bMass=true;
                g.progInfo("VecStt="+VecStt+", iDtStamm="+iDtStamm);
                if(bDtDatum && iDtStamm>1 && TabZeitart==null)
                {
                        int iEig=iDtStamm>2 ? 0:g.TabEigenschaften.getAic("INDICATE IN INDEX");
                        String s=/*(iEig>0?*/"select * from ("+//:"")+
                        "select aic_stamm"+g.AU_Bezeichnung2("Stamm","v.aic_stamm","v.Bezeichnung")+" Bezeichnung,kennung"+//,(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("GRANTED")+") bewilligt"+
                        ",(select Spalte_StringX from Daten_StringX d join poolview p on d.aic_daten=p.aic_daten where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("SHORTCUT_3")+") KZ"+
                        (sBool3==null?"":",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("GRANTED")+") bewilligt")+
                        ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("PRODUCTIVE ACTIVITY")+") prod"+
                        ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("PAYED ACTIVITY")+") pay"+
                         (bSchicht ? ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("MIN_PAUSE")+") TM_Pause"+
                         ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("TIME BEFOR BREAK")+") TM_ab"+
                         ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("PZE_CONTROLLING")+") cntr":"")+
                         (bSchichtTMZ ? ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("TM_OPEN")+") TM_open"+
                         ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("TM_CORE")+") TM_core"+
                         ",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("TM_CLOSE")+") TM_close":"")+

                        //",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.iEigFarbe+") Farbe"+
                        //(iDtStamm>2?",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+g.TabEigenschaften.getAic("DAYDEBIT")+") soll":"")+
                        (iEig>0?",(select spalte_double from poolview where aic_stamm=v.aic_stamm and aic_eigenschaft="+iEig+") ok":"")+
                        " ,aic_stammtyp from stammview2 v where aic_stammtyp"+Static.SQL_in(VecStt)+g.getReadMandanten(false,"v")+") x "+(bSchicht?"":"where"+(iEig>0?" ok=1 and":"")+" kz is not null")+
                        " order by Bezeichnung";
                        TabZeitart = new Tabellenspeicher(g,s,true);
                        //g.progInfo(s);
                        //TabZeitart.showGrid("TabZeitart");
                        //TabSp.moveLast();
                }

                if (TabZeitart==null)
                  Static.printError("DruckHS.addPlanung: BewDatum-Spalte oder BewStamm-Spalten fehlen");
                if (!bMass)
                  g.testInfo("DruckHS.addPlanung: BewMass-Spalte fehlt");
          Tabellenspeicher TabEinAus=new Tabellenspeicher(g,"select aic_stamm,eintritt,austritt from stammview2 where aic_stamm"+Static.SQL_in(TabDaten.getVecSpalte(sMA2)),true);
          //TabEinAus.showGrid();
          boolean bAic=(iABits&Drucken.cstLtAic)>0;
          Vector<String> Vec=new Vector<String>();
          if (bAic)
        	  Vec.addElement("Aic");
          Vec.addElement("Name");
          Vec.addElement("ZR");
          boolean bWoche=g.getBis().getTime()-g.getVon().getTime()<=DateWOD.WOCHE+DateWOD.STUNDE;
          for(int i=1;i<(bWoche?8:32);i++)
            Vec.addElement(""+i);
          if (sZA2==null)
          {
            Vector<String> VecKZ=new Vector(TabZeitart.getVecSpalte(Static.bTranslate ?"Bezeichnung":"KZ"));
            for(int i = 0; i < VecKZ.size(); i++)
              Vec.addElement(Static.bTranslate ? (""+VecKZ.elementAt(i)).substring(0,3):VecKZ.elementAt(i));
          }
          String sSum=g.getBegriffS("Show","Summe");
          String sGesamt= sAbt!=null ? sAbt:g.getBegriffS("Show","Gesamtsumme");
          String sSoll=g.getBegriffS("Checkbox","soll");
          String sIst=g.getBegriffS("Checkbox","ist");
          String sAZ=Static.cutString(g.getBegriffS("Checkbox","AZ"));
          String sPPS=Static.cutString(g.getBegriffS("Checkbox","PPS"));
          String sDiff=Static.cutString(g.getBegriffS("Checkbox","Diff"));
          int iVpos=0;
          if (bPb && bSchicht)
          {
            iVpos=Vec.size();
            if (bAZ) Vec.addElement(sAZ);
            if (bPPS) Vec.addElement(sPPS);
            if (bIst) Vec.addElement(sIst);
            if (bSoll) Vec.addElement(sSoll);
            if (bDiff) Vec.addElement(sDiff);
          }
          else if (sSaldo != null)
          {
            Vec.addElement(sIst);
            Vec.addElement(sSoll);
            Vec.addElement(sDiff);
          }
          else if (bSumme)
            Vec.addElement(sSum);
          Vector<TMZ> VecTMZ=new Vector<TMZ>();
          for (int i=1;i<(iVpos>0?iVpos:Vec.size());i++)
            VecTMZ.addElement(new TMZ(bSchichtTMZ,g.bH_min,g.bH_min ? "hm" : iStellen == 2 ? "0.00" : "0.0"));
          //TMZ tm[]=new TMZ()[Vec.size()];
          //TMZ tm[]=new TMZ(bSchichtTMZ,Static.bH_min,Static.bH_min ? "hm" : iStellen == 2 ? "0.00" : "0.0")[Vec.size()];
          /*double dx[]=new double[Vec.size()];
          int ix[]=new int[Vec.size()];
          int iOpen[]=new int[Vec.size()];
          int iCore[]=new int[Vec.size()];
          int iClose[]=new int[Vec.size()];*/
          Tabellenspeicher Tab=new Tabellenspeicher(g,Vec);
          Tab.sGruppe=bAic ? "Aic":"Name";
          Tab.sAIC="ZR";
          String sFormat=bWoche ?"ww":"yyyy-MM";
          String sSo=g.getBegriffS("Show","So");
          String sFt=g.getBegriffS("Show","Ft");
          //TabStamm.showGrid("Stamm");
          if (bMenge)// && !bWoche)
            for(TabStamm.moveFirst();!TabStamm.eof();TabStamm.moveNext())
            {
              DateWOD dtVon=new DateWOD(g.getVon());
              Tab.newLine();
              int iStamm=TabStamm.getI("aic");
              if (bAic)
            	  Tab.setInhalt("Aic",iStamm);
              Tab.setInhalt("Name",TabStamm.getS("Bezeichnung"));
              Tab.setInhalt("ZR",dtVon.Format(sFormat));
              int iM=bWoche ? dtVon.get(DateWOD.WEEK_OF_YEAR):dtVon.getMonth();
              while (iM==(bWoche ? dtVon.get(DateWOD.WEEK_OF_YEAR):dtVon.getMonth()))
              {
                Tab.setInhalt(""+(bWoche ? (dtVon.getDay()+5)%7+1:dtVon.getDate()),dtVon.getDay()==Calendar.SUNDAY?sSo:!g.Feiertag(dtVon,iStamm).equals("")?sFt:" ");
                dtVon.tomorrow();
                //d=dtVon.getAbsSeconds();
              }
            }
          //TabDaten.showGrid("TabDaten");
          String sFZ=bStunde?g.bH_min?"hm":iStellen==2?"0.00":"0.0":bMass?"0.0":"0";
//          Tab.showGrid("Tab");
//          TabDaten.showGrid("TabDaten");
          for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
          {
            String sName=TabDaten.getS(sMA);
            int iStamm=TabDaten.getI(sMA2);
            if (bAic ? !Tab.posInhalt("Aic",iStamm):!Tab.posInhalt("Name",sName))
            {            
              TabEinAus.posInhalt("aic_stamm",iStamm);
              double dEin=TabEinAus.isNull("Eintritt")?0.5:TabEinAus.getF("Eintritt");
              double dAus=TabEinAus.isNull("Austritt")?0.5:TabEinAus.getF("Austritt");
              DateWOD dtVon=new DateWOD(g.getVon());
              //double dVon=dtVon.getAbsSeconds();
              //dtVon.setDate(1);
              double dBis=new DateWOD(g.getBis()).getAbsSeconds();
              double d=dtVon.getAbsSeconds();
              while(d<dBis)
              {
                Tab.newLine();
                Tab.setInhalt("Name",sName);
                Tab.setInhalt("ZR",dtVon.Format(sFormat));
                int iM=bWoche?dtVon.get(DateWOD.WEEK_OF_YEAR):dtVon.getMonth();
                while (iM==(bWoche?dtVon.get(DateWOD.WEEK_OF_YEAR):dtVon.getMonth()))
                {
                  if (!bWoche && (dEin==0.5 || d>=dEin) && (dAus==0.5 || d<=dAus))
                    Tab.setInhalt(""+dtVon.getDate(),dtVon.getDay()==Calendar.SUNDAY?sSo:!g.Feiertag(dtVon,iStamm).equals("")?sFt:" ");
                  dtVon.tomorrow();
                  d=dtVon.getAbsSeconds();
                }
              }
            }
            DateWOD dt=new DateWOD(TabDaten.getTimestamp(sDatum));
            double dF=bStunde && g.bH_min ? 3600.0:1.0;
            //g.fixInfo(TabDaten.getPos()+":"+sName+"/"+dt.Format(sFormat));
            if (Tab.posInhalt(bAic ? (Object)iStamm:(Object)sName,dt.Format(sFormat)))
            {
              if (!TabDaten.isNull(sZA) && TabZeitart.posInhalt("aic_stamm",TabDaten.getI(sZA)) ||
                  sZA2 != null && !TabDaten.isNull(sZA2) && TabZeitart.posInhalt("aic_stamm",TabDaten.getI(sZA2)))
              {
                int iSpalte=bWoche ? (dt.getDay()+5)%7+1:dt.getDate();
                //g.fixInfo(dt+"/"+TabZeitart.getS("Bezeichnung")+":"+iSpalte);
                boolean bBis2=sVonBis2!= null && !TabDaten.isNull("B"+sVonBis2);
                int iNB=sBool3!=null && TabZeitart.getB("bewilligt") ? g.getAuswahlNr(TabDaten.getI(sBool3)):1;
                Tab.setInhalt("" + iSpalte, (iNB==2 ? "x":iNB==0 ? "o":"")+
                              (!bVB || TabDaten.getI(sZA)!=0 || sVonBis==null || TabDaten.getF("D"+sVonBis)==0 ? Static.bTranslate && !bSchicht ? TabZeitart.getS("Bezeichnung").substring(0,3):TabZeitart.getS("KZ"):
                              ""+(iMass>0 ? new VonBis(TabDaten.getInhalt("V"+sVonBis),TabDaten.getInhalt("B"+(bBis2 ? sVonBis2:sVonBis)),
                                bBis2 ? TabDaten.getF("D"+sVonBis)+TabDaten.getF("D"+sVonBis2):Pause(TabDaten.getF("D"+sVonBis),bPause,TabZeitart,VecStt),"HH:mm",iStellen,g,iMass,true):
                        	new VonBis(g,TabDaten.getInhalt("V"+sVonBis),TabDaten.getInhalt("B"+sVonBis),"HH:mm")))+(sText==null || TabDaten.isNull(sText) ? "":(bMemo2?"\n":" ")+TabDaten.getS(sText)));
                if (iNB==1)
                {
                  Color Col = g.getColor(TabDaten.getI(bSchicht && sZA3 != null ? sZA3 : TabDaten.isNull(sZA) ? sZA2 : sZA),null);
                  //if (bNB)
                  //  Col = Col.brighter().brighter();
                  if (Col!= null)
                  {
                    TabFarbe.addInhalt("Zeile", Tab.getPos()+iZeileP);//Tab.getPos() + iZeilen);
                    TabFarbe.addInhalt("Spalte", iSpalte + 1);
                    TabFarbe.addInhalt("Farbe", Col.getRGB());
                  }
                  if (sFarbe!=null)
                  {
                    Col=TabDaten.isNull(sFarbe) ? null:new Color(TabDaten.getI(sFarbe));
                    if (Col!=null)
                    {
                      TabFarbe.addInhalt("Zeile", Tab.getPos()+iZeileP);//Tab.getPos() + iZeilen);
                      TabFarbe.addInhalt("Spalte", 1);
                      TabFarbe.addInhalt("Farbe", Col.getRGB());
                    }
                  }
                }
                if (bSchicht)
                {
                  double d=Pause(TabDaten.getF("D"+sVonBis),bPause,TabZeitart,VecStt)/3600;
                  if (sVonBis2 != null)
                    d+=TabDaten.getF("D"+sVonBis2)/3600;
                  //g.progInfo("d="+d+"/"+TabDaten.getF("D"+sVonBis)+"/"+TabDaten.getF("D"+sVonBis2));
                  //if (d>=6 && (sMass.equals("") || TabDaten.isNull(sMass)))
                  //  d-=0.5;
                  if (d>0 && TabDaten.isNull(sZA))
                  {
                    if (bSchichtTMZ)
                      VecTMZ.elementAt(iSpalte).add(TabZeitart.getB("TM_open"),TabZeitart.getB("TM_core"),TabZeitart.getB("TM_close"));
                    else
                      VecTMZ.elementAt(iSpalte).add(d);
                    /*dx[iSpalte]+=d;
                    ix[iSpalte]+=1;
                    if (bSchichtTMZ)
                    {
                      if (TabZeitart.getB("TM_open"))
                        iOpen[iSpalte]+=1;
                      if (TabZeitart.getB("TM_core"))
                        iCore[iSpalte]+=1;
                      if (TabZeitart.getB("TM_close"))
                        iClose[iSpalte]+=1;
                    }*/
                  }
                }
                double dPlus=0; // Ist-Zeit
                double dPlus1=0;// PPS
                double dPlus2=0;// Arbeitszeit
                if (sZA2==null)
                {
//                  iStamm=TabDaten.getI(sMA2);
                  dPlus = bMass ? sMass.startsWith("d") ? TabDaten.getF(sMass) == 0 || !g.Feiertag(new DateWOD(TabDaten.getTimestamp(sDatum)),iStamm).equals("") ? 0 : iMass==0 ? 1:TabDaten.getF(sMass)/g.getFaktor(iMass) :
                    TabDaten.getF(sMass) / (iMass2>0 ? g.getFaktor(iMass2): 3600 * 24) : 1;
                  String sKZ=Static.bTranslate ? TabZeitart.getS("Bezeichnung").substring(0,3):TabZeitart.getS("KZ");
                  Tab.setInhalt(sKZ, new Zahl1((Tab.getF(sKZ)/dF + dPlus)*dF,sFZ));
                  boolean bProd=TabZeitart.getB("prod") || VecStt.size()>1 && TabZeitart.getI("aic_Stammtyp")==Sort.geti(VecStt,1);
                  boolean bCntr=TabZeitart.exists("cntr") && TabZeitart.getB("cntr");
                  dPlus2=bProd ? dPlus:0;
                  dPlus1=bProd || bCntr ? dPlus:0;
                  g.progInfo("dPlus:"+dPlus+"/"+bProd+" am "+TabDaten.getS(sDatum));
                }
                else
                {
                  dPlus = /*TabZeitart.getI("aic_stamm")==TabDaten.getI(sZA) ? TabZeitart.getF("Soll"):*/ Pause(TabDaten.getF(sMass), bPause, TabZeitart, VecStt) / 3600;
                  if (sVonBis2 != null)
                    dPlus+=TabDaten.getF("D"+sVonBis2)/3600;
                  boolean bProd=TabZeitart.getB("prod") || VecStt.size()>1 && TabZeitart.getI("aic_Stammtyp")==Sort.geti(VecStt,1);
                  boolean bPay=TabZeitart.getB("pay");
                  boolean bCntr=TabZeitart.exists("cntr") && TabZeitart.getB("cntr");
                  if (!bProd && !bPay)
                    dPlus=0;
                  dPlus2=bProd ? dPlus:0;
                  dPlus1=bProd || bCntr ? dPlus:0;
                  g.progInfo("dPlus2:"+dPlus2+"/"+TabZeitart.getS("Bezeichnung")+"/"+bProd+" für "+TabDaten.getS(sMA)+" am "+TabDaten.getS(sDatum));
                }
                //g.progInfo(Tab.getS("Name")+":"+dPlus);
                if (bPb && bSchicht)
                {
                  double dIst=bIst ? (Tab.getF(sIst)/dF + dPlus)*dF:0;
                  double d0=0.000001;
                  if (bAZ) Tab.setInhalt(sAZ,new Zahl1((Tab.getF(sAZ)/dF + dPlus2)*dF+d0,sFZ));
                  if (bPPS) Tab.setInhalt(sPPS,new Zahl1((Tab.getF(sPPS)/dF + dPlus1)*dF+d0,sFZ));
                  if (bIst) Tab.setInhalt(sIst,new Zahl1(dIst+d0,sFZ));
                  double dSoll=sSaldo!=null ? TabDaten.getF(sSaldo):0;
                  if (dSoll==0) dSoll=38.5*dF;
                  if (bSoll) Tab.setInhalt(sSoll,new Zahl1(dSoll+d0,sFZ));
                  if (bDiff) Tab.setInhalt(sDiff,new Zahl1(dIst-dSoll+d0,sFZ));
                }
                else if (sSaldo != null)
                {
                  double dSoll=TabDaten.getF(sSaldo);
                  if (dSoll==0)
                    dSoll=38.5*dF;
                  Tab.setInhalt(sSoll,new Zahl1(dSoll,sFZ));
                  double dIst=(Tab.getF(sIst)/dF + dPlus)*dF;
                  //g.fixInfo("Ist="+dIst+", Soll="+dSoll);
                  Tab.setInhalt(sIst,new Zahl1(dIst,sFZ));
                  Tab.setInhalt(sDiff,new Zahl1(dIst-dSoll,sFZ));
                }
                else if (bSumme)
                  Tab.setInhalt(sSum, new Zahl1((Tab.getF(sSum)/dF + dPlus)*dF,sFZ));
              }
            }
            else
              g.fixtestError("addPlanung: Stamm "+iStamm+"mit Date="+dt+" nicht gefunden");
          }
//          Tab.showGrid("Haupt");
          Vector Vec2=new Vector();
          int iP=bAic ? 1:0;
          for(int i=0;i<Vec.size()-iP;i++)
            Vec2.addElement(""+i);
          Vec2.addElement("LINES");
          Tabellenspeicher TabNeu=new Tabellenspeicher(g,Vec2);
          if (bGesSumme && TabGesSum==null)
          {
            TabGesSum = new Tabellenspeicher(g, Vec2);
            TabGesSum.newLine();
            TabGesSum.setInhalt("0","S");
            TabGesSum.setInhalt("1",g.getBegriffS("Show","Gesamtsumme"));
            for (int i=1+iP;i<VecTMZ.size();i++)
              TabGesSum.setInhalt(""+(i+1-iP),new TMZ(bSchichtTMZ,g.bH_min,g.bH_min ? "hm" : iStellen == 2 ? "0.00" : "0.0"));
          }
          TabNeu.newLine();
          TabNeu.setInhalt("0","U");
          TabNeu.setInhalt("1","0 "+(bMenge?"\n ":""));
          String s="";
          DateWOD dw=new DateWOD(g.getVon());
          for (int i = 2+iP; i < Vec.size(); i++)
          {
        	String sSp="" + (i-iP);
            if (bWoche)
            {
              if (i<9)
              {
                TabNeu.setInhalt(sSp, "1" + dw.Format("dd.MM.") + "\n" + dw.Format("EE"));
                dw.tomorrow();
              }
              else
                TabNeu.setInhalt(sSp, "1" + Vec.elementAt(i) + (((String)Vec.elementAt(i)).indexOf("\n")>0 ? "": "\n "));
            }
            else
            {
              if (bMenge)
                if (i>1+iP && i < dw.getActualMaximum(Calendar.DAY_OF_MONTH) + 2+iP)
                {
                  dw.setDate(i-1-iP);
                  s = "\n" + dw.Format("EE");
                }
                else
                  s = "\n ";
              TabNeu.setInhalt(sSp, "1" + Vec.elementAt(i) + s);
            }
          }
          if (bPb && bSchicht)
          {
            int iAnz=(bAZ ? 1:0)+(bPPS ? 1:0)+(bIst ? 1:0)+(bSoll ? 1:0)+(bDiff ? 1:0);
            for (int i=1;i<=iAnz;i++)
              TabNeu.setInhalt(""+(Vec.size()-i),"2"+Vec.elementAt(Vec.size()-i) + (((String)Vec.elementAt(Vec.size()-i)).indexOf("\n")>0 ? "": "\n "));
          }
          else if (sSaldo != null)
          {
            TabNeu.setInhalt(""+(Vec.size()-3),"2"+Vec.elementAt(Vec.size()-3)+" \n ");
            TabNeu.setInhalt(""+(Vec.size()-2),"2"+Vec.elementAt(Vec.size()-2)+" \n ");
            TabNeu.setInhalt(""+(Vec.size()-1),"2"+Vec.elementAt(Vec.size()-1));
          }
          else if (bSumme)
            TabNeu.setInhalt(""+(Vec.size()-1-iP),"2"+Vec.elementAt(Vec.size()-1)+(bMenge ? "\n ":""));
          for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            TabNeu.addInhalt("0",TabFarbe.size()>0 ? iZeileP/*Tab.getPos() + iZeilen*/:"");
            iZeileP++;
            //new SimpleDateFormat(Static.beiLeer(TabSpalten.getS("Format"),bMitFormat ? "dd.MM.yyyy":"yyyy/MM/dd")).parse(s,new ParsePosition(0));
            TabNeu.addInhalt("1",bMenge?Tab.getS("Name"):new DateWOD(new java.text.SimpleDateFormat(sFormat).parse(Tab.getS("ZR"),new java.text.ParsePosition(0))).Format("MMM yy"));
            for(int i=2+iP;i<Vec.size();i++)
              TabNeu.addInhalt(""+(i-iP),Tab.isNull(""+Vec.elementAt(i)) ?"-":Tab.getS(""+Vec.elementAt(i)));
            TabNeu.addInhalt("LINES","L");
          }
          TabNeu.newLine();
          TabNeu.setInhalt("0","S");
          TabNeu.setInhalt("1",sGesamt);
//          TabNeu.showGrid("Ergebnis1");
          
          if (bSchicht)
          {
            double d=0;
            //String sF=Static.bH_min ? "hm" : iStellen == 2 ? "0.00" : "0.0";
            for(int i=1;i<VecTMZ.size();i++)
            {
              //d+=dx[i];
              //if (dx[i] > 0)
              {
                TabNeu.setInhalt("" + (i + 1),""+VecTMZ.elementAt(i));
                //TabNeu.setInhalt("" + (i + 1), bSchichtTMZ ? iOpen[i] + " / " + iCore[i] + " / " + iClose[i] :
                //                 Static.rightString("" + ix[i], 6) + Static.rightString("" + new Zahl1(dx[i] * (Static.bH_min ? 3600. : 1), sF), 9 + iStellen));
                //if (TabGesSum != null)TabGesSum.setInhalt("" + (i + 1), "" + new Zahl1(dx[i] * (Static.bH_min ? 3600. : 1) + TabGesSum.getF("" + (i + 1)), sF));
                if (TabGesSum != null)
                {
                  Object Obj=TabGesSum.getInhalt("" + (i + 1));
                  if (Obj instanceof TMZ)
                    ((TMZ)TabGesSum.getInhalt("" + (i + 1))).add(VecTMZ.elementAt(i));
                  //else
                  //  TabGesSum.setInhalt("" + (i + 1), "" + new Zahl1(dx[i] * (Static.bH_min ? 3600. : 1) + TabGesSum.getF("" + (i + 1)), sF));
                }
              }
            }
            if (bSumme)
              if (!bPb)
                ;//!26.5.2014 entfernt! TabNeu.setInhalt("" + (dx.length-1),"" + new Zahl1(d*(Static.bH_min?3600.:1), Static.bH_min ? "hm" : iStellen == 2 ? "0.00" : "0.0"));
              else
                for(int i=iVpos;i<Vec.size();i++)
                {
                  TabNeu.setInhalt("" + i, "" + new Zahl1(TabNeu.sum("" + i, 1), sFZ));
                  //if (TabGesSum!=null) TabGesSum.setInhalt("" + i, "" + TabGesSum.getInhalt(""+i));
                  if (TabGesSum!=null) TabGesSum.setInhalt("" + i, new Zahl1(TabNeu.getF("" + i)+TabGesSum.getF(""+i), sFZ));
                }
          }
          //for(int i=2;i<33;i++)
          //  TabNeu.addInhalt(""+i,"");
          if (sZA2==null)
            for(int i=33+iP;i<Vec.size();i++)
              TabNeu.setInhalt(""+(i-iP),""+new Zahl1(Tab.sum(""+Vec.elementAt(i)),sFZ));
          //TabNeu.addInhalt("LINES","L");
          //TabZeitart.showGrid("Zeitart");
          //TabSp.showGrid("Spalten");
          //TabDaten.showGrid("Daten");
          //TabNeu.showGrid("Ergebnis");
          //if (g.bInfoDruck)
          //  TabFarbe.showGrid("Farbe");
          //if (TabGesSum!=null)
          //  TabGesSum.showGrid("Summe");
          return TabNeu;
        }

	@SuppressWarnings("unchecked")
	private Vector<Integer> getColumnWidths(Tabellenspeicher TabDaten,Font foSpalte,Font foBez,Font foZwSummen,Font foSummen,Tabellenspeicher TabBreite
                                                ,boolean bNurSumme,boolean bSpaltenAbstand,boolean bMemo2,boolean bDZH)
	{
          //g.fixInfo("getColumnWidths: bMemo2="+bMemo2);
          //Vector VecBreite=null;
		//System.out.println(
                boolean bCol=TabDaten.exists("color");
                int iSpalten=TabDaten.getAnzahlSpalten() - (bCol ? 3:2);
                String [] sSpalten=new String[iSpalten];
		for(int i=1;i<=iSpalten;i++)
			sSpalten[i-1]=""+i;
		//Tabellenspeicher TabWidths=new Tabellenspeicher(g,sSpalten);

		//TabWidths.newLine();
                Vector<Integer> Vec=new Vector<Integer>();
                //System.err.println("VecBreite="+VecBreite);
                Color Col=null;
		for(int i=1;i<=iSpalten;i++)
		{
//			g.fixtestError("getColumnWidths: Spalte "+i);
                  Vector [] vecSpalteU=new Vector[2];
                  //g.fixInfo("Spalte i="+i);
                  //if (VecBreite==null || VecBreite.size()<i || ((Integer)VecBreite.elementAt(i-1)).intValue()<=0)
                  //{
                    //if (VecBreite!=null && VecBreite.size()<i)
                    //  g.progInfo("VecBreite zu klein");

                    TabDaten.moveFirst();
                    vecSpalteU[0] = new Vector();
                    String s=TabDaten.getS("" + i);
                    int iPos=s.indexOf('\n');
                    vecSpalteU[0].addElement(iPos==-1?s.substring(1):s.substring(1,iPos));
                    vecSpalteU[0].addElement(foBez);
                    vecSpalteU[1] = new Vector();
                    vecSpalteU[1].addElement(iPos==-1?"":s.substring(iPos+1));
                    vecSpalteU[1].addElement(foBez);
                    int iWidthU=pm.getRowWidth(vecSpalteU, true);
                    Vector [] vecSpalte=new Vector[TabDaten.getAnzahlElemente(null)-1];
                    int iCount = 0;
                    boolean bBild100=false;
                    boolean bBC=false;
                    boolean bCR=false;
                    for (TabDaten.moveNext();!TabDaten.eof();TabDaten.moveNext())
                    {
                      vecSpalte[iCount] = new Vector();
                      boolean bBild=TabDaten.getInhalt("" + i) instanceof Image;
                      bBC=bBC || TabDaten.getInhalt("" + i) instanceof Barcode;
                      s= bBild ?" ":TabDaten.getM("" + i);
//                      g.fixtestError(i+"/"+TabDaten.getPos()+":"+bBild+"/"+bBC+"/"+s);
                      if (bBild)
                      {
                        javax.swing.ImageIcon Img=new javax.swing.ImageIcon((Image)TabDaten.getInhalt("" + i));
                        //g.progInfo("Image-Größe:"+Img.getIconWidth()+"x"+Img.getIconHeight());;
                        if (Img.getIconHeight()>49)
                          bBild100=true;
                      }
                      //else if (TabDaten.getS("" + i).indexOf('\n')>-1)
                      //  bCR=true;
                      String sArt=TabDaten.getS("0");
                      /*if (sArt.equals("U"))
                      {
                        int iPos=s.indexOf('\n');
                        vecSpalte[iCount].addElement(iPos==-1?s.substring(1):s.substring(1,iPos));
                        vecSpalte[iCount].addElement(foBez);
                        iCount++;
                        vecSpalte[iCount] = new Vector();
                        vecSpalte[iCount].addElement(iPos==-1?"":s.substring(iPos+1));
                        vecSpalte[iCount].addElement(foBez);
                      }
                      else
                      {*/
                        vecSpalte[iCount].addElement(s);
                        if(sArt.equals("S"))
                          vecSpalte[iCount].addElement(foSummen);
                        else if(sArt.equals("Z") || sArt.equals("H"))
                          vecSpalte[iCount].addElement(foZwSummen);
                        else
                          vecSpalte[iCount].addElement(bNurSumme ? null : foSpalte);
                        bCR=bCR || hasCR(vecSpalte[iCount]); // wegen Bewerberblatt
                      //}
                      iCount++;
                    }
                    if (bMemo2)
                      bCR=true;
                    int iWidth=pm.getRowWidth(vecSpalte, true);
                    boolean bBreite=TabBreite != null && TabBreite.getAnzahlElemente(null)>=i;
                    if (bBreite)
                      TabBreite.setPos(i-1);
                    if (/*bNurSumme &&*/ iWidth==0 && (bBreite && TabBreite.getI("Breite")==0))
                      iWidth=0;
                    else
                      iWidth=Math.max(iWidth,iWidthU);
                    int iWidthUser=!bBreite ? -1:TabBreite.getI("Breite")<=0 ? iWidth>7200/25.4 || bBild100 ? 100:0:TabBreite.getI("Breite");
                    //g.fixtestInfo(Vec.size()+": iWidthUser="+iWidthUser+"; bBreite/bMemo2="+bBreite+"/"+bMemo2);
                    if (bMemo2 || TabBreite==null || TabBreite.getS("Datentyp").equals("Text") && iWidthUser==0 && TabBreite.getI("Breite")==0)
                    {
                      if (TabBreite == null) iWidth+=(bSpaltenAbstand ? 8:3);
                      iWidthUser=(int)Math.round(iWidth*25.4/72.0);
                      //g.fixtestInfo("Breite bei Text:"+iWidthUser+"/"+bMemo2+"/"+bSpaltenAbstand+"/"+TabBreite);
                    }
                    if (iWidthUser<=0)
                    {
                      Vec.addElement(new Integer(iWidth == 0 ? 0 : iWidth + (bSpaltenAbstand ? 8 : 3) /*iVSpace*/));
                      //g.fixtestInfo("Spaltenabstand berücksichtigt:"+Vec);
                    }
                    else
                    {
                      long iWidthDef=Math.round(iWidthUser * 72 / 25.4);
                      //g.fixtestInfo(Vec.size()+": Breite "+iWidthDef);
                      Vec.addElement(new Integer((int)iWidthDef));
                      if (iWidthDef<iWidth || bBild100 || bCR || bBC)
                      {
                        //g.fixtestInfo(iWidthDef + " statt " + iWidth + "bei:" + vecSpalte);
                        pm.setFont(foSpalte);
                        TabDaten.bInsert=true;
                        TabDaten.moveFirst();
                        for(TabDaten.moveNext();!TabDaten.eof();TabDaten.moveNext())
                        {
                          //int iPos=TabDaten.getPos();
                          //String sL = TabDaten.getS("Lines");
                          boolean bIn=false;
                          boolean bBild=TabDaten.getInhalt("" + i) instanceof Image;
                          s=bBild ? " ":TabDaten.getM(""+i);
                          //g.fixInfo("Bild="+bBild+"->"+s);
                          //Object Obj=null;
                          if (bBild)
                          {
                            if(bBild100)
                            {
                              //TabDaten.setInhalt("Lines", null);
                              javax.swing.ImageIcon Img=new javax.swing.ImageIcon((Image)TabDaten.getInhalt("" + i));
                              for (int i3=0;i3<Img.getIconHeight()/50;i3++)
                              {
                                //TabDaten.putInhalt("" + i, s.substring(0, iPos), false);
                                TabDaten.setInhalt("Lines", null);
                                if(TabDaten.nextIsNull("" + i))
                                  TabDaten.moveNext();
                                else
                                {
                                  //TabDaten.setInhalt("Lines", null);
                                  bIn=true;
                                  //if (Obj==null)
                                  //  Obj=TabDaten.getInhalt("Lines");
                                  if (bCol)
                                    Col=(Color)TabDaten.getInhalt("color");
                                  TabDaten.newLine2();
                                }
                                //s = s.substring(iPos);
                                //iPos = getPos(s, iWidthDef);
                                //TabDaten.moveNext();
                              }
                            }
                          }
                          else if (bBC)
                          {
//                        	  g.fixtestError("Barcode verwendet");
                        	  for (int i3=0;i3<(bDZH?1:3);i3++)
                              {
                                TabDaten.setInhalt("Lines", null);
                                if(TabDaten.nextIsNull("" + i))
                                  TabDaten.moveNext();
                                else
                                {
                                  bIn=true;
                                  if (bCol)
                                    Col=(Color)TabDaten.getInhalt("color");
                                  TabDaten.newLine2();
                                }
                              }
                          }
                          else
                          {
                            //int iWidthMom=pm.stringWidths(s);
                            //g.progInfo(iWidthMom+":"+s);
                            String sDatentyp=TabBreite==null ? "":TabBreite.getS("Datentyp");

                            //if (pm.bHtml && (s.indexOf("<p")>=0 || s.indexOf("<br>")>0))
                            //  s=s.replaceAll("\n"," ").replaceAll("<br>","\n").replaceAll("  "," ");
                           boolean bDirekt= (iABits&Drucken.cstArt)==Drucken.cstHtmlDirekt;
                           if (pm.bHtml && (pm.bHtmlOk || bDirekt))
                           {
                              //s=s.replaceAll("\n"," ");
                              int iZeilen=bDirekt ? 1:pm.getZeilen(s,(int)iWidthDef);
                              //g.fixtestInfo("Keine Zeile für "+s);
                              if (iZeilen>1)
                              {
                                //TabDaten.push();
                                for (int iZ = 1; iZ < iZeilen; iZ++)
                                {
                                  if (TabDaten.nextIsNull("" + i) && TabDaten.nextIsNull("0"))
                                    TabDaten.moveNext();
                                  else
                                  {
                                    TabDaten.setInhalt("Lines", null);
                                    TabDaten.newLine2();
                                    bIn=true;
                                  }
                                  TabDaten.putInhalt("" + i," ",false);
                                }
                                //TabDaten.pop();

                              }
                           }
                           else
                           {
                             if (pm.bHtml && (s.indexOf("<p")>=0 || s.indexOf("<br>")>0))
                               s=s.replaceAll("\n"," ").replaceAll("<br>","\n").replaceAll("  "," ");
                            iPos = iWidthDef<5 ? /*s.length()>0?*/1/*:0*/:getPos(s, iWidthDef,sDatentyp,bMemo2);
                            //String sL = TabDaten.getS("Lines");
                            if(iWidthDef<5 || iPos > 0 && (TabDaten.getS("0").equals("Z") || TabDaten.getS("0").equals("H") || TabDaten.getS("0").equals("S")))
                            {
                              String s2=sub(s, iPos).trim();
                              //if (!pm.bHtml)
                                TabDaten.putInhalt("" + i, /*pm.bHtml ? s:*/s2, false);
                              //g.fixInfo("iWidthDef="+iWidthDef+"/"+TabDaten.getS("0"));
                              //g.fixInfo("xx "+i+", iPos="+iPos+"->"+s2);
                            }
                            else
                            {
                              while(iPos > 0)
                              {
                                String s2=sub(s, iPos).trim();
                                //if (!pm.bHtml || TabDaten.isNull("" + i))
                                  TabDaten.putInhalt("" + i, /*pm.bHtml ? " ":*/s2, false);
                                //g.fixInfo("Spalte "+i+", iPos="+iPos+"->"+s2);
                                //if(TabDaten.nextIsNull("Lines"))   // !!! geändert am 10.11.2004
                                if(TabDaten.nextIsNull("" + i) && TabDaten.nextIsNull("0"))      // !!! zurückgeändert am 16.12.2004
                                  TabDaten.moveNext();
                                else
                                {
                                  //if (Obj==null)
                                  //  Obj=TabDaten.getInhalt("Lines");
                                  TabDaten.setInhalt("Lines", null);
                                  if (bCol)
                                    Col=(Color)TabDaten.getInhalt("color");
                                  TabDaten.newLine2();
                                  bIn=true;
                                }
                                if (iPos>0)
                                {
                                  //g.testInfo(iPos+" von "+s.length());
                                  if (iPos>s.length())
                                    s="";
                                  else
                                    s = s.substring(iPos);
                                }
                                iPos = getPos(s, iWidthDef,sDatentyp,bMemo2);
                                //TabDaten.moveNext();
                              }
                              s=s.trim();
                              //if (s.startsWith("\n"))
                              //  s=s.substring(1);
                              //g.progInfo("sub: CR="+iPos+"<"+s+">");
                              //if (!pm.bHtml)
                                TabDaten.putInhalt("" + i, /*pm.bHtml ? " ":*/s, false);
                              if (bCol && Col != null)
                                TabDaten.putInhalt("color",Col,false);
                            }
                           }
                          }
                          if(bIn)
                              TabDaten.putInhalt("Lines", "L", false);
                        }
                      }
                    }
                  //}

                  //g.progInfo("Spaltenbreite "+i+"="+TabWidths.getI(""+i));

		}
                //if (g.Prog())
                //  TabDaten.showGrid();
		return Vec;
	}//getColumnWidths

        private boolean hasCR(Vector Vec)
        {
          if (Vec==null)
            return false;
          for (int i=0;i<Vec.size();i++)
          {
            //String s=""+Vec.elementAt(i);
            //g.progInfo("hasCR"+i+":"+s+"/"+s.length());
            if(("" + Vec.elementAt(i)).indexOf("\n") > -1)
              return true;
          }
          return false;
        }

        private String sub(String s,int iPos)
        {
          int iCR=s.indexOf('\n');
          //if (iCR != -1)
          //  g.progInfo("sub: CR="+iCR+"/"+iPos);
          if (iCR<0 && iPos>s.length())
            return s;
          String s2=s.substring(0, iCR+1==iPos ? iPos-1:iPos);
          //g.progInfo("sub: CR="+iCR+"/"+iPos+"<"+s2+">");
          return s2;
        }

        private int getPos(String s,long iW,String sDT,boolean bMemo2)
        {
          iW-=4;
          int iPlus=0;
          if (s.length()>1 && s.charAt(0)=='\n')
          {
            s = s.substring(1);
            iPlus=1;
          }

          int iCR=/*pm.bHtml && s.indexOf("<br>")>-1 ? s.indexOf("<br>")+3:*/s.indexOf('\n');
          //if (iCR==0)
          //	g.progInfo(" ------------------------------ iCR=0 ----------------------------");
          //g.progInfo("getPos:"+iCR+"/"+(iCR==-1?s:s.substring(0,iCR)));
          //if (iCR+1==s.length())
          //  iCR=-1;
          /*if (iCR==0)
          	return 1;
          else*/
          int iSW=pm.stringWidths(s);
          //g.progInfo(s+":"+iSW+"/"+iW+"/"+sDT+"/ CR="+iCR+"/"+s.length());
          if (bMemo2 && iCR==-1 || !bMemo2 && DtZahl.contains(sDT) || iSW<iW && (iCR==-1 || (iCR+1==s.length())))
            return 0;
          else if (iCR>-1 && iSW<iW)
            return iCR+iPlus;
          else
          {
          	/*int iCR2=s.lastIndexOf('\n');
          	if (iCR2+1==s.length())
          		iCR2=s.lastIndexOf('\n',iCR2-1);
          	int iP=iCR2>0 ? iCR2+1:0;
          	if (iP>0)
          		s=s.substring(iP);
                s=s.trim();*/
          	//if (iCR>0)
          	//  s=s.substring(0,iCR);
            boolean bVB=!bMemo2 && DtVB.contains(sDT);
            int i=s.lastIndexOf(pm.bHtml ? '>':bVB?'-':' ');
            int i2=!bMemo2 && DtDatum.contains(sDT) || bVB ? -2:s.lastIndexOf(pm.bHtml ? '>':'.');
            //g.testInfo("+"+i+"/"+i2+":"+s);
            if (i2>i) i=i2;
            i2=s.lastIndexOf(',');
            if (i2>i) i=i2;
            if (i==-1)
              if (iSW<iW)
                return iCR==-1 ? 0:iCR;
              else
                i=s.length();
            int iPos=0;
            int iBest=0;
            while (i>-1 && iPos==0)
            {
              String s2=s.substring(0,iCR>0&& iCR<i?iCR-1:i);
              int iMom=pm.stringWidths(s2);
              //g.progInfo(s2+": "+iMom+"/"+iW);
              if (iMom<iW)
                iPos=i+1;
              if (iMom<iW+3)
                iBest=i+1;
              i2=s.lastIndexOf(pm.bHtml ? '>':'.',i-1);
              int i3=s.lastIndexOf(',',i-1);
              int i4=s.lastIndexOf('-',i-1);
              i=s.lastIndexOf(' ',i-1);
              //g.testInfo(iPos+"/"+i+"/"+i2+":"+s);
              if (i==-1) i=i2;
              if (i==-1) i=i3;
              if (i==-1) i=i4;
              if (i==-1 && iBest==0 && iW<iMom)
              {
                if (bMemo2 || !DtDatum.contains(sDT))
                {
                  i2 = s2.length();
                  while(iMom > iW + 3) {
                    i2--;
                    iMom = pm.stringWidths(s2.substring(0, i2));
                    //g.progInfo(s2.substring(0,i2)+": "+iMom+"/"+iW);
                  }
                }
                iBest=i2;

              }
            }
            if (pm.bHtml && iPos>0 && iBest<s.length())
            {
              String sX=s.substring(0,iBest);
              if (sX.lastIndexOf("<")> sX.lastIndexOf(">"))
                iPos=sX.lastIndexOf("<")-1;
            }

            //g.testInfo("CR="+iCR+",iPos="+iPos+",iBest="+iBest+"/"+i+"/"+i2+":"+s);
            return (iCR==-1 ? iPos==0?iBest:iPos:Math.min(iBest/*iPos*/,iCR+1))/*+iPlus*/;
          }
        }//getPos

        private String CR(String s,boolean b)
        {
          //g.progInfo("CR:"+s+"/"+b);
          return !b || s.indexOf("\n")>-1 ? s.equals("")?" ":s:" \n"+(s.equals("")?" ":s);
        }

        public static void showOutliner(JCOutliner GidGesamt)
        {
          if (Static.bX11) // !!! für AServer !!!
          {
            Frame f = new Frame("");
            f.add(GidGesamt);
            f.setLocation(2000, 2000);
            f.addNotify();
            /*f.toBack();
              f.setVisible(true);*/
            //f.pack();
            f.dispose();
          }
          //else
          //  GidGesamt.folderChanged(GidGesamt.getRootNode());
        }

	private Tabellenspeicher makeTable(JCOutliner outliner,int iABits,ShowAbfrage Abf)
	{
          Memo1.bMemo=true;
          boolean bFarbe=(iABits & Drucken.cstColor)>0 && pm.Farbe();
          boolean bBarcode=false;
          if (Abf != null)
          {
        	  Tabellenspeicher TabSpalten=Abf.getSpalten();
        	  for (int i=0;i<TabSpalten.size();i++)
        		if ((TabSpalten.getI(i,"bits3")&Abfrage.cstBarcode)>0)
        		  bBarcode=true;
        	  
          }
          //boolean bLeer=VecBreite!=null && VecBreite.contains(new Integer(-2));
          //g.progInfo("makeTable:"+bLeer);
		Vector vec=getOutlinerNodes(outliner);//outliner.getVisibleNodes();
		/*if(outliner.getRootVisible())
			vec.removeElementAt(0);*/

		//int iPos=0;
		int iLevel=1;
		//System.out.println("VecSize:"+vec.size());
		/*for(int i=0;i<vec.size();i++)              //Anzahl der Ebenen ermitteln
		{
			//System.out.println("-->:"+vec.elementAt(i));
			JCOutlinerNode fn=(JCOutlinerNode)vec.elementAt(i);
			if(fn.getLevel()>iLevel)
				iLevel=fn.getLevel();
		}*/
		String [] sSpalten=new String[iLevel+(outliner.getNumColumns())+(bFarbe ? 2:1)];
		//JCOutlinerNode [] jcS=new JCOutlinerNode[iLevel-1];
		for(int i=0;i<iLevel+(outliner.getNumColumns());i++)
			sSpalten[i]=""+i;
		sSpalten[iLevel+(outliner.getNumColumns())]="lines";
                if (bFarbe)
                  sSpalten[iLevel+(outliner.getNumColumns())+1]="color";
		Tabellenspeicher TabTest=new Tabellenspeicher(g,sSpalten);
		String [] sTitel= outliner.getColumnLabels();
                //g.progInfo("sTitel="+sTitel+"/"+sTitel.length);
                boolean bCR=false;
                for(int i=0;i<sTitel.length;i++)
                  bCR=bCR || sTitel[i].indexOf("\n")>-1;
		TabTest.newLine();
                //if (bLeer)
                //  TabTest.setInhalt("1",outliner.getColumnAlignment(0)+CR(sTitel[0],bCR));
		TabTest.setInhalt("0","U");
		/*int iCount=1;
		for(int i=iLevel+1;i<iLevel+(outliner.getNumColumns());i++)
		{
			TabTest.setInhalt(""+i,outliner.getColumnAlignment(iCount)+CR(sTitel[iCount],bCR));
			iCount++;
		}*/
                int iAlign[]=Abf == null || (Abf.iBits&Abfrage.cstEinzeldaten)>0 ? null:Abf.getAusrichtung();
                //for(int i=1;i<iLevel/*+(bLeer?1:0)*/;i++)
                //  TabTest.setInhalt(""+i,(iAlign == null ? outliner.getColumnAlignment(0):iAlign[0])+CR("",bCR));
                //g.progInfo(sTitel+" / "+iAlign);
                for(int i=1/*iLevel+(bLeer?1:0)*/;i<outliner.getNumColumns()+1;i++)
                {
                  //g.progInfo(i+":"+Abf.getAusrichtung()[i - 1]+" bei "+sTitel[i - 1]+"("+iLevel+")");
                  //g.progInfo("outliner.getColumnAlignments()="+outliner.getColumnAlignments().length+"/"+(i-iLevel));
                  TabTest.setInhalt("" + i,(iAlign == null || iAlign.length<i ?
                                            /*outliner.getColumnAlignments().length>i-iLevel ?*/ outliner.getColumnAlignment(i - 1)//:0
                                            : iAlign[i - 1/*(bLeer ? 2:1)*/])+ CR(sTitel.length==0?"":sTitel[i - 1], bCR));
                }
                //iLevel=1;
                //if (Abf != null)
                //  g.progInfo("Ausrichtung:"+Abf.getAusrichtung());
		int iCurLevel=-1;
		TabTest.newLine();
		//JCOutlinerNode fn=null;
		boolean bNoget=false;
		JCOutlinerNode fn=(JCOutlinerNode)vec.elementAt(0);
		for(int i=0;i<vec.size();i++)
		{

			if(!bNoget)
				fn=(JCOutlinerNode)vec.elementAt(i);
			if((fn.getLevel())<=iCurLevel||bNoget)
			{
				if((fn.getLevel())<iCurLevel||bNoget)
				{
					boolean bRun=true;
					JCOutlinerNode jn;
					if(!bNoget)
						jn=((JCOutlinerNode)vec.elementAt(i-1));
					else
						jn=((JCOutlinerNode)vec.elementAt(i));
					while(bRun)
					{
						jn=jn.getParent();
						TabTest.newLine();//<-


						getNode(jn,TabTest,""+(jn.getLevel()),jn.getLevel()==1?"H":"Z");
                                                //g.progInfo("1:"+TabTest.getPos()+"/"+jn.getLevel()+":"+jn.toString());

						//TabTest.setInhalt(""+(jn.getLevel()),jn.toString());//<-
						//System.out.println(">>>"+jn.toString());
						if(jn.getLabel() instanceof Vector)
						{

							Vector v=(Vector)jn.getLabel();
							for(int j=1;j<v.size();j++)
							{
								//TabTest.setInhalt(""+(j+iLevel),""+v.elementAt(j));
                                                                //Image Img=v.elementAt(j) instanceof Image ? (Image)v.elementAt(j):null;
								String str=v.elementAt(j)==null?"":""+v.elementAt(j);                            
                                                                  //g.progInfo("3:"+str+"/"+TabTest.getPos()+"/"+j+"/"+iLevel);
                                                                  if (TabTest.isNull(""+(j+iLevel)))
                                                                  {
                                                                    TabTest.setInhalt(""+(j+iLevel),str);
//                                                                    if (g.bInfoDruck)
//                                                                        g.fixInfo("makeTable:"+TabTest.getPos()+"/"+(j+iLevel)+":"+str);
                                                                  }
							}
						}
						if(bNoget&&jn.getLevel()==1)
							bRun=false;
						else if(jn.getLevel()==fn.getLevel())
							bRun=false;
						TabTest.push();
						TabTest.moveLast();
						TabTest.setInhalt("LINES","L");
						TabTest.pop();

					}
				}
				//System.out.println("TabTest:"+TabTest.eof());
				if(!bNoget)
				{
					TabTest.newLine();
					getNode(fn,TabTest,""+(fn.getLevel()),"");
					getLabels(fn,TabTest,iLevel);
					iCurLevel=fn.getLevel();
				}

			}
			else
			{
				//TabTest.setInhalt(""+(fn.getLevel()-1),fn.toString());
                                //g.progInfo("3:"+TabTest.getPos()+"/"+fn.getLevel()+"/"+iLevel+":"+fn.toString());
				getLabels(fn,TabTest,iLevel);
                                getGruppeninhalt(fn,TabTest);
                                //getNode(fn,TabTest,""+(fn.getLevel()),"");
				iCurLevel=fn.getLevel();
			}
			TabTest.push();
			TabTest.moveLast();
			TabTest.setInhalt("LINES","L");
			if(i==vec.size()-1&&!bNoget)
			{
				if(fn.getLevel()>1)
				{
					i--;
					bNoget=true;
				}

			}
			//System.out.println("Level-->"+fn.getLevel());
			TabTest.pop();

		}

                Object Obj=outliner.getRootNode().getLabel();
                if ((iABits&Drucken.cstGesamtsumme)>0 && Obj instanceof Vector)
                {
                  TabTest.newLine();
                  Vector v = (Vector) Obj;
//                  g.fixtestError("Ges-Vec="+v+"/"+v.size()+" bei "+sTitel.length);
                  for (int j = 1; j < v.size(); j++) {
                    String str = v.elementAt(j) == null ? bBarcode ? " ":"" : "" + v.elementAt(j);
                    if (str.indexOf("\n") != -1) {
                      TabTest.push();
                      StringTokenizer st = new StringTokenizer(str, "\n");
                      TabTest.setInhalt("" + (j + iLevel), st.nextToken());
                      TabTest.setInhalt("0", "S");
                      while (st.hasMoreTokens()) {
                        if (TabTest.getPos() + 1 == TabTest.getAnzahlElemente(null))
                          TabTest.newLine();
                        else
                          TabTest.moveNext();
                        TabTest.setInhalt("" + (j + iLevel), st.nextToken());
                        TabTest.setInhalt("0", "S");
                      }
                      TabTest.pop();
                    }
                    else {
                      TabTest.setInhalt("" + (j + iLevel), str);
                      TabTest.setInhalt("0", "S");
                    }
                  }
                  
                  if (bBarcode && v.size()<sTitel.length)
                	  TabTest.setInhalt("" +sTitel.length," ");
                }

		//Vector vecDaten=new Vector();
		//vecDaten.addElement(TabTest);
		//vecDaten.addElement(new Integer(iLevel+(outliner.getNumColumns()-1)));
            Memo1.bMemo=false;
            return TabTest;

	}

        private void getGruppeninhalt(JCOutlinerNode Out,Tabellenspeicher TabTest)
        {
          int iL=Out.getLevel();
          //g.progInfo(TabTest.getPos()+":"+iL+":"+Out.getParent());
          if (iL>0)
          {
            //g.progInfo(TabTest.getPos()+"/"+(iL-1)+":"+(iL==2?Out.getParent():((Vector)Out.getParent().getLabel()).elementAt(iL-2)));
            //g.progInfo("getGruppeninhalt:"+iL+","+Out.getLabel()+"/"+Out.getParent().getLabel()+"/"+TabTest.getS(""+(iL-(iL>1?1:0))));
            if (iL==1)
            {
              //if (!Out.getLabel() instanceof Memo1)
              //  TabTest.setInhaltS("" + iL, Sort.gets(Out.getLabel())); // 5.12.2007 entfernt wegen Bewerberblatt
            }
              //TabTest.setInhaltS(""+(iL-(iL>1?1:0)),""+(iL==1?Out:iL==2?Out.getParent():((Vector)Out.getParent().getLabel()).elementAt(iL-2)));
            else for (;iL>1;iL--)
            {
              Out=Out.getParent();
              //g.progInfo(iL+":"+Out.getLabel());
              String s=""+((Vector)Out.getLabel()).elementAt(iL - 2);
              if (sLast == null || !s.equals(sLast))
                TabTest.setInhaltS("" + (iL - 1), s);
              if (g.bInfoDruck)
                  g.fixInfo("getGruppeninhalt:"+TabTest.getPos()+"/"+(iL-1)+":"+s);
              //g.progInfo(TabTest.getPos()+"/"+(iL-1)+":"+s);
            }
            //g.progInfo("nachher:"+TabTest.getS(""+(iL-(iL>1?1:0))));
            //if (iL==2)
            //  TabTest.showGrid("Daten");
          }
        }

	public static Vector getOutlinerNodes(JCOutliner outliner)
	{
          //if (!Static.bX11) // !!! für AServer !!!
          //  return outliner.getRootNode().getChildren();

		outliner.folderChanged(outliner.getRootNode());
		JCOutlinerNode root=outliner.getRootNode();
		JCOutlinerNode curNode=root;
		Vector<JCOutlinerNode> vec=new Vector<JCOutlinerNode>();
                if (outliner.getRootVisible())
                  vec.addElement(root);
                if (Static.bX11)
		 while((curNode=outliner.getNextNode(curNode))!=null)
                 {
                  JCOutlinerFolderNode NodP=curNode.getParent();
                  while (NodP != root && NodP.getState() != BWTEnum.FOLDER_CLOSED)
                    NodP=NodP.getParent();
                  //if(curNode.getParent().getState() != BWTEnum.FOLDER_CLOSED)
                  if (NodP == root)
                    vec.addElement(curNode);
                 }
               else
                 addNodes((JCOutlinerFolderNode)outliner.getRootNode(),vec);
		return vec;
	}

        private static void addNodes(JCOutlinerFolderNode Nod,Vector<JCOutlinerNode> vec)
        {
          Vector vec2=Nod.getChildren();
          for (int i=0;i<vec2.size();i++)
          {
            JCOutlinerNode curNode=(JCOutlinerNode)vec2.elementAt(i);
            vec.addElement(curNode);
            if (curNode.getState() != BWTEnum.FOLDER_CLOSED && curNode instanceof JCOutlinerFolderNode)
              addNodes((JCOutlinerFolderNode)curNode,vec);
          }
        }

	private void getNode(JCOutlinerNode node,Tabellenspeicher TabDaten,String sPos,String sType)
	{
          //g.fixInfo("node="+node+","+node.getStyle().getForeground());
		TabDaten.push();
             
		TabDaten.setInhalt("0",sType);

		
                        if (!sType.equals(""))
                        {
                          int iPos=new Integer(sPos).intValue();
                          for(int i=iPos-(iPos==1?0:1);i>0;i--)
                          {
                            TabDaten.push();
                            String s=null;
                            while(s==null)
                            {
                              TabDaten.movePrevious();
                              //if (!TabDaten.isNull(""+i))
                              if (!TabDaten.getS(""+i).equals(""))
                                s=TabDaten.getS(""+i);
                            }
                            TabDaten.pop();
                            //if (TabDaten.isNull(""+i))
                            //if (sType.equals("Z"))
                            //  g.progInfo(TabDaten.getPos()+"/"+i+":"+s);
                            if (s != null)
                            {
                              TabDaten.setInhalt(""+i,s);
                              if (g.bInfoDruck)
                            	  g.fixInfo("getNode:"+TabDaten.getPos()+"/"+i+":"+s+"/"+sLast+"/"+iPosLast);
                              if ((iABits&Drucken.cstGruppenRed)>0)
                              {
	                              if (sLast != null && s.equals(sLast))
	                            	  TabDaten.setInhalt(iPosLast, ""+i, "");
	                              iPosLast=TabDaten.getPos();
	                              sLast=s;
                              }
                            }
                          }
                        }
                        if (!TabDaten.getS(sPos).equals(""))
                        {
                          TabDaten.setInhalt(sPos, /*node.toString()*/ Sort.gets(node.getLabel()));
                        }
                        if (node != null && node.getStyle() != null)
                        {
                          Color col = node.getStyle().getForeground();
                          //g.fixInfo("getNode: " + node + "," + col);
                          if (col != null && TabDaten.exists("color"))
                            TabDaten.setInhalt("color", col);
                        }

                TabDaten.pop();
                //Memo1.bMemo=false;
	}

	private void getLabels(JCOutlinerNode node,Tabellenspeicher TabDaten,int iLevel)
	{		
		if(node instanceof JCOutlinerFolderNode)
		{
			JCOutlinerFolderNode fH=(JCOutlinerFolderNode)node;
			if(fH.getChildren()==null||((Vector)fH.getChildren()).size()==0||fH.getState()==BWTEnum.FOLDER_CLOSED)
			{
				if(fH.getLabel() instanceof Vector)
				{

					Vector v=(Vector)fH.getLabel();
					//System.out.println(">2<");
                                        //g.progInfo("getLabels2:"+v);
					for(int j=0;j<v.size();j++)
					{
                            Image Img=v.elementAt(j) instanceof Image ? (Image)v.elementAt(j):null;
                            Barcode BC=v.elementAt(j) instanceof Barcode ? (Barcode)v.elementAt(j):null;
                            String str=v.elementAt(j)==null?"":""+v.elementAt(j);
//                            if (BC != null)
//                            	g.fixtestError("Barcode bei getLabels1:"+str);
                            // seit 13.7.2009 dabei
                              if (Img != null)
                                TabDaten.setInhalt("" + (j + iLevel), Img);
                              else if (BC != null)
                                TabDaten.setInhalt("" + (j + iLevel), BC);
                              else
                                TabDaten.setInhalt("" + (j + iLevel), str); //*/
                              if (g.bInfoDruck)
                                  g.fixInfo("getLabels1:"+TabDaten.getPos()+"/"+(j+iLevel)+":"+str);                                                
					}
				}
			}
		}
		else
		{
			//System.out.println("Node!");

			if((Vector)node.getLabel()!=null)
			{
				Vector v=(Vector)node.getLabel();
				for(int j=node.getLevel()-1;j<v.size();j++)
				{
					//System.out.println("vor n");
                                        Image Img=v.elementAt(j) instanceof Image ? (Image)v.elementAt(j):null;
                                        Barcode BC=v.elementAt(j) instanceof Barcode ? (Barcode)v.elementAt(j):null;
                                        String str=v.elementAt(j)==null?"":""+v.elementAt(j);
//                                        if (BC != null)
//                                        	g.fixtestError("Barcode bei getLabels2:"+str);
					//System.out.println("nach n");
                                        // seit 13.7.2009 dabei
					
                                          if (Img !=null)
                                            TabDaten.setInhalt(""+(j+iLevel),Img);
                                          else if (BC != null)
                                            TabDaten.setInhalt("" + (j + iLevel), BC);
                                          else
                                          {
                                            if (node.getStyle().getFont()==g.fontSumme)
                                              TabDaten.setInhalt("0","Z");
                                            else if (node.getStyle().getFont()==g.fontSumme2)
                                              TabDaten.setInhalt("0","S");
                                            else// if (node != null && node.getStyle() != null)
                                            {
                                              Color col = node.getStyle().getForeground();
                                              //g.fixInfo("getNode: " + node + "," + col);
                                              if (col != null && TabDaten.exists("color"))
                                                TabDaten.setInhalt("color", col);
                                            }
                                            if (TabDaten.exists(""+(j+iLevel)))
                                              TabDaten.setInhalt(""+(j+iLevel),str);
//                                            if (g.bInfoDruck)
//                                              g.fixInfo("getLabels2:"+TabDaten.getPos()+"/"+(j+iLevel)+":"+str);
                                          }
				}
			}
		}
		//====================================================
		//System.out.println("ende");
	}

	private void getOptions(int iLayout,long iDruckBits)
	{
          if (iLayout==0)
          {
            if (!g.Def())
              new Message(Message.WARNING_MESSAGE,null,g).showDialog("kein_Seitenlayout");
            Static.printError("Kein Seitenlayout hinterlegt!!!");
            pm.setMargins(20, 10, 10, 10);
          }
          else
          {
//            g.clockInfo("getOptions start");
            SQL Qry =new SQL(g);
            Qry.open("select * from layout where aic_layout="+iLayout);
            if (!Qry.eof())
            {
              bQuer=(Qry.getI("Bits")&Drucken.cstLayQuer)>0 || bQuer;
              pm.setMargins(Qry.getI("Links"), Qry.getI("Rechts"), Qry.getI("Oben"), Qry.getI("Unten"));
              //g.fixInfo("DruckHS.getOptions:"+Qry.getI("aic_schrift")+"/"+g.fontTitel);
              if (Qry.getI("aic_schrift")>0 && (foDTitel==null || foDTitel==g.fontTitel))
                foDTitel=g.getSchrift(Qry.getI("aic_schrift"),g.fontTitel);
              //g.fixInfo("Seitenlayoutschrift->"+foDTitel);
              if ((iDruckBits&Drucken.cstPntKeinKopfFuss)==0)
              {
                int iKopf = Qry.getI("AIC_Zeile");
                int iFuss = Qry.getI("ZEI_AIC_Zeile");
                if (iKopf > 0)
                {
                  Qry.open("select * from Zeile where aic_zeile=" + iKopf);
                  int iAlign = Qry.getI("Ausrichtung");
                  String sFBild = Qry.getS("Bild");
//                  if (!sFBild.trim().equals(""))
//                    sFBild = Static.DirImageDef/*.substring(6)*/ + sFBild;
                  Font foHead = g.getSchrift(Qry.getI("aic_schrift"),g.fontTitel); //new Font("SansSerif", Font.PLAIN, 12);
                  pm.setHeadrow(cP(Qry.getS("Links")), cP(Qry.getS("Mitte")), cP(Qry.getS("Rechts")), Static.Leer(sFBild) ? null: SQL.getImageZ(g,iKopf),
                                iAlign == FlowLayout.RIGHT ? PrintManagerA.RIGHT : iAlign == FlowLayout.CENTER ? PrintManagerA.CENTER : PrintManagerA.LEFT, foHead,
                                Qry.getI("Hoehe"), (Qry.getI("Bits") & Drucken.cstLinie) > 0);
                }
//                g.clockInfo("getOptions Kopf");
                if (iFuss > 0)
                {
                  Qry.open("select * from Zeile where aic_zeile=" + iFuss);
                  int iAlign = Qry.getI("Ausrichtung");
                  String sFBild = Qry.getS("Bild");
//                  if (!sFBild.trim().equals(""))
//                    sFBild = Static.DirImageDef/*.substring(6)*/ + sFBild;
                  Font foFoot = g.getSchrift(Qry.getI("aic_schrift"),g.fontTitel); //new Font("SansSerif", Font.PLAIN, 12);
                  pm.setFootrow(cP(Qry.getS("Links")), cP(Qry.getS("Mitte")), cP(Qry.getS("Rechts")), Static.Leer(sFBild) ? null: SQL.getImageZ(g,iFuss),
                                iAlign == FlowLayout.RIGHT ? PrintManagerA.RIGHT : iAlign == FlowLayout.CENTER ? PrintManagerA.CENTER : PrintManagerA.LEFT, foFoot,
                                Qry.getI("Hoehe"), (Qry.getI("Bits") & Drucken.cstLinie) > 0);
                }
//                g.clockInfo("getOptions Fuss");
              }
            }
            Qry.free();
          }
	}

        public static int getFirma(Global g)
        {
          if (iFirma==0)
            iFirma=g.getSyncStamm(g.iSttFirma);
          return iFirma;
        }

        public static String PH(Global g,String sP,String sEig,String sText)
        {
          if (sText.indexOf(sP) > -1)
          {
            //g.progInfo("!!PH!! "+sP+"/"+sEig);
            int iPos=g.TabEigenschaften.getPos("Kennung",sEig);
            if (iPos<0)
              return sText;
            String sDT=g.TabEigenschaften.getS(iPos,"Datentyp");
            String s= /*sDT.equals("Stringx") && !Static.bStringX ? SQL.getString(g,"select stringx from stringXview where aic_stamm="+getFirma(g)+" and aic_eigenschaft="+g.TabEigenschaften.getI(iPos,"Aic")):*/
                SQL.getString(g,"select spalte_"+sDT+" from poolview p join daten_"+sDT+" d on d.aic_daten=p.aic_daten where p.aic_stamm="+getFirma(g)+" and p.aic_eigenschaft="+g.TabEigenschaften.getI(iPos,"Aic"));
            //g.progInfo("!!PH!! "+sP+"/"+iPos+"/"+sDT+":"+s);
            return Static.replaceString(sText, sP, s);
          }
          else
            return sText;
        }

        private String cP(String sText)
        {
          if (sText != null) // \p und \s wird in PrintManagerA behandelt
          {
            if (sText.indexOf("\\u") > -1) // Benutzer
              sText = Static.replaceString(sText, "\\u", g.getUser());
            if (sText.indexOf("\\c") > -1) // Firma
              sText = Static.replaceString(sText, "\\c",SQL.getString(g,"select bezeichnung from stammview where aic_stamm="+getFirma(g)));
            if (sText.indexOf("\\t") > -1) // Uhrzeit
              sText = Static.replaceString(sText, "\\t", new Zeit(new Date(), "HH:mm").toString());
            if (sText.indexOf("\\d") > -1) // Datum
              sText = Static.replaceString(sText, "\\d", new Zeit(new Date(), "dd.MM.yyyy").toString());
            sText=PH(g,"\\Ort","ORT",sText);
            sText=PH(g,"\\Strasse","STRASSE",sText);
            sText=PH(g,"\\PLZ","PLZ",sText);
            sText=PH(g,"\\DVR","DVR_NR_STRING",sText);
            sText=Static.replaceString(sText,"\\n","\n");
            //if (sText.indexOf("\\Strasse") > -1)
            //  sText = Static.replaceString(sText, "\\Strasse",SQL.getString(g,"
          }

          return sText;
        }

        public void setUnterschrift()
        {
          //int iY=pm.iCurY+20;
          //int iY=(int)pm.getFullHeight()-80;
          int iY=pm.iFootY-20;
          pm.setFont(foDTitel);
          pm.drawSpalte(g.getBegriffS("Show","Ort")+", "+g.getBegriffS("Show","Datum"),(int)pm.getIX(false),iY,PrintManagerA.CENTER,PrintManagerA.BLINE,200);
          pm.drawSpalte(g.getBegriffS("Show","Firmenstempel")+", "+g.getBegriffS("Show","Unterschrift"),300,iY,PrintManagerA.CENTER,PrintManagerA.BLINE,200);

		/*JLabel laU=new JLabel("\\|Ort, Datum");
		add(laU,15,dPageHeight-30,60,15,4,false);
		laU=new JLabel("\\|Firmenstempel, Unterschrift");
		add(laU,90,dPageHeight-30,75,15,4,false);
		drawLine(15,dPageHeight-31,60,0.15,false);
		drawLine(90,dPageHeight-31,75,0.15,false);*/
        }

        public void printReady()
        {
          //Drucken.bAktiv=false;
          pm.printEnd();
//          g.LoadSchrift(false,g.getFontFaktor());
        }
        
        private Vector<String> addVec(Vector<String> Vec,Vector<String> Vec2)
        {
        	if (Vec2==null)
        		return Vec;
        	else
        	{
//        		if (Vec==null)
//        			Vec=new Vector();
        		for (int i=0;i<Vec2.size();i++)
        			Vec.addElement(Vec2.elementAt(i));
        		return Vec;
        	}
        }

        public String printPDF(String sFile,int iVon,int iBis,String sPW,Vector<DruckHS> VecDH)
        {
//        	g.fixtestError("printPDF "+sFile+": "+(VecDH==null ? "null":""+VecDH.size()));
          printReady();
          if (sFile==null || sFile.equals(""))
          {
                JFileChooser chooser = new JFileChooser();
                SimpleFileFilter filefilter = new SimpleFileFilter(new String[] {"PDF"}, "Portable Document Format (*.PDF)");
                chooser.setMultiSelectionEnabled(false);
                chooser.addChoosableFileFilter(filefilter);
                //chooser.setCurrentDirectory(new java.io.File("C:\\"));
                chooser.setFileFilter(filefilter);
                int option = chooser.showSaveDialog(null);
                if(option == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile()!=null)
                {
                  sFile = chooser.getSelectedFile().getPath();
                  if (sFile.indexOf(".")<0)
                    sFile+=".pdf";
                }
          }

          if (sFile==null)
            return null;
          try
              {
        	    long lClock2 = Static.get_ms();
                Rectangle R=pm.A3() ? PageSize.A3:PageSize.A4;
                Vector<String> VecPDF=new Vector<String>();
                boolean bEinzeln=pm.getPDF()==null && (VecDH==null || VecDH.size()==1);
                boolean bQR=false; //Querformat richtig;
                for (int iS=0;iS==0 || (VecDH !=null && iS<VecDH.size());iS++)
                {
                	Document document = new Document(pm.Quer() && (!bQR || bEinzeln) ? R.rotate():R);                    
                	PrintManagerA pm2=VecDH==null ? pm:VecDH.elementAt(iS).pm;
                	Vector VecSeiten=pm2.getPages();
                    Vector<String> Vec2=pm2.getPDF();
	                String sFile2=bEinzeln ? sFile:sFile.substring(0,sFile.lastIndexOf('.'))+"_temp"+iS+sFile.substring(sFile.lastIndexOf('.'));
//	                g.fixtestError("printPDF "+sFile2+" hat "+VecSeiten.size()+" Seiten");
	                VecPDF.addElement("file://"+sFile2);
	                VecPDF=addVec(VecPDF,Vec2);
//	                g.fixtestError(iS+".printPDF: File="+sFile2+" hat "+VecSeiten.size()+" Seiten"+" -> VecPDF="+VecPDF);
	                PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(sFile2));
	                if (sPW!=null)
	                	writer.setEncryption(sPW.getBytes(),sPW.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
	                document.open();
	                for (int i=(VecDH==null ? iVon-1:0);i<(VecDH==null ? iBis:VecSeiten.size());i++)
	                {
//	                	g.fixtestError("Seite "+(i+1)+" von "+sFile2);
	                  PdfContentByte cb = writer.getDirectContent();
	                  cb.saveState();
	                  float w=/*pm.Quer() && bQR && !bEinzeln ? (int)pm2.getFullHeight():*/(int)pm2.getFullWidth();
	                  float h=/*pm.Quer() && bQR && !bEinzeln ? (int)pm2.getFullWidth():*/(int)pm2.getFullHeight();
	                  java.awt.Graphics2D g2 = cb.createGraphics(w, h);
	                  g2.setColor(Color.black);
	                  g2 = pm2.makePage(g2, (Vector)VecSeiten.elementAt(i), 1);
	                  g2.dispose();
	                  cb.restoreState();
	                  g.clockInfo("PDF - " + sFile2+" S"+(i+1)+Static.Mem(g.SuperUser()), lClock2);
	                  lClock2 = Static.get_ms();
	                  if (i+1<VecSeiten.size())
	                    document.newPage();
	                }
	                document.close();
                }
                if (VecPDF.size()>1)
                {
                		concatPDFs(g,VecPDF, new FileOutputStream(sFile), false,pm.A3(),pm.Quer() && bQR);
                		for (int i=0;i<VecPDF.size();i++)
                			new java.io.File(VecPDF.elementAt(i).substring(7)).delete();
//                        return "/tmp/merge.pdf";
                }
//                else
                  return sFile;
              }
          catch(Exception e) { 
        	  g.printError("Exception bei printPDF:"+e);
        	  g.printStackTrace(e);
        	  return null;
           }
        }
        
        public static void concatPDFs(Global g,Vector<String> VecPDF, FileOutputStream outputStream, boolean paginate,boolean bA3,boolean bQuer) {

//            Document document = new Document();
        	Rectangle R=bA3 ? PageSize.A3:PageSize.A4;
            Document document = new Document(bQuer ? R.rotate():R);
            try {
                Vector<PdfReader> readers = new Vector<PdfReader>();
                int totalPages = 0;
                for (int i=0;i<VecPDF.size();i++)
                {
                	String s=Sort.gets(VecPDF, i).substring(7);
//                	g.fixtestError("concatPDFs: anhängen von "+s);
                    InputStream pdf = new FileInputStream(s);
                    PdfReader pdfReader = new PdfReader(pdf);
                    pdfReader.removeUnusedObjects();
//                    pdfReader.addPdfObject(new PdfObject(0, s) {});
                    readers.add(pdfReader);
                    totalPages += pdfReader.getNumberOfPages();
                }
                // Create a writer for the outputstream
                PdfWriter writer = PdfWriter.getInstance(document, outputStream);

                document.open();
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
                // data

                PdfImportedPage page;
                int currentPageNumber = 0;
                int pageOfCurrentReaderPDF = 0;
                Iterator<PdfReader> iteratorPDFReader = readers.iterator();

                // Loop through the PDF files and add to the output.
                int iPDF=0;
                while (iteratorPDFReader.hasNext()) { 
                    PdfReader pdfReader = iteratorPDFReader.next();

                    // Create a new page in the target for each source page.
                    while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                        document.newPage();
                        pageOfCurrentReaderPDF++;
                        currentPageNumber++;
                        page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
//                        if (bQuer)
//                        	cb.variableRectangle(R.rotate());
                        cb.addTemplate(page, 0, 0);

                        // Code for pagination.
                        if (paginate) {
                            cb.beginText();
                            cb.setFontAndSize(bf, 9);
                            String s=Sort.gets(VecPDF, iPDF);
                            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, s.substring(s.lastIndexOf(File.separator)+1)+" AU-Seite "+ currentPageNumber + " von " + totalPages, 570, 5, 0);
                            cb.endText();
                        }
//                        cb.beginText();
//                        cb.setFontAndSize(bf, 9);
//                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER,"hallo ABC",20,20,0);
//                        cb.endText();
                    }
                    pageOfCurrentReaderPDF = 0;
                    iPDF++;
                }
                outputStream.flush();
                document.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (document.isOpen())
                    document.close();
                try {
                    if (outputStream != null)
                        outputStream.close();
                } catch (Exception ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    

        /**
         *Zeigt eine Vorschau
         */

	public void vorschau()
	{
	  printReady();
//	  g.fixInfo("nach printReady");
	  new Vorschau2(g,pm,"Seitenvorschau"+(bQuer?"3":"2"));
	}

        public int pages()
        {
          return pm.countPages();
        }

        
        public void addPDF(String sFile)
        {
        	pm.addPDF(sFile);
        }
        /**
         *druckt ohne Vorschau
         */

	public void print()
	{
	  printReady();
      pm.startPrint();
      pm.printPDFs();
	}

	/*public void printer()
	{
		pm.printDialog();
	}*/

        /*public void setTitle(String sTitle)
	{
          setTitle(sTitle,PrintManagerA.CENTER);
        }*/

  /**
   *setzt den Titel
   */

	private void setTitle(String sTitle, int iAlign)
	{
          //g.progInfo("setTitle:"+sTitle+"/"+iAlign+"/"+bRechts);
          //pm.setFont(foTitel);
          pm.setColor(coTitel);
          checkY();
          int iY=pm.iCurY==0?(int)pm.getIY()+5:pm.iCurY+iHSpace;
          //int iX=(int)pm.getIX();
          //int iWidth=(int)pm.getIWidth();
          pm.drawSpalte(sTitle,(int)pm.getIX(bRechts),iY,iAlign,0,(int)(pm.getIWidth(bRechts)));
          //g.progInfo("!!!setTitel:"+sTitle+"/"+iAlign+"/"+iX+"/"+iY+"/"+iWidth);
	}


        public void setBack(Image Img,boolean bVoll)
        {
          int iHelp=pm.iCurY;
          int iRand=bVoll ? 0:40;
          pm.iCurY=iRand*2;
          pm.drawImage(Img,iRand,iRand*2,(int)pm.getFullWidth()-2*iRand,(int)pm.getFullHeight()-4*iRand,PrintManagerA.LEFT);
          pm.iCurY=bVoll ? iHelp:(int)pm.getFullHeight();
        }

        public void addImage(Image Img)
        {
          pm.drawImage(Img,(int)pm.getIX(false),pm.iCurY,(int)pm.getIWidth(bRechts),(int)(pm.getIHeight()-pm.iCurY),PrintManagerA.LEFT);
        }

}
