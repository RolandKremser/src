package All_Unlimited.Print;


import java.util.Vector;

import jclass.bwt.BWTEnum;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
//import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;

public class DruckeAbfrageA implements Runnable
{
    public DruckeAbfrageA(Global rg,String rsTitel,String rsPDF_File,String rsPDF_PW,int iBegriff,int iStammtyp,Tabellenspeicher TabStamm,int riAIC_Raster,int riDFBits,int riLayout,JCOutlinerFolderNode Out,java.util.Vector rVecE)
	{
		g=rg;
		iAIC_Raster=riAIC_Raster;
		sTitel=rsTitel;
                sPDF_File=rsPDF_File;
                sPDF_PW=rsPDF_PW;
		this.iBegriff=iBegriff;
		this.iStammtyp=iStammtyp;
		this.TabStamm=TabStamm;
                this.iDFBits=riDFBits;
                this.iLayout=riLayout;
                this.NodGroup=Out;
                this.VecE=rVecE;
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

    @SuppressWarnings("unchecked")
	public void run()
	{
          long lClock = Static.get_ms();
          TabStamm.moveFirst();
		DruckHS dh=new DruckHS(g,sTitel,iLayout,iDFBits&(Drucken.cstDruckerauswahl + Drucken.cstDFFarbe),TabStamm.getI("AIC"));
                dh.LoadRaster(iAIC_Raster);
                //dh.TabRaster=new Tabellenspeicher(g,"select aic_raster,kennung "+g.AU_Bezeichnung("raster")+",aic_schrift,sch_aic_schrift,sch2_aic_schrift,sch3_aic_schrift,sch4_aic_schrift,sch5_aic_schrift,sch6_aic_schrift,bits from raster where "+
                //                                  (iAIC_Raster>0 ? "aic_raster="+iAIC_Raster:g.bit("bits",Drucken.cstStandard)),true);
		ShowAbfrage Abf=new ShowAbfrage(g,iBegriff,ShowAbfrage.cstBegriff);
		//boolean bAlle=(Abf.iBits & (Abfrage.cstMengen+Abfrage.cstKeinStamm))>0;
                boolean bAlle=VecE != null || (Abf.iBits&Abfrage.cstMengen)>0;// || (Abf.iBits&Abfrage.cstKeinStamm)>0 && (Abf.iBits&Abfrage.cstFremdStamm)==0;
                //Tabellenspeicher TabSpalten=Abf.getSpalten();// 10.3.2005 entfernt
                Vector VecG=null;
                if ((iDFBits&Drucken.cstSeiteProGruppe)>0)
                {
                  VecG=new Vector();
                  fillVecP(NodGroup,VecG);
                  //g.progInfo(VecG.size()+":"+VecG);
                }
		int iGaugeCount=0;
		int iPmax=VecG == null ? 1:VecG.size();
		///Gauge gauge=new Gauge(0,iPmax*(bAlle ? 1:TabStamm.getAnzahlElemente(null)),"",g,true);

                boolean bH_dez=g.bH_dez;
                boolean bH_min=g.bH_min;
                g.bH_dez=(iDFBits&Drucken.cstART_H)==Drucken.cstH_DEZ;
                g.bH_min=(iDFBits&Drucken.cstART_H)==Drucken.cstH_MIN;


		//dh.setTitle(sTitel,PrintManagerA.CENTER);
                boolean bFirstStamm=true;
                int iSync=-1;
                if ((Abf.iBits&Abfrage.cstFremdStamm)>0)
                {
                  int iPos=g.TabStammtypen.getPos("Aic",iStammtyp);
                  iSync= iPos>=0 ? g.TabStammtypen.getI(iPos,"Sync"):-1;
                }
                for (int iP = 0; iP < iPmax; iP++)
                {
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
                  String sDrucktitel=sTitel;
                  if (VecG != null)
                  {
                    sDrucktitel = (sDrucktitel == null ? "" : sDrucktitel + " - ") + Sort.gets(((JCOutlinerNode)VecG.elementAt(iP)).getLabel());
                    bFirstStamm=true;
                  }
                  dh.setDTitel(sDrucktitel,false,g.fontTitel);
		for(TabStamm.moveFirst();!TabStamm.eof()/* && !gauge.bEscape*/;TabStamm.moveNext())
		{
                  ///gauge.setText(TabStamm.getS("bezeichnung"),iGaugeCount);
                  if ((Abf.iBits&Abfrage.cstFremdStamm)>0)
                    g.setSyncStamm(iStammtyp,TabStamm.getI("AIC"));
                  Tabellenspeicher TabDaten= VecE != null ? null:Abf.getDaten(iStammtyp,TabStamm.getI("AIC"),(Abf.iBits&Abfrage.cstMengen)>0 ? TabStamm.getVecSpalte("AIC"):null,null);
                  if (VecE != null || TabDaten != null && !TabDaten.isEmpty())
                  {
                    if ((Abf.iBits&Abfrage.cstMengen)==0)// && ((Abf.iBits&Abfrage.cstKeinStamm)==0 || (Abf.iBits&Abfrage.cstFremdStamm)>0))
                      dh.setSTitel(TabStamm.getS("bezeichnung"),(iDFBits & Drucken.cstStammLinks)>0);
                    dh.printTitel(!bFirstStamm && (iDFBits&Drucken.cstSeitenumbruch)>0,bFirstStamm,true,false);
                    bFirstStamm=false;

			AUOutliner GidGesamt = new AUOutliner();
			ShowAbfrage.initOutliner(g,GidGesamt);
                        if (VecE==null)
                          Abf.TabToOutliner(GidGesamt,TabDaten,null);
                        else
                        {
                          GidGesamt.setRootNode(NodGroup);
                          DruckHS.showOutliner(GidGesamt);
                          int iAic = 0;
                          //GidGesamt.folderChanged(GidGesamt.getRootNode());
                          for (JCOutlinerFolderNode Nod = (JCOutlinerFolderNode)GidGesamt.getRootNode(); Nod != null; Nod = (JCOutlinerFolderNode)GidGesamt.getNextNode(Nod))
                          {
                            //g.progInfo("showPrint1:"+Nod+"/"+Nod.getLevel()+"/"+Nod.getUserData()+"/"+iStammtyp);
                            if ( Nod.getLevel()==0)
                              ;
                            else if (iStammtyp == ((Integer)((Vector)Nod.getUserData()).elementAt(1)).intValue())
                            {
                              /*if (iAic==0)
                                for(int i2=(Abf.iBits&Abf.cstVerdichten)==0?0:1;i2<Nod.getLevel();i2++)
                                {
                                  g.progInfo("Hinzu:"+i2);
                                  VecBreite.insertElementAt(new Integer(0),0);
                                }*/
                              Nod.setState((Abf.iBits&Abfrage.cstVerdichten)==0 ? BWTEnum.FOLDER_OPEN_ALL:BWTEnum.FOLDER_CLOSED);
                              iAic = ((Integer)((Vector)Nod.getUserData()).elementAt(0)).intValue();
                              //g.progInfo("showPrint2:"+Nod+"/"+iAic);
                              Vector Vec=(Vector)((Vector)Nod.getLabel()).clone();
                              //for (int i3=1;i3<Vec.size();i3++)
                              //  Vec.setElementAt(i3,null);
                              Nod.setLabel(Vec);
                              TabDaten=Abf.getDaten(iStammtyp, iAic, null,null);
                              if (TabDaten != null)
                                Abf.TabToOutliner(GidGesamt, TabDaten, (iDFBits&Drucken.cstLetzteGruppeWeg)>0 ? Nod.getParent():Nod,VecE,1);
                              if((iDFBits&Drucken.cstLetzteGruppeWeg)>0)
                                          Nod.getParent().removeChild(Nod);
                            }
                            else
                            {
                              Vector Vec=(Vector)((Vector)Nod.getLabel()).clone();
                              //for (int i3=1;i3<Vec.size();i3++)
                              //  Vec.setElementAt(i3,null);
                              Nod.setLabel(Vec);
                            }
                          }
                        }
                        //GidGesamt.folderChanged(GidGesamt.getRootNode());
                        DruckHS.showOutliner(GidGesamt);
			//String sTitelStamm=(bMengen?"":TabStamm.getInhalt("bezeichnung") instanceof Vector?""+((Vector)TabStamm.getInhalt("bezeichnung")).elementAt(0):TabStamm.getS("bezeichnung"));
			dh.addOutliner(GidGesamt,Abf,iAIC_Raster,-1,iDFBits,Abf.getTabDruckbreite(VecE==null ? 0:VecE.size()));
                  }
                  if(bAlle)
                    TabStamm.moveLast();
                  iGaugeCount++;
		 }
                 g.clockInfo("Drucke Abfrage " + sDrucktitel/*+" - "+TabStamm.getS("bezeichnung")*/, lClock);
                }
                if (iSync>-1)
                  g.setSyncStamm(iStammtyp,iSync);
                g.bH_dez=bH_dez;
                g.bH_min=bH_min;
                //gauge.setText("fertig",gauge.getMax());
                if ((iDFBits & Drucken.cstPDF_Art) == Drucken.cstPDF)
                  dh.printPDF(sPDF_File,1,dh.pages(),sPDF_PW,null);
                else if ((iDFBits&Drucken.cstSeitenvorschau)>0)
                  dh.vorschau();
                else
                  dh.print();
                g.endThread(Thread.currentThread());
	}

	private Global g;
	private String sTitel;
        private String sPDF_File;
        private String sPDF_PW;
	private int iBegriff;
	private int iStammtyp;
	private int iAIC_Raster=0;
	private Tabellenspeicher TabStamm;
	//private String sKennung;
        private int iLayout;
        private int iDFBits;
        private JCOutlinerFolderNode NodGroup;
        private Vector VecE;
}
