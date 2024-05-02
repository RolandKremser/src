// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTableDataSource.java

package jclass.table3;

import java.util.Vector;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            EditableVectorDataSource, JCCellValueListener, JCLabelValueListener, JCTable, 
//            JCTblEnum, JCValidateCellEvent, JCValidateCellListener, JCValueEvent, 
//            Table, TableDataEvent, Validate, VectorDataSource

public class JCTableDataSource extends EditableVectorDataSource
{

    public JCTableDataSource(JCTable jctable)
    {
        in_get_cell = false;
        in_get_label = false;
        in_cell_value_cb = false;
        in_label_value_cb = false;
        table = jctable;
    }

    public Object getCell(int i, int j)
    {
        if(VectorDataSource.isColumnLabel(i, j))
            return getColumnLabel(j);
        if(VectorDataSource.isRowLabel(i, j))
            return getRowLabel(i);
        if(VectorDataSource.isCell(i, j))
            return getCellValue(i, j);
        else
            return null;
    }

    public JCVector getCells()
    {
        return super.cells;
    }

    public void setCell(int i, int j, Object obj)
    {
        if(VectorDataSource.isLabel(i, j))
        {
            setLabelValue(i, j, obj);
            return;
        }
        if(VectorDataSource.isCell(i, j))
            commitEdit(obj, i, j, false);
    }

    public void setCells(String as[][])
    {
        JCVector jcvector;
        if(as != null)
        {
            jcvector = new JCVector(as.length);
            for(int i = 0; i < as.length; i++)
                jcvector.setElementAt(i, new JCVector(as[i]));

        } else
        {
            jcvector = new JCVector();
        }
        setCells(((Vector) (jcvector)));
    }

    public void setCells(Vector vector)
    {
        JCVector jcvector;
        if(vector == null)
            jcvector = new JCVector();
        else
        if(vector instanceof JCVector)
            jcvector = (JCVector)vector;
        else
            jcvector = new JCVector(vector);
        if(vector != null && (table.validate_policy & 2) != 0)
            Validate.cells(table, jcvector);
        super.setCells(jcvector);
    }

    public Object getColumnLabel(int i)
    {
        return getTableColumnLabel(i);
    }

    public Vector getColumnLabels()
    {
        return super.column_labels;
    }

    public void setColumnLabel(int i, Object obj)
    {
        super.setColumnLabel(i, obj);
    }

    public void setColumnLabels(String as[])
    {
        setColumnLabels(new JCVector(as));
    }

    public Object getRowLabel(int i)
    {
        return getTableRowLabel(i);
    }

    public Vector getRowLabels()
    {
        return super.row_labels;
    }

    public void setRowLabel(int i, Object obj)
    {
        super.setRowLabel(i, obj);
    }

    public void setRowLabels(String as[])
    {
        setRowLabels(new JCVector(as));
    }

    public Object getTableColumnLabel(int i)
    {
        Object obj = getLabelValue(-1, i);
        if(obj == null)
        {
            in_get_label = true;
            JCValueEvent jcvalueevent = null;
            JCVector jcvector = table.labelValueListeners;
            if(!in_label_value_cb && jcvector.size() > 0)
            {
                in_label_value_cb = true;
                jcvalueevent = callGetLabelValueCB(-1, i);
                in_label_value_cb = false;
                obj = jcvalueevent.value;
            }
            if(jcvalueevent != null && jcvalueevent.store && obj != null)
                setLabelValue(-1, i, obj);
            in_get_label = false;
        }
        if(obj == null)
            return "";
        else
            return obj;
    }

    public Object getTableRowLabel(int i)
    {
        Object obj = getLabelValue(i, -1);
        if(obj == null)
        {
            in_get_label = true;
            JCValueEvent jcvalueevent = null;
            JCVector jcvector = table.labelValueListeners;
            if(!in_label_value_cb && jcvector.size() > 0)
            {
                in_label_value_cb = true;
                jcvalueevent = callGetLabelValueCB(i, -1);
                in_label_value_cb = false;
                obj = jcvalueevent.value;
            }
            if(jcvalueevent != null && jcvalueevent.store && obj != null)
                setLabelValue(i, -1, obj);
            in_get_label = false;
        }
        if(obj == null)
            return "";
        else
            return obj;
    }

    public Object getTableDataItem(int i, int j)
    {
        return getCellValue(i, j);
    }

