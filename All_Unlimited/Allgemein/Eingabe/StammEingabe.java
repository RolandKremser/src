/*
    All_Unlimited-Allgemein-Eingabe-StammEingabe.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import All_Unlimited.Hauptmaske.EingabeFormular;

import java.awt.FlowLayout;
import java.awt.GridLayout;

public class StammEingabe extends javax.swing.JPanel
{
    /**
	 *
	 */
	private static final long serialVersionUID = -1516841224445744458L;
	// add your data members here
	private ComboSort CboStt;
	private ComboSort CboStamm;
        private AUCheckBox CbxKeinSZR=null;
        private JButton BtnList;
        private JButton BtnAufruf;
	private Global g;
        private JDialog Dlg=null;
        private Window PDlg;
        private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
        private JCOutlinerNode NodeMom=null;
        private Tabellenspeicher Tab=null;
        private int iRolle=0;
        private ComboSort CboRolle=null;
        private int iSttRolle=0;
        private boolean bBew=false;
        private boolean bUseKennung=false;

        public StammEingabe(Window rDlg,Global glob)
        {
          this(rDlg,glob,0,0);
        }

	public StammEingabe(Window rDlg,Global glob,int riStt,int riRolle)
	{
          g = glob;
          PDlg=rDlg;
          iRolle=riRolle;
          CboStt = new ComboSort(g);
          CboStt.setPreferredSize(new java.awt.Dimension(150,20));
          bBew=riStt<0;
          if (iRolle<0)
          {
            iRolle=0;
            CboStt.fillCbo("select aic_stammtyp,kennung"+g.AU_Bezeichnung("stammtyp")+" from stammtyp where"+g.bit("SttBits",Global.cstSttBed),"stammtyp",true,false);
          }
          else if (bBew)
            CboStt.fillCbo("select e.aic_stammtyp faktor,e.aic_eigenschaft,e.kennung"+g.AU_Bezeichnung2("Eigenschaft","e")+
                           " from begriff b"+g.join("eigenschaft","e","b","begriff")+g.join("bew_zuordnung","z","e","Eigenschaft")+
                           " where z.aic_bewegungstyp="+(-riStt)+" and b.kennung='BewStamm'","eigenschaft",false,true);
          else
            CboStt.fillDefTable("STAMMTYP", true);
          CboStamm = new ComboSort(g);
          if (riStt>0)
          {
            CboStt.setSelectedAIC(riStt);
          }
          else
            CboStt.addItemListener(new ItemListener()
            {
              public void itemStateChanged(ItemEvent ev)
              {
                if (ev.getStateChange() == ItemEvent.SELECTED)
                {
                  //CboStamm.fillStammTable(CboStt.getSelectedAIC(), true);
                  if (CboStt.getSelectedAIC() != iSttRolle)
                  {
                    iRolle = 0;
                    iSttRolle = 0;
                  }
                  Refresh(false);
                }
              }
            });
          Refresh(false);
          setLayout(new BorderLayout());
          CboStamm.setMaximumRowCount(20);
          CboStamm.addKeyListener(new KeyListener()
          {
            public void keyPressed(KeyEvent e)
            {
              int ik = e.getKeyCode();
              if (ik == KeyEvent.VK_DELETE)
              {
                g.fixInfo("StammEingabe.Delete gedrückt");
                int iOld = CboStamm.getOld();
                CboStamm.setSelectedAIC(0);
                CboStamm.setAktAIC(iOld);
              }
            }

            public void keyReleased(KeyEvent e)
            {
            }

            public void keyTyped(KeyEvent e)
            {
              if (e.getKeyChar() == '*')
              {
                e.consume();
                OpenList();
              }
            }
          });
          JPanel Pnl=new JPanel(new GridLayout(1,2));
          BtnList = g.getButton("...");
          Pnl.add(BtnList);
          if (g.Def())
          {
        	  BtnAufruf = g.getButton("*");          
        	  Pnl.add(BtnAufruf);
        	  BtnAufruf.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                  OpenEF();
                }
              });
          }
          if (riStt<=0)
            add("West", CboStt);
          add("Center", CboStamm);
          add("East", Pnl);
          BtnList.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              OpenList();
            }
          });

	}
	
	public void useKennung(boolean b)
	{
		bUseKennung=b;
	}

  private void Refresh(boolean bSub)
  {
    long lClock=Static.get_ms();
    boolean bZR=CbxKeinSZR==null || !CbxKeinSZR.isSelected();
    int iStt=bBew ? (int)CboStt.getSelectedFaktor():CboStt.getSelectedAIC();
    if (CboRolle != null && iSttRolle==iStt)
      iRolle = CboRolle.getSelectedAIC();
    g.progInfo("Refresh:"+iRolle+"/"+iStt+"/"+iSttRolle+"/"+bZR);
    Tab=new Tabellenspeicher(g,"select aic_stamm aic,kennung ken,"+g.concat(g.ifnull("austritt","''","'_'"),"bezeichnung")+" kennung"+g.AU_Bezeichnung2("Stamm","p2")+",Eintritt,Austritt from stammview"+(bZR?"":"2")+" p2 where aic_rolle"+
                             (iRolle==0?" is null":"="+iRolle)+" and aic_stammtyp="+iStt+g.getMandanten(iStt)+(bUseKennung ? " and kennung is not null":""),true);
    int i=CboStamm.getOld();
    CboStamm.fillCbo(Tab,true);
    if (bSub)
    {
      int iAic=Out.getSelectedNode()==null ? 0:Tabellenspeicher.geti(Out.getSelectedNode().getUserData());
      CboStamm.setSelectedAIC(iAic);
      CboStamm.setAktAIC(i);
      fillOutliner();
      if (NodeMom != null)
        Static.makeVisible(Out, NodeMom);
    }
    else
      CboStamm.setSelectedAIC(g.getSyncStamm(iStt,iRolle));
    if (g.TestPC())
      g.clockInfo("Refresh StammEingabe",lClock);
  }
  
  private void OpenEF()
  {
	  int iStt=CboStt.getSelectedAIC();
	  int iStamm=CboStamm.getSelectedAIC();
//	  new EingabeFormular(g,CboStamm,iStt);
	  int iForm=SQL.getInteger(g, "select aic_begriff from begriff where aic_begriffgruppe=17 and aic_bewegungstyp is null and aic_rolle is null and aic_stammtyp="+iStt+" and web is null and bits&8>0 order by used desc");
	  EingabeFormular.HoleFormular(g,iForm>0 ? iForm:-iStt,null,iStamm,false);
  }

  private void OpenList()
  {
	//g.fixInfo("StammEingabe.OpenList");
    if (CboRolle==null)
      CboRolle=new ComboSort(g);
    if (iSttRolle != CboStt.getSelectedAIC())
    {
      iSttRolle = CboStt.getSelectedAIC();
      CboRolle.fillRolle(iSttRolle, true);
      CboRolle.setSelectedAIC(iRolle);
    }
    //g.fixInfo("StammEingabe: BuildFrame");
    if (Dlg==null)
          BuildFrame();
    //g.fixInfo("StammEingabe: fillOutliner");
    if (iSttRolle>0)
      fillOutliner();
    Dlg.pack();
    if (Static.bStdSize)
    	Dlg.setSize(450,800);
    else
    {
      int iH=Toolkit.getDefaultToolkit().getScreenSize().height;	
      //g.fixInfo("StammEingabe: iH="+iH);
      if (Dlg.getContentPane().getSize().height>iH-60)
        Dlg.setSize(new Dimension(Dlg.getSize().width,/*Dlg.getSize().height*/iH-60));
      //g.fixInfo("StammEingabe: Dim="+Dlg.getSize()+"-> centerComponent");
      Static.centerComponent(Dlg,PDlg);
    }
    //g.fixInfo("StammEingabe: setVisible");
    Dlg.setVisible(true);
  }

  private void fillOutliner()
  {
    Vector<Combo> Vec=CboStamm.getItems();
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    int iMom=CboStamm.getSelectedAIC();
    for(int i=0;i<Vec.size();i++)
    {
      Vector<Object> VecSpalten=new Vector<Object>();
      Combo c=(Combo)Vec.elementAt(i);
      int iAic=c.getAic();
      VecSpalten.addElement(c.getBezeichnung());
      if (g.Def())
      {
        if (Tab != null && Tab.posInhalt("Aic",iAic))
          VecSpalten.addElement(Tab.getS("Ken"));
        VecSpalten.addElement(new Integer(iAic));
      }
      if (Tab != null && Tab.posInhalt("Aic",iAic))
      {
        VecSpalten.addElement(Tab.isNull("Eintritt")?"":Transact.TS_String(Tab.getDate("Eintritt")));
        VecSpalten.addElement(Tab.isNull("Austritt")?"":Transact.TS_String(Tab.getDate("Austritt")));
      }
      else
      {
        VecSpalten.addElement(null);
        VecSpalten.addElement(null);
      }
      JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
      Node.setUserData(new Integer(iAic));
      if (iAic==iMom)
        NodeMom=Node;
    }
    Out.folderChanged(NodeRoot);
  }


  private void BuildFrame()
    {
      if (PDlg instanceof JFrame)
        Dlg = new JDialog((JFrame)PDlg,"Stamm-Auswahl",true);
      else
        Dlg = new JDialog((JDialog)PDlg,"Stamm-Auswahl",true);
      JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
      String [] s = g.Def() ? new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Nr"),g.getBegriffS("Show","Eintritt"),g.getBegriffS("Show","Austritt")}:
          new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Eintritt"),g.getBegriffS("Show","Austritt")};
      Out.setColumnButtons(s);
      Out.setNumColumns(s.length);
      /*Vector VecSpalten=new Vector();
      VecSpalten.addElement(Static.sKein);
      VecSpalten.addElement("<kein>");
      VecSpalten.addElement(Static.Int0);
      Out.getRootNode().setLabel(VecSpalten);
      Out.getRootNode().setUserData(Static.Int0);*/
      Out.setRootVisible(false);
      Dlg.getContentPane().add("Center",Out);
      JButton BtnOk = g.getButton("Ok");
      JButton BtnRefresh=g.getButton("Refresh");
      JButton BtnAbbruch = g.getButton("Abbruch");
      CbxKeinSZR = g.getCheckbox("kein_Stamm-Zeitraum");
      Dlg.getRootPane().setDefaultButton(BtnOk);
      Pnl.add(CboRolle);
      Pnl.add(CbxKeinSZR);
      Pnl.add(BtnRefresh);
      Pnl.add(BtnOk);
      Pnl.add(BtnAbbruch);
      BtnOk.addActionListener(new ActionListener()
      {
              public void actionPerformed(ActionEvent e)
              {
                Object O=Out.getSelectedNode().getUserData();
                int iAic=O instanceof Integer ?((Integer)O).intValue():0;
                int i=CboStamm.getOld();
                CboStamm.setSelectedAIC(iAic);
                CboStamm.setAktAIC(i);
                Dlg.setVisible(false);
              }
      });
      BtnRefresh.addActionListener(new ActionListener()
      {
              public void actionPerformed(ActionEvent e)
              {
                      Refresh(true);
              }
      });
      BtnAbbruch.addActionListener(new ActionListener()
      {
              public void actionPerformed(ActionEvent e)
              {
                      Dlg.setVisible(false);
              }
      });
      Action cancelKeyAction = new AbstractAction() {
        /**
		 *
		 */
		private static final long serialVersionUID = -7114281824060544203L;

		public void actionPerformed(ActionEvent e)
        {
          Dlg.setVisible(false);
        }
      };
      Static.Escape(BtnAbbruch,Dlg,cancelKeyAction);

      Dlg.getContentPane().add("South", Pnl);
      //Frm.setSize(700, 500);
      Dlg.addWindowListener(new WindowListener()
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
	public void fixStt(int iStt)
	{
		if (iStt==0)
			CboStt.setEnabled(true);
		else
		{
			CboStt.setSelectedAIC(iStt);
			CboStt.setEnabled(false);
		}
	}

        public void setStt(int iStt)
        {
          CboStt.setSelectedAIC(iStt);
        }

        public void setStamm(int iStamm)
        {
          CboStamm.setSelectedAIC(iStamm);
        }

        public void setStamm2(int iStamm)
        {
          if (iStamm>0)
            CboStt.setSelectedAIC(SQL.getInteger(g,"select aic_stammtyp from stamm where aic_stamm="+iStamm));
          CboStamm.setSelectedAIC(iStamm);
        }

	public All_Unlimited.Allgemein.Anzeige.Combo getCombo()
	{
		return CboStamm.getItem(CboStamm.getSelectedAIC());
	}

        public All_Unlimited.Allgemein.Anzeige.Combo getComboStt()
        {
                return new Combo(CboStt.getSelectedBezeichnung(),-CboStt.getSelectedAIC(),CboStt.getSelectedKennung(),0);
        }

        public int getStt()
        {
                return CboStt.getSelectedAIC();
        }

	public int getStamm()
	{
		return CboStamm.getSelectedAIC();
	}

	public String getBezeichnung()
	{
		return ""+CboStamm.getSelectedItem();
	}

        /*public String getBezeichnungStt()
        {
                return ""+CboStt.getSelectedItem();
        }*/

        public String getBezeichnung2(boolean bMit)
        {
          return isNull() ? ""+CboStt.getSelectedItem():(bMit && bBew ? CboStt.getSelectedItem()+".":"")+CboStamm.getSelectedItem();
        }

        public Vector<Integer> getVec()
        {
          Vector<Integer> Vec=new Vector<Integer>();
          Vec.addElement(new Integer(CboStamm.getSelectedAIC()));      // Stamm
          Vec.addElement(new Integer((int)CboStt.getSelectedFaktor()));// Stammtyp
          Vec.addElement(new Integer(CboStt.getSelectedAIC()));        // Eigenschaft
          return Vec;
        }

        public boolean Modified()
        {
          return CboStamm.Modified();
        }

        public ComboSort getComboBox()
        {
          return CboStamm;
        }

        public boolean isNull()
        {
          return CboStamm.getSelectedAIC()==0;
        }

        public void setEditable(boolean b)
        {
          CboStt.setEditable1(b);
          CboStamm.setEditable(b);
        }

        public void setEnabled(boolean b)
        {
          CboStt.setEnabled(b);
          CboStamm.setEnabled(b);
          BtnList.setEnabled(b);
        }

}

