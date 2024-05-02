/*
    All_Unlimited-Allgemein-TabTerminal.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

import java.util.Vector;

// add your custom import statements here

public class TabTerminal extends java.lang.Object
{
    // add your data members here
    public static final int BDE00=   0x0001;
    public static final int DATEI=   0x0002;
    public static final int KARTE=   0x00B4;
    public static final int KARTE0=  0x0008;
    public static final int MECS =   0x0040;

    public static final int HITAG=   0x0000; // 8 Stellen - 6 verwendet
    public static final int MIRO=    0x0004; // Miro-Verschlüsselung von 8 Stellen
    public static final int LEGIC=   0x0010; // ident mit Hitag ?
    public static final int RO=      0x0014; // 12 Stellen
    public static final int SYMBOL=  0x0020; // 16 Stellen
    public static final int MIFARE=  0x0024; // 10 Stellen
    public static final int HITAGHEX=0x0030; // 10 Stellen Hex werden auf 12 Stellen umgewandet
    public static final int MIFARE2= 0x0034; // 12 Stellen (erst 2 werden mit 0 aufgefüllt)
    public static final int MIFARED= 0x0080; // 17 Stellen
    public static final int NOVA   = 0x0084; // 14 Stellen für Novachron für PeterMax
    public static final int MIFARE8= 0x0090; //  8 Stellen, alle verwendet
    public static final int SONST=   0x0094; // beliebige Kartenlänge (1-31 Stellen)
    public static final int KL_pos = 0x0100;
    public static final int KL_max = 0x1F;

    public static void Read(String sIp_Port,Tabellenspeicher Tab,java.sql.Timestamp rTS,Global g)
    {
      g.defInfo2("Read:"+sIp_Port);
      String sMandant=g.getReadMandanten(false,"zwischenspeicher");
      Tabellenspeicher TabStempel = new Tabellenspeicher(g,"select * from zwischenspeicher where kennung='Import' and terminal='"+sIp_Port+"' and "+(rTS!=null?"gueltig>"+Static.SQL_Format(rTS):"Pro_AIC_Protokoll is null")+sMandant,true);
      if (TabStempel==null || TabStempel.size()==0)
        return;
      g.testInfo("Terminal "+sIp_Port+" liefert "+TabStempel.size()+" Stempelungen");
      Parameter Para = new Parameter(g,"Terminal");
	Para.getMParameterH("Werte");
	int iAnzKST=Para.bGefunden?Para.int2:6;
	int iAnzTaetig=Para.bGefunden?Para.int3:3;
	int iAnzAuftrag=Para.bGefunden?Para.int4:15;
	boolean bNullen = Para.bGefunden&&(Para.int1&1)==1;
        boolean bHitag = Para.bGefunden&&(Para.int1&KARTE)==HITAG;
        boolean bLegic = Para.bGefunden&&(Para.int1&KARTE)==LEGIC;
        boolean bMiro = Para.bGefunden&&(Para.int1&KARTE)==MIRO;
        //boolean bRo=Para.bGefunden&&(Para.int1&KARTE)==RO;
        //boolean bSymbol=Para.bGefunden&&(Para.int1&KARTE)==SYMBOL;
        //boolean bMifare=Para.bGefunden&&(Para.int1&KARTE)==MIFARE;
        boolean bMifare2=Para.bGefunden&&(Para.int1&KARTE)==MIFARE2;
        //boolean bMifareD=Para.bGefunden&&(Para.int1&KARTE)==MIFARED;
        //boolean bNova=Para.bGefunden&&(Para.int1&KARTE)==NOVA;
        boolean bHitagHex=Para.bGefunden&&(Para.int1&KARTE)==HITAGHEX;
	boolean bKarteNullen = Para.bGefunden?(Para.int1&KARTE0)==0:true;
        int iInt1=Para.int1;
        //int iKarte=Para.int1&KARTE;
        //g.progInfo("Parameter="+Para.int1+"-> Hitag="+bHitag);
        Para.free();
        Tabellenspeicher TabZT=null;
        SQL Qry = new SQL(g);
	//int iProt=!TabStempel.eof()?g.Protokoll("Terminal",0/*unknown*/):0;
        Vector<String> VecMA=null;
        Vector<String> VecZ=null;
        if (Static.bOhneProt)
        {
              //g.bISQL = true;
              TabZT = new Tabellenspeicher(g,"select * from all_terminal_ohne_prot",true);
              if (TabZT!=null && TabZT.size()>0 && TabZT.posInhalt("Terminal",sIp_Port))
              {
                if (!TabZT.getB("nicht"))
                  VecMA = SQL.getSVector("select * from all_ma_ohne_prot", g);
                VecZ = SQL.getSVector("select * from all_Zutritt_ohne_prot", g);
              }
              //g.bISQL = false;
        }
	for(;!TabStempel.eof();TabStempel.moveNext())
	{
		java.sql.Timestamp TS = TabStempel.getTimestamp("Gueltig");
		String sSatz=TabStempel.getS("Zwischentext");
		/*
		int i=sSatz.indexOf("78\r");
		if(i>=0)
			sSatz=sSatz.substring(i+3);
		i=sSatz.indexOf("\r78");
		if(i>=0)
			sSatz=sSatz.substring(0,i);
		sSatz=sSatz+"\r";*/

		g.printInfo("Lesen: "+sSatz);
		String sMemo = sSatz.substring(sSatz.indexOf("\r")+1);
		sSatz=sSatz.substring(0,sSatz.indexOf("\r")+1);
		String sSubLeser = sSatz.substring(6,8);
		String sArt = sSatz.substring(8, 10);
                int iL=Kartenlaenge(sIp_Port.equals("Symbol"),iInt1);//bSymbol || sIp_Port.equals("Symbol") ? 16:bMifare || bMifare2 || bHitagHex ? 10:bMifareD ? 17:bNova ? 14:bRo ? 12:8;
		String sKartenNr = sSatz.substring(10, 10+iL);
		String sFehler = sSatz.substring(24+iL, 25+iL);
		String sTaetigkeit = "";
		String sAuftrag = "";
		String sKostenstelle = "";
//		g.fixtestError("Karte="+sKartenNr+" bei Art="+sArt);
//		g.fixtestError("Satz="+sSatz);
//		g.fixtestError("Memo="+sMemo);
		int iA=sSatz.indexOf(';')+1;
		int iE=0;
		do
		{
			iE=sSatz.indexOf(';',iA);
			if(iE==-1)
				iE=sSatz.length()-3;

			String sTAK = sSatz.substring(iA,iE).trim();
			if(sTAK.length()==iAnzTaetig)
				sTaetigkeit=sTAK;
			else if(sTAK.length()==iAnzAuftrag)
				sAuftrag=sTAK;
			else if(sTAK.length()==iAnzKST)
				sKostenstelle=sTAK;

			iA=iE+1;
		}
		while(iE!=sSatz.length()-3);
		//g.testInfo("Kartennummer1="+sKartenNr);
                if(!sKartenNr.equals("********"))
		{
                        if(!sIp_Port.equals("Symbol") && (bHitag || bLegic || bMifare2))
                          sKartenNr=sKartenNr.substring(2);
                        else if (sArt.equals("PN"))
                          g.testInfo("Personalnummer="+sKartenNr);
                        else if(bMiro)
                          sKartenNr=Static.MiroCalc(sKartenNr,false);
                        else if (bHitagHex)
                          sKartenNr=Static.FillNull(""+Long.decode("#"+sKartenNr),12);
			//g.testInfo("Kartennummer2="+sKartenNr);
                        String sK2=bKarteNullen?sKartenNr:Static.CutNull(sKartenNr);
                        if (VecZ!=null && VecZ.contains(sArt) && (TabZT.getB("nicht") || VecMA!=null && VecMA.contains(sK2)))
                          g.exec("delete from zwischenspeicher where AIC_Zwischenspeicher=" + TabStempel.getInt("AIC_Zwischenspeicher"));
                        else
                        {
                          Tab.newLine();
                          Tab.putElementAt(Tab.getPos(), 0, TabStempel.getInt("AIC_Zwischenspeicher"));
                          Tab.putElementAt(Tab.getPos(), 1, TabStempel.getInt("Zone"));
                          Tab.putElementAt(Tab.getPos(), 3, sK2);
                          Tab.putElementAt(Tab.getPos(), 4, sArt);
                          Tab.putElementAt(Tab.getPos(), 5, TS);
                          Tab.putElementAt(Tab.getPos(), 6, sKostenstelle.indexOf(';') > -1 || sKostenstelle.equals("") ? null : bNullen ? sKostenstelle : Static.CutNull(sKostenstelle));
                          Tab.putElementAt(Tab.getPos(), 7, sAuftrag.indexOf(';') > -1 || sAuftrag.equals("") ? null : bNullen ? sAuftrag : Static.CutNull(sAuftrag));
                          Tab.putElementAt(Tab.getPos(), 8, sTaetigkeit.indexOf(';') > -1 || sTaetigkeit.equals("") ? null : bNullen ? sTaetigkeit : Static.CutNull(sTaetigkeit));
                          Tab.putElementAt(Tab.getPos(), 9, sIp_Port);
                          Tab.putElementAt(Tab.getPos(), 10, sSubLeser);
                          Tab.putElementAt(Tab.getPos(), 11, sFehler);
                          Tab.putElementAt(Tab.getPos(), 12, sMemo);
                          Tab.putElementAt(Tab.getPos(), 13, TabStempel.getInt("AIC_Mandant"));
                        }
		}

		//Qry.add("Pro_AIC_Protokoll",iProt);
		//Qry.update("Zwischenspeicher",TabStempel.getI("AIC_Zwischenspeicher"));
	}
	Qry.free();
}

