/*
    All_Unlimited-Allgemein-Eingabe-AUTextArea.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import javax.swing.JButton;
import javax.swing.JScrollPane;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;

import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComponent;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;

import All_Unlimited.Allgemein.GroupBox;
//import javax.swing.JFrame;
import All_Unlimited.Allgemein.Message;

public class AUTextArea extends javax.swing.JPanel
{
/**
	 *
	 */
	private static final long serialVersionUID = 7243396544301097523L;
public AUTextArea(Global rg,int riBits)
{
	Build(rg,riBits);
}

public AUTextArea()
{
	this(null,0);
}

public void setMaxLength(int i)
{
	iLaenge=i;
}

/*public AUTextArea(Global rg,String s)
{
	//super(text);
	Edt=new javax.swing.JTextArea(s);
	Build(rg,s);
}

public AUTextArea(Global rg,String s, int rows, int columns)
{
	Edt=new javax.swing.JTextArea(s,rows,columns);
	//super(text,rows,columns);
	Build(rg,s);
}

public AUTextArea(Global rg,int rows, int columns)
{
	this(rg,"",rows,columns);
}*/

  private JMenuItem addMenuItem(String sKnopf,JComponent pop)
  {
    JMenuItem Mnu = new JMenuItem(g==null ? sKnopf:g.getBegriffS("Button", sKnopf));
    if (g!= null)
      Mnu.setFont(Transact.fontStandard);
    if (pop instanceof JMenu || pop instanceof JPopupMenu)
      pop.add(Mnu);
    Mnu.setName(sKnopf);
    Mnu.addActionListener(PopList);
    return Mnu;
 }

