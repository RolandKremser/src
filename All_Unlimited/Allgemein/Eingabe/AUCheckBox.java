/*
    All_Unlimited-Allgemein-Eingabe-AUCheckBox.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

import All_Unlimited.Allgemein.Static;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Insets;

// add your custom import statements here

public class AUCheckBox extends javax.swing.JCheckBox
{
/**
	 *
	 */
	private static final long serialVersionUID = -1671154075060483649L;
public AUCheckBox()
{
  this(false,"");
}

public AUCheckBox(boolean b)
{
  this(b,"");
}

public AUCheckBox(String s)
{
  this(false,s);
}

/*public AUCheckBox(String s,ImageIcon Img)
{
  this(false,s,Img);
}

public AUCheckBox(boolean b,String s)
{
  this(b,s,null);
}*/

public AUCheckBox clone()
{
  return new AUCheckBox(isSelected(),getText());
}

public AUCheckBox(boolean b,String s)
{
	super(s,b);
        if (unchecked==null)
        {
          checked  = new ImageIcon(getClass().getResource(Static.getIcon(Static.IA_checked)));// "/images/ok.png"));
          unchecked= new ImageIcon(getClass().getResource(Static.getIcon(Static.IA_unchecked)));//"/images/cancel.png"));
          checked2  = new ImageIcon(getClass().getResource(Static.getIcon(Static.IA_checked2)));//"/images/ok2.png"));
          unchecked2= new ImageIcon(getClass().getResource(Static.getIcon(Static.IA_unchecked2)));//"/images/cancel2.png"));
        }
        try
        {
          setIcon(unchecked);
          setRolloverIcon(unchecked);
          setSelectedIcon(checked);
          setRolloverSelectedIcon(checked);
          setDisabledSelectedIcon(checked2);
          setDisabledIcon(unchecked2);
        }
        catch(Exception E) {}
	bOld=b;
        setMargin(new Insets(0,0,0,0));
        addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    CheckColor();
                  }
                });

}

private void CheckColor()
{
  if (Col==null)
    Col=getBackground();
  setBackground(Modified()? Static.ColChange:Col);
}

public boolean isNull()
{
	return !isSelected();
}

public boolean wasNull()
{
  return !bOld;
}

public boolean Modified()
{
	return bOld != isSelected();
}

public void setSelected(boolean b)
{
	super.setSelected(b);
	bOld=b;
        CheckColor();
}

public void setAktSelected(boolean b)
{
	bOld=b;
  CheckColor();
}

public void setEnabled(boolean b)
{
  super.setEnabled(b && bEdit);
}

public void setEditable(boolean b)
{
  //if (b)
  //disableEvents(-1);
  bEdit=b;
  super.setEnabled(b);
}

public String toString()
{
  return isSelected() ? Static.sJa:Static.sNein;
}

public void Reset2()
{
  setSelected(bOld);
}

// add your data members here
private boolean bOld = false;
private boolean bEdit = true;
private Color Col=null;
public static ImageIcon unchecked = null;
public static ImageIcon checked = null;
public static ImageIcon unchecked2 = null;
public static ImageIcon checked2 = null;
}

