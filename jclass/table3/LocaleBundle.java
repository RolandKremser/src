// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocaleBundle.java

package jclass.table3;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleBundle
{

    static synchronized String string(String s, String s1, String s2)
    {
        return ResourceBundle.getBundle("jclass.table3.resources.LocaleInfo", new Locale(s, s1)).getString(s2);
    }

    public static synchronized String string(String s)
    {
        return bundle.getString(s);
    }

    static synchronized void setBundleLocale(Locale locale)
    {
        bundle = ResourceBundle.getBundle("jclass.table3.resources.LocaleInfo", locale);
    }

    public LocaleBundle()
    {
    }

    private static final String BUNDLE_PATH = "jclass.table3.resources.LocaleInfo";
    private static ResourceBundle bundle = ResourceBundle.getBundle("jclass.table3.resources.LocaleInfo", Locale.getDefault());
    public static final String DELETE_ROWS = "Delete Record(s)";
    public static final String GO_TO = "Go to Row...";
    public static final String HIDE_COLUMN = "Hide Column...";
    public static final String INSERT_ROW = "Insert Record";
    public static final String PRINT = "Print...";
    public static final String PRINT_PREVIEW = "Print Preview...";
    public static final String CANCEL_ALL = "Refresh All";
    public static final String CANCEL_ROWS = "Refresh Record(s)";
    public static final String REQUERY_ALL = "Requery All";
    public static final String REQUERY_ROW_AND = "Requery Record and Details";
    public static final String COMMIT_ALL = "Save All";
    public static final String COMMIT_ROWS = "Save Record(s)";
    public static final String SHOW_COLUMN = "Show Column...";

}
