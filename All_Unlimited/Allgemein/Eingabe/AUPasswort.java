/*
    All_Unlimited-Allgemein-Eingabe-AUPasswort.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.border.MatteBorder;

import All_Unlimited.Allgemein.Static;

// add your custom import statements here

public class AUPasswort extends javax.swing.JPasswordField
{
	/**
	 *
	 */
	private static final long serialVersionUID = 4829776125484681881L;

    public AUPasswort(int i)
    {
            super(i);
            setColumns(25);
            Build();
    }

    public AUPasswort()
	{
		super();
		Build();
		addFocusListener(new FocusListener()
		{
				public void focusGained(FocusEvent fe)
				{
					selectAll();
				}
				public void focusLost(FocusEvent fe)
				{
				}
		});
	}
        
    private void Build()
    {
    	CheckColor();
    	if (Static.bND)
            setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        else if (!Static.bRahmen)
            setBorder(new javax.swing.border.EmptyBorder(1,1,1,1));
    }

	public void setValue(String s)
	{
		sOld = s;
		setText(s);
		CheckColor();
	}

        public String getValue()
        {
          return isNull() ? null:new String(getPassword());
        }

	public boolean Modified()
	{
//		System.err.println("Modified PW:"+sOld+"/"+new String(getPassword()));
		return !new String(getPassword()).equals(sOld);
	}

	public boolean isNull()
	{
		return new String(getPassword()).equals("");
	}

    public void Reset()
    {
      sOld=getValue();
      if (sOld==null)
    	  sOld="";
//      System.err.println("Reset PW:"+Static.print(sOld));
    }

    public void Reset2()
    {
          setValue(sOld);
    }
    
    private void CheckColor()
    {
      setBackground(Static.ColEdt(Modified(),isEditable()));
    }
	 // add your data members here
	 String sOld="";
}

