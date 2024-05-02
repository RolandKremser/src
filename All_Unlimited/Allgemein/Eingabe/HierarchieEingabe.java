/*
    All_Unlimited-Allgemein-Eingabe-HierarchieEingabe.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Combo;
import java.awt.FlowLayout;

public class HierarchieEingabe extends javax.swing.JPanel
{

/**
	 *
	 */
	private static final long serialVersionUID = 3413370958379582284L;
public HierarchieEingabe(int riFomBegriff,int iAIC_Stammtyp_Von, int iAIC_Stammtyp_Bis, int riAIC_Eigenschaft, Global glob)
{
	g=glob;
        iFomBegriff=riFomBegriff;

        if (Static.bStern)
        {
          BtnList = g.getButton("...");
          BtnList.setFocusable(false);
          BtnList.setBorder(null);
        }
	//BtnList.setMargin(new Insets(1,1,1,1));
        //BtnList.setBackground(g.ColEF);
	iAIC_Eigenschaft = riAIC_Eigenschaft;
	Build(iAIC_Stammtyp_Von, iAIC_Stammtyp_Bis);
}

public HierarchieEingabe clone()
      {
        return new HierarchieEingabe(0,iVon,iBis,iAIC_Eigenschaft,g);
      }

private void BuildDlg()
{
	if (Frm==null)
	{

		Container Con=FrmThis;
		int i=0;
		while (!(Con instanceof JFrame) && i<30)
		{
			//g.progInfo(i+".:"+Con.getClass().getName()+"/"+Con);
			Con=Con.getParent();
			i++;
		}

		Frm = new JDialog((JFrame)Con,sTitle,true);
		Frm.getContentPane().setLayout(new BorderLayout(2,2));
		Frm.getContentPane().add("Center",Out);
                Frm.getRootPane().setDefaultButton(BtnOk);
                Action cancelKeyAction = new AbstractAction() {
                  /**
					 *
					 */
					private static final long serialVersionUID = 1563234061347792717L;

				public void actionPerformed(ActionEvent e) {
                    Frm.setVisible(false);
                  }
                };
                Static.Escape(BtnAbbruch, Frm, cancelKeyAction);

		JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,3,2));
                if (iAIC_Eigenschaft==g.iEigFirma)
                  Pnl.add(BtnAlle);
                Pnl.add(BtnRefresh);
		Pnl.add(BtnOk);
		Pnl.add(BtnAbbruch);
		Frm.getContentPane().add("South",Pnl);
		Frm.setSize(400,500);
                Out.requestFocus();
	}
}

      public void openList()
      {
        fillList();
        selectItem();
        BuildDlg();
        if (!bCenter)
        {
          Static.centerComponent(Frm, FrmThis);
          bCenter = true;
        }
        Static.bRefreshStop=true;
        Frm.setVisible(true);
      }

