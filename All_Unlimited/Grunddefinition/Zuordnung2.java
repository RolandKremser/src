package All_Unlimited.Grunddefinition;

import java.awt.Color;
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
import javax.swing.JRadioButton;
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
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.RolleEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Print.Drucken;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import All_Unlimited.Allgemein.Anzeige.Zeit;

/**
 * <p>Überschrift: neue Zuordnung</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class Zuordnung2 extends Formular
{
  private Global g;
  private AUOutliner OutGruppen = new AUOutliner(new JCOutlinerFolderNode(""));
  private AUOutliner OutEigenschaft = new AUOutliner(new JCOutlinerFolderNode(""));
  private Tabellenspeicher TabG;
  private Tabellenspeicher TabE;
  private ComboSort CboTabellen;
  private ComboSort CboStt;
  private RolleEingabe CboRolle;
  private JPanel PnlArt=null;
  private boolean bReihenfolge;
  private boolean bReady=false;
  private boolean bRefresh=false;
  private String sKennungG;
  private String sKennungE;
  private String sKennungGZ;
  private String sKennungEZ;
  private String sTab;
  private int iBG;
  private int iBG2;
  private int iLastAic=0;

  private JButton BtnDel;
  private JButton BtnCopy;
  private JButton BtnSpeichern;
  //private JButton BtnShow;
  //private JButton BtnBeenden;
  //private JButton BtnDrehen;
  //private JButton BtnRefresh;
  private JButton BtnPfeilrechts;
  private JButton BtnErsetzen;
  private JButton BtnPfeillinks;
  private JButton BtnPfeiloben;
  private JButton BtnSeperator;
  private JButton BtnPfeilunten;
  private int iPosPos=4;
  private Color ColNormal=Color.BLACK;
  private Color ColHinzu=Color.GREEN.darker();
  //private Color ColWeg=Color.RED.darker();
  private Color ColPos=Color.BLUE.darker();

  private JCheckBox CbxBez;
  private JCheckBox CbxCase=null;
  private JCheckBox CbxVoll=null;
  private JRadioButton RadLinks;
  private JRadioButton RadRechts;
  private Text TxtSuche = new Text("",62);
  private int iGPos=-1;
  private int iEPos=-1;
  //private Tabellenspeicher TabAll;

  private static Zuordnung2 Self;

  public static Zuordnung2 get(Global rg)
  {
    if (Self==null)
      Self=new Zuordnung2(rg);
    else
      Self.show();
    return Self;
  }

  public static void free()
  {
     if (Self != null)
     {
       Self.g.winInfo("Zuordnung2.free");
       Self.thisFrame.dispose();
       Self = null;
     }
  }

  private Zuordnung2(Global glob)
        {
          super("Zuordnung2", null, glob);
          g = glob;
          g.winInfo("Zuordnung2.create");
          if (!g.Def())
      	  {
      		new Message(Message.WARNING_MESSAGE,null,g).showDialog("keineBerechtigung");
      		thisFrame.dispose();
      		return;
      	  }
          CboTabellen= new ComboSort(g);
          CboTabellen.setPreferredSize(new java.awt.Dimension(200,20));
          //CboTabellen.setFont(g.fontStandard);
          CboTabellen.fillDefTable("ZUORDNUNG",true);

          String[] sLabels1 = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Info"),g.getShow("Aic"),g.getBegriffS("Checkbox","Jeder"),g.getBegriffS("Checkbox","Tod"),g.getShow("Web")};
          String[] sLabels2 = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Info"),g.getShow("Aic"),g.getShow("Anzahl"),g.getBegriffS("Checkbox","Jeder"),g.getBegriffS("Checkbox","Tod"),g.getShow("Web")};
          OutEigenschaft.setColumnLabelSortMethod(Sort.sortMethod);
          OutEigenschaft.setRootVisible(false);
          OutEigenschaft.setColumnButtons(sLabels1);
          OutEigenschaft.setNumColumns(sLabels1.length);
          OutEigenschaft.setColumnWidth(3,50);
          OutEigenschaft.setAllowMultipleSelections(true);
          OutGruppen.setColumnLabelSortMethod(Sort.sortMethod);
          OutGruppen.setRootVisible(false);
          OutGruppen.setColumnButtons(sLabels2);
          OutGruppen.setNumColumns(sLabels2.length);
          OutGruppen.setColumnWidth(3,50);
          OutGruppen.setColumnWidth(iPosPos,60);
          OutGruppen.setAllowMultipleSelections(true);
          //OutGruppen.setCopy(true); // wieder entfernt, weil er auf kopierte Änderungen nicht reagiert

          Static.addContainer(getFrei("Outliner Gruppen"),OutGruppen);
          Static.addContainer(getFrei("Outliner Eigenschaft"),OutEigenschaft);
          Static.addContainer(getFrei("Combo Tabellen"),CboTabellen);
          PnlArt=getFrei("Combo Art");

          //fillOutliners(true);
          BtnPfeilrechts = getButton(">");
          BtnErsetzen = getButton("Ersetzen");
          BtnPfeillinks = getButton("<");
          BtnPfeilunten = getButton("Pfeil unten");
          BtnSeperator = getButton("Seperator");
          BtnPfeiloben = getButton("Pfeil oben");

          BtnDel = getButton("Loeschen");
          BtnCopy = getButton("Kopie");
          BtnSpeichern = getButton("Speichern");
          //BtnBeenden = getButton("Beenden");

          CbxBez = getCheckbox("Bezeichnung");
          if(CbxBez==null) CbxBez = new JCheckBox();
          CbxVoll = getCheckbox("Volltextsuche");
          //if(CbxVoll==null) CbxVoll = new JCheckBox();
          CbxCase = getCheckbox("Case");
          //if(CbxCase==null) CbxCase = new JCheckBox();
          RadLinks = getRadiobutton("links");
          if (RadLinks == null) RadLinks=new JRadioButton();
          RadRechts = getRadiobutton("rechts");
          if (RadRechts == null) RadRechts=new JRadioButton();


          JPanel PnlEingabe = getFrei("Eingabe");
          if (PnlEingabe != null)
          {
            PnlEingabe.add(TxtSuche);
            TxtSuche.addKeyListener(new KeyListener()
            {
			public void keyPressed(KeyEvent ev)
			{
			}
			public void keyReleased(KeyEvent ev)
			{
			  int iK=ev.getKeyCode();
			  if (iK==KeyEvent.VK_DOWN || iK==KeyEvent.VK_ENTER)
			    Suche(TxtSuche.getText(),true);
			  else if (iK==KeyEvent.VK_UP)
			    Suche(TxtSuche.getText(),false);
			}
			public void keyTyped(KeyEvent ev)
			{
			}
            });
          }

                CboTabellen.addItemListener(new ItemListener()
                  {
                          public void itemStateChanged(ItemEvent ev)
                          {
                                  if(ev.getStateChange()==ItemEvent.SELECTED)
                                  {
                                    if (BtnSpeichern.isEnabled() && checkSave())
                                    {
                                      int pane = new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Speichern", new String[]
                                          {"die Änderung"});
                                      if (pane == Message.YES_OPTION)
                                        Speichern();
                                    }
                                    Refresh(true);
                                  }
                          }
                  });

                OutEigenschaft.addItemListener(new JCOutlinerListener ()
                {
                        public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
                        {
                                EnableButtons();
                        }
                        public void outlinerNodeSelectBegin(JCOutlinerEvent ev) {}
                        public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev) {}
                        public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev) {}
                        public void itemStateChanged(JCItemEvent ev) {}
                });

                OutGruppen.addItemListener(new JCOutlinerListener ()
                {
                        public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
                        {
                                EnableButtons();
                        }
                        public void outlinerNodeSelectBegin(JCOutlinerEvent ev) {}
                        public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev) {}
                        public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev) {}
                        public void itemStateChanged(JCItemEvent ev) {}
                });
                OutGruppen.getOutliner().addMouseListener(new MouseListener()
                  {
                    public void mousePressed(MouseEvent ev) {}
                    public void mouseClicked(MouseEvent ev)
                    {
                        if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                        {
                          //Vector Vec=(Vector)OutGruppen.getSelectedNode().getUserData();
                          //g.testInfo("rechte Maustaste:"+Vec);
                          if (TabE.posInhalt(sKennungE, Sort.geti(OutGruppen.getSelectedNode().getUserData())))
                              Static.makeVisible(null, ((JCOutlinerNode)TabE.getInhalt("Nod")));
                        }
                    }
                    public void mouseEntered(MouseEvent ev) {}
                    public void mouseExited(MouseEvent ev)  {}
                    public void mouseReleased(MouseEvent ev){}
                  });

                  ActionListener AL=new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      String s = ev.getActionCommand();
                      if(s.equals("Refresh"))
                        Refresh(true);
                      else if(s.equals("Del"))
                        DelZ2();
                      else if(s.equals("Copy"))
                        CopyZ2();
                      else if(s.equals("Speichern"))
                        Speichern();
                      else if(s.equals("Hinzu"))
                        Hinzufuegen();
                      else if(s.equals("Ersetzen"))
                        Ersetzen();
                      else if(s.equals("Weg"))
                        Loeschen();
                      else if(s.equals("runter") || s.equals("rauf"))
                        Verschieben(s.equals("rauf"));
                      else if(s.equals("-"))
                        Seperator_Hinzufuegen();
                      else if(s.equals("Beenden"))
                      {
                        if(BtnSpeichern.isEnabled() && checkSave())
                        {
                          int pane = new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Speichern", new String[] {"die Änderung"});
                          if(pane == Message.YES_OPTION)
                            Speichern();
                        }
                        hide();
                      }
                      else if(s.equals("show") && iBG>0 && iBG2>0)
                        getTab(OutEigenschaft).showGrid("Zuordnungsanzeige");
                    }
                  };

                  g.BtnAdd(getButton("Refresh"),"Refresh",AL);
                  g.BtnAdd(BtnDel,"Del",AL);
                  g.BtnAdd(BtnCopy,"Copy",AL);
                  g.BtnAdd(BtnSpeichern,"Speichern",AL);
                  g.BtnAdd(BtnPfeilrechts,"Hinzu",AL);
                  g.BtnAdd(BtnErsetzen,"Ersetzen",AL);
                  g.BtnAdd(BtnPfeillinks,"Weg",AL);
                  g.BtnAdd(BtnPfeilunten,"runter",AL);
                  g.BtnAdd(BtnPfeiloben,"rauf",AL);
                  g.BtnAdd(BtnSeperator,"-",AL);
                  g.BtnAdd(getButton("Beenden"),"Beenden",AL);
                  g.BtnAdd(getButton("show"),"show",AL);

          EnableButtons();
          show();
        }

        /*private void fillRolle()
        {
          CboRolle.fillCbo("select aic_Rolle,kennung"+g.AU_Bezeichnung("Rolle")+" from rolle where aic_stammtyp="+CboStt.getSelectedAIC(),"Rolle",true,false);
        }*/

  private Tabellenspeicher getTab(AUOutliner Out)
  {
      //g.progInfo("iBG="+iBG+"/"+iBG2+", CboTab="+CboTabellen.getSelectedKennung());
      String sBed=CboTabellen.getSelectedKennung().equals("Begriff") ? " and z.aic_begriff":" and z.beg_aic_begriff";
      return new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("Begriffgruppe","b")+" Gruppe,b.defbezeichnung Begriff,b2.defBezeichnung "+g.TabBegriffgruppen.getKennung(iBG)+
                                                 ",b2.kennung,b2.aic_begriff"+g.AU_Bezeichnung1("Stammtyp","b2")+" Stammtyp"+g.AU_Bezeichnung1("Bewegungstyp","b2")+" Bewegungstyp"+
                                                 " from begriff b join begriff_zuordnung z on z.beg_aic_begriff=b.aic_begriff join begriff b2 on z.aic_begriff=b2.aic_begriff"+
                                                 " and b2.aic_begriffgruppe="+iBG+sBed+Static.SQL_in(g.getAics(Out)),true);
  }

  private void Suche(String sK,boolean bDown)
  {
    //boolean bSub=sK.startsWith("-");
    //sK=bSub ? sK.substring(1):sK;
    String sKT=CbxBez.isSelected() ? "Bezeichnung":"Kennung";
    boolean bVoll=CbxVoll==null?false:CbxVoll.isSelected();
    boolean bCase=CbxCase==null?true:CbxCase.isSelected();
    Tabellenspeicher Tab=TabG;
    int i=iGPos;
    if (sK.startsWith("1:") || RadLinks.isSelected())
    {
      Tab=TabE;
      i=iEPos;
      if (!RadLinks.isSelected())
        sK=sK.substring(2);
    }
    //if (bModell)
    {
      i=bDown ? Tab.posLikeD(i,sKT,sK,bVoll,bCase):Tab.posLikeU(i,sKT,sK,bVoll,bCase);
      if (i>-1)
      {
        JCOutlinerNode Nod=(JCOutlinerNode)Tab.getInhalt("Nod",i);
        //openUp(Nod);
        if (Tab==TabG)
        {
          iGPos=i;
          Static.makeVisible(OutGruppen, Nod);
        }
        else if (Tab==TabE)
        {
          Static.makeVisible(OutEigenschaft, Nod);
          iEPos=i;
        }
      }
    }
  }

        private void Refresh(boolean bSttFill)
        {
          if (bRefresh)
          {
            //g.progInfo("Refresht bereits");
            return;
          }
          bRefresh=true;
          //g.progInfo("iLastAic1="+iLastAic);
          bReady=false;
          //g.progInfo("Refresh:"+CboTabellen.getSelectedKennung());
          //if (CboTabellen.getSelectedKennung().equals("ROLLE") || sKennungE.equals("Eigenschaft"))
          //{
            if (CboStt == null)
            {
              CboStt = new ComboSort(g);

              CboStt.addItemListener(new ItemListener()
              {
                      public void itemStateChanged(ItemEvent ev)
                      {
                        CboRolle.fillRolle(CboStt.getSelectedAIC(),true);
                        Refresh(false);
                      }
              });
              PnlArt.add(CboStt);
              CboRolle = new RolleEingabe(g,thisFrame);
              CboRolle.setPreferredSize(new java.awt.Dimension(100,20));
              PnlArt.add(CboRolle);
              CboRolle.Cbo.addItemListener(new ItemListener()
              {
                      public void itemStateChanged(ItemEvent ev)
                      {
                        if(ev.getStateChange() == ItemEvent.SELECTED)
                          Refresh(false);
                      }
              });
              CboRolle.fillRolle(CboStt.getSelectedAIC(),true);
              //fillRolle();
            }
            //return;
          //}
          if (bSttFill)
          {
            if (CboTabellen.getSelectedKennung().equals("ROLLE"))
              CboStt.fillCbo("select distinct stammtyp.aic_stammtyp,stammtyp.kennung" + g.AU_Bezeichnung("Stammtyp") + " from stammtyp" +
                             g.join2("rolle", "stammtyp") + " where rol_aic_rolle is null", "Stammtyp", false, false);
            else
              CboStt.fillDefTable("Stammtyp", true);
          }
          ((JCOutlinerFolderNode)OutGruppen.getRootNode()).removeChildren();
          ((JCOutlinerFolderNode)OutEigenschaft.getRootNode()).removeChildren();

                SQL Qry = new SQL(g);

                if(Qry.open("SELECT * FROM Zuordnung WHERE AIC_Zuordnung="+CboTabellen.getSelectedAIC()) && !Qry.eof())
                {

                        bReihenfolge= Qry.getI("Reihenfolge")==1;

                        //EnableButtons();
                        /*if (Qry.getI("AIC_Begriffgruppe")>0)
                        {
                          g.TabBegriffgruppen.posInhalt("Aic",Qry.getI("AIC_Begriffgruppe"));
                          //BtnNewGrp.setIcon(g.LoadImageIcon(g.TabBegriffgruppen.getS("filename")));
                        }
                        else
                        {
                          g.TabTabellenname.posInhalt("Aic",Qry.getI("AIC_Tabellenname"));
                          //BtnNewGrp.setIcon(g.TabTabellenname.isNull("Bild") ? null:new ImageIcon((Image)g.TabTabellenname.getInhalt("Bild")));
                        }
                        if (Qry.getI("Beg_AIC_Begriffgruppe")>0)
                        {
                          g.TabBegriffgruppen.posInhalt("Aic",Qry.getI("Beg_AIC_Begriffgruppe"));
                          //BtnNewEig.setIcon(g.LoadImageIcon(g.TabBegriffgruppen.getS("filename")));
                        }
                        else
                        {
                          g.TabTabellenname.posInhalt("Aic",Qry.getI("Tab_AIC_Tabellenname"));
                          //BtnNewEig.setIcon(g.TabTabellenname.isNull("Bild") ? null:new ImageIcon((Image)g.TabTabellenname.getInhalt("Bild")));
                        }*/
                        sKennungG=null;
                        sKennungE=null;
                        if (Qry.getS("Kennung").equals("Begriff"))
                        {
                          fillOutliner(1, Qry.getI("AIC_Tabellenname"), Qry.getI("AIC_Begriffgruppe"));
                          fillOutliner(0, Qry.getI("Tab_AIC_Tabellenname"), Qry.getI("Beg_AIC_Begriffgruppe"));
                        }
                        else
                        {
                          fillOutliner(0, Qry.getI("AIC_Tabellenname"), Qry.getI("AIC_Begriffgruppe"));
                          fillOutliner(1, Qry.getI("Tab_AIC_Tabellenname"), Qry.getI("Beg_AIC_Begriffgruppe"));
                        }
                        iBG=Qry.getI("AIC_Begriffgruppe");
                        iBG2=Qry.getI("Beg_AIC_Begriffgruppe");
                        fuelleOutZuordnung(Qry.getS("Kennung"));
                }

                Qry.free();
                OutGruppen.folderChanged(OutGruppen.getRootNode());
                OutEigenschaft.folderChanged(OutEigenschaft.getRootNode());
                //g.progInfo("iLastAic2="+iLastAic);
                if (iLastAic>0 && TabG.posInhalt(sKennungG,iLastAic))
                {
                  JCOutlinerFolderNode Nod=(JCOutlinerFolderNode)TabG.getInhalt("Nod");
                  Static.makeVisible(OutGruppen, Nod);
                  Nod.setState(BWTEnum.FOLDER_OPEN_ALL);
                  OutGruppen.folderChanged(Nod);
                }
                bReady=true;
                EnableButtons();
                bRefresh=false;
                //TabG.showGrid("Gruppe");
                //TabE.showGrid("Eig");
        }

        @SuppressWarnings("unchecked")
        private void fuelleOutZuordnung(String sKennung)
        {
          boolean bBegriff=iBG2>0 && !sKennung.equals("Programm");
          sTab=(bBegriff ?"Begriff":sKennung)+"_Zuordnung";
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select z.* from "+sTab+" z"+
                                                    (bBegriff ? " join begriff on z.aic_begriff=begriff.aic_begriff and aic_begriffgruppe="+iBG:"")
                                                    +" order by z."+sKennungGZ+(bReihenfolge?",z.Reihenfolge":""),true);
          //Tab.showGrid();
          //g.progInfo(sKennungG+"/"+sKennungE+"/"+sKennungGZ+"/"+sKennungEZ);
          int iMom=-1;
          int iGruppe=0;
          JCOutlinerFolderNode NodLast=null;
          for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
            if (TabG.posInhalt(sKennungG,Tab.getInhalt(sKennungGZ)) && TabE.posInhalt(sKennungE,Tab.getInhalt(sKennungEZ)))
            {
              if (iGruppe != Tab.getI(sKennungGZ))
              {
                if (NodLast !=null)
                  ((Vector)NodLast.getLabel()).setElementAt(new Integer(NodLast.getChildren()==null ? 0:NodLast.getChildren().size()),iPosPos);
                iMom=0;
                iGruppe=Tab.getI(sKennungGZ);
              }
              NodLast=(JCOutlinerFolderNode)TabG.getInhalt("Nod");
              if (bReihenfolge)
              {
                int iReihenfolge=Tab.getI("Reihenfolge");
                iMom++;
                for (; iMom < iReihenfolge; iMom++)
                {
                  Vector<Comparable> VecSepVisible = new Vector<Comparable>();
                  VecSepVisible.addElement("-------------");
                  VecSepVisible.addElement("-------------");
                  VecSepVisible.addElement(null);
                  VecSepVisible.addElement(null);
                  VecSepVisible.addElement(new Integer(iMom));
                  JCOutlinerNode Nod = new JCOutlinerNode(VecSepVisible, NodLast);
                  Nod.setUserData(Static.Int0);
                  g.setStyle(Nod,ColNormal);
                }
                iMom=iReihenfolge;
              }
              Vector<Object> VecVisible = new Vector<Object>();
              VecVisible.addElement(TabE.getS("Bezeichnung"));
              VecVisible.addElement(TabE.getS("Kennung"));
              VecVisible.addElement(TabE.getS("Info"));
              VecVisible.addElement(TabE.getInhalt(sKennungE));
              VecVisible.addElement(bReihenfolge ? Tab.getInhalt("Reihenfolge"):null);
              Object IntAic=TabE.getInhalt(sKennungE);
              if(sKennungE.equalsIgnoreCase("AIC_Eigenschaft"))
              {
                VecVisible.addElement(Static.JaNein2(g.isJederEig(IntAic)));
                VecVisible.addElement(Static.JaNein2(g.EigIsTod(IntAic)));
              }
              else if(sKennungE.equalsIgnoreCase("AIC_Begriff"))
              {
                VecVisible.addElement(Static.JaNein2(g.isJeder(IntAic)));
                VecVisible.addElement(Static.JaNein2(g.isTod(IntAic)));
                VecVisible.addElement(Static.JaNein2(g.isWeb(IntAic)));
              }
              //VecVisible.addElement("bbb");
              JCOutlinerNode Nod = new JCOutlinerNode(VecVisible,NodLast);
              Nod.setUserData(TabE.getInhalt(sKennungE));
              g.setStyle(Nod,sKennungE.equalsIgnoreCase("AIC_Eigenschaft") && g.EigIsTod(IntAic) || sKennungE.equalsIgnoreCase("AIC_Begriff") && g.isTod(IntAic) ? g.ColLoeschen:ColNormal);
              /*{
                JCOutlinerNodeStyle NodeStyle2=new JCOutlinerNodeStyle(Sty);
                NodeStyle2.setForeground(g.ColLoeschen);
                Nod.setStyle(NodeStyle2);
              }
              else
                g.setStyle(Nod,ColNormal);*/
            }
          if (NodLast !=null)
                  ((Vector)NodLast.getLabel()).setElementAt(new Integer(NodLast.getChildren()==null ? 0:NodLast.getChildren().size()),iPosPos);
        }

        private void fillOutliner(int iGid,int riTab, int riBegriffgruppe)
        {
                int iPosT=g.TabTabellenname.getPos("AIC",new Integer(riTab));
                String sKennung=g.TabTabellenname.getS(iPosT,"Kennung");
                g.progInfo(iGid+":"+sKennung+"/"+CboTabellen.getSelectedKennung());
                String sTab=CboTabellen.getSelectedKennung();
                boolean bRolle=sTab.equals("ROLLE");
                boolean bBegriff=sKennung.equals("BEGRIFF");
                boolean bCode=sKennung.equals("CODE");
                int iAppl=0;
                int iModell=0;
                int iDruck=0;
                //int iAbfrage=0;
                if (bBegriff)
                {
                  iAppl = g.TabBegriffgruppen.getAic("Applikation");
                  iModell=g.TabBegriffgruppen.getAic("Modell");
                  iDruck=g.TabBegriffgruppen.getAic("Druck");
                  //iAbfrage=g.TabBegriffgruppen.getAic("Abfrage");
                  //g.progInfo("Appl="+iAppl);
                }
                //((JLabel)VecLbl.elementAt(rint)).setText(riBegriffgruppe==0 ? (String)g.TabTabellenname.getInhalt("Bezeichnung") : g.TabBegriffgruppen.getBezeichnung(riBegriffgruppe));
                String s=null;
                String sCB=sKennung.equalsIgnoreCase("Eigenschaft") && iGid==0 ? " aic_begriff="+g.getBegriffAicS("Datentyp","CalcBezeichnung"):null;
                boolean bKunde=sTab.equals("Kunde");

                //g.fixtestInfo(iGid+"/"+sTab+"->"+bKunde);
                if (bRolle)
                {
                  boolean bRolle0=CboRolle.getSelectedAIC()==0;
                  if (sKennung.equals("ROLLE"))
                    s="SELECT AIC_Rolle,Kennung"+g.AU_Bezeichnung2(sKennung)+" Bezeichnung,null Info,null Nod,null change2"+
                        " FROM Rolle where rol_aic_rolle"+(bRolle0 ? " is null and aic_stammtyp="+CboStt.getSelectedAIC():"="+CboRolle.getSelectedAIC());
                  else
                    s="SELECT distinct E.AIC_Eigenschaft,Kennung"+g.AU_Bezeichnung3(sKennung,"E")+",null Info,null Nod,null change2"+
                        " FROM Eigenschaft E"+Transact.join2((bRolle0?"Stammtyp":"Rolle")+"_Zuordnung","Z","E","Eigenschaft")+
                        " where Z.aic_"+(bRolle0?"stammtyp="+CboStt.getSelectedAIC():"rolle="+CboRolle.getSelectedAIC());
                }
                else
                 s="SELECT AIC_"+sKennung+",Kennung"+(bBegriff?",aic_begriffgruppe,DefBezeichnung":g.AU_Bezeichnung2(sKennung))+
                        " Bezeichnung,"+(bKunde ?"Ende":"null")+" Info,null Nod,null change2 FROM "+sKennung+(bBegriff || bCode ? " where aic_begriffgruppe"+(iGid==1 && (sTab.equals("Menge") || sTab.equals("Druck")) ?
                            " in("+riBegriffgruppe+","+iAppl+") or aic_begriffgruppe="+iModell+" and"+g.bit("bits",sTab.equals("Menge") ? Global.cstKnopf:Global.cstDruckZR)+
                            (sTab.equals("Menge") ? " or aic_begriffgruppe="+iDruck+" and"+g.bit("bits",Drucken.cstPntKnopf):""):
                            "="+riBegriffgruppe+(iGid==1 && sTab.equals("Programm") ? " and"+g.bit("bits",Abfrage.cstImportierbar):"")):"")+
                        (bKunde?" and (ende is null or ende>="+g.von()+")":"")+(sCB==null?"":" where"+sCB)+" order by bezeichnung,kennung";
                //g.fixtestInfo(s);
                Vector Vec2=null;
                if (sKennung.equalsIgnoreCase("Eigenschaft"))
                {
                  if (CboRolle.getSelectedAIC()>0)
                    Vec2=SQL.getVector("select aic_eigenschaft from rolle_zuordnung where aic_rolle="+CboRolle.getSelectedAIC(),g);
                  else if (CboStt.getSelectedAIC()>0)
                    Vec2=SQL.getVector("select aic_eigenschaft from stammtyp_zuordnung where aic_stammtyp="+CboStt.getSelectedAIC(),g);
                }
                Tabellenspeicher Tab=new Tabellenspeicher(g,s,true);
                if (iGid==0)
                {
                  TabG = Tab;
                  sKennungG="AIC_"+sKennung;
                  sKennungGZ=sKennungE != null && sKennungE.equals(sKennungG)? sKennungG.substring(4,7)+"_"+sKennungG:sKennungG;
                }
                else
                {
                  TabE = Tab;
                  sKennungE="AIC_"+sKennung;
                  sKennungEZ=sKennungG != null && sKennungE.equals(sKennungG)? sKennungE.substring(4,7)+"_"+sKennungE:sKennungE;
                }
                Tab.checkBezeichnung();
                Tab.sAIC="AIC_"+sKennung;

                //if(g.Prog())
                //	TabGrpEig[rint].showGrid("TabGrpEig");
                JCOutlinerNode NodRoot=(iGid==0 ? OutGruppen:OutEigenschaft).getRootNode();
                JCOutlinerNodeStyle Sty = riBegriffgruppe>0 ? g.getStyle(g.getImageBG(riBegriffgruppe)):g.getTabStyle(riTab);
                //Sty.setItemIcon(null);
                //g.progInfo(rint+".:"+saryKennung[rint]);
                //if (iGid==1)
                //  TabAll=new Tabellenspeicher(g,new String[]{"Data","Node"});
                for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  if (Vec2==null || Vec2.contains(Tab.getInhalt("AIC_"+sKennung)))
                {
                        Vector<Comparable> VecVisible = new Vector<Comparable>();

                        VecVisible.addElement(Tab.getS("Bezeichnung"));
                        VecVisible.addElement(Tab.getS("Kennung"));
                        int iAnzahl=0;
                        if(sKennung.equalsIgnoreCase("Eigenschaft"))
                        {
                                int iPosE=g.TabEigenschaften.getPos("AIC",Tab.getI("AIC_"+sKennung));
                                String sDatentyp=g.TabEigenschaften.getS(iPosE,"Datentyp");
                                VecVisible.addElement(sDatentyp);
                                Tab.setInhalt("Info",sDatentyp);
                                int iPos=iPosE<0?-1:g.getPosBegriff("Datentyp",g.TabEigenschaften.getS(iPosE,"Datentyp"));
                                Image Gif = iPos<0 ? null:g.LoadImage(g.TabBegriffe.getS(iPos,"BildFile"));
                                Sty=g.getStyle(Gif);
                        }
                        else if(sKennung.equalsIgnoreCase("Begriff"))
                        {
                                int iPosB=g.TabBegriffe.getPos("AIC",Tab.getI("AIC_"+sKennung));
                                iAnzahl=g.getAnzahl(Tab.getI("AIC_Begriff"));
                                if (bKunde)
                                  VecVisible.addElement(Tab.isNull("Info") ? null : new Zeit(Tab.getTimestamp("Info"), "dd.MM.yyyy"));
                                else if (Tab.getI("aic_begriffgruppe")==iAppl)
                                {
                                  String sHS="* Hauptschirm *";
                                  VecVisible.addElement(sHS);
                                  Tab.setInhalt("Info",sHS);
                                }
                                else if (Tab.getI("aic_begriffgruppe")==iModell)
                                {
                                  String sHS="* Modell *";
                                  VecVisible.addElement(sHS);
                                  Tab.setInhalt("Info",sHS);
                                }
                                else if (Tab.getI("aic_begriffgruppe")==iDruck)
                                {
                                  String sHS=iGid==1 ? "* Druck *":g.getSttBez(iPosB);
                                  VecVisible.addElement(sHS);
                                  Tab.setInhalt("Info",sHS);
                                }
                                else if (g.TabBegriffe.getI(iPosB,"Stt")>0 || g.TabBegriffe.getI(iPosB,"Erf")>0)
                                {
                                  //int iPosS=g.TabStammtypen.getPos("AIC", g.TabBegriffe.getI(iPosB,"Stt"));
                                  String sStt=g.getSttBez(iPosB);//g.TabStammtypen.getS(iPosS,"Bezeichnung");*/
                                  VecVisible.addElement(sStt);
                                  Tab.setInhalt("Info",sStt);

                                  //Image Gif = (Image)g.TabStammtypen.getInhalt("Bild");
                                  Sty = g.getStyle(g.getSttGif(g.TabBegriffe.getI(iPosB,"Stt")));
                                }
                                else
                                  VecVisible.addElement(null);
                        }
                        else
                                VecVisible.addElement(null);
                        VecVisible.addElement(new Integer(Tab.getI("AIC_"+sKennung)));
                        if (iGid==0)
                        {
                          //VecVisible.addElement(null);
                          VecVisible.addElement(iAnzahl<1 ? null:new Integer(iAnzahl));
                          //VecVisible.addElement(null);
                        }
                        Object IntAic=Tab.getInhalt("AIC_"+sKennung);
                        if(sKennung.equalsIgnoreCase("Eigenschaft"))
                        {
                          VecVisible.addElement(Static.JaNein2(g.isJederEig(IntAic)));
                          VecVisible.addElement(Static.JaNein2(g.EigIsTod(IntAic)));
                        }
                        else if(sKennung.equalsIgnoreCase("Begriff"))
                        {
                          VecVisible.addElement(Static.JaNein2(g.isJeder(IntAic)));
                          VecVisible.addElement(Static.JaNein2(g.isTod(IntAic)));
                          VecVisible.addElement(Static.JaNein2(g.isWeb(IntAic)));
                        }
                        //VecVisible.addElement("aaa");
                        if(bKunde || iGid==0 || CheckAllowed(sTab,(String)VecVisible.elementAt(2)))
                        {
                                JCOutlinerFolderNode Nod = new JCOutlinerFolderNode(VecVisible,(JCOutlinerFolderNode)NodRoot);
                                Nod.setState(BWTEnum.FOLDER_CLOSED);
                                Nod.setUserData(IntAic);
                                if (sKennung.equalsIgnoreCase("Eigenschaft") && g.EigIsTod(IntAic) || sKennung.equalsIgnoreCase("Begriff") && g.isTod(IntAic))
                                {
                                  JCOutlinerNodeStyle NodeStyle2=new JCOutlinerNodeStyle(Sty);
                                  NodeStyle2.setForeground(g.ColLoeschen);
                                  Nod.setStyle(NodeStyle2);
                                }
                                else
                                  Nod.setStyle(Sty);
                                Tab.setInhalt("Nod",Nod);
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

        private void EnableButtons()
        {
          if (!bReady)
            return;
                JCOutlinerNode Node = OutGruppen.getSelectedNode();
                JCOutlinerNode NodeEig = OutEigenschaft.getSelectedNode();
                boolean bLevel2=Node!=null && Node.getLevel()==2;
                iLastAic=Node==null ? 0:Sort.geti((bLevel2?Node.getParent():Node).getUserData());
                BtnPfeilrechts.setEnabled(Node!=null && (Node.getLevel()>0 && bReihenfolge || Node.getLevel()==1) && NodeEig!=null && NodeEig.getLevel()>0);
                BtnErsetzen.setEnabled(Node!=null && Node.getLevel()==2 && NodeEig!=null && NodeEig.getLevel()>0);
                BtnPfeillinks.setEnabled(bLevel2);
                BtnPfeiloben.setEnabled(bReihenfolge && bLevel2);
                BtnSeperator.setEnabled(bReihenfolge && Node!=null && Node.getLevel()>0);
                BtnPfeilunten.setEnabled(bReihenfolge && bLevel2);
                if (BtnDel != null) BtnDel.setEnabled(NodeEig!=null && NodeEig.getLevel()>0);
                if (BtnCopy != null) BtnCopy.setEnabled(NodeEig!=null && NodeEig.getLevel()>0 && !bReihenfolge && Node!=null && Node.getLevel()>1);
                //String sKennung=CboTabellen.getSelectedKennung();
                //boolean bSeperator=sKennung.equalsIgnoreCase("BEGRIFF") || sKennung.equalsIgnoreCase("EIGENSCHAFT");
                BtnSpeichern.setEnabled(CboTabellen.getSelectedAIC()>0 && !CboTabellen.getSelectedKennung().equals("BENUTZER"));
                //if (BtnSpeichern != null)
                //  BtnSpeichern.setEnabled(!bReihenfolge || iGedreht<1);

        }

        private boolean checkSave()
        {
          boolean bSave=false;
          if (TabG!=null)
           for(TabG.moveFirst();!TabG.eof() && !bSave;TabG.moveNext())
            bSave=TabG.getB("change2");
          return bSave;
        }

        private void DelZ2()
        {
          JCOutlinerNode NodeE = OutEigenschaft.getSelectedNode();
          if (new Message(Message.YES_NO_OPTION+Message.SHOW_TAB, (JFrame)thisFrame, g).showDialog("DelZ2", new String[]{Sort.gets(NodeE.getLabel(),0)},getTab(OutEigenschaft)) == Message.YES_OPTION)
          {
            int iEig=Sort.geti(NodeE.getUserData());
            g.fixtestInfo("Lösche " + iEig);
            int iAnz=g.exec("delete from "+sTab+" where "+sKennungEZ+"="+iEig+(iBG>0 && iBG2>0 ?" and (select aic_begriffgruppe from begriff where "+sTab+".aic_begriff=begriff.aic_begriff)="+iBG:""));
            //Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from "+sTab+" where "+sKennungEZ+"="+iEig+
            // Tab.showGrid();
            g.fixtestInfo(iAnz+" von Tab "+sTab+" gelöscht");
            Refresh(false);
          }
        }

        private void CopyZ2()
        {
          JCOutlinerNode NodeE = OutEigenschaft.getSelectedNode();
          JCOutlinerNode NodeG = OutGruppen.getSelectedNode();
          if (new Message(Message.YES_NO_OPTION+Message.SHOW_TAB, (JFrame)thisFrame, g).showDialog("CopyZ2", new String[]{Sort.gets(NodeG.getLabel(),0),Sort.gets(NodeE.getLabel(),0)},getTab(OutGruppen)) == Message.YES_OPTION)
          {
            int iEig1=Sort.geti(NodeG.getUserData());
            int iEig2=Sort.geti(NodeE.getUserData());
            g.fixtestInfo("Kopiere " + iEig1 + " nach " + iEig2);
            String sBed2=iBG>0 && iBG2>0 ?" and (select aic_begriffgruppe from begriff where "+sTab+".aic_begriff=begriff.aic_begriff)="+iBG:"";
            int iAnz=g.exec("delete from "+sTab+" where "+sKennungEZ+"="+iEig2+sBed2);
            Vector<Integer> Vec=SQL.getVector("select "+sKennungGZ+" from "+sTab+" where "+sKennungEZ+"="+iEig1+sBed2,g);
            SQL Qry=new SQL(g);
            for (int i=0;i<Vec.size();i++)
            {
              Qry.add(sKennungGZ,Sort.geti(Vec,i));
              Qry.add(sKennungEZ,iEig2);
              Qry.insert(sTab,false);
            }
            Qry.free();
            g.fixtestInfo(iAnz+"->"+Vec.size()+" von Tab "+sTab+" kopiert");
            Refresh(false);
          }
        }

        private void Speichern()
        {
          SQL Qry=new SQL(g);
          for(TabG.moveFirst();!TabG.eof();TabG.moveNext())
            if (TabG.getB("change2"))
            {
              g.progInfo("Save "+TabG.getS("Nod"));
              Vector VecNodes = ((JCOutlinerFolderNode)TabG.getInhalt("Nod")).getChildren();
              Gauge gau=null;
              int iAnz=VecNodes.size();
              if (iAnz>20)
            	  gau=new Gauge(0,iAnz,"lösche in "+sTab,g,false);
              g.start();
              Qry.exec("delete from "+sTab+" where "+sKennungGZ+"="+TabG.getI(sKennungG)+
                  (iBG2>0 && !sKennungGZ.equals("AIC_CODE")?" and (select aic_begriff from begriff where "+sTab+".aic_begriff=begriff.aic_begriff and aic_begriffgruppe="+iBG+") is not null":""));            
              for(int i=0;i<iAnz;i++)
              {
            	JCOutlinerNode Nod=  (JCOutlinerNode)VecNodes.elementAt(i);
                int iAic=((Integer)Nod.getUserData()).intValue();
                if(iAic>0)
                {
                  if (gau !=null)
                  	gau.setText(Sort.gets(Nod.getLabel()),i);
                  Qry.add(sKennungGZ,TabG.getI(sKennungG));
                  Qry.add(sKennungEZ,iAic);
                  if (bReihenfolge)
                    Qry.add("Reihenfolge",((Integer)((Vector)Nod.getLabel()).elementAt(iPosPos)).intValue());
                  Qry.insert(sTab,false);  
                  //Static.sleep(100); // !!! nur zum testen
                }
              }
              g.commit();
              if (gau!=null)
            	  gau.free();
              g.SaveDefVerlauf(getBegriff(),sTab+" bei "+sKennungGZ+"="+TabG.getI(sKennungG)+" geändert");
            }
          Qry.free();
          Refresh(false);
        }

        @SuppressWarnings("unchecked")
        private void checkReihenfolge(JCOutlinerFolderNode Nod)
        {
          if (bReihenfolge)
          {
            Vector VecNodes = Nod.getChildren();
            ((Vector)Nod.getLabel()).setElementAt(new Integer(VecNodes==null ? 0:VecNodes.size()),iPosPos);
            for(int i = 0; i < VecNodes.size(); i++)
            {
              Vector<Integer> Vec=(Vector)((JCOutlinerNode)VecNodes.elementAt(i)).getLabel();
              if (Vec.size()<5)
              {
                Vec.addElement(new Integer(i + 1));
                setColorIf((JCOutlinerNode)VecNodes.elementAt(i),ColPos);
              }
              else if (!new Integer(i+1).equals(Vec.elementAt(4)))
              {
                Vec.setElementAt(new Integer(i + 1), 4);
                setColorIf((JCOutlinerNode)VecNodes.elementAt(i),ColPos);
              }
            }
          }
        }

        private boolean dazu(Vector VecChildren,Object Obj)
        {
          boolean b=true;
          if (VecChildren != null)
            for (int i=0;b && i<VecChildren.size();i++)
              b=!Obj.equals(((JCOutlinerNode)VecChildren.elementAt(i)).getUserData());
          return b;
        }

        @SuppressWarnings("unchecked")
        private void Hinzufuegen()
        {
          JCOutlinerNode[] NodesGruppen = OutGruppen.getSelectedNodes();
          JCOutlinerNode[] NodesEigenschaft = OutEigenschaft.getSelectedNodes();
          if(NodesGruppen[0].getLevel()==1)
          {
            for (int i = 0; i < NodesGruppen.length; i++)
            {
              Vector VecChildren=NodesGruppen[i].getChildren();
              ((JCOutlinerFolderNode)NodesGruppen[i]).setState(BWTEnum.FOLDER_OPEN_ALL);
              change(NodesGruppen[i]);
              for (int j = 0; j < NodesEigenschaft.length; j++)
                if(dazu(VecChildren,NodesEigenschaft[j].getUserData()))
                {
                  Vector Vec = new Vector((Vector)NodesEigenschaft[j].getLabel());
                  JCOutlinerNode Nod = new JCOutlinerNode(Vec, (JCOutlinerFolderNode)NodesGruppen[i]);
                  Nod.setUserData(NodesEigenschaft[j].getUserData());
                  g.setStyle(Nod, ColHinzu);
                }
              checkReihenfolge((JCOutlinerFolderNode)NodesGruppen[i]);
            }
          }
          else
          {
            Vector VecChildren=NodesGruppen[0].getParent().getChildren();
            Integer IntPos=(Integer)((Vector)NodesGruppen[0].getLabel()).elementAt(iPosPos);
            for (int j = NodesEigenschaft.length-1; j >= 0; j--)
              if(dazu(VecChildren,NodesEigenschaft[j].getUserData()))
              {
                Vector Vec = new Vector((Vector)NodesEigenschaft[j].getLabel());
                JCOutlinerNode Nod = new JCOutlinerNode(Vec, NodesGruppen[0].getParent());
                Nod.setUserData(NodesEigenschaft[j].getUserData());
                g.setStyle(Nod, ColHinzu);
                VecChildren.insertElementAt(Nod,IntPos.intValue()-1);
              }
            VecChildren.setSize(VecChildren.size()-NodesEigenschaft.length);
            JCOutlinerFolderNode NodP=NodesGruppen[0].getParent();
            change(NodP);
            checkReihenfolge(NodP);
          }
          OutGruppen.folderChanged(OutGruppen.getRootNode());
        }

        @SuppressWarnings("unchecked")
        private void Ersetzen()
        {
          JCOutlinerNode NodeZ = OutGruppen.getSelectedNode();
          JCOutlinerNode NodeE = OutEigenschaft.getSelectedNode();
          NodeZ.setLabel(new Vector((Vector)NodeE.getLabel()));
          NodeZ.setUserData(NodeE.getUserData());
          change(NodeZ.getParent());
          setColor(NodeZ,ColHinzu);
          checkReihenfolge(NodeZ.getParent());
          OutGruppen.folderChanged(NodeZ.getParent());
        }

        private void Loeschen()
        {
          JCOutlinerNode[] NodesZuordnung = OutGruppen.getSelectedNodes();
          OutGruppen.selectNode(OutGruppen.getPreviousNode(NodesZuordnung[0]),null);
          for(int i=0;i<NodesZuordnung.length;i++)
            NodesZuordnung[i].getParent().removeChild(NodesZuordnung[i]);
          change(NodesZuordnung[0].getParent());
          checkReihenfolge(NodesZuordnung[0].getParent());
          OutGruppen.folderChanged(OutGruppen.getRootNode());
        }

        @SuppressWarnings("unchecked")
		private void Verschieben(boolean bUp)
        {
          JCOutlinerNode[] NodSelected = OutGruppen.getSelectedNodes();

                for(int i=0;NodSelected.length>i;i++)
                {
                        Vector<JCOutlinerNode> VecNodes = NodSelected[i].getParent().getChildren();
                        int iPos=VecNodes.indexOf(NodSelected[i]);
                        if(iPos+(bUp?-1:1)>=0 && iPos+(bUp?-1:1)<VecNodes.size())
                        {
                                JCOutlinerNode NodAndere = VecNodes.elementAt(iPos+(bUp?-1:1));

                                VecNodes.setElementAt(NodAndere,iPos);
                                VecNodes.setElementAt(NodSelected[i],iPos+(bUp?-1:1));

                                /*((Vector)NodSelected[i].getLabel()).setElementAt(iPosPos,new Integer(iPos+(bUp?-1:1)+1));
                                ((Vector)NodAndere.getLabel()).setElementAt(iPosPos,new Integer(iPos+1));
                                setColorIf(NodAndere,ColPos);
                                setColorIf(NodSelected[i],ColPos);*/
                        }
                }

                change(NodSelected[0].getParent());
                checkReihenfolge(NodSelected[0].getParent());
                OutGruppen.folderChanged(OutGruppen.getRootNode());
        }

        private void change(JCOutlinerNode Nod)
        {
          if (TabG.posInhalt("Nod",Nod))
            TabG.setInhalt("change2",Boolean.TRUE);
        }

        private void setColor(JCOutlinerNode Nod,Color Col)
        {
          JCOutlinerNodeStyle Sty = Nod.getStyle();
          Sty.setForeground(Col);
        }

        private void setColorIf(JCOutlinerNode Nod,Color Col)
        {
          JCOutlinerNodeStyle Sty = Nod.getStyle();
          if (ColNormal.equals(Sty.getForeground()))
            Sty.setForeground(Col);
        }

        /*private void setStyle(JCOutlinerNode Nod,Color Col)
        {
          JCOutlinerNodeStyle Sty = new JCOutlinerNodeStyle();
          Sty.setItemIcon(null);
          Sty.setForeground(Col);
          Nod.setStyle(Sty);
        }*/

        @SuppressWarnings("unchecked")
        private void Seperator_Hinzufuegen()
        {
          JCOutlinerNode[] NodesZuordnung = OutGruppen.getSelectedNodes();

          for(int i=0;i<NodesZuordnung.length;i++)
          {
                  Vector<Comparable> VecVisible = new Vector<Comparable>();
                  VecVisible.addElement("-------------");
                  VecVisible.addElement("-------------");
                  VecVisible.addElement(null);
                  VecVisible.addElement(null);

                  if(NodesZuordnung[i].getLevel()==1)
                  {
                          VecVisible.addElement(new Integer(((JCOutlinerFolderNode)NodesZuordnung[i]).getChildren().size()+1));
                          JCOutlinerNode NodSeperator = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)NodesZuordnung[i]);
                          NodSeperator.setUserData(Static.Int0);
                          ((JCOutlinerFolderNode)NodesZuordnung[i]).setState(BWTEnum.FOLDER_OPEN_ALL);
                          g.setStyle(NodSeperator,ColHinzu);
                          change(NodesZuordnung[i]);
                  }
                  else
                  {
                    Vector<JCOutlinerNode> VecChildren=NodesZuordnung[i].getParent().getChildren();
                    //Object Obj=((Vector)((JCOutlinerNode)NodesZuordnung[i]).getLabel()).elementAt(iPosPos);
                    //g.progInfo("Obj="+Obj+"/"+Static.className(Obj));
                    Integer IntPos=(Integer)((Vector)NodesZuordnung[i].getLabel()).elementAt(iPosPos);
                    VecVisible.addElement(IntPos);
                    JCOutlinerNode NodSeperator = new JCOutlinerNode(VecVisible,NodesZuordnung[i].getParent());
                    NodSeperator.setUserData(Static.Int0);
                    g.setStyle(NodSeperator,ColHinzu);
                    VecChildren.insertElementAt(NodSeperator,IntPos.intValue()-1);
                    for(int iPos=IntPos.intValue();iPos<VecChildren.size();iPos++)
                    {
                      JCOutlinerNode Nod=VecChildren.elementAt(iPos-1);
                            ((Vector)Nod.getLabel()).setElementAt(new Integer(iPos),iPosPos);
                            setColorIf(Nod,ColPos);
                            //((Vector)((JCOutlinerNode)VecChildren.elementAt(iPos-1)).getUserData()).setElementAt(new Integer(iPos),2);
                    }
                    VecChildren.setSize(VecChildren.size()-1);
                    //NodesZuordnung[i].getParent().setChildren(VecChildren);
                    change(NodesZuordnung[i].getParent());
                    i=NodesZuordnung.length;
                  }
          }
          OutGruppen.folderChanged(OutGruppen.getRootNode());
          //TabG.showGrid("Gruppen");
        }


}
