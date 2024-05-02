/*
    All_Unlimited-Allgemein-Eingabe-WaehrungsEingabe.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;

public class WaehrungsEingabe extends javax.swing.JPanel
{
/**
	 *
	 */
	private static final long serialVersionUID = -7736770460739334835L;
public WaehrungsEingabe(double dWert, int iAIC_Stamm, Global glob,boolean rbCbo,int iStellen)
{
	g=glob;
        Edt = new Zahl(0.0,iStellen);
	Cbo = new ComboSort(g);
        
        //Cbo.getBorder().getBorderInsets()
        Cbo.setBorder(new javax.swing.border.EmptyBorder(0,0,0,0));
        Cbo.setBounds(0,0,0,0);
        Cbo.setPreferredSize(new java.awt.Dimension(40,20));
//        Cbo.setMinimumSize(new Dimension(40,40));
        Cbo.fillWaehrung(false,true);
        
	setValue(dWert,iAIC_Stamm);
        bCbo=rbCbo;
	Build();
}

/*public WaehrungsEingabe(int iAIC_Stamm,Global glob)
{
	this(0.0,iAIC_Stamm,glob,true,2);
}*/

private void Build()
{
	setLayout(new BorderLayout());
	add("Center",Edt);
	if (bCbo)
        {
          add("East", Cbo);
          Cbo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ev) {
              if(ev.getStateChange() == ItemEvent.SELECTED) {
                double d = Edt.doubleValue();
                double dfJetzt = Cbo.getSelectedFaktor();
                d = d * dfJetzt / dfVorher;
                //int iZiffern = 16;
                //double df= d==0 ? 1 : Math.pow(10,Math.rint(iZiffern - Math.log(d)/Math.log(10)));
                //Edt.setValue(Math.rint(d*df)/df);
                Edt.setValue(d);
                dfVorher = dfJetzt;
              }
            }
          });
        }
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
}

public void setValue(double dWert, int iAIC_Stamm)
{
	Cbo.setSelectedAIC(iAIC_Stamm);
	dfVorher=Cbo.getSelectedFaktor();
	Edt.setValue(dWert);
        dAlterWert=getAbsValue();
}

public void setAktValue(double dWert, int iAIC_Stamm)
{
	double d=Cbo.getItem(iAIC_Stamm).getFaktor();
	dAlterWert=d==0.0 ? 0.0:dWert/d;
}

public double getValue()
{
	return Edt.doubleValue();
}

public int getWaehrung()
{
	return Cbo.getSelectedAIC();
}

public double getAbsValue()
{
	double d=Cbo.getSelectedFaktor();
	//g.progInfo("WaehrungsEingabe.getAbsValue:"+getValue()+"/"+d);
	return d==0.0 ? 0.0:Edt.doubleValue()/d;
}

public boolean Modified()
{
	//g.progInfo("WaehrungsEingabe.Modified:"+dAlterWert+"-"+getAbsValue()+"="+(dAlterWert-getAbsValue()));
	boolean b=Static.Round6(dAlterWert)!=Static.Round6(getAbsValue()) || bCbo && Cbo.Modified();
        //if (b)
        //  g.progInfo("WaehrungsEingabe.Modified:"+Static.Round6(dAlterWert)+"-"+Static.Round6(getAbsValue())+"="+(Static.Round6(dAlterWert)-Static.Round6(getAbsValue())));
        return b;
}

public void Reset2()
{
  Cbo.Reset2();
  setValue(dAlterWert,Cbo.getSelectedAIC());
}


public boolean isNull()
{
	return getValue()==0.0 || Cbo.getSelectedFaktor()==0.0;
}

public String toString()
      {
        return Edt.doubleValue()+" "+Cbo.getSelectedBezeichnung();
      }

public void setFont(Font font)
{
	if (Cbo !=null)
	{
		if (font==null)
			font=Transact.fontStandard;
		Cbo.setFont(font);
		Edt.setFont(font);
	}
}

public void setEnabled(boolean b)
{
        Edt.setEnabled(b);
        Cbo.setEnabled(b);
}

public void setEditable(boolean b)
{
  Edt.setEditable(b);
  Cbo.setEditable1(b);
}

public javax.swing.JTextField getWaehrungsEditor()
{
	return Edt;
}

public void setMax()
{
  Edt.setMax(Edt.getInteger());
}

// add your data members here
private Global g;
private Zahl Edt;
private ComboSort Cbo;
private double dfVorher;
private double dAlterWert;
private boolean bCbo;
}