private void Build(int iAIC_Stammtyp_Von, int iAIC_Stammtyp_Bis)
{
	FrmThis = this;
        iVon=iAIC_Stammtyp_Von;
        iBis=iAIC_Stammtyp_Bis;
	int iPos=g.TabEigenschaften.getPos("AIC",iAIC_Eigenschaft);
	sTitle = (g.Def() ? "HierarchieEingabe - ":"")+g.TabEigenschaften.getS(iPos,"Bezeichnung");

	Out.setBackground(Color.white);
	Out.setRootVisible(false);

	Cbo1 = new ComboSort(g,ComboSort.kein);
    Cbo1.setFocusable(false);
    if (Static.bND)
    	Cbo1.setBackground(Static.ColWhite);
        //Cbo1.setBorder(new EmptyBorder(2,2,2,2));
	Cbo2 = new ComboSort(g);
        Cbo2.set(ComboSort.STAMM,true);
        //Cbo2.setBorder(new EmptyBorder(2,2,2,2));

	Cbo1.fillHierachieBis(iVon,iBis,false);
	fillStamm(Cbo1.getSelectedAIC());
        Cbo1.setPreferredSize(new java.awt.Dimension(100,16));
	//JPanel Pnl = new JPanel(new GridLayout(1,0,2,2));
	//Pnl.add(Cbo1);
	//Pnl.add(Cbo2);

	setLayout(new BorderLayout(0,0));
	add("West",Cbo1);
	add("Center",Cbo2);
        if (Static.bStern)
          add("East",BtnList);

	/*JPanel Pnl = new JPanel(new GridLayout(1,0,2,2));
	Pnl.add(BtnOk);
	Pnl.add(BtnAbbruch);
	//Pnl.add(BtnAbfrage);
	Frm.getContentPane().setLayout(new BorderLayout(2,2));
	Frm.getContentPane().add("Center",Out);
	Frm.getContentPane().add("South",Pnl);
	Frm.setSize(300,500);*/

	//fillList();

        addFocusListener(new FocusListener()
        {
                        public void focusGained(FocusEvent fe)
                        {
                          Cbo2.requestFocus();
                        }
                        public void focusLost(FocusEvent fe)
                        {
                        }
        });

	Cbo1.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent ev)
		{
			if(bRefresh && ev.getStateChange() == ItemEvent.SELECTED)
			{
				g.progInfo("Cbo1.itemStateChanged: fill"+Cbo1.getSelectedAIC());
				fillStamm(Cbo1.getSelectedAIC());
                                selectItem();
			}
		}
	});

	Cbo2.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent ev)
		{
			if(ev.getStateChange() == ItemEvent.SELECTED)
				selectItem();
		}
	});

        Cbo2.addKeyListener(new KeyListener()
                {
                        public void keyPressed(KeyEvent e)
                        {
                        }
                        public void keyReleased(KeyEvent e)
                        {
                        }
                        public void keyTyped(KeyEvent e)
                        {
                          //g.progInfo(e.getKeyCode()+"/"+((int)'*'));
                          if(e.getKeyChar()=='*')
                          {
                            e.consume();
                            openList();
                          }
                        }
        });
        Cbo2.getComboSortEditor().addMouseListener(new MouseListener()
	      {
	        public void mousePressed(MouseEvent ev)
	        {}

	        public void mouseClicked(MouseEvent ev)
	        {
	          //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
	          if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
	          {
	            openList();
	          }
	        }

	        public void mouseEntered(MouseEvent ev)
	        {}

	        public void mouseExited(MouseEvent ev)
	        {}

	        public void mouseReleased(MouseEvent ev)
	        {}
	      });

          ActionListener AL=new ActionListener()
          {
            public void actionPerformed(ActionEvent ev) {
              String s = ev.getActionCommand();
              g.progInfo("Action=" + s);
              if(s.equals("List"))
                openList();
              else if(s.equals("Alle") || s.equals("Refresh"))
              {
                if(s.equals("Alle"))
                  iFirma = 0;
                ((JCOutlinerFolderNode)Out.getRootNode()).removeChildren();
                int iAIC = Cbo2.getSelectedAIC();
                //int iOld=Cbo2.getOld();
                fillStamm(Cbo1.getSelectedAIC());
                Cbo2.setSelectedAIC(iAIC,null,null,false);
                fillList();
                Out.folderChanged(Out.getRootNode());
                selectItem();
              }
              else if(s.equals("Ok"))
              {
                      JCOutlinerNode Node = Out.getSelectedNode();
                      //g.progInfo("Node="+Node);
                      if(Node!=null)
                      {
                              Vector Vec = (Vector)Node.getUserData();
                              g.progInfo("Vec="+Vec);
                              bRefresh=false;
                              Cbo1.setSelectedAIC(((Integer)Vec.elementAt(1)).intValue(),null,null,false);
                              fillStamm(((Integer)Vec.elementAt(1)).intValue());
                              Cbo2.setSelectedAIC(((Integer)Vec.elementAt(0)).intValue(),null,null,false);
                              bRefresh=true;
                      }
                      else
                              Cbo2.setSelectedAIC(0);
                      Frm.setVisible(false);
              }
              else if(s.equals("Abbruch"))
                Frm.setVisible(false);
            }
          };
          if (Static.bStern)
            Global.setAction(BtnList,"List",AL);
          BtnAlle = g.getButton("alle","Alle",AL);
          BtnRefresh = g.getButton("Refresh","Refresh",AL);
          BtnOk = g.getButton("Ok","Ok",AL);
          BtnAbbruch = g.getButton("Abbruch","Abbruch",AL);
          //BtnAbfrage = g.getButton("Abfrage");


	//if(iStt!=0)
	//	setValue(iStt,iStamm);

}

