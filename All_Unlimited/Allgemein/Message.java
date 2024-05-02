/*
    All_Unlimited-Allgemein-Message.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
//import javax.swing.JViewport;
//import java.awt.Point;

import java.awt.Insets;

import javax.swing.border.TitledBorder;

import All_Unlimited.Allgemein.Eingabe.AUOutliner;

import java.awt.Dimension;

public class Message extends javax.swing.JDialog
{
  /**
	 *
	 */
	private static final long serialVersionUID = 5046187183730507532L;
public Message(int iMessageType, JFrame FrmOwner, Global glob) {
    this(iMessageType, FrmOwner, glob, 0);
  }

  public Message(int iMessageType, JFrame FrmOwner, Global glob, int iDauer) {
    super(FrmOwner, (iMessageType&UNMODAL)==0);//iDauer>=0);
    bShow=iDauer>=0;
    Rest(iMessageType, FrmOwner, glob, iDauer);
  }

  //public Message(int iMessageType, JDialog FrmOwner, Global glob) {
  //  this(iMessageType, FrmOwner, glob, 0);
  //}

  public Message(int iMessageType, JDialog FrmOwner, Global glob, int iDauer) {
    super(FrmOwner, true);
    Rest(iMessageType, FrmOwner, glob, iDauer);
  }

 /* public Message(int iMessageType, Window FrmOwner, Global glob, int iDauer) {
    if (FrmOwner instanceof JDialog)
      super(iMessageType, (JDialog)FrmOwner, glob, iDauer);
    else
      super(iMessageType, (JFrame)FrmOwner, glob, iDauer);
  }*/

public static boolean KennungWarnung(Global g,String sKennung,int iBegriff,int iBG, Window FrmOwner)
{
      if(sKennung.equals(""))
      {
        showWarnung(g, FrmOwner,"KennungLeer");
        return true;
      }
      else if(SQL.exists(g,"select aic_begriff from Begriff WHERE aic_begriff<> " + iBegriff + " and AIC_Begriffgruppe="+iBG+
                                             " and Begriff.Kennung='" +sKennung + "'"))
      {
        showWarnung(g, FrmOwner,"KennungVorhanden");
        return true;
      }
      else
        return false;
}

public static void showWarnung(Global g, Window FrmOwner,String s)
{
      if (FrmOwner instanceof JDialog)
        new Message(WARNING_MESSAGE, (JDialog)FrmOwner, g,10).showDialog(s);
      else
        new Message(WARNING_MESSAGE, (JFrame)FrmOwner, g,10).showDialog(s);
}

public static boolean SecCheck(Global g, Window w,String s,String sPW)
{
	bOk=false;
	Vector<String> Vec=g.getTranslate("Message",s,null,true);
	final JDialog Dlg=new JDialog(new JFrame(),Vec.elementAt(0)/*"Sicherheits-Frage"*/,true);
	Dlg.setMinimumSize(new Dimension(300,120));
	JLabel Lbl=new JLabel(Vec.elementAt(1)/*"Warum benutzen?"*/);
	Lbl.setBorder(new EmptyBorder(new Insets(10,10,5,10)));
	//Lbl.setBounds(5, 5, 300, 200);
	//((BorderLayout)Dlg.getRootPane()).setMargin();
    Dlg.getContentPane().add("North",Lbl);
    final JPasswordField EdtPW = new JPasswordField();
    EdtPW.setBorder(new javax.swing.border.LineBorder(Lbl.getBackground(),5));
    JButton BtnOk = g.getButton("Ok");
    JButton BtnAbbruch = g.getButton("Abbruch");
    Dlg.getRootPane().setDefaultButton(BtnOk);
    BtnOk.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
          bOk=new String(EdtPW.getPassword()).equals(sPW);//"wegen M$");
          Dlg.dispose();
          if (!bOk)
        	  if (w instanceof JDialog)
        		  new Message(Message.WARNING_MESSAGE,(JDialog)w,g,10).showDialog("keineBerechtigung");
        	  else
        		  new Message(Message.WARNING_MESSAGE,(JFrame)w,g).showDialog("keineBerechtigung");
          //Dlg.setVisible(false);
        }
    });
    BtnAbbruch.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
          Dlg.dispose();
        }
    });
    JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));
    PnlS.add(BtnOk);
    PnlS.add(BtnAbbruch);
    ((BorderLayout)Dlg.getContentPane().getLayout()).setVgap(5);
    Dlg.getContentPane().add("Center",EdtPW);
    Dlg.getContentPane().add("South",PnlS);
    Dlg.pack();
    Static.centerComponent(Dlg,w);
    Dlg.setVisible(true);
	return bOk;
}

