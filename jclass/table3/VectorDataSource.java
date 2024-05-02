// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VectorDataSource.java

package jclass.table3;

import java.awt.Component;
import java.io.Serializable;
import java.util.Vector;
import jclass.util.ConvertUtil;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            TableDataSupport, JCTblConverter, JCTblEnum, JCTblRevConverter, 
//            TableDataEvent

public class VectorDataSource extends TableDataSupport
    implements Serializable
{

    public VectorDataSource()
    {
        cells = new JCVector();
        column_labels = new JCVector();
        row_labels = new JCVector();
        columns = 5;
        rows = 10;
        is_moving = false;
    }

    public VectorDataSource(int i, int j, String s, String s1, String s2)
    {
        cells = new JCVector();
        column_labels = new JCVector();
        row_labels = new JCVector();
        columns = 5;
        rows = 10;
        is_moving = false;
        columns = j;
        rows = i;
        cells = JCTblConverter.toCellValues(null, s2, '|', true);
        column_labels = ConvertUtil.toVector(null, s1, '|', false);
        row_labels = ConvertUtil.toVector(null, s, '|', false);
    }

    public String toString()
    {
        return JCTblRevConverter.fromCellValues(cells, '|');
    }

    public int getNumColumns()
    {
        return columns;
    }

    public void setNumColumns(int i)
    {
        columns = i;
        setDataChanged(0, 0, 0, 0, 13);
    }

    public int getNumRows()
    {
        return rows;
    }

    public void setNumRows(int i)
    {
        rows = i;
        setDataChanged(0, 0, 0, 0, 12);
    }

    public Object getTableColumnLabel(int i)
    {
        if(i >= 0 && i < column_labels.size())
            return column_labels.elementAt(i);
        else
            return "";
    }

    public Object getTableRowLabel(int i)
    {
        if(i >= 0 && i < row_labels.size())
            return row_labels.elementAt(i);
        else
            return "";
    }

    public Object getTableDataItem(int i, int j)
    {
        Object obj = isValidCell(i, j) ? ((JCVector)cells.elementAt(i)).elementAt(j) : null;
        if(obj != null)
            return obj;
        else
            return "";
    }

    public void clearCells()
    {
        setCells(((Vector) (null)));
    }

    public JCVector getCells()
    {
        return cells;
    }

    public Vector getColumnLabels()
    {
        return column_labels;
    }

    public Vector getRowLabels()
    {
        return row_labels;
    }

    public static final boolean isCell(int i, int j)
    {
        return i >= 0 && j >= 0;
    }

    public static final boolean isColumnLabel(int i, int j)
    {
        return j >= 0 && i == -1;
    }

    public static final boolean isLabel(int i, int j)
    {
        if(i == -1)
            return j >= 0;
        if(j == -1)
            return i >= 0;
        else
            return false;
    }

    public static final boolean isRowLabel(int i, int j)
    {
        return i >= 0 && j == -1;
    }

    public final boolean isValidCell(int i, int j)
    {
        return i >= 0 && i < cells.size() && cells.elementAt(i) != null && j >= 0 && j < ((Vector)cells.elementAt(i)).size();
    }

    public final boolean isValidLabel(int i, int j)
    {
        if(j == -1)
            return i < row_labels.size();
        if(i == -1)
            return j < column_labels.size();
        else
            return false;
    }

    public void setColumnLabel(int i, Object obj)
    {
        setLabelValue(-1, i, obj);
    }

    public void setColumnLabels(Component component, String s, char c, boolean flag)
    {
        column_labels = ConvertUtil.toVector(component, s, c, flag);
        setDataChanged(-998, -998, 0, 0, 14);
    }

    public void setColumnLabels(String as[])
    {
        setColumnLabels(((Vector) (new JCVector(as))));
    }

    public void setColumnLabels(Vector vector)
    {
        if(vector == null || (vector instanceof JCVector))
            column_labels = (JCVector)vector;
        else
            column_labels = new JCVector(vector);
        setDataChanged(-998, -998, 0, 0, 14);
    }

    public void setRowLabels(Component component, String s, char c, boolean flag)
    {
        row_labels = ConvertUtil.toVector(component, s, c, flag);
        setDataChanged(-998, -998, 0, 0, 14);
    }

    public void setRowLabel(int i, Object obj)
    {
        setLabelValue(i, -1, obj);
    }

    public void setRowLabels(String as[])
    {
        setRowLabels(((Vector) (new JCVector(as))));
    }

    public void setRowLabels(Vector vector)
    {
        if(vector == null || (vector instanceof JCVector))
            row_labels = (JCVector)vector;
        else
            row_labels = new JCVector(vector);
        setDataChanged(-998, -998, 0, 0, 14);
    }

    public void setCell(int i, int j, Object obj)
    {
        if((i == -998 || i == -997 || i >= 0) && (j == -998 || j == -997 || j >= 0))
            setCellValue(i, j, obj);
    }

    public void setCells(String as[][])
    {
        if(as == null)
        {
            setCells(((Vector) (new JCVector())));
            return;
        }
        JCVector jcvector = new JCVector(as.length);
        for(int i = 0; i < as.length; i++)
            jcvector.setElementAt(i, new JCVector(as[i]));

        setCells(((Vector) (jcvector)));
    }

    public void setCells(Vector vector)
    {
        if(vector == null)
            cells = new JCVector();
        else
        if(vector instanceof JCVector)
            cells = (JCVector)vector;
        else
            cells = new JCVector(vector);
        setDataChanged(-998, -998, 0, 0, 14);
    }

    protected Object getLabelValue(int i, int j)
    {
        if(i >= 0 && j == -1 && i < row_labels.size())
            return row_labels.elementAt(i);
        if(j >= 0 && i == -1 && j < column_labels.size())
            return column_labels.elementAt(j);
        else
            return null;
    }

    protected void setLabelValue(int i, int j, Object obj)
    {
        if(!isLabel(i, j))
            return;
        if(i == -1)
        {
            if(setLabelValue(column_labels, j, obj))
            {
                setDataChanged(-1, j, 1, 0, 9);
                return;
            }
        } else
        if(setLabelValue(row_labels, i, obj))
            setDataChanged(i, -1, 1, 0, 8);
    }

    private boolean setLabelValue(JCVector jcvector, int i, Object obj)
    {
        if(i < 0)
            return false;
        if(i < jcvector.size())
        {
            Object obj1 = jcvector.elementAt(i);
            if(obj1 != null && obj != null && obj1.equals(obj))
                return false;
        }
        jcvector.setElementAt(i, obj);
        if(obj == null && i == jcvector.size() - 1)
            jcvector.strip();
        return true;
    }

    private void setNumRowsColumns(int i, int j)
    {
        rows = i;
        columns = j;
        setDataChanged(-998, -998, 0, 0, 14);
    }

    private void setCellValue(int i, int j, Object obj)
    {
        int i1 = 0;
        int j1 = rows - 1;
        int k1 = 0;
        int l1 = columns - 1;
        if(i >= 0 && j >= 0)
        {
            i1 = j1 = i;
            k1 = l1 = j;
        } else
        {
            if(i == -997)
                i = -998;
            if(j == -997)
                j = -998;
            if(i == -998)
            {
                if(j != -998)
                    k1 = l1 = j;
                i1 = 0;
                j1 = rows - 1;
            }
            if(j == -998)
            {
                if(i != -998)
                    i1 = j1 = i;
                k1 = 0;
                l1 = columns - 1;
            }
            if(i != -998 && j != -998)
            {
                i1 = j1 = i;
                k1 = l1 = j;
            }
            if(i1 < 0 || k1 < 0)
                return;
        }
        for(int k = i1; k <= j1; k++)
        {
            int l;
            for(l = k1; l <= l1; l++)
            {
                Object obj1 = isValidCell(i, j) ? ((Vector)cells.elementAt(i)).elementAt(j) : null;
                if(obj1 != null)
                {
                    if(obj1.equals(obj))
                        continue;
                    if(isValidCell(k, l))
                        ((JCVector)cells.elementAt(k)).setElementAt(l, null);
                }
                cells.setMinSize(k + 1);
                if(cells.elementAt(k) == null)
                    cells.setElementAt(k, new JCVector(l + 1));
                ((JCVector)cells.elementAt(k)).setElementAt(l, obj);
                setDataChanged(k, l, 1, 0, 1);
            }

            if(obj == null && isValidCell(k, l) && l1 == cells.size() - 1)
            {
                ((JCVector)cells.elementAt(k)).strip();
                cells.strip();
            }
        }

    }

    public void setDataChanged(int i, int j, int k, int l, int i1)
    {
        if(is_moving || !hasListeners())
        {
            return;
        } else
        {
            TableDataEvent tabledataevent = new TableDataEvent(this, i, j, k, l, i1);
            fireTableDataEvent(tabledataevent);
            return;
        }
    }

    public boolean addColumn(int i, Object obj, Vector vector)
    {
        Object obj1 = vector;
        if(vector != null && !(vector instanceof JCVector))
            obj1 = new JCVector(vector);
        if(i == 0x7fffffff)
            i = columns;
        else
        if(i < 0 || i > columns)
            return false;
        return addColumn(i, obj, vector, true);
    }

    protected boolean addColumn(int i, Object obj, Vector vector, boolean flag)
    {
        if(vector != null && cells.size() < vector.size())
            cells.setElementAt(vector.size() - 1, new JCVector());
        int j = 0;
        for(int k = 0; j < cells.size(); k++)
        {
            JCVector jcvector1 = (JCVector)cells.elementAt(j);
            JCVector jcvector;
            if(jcvector1 == null)
                cells.setElementAt(j, jcvector = new JCVector());
            else
                jcvector = jcvector1;
            if(i < jcvector.size())
                jcvector.insertElementAt(null, i);
            Object obj1 = vector == null || k >= vector.size() ? null : vector.elementAt(k);
            jcvector.setElementAt(i, obj1);
            j++;
        }

        if(flag && column_labels.size() != 0 && i < column_labels.size())
            column_labels.insertElementAt(null, i);
        if(obj != null && flag)
            setColumnLabel(i, obj);
        setNumColumns(columns + 1);
        setDataChanged(-1, i, 1, i, 6);
        return true;
    }

    public boolean addRow(int i, Object obj, Vector vector)
    {
        Object obj1 = vector;
        if(vector != null && !(vector instanceof JCVector))
            obj1 = new JCVector(vector);
        if(i == 0x7fffffff)
            i = rows;
        else
        if(i < 0 || i > rows)
            return false;
        return addRow(i, obj, ((Vector) (obj1)), true);
    }

    protected boolean addRow(int i, Object obj, Vector vector, boolean flag)
    {
        if(i < cells.size())
            cells.insertElementAt(null, i);
        cells.setElementAt(i, (JCVector)vector);
        if(flag && row_labels.size() != 0 && i < row_labels.size())
            row_labels.insertElementAt(null, i);
        if(obj != null && flag)
            setRowLabel(i, obj);
        setNumRows(rows + 1);
        setDataChanged(i, -1, 1, i, 3);
        return true;
    }

    public boolean deleteColumns(int i, int j)
    {
        return deleteColumns(i, j, true);
    }

    protected boolean deleteColumns(int i, int j, boolean flag)
    {
        if(j <= 0)
            return false;
        if(i < 0 || i + j > columns)
            return false;
        if(flag && column_labels.size() != 0 && i < column_labels.size())
            column_labels.removeElementsAt(i, j);
        for(int k = 0; k < cells.size(); k++)
        {
            JCVector jcvector;
            if((jcvector = (JCVector)cells.elementAt(k)) != null)
                jcvector.removeElementsAt(i, j);
        }

        setNumColumns(columns - j);
        setDataChanged(-1, i, j, 0, 7);
        return true;
    }

    public boolean deleteRows(int i, int j)
    {
        return deleteRows(i, j, true);
    }

    protected boolean deleteRows(int i, int j, boolean flag)
    {
        if(j <= 0)
            return false;
        if(i < 0 || i + j > rows)
            return false;
        if(flag && row_labels.size() != 0 && i < row_labels.size())
            row_labels.removeElementsAt(i, j);
        cells.removeElementsAt(i, j);
        setNumRows(rows - j);
        setDataChanged(i, -1, j, 0, 4);
        return true;
    }

    public boolean moveColumns(int i, int j, int k)
    {
        if(j <= 0)
            return false;
        if(i < 0 || i + j > columns && k != 0x7fffffff)
            return false;
        if(k < 0 || k > columns && k != 0x7fffffff || i <= k && k < i + j)
            return false;
        if(i + j == k)
            return true;
        else
            return moveColumns(i, j, k, true);
    }

    protected boolean moveColumns(int i, int j, int k, boolean flag)
    {
        is_moving = true;
        if(k == 0x7fffffff)
            k = columns;
        JCVector jcvector = new JCVector(j);
        JCVector jcvector1 = new JCVector(j);
        for(int l = 0; l < j; l++)
        {
            Vector vector = new Vector();
            for(int k1 = 0; k1 < rows; k1++)
                try
                {
                    vector.addElement(((Vector)cells.elementAt(k1)).elementAt(i + l));
                }
                catch(Exception _ex)
                {
                    return false;
                }

            jcvector.addElement(vector);
            jcvector1.addElement(getTableColumnLabel(i + l));
        }

        if(!deleteColumns(i, j, flag))
            return false;
        if(i < k)
        {
            for(int i1 = 0; i1 < j; i1++)
                if(!addColumn((k + i1) - j, jcvector1.elementAt(i1), (Vector)jcvector.elementAt(i1), flag))
                    return false;

        } else
        {
            for(int j1 = 0; j1 < j; j1++)
                if(!addColumn(k + j1, jcvector1.elementAt(j1), (Vector)jcvector.elementAt(j1), flag))
                    return false;

        }
        jcvector = null;
        jcvector1 = null;
        is_moving = false;
        setDataChanged(-998, i, j, k, 11);
        return true;
    }

    public boolean moveRows(int i, int j, int k)
    {
        if(j <= 0)
            return false;
        if(i < 0 || i + j > rows)
            return false;
        if(k < 0 || k > rows || i <= k && k < i + j)
            return false;
        if(i + j == k)
            return true;
        else
            return moveRows(i, j, k, true);
    }

    protected boolean moveRows(int i, int j, int k, boolean flag)
    {
        is_moving = true;
        JCVector jcvector = new JCVector(j);
        JCVector jcvector1 = new JCVector(j);
        for(int l = 0; l < j; l++)
            try
            {
                jcvector.addElement((Vector)cells.elementAt(i + l));
                jcvector1.addElement(getTableRowLabel(i + l));
            }
            catch(Exception _ex)
            {
                return false;
            }

        if(!deleteRows(i, j, flag))
            return false;
        if(i < k)
        {
            for(int i1 = 0; i1 < j; i1++)
                if(!addRow((k + i1) - j, jcvector1.elementAt(i1), (Vector)jcvector.elementAt(i1), flag))
                    return false;

        } else
        {
            for(int j1 = 0; j1 < j; j1++)
                if(!addRow(k + j1, jcvector1.elementAt(j1), (Vector)jcvector.elementAt(j1), flag))
                    return false;

        }
        jcvector = null;
        jcvector1 = null;
        is_moving = false;
        setDataChanged(i, -998, j, k, 10);
        return true;
    }

    protected JCVector cells;
    protected JCVector column_labels;
    protected JCVector row_labels;
    protected int columns;
    protected int rows;
    protected boolean is_moving;
}
