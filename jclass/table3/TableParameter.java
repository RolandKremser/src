// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableParameter.java

package jclass.table3;

import java.applet.Applet;
import java.awt.Component;
import java.util.Vector;
import jclass.util.*;

// Referenced classes of package jclass.table3:
//            JCTable, JCTblConverter, JCTblEnum, Span, 
//            Table, Validate

public class TableParameter
{

    static int toInt(Table table, String s, int i)
    {
        return JCUtilConverter.toInt(JCUtilConverter.getParam(table.applet, table, table.name, s), i);
    }

    static boolean toBoolean(Table table, String s, boolean flag)
    {
        return JCUtilConverter.toBoolean(table.applet, table, table.name, s, flag);
    }

    static int toEnum(Table table, String s, int i)
    {
        return toEnum(table, s, s, i);
    }

    static int toEnum(Table table, String s, String s1, int i)
    {
        String s2 = JCUtilConverter.getParam(table.applet, table, table.name, s);
        if(s2 != null)
            return JCTblConverter.toEnum(s2, s, s1, i);
        else
            return i;
    }

    static void getParams(Table table, String s)
    {
        Applet applet = table.applet;
        if(s == null)
            s = JCUtilConverter.getParam(applet, table, table.name, "paramFile");
        if(s != null)
            JCUtilConverter.setParamSource(table, JCFile.read(applet, s));
        setParameters(table, s);
    }

