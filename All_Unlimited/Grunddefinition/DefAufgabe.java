package All_Unlimited.Grunddefinition;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.JDialog;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

//import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
//import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.StammEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Print.Druckdefinition;
import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;

public class DefAufgabe extends Formular {
	
	  private Global g;
	  private static DefAufgabe Self=null;
	  private AUOutliner Out = new AUOutliner(null);
	  private AUOutliner OutS = new AUOutliner(null);
	  private AUOutliner OutB = new AUOutliner(null);
	  private JDialog DlgAufgabe=null;
	  private JDialog DlgStatus=null;
	  private ActionListener AL;
	  private AUCheckBox CbxFPers;
	  private AUCheckBox CbxFAllg;
	  private AUCheckBox CbxFMandant;
	  // Aufgabe
	  private Text TxtBezA=new Text("",99);
	  private Text TxtDefBezA=new Text("",99);
	  private Text TxtKenA=new Text("",98,Text.KENNUNG);
	  private Text TxtBildA=new Text("",50);
	  private Text TxtDarA=new Text("",40);
	  private ComboSort CboProg;
	  private ComboSort CboAT; // Abschlusstyp
	  private AUCheckBox CbxTab;
	  private AUCheckBox CbxStepper;
	  private AUCheckBox CbxPers;
	  private AUCheckBox CbxTod;
	  private AUCheckBox CbxJeder;
	  private AUCheckBox CbxTest;
	  private AUCheckBox CbxDetailaufgabe;
	  private AUCheckBox CbxHR;
	  private AUTextArea TxtMemoA;
	  private AUTextArea TxtTTA;
	  //Status
//	  private ComboSort CboAufgabe;
	  private Text TxtBezS=new Text("",99);
	  private Text TxtKenS=new Text("",40,Text.KENNUNG);
	  private Text TxtBildS=new Text("",50);
	  private ComboSort CboSProg;
	  private AUTextArea TxtMemoS;
	  private AUTextArea TxtTTS;  
	  private AbfrageEingabe CboAbfrage;
	  private ComboSort CboHS;
	  private StammEingabe CboStamm;
	  private AUCheckBox CbxPeriode;
	  private AUCheckBox CbxZusatzinfo;
	  private AUCheckBox CbxInaktiv;
	  private AUCheckBox CbxTest2;
	  private JRadioButton RadOffen;
	  private JRadioButton RadTag;
	  private JRadioButton RadWoche;
	  private JRadioButton RadMonat;
	  private JRadioButton RadQuartal;
	  private JRadioButton RadJahr;
	  
	  private JButton BtnNeuS=null;
	  private JButton BtnEdit=null;
	  private JButton BtnCopy=null;
	  private JButton BtnDel=null;
	  private JButton BtnHinzu=null;
	  private JButton BtnWeg=null;
	  
	  private ComboSort CboProg2;
	  private Tabellenspeicher TabB=null;
	  private Tabellenspeicher TabS=null;
	  
	  private int iAufgabe=0;
	  private int iStatus=0;
	  private int iBG_Aufgabe=0;
	  private int iSync=0;
	  private ComboSort CboDar=null;
	  private Text TxtBezD=new Text("",60);
	  private Text TxtKenD=new Text("",20,Text.KENNUNG);
	  private JDialog DlgDar;
	  private int iTabStatus=0;
	  
	  // bits für Aufgaben
	  public static final int TAB =1;
	  public static final int STEP=2;
	  public static final int PS  =4; // persönlich
	  public static final int DA  =8; // Detailaufgabe
	  public static final int HR  =16;// HumanResources
	  // bits für Status
	  public static final int PER=8;
	  public static final int ZA =7;
	  public static final int Z_O=0;
	  public static final int Z_T=1;
	  public static final int Z_W=2;
	  public static final int Z_M=3;
	  public static final int Z_Q=4;
	  public static final int Z_J=5;
	  public static final int ZI  =0x0010; // Zusatzinfo
	  
	  // bits für Aufgabe und Status
	  public static final int TEST=0x4000; // nur zum testen, nicht beim Kunden 
	  public static final int TOD =0x8000; // inaktiv
	  
	  // bits für Status-Zuordnung
	  public static final int AUFG=0x10000; // Aufgabe-Status-Zuordnung
	
	public static DefAufgabe get(Global rg,int riAufgabe)
	  {
		if (Self==null)
			Self=new DefAufgabe(rg);
//	        return Self==null ? new DefAufgabe(rg):Self;
        Self.RefreshAll(riAufgabe);
	    Self.show();
	    return Self;
	  }

	  public static void free()
	  {
//		  Static.printError("DefAufgabe.free: "+Self);
	        if (Self!=null)
	        {
	                Self.g.winInfo("DefAufgabe.free");
	                Self.thisFrame.dispose();
	                Self=null;
	        }
	  }

	  private DefAufgabe(Global glob)
	  {
	        super("Aufgabe",null,glob);
	        g=glob;
//	        checkKonvert(g);
//	        g.winInfo("DefAufgabe.create");
	        //long lClock=Static.get_ms();
	        Self=this;
	        Build();
	  }
	  
//	  public static void checkKonvert(Global g)
//	  {
////		  int iAnz1=SQL.getInteger(g, "select count(*) from status_zuordnung");
//		  int iAnz2=SQL.getInteger(g, "select count(*) from status_zuordnung where"+g.bit("Pos", AUFG));
//		  if (/*iAnz1>0 &&*/ iAnz2==0)
//		  {
////			  g.fixInfo("Konvertierung nötig, da "+iAnz2+"/"+iAnz1);
//			  if (g.MS())
//                  g.exec("ALTER TABLE STATUS ALTER COLUMN Aufgabe int null");
//			  else
//				  g.exec("alter table STATUS MODIFY Aufgabe int null");
//			  
//			  Version.addIntSpalte(g,"STATUS","sProg","aic_code","code",true);
//			  
//			  Tabellenspeicher TabS=new Tabellenspeicher(g,"select aic_status,Aufgabe,Reihenfolge from status",true);
//			  SQL Qry=new SQL(g);
//			  for (TabS.moveFirst();!TabS.out();TabS.moveNext())
//			  {
//				  Qry.add("aic_status", TabS.getI("aic_status"));
//				  Qry.add("aic_begriff", TabS.getI("Aufgabe"));
//				  Qry.add("Pos", TabS.getI("Reihenfolge")+AUFG);
//				  Qry.insert("Status_Zuordnung", false);
//			  }
//			  Qry.free();
//			  g.exec("Update Status set Aufgabe=null");
//		  }
//	  }
	  
	  private void Build()
	  {
		iTabStatus=g.TabTabellenname.getAic("STATUS");
		  iBG_Aufgabe=g.TabBegriffgruppen.getAic("Aufgabe");
		  g.initOutliner(Out,new String[] {"DefBezeichnung","Kennung","Aic","Filter_Prog","Nr","Stt","Anzahl","Darstellung","Mandant","Bild","Typ_Stamm","Change","Memo","Pers","Detail","Bezeichnung"});
		  g.initOutliner(OutS,new String[] {"Bezeichnung","Kennung","Aic","Filter","Darstellung","Bild","Stamm","Memo"});
		  g.initOutliner(OutB,new String[] {"DefBezeichnung","Aic","Kennung","Prog","Stt"});

		  int iBG_Prog=g.TabBegriffgruppen.getAic("Programm");
		  CboProg = new ComboSort(g);
		  CboProg.fillBegriffTable(iBG_Prog,true,true);
		  CboSProg = new ComboSort(g);
		  CboSProg.fillBegriffTable(iBG_Prog,true,true);
		  CboProg2 = new ComboSort(g);
		  CboProg2.fillBegriffTable(iBG_Prog,true,true);
		  CboAT = new ComboSort(g);
		  CboAT.fillCbo("select aic_abschlusstyp,kennung"+g.AU_Bezeichnung("Abschlusstyp")+" from abschlusstyp", "Abschlusstyp", true, false);
		  JPanel PnlProg = getFrei("Combo Prog");
		  if (PnlProg != null)
			  PnlProg.add(CboProg2);
		  CboProg2.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED)
						RefreshAll(iSync);				
				}
			});
		  
		    JPanel PnlOutliner = getFrei("Outliner");
		    PnlOutliner.add(Out);
//		    Out.addItemListener(new JCItemListener()
//			{
//				public void itemStateChanged( JCItemEvent ev )
//				{
//					if(ev.getStateChange() == ItemEvent.SELECTED)
//	                   EnableButtons();
//					if(ev.getStateChange() == ItemEvent.ITEM_STATE_CHANGED)
//					{
//						g.fixtestError("ITEM_STATE_CHANGED:"+Static.print(ev.getSource()));
//					}
//				}
//			});
		    Out.addItemListener(new JCOutlinerListener ()
	          {
	              public void outlinerNodeSelectEnd(JCOutlinerEvent ev) {}
	              public void outlinerNodeSelectBegin(JCOutlinerEvent ev) {}
	              public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev) {}
	              public void itemStateChanged(JCItemEvent ev)
	              {
						if(ev.getStateChange() == ItemEvent.SELECTED)
		                   EnableButtons();	  
	              }
	              public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
	              {
	            	  JCOutlinerNode NodSelected =Out.getSelectedNode();
	            	  int iL=NodSelected != null ? NodSelected.getLevel():0;
//	            	  g.fixtestError("outlinerFolderStateChangeBegin: Level="+iL+", State="+ev.getNewState());
	            	  if (iL==2 && ev.getNewState() == BWTEnum.FOLDER_OPEN_ALL)
	            	  {
//	            		  g.fixtestError("Laden für "+NodSelected.getLabel());
	            		  addZuordnung((int)NodSelected.getUserData(),(JCOutlinerFolderNode)NodSelected);
	            	  }
	              }
	          });
		    JPanel PnlOutS = getFrei("OutlinerStatus");
		    if (PnlOutS != null)
		    	PnlOutS.add(OutS);
