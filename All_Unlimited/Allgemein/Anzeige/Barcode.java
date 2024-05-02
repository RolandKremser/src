package All_Unlimited.Allgemein.Anzeige;

public class Barcode {
	
	private String sBC;
	public Barcode(String s)
	{
		sBC=s;
	}
	
//	private String getBC()
//	{
//		return sBC;
//	}
	
	public String toString()
	{
		return "*"+sBC+"*";
	}
	
	public boolean equals(Object obj)
	{
		if ((obj != null) && (obj instanceof Barcode))
			return ((Barcode)obj).sBC.equals(sBC);
		else
			return false;
	}

}
