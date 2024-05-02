// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TraverseInitial.java

package jclass.table3;

import java.awt.Point;
import java.util.Vector;

// Referenced classes of package jclass.table3:
//            Clip, Draw, InputHandler, JCCellPosition, 
//            JCEnterCellEvent, JCTblEnum, JCTraverseCellEvent, Span, 
//            Table

class TraverseInitial
{

    static void traverse(Table table, boolean flag)
    {
        if(table.edit_row == -996)
            return;
        int i;
        int j;
        if(table.edit_row == -999)
        {
            i = table.YtoRow(table.clip.vert_origin);
            j = table.XtoColumn(table.clip.horiz_origin);
        } else
        {
            i = table.edit_row;
            j = table.edit_column;
        }
        if(!table.IS_TRAVERSABLE(i, j))
        {
            JCCellPosition jccellposition = InputHandler.findTraversableCell(table, "RIGHT", i, j);
            if(jccellposition == null)
                return;
            i = jccellposition.row;
            j = jccellposition.column;
        }
        if(table.traverseCellListeners != null)
        {
            JCTraverseCellEvent jctraversecellevent = new JCTraverseCellEvent(table, -999, -999, i, j, null);
            table.fireJCTraverseCellEvent(jctraversecellevent);
            i = jctraversecellevent.next_row;
            j = jctraversecellevent.next_column;
        }
        if(table.selection_policy == 0)
            flag = false;
        traverse(table, i, j, flag, null);
        if(table.enterCellListeners != null)
        {
            JCEnterCellEvent jcentercellevent = new JCEnterCellEvent(table, 2, i, j, null);
            table.fireJCEnterCellEvent(jcentercellevent);
        }
    }

    static void eraseRect(Table table, int i, int j)
    {
        if(table.hasText() || !table.isVisible(i, j))
            return;
        if(table.margin_width > 1 && table.margin_height > 1)
        {
            if(table.isSelected(i, j))
            {
                Draw.drawRect(table, null, i, j, table.getSelectedBackground());
                return;
            } else
            {
                Draw.drawRect(table, null, i, j, table.getBackground(i, j));
                return;
            }
        } else
        {
            table.edit_row = -999;
            table.paint(i, j);
            return;
        }
    }

    static boolean traverse(Table table, int i, int j, boolean flag, String s)
    {
        if(table.span.span_list.size() > 0)
        {
            JCCellPosition jccellposition = new JCCellPosition();
            if(table.span.find(i, j, jccellposition) != -999)
            {
                i = jccellposition.row;
                j = jccellposition.column;
            }
        }
        if(!table.makeVisible(i, j))
            return false;
        if(!table.IS_TRAVERSABLE(i, j))
            return false;
        if((s == null || s != null && !s.equals("PRESS")) && table.enterCellListeners != null)
        {
            JCEnterCellEvent jcentercellevent = new JCEnterCellEvent(table, 1, i, j, s);
            table.fireJCEnterCellEvent(jcentercellevent);
        }
        int k = table.edit_row;
        int l = table.edit_column;
        boolean flag1 = false;
        Clip clip = Clip.find(table, i, j);
        if(clip != null)
            clip.requestFocus();
        if(k != i || l != j)
            flag1 = true;
        table.edit_row = i;
        table.edit_column = j;
        if(flag)
        {
            Point point = new Point(0, 0);
            table.getPosition(i, j, point);
            table.inputHandler.select(point.x, point.y, "CURRENT");
        } else
        if(flag1)
            table.paint(k, l);
        return true;
    }

    TraverseInitial()
    {
    }
}
