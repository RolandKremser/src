// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCRectUtil.java

package jclass.util;

import java.awt.Rectangle;
import java.util.Vector;

// Referenced classes of package jclass.util:
//            JCPointRect, JCRectList, JCRectNode, JCRectSortLR, 
//            JCRectSortTB, JCqsort

public class JCRectUtil
{

    public JCRectUtil()
    {
    }

    static void appendrect(Rectangle rectangle, JCRectList jcrectlist)
    {
        JCRectNode jcrectnode = new JCRectNode();
        jcrectnode.rect = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        appendrectnode(jcrectnode, jcrectlist);
    }

    static void appendrectnode(JCRectNode jcrectnode, JCRectList jcrectlist)
    {
        jcrectnode.next = null;
        if(jcrectlist.head == null)
            jcrectlist.head = jcrectnode;
        if(jcrectlist.tail != null)
            jcrectlist.tail.next = jcrectnode;
        jcrectlist.tail = jcrectnode;
    }

    static void appendremaining(Rectangle rectangle, Rectangle rectangle1, JCRectList jcrectlist)
    {
        Rectangle rectangle3 = new Rectangle();
        if(includesrect(rectangle, rectangle1))
            return;
        if(!rectangle.intersects(rectangle1))
        {
            appendrect(rectangle1, jcrectlist);
            return;
        }
        Rectangle rectangle2 = new Rectangle(rectangle1.x, rectangle1.y, rectangle1.width, rectangle1.height);
        int l = rectangle.y - rectangle2.y;
        if(l > 0)
        {
            rectangle3.x = rectangle2.x;
            rectangle3.y = rectangle2.y;
            rectangle3.width = rectangle2.width;
            rectangle3.height = l;
            appendrect(rectangle3, jcrectlist);
            rectangle2.y += l;
            rectangle2.height -= l;
        }
        int j = rectangle.y + rectangle.height;
        l = (rectangle2.y + rectangle2.height) - j;
        if(l > 0)
        {
            rectangle3.x = rectangle2.x;
            rectangle3.y = j;
            rectangle3.width = rectangle2.width;
            rectangle3.height = l;
            appendrect(rectangle3, jcrectlist);
            rectangle2.height -= l;
        }
        int k = rectangle.x - rectangle2.x;
        if(k > 0)
        {
            rectangle3.x = rectangle2.x;
            rectangle3.y = rectangle2.y;
            rectangle3.width = k;
            rectangle3.height = rectangle2.height;
            appendrect(rectangle3, jcrectlist);
            rectangle2.x += k;
            rectangle2.width -= k;
        }
        int i = rectangle.x + rectangle.width;
        k = (rectangle2.x + rectangle2.width) - i;
        if(k > 0)
        {
            rectangle3.x = i;
            rectangle3.y = rectangle2.y;
            rectangle3.width = k;
            rectangle3.height = rectangle2.height;
            appendrect(rectangle3, jcrectlist);
            rectangle2.width -= k;
        }
    }

    static Rectangle bounding(Rectangle rectangle, Rectangle rectangle1)
    {
        Rectangle rectangle2 = new Rectangle();
        if(isnull(rectangle))
            rectangle2 = rectangle1;
        else
        if(isnull(rectangle1))
        {
            rectangle2 = rectangle;
        } else
        {
            rectangle2.x = Math.min(rectangle.x, rectangle1.x);
            rectangle2.y = Math.min(rectangle.y, rectangle1.y);
            rectangle2.width = Math.max(rectangle.x + rectangle.width, rectangle1.x + rectangle1.width) - rectangle2.x;
            rectangle2.height = Math.max(rectangle.y + rectangle.height, rectangle1.y + rectangle1.height) - rectangle2.y;
        }
        return rectangle2;
    }