private void Build(Global rg,int iBits)
{
	g=rg;
	Edt.setLineWrap(true);
	Edt.setWrapStyleWord(true);
	Edt.setPreferredSize(new Dimension(150,34));
        setFont(getFont());
        //if (!Static.bRahmen)
          Edt.setBorder(null);
        //Edt.setAutoscrolls(true);
        //setText(s);
	setLayout(new BorderLayout(1,1));
        SP=new JScrollPane(Edt);
        if (!Static.bRahmen)
          SP.setBorder(null);
        if ((iBits&HTML)>0)
        {
          EdtResult=new JEditorPane();
          EdtResult.setText("");
          EdtResult.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
          EdtResult.setFont(Transact.fontStandard);
          EdtResult.setContentType("text/html");
          EdtResult.setEditable(false);
          GroupBox GO = new GroupBox();
          GO.setText(g.getShow("Eingabe"),Transact.fontStandard);
          GO.setBackground(Color.WHITE);
          GO.add(new JScrollPane(Edt));
          GroupBox GU = new GroupBox();
          GU.setText(g.getShow("Ergebnis"),Transact.fontStandard);
          GU.setBackground(Color.WHITE);
          GU.add(new JScrollPane(EdtResult));
          JSplitPane PnlC = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,GO,GU);
          PnlC.setOneTouchExpandable(true);
          PnlC.setResizeWeight(0.5);
          add("Center",PnlC);
        }
        else
          add("Center",SP);
        /*
	if (iBits>0 && Static.bStern)
	{
		JPanel PnlEast=new JPanel(new GridLayout(0,1,1,1));
		if((iBits&1)>0)
		{
			JButton BtnCopy=g.getButton("C");
			//BtnCopy.setMargin(g.inset);
			PnlEast.add(BtnCopy);
			BtnCopy.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(Edt.getCaret().getDot()==Edt.getCaret().getMark())
						Edt.selectAll();
					Edt.copy();
                                        g.progInfo("h1="+Edt.getHeight()+",h2="+getHeight()+",h3="+Edt.getPreferredScrollableViewportSize().height+",h4="+Edt.getPreferredSize().height+
                                            ",h5="+Edt.getVisibleRect().getHeight()+"Anz1="+Edt.getLineCount());
                                        //g.progInfo("Anz1="+Edt.getLineCount()+",Anz2="+Edt.getRows()+",insets="+Edt.getInsets().top + "/"+Edt.getInsets().bottom);
					//g.progInfo(Edt.getCaret().getDot()+"/"+Edt.getCaret().getMark());
					//g.sZwischenspeicher=getValue();
				}
			});
		}
		if((iBits&2)>0)
		{
			JButton BtnPaste=g.getButton("P");
			//BtnPaste.setMargin(g.inset);
			PnlEast.add(BtnPaste);
			BtnPaste.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Edt.paste();
					//Edt.setText(Edt.getText()+g.sZwischenspeicher);
				}
			});
		}
		if(g.History() && (iBits&4)>0)
		{
			BtnHistory=g.getButton("H");
			//BtnHistory.setMargin(g.inset);
			PnlEast.add(BtnHistory);
		}
		add("East",PnlEast);
	}*/
        //else if ((iBits&4)>0)
        //  BtnHistory=new JButton();

        popup= new JPopupMenu();
        PopList = new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ((JComponent)ev.getSource()).getName();
            if (s.equals("cut"))
              Edt.cut();
            else if (s.equals("copy"))
              Edt.copy();
            else if (s.equals("paste"))
              Edt.paste();
            else if (s.equals("selectAll"))
              Edt.selectAll();
            else if (s.equals("Undo"))
              Reset2();
            else if (s.equals("Anzahl"))
            {
              int iAnz=Edt.getText().length();
              new Message(Message.INFORMATION_MESSAGE, null, g).showDialog("Anzahl_Zeichen",new String[] {""+iAnz,""+((iLaenge>0 ? iLaenge:Static.iMemoMax)-iAnz)});
              //g.fixInfo("Zeichen:"+Edt.getText().length());
            }
            else if (s.equals("Zeichen"))
            {
            	int i=Edt.getCaretPosition();
            	if (i>=0 && i<3290)
            	{
            	  Character c=Edt.getText().charAt(i);
            	  g.fixInfo("Zeichen="+c+"/"+Character.isDefined(c)+", code="+c.hashCode()+", WS="+Character.isWhitespace(c));
            	}
            }
          }
        };
        addMenuItem("cut",popup);
        addMenuItem("copy",popup);
        addMenuItem("paste",popup);
        addMenuItem("selectAll",popup);
        addMenuItem("Undo",popup);
        addMenuItem("Anzahl",popup);
        addMenuItem("Zeichen",popup);
        if (g!= null && g.History() && (iBits&HISTORY)>0)
        {
          popup.addSeparator();
          MnuH = addMenuItem("History", popup);
        }
        Edt.addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev)
          {}

          public void mouseClicked(MouseEvent ev)
          {
            if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM))
              popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
          }

          public void mouseEntered(MouseEvent ev)
          {}

          public void mouseExited(MouseEvent ev)
          {}

          public void mouseReleased(MouseEvent ev)
          {}
        });

	addFocusListener(new FocusListener()
	{
			public void focusGained(FocusEvent fe)
			{
				Edt.requestFocus();
			}
			public void focusLost(FocusEvent fe)
			{
			}
	});
      
        Edt.addKeyListener(new KeyListener()
                        {
                                public void keyTyped(KeyEvent e)
                                {
                                	if (iLaenge>0)
                                	{
                                		int iText=Edt.getText().length();
                                		if (iText>=iLaenge)
                                			e.consume();
                                	}
                                }
                                public void keyPressed(KeyEvent e)
                                {
                                  int iKC=e.getKeyCode();
                                  if (iLaenge>0 && iKC==10)
                                  {
                                	  e.consume();
                                	  return;
                                  }
                                  String s=KeyEvent.getKeyText(iKC);
//                                  System.out.println("keyPressed:" + s +"/"+iKC+ "/" + e.getModifiers());
                                  if (e.getModifiers() == 2)
                                  {
                                    if (g!= null && g.Mac())
                                    {
                                      if (s.equals("A"))
                                        Edt.insert("ä", Edt.getCaretPosition());
                                      if (s.equals("O"))
                                        Edt.insert("ö", Edt.getCaretPosition());
                                      if (s.equals("U"))
                                        Edt.insert("ü", Edt.getCaretPosition());
                                      if (s.equals("S"))
                                        Edt.insert("ß", Edt.getCaretPosition());
                                      if (s.equals("Q"))
                                        Edt.insert("@", Edt.getCaretPosition());
                                      if (s.equals("E"))
                                        Edt.insert("€", Edt.getCaretPosition());
                                      if (s.equals("M"))
                                        Edt.insert("µ", Edt.getCaretPosition());
                                      if (s.equals("3"))
                                        Edt.insert("§", Edt.getCaretPosition());
                                      if (s.equals("7"))
                                        Edt.insert("{", Edt.getCaretPosition());
                                      if (s.equals("0"))
                                        Edt.insert("}", Edt.getCaretPosition());
                                      if (s.equals("<"))
                                        Edt.insert("|", Edt.getCaretPosition());
                                      e.consume();
                                    }
                                    if (s.equals("Z") && !Static.Leer(sOldText))
                                    {
                                      Reset2();
                                      e.consume();
                                    }
                                  }
                                  else if (e.getModifiers() == 4)
                                  {
                                    if ((s.equals("Y") || s.equals("Z")) && !Static.Leer(sOldText))
                                      Reset2();
                                    else if (s.equals("X"))
                                      Edt.cut();
                                    else if (s.equals("C"))
                                      Edt.copy();
                                    else if (s.equals("V"))
                                      Edt.paste();
                                    else if (s.equals("A"))
                                      Edt.selectAll();
                                    e.consume();
                                  }
                                  /*else if (e.getModifiers() == 8)
                                  {
                                    if (s.equals("A"))
                                      Edt.insert("ä",Edt.getCaretPosition());
                                    if (s.equals("O"))
                                      Edt.insert("ö",Edt.getCaretPosition());
                                    if (s.equals("U"))
                                      Edt.insert("ü",Edt.getCaretPosition());
                                    if (s.equals("S"))
                                      Edt.insert("ß",Edt.getCaretPosition());
                                    e.consume();
                                  }*/
                                  else if (/*e.getModifiers() == 9 ||*/g!= null && g.Mac() && e.getModifiers() == 3)
                                  {
                                    if (s.equals("A"))
                                      Edt.insert("Ä",Edt.getCaretPosition());
                                    if (s.equals("O"))
                                      Edt.insert("Ö",Edt.getCaretPosition());
                                    if (s.equals("U"))
                                      Edt.insert("Ü",Edt.getCaretPosition());
                                    e.consume();
                                  }

                                  if (iKC==KeyEvent.VK_PAGE_DOWN)
                                  {
                                    Edt.setPreferredSize(new Dimension(Edt.getPreferredScrollableViewportSize().width, Edt.getPreferredSize().height+50));
//                                    Global.fixInfoS("Memo-Height="+Edt.getPreferredSize().height+" bei "+Edt.getLineCount()+" Zeilen");
                                    //repaint();
                                    Edt.revalidate();
                                  }
                                  else if (iKC==KeyEvent.VK_PAGE_UP)
                                  {
                                    Edt.setPreferredSize(new Dimension(Edt.getPreferredScrollableViewportSize().width, Edt.getPreferredSize().height - 50));
//                                    Global.fixInfoS("Memo-Height="+Edt.getPreferredSize().height+" bei "+Edt.getLineCount()+" Zeilen");
                                    //repaint();
                                    Edt.revalidate();
                                  }
                                }
                                public void keyReleased(KeyEvent e)
                                {
                                  CheckColor();
                                }

                        });


	//iWidth=getWidth();
	//System.out.println("!!! Width="+iWidth);
}

      private void CheckColor()
        {
    	  Color Col=Modified() ? Static.ColChange:Static.bND ? Edt.isEditable() ? Static.ColMemo:Static.ColEF:Color.WHITE;
          Edt.setBackground(Col);//Static.ColEdt(Modified(),!Static.bND || Edt.isEditable()));
          if (EdtResult!=null)
          {
            String s=Edt.getText();
            EdtResult.setText(s); //.replaceAll("\n","<br>"));
            String sTyp=s.indexOf("\\rtf")>=0 ? "text/rtf":s.indexOf("<")<0 ? "text/plain":"text/html";
            if (!EdtResult.getContentType().equals(sTyp))
            {
              EdtResult.setContentType(sTyp);
              g.fixInfo("setzte ContentType auf " + sTyp);
            }
          }
        }

