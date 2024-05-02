/*
    All_Unlimited-Allgemein-Eingabe-MassEingabe.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Dimension;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Transact;

public class MassEingabe extends javax.swing.JPanel
{
/*public MassEingabe(double dWert, int iAIC_Stamm, Global glob)
{
	g=glob;
	CboMass = new ComboSort(g);
	CboMass.fillMass(iAIC_Stamm,false);
	setValue(dWert,iAIC_Stamm);
	dfVorher=CboMass.getSelectedFaktor();
	Build();
}*/

/*public MassEingabe(int iAIC_Stamm,Global glob)
{
	this(iAIC_Stamm,glob,true);
}*/

/**
	 *
	 */
	private static final long serialVersionUID = -8302928656874386632L;
public MassEingabe(int riAIC_Stamm,Global glob,boolean rbMass,boolean bCbo,int iStellen,String sFormat,boolean bDez)
{
	g=glob;
	iAIC_Stamm=riAIC_Stamm;
        if (g.bH_dez && sFormat!=null && sFormat.equals("hm"))
            sFormat="0.00";
        if (!bDez && (g.bH_min && iAIC_Stamm==g.iAicStunde || sFormat!=null && sFormat.equals("hm")))
        {
          EdtZeit = new Datum(g, "HHH:mm");
          EdtZeit.bMass=true;
        }
        else //if (sFormat==null || !sFormat.equals("hm"))
        {
          CboMass = new ComboSort(g);
          CboMass.setPreferredSize(null);
          CboMass.setFocusable(false);
          EdtZahl = new Zahl(0.0, iStellen);
          bMass=rbMass;
          if (bMass)
          {
            if (iAIC_Stamm > 0)
            {
              CboMass.fillMass(iAIC_Stamm, false);
              setValue(0.0, iAIC_Stamm);
            }
            dfVorher = CboMass.getSelectedFaktor();
          }
          else
          {
            CboMass.fillStammTable(iAIC_Stamm, false);
            dAlterWert = 0.0;
          }
          CboMass.setPreferredSize(new Dimension((int)CboMass.getPreferredSize().getWidth()+1,16));
          //g.fixInfo("MassEingabe:"+CboMass.getPreferredSize());
        }

	Build(iAIC_Stamm==0 && EdtZahl!=null,bCbo && EdtZahl!=null);
}

private void Build(boolean bAuswahl,boolean bCbo)
{
	setLayout(new BorderLayout());
	add("Center",EdtZahl==null ? EdtZeit:EdtZahl);
	if (bAuswahl)
	{
		Panel Pnl=new Panel(new GridLayout());
		Pnl.add(CboMass);
		CboAuswahl=new ComboSort(g);
		CboAuswahl.fillStammTable(SQL.getInteger(g,"select aic_stammtyp from stammtyp where kennung='SI-Einheit'"),false);
		CboAuswahl.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange() == ItemEvent.SELECTED)
				{
					CboMass.setVisible(false);
					CboAuswahl.setVisible(false);
					//g.progInfo("vor :"+CboMass.getSelectedBezeichnung()+"/"+CboMass.getSelectedAIC());
					CboMass.fillMass2(((ComboSort)ev.getSource()).getSelectedAIC(),false);
					//g.progInfo("nach:"+CboMass.getSelectedBezeichnung()+"/"+CboMass.getSelectedAIC());
					CboMass.setVisible(true);
					CboAuswahl.setVisible(true);
				}
			}
		});
		Pnl.add(CboAuswahl);
		add("East",Pnl);
	}
	else if (bCbo)
		add("East",CboMass);
	if (bMass && EdtZahl!=null)
		CboMass.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange() == ItemEvent.SELECTED)
				{
					CboMass.setVisible(false);
					double d = EdtZahl.doubleValue();
					double dfJetzt = CboMass.getSelectedFaktor();
					d = d*dfVorher/dfJetzt;
					//int iZiffern = 10;
					//double df= d==0 ? 1 : Math.pow(10,Math.rint(iZiffern - Math.log(d)/Math.log(10)));
					//EdtZahl.setValue(Math.rint(d*df)/df);
					//System.out.println("CboMass.itemStateChanged:"+dfVorher+"/"+dfJetzt+"->"+d);
					EdtZahl.setValue(d);
					dfVorher=dfJetzt;
					CboMass.setVisible(true);
				}
			}
		});
	addFocusListener(new FocusListener()
	{
			public void focusGained(FocusEvent fe)
			{
                          if (EdtZahl==null)
                            EdtZeit.requestFocus();
                          else
                            EdtZahl.requestFocus();
			}
			public void focusLost(FocusEvent fe)
			{
			}
	});
}