    static void calc_lg_rect(Rectangle rectangle, Rectangle rectangle1, Rectangle rectangle2)
    {
        JCPointRect jcpointrect = new JCPointRect();
        JCPointRect jcpointrect1 = new JCPointRect();
        JCPointRect jcpointrect2 = new JCPointRect();
        JCPointRect jcpointrect3 = new JCPointRect();
        jcpointrect.x1 = rectangle1.x;
        jcpointrect.x2 = (rectangle1.width + rectangle1.x) - 1;
        jcpointrect.y1 = rectangle1.y;
        jcpointrect.y2 = (rectangle1.height + rectangle1.y) - 1;
        jcpointrect1.x1 = rectangle2.x;
        jcpointrect1.x2 = (rectangle2.width + rectangle2.x) - 1;
        jcpointrect1.y1 = rectangle2.y;
        jcpointrect1.y2 = (rectangle2.height + rectangle2.y) - 1;
        jcpointrect2.x1 = rectangle.x;
        jcpointrect2.x2 = (rectangle.width + rectangle.x) - 1;
        jcpointrect2.y1 = rectangle.y;
        jcpointrect2.y2 = (rectangle.height + rectangle.y) - 1;
        double d1 = lg_metric(jcpointrect2);
        if(jcpointrect.x1 == jcpointrect1.x1 && jcpointrect.x2 == jcpointrect1.x2 && jcpointrect.y1 == jcpointrect1.y1 && jcpointrect.y2 == jcpointrect1.y2)
        {
            double d = lg_metric(jcpointrect);
            if(d > d1)
            {
                rectangle.x = rectangle1.x;
                rectangle.y = rectangle1.y;
                rectangle.width = rectangle1.width;
                rectangle.height = rectangle1.height;
            }
        } else
        if(Math.abs(jcpointrect.y2 - jcpointrect1.y1) == 1)
        {
            jcpointrect3.x1 = Math.max(jcpointrect.x1, jcpointrect1.x1);
            jcpointrect3.x2 = Math.min(jcpointrect.x2, jcpointrect1.x2);
            jcpointrect3.y1 = jcpointrect.y1;
            jcpointrect3.y2 = jcpointrect1.y2;
            if(jcpointrect3.x1 <= jcpointrect3.x2)
            {
                rectangle1.x = jcpointrect3.x1;
                rectangle1.y = jcpointrect3.y1;
                rectangle1.width = (jcpointrect3.x2 - jcpointrect3.x1) + 1;
                rectangle1.height = (jcpointrect3.y2 - jcpointrect3.y1) + 1;
                double d2 = lg_metric(jcpointrect3);
                if(d2 > d1)
                {
                    rectangle.x = rectangle1.x;
                    rectangle.y = rectangle1.y;
                    rectangle.width = rectangle1.width;
                    rectangle.height = rectangle1.height;
                }
            }
        } else
        if(Math.abs(jcpointrect.x2 - jcpointrect1.x1) == 1)
        {
            jcpointrect3.x1 = jcpointrect.x1;
            jcpointrect3.x2 = jcpointrect1.x2;
            jcpointrect3.y1 = Math.max(jcpointrect.y1, jcpointrect1.y1);
            jcpointrect3.y2 = Math.min(jcpointrect.y2, jcpointrect1.y2);
            if(jcpointrect3.y1 <= jcpointrect3.y2)
            {
                rectangle1.x = jcpointrect3.x1;
                rectangle1.y = jcpointrect3.y1;
                rectangle1.width = (jcpointrect3.x2 - jcpointrect3.x1) + 1;
                rectangle1.height = (jcpointrect3.y2 - jcpointrect3.y1) + 1;
                double d3 = lg_metric(jcpointrect3);
                if(d3 > d1)
                {
                    rectangle.x = rectangle1.x;
                    rectangle.y = rectangle1.y;
                    rectangle.width = rectangle1.width;
                    rectangle.height = rectangle1.height;
                }
            }
        }
    }

