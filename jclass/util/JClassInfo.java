// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JClassInfo.java

package jclass.util;

import java.awt.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Properties;

// Referenced classes of package jclass.util:
//            JCEnvironment, JCImageCreator, JCString

public class JClassInfo
{

    public JClassInfo()
    {
    }

    private static void createPropertyFile()
        throws IOException
    {
        File file = new File(getPropertyFilePath());
        file.mkdirs();
        FileWriter filewriter = new FileWriter(getPropertyFile());
        for(int i = 0; i < defaultProperties.length; i++)
        {
            filewriter.write(defaultProperties[i][0] + "=" + defaultProperties[i][1]);
            filewriter.write(System.getProperty("line.separator"));
        }

        filewriter.close();
    }

    public static JCString getAd(Component component)
    {
        return JCString.parse(component, "[FONT=timesroman-plain-12] [VERT_SPACE=1][HORIZ_SPACE=2]Java developers worldwide are using JClass to create professional GUI [NEWLINE] applications. All JClass components are 100% Java, support[NEWLINE] easy-to-use developer interfaces, are available for JDK 1.0.2 and JDK 1.1[NEWLINE] with the same API, and are supported in all popular IDEs. The JClass[NEWLINE] family includes: [NEWLINE][NEWLINE] [HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] BWT[/UL], a Java GUI toolkit that enhances and extends[NEWLINE][HORIZ_SPACE=9]AWT with over 20 components plus rich text.[NEWLINE][NEWLINE] [HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] LiveTable[/UL] , the essential Java grid/table to easily display[NEWLINE] [HORIZ_SPACE=9]and manipulate tables and forms.[NEWLINE][NEWLINE] [HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] Chart[/UL], a powerful component to embed sophisticated[NEWLINE] [HORIZ_SPACE=9]graphs and charts.[NEWLINE] [HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] Field[/UL], masked edit fields and data validation for data entry. [NEWLINE][HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] HiGrid[/UL], an innovative outline-grid for developing multi-level database GUIs. [NEWLINE][HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] DataSource[/UL], hierarchical data source components for platform-independent data binding. [NEWLINE] [NEWLINE] [VERT_SPACE=3][HORIZ_SPACE=2]All our components are 100% Pure JavaBeans, and are built to run in a variety of[NEWLINE] environments.  Demos of all products are available [HREF=http://www.klg.com/jclass]online[/HREF].[NEWLINE] [NEWLINE] [VERT_SPACE=3][HORIZ_SPACE=2]Contact us at [COLOR=blue]www.klg.com [DEFAULT_COLOR] for more details! ");
    }

    public static JCString getAdTitle(Component component)
    {
        return JCString.parse(component, "[FONT=timesroman-plain-20][COLOR=red]J[COLOR=blue]Class[DEFAULT_COLOR] Java Beans!");
    }

    public static String getEnvironmentInfo(Component component)
    {
        int i = JCEnvironment.getBrowser(component);
        int j = JCEnvironment.getJavaVersion();
        String s = new String();
        switch(i)
        {
        case 1: // '\001'
            s = s + "No Browser";
            break;

        case 2: // '\002'
            s = s + "JDK Appletviewer";
            break;

        case 3: // '\003'
            s = s + "Netscape Navigator";
            break;

        case 4: // '\004'
            s = s + "Microsoft Internet Explorer";
            break;

        default:
            s = s + "Unknown browser";
            break;
        }
        s = s + " and ";
        switch(j)
        {
        case 102: // 'f'
            s = s + "JDK 1.0.2";
            break;

        case 110: // 'n'
            s = s + "JDK 1.1";
            break;

        default:
            s = s + "Unknown Java Flavor";
            break;
        }
        return s;
    }

