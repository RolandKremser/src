/*
    All_Unlimited-Allgemein-Eingabe-VonBisEingabe.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.DateWOD;

public class VonBisEingabe extends javax.swing.JPanel
{
/**
	 *
	 */
	private static final long serialVersionUID = -5949318042135381409L;
public VonBisEingabe(Date DatVon, Date DatBis, String sMask, Global glob,int iStamm, int iNachkomma)
{
  g=glob;
	dFaktor=g.getFaktor(iStamm);
        if (g.bH_min && iStamm==g.iAicStunde || iNachkomma<0)
        {
          DatumDauer=new Datum(g,"HHH:mm");
          DatumDauer.bMass=true;
        }
        else
	  zDauer = dFaktor==0.0 ? null:new Zahl(0.0,iNachkomma);
	sMask=sMask.equals("")?"dd.MM.yyyy HH:mm:ss":sMask;
	Build(sMask);
	setValue(DatVon, DatBis);
	if (zDauer!=null && sMask.equals("-"))
		addFocusListener(new FocusListener()
		{
				public void focusGained(FocusEvent fe)
				{
					zDauer.requestFocus();
				}
				public void focusLost(FocusEvent fe)
				{
				}
		});
}

private void Build(String rsMask)
{
  sMask=rsMask;
	DatumVon = new Datum(g,sMask);
	DatumBis = new Datum(g,sMask);
        bJahr=sMask.indexOf("yy")>-1 && sMask.indexOf("MM")==-1;
        bMonat=sMask.indexOf("MM")>-1 && sMask.indexOf("dd")==-1;
        bWoche=sMask.indexOf("ww")>-1;
	bDatum=sMask.indexOf("dd")>-1 && sMask.indexOf("HH")==-1;
        bZeit=sMask.indexOf("HH")>-1 && sMask.indexOf("dd")==-1;
	setLayout(new GridLayout(1,0,2,2));

	if (!sMask.equals("-"))
	{
		JPanel PnlBorder1 = new JPanel(new BorderLayout());
		PnlBorder1.add("Center",DatumVon);
		PnlBorder1.add("East",new JLabel(" - "));
		add(PnlBorder1);
		add(DatumBis);

		DatumVon.Edt.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent e)
			{/*
				g.progInfo("DatumVon.changedUpdate");
				DatumVon.Check();
				Calculate(true);*/
			}
			public void insertUpdate(DocumentEvent e)
			{
				//g.progInfo("DatumVon.insertUpdate");
				DatumVon.Check();
				Calculate(true);
			}
			public void removeUpdate(DocumentEvent e)
			{/*
				g.progInfo("DatumVon.removeUpdate");
				//DatumVon.Check();
				Calculate(true);*/
			}
		});
		DatumBis.Edt.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent e)
			{/*
				g.progInfo("DatumBis.changedUpdate");
				DatumBis.Check();
				Calculate(true);*/
			}
			public void insertUpdate(DocumentEvent e)
			{
				//g.progInfo("DatumBis.insertUpdate");
				DatumBis.Check();
				Calculate(true);
			}
			public void removeUpdate(DocumentEvent e)
			{/*
				g.progInfo("DatumBis.removeUpdate");
				//DatumBis.Check();
				Calculate(true);*/
			}
		});
	}
        if (DatumDauer != null || zDauer != null)
	{
		JPanel PnlBorder2 = new JPanel(new BorderLayout());
                JLabel Lbl=new JLabel(" "+g.getBegriffS("Show","Dauer")+":");
                Lbl.setFont(Transact.fontStandard);
		PnlBorder2.add("West",Lbl);
		PnlBorder2.add("Center",DatumDauer != null ? DatumDauer :zDauer);
		add(PnlBorder2);
                if (zDauer != null)
                  zDauer.addKeyListener(new KeyListener()
                  {
			public void keyTyped(KeyEvent e)
			{
			}
			public void keyPressed(KeyEvent e)
			{
			}
			public void keyReleased(KeyEvent e)
			{
				Calculate(false);
			}
                  });
	}
}

