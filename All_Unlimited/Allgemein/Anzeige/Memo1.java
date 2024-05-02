/*
    All_Unlimited-Allgemein-Anzeige-Memo1.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

import java.io.Serializable;

// add your custom import statements here
import All_Unlimited.Allgemein.Count;

public class Memo1 extends Object implements Serializable
{
	private static final long serialVersionUID = -123458L;
	
	public Memo1(String rsMemo,int iLaenge)
	{
		sMemo=rsMemo;
		if (iLaenge==0)
			iLaenge=60;
		//if (rsMemo.length()>iLaenge)
		//	i=iLaenge;
		if (iLaenge>0)
		{
			i=Math.min(rsMemo.length(),iLaenge);
			int i2=sMemo.indexOf('\n');
			if (i2>-1 && i2<i)
				i=i2;
		}
		else if(iLaenge<-1)
		{
			int i2=sMemo.indexOf('\n');
			//System.out.println(iLaenge+".:"+i2);
			for(int i3=iLaenge;i2>-1 && i3<-1;i3++)
			{
				i2=sMemo.indexOf('\n',i2+1);
				//System.out.println(i3+".:"+i2);
			}
			i=i2>-1 ? i2:-1;
		}
		//System.out.println("Memo1-Länge="+i);
		Count.add(Count.Memo1);
	}

	public Memo1(String rsMemo)
	{
		this(rsMemo,60);
	}

	public void finalize()
	{
		Count.sub(Count.Memo1);
	}

	public boolean isNull()
	{
		return(sMemo==null || sMemo.trim().equals(""));
	}

	public String getValue()
	{
		return(sMemo);
	}

	public String toString()
	{
          if (bMemo)
            return sMemo;
          else
            return(i!=-1 ? sMemo.substring(0,i):sMemo);
	}

	public boolean equals(Object Objequal)
	{
		if(Objequal instanceof String)
                  return ((String)Objequal).equals(sMemo);
                else if (Objequal instanceof Memo1)
                  return ((Memo1)Objequal).getValue().equals(sMemo);
		else
                  return false;
	}

    // add your data members here
	private String sMemo="";
	private int i=-1;
        public static boolean bMemo=false;
}

