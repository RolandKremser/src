/*
    All_Unlimited-Allgemein-Anzeige-JaNein.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

// add your custom import statements here
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.Static;

public class JaNein extends Object
{
    public JaNein(Boolean rbJN)
	{
		bJN=rbJN;
		Count.add(Count.JaNein);
	}

	public JaNein(boolean rbJN)
	{
		//bJN=new Boolean(rbJN);
                bJN=rbJN ? Boolean.TRUE:Boolean.FALSE;
		Count.add(Count.JaNein);
	}

	public void finalize()
	{
		Count.sub(Count.JaNein);
	}

	public boolean isNull()
	{
		return(bJN==null);
	}

	public String toString()
	{
		return(bJN!=null?Static.JaNein2(bJN.booleanValue()):Static.sKein);
	}

	public Boolean getValue()
	{
		return(bJN);
	}

        public int intValue()
        {
          return bJN==Boolean.TRUE ? 1:0;
        }

	public boolean equals(Boolean b)
	{
		return Static.Gleich(bJN,b);
	}

	Boolean bJN=null;
	// add your data members here
}