    static boolean includesrect(Rectangle rectangle, Rectangle rectangle1)
    {
        return rectangle.x <= rectangle1.x && rectangle.y <= rectangle1.y && rectangle.x + rectangle.width >= rectangle1.x + rectangle1.width && rectangle.y + rectangle.height >= rectangle1.y + rectangle1.height;
    }

    static void intersect(Rectangle rectangle, Rectangle rectangle1, Rectangle rectangle2)
    {
        if(rectangle == null || rectangle1 == null || rectangle2 == null)
            return;
        rectangle2.x = rectangle2.y = rectangle2.width = rectangle2.height = 0;
        if(isnull(rectangle) || isnull(rectangle1))
        {
            return;
        } else
        {
            rectangle2.x = Math.max(rectangle.x, rectangle1.x);
            rectangle2.y = Math.max(rectangle.y, rectangle1.y);
            int i = Math.min(rectangle.x + rectangle.width, rectangle1.x + rectangle1.width);
            int j = Math.min(rectangle.y + rectangle.height, rectangle1.y + rectangle1.height);
            rectangle2.width = Math.max(i - rectangle2.x, 0);
            rectangle2.height = Math.max(j - rectangle2.y, 0);
            return;
        }
    }

    static boolean isnull(Rectangle rectangle)
    {
        return rectangle.width == 0 || rectangle.height == 0;
    }