public static int Kartenlaenge(boolean bSymbol,int iInt1)
{
      if (bSymbol)
        return 16;
      else
      {
        int iKarte = iInt1 & KARTE;
        return iKarte == SYMBOL ? 16 : iKarte == MIFARE || iKarte == MIFARE2 || iKarte == HITAGHEX ? 10 :
            iKarte == MIFARED ? 17 : iKarte == NOVA ? 14 : iKarte == RO ? 12 : iKarte == SONST ? iInt1/KL_pos : 8;
      }
}

public static void Quittieren(int iAIC_Zwischenspeicher,int iProt,Global g)
{
	g.exec("UPDATE Zwischenspeicher SET Pro_AIC_Protokoll="+iProt+" where AIC_Zwischenspeicher="+iAIC_Zwischenspeicher);
}

private static void writeZS(Global g,SQL Qry,String sKennung,String sIP,java.sql.Timestamp TS,String ssend,int iProt)
      {
        Qry.add("Kennung", sKennung);
        Qry.add("TERMINAL", sIP);
        Qry.add("Gueltig", TS);
        Qry.add("Zwischentext", ssend);
        Qry.add("AIC_Protokoll", iProt);
        Qry.add("Zone", g.getZone());
        Qry.add("AIC_Mandant", g.getMandant());
        Qry.insert("Zwischenspeicher", false);
      }

