package All_Unlimited.Allgemein.Anzeige;

import All_Unlimited.Allgemein.Static;
/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.10
 */
public class TMZ
{
  private boolean bTMZ;
  private boolean bH_min;
  private int iAnz=0;
  private double dSum=0;
  private int iOpen=0;
  private int iCore=0;
  private int iClose=0;
  private String sF;

  public TMZ(boolean rbTMZ,boolean rbH_min,String rsF)
  {
    bTMZ=rbTMZ;
    bH_min=rbH_min;
    sF=rsF;
  }

  public void add(double d)
  {
    dSum+=d;
    iAnz++;
    //System.out.println("add TMZ"+iPos+"->"+iAnz+"/"+dSum);
  }

  public void add(boolean bOpen,boolean bCore,boolean bClose)
  {
    if (bOpen) iOpen++;
    if (bCore) iCore++;
    if (bClose) iClose++;
    iAnz++;
  }

  public void add(TMZ x)
  {
    dSum+=x.dSum;
    iAnz+=x.iAnz;
    iOpen+=x.iOpen;
    iCore+=x.iCore;
    iClose+=x.iClose;
  }

  public String toString()
  {
    //System.out.println("toString TMZ:"+iAnz+"/"+bTMZ+"/"+dSum);
    return iAnz==0 ? "":bTMZ ? iOpen + " / " + iCore + " / " + iClose:
        Static.rightString("" + iAnz, 6) + Static.rightString("" + new Zahl1(dSum * (bH_min ? 3600. : 1), sF), 11);
  }
}
