/*
    All_Unlimited-Allgemein-SimpleFileFilter.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.io.File;

public class SimpleFileFilter extends javax.swing.filechooser.FileFilter
{
    /**
     * accept Method
     */

    public boolean accept(File f)
    {
		if( f.isDirectory())
			return true;

		String name = f.getName().toLowerCase();
		for(int i = extensions.length - 1; i>=0;i--)
		{
			if(name.endsWith(extensions[i]))
				return true;
		}
		return false;
    }
    /**
     * getDescription Method
     */

    public String getDescription()
    {
		return description;
    }
    // add your data members here
	String[] extensions;
	String description;

	public SimpleFileFilter(String ext)
	{
		this (new String[] {ext},null);
	}

	public SimpleFileFilter(String[] exts,String descr)
	{
		extensions = new String[exts.length];
		for(int i = exts.length - 1; i >= 0; i--)
		{
			extensions[i]=exts[i].toLowerCase();
		}
	description=(descr == null ? exts[0] + " files" : descr);
	}	
}

