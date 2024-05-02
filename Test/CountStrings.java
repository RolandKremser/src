package Test;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CountStrings extends java.applet.Applet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5963283014893383372L;
	public void init()
    {
		String[] s = new File("c:\\sign\\java2\\3_12x\\").list();
		int iSum=0;
		for(int i=0;i<s.length;i++)
		{
			//File f = new File(sVerzeichnis+s[i]);
			int iAnz=countStrings("file:\\c:\\sign\\java2\\3_12x\\"+s[i],"now");
			if (iAnz>0)
			{
				iSum+=iAnz;
				System.out.println("Datei"+i+":"+s[i]+":"+iAnz);
			}
		}
		System.out.println("Anzahl="+iSum);

    }
	@SuppressWarnings("deprecation")
	public int countStrings(String sFile,String sText)
	{
		String s2=sText.toUpperCase();
		int i=0;
		try
		{
			java.net.URL url=new java.net.URL(sFile);
			InputStream is=(InputStream)url.getContent();
			DataInputStream Buf=new DataInputStream(is);
			String s=Buf.readLine().toUpperCase();
			while (s != null)
			{
				int iPos=0;
				while(iPos>=0)
				{
					iPos=s.indexOf(s2,iPos);
					//System.out.println(iPos+"/"+i);
					if (iPos>=0)
					{
						iPos++;
						i++;
					}
				}
				s=Buf.readLine();
				if (s!=null)
					s=s.toUpperCase();
			}
			Buf.close();

		}catch(IOException io)
		{
			System.out.println("Fehler: "+io);
		}

		return i;
	}
    // add your data members here
}

