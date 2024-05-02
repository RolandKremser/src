package All_Unlimited.Grunddefinition;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
//import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Eingabe.Text;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import javax.swing.JPopupMenu;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Component;



/**
 * <p>Überschrift: </p>
 * <p>Beschreibung: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Organisation: </p>
 * @author unbekannt
 * @version 1.0
 */

public class CleanComputer extends Formular
{
  private Global g;
  private static CleanComputer Self=null;
  private AUOutliner Out;
  private JButton BtnEdit;
  private JButton BtnDelete;
  private JButton BtnUndelete;
  private int iAic=0;
  private int iBits=0;
  private String sKennung="";
  private boolean bDel=false;
  private int iPosDel=12;
  private Tabellenspeicher Tab=null;
  private JPopupMenu popup=null;

  public static CleanComputer get(Global rg)
  {
    if (Self==null) new CleanComputer(rg);
    Self.show();
    return Self;
  }

  public static void free()
  {
    if (Self!=null)
    {
      Self.g.winInfo("CleanComputer.free");
      Self.thisFrame.dispose();
      Self=null;
    }
  }

  public void show()
  {
        Refresh();
        super.show();
  }

  public CleanComputer(Global rg)
  {
    super("CleanComputer",null,rg);
    g=rg;
    Self=this;
    g.winInfo("CleanComputer.create");
    Out=new AUOutliner();
    Out.setRootNode(new JCOutlinerFolderNode(""));
    Out.setColumnLabelSortMethod(Sort.sortMethod);
    Out.setRootVisible(false);
    Out.setFont(g.fontBezeichnung);
    String[] sAry=new String[] {"Aic","Kennung","Hostname","Benutzer","IP_Adresse","MAC_Adresse","OS","OS-Ver","Anlage","Java","Version","zuletzt","entfernt","Fensterpos","Parameter"};
    for (int i=0;i<sAry.length;i++)
      sAry[i]=g.getBegriffS("Show", sAry[i]);
    Out.setColumnButtons(new jclass.util.JCVector(sAry));
    Out.setNumColumns(sAry.length);
    Out.setRootNode(new JCOutlinerFolderNode("Stammtyp - Stamm"));
    JPanel Pnl_Outliner = getFrei("Outliner");
    if (Pnl_Outliner != null)
      Pnl_Outliner.add(Out);

    ActionListener AL = new ActionListener()
        {
          @SuppressWarnings("unchecked")
          public void actionPerformed(ActionEvent e)
          {
            String s = e.getActionCommand();
            g.progInfo("Action="+s);
            if (s.equals("Refresh"))
              Refresh();
            else if (s.equals("Edit"))
              Edit();
            else if (s.equals("Loeschen"))
              Delete();
            else if (s.equals("Undelete"))
              Undelete();
            else if (s.equals("Export"))
            {
              /*JFileChooser FC = new JFileChooser();
              FC.setMultiSelectionEnabled(false);
              if (JFileChooser.APPROVE_OPTION == FC.showDialog(null, "ok"))
                Tab.writeFile(';', "" + FC.getSelectedFile());*/
              Tab.Export();
            }
            else if (s.equals("DelFensterpos"))
            {
              String[] sDel = new String[] {g.getShow("Fensterpos")+" "+g.getShow("fuer")+" "+g.getShow("Computer")+" "+sKennung};
              if(new Message(Message.YES_NO_OPTION,thisJFrame(),g).showDialog("Loeschen",sDel)==Message.YES_OPTION)
              {
                g.diskInfo(g.exec("delete from fensterpos where aic_logging in (select aic_logging from logging where aic_computer=" + iAic + ")") + " Fensterpos von "+sKennung+" gelöscht");
                ((Vector)Out.getSelectedNode().getLabel()).setElementAt(Static.Int0, 13);
                Out.folderChanged(Out.getRootNode());
              }
            }
            else if (s.equals("DelParameter"))
            {
              String[] sDel = new String[] {g.getShow("Parameter")+" "+g.getShow("fuer")+" "+g.getShow("Computer")+" "+sKennung};
              if(new Message(Message.YES_NO_OPTION,thisJFrame(),g).showDialog("Loeschen",sDel)==Message.YES_OPTION)
              {
                g.diskInfo(g.exec("delete from parameter where aic_computer=" + iAic) + " Parameter  von Computer "+sKennung+" gelöscht");
                ((Vector)Out.getSelectedNode().getLabel()).setElementAt(Static.Int0, 14);
                Out.folderChanged(Out.getRootNode());
              }
            }
            else if (s.equals("Bits"))
              ShowComputerBits();
            else if (s.equals("Parameter"))
              new Tabellenspeicher(g,"select h.kennung Hauptgruppe,n.kennung Nebengruppe,(select kennung from Benutzer where aic_Benutzer=p.aic_Benutzer) Benutzer,p.Parameterzeile Parameter,"+g.int1_int4()+
                                   ",(select ein from logging where aic_logging=p.aic_logging) Datum"+
                                   " from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe"+
                                   " where aic_computer=" + iAic,true).showGrid("Parameter für "+iAic);
          }
        };
        popup= new JPopupMenu();
        g.addMenuItem("Edit", popup,null,AL);
        g.addMenuItem("Parameter", popup,null,AL);
        g.addMenuItem("Loeschen", popup,null,AL);
        g.addMenuItem("Undelete", popup,null,AL);
        popup.addSeparator();
        g.addMenuItem("Refresh", popup,null,AL);
        g.addMenuItem("Bits", popup,null,AL);
        g.addMenuItem("Export", popup,null,AL);
        popup.addSeparator();
        g.addMenuItem("DelFensterpos", popup,null,AL);
        g.addMenuItem("DelParameter", popup,null,AL);

    g.BtnAdd(getButton("Refresh"),"Refresh",AL);
    BtnEdit=g.BtnAdd(getButton("Edit"),"Edit",AL);
    BtnDelete=g.BtnAdd(getButton("Loeschen"),"Loeschen",AL);
    BtnUndelete = g.BtnAdd(getButton("Undelete"),"Undelete",AL);
    g.BtnAdd(getButton("Export"),"Export",AL);
    g.BtnAdd(getButton("DelFensterpos"),"DelFensterpos",AL);
    g.BtnAdd(getButton("DelParameter"),"DelParameter",AL);
    g.BtnAdd(getButton("Bits"),"Bits",AL);

    JButton BtnBeenden = getButton("Beenden");
    if (BtnBeenden != null)
    {
      Action cancelKeyAction = new AbstractAction()
      {
        /**
		 *
		 */
		private static final long serialVersionUID = -420035845722261715L;

		public void actionPerformed(ActionEvent e) {
          hide();
        }
      };
      BtnBeenden.addActionListener(cancelKeyAction);
      Static.Escape(BtnBeenden,thisFrame,cancelKeyAction);
    }

    Out.addItemListener(new JCItemListener()
    {
      public void itemStateChanged(JCItemEvent ev)
      {
        JCOutlinerNode Nod=Out.getSelectedNode();
        Vector Vec=(Vector)Nod.getUserData();
        iAic=((Integer)Vec.elementAt(0)).intValue();
        iBits=Vec.elementAt(1)==null ? 0:((Integer)Vec.elementAt(1)).intValue();
        sKennung=(String)((Vector)Nod.getLabel()).elementAt(1);
        bDel=!((String)((Vector)Nod.getLabel()).elementAt(iPosDel)).equals("");
        EnableButtons();
      }
    });
    Out.getOutliner().addMouseListener(new MouseListener()
    {
      public void mousePressed(MouseEvent ev)
      {}

      public void mouseClicked(MouseEvent ev)
      {
        //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
        if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM) && g.Def())
          popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
      }

      public void mouseEntered(MouseEvent ev)
      {}

      public void mouseExited(MouseEvent ev)
      {}

      public void mouseReleased(MouseEvent ev)
      {}
    });

  }

  private void Edit()
  {
    final JDialog Dlg = new JDialog((JFrame)thisFrame,"Edit",true);
    JButton BtnOk = g.getButton("Ok");
    JButton BtnAbbruch = g.getButton("Abbruch");
    final Text Edt=new Text(sKennung,12);
    final JCheckBox CbxInfo1 = g.getCheckbox("Info1",(iBits&Transact.INFO1)>0);
    final JCheckBox CbxInfo2 = g.getCheckbox("Info2",(iBits&Transact.INFO2)>0);
    final JCheckBox CbxInfo3 = g.getCheckbox("Info3",(iBits&Transact.INFO3)>0);
    final JCheckBox CbxInfo4 = g.getCheckbox("Info4",(iBits&Transact.EXEC)>0);
    final JCheckBox CbxFehlende = g.getCheckbox("fehlendes",(iBits&Transact.FEHLENDE)>0);
    final JCheckBox CbxInfo = g.getCheckbox("Info",(iBits&Transact.INFO)>0);
    final JCheckBox CbxTerminal = g.getCheckbox("Terminal",(iBits&Transact.TERMINAL)>0);
    final JCheckBox CbxClock = g.getCheckbox("Clock",(iBits&Transact.CLOCK)>0);
    final JCheckBox CbxNoCache = g.getCheckbox("no Cache",(iBits&Transact.NO_CACHE)>0);
    //final JCheckBox CbxFill = g.getCheckbox("vorfuellen",(iBits&Transact.FILL)>0);
    final JCheckBox CbxNoInfo = g.getCheckbox("no Info",(iBits&Transact.NO_INFO)>0);
    final JCheckBox CbxNoSpeed = g.getCheckbox("no Speed",(iBits&Transact.NO_SPEED)>0);
    final JCheckBox CbxClockSub = g.getCheckbox("ClockSub",(iBits&Transact.CLOCKSUB)>0);
    //final JCheckBox CbxLDATE = g.getCheckbox("LDATE",(iBits&g.LDATE)>0);
    final JCheckBox CbxFastOut = g.getCheckbox("fast out",(iBits&Transact.FAST_OUT)>0);
    final JCheckBox CbxMehrmals = g.getCheckbox("Mehrmals",(iBits&Transact.MEHRMALS)>0);
    final JCheckBox CbxAllein = g.getCheckbox("allein",(iBits&Transact.ALLEIN)>0);
    final JCheckBox CbxNoAServer = g.getCheckbox("no AServer",(iBits&Transact.NO_ASERVER)>0);
    final JCheckBox CbxWorkflow = g.getCheckbox("workflow",(iBits&Transact.WORKFLOW)>0);
    final JCheckBox CbxClearCache = g.getCheckbox("clear Cache",(iBits&Transact.CLRCACHE)>0);
    final JCheckBox CbxNoFix = g.getCheckbox("no fixtest",(iBits&Transact.NO_FIX)>0);
    final JCheckBox CbxClock3 = g.getCheckbox("Clock3",(iBits&Transact.CLOCK3)>0);
    final JCheckBox CbxMal2 = g.getCheckbox("Mal2",(iBits&Transact.MAL2)>0);
    CbxAllein.setEnabled(CbxAllein.isSelected());
    CbxClearCache.setEnabled(!CbxClearCache.isSelected());
    //final JRadioButton RbnNormal=g.getRadiobutton("Normal");
    //final JRadioButton RbnTerminal=g.getRadiobutton("Terminal");
    /*ButtonGroup RadGroup=new ButtonGroup();
    RadGroup.add(RbnNormal);
    RadGroup.add(RbnTerminal);
    if((iBits&64)>0)
      RbnTerminal.setSelected(true);
    else
      RbnNormal.setSelected(true);*/

    BtnOk.addActionListener(new ActionListener()
    {
            @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent ev)
            {
              sKennung=Edt.getText();

                if (Edt.Modified() && SQL.exists(g,"SELECT AIC_Computer FROM Computer WHERE Kennung='" + sKennung + "' and geloescht is null"))
                {
                  new Message(Message.WARNING_MESSAGE, null,g).showDialog("KennungVorhanden");
                  return;
                }
                else
                {
                  iBits=(CbxInfo1.isSelected()?Transact.INFO1:0)+(CbxInfo2.isSelected()?Transact.INFO2:0)+(CbxInfo3.isSelected()?Transact.INFO3:0)+(CbxInfo4.isSelected()?Transact.EXEC:0)+
                      (CbxFehlende.isSelected()?Transact.FEHLENDE:0)+(CbxInfo.isSelected()?Transact.INFO:0)+(CbxTerminal.isSelected()?Transact.TERMINAL:0)+(CbxClock.isSelected()?Transact.CLOCK:0)+
                      (CbxNoCache.isSelected()?Transact.NO_CACHE:0)/*+(CbxFill.isSelected()?Transact.FILL:0)*/+(CbxNoInfo.isSelected()?Transact.NO_INFO:0)+(CbxNoSpeed.isSelected()?Transact.NO_SPEED:0)+
                      (iBits&Transact.FONTFAKT)+(CbxClockSub.isSelected()?Transact.CLOCKSUB:0)+(CbxFastOut.isSelected()?Transact.FAST_OUT:0)+(CbxMehrmals.isSelected()?Transact.MEHRMALS:0)+
                      (CbxAllein.isSelected()?Transact.ALLEIN:0)+(CbxNoAServer.isSelected()?Transact.NO_ASERVER:0)+(CbxWorkflow.isSelected()?Transact.WORKFLOW:0)+
                      (CbxClearCache.isSelected()?Transact.CLRCACHE:0)+(CbxNoFix.isSelected()?Transact.NO_FIX:0)+(CbxClock3.isSelected()?Transact.CLOCK3:0)+(CbxMal2.isSelected()?Transact.MAL2:0);
                  //g.progInfo("Kennung="+sKennung+", iBits="+Integer.toHexString(iBits));
                  g.exec("update computer set Kennung='" + sKennung +"',cbits="+iBits+" where aic_computer=" + iAic);
                  ( (Vector) Out.getSelectedNode().getLabel()).setElementAt(sKennung, 1);
                  ( (Vector) Out.getSelectedNode().getUserData()).setElementAt(new Integer(iBits), 1);
                  Out.folderChanged(Out.getRootNode());
                }
              Dlg.setVisible(false);
            }
    });

    BtnAbbruch.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent ev)
            {
              Dlg.setVisible(false);
            }
    });

    JPanel Pnl = new JPanel(new GridLayout(1,0,2,2));
    Pnl.add(BtnOk);
    Pnl.add(BtnAbbruch);
    //JPanel PnlW = new JPanel(new GridLayout(0,1,2,2));
    JPanel PnlC = new JPanel(new GridLayout(0,2,2,2));
    PnlC.add(new JLabel(g.getBegriffS("Show","Kennung")+":"));
    PnlC.add(Edt);

    PnlC.add(CbxInfo1);
    PnlC.add(CbxInfo2);
    PnlC.add(CbxInfo3);
    PnlC.add(CbxInfo4);
    PnlC.add(CbxInfo);
    PnlC.add(CbxFehlende);
    PnlC.add(CbxClock);
    PnlC.add(CbxClockSub);
    PnlC.add(CbxTerminal);
    PnlC.add(CbxNoCache);
    //PnlC.add(CbxFill);
    PnlC.add(CbxNoInfo);
    PnlC.add(CbxNoSpeed);
    //PnlC.add(CbxLDATE);
    PnlC.add(CbxFastOut);
    PnlC.add(CbxMehrmals);
    PnlC.add(CbxAllein);
    PnlC.add(CbxNoAServer);
    PnlC.add(CbxWorkflow);
    PnlC.add(CbxClearCache);
    PnlC.add(CbxNoFix);
    PnlC.add(CbxClock3);
    PnlC.add(CbxMal2);
    //Dlg.getContentPane().add("West",PnlW);
    Dlg.getContentPane().add("Center",PnlC);
    Dlg.getContentPane().add("South",Pnl);
    Dlg.pack();
    Static.centerComponent(Dlg,thisFrame);
    Dlg.setVisible(true);
  }

  @SuppressWarnings("unchecked")