public void Rest(int iMessageType, Window FrmOwner, Global glob,int iDauer)
{
	setDefaultCloseOperation(bShow ? WindowConstants.DISPOSE_ON_CLOSE:WindowConstants.DO_NOTHING_ON_CLOSE);
	g=glob;
	iFF=g.getFontFaktor();
	Count.add(Count.Message);
	iMT=iMessageType & MESSAGE_TYPE;
        //getContentPane().setBackground(java.awt.Color.RED);
        FrmO=FrmOwner;
	//setSize(360,150);
        Col=new java.awt.Color(249,249,249);//java.awt.Color.lightGray.brighter();
        JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));//new GridLayout(1,0,2,2));
	JPanel PnlX=new JPanel(new BorderLayout(2,2));
        TB=new TitledBorder("");
        TB.setBorder(new javax.swing.border.LineBorder(g==null ? java.awt.Color.BLACK:Global.ColRahmen,3,true));
        Pnl.setBackground(Col);
        PnlX.setBorder(TB);
        PnlX.setOpaque(true);
        PnlX.setBackground(Col);//java.awt.Color.GREEN);
        getContentPane().add("Center",PnlX);
        //PnlX.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
	//setContentPane().setLayout(PnlX);
        //TxtInfo.setAutoscrolls(true);
	Scroll=new JScrollPane(TxtInfo);
        Scroll.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
        //Scroll.setH
        Scroll.setBackground(Col);//java.awt.Color.YELLOW);
	//Scroll.setScrollPosition(0,0);
	//View=new JViewport();
	//View.setViewPosition(new Point(0,0));
	//Scroll.setViewport(View);
        if ((iMessageType & SHOW_TAB)>0)
        {
          PnlX.add("North", Scroll);
          PnlTab=new JPanel(new GridLayout(1,0,2,2));
          PnlX.add("Center", PnlTab);
        }
        else
        {
          PnlX.add("Center", Scroll);
          JPanel Pnl2 = new JPanel(new BorderLayout(2, 2));
          Pnl2.setOpaque(true);
          Pnl2.setBackground(Col); //java.awt.Color.BLUE);
          PnlX.add("West", Pnl2);
          Pnl2.add("North", LblPic);
        }
	PnlX.add("South",Pnl);

	TxtInfo.setBorder(new EmptyBorder(TxtInfo.getInsets()));
	TxtInfo.setEditable(false);
	TxtInfo.setOpaque(true);
        if (g!=null)
          TxtInfo.setFont(Transact.fontStandard);
	//TxtInfo.setLineWrap(true);
	//TxtInfo.setWrapStyleWord(true);

        TxtInfo.setContentType("text/html");

        //TxtInfo.setBackground(getContentPane().getBackground());
        TxtInfo.setBackground(Col);//java.awt.Color.CYAN);
        JButton BtnESC=null;
        Dimension D=new Dimension(120*iFF/100,24*iFF/100);

	if (iDauer>0)
	{
		lStop=Static.get_ms()+iDauer*1000;
		timer= new javax.swing.Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				long l=lStop-Static.get_ms();
				g.progInfo("Message:"+l);
				if (l<0)
				{
					timer.stop();
					dispose();
				}
				else if (isShowing())
					toFront();
			}
		});
		timer.start();
	}
        if((iMessageType&EXPORT)>0)
	{
          JButton Btn=g.getButtonS("Export");
          Btn.setPreferredSize(D);
          Pnl.add(Btn);
          Btn.addActionListener(new ActionListener()
          {
                  public void actionPerformed(ActionEvent ev)
                  {
                    if (Tab!=null) Tab.Export();
                  }
          });

        }
	if(iMT==QUESTION_MESSAGE || iMT==YES_NO_OPTION)
	{
		JButton Btn=g.getButtonS("_Ja");
                Btn.setEnabled(true);
                Btn.setPreferredSize(D);
		Pnl.add(Btn);
                getRootPane().setDefaultButton(Btn);
		Btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				iRet = YES_OPTION;
				close();
			}
		});

		Btn=g.getButtonS("_Nein");
                Btn.setEnabled(true);
                Btn.setPreferredSize(D);
		Pnl.add(Btn);
                BtnESC=Btn;

                /*Action cancelKeyAction = new AbstractAction()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    dispose();
                  }
                };
                KeyStroke cancelKeyStroke = KeyStroke.getKeyStroke((char)KeyEvent.VK_ESCAPE, false);
                InputMap inputMap = Btn2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
                ActionMap actionMap = Btn2.getActionMap();
                if (inputMap != null && actionMap != null) {
                  inputMap.put(cancelKeyStroke, "cancel");
                  actionMap.put("cancel", cancelKeyAction);
                }*/

		Btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				iRet = NO_OPTION;
				close();
			}
		});
                if (g.TabBegriffe != null && (iMessageType & SHOW_TAB)==0)
                {
                  int iPos=g.getPosBegriff("Show", "Frage");
                  if (iPos>=0)
                  {
                    Image Img = g.getImageBegriff(iPos);
                    if (Img != null)LblPic.setIcon(new ImageIcon(Img));
                  }
                }
	}

	else if((iMT>=12 && iMT<=15) || (iMT>=5 && iMT<=7) || iMT==OK_OPTION)
	{
		JButton Btn;
		Btn=g.getButtonS("_Ok");
                Btn.setEnabled(bShow);
                if (bShow)
                  getRootPane().setDefaultButton(Btn);
                Btn.setPreferredSize(D);
		Pnl.add(Btn);
                if (bShow)
		Btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				iRet = OK_OPTION;
				close();
			}
		});
                BtnESC=Btn;

		if(iMT==13 || iMT==WARNING_MESSAGE)
		{
		  int iPos=g.getPosBegriff("Show","Warnung");
			if(iPos>=0)
			{
				Image Img = g.getImageBegriff(iPos);
				if(Img!=null) LblPic.setIcon(new ImageIcon(Img));
			}
		}
		else if(iMT==14 || iMT==INFORMATION_MESSAGE)
		{
		  int iPos=g.getPosBegriff("Show","Info");
			if(iPos>=0)
			{
				Image Img = g.getImageBegriff(iPos);
				if(Img!=null) LblPic.setIcon(new ImageIcon(Img));
			}
		}
		else if(iMT==15 || iMT==ERROR_MESSAGE)
		{
		  int iPos=g.getPosBegriff("Show","Error");
			if(iPos>=0)
			{
				Image Img = g.getImageBegriff(iPos);
				if(Img!=null) LblPic.setIcon(new ImageIcon(Img));
                                //Static.printError(g==null ? TxtInfo.getText():g.clearHtml2(TxtInfo.getText()));
			}
		}
	}

	if((iMT&CANCEL_OPTION)>0)
	{
		JButton Btn;
		Btn=g.getButtonS("_Abbruch");
                Btn.setEnabled(true);
                Btn.setPreferredSize(D);
		Pnl.add(Btn);
                BtnESC=Btn;
		Btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				iRet = CANCEL_OPTION;
				close();
			}
		});
	}
        Action cancelKeyAction = new AbstractAction()
            {
              /**
				 *
				 */
				private static final long serialVersionUID = 5728846318971416435L;

			public void actionPerformed(ActionEvent e)
              {
                iRet = (iMT&8)>0 ? CANCEL_OPTION:NO_OPTION;
                close();
              }
            };
        Static.Escape(BtnESC,this,cancelKeyAction);
}

      public void close()
      {
        if (timer != null)
          timer.stop();
        dispose();
      }

