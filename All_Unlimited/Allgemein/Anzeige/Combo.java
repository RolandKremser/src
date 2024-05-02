/*
    All_Unlimited-Allgemein-Anzeige-Combo.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

// add your custom import statements here
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.Static;

import java.awt.Color;
import java.io.Serializable;

public class Combo extends Object implements Serializable,Comparable<Combo>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// add your data members here
	private String name;
	private int key;
	private String kennung;
	private double faktor;
        private Color Col;
        public boolean use=true;
	public static final int kein=0;
	public static final int Bezeichnung=1;
	public static final int Kennung=2;
	public static final int Aic=3;
	public static Combo CboKein=new Combo(Static.sKein,0,false);

	public Combo(String rsName,int riKey,String rsKennung,double dFaktor)
	{
		name=rsName;
		key=riKey;
		kennung=rsKennung;
		faktor=dFaktor;
                //if (key%2==0)
                //  Col=Color.RED;
                Count.add(Count.Combo);
	}

        public Combo(String rsName,int riKey,String rsKennung,double dFaktor,Color rCol)
        {
                name=rsName;
                key=riKey;
                kennung=rsKennung;
                faktor=dFaktor;
                Col=rCol;
                Count.add(Count.Combo);
        }

        public Combo(String rsName,int riKey,boolean rbUse)
        {
          name=rsName;
          key=riKey;
          kennung="";
          faktor=1;
          use=rbUse;
          Count.add(Count.Combo);
        }

        public boolean change(String rsName,int riKey,String rsKennung,double dFaktor)
        {
          boolean b=!name.equals(rsName);
          name=rsName;
          key=riKey;
          kennung=rsKennung;
          faktor=dFaktor;
          return b;
        }

	public void finalize()
	{
		Count.sub(Count.Combo);

	}
	
	public String print()
	{
		return "("+name+","+kennung+","+key+")";
	}

	public String toString()
	{
		return Static.beiLeer(name, kennung);// /*(key%2==0 ? "x ":"+ ")+*/(name.equals("")?kennung:name);
	}

	public boolean isKein()
	{
		return(key==0);
	}

	public Object getValue(int iSort)
	{
		if(iSort==kein)
			return(null);
		else if(iSort==Bezeichnung)
			return toString();
		else if(iSort==Kennung)
			return(kennung);
		else if(iSort==Aic)
			return(new Integer(key));
		else
			return(null);

	}

        public Color getColor()
        {
          return Col==null ? Color.BLACK:Col;
        }

	public String getKennung()
	{
		return(kennung);
	}

	public int getAic()
	{
		return(key);
	}

	public String getBezeichnung()
	{
		return toString();
	}

	public double getFaktor()
	{
		return faktor;
	}
	
	public int compareTo(Combo C)
	{
		return toString().compareToIgnoreCase(C.toString());
	}
	
	public boolean equals(Object o)
	{
		boolean b=o instanceof Combo && ((Combo)o).key==key && ((Combo)o).toString().equals(toString());
		//System.err.println("Combo.equals:"+this+"/"+o+"->"+b);
		return b;
	}

}

