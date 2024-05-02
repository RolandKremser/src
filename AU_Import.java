/*
    AU_Import.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/
// add your custom import statements here
import All_Unlimited.Allgemein.*;
import All_Unlimited.Grunddefinition.DefImport;
//import java.util.Vector;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
//import All_Unlimited.Allgemein.Transact;

public class AU_Import
{
  public static void prot(String sTitel,File fil,String sZeile)
        {
                try
                {
                  boolean bTitel=sTitel != null && !fil.exists();
                  FileOutputStream filOpenSave = new FileOutputStream(fil.getAbsolutePath(),true);
                  if (bTitel)
                    filOpenSave.write((sTitel+"\r\n").getBytes());
                  filOpenSave.write((sZeile+"\r\n").getBytes());
                  filOpenSave.close();
                }
                catch(IOException ioe){System.err.println("Save.prot(): IOException - "+ioe);}
        }


    public static void main( String[] args )
    {

		//Transact t=new Transact();
                if (args.length<3)
                {
                  System.out.println("Parameter: Datenbank File Verzeichnis");
                  System.exit(1);
                }
                //t.connect(args[0]);
        Static.bX11=false;
        Transact.lQryAb=5000;
        long lClock = Static.get_ms();
        Global g=new Global(args[0]);
        Transact.iInfos=Transact.CLOCK;
        g.checkSpracheLand();
        Transact.iInfos=Transact.CLOCK;
        Static.DirError = args[2];
        DefImport.StartPfad(g,args[1],null);
        g.clockInfo("DefImportdauer",lClock);

                g.unConnect();
		g.fixInfo("fertig");
		Static.sleep(30000);

		System.exit(0);
    }
    // add your data members here
}

