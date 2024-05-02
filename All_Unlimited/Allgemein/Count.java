/*
    All_Unlimited-Allgemein-Count.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here

public class Count extends java.lang.Object
{
    // add your data members here
	public static final int Combo=1;
	public static final int JaNein=2;
	public static final int Mass=3;
	public static final int Memo1=4;
	public static final int VonBis=5;
	public static final int Waehrung=6;
	public static final int Zahl1=7;
	public static final int Zeit=8;
        public static final int Html=9;
	public static final int Tabellenspeicher=10;
	public static final int SQL=11;
	public static final int Text=12;
	public static final int Zahl=13;
	public static final int ComboSort=14;
	public static final int DateWOD=15;
	public static final int TabLeer=16;
	public static final int Formular=17;
	public static final int Datum=18;

	public static final int Abfrage=20;
	public static final int Calc=21;
	public static final int EingabeFormular=22;
	public static final int AbfrageLaden=23;
	public static final int Hauptschirm=24;
	public static final int Message=25;
	public static final int Aufruf=26;
	public static final int Diagramm=27;
	
	public static final int FxCbx=30;
	public static final int FxCho=31;
	public static final int FxCbo=32;
	public static final int FxDate=33;
	public static final int FxDatum=34;
	public static final int FxText=35;

	private static int[] iAnz = new int[36];
	private static int[] iAnz2 = new int[36];

	public static void add(int i)
	{
		iAnz[i]++;
	}

	public static void sub(int i)
	{
		iAnz2[i]++;
	}
	
	public static int get(int i)
	{
		return iAnz[i]-iAnz2[i];
	}

	private static String rightString(String s,int iAnz)
	{
		for (int i=s.length();i<iAnz;i++)
			s=' '+s;
		return s;
	}

	public static String print(String s,int i)
	{
		String str = rightString(s,20)+":"+rightString(new java.text.DecimalFormat("#,##0").format(iAnz[i]),5)
			+"-"+rightString(new java.text.DecimalFormat("#,##0").format(iAnz2[i]),5)
			+"="+rightString(new java.text.DecimalFormat("#,##0").format(iAnz[i]-iAnz2[i]),5);
		//System.out.println(str);
		return str+"\n";
	}

	public static String print()
	{
		String s = "";
		System.gc();
		s=s+print("Combo",Combo);
		s=s+print("JaNein ",JaNein);
		s=s+print("Mass ",Mass);
		s=s+print("Memo1 ",Memo1);
		s=s+print("VonBis ",VonBis);
		s=s+print("Waehrung ",Waehrung);
		s=s+print("Zahl1 ",Zahl1);
		s=s+print("Zeit ",Zeit);
                s=s+print("Html ",Html);
		s=s+print("TabLeer",TabLeer);
		s=s+print("Tabellenspeicher ",Tabellenspeicher);
		s=s+print("SQL ",SQL);
		s=s+print("Text ",Text);
		s=s+print("Zahl ",Zahl);
		s=s+print("ComboSort ",ComboSort);
		s=s+print("DateWOD ",DateWOD);
		s=s+print("Datum ",Datum);
		s=s+print("Abfrage",Abfrage);
		s=s+print("Abfrage laden ",AbfrageLaden);
		s=s+print("Aufruf ",Aufruf);
		s=s+print("Calc ",Calc);
		s=s+print("Formular ",Formular);
		s=s+print("EingabeFormular ",EingabeFormular);
		s=s+print("Hauptschirm ",Hauptschirm);
		s=s+print("Message ",Message);
		s=s+print("Diagramm ",Diagramm);
		s+=" ---   Java-FX   ---\n";
		s+=print("FxCbx ",FxCbx);
		s+=print("FxCho ",FxCho);
		s+=print("FxCbo ",FxCbo);
		return s;
	}
}

