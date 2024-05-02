// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Widget.java

package jclass.table3;

import java.awt.*;
import java.util.Vector;

// Referenced classes of package jclass.table3:
//            Clip, EditHandler, JCCellPosition, JCCellRange, 
//            JCCreateComponentEvent, JCDisplayComponentEvent, JCTblEnum, JDKSupport, 
//            PropertyEnum, Series, SeriesValue, Span, 
//            Table, VarSize, WidgetSeriesValue

class Widget
{

    private static void set_size(Clip clip, Component component, int i, int j, Point point)
    {
        Table table = clip.table;
        int i1 = table.rowHeight(i);
        int l = table.columnWidth(j);
        if(table.span.span_list.size() > 0)
        {
            Dimension dimension = new Dimension();
            if(table.span.find(i, j, null, dimension) != -999)
            {
                i1 = dimension.height;
                l = dimension.width;
            }
        }
        int k = table.shadow_thickness + 1;
        i1 = Math.max(1, i1 - 2 * k);
        l = Math.max(1, l - 2 * k);
        if(component.getParent() != clip)
        {
            component.hide();
            clip.add(component);
        }
        component.setBounds(point.x + k, point.y + k, l, i1);
        component.validate();
        component.setSize(l, i1);
        if(!component.isVisible())
            component.show();
    }

    private static boolean isVisible(Table table, Component component, int i, int j)
    {
        if(!Table.isCell(i, j) && !Table.isLabel(i, j))
            return false;
        if(table.span.span_list.size() > 0)
        {
            JCCellRange jccellrange = new JCCellRange();
            if(table.span.find(i, j, jccellrange, null) != -999)
            {
                if(jccellrange.start_row != i || jccellrange.start_column != j)
                    return false;
                Clip clip = Clip.find(table, i, j);
                if(clip == null)
                    return false;
                Point point = table.getPosition(i, j, null);
                if(point == null)
                    return false;
                int k = point.x + clip.location().x;
                int l = point.y + clip.location().y;
                Rectangle rectangle = new Rectangle();
                JDKSupport.setBounds(rectangle, k, l, component.size().width, component.size().height);
                if(!clip.bounds().intersects(rectangle))
                    return false;
            } else
            if(!table.isVisible(i, j))
                return false;
        } else
        if(!table.isVisible(i, j))
            return false;
        return table.rowHeight(i) != 0 && table.columnWidth(j) != 0;
    }

    static void move_component(Table table, Component component, int i, int j)
    {
        Clip clip = Clip.find(table, i, j);
        Point point = new Point(0, 0);
        table.getPosition(i, j, point);
        set_size(clip, component, i, j, point);
    }

    private static boolean hasOrientation(Component component, int i)
    {
        return (component.getParent() instanceof Clip) && ((Clip)component.getParent()).hasOrientation(i);
    }

    static void scroll(Table table, int i)
    {
        Series series = table.component_series;
        for(int j = 0; j < series.size(); j++)
        {
            WidgetSeriesValue widgetseriesvalue = (WidgetSeriesValue)series.getValue(j);
            if(widgetseriesvalue != null)
            {
                int k = ((SeriesValue)series.elementAt(j)).row;
                int l = ((SeriesValue)series.elementAt(j)).column;
                if((Table.isCell(k, l) || Table.isLabel(k, l)) && hasOrientation(widgetseriesvalue.component, i))
                    if(isVisible(table, widgetseriesvalue.component, k, l))
                        move_component(table, widgetseriesvalue.component, k, l);
                    else
                        widgetseriesvalue.component.hide();
                for(int i1 = 0; i1 < widgetseriesvalue.clones.length; i1++)
                    if(hasOrientation(widgetseriesvalue.clones[i1], i))
                        if(isVisible(table, widgetseriesvalue.clones[i1], widgetseriesvalue.row[i1], widgetseriesvalue.column[i1]))
                            move_component(table, widgetseriesvalue.clones[i1], widgetseriesvalue.row[i1], widgetseriesvalue.column[i1]);
                        else
                            widgetseriesvalue.clones[i1].hide();

            }
        }

    }

