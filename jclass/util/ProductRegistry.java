// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProductRegistry.java

package jclass.util;

import java.util.Vector;

// Referenced classes of package jclass.util:
//            RegistryListener

public class ProductRegistry
{

    public ProductRegistry()
    {
    }

    public static synchronized void registerListener(RegistryListener registrylistener)
    {
        if(registrylistener == null)
        {
            return;
        } else
        {
            thisListener = registrylistener;
            return;
        }
    }

    public static synchronized Vector registerProduct(String s)
    {
        for(int i = 0; i < products.size(); i++)
            if(s.equals((String)products.elementAt(i)))
                return products;

        products.addElement(s);
        productString = "";
        for(int j = 0; j < products.size(); j++)
        {
            if(j == products.size() - 1)
                productString = productString + " and ";
            else
            if(j > 0)
                productString = productString + ", ";
            productString = productString + (String)products.elementAt(j);
        }

        if(thisListener != null)
            thisListener.expiryNotify(productString);
        return products;
    }

    private static Vector products = new Vector();
    private static String productString = null;
    private static RegistryListener thisListener = null;

}
