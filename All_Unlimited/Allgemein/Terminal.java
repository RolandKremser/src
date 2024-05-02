/*
    All_Unlimited-Allgemein-Terminal.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import java.util.Vector;
import All_Unlimited.Hauptmaske.AClient;

public class Terminal extends Thread
{
public Terminal(String activeThread, Global g,boolean bUL)
{
	super(activeThread);
        if (bUL)
          iLZmom=1;
	if(glob==null)
		glob=g;
}

public void run()
{
  //glob.testInfo("run:   "+Static.get_ms());
	if(Connect(getName(),glob))
	{
		Parameter Para = new Parameter(glob,"Terminal");
		Para.getMParameterH("Werte");
		int iAnzKST=Para.bGefunden?Para.int2:6;
		int iAnzTaetig=Para.bGefunden?Para.int3:3;
		int iAnzAuftrag=Para.bGefunden?Para.int4:15;
                int iInt1=Para.int1;
		boolean bNullen = Para.bGefunden&&(Para.int1&1)==1;
		boolean bDatei = Para.bGefunden&&(Para.int1&2)==2;
                int iKarte=Para.int1&TabTerminal.KARTE;
                boolean bHitag = Para.bGefunden && (iKarte==TabTerminal.HITAG);
                boolean bLegic = Para.bGefunden && (iKarte==TabTerminal.LEGIC);
		boolean bMiro  = Para.bGefunden && (iKarte==TabTerminal.MIRO);
                //boolean bRo=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.RO;
                //boolean bSymbol=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.SYMBOL;
                //boolean bMifare=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARE;
                boolean bMifare2=Para.bGefunden && (iKarte==TabTerminal.MIFARE2);
                //boolean bMifareD=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.MIFARED;
                boolean bHitagHex=Para.bGefunden&& (iKarte==TabTerminal.HITAGHEX);
                //boolean bNova=Para.bGefunden&&(Para.int1&TabTerminal.KARTE)==TabTerminal.NOVA;
                boolean bKarteNullen = Para.bGefunden?(Para.int1&TabTerminal.KARTE0)==0:true;
                //glob.testInfo("para:  "+Static.get_ms());
		//if(TabConnections.posInhalt("IP/Port",sIp_Port))
		//InputStream in = (InputStream)TabConnections.getInhalt("InputStream");
		//OutputStream out = (OutputStream)TabConnections.getInhalt("OutputStream");
		try
		{
			SQL Qry=new SQL(glob);
			byte abyte[] = new byte[200];
			for(int i=in.read(abyte);bThread&&i>0;i=in.read(abyte))
			{
                          i2++;
                          bAktiv=true;
                          lLast=Static.get_ms2();
                          //glob.testInfo(Static.FillNull(""+i2,6)+":"+Static.get_ms());
                          	String sSatz=new String(abyte, 0, i);
                                glob.testInfo(getName()+"/"+Static.FillNull(""+i2,6)+":"+sSatz.trim());
				if(sSatz.length()>3&&sSatz.indexOf("78\r")==0)
					sSatz=sSatz.substring(3);
				sSatz=sSatz.substring(0,sSatz.indexOf('\r'))+"\r";
				if(sSatz.length()>2 && !sSatz.substring(0,2).equals("78"))
					glob.fixInfo("Lesen: "+sSatz.trim());

				if(sSatz.length()>2 && sSatz.substring(0,2).equals("78"))
				{
					checkExit2(glob);
          if(Static.get_ms2()-lLogcheck>239500l)
					{
                                          //glob.testInfo("Log:   "+Static.get_ms());
            checkExit();
            int iLC=glob.Logcheck();
			if (iLC==0)
            {
              try
              {
                      fileRun.close();
              }
              catch(IOException ioe)
              {
                      System.err.println("Save.prot(): IOException - "+ioe);
              }
              if (!new File(sPath+"Terminal.run").delete())
                ProtSave("not deleted");
              ProtSave("exit bei Logcheck");
              System.exit(8);
            }
			if (iLC>1)
				lLogcheck=Static.get_ms2();
						DateWOD dtMom = new DateWOD(lLogcheck);
                                                glob.testInfo("dtMom="+dtMom);
						dtMom.setTimeZero();
						glob.setVon(dtMom.toTimestamp());
						dtMom.tomorrow();
						glob.setBis(dtMom.toTimestamp());
                                                //glob.testInfo("->Bis="+dtMom);
					}
                                        else
                                          glob.testInfo("Lebenszeichen: "+new DateWOD());
                                        if (iLZmom==0)
					{
                                          iLZmom++;
                                          java.sql.Timestamp TSStammUpload = SQL.getTimestamp(glob,
                                              "SELECT Gueltig FROM Zwischenspeicher WHERE Kennung='StammUpload' AND TERMINAL='" + getName() + "' AND Zwischentext='J****!**Y9**\r'");
                                          java.sql.Timestamp TSStammUpload2 = SQL.getTimestamp(glob,
                                              "SELECT Gueltig FROM Zwischenspeicher WHERE Kennung='StammUpload2' AND TERMINAL='" + getName() + "'");
                                          java.sql.Timestamp TSZeitZutritt = SQL.getTimestamp(glob,
                                              "SELECT Gueltig FROM Zwischenspeicher WHERE Kennung='ZeitZutritt' AND TERMINAL='" + getName() + "' AND Zwischentext='J****!**P0**\r'");
                                          java.sql.Timestamp TSFeiertag = SQL.getTimestamp(glob,
                                              "SELECT Gueltig FROM Zwischenspeicher WHERE Kennung='Feiertag' AND TERMINAL='" + getName() + "' AND Zwischentext='J****!**L00*\r'");
                                          if ((TSStammUpload != null && (TSGueltig == null || TSStammUpload.getTime() > TSGueltig.getTime())) ||
                                              (TSZeitZutritt != null && (TSGueltig == null || TSZeitZutritt.getTime() > TSGueltig.getTime())) ||
                                              (TSFeiertag != null && (TSGueltig == null || TSFeiertag.getTime() > TSGueltig.getTime())))
                                          {
                                            if (Qry.open(
                                                "SELECT Zwischentext,Kennung FROM Zwischenspeicher WHERE Kennung in ('StammUpload','ZeitZutritt','Feiertag') AND TERMINAL='" +
                                                getName() + "' ORDER BY AIC_Zwischenspeicher"))
                                            {
                                              ProtSave("Upload-start " + sIp_Port);
                                              for (; !Qry.eof(); Qry.moveNext())
                                              {
                                                out.write(Qry.getS("Zwischentext").getBytes());
                                                if (iWW>0)
                                                  Static.sleep(iWW);
                                                glob.fixInfo(getName() + ":" + Qry.getS("Kennung") + "/" + Qry.getS("Zwischentext").trim());
                                              }
                                              ProtSave("Upload-stop " + sIp_Port);
                                              Qry.close();
                                            }
                                            TSGueltig = TSStammUpload != null && TSZeitZutritt != null ? TSStammUpload.getTime() > TSZeitZutritt.getTime() ? TSStammUpload :
                                                TSZeitZutritt : TSStammUpload != null ? TSStammUpload : TSZeitZutritt;
                                          }
                                          else if (TSStammUpload2 != null && (TSGueltig2 == null || TSStammUpload2.getTime() > TSGueltig2.getTime()))
                                          {
                                            Tabellenspeicher Tab=new Tabellenspeicher(glob,"SELECT aic_Zwischenspeicher,Zwischentext FROM Zwischenspeicher WHERE Kennung='StammUpload2' AND TERMINAL='" +
                                                getName() + "' ORDER BY AIC_Zwischenspeicher",true);

                                              ProtSave("Upload2-start " + sIp_Port);
                                              for (; !Tab.eof(); Tab.moveNext())
                                              {
                                                out.write(Tab.getS("Zwischentext").getBytes());
                                                glob.fixInfo(getName() + ": StammUpload2/" + Tab.getS("Zwischentext"));
                                              }
                                              ProtSave("Upload2-stop " + sIp_Port);
                                            glob.exec("delete from zwischenspeicher where "+glob.in("aic_Zwischenspeicher",Tab.getVecSpalte("aic_Zwischenspeicher")));
                                            TSGueltig2 = TSStammUpload2;
                                          }
                                        }
                                        else if (iLZmom>=iLZ)
                                          iLZmom=0;
                                        else
                                          iLZmom++;

                                        if (iLSmom==0)
                                        {
                                          iLSmom++;
                                          glob.exec("update Zwischenspeicher set Gueltig="+glob.now()+" where kennung='Status' AND TERMINAL='" +getName() + "'");
                                        }
                                        else if (iLSmom>=iLS)
                                          iLSmom=0;
                                        else
                                          iLSmom++;

                                        if (iLAmom==1 && AbfAlarm!=null)
                                        {
                                          iLAmom++;
                                          //int iAnzImport=SQL.getInteger(glob,"select count(*) from zwischenspeicher where Kennung='Import' and pro_aic_protokoll is null and gueltig>"+SQL.von());
                                          //glob.testInfo("in Zwischenspeicher:"+iAnzImport);
                                          //if (iAnzImport==0)
                                          {
                                            iDa=AbfAlarm.getDaten0().getAnzahlElemente(null);
                                            if (iDa==0)
                                            {
                                            	glob.fixInfo("!!! ALARM AN !!!");
                                              write("J****!00TT100000**");
                                            }
                                            else
                                            {
                                              write("J****!00TT000000**");
                                              glob.testInfo("Noch " + iDa + " Anwesend");
                                            }
                                          }
                                        }
                                        else if (iLAmom>=iLA)
                                          iLAmom-=iLA;
                                        else
                                          iLAmom++;

                                        if (iAnzahl>28)
                                        {
                                            iAnzahl=0;
                                            DateWOD date = new DateWOD();
                                            glob.testInfo("date vorher="+date);
                                            if (Transact.iZoneOffset != 0)
                                              date.add(60000*Transact.iZoneOffset);
                                            glob.testInfo("date nachher="+date+" bei "+Transact.iZoneOffset);
                                            write("J****!00U3"+date.Format("yyyyMMddHHmmss")+" "+(date.get(Calendar.DAY_OF_WEEK)-1)+"**");
					}
					else
						iAnzahl++;

				}
				else
				{
                  boolean bQuittieren=true;
                  boolean bSendAS=true;
				  if(sSatz.length()>6 && sSatz.substring(5,6).equals("R"))
				  {
                                  glob.testInfo("Online-Check:"+sSatz);
                                  String sArt=sSatz.substring(8,10);
                                  boolean bBDE=sArt.equals("BD") || sArt.equals("BE");
                                      String sTAK = sSatz.substring(10, 13);
                                      int iL=TabTerminal.Kartenlaenge(false,iInt1);//bSymbol  ? 16:bMifare || bMifare2 || bHitagHex ? 10:bMifareD ? 17:bNova ? 14:bRo ? 12:8;
                                      String sKartenNr = sSatz.substring(13,13+iL);
                                      if(bHitag || bLegic || bMifare2)
                                        sKartenNr=sKartenNr.substring(2);
                                      else if (sArt.equals("PN"))
                                        glob.testInfo("Personalnummer="+sKartenNr);
                                      else if(bMiro)
                                        sKartenNr=Static.MiroCalc(sKartenNr,false);
                                      else if (bHitagHex)
                                        sKartenNr=Static.FillNull(""+Long.decode("#"+sKartenNr),12);
                                      String sTaetigkeit = "";
                                      String sAuftrag = "";
                                      String sKostenstelle = "";
                                      if (bBDE)
                                      {
                                        int iA = sSatz.indexOf(';') + 1;
                                        int iE = 0;
                                        do {
                                          iE = sSatz.indexOf(';', iA);
                                          if(iE == -1)
                                            iE = sSatz.length() - 3;
                                          String sTKA = sSatz.substring(iA, iE).trim();
                                          if(sTKA.length() == iAnzTaetig)
                                            sTaetigkeit = sTKA;
                                          else if(sTKA.length() == iAnzAuftrag)
                                            sAuftrag = sTKA;
                                          else if(sTKA.length() == iAnzKST)
                                            sKostenstelle = sTKA;
                                          iA = iE + 1;
                                        }
                                        while(iE != sSatz.length() - 3);
                                      }
					//Vector Vec = new Vector();
					glob.sJoker=(bKarteNullen?sKartenNr:Static.CutNull(sKartenNr));
                                        glob.testInfo("StringJoker="+glob.sJoker);
					ShowAbfrage A=null;

                                        boolean bAllow = true;
                                        String sKG=null;
                                        String sNotAllow=null;
                                        if (bBDE)
                                        {
                                          A=new ShowAbfrage(glob,"TerminalKarte");
                                          bAllow = !A.getDaten0().isEmpty();
                                          sNotAllow = bAllow ? "" : glob.getBegriffS("Show", "Kartennummer") + " " + glob.getBegriffS("Show", "falsch") + "!!!";
                                        }
                                        else if (AbfAbwesend != null)
                                        {
                                          Tabellenspeicher TabDaten=AbfAbwesend.getDaten0();
                                          glob.testInfo("Abfrage:"+(TabDaten.eof()?"null":TabDaten.getS("E" + iAbwStamm)+"/"+TabDaten.getB("E" + iAbwBool)));
                                          if (!TabDaten.eof())
                                          {
                                            int iAIC = TabDaten.getI("aic_bew_pool");
                                            String sName = TabDaten.getS("E" + iAbwStamm);
                                            boolean bAbw = TabDaten.getB("E" + iAbwBool);
                                            if (iDa != -1)
                                            {
                                              iDa+= bAbw ? -1:1;
                                              glob.testInfo(iDa==0 ? "Niemand mehr da":"Noch "+iDa+" da");
                                              iLAmom = bAbw && iDa<5 ? 1 : 2;
                                            }
                                            if(bAbw)
                                              glob.exec("delete from bew_boolean where aic_bew_pool=" + iAIC + " and aic_eigenschaft=" + iAbwBool);
                                            else
                                              glob.exec("insert into bew_boolean (aic_bew_pool,aic_eigenschaft,spalte_boolean) VALUES (" + iAIC + "," +
                                                  iAbwBool + ",1)");
                                            sKG = glob.getBegriffS("Show", (bAbw ? "geht" : "kommt") + " " + sName);
                                            glob.fixInfo(sKG);
                                          }
                                          else
                                            sKG = glob.getBegriffS("Show","Daten fehlen");
                                        }

					if(bAllow&&!sTaetigkeit.equals(""))
					{
						//Vec.removeAllElements();
						glob.testInfo("Taetigkeit: "+(bNullen?sTaetigkeit:Static.CutNull(sTaetigkeit)));
						glob.sJoker=(bNullen?sTaetigkeit:Static.CutNull(sTaetigkeit));
						A=new ShowAbfrage(glob,"TerminalTaetigkeit");
						bAllow =bAllow && !A.getDaten0().isEmpty();
						sNotAllow=bAllow?"":glob.getBegriffS("Show","TaetigkeitsNr")+" "+glob.getBegriffS("Show","falsch")+"!!!";
						//if (g.bTest && bAllow)
							//((Tabellenspeicher)Vec.elementAt(5)).showGrid("Taetigkeit");
					}
					if(bAllow&&!sAuftrag.equals(""))
					{
						//Vec.removeAllElements();
						glob.testInfo("Auftrag: "+(bNullen?sAuftrag:Static.CutNull(sAuftrag)));
						glob.sJoker=(bNullen?sAuftrag:Static.CutNull(sAuftrag));
						A=new ShowAbfrage(glob,"TerminalAuftrag");
						bAllow =bAllow && !A.getDaten0().isEmpty();
						sNotAllow=bAllow?"":glob.getBegriffS("Show","AuftragsNr")+" "+glob.getBegriffS("Show","falsch")+"!!!";
						//if (glob.TestPC() && bAllow)
							//((Tabellenspeicher)Vec.elementAt(5)).showGrid("Auftrag");
					}
					if(bAllow&&!sKostenstelle.equals(""))
					{
						//Vec.removeAllElements();
						glob.testInfo("KST: "+(bNullen?sKostenstelle:Static.CutNull(sKostenstelle)));
						glob.sJoker=(bNullen?sKostenstelle:Static.CutNull(sKostenstelle));
						A=new ShowAbfrage(glob,"TerminalKSt");
						bAllow =bAllow && !A.getDaten0().isEmpty();
						sNotAllow=bAllow?"":glob.getBegriffS("Show","KST")+" "+glob.getBegriffS("Show","falsch")+"!!!";
					}

					if(bAllow)
						write("J****!00R1"+sTAK+(sKG==null?"Online-Check OK":sKG)+"**");
					else
						write("J****!00R2"+sTAK+sNotAllow+"**");
				}
				else if(sSatz.startsWith("J"))
				{
                                  //glob.testInfo("else bei "+Static.FillNull(""+i2,6));
					bSendAS=sSatz.length()>5&&sSatz.substring(5, 6).equals("[");
					iProt=bSendAS || bNeuProt ? 0:iProt;
					bNeuProt=false;
                                        int iL=TabTerminal.Kartenlaenge(false,iInt1);//bSymbol ? 16:bMifare || bMifare2 || bHitagHex ? 10:bMifareD ? 17:bNova ? 14:bRo ? 12:8;
                                        boolean bAnders=true;
                                        java.sql.Timestamp TS=null;
                                        String sDatum=null;
                                        if(sSatz.length()>24+iL)
                                        {
                                          bAnders=sLast == null || !sSatz.substring(0,5).equals(sLast);
                                          //glob.testInfo("Anders="+bAnders+"/"+sLast+" bei "+Static.FillNull(""+i2,6));
                                          String sArt=sSatz.substring(8,10);
                                          sDatum = sSatz.substring(10+iL, 18+iL);
                                          String sZeit = sSatz.substring(18+iL, 24+iL);
                                          TS=new java.sql.Timestamp(new DateWOD(
                                          new Integer(sDatum.substring(0,4)).intValue(),
                                          new Integer(sDatum.substring(4,6)).intValue(),
                                          new Integer(sDatum.substring(6,8)).intValue(),
                                          new Integer(sZeit.substring(0,2)).intValue(),
                                          new Integer(sZeit.substring(2,4)).intValue(),
                                          new Integer(sZeit.substring(4,6)).intValue()).getTimeInMilli());
                                          if (iDS>0 && !sArt.equals("BD") && !sArt.equals("BE") && !sArt.equals("LE") && !sArt.equals("L1"))
                                          {
                                            if (lLastK>TS.getTime()-iDS*1000 && sLastK!=null && sSatz.substring(8, 10 + iL).equals(sLastK))
                                              bAnders=false;
                                            lLastK=TS.getTime();
                                            sLastK = sSatz.substring(8, 10 + iL);
                                          }
                                        }
					if(sSatz.length()>24+iL && bAnders)
					{
                                          //glob.testInfo("vor checkExit bei "+Static.FillNull(""+i2,6));
                                            checkExit();
                                          //glob.testInfo("nach checkExit bei "+Static.FillNull(""+i2,6));
                                            if (/*sSatz.startsWith("J") &&*/ sDatum.startsWith("20"))
                                            {
                                              iProt = iProt == 0 ? glob.Protokoll("Terminal", 0 /*unknown*/) : iProt;

                                              Qry.add("AIC_Protokoll", iProt);
                                              Qry.add("Gueltig", TS);
                                              Qry.add("Zwischentext", sSatz);
                                              Qry.add("Kennung", "Import");
                                              Qry.add("Terminal", getName());
                                              Qry.add("Zone", glob.getZone());
                                              Qry.add("aic_mandant", glob.getMandant());
                                              int iAIC_ZS = Qry.insert("Zwischenspeicher", true);
                                              bQuittieren = iAIC_ZS > 0;
                                              if (bQuittieren)
                                              {
                                                sLast = sSatz.substring(0, 5);
                                                iZSF=0;
                                              }
                                              else
                                              {
                                                sLastK = null; // wird nicht gespeichert, dann keine Doppelstempelprüfung
                                                iZSF++;
                                                glob.fixInfo(iZSF+".Zwischenspeicherfehler");
                                              }
                                              if (bDatei && bQuittieren)
                                              {
                                                String sSave2 = new Zeit(new java.sql.Timestamp(new Date().getTime()), "yyyy-MM-dd") + ".term";
                                                String sSave = sPath + sSave2;
                                                boolean bAicInfo=false;
                                                try
                                                {
	                                                boolean bNewFile = !new File(sSave).exists();
	                                                save = new FileOutputStream(sSave, true);
	                                                save.write((sSatz.substring(0, sSatz.length() - 1) + ":" + getName() + ":" + iAIC_ZS + "\r\n").getBytes());
	                                                save.close();
	
	                                                if (bNewFile)
	                                                {
	                                                  bAicInfo=true;
	                                                  save2 = new FileOutputStream(sPath + "AIC.info", true);
	                                                  save2.write((iAIC_ZS + ":" + sSave2 + "\r\n").getBytes());
	                                                  save2.close();
	                                                }
                                                }
                                            	catch
                                            	(Exception e)
                                        		{
                                            		glob.printError("Terminal.term: Fehler mit "+(bAicInfo ?"AIC.info":sSave2)+":"+e);
                                        		}
                                              }
                                            }
                                            else
                                              bQuittieren=false;
					}
                                        else
                                        {
                                          bQuittieren = iZSF==0;
                                        }
				  }
                                else
                                {
                                  bQuittieren=false;
                                  glob.printError("Stempelung von "+getName()+" fehlerhaft:"+sSatz);
                                }
                                //glob.testInfo("vor Quittieren bei "+Static.FillNull(""+i2,6));
                                if (bQuittieren)
                                {
                                  String sJob = sSatz.length() > 5 ? sSatz.substring(1, 5) : "";
                                  if (!sJob.equals("") && !sJob.equals("****"))
                                  {
                                    write("J****!00Q " + sJob + "**");
                                    glob.fixtestInfo("Stempeln -> bSendAS="+bSendAS+", sAServerSoll="+AClient.sAServerSoll);
                                    if (bSendAS && AClient.sAServerSoll != null)
                                    {
                                    	bNeuProt=true;
                                    	try
                                    	{
                                    		AClient.send_AServer("import"/*AClient.getImport(glob)*/,glob);
                                    	}
                                    	catch
                                    	(Exception e)
                                		{
                                    		Static.printError("AServer "+AClient.sAServerSoll+" nicht erreichbar: "+e);
                                		}
                                    }
                                  }
                                }
				}
                               bAktiv=false;
			}
                        Qry.free();
                        glob.fixInfo("fertig:"+new DateWOD());
		}
		catch(IOException e)
		{
                  bAktiv=false;
                  if(bThread)
                  {
                    e.printStackTrace();
                    glob.printError("Terminal.Online(" + getName() + "): Fehler beim Lesen: " + e);
                  }
			//else
			//	glob.testInfo("Terminal.Online("+getName()+"): Geschlossen!!!");
		}

	}
}

      private void write(String ssend)
      {
    	  glob.fixInfo("Schreiben: "+ssend);
        try
        {
          out.write((ssend + "\r").getBytes());
        }
        catch(IOException e)
        {
          bAktiv=false;
          Static.printError("Terminal.write: Fehler beim schreiben von:"+ssend);
        }
//        if (bWI)
//          Transact.fixInfo("geschrieben: "+ssend);
      }

      private void checkExit()
        {
          //boolean bConnect=SQL.getInteger(glob,"select count(aic_stammtyp) from stammtyp")>0;
          if (bExit && SQL.getInteger(glob,"select count(aic_stammtyp) from stammtyp")<1)
          {
            try
            {
                    fileRun.close();
            }
            catch(IOException ioe)
            {
                    System.err.println("Save.prot(): IOException - "+ioe);
            }
            if (!new File(sPath+"Terminal.run").delete())
              ProtSave("not deleted");
            //dispose();
            ProtSave("disconnected");
            System.exit(6);
          }
        }

        public static void checkExit2(Global g)
        {
          if (bCE2)
            return;
          bCE2=true;
          int iStatus= iPU>0 ? SQL.getInteger(g,"select "+g.int1()+" from parameter where aic_parameter="+iPU,5):-2;
          if (iStatus>0)
          {
            boolean bRaus=true;
            if (iStatus==6)
            {
                Vector Vec=SQL.getVector("select "+g.int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='raus'",g);
                bRaus=Vec.contains(new Integer(g.getLog()));
            }
            if (bRaus)
            {
              if (g.getLog() > 0)
                g.Logout(false);
              else
                g.disconnect();
              AClient.setNull();
              if (iStatus == 4)
              {
                ProtSave("deactivated");
                System.exit(7);
              }
              else
              {
                while (iStatus > 0)
                {
                  Static.sleep(5000);
                  iStatus = SQL.getInteger(g, "select " + g.int1() + " from parameter where aic_parameter=" + iPU, 5);
                  if (iStatus == 4)
                  {
                    ProtSave("deactivated2");
                    System.exit(7);
                  }
                }
                g.Login();
                //if (sAServer != null)
                //  AClient.sAServerSoll=sAServer;
                AClient.setg(g);
                ShowAbfrage.refreshBerechtigung(g);
              }
            }
          }
          bCE2=false;
        }

public boolean Connect(String rsIp_Port,Global g)
{
	//if(TabConnections==null)
	//	TabConnections = new Tabellenspeicher(g,new String[]{"IP/Port","Socket","InputStream","OutputStream"});
        lLast=Static.get_ms2();
	boolean bOk=false;
        if (rsIp_Port != null)
          sIp_Port=rsIp_Port;
        bAktiv=true;
	//if(!TabConnections.posInhalt("IP/Port",sIp_Port))
	//{
		String sIp = sIp_Port!=null&&sIp_Port.indexOf(":")>-1?sIp_Port.substring(0,sIp_Port.indexOf(":")):"";
		int iPort = sIp_Port!=null&&sIp_Port.indexOf(":")>-1?new Integer(sIp_Port.substring(sIp_Port.indexOf(":")+1)).intValue():0;
		try
		{
			//do
			//{
				socket = new Socket(sIp,iPort);
				out = socket.getOutputStream();
				in = socket.getInputStream();

				byte abyte[] = new byte[200];
				in.read(abyte);
				String s = new String(abyte, 0, 17);

				if(s.length() > 0)
				{
					g.fixInfo("Gerätezustand von Terminal("+sIp_Port+"):"+s.trim());

					  g.exec("DELETE FROM Zwischenspeicher WHERE Kennung='Status' AND TERMINAL='"+getName()+"'");
                                          iProt=iProt==0?glob.Protokoll("Terminal",0):iProt;
                                          SQL Qry=new SQL(g);
                                          Qry.add("AIC_Protokoll",iProt);
                                          Qry.addnow("Gueltig");
                                          Qry.add("Zwischentext",s);
                                          Qry.add("Kennung","Status");
                                          Qry.add("Terminal",getName());
                                          Qry.insert("Zwischenspeicher",true);

					bOk=s.substring(0,2).equals("16") || s.substring(8,12).equals("T 00") || s.substring(8,12).equals("T 76") || s.substring(8,12).equals("T 74");
                                        //bAktiv=bOk;

					if(!bOk)
					{
						//sFinal_IP_Port=sIp_Port;
						//Config(s,g);
                                                Close();
						//Close(sIp_Port,g);
					}
                                        else
                                        {
                                          glob.testInfo("Terminalconnect " + sIp_Port);
                                          ProtSave("Connect "+sIp_Port);
                                        }
				}
                                //socket.setKeepAlive(true);
			//}
			//while(!bOk);

		}
		catch(IOException e)
		{
			g.fixInfo("Terminal.Connect("+sIp_Port+"): Fehler beim Verbinden des Terminals!");
                        //bAktiv=false;
                        bThread=false;
			bOk=false;
		}
                bAktiv=false;
	//}
	//else
	//	bOk=true;

	return bOk;
}

public boolean Close()
{

  boolean bOk = true;
  if (bThread)
  {
    bThread=false;
    try {
      if (out != null)
        out.close();
      if (in != null)
        in.close();
      if (socket != null)
        socket.close();
      bAktiv = false;
      glob.testInfo("Terminalclose " + sIp_Port);
      ProtSave("Close " + sIp_Port);
      this.interrupt();
      //glob.fixInfo("Socket-Verbindung geschlossen -> "+sIp_Port);
    }
    catch(IOException e) {
      Static.printError("Terminal.Close(" + sIp_Port + "): Fehler beim Schliessen der Verbindung!!!");
      bOk = false;
    }
  }
  return bOk;
}

public String toString()
{
  return getName()+"/"+bAktiv;
}

public boolean Aktiv()
{
  return bAktiv;
}

public long Dif()
{
  return Static.get_ms2()-lLast;
}

private static void ProtSave(String s)
{
  if (Static.DirError != null)
        Save.prot(new java.io.File(Static.DirError+"TERMINAL.log"),Runtime.getRuntime().totalMemory()+"\t"+Runtime.getRuntime().freeMemory()+"\t"+Version.getVer2()+"\t"+s);
}

/*private static boolean Close(String sIp_Port, Global g)
{
	boolean bOk = true;
	if(TabConnections!=null && (sIp_Port==null || TabConnections.posInhalt("IP/Port",sIp_Port)))
	{
		try
		{
			((OutputStream)TabConnections.getInhalt("OutputStream")).close();
			((InputStream)TabConnections.getInhalt("InputStream")).close();
			((Socket)TabConnections.getInhalt("Socket")).close();

			g.fixInfo("Socket-Verbindung geschlossen -> "+TabConnections.getS("IP/Port"));
			TabConnections.clearInhalt();
		}
		catch(IOException e)
		{
			Static.printError("Terminal.Close("+sIp_Port+"): Fehler beim Schliessen der Verbindung!!!");
			bOk=false;
		}
	}
	return bOk;
}

public static boolean CloseAll(Global g)
{
	boolean bOk=true;
	if(TabConnections!=null)
	{
		for(TabConnections.moveFirst();bOk&&!(TabConnections.out()||TabConnections.eof());)
			bOk=Close(null,g);
	}

	return bOk;
}*/


// add your data members here
/*public static final int KARTE=  0x0034;
public static final int HITAG=  0x0000;
public static final int MIRO=   0x0004;
public static final int LEGIC=  0x0010;
public static final int RO=     0x0014;
public static final int SYMBOL= 0x0020;
public static final int MIFARE= 0x0024;
//public static final int xx=   0x0030;
public static final int MIFARE2=0x0034;*/


public Socket socket;
private InputStream in = null;
private OutputStream out = null;
public String sIp_Port=null;
private int i2=0;
private long lLast;
private String sLast=null;
private String  sLastK=null;
private long lLastK=0;

private boolean bAktiv=false;

private static Global glob=null;
private int iProt=0;
private boolean bNeuProt=true;

//private static String sFinal_IP_Port="";

//private static JDialog FrmConfig = null;
//private static FileEingabe FE = null;
//private static JLabel LblConfig = null;
private static boolean bCE2=false;

private static long lLogcheck = 0l;

//private JTextField TxtIp_Port;
//private FileEingabe FE_Pfad;

public boolean bThread = true;
private java.sql.Timestamp TSGueltig=null;
private java.sql.Timestamp TSGueltig2=null;
private int iAnzahl=29;

private static FileOutputStream save;
private static FileOutputStream save2;
public static String sPath;
public static boolean bExit=false;
public static FileOutputStream fileRun;
public static int iPU=0;
public static int iLZ=30;
public static int iLS=30;
public static int iLA=10;
public static int iDS=30;
private int iLZmom=0;
private int iLSmom=0;
private int iLAmom=0;
public static ShowAbfrage AbfAlarm=null;
public static ShowAbfrage AbfAbwesend=null;
public static int iAbwStamm=0;
public static int iAbwBool=0;
private static int iDa=-1;
private int iZSF=0; //Zwischenspeicherfehler
//public static boolean bWI=false;
public static int iWW=0;
//public static String sAServer=null;
}

