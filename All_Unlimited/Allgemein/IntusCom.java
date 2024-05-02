package All_Unlimited.Allgemein;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Stack;
import java.util.Vector;

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
public class IntusCom
{
  private Global t2;
  private Global g;
  public boolean bFehler=false;
  private String sConn;
  private String sPW;
  private String sName;
  private Tabellenspeicher TabZT=null;
  private Vector<String> VecMA=null;
  private Vector<String> VecZ=null;
  public static int iWait=5;

  public IntusCom(Stack<String> Sta,Global rg)
  {
    t2 = new Global();
    g=rg;
    try
    {
      sConn = "" + Sta.pop();
      sPW = "" + Sta.pop();
      sName = "" + Sta.pop();
      t2.connect3(sConn, sName, sPW);
    }
    catch(Exception e)
    {
      g.printError("IntusCom:"+e);
      bFehler=true;
    }
  }

  public void free()
  {
    t2.disconnect();
  }

  public boolean reconnect()
  {
    t2.disconnect();
    return t2.connect3(sConn, sName, sPW);
  }
  
  public void exec(String s)
  {
	  int i=t2.exec(s);
	  if (i>0)
	  t2.fixInfo("Intuscom: "+i+" Zeilen "+(s.toLowerCase().startsWith("delete") ? "gelöscht":"geändert"));
  }

