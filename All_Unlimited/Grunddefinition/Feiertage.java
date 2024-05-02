package All_Unlimited.Grunddefinition;

import All_Unlimited.Allgemein.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import java.util.Vector;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;

import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
import javax.swing.JDialog;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Allgemein.Eingabe.SpinBoxJahr;
import All_Unlimited.Hauptmaske.AClient;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.10
 */
public class Feiertage extends Formular
{
  private static Feiertage Self=null;
  private Global g;
  private AUOutliner OutStamm = new AUOutliner(new JCOutlinerFolderNode(""));
  private AUOutliner OutFeiertag = new AUOutliner(new JCOutlinerFolderNode(""));
  private AUOutliner OutZ = new AUOutliner(new JCOutlinerFolderNode(""));
  private Tabellenspeicher TabChange;
  private JPopupMenu popupFt;
  private JPopupMenu popupL;
  private JButton BtnSave;
  private JButton BtnHinzu;
  private JButton BtnErsetzen;
  private JButton BtnWeg;
  private JDialog FrmEditF;
  private JDialog FrmEditL;
  private Text TxtBezeichnungF=new Text("",60);
  private Text TxtKennungF=new Text("",20,Text.KENNUNG);
  private SpinBox EdtTag;
  private ComboSort CboMonat;
  private static SpinBoxJahr EdtVon;
  private static SpinBoxJahr EdtBis;
  private AUTextArea TxtMemoF=new AUTextArea();
  private Text TxtBezeichnungL=new Text("",60);
  private Text TxtKennungL=new Text("",20,Text.KENNUNG);
  private Text TxtISO3166=new Text("",2);
  private ComboSort CboLand;

  private AUTextArea TxtMemoL=new AUTextArea();
  private int iFT=0;
  private int iL=0;
  private boolean bFill=false;

  public static Feiertage get(Global rg)
  {
    return Self == null ? new Feiertage(rg) : Self;
  }

  public void show(Window w)
  {
    if (w != null)
      Static.centerComponent(thisFrame, w);
    Refresh();
    super.show();
  }