//		    OutS.addItemListener(new JCItemListener()
//			{
//				public void itemStateChanged( JCItemEvent ev )
//				{
//					if(ev.getStateChange() == ItemEvent.SELECTED)
//	                   EnableButtons();
//				}
//			});
		    
		    JPanel PnlOutBeg = getFrei("Outliner Begriff");
		    PnlOutBeg.add(OutB);
		    OutB.addItemListener(new JCItemListener()
			{
				public void itemStateChanged( JCItemEvent ev )
				{
					if(ev.getStateChange() == ItemEvent.SELECTED)
	                   EnableButtons();
				}
			});
		    
		    CbxFPers=getCheckbox0("pers");
		    CbxFAllg=getCheckbox0("allgemein");
		    CbxFMandant=getCheckbox0("Mandant");
		    CbxFPers.setSelected(true);
		    CbxFAllg.setSelected(true);	
		    addButtons();
		    addPopup();
	  }
	
	  private void addButtons()
	  {
		    AL=new ActionListener()
	        {
	          public void actionPerformed(ActionEvent ev)
	          {
	            String s = ev.getActionCommand();
	            if (s.equals("NeuAufgabe"))
	            	EditAufgabe(0);
	            else if (s.equals("NeuStatus"))
	            	EditStatus(0);
	            else if (s.equals("Edit"))
	            	Edit();
	            else if (s.equals("EditStatus"))
	            	EditStatus((int)OutS.getSelectedNode().getUserData());
	            else if (s.equals("InfoStatus"))
	            	getTabSZ((int)OutS.getSelectedNode().getUserData()).showGrid("Info Status "+Sort.gets(OutS.getSelectedNode().getLabel()), thisJFrame());
//	            	new Tabellenspeicher(g,"select * from status_zuordnung where aic_status="+(int)OutS.getSelectedNode().getUserData(),true)
	            else if (s.equals("DelStatus2"))
		              DelStatus2();
	            else if (s.equals("editBegriff"))
	            	EditBegriff(Sort.geti(OutB.getSelectedNode().getUserData()));
	            else if (s.equals("Copy"))
	            	Copy();
	            else if (s.equals("Loeschen"))
	              Del();
	            else if (s.equals("DelStatus"))
	              DelStatus();
//	            else if (s.equals("Speichern"))
//	              Save();
	            else if (s.equals("Refresh"))
	              RefreshAll(iSync);
	            else if (s.equals("Beenden"))
	              hide();
	            else if (s.equals("OkA"))
		          setAufgabe();
	            else if (s.equals("AbbruchA"))
	              DlgAufgabe.setVisible(false);
	            else if (s.equals("OkS"))
		          setStatus();
	            else if (s.equals("AbbruchS"))
	            	hideDlg(DlgStatus);
	            else if (s.equals("up") || s.equals("down"))
	              Verschieben(s.equals("up"));
	            else if (s.equals("hinzu"))
	              Hinzu();
	            else if (s.equals("weg"))
		          Weg();
	            else if (s.equals("DarNeu"))
                    DarNeu();
                else if (s.equals("DarDel"))
	                DarDel();
	            else if (s.equals("OkD"))
	                DarSave();
	            else if (s.equals("AbbruchD"))
	                DlgDar.setVisible(false);
	            else if (s.equals("RefreshDB"))
	            	g.sendWebDB("refreshDB",null,thisJFrame());
	            else if (s.equals("Abfrage") || s.equals("Abfrage2"))
	                DefAbfrageAufrufen(s.equals("Abfrage"));
	            else if (s.equals("sync"))
	            	Sync();
	            else if (s.equals("show Frame"))
	                Darstellung.showUsed(g,Sort.geti(OutB.getSelectedNode().getUserData()),thisJFrame());
	            else if (s.equals("showAufgabe"))
	                Darstellung.showUsed(g,Sort.geti(Out.getSelectedNode().getUserData()),thisJFrame());
	            else
	            	Static.printError("DefAufgabe: "+s+" wird noch nicht unterstützt");
	          }
	        };
	        g.BtnAdd(getButton("neuAufgabe"),"NeuAufgabe",AL);
	        BtnNeuS=g.BtnAdd(getButton("neuStatus"),"NeuStatus",AL);
	        BtnEdit=g.BtnAdd(getButton("Edit"),"Edit",AL);
	        BtnCopy=g.BtnAdd(getButton("Kopieren"),"Copy",AL);
	        BtnDel=g.BtnAdd(getButton("Loeschen"),"Loeschen",AL);
	        g.BtnAdd(getButton("Refresh"),"Refresh",AL);
//	        g.BtnAdd(getButton("Speichern"),"Speichern",AL);
	        g.BtnAdd(getButton("Beenden"),"Beenden",AL);
	        g.BtnAdd(getButton("Pfeil oben"),"up",AL);
	        g.BtnAdd(getButton("Pfeil unten"),"down",AL);
	        BtnHinzu=g.BtnAdd(getButton(">"),"hinzu",AL);
	        BtnWeg=g.BtnAdd(getButton("<"),"weg",AL);
	        g.BtnAdd(getButton("RefreshDB"),"RefreshDB",AL);
	        g.BtnAdd(getFormularButton("Abfrage"),"Abfrage",AL);
	        g.BtnAdd(getButton("Abfrage2"),"Abfrage2",AL);
	        g.CbxAdd(CbxFPers,"Refresh",AL);
	        g.CbxAdd(CbxFAllg,"Refresh",AL);
	        g.CbxAdd(CbxFMandant,"Refresh",AL);
	        g.BtnAdd(getButton("show Frame"),"show Frame",AL);
	  }
	  
	  private void addPopup()
	  {
		  JPopupMenu popup= new JPopupMenu();
		    g.addMenuItem("Edit",popup,"Edit",AL);
		    g.addMenuItem("neuAufgabe",popup,"NeuAufgabe",AL);
		    g.addMenuItem("Loeschen",popup,"Loeschen",AL);
		    g.addMenuItem("Kopieren",popup,"Copy",AL);
		    g.addMenuItem("showAufgabe",popup,"showAufgabe",AL);
		    popup.addSeparator();
//			g.addMenuItem("show",popup,null,AL);
			g.addMenuItem("sync",popup,null,AL);
//			g.addMenuItem("History",popup,null,AL);
			
			MouseListener ML=new MouseListener()
	          {
	            public void mousePressed(MouseEvent ev) {}
	            public void mouseClicked(MouseEvent ev)
	            {
	            	if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM) && g.Def())
	            		popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
	            }
	            public void mouseEntered(MouseEvent ev) {}
	            public void mouseExited(MouseEvent ev)  {}
	            public void mouseReleased(MouseEvent ev){}
	          };
			Out.getOutliner().addMouseListener(ML);
			
		  JPopupMenu popupS= new JPopupMenu();
		    g.addMenuItem("editStatus",popupS,"EditStatus",AL);
		    g.addMenuItem("neuStatus",popupS,"NeuStatus",AL);
		    g.addMenuItem("infoStatus",popupS,"InfoStatus",AL);
		    g.addMenuItem("delStatus2",popupS,"DelStatus2",AL);
		    g.addMenuItem("delStatus",popupS,"DelStatus",AL);
		    
			OutS.getOutliner().addMouseListener(new MouseListener()
	          {
	            public void mousePressed(MouseEvent ev) {}
	            public void mouseClicked(MouseEvent ev)
	            {
	            	if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM) && g.Def())
	            		popupS.show((Component)ev.getSource(), ev.getX(), ev.getY());
	            }
	            public void mouseEntered(MouseEvent ev) {}
	            public void mouseExited(MouseEvent ev)  {}
	            public void mouseReleased(MouseEvent ev){}
	          });
			
			JPopupMenu popupB= new JPopupMenu();
			g.addMenuItem("show Frame",popupB,null,AL);
			g.addMenuItem("Edit",popupB,"editBegriff",AL);
			OutB.getOutliner().addMouseListener(new MouseListener()
	          {
	            public void mousePressed(MouseEvent ev) {}
	            public void mouseClicked(MouseEvent ev)
	            {
	            	if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM) && g.Def())
	            		popupB.show((Component)ev.getSource(), ev.getX(), ev.getY());
	            }
	            public void mouseEntered(MouseEvent ev) {}
	            public void mouseExited(MouseEvent ev)  {}
	            public void mouseReleased(MouseEvent ev){}
	          });
	  }
	  
	  private void Verschieben(boolean bUp)
      {
        JCOutlinerNode[] NodSelected = Out.getSelectedNodes();
        int iL=NodSelected != null && NodSelected.length>0 ? NodSelected[0].getLevel():0;
        if (iL>0) 
        {
              for(int i=0;NodSelected.length>i;i++)
              {
                      Vector<JCOutlinerNode> VecNodes = NodSelected[i].getParent().getChildren();
                      int iPos=VecNodes.indexOf(NodSelected[i]);
                      if(iPos+(bUp?-1:1)>=0 && iPos+(bUp?-1:1)<VecNodes.size())
                      {
                              JCOutlinerNode NodAndere = VecNodes.elementAt(iPos+(bUp?-1:1));
                              ((Vector)NodAndere.getLabel()).setElementAt(iPos+1, 4);
                              ((Vector)NodSelected[i].getLabel()).setElementAt(iPos+(bUp ? 0:2), 4);
                              if (iL==1)
                              {
	                              g.exec("update begriff set pos="+(iPos+1)+" where aic_begriff="+NodAndere.getUserData());
	                              g.exec("update begriff set pos="+(iPos+(bUp ? 0:2))+" where aic_begriff="+NodSelected[i].getUserData());
                              }
                              else if (iL==2)
                              {
                            	  int iAufg=(int)NodAndere.getParent().getUserData();
	                              g.exec("update status_zuordnung set pos="+(iPos+1+AUFG)+" where aic_status="+NodAndere.getUserData()+" and aic_begriff="+iAufg);
	                              g.exec("update status_zuordnung set pos="+(iPos+(bUp ? 0:2)+AUFG)+" where aic_status="+NodSelected[i].getUserData()+" and aic_begriff="+iAufg);
                              }
                              else if (iL==3)
                              {
	                              g.exec("update status_zuordnung set pos="+(iPos+1)+" where aic_begriff="+NodAndere.getUserData());
	                              g.exec("update status_zuordnung set pos="+(iPos+(bUp ? 0:2))+" where aic_begriff="+NodSelected[i].getUserData());
                              }
                              VecNodes.setElementAt(NodAndere,iPos);
                              VecNodes.setElementAt(NodSelected[i],iPos+(bUp?-1:1));
                      }
              }

//              change(NodSelected[0].getParent());
//              checkReihenfolge(NodSelected[0].getParent());
              Out.folderChanged(Out.getRootNode());
        }
//        else if (iL==3)
//        {
////        	g.fixtestError("verschieben auf Ebene Zuordnung");
//        	JCOutlinerNode NodS=Out.getSelectedNode();
//        	Vector<JCOutlinerNode> VecNodes = NodS.getParent().getChildren();
//            int iPos=VecNodes.indexOf(NodS);
//            if(iPos+(bUp?-1:1)>=0 && iPos+(bUp?-1:1)<VecNodes.size())
//            {
//	            JCOutlinerNode NodAndere = VecNodes.elementAt(iPos+(bUp?-1:1));
//	            ((Vector)NodAndere.getLabel()).setElementAt(iPos+1, 4);
//	            ((Vector)NodS.getLabel()).setElementAt(iPos+(bUp ? 0:2), 4);
//	            VecNodes.setElementAt(NodAndere,iPos);
//	            VecNodes.setElementAt(NodS,iPos+(bUp?-1:1));
//            }
//            Out.folderChanged(Out.getRootNode());
//        }
        	
      }
	  
	  private void Hinzu()
	  {
		  JCOutlinerNode NodB=OutB.getSelectedNode();
		  int iLevelB=NodB==null ? 0:NodB.getLevel();
		  JCOutlinerFolderNode Nod=(JCOutlinerFolderNode)Out.getSelectedNode();
		  int iLevel=Nod==null ? 0:Nod.getLevel();
		  if (iLevel==1)
		  {
			  JCOutlinerNode NodS=OutS.getSelectedNode();
			  int iPos=Nod==null || Nod.getChildren()==null ? 1:Nod.getChildren().size()+1;
			  if (g.exec("insert into STATUS_ZUORDNUNG (aic_status,aic_begriff,pos) values ("+NodS.getUserData()+","+Nod.getUserData()+","+(iPos+AUFG)+")")>0)
				  addStatus2((int)NodS.getUserData(),iPos,Nod);
		  }
		  else if (iLevelB==2 && iLevel==2)
		  {
			  if (Nod.getState() != BWTEnum.FOLDER_OPEN_ALL)
			  {
				  Nod.setState(BWTEnum.FOLDER_OPEN_ALL);
//				  g.fixtestError("Hinzu: addZuordnung");
				  addZuordnung((int)Nod.getUserData(),(JCOutlinerFolderNode)Nod);
			  }
			  Vector Vec=(Vector)NodB.getLabel();
			  
			  int iPos=Nod.getChildren()==null ? 1:Nod.getChildren().size()+1;
//				  if (iStatus>0)
//					  g.exec("update status_zuordnung set pos="+iP+" where aic_status="+iStatus+" and aic_begriff="+iAic);
			  if (g.exec("insert into STATUS_ZUORDNUNG (aic_status,aic_begriff,pos) values ("+Nod.getUserData()+","+NodB.getUserData()+","+iPos+")")>0)
			  {
//				  g.fixtestError("Hinzu Begriff:"+Vec);		  
				  addBegriff(Sort.gets(Vec, 0),Sort.gets(Vec, 2),Sort.geti(Vec, 1),iPos,Nod,0,false);
			  }
		  }
		  else
			  g.fixtestError("Hinzu mit Level"+iLevel+" nicht möglich");
	  }
	  
	  private void Weg()
	  {
		  JCOutlinerNode Nod=Out.getSelectedNode();
		  int iLevel=Nod==null ? 0:Nod.getLevel();
		  if (iLevel==2 || iLevel==3)
		  {
//			  g.fixtestError("Weg:"+Out.getSelectedNode().getLabel());
			  if (iLevel==2)
				  g.exec("delete from STATUS_ZUORDNUNG where aic_status="+Nod.getUserData()+" and aic_begriff="+Nod.getParent().getUserData());
			  else if (iLevel==3)
				  g.exec("delete from STATUS_ZUORDNUNG where aic_status="+Nod.getParent().getUserData()+" and aic_begriff="+Nod.getUserData());
			  Nod.getParent().removeChild(Nod);
			  Out.folderChanged(Nod.getParent());
		  }
	  }
	  
	  private void RefreshAll(int riSync)
	  {	
		  thisFrame.setVisible(false);
//		  g.fixtestError("DefAufgabe.RefreshAll: "+riSync);
		  iSync=riSync;
		  boolean bMandant=CbxFMandant !=null && CbxFMandant.isSelected();
		  boolean bPers=CbxFPers !=null && CbxFPers.isSelected();
		  boolean bAllg=CbxFAllg !=null && CbxFAllg.isSelected();
		  ((JCOutlinerFolderNode)Out.getRootNode()).removeChildren();
		  ((JCOutlinerFolderNode)OutS.getRootNode()).removeChildren();
		  String sProg=CboProg2.isNull() ? "":" and (prog="+CboProg2.getSelectedAIC()+" or prog is null)";
		  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,kennung,defbezeichnung,bits"+g.AU_Bezeichnung("Begriff")+g.AU_Memo("Begriff")+",(select ein from logging where aic_logging=begriff.aic_logging) Log,prog,bildname"+g.AU_Bezeichnung1("Abschlusstyp","Begriff")+
				  " AT,kennzeichen,pos,Tod,aic_mandant,null Node from begriff where aic_begriffgruppe="+iBG_Aufgabe+sProg+(bMandant ? " and aic_mandant="+g.getMandant():g.getReadMandanten("begriff"))+
				  (bPers ? bAllg ? "":" and"+g.bit("bits", PS):bAllg ? " and"+g.bitis("bits", PS, 0):"1=2")+" order by pos",true);
		  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
			  Tab.setInhalt("Node", addAufgabe(Tab.getS("DefBezeichnung"),Tab.getS("Kennung"),CboProg.getBezeichnung(Tab.getI("prog")),Tab.getDate("Log"),Tab.getM("Memo"),Tab.getI("aic_begriff"),Tab.getS("Bildname"),Tab.getS("AT")
					  ,Tab.getS("Kennzeichen"),null,Tab.getI("Pos"),Tab.getB("Tod"),(Tab.getI("Bits")&TEST)>0,Tab.getI("aic_mandant"),(Tab.getI("Bits")&PS)>0,(Tab.getI("Bits")&DA)>0,Tab.getS("Bezeichnung")));
		  TabS=new Tabellenspeicher(g,"select aic_status,aic_code,kennung"+g.AU_Bezeichnung("Status")+g.AU_Memo("Status")+",aufgabe,Bildname,reihenfolge,bits,aic_stamm"+
			  ",(select aic_begriff from abfrage where abfrage=abfrage.aic_abfrage) filter,sProg,null Node,(select count(*) from status_zuordnung where aic_status=status.aic_status and Pos<10000) Anzahl from status order by aufgabe,reihenfolge",true);
		  for (TabS.moveFirst();!TabS.out();TabS.moveNext())
			  if (CboProg2.isNull() || TabS.getI("sProg")==0 || CboProg2.getSelectedAIC()==TabS.getI("sProg"))
			  TabS.setInhalt("Node",addStatus1(TabS.getI("aic_status"),g.TabBegriffe.getBezeichnungS(TabS.getI("filter")),true));
		  Tabellenspeicher TabSZ=new Tabellenspeicher(g,"select aic_status,aic_begriff,pos from status_zuordnung where"+g.bit("pos",AUFG),true);
		  for (TabSZ.moveFirst();!TabSZ.out();TabSZ.moveNext())
			  addStatus2(TabSZ.getI("aic_status"),TabSZ.getI("pos")&0xffff,Tab.posInhalt("aic_begriff",TabSZ.getI("aic_begriff")) ? (JCOutlinerFolderNode)Tab.getInhalt("Node"):null);
