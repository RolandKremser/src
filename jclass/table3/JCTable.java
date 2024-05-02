// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTable.java

package jclass.table3;

import java.applet.Applet;
import java.awt.*;
import java.util.Vector;
import jclass.cell.CellEditor;
import jclass.util.JCSortable;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            Table, Clip, EditHandler, JCTableDataSource, 
//            JCTblEnum, LabelTrigger, Layout, PropertyEnum, 
//            Series, Sort, StandardCellBorder, TableDataView, 
//            TableParameter, TblConvertUtil, TraverseInitial, VectorDataSource, 
//            JCCellRange, JCCellPosition, JCCellValueListener, JCLabelValueListener, 
//            JCValidateCellListener

public class JCTable extends Table
{

    public JCTable()
    {
        this(null, null);
    }

    public JCTable(Applet applet, String s)
    {
        datatype_series = new Series(this, 5);
        cellValueListeners = new JCVector(0);
        labelValueListeners = new JCVector(0);
        validateCellListeners = new JCVector(0);
        column_label_sort = false;
        validate_policy = 0;
        super.name = s;
        data = new JCTableDataSource(this);
        getDataView().setDataSource(data);
        if(applet != null)
            getParameters(applet);
        setRowLabelDisplay(false);
        setColumnLabelDisplay(false);
    }

    public void getParameters(Applet applet)
    {
        super.applet = applet;
        super.applet_context = null;
        if(applet != null)
            try
            {
                super.applet_context = applet.getAppletContext();
            }
            catch(Exception _ex) { }
        super.defaultCellAppearance = null;
        if(super.applet_context != null)
            TableParameter.getParams(this, null);
        if(super.applet_context != null && isDisplayable())
            reset();
    }

    public void getParameters(Applet applet, String s)
    {
        if(applet != null)
        {
            super.applet = applet;
            try
            {
                super.applet_context = applet.getAppletContext();
            }
            catch(Exception _ex) { }
        }
        super.defaultCellAppearance = null;
        TableParameter.getParams(this, s);
        if(isDisplayable())
            reset();
    }

    private void reset()
    {
        addNotify();
        Layout.doLayout(this);
        if(super.traversable)
            TraverseInitial.traverse(this, false);
        repaint();
    }

    public void convert(JCVector jcvector, int i, int j)
    {
        if(datatype_series.getSingleValue() != new Integer(5))
            TblConvertUtil.convert(this, jcvector, i, j);
    }

    public void setDisplayClipArrows(boolean flag)
    {
        if(flag)
        {
            setClipArrows(4);
            return;
        } else
        {
            setClipArrows(0);
            return;
        }
    }

    public boolean getDisplayClipArrows()
    {
        return getClipArrows() == 4;
    }

    public void setBorderType(int i, int j, int k)
    {
        setCellBorderType(i, j, new StandardCellBorder(k));
    }

    public void setBorderType(JCCellRange jccellrange, int i)
    {
        setCellBorderType(jccellrange, new StandardCellBorder(i));
    }

    public int getBorderType(int i, int j)
    {
        CellBorder cellborder = getCellBorderType(i, j);
        if(cellborder == null || !(cellborder instanceof StandardCellBorder))
            return 8;
        else
            return ((StandardCellBorder)cellborder).getBorderType();
    }

    public int getBorderSides(int i, int j)
    {
        return getCellBorderSides(i, j);
    }

    public void setBorderSides(int i, int j, int k)
    {
        setCellBorderSides(i, j, k);
    }

    public void setBorderSides(JCCellRange jccellrange, int i)
    {
        setCellBorderSides(jccellrange, i);
    }

    public int getDatatype(int i, int j)
    {
        return datatype_series.getIntValue(i, j);
    }

    public void setDatatype(int i, int j, int k)
    {
        datatype_series.setValue(i, j, k);
    }

    public int getFrameShadowThickness()
    {
        return super.frame_shadow;
    }

    public void setFrameShadowThickness(int i)
    {
        if(isDisplayable() && super.repaint && super.frame_shadow > 0)
            Clip.drawFrames(getGraphics(), this, true);
        super.frame_shadow = i;
        setProperty(0x80080);
    }

    public int getShadowThickness()
    {
        return super.shadow_thickness;
    }

    public void setShadowThickness(int i)
    {
        super.shadow_thickness = i;
        setProperty(512);
    }

    public int getValidatePolicy()
    {
        return validate_policy;
    }

    public void setValidatePolicy(int i)
    {
        validate_policy = i;
    }

    public boolean addColumn(int i, Object obj, Vector vector)
    {
        return data.addColumn(i, obj, vector);
    }

    public boolean addRow(int i, Object obj, Vector vector)
    {
        return data.addRow(i, obj, vector);
    }

    public void clearCells()
    {
        data.clearCells();
    }

    public boolean deleteColumns(int i, int j)
    {
        return data.deleteColumns(i, j);
    }

    public boolean deleteRows(int i, int j)
    {
        return data.deleteRows(i, j);
    }

