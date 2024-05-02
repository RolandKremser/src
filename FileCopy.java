/**
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

import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy
{

  public static void main(String[] args)
  {
     if (args.length != 2)
     {
       System.out.println("java FileCopy inputfile outputfile");
       System.exit(1);
     }
     try
     {
       long lClock=System.currentTimeMillis();
       //FileInputStream in = new FileInputStream(args[0]);
       FileOutputStream out = new FileOutputStream(args[1]);
       byte[] buf = new byte[4096];
       int len=512;
       for (int i=0;i<256;i++)
       {
         buf[i * 2] = (byte)i;
         buf[i * 2+1] = (byte)i;
       }
       //while ((len = in.read(buf)) > 0)
         out.write(buf, 0, len);
       out.close();
       //in.close();
       System.out.println("FileCopy "+args[1]+":"+(System.currentTimeMillis()-lClock));
     }
     catch (IOException e)
     {
       System.err.println(e.toString());
     }
  }
}