  public static void free()
  {
    if(Self != null) {
      Self.g.winInfo("Feiertage.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

  private Feiertage(Global glob)
  {
    super("Feiertag", null, glob);
    g = glob;
    Self = this;
    g.winInfo("Feiertage.create");
    checkFT(g);
    Build();
  }
  
  
  public static void checkFT(Global g)
  {
  	int iBG=SQL.getInteger(g, "select aic_begriffgruppe from begriffgruppe where kennung='Berechnete Feiertage'");
  	int iAic=SQL.getInteger(g,"select aic_code from code where aic_begriffgruppe="+iBG+" and kennung='Ost-Ostern'");
  	if (iAic==0)
  		g.exec("insert into code (aic_begriffgruppe,kennung) values ("+iBG+",'Ost-Ostern')");
  }

  private void Build()
  {
    //g.fixInfo("Build Feiertage");
    TabChange=new Tabellenspeicher(g,new String[] {"Feiertag","Land","Stamm","Knoten","Art"});
    ActionListener AL=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s = ev.getActionCommand();
        g.progInfo("Action="+s);
        if (s.equals("Refresh"))
          Refresh();
        else if (s.equals("Speichern"))
          Speichern();
        else if (s.equals("Beenden"))
        {
          if(TabChange.size()>0)
          {
            int pane = new Message(Message.YES_NO_OPTION, thisJFrame(), g).showDialog("Speichern", new String[] {"die Änderung"});
            if(pane == Message.YES_OPTION)
              Speichern();
          }
          hide();
        }
        else if (s.equals("hinzu"))
          hinzu();
        else if (s.equals("weg"))
          weg();
        else if (s.equals("Ersetzen"))
        {
          weg();
          hinzu();
        }
        else if (s.equals("NeuF"))
          EditF(true,false);
        else if (s.equals("CopyF"))
          EditF(true,true);
        else if (s.equals("EditF"))
          EditF(false,false);
        else if (s.equals("LoeschenF"))
          LoeschenF();
        else if (s.equals("showF"))
          Vorhanden(true).showGrid("Feiertage");
        else if (s.equals("showChange"))
          TabChange.showGrid();
        else if (s.equals("EditF_Ok"))
          EditF_Ok();
        else if (s.equals("EditF_Abbruch"))
          FrmEditF.setVisible(false);
        else if (s.equals("NeuL"))
          EditL(true);
        else if (s.equals("EditL"))
          EditL(false);
        else if (s.equals("LoeschenL"))
          LoeschenL();
        else if (s.equals("EditL_Ok"))
          EditL_Ok();
        else if (s.equals("EditL_Abbruch"))
          FrmEditL.setVisible(false);
        else if (s.equals("reset"))
        	Prozesse.checkDeaktiv(g,true,false,thisJFrame());
//          AClient.send_AServer("reset",g);
        else if (s.equals("status"))
          g.fixInfo("Status="+AClient.get_AServer("status"));
        else if (s.equals("sendFT"))
          g.sendWebChanged("holidays");
        else if (s.equals("AktJahr"))
        	setAktJahr();
        EnableButtons();
      }
    };
    getButton("Refresh","Refresh",AL);
    BtnSave=getButton("Speichern","Speichern",AL);
    getButton("Beenden","Beenden",AL);

    getButton("Neu","NeuF",AL);
    getButton("copy","CopyF",AL);
    getButton("Edit","EditF",AL);
    getButton("Loeschen","LoeschenF",AL);
    getButton("show","showF",AL);
    getButton("reset","reset",AL);
    getButton("status","status",AL);
    getButton("sendFT","sendFT",AL);

    BtnHinzu=getButton(">","hinzu",AL);
    BtnErsetzen=getButton("Ersetzen","Ersetzen",AL);
    BtnWeg=getButton("<","weg",AL);

    // --- PopupMenu ---
    popupFt= new JPopupMenu();
    g.addMenuItem("Neu",popupFt,"NeuF").addActionListener(AL);
    g.addMenuItem("Edit",popupFt,"EditF").addActionListener(AL);
    g.addMenuItem("copy",popupFt,"CopyF").addActionListener(AL);
    g.addMenuItem("Loeschen",popupFt,"LoeschenF").addActionListener(AL);
    g.addMenuItem("show",popupFt,"showF").addActionListener(AL);
    if (g.Def())
      g.addMenuItem("showChange",popupFt,"showChange").addActionListener(AL);

    popupL= new JPopupMenu();
        g.addMenuItem("Neu",popupL,"NeuL").addActionListener(AL);
        g.addMenuItem("Edit",popupL,"EditL").addActionListener(AL);
        g.addMenuItem("Loeschen",popupL,"LoeschenL").addActionListener(AL);
    //g.addMenuItem("show",popupL,"showL").addActionListener(AL);
    // --- Outliner ---
    JPanel Pnl_Stamm = getFrei("Outliner Stamm");
    Pnl_Stamm.add("Center",OutStamm);
    JPanel Pnl_Feiertag = getFrei("Outliner Feiertag");
    Pnl_Feiertag.add("Center",OutFeiertag);
    JPanel Pnl_Zuord = getFrei("Outliner");
    Pnl_Zuord.add("Center",OutZ);
    OutStamm.setBackground(Color.white);
    OutStamm.setRootVisible(false);
    OutFeiertag.setBackground(Color.white);
    OutFeiertag.setRootVisible(false);
    String[] s = new String[] {g.getShow("Feiertag"),g.getShow("Info"),g.getShow("Datum"),g.getShow("Kennung"),g.getShow("Aic")};
    OutFeiertag.setColumnButtons(s);
    OutFeiertag.setNumColumns(s.length);
    OutFeiertag.setAllowMultipleSelections(true);
    OutZ.setBackground(Color.white);
    OutZ.setRootVisible(false);
    s = new String[] {g.getShow("Bezeichnung"),g.getShow("Stamm"),g.getShow("Datum"),g.getShow("Mandant")};
    OutZ.setColumnButtons(s);
    OutZ.setNumColumns(s.length);
    OutZ.setAllowMultipleSelections(true);
    OutFeiertag.getOutliner().addMouseListener(new MouseListener()
    {
      public void mousePressed(MouseEvent ev) {}
      public void mouseClicked(MouseEvent ev)
      {
          if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
            popupFt.show((Component)ev.getSource(), ev.getX(), ev.getY());
      }
      public void mouseEntered(MouseEvent ev) {}
      public void mouseExited(MouseEvent ev)  {}
      public void mouseReleased(MouseEvent ev){}
    });
    OutZ.getOutliner().addMouseListener(new MouseListener()
    {
      public void mousePressed(MouseEvent ev) {}
      public void mouseClicked(MouseEvent ev)
      {
          if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
          {
            int iLevel=OutZ.getSelectedNode()==null ? -1:OutZ.getSelectedNode().getLevel();
            if (iLevel==1)
              popupL.show((Component)ev.getSource(), ev.getX(), ev.getY());
          }
      }
      public void mouseEntered(MouseEvent ev) {}
      public void mouseExited(MouseEvent ev)  {}
      public void mouseReleased(MouseEvent ev){}
    });
    JCOutlinerListener OL=new JCOutlinerListener ()
    {
            public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
            {
              if (!bFill)
                EnableButtons();
            }
            public void outlinerNodeSelectBegin(JCOutlinerEvent ev) {}
            public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev) {}
            public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev) {}
            public void itemStateChanged(JCItemEvent ev) {}
    };
    OutFeiertag.addItemListener(OL);
    OutZ.addItemListener(OL);

