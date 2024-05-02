package All_Unlimited.Allgemein.Anzeige;

public class Farbe extends Number implements Comparable<Farbe> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7216198718621571851L;
	private int iFarbe=0;
	
	public Farbe(int i)
	{
		iFarbe=i;
	}
	
	@Override public int compareTo(Farbe farbe)
	{
		return intValue()<farbe.intValue() ? -1:intValue()>farbe.intValue() ? +1:0;
	}

	@Override
	public int intValue() {
		return iFarbe;
	}

	@Override
	public long longValue() {
		return iFarbe;
	}

	@Override
	public float floatValue() {
		return iFarbe;
	}

	@Override
	public double doubleValue() {
		return iFarbe;
	}
	
	public String toString()
	{
		String s=Integer.toHexString(iFarbe).toUpperCase();
		if (s.length()==8)
			s=s.substring(2);
		while (s.length()<6)
			s="00"+s;
		return "#"+s;
	}

}
