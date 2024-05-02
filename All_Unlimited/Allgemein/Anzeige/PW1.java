package All_Unlimited.Allgemein.Anzeige;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class PW1
{
  public PW1(String s)
  {
    sPW=s;
    bNull=s==null || s.equals("");
  }

  public String toString()
  {
    if (bNull)
      return "";
    String s="";
    for(long j=Math.round(Math.random()*9)+1;j>0;j--)
      s=s+"*";
    return s;
  }

  public String getPW()
  {
    return sPW;
  }

  public boolean isNull()
  {
          return bNull;
  }


  private String sPW;
  private boolean bNull;

}