//					  addStatus(TabS.getS("bezeichnung"),TabS.getS("Kennung"),g.TabBegriffe.getBezeichnungS(TabS.getI("filter")),TabS.getM("Memo"),TabS.getI("aic_status"),TabS.getI("aic_code"),TabS.getM("Bildname"),TabS.getI("aic_stamm"),
//					  Tab.posInhalt("aic_begriff",TabS.getI("aufgabe")) ? (JCOutlinerFolderNode)Tab.getInhalt("Node"):null,true,TabS.getI("Reihenfolge"),(TabS.getI("Bits")&TOD)>0,(TabS.getI("Bits")&TEST)>0));
		  BegriffeLaden(sProg);
//		  Tabellenspeicher TabZ=new Tabellenspeicher(g,"select * from status_zuordnung order by aic_Status,pos",true);
//		  for (TabZ.moveFirst();!TabZ.out();TabZ.moveNext())
//		  {
//			  int iBeg=TabZ.getI("aic_begriff");
//			  int iPos=g.TabBegriffe.getPos("Aic", iBeg);
//			  String sBez=iPos<0 ? "Aic="+iBeg:g.getBegBez2(iPos);
//			  String sKen=iPos<0 ? Static.sLeer:g.TabBegriffe.getS(iPos,"Kennung");
//			  TabS.posInhalt("aic_status", TabZ.getI("aic_Status"));
//			  JCOutlinerFolderNode Node=(JCOutlinerFolderNode)TabS.getInhalt("Node");
//			  addBegriff(sBez,sKen,iBeg,TabZ.getI("Pos"),Node,TabZ.getI("aic_Status"),true);
//		  }
		  Out.folderChanged(Out.getRootNode());
		  OutB.folderChanged(OutB.getRootNode());
		  thisFrame.setVisible(true);
		  EnableButtons();
	  }
	  
	  private void BegriffeLaden(String sProg)
	  {
		((JCOutlinerFolderNode)OutB.getRootNode()).removeChildren();
		JCOutlinerNodeStyle NodeStyle=null;
		TabB=new Tabellenspeicher(g,"select b.aic_begriff AIC,b.DefBezeichnung bezeichnung,b.kennung,b.aic_stammtyp,b.aic_bewegungstyp,bg.aic_begriffgruppe,b.bits,b.tod,b.web,b.jeder,b.prog,null Node from begriff b"+
		g.join("begriffgruppe","bg","b","begriffgruppe")+" where bg.kennung in('Frame','Modell','Druck','Aufgabe','Hauptschirm','Abfrage') and web=1"+sProg+" order by bg.kennung,bezeichnung",true);
//		SQL Qry = new SQL(g);
//		  if(Qry.open("select b.aic_begriff AIC,b.DefBezeichnung bezeichnung,b.kennung,b.aic_stammtyp,b.aic_bewegungstyp,bg.aic_begriffgruppe,b.bits,b.tod,b.web,b.jeder,b.prog from begriff b"+g.join("begriffgruppe","bg","b","begriffgruppe")+" where bg.kennung in('Frame','Modell','Druck','Aufgabe') and web=1"+sProg+" order by bg.kennung,bezeichnung"))
		  if (TabB != null && !TabB.fehler())	  
			{
				int iBG=0;
				String sBG="";
				Vector<Comparable> VecVisible;
				Vector<Integer> VecInvisible;
				JCOutlinerFolderNode NodeGruppe=null;
				for(TabB.moveFirst();!TabB.out();TabB.moveNext())
				{
					if(iBG!=TabB.getI("aic_begriffgruppe"))
					{
						iBG=TabB.getI("aic_begriffgruppe");
						VecVisible=new Vector<Comparable>();
						VecInvisible=new Vector<Integer>();
						VecInvisible.addElement(new Integer(iBG));
		                                int iPosBG=g.TabBegriffgruppen.getPos("Aic",iBG);
						VecVisible.addElement(g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung"));
		                                VecVisible.addElement(new Integer(iBG));
						sBG=g.TabBegriffgruppen.getS(iPosBG,"Kennung");
						VecVisible.addElement(sBG);
		                NodeGruppe=new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutB.getRootNode());
						NodeGruppe.setUserData(VecInvisible);
						ImageIcon Gif = iPosBG>=0 ? g.LoadImageIcon(g.TabBegriffgruppen.getS(iPosBG,"FILENAME")):null;
						NodeStyle=g.getStyle(Gif!=null?Gif.getImage():null);
						NodeGruppe.setStyle(NodeStyle);
						NodeGruppe.setState(BWTEnum.FOLDER_CLOSED);
					}
					long lBits=TabB.getL("bits");
					if (!sBG.equals("Abfrage") || (lBits&Abfrage.cstExportierbar)>0)
					{
						VecVisible = new Vector<Comparable>();
	                    VecInvisible = new Vector<Integer>();
	
	                    VecVisible.addElement(TabB.getS("bezeichnung"));
	                    VecVisible.addElement(TabB.getInt("AIC"));
	                    VecVisible.addElement(TabB.getS("kennung"));
	                    VecVisible.addElement(g.TabCodes.getBezeichnungS(TabB.getI("prog")));
	                    int iPos=g.TabBegriffe.getPos("Aic", TabB.getI("AIC"));
	                    VecVisible.addElement(iPos<0 ? Static.sLeer:g.getSttBez(iPos));
	                    
	                    JCOutlinerNode Node = new JCOutlinerNode(VecVisible, NodeGruppe);
	                    Node.setUserData(TabB.getI("AIC"));
	                    if (TabB.getB("Tod"))// || sBG.equals("Frame") && (lBits&Formular.cstJavaFX)>0)
	                    {
	                      JCOutlinerNodeStyle NodeStyle2=new JCOutlinerNodeStyle(NodeStyle);
	                      NodeStyle2.setForeground(/*sBG.equals("Frame") ? g.ColJavaFX:*/ g.ColLoeschen);
	                      Node.setStyle(NodeStyle2);
	                    }
	                    else
	                      Node.setStyle(NodeStyle);
	                    TabB.setInhalt("Node", Node);
					}
				}
			}
	  }
	  
	  private void EditAufgabe(int riAufgabe)
	  {
		  iAufgabe=riAufgabe;
		  if (DlgAufgabe==null)
		  {
			  DlgAufgabe= new JDialog(thisJFrame(), "neue Aufgabe", true);
			  DlgAufgabe.getContentPane().setLayout(new BorderLayout(2, 2));
			  	JPanel PnlN=new JPanel(new BorderLayout(2, 2));
		         JPanel Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
		         g.addLabel2(Pnl1, "DefBezeichnung");
		         g.addLabel2(Pnl1, "Bezeichnung");
		         g.addLabel2(Pnl1, "Kennung");
		         g.addLabel2(Pnl1, "Bild");
		         g.addLabel2(Pnl1, "Darstellung");
		         g.addLabel2(Pnl1, "Programm");
		         g.addLabel2(Pnl1, "Abschlusstyp");
		         Pnl1.add(new JLabel());
		         Pnl1.add(new JLabel());
		         Pnl1.add(new JLabel());
		         Pnl1.add(new JLabel());
		         PnlN.add("West", Pnl1);
		         Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
//		         TxtKenA.setEditable(g.Def());
		         Pnl1.add(TxtDefBezA);
		         Pnl1.add(TxtBezA);
		         Pnl1.add(TxtKenA);		
		         Pnl1.add(TxtBildA);
		         Pnl1.add(TxtDarA);
				 Pnl1.add(CboProg);
				 Pnl1.add(CboAT);
//				   JPanel PnlCbx=new JPanel(new GridLayout(0,2,2,2));
				   CbxTab=getCheckboxM("Tab",false);
				   CbxStepper=getCheckboxM("Stepper",false);
				   CbxPers=getCheckboxM("pers",false);
				   CbxTod=getCheckboxM("Tod",false);
				   CbxJeder=getCheckboxM("Jeder",false);
				   CbxTest=getCheckboxM("TestA",false);
                   CbxDetailaufgabe=getCheckboxM("Detailaufgabe",false);
                   CbxHR=getCheckboxM("HR",false);
				 Pnl1.add(g.addTwo(CbxTab,CbxStepper));
				 Pnl1.add(g.addTwo(CbxPers,CbxTod));
				 Pnl1.add(g.addTwo(CbxTest,CbxJeder));
				 Pnl1.add(g.addTwo(CbxHR,CbxDetailaufgabe));
				 PnlN.add("Center", Pnl1);
		        DlgAufgabe.getContentPane().add("North", PnlN);
		        JTabbedPane PnlMemo=new JTabbedPane();
		         TxtMemoA=new AUTextArea(g,0);
		         PnlMemo.add(g.getShow("Memo"),TxtMemoA);
		         TxtTTA=new AUTextArea(g,0);
		         PnlMemo.add(g.getShow("Tooltip"),TxtTTA);
		        DlgAufgabe.getContentPane().add("Center", PnlMemo);
		        JPanel Pnl = new JPanel(new GridLayout(1, 0, 2, 2));
		         JButton BtnOk = g.getButton("Ok");
		         JButton BtnAbbruch = g.getButton("Abbruch");
		         g.BtnAdd(BtnOk, "OkA", AL);
		         g.BtnAdd(BtnAbbruch, "AbbruchA", AL);
		         Pnl.add(BtnOk);
		         Pnl.add(BtnAbbruch);
		        DlgAufgabe.getContentPane().add("South", Pnl);
		        DlgAufgabe.pack();
		  }
		  Static.centerComponent(DlgAufgabe,thisJFrame());
		  if (iAufgabe==0)
		  {
			  TxtBezA.setText("");
			  TxtDefBezA.setText("");
			  TxtKenA.setEditable(g.Def());
			  TxtKenA.setText("");
			  TxtBildA.setText("");
			  TxtDarA.setText("");
			  TxtMemoA.setText("");
			  TxtTTA.setText("");
			  CboProg.setSelectedAIC(0);
			  CboAT.setSelectedAIC(0);
			  CbxTab.setSelected(false);
			  CbxStepper.setSelected(false);
			  CbxPers.setSelected(false);
			  CbxTest.setSelected(true);
			  CbxTod.setSelected(false);
			  CbxJeder.setSelected(false);
			  CbxDetailaufgabe.setSelected(false);
			  CbxHR.setSelected(false);
			  DlgAufgabe.setTitle(g.getShow("new")+" "+g.getShow("Aufgabe"));
		  }
		  else
		  {
			  boolean bCopy=iAufgabe<0;
			  if (bCopy)
				  iAufgabe=-iAufgabe;
			  DlgAufgabe.setTitle(g.getShow(bCopy ? "copy":"edit")+" "+g.getShow("Aufgabe"));
			  JCOutlinerNode Nod=Out.getSelectedNode();
			  Vector<Comparable> Vec=(Vector<Comparable>)Nod.getLabel();
			  TxtDefBezA.setText(Sort.gets(Vec, 0));
			  TxtKenA.setEditable(g.Def() && bCopy);
			  TxtKenA.setText(bCopy ? "":Sort.gets(Vec, 1));
			  TxtMemoA.setText(g.getMemo2(iAufgabe));
			  int iPos=g.TabBegriffe.getPos("Aic", iAufgabe);
			  TxtBezA.setText(iPos<0 ? "":g.TabBegriffe.getS(iPos,"Bezeichnung"));
			  TxtTTA.setText(iPos<0 ? "":g.TabBegriffe.getM(iPos,"Tooltip"));
			  Tabellenspeicher Tab=new Tabellenspeicher(g,"select prog,aic_abschlusstyp,bits,tod,jeder from begriff where aic_begriff="+iAufgabe,true);
			  CboProg.setSelectedAIC(Tab.getI("prog"));//SQL.getInteger(g, "select prog from begriff where aic_begriff=?",0,""+iAufgabe));
			  CboAT.setSelectedAIC(Tab.getI("aic_abschlusstyp"));//SQL.getInteger(g, "select aic_abschlusstyp from begriff where aic_begriff=?",0,""+iAufgabe));
			  //TODO Bild und Bits
			  iBits=Tab.getI("bits");//SQL.getInteger(g, "select bits from begriff where aic_begriff=?",0,""+iAufgabe);
			  TxtBildA.setText(Sort.gets(Vec, 9));
			  TxtDarA.setText(Sort.gets(Vec, 7));
			  CbxTab.setSelected((iBits&TAB)>0);
			  CbxStepper.setSelected((iBits&STEP)>0);
			  CbxPers.setSelected((iBits&PS)>0);
			  CbxTod.setSelected(Tab.getB("Tod"));
			  CbxJeder.setSelected(Tab.getB("Jeder"));
			  CbxTest.setSelected((iBits&TEST)>0);
			  CbxDetailaufgabe.setSelected((iBits&DA)>0);
			  CbxHR.setSelected((iBits&HR)>0);
			  if (bCopy)
				  iAufgabe=-iAufgabe;
		  }
	      DlgAufgabe.setVisible(true);
	  }
	  
