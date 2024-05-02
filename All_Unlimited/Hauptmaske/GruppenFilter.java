/*
    All_Unlimited-Hauptmaske-GruppenFilter.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;

public class GruppenFilter extends All_Unlimited.Allgemein.Formular
{
	public static GruppenFilter start(Tabellenspeicher Tab,Global glob)
	{
		TabOriginal = Tab;
		//TabOriginal.showGrid("TabOriginal");
		return This==null?new GruppenFilter(glob):This;
	}

	public void show()
	{
		TabCopy = new Tabellenspeicher(g,new String[] {"Knoten","int2","int3","int4","aic_abfrage","aic_ansicht","ans_aic_ansicht"});
		fillOutliners();
                //TabCopy.showGrid("TabCopy");
		super.show();
	}

	public static void free()
	{
		if (This!=null)
		{
			This.g.testInfo("GruppenFilter.free");
			This.thisFrame.dispose();
			This=null;
		}
	}

	private GruppenFilter(Global glob)
	{
		super("GruppenFilter",glob.getFomLeer(),glob);
		g=glob;
		This=this;

		BtnLeft=getButton("<");
                BtnErsetzen=getButton("Ersetzen");
		BtnRight=getButton(">");
                BtnShow = getButton("show");
                BtnShow.setVisible(g.Def());
		OutShow = new AUOutliner(NodRootShow);
		OutShow.setColumnLabelSortMethod(Sort.sortMethod);
		OutShow.setRootVisible(false);
		OutShow.setBackground(Color.white);
		/*JCOutlinerFolderNode NodRootGruppeBEW=new JCOutlinerFolderNode("");
		NodRootGruppeBEW.addNode(NodRootGruppen);
		NodRootGruppeBEW.addNode(NodRootBEW);*/
                /*NodRootGruppen.setState(BWTEnum.FOLDER_CLOSED);
                NodRootBEW.setState(BWTEnum.FOLDER_CLOSED);
                NodRootBEW.setLabel(g.getBegriff("Show","Bewegung"));
		NodRootGruppen.setLabel(g.getBegriff("Show","Gruppe"));*/
		OutGruppen = new AUOutliner(NodRootGruppen);
		OutGruppen.setRootVisible(false);
                OutBew = new AUOutliner(NodRootBEW);
                OutBew.setRootVisible(false);
		OutFilter = new AUOutliner(NodRootFilter);
		OutFilter.setRootVisible(false);
                OutSub = new AUOutliner(NodRootSub);
		OutSub.setRootVisible(false);
                OutMF = new AUOutliner(NodRootMF);
		OutMF.setRootVisible(false);
                iMF=g.getBegriffAicS("Datentyp","Mehrfach");
		//OutFilter.setBackground(Color.white);

		Static.addContainer(getFrei("Outliner Alles"),OutShow);
		Static.addContainer(getFrei("Outliner Gruppen"),OutGruppen);
                Static.addContainer(getFrei("Outliner Bewegung"),OutBew);
		Static.addContainer(getFrei("Outliner Filter"),OutFilter);
                Static.addContainer(getFrei("Outliner Mehrfach"),OutMF);
                Static.addContainer(getFrei("Outliner Sub"),OutSub);
                //if (!g.Def())
                //  OutSub.getParent().setVisible(false);

		Static.addActionListener(BtnRight,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Loeschen();
			}
		});

                Static.addActionListener(BtnShow,new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                TabCopy.showGrid("TabCopy",thisFrame);
                        }
                });

		Static.addActionListener(BtnLeft,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                          EnableButtons();
                          if (BtnLeft.isEnabled())
                          {
                            if (OutGruppen.isShowing())
                              Hinzufuegen(GRUPPE);
                            else if (OutBew.isShowing())
                              Hinzufuegen(BEW);
                            else if (OutSub.isShowing())
                              Hinzufuegen(SUB);
                            else if (OutMF.isShowing())
                              Hinzufuegen(MF);
                            /*
                                                       int iArt=((Integer)((Vector)OutGruppen.getSelectedNode().getUserData()).elementAt(2)).intValue();
                                 Hinzufuegen(iArt!=BEW && iArt!=BEWBEW);*/
                            fillOutlinerBEW();
                            EnableButtons();
                          }
			}
		});

                Static.addActionListener(BtnErsetzen,new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    Ersetzen();
                  }
                });

                JButton BtnOk=getButton("Ok");
                ((JDialog)thisFrame).getRootPane().setDefaultButton(BtnOk);
		Static.addActionListener(BtnOk,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Save();
				bOk=true;
				hide();
			}
		});
                JButton BtnAbbruch=getButton("Abbruch");
                Action cancelKeyAction = new AbstractAction() {
                  /**
					 *
					 */
					private static final long serialVersionUID = 4091688139252508723L;

				public void actionPerformed(ActionEvent e)
                  {
                    hide();
                  }
                };
                Static.Escape(BtnAbbruch,thisFrame,cancelKeyAction);

		Static.addActionListener(BtnAbbruch,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				hide();
			}
		});

                JCItemListener IL=new JCItemListener()
                {
                    public void itemStateChanged(JCItemEvent ev)
                    {
                        if(ev.getStateChange() == ItemEvent.SELECTED)
                        {
                          if (ev.getSource()==OutShow)
                          {
                            TabAnzahl.refresh(g);
                            fillOutlinerGruppen();
                            fillOutlinerBEW();
                            fillOutlinerMF();
                            fillOutlinerSub();
                          }
                          EnableButtons();
                        }
                    }
                };

		OutShow.addItemListener(IL);
		OutGruppen.addItemListener(IL);
                OutBew.addItemListener(IL);
                OutMF.addItemListener(IL);
                OutSub.addItemListener(IL);

		OutFilter.addItemListener(new JCOutlinerListener()
		{
			public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
			{
			}
			public void outlinerNodeSelectBegin(JCOutlinerEvent ev)
			{
			}
			public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
			{
			}
			public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
			{
					HideShow();
			}
			public void itemStateChanged(JCItemEvent ev)
			{
			}
		});

	}

	public boolean isOkConfirm()
	{
		return (bOk);
	}

	private void fillOutlinerGruppen()
	{
//        g.fixtestError("fillOutlinerGruppen");
		NodRootGruppen.removeChildren();
		JCOutlinerNode NodSelected = OutShow.getSelectedNode();
		if(Sort.geti(NodSelected.getUserData(),2)!=BEWGRUPPE)
		{
			TabCopy.posInhalt("Knoten",NodSelected);
			while(!TabCopy.eof() && TabCopy.getI("int4") == GRUPPE)
			{
				TabCopy.moveNext();
			}

			int int2 = !TabCopy.eof()?TabCopy.getI("int2"):0;
//			g.fixtestError("int2="+int2);
//			if (int2<0 && TabCopy.getI("int4")<2)
//			{
//				int2=g.RolleToStt(-int2);
//				g.fixtestError("wandle Rolle um von "+TabCopy.getI("int2")+" auf "+int2);
//			}
                        //g.progInfo("fillOutlinerGruppen:"+int2);
			TabCopy.movePrevious();
                        Vector<Integer> Vec = new Vector<Integer>();
			while(int2!=0 && !TabCopy.bof() && TabCopy.getI("int4") == GRUPPE)
			{
				Vec.addElement(new Integer(TabCopy.getI("int3")));
				TabCopy.movePrevious();
			}
                        Tabellenspeicher TabGruppen=TabGruppe1;
                        boolean bBew=Tabellenspeicher.geti(((Vector)NodSelected.getUserData()).elementAt(2))==BEW;
                        if (bBew)
                        {
                          String s="SELECT e.aic_eigenschaft, e.kennung, e.aic_stammtyp"+g.AU_Bezeichnung3("Eigenschaft","e")+
                              " from bew_zuordnung z"+g.join("eigenschaft","e","z","eigenschaft")+g.join("begriff","e","begriff")+
                              " where z.aic_bewegungstyp="+(-int2)+" and begriff.kennung in('BewStamm') order by bezeichnung";
                          //g.progInfo(s);
                          TabGruppen=new Tabellenspeicher(g,s,true);
//                          TabGruppen.showGrid("TabGruppen");
                        }
                        else
                          TabGruppen.posInhalt("Zugeordnet",new Integer(int2));
			for(;!TabGruppen.eof() && (bBew || TabGruppen.getI("Zugeordnet") == int2);TabGruppen.moveNext())
			{
				boolean bHinzufuegen=true;

				for(int i=0;i<Vec.size();i++)
					bHinzufuegen=bHinzufuegen && Vec.elementAt(i).intValue() != TabGruppen.getI("aic_eigenschaft");
                                int iSttMom=TabGruppen.getI("aic_stammtyp");
                                if(bHinzufuegen && (iSttMom<0 || posStt(iSttMom)))
				{
					Vector<Integer> VecInvisible = new Vector<Integer>();
					VecInvisible.addElement(new Integer(iSttMom));
					VecInvisible.addElement(new Integer(TabGruppen.getI("aic_eigenschaft")));
					VecInvisible.addElement(new Integer(bBew?BEWGRUPPE:GRUPPE));

					JCOutlinerFolderNode Nod = new JCOutlinerFolderNode(g.TabEigenschaften.getBezeichnungS(TabGruppen.getI("aic_eigenschaft"))/*Static.beiLeer(TabGruppen.getS("Bezeichnung"),TabGruppen.getS("Kennung"))*/,NodRootGruppen);
//					g.fixtestError("OutGruppen dazu:"+Nod.getLabel());
					Nod.setUserData(VecInvisible);
					Image Gif = g.getSttGif(iSttMom);
//					if(Gif!=null)
//					{
//						JCOutlinerNodeStyle StyFolderFormular=new JCOutlinerNodeStyle();
//						StyFolderFormular.setFolderClosedIcon(Gif);
//						StyFolderFormular.setFolderOpenIcon(Gif);
//						Nod.setStyle(StyFolderFormular);
//					}
					Nod.setStyle(g.getStyle(Gif));
				}
			}
		}
                OutGruppen.selectNode(NodRootGruppen,null);
		OutGruppen.folderChanged(NodRootGruppen);
	}

	private void fillOutlinerBEW()
	{
          //g.progInfo("fillOutlinerBEW");
          if (!g.Abfrage())
            return;
		NodRootBEW.removeChildren();
		JCOutlinerFolderNode NodeShow = (JCOutlinerFolderNode)OutShow.getSelectedNode();
		Integer iStt = (Integer)((Vector)NodeShow.getUserData()).elementAt(0);
		int iArt = ((Integer)((Vector)NodeShow.getUserData()).elementAt(2)).intValue();
		if(iArt!=BEWGRUPPE)
		{
                  //g.progInfo("fillOutlinerBEW:"+iStt);
			SQL Qry = new SQL(g);
                  String s=iArt==BEW ? " where eigenschaft.aic_bewegungstyp="+(-iStt.intValue()):" where aic_stammtyp="+iStt+" or eigenschaft.aic_begriff="+g.getBegriffAicS("Datentyp","BewHierarchie");
			if(Qry.open("select distinct b.aic_bewegungstyp,b.aic_eigenschaft,aic_stammtyp from eigenschaft"+g.join("bew_zuordnung","b","eigenschaft","eigenschaft")+s+" order by b.aic_bewegungstyp")&&!Qry.eof())
			{
				for(;!Qry.eof();Qry.moveNext())
				{
				  int iBew=Qry.getI("aic_bewegungstyp");
                                  int iPosB=g.TabErfassungstypen.getPos("Aic",iBew);
                                  int iPosE=g.TabEigenschaften.getPos("Aic",Qry.getI("aic_eigenschaft"));
					if(g.existsBew(iBew) && iPosB>=0 && (Qry.getI("aic_stammtyp")==iStt || g.InHierarchie(Qry.getI("aic_stammtyp"),iStt))
                                           && (g.TabErfassungstypen.getI(iPosB,"bits")&Global.cstNurModell)==0 && iPosE>=0)
					{
						Vector<Integer> VecInvisible=new Vector<Integer>();
						VecInvisible.addElement(new Integer(-Qry.getI("aic_bewegungstyp")));
						VecInvisible.addElement(new Integer(Qry.getI("aic_eigenschaft")));
						VecInvisible.addElement(new Integer(iArt==BEW ? BEWBEW:BEW));
						JCOutlinerFolderNode Node = new JCOutlinerFolderNode(g.TabErfassungstypen.getS(iPosB,"Bezeichnung")+" . "+g.TabEigenschaften.getS(iPosE,"Bezeichnung"),NodRootBEW);
						Node.setUserData(VecInvisible);
                                                Node.setStyle(g.getStyle(g.getBewGif(iBew)));
						/*Image Gif = (Image)g.TabErfassungstypen.getInhalt("Bild");
						if(Gif!=null)
						{
							JCOutlinerNodeStyle StyNode=new JCOutlinerNodeStyle();
							StyNode.setFolderClosedIcon(Gif);
							StyNode.setFolderOpenIcon(Gif);
							Node.setStyle(StyNode);
						}*/
					}
				}
				Qry.close();
			}
			Qry.free();
		}
		OutBew.sortByColumn(0,Sort.sortMethod);
                OutBew.selectNode(NodRootBEW,null);
		OutBew.folderChanged(NodRootBEW);
	}

        private void fillOutlinerMF()
        {
          //g.progInfo("fillOutlinerMF");
          NodRootMF.removeChildren();
          JCOutlinerFolderNode NodeShow = (JCOutlinerFolderNode)OutShow.getSelectedNode();
          Integer iStt = (Integer)((Vector)NodeShow.getUserData()).elementAt(0);
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select e.aic_stammtyp,z.aic_eigenschaft from stammtyp_zuordnung z join eigenschaft e"+
                                                    " on z.aic_eigenschaft=e.aic_eigenschaft and e.aic_begriff="+iMF+" and z.aic_stammtyp="+iStt,true);
          for (Tab.moveFirst();!Tab.out();Tab.moveNext())
          {
            int iSttMom=Tab.getI("aic_stammtyp");
            if (posStt(iSttMom))
            {
              Vector<Integer> VecInvisible = new Vector<Integer>();
              VecInvisible.addElement(new Integer(iSttMom));
              VecInvisible.addElement(new Integer(Tab.getI("aic_eigenschaft")));
              VecInvisible.addElement(new Integer(MF));
              JCOutlinerFolderNode Node = new JCOutlinerFolderNode(g.TabEigenschaften.getBezeichnungS(Tab.getI("aic_eigenschaft")), NodRootMF);
              Node.setUserData(VecInvisible);
              Node.setStyle(g.getStyle(g.getSttGif(iSttMom)));
            }
          }
          OutMF.sortByColumn(0,Sort.sortMethod);
          OutMF.selectNode(NodRootMF,null);
          OutMF.folderChanged(NodRootMF);
        }

        private void fillOutlinerSub()
        {
          //g.progInfo("fillOutlinerSub");
          NodRootSub.removeChildren();
          JCOutlinerFolderNode NodeShow = (JCOutlinerFolderNode)OutShow.getSelectedNode();
          Integer iStt = (Integer)((Vector)NodeShow.getUserData()).elementAt(0);
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select z.aic_stammtyp,z.aic_eigenschaft from stammtyp_zuordnung z join eigenschaft e"+
                                                    " on z.aic_eigenschaft=e.aic_eigenschaft and e.aic_begriff<>"+iMF+" and e.aic_stammtyp="+iStt,true);
          for (Tab.moveFirst();!Tab.out();Tab.moveNext())
          {
            int iSttMom=Tab.getI("aic_stammtyp");
            if (posStt(iSttMom))
            {
              Vector<Integer> VecInvisible = new Vector<Integer>();
              VecInvisible.addElement(new Integer(iSttMom));
              VecInvisible.addElement(new Integer(Tab.getI("aic_eigenschaft")));
              VecInvisible.addElement(new Integer(SUB));
              JCOutlinerFolderNode Node = new JCOutlinerFolderNode(g.TabStammtypen.getBezeichnungS(iSttMom) + "." +
                  g.TabEigenschaften.getBezeichnungS(Tab.getI("aic_eigenschaft")), NodRootSub);
              Node.setUserData(VecInvisible);
              Node.setStyle(g.getStyle(g.getSttGif(iSttMom)));
            }
          }
          OutSub.sortByColumn(0,Sort.sortMethod);
          OutSub.selectNode(NodRootSub,null);
          OutSub.folderChanged(NodRootSub);
        }

        private boolean posStt(int iSttMom)
        {
          return Static.bDefBezeichnung || TabAnzahl==null ||  TabAnzahl.posInhalt("aic_stammtyp",iSttMom);
        }

	private void fillOutliners()
	{
          TabAnzahl=new Tabellenspeicher(g,"select aic_stammtyp,count(*) Anzahl from stamm group by aic_stammtyp",true);
		//SQL Qry = new SQL(g);
		Image Gif;
		Vector<Object> VecGruppen=new Vector<Object>();
		JCOutlinerNodeStyle StyFolderFormular;
		iAICRootStammtyp = TabOriginal.getI("int1");
		TabOriginal.push();
		NodRootShow.removeChildren();
		NodRootFilter.removeChildren();
		JCOutlinerFolderNode NodVor = NodRootShow;
		//JCOutlinerFolderNode NodParent=null;

		for(;!TabOriginal.eof() && iAICRootStammtyp==TabOriginal.getI("int1");TabOriginal.moveNext())
		{
			int int2 = TabOriginal.getI("int2");
			int int3 = TabOriginal.getI("int3");
			int int4 = TabOriginal.getI("int4");
			if (int2<0 && int4<2)
				int2=g.RolleToStt(-int2);
                        int iPosB=int4==BEW || int4==BEWBEW ? g.TabErfassungstypen.getPos("Aic",new Integer(-int2)):-1;
                        int iPosS=iPosB<0 ? g.TabStammtypen.getPos("Aic",new Integer(int2)):-1;
			String sBezeichnung =  iPosB>=0 ?g.TabErfassungstypen.getS(iPosB,"Bezeichnung"):iPosS>=0 ? g.TabStammtypen.getS(iPosS,"Bezeichnung") : "-->Fehler";
			Gif = int4==BEW || int4==BEWBEW ? g.getBewGif(-int2):g.getSttGif(int2);

			if(int4<2)
			{
				JCOutlinerFolderNode Nod = new JCOutlinerFolderNode(sBezeichnung,NodRootFilter);
				Vector<Object> VecInvisible = new Vector<Object>();
				VecInvisible.addElement(new Integer(int2));
				VecInvisible.addElement(Gif);
				Nod.setUserData(VecInvisible);
				Nod.setStyle(g.getStyle(null));
                                if (int4>0 && !posStt(int2))
                                  int4=0;
				if(int4==0)
					Nod.setState(BWTEnum.FOLDER_CLOSED);
				else
					Nod.setState(BWTEnum.FOLDER_OPEN_ALL);
			}
                        if(int4>0 && (int2<0 || posStt(int2)))
			{
				//NodVor=NodVor==null?NodRootShow:NodVor;
                                TabCopy.push();
                                NodVor = TabOriginal.getI("ANS_AIC_ANSICHT")!=0 && TabCopy.posInhalt("AIC_ANSICHT",TabOriginal.getI("ANS_AIC_ANSICHT")) && !TabCopy.isNull("Knoten") ? (JCOutlinerFolderNode)TabCopy.getInhalt("Knoten"):NodVor;
                                TabCopy.pop();

				NodVor = new JCOutlinerFolderNode(sBezeichnung,NodVor);
				Vector<Integer> VecInvisible = new Vector<Integer>();
				VecInvisible.addElement(new Integer(int2));
				VecInvisible.addElement(new Integer(int3));
				VecInvisible.addElement(new Integer(int4));
				NodVor.setUserData(VecInvisible);
				if(Gif!=null)
				{
					StyFolderFormular=new JCOutlinerNodeStyle();
					StyFolderFormular.setFolderClosedIcon(Gif);
					StyFolderFormular.setFolderOpenIcon(Gif);
					NodVor.setStyle(StyFolderFormular);
				}

				TabCopy.addInhalt("Knoten",NodVor);
                                //NodParent=NodVor;
				/*TabOriginal.push();
                                //g.progInfo("fillOutliners:"+int4+"/"+TabOriginal.getI("int4")+"/"+(g.TabStammtypen.getI("Bits")&g.cstEnde));
				if(int4==EIN&&!TabOriginal.eof())
					TabOriginal.moveNext();
                                g.progInfo("fillOutliners:"+int2+":"+int4+"/"+TabOriginal.getI("int4")+"/"+(g.TabStammtypen.getI("Bits")&g.cstEnde));
				if((int4==EIN&&(g.TabStammtypen.getI("Bits")&g.cstEnde)>0&&TabOriginal.getI("int1")==iAICRootStammtyp&&TabOriginal.getI("int4")!=BEW)||int4==BEW||int4==BEWGRUPPE)
					NodVor=NodParent;
				else if(int4==EIN&&(g.TabStammtypen.getI("Bits")&g.cstEnde)==0)
					NodParent = NodVor;

				TabOriginal.pop();*/

			}
			else
				TabCopy.addInhalt("Knoten",null);

			TabCopy.addInhalt("int2",new Integer(int2));
			TabCopy.addInhalt("int3",new Integer(int3));
			TabCopy.addInhalt("int4",new Integer(int4));
                        TabCopy.addInhalt("aic_abfrage",TabOriginal.getInhalt("aic_abfrage"));
                        TabCopy.addInhalt("aic_ansicht",TabOriginal.getInhalt("aic_ansicht"));
                        TabCopy.addInhalt("ans_aic_ansicht",TabOriginal.getInhalt("ans_aic_ansicht"));
                        
			VecGruppen.addElement(int2);//TabOriginal.getInhalt("int2"));
		}
//		g.fixtestError("VecGruppen="+VecGruppen);
		TabGruppe1=new Tabellenspeicher(g,"SELECT e.aic_eigenschaft, e.kennung, e.aic_stammtyp, z.aic_stammtyp Zugeordnet"+g.AU_Bezeichnung3("Eigenschaft","e")+",e.aic_begriff from stammtyp_zuordnung z"+g.join("eigenschaft","e","z","eigenschaft")+g.join("begriff","e","begriff")+" where z.aic_stammtyp"+Static.SQL_in(VecGruppen)+" and begriff.kennung in('Gruppe','Hierarchie','Firma') order by z.aic_stammtyp,bezeichnung",true);
        TabGruppe1.showGrid("TabGruppe1");
		OutShow.setColumnWidth(0,200);
		OutGruppen.folderChanged(NodRootGruppen);
		OutFilter.folderChanged(NodRootFilter);
		OutShow.folderChanged(NodRootShow);

		TabOriginal.pop();
	}

	private void EnableButtons()
	{
		JCOutlinerNode Nod = OutShow.getSelectedNode();
		Vector Vec = (Vector)Nod.getUserData();
		int int4 = Nod.getLevel()>0?((Integer)Vec.elementAt(2)).intValue():-1;

		BtnLeft.setEnabled(OutGruppen.isShowing() && OutGruppen.getSelectedNode().getLevel()==1 || OutBew.isShowing() && OutBew.getSelectedNode().getLevel()==1
                    || OutMF.isShowing() && OutMF.getSelectedNode().getLevel()==1 || OutSub.isShowing() && OutSub.getSelectedNode().getLevel()==1);
                BtnErsetzen.setEnabled(OutGruppen.isShowing() && OutGruppen.getSelectedNode()!=null && OutShow.getSelectedNode()!=null &&
                                       Tabellenspeicher.geti(((Vector)OutShow.getSelectedNode().getUserData()).elementAt(2))==GRUPPE);
		BtnRight.setEnabled(int4>=3);
	}

	private void HideShow()
	{

		JCOutlinerFolderNode Nod = (JCOutlinerFolderNode)OutFilter.getSelectedNode();
		if(Nod!=null && Nod.getLevel()>0)
		{
			Vector VecNod = (Vector)Nod.getUserData();
			int iState = Nod.getState();

			TabCopy.posInhalt("int2",(Integer)VecNod.elementAt(0));
			TabCopy.setInhalt("int4",new Integer(iState==BWTEnum.FOLDER_CLOSED?0:1));
			//if(iState==BWTEnum.FOLDER_CLOSED)
			//{
				//Vector VecChildren=((JCOutlinerFolderNode)TabCopy.getInhalt("Knoten")).getChildren();
                                //g.progInfo("VecChildren="+VecChildren);
				//JCOutlinerFolderNode NodeChild = VecChildren!=null?(JCOutlinerFolderNode)VecChildren.elementAt(0):null;
				/*if(NodeChild!=null&&((Integer)((Vector)NodeChild.getUserData()).elementAt(2)).intValue()==4)
				{
					TabCopy.posInhalt("Knoten",NodeChild);
					TabCopy.clearInhalt();
				}*/
			//}

			UpToDate();
		}
	}

	private void UpToDate()
	{
		Image Gif;
		JCOutlinerNodeStyle StyFolderFormular;
		JCOutlinerFolderNode NodVor=null;// = NodRootShow==null?(JCOutlinerFolderNode)OutShow.getRootNode():NodRootShow;
		//JCOutlinerFolderNode NodParent=NodVor;
		NodRootShow.removeChildren();
		for(TabCopy.moveFirst();!TabCopy.eof();TabCopy.moveNext())
		{
			int int2 = TabCopy.getI("int2");
			int int3 = TabCopy.getI("int3");
			int int4 = TabCopy.getI("int4");

			String sBezeichnung = int4==BEW ? g.TabErfassungstypen.getBezeichnungS(-int2):g.TabStammtypen.getBezeichnungS(int2);
			Gif = int4==BEW ? g.getBewGif(-int2):g.getSttGif(int2);

			if(int4>AUS)
			{
                          TabCopy.push();
                          NodVor = TabCopy.getI("ANS_AIC_ANSICHT")==0 ? NodRootShow:TabCopy.posInhalt("AIC_ANSICHT",TabCopy.getI("ANS_AIC_ANSICHT")) && !TabCopy.isNull("Knoten") ? (JCOutlinerFolderNode)TabCopy.getInhalt("Knoten"):NodVor;
                          if (NodVor == null)
                            NodVor=NodRootShow;
                          TabCopy.pop();
				NodVor = new JCOutlinerFolderNode(sBezeichnung,NodVor);
				Vector<Integer> VecInvisible = new Vector<Integer>();
				VecInvisible.addElement(new Integer(int2));
				VecInvisible.addElement(new Integer(int3));
				VecInvisible.addElement(new Integer(int4));
				NodVor.setUserData(VecInvisible);
				if(Gif!=null)
				{
					StyFolderFormular=new JCOutlinerNodeStyle();
					StyFolderFormular.setFolderClosedIcon(Gif);
					StyFolderFormular.setFolderOpenIcon(Gif);
					NodVor.setStyle(StyFolderFormular);
				}

				TabCopy.setInhalt("Knoten",NodVor);

				/*TabCopy.push();
                                //NodVor = TabCopy.getI("ANS_AIC_ANSICHT")==0 ? NodRootShow:(JCOutlinerFolderNode)TabCopy.getInhalt("Knoten");
				if(int4==EIN&&!TabCopy.eof())
					TabCopy.moveNext();
				if((int4==EIN&&(g.TabStammtypen.getI("Bits")&g.cstEnde)>0&&TabCopy.getI("int4")<BEW)||int4==BEW)
					NodVor=NodParent;
				else if(int4==EIN&&(g.TabStammtypen.getI("Bits")&g.cstEnde)==0)
					NodParent = NodVor;
				TabCopy.pop();*/
			}
			else
				TabCopy.setInhalt("Knoten",null);
		}
		OutShow.folderChanged(NodRootShow);
	}

        private void Ersetzen()
        {
          JCOutlinerFolderNode NodSelected = (JCOutlinerFolderNode)OutShow.getSelectedNode();
          JCOutlinerNode NodGruppen = OutGruppen.isShowing() ? OutGruppen.getSelectedNode():OutBew.getSelectedNode();
          Vector VecInvisible = (Vector)NodGruppen.getUserData();
          TabCopy.posInhalt("Knoten",NodSelected);
          TabCopy.setInhalt("int2",new Integer(((Integer)VecInvisible.elementAt(0)).intValue()));
          TabCopy.setInhalt("int3",new Integer(((Integer)VecInvisible.elementAt(1)).intValue()));
          NodSelected.setLabel(NodGruppen.getLabel());
          NodSelected.setUserData(VecInvisible);
          OutShow.folderChanged(NodRootShow);
        }

	private void Hinzufuegen(int iTyp)
	{
          //g.progInfo("Hinzufuegen:"+iTyp);
		JCOutlinerFolderNode NodSelected = (JCOutlinerFolderNode)OutShow.getSelectedNode();
		JCOutlinerNode NodGruppen = iTyp==GRUPPE ? OutGruppen.getSelectedNode():iTyp==BEW ? OutBew.getSelectedNode():
                    iTyp==MF ? OutMF.getSelectedNode():iTyp==SUB ? OutSub.getSelectedNode():null;
		Vector VecInvisible = (Vector)NodGruppen.getUserData();

		JCOutlinerFolderNode NodNew = new JCOutlinerFolderNode(NodGruppen.getLabel(),iTyp==GRUPPE ? NodSelected.getParent():NodSelected);
		NodNew.setUserData(VecInvisible);
		NodNew.setStyle(NodGruppen.getStyle());
		if(iTyp==GRUPPE)
			NodNew.addNode(NodSelected);
		NodRootGruppen.removeChild(NodGruppen);

		TabCopy.posInhalt("int2",(Integer)((Vector)NodSelected.getUserData()).elementAt(0));
                int iAnsicht2=0;
		if(iTyp==GRUPPE)
		{
			TabCopy.bInsert=true;
		}
		else
		{
			TabCopy.moveNext();
			if(!TabCopy.out())
				TabCopy.bInsert=true;
		}
                if(!TabCopy.out())
                  iAnsicht2=TabCopy.getI("ans_aic_ansicht");
                else
                {
                  TabCopy.movePrevious();
                  iAnsicht2=TabCopy.getI("aic_ansicht");
                  TabCopy.moveNext();
                }
                if(!TabCopy.out() && iTyp==GRUPPE)
                {
                  TabCopy.push();
                  TabCopy.posInhalt("ans_aic_ansicht", iAnsicht2);
                  TabCopy.setInhalt("ans_aic_ansicht",iAnsicht);
                  TabCopy.pop();
                }

		TabCopy.addInhalt("Knoten",NodNew);
		TabCopy.addInhalt("int2",new Integer(((Integer)VecInvisible.elementAt(0)).intValue()));
		TabCopy.addInhalt("int3",new Integer(((Integer)VecInvisible.elementAt(1)).intValue()));
		TabCopy.addInhalt("int4",new Integer(((Integer)VecInvisible.elementAt(2)).intValue()));
                TabCopy.addInhalt("aic_abfrage",null);
                TabCopy.addInhalt("aic_ansicht",iAnsicht);
                TabCopy.addInhalt("ans_aic_ansicht",iAnsicht2);
		TabCopy.bInsert=false;
                iAnsicht--;

  //TabCopy.showGrid("TabCopy");

		OutShow.folderChanged(NodRootShow);
		OutGruppen.folderChanged(NodRootGruppen);

	}

	private void Loeschen()
	{
		JCOutlinerNode NodSelected = OutShow.getSelectedNode();

		JCOutlinerFolderNode NodNew = new JCOutlinerFolderNode(NodSelected.getLabel(),NodRootGruppen);
		NodNew.setUserData(NodSelected.getUserData());
		NodNew.setStyle(NodSelected.getStyle());

		if (TabCopy.posInhalt("Knoten",NodSelected))
                {
                  int iVor=TabCopy.getI("ans_aic_ansicht");
                  int iAic=TabCopy.getI("aic_ansicht");
                  TabCopy.clearInhalt();
                  if (TabCopy.posInhalt("ans_aic_ansicht",iAic))
                    TabCopy.setInhalt("ans_aic_ansicht",iVor);
                  //TabCopy.showGrid("");
                }
                else
                  Static.printError("GruppenFilter.Loeschen: Knoten "+NodSelected+" nicht gefunden");

		UpToDate();

		OutShow.folderChanged(NodRootShow);
		OutGruppen.folderChanged(NodRootGruppen);
	}

	private void Save()
	{
		while(!TabOriginal.eof() && TabOriginal.getI("int1")==iAICRootStammtyp)
			TabOriginal.clearInhalt();

		TabCopy.moveFirst();
		while(!TabCopy.eof())
		{
			TabOriginal.addInhalt("int1",new Integer(iAICRootStammtyp));
			TabOriginal.addInhalt("int2",TabCopy.getInhalt("int2"));
			TabOriginal.addInhalt("int3",TabCopy.getInhalt("int3"));
			TabOriginal.addInhalt("int4",TabCopy.getInhalt("int4"));
			TabOriginal.addInhalt("aic_abfrage",TabCopy.getInhalt("aic_abfrage"));
                        TabOriginal.addInhalt("aic_ansicht",TabCopy.getInhalt("aic_ansicht"));
                        TabOriginal.addInhalt("ans_aic_ansicht",TabCopy.getInhalt("ans_aic_ansicht"));
			TabCopy.moveNext();
		}


	}

