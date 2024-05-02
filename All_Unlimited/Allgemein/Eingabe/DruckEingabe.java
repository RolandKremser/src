package All_Unlimited.Allgemein.Eingabe;

import javax.swing.*;
import All_Unlimited.Allgemein.*;
import All_Unlimited.Print.Drucken;
import java.awt.Window;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.GridLayout;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
//import All_Unlimited.Allgemein.Anzeige.Combo;
import java.util.Vector;
import jclass.bwt.JCOutlinerNodeStyle;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.8
 */
public class DruckEingabe extends JPanel
{
  private static final long serialVersionUID = 2740918275781785104L;

  private Global g;
  public ComboSort Cbo;
  private JButton BtnList;
  private JDialog Frm=null;
  private int iBegriff=0;
  private Window PFom;
  private int iFF=100;
  private AUOutliner Out = new AUOutliner(null);
  private JCOutlinerNode NodeMom=null;
  private Tabellenspeicher Tab=null;
  private Vector<Integer> VecDrucke=null;

  public DruckEingabe(Global rg,Window rFom)
  {
    g=rg;
    PFom=rFom;
    build();
  }

  public void build()
  {
    setLayout(new BorderLayout(0,0));
    Cbo = new ComboSort(g,ComboSort.Farbe);
    //Cbo.setPreferredSize(new java.awt.Dimension(200,16));
    //Cbo.setMaximumRowCount(30);
    Cbo.setFont(Transact.fontStandard);
    Cbo.addKeyListener(new KeyListener()
        {
                public void keyPressed(KeyEvent e)
                {
                }
                public void keyReleased(KeyEvent e)
                {
                }
                public void keyTyped(KeyEvent e)
                {
                  if(e.getKeyChar()=='*' && g.Def())
                  {
                    e.consume();
                    OpenList();
                  }
                }
        });

    BtnList = g.getButton("...");
    BtnList.setBorder(null);

    add("Center",Cbo);
    if (g.Def() && Static.bStern)
      add("East", BtnList);
    BtnList.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        OpenList();
      }
    });
  }

  private void OpenList()
  {
    long lClock=Static.get_ms();
    if (Frm==null)
          BuildFrame();
    //g.checkDrucke();
    fillOutliner();
    g.clockInfo("fill DruckEingabe",lClock);
    Frm.setVisible(true);
  }

  private void BuildFrame()
    {
      //g.testInfo("Abfrage-Auswahl-BuildFrame:"+PFom+"/"+(PFom instanceof JFrame)+"/"+(PFom instanceof JDialog));
      int iPos=g.getPosBegriff("Dialog","Druck-Auswahl");
      String sBez=iPos<0 ? "Druck-Auswahl":g.getBegBez2(iPos);
      iBegriff=iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Aic");
      if (PFom instanceof JFrame)
        Frm = new JDialog((JFrame)PFom,sBez,false);
      else
        Frm = new JDialog((JDialog)PFom,sBez,((JDialog)PFom).isModal());
      //Frm = new JDialog(PFom,sBez,false);
      iFF=g.getFontFaktor();
      JPanel Pnl = new JPanel(new GridLayout(1,0,2,2));
      String [] s = new String[]{g.getShow("Bezeichnung"),g.getShow("DefBezeichnung"),g.getShow("Kennung"),g.getShow("last"),g.getShow("Stt"),g.getShow("BEW")
        ,g.getShow("aktiv"),g.getShow("Web"),g.getShow("pers"),g.getShow("Aic")};
      Out.setColumnButtons(s);
      Out.setNumColumns(s.length);
      Out.setRootVisible(false);
      Frm.getContentPane().add("Center",Out);
      ActionListener AL=new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          String s = e.getActionCommand();
          g.progInfo("Action=" + s);
          if (s.equals("Ok"))
          {
            Object O = Out.getSelectedNode().getUserData();
            int iAic = O instanceof Integer ? ((Integer)O).intValue() : 0;
            int i = Cbo.getOld();
            Cbo.setSelectedAIC(iAic);
            Cbo.setAktAIC(i);
            g.setFenster(Frm, iBegriff,iFF);
            Frm.setVisible(false);
          }
          else if (s.equals("Abbruch"))
          {
            g.setFenster(Frm,iBegriff,iFF);
            Frm.setVisible(false);
          }
          //else if (s.equals("Refresh"))
          //  fillOutliner();
        }
      };
      //JButton BtnRefresh = g.BtnAdd(g.getButton("Refresh"),"Refresh",AL);
      Pnl=new JPanel(new BorderLayout(2, 2));
      JPanel PnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
      Tab.addSuche(Pnl,PnlBtn,PFom,Out);
      JButton BtnOk = g.BtnAdd(g.getButton("Ok"),"Ok",AL);
      JButton BtnAbbruch = g.BtnAdd(g.getButton("Abbruch"),"Abbruch",AL);
      Frm.getRootPane().setDefaultButton(BtnOk);
      // Pnl.add(new JPanel());//BtnRefresh);
      PnlBtn.add(new JPanel());
      PnlBtn.add(BtnOk);
      PnlBtn.add(BtnAbbruch);

      Action cancelKeyAction = new AbstractAction() {
        /**
                   *
                   */
                  private static final long serialVersionUID = 3049607212338696850L;

          public void actionPerformed(ActionEvent e)
          {
            g.setFenster(Frm,iBegriff,iFF);
            Frm.setVisible(false);
          }
        };
      Static.Escape(BtnAbbruch,Frm,cancelKeyAction);
      Frm.getContentPane().add("South", Pnl);
      if (iBegriff>0)
        g.getFenster(Frm,iBegriff,false,0,0,900,500,iFF);
      else
        Frm.setSize(900, 500);
      Frm.addWindowListener(new WindowListener()
      {
              public void windowClosed(WindowEvent e)
              {
              }
              public void windowOpened(WindowEvent e)
              {
              }
              public void windowClosing(WindowEvent e)
              {
              }
              public void windowIconified(WindowEvent e)
              {
              }
              public void windowDeiconified(WindowEvent e)
              {
              }
              public void windowActivated(WindowEvent e)
              {
                if (NodeMom !=null)
                  Static.makeVisible(Out,NodeMom);
                Out.requestFocus();
              }
              public void windowDeactivated(WindowEvent e)
              {
              }
      });

      Static.centerComponent(Frm,PFom);
  }

  private void fillOutliner()
  {
    int iMom=Cbo.getSelectedAIC();

    /*  int i = Cbo.getOld();
      //Cbo.fillCbo(sQry, sTabname, bKein, false);
      Cbo.setSelectedAIC(iMom);
      Cbo.setAktAIC(i);*/
    JCOutlinerNodeStyle StyleDruck=g.getBGStype("Druck");
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    boolean bAIC=Tab.exists("aic");
    for(Tab.moveFirst();!Tab.out();Tab.moveNext())
    {
      Vector<Object> VecSpalten=new Vector<Object>();
      VecSpalten.addElement(Tab.getS("Bezeichnung"));
      VecSpalten.addElement(Tab.getS(bAIC ? "defBez":"DefBezeichnung"));
      int iAic=Tab.getI(bAIC ? "aic":"aic_begriff");
      int iPos=g.TabBegriffe.getPos("Aic", iAic);
      //VecSpalten.addElement(Tab.getI("aic_bewegungstyp")>0 ? g.TabErfassungstypen.getBezeichnungS(Tab.getI("aic_bewegungstyp")):
      //                      Tab.getI("aic_stammtyp")>0 ? "-"+g.TabStammtypen.getBezeichnungS(Tab.getI("aic_stammtyp")):Static.sKein);
      //VecSpalten.addElement(Tab.getI("aic_rolle")<=0 ? "":g.TabRollen.getBezeichnungS(Tab.getI("aic_rolle")));
      VecSpalten.addElement(Tab.getS("Kennung"));
      VecSpalten.addElement(Tab.getDate("Log"));
      VecSpalten.addElement(iPos<0 ? "?":g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt")));
      VecSpalten.addElement(iPos<0 ? "?":g.TabErfassungstypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Erf")));
      boolean bAktiv = VecDrucke==null || VecDrucke.contains(iAic);
      VecSpalten.addElement(Static.JaNein2(bAktiv));
      VecSpalten.addElement(iPos<0 ? "?":Static.JaNein2(g.TabBegriffe.getB(iPos,"Web")));
      VecSpalten.addElement(iPos<0 ? "?":Static.JaNein2((g.TabBegriffe.getI(iPos,"bits")&Drucken.cstPntPers)>0));
      VecSpalten.addElement(iAic);
      JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
      Node.setUserData(new Integer(iAic));
      Node.setStyle(bAktiv ? StyleDruck:g.setColor(StyleDruck,Global.ColTod));
      if (iAic == iMom)
        NodeMom = Node;
    }
    Out.folderChanged(NodeRoot);
  }

  public void fillDruck(int iRolle,int iStt,int iDruck,Vector<Integer> rVecDrucke,boolean bAlle,boolean bExport)
        {
          VecDrucke=rVecDrucke;
          g.progInfo("fillDruck:"+iStt+"/"+iDruck+"/"+VecDrucke);
          Vector<Integer> Vec=new Vector<Integer>();
          int iPosBG=g.TabBegriffgruppen.getPos("Kennung","Druck");
                if (iPosBG>=0)
                {
                        if (iRolle>0 && iStt!=g.RolleToStt(iRolle))
                          iRolle=0;
                        int iPos=g.TabBegriffe.getPos("Gruppe",g.TabBegriffgruppen.getI(iPosBG,"AIC"));
                        for(int iGruppe=g.TabBegriffe.getI(iPos,"Gruppe");iPos<g.TabBegriffe.size() && g.TabBegriffe.getI(iPos,"Gruppe")==iGruppe;iPos++)
                        {
                                if((iDruck==0 /*&& (g.TabBegriffe.getI(iPos,"bits")&Drucken.cstPntNeuerDruck)>0*/ && ((iRolle>0 ? g.TabBegriffe.getI(iPos,"Rolle")==iRolle:g.TabBegriffe.getI(iPos,"Stt")==iStt)
                                    && (/*(g.TabBegriffe.getI(iPos,"bits")&Drucken.cstPntAuchHS)>0 ||*/ g.TabBegriffe.getI(iPos,"Erf")==0) || iStt<0 && g.TabBegriffe.getI(iPos,"Erf")==-iStt) || iDruck==g.TabBegriffe.getI(iPos,"Aic"))
                                   && g.BerechtigungS(iPos) && (bAlle || VecDrucke==null || VecDrucke.contains(g.TabBegriffe.getInhalt("Aic",iPos))) && (!bExport || (g.TabBegriffe.getI(iPos,"bits")&Drucken.cstPntExport)>0))
                                {
                                  Vec.addElement(g.TabBegriffe.getI(iPos, "Aic"));
                                }
                        }
                        Tab=new Tabellenspeicher(g,"select aic_begriff,defbezeichnung"+g.AU_Bezeichnung("begriff")+",kennung,bits,aic_stammtyp,aic_bewegungstyp,aic_rolle"+
                                                 ",(select ein from logging where aic_logging=begriff.aic_logging) Log from begriff where"+g.in("aic_begriff",Vec)+" order by defbezeichnung",true);
                        Cbo.Clear();
                        for(Tab.moveFirst();!Tab.out();Tab.moveNext())
                          Cbo.addItem2(g.getBegBez(Tab.getI("aic_begriff")),Tab.getI("aic_begriff"),Tab.getS("Kennung"),Tab.getI("bits"),
                                       VecDrucke!=null && !VecDrucke.contains(Tab.getI("aic_begriff")) ? Global.ColTod:null);
                        Cbo.setKein(false);
                        Cbo.Sort();
                        //Cbo.setBackground(java.awt.Color.WHITE);
                }
        }

  public int getSelectedAIC()
  {
    return Cbo.getSelectedAIC();
  }

  public void setSelectedAIC(int iDruck)
  {
    Cbo.setSelectedAIC(iDruck);
  }

  public void fillCbo(Tabellenspeicher TabDrucke,boolean b)
  {
	  Tab=TabDrucke;
    Cbo.fillCbo(TabDrucke,b);
  }

}
