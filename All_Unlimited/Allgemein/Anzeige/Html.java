package All_Unlimited.Allgemein.Anzeige;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2015</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.12
 */

import All_Unlimited.Allgemein.*;

public class Html
{
  private String s;
  private String sHtml;
  private String sCR;

  public Html(String rs,String rsCR)
  {
          s=rs;
          sCR=rsCR;
          sHtml=clearHtml(s,sCR);
          //System.out.println("Erzeuge Html:"+s);
          Count.add(Count.Html);
  }

  public void finalize()
  {
          Count.sub(Count.Html);
  }

  public boolean isNull()
  {
    return Static.Leer(s);
  }

  public String toString()
  {
    return sHtml;
  }

  private static String UmlauteErsetzen(String s)
  {
    return s.replaceAll("&#38;","&").replaceAll("&#196;","Ä").replaceAll("&#214;","Ö").replaceAll("&#220;","Ü")
        .replaceAll("&#228;","ä").replaceAll("&#246;","ö").replaceAll("&#252;","ü").replaceAll("&#223;","ß")
        .replaceAll("&quot;","\"").replaceAll("&#167;","§").replaceAll("&amp;","&")
        .replaceAll("&#178;","²").replaceAll("&#179;","³").replaceAll("&#8364;","€").replaceAll("&#181;","µ")
        .replaceAll("&lt;","<").replaceAll("&gt;",">").replaceAll("&#160;"," ");
  }

  public static Object check(String s)
  {
    if (s==null || !s.startsWith("<html>"))
      return s;
    else
      return new Html(s," ");
  }

  public static String clearHtml(String s,String sCR)
        {
          if (s != null && s.startsWith("<html>"))
          {
            String[] sAry=s.split("\n");
            String s2=sAry[0].trim();
            for(int i=1;i<sAry.length;i++)
              s2+=(s2.endsWith(">")?"":" ")+sAry[i].trim();
            s=s2.substring(6,s2.length()-7).trim();
            int iPos=s.indexOf("<");
            while(iPos>=0)
            {
              int iPos2=s.indexOf(">");
              if (iPos2<0)
                iPos=-1;
              else
              {
                s2=s.substring(iPos+1,iPos2);
                s = s.substring(0, iPos) + (s2.equals("br") || s2.equals("/p") ?"K2CRLF"+s.substring(iPos2 + 1).trim():s.substring(iPos2 + 1));
                //System.out.println("TAG"+iAnz+":"+s2+"->"+s);
                iPos = s.indexOf("<");
              }
            }
            //System.out.println("clearHtml2:"+s);
            return UmlauteErsetzen(s.replaceAll("K2CRLF",sCR));//.replaceAll("  "," "));
          }
          else
            return s;
        }

  public String getValue()
  {
    return s;
  }

  public void setValue(String rs)
  {
    s=rs;
    sHtml=clearHtml(s,sCR);
  }

}