    private static Component create(Table table, int i, int j, Component component)
    {
        Component component1 = null;
        JCCreateComponentEvent jccreatecomponentevent = new JCCreateComponentEvent(table, i, j, component);
        table.fireJCCreateComponentEvent(jccreatecomponentevent);
        if(jccreatecomponentevent.component != null)
            component1 = jccreatecomponentevent.component;
        try
        {
            if(component1 == null)
                component1 = (Component)component.getClass().newInstance();
        }
        catch(Exception _ex) { }
        if(component1 != null)
        {
            component1.removeFocusListener(table.traverseHandler);
            component1.addFocusListener(table.traverseHandler);
        }
        return component1;
    }

    private static int create_clone(Table table, int i, int j, WidgetSeriesValue widgetseriesvalue)
    {
        Component component = create(table, i, j, widgetseriesvalue.component);
        int l = widgetseriesvalue.clones.length;
        Component acomponent[] = widgetseriesvalue.clones;
        boolean aflag[] = widgetseriesvalue.moved;
        boolean aflag1[] = widgetseriesvalue.needs_displaycb;
        int ai[] = widgetseriesvalue.row;
        int ai1[] = widgetseriesvalue.column;
        widgetseriesvalue.clones = new Component[l + 1];
        widgetseriesvalue.moved = new boolean[l + 1];
        widgetseriesvalue.needs_displaycb = new boolean[l + 1];
        widgetseriesvalue.row = new int[l + 1];
        widgetseriesvalue.column = new int[l + 1];
        if(l > 0)
        {
            System.arraycopy(acomponent, 0, widgetseriesvalue.clones, 0, l);
            System.arraycopy(aflag, 0, widgetseriesvalue.moved, 0, l);
            System.arraycopy(aflag1, 0, widgetseriesvalue.needs_displaycb, 0, l);
            System.arraycopy(ai, 0, widgetseriesvalue.row, 0, l);
            System.arraycopy(ai1, 0, widgetseriesvalue.column, 0, l);
        }
        widgetseriesvalue.clones[l] = component;
        widgetseriesvalue.moved[l] = true;
        widgetseriesvalue.needs_displaycb[l] = true;
        int k;
        for(k = 0; k < l; k++)
            if(i == widgetseriesvalue.row[k] && j == widgetseriesvalue.column[k])
                break;

        if(k == l)
        {
            widgetseriesvalue.row[l] = i;
            widgetseriesvalue.column[l] = j;
        } else
        {
            widgetseriesvalue.row[l] = widgetseriesvalue.column[l] = -999;
        }
        return l;
    }

    private static int find_clone(Clip clip, int i, int j, WidgetSeriesValue widgetseriesvalue)
    {
        for(int k = 0; k < widgetseriesvalue.clones.length; k++)
            if(widgetseriesvalue.row[k] == i && widgetseriesvalue.column[k] == j)
                return k;

        int l = -999;
        int i1 = -999;
        for(int j1 = 0; j1 < widgetseriesvalue.clones.length; j1++)
        {
            Component component = widgetseriesvalue.clones[j1];
            if(l == -999 && clip.table.scrolling && component.isVisible() && component.getParent() == clip && !isVisible(clip.table, component, widgetseriesvalue.row[j1], widgetseriesvalue.column[j1]))
            {
                l = j1;
                widgetseriesvalue.moved[j1] = true;
            }
            if(i1 == -999 && !component.isVisible())
                i1 = j1;
        }

        if(l == -999)
            l = i1;
        if(l == -999)
        {
            return create_clone(clip.table, i, j, widgetseriesvalue);
        } else
        {
            widgetseriesvalue.row[l] = i;
            widgetseriesvalue.column[l] = j;
            widgetseriesvalue.moved[l] = true;
            return l;
        }
    }

