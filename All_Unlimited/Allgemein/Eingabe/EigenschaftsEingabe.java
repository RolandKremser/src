package All_Unlimited.Allgemein.Eingabe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
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

//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Combo;

/**
 * <p>Überschrift: Eigenschaftsauswahl</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class EigenschaftsEingabe extends JPanel
{
  /**
	 *
	 */
	private static final long serialVersionUID = -2734203272214394901L;
private Global g;
  private Window PFom;
  public ComboSort Cbo;
  private JButton BtnList;
  private JDialog Frm=null;
  private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
  private JCOutlinerNode NodeMom=null;
  private int iBegriff=0;
  private int iFF=100;

  public EigenschaftsEingabe(Global rg,Window rFom)
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
    int iPos=g.getPosBegriff("Dialog","Eigenschaft-Auswahl");
    String sBez=iPos<0 ? "Eigenschaft-Auswahl":g.getBegBez2(iPos);
    iBegriff=iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Aic");
    if (PFom instanceof JFrame)
      Frm = new JDialog((JFrame)PFom,sBez,false);
    else
      Frm = new JDialog((JDialog)PFom,sBez,((JDialog)PFom).isModal());
    iFF=g.getFontFaktor();
    JPanel Pnl = new JPanel(new GridLayout(0,2,2,2));
    String [] s = new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Nr"),g.getBegriffS("Show","Datentyp")};
    Out.setColumnButtons(s);
    Out.setNumColumns(s.length);
    Out.setRootVisible(false);
    Frm.getContentPane().add("Center",Out);
    JButton BtnOk = g.getButton("Ok");
    JButton BtnAbbruch = g.getButton("Abbruch");
    Frm.getRootPane().setDefaultButton(BtnOk);
    Pnl.add(BtnOk);
    Pnl.add(BtnAbbruch);
    BtnOk.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent e)
            {
              Object O=Out.getSelectedNode().getUserData();
              int iAic=O instanceof Integer ?((Integer)O).intValue():0;
              int i=Cbo.getOld();
              Cbo.setSelectedAIC(iAic);
              Cbo.setAktAIC(i);
              g.setFenster(Frm,iBegriff,iFF);
              Frm.setVisible(false);
            }
    });
    BtnAbbruch.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent e)
            {
               g.setFenster(Frm,iBegriff,iFF);
               Frm.setVisible(false);
            }
    });
    Action cancelKeyAction = new AbstractAction() {
      /**
		 *
		 */
		private static final long serialVersionUID = -2363959329170837325L;

	public void actionPerformed(ActionEvent e)
        {
          g.setFenster(Frm,iBegriff,iFF);
          Frm.setVisible(false);
        }
      };
    Static.Escape(BtnAbbruch,Frm,cancelKeyAction);

    Frm.getContentPane().add("South", Pnl);
    if (iBegriff>0)
      g.getFenster(Frm,iBegriff,false,0,0,500,500,iFF);
    else
      Frm.setSize(500, 500);
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
    Vector<Combo> Vec=Cbo.getItems();
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    int iMom=Cbo.getSelectedAIC();
    for(int i=0;i<Vec.size();i++)
    {
      Vector<Object> VecSpalten=new Vector<Object>();
      Combo c=Vec.elementAt(i);
      VecSpalten.addElement(c.getBezeichnung());
      int iAic=c.getAic();
      VecSpalten.addElement(c.getKennung());
      VecSpalten.addElement(new Integer(iAic));
      int iPosE=g.TabEigenschaften.getPos("Aic",iAic);
      String sDatentyp=iPosE>=0 ? g.TabEigenschaften.getS(iPosE,"Datentyp"):"???";
      VecSpalten.addElement(sDatentyp);
      JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
      Node.setUserData(new Integer(iAic));
      int iPos=g.getPosBegriff("Datentyp",sDatentyp);
      Image Gif = iPos<0 ? null:g.LoadImage(g.TabBegriffe.getS(iPos,"BildFile"));
      if (Gif != null)
      {
              JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
              StyFolder.setItemIcon(Gif);
              Node.setStyle(StyFolder);
      }

      if (iAic==iMom)
        NodeMom=Node;
    }
    Out.folderChanged(NodeRoot);
  }

  private void OpenList()
  {
    long lClock=Static.get_ms();
    if (Frm==null)
          BuildFrame();
    fillOutliner();
    g.clockInfo("fill EigenschaftEingabe",lClock);
    Frm.setVisible(true);
  }

  public void fillCbo(Tabellenspeicher TabAbfragen,boolean b)
  {
    Cbo.fillCbo(TabAbfragen,b);
  }

  public void setSelectedAIC(int iAbfrage)
  {
    Cbo.setSelectedAIC(iAbfrage);
  }

  public int getSelectedAIC()
  {
    return Cbo.getSelectedAIC();
  }

  public void setEnabled(boolean b)
  {
    Cbo.setEnabled(b);
    BtnList.setEnabled(b);
  }

}