  public void ReadStamps(String sTerminal,Tabellenspeicher Tab)
  {
    Parameter Para = new Parameter(g,"Terminal");
    Para.getMParameterH("Werte");
    int iAnzKST=Para.bGefunden?Para.int2:6;
    int iAnzTaetig=Para.bGefunden?Para.int3:3;
    int iAnzAuftrag=Para.bGefunden?Para.int4:15;
    boolean bNullen = Para.bGefunden&&(Para.int1&1)==1;
    boolean bHitag = Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.HITAG;
    boolean bLegic = Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.LEGIC;
    //boolean bMiro = Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.MIRO;
    boolean bRo=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.RO;
    //boolean bSymbol=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.SYMBOL;
    boolean bMifare=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARE;
    boolean bMifare2=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARE2;
    //boolean bHitagHex=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.HITAGHEX;
    boolean bKarteNullen = Para.bGefunden?(Para.int1&TabTerminal.KARTE0)==0:true;
    Para.free();
    //g.fixInfo("ReadStamps: KSt="+iAnzKST+", Tat="+iAnzTaetig+", Auftrag="+iAnzAuftrag+", Nullen="+bNullen);
    long lLastK=0;
    String sLastK="";
    if (Static.bOhneProt)
    {
      g.bISQL = true;
      if (VecZ == null)
      {
        VecZ = SQL.getSVector("select * from all_Zutritt_ohne_prot", g);
        if (VecZ == null)
          Static.printError("View all_Zutritt_ohne_prot nicht gefunden");
      }
      if (TabZT == null)
        TabZT = new Tabellenspeicher(g, "select * from all_terminal_ohne_prot", true);
      g.bISQL = false;
      if (TabZT == null)
        Static.printError("View all_terminal_ohne_prot nicht gefunden");
      else
      {
        if (TabZT.size() == 0)
          Static.printError("Keine nur-Zutritt-Terminals definiert");
        else if (TabZT.posInhalt("Terminal", sTerminal) && TabZT.getB("nicht") && VecZ != null)
          t2.exec("delete from INTUSCOM_UPLOAD_BOOKINGS where TERMINAL_ID='" + sTerminal + "' and RECORD_TYPE" + Static.SQL_in(VecZ));
      }
    }
    Tabellenspeicher Tab2=new Tabellenspeicher(t2,"select * from INTUSCOM_UPLOAD_BOOKINGS where STATUS='v' and TERMINAL_ID='"+sTerminal+"'",true);
    if (Static.bOhneProt && Tab2.size()>0 && VecMA==null)
    {
      g.bISQL = true;
      VecMA = SQL.getSVector("select * from all_ma_ohne_prot", g);
      g.bISQL = false;
      if (VecMA==null)
        Static.printError("View all_ma_ohne_prot nicht gefunden");
      else if (VecMA.size()==0)
        Static.printError("Keine Mitarbeiter ohne Zutritts-Protokollierung definiert");
    }

    for(Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
    {
      String sTaetigkeit = "";
      String sAuftrag = "";
      String sKostenstelle = "";
      String sSatz=Tab2.getS("EXTRA_DATA").trim();
//      if (!Static.Leer(sSatz))
//    	  g.fixInfo(Tab2.getInt("AIC")+": Satz="+sSatz+", L="+sSatz.length());
      int iA=0;
      boolean bRead=true;
      while (bRead)
      {
    	int iE=sSatz.indexOf(';',iA);
    	bRead=iE != -1;
        String sTAK = bRead ? sSatz.substring(iA,iE).trim():sSatz.substring(iA);
        //g.fixInfo("Read="+bRead+", L="+sTAK.length()+":"+sTAK);
        if(sTAK.length()==iAnzTaetig)
                sTaetigkeit=sTAK;
        else if(sTAK.length()==iAnzAuftrag)
                sAuftrag=sTAK;
        else if(sTAK.length()==iAnzKST)
                sKostenstelle=sTAK;
        iA=iE+1;
      }
      String sKartenNr=Tab2.getS("TIMEID_NO").trim();
      String sK=sKartenNr+Tab2.getS("RECORD_TYPE")+Tab2.getS("TERMINAL_ID")+":"+Tab2.getS("SUB_TERMINAL_ID");
      String sK2=bKarteNullen ? Static.FillNull(sKartenNr, bHitag || bLegic ? 6 : bMifare2 ? 8 : bMifare ? 10 : bRo ? 12 : 16) : Static.CutNull(sKartenNr);
      String sTime=Tab2.getS("BOOKING_DATE")+Tab2.getS("BOOKING_TIME");
      java.util.Date dt=new SimpleDateFormat("yyyyMMddHHmmss").parse(sTime,new ParsePosition(0));
      //g.testInfo("Terminal="+sTerminal+", Karte="+sK2+", Art="+Tab2.getS("RECORD_TYPE"));
      if (VecZ != null && VecZ.contains(Tab2.getS("RECORD_TYPE")) && (TabZT!=null && TabZT.posInhalt("Terminal",sTerminal) && TabZT.getB("nicht") || VecMA!=null && VecMA.contains(sK2)))
      {
        //g.testInfo("entferne Zutritt");
        t2.exec("delete from INTUSCOM_UPLOAD_BOOKINGS where AIC=" + Tab2.getInt("AIC"));
      }
      else if (!sK.equals(sLastK) || dt!=null && dt.getTime()>lLastK+30000)
      {
        Tab.newLine();
        Tab.putElementAt(Tab.getPos(), 0, Tab2.getInt("AIC"));
        Tab.putElementAt(Tab.getPos(), 1, g.getZone());
        Tab.putElementAt(Tab.getPos(), 3, sK2);
        Tab.putElementAt(Tab.getPos(), 4, Tab2.getS("RECORD_TYPE"));
        Tab.putElementAt(Tab.getPos(), 5, dt);
        Tab.putElementAt(Tab.getPos(), 6, sKostenstelle.equals("") ? null : bNullen ? sKostenstelle : Static.CutNull(sKostenstelle));
        Tab.putElementAt(Tab.getPos(), 7, sAuftrag.equals("") ? null : bNullen ? sAuftrag : Static.CutNull(sAuftrag));
        Tab.putElementAt(Tab.getPos(), 8, sTaetigkeit.equals("") ? null : bNullen ? sTaetigkeit : Static.CutNull(sTaetigkeit));
        Tab.putElementAt(Tab.getPos(), 9, Tab2.getS("TERMINAL_ID"));
        Tab.putElementAt(Tab.getPos(), 10, Tab2.getS("SUB_TERMINAL_ID"));
        Tab.putElementAt(Tab.getPos(), 11, Tab2.getS("ERROR_STATUS"));
        lLastK = dt.getTime();
        sLastK = "" + sK;
      }
      else
        Quittieren(Tab2.getInt("AIC"));
      //Tab.putElementAt(Tab.getPos(), 11, Tab2.getS("TEMPLATES_ID"));
    }
  }

  public void Quittieren(int iAIC)
  {
    t2.exec("UPDATE INTUSCOM_UPLOAD_BOOKINGS SET STATUS='r' where AIC=" + iAIC);
  }
  
  public void ClearStamp(int iAIC)
  {
    t2.exec("delete from INTUSCOM_UPLOAD_BOOKINGS where AIC=" + iAIC);
  }

  private boolean WriteIMR(Tabellenspeicher Tab,SQL Qry)
  {
    Qry.add("CLIENT","01");
    Qry.add("TIMEID_NO",Static.FillNull(Tab.getS(3),10));
    Qry.add("ACCESS_PROFILE_NO",Static.FillNull(Tab.getS(4),3));
    Qry.add("BOOKING_PROFILE_NO",Static.FillNull(Tab.getS(5),3));
    Qry.add("AUTHORISATION_GROUP",Static.FillNull(Tab.getS(6),3));
    Qry.add("PIN_CODE",Tab.getS(7));
    Qry.add("ATTENDANCE_STATUS","*");
    Qry.add("MAIL_NO","00");
    Qry.add("INFO_FIELD_1",Tab.getS(8));
    Qry.add("INFO_FIELD_2",Tab.getS(9));
    Qry.add("TEMPLATES_ID",Static.FillNull(Tab.getS(10),8));
    Qry.add("ALTERNATIVE_AUTH","1");
    Qry.add("FROM_DATE","20000101");
    Qry.add("TO_DATE","21000101");
    Qry.add("FROM_TIME","0000");
    Qry.add("TO_TIME","2400");
    Qry.add("STATUS","v");
    Qry.addnow("TIME_STAMP");
    Qry.insert("INTUSCOM_MASTER_RECORDS",false);
    Static.sleep(iWait);
    return t2.sError==null || t2.sError.indexOf("socket write error")<0;
  }

  public void StammUpload(Tabellenspeicher Tab)
  {
    long lClock = Static.get_ms();
    if (Tab==null || Tab.isEmpty())
    {
      Static.printError("StammUpload: Tabelle leer");
      return;
    }
    boolean b=false;
    int i=0;
    while(!b && i<3)
    {
      i++;
      if (reconnect())
      {
        t2.start();
        b = t2.exec("delete from INTUSCOM_MASTER_RECORDS") > -2;
        if (b)
        {
          SQL Qry = new SQL(t2);
          for (Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
            b = b && WriteIMR(Tab, Qry);
          if (b)
          {
            Qry.addnow("TIME_STAMP");
            Qry.addWhere("TABLE_ID", "76");
            Qry.update("INTUSCOM_TIMESTAMPS");
          }
          Qry.free();
        }
        if (b)
          t2.commit();
        else
        {
          t2.rollback();
          Static.printError(i+". StammUpload: rollback wegen Fehler");
        }
      }
      else
        Static.printError(i+". StammUpload: Verbindung zu Intuscom nicht möglich");
      if (!b && i<3)
        Static.sleep(60000);
    }
    g.clockInfo("StammUpload von "+Tab.size()+" Zeilen",lClock);
  }

  public void ChangeStammUpload(Tabellenspeicher Tab)
  {
    t2.exec("delete from INTUSCOM_MASTER_RECORDS where TEMPLATES_ID='"+Static.FillNull(Tab.getS(10),8)+"'");
    SQL Qry=new SQL(t2);
    WriteIMR(Tab,Qry);
    Qry.addnow("TIME_STAMP");
    Qry.addWhere("TABLE_ID","76");
    Qry.update("INTUSCOM_TIMESTAMPS");
    Qry.free();
  }

  public void UploadTI(Tabellenspeicher Tab)
  {
    if (Tab==null || Tab.isEmpty())
    {
      Static.printError("UploadTI: Tabelle leer");
      return;
    }
    t2.start();
    t2.exec("delete from INTUS_FP_TEMPLATES_IDS");
    SQL Qry=new SQL(t2);
    for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
    {
      Qry.add("TEMPLATES_ID",Static.FillNull(Tab.getS(1),8));
      Qry.add("SURNAME",Tab.getS(2));
      Qry.add("FIRST_NAME",Tab.getS(3).equals("")?" ":Tab.getS(3));
      Qry.add("PERNO",Static.FillNull(Tab.getS(4),5));
      Qry.insert("INTUS_FP_TEMPLATES_IDS",false);
      Static.sleep(iWait);
    }
    Qry.addnow("TIME_STAMP");
    Qry.addWhere("TABLE_ID","TI");
    Qry.update("INTUSCOM_TIMESTAMPS");
    Qry.free();
    t2.commit();
  }

  public void ChangeTI(Tabellenspeicher Tab)
  {
    String sID=Static.FillNull(Tab.getS(1),8);
    t2.exec("delete from INTUS_FP_TEMPLATES_IDS where TEMPLATES_ID='"+sID+"'");
    SQL Qry=new SQL(t2);
    Qry.add("TEMPLATES_ID",sID);
    Qry.add("SURNAME",Tab.getS(2));
    Qry.add("FIRST_NAME",Tab.getS(3));
    Qry.add("PERNO",Static.FillNull(Tab.getS(4),5));
    Qry.insert("INTUS_FP_TEMPLATES_IDS",false);
    Qry.addnow("TIME_STAMP");
    Qry.addWhere("TABLE_ID","TI");
    Qry.update("INTUSCOM_TIMESTAMPS");
    Qry.free();
  }

  public void ZeitZutritt(Tabellenspeicher Tab,char c)
  {
    if (Tab==null || Tab.isEmpty())
    {
      Static.printError("ZeitZutritt: "+c+"-Tabelle leer");
      return;
    }
    t2.start();
    t2.exec("delete from INTUSCOM_PROFILES where PROFILE_TYPE='"+c+"'");
    SQL Qry=new SQL(t2);
    for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
    {
      if (c=='B')
      {
        Qry.add("SERVER_ID", "00");
        Qry.add("TERMINAL_ID",Tab.getS(5)); // 2-stellig statt IP-Adresse
        Qry.add("SUB_TERMINAL_ID",Static.VorNull(0));
        Qry.add("PROFILE_TYPE",""+c);
        Qry.add("PROFILE_NO",Tab.getS(6));
        Qry.add("FROM_TIME","0000");
        Qry.add("TO_TIME","2400");
        Qry.add("SUNDAY",true);
        Qry.add("MONDAY",true);
        Qry.add("TUESDAY",true);
        Qry.add("WEDNESDAY",true);
        Qry.add("THURSDAY",true);
        Qry.add("FRIDAY",true);
        Qry.add("SATURDAY",true);
        Qry.add("FROM_DATE","--------");
        Qry.add("TO_DATE","--------");
        Qry.add("SD_FLAG",0);
        Qry.add("SD_GROUP","--"); // Sondertagsgruppe muß später übergeben werden
        Qry.insert("INTUSCOM_PROFILES",false);
      }
      else
       for (int i=0;i<17;i++)
        if (Tab.getB(i+15))
        {
          Qry.add("SERVER_ID", "00");
          Qry.add("TERMINAL_ID",Tab.getS(33)); // 2-stellig statt IP-Adresse
          Qry.add("SUB_TERMINAL_ID",Static.VorNull(i));
          Qry.add("PROFILE_TYPE",""+c);
          Qry.add("PROFILE_NO",Tab.getS(34));
          Qry.add("FROM_TIME",Tab.getElementAt(Tab.getPos(),5)==null?"0000":new DateWOD(((java.sql.Timestamp)Tab.getElementAt(Tab.getPos(),5)).getTime()).Format("HHmm"));
          Qry.add("TO_TIME",Tab.getElementAt(Tab.getPos(),6)==null?"2400":new DateWOD(((java.sql.Timestamp)Tab.getElementAt(Tab.getPos(),6)).getTime()).Format("HHmm"));
          Qry.add("SUNDAY",Tab.getB(7));
          Qry.add("MONDAY",Tab.getB(8));
          Qry.add("TUESDAY",Tab.getB(9));
          Qry.add("WEDNESDAY",Tab.getB(10));
          Qry.add("THURSDAY",Tab.getB(11));
          Qry.add("FRIDAY",Tab.getB(12));
          Qry.add("SATURDAY",Tab.getB(13));
          Qry.add("FROM_DATE","--------");
          Qry.add("TO_DATE","--------");
          //Qry.add("SPECIAL_DAY_1",Tab.getB(13));
          boolean b=!Tab.getS(32).equals("");
          Qry.add("SD_FLAG",b?1:0);
          Qry.add("SD_GROUP",b?Tab.getS(35):"--"); // Sondertagsgruppe muß später übergeben werden
          Qry.insert("INTUSCOM_PROFILES",false);
        }
      Static.sleep(iWait);
    }
    Qry.addnow("TIME_STAMP");
    Qry.addWhere("TABLE_ID","74");
    Qry.update("INTUSCOM_TIMESTAMPS");
    Qry.free();
    t2.commit();
  }

  public void Holiday()
  {
    t2.start();
    t2.exec("delete from INTUSCOM_SPECIAL_DAYS");
    SQL Qry=new SQL(t2);
    for(int iPos=0;iPos<g.TabFeiertage.size();iPos++)
      if (g.TabFeiertage.getI(iPos,"Stamm")==Global.keinStamm && g.TabFeiertage.getI(iPos,"Land")==g.getLand())
      {
        DateWOD dt = (DateWOD)g.TabFeiertage.getInhalt("Datum", iPos);
        Qry.add("SD_GROUP", "01");
        Qry.add("SD_DATE", dt.Format("yyyyMMdd"));
        Qry.insert("INTUSCOM_SPECIAL_DAYS", false);
      }
    Qry.addnow("TIME_STAMP");
    Qry.addWhere("TABLE_ID","71");
    Qry.update("INTUSCOM_TIMESTAMPS");
    Qry.free();
    t2.commit();
  }

}
