// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCPrintEvent.java

package jclass.table3;

import java.awt.*;

// Referenced classes of package jclass.table3:
//            TableAWTEvent, JCPrintTable, JCTblEnum, Table

public class JCPrintEvent extends TableAWTEvent
{

    public Graphics getGraphics()
    {
        return gc;
    }

    public int getMarginUnits()
    {
        if(printTable != null)
            return printTable.getMarginUnits();
        else
            return -999;
    }

    public int getNumHorizontalPages()
    {
        if(printTable != null)
            return printTable.getNumHorizontalPages();
        else
            return 0;
    }

    public int getNumPages()
    {
        return numPages;
    }

    public int getNumVerticalPages()
    {
        if(printTable != null)
            return printTable.getNumVerticalPages();
        else
            return 0;
    }

    public int getPage()
    {
        return page;
    }

    public Dimension getPageDimensions()
    {
        if(printTable != null)
            return printTable.getPageDimensions();
        else
            return null;
    }

    public Insets getPageMargins()
    {
        if(printTable != null)
            return printTable.getPageMargins();
        else
            return null;
    }

    public int getPageResolution()
    {
        if(printTable != null)
            return printTable.getPageResolution();
        else
            return 0;
    }

    public Dimension getTableDimensions()
    {
        if(printTable != null)
            return printTable.getTableDimensions(page - 1);
        else
            return null;
    }

    public int getType()
    {
        return type;
    }

    public JCPrintEvent(Table table, Graphics g, int i, int j, int k)
    {
        super(table, 5050);
        gc = g;
        page = i;
        numPages = j;
        type = k;
    }

    public JCPrintEvent(Table table, Graphics g, int i, int j, int k, JCPrintTable jcprinttable)
    {
        super(table, 5050);
        gc = g;
        page = i;
        numPages = j;
        type = k;
        printTable = jcprinttable;
    }

    public static final int HEADER = 1;
    public static final int FOOTER = 2;
    public static final int END = 3;
    public static final int BODY = 4;
    Graphics gc;
    int page;
    int numPages;
    int type;
    JCPrintTable printTable;
    public static final int PRINT = 5050;
}
