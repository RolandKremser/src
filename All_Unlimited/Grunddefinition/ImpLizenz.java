/*
    All_Unlimited-Grunddefinition-ImpLizenz.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.util.zip.ZipEntry;
import java.util.Vector;

import javax.swing.JDialog;

import All_Unlimited.Allgemein.AUZip;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;

public class ImpLizenz extends java.lang.Object
{
public ImpLizenz(String rsFilename,Global glob,JDialog rFrmOption)
{
	g=glob;
	sFilename=rsFilename;
	if (!Message.SecCheck(g,rFrmOption,"DefImport-Check","FS7615"))
    	return;
	Einspielen();
	g.printInfo("Fertig importiert!!!");
}

private void Einspielen()
{
	if(new java.io.File(sFilename).exists())
	{
                Vector<Integer> VecMandanten=null;
		SQL Qry = new SQL(g);
		AUZip Zip = new AUZip(sFilename,true);
                boolean bOk=false;
		ZipEntry ze=Zip.getNextEntry();
		int iVersion = ze.getName().equals("Version.up")?Zip.ReadInteger().intValue():0;
		int iExpVersion = ze.getName().equals("Version.up")?Zip.ReadInteger().intValue():0;
		if(iVersion<5120/*!=Version.getVer()*/ || iExpVersion!=Lizenz.cstVersion)
			new Message(Message.ERROR_MESSAGE,null,g).showDialog("FalscheVersion");
		else
		{
			for(ze=Zip.getNextEntry();ze!=null && Zip.available()!=0;ze=Zip.getNextEntry())
			{
				String sFile = ze.getName();

				if(sFile.equals("Mandant.liz"))
				{
					g.printInfo("Mandant");
                                        VecMandanten=g.getMandanten(Zip.ReadString2());
                                        if (VecMandanten.size()==0)
                                        {
                                          new Message(Message.ERROR_MESSAGE,null,g).showDialog("Mandant_falsch");
                                          return;
                                        }
                                        bOk=true;
					int iVerbindungen=Zip.ReadInteger().intValue();
                                        if (iVerbindungen !=0)
                                          Qry.exec("update mandant set Verbindungen="+iVerbindungen+" where"+g.in("aic_mandant",VecMandanten));
					//Qry.exec("DELETE FROM Lizenz WHERE"+g.in("aic_mandant",VecMandanten));
				}
				else if(bOk && sFile.equals("Begriff.liz"))
				{
					g.fixInfo("Modul-Lizenz importieren");
                                        //int iAIC_Tabellenname = g.TabTabellenname.getAic("BEGRIFF");
                                        Qry.exec("DELETE FROM Lizenz WHERE aic_tabellenname="+Global.iTabBegriff+" and"+g.in("aic_mandant",VecMandanten));
					for(String sKennung=Zip.ReadString2();sKennung!=null;sKennung=Zip.ReadString2())
					{
                                          String sProg=Zip.ReadString2();
						int iAIC_Fremd = g.getModul(sKennung,sProg);
						if(iAIC_Fremd>0)
                                                  for (int i=0;i<VecMandanten.size();i++)
                                                  {
							Qry.add("aic_mandant",Sort.geti(VecMandanten,i));
							Qry.add("aic_tabellenname",Global.iTabBegriff);
							Qry.add("aic_fremd",iAIC_Fremd);
							Qry.insert("Lizenz",false);
                                                  }
					}
				}
				else if(bOk && sFile.equals("Stammtyp.liz"))
				{
					g.fixInfo("Stammtyp-Lizenz importieren");
					int iAIC_Tabellenname = g.TabTabellenname.getAic("STAMMTYP");
                                        Qry.exec("DELETE FROM Lizenz WHERE aic_tabellenname="+iAIC_Tabellenname+" and"+g.in("aic_mandant",VecMandanten));
                                        for(String sKennung=Zip.ReadString2();sKennung!=null;sKennung=Zip.ReadString2())
					{
						int iAIC_Fremd = g.TabStammtypen.getAic(sKennung.toUpperCase());
                                                int iAnzahl=Zip.ReadInteger();
						if(iAIC_Fremd>0)
                                                  for (int i=0;i<VecMandanten.size();i++)
                                                  {
							Qry.add("aic_mandant",Sort.geti(VecMandanten,i));
							Qry.add("aic_tabellenname",iAIC_Tabellenname);
							Qry.add("aic_fremd",iAIC_Fremd);
							Qry.add("anzahl",iAnzahl);
							Qry.insert("Lizenz",false);
                                                  }
					}
				}
                                else if(bOk && sFile.equals("Rolle.liz"))
                                {
                                        g.fixInfo("Rollen-Lizenz importieren");
                                        int iAIC_Tabellenname = g.TabTabellenname.getAic("ROLLE");
                                        Qry.exec("DELETE FROM Lizenz WHERE aic_tabellenname="+iAIC_Tabellenname+" and"+g.in("aic_mandant",VecMandanten));
					for(String sKennung=Zip.ReadString2();sKennung!=null;sKennung=Zip.ReadString2())
                                        {
                                                int iAIC_Fremd = g.TabRollen.getAic(sKennung.toUpperCase());
                                                int iAnzahl=Zip.ReadInteger();
                                                if(iAIC_Fremd>0)
                                                  for (int i=0;i<VecMandanten.size();i++)
                                                  {
                                                        Qry.add("aic_mandant",Sort.geti(VecMandanten,i));
                                                        Qry.add("aic_tabellenname",iAIC_Tabellenname);
                                                        Qry.add("aic_fremd",iAIC_Fremd);
                                                        Qry.add("anzahl",iAnzahl);
                                                        Qry.insert("Lizenz",false);
                                                  }
                                        }
                                }
			}
			new Message(Message.INFORMATION_MESSAGE,null,g).showDialog("Import fertig");
		}
		Zip.close();
	}
	else
		new Message(Message.WARNING_MESSAGE,null,g).showDialog("File not found");
}

	// add your data members here
private Global g;
private String sFilename;
//private int iAIC_Mandant;
}

