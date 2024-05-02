package All_Unlimited.Allgemein.Eingabe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
//import java.awt.Image;
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
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;

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
public class RolleEingabe extends JPanel
{
  /**
	 *
	 */
	private static final long serialVersionUID = -1564565482527373561L;
private Global g;
  private Window PFom;
  public ComboSort Cbo;
  private JButton BtnList;
  private JDialog Frm=null;
  private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
  private JCOutlinerNode NodeMom=null;
  private int iStt=0;
  private int iMom=0;
  private int iBegriff=0;
  private int iFF=100;

  public RolleEingabe(Global rg,Window rFom)
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

  private void OpenList()
  {
    long lClock=Static.get_ms();
    if (Frm==null)
          BuildFrame();
    fillOutliner();
    if (iBegriff>0)
      g.getFenster(Frm,iBegriff,false,0,0,300,500,iFF);
    else
      Frm.pack();
    Static.centerComponent(Frm,PFom);
    g.clockInfo("fill RolleEingabe",lClock);
    Frm.setVisible(true);
  }

  private void BuildFrame()
  {
    int iPos=g.getPosBegriff("Dialog","Rolle-Auswahl");
    String sBez=iPos<0 ? "Rolle-Auswahl":g.getBegBez2(iPos);
    iBegriff=iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Aic");
    if (PFom instanceof JFrame)
      Frm = new JDialog((JFrame)PFom,sBez,false);
    else
      Frm = new JDialog((JDialog)PFom,sBez,((JDialog)PFom).isModal());
    iFF=g.getFontFaktor();
    JPanel Pnl = new JPanel(new GridLayout(0,2,2,2));
    String [] s = new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Nr")};
    Out.setColumnButtons(s);
    Out.setNumColumns(s.length);
    Vector<Object> VecSpalten=new Vector<Object>();
    VecSpalten.addElement(Static.sKein);
    VecSpalten.addElement("<kein>");
    VecSpalten.addElement(Static.Int0);
    Out.getRootNode().setLabel(VecSpalten);
    Out.getRootNode().setUserData(Static.Int0);
    //Out.setRootVisible(false);
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
		private static final long serialVersionUID = -1872220467171282192L;

	public void actionPerformed(ActionEvent e)
        {
          g.setFenster(Frm,iBegriff,iFF);
          Frm.setVisible(false);
        }
      };
    Static.Escape(BtnAbbruch,Frm,cancelKeyAction);

    Frm.getContentPane().add("South", Pnl);
    //Frm.setSize(700, 500);
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
  }

  private void fillOutliner()
  {
    //Vector Vec=Cbo.getItems();
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    iMom=Cbo.getSelectedAIC();
    g.TabRollen.sort("Bezeichnung",true);
    g.TabRollen.sort("Stt",true);
    int iSttOld=-1;
    if (iStt>0)
      fillNode(-iStt,NodeRoot);
    else
      for(int i=0;i<g.TabRollen.size();i++)
        if (g.TabRollen.getI(i,"Stt") != iSttOld)
        {
          int iPosS=g.TabStammtypen.getPos("Aic",g.TabRollen.getI(i,"Stt"));
          iSttOld=g.TabRollen.getI(i,"Stt");
          Vector<Object> VecSpalten=new Vector<Object>();
          VecSpalten.addElement(g.TabStammtypen.getS(iPosS,"Bezeichnung"));
          VecSpalten.addElement(g.TabStammtypen.getS(iPosS,"Kennung"));
          VecSpalten.addElement(g.TabStammtypen.getInhalt("Aic",iPosS));
          JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecSpalten, NodeRoot);
          Node.setUserData(Static.Int0);
          Node.setStyle(g.getStyle(g.getSttGif(g.TabStammtypen.getI(iPosS,"Aic"))));//(Image)g.TabStammtypen.getInhalt("Bild")));
          fillNode(-iSttOld,Node);
        }
    /*for(int i=0;i<Vec.size();i++)
    {
      Vector VecSpalten=new Vector();
      Combo c=(Combo)Vec.elementAt(i);
      VecSpalten.addElement(c.getBezeichnung());
      int iAic=c.getAic();
      VecSpalten.addElement(c.getKennung());
      VecSpalten.addElement(new Integer(iAic));
      JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
      Node.setUserData(new Integer(iAic));
      Image Gif = g.TabRollen.posInhalt("Aic",iAic) ? (Image)g.TabRollen.getInhalt("Bild"):null;
      if (Gif != null)
      {
              JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
              StyFolder.setItemIcon(Gif);
              Node.setStyle(StyFolder);
      }

      if (iAic==iMom)
        NodeMom=Node;
    }*/
    Out.folderChanged(NodeRoot);
  }

  private void fillNode(int riRolle,JCOutlinerFolderNode NodeP)
  {
    //g.TabRollen.push();
    for(int i=0;i<g.TabRollen.size();i++)
      if (riRolle<0 && g.TabRollen.getI(i,"Stt")==-riRolle && g.TabRollen.getI(i,"Davor")==0 || g.TabRollen.getI(i,"Davor")==riRolle)
      {
        Vector<Object> VecSpalten=new Vector<Object>();
        VecSpalten.addElement(g.TabRollen.getS(i,"Bezeichnung"));
        VecSpalten.addElement(g.TabRollen.getS(i,"Kennung"));
        VecSpalten.addElement(g.TabRollen.getInhalt("Aic",i));
        JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecSpalten, NodeP);
        Node.setUserData(g.TabRollen.getInhalt("Aic",i));
        Node.setStyle(g.getStyle(g.getRolleGif(g.TabRollen.getI(i,"Aic"))));//(Image)g.TabRollen.getInhalt("Bild")));
        if (g.TabRollen.getI(i,"Aic")==iMom)
          NodeMom=Node;
        fillNode(g.TabRollen.getI(i,"Aic"),Node);
      }
    //g.TabRollen.pop();
  }

  public void fillRolle(int riStt, boolean bKein)
  {
    iStt=riStt;
    Cbo.fillRolle(iStt,bKein);
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

  public boolean isNull()
  {
    return getSelectedAIC()==0;
  }

  public void setEditable(boolean b)
  {
      Cbo.setEditable1(b);
      BtnList.setEnabled(b);
  }

  public void setEnabled(boolean b)
  {
    Cbo.setEnabled(b);
    BtnList.setEnabled(b);
  }

}
