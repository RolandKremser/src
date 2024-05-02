/*
    All_Unlimited-Allgemein-Anzeige-HierarchieAnzeige.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;

// add your custom import statements here

public class HierarchieAnzeige extends java.lang.Object
{
	public HierarchieAnzeige(Global g,String rs)
	{
//		s = rs;
		int iPos=rs.indexOf("|");
		sStt=rs.substring(0, iPos);
   	 	s=rs.substring(iPos + 1);
   	 	int iPosStt=g.TabStammtypen.getPos("Kennung",sStt);
   	 	if (iPosStt<0)
   	 	{
   	 		iStt=-1;
   	 		sStt=null;
   	 		iStamm=0;
   	 	}
   	 	else
   	 	{
   	 		iStt=g.TabStammtypen.getI(iPosStt,"Aic");
   	 		sStt=g.TabStammtypen.getS(iPosStt,"Bezeichnung");
   	 		iStamm=SQL.getInteger(g, "select aic_stamm from stammview2 where aic_stammtyp="+iStt+" and bezeichnung='"+s+"'");
   	 	}
//   	 g.fixtestError("HierarchieAnzeige für "+rs+"->"+iStt+"/"+iStamm);
	}
			
	public HierarchieAnzeige(String rs, int riStt, int riStamm)
	{
		s = rs;
		iStt = riStt;
		iStamm = riStamm;
	}
	
	public String toString()
	{
		return sStt==null ? s:sStt+" "+s+" ("+iStamm+")";
	}
	
	public int getValueStt()
	{
		return iStt;
	}
	
	public int getValueStamm()
	{
		return iStamm;
	}
	
	public boolean equals(Object Obj)
	{
		return Obj instanceof HierarchieAnzeige && ((HierarchieAnzeige)Obj).getValueStamm()==iStamm;
	}
	
	// add your data members here
	public String s;
	public int iStt;
	public int iStamm;
	public String sStt=null;
}

