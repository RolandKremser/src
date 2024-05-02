package All_Unlimited.Grunddefinition;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JLabel;
import javax.swing.JPanel;

import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.EigenschaftsEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import jclass.bwt.JCOutlinerNodeStyle;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPopupMenu;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Component;
import jclass.bwt.JCActionEvent;
import javax.swing.JDialog;
import jclass.bwt.JCActionListener;
//import java.awt.FlowLayout;

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
public class DefBew extends Formular
{
  private Global g;
  private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
  private AUOutliner OutZ = new AUOutliner(new JCOutlinerFolderNode(""));
  private Text TxtBezeichnung = new Text("",200);
  private Text TxtKennung = new Text("",98,Text.KENNUNG);
  private final static int iANRs=9;
  private final static int iLDATEs=2;
  private final static int iBOOLs=2;
  private final static int iVBs=1;
  private static int iEigGes=iANRs+iLDATEs+iBOOLs+iVBs;
  private EigenschaftsEingabe[] CboEig=new EigenschaftsEingabe[iEigGes]; // 1-9: ANR, 10-11: LDATE, 12-13: BOOL
  private AUCheckBox CbxTimer=null;
  private AUCheckBox CbxLokalTimer=null;
  private AUCheckBox CbxNurModell=null;
  private AUCheckBox CbxMandantIgnorieren=null;
  private AUCheckBox CbxBerechtigung=null;
  private AUCheckBox CbxDel=null;
  private AUCheckBox CbxDef=null;
  private AUCheckBox CbxSperre=null;
  private AUCheckBox CbxBerechtigbar=null;
  private AUCheckBox CbxBewVoll=null;
  private AUCheckBox CbxBewBew=null;
  private AUCheckBox CbxBewZone=null;
  private AUCheckBox CbxNOA=null;
  private AUCheckBox CbxANR1=null;
  private AUCheckBox CbxANR2=null;
  private AUCheckBox CbxANR3=null;
  private AUCheckBox CbxANR4=null;
  private AUCheckBox CbxANR5=null;
  private AUCheckBox CbxANR6=null;
  private AUCheckBox CbxANR7=null;
  private AUCheckBox CbxANR8=null;
  private AUCheckBox CbxANR9=null;
  private JRadioButton RadSK=null;
  private JRadioButton RadK=null;
  private JRadioButton RadN=null;
  private JRadioButton RadL=null;
  private JRadioButton RadNie=null;
  private AUCheckBox CbxTod=null;
  private AUTextArea Memo=new AUTextArea();
  private static int iAktAIC=0;
  private ActionListener AL;
  private Tabellenspeicher Tab;
  private JButton BtnShow;
  private JPopupMenu popup;
  private static DefBew Self;

  public static DefBew get(Global g,int riBew)
    {
      iAktAIC=riBew;
      if (Self==null)
        new DefBew(g);
      else
        Self.show();
      return Self;
  }