    static boolean draw(Table table, int i, int j)
    {
        Series series = table.component_series;
        boolean flag = false;
        int k = 0;
        int l = series.find(i, j);
        if(l == -999)
            return false;
        SeriesValue seriesvalue = (SeriesValue)series.elementAt(l);
        WidgetSeriesValue widgetseriesvalue = (WidgetSeriesValue)seriesvalue.value;
        if(widgetseriesvalue == null)
            return false;
        Component component1;
        Component component = component1 = widgetseriesvalue.component;
        Clip clip = Clip.find(table, i, j);
        if(clip == null)
            return false;
        if(seriesvalue.row != i || seriesvalue.column != j)
        {
            k = find_clone(clip, i, j, widgetseriesvalue);
            component1 = widgetseriesvalue.clones[k];
            flag = true;
        }
        if(table.hasText() && table.edit_row == i && table.edit_column == j)
            table.editHandler.cancel(true);
        if(table.displayComponentListeners != null && (flag && widgetseriesvalue.moved[k] || flag && widgetseriesvalue.needs_displaycb[k]))
        {
            JCDisplayComponentEvent jcdisplaycomponentevent = new JCDisplayComponentEvent(table, i, j, component1);
            table.fireJCDisplayComponentEvent(jcdisplaycomponentevent);
            if(widgetseriesvalue.moved != null)
                widgetseriesvalue.moved[k] = false;
            widgetseriesvalue.needs_displaycb[k] = false;
        }
        Point point = new Point(0, 0);
        table.getPosition(i, j, point);
        set_size(clip, component1, i, j, point);
        return true;
    }

    private static boolean is_child(Component component, Component component1)
    {
        if(component == null)
            return false;
        if(component == component1)
            return true;
        for(Object obj = component.getParent(); obj != null && obj != component1 && (component = ((Component) (obj)).getParent()) != null; obj = component);
        return component != null;
    }

    static JCCellPosition getPosition(Table table, Component component, JCCellPosition jccellposition)
    {
        Series series = table.component_series;
        int j = -999;
        int k = -999;
        boolean flag = false;
        for(int i = 0; i < series.size(); i++)
        {
            WidgetSeriesValue widgetseriesvalue = (WidgetSeriesValue)series.getValue(i);
            if(widgetseriesvalue == null)
                continue;
            j = ((SeriesValue)series.elementAt(i)).row;
            k = ((SeriesValue)series.elementAt(i)).column;
            if((Table.isCell(j, k) || Table.isLabel(j, k)) && is_child(component, widgetseriesvalue.component))
            {
                flag = true;
                break;
            }
            for(int l = 0; l < widgetseriesvalue.clones.length; l++)
            {
                if(!is_child(component, widgetseriesvalue.clones[l]))
                    continue;
                j = widgetseriesvalue.row[l];
                k = widgetseriesvalue.column[l];
                flag = true;
                break;
            }

            if(flag)
                break;
        }

        if(!flag)
        {
            if(jccellposition != null)
                jccellposition.row = jccellposition.column = -999;
            return null;
        }
        if(jccellposition != null)
        {
            jccellposition.row = j;
            jccellposition.column = k;
            return null;
        } else
        {
            return new JCCellPosition(j, k);
        }
    }

    static boolean componentExistsInTable(Table table, Component component)
    {
        Series series = table.component_series;
        for(int i = 0; i < series.size(); i++)
        {
            WidgetSeriesValue widgetseriesvalue = (WidgetSeriesValue)series.getValue(i);
            if(widgetseriesvalue != null)
            {
                if(is_child(component, widgetseriesvalue.component))
                    return true;
                for(int j = 0; j < widgetseriesvalue.clones.length; j++)
                    if(is_child(component, widgetseriesvalue.clones[j]))
                        return true;

            }
        }

        return false;
    }

    static Component getComponent(Table table, int i, int j, boolean flag)
    {
        Series series = table.component_series;
        Component component = null;
        WidgetSeriesValue widgetseriesvalue = null;
        boolean flag1 = false;
        for(int l = 0; l < series.size(); l++)
        {
            widgetseriesvalue = (WidgetSeriesValue)series.getValue(l);
            if(widgetseriesvalue == null)
                continue;
            if(i == ((SeriesValue)series.elementAt(l)).row && j == ((SeriesValue)series.elementAt(l)).column)
            {
                component = widgetseriesvalue.component;
                break;
            }
            for(int i1 = 0; i1 < widgetseriesvalue.clones.length; i1++)
            {
                if(i != widgetseriesvalue.row[i1] || j != widgetseriesvalue.column[i1])
                    continue;
                component = widgetseriesvalue.clones[i1];
                flag1 = true;
                break;
            }

            if(flag1)
                break;
        }

        if(flag && component == null && (widgetseriesvalue = (WidgetSeriesValue)series.getValue(i, j)) != null)
        {
            int k;
            if(widgetseriesvalue.clones.length > 0)
                k = 0;
            else
                k = create_clone(table, i, j, widgetseriesvalue);
            component = widgetseriesvalue.clones[k];
        }
        if(component != null)
        {
            Object obj = Clip.find(table, i, j);
            if(obj == null)
                obj = table;
            if(component.getParent() != obj)
            {
                boolean flag2 = component.isVisible();
                ((Container) (obj)).add(component);
                component.show(flag2);
            }
        }
        return component;
    }