    public boolean setTableDataItem(Object obj, int i, int j)
    {
        if(VectorDataSource.isCell(i, j))
            return commitEdit(obj, i, j, false);
        else
            return false;
    }

    public boolean commitEdit(Object obj, int i, int j, boolean flag)
    {
        Object obj1 = getCell(i, j);
        Object obj2 = obj;
        boolean flag1 = (table.validate_policy & 1) != 0;
        JCValidateCellEvent jcvalidatecellevent = new JCValidateCellEvent(table);
        if(flag1)
        {
            if(!Validate.value(table, jcvalidatecellevent, i, j, true, obj2))
                return false;
            obj = jcvalidatecellevent.value;
        }
        boolean flag2 = obj2 == null ? true : !obj2.equals(obj1);
        if(flag2)
            setCellValue(i, j, obj, false);
        JCVector jcvector = table.validateCellListeners;
        if(flag1 && jcvector.size() > 0)
        {
            for(int k = 0; k < jcvector.size(); k++)
                ((JCValidateCellListener)jcvector.elementAt(k)).validateCellEnd(jcvalidatecellevent);

        }
        return true;
    }

    public boolean addColumn(int i, Object obj, Vector vector)
    {
        Object obj1 = vector;
        if(vector != null && !(vector instanceof JCVector))
            obj1 = new JCVector(vector);
        if(i == 0x7fffffff)
            i = super.columns;
        else
        if(i < 0 || i > super.columns)
            return false;
        boolean flag = false;
        if(table.labelValueListeners.size() == 0)
            flag = true;
        if(obj1 != null && (table.validate_policy & 2) != 0)
        {
            JCValidateCellEvent jcvalidatecellevent = new JCValidateCellEvent(table);
            for(int j = 0; j < ((Vector) (obj1)).size(); j++)
                if(!Validate.value(table, jcvalidatecellevent, j, i, true, ((Vector) (obj1)).elementAt(j)))
                    ((Vector) (obj1)).setElementAt(null, j);

        }
        return super.addColumn(i, obj, (JCVector)obj1, flag);
    }

    public boolean addRow(int i, Object obj, Vector vector)
    {
        Object obj1 = vector;
        if(vector != null && !(vector instanceof JCVector))
            obj1 = new JCVector(vector);
        if(i == 0x7fffffff)
            i = super.rows;
        else
        if(i < 0 || i > ((Table) (table)).rows)
            return false;
        boolean flag = false;
        if(table.labelValueListeners.size() == 0)
            flag = true;
        if(obj1 != null && (table.validate_policy & 2) != 0)
        {
            JCValidateCellEvent jcvalidatecellevent = new JCValidateCellEvent(table);
            for(int j = 0; j < ((Vector) (obj1)).size(); j++)
                if(!Validate.value(table, jcvalidatecellevent, i, j, true, ((Vector) (obj1)).elementAt(j)))
                    ((Vector) (obj1)).setElementAt(null, j);

        }
        return super.addRow(i, obj, (JCVector)obj1, flag);
    }

    public boolean deleteColumns(int i, int j)
    {
        if(j > super.columns - ((Table) (table)).frozen_columns)
            return false;
        boolean flag = false;
        if(table.labelValueListeners.size() == 0)
            flag = true;
        return super.deleteColumns(i, j, flag);
    }

    public boolean deleteRows(int i, int j)
    {
        if(j > super.rows - ((Table) (table)).frozen_rows)
            return false;
        boolean flag = false;
        if(table.labelValueListeners.size() == 0)
            flag = true;
        return super.deleteRows(i, j, flag);
    }

    public boolean moveColumns(int i, int j, int k)
    {
        if(j >= super.columns - ((Table) (table)).frozen_columns)
            return false;
        boolean flag = false;
        if(table.labelValueListeners.size() == 0)
            flag = true;
        return super.moveColumns(i, j, k, flag);
    }

    public boolean moveRows(int i, int j, int k)
    {
        if(j >= super.rows - ((Table) (table)).frozen_rows)
            return false;
        boolean flag = false;
        if(table.labelValueListeners.size() == 0)
            flag = true;
        return super.moveRows(i, j, k, flag);
    }

