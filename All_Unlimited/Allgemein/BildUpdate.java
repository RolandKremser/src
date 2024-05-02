/*
    All_Unlimited-Allgemein-BildUpdate.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.io.File;
import java.util.Vector;

public class BildUpdate extends java.lang.Object
{
	public BildUpdate(Global glob)
	{
		g=glob;
                int iChange=0;
                int iMiss=0;
		String sTab[] = new String[]{"Daten_Bild"};
		for(int ii=0;ii<sTab.length;ii++)
		{
			g.printInfo("Bildcheck: "+sTab[ii]);
			TabFilesDB = new Tabellenspeicher(g,"SELECT * FROM "+sTab[ii],true);
			Vector<String> VecBilder=new Vector<String>();
			String sVerzeichnis = Static.DirImageSys;
			int iIndex = sVerzeichnis.indexOf("file:");
			if(iIndex!=-1)
				sVerzeichnis = sVerzeichnis.substring(iIndex+5);
                        g.testInfo("BildUpdate: Verzeichnis="+sVerzeichnis);
			String[] s = new File(sVerzeichnis).list();
                        if (s!=null && s.length>0)
                        {
                          g.testInfo("BildUpdate: "+s.length+" Bilder gefunden");
                          for(int i = 0; i < s.length; i++)
                          {
                            VecBilder.addElement(s[i]);
                            if(s[i].indexOf(' ') > -1)
                              VecBilder.addElement(Static.replaceString(s[i], " ", "%20"));
                          }

                          while(!TabFilesDB.eof())
                          {
				int iPos=0;
                                String sFile=TabFilesDB.getS("Filename");
                                if(sFile.indexOf(' ')>-1)
                                {
                                  sFile=sFile.trim();
                                  sFile=Static.replaceString(sFile," ","%20");
                                  sMessages+= TabFilesDB.getS("Filename")+" --> "+sFile+"\n";
                                  SQL Qry = new SQL(g);
                                  Qry.exec("UPDATE "+sTab[ii]+" SET Filename='"+sFile+"' WHERE "+(sTab[ii].equals("Daten_Bild")?
                                      "AIC_Tabellenname="+TabFilesDB.getI("AIC_Tabellenname")+" AND AIC_Fremd="+TabFilesDB.getI("AIC_Fremd")+" AND AIC_Sprache="+TabFilesDB.getI("AIC_Sprache"):
                                      "AIC_Daten="+TabFilesDB.getI("AIC_Daten")));
                                  iChange++;

                                }
				for(;iPos<VecBilder.size() && !sFile.equalsIgnoreCase(VecBilder.elementAt(iPos));iPos++);

				if(iPos<VecBilder.size())
				{
					if(!sFile.equals(VecBilder.elementAt(iPos)))
					{
						//g.debugInfo(TabFilesDB.getS("Filename")+" --> "+((String)VecBilder.elementAt(iPos)));
						sMessages+= TabFilesDB.getS("Filename")+" --> "+(VecBilder.elementAt(iPos))+"\n";
						SQL Qry = new SQL(g);
						Qry.exec("UPDATE "+sTab[ii]+" SET Filename='"+VecBilder.elementAt(iPos)+"' WHERE "+
						(sTab[ii].equals("Daten_Bild")?
						"AIC_Tabellenname="+TabFilesDB.getI("AIC_Tabellenname")+" AND AIC_Fremd="+TabFilesDB.getI("AIC_Fremd")+" AND AIC_Sprache="+TabFilesDB.getI("AIC_Sprache"):
						"AIC_Daten="+TabFilesDB.getI("AIC_Daten")));
                                                iChange++;
					}
				}
				else if (g.ApplPort())
				{
					sMessages+= "File <"+TabFilesDB.getS("Filename")+"> existiert nicht!!!\n";
                                        int iPosT=g.TabTabellenname.getPos("Aic",TabFilesDB.getI("AIC_Tabellenname"));
                                        sMessages+= "benötigt für "+g.TabTabellenname.getS(iPosT,"Bezeichnung")+": "+(g.TabTabellenname.getS(iPosT,"Kennung").equals("BEGRIFF")?SQL.getString(g,"select "+(g.MS()?"g.kennung+' - '+defBezeichnung":"g.kennung||' - '||defBezeichnung")+" from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" where aic_begriff="+TabFilesDB.getI("AIC_Fremd")):
                                        SQL.getString(g,"select bezeichnung from bezeichnung where aic_sprache=1 and aic_fremd="+TabFilesDB.getI("AIC_Fremd")+" and aic_tabellenname="+TabFilesDB.getI("AIC_Tabellenname")))+"\n";
					iMiss++;
                                        //Static.printError("BildUpdate: File <"+TabFilesDB.getS("Filename")+"> existiert nicht!!!");
				}
                                else
                                {
                                  iMiss+=g.exec("delete from daten_bild where filename='"+TabFilesDB.getS("Filename")+"'");
                                }
				TabFilesDB.moveNext();
                          }
                        }
		}
                if (iChange>0)
                  sMessages+= "\n"+iChange+" Bilder wurden geändert\n";
                if (iMiss>0)
                  sMessages+= "\n"+iMiss+(g.ApplPort()?" Bilder fehlen\n":" Bilder-Zuordnungen wurden gelöscht\n");
	}

	public String getString()
	{
		return !sMessages.equals("")?sMessages:"Keine Änderungen";
	}

// add your data members here
private Global g;
private Tabellenspeicher TabFilesDB;
private String sMessages = "";
}

