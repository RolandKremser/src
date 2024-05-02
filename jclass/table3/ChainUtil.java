// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChainUtil.java

package jclass.table3;

import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            JCTblEnum, Run

class ChainUtil implements Serializable
{

    ChainUtil(int i)
    {
        runs = new JCRun[0];
        if(i > 0)
        {
            runs = makeRuns(1);
            runs[0].value = -999;
            runs[0].end = i - 1;
        }
    }

    ChainUtil(int ai[])
    {
        runs = new JCRun[0];
        for(int i = 0; i < ai.length; i++)
            append(ai[i]);

    }

    ChainUtil()
    {
        runs = new JCRun[0];
    }

    private void resetCache()
    {
        last_pos = last_run = 0;
    }

    private int RunLeftOfStart(JCRun arun[], int i, int j)
    {
        if(arun[i].start == j)
            return i - 1;
        else
            return i;
    }

    private int RunRightOfEnd(JCRun arun[], int i, int j)
    {
        if(arun[i].end == j)
            return i + 1;
        else
            return i;
    }

    private static final void copy(JCRun arun[], JCRun arun1[], int i, int j, int k)
    {
        int l = Math.min(arun1.length - j, arun.length - i);
        l = Math.min(l, k);
        for(int i1 = 0; i1 < l; i1++)
            arun[i1 + i].copy(arun1[i1 + j]);

    }

    private static final JCRun[] makeRuns(int i)
    {
    	JCRun arun[] = new JCRun[i];
        if(arun != null)
        {
            for(int j = 0; j < i; j++)
                arun[j] = new JCRun();

        }
        return arun;
    }

    private int getArrayPos(int i)
    {
        int j = 0;
        int k = runs.length - 1;
        int l = (k + j) / 2;
        while(k >= j) 
        {
            l = (k + j) / 2;
            if(i < runs[l].start)
            {
                k = l - 1;
                continue;
            }
            if(i <= runs[l].end)
                break;
            j = l + 1;
        }
        last_pos = i;
        last_run = l;
        return l;
    }

    private int fetchIndex(int i)
    {
        if(last_run >= runs.length)
            return getArrayPos(i);
        if(i == last_pos)
            return last_run;
        if(i <= runs[last_run].end && i >= runs[last_run].start)
        {
            last_pos = i;
            return last_run;
        }
        if(i == last_pos - 1)
        {
            last_pos--;
            return --last_run;
        }
        if(i == last_pos + 1)
        {
            last_pos++;
            return ++last_run;
        }
        if(i >= runs[runs.length - 1].start)
        {
            last_pos = i;
            return last_run = runs.length - 1;
        }
        if(i <= runs[0].end)
        {
            last_pos = i;
            return last_run = 0;
        } else
        {
            return getArrayPos(i);
        }
    }

    private static final int SumBeforeRun(JCRun arun[], int i)
    {
        if(i > 0)
            return arun[i - 1].sum + arun[i - 1].value * ((arun[i - 1].end - arun[i - 1].start) + 1);
        else
            return 0;
    }

    private static final int SumAtRun(JCRun arun[], int i)
    {
        if(i >= 0)
            return arun[i].sum + arun[i].value * ((arun[i].end - arun[i].start) + 1);
        else
            return 0;
    }

    private static final int SumAtPos(JCRun arun[], int i, int j)
    {
        if(i >= 0)
            return arun[i].sum + arun[i].value * ((j - arun[i].start) + 1);
        else
            return 0;
    }

    private static final void PropagateSum(JCRun arun[], int i, int j, int k)
    {
        int l = Math.min(j, arun.length - i);
        for(int i1 = 0; i1 < l; i1++)
            arun[i1 + i].sum += k;

    }

    private static final void ShuffleUp(JCRun arun[], int i, int j, int k, int l)
    {
        int i1 = Math.min(j, arun.length - i);
        for(int j1 = 0; j1 < i1; j1++)
        {
            arun[j1 + i].start -= l;
            arun[j1 + i].end -= l;
            arun[j1 + i].sum -= k;
        }

    }

    private static final void ShuffleDown(JCRun arun[], int i, int j, int k, int l)
    {
        ShuffleUp(arun, i, j, -k, -l);
    }

