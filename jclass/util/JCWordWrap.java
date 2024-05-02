// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCWordWrap.java

package jclass.util;

import java.awt.FontMetrics;
import java.util.StringTokenizer;

public class JCWordWrap
{

    public JCWordWrap()
    {
    }

    public static String replace(String s, String s1, String s2)
    {
        if(s1.equals(s2))
            return s;
        int i = s.indexOf(s1);
        if(i != -1)
            return replace(s.substring(0, i) + s2 + s.substring(i + s1.length()), s1, s2);
        else
            return s;
    }

    public static String wrapText(String s, FontMetrics fontmetrics, int i, String s1, boolean flag)
    {
        if(i < 10)
            return s;
        if(s == null || s.length() == 0)
            return s;
        if(s1 == null)
            s1 = "\n";
        if(fontmetrics.stringWidth(s) < i)
            return s;
        boolean flag1 = false;
        int k = 0;
        StringBuffer stringbuffer = new StringBuffer(s.length());
        for(StringTokenizer stringtokenizer = new StringTokenizer(s, " ", true); stringtokenizer.hasMoreTokens();)
        {
            String s2 = stringtokenizer.nextToken();
            int j = fontmetrics.stringWidth(s2);
            if(s2.equals(" ") && flag)
            {
                stringbuffer.append(s2);
                k += j;
            } else
            if(k + j > i)
            {
                stringbuffer.append(s1 + s2);
                k = j;
            } else
            {
                stringbuffer.append(s2);
                k += j;
            }
        }

        return stringbuffer.toString();
    }
}
