/*
    All_Unlimited-Allgemein-Anzeige-VonBis.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;

public class VonBis extends Object implements Comparable<VonBis>
{
    // add your data members here
	public VonBis(Global rg,Object ObjVon,Object ObjBis,double rdDauer,String rsFormat,double rdFaktor)
	{
		this(rg,ObjVon,ObjBis,rdDauer,rsFormat,2,rdFaktor);
	}

	public VonBis(Global rg,Object ObjVon,Object ObjBis,double rdDauer,String rsFormat,int riStellen,double rdFaktor)
	{
		g=rg;
		if (g==null)
			Global.printStackTraceS(new Exception());
		if(ObjVon instanceof Date || ObjBis instanceof Date)
		{
			DatVon=(Date)ObjVon;
			DatBis=(Date)ObjBis;
			bDate=true;
		}
		sFormat=rsFormat;
		iStellen=riStellen;
		dFaktor=rdFaktor;
		//bDauer=dFaktor!=0.0;
		dDauer=dFaktor!=0.0?rdDauer/dFaktor:0.0;
		Count.add(Count.VonBis);
	}
	
	public VonBis(Object ObjVon,Object ObjBis,double rdDauer,String rsFormat,int riStellen,Global rg,int riStamm,boolean bKE)
	{
		this(ObjVon,ObjBis,rdDauer,rsFormat,riStellen,rg,riStamm,bKE,0);
	}

	public VonBis(Object ObjVon,Object ObjBis,double rdDauer,String rsFormat,int riStellen,Global rg,int riStamm,boolean bKE,int iZone)
	{
		g=rg;
		if (g==null)
			Global.printStackTraceS(new Exception());
		if(ObjVon instanceof Date || ObjBis instanceof Date)
		{
			DatVon=(Date)ObjVon;
			DatBis=(Date)ObjBis;
			bDate=true;
		}
		if (iZone != 0)
		{
			if (DatVon != null)
				DatVon=new Date(DatVon.getTime()-iZone*60000);
			if (DatBis != null)
				DatBis=new Date(DatBis.getTime()-iZone*60000);
		}
		//System.out.println("VonBis: VecMass0="+VecMass.elementAt(0)+"/"+Static.className(VecMass.elementAt(0)));
		//System.out.println("VonBis: VecMass1="+VecMass.elementAt(1)+"/"+Static.className(VecMass.elementAt(1)));
		sFormat=rsFormat;
		iStellen=riStellen;
		iStamm=riStamm;
		Vector<Object> VecMass=g.getMass(iStamm,bKE);
		dFaktor=VecMass.elementAt(0)==null?0.0:((Double)VecMass.elementAt(0)).doubleValue();
		sKZ=bKE || VecMass.elementAt(1)==null ? null:(String)VecMass.elementAt(1);
		//bDauer=dFaktor!=0.0;
		dDauer=dFaktor!=0.0?rdDauer/dFaktor:0.0;
		Count.add(Count.VonBis);
	}

        public Vector<Object> getVec()
        {
          Vector<Object> Vec=new Vector<Object>();
          Vec.addElement(new Double(dFaktor));
          Vec.addElement(sKZ);
          Vec.addElement(sFormat);
          Vec.addElement(new Integer(iStellen));
          Vec.addElement(iStamm);
          return Vec;
        }
        
    public VonBis(Global rg,Object ObjVon,Object ObjBis,double rdDauer,String rsFormat)
	{
    	this(rg,ObjVon,ObjBis,rdDauer,rsFormat,3600.0);
	}
	
	public VonBis(Global rg,Object ObjVon,Object ObjBis,String rsFormat)
	{
		this(rg,ObjVon,ObjBis,0.0,rsFormat,0.0);
	}

	public VonBis(VonBis vb)
	{
		dDauer=vb.dDauer;
		dFaktor=vb.dFaktor;
		sFormat=vb.sFormat;
		iStellen=vb.iStellen;
		sKZ=vb.sKZ;
		Count.add(Count.VonBis);
	}

	public void finalize()
	{
		Count.sub(Count.VonBis);
	}

	public double getValue()
	{
		return(bDate && DatVon != null ?DatVon.getTime():0);
	}

        public void setValue(double d)
        {
          dDauer=d;
        }

	public boolean isNull()
	{
		return(DatVon==null && DatBis==null && dDauer==0.0);
	}

        public void setNull()
        {
          bNull=true;
        }

	public String toString()
	{
		if(isNull() || bNull)
		  return bNull || dFaktor==0 ? "" :DauerString();
		String sDate;
		//System.out.println("VonBis1:"+bDate+"/"+sFormat+"/"+DatVon+"-"+DatBis);
		if(!sFormat.equals("-") && bDate)
		{
		  boolean bBis=sFormat.indexOf("-")>0;
                  if (DatVon != null && DatBis != null && sdf0.format(DatVon).equals("0000") && sdf0.format(DatBis).equals("0000"))
                  {
                    //DatBis.setTime(DatBis.getTime()-1);
                    int iIndex=sFormat.indexOf(" HH");
                    SimpleDateFormat sdf = new SimpleDateFormat(iIndex>0 ?sFormat.substring(0,iIndex):sFormat);
                    String sVon=sdf.format(DatVon);
                    String sBis=(bBis ? new SimpleDateFormat(sFormat.substring(0,sFormat.indexOf("-"))):sdf).format(new Date(DatBis.getTime()-1));
                    sDate=sVon+(!bBis && sBis.equals(sVon)?"":(bBis?"":"-")+sBis);
                  }
                  else
                  {
                    SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
                    //System.out.println("VonBis2:"+bBis+"/"+sFormat+"/"+DatVon+"-"+DatBis);
                    if (DatVon == null || DatBis == null || bBis || !new SimpleDateFormat("yyyy/MM/dd").format(DatVon).equals(new SimpleDateFormat("yyyy/MM/dd").format(DatBis)))
                    {
                      //sDate=sdf.format(DatVon);
                      //System.out.println(sDate);
                      sDate = (DatVon == null ? "" : sdf.format(DatVon)) + (bBis?"":"-") + (DatBis == null ? "" : (bBis ? new SimpleDateFormat(sFormat.substring(0,sFormat.indexOf("-"))): sdf).format(DatBis));
                      //System.out.println(sDate);
                    }
                    else
                    {
                      sDate = sdf.format(DatVon) + "-";
                      if (sFormat.indexOf('s') != -1)
                        sdf = new SimpleDateFormat("HH:mm:ss");
                      else
                        sdf = new SimpleDateFormat("HH:mm");
                      sDate += sdf.format(DatBis);
                    }
                  }
                  if (dFaktor != 0.0)
                  {
                    String s=Static.rightString(DauerString(), 4 + (g.bH_min || iStellen < 0 ? 2 : iStellen + (sKZ == null ? 0 : 2)));
                    sDate += (s.charAt(0)==' '?"":" ")+s;
                  }
		}
		else
			if(dFaktor!=0.0)
				sDate = DauerString();
			else
				sDate = "";

		return(sDate);
	}

	private String DauerString()
	{
          if (g.bH_dez && iStellen<0)
            iStellen=2;
		if (g.bH_min && iStamm==g.iAicStunde || iStellen<0)
		{
			//int iMin=(int)Math.round(Math.abs(getSeconds())/60.0);
			//return (dDauer<0?"-":"")+(iMin/60)+":"+Static.VorNull(iMin%60);
			return Static.hm(getSeconds());
		}
		else
		{
			java.text.DecimalFormat df=new java.text.DecimalFormat("");
			df.setMinimumIntegerDigits(1);
			df.setMinimumFractionDigits(iStellen);
			df.setMaximumFractionDigits(iStellen);
			return df.format(iAnz==1 ? dDauer:dDauer/iAnz)+(sKZ==null?"":" "+sKZ);
		}
	}

	public boolean equals(Object Objequal)
	{
		boolean bEquals = true;
		if(Objequal instanceof VonBis)
		{
			VonBis vb=(VonBis)Objequal;
			bEquals = bDate==vb.bDate;

			if(bDate)
				bEquals = Static.Gleich(vb.getVon(),DatVon) && Static.Gleich(vb.getBis(),DatBis);

			if(dFaktor!=0.0)
				bEquals = bEquals && vb.getDauer()==dDauer;
		}
		else
			bEquals=false;

		return(bEquals);
	}

	public java.sql.Timestamp getVon()
	{
		return(DatVon!=null?new java.sql.Timestamp(DatVon.getTime()):null);
	}

	public java.sql.Timestamp getBis()
	{
		return(DatBis!=null?new java.sql.Timestamp(DatBis.getTime()):null);
	}

        public void setVon(java.sql.Timestamp dtVon)
        {
          DatVon=dtVon;
          bDate=DatVon != null;
        }

        public void setBis(java.sql.Timestamp dtBis)
        {
          DatBis=dtBis;
          //bDate=DatVon != null;
        }

	public double getDauer()
	{
		//return(DatVon!=null && DatBis!=null?(DatBis.getTime()-DatVon.getTime())/3600000.0:0.0);
		return dDauer;
	}
	
	public double getDauer(DateWOD dtVon,DateWOD dtBis)
	{
		Timestamp dtV=dtVon.toTimestamp();
		Timestamp dtB=dtBis.toTimestamp();
		if (dtV.after(DatBis) || dtB.before(DatVon))
			return 0;
		if (dtV.before(DatVon) && dtB.after(DatBis))
			return getSeconds();
		Date dtV2=(Date)DatVon.clone();
		Date dtB2=(Date)DatBis.clone();
		if (dtV2.before(dtV))
			dtV2=dtV;
		if (dtB2.after(dtB))
			dtB2=dtB;
		return (dtB2.getTime()-dtV2.getTime())/1000;
	}
	
	@Override public int compareTo(VonBis zahl)
	{
		return dDauer<zahl.getDauer() ? -1:dDauer>zahl.getDauer() ? +1:0;
	}


	public void add(double d)
	{
		dDauer+=d/dFaktor;
		iAnz++;
	}

	public double getSeconds()
	{
	  if (dFaktor==0 && dDauer==0 && DatVon!=null && DatBis!=null)
	    return (DatBis.getTime()-DatVon.getTime())/1000;
	  else
	    return iAnz==1 ? dDauer*dFaktor:dDauer*dFaktor/iAnz;
	}
	
	public boolean hasFaktor()
	{
		return dFaktor!=0;
	}

	private Global g=null;
	private	boolean bDate=false;
	private Date DatVon=null;
	private Date DatBis=null;
	private double dDauer=0;
	private double dFaktor;
	private String sFormat="";
	private String sKZ=null;
	private int iStellen=2;
	private int iStamm=0;
	private int iAnz=1;
    private boolean bNull=false;
    private static SimpleDateFormat sdf0=new SimpleDateFormat("HHmm");
}

