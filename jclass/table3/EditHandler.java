// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EditHandler.java

package jclass.table3;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Vector;
import jclass.cell.*;
import jclass.cell.editors.MultilineCellEditor;

// Referenced classes of package jclass.table3:
//            Clip, InputHandler, JCCell, JCCellPosition, 
//            JCCellRange, JCEnterCellEvent, JCTblEnum, JCTraverseCellEvent, 
//            Span, Table, TableCellInfo, TableDataView, 
//            TextCellEditor, TraverseInitial

public class EditHandler
    implements CellEditorListener, Serializable, FocusListener
{

    public EditHandler(Table table1)
    {
        tableFocusRequested = false;
        table = table1;
    }

    public CellEditor getCellEditor()
    {
        return editor;
    }

    public void setCellEditor(CellEditor celleditor)
    {
        if(celleditor != null)
        {
            editComponent = celleditor.getComponent();
            if(editComponent != null)
            {
                editComponent.addKeyListener(table.inputHandler);
                editComponent.removeFocusListener(this);
                editComponent.addFocusListener(this);
            }
            celleditor.addCellEditorListener(this);
        } else
        {
            if(editor != null)
                editor.removeCellEditorListener(this);
            if(editComponent != null)
                editComponent.removeKeyListener(table.inputHandler);
            editComponent = null;
        }
        editor = celleditor;
        editRow = table.edit_row;
        editColumn = table.edit_column;
        clip = Clip.find(table, editRow, editColumn);
    }

    boolean traverse(int i, int j, boolean flag, InitialEvent initialevent, String s, boolean flag1)
    {
        if(initialevent != null && initialevent.getKey() == 16)
            return false;
        if(table.span.span_list.size() > 0)
        {
            JCCellPosition jccellposition = new JCCellPosition();
            if(table.span.find(i, j, jccellposition) != -999)
            {
                i = jccellposition.row;
                j = jccellposition.column;
            }
        }
        int k = table.edit_row;
        int l = table.edit_column;
        if(!TraverseInitial.traverse(table, i, j, flag, s))
            return false;
        if(!table.isDisplayable() || !table.isVisible())
        {
            table.edit_row = i;
            table.edit_column = j;
            return true;
        }
        Component component = table.getComponent(i, j);
        if(component != null)
        {
            component.requestFocus();
            table.edit_row = i;
            table.edit_column = j;
        } else
        {
            boolean flag2 = flag1;
            if(s == null && !flag1)
                flag2 = false;
            if(s != null && (!s.equalsIgnoreCase("POINTER") || !flag1) && (k != table.edit_row || l != table.edit_column))
                flag2 = false;
            if(!table.getEditable(i, j) || !table.dataView.getEditable())
                flag2 = false;
            if(initialevent == null)
                if(flag1)
                {
                    table.clearSelectedCells();
                    initialevent = new BaseInitialEvent(0, 0, 0);
                } else
                {
                    flag2 = false;
                }
            if(flag2)
            {
                Object obj = table.dataView.getCellEditor(i, j);
                if(editor != null && editor != obj)
                    cancel();
                Object obj1 = table.dataView.getCellData(i, j);
                if((obj instanceof TextCellEditor) && obj1 != null && (table.getMultiline(i, j) || obj1.toString().indexOf("\n") != -1))
                    obj = new MultilineCellEditor();
                setCellEditor(((CellEditor) (obj)));
                cellInfo = new TableCellInfo(table, clip, i, j);
                if(editor != null)
                {
                    Rectangle rectangle = new Rectangle();
                    JCCell.getBounds(table, null, i, j, rectangle);
                    Point point = clip.location();
                    ((BaseInitialEvent)initialevent).setX(initialevent.getX() - rectangle.x - point.x);
                    ((BaseInitialEvent)initialevent).setY(initialevent.getY() - rectangle.y - point.y);
                    if(obj1 instanceof CellData)
                    {
                        cellDataObject = (CellData)obj1;
                        editor.initialize(initialevent, cellInfo, cellDataObject.getData());
                    } else
                    {
                        cellDataObject = null;
                        editor.initialize(initialevent, cellInfo, obj1);
                    }
                    if(editComponent != null)
                    {
                        Color color = table.getBackground(i, j);
                        if(color instanceof SystemColor)
                            color = new Color(((SystemColor)color).getRGB());
                        editComponent.setVisible(false);
                        if(editComponent.getParent() != clip)
                            clip.add(editComponent);
                    }
                    if(setValues(i, j) && editComponent != null)
                    {
                        editComponent.show();
                        setFocusOnEditor(editComponent);
                    }
                }
            }
        }
        if((s == null || s != null && !s.equals("PRESS")) && table.enterCellListeners != null)
        {
            JCEnterCellEvent jcentercellevent = new JCEnterCellEvent(table, 2, i, j, s);
            table.fireJCEnterCellEvent(jcentercellevent);
        }
        return true;
    }

    void setFocusOnEditor(Component component)
    {
        if(component == null)
            return;
        if(component.isFocusTraversable())
        {
            component.requestFocus();
            return;
        }
        if(component instanceof Container)
        {
            Component acomponent[] = ((Container)component).getComponents();
            for(int i = 0; i < acomponent.length; i++)
                if(acomponent[i].isFocusTraversable())
                {
                    acomponent[i].requestFocus();
                    return;
                }

        }
    }

    boolean setValues(int i, int j)
    {
        if(editor == null)
            return false;
        if(editComponent == null)
            return true;
        else
            return setValues(i, j, table.getEditable(i, j) & table.dataView.getEditable());
    }

    boolean setValues(int i, int j, boolean flag)
    {
        if(editor == null)
            return false;
        if(editComponent == null)
            return true;
        Rectangle rectangle = new Rectangle();
        JCCell.getBounds(table, null, i, j, rectangle);
        if(table.resize_row == -999 && table.resize_column == -999)
        {
            editComponent.enable(table.isEnabled());
            clip.changed = false;
        }
        setSize(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        return true;
    }

    void setSize(int i, int j, int k, int l)
    {
        Dimension dimension = new Dimension(10, 10);
        int i1 = table.edit_height_policy;
        int j1 = table.edit_width_policy;
        int k1 = Math.max(dimension.width, k);
        int l1 = Math.max(dimension.height, l);
        switch(j1)
        {
        case 1: // '\001'
            if(editComponent.getMinimumSize().width > k1)
                k1 = editComponent.getMinimumSize().width;
            break;

        case 2: // '\002'
            if(editComponent.getPreferredSize().width > k1)
                k1 = editComponent.getPreferredSize().width;
            break;
        }
        switch(i1)
        {
        default:
            break;

        case 1: // '\001'
            if(editComponent.getMinimumSize().height > l1)
                l1 = editComponent.getMinimumSize().height;
            break;

        case 2: // '\002'
            if(editComponent.getPreferredSize().height > l1)
                l1 = editComponent.getPreferredSize().height;
            break;
        }
        origin = new Point(i, j);
        repositionOrigin(origin);
        if(editComponent instanceof Choice)
            l1 = editComponent.getPreferredSize().height;
        table.paint(editRow, editColumn);
        editComponent.setBounds(i, j, k1, l1);
    }

    void move(int i, int j)
    {
        if(editComponent == null)
            return;
        int k;
        int l;
        switch(i)
        {
        case 2: // '\002'
            k = editComponent.location().x;
            l = (editComponent.location().y + clip.vert_origin) - j;
            break;

        case 1: // '\001'
            k = (editComponent.location().x + clip.horiz_origin) - j;
            l = editComponent.location().y;
            break;

        default:
            Point point = new Point(0, 0);
            table.getPosition(editRow, editColumn, point);
            if(point.x == 0x7fffffff)
                return;
            k = point.x;
            l = point.y;
            break;
        }
        origin = new Point(k, l);
        repositionOrigin(origin);
    }

    void repositionOrigin(Point point)
    {
        Dimension dimension = clip.size();
        Dimension dimension1 = editComponent.size();
        if(editColumn < table.frozen_columns)
        {
            if(point.x + dimension1.width > table.frozenColumnWidth() - clip.horiz_origin && dimension.width > dimension1.width)
                point.x = table.frozenColumnWidth() - clip.horiz_origin - dimension1.width;
        } else
        if(point.x + dimension1.width > table.cell_total_width - clip.horiz_origin && dimension.width > dimension1.width)
            point.x = table.cell_total_width - clip.horiz_origin - dimension1.width;
        if(editRow < table.frozen_rows)
        {
            if(point.y + dimension1.height > table.frozenRowHeight() - clip.vert_origin && dimension.height > dimension1.height)
                point.y = table.frozenRowHeight() - clip.vert_origin - dimension1.height;
        } else
        if(point.y + dimension1.height > table.cell_total_height - clip.vert_origin && dimension.height > dimension1.height)
            point.y = table.cell_total_height - clip.vert_origin - dimension1.height;
        editComponent.move(point.x, point.y);
    }

    void flash()
    {
        if(editComponent == null || !editComponent.isVisible())
        {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        Toolkit.getDefaultToolkit().beep();
        if(!(editComponent instanceof TextComponent))
            return;
        Color color = editComponent.getForeground();
        Color color1 = editComponent.getBackground();
        editComponent.setForeground(color1);
        editComponent.setBackground(color);
        TextComponent textcomponent = null;
        String s = null;
        if(editComponent instanceof TextComponent)
        {
            textcomponent = (TextComponent)editComponent;
            s = textcomponent.getText();
            if(s != null)
            {
                textcomponent.setText(s);
                textcomponent.select(s.length(), s.length());
            }
        }
        editComponent.getToolkit().sync();
        try
        {
            Thread.sleep(250L);
        }
        catch(Exception _ex) { }
        editComponent.setBackground(color1);
        editComponent.setForeground(color);
        if((editComponent instanceof TextComponent) && s != null)
        {
            textcomponent.setText(s);
            textcomponent.select(s.length(), s.length());
        }
    }

    boolean cancel()
    {
        return cancel(true);
    }

    boolean cancel(boolean flag)
    {
        if(editor == null)
            return true;
        editor.cancelCellEditing();
        editor.removeCellEditorListener(this);
        if(flag)
            removeEditor();
        return true;
    }

    boolean commit()
    {
        return commit(true);
    }

    boolean commit(boolean flag)
    {
        return commit(flag, true);
    }

    boolean commit(boolean flag, boolean flag1)
    {
        if(editor == null)
            return true;
        if(flag1)
        {
            boolean flag2 = editor.isModified();
            if(!editor.stopCellEditing())
            {
                flash();
                return false;
            }
            if(!flag2)
            {
                if(flag)
                    removeEditor();
                return true;
            }
        }
        Object obj;
        if((obj = editor.getCellEditorValue()) != null)
        {
            if(cellDataObject != null)
                try
                {
                    try
                    {
                        cellDataObject = (CellData)cellDataObject.getClass().newInstance();
                    }
                    catch(Throwable _ex)
                    {
                        System.out.println("Warning: CellData object lacks no-argument constructor.");
                    }
                    cellDataObject.setData(obj);
                    obj = cellDataObject;
                }
                catch(Exception exception)
                {
                    exception.printStackTrace(System.out);
                    cancel();
                    return true;
                }
            if(!table.dataView.setCellData(obj, editRow, editColumn) && editComponent != null)
                editor.cancelCellEditing();
            if(flag)
                removeEditor();
            return true;
        } else
        {
            flash();
            setFocusOnEditor(editComponent);
            return false;
        }
    }

    void commitAndTraverse(int i)
    {
        JCCellPosition jccellposition = null;
        String s = "POINTER";
        switch(i)
        {
        case 1: // '\001'
            s = "LEFT";
            jccellposition = InputHandler.findTraversableCell(table, s, editRow, editColumn);
            break;

        case 2: // '\002'
            s = "RIGHT";
            jccellposition = InputHandler.findTraversableCell(table, s, editRow, editColumn);
            break;

        case 3: // '\003'
            s = "UP";
            jccellposition = InputHandler.findTraversableCell(table, s, editRow, editColumn);
            break;

        case 4: // '\004'
            s = "DOWN";
            jccellposition = InputHandler.findTraversableCell(table, s, editRow, editColumn);
            break;
        }
        if(commit())
        {
            if(jccellposition != null)
                if(table.traverseCellListeners != null)
                {
                    JCTraverseCellEvent jctraversecellevent = new JCTraverseCellEvent(table, editRow, editColumn, jccellposition.row, jccellposition.column, s);
                    table.fireJCTraverseCellEvent(jctraversecellevent);
                    int j = jctraversecellevent.next_row;
                    int k = jctraversecellevent.next_column;
                    traverse(j, k, true, null, s, false);
                    return;
                } else
                {
                    traverse(jccellposition.row, jccellposition.column, true, null, s, false);
                    return;
                }
            if(clip != null)
                clip.requestFocus();
        }
    }

    void removeEditor()
    {
        if(editComponent != null)
            editComponent.setVisible(false);
        setCellEditor(null);
        if(clip != null)
            clip.requestFocus();
        table.paint(new JCCellRange(editRow, editColumn, editRow, editColumn));
    }

    public void editingStopped(CellEditorEvent celleditorevent)
    {
        if(celleditorevent.getEvent() != null && (celleditorevent.getEvent() instanceof KeyEvent))
            ((KeyEvent)celleditorevent.getEvent()).consume();
        if(editor != null && table.edit_row == editRow && table.edit_column == editColumn && commit(true, false))
        {
            Clip clip1 = Clip.find(table, table.edit_row, table.edit_column);
            if(clip1 != null)
                clip1.requestFocus();
        }
    }

    public void editingCanceled(CellEditorEvent celleditorevent)
    {
        if(editor != null && table.edit_row == editRow && table.edit_column == editColumn)
        {
            int i = table.edit_row;
            int j = table.edit_column;
            cancel(true);
            Clip clip1 = Clip.find(table, i, j);
            if(clip1 != null)
                clip1.requestFocus();
        }
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent focusevent)
    {
        if(tableFocusRequested)
            return;
        tableFocusRequested = true;
        if(focusevent.getComponent() != editComponent && !focusevent.getComponent().getClass().getName().equals("jclass.bwt.ComboField"))
        {
            Clip clip1 = Clip.find_by_type(table, 0);
            if(clip1 != null)
                clip1.requestFocus();
            focusevent.getComponent().removeFocusListener(this);
        }
        tableFocusRequested = false;
    }

    Table table;
    CellEditor editor;
    Component editComponent;
    int editRow;
    int editColumn;
    Clip clip;
    Point origin;
    TableCellInfo cellInfo;
    CellData cellDataObject;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    private boolean tableFocusRequested;
}
