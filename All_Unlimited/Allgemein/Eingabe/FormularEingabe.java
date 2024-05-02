package All_Unlimited.Allgemein.Eingabe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Grunddefinition.DefFormular;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;

public class FormularEingabe extends JPanel 
{
  private Global g;
  private Window PFom;
  public ComboSort Cbo;
  private JButton BtnList;
  private JDialog Frm=null;
  private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
  private JCOutlinerNode NodeMom=null;
  private int iBegriff=0;
  private int iFF=100;

  public FormularEingabe(Global rg,Window rFom)
  {
    g=rg;
    PFom=rFom;
    build();
  }

  public void build()
  {
    setLayout(new BorderLayout(0,0));
    Cbo = new ComboSort(g);
    Cbo.setPreferredSize(new java.awt.Dimension(200,20));
    Cbo.setMaximumRowCount(20);
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
                  if(e.getKeyChar()=='*')
                  {
                    e.consume();
                    OpenList();
                  }
                }
        });

    BtnList = g.getButton("...");
    add("Center",Cbo);
    if (Static.bStern)
      add("East", BtnList);
    BtnList.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        OpenList();
      }
    });
  }

  private void BuildFrame()
  {
    //g.testInfo("Abfrage-Auswahl-BuildFrame:"+PFom+"/"+(PFom instanceof JFrame)+"/"+(PFom instanceof JDialog));
    int iPos=g.getPosBegriff("Dialog","Formular-Auswahl");
    String sBez=iPos<0 ? "Formular-Auswahl":g.getBegBez2(iPos);
    iBegriff=iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Aic");
    if (PFom instanceof JFrame)
      Frm = new JDialog((JFrame)PFom,sBez,false);
    else
      Frm = new JDialog((JDialog)PFom,sBez,((JDialog)PFom).isModal());
    iFF=g.getFontFaktor();
    JPanel PnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
    String [] s = new String[]{g.getShow("Bezeichnung"),g.getShow("DefBezeichnung"),g.getShow("Stt")/* ,g.getShow("BEW")*/,g.getShow("Kennung"),g.getShow("Aic"),g.getShow("Programm"),g.getShow("Web")};
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
        else if (s.equals("Refresh"))
        {
          int iAic=Cbo.getSelectedAIC();
          Cbo.refresh();
          Cbo.setSelectedAIC(iAic);
          fillOutliner();
        }
        else if (s.startsWith("Edit"))
        {
        	Object O = Out.getSelectedNode().getUserData();
            int iAic = O instanceof Integer ? ((Integer)O).intValue() : 0;
            if (iAic>0)
                DefFormular.get(g,iAic);
        }
      }
    };
    JButton BtnRefresh = g.BtnAdd(g.getButton("Refresh"),"Refresh",AL);
    JButton BtnOk = g.BtnAdd(g.getButton("Ok"),"Ok",AL);
    JButton BtnAbbruch = g.BtnAdd(g.getButton("Abbruch"),"Abbruch",AL);
    JButton BtnEdit = g.BtnAdd(g.getButton("Edit"),"Edit",AL);
    Frm.getRootPane().setDefaultButton(BtnOk);
    PnlBtn.add(BtnRefresh);
    if (g.Def())
      PnlBtn.add(BtnEdit);
    JPanel Pnl=new JPanel(new BorderLayout(2, 2));
    // if (Tab != null)
    // {
    //   // JPanel PnlO=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));//new GridLayout(1,0,5,5));
    //   // JPanel Pnl=new JPanel(new BorderLayout(2, 2));
    //   Tab.addSuche(Pnl,PnlBtn,PFom,Out);
    //   // PnlU.add("East",PnlBtn);
    //   // Pnl.add(PnlO);
    //   // Pnl.add(PnlU);
    // }
    // else
      Pnl=PnlBtn;
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
            //   if (NodeMom !=null)
            //     Static.makeVisible(Out,NodeMom);
              Out.requestFocus();
            }
            public void windowDeactivated(WindowEvent e)
            {
            }
    });

    Static.centerComponent(Frm,PFom);
  }

  private void OpenList()
  {
    long lClock=Static.get_ms();
    if (Frm==null)
          BuildFrame();
    fillOutliner();
    g.clockInfo("fill FormularEingabe",lClock);
    Frm.setVisible(true);
  }

  private void fillOutliner()
  {
    int iMom=Cbo.getSelectedAIC();
    // g.fixtestError("fillOutliner bei Aic="+iMom);
    Vector<Combo> Vec=Cbo.getAllItems();
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    for(int i=0;i<Vec.size();i++)
    {
        Vector<Object> VecSpalten=new Vector<Object>();
        Combo c=(Combo)Vec.elementAt(i);
        int iAic=c.getAic();
        int iPos=g.TabBegriffe.getPos("Aic",iAic);
        VecSpalten.addElement(iPos<0 ? c.getBezeichnung():g.TabBegriffe.getS(iPos,"Bezeichnung"));
        VecSpalten.addElement(iPos<0 ? "???":g.TabBegriffe.getS(iPos,"DefBezeichnung"));
        VecSpalten.addElement(iAic>0 && g.TabBegriffe.getI(iPos,"Stt")>0 ? g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt")):Static.sKein);
        // VecSpalten.addElement(iAic>0 && g.TabBegriffe.getI(iPos,"Erf")>0 ? g.TabErfassungstypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Erf")):"");
        VecSpalten.addElement(c.getKennung());
        VecSpalten.addElement(new Integer(iAic));
        VecSpalten.addElement(iPos<0 ? "?":g.TabBegriffe.isNull(iPos,"Prog") ? "":g.TabCodes.getBezeichnungS(g.TabBegriffe.getI(iPos,"Prog")));
        VecSpalten.addElement(iPos<0 ? "?":Static.JaNein2(g.TabBegriffe.getB(iPos, "Web")));//iPosAbf<0 ? "???":Static.JaNein((g.TabAbfragen.getI(iPosAbf,"Abits2")&Abfrage.cstAbfWeb)>0));
        JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
        Node.setUserData(new Integer(iAic));
        if (iAic == iMom)
          NodeMom = Node;
    }
    if (NodeMom!=null)
        Static.makeVisible(Out,NodeMom);
    // Out.folderChanged(NodeRoot);
  }
    
}