    // Dialog für Edit Feiertag
    EdtTag=new SpinBox(0,-2,60,1,Color.WHITE);
    CboMonat=new ComboSort(g,ComboSort.Aic);
    CboMonat.fillBegriffTable(new String[]{"Monat","Berechnete Feiertage"},false);
    EdtVon=new SpinBoxJahr(g.ColBackground,1900);
    EdtBis=new SpinBoxJahr(g.ColBackground,2000);
    JButton BtnAkt=g.BtnAdd(new JButton("   -   "),"AktJahr",AL);
    FrmEditF=new JDialog((JFrame)thisFrame,g.getBegriffS("Dialog","Feiertag"),true);
        FrmEditF.getContentPane().setLayout(new BorderLayout(2,2));
        JPanel Pnl=new JPanel(new BorderLayout(2,2));
        JPanel Pnl1=new JPanel(new GridLayout(0,1,2,2));
        g.addLabel2(Pnl1,"Bezeichnung");
        g.addLabel2(Pnl1,"Kennung");
        g.addLabel2(Pnl1,"Datum");
        g.addLabel2(Pnl1,"Jahr");
        Pnl.add("West",Pnl1);
        Pnl1=new JPanel(new GridLayout(0,1,2,2));
        Pnl1.add(TxtBezeichnungF);
        Pnl1.add(TxtKennungF);
         JPanel PnlSub=new JPanel(new GridLayout(1,0,2,2));
         PnlSub.add(EdtTag);
         PnlSub.add(new JLabel(" . "));
         PnlSub.add(CboMonat);
        Pnl1.add(PnlSub);
         PnlSub=new JPanel(new GridLayout(1,0,2,2));
         PnlSub.add(EdtVon);
         PnlSub.add(BtnAkt/*new JLabel("   -   ",SwingConstants.CENTER)*/);
         PnlSub.add(EdtBis);
        Pnl1.add(PnlSub);
        TxtMemoF.setPreferredSize(new Dimension(100,50));
        Pnl.add("South",TxtMemoF);
        Pnl.add("Center",Pnl1);
        FrmEditF.getContentPane().add("Center",Pnl);
        Pnl=new JPanel(new GridLayout(1,0,2,2));
        Pnl.add(g.BtnAdd(g.getButton("Ok"),"EditF_Ok",AL));
        Pnl.add(g.BtnAdd(g.getButton("Abbruch"),"EditF_Abbruch",AL));
        FrmEditF.getContentPane().add("South",Pnl);
        FrmEditF.setSize(330,200);

