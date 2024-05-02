// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CellBorder.java

package jclass.table3;

import java.awt.Color;
import java.awt.Graphics;

public interface CellBorder
{

    public abstract void drawBackground(Graphics g, int i, int j, int k, int l, int i1, int j1, 
            Color color, Color color1, Color color2);
}
