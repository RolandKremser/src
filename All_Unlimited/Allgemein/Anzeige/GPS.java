package All_Unlimited.Allgemein.Anzeige;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Eingabe.GPS_Eingabe;

public class GPS implements Comparable<GPS> {
	
	private double dLat=0;
	private double dLen=0;
	private String sName=null;
	private int iDaten=0;
	private int iMap=17;
	private boolean bNull=true;
	public static String sGoogleKey=null;
//	private boolean bFehler=false; 
	
	public GPS(Tabellenspeicher Tab,String sAic)
	{
		if (!Tab.isNull("B"+sAic.substring(1)))
		{
			dLat= Tab.getF("B"+sAic.substring(1));
			dLen= Tab.getF("L"+sAic.substring(1));
		    sName=Tab.getS(sAic);
		    iDaten=Tab.getI("D"+sAic.substring(1));
		    bNull=false;
		}
	}
	
	public GPS(String s)
	{
		s=s.substring(1,s.length()-1);
//		System.out.println("GPS-Übergabe:"+s);
		String[] sAry=s.split(":");
//		System.out.println("GPS="+sAry[0]+"/"+sAry.length);
		if (sAry.length>1)
		{
			dLat=Sort.getf(sAry[0]);
			dLen=Sort.getf(sAry[1]);
			if (sAry.length>2)
				sName=sAry[2];
			bNull=dLat ==0 && dLen ==0;
//			System.out.println("Lat="+dLat+", Lng="+dLen+", bNull="+bNull);
		}
	}
	
	public GPS(GPS_Eingabe Edt)
	{
		dLat=Edt.getLat();
		dLen=Edt.getLng();
		sName=Edt.getName();
		iMap=Edt.getMap();
		bNull=dLat ==0 && dLen ==0;
	}
	
	public static GPS getGPS(Global g,Stack<String> Sta,boolean bLet)
	{
		if (Sta==null || Sta.size()<2)
			{
				g.fixtestError("getGPS ohne Daten aufgerufen");
				return null;
			}	
		String sStreet=Sta.pop();
		String sCity=Sta.pop();
		String sLand="at";
		int i=sStreet.lastIndexOf(' ');
		if (i<1)
		{
			g.fixtestError("Hausnummer in "+sStreet+" nicht gefunden");
			return null;
		}
		else
		{
			String sSt=Static.toHttpFile(sStreet.substring(0, i));
			String sNr=sStreet.substring(i+1);
			if (sNr.indexOf('/')>0)
				sNr=sNr.substring(0, sNr.indexOf('/'));
			if (!Sta.isEmpty())
				sLand=Sta.pop();
			if (bLet)
			{
				Sta.push(sLand);
				Sta.push(sCity);
				Sta.push(sStreet);
			}
		  try {
    		//URL url = new URL("https://nominatim.openstreetmap.org/search/"+sLand+"/"+Static.toHttpFile(sCity)+"/"+sSt+"/"+sNr+"?format=json");
			URL url = new URL("https://nominatim.openstreetmap.org/search?format=json&country="+sLand+"&city="+Static.toHttpFile(sCity)+"&street="+sSt+"%20"+sNr);
    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod("GET");
    		con.setDoOutput(true);
    		con.setRequestProperty("Content-Type", "application/json");
    		con.setConnectTimeout(1000);
    		con.setReadTimeout(10000);
    		String s=null;
    		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String sIn=in.readLine();
			while (sIn != null) {
				s=s==null ? sIn:s+"\n"+sIn;
				sIn=in.readLine();
			}
			in.close();
			JSONArray j= new JSONArray(s);
			if (j.length()==0)
			{
				g.fixtestError("Adresse "+sLand+"/"+sCity+"/"+sStreet+" nicht gefunden");
//				bFehler=true;
				return null;
			}
			JSONObject json=j.getJSONObject(0);
			String sLat=json.getString("lat");
			String sLen=json.getString("lon");
			String sName=json.getString("display_name");
//			g.fixtestError("GPS von "+sName+": "+sLat+","+sLen);
			return new GPS("["+sLat+":"+sLen+":"+sName+"]");
		  } catch (Exception e) {
			g.printError("getGPS "+sLand+"/"+sCity+"/"+sStreet+" geht nicht:"+e);
			return null;
		  }
		}
	}
	
	public double getLat()
	{
		return dLat;
	}
	
	public double getLng()
	{
		return dLen;
	}
	
	public String getName()
	{
		return bNull ? "":Static.Leer(sName) ? new Zahl1(dLat,"0.0000")+"/"+new Zahl1(dLen,"0.0000"):sName;
	}
	
	public int getDaten()
	{
		return iDaten;
	}
	
	public int getMap()
	{
		return iMap;//17;
	}
	
	public boolean isNull()
	{
		return bNull;
	}
	
	public String toString()
	{
		return bNull ? "":Static.Leer(sName) || Version.Test() ? new Zahl1(dLat,"0.0000")+"/"+new Zahl1(dLen,"0.0000"):sName;
	}
	
	public String getURL()
	{
		return bNull ? "":"https://www.openstreetmap.org/#map="+iMap+"/"+dLat+"/"+dLen;
	}
	
	public double distanceTo(GPS gps)
	{
		if (bNull || gps.bNull)
			return -1;
		double dx= 71.5*(dLen-gps.dLen);
		double dy=111.3*(dLat-gps.dLat);
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public Vector<Integer> RouteTo(Global g,GPS gps)
	{
		if (Static.Leer(sGoogleKey))
		{
			g.printError("Google-Key noch nicht definiert");
			return null;
		}
		try {
    		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?mode=driving&language=de&origins="+getLat()+","+getLng()+"&destinations="+gps.getLat()+","+gps.getLng()+"&key="+sGoogleKey);
    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod("GET");
    		con.setDoOutput(true);
    		con.setRequestProperty("Content-Type", "application/json");
    		con.setConnectTimeout(1000);
    		con.setReadTimeout(10000);
    		String s=null;
    		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String sIn=in.readLine();
			while (sIn != null) {
				s=s==null ? sIn:s+"\n"+sIn;
				sIn=in.readLine();
			}
			in.close();
			JSONObject json= new JSONObject(s);
//			g.fixtestError("RouteTo von "+json.getJSONArray("origin_addresses")+" nach "+json.getJSONArray("destination_addresses"));
			JSONObject json2=json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0);
			Vector<Integer> Vec=new Vector<Integer>();
			Vec.add(json2.getJSONObject("distance").getInt("value"));
			Vec.add(json2.getJSONObject("duration").getInt("value"));
//			g.fixtestError("-> Vec="+Vec);
			return Vec;
			
		} catch (Exception e) {
			g.printError("RouteTo von "+toString()+" nach "+gps.toString()+" geht nicht:"+e);
			return null;
		  }
	}
	
	public void showRoute(GPS gps)
	{
		if (gps==null || bNull)
			;
		else
		{
			String s="https://classic-maps.openrouteservice.org/directions?n1=47&n2=16&n3=12&a="+getLat()+","+getLng()+","+gps.getLat()+","+gps.getLng()+"&b=0&c=0&k1=de&k2=km";
			Global.fixInfoS(s);
			Static.OpenURL(s);
		}
	}
	
	@Override public int compareTo(GPS gps)
	{
		return dLen<gps.dLen ? -1:dLen>gps.dLen ? +1:0;
	}

}
