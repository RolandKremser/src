/*
    All_Unlimited-Grunddefinition-Zuordnung.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.ComboSort;

public class Zuordnung extends All_Unlimited.Allgemein.Formular
{
	public Zuordnung(Global glob)
	{
		super("Zuordnung",null,glob);
		g=glob;
		if (!g.Def())
		{
			new Message(Message.WARNING_MESSAGE,null,g).showDialog("keineBerechtigung");
			thisFrame.dispose();
			return;
		}

		VecOut.addElement(OutGruppen);
		VecOut.addElement(OutEigenschaft);
		VecLbl.addElement(new JLabel());
		VecLbl.addElement(new JLabel());
		OutZuordnung = new AUOutliner(new JCOutlinerFolderNode("RootZuordnung"));
		OutZuordnung.setColumnLabelSortMethod(Sort.sortMethod);
		OutGruppen.setColumnLabelSortMethod(Sort.sortMethod);
		OutEigenschaft.setColumnLabelSortMethod(Sort.sortMethod);

		CboTabellen= new ComboSort(g);
                CboTabellen.setPreferredSize(new java.awt.Dimension(200,20));
                //CboTabellen.setFont(g.fontStandard);
		CboTabellen.fillDefTable("ZUORDNUNG",false);
		sTabVorher=CboTabellen.getSelectedKennung();

		Static.addContainer(getFrei("Outliner Gruppen"),OutGruppen);
		Static.addContainer(getFrei("Outliner Eigenschaft"),OutEigenschaft);
		Static.addContainer(getFrei("Outliner Alles"),OutZuordnung);
		Static.addContainer(getFrei("Combo Tabellen"),CboTabellen);
		Static.addContainer(getFrei("PnlLblGrp"),VecLbl.elementAt(0));
		Static.addContainer(getFrei("PnlLblEig"),VecLbl.elementAt(1));

		String[] sLabels = new String[] {g.getBegriffS("Show","Nr"),g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Info"),g.getBegriffS("Show","Kennung")};
		OutGruppen.setRootVisible(false);
		OutGruppen.setColumnButtons(sLabels);
		OutGruppen.setNumColumns(sLabels.length);
		OutGruppen.setColumnWidth(0,40);
		OutEigenschaft.setRootVisible(false);
		OutEigenschaft.setColumnButtons(sLabels);
		OutEigenschaft.setNumColumns(sLabels.length);
		OutEigenschaft.setColumnWidth(0,40);
		OutZuordnung.setRootVisible(false);
		OutGruppen.setAllowMultipleSelections(true);
		OutEigenschaft.setAllowMultipleSelections(true);
		OutZuordnung.setAllowMultipleSelections(true);

		BtnNewGrp = getButton("BtnNewGrp");
		if(BtnNewGrp==null) BtnNewGrp=new JButton();
		BtnNewEig = getButton("BtnNewEig");
		if(BtnNewEig==null) BtnNewEig=new JButton();
                //BtnOk = getButton("Ok");
                //BtnAbbruch = getButton("Abbruch");
		BtnSpeichern = getButton("Speichern");
                BtnBeenden = getButton("Beenden");

		BtnDrehen = getButton("Drehen");
		if(BtnDrehen==null) BtnDrehen=new JButton();
		BtnPfeilrechts = getButton(">");
		if(BtnPfeilrechts==null) BtnPfeilrechts=new JButton();
		BtnPfeillinks = getButton("<");
		if(BtnPfeillinks==null) BtnPfeillinks=new JButton();
		BtnPfeilunten = getButton("Pfeil unten");
		if(BtnPfeilunten==null) BtnPfeilunten=new JButton();
		BtnPfeiloben = getButton("Pfeil oben");
		if(BtnPfeiloben==null) BtnPfeiloben=new JButton();
		BtnSeperator = getButton("Seperator");
		if(BtnSeperator==null) BtnSeperator=new JButton();
		BtnDelAllSeperators = getButton("DelAllSeperators");
		if(BtnDelAllSeperators==null) BtnDelAllSeperators=new JButton();
                BtnShow = getButton("show");
                BtnRefresh = getButton("Refresh");

		Static.addActionListener(BtnNewGrp,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				DefinitionAufrufen(0);
			}
		});

		Static.addActionListener(BtnNewEig,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				DefinitionAufrufen(1);
			}
		});

                /*Static.addActionListener(BtnOk,new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                Speichern();
                                thisFrame.setVisible(false);
                        }
                });

		Static.addActionListener(BtnAbbruch,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				thisFrame.setVisible(false);
			}
		});*/

		Static.addActionListener(BtnSpeichern,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Speichern();
			}
		});

                Static.addActionListener(BtnShow,new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                TabZuordnung.showGrid();
                        }
                });

                Static.addActionListener(BtnBeenden,new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          if(BtnSpeichern.isEnabled() && (TabZuordnung.posInhalt("Status","new") || TabZuordnung.posInhalt("Status","del")))
                          {
                            int pane = new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Speichern", new String[] {"die Änderung"});
                            if(pane == Message.YES_OPTION)
                              Speichern();
                          }
                          hide();
                        }
                });

		Static.addActionListener(BtnDrehen,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Drehen();
			}
		});

		Static.addActionListener(BtnPfeilrechts,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Hinzufuegen();
			}
		});

		Static.addActionListener(BtnPfeillinks,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Loeschen();
			}
		});

		Static.addActionListener(BtnPfeilunten,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Verschieben(false);
			}
		});

		Static.addActionListener(BtnPfeiloben,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Verschieben(true);
			}
		});

		Static.addActionListener(BtnSeperator,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Seperator_Hinzufuegen();
			}
		});

		Static.addActionListener(BtnDelAllSeperators,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				deleteAllSeperators();
				fillOutliners(true);
			}
		});

                Static.addActionListener(BtnRefresh,new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          fillOutliners(false);
                        }
                });

		CboTabellen.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange()==ItemEvent.SELECTED)
				{
					//boolean bAenderung = TabZuordnung.posInhalt("Status","new");
					if (BtnSpeichern.isEnabled() &&
						(TabZuordnung.posInhalt("Status","new") || TabZuordnung.posInhalt("Status","del")))
					{
						int pane = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Speichern");
						if(pane == Message.YES_OPTION)
							Speichern();
					}

					fillOutliners(true);
					sTabVorher=CboTabellen.getSelectedKennung();
				}
			}
		});

		OutZuordnung.addItemListener(new JCOutlinerListener ()
		{
			public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
			{
				EnableButtons();
			}

			public void outlinerNodeSelectBegin(JCOutlinerEvent ev)
			{
			}

			public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
			{
			}

			public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
			{
			}

			public void itemStateChanged(JCItemEvent ev)
			{
			}
		});

		show();
	}

	public void show()
	{
		iDA=0;
		fillOutliners(bFirst);
                bFirst=false;
		sTabVorher=CboTabellen.getSelectedKennung();

		super.show();
	}

	private void DefinitionAufrufen(int iArt)
	{
                if (saryKennung[iArt].equals("EIGENSCHAFT"))
                  DefEigenschaft.get(g,0);
                else
                  new Definition(g,saryKennung[iArt]);
                /*
		Definition def=new Definition(g,saryKennung[iArt]);
		def.addWindowListener(new WindowListener()
		{
			public void windowOpened(WindowEvent ev)
			{
			}
			public void windowClosing(WindowEvent ev)
			{
			}
			public void windowClosed(WindowEvent ev)
			{
				((JCOutlinerFolderNode)OutGruppen.getRootNode()).removeChildren();
				((JCOutlinerFolderNode)OutEigenschaft.getRootNode()).removeChildren();
				fillOutliners();
				OutGruppen.folderChanged(OutGruppen.getRootNode());
				OutEigenschaft.folderChanged(OutEigenschaft.getRootNode());
			}
			public void windowIconified(WindowEvent ev)
			{
			}
			public void windowDeiconified(WindowEvent ev)
			{
			}
			public void windowActivated(WindowEvent ev)
			{
			}
			public void windowDeactivated(WindowEvent ev)
			{
			}
		});
                */
	}

	private void EnableButtons()
	{
		//g.progInfo("EnableButtons");
		JCOutlinerNode Node = OutZuordnung.getSelectedNode();

		BtnPfeiloben.setEnabled(bReihenfolge && Node!=null && Node.getLevel()==2);
		BtnPfeilunten.setEnabled(bReihenfolge && Node!=null && Node.getLevel()==2);
		String sKennung=CboTabellen.getSelectedKennung();
		boolean bSeperator=sKennung.equalsIgnoreCase("BEGRIFF") || sKennung.equalsIgnoreCase("EIGENSCHAFT");
		BtnSeperator.setEnabled(bSeperator && bReihenfolge);
		BtnDelAllSeperators.setEnabled(!bSeperator && bReihenfolge);
                //if (BtnOk != null)
                //  BtnOk.setEnabled(!sKennung.equals("BENUTZER") &&(!bReihenfolge || iGedreht<1));
                if (BtnSpeichern != null)
                  BtnSpeichern.setEnabled(!sKennung.equals("BENUTZER") &&(!bReihenfolge || iGedreht<1));

		/*
		getButton("BtnNewGrp").setEnabled(true);
		getButton("BtnNewEig").setEnabled(true);
		getButton("Abbruch").setEnabled(true);
		getButton("Ok").setEnabled(true);
		getButton("Drehen").setEnabled(true);
		getButton(">").setEnabled(true);
		getButton("<").setEnabled(true);
		*/
	}

	private void fillOutliners(boolean bFill)
	{
          ((JCOutlinerFolderNode)OutGruppen.getRootNode()).removeChildren();
          ((JCOutlinerFolderNode)OutEigenschaft.getRootNode()).removeChildren();

		SQL Qry = new SQL(g);

		if(Qry.open("SELECT * FROM Zuordnung WHERE AIC_Zuordnung="+CboTabellen.getSelectedAIC()) && !Qry.eof())
		{

			bReihenfolge= Qry.getI("Reihenfolge")==1;

			EnableButtons();
                        if (Qry.getI("AIC_Begriffgruppe")>0)
                        {
                          int iPosBG=g.TabBegriffgruppen.getPos("Aic",Qry.getI("AIC_Begriffgruppe"));
                          BtnNewGrp.setIcon(g.LoadImageIcon(g.TabBegriffgruppen.getS(iPosBG,"filename")));
                        }
                        else
                        {
                          //g.TabTabellenname.posInhalt("Aic",Qry.getI("AIC_Tabellenname"));
                          Image Gif=g.getGif(g.TabTabellenname, Qry.getI("AIC_Tabellenname"));
                          BtnNewGrp.setIcon(Gif==null?null:new ImageIcon(Gif));//g.TabTabellenname.isNull("Bild") ? null:new ImageIcon((Image)g.TabTabellenname.getInhalt("Bild")));
                        }
                        if (Qry.getI("Beg_AIC_Begriffgruppe")>0)
                        {
                          int iPosBG=g.TabBegriffgruppen.getPos("Aic",Qry.getI("Beg_AIC_Begriffgruppe"));
                          BtnNewEig.setIcon(g.LoadImageIcon(g.TabBegriffgruppen.getS(iPosBG,"filename")));
                        }
                        else
                        {
                          //g.TabTabellenname.posInhalt("Aic",Qry.getI("Tab_AIC_Tabellenname"));
                          Image Gif=g.getGif(g.TabTabellenname, Qry.getI("Tab_AIC_Tabellenname"));
                          BtnNewEig.setIcon(Gif==null?null:new ImageIcon(Gif));//g.TabTabellenname.isNull("Bild") ? null:new ImageIcon((Image)g.TabTabellenname.getInhalt("Bild")));
                        }


			fillOutliner(0,Qry.getI("AIC_Tabellenname"),Qry.getI("AIC_Begriffgruppe"));
			fillOutliner(1,Qry.getI("Tab_AIC_Tabellenname"),Qry.getI("Beg_AIC_Begriffgruppe"));
                        if (bFill)
                          Laden(Qry.getS("Kennung"),Qry.getI("Beg_AIC_Begriffgruppe"),Qry.getI("AIC_Begriffgruppe"));
		}

		Qry.free();
                OutGruppen.folderChanged(OutGruppen.getRootNode());
                OutEigenschaft.folderChanged(OutEigenschaft.getRootNode());
	}


	private void fillOutliner(int rint,int riTab, int riBegriffgruppe)
	{

		int iPosT=g.TabTabellenname.getPos("AIC",new Integer(riTab));
		saryKennung[rint]=g.TabTabellenname.getS(iPosT,"Kennung");
		VecLbl.elementAt(rint).setText(riBegriffgruppe==0 ? (String)g.TabTabellenname.getInhalt("Bezeichnung",iPosT) : g.TabBegriffgruppen.getBezeichnungS(riBegriffgruppe));

		TabGrpEig[rint]=new Tabellenspeicher(g,"SELECT AIC_"+saryKennung[rint]+",Kennung"+(saryKennung[rint].equals("BEGRIFF")?",DefBezeichnung Bezeichnung":g.AU_Bezeichnung(saryKennung[rint]))+
			" FROM "+saryKennung[rint]+(riBegriffgruppe!=0 ? " where aic_begriffgruppe="+riBegriffgruppe:""),true);

		TabGrpEig[rint].checkBezeichnung();
		TabGrpEig[rint].sAIC="AIC_"+saryKennung[rint];

		//if(g.Prog())
		//	TabGrpEig[rint].showGrid("TabGrpEig");
		JCOutlinerNode NodRoot=VecOut.elementAt(rint).getRootNode();
		TabGrpEig[rint].moveFirst();
		JCOutlinerNodeStyle Sty = new JCOutlinerNodeStyle();
		Sty.setItemIcon(null);
		//g.progInfo(rint+".:"+saryKennung[rint]);
		for(;!TabGrpEig[rint].eof();TabGrpEig[rint].moveNext())
		{
			Vector<Comparable> VecVisible = new Vector<Comparable>();
			Vector<Integer> VecInvisible = new Vector<Integer>();

			VecVisible.addElement(new Integer(TabGrpEig[rint].getI("AIC_"+saryKennung[rint])));
			VecVisible.addElement(TabGrpEig[rint].getS("Bezeichnung"));
			if(saryKennung[rint].equalsIgnoreCase("Eigenschaft"))
			{
				int iPosE=g.TabEigenschaften.getPos("AIC",TabGrpEig[rint].getI("AIC_"+saryKennung[rint]));
				VecVisible.addElement(g.TabEigenschaften.getS(iPosE,"Datentyp"));
                                int iPosD=g.getPosBegriff("Datentyp",g.TabEigenschaften.getS(iPosE,"Datentyp"));
				Image Gif = iPosD<0?null:g.LoadImage(g.TabBegriffe.getS(iPosD,"BildFile"));
				Sty=g.getStyle(Gif);
			}
			else if(saryKennung[rint].equalsIgnoreCase("Begriff"))
			{
				int iPosB=g.TabBegriffe.getPos("AIC",TabGrpEig[rint].getI("AIC_"+saryKennung[rint]));
                                if (g.TabBegriffe.getI(iPosB,"Stt")>0)
                                {
                                  int iPosS=g.TabStammtypen.getPos("AIC", g.TabBegriffe.getI(iPosB,"Stt"));
                                  VecVisible.addElement(g.TabStammtypen.getS(iPosS,"Bezeichnung"));

                                  //Image Gif = (Image)g.TabStammtypen.getInhalt("Bild");
                                  Sty = g.getStyle(g.getSttGif(g.TabBegriffe.getI(iPosB,"Stt")));
                                }
                                else
                                  VecVisible.addElement(null);
			}
			else
				VecVisible.addElement(null);
                        VecVisible.addElement(TabGrpEig[rint].getS("Kennung"));
			VecInvisible.addElement(new Integer(TabGrpEig[rint].getI("AIC_"+saryKennung[rint])));

			if(rint==0 || CheckAllowed(CboTabellen.getSelectedKennung(),(String)VecVisible.elementAt(2)))
			{
				JCOutlinerNode Nod = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)NodRoot);
				Nod.setUserData(VecInvisible);
				Nod.setStyle(Sty);
			}
		}
	}

	private boolean CheckAllowed(String sZuordnung, String sDatentyp)
	{
		if (sDatentyp==null)
			return true;
		//g.progInfo("CheckAllowed:"+sZuordnung+"/"+sDatentyp);
		boolean bAllowed = !(sZuordnung.equals("Stammtyp") && sDatentyp.startsWith("Bew"));

		bAllowed = bAllowed && !(sZuordnung.equals("BEW") && (sDatentyp.equals("Bezeichnung") || sDatentyp.equals("Kennung") || sDatentyp.equals("Eintritt") ||
			sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Gruppe")));

		return bAllowed;
	}

	private void Laden(String sKennung,int iBegriff,int iBegriff2)
	{
		//g.progInfo("Laden:"+sKennung);
		iDA=0;
		String sTab=(sKennung.equals("Kunde") || sKennung.equals("Menge") || sKennung.equals("Appl2") ?"Begriff":sKennung)+"_Zuordnung";
		saryAIC[0]="AIC_"+saryKennung[0];
		saryAIC[1]=saryKennung[0].equals(saryKennung[1]) ? saryKennung[1].substring(0,3)+"_AIC_"+saryKennung[1] : "AIC_"+saryKennung[1];
		SQL Qry = new SQL(g);
		if(Qry.open("SELECT "+sTab+".*"+(saryKennung[1].equalsIgnoreCase("Eigenschaft")?",(select aic_begriff from eigenschaft where aic_eigenschaft="+sTab+"."+saryAIC[1]+") as Info":
		saryKennung[0].equalsIgnoreCase("Begriff")?","+sTab+"."+saryAIC[0]+" Info":"")+
                ",(select kennung from "+saryKennung[0]+" where aic_"+saryKennung[0]+"="+sTab+"."+saryAIC[0]+") Kennung0"+
                ",(select kennung from "+saryKennung[1]+" where aic_"+saryKennung[1]+"="+sTab+"."+saryAIC[1]+") Kennung1"+
		" FROM "+sTab+(iBegriff==0?"":" where (select aic_begriffgruppe from begriff where aic_begriff="+sTab+".beg_aic_begriff)="+iBegriff)+
		(iBegriff==0 || iBegriff2==0?"":" and (select aic_begriffgruppe from begriff where aic_begriff="+sTab+".aic_begriff)="+iBegriff2)+
		" ORDER BY "+saryAIC[0]+(bReihenfolge ? ",Reihenfolge" : "")))
		{
			TabZuordnung=new Tabellenspeicher(g,new String[]{"Gruppe","Eigenschaft","Node Gruppe","Node Eigenschaft","Status","Reihenfolge","Info","Pic","Kennung0","Kennung1"});
			TabZuordnung.sAIC="Eigenschaft";
			while(!Qry.eof())
			{
				TabZuordnung.addInhalt("Gruppe",new Integer(Qry.getI(saryAIC[0])));
				TabZuordnung.addInhalt("Eigenschaft",new Integer(Qry.getI(saryAIC[1])));
				TabZuordnung.addInhalt("Node Gruppe",null);
				TabZuordnung.addInhalt("Node Eigenschaft",null);
				TabZuordnung.addInhalt("Status","");
				TabZuordnung.addInhalt("Reihenfolge",bReihenfolge ? new Integer(Qry.getI("Reihenfolge")):null);
                                int iPosB=saryKennung[1].equalsIgnoreCase("Eigenschaft") || saryKennung[1].equalsIgnoreCase("Begriff") ? g.TabBegriffe.getPos("Aic",Qry.getI("Info")):-1;
				TabZuordnung.addInhalt("Info",saryKennung[1].equalsIgnoreCase("Eigenschaft") && iPosB>=0?g.TabBegriffe.getS(iPosB,"Bezeichnung"):
					saryKennung[1].equalsIgnoreCase("Begriff") && iPosB>=0 ? g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPosB,"stt")):null);
                                TabZuordnung.addInhalt("Kennung0",/*Qry.getS(saryAIC[0])*/Qry.getS("Kennung0"));
                                TabZuordnung.addInhalt("Kennung1",/*Qry.getS(saryAIC[1])*/Qry.getS("Kennung1"));
                                int iPosE=saryKennung[1].equalsIgnoreCase("Eigenschaft") ? g.TabEigenschaften.getPos("AIC",Qry.getI(saryAIC[1])):-1;
				TabZuordnung.addInhalt("Pic",iPosE>=0 && !g.getBegriffS("Datentyp",g.TabEigenschaften.getS(iPosE,"Datentyp")).equals("")?g.LoadImage(g.TabBegriffe.getS(iPosB,"BildFile")):
											 saryKennung[1].equalsIgnoreCase("Begriff")?g.getSttGif(g.TabBegriffe.getI(iPosB,"stt")):null);
				//g.TabBegriffe.posInhalt("AIC",TabGrpEig[rint].getI("AIC_"+saryKennung[rint])
				//g.TabStammtypen.posInhalt("AIC",g.TabBegriffe.getI("Stt"));
				//VecVisible.addElement(g.TabStammtypen.getS("Bezeichnung"));
				//Image Gif = (Image)g.TabStammtypen.getInhalt("Bild");

				Qry.moveNext();
			}

			Qry.close();
		}
		else
			Static.printError("Zuordnung.Laden: Zuordnung <"+sTab+"> kann nicht geladen werden!!!");

		Qry.free();

		fuelleOutZuordnung();
		if (sKennung.equals("Begriff"))
			Drehen();
		iGedreht=0;
		EnableButtons();
		//if (g.Prog())
		//	TabZuordnung.showGrid();
	}

	private void fuelleOutZuordnung()
	{
		String[] sColumnButtons = bReihenfolge ? new String[] {"Zuordnung","Reihenfolge","Info","Kennung"}:new String[]{"Zuordnung","Info","Kennung"};
		OutZuordnung.setColumnButtons(sColumnButtons);
		OutZuordnung.setNumColumns(sColumnButtons.length);

		((JCOutlinerFolderNode)OutZuordnung.getRootNode()).removeChildren();
		int iGruppe=-1;
		int iMom=0;
		JCOutlinerFolderNode NodGruppe=null;

		for(TabZuordnung.moveFirst();!TabZuordnung.eof();TabZuordnung.moveNext())
		{
			if(iGruppe!=TabZuordnung.getI(sSpalte[iDA]))
			{
				iGruppe=TabZuordnung.getI(sSpalte[iDA]);
				Vector<Object> VecVisible = new Vector<Object>();
				Vector<Integer> VecInvisible = new Vector<Integer>();

				VecVisible.addElement(TabGrpEig[iDA].getBezeichnung(iGruppe));
                                VecVisible.addElement(null);
                                if(bReihenfolge)
                                  VecVisible.addElement(null);
                                VecVisible.addElement(TabZuordnung.getInhalt("Kennung"+iDA));
				VecInvisible.addElement(new Integer(iGruppe));

				NodGruppe = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutZuordnung.getRootNode());
				NodGruppe.setState(BWTEnum.FOLDER_CLOSED);
				NodGruppe.setUserData(VecInvisible);
				iMom=0;
			}

			Vector<Object> VecVisible = new Vector<Object>();
			Vector<Integer> VecInvisible = new Vector<Integer>();

			VecVisible.addElement(TabGrpEig[1-iDA].getBezeichnung(TabZuordnung.getI(sSpalte[1-iDA])));
			if(bReihenfolge)
			{
				int iReihenfolge=TabZuordnung.getI("Reihenfolge");
				iMom++;
				//int iKinder=NodGruppe.getChildren() != null ? NodGruppe.getChildren().size() : 0;

				//g.progInfo("Reihenfolge="+iReihenfolge+", Kinder="+iKinder);
				if(iReihenfolge < iMom)
				{
					//iReihenfolge=iKinder+1;
					g.progInfo(iReihenfolge+"->"+iMom);
					TabZuordnung.setInhalt("Reihenfolge",new Integer(iMom));
					TabZuordnung.setInhalt("Status","edit");
				}

				Vector<Integer> VecSepInvisible = new Vector<Integer>();
				VecSepInvisible.addElement(new Integer(0));
				for(;iMom<iReihenfolge;iMom++)
				{
					Vector<Comparable> VecSepVisible = new Vector<Comparable>();
					VecSepVisible.addElement("-------------");
					VecSepVisible.addElement(new Integer(iMom));
					JCOutlinerNode NodEigenschaft = new JCOutlinerNode(VecSepVisible,NodGruppe);
					NodEigenschaft.setUserData(VecSepInvisible);
				}
				/*for(iKinder+=1;iDA==0 && iKinder<iReihenfolge;iKinder++)
				{
					Vector VecSepVisible = new Vector();
					VecSepVisible.addElement("-------------");
					VecSepVisible.addElement(new Integer(iKinder));
					JCOutlinerNode NodEigenschaft = new JCOutlinerNode(VecSepVisible,NodGruppe);
					NodEigenschaft.setUserData(VecSepInvisible);
				}*/
				VecVisible.addElement(new Integer(iMom));
			}
			VecVisible.addElement(TabZuordnung.getInhalt("Info"));
                        VecVisible.addElement(TabZuordnung.getInhalt("Kennung"+(1-iDA)));
			VecInvisible.addElement(new Integer(TabZuordnung.getI(sSpalte[1-iDA])));

			TabZuordnung.setInhalt("Node Gruppe",NodGruppe);
			if(!(TabZuordnung.getS("Status").equals("del") || TabZuordnung.getS("Status").equals("new del")))
			{
				JCOutlinerNode NodEigenschaft = new JCOutlinerNode(VecVisible,NodGruppe);
				NodEigenschaft.setUserData(VecInvisible);

				NodEigenschaft.setStyle(g.getStyle((Image)TabZuordnung.getInhalt("Pic")));

				TabZuordnung.setInhalt("Node Eigenschaft",NodEigenschaft);
			}
		}
		OutZuordnung.folderChanged(OutZuordnung.getRootNode());
	}

	private void Hinzufuegen()
	{
		JCOutlinerNode[] NodesGruppen;
		JCOutlinerNode[] NodesEigenschaft;
		if(iDA==0)
		{
			NodesGruppen = OutGruppen.getSelectedNodes();
			NodesEigenschaft = OutEigenschaft.getSelectedNodes();
		}
		else
		{
			NodesGruppen = OutEigenschaft.getSelectedNodes();
			NodesEigenschaft = OutGruppen.getSelectedNodes();
		}


		for(int i=0;i<NodesGruppen.length;i++)
		{
			Integer iAIC_Gruppe = (Integer)((Vector)NodesGruppen[i].getUserData()).elementAt(0);
			JCOutlinerFolderNode NodGruppe;
			if(TabZuordnung.posInhalt(sSpalte[iDA],iAIC_Gruppe))
			{
				NodGruppe = (JCOutlinerFolderNode)TabZuordnung.getInhalt("Node Gruppe");
			}
			else
			{
				NodGruppe = new JCOutlinerFolderNode(((Vector)NodesGruppen[i].getLabel()).elementAt(1),(JCOutlinerFolderNode)OutZuordnung.getRootNode());
				NodGruppe.setUserData(NodesGruppen[i].getUserData());
			}



			for(int j=0;j<NodesEigenschaft.length;j++)
			{
				Vector<Object> VecVisible;
				if(!TabZuordnung.posInhalt((Object)iAIC_Gruppe,((Vector)NodesEigenschaft[j].getUserData()).elementAt(0)))
				{
					VecVisible=new Vector<Object>();
					VecVisible.addElement(((Vector)NodesEigenschaft[j].getLabel()).elementAt(1));
					if(bReihenfolge)
					{
						VecVisible.addElement(NodGruppe.getChildren()!=null ?new Integer(NodGruppe.getChildren().size()+1):new Integer(1));
						TabZuordnung.putInhalt("Reihenfolge",NodGruppe.getChildren()!=null ?new Integer(NodGruppe.getChildren().size()+1):new Integer(1),true);
					}
					else
						TabZuordnung.putInhalt("Reihenfolge",null,true);

					VecVisible.addElement(((Vector)NodesEigenschaft[j].getLabel()).elementAt(2));
					JCOutlinerNode NodEigenschaft = new JCOutlinerNode(VecVisible,NodGruppe);
					NodEigenschaft.setUserData(NodesEigenschaft[j].getUserData());

					TabZuordnung.putInhalt(sSpalte[iDA],((Vector)NodesGruppen[i].getUserData()).elementAt(0),true);
					TabZuordnung.putInhalt(sSpalte[1-iDA],((Vector)NodesEigenschaft[j].getUserData()).elementAt(0),true);
					TabZuordnung.putInhalt("Info",((Vector)NodesEigenschaft[j].getLabel()).elementAt(2),true);
					TabZuordnung.putInhalt("Node Gruppe",NodGruppe,true);
					TabZuordnung.putInhalt("Node Eigenschaft",NodEigenschaft,true);
					TabZuordnung.putInhalt("Status","new",true);
                                        int iPosE=g.TabEigenschaften.getPos("AIC",((Vector)NodesGruppen[i].getUserData()).elementAt(0));
                                        int iPosB=iPosE<0 ? -1:g.getPosBegriff("Datentyp",g.TabEigenschaften.getS(iPosE,"Datentyp"));
					TabZuordnung.putInhalt("Pic",iPosB>=0 ? g.LoadImage(g.TabBegriffe.getS(iPosB,"BildFile")):null,true);
				}
				else
				{
					if(TabZuordnung.getS("Status").equals("del"))
					{
						VecVisible=new Vector<Object>();
						VecVisible.addElement(((Vector)NodesEigenschaft[j].getLabel()).elementAt(1));
						if(bReihenfolge)
						{
							VecVisible.addElement(new Integer(NodGruppe.getChildren().size()+1));
							TabZuordnung.setInhalt("Reihenfolge",new Integer(NodGruppe.getChildren().size()+1));
						}
						TabZuordnung.setInhalt("Status","");
						JCOutlinerNode NodEigenschaft = new JCOutlinerNode(VecVisible,NodGruppe);
						NodEigenschaft.setUserData(NodesEigenschaft[j].getUserData());
						TabZuordnung.setInhalt("Node Eigenschaft",NodEigenschaft);
					}
					else if(TabZuordnung.getS("Status").equals("new del"))
					{
						VecVisible=new Vector<Object>();
						VecVisible.addElement(((Vector)NodesEigenschaft[j].getLabel()).elementAt(1));
						if(bReihenfolge)
						{
							VecVisible.addElement(new Integer(NodGruppe.getChildren().size()+1));
							TabZuordnung.setInhalt("Reihenfolge",new Integer(NodGruppe.getChildren().size()+1));
						}
						TabZuordnung.setInhalt("Status","new");
						JCOutlinerNode NodEigenschaft = new JCOutlinerNode(VecVisible,NodGruppe);
						NodEigenschaft.setUserData(NodesEigenschaft[j].getUserData());
						TabZuordnung.setInhalt("Node Eigenschaft",NodEigenschaft);
					}
					//TabZuordnung.setInhalt("Pic",null);
				}
			}
		}
		OutZuordnung.folderChanged(OutZuordnung.getRootNode());
	}

	private void Seperator_Hinzufuegen()
	{
		JCOutlinerNode[] NodesZuordnung = OutZuordnung.getSelectedNodes();

		for(int i=0;i<NodesZuordnung.length;i++)
		{
			Vector<Comparable> VecVisible = new Vector<Comparable>();
			Vector<Integer> VecInvisible = new Vector<Integer>();
			VecInvisible.addElement(new Integer(0));
			VecVisible.addElement("-------------");

			if(NodesZuordnung[i].getLevel()==1)
			{
				VecVisible.addElement(new Integer(((JCOutlinerFolderNode)NodesZuordnung[i]).getChildren().size()+1));
				JCOutlinerNode NodSeperator = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)NodesZuordnung[i]);
				NodSeperator.setUserData(VecInvisible);
			}
			else
			{
				VecVisible.addElement(new Integer((NodesZuordnung[i].getParent()).getChildren().size()+1));
				JCOutlinerNode NodSeperator = new JCOutlinerNode(VecVisible,NodesZuordnung[i].getParent());
				NodSeperator.setUserData(VecInvisible);
				i=NodesZuordnung.length;
			}
		}
		OutZuordnung.folderChanged(OutZuordnung.getRootNode());
	}

	@SuppressWarnings("unchecked")
	private void Loeschen()
	{
		JCOutlinerNode[] NodesZuordnung = OutZuordnung.getSelectedNodes();
                String s=""+NodesZuordnung[0];
                if (NodesZuordnung[0].getLevel()==1)
                {
                  //String s=""+NodesZuordnung[0];
                  for(int i=1;i<NodesZuordnung.length;i++)
                    s+=","+NodesZuordnung[i];
                  //g.progInfo("löschen:"+s);
                  //return;
                }
                if(NodesZuordnung[0].getLevel()>1 || new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",new String[] {s}) == Message.YES_OPTION)
                  for(int i=0;i<NodesZuordnung.length;i++)
                  {
			if(NodesZuordnung[i].getLevel()==1)
			{
				if(TabZuordnung.posInhalt("Node Gruppe",NodesZuordnung[i]))
				{
					((JCOutlinerFolderNode)NodesZuordnung[i]).removeChildren();

					do
					{
						if(TabZuordnung.getS("Status").equals("new"))
							TabZuordnung.setInhalt("Status","new del");
						else
							TabZuordnung.setInhalt("Status","del");
						TabZuordnung.moveNext();
					}
					while(TabZuordnung.getInhalt("Node Gruppe")==NodesZuordnung[i] && !TabZuordnung.eof());
				}
			}
			else
			{
				if(TabZuordnung.posInhalt("Node Eigenschaft",NodesZuordnung[i]))
				{
					if(TabZuordnung.getS("Status").equals("new"))
						TabZuordnung.setInhalt("Status","new del");
					else
						TabZuordnung.setInhalt("Status","del");
					if (bReihenfolge)
						((Vector)NodesZuordnung[i].getLabel()).setElementAt("-------------",0);
					else
						NodesZuordnung[i].getParent().removeChild(NodesZuordnung[i]);
				}
			}
                  }
		OutZuordnung.folderChanged(OutZuordnung.getRootNode());
	}

	private void Speichern()
	{
          long lClock = Static.get_ms();
		String sTab=(sTabVorher.equals("Kunde") || sTabVorher.equals("Menge")?"Begriff":sTabVorher)+"_Zuordnung";
		SQL Qry = new SQL(g);
		TabZuordnung.moveFirst();
		while(!TabZuordnung.eof())
		{
			if(TabZuordnung.getS("Status").equals("new"))
			{
				Qry.add(saryAIC[0],TabZuordnung.getI("Gruppe"));
				Qry.add(saryAIC[1],TabZuordnung.getI("Eigenschaft"));
				if(bReihenfolge)
					Qry.add("Reihenfolge",TabZuordnung.getI("Reihenfolge"));
				Qry.insert(sTab,false);
			}
			else if(TabZuordnung.getS("Status").equals("del"))
			{
				Qry.add(saryAIC[0],TabZuordnung.getI("Gruppe"));
				Qry.add(saryAIC[1],TabZuordnung.getI("Eigenschaft"));
				Qry.delete(sTab);
				Qry.clear();
			}
			else if(TabZuordnung.getS("Status").equals("edit"))
			{
				Qry.add("Reihenfolge",TabZuordnung.getI("Reihenfolge"));
				//Qry.update(sTab,saryAIC[0],TabZuordnung.getI("Gruppe"),saryAIC[1],TabZuordnung.getI("Eigenschaft"));
				Qry.addWhere(saryAIC[0],TabZuordnung.getI("Gruppe"));
				Qry.addWhere(saryAIC[1],TabZuordnung.getI("Eigenschaft"));
				Qry.update(sTab);
			}
                        TabZuordnung.setInhalt("Status",null);
			TabZuordnung.moveNext();
		}
		Qry.free();
            g.progInfo("Zuordnung speichern:"+(Static.get_ms()-lClock)+" ms");
	}

	private void Drehen()
	{
		iGedreht++;
		g.progInfo("iGedreht="+iGedreht);
		TabZuordnung.sAIC=sSpalte[iDA];
		iDA=1-iDA;
		TabZuordnung.sGruppe=sSpalte[iDA];

		if(!bReihenfolge)
			TabZuordnung.zusammenfassen(sSpalte[iDA]);
		else
			TabZuordnung.group_sort(sSpalte[iDA],"Reihenfolge");

		fuelleOutZuordnung();
		//EnableButtons();
	}

	@SuppressWarnings("unchecked")
	private void Verschieben(boolean bOben)
	{
		JCOutlinerNode[] NodSelected = OutZuordnung.getSelectedNodes();

		for(int i=0;NodSelected.length>i;i++)
		{
			Vector<JCOutlinerNode> VecNodes = NodSelected[i].getParent().getChildren();
			int iPos=VecNodes.indexOf(NodSelected[i]);
			if(iPos+(bOben?-1:1)>=0 && iPos+(bOben?-1:1)<VecNodes.size())
			{
				JCOutlinerNode NodAndere = VecNodes.elementAt(iPos+(bOben?-1:1));

				VecNodes.setElementAt(NodAndere,iPos);
				VecNodes.setElementAt(NodSelected[i],iPos+(bOben?-1:1));

				((Vector)NodSelected[i].getLabel()).setElementAt(new Integer(iPos+(bOben?-1:1)+1),1);
				((Vector)NodAndere.getLabel()).setElementAt(new Integer(iPos+1),1);

				if(TabZuordnung.posInhalt("Node Eigenschaft",NodSelected[i]))
				{
					TabZuordnung.setInhalt("Reihenfolge",new Integer(iPos+(bOben?-1:1)+1));
					if(TabZuordnung.getS("Status").equals(""))
						TabZuordnung.setInhalt("Status","edit");
				}
				if(TabZuordnung.posInhalt("Node Eigenschaft",NodAndere))
				{
					TabZuordnung.setInhalt("Reihenfolge",new Integer(iPos+1));
					if(TabZuordnung.getS("Status").equals(""))
						TabZuordnung.setInhalt("Status","edit");
				}
			}
		}

		OutZuordnung.folderChanged(OutZuordnung.getRootNode());
	}

	private void deleteAllSeperators()
	{
		if(bReihenfolge)
		{
			g.progInfo("select * from "+sTabVorher+"_zuordnung order by aic_"+saryKennung[0]+",reihenfolge");
			Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_"+saryKennung[0]+" t1,aic_"+saryKennung[1]+" t2,reihenfolge from "+sTabVorher+"_zuordnung order by aic_"+saryKennung[0]+",reihenfolge",true);
			//Tab.showGrid();

			int iAIC=0;
			int iCounter=1;
			for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
			{
				if(iAIC!=Tab.getI("t1"))
				{
					iAIC=Tab.getI("t1");
					iCounter=1;
				}

				g.exec("UPDATE "+sTabVorher+"_zuordnung SET Reihenfolge="+iCounter+" WHERE aic_"+saryKennung[0]+"="+iAIC+" and aic_"+saryKennung[1]+"="+Tab.getI("t2"));
				iCounter++;
			}
		}
	}


// add your data members here
Global g;
private AUOutliner OutGruppen = new AUOutliner(new JCOutlinerFolderNode("RootGruppen"));
private AUOutliner OutEigenschaft = new AUOutliner(new JCOutlinerFolderNode("RootEigenschaft"));
private Vector<AUOutliner> VecOut = new Vector<AUOutliner>();
private AUOutliner OutZuordnung;
private ComboSort CboTabellen;
private Vector<JLabel> VecLbl = new Vector<JLabel>();
private String[] saryKennung = {"",""};
private String[] saryAIC = {"",""};
private Tabellenspeicher[] TabGrpEig = new Tabellenspeicher[2];
private Tabellenspeicher TabZuordnung;
private int iDA=0;
private int iGedreht=0;
private String[] sSpalte = {"Gruppe","Eigenschaft"};
private boolean bReihenfolge;
private String sTabVorher;
private boolean bFirst=true;

private JButton BtnNewGrp;
private JButton BtnNewEig;
//private JButton BtnOk;
//private JButton BtnAbbruch;
private JButton BtnSpeichern;
private JButton BtnShow;
private JButton BtnBeenden;
private JButton BtnDrehen;
private JButton BtnRefresh;
private JButton BtnPfeilrechts;
private JButton BtnPfeillinks;
private JButton BtnPfeiloben;
private JButton BtnPfeilunten;
private JButton BtnSeperator;
private JButton BtnDelAllSeperators;
}