    public static String getEvalMessage(String s, String s1, String s2)
    {
        String s3 = System.getProperty("file.separator");
        if(s3.charAt(0) == '\\')
            s3 = s3 + s3;
        return "[VERT_SPACE=10][FONT=arial-plain-12]To purchase permanent JClass product licenses, including technical support services[NEWLINE]and/or source code, or to inquire about any of the other tools KL Group provides [NEWLINE]Java developers, go to [COLOR=blue][HREF=http://www.klg.com/nonag]http://www.klg.com/nonag[/HREF][DEFAULT_COLOR] or contact a JClass reseller.  To [NEWLINE]find your local JClass reseller please check [COLOR=blue][HREF=http://www.klg.com/resellers_frame.html]http://www.klg.com/resellers_frame.html[/HREF][DEFAULT_COLOR], [NEWLINE]or call KL Group at: [NEWLINE][VERT_SPACE=10][HORIZ_SPACE=20]North America:  800-663-4723[NEWLINE][HORIZ_SPACE=20]Europe:[HORIZ_SPACE=43]+31 (0) 20 679 95 03[NEWLINE][HORIZ_SPACE=20]Rest of World: [HORIZ_SPACE=8](416) 594-1026[NEWLINE][VERT_SPACE=10][FONT=arial-plain-12]For information about JClass[DEFAULT_COLOR] products for purchase please visit: [NEWLINE][HORIZ_SPACE=60][COLOR=blue][HREF=http://www.sitraka.com/software/jclass/]http://www.sitraka.com/software/jclass/[/HREF][DEFAULT_COLOR][NEWLINE][/FONT][VERT_SPACE=20][FONT=arial-plain-12]For product documentation and support:[NEWLINE][HORIZ_SPACE=20]Local:[HORIZ_SPACE=75][COLOR=blue]" + s2 + s3 + "jclass" + s3 + s + s3 + "api" + s3 + "index.html[NEWLINE][HORIZ_SPACE=20][FONT=arial-plain-12]" + "[DEFAULT_COLOR]Online at KL Group:[HORIZ_SPACE=4][COLOR=blue]" + "[HREF=http://www.klg.com/jclass/" + s1 + "/api/index.html]http://www.klg.com/jclass/" + s1 + "/api/index.html[/HREF][NEWLINE]" + "[DEFAULT_COLOR][HORIZ_SPACE=19]Download Centre:[HORIZ_SPACE=12]" + "[COLOR=blue][HREF=http://www.klg.com/dload]" + "http://www.klg.com/dload[/HREF][DEFAULT_COLOR]" + "[NEWLINE][HORIZ_SPACE=20]Knowledge Base:[HORIZ_SPACE=13]" + "[COLOR=blue][HREF=http://www.klg.com/cgi-bin/jcl_search.cgi]" + "http://www.klg.com/cgi-bin/jcl_search.cgi[/HREF][DEFAULT_COLOR]" + "[NEWLINE][DEFAULT_COLOR][NEWLINE][VERT_SPACE=10]";
    }

    public static String[] getEvalPackageNames()
    {
        return evalPackageNames;
    }

