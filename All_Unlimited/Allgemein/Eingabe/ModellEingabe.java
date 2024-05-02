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

import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Grunddefinition.DefModell;

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
public class ModellEingabe extends JPanel
{
  /**
	 *
	 */
	private static final long serialVersionUID = 9011773819187438799L;
private Global g;
  private Window PFom;
  public ComboSort Cbo;
  private JButton BtnList;
  private JDialog Frm=null;
  private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
  private JCOutlinerNode NodeMom=null;
  private Tabellenspeicher Tab=null;
  private int iAbfrage=0;
  private int iBegriff=0;
  private int iFF=100;
  private boolean bBegriff=false;

  public ModellEingabe(Global rg,Window rFom)
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
  
  private int getAic()
  {
	  Object O=Out.getSelectedNode().getUserData();
      int iAic=O instanceof Integer ?((Integer)O).intValue():0;
      return iAic;
  }

  private void BuildFrame()
  {
    int iPos=g.getPosBegriff("Dialog","Modell-Auswahl");
    String sBez=iPos<0 ? "Modell-Auswahl":g.getBegBez2(iPos);
    iBegriff=iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Aic");
    if (PFom instanceof JFrame)
      Frm = new JDialog((JFrame)PFom,sBez,false);
    else
      Frm = new JDialog((JDialog)PFom,sBez,((JDialog)PFom).isModal());
    iFF=g.getFontFaktor();
    JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
    String [] s = new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Nr"),g.getBegriffS("Show","Change")};
    Out.setColumnButtons(s);
    Out.setNumColumns(s.length);
    Out.setRootVisible(false);
    Frm.getContentPane().add("Center",Out);
    JButton BtnOk = g.getButton("Ok");
    JButton BtnRefresh=g.getButton("Refresh");
    JButton BtnEdit=g.getButton("Edit");
    JButton BtnAbbruch = g.getButton("Abbruch");
    Frm.getRootPane().setDefaultButton(BtnOk);
    Pnl.add(BtnRefresh);
    if (g.Def()) Pnl.add(BtnEdit);
    Pnl.add(new JPanel());
    Pnl.add(BtnOk);
    Pnl.add(BtnAbbruch);
    ActionListener AL=new ActionListener()
    {
            public void actionPerformed(ActionEvent e)
            {
            	String s=e.getActionCommand();
            	if (s.equals("Ok") || s.equals("Abbruch"))
            	{
            		if (s.equals("Ok"))
            		{
	            		
	                    int i=Cbo.getOld();
	                    Cbo.setSelectedAIC(getAic());
	                    Cbo.setAktAIC(i);
            		}
                    g.setFenster(Frm,iBegriff,iFF);
                    Frm.setVisible(false);
            	}
            	else if (s.equals("Refresh"))
            	{
            		int iAic=Cbo.getOld();
                    fillCboM(iAbfrage);
                    Cbo.setSelectedAIC(iAic);
                    fillOutliner();
                    if (NodeMom !=null)
                      Static.makeVisible(Out,NodeMom);
                    Out.requestFocus();
            	}
            	else if (s.equals("Edit"))
            		DefModell.get(g, bBegriff ? getAic():g.ModellToBegriff(getAic())).show();
            }
    };
    g.BtnAdd(BtnOk, "Ok", AL);
    g.BtnAdd(BtnAbbruch, "Abbruch", AL);
    g.BtnAdd(BtnRefresh, "Refresh", AL);
    g.BtnAdd(BtnEdit, "Edit", AL);
    Action cancelKeyAction = new AbstractAction() {
      /**
		 *
		 */
		private static final long serialVersionUID = 2477022925733839631L;

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
      Combo c=(Combo)Vec.elementAt(i);
      VecSpalten.addElement(c.getBezeichnung());
      int iAic=c.getAic();
      VecSpalten.addElement(c.getKennung());
      VecSpalten.addElement(new Integer(iAic));
      if (Tab.posInhalt("Aic",iAic))
        VecSpalten.addElement(Tab.getInhalt("Log"));
      JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
      Node.setUserData(new Integer(iAic));
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
    g.clockInfo("fill ModellEingabe",lClock);
    Frm.setVisible(true);
  }

  public void fillCboM(int riAbfrage)
  {
	  // g.fixtestError("ME.fillCbo "+riAbfrage);
    iAbfrage=riAbfrage;
    if (iAbfrage==0)
      Cbo.Clear(true);
    else
    {
    	bBegriff=iAbfrage<-1; //==-2;
    	if (iAbfrage==-2) // Web-Modelle
    		Tab = new Tabellenspeicher(g, "select begriff.aic_begriff aic,kennung,defBezeichnung Bezeichnung,(select ein from logging where aic_logging=begriff.aic_logging) Log"+
                  " from Modell join begriff on Modell.aic_begriff=begriff.aic_begriff and tod is null and web=1", true);
    	else if (iAbfrage==-3)
    		Tab = new Tabellenspeicher(g, "select aic_begriff aic,kennung,defBezeichnung Bezeichnung from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell")+" and "+g.bit("bits",Global.cstDruckZR), true);
    	else
    		Tab = new Tabellenspeicher(g, "select aic_Modell aic,kennung,defBezeichnung Bezeichnung,(select ein from logging where aic_logging=begriff.aic_logging) Log"+
                                 " from Modell join begriff on Modell.aic_begriff=begriff.aic_begriff and tod is null"+(iAbfrage==-1?" and"+g.bit("bits",Global.cstSave):""), true);
      Cbo.fillCbo(Tab, true);
    }
  }

  public void setSelectedAIC(int iModell)
  {
    Cbo.setSelectedAIC(iModell);
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
