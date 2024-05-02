// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TraverseHandler.java

package jclass.table3;

import java.awt.Component;
import java.awt.event.*;
import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            EditHandler, InputHandler, JCCellPosition, JCTraverseCellEvent, 
//            Table

class TraverseHandler
    implements Serializable, FocusListener, KeyListener
{

    public TraverseHandler(Table table1)
    {
        forward = true;
        table = table1;
    }

    public void focusGained(FocusEvent focusevent)
    {
        Component component = focusevent.getComponent();
        JCCellPosition jccellposition = table.getPosition(component, null);
        table.editHandler.commit(true);
        if(!component.isFocusTraversable())
            component.addKeyListener(this);
        if(jccellposition == null)
        {
            return;
        } else
        {
            table.edit_row = jccellposition.row;
            table.edit_column = jccellposition.column;
            return;
        }
    }

    public void focusLost(FocusEvent focusevent)
    {
        Component component = focusevent.getComponent();
        JCCellPosition jccellposition = table.getPosition(component, null);
        component.removeKeyListener(this);
        if(jccellposition != null && (table.edit_row != jccellposition.row || table.edit_column != jccellposition.column))
        {
            Component component1 = table.getComponent(table.edit_row, table.edit_column);
            if(component1 != null)
            {
                table.edit_row = jccellposition.row;
                table.edit_column = jccellposition.column;
                component1.requestFocus();
            }
        }
    }

    public void keyPressed(KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 9)
        {
            if(keyevent.isShiftDown())
                forward = false;
            else
                forward = true;
            traverseNext(table.getPosition(keyevent.getComponent(), null));
        }
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void traverseNext(JCCellPosition jccellposition)
    {
        JCCellPosition jccellposition1 = null;
        String s = "POINTER";
        if(forward)
        {
            s = "RIGHT";
            jccellposition1 = InputHandler.findTraversableCell(table, s, jccellposition.row, jccellposition.column);
        } else
        {
            s = "LEFT";
            jccellposition1 = InputHandler.findTraversableCell(table, s, jccellposition.row, jccellposition.column);
        }
        int i = jccellposition1.row;
        int j = jccellposition1.column;
        if(table.traverseCellListeners != null)
        {
            JCTraverseCellEvent jctraversecellevent = new JCTraverseCellEvent(table, jccellposition.row, jccellposition.column, jccellposition1.row, jccellposition1.column, s);
            table.fireJCTraverseCellEvent(jctraversecellevent);
            i = jctraversecellevent.next_row;
            j = jctraversecellevent.next_column;
        }
        table.editHandler.traverse(i, j, false, null, s, false);
        Component component = table.getComponent(table.edit_row, table.edit_column);
        if(component != null)
            component.requestFocus();
    }

    Table table;
    boolean forward;
}