//	  private JPanel TwoInOne(JCheckBox Cbx1,JCheckBox Cbx2)
//	  {
//	    JPanel Pnl=new JPanel(new GridLayout(1,2,2,2));
//	    Pnl.add(Cbx1);
//	    if (Cbx2!=null)
//	  	  Pnl.add(Cbx2);
//	    return Pnl;
//	  }
	  
	  private void EditStatus(int riStatus)
	  {
//		  g.fixtestError("EditStatus="+riStatus);
		  JCOutlinerNode NodS=OutS.getSelectedNode();
//		  int iLevel=Nod==null ? 0:Nod.getLevel();
//		  if (iLevel==0 || riStatus==0 && iLevel>1) 
//			  return;
//		  if (iLevel==1)
//			  iAufgabe=(int)Nod.getUserData();
//		  else if (iLevel==2)
//			  iAufgabe=(int)Nod.getParent().getUserData();
		  iStatus=riStatus;
		  if (DlgStatus==null)
		  {
//			  thisJFrame().setEnabled(true);
			  DlgStatus= new JDialog(thisJFrame(), "neuer Status", false);
			  DlgStatus.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			  DlgStatus.getContentPane().setLayout(new BorderLayout(2, 2));
			  	JPanel PnlN=new JPanel(new BorderLayout(2, 2));
		         JPanel Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
//		         g.addLabel2(Pnl1, "Aufgabe");
		         g.addLabel2(Pnl1, "Bezeichnung");
		         g.addLabel2(Pnl1, "Kennung");
		         g.addLabel2(Pnl1, "Bild");
		         g.addLabel2(Pnl1, "Filter");
		         g.addLabel2(Pnl1, "Hauptschirm");
		         g.addLabel2(Pnl1, "Stamm");
		         g.addLabel2(Pnl1, "Darstellung");
		         g.addLabel2(Pnl1, "Programm");
		         Pnl1.add(new JLabel());
		         Pnl1.add(new JLabel());
		         g.addLabel2(Pnl1, "Zeitraum");
		         Pnl1.add(new JLabel());
		        PnlN.add("West", Pnl1);
		         Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
//		         CboAufgabe = new ComboSort(g);
		         
//		         Pnl1.add(CboAufgabe);
		         Pnl1.add(TxtBezS);
		         Pnl1.add(TxtKenS);
		         Pnl1.add(TxtBildS);
		         CboAbfrage = new AbfrageEingabe(g,thisFrame,true,false);
                 CboAbfrage.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("abits2",Abfrage.cstForStatus),"Abfrage",true);
                 CboStamm = new StammEingabe(DlgStatus,g,SQL.getInteger(g, "select aic_stammtyp from stammtyp where "+g.bit("SttBits", Global.cstForStatus)),0);
                 CboHS=new ComboSort(g);
                 CboHS.fillBegriffTable(g.TabBegriffgruppen.getAic("Hauptschirm"),true,true);
//                 CboStamm.fillStammBitTable(Global.cstForStatus, true);
                 Pnl1.add(CboAbfrage);
                 Pnl1.add(CboHS);
                 Pnl1.add(CboStamm);
                 JPanel PnlDar=new JPanel(new BorderLayout(2,2));
                 CboDar=new ComboSort(g);
                 CboDar.fillBegriffTable(g.TabBegriffgruppen.getAic("Darstellung"),true,true);
                 PnlDar.add("Center",CboDar);
                 if (g.ApplPort())
                 {
                   JButton BtnP=g.getButton("+");
                   JButton BtnM=g.getButton("-");
                   JPanel PnlSub=new JPanel(new GridLayout());
                   PnlSub.add(BtnP);
                   PnlSub.add(BtnM);
       	           g.BtnAdd(BtnP,"DarNeu",AL);
       	           g.BtnAdd(BtnM,"DarDel",AL);
                   PnlDar.add("East", PnlSub);
                 }
                 Pnl1.add(PnlDar);
                 Pnl1.add(CboSProg);
                   CbxPeriode=g.getCheckbox("Periode");
                   CbxZusatzinfo=g.getCheckbox("Zusatzinfo");
                   CbxInaktiv=g.getCheckbox("Tod");
                   CbxTest2=g.getCheckbox("TestS");
                 Pnl1.add(g.addTwo(CbxPeriode,CbxZusatzinfo));
                 Pnl1.add(g.addTwo(CbxInaktiv,CbxTest2));
                   JPanel PnlRad=new JPanel(new GridLayout(0,3,2,2));
                   RadOffen=g.getRadiobutton("offen");
                   RadTag=g.getRadiobutton("Tag");
                   RadWoche=g.getRadiobutton("Woche");
                   RadMonat=g.getRadiobutton("Monat");
                   RadQuartal=g.getRadiobutton("Quartal");
                   RadJahr=g.getRadiobutton("Jahr");
                   ButtonGroup group=new ButtonGroup();
                   group.add(RadOffen);
                   group.add(RadTag);
                   group.add(RadWoche);
                   group.add(RadMonat);
                   group.add(RadQuartal);
                   group.add(RadJahr);
                   PnlRad.add(RadOffen);
                   PnlRad.add(RadTag);
                   PnlRad.add(RadWoche);
                 Pnl1.add(PnlRad);
                   PnlRad=new JPanel(new GridLayout(0,3,2,2));
                   PnlRad.add(RadMonat);
                   PnlRad.add(RadQuartal);
                   PnlRad.add(RadJahr);
                 Pnl1.add(PnlRad);
                PnlN.add("Center", Pnl1);;
		        DlgStatus.getContentPane().add("North", PnlN);
		        JTabbedPane PnlMemo=new JTabbedPane();
		         TxtMemoS=new AUTextArea(g,0);
		         PnlMemo.add(g.getShow("Memo"),TxtMemoS);
		         TxtTTS=new AUTextArea(g,0);
		         PnlMemo.add(g.getShow("Tooltip"),TxtTTS);
		         DlgStatus.getContentPane().add("Center", PnlMemo);
		        JPanel Pnl = new JPanel(new GridLayout(1, 0, 2, 2));
		        JButton BtnOk = g.getButton("Ok");
		        JButton BtnAbbruch = g.getButton("Abbruch");
		        g.BtnAdd(BtnOk, "OkS", AL);
		        g.BtnAdd(BtnAbbruch, "AbbruchS", AL);
		        Pnl.add(BtnOk);
		        Pnl.add(BtnAbbruch);
		        DlgStatus.getContentPane().add("South", Pnl);
		        DlgStatus.pack();
		  }
		  Static.centerComponent(DlgStatus,thisJFrame());