// add your data members here
public static final int AUS=0;
public static final int EIN=1;
public static final int IMMER=2;
public static final int GRUPPE=3;
public static final int BEW=4;
public static final int BEWGRUPPE=5;
public static final int BEWBEW=6;
public static final int MF=7;
public static final int SUB=8;

private int iMF=0;

private Global g;
private static GruppenFilter This;
private boolean bOk=false;

private AUOutliner OutGruppen;
private JCOutlinerFolderNode NodRootGruppen = new JCOutlinerFolderNode("");
private AUOutliner OutBew;
private JCOutlinerFolderNode NodRootBEW = new JCOutlinerFolderNode("");
private AUOutliner OutSub;
private JCOutlinerFolderNode NodRootSub = new JCOutlinerFolderNode("");
private AUOutliner OutMF;
private JCOutlinerFolderNode NodRootMF = new JCOutlinerFolderNode("");
private AUOutliner OutFilter;
private JCOutlinerFolderNode NodRootFilter = new JCOutlinerFolderNode("");
private AUOutliner OutShow;
private JCOutlinerFolderNode NodRootShow = new JCOutlinerFolderNode("");

private JButton BtnLeft;
private JButton BtnErsetzen;
private JButton BtnRight;
private JButton BtnShow;

private static Tabellenspeicher TabOriginal;
private Tabellenspeicher TabAnzahl=null;
private Tabellenspeicher TabCopy;
private Tabellenspeicher TabGruppe1;
private int iAICRootStammtyp;
private int iAnsicht=-1000;
}

