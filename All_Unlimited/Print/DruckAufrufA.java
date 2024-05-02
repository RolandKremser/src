package All_Unlimited.Print;

// add your custom import statements here
import jclass.bwt.JCOutlinerFolderNode;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Static;

public class DruckAufrufA
{
    // add your data members here
	public DruckAufrufA(Global rg,int riLayout)
	{
          g=rg;
          iLayout=riLayout;
//          g.fixtestError("DruckAufrufA1");
//          g.LoadSchrift();//false,100);
//          g.fixtestError("DruckAufrufA2");
	}


	public Thread druckeDruck(int iDruck,Tabellenspeicher TabStamm,String sPDF_File,String sPDF_PW,JCOutlinerFolderNode Out,int iBits,int iRaster,java.util.Vector Vec,int iSort,int iVB)
	{

		//ddr=new DruckeDruck(g,sTitel,iDruck,iStammtyp,TabStamm,bUmbruch,sKennung,bDrehen,Out,bNurSumme,bKeinDiagramm,bCbxLinien,bSeitenvorschau,bKeinKopfFuss,spm,bQuer,brStammLinks);
		dd=new DruckeDruckA(g,iDruck,TabStamm,sPDF_File,sPDF_PW,Out,iBits,iRaster,iLayout,Vec,iSort,iVB);
		if (bThread)
		{
			Thread thread=new Thread(dd);
	                if (Static.bX11)
	                  g.startThread(thread,null,iDruck);
	        thread.setPriority(Thread.MAX_PRIORITY);
	        thread.setName("Druck "+g.getBegBez(iDruck));
			thread.start();
			return thread;
		}
		else
		{
			dd.bThread=false;
			dd.run();
		}
//		try
//		{
//			thread.join(2000);
//		}
//		catch(Exception e)
//		{
//			g.fixtestError("druckeDruck-Exception:"+e);
//		}
		return null;
	}


	public void druckeAbfrage(String sTitel,String sPDF_File,String sPDF_PW,int iBegriff,int iStammtyp,Tabellenspeicher TabStamm,int iAIC_Raster,int iBits,JCOutlinerFolderNode Out,java.util.Vector Vec)
	{

		//System.out.println("Aufruf.Seitenvorschau:"+bSeitenvorschau);
		//dabf=new DruckeAbfrageA(g,sTitel,iBegriff,iStammtyp,TabStamm,bUmbruch,sKennung,bNurSumme,bCbxLinien,bKeinKopfFuss,bSeitenvorschau,spm,iAIC_Raster,bQuer);
		//dabf=new DruckAbf(g,sTitel,iBegriff,iStammtyp,TabStamm,sKennung,spm,bQuer,iBits);
		dabf=new DruckeAbfrageA(g,sTitel,sPDF_File,sPDF_PW,iBegriff,iStammtyp,TabStamm,iAIC_Raster,iBits,iLayout,Out,Vec);
		Thread thread=new Thread(dabf);
                g.startThread(thread,null,iBegriff);
		thread.start();
	}



    boolean bThread=true;
	Global g=null;
        int iLayout=0;
	DruckeAbfrageA dabf=null;
	DruckeDruckA dd=null;
}

