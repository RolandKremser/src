package All_Unlimited.Grunddefinition;

import java.awt.Color;
import java.awt.Component;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.Hauptschirm;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class Berechtigung extends Formular
{
  private Global g;
  private static Berechtigung Self=null;
  private Tabellenspeicher TabBenutzergruppe;
  private Vector VecBG=null;
  //private JCheckBox CbxGruppe;
  private JCheckBox CbxSelbst;
  private JCheckBox CbxNicht;
  private JCheckBox CbxReadOnly;
  
  private JCheckBox CbxJeder;
  private JCheckBox CbxWeb;
  private JCheckBox CbxAll;
  private JCheckBox CbxAPI;
  
  //private JButton BtnShow;
  //private JButton BtnSpeichern;
  //private JButton BtnBeenden;
  private JButton BtnHinzufuegen;
  private JButton BtnEntfernen;
  private AUOutliner OutBenutzergruppe2 = new AUOutliner(new JCOutlinerFolderNode(""));
  private AUOutliner OutGruppen = new AUOutliner(new JCOutlinerFolderNode(""));
  private AUTextArea Txt=null;
  private Tabellenspeicher TabAll;
  private Tabellenspeicher TabHS=null;
  private int iTabH=0;
  private int iTabE=0;
  private int iTabS=0;
  private int iTabB=0;
  private int iTabEig=0;
  
  public static final int GRUPPE=1;
  public static final int SELBST=2;
  public static final int NICHT =4;
  public static final int RO    =8;

  public static Berechtigung get(Global rg)
  {
    return Self == null ? new Berechtigung(rg) : Self;
  }

  public static void free()
  {
    if (Self != null)
    {
      Self.g.winInfo("Berechtigung.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

  public Berechtigung(Global glob)
  {
    super("Berechtigung", null, glob);
    Self=this;
    g = glob;
    g.winInfo("Berechtigung.create");
    Build();
  }

  public void show(Vector Vec)
  {
    VecBG=Vec;
    fillBenutzergruppe2();
    thisFrame.setVisible(true);
  }

  private void Build()
  {
    TabBenutzergruppe = new Tabellenspeicher(g,new String[]{"Tab","AIC_BG","AIC_Fremd","AIC_Tabellenname","Art","bits"});
    TabBenutzergruppe.sGruppe="AIC_BG";
    TabBenutzergruppe.sAIC="AIC_Fremd";

    //CbxGruppe = getCheckbox("Gruppe");
    CbxSelbst = getCheckbox("Selbst");
    CbxNicht = getCheckbox("nicht");
    if (CbxNicht==null) CbxNicht=new JCheckBox();
    CbxReadOnly = getCheckbox("ReadOnly");
    if (CbxReadOnly==null) CbxReadOnly=new JCheckBox();
    
    CbxJeder = getCheckbox("Jeder2");
    if (CbxJeder==null) CbxJeder=new JCheckBox();
    CbxAll = getCheckbox("All");
    if (CbxAll==null) CbxAll=new JCheckBox();
    CbxAll.setSelected(true);
    CbxWeb = getCheckbox("Web");
    if (CbxWeb==null) CbxWeb=new JCheckBox();
    CbxWeb.setSelected(true);
    CbxAPI = getCheckbox("nurAPI");
    if (CbxAPI==null) CbxAPI=new JCheckBox();
    if (!g.Def())
    	CbxAPI.setVisible(false);
    if (g.SuperUser())
    {
    	ActionListener AL=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fillGruppen();		
			}
		};
    	CbxJeder.addActionListener(AL);
    	CbxAll.addActionListener(AL);
    	CbxWeb.addActionListener(AL);
    	if (g.Def())
    		CbxAPI.addActionListener(AL);
    }
    else
    	CbxJeder.setEnabled(false);

    OutBenutzergruppe2.setBackground(Color.white);
    OutGruppen.setBackground(Color.white);
    OutBenutzergruppe2.setRootVisible(false);
    OutGruppen.setRootVisible(false);
    OutBenutzergruppe2.setAllowMultipleSelections(true);
    OutGruppen.setAllowMultipleSelections(true);
    OutBenutzergruppe2.setColumnLabelSortMethod(Sort.sortMethod);
    OutGruppen.setColumnLabelSortMethod(Sort.sortMethod);
    String[] s = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung")+" / "+g.getShow("Aic"),g.TabTabellenname.getBezeichnungS("TABELLENNAME"),g.getShow("Web"),g.getBegriffS("Checkbox","Jeder"),g.getShow("Stt"),
    		g.getShow("selbst"),g.getShow("nicht"),g.getShow("ReadOnly"),g.getShow("Aic")};
    OutBenutzergruppe2.setColumnButtons(s);
    OutBenutzergruppe2.setNumColumns(s.length-(g.Def() ? 0:1));
    s = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Stt"),g.getShow("Web"),g.getBegriffS("Checkbox","Jeder"),g.getShow("Aic"),g.getShow("Pers"),g.getShow("Detail"),g.getShow("DefBezeichnung"),g.getBegriffS("Checkbox","API")};
    OutGruppen.setColumnButtons(s);
    OutGruppen.setNumColumns(s.length);

    JPanel Pnl_Outliner_Usergroup2 = getFrei("Outliner Usergroup2");
    JPanel Pnl_Outliner_Gruppen = getFrei("Outliner Gruppen");
    JPanel Pnl_Memo = getFrei("Memo");
    if (Pnl_Memo != null)
    {
      Txt=new AUTextArea();
      Txt.Edt.setEditable(false);
      Pnl_Memo.add(Txt);
    }
    Pnl_Outliner_Usergroup2.add(OutBenutzergruppe2);
    Pnl_Outliner_Gruppen.add(OutGruppen);

    ActionListener AL=new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                String s = ev.getActionCommand();
                if (s.equals("Hinzu"))
                  Hinzufuegen_Benutzergruppe2();
                else if (s.equals("Weg"))
                  Entfernen_Benutzergruppe2();
                else if (s.equals("Save"))
                  Save();
                else if (s.equals("show"))
                {
                  JCOutlinerNode[] NodeBegriff = OutGruppen.getSelectedNodes();
                  Vector<Object> Vec = new Vector<Object>();
                  for (int i = 0; i < NodeBegriff.length; i++)
                      Vec.addElement(NodeBegriff[i].getUserData());
                  s="select b.defbezeichnung Modul,bg.kennung Art,b2.defBezeichnung Element from begriffgruppe g"+g.join("begriff","b","g","begriffgruppe")+" join begriff_zuordnung z on z.aic_begriff=b.aic_begriff join begriff b2 on z.beg_aic_begriff=b2.aic_begriff and z.aic_begriff"+Static.SQL_in(Vec)+" join begriffgruppe bg on bg.aic_begriffgruppe=b2.aic_begriffgruppe";
                  new Tabellenspeicher(g,s,true).showGrid("Module");
                }
                else if (s.equals("suche"))
                	suchen(OutGruppen,((Component)ev.getSource()).getLocationOnScreen());
                else if (s.equals("show Frame"))
                    Darstellung.showUsed(g,Sort.geti(OutGruppen.getSelectedNode().getUserData()),thisJFrame());
                else if (s.equals("Beenden"))
                {
                  checkSave();
                  hide();
                }
              }
        };

    BtnHinzufuegen = g.BtnAdd(getButton(">2"),"Hinzu",AL);
    BtnEntfernen = g.BtnAdd(getButton("<2"),"Weg",AL);
    g.BtnAdd(getButton("show"),"show",AL);
    g.BtnAdd(getButton("Tb_Suche"),"suche",AL);
    JButton BtnSpeichern=g.BtnAdd(getButton("Speichern"),"Save",AL);
    JButton BtnBeenden = g.BtnAdd(getButton("Beenden"),"Beenden",AL);
    JButton BtnShow=getButton("show Frame");
    if (BtnShow != null)
    {
    	if (g.Def())
    		g.BtnAdd(BtnShow,"show Frame",AL);
    	else
    		BtnShow.setVisible(false);
    }

    /*CbxGruppe.addItemListener(new ItemListener()
    {
            public void itemStateChanged(ItemEvent e)
            {
                    Aendern_Benutzergruppe2();
            }
    });*/
    
    ItemListener IL=new ItemListener()
    {
        public void itemStateChanged(ItemEvent e)
        {
                Aendern_Benutzergruppe2();
        }
    };
    CbxSelbst.addItemListener(IL);    
    CbxNicht.addItemListener(IL);
    CbxReadOnly.addItemListener(IL);

    OutBenutzergruppe2.addItemListener(new JCOutlinerListener()
        {
                public void itemStateChanged(JCItemEvent e)
                {

                }

                public void outlinerFolderStateChangeBegin(JCOutlinerEvent e)
                {
                  if (e.getNewState() == BWTEnum.FOLDER_OPEN_ALL)
                    checkLoad(e.getNode());
                }

                public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)
                {
                }

                public void outlinerNodeSelectBegin(JCOutlinerEvent e)
                {
                }

                public void outlinerNodeSelectEnd(JCOutlinerEvent e)
                {
                        EnableButtons();
                }
        });
        OutBenutzergruppe2.getOutliner().addMouseListener(new MouseListener()
                  {
                    public void mousePressed(MouseEvent ev) {}
                    public void mouseClicked(MouseEvent ev)
                    {
                        if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                        {
                          JCOutlinerNode Nod=OutBenutzergruppe2.getSelectedNode();
                          if (Nod!=null && Nod.getLevel()==2 && OutBenutzergruppe2.getSelectedNodes().length==1)
                          {
	                          Vector Vec=(Vector)Nod.getUserData();
	                          int iTab=Sort.geti(Vec,1);
	                          g.fixtestInfo("rechte Maustaste:"+Vec);
	                          
	                          if (iTab==Global.iTabBegriff && TabAll.posInhalt("Data", Sort.geti(Vec,0)) || iTab==iTabE && TabAll.posInhalt("Data", "E"+Sort.geti(Vec,0))
	                        		  || iTab==iTabH && TabAll.posInhalt("Data", "H"+Sort.geti(Vec,0)) || iTab==iTabS && TabAll.posInhalt("Data", "S"+Sort.geti(Vec,0))
	                        		  || iTab==iTabB && TabAll.posInhalt("Data", "B"+Sort.geti(Vec,0)))
	                            Static.makeVisible(null, ((JCOutlinerNode)TabAll.getInhalt("Node")));
	                          int iBits=Sort.geti(Nod.getUserData(),2);
	                          // (CbxSelbst.isSelected()?SELBST:0)+(CbxNicht.isSelected()?NICHT:0)+(CbxReadOnly.isSelected()? RO:0)
	                          CbxSelbst.setSelected((iBits&SELBST)>0);
	                          CbxNicht.setSelected((iBits&NICHT)>0);
	                          CbxReadOnly.setSelected((iBits&RO)>0);
                          }
                        }
                    }
                    public void mouseEntered(MouseEvent ev) {}
                    public void mouseExited(MouseEvent ev)  {}
                    public void mouseReleased(MouseEvent ev){}
                  });


        OutGruppen.addItemListener(new JCOutlinerListener()
                {
                        public void itemStateChanged(JCItemEvent e)
                        {
                        }

                        public void outlinerFolderStateChangeBegin(JCOutlinerEvent e)
                        {
                        }

                        public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)
                        {
                        }

                        public void outlinerNodeSelectBegin(JCOutlinerEvent e)
                        {
                        }

                        public void outlinerNodeSelectEnd(JCOutlinerEvent e)
                        {
                          if (Txt != null)
                            Txt.setText(OutGruppen.getSelectedNode().getLevel()<2 || Sort.geti(OutGruppen.getSelectedNode().getParent().getUserData()) != Global.iTabBegriff ? "":
                                        g.getMemo1(Sort.geti(OutGruppen.getSelectedNode().getUserData())));
                          EnableButtons();
                        }
	});

    if (BtnSpeichern != null)
      ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnSpeichern);
    if (BtnBeenden != null)
    {
      Action cancelKeyAction = new AbstractAction()
      {
        /**
		 *
		 */
		private static final long serialVersionUID = 9171660191965583191L;

		public void actionPerformed(ActionEvent e) {
          checkSave();
          hide();
        }
      };
      Static.Escape(BtnBeenden,thisFrame,cancelKeyAction);
    }

    fillGruppen();

  }

  private void fillBenutzergruppe2()
  {
    TabBenutzergruppe.clearAll();
    ((JCOutlinerFolderNode)OutBenutzergruppe2.getRootNode()).removeChildren();
    //g.TabTabellenname.posInhalt("Kennung","BENUTZERGRUPPE");
    JCOutlinerNodeStyle StyleBenutzergruppe=g.getStyle(g.getGif(g.TabTabellenname, "BENUTZERGRUPPE"));//(Image)g.TabTabellenname.);
    Tabellenspeicher TabBG=new Tabellenspeicher(g,"select AIC_Benutzergruppe,kennung,"+g.AU_BezeichnungF("Benutzergruppe")+" Bezeichnung from benutzergruppe where "+(VecBG==null?"aic_mandant="+g.getMandant():"aic_benutzergruppe"+Static.SQL_in(VecBG)),true);
    for (TabBG.moveFirst();!TabBG.eof();TabBG.moveNext())
    {
      Vector<String> VecVisible = new Vector<String>();
      VecVisible.addElement(TabBG.getS("Bezeichnung"));
      VecVisible.addElement(TabBG.getS("Kennung"));
      JCOutlinerFolderNode NodeParent = new JCOutlinerFolderNode((Object)VecVisible, (JCOutlinerFolderNode)OutBenutzergruppe2.getRootNode());
      NodeParent.setUserData(TabBG.getInt("AIC_Benutzergruppe"));
      NodeParent.setStyle(StyleBenutzergruppe);
      if (VecBG==null || VecBG.size()>1)
        NodeParent.setState(BWTEnum.FOLDER_CLOSED);
      else
        checkLoad(NodeParent);
    }
    try
    {
      OutBenutzergruppe2.sortByColumn(0, Sort.sortMethod);
    }
    catch(Exception e)
    {
      Static.printError("Berechtigung.fillBenutzergruppe2: Sortieren nicht möglich");
    }
    OutBenutzergruppe2.folderChanged(OutBenutzergruppe2.getRootNode());
  }

  private void fillGruppen()
  {
    long lClock=Static.get_ms();
    boolean bAPI=CbxAPI.isSelected();
    ((JCOutlinerFolderNode)OutGruppen.getRootNode()).removeChildren();
    TabAll=new Tabellenspeicher(g,new String[]{"Data","Node"});
    Vector<JCOutlinerFolderNode> VecParent = new Vector<JCOutlinerFolderNode>();
    String [] sEBS1= new String [] {"EIGENSCHAFT","BEWEGUNGSTYP","STAMMTYP"};
    //String sEBS="EIGENSCHAFT";
    if (!bAPI)
        for(int i=0;i<sEBS1.length;i++)
        {
          String sTab=sEBS1[i];
          int iTab=g.TabTabellenname.getPos("Kennung",sTab);     	
          if (i==0)
          {
        	  iTabEig=g.TabTabellenname.getI(iTab,"AIC");
//        	  g.fixtestError("iTabEig="+iTabEig);
          }
          if(iTab>=0)
                {
                	int iAicTab=g.TabTabellenname.getI(iTab,"AIC");
                	if (i==0)
                  	  iTabE=iAicTab;
                  	else if (i==1)
                  	  iTabB=iAicTab;
                  	else if (i==2)
                  	  iTabS=iAicTab;
                        JCOutlinerNodeStyle Style = g.getStyle(g.getGif(g.TabTabellenname, sTab));//(Image)g.TabTabellenname.);
                        Vector<Comparable> VecVisible = new Vector<Comparable>();
                        VecVisible.addElement(g.TabTabellenname.getS(iTab,"Bezeichnung"));
                        VecVisible.addElement(g.TabTabellenname.getS(iTab,"Kennung"));
                        JCOutlinerFolderNode NodeParent = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutGruppen.getRootNode());
                        NodeParent.setUserData(iAicTab);
                        NodeParent.setStyle(Style);
                        VecParent.addElement(NodeParent);
                        Vector<Integer> VecModul=g.getMandant()==1 || !Static.bLizenz ? null:
                            SQL.getVector("select aic_fremd from lizenz where aic_tabellenname="+Global.iTabBegriff+" and aic_mandant="+g.getMandant(),g);
                        g.testInfo("VecModul="+VecModul);
                        Vector<Integer> VecLizEig = VecModul==null ? null:SQL.getVector("select distinct aic_fremd from modul where aic_tabellenname="+iAicTab
                            +" and aic_begriff"+Static.SQL_in(VecModul),g);
                        g.testInfo("VecLizEig="+VecLizEig);
                        Tabellenspeicher Tab=sTab.equals("EIGENSCHAFT") ? g.TabEigenschaften:sTab.equals("BEWEGUNGSTYP") ? g.TabErfassungstypen:sTab.equals("STAMMTYP") ? g.TabStammtypen:null;
                        for(int iPos=0;iPos<Tab.size();iPos++)
                        	if (!sTab.equals("EIGENSCHAFT") || g.existsEigenschaft(Tab.getInt(iPos,"AIC")) && (CbxJeder.isSelected() || !g.isJederEig(Tab.getInt(iPos,"AIC"))))
//                        			CbxJeder.isSelected() && (Tab.getI(iPos,"bits")&Global.cstJeder)==0 || (Tab.getI(iPos,"bits")&Global.cstJeder)==0)
//                                if ((VecLizEig==null || VecLizEig.contains(Tab.getInt(iPos,"AIC"))) && 
//                                		(!sTab.equals("EIGENSCHAFT") || CbxJeder.isSelected() || (Tab.getI(iPos,"bits")&Global.cstJeder)==0 && (Tab.getI(iPos,"bits")&Global.cstEigLizenz)>0))
                                {
                                        VecVisible = new Vector<Comparable>();
                                        VecVisible.addElement(Tab.getS(iPos,"Bezeichnung"));
                                        VecVisible.addElement(Tab.getS(iPos,"Kennung"));
                                        VecVisible.addElement(null); //Tab.getI("AIC_Stammtyp")>0?g.TabStammtypen.getBezeichnung(Tab.getI("AIC_Stammtyp")):"");
                                        VecVisible.addElement(null); // Web
                                        VecVisible.addElement(Static.JaNein2(sTab.equals("EIGENSCHAFT") && (Tab.getI(iPos,"bits")&Global.cstJeder)>0));
                                        VecVisible.addElement(Tab.getInt(iPos,"AIC"));
                                        JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeParent);
                                        Node.setUserData(Tab.getInt(iPos,"AIC"));
                                        Node.setStyle(Style);
                                        TabAll.addInhalt("Data",sTab.substring(0,1)+Tab.getInt(iPos,"AIC"));
                                        TabAll.addInhalt("Node",Node);
                                }
                }
        }
        g.clockInfo("OutGruppe-Eig",lClock);lClock=Static.get_ms();
        iTabH=g.TabTabellenname.getPos("Kennung","HAUPTSCHIRM");
        if(!bAPI && iTabH>=0)
        {
                JCOutlinerNodeStyle Style = g.getStyle(g.getGif(g.TabTabellenname, "HAUPTSCHIRM"));//(Image)g.TabTabellenname.);
                Vector<Comparable> VecVisible = new Vector<Comparable>();
                VecVisible.addElement(g.TabTabellenname.getS(iTabH,"Bezeichnung"));
                VecVisible.addElement(g.TabTabellenname.getS(iTabH,"Kennung"));
                JCOutlinerFolderNode NodeParent = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutGruppen.getRootNode());
                int iAicTab=g.TabTabellenname.getI(iTabH,"AIC");
                iTabH=iAicTab;
                NodeParent.setUserData(iAicTab);
                NodeParent.setStyle(Style);
                VecParent.addElement(NodeParent);
                Vector<Integer> VecModul=g.getMandant()==1 || !Static.bLizenz ? null:
                    SQL.getVector("select aic_fremd from lizenz where aic_tabellenname="+Global.iTabBegriff+" and aic_mandant="+g.getMandant(),g);
                g.testInfo("VecModul="+VecModul);
                Vector<Integer> VecLizHS = VecModul==null ? null:SQL.getVector("select distinct aic_fremd from modul where aic_tabellenname="+
                    g.TabTabellenname.getAic("HAUPTSCHIRM")+" and aic_begriff"+Static.SQL_in(VecModul),g);
                g.testInfo("VecLizHS="+VecLizHS);
                TabHS=new Tabellenspeicher(g,"select aic_hauptschirm aic,kennung,bits"+g.AU_Bezeichnung("Hauptschirm")+" from hauptschirm"+
                  " where aic_abfrage is not null and"+g.bitis("bits", 0x0200,0)+" and kennung is not null and kennung<>'*' and selbst is null",true);
                for(int iPos=0;iPos<TabHS.size();iPos++)
                {
                	boolean bWeb=(TabHS.getI(iPos,"bits") & Hauptschirm.WEB)>0;
                        if ((VecLizHS==null || VecLizHS.contains(TabHS.getInt(iPos,"AIC"))) && (bWeb && CbxWeb.isSelected() || !bWeb && CbxAll.isSelected()))// && (Tab.getI(iPos,"bits")&Global.cstJeder)==0 && (Tab.getI(iPos,"bits")&Global.cstEigLizenz)>0)
                        {
                                VecVisible = new Vector<Comparable>();
                                VecVisible.addElement(TabHS.getS(iPos,"Bezeichnung"));
                                VecVisible.addElement(TabHS.getS(iPos,"Kennung"));
                                VecVisible.addElement(null); //Tab.getI("AIC_Stammtyp")>0?g.TabStammtypen.getBezeichnung(Tab.getI("AIC_Stammtyp")):"");
                                VecVisible.addElement(Static.JaNein2(bWeb)); // Web
                                VecVisible.addElement(null); // Jeder
                                VecVisible.addElement(TabHS.getInt(iPos,"AIC"));
                                JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeParent);
                                Node.setUserData(TabHS.getInt(iPos,"AIC"));
                                Node.setStyle(Style);
                                TabAll.addInhalt("Data","H"+TabHS.getInt(iPos,"AIC"));
                                TabAll.addInhalt("Node",Node);
                        }
                }
        }
        //g.fixtestError("Tabs="+iTabH+"/"+iTabE+"/"+iTabS+"/"+iTabB);
     g.clockInfo("OutGruppe-HS",lClock);lClock=Static.get_ms();
        //sEBS = new String[]{"Button","Checkbox","Druck","Abfrage","Frame","Applikation"};
        String [] sEBS = bAPI ? new String[]{"Abfrage"}:new String[]{"Applikation","Frame","Button","Modell","Druck","Abfrage","Modul","Titel","Label","Aufgabe","Hauptschirm"};
        for(int i=0;i<sEBS.length;i++)
        {
          int iPosBG=g.TabBegriffgruppen.getPos("Kennung",sEBS[i]);
                if(iPosBG>=0)
                {
                        int iAIC_Begriffgruppe = g.TabBegriffgruppen.getI(iPosBG,"AIC");
                        JCOutlinerNodeStyle Style = g.getStyle(g.getImageBG(iAIC_Begriffgruppe));
                        JCOutlinerNodeStyle StyleP =new JCOutlinerNodeStyle(Style);
                        StyleP.setForeground(Global.ColPers);
                        JCOutlinerNodeStyle StyleT =new JCOutlinerNodeStyle(Style);
                        StyleT.setForeground(Global.ColTod);
                        Vector<Comparable> VecVisible = new Vector<Comparable>();
                        VecVisible.addElement(g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung"));
                        VecVisible.addElement(g.TabBegriffgruppen.getS(iPosBG,"Kennung"));
                        JCOutlinerFolderNode NodeParent = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutGruppen.getRootNode());
                        NodeParent.setUserData(new Integer(g.TabTabellenname.getAic("BEGRIFF")));
                        NodeParent.setStyle(Style);
                        VecParent.addElement(NodeParent);
                        Vector<Integer> VecModul=g.getMandant()==1 || !Static.bLizenz ? null:
                            SQL.getVector("select aic_fremd from lizenz where aic_tabellenname="+Global.iTabBegriff+" and aic_mandant="+g.getMandant(),g);
                        Vector<Integer> VecLizBeg = VecModul==null ? null:SQL.getVector("select distinct beg_aic_begriff from begriff_zuordnung where aic_begriff"+Static.SQL_in(VecModul),g);
                        for (int iPos=g.TabBegriffe.getPos("Gruppe",iAIC_Begriffgruppe);iPos<g.TabBegriffe.size() && g.TabBegriffe.getI(iPos,"Gruppe")==iAIC_Begriffgruppe;iPos++)
                        {
                          //if (g.VecBegLizenz.contains(g.TabBegriffe.getInhalt("AIC")))
                          int iBegriff=g.TabBegriffe.getInt(iPos,"AIC");
                          boolean bJeder=g.isJeder(iBegriff);
                          boolean bIsWeb=g.isWeb(iBegriff);
                          boolean bIsAPI=sEBS[i].equals("Abfrage") && g.AbfIsBit2(iBegriff,Abfrage.cstAbfAPI);
                          if (bAPI && bIsAPI || !bAPI && (CbxJeder.isSelected() || !bJeder) && !g.isTod(g.TabBegriffe.getInhalt("AIC",iPos)) && 
                        		  (/*!sEBS[i].equals("Abfrage") || */bIsWeb && CbxWeb.isSelected() || !bIsWeb && CbxAll.isSelected()) &&
                              (!sEBS[i].equals("Modul") && (bJeder || VecLizBeg==null || VecLizBeg.contains(iBegriff)) || VecModul==null || VecModul.contains(iBegriff))) //g.Lizenz(iPos)/* && (!sEBS[i].equals("Modell") || g.TabBegriffe.getI("Stt")>0 || g.TabBegriffe.getI("Erf")>0)*/)
                                        {
                                                VecVisible = new Vector<Comparable>();
                                                VecVisible.addElement(g.getBegBez2(iPos));
                                                VecVisible.addElement(g.TabBegriffe.getS(iPos,"Kennung"));
                                                VecVisible.addElement(g.TabBegriffe.getI(iPos,"Stt")>0?g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt")):"");
                                                VecVisible.addElement(Static.JaNein2(bIsWeb));
                                                VecVisible.addElement(Static.JaNein2(bJeder));
                                                VecVisible.addElement(iBegriff);
                                                long lBits=g.TabBegriffe.getL(iPos,"Bits");
                                                boolean bPers=false;
                                                if (sEBS[i].equals("Aufgabe"))
                                                {
                                                	bPers=(lBits&DefAufgabe.PS)>0;
                                                	VecVisible.addElement(Static.JaNein2(bPers));
                                                	VecVisible.addElement(Static.JaNein2((lBits&DefAufgabe.DA)>0));
                                                }
                                                else if (sEBS[i].equals("Frame"))
                                                {
                                                	bPers=(lBits&Formular.cstPers)>0;
                                                	VecVisible.addElement(Static.JaNein2(bPers));
                                                	VecVisible.addElement(null);
                                                }
                                                else
                                                {
                                                	VecVisible.addElement(sEBS[i].equals("Titel") ? Static.JaNein2((lBits& Global.cstBer)>0) :null);
                                                	VecVisible.addElement(null);
                                                }
                                                VecVisible.addElement(g.TabBegriffe.getS(iPos,"DefBezeichnung"));
                                                VecVisible.addElement(Static.JaNein2(bIsAPI));
                                                if (!sEBS[i].equals("Titel") || bIsWeb || (g.TabBegriffe.getI(iPos,"bits") & Global.cstBer)>0)
                                                {
	                                                JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeParent);
	                                                Node.setUserData(iBegriff);
	                                                Node.setStyle(g.isTod(iBegriff) ? StyleT: bPers ? StyleP:Style);
	                                                TabAll.addInhalt("Data",iBegriff);
	                                                TabAll.addInhalt("Node",Node);
                                                }
                                        }
                        }
                        /*if(Qry.open("SELECT AIC_Stammtyp,AIC_Begriff AIC, Kennung,Defbezeichnung Bezeichnung,AIC_Begriffgruppe Gruppe from Begriff WHERE AIC_Begriffgruppe="+iAIC_Begriffgruppe+" AND (jeder=0 OR jeder is null) order by bezeichnung"))
                        {
                                for(;!Qry.eof();Qry.moveNext())
                                {
                                        if (g.TabBegriffe.posInhalt("Aic",Qry.getI("aic")) && g.Lizenz())
                                        {
                                                VecVisible = new Vector();
                                                VecVisible.addElement(Qry.getS("Bezeichnung").equals("")?Qry.getS("Kennung"):Qry.getS("Bezeichnung"));
                                                VecVisible.addElement(Qry.getS("Kennung"));
                                                VecVisible.addElement(Qry.getI("AIC_Stammtyp")>0?g.TabStammtypen.getBezeichnung(Qry.getI("AIC_Stammtyp")):"");
                                                VecVisible.addElement(Qry.getInt("AIC"));
                                                JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeParent);
                                                Node.setUserData(Qry.getInt("AIC"));
                                                Node.setStyle(Style);
                                        }
                                }
                                Qry.close();
                        }*/
                }
        }
        OutGruppen.sortByColumn(0,Sort.sortMethod);
        for(int i=0;i<VecParent.size();i++)
                VecParent.elementAt(i).setState(BWTEnum.FOLDER_CLOSED);
        g.clockInfo("OutGruppe-Beg",lClock);lClock=Static.get_ms();

        OutGruppen.folderChanged(OutGruppen.getRootNode());

  }

  private void checkLoad(JCOutlinerNode Nod)
{
//	  g.fixInfo("checkLoad");
        int iAic=((Integer)Nod.getUserData()).intValue();
        //g.progInfo(iAic+":"+TabBenutzergruppe.posInhalt("AIC_BG",iAic));
        if (!TabBenutzergruppe.posInhalt("AIC_BG",iAic))
        {
          long lClock=Static.get_ms();
          String [] sEBS2= new String [] {"EIGENSCHAFT","BEGRIFF","STAMMTYP","HAUPTSCHIRM","BEWEGUNGSTYP"};
          SQL Qry=new SQL(g);
          Vector<Object> VecVisible;
        for(int i=0;i<sEBS2.length;i++)
        {
          int iTabAic=g.TabTabellenname.getAic(sEBS2[i]);
          boolean bBegriff = sEBS2[i].equalsIgnoreCase("BEGRIFF");
          boolean bEig=sEBS2[i].equalsIgnoreCase("EIGENSCHAFT");
          boolean bHS=sEBS2[i].equalsIgnoreCase("HAUPTSCHIRM");
          boolean bStt=sEBS2[i].equalsIgnoreCase("STAMMTYP");
          boolean bBew=sEBS2[i].equalsIgnoreCase("BEWEGUNGSTYP");
//          boolean bFirst=true;
          for(Qry.open("SELECT BG.AIC_Benutzergruppe, BG.Kennung"+g.AU_Bezeichnung("Benutzergruppe","BG")+" Benutzergruppe, AIC_Fremd, B.bits"+
                     (bBegriff?" from begriffgruppe Gruppe"+g.join("begriff","Gruppe","begriffgruppe")+" join berechtigung B on begriff.aic_begriff=aic_fremd":
                     ",(SELECT Bezeichnung FROM Bezeichnung AUB WHERE AUB.aic_tabellenname="+iTabAic+" and AUB.AIC_Fremd=B.AIC_Fremd AND AUB.AIC_Sprache="+g.getSprache()+") bezeichnung from berechtigung B")+
                     g.join("benutzergruppe","BG","B","benutzergruppe")+" where AIC_Tabellenname="+iTabAic+" and BG.AIC_Benutzergruppe="+iAic+g.getReadMandanten(false,"BG")+
                     " order by BG.AIC_Benutzergruppe"+(bBegriff?",Gruppe.kennung,DefBezeichnung":",Bezeichnung"));!Qry.eof();Qry.moveNext())
          {
//        	  if (bFirst)
//        	  {
//        		  Qry.printSQL();
//        		  bFirst=false;
//        	  }
                if(Qry.getI("AIC_Fremd")!=0 && (bBegriff || bEig || bBew || bStt || bHS))
                {
                  int iPosBG=-1;
                  int iPosB=-1;
                  int iPosE=-1;
                  int iPosT=g.TabTabellenname.getPos("AIC",iTabAic);
                  int iAicM=Qry.getI("AIC_Fremd");
                        if(bBegriff)
                        {
                                iPosB=g.TabBegriffe.getPos("AIC",iAicM);
                                iPosBG=g.TabBegriffgruppen.getPos("AIC",g.TabBegriffe.getI(iPosB,"Gruppe"));
                        }
                        else if (bEig)
                          iPosE=g.TabEigenschaften.getPos("AIC",iAicM);
                        else if (bBew)
                          iPosE=g.TabErfassungstypen.getPos("AIC",iAicM);
                        else if (bStt)
                            iPosE=g.TabStammtypen.getPos("AIC",iAicM);
                        else if (bHS)
                        	iPosE=TabHS.getPos("AIC",iAicM);
                        // if (bEig)
                        // {
                        // 	g.fixtestError("Eig "+iPosE);
                        // 	if (iPosE>=0)
                        // 		g.fixtestError(g.TabEigenschaften.getS(iPosE,"Bezeichnung")+"/"+g.TabEigenschaften.getS(iPosE,"Kennung"));
                        // }
                        if (bBegriff && iPosB>=0 || (bEig || bBew || bStt || bHS) && iPosE>=0)
                        {
	                        JCOutlinerNodeStyle Style = g.getStyle(bBegriff?g.getImageBG(g.TabBegriffgruppen.getI(iPosBG,"AIC")):g.getGif(g.TabTabellenname,g.TabTabellenname.getI(iPosT,"AIC")));//(Image)g.TabTabellenname.getInhalt("Bild"));
	                        VecVisible = new Vector<Object>();
	                        Vector<Integer> VecInvisible = new Vector<Integer>();
	                        if (bBegriff && iPosB>=0 || (bEig || bBew || bStt || bHS) && iPosE>=0)
	                        {
		                        VecVisible.addElement(g.clearHtml(bBegriff?g.TabBegriffe.getS(iPosB,"Bezeichnung"):bEig?g.TabEigenschaften.getS(iPosE,"Bezeichnung"):
		                        	bBew?g.TabErfassungstypen.getS(iPosE,"Bezeichnung"):bStt?g.TabStammtypen.getS(iPosE,"Bezeichnung"):bHS?TabHS.getS(iPosE,"Bezeichnung"):""));
		                        VecVisible.addElement(bBegriff?g.TabBegriffe.getS(iPosB,"Kennung"):bEig?g.TabEigenschaften.getS(iPosE,"Kennung"):
		                        	bBew ? g.TabErfassungstypen.getS(iPosE,"Kennung"):bStt ? g.TabStammtypen.getS(iPosE,"Kennung"):bHS ? TabHS.getS(iPosE,"Kennung"):"");
	                        }
	                        else
	                        {
	                        	VecVisible.addElement("???");
	                        	VecVisible.addElement("???");
	                        }
	                        VecVisible.addElement(bBegriff?g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung"):g.TabTabellenname.getS(iPosT,"Bezeichnung"));
	                        //VecVisible.addElement(Qry.getB("Aendern")?Static.sJa:Static.sNein);
	                        //VecVisible.addElement(Qry.getB("Anlegen")?Static.sJa:Static.sNein);
	                        //VecVisible.addElement(Qry.getB("Loeschen")?Static.sJa:Static.sNein);
	                        //VecVisible.addElement((Qry.getI("bits")&1)>0?Static.sJa:Static.sNein);
	                        VecVisible.addElement(Static.JaNein2(bBegriff ? g.isWeb(iAicM): false)); // Web
                            VecVisible.addElement(Static.JaNein2(bEig ? g.isJederEig(iAicM) : bBegriff ? g.isJeder(iAicM): false)); // Jeder
	                        VecVisible.addElement(g.TabStammtypen.getBezeichnungS(bBegriff?g.TabBegriffe.getI(iPosB,"Stt"):0));
	                        VecVisible.addElement(Static.JaNein2((Qry.getI("bits")& SELBST)>0)); // selbst
	                        VecVisible.addElement(Static.JaNein2((Qry.getI("bits")& NICHT)>0)); // nicht
	                        VecVisible.addElement(Static.JaNein2((Qry.getI("bits")& RO)>0)); // ReadOnly
	                        if (g.Def())
	                        	VecVisible.addElement(iAicM);
	                        VecInvisible.addElement(iAicM);
	                        VecInvisible.addElement(iTabAic);
	                        //VecInvisible.addElement(new Boolean(Qry.getB("Aendern")));
	                        //VecInvisible.addElement(new Boolean(Qry.getB("Anlegen")));
	                        //VecInvisible.addElement(new Boolean(Qry.getB("Loeschen")));
	                        VecInvisible.addElement(new Integer(Qry.getI("bits")));
	                        JCOutlinerNode Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)Nod);
	                        Node.setUserData(VecInvisible);
	                        String sBG=bBegriff ? g.TabBegriffgruppen.getS(iPosBG,"Kennung"):Static.sLeer;
	                        boolean bPers=sBG.equals("Frame") && (g.TabBegriffe.getL(iPosB,"bits")&Formular.cstPers)>0 || sBG.equals("Aufgabe") && (g.TabBegriffe.getL(iPosB,"bits")&DefAufgabe.PS)>0;
	                        if (bPers)
	                        {
	                        	JCOutlinerNodeStyle StyleP = new JCOutlinerNodeStyle(Style);
	                        	StyleP.setForeground(Global.ColPers);
	                        	Node.setStyle(StyleP);
	                        }
	                        else
	                        	Node.setStyle(Style);
	
	                        TabBenutzergruppe.addInhalt("Tab",Qry.getInt("AIC_Benutzergruppe")+"."+Qry.getInt("AIC_Fremd")+"."+iTabAic);
	                        TabBenutzergruppe.addInhalt("AIC_BG",Qry.getInt("AIC_Benutzergruppe"));
	                        TabBenutzergruppe.addInhalt("AIC_Fremd",Qry.getInt("AIC_Fremd"));
	                        TabBenutzergruppe.addInhalt("AIC_Tabellenname",iTabAic);
	                        TabBenutzergruppe.addInhalt("Art","Vorhanden");
	                        TabBenutzergruppe.addInhalt("bits",Qry.getI("bits"));
                        }
                        else
                        	g.fixtestError("in "+g.TabTabellenname.getS(iPosT,"Bezeichnung")+" Aic "+Qry.getI("AIC_Fremd")+" nicht gefunden");
                }

          }
        }
        OutBenutzergruppe2.folderChanged(Nod);
        g.clockInfo("Lade "+Nod,lClock);
      }
}

    private void Hinzufuegen_Benutzergruppe2()
{
        JCOutlinerNode[] NodeBG = OutBenutzergruppe2.getSelectedNodes();
        JCOutlinerNode[] NodeG = OutGruppen.getSelectedNodes();

        for(int i=0;i<NodeBG.length;i++)
        {
          checkLoad(NodeBG[i]);
                for(int j=0;j<NodeG.length;j++)
                {
                        if(!(TabBenutzergruppe.posInhalt("Tab",NodeBG[i].getUserData()+"."+NodeG[j].getUserData()+"."+NodeG[j].getParent().getUserData()) && !TabBenutzergruppe.getS("Art").equals("Loeschen")))
                        {
                                //g.TabTabellenname.posInhalt("AIC",NodeG[j].getParent().getUserData());
                                Vector<Object> VecInvisible = new Vector<Object>();
                                Vector<Object> VecVisible = new Vector<Object>();
                                VecVisible.addElement(((Vector)NodeG[j].getLabel()).elementAt(0));
                                VecVisible.addElement(((Vector)NodeG[j].getLabel()).elementAt(1));
                                VecVisible.addElement(((Vector)NodeG[j].getParent().getLabel()).elementAt(0));     
                                boolean bBegriff=(int)NodeG[j].getParent().getUserData()==Global.iTabBegriff;
                                boolean bEig=(int)NodeG[j].getParent().getUserData()==iTabEig;
                                int iAic=/*bBegriff || bEig ?*/ (int)NodeG[j].getUserData();//:0;
//                                g.fixtestError("Hinzu "+iAic+"/"+NodeG[j].getParent().getUserData()+"-> bBegriff="+bBegriff+", bEig="+bEig);
                                VecVisible.addElement(Static.JaNein2(bBegriff && iAic>0 && g.isWeb(iAic))); // Web
                                VecVisible.addElement(Static.JaNein2(bEig ? g.isJederEig(iAic) : bBegriff && g.isJeder(iAic))); // Jeder
                                VecVisible.addElement(((Vector)NodeG[j].getLabel()).elementAt(2));
                                VecVisible.addElement(Static.JaNein2(CbxSelbst.isSelected()));
                                VecVisible.addElement(Static.JaNein2(CbxNicht.isSelected()));
                                VecVisible.addElement(iAic);
                                VecInvisible.addElement(NodeG[j].getUserData());
                                VecInvisible.addElement(NodeG[j].getParent().getUserData());
                                //VecInvisible.addElement(new Boolean(CbxAendern.isSelected()));
                                //VecInvisible.addElement(new Boolean(CbxAnlegen.isSelected()));
                                //VecInvisible.addElement(new Boolean(CbxLoeschen.isSelected()));
                                int iBits=/*(CbxGruppe.isSelected()?GRUPPE:0)+*/(CbxSelbst.isSelected()?SELBST:0)+(CbxNicht.isSelected()?NICHT:0)+(CbxReadOnly.isSelected()? RO:0);
                                VecInvisible.addElement(new Integer(iBits));
                                JCOutlinerNode Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)NodeBG[i]);
                                Node.setUserData(VecInvisible);
                                Node.setStyle(NodeG[j].getStyle());

                                if(!TabBenutzergruppe.getS("Art").equals("Loeschen"))
                                {
                                        TabBenutzergruppe.posInhalt("AIC_BG",NodeBG[i].getUserData());
                                        TabBenutzergruppe.putInhalt("Tab",NodeBG[i].getUserData()+"."+NodeG[j].getUserData()+"."+NodeG[j].getParent().getUserData(),true);
                                        TabBenutzergruppe.putInhalt("AIC_BG",NodeBG[i].getUserData(),true);
                                        TabBenutzergruppe.putInhalt("AIC_Fremd",NodeG[j].getUserData(),true);
                                        TabBenutzergruppe.putInhalt("AIC_Tabellenname",NodeG[j].getParent().getUserData(),true);
                                        TabBenutzergruppe.putInhalt("Art","Neu",true);
                                        //TabBenutzergruppe.putInhalt("Aendern",new Boolean(CbxAendern.isSelected()),true);
                                        //TabBenutzergruppe.putInhalt("Anlegen",new Boolean(CbxAnlegen.isSelected()),true);
                                        //TabBenutzergruppe.putInhalt("Loeschen",new Boolean(CbxLoeschen.isSelected()),true);
                                        TabBenutzergruppe.putInhalt("bits",new Integer(iBits),true);
                                }
                                else
                                {
                                        TabBenutzergruppe.setInhalt("Art","Aendern");
                                        //TabBenutzergruppe.setInhalt("Aendern",new Boolean(CbxAendern.isSelected()));
                                        //TabBenutzergruppe.setInhalt("Anlegen",new Boolean(CbxAnlegen.isSelected()));
                                        //TabBenutzergruppe.setInhalt("Loeschen",new Boolean(CbxLoeschen.isSelected()));
                                        TabBenutzergruppe.setInhalt("bits",new Integer(iBits));
                                }
                             
                        }
                }
        }

        if(g.Debug())
                TabBenutzergruppe.showGrid("TabBenutzergruppe");

        OutBenutzergruppe2.folderChanged(OutBenutzergruppe2.getRootNode());
        //OutBenutzergruppe3.folderChanged(OutBenutzergruppe3.getRootNode());
}

