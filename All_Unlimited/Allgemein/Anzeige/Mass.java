/*
    All_Unlimited-Allgemein-Anzeige-Mass.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

import java.util.Vector;

import org.json.JSONObject;

import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;

public final class Mass extends Object implements Comparable<Mass>
{
    public Mass(Global rg,Vector<Object> rvVector,Object ObjZahl,String rsFormat)
	{
    	g=rg;
		//System.out.println("Mass:"+rvVector+"/"+Static.print(ObjZahl)+"/"+rsFormat);
		if(rsFormat!=null&&!rsFormat.equals(""))
		   sFormat=rsFormat;
		//vVector=rvVector;
		if (ObjZahl == null || rvVector.elementAt(0)==null)
		{
			//System.out.println("Mass:"+rvVector+"/"+ObjZahl);
			bNull = true;
//			if (ObjZahl != null)
//				Static.printError("Mass: Zahl "+ObjZahl+" mit Format "+rsFormat+" nicht darstellbar weil Faktor fehlt!");
		}
		else if(ObjZahl instanceof Double)
		{
			iAIC=((Integer)rvVector.elementAt(2)).intValue();
			if (iAIC!=0)
			{
				dZahl = ((Double)ObjZahl).doubleValue();
				sKZ=(String)rvVector.elementAt(1);
				dFaktor=((Double)rvVector.elementAt(0)).doubleValue();
                                //System.out.println("Mass-Double:"+dZahl+";"+sKZ+";"+dFaktor);
			}
			else
			{
				bNull = true;
				//sKZ=(String)rvVector.elementAt(1);
				//dFaktor=1;
			}
		}
		else
			Static.printError("Mass: Mass funktioniert nicht mit "+ObjZahl.getClass().getName());

		Count.add(Count.Mass);
		//if(ObjZahl instanceof Long||ObjZahl instanceof Float||ObjZahl instanceof Double||ObjZahl instanceof Integer)
			//dZahl=new Double(ObjZahl.toString());
	}

	public Mass(Mass w)
	{
		g=w.g;
		sFormat=w.sFormat;
		sKZ=w.sKZ;
		dFaktor=w.dFaktor;
		dZahl=w.dZahl;
		//iAnz=w.iAnz;
		Count.add(Count.Mass);
	}


	public void finalize()
	{
		Count.sub(Count.Mass);
	}

	public double getValue()
	{
		return bNull?0.0:dZahl;
	}

        public void setValue(double d)
        {
          dZahl=d;
        }

	public double getAbsValue()
	{
		if (!bNull && dFaktor==0.0)
			return dZahl;
			//Static.printError("Mass.getAbsValue: Zahl nicht darstellbar weil Faktor fehlt!");
		return bNull || dFaktor==0.0 ? 0.0:iAnz==1?dZahl/dFaktor:dZahl/dFaktor/iAnz;
	}
	
	@Override public int compareTo(Mass mass)
	{
		return getAbsValue()<mass.getAbsValue() ? -1:getAbsValue()>mass.getAbsValue() ? +1:0;
	}

	public void add(double d)
	{
		dZahl+=d;
		iAnz++;
	}
	
	public void add2(double d)
	{
		dZahl+=d;
		//iAnz++;
	}

	public int getAIC()
	{
		return iAIC;
	}

	public boolean isNull()
	{
		return bNull || dZahl==0.0;
	}

        public void setNull()
        {
          bNull=true;
        }

	public String toString()
	{
          if (g.bH_dez && sFormat.equals("hm"))
            sFormat="0.00";
          return bNull ? "":g.bH_min && iAIC==g.iAicStunde || sFormat.equals("hm")?Static.hm(getValue()):new java.text.DecimalFormat(sFormat).format(getAbsValue())+(sKZ==null?"":" "+sKZ);
	}
	
	public JSONObject toJSON(boolean bHHmm)
	{
		JSONObject jO=new JSONObject();
//		if (bHHmm)
//		{
//			jO.put("value", bNull ? "":Static.hm(getValue()));
//		}
//		else
//		{
//			jO.put("value", getValue());
//			jO.put("aic",iAIC);
//			if (dFaktor != 0)
//				jO.put("faktor", dFaktor);
//			jO.put("kz",sKZ);
//			if (bHHmm && iAIC==Static.iAicStunde)
//				jO.put("HHmm",true);
//		}
		return jO;
	}

    public boolean equals(Mass mEqual)
	{
		if(bNull&&mEqual.isNull())
			return(true);
		else if(bNull || mEqual.isNull())
			return(false);
		else
			return(mEqual.getValue()==dZahl);
	}

    private Global g=null;
	private double dZahl=0.0;
	private boolean bNull=false;
	//private Vector vVector;
	private String sFormat="0.00";
	private String sKZ;
	private double dFaktor;
	private int iAIC=0;
	private int iAnz=1;

	// add your data members here
}

