import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import All_Unlimited.Allgemein.*;

public class KillAA {
	
	final int iVer=3;
	Tabellenspeicher Tab=null;
	Global g=null;
	javax.swing.Timer timer=null;
	int iKA=60; // kill after 60 min
	int iWD=120;// WatchDog all 120 s
	int iMax=3; // nach 3 versuchen killen, auch wenn ausgeloggt

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KillAA(args);
	}
	
	private KillAA(String[] args)
	{
	  Static.bProtFehler=false;
	  String sJDBC = args[0];
	  g=new Global(sJDBC);
	  Tab=new Tabellenspeicher(g,new String[] {"x","Prozess","Anlage","Anzahl"});
	  Static.DirError = args[2];
	  Static.bSave = true;
	  iKA=Sort.geti(args[3]);
	  iWD=Sort.geti(args[4]);
	  if (args.length>5)
		  iMax=Sort.geti(args[5]);
      Static.bX11=false;
      Tab.readFile(';',args[1],false);
      g.fixInfo("KillAA"+iVer+": "+Tab.size()+" nach "+(iKA/60.0)+" h"+" WD alle "+(iWD/60.0)+" min");
      //Tab.showGrid("Tab");
      timer = new javax.swing.Timer(iWD*1000, new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            WatchDog();
          }
        });
        timer.setDelay(iWD*1000);
        timer.start();
        while(timer.isRunning());
        if (g.isConnected())
            g.unConnect();
          Static.sleep(15000);
	}
	
	private void WatchDog()
	{
		g.fixInfo(Save.now()+" WatchDog");
		Tabellenspeicher Tab2=new Tabellenspeicher(g,"select aic_logging, ein, mom, kennung from logging l join code on l.aic_code=code.aic_code where aus is null",true);
		for (Tab.moveFirst();!Tab.out();Tab.moveNext())
		{
			//g.fixInfo("prüfe "+Tab.getS("Anlage"));
			if (Tab2.posInhalt("kennung", Tab.getS("Anlage")))
			{
				DateWOD dw=new DateWOD(Tab2.getDate("mom"));
				dw.add(Calendar.MINUTE,iKA);
				if (dw.getTime().before(new Date()))
				{
					String sExe=Tab.getS("Prozess");
					g.fixInfo(Save.now()+" kill "+sExe+", da bei log="+Tab2.getI("aic_logging")+"/"+Tab2.getDate("ein")+": mom bei "+Tab2.getDate("mom"));
					try
					{
						Runtime.getRuntime().exec("taskkill /F /IM "+sExe);
						int iAnz=g.exec("update logging set aus="+g.now()+",status="+Transact.ABSTURZ+" where aus is null and aic_logging="+Tab2.getI("aic_logging"));
						if (iAnz>0)
							g.fixInfo(Save.now()+" "+iAnz+" ausgeloggt mit log="+Tab2.getI("aic_logging"));
					}
					catch (Exception e)
					{
						Static.printError("kill-Error bei "+sExe+":"+e);
					}
				}
			}
			else
			{
				Static.printError("Prozess "+Tab.getS("Anlage")+" nicht gefunden");
			   	int iAnz=Tab.getI("Anzahl");
			   	iAnz++;
			   	if (iMax>0 && iAnz>=iMax)
			   	{
			   		String sExe=Tab.getS("Prozess");
			   		g.fixInfo(Save.now()+" kill "+sExe+", da "+iAnz+"x nicht erreichbar");
			   		iAnz=0;
			   		try
					{
			   			Runtime.getRuntime().exec("taskkill /F /IM "+sExe);
					}
					catch (Exception e)
					{
						Static.printError("kill2-Error bei "+sExe+":"+e);
					}
			   	}
			   	Tab.setInhalt("Anzahl", iAnz);
			}
		}
	}

}
