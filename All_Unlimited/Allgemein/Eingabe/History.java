package All_Unlimited.Allgemein.Eingabe;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import jclass.bwt.JCOutlinerFolderNode;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutlinerEvent;
import java.util.Vector;
import java.util.Date;
import jclass.bwt.JCOutlinerNode;
//import java.awt.Color;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Message;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: All Unlimited</p>
 *
 * <p>Copyright: Copyright (c) 2003</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 3.12
 */
public class History extends Formular
{
  private Global g;
  private JButton BtnOk;
  private JButton BtnCancel;
  private JButton BtnSave;
  private JButton BtnRefresh;
  private JButton BtnNew;
  private JButton BtnDel;
  private JButton BtnTake;
  private Tabellenspeicher Tab;
  private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
  private EF_Eingabe EF;
  private Datum DtEin;
  private Datum DtAus;
  private int iEig;
  private int iStamm;
  private String sDt;
  private boolean bNeu=false;
  private boolean bLast=false;
  private boolean bFirst=false;
  private boolean bSProt=false;

  public History(Global rg,JFrame Fom,String sBez,int riEig,int riStamm,String rsDt,Component Edt)
  {
    super("History",Fom,rg);
    g=rg;
    iEig=riEig;
    iStamm=riStamm;
    sDt=rsDt;
    setTitle(getTitle());
    if (Edt==null)
    {
      Edt = new Text("",60);
      sBez="Bezeichnung";
    }
    EF = new EF_Eingabe(g, Edt, "",sBez, true,false,null,"");
    //Tab=new Tabellenspeicher(g,ShowAbfrage.SQL_History(g,iEig,iStamm,sDt,-1),true);
    Build();
    show();
  }

  private Tabellenspeicher getTab()
  {
    String s=null;
    if (sDt.equals("Hierarchie") || sDt.equals("Gruppe"))
      s="select aic_stammpool aic,sta_aic_stamm,Bezeichnung,aic_stammtyp,gultig_von ein,gueltig_bis aus,null status from stammpool p join stammview2 s on p.sta_aic_stamm=s.aic_stamm"+
          " where pro2_aic_protokoll is null and aic_rolle is null and p.aic_stamm="+iStamm+" and aic_eigenschaft="+iEig+" order by "+g.orderD("ein")+" desc";
    else if (sDt.equals("Boolean") || sDt.equals("Double"))
      s="select aic_stammpool aic,spalte_double,gultig_von ein,gueltig_bis aus,null status from stammpool p"+
          " where pro2_aic_protokoll is null and p.aic_stamm="+iStamm+" and aic_eigenschaft="+iEig+" order by "+g.orderD("ein")+" desc";
    else if (sDt.equals("Bezeichnung") || sDt.equals("Eintritt") || sDt.equals("Austritt"))
    {
      s="select aic_protokoll aic,Bezeichnung,Eintritt ein,Austritt aus,null status from stamm_protokoll"+
          " where pro_aic_protokoll is null and aic_rolle"+(iEig==0?" is null":"="+iEig)+" and aic_stamm="+iStamm+" order by "+g.orderD("ein")+" desc";
      bSProt=true;
    }
    return new Tabellenspeicher(g,s,true);
  }

  private void fillOut()
  {
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    JCOutlinerNode NodSelect=null;
    for(Tab.moveFirst();!Tab.out();Tab.moveNext())
    {
      String sStatus=Tab.getS("status");
      if (sStatus==null || !sStatus.equals("del"))
      {
        Vector<Object> VecSpalten = new Vector<Object>();
        VecSpalten.addElement(sDt.equals("Double") ? Tab.getF("spalte_double"):sDt.equals("Boolean") ? Static.JaNein(Tab.getB("spalte_double")):Tab.getS("Bezeichnung"));
        VecSpalten.addElement(new Zeit(Tab.getDate("ein"), "dd.MM.yyyy"));
        VecSpalten.addElement(new Zeit(Tab.getDate("aus"), "dd.MM.yyyy"));
        JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
        Node.setUserData(Tab.getInt("aic"));
        if (NodSelect==null)
          NodSelect=Node;
        if (sStatus != null && !sStatus.equals(""))
        {
          JCOutlinerNodeStyle Sty = new JCOutlinerNodeStyle();
          Sty.setForeground(sStatus.equals("edit") ? Global.ColAendern:Global.ColNeu);
          Node.setStyle(Sty);
        }
      }
    }
    Out.folderChanged(NodeRoot);
    BtnNew.setEnabled(true);
    BtnDel.setEnabled(NodSelect!=null);
    BtnTake.setEnabled(NodSelect!=null);
    Out.selectNode(NodSelect==null ? Out.getRootNode():NodSelect);
    if (NodSelect!=null)// && DtStichtag!=null)
      setData();
  }

