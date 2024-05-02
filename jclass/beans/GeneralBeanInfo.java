// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneralBeanInfo.java

package jclass.beans;

import java.beans.*;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.Vector;
import jclass.util.JCEnvironment;

public class GeneralBeanInfo extends SimpleBeanInfo
{

    public GeneralBeanInfo(String s, String as[][])
    {
        useSuperBeanInfo = true;
        tableModelClassString = new String("javax.swing.table.TableModel");
        name = s;
        props = as;
    }

    public GeneralBeanInfo(String s, String as[][], String as1[][], String as2[][])
    {
        useSuperBeanInfo = true;
        tableModelClassString = new String("javax.swing.table.TableModel");
        name = s;
        props = as;
        events = as1;
        listeners = as2;
    }

    protected PropertyDescriptor createDescriptor(String s, Class class1)
        throws IntrospectionException
    {
        PropertyDescriptor propertydescriptor = null;
        try
        {
            propertydescriptor = new PropertyDescriptor(s, class1);
        }
        catch(Exception _ex)
        {
            propertydescriptor = null;
        }
        if(propertydescriptor == null)
            try
            {
                propertydescriptor = new PropertyDescriptor(s, class1, "get" + s, null);
            }
            catch(Exception _ex)
            {
                propertydescriptor = null;
            }
        return propertydescriptor;
    }

    private static Method findMethod(Class class1, String s, int i)
    {
        if(s == null)
            return null;
        Class class2 = class1;
        while(class1 != null) 
            try
            {
                Method amethod[] = class1.getDeclaredMethods();
                if(amethod != null)
                {
                    for(int j = 0; j < amethod.length; j++)
                    {
                        Method method = amethod[j];
                        if(method != null)
                        {
                            int l = method.getModifiers();
                            if(!Modifier.isStatic(l) && Modifier.isPublic(l) && method.getName().equals(s) && method.getParameterTypes().length == i)
                                return method;
                        }
                    }

                }
                class1 = class1.getSuperclass();
            }
            catch(Exception _ex)
            {
                class1 = null;
            }
        try
        {
            Class aclass[] = class2.getInterfaces();
            if(aclass != null)
            {
                for(int k = 0; k < aclass.length; k++)
                {
                    Method method1 = findMethod(aclass[k], s, i);
                    if(method1 != null)
                        return method1;
                }

            }
        }
        catch(Exception _ex) { }
        return null;
    }

    public BeanInfo[] getAdditionalBeanInfo()
    {
        if(events == null || JCEnvironment.isJBuilder())
        {
            return null;
        } else
        {
            BeanInfo abeaninfo[] = new BeanInfo[1];
            abeaninfo[0] = new GeneralBeanInfo(name, null, events, listeners);
            return abeaninfo;
        }
    }

