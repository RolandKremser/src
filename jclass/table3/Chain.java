// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Chain.java

package jclass.table3;

import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            ChainUtil, JCTblEnum

class Chain
    implements Serializable
{

    static void changeMaxSize(int i)
    {
        MAXSIZE = i;
    }

    Chain(int i)
    {
        if(i < MAXSIZE)
        {
            values = new int[i];
            positions = new int[i];
            values_len = values.length;
            return;
        } else
        {
            chain = new ChainUtil(i);
            return;
        }
    }

    Chain()
    {
        this(0);
    }

    private int[] setArraySize(int ai[], int i)
    {
        int j = ai.length;
        if(i > j)
        {
            int ai1[] = ai;
            ai = new int[Math.max(j + 20, i)];
            if(j > 0)
                System.arraycopy(ai1, 0, ai, 0, j);
        }
        return ai;
    }

    private void setSize(int i)
    {
        values = setArraySize(values, i);
        positions = setArraySize(positions, i);
    }

    int getValue(int i)
    {
        if(values == null)
            return chain.getRunLen(i);
        if(i >= values.length)
            return -1;
        else
            return values[i];
    }

    int getPosition(int i)
    {
        if(values == null)
            return chain.getRunSum(i);
        if(i >= positions.length)
            return 0;
        else
            return positions[i];
    }

    void calcPositions(int i)
    {
        if(i < 0)
            i = 0;
        if(values_len <= 0 || values.length <= 0 || positions.length <= 0)
            return;
        int j = i;
        int k = positions[i];
        for(; j < values_len; j++)
        {
            positions[j] = k;
            k += values[j];
        }

    }

    void setValue(int i, int j, int k)
    {
        if(values != null && j > MAXSIZE)
        {
            chain = new ChainUtil(values);
            values = positions = null;
        }
        if(values == null)
        {
            chain.setRunLen(i, Math.max(i, j), k);
            return;
        }
        if(i < 0 || i > values_len)
            return;
        if(j < 0 || j > values_len)
            return;
        int ai[] = positions;
        setSize(j + 1);
        for(int l = i; l <= j; l++)
            values[l] = k;

        values_len = Math.max(j + 1, values_len);
        System.arraycopy(ai, 0, positions, 0, i);
        calcPositions(i - 1);
    }

    void delete(int i, int j)
    {
        if(values == null)
        {
            chain.delete(i, j);
            return;
        }
        if(i < 0 || i > values_len || j < 0 || j > values_len || j < i)
        {
            return;
        } else
        {
            int k = values.length;
            int ai[] = values;
            values = new int[((k - j) + i) - 1];
            System.arraycopy(ai, 0, values, 0, i);
            System.arraycopy(ai, j + 1, values, i, k - j - 1);
            j = Math.min(j, values_len - 1);
            values_len -= (j - i) + 1;
            ai = positions;
            positions = new int[((k - j) + i) - 1];
            System.arraycopy(ai, 0, positions, 0, i);
            calcPositions(i - 1);
            return;
        }
    }

    void move(int i, int j, int k)
    {
        if(values == null)
        {
            chain.move(i, j, k);
            return;
        }
        if(i + j > values.length || k > values.length || j <= 0)
            return;
        int ai[] = values;
        values = new int[ai.length];
        try
        {
            if(k > i)
            {
                System.arraycopy(ai, 0, values, 0, i);
                System.arraycopy(ai, i + j, values, i, k - (i + j));
                System.arraycopy(ai, i, values, k - j, j);
                System.arraycopy(ai, k, values, k, ai.length - k);
            } else
            {
                System.arraycopy(ai, 0, values, 0, k);
                System.arraycopy(ai, i, values, k, j);
                System.arraycopy(ai, k, values, k + j, i - k);
                System.arraycopy(ai, i + j, values, i + j, ai.length - (i + j));
            }
        }
        catch(Throwable _ex)
        {
            values = ai;
        }
        calcPositions(Math.min(i, k));
    }

    void insert(Chain chain1, int i)
    {
        if(chain1 == null)
            return;
        if(values != null && i > MAXSIZE)
        {
            chain = new ChainUtil(values);
            values = positions = null;
        }
        if(values == null)
        {
            if(chain1 == null)
                return;
            if(chain1.values == null)
            {
                chain.insert(chain1.chain, i);
                return;
            } else
            {
                chain.insert(new ChainUtil(chain1.values), i);
                return;
            }
        }
        if(i < 0 || i > values_len)
            return;
        int ai[] = chain1.values == null ? chain1.chain.getArray() : chain1.values;
        int j = chain1.values == null ? ai.length : chain1.values_len;
        int k = values_len;
        values_len += j;
        setSize(values_len);
        if(i < k)
            System.arraycopy(values, i, values, i + j, k - i);
        System.arraycopy(chain1.values, 0, values, i, j);
        calcPositions(i - 1);
    }

    void append(int i)
    {
        appendNocalc(i);
        if(values != null)
            calcPositions(values_len - 2);
    }

    void appendNocalc(int i)
    {
        if(values == null)
        {
            chain.append(i);
            return;
        } else
        {
            setSize(++values_len);
            values[values_len - 1] = i;
            return;
        }
    }

    public int getValueAtPosition(int i, int j)
    {
        if(values == null)
            return chain.getValueAtPosition(i);
        if(values.length == 0)
            return -999;
        if(i < getPosition(j))
        {
            for(int k = j - 1; k > 0; k--)
                if(getPosition(k) < i)
                    return k;

            return 0x80000000;
        }
        if(i > getPosition(j))
        {
            for(int l = j + 1; l < values_len; l++)
                if(getPosition(l) > i)
                    return l - 1;

            if(getPosition(values_len - 1) + getValue(values_len - 1) >= i)
                return values_len - 1;
            else
                return 0x7fffffff;
        } else
        {
            return j;
        }
    }

    private int values[];
    private int positions[];
    private int values_len;
    private ChainUtil chain;
    static int MAXSIZE = 1000;
    private static final int INCR = 20;

}