  private void Build()
  {
    //Tab.showGrid(Out);
    String [] s = new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","von"),g.getBegriffS("Show","bis")};
    BtnOk=getButton("Ok");
    BtnCancel=getButton("Abbruch");
    BtnSave=getButton("Speichern");
    BtnRefresh=getButton("Refresh");
    BtnNew=getButton("Neu");
    BtnDel=getButton("Loeschen");
    BtnTake=getButton("Uebernehmen");

    Out.setColumnButtons(s);
    Out.setNumColumns(s.length);
    Out.setRootVisible(false);
    DtEin=new Datum(g,"dd.MM.yyyy");
    DtAus=new Datum(g,"dd.MM.yyyy");
    Refresh();
    getFrei("Outliner").add(Out);
    JPanel PnlEingabe=getFrei("Eingabe");
    int iSpalten=((GridLayout)PnlEingabe.getLayout()).getColumns();
    JPanel Pnl=new JPanel(new GridLayout(0,iSpalten==0?1:iSpalten));
    Pnl.add(EF);
    JPanel PnlEinAus=new JPanel(new GridLayout(1,2));
    PnlEinAus.add(new EF_Eingabe(g, DtEin, "","Stichtag", true,false,null,""));
    PnlEinAus.add(new EF_Eingabe(g, DtAus, "","bis", true,false,null,""));
    Pnl.add(PnlEinAus);
    if (g.Def())
     PnlEingabe.addMouseListener(new MouseListener()
     {
      public void mousePressed(MouseEvent ev)
      {}

      public void mouseClicked(MouseEvent ev)
      {
        if(ev.getModifiers()==MouseEvent.BUTTON3_MASK && ev.getClickCount()==3)
          Tab.showGrid("History",thisFrame);
      }

      public void mouseEntered(MouseEvent ev)
      {}

      public void mouseExited(MouseEvent ev)
      {}

      public void mouseReleased(MouseEvent ev)
      {}
     });
    PnlEingabe.setLayout(new BorderLayout());
    PnlEingabe.add("North",Pnl);

    ActionListener AL=new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          Object Btn = ev.getSource();
          if (Btn == BtnOk)
          {
            Save();
            saveFenster();
            thisFrame.dispose();
          }
          else if (Btn == BtnCancel)
          {
            saveFenster();
            thisFrame.dispose();
          }
          else if (Btn == BtnSave)
          {
            Save();
            Refresh();
          }
          else if (Btn == BtnRefresh)
            Refresh();
          else if (Btn == BtnNew)
            New();
          else if (Btn == BtnDel)
            Del();
          else if (Btn == BtnTake)
            Take();
        }
      };
      if (BtnOk != null) BtnOk.addActionListener(AL);
      if (BtnCancel != null) BtnCancel.addActionListener(AL);
      if (BtnRefresh != null) BtnRefresh.addActionListener(AL);
      if (BtnSave != null)
      {
        BtnSave.addActionListener(AL);
        BtnSave.setEnabled(false);
      }
      BtnNew.addActionListener(AL);
      BtnDel.addActionListener(AL);
      BtnTake.addActionListener(AL);

      Out.addItemListener(new JCOutlinerListener()
        {
          public void itemStateChanged(JCItemEvent e) {}
          public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {}
          public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {}
          public void outlinerNodeSelectBegin(JCOutlinerEvent e) {}

          public void outlinerNodeSelectEnd(JCOutlinerEvent e) {
            setData();
            //DtStichtag.setDate(Out.getLabel()
          }
        });
        KeyListener KL=new KeyListener()
        {
               public void keyPressed(KeyEvent ev)
               {
               }
               public void keyReleased(KeyEvent ev)
               {
                 int iK=ev.getKeyCode();
                 if (iK==KeyEvent.VK_ENTER)
                   Take();
               }
               public void keyTyped(KeyEvent ev)
               {
               }
        };
        DtEin.getDatumEditor().addKeyListener(KL);
        DtAus.getDatumEditor().addKeyListener(KL);
  }

  private void setData()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    if (Nod.getLevel()==0)
      return;
    bNeu=false;
    BtnNew.setEnabled(true);
    BtnDel.setEnabled(true);
    BtnTake.setEnabled(true);
    //g.progInfo("bNeu="+bNeu);
    Tab.posInhalt("aic",Nod.getUserData());
    Component C=EF.getComp();
    if (C instanceof HierarchieEingabe)
      ((HierarchieEingabe)C).setValue(Tab.getI("aic_stammtyp"),Tab.getI("Sta_aic_Stamm"));
    else  if (C instanceof AUComboList)
      ((AUComboList)C).getComboBox().setSelectedAIC(Tab.getI("sta_aic_Stamm"));
    else  if (C instanceof Zahl)
      ((Zahl)C).setValue(Tab.getF("spalte_double"));
    else  if (C instanceof AUCheckBox)
      ((AUCheckBox)C).setSelected(Tab.getB("spalte_double"));
    else  if (bSProt)
      ((Text)C).setText(Tab.getS("Bezeichnung"));
    DtEin.setDate(Tab.getDate("ein"));
    DtAus.setDate(Tab.getDate("aus"));
  }

  private void Save()
  {
    int iProt=g.Protokoll("Eingabe",getBegriff());
    for (Tab.moveFirst();!Tab.out();Tab.moveNext())
    {
      String sStatus=Tab.getS("status");
      if (sStatus != null && !sStatus.equals(""))
      {
        if (sStatus.equals("del") || sStatus.equals("edit"))
          if (bSProt)
          {
            g.exec("update stamm_protokoll set pro_aic_protokoll=" + iProt + " where aic_stamm=" + iStamm + Abfrage.Rolle(iEig) + " and aic_protokoll=" + Tab.getI("aic"));
            g.exec("update stamm_protokoll set weg=null where aic_stamm=" + iStamm + Abfrage.Rolle(iEig)+" and weg="+g.DateTimeToString(Tab.getDate("ein")));
          }
          else
            g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where aic_stammpool="+Tab.getI("aic"));
        Component C=EF.getComp();
        boolean b=C instanceof AUCheckBox;
        if (!sStatus.equals("del") && (Tab.exists("sta_aic_stamm") && Tab.getI("sta_aic_stamm")>0 || Tab.exists("spalte_double") && (b && Tab.getB("spalte_double") || Tab.getF("spalte_double")!=0)))
        {
          SQL Qry=new SQL(g);
          Qry.add("Aic_Protokoll",iProt);
          Qry.add("Aic_Stamm",iStamm);
          Qry.add("Aic_Eigenschaft",iEig);
          if (Tab.exists("sta_aic_stamm"))
            Qry.add("STA_Aic_Stamm",Tab.getI("sta_aic_stamm"));
          else if (Tab.exists("spalte_double"))
        	if (b)
        		Qry.add("spalte_double",Tab.getB("spalte_double"));
        	else
        		Qry.add("spalte_double",Tab.getF("spalte_double"));
          Qry.add("GULTIG_VON",Tab.getDate("ein"));
          Qry.add("GUELTIG_BIS",Tab.getDate("aus"));
          Qry.insert("StammPool",false);
        }
      }
    }
    if (BtnSave != null) BtnSave.setEnabled(false);
  }

  private void Refresh()
  {
    Tab=getTab();
    Tab.bInsert=true;
    if (Tab.isEmpty())
      bNeu=true;
    fillOut();
    if (BtnSave != null) BtnSave.setEnabled(false);
  }

  private void New()
  {
    BtnTake.setEnabled(true);
    BtnNew.setEnabled(false);
    //Out.selectNode(Out.getRootNode());
    bNeu=true;
    //g.progInfo("bNeu="+bNeu);
    DtEin.setDate(null);
    DtEin.requestFocus();
    DtAus.setDate(null);
  }
  private void Del()
  {
    Tab.setInhalt("status","del");
    if (!bSProt)
    {
      Date dt = Tab.getDate("aus");
      next();
      if (!Tab.out())
      {
        Tab.setInhalt("status", "edit");
        Tab.setInhalt("aus", dt);
      }
    }
    if (BtnSave != null) BtnSave.setEnabled(true);
    fillOut();
  }

  private boolean DateOk()
  {
    Date dt=DtEin.getDate();
    if (dt==null)
    {
      dt = Static.dt1970;
      DtEin.setDate2(dt);
    }
      //return false;
    boolean b= Tab.isNull("aus") || dt.before(Tab.getDate("aus"));
    if (b)
    {
      Tab.push();
      next();
      bLast=bNeu && Tab.out();
      if (!bLast)
        b=Tab.isNull("ein") || dt.after(Tab.getDate("ein"));
      Tab.pop();
    }
    else if (Tab.getPos()==0) // Erste Zeile
    {
      bFirst=true;
      b = true;
    }
    if (bLast)
      bLast=!Tab.isNull("ein") && dt.before(Tab.getDate("ein"));
    return b;
  }

  private void next()
  {
    Tab.moveNext();
      while(!Tab.out() && Tab.getS("status").equals("del"))
        Tab.moveNext();
  }

  private void Take()
  {
    if (!DateOk())
    {
      new Message(Message.WARNING_MESSAGE, (JDialog)thisFrame, g,5).showDialog("Datum_fehlerhaft");
      return;
    }
    String sStatus=Tab.getS("status");
    if (bNeu)
    {
      if (bLast)
        Tab.bInsert=false;
      Tab.newLine();
      Tab.setInhalt("status", "new");
    }
    else if (sStatus == null || sStatus.equals(""))
      Tab.setInhalt("status","edit");
    Component C=EF.getComp();
    if (C instanceof HierarchieEingabe)
    {
      Tab.setInhalt("sta_aic_stamm", ((HierarchieEingabe)C).getValueStamm());
      Tab.setInhalt("aic_stammtyp",((HierarchieEingabe)C).getValueStt());
      Tab.setInhalt("Bezeichnung",((HierarchieEingabe)C).getCombo().getBezeichnung());
    }
    else  if (C instanceof Zahl)
    {
      Tab.setInhalt("spalte_double",((Zahl)C).getDouble());
    }
    else  if (C instanceof AUCheckBox)
    {
      Tab.setInhalt("spalte_double",((AUCheckBox)C).isSelected());
    }
    else  if (C instanceof AUComboList)
    {
      Tab.setInhalt("sta_aic_stamm", ((AUComboList)C).getAIC());
      Tab.setInhalt("Bezeichnung",((AUComboList)C).getComboBox().getSelectedBezeichnung());
    }
    if (DtAus.Modified())
    {
      Tab.setInhalt("aus", DtAus.getDate());
      Tab.setInhalt("status", "edit");
    }
    if (DtEin.Modified())
    {
      Tab.setInhalt("ein", DtEin.getDate());
      if (bNeu && Tab.getPos()>0)
      {
        //g.testInfo("andere Gültigkeit übernehmen:"+Tab.getPos()+"/"+Tab.getDate("gultig_von"));
        Tab.movePrevious();
        Date dt=Tab.getDate("ein");
        //g.testInfo("danach:"+Tab.getPos()+"/"+dt);
        Tab.moveNext();
        Tab.setInhalt("aus",dt);
      }
      next();
      if (!Tab.out() && !bFirst)
      {
        Tab.setInhalt("status", "edit");
        Tab.setInhalt("aus", DtEin.getDate());
      }
    }
    bNeu=false;
    bLast=false;
    bFirst=false;
    Tab.bInsert=true;
    if (BtnSave != null) BtnSave.setEnabled(true);
    fillOut();
    //Tab.showGrid("History",thisFrame);
  }

}
