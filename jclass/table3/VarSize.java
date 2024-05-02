// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VarSize.java

package jclass.table3;

import java.awt.Component;

// Referenced classes of package jclass.table3:
//            Chain, JCCell, JCTblEnum, PropertyEnum, 
//            Size, Table

class VarSize
{

    static int calc(Table table, int i, int j, int k, int l)
    {
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = table.in_setComponent;
        if(!table.isDisplayable())
        {
            table.needs_recalc = true;
            return 0;
        }
        if(table.repaint && !flag2)
        {
            i1 = table.columnWidth(j) - table.textWidthOffset() * 2;
            flag = Size.calcLargestWidth(table, i, i, j, 0) < i1;
            j1 = table.rowHeight(i) - table.textHeightOffset() * 2;
            flag1 = Size.calcLargestHeight(table, i, j, j, 0) < j1;
        }
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return 0;
        }
        int k1 = JCCell.getWidth(table, i, j);
        int l1 = table.textWidthOffset() * 2;
        int i2 = JCCell.getHeight(table, i, j);
        int j2 = table.textHeightOffset() * 2;
        int k2 = 0;
        if(Table.isCell(i, j))
        {
            if(k == 33001)
                if(flag2)
                    k2 |= 0x8000;
                else
                if(k1 > table.columnWidth(j) - l1)
                {
                    k2 |= 0x2000;
                    table.column_widths.setValue(j, j, k1 + l1);
                } else
                if(flag && k1 < i1)
                    k2 |= 0x8000;
            if(l == 33001)
                if(flag2)
                    k2 |= 0x100000;
                else
                if(i2 > table.rowHeight(i) - j2)
                {
                    k2 |= 0x1000;
                    table.row_heights.setValue(i, i, i2 + j2);
                } else
                if(flag1 && i2 < j1)
                    k2 |= 0x100000;
        } else
        if(Table.isColumnLabel(i, j))
        {
            if(k == 33001)
            {
                if(flag2)
                    k2 |= 0xc000;
                else
                if(k1 > table.columnWidth(j) - l1)
                {
                    k2 |= 0x2080;
                    table.column_widths.setValue(j, j, k1 + l1);
                } else
                if(flag && k1 < i1)
                    k2 |= 0x8080;
                if(JCCell.getHeight(table, i, j) > table.column_label_height - j2)
                    k2 |= 0x4080;
            }
            if(l == 33001)
                if(flag2)
                    k2 |= 0x4000;
                else
                if(i2 > table.rowHeight(i) - j2)
                    k2 |= 0x4080;
                else
                if(flag1 && i2 < j1)
                    k2 |= 0x4080;
        } else
        if(Table.isRowLabel(i, j))
        {
            if(l == 33001)
            {
                if(flag2)
                    k2 |= 0x300000;
                else
                if(i2 > table.rowHeight(i) - j2)
                {
                    k2 |= 0x1080;
                    table.row_heights.setValue(i, i, i2 + j2);
                } else
                if(flag1 && i2 < j1)
                    k2 |= 0x100080;
                if(JCCell.getWidth(table, i, j) > table.row_label_width - l1)
                    k2 |= 0x200080;
            }
            if(k == 33001)
                if(flag2)
                    k2 |= 0x200000;
                else
                if(k1 > table.columnWidth(j) - l1)
                    k2 |= 0x200080;
                else
                if(flag && k1 < i1)
                    k2 |= 0x200080;
        }
        return k2;
    }

    VarSize()
    {
    }
}
