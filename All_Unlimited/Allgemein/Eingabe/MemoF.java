package All_Unlimited.Allgemein.Eingabe;

import java.awt.FlowLayout;
import javax.swing.*;
import All_Unlimited.Allgemein.Global;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import All_Unlimited.Allgemein.Static;
import java.awt.Graphics;
//import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;

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
public class MemoF extends JDialog
{
  /**
	 *
	 */
	private static final long serialVersionUID = -5729869022596670283L;
public AUTextArea Txt;
  public boolean bOk=false;
  //private Global g;
  private int i=0;
  private boolean bRF=true;

  public MemoF(JFrame Fom,String sTitel,Global rg)
  {
    super(Fom,sTitel,true);
    Build(rg,true,0);
  }
  
  public MemoF(JDialog Fom,String sTitel,Global rg)
  {
    super(Fom,sTitel,true);
    Build(rg,false,0);
  }

  public MemoF(JFrame Fom,String sTitel,Global rg,boolean bModal,boolean bEdit,int iMaxL)
  {
    super(Fom,sTitel,bModal);
    Build(rg,bEdit,iMaxL);
  }

  private void Build(Global rg,boolean bEdit,int iMaxL)
  {
    //super(Fom,sTitel,true);
    //g=rg;
    Txt=new AUTextArea(rg, 3);
    Txt.setBorder(new EmptyBorder(new Insets(5,5,2,2)));
    Txt.setEditable(bEdit);
    if (iMaxL>0)
    	Txt.setMaxLength(iMaxL);
    getContentPane().add("Center",Txt);
    setSize(400,400);
    JPanel Pnl=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
    JButton BtnOk=rg.getButton("Ok");
    BtnOk.setName("Ok");
    JButton BtnCancel = rg.getButton("Abbruch");
    BtnCancel.setName("Abbruch");
    if (bEdit)
    {
      getRootPane().setDefaultButton(BtnOk);
      Pnl.add(BtnOk);
    }
    else
      getRootPane().setDefaultButton(BtnCancel);
    Pnl.add(BtnCancel);
    Txt.Edt.addKeyListener(new KeyListener()
    {
            public void keyTyped(KeyEvent e) { /*g.fixInfo("keyTyped:"+e.getKeyChar()+"/"+e.getKeyCode());*/}
            public void keyPressed(KeyEvent e)
            {
              //g.fixInfo("keyPressed:"+e.getKeyCode());
              if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
              {
                hide2(false);
              }
            }
            public void keyReleased(KeyEvent e) {}
      });
    Action cancelKeyAction = new AbstractAction()
    {
      /**
		 *
		 */
		private static final long serialVersionUID = -8413225354579796354L;

	public void actionPerformed(ActionEvent e) {
        hide2(false);
      }
    };
    Static.Escape(BtnCancel,this,cancelKeyAction);

    ActionListener Act=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s=((JComponent)ev.getSource()).getName();
        hide2(s.equals("Ok"));
      }
    };
    //Static.Escape(BtnCancel,this,Act);
    BtnOk.addActionListener(Act);
    BtnCancel.addActionListener(Act);

    getContentPane().add("South",Pnl);
  }

  private void hide2(boolean rbOk)
  {
    bOk=rbOk;
    bRF=true;
    //g.fixInfo("MemoF:"+s+"/"+bOk);
    setVisible(false);
  }

  public void show2()
  {
    if (i==0)
      Static.centerComponent(this,getOwner());
    i++;
    Txt.validate();
    setVisible(true);
  }

  public void paint(Graphics gr)
  {
    super.paint(gr);
    if (bRF)
    {
      bRF=false;
      Txt.requestFocus();
    }
  }
}
