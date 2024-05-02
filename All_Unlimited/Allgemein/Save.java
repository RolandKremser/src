/*
    All_Unlimited-Allgemein-Save.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class Save
{
  private static boolean bSave=true;
  private static Vector<String> VecFile=new Vector<String>();

	public static String now()
	{
		return new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(new java.util.Date());
	}

        public static boolean prot(File fil,String sZeile)
        {
          return prot(null,fil,sZeile,false);
        }

	public static boolean prot(String sTitel,File fil,String sZeile,boolean bWeiter)
	{
          String sFile=fil.getName();
          try
          {
            if (bSave && !VecFile.contains(sFile))
            {
              boolean bTitel = sTitel != null && !fil.exists();
              FileOutputStream filOpenSave = new FileOutputStream(fil.getAbsolutePath(), true);
              if (bTitel)
                filOpenSave.write(("date and time   \t" + sTitel + "\r\n").getBytes());
              filOpenSave.write((now() + "\t" + sZeile + "\r\n").getBytes());
              filOpenSave.close();
            }
            return true;
          }
          catch(IOException ioe){
            System.err.println("Save.prot(): IOException - "+ioe);
            if (!bWeiter)
            {
              //bSave = bWeiter;
              VecFile.addElement(sFile);
              System.err.println("! Protokollieren in "+sFile+" abgeschaltet !");
            }
            return false;
          }
	}

}