    static void setParameters(Table table, String s)
    {
        Applet applet = table.applet;
        if(table instanceof JCTable)
        {
            getCellValues(applet, (JCTable)table);
            ((JCTable)table).datatype_series = JCTblConverter.toSeries(applet, table, "DataTypeList", 9, ((JCTable)table).datatype_series, 5);
        }
        table.rows = toInt(table, "numRows", table.rows);
        table.frozen_rows = toInt(table, "frozenRows", table.frozen_rows);
        table.visible_rows = toInt(table, "visibleRows", table.visible_rows);
        table.top_row = toInt(table, "topRow", table.top_row);
        table.frozen_row_placement = toEnum(table, "frozenRowPlacement", "RowPlacement", table.frozen_row_placement);
        table.row_label_offset = toInt(table, "rowLabelOffset", table.row_label_offset);
        table.row_label_placement = toEnum(table, "rowLabelPlacement", "ColumnPlacement", table.row_label_placement);
        table.columns = toInt(table, "numColumns", table.columns);
        table.left_column = toInt(table, "leftColumn", table.left_column);
        table.frozen_columns = toInt(table, "frozenColumns", table.frozen_columns);
        table.visible_columns = toInt(table, "visibleColumns", table.visible_columns);
        table.frozen_column_placement = toEnum(table, "frozenColumnPlacement", "ColumnPlacement", table.frozen_column_placement);
        table.column_label_offset = toInt(table, "columnLabelOffset", table.column_label_offset);
        table.column_label_placement = toEnum(table, "columnLabelPlacement", "RowPlacement", table.column_label_placement);
        table.shadow_thickness = toInt(table, "shadowThickness", table.shadow_thickness);
        table.frame_shadow = toInt(table, "frameShadowThickness", table.frame_shadow);
        table.frame_bordertype = toEnum(table, "frameBorderType", "BorderType", table.frame_bordertype);
        table.clip_arrows = toInt(table, "displayClipArrows", table.clip_arrows);
        table.track_cursor = toBoolean(table, "trackCursor", table.track_cursor);
        if(table instanceof JCTable)
            ((JCTable)table).column_label_sort = toBoolean((JCTable)table, "columnLabelSort", ((JCTable)table).column_label_sort);
        table.double_buffer = toBoolean(table, "doubleBuffer", table.double_buffer);
        table.min_cell_visibility = toInt(table, "minCellVisibility", table.min_cell_visibility);
        table.jump = toEnum(table, "jump", "Jump", table.jump);
        table.allow_cell_resize = toEnum(table, "allowCellResize", "Resize", table.allow_cell_resize);
        table.margin_width = toInt(table, "marginWidth", table.margin_width);
        table.margin_height = toInt(table, "marginHeight", table.margin_height);
        String s1 = JCUtilConverter.getParam(applet, table, table.name, "RepeatBackgroundColors");
        if(s1 != null)
            table.repeat_bg_colors = JCUtilConverter.toColorList(s1);
        s1 = JCUtilConverter.getParam(applet, table, table.name, "RepeatForegroundColors");
        if(s1 != null)
            table.repeat_fg_colors = JCUtilConverter.toColorList(s1);
        table.alignment_series = JCTblConverter.toSeries(applet, table, "AlignmentList", 1, table.alignment_series, 0);
        table.bg_series = JCTblConverter.toSeries(applet, table, "BackgroundList", 7, table.bg_series, table.getBackground());
        table.bordersides_series = JCTblConverter.toSeries(applet, table, "BorderSidesList", 3, table.bordersides_series, 15);
        table.bordertype_series = JCTblConverter.toSeries(applet, table, "BorderTypeList", 4, table.bordertype_series, 3);
        table.charheight_series = JCTblConverter.toSeries(applet, table, "CharHeightList", 5, table.charheight_series, 1);
        table.charwidth_series = JCTblConverter.toSeries(applet, table, "CharWidthList", 6, table.charwidth_series, 10);
        table.font_series = JCTblConverter.toSeries(applet, table, "FontList", 10, table.font_series, table.getFont());
        table.fg_series = JCTblConverter.toSeries(applet, table, "ForegroundList", 7, table.fg_series, table.getForeground());
        s1 = JCUtilConverter.getParam(applet, table, table.name, "PixelHeightList");
        if(s1 != null)
            table.pixelheight_series = JCTblConverter.toSeries(applet, table, "PixelHeightList", 12, table.pixelheight_series, -999);
        s1 = JCUtilConverter.getParam(applet, table, table.name, "PixelWidthList");
        if(s1 != null)
            table.pixelwidth_series = JCTblConverter.toSeries(applet, table, "PixelWidthList", 13, table.pixelwidth_series, -999);
        s1 = JCUtilConverter.getParam(applet, table, table.name, "Spans");
        if(s1 != null)
            table.span.span_list_orig = JCTblConverter.toRangeList(s1);
        table.vertsb_display = toEnum(table, "vertScrollbarDisplay", "ScrollbarDisplay", table.vertsb_display);
        table.horizsb_display = toEnum(table, "horizScrollbarDisplay", "ScrollbarDisplay", table.horizsb_display);
        table.vertsb_position = toEnum(table, "vertScrollbarPosition", "ScrollbarPosition", table.vertsb_position);
        table.horizsb_position = toEnum(table, "horizScrollbarPosition", "ScrollbarPosition", table.horizsb_position);
        table.vertsb_offset = toInt(table, "VertScrollbarOffset", table.vertsb_offset);
        table.horizsb_offset = toInt(table, "HorizScrollbarOffset", table.horizsb_offset);
        table.horizsb_attach = toEnum(table, "HorizScrollbarAttachment", "Attachment", table.horizsb_attach);
        table.vertsb_attach = toEnum(table, "VertScrollbarAttachment", "Attachment", table.vertsb_attach);
        table.auto_scroll = toEnum(table, "autoScroll", "AutoScroll", table.auto_scroll);
        table.column_label_display = toBoolean(table, "columnLabelDisplay", table.column_label_display);
        table.edit_height_policy = toEnum(table, "editHeightPolicy", "EditPolicy", table.edit_height_policy);
        table.edit_width_policy = toEnum(table, "editWidthPolicy", "EditPolicy", table.edit_width_policy);
        s1 = JCUtilConverter.getParam(applet, table, table.name, "FocusRectColor");
        if(s1 != null)
            table.focus_rect_color = JCUtilConverter.toColor(s1);
        table.resize_by_labels_only = toBoolean(table, "resizeByLabelsOnly", table.resize_by_labels_only);
        table.row_label_display = toBoolean(table, "rowLabelDisplay", table.row_label_display);
        if(table instanceof JCTable)
            setTableProperties(applet, (JCTable)table);
        if(s != null)
            JCUtilConverter.setParamSource(table, null);
    }