public static boolean StammUpload(String sIP,Tabellenspeicher Tab,Global g,boolean bAlle)
{
	boolean bOk=true;
	if(Tab!=null)
	{
		Parameter Para = new Parameter(g,"Terminal");
		Para.getMParameterH("Werte");
                //boolean bHitag = Para.bGefunden&&(Para.int1&KARTE)==HITAG;
                //boolean bLegic = Para.bGefunden&&(Para.int1&KARTE)==LEGIC;
                boolean bMiro = Para.bGefunden&&(Para.int1&KARTE)==MIRO;
                //boolean bRo=Para.bGefunden&&(Para.int1&KARTE)==RO;
                //boolean bSymbol=Para.bGefunden&&(Para.int1&KARTE)==SYMBOL;
                //boolean bMifare=Para.bGefunden&&(Para.int1&KARTE)==MIFARE;
                //boolean bMifare2=Para.bGefunden&&(Para.int1&KARTE)==MIFARE2;
                boolean bHitagHex=Para.bGefunden&&(Para.int1&KARTE)==HITAGHEX;
                //boolean bMifareD=Para.bGefunden&&(Para.int1&KARTE)==MIFARED;
                //boolean bNova=Para.bGefunden&&(Para.int1&KARTE)==NOVA;
                //int iKarte=Para.int1&KARTE;
                int iInt1=Para.int1;
                String sKennung=bAlle ? "StammUpload":"StammUpload2";
                Para.free();
		int iProt=g.Protokoll("Terminal",0/*unknown*/);
                if (bAlle)
                  g.exec("DELETE FROM Zwischenspeicher WHERE Kennung='StammUpload' and TERMINAL='"+sIP+"'"+g.getReadMandanten(false,"zwischenspeicher"));
		java.sql.Timestamp TS = SQL.getNow(g);
		String sLeer="        ";
                SQL Qry = new SQL(g);
                String ssend = null;
		if (bAlle)
                  writeZS(g,Qry,"StammUpload",sIP,TS,"J****!**Y7**\r",iProt);
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			String sKartenNr=Tab.getS(3);
			if(bMiro)
                          sKartenNr=Static.MiroCalc(sKartenNr,true);
                        else if (bHitagHex)
                          sKartenNr=Static.FillNull(Long.toHexString(Long.parseLong(sKartenNr)).toUpperCase(),10);
			else
                          sKartenNr=Static.FillNull(sKartenNr,Kartenlaenge(sIP.equals("Symbol"),iInt1));//bSymbol || sIP.equals("Symbol") ? 16:bMifare || bMifare2 ? 10:bMifareD ? 17:bNova ? 14:bRo ?12:8);
			String sZutProfil = Tab.getS(4);
			sZutProfil=Static.FillNull(sZutProfil,3);
			String sSaldo;
			if (Tab.getAnzahlSpalten()>8 && !Tab.isNull(8))
				sSaldo=Tab.getS(8);
			else
			{
			  double d = Sort.getf(Tab.getElementAt(Tab.getPos(),5));//((Double)Tab.getElementAt(Tab.getPos(),4)).doubleValue();
			  String sZeitsaldo = d<10000&&d>-1000?new java.text.DecimalFormat("0.00").format(d):"0,00";
			  sZeitsaldo=sLeer.substring(sZeitsaldo.length())+sZeitsaldo;
			  d = Sort.getf(Tab.getElementAt(Tab.getPos(),6));//((Double)Tab.getElementAt(Tab.getPos(),5)).doubleValue();
			  String sResturlaub = d<10000&&d>-1000?new java.text.DecimalFormat("0.00").format(d):"0,00";
			  sResturlaub=sLeer.substring(sResturlaub.length())+sResturlaub;
			  sSaldo=sZeitsaldo+sResturlaub;
			}
			//g.printInfo("StammUpload: "+sKartenNr+":"+sZutProfil+":"+sZeitsaldo+":"+sResturlaub+":");
                        String sBerProfil=Tab.getAnzahlSpalten()>7 && Tab.getS(7).length()==3 ? ""+Tab.getS(7):"000";
			ssend = "J****!**Y0"+sKartenNr+sZutProfil+"000"+sBerProfil+"00000*000"+sSaldo+"**\r";
			g.debugInfo("Terminal.StammUpload() Write->: "+ssend);
			writeZS(g,Qry,sKennung,sIP,TS,ssend,iProt);
		}
                if (bAlle)
                  writeZS(g,Qry,"StammUpload",sIP,TS,"J****!**Y9**\r",iProt);
		Qry.free();

		g.printInfo("Geaenderte Stammsaetze: "+Tab.getAnzahlElemente(null));
	}
	else
	{
		bOk=false;
		Static.printError("Terminal.StammUpload(): Tabelle ist null!!!");
	}
	return bOk;
}