public void finalize()
{
	Count.sub(Count.Message);
}

public int showDialog(String sKennung)
{
	return(showDialog(sKennung,new String[] {},null));
}

public int showDialog(String sKennung, String[] sArray)
{
  return(showDialog(sKennung,sArray,null));
}

public int showDialog(String sKennung, Tabellenspeicher Tab)
{
  return(showDialog(sKennung,new String[] {},Tab));
}

private void setText(String s)
{
  boolean b=s.indexOf("<")<0;
  //TxtInfo.setContentType(b ? "text/rtf":"text/html");
  //TxtInfo.setContentType(b ? "text/plain":"text/html");
  //if (b)
    s="<span style=\"font-family:"+Static.sFont+"\">"+(b ? s.replaceAll("\n","<br>"):g.getBody(s))+"</span>";
  g.fixtestInfo("Message="+s);
  TxtInfo.setText(/*(b?"\\rtf1\n":"")+*/s);
}

public static String getText(Global g,String sKennung, String[] sArray)
{
	return getText(g,sKennung,sArray,"Message",false);
}

public static String getText(Global g,String sKennung, String[] sArray,String sGruppe,boolean bAlle)
{
	boolean bNichtDa=true;
	Vector<String> VecMemo=null;
	int iAic=0;
    int iPos=-1;
    if (sGruppe==null)
    	sGruppe="Message";
    String sTitel=null;
    if (g==null || g.TabBegriffe == null)
    {
      int iBG = SQL.getInteger(g, "select aic_begriffgruppe from begriffgruppe where kennung='"+sGruppe+"'");
      iAic = SQL.getInteger(g, "select aic_begriff from begriff where kennung='"+sKennung+"' and aic_begriffgruppe="+iBG);
    }
    else
      iPos=g.getPosBegriff(sGruppe,sKennung);
    if (iPos>=0)
        iAic=g.TabBegriffe.getI(iPos,"Aic");
    if(iAic>0)
	{
		VecMemo = g.getMemoVector("Begriff",iAic);
		bNichtDa=VecMemo.elementAt(0).equals("") && VecMemo.elementAt(1).equals("");
                
	}
    String s=null;
    if(bNichtDa)
	{
    	sTitel=sKennung;
		s = "*"+sKennung+"* ";
		if(sArray!=null && sArray.length>0) 
		{
			s = s + "\""+sArray[0]+"\"";
			for(int i=1;i<sArray.length;i++)
				s = s+", \""+sArray[i]+"\"";
		}
	}
    else
    {
    	sTitel=(String)VecMemo.elementAt(0);
//    	g.fixtestError("davor:"+VecMemo.elementAt(1));
    	s=Static.makeString((String) VecMemo.elementAt(1),sArray);
//    	g.fixtestError("danach:"+s);
    }
    return bAlle ? iPos+";"+sTitel+";"+s:s;
}

