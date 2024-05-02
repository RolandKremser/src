// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCVersion.java

package jclass.table3;

import java.io.PrintStream;

public final class JCTVersion
{

    public static final String getVersionString()
    {
        if(versionString == null)
        {
            versionString = "JClass Table " + 3 + "." + 6 + "." + 0 + "J" + " for " + "JDK 1.2 Swing";
            versionString = versionString + " Lite";
            versionString = versionString + " Preview";
        }
        return versionString;
    }

    public static final String getVersionNumber()
    {
        if(versionNumber == null)
            versionNumber = "" + 3 + 6 + 0 + "J";
        return versionNumber;
    }

    public static final void main(String args[])
    {
        System.out.println(getVersionString());
    }

    public JCTVersion()
    {
    }

    public static final int major = 3;
    public static final int minor = 6;
    public static final int release = 0;
    public static final String platform_id = "J";
    public static final String platform = "JDK 1.2 Swing";
    private static String versionString = null;
    private static String versionNumber = null;

}