//		  CboAufgabe.fillBegriffTable(g.TabBegriffgruppen.getAic("Aufgabe"),false,true,true);
//		  CboAufgabe.setSelectedAIC(iAufgabe);
		  if (iStatus==0)
		  {
		      TxtKenS.setEditable(g.Def());
			  TxtBezS.setText("");
			  TxtKenS.setText("");
			  TxtMemoS.setText("");
			  TxtTTS.setText("");
			  CboAbfrage.setSelectedAIC(0);
			  CboHS.setSelectedAIC(0);
			  CboStamm.setStamm(0);
			  CboSProg.setSelectedAIC(0);
			  CboDar.setSelectedAIC(0);
			  TxtBildS.setText("");
			  CbxPeriode.setSelected(false);
			  CbxZusatzinfo.setSelected(false);
			  CbxInaktiv.setSelected(false);
			  CbxTest2.setSelected(true);
			  RadOffen.setSelected(true);
			  DlgStatus.setTitle(g.getShow("new")+" "+g.getShow("Status"));
		  }
		  else
		  {
			  //JCOutlinerNode Nod=Out.getSelectedNode();
			  boolean bCopy=iStatus<0;
			  if (bCopy)
				  iStatus=-iStatus;
			  if (!TabS.posInhalt("aic_status",iStatus))
			  {
				  Static.printError("Status "+iStatus+" nicht gefunden");
				  return;
			  }
			  DlgStatus.setTitle(g.getShow(bCopy ? "copy":"edit")+" "+g.getShow("Status"));
			  Vector<Comparable> Vec=(Vector<Comparable>)NodS.getLabel();
			  TxtBezS.setText(Sort.gets(Vec, 0));
		      TxtKenS.setEditable(g.Def() && bCopy);
			  TxtKenS.setText(bCopy ? "":Sort.gets(Vec, 1));
			  TxtMemoS.setText(Sort.gets(Vec, 7));
			  TxtTTS.setText(SQL.getString(g, "select memo2 from tooltip where aic_tabellenname="+iTabStatus+" and aic_fremd="+iStatus+" and aic_sprache="+g.getSprache()));
			  CboAbfrage.setSelectedAIC(SQL.getInteger(g, "select abfrage from status where aic_status=?",0,""+iStatus));
			  CboHS.setSelectedAIC(SQL.getInteger(g, "select selbst from status join hauptschirm on status.hauptschirm=hauptschirm.aic_hauptschirm where aic_status=?",0,""+iStatus));
			  CboStamm.setStamm(TabS.getI("aic_stamm"));
			  CboSProg.setSelectedAIC(TabS.getI("sProg"));//SQL.getInteger(g, "select AIC_STAMM from status where aic_status=?",0,""+iStatus));
			  CboDar.setSelectedAIC(TabS.getI("aic_code"));//SQL.getInteger(g, "select aic_code from status where aic_status=?",0,""+iStatus));
			  TxtBildS.setText(Sort.gets(Vec, 5));
			  int iBits=TabS.getI("bits");//SQL.getInteger(g, "select bits from status where aic_status=?",0,""+iStatus);
			  CbxPeriode.setSelected((iBits&PER)>0);
			  CbxZusatzinfo.setSelected((iBits&ZI)>0);
			  CbxInaktiv.setSelected((iBits&TOD)>0);
			  CbxTest2.setSelected((iBits&TEST)>0);
			  int iZ=iBits&ZA;
			  if (iZ==Z_O)
				  RadOffen.setSelected(true); 
			  else if (iZ==Z_T)
				  RadTag.setSelected(true);
			  else if (iZ==Z_W)
				  RadWoche.setSelected(true);
			  else if (iZ==Z_M)
				  RadMonat.setSelected(true);
			  else if (iZ==Z_Q)
				  RadQuartal.setSelected(true);
			  else if (iZ==Z_J)
				  RadJahr.setSelected(true);
			  if (bCopy)
				  iStatus=-iStatus;
		  }
          thisJFrame().setEnabled(false);
		  DlgStatus.setVisible(true);
	  }
	  
	  private void setAufgabe()
	  {
		  boolean bNeu=iAufgabe<=0;
		  int iCopy=bNeu ? -iAufgabe:0;
		  boolean bKC=bNeu || TxtKenA.Modified();
		  String sKen=TxtKenA.getText();
		  int iMandant=g.getMandant();
          if(bKC && (SQL.exists(g,"select aic_Begriff from  Begriff WHERE aic_begriffgruppe="+iBG_Aufgabe+" and Kennung="+g.S(sKen,98)) || SQL.exists(g, "select kennung from status where kennung like "+g.S(sKen,40))))
              new Message(Message.WARNING_MESSAGE,DlgAufgabe,g,10).showDialog("KennungVorhanden");
          else if (bKC && sKen.equals("") && g.Def() && iMandant==1)
        	  new Message(Message.WARNING_MESSAGE,DlgAufgabe,g,10).showDialog("KennungLeer");
          else
          {
			  SQL Qry=new SQL(g);
			  Qry.add("Kennung", sKen);
			  Qry.add("DefBezeichnung",TxtDefBezA.getText());
			  Qry.add("Bildname",TxtBildA.getText());
			  Qry.add("Kennzeichen",TxtDarA.getText());
			  Qry.add0("Prog",CboProg.getSelectedAIC());
			  Qry.add0("aic_abschlusstyp",CboAT.getSelectedAIC());
			  Qry.add("aic_logging",g.getLog());
			  Qry.add("web",1);
			  Qry.add0("Tod",CbxTod.isSelected());
			  Qry.add0("Jeder",CbxJeder.isSelected());
			  int iBits=(CbxTab.isSelected() ? TAB:0)+(CbxStepper.isSelected() ? STEP:0)+(CbxPers.isSelected() ? PS:0)+(CbxTest.isSelected() ? TEST:0)+(CbxDetailaufgabe.isSelected() ? DA:0)+(CbxHR.isSelected() ? HR:0);
			  Qry.add("bits",iBits);
//			  Qry.add("jeder",1);
			  if (bNeu)
	          {
				  Qry.add("pos", SQL.getInteger(g, "select max(pos) from begriff where aic_begriffgruppe="+iBG_Aufgabe)+1);
	        	  Qry.add("AIC_Begriffgruppe",iBG_Aufgabe);    
	        	  Qry.add("AIC_Mandant",iMandant);
	        	  iAufgabe=Qry.insert("Begriff",true);
	          }
	          else
	        	  Qry.update("Begriff", iAufgabe);
			  if (TxtBezA.Modified())
				  g.setBezeichnung("", TxtBezA.getText(), g.iTabBegriff, iAufgabe, 0);
			  if (TxtMemoA.Modified())
				  g.setMemo(TxtMemoA.getValue(), "", g.iTabBegriff, iAufgabe, 0);
			  if (TxtTTA.Modified())
				  g.setTooltip(TxtTTA.getValue(), g.iTabBegriff, iAufgabe, 0);
			  g.putTabBegriffe(iBG_Aufgabe, iAufgabe, sKen, TxtBezA.getText(), TxtDefBezA.getText(), null, 0, null, -1, CboProg.getSelectedAIC(), -1, -1, -1, iBits, false, true, 0, null, CbxTod.isSelected(), TxtTTA.getValue(), null, null, null, null, bNeu);
			  if (iCopy==0)
				  Static.makeVisible(Out,addAufgabe(TxtDefBezA.getText(),sKen,CboProg.getSelectedBezeichnung(),new Date(),TxtMemoA.getValue(),iAufgabe,TxtBildA.getText(),CboAT.getSelectedBezeichnung(),
						  TxtDarA.getText(),bNeu ? null:(JCOutlinerFolderNode)Out.getSelectedNode(),bNeu ? 0:-1,CbxTod.isSelected(),CbxTest.isSelected(),g.getMandant(),CbxPers.isSelected(),CbxDetailaufgabe.isSelected(),TxtBezA.getText()));
			  else
				  copyAufgabe(iCopy,iAufgabe,sKen);
	          DlgAufgabe.setVisible(false);
          }
	  }
	  
