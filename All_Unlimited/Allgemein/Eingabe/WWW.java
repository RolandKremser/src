/*
    All_Unlimited-Allgemein-Eingabe-WWW.java

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
import javax.swing.JTextField;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import java.awt.Color;

public class WWW extends javax.swing.JPanel
{
/**
	 *
	 */
	private static final long serialVersionUID = -4927971341555459691L;
public WWW(Global glob)
{
	g=glob;
	setValue("");
	Build();
}

public WWW(String sURL, Global glob)
{
	g=glob;
	setValue(sURL);
	Build();
}

private void Build()
{
  if (Static.bStern)
  {
    BtnOpenURL = g.getButton("*");
    BtnOpenURL.setFocusable(false);
    BtnOpenURL.setBorder(null);
  }
	setLayout(new BorderLayout());
	add("Center",EdtURL);
        if(Static.bStern) {
          add("East", BtnOpenURL);
          BtnOpenURL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              OpenURL();
            }
          });
        }
        EdtURL.setColumns(25);//setPreferredSize(new java.awt.Dimension(150, 20));
        EdtURL.setBorder(new javax.swing.border.EmptyBorder(1,1,1,1));
	EdtURL.addKeyListener(new KeyListener()
	{
		public void keyPressed(KeyEvent e)
		{
		}
		public void keyReleased(KeyEvent e)
		{
                  CheckColor();
			//if(e.getKeyCode()==e.VK_ENTER)            // entfernt am 21.10.2005 für V 5.0
			//	Static.OpenURL(EdtURL.getText());
		}
		public void keyTyped(KeyEvent e)
		{
                  if(e.getKeyChar()=='*')
                  {
                    OpenURL();
                    e.consume();
                  }
		}
	});

	EdtURL.addMouseListener(new MouseListener()
	      {
	        public void mousePressed(MouseEvent ev)
	        {}

	        public void mouseClicked(MouseEvent ev)
	        {
	          //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
	          if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
	          {
	            OpenURL();
	          }
	        }

	        public void mouseEntered(MouseEvent ev)
	        {}

	        public void mouseExited(MouseEvent ev)
	        {}

	        public void mouseReleased(MouseEvent ev)
	        {}
	      });
}

private void CheckColor()
{
  EdtURL.setBackground(Modified()? Static.ColChange:Color.WHITE);
}

public void OpenURL()
{
  Static.OpenURL(EdtURL.getText());
}

public void setValue(String sURL)
{
	sOldValue=sURL;
	EdtURL.setText(sURL);
}

public void setAktValue(String sURL)
{
	sOldValue=sURL;
}

public String getValue()
{
	return EdtURL.getText();
}

public boolean Modified()
{
	return !EdtURL.getText().equals(sOldValue);
}

public void Reset2()
{
      setValue(sOldValue);
}

public boolean isValid2() //Kann sein, daß falsche Werte zurückgeliefert werden!!!!
{
	boolean bOk=true;
	/*String sURL = EdtURL.getText();
	if(!sURL.equals(""))
	{
		try
		{
			new URL(sURL.indexOf("://")>=0 ? sURL : "http://"+sURL).openConnection().connect();

		}
		catch(MalformedURLException e)
		{
			//Static.printError("WWW.isValid(): MalformedURLException - "+e);
			bOk=false;
		}
		catch(java.io.IOException e)
		{
			//Static.printError("WWW.isValid(): IOException - "+e);
			bOk=false;
		}
	}*/


	return(bOk);
}

public JTextField getTextField()
{
	return EdtURL;
}

public boolean isNull()
{
	return getValue().equals("");
}

public void setEditable(boolean b)
{
	EdtURL.setEditable(b);
}

public JTextField getEditor()
{
	return EdtURL;
}

public void setFont(Font font)
{
        if (EdtURL !=null)
        {
                EdtURL.setFont(font);
                if (Static.bStern)
                  BtnOpenURL.setFont(font);
        }
}


// add your data members here
private Global g;
private JButton BtnOpenURL;
private JTextField EdtURL = new JTextField();
private String sOldValue;
}