private void Entfernen_Benutzergruppe2()
{
        JCOutlinerNode[] NodeBG = OutBenutzergruppe2.getSelectedNodes();

        for(int i=0;i<NodeBG.length;i++)
        {
          if (i==0)
            OutBenutzergruppe2.selectNode(OutBenutzergruppe2.getPreviousNode(NodeBG[i]),null);
                if(TabBenutzergruppe.posInhalt("Tab",NodeBG[i].getParent().getUserData()+"."+((Vector)NodeBG[i].getUserData()).elementAt(0)+"."+((Vector)NodeBG[i].getUserData()).elementAt(1)))
                {
                        if(!TabBenutzergruppe.getS("Art").equals("Neu"))
                                TabBenutzergruppe.setInhalt("Art","Loeschen");
                        else
                                TabBenutzergruppe.clearInhalt();                       

                        NodeBG[i].getParent().removeChild(NodeBG[i]);
                }
                else
                {
                        Static.printError("UserManager.Entfernen_Benutzergruppe2(): "+NodeBG[i].getParent().getUserData()+"."+((Vector)NodeBG[i].getUserData()).elementAt(0)+"."+((Vector)NodeBG[i].getUserData()).elementAt(1));
                        Static.printError("UserManager.Entfernen_Benutzergruppe2(): Fehler beim positionieren in TabBenutzergruppe!!! Darf nicht vorkommen!!!");
                }
        }

        if(g.Debug())
        {
                TabBenutzergruppe.showGrid("TabBenutzergruppe");
                //TabStamm.showGrid("TabStamm");
        }
        OutBenutzergruppe2.folderChanged(OutBenutzergruppe2.getRootNode());
        //OutBenutzergruppe3.folderChanged(OutBenutzergruppe3.getRootNode());
}

