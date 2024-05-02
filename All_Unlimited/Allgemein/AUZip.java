/*
    All_Unlimited-Allgemein-AUZip.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import jclass.bwt.JCOutliner;

public class AUZip extends java.lang.Object
{
public AUZip(String sFilename,boolean rbOpen)
{
	bOpen = rbOpen;
	if(bOpen)
	{
		try
		{
			is = new FileInputStream(sFilename);
			zis = new ZipInputStream(is);
		}
		catch(IOException e){ Static.printError("AUZip: IOException - "+e);}
	}
	else
	{
		try
		{
			os = new FileOutputStream(sFilename);
			zos = new ZipOutputStream(os);
			zos.setLevel(9);
		}
		catch(IOException e){ Static.printError("AUZip: IOException - "+e);}
	}
}

public void close()
{
	try
	{
		if(bOpen)
		{
			zis.close();
			is.close();
		}
		else
		{
			zos.close();
			os.close();
		}
	}
	catch(IOException e){ Static.printError("AUZip: IOException - "+e);}

}

///////////////////
//Speicherbefehle//
///////////////////
        //
		//
		//
	 ///////
	  /////
	   ///
	   //

public void SaveOutliner(JCOutliner Out)
{
	String[] s = Out.getTree();
		for(int i=0;s.length>i;i++)
			SaveString(s[i]);
}


public void SaveString2(String s) // Speichert String im 8-bit-System
{
	if(!bOpen)
	{
		try
		{
                  	byte[] buf = s.getBytes();
			byte[] la = new byte[2];
			byte l = new Integer(buf.length/256).byteValue();
			la[0]= l;
			l = new Integer(buf.length%256).byteValue();
			la[1]= l;
			zos.write(la,0,2);
			zos.write(buf,0,buf.length);
		}
		catch(Exception e){ Static.printError("AUZip.SaveString2(): Exception - "+e);}
	}
}

public void SaveString(String s) // Speichert String im 16-bit-System
{
        if(!bOpen)
        {
                try
                {
                        byte[] la = new byte[2];
                        int iAnz=s.length();
                        la[0]= new Integer(iAnz/256).byteValue();
                        la[1]= new Integer(iAnz%256).byteValue();
                        zos.write(la,0,2);
                        for(int i=0;i<iAnz;i++)
                        {
                          la[0]=new Integer(s.charAt(i)/256).byteValue();
                          la[1]=new Integer(s.charAt(i)%256).byteValue();
                          zos.write(la,0,2);
                        }
                }
                catch(Exception e){ Static.printError("AUZip.SaveString(): Exception - "+e);}
        }
}

public void SaveBoolean(boolean b)
{
	if(!bOpen)
	{
		try
		{
			byte[] ba = new byte[1];
			//ba[0] = b?(byte)1:(byte)0;
			ba[0] = b?(byte)'T':(byte)'F';
			zos.write(ba,0,1);
		}
		catch(Exception e){Static.printError("AUZip.SaveBoolean(): Exception - "+e);}
	}
}

public void SaveLong(Long l)
{
	if(l!=null)
		SaveString2(l.toString());
	else
		SaveString2("");
}

public void SaveInteger(Integer i)
{
	if(i!=null)
		SaveString2(i.toString());
	else
		SaveString2("");
}

public void SaveDouble(Double d)
{
	if(d!=null)
		SaveString2(d.toString());
	else
		SaveString2("");
}

public void putNextEntry(ZipEntry ze)
{
	try
	{
		zos.putNextEntry(ze);
	}
	catch(IOException e){ Static.printError("AUZip.putNextEntry(): IOException - "+e);}
}

public void closeEntry()
{
	try
	{
		zos.closeEntry();
	}
	catch(IOException e){ Static.printError("AUZip.closeEntry(): IOException - "+e);}
}

/////////////////
///Lesebefehle///
/////////////////
       //
	   //
	   //
	///////
	 /////
	  ///
	  //

public String ReadString2()
{
	String s="";
	int retval=-1;
	if(bOpen)
	{
		byte[] buf = null;
		try
		{
			byte[] la = new byte[2];
			retval = zis.read(la,0,1);
			retval = zis.read(la,1,1);
			int l = (la[1]+256)%256+((la[0]+256)%256)*256;

			while(retval!=-1 && s.length()<l)
			{
				buf = new byte[l-s.length()];
				retval = zis.read(buf,0,l-s.length());
				s+=new String(buf);
				if(s.indexOf((char)0)>-1)
					s=s.substring(0,s.indexOf((char)0));
			}
		}
		catch(Exception e)
		{
			retval=-1;
			Static.printError("AUZip.ReadString2(): Exception - "+e);
		}
	}
	else
		Static.printError("AUZip.ReadString2(): Not opened!!!");

	return retval!=-1?s:null;
}

public String ReadString()
{
        String s=null;
        int retval=-1;
        if(bOpen)
        {
                //byte[] buf = null;
                //int i=0;
                try
                {
                        byte[] la = new byte[2];
                        retval = zis.read(la,0,1);
                        retval = zis.read(la,1,1);
                        int l = (la[1]+256)%256+((la[0]+256)%256)*256;
                        char[] c=new char[l];
                        for (int i2=0;i2<l;i2++)
                        {
                          retval = zis.read(la,0,1);
                          retval = zis.read(la,1,1);
                          c[i2] = (char)((la[1]+256)%256+((la[0]+256)%256)*256);
                        }
                        s=new String(c);
                        /*while(retval!=-1 && s.length()<l)
                        {
                                i++;
                                buf = new byte[l-s.length()];
                                retval = zis.read(buf,0,l-s.length());
                                s+=new String(buf);
                                if(s.indexOf((char)0)>-1)
                                        s=s.substring(0,s.indexOf((char)0));
                        }*/
                }
                catch(Exception e)
                {
                        retval=-1;
                        Static.printError("AUZip.ReadString(): Exception - "+e);
                }
        }
        else
                Static.printError("AUZip.ReadString(): Not opened!!!");

        return retval!=-1?s:null;
}