    private void ShuffleDownFromPos(JCRun arun[], int i, int j, int k, int l, int i1)
    {
        if(arun == null)
            return;
        if(arun[i].start == i1)
        {
            ShuffleDown(arun, i, j, k, l);
            return;
        }
        arun[i].end += l;
        if(j - 1 > 0)
            ShuffleDown(arun, i + 1, j - 1, k, l);
    }

    private static final int CalcDifference(JCRun arun[], int i, int j, int k, int l, int i1)
    {
        int k1 = 0;
        if(l == i1)
        {
            k1 += (k - arun[l].value) * ((j - i) + 1);
        } else
        {
            k1 += (k - arun[l].value) * ((arun[l].end - i) + 1);
            for(int j1 = l + 1; j1 < i1; j1++)
                k1 += (k - arun[j1].value) * ((arun[j1].end - arun[j1].start) + 1);

            k1 += (k - arun[i1].value) * ((j - arun[i1].start) + 1);
        }
        return k1;
    }

    protected Object clone()
    {
        ChainUtil chainutil = new ChainUtil();
        if(runs != null && runs.length != 0)
        {
            chainutil.runs = makeRuns(runs.length);
            copy(chainutil.runs, runs, 0, 0, runs.length);
            chainutil.resetCache();
        }
        return chainutil;
    }

    private static final boolean BlocksMerge(ChainUtil chainutil, int i, int j)
    {
        return i >= 0 && j < chainutil.runs.length && chainutil.runs[i].value == chainutil.runs[j].value;
    }

    private static final int GetNumNewRuns(ChainUtil chainutil, boolean flag, int i, int j)
    {
        if(flag)
            return chainutil.runs.length - (j - i);
        else
            return chainutil.runs.length - (j - i - 1);
    }

    void delete(int i, int j)
    {
        delete(i, j, null);
    }

    void delete(int i, int j, ChainUtil chainutil)
    {
        if(runs == null || runs.length == 0)
            return;
        int k2 = runs.length - 1;
        if(i < 0 || i > runs[k2].end)
            return;
        if(j < 0 || j > runs[k2].end)
            return;
        if(j < i)
            return;
        if(i == 0 && j == runs[k2].end)
        {
            if(chainutil != null)
            {
                chainutil.runs = runs;
                chainutil.resetCache();
            }
            runs = null;
            last_pos = 0;
            last_run = 0;
            return;
        }
        int j1 = fetchIndex(i);
        int l1 = RunLeftOfStart(runs, j1, i);
        int k1 = fetchIndex(j);
        int i2 = RunRightOfEnd(runs, k1, j);
        resetCache();
        int i1 = (j - i) + 1;
        int l2 = -1 * CalcDifference(runs, i, j, 0, j1, k1);
        boolean flag = BlocksMerge(this, l1, i2);
        int k = GetNumNewRuns(this, flag, l1, i2);
        if(chainutil != null)
        {
            int l = (k1 - j1) + 1;
            if(l > 0)
            {
            	JCRun arun1[] = chainutil.runs = makeRuns(l);
                copy(arun1, runs, 0, j1, l);
                arun1[0].sum = arun1[0].sum + arun1[0].value * (i - arun1[0].start);
                arun1[0].start = i;
                arun1[l - 1].end = j;
                ShuffleUp(arun1, 0, l, arun1[0].sum, i);
            }
        }
        if(flag)
        {
            runs[l1].end = runs[i2].end - i1;
        } else
        {
            if(l1 >= 0)
                runs[l1].end = i - 1;
            if(i2 < runs.length)
            {
                runs[i2].start = i;
                runs[i2].end -= i1;
                runs[i2].sum = l1 < 0 ? 0 : SumAtRun(runs, l1);
            }
        }
        if(i2 + 1 < runs.length)
            ShuffleUp(runs, i2 + 1, runs.length - (i2 + 1), l2, i1);
        if(k != runs.length)
        {
            JCRun arun[] = makeRuns(k);
            int j2 = l1 + 1;
            copy(arun, runs, 0, 0, j2);
            if(flag)
                copy(arun, runs, j2, i2 + 1, runs.length - (i2 + 1));
            else
                copy(arun, runs, j2, i2, runs.length - i2);
            runs = arun;
        }
    }

    private static final boolean INSERT_LEFT_MERGE(ChainUtil chainutil, ChainUtil chainutil1, int i)
    {
        return i >= 0 && chainutil.runs[i].value == chainutil1.runs[0].value;
    }