private void fillList()
{
	TabKnoten = new Tabellenspeicher(g,new String[]{"Stamm","Knoten"});
        ((JCOutlinerFolderNode)Out.getRootNode()).removeChildren();
	SQL Qry = new SQL(g);
	Vector VecStt = Cbo1.getItems();
        //g.progInfo("VecStt="+VecStt);
        //int iSttLast=0;
        boolean bFirst=true;
	for(int i=0;i<VecStt.size();i++)
	{
          int iMom=((Combo)VecStt.elementAt(i)).getAic();
          int iPosS = g.getPosFirstStt(iMom);
          int iStt = g.TabStammtypen.getI(iPosS,"Aic");
          boolean bCopy=(g.TabStammtypen.getI(iPosS,"bits")&Global.cstCopy)>0;
          //g.progInfo(i+":"+VecStt.elementAt(i)+"/"+iStt+"/"+iMom);
          if (/*iStt != iSttLast && */iStt==iMom)
          {
            //iSttLast=iStt;
            String sMandanten=bCopy ? g.getCopyMandanten(false):g.getReadMandanten();
                String sSQL=null;
		if(bFirst)
			sSQL="SELECT AIC_Stamm, Bezeichnung FROM Stammview p2 WHERE AIC_Stammtyp="+iStt+sMandanten+(iFirma>0 ? " and (firma="+iFirma+" or firma is null)":"")+" and aic_rolle is null ORDER BY Bezeichnung";
		else if (TabKnoten.getVecSpalte("Stamm").size()>0)
			sSQL="SELECT distinct P2.AIC_Stamm, Bezeichnung, STA_AIC_Stamm FROM Stammview p2 JOIN Stammpool on Stammpool.aic_stamm=p2.aic_stamm"+(iFirma>0 ? " and (firma="+iFirma+" or firma is null)":"")+sMandanten+
			         " WHERE AIC_Eigenschaft="+iAIC_Eigenschaft+" AND AIC_Stammtyp="+iStt+" and aic_rolle is null AND PRO2_AIC_Protokoll IS NULL "+
					 "AND"+g.in("STA_AIC_Stamm",TabKnoten.getVecSpalte("Stamm"))+"ORDER BY Bezeichnung";
                if (sSQL != null)
                {
                  //g.progInfo(sSQL);
                  Qry.open(sSQL);
                  JCOutlinerNodeStyle Style = g.getSttStyle(iStt);
                  for (; !Qry.eof(); Qry.moveNext())
                  {
                    JCOutlinerFolderNode NodeParent = null;
                    if (bFirst)
                      NodeParent = (JCOutlinerFolderNode)Out.getRootNode();
                    else
                    {
                      TabKnoten.posInhalt("Stamm", Qry.getI("STA_AIC_Stamm"));
                      NodeParent = (JCOutlinerFolderNode)TabKnoten.getInhalt("Knoten");
                    }

                    Vector<Integer> VecInvisible = new Vector<Integer>();
                    VecInvisible.addElement(new Integer(Qry.getI("AIC_Stamm")));
                    VecInvisible.addElement(new Integer(iStt));

                    JCOutlinerFolderNode Node = new JCOutlinerFolderNode(Qry.getS("Bezeichnung"), NodeParent);
                    Node.setUserData(VecInvisible);
                    if (Style != null)
                      Node.setStyle(Style);
                    TabKnoten.addInhalt("Stamm", new Integer(Qry.getI("AIC_Stamm")));
                    TabKnoten.addInhalt("Knoten", Node);
                  }
                  bFirst = false;
                  Qry.close();
                }
          }
	}
        //TabKnoten.showGrid("TabKnoten");
	Qry.free();
}

