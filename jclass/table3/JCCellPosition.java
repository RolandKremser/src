// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCellPosition.java

package jclass.table3;

import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            JCTblEnum

public class JCCellPosition
    implements Serializable
{

    public JCCellPosition()
    {
        row = -999;
        column = -999;
    }

    public JCCellPosition(int i, int j)
    {
        row = -999;
        column = -999;
        row = i;
        column = j;
    }

    public boolean equals(JCCellPosition jccellposition)
    {
        return jccellposition.row == row && jccellposition.column == column;
    }

    public boolean equals(int i, int j)
    {
        return i == row && j == column;
    }

    public void reshape(int i, int j)
    {
        row = i;
        column = j;
    }

    public String toString()
    {
        return super.toString() + " [" + row + "," + column + "]";
    }

    public int row;
    public int column;
}