    static void getCellValues(Applet applet, JCTable jctable)
    {
        boolean flag = false;
        String s = JCUtilConverter.getParam(applet, jctable, ((Table) (jctable)).name, "cellsFile");
        if(s != null)
        {
            String s1 = s;
            s = JCUtilConverter.getParam(applet, jctable, ((Table) (jctable)).name, "cellsFileFormat");
            if(s != null && s.equalsIgnoreCase("csv"))
            {
                jctable.setCells(JCFile.readCSV(applet, s1));
            } else
            {
                char c = '|';
                boolean flag1 = false;
                if(s != null)
                {
                    JCStringTokenizer jcstringtokenizer = new JCStringTokenizer(s);
                    c = jcstringtokenizer.nextToken('-').charAt(0);
                    flag1 = jcstringtokenizer.nextToken('-') != null;
                }
                if(flag1)
                {
                    jctable.setCells(JCFile.read(applet, s1, c, true));
                } else
                {
                    String s2 = JCFile.read(applet, s1);
                    jctable.setCells(JCTblConverter.toCellValues(applet, s2, c, true));
                }
            }
            flag = true;
        } else
        {
            s = JCUtilConverter.getParam(applet, jctable, ((Table) (jctable)).name, "cells");
            if(s != null)
            {
                jctable.setCells(JCTblConverter.toCellValues(applet, s, '|', true));
                flag = true;
            }
        }
        if(flag && jctable.getCells() != null)
        {
            jctable.setNumRows(jctable.getCells().size());
            int j = 0;
            for(int i = 0; i < jctable.getNumRows(); i++)
            {
                JCVector jcvector;
                if((jcvector = (JCVector)jctable.getCells().elementAt(i)) != null)
                    j = Math.max(j, jcvector.size());
            }

            jctable.setNumColumns(j);
        }
        s = JCUtilConverter.getParam(applet, jctable, ((Table) (jctable)).name, "columnLabels");
        if(s != null)
            jctable.setColumnLabels(JCUtilConverter.toVector(applet, s, '|', true));
        s = JCUtilConverter.getParam(applet, jctable, ((Table) (jctable)).name, "rowLabels");
        if(s != null)
            jctable.setRowLabels(JCUtilConverter.toVector(applet, s, '|', true));
    }

    static void setTableProperties(Applet applet, JCTable jctable)
    {
        jctable.mode = toEnum(jctable, "Mode", ((Table) (jctable)).mode);
        if(((Table) (jctable)).mode == 1)
            jctable.setModeList();
        jctable.selection_policy = toEnum(jctable, "SelectionPolicy", ((Table) (jctable)).selection_policy);
        String s = JCUtilConverter.getParam(applet, jctable, ((Table) (jctable)).name, "SelectedCells");
        if(s != null)
            jctable.selected_cells = JCTblConverter.toRangeList(s);
        s = JCUtilConverter.getParam(applet, jctable, ((Table) (jctable)).name, "SelectedBackground");
        if(s != null)
        {
            jctable.selected_bg = JCUtilConverter.toColor(s);
            jctable.selected_bg_set = true;
        }
        s = JCUtilConverter.getParam(applet, jctable, ((Table) (jctable)).name, "SelectedForeground");
        if(s != null)
        {
            jctable.selected_fg = JCUtilConverter.toColor(s);
            jctable.selected_fg_set = true;
        }
        jctable.editable_series = JCTblConverter.toSeries(applet, jctable, "EditableList", 2, ((Table) (jctable)).editable_series, true);
        jctable.maxlength_series = JCTblConverter.toSeries(applet, jctable, "MaxLengthList", 11, ((Table) (jctable)).maxlength_series, 0x7fffffff);
        jctable.multiline_series = JCTblConverter.toSeries(applet, jctable, "MultilineList", 2, ((Table) (jctable)).multiline_series, false);
        jctable.stringcase_series = JCTblConverter.toSeries(applet, jctable, "StringCaseList", 14, ((Table) (jctable)).stringcase_series, 0);
        jctable.traversable_series = JCTblConverter.toSeries(applet, jctable, "TraversableList", 2, ((Table) (jctable)).traversable_series, true);
        jctable.component_series = JCTblConverter.toSeries(applet, jctable, "ComponentList", 8, ((Table) (jctable)).component_series, null);
        jctable.validate_policy = toEnum(jctable, "ValidatePolicy", jctable.validate_policy);
        if((jctable.validate_policy & 2) != 0)
            Validate.cells(jctable, jctable.getCells());
    }

    public TableParameter()
    {
    }
}