public void setH()
{
  Edt.setLineWrap(false);
  Edt.setPreferredSize(new Dimension(4000, Edt.getPreferredSize().height));
  SP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
}

public void setText(String s)
{
	//System.out.println(hashCode()+":"+getSize());
	sOldText = s;
	//int i=getColumns();
	Edt.setText(s);
//	Global.fixInfoS("Memo-Height="+Edt.getPreferredSize().height+" bei "+Edt.getLineCount()+" Zeilen");
    Edt.setPreferredSize(new Dimension(Edt.getPreferredScrollableViewportSize().width, Edt.getLineCount()*15+20/*Edt.getLineCount()==1?0:Edt.getHeight()*/));
    if (!Edt.isEditable())
    	Edt.setCaretPosition(0);
    CheckColor();
}

public void setAktText(String s)
{
	sOldText = s;
        CheckColor();
}

public String getValue()
{
	String s=Edt.getText();
	int iAnz=0;
	Vector<Integer> Vec=new Vector<Integer>();
	for (int i=0;i<s.length();i++)
		if (s.charAt(i)>61500)
			{
				Character c=Edt.getText().charAt(i);
				iAnz++;
//				g.fixtestError("entferne "+c+"/"+c.isDefined(c)+"/"+c.hashCode()+"/"+c.isWhitespace(c));
				Vec.addElement(c.hashCode());
				s=s.replace(c, ' ');
			}
	if (iAnz>0)
	{
		g.fixtestError("Ersetzt: "+iAnz+"/"+Vec);
		Edt.setText(s);
	}
	return s;
}

