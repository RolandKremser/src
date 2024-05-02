/*
    All_Unlimited-Allgemein-Eingabe-AUFarbe.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import All_Unlimited.Allgemein.Farbwahl;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;

public class AUFarbe extends javax.swing.JButton
{
    /**
	 *
	 */
	private static final long serialVersionUID = 5732701236135262924L;
	// add your data members here
	//Global g=null;
	Color Col=null;
	Farbwahl Far=null;
	int iOld=0;
	boolean bFarbe;
        boolean bEdit=true;
	//Global g;

	public AUFarbe(JFrame frame,Global rg,int iFarbe)
	{
		Build(frame,rg,iFarbe);
	}

	private void Build(JFrame frame,Global rg,int iFarbe)
	{
		//g=rg;
		if (Static.bND)
		{
			//setMinimumSize(new Dimension(16,2));
			int iFF=rg.getFontFaktor();
			setPreferredSize(new Dimension(175*iFF/100, Static.fontStandard.getSize()*15*iFF/1000));
		}
		setValue(iFarbe);
		Far=new Farbwahl(frame,rg);
		addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                          if (bEdit)
                          {
                            Col = Far.showDialog(Col);
                            setBackground(Col);
                            bFarbe = true;
                            setOpaque(true);
                          }
			}
		});
	}

	public void setValue(int iFarbe)
	{
		bFarbe=iFarbe != 0;
                setOpaque(bFarbe);
		iOld=iFarbe;
		Col=new Color(iFarbe);
		setBackground(Col);
		//iOld=Col.getRGB();
		//g.progInfo("Farbe="+iOld+"/"+iFarbe);
	}

	public int getValue()
	{
		return bFarbe ? Col.getRGB():0;
	}

        public void Delete()
        {
          bFarbe=false;
          setOpaque(false);
          setBackground(Color.BLACK);
        }

	public boolean isNull()
	{
		return !bFarbe;//Col.getRGB()==0;
	}

	public boolean Modified()
	{
		return bFarbe && Col.getRGB()!=iOld || !bFarbe && iOld!=0;
	}

        public void Reset()
        {
          iOld=Col.getRGB();
        }

        public void Reset2()
        {
          setValue(iOld);
        }

        public void setEditable(boolean b)
        {
          bEdit=b;
        }

}

