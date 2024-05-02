// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LiteComponent.java

package jclass.beans;

import java.awt.*;

public class LiteComponent extends Panel
{

    public LiteComponent()
    {
        this(new String("Some features are disabled"));
    }

    public LiteComponent(String s)
    {
        setLayout(new FlowLayout());
        setBackground(Color.yellow);
        add(new Label("Lite Version - "));
        add(new Label(s));
    }

    public LiteComponent(String s, String s1, boolean flag)
    {
        if(flag)
            setLayout(new GridLayout(2, 1));
        else
            setLayout(new FlowLayout());
        setBackground(Color.yellow);
        add(new Label(s, 1));
        add(new Label(s1, 1));
    }
}
