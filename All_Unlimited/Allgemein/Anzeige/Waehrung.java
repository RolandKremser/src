/*
    All_Unlimited-Allgemein-Anzeige-Waehrung.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

// add your custom import statements here
import java.util.Vector;

import All_Unlimited.Allgemein.Count;

public class Waehrung extends Object implements Comparable<Waehrung>
{
    // add your data members here
	public Waehrung(Vector<Object> rVec,Object ObjZahl,String rsFormat)
	{
		sFormat=rsFormat.equals("") ? "#,##0.00":rsFormat;
		//Vec=rVec;
		iAIC=((Integer)rVec.elementAt(2)).intValue();
		sKZ=(String)rVec.elementAt(1);
		dFaktor=((Double)rVec.elementAt(0)).doubleValue();
		if(ObjZahl instanceof Double)
                  dZahl=((Double)ObjZahl).doubleValue();
                //else if(ObjZahl instanceof String)
                //  dZahl=Double.valueOf((String)ObjZahl);
		else
                  bNull=true;
                //System.out.println("Waehrung:"+ObjZahl+"->"+dZahl+"/"+sKZ+"/"+dFaktor+"/"+bNull);
		Count.add(Count.Waehrung);
	}

	/*public Waehrung(Vector rVec,double rdZahl,String rsFormat)
	{
		sFormat=rsFormat.equals("") ? "#,##0.00":rsFormat;
		sKZ=(String)rVec.elementAt(1);
		dFaktor=((Double)rVec.elementAt(0)).doubleValue();
		dZahl=rdZahl;
		Count.add(Count.Waehrung);
	}*/

        public Waehrung(Waehrung w)  // für Summenbildung in ShowAbfrage
	{
		sFormat=w.sFormat;
		sKZ=w.sKZ;
		dFaktor=w.dFaktor;
		dZahl=w.dZahl;
		//iAnz=w.iAnz;
		Count.add(Count.Waehrung);
	}

	public void finalize()
	{
		Count.sub(Count.Waehrung);
	}

	public double getValue()
	{
		return bNull ? 0.0:dZahl;
	}

	public double getAbsValue()
	{
		return bNull ? 0.0:iAnz==1?dZahl*dFaktor:dZahl*dFaktor/iAnz;
	}
	
	@Override public int compareTo(Waehrung mass)
	{
		return getAbsValue()<mass.getAbsValue() ? -1:getAbsValue()>mass.getAbsValue() ? +1:0;
	}

	public void add(double d)
	{
		dZahl+=d;
		iAnz++;
		//System.out.println("Waehrung.add:"+dZahl+"/"+iAnz+" -> "+getAbsValue());
	}
	
	public void add2(double d)
	{
		dZahl+=d;
	}

	public boolean isNull()
	{
		return(bNull || dZahl==0.0);
	}

    public String toString()
	{
		String s=bNull ? "":(iAnz>1?"ø ":"")+new java.text.DecimalFormat(sFormat).format(getAbsValue())+(sKZ==null?"":" "+sKZ);
		//System.out.println("Waehrung.toString:"+s);
		return s;
	}

	public int getAIC()
	{
		return iAIC;
	}

        public void setNull()
        {
          bNull=true;
        }

	private double dZahl=0;
	private boolean bNull=false;
	//Vector Vec;
	private String sKZ;
	private double dFaktor;
	private String sFormat;
	private int iAIC;
	private int iAnz=1;
}