private void selectItem()
{
	//g.progInfo("HierarchieEingabe.selectItem");
	if(TabKnoten != null && TabKnoten.posInhalt("Stamm",getValueStamm()))
		Static.makeVisible(Out,(JCOutlinerFolderNode)TabKnoten.getInhalt("Knoten"));
}

public void setValue(int iStt,int iStamm)
{
	//g.progInfo("HierarchieEingabe.setValue: Stt="+iStt+", Stamm="+iStamm);
	bRefresh = false;
	if (Cbo1.getSelectedAIC() != iStt)
	{

                if (iStt>0)
                {
                  fillStamm(iStt);
                  Cbo1.setSelectedAIC(iStt);
                }
                else
                {
                  Cbo1.setFirst();
                 fillStamm(Cbo1.getSelectedAIC());
                }
	}
	Cbo2.setSelectedAIC(iStamm);
        Cbo2.setEditable1(Cbo1.get(ComboSort.EDIT));

	iOldStamm = iStamm;

	selectItem();
        //Cbo2.setEnabled(isEnabled());
	bRefresh = true;
}

private void fillStamm(int i)
{
  Cbo2.fillStammTable(iFomBegriff,i,iFirma,true);
}

public void setFirma(int i)
{
  if (i!=iFirma && iAIC_Eigenschaft==g.iEigFirma)
  {
    iFirma=i;
    fillStamm(Cbo1.getSelectedAIC());
  }
}

public void setStamm(int i)
{
	iOldStamm=i;
        Cbo2.Reset();
}

public void Reset2()
{
  Cbo1.Reset2();
  Cbo2.Reset2();
}

public String toString()
{
	return getValueStamm()!=0?Cbo1.getSelectedBezeichnung()+" - "+Cbo2.getSelectedBezeichnung():"";
}

public int getValueStt()
{
	return Cbo1.getSelectedAIC();
}
public int getValueStamm()
{
	return Cbo2.getSelectedAIC();
}

public boolean Modified()
{
	return iOldStamm != getValueStamm();
}

public boolean isNull()
{
	return Cbo2.isNull();
}

public boolean wasNull()
      {
        return iOldStamm==0;
      }

public Component getComboStammEditor()
{
	return Cbo2.getComboSortEditor();
}

public void setEnabled(boolean b)
{
	Cbo1.setEnabled(b);
        //Cbo2.setEditable(Cbo1.bEdit);
	Cbo2.setEnabled(b);
        //Cbo2.setEditable(Cbo1.bEdit);
        if (Static.bStern)
          BtnList.setEnabled(b && Cbo1.get(ComboSort.EDIT));
}

public void setEditable(boolean b)
{
  Cbo1.setEditable1(b);
  Cbo2.setEditable1(b);
  if (Static.bStern)
    BtnList.setEnabled(b);
}


public void setFont(Font font)
{
	if (Cbo1 !=null)
	{
		if (font==null)
			font=Transact.fontStandard;
		Cbo1.setFont(font);
		Cbo2.setFont(font);
                if (Static.bStern)
                  BtnList.setFont(font);
	}
}

      public Combo getCombo()
      {
        return Cbo2.getCombo();
      }


// add your data members here
private Global g;
private ComboSort Cbo1;
public ComboSort Cbo2;
private int iOldStamm = 0;
private int iFomBegriff=0;
private int iVon=0;
private int iBis=0;
private int iFirma=0;
private JButton BtnList;
private JButton BtnAlle;
private JButton BtnRefresh;
private JButton BtnOk;
private JButton BtnAbbruch;
//private JButton BtnAbfrage;
private JDialog Frm = null;
private HierarchieEingabe FrmThis;
private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode("Root"));
private boolean bCenter = false;

private int iAIC_Eigenschaft;
private Tabellenspeicher TabKnoten;
private String sTitle;
private boolean bRefresh=true;
}