public int showDialog(String sKennung, String[] sArray,Tabellenspeicher rTab)
{
  Cursor cur=getCursor();
  setCursor(Cursor.getDefaultCursor());
  AUOutliner Grid = null;
  Tab=rTab;
  if (PnlTab != null)
    if (Tab != null && Tab.size() > 0)
    {
      Grid = new AUOutliner();
      //Grid.setFont(g.fontStandard);
      //Grid.setMaximumSize(new java.awt.Dimension(600,1000));
      PnlTab.add(Grid);
      Tab.showGrid(Grid);
      Grid.setBackground(Col);
    }
    else
    {
      //LblPic.setIcon(null);
      PnlTab.setBackground(Col);
    }
	/*boolean bNichtDa=true;
	Vector VecMemo=null;
        int iAic=0;
        int iPos=-1;
        if (g.TabBegriffe == null)
        {
          int iBG = SQL.getInteger(g, "select aic_begriffgruppe from begriffgruppe where kennung='"+sGruppe+"'");
          iAic = SQL.getInteger(g, "select aic_begriff from begriff where kennung='"+sKennung+"' and aic_begriffgruppe="+iBG);
        }
        else
          iPos=g.getPosBegriff(sGruppe,sKennung);
        if (iPos>=0)
          iAic=g.TabBegriffe.getI(iPos,"Aic");
	if(iAic>0)
	{
		VecMemo = g.getMemoVector("Begriff",iAic);
		bNichtDa=VecMemo.elementAt(0).equals("") && VecMemo.elementAt(1).equals("");
                if (g.TabBegriffe != null)
                {
                  Image Img = g.getImageBegriff(iPos);
                  if (Img != null)LblPic.setIcon(new ImageIcon(Img));
                  g.setSchrift2(iPos, TxtInfo,g.fontMessage);
                }
	}
        if(bNichtDa)
	{
		setTitle(sKennung);
		String s = "*"+sKennung+"* ";
		if(sArray.length>0) s = s + "\""+sArray[0]+"\"";
		for(int i=1;i<sArray.length;i++)
			s = s+", \""+sArray[i]+"\"";
		if((iMT%8)==3)
			s = s + "?";
		else
			s = s + "!";
                bFew=s.length()<100;
                bMany=s.length()>400;
                setText(s);
	}
	else
	{
          String s=(String)VecMemo.elementAt(0);
          setUndecorated(true);
          if (s==null || s.equals("") || s.equals(" "))
            TB.setTitle("");
          else
            TB.setTitle(s);
          repaint();
          s=makeString((String) VecMemo.elementAt(1),sArray);
          bFew=s.length()<100;
          bMany=s.length()>400;
          g.fixtestInfo(bFew+"/"+bMany+","+s.length());
          setText(s);
	}*/
    String s=getText(g,sKennung,sArray,sGruppe,true);
    int iP=s.indexOf(";");
    String sTitel=s.substring(iP+1);
    int iPos=Sort.geti(s.substring(0,iP));
    iP=sTitel.indexOf(";");
    s=sTitel.substring(iP+1);
    sTitel=sTitel.substring(0,iP);
    g.fixtestInfo("iP="+iP+", sTitel="+sTitel);
    if (iPos>=0)
    {
      Image Img = g.getImageBegriff(iPos);
      if (Img != null)LblPic.setIcon(new ImageIcon(Img));
      g.setSchrift2(iPos, TxtInfo,Global.fontMessage);
      //g.fixtestError("Message-Größe="+TxtInfo.getFont().getSize());
    }
    else
    {
		if((iMT%8)==3)
			s = s + "?";
//		else
//			s = s + "!";
    }
    setTitle(sTitel+(g.Def() ? " ("+sKennung+")":""));
    bFew=s.length()<100;
    bMany=s.length()>400;
    setText(s);
	//g.testInfo("Rows="+TxtInfo.getRows()+"/ Size="+TxtInfo.getPreferredSize());
	//setheight(TxtInfo.getPreferredSize().height+50);
	//int iWidth=getWidth();
        setMinimumSize(new Dimension(Tab == null ? 350:450,Tab == null && bFew ? 150:250));
        //Scroll.setMaximumSize(new Dimension(800,500));
        if (Tab != null)
          ;
        else if (bMany)
          Scroll.setPreferredSize(new Dimension(500*iFF/100,350*iFF/100));
        else if (!bFew)
          Scroll.setPreferredSize(new Dimension(350*iFF/100,100*iFF/100));
        //  TxtInfo.setPreferredSize(new Dimension(350,80));
        setMaximumSize(new Dimension(1024*iFF/100,786*iFF/100));
        pack();
        if (Grid != null)
        {
          int iMass=0;
          for(int i=0;i<Grid.getNumColumns();i++)
          {
            //g.fixInfo("Spalte "+i+": "+Grid.getColumnDisplayWidth(i)+"/"+Grid.getColumnWidth(i));
            if(Grid.getColumnWidth(i) > 250)
              Grid.setColumnWidth(i,250);
            iMass += Grid.getColumnWidth(i);
          }
          //g.fixInfo("Gesamtbreite="+iMass);
          validate();
          setSize((iMass<950 ? iMass+100:1000)*iFF/100,getSize().height+(Tab!=null ? 50:0));
        }
	//g.progInfo("showDialog:"+iWidth+"/"+getWidth()+"/"+getHeight());
	//setSize(Math.max(400,getWidth()),Math.max(Math.min(g.iMaxH-50,getHeight()+32),150)); // !!!
        Static.centerComponent(this,/*FrmO==null||!FrmO.isShowing()?null:*/FrmO);
	TxtInfo.setCaretPosition(0);
        //View.setViewPosition(new Point(0,0));
        if (bShow)
          setVisible(true);
	//g.testInfo("Rows="+TxtInfo.getRows()+"/ Size="+TxtInfo.getPreferredSize());
        setCursor(cur);
	return(iRet);

}