    static void unmanageInvisible(Table table)
    {
        Table table1 = table;
        Series series = table1.component_series;
        for(int i = 0; i < series.size(); i++)
        {
            WidgetSeriesValue widgetseriesvalue = (WidgetSeriesValue)series.getValue(i);
            if(widgetseriesvalue != null)
            {
                int j = ((SeriesValue)series.elementAt(i)).row;
                int k = ((SeriesValue)series.elementAt(i)).column;
                if(isVisible(table1, widgetseriesvalue.component, j, k))
                    move_component(table1, widgetseriesvalue.component, j, k);
                else
                if(j != -1 || k != -1)
                {
                    widgetseriesvalue.component.hide();
                    for(int l = 0; l < widgetseriesvalue.clones.length; l++)
                        widgetseriesvalue.needs_displaycb[l] = true;

                }
                for(int i1 = 0; i1 < widgetseriesvalue.clones.length; i1++)
                    if(!isVisible(table1, widgetseriesvalue.clones[i1], widgetseriesvalue.row[i1], widgetseriesvalue.column[i1]))
                        widgetseriesvalue.clones[i1].hide();

            }
        }

    }

    static void setValue(Table table, int i, int j, Component component)
    {
        Series series = table.component_series;
        if(component != null)
        {
            component.removeFocusListener(table.traverseHandler);
            component.addFocusListener(table.traverseHandler);
        }
        if(table.in_setComponent)
        {
            series.setValue(i, j, component);
            return;
        }
        boolean flag = table.isShowing();
        if(component != null)
        {
            component.hide();
            for(int k = 0; k < series.size(); k++)
            {
                WidgetSeriesValue widgetseriesvalue = (WidgetSeriesValue)series.getValue(k);
                if(widgetseriesvalue == null || widgetseriesvalue.component != component)
                    continue;
                for(int k1 = 0; k1 < widgetseriesvalue.clones.length; k1++)
                    widgetseriesvalue.clones[k1].hide();

                series.removeAt(k);
                break;
            }

        }
        Component component1;
        if((component1 = getComponent(table, i, j, false)) != null)
        {
            component1.hide();
            int l = series.findExact(i, j);
            WidgetSeriesValue widgetseriesvalue1 = (WidgetSeriesValue)series.getValue(l);
            for(int l1 = 0; widgetseriesvalue1 != null && l1 < widgetseriesvalue1.clones.length; l1++)
                widgetseriesvalue1.clones[l1].hide();

            series.removeAt(l);
        } else
        if(i == -998 && j == -998)
            series.removeAllElements();
        if(!table.isDisplayable())
        {
            series.setValue(i, j, component);
        } else
        {
            table.in_setComponent = true;
            int i1 = 0x10000;
            if(Table.isColumnLabel(i, j) && table.column_label_height == 0)
                i1 |= 0x4080;
            else
            if(Table.isRowLabel(i, j) && table.row_label_width == 0)
                i1 |= 0x200080;
            int j1 = table.getPixelWidth(j);
            int i2 = table.getPixelHeight(i);
            if(j1 == 33001 || i2 == 33001)
                i1 |= VarSize.calc(table, i, j, j1, i2);
            table.setProperty(i1, i, j);
            series.setValue(i, j, component);
            table.paint(new JCCellRange(i, j, i, j));
            table.in_setComponent = false;
        }
        if(flag)
            table.show();
    }

    static boolean checkChild(Table table, Event event)
    {
        Component component = (Component)event.target;
        if((component instanceof Table) && component != table)
            return true;
        return (component.getParent() instanceof Table) && component.getParent() != table;
    }

    Widget()
    {
    }
}