    public static Rectangle largest_rect(JCRectList jcrectlist)
    {
        JCRectList ajcrectlist[] = null;
        normalize(jcrectlist);
        sort(jcrectlist, jcrectlist, 1);
        ajcrectlist = make(jcrectlist);
        Rectangle rectangle2 = ajcrectlist[0].head.rect;
        Rectangle rectangle = new Rectangle(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
        int i = 0;
        for(JCRectList jcrectlist1 = ajcrectlist[i]; jcrectlist1.head != null; jcrectlist1 = ajcrectlist[i])
        {
            for(JCRectNode jcrectnode = jcrectlist1.head; jcrectnode != null; jcrectnode = jcrectnode.next)
            {
                Rectangle rectangle1 = new Rectangle(jcrectnode.rect.x, jcrectnode.rect.y, jcrectnode.rect.width, jcrectnode.rect.height);
                search_lg_rect(ajcrectlist, i, jcrectnode, rectangle, rectangle1);
            }

            i++;
        }

        return rectangle;
    }

    static double lg_metric(JCPointRect jcpointrect)
    {
        return (double)(jcpointrect.x2 - jcpointrect.x1) * (double)(jcpointrect.y2 - jcpointrect.y1);
    }

    static JCRectList[] make(JCRectList jcrectlist)
    {
        if(jcrectlist.head == null)
        {
            JCRectList ajcrectlist[] = null;
            return ajcrectlist;
        }
        int i = jcrectlist.head.rect.y;
        int j = 1;
        for(JCRectNode jcrectnode = jcrectlist.head.next; jcrectnode != null; jcrectnode = jcrectnode.next)
            if(jcrectnode.rect.y != i)
            {
                i = jcrectnode.rect.y;
                j++;
            }

        int l = j + 1;
        JCRectList ajcrectlist1[] = new JCRectList[l];
        if(ajcrectlist1 == null)
            return ajcrectlist1;
        for(int k = 0; k < l; k++)
            ajcrectlist1[k] = new JCRectList();

        int i1 = 0;
        JCRectList jcrectlist1 = ajcrectlist1[i1];
        JCRectNode jcrectnode1 = jcrectlist.head;
        rl_init(jcrectnode1.rect, jcrectlist1);
        i = jcrectnode1.rect.y;
        for(jcrectnode1 = jcrectnode1.next; jcrectnode1 != null; jcrectnode1 = jcrectnode1.next)
            if(jcrectnode1.rect.y == i)
            {
                appendrect(jcrectnode1.rect, jcrectlist1);
            } else
            {
                sort(jcrectlist1, jcrectlist1, 0);
                i1++;
                jcrectlist1 = ajcrectlist1[i1];
                i = jcrectnode1.rect.y;
                rl_init(jcrectnode1.rect, jcrectlist1);
            }

        sort(jcrectlist1, jcrectlist1, 0);
        return ajcrectlist1;
    }

    static void normalize(JCRectList jcrectlist)
    {
        for(JCRectNode jcrectnode = jcrectlist.head; jcrectnode != null; jcrectnode = jcrectnode.next)
        {
            if(jcrectnode.rect.width < 0)
            {
                jcrectnode.rect.x += jcrectnode.rect.width;
                jcrectnode.rect.width = -jcrectnode.rect.width;
            }
            if(jcrectnode.rect.height < 0)
            {
                jcrectnode.rect.y += jcrectnode.rect.height;
                jcrectnode.rect.height = -jcrectnode.rect.height;
            }
        }

    }

    public static void rl_init(Rectangle rectangle, JCRectList jcrectlist)
    {
        JCRectNode jcrectnode = new JCRectNode();
        jcrectnode.rect = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        jcrectlist.head = jcrectlist.tail = jcrectnode;
    }

    public static void rl_remove(Rectangle rectangle, JCRectList jcrectlist, JCRectList jcrectlist1)
    {
        JCRectList jcrectlist2 = new JCRectList();
        for(JCRectNode jcrectnode = jcrectlist.head; jcrectnode != null; jcrectnode = jcrectnode.next)
            appendremaining(rectangle, jcrectnode.rect, jcrectlist2);

        jcrectlist1.head = jcrectlist2.head;
        jcrectlist1.tail = jcrectlist2.tail;
    }

    static void search_lg_rect(JCRectList ajcrectlist[], int i, JCRectNode jcrectnode, Rectangle rectangle, Rectangle rectangle1)
    {
        JCRectList jcrectlist = ajcrectlist[i];
        if(jcrectlist == null)
            return;
        for(JCRectNode jcrectnode1 = jcrectnode; jcrectnode1 != null; jcrectnode1 = jcrectnode1.next)
            calc_lg_rect(rectangle, rectangle1, jcrectnode1.rect);

        int j = i + 1;
        for(JCRectList jcrectlist1 = ajcrectlist[j]; jcrectlist1.head != null; jcrectlist1 = ajcrectlist[j])
        {
            for(JCRectNode jcrectnode2 = jcrectlist1.head; jcrectnode2 != null; jcrectnode2 = jcrectnode2.next)
            {
                Rectangle rectangle2 = new Rectangle(rectangle1.x, rectangle1.y, rectangle1.width, rectangle1.height);
                calc_lg_rect(rectangle, rectangle2, jcrectnode2.rect);
                search_lg_rect(ajcrectlist, j, jcrectnode2, rectangle, rectangle2);
            }

            j++;
        }

    }

    static void sort(JCRectList jcrectlist, JCRectList jcrectlist1, int i)
    {
        JCRectNode jcrectnode = jcrectlist.head;
        int l;
        for(l = 0; jcrectnode != null; l++)
            jcrectnode = jcrectnode.next;

        Vector vector = new Vector();
        jcrectnode = jcrectlist.head;
        for(int j = 0; jcrectnode != null; j++)
        {
            vector.addElement(jcrectnode);
            jcrectnode = jcrectnode.next;
        }

        Object obj;
        if(i == 0)
            obj = new JCRectSortLR();
        else
            obj = new JCRectSortTB();
        (new JCqsort(vector, ((JCSortInterface) (obj)))).sort(0);
        jcrectlist1.head = null;
        jcrectlist1.tail = null;
        for(int k = 0; k < l; k++)
            appendrectnode((JCRectNode)vector.elementAt(k), jcrectlist1);

    }

    public static final int LEFTTORIGHT = 0;
    public static final int TOPTOBOTTOM = 1;
}