private boolean Delete()
  {
    int pane = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",new String[] {sKennung});
    if(pane == Message.YES_OPTION)
    {
      g.exec("update computer set geloescht="+g.now()+" where aic_computer=" + iAic);
      ((Vector)Out.getSelectedNode().getLabel()).setElementAt(new DateWOD().Format("yyyy-MM-dd"),iPosDel);
      Out.folderChanged(Out.getRootNode());
      bDel=true;
      EnableButtons();
      return true;
    }
    else
      return false;
  }

  @SuppressWarnings("unchecked")
private boolean Undelete()
  {
    int pane = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Undelete",new String[] {sKennung});
    if(pane == Message.YES_OPTION)
    {
      g.exec("update computer set geloescht=null where aic_computer=" + iAic);
      ((Vector)Out.getSelectedNode().getLabel()).setElementAt("",iPosDel);
      Out.folderChanged(Out.getRootNode());
      bDel=false;
      EnableButtons();
      return true;
    }
    else
      return false;
  }

  private String Format(String s)
  {
    return s.equals("")?"":s.substring(0,16);
  }

  private void Refresh()
  {
    g.fixInfo("Refresh");
    String s="select x.*,(select mom from logging where aic_logging=zuletzt) mom"+
      ",(select code.kennung from code"+g.join2("logging","code")+" where aic_logging=Zuletzt) Anlage"+
      ",(select benutzer.kennung from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer where logging.aic_logging=Zuletzt) Benutzer"+
      ",(select Java_Version from Systeminfo"+g.join2("logging","Systeminfo")+" where aic_logging=Zuletzt) Java_Version"+
      ",(select Betriebssystem from Systeminfo"+g.join2("logging","Systeminfo")+" where aic_logging=Zuletzt) Betriebssystem"+
      ",(select OS_Version from Systeminfo"+g.join2("logging","Systeminfo")+" where aic_logging=Zuletzt) OS_Version"+
      ",(select Version from logging where aic_logging=Zuletzt) Version"+
      " from (select Computer.aic_computer,CBITS,Computer.IP_Adresse,Computer.kennung Rechner,hostname,adresse,geloescht,max(aic_logging) Zuletzt"+
      ",(select count(*) from fensterpos"+g.join("logging","fensterpos")+" where aic_computer=Computer.aic_computer) Fensterpos"+
      ",(select count(*) from parameter where aic_computer=Computer.aic_computer) Parameter"+
      " from systeminfo right outer"+g.join2("logging","Systeminfo")+" right outer"+g.join("Computer","logging")+
      " where (geloescht is null or geloescht>"+g.von()+")"+
      (g.Def() ? "":" and (computer.aic_mandant"+Static.SQL_in(g.VecMandantWrite)+" or logging.aic_mandant"+Static.SQL_in(g.VecMandantWrite)+")")+
      " group by Computer.aic_computer,CBITS,Computer.IP_Adresse,Computer.kennung,hostname,adresse,geloescht) x"+(g.Def() ? "":" where (select ein from logging where aic_logging=zuletzt)>"+g.DateTimeToString(g.getBeginn()))+" order by "+g.order("Zuletzt")+" desc";
  g.progInfo(s);
  Tab=new Tabellenspeicher(g,s,true);
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
    StyFolder.setFont(g.getOutFont(false));//g.fontStandard);
    NodeRoot.removeChildren();
    for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
    {
      JCOutlinerNode Nod = new JCOutlinerNode(new jclass.util.JCVector(new Object[] {Tab.getInhalt("AIC_Computer"),Tab.getS("Rechner"),Tab.getS("HOSTNAME"),Tab.getS("Benutzer"),
          Tab.getS("IP_ADRESSE"),Tab.getS("ADRESSE"),Tab.getS("Betriebssystem"),Tab.getS("OS_Version"),Tab.getS("Anlage"),Tab.getS("Java_Version"),Version.getS(Tab.getI("Version")),
          Format(Tab.getS("Mom")),Format(Tab.getS("Geloescht")),Tab.getInhalt("Fensterpos"),Tab.getInhalt("Parameter")}),NodeRoot);
      Nod.setStyle(StyFolder);
      Nod.setUserData(new jclass.util.JCVector(new Object[] {Tab.getInhalt("AIC_Computer"),Tab.getInhalt("CBITS")}));
    }
    Out.folderChanged(NodeRoot);
    Out.setColumnWidth(0,50);
    iAic=0;
    iBits=0;
    EnableButtons();
  }

  private void EnableButtons()
  {
    if (BtnEdit != null)
      BtnEdit.setEnabled(iAic>0 && !bDel);
    if (BtnDelete != null)
      BtnDelete.setEnabled(iAic>0 && !bDel);
    if (BtnUndelete != null)
      BtnUndelete.setEnabled(iAic>0 && bDel);
  }

  private void ShowComputerBits()
  {
      Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr", "Kennung","Bezeichnung","Anzahl"});
      addBit(Tab2, "INFO1","Info1", Transact.INFO1, 0);
      addBit(Tab2, "INFO2","Info2",  Transact.INFO2, 1);
      addBit(Tab2, "INFO3","Info3",  Transact.INFO3, 2);
      addBit(Tab2, "EXEC","Info4",  Transact.EXEC, 3);
      addBit(Tab2, "FEHLENDE","fehlendes",  Transact.FEHLENDE, 4);
      addBit(Tab2, "INFO","Info",  Transact.INFO, 5);
      addBit(Tab2, "TERMINAL","Terminal",  Transact.TERMINAL, 6);
      addBit(Tab2, "CLOCK","Clock",  Transact.CLOCK, 7);
      addBit(Tab2, "NO_CACHE","no Cache",  Transact.NO_CACHE, 8);
      addBit(Tab2, "NO_INFO","no Info",  Transact.NO_INFO, 10);
      addBit(Tab2, "NO_SPEED","no Speed",  Transact.NO_SPEED, 11);
      addBit(Tab2, "FAST_OUT","fast out",  Transact.FAST_OUT, 17);
      addBit(Tab2, "CLOCKSUB","ClockSub",  Transact.CLOCKSUB, 18);
      addBit(Tab2, "MEHRMALS","Mehrmals",  Transact.MEHRMALS, 19);
      addBit(Tab2, "ALLEIN","allein",  Transact.ALLEIN, 20);
      addBit(Tab2, "NO_ASERVER","no AServer",  Transact.NO_ASERVER, 21);
      addBit(Tab2, "WORKFLOW","workflow",  Transact.WORKFLOW, 22);
      addBit(Tab2, "CLRCACHE","clear Cache",  Transact.CLRCACHE, 23);
      addBit(Tab2, "NO_FIX","no fixtest",  Transact.NO_FIX, 24);
      addBit(Tab2, "CLOCK3","Clock3",  Transact.CLOCK3, 25);
      addBit(Tab2, "MAL2","Mal2",  Transact.MAL2, 26);
      
      JDialog FomGid = new JDialog((JFrame)thisFrame, "Computerbits", false);
      AUOutliner Grid = new AUOutliner();
      FomGid.getContentPane().add("Center", Grid);
      Tab2.showGrid(Grid);
      FomGid.setSize(400, 600);
      Static.centerComponent(FomGid,thisFrame);
      Grid.addActionListener(new JCActionListener() {
        public void actionPerformed(JCActionEvent ev) {
          JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
          int i=Tabellenspeicher.geti(((Vector)Nod.getLabel()).elementAt(0));
          long l=(long)Math.pow(2,i);
          Tabellenspeicher Tab = new Tabellenspeicher(g,"select * from computer b where "+g.bit("cbits",l),true);
          //if(Tab.FrmGrid != null)
          //  Tab.FrmGrid.dispose();
          Tab.showGrid("Formulare mit bit " + i, /*bModal ? FomGid :*/ null);
        }
      });
      FomGid.setVisible(true);
  }

  private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
    {
      Tab2.addInhalt("Nr",i);
      Tab2.addInhalt("Kennung",sConst);
      Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
      Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from computer where "+g.bit("cbits",l)));
  }

}
