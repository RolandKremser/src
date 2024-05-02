package All_Unlimited.Hauptmaske;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Grunddefinition.DefAbfrage;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 22.04.2005</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class Abschluss extends Formular
{
  private Global g;
  private static Abschluss Self=null;
  private JCOutliner Out = new JCOutliner(new JCOutlinerFolderNode("Root"));
  private Tabellenspeicher TabAT;
  private ComboSort CboAbschluss;
  private AbfrageEingabe CboAbfrage;
  private JRadioButton RadJahr;
  private JRadioButton RadQuartal;
  private JRadioButton RadMonat;
  private JRadioButton RadSonst;
  private SpinBox SBxWeiter;
  private int iSttOld=-1;
  private Datum dt;
  private Datum dtAb;
  //private JButton BtnRefresh;
  //private JButton BtnNeu;
  private JButton BtnLoeschen;
  private int iAbschluss=0;
  private String [] s = {"Abschluss","SperrDatum","Abfrage","fixiert_von","fixiert_am","ab","weiter","Aic"};

  //private JButton BtnSpeichern;

  public static Abschluss get(Global rg)
  {
        return Self==null ? new Abschluss(rg):Self;
  }

  public static void free()
  {
        if (Self!=null)
        {
                Self.g.testInfo("Abschluss.free");
                Self.thisFrame.dispose();
                Self=null;
        }
  }

  private Abschluss(Global glob)
  {
        super("Abschluss",null,glob);
        g=glob;
        g.testInfo("Abschluss.create");
        //long lClock=Static.get_ms();
        Self=this;
        Build();
        RefreshAll();
  }

  private void Build()
  {
    for (int i=0;i<s.length;i++)
    {
      s[i]=g.getBegriffS("Show",s[i]);
    }
    Out.setColumnButtons(s);
    Out.setNumColumns(s.length);
    Out.setRootVisible(false);
    Out.setBackground(Color.white);
    getFrei("Outliner").add("Center",Out);

    JPanel PnlE = getFrei("Eingabe");
    PnlE.setLayout(new BorderLayout(2,2));
    JPanel PnlEingabe=new JPanel(new BorderLayout(2,2));
    JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
    g.addLabel2(Pnl,"Abschluss");
    g.addLabel2(Pnl,"Sperren_vor_Datum");
    g.addLabel2(Pnl,"Abfrage");
    g.addLabel2(Pnl,"Sperre_ab");
    g.addLabel2(Pnl,"Sperre_weiter");
    Pnl.add(new JPanel());
    PnlEingabe.add("West",Pnl);
    Pnl = new JPanel(new GridLayout(0,1,2,2));
    CboAbschluss=new ComboSort(g);
    CboAbschluss.setFont(g.fontStandard);
    CboAbfrage=new AbfrageEingabe(g,thisFrame,true,false);
    CboAbfrage.setFont(g.fontStandard);
    Pnl.add(CboAbschluss);
    dt=new Datum(g,"dd.MM.yyyy");
    dtAb=new Datum(g,"dd.MM.yyyy");
    RadJahr = g.getRadiobutton("Jahr");
    RadQuartal = g.getRadiobutton("Quartal");
    RadMonat = g.getRadiobutton("Monat");
    RadSonst = g.getRadiobutton("Tage");
    ButtonGroup RadGroup=new ButtonGroup();
    RadGroup.add(RadJahr);
    RadGroup.add(RadQuartal);
    RadGroup.add(RadMonat);
    RadGroup.add(RadSonst);
    SBxWeiter=new SpinBox(0,-4,30,1,Color.WHITE);
    Pnl.add(dt);
    Pnl.add(CboAbfrage);
    Pnl.add(dtAb);
    JPanel PnlWeiter=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
    PnlWeiter.add(RadJahr);
    PnlWeiter.add(RadQuartal);
    PnlWeiter.add(RadMonat);
    Pnl.add(PnlWeiter);
    PnlWeiter=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
    PnlWeiter.add(RadSonst);
    PnlWeiter.add(SBxWeiter);
    Pnl.add(PnlWeiter);
    PnlEingabe.add("Center",Pnl);
    PnlE.add("North",PnlEingabe);

    ActionListener AL=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s = ev.getActionCommand();
        if (s.equals("Refresh"))
          RefreshAll();
        else if (s.equals("Save"))
          Save();
        else if (s.equals("Del"))
          Loeschen();
        else if (s.equals("History"))
          History();
        else if (s.equals("Abfrage"))
        {
          int iAbf=CboAbfrage.getSelectedAIC();
          int iSttAbf=iSttOld;
          DefAbfrage.get(g,new ShowAbfrage(g,iAbf,Abfrage.cstHS_Filter),iSttAbf,null,true,0).show(false);
        }
        else if (s.equals("Jahr") || s.equals("Quartal") || s.equals("Monat") || s.equals("sonst"))
        {
        	SBxWeiter.setEnabled(s.equals("sonst"));
        	if (s.equals("sonst"))
        	{
        		if (SBxWeiter.getIntValue()<0)
        			SBxWeiter.setValue(0);
        	}
        	else
        		SBxWeiter.setValue(s.equals("Jahr") ? -4:s.equals("Quartal") ? -3:-2);
        }
        else if (s.equals("Beenden"))
          hide();
      }
    };
    g.RadAdd(RadJahr,"Jahr",AL);
    g.RadAdd(RadQuartal,"Quartal",AL);
    g.RadAdd(RadMonat,"Monat",AL);
    g.RadAdd(RadSonst,"sonst",AL);
    g.BtnAdd(getButton("Tb_Refresh"),"Refresh",AL);
    g.BtnAdd(getButton("Tb_Speichern"),"Save",AL);
    BtnLoeschen = g.BtnAdd(getButton("Tb_Loeschen"),"Del",AL);
    g.BtnAdd(getButton("History"),"History",AL);
    g.BtnAdd(getFormularButton("Abfrage"),"Abfrage",AL);
    g.BtnAdd(getButton("Beenden"),"Beenden",AL);

    Out.addItemListener(new JCOutlinerListener()
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
                  setEingabe();
                  EnableButtons();
                }
        });


    CboAbschluss.addItemListener(new ItemListener()
                {
                  public void itemStateChanged(ItemEvent ev) {
                    if(ev.getStateChange() == ItemEvent.SELECTED) {
                      fillStamm(false);
                    }
                  }
                });

  }

  private void fillStamm(boolean bImmer)
  {
    int iStt=TabAT.posInhalt("aic_abschlusstyp",CboAbschluss.getSelectedAIC()) ? TabAT.getI("aic_stammtyp"):0;
    if (bImmer || iStt != iSttOld)
    {
      iSttOld=iStt;
      /*Tabellenspeicher Tab=new Tabellenspeicher(g,*/ String s="select aic_abfrage,begriff.kennung"+g.AU_Begriff()+" Bezeichnung,bits from abfrage"+g.join("begriff","abfrage")+
                                                " where"+g.bit("bits",Abfrage.cstBerechtigung)+" and aic_stammtyp="+iStt+g.getReadMandanten("begriff");//,true);
      /*if (g.MS())
        for (Tab.moveFirst(); !Tab.eof(); )
          if ((Tab.getL("bits") & Abfrage.cstBerechtigung) > 0)
            Tab.moveNext();
          else
            Tab.clearInhalt();*/
      CboAbfrage.fillCbo(s,"Abfrage",true);
      //CboStamm.fillStammTable(iStt, false);
    }
  }

  private void History()
  {
    String sAuto=g.getBegriffS("Show","auto");
    int iAuto=g.getCodeAic("Anlage","Abschluss");
    new Tabellenspeicher(g,"select a.aic_abschluss aic,a.Datum '"+s[1]+"'"+g.AU_BezeichnungFo("Abschlusstyp","a")+" '"+s[0]+"',(select defbezeichnung from begriff"+g.join2("abfrage","begriff")+
                         " where aic_abfrage=a.aic_abfrage) '"+s[2]+"',(case when p.aic_code="+iAuto+" then '"+sAuto+"' else benutzer.kennung end) '"+s[3]+"'"+
                         ",p.Timestamp '"+g.getBegriffS("Show","erstellt")+"',(select p2.Timestamp from protokoll p2 where a.pro_aic_protokoll=p2.aic_protokoll) '"+g.getBegriffS("Show","entfernt")+"'"+
                         g.AU_Bezeichnung1("Mandant","a")+" '"+g.getBegriffS("Show","Mandant")+"',a.ab '"+s[5]+"' from abschluss a join protokoll p on a.aic_protokoll=p.aic_protokoll"+g.join("logging","p")+
                         " join benutzer on benutzer.aic_benutzer=logging.aic_benutzer where a.aic_abschlusstyp="+CboAbschluss.getSelectedAIC()+g.getReadMandanten("a")+
                         " order by p.timestamp desc",true).showGrid();
  }

  private void RefreshAll()
  {
    CboAbschluss.fillDefTable("Abschlusstyp",false);
    TabAT=new Tabellenspeicher(g,"select aic_abschlusstyp,aic_stammtyp,bits,kennung"+g.AU_BezeichnungFo("abschlusstyp")+"Bezeichnung from abschlusstyp",true);
    fillStamm(true);
    Refresh();
  }

  private void Refresh()
  {
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    String sAuto=g.getBegriffS("Show","auto");
    int iAuto=g.getCodeAic("Anlage","Abschluss");
    String s="select a.aic_abschluss,a.aic_abschlusstyp,a.Datum,a.aic_abfrage,(case when p.aic_code="+iAuto+" then '"+sAuto+"' else benutzer.kennung end) kennung,"+
      "p.Timestamp,a.ab,a.weiter from Abschluss a join protokoll p on a.aic_protokoll=p.aic_protokoll"+
      g.join("logging","p")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer where pro_aic_protokoll is null"+g.getReadMandanten("a");
    //g.fixInfo(s);
    Tabellenspeicher Tab=new Tabellenspeicher(g,s,true);
    g.checkAbfragen();
    for(;!Tab.eof();Tab.moveNext())
    {
            Vector<Object> VecVisible=new Vector<Object>();
            VecVisible.addElement(TabAT.posInhalt("aic_abschlusstyp",Tab.getI("aic_abschlusstyp"))?TabAT.getS("Bezeichnung"):"Abschluss "+Tab.getI("aic_abschlusstyp"));
            VecVisible.addElement(new Zeit(Tab.getDate("Datum"),"dd.MM.yyyy"));
            int iPos=TabAT.getI("aic_stammtyp")==0 ? -1:g.TabAbfragen.getPos("aic_abfrage",Tab.getI("aic_abfrage"));
            int iB=iPos<0 ? 0:g.TabAbfragen.getI(iPos,"aic_begriff");
            //VecVisible.addElement(TabAT.getI("aic_stammtyp")==0 ? Static.sKein:SQL.getString(g,"select defbezeichnung from abfrage"+g.join("begriff","abfrage")+" where aic_abfrage="+Tab.getI("aic_abfrage")));
            VecVisible.addElement(iB==0 ? Static.sKein:g.getBegBez(iB));
            VecVisible.addElement(Tab.getS("Kennung"));
            VecVisible.addElement(new Zeit(Tab.getDate("Timestamp"),"dd.MM.yyyy"));
            VecVisible.addElement(new Zeit(Tab.getDate("ab"),"dd.MM.yyyy"));
            VecVisible.addElement(Tab.getI("weiter"));
            VecVisible.addElement(Tab.getInt("aic_abschluss"));
            JCOutlinerNode Nod=new JCOutlinerFolderNode((Object)VecVisible,NodeRoot);
            Nod.setUserData(Tab.getInt("aic_abschluss"));
            if (iAbschluss>0 && iAbschluss==Tab.getI("aic_abschluss"))
              Out.selectNode(Nod,null);
    }
    Out.folderChanged(NodeRoot);
  }

  private void Loeschen()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    if (Nod != null && Nod.getLevel()==1)
    {
      int iMessage = new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Loeschen", new String[]{"" + Nod.getLabel()});
      if (iMessage == Message.YES_OPTION)
      {
        int iProt = g.Protokoll("Eingabe", 0);
        g.exec("update abschluss set pro_aic_protokoll=" + iProt + " where pro_aic_protokoll is null and aic_abschluss=" + Nod.getUserData());
        AClient.send_AServer("abschluss",g);
        g.sendWebChanged("periodenSperre");
        Refresh();
      }
    }
  }

  private void Save()
  {
    int iProt = g.Protokoll("Eingabe",0);
    SQL Qry=new SQL(g);
    int iTyp=CboAbschluss.getSelectedAIC();
    boolean bStamm=TabAT.posInhalt("aic_abschlusstyp",iTyp)?TabAT.getI("aic_stammtyp")>0:false;
    Qry.exec("update abschluss set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and (aic_mandant is null or aic_mandant="+g.getMandant()+")"+
             " and aic_abschlusstyp="+iTyp+(bStamm && CboAbfrage.getSelectedAIC()>0?" and aic_Abfrage="+CboAbfrage.getSelectedAIC():""));
    Qry.add("aic_Abschlusstyp",iTyp);
    if (bStamm)
      Qry.add0("aic_Abfrage",CboAbfrage.getSelectedAIC());
    Qry.add("aic_protokoll",iProt);
    Qry.add("Datum",dt.getDate());
    Qry.add("Ab",dtAb.getDate());
    Qry.add("weiter",SBxWeiter.getIntValue());
    Qry.add("AIC_Mandant",g.getMandant());
    iAbschluss=Qry.insert("abschluss",true);
    Refresh();
    AClient.send_AServer("abschluss",g);
    g.sendWebChanged("periodenSperre");
  }

  private void setEingabe()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    if (Nod!=null && Nod.getLevel()>0)
    {
      iAbschluss=Sort.geti(Nod.getUserData());
      g.progInfo("setEingabe:" + iAbschluss);
      Tabellenspeicher Tab = new Tabellenspeicher(g, "select * from abschluss where aic_abschluss=" + iAbschluss, true);
      CboAbschluss.setSelectedAIC(Tab.getI("aic_abschlusstyp"));
      dt.setDate(Tab.getD("Datum"));
      dtAb.setDate(Tab.getD("Ab"));
      int iWeiter=Tab.getI("weiter");
      SBxWeiter.setIntValue(iWeiter);
      if (iWeiter==-4)
    	  RadJahr.setSelected(true);
      else if (iWeiter==-3)
    	  RadQuartal.setSelected(true);
      else if (iWeiter==-2)
    	  RadMonat.setSelected(true);
      else
    	  RadSonst.setSelected(true);
      SBxWeiter.setEnabled(iWeiter>=0);
      CboAbfrage.setSelectedAIC(Tab.getI("aic_Abfrage"));
    }
  }

  private void EnableButtons()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    int iLevel= Nod==null ? 0:Nod.getLevel();
    if (BtnLoeschen != null)
      BtnLoeschen.setEnabled(iLevel==1);
    /*if (iLevel==1)
    {
      g.progInfo("UserData="+Nod.getUserData());
    }*/
  }

}
