// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCVersion.java

package jclass.field;

import java.io.PrintStream;

public final class JCVersion
{

    public static final String getVersionString()
    {
        if(versionString == null)
        {
            versionString = "JClass Field " + 3 + "." + 6 + "." + 0 + "J" + " for " + "JDK 1.2 Swing";
            versionString = versionString + " Preview";
        }
        return versionString;
    }

    public static final String getAboutString()
    {
        return "JClass Field by KL Group (http://www.klg.com)";
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

    public JCVersion()
    {
    }

    public static final int major = 3;
    public static final int minor = 6;
    public static final int release = 0;
    public static final String about = "JClass Field by KL Group (http://www.klg.com)";
    public static final String platform = "JDK 1.2 Swing";
    public static final String platform_id = "J";
    private static String versionString = null;
    private static String versionNumber = null;

}