public void setValue(double dWert, int riAIC_Stamm)
{
	//g.progInfo("MassEingabe.setValue:"+dWert+"/"+iAIC_Stamm);
        if (EdtZahl==null)
        {
          dAlterWert=dWert;//Math.round(dWert*3600);
          iAlteEinheit=iAIC_Stamm;
          EdtZeit.setMass(dAlterWert);
          //EdtZeit.setDate(new java.util.Date(Math.round(dAlterWert*DateWOD.STUNDE-DateWOD.getZO())));
          dAlterWert*=3600;
          g.testInfo("alter Wert="+dAlterWert);
          return;
        }
        if (dWert != 0)
          iAIC_Stamm=riAIC_Stamm;
        boolean bGleich=CboAuswahl==null || g.GleicheMassgruppe(CboAuswahl.getSelectedAIC(),iAIC_Stamm);
	CboMass.setVisible(false);
	if(!bGleich)
	{
		int iGruppe=g.TabMass.getI("Gruppe");
		g.progInfo("Neufüllen mit "+iGruppe);
		CboAuswahl.setSelectedAIC(iGruppe);
		CboAuswahl.setVisible(false);
		CboMass.fillMass2(iGruppe,false);
		CboAuswahl.setVisible(true);
	}
        CboMass.setSelectedAIC(iAIC_Stamm);
	CboMass.setVisible(true);
        iAlteEinheit=iAIC_Stamm;
	/*if (bMass)
	{
		dAlterWert=dWert*CboMass.getSelectedFaktor();
	}
	else
	{
		iAlteEinheit=iAIC_Stamm;
		dAlterWert=dWert;
	}*/
        EdtZahl.setValue(dWert);
        Reset();
}

public void setAktValue(double dWert, int iAIC_Stamm)
{
	if (bMass && dWert!=0.0)
		dAlterWert=dWert*CboMass.getItem(iAIC_Stamm).getFaktor();
	else
	{
		iAlteEinheit=iAIC_Stamm;
		dAlterWert=dWert;
	}
}

public double getValue()
{
	return EdtZahl==null ? EdtZeit.isNull() ? 0: /*(double)(EdtZeit.getMillis()+DateWOD.getZO())/DateWOD.STUNDE*/EdtZeit.getMass():EdtZahl.doubleValue();
}

public int getMass()
{
	return EdtZahl==null ? g.iAicStunde:CboMass.getSelectedAIC();
}

public double getAbsValue()
{
	return getValue()*(bMass?CboMass.getSelectedFaktor():EdtZeit != null?3600:1);
}

public boolean Modified()
{
  //g.fixtestError("MassEingabe.Modified:"+dAlterWert+"/"+getAbsValue()+", "+getMass()+"/"+iAlteEinheit);
  if (hm())
    return EdtZeit.Modified();
  else
    return /*EdtZahl.Modified() */dAlterWert!=getAbsValue() || !(bMass || getMass()==iAlteEinheit);
}

public void Reset()
{
  dAlterWert=getAbsValue();
  if (EdtZahl==null)
    EdtZeit.setAktDate(EdtZeit.getDateTime());
  else
    EdtZahl.setAktValue(EdtZahl.getDouble());
  if (bMass)
  {
    iAlteEinheit = CboMass.getSelectedAIC();
    CboMass.Reset();
  }
}

public void Reset2()
{
  if (EdtZahl==null)
    EdtZahl.Reset2();
  else
    EdtZahl.Reset2();
}

public boolean isNull()
{
	return getValue()==0;
}

public boolean hm()
{
  return EdtZeit != null;
}

public String toString()
{
	return EdtZahl==null ? EdtZeit.toString():EdtZahl.getText()+" "+CboMass.getSelectedKennung();
}

public void setFont(Font font)
{
	if (CboMass !=null)
	{
		if (font==null)
			font=Transact.fontStandard;
		CboMass.setFont(font);
		EdtZahl.setFont(font);
		if (CboAuswahl !=null)
			CboAuswahl.setFont(font);
	}
        else if (hm())
          EdtZeit.setFont(font);
}

public void setEnabled(boolean b)
{
  if (EdtZeit != null)
    EdtZeit.setEnabled(b);
  else
  {
    EdtZahl.setEnabled(b);
    CboMass.setEnabled(b);
    if (CboAuswahl != null)
      CboAuswahl.setEnabled(b);
  }
}

public void setEditable(boolean b)
{
  if (hm())
    EdtZeit.setEditable(b);
  else
  {
    EdtZahl.setEditable(b);
    CboMass.setEditable1(b);
    if (CboAuswahl != null)
      CboAuswahl.setEditable(b);
  }
}

public javax.swing.JTextField getMassEditor()
{
  return EdtZeit != null ? EdtZeit.getDatumEditor():EdtZahl;
}

public void setMax(int riMax)
{
	if (hm())
	    EdtZeit.setMax(riMax);
	  else
	    EdtZahl.setMax(riMax);
}

public void setMax()
      {
        EdtZahl.setMax(EdtZahl.getInteger());
      }

// add your data members here
private Global g;
private boolean bMass=false;
private Zahl EdtZahl;
private Datum EdtZeit=null;
private ComboSort CboMass;
private ComboSort CboAuswahl=null;
private double dfVorher;
private double dAlterWert;
private int iAlteEinheit;
private int iAIC_Stamm;
}