private void Calculate(boolean bVonBis)
{
	if(!bCalc)
	{
		bCalc=true;
		if(bVonBis)
		{
			if(DatumVon.isValid2() && DatumBis.isValid2() && !DatumVon.isNull() && !DatumBis.isNull())
			{
				double dVon = DatumVon.getMillis()/1000.0;
				double dBis = DatumBis.getMillis()/1000.0;
				//g.progInfo("dVon="+dVon+", dBis)"+dBis);
				if (dVon>dBis || bDatum)
					dBis+=24*3600;
                                if (bJahr || bMonat || bWoche)
                                {
                                  DateWOD DW=new DateWOD(DatumBis.getMillis());
                                  if (bWoche)
                                    DW.nextWeek();
                                  else if (bMonat)
                                    DW.nextMonth();
                                  else if (bJahr)
                                    DW.nextYear();
                                  dBis=DW.getAllSeconds();
                                }
                                //g.progInfo("Dauer="+((dBis-dVon)/dFaktor));
				if (zDauer != null)
					zDauer.setValue((dBis-dVon)/dFaktor);
			}
		}
		else if (zDauer != null)
		{
			//if(zDauer.isValid2())
			{
				if(DatumVon.isValid2())
				{
					Date DatVon = DatumVon.getDateTime();
					DatumBis.setDate(DatVon!=null?new Date(DatVon.getTime()+Math.round(zDauer.doubleValue()*dFaktor*1000.0)):null,true);
				}
				else if(DatumBis.isValid2())
				{
					Date DatBis = DatumBis.getDateTime();
					DatumVon.setDate(DatBis!=null?new Date(DatBis.getTime()-Math.round(zDauer.doubleValue()*dFaktor*1000.0)):null);
				}
			}
		}
		bCalc=false;
	}
}

public void setValue(Date DatVon, Date DatBis, double dDauer)
{
  //g.fixtestInfo("VonBisEingabe.setValue:"+DatVon+"/"+DatBis+"/"+dDauer);
	setAktValue(DatVon,DatBis,dDauer);
        DatumVon.setDate(DatVon);
        if (DatBis !=null)
          if (bDatum)
          {
            DateWOD DW = new DateWOD(DatBis);
            DW.yesterday();
            DatBis = DW.toTimestamp();
          }
          else if (bJahr || bMonat || bWoche)
          {
            DateWOD DW = new DateWOD(DatBis);
            if (bWoche)
              DW.prevWeek();
            else if (bMonat)
              DW.prevMonth();
            else if (bJahr)
              DW.prevYear();
            DatBis = DW.toTimestamp();
          }
        //g.progInfo("bis="+DatBis);
	DatumBis.setDate(DatBis,true);
	if (zDauer != null)
		zDauer.setValue(dDauer/dFaktor);
        else if (DatumDauer != null)
          DatumDauer.setMass(dDauer/dFaktor);//setDate(DateWOD.toTime(dDauer));
        dOldDauer=getDauer();
//   g.fixtestInfo("-Von:"+Static.DateToString(DatumVon.getDateTime())+"-"+Static.DateToString(DatumBis.getDateTime())+"/"+dOldDauer);
}

public void setValue(Date DatVon, Date DatBis)
{
	setValue(DatVon, DatBis, DatVon!=null && DatBis!=null?(DatBis.getTime()-DatVon.getTime())/1000.0:0.0);
}

public void setAktValue(Date DatVon, Date DatBis, double dDauer)
{
	lOldVon = DatVon!=null?DatVon.getTime():Long.MIN_VALUE;
	lOldBis = DatBis!=null?DatBis.getTime():Long.MIN_VALUE;
	dOldDauer = dDauer;
        //g.progInfo("VonBisEingabe.setAktValue:"+lOldVon+"/"+lOldBis+"/"+dOldDauer);
}

public void setAktValue(Date DatVon, Date DatBis)
{
	setAktValue(DatVon, DatBis, DatVon!=null && DatBis!=null?(DatBis.getTime()-DatVon.getTime())/1000.0:0.0);
}

public Vector getValue()
{
	Vector<Comparable> Vec = new Vector<Comparable>();
	Vec.addElement(DatumVon.getDateTime());
	Vec.addElement(getBis());
	Vec.addElement(new Double(zDauer==null?0.0:zDauer.doubleValue()*dFaktor));

	return Vec;
}

public All_Unlimited.Allgemein.Anzeige.VonBis getVonBis()
{
        return new All_Unlimited.Allgemein.Anzeige.VonBis(g,getVon(),getBis(),getDauer(),"");
}

public String toString()
{
	return DatumVon+" - "+DatumBis;
}

/*public String Format(String s)
{
        return new All_Unlimited.Allgemein.Anzeige.VonBis(getVon(),getBis(),0,s,0).toString();
}*/

public Date getVon()
{
	//g.fixtestInfo("-Von:"+Static.DateToString(DatumVon.getDateTime()));
	return DatumVon.getDateTime();
}

