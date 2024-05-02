

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Sort;
//import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Static;/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class ViewsRebuild
{
  public static void main(String[] args)
  {
    //ViewRebuild viewrebuild = new ViewRebuild();
    Global g=new Global();
    g.connect(args[0]);
    int iNr=0;
    int iBis=0;
    if (args.length>1)
    	iNr=Sort.geti(args[1]);
    if (args.length>2)
    	iBis=Sort.geti(args[2]);
	if (iNr<0)
	{
		iNr=-iNr;
		boolean bExists=true;
		if (g.MySQL())
		  while (bExists || iNr<iBis)
   	 	  {
			bExists=g.exists("select specific_name from information_schema.routines where routine_schema=DATABASE() and specific_name=?","bis"+iNr);
		  	if (bExists)
		  	{
   		   		g.exec("drop function von"+iNr);
   		   		g.exec("drop function bis"+iNr);
   		   		g.fixInfo("Function "+iNr+" entfernt");
		  	}
		  	iNr++;
		  }
	}
    else if (iNr<6)
    {
	    g.bISQL = true;
	    g.exec("drop table poolview5");
	    g.exec("drop table poolview4");
	    g.exec("drop table poolview2");
	    g.exec("drop table poolview");
	    g.exec("drop table bewview3");
	    g.exec("drop table bewview2");
	    g.exec("drop table bewview");
	    g.exec("drop table stammview");
	    g.exec("drop table stammview2");
	    g.exec("drop table befehl2");
	    g.exec("drop table zr");
	    g.bISQL = false;
	    g.fixInfo(Version.ViewRebuild(g,Version.getVer())+" Views erneuert");
    }
    else
    {
     boolean bExists=true;
	 String sSub=g.getDB();
   	 while (bExists || iNr<iBis)
   	 {
		if (g.MySQL())
   	   		bExists=g.exists("select table_name from information_schema.views where table_schema=database() and table_name in ('stammview"+iNr+"','bewview"+iNr+"')",null);
		else if (g.MS())
			bExists=g.exists("select name,crdate from sysobjects where xtype='V ' and name in ('stammview"+iNr+"','bewview"+iNr+"')",null);
   	   	if (bExists)
   	   	{
   		   g.exec("drop view stammview"+iNr);
   		   g.exec("drop view bewview"+iNr);
   		   g.exec("drop view bewview"+iNr+"d");
   		   g.exec("drop view poolview"+iNr);
		   if (g.MySQL())
		   {
   		   	g.exec("drop function von"+iNr+sSub);
   		   	g.exec("drop function bis"+iNr+sSub);
   		   	g.fixInfo("View/Function "+iNr+" entfernt");
		   }
		   else
		   	g.fixInfo("View "+iNr+" entfernt");
   	   	}
	   	iNr++;
   	 }
    }
    Static.sleep(10000);
    System.exit(0);
  }
}
