/*
    All_Unlimited-Allgemein-Memo.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import All_Unlimited.Allgemein.Eingabe.AUTextArea;


//import javax.swing.plaf.FontUIResource;
//import javax.swing.JScrollPane;
//import All_Unlimited.Allgemein.Eingabe.HtmlEingabe;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Memo extends java.lang.Object
{
    /*public Memo(Vector VecText, Vector VecTitel, int iElement, String Ueberschrift,Window frame, Global g)
    {
		//super(frame,Ueberschrift,true);
                if (frame instanceof JFrame)
                  thisFrame = new JDialog((JFrame)frame,Ueberschrift,true);
                else
                  thisFrame = new JDialog((JDialog)frame,Ueberschrift,true);
                thisFrame.setSize(400,500);
                Static.centerComponent(thisFrame,frame);
		bModal = true;
		Work(VecText,VecTitel,iElement,true,g);
    }*/

    public Memo(Vector<String> Vec, int iElement, String Ueberschrift,Window frame, Global g)
    {
		//super(frame,Ueberschrift,true);
                if (frame instanceof JFrame)
                  thisFrame = new JDialog((JFrame)frame,Ueberschrift,true);
                else
                  thisFrame = new JDialog((JDialog)frame,Ueberschrift,true);
                thisFrame.setSize(400,500);
                Static.centerComponent(thisFrame,frame);
		bModal = true;
		//int iPos=iElement*2;
		//g.fixtestInfo("Memo mit Vec="+Vec);
		Work(Vec,true,g);
		/*Vector<Object> VecText = new Vector<Object>();
		Vector<Object> VecTitel = new Vector<Object>();

		VecTitel.addElement(Vec.elementAt(iPos));
		VecText.addElement(Vec.elementAt(iPos+1));
		Work(VecText,VecTitel,0,true,g);

		Vec.setElementAt((String)VecTitel.elementAt(0),iPos);
		Vec.setElementAt((String)VecText.elementAt(0),iPos+1);*/

    }

	/*public Memo(String sTitel, String sText, Global g)
	{
		//super(frame,sTitel,false);
		thisFrame = new JFrame(sTitel);
		bModal = false;
		Vector<Object> Vec1=new Vector<Object>();
		Vec1.addElement(sText);
		Vector<Object> Vec2=new Vector<Object>();
		Vec2.addElement(sTitel);
		Work(Vec1,Vec2,0,sTitel,false,g);
	}*/

	public void Refresh(String sTxt,Global g)
	{
		EdtText.setText(sTxt);
                LblAnzahl.setText(""+sTxt.length());
                if (sTxt.indexOf("<body")<0)
                  sTxt=sTxt.replaceAll("\n","<br>");
                g.fixtestInfo("Memo.Refresh:"+sTxt);
                EdtResult.setText(sTxt.replaceAll("<Hilfe>","<p>"));
	}

	private void Make_Layout(boolean bAenderbar,Global g)
	{
		JPanel Pnl1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,3,3));
                if(bAenderbar)
		{
			if (g.Def()) Pnl1.add(BtnOk);
			if(bModal)
                        {
						  JPanel Pnl=new JPanel(new BorderLayout(2,2));
						  Pnl.add("North",EdtTitel);
						  Pnl.add("Center",EdtHeader);
                          JSplitPane SP = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,Pnl,LblAnzahl);
                          SP.setOneTouchExpandable(true);
                          SP.setResizeWeight(0.9);
                          thisFrame.getContentPane().add("North", g.Def() ? SP:EdtTitel);
                        }
		}
                Pnl1.add(BtnRefresh);
		Pnl1.add(BtnAbbruch);
		if(bModal)
                {
                  //JPanel PnlC=new JPanel(new GridLayout(2,1));
                  GroupBox GP = new GroupBox("Ergebnis");
                  GP.add(EdtResult);
                  JSplitPane PnlC = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,EdtText,GP);
                  PnlC.setOneTouchExpandable(true);
                  PnlC.setResizeWeight(0.5);
                  thisFrame.getContentPane().add("Center", g.Def() ? PnlC:EdtResult);
                  thisFrame.getContentPane().add("South", Pnl1);
                }
                thisFrame.setVisible(true);
		//repaint();
	}

	private void Work(final Vector<String> Vec, boolean bAenderbar,final Global g)
	{
		//boolean b = new Parameter(g,Ueberschrift).getFenster(thisFrame);
		//if(!b)
			//thisFrame.setSize(300,200);
                EdtResult.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
                EdtResult.setFont(Transact.fontStandard);

                EdtResult.setContentType("text/html");
                EdtResult.setEditable(false);
                //EdtResult.setBackground(java.awt.Color.WHITE);

		BtnOk = g.getButton("Ok");
                thisFrame.getRootPane().setDefaultButton(BtnOk);
		if(bAenderbar)
			BtnAbbruch = g.getButton("Abbruch");
		else
			BtnAbbruch = g.getButton("Beenden");

		if(Vec.elementAt(1)==null)
			EdtText = new AUTextArea(g,0);
		else
        {
          EdtText = new AUTextArea(g,0);
          Refresh(Vec.elementAt(1),g);
        }
		if(Vec.elementAt(0)==null)
			EdtTitel = new JTextField();
		else
			EdtTitel = new JTextField((String)Vec.elementAt(0));
		if(Vec.size()<3 || Vec.elementAt(2)==null)
			EdtHeader = new JTextField();
		else
			EdtHeader = new JTextField((String)Vec.elementAt(2));

		//PnlSP = new JScrollPane(EdtText);

		//BtnMail = g.getButton("E-Mail");
                BtnRefresh = g.getButton("Refresh");
                BtnRefresh.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          g.setTooltip(EdtText.getValue(),EdtText.Edt);
                        }
                });

		BtnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Vec.setElementAt(EdtText.getValue(),1);
				Vec.setElementAt(EdtTitel.getText(),0);
				if (Vec.size()<3)
					Vec.addElement(EdtHeader.getText());
				else
					Vec.setElementAt(EdtHeader.getText(),2);
				//new Parameter(g,Ueberschrift).setFenster(thisFrame);
				thisFrame.setVisible(false);
			}
		});

		BtnAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				//new Parameter(g,Ueberschrift).setFenster(thisFrame);
				thisFrame.setVisible(false);
			}
		});

                EdtText.Edt.addKeyListener(new KeyListener()
                {
                        public void keyPressed(KeyEvent e)
                        {}
                        public void keyReleased(KeyEvent e)
                        {
                          String s=EdtText.getValue();
                          if (s.indexOf("<body")<0)
                            s=s.replaceAll("\n","<br>");
                          //Global.fixInfo("EdtText="+s);
                          EdtResult.setText(s.replaceAll("<Hilfe>","<p>"));
                          LblAnzahl.setText(""+EdtText.getValue().length());
                        }
                        public void keyTyped(KeyEvent e)
                        {
                        }
                });

                /*EdtResult.addKeyListener(new KeyListener()
                {
                        public void keyPressed(KeyEvent e)
                        {}
                        public void keyReleased(KeyEvent e)
                        {
                          g.progInfo("Zurück:"+EdtResult.getText());
                        }
                        public void keyTyped(KeyEvent e)
                        {
                        }
                });*/


		/*BtnMail.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				new Message(Message.WARNING_MESSAGE,Static.FomStart,g).showDialog("MailFehlt");
				//new MailFenster("","","",bModal?((JDialog)thisFrame).getTitle():((JFrame)thisFrame).getTitle(),EdtText.getText(),g);
			}
		});*/
		/*
		addWindowListener(new WindowListener()
		{
			public void windowClosed(WindowEvent e)
			{
			}
			public void windowOpened(WindowEvent e)
			{
			}
			public void windowClosing(WindowEvent e)
			{
				new Parameter(g,Ueberschrift).setFenster(This);
				dispose();
			}
			public void windowIconified(WindowEvent e)
			{
			}
			public void windowDeiconified(WindowEvent e)
			{
			}
			public void windowActivated(WindowEvent e)
			{
			}
			public void windowDeactivated(WindowEvent e)
			{
			}
		});
		*/

		Make_Layout(bAenderbar,g);

	}

   // add your data members here
    //private Memo This= this;
	private JButton BtnOk;
	private JButton BtnAbbruch;
    private JButton BtnRefresh;
	//private JButton BtnMail;
	private JTextField EdtTitel;
	private JTextField EdtHeader;
    private JLabel LblAnzahl=new JLabel("0",JLabel.RIGHT);
	private AUTextArea EdtText;
    private JEditorPane EdtResult=new JEditorPane();
    //private JButton EdtResult=new JButton();
	//private JScrollPane PnlSP;
	private boolean bModal;
	private JDialog thisFrame;
}

