

import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Scanner;
/*
    Wunsch.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/
// add your custom import statements here
//import All_Unlimited.Allgemein.Transact;
//import All_Unlimited.Hauptmaske.AClient;
import java.util.Vector;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Tabellenspeicher;

public class Wunsch
{
  public static String sAServer=null;

  // public static Socket connect()
  // {
  //   try
  //   {
  //     String sIP = sAServer.substring(0, sAServer.indexOf(':'));
  //     int iPort = Integer.parseInt(sAServer.substring(sAServer.indexOf(':') + 1));
  //     Socket server = new Socket(sIP, iPort);
  //     server.setSoTimeout(30000);
  //     return server;
  //   }
  //   catch(IOException e2)
  //   {
  //     sAServer=null;
  //     System.err.println("AClient.connect:" + e2);
  //     return null;
  //   }

  // }

  /*public static void send_AServer(String s)
  {
    if (sAServer==null)
      return;
    try
    {
      Socket server=connect();
      if (server!=null)
      {
        new ObjectOutputStream(server.getOutputStream()).writeObject(s);
        server.close();
      }
    }
    catch(Exception e2)
    {
      System.err.println("send_AServer "+s+":" + e2);
    }
  }*/

  // public static String get_AServer(String s)
  // {
  //   if (sAServer==null)
  //     return "x";
  //   try
  //   {
  //     Socket server=connect();
  //     if (server!=null)
  //     {
  //       new ObjectOutputStream(server.getOutputStream()).writeObject(s);
  //       String sGet = new Scanner(server.getInputStream()).nextLine();
  //       server.close();
  //       return sGet;//Integer.parseInt(sGet);
  //     }
  //   }
  //   catch(Exception e2)
  //   {
  //     System.err.println("AClient.get_AServer "+s+":" + e2);
  //   }
  //   return "-1";
  // }


    public static void main( String[] args )
    {
      /*All_Unlimited.Print.SimpleFilePrinter sfp = new All_Unlimited.Print.SimpleFilePrinter(args[0]);
     if (sfp.setupPageFormat()) {
       if (sfp.setupJobOptions()) {
         try {
           sfp.printFile();
         } catch (Exception e) {
           System.err.println(e.toString());
           System.exit(1);
         }
       }
     }*/

		//Transact t=new Transact();
                /*if (args.length<2)
                {
                  t.fixInfo("Mandant nicht angegeben");
                  System.exit(1);
                }*/
                //System.out.println("Sende "+args[1]+" an "+args[0]);
                // sAServer=args[0];
                //send_AServer(args[1]);
                // System.out.println((sCommand.startsWith("e:")?"":sCommand+"=")+get_AServer(sCommand));
                Global g=new Global();
                g.connect(args[0]);
                Tabellenspeicher Tab=new Tabellenspeicher(g, new String[] {"Bew", "Name", "Daten"});
                Tab.readFile(';', args[1], true);
                g.bISQL=true;             
                //Tab.showGrid();
                int iProt=Sort.geti(args[2]);
                int iEig=Sort.geti(args[3]);
                g.fixInfo("Prot="+iProt+", iEig="+iEig);
                Vector<Integer> Vec=SQL.getVector("select distinct aic_bew_pool from stammpool where aic_eigenschaft="+iEig, g);
                g.fixInfo("vorhanden:"+Vec);
                String sName=null;
                int iA=0;int iV=0;int iF=0;
                int iAG=0;int iVG=0;int iFG=0;
                SQL Qry=new SQL(g);
                for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                {
                  if (sName==null)
                    sName=Tab.getS("Name");
                  else if (!sName.equals(Tab.getS("Name")))
                  {
                    g.fixInfo(sName+":"+iA+" (vorhanden:"+iV+", Fehler="+iF+")");
                    iAG+=iA;iVG+=iV;iFG+=iF;
                    iA=0;iV=0;iF=0;
                    sName=Tab.getS("Name");
                  }
                  int iD=Tab.getI("Daten");
                  int iB=Tab.getI("Bew");
                  if (Vec.contains(iB))
                    iV++;
                  else
                  {
                    Qry.add("Aic_Daten",iD);
                    Qry.add("aic_bew_pool",iB);
                    Qry.add("Aic_protokoll",iProt);
                    Qry.add("Aic_eigenschaft",iEig);
                    int iAic=Qry.insert("Stammpool", true);
                    if (iAic>0)
                      iA++;
                    else
                      iF++;
                  }
                }
                Qry.free();
                iAG+=iA;iVG+=iV;iFG+=iF;
                if (sName!=null)
                  g.fixInfo(sName+":"+iA+" (vorhanden:"+iV+", Fehler="+iF+")");
                g.fixInfo("Gesamt:"+iAG+" (vorhanden:"+iVG+", Fehler="+iFG+")");

                //Transact.fixInfo(t.exec("update computer set cbits=cbits-"+t.ALLEIN+" where"+t.bit("cbits",t.ALLEIN))+" Sperren gel�scht");
                /*Tabellenspeicher Tab=new Tabellenspeicher(t,"select aic_benutzer,passwort,bits from benutzer",true);
                int iAnz=0;
                SQL Qry=new SQL(t);
                for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                {
                  if ((Tab.getI("bits")&0x0400)==0) // cstPWneu
                  {
                    Qry.add("bits",Tab.getI("bits")+0x0400);
                    Qry.add("Passwort",t.PasswordConvert(t.getPassword(Tab.getS("passwort"),false,0),true,Tab.getI("aic_benutzer")));
                    Qry.update("Benutzer",Tab.getI("aic_benutzer"));
                    iAnz++;
                  }
                }
                Transact.fixInfo(iAnz+" passwords changed");*/
                /*int iStt=SQL.getInteger(t,"select aic_stammtyp from stammtyp where kennung='Arbeitnehmer'");
                Transact.fixInfo(t.exec("update begriff set bits=bits+0x400000 where aic_begriffgruppe=30 and aic_bewegungstyp is not null"+
                       " and (aic_stammtyp is null or aic_stammtyp<>"+iStt+") and bits&0x400000=0")+"x kein Stamm-Zeitraum nachgesetzt");*/
                /*t.exec("ALTER TABLE BEW_BOOLEAN ALTER COLUMN SPALTE_BOOLEAN bit NULL");
                Transact.fixInfo("BEW_BOOLEAN-Tabelle richtiggestellt!");*/
                //Transact.fixInfo(t.exec("delete from defverlauf")+" DefVerlauf entfernt");
                /*Tabellenspeicher Tab=new Tabellenspeicher(t,"select * from stamm_protokoll where aic_rolle=1 and pro_aic_protokoll is null",true);
                Tabellenspeicher Tab2=new Tabellenspeicher(t,"select * from stammview2 where aic_stammtyp=163 and aic_rolle is null",true);
                SQL Qry=new SQL(t);
                int iAnz=0;
                for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                {
                  int iStamm=Tab.getI("aic_stamm");
                  if (!Tab2.posInhalt("aic_stamm",iStamm))
                  {
                    Qry.add("aic_stamm",iStamm);
                    Qry.add("aic_mandant",Tab.getI("aic_mandant"));
                    Qry.add("aic_protokoll",Tab.getI("aic_protokoll"));
                    Qry.add("Nr",2);
                    Qry.add("Bezeichnung",Tab.getS("Bezeichnung"));
                    Qry.add("Aic_Code",656);
                    Qry.insert("Stamm_Protokoll",false);
                    iAnz++;
                  }
                }
                Qry.free();
                Transact.fixInfo(iAnz+" als Person angelegt");*/
                /*int iM=SQL.getInteger(t,"select aic_mandant from mandant where kennung='"+args[1]+"'");
                Transact.fixInfo(t.exec("update zwischenspeicher set aic_mandant="+iM+
                                        " where pro_aic_protokoll is null and aic_mandant is null and kennung='Import'")+
                                 " Mandanten in Zwischenspeicher zugeordnet");
                Transact.fixInfo(t.exec("update zwischenspeicher set Zone="+t.getZone()+
                                        " where pro_aic_protokoll is null and Zone is null and kennung='Import'")+
                                 " Zonen in Zwischenspeicher zugeordnet");*/

                //int iAnz=t.exec("update benutzer set bits=bits+8 where bitand(bits,3)=0 and bitand(bits,8)=0");
                //t.fixInfo(iAnz+" Benutzer auf Mehrfach gestellt");
                /*t.exec("delete from fixeigenschaft where aic_spalte in (7531,8319,8320,9549)");
                SQL Qry=new SQL(t);
                for (int i=0;i<4;i++)
                {
                  for (int i2=0;i2<3;i2++)
                  {
                    Qry.add("aic_spalte", i == 0 ? 8319 : i == 1 ? 8320 : i == 2 ? 7531 : 9549);
                    Qry.add("aic_eigenschaft", 1003);
                    Qry.insert("Fixeigenschaft", false);
                  }
                  if (i<3)
                  {
                    Qry.add("aic_spalte", i == 0 ? 8319 : i == 1 ? 8320 : i == 2 ? 7531 : 9549);
                    Qry.add("aic_eigenschaft", i==0 ? 1593:i == 1 ? 1592:1003);
                    Qry.insert("Fixeigenschaft", false);
                  }
                }
                Qry.free();
                t.fixInfo("Abfrage RK_alle MA erweitert");
*/
                //Transact.fixInfo(t.exec("update zwischenspeicher set aic_mandant=10 where pro_aic_protokoll is null and aic_mandant is null and kennung='Import' and Terminal in ('10.115.0.180:3001','10.115.0.181:3001')")+" Mandanten in Zwischenspeicher zugeordnet");
                /*int iAnz=SQL.getInteger(t,"select count(*) from zwischenspeicher where aic_protokoll=710528 and aic_zwischenspeicher<5055025 and zone=60");
                if (iAnz>0)
                {
                  int i=t.exec("update zwischenspeicher set zone=0 where aic_protokoll=710528 and aic_zwischenspeicher<5055025");
                  t.fixInfo(i+" zones changed");
                  Tabellenspeicher Tab=new Tabellenspeicher(t,"select aic_zwischenspeicher,gueltig from zwischenspeicher where aic_protokoll=710528 and aic_zwischenspeicher<5056392 and aic_zwischenspeicher>5055024",true);
                  i=0;
                  SQL Qry=new SQL(t);
                  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    Qry.add("gueltig",new java.sql.Timestamp(Tab.getTimestamp("gueltig").getTime()+3600000));
                    if (Qry.update("zwischenspeicher",Tab.getI("aic_zwischenspeicher")))
                      i++;
                  }
                  t.fixInfo(i+" times changed");
                  i=t.exec("update zwischenspeicher set pro_aic_protokoll=null where terminal in ('10.115.0.180.3001','10.115.0.181:3001') and kennung='Import' and gueltig>'2008/28/03' and gueltig<'2008/04/04'");
                  t.fixInfo(i+" times undeleted");
                }
                else
                  t.fixInfo("Daten bereits konvertiert");*/
                /*Tabellenspeicher Tab = new Tabellenspeicher(t, "select * from stamm_protokoll where bezeichnung like ' %'",true);
                for (Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                  t.exec("update stamm_protokoll set bezeichnung='"+Tab.getS("Bezeichnung").substring(1)+"' where aic_stamm="+Tab.getI("aic_stamm")+
                         " and aic_protokoll="+Tab.getI("aic_protokoll"));
                t.fixInfo(Tab.getAnzahlElemente(null)+" Leerzeichnen vor Bezeichnung entfernt");
                Tab = new Tabellenspeicher(t, "select * from stamm_protokoll where bezeichnung like '% '",true);
                for (Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                {
                  String s=Tab.getS("Bezeichnung");
                  t.exec("update stamm_protokoll set bezeichnung='" + s.substring(0,s.length()-1) + "' where aic_stamm=" +
                         Tab.getI("aic_stamm") + " and aic_protokoll=" + Tab.getI("aic_protokoll"));
                }
                t.fixInfo(Tab.getAnzahlElemente(null)+" Leerzeichnen nach Bezeichnung entfernt");*/
                /*int iEig1=SQL.getInteger(t,"select aic_eigenschaft from eigenschaft where kennung='Erledigungstermin'");
                int iEig2=SQL.getInteger(t,"select aic_eigenschaft from eigenschaft where kennung='Erledigungsdatum'");
                t.fixInfo(iEig1+"->"+iEig2);
                if (iEig1>0 && iEig2>0)
                {
                  Tabellenspeicher Tab = new Tabellenspeicher(t, "select * from stammpool where aic_stamm is null and aic_eigenschaft=" + iEig1, true);
                  for (Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                  {
                    t.exec("insert into bew_von_bis (aic_bew_pool,aic_eigenschaft,von,dauer) values ("+Tab.getI("aic_bew_pool")+","+iEig2+","+t.DateTimeToString(Tab.getDate("gultig_von"))+",0)");
                    t.exec("delete from stammpool where aic_stammpool="+Tab.getI("aic_stammpool"));
                  }
                  t.fixInfo(Tab.getAnzahlElemente(null)+" Datums umkopiert");
                }*/
                //t.exec("ALTER TABLE BEW_BOOLEAN ALTER COLUMN SPALTE_BOOLEAN bit NULL");
                //t.fixInfo("Bew_Boolean richtiggestellt");
                //t.exec("update stamm set aic_mandant=8 where aic_stamm=6346");
                //t.fixInfo(t.exec("update stamm set aic_mandant=9 where aic_stammtyp=99 and aic_mandant=8")+" Zeitarten auf AG-Mandant");
                //t.fixInfo(t.exec("update benutzergruppe set aic_mandant=9 where aic_mandant=8")+" Benutzergruppen auf AG-Mandant");

                //t.fixInfo(t.exec("update bew_pool set pro_aic_protokoll=17 where aic_bew_pool=1544619 and pro_aic_protokoll is null")+" bew_pool entfernt");

                /*int iBew=SQL.getInteger(t,"select aic_bewegungstyp from bewegungstyp where kennung='LV_Travel time'");
                int iAnz=t.exec("update bew_pool set gueltig=null,ldate=0 where aic_bewegungstyp="+iBew+" and gueltig is not null");
                if (iAnz>0)
                  t.fixInfo(iAnz+" Reisezeiten richtiggestellt");
                if (t.exec("update stamm set aic_mandant=1 where aic_stammtyp=6 and kennung='Euro' and aic_mandant>1")>0)
                  t.fixInfo("Euro-Mandant richtiggestellt");
                if (t.exec("update stamm set aic_mandant=1 where aic_stammtyp=29 and kennung='y' and aic_mandant>1")>0)
                  t.fixInfo("Jahr-Mandant richtiggestellt");*/

                /*int iANR=SQL.getInteger(t,"select aic_stammtyp from stammtyp where kennung='Arbeitnehmer'");
                t.fixInfo("SttANR="+iANR);
                t.fixInfo(t.exec("update stamm_protokoll set pro_aic_protokoll=17 where pro_aic_protokoll is null"+
                  " and (select count(*) from stammview2 where aic_stamm=stamm_protokoll.aic_stamm)=1"+
                  " and aic_stamm in (select aic_stamm from stamm where aic_stammtyp="+iANR+")")+" Personen entfernt");
 if (!t.Oracle())
                t.fixInfo(t.exec("update stamm_protokoll set eintritt=null where aic_rolle is null and pro_aic_protokoll is null"+
                  " and aic_stamm in (select aic_stamm from stamm where aic_stammtyp="+iANR+") and eintritt>'1995/01/01'")+" Geburtsdatums entfernt");
                t.fixInfo(t.exec("update bew_pool set zone=120 where zone=0")+" Zonen richtiggestellt");*/
                /*Tabellenspeicher Tab=new Tabellenspeicher(t,"select * from daten_image where filename like 'file:%'",true);
                for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                {
                  String s=Tab.getS("Filename");
                  int iPos=s.lastIndexOf("\\");
                  s=s.substring(iPos+1);
                  //t.fixInfo("->"+s+"<-");
                  t.exec("update daten_image set filename='"+s+"' where aic_daten="+Tab.getI("aic_daten"));
                }
                t.fixInfo(""+Tab.getAnzahlElemente(null)+" Bild-Pfade entfernt");*/
                //t.fixInfo(t.exec("update bew_pool set pro_aic_protokoll=17 where aic_bewegungstyp=7 and gueltig is null and pro_aic_protokoll is null")+" Planungen gel�scht");
                /*Date dt=new Date(0);
                t.fixInfo(t.exec("update stamm_protokoll set eintritt="+SQL.DateTimeToString(dt,t)+
                                 " where aic_stamm in (select aic_stamm from stamm where aic_stammtyp=4) and eintritt is null")+"x stamm_protokoll ge�ndert");
                t.fixInfo(t.exec("update stammpool set gultig_von="+SQL.DateTimeToString(dt,t)+
                                 " where aic_stamm in (select aic_stamm from stamm where aic_stammtyp=4) and gultig_von is null")+"x stammpool ge�ndert");
                 */
                /*String s1=args[1];
                String s2=s1.substring(18);
                String s3=s2.substring(0,8)+args[2];
                //t.fixInfo("'"+s1+"%'");
                if (SQL.exists(t,"select aic_zwischenspeicher from zwischenspeicher where zwischentext like '"+s1+"%'"))
                {
                  Tabellenspeicher Tab = new Tabellenspeicher(t,"select aic_zwischenspeicher,zwischentext,gueltig from zwischenspeicher where zwischentext like '%"+s2+"%'",true);
                  //Tab.showGrid();
                  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  {
                    String s=Tab.getM("zwischentext");
                    s=s.replaceFirst(s2,s3);
                    DateWOD DW=new DateWOD(Tab.getTimestamp("gueltig"));
                    DW.add(DateWOD.STUNDE);
                    t.exec("update zwischenspeicher set zwischentext='"+s+"',gueltig="+SQL.DateTimeToString(DW.toTimestamp(),t)+" where aic_zwischenspeicher="+Tab.getI("aic_zwischenspeicher"));
                  }
                  t.fixInfo(s2+" -> "+s3+": "+Tab.getAnzahlElemente(null)+" ersetzt");
                }
                else
                  t.fixInfo("Daten bereits ge�ndert");*/
                /*int i=t.exec("update stamm_protokoll join stamm set bezeichnung=null where aic_stammtyp=203");
                if (i>0)
                {
                  t.fixInfo(i+" Qualifikationen gel�scht!");
                  t.fixInfo("Stamm-Reinigung muss aufgerufen werden !!!");
                }*/
                /*if (SQL.exists(t,"select * from zwischenspeicher where zwischentext like '"+args[2]+"%'"))
                  t.fixInfo("Daten bereits importiert");
                else
                {
                  Tabellenspeicher Tab = new Tabellenspeicher(t, new String[] {"xx", "D1", "D2"});
                  Tab.readFile('\t', args[1]);
                  int i=0;
                  //Tab.showGrid();
                  for(Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                  {
                    String s = Tab.getS("D2") + "\r";
                    if (t.Oracle())
                    {
                      int iAic = SQL.getInteger(t, "Select aic_Zwischenspeicher.nextval from dual");
                      Tab.moveNext();
                      s += Tab.getS("D1") + iAic + ")";
                    }
                    else
                    {
                      Tab.moveNext();
                      s += Tab.getS("D1");
                    }
                    SQL.exec(t, s);
                    i++;
                  }
                  t.fixInfo(i+" Daten importiert");
                }*/

      /*int iAbfrage=SQL.getInteger(t,"select aic_begriffgruppe from begriffgruppe where kennung='Abfrage'");
      int iStt=SQL.getInteger(t,"select aic_stammtyp from stammtyp where kennung='Arbeitnehmer'");
      int iRolle=SQL.getInteger(t,"select aic_Rolle from Rolle where kennung='Mitarbeiter'");
      if (iRolle>0)
      {
        int i = t.exec("update begriff set aic_rolle="+iRolle+" where aic_stammtyp="+iStt+" and aic_rolle is null and aic_begriffgruppe=" + iAbfrage + " and" + SQL.bit("bits", 0x200000000000000l  cstBerechtigung ));
        if (i>0)
          t.fixInfo(i+" Abfrage auf Rolle Mitarbeiter ge�ndert");
      }
      else
        t.fixInfo("Rolle Mitarbeiter nicht gefunden");*/

      /*int i=t.exec("update bew_pool p join protokoll p2 on p.aic_protokoll=p2.aic_protokoll set p.pro_aic_protokoll=17"+
                   " where p.aic_bewegungstyp=4 and p.aic_bew_pool>625131 and p2.aic_code=203 and p.pro_aic_protokoll is null");*/
      /*int i=t.exec("update stammpool p set  pro2_aic_protokoll=null where pro2_aic_protokoll=703291 and (select count(*) from stammpool"+
                    " where p.aic_stamm=aic_stamm and p.aic_eigenschaft=aic_eigenschaft and pro2_aic_protokoll is null)=0");
      if (i>0)
        t.fixInfo(i+" L�schungen1 r�ckgesetzt");
      i=t.exec("update stammpool p set pro_aic_protokoll=null,gueltig_bis=null where pro_aic_protokoll=672089 and (select count(*) from stammpool"+
               " where p.aic_stamm=aic_stamm and p.aic_eigenschaft=aic_eigenschaft)=1");
      if (i>0)
        t.fixInfo(i+" L�schungen2 r�ckgesetzt");*/


      /*
                int iSprache=SQL.getInteger(t,"select aic_sprache from sprache where kennung='Klingonisch'");
                t.exec("delete from bezeichnung where aic_sprache="+iSprache);
                t.exec("delete from daten_memo where aic_sprache="+iSprache);
                t.exec("delete from daten_bild where aic_sprache="+iSprache);
                int i=t.exec("delete from sprache where aic_sprache="+iSprache);
                if (i>0)
                  t.fixInfo("Klingonisch gel�scht");
       */
                /*int i=t.exec("update zwischenspeicher set pro_aic_protokoll=null where kennung='Import' and zwischentext like '%00000060200606%'");
                if (i>0)
                  t.fixInfo(i+" Stemplungen mit Kartennumer 000060 r�ckgesetzt!");*/
                /*int i=t.exec("update stammpool set pro2_aic_protokoll=null where pro2_aic_protokoll=1359336");
                if (i>0)
                  t.fixInfo(i+" Stamm-L�schungen r�ckgesetzt!");
                i=t.exec("update bew_pool set pro_aic_protokoll=null where pro_aic_protokoll=1359336");
                if (i>0)
                  t.fixInfo(i+" Bew-L�schungen r�ckgesetzt!");*/

                /*Tabellenspeicher Tab=new Tabellenspeicher(t,"select von,s.aic_stamm,count(*) Anzahl,min(v.aic_bew_pool) d,list(v.aic_bew_pool) l from bew_von_bis v join bew_pool p join bew_stamm join stammview2 s"+
                  " where aic_bewegungstyp=4 and p.pro_aic_protokoll is null and p.aic_protokoll in (1267413,1267774,1267099) and v.aic_eigenschaft=585 and s.aic_stammtyp=163"+
                  " group by von,s.aic_stamm having Anzahl>1",true);
                int iGes=Tab.getAnzahlElemente(null);
                for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                {
                  SQL.exec(t, "update bew_pool set pro_aic_protokoll=17 where aic_bew_pool<>" + Tab.getI("d") + " and aic_bew_pool in(" + Tab.getM("l") + ")");
                }
                t.fixInfo(iGes+" Stempelungen gel�scht!");*/

                // f�r Wyeth
                /*int iAnz=t.exec("update bew_pool set pro_aic_protokoll=null where pro_aic_protokoll in (392423,392393)");
                if (iAnz>0)
                  t.fixInfo(iAnz+" Bew-Pool L�schungen r�ckgesetzt!");*/

                //t.fixInfo(t.exec("update stammpool join eigenschaft join begriff set spalte_double=10 where begriff.kennung='StringSehrKurz' and (spalte_double is null or spalte_double<>10)")+" StringSehrKurz ge�ndert!");
                //t.fixInfo(t.exec("update stammpool join eigenschaft join begriff set spalte_double=30 where begriff.kennung='StringKurz' and (spalte_double is null or spalte_double<>30)")+" StringKurz ge�ndert!");
                //t.fixInfo(t.exec("update stammpool join eigenschaft join begriff set spalte_double=60 where begriff.kennung='String60' and (spalte_double is null or spalte_double<>60)")+" String60 ge�ndert!");
                //t.fixInfo(t.exec("update stammpool join eigenschaft join begriff set spalte_double=255 where begriff.kennung='StringLang' and (spalte_double is null or spalte_double<>255)")+" StringLang ge�ndert!");

                //t.fixInfo("Sicherung auf "+args[1]+"wird durchgef�hrt");
                //t.exec("BACKUP DATABASE DIRECTORY '"+args[1]+"'");

                /*int iAnz1=t.exec("update protokoll set aic_code=197 where aic_code=461");
                int iAnz2=t.exec("delete from code where aic_code=461");
                if (iAnz1>0 || iAnz2>0)
                  t.fixInfo(iAnz1+" Protokolle ge�ndert,"+iAnz2+" Codes gel�scht!");*/
                /*int i=SQL.getInteger(t,"select count(*) from bew_boolean where spalte_boolean=0");
                int iMom=1;
                while (i>0 && iMom>0)
                {
                  t.fixInfo("Noch "+i+" Bew_boolean zu l�schen");
                  iMom=t.exec("delete top 10000 from bew_boolean where spalte_boolean=0");
                  i-=iMom;
                }*/
      /*SQL Qry=new SQL(t);
      int iStemp=Qry.getInteger("select aic_bewegungstyp from bewegungstyp where kennung='Stempelung'");
      int iPlan=Qry.getInteger("select aic_bewegungstyp from bewegungstyp where kennung='planning'");
      if (iStemp>0)
        t.fixInfo(t.exec("update bew_pool set gueltig=trunc(gueltig) where gueltig>trunc(gueltig) and aic_bewegungstyp="+iStemp)+" Stempelungen richtiggestellt");
      else
        t.fixInfo("Stempelung nicht gefunden");
      if (iPlan>0)
        t.fixInfo(t.exec("update bew_pool set gueltig=trunc(gueltig) where gueltig>trunc(gueltig) and aic_bewegungstyp="+iPlan)+" Planungen richtiggestellt");
      else
        t.fixInfo("Planung nicht gefunden");*/

      //t.fixInfo(t.exec("update bew_von_bis set dauer=0 where aic_bew_pool in (select aic_bew_pool from bew_pool where aic_bewegungstyp=18) and to_char(von, 'DAY') like 'S%' and dauer>0")+" Planungen ge�ndert");
                //t.fixInfo(t.exec("delete from begriff_zuordnung where aic_begriff=8393")+" begriff_zuordnung laut aic_begriff gel�scht");
                //t.fixInfo(t.exec("delete from begriff_zuordnung where beg_aic_begriff=8393")+" begriff_zuordnung laut beg_aic_begriff gel�scht");
                //t.fixInfo(t.exec("update bew_pool set pro_aic_protokoll=null where pro_aic_protokoll=296961")+" Datens�tze wiederhergestellt");
                //new Tabellenspeicher(t,"select aic_stamm,bezeichnung,eintritt,(select min(eintritt) from stamm_protokoll where aic_stamm=s.aic_stamm and pro_aic_protokoll is not null and eintritt<>s.eintritt) davor"+
                //                     " from stammview2 s where aic_stammtyp=163 and davor is not null",true).writeFile(';',"Eintritte.csv");
                /*Tabellenspeicher Tab=new Tabellenspeicher(t,"select v.bezeichnung,eigenschaft.kennung eig from stammview2 v join stammpool p on v.aic_stamm=p.aic_stamm"+
                                                         " join eigenschaft join begriff where begriff.kennung='Datum' and gueltig_bis is not null",true);
                if (Tab.getAnzahlElemente(null)>0)
                {
                  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                    t.fixInfo("Stammsatz "+Tab.getS("Bezeichnung")+":"+Tab.getS("Eig")+" l�schen");
                  t.fixInfo(t.exec("delete from stammpool from stammpool join eigenschaft join begriff where begriff.kennung='Datum' and gueltig_bis is not null")+" Datums mit Stichtag gel�scht");
                }*/
                /*Tabellenspeicher Tab=new Tabellenspeicher(t,"select table_name from systable where (table_name like '_Garant%' or table_name like '_All%') and table_name not like '%Temp' and view_def is not null",true);
                for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  t.exec("grant SELECT ON "+Tab.getS("table_name")+" TO _Garant");
                t.fixInfo(Tab.getAnzahlElemente(null)+" Garant-Views berechtigt");*/
                /*Vector Vec=SQL.getVector("select b.aic_begriff,(select count(*) from darstellung where aic_begriff=b.aic_begriff) Form"+
                                         ",(select count(*) from abschnitt where aic_begriff=b.aic_begriff) Druck"+
                                         ",(select count(*) from befehl join spalte where aic_abfrage=a.aic_abfrage) Modell"+
                                         ",(select count(*) from eigenschaft where aic_abfrage=a.aic_abfrage) Eig"+
                                         ",(select count(*) from spalte where filter=a.aic_abfrage) Filter"+
                                         ",(select count(*) from planung where aic_abfrage=a.aic_abfrage or abf_aic_abfrage=a.aic_abfrage) Planung"+
                                         " from abfrage a join begriff b where Form+Druck+Modell+Eig+Filter+Planung=0"+
                                         " and mod(bits,4194304)&0x2020=0 and (kennung is null or kennung not like 'Terminal%')",t);
                t.fixInfo(Vec.size()+" Abfragen zum l�schen gefunden!");
                t.fixInfo(t.exec("delete from SPALTE_BERECHNUNG from SPALTE_BERECHNUNG z join spalte on z.aic_spalte=spalte.aic_spalte join abfrage where abfrage.aic_begriff"+Static.SQL_in(Vec))+"x SPALTE_BERECHNUNG geloescht");
                t.fixInfo(t.exec("delete from spalte_zuordnung from spalte_zuordnung join spalte join abfrage where abfrage.aic_begriff"+Static.SQL_in(Vec))+"x spalte_zuordnung geloescht");
                t.fixInfo(t.exec("delete from fixeigenschaft from fixeigenschaft join spalte join abfrage where abfrage.aic_begriff"+Static.SQL_in(Vec))+"x fixeigenschaft1 geloescht");
                t.fixInfo(t.exec("delete from fixeigenschaft from fixeigenschaft join bedingung join abfrage where abfrage.aic_begriff"+Static.SQL_in(Vec))+"x fixeigenschaft2 geloescht");
                t.fixInfo(t.exec("delete from spalte from spalte join abfrage where abfrage.aic_begriff"+Static.SQL_in(Vec))+"x spalte geloescht");
                t.fixInfo(t.exec("delete from bedingung from bedingung join abfrage where abfrage.aic_begriff"+Static.SQL_in(Vec))+"x bedingung geloescht");
                t.fixInfo(t.exec("delete from abfrage where aic_begriff"+Static.SQL_in(Vec))+"x abfrage geloescht");
                t.fixInfo(t.exec("delete from begriff where aic_begriff"+Static.SQL_in(Vec))+"x begriff geloescht");*/
                //t.fixInfo(t.exec("update bew_pool p2 join protokoll p on p2.pro_aic_protokoll=p.aic_protokoll join code set pro_aic_protokoll=null where aic_bewegungstyp=29 and code.kennung='Modell'")+" S�tze wieder hergestellt");
                //t.exec("update logging set aus=mom where aus is null and seconds(mom,now())>300");
                /*int i=SQL.getInteger(t,"select count(*) from logging where aus is null");
                if (i>0)
                        t.fixInfo("Es sind noch "+i+" Leute eingeloggt!");
                else
                {
                  t.exec("alter table BEW_POOL drop foreign key FK_BEW_POOL_BEWEGUNG__BEWEGUNG");
                  t.exec("alter table BEW_STAMM drop foreign key FK_BEW_STAM_POOL_DER__BEW_POOL");
                  t.exec("alter table BEW_POOL add foreign key FK_BEW_POOL_BEWEGUNG__BEWEGUNG (AIC_BEWEGUNGSTYP)references BEWEGUNGSTYP (AIC_BEWEGUNGSTYP) on update restrict on delete restrict");
                  t.exec("alter table BEW_STAMM add foreign key FK_BEW_STAM_POOL_DER__BEW_POOL (AIC_BEW_POOL)references BEW_POOL (AIC_BEW_POOL) on update restrict on delete restrict");
                  t.fixInfo("Index FK_BEW_POOL_BEWEGUNG__BEWEGUNG und FK_BEW_STAM_POOL_DER__BEW_POOL erneuert!");
                }*/
                /*
                Tabellenspeicher Tab= new Tabellenspeicher(t,"select table_name,view_def from systable where creator=1 and table_type='VIEW' and table_name not in ('stringXview','stringXview2','poolview2','poolview','bewview3','bewview2','bewview','stammview','stammview2') and table_name like '%Temp' order by table_id",true);
                Tabellenspeicher Tab2= new Tabellenspeicher(t,"select table_name,view_def from systable where creator=1 and table_type='VIEW' and table_name not in ('stringXview','stringXview2','poolview2','poolview','bewview3','bewview2','bewview','stammview','stammview2') and table_name not like '%Temp' order by table_id",true);
                for (Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
                  t.exec("drop view "+Tab2.getS("table_name"));
                for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  t.exec("drop view "+Tab.getS("table_name"));
                t.exec("drop view stringXview2");
                t.exec("drop view stringXview");
                t.exec("drop view poolview2");
                t.exec("drop view poolview");
                t.exec("drop view bewview3");
                t.exec("drop view bewview2");
                t.exec("drop view bewview");
                t.exec("drop view stammview");
                t.exec("drop view stammview2");
                t.exec("create view stammview2 as select stamm.aic_stamm,kennung,bezeichnung,aic_stammtyp,aic_mandant,eintritt,austritt from stamm join stamm_protokoll where pro_aic_protokoll is null");
                t.exec("create view stammview as select * from stammview2 where (von is null or austritt is null or austritt >= von) and (bis is null or eintritt is null or eintritt < bis)");
                t.exec("create view bewview as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant from bew_pool where pro_aic_protokoll is null and(gueltig is null or(von is null or von <= gueltig) and(bis is null or bis > gueltig))");
                t.exec("create view bewview2 as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant from bew_pool where pro_aic_protokoll is null");
                t.exec("create view bewview3 as select aic_Bew_pool,aic_bewegungstyp,gueltig,aic_mandant from bew_pool"+
                                 " where (gueltig is null or (von is null or von <= gueltig) and (bis is null or bis > gueltig))");
                t.exec("create view poolview as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von from stammpool where pro2_aic_protokoll is null and (bis is null or gultig_von is null or gultig_von < bis) and (gueltig_bis is null or gueltig_bis >= bis)");
                t.exec("create view poolview2 as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von from stammpool where pro2_aic_protokoll is null");
                t.exec("create view stringXview as select aic_stamm,aic_eigenschaft,aic_bew_pool,spalte_double,gultig_von,"+
                        "(case when spalte_double < 11 then(select spalte_stringsehrkurz from daten_StringSehrKurz where aic_daten = stammpool.aic_daten)"+
                                 " when spalte_double < 31 then(select spalte_stringkurz from daten_StringKurz where aic_daten = stammpool.aic_daten)"+
                        " when spalte_double < 61 then(select spalte_string60 from daten_String60 where aic_daten = stammpool.aic_daten)"+
                        " when spalte_double < 256 then(select spalte_stringLang from daten_StringLang where aic_daten = stammpool.aic_daten)"+
                        " else(select spalte_text from DBA.daten_text where aic_daten = stammpool.aic_daten) end) as StringX"+
                        " from stammpool join daten where spalte_double > 0 and pro2_aic_protokoll is null and(bis is null or gultig_von is null or gultig_von < bis) and(gueltig_bis is null or gueltig_bis >= bis)");
                 t.exec("create view stringXview2 as select aic_stamm,aic_eigenschaft,aic_bew_pool,spalte_double,gultig_von,(case"+
                                 " when spalte_double<11 then (select spalte_stringsehrkurz from daten_StringSehrKurz where aic_daten=stammpool.aic_daten)"+
                                 " when spalte_double<31 then (select spalte_stringkurz from daten_StringKurz where aic_daten=stammpool.aic_daten)"+
                                 " when spalte_double<61 then (select spalte_string60 from daten_String60 where aic_daten=stammpool.aic_daten)"+
                                 " when spalte_double<256 then (select spalte_stringLang from daten_StringLang where aic_daten=stammpool.aic_daten)"+
                                 " else (select spalte_text from daten_text where aic_daten=stammpool.aic_daten) end) StringX from stammpool join daten where spalte_double>0"+
                                 " and pro2_aic_protokoll is null and gueltig_bis is null");
                int i=9+Tab.getAnzahlElemente(null)+Tab2.getAnzahlElemente(null);
                for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  t.exec(Tab.getM("view_def"));
                for (Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
                  t.exec(Tab2.getM("view_def"));
                t.fixInfo(i+" Views erneuert");
                */

		//SQL Qry=new SQL(t);
		//int i=Qry.getInteger("select count(*) from bew_stamm where aic_stamm=8087");
		//Qry.free();
                //t.fixInfo(t.exec("delete from bew_von_bis from bew_von_bis b join bew_pool join bew_stamm where aic_stamm=6478 and aic_bewegungstyp=4 and b.aic_eigenschaft=582 and pro_aic_protokoll is null")+" SFG-Stempelungen zur�ckgesetzt");
                //t.fixInfo(t.exec("delete from bew_stamm from bew_pool join bew_stamm where aic_stamm=6478 and aic_bewegungstyp=4 and pro_aic_protokoll is null")+" SFG-Stempelung-Zuordnungen entfernt");
                //t.fixInfo(t.exec("delete from bezeichnung from begriff,bezeichnung where aic_sprache=1 and aic_tabellenname=6 and aic_fremd=aic_begriff and bezeichnung=defbezeichnung")+" Bezeichnungen gel�scht");

                //t.fixInfo(t.exec("delete from stamm_protokoll from stamm_protokoll join stamm join stammtyp where stammtyp.kennung='Ort'")+" Orte aus stamm_protokoll gel�scht!");
                //t.fixInfo(t.exec("delete from bew_stamm from bew_stamm join stamm join stammtyp where stammtyp.kennung='Ort'")+" Orte aus bew_stamm gel�scht!");
                //t.fixInfo(t.exec("delete from stammpool from stammpool join stamm on stammpool.aic_stamm=stamm.aic_stamm join stammtyp where stammtyp.kennung='Ort'")+" Orte aus stammpool gel�scht!");
                //t.fixInfo(t.exec("delete from stammpool from stammpool join stamm on stammpool.sta_aic_stamm=stamm.aic_stamm join stammtyp where stammtyp.kennung='Ort'")+" Orte aus stammpool2 gel�scht!");
                //t.fixInfo(t.exec("delete from stamm from stamm join stammtyp where stammtyp.kennung='Ort'")+" Orte gel�scht!");
		/*
                t.fixInfo(t.exec("update stammpool join eigenschaft join begriff set spalte_double=10 where begriff.kennung='StringSehrKurz' and (spalte_double is null or spalte_double<>10)")+" StringSehrKurz ge�ndert!");
		t.fixInfo(t.exec("update stammpool join eigenschaft join begriff set spalte_double=30 where begriff.kennung='StringKurz' and (spalte_double is null or spalte_double<>30)")+" StringKurz ge�ndert!");
                t.fixInfo(t.exec("update stammpool join eigenschaft join begriff set spalte_double=60 where begriff.kennung='String60' and (spalte_double is null or spalte_double<>60)")+" String60 ge�ndert!");
                t.fixInfo(t.exec("update stammpool join eigenschaft join begriff set spalte_double=255 where begriff.kennung='StringLang' and (spalte_double is null or spalte_double<>255)")+" StringLang ge�ndert!");
                */
                //t.fixInfo(t.exec("delete from stammpool where aic_stamm=8888 and aic_protokoll=36939")+" Pool-Daten gel�scht!");
		//t.fixInfo(t.exec("delete from bew_von_bis from bew_von_bis join bew_pool join bewegungstyp where von=bis and kennung='planning'")+" leere Bew_Von_Bis gel�scht");

		/*SQL Qry=new SQL(t);
		t.exec("update logging set aus=mom where aus is null and seconds(mom,now())>300");
		int i=Qry.getInteger("select count(*) from logging where aus is null");
		if (i>0)
			t.fixInfo("Es sind noch "+i+" Leute eingeloggt!");
		else
		{
			t.exec("alter table BEW_STAMM drop PRIMARY KEY");
			t.exec("alter table BEW_STAMM add PRIMARY  KEY (AIC_BEW_POOL,AIC_EIGENSCHAFT)");
			t.fixInfo("BEW_STAMM reindiziert!");
			t.exec("alter table BEW_VON_BIS drop PRIMARY KEY");
			t.exec("alter table BEW_VON_BIS add PRIMARY  KEY (AIC_BEW_POOL,AIC_EIGENSCHAFT)");
			t.fixInfo("BEW_VON_BIS reindiziert!");
		}
		Qry.free();*/

		/*SQL Qry=new SQL(t);
		int i=Qry.getInteger("select count(*) from bew_pool where pro_aic_protokoll=84124 and gueltig is not null");
		if (i>0)
		{
			t.fixInfo("Aktiviere "+i+" Datens�tze !");
			Qry.exec("update bew_pool set pro_aic_protokoll=null where pro_aic_protokoll=84124 and gueltig is not null");
		}*/
		/*int i=Qry.getInteger("select count(*) from stamm where aic_stammtyp=162 and aic_mandant=1");
		if (i>0)
		{
			t.fixInfo("Aktiviere "+i+" Kostenstellen !");
			Qry.exec("update stamm set aic_mandant=19 where aic_stammtyp=162 and aic_mandant=1");
		}
		int i=Qry.getInteger("select count(*) from stamm where aic_stammtyp=163 and aic_mandant=1");
		if (i>0)
		{
			t.fixInfo("Aktiviere "+i+" Arbeitnehmer !");
			Qry.exec("update stamm set aic_mandant=23 where aic_stammtyp=163 and aic_mandant=1");
		}
		Qry.free();
		*/
		/*int i=Qry.getInteger("select count(*) from bew_pool where aic_bew_pool=433078");
		if (i>0)
		{
			t.fixInfo("l�sche "+i+" Timers�tze !");
			Qry.exec("update bew_pool set pro_aic_protokoll=17 where aic_bew_pool=433078");
		}
		Qry.free();*/


		/*SQL Qry=new SQL(t);
		Qry.exec("update logging set aus=mom where aus is null and seconds(mom,now())>300");
		int i=Qry.getInteger("select count(*) from logging where aus is null");
		if (i>0)
			t.fixInfo("Es sind noch "+i+" Leute eingeloggt!");
		else
		{
			Qry.exec("alter table BEFEHL drop foreign key FK_BEFEHL_MODELL_DES_BEFEHLS");
			Qry.exec("alter table BEFEHL add foreign key FK_BEFEHL_MODELL_DES_BEFEHLS (AIC_MODELL) references MODELL (AIC_MODELL) on update restrict on delete restrict");
			t.fixInfo("Index korrigiert");
		}*/

		/*
		SQL Qry=new SQL(t);
		Qry.open("select aic_spalte,count(*) Anzahl,max(aic_fixeigenschaft) falsch from fixeigenschaft join eigenschaft join begriff"+
 			" where aic_spalte is not null and begriff.kennung='bewVon_bis' group by aic_spalte having Anzahl>1");
		int i=0;
		for(Qry.moveFirst();!Qry.eof();Qry.moveNext())
		{
			i++;
			SQL.exec(t,"delete from fixeigenschaft where aic_fixeigenschaft="+Qry.getI("falsch"));
		}
		t.fixInfo(i+" falsche Elemente gel�scht!");
		*/


		// Kartennummern ermitteln
		/*Save sav=new Save();
		SQL Qry=new SQL(t);
		Qry.open("select aic_stamm,bezeichnung,(select Spalte_StringKurz from daten_stringkurz join daten join stammpool where aic_eigenschaft=210 and aic_stamm=v.aic_stamm) Karte"+
 			" from stammview v where aic_stammtyp=163 order by Karte");
		for(Qry.moveFirst();!Qry.eof();Qry.moveNext())
			sav.add(Qry.getS("aic_Stamm")+";"+Qry.getS("Bezeichnung")+";"+Qry.getS("Karte"));
		sav.save(new java.io.File("Karten"),false);
		Qry.open("select aic_zwischenspeicher,kennung,zwischentext from zwischenspeicher order by aic_zwischenspeicher");
		for(Qry.moveFirst();!Qry.eof();Qry.moveNext())
		{
			String s=Qry.getS("zwischentext");
			sav.add(s.substring(0,s.length()-1)+";"+Qry.getS("kennung")+";"+Qry.getI("aic_zwischenspeicher"));
		}
		sav.save(new java.io.File("Zwischen"),false);*/


		//t.connect("192.1.1.245:2641");

		//SQL.exec(t,"delete from feiertag_zuordnung where aic_land=1 and aic_feiertag in (10,13)");

		// Doppelte Auftr�ge bei Renner l�schen

		/*SQL.exec(t,"delete from stammpool from stammpool p,stammview s where aic_stammtyp=179 and bezeichnung not like '% %' and p.aic_stamm=s.aic_stamm");
		SQL.exec(t,"delete from stammpool from stammpool p,stammview s where aic_stammtyp=179 and bezeichnung like '%;%' and p.aic_stamm=s.aic_stamm");
		t.fixInfo(SQL.getInteger(t,"select count(*) from stamm_protokoll join stamm where aic_stammtyp=179 and (bezeichnung not like '% %' or bezeichnung like '%;%')")+" * stamm_protokoll gel�scht!");
		SQL.exec(t,"delete from stamm_protokoll from stamm_protokoll join stamm where aic_stammtyp=179 and bezeichnung not like '% %'");
		SQL.exec(t,"delete from stamm_protokoll from stamm_protokoll join stamm where aic_stammtyp=179 and bezeichnung like '%;%'");
		SQL.exec(t,"delete from stammpool from stammpool,stamm left outer join stamm_protokoll p where p.aic_stamm is null and aic_stammtyp=179 and stammpool.aic_stamm=stamm.aic_stamm");
		t.fixInfo(SQL.getInteger(t,"select count(*) from bew_stamm where aic_stamm in (11530,11486,11329,11217,10262)")+" * BewStamm ge�ndert!");
		SQL.exec(t,"update bew_stamm set aic_stamm=9282 where aic_stamm=10262");
		SQL.exec(t,"update bew_stamm set aic_stamm=9186 where aic_stamm in (11530,11486,11329,11217)");
		t.fixInfo(SQL.getInteger(t,"select count(*) from stamm left outer join stamm_protokoll p where p.aic_stamm is null and aic_stammtyp=179")+" * Stamm gel�scht!");
		SQL.exec(t,"delete from stamm from stamm left outer join stamm_protokoll p where p.aic_stamm is null and aic_stammtyp=179");
		*/

		//t.unConnect(); // !!!
                //t.disconnect();
		//Transact.fixInfo("fertig");
		//Static.sleep(30000);
		/*int iAbfrage=0;
		int iMax=0;
		Tabellenspeicher Tab=new Tabellenspeicher(t,"select aic_spalte,aic_abfrage,nummer from spalte s where aic_abfrage<864 and (select max(aic_spalte) from spalte where aic_abfrage=s.aic_abfrage)>3750 order by aic_abfrage,aic_spalte",true);
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			if (Tab.getI("Aic_abfrage") != iAbfrage)
			{
				iMax=0;
				iAbfrage=Tab.getI("Aic_abfrage");
			}
			int iSpalte=Tab.getI("aic_spalte");
			if (iSpalte<3751)
				iMax=Math.max(iMax,Tab.getI("Nummer"));
			else
			{
				iMax++;
				t.fixInfo(iSpalte+":"+Tab.getI("Nummer")+"->"+iMax);
				SQL.exec(t,"update spalte set nummer="+iMax+" where aic_spalte="+iSpalte);
				SQL.exec(t,"update abfrage set spalten="+iMax+" where aic_abfrage="+iAbfrage);
			}

		}*/

		//SQL.exec(t,"update Begriff set Jeder=1 where kennung='DefImport'");
		//SQL.exec(t,"update Begriff set Jeder=1 where kennung='LizImport'");

		/*if(SQL.exists(t,"select kennung from feiertag where kennung='WS'"))
			t.fixInfo("Feiertag WS existiert bereits");
		else
		{
			SQL Qry=new SQL(t);
			Qry.add("Kennung","WS");
			Qry.add("Tag",24);
			Qry.add("AIC_CODE",136);
			Qry.add("AIC_FEIERTAG",Qry.insert("Feiertag"));
			Qry.add("AIC_LAND",1);
			Qry.insert("Feiertag_Zuordnung");
			t.fixInfo("fertig");
		}*/
		System.exit(0);
    }
    // add your data members here
}