    private Object getCellValue(int i, int j)
    {
        Object obj = null;
        if(isValidCell(i, j))
            obj = ((JCVector)super.cells.elementAt(i)).elementAt(j);
        if(obj != null)
            return obj;
        in_get_cell = true;
        JCValueEvent jcvalueevent = null;
        JCVector jcvector = table.cellValueListeners;
        if(!in_cell_value_cb && jcvector.size() > 0)
        {
            in_cell_value_cb = true;
            jcvalueevent = callGetCellValueCB(i, j);
            in_cell_value_cb = false;
            obj = jcvalueevent.value;
        }
        if(jcvalueevent != null && jcvalueevent.store && obj != null)
            setCellValue(i, j, obj, false);
        in_get_cell = false;
        if(obj == null)
            return "";
        else
            return obj;
    }

    private JCValueEvent callGetCellValueCB(int i, int j)
    {
        JCValueEvent jcvalueevent = new JCValueEvent(table, i, j);
        JCVector jcvector = table.cellValueListeners;
        for(int k = 0; k < jcvector.size(); k++)
            ((JCCellValueListener)jcvector.elementAt(k)).cellValue(jcvalueevent);

        return jcvalueevent;
    }

    private JCValueEvent callGetLabelValueCB(int i, int j)
    {
        JCValueEvent jcvalueevent = new JCValueEvent(table, i, j);
        JCVector jcvector = table.labelValueListeners;
        for(int k = 0; k < jcvector.size(); k++)
        {
            JCLabelValueListener jclabelvaluelistener = (JCLabelValueListener)jcvector.elementAt(k);
            if(j == -1)
                jclabelvaluelistener.rowLabelValue(jcvalueevent);
            else
                jclabelvaluelistener.columnLabelValue(jcvalueevent);
        }

        return jcvalueevent;
    }

    private boolean setCellValue(int i, int j, Object obj, boolean flag)
    {
        Object obj2 = null;
        Object obj3 = obj;
        boolean flag1 = true;
        boolean flag2 = true;
        Object obj4 = null;
        int i1 = 0;
        int j1 = super.rows - 1;
        int k1 = 0;
        int l1 = super.columns - 1;
        if(i == -997)
            i = -998;
        if(j == -997)
            j = -998;
        if(i == -998)
        {
            if(j != -998)
                k1 = l1 = j;
            i1 = 0;
            j1 = ((Table) (table)).rows - 1;
        }
        if(j == -998)
        {
            if(i != -998)
                i1 = j1 = i;
            k1 = 0;
            l1 = ((Table) (table)).columns - 1;
        }
        if(i != -998 && j != -998)
        {
            i1 = j1 = i;
            k1 = l1 = j;
        }
        if(i1 < 0 || k1 < 0)
            return false;
        boolean flag3 = true;
        flag = flag && (table.validate_policy & 2) != 0;
        boolean flag4 = table.cellValueListeners.size() > 0;
        if(in_get_cell || !flag4)
        {
            obj2 = obj3;
            if(flag)
                obj4 = obj3;
        }
        for(int k = i1; k <= j1; k++)
        {
            int l;
            for(l = k1; l <= l1; l++)
            {
                if(!in_get_cell && flag4)
                    continue;
                Object obj1 = isValidCell(i, j) ? ((Vector)super.cells.elementAt(i)).elementAt(j) : null;
                if(flag2)
                    flag1 = obj2 == null ? obj1 != null : !obj2.equals(obj1);
                if(flag)
                {
                    JCValidateCellEvent jcvalidatecellevent = new JCValidateCellEvent(table);
                    if(!Validate.value(table, jcvalidatecellevent, k, l, flag1, obj4))
                    {
                        flag3 = false;
                        continue;
                    }
                    obj2 = jcvalidatecellevent.value;
                }
                if(flag1)
                {
                    if(obj1 != null && isValidCell(k, l))
                        ((JCVector)super.cells.elementAt(k)).setElementAt(l, null);
                    if(flag2 && obj2 == null)
                    {
                        setDataChanged(k, l, 1, 0, 1);
                    } else
                    {
                        super.cells.setMinSize(k + 1);
                        if(super.cells.elementAt(k) == null)
                            super.cells.setElementAt(k, new JCVector(l + 1));
                        ((JCVector)super.cells.elementAt(k)).setElementAt(l, obj2);
                        setDataChanged(k, l, 1, 0, 1);
                    }
                }
            }

            if(flag2 && obj2 == null && isValidCell(k, l) && l1 == super.cells.size() - 1)
            {
                ((JCVector)super.cells.elementAt(k)).strip();
                super.cells.strip();
            }
        }

        return flag3;
    }

    boolean in_get_cell;
    boolean in_get_label;
    boolean in_cell_value_cb;
    boolean in_label_value_cb;
    JCTable table;
}