public String toString()
{
        return Edt.getText();
}


public boolean Modified()
{
	boolean b=!All_Unlimited.Allgemein.Static.Gleich(sOldText,Edt.getText());
	//System.out.println("AUTextArea.Modified:"+sOldText+"/"+getText()+"/"+b);
	return b;
}

public boolean isNull()
{
	return Edt.getText().equals("");
}

public void setFont(Font font)
{
        if (g !=null)
	{
          if (font == null)
            font = Transact.fontStandard;
          //g.progInfo("AUTextArea:Font="+font);
        }
        if (font != null && Edt != null)
          Edt.setFont(font);
}

public void setEnabled(boolean b)
{
        Edt.setEnabled(b);
}
public void setEditable(boolean b)
{
  Edt.setEditable(b);
  //Cbo2.setEditable(b);
  //  BtnList.setFocusable(b);
}

public void Reset2()
{
  setText(sOldText);
}

// add your data members here
public static final int HISTORY=4;
public static final int HTML=8;
public javax.swing.JTextArea Edt=new javax.swing.JTextArea();
private JEditorPane EdtResult;
private String sOldText="";
//public JButton BtnHistory;
public JMenuItem MnuH;
private Global g;
private JPopupMenu popup;
private ActionListener PopList;
private JScrollPane SP;
private int iLaenge=0;
//private int iMax=4000;
//private int iWidth;
}