//	  private String checkKennung(String s)
//	  {
//		  if (s.length()>39)
//			  s=s.substring(0, 39);
//		  if (SQL.exists(g, "select kennung from status where kennung like '"+Static.forSQL(s)+"'"))
//		  {
//			  for(int i=1;i<9;i++)
//				  if (!SQL.exists(g, "select kennung from status where kennung like '"+Static.forSQL(s+i)+"'"))
//					  return s+i;
//			  return "???";
//		  }		
//		  else
//			  return s;
//	  }
	  
	  private void copyAufgabe(int iCopy,int iAufgabe,String sKen)
	  {
		  g.fixtestError("kopiere Aufgabe von "+iCopy+" nach "+iAufgabe+"/"+sKen);
//		  int iTabStatus=g.TabTabellenname.getAic("STATUS");
//		  Tabellenspeicher TabS=new Tabellenspeicher(g,"select aic_status,kennung"+g.AU_Bezeichnung("Status")+g.AU_Memo("Status")+",bildname,Abfrage,aic_stamm,aic_code,bits,Reihenfolge from status where aufgabe="+iCopy,true);
//		  for (TabS.moveFirst();!TabS.out();TabS.moveNext())
//		  {
//			  SQL Qry=new SQL(g);
//			  Qry.add("Kennung", checkKennung(TabS.getS("kennung")+"_"+sKen));
//			  Qry.add("Bildname", TabS.getS("Bildname"));
//			  Qry.add0("Abfrage", TabS.getI("Abfrage"));
//			  Qry.add0("Aic_Stamm", TabS.getI("aic_stamm"));
//			  Qry.add0("aic_code", TabS.getI("aic_code"));
//			  Qry.add("bits", TabS.getI("bits"));
//			  Qry.add0("Reihenfolge", TabS.getI("Reihenfolge"));
//			  Qry.add("Aufgabe",iAufgabe);
//			  iStatus=Qry.insert("Status",true);
//			  g.setBezeichnung("",TabS.getS("bezeichnung"),iTabStatus,iStatus,1);
//			  if (!Static.Leer(TabS.getS("Memo")))
//				  g.setMemo(TabS.getS("Memo"), "", iTabStatus, iStatus, 0);
//			  CopyZuordnung(TabS.getI("aic_status"),iStatus);
//		  }
		  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_status,pos from status_zuordnung where aic_begriff="+iCopy+" and pos>10000",true);
		  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
			  g.exec("insert into STATUS_ZUORDNUNG (aic_status,aic_begriff,pos) values ("+Tab.getI("Aic_Status")+","+iAufgabe+","+Tab.getI("pos")+")");
		  DlgAufgabe.setVisible(false);
		  RefreshAll(iAufgabe);
	  }
	  
	  private void setStatus()
	  {
		  boolean bNeu=iStatus<=0;
		  int iCopy=bNeu ? -iStatus:0;
		  boolean bKC=bNeu || TxtKenS.Modified();
		  String sKen=TxtKenS.getText();
          if(bKC && (SQL.exists(g,"select aic_Status from  Status WHERE Kennung="+g.S(sKen,40)) || SQL.exists(g,"select aic_Begriff from  Begriff WHERE aic_begriffgruppe="+iBG_Aufgabe+" and Kennung="+g.S(sKen,98))))
              new Message(Message.WARNING_MESSAGE,DlgStatus,g,10).showDialog("KennungVorhanden");
          else if (bKC && sKen.equals("") && g.Def())
        	  new Message(Message.WARNING_MESSAGE,DlgStatus,g,10).showDialog("KennungLeer");
          else if (CboAbfrage.getSelectedAIC()==0 && CboHS.getSelectedAIC()==0)
        	  new Message(Message.WARNING_MESSAGE,DlgStatus,g,10).showDialog("FilterFehlt");
          else
          {
			  SQL Qry=new SQL(g);
			  Qry.add("Kennung", sKen);
			  Qry.add("Bildname",TxtBildS.getText());
			  Qry.add0("Abfrage",CboAbfrage.getSelectedAIC());
			  Qry.add0("Hauptschirm",CboHS.isNull() ? 0:SQL.getInteger(g, "select aic_hauptschirm from hauptschirm where selbst="+CboHS.getSelectedAIC()));
			  Qry.add0("Aic_Stamm",CboStamm.getStamm());
			  Qry.add0("sProg",CboSProg.getSelectedAIC());
			  Qry.add0("Aic_code",CboDar.getSelectedAIC());
			  int iBits=(CbxPeriode.isSelected() ? PER:0)+(RadTag.isSelected() ? Z_T:RadWoche.isSelected() ? Z_W:RadMonat.isSelected() ? Z_M:RadQuartal.isSelected() ? Z_Q:RadJahr.isSelected() ? Z_J:Z_O)+
					  (CbxZusatzinfo.isSelected() ? ZI:0)+(CbxInaktiv.isSelected() ? TOD:0)+(CbxTest2.isSelected() ? TEST:0);
			  Qry.add("bits",iBits);
//			  if (bNeu || CboAufgabe.Modified())
//				  Qry.add("Aufgabe",CboAufgabe.getSelectedAIC()); 
//			  if (iCopy>0)
//				  Qry.add("Reihenfolge",SQL.getInteger(g, "select max(Reihenfolge) from status where aufgabe="+CboAufgabe.getSelectedAIC())+1);
		        	  
	          if (bNeu)   
	        	  iStatus=Qry.insert("Status",true);
	          else
	        	  Qry.update("Status", iStatus);
	          if (TxtBezS.Modified() || bNeu)
	        	  g.setBezeichnung("",TxtBezS.getText(),iTabStatus,iStatus,1);
	          if (TxtMemoS.Modified())
				  g.setMemo(TxtMemoS.getValue(), "", iTabStatus, iStatus, 0);
			  if (TxtTTS.Modified())
				  g.setTooltip(TxtTTS.getValue(), iTabStatus, iStatus, 0);
			  if (iCopy>0)
				  CopyZuordnung(iCopy,iStatus);
//			  if (/*CboAufgabe.Modified() ||*/ iCopy>0)
				  RefreshAll(-iStatus);
//			  else  // darf nicht verwendet werden, da dann Sync nicht mehr klappt, da Node von anderen nicht gefüllt
//			  {
////				  TabS.refresh(g);
//				  iSync=-iStatus;
//				  TabS.posInhalt("aic_status", iStatus);
//				  TabS.setInhalt("Node",addStatus1(iStatus,CboAbfrage.Cbo.getSelectedBezeichnung(),bNeu));
//						  //addStatus(TxtBezS.getText(),sKen,CboAbfrage.Cbo.getSelectedBezeichnung(),TxtMemoS.getValue(),iStatus,CboDar.getSelectedAIC(),TxtBildS.getText(),CboStamm.getStamm(),(JCOutlinerFolderNode)Out.getSelectedNode(),bNeu,0,CbxInaktiv.isSelected(),CbxTest2.isSelected()));
//			  }
			  hideDlg(DlgStatus);
          }
	  }
	  
	  private void CopyZuordnung(int iVon,int iNach)
	  {
		  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,pos from status_zuordnung where aic_status="+iVon,true);
		  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
			  g.exec("insert into STATUS_ZUORDNUNG (aic_status,aic_begriff,pos) values ("+iNach+","+Tab.getI("Aic_begriff")+","+Tab.getI("pos")+")");
	  }
	  
	  private JCOutlinerFolderNode addAufgabe(String sDefBez,String sKennung,String sProg,Date dt,String sMemo,int iAic,String sBild,String sATyp,String sDar,JCOutlinerFolderNode Node,int iPos,boolean bTod,boolean bTest,int iMandant,boolean bPers,boolean bDetail,String sBez)
	  {
		  Vector<Comparable> VecVisible=new Vector<Comparable>();
          VecVisible.addElement(sDefBez);
          VecVisible.addElement(sKennung);
          VecVisible.addElement(iAic);
          VecVisible.addElement(sProg);
          if (iPos==0)
    	  {
    		  iPos=Out.getRootNode().getChildren()==null ? 1:Out.getRootNode().getChildren().size()+1;
    		  g.exec("update begriff set Pos="+iPos+" where aic_Begriff="+iAic);
    	  }
          else if (iPos<0 && Node != null)
        	  iPos=Sort.geti(Node.getLabel(),4);
          VecVisible.addElement(iPos);
          int iPosA=g.TabBegriffe.getPos("Aic", iAic);
          VecVisible.addElement(iPosA<0 ? Static.sLeer:""+g.getSttBez(iPosA));
          VecVisible.addElement(0); // Anzahl
          VecVisible.addElement(sDar);
          VecVisible.addElement(iMandant>0 ? g.TabMandanten.getBezeichnungS(iMandant):Static.sLeer);
          VecVisible.addElement(sBild);
          VecVisible.addElement(sATyp);
          VecVisible.addElement(dt==null ? null : new Zeit(dt, "dd.MM.yyyy"));
          VecVisible.addElement(sMemo);
          VecVisible.addElement(Static.JaNein2(bPers));
          VecVisible.addElement(Static.JaNein2(bDetail));
          VecVisible.addElement(sBez);
          if (Node==null)
          {
	          Node = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)Out.getRootNode());
	          Node.setUserData(iAic);
	          Out.folderChanged(Out.getRootNode());
          }
          else
        	  Node.setLabel(VecVisible);
          Node.setStyle(g.setColor(g.getBGStype("Aufgabe"),bTod ? g.ColTod:bTest ? g.ColBreakpoint:g.ColStandard));
          if (iSync>0 && iSync==iAic)
        	  Static.makeVisible(Out, Node);
          return Node;
	  }
	  
	  private JCOutlinerFolderNode addStatus1(int iAic,String sAbf,boolean bNeu)
	  {
		  if (!TabS.posInhalt("aic_status", iAic))
			  return null;
		  Vector<Comparable> VecVisS=new Vector<Comparable>();
		  VecVisS.addElement(TabS.getS("Bezeichnung"));
		  VecVisS.addElement(TabS.getS("Kennung"));
		  VecVisS.addElement(iAic);
		  VecVisS.addElement(sAbf);
		  int iDar=TabS.getI("aic_code");
		  VecVisS.addElement(iDar==0 ? "":g.TabCodes.getBezeichnungS(iDar));
		  VecVisS.addElement(TabS.getM("Bildname"));
		  int iStamm=TabS.getI("aic_stamm");
		  VecVisS.addElement(iStamm>0 ? g.getStamm(iStamm):"");
		  VecVisS.addElement(TabS.getM("Memo"));
//		  VecVisS.addElement(null); // persönlich
//		  VecVisS.addElement(null); // Detail
//		  VecVisS.addElement(TabS.getS("Bezeichnung"));
		  JCOutlinerFolderNode NodeS=null;
		  if (bNeu)
		  {		  
			  NodeS = new JCOutlinerFolderNode((Object)VecVisS,(JCOutlinerFolderNode)OutS.getRootNode());		  
        	  NodeS.setUserData(iAic);      	  
		  }
		  else
		  {
			  NodeS=(JCOutlinerFolderNode)OutS.getSelectedNode();
        	  if ((int)NodeS.getUserData()==iAic)
        		  NodeS.setLabel(VecVisS);
		  }
		  NodeS.setStyle(g.setColor(g.getTabStype("STATUS"),(TabS.getI("Bits")&TOD)>0 ? g.ColTod:(TabS.getI("Bits")&TEST)>0 ? g.ColBreakpoint:g.ColStandard));
		  if (iSync<0 && iSync==-iAic)
        	  Static.makeVisible(OutS, NodeS);
		  else
			  OutS.folderChanged(OutS.getRootNode());
          return NodeS;
	  }
	  
	  private JCOutlinerFolderNode addStatus2(int iAic,int iNr,JCOutlinerFolderNode NodeA)
	  {
		  if (NodeA==null || !TabS.posInhalt("aic_status", iAic))
			  return null;
		  Vector<Comparable> VecVisible=new Vector<Comparable>();
		  VecVisible.addElement(TabS.getS("Bezeichnung"));
		  VecVisible.addElement(TabS.getS("Kennung"));
		  VecVisible.addElement(iAic);
		  VecVisible.addElement(g.TabBegriffe.getBezeichnungS(TabS.getI("filter")));
//		  if (iNr==0)
//    	  {
//    		  iNr=NodeA==null || NodeA.getChildren()==null ? 1:NodeA.getChildren().size()+1;
////    		  g.exec("update status set Reihenfolge="+iNr+" where aic_status="+iAic);
//    	  }
		  VecVisible.addElement(iNr);
		  int iPosF=g.TabBegriffe.getPos("Aic", TabS.getI("filter"));
          VecVisible.addElement(iPosF<0 ? Static.sLeer:""+g.getSttBez(iPosF));
		  VecVisible.addElement(TabS.getS("Anzahl"));
		  int iDar=TabS.getI("aic_code");
    	  VecVisible.addElement(iDar==0 ? "":g.TabCodes.getBezeichnungS(iDar));
    	  VecVisible.addElement(Static.sLeer);
    	  VecVisible.addElement(TabS.getM("Bildname"));
    	  int iStamm=TabS.getI("aic_stamm");
    	  VecVisible.addElement(iStamm>0 ? g.getStamm(iStamm):"");
    	  VecVisible.addElement(Static.sLeer);
    	  VecVisible.addElement(TabS.getM("Memo"));
    	  VecVisible.addElement(null); // persönlich
    	  VecVisible.addElement(null); // Detail
    	  VecVisible.addElement(TabS.getS("Bezeichnung"));
    	  JCOutlinerFolderNode Node=new JCOutlinerFolderNode((Object)VecVisible,NodeA);
    	  Vector VecA=(Vector)NodeA.getLabel();
    	  VecA.setElementAt(Sort.geti(VecA,6)+1,6);
    	  Node.setUserData(iAic);    	  
    	  Node.setState(BWTEnum.FOLDER_CLOSED);
    	  Out.folderChanged(Out.getRootNode());
    	  Node.setStyle(g.setColor(g.getTabStype("STATUS"),(TabS.getI("Bits")&TOD)>0 ? g.ColTod:(TabS.getI("Bits")&TEST)>0 ? g.ColBreakpoint:g.ColStandard));
        	  
        return Node;
	  }
	  	  
	  private void addZuordnung(int iAic,JCOutlinerFolderNode NodeP)
	  {
		  NodeP.removeChildren();
		  Tabellenspeicher TabZ=new Tabellenspeicher(g,"select * from status_zuordnung where aic_status="+iAic+" and Pos<10000 order by pos",true);
		  for (TabZ.moveFirst();!TabZ.out();TabZ.moveNext())
		  {
			  int iBeg=TabZ.getI("aic_begriff");
			  int iPos=g.TabBegriffe.getPos("Aic", iBeg);
			  String sBez=iPos<0 ? "Aic="+iBeg:g.TabBegriffe.getS(iPos,"DefBezeichnung");//g.getBegBez2(iPos);
			  String sKen=iPos<0 ? Static.sLeer:g.TabBegriffe.getS(iPos,"Kennung");
//			  TabS.posInhalt("aic_status", TabZ.getI("aic_Status"));
//			  JCOutlinerFolderNode Node=(JCOutlinerFolderNode)TabS.getInhalt("Node");
			  addBegriff(sBez,sKen,iBeg,TabZ.getI("Pos"),NodeP,iAic,true);
		  }
		  Out.folderChanged(NodeP);
	  }
	  
	  private JCOutlinerFolderNode addBegriff(String sBez,String sKennung,int iAic,int iP,JCOutlinerFolderNode NodeP,int iStatus,boolean bClose)
	  {
		  if (NodeP==null)
			  return null;
		  
		  int iPos=g.TabBegriffe.getPos("Aic", iAic);
          Vector<Object> VecVisible=new Vector<Object>();
          VecVisible.addElement(sBez);
          VecVisible.addElement(sKennung);
          VecVisible.addElement(iAic);
          VecVisible.addElement(iPos<0 ? Static.sLeer:""+g.TabCodes.getBezeichnungS(g.TabBegriffe.getI(iPos,"Prog")));
          VecVisible.addElement(iP);
          VecVisible.addElement(iPos<0 ? Static.sLeer:""+g.getSttBez(iPos));
          VecVisible.addElement(null);
          VecVisible.addElement(Static.sLeer);
          VecVisible.addElement(Static.sLeer); // Mandant
          VecVisible.addElement(iPos<0 ? Static.sLeer:""+g.TabBegriffe.getS(iPos,"BildFile"));
          VecVisible.addElement(Static.sLeer); // Stamm
          VecVisible.addElement(Static.sLeer); // Change
          VecVisible.addElement(Static.sLeer); // Memo
          VecVisible.addElement(Static.sLeer); // pers
          VecVisible.addElement(Static.sLeer); // Detail
          VecVisible.addElement(g.TabBegriffe.getS(iPos,"Bezeichnung"));
//          VecVisible.addElement(null);
          JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,NodeP);
          Node.setUserData(iAic);
          int iPosBG=iPos<0 ? -1:g.TabBegriffgruppen.getPos("Aic", g.TabBegriffe.getI(iPos,"Gruppe"));
          ImageIcon Gif = iPosBG>=0 ? g.LoadImageIcon(g.TabBegriffgruppen.getS(iPosBG,"FILENAME")):null;
		  Node.setStyle(g.getStyle(Gif!=null?Gif.getImage():null));
		  if (!bClose)
			  Out.folderChanged(NodeP);
