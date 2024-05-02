// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCURLDirectory.java

package jclass.util;

import java.applet.Applet;
import java.net.URL;
import java.util.Vector;

// Referenced classes of package jclass.util:
//            JCFile

public class JCURLDirectory
{

    public JCURLDirectory()
    {
    }

    public static String[] getFileList(Applet applet, String s)
    {
        String as[] = null;
        try
        {
            URL url = JCFile.createURL(applet, s);
            String s1 = url.getHost();
            String s2 = url.getFile();
            String s3 = url.getProtocol();
            String s4 = new String(JCFile.read(null, s));
            Vector vector = new Vector();
            for(int i = getNextRef(s4, 0); i >= 0 && i < s4.length(); i = getNextRef(s4, i + 4))
            {
                String s5 = parseRef(s4, i);
                if(s5 != null && !s5.equals("/.."))
                {
                    String s6 = new String(s3 + "://" + s1 + s2 + s5);
                    vector.addElement(s6);
                }
            }

            as = new String[vector.size()];
            for(int j = 0; j < vector.size(); j++)
                as[j] = (String)vector.elementAt(j);

        }
        catch(Throwable _ex) { }
        return as;
    }

    private static int getNextRef(String s, int i)
    {
        return s.toUpperCase().indexOf("HREF", i);
    }

    private static String parseRef(String s, int i)
    {
        if(!s.substring(i, i + 4).equalsIgnoreCase("HREF"))
            return null;
        int j = i + 4;
        int k = s.indexOf(">", j);
        if(k < 0)
            return null;
        if(s.charAt(k - 1) == '"')
            k--;
        int l;
        for(l = k - 1; l >= j; l--)
        {
            if(s.charAt(l) != '=' && s.charAt(l) != '/' && s.charAt(l) != '\\' && s.charAt(l) != '"')
                continue;
            l++;
            break;
        }

        if(l >= k)
            return null;
        else
            return s.substring(l, k);
    }
}