    public JCCellPosition eventToCell(Event event)
    {
        try
        {
            return XYToCell(event.x, event.y);
        }
        catch(Exception _ex)
        {
            return null;
        }
    }

    public Object getCell(int i, int j)
    {
        return data.getCell(i, j);
    }

    public JCVector getCells()
    {
        return data.getCells();
    }

    public Vector getColumnLabels()
    {
        return data.getColumnLabels();
    }

    public Object getColumnLabel(int i)
    {
        return data.getColumnLabel(i);
    }

    public Vector getRowLabels()
    {
        return data.getRowLabels();
    }

    public Object getRowLabel(int i)
    {
        return data.getRowLabel(i);
    }

    public TextComponent getTextComponent()
    {
        if(super.editHandler.getCellEditor() == null)
            return null;
        if(!(super.editHandler.getCellEditor().getComponent() instanceof TextComponent))
            return null;
        else
            return (TextComponent)super.editHandler.getCellEditor().getComponent();
    }

    public boolean moveColumns(int i, int j, int k)
    {
        return data.moveColumns(i, j, k);
    }

    public boolean moveRows(int i, int j, int k)
    {
        return data.moveRows(i, j, k);
    }

    public void setCell(int i, int j, Object obj)
    {
        if(obj instanceof Component)
            setComponent(i, j, (Component)obj);
        else
            data.setCell(i, j, obj);
        if(i == -1 && obj != null)
        {
            setColumnLabelDisplay(true);
            return;
        }
        if(j == -1 && obj != null)
            setRowLabelDisplay(true);
    }

    public void setCells(String as[][])
    {
        data.setCells(as);
    }

    public void setCells(Vector vector)
    {
        data.setCells(vector);
    }

    public void setColumnLabel(int i, Object obj)
    {
        if(obj instanceof Component)
            setComponent(-1, i, (Component)obj);
        else
            data.setColumnLabel(i, obj);
        if(obj != null)
            setColumnLabelDisplay(true);
    }

    public void setColumnLabels(String as[])
    {
        data.setColumnLabels(as);
        if(as != null)
            setColumnLabelDisplay(true);
    }

    public void setColumnLabels(Vector vector)
    {
        data.setColumnLabels(vector);
        if(vector != null)
            setColumnLabelDisplay(true);
    }

    public void setNumColumns(int i)
    {
        data.setNumColumns(i);
    }

    public void setNumRows(int i)
    {
        data.setNumRows(i);
    }

    public void setRowLabel(int i, Object obj)
    {
        if(obj instanceof Component)
            setComponent(i, -1, (Component)obj);
        else
            data.setRowLabel(i, obj);
        if(obj != null)
            setRowLabelDisplay(true);
    }

    public void setRowLabels(String as[])
    {
        data.setRowLabels(as);
        if(as != null)
            setRowLabelDisplay(true);
    }

    public void setRowLabels(Vector vector)
    {
        data.setRowLabels(vector);
        if(vector != null)
            setRowLabelDisplay(true);
    }

    public boolean sortByColumn(int i, JCSortable jcsortable)
    {
        return sortByColumn(i, jcsortable, 0);
    }

    public boolean sortByColumn(int i, JCSortable jcsortable, int j)
    {
        return JCSort.sortByColumn(this, i, j, jcsortable);
    }

    public boolean getColumnLabelSort()
    {
        return column_label_sort;
    }

    public void setColumnLabelSort(boolean flag)
    {
        column_label_sort = flag;
        if(flag)
        {
            setColumnTrigger(new LabelTrigger(0, 1));
            return;
        }
        if(super.column_triggers == null)
        {
            return;
        } else
        {
            removeColumnTrigger(0);
            return;
        }
    }

    public void addCellValueListener(JCCellValueListener jccellvaluelistener)
    {
        cellValueListeners.addUnique(jccellvaluelistener);
    }

    public void removeCellValueListener(JCCellValueListener jccellvaluelistener)
    {
        cellValueListeners.removeElement(jccellvaluelistener);
    }

    public void addLabelValueListener(JCLabelValueListener jclabelvaluelistener)
    {
        labelValueListeners.addUnique(jclabelvaluelistener);
        setRowLabelDisplay(true);
        setColumnLabelDisplay(true);
    }

    public void removeLabelValueListener(JCLabelValueListener jclabelvaluelistener)
    {
        labelValueListeners.removeElement(jclabelvaluelistener);
    }

    public void addValidateCellListener(JCValidateCellListener jcvalidatecelllistener)
    {
        validateCellListeners.addUnique(jcvalidatecelllistener);
    }

    public void removeValidateCellListener(JCValidateCellListener jcvalidatecelllistener)
    {
        validateCellListeners.removeElement(jcvalidatecelllistener);
    }

    JCTableDataSource data;
    Series datatype_series;
    protected JCVector cellValueListeners;
    protected JCVector labelValueListeners;
    protected JCVector validateCellListeners;
    boolean column_label_sort;
    int validate_policy;
}