    private static final boolean INSERT_RIGHT_MERGE(ChainUtil chainutil, ChainUtil chainutil1, int i)
    {
        return i < chainutil.runs.length && chainutil.runs[i].value == chainutil1.runs[chainutil1.runs.length - 1].value;
    }

    private static final int getNumNewRunsForInsert(ChainUtil chainutil, ChainUtil chainutil1, boolean flag, boolean flag1, int i, int j)
    {
        return ((chainutil.runs.length + chainutil1.runs.length) - (flag ? 1 : 0) - (flag1 ? 1 : 0)) + (j >= chainutil.runs.length || i == chainutil.runs[j].start ? 0 : 1);
    }

    void insert(ChainUtil chainutil, int i)
    {
        if(runs == null || runs.length == 0)
            return;
        int k = runs.length - 1;
        if(chainutil == null || chainutil.runs == null || chainutil.runs.length == 0)
            return;
        JCRun arun1[] = chainutil.runs;
        int j = chainutil.runs.length - 1;
        if(i < 0 || i > runs[runs.length - 1].end + 1)
            return;
        int j1 = i != 0 ? fetchIndex(i - 1) : -1;
        int k1 = i <= runs[k].end ? fetchIndex(i) : k + 1;
        resetCache();
        if(j1 >= 0 && i < runs[j1].start)
            return;
        boolean flag = INSERT_LEFT_MERGE(this, chainutil, j1);
        boolean flag1 = INSERT_RIGHT_MERGE(this, chainutil, k1);
        int l = getNumNewRunsForInsert(this, chainutil, flag, flag1, i, k1);
        if(k1 < runs.length)
            ShuffleDownFromPos(runs, k1, runs.length - k1, SumAtRun(arun1, j), arun1[j].end + 1, i);
        ShuffleDown(arun1, 0, chainutil.runs.length, SumAtPos(runs, j1, i - 1), i);
        if(flag)
        {
            arun1[0].start = runs[j1].start;
            arun1[0].sum = runs[j1].sum;
        }
        if(flag1)
            arun1[j].end = runs[k1].end;
        if(l == runs.length)
        {
            if(flag)
                runs[j1].end = arun1[0].end;
            if(flag1)
            {
                runs[k1].start = arun1[j].start;
                runs[k1].sum = SumBeforeRun(runs, k1);
                return;
            }
        } else
        {
            JCRun arun[] = makeRuns(l);
            copy(arun, runs, 0, 0, j1 + 1);
            int i1;
            if(flag)
            {
                copy(arun, arun1, j1, 0, chainutil.runs.length);
                i1 = (j1 + chainutil.runs.length) - 1;
            } else
            {
                if(j1 >= 0)
                    arun[j1].end = i - 1;
                copy(arun, arun1, j1 + 1, 0, chainutil.runs.length);
                i1 = j1 + chainutil.runs.length;
            }
            if(flag1)
                copy(arun, runs, i1 + 1, k1 + 1, runs.length - (k1 + 1));
            else
            if(i1 + 1 < l)
            {
                copy(arun, runs, i1 + 1, k1, runs.length - k1);
                arun[i1 + 1].sum = SumBeforeRun(arun, i1 + 1);
                arun[i1 + 1].start = arun1[j].end + 1;
            }
            runs = arun;
        }
    }

    void move(int i, int j, int k)
    {
        ChainUtil chainutil = new ChainUtil();
        k -= k <= i ? 0 : j;
        ChainUtil chainutil1 = (ChainUtil)clone();
        try
        {
            delete(i, (i + j) - 1, chainutil);
            insert(chainutil, k);
            return;
        }
        catch(ArrayIndexOutOfBoundsException _ex)
        {
            runs = chainutil1.runs;
        }
    }

    void append(int i)
    {
        if(runs == null || runs.length == 0)
        {
            runs = makeRuns(1);
            runs[0].start = runs[0].end = runs[0].sum = 0;
            runs[0].value = i;
            return;
        }
        if(runs[runs.length - 1].value != i)
        {
            int j = runs.length - 1;
            int k = runs.length;
            JCRun arun[] = makeRuns(runs.length + 1);
            copy(arun, runs, 0, 0, runs.length);
            arun[k].start = arun[k].end = arun[j].end + 1;
            arun[k].value = i;
            arun[k].sum = SumBeforeRun(arun, k);
            runs = arun;
            last_run = k;
            return;
        } else
        {
            runs[last_run].end++;
            return;
        }
    }