public static boolean HolidayUpload(String sIP,Global g)
{
        int iProt=g.Protokoll("Terminal",0/*unknown*/);
        SQL Qry = new SQL(g);
        String sKennung="Feiertag";
        Qry.exec("DELETE FROM Zwischenspeicher WHERE Kennung='"+sKennung+"' and TERMINAL='"+sIP+"'"+g.getReadMandanten(false,"zwischenspeicher"));
        java.sql.Timestamp TS = SQL.getNow(g);

        String ssend = "J****!00>3L00**\r";
        g.debugInfo(ssend);
        writeZS(g,Qry,sKennung,sIP,TS,ssend,iProt);

        String sJahr=new DateWOD().Format("yyyy");
        int i=0;
        //g.TabFeiertage.showGrid("Feiertage");
        for(int iPos=0;iPos<g.TabFeiertage.size();iPos++)
        {
          DateWOD dt=(DateWOD)g.TabFeiertage.getInhalt("Datum",iPos);
          if (dt.Format("yyyy").equals(sJahr) && g.TabFeiertage.getI(iPos,"Stamm")==Global.keinStamm && g.TabFeiertage.getI(iPos,"Land")==g.getLand())
          {
            String sMMTT = dt.Format("MMdd");
            i++;
            ssend="J****!00>3L01"+Static.VorNull(i)+sMMTT+"JJJ-**\r";
            g.debugInfo(ssend);
            writeZS(g,Qry,sKennung,sIP,TS,ssend,iProt);
          }
        }
        ssend = "J****!00>9**\r";
        g.debugInfo(ssend);
        writeZS(g,Qry,sKennung,sIP,TS,ssend,iProt);
        Qry.free();

 return true;
}

