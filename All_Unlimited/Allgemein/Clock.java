package All_Unlimited.Allgemein;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import javax.swing.JFrame;

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
public class Clock extends Tabellenspeicher
{
  private static final long serialVersionUID = -123457L;

  private Global g=null;
  private int iCPos=0;
  private long lClock1 = 0;
  private long lClock2 = 0;

  //private long lVon=0;
  //private long lBis=0;

  private int iAnzSQL=0;
  private int iAnzSQLMom=0;
  private int iNr=0;
  private JFrame Fom;

  public Clock(Global rg,JFrame rFom)
  {
    super(rg,new String[] {"Art","Abfrage","Zeit","Prozent","Sql","Nr"});//,"von","bis"});
    g=rg;
    iAnzSQL=g.getSqlAnzahl();
    iAnzSQLMom=iAnzSQL;
    Fom=rFom;
  }

  public static long startClock(Clock clock)
  {
    long lClock = Static.get_ms();
    //long lBis2=Static.lLast;
    if (clock != null)
    {
      if (clock.iCPos==0)
        clock.lClock1=lClock;
      clock.lClock2=lClock;
      //clock.lBis=lBis2;
      clock.iCPos++;
    }
    return lClock;
  }

  /*private long startClock()
  {
    iCPos++;
    long lClock = System.Static.get_ms()();
    return lClock;
  }*/

  public static void add(Clock clock,String sArt,String sAbfrage,long lClock)
  {
    if (clock != null) clock.add(sArt,sAbfrage,lClock);
  }

  private void add(String sArt,String sAbfrage,long lClock)
  {
    if (g.Clock2())
    {
      //long lClockSave=lClock;
      if (lClock==-1) lClock=lClock2;
      lClock2=Static.get_ms();
      //lVon=lBis;
      //lBis=Static.lLast;
      //if (lClock>lClock2)
      //  g.progInfo("Clock von "+lClock+" nach "+lClock2);
      g.clockInfo2(sArt+" "+sAbfrage,lClock);
      newLine();
      setInhalt("Art",sArt);
      setInhalt("Abfrage",sAbfrage);
      setInhalt("Zeit",(int)(lClock2-lClock));
      int iMom=g.getSqlAnzahl();
      setInhalt("Sql",iMom-iAnzSQLMom);
      setInhalt("Nr",++iNr);
      iAnzSQLMom=iMom;
      //setInhalt("von",lVon-Static.lStart);
      //setInhalt("bis",lBis-Static.lStart);
    }
  }

  public static void showClock(Transact rg,Clock clock,String s,long lClock)
  {
    int iMom=rg.getSqlAnzahl();
    //  g.clockInfo2(s+", Qry="+(iMom-iAnzSQL),lClock1);
    rg.clockInfo(s+(clock==null?"":", Qry="+(iMom-clock.iAnzSQL)), lClock);
    if (clock != null)
    {
      clock.showClock(s,iMom);
      clock.iAnzSQL=iMom;
      clock.iAnzSQLMom=iMom;
      clock.iNr=0;
    }
  }

  private void showClock(String s,int iMom)
  {
    iCPos--;
    if (g.Clock2() && iCPos==0)
    {
      long l = Static.get_ms() - lClock1;
      if (l>0)
      {
        int iRest=(int)(l-sum("Zeit"));
        int iSql=iMom-iAnzSQL;
        if (iRest>0)
        {
          newLine();
          setInhalt("Art", "Rest");
          setInhalt("Zeit", iRest);
          setInhalt("Sql",iSql-(int)sum("Sql"));
        }
        else
          l=(long)sum("Zeit");
        for(moveFirst();!eof();moveNext())
          setInhalt("Prozent",new Zahl1(100.0*getF("Zeit")/l,null));

        newLine();
        setInhalt("Art", "*** Gesamt ***");
        setInhalt("Zeit", l);
        setInhalt("Prozent",new Zahl1(100.0,null));
        setInhalt("Sql",iSql);

        if (Grid==null)
        {
          Grid=new JCOutliner(new JCOutlinerFolderNode(""));
          Grid.setColumnAlignments(new int[] {BWTEnum.TOPLEFT,BWTEnum.TOPLEFT,BWTEnum.TOPRIGHT,BWTEnum.TOPRIGHT});
        }
        if (Fom!=null)
          showGrid(s,Fom,true);
        else
          showGrid(s);
        clearAll();
      }
    }
  }

}
