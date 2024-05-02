// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InputHandler.java

package jclass.table3;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.Vector;
import jclass.cell.*;
import jclass.util.*;

// Referenced classes of package jclass.table3:
//            Clip, EditHandler, JCCellPosition, JCCellRange, 
//            JCResizeCellEvent, JCSelectEvent, JCStringCellData, JCStringCellRenderer, 
//            JCTblEnum, JCTraverseCellEvent, LabelTrigger, Select, 
//            Sort, Span, StringCellRangeFlavor, Table, 
//            TableDataView, TableScrollbar, VectorDataSource, Widget

class InputHandler
    implements Runnable, Serializable, MouseListener, MouseMotionListener, KeyListener
{

    public InputHandler(Table table1)
    {
        span = new JCCellRange();
        x_save = -999;
        selecting = false;
        extended = false;
        sorting = false;
        menu = false;
        dragging = false;
        autoscrolling = false;
        auto_scroll_horiz = false;
        auto_scroll_vert = false;
        auto_scroll_horiz_direction = 0;
        auto_scroll_vert_direction = 0;
        DEBUG_MODE = false;
        table = table1;
    }

    public static boolean isShift(int i)
    {
        return (i & 1) != 0;
    }

    public static boolean isControl(int i)
    {
        return (i & 2) != 0;
    }

    public static boolean isAlt(int i)
    {
        return (i & 8) != 0;
    }

    public static boolean isMeta(int i)
    {
        return (i & 4) != 0;
    }

    Clip getClip(int i, int j)
    {
        Component component = table.locate(i, j);
        if(component instanceof Clip)
            return (Clip)component;
        else
            return null;
    }

    public JCCellPosition XYToCellPosition(int i, int j)
    {
        JCCellPosition jccellposition = XYToCell(i, j);
        if(jccellposition == null)
            return null;
        else
            return new JCCellPosition(jccellposition.row, jccellposition.column);
    }

    public JCCellPosition XYToCell(int i, int j)
    {
        Clip clip1 = getClip(i, j);
        if(clip1 == null)
            return null;
        i -= clip1.location().x;
        j -= clip1.location().y;
        int l;
        int k = l = -999;
        switch(clip1.type)
        {
        case 4: // '\004'
        case 6: // '\006'
            k = table.YtoRow(j + clip1.yOffset() + clip1.vert_origin);
            l = -1;
            if(k >= table.rows || k < 0)
                return null;
            break;

        case 5: // '\005'
        case 7: // '\007'
            k = -1;
            l = table.XtoColumn(i + clip1.xOffset() + clip1.horiz_origin);
            if(l >= table.columns || l < 0)
                return null;
            break;

        default:
            k = table.YtoRow(j + clip1.yOffset() + clip1.vert_origin);
            l = table.XtoColumn(i + clip1.xOffset() + clip1.horiz_origin);
            if(k >= table.rows || k < 0)
                return null;
            if(l >= table.columns || l < 0)
                return null;
            break;
        }
        xyToCellPosition.reshape(k, l);
        return xyToCellPosition;
    }

    public JCCellPosition XYToCellPosition(boolean flag, boolean flag1, Point point)
    {
        JCCellPosition jccellposition = XYToCell(flag, flag1, point);
        if(jccellposition == null)
            return null;
        else
            return new JCCellPosition(jccellposition.row, jccellposition.column);
    }

    public JCCellPosition XYToCell(boolean flag, boolean flag1, Point point)
    {
        if(point == null)
            return null;
        int k = point.x;
        int l = point.y;
        Clip clip1 = getClip(k, l);
        if(clip1 == null)
            return null;
        JCCellPosition jccellposition = XYToCell(k, l);
        if(jccellposition == null)
            return null;
        int i = jccellposition.row;
        int j = jccellposition.column;
        k -= clip1.location().x;
        l -= clip1.location().y;
        if(clip1.type != 0 && flag1)
        {
            if(clip1.isVertical() && !table.isRowVisible(i))
                return null;
            if(clip1.isHorizontal() && !table.isColumnVisible(j))
                return null;
        }
        if(flag)
        {
            k -= table.columnPosition(j) - clip1.horiz_origin;
            l -= table.rowPosition(i) - clip1.vert_origin;
        } else
        {
            k += clip1.horiz_origin;
            l += clip1.vert_origin;
        }
        point.x = k;
        point.y = l;
        return jccellposition;
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
        when = mouseevent.getWhen();
        modifiers = mouseevent.getModifiers();
        clickCount = mouseevent.getClickCount();
        mouseDragged(mouseevent.getX(), mouseevent.getY(), mouseevent.getModifiers());
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
        when = mouseevent.getWhen();
        modifiers = mouseevent.getModifiers();
        clickCount = mouseevent.getClickCount();
        mouseMoved(mouseevent.getX(), mouseevent.getY(), mouseevent.getModifiers());
    }

    public void mousePressed(MouseEvent mouseevent)
    {
        when = mouseevent.getWhen();
        modifiers = mouseevent.getModifiers();
        clickCount = mouseevent.getClickCount();
        mousePressed(mouseevent.getX(), mouseevent.getY(), mouseevent.getModifiers(), mouseevent.getWhen(), mouseevent.getClickCount());
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
        when = mouseevent.getWhen();
        modifiers = mouseevent.getModifiers();
        clickCount = mouseevent.getClickCount();
        mouseReleased(mouseevent.getX(), mouseevent.getY(), mouseevent.getModifiers(), mouseevent.getWhen(), mouseevent.getClickCount());
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
        when = mouseevent.getWhen();
        modifiers = mouseevent.getModifiers();
        clickCount = mouseevent.getClickCount();
        mouseEntered(mouseevent.getX(), mouseevent.getY());
    }

    public void mouseExited(MouseEvent mouseevent)
    {
        when = mouseevent.getWhen();
        modifiers = mouseevent.getModifiers();
        clickCount = mouseevent.getClickCount();
        mouseExited(mouseevent.getX(), mouseevent.getY());
    }

    public void keyPressed(KeyEvent keyevent)
    {
        when = keyevent.getWhen();
        modifiers = keyevent.getModifiers();
        clickCount = 0;
        if(keyevent.isConsumed() && keyevent.getKeyCode() == 10)
        {
            return;
        } else
        {
            keyPressed(keyevent.getKeyCode(), keyevent.getModifiers());
            return;
        }
    }

    public void keyReleased(KeyEvent keyevent)
    {
        when = keyevent.getWhen();
        modifiers = keyevent.getModifiers();
        clickCount = 0;
        keyReleased(keyevent.getKeyCode(), keyevent.getModifiers());
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void mouseDragged(int i, int j, int k)
    {
        currentPoint = new Point(i, j);
        Point point = new Point(i, j);
        JCCellPosition jccellposition = XYToCellPosition(false, true, point);
        boolean flag = false;
        if(table.allow_cell_resize != 0)
            flag = resize(i, j, "MOVE");
        if(!flag)
        {
            if(lastPoint == null)
                lastPoint = new Point(i, j);
            if(jccellposition != null && initialCell != null)
            {
                if(autoscrolling)
                    stopAutoScroll(true);
                if(sorting)
                    selecting = false;
                else
                if(dragging)
                {
                    selecting = false;
                    doDrag(jccellposition);
                } else
                if(!jccellposition.equals(initialCell) && !selecting)
                {
                    selecting = false;
                    if(table.selection_policy == 2 || table.selection_policy == 3 || table.selection_policy == 1 && initialCell.row != -1 && initialCell.column != -1)
                    {
                        selecting = true;
                        select(initialPoint.x, initialPoint.y, "START");
                    }
                }
            } else
            if(jccellposition == null)
            {
                if(lastPoint.y != point.y && IS_AUTO_SCROLL_ROW(table))
                {
                    auto_scroll_vert = true;
                    if(j < table.clip.location().y)
                        auto_scroll_vert_direction = -1;
                    else
                    if(j > table.getRowPixelHeight(-1) + table.frozenRowHeight() + table.cell_height)
                        auto_scroll_vert_direction = 1;
                    else
                        auto_scroll_vert = false;
                    if(auto_scroll_initialCell.row == -1)
                        auto_scroll_vert = false;
                }
                if(lastPoint.x != point.x && IS_AUTO_SCROLL_COLUMN(table))
                {
                    auto_scroll_horiz = true;
                    if(i < table.clip.location().x)
                        auto_scroll_horiz_direction = -1;
                    else
                    if(i > table.getColumnPixelWidth(-1) + table.frozenColumnWidth() + table.cell_width)
                        auto_scroll_horiz_direction = 1;
                    else
                        auto_scroll_horiz = false;
                    if(auto_scroll_initialCell.column == -1)
                        auto_scroll_horiz = false;
                }
                if(auto_scroll_vert || auto_scroll_horiz)
                    startAutoScroll();
            } else
            if(initialCell == null && autoscrolling)
                stopAutoScroll(true);
            if(selecting && (table.selection_policy == 2 || table.selection_policy == 3))
                select(i, j, "EXTEND");
        }
        lastPoint = point;
    }

    public void mouseMoved(int i, int j, int k)
    {
        currentPoint = new Point(i, j);
        int j1 = DEFAULT_CURSOR;
        Point point = new Point(i, j);
        JCCellPosition jccellposition = XYToCell(false, true, point);
        if(jccellposition == null)
            return;
        int l = jccellposition.row;
        int i1 = jccellposition.column;
        if(Widget.getComponent(table, l, i1, false) != null)
        {
            setCursor(DEFAULT_CURSOR);
            return;
        }
        if(table.IS_TRAVERSABLE(l, i1))
            j1 = TRAV_CURSOR;
        else
            j1 = NONTRAV_CURSOR;
        if(!RESIZING_HEIGHT(table) && !RESIZING_WIDTH(table) && table.allow_cell_resize != 0)
        {
            int k1 = trackResizeCursor(i, j);
            if(k1 != NULL_CURSOR)
                j1 = k1;
        }
        if(j1 == TRAV_CURSOR && table.dataView.trackJCStringURL())
        {
            jclass.cell.CellRenderer cellrenderer = table.dataView.getCellRenderer(l, i1);
            if(cellrenderer instanceof JCStringCellRenderer)
            {
                Point point1 = new Point(i, j);
                XYToCell(true, true, point1);
                Object obj = table.dataView.getCellData(l, i1);
                JCString jcstring = null;
                if(obj instanceof JCString)
                    jcstring = (JCString)obj;
                else
                if(obj instanceof JCStringCellData)
                    jcstring = (JCString)((JCStringCellData)obj).getData();
                if(jcstring != null && jcstring.getURL(point1.x - table.margin_width, point1.y - table.margin_height) != null)
                    j1 = MOVE_CURSOR;
            }
        }
        if(!dragging)
            setCursor(j1);
    }

    public void mousePressed(int i, int j, int k, long l, int i1)
    {
        if(dragging)
            return;
        initialCell = XYToCellPosition(i, j);
        initialPoint = new Point(i, j);
        if(initialCell != null)
        {
            auto_scroll_initialCell = initialCell;
            auto_scroll_initialPoint = initialPoint;
        }
        boolean flag = false;
        if(table.allow_cell_resize != 0)
            flag = resize(i, j, "START");
        if(initialCell == null)
        {
            endDrag();
            return;
        }
        selecting = false;
        extended = false;
        menu = false;
        LabelTrigger labeltrigger = null;
        if(!flag && initialCell != null)
        {
            if(initialCell.row == -1)
                labeltrigger = table.getColumnTrigger(k);
            else
            if(initialCell.column == -1)
                labeltrigger = table.getRowTrigger(k);
            else
                labeltrigger = table.getCellTrigger(k);
            if(labeltrigger != null)
            {
                int j1 = labeltrigger.getAction();
                if(initialCell.row == -1 || initialCell.column == -1)
                {
                    if(j1 == 3)
                    {
                        menu = true;
                        if(initialCell.row == -1 && Select.isSelected(table, -1, initialCell.column) || initialCell.column == -1 && Select.isSelected(table, initialCell.row, -1))
                            return;
                        labeltrigger = null;
                    } else
                    {
                        if(initialCell.row == -1 && j1 == 1)
                        {
                            sorting = true;
                            return;
                        }
                        if(j1 == 2)
                        {
                            if(table.span.span_list.size() != 0)
                                return;
                            dragging = true;
                            if(table.editHandler.getCellEditor() != null)
                                table.editHandler.cancel();
                            table.clearSelectedCells();
                            doDrag(initialCell);
                            return;
                        }
                    }
                } else
                if(j1 == 3)
                {
                    menu = true;
                    labeltrigger = null;
                }
            }
        }
        sorting = false;
        dragging = false;
        if(!flag)
        {
            traverse(i, j, "PRESS");
            if(labeltrigger != null && labeltrigger.getAction() == 0)
            {
                if((table.selection_policy == 2 || table.selection_policy == 3) && (initialCell.row == -1 || initialCell.column == -1))
                {
                    select(i, j, "START");
                    selecting = true;
                }
                return;
            }
            if(isShift(k))
            {
                if(table.selection_policy == 2 || table.selection_policy == 3)
                {
                    select(i, j, "EXTEND");
                    selecting = true;
                }
                return;
            }
            if(isControl(k))
            {
                if(table.selection_policy == 3)
                {
                    select(i, j, "ADD");
                    selecting = true;
                }
                return;
            }
            if(table.mode == 1 || (table.selection_policy == 2 || table.selection_policy == 3) && (initialCell.row == -1 || initialCell.column == -1))
            {
                select(i, j, "START");
                selecting = true;
            }
        }
    }

    public void mouseReleased(int i, int j, int k, long l, int i1)
    {
        upPoint = new Point(i, j);
        Point point = new Point(i, j);
        JCCellPosition jccellposition = XYToCellPosition(false, true, point);
        initialEvent = new BaseInitialEvent(i, j, k);
        boolean flag = false;
        if(autoscrolling)
            stopAutoScroll(true);
        if(extended)
            return;
        boolean flag1 = false;
        flag1 = RESIZING_HEIGHT(table) || RESIZING_WIDTH(table);
        if(table.allow_cell_resize != 0)
            flag = resize(i, j, "END");
        if(!flag1)
            if(jccellposition != null)
            {
                if(sorting)
                {
                    setCursor(WAIT_CURSOR);
                    int j1 = table.dataView.getDataColumn(jccellposition.column);
                    int l1 = 0;
                    if(table.sort_column == j1 && table.sort_direction == 0)
                        l1 = 1;
                    JCSort.sortByColumn(table, j1, l1, null);
                    setCursor(TRAV_CURSOR);
                    sorting = false;
                } else
                if(dragging)
                {
                    int k1 = jccellposition.row;
                    if(jccellposition.row > initialCell.row)
                        k1++;
                    int i2 = jccellposition.column;
                    if(jccellposition.column > initialCell.column)
                        i2++;
                    endDrag();
                    if(initialCell.row == -1)
                    {
                        if(initialCell.column != jccellposition.column)
                            table.dragColumn(initialCell.column, i2);
                    } else
                    if(initialCell.row != jccellposition.row)
                        table.dragRow(initialCell.row, k1);
                } else
                if((jccellposition.row == -1 || jccellposition.column == -1) && table.selection_policy != 0)
                {
                    select(i, j, "END");
                    selecting = true;
                } else
                if(!selecting && !table.IS_TRAVERSABLE(jccellposition.row, jccellposition.column) && table.selection_policy != 0)
                {
                    select(i, j, "START");
                    select(i, j, "END");
                    selecting = true;
                } else
                if(selecting && table.selection_policy != 0)
                    select(i, j, "END");
                else
                if(initialCell != null && !menu && save_row == jccellposition.row && save_column == jccellposition.column && getEditable(jccellposition.row, jccellposition.column) && table.dataView.getCellEditor(jccellposition.row, jccellposition.column) != null)
                {
                    traverse(i, j, "POINTER");
                    select(i, j, "CANCEL");
                    return;
                }
            } else
            if(dragging)
                endDrag();
            else
            if(selecting)
                select(i, j, "END");
        sorting = false;
        dragging = false;
        if(jccellposition != null && !flag1 && initialCell != null && initialCell.row == jccellposition.row && initialCell.column == jccellposition.column && ((table.selection_policy == 2 && !isShift(k) || table.selection_policy == 3 && !isShift(k) && !isControl(k)) && (!menu || !table.isSelected(jccellposition.row, jccellposition.column)) || table.selection_policy == 1 && table.mode != 1 && initialCell.row != -1 && initialCell.column != -1))
        {
            if(!selecting)
                select(i, j, "START");
            select(i, j, "END");
            selecting = true;
        }
        if(menu)
        {
            menu = false;
            table.showPopupMenu(i, j);
        }
        setCursor(DEFAULT_CURSOR);
    }

    public void mouseEntered(int i, int j)
    {
        new Point(i, j);
    }

    public void mouseExited(int i, int j)
    {
        if(!RESIZING_HEIGHT(table) && !RESIZING_WIDTH(table))
            setCursor(DEFAULT_CURSOR);
    }

    public void keyPressed(int i, int j)
    {
        if(!table.repaint)
            return;
        int k = getCopyPasteKey(i, j);
        if(k != -999)
        {
            doCopyPaste(k);
            return;
        }
        if(table.editHandler.editor != null)
        {
            KeyModifier akeymodifier[] = table.editHandler.editor.getReservedKeys();
            if(akeymodifier != null && akeymodifier.length > 0)
            {
                for(int l = 0; l < akeymodifier.length; l++)
                    if(akeymodifier[l] != null && akeymodifier[l].match(i, j) && !containerReserved(akeymodifier[l]))
                        return;

            }
        }
        initialEvent = new BaseInitialEvent(i, j);
        if(table.edit_row >= 0 && table.edit_column >= 0)
        {
            CellEditor celleditor = table.dataView.getCellEditor(table.edit_row, table.edit_column);
            if(celleditor != null)
            {
                KeyModifier akeymodifier1[] = celleditor.getReservedKeys();
                if(akeymodifier1 != null && akeymodifier1.length > 0)
                {
                    for(int i1 = 0; i1 < akeymodifier1.length; i1++)
                        if(akeymodifier1[i1] != null && akeymodifier1[i1].match(i, j) && akeymodifier1[i1].canInitializeEdit && !containerReserved(akeymodifier1[i1]))
                        {
                            table.editHandler.traverse(table.edit_row, table.edit_column, true, initialEvent, null, true);
                            return;
                        }

                }
            }
        }
        if(i == 36)
        {
            if(isControl(j))
            {
                traverse(0, 0, "CTRLHOME");
                return;
            }
        } else
        {
            if(i == 34 && isControl(j))
            {
                traverse(0, 0, "CTRLPAGEDOWN");
                return;
            }
            if(i == 35)
            {
                if(isControl(j))
                {
                    traverse(0, 0, "CTRLEND");
                    return;
                }
            } else
            {
                if(i == 33 && isControl(j))
                {
                    traverse(0, 0, "CTRLPAGEUP");
                    return;
                }
                if(i == 34)
                {
                    traverse(0, 0, "PAGEDOWN");
                    return;
                }
                if(i == 33)
                {
                    traverse(0, 0, "PAGEUP");
                    return;
                }
                if(i == 27)
                {
                    resize(0, 0, "CANCEL");
                    select(0, 0, "CANCEL");
                    return;
                }
                if(i == 10 && table.editHandler.editor != null)
                {
                    table.editHandler.commit();
                    return;
                }
                if(table.edit_row >= 0)
                {
                    boolean flag = false;
                    if(i == 9)
                    {
                        if(isShift(j))
                            traverse(0, 0, "LEFT");
                        else
                            traverse(0, 0, "RIGHT");
                        boolean flag1 = true;
                        return;
                    }
                    if(i == 38)
                    {
                        traverse(0, 0, "UP");
                        return;
                    }
                    if(i == 40)
                    {
                        traverse(0, 0, "DOWN");
                        return;
                    }
                    if(i == 37)
                    {
                        traverse(0, 0, "LEFT");
                        return;
                    }
                    if(i == 39)
                    {
                        traverse(0, 0, "RIGHT");
                        return;
                    }
                    if(!table.hasText())
                    {
                        table.editHandler.traverse(table.edit_row, table.edit_column, false, initialEvent, null, true);
                        return;
                    }
                } else
                if(table.mode == 1)
                {
                    if(i == 40)
                    {
                        select(0, 0, "NEXTROW");
                        return;
                    }
                    if(i == 38)
                        select(0, 0, "PREVROW");
                }
            }
        }
    }

    public void keyReleased(int i, int j)
    {
    }

    public void setCursor(int i)
    {
        if(table.getTrackCursor() || table.cursor == -999)
        {
            for(int j = 0; j < table.clip_list.length; j++)
                table.clip_list[j].setCursor(Cursor.getPredefinedCursor(i));

        }
    }

    private int trackResizeCursor(int i, int j)
    {
        if(!table.getTrackCursor())
            return NONTRAV_CURSOR;
        Clip clip1 = getClip(i, j);
        int i1 = DEFAULT_CURSOR;
        Point point = new Point(i, j);
        JCCellPosition jccellposition = XYToCell(false, true, point);
        if(jccellposition == null)
            return NULL_CURSOR;
        i = point.x;
        j = point.y;
        int k = jccellposition.row;
        int l = jccellposition.column;
        if(getResizePosition(clip1, k, l, i, j))
            if(table.allow_cell_resize == 0)
            {
                if(table.IS_TRAVERSABLE(k, l))
                    i1 = TRAV_CURSOR;
                else
                    i1 = NONTRAV_CURSOR;
            } else
            if(resize_row != -999 && resize_column != -999)
                i1 = CORNER_CURSOR;
            else
            if(resize_row != -999)
                i1 = VERT_CURSOR;
            else
                i1 = HORIZ_CURSOR;
        if(i1 != DEFAULT_CURSOR)
            return i1;
        else
            return NULL_CURSOR;
    }

    boolean getResizePosition(Clip clip1, int i, int j, int k, int l)
    {
        if(clip1 == null || table.rows == 0 || table.columns == 0)
            return false;
        int i2 = i;
        int j2 = j;
        int l2 = Math.max(4, table.shadow_thickness);
        boolean flag = false;
        resize_column = resize_row = -999;
        if(table.span.doSpansExist() && table.span.find(i, j, span, null) != -999)
        {
            flag = true;
            i2 = span.end_row;
            j2 = span.end_column;
        }
        k += clip1.xOffset();
        l += clip1.yOffset();
        int k2 = 0;
        if(Table.isColumnLabel(i, j))
        {
            if((!flag || span.start_column == j) && Math.abs(k - table.columnPosition(j)) < l2)
            {
                if(j == 0 && (table.frozen_columns > 0) & (table.frozen_column_placement == 2))
                    cancelResize();
                else
                if(j == table.frozen_columns && table.frozen_column_placement == 2)
                {
                    cancelResize();
                } else
                {
                    int k1;
                    for(k1 = j - 1; k1 >= 0 && table.columnWidth(k1) == 0; k1--);
                    if(k1 >= 0)
                    {
                        resize_column = k1;
                        k2 |= 1;
                    }
                }
            } else
            if(j2 < table.columns - 1 && table.columnPosition(j2 + 1) - k < l2)
            {
                resize_column = j2;
                k2 |= 8;
            } else
            if(j2 == table.columns - 1 && (table.columnPosition(j2) + table.columnWidth(j2)) - k < l2)
            {
                resize_column = j2;
                k2 |= 8;
            } else
            if(table.column_label_height > 0 && Math.abs(table.columnLabelHeight() - l) < l2)
            {
                resize_row = i;
                k2 |= 4;
            }
        } else
        if(Table.isRowLabel(i, j))
        {
            if((!flag || span.start_row == i) && Math.abs(l - table.rowPosition(i)) < l2)
            {
                if(i == 0 && (table.frozen_rows > 0) & (table.frozen_row_placement == 6))
                    cancelResize();
                else
                if(i == table.frozen_rows && table.frozen_row_placement == 6)
                {
                    cancelResize();
                } else
                {
                    int i1;
                    for(i1 = i - 1; i1 >= 0 && table.rowHeight(i1) == 0; i1--);
                    if(i1 >= 0)
                    {
                        resize_row = i1;
                        k2 |= 2;
                    }
                }
            } else
            if(i2 < table.rows - 1 && table.rowPosition(i2 + 1) - l < l2)
            {
                resize_row = i2;
                k2 |= 4;
            } else
            if(i2 == table.rows - 1 && (table.rowPosition(i2) + table.rowHeight(i2)) - l < l2)
            {
                resize_row = i2;
                k2 |= 4;
            } else
            if(table.row_label_width > 0 && Math.abs(table.rowLabelWidth() - k) < l2)
            {
                resize_column = j;
                k2 |= 8;
            }
        } else
        if(!table.resize_by_labels_only)
        {
            if((!flag || span.start_column == j) && Math.abs(k - table.columnPosition(j)) < l2)
            {
                if(j == 0 && table.frozen_columns > 0 && table.frozen_column_placement == 2)
                    cancelResize();
                else
                if(j == table.frozen_columns && table.frozen_column_placement == 2 && table.row_label_width > 0)
                {
                    resize_column = -1;
                } else
                {
                    int l1;
                    for(l1 = j - 1; l1 >= 0 && table.columnWidth(l1) == 0; l1--);
                    if(l1 >= 0)
                    {
                        resize_column = l1;
                        k2 |= 1;
                    } else
                    if(table.row_label_width > 0 && isFrozen(table, i, j) && table.frozen_column_placement == 0)
                    {
                        resize_column = -1;
                        k2 |= 1;
                    }
                }
            } else
            if(j2 < table.columns - 1 && table.columnPosition(j2 + 1) - k < l2)
            {
                resize_column = j2;
                k2 |= 8;
            } else
            if(j2 == table.columns - 1 && (table.columnPosition(j2) + table.columnWidth(j2)) - k < l2)
            {
                resize_column = j2;
                k2 |= 8;
            }
            if((!flag || span.start_row == i) && Math.abs(l - table.rowPosition(i)) < l2)
            {
                if(i == 0 && (table.frozen_rows > 0) & (table.frozen_row_placement == 6))
                    cancelResize();
                else
                if(i == table.frozen_rows && table.frozen_row_placement == 6 && table.column_label_height > 0)
                {
                    resize_row = -1;
                } else
                {
                    int j1;
                    for(j1 = i - 1; j1 >= 0 && table.rowHeight(j1) == 0; j1--);
                    if(j1 >= 0)
                    {
                        resize_row = j1;
                        k2 |= 2;
                    } else
                    if(table.column_label_height > 0 && isFrozen(table, i, j))
                    {
                        resize_row = -1;
                        k2 |= 2;
                    }
                }
            } else
            if(i2 < table.rows - 1 && table.rowPosition(i2 + 1) - l < l2)
            {
                resize_row = i2;
                k2 |= 4;
            } else
            if(i2 == table.rows - 1 && (table.rowPosition(i2) + table.rowHeight(i2)) - l < l2)
            {
                resize_row = i2;
                k2 |= 4;
            }
        }
        if(table.allow_cell_resize == 4)
            resize_column = -999;
        if(table.allow_cell_resize == 2)
            resize_row = -999;
        return resize_row != -999 || resize_column != -999;
    }

    public static boolean isFrozen(Table table1, int i, int j)
    {
        return i < table1.frozen_rows || j < table1.frozen_columns;
    }

    private boolean resize(int i, int j, String s)
    {
        Clip clip1 = null;
        if(prevParam == null)
            prevParam = "";
        if(s.equals("START") && prevParam.equals("MOVE"))
            return true;
        if(s.equals("END") && prevParam.equals("END"))
            return true;
        if(s.equals("START") && prevParam.equals("START"))
            return true;
        prevParam = s;
        Clip clip2 = getClip(i, j);
        if(clip2 != null)
            clip = clip2;
        if(clip == null || table.allow_cell_resize == 0)
        {
            cancelResize();
            clip = null;
            return false;
        }
        if(!s.equals("START") && (i < 0 || j < 0))
        {
            int k2 = clip.location().x;
            int l2 = clip.location().y;
            if(RESIZING_WIDTH(table) && i < 0)
            {
                int k;
                for(k = 0; k < table.clip_list.length; k++)
                {
                    clip1 = table.clip_list[k];
                    if(clip1.location().x + clip1.size().width == k2)
                        break;
                }

                if(k < table.clip_list.length)
                {
                    i = (i + k2) - clip1.location().x;
                    clip = clip1;
                }
            } else
            if(RESIZING_HEIGHT(table) && j < 0)
            {
                int l;
                for(l = 0; l < table.clip_list.length; l++)
                {
                    clip1 = table.clip_list[l];
                    if(clip1.location().y + clip1.size().height == l2)
                        break;
                }

                if(l < table.clip_list.length)
                {
                    j = (j + l2) - clip1.location().y;
                    clip = clip1;
                }
            }
        }
        Point point = new Point(i, j);
        JCCellPosition jccellposition = XYToCell(false, true, point);
        if(jccellposition == null)
        {
            jccellposition = oldPos;
            if(jccellposition == null)
                return false;
        }
        oldPos = jccellposition;
        if(jccellposition == null)
            return true;
        int i1 = jccellposition.row;
        int j1 = jccellposition.column;
        i = point.x;
        j = point.y;
        if(!s.equals("START") && !RESIZING_HEIGHT(table) && !RESIZING_WIDTH(table))
        {
            setCursor(DEFAULT_CURSOR);
            cancelResize();
            clip = null;
            return false;
        }
        if((RESIZING_HEIGHT(table) || RESIZING_WIDTH(table)) && (s.equals("CANCEL") || s.equals("END")))
            setCursor(DEFAULT_CURSOR);
        if(s.equals("START"))
        {
            clip.suspend_paint = true;
            table.resize_column = table.resize_row = -999;
            if(table.allow_cell_resize == 0)
            {
                cancelResize();
                clip = null;
                return false;
            }
            if(!getResizePosition(clip, i1, j1, i, j))
            {
                cancelResize();
                clip = null;
                return false;
            }
            JCResizeCellEvent jcresizecellevent = new JCResizeCellEvent(table, 1, resize_row, resize_column, s, CURRENT_WIDTH(table, resize_column), CURRENT_HEIGHT(table, resize_row));
            table.fireJCResizeCellEvent(jcresizecellevent);
            if(!jcresizecellevent.doit)
            {
                cancelResize();
                clip = null;
                return false;
            }
            table.resize_row = resize_row;
            table.resize_column = resize_column;
            if(RESIZING_WIDTH(table) && RESIZING_HEIGHT(table))
                setCursor(CORNER_CURSOR);
            else
            if(RESIZING_WIDTH(table))
                setCursor(HORIZ_CURSOR);
            else
            if(RESIZING_HEIGHT(table))
                setCursor(VERT_CURSOR);
            drawLine(false, currentPoint.x, currentPoint.y);
        } else
        if(s.equals("MOVE"))
        {
            if(RESIZING_HEIGHT(table))
                ROW_POS(table, clip);
            if(RESIZING_WIDTH(table))
                COLUMN_POS(table, clip);
            drawLine(true, 0, 0);
            drawLine(false, currentPoint.x, currentPoint.y);
        } else
        if(s.equals("CANCEL"))
        {
            clip.suspend_paint = false;
            drawLine(true, 0, 0);
            cancelResize();
            clip = null;
        } else
        if(s.equals("END"))
        {
            clip.suspend_paint = false;
            int l1;
            int k1 = l1 = 0;
            drawLine(true, 0, 0);
            int j2;
            if(RESIZING_HEIGHT(table))
            {
                k1 = initialPoint.y - upPoint.y;
                j2 = CURRENT_HEIGHT(table, i1) - k1;
                j2 = Math.max(j2, table.shadow_thickness + 4);
                k1 = CURRENT_HEIGHT(table, i1) - j2;
            } else
            {
                j2 = CURRENT_HEIGHT(table, i1);
            }
            int i2;
            if(RESIZING_WIDTH(table))
            {
                l1 = initialPoint.x - upPoint.x;
                i2 = CURRENT_WIDTH(table, j1) - l1;
                i2 = Math.max(i2, table.shadow_thickness + 4);
                l1 = CURRENT_WIDTH(table, j1) - i2;
            } else
            {
                i2 = CURRENT_WIDTH(table, j1);
            }
            if(Math.abs(k1) < 4 && Math.abs(l1) < 4)
            {
                if(table.resizeCellListeners != null)
                {
                    JCResizeCellEvent jcresizecellevent1 = new JCResizeCellEvent(table, 2, table.resize_row, table.resize_column, "CANCEL", CURRENT_WIDTH(table, table.resize_column), CURRENT_HEIGHT(table, table.resize_row));
                    jcresizecellevent1.new_column_width = CURRENT_WIDTH(table, table.resize_column);
                    jcresizecellevent1.new_row_height = CURRENT_HEIGHT(table, table.resize_row);
                    table.fireJCResizeCellEvent(jcresizecellevent1);
                }
                cancelResize();
                clip = null;
                return false;
            }
            JCResizeCellEvent jcresizecellevent2 = new JCResizeCellEvent(table, 2, table.resize_row, table.resize_column, s, CURRENT_WIDTH(table, table.resize_column), CURRENT_HEIGHT(table, table.resize_row));
            jcresizecellevent2.new_row_height = RESIZING_HEIGHT(table) ? j2 : -999;
            jcresizecellevent2.new_column_width = RESIZING_WIDTH(table) ? i2 : -999;
            table.fireJCResizeCellEvent(jcresizecellevent2);
            if(!jcresizecellevent2.doit)
            {
                cancelResize();
                clip = null;
                return false;
            }
            j2 = jcresizecellevent2.new_row_height;
            i2 = jcresizecellevent2.new_column_width;
            if(RESIZING_HEIGHT(table) || RESIZING_WIDTH(table))
            {
                int i3 = 0;
                table.set_property = false;
                if(RESIZING_HEIGHT(table))
                {
                    if(table.resize_row != -1 && table.getResizeEven())
                        table.setPixelHeight(-997, j2);
                    else
                        table.setPixelHeight(table.resize_row, j2);
                    i3 |= table.property_flags;
                }
                if(RESIZING_WIDTH(table))
                {
                    if(table.resize_column != -1 && table.getResizeEven())
                        table.setPixelWidth(-997, i2);
                    else
                        table.setPixelWidth(table.resize_column, i2);
                    i3 |= table.property_flags;
                }
                table.set_property = true;
                table.setProperty(i3);
            }
            cancelResize();
            clip = null;
            setCursor(DEFAULT_CURSOR);
        }
        return true;
    }

    private void cancelResize()
    {
        if(resize_column != -999 || resize_row != -999)
        {
            table.resize_column = table.resize_row = -999;
            resize_column = resize_row = -999;
            setCursor(DEFAULT_CURSOR);
        }
    }

    private void drawLine(boolean flag, int i, int j)
    {
        boolean flag1 = JCEnvironment.isJBuilder();
        if(flag && flag1)
            return;
        if(!flag)
        {
            x_save = i;
            y_save = j;
        }
        if(i == -999 || j == -999 || table.clip_list == null)
            return;
        if(flag1)
            table.repaint();
        if(RESIZING_HEIGHT(table))
        {
            for(int k = 0; k < table.clip_list.length; k++)
            {
                Clip clip1 = table.clip_list[k];
                j = y_save - clip1.location().y;
                Graphics g = clip1.getGraphics();
                if(!flag1)
                    g.setXORMode(Color.lightGray);
                g.drawLine(0, j, clip1.size().width, j);
                g.dispose();
            }

        }
        if(RESIZING_WIDTH(table))
        {
            for(int l = 0; l < table.clip_list.length; l++)
            {
                Clip clip2 = table.clip_list[l];
                i = x_save - clip2.location().x;
                Graphics g1 = clip2.getGraphics();
                if(!flag1)
                    g1.setXORMode(Color.lightGray);
                g1.drawLine(i, 0, i, clip2.size().height);
                g1.dispose();
            }

        }
    }

    boolean select(int i, int j, String s)
    {
        if(!table.allow_select)
        {
            table.allow_select = true;
            table.select_last_row = -1000;
            return false;
        }
        if(!table.repaint)
            return false;
        if(s.equals("CANCEL"))
        {
            Select.deselectAll(table);
            return false;
        }
        if(s.equals("END"))
            table.editHandler.commit();
        if(RESIZING_HEIGHT(table) || RESIZING_WIDTH(table))
            return false;
        if(table.selection_policy == 0)
            return false;
        int k = -999;
        int l = -999;
        String s1 = new String(s);
        if(s.equals("CURRENT"))
        {
            k = table.edit_row;
            l = table.edit_column;
            s = "START";
        } else
        {
            if((s.equals("EXTEND") || s.equals("END")) && table.select_last_row == -1000)
                return false;
            if(s.equals("DOWN") || s.equals("UP"))
            {
                if(table.rows == 0)
                    return false;
                if(table.selected_cells.size() > 0)
                {
                    JCCellRange jccellrange = (JCCellRange)table.selected_cells.elementAt(table.selected_cells.size() - 1);
                    if(s.equals("DOWN"))
                    {
                        k = jccellrange.end_row + 1;
                        if(k == table.rows - 1)
                            return false;
                        l = jccellrange.end_column;
                        s = "EXTEND";
                    } else
                    if(s.equals("UP"))
                    {
                        k = jccellrange.end_row - 1;
                        if(k < 0)
                            return false;
                        l = jccellrange.end_column;
                        s = "EXTEND";
                    }
                }
            } else
            if(!s.equals("NEXTROW") && !s.equals("PREVROW"))
            {
                Point point = new Point(i, j);
                JCCellPosition jccellposition;
                if((jccellposition = XYToCell(true, false, point)) == null && !s.equals("END"))
                    return false;
                if(jccellposition != null)
                {
                    k = jccellposition.row;
                    l = jccellposition.column;
                }
            } else
            {
                int k1 = table.rows;
                int l1 = -1;
                if(table.rows == 0)
                    return false;
                if(table.selected_cells.size() > 0)
                {
                    for(int i1 = 0; i1 < table.selected_cells.size(); i1++)
                    {
                        JCCellRange jccellrange1 = (JCCellRange)table.selected_cells.elementAt(i1);
                        k1 = Math.min(k1, jccellrange1.start_row);
                        l1 = Math.max(l1, jccellrange1.end_row);
                    }

                }
                if(s.equals("PREVROW"))
                {
                    if(k1 == 0)
                        return false;
                    k = k1 - 1;
                } else
                if(s.equals("NEXTROW"))
                {
                    if(l1 == table.rows - 1)
                        return false;
                    k = l1 + 1;
                }
                l = -998;
                s = "START";
            }
        }
        int j1 = table.selection_policy;
        JCSelectEvent jcselectevent = null;
        if(table.selectListeners != null)
            jcselectevent = new JCSelectEvent(table, 1, k, l, s, when, modifiers, clickCount);
        if((Table.isColumnLabel(k, l) || Table.isRowLabel(k, l) && !IS_LISTMODE(table)) && (j1 == 1 || IS_LISTMODE(table)) && IS_LISTMODE(table) && !s.equals("START") && s.equals("END"))
        {
            exit(s, s1, jcselectevent);
            return true;
        }
        if(IS_LISTMODE(table) && k != -1)
        {
            l = -998;
            if(jcselectevent != null)
                jcselectevent.column = -998;
        }
        if(table.getSelectionMode() == 1 && k != -1)
        {
            l = -998;
            if(jcselectevent != null)
                jcselectevent.column = -998;
        }
        if(table.getSelectionMode() == 2 && l != -1)
        {
            k = -998;
            if(jcselectevent != null)
                jcselectevent.row = -998;
        }
        if(s.equals("START") || s.equals("ADD") && j1 == 3 || s.equals("EXTEND") && IS_LISTMODE(table) && j1 == 1)
        {
            if(s.equals("EXTEND") && (k == table.select_last_row || table.select_start_row == -1001))
                return false;
            if(s.equals("START") || j1 == 1)
            {
                if(IS_LISTMODE(table) && j1 == 1 && k == table.select_last_row && table.select_start_row != -1)
                {
                    table.select_start_row = -1001;
                    table.select_last_row = -999;
                    return false;
                }
                if(table.getSelectedCells() != null && table.getSelectedCells().size() != 0)
                {
                    callCallback(true, 2, jcselectevent);
                    if(jcselectevent == null || jcselectevent.doit)
                        Select.deselectAll(table);
                }
            }
            callCallback(true, 1, jcselectevent);
            if(jcselectevent != null && !jcselectevent.doit)
            {
                table.allow_select = false;
                return false;
            }
            if(!table.makeVisible(k, l))
                return false;
            if(jcselectevent != null)
            {
                if(jcselectevent.row == -998 || jcselectevent.row == -997)
                    k = -1;
                if(jcselectevent.column == -998 || jcselectevent.column == -997)
                    l = -1;
            }
            if(jcselectevent == null || jcselectevent.doit)
                Select.createSelectedRange(table, k, l);
            table.select_start_row = table.select_last_row = k;
            table.select_start_column = table.select_last_column = l;
        } else
        if(s.equals("EXTEND") && (j1 == 2 || j1 == 3))
        {
            if(table.selected_cells.size() == 0)
                return false;
            if(k == table.select_last_row && l == table.select_last_column)
                return false;
            if(!table.makeVisible(k, l))
                return false;
            if(jcselectevent != null)
                jcselectevent.stage = 1;
            callCallback(true, 1, jcselectevent);
            if(jcselectevent != null && !jcselectevent.doit)
                return false;
            if(jcselectevent != null)
            {
                if(jcselectevent.row == -998 || jcselectevent.row == -997)
                    k = -1;
                if(jcselectevent.column == -998 || jcselectevent.column == -997)
                    l = -1;
            }
            Select.setSelectedRange(table, k, l);
            table.select_last_row = k;
            table.select_last_column = l;
        }
        exit(s, s1, jcselectevent);
        return true;
    }

    private void callCallback(boolean flag, int i, JCSelectEvent jcselectevent)
    {
        if(jcselectevent == null)
            return;
        if(flag)
            jcselectevent.type = 1;
        else
            jcselectevent.type = 2;
        jcselectevent.state_change = i;
        table.fireJCSelectEvent(jcselectevent);
    }

    private void exit(String s, String s1, JCSelectEvent jcselectevent)
    {
        if(s.equals("END") || s1.equals("CURRENT") || s1.equals("PREVROW") || s1.equals("NEXTROW"))
        {
            Select.cleanup(table);
            if(jcselectevent != null)
                callCallback(false, jcselectevent.state_change, jcselectevent);
        }
    }

    static JCCellPosition findTraversableCell(Table table1, String s, int i, int j)
    {
        boolean flag = false;
        if(!table1.traversable)
            return null;
        if(i < 0 || j < 0)
            return null;
        int k = i;
        int l = j;
        if(s.equals("RIGHT"))
            for(l = j + 1; k != i || l != j; l = 0)
            {
                while(l < table1.columns) 
                {
                    if(k == i && l == j)
                        if(flag || table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);
                        else
                            return null;
                    if(flag = table1.isTraversable(k, l))
                        if(flag || table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);
                        else
                            return null;
                    l++;
                }
                if(k == table1.rows - 1)
                    k = 0;
                else
                    k++;
            }

        else
        if(s.equals("LEFT"))
            for(l = j - 1; k != i || l != j; l = table1.columns - 1)
            {
                while(l >= 0) 
                {
                    if(k == i && l == j)
                        if(flag || table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);
                        else
                            return null;
                    if(flag = table1.isTraversable(k, l))
                        if(flag || table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);
                        else
                            return null;
                    l--;
                }
                if(k == 0)
                    k = table1.rows - 1;
                else
                    k--;
            }

        else
        if(s.equals("DOWN"))
            for(k = i + 1; k != i || l != j; k = 0)
            {
                while(k < table1.rows) 
                {
                    if(k == i && l == j)
                        if(flag || table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);
                        else
                            return null;
                    if(flag = table1.isTraversable(k, l))
                        if(flag || table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);
                        else
                            return null;
                    k++;
                }
                if(l == table1.columns - 1)
                    l = 0;
                else
                    l++;
            }

        else
        if(s.equals("UP"))
        {
            for(k = i - 1; k != i || l != j; k = table1.rows - 1)
            {
                while(k >= 0) 
                {
                    if(k == i && l == j)
                        if(flag || table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);
                        else
                            return null;
                    if(flag = table1.isTraversable(k, l))
                        if(flag || table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);
                        else
                            return null;
                    k--;
                }
                if(l == 0)
                    l = table1.columns - 1;
                else
                    l--;
            }

        } else
        {
            if(s.equals("CTRLHOME"))
            {
                k = 0;
                l = 0;
                if(table1.isTraversable(k, l))
                    return new JCCellPosition(k, l);
                for(k = 0; k < table1.rows; k++)
                    for(l = 0; l < table1.columns; l++)
                        if(table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);


                return null;
            }
            if(s.equals("PAGEUP"))
            {
                JCCellRange jccellrange = table1.getVisibleCells();
                k = table1.edit_row - (jccellrange.end_row - jccellrange.start_row);
                l = table1.edit_column;
                if(table1.isTraversable(k, l))
                    return new JCCellPosition(k, l);
                for(k = 0; k < table1.rows; k++)
                    if(table1.isTraversable(k, l))
                        return new JCCellPosition(k, l);

                return null;
            }
            if(s.equals("CTRLPAGEUP"))
            {
                k = 0;
                l = table1.edit_column;
                if(table1.isTraversable(k, l))
                    return new JCCellPosition(k, l);
                for(k = 0; k < table1.rows; k++)
                    if(table1.isTraversable(k, l))
                        return new JCCellPosition(k, l);

                return null;
            }
            if(s.equals("PAGEDOWN"))
            {
                JCCellRange jccellrange1 = table1.getVisibleCells();
                k = table1.edit_row + (jccellrange1.end_row - jccellrange1.start_row);
                l = table1.edit_column;
                if(table1.isTraversable(k, l))
                    return new JCCellPosition(k, l);
                for(k = 0; k < table1.rows; k++)
                    if(table1.isTraversable(k, l))
                        return new JCCellPosition(k, l);

                return null;
            }
            if(s.equals("CTRLEND"))
            {
                int i1 = table1.dataView.getNumRows();
                int k1 = table1.dataView.getNumColumns();
                k = i1 - 1;
                l = k1 - 1;
                if(table1.isTraversable(k, l))
                    return new JCCellPosition(k, l);
                for(k = i1 - 1; k >= 0; k--)
                    for(l = k1 - 1; l >= 0; l--)
                        if(table1.isTraversable(k, l))
                            return new JCCellPosition(k, l);


                return null;
            }
            if(s.equals("CTRLPAGEDOWN"))
            {
                int j1 = table1.dataView.getNumRows();
                k = j1 - 1;
                l = table1.edit_column;
                if(table1.isTraversable(k, l))
                    return new JCCellPosition(k, l);
                for(k = j1 - 1; k >= 0; k--)
                    if(table1.isTraversable(k, l))
                        return new JCCellPosition(k, l);

                return null;
            }
        }
        if(flag || table1.isTraversable(k, l))
            return new JCCellPosition(k, l);
        else
            return null;
    }

    boolean traverse(int i, int j, String s)
    {
        if(!table.traversable)
            return false;
        Table table1 = table;
        int k = table1.edit_row;
        int l = table1.edit_column;
        if(table.resize_row != -999 || table.resize_column != -999)
            return false;
        if(s.equals("POINTER") || s.equals("PRESS"))
        {
            Point point = new Point(i, j);
            JCCellPosition jccellposition;
            if((jccellposition = XYToCell(true, true, point)) == null)
                return false;
            k = jccellposition.row;
            l = jccellposition.column;
            if(Table.isLabel(k, l))
                return true;
            if(!table.editHandler.commit())
                return false;
            if(!s.equals("PRESS"))
            {
                table.editHandler.traverse(k, l, false, initialEvent, s, !menu);
            } else
            {
                if(table.traverseCellListeners != null)
                {
                    JCTraverseCellEvent jctraversecellevent1 = new JCTraverseCellEvent(table, save_row, save_column, k, l, "POINTER");
                    table.fireJCTraverseCellEvent(jctraversecellevent1);
                    k = jctraversecellevent1.next_row;
                    l = jctraversecellevent1.next_column;
                }
                if(table.isTraverseAllowed(k, l))
                {
                    save_row = k;
                    save_column = l;
                    table.editHandler.traverse(k, l, false, initialEvent, s, false);
                }
            }
        } else
        {
            JCCellPosition jccellposition1;
            if((jccellposition1 = findTraversableCell(table, s, k, l)) == null)
                return false;
            if(!table.traverseCycle)
            {
                if(s.equals("RIGHT") && (k != jccellposition1.row || jccellposition1.column < l))
                    return false;
                if(s.equals("LEFT") && (k != jccellposition1.row || jccellposition1.column > l))
                    return false;
                if(s.equals("UP") && (jccellposition1.row > k || l != jccellposition1.column))
                    return false;
                if(s.equals("DOWN") && (jccellposition1.row < k || l != jccellposition1.column))
                    return false;
            }
            k = jccellposition1.row;
            l = jccellposition1.column;
            if(!table.editHandler.commit())
                return false;
            if(table.traverseCellListeners != null)
            {
                JCTraverseCellEvent jctraversecellevent = new JCTraverseCellEvent(table, table1.edit_row, table1.edit_column, k, l, s);
                table.fireJCTraverseCellEvent(jctraversecellevent);
                k = jctraversecellevent.next_row;
                l = jctraversecellevent.next_column;
            }
            if((k != table1.edit_row || l != table1.edit_column) && table.isTraverseAllowed(k, l))
                table.editHandler.traverse(k, l, false, initialEvent, s, true);
        }
        return true;
    }

    private static boolean IS_AUTO_SCROLL_ROW(Table table1)
    {
        int i = table1.getAutoScroll();
        return i == 1 || i == 3;
    }

    private static boolean IS_AUTO_SCROLL_COLUMN(Table table1)
    {
        int i = table1.getAutoScroll();
        return i == 2 || i == 3;
    }

    private static int COLUMN(Table table1, int i)
    {
        if(RESIZING_WIDTH(table1))
            return table1.resize_column;
        else
            return i;
    }

    private static int ROW(Table table1, int i)
    {
        if(RESIZING_HEIGHT(table1))
            return table1.resize_row;
        else
            return i;
    }

    private static int CURRENT_WIDTH(Table table1, int i)
    {
        if(i == -999)
            return -999;
        if(COLUMN(table1, i) == -1)
            return table1.rowLabelWidth();
        else
            return table1.columnWidth(COLUMN(table1, i));
    }

    private static int CURRENT_HEIGHT(Table table1, int i)
    {
        if(i == -999)
            return -999;
        if(ROW(table1, i) == -1)
            return table1.columnLabelHeight();
        else
            return table1.rowHeight(ROW(table1, i));
    }

    private static int ROW_POS(Table table1, Clip clip1)
    {
        if(table1.resize_row != -1)
            return table1.rowPosition(table1.resize_row) - clip1.yOffset();
        else
            return 0;
    }

    private static int COLUMN_POS(Table table1, Clip clip1)
    {
        if(table1.resize_column != -1)
            return table1.columnPosition(table1.resize_column) - clip1.xOffset();
        else
            return 0;
    }

    private static boolean RESIZING_WIDTH(Table table1)
    {
        return table1.resize_column != -999;
    }

    private static boolean RESIZING_HEIGHT(Table table1)
    {
        return table1.resize_row != -999;
    }

    private static boolean IS_LISTMODE(Table table1)
    {
        return table1.mode == 1;
    }

    private boolean getEditable(int i, int j)
    {
        if(!table.dataView.getEditable())
            return false;
        else
            return table.getEditable(i, j);
    }

    private void doDrag(JCCellPosition jccellposition)
    {
        int i = initialCell.row;
        int j = initialCell.column;
        setCursor(MOVE_CURSOR);
        if(initialCell.row == -1)
        {
            j = jccellposition.column;
            if(j == -1)
                return;
            if(jccellposition.column > initialCell.column)
                j++;
        } else
        if(initialCell.column == -1)
        {
            i = jccellposition.row;
            if(i == -1)
                return;
            if(jccellposition.row > initialCell.row)
                i++;
        }
        for(int k = 0; k < table.clip_list.length; k++)
        {
            Clip clip1 = table.clip_list[k];
            clip1.beginDrag(initialCell.row, initialCell.column, i, j);
        }

    }

    private void endDrag()
    {
        dragging = false;
        for(int i = 0; i < table.clip_list.length; i++)
        {
            Clip clip1 = table.clip_list[i];
            clip1.endDrag();
        }

        setCursor(TRAV_CURSOR);
    }

    private void startAutoScroll()
    {
        if((auto_scroll_vert || auto_scroll_horiz) && auto_scroll_thread == null)
        {
            auto_scroll_thread = new Thread(this, "AUTOSCROLL");
            autoscrolling = true;
            auto_scroll_thread.start();
        }
    }

    private void stopAutoScroll(boolean flag)
    {
        if(flag || auto_scroll_horiz_direction == 0 && auto_scroll_vert_direction == 0)
        {
            if(auto_scroll_thread != null)
            {
                auto_scroll_thread.stop();
                auto_scroll_thread = null;
            }
            resetAutoScrolling();
        }
    }

    private void resetAutoScrolling()
    {
        autoscrolling = false;
        auto_scroll_horiz = false;
        auto_scroll_vert = false;
        auto_scroll_horiz_direction = 0;
        auto_scroll_vert_direction = 0;
    }

    public void run()
    {
        try
        {
            while(autoscrolling) 
            {
                Thread.sleep(100L);
                if(auto_scroll_vert)
                {
                    TableScrollbar tablescrollbar = table.getVertSB();
                    JCAdjustable jcadjustable = tablescrollbar.getAdjustable();
                    int i = jcadjustable.getValue() + auto_scroll_vert_direction * 20;
                    int k = jcadjustable.getMaximum() - jcadjustable.getVisibleAmount();
                    if(i >= k)
                    {
                        tablescrollbar.setValue(k);
                        auto_scroll_vert_direction = 0;
                        stopAutoScroll(false);
                        return;
                    }
                    if(i < 0)
                    {
                        tablescrollbar.setValue(0);
                        auto_scroll_vert_direction = 0;
                        stopAutoScroll(false);
                        return;
                    }
                    tablescrollbar.setValue(i);
                    int i1 = lastPoint.x;
                    if(i1 < 0)
                        i1 = 0;
                    if(selecting)
                    {
                        if(auto_scroll_vert_direction == 1)
                        {
                            if(table.getFrozenRowPlacement() == 0)
                                select(i1, table.cell_height + table.frozenRowHeight(), "EXTEND");
                            else
                                select(i1, table.cell_height, "EXTEND");
                        } else
                        if(table.getFrozenRowPlacement() == 0)
                            select(i1, table.getRowPixelHeight(-1) + table.frozenRowHeight(), "EXTEND");
                        else
                            select(i1, table.getRowPixelHeight(-1), "EXTEND");
                    } else
                    if(dragging && auto_scroll_initialCell.column == -1)
                        if(auto_scroll_vert_direction == 1)
                        {
                            int k1 = XYToCell(i1, table.cell_height + table.frozenRowHeight()).row;
                            doDrag(new JCCellPosition(k1, auto_scroll_initialCell.column));
                        } else
                        {
                            int l1 = XYToCell(i1, table.getRowPixelHeight(-1)).row;
                            doDrag(new JCCellPosition(l1, auto_scroll_initialCell.column));
                        }
                }
                if(auto_scroll_horiz)
                {
                    TableScrollbar tablescrollbar1 = table.getHorizSB();
                    JCAdjustable jcadjustable1 = tablescrollbar1.getAdjustable();
                    int j = jcadjustable1.getValue() + auto_scroll_horiz_direction * 20;
                    int l = jcadjustable1.getMaximum() - jcadjustable1.getVisibleAmount();
                    if(j >= l)
                    {
                        tablescrollbar1.setValue(l);
                        auto_scroll_horiz_direction = 0;
                        stopAutoScroll(false);
                        return;
                    }
                    if(j < 0)
                    {
                        tablescrollbar1.setValue(0);
                        auto_scroll_horiz_direction = 0;
                        stopAutoScroll(false);
                        return;
                    }
                    tablescrollbar1.setValue(j);
                    int j1 = lastPoint.y;
                    if(j1 < 0)
                        j1 = 0;
                    if(selecting)
                    {
                        if(auto_scroll_horiz_direction == 1)
                        {
                            if(table.getFrozenColumnPlacement() == 0)
                                select(table.cell_width + table.frozenColumnWidth(), j1, "EXTEND");
                            else
                                select(table.cell_width, j1, "EXTEND");
                        } else
                        if(table.getFrozenColumnPlacement() == 0)
                            select(table.getColumnPixelWidth(-1) + table.frozenColumnWidth(), j1, "EXTEND");
                        else
                            select(table.getColumnPixelWidth(-1), j1, "EXTEND");
                    } else
                    if(dragging && auto_scroll_initialCell.row == -1)
                        if(auto_scroll_horiz_direction == 1)
                        {
                            int i2 = XYToCell(table.cell_width + table.frozenColumnWidth(), j1).column;
                            doDrag(new JCCellPosition(auto_scroll_initialCell.row, i2));
                        } else
                        {
                            int j2 = XYToCell(table.getColumnPixelWidth(-1), j1).column;
                            doDrag(new JCCellPosition(auto_scroll_initialCell.row, j2));
                        }
                }
            }
            return;
        }
        catch(InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void debugMessage(int i, String s)
    {
        if(!DEBUG_MODE)
            return;
        switch(i)
        {
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        default:
            return;
        }
    }

    private boolean containerReserved(KeyModifier keymodifier)
    {
        if(keymodifier.match(27, 0))
            return true;
        if(keymodifier.match(9, 0))
            return true;
        return keymodifier.match(9, 1);
    }

    void dispose()
    {
        table = null;
    }

    int getCopyPasteKey(int i, int j)
    {
        int k = table.findActionKey(i, j);
        if(k == 0 || k == 1)
            return k;
        else
            return -999;
    }

    void doCopyPaste(int i)
    {
        if(i == 0)
        {
            JCVector jcvector = (JCVector)table.selected_cells.clone();
            JCCellRange jccellrange;
            if(jcvector.size() != 0)
                jccellrange = (JCCellRange)jcvector.elementAt(0);
            else
                jccellrange = new JCCellRange(table.edit_row, table.edit_column, table.edit_row, table.edit_column);
            StringCellRangeFlavor stringcellrangeflavor = new StringCellRangeFlavor(table, jccellrange);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringcellrangeflavor, stringcellrangeflavor);
            return;
        }
        if(i == 1)
        {
            Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this);
            if(transferable != null)
                try
                {
                    DataFlavor adataflavor[] = transferable.getTransferDataFlavors();
                    for(int j = 0; j < adataflavor.length; j++)
                    {
                        if(adataflavor[j].getHumanPresentableName().equals("CellRange"))
                        {
                            JCVector jcvector1 = (JCVector)transferable.getTransferData(adataflavor[j]);
                            loadPasteData(jcvector1);
                            return;
                        }
                        if(adataflavor[j].getHumanPresentableName().equals("Unicode String"))
                        {
                            StringTokenizer stringtokenizer = new StringTokenizer((String)transferable.getTransferData(adataflavor[j]), "\n", false);
                            JCVector jcvector2 = new JCVector();
                            JCVector jcvector3;
                            for(; stringtokenizer.hasMoreElements(); jcvector2.addElement(jcvector3))
                            {
                                String s = (String)stringtokenizer.nextElement();
                                jcvector3 = JCUtilConverter.toVector(null, s, '\t', false);
                                if(jcvector3 == null)
                                    jcvector3 = new JCVector();
                            }

                            loadPasteData(jcvector2);
                            return;
                        }
                    }

                    return;
                }
                catch(Exception _ex)
                {
                    return;
                }
        }
    }

    void loadPasteData(JCVector jcvector)
    {
        JCCellRange jccellrange;
        if(table.getSelectedCells().size() != 0)
            jccellrange = (JCCellRange)table.getSelectedCells().elementAt(0);
        else
            jccellrange = new JCCellRange(table.edit_row, table.edit_column, table.getNumRows() - 1, table.getNumColumns() - 1);
        if(jccellrange.end_row == 0x7fffffff)
        {
            jccellrange.start_row = -1;
            jccellrange.end_row = table.getNumRows() - 1;
        }
        if(jccellrange.end_column == 0x7fffffff)
        {
            jccellrange.start_column = -1;
            jccellrange.end_column = table.getNumColumns() - 1;
        }
        if(jccellrange != null)
        {
            int i = jcvector.size();
            if(i == 0)
                return;
            int j = ((JCVector)jcvector.elementAt(0)).size();
            int k = jccellrange.start_row;
            for(int l = 0; k <= jccellrange.end_row && l < i; l++)
            {
                JCVector jcvector1 = (JCVector)jcvector.elementAt(l);
                int i1 = jccellrange.start_column;
                for(int j1 = 0; i1 <= jccellrange.end_column && j1 < j; j1++)
                {
                    if(i1 == -1 || k == -1)
                    {
                        if(table.dataView.getDataSource() instanceof VectorDataSource)
                            if(i1 == -1)
                                ((VectorDataSource)table.dataView.getDataSource()).setRowLabel(k, jcvector1.elementAt(j1));
                            else
                            if(k == -1)
                                ((VectorDataSource)table.dataView.getDataSource()).setColumnLabel(i1, jcvector1.elementAt(j1));
                    } else
                    {
                        table.dataView.setCellData(jcvector1.elementAt(j1), k, i1);
                    }
                    i1++;
                }

                k++;
            }

        }
    }

    static int NULL_CURSOR = -1;
    static int DEFAULT_CURSOR;
    static int TRAV_CURSOR = 2;
    static int NONTRAV_CURSOR = 1;
    static int CORNER_CURSOR = 5;
    static int HORIZ_CURSOR = 11;
    static int VERT_CURSOR = 9;
    static int MOVE_CURSOR = 12;
    static int WAIT_CURSOR = 3;
    private final int KEY_DOWN = 40;
    private final int KEY_UP = 38;
    private final int KEY_LEFT = 37;
    private final int KEY_RIGHT = 39;
    private final int KEY_PAGE_UP = 33;
    private final int KEY_PAGE_DOWN = 34;
    private final int KEY_TAB = 9;
    private final int KEY_ESCAPE = 27;
    private final int KEY_HOME = 36;
    private final int KEY_END = 35;
    private final int KEY_ENTER = 10;
    Table table;
    int resize_row;
    int resize_column;
    JCCellRange span;
    int x_save;
    int y_save;
    int save_row;
    int save_column;
    JCCellPosition initialCell;
    Point initialPoint;
    Point lastPoint;
    Point upPoint;
    Point currentPoint;
    boolean selecting;
    boolean extended;
    boolean sorting;
    boolean menu;
    boolean dragging;
    InitialEvent initialEvent;
    long when;
    int modifiers;
    int clickCount;
    private static final int AUTO_SCROLL_DEC = -1;
    private static final int AUTO_SCROLL_NOVALUE = 0;
    private static final int AUTO_SCROLL_INC = 1;
    private static final int AUTO_SCROLL_AMOUNT = 20;
    boolean autoscrolling;
    boolean auto_scroll_horiz;
    boolean auto_scroll_vert;
    int auto_scroll_horiz_direction;
    int auto_scroll_vert_direction;
    Thread auto_scroll_thread;
    JCCellPosition auto_scroll_initialCell;
    Point auto_scroll_initialPoint;
    private static final int DELTA = 4;
    private static final int MINSIZE = 5;
    private static final int SELECT = 1;
    private static final int DESELECT = 2;
    private static final int DISALLOWED = -1000;
    private static final int TOGGLE = -1001;
    boolean DEBUG_MODE;
    private final int MOUSE_DRAG = 0;
    private final int MOUSE_MOVED = 1;
    private final int MOUSE_PRESSED = 2;
    private final int MOUSE_RELEASED = 3;
    private final int MOUSE_ENTERED = 4;
    private final int MOUSE_EXITED = 5;
    private final int KEY_PRESSED = 6;
    private final int KEY_RELEASED = 7;
    private final int SELECTING = 8;
    private final int COPYING = 9;
    Clipboard clipboard;
    static JCCellPosition xyToCellPosition = new JCCellPosition();
    Clip clip;
    JCCellPosition oldPos;
    String prevParam;

}