    // Dialog für Edit Land
    FrmEditL=new JDialog((JFrame)thisFrame,g.getBegriffS("Dialog","Land"),true);
    FrmEditL.getContentPane().setLayout(new BorderLayout(2,2));
      Pnl=new JPanel(new BorderLayout(2,2));
      Pnl1=new JPanel(new GridLayout(0,1,2,2));
        g.addLabel2(Pnl1,"Bezeichnung");
        g.addLabel2(Pnl1,"Kennung");
        g.addLabel2(Pnl1,"ISO3166");
        g.addLabel2(Pnl1,"Land");
      Pnl.add("West",Pnl1);
      Pnl1=new JPanel(new GridLayout(0,1,2,2));
        Pnl1.add(TxtBezeichnungL);
        Pnl1.add(TxtKennungL);
        Pnl1.add(TxtISO3166);
        CboLand=new ComboSort(g);
//        CboLand.fillStammTable(g.TabStammtypen.getAic("LAND"),true);
        Pnl1.add(CboLand);
      Pnl.add("Center",Pnl1);
      TxtMemoL.setPreferredSize(new Dimension(100,50));
      Pnl.add("South",TxtMemoL);
    FrmEditL.getContentPane().add("Center",Pnl);
      Pnl=new JPanel(new GridLayout(1,0,2,2));
        Pnl.add(g.BtnAdd(g.getButton("Ok"),"EditL_Ok",AL));
        Pnl.add(g.BtnAdd(g.getButton("Abbruch"),"EditL_Abbruch",AL));
    FrmEditL.getContentPane().add("South",Pnl);
    FrmEditL.setSize(330,200);
  }
  
  private void setAktJahr()
  {
	  int iJahr=new DateWOD(g.getVon()).get(DateWOD.YEAR);
	  EdtVon.setIntValue(iJahr);
	  EdtBis.setIntValue(iJahr);
  }

  private void Refresh()
  {
    bFill=true;
    g.progInfo("Refresh Feiertage:"+iFT+"/"+iL);
    int iRel=g.TabStammtypen.getAic("RELIGION");
    int iBL=g.TabStammtypen.getAic("REGION");
    //g.fixInfo(iRel+"/"+iBL);
    ((JCOutlinerFolderNode)OutStamm.getRootNode()).removeChildren();
    ((JCOutlinerFolderNode)OutFeiertag.getRootNode()).removeChildren();
    ((JCOutlinerFolderNode)OutZ.getRootNode()).removeChildren();
    JCOutlinerNodeStyle Sty=new JCOutlinerNodeStyle();//g.getNullStyle();
    JCOutlinerNodeStyle StyFolder=g.getStyle(null);
    // füllt Religionen
    if (iRel>0)
    {
      Tabellenspeicher TabRel=new Tabellenspeicher(g,"select aic_stamm,bezeichnung,"+g.AU_Bezeichnungo("Stamm","stammview")+" Bez2 from stammview where aic_stammtyp="+iRel+" order by bezeichnung",true);
      JCOutlinerFolderNode NodRel = new JCOutlinerFolderNode(g.TabStammtypen.getBezeichnungS(iRel), (JCOutlinerFolderNode)OutStamm.getRootNode());
      NodRel.setStyle(StyFolder);
      for (TabRel.moveFirst();!TabRel.out();TabRel.moveNext())
      {
        JCOutlinerFolderNode Node = new JCOutlinerFolderNode(Static.beiLeer(TabRel.getS("Bez2"),TabRel.getS("Bezeichnung")), NodRel);
        Node.setStyle(g.getSttStyle(iRel));
        Node.setUserData(TabRel.getI("aic_stamm"));
      }
    }
    // füllt Bundesländer
    if (iBL>0)
    {
      int iPos=g.TabRollen.getPos("Kennung","BUNDESLAND_REGION");
      //int iRolle=SQL.getInteger(g,"select aic_rolle from rolle where kennung='Bundesland_Region'");
      if (iPos>=0)
      {
        int iRolle=g.TabRollen.getI(iPos,"Aic");
        Tabellenspeicher TabBL = new Tabellenspeicher(g, "select aic_stamm,bezeichnung,"+g.AU_Bezeichnungo("Stamm","stammview")+" Bez2 from stammview where aic_stammtyp=" + iBL + " and aic_rolle="+iRolle+" order by bezeichnung", true);
        JCOutlinerFolderNode NodRel = new JCOutlinerFolderNode(g.TabRollen.getS(iPos,"Bezeichnung"), (JCOutlinerFolderNode)OutStamm.getRootNode());
        NodRel.setStyle(StyFolder);
        for (TabBL.moveFirst(); !TabBL.out(); TabBL.moveNext())
        {
          JCOutlinerFolderNode Node = new JCOutlinerFolderNode(Static.beiLeer(TabBL.getS("Bez2"),TabBL.getS("Bezeichnung")), NodRel);
          Node.setStyle(g.getSttStyle(iBL));
          Node.setUserData(TabBL.getI("aic_stamm"));
        }
      }
    }
    OutStamm.folderChanged(OutStamm.getRootNode());
    // Feiertage füllen
    int iJahr=new DateWOD(g.getVon()).getYear();//get(DateWOD.YEAR);
    g.checkReadFeiertage(iJahr);
    Tabellenspeicher TabFT=new Tabellenspeicher(g,"select aic_feiertag,aic_code,Tag,kennung"+g.AU_Bezeichnung("feiertag")+" from feiertag where bis is null or bis>="+iJahr,true);
    for (TabFT.moveFirst();!TabFT.out();TabFT.moveNext())
    {
      Vector<Object> Vec=new Vector<Object>();
      Vec.addElement(TabFT.getS("Bezeichnung"));
      String sMonat=g.TabCodes.getKennung(TabFT.getI("aic_code"));
      Vec.addElement(sMonat.startsWith("Ost") ? (sMonat.equals("Ostersonntag") ? g.getShow("Ostersonntag"):g.getShow("Ost-Ostern"))+(TabFT.getI("Tag")<0 ?"":"+")+TabFT.getS("Tag"):TabFT.getS("Tag")+". "+g.TabCodes.getBezeichnungS(TabFT.getI("aic_code")));
      Vec.addElement(g.getFeiertagDatum(TabFT.getI("aic_feiertag")));
      Vec.addElement(TabFT.getS("Kennung"));
      Vec.addElement(TabFT.getI("aic_feiertag"));
      JCOutlinerFolderNode Node = new JCOutlinerFolderNode(Vec, (JCOutlinerFolderNode)OutFeiertag.getRootNode());
      Node.setStyle(Sty);
      Node.setUserData(TabFT.getI("aic_feiertag"));
      if (iFT==TabFT.getI("aic_feiertag"))
        Static.makeVisible(OutFeiertag,Node);
    }
    OutFeiertag.folderChanged(OutFeiertag.getRootNode());
    // Land füllen
    JCOutlinerFolderNode NodeL=null;
    Tabellenspeicher TabLand=new Tabellenspeicher(g,"select aic_land,aic_stamm,Kennung"+g.AU_Bezeichnung("land")+",null Node from land",true);
    for (TabLand.moveFirst();!TabLand.out();TabLand.moveNext())
    {
      Vector<String> Vec=new Vector<String>();
      Vec.addElement(Static.beiLeer(TabLand.getS("Bezeichnung"),TabLand.getS("Kennung")));
      Vec.addElement(TabLand.getI("aic_stamm")==0 ? "":g.getStamm(TabLand.getI("aic_stamm"),true,false));
      JCOutlinerFolderNode Node = new JCOutlinerFolderNode(Vec, (JCOutlinerFolderNode)OutZ.getRootNode());
      Node.setStyle(StyFolder);
      Node.setUserData(TabLand.getI("aic_land"));
      TabLand.setInhalt("Node",Node);
      if (iL==TabLand.getI("aic_land"))
        NodeL=Node;
      Node.setState(BWTEnum.FOLDER_CLOSED);
    }
    // Zuordnung füllen
    Tabellenspeicher TabZ=new Tabellenspeicher(g,"select * from feiertag_zuordnung z"+g.getReadMandanten(true,"z"),true);
    for (TabZ.moveFirst();!TabZ.out();TabZ.moveNext())
    {
      if (TabLand.posInhalt("aic_land",TabZ.getI("aic_land")) && TabFT.posInhalt("aic_feiertag",TabZ.getI("aic_feiertag")))
      {
        Vector<Object> Vec=new Vector<Object>();
        Vec.addElement(TabFT.getS("Bezeichnung"));
        Vec.addElement(TabZ.getI("aic_stamm")==g.keinStamm ? "":g.getStamm(TabZ.getI("aic_stamm"),true,false));
        Vec.addElement(g.getFeiertagDatum(TabFT.getI("aic_feiertag")));
        Vec.addElement(g.TabMandanten.getBezeichnungS(TabZ.getI("aic_mandant")));
        JCOutlinerFolderNode Node = new JCOutlinerFolderNode(Vec, (JCOutlinerFolderNode)TabLand.getInhalt("Node"));
        Node.setStyle(Sty);
        Vector<Integer> Vec2=new Vector<Integer>();
        Vec2.addElement(TabZ.getI("aic_feiertag"));
        Vec2.addElement(TabZ.getI("aic_stamm"));
        Vec2.addElement(TabZ.getI("aic_mandant"));
        Node.setUserData(Vec2);
      }
    }
    if (NodeL==null)
      OutZ.folderChanged(OutZ.getRootNode());
    else
      Static.makeVisible(OutZ,NodeL);
    bFill=false;
    EnableButtons();
  }

  private void hinzu()
  {
    int iStamm=OutStamm.getSelectedNode()==null || OutStamm.getSelectedNode().getLevel()<2 ? 0:Sort.geti(OutStamm.getSelectedNode().getUserData());
    JCOutlinerNode[] NodeFT = OutFeiertag.getSelectedNodes();
    JCOutlinerNode[] NodeZ = OutZ.getSelectedNodes();
    for(int i=0;i<NodeZ.length;i++)
      for(int i2=0;i2<NodeFT.length;i2++)
      {
        Vector<String> Vec=new Vector<String>();
        Vec.addElement(Sort.gets(NodeFT[i2].getLabel(),0));
        Vec.addElement(iStamm==0 ? "":Sort.gets(OutStamm.getSelectedNode().getLabel()));
        Vec.addElement(""+g.getFeiertagDatum(iFT));
        Vec.addElement(g.TabMandanten.getBezeichnungS(g.getMandant()));
        JCOutlinerFolderNode Node = new JCOutlinerFolderNode(Vec, (JCOutlinerFolderNode)NodeZ[i]);
        int iFT=Sort.geti(NodeFT[i2].getUserData());
        Vector<Integer> Vec2=new Vector<Integer>();
        Vec2.addElement(iFT);
        Vec2.addElement(iStamm);
        Vec2.addElement(g.getMandant());
        Node.setUserData(Vec2);
        g.setStyle(Node, g.ColAendern);
        TabChange.addInhalt("Feiertag",iFT);
        TabChange.addInhalt("Land",NodeZ[i].getUserData());
        TabChange.addInhalt("Stamm",iStamm);
        TabChange.addInhalt("Knoten",Node);
        TabChange.addInhalt("Art","hinzu");
      }
    OutZ.folderChanged(OutZ.getRootNode());
  }

  private void weg()
  {
    JCOutlinerNode[] NodeZ = OutZ.getSelectedNodes();
    for(int i=0;i<NodeZ.length;i++)
      if (NodeZ[i].getLevel()==2)
      {
        if (TabChange.posInhalt("Knoten",NodeZ[i]))
          TabChange.clearInhalt();
        else
        {
          TabChange.addInhalt("Land",NodeZ[i].getParent().getUserData());
          TabChange.addInhalt("Feiertag",Sort.geti(NodeZ[i].getUserData(),0));
          TabChange.addInhalt("Stamm",Sort.geti(NodeZ[i].getUserData(),1));
          TabChange.addInhalt("Knoten",NodeZ[i]);
          TabChange.addInhalt("Art","weg");
        }
        if (i==0)
          OutZ.selectNode(NodeZ[i].getParent());
        NodeZ[i].getParent().removeChild(NodeZ[i]);
      }
    OutZ.folderChanged(OutZ.getRootNode());
  }

  private void EnableButtons()
  {
    g.progInfo("EnableButtons");
    JCOutlinerNode NodeF = OutFeiertag.getSelectedNode();
    JCOutlinerNode NodeL = OutZ.getSelectedNode();
    if (NodeL != null && NodeL.getLevel()==1)
      iL=Sort.geti(NodeL.getUserData());
    if (NodeF != null && NodeF.getLevel()==1)
      iFT=Sort.geti(NodeF.getUserData());
    BtnHinzu.setEnabled(NodeL != null && NodeL.getLevel()==1 && NodeF!= null && NodeF.getLevel()==1);
    boolean bL2=NodeL != null && NodeL.getLevel()==2;
    int iMandant=bL2 ? Sort.geti(NodeL.getUserData(),2):0;
    BtnErsetzen.setEnabled(bL2 && NodeF!= null && NodeF.getLevel()==1 && g.getMandant()==iMandant);
    BtnWeg.setEnabled(bL2 && g.getMandant()==iMandant);
    BtnSave.setEnabled(TabChange.size()>0);
  }

  private void Speichern()
  {
    long lClock=Static.get_ms();
    SQL Qry=new SQL(g);
    for(TabChange.moveFirst();!TabChange.out();TabChange.moveNext())
    {
      int iStamm=TabChange.getI("Stamm")==0 ? g.keinStamm:TabChange.getI("Stamm");
      if (TabChange.getS("Art").equals("weg"))
        g.exec("delete from feiertag_zuordnung where aic_land="+TabChange.getI("Land")+" and aic_feiertag="+TabChange.getI("Feiertag")+
               " and aic_stamm="+iStamm+" and aic_mandant="+g.getMandant());
      else if (TabChange.getS("Art").equals("hinzu"))
      {
        Qry.add("aic_land",TabChange.getI("Land"));
        Qry.add("aic_feiertag",TabChange.getI("Feiertag"));
        Qry.add("aic_stamm",iStamm);
        Qry.add("aic_logging",g.getLog());
        Qry.add("aic_mandant",g.getMandant());
        Qry.insert("feiertag_zuordnung",false);
      }
    }
    Qry.free();
    TabChange.clearAll();
    Refresh();
    g.RefreshFeiertage();
    g.clockInfo("Feiertage.Speichern",lClock);
  }

  private void LoeschenF()
  {
    JCOutlinerNode[] NodeFT = OutFeiertag.getSelectedNodes();
    if (NodeFT.length>0 && NodeFT[0].getLevel()>0)
    {
      Tabellenspeicher Tab=Vorhanden(true);
      if (Tab.size()>0)
        new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,thisJFrame(),g).showDialog("BereitsVerwendet",new String[] {"Feiertag(e)"},Tab);
      else
      {
        for (int i = 0; i < NodeFT.length; i++)
        {
          int iFT = Sort.geti(NodeFT[i].getUserData());
          if(new Message(Message.YES_NO_OPTION,thisJFrame(),g).showDialog("Loeschen",new String[] {"["+NodeFT[i].getLabel()+"]"})==Message.YES_OPTION)
            if (g.exec("delete from feiertag where aic_feiertag=" + iFT)>0)
              NodeFT[i].getParent().removeChild(NodeFT[i]);
        }
        OutFeiertag.folderChanged(OutFeiertag.getRootNode());
      }
    }
  }

  private Tabellenspeicher Vorhanden(boolean bFT)
  {
    JCOutlinerNode[] Nodes = (bFT ? OutFeiertag:OutZ).getSelectedNodes();
    Vector<Integer> Vec=new Vector<Integer>();
    for(int i=0;i<Nodes.length;i++)
      Vec.addElement(Sort.geti(Nodes[i].getUserData()));
    return new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("Feiertag","z")+" Feiertag,"+g.AU_BezeichnungK("Land","z")+" Land"+
                                ",(case when aic_stamm="+g.keinStamm+" then null else (select bezeichnung from stammview2 where aic_stamm=z.aic_stamm and aic_rolle is null) end) Stamm"+
                                ",(case when aic_logging>0 then (select ein from logging where aic_logging=z.aic_logging) end) hinzu"+
                                ",(case when aic_logging>0 then (select "+g.AU_Bezeichnungo("Benutzer","logging")+" from logging where aic_logging=z.aic_logging) end) von_Benutzer"+
                                " from feiertag_zuordnung z where"+g.in(bFT ? "aic_feiertag":"aic_land",Vec),true);
  }

  private void EditF(boolean bNeu,boolean bCopy)
  {
    iFT=bNeu && !bCopy ? 0:Sort.geti(OutFeiertag.getSelectedNode().getUserData());
    //g.fixInfo("Edit "+iFT);
    if (!bCopy && iFT==0)
    {     
      TxtBezeichnungF.setText("");
      TxtKennungF.setText("");
      TxtMemoF.setText("");
      EdtVon.setIntValue(2000);
      EdtBis.setIntValue(2100);
    }
    else
    {
      Tabellenspeicher Tab=new Tabellenspeicher(g,"select *"+g.AU_Bezeichnung("feiertag")+g.AU_Memo("feiertag")+" from feiertag where aic_feiertag="+iFT,true);
      TxtBezeichnungF.setText(Tab.getS("Bezeichnung")+(bCopy ? "_2":""));
      TxtKennungF.setText(Tab.getS("Kennung")+(bCopy ? "_2":""));
      EdtTag.setIntValue(Tab.getI("Tag"));
      CboMonat.setSelectedAIC(Tab.getI("aic_code"));
      EdtVon.setIntValue(Tab.getI("Von")==0 ? 2000:Tab.getI("Von"));
      EdtBis.setIntValue(Tab.getI("Bis")==0 ? 2100:Tab.getI("Bis"));
      TxtMemoF.setText(Tab.getM("Memo"));
    }
    if (bCopy || iFT==0)
    {
    	iFT=0;
    	OutFeiertag.selectNode(OutFeiertag.getRootNode());
    }
    Static.centerComponent(FrmEditF,thisFrame);
    FrmEditF.setVisible(true);
  }

  private void EditF_Ok()
  {
	iFT=OutFeiertag.getSelectedNode().getLevel()==0 ? 0:Sort.geti(OutFeiertag.getSelectedNode().getUserData());
	String sKen=TxtKennungF.getText();
	if (TxtKennungF.isNull() || TxtBezeichnungF.isNull())
	{
		new Message(Message.WARNING_MESSAGE,FrmEditF,g,10).showDialog("BezLeer");
		return;
	}
	else if (sKen.length()>20)
	{
		new Message(Message.WARNING_MESSAGE,FrmEditF,g,10).showDialog("KennungZuLang");
		return;
	}
	else if (((iFT==0) || TxtKennungF.Modified()) && SQL.exists(g,"select aic_feiertag from  Feiertag WHERE aic_Feiertag<> "+iFT+" and Kennung='"+sKen+"'"))
	{
		new Message(Message.WARNING_MESSAGE,FrmEditF,g,10).showDialog("KennungVorhanden");
		return;
	}
    SQL Qry=new SQL(g);
    Qry.add("Kennung",TxtKennungF.getText());
    Qry.add("Tag",EdtTag.getIntValue());
    Qry.add("aic_code",CboMonat.getSelectedAIC());
    Qry.add("Von",EdtVon.getIntValue());
    Qry.add("Bis",EdtBis.getIntValue());
    if (iFT==0)
      iFT=Qry.insert("Feiertag",true);
    else
      Qry.update("Feiertag",iFT);
    Qry.free();
    int iTab=g.TabTabellenname.getAic("FEIERTAG");
    g.setBezeichnung("xxx",TxtBezeichnungF.getText(),iTab,iFT,0);
    g.setMemo(TxtMemoF.getValue(),TxtBezeichnungF.getText(),iTab,iFT,0);
    FrmEditF.setVisible(false);
    Refresh();
  }

  private void LoeschenL()
  {
    JCOutlinerNode[] NodeL = OutZ.getSelectedNodes();
    if (NodeL.length>0 && NodeL[0].getLevel()>0)
    {
      Tabellenspeicher Tab=Vorhanden(false);
      if (Tab.size()>0)
        new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,thisJFrame(),g).showDialog("BereitsVerwendet",new String[] {"Feiertag(e)"},Tab);
      else
      {
        for (int i = 0; i < NodeL.length; i++)
        {
          int iL = Sort.geti(NodeL[i].getUserData());
          if(new Message(Message.YES_NO_OPTION,g.getFomLeer(),g).showDialog("Loeschen",new String[] {"["+NodeL[i].getLabel()+"]"})==Message.YES_OPTION)
            if (g.exec("delete from land where aic_land=" + iL)>0)
              NodeL[i].getParent().removeChild(NodeL[i]);
        }
        OutZ.folderChanged(OutZ.getRootNode());
      }
    }
  }

  private void EditL(boolean bNeu)
  {
    iL=bNeu ? 0:Sort.geti(OutZ.getSelectedNode().getUserData());
    //g.fixInfo("EditL "+iL);
    if (iL==0)
    {
      OutZ.selectNode(OutFeiertag.getRootNode());
      TxtBezeichnungL.setText("");
      TxtKennungL.setText("");
      TxtISO3166.setText("");
      CboLand.fillStammTable(g.TabStammtypen.getAic("LAND"),SQL.getVector("select distinct aic_stamm from land", g),true);
      CboLand.setSelectedAIC(0);
      TxtMemoL.setText("");
    }
    else
    {
      Tabellenspeicher Tab=new Tabellenspeicher(g,"select *"+g.AU_Bezeichnung("land")+g.AU_Memo("land")+" from land where aic_land="+iL,true);
      TxtBezeichnungL.setText(Tab.getS("Bezeichnung"));
      TxtKennungL.setText(Tab.getS("Kennung"));
      TxtISO3166.setText(Tab.getS("ISO3166"));
      CboLand.fillStammTable(g.TabStammtypen.getAic("LAND"),SQL.getVector("select distinct aic_stamm from land where aic_land<>"+iL, g),true);
      CboLand.setSelectedAIC(Tab.getI("Aic_Stamm"));
      TxtMemoL.setText(Tab.getM("Memo"));
    }
    Static.centerComponent(FrmEditL,thisFrame);
    FrmEditL.setVisible(true);

  }

  private void EditL_Ok()
    {
      iL=OutZ.getSelectedNode().getLevel()==0 ? 0:Sort.geti(OutZ.getSelectedNode().getUserData());
      if(SQL.exists(g,"select aic_land from  Land WHERE aic_land<> "+iL+" and Kennung='"+TxtKennungL.getText()+"'"))
      {
        new Message(Message.WARNING_MESSAGE, FrmEditL, g,10).showDialog("KennungVorhanden");
        return;
      }

      SQL Qry=new SQL(g);
      Qry.add("Kennung",TxtKennungL.getText());
      Qry.add("ISO3166",TxtISO3166.getText());
      Qry.add0("Aic_Stamm",CboLand.getSelectedAIC());
      if (iL==0)
        iL=Qry.insert("Land",true);
      else
        Qry.update("Land",iL);
      Qry.free();
      int iTab=g.TabTabellenname.getAic("LAND");
      g.setBezeichnung("xxx",TxtBezeichnungL.getText(),iTab,iL,0);
      g.setMemo(TxtMemoL.getValue(),TxtBezeichnungL.getText(),iTab,iL,0);
      FrmEditL.setVisible(false);
      Refresh();
  }
}
