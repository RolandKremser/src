// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPair.java

package jclass.table3;

import java.io.Serializable;

class KeyPair
    implements Serializable
{

    public KeyPair(int i, int j)
    {
        key = i;
        modifier = j;
    }

    public boolean equals(KeyPair keypair)
    {
        return key == keypair.key && modifier == keypair.modifier;
    }

    public boolean equals(int i, int j)
    {
        return key == i && modifier == j;
    }

    public int key;
    public int modifier;
}
