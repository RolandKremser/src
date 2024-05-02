// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableDataView.java

package jclass.table3;

import java.io.*;
import java.util.Hashtable;
import java.util.Vector;
import jclass.cell.*;
import jclass.cell.renderers.StringCellRenderer;
import jclass.util.JCString;

// Referenced classes of package jclass.table3:
//            DrawRange, EditHandler, EditableTableData, JCCellRange, 
//            JCTblEnum, PropertyEnum, Table, TableData, 
//            TableDataEvent, TableDataListener, TableDataUtil, TableException, 
//            TraverseInitial, VarSize

public class TableDataView
    implements TableDataListener, Serializable
{

    private TableDataView()
    {
        this(null);
    }

    public TableDataView(Table table)
    {
        isEditable = false;
        needs_swapped_columns = false;
        needs_swapped_rows = false;
        containsJCStringURL = false;
        cellRange = new JCCellRange();
        parent = table;
        InitEditorRenderers();
    }

    public void InitEditorRenderers()
    {
        InitEditorHashtable();
        InitRendererHashtable();
    }

    public void dataChanged(TableDataEvent tabledataevent)
    {
        int i = tabledataevent.getRow();
        if(i >= 0)
            i = getViewRow(i);
        int j = tabledataevent.getColumn();
        if(j >= 0)
            j = getViewColumn(j);
        switch(tabledataevent.getCommand())
        {
        case 1: // '\001'
            if(parent.editHandler.getCellEditor() != null && i == parent.edit_row && j == parent.edit_column)
                parent.editHandler.cancel();
            int k = parent.getPixelWidth(j);
            int j1 = parent.getPixelHeight(i);
            int i2 = 0x10000;
            if(k == 33001 || j1 == 33001)
            {
                i2 |= VarSize.calc(parent, i, j, k, j1);
                parent.setProperty(i2, i, j);
            }
            repaintTable(i, j);
            return;

        case 2: // '\002'
            if(parent.editHandler.getCellEditor() != null && i == parent.edit_row)
                parent.editHandler.cancel();
            repaintTable(i, -1, i, dataSource.getNumColumns());
            return;

        case 3: // '\003'
            if(parent.editHandler.getCellEditor() != null)
                parent.editHandler.cancel();
            TableDataUtil.addRow(parent, i, 1, dataSource.getNumRows(), dataSource.getNumColumns());
            repaintTable(i, -1, dataSource.getNumRows(), dataSource.getNumColumns());
            return;

        case 4: // '\004'
            if(parent.editHandler.getCellEditor() != null)
                parent.editHandler.cancel();
            if(needs_swapped_rows)
            {
                for(int l2 = 0; l2 < tabledataevent.getNumAffected(); l2++)
                {
                    int k3 = getViewRow(tabledataevent.getRow() + l2);
                    swappedRows = removeSwappedElement(swappedRows, k3);
                    TableDataUtil.deleteRow(parent, k3, 1, dataSource.getNumRows(), dataSource.getNumColumns());
                }

                repaintTable();
                return;
            }
            TableDataUtil.deleteRow(parent, tabledataevent.getRow(), tabledataevent.getNumAffected(), dataSource.getNumRows(), dataSource.getNumColumns());
            if(dataSource.getNumRows() > 0)
            {
                repaintTable(tabledataevent.getRow(), -1, dataSource.getNumRows(), dataSource.getNumColumns());
                return;
            } else
            {
                repaintTable();
                return;
            }

        case 5: // '\005'
            if(parent.editHandler.getCellEditor() != null && j == parent.edit_column)
                parent.editHandler.cancel();
            repaintTable(-1, j, dataSource.getNumRows(), j);
            return;

        case 6: // '\006'
            if(parent.editHandler.getCellEditor() != null)
                parent.editHandler.cancel();
            TableDataUtil.addColumn(parent, tabledataevent.getColumn(), 1, dataSource.getNumRows(), dataSource.getNumColumns());
            repaintTable(-1, tabledataevent.getColumn(), dataSource.getNumRows(), dataSource.getNumColumns());
            return;

        case 7: // '\007'
            if(parent.editHandler.getCellEditor() != null)
                parent.editHandler.cancel();
            if(needs_swapped_columns)
            {
                for(int i3 = 0; i3 < tabledataevent.getNumAffected(); i3++)
                {
                    int l3 = getViewColumn(tabledataevent.getColumn() + i3);
                    swappedColumns = removeSwappedElement(swappedColumns, l3);
                    TableDataUtil.deleteColumns(parent, l3, 1, dataSource.getNumRows(), dataSource.getNumColumns());
                }

                repaintTable();
                return;
            }
            TableDataUtil.deleteColumns(parent, tabledataevent.getColumn(), tabledataevent.getNumAffected(), dataSource.getNumRows(), dataSource.getNumColumns());
            if(dataSource.getNumColumns() > 0)
            {
                repaintTable(-1, tabledataevent.getColumn(), dataSource.getNumRows(), dataSource.getNumColumns());
                return;
            } else
            {
                repaintTable();
                return;
            }

        case 8: // '\b'
            int l = parent.getPixelWidth(-1);
            int k1 = parent.getPixelHeight(i);
            int j2 = 0x10000;
            if(l == 33001 || k1 == 33001)
            {
                j2 |= VarSize.calc(parent, i, -1, l, k1);
                parent.setProperty(j2, i, -1);
            }
            repaintTable(i, -1);
            return;

        case 9: // '\t'
            int i1 = parent.getPixelWidth(j);
            int l1 = parent.getPixelHeight(-1);
            int k2 = 0x10000;
            if(i1 == 33001 || l1 == 33001)
            {
                k2 |= VarSize.calc(parent, -1, j, i1, l1);
                parent.setProperty(k2, -1, j);
            }
            repaintTable(-1, j);
            return;

        case 10: // '\n'
            if(parent.editHandler.getCellEditor() != null)
                parent.editHandler.cancel();
            TableDataUtil.moveRows(parent, tabledataevent.getRow(), tabledataevent.getNumAffected(), tabledataevent.getDestination());
            repaintTable();
            return;

        case 11: // '\013'
            if(parent.editHandler.getCellEditor() != null)
                parent.editHandler.cancel();
            TableDataUtil.moveColumns(parent, tabledataevent.getColumn(), tabledataevent.getNumAffected(), tabledataevent.getDestination());
            repaintTable();
            return;

        case 12: // '\f'
            int j3 = dataSource.getNumRows();
            if(parent.edit_row >= j3)
                parent.edit_row = parent.edit_column = -999;
            if(parent.rows != j3)
            {
                if(parent.editHandler.getCellEditor() != null && j3 <= parent.edit_row)
                    parent.editHandler.cancel();
                parent.rows = j3;
                parent.setProperty(1408);
            }
            resetSwappedRows();
            return;

        case 13: // '\r'
            int i4 = dataSource.getNumColumns();
            if(parent.edit_column >= i4)
                parent.edit_row = parent.edit_column = -999;
            if(parent.columns != i4)
            {
                if(parent.editHandler.getCellEditor() != null && i4 <= parent.edit_row)
                    parent.editHandler.cancel();
                parent.columns = i4;
                parent.setProperty(146);
            }
            resetSwappedColumns();
            return;

        case 14: // '\016'
            int j4 = dataSource.getNumRows();
            int k4 = dataSource.getNumColumns();
            if(parent.edit_row >= j4 || parent.edit_column >= k4)
                parent.edit_row = parent.edit_column = -999;
            if(parent.rows != j4)
            {
                if(parent.editHandler.getCellEditor() != null && j4 <= parent.edit_row)
                    parent.editHandler.cancel();
                parent.rows = j4;
                parent.setProperty(1408);
            }
            resizeSwappedRows();
            if(parent.columns != k4)
            {
                if(parent.editHandler.getCellEditor() != null && k4 <= parent.edit_row)
                    parent.editHandler.cancel();
                parent.columns = k4;
                parent.setProperty(146);
            }
            resizeSwappedColumns();
            repaintTable();
            return;
        }
    }

    public void setDataSource(TableData tabledata)
    {
        boolean flag = false;
        if(tabledata == null)
            return;
        parent.setRepaint(false);
        if(dataSource != null)
        {
            flag = true;
            dataSource.removeTableDataListener(this);
            flag = true;
            parent.cancelEdit(true);
        }
        dataSource = tabledata;
        dataSource.addTableDataListener(this);
        if(dataSource instanceof EditableTableData)
            isEditable = true;
        else
            isEditable = false;
        parent.rows = dataSource.getNumRows();
        parent.columns = dataSource.getNumColumns();
        resetSwappedRows();
        resetSwappedColumns();
        containsJCStringURL = false;
        parent.needs_recalc = true;
        parent.setProperty(0xc00c0);
        if(flag)
        {
            parent.edit_row = parent.edit_column = -999;
            parent.setTopRow(0);
            parent.setLeftColumn(0);
        }
        parent.setRepaint(true);
        if(flag)
            TraverseInitial.traverse(parent, false);
    }

    public TableData getDataSource()
    {
        return dataSource;
    }

    public boolean getEditable()
    {
        return isEditable;
    }

    public Object getCellData(int i, int j)
    {
        if(dataSource == null)
            return null;
        if(!parent.trackJCStringURL || containsJCStringURL)
            return dataSource.getTableDataItem(getDataRow(i), getDataColumn(j));
        Object obj = dataSource.getTableDataItem(getDataRow(i), getDataColumn(j));
        Object obj1 = obj;
        if(obj1 instanceof CellData)
            obj1 = ((CellData)obj).getData();
        if(obj1 instanceof JCString)
            containsJCStringURL = containsURL((JCString)obj1);
        return obj;
    }

    private boolean containsURL(JCString jcstring)
    {
        if(jcstring == null || jcstring.size() == 0)
            return false;
        int i = 0;
        for(int j = jcstring.size(); i < j; i++)
            if(jcstring.elementAt(i) == JCString.HREF)
                return true;

        return false;
    }

    public boolean setCellData(Object obj, int i, int j)
    {
        int k = getDataColumn(j);
        int l = getDataRow(i);
        if(isEditable)
            return ((EditableTableData)dataSource).setTableDataItem(obj, l, k);
        else
            return true;
    }

    public Object getColumnLabel(int i)
    {
        if(dataSource == null)
            return null;
        else
            return dataSource.getTableColumnLabel(getDataColumn(i));
    }

    public Object getRowLabel(int i)
    {
        if(dataSource == null)
            return null;
        else
            return dataSource.getTableRowLabel(getDataRow(i));
    }

    public int getNumRows()
    {
        return dataSource.getNumRows();
    }

    public int getNumColumns()
    {
        return dataSource.getNumColumns();
    }

    private void repaintTable()
    {
        parent.repaint();
    }

    private void repaintTable(int i, int j)
    {
        DrawRange.draw(parent, cellRange.reshape(i, j));
    }

    private void repaintTable(int i, int j, int k, int l)
    {
        DrawRange.draw(parent, cellRange.reshape(i, j, k, l));
    }

    public int[] getSwappedColumns()
    {
        int i = dataSource.getNumColumns();
        if(swappedColumns == null || swappedColumns.length < i)
            resizeSwappedColumns();
        return swappedColumns;
    }

    public int[] getSwappedRows()
    {
        int i = dataSource.getNumRows();
        if(swappedRows == null || swappedRows.length < i)
            resizeSwappedRows();
        return swappedRows;
    }

    public int getDataColumn(int i)
    {
        if(swappedColumns == null)
            return i;
        int j = dataSource.getNumColumns();
        if(swappedColumns == null || swappedColumns.length < j)
            resizeSwappedColumns();
        if(i < j)
            return swappedColumns[i];
        else
            return -999;
    }

    public Object getData(int i, int j)
    {
        if(i >= 0 && j >= 0)
            return getCellData(i, j);
        if(i == -1)
            return getColumnLabel(j);
        else
            return getRowLabel(i);
    }

    public int getDataRow(int i)
    {
        if(swappedRows == null)
            return i;
        int j = dataSource.getNumRows();
        if(swappedRows == null || swappedRows.length < j)
            resizeSwappedRows();
        if(i < j)
            return swappedRows[i];
        else
            return -999;
    }

    public int getViewColumn(int i)
    {
        if(swappedColumns == null)
            return i;
        int j = dataSource.getNumColumns();
        if(swappedColumns == null || swappedColumns.length < j)
            resizeSwappedColumns();
        if(i < j)
        {
            if(swappedColumns[i] == i)
                return i;
            for(int k = 0; k < j; k++)
                if(swappedColumns[k] == i)
                    return k;

        }
        return -999;
    }

    public int getViewRow(int i)
    {
        if(swappedRows == null)
            return i;
        int j = dataSource.getNumRows();
        if(swappedRows == null || swappedRows.length < j)
            resizeSwappedRows();
        if(i < j)
        {
            if(swappedRows[i] == i)
                return i;
            for(int k = 0; k < j; k++)
                if(swappedRows[k] == i)
                    return k;

        }
        return -999;
    }

    protected void resizeSwappedColumns()
    {
        if(!needs_swapped_columns)
            return;
        int i = dataSource.getNumColumns();
        int ai[] = new int[i];
        for(int j = 0; j < i; j++)
            if(swappedColumns == null || j >= swappedColumns.length)
                ai[j] = j;
            else
                ai[j] = swappedColumns[j];

        swappedColumns = ai;
    }

    protected void resizeSwappedRows()
    {
        if(!needs_swapped_rows)
            return;
        int i = dataSource.getNumRows();
        int ai[] = new int[i];
        for(int j = 0; j < i; j++)
            if(swappedRows == null || j >= swappedRows.length)
                ai[j] = j;
            else
                ai[j] = swappedRows[j];

        swappedRows = ai;
    }

    protected void resetSwappedColumns()
    {
        needs_swapped_columns = false;
        if(swappedColumns != null)
        {
            int i = swappedColumns.length;
            int ai[] = new int[i];
            for(int j = 0; j < i; j++)
                ai[j] = j;

            setSwappedColumns(ai);
        }
        swappedColumns = null;
        repaintTable();
    }

    protected void resetSwappedRows()
    {
        needs_swapped_rows = false;
        if(swappedRows != null)
        {
            int i = swappedRows.length;
            int ai[] = new int[i];
            for(int j = 0; j < i; j++)
                ai[j] = j;

            setSwappedRows(ai);
        }
        swappedRows = null;
        repaintTable();
    }

    protected boolean dragColumn(int i, int j)
    {
        int k = dataSource.getNumColumns();
        if(i < 0 || i > k)
            return false;
        if(j < 0)
            return false;
        if(i == j)
            return true;
        needs_swapped_columns = true;
        if(swappedColumns == null || swappedColumns.length < k)
            resizeSwappedColumns();
        swappedColumns = shiftSwappedArray(swappedColumns, i, j);
        boolean flag = TableDataUtil.dragColumn(parent, i, j);
        repaintTable();
        return flag;
    }

    protected boolean dragRow(int i, int j)
    {
        int k = dataSource.getNumRows();
        if(i < 0 || i > k)
            return false;
        if(j < 0)
            return false;
        if(i == j)
            return true;
        needs_swapped_rows = true;
        if(swappedRows == null || swappedRows.length < k)
            resizeSwappedRows();
        swappedRows = shiftSwappedArray(swappedRows, i, j);
        boolean flag = TableDataUtil.dragRow(parent, i, j);
        repaintTable();
        return flag;
    }

    protected int[] shiftSwappedArray(int ai[], int i, int j)
    {
        int k = ai.length;
        int ai1[] = new int[k];
        if(j == 0x7fffffff)
        {
            for(int l = 0; l < i; l++)
                ai1[l] = ai[l];

            for(int k1 = i + 1; k1 < k; k1++)
                ai1[k1 - 1] = ai[k1];

            ai1[k - 1] = ai[i];
        } else
        if(i < j)
        {
            for(int i1 = 0; i1 < k; i1++)
                ai1[i1] = ai[i1];

            for(int l1 = i + 1; l1 < j; l1++)
                ai1[l1 - 1] = ai[l1];

            ai1[j - 1] = ai[i];
        } else
        if(i > j)
        {
            for(int j1 = 0; j1 < k; j1++)
                ai1[j1] = ai[j1];

            for(int i2 = j; i2 < i; i2++)
                ai1[i2 + 1] = ai[i2];

            ai1[j] = ai[i];
        }
        return ai1;
    }

    protected int[] removeSwappedElement(int ai[], int i)
    {
        if(ai == null)
            return null;
        int j = ai.length;
        int ai1[] = new int[j - 1];
        for(int k = 0; k < j; k++)
            if(k < i)
                ai1[k] = ai[k];
            else
            if(k > i)
                ai1[k - 1] = ai[k];

        return ai1;
    }

    protected boolean swapColumns(int i, int j)
    {
        int k = dataSource.getNumColumns();
        if(i < 0 || i > k)
            return false;
        if(j < 0 || j > k)
            return false;
        if(i == j)
            return true;
        if(parent.editHandler.getCellEditor() != null)
            parent.editHandler.cancel();
        needs_swapped_columns = true;
        if(swappedColumns == null || swappedColumns.length < k)
            resizeSwappedColumns();
        int l = swappedColumns[i];
        swappedColumns[i] = swappedColumns[j];
        swappedColumns[j] = l;
        TableDataUtil.swapColumns(parent, i, j);
        repaintTable();
        return true;
    }

    public boolean setSwappedColumns(int ai[])
    {
        int i = dataSource.getNumColumns();
        if(ai.length != i)
            return false;
        if(parent.editHandler.getCellEditor() != null)
            parent.editHandler.cancel();
        boolean flag = parent.getRepaint();
        parent.setRepaint(false);
        needs_swapped_columns = true;
        if(swappedColumns == null || swappedColumns.length < i)
            resizeSwappedColumns();
        if(TableDataUtil.swapColumnArrays(parent, swappedColumns, ai))
            swappedColumns = ai;
        parent.setRepaint(flag);
        repaintTable();
        return true;
    }

    protected boolean swapRows(int i, int j)
    {
        int k = dataSource.getNumRows();
        if(i < 0 || i > k)
            return false;
        if(j < 0 || j > k)
            return false;
        if(i == j)
            return true;
        if(parent.editHandler.getCellEditor() != null)
            parent.editHandler.cancel();
        needs_swapped_rows = true;
        if(swappedRows == null || swappedRows.length < k)
            resizeSwappedRows();
        int l = swappedRows[i];
        swappedRows[i] = swappedRows[j];
        swappedRows[j] = l;
        TableDataUtil.swapRows(parent, i, j);
        repaintTable();
        return true;
    }

    public boolean setSwappedRows(int ai[])
    {
        int i = dataSource.getNumRows();
        if(ai.length != i)
            return false;
        if(parent.editHandler.getCellEditor() != null)
            parent.editHandler.cancel();
        needs_swapped_rows = true;
        if(swappedRows != null && swappedRows.length > i)
            resetSwappedRows();
        if(swappedRows == null || swappedRows.length < i)
            resizeSwappedRows();
        if(parent.sort_series)
        {
            if(TableDataUtil.swapRowArrays(parent, swappedRows, ai))
            {
                swappedRows = ai;
                repaintTable();
            }
        } else
        {
            swappedRows = ai;
            repaintTable();
        }
        return true;
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        InitRendererHashtable();
        InitEditorHashtable();
    }

    protected boolean trackJCStringURL()
    {
        return parent.track_cursor && containsJCStringURL;
    }

    private void InitRendererHashtable()
    {
        renderers = new Hashtable();
        try
        {
            renderers.put(Class.forName("java.awt.Image"), Class.forName("jclass.cell.renderers.ImageCellRenderer"));
            renderers.put(Class.forName("jclass.util.JCString"), Class.forName("jclass.table3.JCStringCellRenderer"));
            renderers.put(Class.forName("java.lang.String"), Class.forName("jclass.cell.renderers.StringCellRenderer"));
            renderers.put(Class.forName("java.lang.Object"), Class.forName("jclass.cell.renderers.StringCellRenderer"));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(parent != null && parent.advanced_editor_renderers)
            loadFieldRenderers(renderers);
    }

    public CellRenderer getCellRenderer(int i, int j)
    {
        Object obj;
        if(i != -1 && j != -1)
            obj = getCellData(i, j);
        else
        if(i == -1)
            obj = getColumnLabel(j);
        else
            obj = getRowLabel(i);
        return getCellRenderer(i, j, obj);
    }

    public CellRenderer getCellRenderer(int i, int j, Object obj)
    {
        CellRenderer cellrenderer = null;
        if(obj instanceof CellData)
        {
            cellrenderer = ((CellData)obj).getRenderer();
            if(cellrenderer != null)
                return cellrenderer;
        }
        cellrenderer = parent.getCellRenderer(i, j);
        if(cellrenderer == null)
            return getCellRenderer(obj);
        else
            return cellrenderer;
    }

    public Hashtable getRenderersTable()
    {
        return renderers;
    }

    public void setRenderersTable(Hashtable hashtable)
    {
        if(hashtable == null)
            return;
        try
        {
            if(hashtable.get(Class.forName("java.lang.Object")) == null)
                hashtable.put(Class.forName("java.lang.Object"), Class.forName("jclass.cell.renderers.StringCellRenderer"));
        }
        catch(ClassNotFoundException _ex)
        {
            throw new TableException("No renderer for java.lang.Object available");
        }
        renderers = hashtable;
    }

    public CellRenderer getCellRenderer(Object obj)
    {
        if(obj == null)
            return new StringCellRenderer();
        CellRenderer cellrenderer = null;
        try
        {
            Class class1 = obj.getClass();
            Class class2 = null;
            Object obj1 = null;
            do
            {
                obj1 = renderers.get(class1);
                class2 = class1;
                class1 = class1.getSuperclass();
            } while(obj1 == null);
            if(obj1 instanceof Class)
            {
                cellrenderer = (CellRenderer)((Class)obj1).newInstance();
                renderers.put(class2, cellrenderer);
            } else
            {
                cellrenderer = (CellRenderer)obj1;
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(cellrenderer == null)
            throw new TableException("No renderer for " + obj.getClass().getName() + " available");
        else
            return cellrenderer;
    }

    private void InitEditorHashtable()
    {
        editors = new Hashtable();
        try
        {
            editors.put(Class.forName("java.lang.Boolean"), Class.forName("jclass.cell.editors.BooleanCellEditor"));
            editors.put(Class.forName("java.util.Date"), Class.forName("jclass.cell.editors.DateCellEditor"));
            editors.put(Class.forName("java.lang.Double"), Class.forName("jclass.cell.editors.DoubleCellEditor"));
            editors.put(Class.forName("java.lang.Float"), Class.forName("jclass.cell.editors.FloatCellEditor"));
            editors.put(Class.forName("java.lang.Integer"), Class.forName("jclass.cell.editors.IntegerCellEditor"));
            editors.put(Class.forName("jclass.util.JCString"), Class.forName("jclass.table3.JCStringCellEditor"));
            editors.put(Class.forName("java.lang.String"), Class.forName("jclass.table3.TextCellEditor"));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(parent != null && parent.advanced_editor_renderers)
            loadFieldEditors(editors);
    }

    public CellEditor getCellEditor(int i, int j)
    {
        Object obj;
        if(i != -1 && j != -1)
            obj = getCellData(i, j);
        else
        if(i == -1)
            obj = getColumnLabel(j);
        else
            obj = getRowLabel(i);
        CellEditor celleditor = null;
        if(obj instanceof CellData)
            return ((CellData)obj).getEditor();
        celleditor = parent.getCellEditor(i, j);
        if(celleditor == null)
            return getCellEditor(obj);
        else
            return celleditor;
    }

    public Hashtable getEditorsTable()
    {
        return editors;
    }

    public void setEditorsTable(Hashtable hashtable)
    {
        if(hashtable == null)
        {
            return;
        } else
        {
            editors = hashtable;
            return;
        }
    }

    public CellEditor getCellEditor(Object obj)
    {
        if(obj == null)
            return null;
        if(editors.size() == 0)
            return null;
        CellEditor celleditor = null;
        try
        {
            Class class1 = obj.getClass();
            Class class2 = null;
            Object obj1 = null;
            do
            {
                obj1 = editors.get(class1);
                class2 = class1;
                class1 = class1.getSuperclass();
            } while(obj1 == null && class1 != null);
            if(obj1 instanceof Class)
            {
                celleditor = (CellEditor)((Class)obj1).newInstance();
                editors.put(class2, celleditor);
            } else
            {
                celleditor = (CellEditor)obj1;
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return celleditor;
    }

    void dispose()
    {
        if(dataSource != null)
        {
            dataSource.removeTableDataListener(this);
            dataSource = null;
        }
        if(parent != null)
            parent = null;
    }

    protected void loadFieldRenderers(Hashtable hashtable)
    {
        try
        {
            hashtable.put(Class.forName("java.lang.Float"), Class.forName("jclass.field.cell.DoubleRendererEditor"));
            hashtable.put(Class.forName("java.lang.Double"), Class.forName("jclass.field.cell.DoubleRendererEditor"));
            hashtable.put(Class.forName("java.lang.Integer"), Class.forName("jclass.field.cell.IntegerRendererEditor"));
            hashtable.put(Class.forName("java.lang.Long"), Class.forName("jclass.field.cell.IntegerRendererEditor"));
            hashtable.put(Class.forName("java.lang.Byte"), Class.forName("jclass.field.cell.IntegerRendererEditor"));
            hashtable.put(Class.forName("java.lang.Long"), Class.forName("jclass.field.cell.IntegerRendererEditor"));
            hashtable.put(Class.forName("java.util.Date"), Class.forName("jclass.field.cell.DateRendererEditor"));
            hashtable.put(Class.forName("java.util.Calendar"), Class.forName("jclass.field.cell.DateRendererEditor"));
            hashtable.put(Class.forName("java.sql.Date"), Class.forName("jclass.field.cell.DateRendererEditor"));
            hashtable.put(Class.forName("java.sql.Timestamp"), Class.forName("jclass.field.cell.DateRendererEditor"));
            hashtable.put(Class.forName("java.sql.Time"), Class.forName("jclass.field.cell.DateRendererEditor"));
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.out);
        }
    }

    protected void loadFieldEditors(Hashtable hashtable)
    {
        loadFieldRenderers(hashtable);
    }

    Table parent;
    TableData dataSource;
    boolean isEditable;
    transient Hashtable renderers;
    transient Hashtable editors;
    private int swappedColumns[];
    private int swappedRows[];
    boolean needs_swapped_columns;
    boolean needs_swapped_rows;
    private boolean containsJCStringURL;
    JCCellRange cellRange;
}