//          NodeP.setState(bClose ? BWTEnum.FOLDER_CLOSED:BWTEnum.FOLDER_OPEN_FOLDERS);
          return Node;
	  }
	  
	  private void Sync()
	  {
		  JCOutlinerNode Nod=Out.getSelectedNode();
		  int iLevel=Nod==null ? 0:Nod.getLevel();
		  if (iLevel==2)
		  {
			  int iS=(int)Nod.getUserData();
			  if (TabS.posInhalt("Aic_Status", iS))
			  {
//				  TabS.showGrid("TabS");
				  Static.makeVisible(null, ((JCOutlinerNode)TabS.getInhalt("Node")));
			  }
		  }
		  if (iLevel==3)
		  {
			  int iB=(int)Nod.getUserData();
//			  g.fixInfo("Sync "+iB);
			  if (TabB.posInhalt("Aic", iB))
				  Static.makeVisible(null, ((JCOutlinerNode)TabB.getInhalt("Node")));
		  }
	  }
	  
//	  private void EditStatus()
//	  {
//		  g.fixtestError("EditStatus noch nicht verfügbar");
//	  }
	  
	  private void Edit()
	  {
		  JCOutlinerNode Nod=Out.getSelectedNode();
		  int iLevel=Nod==null ? 0:Nod.getLevel();
		  if (iLevel==1)
			  EditAufgabe((int)Nod.getUserData());
		  else if (iLevel==2)
		  {
			  Sync();
			  EditStatus((int)Nod.getUserData());
		  }
		  else if (iLevel==3)
		  {
			  int iB=(int)Nod.getUserData();
			  int iPos=g.TabBegriffe.getPos("Aic", iB);
			  if (iPos>=0)
			  {
				  String sGruppe=g.TabBegriffgruppen.getKennung(g.TabBegriffe.getI(iPos,"Gruppe"));
				  if (sGruppe.equals("Aufgabe"))
					  RefreshAll(iB);
				  else if (sGruppe.equals("Druck"))
			      {
			        int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iB);
			        Druckdefinition.get(g, true, iB, iStt);
			      }
				  if (sGruppe.equals("Frame"))
					  DefFormular.get(g,iB);
				  else if (sGruppe.equals("Modell"))
				    	DefModell.get(g, iB).show();
				  else
					  Static.printError("Edit "+sGruppe+" wird noch nicht unterstützt");
			  }
		  }
	  }
	  
	  private void Copy()
	  {
		  JCOutlinerNode Nod=Out.getSelectedNode();
		  int iLevel=Nod==null ? 0:Nod.getLevel();
		  if (iLevel==1)
			  /*Static.printError("Aufgabe noch nicht kopierbar");*/EditAufgabe(-(int)Nod.getUserData());
		  else if (iLevel==2)
			  EditStatus(-(int)Nod.getUserData());
	  }
	  
	  private void DelStatus()
	  {
		  JCOutlinerNode Nod=OutS.getSelectedNode();
		  int iS=(int)Nod.getUserData();
		  String sBez=Sort.gets(Nod.getLabel(),0);
		  Tabellenspeicher Tab=new Tabellenspeicher(g,"select b.defbezeichnung,b.kennung,b.aic_begriff from status_zuordnung z join begriff b on b.aic_begriff=z.aic_begriff where z.aic_status="+iS+" and"+g.bit("z.pos",AUFG),true);
		  if (!Tab.isEmpty())
			  new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB,thisJFrame(),g).showDialog("BereitsVerwendet",new String[] {sBez},Tab);
		  else if (new Message(Message.YES_NO_OPTION,thisJFrame(),g).showDialog("Loeschen",new String[] {sBez})==Message.YES_OPTION)
			  DelStatus(iS);
	  }
	  
	  private Tabellenspeicher getTabSZ(int iS)
	  {
		  return new Tabellenspeicher(g,"select g.kennung Gruppe,b.defbezeichnung,b.kennung,b.aic_begriff,z.pos&0xFFFF pos"+g.bei("z.pos", AUFG, "Aufgabe")+" from status_zuordnung z join begriff b on b.aic_begriff=z.aic_begriff"+
				  " join begriffgruppe g on b.aic_begriffgruppe=g.aic_begriffgruppe where z.aic_status="+iS,true);
	  }
	  
	  private void DelStatus2()
	  {
//		  g.fixtestError("DelStatus noch nicht programmiert");
		  JCOutlinerNode Nod=OutS.getSelectedNode();
		  int iS=(int)Nod.getUserData();
		  String sBez=Sort.gets(Nod.getLabel(),0);
		  Tabellenspeicher Tab=getTabSZ(iS);
		  if (!Tab.isEmpty() && new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,thisJFrame(),g).showDialog("Loeschen",new String[] {sBez},Tab)==Message.YES_OPTION)
		  {
			  g.exec("delete from status_zuordnung where aic_status="+iS);
			  RefreshAll(0);
		  }
	  }
	  
	  private void Del()
	  {
		  JCOutlinerNode Nod=Out.getSelectedNode();
		  int iLevel=Nod==null ? 0:Nod.getLevel();
		  if (iLevel==1)
		  {
			  int iAufg=(int)Nod.getUserData();
			  Tabellenspeicher Tab=new Tabellenspeicher(g,"select g.kennung Gruppe,b.defbezeichnung,b.kennung,b.aic_begriff from begriffgruppe g join begriff b on g.aic_begriffgruppe=b.aic_begriffgruppe join begriff_zuordnung z on b.aic_begriff=z.aic_begriff where beg_aic_begriff="+iAufg,true);
			  if (Tab.size()>0)
			  {
				  new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,thisJFrame(),g).showDialog("BereitsVerwendet",new String[] {g.getBegBez(iAufg)},Tab);
				  iLevel=0;
			  }
		  }
		  if (iLevel>0 && new Message(Message.YES_NO_OPTION,thisJFrame(),g).showDialog("Loeschen",new String[] {Sort.gets(Nod.getLabel(),0)})==Message.YES_OPTION)
			  if (iLevel==1)
				  DelAufgabe((int)Nod.getUserData());
			  else if (iLevel==2)
				  DelStatus((int)Nod.getUserData());
			  else
				  g.fixtestError("kann nicht löschen: "+Sort.gets(Nod.getLabel(),0));
	  }
	  
	  private void DelAufgabe(int riAufgabe)
	  {
		  g.fixtestError("DelAufgabe "+riAufgabe);
//		  Tabellenspeicher Tab=new Tabellenspeicher(g,"select g.kennung Gruppe,b.defbezeichnung,b.kennung,b.aic_begriff from begriffgruppe g join begriff b on g.aic_begriffgruppe=b.aic_begriffgruppe join begriff_zuordnung z on b.aic_begriff=z.aic_begriff where beg_aic_begriff="+riAufgabe,true);
//		  if (Tab.size()>0)
//			  new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,null,g).showDialog("BereitsVerwendet",new String[] {g.getBegBez(riAufgabe)},Tab);
//		  else
//		  {
			  g.exec("delete from begriff_zuordnung where beg_aic_begriff="+riAufgabe);
	//		  Vector<Integer> Vec=SQL.getVector("select aic_status from status where aufgabe="+riAufgabe, g);
	//		  if (Vec.size()>0)
	//			  g.exec("delete from STATUS_ZUORDNUNG where aic_status"+Static.SQL_in(Vec));
			  g.exec("delete from STATUS_ZUORDNUNG where aic_begriff="+riAufgabe);
			  g.exec("delete from status where aufgabe="+riAufgabe);
			  g.exec("delete from defverlauf where aic_begriff="+riAufgabe);
			  g.exec("delete from begriff where aic_begriff="+riAufgabe);
			  RefreshAll(0);
//		  }
		  //((JCOutlinerFolderNode)Out.getRootNode()).removeChild(Nod);
	  }
	  
	  private void DelStatus(int riStatus)
	  {
		  g.fixtestError("DelStatus "+riStatus);
		  int iAufgabe=SQL.getInteger(g, "select aufgabe from status where aic_status="+riStatus);
		  g.exec("delete from STATUS_ZUORDNUNG where aic_status="+riStatus);
		  g.exec("delete from status where aic_status="+riStatus);
		  RefreshAll(iAufgabe);
		  //((JCOutlinerFolderNode)Out.getRootNode()).removeChild(Nod);
	  }
	  
