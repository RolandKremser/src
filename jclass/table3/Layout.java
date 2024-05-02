// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Layout.java

package jclass.table3;

import java.awt.Dimension;
import java.awt.Point;

// Referenced classes of package jclass.table3:
//            Clip, EditHandler, JCTblEnum, Table, 
//            TableScrollbar

class Layout
{

    static void getHorizSBSize(Table table, boolean flag, int i, Point point, Dimension dimension)
    {
        point.x = table.frozenColumnLabelOffset() + table.frame_shadow;
        if(table.horizsb_attach == 0)
        {
            dimension.width = i;
            return;
        }
        dimension.width = table.containerWidth() - point.x;
        if(flag)
            dimension.width -= table.vertSBwidth();
    }

    static void getVertSBSize(Table table, boolean flag, int i, Point point, Dimension dimension)
    {
        point.y = table.frozenRowLabelOffset() + table.frame_shadow;
        if(table.vertsb_attach == 0)
        {
            dimension.height = i;
            return;
        }
        dimension.height = table.containerHeight() - point.y;
        if(flag)
            dimension.height -= table.horizSBheight();
    }

    static void doLayout(Table table)
    {
        int i = table.containerWidth();
        int j = table.containerHeight();
        int k = table.cell_total_width;
        int l = table.cell_total_height;
        int i1 = table.desired_width <= 0 || table.desired_width >= i ? i : table.desired_width;
        int j1 = table.desired_height <= 0 || table.desired_height >= j ? j : table.desired_height;
        Clip clip = table.clip;
        int k1 = 2 * table.frame_shadow + table.rowLabelExtraWidth();
        int l1 = 2 * table.frame_shadow + table.colLabelExtraHeight();
        int i2 = Math.min(65534, k + table.frozenColumnWidth() + table.rowLabelWidth() + k1);
        int j2 = Math.min(65534, l + table.frozenRowHeight() + table.columnLabelHeight() + l1);
        boolean flag = table.vertsb_display == 1 || j1 == 0 && table.visible_rows != -999 && table.visible_rows < table.rows || table.vertsb_display == 0 && j1 > 0 && j2 > j && table.frozen_rows < table.rows;
        boolean flag1 = table.horizsb_display == 1 || i1 == 0 && table.visible_columns != -999 && table.visible_columns < table.columns || table.horizsb_display == 0 && i1 > 0 && i2 > i && table.frozen_columns < table.columns;
        boolean flag2 = false;
        flag2 = table.frozenRowHeight() > 0 && table.frozenRowHeight() > j - table.columnLabelHeight() - l1 - table.horizSBheight();
        boolean flag3 = false;
        flag3 = table.frozenColumnWidth() > 0 && table.frozenColumnWidth() > i - table.rowLabelWidth() - k1 - table.vertSBwidth();
        if(flag2 || flag3)
        {
            flag = false;
            flag1 = false;
        }
        if(flag && !flag1 && table.horizsb_display == 0 && i > 0 && i2 > i - table.vertSBwidth() - table.vertsb_offset && !flag2)
            flag1 = true;
        if(flag1 && !flag && table.vertsb_display == 0 && j > 0 && j2 > j - table.horizSBheight() - table.horizsb_offset && !flag3)
            flag = true;
        int k2 = i - table.rowLabelWidth() - table.frozenColumnWidth() - k1;
        if(flag)
            k2 -= table.vertSBwidth() + table.vertsb_offset;
        int l2 = j - table.columnLabelHeight() - table.frozenRowHeight() - l1;
        if(flag1)
            l2 -= table.horizSBheight() + table.horizsb_offset;
        k2 = Math.min(k2, k);
        l2 = Math.min(l2, l);
        Point point = new Point(0, 0);
        Dimension dimension = new Dimension(0, 0);
        getVertSBSize(table, flag1, l2, point, dimension);
        if(table.cell_height < dimension.height)
            clip.vert_origin -= dimension.height - table.cell_height;
        if(l > 0)
        {
            if(table.set_top_row)
            {
                int k3 = Math.max(0, table.rowPosition(table.top_row) - table.rowPosition(table.frozen_rows));
                clip.vert_origin = Math.min(k3, l);
            }
            table.set_top_row = false;
        }
        if(l - clip.vert_origin < dimension.height)
            clip.vert_origin = l - dimension.height;
        if(clip.vert_origin < 0)
            clip.vert_origin = 0;
        if(table.vert_sb == null)
        {
            table.add(table.vert_sb = new TableScrollbar(table, 1));
            table.vert_sb.setUnitIncrement(21);
        }
        int i3;
        if(table.vertsb_position == 1)
            i3 = i - table.vertSBwidth();
        else
            i3 = k2 + table.rowLabelWidth() + table.frozenColumnWidth() + table.vertsb_offset + k1;
        if(flag)
            table.vert_sb.setBounds(i3, point.y, 16, dimension.height);
        else
            table.vert_sb.setBounds(i3, point.y, 0, 0);
        table.vert_sb.setValues(clip.vert_origin, l2, 0, l);
        table.vert_sb.show(flag);
        getHorizSBSize(table, flag, k2, point, dimension);
        if(table.cell_width < dimension.width)
            clip.horiz_origin -= dimension.width - table.cell_width;
        if(k > 0)
        {
            if(table.set_left_column)
            {
                int l3 = Math.max(0, table.columnPosition(table.left_column) - table.columnPosition(table.frozen_columns));
                clip.horiz_origin = Math.min(l3, k);
            }
            table.set_left_column = false;
        }
        if(k - clip.horiz_origin < dimension.width)
            clip.horiz_origin = k - dimension.width;
        if(clip.horiz_origin < 0)
            clip.horiz_origin = 0;
        if(table.horiz_sb == null)
        {
            table.add(table.horiz_sb = new TableScrollbar(table, 0));
            table.horiz_sb.setUnitIncrement(21);
        }
        int j3;
        if(table.horizsb_position == 1)
            j3 = j - table.horizSBheight();
        else
            j3 = l2 + table.columnLabelHeight() + table.frozenRowHeight() + table.horizsb_offset + l1;
        if(flag1)
            table.horiz_sb.setBounds(point.x, j3, dimension.width, 16);
        else
            table.horiz_sb.setBounds(point.x, j3, 0, 0);
        table.horiz_sb.setValues(clip.horiz_origin, k2, 0, k);
        table.horiz_sb.show(flag1);
        table.cell_width = clip.width = k2;
        table.cell_height = clip.height = l2;
        for(int i4 = 0; i4 < table.clip_list.length; i4++)
        {
            Clip clip1 = table.clip_list[i4];
            if(clip1.isHorizontal())
                clip1.horiz_origin = clip.horiz_origin;
            if(clip1.isVertical())
                clip1.vert_origin = clip.vert_origin;
        }

        Clip.reshape(table);
        if(table.hasText())
            table.editHandler.move(0, 0);
    }

    Layout()
    {
    }
}