public static boolean ZeitZutrittUpload(String sIP,Tabellenspeicher Tab,Global g,boolean bPZ)
{
	//g.progInfo("ZeitZutrittUpload start");
	boolean bOk=true;
	if(Tab!=null)
	{
		int iProt=g.Protokoll("Terminal",0/*unknown*/);
		SQL Qry = new SQL(g);
                String sKennung="ZeitZutritt";
                if (bPZ)
                  Qry.exec("DELETE FROM Zwischenspeicher WHERE Kennung='"+sKennung+"' and TERMINAL='"+sIP+"'"+g.getReadMandanten(false,"zwischenspeicher"));
		java.sql.Timestamp TS = SQL.getNow(g);
		//String sLeer="        ";

		String ssend = "J****!**P0**\r";
		//out.write(ssend.getBytes());
                if (bPZ)
                  writeZS(g,Qry,sKennung,sIP,TS,ssend,iProt);
		//g.progInfo(" vor Schleife");
                //Tab.showGrid(sIP);
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			String sZutProfil=""+Tab.getElementAt(Tab.getPos(),33);
			sZutProfil=Static.FillNull(sZutProfil,3);
			//g.progInfo("sZutProfil="+sZutProfil);
                        //g.progInfo("Timestamp="+Static.className(Tab.getElementAt(Tab.getPos(),9))+"/"+Static.className(Tab.getElementAt(Tab.getPos(),10)));
			String sBeginn=new DateWOD(((java.sql.Timestamp)Tab.getElementAt(Tab.getPos(),5)).getTime()).Format("HHmm");
			//g.progInfo("sBeginn="+sBeginn);
			String sEnde=new DateWOD(((java.sql.Timestamp)Tab.getElementAt(Tab.getPos(),6)).getTime()).Format("HHmm");
			//g.progInfo("sEnde="+sEnde);
			String sSo=   Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),7))?"J":"N";
			String sMo=   Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),8))?"J":"N";
			String sDi=   Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),9))?"J":"N";
			String sMi=   Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),10))?"J":"N";
			String sDo=   Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),11))?"J":"N";
			String sFr=   Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),12))?"J":"N";
			String sSa=   Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),13))?"J":"N";
                        String sFT=   Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),14))?"J":"N";
			String sHT=   Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),15))?"J":"N";
			String sSub1= Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),16))?"J":"N";
			String sSub2= Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),17))?"J":"N";
			String sSub3= Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),18))?"J":"N";
			String sSub4= Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),19))?"J":"N";
			String sSub5= Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),20))?"J":"N";
			String sSub6= Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),21))?"J":"N";
			String sSub7= Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),22))?"J":"N";
			String sSub8= Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),23))?"J":"N";
			String sSub9= Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),24))?"J":"N";
			String sSub10=Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),25))?"J":"N";
			String sSub11=Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),26))?"J":"N";
			String sSub12=Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),27))?"J":"N";
			String sSub13=Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),28))?"J":"N";
			String sSub14=Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),29))?"J":"N";
			String sSub15=Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),30))?"J":"N";
			String sSub16=Tabellenspeicher.getb(Tab.getElementAt(Tab.getPos(),31))?"J":"N";

			ssend = "J****!**"+(bPZ?"PZ":"PT")+sZutProfil+sBeginn+sEnde+sSo+sMo+sDi+sMi+sDo+sFr+sSa+sFT+"NN-N-NN"+sHT+sSub1+sSub2+sSub3+sSub4+sSub5+sSub6+sSub7+sSub8+sSub9+sSub10+sSub11+sSub12+sSub13+sSub14+sSub15+sSub16+"**\r";
			g.debugInfo("Terminal.ZeitZutrittUpload() Write->: "+ssend);
                        writeZS(g,Qry,sKennung,sIP,TS,ssend,iProt);
		}
                if (!bPZ)
                  writeZS(g,Qry,sKennung,sIP,TS,"J****!**P9**\r",iProt);

		Qry.free();

		g.printInfo("Geaenderte Zeitzutrittssaetze: "+Tab.getAnzahlElemente(null));
	}
	else
	{
		bOk=false;
		Static.printError("Terminal.ZeitZutrittUpload(): Tabelle ist null!!!");
	}
	g.progInfo("ZeitZutrittUpload fertig");
	return bOk;
}
}

