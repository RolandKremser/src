/*
    All_Unlimited-Allgemein-Anzeige-BewEig.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

import All_Unlimited.Allgemein.Static;

public class BewEig extends java.lang.Object
{
    // add your data members here
	private int iBew;
	private int iEig;
    private int iRolle;
    private String sArt;

	public BewEig(int riBew,int riEig,int riRolle,String rsArt)
	{
		iBew=riBew;
		iEig=riEig;
        iRolle=riRolle;
        sArt=rsArt;
	}

	public String toString()
	{
		return (sArt==null ?"":sArt)+"{"+iBew+'/'+iEig+(iRolle>0?"R"+iRolle:"")+'}';
	}

	public int Bew()
	{
		return iBew;
	}

	public int Eig()
	{
		return iEig;
	}

    public int Rolle()
    {
            return iRolle;
    }
    
    public String Art()
    {
    	return sArt;
    }

	public boolean equals(Object obj)
	{
		if ((obj != null) && (obj instanceof BewEig))
			return Static.Gleich(sArt, ((BewEig)obj).Art()) && iBew == ((BewEig)obj).Bew() && iEig == ((BewEig)obj).Eig() && iRolle == ((BewEig)obj).Rolle();
		return false;
	}
}