@SuppressWarnings("unchecked")
private void Aendern_Benutzergruppe2()
{
        JCOutlinerNode[] NodeBG = OutBenutzergruppe2.getSelectedNodes();

        for(int i=0;i<NodeBG.length && NodeBG[i].getLevel()==2;i++)
        {
                Vector<String> VecVisible = (Vector)NodeBG[i].getLabel();
                Vector<Object> VecInvisible = (Vector)NodeBG[i].getUserData();
                //boolean bAendern = ((Boolean)VecInvisible.elementAt(2)).booleanValue();
                //boolean bAnlegen = ((Boolean)VecInvisible.elementAt(3)).booleanValue();
                //boolean bLoeschen = ((Boolean)VecInvisible.elementAt(4)).booleanValue();
                int iBitsOld = ((Integer)VecInvisible.elementAt(2)).intValue();
                int iBits=/*(CbxGruppe.isSelected()?GRUPPE:0)+*/(CbxSelbst.isSelected()? SELBST:0)+(CbxNicht.isSelected()? NICHT:0)+(CbxReadOnly.isSelected()? RO:0);
                if(iBitsOld!=iBits)
                {
                        //VecVisible.setElementAt(3,CbxAendern.isSelected()?Static.sJa:Static.sNein);
                        //VecVisible.setElementAt(4,CbxAnlegen.isSelected()?Static.sJa:Static.sNein);
                        //VecVisible.setElementAt(5,CbxLoeschen.isSelected()?Static.sJa:Static.sNein);
                        VecVisible.setElementAt(/*CbxGruppe.isSelected()?Static.sJa:*/Static.sNein,5);
                        VecVisible.setElementAt(Static.JaNein2(CbxSelbst.isSelected()),6);
                        VecVisible.setElementAt(Static.JaNein2(CbxNicht.isSelected()),7);
                        VecVisible.setElementAt(Static.JaNein2(CbxReadOnly.isSelected()),8);
                        //VecInvisible.setElementAt(2,new Boolean(CbxAendern.isSelected()));
                        //VecInvisible.setElementAt(3,new Boolean(CbxAnlegen.isSelected()));
                        //VecInvisible.setElementAt(4,new Boolean(CbxLoeschen.isSelected()));
                        VecInvisible.setElementAt(new Integer(iBits),2);

                        if(TabBenutzergruppe.posInhalt("Tab",NodeBG[i].getParent().getUserData()+"."+((Vector)NodeBG[i].getUserData()).elementAt(0)+"."+((Vector)NodeBG[i].getUserData()).elementAt(1)))
                        {
                                if(!TabBenutzergruppe.getS("Art").equals("Neu"))
                                        TabBenutzergruppe.setInhalt("Art","Aendern");
                                //TabBenutzergruppe.setInhalt("Aendern",new Boolean(CbxAendern.isSelected()));
                                //TabBenutzergruppe.setInhalt("Anlegen",new Boolean(CbxAnlegen.isSelected()));
                                //TabBenutzergruppe.setInhalt("Loeschen",new Boolean(CbxLoeschen.isSelected()));
                                TabBenutzergruppe.setInhalt("bits",new Integer(iBits));
                                g.progInfo("Aendern_Benutzergruppe2: Bits="+iBits);
                        }
                        else
                        {
                                Static.printError("UserManager.Aendern_Benutzergruppe2(): "+NodeBG[i].getParent().getUserData()+"."+((Vector)NodeBG[i].getUserData()).elementAt(0)+"."+((Vector)NodeBG[i].getUserData()).elementAt(1));
                                Static.printError("UserManager.Aendern_Benutzergruppe2(): Fehler beim positionieren in TabBenutzergruppe!!! Darf nicht vorkommen!!!");
                        }
                      
                }
        }

        if(g.Debug())
        {
                TabBenutzergruppe.showGrid("TabBenutzergruppe");
                //TabStamm.showGrid("TabStamm");
        }

        OutBenutzergruppe2.folderChanged(OutBenutzergruppe2.getRootNode());
        //OutBenutzergruppe3.folderChanged(OutBenutzergruppe3.getRootNode());
}

  private void EnableButtons()
  {
    JCOutlinerNode[] NodeBG2 = OutBenutzergruppe2.getSelectedNodes();
    JCOutlinerNode[] NodeG = OutGruppen.getSelectedNodes();

    BtnHinzufuegen.setEnabled(NodeBG2!=null && NodeBG2.length>0 && NodeBG2[0].getLevel()==1 && NodeG!=null && NodeG.length>0 && NodeG[0].getLevel()==2);
    BtnEntfernen.setEnabled(NodeBG2!=null && NodeBG2.length>0 && NodeBG2[0].getLevel()==2);
  }

  private void checkSave()
  {
    boolean bSave=false;
    for(TabBenutzergruppe.moveFirst();!TabBenutzergruppe.eof();TabBenutzergruppe.moveNext())
    {
      if (!TabBenutzergruppe.getS("Art").equals("Vorhanden"))
        bSave = true;
    }
    if (bSave && new Message(Message.YES_NO_OPTION,(JFrame)this.thisFrame,g).showDialog("Speichern")==Message.YES_OPTION)
      Save();
  }

  private void Save()
  {
    SQL Qry = new SQL(g);
    for(TabBenutzergruppe.moveFirst();!TabBenutzergruppe.eof();TabBenutzergruppe.moveNext())
    {
            String sArt = TabBenutzergruppe.getS("Art");
            if(!sArt.equals("Vorhanden"))
            {
                    //Qry.add("Aendern",TabBenutzergruppe.getB("Aendern"));
                    //Qry.add("Anlegen",TabBenutzergruppe.getB("Anlegen"));
                    //Qry.add("Loeschen",TabBenutzergruppe.getB("Loeschen"));
                    Qry.add("bits",TabBenutzergruppe.getI("bits"));
                    if(sArt.equals("Aendern"))
                    {
                            Qry.addWhere("AIC_Benutzergruppe",TabBenutzergruppe.getI("AIC_BG"));
                            Qry.addWhere("AIC_Fremd",TabBenutzergruppe.getI("AIC_Fremd"));
                            Qry.addWhere("AIC_Tabellenname",TabBenutzergruppe.getI("AIC_Tabellenname"));
                            Qry.update("Berechtigung");
                    }
                    else
                    {
                            Qry.add("AIC_Benutzergruppe",TabBenutzergruppe.getI("AIC_BG"));
                            Qry.add("AIC_Fremd",TabBenutzergruppe.getI("AIC_Fremd"));
                            Qry.add("AIC_Tabellenname",TabBenutzergruppe.getI("AIC_Tabellenname"));
                            if(sArt.equals("Neu"))
                                    Qry.insert("Berechtigung",false);
                            else
                            {
                                    Qry.delete("Berechtigung");
                                    Qry.clear();
                            }
                    }
                    TabBenutzergruppe.setInhalt("Art","Vorhanden");
            }
    }
    Qry.free();
    g.sendWebDB("refreshDB",null,thisJFrame());
  }

}
