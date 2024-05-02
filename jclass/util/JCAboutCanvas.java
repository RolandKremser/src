// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCAboutCanvas.java

package jclass.util;

import java.awt.*;

// Referenced classes of package jclass.util:
//            JCString, JClassInfo

public class JCAboutCanvas extends Canvas
{

    public JCAboutCanvas(String s)
    {
        logo = null;
        labelledLogo = null;
        adTitle = null;
        ad = null;
        version = null;
        com_msg = null;
        config_msg = null;
        doc_msg = null;
        eval_msg = null;
        useEval = false;
        prefSize = null;
        labelledLogo = new JCString("", getLogo(), 3);
        adTitle = JClassInfo.getAdTitle(this);
        ad = JClassInfo.getAd(this);
        version = new JCString(s);
    }

    public JCAboutCanvas(String s, String s1, String s2, JCString jcstring, JCString jcstring1)
    {
        logo = null;
        labelledLogo = null;
        adTitle = null;
        ad = null;
        version = null;
        com_msg = null;
        config_msg = null;
        doc_msg = null;
        eval_msg = null;
        useEval = false;
        prefSize = null;
        useEval = true;
        labelledLogo = new JCString("", getLogo(), 3);
        config_msg = jcstring;
        eval_msg = jcstring1;
        String s3 = System.getProperty("file.separator");
        if(s3.charAt(0) == '\\')
            s3 = s3 + s3;
        doc_msg = JCString.parse(this, "");
        com_msg = JCString.parse(this, "[FONT=arial-plain-12]For information about JClass[DEFAULT_COLOR] products for purchase please visit: [NEWLINE][HORIZ_SPACE=60][COLOR=blue][HREF=http://www.sitraka.com/software/jclass/]http://www.sitraka.com/software/jclass/[/HREF][DEFAULT_COLOR][NEWLINE][/FONT]");
    }

    public JCAboutCanvas(String s, String s1, JCString jcstring, JCString jcstring1)
    {
        this(s, s, s1, jcstring, jcstring1);
    }

    protected Image getLogo()
    {
        if(logo == null)
            logo = JClassInfo.makeKLImage(this);
        return logo;
    }

    public Dimension getPreferredSize()
    {
        if(prefSize == null)
            if(!useEval)
            {
                Dimension dimension = ad.getSize(this, null);
                prefSize = new Dimension(dimension.width + 2, labelledLogo.getSize(this, null).height + adTitle.getSize(this, null).height + dimension.height + version.getSize(this, null).height + 4 + 2);
            } else
            {
                Dimension dimension1 = doc_msg.getSize(this, null);
                int i = 0;
                int j = dimension1.width;
                if(eval_msg != null)
                {
                    i += eval_msg.getSize(this, null).height;
                    j = eval_msg.getSize(this, null).width;
                }
                if(config_msg != null)
                    i += config_msg.getSize(this, null).height;
                if(com_msg != null)
                    j = Math.max(j, com_msg.getSize(this, null).width);
                j += j / 4;
                prefSize = new Dimension(j + 5, labelledLogo.getSize(this, null).height + com_msg.getSize(this, null).height + i + dimension1.height + 30);
            }
        return prefSize;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Dimension dimension = getSize();
        g.setColor(getForeground());
        Dimension dimension1 = labelledLogo.getSize(this, null);
        Rectangle rectangle = new Rectangle(dimension.width, dimension1.height);
        rectangle.y += 10;
        labelledLogo.draw(this, g, rectangle, 1);
        if(!useEval)
        {
            g.drawRect(0, 0, dimension.width - 1, dimension.height - 1);
            Dimension dimension2 = adTitle.getSize(this, null);
            Dimension dimension4 = ad.getSize(this, null);
            Dimension dimension6 = version.getSize(this, null);
            if(dimension.height >= dimension1.height + dimension2.height + dimension4.height + dimension6.height)
            {
                rectangle.y += dimension1.height;
                rectangle.height = dimension2.height;
                adTitle.draw(this, g, rectangle, 1);
                rectangle.y += dimension2.height;
                rectangle.height = dimension4.height;
                rectangle.x = (dimension.width - dimension4.width) / 2;
                rectangle.width = dimension4.width;
                ad.draw(this, g, rectangle, 0);
                rectangle.x = 0;
                rectangle.y += dimension4.height;
                rectangle.width = dimension.width;
                rectangle.height = dimension6.height;
                version.draw(this, g, rectangle, 1);
            }
        } else
        {
            Dimension dimension3 = com_msg.getSize(this, null);
            Dimension dimension5 = doc_msg.getSize(this, null);
            Dimension dimension7 = new Dimension();
            Dimension dimension8 = new Dimension();
            int i = 0;
            if(eval_msg != null)
            {
                dimension8 = eval_msg.getSize(this, null);
                i += dimension8.height;
            }
            if(config_msg != null)
            {
                dimension7 = config_msg.getSize(this, null);
                i += dimension7.height;
            }
            i += dimension1.height + dimension3.height + dimension5.height;
            if(dimension.height >= i)
            {
                if(eval_msg != null)
                    rectangle.x = (dimension.width - dimension8.width) / 2;
                else
                    rectangle.x = (dimension.width - dimension5.width) / 2;
                rectangle.y += dimension1.height;
                if(config_msg != null)
                {
                    rectangle.height = dimension7.height;
                    config_msg.draw(this, g, rectangle, 0);
                    rectangle.y += dimension7.height;
                }
                if(eval_msg != null)
                {
                    rectangle.height = dimension8.height;
                    rectangle.width = dimension8.width;
                    eval_msg.draw(this, g, rectangle, 0);
                    rectangle.y += dimension8.height;
                }
                rectangle.height = dimension3.height;
                rectangle.width = dimension3.width;
                rectangle.x = (dimension.width - com_msg.getSize(this, null).width) / 2;
                com_msg.draw(this, g, rectangle, 0);
                rectangle.y += dimension3.height;
                rectangle.height = dimension5.height;
                rectangle.width = dimension5.width;
                doc_msg.draw(this, g, rectangle, 0);
            }
        }
    }

    public void setConfigMsg(JCString jcstring)
    {
        config_msg = jcstring;
        getPreferredSize();
    }

    protected transient Image logo;
    protected JCString labelledLogo;
    protected JCString adTitle;
    protected JCString ad;
    protected JCString version;
    private JCString com_msg;
    private JCString config_msg;
    private JCString doc_msg;
    private JCString eval_msg;
    private boolean useEval;
    Dimension prefSize;
}