    public EventSetDescriptor[] getEventSetDescriptors()
    {
        if(events == null || listeners == null)
            return null;
        String s = name.substring(0, name.lastIndexOf('.') + 1);
        Vector vector = new Vector();
        for(int i = 0; i < events.length; i++)
            try
            {
                Class class1 = Class.forName(name);
                Class class2 = Class.forName(s + events[i][1]);
                if(i < listeners.length && listeners[i] != null && listeners[i].length != 0)
                {
                    Method amethod[] = new Method[listeners[i].length];
                    int k = 0;
                    for(int l = 0; l < listeners[i].length; l++)
                        if(listeners[i] != null)
                        {
                            Method method = findMethod(class2, listeners[i][l], 1);
                            if(method != null)
                                amethod[k++] = method;
                        }

                    Method method1 = findMethod(class1, events[i][2], 1);
                    Method method2 = findMethod(class1, events[i][3], 1);
                    if(k != amethod.length)
                    {
                        Method amethod1[] = new Method[k];
                        for(int i1 = 0; i1 < amethod1.length; i1++)
                            amethod1[i1] = amethod[i1];

                        amethod = amethod1;
                    }
                    EventSetDescriptor eventsetdescriptor = new EventSetDescriptor(events[i][0], class2, amethod, method1, method2);
                    vector.addElement(eventsetdescriptor);
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace(System.out);
            }

        if(vector == null || vector.size() <= 0)
            return null;
        EventSetDescriptor aeventsetdescriptor[] = new EventSetDescriptor[vector.size()];
        for(int j = 0; j < vector.size(); j++)
            aeventsetdescriptor[j] = (EventSetDescriptor)vector.elementAt(j);

        return aeventsetdescriptor;
    }

    public PropertyDescriptor[] getPropertyDescriptors()
    {
        if(props == null)
            return null;
        BeanInfo beaninfo = null;
        int i = 0;
        Class class1;
        try
        {
            class1 = Class.forName(name);
            if(useSuperBeanInfo)
                beaninfo = Introspector.getBeanInfo(class1.getSuperclass());
        }
        catch(Exception exception)
        {
            throw new Error(exception.toString());
        }
        if(useSuperBeanInfo && beaninfo != null)
        {
            PropertyDescriptor apropertydescriptor[] = beaninfo.getPropertyDescriptors();
            desc_list = new PropertyDescriptor[apropertydescriptor.length + props.length];
            System.arraycopy(apropertydescriptor, 0, desc_list, 0, apropertydescriptor.length);
            i = apropertydescriptor.length;
        } else
        {
            desc_list = new PropertyDescriptor[props.length];
        }
        try
        {
            for(int j = 0; j < props.length; j++)
            {
                String s = props[j][0];
                String s1 = props[j][1];
                PropertyDescriptor propertydescriptor = createDescriptor(s, class1);
                if(propertydescriptor != null)
                {
                    if(s1 != null && s1.length() > 0)
                        propertydescriptor.setPropertyEditorClass(Class.forName(s1));
                    try
                    {
                        if(props[j].length == 3)
                            propertydescriptor.setShortDescription(props[j][2]);
                    }
                    catch(Exception _ex) { }
                    modifyPropertyDescriptor(propertydescriptor);
                }
                desc_list[i + j] = propertydescriptor;
            }

        }
        catch(Exception exception1)
        {
            exception1.printStackTrace(System.out);
            throw new Error(exception1.toString());
        }
        Vector vector = new Vector();
        for(int l = 0; l < desc_list.length; l++)
            if(desc_list[l] != null)
            {
                String s2 = desc_list[l].getName();
                if(omit_props != null)
                {
                    int k;
                    for(k = 0; k < omit_props.length; k++)
                        if(omit_props[k].equals(s2))
                            break;

                    if(k == omit_props.length)
                        vector.addElement(desc_list[l]);
                } else
                {
                    vector.addElement(desc_list[l]);
                }
            }

        desc_list = new PropertyDescriptor[vector.size()];
        for(int i1 = 0; i1 < vector.size(); i1++)
            desc_list[i1] = (PropertyDescriptor)vector.elementAt(i1);

        return desc_list;
    }

    public void modifyPropertyDescriptor(PropertyDescriptor propertydescriptor)
    {
        if(propertydescriptor == null)
            return;
        if(JCEnvironment.isVisualCafe() && propertydescriptor.getPropertyType().getName().equals(tableModelClassString))
            try
            {
                Class class1 = Class.forName("com.symantec.itools.vcafe.beans.ObjectReferenceAttributes");
                String s = (String)class1.getDeclaredField("OBJECTREFERENCE_SHOW_POSSIBLE_COMPONENT_REFERENCES_ATTRIBUTE").get(class1.newInstance());
                propertydescriptor.setValue(s, Boolean.TRUE);
            }
            catch(Exception _ex) { }
    }

    String name;
    String props[][];
    String events[][];
    String listeners[][];
    protected PropertyDescriptor desc_list[];
    protected String omit_props[];
    private static final boolean TRACE = false;
    protected boolean useSuperBeanInfo;
    private static String vendor = null;
    protected String tableModelClassString;

}