public JCOutliner ReadOutliner()
{
	JCOutliner Out = new JCOutliner();
	if(bOpen)
	{
		Vector<String> Vec = new Vector<String>();
		for(String s=ReadString();s!=null;s=ReadString())
			Vec.addElement(s);
		String[] s = new String[Vec.size()];
		Vec.copyInto(s);
		Out.setTree(s);
	}
	else
		Static.printError("AUZip.ReadOutliner(): Not opened!!!");

	return Out;
}

public Boolean ReadBoolean()
{
	int retval=-1;
	Boolean bRet = null;
	if(bOpen)
	{
		try
		{
			byte[] ba = new byte[1];
			retval=zis.read(ba,0,1);
			bRet = new Boolean(ba[0]==(byte)'T'?true:false);
		}
		catch(Exception e){ Static.printError("DefImport.ReadBoolean(): Exception - "+e);}
	}
	else
		Static.printError("AUZip.ReadBoolean(): Not opened!!!");

	return retval!=-1?bRet:null;
}

public Long ReadLong()
{
	Long iRet = null;
	if(bOpen)
	{
	String sInt = ReadString2();
	if(sInt!=null && !sInt.equals(""))
		iRet=new Long(sInt);
	}
	else
		Static.printError("AUZip.ReadLong(): Not opened!!!");

	return iRet;
}

public Integer ReadInteger()
{
	Integer iRet = null;
	if(bOpen)
	{
	String sInt = ReadString2();
	if(sInt!=null && !sInt.equals(""))
		iRet=new Integer(sInt);
	}
	else
		Static.printError("AUZip.ReadInteger(): Not opened!!!");

	return iRet;
}

public Double ReadDouble()
{
	Double dRet = null;
	if(bOpen)
	{
	String sInt = ReadString2();
	if(sInt!=null && !sInt.equals(""))
		dRet=new Double(sInt);
	}
	else
		Static.printError("AUZip.ReadDouble(): Not opened!!!");

	return dRet;
}

public ZipEntry getNextEntry()
{
	ZipEntry ze=null;
	try
	{
		ze = zis!=null?zis.getNextEntry():null;
	}
	catch(IOException e){ Static.printError("AUZip.getNextEntry(): IOException - "+e);}
	return ze;

}

public int available()
{
	int i=0;
	try
	{
		i=zis!=null?zis.available():0;
	}
	catch(IOException e){ Static.printError("AUZip.available(): IOException - "+e);}

	return i;
}

	// add your data members here
private boolean bOpen=false;
public FileOutputStream os = null;
public ZipOutputStream zos = null;
public FileInputStream is = null;
public ZipInputStream zis = null;
}