public Date getBis()
{
	//g.fixtestInfo("-Bis:"+Static.DateToString(DatumBis.getDateTime()));
	if (DatumBis.isNull())
		return null;
	long lBis = DatumBis.getMillis();
	if (bDatum)
        {
          DateWOD DW=new DateWOD(lBis);
          DW.tomorrow();
          if (!DatumVon.isNull() && !DatumBis.isNull() && lBis<DatumVon.getMillis())
          {
            if (sMask.indexOf("MM") >= 0 && sMask.indexOf("yy") < 0)
              DW.nextYear();
            if (sMask.indexOf("dd") >= 0 && sMask.indexOf("MM") < 0)
              DW.nextMonth();
          }
          //g.progInfo("Bis="+DW);
          return DW.toTimestamp();
          //return new java.sql.Timestamp(lBis + 86400000l);
        }
        else if (bJahr || bMonat || bWoche)
        {
          DateWOD DW=new DateWOD(lBis);
          if (bWoche)
            DW.nextWeek();
          else if (bMonat)
            DW.nextMonth();
          else if (bJahr)
            DW.nextYear();
          return DW.toTimestamp();
        }
	else
	  return new java.sql.Timestamp(lBis+(!bZeit || DatumVon.isNull() || DatumVon.getMillis()<=lBis ? 0:86400000l));
}

public double getDauer()
{
	return DatumDauer != null ? DatumDauer.getMillis()/1000:zDauer==null?dOldDauer:zDauer.doubleValue();
}

public double getSeconds()
{
	return DatumDauer != null ? new DateWOD(DatumDauer.getMillis()).getAbsSeconds():zDauer==null?0.0:zDauer.doubleValue()*dFaktor;
}

public double getFaktor()
{
	return dFaktor;
}

public boolean isNull()
{
	return DatumVon.isNull() && DatumBis.isNull() && (zDauer==null || zDauer.doubleValue() ==0.0);
}

public boolean Modified()
{
	Date DatVon = getVon();
	Date DatBis = getBis();

	//g.progInfo("Dauer: "+getDauer()+" / "+dOldDauer);
	//g.progInfo("Old: "+lOldVon+" / "+lOldBis);
	//g.progInfo("New: "+(DatVon==null?Long.MIN_VALUE:DatVon.getTime())+" / "+(DatBis==null?Long.MIN_VALUE:DatBis.getTime()));
	//g.progInfo(DatOldVon.getTime()+" / "+DatOldBis.getTime());
	//g.progInfo(DatVon.getTime()+" / "+DatBis.getTime());
        //g.progInfo("Bis: "+new Date(lOldBis)+" -> "+DatBis);
	//g.progInfo("Rueckgabe: "+(getSeconds()!=dOldDauer || (DatVon==null || DatOldVon==null ?DatVon!=DatOldVon:DatVon.getTime()!=DatOldVon.getTime()) || (DatBis==null || DatOldBis==null ?DatBis!=DatOldBis:DatBis.getTime()!=DatOldBis.getTime())));

	boolean b= getDauer()!=dOldDauer || (DatVon==null?Long.MIN_VALUE:DatVon.getTime())!=lOldVon
		|| (DatBis==null?Long.MIN_VALUE:DatBis.getTime()) != lOldBis;
        //g.progInfo("Rueckgabe: "+b);
        return b;
}

public void Reset2()
{
  setValue(new Date(lOldVon),new Date(lOldBis),dOldDauer);
}

public void Reset()
{
  dOldDauer=getDauer();
  Date DatVon=getVon();
  Date DatBis=getBis();
  lOldVon = DatVon!=null?DatVon.getTime():Long.MIN_VALUE;
  lOldBis = DatBis!=null?DatBis.getTime():Long.MIN_VALUE;
}

public boolean isValid2()
{
	boolean bOk = DatumVon.isValid2() && DatumBis.isValid2();// && zDauer.isValid();

	//g.progInfo("isValid:"+DatumVon.isValid()+" / "+DatumBis.isValid());

	if(bOk)
          bOk = DatumVon.isNull() || DatumBis.isNull() ? true:getVon().getTime()<=getBis().getTime();

	return(bOk);
}

public Datum getVonEditor()
{
	return DatumVon;
}

public Datum getBisEditor()
{
	return DatumBis;
}

public Zahl getDauerEditor()
{
	return zDauer;
}

public void setFont(Font font)
{
	if (DatumVon !=null)
	{
		DatumVon.setFont(font);
		DatumBis.setFont(font);
		if (zDauer != null)
			zDauer.setFont(font);
	}
}

public void setEnabled(boolean b)
{
  if (DatumVon != null)
  {
    DatumVon.setEnabled(b);
    DatumBis.setEnabled(b);
  }
  if (zDauer !=null)
    zDauer.setEnabled(b);
}

public void setEditable(boolean b)
{
        DatumVon.setEditable(b);
        DatumBis.setEditable(b);
        if (zDauer != null)
          zDauer.setEditable(b);
}

// add your data members here
private Global g;
private Datum DatumVon;
private Datum DatumBis;
private Zahl zDauer=null;
private Datum DatumDauer=null;
private long lOldVon;
private long lOldBis;
private double dOldDauer=0.0;
private double dFaktor;
private boolean bCalc=false;
private boolean bJahr=false;
private boolean bMonat=false;
private boolean bWoche=false;
private boolean bDatum=false;
private boolean bZeit=false;
private String sMask=null;
}