private Global g;

public static int YES_NO_CANCEL_OPTION = 11;
public static int YES_NO_OPTION = 3;
public static int OK_CANCEL_OPTION = 12;

public static int YES_OPTION = 1;
public static int NO_OPTION = 2;
public static int OK_OPTION = 4;
public static int CANCEL_OPTION = 8;
private static int MESSAGE_TYPE=15;

public static int WARNING_MESSAGE = 5;
public static int INFORMATION_MESSAGE = 6;
public static int ERROR_MESSAGE = 7;
public static int QUESTION_MESSAGE = 11;

public static int SHOW_TAB = 16;
public static int UNMODAL = 32;
public static int EXPORT = 64;

private JLabel LblPic = new JLabel();
//private JTextArea TxtInfo= new JTextArea();
private JEditorPane TxtInfo= new JEditorPane();
private JPanel PnlTab=null;
private JScrollPane Scroll;
//private JViewport View;
private int iRet=0;
private int iMT=1;
private long lStop;
private javax.swing.Timer timer=null;
private boolean bShow=true;
private boolean bFew=true;
private boolean bMany=false;
public String sGruppe="Message";
private TitledBorder TB=null;
private Window FrmO;
private java.awt.Color Col;
private Tabellenspeicher Tab=null;
private static boolean bOk=false;
private int iFF=100;

}

