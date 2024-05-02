package All_Unlimited.Allgemein.Eingabe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
// import java.awt.GridLayout;
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
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Grunddefinition.DefAbfrage;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;

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
public class AbfrageEingabe extends JPanel
{
  /**
	 *
	 */
	private static final long serialVersionUID = 2740918275781785103L;
public AbfrageEingabe(Global rg,Window rFom,boolean rbWo,boolean rbBegriff)
  {
    g=rg;
    bWo=rbWo;
    bBegriff=rbBegriff;
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
                  if(e.getKeyChar()=='*')
                  {
                    e.consume();
                    OpenList();
                  }
                }
        });

    BtnList = g.getButton("...");
    BtnList.setBorder(null);
    TabBenutzer=new Tabellenspeicher(g,"select aic_benutzer aic,kennung from benutzer",true);
    TabBenutzergruppe=new Tabellenspeicher(g,"select aic_benutzergruppe aic,kennung from benutzergruppe",true);
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
//	  g.fixtestError("AE.BuildFrame");
    //g.testInfo("Abfrage-Auswahl-BuildFrame:"+PFom+"/"+(PFom instanceof JFrame)+"/"+(PFom instanceof JDialog));
    int iPos=g.getPosBegriff("Dialog","Abfrage-Auswahl");
    String sBez=iPos<0 ? "Abfrage-Auswahl":g.getBegBez2(iPos);
    iBegriff=iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Aic");
    if (PFom instanceof JFrame)
      Frm = new JDialog((JFrame)PFom,sBez,false);
    else
      Frm = new JDialog((JDialog)PFom,sBez,((JDialog)PFom).isModal());
    iFF=g.getFontFaktor();
    JPanel PnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
    String [] s = bWo ? new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Typ"),g.getBegriffS("Show","Rolle"),g.getBegriffS("Show","Kennung"),
    		g.getBegriffS("Show","Nr"),g.getBegriffS("Show","nur_modell"),g.getBegriffS("Show","Benutzergruppe"),g.getBegriffS("Show","Benutzer"),g.getBegriffS("Show","last")}:
        new String[]{g.getShow("Bezeichnung"),g.getShow("DefBezeichnung"),g.getShow("Rolle"),g.getShow("Kennung"),g.getShow("Aic"),g.getShow("nur_modell"),
        		g.getShow("Benutzergruppe"),g.getShow("Benutzer"),g.getShow("last"),g.getShow("Tabellentyp"),g.getShow("Programm"),g.getShow("Web"),g.getShow("System"),g.getBegriffS("Checkbox","API")};
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
          VecA=null;
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
            {
            	if (!bBegriff)
                {
                	int iPosAbf=g.TabAbfragen.getPos("Aic_Abfrage",iAic);
                    iAic=g.TabAbfragen.getI(iPosAbf,"Aic_begriff");
                }
            	int iStt=SQL.getInteger(g,"select "+g.isnull()+"-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff="+iAic);
            	DefAbfrage.get(g, new ShowAbfrage(g, iAic, Abfrage.cstBegriff), iStt,null,false,s.equals("Edit") ? 0:1).show();
            }
        }
      }
    };
    JButton BtnRefresh = g.BtnAdd(g.getButton("Refresh"),"Refresh",AL);
    JButton BtnOk = g.BtnAdd(g.getButton("Ok"),"Ok",AL);
    JButton BtnAbbruch = g.BtnAdd(g.getButton("Abbruch"),"Abbruch",AL);
    JButton BtnEdit = g.BtnAdd(g.getButton("Edit"),"Edit",AL);
    JButton BtnEdit2 = g.BtnAdd(g.getButton("Edit2"),"Edit2",AL);
    CbxNurModell=g.getCheckbox("nur_Modell");
    Frm.getRootPane().setDefaultButton(BtnOk);
    PnlBtn.add(BtnRefresh);
    if (g.Def())
    {
      PnlBtn.add(CbxNurModell);
      PnlBtn.add(BtnEdit);
      PnlBtn.add(BtnEdit2);
    }
    // JPanel Pnl=new JPanel(new GridLayout(2, 1,2,2));
    JPanel Pnl=new JPanel(new BorderLayout(2, 2));
    if (Tab != null)
    {
      // JPanel PnlO=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));//new GridLayout(1,0,5,5));
      // JPanel Pnl=new JPanel(new BorderLayout(2, 2));
      Tab.addSuche(Pnl,PnlBtn,PFom,Out);
      // PnlU.add("East",PnlBtn);
      // Pnl.add(PnlO);
      // Pnl.add(PnlU);
    }
    else
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
//	  g.fixtestError("AE.fillOutliner");
    int iMom=Cbo.getSelectedAIC();
    if (sQry != null)
    {
      int i = Cbo.getOld();
      Cbo.fillCbo(sQry, sTabname, bKein, false);
      Cbo.setSelectedAIC(iMom);
      Cbo.setAktAIC(i);
    }
    Vector<Combo> Vec=Cbo.getAllItems();
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    for(int i=0;i<Vec.size();i++)
    {
      Vector<Object> VecSpalten=new Vector<Object>();
      Combo c=(Combo)Vec.elementAt(i);
      int iAic=c.getAic();
      String sRolle=null;
      int iPosAbf=g.TabAbfragen.getPos(bBegriff ? "aic_begriff":"Aic_Abfrage",iAic);
      //if (iPosAbf<0)
      //  g.fixInfo("         -------  Abfrage.Eingabe: nicht gefunden "+iAic+"/"+c.getBezeichnung());
      int iPos=iPosAbf<0 ? -1:g.TabBegriffe.getPos("Aic",g.TabAbfragen.getI(iPosAbf,"Aic_begriff"));
      int iPosR=iPos<0 ? -1:g.TabRollen.getPos("Aic",g.TabBegriffe.getI(iPos,"Rolle"));
      if (iPosR>=0)
        sRolle=g.TabRollen.getS(iPosR,"Bezeichnung");
      if (bWo)
    	  VecSpalten.addElement(c.getBezeichnung());
      else
      {
    	  VecSpalten.addElement(iPos<0 ? c.getBezeichnung():g.TabBegriffe.getS(iPos,"Bezeichnung"));
    	  VecSpalten.addElement(iPos<0 ? "???":g.TabBegriffe.getS(iPos,"DefBezeichnung"));
      }
      if (bWo)
        VecSpalten.addElement(iAic>0 && g.TabBegriffe.getI(iPos,"Erf")>0 ? g.TabErfassungstypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Erf")):
                              iAic>0 && g.TabBegriffe.getI(iPos,"Stt")>0 ? "-"+g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt")):Static.sKein);
      VecSpalten.addElement(sRolle);
      VecSpalten.addElement(c.getKennung());
      VecSpalten.addElement(new Integer(iAic));
      boolean bNurModell=iPosAbf<0 ? false:(g.TabAbfragen.getI(iPosAbf,"bits")&Abfrage.cstNurModell)>0;
      VecSpalten.addElement(iPosAbf<0 ? "?":Static.JaNein2(bNurModell));
      VecSpalten.addElement(iPosAbf<0 || g.TabAbfragen.getInhalt("aic_benutzergruppe",iPosAbf)==null ? "":TabBenutzergruppe.getKennung(g.TabAbfragen.getI(iPosAbf,"aic_benutzergruppe")));
      VecSpalten.addElement(iPosAbf<0 || g.TabAbfragen.getInhalt("aic_benutzer",iPosAbf)==null ? "":TabBenutzer.getKennung(g.TabAbfragen.getI(iPosAbf,"aic_benutzer")));
      VecSpalten.addElement(iPosAbf<0 ? null:g.TabAbfragen.getTimestamp(iPosAbf,"last"));
      VecSpalten.addElement(iPosAbf<0 ? "?":g.TabAbfragen.isNull(iPosAbf,"aic_code") ? "":g.TabCodes.getBezeichnungS(g.TabAbfragen.getI(iPosAbf,"aic_code")));
      VecSpalten.addElement(iPosAbf<0 ? "?":g.TabAbfragen.isNull(iPosAbf,"Prog") ? "":g.TabCodes.getBezeichnungS(g.TabAbfragen.getI(iPosAbf,"Prog")));
      VecSpalten.addElement(iPos<0 ? "?":Static.JaNein2(g.TabBegriffe.getB(iPos, "Web")));//iPosAbf<0 ? "???":Static.JaNein((g.TabAbfragen.getI(iPosAbf,"Abits2")&Abfrage.cstAbfWeb)>0));
      VecSpalten.addElement(iPosAbf<0 ? "?":Static.JaNein2((g.TabAbfragen.getI(iPosAbf,"Abits2")&Abfrage.cstAbfSystem)>0));
      VecSpalten.addElement(iPosAbf<0 ? "?":Static.JaNein2((g.TabAbfragen.getI(iPosAbf,"Abits2")&Abfrage.cstAbfAPI)>0));
      if ((!CbxNurModell.isSelected() || bNurModell) && (VecA==null || VecA.contains(iAic)))
      {
        JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
        Node.setUserData(new Integer(iAic));
        if (iAic == iMom)
          NodeMom = Node;
      }
    }
    Out.folderChanged(NodeRoot);
  }

  public void setFilterEig(int iEig)
  {
    Vector<Integer> Vec=Tab.getVecSpalteI("Aic");
    g.fixtestError("setFilterEig:"+iEig+" <- "+Vec);
    VecA=SQL.getVector("select distinct s.aic_abfrage from spalte s join fixeigenschaft f on s.aic_spalte=f.aic_spalte and f.aic_eigenschaft="+iEig+" where s.aic_abfrage"+Static.SQL_in(Vec), g);
    g.fixtestError("-> "+VecA);
    Cbo.setFilter(VecA);
  }

  private void OpenList()
  {
    long lClock=Static.get_ms();
//    g.fixtestError("AE.OpenList");
    if (Frm==null)
          BuildFrame();
//    g.fixtestError("g.checkAbfragen");
    g.checkAbfragen();
    fillOutliner();
    g.clockInfo("fill AbfrageEingabe",lClock);
//    g.fixtestError("AE.fertig");
    Frm.setVisible(true);
  }

  public void fillCbo(Tabellenspeicher TabAbfragen,boolean b)
  {
	Tab=TabAbfragen;
    Cbo.fillCbo(TabAbfragen,b);
  }

  public void fillCbo(String rsQry,String rsTabname,boolean rbKein)
  {
    sQry=rsQry;
    sTabname=rsTabname;
    bKein=rbKein;
    Cbo.fillCbo(sQry,sTabname,bKein,false);
  }

  public void setSelectedAIC(int iAbfrage)
  {
    Cbo.setSelectedAIC(iAbfrage);
  }

  public void addItem(String sBez,int iNeu,String s,int i)
  {
    Cbo.addItem(sBez,iNeu,s,i);
  }
  public void setItem(int iAbfrage,String sBez,String s,int i)
  {
    Cbo.setItem(iAbfrage, sBez, s, i);
  }
  public void removeItemAt(int iAbfrage)
  {
    Cbo.removeItemAt(iAbfrage);
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

  private Global g;
  private boolean bWo=false;
  private boolean bBegriff=false;
  private Window PFom;
  public ComboSort Cbo;
  private JButton BtnList;
  private JDialog Frm=null;
  private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
  private Tabellenspeicher Tab=null;
  private Vector<Integer> VecA=null;
  private Tabellenspeicher TabBenutzer=null;
  private Tabellenspeicher TabBenutzergruppe=null;
  private JCOutlinerNode NodeMom=null;
  private int iBegriff=0;
  private AUCheckBox CbxNurModell;
  private String sQry=null;
  private String sTabname=null;
  private boolean bKein=false;
  private int iFF=100;
}
