/*
    All_Unlimited-Allgemein-Anzeige-Zahl1.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

import java.io.Serializable;

// add your custom import statements here
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.Static;

public class Zahl1 extends Object implements Serializable,Comparable<Zahl1>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Zahl1(Object ObjZahl,String rsFormat)
	{
	    sFormat=rsFormat==null||rsFormat.equals("")?"0.00":rsFormat.equals("x")?"0.0###########":rsFormat;
		if(ObjZahl==null)
			bNull=true;
		else if(ObjZahl instanceof Double)
		{
			//dZahl=bNull?0.0:((Double)ObjZahl).doubleValue();
                        dZahl=((Double)ObjZahl).doubleValue();
                        //bNull=dZahl==0;
		}
                else if(ObjZahl instanceof String)
                {
                  dZahl=Double.parseDouble((String)ObjZahl);
                }
		else
			Static.printError("Zahl1: Zahl1("+ObjZahl.getClass().getName()+")");

		Count.add(Count.Zahl1);
	}

	public Zahl1(double rdZahl,String rsFormat)
	{
		sFormat=rsFormat==null||rsFormat.equals("")?"0.00":rsFormat.equals("x")?"0.0###########":rsFormat;
		dZahl=rdZahl;
		bNull=dZahl==0 || rsFormat!=null && rsFormat.equals("0") && Math.abs(dZahl)<0.5;
		Count.add(Count.Zahl1);
	}

	public Zahl1(Zahl1 z)
	{
		sFormat=z.sFormat;
		dZahl=z.dZahl;
                bNull=z.bNull;
		Count.add(Count.Zahl1);
	}

	public void finalize()
	{
		Count.sub(Count.Zahl1);
	}

	public double getValue()
	{
		return iAnz==1 ? dZahl:dZahl/iAnz;
	}
	
	@Override public int compareTo(Zahl1 zahl)
	{
		return getValue()<zahl.getValue() ? -1:getValue()>zahl.getValue() ? +1:0;
	}

	public void add(double d)
	{
		dZahl+=d;
		iAnz++;
	}
	
	public void add2(double d)
	{
		dZahl+=d;
	}

	public Double getDouble()
	{
		return(bNull?null:new Double(dZahl));
	}

	public boolean isNull()
	{
		return bNull || dZahl==0;
	}
	
	public String getS()
	{
		return sFormat.equals("hm")?Static.hm(getValue()):(iAnz>1?"ø ":"")+new java.text.DecimalFormat(sFormat).format(getValue());
	}

	public String toString()
	{
		return bNull ? "":getS();
	}

	public boolean equals(Double Objequal)
	{
		return Static.Gleich(getDouble(),Objequal);
	}

	public boolean equals(double d)
	{
		return bNull || d==dZahl;
	}

        public void setNull()
        {
          bNull=true;
        }

	private double dZahl=0.0;
	private boolean bNull=false;
	private String sFormat="0.";
	private int iAnz=1;

	// add your data members here
}