//	  private void Save()
//	  {
//		  
////		  g.fixtestError("Save");
//		  for (TabZ.moveFirst();!TabZ.out();TabZ.moveNext())
//		  {
//			  int iS=TabZ.getI("Status");
//			  int iB=TabZ.getI("Begriff");
//			  JCOutlinerNode Nod=(JCOutlinerNode)TabZ.getInhalt("Node");
//			  int iPos=Nod==null ? 0:Sort.geti(Nod.getLabel(),4);
//			  String sArt=TabZ.getS("Art");
//			  if (sArt.equals("new"))
//				  g.exec("insert into STATUS_ZUORDNUNG (aic_status,aic_begriff,pos) values ("+iS+","+iB+","+iPos+")");
//			  else if (sArt.equals("del"))
//				  g.exec("delete from STATUS_ZUORDNUNG where aic_status="+iS+" and aic_begriff="+iB);
//			  else
//				  Static.printError("Art "+sArt+" wird beim Speichern nicht unterstützt");
//		  }
//		  TabZ.clearAll();
//		  RefreshAll();
//	  }
	  
	  // neue Darstellung
	  private void DarNeu()
	    {
	      if (DlgDar==null)
	      {
	    	  DlgDar = new JDialog(thisJFrame(), "neue Darstellung", true);
	    	  DlgDar.getContentPane().setLayout(new BorderLayout(2, 2));
	        JPanel Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
	        g.addLabel2(Pnl1, "Bezeichnung");
	        g.addLabel2(Pnl1, "Kennung");
	        DlgDar.getContentPane().add("West", Pnl1);
	        Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
	        Pnl1.add(TxtBezD);
	        Pnl1.add(TxtKenD);
	        DlgDar.getContentPane().add("Center", Pnl1);
	        JPanel Pnl = new JPanel(new GridLayout(1, 0, 2, 2));
	        JButton BtnOk = g.getButton("Ok");
	        JButton BtnAbbruch = g.getButton("Abbruch");
	        g.BtnAdd(BtnOk, "OkD", AL);
	        g.BtnAdd(BtnAbbruch, "AbbruchD", AL);
	        Pnl.add(BtnOk);
	        Pnl.add(BtnAbbruch);
	        DlgDar.getContentPane().add("South", Pnl);
	        DlgDar.pack();
	      }
	      Static.centerComponent(DlgDar,thisFrame);
	      TxtBezD.setText("");
	      TxtKenD.setText("");
	      DlgDar.setVisible(true);
	    }

	private void DarDel()
	    {
	      int iAic=CboDar.getSelectedAIC();
	      String sBez=CboDar.getSelectedBezeichnung();
	      //g.fixInfo("Programm="+iAic+"/"+CboProgramm.getSelectedBezeichnung());
	      Tabellenspeicher Tab=new Tabellenspeicher(g,"select kennung,aic_status from status where aic_code=?",""+iAic,true);
	      if (Tab.isEmpty())
	      {
	        if (new Message(Message.YES_NO_OPTION,thisJFrame(),g,10).showDialog("Loeschen", new String[] {"(" + sBez + ")"}) == Message.YES_OPTION)
	        {
	          g.exec("delete from code where aic_code="+iAic);
	          CboDar.fillBegriffTable(g.TabBegriffgruppen.getAic("Darstellung"),true,true);
	        }
	      }
	      else
	        Tab.showGrid(sBez+" schon verwendet",thisFrame);
	    }

	private void DarSave()
	    {
		  String sKen=TxtKenD.getText();
		  int iBG=g.TabBegriffgruppen.getAic("Darstellung");
		  if (sKen.equals(""))
	      	  new Message(Message.WARNING_MESSAGE,DlgDar,g,10).showDialog("KennungLeer");
		  else if (sKen.length()>20)
			  new Message(Message.WARNING_MESSAGE,DlgDar, g, 10).showDialog("KennungZuLang");
		  else if(SQL.exists(g,"select aic_Code from Code WHERE aic_begriffgruppe="+iBG+" and Kennung='"+sKen+"'"))
              new Message(Message.WARNING_MESSAGE,DlgDar,g,10).showDialog("KennungVorhanden");
		  else
		  {
		      SQL Qry=new SQL(g);
		      Qry.add("Kennung",sKen);
		      Qry.add("AIC_Begriffgruppe",iBG);
		      int iAic=Qry.insert("Code",true);
		      g.setBezeichnung("",TxtBezD.getText(),g.TabTabellenname.getAic("CODE"),iAic,1);
		      Qry.free();
		      CboDar.addItem(TxtBezD.getText(),iAic,TxtKenD.getText());
		      DlgDar.setVisible(false);
		  }
	    }
	
	private void EnableButtons()
	{
          //g.progInfo("EnableButtons");
		JCOutlinerNode Nod=Out.getSelectedNode();
		int iLevel= (Nod==null?-1:Nod.getLevel());
		if (thisFrame.isVisible())
		{
			if (iLevel==1)
				iSync=(int)Nod.getUserData();
			else if (iLevel==2)
				iSync=-(int)Nod.getUserData();
//			g.fixtestError("EnableButtons: "+iLevel+"/"+iSync);
		}
		int iLevelB= (OutB.getSelectedNode()==null?-1:OutB.getSelectedNode().getLevel());
		if (BtnNeuS != null)
			BtnNeuS.setEnabled(iLevel==1);
		if (BtnEdit != null)
			BtnEdit.setEnabled(iLevel==1);
		if (BtnCopy != null)
			BtnCopy.setEnabled(iLevel==1);
		if (BtnDel != null)
			BtnDel.setEnabled(iLevel==1);
		BtnHinzu.setEnabled(iLevelB==2 && iLevel==2 && OutB.isShowing() || iLevel==1 && OutS.isShowing());
		BtnWeg.setEnabled(iLevel==2 || iLevel==3);
	}
	
	private void DefAbfrageAufrufen(boolean b1)
	{
	  JCOutlinerNode Node = Out.getSelectedNodes()[0];
	  int iLevel= Node==null?-1:Node.getLevel();
	  if (iLevel==2)
	  {
		  int iStatus2=(int)Node.getUserData();
		  int iAbf=SQL.getInteger(g, "select abfrage from status where aic_status=?",0,""+iStatus2);
		  if (iAbf>0)
		  {
		    int iBegriff = g.AbfToBeg(iAbf);//((Integer)VecInvisible.elementAt(1)).intValue();
		    int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iBegriff);
		    All_Unlimited.Grunddefinition.DefAbfrage.get(g, new All_Unlimited.Hauptmaske.ShowAbfrage(g, iBegriff, Abfrage.cstBegriff), iStt,null,false,b1 ? 0:1).show(false);
		  }
	  }
	}
	  
	private void EditBegriff(int iBegriff)
	{
		if (iBegriff==0)
			return;
		int iPos=g.TabBegriffe.getPos("Aic", iBegriff);
		int iBG=g.TabBegriffe.getI(iPos,"Gruppe");
		if (iBG>0)
		Tsearch.Edit(g,0,g.TabBegriffgruppen.getKennung(iBG),iBegriff,true);
//		int iBG_Druck=g.TabBegriffgruppen.getAic("Druck");
////		int iBG_Abf=g.TabBegriffgruppen.getAic("Abfrage");
//		int iBG_Aufgabe=g.TabBegriffgruppen.getAic("Aufgabe");
//		int iBG_Frm=g.TabBegriffgruppen.getAic("Frame");
//		int iBG_Mod=g.TabBegriffgruppen.getAic("Modell");
//		int iPos=g.TabBegriffe.getPos("Aic", iBegriff);
//		if (iPos>=0)
//		{
//			int iBG=g.TabBegriffe.getI(iPos,"Gruppe");
//			//g.fixtestError(g.getBegBez2(iPos)+": "+iBG+"->"+(iBG==iBG_Druck ? "Druck":iBG==iBG_Abf?"Abf":"??"));
//			if (iBG==iBG_Aufgabe)
//				DefAufgabe.get(g, iBegriff);
//			else if (iBG==iBG_Druck)
//				Druckdefinition.get(g, true, iBegriff, 0);
//			else if (iBG==iBG_Frm)
//				DefFormular.get(g, iBegriff);
//			else if (iBG==iBG_Mod)
//				DefModell.get(g, iBegriff).show();
//			else
//				Static.printError("EditBegriff von "+g.TabBegriffgruppen.getKennung(iBG)+" wird noch nicht unterstützt");
//		}
	}

}
