package All_Unlimited.Web;

//import java.awt.Image;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

//import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Global;
//import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
//import All_Unlimited.Allgemein.Anzeige.AddUp;
//import All_Unlimited.Allgemein.Anzeige.Combo;
//import All_Unlimited.Allgemein.Anzeige.Farbe;
//import All_Unlimited.Allgemein.Anzeige.Mass;
//import All_Unlimited.Allgemein.Anzeige.Memo1;
//import All_Unlimited.Allgemein.Anzeige.PW1;
//import All_Unlimited.Allgemein.Anzeige.VonBis;
//import All_Unlimited.Allgemein.Anzeige.Waehrung;
//import All_Unlimited.Allgemein.Anzeige.Zeit;

public class webJSON {
	
	
	
	public static JSONArray VecToJSON(Vector<Object> Vec)
	{
		if (Vec==null)
			return null;
		JSONArray jA=new JSONArray();
		for (int i=0;i<Vec.size();i++)
			jA.put(Vec.elementAt(i));
		return jA;
	}
	
	public static JSONArray toArray(String s)
	{
		if (s==null)
			return null;
		JSONArray jA=new JSONArray();
		String[] sSp=s.split("\r\n");
		for (int i=0;i<sSp.length;i++)
			jA.put(sSp[i]);
		return jA;
	}
	
//	private static String addZone(int iZone)
//	{
//		if (iZone==-1)
//			return "";
////		if (iZone==0)
////			return "Z";
//		int iZH=iZone/60;
//		int iZM=Math.abs(iZone%60);
//		String sZM=(iZM==0 ? ":00":":"+iZM);
//		if (iZone==0)
//			return "Z";
//		if (iZH>9)
//			return "+"+iZH+sZM;
//		if (iZH>0)
//			return "+0"+iZH+sZM;
//		if (iZH<-9)
//			return iZH+sZM;
//		if (iZH<0)
//			return "-0"+(-iZH)+sZM;
//		return "";
//	}
	
	public static DateFormatSymbols DFS=new DateFormatSymbols();
	
//	private static String DateToSJON(Date dt,int iZone)
//	{
//		if (dt==null)
//			return null;
//		else
//			return new java.text.SimpleDateFormat("yyyy-MM-dd").format(dt)+"T"+new java.text.SimpleDateFormat(/*iZone==-1 ? "HH:mm:ssXXX":*/"HH:mm:ss").format(dt);//+addZone(iZone);
//	}
	
//	public static Object toJSON(Global g, Object Obj,int iZone,boolean bHHmm)
//	{
//		if (Obj==null)
//			return null;
//		if (Obj instanceof Combo)
//			return ((Combo)Obj).toJSON2(g);
//		if (Obj instanceof Mass)
//			return ((Mass)Obj).toJSON(bHHmm);
//		if (Obj instanceof Waehrung)
//			return ((Waehrung)Obj).toJSON();
//		if (Obj instanceof VonBis)
//			return ((VonBis)Obj).toJSON(bHHmm);
//		if (Obj instanceof Zeit) 
//			return DateToSJON(((Zeit)Obj).getDate(),iZone);
//		if (Obj instanceof Date) 
//			return DateToSJON(((Date)Obj),iZone);
//		if (Obj instanceof Farbe)
//			return ""+Obj;//((Farbe)Obj).intValue();
//		if (Obj instanceof PW1) 
//			return ((PW1)Obj).toJSON();
//		if (Obj instanceof Image)
//			return Static.ImageToString((Image)Obj,"png")+"\" />";
//		if (Obj instanceof Memo1)
//			return ((Memo1)Obj).getValue();
//		if (Obj instanceof AddUp)
//			return ((AddUp)Obj).getValue();
//		return Obj;
//		//return Obj==null ? null:Obj instanceof Combo ? ((Combo)Obj).toJSON2():Obj instanceof Zeit ? DateToSJON(((Zeit)Obj).getDate()):Obj;
//	}
	
	public static String DateToJSON(Date dt)
	{
		if (dt==null)
			return null;
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(dt)+"T"+new java.text.SimpleDateFormat("HH:mm:ss").format(dt); // war mit XXX
	}
	
	public static String DateToJSONZ(Date dt)
	{
		if (dt==null)
			return null;
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(dt)+"T"+new java.text.SimpleDateFormat("HH:mm:ssXXX").format(dt);//+"Z";
	}
	
	public static Timestamp StrToDateF(String s)
	{
		return s==null || s.length()<8 ? null:new Timestamp(new SimpleDateFormat("yyyyMMdd").parse(s,new ParsePosition(0)).getTime());
	}
	
	public static Timestamp StrToDateH(String s)
	{
		try
		{
			if (s!=null && s.length()<10 && s.length()>0)
				Static.printError("webJSON.StrToDateH: "+s+" nicht umwandelbar, da zu kurz!");
			return s==null || s.length()<10 ? null:new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(s,new ParsePosition(0)).getTime());
		}
		catch (Exception e)
		{
			Static.printError("webJSON.StrToDateH mit "+s+":"+e);
			Global.printStackTraceS(e);
		}
		return null;
	}
	
	
	public static Date JSONtoDate(String s)
	{
		if (s==null || s.equals(""))
			return null;
		s=s.replaceAll("T", " ").substring(0, 19);			
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s,new ParsePosition(0));
	}
	
	public static Timestamp getDateTime(JSONObject jE,String sSpalte)
	{
		Timestamp ts=null;
		try
		{
			if (jE.has(sSpalte))
			{
				String s=jE.getString(sSpalte);
				if (s==null)
					return null;
				else if(s.length()==8)
					return StrToDateF(s);
				else if(s.length()==10)
					return StrToDateH(s);
				else
					return new Timestamp(JSONtoDate(s).getTime());
			}
		}
		catch (Exception e) { };
		return ts;
	}
	
	public static Timestamp getDate(JSONObject jE,String sSpalte)
	{
		Timestamp ts=null;
		try
		{
			if (jE.has(sSpalte))
			{
				String s=jE.getString(sSpalte);	
				if (s==null)
					return null;
				else if(s.length()==8)
					return StrToDateF(s);
				else if(s.length()>9)
					return StrToDateH(s.substring(0, 10));
				else
					Static.printError("webJSON.getDate:"+s+" nicht in Datum umwandelbar");
			}
		}
		catch (Exception e) { };
		return ts;
	}
	
	public static boolean getBool(JSONObject jE,String sSpalte)
	{
		boolean b=false;
		try
		{
			if (jE.has(sSpalte))
				b=jE.getBoolean(sSpalte);		
		}
		catch (Exception e) { };
		return b;
	}
	
	public static int getInt(JSONObject jE,String sSpalte)
	{
		int i=0;
		try
		{
			if (jE.has(sSpalte))
				i=jE.getInt(sSpalte);		
		}
		catch (Exception e) { };
		return i;
	}
	
	public static String getString(JSONObject jE,String sSpalte)
	{
		String s=null;
		try
		{
			if (jE.has(sSpalte))
				s=jE.getString(sSpalte);		
		}
		catch (Exception e) { };
		return s;
	}
	
	public static Vector<Integer> getVector(JSONObject jE,String sSpalte)
	{
		try
		{
			JSONArray jA=jE.getJSONArray(sSpalte);
			Vector<Integer> Vec=new Vector<Integer>();
			for (int i=0;i<jA.length();i++)
				Vec.addElement(jA.getInt(i));
			return Vec;
		}
		catch (Exception e) { };
		return null;
	}

}
