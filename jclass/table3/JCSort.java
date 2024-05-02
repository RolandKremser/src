// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Sort.java

package jclass.table3;

import jclass.util.JCSortable;

// Referenced classes of package jclass.table3:
//            JCSortEvent, JCTblEnum, Table, TableData, 
//            TableDataSortable, TableDataView

public class JCSort
{

    public static boolean sortByColumn(Table table, int i, int j, JCSortable jcsortable)
    {
        return sortByColumn(table, i, j, table.getFrozenRows(), table.dataView.getNumRows() - 1, jcsortable);
    }

    public static boolean sortByColumn(Table table, int i, int j, int k, int l, JCSortable jcsortable)
    {
        int ai[] = {
            i
        };
        int ai1[] = {
            j
        };
        return sortByColumn(table, ai, ai1, k, l, jcsortable);
    }

    public static boolean sortByColumn(Table table, int ai[], int ai1[], JCSortable jcsortable)
    {
        return sortByColumn(table, ai, ai1, table.getFrozenRows(), table.dataView.getNumRows() - 1, jcsortable);
    }

    public static boolean sortByColumn(Table table, int ai[], int ai1[], int i, int j, JCSortable jcsortable)
    {
        if(jcsortable == null)
            jcsortable = new TableDataSortable();
        if(ai.length == 1)
        {
            table.sort_column = ai[0];
            table.sort_direction = ai1[0];
        } else
        {
            table.sort_column = -999;
            table.sort_direction = -999;
        }
        int k = table.dataView.getNumRows();
        if(i < 0)
            i = 0;
        if(j >= k)
            j = k - 1;
        int ai2[] = new int[k];
        for(int l = 0; l < k; l++)
            ai2[l] = table.dataView.getDataRow(l);

        sort(table, ai2, ai, ai1, i, j, jcsortable);
        table.dataView.setSwappedRows(ai2);
        JCSortEvent jcsortevent = new JCSortEvent(table, ai, table.dataView.getSwappedRows());
        table.fireJCSortEvent(jcsortevent);
        return true;
    }

    static void sort(Table table, int ai[], int ai1[], int ai2[], int i, int j, JCSortable jcsortable)
    {
        if(i >= j)
            return;
        swap(table, ai, i, (i + j) / 2);
        TableData tabledata = table.dataView.getDataSource();
        int k = i;
        for(int i1 = i + 1; i1 <= j; i1++)
        {
            for(int j1 = 0; j1 < ai1.length; j1++)
            {
                long l = jcsortable.compare(tabledata.getTableDataItem(ai[i1], ai1[j1]), tabledata.getTableDataItem(ai[i], ai1[j1]));
                if(l < 0L && ai2[j1] == 0 || l > 0L && ai2[j1] == 1)
                {
                    swap(table, ai, ++k, i1);
                    break;
                }
                if(l != 0L)
                    break;
            }

        }

        swap(table, ai, i, k);
        sort(table, ai, ai1, ai2, i, k - 1, jcsortable);
        sort(table, ai, ai1, ai2, k + 1, j, jcsortable);
    }

    private static void swap(Table table, int ai[], int i, int j)
    {
        int k = ai[i];
        ai[i] = ai[j];
        ai[j] = k;
    }

    public JCSort()
    {
    }

    public static final int ASCENDING = 0;
    public static final int DESCENDING = 1;
}
