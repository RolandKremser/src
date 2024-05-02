/*
    All_Unlimited-Grunddefinition-Sprache2.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import java.awt.Cursor;

public class Sprache2 extends All_Unlimited.Allgemein.Formular
{
	public static Sprache2 Self=null;
	
    public static Sprache2 get(Global g)
    {
    	if (Self==null)
    		Self=new Sprache2(g);
    	else
    		Self.show();
    	return Self;
    }
    
    public static void free()
    {
    	if (Self != null)
    	{
    		Self.g.winInfo("Sprache2.free");
    		Self.thisFrame.dispose();
    		Self = null;
    	}
    }
	
	private Sprache2(Global glob)
    {
		super("Sprache2",null,glob);
                //long lClock=Static.get_ms();
		g=glob;
		g.winInfo("Sprache2.create");
		BtnBild = new BildEingabe((JFrame)thisFrame,g);
		BtnBild.setScale(400,300);
        BtnBild.setFont(g.fontButton);
        BtnBildFX = new BildEingabe((JFrame)thisFrame,g);
		BtnBildFX.setScale(400,300);
        BtnBildFX.setFont(g.fontButton);
        BtnBildSel = new BildEingabe((JFrame)thisFrame,g);
        BtnBildSel.setScale(400,300);
        BtnBildSel.setFont(g.fontButton);
                //LblBild.setFont(g.fontButton);
                boolean b=Static.bRahmen;
                Static.bRahmen=true;
                MemStdSprache = new AUTextArea();
                MemFremdSprache = new AUTextArea();
                TooltipStd = new AUTextArea();
                TooltipFremd = new AUTextArea();
                Static.bRahmen=b;
		CboSprache = new ComboSort(g);
		CboSprache.fillDefTable("SPRACHE",false);
                CboSprache.setSelectedAIC(g.getSprache());
		EdtKennung.setEditable(false);
                //EdtBezeichnung.setEditable(false);
		EdtStdSprache.setEditable(false);

		Out.setRootVisible(false);
		Out.setColumnButtons(new String[] {"Kennung","Aic","DefBezeichnung","StdSprache","FremdSprache","Bild","BildFX","Combo","fix","Memo","Tooltip"});
		Out.setNumColumns(11);
		Out.setColumnLabelSortMethod(Sort.sortMethod);

		for(int iPos=0;iPos<g.TabTabellenname.size();iPos++)
                {
                  String sKennung = g.TabTabellenname.getS(iPos,"Kennung");
                  if (!g.TabTabellenname.getB(iPos,"Fixe_Sprache")/* && !sKennung.equals("HAUPTSCHIRM")*/ && (g.Def() || !sKennung.equals("SPALTENNAME") && !sKennung.equals("TABELLENNAME") && !sKennung.equals("ZUORDNUNG")))
                  {

			Vector<Comparable> VecVisible = new Vector<Comparable>();
			Vector<Object> VecInvisible = new Vector<Object>();
			JCOutlinerFolderNode NodL1=null;
			if(!sKennung.equalsIgnoreCase("BEFEHL"))
			{
				VecVisible.addElement(g.TabTabellenname.getS(iPos,"Kennung"));
                                VecVisible.addElement(new Integer(g.TabTabellenname.getI(iPos,"AIC")));
                                VecVisible.addElement(g.TabTabellenname.getS(iPos,"Bezeichnung"));
				VecInvisible.addElement(new Integer(g.TabTabellenname.getI(iPos,"AIC")));
				VecInvisible.addElement(sKennung);
				VecInvisible.addElement(new Integer(0));
				VecInvisible.addElement(sKennung.equalsIgnoreCase("Begriff") || sKennung.equalsIgnoreCase("Stamm") || sKennung.equalsIgnoreCase("Code")?null:new Integer(0));

				NodL1 = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)Out.getRootNode());
				NodL1.setUserData(VecInvisible);
				NodL1.setState(BWTEnum.FOLDER_CLOSED);
			}

			if(sKennung.equalsIgnoreCase("Begriff"))
			{
				int iOld_AIC_Begriffgruppe=0;
				for(int iPosB=0;iPosB<g.TabBegriffe.size();iPosB++)
				{

					if(g.TabBegriffe.getI(iPosB,"Gruppe")!=iOld_AIC_Begriffgruppe)
					{
						iOld_AIC_Begriffgruppe=g.TabBegriffe.getI(iPosB,"Gruppe");
						int iPosBG=g.TabBegriffgruppen.getPos("AIC",iOld_AIC_Begriffgruppe);
						VecVisible = new Vector<Comparable>();
						VecInvisible = new Vector<Object>();
                                                VecVisible.addElement(g.TabBegriffgruppen.getS(iPosBG,"Kennung").toUpperCase());
                                                VecVisible.addElement(new Integer(iOld_AIC_Begriffgruppe));
						VecVisible.addElement(g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung"));
						VecInvisible.addElement(new Integer(g.TabTabellenname.getI(iPos,"AIC")));
						VecInvisible.addElement(sKennung);
						VecInvisible.addElement(new Integer(iOld_AIC_Begriffgruppe));
						VecInvisible.addElement(new Integer(0));

						JCOutlinerFolderNode Nod = new JCOutlinerFolderNode((Object)VecVisible,NodL1);
						Nod.setUserData(VecInvisible);
						Nod.setState(BWTEnum.FOLDER_CLOSED);
					}
				}
			}
			else if(sKennung.equalsIgnoreCase("Spalte"))
			{
                          ;
			}
			else if(sKennung.equalsIgnoreCase("Code"))
			{
				int iOld_AIC_Begriffgruppe=0;
				for(int iPosC=0;iPosC<g.TabCodes.size();iPosC++)
				{
					if(g.TabCodes.getI(iPosC,"Gruppe")!=iOld_AIC_Begriffgruppe)
					{
						iOld_AIC_Begriffgruppe=g.TabCodes.getI(iPosC,"Gruppe");
						int iPosBG=g.TabBegriffgruppen.getPos("AIC",iOld_AIC_Begriffgruppe);
						VecVisible = new Vector<Comparable>();
						VecInvisible = new Vector<Object>();
                                                VecVisible.addElement(g.TabBegriffgruppen.getS(iPosBG,"Kennung").toUpperCase());
                                                VecVisible.addElement(new Integer(iOld_AIC_Begriffgruppe));
						VecVisible.addElement(g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung"));
						VecInvisible.addElement(new Integer(g.TabTabellenname.getI(iPos,"AIC")));
						VecInvisible.addElement(sKennung);
						VecInvisible.addElement(new Integer(iOld_AIC_Begriffgruppe));
						VecInvisible.addElement(new Integer(0));

						JCOutlinerFolderNode Nod = new JCOutlinerFolderNode((Object)VecVisible,NodL1);
						Nod.setUserData(VecInvisible);
						Nod.setState(BWTEnum.FOLDER_CLOSED);
					}
				}
			}
			else if(sKennung.equalsIgnoreCase("Stamm"))
			{
				SQL Qry = new SQL(g);

				if(Qry.open("SELECT DISTINCT Stammtyp.kennung,Stammtyp.AIC_Stammtyp AIC"+g.AU_Bezeichnung2("Stammtyp")+" bezeichnung FROM Stammtyp "+g.join2("Stammview2","Stammtyp")+" WHERE "+g.bit("SttBits",g.cstTranslate)+" order by bezeichnung"))
				{
					while(!Qry.eof())
					{
						VecVisible = new Vector<Comparable>();
						VecInvisible = new Vector<Object>();
                                                VecVisible.addElement(Qry.getS("Kennung").toUpperCase());
                                                VecVisible.addElement(new Integer(Qry.getI("AIC")));
						VecVisible.addElement(Qry.getS("Bezeichnung"));
						VecInvisible.addElement(new Integer(g.TabTabellenname.getI(iPos,"AIC")));
						VecInvisible.addElement(sKennung);
						VecInvisible.addElement(new Integer(Qry.getI("AIC")));
						VecInvisible.addElement(new Integer(0));

						JCOutlinerFolderNode Nod = new JCOutlinerFolderNode((Object)VecVisible,NodL1);
						Nod.setUserData(VecInvisible);
						Nod.setState(BWTEnum.FOLDER_CLOSED);

						Qry.moveNext();
					}
				}
				else
					Static.printError("Sprache2: Fehler beim Laden der Stammtypen!!!");

				Qry.free();
			}
                  }
                }

		Build();

		show();

		EnableButtons();

		EdtFremdSprache.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent ev)
			{
			}
			public void keyReleased(KeyEvent ev)
			{
				if(ev.getKeyCode()==KeyEvent.VK_ESCAPE)
					nextNode();
				else if(ev.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(BtnSave.isEnabled())
						Ok();
					nextNode();
				}

				EnableButtons();

			}
			public void keyTyped(KeyEvent ev)
			{
			}
		});

                KeyListener KeyList=new KeyListener()
                {
                        public void keyPressed(KeyEvent ev)
                        {
                        }
                        public void keyReleased(KeyEvent ev)
                        {
                          //g.progInfo("KeyListener:"+ev.getSource());
                                EnableButtons();
                        }
                        public void keyTyped(KeyEvent ev)
                        {
                        }
                };
                EdtKennung.addKeyListener(KeyList);
                EdtBezeichnung.addKeyListener(KeyList);
		EdtFremdTitel.addKeyListener(KeyList);
		EdtFremdHeader.addKeyListener(KeyList);
		MemFremdSprache.Edt.addKeyListener(KeyList);
                TooltipFremd.Edt.addKeyListener(KeyList);

		Out.addItemListener(new JCOutlinerListener()
		{
			 public void itemStateChanged(JCItemEvent ev)
			 {
			 }

			 public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
			 {
			 }

			 public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
			 {
                           JCOutlinerNode NodeSelected = Out.getSelectedNode();
                           //g.progInfo(bSpalte+"/"+NodeSelected.getLevel()+"/"+Sort.gets(NodeSelected.getUserData(),1));
                           if (!bSpalte && NodeSelected.getLevel()==1 && Sort.gets(NodeSelected.getUserData(),1).equals("SPALTE"))
                             SpaltenabfragenLaden((JCOutlinerFolderNode)NodeSelected);

				Vector VecInvisible = NodeSelected!=null?(Vector)NodeSelected.getUserData():null;
				if(VecInvisible!=null && NodeSelected.getState()==5 && VecInvisible.elementAt(3)!=null )
					LoadNodes((JCOutlinerFolderNode)NodeSelected);
				EnableButtons();
			 }

			 public void outlinerNodeSelectBegin(JCOutlinerEvent ev)
			 {
			 }

			 public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
			 {
				JCOutlinerNode NodeSelected = Out.getSelectedNode();
				//Vector VecInvisible = NodeSelected!=null?(Vector)NodeSelected.getUserData():null;
				if(NodeSelected.getState()==BWTEnum.NOVALUE)
					showNode(NodeSelected);
				EnableButtons();
			 }
		});

		CboSprache.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange() == ItemEvent.SELECTED)
				{
					changeLanguage();
					JCOutlinerNode NodeSelected = Out.getSelectedNode();
					//Vector VecInvisible = NodeSelected!=null?(Vector)NodeSelected.getUserData():null;
					if(NodeSelected.getState()==BWTEnum.NOVALUE)
						showNode(NodeSelected);
					EnableButtons();
				}
			}
		});

                ActionListener AL=new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          Object Btn=ev.getSource();
                          if (Btn==BtnNew)
                          {
                            bNeu=true;
                            EdtKennung.setEditable(true);
                            EdtKennung.setText("");
                            //EdtBezeichnung.setEditable(true);
                            EdtBezeichnung.setText("");
                            EdtKennung.requestFocus();
                          }
                          else if (Btn==BtnSave)
                            Ok();
                          else if (Btn==BtnAbbruch)
                            showNode(Out.getSelectedNode());
                          else if (Btn==BtnBild)
                          {
                              BtnBild.LadeBild();
                              EnableButtons();
                          }
                          else if (Btn==BtnBildFX)
                          {
                              BtnBildFX.LadeBild();
                              EnableButtons();
                          }
                          else if (Btn==BtnBildSel)
                          {
                        	  BtnBildSel.LadeBild();
                              EnableButtons();
                          }
                          //else if (Btn==BtnHelp)
                          //  Static.OpenURL("babel.altavista.com/translate.dyn");
                          else if (Btn==BtnTranslate)
                          {
                                /*String s=Static.changeUmlaute(Static.beiLeer(EdtStdSprache.getText(),EdtKennung.getText()));
                                String sSprache=CboSprache.getSelectedKennung();
                                Static.OpenURL(sSprache.equals("Klingonisch")? "http://infoportal-deutschland.aus-stade.de/Klingon/"+s.charAt(0)+".htm":sSprache.equals("Englisch")?
                                        s==null || s.equals("")?"dict.leo.org":"http://dict.leo.org/ende/index_de.html#/search="+s:"babel.altavista.com/translate.dyn");*/
                                String sVon=SQL.getString(g,"select iso639 from sprache where aic_sprache="+g.getSprache());
                                String sNach=SQL.getString(g,"select iso639 from sprache where aic_sprache="+CboSprache.getSelectedAIC());
                                Static.OpenURL("http://"+sVon+"-"+sNach+".dict.cc/?s="+Static.beiLeer(EdtStdSprache.getText(),EdtKennung.getText()));
                          }
                          else if (Btn==BtnTsearch)
                            Tsearch.get(g,null);
                          if (Btn==BtnSave || Btn==BtnAbbruch)
                            EnableButtons();
                        }
                };
                BtnNew.addActionListener(AL);
		BtnSave.addActionListener(AL);
		BtnAbbruch.addActionListener(AL);
        BtnBild.addActionListener(AL);
        BtnBildFX.addActionListener(AL);
        BtnBildSel.addActionListener(AL);
		/*CbxMemo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				show_Memo();
				thisFrame.setVisible(false);
				thisFrame.setVisible(true);
			}
		});*/

		CbxBild.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				show_Bild();
				thisFrame.setVisible(false);
				thisFrame.setVisible(true);
			}
		});

                if (CbxCombo !=  null)
                  CbxCombo.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      EnableButtons();
                      fillImageText();
                    }
                  });

                if (CbxFix !=  null)
                  CbxFix.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      EnableButtons();
                      //fillImageText();
                    }
                  });

		BtnBeenden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				SaveParameter();
				hide();
				if (bChange)
				{
					g.Refresh();
					bChange=false;
				}
			}
		});

		//BtnHelp.addActionListener(AL);
                BtnTsearch.addActionListener(AL);
		BtnTranslate.addActionListener(AL);

		BtnExport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				JFileChooser chooser = new JFileChooser();
				SimpleFileFilter filefilter = new SimpleFileFilter(new String[] {"csv"},"Textdatei (*.csv)");
				chooser.setMultiSelectionEnabled(false);
				chooser.addChoosableFileFilter(filefilter);
				chooser.setCurrentDirectory(new java.io.File("C:\\"));
				chooser.setFileFilter(filefilter);
				int option = chooser.showSaveDialog(null);
				String sFileName=option == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile()!=null ? chooser.getSelectedFile().getPath():"";
				//if(option == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile()!=null)
				//String sFileName=chooser.getSelectedFile().getPath();

				if (!sFileName.equals(""))
				{
					try
					{
					java.io.FileOutputStream fos=new java.io.FileOutputStream(sFileName,false);
					String s="Kennung;Bezeichnung;"+CboSprache.getSelectedBezeichnung()+"\n";
					fos.write(s.getBytes(Static.CP));

					//String sFremd=CboSprache.getSelectedBezeichnung();
					//Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Kennung","Bezeichnung",sFremd});
					Vector Vec=Out.getSelectedNode().getParent().getChildren();
					for(int i=0;i<Vec.size();i++)
					{
						Vector VecVisible = (Vector)((JCOutlinerNode)Vec.elementAt(i)).getLabel();
						s=VecVisible.elementAt(SpKennung)+";"+Static.beiLeer((String)VecVisible.elementAt(SpStdSprache),(String)VecVisible.elementAt(SpKennung))+";"+VecVisible.elementAt(SpFremdSprache)+"\n";
						fos.write(s.getBytes(Static.CP));
						/*Tab.addInhalt("Kennung",(String)VecVisible.elementAt(0));
						Tab.addInhalt("Bezeichnung",Static.beiLeer((String)VecVisible.elementAt(1),(String)VecVisible.elementAt(0)));
						Tab.addInhalt(sFremd,(String)VecVisible.elementAt(2));*/
					}
					fos.close();
					}
					catch (java.io.IOException ex)
					{
						Static.printError("Sprache2.Export("+sFileName+"): IOException - "+ex);
					}
				}
				//Tab.showGrid();
			}
		});

		BtnImport.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent ev)
			{
				JFileChooser chooser = new JFileChooser();
				SimpleFileFilter filefilter = new SimpleFileFilter(new String[] {"csv"},"Textdatei (*.csv)");
				chooser.setMultiSelectionEnabled(false);
				chooser.addChoosableFileFilter(filefilter);
				chooser.setCurrentDirectory(new java.io.File("C:\\"));
				chooser.setFileFilter(filefilter);
				int option = chooser.showOpenDialog(null);
				String sFileName=option == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile()!=null ? chooser.getSelectedFile().getPath():"";
				if (!sFileName.equals(""))
				{
					String sFremd=CboSprache.getSelectedBezeichnung();
					Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Aic","Kennung","Bezeichnung",sFremd});
					Tab.readFile(';',/*"file:\\"+*/sFileName,false);
					Tab.moveFirst();
					if(Tab.getS(sFremd).equals(sFremd))
					{
						Tab.clearInhalt();
						//Tab.showGrid();
						JCOutlinerFolderNode NodParent=Out.getSelectedNode().getParent();
						int iTab=((Integer)((Vector)NodParent.getUserData()).elementAt(0)).intValue();
						int iSprache=CboSprache.getSelectedAIC();
						Vector Vec= NodParent.getChildren();
						for(int i=0;i<Vec.size();i++)
						{
							JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
							String sKennung=(String)((Vector)Nod.getLabel()).elementAt(0);
							if (Tab.posInhalt("Kennung",sKennung))
							{
								String s=(String)((Vector)Nod.getLabel()).elementAt(2);
								String sNeu=Tab.getS(sFremd);
								if (!sNeu.equals("") && !sNeu.equals(s))
								{
									int iFremd=((Integer)((Vector)Nod.getUserData()).elementAt(0)).intValue();
									//g.progInfo("Ändere "+sKennung+" von "+s+" auf "+sNeu+" ("+iTab+"/"+iFremd+"/"+iSprache+")");
									g.setBezeichnung(s,sNeu,iTab,iFremd,iSprache);
								}
							}
							else
								Static.printError(sKennung+" nicht gefunden!");

						}
						int iVon=((Integer)((Vector)NodParent.getUserData()).elementAt(3)).intValue();
						//g.progInfo("Element 3 von "+iVon+" auf 0");
						((Vector)NodParent.getUserData()).setElementAt(new Integer(iVon+1),3);
						//LoadNodes(NodParent);
						changeLanguage();
						//g.setBezeichnung((String)VecVisible.elementAt(2),EdtFremdSprache.getText(),iTab,iFremd,iSprache);

					}
					else
						Static.printError("Sprache unterschiedlich");
					//Tab.showGrid();
				}
			}
		});
        //g.clockInfo("Sprache2",lClock);
    }

    private void SpaltenabfragenLaden(JCOutlinerFolderNode NodP)
    {
      thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      long lClock=Static.get_ms();
      int iTabSp=g.TabTabellenname.getAic("SPALTE");
      //g.fixInfo("iTabSp="+iTabSp);
      int iBG_Abfrage=g.TabBegriffgruppen.getI("Aic");
      if(iBG_Abfrage>=0)//g.TabBegriffgruppen.posInhalt("Kennung","Abfrage"))
      {
              //int iOld_AIC_Begriffgruppe=g.TabBegriffgruppen.getI("AIC");
              Tabellenspeicher Tab=new Tabellenspeicher(g,"select begriff.aic_begriff,kennung,defbezeichnung"+g.AU_Bezeichnung("Begriff")+
                  ",(select count(*) from bezeichnung where aic_tabellenname="+iTabSp+" and aic_sprache="+g.getSprache()+" and aic_fremd in (select aic_spalte from spalte where aic_abfrage=abfrage.aic_abfrage and "+g.bit("spalte.bits",0x0008/*cstAnzeigen*/)+" and "+g.bits("spalte.bits",0x1000/*cstUnsichtbar*/)+"=0)) deutsch"+
                  ",(select count(*) from bezeichnung where aic_tabellenname="+iTabSp+" and aic_sprache="+CboSprache.getSelectedAIC()+" and aic_fremd in (select aic_spalte from spalte where aic_abfrage=abfrage.aic_abfrage and "+g.bit("spalte.bits",0x0008/*cstAnzeigen*/)+" and "+g.bits("spalte.bits",0x1000/*cstUnsichtbar*/)+"=0)) fremd"+
                  ",(select count(*) from daten_memo where aic_tabellenname="+iTabSp+" and aic_sprache="+CboSprache.getSelectedAIC()+" and aic_fremd in (select aic_spalte from spalte where aic_abfrage=abfrage.aic_abfrage and "+g.bit("spalte.bits",0x0008/*cstAnzeigen*/)+" and "+g.bits("spalte.bits",0x1000/*cstUnsichtbar*/)+"=0)) mem"+
                  ",(select count(*) from tooltip where aic_tabellenname="+iTabSp+" and aic_sprache="+CboSprache.getSelectedAIC()+" and aic_fremd in (select aic_spalte from spalte where aic_abfrage=abfrage.aic_abfrage and "+g.bit("spalte.bits",0x0008/*cstAnzeigen*/)+" and "+g.bits("spalte.bits",0x1000/*cstUnsichtbar*/)+"=0)) tt"+
                  " from begriff "+g.join2("abfrage","begriff")+" where spalten>0 order by defbezeichnung",true);
              //for(g.TabBegriffe.posInhalt("Gruppe",iOld_AIC_Begriffgruppe);!g.TabBegriffe.eof() && iOld_AIC_Begriffgruppe==g.TabBegriffe.getI("Gruppe");g.TabBegriffe.moveNext())
              for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
              {
                      Vector<Comparable> VecVisible = new Vector<Comparable>();
                      Vector<Object> VecInvisible = new Vector<Object>();
                      VecVisible.addElement(Tab.getS("Kennung").toUpperCase());
                      VecVisible.addElement(Tab.getInt("AIC_BEGRIFF"));
                      VecVisible.addElement(Tab.getS("DefBezeichnung"));
                      //VecVisible.addElement(Tab.getS("Bezeichnung"));
                      VecVisible.addElement(Tab.getI("deutsch"));
                      VecVisible.addElement(Tab.getI("fremd"));
                      VecVisible.addElement(null);
                      VecVisible.addElement(null);
                      VecVisible.addElement(null);
                      VecVisible.addElement(null);
                      VecVisible.addElement(Tab.getI("mem"));
                      VecVisible.addElement(Tab.getI("tt"));
                      VecInvisible.addElement(new Integer(g.TabTabellenname.getI("AIC")));
                      VecInvisible.addElement("SPALTE");
                      VecInvisible.addElement(Tab.getInt("AIC_BEGRIFF"));
                      VecInvisible.addElement(new Integer(0));

                      JCOutlinerFolderNode Nod = new JCOutlinerFolderNode((Object)VecVisible,NodP);
                      Nod.setUserData(VecInvisible);
                      Nod.setState(BWTEnum.FOLDER_CLOSED);
              }
              bSpalte=true;
      }
      g.clockInfo("Spaltenabfragen laden",lClock);
      thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
	private void Build()
	{
		//BtnHelp = getButton("help");
		//if(BtnHelp==null) BtnHelp = new JButton();
                BtnTsearch = getFormularButton("translate-search");
                if(BtnTsearch==null) BtnTsearch = new JButton();
		BtnTranslate = getButton("Uebersetzen");
		if(BtnTranslate==null) BtnTranslate = new JButton();
                BtnNew = getButton("Neu");
		if(BtnNew==null) BtnNew = new JButton();
		BtnSave = getButton("Speichern");
		if(BtnSave==null) BtnSave = new JButton();
		BtnAbbruch = getButton("Ruecksetzen");
		if(BtnAbbruch==null) BtnAbbruch = new JButton();
		BtnBeenden = getButton("Beenden");
		if(BtnBeenden==null) BtnBeenden = new JButton();
		BtnExport = getButton("Export");
		if(BtnExport==null) BtnExport = new JButton();
		BtnImport = getButton("Import");
		if(BtnImport==null) BtnImport = new JButton();

		CbxBild = getCheckbox("Bild3");
		if(CbxBild==null) CbxBild = new JCheckBox();
		//CbxMemo = getCheckbox("Memo");
		//if(CbxMemo==null) CbxMemo = new JCheckBox();
		CbxBild.setSelected(true);
		//CbxMemo.setSelected(true);

		//JPanel Pnl_LblFremdSprache = (JPanel)getFrei("LblFremdSprache");
		JPanel Pnl_LblStdSprache = getFrei("LblStdSprache");
		JPanel Pnl_Combo_Sprache = getFrei("Combo Sprache");
		JPanel Pnl_TxtKennung = getFrei("TxtKennung");
                JPanel Pnl_TxtBezeichnung = getFrei("TxtBezeichnung");
		JPanel Pnl_TxtStdSprache = getFrei("TxtStdSprache");
		JPanel Pnl_TxtFremdSprache = getFrei("TxtFremdSprache");
                CbxCombo = getCheckbox("combo");
                CbxFix = getCheckbox("fix2");
		Pnl_Bild = getFrei("Bild");
		Pnl_BildFX = getFrei("BildFX");
		Pnl_BildSel = getFrei("BildSel");
		Pnl_Memo = getFrei("Memo");
                Pnl_Tooltip = getFrei("Tooltip");
		JPanel Pnl_Outliner = getFrei("Outliner");

		/*if(Pnl_LblFremdSprache!=null)
		{
			Pnl_LblFremdSprache.setLayout(new BorderLayout(2,2));
			Pnl_LblFremdSprache.add("Center",LblFremdSprache);
		}*/
		if(Pnl_LblStdSprache!=null)
		{
			Pnl_LblStdSprache.setLayout(new BorderLayout(2,2));
			Pnl_LblStdSprache.add("Center",LblStdSprache);
		}
		if(Pnl_Combo_Sprache!=null)
		{
			Pnl_Combo_Sprache.setLayout(new BorderLayout(2,2));
			Pnl_Combo_Sprache.add("Center",CboSprache);
		}
		if(Pnl_TxtKennung!=null)
		{
			Pnl_TxtKennung.setLayout(new BorderLayout(2,2));
			Pnl_TxtKennung.add("Center",EdtKennung);
		}
                if(Pnl_TxtBezeichnung!=null)
		{
			Pnl_TxtBezeichnung.setLayout(new BorderLayout(2,2));
			Pnl_TxtBezeichnung.add("Center",EdtBezeichnung);
		}
		if(Pnl_TxtStdSprache!=null)
		{
			Pnl_TxtStdSprache.setLayout(new BorderLayout(2,2));
			Pnl_TxtStdSprache.add("Center",EdtStdSprache);
		}
		if(Pnl_TxtFremdSprache!=null)
		{
			Pnl_TxtFremdSprache.setLayout(new BorderLayout(2,2));
			Pnl_TxtFremdSprache.add("Center",EdtFremdSprache);
		}
		if(Pnl_Outliner!=null)
		{
			Pnl_Outliner.setLayout(new BorderLayout(2,2));
			Pnl_Outliner.add("Center",Out);
		}

		//LblBild.setMargin(new Insets(0,0,0,0));
                LoadParameter();

		show_Memo();
		show_Bild();
	}

	private void show_Bild()
	{
		if(CbxBild.isSelected())
		{
			if(Pnl_Bild!=null)
			{
				Pnl_Bild.setLayout(new GridLayout(0,1,2,2));
				Pnl_Bild.add(BtnBild);
			}
			if(Pnl_BildFX!=null)
			{
				Pnl_BildFX.setLayout(new GridLayout(0,1,2,2));
				Pnl_BildFX.add(BtnBildFX);
			}
			if(Pnl_BildSel!=null)
			{
				Pnl_BildSel.setLayout(new GridLayout(0,1,2,2));
				Pnl_BildSel.add(BtnBildSel);
			}
		}
		else
		{
			if(Pnl_Bild!=null) Pnl_Bild.removeAll();
			if(Pnl_BildFX!=null) Pnl_BildFX.removeAll();
			if(Pnl_BildSel!=null) Pnl_BildSel.removeAll();
		}
	}

	private void show_Memo()
	{
		if(Pnl_Memo!=null)
		{
            MemStdSprache.setEditable(false);
            
			EdtStdTitel.setEditable(false);
			EdtStdTitel.setBackground(MemStdSprache.getBackground());
			EdtStdHeader.setEditable(false);
			//MemStdSprache.setBackground(EdtKennung.getBackground());
			MemStdSprache.Edt.setRows(5);
            MemFremdSprache.Edt.setRows(5);
			JPanel PnlStd= new JPanel(new BorderLayout(2,2));
			g.addLabel(PnlStd,"Titel",EdtStdTitel,"North");
			PnlStd.add("Center",MemStdSprache);
			g.addLabel(PnlStd,"Header",EdtStdHeader,"South");
			
			JPanel PnlFremd = new JPanel(new BorderLayout(2,2));
			g.addLabel(PnlFremd,"Titel",EdtFremdTitel,"North");
			PnlFremd.add("Center",MemFremdSprache);
			g.addLabel(PnlFremd,"Header",EdtFremdHeader,"South");
			
			Pnl_Memo.setLayout(new GridLayout(0,1,2,2));
			GroupBox Gbx=new GroupBox(g.getShow("Original"));
			Gbx.add(PnlStd);
			Pnl_Memo.add(Gbx);
			Gbx=new GroupBox(g.getShow("Fremdsprache"));
			Gbx.add(PnlFremd);
			Pnl_Memo.add(Gbx);
			                               
		}
			
	    if (Pnl_Tooltip != null)
	    {
	    	TooltipStd.setEditable(false);
            TooltipStd.Edt.setRows(5);
			TooltipFremd.Edt.setRows(5);
			
		    Pnl_Tooltip.setLayout(new GridLayout(0, 1, 2, 2));
		    Pnl_Tooltip.add(TooltipStd);
		    Pnl_Tooltip.add(TooltipFremd);
	
		}
	}

	@SuppressWarnings("unchecked")
	private void LoadNodes(JCOutlinerFolderNode NodeSelected)
	{
		Vector<Object> VecRootInvisible = (Vector)NodeSelected.getUserData();
		//int iAIC_Tabellenname = ((Integer)VecRootInvisible.elementAt(0)).intValue();
		String sTabname = (String)VecRootInvisible.elementAt(1);
		int iAIC_Untergruppe = ((Integer)VecRootInvisible.elementAt(2)).intValue();
		int iAIC_Sprache = CboSprache.getSelectedAIC();
		SQL Qry = new SQL(g);

		if(((Integer)VecRootInvisible.elementAt(3)).intValue()==0)
		{
			if(sTabname.equalsIgnoreCase("Begriff"))
			{
				Qry.setSQL("SELECT Begriff.AIC_Begriff AIC, Begriff.DefBezeichnung,Begriff.Kennung,Combo,fixe_sprache"+
				            g.AU_Sprache("Daten_Bild","Filename","Begriff","",Global.iSpSw)+" StdFilename"+
							g.AU_Sprache("Daten_Bild","Filename","Begriff","",Global.iSpFX)+" FremdFilename"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Begriff","",0)+" StdSprache"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Begriff","",iAIC_Sprache)+" FremdSprache"+
							g.AU_Sprache("Daten_Memo","Titel","Begriff","",0)+" StdTitel"+
							g.AU_Sprache("Daten_Memo","Titel","Begriff","",iAIC_Sprache)+" FremdTitel"+
							g.AU_Sprache("Daten_Memo","Header","Begriff","",0)+" StdHeader"+
							g.AU_Sprache("Daten_Memo","Header","Begriff","",iAIC_Sprache)+" FremdHeader"+
							g.AU_Sprache("Daten_Memo","Memo","Begriff","",0)+" StdMemo"+
							g.AU_Sprache("Daten_Memo","Memo","Begriff","",iAIC_Sprache)+" FremdMemo"+
                                                        g.AU_Sprache("Tooltip","Memo2","Begriff","",0)+" StdTooltip"+
                                                        g.AU_Sprache("Tooltip","Memo2","Begriff","",iAIC_Sprache)+" FremdTooltip"+
							" FROM Begriff"+g.join("Begriffgruppe","Begriff")+" WHERE Begriffgruppe.AIC_Begriffgruppe="+iAIC_Untergruppe+" order by DefBezeichnung");
			}
			else if(sTabname.equalsIgnoreCase("Spalte"))
			{
				Qry.setSQL("SELECT S.AIC_Spalte AIC, S.Kennung, S.Kennung DefBezeichnung"+
				            g.AU_Sprache("Daten_Bild","Filename","Spalte","S",Global.iSpSw)+" StdFilename"+
							g.AU_Sprache("Daten_Bild","Filename","Spalte","S",Global.iSpFX)+" FremdFilename"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Spalte","S",0)+" StdSprache"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Spalte","S",iAIC_Sprache)+" FremdSprache"+
							g.AU_Sprache("Daten_Memo","Titel","Spalte","S",0)+" StdTitel"+
							g.AU_Sprache("Daten_Memo","Titel","Spalte","S",iAIC_Sprache)+" FremdTitel"+
							g.AU_Sprache("Daten_Memo","Memo","Spalte","S",0)+" StdMemo"+
							g.AU_Sprache("Daten_Memo","Memo","Spalte","S",iAIC_Sprache)+" FremdMemo"+
                                                        g.AU_Sprache("Tooltip","Memo2","Spalte","S",0)+" StdTooltip"+
                                                        g.AU_Sprache("Tooltip","Memo2","Spalte","S",iAIC_Sprache)+" FremdTooltip"+
							" FROM Spalte S"+g.join("Abfrage","A","S","Abfrage")+" WHERE A.AIC_Begriff="+iAIC_Untergruppe+
                                                        " and "+g.bit("s.bits",0x0008/*cstAnzeigen*/)+" and "+g.bits("s.bits",0x1000/*cstUnsichtbar*/)+"=0 order by s.reihenfolge");
			}
			else if(sTabname.equalsIgnoreCase("Code"))
			{
				Qry.setSQL("SELECT Code.AIC_Code AIC, Code.Kennung, Code.Kennung DefBezeichnung"+
				            g.AU_Sprache("Daten_Bild","Filename","Code","",Global.iSpSw)+" StdFilename"+
							g.AU_Sprache("Daten_Bild","Filename","Code","",Global.iSpFX)+" FremdFilename"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Code","",0)+" StdSprache"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Code","",iAIC_Sprache)+" FremdSprache"+
							g.AU_Sprache("Daten_Memo","Titel","Code","",0)+" StdTitel"+
							g.AU_Sprache("Daten_Memo","Titel","Code","",iAIC_Sprache)+" FremdTitel"+
							g.AU_Sprache("Daten_Memo","Memo","Code","",0)+" StdMemo"+
							g.AU_Sprache("Daten_Memo","Memo","Code","",iAIC_Sprache)+" FremdMemo"+
                                                        g.AU_Sprache("Tooltip","Memo2","Code","",0)+" StdTooltip"+
                                                        g.AU_Sprache("Tooltip","Memo2","Code","",iAIC_Sprache)+" FremdTooltip"+
                                                        " FROM Code"+g.join("Begriffgruppe","Code")+" WHERE Begriffgruppe.AIC_Begriffgruppe="+iAIC_Untergruppe+" order by DefBezeichnung");
			}
			else if(sTabname.equalsIgnoreCase("Stamm"))
			{
				Qry.setSQL("SELECT Stammview2.AIC_Stamm AIC, Stammview2.Bezeichnung DefBezeichnung, Stammview2.Kennung"+
				            g.AU_Sprache("Daten_Bild","Filename","Stamm","Stammview2",Global.iSpSw)+" StdFilename"+
							g.AU_Sprache("Daten_Bild","Filename","Stamm","Stammview2",Global.iSpFX)+" FremdFilename"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Stamm","Stammview2",0)+" StdSprache"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Stamm","Stammview2",iAIC_Sprache)+" FremdSprache"+
							g.AU_Sprache("Daten_Memo","Titel","Stamm","Stammview2",0)+" StdTitel"+
							g.AU_Sprache("Daten_Memo","Titel","Stamm","Stammview2",iAIC_Sprache)+" FremdTitel"+
							g.AU_Sprache("Daten_Memo","Memo","Stamm","Stammview2",0)+" StdMemo"+
							g.AU_Sprache("Daten_Memo","Memo","Stamm","Stammview2",iAIC_Sprache)+" FremdMemo"+
                                                        g.AU_Sprache("Tooltip","Memo2","Stamm","Stammview2",0)+" StdTooltip"+
                                                        g.AU_Sprache("Tooltip","Memo2","Stamm","Stammview2",iAIC_Sprache)+" FremdTooltip"+
                                                        ",(select fix from daten_memo where aic_tabellenname="+g.iTabStamm+" AND aic_sprache="+iAIC_Sprache+" AND aic_fremd=stammview2.aic_stamm) fix"+
							" FROM Stammview2"+g.join("Stammtyp","Stammview2")+" WHERE Stammtyp.AIC_Stammtyp="+iAIC_Untergruppe+" order by DefBezeichnung");
			}
			else
			{
				Qry.setSQL("SELECT "+sTabname+".AIC_"+sTabname+" AIC,"+sTabname+".Kennung, Kennung DefBezeichnung"+
				            g.AU_Sprache("Daten_Bild","Filename",sTabname,"",Global.iSpSw)+" StdFilename"+
							g.AU_Sprache("Daten_Bild","Filename",sTabname,"",Global.iSpFX)+" FremdFilename"+
							g.AU_Sprache("Bezeichnung","Bezeichnung",sTabname,"",0)+" StdSprache"+
							g.AU_Sprache("Bezeichnung","Bezeichnung",sTabname,"",iAIC_Sprache)+" FremdSprache"+
							g.AU_Sprache("Daten_Memo","Titel",sTabname,"",0)+" StdTitel"+
							g.AU_Sprache("Daten_Memo","Titel",sTabname,"",iAIC_Sprache)+" FremdTitel"+
							g.AU_Sprache("Daten_Memo","Memo",sTabname,"",0)+" StdMemo"+
							g.AU_Sprache("Daten_Memo","Memo",sTabname,"",iAIC_Sprache)+" FremdMemo"+
                                                        g.AU_Sprache("Tooltip","Memo2",sTabname,"",0)+" StdTooltip"+
                                                        g.AU_Sprache("Tooltip","Memo2",sTabname,"",iAIC_Sprache)+" FremdTooltip"+
                                                        " FROM "+sTabname+" order by Kennung");
			}

			if(Qry.open2())
			{
				Tabellenspeicher TabFixEigenschaft = sTabname.equalsIgnoreCase("Spalte")?
					new Tabellenspeicher(g,"select spalte.aic_spalte,case richtung when 1 then -AIC_Eigenschaft ELSE AIC_Eigenschaft end Eigenschaft from fixeigenschaft "+g.join("spalte","fixeigenschaft")+g.join("abfrage","spalte")+" where abfrage.aic_begriff="+iAIC_Untergruppe+" order by spalte.aic_spalte",true):null;

				VecRootInvisible.setElementAt(new Integer(CboSprache.getSelectedAIC()),3);
				while(!Qry.eof())
				{
					Vector<Comparable> VecVisible = new Vector<Comparable>();
					Vector<Comparable> VecInvisible = new Vector<Comparable>();

					if(Qry.getS("Kennung").equals("") && TabFixEigenschaft!=null)
					{
						Vector<Object> VecFixEigenschaft = new Vector<Object>();

						for(TabFixEigenschaft.posInhalt("AIC_Spalte",Qry.getI("AIC"));!TabFixEigenschaft.out() && TabFixEigenschaft.getI("AIC_Spalte")==Qry.getI("AIC");TabFixEigenschaft.moveNext())
							VecFixEigenschaft.addElement(TabFixEigenschaft.getInhalt("Eigenschaft"));
                                                String s=All_Unlimited.Hauptmaske.Abfrage.getEigenschaftBezeichnung(g,VecFixEigenschaft);
						VecVisible.addElement(s.toUpperCase());
                                                VecVisible.addElement(new Integer(Qry.getI("AIC")));
                                                VecVisible.addElement(s);
					}
					else
                    {
                      VecVisible.addElement(Qry.getS("Kennung").toUpperCase());
                      VecVisible.addElement(new Integer(Qry.getI("AIC")));
                      VecVisible.addElement(Qry.getS("DefBezeichnung"));
                    }
					VecVisible.addElement(Qry.getS("StdSprache"));
					VecVisible.addElement(Qry.getS("FremdSprache"));
                    VecVisible.addElement(Qry.getS("StdFilename"));
                    VecVisible.addElement(Qry.getS("FremdFilename"));
                    if (sTabname.equalsIgnoreCase("Begriff"))
                    {
                      VecVisible.addElement(Static.JaNein(Qry.getI("Combo") == 1));
                      VecVisible.addElement(Static.JaNein(Qry.getI("fixe_sprache") == 1));
                    }
                    else if(sTabname.equalsIgnoreCase("Stamm"))
                    {
                      VecVisible.addElement(Qry.isNull("FremdMemo")? "-":"x");
                      VecVisible.addElement(Static.JaNein(Qry.getI("fix") == 1));
                    }
                    else
                    {
                      VecVisible.addElement("");
                      VecVisible.addElement("");
                    }
                    VecVisible.addElement(Qry.getS("FremdMemo"));
                    VecVisible.addElement(Qry.getS("FremdTooltip"));
                    VecInvisible.addElement(new Integer(Qry.getI("AIC"))); // 0
                    VecInvisible.addElement(Qry.getS("StdFilename"));      // 1
					VecInvisible.addElement(Qry.getS("FremdFilename"));    // 2
                    VecInvisible.addElement(Qry.getS("StdMemo"));          // 3
					VecInvisible.addElement(Qry.getS("StdTitel"));         // 4
					VecInvisible.addElement(Qry.getS("FremdMemo"));        // 5
					VecInvisible.addElement(Qry.getS("FremdTitel"));       // 6
                    VecInvisible.addElement(Qry.getS("StdTooltip"));       // 7
					VecInvisible.addElement(Qry.getS("FremdTooltip"));     // 8
                    if (sTabname.equalsIgnoreCase("Begriff"))
                    {
                      VecInvisible.addElement(new Boolean(Qry.getI("Combo") == 1));
                      VecInvisible.addElement(new Boolean(Qry.getI("fixe_sprache")==1));
                    }
                    else if(sTabname.equalsIgnoreCase("Stamm"))
                    {
                      VecInvisible.addElement("-");
                      VecInvisible.addElement(new Boolean(Qry.getI("fix") == 1));
                    }
                    /*else
                    {
                    	VecInvisible.addElement(Static.sLeer);
                    	VecInvisible.addElement(Static.sLeer);
                    } */                                   	
                                        //g.progInfo(Qry.getS("Kennung")+":"+Qry.getS("FremdSprache"));
                    if(sTabname.equalsIgnoreCase("Begriff"))
                    {
                      VecInvisible.addElement(Qry.getS("StdHeader"));       // 11
                      VecInvisible.addElement(Qry.getS("FremdHeader"));     // 12
                    }
                    /*else
                    {
                    	VecInvisible.addElement(Static.sLeer);
                    	VecInvisible.addElement(Static.sLeer);
                    }*/
					JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeSelected);
					Node.setUserData(VecInvisible);

					Qry.moveNext();
				}
				Qry.close();
				Out.folderChanged(NodeSelected);

			}
			else
				Static.printError("Sprache2.LoadNodes(): Fehler beim Laden aus der Datenbank!!!");
		}
		else if(((Integer)VecRootInvisible.elementAt(3)).intValue()!=iAIC_Sprache)
		{
			Tabellenspeicher TabQry=null;

			if(sTabname.equalsIgnoreCase("Begriff"))
			{
				TabQry=new Tabellenspeicher(g,"SELECT Begriff.AIC_Begriff AIC"+
				            g.AU_Sprache("Daten_Bild","Filename","Begriff","",Global.iSpFX)+" FremdFilename"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Begriff","",iAIC_Sprache)+" FremdSprache"+
							g.AU_Sprache("Daten_Memo","Titel","Begriff","",iAIC_Sprache)+" FremdTitel"+
							g.AU_Sprache("Daten_Memo","Header","Begriff","",iAIC_Sprache)+" FremdHeader"+
                            g.AU_Sprache("Daten_Memo","Memo","Begriff","",iAIC_Sprache)+" FremdMemo"+
                            g.AU_Sprache("Tooltip","Memo2","Begriff","",iAIC_Sprache)+" FremdTooltip"+
							" FROM Begriff"+g.join("Begriffgruppe","Begriff")+" WHERE Begriffgruppe.AIC_Begriffgruppe="+iAIC_Untergruppe,true);
			}
			else if(sTabname.equalsIgnoreCase("Code"))
			{
				TabQry=new Tabellenspeicher(g,"SELECT Code.AIC_Code AIC"+
							g.AU_Sprache("Daten_Bild","Filename","Code","",Global.iSpFX)+" FremdFilename"+
							g.AU_Sprache("Bezeichnung","Bezeichnung","Code","",iAIC_Sprache)+" FremdSprache"+
							g.AU_Sprache("Daten_Memo","Titel","Code","",iAIC_Sprache)+" FremdTitel"+
							g.AU_Sprache("Daten_Memo","Memo","Code","",iAIC_Sprache)+" FremdMemo"+
                                                        g.AU_Sprache("Tooltip","Memo2","Code","",iAIC_Sprache)+" FremdTooltip"+
							" FROM Code"+g.join("Begriffgruppe","Code")+" WHERE Begriffgruppe.AIC_Begriffgruppe="+iAIC_Untergruppe,true);
			}
			else if(sTabname.equalsIgnoreCase("Stamm"))
			{
				TabQry=new Tabellenspeicher(g,"SELECT Stammview2.AIC_Stamm AIC"+
                                    g.AU_Sprache("Daten_Bild","Filename","Stamm","Stammview2",Global.iSpFX)+" FremdFilename"+
                                    g.AU_Sprache("Bezeichnung","Bezeichnung","Stamm","Stammview2",iAIC_Sprache)+" FremdSprache"+
                                    g.AU_Sprache("Daten_Memo","Titel","Stamm","Stammview2",iAIC_Sprache)+" FremdTitel"+
                                    g.AU_Sprache("Daten_Memo","Memo","Stamm","Stammview2",iAIC_Sprache)+" FremdMemo"+
                                    g.AU_Sprache("Tooltip","Memo2","Stamm","Stammview2",iAIC_Sprache)+" FremdTooltip"+
                                    " FROM Stammview2"+g.join("Stammtyp","Stammview2")+" WHERE Stammtyp.AIC_Stammtyp="+iAIC_Untergruppe,true);
			}
			else
			{
				TabQry=new Tabellenspeicher(g,"SELECT "+sTabname+".AIC_"+sTabname+" AIC"+
                                    g.AU_Sprache("Daten_Bild","Filename",sTabname,"",Global.iSpFX)+" FremdFilename"+
                                    g.AU_Sprache("Bezeichnung","Bezeichnung",sTabname,"",iAIC_Sprache)+" FremdSprache"+
                                    g.AU_Sprache("Daten_Memo","Titel",sTabname,"",iAIC_Sprache)+" FremdTitel"+
                                    g.AU_Sprache("Daten_Memo","Memo",sTabname,"",iAIC_Sprache)+" FremdMemo"+
                                    g.AU_Sprache("Tooltip","Memo2",sTabname,"",iAIC_Sprache)+" FremdTooltip"+
                                    " FROM "+sTabname,true);
			}

			if(TabQry!=null)
			{
				//TabQry.showGrid();
				VecRootInvisible.setElementAt(new Integer(CboSprache.getSelectedAIC()),3);
				Vector VecNodes = NodeSelected.getChildren();
				for(int i=0;i<VecNodes.size();i++)
				{
					Vector<String> VecVisible = (Vector)((JCOutlinerNode)VecNodes.elementAt(i)).getLabel();
					Vector<Object> VecInvisible = (Vector)((JCOutlinerNode)VecNodes.elementAt(i)).getUserData();

					if(TabQry.posInhalt("AIC",VecInvisible.elementAt(0)))
					{
						VecVisible.setElementAt(TabQry.getS("FremdSprache"),SpFremdSprache);
						VecInvisible.setElementAt(TabQry.getS("FremdFilename"),2);
						VecInvisible.setElementAt(TabQry.getM("FremdMemo"),5);
						VecInvisible.setElementAt(TabQry.getS("FremdTitel"),6);
                        VecInvisible.setElementAt(TabQry.getS("FremdTooltip"),8);
                        if(sTabname.equalsIgnoreCase("Begriff"))
                        	VecInvisible.setElementAt(TabQry.getS("FremdHeader"),12);
					}
				}
				Out.folderChanged(NodeSelected);

			}
			else
				Static.printError("Sprache2.LoadNodes(): Fehler beim Laden aus der Datenbank!!!");
		}

		Qry.free();
	}

	private void changeLanguage()
	{
		String sK = "";
		//int i=0;
		for(JCOutlinerNode NodeAkt=Out.getRootNode();NodeAkt!=null;NodeAkt=Out.getNextNode(NodeAkt))
		{
			//g.printInfo(""+NodeAkt.getLabel());
			Vector VecInvisible = (Vector)NodeAkt.getUserData();
			if(NodeAkt.getState()==BWTEnum.NOVALUE && !sK.equals(VecInvisible.elementAt(0)+":"+VecInvisible.elementAt(2)))
			{
				sK = VecInvisible.elementAt(0)+":"+VecInvisible.elementAt(2);
				LoadNodes(NodeAkt.getParent());
			}
		}

		Out.folderChanged(Out.getRootNode());
	}

        private void fillImageText()
        {
          //LblBild.setText(CbxCombo != null && CbxCombo.isSelected()? EdtStdSprache.getText():"");
          BtnBild.setText(CbxCombo != null && CbxCombo.isSelected()? EdtFremdSprache.getText():BtnBild.getImage()==null?"*":"");
          BtnBildFX.setText(CbxCombo != null && CbxCombo.isSelected()? EdtFremdSprache.getText():BtnBildFX.getImage()==null?"*":"");
          BtnBildSel.setText(CbxCombo != null && CbxCombo.isSelected()? EdtFremdSprache.getText():BtnBildSel.getImage()==null?"*":"");
          //if (CbxCombo != null && CbxCombo.isSelected() && BtnBild.getImage()==null)
          //  BtnBild.setIcon(LblBild.getIcon());
        }

	private void showNode(JCOutlinerNode Node)
	{
		Vector VecVisible = (Vector)Node.getLabel();
		Vector VecInvisible = (Vector)Node.getUserData();
		EdtKennung.setText((String)VecVisible.elementAt(SpKennung));
                EdtBezeichnung.setText((String)VecVisible.elementAt(SpDefBezeichnung));
		EdtStdSprache.setText((String)VecVisible.elementAt(SpStdSprache));
		EdtFremdSprache.setText((String)VecVisible.elementAt(SpFremdSprache));
                if (CbxCombo != null)
                {
                  CbxCombo.setEnabled(VecInvisible.size()>PosCbo && VecInvisible.elementAt(PosCbo) instanceof Boolean);
                  CbxCombo.setSelected(VecInvisible.size()>PosCbo && VecInvisible.elementAt(PosCbo) instanceof Boolean && ((Boolean)VecInvisible.elementAt(PosCbo)).booleanValue());
                }
                if (CbxFix != null)
                {
                  CbxFix.setEnabled(VecInvisible.size() > PosFix);
                  CbxFix.setSelected(VecInvisible.size() > PosFix && ((Boolean)VecInvisible.elementAt(PosFix)).booleanValue());
                }
		Image Img =g.LoadImage((String)VecInvisible.elementAt(1));
                //LblBild.setIcon(Img!=null?new ImageIcon(BildEingabe.shrink(Img,400,300)):null);
		//BtnBild.setIcon(Img!=null?new ImageIcon(Img):null);
		//BtnBild.setActionCommand((String)VecInvisible.elementAt(2));
		BtnBild.setValue(Img,(String)VecInvisible.elementAt(1));
		Img =g.LoadImage((String)VecInvisible.elementAt(2));
		BtnBildFX.setValue(Img,(String)VecInvisible.elementAt(2));
		//TODO Bild ermitteln
                fillImageText();

		EdtStdTitel.setText((String)VecInvisible.elementAt(4));
		MemStdSprache.setText((String)VecInvisible.elementAt(3));
		EdtFremdTitel.setText((String)VecInvisible.elementAt(6));
		MemFremdSprache.setText((String)VecInvisible.elementAt(5));
        TooltipStd.setText((String)VecInvisible.elementAt(7));
        TooltipFremd.setText((String)VecInvisible.elementAt(8));
        if (VecInvisible.size()>12)
        {
          EdtStdHeader.setText((String)VecInvisible.elementAt(11));
          EdtFremdHeader.setText((String)VecInvisible.elementAt(12));
        }
		//EdtFremdSprache.requestFocus();
	}

	private void EnableButtons()
	{
		JCOutlinerNode NodeSelected = Out.getSelectedNode();
		boolean b = NodeSelected.getState()==BWTEnum.NOVALUE;
		int iLevel=NodeSelected==null ? 0:NodeSelected.getLevel();
		JCOutlinerNode NodTab=iLevel<1 ? null:iLevel==1 ? NodeSelected:iLevel==2 ? NodeSelected.getParent():NodeSelected.getParent().getParent();
	    sTabName = NodTab==null ? "":Sort.gets(NodTab.getUserData(),1);
		//g.fixtestInfo("Sprache2.EnableButtons:"+sTabName);
		EdtFremdSprache.setEnabled(b);
		EdtFremdTitel.setEnabled(b);
		EdtFremdHeader.setEnabled(b);
		MemFremdSprache.setEnabled(b);
		BtnExport.setEnabled(b);
		BtnImport.setEnabled(b);

		if(b)
		{
			Vector VecVisible = (Vector)NodeSelected.getLabel();
			Vector VecInvisible = (Vector)NodeSelected.getUserData();

			//g.debugInfo(""+VecVisible);
			//g.debugInfo(EdtFremdSprache.getText());

			b = !(TooltipFremd.getValue().equals((String)VecInvisible.elementAt(8)) &&
                              EdtFremdSprache.getText().equals((String)VecVisible.elementAt(SpFremdSprache)) &&
                              !EdtKennung.Modified() && !EdtBezeichnung.Modified() &&
			      EdtFremdTitel.getText().equals((String)VecInvisible.elementAt(6)) &&
			      (VecInvisible.size()<13 || EdtFremdHeader.getText().equals((String)VecInvisible.elementAt(12))) &&
			      MemFremdSprache.getValue().equals((String)VecInvisible.elementAt(5)) &&
			      !BtnBild.Modified() && !BtnBildFX.Modified() && !BtnBildSel.Modified() &&
			      //BtnBild.getFilename().equals((String)VecInvisible.elementAt(1)) &&
			      //BtnBildFX.getFilename().equals((String)VecInvisible.elementAt(2)) &&
                              (CbxCombo == null || !CbxCombo.isEnabled() || CbxCombo.isSelected()==((Boolean)VecInvisible.elementAt(PosCbo)).booleanValue()) &&
                              (CbxFix == null || !CbxFix.isEnabled() || CbxFix.isSelected()==((Boolean)VecInvisible.elementAt(PosFix)).booleanValue()));

		}
                boolean bBegriff=sTabName.equals("BEGRIFF"); //iLevel>2 && CbxCombo.isEnabled();
                BtnNew.setEnabled(bBegriff && iLevel>2 || sTabName.equals("SPRACHE"));
                //EdtBezeichnung.setEditable(bBegriff);
		BtnSave.setEnabled(b);
		BtnAbbruch.setEnabled(b);
	}

	@SuppressWarnings("unchecked")
	public void Ok()
	{
		JCOutlinerNode NodeSelected = Out.getSelectedNode();
		JCOutlinerFolderNode NodeParent = NodeSelected.getParent();
		Vector<Comparable> VecVisible = (Vector)NodeSelected.getLabel();
		Vector VecInvisible = (Vector)NodeSelected.getUserData();
		Vector VecParInvisible = (Vector)NodeParent.getUserData();

		int iTab=((Integer)VecParInvisible.elementAt(0)).intValue();
		int iFremd=((Integer)VecInvisible.elementAt(0)).intValue();
		int iSprache=CboSprache.getSelectedAIC();
                String sBez=EdtBezeichnung.getText();
                if (bNeu && sTabName.equals("BEGRIFF"))
                {
                  int iBG=Sort.geti(NodeParent.getUserData(),2);
                  if (Message.KennungWarnung(g,EdtKennung.getText(),0,iBG,thisFrame))
                    return;
                  SQL Qry=new SQL(g);
                  Qry.add("Kennung",EdtKennung.getText());
                  Qry.add("DefBezeichnung",sBez);
                  //Qry.add("aic_begriffgruppe",SQL.getInteger(g,"select aic_begriffgruppe from begriff where aic_begriff="+iFremd));
                  Qry.add("aic_begriffgruppe",iBG);
                  Qry.add("aic_logging",g.getLog());
                  iFremd=Qry.insert("Begriff",true);
                  VecVisible=new Vector<Comparable>();
                  VecInvisible=new Vector<Comparable>();
                  VecVisible.addElement(EdtKennung.getText());    // 0
                  VecVisible.addElement(new Integer(iFremd));     // 1
                  VecVisible.addElement(sBez);// 2
                  VecVisible.addElement(EdtStdSprache.getText()); // 3
                  VecVisible.addElement(EdtFremdSprache.getText());//4
                  VecVisible.addElement(BtnBild.getFilename());
                  VecVisible.addElement(BtnBildFX.getFilename());
      			//TODO BildSel dazu ??
                  VecVisible.addElement(Static.JaNein(CbxCombo.isSelected()));
                  VecVisible.addElement(Static.JaNein(CbxFix.isSelected()));
                  VecInvisible.addElement(new Integer(iFremd)); // 0
                  VecInvisible.addElement(BtnBild.getFilename());     // 1
                  VecInvisible.addElement(BtnBildFX.getFilename());     // 2
                  VecInvisible.addElement("");     // 3
                  VecInvisible.addElement("");     // 4
                  VecInvisible.addElement("");     // 5
                  VecInvisible.addElement("");     // 6
                  VecInvisible.addElement("");     // 7
                  VecInvisible.addElement("");     // 8
                  VecInvisible.addElement(new Boolean(CbxCombo.isSelected())); // 9
                  VecInvisible.addElement(new Boolean(CbxFix.isSelected()));  // 10
                  VecInvisible.addElement("");    // 11
                  VecInvisible.addElement("");    // 12
                  NodeSelected=new JCOutlinerNode(VecVisible,NodeParent);
                  NodeSelected.setUserData(VecInvisible);
                  EdtKennung.setAktText(EdtKennung.getText());
                  EdtBezeichnung.setAktText(sBez);
                  Out.folderChanged(NodeParent);
                  Static.makeVisible(null,NodeSelected);
                  bNeu=false;
                  EdtKennung.setEditable(false);
                  g.putTabBegriffe(iBG,iFremd,EdtKennung.getText(),sBez,sBez,"",0,null,-1,-1,-1,-1,-1,0,CbxCombo.isSelected(),false,0,null,false,null,null,null,null,null,true);
                  //EdtBezeichnung.setEditable(false);
                }
                else if (bNeu && sTabName.equals("SPRACHE"))
                {
                	SQL Qry=new SQL(g);
                    Qry.add("Kennung",EdtKennung.getText());
                    iFremd=Qry.insert("Sprache",true);
                }
                else if (EdtBezeichnung.Modified())
                {
                  g.exec("update begriff set DefBezeichnung='" + sBez + "' where aic_begriff=" + iFremd);
                  EdtBezeichnung.setAktText(sBez);
                  VecVisible.setElementAt(sBez,2);
                }
                if (CbxCombo != null && CbxCombo.isEnabled() && CbxCombo.isSelected()!=((Boolean)VecInvisible.elementAt(PosCbo)).booleanValue())
                {
                  g.exec("update begriff set combo="+(CbxCombo.isSelected()?"1":"null")+" where aic_begriff="+iFremd);
                  VecVisible.setElementAt(Static.JaNein(CbxCombo.isSelected()),SpCombo);
                  VecInvisible.setElementAt(new Boolean(CbxCombo.isSelected()),PosCbo);
                }
                //g.testInfo("iTab="+iTab);
                if (iTab==0)
                  iTab=g.TabTabellenname.getAic("SPALTE");
		g.setBezeichnung((String)VecVisible.elementAt(SpFremdSprache),EdtFremdSprache.getText(),iTab,iFremd,iSprache);
		//if(!(((String)VecInvisible.elementAt(6)).equals(EdtFremdTitel.getText()) && ((String)VecInvisible.elementAt(5)).equals(MemFremdSprache.getValue())))
		if (MemFremdSprache.Modified() || EdtFremdTitel.Modified() || EdtFremdHeader.Modified())
			g.setMemo(MemFremdSprache.getValue(),EdtFremdTitel.getText(),EdtFremdHeader.getText(),iTab,iFremd,iSprache);
                if(!(((String)VecInvisible.elementAt(8)).equals(TooltipFremd.getValue())))
			g.setTooltip(TooltipFremd.getValue(),iTab,iFremd,iSprache);
        if (BtnBild.Modified())
		  g.setImage(BtnBild.getFilename(),iTab,iFremd,Global.iSpSw);
        if (BtnBildFX.Modified())
		  g.setImage(BtnBildFX.getFilename(),iTab,iFremd,Global.iSpFX);
        if (BtnBildSel.Modified())
  		  g.setImage(BtnBildSel.getFilename(),iTab,iFremd,Global.iZSel);
                if (CbxFix != null && CbxFix.isEnabled() && CbxFix.isSelected()!=((Boolean)VecInvisible.elementAt(PosFix)).booleanValue())
                {
                  if (iTab==g.iTabBegriff)
                    g.exec("update begriff set fixe_sprache="+(CbxFix.isSelected()?"1":"null")+" where aic_begriff="+iFremd);
                  else if (iTab==g.iTabStamm)
                    g.exec("update daten_memo set fix="+(CbxFix.isSelected()?"1":"null")+" where aic_tabellenname="+iTab+" and aic_sprache="+iSprache+" and aic_fremd="+iFremd);
                  VecVisible.setElementAt(Static.JaNein(CbxFix.isSelected()),SpFix);
                  VecInvisible.setElementAt(new Boolean(CbxFix.isSelected()),PosFix);
                }
		if (iSprache == g.getSprache())
		{
			VecVisible.setElementAt(EdtFremdSprache.getText(),SpStdSprache);
			bChange=true;
		}
		VecVisible.setElementAt(EdtFremdSprache.getText(),SpFremdSprache);
		VecVisible.setElementAt(BtnBild.getFilename(),5);
		VecVisible.setElementAt(BtnBildFX.getFilename(),6);
		VecInvisible.setElementAt(BtnBild.getFilename(),1);
		VecInvisible.setElementAt(BtnBildFX.getFilename(),2);
		VecInvisible.setElementAt(MemFremdSprache.getValue(),5);
		VecInvisible.setElementAt(EdtFremdTitel.getText(),6);
        VecInvisible.setElementAt(TooltipFremd.getValue(),8);
        if (sTabName.equals("BEGRIFF"))
        	VecInvisible.setElementAt(EdtFremdHeader.getText(),12);
		Out.folderChanged(NodeParent);
	}

	private void nextNode()
	{
		JCOutlinerNode NodeSelected = Out.getSelectedNode();
		Vector VecNodes = NodeSelected.getParent().getChildren();
		if(VecNodes.size()-1>VecNodes.indexOf(NodeSelected))
		{
			Out.selectNode(Out.getNextNode(NodeSelected),null);
		}
		else
		{

		}
	}

        private void LoadParameter()
        {
          Parameter Para = new Parameter(g,"Sprache2");
          Para.getParameter("Option1",true,false);
          if(Para.bGefunden)
          {
            Out.setColumnWidth(0,Para.int1);
            Out.setColumnWidth(1,Para.int2);
            Out.setColumnWidth(2,Para.int3);
            Out.setColumnWidth(3,Para.int4);
            iW[0]=Para.int1;
            iW[1]=Para.int2;
            iW[2]=Para.int3;
            iW[3]=Para.int4;
          }
          Para.getParameter("Option2",true,false);
          if(Para.bGefunden)
          {
            Out.setColumnWidth(4,Para.int1);
            Out.setColumnWidth(5,Para.int2);
            CbxBild.setSelected((Para.int3&1)>0);
            //CbxMemo.setSelected((Para.int3&2)>0);
            iW[4]=Para.int1;
            iW[5]=Para.int2;
            iW[6]=Para.int3;
          }
          Para.free();
        }

        private void SaveParameter()
        {
          int i1=Out.getColumnWidth(0);
          int i2=Out.getColumnWidth(1);
          int i3=Out.getColumnWidth(2);
          int i4=Out.getColumnWidth(3);

          int i5=Out.getColumnWidth(4);
          int i6=Out.getColumnWidth(5);
          int i7=(CbxBild.isSelected()?1:0);//+(CbxMemo.isSelected()?2:0);

          Parameter Para = new Parameter(g,"Sprache2");
          if (i1!=iW[0] || i2!=iW[1] || i3!=iW[2] || i4!=iW[3])
            Para.setParameter("Option1","",i1,i2,i3,i4,true,false);
          if (i5!=iW[4] || i6!=iW[5] || i7!=iW[6])
            Para.setParameter("Option2","",i5,i6,i7,0,true,false);

          Para.free();
        }

        private static final int SpKennung=0;
        private static final int SpDefBezeichnung=2;
        private static final int SpStdSprache=3;
        private static final int SpFremdSprache=4;
        //private static final int SpBild=5;
        private static final int SpCombo=6;
        private static final int SpFix=7;

	// add your data members here
        private boolean bNeu=false;
	private boolean bChange=false;
	private Global g;
	//private JLabel LblFremdSprache = new JLabel("Fremdsprache",SwingConstants.LEFT);
	private JLabel LblStdSprache = new JLabel("Standardsprache",SwingConstants.LEFT);
	private Text EdtKennung = new Text("",40,Text.KENNUNG);
        private Text EdtBezeichnung = new Text("",255);
	private JTextField EdtStdSprache = new JTextField();
        private JCheckBox CbxCombo=null;
        private JCheckBox CbxFix=null;
	private Text EdtFremdSprache = new Text("",255);
	private AUTextArea MemStdSprache;
	private AUTextArea MemFremdSprache;
        private AUTextArea TooltipStd;
	private AUTextArea TooltipFremd;
	private Text EdtFremdTitel = new Text("",40);
	private Text EdtStdTitel = new Text("",40);
	private Text EdtStdHeader = new Text("",255);
	private Text EdtFremdHeader = new Text("",255);
	String sTabName=null;
	//private JLabel LblSprache;
	private ComboSort CboSprache;
	private AUOutliner Out= new AUOutliner(new JCOutlinerFolderNode(""));
        private boolean bSpalte=false;
	//private Tabellenspeicher TabSprache;
	//private JButton BtnHelp;
        private JButton BtnTsearch;
	private JButton BtnTranslate;
        private JButton BtnNew;
	private JButton BtnSave;
	private JButton BtnExport;
	private JButton BtnImport;
	private JButton BtnAbbruch;
	private JButton BtnBeenden;
	private BildEingabe BtnBild;
	private BildEingabe BtnBildFX;
	private BildEingabe BtnBildSel;
	//private JLabel LblBild = new JLabel("",SwingConstants.CENTER);
	private JCheckBox CbxBild;
	//private JCheckBox CbxMemo;
	//private JCheckBox CbxSave = new JCheckBox("Speichern",false);
	private JPanel Pnl_Bild;
	private JPanel Pnl_BildFX;
	private JPanel Pnl_BildSel;
    private JPanel Pnl_Memo;
    private JPanel Pnl_Tooltip;
    private int[] iW = {0,0,0,0,0,0,0};
    private static int PosCbo=9;
    private static int PosFix=10;
}

