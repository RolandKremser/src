/*
    All_Unlimited-Allgemein-Eingabe-AUBits.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.FlowLayout;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Tabellenspeicher;

public class AUBits extends javax.swing.JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5692442429517407529L;
	// add your data members here
	//private Global g;
	private Tabellenspeicher Tab;
	private int iOld;

	public AUBits(int riStt,Global rg)
	{
		this(new Tabellenspeicher(rg,"select Bezeichnung,Cbx=null from stamm left outer join stammview where stamm.aic_stammtyp="+riStt+" order by stamm.aic_stamm",true),rg);
	}

	public AUBits(Tabellenspeicher rTab,Global rg)
	{
		//g=rg;
		setLayout(new FlowLayout());
		Tab=rTab;
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			if (!Tab.getS("Bezeichnung").equals(""))
			{
				AUCheckBox Cbx=new AUCheckBox(Tab.getS("Bezeichnung"));
				add(Cbx);
				Tab.setInhalt("Cbx",Cbx);
			}
		}
	}

	public void setValue(int i)
	{
		iOld=i;
		String s=Integer.toBinaryString(i);
		//g.fixInfo(s);
		i=s.length();
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			i--;
			if(Tab.getInhalt("Cbx") != null)
				((AUCheckBox)Tab.getInhalt("Cbx")).setSelected(i>=0 && s.charAt(i)=='1');
		}
	}

	public int getValue()
	{
		int i=0;
		int iF=1;
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			if (Tab.getInhalt("Cbx") != null && ((AUCheckBox)Tab.getInhalt("Cbx")).isSelected())
				i+=iF;
			iF*=2;
		}
		//g.fixInfo("->"+i);
		return i;
	}

	public boolean isNull()
	{
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			if (Tab.getInhalt("Cbx") != null && ((AUCheckBox)Tab.getInhalt("Cbx")).isSelected())
				return false;
		}
		return true;
	}

	public boolean Modified()
	{
		return iOld != getValue();
	}
}

