package All_Unlimited.Web;

import org.json.JSONArray;

import All_Unlimited.Allgemein.Tabellenspeicher;

public class webLog {

	public static boolean bInfo=false;
	public static boolean bKInfo=false; // in Konsole speichern
	public static boolean bFInfo=true;  // Info in tmp-Files
	public static boolean bHInfo=false; // in Hauptspeicher merken
	public static boolean bDInfo=false; // Debug-Info
	public static boolean bVarInfo=false; // Var-Info
	public static boolean bVBInfo=false; // Von-Bis-Info
	public static boolean bTInfo=false; // Token-Info
	public static boolean bDD=false; // Druck-Debug
	public static int iMsgL=0;
	public static boolean bTest=false;

	public static int newMsgF(String sDB,int iAic,int iStamm)
	{
		return 0;
	}
	
	public static JSONArray TabToJSON(Tabellenspeicher TabAbfrage)
	{
		return null;
	}

}