    private static Properties getJClassProperties()
    {
        String s = getPropertyFile();
        Properties properties = new Properties();
        try
        {
            properties.load(new BufferedInputStream(new FileInputStream(s)));
        }
        catch(FileNotFoundException _ex)
        {
            try
            {
                createPropertyFile();
                properties.load(new BufferedInputStream(new FileInputStream(s)));
            }
            catch(IOException _ex2) { }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return properties;
    }

    private static String getJClassProperty(String s)
    {
        Properties properties = getJClassProperties();
        return properties.getProperty(s);
    }

    public static String getProperty(String s)
    {
        return getJClassProperty("jclass." + s);
    }

    public static String getProperty(String s, String s1)
    {
        return getJClassProperty(s + "." + s1);
    }

    public static String getProperty(String s, String s1, String s2)
    {
        return getJClassProperty(s + "." + s1 + "." + s2);
    }

    public static String getPropertyFile()
    {
        return getPropertyFilePath() + System.getProperty("file.separator") + "jclass.properties";
    }

    public static String getPropertyFilePath()
    {
        String s = System.getProperty("JCLASS_HOME");
        if(s == null)
        {
            String s1 = new String(System.getProperty("user.home"));
            String s2 = System.getProperty("file.separator");
            if(!s1.endsWith(s2))
                s1 = s1 + s2;
            s = s1 + "." + s2 + "jclass";
        }
        if(s == null)
            s = ".";
        return s;
    }

    public static Image makeKLImage(Component component)
    {
        SystemColor systemcolor = SystemColor.control;
        JCImageCreator jcimagecreator = new JCImageCreator(component, 87, 50);
        if(systemcolor != null)
            jcimagecreator.setColor('a', systemcolor);
        else
            jcimagecreator.setColor('a', null);
        jcimagecreator.setColor('E', new Color(0xff9c70));
        jcimagecreator.setColor('P', new Color(0x6699ff));
        jcimagecreator.setColor('G', new Color(0xff6520));
        jcimagecreator.setColor('Y', new Color(0x2670ff));
        jcimagecreator.setColor('o', new Color(0xafbcbf));
        jcimagecreator.setColor('t', new Color(0xffdecf));
        jcimagecreator.setColor('Z', new Color(0x4383ff));
        jcimagecreator.setColor('R', new Color(0x7ca9ff));
        jcimagecreator.setColor('U', new Color(0xa8c5ff));
        jcimagecreator.setColor('F', new Color(0xffa780));
        jcimagecreator.setColor('y', new Color(0xff9966));
        jcimagecreator.setColor('v', new Color(0xffa77f));
        jcimagecreator.setColor('3', new Color(0x879a9f));
        jcimagecreator.setColor('C', new Color(0xff7030));
        jcimagecreator.setColor('I', new Color(0xffd4c0));
        jcimagecreator.setColor('h', new Color(0xe7ebec));
        jcimagecreator.setColor('Q', new Color(0xb6cfff));
        jcimagecreator.setColor('c', new Color(0xbfc9cc));
        jcimagecreator.setColor('A', new Color(0xffe9df));
        jcimagecreator.setColor('S', new Color(0x9abcff));
        jcimagecreator.setColor('O', new Color(0xd3e2ff));
        jcimagecreator.setColor('s', new Color(0xffb28f));
        jcimagecreator.setColor('d', new Color(0xb7c3c5));
        jcimagecreator.setColor('k', new Color(0x809499));
        jcimagecreator.setColor('L', new Color(0x8bb3ff));
        jcimagecreator.setColor('z', new Color(0xff7b40));
        jcimagecreator.setColor('K', new Color(0xf0f5ff));
        jcimagecreator.setColor('n', new Color(0xf7f8f9));
        jcimagecreator.setColor('m', new Color(0xe0e5e6));
        jcimagecreator.setColor('g', new Color(0xeff2f2));
        jcimagecreator.setColor('J', new Color(0xc5d9ff));
        jcimagecreator.setColor('u', new Color(0xffbd9f));
        jcimagecreator.setColor('q', new Color(0x90a1a6));
        jcimagecreator.setColor('H', new Color(0xff8650));
        jcimagecreator.setColor('1', new Color(0x256fff));
        jcimagecreator.setColor('2', new Color(0xe2ecff));
        jcimagecreator.setColor('W', new Color(0xa9c6ff));
        jcimagecreator.setColor('e', new Color(0x9faeb2));
        jcimagecreator.setColor('j', new Color(0x889b9f));
        jcimagecreator.setColor('l', new Color(0x97a8ac));
        jcimagecreator.setColor('a', new Color(0xffffff));
        jcimagecreator.setColor('f', new Color(0xc8d0d3));
        jcimagecreator.setColor('T', new Color(0x3379ff));
        jcimagecreator.setColor('D', new Color(0xffc8af));
        jcimagecreator.setColor('b', new Color(0xd7dddf));
        jcimagecreator.setColor('N', new Color(0xe3ecff));
        jcimagecreator.setColor('p', new Color(0xc0cacc));
        jcimagecreator.setColor('X', new Color(0x1766ff));
        jcimagecreator.setColor('w', new Color(0xfff5f0));
        jcimagecreator.setColor('r', new Color(0xcfd7d9));
        jcimagecreator.setColor('B', new Color(0xff5a10));
        jcimagecreator.setColor('i', new Color(0xa7b5b9));
        jcimagecreator.setColor('M', new Color(0x518cff));
        jcimagecreator.setColor('x', new Color(0xff5001));
        jcimagecreator.setColor('V', new Color(0xd5e3ff));
        jcimagecreator.setPixels(kl_logo);
        return jcimagecreator.create();
    }

    private static void setJClassProperty(String s, String s1)
    {
        Properties properties = getJClassProperties();
        properties.put(s, s1);
        try
        {
            BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(getPropertyFile()));
            properties.save(bufferedoutputstream, getPropertyFile());
            bufferedoutputstream.flush();
            bufferedoutputstream.close();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public static void setProperty(String s, String s1)
    {
        setJClassProperty("jclass." + s, s1);
    }

    public static void setProperty(String s, String s1, String s2)
    {
        setJClassProperty(s + "." + s1, s2);
    }

    public static void setProperty(String s, String s1, String s2, String s3)
    {
        setJClassProperty(s + "." + s1 + "." + s2, s3);
    }

    private static String kl_logo[] = {
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcdecfgaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahcidchaaaaaaaaaagcjkkkkkkkdaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaackkkkkklfmaaaanbokkkkkkkkkkkpaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqkkkkkkkkqeepeekkkkkkkkkkkkkeaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaamqkkkkqpgaaaaaaabekkkkkkkkkkbaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaammmhaaaaaaaaaaaamokkkkkkemaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaamccecfgaaaaaaaaaaaaaaaaaaaaaaaaaaaahmmmgaaaaaaa", "aaaaaaaaaaaaaaaaaaaaagcodchaaaaaaaaaancjkkkkkkkdnaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaarkkkkkkqdmaaaaamojkkkkkkkkkkoaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
        "aaaaaaaaaaaaaaaaaaaaekkkkkkkkjeeieekkkkkkkkkkkkkeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaahqkkkkjdhaaaaaaabekkkkkkkkkkraaaaabdeeedbaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaahmmhaaaaaaaaahdejkkkkkkkqbaaancjkkkkkkklhaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabkkkkkkkjopgaagbojkkkkkkkkkklaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaadkkkkkkkkqipppolkkkkkkkkkkkkkaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaanikkkkqdgaaaaaaagpjkkkkkkkkkpaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaanmmgaaaaaaaaaaaanflkkkkklbaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaanmmmnaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
        "aaaaaaaaaaaaastaattaaaaaaaaaaaaaaauvaaaaaaaaaaaaaaaaaaaaaaaaanmccccmaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaawxyaazzaaaaaaaaaaaaaaazxaaaaaaaaaaaaaaaaaaaaaaagojkkkkkkqbaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaAaaazzaaaaaaaaaaaaaaazxaaaaaaaaaaaaaaaaaaaanbokkkkkkkkkkkhaaaaaaaaaaaaaaaa", "aaaaaavBxCDaazuaEBBzttzuCFauGxBywazxawHztaDGxxywaaaaaapeqkkkkkkkkkkkkkpaaaaaaaaaaaaaaaa", "aaaaasxsIHCaaxFatCCIwIxxGutxzIFxuazxwCxuaAxzIFxsaaaaaaaaagpjkkkkkkkkkjgaaaaaaaaaaaaaaaa", "aaaaaFxyIwIaaxFaazzaaIxHaawIIszxFazxCxsaawIIsyxFaaaaaaaaaaanfqkkkkkkehaaaaaaaaaaaaaaaaa", "aaaaawzxxxzaaxFaazzaaIxsaawCxBzxFazxxxEaawzxxHxFaaaaaaaaaaaaaagmrpmgaaaaaaaaaaaaaaaaaaa", "aaaaaaaADzxIaxFaazzaaIxIaaFxDaIxFazxuHxIasxFawxFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaauBDICxtaxFaazBvAIxIaaFxEDCxFazxawBGwsxEDzxFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaatzxxBuaaxFaauBxtIxIaawzxxzxEazxaaDxEwHxxyxyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaJKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaLMNLLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaOPMQKRMSaTTOTTOPaROUUQMMOOPPaRMLaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaLRVWSPaTOLLaLLaPJXLPJWQPLJYQWZJTOaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaNRZRLUaPWLLaLLaWMRXYaMPRLJMaLMLLNaaaaaaaaaaaaaaaaaaaa", 
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaLRPPJ1LTKLLaSZ2NXJPPaTRZLJMaNTRTOaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaJJaaNJKaNNaaJKaJaNNaKJVVKVaaNJNaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaamacaaaaaaggaaaaaaaldaaaaaraaaaaaaamaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaonncn3faammddmbhcdbrafcadqmbmcohdfdgccg3dhohaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaapakphllaapmojngaojaaadonddngnojmmkpnkohcofeoaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaagnmgabgaafamhahahhaaahmammahahhnnmnghmgednrnaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    };
    protected static final String ad_title = "[FONT=timesroman-plain-20][COLOR=red]J[COLOR=blue]Class[DEFAULT_COLOR] Java Beans!";
    protected static final String ad = "[FONT=timesroman-plain-12] [VERT_SPACE=1][HORIZ_SPACE=2]Java developers worldwide are using JClass to create professional GUI [NEWLINE] applications. All JClass components are 100% Java, support[NEWLINE] easy-to-use developer interfaces, are available for JDK 1.0.2 and JDK 1.1[NEWLINE] with the same API, and are supported in all popular IDEs. The JClass[NEWLINE] family includes: [NEWLINE][NEWLINE] [HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] BWT[/UL], a Java GUI toolkit that enhances and extends[NEWLINE][HORIZ_SPACE=9]AWT with over 20 components plus rich text.[NEWLINE][NEWLINE] [HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] LiveTable[/UL] , the essential Java grid/table to easily display[NEWLINE] [HORIZ_SPACE=9]and manipulate tables and forms.[NEWLINE][NEWLINE] [HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] Chart[/UL], a powerful component to embed sophisticated[NEWLINE] [HORIZ_SPACE=9]graphs and charts.[NEWLINE] [HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] Field[/UL], masked edit fields and data validation for data entry. [NEWLINE][HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] HiGrid[/UL], an innovative outline-grid for developing multi-level database GUIs. [NEWLINE][HORIZ_SPACE=5]* [UL]JClass[DEFAULT_COLOR] DataSource[/UL], hierarchical data source components for platform-independent data binding. [NEWLINE] [NEWLINE] [VERT_SPACE=3][HORIZ_SPACE=2]All our components are 100% Pure JavaBeans, and are built to run in a variety of[NEWLINE] environments.  Demos of all products are available [HREF=http://www.klg.com/jclass]online[/HREF].[NEWLINE] [NEWLINE] [VERT_SPACE=3][HORIZ_SPACE=2]Contact us at [COLOR=blue]www.klg.com [DEFAULT_COLOR] for more details! ";
    protected static final String product_info = "[FONT=arial-plain-12]For information about JClass[DEFAULT_COLOR] products for purchase please visit: [NEWLINE][HORIZ_SPACE=60][COLOR=blue][HREF=http://www.sitraka.com/software/jclass/]http://www.sitraka.com/software/jclass/[/HREF][DEFAULT_COLOR][NEWLINE][/FONT]";
    private static final String defaultProperties[][] = {
        {
            "datasource.data_bean_component.customizer.show_tips_at_start", "true"
        }, {
            "datasource.tree_data_bean_component.customizer.show_tips_at_start", "true"
        }, {
            "datasource.jdbc.drivers", "sun.jdbc.odbc.JdbcOdbcDriver"
        }, {
            "datasource.jdbc.urls", ""
        }, {
            "datasource.jdbc.urls.delimiter", "#!#*#"
        }, {
            "higrid.higrid_bean_component.customizer.show_tips_at_start", "true"
        }
    };
    private static final String evalMessage = "[VERT_SPACE=10][FONT=arial-plain-12]To purchase permanent JClass product licenses, including technical support services[NEWLINE]and/or source code, or to inquire about any of the other tools KL Group provides [NEWLINE]Java developers, go to [COLOR=blue][HREF=http://www.klg.com/nonag]http://www.klg.com/nonag[/HREF][DEFAULT_COLOR] or contact a JClass reseller.  To [NEWLINE]find your local JClass reseller please check [COLOR=blue][HREF=http://www.klg.com/resellers_frame.html]http://www.klg.com/resellers_frame.html[/HREF][DEFAULT_COLOR], [NEWLINE]or call KL Group at: [NEWLINE][VERT_SPACE=10][HORIZ_SPACE=20]North America:  800-663-4723[NEWLINE][HORIZ_SPACE=20]Europe:[HORIZ_SPACE=43]+31 (0) 20 679 95 03[NEWLINE][HORIZ_SPACE=20]Rest of World: [HORIZ_SPACE=8](416) 594-1026[NEWLINE][VERT_SPACE=10]";
    private static final String evalPackageNames[] = {
        "bwt", "chart", "datasource", "field", "higrid", "table3"
    };

}