  public static void free()
  {

    if (Self != null)
    {
      Self.g.winInfo("DefBew.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

  private DefBew(Global glob)
  {
    super("DefBew", null, glob);
    Self=this;
    glob.winInfo("DefBew.create");
    Build(glob);
    show();
  }

//  private void BtnAdd(String s)
//  {
//    JButton Btn=getButton(s);
//    if (Btn != null)
//    {
//      Btn.setActionCommand(s);
//      Btn.addActionListener(AL);
//      if (s.equals("show"))
//        BtnShow=Btn;
//    }
//  }

  private void Build(Global glob)
  {
    g = glob;
    /*String [] s = {g.getBegriff("Show","Bezeichnung"),g.getBegriff("Show","Kennung"),g.getBegriff("Show","Eigenschaft"),g.getBegriff("Show","Change"),g.getBegriff("Show","Nr")};
    Out.setColumnButtons(s);
    Out.setNumColumns(s.length);
    Out.setRootVisible(false);
    Out.setColumnLabelSortMethod(Sort.sortMethod);
    Out.setBackground(Color.white);
    String [] s2 = {g.getBegriff("Show","Bezeichnung"),g.getBegriff("Show","Reihenfolge")};
    OutZ.setColumnButtons(s2);
    OutZ.setNumColumns(s2.length);
    OutZ.setRootVisible(false);
    OutZ.setColumnLabelSortMethod(Sort.sortMethod);
    OutZ.setBackground(Color.white);*/
    g.initOutliner(Out,new String[] {"Bezeichnung","Kennung","Eigenschaft","Eigenschaft2","Change","Bits","ANR","delete","Nr","BewVoll"});
    g.initOutliner(OutZ,new String[] {"Bezeichnung","Reihenfolge","Datentyp"});
    for (int i=0;i<iEigGes;i++)
      CboEig[i]=new EigenschaftsEingabe(g,thisFrame);

    JPanel PnlOutliner = getFrei("Outliner Bewegung");
    JPanel PnlZuordnung = getFrei("Outliner Zuordnung");
    JPanel PnlEingabe = getFrei("Eingabe");
    JPanel PnlMemo = getFrei("Memo");
    PnlOutliner.add(Out);
    if (PnlZuordnung != null)
      PnlZuordnung.add(OutZ);
    PnlEingabe.setLayout(new BorderLayout(2,2));
    JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
    g.addLabel2(Pnl,"Bezeichnung");
    g.addLabel2(Pnl,"Kennung");
    CbxANR1=getCheckboxM("ANR1",false);
    CbxANR2=getCheckboxM("ANR2",false);
    CbxANR3=getCheckboxM("ANR3",false);
    CbxANR4=getCheckboxM("ANR4",false);
    CbxANR5=getCheckboxM("ANR5",false);
    CbxANR6=getCheckboxM("ANR6",false);
    CbxANR7=getCheckboxM("ANR7",false);
    CbxANR8=getCheckboxM("ANR8",false);
    CbxANR9=getCheckboxM("ANR9",false);
    g.addComp(Pnl,CbxANR1);
    g.addComp(Pnl,CbxANR2);
    g.addComp(Pnl,CbxANR3);
    g.addComp(Pnl,CbxANR4);
    g.addComp(Pnl,CbxANR5);
    g.addComp(Pnl,CbxANR6);
    g.addComp(Pnl,CbxANR7);
    g.addComp(Pnl,CbxANR8);
    g.addComp(Pnl,CbxANR9);
    g.addLabel2(Pnl,"LDATE2");
    g.addLabel2(Pnl,"LDATE3");
    g.addLabel2(Pnl,"BOOL1");
    g.addLabel2(Pnl,"BOOL2");
    g.addLabel2(Pnl,"LDATEVB");
    /*g.addLabel2(Pnl,"Timer");
    g.addLabel2(Pnl,"Lokaltimer");
    g.addLabel2(Pnl,"nur Modell");
    g.addLabel2(Pnl,"MandantKopie");*/
    CbxTimer=getCheckboxM("Timer3",false);
    CbxLokalTimer=getCheckboxM("Lokaltimer",false);
    CbxNurModell=getCheckboxM("nur Modell3",false);
    CbxMandantIgnorieren=getCheckboxM("Mandant ignorieren",false);
    CbxBerechtigung=getCheckboxM("Berechtigung2",false);
    CbxDel=getCheckboxM("altes",false);
    CbxDef=getCheckboxM("Definition",false);
    CbxSperre=getCheckboxM("Sperre2",false);
    CbxBerechtigbar=getCheckboxM("berechtigbar",false);
    CbxBewVoll=getCheckboxM("BewVoll",false);
    CbxBewBew=getCheckboxM("BewBew",false);
    CbxBewZone=getCheckboxM("BewZone",false);
    CbxNOA=getCheckboxM("NOA",false);
    CbxTod=getCheckboxM("Tod",false);
    RadSK=getRadiobuttonM("DDsehrkurz");
    RadK=getRadiobuttonM("DDkurz");
    RadN=getRadiobuttonM("DDnormal");
    RadL=getRadiobuttonM("DDlang");
    RadNie=getRadiobuttonM("DDnie");
    ButtonGroup RadDD=new ButtonGroup();
    RadDD.add(RadSK);
    RadDD.add(RadK);
    RadDD.add(RadN);
    RadDD.add(RadL);
    RadDD.add(RadNie);

    //for (int i=0;i<5;i++)
    //  Pnl.add(new JLabel());
    //g.addLabel2(Pnl,"Bits");
    Pnl.add(CbxSperre);
    Pnl.add(CbxDel);
    Pnl.add(CbxBerechtigbar);
    g.addLabel2(Pnl,"Del");
    Pnl.add(RadNie);
    Pnl.add(CbxTod);
    Pnl.add(new JLabel());
    PnlEingabe.add("West",Pnl);

    Pnl = new JPanel(new GridLayout(0,1,2,2));
    g.addComp(Pnl,TxtBezeichnung);
    g.addComp(Pnl,TxtKennung);
    for (int i=0;i<iEigGes;i++)
      g.addComp(Pnl,CboEig[i]);

      JPanel PnlSub = new JPanel(new GridLayout(1,2,2,2));
      PnlSub.add(CbxTimer);
      PnlSub.add(CbxLokalTimer);
    g.addComp(Pnl,PnlSub);
      PnlSub = new JPanel(new GridLayout(1,2,2,2));
      PnlSub.add(CbxNurModell);
      PnlSub.add(CbxBerechtigung);
    g.addComp(Pnl,PnlSub);
      PnlSub = new JPanel(new GridLayout(1,2,2,2));
      PnlSub.add(CbxMandantIgnorieren);
      PnlSub.add(CbxDef);
    g.addComp(Pnl,PnlSub);
      PnlSub = new JPanel(new GridLayout(1,2,2,2));
      PnlSub.add(RadSK);
      PnlSub.add(RadK);
    g.addComp(Pnl,PnlSub);
      PnlSub = new JPanel(new GridLayout(1,2,2,2));
      PnlSub.add(RadN);
      PnlSub.add(RadL);
    g.addComp(Pnl,PnlSub);
      PnlSub = new JPanel(new GridLayout(1,2,2,2));
      PnlSub.add(CbxBewVoll);
      PnlSub.add(CbxBewBew);
    g.addComp(Pnl,PnlSub);
    PnlSub = new JPanel(new GridLayout(1,2,2,2));
      PnlSub.add(CbxBewZone);
      PnlSub.add(CbxNOA);
    g.addComp(Pnl,PnlSub);
    PnlEingabe.add("Center",Pnl);
    PnlMemo.add(Memo);

    Out.addItemListener(new JCOutlinerListener()
    {
            public void itemStateChanged(JCItemEvent e) {}
            public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {}
            public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {}
            public void outlinerNodeSelectBegin(JCOutlinerEvent e) {}
            public void outlinerNodeSelectEnd(JCOutlinerEvent e)
            {
                    setSelected();
            }
    });

    AL=new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
          String s=ev.getActionCommand();
          if (s.equals("init ANR"))
            BewInit();
          else if (s.startsWith("Bewbits"))
            ShowBewbits();
          else if (s.equals("Neu"))
            BewNew();
          else if (s.equals("Loeschen"))
            BewDel();
          else if (s.equals("Info"))
            Info(false).showGrid("Bew "+iAktAIC);
          else if (s.equals("Test"))
            Reinigung.TestBew(g,iAktAIC,thisJFrame());
          else if (s.equals("show"))
          {
            BtnShow.setEnabled(false);
            new Thread(new Runnable()
            {

              public void run()
              {
            	long lClock = Static.get_ms();
                new Tabellenspeicher(g, sqlShow(), true).showGrid("Bewegungsliste", (java.awt.Frame)thisFrame);
                BtnShow.setEnabled(true);
                g.clockInfo("Bewegungsliste", lClock);
              }
            }).start();
          }
          else if (s.equals("ANR"))
          {
            //if(new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("ANR_aufbauen") == Message.YES_OPTION)
              g.fixInfo(Reinigung.ANR_Check(g, "",iAktAIC, true,true));
              Reinigung.setLDate(g,iAktAIC);
              Reinigung.setBOOL(g,iAktAIC);
              Reinigung.setLDateVB(g,iAktAIC);
          }
          else if (s.equals("BOOL3"))
            Reinigung.setBOOL3Index(g);
          else if (s.equals("Clear"))
        	Reinigung.CleanBew(g,iAktAIC,getBegriff()); 
          else if (s.equals("Speichern"))
            BewSave();
          else if (s.equals("noBewVoll"))
        	ShowNoBewVoll();
          else if (s.equals("Beenden"))
            hide();
        }
    };
    getButton("init ANR",null,AL);
    getButton("Neu",null,AL);
    getButton("Loeschen",null,AL);
    getButton("Speichern",null,AL);
    getButton("noBewVoll",null,AL);
    getButton("Info",null,AL);
    getButton("Test",null,AL);
    BtnShow=getButton("show",null,AL);
    getButton("ANR",null,AL);
    getButton("BOOL3",null,AL);
    getButton("Clear",null,AL);
    getButton("Beenden",null,AL);

    popup= new JPopupMenu();
        g.addMenuItem("Neu", popup,null,AL);
        g.addMenuItem("Loeschen", popup,null,AL);
        popup.addSeparator();
        g.addMenuItem("Info", popup,null,AL);
        g.addMenuItem("Bewbits", popup,null,AL);
        Out.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
            //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
            if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM) && g.Def())
              popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
          }

          public void mouseEntered(MouseEvent ev) {}
          public void mouseExited(MouseEvent ev) {}
          public void mouseReleased(MouseEvent ev) {}
        });
  }
  
  private void ShowNoBewVoll()
  {
	  Tabellenspeicher Tab = new Tabellenspeicher(g,"select distinct b.DefBezeichnung,b.kennung,(select max(aic_defverlauf) from defverlauf where aic_begriff=b.aic_begriff) last"+
      		g.AU_Bezeichnung("Bewegungstyp","b")+" Bewegungstyp"+ g.AU_Bezeichnung("Stammtyp","b")+" Stammtyp,b.aic_begriff"+g.AU_Memo("begriff", "b")+" from darstellung d1"+
			  " join darstellung d2 on d1.aic_darstellung=d2.dar_aic_darstellung and d1.aic_begriff=497 join begriff b on d2.aic_begriff=b.aic_begriff" + 
      		  " where d2.aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe=30 and bits&0x08000000=0) and b.aic_bewegungstyp="+iAktAIC+" order by last desc",true);
      DefAbfrage.ShowOpenAbf(g,thisJFrame(),"ohne Bew Voll",Tab);
  }

  private String sqlShow()
  {
    String s = "select * from (select "+g.AU_Bezeichnungo("bewegungstyp")+" Bezeichnung,kennung,aic_bewegungstyp aic"+
        ",(select count(aic_bew_pool) from bew_pool where aic_bewegungstyp=bewegungstyp.aic_bewegungstyp and pro_aic_protokoll is null) Daten"+
        ",(select count(aic_bew_pool) from bew_pool where aic_bewegungstyp=bewegungstyp.aic_bewegungstyp and pro_aic_protokoll is not null) entfernt";
    for (int i=1;i<=iANRs;i++)
      s+=g.AU_Bezeichnung2("Eigenschaft","aic_eig"+i,"''")+"Eigenschaft"+i;
    s+= g.bei("bewbits",Global.cstTimer,"Timer")+g.bei("bewbits",Global.cstLokalTimer,"Timer2")+g.bei("bewbits",Global.cstNurModell,"nur_Modell")+
        g.bei("bewbits",Global.cstMandantIgnorieren,"Mandant_ignorieren")+g.bei("bewbits",Global.cstBerechtigung,"Berechtigung")+
        g.bei("bewbits",Global.cstANR1,"bei_ANR1")+g.bei("bewbits",Global.cstANR2,"bei_ANR2")+g.bei("bewbits",Global.cstANR3,"bei_ANR3")+
        g.bei("bewbits",Global.cstANR4,"bei_ANR4")+g.bei("bewbits",Global.cstANR5,"bei_ANR5")+g.bei("bewbits",Global.cstANR6,"bei_ANR6")+
        g.bei("bewbits",Global.cstANR7,"bei_ANR7")+g.bei("bewbits",Global.cstANR8,"bei_ANR8")+g.bei("bewbits",Global.cstANR9,"bei_ANR9")+" from bewegungstyp) Bew order by bezeichnung";
    return s;
  }

  private void BewInit()
  {
    Vector<Integer> Vec=new Vector<Integer>();
    for (int i=0;i<iANRs;i++)
    {
      int iAic=CboEig[i].getSelectedAIC();
      if (iAic>0 && !Vec.contains(iAic))
        Vec.addElement(new Integer(iAic));
    }
    for (int i=0;i<iANRs;i++)
    {
      if (CboEig[i].getSelectedAIC()==0)
        for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
        {
          int iAic=Tab.getI("Aic");
          if (!Vec.contains(iAic))
          {
            Vec.addElement(new Integer(iAic));
            CboEig[i].setSelectedAIC(iAic);
            Tab.moveLast();
          }
        }
    }
  }

  private void BewNew()
  {
    Out.selectNode(Out.getRootNode(),null);
    //setSelected();
  }

  private int[] getCboANRs()
  {
    int[] iR=new int[iEigGes];
    for (int i=0;i<iEigGes;i++)
      iR[i]=CboEig[i].getSelectedAIC();
    return iR;
  }

  private void BewSave()
  {
    if (TxtKennung.isNull())
    {
      new Message(Message.ERROR_MESSAGE, (JFrame)thisFrame, g,10).showDialog("KennungLeer");
      return;
    }
    if(SQL.exists(g,"select aic_bewegungstyp from bewegungstyp WHERE aic_bewegungstyp<> "+iAktAIC+" and Kennung='"+TxtKennung.getText()+"'"))
    {
      new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g,10).showDialog("KennungVorhanden");
      return;
    }
    SQL Qry = new SQL(g);
    int iBits=(CbxTimer.isSelected()?Global.cstTimer:0)+(CbxLokalTimer.isSelected()?Global.cstLokalTimer:0)
        +(CbxNurModell.isSelected()?Global.cstNurModell:0)+(CbxMandantIgnorieren.isSelected()?Global.cstMandantIgnorieren:0)
        +(CbxBerechtigung.isSelected()?Global.cstBerechtigung:0)+(CbxDel.isSelected()?Global.cstDel:0)+(CbxDef.isSelected()?Global.cstDefinition:0)
        +(CbxSperre.isSelected()?Global.cstSperre:0)+(CbxBerechtigbar.isSelected()?Global.cstBewBrtg:0)+(CbxBewVoll.isSelected()?Global.cstBewVoll:0)
        +(CbxBewBew.isSelected()?Global.cstBewBew:0)+(CbxBewZone.isSelected()?Global.cstBewZone:0)+(CbxNOA.isSelected()?Global.cstNOA:0)
        +(CbxANR1.isSelected()?Global.cstANR1:0)+(CbxANR2.isSelected()?Global.cstANR2:0)+(CbxANR3.isSelected()?Global.cstANR3:0)
        +(CbxANR4.isSelected()?Global.cstANR4:0)+(CbxANR5.isSelected()?Global.cstANR5:0)+(CbxANR6.isSelected()?Global.cstANR6:0)
        +(CbxANR7.isSelected()?Global.cstANR7:0)+(CbxANR8.isSelected()?Global.cstANR8:0)+(CbxANR9.isSelected()?Global.cstANR9:0)
        +(RadSK.isSelected()?Global.cstSK:RadK.isSelected()?Global.cstK:RadN.isSelected()?Global.cstN:RadL.isSelected() ? Global.cstL:RadNie.isSelected() ? Global.cstNIE:0);
    Qry.add("Kennung",TxtKennung.getText());
    for (int i=0;i<iANRs;i++)
      Qry.add0("AIC_Eig"+(i+1),CboEig[i].getSelectedAIC());
    for (int i=iANRs;i<iANRs+iLDATEs;i++)
    {
      Qry.add0("Datum_Eig" + (i - iANRs + 2), CboEig[i].getSelectedAIC());
      if (CboEig[i].Cbo.Modified())
        Reinigung.setLDate(i-iANRs+2,g,iAktAIC,CboEig[i].getSelectedAIC());
    }
    for (int i=iANRs+iLDATEs;i<iANRs+iLDATEs+iBOOLs;i++)
    {
      Qry.add0("Bool_Eig" + (i - iANRs - iLDATEs + 1), CboEig[i].getSelectedAIC());
      if (CboEig[i].Cbo.Modified())
        Reinigung.setBOOL(i-iANRs-iLDATEs+1,g,iAktAIC,CboEig[i].getSelectedAIC());
    }
    Qry.add0("VonBis_Eig", CboEig[iEigGes-1].getSelectedAIC());
    Reinigung.setLDateVB(g,iAktAIC,CboEig[iEigGes-1].getSelectedAIC());
    //TODO Reinigung LDateVonBis
    Qry.add("BewBits",iBits);
    Qry.add0("Tod",CbxTod.isSelected());
    Qry.add("aic_logging",g.getLog());
    boolean bNew=iAktAIC==0;
    if (bNew)
      iAktAIC=Qry.insert("Bewegungstyp",true);
    else
      Qry.update("Bewegungstyp",iAktAIC);
    Qry.free();
    int iTabBew=g.TabTabellenname.getAic("BEWEGUNGSTYP");
    g.setBezeichnung("",TxtBezeichnung.getText(),iTabBew,iAktAIC,0);
    g.setMemo(Memo.getValue(),"",iTabBew,iAktAIC,0);
    g.putTabErfassungstyp(iAktAIC,TxtKennung.getText(),TxtBezeichnung.getText(),null,getCboANRs(),iBits,bNew);
    FillOutliner();
  }

  private void ShowBewbits()
  {
    Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr", "Sub", "Kennung", "Bezeichnung", "Anzahl"});
    addBit(Tab2, "cstTimer", "Timer3", g.cstTimer, 0);
    addBit(Tab2, "cstLokalTimer", "Lokaltimer", g.cstLokalTimer, 1);
    addBit(Tab2, "cstNurModell", "nur Modell3", g.cstNurModell, 2);
    addBit(Tab2, "cstMandantIgnorieren", "Mandant ignorieren", g.cstMandantIgnorieren, 3);
    addBit(Tab2, "cstBerechtigung", "Berechtigung2", g.cstBerechtigung, 4);
    addBit(Tab2, "cstANR1", "ANR1", g.cstANR1, 5);
    addBit(Tab2, "cstANR2", "ANR2", g.cstANR2, 6);
    addBit(Tab2, "cstANR3", "ANR3", g.cstANR3, 7);
    addBit(Tab2, "cstANR4", "ANR4", g.cstANR4, 8);
    addBit(Tab2, "cstANR5", "ANR5", g.cstANR5, 9);
    addBit(Tab2, "cstANR6", "ANR6", g.cstANR6, 10);
    addBit(Tab2, "cstANR7", "ANR7", g.cstANR7, 11);
    addBit(Tab2, "cstANR8", "ANR8", g.cstANR8, 12);
    addBit(Tab2, "cstANR9", "ANR9", g.cstANR9, 13);
    addBit(Tab2, "cstDel", "altes", g.cstDel, 16);
    addBit(Tab2, "cstDefinition", "Definition", g.cstDefinition, 17);
    addBit(Tab2, "cstSperre", "Sperre2", g.cstSperre, 18);
    addBit(Tab2, "cstBewBrtg", "berechtigbar", g.cstBewBrtg, 19);
    addBit(Tab2, "cstBewVoll", "BewVoll", g.cstBewVoll, 21);
    addBit(Tab2, "cstBewBew", "BewBew", g.cstBewBew, 22);
    addBit(Tab2, "cstBewZone", "BewZone", g.cstBewZone, 23);
    addBit(Tab2, "cstNOA", "NOA", g.cstNOA, 24);
    addBit(Tab2, "cstSK","DDsehrkurz",g.cstDD,g.cstSK,20,1);
    addBit(Tab2, "cstK","DDkurz",g.cstDD,g.cstK,20,2);
    addBit(Tab2, "cstN","DDnormal",g.cstDD,g.cstN,20,3);
    addBit(Tab2, "cstL","DDlang",g.cstDD,g.cstL,20,4);
    addBit(Tab2, "cstNIE","DDnie",g.cstDD,g.cstNIE,20,5);
    /*addBit(Tab2, "cstEnde", "Ende", g.cstEnde, 0);
    addBit(Tab2, "cstTranslate", "translate2", g.cstTranslate, 1);
    addBit(Tab2, "cstBenutzer", "Benutzer", g.cstBenutzer, 2);
    addBit(Tab2, "cstEinheit", "Einheit", g.cstEinheit, 3);
    addBit(Tab2, "cstLizenz", "Lizenz", g.cstLizenz, 4);
    addBit(Tab2, "cstCopy", "Copy", g.cstCopy, 5);
    addBit(Tab2, "cstStdFormular", "Std_formular", g.cstStdFormular, 6);
    addBit(Tab2, "cstKeinStichtag", "kein_Stichtag", g.cstKeinStichtag, 7);
    addBit(Tab2, "cstModul", "Modul", g.cstModul, 8);
    addBit(Tab2, "cstHA", "HA", g.cstHA, 9);
    addBit(Tab2, "cstBFilter", "BFilter", g.cstBFilter, 10);*/
    final JDialog FomGid = new JDialog(thisJFrame(), "Bewegungstypbits", false);
    AUOutliner Grid = new AUOutliner();
    FomGid.getContentPane().add("Center", Grid);
    Tab2.showGrid(Grid);
    FomGid.pack();//setSize(400, 600);
    Static.centerComponent(FomGid,thisFrame);
    Grid.addActionListener(new JCActionListener() {
          public void actionPerformed(JCActionEvent ev) {
            JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
            int i=Sort.geti(Nod.getLabel(),0);
            int i2=Sort.geti(Nod.getLabel(),1);
            long l=(long)Math.pow(2,i);
            long l2=0;
            if (i == 20)
            {
              l=g.cstDD;
              l2=i2==1 ? g.cstSK:i2==2 ? g.cstK:i2==3 ? g.cstN:i2==4 ? g.cstL:g.cstNIE;
            }
            Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_Bewegungstyp aic"+g.AU_Bezeichnung("Bewegungstyp")+",kennung from Bewegungstyp where "+/*g.bit("BewBits",l)*/(i2>0 ? g.bits("BewBits",l)+"="+l2:g.bit("BewBits",l)),true);
            Tab.showGrid("Bewegungstyp mit bit " + Sort.gets(Nod.getLabel(),2), thisFrame);
          }
        });
    FomGid.setVisible(true);
  }

  private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
  {
      Tab2.addInhalt("Nr",i);
      Tab2.addInhalt("Sub",0);
      Tab2.addInhalt("Kennung",sConst);
      Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
      Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from Bewegungstyp where "+g.bit("BewBits",l)));
  }
  
  private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,long l2,int i,int iSub)
  {
    Tab2.addInhalt("Nr",i);
    Tab2.addInhalt("Sub",iSub);
    Tab2.addInhalt("Kennung",sConst);
    Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Radiobutton",sBez));
    Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from Bewegungstyp where "+g.bits("BewBits",l)+"="+l2));
  }


  private Tabellenspeicher Info(boolean bDel)
  {
	CbxTod.setEnabled(true);
    Tabellenspeicher Tab = new Tabellenspeicher(g, "select (select kennung from begriffgruppe where aic_begriffgruppe=begriff.aic_begriffgruppe) Gruppe,defbezeichnung Bezeichnung,begriff.kennung,aic_begriff aic,null Anzahl from begriff where aic_bewegungstyp=?",""+iAktAIC,true);
    new Tabellenspeicher(g, "select 'Eigenschaft' Gruppe" + g.AU_Bezeichnung("eigenschaft") +
                         ",kennung,aic_eigenschaft aic,null Anzahl from eigenschaft where aic_bewegungstyp=?",""+iAktAIC, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'Abschlusstyp' Gruppe" + g.AU_Bezeichnung("abschlusstyp") +
                         ",kennung,abschlusstyp.aic_abschlusstyp aic,null Anzahl from abschlussdefinition"+g.join("abschlusstyp","abschlussdefinition")+" where aic_bewegungstyp=?",""+iAktAIC, true).addTo(Tab, true);
    if (!bDel)
    {
      new Tabellenspeicher(g,"select 'gelöschte Daten' Gruppe,null Bezeichnung,null Kennung,null aic,count(*) Anzahl from bew_pool where aic_bewegungstyp=? and pro_aic_protokoll is not null",""+iAktAIC, true).addTo(Tab, true);
      new Tabellenspeicher(g,"select 'aktive Daten' Gruppe,null Bezeichnung,null Kennung,null aic,count(*) Anzahl from bew_pool where aic_bewegungstyp=? and pro_aic_protokoll is null",""+iAktAIC, true).addTo(Tab, true);
    }
    new Tabellenspeicher(g, "select 'Abfrage-Spalte' Gruppe,b.defbezeichnung Bezeichnung,b.kennung,b.aic_begriff aic,count(*) Anzahl from begriff b "+
                         g.join("abfrage","b","begriff")+g.join2("spalte","abfrage")+g.join2("fixeigenschaft","spalte")+
                         " where fixeigenschaft.aic_bewegungstyp=? group by b.defbezeichnung,b.kennung,b.aic_stammtyp,b.aic_begriff",""+iAktAIC, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'Abfrage-Bedingung' Gruppe,b.defbezeichnung Bezeichnung,b.kennung,b.aic_begriff aic,count(*) Anzahl from begriff b "+
                         g.join("abfrage","b","begriff")+g.join2("bedingung","abfrage")+g.join2("fixeigenschaft","bedingung")+
                         " where fixeigenschaft.aic_bewegungstyp=? group by b.defbezeichnung,b.kennung,b.aic_stammtyp,b.aic_begriff",""+iAktAIC, true).addTo(Tab, true);
    /*Tabellenspeicher Tab = new Tabellenspeicher(g, "select distinct 'Mandant' Art" + g.AU_Bezeichnung("Mandant") +
                                                ",kennung,aic_mandant aic from mandant where aic_begriff=" + iAIC_Begriff, true);
    new Tabellenspeicher(g, "select 'Benutzergruppe' Art" + g.AU_Bezeichnung("Benutzergruppe") +
                         ",kennung,aic_benutzergruppe aic from benutzergruppe where aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'Formular' Art, defBezeichnung Bezeichnung,kennung,begriff.aic_begriff aic from darstellung" +
                         g.join("formular", "darstellung") + g.join("begriff", "formular") + " where darstellung.aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'Formularmenge' Art, defBezeichnung Bezeichnung,kennung,begriff.aic_begriff aic from begriff" +
                         " join begriff_zuordnung z on z.aic_begriff=begriff.aic_begriff and aic_begriffgruppe=" +
                         g.TabBegriffgruppen.getAic("Formularmenge") + " and z.beg_aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'Modul' Art, defBezeichnung Bezeichnung,kennung,begriff.aic_begriff aic from begriff" +
                         " join begriff_zuordnung z on z.aic_begriff=begriff.aic_begriff and aic_begriffgruppe=" +
                         g.TabBegriffgruppen.getAic("Modul") + " and z.beg_aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'Applikation' Art, defBezeichnung Bezeichnung,kennung,begriff.aic_begriff aic from begriff" +
                         " join begriff_zuordnung z on z.beg_aic_begriff=begriff.aic_begriff and aic_begriffgruppe=" +
                         g.TabBegriffgruppen.getAic("Applikation") + " and z.aic_begriff=" + iAIC_Begriff, true).addTo(Tab, true);*/
    return Tab;
  }

  private void BewDel()
  {
    Tabellenspeicher Tab=Info(true);
    String s=TxtBezeichnung.getText();
    if(Tab.isEmpty())
    {
      int pane = new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Loeschen", new String[] {s});
      if (pane == Message.YES_OPTION)
      {
        g.bISQL=true;
        g.exec("delete from bewegungstyp where aic_bewegungstyp="+iAktAIC);
        g.bISQL=false;
        if (g.sError==null)
          FillOutliner();
        else
          new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g,10).showDialog("BereitsVerwendet", new String[] {s});
      }
    }
    else
      new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,(JFrame)thisFrame,g).showDialog("BereitsVerwendet",new String[] {s},Tab);

  }

  public void show()
  {
    FillOutliner();
    super.show();
    setSelected();
  }

      private void FillOutliner()
      {
              SQL Qry = new SQL(g);
              JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
              NodeRoot.removeChildren();

              if(Qry.open("select * from (select bewegungstyp.*"+g.AU_Bezeichnung("bewegungstyp")+g.AU_Memo("bewegungstyp")+
                          ",(select ein from logging where aic_logging=bewegungstyp.aic_logging) log"+
                          g.AU_Bezeichnung2("Eigenschaft","aic_eig2","''")+"Eigenschaft2"+
                          g.AU_Bezeichnung2("Eigenschaft","aic_eig1","''")+"Eigenschaft1 from bewegungstyp) x order by bezeichnung"))
              {
                  for(;!Qry.eof();Qry.moveNext())
                  {
                          Vector<Comparable> VecVisible=new Vector<Comparable>();
                          Vector<Comparable> VecInvisible=new Vector<Comparable>();

                          VecVisible.addElement(Qry.getS("bezeichnung"));
                          VecVisible.addElement(Qry.getS("kennung"));
                          VecVisible.addElement(Qry.getS("Eigenschaft1"));
                          VecVisible.addElement(Qry.getS("Eigenschaft2"));
                          VecVisible.addElement(Qry.getD("log"));
                          addBits(VecVisible,Qry.getI("Bewbits"));
                          VecVisible.addElement(Qry.getInt("aic_bewegungstyp"));
                          VecVisible.addElement(Static.JaNein2((Qry.getI("Bewbits")&Global.cstBewVoll)>0));
                          VecInvisible.addElement(Qry.getInt("aic_bewegungstyp"));
                          for (int i=0;i<iANRs;i++)
                            VecInvisible.addElement(Qry.getInt("aic_eig"+(i+1)));
                          VecInvisible.addElement(Qry.getInt("Bewbits"));
                          VecInvisible.addElement(Qry.getS("Memo"));
                          for (int i=0;i<iLDATEs;i++)
                            VecInvisible.addElement(Qry.getInt("Datum_Eig"+(i+2)));
                          for (int i=0;i<iBOOLs;i++)
                            VecInvisible.addElement(Qry.getInt("Bool_Eig"+(i+1)));
                          //for (int i=0;i<iVBs;i++)
                              VecInvisible.addElement(Qry.getInt("VonBis_Eig"));
                          //g.progInfo("Memo="+Qry.getS("Memo"));

                          JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodeRoot);
                          Nod.setUserData(VecInvisible);
                          Image Gif = g.getBewGif(Qry.getInt("aic_bewegungstyp"));
                          Nod.setStyle(g.setColor(g.getStyle(Gif),Qry.getB("Tod") ? g.ColTod:g.ColStandard));
                          if (iAktAIC==Qry.getI("aic_bewegungstyp"))
                            Out.selectNode(Nod,null);
                  }
                  Qry.close();
              }
              Qry.free();

              //Out.folderChanged(NodeRoot);
              Static.makeVisible(Out,null);
    }

  private void addBits(Vector<Comparable> VecVisible,int iBits)
  {
    String sBits="";
    if ((iBits&Global.cstTimer)>0)
      sBits+=g.getBegriffS("Checkbox","Timer3")+" ";
    if ((iBits&Global.cstLokalTimer)>0)
      sBits+=g.getBegriffS("Checkbox","Lokaltimer")+" ";
    if ((iBits&Global.cstBerechtigung)>0)
      sBits+=g.getBegriffS("Checkbox","Berechtigung2")+" ";
    if ((iBits&Global.cstDel)>0)
      sBits+=g.getBegriffS("Checkbox","altes")+" ";
    if ((iBits&Global.cstSperre)>0)
      sBits+=g.getBegriffS("Checkbox","Sperre2")+" ";
    if ((iBits&Global.cstBewBrtg)>0)
        sBits+=g.getBegriffS("Checkbox","berechtigbar")+" ";
    if ((iBits&Global.cstBewVoll)>0)
        sBits+=g.getBegriffS("Checkbox","BewVoll")+" ";
    if ((iBits&Global.cstBewBew)>0)
        sBits+=g.getBegriffS("Checkbox","BewBew")+" ";
    if ((iBits&Global.cstBewZone)>0)
        sBits+=g.getBegriffS("Checkbox","BewZone")+" ";
    if ((iBits&Global.cstNOA)>0)
        sBits+=g.getBegriffS("Checkbox","NOA")+" ";
    if ((iBits&Global.cstDefinition)>0)
      sBits+=g.getBegriffS("Checkbox","Definition")+" ";
    if ((iBits&Global.cstMandantIgnorieren)>0)
      sBits+=g.getBegriffS("Checkbox","Mandant ignorieren")+" ";
    if ((iBits&Global.cstNurModell)>0)
      sBits+=g.getBegriffS("Checkbox","nur Modell3")+" ";
    VecVisible.addElement(sBits);
    String sANR="";
    if ((iBits&Global.cstANR1)>0)
      sANR+="1";
    if ((iBits&Global.cstANR2)>0)
      sANR+="2";
    if ((iBits&Global.cstANR3)>0)
      sANR+="3";
    if ((iBits&Global.cstANR4)>0)
      sANR+="4";
    if ((iBits&Global.cstANR5)>0)
      sANR+="5";
    if ((iBits&Global.cstANR6)>0)
      sANR+="6";
    if ((iBits&Global.cstANR7)>0)
      sANR+="7";
    if ((iBits&Global.cstANR8)>0)
      sANR+="8";
    if ((iBits&Global.cstANR9)>0)
      sANR+="9";
    VecVisible.addElement(sANR);
    int iDD=iBits&Global.cstDD;
    VecVisible.addElement(g.getBegriffS("Radiobutton",iDD==Global.cstSK ? "DDsehrkurz":iDD==Global.cstK ? "DDkurz":iDD==Global.cstL ? "DDlang":iDD==Global.cstN ? "DDnormal":"DDnie"));
  }

  private void setSelected()
  {
    CbxTod.setEnabled(false);
    JCOutlinerNode Node=Out.getSelectedNode();
    if(Node!=null&&Node.getLevel()==1)
      iAktAIC=Sort.geti((Vector)Node.getUserData(),0);//((Integer)VecInvisible.elementAt(0)).intValue();
    else
      iAktAIC=0;
    ((JCOutlinerFolderNode)OutZ.getRootNode()).removeChildren();
    if(iAktAIC>0)
    {
      Tab = new Tabellenspeicher(g,"Select e.aic_Eigenschaft aic,e.kennung" + g.AU_Bezeichnung2("Eigenschaft", "e") +
                                                  " from Bew_Zuordnung z"+g.join("Eigenschaft","e","z","Eigenschaft") + g.join("Begriff", "e") +
                                                  " where Begriff.kennung='BewStamm' and z.aic_bewegungstyp="+iAktAIC, true);
      Vector VecVisible=(Vector)Node.getLabel();
      Vector VecInvisible=(Vector)Node.getUserData();
      TxtBezeichnung.setText((String)VecVisible.elementAt(0));
      TxtKennung.setText((String)VecVisible.elementAt(1));
      for (int i=0;i<iANRs;i++)
      {
        CboEig[i].Cbo.fillCbo(Tab, true);
        CboEig[i].setSelectedAIC(Sort.geti(VecInvisible,i+1));
      }

      Tabellenspeicher Tab2 = new Tabellenspeicher(g,"Select e.aic_Eigenschaft aic,e.kennung" + g.AU_Bezeichnung2("Eigenschaft", "e") +
                                                  " from Bew_Zuordnung z"+g.join("Eigenschaft","e","z","Eigenschaft") + g.join("Begriff", "e") +
                                                  " where Begriff.kennung='BewDatum2' and z.aic_bewegungstyp="+iAktAIC, true);
      for (int i=iANRs;i<iANRs+iLDATEs;i++)
      {
        CboEig[i].Cbo.fillCbo(Tab2, true);
        CboEig[i].setSelectedAIC(Sort.geti(VecInvisible,i+3));
      }

      Tabellenspeicher Tab3 = new Tabellenspeicher(g,"Select e.aic_Eigenschaft aic,e.kennung" + g.AU_Bezeichnung2("Eigenschaft", "e") +
                                                  " from Bew_Zuordnung z"+g.join("Eigenschaft","e","z","Eigenschaft") + g.join("Begriff", "e") +
                                                  " where Begriff.kennung like 'BewBool%' and z.aic_bewegungstyp="+iAktAIC, true);
      for (int i=iANRs+iLDATEs;i<iEigGes;i++)
      {
        CboEig[i].Cbo.fillCbo(Tab3, true);
        CboEig[i].setSelectedAIC(Sort.geti(VecInvisible,i+3));
      }
      
      Tabellenspeicher Tab4 = new Tabellenspeicher(g,"Select e.aic_Eigenschaft aic,e.kennung" + g.AU_Bezeichnung2("Eigenschaft", "e") +
              " from Bew_Zuordnung z"+g.join("Eigenschaft","e","z","Eigenschaft") + g.join("Begriff", "e") +
              " where Begriff.kennung like 'BewVon_Bis' and z.aic_bewegungstyp="+iAktAIC, true);
      for (int i=iANRs+iLDATEs+iBOOLs;i<iEigGes;i++)
      {
		CboEig[i].Cbo.fillCbo(Tab4, true);
		CboEig[i].setSelectedAIC(Sort.geti(VecInvisible,i+3));
      }

      int iBits=Sort.geti(VecInvisible,iANRs+1);//VecInvisible.elementAt(4)==null?0:((Integer)VecInvisible.elementAt(4)).intValue();
      CbxTimer.setSelected((iBits&Global.cstTimer)>0);
      CbxLokalTimer.setSelected((iBits&Global.cstLokalTimer)>0);
      CbxNurModell.setSelected((iBits&Global.cstNurModell)>0);
      CbxMandantIgnorieren.setSelected((iBits&Global.cstMandantIgnorieren)>0);
      CbxBerechtigung.setSelected((iBits&Global.cstBerechtigung)>0);
      CbxDel.setSelected((iBits&Global.cstDel)>0);
      CbxDef.setSelected((iBits&Global.cstDefinition)>0);
      CbxSperre.setSelected((iBits&Global.cstSperre)>0);
      CbxBerechtigbar.setSelected((iBits&Global.cstBewBrtg)>0);
      CbxBewVoll.setSelected((iBits&Global.cstBewVoll)>0);
      CbxBewBew.setSelected((iBits&Global.cstBewBew)>0);
      CbxBewZone.setSelected((iBits&Global.cstBewZone)>0);
      CbxNOA.setSelected((iBits&Global.cstNOA)>0);
      CbxANR1.setSelected((iBits&Global.cstANR1)>0);
      CbxANR2.setSelected((iBits&Global.cstANR2)>0);
      CbxANR3.setSelected((iBits&Global.cstANR3)>0);
      CbxANR4.setSelected((iBits&Global.cstANR4)>0);
      CbxANR5.setSelected((iBits&Global.cstANR5)>0);
      CbxANR6.setSelected((iBits&Global.cstANR6)>0);
      CbxANR7.setSelected((iBits&Global.cstANR7)>0);
      CbxANR8.setSelected((iBits&Global.cstANR8)>0);
      CbxANR9.setSelected((iBits&Global.cstANR9)>0);
      int iDD=iBits&Global.cstDD;
      RadSK.setSelected(iDD==Global.cstSK);
      RadK.setSelected(iDD==Global.cstK);
      RadN.setSelected(iDD==Global.cstN);
      RadL.setSelected(iDD==Global.cstL);
      RadNie.setSelected(iDD==Global.cstNIE);
      Memo.setText((String)VecInvisible.elementAt(iANRs+2));
      CbxTod.setSelected(SQL.getInteger(g,"select tod from bewegungstyp where aic_bewegungstyp=?",0,""+iAktAIC)==1);
      //OutZ.setVisible(true);
      setTitle(sFormularBezeichnung+" - "+(VecVisible.elementAt(0)));
      //OutZ.getRootNode().setLabel((String)VecVisible.elementAt(0));
      fillOutZ();
    }
    else
    {
      TxtBezeichnung.setText("");
      TxtKennung.setText("");
      Memo.setText("");
      for (int i=0;i<iANRs;i++)
      {
        CboEig[i].Cbo.Clear(true);
        CboEig[i].setSelectedAIC(0);
      }
      CbxTimer.setSelected(false);
      CbxLokalTimer.setSelected(false);
      CbxNurModell.setSelected(false);
      CbxMandantIgnorieren.setSelected(false);
      CbxBerechtigung.setSelected(false);
      CbxDel.setSelected(false);
      CbxDef.setSelected(false);
      CbxSperre.setSelected(false);
      CbxBerechtigbar.setSelected(false);
      CbxBewVoll.setSelected(false);
      CbxBewBew.setSelected(false);
      CbxBewZone.setSelected(false);
      CbxNOA.setSelected(false);
      RadN.setSelected(true);
      CbxANR1.setSelected(false);
      CbxANR2.setSelected(false);
      CbxANR3.setSelected(false);
      CbxANR4.setSelected(false);
      CbxANR5.setSelected(false);
      CbxANR6.setSelected(false);
      CbxANR7.setSelected(false);
      CbxANR8.setSelected(false);
      CbxANR9.setSelected(false);
      CbxTod.setSelected(false);
      //OutZ.setVisible(false);
    }
    TxtKennung.setEnabled(iAktAIC==0);
    g.progInfo("DefBew.setSelected="+iAktAIC);
  }

  private boolean zugeordnet(int iAic)
  {
    for (int i=0;i<iEigGes;i++)
      if (iAic==CboEig[i].getSelectedAIC())
        return true;
    return false;
  }

  private void fillOutZ()
  {
    JCOutlinerFolderNode NodP=(JCOutlinerFolderNode)OutZ.getRootNode();
    //NodP.removeChildren();
    Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from bew_zuordnung where aic_bewegungstyp="+iAktAIC+" order by reihenfolge",true);
    for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
    {
      int iAic=Tab.getI("aic_eigenschaft");
      int iPos=g.TabEigenschaften.getPos("aic",iAic);
      Vector<Object> VecVisible=new Vector<Object>();
      VecVisible.addElement(g.TabEigenschaften.getS(iPos,"Bezeichnung"));
      VecVisible.addElement(Tab.getInhalt("reihenfolge"));
      String sDt=g.TabEigenschaften.getS(iPos,"Datentyp");
      int iPosB=g.getPosBegriff("Datentyp",sDt);
      VecVisible.addElement(iPosB<0 ? sDt:g.getBegBez3(iPosB));
      JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodP);
      Nod.setUserData(Tab.getInhalt("aic_eigenschaft"));
      Image Gif = iPosB<0?null:g.LoadImage(g.TabBegriffe.getS(iPosB,"BildFile"));
      JCOutlinerNodeStyle Sty=g.getStyle(Gif);
      if (sDt.equals("BewStamm") || sDt.equals("BewDatum") || sDt.equals("BewDatum2") || sDt.equals("BewVon_Bis") || sDt.startsWith("BewBool"))
        Sty.setForeground(sDt.equals("BewDatum") || zugeordnet(iAic) ? Color.GREEN.darker():Color.RED);
      Nod.setStyle(Sty);
    }
    OutZ.folderChanged(NodP);
  }


}