    int getRunLen(int i)
    {
        int j = fetchIndex(i);
        if(j < 0 || j >= runs.length)
        {
            resetCache();
            return -1;
        } else
        {
            return runs[j].value;
        }
    }

    int getRunSum(int i)
    {
        int j = fetchIndex(i);
        int k;
        if(j < 0)
        {
            resetCache();
            k = -1;
        } else
        if(j >= 0 && j < runs.length && i <= runs[j].end)
            k = runs[j].sum + runs[j].value * (i - runs[j].start);
        else
            k = 0;
        return k;
    }

    private static final int getNumNewRunsForSet(ChainUtil chainutil, boolean flag, boolean flag1, int i, int j)
    {
        return (chainutil.runs.length - (j - i)) + (flag ? 0 : 1) + (flag1 ? 0 : 1);
    }

    private static final boolean setLeftMerge(ChainUtil chainutil, int i, int j)
    {
        return j >= 0 && i == chainutil.runs[j].value;
    }

    private static final boolean setRightMerge(ChainUtil chainutil, int i, int j)
    {
        return j < chainutil.runs.length && i == chainutil.runs[j].value;
    }

    void setRunLen(int i, int j, int k)
    {
        if(runs == null || runs.length == 0)
            return;
        int k2 = runs.length - 1;
        int l = fetchIndex(i);
        int j1 = RunLeftOfStart(runs, l, i);
        int i1 = fetchIndex(j);
        int k1 = RunRightOfEnd(runs, i1, j);
        resetCache();
        boolean flag = setLeftMerge(this, k, j1);
        boolean flag1 = setRightMerge(this, k, k1);
        int i2 = getNumNewRunsForSet(this, flag, flag1, j1, k1);
        int j2 = CalcDifference(runs, i, j, k, l, i1);
        if(k1 + 1 < runs.length)
            PropagateSum(runs, k1 + 1, runs.length - (k1 + 1), j2);
        if(i2 == runs.length)
        {
            if(j1 >= 0)
            {
                if(!flag)
                {
                    runs[j1].end = i - 1;
                    runs[j1 + 1].start = i;
                    runs[j1 + 1].value = k;
                    runs[j1 + 1].sum = SumBeforeRun(runs, j1 + 1);
                }
            } else
            {
                runs[0].value = k;
            }
            if(k1 < runs.length)
            {
                if(!flag1)
                {
                    runs[k1 - 1].end = j;
                    runs[k1].start = j + 1;
                    runs[k1].sum = SumBeforeRun(runs, k1);
                    return;
                }
            } else
            {
                runs[runs.length - 1].value = k;
            }
            return;
        }
        JCRun arun[] = makeRuns(i2);
        copy(arun, runs, 0, 0, j1 + 1);
        int l1;
        if(flag)
        {
            l1 = j1;
        } else
        {
            if(j1 >= 0)
                arun[j1].end = i - 1;
            arun[j1 + 1].start = i;
            arun[j1 + 1].sum = SumBeforeRun(arun, j1 + 1);
            arun[j1 + 1].value = k;
            l1 = j1 + 1;
        }
        if(flag1)
        {
            arun[l1].end = runs[k1].end;
            copy(arun, runs, l1 + 1, k1 + 1, runs.length - (k1 + 1));
        } else
        {
            arun[l1].end = j;
            if(k1 < runs.length)
            {
                arun[l1 + 1] = runs[k1];
                arun[l1 + 1].start = j + 1;
                arun[l1 + 1].sum = SumBeforeRun(arun, l1 + 1);
                copy(arun, runs, l1 + 2, k1 + 1, runs.length - (k1 + 1));
            }
        }
        runs = arun;
    }

    int[] getArray()
    {
        if(runs == null)
            return null;
        int ai[] = new int[runs[runs.length - 1].end];
        for(int i = 0; i < runs.length; i++)
        {
            for(int j = runs[i].start; j <= runs[i].end; j++)
                ai[j] = runs[i].value;

        }

        return ai;
    }

    public int getValueAtPosition(int i)
    {
        int j;
        for(j = 0; j < runs.length; j++)
            if(runs[j].sum > i)
                break;

        if(--j == -1)
            return -999;
        else
            return (i - runs[j].sum) / runs[j].value + runs[j].start;
    }

    JCRun runs[];
    int last_pos;
    int last_run;
}
