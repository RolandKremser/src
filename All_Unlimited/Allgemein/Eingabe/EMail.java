/*
    All_Unlimited-Allgemein-Eingabe-EMail.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;

public class EMail extends javax.swing.JPanel
{
/**
	 *
	 */
	private static final long serialVersionUID = -3567753307542268464L;
public EMail(int iLaenge, Global glob)
{
	g=glob;
	Build("",iLaenge);
}

public EMail(String s, int iLaenge, Global glob)
{
	g=glob;
	Build(s,iLaenge);
}

private void Build(String s, int iLaenge)
{
  if (Static.bStern)
  {
    BtnMail = g.getButton("*");
    BtnMail.setFocusable(false);
    BtnMail.setBorder(null);
    //BtnMail.setMargin(g.inset);
  }
	Txt = new Text(s,iLaenge);
        Txt.setColumns(Static.bND ? 21:25);
	setLayout(new BorderLayout(2,2));
	add("Center",Txt);
        Txt.addKeyListener(new KeyListener()
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
                    //g.progInfo("mailto");
                    MailTo();
                    e.consume();
                  }
                }
        });

        Txt.addMouseListener(new MouseListener()
	      {
	        public void mousePressed(MouseEvent ev)
	        {}

	        public void mouseClicked(MouseEvent ev)
	        {
	          //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
	          if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
	          {
	            MailTo();
	          }
	        }

	        public void mouseEntered(MouseEvent ev)
	        {}

	        public void mouseExited(MouseEvent ev)
	        {}

	        public void mouseReleased(MouseEvent ev)
	        {}
	      });

  if (Static.bStern)
  {
    add("East", BtnMail);
    BtnMail.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        MailTo();
      }
    });
  }
}

public void MailTo()
{
  Static.OpenURL("mailto:" + Txt.getText());
}

public void setValue(String s)
{
	Txt.setText(s);
}

public void setAktValue(String s)
{
	Txt.setAktText(s);
}

public boolean isNull()
{
	return Txt.isNull();
}

public boolean Modified()
{
	return Txt.Modified();
}

public void Reset2()
{
  Txt.Reset2();
}

public String getValue()
{
	return Txt.getText();
}

public void setEditable(boolean b)
{
	Txt.setEditable(b);
}

public Text getEditor()
{
	return Txt;
}

public void setFont(Font font)
{
	if (Txt !=null)
	{
		Txt.setFont(font);
                if (Static.bStern)
                  BtnMail.setFont(font);
	}
}

// add your data members here
private Global g;
private Text Txt;
private JButton BtnMail;
}

