/*
    All_Unlimited-Allgemein-Global.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import javax.swing.*;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerComponent;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Anzeige.Ampel;
//import All_Unlimited.Allgemein.Anzeige.GPS;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
// import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Grunddefinition.Berechtigung;
import All_Unlimited.Grunddefinition.UserManager;
import All_Unlimited.Hauptmaske.ShowAbfrage;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;

import jclass.bwt.JCOutlinerNode;

import java.awt.GraphicsDevice;
import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

//import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;

import jclass.bwt.JCOutlinerFolderNode;



//import java.awt.event.MouseListener;
import java.awt.FlowLayout;

import javax.naming.AuthenticationException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.directory.DirContext;

import java.util.Hashtable;
//import java.io.File;
//import java.awt.event.MouseListener;
import java.util.List;

public class Global extends Transact
{
	/**
     * setBezeichnung Method
     */

    public boolean setBezeichnung(String sBezVor, String sBezNach, int iTab, int iFremd,int riSprache)
    {
		if (riSprache == 0)
			riSprache = iSprache;
		boolean bNeu=sBezVor==null;
        if(bNeu || !sBezVor.equals(sBezNach))
		{
        	String s=bNeu ? Static.sLeer:SQL.getString(this, "select bezeichnung from bezeichnung WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache);
			if (sBezNach.equals(s))
				return false;
			else if (!Static.Leer(s))
			{
				if(Static.Leer(sBezNach))
					exec("DELETE FROM Bezeichnung WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache);
				else
					exec("UPDATE Bezeichnung SET Bezeichnung="+Static.StringForSQL(sBezNach)+" WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache);
			}
			else
				if(!sBezNach.equals(""))
					exec("INSERT INTO Bezeichnung (AIC_Tabellenname, AIC_Fremd, AIC_Sprache, Bezeichnung) VALUES ("+iTab+","+iFremd+","+riSprache+","+Static.StringForSQL(sBezNach)+")");
			return true;
		}
        else
        	return false;
	}
	/*public int getBenutzer()
    {
        return(iBenutzer);
    }*/
    public String getBezeichnung(String sTabelle,String sKennung)
    {
		//String s=new String(sZeile);
		if (sTabelle.equalsIgnoreCase("Begriffgruppe"))
			return TabBegriffgruppen.getBezeichnungS(sKennung);
		else if (sTabelle.equalsIgnoreCase("Tabellenname"))
			return TabTabellenname.getBezeichnungS(sKennung);
		else if (sTabelle.equalsIgnoreCase("Stammtyp"))
			return TabStammtypen.getBezeichnungS(sKennung);
		else if (sTabelle.equalsIgnoreCase("Bewegungstyp"))
			return TabErfassungstypen.getBezeichnungS(sKennung);
		else
		{
			printInfo(1,"Bezeichnung "+sKennung+" muss von Datenbanktabelle "+sTabelle+" geholt werden!");
			SQL Qry = new SQL(this);
			Qry.open("Select bezeichnung from "+sTabelle+" t2,tabellenname t1 join bezeichnung where t1.kennung='"+sTabelle+"' and t2.kennung='"
					+sKennung+"' and bezeichnung.aic_fremd=t2.aic_"+sTabelle+" and bezeichnung.aic_sprache="+iSprache);
			if(!Qry.eof() && !Qry.getS("Bezeichnung").equals(""))
				sKennung = Qry.getS("Bezeichnung");
			Qry.free();
			return(sKennung);
		}
    }

    public String getBezeichnung(String sTabelle,int iAic)
    {
		String s=new String(sTabelle+" "+iAic);
		if (sTabelle.equalsIgnoreCase("Stammtyp"))
			s = TabStammtypen.getBezeichnung(iAic);
		else if (sTabelle.equalsIgnoreCase("Bewegungstyp"))
			s = TabErfassungstypen.getBezeichnung(iAic);
                else if (sTabelle.equalsIgnoreCase("Rolle"))
			s = TabRollen.getBezeichnung(iAic);
		else
		{
			SQL Qry = new SQL(this);
			Qry.open("Select t2.kennung,(Select bezeichnung from bezeichnung where aic_tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
			" and aic_fremd=t2.Aic_"+sTabelle+" and aic_sprache="+iSprache+") Bezeichnung from "+sTabelle+" t2 where t2.aic_"+sTabelle+"="+iAic);
			if(!Qry.eof())
				s = Qry.getS("Bezeichnung").equals("") ? Qry.getS("Kennung") : Qry.getS("Bezeichnung");
			Qry.free();
		}
		return(s);
    }

    public String getBezeichnungS(String sTabelle,String sKennung)
    {
      String s = new String(sTabelle + " " + sKennung);
      if (sTabelle.equalsIgnoreCase("Stammtyp"))
        s = TabStammtypen.getBezeichnungS(sKennung);
      else if (sTabelle.equalsIgnoreCase("Bewegungstyp"))
        s = TabErfassungstypen.getBezeichnungS(sKennung);
      else if (sTabelle.equalsIgnoreCase("Rolle"))
        s = TabRollen.getBezeichnungS(sKennung);
      else if (sTabelle.equalsIgnoreCase("Tabellenname"))
        s = TabTabellenname.getBezeichnungS(sKennung);
      else if (sTabelle.equalsIgnoreCase("Begriffgruppe"))
        s = TabBegriffgruppen.getBezeichnungS(sKennung);
      else
        printError("Global.getBezeichnungS von"+sTabelle+" wird nicht unterstützt");
      return s;
    }

    public String getBezeichnungS(String sTabelle,int iAic)
    {
                String s=new String(sTabelle+" "+iAic);
                if (sTabelle.equalsIgnoreCase("Stammtyp"))
                        s = TabStammtypen.getBezeichnungS(iAic);
                else if (sTabelle.equalsIgnoreCase("Bewegungstyp"))
                        s = TabErfassungstypen.getBezeichnungS(iAic);
                else if (sTabelle.equalsIgnoreCase("Rolle"))
                        s = TabRollen.getBezeichnungS(iAic);
                else if (sTabelle.equalsIgnoreCase("Tabellenname"))
                  s = TabTabellenname.getBezeichnungS(iAic);
                else
                {
                        SQL Qry = new SQL(this);
                        Qry.open("Select t2.kennung,(Select bezeichnung from bezeichnung where aic_tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
                        " and aic_fremd=t2.Aic_"+sTabelle+" and aic_sprache="+iSprache+") Bezeichnung from "+sTabelle+" t2 where t2.aic_"+sTabelle+"="+iAic);
                        if(!Qry.eof())
                                s = Qry.getS("Bezeichnung").equals("") ? Qry.getS("Kennung") : Qry.getS("Bezeichnung");
                        Qry.free();
                }
                return(s);
    }

    public String getBez(Object Obj)
    {
      int i = Tabellenspeicher.geti(Obj);
      return i > 0 ? TabStammtypen.getBezeichnungS(i) : TabErfassungstypen.getBezeichnungS( -i);
    }

    //                    //
	//    L O G G I N G   //
	//                    //

	/*public void Login()
	{
		Login(true);
	}*/

	public void Login()
	{
          //boolean bAnlage=true;
		//checkSpracheLand();
                //testInfo("Login "+bAnlage+", iMandant="+iMandant+", iAnlage="+iAnlage);
		if (iMandant>0 || iAnlage>0)
		{
			SQL Qry = new SQL(this);
			if (iAnlage>0) // && bAnlage)
                        {
                          //testInfo("iAnlageBenutzer="+iAnlageBenutzer+", iMandantT="+iMandantT);
                          if (iAnlageBenutzer>0)
                            Qry.open("select kennung,aic_benutzer,aic_mandant,aic_stamm,aic_sprache,aic_land,bits,AIC_Benutzergruppe from benutzer where"+
                                     (iMandantT>0 ? " aic_mandant="+iMandantT+" and":"")+bit("bits",iAnlageBenutzer)+" and geloescht is null");
                          if (Qry.eof()) // sucht letzten Benutzer dieses Computers mit dieser Anlage
                            Qry.open(top("kennung,benutzer.aic_benutzer,logging.aic_mandant,aic_stamm,aic_sprache,aic_land,bits,AIC_Benutzergruppe from logging join benutzer on logging.aic_benutzer=benutzer.aic_benutzer where aic_computer="+iComputer+
                                         " and aic_code="+iAnlage+" and benutzer.geloescht is null order by logging.aic_logging desc",1));
                        }
                        if (Qry.eof()) // sucht letzten Benutzer dieses Computers
                          Qry.open(top("kennung,benutzer.aic_benutzer,aic_stamm,aic_sprache,aic_land,bits,AIC_Benutzergruppe from logging join benutzer on logging.aic_benutzer=benutzer.aic_benutzer where aic_computer="+iComputer+" and aic_code is null order by logging.aic_logging desc",1));
			else
				iMandant=Qry.getI("aic_mandant");
                        //testInfo("iMandant="+iMandant);
			if (iMandant>0)
			{
                          /*if (noConnect())
                                  ;
                                else*/ if (Qry.eof())
				{
					//diskInfo("Global.Login(): Last Login fehlt");
                                        testInfo("Last Login fehlt");
                                        if (Static.bX11)
                                          JOptionPane.showMessageDialog(new JFrame(),"Last Login fehlt","Fehler",JOptionPane.ERROR_MESSAGE);
                                        else
                                          printError("Last Login fehlt");
					System.exit(14);
				}
				else
				{
                                  iBits = Qry.getI("BITS");
                                  //fixInfo("iAnlage="+iAnlage+",Bits="+(iBits&(cstTimerBenutzer+cstImport)));
                                  //if (iAnlage==0 && (iBits&(cstTimerBenutzer+cstImport))>0)
                                  //  printError("nicht zum einloggen gedacht");
                                  //else
                                  {
                                    iBenutzer = Qry.getI("AIC_Benutzer");
                                    setStamm(Qry.getI("Aic_Stamm"));
                                    iBenutzergruppe = Qry.getI("AIC_Benutzergruppe");
                                    sUser = Qry.getS("kennung");
                                    //readBGs();
                                    int iSpracheMom=Qry.getI("AIC_Sprache");
                                    int iLand2 = Static.sLand != null ? iStdLand : Qry.getI("AIC_Land");
                                    setSprache(iSpracheMom, iLand2);
                                    setLand(iLand2, -1);
                                    testInfo("iBenutzer=" + iBenutzer + ", iBits=" + iBits + ", Kennung=" + sUser);
                                    //bProg = Qry.getB("PROG");
                                    //bDef = bProg || Qry.getB("ENTWICKLER");
                                    if (writeLoginInfo())
                                    {
                                      if (fillVecMandant(true))
                                        fillBerechtigung(0);
                                    }
                                    if (!existsSprache(iSpracheMom))
                                      setSprache(1,iLand2);
                                    //else
                                    //  testInfo("fillBerechtigung nicht geladen");
                                    //progInfo("Last login:"+Qry.getS("kennung")+"/"+Qry.getS("ein"));
                                  }
				}
			}
			Qry.free();
		}
		if (iMandant==0)
		{
                  //testInfo("Mandant fehlt");
			diskInfo("Global.Login(): Mandant fehlt");
                        if (Static.bX11)
                          JOptionPane.showMessageDialog(new JFrame(),"Mandant fehlt","Fehler",JOptionPane.ERROR_MESSAGE);
                        else
                          printError("Mandant fehlt");
			System.exit(15);
		}
                else if (iLog<=0)
                {
                  diskInfo("Global.Login(): Einloggen fehlerhaft");
                  if (Static.bX11)
                    JOptionPane.showMessageDialog(new JFrame(),"Einloggen fehlerhaft","Fehler",JOptionPane.ERROR_MESSAGE);
                  else
                    printError("Einloggen fehlerhaft");
                  if (!Def())
                    System.exit(16);
                }
                printSI("Login");
	}

        private boolean PWok(String sPWein,int iBits,int iAic,String sPWdb)
        {
        	int iPWArt=iBits&cstPW;
          // if (iPWArt==cstPW_KEIN)
          //   return true;
          // else 
          if (iPWArt==cstPW_LDAP)
          {
            //printError("LDAP noch im Testmodus:"+iAic);
            Parameter Para=new Parameter(this,"LDAP");
            Para.getMParameterH("IP");
            String sDom="All-Unlimited.local";
            String sIP="192.168.1.101";
            int iPort=389;
            if (Para.bGefunden)
            {
              String s = Para.sParameterzeile;
              sDom=s.substring(s.indexOf("/") + 1);
              sIP=s.substring(0, s.indexOf("/"));
              iPort=Para.int1;
            }
            else
            {
              printError("LDAP nicht definiert");
              return false;
            }
            if (Static.bNotfall && iPort==666 && sDom.equals("TestAU"))
            	return true;
            try
            {
              Hashtable<String, String> env1 = new Hashtable<String, String>();
              env1.put(DirContext.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
              env1.put(DirContext.SECURITY_AUTHENTICATION, "simple");
              String sName=SQL.getString(this,"select Kennung from benutzer where aic_benutzer=?",""+iAic);
              env1.put(DirContext.SECURITY_PRINCIPAL, sName+"@"+sDom);
              env1.put(DirContext.SECURITY_CREDENTIALS, sPWein);
              env1.put(DirContext.PROVIDER_URL,"ldap://"+sIP+":"+iPort);
              env1.put("com.sun.jndi.ldap.connect.timeout", "5000");
              try
              {
                new InitialLdapContext(env1, null);
                return true;
              }
              catch (AuthenticationException e2)
              {
                return false;
              }
            }
            catch (Exception e2)
            {
            	iLogFehler=4;
              printError("LDAP: Sonstiger Fehler:" + e2);
            }
            return false;
          }
          else if (iPWArt==cstPW_AZURE)
          {
        	  return testOAuth(iAic,sPWein);
          }
//          else if (iPWArt==cstPW_GOOGLE)
//          {
//        	  printError("Google wird im All-Teil nicht unterstützt");
//        	  return false;
//          }
          else
          {
            boolean b= PasswordConvert(sPWein, iPWArt == cstPW_MD5 ? PWH:PWVH, iAic).equals(sPWdb);
            if (b && iPWArt == cstPW_MD5)
            {
            	String sPWx=PasswordConvert(sPWein,PWVH,iAic);
            	fixtestInfo("ändere auf sehr hoch:"+iAic);//sPWdb+"->"+sPWx);
            	SQL Qry=new SQL(this);
            	Qry.add("Passwort",sPWx);
            	Qry.add("bits", iBits-cstPW_MD5+cstPW_MD5B);
            	Qry.update("benutzer", iAic);
            	Qry.free();
            }
            return b;
          }
        }

	public void changeBenutzer(String sKennung,String sPW)
	{
          Tabellenspeicher Tab=new Tabellenspeicher(this,"select aic_benutzer,Passwort,bits from benutzer where aic_mandant="+iMandant+" and (use_bis is null or use_bis>"+now()+") and geloescht is null and kennung='" + sKennung + "'",true);
	  if (!Tab.isEmpty() && PWok(sPW,Tab.getI("bits"),Tab.getI("aic_benutzer"),Tab.getS("Passwort")))
	  {
            int i=Tab.getI("aic_benutzer");
            sUser=sKennung;
	    setBenutzer(i);
            TabMeine=null;
            fillBerechtigung(0);
	  }
	}

        public void setBenutzer(int i)
        {
          fixInfo("Benutzer="+i);
          iBenutzer=i;
          setStamm(SQL.getInteger(this,"select aic_stamm from benutzer where aic_benutzer=?",0,""+iBenutzer));
        }

        public boolean Login(JFrame Fom,String sKennung,String sPW,int iArt)
        {
          // fixtestError("Login1");
        	// Art: 0..kein PW, 1.. unschlüsselt, 2..wie in Db verschlüsselt, 3..ohne PW 
          sUser=sKennung;
          int iB=0;
          if (iArt==0 || iArt==4 || iArt==1)
        	  iB=SQL.getInteger(this, "select aic_benutzer from benutzer where kennung='" + sKennung+"'");
          String sPW1=iArt==0 || iArt==4 || sPW==null ? PasswordConvert(Static.sDefaultPW,PWVH,iB):iArt==1 ? PasswordConvert(sPW,PWVH,iB):sPW;
          if (iMandant==0)
          {
            if (sPW==null && iArt==3)
            	iMandant = SQL.getInteger(this, "select aic_mandant from benutzer where (use_bis is null or use_bis>"+now()+") and geloescht is null and aic_mandant>1 and kennung='" + sKennung+"' and" + bits("Benutzer.Bits", 3) + "=0");
            else //if (iMandant==0 || !SQL.exists(this,"select aic_mandant from benutzer where geloescht is null and kennung='"+sKennung+"' and passwort='"+sPW1+"' and aic_mandant="+iMandant))
              iMandant = SQL.getInteger(this, "select aic_mandant from benutzer where (use_bis is null or use_bis>"+now()+") and geloescht is null and kennung='" + sKennung + "' and passwort='" + sPW1 + "'");
          if (iMandant==0)
            return false;
          }
          //else
          {
            //fixInfo("Login: " + sKennung + ": Mandant=" + getMandant(iMandant,"Kennung"));
            return Login(Fom,sKennung, iArt==3 && sPW==null ? null:iArt == 0 || iArt==4 || sPW == null ? Static.sDefaultPW : iArt == 1 ? sPW : getPassword(sPW,PWR,0)); //getPassword(SQL.getString(this,"select passwort from Benutzer where aic_mandant="+iMandant+" and kennung='"+sKennung+"'"))
          }
        }

        private int MaxConnect()
        {
          return SQL.getInteger(this,"select Verbindungen from mandant where aic_mandant=?",0,""+iMandant);
        }

        private int MomConnect()
        {
          return SQL.getInteger(this,"select count(*) from logging where aus is null and "+bit("status",ADMIN)+" and aic_mandant="+iMandant);
        }

        public String getConnectInfo()
        {
          int iVerbindungen = MaxConnect();
          return iVerbindungen>0 ? " ("+MomConnect()+"/"+iVerbindungen+")":"";
        }

//        private boolean noConnect()
//        {
//          if (!HS())
//            return false;
//          int iVerbindungen=MaxConnect();
//          if (iVerbindungen > 0)
//          {
//            int iMom=MomConnect();
//            testInfo((iMom+1)+". von "+iVerbindungen+" erlaubten Verbindungen");
//            if (iMom>=iVerbindungen)
//            {
//              //JOptionPane.showMessageDialog(new JFrame(), "User-Lizenz erreicht", "Lizenz", JOptionPane.WARNING_MESSAGE);
//              return true;
//            }
//            else
//              return false;
//          }
//          else
//            return false;
//        }
        
    private void checkIndividuell(int iSp,int iM)
    {
    	if (iM==0 || iSp==0)
    		return;
    	boolean bLoadIndi=sIndi==null;
    	String sN=iSp+"_"+iM;
    	if (!bLoadIndi && !sIndi.equals(sN))
    		bLoadIndi=true;
    	if (bLoadIndi)
    		sIndi=sN;
//    	fixtestError("checkIndividuell: "+sIndi+"/"+bLoadIndi);
    	if (TabIndi==null || bLoadIndi)
    	{
    		if (TabIndi != null)
    			for (int i=0;i<TabIndi.size();i++)
    			{
    				int iPos=TabBegriffe.getPos("Aic", TabIndi.getI(i,"aic"));
    				if (iPos>=0)
    					TabBegriffe.setInhalt(iPos, "Bezeichnung", TabIndi.getS(i,"Original"));
    			}
    		TabIndi=new Tabellenspeicher(this,"select aic_fremd aic,BEZEICHNUNG,null Original,aic_protokoll from INDIVIDUELL where aic_tabellenname="+iTabBegriff+" and aic_sprache="+iSp+" and aic_mandant="+iM,true);
    	}
//    	TabIndi.showGrid("TabIndi");
    	for (int i=0;i<TabIndi.size();i++)
    	{
    		int iPos=TabBegriffe.getPos("Aic", TabIndi.getI(i,"aic"));
    		if (iPos>=0)
    		{
    			TabIndi.setInhalt(i, "Original", TabBegriffe.getS(iPos,"Bezeichnung")); 		
    			TabBegriffe.setInhalt(iPos, "Bezeichnung", TabIndi.getS(i,"Bezeichnung"));
    		}
    	}
    }

    private void Meldung(JFrame Fom,String sKennung,String sText,String sTitel)
    {
      // JFrame Fom=new JFrame();
      if (TabBegriffe != null)
        new Message(Message.WARNING_MESSAGE,Fom,this,10).showDialog(sKennung);
      else
        JOptionPane.showMessageDialog(Fom,new JLabel(sText), sTitel,JOptionPane.INFORMATION_MESSAGE);
    }

  private boolean check2F(JFrame Fom,SQL Qry)
  {
    //JFrame Fom=new JFrame();
    int iPW_Art=Qry.getI("bits")&Global.cstPW;
    if (iPW_Art != Global.cstPW_EMAIL && iPW_Art != Global.cstPW_KOMBI)
      return true;
    String sEMail=Qry.getS("E_MAIL");//SQL.getString(g, "select E_MAIL from benutzer where kennung="+sK);
        if (Static.Leer(sEMail) || sEMail.indexOf('@')<0)
        {
          Meldung(Fom,"E-Mail_fehlt","E-Mail-Adresse fehlt bei Benutzer","Passwort");
          return false;
        }
        String sCode=UserManager.getCode();
        fixtestInfo("Benutzer:"+Qry.getS("Kennung")+"/"+Qry.getI("aic_benutzer")+" -> Mandant="+Qry.getI("aic_mandant"));
        Vector<String> Vec=getTranslate("Message", "2FA", new String[] {sCode,""+Static.iETimer}, false);
        SMTP2 smtp=new SMTP2(this, null,Qry.getI("aic_mandant"));
          smtp.sendTo(sEMail);
          smtp.subject(Static.beiLeer(Sort.gets(Vec, 2), "2-Faktorcode"));
          smtp.sendText(Sort.gets(Vec, 1)/* "Code von ALL: "+sCode */);
          smtp.quit();

        JDialog Dlg = new JDialog(Fom, getBegriffS("Dialog", "2-Faktor-Authentifizierung"), true);
        Dlg.setBackground(Color.WHITE);
         JPanel PnlH=new JPanel(new BorderLayout(2, 2));
         PnlH.setOpaque(true);
         PnlH.setBackground(Color.WHITE);
        Dlg.getContentPane().setLayout(new BorderLayout(2, 2));
        Vec=getTranslate("Label","Code_eingeben",null,false);
        String sMemo=Sort.gets(Vec,1);
        int iPos=0;
        if (Static.Leer(sMemo))
        {
          JLabel Lbl=getLabel("Code_eingeben");
          Lbl.setFont(fontMessage);
          Lbl.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
          //TxtInfo.setBorder(new EmptyBorder(TxtInfo.getInsets()));
          PnlH.add("North", Lbl);//new JLabel("Bitte geben Sie den Code ein, den Sie per E-Mail erhalten haben:"));
        }
        else
        {
          String sTitel=Sort.gets(Vec,2);
          if (!Static.Leer(sTitel))
            Dlg.setTitle(sTitel);
          JEditorPane TxtMemo=new JEditorPane();//new AUTextArea(this,0);
          // TxtMemo.setOpaque(true);
          TxtMemo.setEditable(false);
          JScrollPane Scroll=new JScrollPane(TxtMemo);
          Scroll.setBorder(new EmptyBorder(new Insets(10,10,5,10)));
          Scroll.setBackground(Color.WHITE);
          // TxtMemo.setFont(fontMessage);
          iPos=getPosBegriff("Label","Code_eingeben");
          if (iPos<0)
            iPos=0;
          else
            fixtestError("Pos für Code_eingeben="+iPos);
          setSchrift2(iPos,TxtMemo,fontMessage);
          TxtMemo.setText(sMemo);
          PnlH.add("North", Scroll);  
        }
        Text EdtCode=new Text("",6);
        EdtCode.setBorder(new MatteBorder(0,0,1,0,Static.ColLinie));
        if (iPos>0)
          setSchrift2(iPos,EdtCode,fontMessage);
        // EdtCode.setBorder(new EmptyBorder(new Insets(2, 10, 2, 10)));
        JPanel PnlC=new JPanel(new GridLayout(1, 3));
        PnlC.setBackground(Color.WHITE);
        PnlC.add(new JLabel());
        PnlC.add(EdtCode);
        PnlC.add(new JLabel());
        PnlH.add("Center",PnlC);
        JLabel LblTime=new JLabel(" s");
        LblTime.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        PnlH.add("East",LblTime);
        JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        Pnl.setBackground(Color.WHITE);
        JButton BtnOk = getButton("Ok");
        BtnOk.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            Dlg.setVisible(false);
          }
        });
        //JButton BtnAbbruch = g.getButton("Abbruch","Abbruch",ALD);
        Dlg.getRootPane().setDefaultButton(BtnOk);
        Pnl.add(BtnOk);
        //Pnl.add(BtnAbbruch);
        PnlH.add("South", Pnl);
        Dlg.getContentPane().add("Center",PnlH);
        Dlg.setSize(600,200);
        Static.centerComponent(Dlg,Fom);
        // Timer damit automatisch nach 60 s geschlossen
        long lStop=Static.get_ms()+Static.iETimer*1000;
        timer= new Timer(1000,new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            long l=lStop-Static.get_ms();
            //progInfo("Message:"+l);
            if (l<0)
            {
              //timer.stop();
              Dlg.dispose();
            }
            else if (Dlg.isShowing())
            {
              Dlg.toFront();
              LblTime.setText((l/1000)+" s");
            }
          }
        });
        timer.start();
        Dlg.setVisible(true);
        timer.stop();
        boolean b=sCode.equals(EdtCode.getText());
        fixtestError("Code ist "+b);
        if (b)
          return true;
        else
        {
          Meldung(Fom,"Code_falsch","Der eingegebene Code ist falsch","Passwort");
          return false;
        }  
  }

  private void LoadTimePara(int iMandant)
  {
    Parameter Para = new Parameter(this,"System");
    Para.getMParameter("Time", iMandant);
      if (Para.bGefunden)
	    {
        // Static.iTT=Para.int1/60;
        // Static.iLTT=Para.int2;
        Static.iESleep=Para.int3;
        if (Static.iESleep==0)
          Static.iESleep=5;
        Static.iETimer=Para.int4;
      }
      else
      {
        // Static.iLTT=14;
        Static.iETimer=60;
      }
      //fixtestError("LoadTime: TT="+Static.iTT+", LTT="+Static.iLTT+", Sleep="+Static.iESleep+", Timer="+Static.iETimer);

      Para.getMParameter("Table", iMandant);
      if (Para.bGefunden)
	    {
        AUTable.iMaxZeilen=Para.int1;
        AUTable.iGaugeAb=Para.int2;
      }
      // else
      // {
      //   if (bG)
      //   {
      //     AUTable.iMaxZeilen=i2/1024;
      //     AUTable.iGaugeAb=i2%1024;
      //   }
      //   else
      //   {
      //     AUTable.iMaxZeilen=1000;
      //     AUTable.iGaugeAb=100;
      //   }
      // }
      Para.free();
  }

  public boolean isDef(String sName)
  {
    int iBits=SQL.getInteger(this,"select bits from benutzer where kennung="+S(sName,40))&cstBenutzerEbene;
    return iBits==cstProg || iBits==cstDef;
  }

	public boolean Login(JFrame Fom,String sKennung, String sPasswort)
        {
//          fixtestError("Login2");
		iLogFehler=0;	
          sUser=sKennung;
		//testInfo("Login");
		ComputerErmitteln();
        //Static.bBilder = rbBild;
		//bZeigeInfo = rbMemo;

		//bZeigeInfo = false;
		/*if (!bConnect)
			connect("");*/
		SQL Qry = new SQL(this);
		//CheckAbsturz();
                //iLog=-1;
		//checkConnects();
                iLog=0;
		iBenutzer = 0;
		if (sToken != null)
			sPasswort=null;

		boolean bOk1=false;
		if (iComputer == 0)
			bGesperrt = true;
		else
		{
                  if (Static.bMandantensuche && iMandant<=0 && sPasswort!=null)
                  {
                    String sBed=" where (use_bis is null or use_bis>"+now()+") and geloescht is null and kennung=" + S(sKennung,40) + (bAutoAll ? "": " AND" + bits("Benutzer.Bits", 3) + " in (0,3)");
                    int iAnz=SQL.getInteger(this,"select count(aic_benutzer) from benutzer"+sBed);
                    if (iAnz>1)
                      printError("Benutzer "+sKennung+" mehrfach vorhanden!");
                    else if (iAnz==0)
                      fixtestInfo("Benutzer "+sKennung+" nicht gefunden");
                    else
                      iMandant = SQL.getInteger(this, "select aic_mandant from benutzer"+sBed);
                  }
                  //fixInfo("Login mit Mandant="+iMandant+" und Kennung "+sKennung);
                  if (iMandant<=0)
                  {
                    printError("Mandant fehlt zum einloggen!");
                    return false;
                  }
                  String sMandant=getMandant(iMandant,"Kennung");
                  if (sMandant.equals(""))
                  {
                	  fixtestError("Mandant "+iMandant+" bereits Tod");
                	  return false;
                  }
                  LoadTimePara(iMandant);
                  fixInfo("Login:"+sKennung+ " mit Mandant=" + sMandant);        		
                         if(Qry.open("SELECT Kennung,Passwort,E_MAIL,aic_Mandant,AIC_Benutzer,Aic_Stamm,AIC_Sprache,AIC_Land,BITS,AIC_Benutzergruppe,(Select max(aic_logging) from logging where aus is null and not"+bit("status",WEBLOG)+//(Static.bWeb ? "="+WEBLOG:" is null")+
                        		 " and logging.aic_benutzer=benutzer.aic_benutzer and aic_code"+(iAnlage==0?" is null":"="+iAnlage)+") Log"+AU_Bezeichnung("Mandant","Benutzer")+
                                     " Mandant FROM Benutzer WHERE (use_bis is null or use_bis>"+now()+") and geloescht is null and aic_mandant="+iMandant+" and Kennung="+S(sKennung,40)))
			{
				//printInfo('>'+Qry.getS("Kennung")+"</>"+Qry.getS("Passwort")+'<');
                String sPW=Qry.getS("Passwort");
                int iBenEbene=Qry.getI("BITS")&cstBenutzerEbene;
			   	if(!Qry.eof() && (!Static.bLNC || Qry.getS("Kennung").equals(sKennung)) && (sToken != null && checkToken(sToken,sKennung) ||
			   			sToken==null && sPasswort==null && (iBenEbene==cstNormalUser || iBenEbene==cstUserManager) || sPasswort!=null && PWok(sPasswort,Qry.getI("bits"),Qry.getI("aic_benutzer"),sPW)))
			   	{
            boolean b2FOk=check2F(Fom,Qry);
            if (!b2FOk)
            {
              fixtestError("2-Faktor-Code falsch");
              return false;
            }
            iFehlLogs=0;
            iBits=Qry.getI("BITS");
            if ((iBits&cstNoLogin)>0)
            {
              printError("Benutzer "+sKennung+" nicht zum einloggen gedacht");
              iBits=0;
              iBenutzer = 0;
            }
            else
            {
              int iLog=Qry.getI("Log");
              if (iAnlage==0 && iLog>0 && (iBits&cstMehrmals)==0 && new Message(Message.YES_NO_OPTION, Fom, this,20).showDialog("BereitsEingeloggt")==Message.YES_OPTION)
              {
                int iAnz=exec("update logging set aus="+now()+",status="+WIEDER+" where aus is null and aic_logging="+iLog);
                if (iAnz>0)
                  fixtestError("Ausgeloggt: "+iLog);
                iLog=0;
              }          
              if (iLog==0 || (iBits&cstMehrmals)>0 && !OhneLizenz())
              {
                iBenutzer=Qry.getI("AIC_Benutzer");
                setStamm(Qry.getI("Aic_Stamm"));
                iBenutzergruppe= Qry.getI("AIC_Benutzergruppe");
                int iSpracheMom=Qry.getI("AIC_Sprache");
                int iLandMom=Qry.getI("AIC_Land");
                checkIndividuell(iSpracheMom,iMandant);
                setSprache(iSpracheMom,iLandMom);
                setLand(iLandMom,-1);
                if ((iBits&cstDB)>0)
                {
                    unConnect();
                    sDbUser=UserManager.getDbUser(this, sKennung);//sMandant+"*"+sKennung;
                    //fixtestInfo("DbUser="+sDbUser);
                    sDbPW=sPW;
                    connect(getJDBC());
                }
                bOk1=writeLoginInfo();
                checkAServer();
                if (bOk1)
                {
                  if (fillVecMandant(true))
                    fillBerechtigung(0);
                  else
                    System.exit(15);
                }
                if (!existsSprache(iSpracheMom))
                  setSprache(1,iLandMom);
                if (!bOk1 && Def())
                {
                  if (Static.bX11)
                    JOptionPane.showMessageDialog(Fom,"Einloggen fehlerhaft, Def darf weiterarbeiten","Fehler",JOptionPane.ERROR_MESSAGE);
                  else
                    printError("Einloggen fehlerhaft");
                  bOk1 = true;
                }
              }
              else
              {               
                iBits=0;
                iBenutzer = -1;
                printError("Benutzer bereits eingeloggt:"+sKennung);
                diskInfo("Global.Login(): Benutzer bereits eingeloggt !");
                //addInfo("Benutzer bereits eingeloggt !!!");
              }
            }
			   	}
			   	else
                                {
                                  if (!Qry.eof() && Qry.getS("Kennung").equals(sKennung))
                                    fixInfo("Passwort falsch");
                                  checkFehlLogs();
                                }
			}
		}
		Qry.free();
                printSI("Login");
		return(bOk1);
    }

    public boolean Login(JFrame Fom,String sMandant, String sPasswortM,String sKennung, String sPasswort,boolean bEintrag)
    {
      // fixtestError("Login3");
      sUser=sKennung;
		//bZeigeInfo = false;
		/*if (!bConnect)
			connect("");*/
		boolean bOk1=false;
                //if (sMandant.length()>40)
                //  sMandant=sMandant.substring(0,40);
		SQL Qry = new SQL(this);
		Qry.open("SELECT AIC_Mandant FROM Mandant WHERE Kennung='"+sMandant+"' AND Passwort='"+PasswordConvert(sPasswortM,PWR,0)+"'");
	   	if(!Qry.eof())
	    {
			iMandant=Qry.getI("AIC_Mandant");
			fixtestInfo("Mandant "+sMandant+" gefunden:"+iMandant);
			bOk1=true;
	   	}
		else
			checkFehlLogs();
		Qry.free();

		if(bOk1)
			bOk1 = Login(Fom,sKennung,sPasswort);

		if (bEintrag && bOk1)
			exec("Update Computer set aic_mandant="+iMandant+" where aic_computer ="+iComputer);

		return(bOk1);
    }//Login

    public void SaveAbfragen(Parameter P,String sArt,Tabellenspeicher Tab)
    {
      //Tab.showGrid("Save "+sArt);
      P.ErmittleParameter(sArt,false);
      for(int iPos=0;iPos<Tab.size();iPos++)
        if (!Tab.isNull(iPos,"Status"))
        {
          if(Tab.getS(iPos,"Status").equals("del"))
            exec("delete from parameter where aic_parameter="+Tab.getI(iPos,"aic_parameter"));
          else
          {
            P.add("Aic_Benutzer",getBenutzer());
            P.addGruppen();
            if (sArt.equals("Filter") || sArt.equals("Spalten"))
              P.add("Parameterzeile",Tab.getS(iPos,"Parameterzeile"));
            P.add("int1",Tab.getI(iPos,"int1"));
            P.add("int2",Tab.getI(iPos,"int2"));
            P.add("int3",Tab.getI(iPos,"int3"));
            P.add("int4",Tab.getI(iPos,"int4"));
            P.add("aic_logging",iLog);
            if (Tab.getI(iPos,"aic_parameter")==0)
              P.insert("parameter",false);
            else
              P.update("parameter",Tab.getI(iPos,"aic_parameter"));
          }
        }
    }   
    
    public void Logout()
    {
    	if (iLog > 0)
    	{
	    	Qry.addnow("AUS");
	        Qry.update("Logging",iLog);
	        iLog = 0;
	        Qry.free();           
	        unConnect();
	        sUser=null;
    	}
    }

	public void Logout(boolean bInit)
	{
//		setJavaFX(false);
		if (iLog > 0)
		{
			fixInfo("Logout "+iLog);
                  boolean bNormal=(iInfos&FAST_OUT)==0;
                  if (bNormal)
                  {
                    Parameter P = new Parameter(this, "Formular");
                    //P.setBParameter("Abfrage", TabAbfragen);
                    //SaveAbfragen(P,"Abfrage",TabUserAbfragen);
                    //P.setBParameter("Filter", TabFilter);
                    SaveAbfragen(P,"Filter", TabFilter);
                    SaveAbfragen(P,"Abfrage2", TabPersAbfragen);
                    //SaveAbfragen(P,"Joker", TabPersJoker);
                    SaveAbfragen(P,"GruppenFilter", TabGF);   // Gruppenfilter für JavaFX
                    SaveAbfragen(P,"Spalten", TabPos); // Position/Breite der Spalte in JavaFX-Tabelle
                    saveSplitPos(P);
                    P.free();
                    Static.clearCache("pdf");
                  }
                    TabAmpel.clearAll();
                    if (TabMeine!=null)
                      TabMeine.clearAll();
                    SQL.clearTabImage(this);
			iBenutzer = 0;
                        iBenutzergruppe = 0;
			//VecBG=null;
			//bHistory = false;
			//bAbfrage = false;
			//bReadOnly= true;
                        iBG_Bits=cstReadOnly;
                        //fixInfo("*** Logout-> iBG=0, iBG_Bits="+iBG_Bits);
			setStamm(0);
                        iStammHaupt=0;
                        sUserStamm=null;
                        //iStt = 0;
			//SQL.exec(this,"Update Logging set AUS=now() where aic_Logging ="+iLog);
                        //SQL Qry=new SQL(this);
                        
                        int iSperren=exec("update begriff set log_aic_logging=null where log_aic_logging="+iLog);
                        iSperren+=Qry.deleteFrom("Sperre","sperre join protokoll p on sperre.aic_protokoll=p.aic_protokoll where p.aic_logging="+iLog);
                        //iSperren+=exec("update bew_pool set bewSperre=null where BewSperre="+iLog); // unnötig; braucht sehr lange
                        if (iSperren > 0)
                          fixtestInfo(iSperren + " eigene Sperren entfernt");

                        if (bNormal)
                          saveFenster();
			
                        if (bInit && bNormal)
                        {
                          setSprache(0, 0);
                          setLand(iStdLand, iStdWaehrung);
                        }
			iBits=0;
                        if (Static.bMandantensuche)
                          iMandant=0;
			//testInfo("iBits="+iBits+", iBenutzer="+iBenutzer);
			//bProg = false;
			//bDef = false;
                        //int iAnz=SQL.getInteger(this,"select count(*) from logging where aus is null and aic_code is null");
                        Static.FomStart=null;
                        if (!Static.bWeb && Static.bND)
                        	LoadSchrift(false,100);                        
			Logout();
			bHS=false;
			sDbUser=null;
        	sDbPW=null;
                        
//                        return 66;//iAnz;
		}
		if (Static.bX11)	  
				Favorit.clear();
		Static.bAutoKonsole=false;
			
//                else
//                  return -1;
	}//Logout

        public void setProg()
        {
          fixInfo("setProg");
          if ((iBits & cstSpezial) > 0) iBits-=cstSpezial;
          iBits+=cstProg-(iBits&cstBenutzerEbene);//(iBits&0xFFF8)+1;
          fixtestInfo("iBits="+iBits);
        }
        public void setDef()
        {
          fixInfo("setDef");
          if ((iBits & cstSpezial) > 0) iBits-=cstSpezial;
          iBits+=cstDef-(iBits&cstBenutzerEbene);//iBits=(iBits&0xFFF8)+2;
          fixtestInfo("iBits="+iBits);
        }
        public void setVerw()
        {
          fixInfo("setVerw");
          iBits+=cstVerw-(iBits&cstBenutzerEbene);//iBits=(iBits&0xFFF8)+cstVerw;
          fixtestInfo("iBits="+iBits);
        }
        public void setSU()
        {
          fixInfo("setSU");
          iBits+=cstSuperUser-(iBits&cstBenutzerEbene);//iBits=(iBits&0xFFF8)+3;
          fixtestInfo("iBits="+iBits);
        }
        public void setSpezial()
        {
          fixInfo("setSpezial");
          if (SuperUser())
        	  iBits+=cstUserManager-(iBits&cstBenutzerEbene);//iBits=(iBits&0xFFF8);
          if (!Spezial())
            iBits+=cstSpezial;
          fixtestInfo("iBits="+iBits);
        }
        public void setUM()
        {
          fixInfo("setUM");
          //iBits=(iBits&0xFFF8);
          //if ((iBits&cstUserManager)==0)
              iBits+=cstUserManager-(iBits&cstBenutzerEbene);
              fixtestInfo("iBits="+iBits);
        }
        public void setNormal()
        {
          fixInfo("setNormal");
          iBits=(iBits&0xFFF8);
        }
        
        public int getCodeAic(String sGruppe,String sKennung)
        {
        	return getCodeAic(sGruppe,sKennung,false);
        }

        public int getCodeAic(String sGruppe,String sKennung,boolean bProgAnlegen)
        {
          if (Static.Leer(sKennung))
        	return 0;
          int iAic=getBegriffAicS(sGruppe,sKennung);
          if (iAic<=0)
          {
            fixtestInfo("suche "+sGruppe+" "+sKennung);
            int iBG=TabBegriffgruppen.getAic(sGruppe);
            if (iBG<=0)
              iBG=SQL.getInteger(this,"select aic_begriffgruppe from begriffgruppe where kennung='"+sGruppe+"'");
            iAic=SQL.getInteger(this,"select aic_code from code where aic_begriffgruppe="+iBG+" and kennung='"+sKennung+"'");
            if (iAic<=0 && (bProgAnlegen || !sGruppe.toLowerCase().equals("programm")))
            {
              diskInfo("lege "+sGruppe+" "+sKennung+" an");
              SQL Qry = new SQL(this);
              Qry.add("kennung", sKennung);
              Qry.add("aic_begriffgruppe", iBG);
              iAic = Qry.insert("code", true);
              Qry.free();
              refreshCodes();
            }
          }
          return iAic;
        }

        public int getModul(String sKennung,String sProg)
        {
          String sGruppe="Modul";
          int iAic=getBegriffAicS(sGruppe,sKennung);
          if (iAic<=0)
          {
            fixtestInfo("suche "+sGruppe+" "+sKennung);
            int iBG=TabBegriffgruppen.getAic(sGruppe);
            if (iBG<=0)
              iBG=SQL.getInteger(this,"select aic_begriffgruppe from begriffgruppe where kennung='"+sGruppe+"'");
            iAic=SQL.getInteger(this,"select aic_begriff from begriff where aic_begriffgruppe="+iBG+" and kennung='"+sKennung+"'");
            if (iAic<=0 && iBG>0)
            {
              diskInfo("lege Modul "+sKennung+" an");
              SQL Qry = new SQL(this);
              Qry.add("kennung", sKennung);
              Qry.add("aic_begriffgruppe", iBG);
              Qry.add("aic_code",getCodeAic("Programm",sProg,true));
              iAic = Qry.insert("begriff", true);
              Qry.free();
              //refreshCodes();
            }
          }
          return iAic;
        }

	public int Protokoll(String sAnlage,int iPlace)
	{
          return Protokoll(getCodeAic("Anlage",sAnlage),iPlace);
	}

	public int Protokoll(int riAnlage,int iPlace)
	{
		SQL Insert = new SQL(this);
		//Insert.add("AIC_Computer",iComputer);
		//Insert.add("AIC_Benutzer",iBenutzer);
		Insert.add("AIC_Logging",iLog);
                Insert.add("AIC_Benutzer",iBenutzer);
		Insert.add("AIC_CODE",riAnlage);
                if (iPlace>0)
                  Insert.add("PLACE",iPlace);
                //if (MS())
                //  Insert.addnow("Timestamp");
		int i=Insert.insert("Protokoll",true);
                testInfo("erstelle Protokoll "+i+": Benutzer="+iBenutzer+", Log="+iLog);
                lLastProt=Static.get_ms();
		Insert.free();
		return i;
	}

        public boolean AutoLogout()
        {
          return Static.iAutoAus>0 && iLog>0 && (Static.get_ms()-lLastProt)>Static.iAutoAus*1000;
        }
    /**
     * TimeToString(java.sql.Time) Method
     */

    public String TimeToString(java.sql.Time time)
    {
        return(new SimpleDateFormat("HH:mm:ss").format(time));
    }
    /*public void setImage(Image Img,Image ImgOld, String sFile, int iTab, int iFremd, int riSprache)
    {
		if (riSprache == 0)
			riSprache = iSprache;

		if(!Static.Gleich(Img,ImgOld))
		{
			if(Img==null)
			{
				SQL.exec(this,"DELETE FROM Daten_Bild WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache);
				//debugInfo("lösche Bild "+sFile);
			}
			else if(ImgOld==null)
			{
				SQL.exec(this,"INSERT INTO Daten_Bild (AIC_Tabellenname, AIC_Fremd, AIC_Sprache, Filename) VALUES ("+iTab+","+iFremd+","+riSprache+",'"+sFile+"')");
				//debugInfo("erzeuge Bild "+sFile);
			}
			else
			{
				SQL.exec(this,"UPDATE Daten_Bild SET Filename='"+sFile+"' WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache);
				//debugInfo("ändere Bild "+sFile);
			}
		}
		//else
			//debugInfo("mache nichts mit Bild "+sFile);
	}*/

    public void setImage(String sFile, int iTab, int iFremd, int riZustand)
    {
		if (riZustand == 0)
			riZustand = iSpSw;
		if(SQL.exists(this,"select AIC_Fremd from Daten_Bild where aic_tabellenname="+iTab+" and aic_fremd="+iFremd+" and aic_Zustand="+riZustand))
		{
			if(sFile.equals(""))
			{
				exec("DELETE FROM Daten_Bild WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND aic_Zustand="+riZustand);
				//progInfo("lösche Bild:<"+sFile+">");
			}
			else
			{
				exec("UPDATE Daten_Bild SET Filename='"+sFile+"' WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND aic_Zustand="+riZustand);
				//progInfo("ändere Bild:<"+sFile+">");
			}
		}
		else if(!sFile.equals(""))
		{
			exec("INSERT INTO Daten_Bild (AIC_Tabellenname, AIC_Fremd, aic_Zustand, Filename) VALUES ("+iTab+","+iFremd+","+riZustand+",'"+sFile+"')");
			//progInfo("erzeuge Bild:<"+sFile+">");
		}
		//else
			//debugInfo("mache nichts mit Bild "+sFile);
	}

        public void setTooltip(String sTooltip, int iTab, int iFremd, int riSprache)
        {
          //progInfo("setTooltip:"+iTab+"/"+iFremd+"/"+riSprache+":"+sTooltip);
          if (riSprache == 0)
            riSprache = iSprache;
          String sVorher=SQL.getString(this,"select Memo2 from Tooltip WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache);
          if ((sVorher == null || sVorher.equals("")) && !sTooltip.equals(""))
            exec("insert into tooltip (aic_tabellenname,aic_sprache,aic_fremd,memo2) values ("+iTab+","+riSprache+","+iFremd+","+Static.StringForSQL(sTooltip)+")");
          else if (sVorher != null && !sVorher.equals(""))
            if (sTooltip.equals(""))
              exec("delete from tooltip where aic_tabellenname="+iTab+" and aic_sprache="+riSprache+" and aic_fremd="+iFremd);
            else if (!sTooltip.equals(sVorher))
              exec("update tooltip set memo2="+Static.StringForSQL(sTooltip)+" where aic_tabellenname="+iTab+
                           " and aic_sprache="+riSprache+" and aic_fremd="+iFremd);
        }

    public void setMemo(String sMemo, String sMemoTitel, int iTab, int iFremd, int riSprache)
    {
    	setMemo(sMemo,sMemoTitel,null,iTab,iFremd,riSprache);
    }
        
	public void setMemo(String sMemo, String sMemoTitel, String sHeader, int iTab, int iFremd, int riSprache)
    {
		//fixtestInfo("setMemo:"+iTab+"/"+iFremd+":"+sMemoTitel+"/"+sHeader);
		if (riSprache == 0)
			riSprache = iSprache;
                if (sMemoTitel != null && sMemoTitel.length()>40)
                  sMemoTitel=sMemoTitel.substring(0,40);
        boolean bHeader=!Static.Leer(sHeader);
		if (SQL.exists(this,"select AIC_Fremd from Daten_Memo WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache))
		{
			if(sMemoTitel.equals("") && sMemo.equals(""))
			{
				exec("DELETE FROM Daten_Memo WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache);
				//saveInfo("Memo gelöscht");
			}
			else
			{
                          if (!ASA())
                            exec("UPDATE Daten_Memo SET Titel='" + sMemoTitel + "',Memo="+Static.StringForSQL(sMemo)+",Header="+(bHeader ? Static.StringForSQL(sHeader):"null")+" WHERE AIC_Tabellenname=" + iTab + " AND AIC_Fremd=" + iFremd + " AND AIC_Sprache=" + riSprache);
                          else
                          {
                            exec("UPDATE Daten_Memo SET Titel='" + sMemoTitel + ",Header="+(bHeader ? Static.StringForSQL(sHeader):"null") + "' WHERE AIC_Tabellenname=" + iTab + " AND AIC_Fremd=" + iFremd + " AND AIC_Sprache=" + riSprache);
                            writetext("Daten_Memo", "Memo", "AIC_Tabellenname=" + iTab + " AND AIC_Fremd=" + iFremd + " AND AIC_Sprache=" + riSprache, sMemo);
                          }
                          //saveInfo("Memo geändert");
			}
		}
		else if (!sMemo.equals("") || !sMemoTitel.equals(""))
		{
                  if (!ASA())
                    exec("INSERT INTO Daten_Memo (AIC_Tabellenname, AIC_Fremd, AIC_Sprache, Titel,Memo"+(bHeader ?",Header":"")+
                    		") VALUES (" + iTab + "," + iFremd + "," + riSprache + ",'" + sMemoTitel + "',"+Static.StringForSQL(sMemo)+(bHeader ? ","+Static.StringForSQL(sHeader):"")+")");
                  else
                  {
                    exec("INSERT INTO Daten_Memo (AIC_Tabellenname, AIC_Fremd, AIC_Sprache, Titel"+(bHeader ?",Header":"")+
                    		") VALUES (" + iTab + "," + iFremd + "," + riSprache + ",'" + sMemoTitel +"'"+ (bHeader ? ","+Static.StringForSQL(sHeader):"")+")");
                    if (!sMemo.equals(""))
                      writetext("Daten_Memo", "Memo", "AIC_Tabellenname=" + iTab + " AND AIC_Fremd=" + iFremd + " AND AIC_Sprache=" + riSprache, sMemo);
                  }
                  //saveInfo("Memo hinzugefügt");
		}
	}
    /*
	public void setMemo(String sMemo, String sMemoTitelVor, String sMemoTitelNach, int iTab, int iFremd, int riSprache)
    {
		printError("Altes setMemo sollte nicht mehr verwendet werden!");
		if (riSprache == 0)
			riSprache = iSprache;

        if(!sMemoTitelVor.equals(sMemoTitelNach) || !sMemo.equals(""))
		{
			if(sMemoTitelNach.equals(""))
				SQL.exec(this,"DELETE FROM Daten_Memo WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache);
			else
			{
				if(sMemoTitelVor.equals(""))
					SQL.exec(this,"INSERT INTO Daten_Memo (AIC_Tabellenname, AIC_Fremd, AIC_Sprache, Titel) VALUES ("+iTab+","+iFremd+","+riSprache+",'"+sMemoTitelNach+"')");
				else
					SQL.exec(this,"UPDATE Daten_Memo SET Titel='"+sMemoTitelNach+"' WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache);
				writetext("Daten_Memo","Memo","AIC_Tabellenname="+iTab+" AND AIC_Fremd="+iFremd+" AND AIC_Sprache="+riSprache,sMemo);
			}
		}
	}*/

	public boolean writetext(String sTable_Name, String sColumn_Name, String sBedingung, String sText)
	{
		boolean bOk = false;
		exec("CREATE VARIABLE Textpointer BINARY(16)");

		if(exec("SET Textpointer = (SELECT TEXTPTR("+sColumn_Name+") FROM "+sTable_Name+" WHERE "+sBedingung+")")>-2)
			bOk = exec("WRITETEXT "+sTable_Name+"."+sColumn_Name+" Textpointer "+Static.StringForSQL(sText))>-2;
		exec("DROP VARIABLE Textpointer");


		if(!bOk)
			Static.printError("Global.writetext: Konnte den Text <"+sText+"> in "+sTable_Name+"."+sColumn_Name+" nicht schreiben!");

		return (bOk);
	}
	/*public boolean Def()
    {
        return(bDef);
    }*/

	/*public boolean ReadOnly()
    {
        return(bReadOnly);
    }*/

    public int getBenutzergruppe()
    {
        return(iBenutzergruppe);
    }

    public void setWorkflow()
    {
      fixInfo("setWorkflow");
      iInfos|=Transact.WORKFLOW;
    }
    
    public void setWorkflowZR()
    {
      fixInfo("setWorkflowZR");
      iInfos|=Transact.WFZR;
    }

    public void setReadOnly()
    {
      fixInfo("setReadOnly");
      iBits=(iBits&0xFFF8);
      iBG_Bits=cstReadOnly;
      /*if (b)
        iBG_Bits|=cstReadOnly;
      else if((iBG_Bits & cstReadOnly)>0)
        iBG_Bits-=cstReadOnly;*/
    }
    
    public boolean Grant()
    {
    	return (iBits&3)==1 || (iBits&3)==2; // Prog oder Def; //Grant(iBits);
    }
    
//    public static boolean Grant(int iBits) // bestimmt ob man Berechtigungs-Berechtigung besitzt
//    {
//    	return (iBits&cstBenutzerEbene)>0;//(iBits&3)==1 || (iBits&3)==2; // Prog oder Def;
//    }

	public boolean Abfrage()
	{
		return SuperUser() || (iBG_Bits & cstAbfrage)>0;
	}

	public boolean History()
	{
		return SuperUser() || (iBG_Bits & cstHistory)>0;
	}

	public boolean ReadOnly()
	{
		return !SuperUser() && (iBG_Bits & cstReadOnly)>0;
	}

        public boolean NurFilter()
        {
          //testInfo("Def="+Def()+",iBG_Bits="+iBG_Bits+",NurFilter="+(!Def() && (iBG_Bits & cstNurFilter)>0));
                return !SuperUser() && (iBG_Bits & cstNurFilter)>0;
        }

        public boolean Workflow()
        {
                return (iInfos & Transact.WORKFLOW) > 0 || !SuperUser() && (iBG_Bits & cstWorkflow)>0;
        }
        
        public static boolean XOR(boolean b,boolean b2)
        {
        	return b && !b2 || b2 && !b;
        }
        
        public boolean WorkflowZR()
        {
                return (iInfos & Transact.WFZR) > 0 || !SuperUser() && XOR((iBG_Bits & cstWorkflow)>0,(iBG_Bits & cstWorkflowZR)>0);
        }

        public boolean Geloeschte()
        {
                return SuperUser() || (iBG_Bits & cstGeloeschte)>0;
        }

        public boolean UserManager()
        {
          return SuperUser() || (iBits&cstBenutzerEbene)==cstUserManager;
        }
        
        public boolean HS()
        {
        	return HS(false);
        }

        public boolean HS(boolean bAufruf)
        {
//          fixtestError("HS-"+bAufruf+": bHS="+bHS+", ohne="+OhneLizenz());
          if (OhneLizenz() || bHS)
        	  return true;
          boolean bOk=UserManager() || (iBits&cstHS)>0;
          if (bAufruf && bOk)
          {
        	  //TODO Prüfung auf Admin-Lizenz
        	  int iMom=MomConnect();
        	  int iMax=MaxConnect();
              fixInfo((iMom+1)+". von "+iMax+" erlaubten Verbindungen");
            if (iMax!=-1 && iMom>=iMax)
            {
              //JOptionPane.showMessageDialog(new JFrame(), "User-Lizenz erreicht", "Lizenz", JOptionPane.WARNING_MESSAGE);
              return false;
            }
            else
            {
            	int iAnz=exec("update logging set status="+ADMIN+" where aic_logging="+getLog());
            	if (iAnz<1)
            		fixtestError("admin gesetzt: "+iAnz);
            	bHS=true;
            }
          }
          return bOk;
        }
        
  public boolean isLDAP()
  {
    return (iBits&cstPW)==cstPW_LDAP;
  }

  public boolean ohnePW()
  {
    return false;//(iBits&cstPW)==cstPW_KEIN;
  }

	public boolean checkBG(int iBG)
	{
		return VecBG.contains(new Integer(iBG));
	}

	public int getHBG()
	{
		return iBenutzergruppe>0 ? iBenutzergruppe:VecBG.size()==0 ? 0:((Integer)VecBG.elementAt(0)).intValue();
	}

	public boolean VecNull(String s,Vector Vec)
	{
		if (Vec==null || Vec.size()==0)
		{
			printInfo(1,s+" ist leer!");
			return true;
		}
		else
			return false;
	}

	public String getAllBG()
	{
		return "aic_benutzergruppe"+(VecNull("getAllBG: VecBG",VecBG)?"=-1":Static.SQL_in(VecBG));
	}
	
	public int getStartFom()
	{
		String sBG=getBenutzergruppe()>0 ? "aic_benutzergruppe="+getBenutzergruppe():getAllBG();
		int iFormular=SQL.getInteger(this,"select formular.aic_formular from begriff join benutzergruppe bg on begriff.aic_begriff=bg.aic_begriff and begriff.web is null join formular on bg.aic_begriff=formular.aic_begriff where bg."+sBG);
		if (iFormular==0)
			iFormular=SQL.getInteger(this,"select formular.aic_formular from mandant join formular on mandant.aic_begriff=formular.aic_begriff where mandant.aic_mandant="+getMandant());
		return iFormular;
	}
	/*public String getEigenschaften(boolean bWhere, String sTable)
	{
		return SuperUser() ? "" : (bWhere ? " where ":" and ")+Qry.in((sTable.equals("")?"":sTable+".")+"aic_eigenschaft",VecEigenschaften);
                    //"aic_eigenschaft"+(VecNull("getEigenschaften: VecEigenschaften",VecEigenschaften)?"=-1":Static.SQL_in(VecEigenschaften));
	}*/

        public boolean isJederEig(Object Obj)
        {
          int iPos=TabEigenschaften.getPos("Aic",Obj);
          return iPos>=0 && (TabEigenschaften.getI(iPos,"Bits")&cstJeder)>0;//VecJederEig.contains(Obj);
        }

        /*public boolean isLizEig(Object Obj)
        {
          return VecLizEig.contains(Obj);
        }*/

        public Color getColLizEig(int iPosE)
        {
          //int iAic=TabEigenschaften.getI(iPosE,"Aic");
          boolean bLiz=(TabEigenschaften.getI(iPosE,"Bits")&cstEigLizenz)>0;
          boolean bJeder=(TabEigenschaften.getI(iPosE,"Bits")&cstJeder)>0;//isJederEig(iAic);
          return bJeder ? bLiz ? Color.RED:null:bLiz ? Color.YELLOW:Color.WHITE;
        }

        private boolean pruefePersEig(Object Obj)
        {
          int iPos=TabEigenschaften.getPos("Aic",Obj);
          boolean b=VecEigenschaften.contains(Obj);
          testInfo(" prüfe "+(iPos<0?"???":TabEigenschaften.getS(iPos,"Bezeichnung"))+"->"+b);
          return b;
        }

        public boolean existsHS(int iHS)
        {
          return Def() || !Static.bLizenz || VecLizHS.contains(iHS);
        }

        public boolean existsSprache(int iSprache)
        {
          return Def() || !Static.bLizenz || iSprache==1 || SuperUser() && AllUnlimited() || VecLizSprache.contains(iSprache);
        }

	public boolean existsEigenschaft(Object Obj)
	{
		if (Static.bInfoTod) 
			fixInfo("prüfe Eig="+Obj+"/"+TabEigenschaften.getBezeichnungS((int)Obj));
          int iPos=TabEigenschaften.getPos("Aic",Obj);
          if (iPos<0 || EigIsTod(Obj))
          {
        	if (Static.bInfoTod)  
          		fixInfo("   !!! Eigenschaft "+(iPos<0 ? "mit Aic "+Obj+" nicht gefunden":TabEigenschaften.getS(iPos,"Bezeichnung")+" ist Tod")+"!!!");
            return false;
          }
          if (VecEigNot != null && VecEigNot.contains(Obj))
          {
        	  if (Static.bInfoTod)  
          		fixInfo("   !!! Eigenschaft "+(iPos<0 ? "mit Aic "+Obj+" nicht gefunden":TabEigenschaften.getS(iPos,"Bezeichnung")+" ist Verboten")+"!!!");
              return false;
          }
          if (!Static.bDefShow && VecEigNicht != null && VecEigNicht.contains(Obj))
          {
        	  if (Static.bInfoTod)  
          		fixInfo("   !!! Eigenschaft "+(iPos<0 ? "mit Aic "+Obj+" nicht gefunden":TabEigenschaften.getS(iPos,"Bezeichnung")+" persönlich nicht erlaubt")+"!!!");
              return false;
          }
          boolean bLiz=(TabEigenschaften.getI(iPos,"Bits")&cstEigLizenz)>0;
          boolean bJeder=(TabEigenschaften.getI(iPos,"Bits")&cstJeder)>0;
          if (Static.bNurJeder)
          {
            if (bJeder && !bLiz)
              return true;
            if (Static.bInfoTod)  
        		fixInfo("   !!! Eigenschaft "+TabEigenschaften.getS(iPos,"Bezeichnung")+": Jeder="+bJeder+", Lizenz="+bLiz+"!!!");
            return false;
          }
          if (Static.bInfoTod) 
  			fixInfo("-> Jeder="+bJeder+", Liz="+bLiz+", VecLiz="+VecLizEigenschaften);
          boolean b= AllUnlimited() || bJeder && !bLiz || bLiz && (bJeder || SuperUser() || pruefePersEig(Obj)) && (!Static.bLizenz || VecLizEigenschaften.contains(Obj));

		//boolean b= Static.bNurJeder ? VecJederEig.contains(Obj): SuperUser() || VecEigenschaften.contains(Obj);
                //fixtestInfo("existsEigenschaft "+Obj+":"+b);
                return b;
	}

	/*public boolean Berechtigung()
	{
          if (isTod(TabBegriffe.getInhalt("aic")))
            return false;
          return SuperUser() || isJeder()|| VecBegBerechtigt==null || VecBegBerechtigt.contains(TabBegriffe.getInhalt("aic"));//TabBegBerechtigt.posInhalt("Aic",TabBegriffe.getInhalt("aic"));
	}*/

        /*public boolean Berechtigung(int iBegriff)
        {
          fixtestInfo("alte Berechtigung nicht mehr erlaubt:"+getBegBez(iBegriff));
          Integer Int=new Integer(iBegriff);
          if (isTod(Int))
            return false;
          return SuperUser() || isJeder(Int) || VecBegBerechtigt==null || VecBegBerechtigt.contains(Int);
        }*/

	public boolean BerechtigungS(int iPos)
	{
//		if (iPos>=0 && TabBegriffe.getI(iPos,"Gruppe")==TabBegriffgruppen.getAic("Frame"))
//			fixtestError("BerechtigungS: "+iPos+" ("+getBegBez3(iPos)+") ist "+TabBegriffgruppen.getKennung(TabBegriffe.getI(iPos,"Gruppe")));
          if (iPos<0)
            return false;
          else if (isTod(TabBegriffe.getInhalt("aic",iPos)))
          {
        	  if (Static.bInfoTod)  
          		fixInfo("Begriff <"+TabBegriffe.getS(iPos,"Bezeichnung")+"> ist Tod");
            return false;
          }
          //TODO nur Formulare auf Web prüfen
          else if (isWeb(TabBegriffe.getInhalt("aic",iPos)) != Static.bWeb && TabBegriffe.getI(iPos,"Typ")==0 && TabBegriffe.getI(iPos,"Gruppe")==TabBegriffgruppen.getAic("Frame"))
          {
        	  if (Static.bInfoTod)  
          		fixInfo("Begriff <"+TabBegriffe.getS(iPos,"Bezeichnung")+"> ist "+(Static.bWeb ? "nicht ":"")+"web");
            return false;
          }
          else if(!Lizenz(iPos))
            return false;
          if (Static.bInfoTod) 
        	  fixInfo("Begriff <"+TabBegriffe.getS(iPos,"Bezeichnung")+"> Lizenz ist ok, VecBegBerechtigt="+VecBegBerechtigt);
          boolean b= SuperUser() || isJederS(iPos) || VecBegBerechtigt==null || VecBegBerechtigt.contains(TabBegriffe.getInhalt("aic",iPos));
//          fixtestError("SU="+SuperUser()+", Jeder="+isJederS(iPos)+"->"+b+", VecBegBerechtigt="+VecBegBerechtigt);         
          return b;
	}

	public boolean NurSelbst(int iBegriff)
	{
		return !SuperUser() && !VecBegJeder.contains(new Integer(iBegriff)) && VecNurSelbst.contains(new Integer(iBegriff));
			//&& TabBegBerechtigt.posInhalt("Aic",iBegriff) && (TabBegBerechtigt.getI("bits")&2)>0;
	}

	public String getReadMandanten()
	{
		return getReadMandanten(false,"p2");
	}

    public String getMandanten(int iStt)
    {
      int iPos=TabStammtypen.getPos("Aic",iStt);
      boolean bCopy=iPos>=0 && (TabStammtypen.getI(iPos,"bits")&cstCopy)>0;
      return bCopy ? getCopyMandanten(false):getReadMandanten();
    }
    
    public boolean hasZone(int iBew)
    {
    	boolean b= iBew>0 && (getBewBits(iBew)&cstBewZone)>0;
//    	fixtestError("hasZone von "+iBew+"="+b);
    	return b;
    }

	public int getBewBits(int iBew)
	{
	  int iPos=TabErfassungstypen.getPos("aic",iBew);
	  if (iPos>=0)
	    return TabErfassungstypen.getI(iPos,"bits");
	  else
	  {
	    printError("Global.getBewBits: Bewegungstyp "+iBew+" nicht gefunden");
	    return 0;
	  }
	}

        public int getSttBits(int iStt)
        {
          int iPos=TabStammtypen.getPos("aic",iStt);
          if (iPos>=0)
            return TabStammtypen.getI(iPos,"bits");
          else
          {
            printError("Global.getSttBits: Stammtyp "+iStt+" nicht gefunden");
            return 0;
          }
        }

	public String getReadMandanten(int iBew)
	{
		//String s=iMandant==1 ? "" : (bWhere ? " where":" and")+" p2.aic_Mandant"+(VecNull("getReadMandanten: VecMandantRead",VecMandantRead)?"=-1":Static.SQL_in(VecMandantRead));
		//progInfo("getReadMandanten:"+s);
		boolean bKeinMandant=false;
		if (iBew>0)
		{
			//TabErfassungstypen.push();
			//if (TabErfassungstypen.posInhalt("aic",iBew) && (TabErfassungstypen.getI("bits")&cstMandantIgnorieren)>0)
		  if ((getBewBits(iBew)&cstMandantIgnorieren)>0)
				bKeinMandant=true;
			//TabErfassungstypen.pop();
		}
                String s=bKeinMandant ? "" : getReadMandanten(false,"p2");
                //if (!s.equals(""))
                //  fixtestInfo("getReadMandanten(Bew):"+s);
		return s;
	}

        /*public String getReadMandanten2()
        {
          return iMandant==1 ? "" :" and (aic_mandant is null or aic_Mandant"+Static.SQL_in(VecMandantRead)+")";
        }*/

	public String getReadMandanten(boolean bWhere, String sTable)
	{
          //fixtestInfo("iMandant="+iMandant+", VecMandantRead="+VecMandantRead);
		String s=/*iMandant==1 ? "" :*/ (bWhere ? " where ":" and ")+(sTable==null ? "":sTable+".")+"aic_Mandant"+(VecNull("getReadMandanten: VecMandantRead",VecMandantRead)?"=-1":Static.SQL_in(VecMandantRead));
//		fixtestError("getReadMandanten:"+s);
		return s;
	}

	public String getWriteMandanten(boolean bWhere)
	{
		return AllUnlimited() ? "" : (bWhere ? " where":" and")+" aic_Mandant"+(VecNull("getWriteMandanten: VecMandantWrite",VecMandantWrite)?"=-1":Static.SQL_in(VecMandantWrite));
	}

        public String getReadMandanten(String sTable)
        {
        	//boolean bBeg=sTable!=null && sTable.equals("begriff");
            return (sTable==null ? " where aic_mandant is null or aic_Mandant":" and ("+sTable+".aic_mandant is null or "+sTable+".aic_Mandant")+
                    (VecNull("getReadMandanten: VecMandantRead",VecMandantRead)?"=-1":/*(bBeg ? "begriff.aic_mandant is null or ":"")+*/Static.SQL_in(VecMandantRead))+(sTable==null?"":")");
        }

        public boolean isWriteMandant(int i)
        {
          if (AllUnlimited())
          {
        	  int iPos=TabMandanten.getPos("aic_mandant",i);
        	  return iPos>=0 && !TabMandanten.getB(iPos, "Tod");
          }
          else
            return VecMandantWrite.contains(new Integer(i));
        }

        public String getCopyMandanten(boolean bWhere)
        {
                return /*iMandant==1 ? "" :*/ (bWhere ? " where":" and")+" aic_Mandant"+(VecNull("getCopyMandanten: VecMandantCopy",VecMandantCopy)?"=-1":Static.SQL_in(VecMandantCopy));
        }


        public void addUsed(int iBegriff)
        {
          //progInfo("addUsed "+iBegriff);
          //if (Version.V5())
            exec("update begriff set used=used+1 where aic_begriff="+iBegriff);
        }

        public Vector<Integer> getMandanten(String s)
        {
          Vector<Integer> Vec=new Vector<Integer>();
          if (TabMandanten==null)
            LoadMandant();
          int iPos=TabMandanten.getPos("Kennung",s);
          if (iPos>=0)
          {
            int iM=TabMandanten.getI(iPos,"aic_mandant");
            Vec.addElement(iM);
            fillMandanten(iM,Vec);
          }
          return Vec;
        }

        private void fillMandanten(int iM,Vector<Integer> Vec)
        {
          for(int iPos=0;iPos<TabMandanten.size();iPos++)
            if (TabMandanten.getI(iPos,"man_aic_mandant")==iM)
            {
              int iM2=TabMandanten.getI(iPos,"aic_mandant");
              Vec.addElement(iM2);
              fillMandanten(iM2,Vec);
            }
        }

	private void fillMandantenDarunter(Tabellenspeicher Tab,Integer Int,boolean bWrite)
	{
		if (Tab.posInhalt("man_aic_mandant",Int))
			for(;!Tab.eof() && !Tab.isNull("man_aic_mandant") && Tab.getInhalt("man_aic_mandant").equals(Int);Tab.moveNext())
			 if (!Tab.getB("Tod"))
			 {
				Integer IntNeu= (Integer)Tab.getInhalt("aic_mandant");
				VecMandantRead.addElement(IntNeu);
				if (bWrite)
					VecMandantWrite.addElement(IntNeu);
				Tab.push();
				fillMandantenDarunter(Tab,IntNeu,bWrite);
				Tab.pop();
			 }
	}

	private void fillMandantDarueber(Tabellenspeicher Tab,int i)
	{
		if (Tab.posInhalt("aic_mandant",i))
		{
			VecMandantRead.addElement(new Integer(i));
		//if (Tab.getI("man_aic_mandant") !=0)
			fillMandantDarueber(Tab,Tab.getI("man_aic_mandant"));
		}
	}

	private boolean fillVecMandant(boolean bWrite)
	{
          fixtestInfo("        **** fillVecMandant:"+bWrite+", Mandant="+iMandant);
		//bSuperuserR = false;
		VecMandantRead.removeAllElements();
                //VecMandantRead.addElement(null);
		if (bWrite)
		{
			//bSuperuserW = false;
			VecMandantWrite.removeAllElements();
		}
		Tabellenspeicher Tab = new Tabellenspeicher(this,"select aic_mandant,man_aic_mandant,Tod,Kennung from mandant order by man_aic_mandant",true,"Mandanten");
		if (Tab.posInhalt("aic_mandant",iMandant))
		{
                  if (Tab.getB("Tod"))
                  {
                    printError("!!!Global.fillVecMandant(): Mandant " + Tab.getS("Kennung") + " nicht mehr aktiv!");
                    iMandant=998;
                  }
			//bSuperuserR = Tab.getI("man_aic_mandant")==0;
                  else //if (Tab.getI("man_aic_mandant")!=0)
			{
				Integer Int=new Integer(iMandant);
                                VecMandantRead.addElement(Int);
				if (bWrite)
					VecMandantWrite.addElement(Int);
				fillMandantDarueber(Tab,Tab.getI("man_aic_mandant"));
                                //Tab.showGrid();
				fillMandantenDarunter(Tab,Int,bWrite);
			}
			//else
			//	bSuperuserW =true;
			//printInfo("VecMandantRead="+VecMandantRead);
			//printInfo("VecMandantWrite="+VecMandantWrite);
		}
		else
        {
          printError("!!!Global.fillVecMandant(): Kann Mandant " + iMandant + " nicht finden!");
          iMandant=999;
        }
        if (iMandant==0 || iMandant>990)
        {
//        	if (iMandant==0)
//        		Logout(true);
          fixtestError("fillVecMandant ohne Mandant -> "+iMandant);
          return false;
        }
        VecMandantCopy=new Vector<Object>(VecMandantRead);
        if (iMandant>1)
          VecMandantCopy.removeElement(new Integer(1));

        fixtestInfo("VecMandantRead="+VecMandantRead);
        fixtestInfo("VecMandantWrite="+VecMandantWrite);
        fixtestInfo("VecMandantCopy="+VecMandantCopy);

		//if(!AllUnlimited())
		//{
			//VecLizenz.setSize(iStdLizenzEnde);
                      if (Static.bLizenz)
                      {
                        Vector Vec = new Vector();
                        addVec(Vec, "select aic_fremd from lizenz where aic_tabellenname=" + iTabBegriff + " and aic_mandant=" + iMandant, "LizMod" + iMandant);
                        fixtestInfo("Vec-Module bei Mandant="+iMandant+":"+Vec);
                        VecBegLizenz.removeAllElements();
                        Static.addVectorI(VecBegLizenz, Vec);
                        addVec(VecBegLizenz, "select distinct beg_aic_begriff from begriff_zuordnung where aic_begriff" + Static.SQL_in(Vec), "LizBeg" + iMandant);
                        // fixtestError("VecBegLizenz="+VecBegLizenz);
                        VecLizEigenschaften.removeAllElements();
                        addVec(VecLizEigenschaften, "select distinct aic_fremd from modul where aic_tabellenname=" + TabTabellenname.getAic("EIGENSCHAFT") +
                               " and aic_begriff" + Static.SQL_in(Vec),"LizEig" + iMandant);
                        // fixtestInfo("VecLizEigenschaften=" + VecLizEigenschaften);
                        if(!AllUnlimited())
                        {
                          VecLizStt.removeAllElements();
                          addVec(VecLizStt, "select distinct aic_fremd from modul where aic_tabellenname=" + TabTabellenname.getAic("STAMMTYP") +
                                 " and aic_begriff" + Static.SQL_in(Vec), "LizStt" + iMandant);
                          // fixtestInfo("VecLizStt=" + VecLizStt);
                          VecLizHS.removeAllElements();
                          addVec(VecLizHS, "select distinct aic_fremd from modul where aic_tabellenname=" + TabTabellenname.getAic("HAUPTSCHIRM") +
                                 " and aic_begriff" + Static.SQL_in(Vec), "LizHS" + iMandant);
                          // fixtestInfo("VecLizHS=" + VecLizHS);

                        }
                        VecLizSprache.removeAllElements();
                        addVec(VecLizSprache, "select distinct aic_fremd from modul where aic_tabellenname=" + TabTabellenname.getAic("SPRACHE") +
                               " and aic_begriff" + Static.SQL_in(Vec), "LizSprache" + iMandant);
                        // fixtestInfo("VecLizSprache=" + VecLizSprache);
                      }
                      for (int i = 0; i < TabStammtypen.size(); i++)
                            if ((TabStammtypen.getI(i, "Bits") & cstModul) > 0)
                              TabStammtypen.setInhalt(i, "Anzahl",AllUnlimited() || !Static.bLizenz || VecLizStt.contains(TabStammtypen.getI(i, "Aic")) ? -1 : 0);

                        //TabModulLizenz=new Tabellenspeicher(this,"select distinct aic_tabellenname,aic_fremd from modul where aic_begriff"+Static.SQL_in(Vec)+" order by aic_tabellenname",true,"LizRest"+iMandant);
                        //TabModulLizenz.sGruppe="aic_tabellenname";
                        //TabModulLizenz.sAIC="aic_fremd";
                        //TabModulLizenz.showGrid("TabModulLizenz");
                        //Static.addVector(VecBegLizenz,Vec); // Module dazu
			//addVec(VecBegLizenz,"select aic_fremd from lizenz where anzahl is null and aic_mandant="+iMandant,"BegLizenz"+iMandant+"b");
                        /*SQL Qry=new SQL(this);
			Qry.open("select aic_fremd from lizenz where anzahl is null and aic_mandant="+iMandant);
			for(;!Qry.eof();Qry.moveNext())
				VecLizenz.addElement(new Integer(Qry.getI("aic_fremd")));
			Qry.free();*/

                    LoadLizenz();
                    VecEigNot=SQL.getVector("select aic_fremd from verboten where vbits is null and aic_tabellenname="+TabTabellenname.getAic("EIGENSCHAFT")+" and aic_mandant="+iMandant, this);
                    VecBewNot=SQL.getVector("select aic_fremd from verboten where vbits is null and aic_tabellenname="+TabTabellenname.getAic("BEWEGUNGSTYP")+" and aic_mandant="+iMandant, this);
                    fixtestInfo("VecEigNot="+VecEigNot+", VecBewNot="+VecBewNot);
                    return true;
	}
        private void LoadLizenz()
        {
			/*for(TabStammtypen.moveFirst();!TabStammtypen.eof();TabStammtypen.moveNext())
				if((TabStammtypen.getI("Bits")&cstLizenz)>0)
					TabStammtypen.setInhalt("Anzahl",0);*/
                        for(int i=0;i<TabStammtypen.size();i++)
                          if((TabStammtypen.getI(i,"Bits")&cstLizenz)>0)
                            TabStammtypen.setInhalt(i,"Anzahl",0);
                        int iTabStt=TabTabellenname.getAic("STAMMTYP");
                        for(Tabellenspeicher TabLizenz=new Tabellenspeicher(this,"select aic_fremd,Anzahl from lizenz where aic_tabellenname="+iTabStt+" and aic_mandant="+iMandant,true,"Lizenz"+iMandant+"a");!TabLizenz.eof();TabLizenz.moveNext())
                        {
                          int iPos=TabStammtypen.getPos("Aic", TabLizenz.getI("Aic_Fremd"));
                          if (iPos>=0)
                            TabStammtypen.setInhalt(iPos,"Anzahl", TabLizenz.getI("Anzahl"));
                        }
                        //for(TabRollen.moveFirst();!TabRollen.eof();TabRollen.moveNext())
                        for(int i=0;i<TabRollen.size();i++)
                        {
                          int iPos=TabStammtypen.getPos("Aic", TabRollen.getInhalt("Stt", i));
                          TabRollen.setInhalt(i, "Anzahl",iPos>=0  && (TabStammtypen.getI(iPos,"bits") & cstLizenz) == 0 ? -1 : 0);
                        }
                        int iTabRolle=TabTabellenname.getAic("ROLLE");
                        for(Tabellenspeicher TabLizenz=new Tabellenspeicher(this,"select aic_fremd,Anzahl from lizenz where aic_tabellenname="+iTabRolle+" and aic_mandant="+iMandant,true,"Lizenz"+iMandant+"b");!TabLizenz.eof();TabLizenz.moveNext())
                        {
                          int iPos=TabRollen.getPos("Aic", TabLizenz.getI("Aic_Fremd"));
                          if (iPos>=0)
                            TabRollen.setInhalt(iPos,"Anzahl", TabLizenz.getI("Anzahl"));
                        }
                        //TabRollen.showGrid();
	}

	public int getAnzahlStamm(int iStammtyp)
	{
          int iPos=TabStammtypen.getPos("Aic",iStammtyp);
          return iPos<0 ? 0 : TabStammtypen.getI(iPos,"Anzahl");
	}

        public int getAnzahlRolle(int iRolle)
        {
          int iPos=TabRollen.getPos("Aic",iRolle);
          return iPos<0 ? 0:TabRollen.getI(iPos,"Anzahl");
        }

        public String getSttBez(int iPosB)
        {
          int iPosE=TabErfassungstypen.getPos("AIC", TabBegriffe.getI(iPosB,"Erf"));
          int iPosS=TabStammtypen.getPos("AIC", TabBegriffe.getI(iPosB,"Stt"));
          int iPosR=TabRollen.getPos("AIC", TabBegriffe.getI(iPosB,"Rolle"));
          return iPosE>=0 ? "*B* "+TabErfassungstypen.getS(iPosE,"Bezeichnung"):iPosR>=0 ? "*R* "+TabRollen.getS(iPosR,"Bezeichnung"):iPosS<0 ?"":"*S* "+TabStammtypen.getS(iPosS,"Bezeichnung");
        }
        
//        public Vector<Integer> getVecStt(int iSttVon,int iSttBis)
//        {
//        	Vector<Integer> Vec=new Vector<>();
//        	while (iSttVon != 0 && iSttBis != iSttVon)
//        	{
//        		int iPos=TabStammtypen.getPos("Aic",new Integer(iSttVon));
//        		iSttVon = TabStammtypen.getI(iPos,"Darunter");
//    			if ((iSttBis==0 || ((TabStammtypen.getI(iPos,"bits")&Global.cstEnde)==0)) && existsSttS(iPos))
//    				Vec.addElement(TabStammtypen.getI(iPos,"Aic"));
//        	}
//        	return Vec;
//        }

        public int getPosFirstStt(int iStt)
        {
          //TabStammtypen.push();
          int i=0;
          int iPos=TabStammtypen.getPos("Aic",iStt);
          while(i==0 && iPos>=0)
          {
            //if (existsStt())
            if (AllUnlimited() && iStt==iSttFirma)
              return iPos;
            if(TabStammtypen.getI(iPos,"Anzahl") != 0)
              i=iStt;
            else
            {
              iStt = TabStammtypen.getI(iPos, "Darunter");
              iPos = TabStammtypen.getPos("Aic", iStt);
            }
          }
          //TabStammtypen.pop();
          return i>0 ? iPos:-1;
        }

        public int getFirma()
        {
          int iFirma=getSyncStamm(iSttFirma,0);
          testInfo("getFirma:"+iFirma);
          return iFirma;
        }
        
        public int getSttFirma()
        {
        	if (iSttFirma==0)
        		iSttFirma=SQL.getInteger(this,"select aic_stammtyp from stammtyp where kennung='Firma'");
        	return iSttFirma;
        }
        
        public int getEigFirma()
        {
        	if (iEigFirma==0)
        		iEigFirma=SQL.getInteger(this,"select aic_eigenschaft from eigenschaft where kennung='Firma'");
        	return iEigFirma;
        }

        public boolean existsSttS(int iPos)
	{
		return AllUnlimited() ? true : TabStammtypen.getI(iPos,"Anzahl") != 0;
	}

	/*public boolean existsStt()
	{
		return AllUnlimited() ? true : TabStammtypen.getI("Anzahl") != 0;
	}*/

	public boolean existsStt(int iStammtyp)
	{
		if (AllUnlimited())
			return true;
		else
		{
			//TabStammtypen.push();
		  int iPos=TabStammtypen.getPos("Aic",iStammtyp);
		  boolean b=iPos>=0 ? TabStammtypen.getI(iPos,"Anzahl") != 0:false;
			//TabStammtypen.pop();
		  return b;
		}
	}

        public boolean existsBew(int iBew) // prüft ob ansehen/Abfrage damit erlaubt
        {
          boolean b= /*(AllUnlimited() || TabModulLizenz.posInhalt(iTabBew,iBew)) &&*/(SuperUser() || TabModulBerechtigt.posInhalt(iTabBew,iBew));// || VecBew.contains(new Integer(iBew));
          //testInfo("existsBew "+iBew+"="+b);
          return b;
        }
        
        public boolean allowBew(int iBew)
        {
        	boolean b= VecBewNot != null && !VecBewNot.contains(iBew);
//        	if (!b)
//        	fixtestError("Bew "+iBew+" ist "+(b ? "erlaubt":"verboten!!"));
            return b;
        }

        public boolean Spezial()
        {
          return (iBits & cstSpezial) > 0 || Verwaltung();//(iBits & 3) == 1 || (iBits & 3) == 2;
        }

        private boolean OhneLizenz()
        {
          return (iBits & cstOhne) > 0 || Def();
        }
        
        public void setAutoFC()
        {
        	Static.bAutoKonsole=(iBits & cstAutoFC) > 0;
        }
        
        public boolean OC()
        {
        	return (iBits & cstOC) > 0;
        }

     // ---------- neue persönliche Abfragen ----------------

        public int getAbfrage(int iDarstellung)
        {
          int i=TabPersAbfragen.getPos("int1", iDarstellung);
          return i<0 ? 0:TabPersAbfragen.getI(i,"int2");
        }

        public void setAbfrage(int iDarstellung,int iAbfrage)
        {
          if (iDarstellung<=0)
            testInfo("AbfrageAusblenden: Darstellung="+iDarstellung+" bei Abfrage"+iAbfrage);
          else
          {
            int iPos=TabPersAbfragen.getPos("int1", iDarstellung);
            if (iPos>=0)
            {
              if (iAbfrage != TabPersAbfragen.getI(iPos,"int2"))
              {
                TabPersAbfragen.setInhalt(iPos,"int2", iAbfrage);
                TabPersAbfragen.setInhalt(iPos,"status", "update");
              }
            }
            else
            {
              iPos=TabPersAbfragen.newLine(-1);
              //TabPersAbfragen.setInhalt("aic_parameter",0);
              TabPersAbfragen.setInhalt(iPos,"int1", iDarstellung);
              TabPersAbfragen.setInhalt(iPos,"int2", iAbfrage);
              //TabPersAbfragen.setInhalt("int3",0);
              //TabPersAbfragen.setInhalt("int4",0);
              TabPersAbfragen.setInhalt(iPos,"status", "insert");
            }
          }
        }

        public void AbfrageP(int iDarstellung,int iAbfrage,boolean bTrue,int iPara)
        {
          if (iDarstellung<=0)
            testInfo("AbfrageP: Darstellung="+iDarstellung+" bei Abfrage"+iAbfrage);
          else
          {
            int iPos = TabPersAbfragen.getPos("int1", iDarstellung);
            if (iPos >= 0)
            {
              TabPersAbfragen.setInhalt(iPos,"int3", (TabPersAbfragen.getI(iPos,"int3") & (0x0FFF - iPara)) + (bTrue ? iPara : 0));
              TabPersAbfragen.setInhalt(iPos,"status", "update");
            }
            else
            {
              iPos=TabPersAbfragen.newLine(-1);
              TabPersAbfragen.setInhalt(iPos,"int1", iDarstellung);
              TabPersAbfragen.setInhalt(iPos,"int2", iAbfrage);
              TabPersAbfragen.setInhalt(iPos,"int3", bTrue ? iPara : 0);
              TabPersAbfragen.setInhalt(iPos,"status", "insert");
            }
          }
        }

        public boolean isAbfrageP(int iDarstellung,int iPara,boolean bDefault)
        {
          if (iDarstellung<=0)
            testInfo("isAbfrageP: Darstellung="+iDarstellung);
          int i=TabPersAbfragen.getPos("int1", iDarstellung);
          return i<0 ? bDefault:(TabPersAbfragen.getI(i,"int3")&iPara)>0;
        }

        public void AbfrageAusblenden(int iDarstellung,int iAbfrage,boolean bWeg)
        {
          if (iDarstellung<=0)
            testInfo("AbfrageAusblenden: Darstellung="+iDarstellung+" bei Abfrage"+iAbfrage);
          else
          {
            int iPos = TabPersAbfragen.getPos("int1", iDarstellung);
            if (iPos >= 0)
            {
              TabPersAbfragen.setInhalt(iPos,"int3", (TabPersAbfragen.getI(iPos,"int3") & (0x0FFF - WEG)) + (bWeg ? WEG : 0));
              TabPersAbfragen.setInhalt(iPos,"status", "update");
            }
            else
            {
              iPos=TabPersAbfragen.newLine(-1);
              TabPersAbfragen.setInhalt(iPos,"int1", iDarstellung);
              TabPersAbfragen.setInhalt(iPos,"int2", iAbfrage);
              TabPersAbfragen.setInhalt(iPos,"int3", bWeg ? WEG : 0);
              TabPersAbfragen.setInhalt(iPos,"status", "insert");
            }
          }
        }

        public boolean isAbfrageAktiv(int iDarstellung)
        {
          if (iDarstellung<=0)
            testInfo("isAbfrageAktiv: Darstellung="+iDarstellung);
          int i=TabPersAbfragen.getPos("int1", iDarstellung);
          return i<0 || (TabPersAbfragen.getI(i,"int3")&WEG)==0;
        }

        public Tabellenspeicher getUserAbfragen()
        {
          Tabellenspeicher Tab=new Tabellenspeicher(this,new String[] {"aic_parameter","Formular","Original-Abfrage","pers. Abfrage","ausgeblendet","vor. Austritt"});
          Tabellenspeicher Tab2=new Tabellenspeicher(this,"select dar_aic_darstellung aic,formular.aic_begriff Fom,darstellung.aic_begriff Abf from formular "+
              join2("darstellung","formular")+" where reihenfolge=1 and dar_aic_darstellung"+Static.SQL_in(TabPersAbfragen.getVecSpalteI("int1")),true);
          for(int iPos=0;iPos<TabPersAbfragen.size();iPos++)
            if (Tab2.posInhalt("aic", TabPersAbfragen.getI(iPos,"int1")))
            {
              Tab.addInhalt("aic_parameter", TabPersAbfragen.getI(iPos,"aic_parameter"));
              Tab.addInhalt("Formular",TabBegriffe.getBezeichnungS(Tab2.getI("Fom")));
              Tab.addInhalt("Original-Abfrage",TabBegriffe.getBezeichnungS(Tab2.getI("Abf")));
              Tab.addInhalt("pers. Abfrage",TabBegriffe.getBezeichnungS(TabPersAbfragen.getI(iPos,"int2")));
              Tab.addInhalt("ausgeblendet",Static.JaNein((TabPersAbfragen.getI(iPos,"int3")&WEG)>0));
              Tab.addInhalt("vor. Austritt",Static.JaNein((TabPersAbfragen.getI(iPos,"int3")&VAUS)>0));
            }
            else
              testInfo("getUserAbfragen: Zeile "+TabPersAbfragen.getI("int1")+" nicht gefunden!");
          return Tab;
        }

        public void DeleteUserAbfrage(int i)
        {
          exec("delete from parameter where aic_parameter="+i);
          TabPersAbfragen.clearInhalt("aic_parameter",new Integer(i));
        }

        // ---------- alte persönliche Abfragen ----------------

        /*public int getAbfrage(int iFormular,int iAbfrageOld)
	{
          TabBegriffe.push();
          int i=TabUserAbfragen.posInhalt(new Integer(iFormular),new Integer(iAbfrageOld)) && TabBegriffe.posInhalt("Aic",TabUserAbfragen.getI("int3")) ? TabUserAbfragen.getI("int3"):iAbfrageOld;
          boolean bDel=!TabUserAbfragen.out() && TabUserAbfragen.getS("status").equals("del");
          if (i != iAbfrageOld && !bDel)
            testInfo("Ändere Abfrage von "+TabBegriffe.getBezeichnung(iAbfrageOld)+" auf "+TabBegriffe.getBezeichnung(i));
          TabBegriffe.pop();
          return bDel ? iAbfrageOld:i;
	}

	public void setAbfrage(int iBegriff,int iAbfrageOld,int iAbfrageNew)
	{
		boolean bAnders=iAbfrageNew>0 && iAbfrageOld != iAbfrageNew;
		if (TabUserAbfragen.posInhalt(new Integer(iBegriff),new Integer(iAbfrageOld)))
                {
                  if (bAnders)
                  {
                  	TabUserAbfragen.setInhalt("int3", iAbfrageNew);
                  	TabUserAbfragen.setInhalt("status","update");
                  }
                  else if (TabUserAbfragen.getI("int4")==0)
                  	TabUserAbfragen.setInhalt("status","del");
                  else
                  {
                  	TabUserAbfragen.setInhalt("int3", iAbfrageOld);
                  	TabUserAbfragen.setInhalt("status","update");
                  }
                }
		else if (bAnders)
		{
			TabUserAbfragen.putInhalt("aic_parameter",0,true);
			TabUserAbfragen.putInhalt("int1",iBegriff,true);
			TabUserAbfragen.putInhalt("int2",iAbfrageOld,true);
			TabUserAbfragen.putInhalt("int3",iAbfrageNew,true);
			TabUserAbfragen.putInhalt("int4",0,true);
			TabUserAbfragen.putInhalt("status","insert",true);
		}
	}*/

        /*public void setAbfrageWeg(int iBegriff,int iAbfrage,int iArt)
        {
          if (TabUserAbfragen.posInhalt(new Integer(iBegriff),new Integer(iAbfrage)))
          {
          	TabUserAbfragen.setInhalt("int4", iArt);
          	TabUserAbfragen.setInhalt("status","update");
          }
          else
          {
          	TabUserAbfragen.putInhalt("aic_parameter",0,true);
          	TabUserAbfragen.putInhalt("int1",iBegriff,true);
          	TabUserAbfragen.putInhalt("int2",iAbfrage,true);
          	TabUserAbfragen.putInhalt("int3",iAbfrage,true);
          	TabUserAbfragen.putInhalt("int4",iArt,true);
          	TabUserAbfragen.putInhalt("status","insert",true);
          }
        }

        public boolean isAbfrageAktiv(int iBegriff,int iAbfrage)
        {
          return TabUserAbfragen.posInhalt(new Integer(iBegriff),new Integer(iAbfrage)) ? TabUserAbfragen.getI("int4")==0:true;
        }*/

        /*public Tabellenspeicher getUserAbfragen()
        {
          Tabellenspeicher Tab=new Tabellenspeicher(this,new String[] {"aic_parameter","Formular","Original-Abfrage","pers. Abfrage","ausgeblendet"});
          TabUserAbfragen.push();
          for(TabUserAbfragen.moveFirst();!TabUserAbfragen.eof();TabUserAbfragen.moveNext())
          {
            Tab.addInhalt("aic_parameter", TabUserAbfragen.getI("aic_parameter"));
            Tab.addInhalt("Formular",TabBegriffe.getBezeichnungS(TabUserAbfragen.getI("int1")));
            Tab.addInhalt("Original-Abfrage",TabBegriffe.getBezeichnungS(TabUserAbfragen.getI("int2")));
            Tab.addInhalt("pers. Abfrage",TabBegriffe.getBezeichnungS(TabUserAbfragen.getI("int3")));
            Tab.addInhalt("ausgeblendet",Static.JaNein(TabUserAbfragen.getB("int4")));
          }
          TabUserAbfragen.pop();
          return Tab;
        }

        /*public void DeleteUserAbfrage(int i)
        {
          exec("delete from parameter where aic_parameter="+i);
          TabUserAbfragen.clearInhalt("aic_parameter",new Integer(i));
        }*/
        
        
        public void setGF(int iStt,int iEig,int iAnz,int iBits)
        {
        	int iPos=TabGF.getPos("int1",iStt);
        	if (iPos<0)
        	{
        		iPos=TabGF.newLine();
        		TabGF.setInhalt(iPos,"int1",iStt);
        		TabGF.setInhalt(iPos,"int2",iEig);
        		TabGF.setInhalt(iPos,"int3",iAnz);
        		TabGF.setInhalt(iPos,"int4",iBits);
        		TabGF.setInhalt(iPos,"status","insert");
        	}
        	else if (iBits!=TabGF.getI(iPos,"int4"))
        	{
        		TabGF.setInhalt(iPos,"int3",iAnz);
        		TabGF.setInhalt(iPos,"int4",iBits);
        		TabGF.setInhalt(iPos,"status","change");
        	}
        }
        
//        public void setTabPos(int iBeg,TreeTableView Out)
//        {
//        	if (Static.bOriginal)
//        		return;
//        	for (int i=0;i<Out.getColumns().size();i++)
//        	{
//        		TableColumnBase TC=(TreeTableColumn)Out.getColumns().get(i);
//        		setColPos(iBeg,i,TC);
//        	}
//        }
//        
//        public void setTabPos(int iBeg,TableView Out)
//        {
//        	if (Static.bOriginal)
//        		return;
//        	for (int i=0;i<Out.getColumns().size();i++)
//        	{
//        		TableColumnBase TC=(TableColumnBase)Out.getColumns().get(i);
//        		setColPos(iBeg,i,TC);
//        	}
//        }
//        
//        private void setColPos(int iBeg,int i,TableColumnBase TC)
//        {
//        	if (TC.getUserData()==null)
//        	{
//        		fixtestError("Spalte "+TC.getText()+" hat keine ID");
//        		return;
//        	}
//    		int iUD=Sort.geti(""+TC.getUserData());
//    		int iW=(int)TC.getWidth();
//    		boolean bV=TC.isVisible();
//    		fixtestInfo("setTabPos für "+iBeg+": Spalte"+iUD+"/"+TC.getText()+": Breite="+iW+", Pos="+i+", sichtbar="+bV);
//    		int i4=iUD+(bV?0:256);   			
//    		int iPos=TabPos.getPos3(new Integer(iBeg),new Integer(i));
//    		if (iPos>=0)
//    		{
//    			if (iW!=TabPos.getI(iPos,"int3") || i4!=TabPos.getI(iPos,"int4"))
//    			{
//    		      TabPos.setInhalt(iPos,"Parameterzeile",TC.getText());
//    			  TabPos.setInhalt(iPos,"int3", iW);
//    			  TabPos.setInhalt(iPos,"int4", i4);
//    			  TabPos.setInhalt(iPos,"status","update");
//    			}
//    		}
//    		else
//            {
//              iPos=TabPos.newLine(TabPos.getPos("int1",iBeg));
//              TabPos.setInhalt(iPos,"aic_parameter",0);
//              TabPos.setInhalt(iPos,"Parameterzeile",TC.getText());
//              TabPos.setInhalt(iPos,"int1",iBeg);
//              TabPos.setInhalt(iPos,"int2",i);
//              TabPos.setInhalt(iPos,"int3",iW);
//              TabPos.setInhalt(iPos,"int4",i4);
//              TabPos.setInhalt(iPos,"status","insert");
//            }
//        }
        
//        public void getTabPos(int iBeg,int i,TableColumnBase Col)
//        {
//        	getTabPos(iBeg,i,Col,null);
//        }
        
//        public void getTabPos(int iBeg,int i,TableColumnBase Col)//,String sBez)
//        {
//        	if (Static.bOriginal)
//        		return;
//        	int iPos=TabPos.getPos3(new Integer(iBeg),new Integer(i));
//        	if (iPos>=0)      		
//        	{
//        		Col.setPrefWidth(TabPos.getI(iPos,"int3"));
//        		int i4=TabPos.getI(iPos,"int4");
//        		Col.setUserData(i4&255);
//        		Col.setVisible((i4&256)==0);
//        		String sBezNeu=TabPos.getS(iPos,"Parameterzeile");//sBez==null ? TabPos.getS(iPos,"Parameterzeile"):sBez;
//        		if (sBezNeu!=null && !Col.getText().equals(sBezNeu))//(sBez==null || !Col.getText().equals(sBez))
//        		{
//        			//fixtestError("ändere Spalte "+Col.getText()+" auf "+sBezNeu);//+", save:"+TabPos.getS(iPos,"Parameterzeile"));
//        			Col.setText(sBezNeu);
//        			//Col.setText(sBez==null ? TabPos.getS(iPos,"Parameterzeile"):sBez);
//        		}
//        		//Col.setText(/*"*"+*/TabPos.getS(iPos,"Parameterzeile"));
////        		fixtestInfo("getTabPos für "+iBeg+"/"+i+":"+TabPos.getS(iPos,"Parameterzeile")+", UD="+(i4&255)+", Visible="+((i4&256)==0));
//        	}
//        }

        /**
         * TabFilter setzen/ermitteln
         * 
         * @param iFormular
         * @param iEigenschaft
         * @param iAbfrageOld
         * @return
         */
        public int getFilter(int iFormular,int iEigenschaft,int iAbfrageOld)
        {
          int iPos=TabFilter.getPos3(new Integer(iFormular),new Integer(iEigenschaft));
          return iPos>=0 && !TabFilter.getS(iPos,"status").equals("del") && TabFilter.getI(iPos,"int4")>0 ? TabFilter.getI(iPos,"int4"):iAbfrageOld;
        }

        public void setFilter(int iBegriff,int iEigenschaft,int iAbfrageOld,int iAbfrageNew)
        {
          if (iBegriff==0 || iEigenschaft==0)
            return;
          boolean bAnders=iAbfrageNew>0 && iAbfrageOld != iAbfrageNew;
          int iPos=TabFilter.getPos3(new Integer(iBegriff),new Integer(iEigenschaft));
          if (iPos>=0)
                  if (bAnders)
                  {
                    TabFilter.setInhalt(iPos,"int3", iAbfrageOld);
                    TabFilter.setInhalt(iPos,"int4", iAbfrageNew);
                    TabFilter.setInhalt(iPos,"status","update");
                  }
                  else
                    TabFilter.setInhalt(iPos,"status","del");
          else if (bAnders)
          {
            iPos=TabFilter.newLine(TabFilter.getPos("int1",iBegriff));
            TabFilter.setInhalt(iPos,"aic_parameter",0);
            TabFilter.setInhalt(iPos,"Parameterzeile",null);
            TabFilter.setInhalt(iPos,"int1",iBegriff);
            TabFilter.setInhalt(iPos,"int2",iEigenschaft);
            TabFilter.setInhalt(iPos,"int3",iAbfrageOld);
            TabFilter.setInhalt(iPos,"int4",iAbfrageNew);
            TabFilter.setInhalt(iPos,"status","insert");
          }
          //fixInfo(" !!! setFilter !!! "+iBegriff+"/"+iEigenschaft+"/"+iAbfrageOld+"->"+iAbfrageNew+"/"+bAnders+"/"+iPos);
          //TabFilter.showGrid("TabFilter");
        }

        public String getFPara(int iFormular,int iEigenschaft)
        {
          int iPos=TabFilter.getPos3(new Integer(iFormular),new Integer(iEigenschaft));
          return iPos>=0 && !TabFilter.getS(iPos,"status").equals("del") ? TabFilter.getS(iPos,"Parameterzeile"):"";
        }

        public void setFPara(int iBegriff,int iEigenschaft,String sPara)
        {
          if (iBegriff==0 || iEigenschaft==0)
            return;
          //boolean bAnders=iAbfrageNew>0 && iAbfrageOld != iAbfrageNew;
          int iPos=TabFilter.getPos3(new Integer(iBegriff),new Integer(iEigenschaft));
          if (iPos>=0)
          {
            if (!sPara.equals(TabFilter.getS(iPos,"Parameterzeile")))
            {
              TabFilter.setInhalt(iPos,"Parameterzeile", sPara);
              TabFilter.setInhalt(iPos,"status", "update");
            }
          }
          else
          {
            iPos=TabFilter.newLine(TabFilter.getPos("int1",iBegriff));
            TabFilter.setInhalt(iPos,"aic_parameter",0);
            TabFilter.setInhalt(iPos,"Parameterzeile",sPara);
            TabFilter.setInhalt(iPos,"int1",iBegriff);
            TabFilter.setInhalt(iPos,"int2",iEigenschaft);
            TabFilter.setInhalt(iPos,"int3",0);
            TabFilter.setInhalt(iPos,"int4",0);
            TabFilter.setInhalt(iPos,"status","insert");
          }
        }


	//                          //
	//    F E N S T E R P O S   //
	//                          //

	public boolean getFenster(Component f,int iBegriff,boolean bNot,int iX,int iY,int iW,int iH,int iFF)
	{
//		fixtestError("getFensterA "+TabBegriffe.getBezeichnungS(iBegriff)+"->"+iX+","+iY+"->"+iFF+":"+iW+"x"+iH+" -> "+Static.getMon(iX,iY));
        int iPos=TabFensterpos.getPos("vfenster",iBegriff);
		boolean b=iPos>=0 && TabFensterpos.getI(iPos,"w")>9 && TabFensterpos.getI(iPos,"h")>9;
		int iZoom=100;
		if (b)
	    {
	      iX=TabFensterpos.getI(iPos,"x");
	      iY=TabFensterpos.getI(iPos,"y");
	      if (iX==-32000)
	      {  iX=0;iY=0; }
	      else if (Static.iMaxX>0 && iX+5>Static.iMaxX)
	        iX=Static.iMaxX-(bNot ? iW:TabFensterpos.getI(iPos,"w"));
	      iW= bNot ? iW:TabFensterpos.getI(iPos,"w");
	      iH= bNot ? iH:TabFensterpos.getI(iPos,"h");
	      iZoom= bNot ? 100:TabFensterpos.getI(iPos,"zoom");
        }
		else if (iX<20 && iY<20)
		{
			iX=Math.max(iX, 5);
			iY=Math.max(iY, 40);
			fixtestInfo("ändere x/y auf "+iX+"/"+iY);
			
		}
		int iMon=Static.getMon(iX,iY);
		if (iMon==0)
		{
			f.setSize(iW,iH);
			return false;
		}
//                fixtestError("getFenster2 "+TabBegriffe.getBezeichnungS(iBegriff)+"->"+iZoom+"->"+iFF+":"+iW+"x"+iH);
                iW=iW*iFF/iZoom;iH=iH*iFF/iZoom;
                //progInfo("->"+iW+"x"+iH);
                f.setBounds(new Rectangle(iX,iY,iW,iH));
                winInfo("get "+TabBegriffe.getBezeichnungS(iBegriff) +"/"+iBegriff+") -> "+b+" "+f.getBounds());
//       fixtestError("getFensterE "+getBegBez(iBegriff)+" ->"+b+" "+iX+","+iY+" "+iW+"x"+iH+" -> "+iMon); 	
//       new Exception().printStackTrace();
       return b;
	}
	
	public void winInfo(String s)
	{
		 if (Static.bInfoWin)
             fixInfo("(W) "+s);
	}
	
//	public boolean getFensterFX(Stage f,int iBegriff,boolean bNot,int iX,int iY,int iW,int iH,int iFF)
//	{
//		return getFensterFX(f,null,iBegriff,bNot,iX,iY,iW,iH,iFF);
//	}
//	
//	public boolean getFensterFX(Stage f,Stage parentStage,int iBegriff,boolean bNot,int iX,int iY,int iW,int iH,int iFF)
//	{
//		if (parentStage!=null)
//			fixtestError("parentStage für "+getBegBez(iBegriff)+"="+parentStage);
//          int iPos=TabFensterpos.getPos("vfenster",iBegriff);
//		boolean b=iPos>=0 && TabFensterpos.getI(iPos,"w")>9 && TabFensterpos.getI(iPos,"h")>9;
//		if (b)
//                {
//                  iX=TabFensterpos.getI(iPos,"x");
//                  iY=TabFensterpos.getI(iPos,"y");
//                  if (iX==-32000)
//                  {  iX=0;iY=0; }
//                  else if (Static.iMaxX>0 && iX+5>Static.iMaxX)
//                    iX=Static.iMaxX-(bNot ? iW:TabFensterpos.getI(iPos,"w"));
//                  iW= bNot ? iW:TabFensterpos.getI(iPos,"w");iH= bNot ? iH:TabFensterpos.getI(iPos,"h");
//                }
//                testInfo("getFenster "+iBegriff+"->"+iFF+":"+iW+"x"+iH);
//                iW=iW*iFF/100;iH=iH*iFF/100;
//                //progInfo("->"+iW+"x"+iH);
//                //f.setBounds(new Rectangle(iX,iY,iW,iH));
//        if (f!=null)
//        {
//        	if ((Static.bCenterStage || !b) && parentStage != null)
//        	{
//	        	ChangeListener<Number> widthListener = (observable, oldValue, newValue) -> {
//	                double stageWidth = newValue.doubleValue();
//	                f.setX(parentStage.getX() + parentStage.getWidth() / 2 - stageWidth / 2);
//	        	};
//	        	ChangeListener<Number> heightListener = (observable, oldValue, newValue) -> {
//	                double stageHeight = newValue.doubleValue();
//	                f.setY(Math.max(iFxMinY, parentStage.getY() + parentStage.getHeight() / 2 - stageHeight / 2));   
//	        	};
//	        	f.widthProperty().addListener(widthListener);
//	        	f.heightProperty().addListener(heightListener);
//	        	f.setOnShown(e -> {
//	        	    f.widthProperty().removeListener(widthListener);
//	        	    f.heightProperty().removeListener(heightListener);
//	        	});
//        	}
//        	else
//        	{
//        		f.setX(iX);
//        		f.setY(iY);
//        	}
//        	if (f.getY()<iFxMinY)
//        		f.setY(iFxMinY);
//        	f.setWidth(iW);
//        	f.setHeight(iH);       	
//        }
//        if (Static.bInfoWin)
//            fixInfo("(W) get "+TabBegriffe.getBezeichnungS(iBegriff) +"/"+iBegriff+" -> "+b+" "+f.getWidth()+"x"+f.getHeight()+" mit Pos="+f.getX()+","+f.getY());
//		return b;
//	}

        public int getFensterBits(int iBegriff)
        {
          int iPos = TabFensterpos.getPos("vfenster", iBegriff);
          return iPos<0 ? 0:TabFensterpos.getI(iPos,"Bits");
        }
        public void setFenster(Component f,int iBegriff,int iFF)
        {
          setFenster(f,iBegriff,0,0,iFF);
        }
        
        public void setFenster(Component f,int iBegriff,int iBit,int iSatz,int iFF)
        {
        	if (f!=null && f.isVisible())
        	{
	        	int ix = f.getLocationOnScreen().x;
	            int iy = f.getLocationOnScreen().y;
	            int iw = f.getSize().width;
	            int ih = f.getSize().height;
	        	setFenster(ix,iy,iw,ih,iBegriff,iBit,iSatz,iFF);
        	}
        }
        
//        public void setFensterFX(Stage f,int iBegriff,int iBit,int iSatz,int iFF)
//        {
//        	if (f!=null)
//        		setFenster((int)f.getX(),(int)f.getY(),(int)f.getWidth(),(int)f.getHeight(),iBegriff,iBit,iSatz,iFF);
//        }

	public void setFenster(int ix,int iy,int iw,int ih,int iBegriff,int iBit,int iSatz,int iFF)
	{
          if (iBegriff<=0)
            return;
          try
          {
//            iw = iw*100/iFF;
//            ih = ih*100/iFF;
        	  if (iFF==-1)
            	  iFF=getFontFaktor();
//            fixtestError("setFenster "+TabBegriffe.getBezeichnungS(iBegriff) + "/"+iBegriff+"->"+iFF+":"+iw+"x"+ih);
            int iPos=TabFensterpos.getPos("vfenster", iBegriff);
            if (iPos>=0)
            {
              if ((ix != TabFensterpos.getI(iPos,"x") || iy != TabFensterpos.getI(iPos,"y") || iw != TabFensterpos.getI(iPos,"w") || ih != TabFensterpos.getI(iPos,"h")
                  ||  TabFensterpos.getI(iPos,"Bits") != iBit || TabFensterpos.getI(iPos,"Satz") != iSatz) && !TabFensterpos.getS(iPos,"Status").equals("del"))
              {
                TabFensterpos.setInhalt(iPos,"x", ix);
                TabFensterpos.setInhalt(iPos,"y", iy);
                TabFensterpos.setInhalt(iPos,"w", iw);
                TabFensterpos.setInhalt(iPos,"h", ih);
                if (iFF>0)
                	TabFensterpos.setInhalt(iPos,"zoom", iFF);
                TabFensterpos.setInhalt(iPos,"Bits", iBit);
                TabFensterpos.setInhalt(iPos,"Satz", iSatz);
                TabFensterpos.setInhalt(iPos,"Status", "update");
                winInfo("set " + TabBegriffe.getBezeichnungS(iBegriff) + "/" + iBegriff + ":" + ix + "," + iy + "," + iw + "," + ih + " -> update");
              }
            }
            else
            {
              iPos=TabFensterpos.newLine(-1);
              TabFensterpos.setInhalt(iPos,"vfenster", iBegriff);
              TabFensterpos.setInhalt(iPos,"Fenster",getBegBez(iBegriff));
              TabFensterpos.setInhalt(iPos,"x", ix);
              TabFensterpos.setInhalt(iPos,"y", iy);
              TabFensterpos.setInhalt(iPos,"w", iw);
              TabFensterpos.setInhalt(iPos,"h", ih);
              if (iFF==-1)
            	  iFF=getFontFaktor();
              TabFensterpos.setInhalt(iPos,"zoom", iFF);
              TabFensterpos.setInhalt(iPos,"Bits",iBit);
              TabFensterpos.setInhalt(iPos,"Satz", iSatz);
              TabFensterpos.setInhalt(iPos,"Status", "insert");
              winInfo("set " + TabBegriffe.getBezeichnungS(iBegriff) + "/" + iBegriff + ":" + ix + "," + iy + "," + iw + "," + ih + " -> insert");
            }
          }
          catch (Exception e) {testInfo("setFenster von "+iBegriff+" nicht möglich");}
                //TabFensterpos.showGrid("TabFensterpos");
//          if (Static.bInfoExcept)
//        	  printStackTrace(new Exception());
	}

	private void saveFenster()
	{
		//int iFF=getFontFaktor();
		SQL Qry=new SQL(this);
		for(int iPos=0;iPos<TabFensterpos.size();iPos++)
			if (!TabFensterpos.isNull(iPos,"Status"))
			{
                          if(TabFensterpos.getS(iPos,"Status").equals("del"))
                            exec("delete from Fensterpos where aic_fensterpos="+TabFensterpos.getI(iPos,"aic_fensterpos"));
                          else
                          {
				if (TabFensterpos.getI(iPos,"vFenster") > 0)
					Qry.add("aic_begriff",TabFensterpos.getI(iPos,"vFenster"));
				else
					Qry.add("aic_stammtyp",-TabFensterpos.getI(iPos,"vFenster"));
				Qry.add("x",TabFensterpos.getI(iPos,"x"));
				Qry.add("y",TabFensterpos.getI(iPos,"y"));
				Qry.add("w",TabFensterpos.getI(iPos,"w"));
				Qry.add("h",TabFensterpos.getI(iPos,"h"));
                Qry.add("bits",TabFensterpos.getI(iPos,"bits"));
                Qry.add("satz",TabFensterpos.getI(iPos,"Satz"));
                Qry.add("zoom", TabFensterpos.getI(iPos,"zoom"));
				Qry.add("aic_logging",iLog);
				if (TabFensterpos.getI(iPos,"aic_fensterpos")==0)
				{
					Qry.insert("Fensterpos",false);
					printInfo(2,"saveFenster: insert "+TabFensterpos.getI(iPos,"vFenster"));
				}
				else
				{
					Qry.update("Fensterpos",TabFensterpos.getI(iPos,"aic_fensterpos"));
					printInfo(2,"saveFenster: update "+TabFensterpos.getI(iPos,"vFenster"));
				}
                          }
			}
		Qry.free();
	}

        //                      //
        //    S P L I T P O S   //
        //                      //

        public void getSplitPos(JSplitPane split,int iAic)
        {
          if (TabSplitPos==null)
            return;
          int iPos=TabSplitPos.getPos("Aic",iAic);
          if (iPos>=0)
            split.setDividerLocation(TabSplitPos.getI(iPos,"neu"));
        }

        public void setSplitPos(int iAic,int iWert)
        {
          if (TabSplitPos==null)
            return;
          int iPos=TabSplitPos.getPos("Aic",iAic);
          if (iPos<0)
          {
            iPos = TabSplitPos.newLine(-1);
            TabSplitPos.setInhalt(iPos, "Aic", new Integer(iAic));
          }
          TabSplitPos.setInhalt(iPos,"neu", new Integer(iWert));
          //TabSplitPos.showGrid();
        }

        public void saveSplitPos(Parameter P)
        {
          if (TabSplitPos==null)
            return;
          P.ErmittleParameter("SplitPos",false);
          for(int iPos=0;iPos<TabSplitPos.size();iPos++)
            if (TabSplitPos.isNull(iPos,"aic_parameter") || TabSplitPos.getI(iPos,"alt") != TabSplitPos.getI(iPos,"neu"))
            {
              P.add("Aic_Computer", getComputer());
              P.add("Aic_Benutzer", getBenutzer());
              P.addGruppen();
              P.add("int1", TabSplitPos.getI(iPos,"Aic"));
              P.add("int2", TabSplitPos.getI(iPos,"neu"));
              P.add("aic_logging", iLog);
              if (TabSplitPos.getI(iPos,"aic_parameter") == 0)
                P.insert("parameter", false);
              else
                P.update("parameter", TabSplitPos.getI(iPos,"aic_parameter"));
            }
        }

	public int getStamm()
	{
          return iStamm;
	}

        /*public int getHauptStamm()
        {
          return iStammHaupt;
        }*/

        public String getUser()
        {
          if (sUserStamm != null)
            return sUserStamm;
          else
          {
            sUserStamm=SQL.getString(this,"select bezeichnung from stammview2 where aic_rolle is null and aic_stamm="+iStamm);
            return sUserStamm;
          }
        }

        public int getStt(int iAic)
        {
          int iPos=TabBegriffe.getPos("Aic",iAic);
          progInfo("getStt("+iAic+"): iPos="+iPos);
          return iPos<0 ? 0:TabBegriffe.getI(iPos,"Erf")==0 ? TabBegriffe.getI(iPos,"Stt"):-TabBegriffe.getI(iPos,"Erf");
        }
        
        public int getRolle(int iAic)
        {
          int iPos=TabBegriffe.getPos("Aic",iAic);
          progInfo("getStt("+iAic+"): iPos="+iPos);
          return iPos<0 ? 0:TabBegriffe.getI(iPos,"Rolle");
        }

//        public int getSystemInfo()
//        {
//          return iSysinfo;
//        }

        public void setStamm(int riStamm)
        {
          iStamm=riStamm;
          //setSyncStamm();
        }

        public void setSyncStamm()
        {
          if (iSttANR==0)
            printError("Arbeitnehmer-Stammtyp nicht gefunden");
          else //if (iStamm>0)
          {
            setSyncStamm(iSttANR,iStamm,iRolMA,0);
            setSyncStamm(iSttANR,Static.AicToVec(iStamm),0);
          }
        }

	private boolean writeLoginInfo()
	{
		//SQL Insert = new SQL(this);
    lLastCheckAus=Static.get_ms();
		bHS=false;
		bErsatz=false;
        bLizenzFrei=iMandant==1;
        Qry=new SQL(this);
		Qry.add("Aic_Systeminfo",iSysinfo);
		Qry.add("Aic_Computer",iComputer);
		Qry.add("Aic_Mandant",iMandant);
		Qry.add("Aic_Benutzer",iBenutzer);
        Qry.add("C_Number",Sid());
		Qry.add("Version",Version.getVer2());
		Qry.addnow("EIN");
		Qry.addnow("MOM");
		Qry.add("status",0);
		Qry.add0("aic_code",iAnlage);
		iLog = Qry.insert("Logging",true);
                lLastProt=Static.get_ms();
                if (iLog<=0)
                  return false;
                //testInfo("writeLoginInfo:"+Sid()+"/"+iBenutzer+"/"+iMandant+"/"+iComputer+"->"+iLog);
                if (!ASA() && !SQL.exists(this,"select connid from bereich where connid=?",""+Sid()))
                {
                  fixInfo("connid="+Sid()+" in Bereich angelegt!");
                  exec("insert into bereich (connid) values ("+Sid()+")");
                  setVonBis(getVon(),getBis(),true);
                }
                iStammHaupt=iStamm;
                ToolTipManager.sharedInstance().setDismissDelay(30000);
                //ToolTipManager.sharedInstance().setInitialDelay(2000);
                //MultiLineToolTipUI.setMaximumWidth(250);
                UIManager.put("ToolTip.background", Color.white);
                //ToolTipManager.sharedInstance().setLightWeightPopupEnabled(true);
                if (Def())
                {
                  //iInfos|=NO_CACHE;
                  //Static.bCachen = false;
//                  setJavaFX(true);                 
                  Static.bAutoEdit=true;
                  //Static.bJavaFX=!Static.Leer(Static.DirCss);
                  //Static.bShowStyle=true;
                  //Static.bShowDB=true;
                }
		//Insert.exec("set ich="+iBenutzer);
		//Insert.exec("set meinStamm="+iStamm);
		//Insert.free();

                /*iStt=iStamm==0 ? 0:SQL.getInteger(this,"select aic_stammtyp from stamm where aic_stamm="+iStamm);
                if (iStt>0)
                  setSyncStamm(iStt,iStamm);*/

                //setSyncStamm();

		iMandantLog = iMandant;
                int iHG_Form=getInteger("select aic_hauptgruppe from hauptgruppe where kennung='Formular'","HG_Form");
                TabSplitPos = new Tabellenspeicher(this,"select aic_parameter,"+int1()+" aic,"+int2()+" alt,"+int2()+" neu from Parameter join Nebengruppe N on n.aic_nebengruppe=parameter.aic_nebengruppe"+
                                                   " where aic_hauptgruppe="+iHG_Form+" and N.Kennung='SplitPos' and aic_computer="+iComputer+" and aic_benutzer=" + iBenutzer +" order by "+int1(), true);
                TabSplitPos.setTitel("TabSplitPos");
                //if (TabSplitPos.VecTitel.size()==0)
                //  TabSplitPos=null;
                TabFensterpos = new Tabellenspeicher(this,"select aic_fensterpos,"+isnull()+"aic_begriff,-aic_stammtyp) vFenster,(select defbezeichnung from begriff where aic_begriff=fensterpos.aic_begriff) Fenster"
                		+ ",x,y,w,h,zoom,null status,bits,satz from fensterpos join logging on fensterpos.aic_logging=logging.aic_logging where "+
                		(OC() || Static.bND ? "":"aic_computer="+iComputer+" and")+" aic_benutzer="+iBenutzer+" order by vfenster,aic_fensterpos desc",true);
                int iOld=0;
                for (TabFensterpos.moveFirst();!TabFensterpos.eof();TabFensterpos.moveNext())
                {
                  int iMom=TabFensterpos.getI("vFenster");
                  if (iOld != iMom)
                    iOld=iMom;
                  else
                    TabFensterpos.setInhalt("status","del");
                }
                TabFensterpos.setTitel("TabFensterpos");

                //TabFensterpos.showGrid("TabFensterpos");
                /*if (SAP())
                {
                  //TabFensterpos = new Tabellenspeicher(this,new String[] {"aic_fensterpos","vFenster","x","y","w","h","status"});
                  TabAbfragen = new Tabellenspeicher(this,new String[] {"int1","int2","int3","int4"});
                  TabFilter = new Tabellenspeicher(this,new String[] {"int1","int2","int3","int4"});
                }
		else
                {*/
              // für Abfragen in Outliner und Tabelle: int1=Formular (Begriff), int2=Original-Abfrage, int3= Benutzerabfrage, int4: 1=ausgeblendet
                /*TabUserAbfragen = new Tabellenspeicher(this,"select aic_parameter,"+int1_int4()+",null status from Hauptgruppe H join Parameter on h.aic_Hauptgruppe=parameter.aic_Hauptgruppe join Nebengruppe N on n.aic_nebengruppe=parameter.aic_nebengruppe"+
                                                     " where H.Kennung='Formular' and N.Kennung='Abfrage' and aic_benutzer=" + iBenutzer +" order by "+int1(), true);
                TabUserAbfragen.sGruppe = "int1";
                TabUserAbfragen.sAIC = "int2";*/
              // für Ersatzabfrage bei AUComboList: int1=Formular (Begriff), int2=Eigenschaft, int3=Original-Abfrage, int4= Benutzerabfrage
                TabFilter = new Tabellenspeicher(this,"select aic_parameter,Parameterzeile,"+int1_int4()+",null status from Parameter join Nebengruppe N on n.aic_nebengruppe=parameter.aic_nebengruppe"+
                                                   " where aic_hauptgruppe="+iHG_Form+" and N.Kennung='Filter' and aic_benutzer=" + iBenutzer +" order by "+int1(), true);
                TabFilter.sGruppe = "int1";
                TabFilter.sAIC = "int2";
                TabFilter.setTitel("TabFilter");
              // neu: Abfragen für Outliner und Tabelle: int1=aic_Darstellung, int2=Abfrage, int3: 1..ausblenden, 2..Tabelle, ...
                TabPersAbfragen = new Tabellenspeicher(this,"select aic_parameter,"+int1_int4()+",null status from Parameter join Nebengruppe N on n.aic_nebengruppe=parameter.aic_nebengruppe"+
                    " where aic_hauptgruppe="+iHG_Form+" and N.Kennung='Abfrage2' and aic_benutzer=" + iBenutzer +" order by "+int1(), true);
                TabPersAbfragen.setTitel("TabPersAbfragen");
//                TabPersJoker = new Tabellenspeicher(this,"select aic_parameter,"+int1_int4()+",null status from Parameter join Nebengruppe N on n.aic_nebengruppe=parameter.aic_nebengruppe"+
//                    " where aic_hauptgruppe="+iHG_Form+" and N.Kennung='Joker' and aic_benutzer=" + iBenutzer +" order by "+int1(), true);
//                TabPersJoker.setTitel("TabPersJoker");
                
                TabGF = new Tabellenspeicher(this,"select aic_parameter,"+int1_int4()+",null status from Parameter join Nebengruppe N on n.aic_nebengruppe=parameter.aic_nebengruppe"+
                        " where aic_hauptgruppe="+iHG_Form+" and N.Kennung='GruppenFilter' and aic_benutzer=" + iBenutzer +" order by "+int1(), true);
                TabGF.setTitel("TabGF");
                TabPos = new Tabellenspeicher(this,"select aic_parameter,Parameterzeile,"+int1_int4()+",null status from Parameter join Nebengruppe N on n.aic_nebengruppe=parameter.aic_nebengruppe"+
                        " where aic_hauptgruppe="+iHG_Form+" and N.Kennung='Spalten' and aic_benutzer=" + iBenutzer +" order by "+int1()+","+int2(), true);
                TabPos.sGruppe = "int1";
                TabPos.sAIC = "int2";
                TabPos.setTitel("TabPos");
//                if (Info())
                  checkTime();

                //int iSperren=MySQL() ? 0:exec("update begriff set log_aic_logging=null where aic_begriff in(select aic_begriff from begriff join logging on begriff.log_aic_logging=logging.aic_logging where aus is not null)");
                checkDefSperren();
                if (SQL.getInteger(this,"select count(*) from sperre")>0)
                {
                  fixtestInfo("prüfe Sperren");
                  int i=Qry.deleteFrom("Sperre","sperre join protokoll p on sperre.aic_protokoll=p.aic_protokoll join logging l on p.aic_logging=l.aic_logging and aus is not null");
                  fixtestInfo(i+" Daten-Sperren entfernt");
                  //iSperren+=i;
                }
                
                //TabMA2=new Tabellenspeicher(this,"select d.aic_bewegungstyp,d.aic_eigenschaft,a.datum from abschluss a join abschlussdefinition d on a.aic_abschlusstyp=d.aic_abschlusstyp"+
                //                            " join abschlusstyp t on d.aic_abschlusstyp=t.aic_abschlusstyp where a.pro_aic_protokoll is null and "+SQL.bit("bits",1),true);
                //if (!TabMA2.isEmpty())
                //  TabMA2.showGrid("MA2");

		//bWWWConnection=checkInternet();
		//fixInfo("Internet: "+bWWWConnection);
                return true;
	}
	
	public void checkDefSperren()
	{
		Vector<Integer> Vec=SQL.getVector("select aic_begriff from begriff join logging on begriff.log_aic_logging=logging.aic_logging where aus is not null",this);
        int iSperren=0;
        if (Vec.size()>0)
          iSperren=exec("update begriff set log_aic_logging=null where "+in("aic_begriff",Vec));
        if (iSperren>0)
            fixtestInfo(iSperren+" Definitionssperren entfernt");
	}

	/*private boolean checkInternet()
	{
		try
		{
			new URL("http://www.foxbyte.com").openConnection().connect();
			return true;

		}
		catch(MalformedURLException e)
		{
			//Static.printError("WWW.isValid(): MalformedURLException - "+e);
			return false;
		}
		catch(java.io.IOException e)
		{
			//Static.printError("WWW.isValid(): IOException - "+e);
			return false;
		}
	}*/

        //@SuppressWarnings("unchecked")
        private void addVec(Vector<Integer> Vec,String s,String sFile)
        {
          long lClock=Static.get_ms();
          if (!Static.cache())
            SQL.addVector(Vec,s,this);
          else if (LoadVec(sFile,Vec))
          {
            if (QrySave())
              saveSqlTime("LoadVec", s.length(), Static.get_ms() - lClock, s, Vec.size(),-2);
          }
          else
          {
            SQL.addVector(Vec,s,this);
            SaveVec(sFile,Vec);
          }
        }
        public boolean LoadVec(String sFileName,Vector<Integer> Vec)
        {
          //long lClock2 = Static.get_ms();
          if (Static.getTempFile(sFileName).exists())
          {
            try
            {
              BufferedReader Buf = new BufferedReader(new FileReader(Static.getTempFile(sFileName)));
              String s=Buf.readLine();
              while (s != null)
              {
                  Integer i = Integer.valueOf(s);
                  if(i!=null && !Vec.contains(i))
                          Vec.addElement(i);
                  s=Buf.readLine();
              }
              Buf.close();
              //clockInfo("LoadVec "+sFileName,lClock2);
              return true;
            }catch(Exception io)
            {
                    printError("Global.LoadVec(): Exception - "+io);
                    printStackTrace(io);
                    return false;
            }
          }
          else
            return false;

        }
        private void SaveVec(String sFileName,Vector Vec)
        {
          long lClock2 = Static.get_ms();
          try
          {
            FileOutputStream fos = new FileOutputStream(Static.getTempFile(sFileName), false);
            String s;
            for(int i=0;i<Vec.size();i++)
            {
              s=Vec.elementAt(i)+"\r\n";
              fos.write(s.getBytes());
            }
            fos.close();
          }
          catch (IOException ex)
          {
            printError("Global.SaveVec: open/close - "+ex);
          }
          clockInfo("SaveVec "+sFileName,lClock2);
        }

	@SuppressWarnings("unchecked")
	public void fillBerechtigung(int iErsatz)
	{
		if (iErsatz>0)
          fixtestInfo("fillBerechtigung: Ersatz="+iErsatz);
//		VecBG_Formular.removeAllElements();
		VecBG.removeAllElements();
                boolean bReadOnly=true;
		SQL Qry = new SQL(this);
                if (TabBerechtigung==null)
                {
                  TabBerechtigung = new Tabellenspeicher(this, new String[] {"AIC", "KENNUNG", "bits", "Vec", "VecStt", "VecStamm", "VecAbfrage", "AIC_STAMM"});
                  //TabBerechtigung.setTitel("TabBerechtigung");
                }
                else
                  TabBerechtigung.clearAll();
		//Qry.open("select b.aic_benutzergruppe,b.bits from benutzer_zuordnung z join benutzergruppe b on z.aic_benutzergruppe=b.aic_benutzergruppe where aic_benutzer="+iBenutzer);
                int iBG=0;
                //new Tabellenspeicher(this,"select distinct b.aic_benutzergruppe,b.kennung,b.bits,z.aic_abfrage,z.aic_stamm from benutzergruppe b left outer join benutzer_zuordnung z on z.aic_benutzergruppe=b.aic_benutzergruppe"+
                //         " where aic_benutzer="+iBenutzer+" or "+SQL.bit("b.bits",cstJeder)+" order by b.aic_benutzergruppe",true).showGrid("fillBerechtigung");
                String sSQL=null;
                if (iErsatz==0)
                  sSQL="select distinct b.aic_benutzergruppe,b.kennung,b.bits,z.aic_abfrage,z.aic_stamm from benutzergruppe b left outer join benutzer_zuordnung z on z.aic_benutzergruppe=b.aic_benutzergruppe"+
                	" where"+bitis("b.bits",Static.bWeb ? cstNurAll:cstNurWeb,0)+" and (aic_benutzer="+iBenutzer+" or "+bit("b.bits",cstJeder)+getReadMandanten(false,"b")+") order by b.aic_benutzergruppe";
                    //" where aic_benutzer="+iBenutzer+" or "+bit("b.bits",cstJeder)+getReadMandanten(false,"b")+" order by b.aic_benutzergruppe";
                else
                  sSQL="select distinct b.aic_benutzergruppe,b.kennung,b.bits,z.aic_abfrage,z.aic_stamm from benutzergruppe b left outer join benutzer_zuordnung z on z.aic_benutzergruppe=b.aic_benutzergruppe"+
                    " where"+bitis("b.bits",Static.bWeb ? cstNurAll:cstNurWeb,0)+" and (aic_benutzer="+iErsatz+" and z.aic_stamm="+iStammHaupt+" or "+bit("b.bits",cstJeder)+getReadMandanten(false,"b")+") order by b.aic_benutzergruppe";
                Qry.open(sSQL);
//                fixtestError("fillBerechtigung-BG-SQL="+sSQL);
                //fixtestError("fillBerechtigung0: ReadOnly="+bReadOnly+"/"+(iBG_Bits&cstReadOnly)+"/"+ReadOnly());
		for(;!Qry.eof();Qry.moveNext())
		{
			//bHistory = bHistory || Qry.getB("history");
			//bAbfrage = bAbfrage || Qry.getB("abfrage");
                        if (iBenutzergruppe==0)
                        {
                          bReadOnly = bReadOnly && (Qry.getI("bits") & cstReadOnly) > 0;
                          iBG_Bits |= Qry.getI("bits");
                          //fixtestError("fillBerechtigung1: ReadOnly="+bReadOnly+"/"+ReadOnly()+" aus "+(Qry.getI("bits") & cstReadOnly));
                        }
			VecBG.addElement(new Integer(Qry.getI("aic_benutzergruppe")));
                        //int iPos=-1;
                        if (iBG != Qry.getI("aic_benutzergruppe"))
                        {
                          iBG = Qry.getI("aic_benutzergruppe");
                          TabBerechtigung.newLine();
                          TabBerechtigung.setInhalt("AIC",iBG);
                          TabBerechtigung.setInhalt("KENNUNG",Qry.getS("KENNUNG"));
                          TabBerechtigung.setInhalt("bits",Qry.getI("bits"));
                          TabBerechtigung.setInhalt("aic_stamm",Qry.getI("aic_stamm"));
                          int iAbf=Qry.getI("aic_abfrage");
                          if (iAbf>0)
                          {
                            Vector<Integer> Vec=new Vector<Integer>();
                            Vec.addElement(new Integer(iAbf));
                            TabBerechtigung.setInhalt("VecAbfrage",Vec);
                          }
                        }
                        else
                        {
                          Vector<Integer> Vec=/*iPos<0 ? new Vector<Integer>():*/(Vector)TabBerechtigung.getInhalt("VecAbfrage");
                          Vec.addElement(Qry.getInt("aic_abfrage"));
                        }
		}
                Qry.free();
                if (iBenutzergruppe>0)
                  iBG_Bits=SQL.getInteger(this,"select bits from benutzergruppe where aic_benutzergruppe="+iBenutzergruppe);
                else if (!bReadOnly && (iBG_Bits & cstReadOnly)>0)
                  iBG_Bits^=cstReadOnly;
                //fixtestError("fillBerechtigungx: ReadOnly="+bReadOnly+"/"+(iBG_Bits&cstReadOnly)+"/"+ReadOnly());
                //TabBerechtigung.showGrid("TabBerechtigung");
                //fixInfo("*** ReadOnly="+bReadOnly+"/"+((iBG_Bits & cstReadOnly)>0)+" iBG="+iBenutzergruppe+" / bits="+iBG_Bits);
                //testInfo("iBG_Bits="+iBG_Bits);

                // Formulare mit Monatsabschluß
                TabMA=new Tabellenspeicher(this,"select d.aic_bewegungstyp,d.aic_eigenschaft,a.datum,a.aic_abfrage,null Daten,t.bits,a.ab,a.weiter,a.aic_abschluss,t.prog,t.kennung,t.aic_abschlusstyp from abschluss a join abschlussdefinition d on a.aic_abschlusstyp=d.aic_abschlusstyp and a.pro_aic_protokoll is null"/*" and (a.ab is null or a.ab<"+now()+")"*/+getReadMandanten("a")+
                                           " join abschlusstyp t on d.aic_abschlusstyp=t.aic_abschlusstyp left outer join abschluss_Zuordnung z on t.aic_abschlusstyp=z.aic_abschlusstyp where"+bit("t.bits",1)+
                                           (SuperUser() && VecBG.size()==0 ? "":" or z.aic_benutzergruppe"+Static.SQL_in(VecBG))+" order by d.aic_bewegungstyp",true);
                TabMA.setTitel("TabMA");
                checkAbschluss();
                //if (TestPC())
                //  TabMA.showGrid("MA");
//                if (TabMA.isEmpty() && iAbschluss>0 && iAbschlussDef>0 && ASA())
//                {
//                        TabFormularSperre=new Tabellenspeicher(this,"select aic_begriff,list(aic_stamm) Abschluss from bew_stamm join bewview2 join bew_begriff where aic_bewegungstyp="+iAbschlussDef+" group by aic_begriff",true);
//                        TabFormularSperre.setTitel("TabFormularSperre");
//                        SQL.addVector(VecBG_Formular,"select distinct bew_begriff.aic_begriff from benutzergruppe"+join("stamm","benutzergruppe")+join2("bew_stamm","stamm")+join("bewview2","bew_stamm","bew_pool")+join("bew_begriff","bewview2","bew_pool")+" where aic_bewegungstyp="+iAbschlussDef+" and aic_benutzergruppe"+Static.SQL_in(VecBG),this);
//                }
                // Eigenschaften begrenzen
		VecEigenschaften.removeAllElements();
                //Static.addVectorI(VecEigenschaften,VecJederEig);
		//addVec(VecEigenschaften,"select aic_eigenschaft from eigenschaft where "+bit("bits",cstJeder),"EigenschaftJeder");
                SQL.addVector(VecEigenschaften,"select distinct aic_fremd from berechtigung where aic_tabellenname="+TabTabellenname.getAic("EIGENSCHAFT")+" and aic_benutzergruppe"+Static.SQL_in(VecBG),this);
                // fixtestError("VecBG="+VecBG);
                // Bewegungstypen begrenzen
                TabModulBerechtigt=new Tabellenspeicher(this,"select distinct modul.aic_tabellenname,modul.aic_fremd from berechtigung join modul on modul.aic_begriff=berechtigung.aic_fremd and berechtigung.aic_tabellenname="+iTabBegriff+
                                                        " where aic_benutzergruppe"+Static.SQL_in(VecBG)+" order by modul.aic_tabellenname",true);
                TabModulBerechtigt.sGruppe="aic_tabellenname";
                TabModulBerechtigt.sAIC="aic_fremd";
                //progInfo("VecEigenschaften1="+VecEigenschaften);
                Static.addVectorI(VecEigenschaften,TabModulBerechtigt.getVec("aic_tabellenname",new Integer(TabTabellenname.getAic("EIGENSCHAFT")),"aic_fremd"));
                testInfo("VecEigenschaften="+VecEigenschaften);
                //TabModulBerechtigt.showGrid("TabModulBerechtigt");

                //VecBew.removeAllElements();
                //SQL.addVector(VecBew,"select distinct aic_fremd from benutzer_Zuordnung"+SQL.join("benutzergruppe","benutzer_Zuordnung")+SQL.join2("berechtigung","benutzergruppe")+SQL.join("tabellenname","berechtigung")+" where tabellenname.kennung='Bewegungstyp' and aic_benutzer="+iBenutzer,this);

                //testInfo("VecBew="+VecBew);
                
                VecHS.removeAllElements();
                SQL.addVector(VecHS,"select distinct aic_fremd from berechtigung where aic_tabellenname="+TabTabellenname.getAic("HAUPTSCHIRM")+" and aic_benutzergruppe"+Static.SQL_in(VecBG),this);
//                fixtestError("VecHS="+VecHS);

                // neue Berechtigung
                VecBegBerechtigt=new Vector();
                for(int iPos=0;iPos<TabBerechtigung.size();iPos++)
                {
                  iBG=TabBerechtigung.getI(iPos,"AIC");
                  Vector VecModule=SQL.getVector("select b.aic_begriff from tabellenname t"+join("berechtigung","t","tabellenname")+",begriff b where aic_benutzergruppe="+iBG+" and t.kennung='Begriff' and b.aic_begriff=aic_fremd and b.aic_begriffgruppe="+TabBegriffgruppen.getAic("Modul"),this);
                  //testInfo("VecModule für "+iBG+"="+VecModule);
                  Vector Vec=new Vector();
                  if (VecModule.size()>0)
                    SQL.addVector(Vec,"select distinct beg_aic_begriff from begriff_zuordnung where aic_begriff"+Static.SQL_in(VecModule),this);
                  SQL.addVector(Vec,"select distinct b.aic_fremd aic from berechtigung b join tabellenname on tabellenname.aic_tabellenname=b.aic_tabellenname where tabellenname.kennung='Begriff' and aic_benutzergruppe="+iBG,this);
                  TabBerechtigung.setInhalt(iPos,"Vec",Vec);
                  Static.addVector(VecBegBerechtigt,Vec);
                }
//                fixtestError("VecBegBerechtigt für "+iBG+"="+VecBegBerechtigt);
                if (TestPC() && Info() && Static.bX11)
                  TabBerechtigung.showGrid();

		// alte Berechtigung
		/*Vector VecModule=SQL.getVector("select b.aic_begriff from tabellenname t"+SQL.join("berechtigung","t","tabellenname")+SQL.join("benutzergruppe","berechtigung")+SQL.join2("benutzer_zuordnung","benutzergruppe")+",begriff b where (aic_benutzer="+iBenutzer+" or "+SQL.bit("benutzergruppe.bits",cstJeder)+") and t.kennung='Begriff' and b.aic_begriff=aic_fremd and b.aic_begriffgruppe="+TabBegriffgruppen.getAic("Modul"),this);
                //progInfo("VecModule="+VecModule);
                VecBegBerechtigt=new Vector();
                if (VecModule.size()>0)
                  SQL.addVector(VecBegBerechtigt,"select distinct beg_aic_begriff from begriff_zuordnung where aic_begriff"+Static.SQL_in(VecModule),this);
                SQL.addVector(VecBegBerechtigt,"select distinct b.aic_fremd aic from benutzer_Zuordnung join berechtigung b on benutzer_Zuordnung.aic_benutzergruppe=b.aic_benutzergruppe join tabellenname on tabellenname.aic_tabellenname=b.aic_tabellenname where tabellenname.kennung='Begriff' and aic_benutzer="+iBenutzer,this);
                */
                VecNurSelbst=SQL.getVector("select distinct b.aic_fremd aic from berechtigung b join tabellenname on tabellenname.aic_tabellenname=b.aic_tabellenname where tabellenname.kennung='Begriff' and "+bit("b.bits",Berechtigung.SELBST)+" and aic_benutzergruppe"+Static.SQL_in(VecBG),this);
                VecNicht=SQL.getVector("select distinct b.aic_fremd aic from berechtigung b join tabellenname on tabellenname.aic_tabellenname=b.aic_tabellenname where tabellenname.kennung='Begriff' and "+bit("b.bits",Berechtigung.NICHT)+" and aic_benutzergruppe"+Static.SQL_in(VecBG),this);
                VecEigNicht=SQL.getVector("select distinct b.aic_fremd aic from berechtigung b join tabellenname on tabellenname.aic_tabellenname=b.aic_tabellenname where tabellenname.kennung='Eigenschaft' and "+bit("b.bits",Berechtigung.NICHT)+" and aic_benutzergruppe"+Static.SQL_in(VecBG),this);
                fixtestInfo("VecNurSelbst="+VecNurSelbst+", VecNicht="+VecNicht+", VecEigNicht="+VecEigNicht);
		//String sBG=VecNull("fillBerechtigung: VecBG",VecBG)?"=-1":Static.SQL_in(VecBG);
//                Static.BtnBeenden=getButtonS("Beenden");
                //Static.BtnBeenden.setPreferredSize(Static.D);
//                Static.BtnExport=getButtonS("Export");
//                Static.BtnDruck=getButtonS("Druck");
                //Static.BtnExport.setPreferredSize(Static.D);
                Static.BtnAbbruch=getButtonS("Abbruch");
                //fixtestError("fillBerechtigungy: ReadOnly="+bReadOnly+"/"+(iBG_Bits&cstReadOnly)+"/"+ReadOnly());
	}

        public void checkAbschluss()
        {
          Date dt=new Date();
          Vector<Integer> Vec=new Vector<Integer>();
          for (int i=0;i<TabMA.size();i++)
            if (!TabMA.isNull(i,"ab"))
            {
              //Date dtDatum = TabMA.getDate(iPos,"Datum");
              Date dtAb = TabMA.getDate(i,"Ab");
              if (dt.after(dtAb))
                //fixInfo("checkAbschluss: fortschreiben:"+TabMA.getDate(i,"Datum")+"/"+TabMA.getI(i,"aic_abschluss"));
                Vec.addElement(TabMA.getI(i,"aic_abschluss"));
              else
              {
                TabMA.setInhalt(i,"Datum",addWeiter(TabMA.getDate(i,"Datum"),TabMA.getI(i,"weiter"),false));
                TabMA.setInhalt(i,"ab",null);
              }
            }
          if (Vec.size()>0)
          {
            int iProt=Protokoll("Abschluss",0);
            Tabellenspeicher Tab=new Tabellenspeicher(this,"select * from abschluss where pro_aic_protokoll is null and"+in("aic_abschluss",Vec),true);
            for(Tab.moveFirst();!Tab.out();Tab.moveNext())
            {
              SQL Qry=new SQL(this);
              Qry.add("AIC_Abschlusstyp",Tab.getI("AIC_Abschlusstyp"));
              Qry.add("Datum",addWeiter(Tab.getDate("Datum"),Tab.getI("weiter"),true));
              Qry.add("AIC_Protokoll",iProt);
              Qry.add0("AIC_Abfrage",Tab.getI("AIC_Abfrage"));
              Qry.add("Ab",addWeiter(Tab.getDate("Ab"),Tab.getI("weiter"),true));
              Qry.add("weiter",Tab.getI("weiter"));
              Qry.add("AIC_Mandant",Tab.getI("AIC_Mandant"));
              Qry.insert("Abschluss",true);
              Qry.free();
              exec("update abschluss set pro_aic_protokoll="+iProt+" where aic_abschluss="+Tab.getI("AIC_Abschluss"));
            }
            bAbschluss=true;
          }
        }
        
        private Date addWeiter(Date dt,int iWeiter,boolean bAdd)
        {
        	DateWOD DW=new DateWOD(dt);
        	if (iWeiter==-2 || iWeiter==0) // Monat
        		if (bAdd) DW.nextMonth(); else DW.prevMonth();
        	else if (iWeiter==-3)
        		if (bAdd) DW.next("Quartal"); else DW.prev("Quartal");
        	else if (iWeiter==-4)
        		if (bAdd) DW.nextYear(); else DW.prevYear();
        	else if (iWeiter<0)
        		printError("Global.addWeiter mit "+iWeiter+" wird noch nicht unterstützt");
        	else
        		for (int i=0;i<iWeiter;i++)
        			if (bAdd) DW.tomorrow(); else DW.yesterday();
        	return DW.toDate();
        }

//        private Date addMonth(Date dt,boolean bAdd)
//        {
//          DateWOD DW=new DateWOD(dt);
//          if (bAdd)
//            DW.nextMonth();
//          else
//            DW.prevMonth();
//          return DW.toDate();
//        }
        
        public String getBerechtigt(int iBegriff)
        {
        	if (SuperUser())
        		return "SuperUser";
        	if ((iBegriff==0) || isJeder(iBegriff))
        		return "Jeder";
        	String s=null;
        	for (int iPos=0;iPos<TabBerechtigung.size();iPos++)
            {
        		Vector Vec=(Vector)TabBerechtigung.getInhalt("Vec",iPos);
//        		Vector VecStt=(Vector)TabBerechtigung.getInhalt("VecStt",iPos);
        		if (Vec.contains(iBegriff))// && VecStt.contains(iStt))
        			s=(s==null ? "BG ":s+", ")+TabBerechtigung.getI(iPos,"Aic");
            }
        	return s==null ? "Nie":s;
        }

        public Vector<Integer> getVecStamm2(int iStt)
        {
//        	fixtestError("getVecStamm2 mit "+iStt+" bei "+sUser+"/"+(iBits&cstBenutzerEbene));
          if (SuperUser())
            return null;
          Vector<Integer> Vec=null;
          for (int iPos=0;iPos<TabBerechtigung.size();iPos++)
          {
            Vector<Integer> VecStt = (Vector<Integer>)TabBerechtigung.getInhalt("VecStt",iPos);
            if(VecStt != null && VecStt.contains(iStt))
            	if (Vec==null)
            		Vec=new Vector<Integer>((Vector<Integer>)TabBerechtigung.getInhalt("VecStamm",iPos));
            	else
            		Static.addVectorI(Vec, (Vector<Integer>)TabBerechtigung.getInhalt("VecStamm",iPos));
//              return(Vector)TabBerechtigung.getInhalt("VecStamm",iPos);
          }
//          TabBerechtigung.showGrid("TabBerechtigung");
//          fixtestError("->"+Vec);
          return Vec;
        }

        @SuppressWarnings("unchecked")
        public void addToVecStamm(int iStt,int iStamm)
        {
          if (SuperUser())
            return;
          for (int iPos=0;iPos<TabBerechtigung.size();iPos++)
          {
            Vector VecStt = (Vector)TabBerechtigung.getInhalt("VecStt",iPos);
            if(VecStt != null && VecStt.contains(new Integer(iStt)))
              ((Vector)TabBerechtigung.getInhalt("VecStamm",iPos)).addElement(new Integer(iStamm));
          }
        }

        @SuppressWarnings("unchecked")
	public Vector<Integer> getVecStamm(int iBegriff,int iStt)
        {
          if (SuperUser())
            return null;
          if ((iBegriff==0) || Static.bWeb && isJeder(iBegriff))
        	return getVecStamm2(iStt);
          Integer IntBegriff=iBegriff;
          Integer IntStt=iStt;
          Vector<Integer> VecStamm=new Vector<Integer>();
          boolean bGefunden=false;
          for (int iPos=0;iPos<TabBerechtigung.size();iPos++)
          {
            Vector Vec=(Vector)TabBerechtigung.getInhalt("Vec",iPos);
            Vector VecStt=(Vector)TabBerechtigung.getInhalt("VecStt",iPos);
            if (Vec.contains(IntBegriff))
            {
              bGefunden=true;
              if(VecStt != null && VecStt.contains(IntStt))
                Static.addVectorI(VecStamm, (Vector)TabBerechtigung.getInhalt("VecStamm",iPos));
              else
                return null;
            }
          }
          if (!bGefunden)
          {
        	fixtestError("getVecStamm mit Stt="+iStt+" nicht gefunden: "+iBegriff);
            VecStamm=getVecStamm2(iStt);
          }
//          testInfo("getVecStamm "+iBegriff+"/"+iStt+"="+VecStamm);
          return VecStamm;
        }

        public Tabellenspeicher saveJokerStt(boolean bDel)
        {
          Tabellenspeicher Tab=new Tabellenspeicher(this,new String[] {"Aic","Stamm","Vec"});
          for (int i=0;i<TabStammtypen.size();i++)
            if (!TabStammtypen.isNull(i,"Sync") || !TabStammtypen.isNull(i,"Vec"))
            {
              Tab.addInhalt("Aic",TabStammtypen.getI(i,"Aic"));
              Tab.addInhalt("Stamm",TabStammtypen.getI(i,"Sync"));
              Tab.addInhalt("Vec",TabStammtypen.isNull(i,"Vec")?null:Sort.getIntVec(TabStammtypen.getInhalt("Vec",i)));
              if (bDel)
              {
                if (TabStammtypen.getI(i,"Sync")>0)
                  defInfo2("Setze Stt "+TabStammtypen.getS(i,"Kennung")+": "+TabStammtypen.getI(i,"Sync")+"-> null");
                TabStammtypen.setInhalt(i, "Sync", null);
                TabStammtypen.setInhalt(i, "Vec", null);
              }
            }
          return Tab;
        }

        public void loadJokerStt(Tabellenspeicher Tab)
        {
          if (Tab!=null)
            for (int i=0;i<TabStammtypen.size();i++)
            {
              int iPos=Tab.getPos("Aic",TabStammtypen.getI(i,"Aic"));
              TabStammtypen.setInhalt0(i, "Sync",iPos<0 ? 0:Tab.getI(iPos,"Stamm"));
              if (iPos<0 || Tab.isNull(iPos,"Vec"))
                TabStammtypen.setInhalt(i, "Vec",null);
              else
                TabStammtypen.setInhalt(i, "Vec", Sort.getIntVec(Tab.getInhalt("Vec",iPos)));
              if (iPos>=0 && Debug())
                debugInfo("Joker für "+TabStammtypen.getS(i,"Bezeichnung")+":"+TabStammtypen.getI(i,"Sync")+"/"+TabStammtypen.getInhalt("Vec",i));
            }
        }

	/*public Vector SQL_CboStt(int iStt)
	{
          if (true)
            return null;
		else if (SuperUser() || !ASA() || !TabStammBer.posInhalt("aic_stammtyp",iStt))
			return null;
		else
		{
			Vector Vec=new Vector();
			int iEig=TabStammBer.getI("aic_eigenschaft");
			Vec.addElement(",(select sta_aic_stamm from poolview where aic_stamm=p2.aic_stamm and aic_eigenschaft="+iEig+") b"+iEig);
			Vec.addElement(" and b"+iEig+" in("+TabStammBer.getM("stamm")+")");
			//(VecBS!=null && !VecBS.elementAt(2).equals(" ")?"and aic_stamm in ("+VecBS.elementAt(2)+")":"")
			String sStamm2=TabStammBer.getM("stamm2");
			Vec.addElement(sStamm2.equals(" ")?"":" and p2.aic_stamm in ("+sStamm2+")");
			return Vec;
		}
		//return "Select aic_stamm,bezeichnung kennung"+AU_Bezeichnung("Stamm","stammview")+sEig+
		//		" from stammview where aic_stammtyp="+iStt+getReadMandanten(false)+sWhere;
	}*/
	private void checkFehlLogs()
	{
		fixInfoT("checkFehlLogs:"+iFehlLogs+"/"+iMaxLog);
		iFehlLogs++;
		diskInfo("Global.checkFehlLogs(): "+iFehlLogs+"/"+iMaxLog+" Fehl-Log !");
		if (iMaxLog>0 && iFehlLogs >= iMaxLog)
		{
			//SQL.exec(this,"Update Computer set gesperrt=now() where aic_computer ="+iComputer);
//                        SQL Qry=new SQL(this);
//                        Qry.addnow("gesperrt");
//                        Qry.update("Computer",iComputer);
//                        Qry.free();
//			diskInfo("Global.checkFehlLogs(): Computer gesperrt !");
			//addInfo("Computer gesperrt !!!");
			bGesperrt = true;
		}
	}
	
//	public void checkGesperrt()
//	{
//		int iAnz=SQL.getInteger(this, "select count(*) from computer where gesperrt is not null");
//		if (iAnz>0)
//		{
//			fixtestError("checkGesperrt: "+iAnz);
//			long lNow=SQL.getNow(this).getTime();
//			Tabellenspeicher Tab=new Tabellenspeicher(this,"select aic_computer,gesperrt,kennung from computer where gesperrt is not null",true);
//			for (Tab.moveFirst();!Tab.out();Tab.moveNext())
//			{
//				long lSperre=Tab.getTimestamp("gesperrt").getTime();
//				fixtestError("Computer "+Tab.getI("aic_computer")+" seit "+Tab.getTimestamp("gesperrt")+" gesperrt");//": "+lNow+"/"+lSperre);
//				if (lSperre+5*60000<lNow && exec("update computer set gesperrt=null where aic_computer="+Tab.getI("aic_computer"))>0)
//					fixInfo("Computer "+Tab.getS("kennung")+" entsperrt");
//			}
//		}
//	}
	
    public Image LoadImage(String sFile)
    {
      //progInfo("LoadImage:"+sFile);
      return LoadImage(sFile, Static.DirImageSys);
    }
    public java.awt.Image LoadImage(String sFile,String sDir)
    {
    	return (java.awt.Image)LoadImage(sFile,sDir,false);
    }
//    public javafx.scene.image.Image LoadImageFx(String sFile,String sDir)
//    {
//    	return (javafx.scene.image.Image)LoadImage(sFile,sDir,true);
//    }
    private Object LoadImage(String sFile,String sDir,boolean bFx)
	{
    	if (Static.bInfoBild)
          fixInfo("  *** Load-"+(bFx?"JavaFX":"Swing")+"-Image *** "+sFile+" von Dir "+sDir);
//    	if (bFx && !bUseJavaFX)
//    		setJavaFX(true);
		if (!Static.bBilder || sFile==null || sFile.equals("") || sFile.equals(" "))
			return null;
                if (TabImages == null)
                {
                  TabImages = new Tabellenspeicher(this, new String[] {"Filename", "Anzahl","AnzahlFX","Image","ImageFx"});
                  TabImages.setTitel("TabImages");
                }
                int iPos=TabImages.getPos("Filename",sFile);
		if (iPos>=0)
		{
			TabImages.setInhalt(iPos,"Anzahl"+(bFx?"FX":""),new Integer(TabImages.getI(iPos,"Anzahl"+(bFx?"FX":""))+1));
			if (Static.bInfoBild)
				fixInfo("Bild gefunden auf Pos "+iPos);
			return TabImages.getInhalt(bFx ? "ImageFx":"Image",iPos);
		}
		else
		{
                  iPos=TabImages.newLine();
                  TabImages.setInhalt(iPos,"Filename",sFile);
                  TabImages.setInhalt(iPos,"Anzahl"+(bFx?"FX":""),new Integer(1));
                  TabImages.setInhalt(iPos,"Anzahl"+(bFx?"":"FX"),new Integer(0));
                  Image Img=Static.LoadImage(sFile,sDir);
                  if (Static.bInfoBild) 
                	  fixInfo("Bild geladen -> "+Static.print(Img));
                  if (Img==null || Img.getWidth(new javax.swing.ImageIcon(Img).getImageObserver())<0)
                    fixInfo(sFile+" nicht gefunden");
                  TabImages.setInhalt(iPos,"Image",Img);
//                  if (bUseJavaFX)
//                  {
//                	  javafx.scene.image.Image ImgFx=Img==null /*|| !bFx*/ ? null:new javafx.scene.image.Image(sDir+sFile);
//                	  TabImages.setInhalt(iPos,"ImageFx",ImgFx);
//                	  if (bFx) 
//                		  return ImgFx;
//                  }
                  return bFx? null:Img;
		}
	}
    
//    private void setJavaFX(boolean bFx)
//    {
//    	fixtestError("setJavaFX "+bFx);
//    	if (bFx) TabImages=null;
//    	bUseJavaFX=bFx;
//    }

    public String getFilename(Image Img)
    {
      return getFilename(Img,Static.DirImageSys);
    }

    public String getFilename(Image Img, String sPath)
    {
      String s=(TabImages.posInhalt("Image",Img) ? TabImages.getS("Filename"):"TOP.GIF");
      return s.startsWith("file:")?s:sPath+s;
    }

    /*public ImageIcon LoadImageIcon()
    {
      if (TabBegriffe.getS("BildFile").equals(""))
        return null;
      else
      {
        ImageIcon Img = LoadImageIcon(TabBegriffe.getS("BildFile"), Static.DirImageSys);
        if (Img == null && Static.bBilder)
          printInfo("Das Bild <"+TabBegriffe.getS("BildFile")+"> für Begriff "+TabBegriffe.getS("DefBezeichnung")+
                    "("+TabBegriffgruppen.getBezeichnungS(TabBegriffe.getI("Gruppe"))+") fehlt!");
        return Img;
      }
    }*/

    public ImageIcon LoadImageIcon(int iPos)
    {
    	return LoadImageIcon(iPos,0);
    }
    
    public ImageIcon LoadImageIcon(int iPos,int iArt)
    {
      if (iPos<0)
        return null;
      String s=TabBegriffe.getS(iPos,iArt==0 ? Static.sBild:"BildSel");
      if (s.equals(""))
        return null;
      else
      {
        ImageIcon Img = LoadImageIcon(s, Static.DirImageSys);
        if (Img == null && Static.bBilder && !VecIBE.contains(iPos))
        {
          Static.printError("Das Bild <"+s+"> für Begriff "+TabBegriffe.getS(iPos,"DefBezeichnung")+
                    "("+TabBegriffgruppen.getBezeichnungS(TabBegriffe.getI(iPos,"Gruppe"))+") fehlt!");
          VecIBE.addElement(iPos);
        }
        return Img;
      }
    }

    public ImageIcon LoadImageIcon(String sFile)
    {
      return LoadImageIcon(sFile,Static.DirImageSys);
    }

    public ImageIcon LoadImageIcon(String sFile, String sPath)
    {
      if (!Static.bBilder || sFile==null || sFile.equals(""))
        return null;
      if (TabImageIcons == null)
      {
        TabImageIcons = new Tabellenspeicher(this, new String[] {"Filename", "Anzahl", "ImageIcon"});
        TabImageIcons.setTitel("TabImageIcons");
      }
      int iPos=TabImageIcons.getPos("Filename",sFile);
      if (iPos>=0)
      {
        TabImageIcons.setInhalt(iPos,"Anzahl",new Integer(TabImageIcons.getI(iPos,"Anzahl")+1));
        return (ImageIcon)TabImageIcons.getInhalt("ImageIcon",iPos);
      }
      else
      {
        URL u=Static.toURL(sPath+sFile);
        ImageIcon Img=u==null ? null:new ImageIcon(u);
        if (Img==null)
        	printError("Bild "+sFile+" nicht gefunden auf "+sPath);
        else if (Img.getImageLoadStatus()==MediaTracker.ERRORED)
          Img=null;
        //progInfo("URL="+u+":"+Img.getImageLoadStatus());
        TabImageIcons.addInhalt("ImageIcon",Img);
        TabImageIcons.addInhalt("Anzahl",new Integer(1));
        TabImageIcons.addInhalt("Filename",sFile);
        return Img;
      }
    }
    
    public Global()
    {
    	super();
    }

    public Global(String sODBC_Treiber,String sAnlage)
    {
      this(sODBC_Treiber,sAnlage,null,null,0);
    }
    
    public boolean Mojave()
    {
    	if (Static.bMojave)
    		return true;
    	else if (bMac)
    	{
    		String s=System.getProperty("os.version");
    		//fixtestInfo("OS="+s);
    		return s.startsWith("10.14") || s.startsWith("10.15") || s.startsWith("10.16");
    	}
    	return false;
    }

    public Global(String sODBC_Treiber,String sAnlage,String rsName,String rsPW,int riPArt)
	{
          sDbUser=rsName;
          sDbPW=riPArt==0 ? null:rsPW;
          iPArt=riPArt;
          bMac=System.getProperty("os.name").equals("Mac OS X");
          if (bMac)
            fixInfo("Mac-Computer");
          UIManager.getDefaults().put("ComboBox.disabledForeground", java.awt.Color.BLACK);
          UIManager.getDefaults().put("TextField.inactiveForeground", java.awt.Color.BLACK);
          if (Static.iMaxX==1020)
          {
            try
            {
              GraphicsDevice[] GD=GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
              for (int i=0;i<GD.length;i++)
              {
                //System.out.println("Bildschirmabmessungen" + i + ":" + GD[i].getDefaultConfiguration().getBounds());
                Rectangle R=GD[i].getDefaultConfiguration().getBounds();
                Static.iMinX=Math.min(Static.iMinX,(int)R.getMinX());
                Static.iMinY=Math.min(Static.iMinY,(int)R.getMinY());
                Static.iMaxX=Math.max(Static.iMaxX,(int)R.getMaxX());
                Static.iMaxY=Math.max(Static.iMaxY,(int)R.getMaxY());
              }
              fixInfo("Koordinaten:"+Static.iMinX+"/"+Static.iMinY+" - "+Static.iMaxX+"/"+Static.iMaxY);
              //Static.iMaxX = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length == 1 ? Toolkit.getDefaultToolkit().getScreenSize().width : -1;
              //Static.iMaxY = Toolkit.getDefaultToolkit().getScreenSize().height;
            }
            catch (Exception e)
            {
              if (Static.bX11)
                fixInfo("Breite nicht ermittelbar, nehme deshalb 1024");
              Static.iMaxX = 1024;
            }
          }
          //UIManager.getDefaults().put("CheckBox.disabledText", java.awt.Color.GREEN);
          if (connect(sODBC_Treiber))
		{
                  checkCache();
//                  checkAServer();
                  //printSI("connect");
                  /*SQL.bASA=ASA();
                  SQL.bASA10=ASA10();
                  SQL.bOracle=Oracle();
                  //SQL.bSAP=SAP();
                  SQL.bMS=MS();
                  SQL.bMySQL=MySQL();*/
			//checkConnects();
                        TabDialog = new Tabellenspeicher(this,new String[] {"Eig","Obj"});
                        TabDialog.setTitel("TabDialog");
                        TabAmpel = new Tabellenspeicher(this,new String[] {"Eig","Stamm","Status"});
                        TabAmpel.sGruppe="Eig";
                        TabAmpel.sAIC="Stamm";
                        TabAmpel.setTitel("TabAmpel");
			//TabImages = new Tabellenspeicher(this,new String[] {"Filename","Anzahl","Image"});
                        //TabImageIcons = new Tabellenspeicher(this,new String[] {"Filename","Anzahl","ImageIcon"});
			TabBegriffInfo = new Tabellenspeicher(this,new String[] {"Kennung","DefBezeichnung","Anzahl","vorhanden","übersetzt"});
                        TabBegriffInfo.setTitel("TabBegriffInfo");
			TabFormulare = new Tabellenspeicher(this,new String[] {"Aic","Stt","Formular"});
                        TabFormulare.setTitel("TabFormulare");
			TabComboboxen = new Tabellenspeicher(this,new String[] {"Stt","Cbo"});
                        TabComboboxen.setTitel("TabComboboxen");
			TabStammFarbe = new Tabellenspeicher(this,new String[] {"Stamm","Farbe"});
                        TabStammFarbe.setTitel("TabStammFarbe");
			TabTabellenname=new Tabellenspeicher(this,"select aic_tabellenname aic,upper(kennung) kennung from tabellenname",true,"tabtemp");
                        TabFensterpos = new Tabellenspeicher(this,new String[] {"aic_fensterpos","vFenster","x","y","w","h","zoom","Bits","Satz","status"});
                        //TabTabellenname.showGrid("TabTabellenname");
			if (sAnlage != null)
                          setAnlage(sAnlage);
                        //else if(ASA() && iFirstProt==0)  // iFirstProt wird nur zum Anzeigen gelöschter Datensätze verwendet
                        //  iFirstProt=getInteger("select min(aic_protokoll) from logging l join protokoll p on l.aic_logging=p.aic_logging where version>2999","FirstProt");
		}
                //printSI("Global");
                testInfo("Global fertig erzeugt");
	}

        /*public int getFirstProt()
        {
          if (iFirstProt<0)
          {
            iFirstProt=getInteger("select min(aic_protokoll) from logging l join protokoll p on l.aic_logging=p.aic_logging where version>2999","FirstProt");
            fixInfo("ermittle iFirstProt->"+iFirstProt);
          }
          return iFirstProt;
        }*/

        public void setAnlage(String sAnlage)
        {
          iAnlageBenutzer=sAnlage==null ? 0:sAnlage.startsWith("Timer") ? cstTimerBenutzer : sAnlage.startsWith("Terminal") ? cstTerminalUser : sAnlage.startsWith("AServer") ? cstAServerUser: sAnlage.equals("Import") ? cstImport:0;
//                              /*sAnlage.equals("SoftTerm") ? cstSoftTerm:*/sAnlage.equals("Import") ? cstImport:0;
          bImport=iAnlageBenutzer==cstImport;
          if (iAnlageBenutzer>0)
          {
            iAnlage=SQL.getInteger(this,"select aic_code from code"+join("begriffgruppe","g","code","begriffgruppe")+" where g.kennung='Anlage' and code.kennung='"+sAnlage+"'");
            if (iAnlage==0)
            {
                SQL Qry=new SQL(this);
                Qry.add("kennung",sAnlage);
                Qry.add("aic_begriffgruppe",SQL.getInteger(this,"select aic_begriffgruppe from begriffgruppe where kennung='Anlage'"));
                iAnlage=Qry.insert("code",true);
                Qry.free();
            }
          }
          else
            iAnlage=0;
          //testInfo("Anlage="+sAnlage+"/"+iAnlage+", Benutzer="+iAnlageBenutzer);
        }

        @SuppressWarnings("unchecked")
        public Global(Global g,boolean bCopy)
        {
          long lClock2=Static.get_ms();
          sDbUser=g.sDbUser;
          sDbPW=g.sDbPW;
          iPArt=g.iPArt;
          if (connect(g.getJDBC()))
          {
            setVonBis(g.getVon(),g.getBis(),true);
            setZA(0,g.getZA(0));
            iMandantLog=g.iMandantLog;
            //ColBackground=g.ColBackground;
            fontBarcode=g.fontBarcode;
            if (g.VecPerioden != null)
              VecPerioden=(Vector<Timestamp>)g.VecPerioden.clone();
            //TabPerioden=g.TabPerioden;
            TabDialog=g.TabDialog;
            TabAmpel=g.TabAmpel;
            VecBegJeder=g.VecBegJeder;
            VecBegTod=g.VecBegTod;
            if (bCopy)
            {
              VecBegLizenz=g.VecBegLizenz;
              VecBegBerechtigt=g.VecBegBerechtigt;
              TabMass=g.TabMass;
              sUser=g.sUser;
              TabPerioden=g.TabPerioden;
            }
            iAicStunde=g.iAicStunde;
            iLight_Off=g.iLight_Off;
            iLight_Red=g.iLight_Red;
            iLight_Yellow=g.iLight_Yellow;
            iLight_Green=g.iLight_Green;
            g.testInfo("Light: off="+iLight_Off+", red="+iLight_Red+", yellow="+iLight_Yellow+", green="+iLight_Green);
            iSttLight=g.iSttLight;
            iSttANR=g.iSttANR;
            iSttFirma=g.iSttFirma;
            iSttPeriode=g.iSttPeriode;
            iRolMA=g.iRolMA;
            g.testInfo("Stt: Light="+iSttLight+", ANR="+iSttANR+", Firma="+iSttFirma+", Periode="+iSttPeriode);

            TabBegriffInfo=g.TabBegriffInfo;
            // neu seit 7.9.2017 um EF parallel zu starten
            TabFormulare=g.TabFormulare;
            TabSplitPos=g.TabSplitPos;
            TabPersAbfragen=g.TabPersAbfragen;
            TabFilter=g.TabFilter;
            TabComboboxen=g.TabComboboxen;
            VecSttBed=g.VecSttBed;
            		
            TabTabellenname=g.TabTabellenname;
            TabBegriffgruppen=g.TabBegriffgruppen;
            TabBegriffe=g.TabBegriffe;
            TabBegriffM=g.TabBegriffM;
            TabStatus=g.TabStatus;
            TabCodes=g.TabCodes;
            TabFarbe=g.TabFarbe;
            TabSchrift=g.TabSchrift;
            TabStammtypen=new Tabellenspeicher(this,g.TabStammtypen);
            TabMandanten=g.TabMandanten;
            TabRollen=g.TabRollen;
            TabStammBild=g.TabStammBild;
            TabStamm=g.TabStamm;
            TabBSt=g.TabBSt;
            TabH=g.TabH;
            TabEigenschaften=g.TabEigenschaften;
            TabErfassungstypen=g.TabErfassungstypen;
            TabModelle=g.TabModelle;
            TabAbfragen=g.TabAbfragen;
            TabAustritt=g.TabAustritt;
            iLog=g.iLog;
            iSysinfo=g.iSysinfo;
            iComputer=g.iComputer;
            iBenutzer=g.iBenutzer;
            iMandant=g.iMandant;
            VecMandantRead=(Vector<Object>)g.VecMandantRead.clone();
            VecMandantWrite=(Vector<Object>)g.VecMandantWrite.clone();
            VecMandantCopy=(Vector<Object>)g.VecMandantCopy.clone();
            TabLand=g.TabLand;
            VecJahre=g.VecJahre;
            VecFeiertage=g.VecFeiertage;
            TabFeiertage=g.TabFeiertage;
            TabFeiertagspeicher=g.TabFeiertagspeicher;
            iEigFaktor=g.iEigFaktor;
            Qry=new SQL(this);
            //VecJederEig=g.VecJederEig;
            //VecLizEig=g.VecLizEig;
            VecEigenschaften=g.VecEigenschaften;
            VecLizEigenschaften=g.VecLizEigenschaften;
            TabStammFarbe=g.TabStammFarbe;
            iEigFarbe=g.iEigFarbe;
            TabFensterpos=g.TabFensterpos;
            TabMA=g.TabMA;
//            TabFormularSperre=g.TabFormularSperre;
//            VecBG_Formular=g.VecBG_Formular;
            TabWaehrung=g.TabWaehrung;
            TabAuswahl=g.TabAuswahl;

            iSttWaehrung = g.iSttWaehrung;
            iEigEurofaktor = g.iEigEurofaktor;
            iEigIstWW=g.iEigIstWW;
            iEigAustritt=g.iEigAustritt;
            iEigFirma=g.iEigFirma;
            iEigZoneMin=g.iEigZoneMin;
            iEigMA=g.iEigMA;
            iEigReligion=g.iEigReligion;
            iEigRegion=g.iEigRegion;
            iEigLand=g.iEigLand;
            iEigBild=g.iEigBild;
            iEigEMail=g.iEigEMail;
            iEigTel=g.iEigTel;
            iTimerSperre=g.iTimerSperre;
            iTimerModell=g.iTimerModell;
            iTimerFort=g.iTimerFort;
            iTimerFertig=g.iTimerFertig;
            iTimerZR=g.iTimerZR;
            iTimerStamm=g.iTimerStamm;
            iTimerNicht=g.iTimerNicht;

            iStdSprache = g.iStdSprache;
            iStdLand = g.iStdLand;
            iStdWaehrung = g.iStdWaehrung;
            iSprache = g.iSprache;
            iSprache2=g.iSprache2;
            iLand = g.iLand;
            iWaehrung = g.iWaehrung;
            iEuro=g.iEuro;

            TabBerechtigung=g.TabBerechtigung;
            iBits=g.iBits;
            TabMeine=g.TabMeine;
            TabStrings=g.TabStrings;
            iStamm=g.iStamm;
            bCC=g.bCC;
            DFS=g.DFS;
            setDebug(g.Debug());
          }
          clockInfo("new Global",lClock2);
        }

	public Global(String sODBC_Treiber)
    {
		this(sODBC_Treiber,null,null,null,0);
    }
	
	public void putTabFormulare(int riFormular,int riStammtyp,Object ObjFom)
	{
//		putTabFormulare(riFormular,riStammtyp,ObjFom,null);
//	}
//
//	public void putTabFormulare(int riFormular,int riStammtyp,Object ObjFom,Scene scene)
//	{
		//testInfo("** putTabFormulare:"+riFormular+"/"+riStammtyp);
		TabFormulare.addInhalt("Aic",riFormular);
		TabFormulare.addInhalt("Stt",riStammtyp);
		TabFormulare.addInhalt("Formular",ObjFom);
//		TabFormulare.addInhalt("Scene",scene);
	}

	private int checkTime()
	{
		int iDiff =(int)Math.round(-Sort.getf(SQL.getNow(this))+Sort.getf(new Date()));
		if (Math.abs(iDiff)>2)
			fixInfo("Zeitdifferenz="+Math.round(iDiff)+" s");
		bZoneChange=Math.abs(iDiff)>1790;
		if (Info() && Math.abs(iDiff)>299 && Static.bX11)
		{
			int iAbs=Math.abs(iDiff);
			String s=(iAbs/60)+" min "+(iAbs%60)+" s "+(iDiff>0?"zu hoch":"zu niedrig");
			//new Message(Message.WARNING_MESSAGE,null,this).showDialog("ZeitFalsch",new String[] {s});
			JOptionPane.showMessageDialog(new JFrame(),"Die Zeit ist "+s+"!","Uhrzeit-Fehler",JOptionPane.WARNING_MESSAGE);
		}
		return iDiff;
	}

        private boolean checkWert(String sName,String sWert)
        {
          int iPos=TabStrings.getPos("Name",sName);
          if (iPos>=0)
          {
            if (TabStrings.getS(iPos,"Wert").equals(sWert))
              return true;
            else
            {
              TabStrings.setInhalt(iPos,"Wert", sWert);
              return false;
            }
          }
          else
          {
            iPos=TabStrings.newLine();
            TabStrings.setInhalt(iPos,"Name", sName);
            TabStrings.setInhalt(iPos,"Wert", sWert);
            return false;
          }
        }

        private int getInteger(String s,String sName)
        {
          if (Static.cache())
          {
            int iPos=TabStrings.getPos("Name",sName);
            if (iPos>=0)
              return TabStrings.getI(iPos,"Wert");
            else
            {
              int i=SQL.getInteger(this,s);
              iPos=TabStrings.newLine();
              TabStrings.setInhalt(iPos,"Name", sName);
              TabStrings.setInhalt(iPos,"Wert", i);
              TabStrings.SaveAU("Strings");
              //fixInfo("SaveInt "+sName);
              return i;
            }
          }
          else
            return SQL.getInteger(this,s);
        }

        public int delSperren()
        {
        	testInfo("delSperren aufgerufen");
        	Tabellenspeicher Tab=new Tabellenspeicher(this,"select aic_bew_pool"+
        			",(select ein from logging where aic_logging=b.aic_logging) ein"+
        			",(select aus from logging where aic_logging=b.aic_logging) aus"+
        			" from bew_boolean b where aic_eigenschaft="+iTimerSperre,true);
        	int i=0;
        	for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
        		if (Tab.isNull("ein") || !Tab.isNull("aus"))
        		{
        			i+=exec("delete from bew_boolean where aic_bew_pool="+Tab.getI("aic_bew_pool"));
        		}
        	if (i>0)
        		fixInfo(i+" Timer-Sperren gelöscht!");
        	return i;
        }

        private void checkCache()
        {
          if (Static.cache())
          {
            long lClock2 = Static.get_ms();
            //testInfo("checkCache");
            TabStrings=new Tabellenspeicher(this,new String[] {});
            //boolean bClear=true;
            if (!TabStrings.LoadAU("Strings"))
            {
//              if (!Static.cache())
//                return;
              TabStrings = new Tabellenspeicher(this, new String[] {"Name", "Wert"});
            }
            TabStrings.setTitel("TabStrings");
            boolean bVersion=checkWert("Version",""+Version.getVer2());
            boolean bDefImport=checkWert("DefImport",SQL.getString(this,"select max(aic_defimport) from defimport"));
            boolean bBegriff1= checkWert("Begriff1",SQL.getString(this,"select max(aic_logging) from begriff left outer"+join2("abfrage","begriff")+" where abfrage.aic_benutzer is null and abfrage.aic_benutzergruppe is null"));
            boolean bBegriff2= checkWert("Begriff2",SQL.getString(this,"select max(aic_logging) from begriff "+join2("abfrage","begriff")+" where abfrage.aic_benutzer is not null or abfrage.aic_benutzergruppe is not null"));
            testInfo("checkCache:"+bVersion+"/"+bDefImport+"/"+bBegriff1+"/"+bBegriff2);
            if (bVersion && bDefImport && bBegriff1 && (iInfos & CLRCACHE)==0)
            {
                fixInfo("Daten unverändert");//bClear=false;
                if (!bBegriff2)
                {
                  Static.clearCache("AU0");
                  Static.clearCache("AU2");
                  TabStrings.SaveAU("Strings");
                }
            }
            else //if (Static.cache())
            {
              //if (bClear)
              Static.clearCache();
              TabStrings.SaveAU("Strings");
              if ((iInfos & CLRCACHE)>0)
            	  exec("update computer set cbits="+(iInfos-CLRCACHE)+" where aic_computer="+getComputer());
            }
            clockInfo("checkCache",lClock2);
          }
        }
        
        private void checkAServer()
        {
//        	fixtestError("checkAServer");
        	if (!bAServer)
        	{
        		int iAnl=SQL.getInteger(this,"select aic_code from code"+join("begriffgruppe","g","code","begriffgruppe")+" where g.kennung='Anlage' and code.kennung='AServer'");
        		int iL=SQL.getInteger(this,"select aic_logging from logging where aus is null and aic_code="+iAnl);
        		bAServer=iL>0;
        		if (!bAServer)
        		{
        		    Vector<Integer> VecBalt=SQL.getVector("select aic_benutzer from benutzer where geloescht is null and use_bis<"+now(), this);
        		    for (int i=0;i<VecBalt.size();i++)
        		    	UserManager.LoescheBenutzer(this, Sort.geti(VecBalt, i),getBegriffAicS("Button", "Anmelden"),false);
        		}
        		fixInfoT("checkAServer ->"+iAnl+"/"+iL+"/"+bAServer);	
        	}
        }
        
        public int SaveVerlauf(String sBtn)
        {
        	return SaveVerlauf(getBegriffAicS("Button",sBtn),0,0,0,null,0);
        }
        
        public int SaveVerlauf(int iBegriff,int iStamm,int iBew)
        {
        	return SaveVerlauf(iBegriff,iStamm,iBew,0,null,0);
        }

        public int SaveVerlauf(int iBegriff,int iStamm,int iBew,int iRolle,String sBem,int iVB)
        {
          int iAic=0;
          if (Static.bVerlauf && iLog>0)
          {
            SQL Qry = new SQL(this);
            Qry.add("AIC_LOGGING",iLog);
            if (iBegriff>0)
              Qry.add("AIC_BEGRIFF",iBegriff);
            else if (iBegriff<0)
              Qry.add("AIC_Stammtyp",-iBegriff);
            Qry.add0("AIC_STAMM",iStamm);
            Qry.add0("AIC_BEW_POOL",iBew);
            Qry.add("von",getVon(iVB));
            Qry.add("bis",getBis(iVB));
            if (iRolle>0)
            	Qry.add("AIC_ROLLE", iRolle);
            if (sBem != null)
            	Qry.add("Bemerkung", sBem);
            iAic=Qry.insert("Verlauf",true);
            Qry.free();
            if (iAic<=0 && Prog())
            {
              fixInfo("!!! SaveVerlauf nicht speicherbar bei: Begriff="+iBegriff+", iStamm="+iStamm+"->"+iAic+", "+sBem);
            	//printStackTrace(new Exception());
            }
          }
          fixtestInfo("Verlauf"+iAic+": "+new Zahl1(Static.Used(),"#0.0 %"));
          lLastProt=Static.get_ms();
          return iAic;
        }
        public void SaveVerlaufFertig(int iVerlauf,long lClock,boolean bAbbruch)
        {
         if (iVerlauf>0)
         {
           SQL Qry = new SQL(this);
           Qry.addnow("fertig");
           Qry.add("Dauer",lClock);
           if (bAbbruch)
             Qry.add("Abbruch",1);
           Qry.update("Verlauf",iVerlauf,true);
           Qry.free();
         }
        }

        public int SaveDefVerlauf(int iBegriff,String sTat)
        {
          lLastProt=Static.get_ms();
          SQL Qry = new SQL(this);
          Qry.add("AIC_LOGGING",iLog);
          if (iBegriff>0)
            Qry.add("AIC_BEGRIFF",iBegriff);
          if (sTat.length()>78)
            sTat=sTat.substring(0,78)+"..";
          Qry.add("TAT",sTat);
          int iAic=Qry.insert("DEFVERLAUF",true);
          Qry.free();
          return iAic;
        }

	public void ComputerErmitteln()
	{
          	//testInfo("ComputerErmitteln");
		if (iComputer == 0)
		{
			fixInfoT("Umlauttest: Straßen für Österreich, äöüÄÖÜ !\"§$%&/()= „¡“¶¢[]|{}≠ #£^\\· «∑€®†Ω¨⁄øπ å‚∂ƒ©ªº∆@ œ æ");			
//                  fixtestInfo(" * ! *   - - - ComputerErmitteln()");
                  TabLand=new Tabellenspeicher(this,"select aic_land,aic_stamm,iso3166,standard from land",true,"land");
                  TabLand.setTitel("TabLand");
//            checkGesperrt();
          		
			SQL Qry = new SQL(this);

			//Sysinfo;
			String sBS = System.getProperty("os.name");
			//System.out.println("Betriebssystem="+sBetriebssystem);
                        fixInfo("User="+System.getProperty("user.name"));
			String sOS_Version = System.getProperty("os.version");
			fixInfo("Betriebssystem="+sBS+"("+sOS_Version+")");
            Static.bWindows=sBS.startsWith("Windows");
            Static.bLinux=sBS.startsWith("Linux");
            fixInfo("Windows="+Static.bWindows+", Linux="+Static.bLinux);
			String sJava_Version = System.getProperty("java.version");
			fixInfo("Prozessor="+System.getProperty("os.arch"));
			Static.bOpenJDK=sJava_Version.contains("ojdk");
			if (Static.bOpenJDK)
				fixInfoT("Java-Version "+sJava_Version+" ist OpenJDK");
			String sBrowser = System.getProperty("browser");//==null ? null : System.getProperty("browser");
			//System.out.println("Browser="+sBrowser);
			//String sBrowser_Version = System.getProperty("browser.version")==null ? "?" : System.getProperty("browser.version");
			String sIP2="";
			String sIPSys="";
			String sHost="";
			try
			{
				sHost=InetAddress.getLocalHost().getHostName();
                                if (sHost.indexOf(".")>0)
                                  sHost=sHost.substring(0,sHost.indexOf("."));
				sIPSys=InetAddress.getLocalHost().getHostAddress();
				//fixInfo("IP-SYS:<"+sIPSys+"/"+sHost+">");
				//sIP=sIP2;
                                /*int i=0;
                                for (InetAddress ia:InetAddress.getAllByName(sHost))
                                  fixInfo("Adresse"+(++i)+":"+ia+"/"+ia.getCanonicalHostName()+"/"+ia.isLoopbackAddress()+"/"+ia.isSiteLocalAddress());*/
			}
			catch(Exception e)
			{
				Static.printError("Global.ComputerErmitteln(): UnknownHostException - "+e);
                                sHost="unknown";
                                sIPSys="x.x.x.x";
			}
			String sIPDB=MS() || MySQL() ? " ":Qry.getString(Oracle() ? "select sys_context('userenv','ip_address') from dual":"SELECT connection_property('NodeAddress')");
			sHost=(sIPDB.equals(" ")?"*":sIPDB.equals(sIPSys)?"":"#")+sHost;
                        String sAdresse = Static.cMacArt=='+' || Static.cMacArt=='c' ? Static.getMacAddress():null;

			//fixInfo("IP-DB:"+sIPDB);
                        bMac=sBS.equals("Mac OS X");
                        //boolean bNovell=sBetriebssystem.equals("NetWare");
			//Tabellenspeicher TabAdresse = sBetriebssystem.equals("Linux") ? null:new Adresse(this).getTabAdresse();
			/*Tabellenspeicher TabAdresse = null;
			try
			{
                          if (Static.bMac && sBS.startsWith("Windows "))
                            sAdresse=new Adresse(this).getWinMacAdress(sIPSys);
                          if (sAdresse != null)
                            testInfo("Mac2="+sAdresse);
                          else
				TabAdresse = !Static.bMac || sBS.equals("NetWare") ? null:sBS.equals("Linux") ? new Adresse(this).getLinuxTabAdresse():
                                bMac ? new Adresse(this).getMacTabAdresse():new Adresse(this).getTabAdresse(sBS.equals("Windows Vista"));
                          //if (TabAdresse != null && TestPC())
                          //  TabAdresse.showGrid("Adresse");
			}
			catch(Exception e){}

                        //if (TabAdresse != null)
                        //  TabAdresse.showGrid("TabAdresse");
			//if (TabAdresse == null || TabAdresse != null && !TabAdresse.isEmpty())
			{
				if (TabAdresse == null || TabAdresse.isEmpty())
				{
					sIP2=sIPDB.equals(" ") ? sIPSys:sIPDB;
                                        if (sAdresse==null)
                                          sAdresse=sIP2;
				}
				else if(!sIPDB.equals(" ") && TabAdresse.posInhalt("IP",sIPDB))
				{
					sIP2=sIPDB;
					sAdresse = TabAdresse.getS("Adresse");
				}
				else if(TabAdresse.posInhalt("IP",sIPSys))
				{
					testInfo("Datenbank-Adresse "+sIPDB+" nicht gefunden!");
					sIP2=sIPSys;
					sAdresse = TabAdresse.getS("Adresse");
					if (sAdresse.startsWith("44-45-53-54-") || sAdresse.equals("00-07-77-64-09-32"))
					{
						if(TabAdresse.getAnzahlElemente(null)>1)
						{
							if (TabAdresse.getPos()==0)
								TabAdresse.moveNext();
							else
								TabAdresse.moveFirst();
							sAdresse = TabAdresse.getS("Adresse");
							sIP2 = TabAdresse.getS("IP");
							testInfo("Einstieg mit anderer Adresse als laut System!");
						}
						else
							testInfo("Einstieg mit DFÜ-Adresse, da nichts anderes existiert!");
					}
					else
						testInfo("Einstieg mit Adresse laut System nicht Datenbank!");
				}
				else
				{
					TabAdresse.moveFirst();
					if(!TabAdresse.eof() && (TabAdresse.getS("Adresse").startsWith("44-45-53-54-") || TabAdresse.getS("Adresse").equals("00-07-77-64-09-32")))
						TabAdresse.moveNext();
					if(!TabAdresse.eof())
					{
						sIP2 = TabAdresse.getS("IP");
						sAdresse = TabAdresse.getS("Adresse");
					}
					fixInfo("IP-Adresse "+sIPSys+" nicht gefunden, verwende deshalb "+sIP2);
				}
			}*/
			/*else
			{
				Static.printError("MAC-Adresse konnte nicht ermittelt werden!");
				JOptionPane.showMessageDialog(new JFrame(),"MAC-Adresse konnte nicht ermittelt werden!","Fehler",JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}*/

			//}
			//printInfo(sBetriebssystem+"("+sOS_Version+"), Adresse="+sAdresse);
			/*if (System.getProperty("browser.vendor") == null)
				sBrowser = Static.sBrowser;*/
			//if (sBrowser.equals("sun.applet.AppletViewer"))
			//	Static.ImageVerzeichnis = "file:///N:/images/";
			//printInfo("Browser="+sBrowser+" / "+Static.ImageVerzeichnis);
			if (sBrowser==null || sBrowser.equals(""))
				sBrowser="unbekannt";
                        String sDbVer=(Oracle() ? "Oracle ":ASA() ? "ASA ":MS() ? "MS ":MySQL() ? "MySQL ":"")+getVersion();
                        //if (sBS.length()>15) sBS = sBS.substring(0,15);
                        //if (sOS_Version.length()>10) sOS_Version = sOS_Version.substring(0,10);
                        //if (sJava_Version.length()>10) sJava_Version = sJava_Version.substring(0,10);
                        //if (sBrowser.length()>30) sBrowser = sBrowser.substring(0,30);
                        //if (sBrowser_Version.length()>10) sBrowser_Version = sBrowser_Version.substring(0,10);
                        //if (sDbVer.length()>15) sDbVer=sDbVer.substring(0,15);
                        fixInfo("DB-Version="+sDbVer);
                        iSysinfo = Qry.getInteger("select aic_systeminfo from systeminfo where betriebssystem='"+sBS+"' and OS_Version='"+sOS_Version+
				"' and java_version='"+sJava_Version+"' and Browser='"+sBrowser+//"' and Browser_Version='"+sBrowser_Version+
                                "' and DB_VERSION='"+sDbVer+"'");
			if (iSysinfo==0)
			{
				Qry.add("betriebssystem",sBS);
				Qry.add("OS_Version",sOS_Version);
				Qry.add("java_version",sJava_Version);
				Qry.add("Browser",sBrowser);
				Qry.add("Browser_Version","x");
                                Qry.add("DB_VERSION",sDbVer);
				iSysinfo = Qry.insert("systeminfo",true);
			}
			//printInfo("Sysinfo erkannt/fixiert mit "+iSysinfo);
			if (sIP2.equals(""))
                          sIP2=sIPSys;
                        if (sAdresse==null || sAdresse.trim().equals(""))
                          sAdresse=sIP2;
                        if (Static.cMacArt=='H')
                          sAdresse=sHost;
                        else if (Static.cMacArt=='w')
                          sAdresse=System.getProperty("user.name");	
			Static.sIP="IP"+sIP2.substring(sIP2.lastIndexOf('.')+1);
			/*printInfo("Computer="+sHost+"("+sIP2+")");
			printInfo("IP-SYS:<"+sIP2+">");*/

			fixInfo("IP="+sIP2+", Host="+sHost+", Adresse=<"+sAdresse+">");
                        testInfo("iInfos1="+Integer.toHexString(iInfos));
                        //if (sHost.length()>40) sHost = sHost.substring(0,40);
			//printInfo("IP-DOS:<"+Adr.getParameter("IP-Ad")+">");
			//printInfo("Computerabfrage");
                        Vector VecOk=SQL.getVector("select aic_computer from computer where"+bit("cbits",ALLEIN),this);
                        if (Qry.open("select aic_computer,aic_mandant,gesperrt,geloescht,cbits,hostname,ip_adresse,Adresse from computer where "+
                        		(Static.cMacArt=='c' ? "hostname='"+sHost+"' and IP_Adresse='"+sIP2+"'" :"Adresse='"+sAdresse+"'")))
			{
				if (Qry.eof())
				{
					SQL Insert = new SQL(this);
					Insert.add("Kennung",sHost);
					Insert.add("hostname",sHost);
					Insert.add("IP_Adresse",sIP2);
					Insert.add("Adresse",sAdresse);
					iComputer = Insert.insert("Computer",true);
					Insert.free();
					fixInfo("Neuen Computer angelegt mit Nummer "+iComputer);
                                        iInfos+=0x5000;
				}
				else
				{
					//bInfo = Qry.getB("Info");
                                        boolean bClock=Clock();
                                        //fixtestError("Computer "+Qry.getS("hostname")+"/"+Qry.getS("Adresse")+": Bits="+Integer.toHexString(Qry.getI("CBITS")));
                                        iInfos= iInfos | Qry.getI("CBITS");
                                        if ((iInfos & NO_CACHE)>0)
                                          Static.iCache=Static.NIE;
                                        if ((iInfos&FONTFAKT)==0)
                                          iInfos+=0x5000;
                                        if (bClock && !Clock())
                                          iInfos+=CLOCK;
                                        //fixtestError("Computer-Bits von "+ Integer.toHexString(iInfosOld)+" auf "+Integer.toHexString(iInfos));
					bGesperrt = !Qry.isNull("gesperrt") || !Qry.isNull("geloescht");
					iComputer = Qry.getI("aic_computer");
					iMandant = Static.bMandantensuche ? 0:iMandantT>0 ? iMandantT:bGesperrt ? -2 : iMandant==0 ? Qry.getI("aic_mandant"):iMandant;
					fixtestInfo("Computer="+iComputer+", Mandant="+iMandant+", Info="+iInfos);
					checkCache();
					
					if (!sHost.equals(Qry.getS("Hostname")) || !sIP2.equals(Qry.getS("ip_adresse")) || !sAdresse.equals(Qry.getS("Adresse")))
					{
						if(!sHost.equals(Qry.getS("Hostname")))
							fixInfo("Hostname geändert von "+Qry.getS("Hostname")+" auf "+sHost);
						if(!sIP2.equals(Qry.getS("ip_adresse")))
							fixInfo("IP_Adresse geändert von "+Qry.getS("ip_adresse")+" auf "+sIP2);
						if(!sAdresse.equals(Qry.getS("Adresse")))
							fixInfo("IP_Adresse geändert von "+Qry.getS("Adresse")+" auf "+sAdresse);
						Qry.exec("update computer set hostname='"+sHost+"',ip_adresse='"+sIP2+"',Adresse='"+sAdresse+"' where aic_computer="+iComputer);
					}
				}
				Qry.close();
                                if (VecOk != null && VecOk.size()>0 && !VecOk.contains(iComputer))
                                {
                                  iMandant=-2;
                                  Qry.free();
                                  return;
                                }
				if (iMandant==0)// && !Static.bMandantensuche)
				{
					iMandant=SQL.getInteger(this,"select +"+int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Default'");
					testInfo("Default-Mandant="+iMandant);
				}
                                if (iMandant>0)
                                  iMaxLog=SQL.getInteger(this,"select Versuche from mandant where aic_mandant="+iMandant)&VMAX;
                                if (iMaxLog>0 && iMaxLog== VMAX)
                                  iMaxLog=-1;
                                else if (iMaxLog==0)
                                  iMaxLog=3;
//                fixtestError("MaxLog="+iMaxLog+" bei Mandant="+iMandant);
                if (Static.bMandantensuche)
                	iMandant=0;
				//printInfo("Computer erkannt/fixiert mit "+iComputer);
				//CheckAbsturz();
                                //testInfo("vor letzen Einloggen ermitteln");
				if (iAnlage==0)
				{
					// Qry.open("Select b.kennung,mom,cbits from computer c join logging l on c.aic_computer=l.aic_computer join benutzer b on l.aic_benutzer=b.aic_benutzer where aus is null and aic_code is null and l.aic_computer="+iComputer);
					// if (!bUP && !Qry.eof() && !bSperreIgnorieren && Static.bComputerLock && (Qry.getI("cbits")&MEHRMALS)==0)
					// {
          //                                 int iTime=(int)(Sort.getf(SQL.getNow(this))-Sort.getf(Qry.getTS("mom")));
          //                                 testInfo("iTime=" + iTime);
          //                                 if (iTime>240)
          //                                 {
          //                                   exec("update logging set aus="+now()+",status="+ABSTURZ+" where aus is null and aic_computer="+iComputer,true);
          //                                   JOptionPane.showMessageDialog(new JFrame(), "Bitte das nächste Mal ordnungsgemäß abmelden !", "Noch angemeldet",
          //                                       JOptionPane.WARNING_MESSAGE);
          //                                 }
          //                                 else
          //                                 {
          //                                   Static.printError("Global.ComputerErmitteln(): Der Computer wird bereits von " + Qry.getS("Kennung") + " benutzt !");
          //                                   JOptionPane.showMessageDialog(new JFrame(), "Der Computer wird bereits von " + Qry.getS("Kennung") + " benutzt !", "Fehler",
          //                                       JOptionPane.ERROR_MESSAGE);
          //                                   //System.exit(-1);
          //                                   iComputer = 0;
          //                                 }
					// }
          fixtestInfo("Prüfung ob mehrmals eingeloggt entfernt");
				}
				else
				{
					Qry.open("Select computer.kennung,mom,logging.aic_logging,code.kennung Anlage from code"+join2("logging","code")+join("computer","logging")+" where aus is null and logging.aic_code="+iAnlage+(iMandantT>0 ? " and logging.aic_mandant="+iMandantT:""));
					if (!Qry.eof())
					{
            int iTime=(int)(Sort.getf(SQL.getNow(this))-Sort.getf(Qry.getTS("mom")));
            if (iTime>3600)
            {
              exec("update logging set aus="+now()+",status="+ABSTURZ+" where aus is null and aic_logging="+Qry.getI("aic_logging"),true);
              printError(Qry.getS("Anlage")+" mit log="+Qry.getI("aic_logging")+" ausloggen, da "+iTime/60+" min nicht gemeldet");
            }
            else
            {
						  Static.printError("Global.ComputerErmitteln(): "+Qry.getS("Anlage")+" wird bereits auf Computer "+Qry.getS("Kennung")+" benutzt !");
						//JOptionPane.showMessageDialog(new JFrame(),Qry.getS("Anlage")+" wird bereits auf "+Qry.getS("Kennung")+" benutzt !","Fehler",JOptionPane.ERROR_MESSAGE);
                                                unConnect(true);
						  System.exit(13);
						  iComputer=0;
            }
					}
				}
				Qry.close();
			}
			else
				iMandant = -1;
                        testInfo("iInfos2="+Integer.toHexString(iInfos));
			//printInfo("Mandant="+iMandant);
			Qry.free();
                        //testInfo("vor ErmittleJeder");
			//ErmittleJeder();
                        //testInfo("nach ErmittleJeder");
			//SQL.addVector(VecBegJeder,"select aic_begriff from begriff where jeder=1",this);

			//for(Qry.open("select aic_begriff from begriff where jeder=1");!Qry.eof();Qry.moveNext())
			//	VecLizenz.addElement(new Integer(Qry.getI("aic_begriff")));
			//iStdLizenzEnde=VecLizenz.size();
                        printSI("Computer ermitteln");
		}

	}

	public void ErmittleJeder()
	{
		fillTabBegriffgruppen();
		VecBegJeder.removeAllElements();
		addVec(VecBegJeder,"select aic_begriff from begriff where jeder=1","BegriffJeder");
        VecBegTod.removeAllElements();
		addVec(VecBegTod,"select aic_begriff from begriff where tod=1","BegriffTod");
		VecBegWeb.removeAllElements();
		addVec(VecBegWeb,"select aic_begriff from begriff where web=1","BegriffWeb2");//" and aic_begriffgruppe="+TabBegriffgruppen.getAic("Frame"),"BegriffWeb");
//		fixtestError("VecBegWeb="+VecBegWeb);
        VecEigTod.removeAllElements();
                //bISQL=true;
		addVec(VecEigTod,"select aic_eigenschaft from eigenschaft where tod=1","EigTod");
                //bISQL=false;
//		fixtestError("VecEigTod="+VecEigTod);
	}

	public String getLastBenutzer()
	{
		SQL Qry = new SQL(this);
		String s="";
		//Qry.open("select first kennung,Bits from logging join benutzer where aic_computer="+iComputer+" order by aic_logging desc");
                Qry.open("select kennung,Bits from logging join benutzer on logging.aic_benutzer=benutzer.aic_benutzer where logging.aic_logging="+
                		"(select max(l.aic_logging) from logging l join benutzer b on l.aic_benutzer=b.aic_benutzer and bits&"+cstNoLogin+"=0 where aic_computer="+iComputer+")");
		if (!Qry.eof())
		{
			iBits = Qry.getI("BITS");
			//bProg = Qry.getB("PROG");
			//bDef = bProg || Qry.getB("ENTWICKLER");
			s = Qry.getS("Kennung");
			//testInfo("iBits="+iBits+", Kennung="+s);
		}
		return s;
	}

	public int getWaehrung()
    {
        return(iWaehrung);
    }

	public int getSprache()
    {
        return(iSprache);
    }

	public int getLand()
    {
        return(iLand);
    }

    public void changeMandant(String sMandant,String sPW)
    {
      int iMandant2=SQL.getInteger(this,"select aic_mandant from mandant where kennung='"+sMandant+"' and Passwort='"+PasswordConvert(sPW,PWR,0)+"' and Tod is null");
      if (iMandant2>0)
      {
        iMandant=iMandant2;
        iMandantLog=iMandant;
        fixInfo("Mandant="+iMandant);
        fillVecMandant(true);
      }
    }

   public boolean isLogMandant()
   {
     return iMandant==iMandantLog;
   }

   public boolean isLogAll()
   {
     return iMandantLog==1;
   }

	/*public void setMandant(String sMandant)
	{
	  iMandant=SQL.getInteger(this,"select aic_mandant from mandant where kennung='"+sMandant+"' and Passwort='"+PasswordConvert(Static.sDefaultPW,false,0)+"'");
	  testInfo("setMandant "+sMandant+" -> "+iMandant);
	}*/

      public String MandantBez(int iAic)
      {
        if (TabMandanten == null)
          LoadMandant();
        return iAic<1 ? "-":TabMandanten.getBezeichnungS(iAic);
      }

      public void setMandant(int riMandant)
      {
      	setMandant(riMandant,true);
      }

  	public void setMandant(int riMandant,boolean bLizenz)
    {
		//debugInfo("Vec="+VecMandantWrite+"\nMandant-Alt:"+iMandant+",Mandant-Neu:"+riMandant);
		if (riMandant==0)
			riMandant=iMandantLog;
		if (iMandant!=riMandant && iMandant>0)
          if (AllUnlimited() || VecMandantWrite.contains(new Integer(riMandant)))
          {
			iMandant=riMandant;
			fillVecMandant(false);
			printInfo(2,"iMandant="+iMandant);
            if (TabMandanten==null)
              LoadMandant();
            checkIndividuell(iSprache,iMandant);         			
            fixInfo("setMandant "+TabMandanten.getBezeichnungS(iMandant));
			//progInfo("VecMandantRead="+VecMandantRead);
          }
          else
            printError("Global.setMandant: Mandant "+riMandant+" nicht setzbar");
    }

	/*public int getComputer()
    {
        return(iComputer);
    }*/

	public boolean gesperrt()
    {
        return(bGesperrt);
    }

    /*public String AU_BezeichnungF(String sTabelle)
    {
      int iTab=TabTabellenname.getAic(sTabelle.toUpperCase());
      return (iSprache==1 ?
      ",(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+sTabelle+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache:
          ","+SQL.isnull()+"(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+sTabelle+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+
          "),(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+sTabelle+".AIC_"+sTabelle+" AND AIC_Sprache=1)")+") Bezeichnung";
    }*/
	
	public String AU_BezeichnungFo(String sTabelle)
	{
		return AU_BezeichnungFo(sTabelle,sTabelle);
	}

    public String AU_BezeichnungFo(String sTabelle,String sAs)
    {
      int iTab=TabTabellenname.getAic(sTabelle.toUpperCase());
      return (iSprache2==0 ?
      ",(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache:
          ","+isnull()+"(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+
          "),(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache2+")")+") ";
    }

    public String AU_Bezeichnung(String sTabelle)
    {
      return ",(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sTabelle+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+") Bezeichnung";//(!ASA()?" Bezeichnung":"");
    }

    public String AU_Bezeichnung2(String sTabelle)
    {
      return ",(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sTabelle+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+")";
    }

    public String AU_Bezeichnungo(String sTabelle)
    {
      return "(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sTabelle+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+")";
    }

    public String AU_BezeichnungF(String sTabelle)
    {
      return "(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sTabelle+".AIC_"+sTabelle+" AND AIC_Sprache="+iSpStd+")";
    }

    public String AU_Bezeichnungo(String sTabelle,String sAs)
    {
      return "(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+")";
    }
    
    public String AU_BezeichnungK(String sTabelle,String sAs)
    {
      return isnull()+"(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+"),(select Kennung from "+sTabelle+" where aic_"+sTabelle+"="+sAs+".aic_"+sTabelle+"))";
    }

    public String AU_BezeichnungF(String sTabelle,String sAs,int riSprache)
    {
      return "(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+riSprache+")";
    }

    public String AU_Bezeichnung1(String sTabelle,String sAs)
    {
    	int iTab=TabTabellenname.getAic(sTabelle.toUpperCase());
      return iSprache2==0 ? ",(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTab+" AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+")":
			","+isnull()+"(SELECT Bezeichnung FROM Bezeichnung AUB WHERE aic_tabellenname="+iTab+" AND AUB.AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AUB.AIC_Sprache="+iSprache+
			"),(SELECT Bezeichnung FROM Bezeichnung AUB WHERE aic_tabellenname="+iTab+" AND AUB.AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AUB.AIC_Sprache="+iSprache2+"))";
    }
    
    public String AU_BezeichnungP(String sTabelle,String sAs)
    {
      return ",(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sAs+" AND AIC_Sprache="+iSprache+")";
    }

    public String AU_Bezeichnung2(String sTabelle,String sAs)
    {
      return ",(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+")"+(!ASA()?" Bezeichnung":"");
    }

    public String AU_Bezeichnung3(String sTabelle,String sAs)
    {
      return ",(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+") Bezeichnung";
    }

    public String AU_Bezeichnung3(String sTabelle)
    {
      return ",(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname=" + TabTabellenname.getAic(sTabelle.toUpperCase()) +
          " AND AIC_Fremd=Modul.AIC_Fremd AND AIC_Sprache=" + iSprache + ")";
    }

    public String AU_Bezeichnung(String sTabelle,String sSp1,String sSp2)
    {
      return isnull()+"(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sSp1+" AND AIC_Sprache="+iSprache+"),(select "+sSp2+" from "+sTabelle+" where aic_"+sTabelle+"="+sSp1+"))";
    }

    public String AU_Bezeichnung4()
    {
      return ","+isnull()+"(SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTabStamm+
          " AND AIC_Fremd=p2.AIC_stamm AND AIC_Sprache="+iSprache+"),p2.Bezeichnung) Bezeichnung";
    }

    public String AU_Tooltip(String sTabelle,String sAs)
    {
        return ","+isnull()+"(SELECT Memo2 FROM Tooltip WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+" AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+
        		"),(SELECT Memo2 FROM Tooltip WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+" AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iStdSprache+")) TT";
    }

    public String AU_Memo(String sTabelle,String sAs)
    {
        return ",(SELECT Memo FROM Daten_Memo WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
                        " AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+") Memo";
    }

    public String AU_Memo2(String sTabelle,String sAs)
    {
	return ",(SELECT Memo FROM Daten_Memo WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
			" AND AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+")"+(!ASA()?" Memo":"");
    }

    public String AU_Memo(String sTabelle)
    {
      return ",(SELECT Memo FROM Daten_Memo WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sTabelle+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+") Memo";//(!ASA()?" Memo":"");
    }

    public String AU_Memo2(String sTabelle)
    {
      return ",(SELECT Memo FROM Daten_Memo WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
          " AND AIC_Fremd="+sTabelle+".AIC_"+sTabelle+" AND AIC_Sprache="+iSprache+")";
    }

    public String getMemo(String sTabelle,int iAic)
    {
    	return SQL.getString(this,"SELECT Memo FROM Daten_Memo WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
    	          " AND AIC_Fremd=? AND AIC_Sprache="+iSprache,""+iAic);
    }

	public String AU_Bezeichnung(String sTabelle,String sAs)
	{
		return(AU_Sprache("Bezeichnung","Bezeichnung",sTabelle,sAs,0));
	}

	public String AU_Sprache(String sTab,String sSpalte,String sTabelle,String sAs,int riSprache)
	{
		if (sAs.equals(""))
			sAs = sTabelle;
		if (sTab.equalsIgnoreCase("Daten_Bild"))
			return AU_Bild2(sTabelle,sAs,riSprache);
		String s = ",(SELECT "+/*(SAP()?"substring("+sSpalte+",1,39)":*/sSpalte+" FROM "+sTab+" AUB WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
			" AND AUB.AIC_Fremd="+sAs+".AIC_"+sTabelle+" AND AUB.AIC_Sprache="+(riSprache==0?iSprache:riSprache)+")";
		return(s);
	}
	
	public String AU_Bezeichnung2(String sTabelle,String sFremd,String sBeiNull)
	{
		return AU_Bezeichnung2(sTabelle,sFremd,sBeiNull,0);
	}

	public String AU_Bezeichnung2(String sTabelle,String sFremd,String sBeiNull,int iEig)
	{
		int iPos=sFremd.indexOf('=');
		if (iPos != -1)
			sFremd=sFremd.substring(iPos+1);
		int iTab=TabTabellenname.getAic(sTabelle.toUpperCase());
		boolean bA=iEig>0 && sTabelle.equals("Auswahl");
		String s = ","+ifnull(sFremd,bA ? isnull()+"(select bezeichnung from bezeichnung where aic_tabellenname="+iTab+" and aic_fremd=(select aic_auswahl from auswahl where aic_eigenschaft="+iEig+" and nr=0) and aic_sprache="+iSprache+"),(select kennung from auswahl where aic_eigenschaft="+iEig+" and nr=0))":"null",isnull()+
				"(SELECT Bezeichnung FROM Bezeichnung AUB WHERE AIC_Tabellenname="+iTab+" AND AUB.AIC_Fremd="+sFremd+" AND AUB.AIC_Sprache="+iSprache+"),"+
				(iSprache2==0 ?"":"(SELECT Bezeichnung FROM Bezeichnung AUB WHERE AIC_Tabellenname="+iTab+" AND AUB.AIC_Fremd="+sFremd+" AND AUB.AIC_Sprache="+iSprache2+"),")+sBeiNull+")");
		return(s);
	}

	public String AU_Bild2(String sTabelle)
	{
		return AU_Bild2(sTabelle,null,iSpSw);
	}
	
	public String AU_Bild2(String sTabelle,int iZ)
	{
		return AU_Bild2(sTabelle,null,iZ);
	}

	public String AU_Bild2(String sTabelle,String sAs,int iZ)
	{
	      return ",(SELECT Filename FROM Daten_Bild WHERE AIC_Tabellenname="+TabTabellenname.getAic(sTabelle.toUpperCase())+
	          " AND AIC_Fremd="+(sAs==null ? sTabelle:sAs)+".AIC_"+sTabelle+" AND AIC_Zustand="+iZ+")";
	}

        public String AU_Begriff()
        {
          return ","+isnull()+"(SELECT Bezeichnung FROM Bezeichnung WHERE aic_tabellenname="+iTabBegriff+
			" AND AIC_Fremd=BEGRIFF.AIC_BEGRIFF AND AIC_Sprache="+iSprache+"),DEFBEZEICHNUNG)";
        }
/*
	public String AU_Bezeichnung(String sTabelle,int i)
	{
		String s = ",(SELECT Bezeichnung FROM Bezeichnung AUB JOIN Tabellenname AUT WHERE AUT.Kennung='"+sTabelle+
			"' AND AUB.AIC_Fremd="+i+" AND AUB.AIC_Sprache="+iSprache+")";
		return(s);
	}
*/

	public double getATS(double d)
	{
		//return d*dEurofaktor;
                int iPos = WW() ? -1:TabWaehrung.getPos("aic_stamm",iWaehrung);
                return iPos<0 ? d:d*TabWaehrung.getF(iPos,"faktor");
	}

	/*public String getWaehrung(double d)
	{
		String s=new DecimalFormat(Static.beiLeer(TabEigenschaften.getS("Format"),"#,###")).format(getATS(d));
		for(int i=s.length();i<TabEigenschaften.getI("Laenge");i++)
			s = " "+s;
		return sWaehrung+s;
	}*/

	public void setWaehrung(int riWaehrung)
	{
		printInfo(2,"setWaehrung:"+iWaehrung+"->"+riWaehrung);
		iWaehrung=riWaehrung;
                if (TabWaehrung==null)
                {
                  TabWaehrung = new Tabellenspeicher(this,"select stammview2.aic_stamm,bezeichnung,kennung" /*+ AU_Bezeichnung2("Stamm", "stammview2")*/ +
                                                     ",(select spalte_double from poolview2 where aic_eigenschaft="+iEigIstWW+" and aic_stamm=stammview2.aic_stamm) WW"+
                                                     //",(select spalte_double from poolview2 where aic_eigenschaft="+iEigCalc+" and aic_stamm=stammview2.aic_stamm) WCalc"+
                                                     ",1 faktor from stammview2 where aic_stammtyp=" + iSttWaehrung, true,"Waehrung");
                  for (TabWaehrung.moveFirst();!TabWaehrung.eof();TabWaehrung.moveNext())
                  {
                    if (TabWaehrung.getB("WW"))
                      VecWW.addElement(TabWaehrung.getInhalt("aic_stamm"));
                    if (TabWaehrung.getS("kennung").equals("Euro"))
                      iEuro=TabWaehrung.getI("aic_stamm");
                  }
                  TabWaehrung.setTitel("TabWaehrung");
                  //testInfo("VecWW"+VecWW);
                  //if (Prog())
                  //    TabWaehrung.showGrid("Währung");
                }
                if (iEuro<=0 && Static.bX11)
                {
                	Static.clearCache();
                	Version.ViewRebuild(this, Version.getVer());
                	new Message(Message.WARNING_MESSAGE,null,this).showDialog("Euro_fehlt");
                	//JOptionPane.showMessageDialog(new JFrame(),"Euro fehlt","Fehler",JOptionPane.WARNING_MESSAGE);
                	System.exit(-6);
                }
                int iPos=iWaehrung>0 ? TabWaehrung.getPos("aic_stamm",iWaehrung):-1;
                if (iPos<0)
                {
                  iWaehrung = iEuro;
                  iPos=TabWaehrung.getPos("aic_stamm",iWaehrung);
                }
                if (iPos>=0)
                  printInfo(2,"Währung="+TabWaehrung.getS(iPos,"Bezeichnung")+"("+iWaehrung+")");
		/*if (riWaehrung != 0)
		{
			SQL Qry = new SQL(this);
			if (Qry.open("SELECT Bezeichnung,spalte_double from stammview2 left outer join poolview2 on poolview2.aic_stamm=stammview2.aic_stamm WHERE stammview2.aic_stamm="+iWaehrung))
			{
				if (!Qry.eof() && Qry.getF("spalte_double")>0)
				{
					dEurofaktor=Qry.getF("spalte_double");
					//sWaehrung=Qry.getS("Bezeichnung");
					testInfo(Qry.getS("Bezeichnung")+':'+dEurofaktor);
				}

			}
			Qry.free();
		}
		else
		{
			dEurofaktor=1;
			//sWaehrung="";
		}*/
	}

	public void setLand(int riLand,int riWaehrung)
	{
		//progInfo("setLand:"+iLand+"->"+riLand);
		if (riLand != 0 && iLand != riLand)
		{
			fixtestInfo("-> setLand:"+riLand+" statt "+iLand+"/"+riWaehrung+"/"+bSpracheGleich);
			iLand = riLand;
			if (bSpracheGleich || TabFeiertage==null)
			{
				LoadFeiertage();
				printSI("LoadFeiertage");
			}
			/*if (riWaehrung == -1) // !!! Währung nicht aus Land
                        {
                          //riWaehrung = getInteger("select aic_stamm from land where aic_land="+iLand,"Waehrung"+iLand);
                          int iPos=TabLand.getPos("aic_land",iLand);
                          if (iPos>=0)
                            riWaehrung=TabLand.getI(iPos,"AIC_Stamm");
                        }*/
			if (iWaehrung !=riWaehrung)
				setWaehrung(riWaehrung);
		}
	}

        public void checkSprache()
        {
          if(iStdSprache==0)
          {
                  //SQL Qry = new SQL(this);
                  String sLang=Locale.getDefault().getLanguage().toUpperCase();
                  testInfo("Sprache (ISO639)="+sLang);
                  iStdSprache=getInteger("SELECT AIC_Sprache from sprache WHERE ISO639='"+sLang+"'","Sprache"+sLang);
//                  iSpStd=getInteger("SELECT AIC_Sprache from sprache WHERE Standard=1","Sprache_Std");
                  if (iStdSprache==0)
                    iStdSprache=iSpStd;//getInteger("SELECT AIC_Sprache from sprache WHERE Standard=1","Sprache_Std");
          }
        }

        public void checkLand()
        {
          if(iStdLand==0)
          {
            Locale l=Locale.getDefault();
            testInfo( "---------------------------------------");
            testInfo( "display:" + l.getDisplayName() );
            testInfo( "country:" + l.getCountry() );
            testInfo( "ISO3:" + l.getISO3Country() );
            testInfo( "display country:" + l.getDisplayCountry() );
            //testInfo( "language:" + l.getLanguage() );
            testInfo( "display language:" + l.getDisplayLanguage() );
            //testInfo("user.language = " + System.getProperty("user.language"));
            //testInfo(" user.country = " + System.getProperty("user.country"));
            //testInfo(" user.variant = " + System.getProperty("user.variant"));
            testInfo( "---------------------------------------");
            String sISO3166=Locale.getDefault().getCountry().toUpperCase();
            //testInfo("Land   (ISO3166)="+sISO3166);
            int iPos=TabLand.getPos("ISO3166",Static.sLand!=null ? Static.sLand:sISO3166);
            if (iPos>=0)
            {
              iStdLand = TabLand.getI(iPos,"AIC_Land");
              //iStdWaehrung  = TabLand.getI(iPos,"AIC_Stamm");
            }
            else
            {
              fixInfo("Land mit ISO3166="+sISO3166+" nicht gefunden!");
              iPos=TabLand.getPos("Standard",Boolean.TRUE);
              if (iPos>=0)
              {
                iStdLand = TabLand.getI(iPos,"AIC_Land");
                //iStdWaehrung = TabLand.getI(iPos,"AIC_Stamm");
              }
              else
                printError("Standard-Land nicht gefunden !");
            }
                  /*SQL Qry = new SQL(this);
                  if (Qry.open("SELECT AIC_Land,Aic_Stamm from land WHERE ISO3166='"+Locale.getDefault().getCountry().toUpperCase()+"'"))
                  {
                          if (Qry.eof())
                          {
                                  Qry.close();
                                  if (Qry.open("SELECT AIC_Land,Aic_Stamm from land WHERE Standard=1") && !Qry.eof())
                                  {
                                          iStdLand = Qry.getI("AIC_Land");
                                          iStdWaehrung  = Qry.getI("AIC_Stamm");
                                  }
                          }
                          else
                          {
                                  iStdLand = Qry.getI("AIC_Land");
                                  iStdWaehrung  = Qry.getI("AIC_Stamm");
                          }
                  }
                  Qry.free();*/
          }
        }

	public void checkSpracheLand()
	{
		//testInfo("checkSpracheLand-Anfang");
		if (TabStamm==null)
		{
			TabStamm=new Tabellenspeicher(this,new String[] {"Aic","Bezeichnung","BSt","Timestamp","Anzahl","Religion","Kennung"});
	        TabStamm.setTitel("TabStamm");
		}
		ComputerErmitteln();
		if (iComputer==0)
			return;
		//checkSprache();
                //checkLand();

		setSprache(0,0);

		if (iLand<1)
			setLand(iStdLand,iStdWaehrung);
                //testInfo("checkSpracheLand-Ende");
	}

	public void setSprache(int riSprache,int riLand)
    {
		//printInfo("ImageVerzeichnis="+Static.ImageVerzeichnis);
                checkSprache();
                checkLand();
		//testInfo("setSprache:"+iSprache+"->"+riSprache+" / Std="+iStdSprache);

		if (riSprache==0)
			riSprache = iStdSprache;
		if (riLand==0)
			riLand = iStdLand;
                testInfo("setSprache: "+iSprache+"->"+riSprache+", Land "+iLand+"->"+riLand);
		bSpracheGleich = iSprache == riSprache;
		if (!bSpracheGleich)
		{
			fixtestInfo("-> Sprache erneuern:"+riSprache+" statt "+iSprache);
			iSprache = riSprache;
			if (Version.V18())
				iSprache2=SQL.getInteger(this, "select spr_aic_sprache from sprache where aic_sprache="+iSprache);
			Refresh();
			LoadFeiertage();
			printSI("LoadFeiertage");
			//addInfo(" - - - - - - -   E N D E  Globale Variaben laden für Sprache "+iSprache+" - - - - - - -");
		}
    }

	public void Refresh()
	{
		//progInfo("Refresh");
//		fixtestError("Global.Refresh");
		System.gc();
		printSI("vor etwas");
                ErmittleJeder();
                printSI("ErmittleJeder");
		LoadTabellennamen();
                TabBegriffM=null;
		printSI("Tabellennamen");
		if (!Static.bWeb)
		{
			LoadFarbe();
            LoadSchrift(true,getFontFaktor());
            printSI("FarbeSchrift");
		}
		LoadBegriffe();
		checkIndividuell(iSprache,iMandant);
		iSystemFormular = getBegriffAicS("Formulartyp","System");
		Static.sJa = getShow("Ja");
		Static.sNein = getShow("Nein");
		Static.sKein = getShow("Kein");
        Static.sKW = getShow("KW");
        Static.sCount = getShow("count");
        Static.sValRequired=getShow("Input_Required");
        Static.sValZuLang=getShow("zu lang");
        Static.sValZuHoch=getShow("zu hoch");
        Static.sInValid=getShow("invalid");
                testInfo(Static.sJa+"/"+Static.sNein+"/"+Static.sKein+"/"+Static.sKW+"/"+Static.sCount);
                /*getBegriff("Button","ESC");
		if (TabBegriffe.pos())
                  Static.sESC=TabBegriffe.getS("Bezeichnung");*/
		printSI("LoadBegriffe");
		LoadStammtypen();
        LoadRollen();
		LoadStammBild();
		printSI("LoadStammtypen");
		LoadErfassungstypen();
		printSI("Erfassungstyp");
		LoadEigenschaften();
		printSI("Eigenschaften");
                LoadAuswahl();
                printSI("Auswahl");
                //LoadANR_BSt();
                //printSI("ANR_BSt");
		if (iAicStunde==0)
			iAicStunde=getInteger("select aic_stamm from stammtyp stt join stammview2 s on stt.aic_stammtyp=s.aic_stammtyp where stt.kennung='Einheit' and s.kennung='h'","Stunde");
		fixtestInfo("iAicStunde="+iAicStunde);
                //if (iAicJahr==0)
                //  iAicJahr=SQL.getInteger(this,"select aic_stamm from stammtyp stt join stamm on stt.aic_stammtyp=stamm.aic_stammtyp where stt.kennung='Einheit' and stamm.kennung='y'");

		/*if (TabDateitypen==null)
			TabDateitypen=new Tabellenspeicher(this,"select * from (select (select spalte_stringsehrkurz from poolview p join daten_stringsehrkurz on daten_stringsehrkurz.aic_daten=p.aic_daten where s.aic_stamm=p.aic_stamm) ext,bezeichnung,"+
				"(select spalte_string60 from poolview p join daten_string60 on daten_string60.aic_daten=p.aic_daten where s.aic_stamm=p.aic_stamm) MIME,"+
				"(select spalte_stringlang from poolview p join daten_stringlang on daten_stringlang.aic_daten=p.aic_daten where s.aic_stamm=p.aic_stamm) \"file\",null ok"+
				" from stammview2 s "+join("stammtyp","s")+" where stammtyp.kennung='Dateityp') x order by ext",true,"Mime");*/ // !!!
                LoadMandant();
                if (iMandant>0)
                  LoadLizenz();
                //setSyncStamm();
                TabAbfragen=null;
                TabModelle=null;
                printSI("Sonst");
	}

        public void LoadMandant()
        {
          fixtestInfo("LoadMandant");
          TabMandanten=new Tabellenspeicher(this,"select AIC_MANDANT,Kennung, PASSWORT,MAN_AIC_MANDANT,AIC_BEGRIFF,Versuche,Verbindungen,"+AU_BezeichnungF("Mandant")+" bezeichnung,Tod from Mandant",true,"Mandant"+iSprache);
          TabMandanten.checkBezeichnung();
          TabMandanten.sAIC="aic_mandant";
          TabMandanten.setTitel("TabMandanten");
          TabMandanten.sort("Bezeichnung",true);
        }
        
        public int getSIMandant()
        {
        	int iM=iMandant;
        	int iPosM=TabMandanten.getPos("Aic_Mandant",iM);
        	if (iPosM>=0 && (TabMandanten.getI(iPosM,"Versuche")&TIMP)>0)
        		return iM;
        	if (iPosM>=0)
        		iPosM=TabMandanten.getPos("Aic_Mandant",TabMandanten.getI(iPosM,"MAN_Aic_Mandant"));
        	if (iPosM>=0 && (TabMandanten.getI(iPosM,"Versuche")&TIMP)>0)
        		return TabMandanten.getI(iPosM,"Aic_Mandant");
        	else 
        		return 0;
        }

        public int getStunde()
        {
          //if (iAicStunde==0)
          //  iAicStunde=SQL.getInteger(this,"select aic_stamm from stammtyp stt join stamm on stt.aic_stammtyp=stamm.aic_stammtyp where stt.kennung='Einheit' and stamm.kennung='h'");
          return iAicStunde;
        }

        public int getJahr()
        {
          if (iAicJahr == 0)
            iAicJahr = getInteger("select aic_stamm from stammtyp stt join stammview2 s on stt.aic_stammtyp=s.aic_stammtyp where stt.kennung='Einheit' and s.kennung='y'","Jahr");
          return iAicJahr;
        }
        public String getMandant(int riMandant,String sArt)
        {
        	if (sArt!=null && sArt.equals("null"))
        		if (riMandant==0)
        		  return Static.sKein;
        		else
        			sArt=null;
          if (TabMandanten==null)
            return SQL.getString(this,"select kennung from mandant where aic_mandant="+(riMandant==0?iMandant:riMandant)+" and tod is null");
          else
          {
        	int iAktM=riMandant == 0 ? iMandant : riMandant;
            int iPos=TabMandanten.getPos("aic_mandant", iAktM);
            if (iPos>=0 && TabMandanten.getB(iPos,"Tod"))
            	return Static.sLeer;
            if (iPos>=0 && sArt==null)
            	return TabMandanten.getS(iPos,"Bezeichnung")+" ("+new Zahl1(iAktM,"00")+")";
            return iPos>=0 ? TabMandanten.getS(iPos,sArt) : Static.sLeer;
          }
        }

        public void setMandantT(String s)
        {
          iMandantT=SQL.getInteger(this,"select aic_mandant from mandant where kennung='"+s+"'");
        }
        
        public void setMandantT(int iM)
        {
        	if (iM>0)
        	{
        		iMandantT=iM;
        		iMandant=iM;
        	}
        }

        public int getMandantT()
        {
          return iMandantT;
        }

	/*private void Ausgabe(String s)
	{
		lClock1 = lClock2;
		lClock2 = Static.get_ms();
		printInfo(s+":\t"+(lClock2-lClock1)+"\t"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
	}*/

        public String getShow(String sKennung)
        {
          return getBegriffS("Show",sKennung);
          /*if (TabBegriffe.out() || TabBegriffe.isNull("Info"))
            return sKennung;
          else
            return TabBegriffe.getS("Info");*/
        }

        public int getPosBegriff(String sGruppe,String sKennung)
        {
          if (TabBegriffgruppen==null)
            return -1;
          int iPosG=TabBegriffgruppen.getPos("Kennung",sGruppe);
          if (iPosG<0)
          {
            printError("Global.getPosBegriff: Begriffgruppe <"+sGruppe+"> existiert nicht!");
            return -1;
          }
          else
          {
            Integer IntAic=(Integer)TabBegriffgruppen.getInhalt("Aic",iPosG);
            int iPos=-1;
            if (TabBegriffgruppen.getB(iPosG,"Code"))
              iPos=-TabCodes.getPos2(IntAic,sKennung)-2;
            else
            {
              iPos=TabBegriffe.getPos2(IntAic,sKennung);
              addBegriffInfo(iPos,sGruppe+'.'+sKennung);
            }
            return iPos;
          }
        }

        public String getBegriffS(String sGruppe,String sKennung)
        {
          int iPos=getPosBegriff(sGruppe,sKennung);
          //addBegriffInfo(iPos,sGruppe+'.'+sKennung);
          return iPos<-1 ? TabCodes.getS(-iPos-2,"Bezeichnung"):iPos<0 ? sKennung:TabBegriffe.getS(iPos,"Bezeichnung");
        }

        /*public String getBegriffS(String sGruppe,String sKennung)
        {
          int iPosG=TabBegriffgruppen.getPos("Kennung",sGruppe);
          if (iPosG<0)
          {
            Static.printError("Global.getBegriffS: Begriffgruppe <"+sGruppe+"> existiert nicht!");
            return sKennung;
          }
          else
          {
            Integer IntAic=(Integer)TabBegriffgruppen.getInhalt("Aic",iPosG);
            boolean bCode=TabBegriffgruppen.getB(iPosG,"Code");
            int iPos = bCode ? TabCodes.getPos2(IntAic,sKennung):TabBegriffe.getPos2(IntAic,sKennung);
            progInfo("getBegriffS("+sGruppe+","+sKennung+"):"+IntAic+"/"+bCode+"/"+iPos);
            if (iPos<0)
		printInfo(1,"*Begriff ["+sKennung+"] in Begriffgruppe ["+TabBegriffgruppen.getS(iPosG,"Bezeichnung")+"] nicht gefunden!");
            addBegriffInfo(iPos,sGruppe+'.'+sKennung);
            return iPos<0 ? sKennung:bCode ? TabCodes.getS(iPos,"Bezeichnung"):TabBegriffe.getS(iPos,"Bezeichnung");
          }
        }*/

	/*public String getBegriff(String sGruppe,String sKennung)
	{
		boolean b = TabBegriffgruppen.posInhalt("Kennung",sGruppe);
		Integer IntAic=(Integer)TabBegriffgruppen.getInhalt("Aic");
		if (!b)
			Static.printError("Global.getBegriff(): Begriffgruppe <"+sGruppe+"> existiert nicht!");
		String s = b ? TabBegriffgruppen.getB("Code") ? TabCodes.getBezeichnung(IntAic,sKennung):
			TabBegriffe.getBezeichnung(IntAic,sKennung) : sKennung;
		//debugInfo("getBegriff:"+sGruppe+"/"+sKennung+"/"+TabBegriffgruppen.getB("Code")+"->"+IntAic+"/"+s+"||"+TabBegriffgruppen.fehler()+TabBegriffe.fehler()+TabCodes.fehler());
		if (b && !TabBegriffe.pos())
			printInfo(1,"*Begriff ["+sKennung+"] in Begriffgruppe ["+TabBegriffgruppen.getS("Bezeichnung")+"] nicht gefunden!");
                addBegriffInfo(sGruppe+'.'+sKennung,TabBegriffgruppen.getB("Code"),b);
		return s;
	}*/

        /*private void addBegriffInfo(String s,boolean bCode,boolean b)
        {
          if (TabBegriffInfo.posInhalt("Kennung",s))
                        TabBegriffInfo.setInhalt("Anzahl",new Integer(TabBegriffInfo.getI("Anzahl")+1));
                else
                {
                  boolean bPos=b && (bCode ? TabCodes.pos():TabBegriffe.pos());
                        TabBegriffInfo.addInhalt("Kennung",s);
                        TabBegriffInfo.addInhalt("DefBezeichnung",bPos && !bCode ? TabBegriffe.getS("DefBezeichnung"):null);
                        TabBegriffInfo.addInhalt("Anzahl",new Integer(1));
                        TabBegriffInfo.addInhalt("vorhanden",new Boolean(bPos));
                        boolean bOk=bPos && (iSprache==1 || (bCode ? !TabCodes.getS("Kennung").equals(TabCodes.getS("Bezeichnung")):
                            !TabBegriffe.getS("BildFile").equals("")&&!TabBegriffe.getB("Combo") || !TabBegriffe.getS("DefBezeichnung").equals(TabBegriffe.getS("Bezeichnung"))));
                        if (!bOk && bPos)
                          printInfo("nicht übersetzt:"+(bCode ? TabCodes.getS("Kennung"):TabBegriffe.getS("DefBezeichnung")+"/"+TabBegriffe.getS("Kennung")));
                        TabBegriffInfo.addInhalt("übersetzt",bPos ? new Boolean(bOk):null);
                }

        }*/

        private void addBegriffInfo(int iPos,String s)
        {
          int iPosI=TabBegriffInfo.getPos("Kennung",s);
          if (iPosI>=0)
                TabBegriffInfo.setInhalt(iPosI,"Anzahl",new Integer(TabBegriffInfo.getI(iPosI,"Anzahl")+1));
          else
          {
            iPosI=TabBegriffInfo.newLine();
            TabBegriffInfo.setInhalt(iPosI,"Kennung",s);
            TabBegriffInfo.setInhalt(iPosI,"DefBezeichnung",iPos>=0 ? TabBegriffe.getS(iPos,"DefBezeichnung"):null);
            TabBegriffInfo.setInhalt(iPosI,"Anzahl",new Integer(1));
            TabBegriffInfo.setInhalt(iPosI,"vorhanden",new Boolean(iPos>=0));
            boolean bOk=iPos>=0 && (iSprache==1 || !TabBegriffe.getS(iPos,Static.sBild).equals("")&&!TabBegriffe.getB(iPos,"Combo") ||
                	!TabBegriffe.getS(iPos,"DefBezeichnung").equals(TabBegriffe.getS(iPos,"Bezeichnung")));
            if (!bOk && iPos>=0)
              printInfo("nicht übersetzt:"+TabBegriffe.getS(iPos,"DefBezeichnung")+"/"+TabBegriffe.getS(iPos,"Kennung"));
            TabBegriffInfo.setInhalt(iPosI,"übersetzt",new Boolean(bOk));
            if (iPos<0 && iDefImport==0)
            	fixtestError("nicht vorhanden: "+s);
          }
//          if (iPos<0 && Static.bInfoExcept)
//      		  new Exception().printStackTrace();
        }

	/*public int getBegriffAic(String sGruppe,String sKennung)
	{
		getBegriff(sGruppe,sKennung);
		int i= TabBegriffgruppen.pos() ? TabBegriffgruppen.getB("Code") ? TabCodes.pos() ? TabCodes.getI("Aic"):-2 :TabBegriffe.pos() ? TabBegriffe.getI("Aic"):0:-1;
		//debugInfo("getBegriffAic:"+sGruppe+"/"+sKennung+":"+i);
		return i;
	}*/

	public int getBegriffAicS(String sGruppe,String sKennung)
	{
	  int iPos=getPosBegriff(sGruppe,sKennung);
          if (iPos==-1)
            return 0;
          else if (iPos<-1)
            return TabCodes.getI(-iPos-2,"Aic");
          else
	    return TabBegriffe.getI(iPos,"Aic");
	}
	
	public String getBG(int iAic)
	{
		if (iAic==0)
			return Static.sLeer;
		int iPos=TabBegriffe.getPos("Aic", iAic);
		if (iPos<0)
		{
			Static.printError("getBG: Begriff"+iAic+" nicht gefunden");
			return Static.sLeer;
		}
		else
		{
			int iGruppe=TabBegriffe.getI(iPos,"Gruppe");
			String s=TabBegriffgruppen.getKennung(iGruppe);
//			fixtestError("getBG von "+iAic+": "+s);
			return s;
		}
	}
	
	public String getBeg(int iAic)
	{
		if (iAic==0)
			return "null";
		int iPos=TabBegriffe.getPos("Aic", iAic);
		if (iPos<0)
			return "fehlt ("+iAic+")";
		else
		{
			int iGruppe=TabBegriffe.getI(iPos,"Gruppe");
			String s=TabBegriffgruppen.getKennung(iGruppe);
			return s+" "+TabBegriffe.getS(iPos,"DefBezeichnung")+" ("+iAic+")";
		}				
	}

// Farbe + Schrift verwalten

	private void putTabFarbe(int iAic,String sKennung,String sBezeichnung,Color color,boolean bNeu)
	{
		int iPos=TabFarbe.getPos(iAic,sKennung,false,sBezeichnung,bNeu,-1);
		TabFarbe.setInhalt(iPos,"Farbe",color);
	}

	private void putTabSchrift(int iAic,String sKennung,String sBezeichnung,Font font,int iFarbe,int iFarbeHG,boolean bNeu)
	{
		int iPos=TabSchrift.getPos(iAic,sKennung,false,sBezeichnung,bNeu,-1);
		TabSchrift.setInhalt(iPos,"Schrift",font);
		TabSchrift.setInhalt(iPos,"Farbe",iFarbe);
                TabSchrift.setInhalt(iPos,"FarbeHG",iFarbeHG);
	}

	public void LoadFarbe()
	{
		//SQL Qry = new SQL(this);

		TabFarbe = new Tabellenspeicher(this,new String[] {"Aic","Kennung","Bezeichnung","Farbe"});
		//TabFarbe.bN=true;
		TabFarbe.setTitel("TabFarbe");
                Tabellenspeicher Tab=new Tabellenspeicher(this,"select aic_farbe,kennung,red,green,blue"+AU_Bezeichnung("farbe")+" from farbe",true,"Farbe"+iSprache);
		//if (Qry.open("select aic_farbe,kennung,red,green,blue"+AU_Bezeichnung("farbe")+" from farbe"))
		//{
			for(;!Tab.eof();Tab.moveNext())
			{
				Color Col=new Color(Tab.getI("red"),Tab.getI("green"),Tab.getI("blue"));
                                String sKennung=Tab.getS("Kennung");
				putTabFarbe(Tab.getI("Aic_Farbe"),sKennung,Tab.getS("Bezeichnung"),Col,true);

				if(sKennung.equals(".holiday"))
					ColHoliday=Col;
				else if(sKennung.equals(".delete"))
					ColLoeschen=Col;
				else if(sKennung.equals(".new"))
					ColNeu=Col;
				else if(sKennung.equals(".edit"))
					ColAendern=Col;
                                else if(sKennung.equals(".kein"))
					ColKein=Col;
                                else if(sKennung.equals(".change"))
					Static.ColChange=Col;
				else if(sKennung.equals(".select"))
					ColSelect=Col;
				else if(sKennung.equals(".Tselect"))
				{
					ColTSelect=Col;
					if (Static.bND)
						UIManager.put("ToggleButton.select", ColTSelect);  
				}
				else if(sKennung.equals(".today"))
					ColToday=Col;
                else if(sKennung.equals(".back"))
					ColBackground=Col;
                else if(sKennung.equals(".EF"))
					Static.ColEF=Col;
                else if(sKennung.equals(".HS"))
					ColHS=Col;
                else if(sKennung.equals(".Admin"))
                	ColAdmin=Col;
                else if(sKennung.equals(".table"))
					ColTable=Col;
                                else if(sKennung.equals(".plan"))
					ColPlanung=Col;
                                else if(sKennung.equals(".always"))
					ColZwingend=Col;
                                else if(sKennung.equals(".Tab_Bez"))
                                {
                                	ColTab_Bez=Col;
                                	//fixtestError("ColTab_Bez="+ColTab_Bez);
                                }
                                else if(sKennung.equals(".breakpoint"))
					ColBreakpoint=Col;
                            else if(sKennung.equals(".var"))
            					ColVar=Col;
                                else if(sKennung.equals(".debug"))
					ColDebug=Col;
                                else if(sKennung.equals(".memo"))
					ColMemo=Col;
                                else if(sKennung.equals(".qry"))
					ColQRY=Col;
                                else if(sKennung.equals(".zr"))
					ColZR=Col;
                                else if(sKennung.equals(".kzr"))
					ColKeinZR=Col;
                                else if(sKennung.equals(".push"))
					ColPush=Col;
                                else if(sKennung.equals(".column"))
					ColSpalte=Col;
                                else if(sKennung.equals(".code"))
					ColBefehl=Col;
                                else if(sKennung.equals(".hide"))
					ColHide=Col;
                                else if(sKennung.equals(".Austritt"))
					ColAustritt=Col;
                                else if(sKennung.equals(".Mo"))
					ColMo=Col;
                                else if(sKennung.equals(".Di"))
					ColDi=Col;
                                else if(sKennung.equals(".Mi"))
					ColMi=Col;
                                else if(sKennung.equals(".Do"))
                                        ColDo=Col;
                                else if(sKennung.equals(".Fr"))
                                        ColFr=Col;
                                else if(sKennung.equals(".Sa"))
                                        ColSa=Col;
                                else if(sKennung.equals(".So"))
                                        ColSo=Col;
                                else if(sKennung.equals(".Mo2"))
                                        ColMo2=Col;
                                else if(sKennung.equals(".Di2"))
                                        ColDi2=Col;
                                else if(sKennung.equals(".Mi2"))
                                        ColMi2=Col;
                                else if(sKennung.equals(".Do2"))
                                        ColDo2=Col;
                                else if(sKennung.equals(".Fr2"))
                                        ColFr2=Col;
                                else if(sKennung.equals(".Sa2"))
                                        ColSa2=Col;
                                else if(sKennung.equals(".So2"))
                                        ColSo2=Col;
                                else if(sKennung.equals(".1"))
                                        Col1=Col;
                                else if(sKennung.equals(".2"))
                                        Col2=Col;
                                else if(sKennung.equals(".Vorbedingung"))
                                        ColVorbedingung=Col;
                                else if(sKennung.equals(".Rahmen"))
                                        ColRahmen=Col;
                                else if(sKennung.equals(".Alle"))
                                        ColAlle=Col;
                                else if(sKennung.equals(".Thread"))
                                        ColThread=Col;
                                else if(sKennung.equals(".Calc"))
                                        ColCalc=Col;
                                else if(sKennung.equals(".Calc2"))
                                        ColCalc2=Col;
                                else if(sKennung.equals(".Text"))
                                        ColText=Col;
                                else if(sKennung.equals(".Sperre"))
                                        ColSperre=Col;
                                else if(sKennung.equals(".ZSum"))
					ColZSum=Col;
                                else if(sKennung.equals(".Doppelt"))
                                  ColDoppelt=Col;
                                else if(sKennung.equals(".Tod"))
                                    ColTod=Col;
                                else if(sKennung.equals(".pers"))
                                    ColPers=Col;
                                else if(sKennung.equals(".JavaFX"))
                                    ColJavaFX=Col;
                                else if(sKennung.equals(".Web"))
                                    ColWeb=Col;
                                else if(sKennung.equals(".Favorit"))
                                    ColFavorit=Col;
                                else if(sKennung.equals(".Edit"))
                                    ColEdit=Col;
                                else if(sKennung.equals(".Info"))
                                    ColInfo=Col;
                                else if(sKennung.equals(".Bild"))
                                    ColBild=Col;
                                else if(sKennung.equals(".Zusatz"))
                                    ColZusatz=Col;
                                else if(sKennung.equals(".Toolbar"))
                                {
                                    ColToolbar=Col;
                                    if (Static.bND)
                                    {
                                    	UIManager.put("ToggleButton.background", ColToolbar);   
                                    }
                                }
                                else if(sKennung.equals(".Btn"))
                                {
                                    ColBtn=Col;
                                    if (Static.bND)
                                    	UIManager.put("Button.background",  ColBtn);

                                }
                                else if(sKennung.equals(".white"))
                                    Static.ColWhite=Col;
                                else if(sKennung.equals(".ND_Memo"))
                                    Static.ColMemo=Col;
                                else if(sKennung.equals(".Linie"))
                                    Static.ColLinie=Col;
                                else if(sKennung.equals(".Hover"))
                                    ColHover=Col;
                                else if (Static.bND && sKennung.startsWith(".ND_"))
                                {
                                	if(sKennung.equals(".ND_EF"))
                                		Static.ColEF=Col;
                                    else if(sKennung.equals(".ND_HS"))
                                    	ColHS=Col;
                                    else if(sKennung.equals(".ND_back"))
                                    	ColBackground=Col;
                                    else if(sKennung.equals(".ND_select"))
                                    {
                                    	ColSelect=Col;
                                    	UIManager.put("Button.select", ColSelect);
                                    	UIManager.put("ToggleButton.select", ColTSelect);
                                    }
                                    else if(sKennung.equals(".ND_change"))
                                    	Static.ColChange=Col;
                                    else if(sKennung.equals(".ND_table"))
                                    	ColTable=Col;
                                    else if(sKennung.equals(".ND_plan"))
                                    	ColPlanung=Col;
                                    else //if (sKennung.startsWith(".ND"))
                                    	printError("New Design-Farbe "+sKennung+" wird noch nicht unmterstützt");
                                }
				//Qry.moveNext();
			}
			//Qry.close();
                        if (Static.ColEF==null)
                          Static.ColEF=ColBackground;
                        if (ColHS==null)
                          ColHS=ColBackground;
                        if (ColTable==null)
                          ColTable=ColBackground;
                        if (ColPlanung==null)
                          ColPlanung=ColTable;
                        if (ColAlle==null)
                          ColAlle=ColHS;
                        if (ColFavorit==null)
                        	ColFavorit=ColSelect;
		//}
                //Qry.free();
      }
	
	  private String checkSchrift(String sSchrift,String sKennung)
	  {
		  if (Mojave())
		  {
			  if (sKennung.startsWith(".Outliner") || sKennung.startsWith(".Table") || sKennung.equals(".Modell")) 
				  return "Lucida Sans";
		  }
		  return sSchrift;
	  }
	  
//	  public void LoadSchrift()
//	  {
//		  LoadSchrift(false,getFontFaktor());
//	  }

      public void LoadSchrift(boolean bZwingend,int iProz)
      {  	 
        if (bZwingend || iProz != iProzOld)
        {
//        	fixtestError("LoadSchrift: "+iProz+" %");
          iProzOld=iProz;
          //progInfo("LoadSchrift:"+iProz);
          Ampel.refresh(this);
		TabSchrift = new Tabellenspeicher(this,new String[] {"Aic","Kennung","Bezeichnung","Schrift","Farbe","FarbeHG"});
		//TabSchrift.bN=true;
		TabSchrift.setTitel("TabSchrift");
                Tabellenspeicher Tab=new Tabellenspeicher(this,"select aic_schrift,schrift.kennung,schriftart.kennung schriftart,"+SIZE()+",bold,italic,aic_farbe,far_aic_farbe"+AU_Bezeichnung("schrift")+
                    " from schrift join schriftart on schrift.aic_schriftart=schriftart.aic_schriftart",true,"Schrift"+iSprache);
            Static.sFont="Arial,sans-serif; font-size:"+(10*iProz/100)+"px; color:black";
            if (bAppl)
            {
            	Font fontX=new Font("Blackmoor LET",0,12*iProz/100);
            	Color ColX=Color.RED;
            	fontTitel=fontX;
            	fontBezeichnung=fontX;
            	fontTitelzeile=fontX;
            	fontEF_Bez=fontX;
            	fontEF_Bez2=fontX;
            	fontAServer=fontX;
            	fontButton=fontX;
            	fontModell=fontX;
            	fontJConsole=fontX;
            	fontStandard=fontX;
            	fontTable=fontX;
            	fontTableT=fontX;
            	fontTableS=fontX;
            	Static.fontOutliner=fontX;
            	fontOutlinerT=fontX;
            	fontMessage=fontX;
            	fontPopup=fontX;
            	fontTooltip=fontX;
            	fontInaktiv=fontX;
            	
            	ColTitel=ColX;
            	ColBezeichnung=ColX;
            	ColEF_Bez=ColX;
            	ColEF_Bez2=ColX;
            	ColStandard=ColX;
            	//Tab.moveLast();
            }
			for(;!Tab.eof();Tab.moveNext())
			{
				String sKennung=Tab.getS("Kennung");
				String sSA=Tab.getS("schriftart");
				Font font=null;
				if (sSA.startsWith("."))
				{
					try
					{
						font=Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/TTF/"+sSA.substring(1)+".ttf"));
//						if(!sKennung.equals(".Barcode"))
								font=font.deriveFont(((float)Tab.getI("size"))*(sKennung.equals(".Barcode") ? 100:iProz)/100);
						if (Tab.getB("bold"))
							font=font.deriveFont(Font.BOLD);
						if (Tab.getB("italic"))
							font=font.deriveFont(Font.ITALIC);
					}
					catch(Exception e)
					{
						printError("cant't create "+sSA+"-Font:"+e);
					}
				}
				else
					font=new Font(checkSchrift(Tab.getS("schriftart"),sKennung),Tab.getI("bold")*Font.BOLD+Tab.getI("italic")*Font.ITALIC,Tab.getI("size")*iProz/100);
				putTabSchrift(Tab.getI("Aic_schrift"),sKennung,Tab.getS("Bezeichnung"),font,Tab.getI("Aic_Farbe"),Tab.getI("Far_Aic_Farbe"),true);
				//fixtestError("Schrift "+sKennung+": "+sSA+"/"+font.getName()+","+font.isTransformed()+","+font.getSize()+","+font.hasUniformLineMetrics()+","+font.getAttributes());
				if(sKennung.equals(".Titel"))
				{
					fontTitel = font;
                                        //fixInfo("fontTitel="+fontTitel);
					ColTitel=setColor(ColTitel,Tab.getI("Aic_Farbe"));
					//if(TabFarbe.posInhalt("Aic",Tab.getI("Aic_Farbe")))
					//	ColTitel=(Color)TabFarbe.getInhalt("Farbe");
				}
				else if(sKennung.equals(".Bezeichnung"))
				{
					fontBezeichnung = font;
//                                        if (fontEF_Bez==null)
//                                          fontEF_Bez=font;
                                        //fixInfo("fontBezeichnung="+fontBezeichnung);
                                        ColBezeichnung=setColor(ColBezeichnung,Tab.getI("Aic_Farbe"));
					//if(TabFarbe.posInhalt("Aic",Tab.getI("Aic_Farbe")))
					//	ColBezeichnung=(Color)TabFarbe.getInhalt("Farbe");
				}
                                else if(sKennung.equals(".Titelzeile"))
                                {
                                        fontTitelzeile = font;
                                        //ColBezeichnung=setColor(ColBezeichnung,Tab.getI("Aic_Farbe"));
                                        //if(TabFarbe.posInhalt("Aic",Tab.getI("Aic_Farbe")))
                                        //	ColBezeichnung=(Color)TabFarbe.getInhalt("Farbe");
                                }
                                else if(sKennung.equals(".EF_Bez"))
                                {
                                        fontEF_Bez = font;
//                                        if (fontEF_Bez2==null)
//                                          fontEF_Bez2=font;
                                        //fixInfo("fontButton="+fontButton);
                                        ColEF_Bez=setColor(ColEF_Bez,Tab.getI("Aic_Farbe"));
                                        //if(TabFarbe.posInhalt("Aic",Tab.getI("Aic_Farbe")))
                                        //       ColEF_Bez=(Color)TabFarbe.getInhalt("Farbe");
                                }
                                else if(sKennung.equals(".EF_Bez2"))
                                {
                                        fontEF_Bez2 = font;
                                        ColEF_Bez2=setColor(ColEF_Bez2,Tab.getI("Aic_Farbe"));
                                        //if(TabFarbe.posInhalt("Aic",Tab.getI("Aic_Farbe")))
                                        //       ColEF_Bez2=(Color)TabFarbe.getInhalt("Farbe");
                                }
                                else if(sKennung.equals(".AServer"))
                                  fontAServer = font;
                                else if(sKennung.equals(".Button"))
                                  fontButton = font;
                                else if(sKennung.equals(".Modell"))
                                {
                                  fontModell = font;
                                  if (fontRem==null)
                                	  fontRem=fontModell;
                                }
                                else if(sKennung.equals(".Rem"))
                                {
                                	fontRem = font;
                                	ColRem=setColor(ColRem,Tab.getI("Aic_Farbe"));
                                }
                                else if(sKennung.equals(".Java-Konsole"))
                                	fontJConsole = font;
				else if(sKennung.equals(".Standard"))
				{
					fontStandard = font;
                    Static.fontStandard=font;
//                    if (Static.fontOutliner==null)
//                    	Static.fontOutliner=fontOutliner;
                                        //fixInfo("fontStandard="+fontStandard);
					ColStandard=setColor(ColStandard,Tab.getI("Aic_Farbe"));
					//if(TabFarbe.posInhalt("Aic",Tab.getI("Aic_Farbe")))
					//	ColStandard=(Color)TabFarbe.getInhalt("Farbe");

					//StyDel = new JCOutlinerNodeStyle();//new JCOutlinerComponent().getDefaultNodeStyle();
					//StyDel.setFont(fontStandard);
					//StyDel.setForeground(ColLoeschen);
				}
				else if(sKennung.equals(".Outliner"))
				{
					//fontOutliner= font;
					Static.fontOutliner=font;
				}
				else if(sKennung.equals(".Table"))
				{
					//fontOutliner= font;
					fontTable=font;
				}
				else if(sKennung.equals(".OutlinerT"))
					fontOutlinerT= font;
				else if(sKennung.equals(".TableT"))
					fontTableT= font;
				else if(sKennung.equals(".TableS"))
					fontTableS= font;
				else if(sKennung.equals(".Summe"))
                {
                        fontSumme = font;
                        ColSumme=setColor(ColSumme,Tab.getI("Aic_Farbe"));
                        //if(TabFarbe.posInhalt("Aic",Tab.getI("Aic_Farbe")))
                        //  ColSumme=(Color)TabFarbe.getInhalt("Farbe");
                }
                else if(sKennung.equals(".Summe2"))
                {
                        fontSumme2 = font;
                        ColSumme2=setColor(ColSumme2,Tab.getI("Aic_Farbe"));
                        //if(TabFarbe.posInhalt("Aic",Tab.getI("Aic_Farbe")))
                        //  ColSumme2=(Color)TabFarbe.getInhalt("Farbe");
                }
                else if(sKennung.equals(".Message"))
                {
                        fontMessage = font;
                        //ColMessage=setColor(ColMessage,Tab.getI("Aic_Farbe"));
                        //if(TabFarbe.posInhalt("Aic",Tab.getI("Aic_Farbe")))
                        //  ColSumme2=(Color)TabFarbe.getInhalt("Farbe");
                }
                else if(sKennung.equals(".Popup"))
                {
                  fontPopup = font;
                }
                else if(sKennung.equals(".Tooltip"))
                {
                  fontTooltip = font;
                  UIManager.put("ToolTip.font", fontTooltip);
                }
                else if(sKennung.equals(".Disabled"))
                {
                        fontDisabled = font;
                        ColDisabled=setColor(ColDisabled,Tab.getI("Aic_Farbe"));
                }
                else if(sKennung.equals(".InAktiv"))
                  fontInaktiv = font;
                else if(sKennung.equals(".Login"))
                  fontLogin =font;
                else if(sKennung.equals(".Barcode"))
                  fontBarcode =font;
                else if (Static.bND && sKennung.startsWith(".ND_"))
                {
                	if(sKennung.equals(".ND_EF_Bez"))
                    {
                            fontEF_Bez = font;
                            ColEF_Bez=setColor(ColEF_Bez,Tab.getI("Aic_Farbe"));
                    }
                	else if(sKennung.equals(".ND_Bezeichnung"))
    				{
    					fontBezeichnung = font;
    					ColBezeichnung=setColor(ColBezeichnung,Tab.getI("Aic_Farbe"));
    				}
                	else 
                    	printError("New Design-Schrift "+sKennung+" wird noch nicht unmterstützt");
                }
                else if (Mojave())
                {
                	if(sKennung.equals(".MOutliner"))
    					Static.fontOutliner=font;
    				else if(sKennung.equals(".MTable"))
    					fontTable=font;
    				else if(sKennung.equals(".MOutlinerT"))
    					fontOutlinerT= font;
    				else if(sKennung.equals(".MTableT"))
    					fontTableT= font;
    				else if(sKennung.equals(".MTableS"))
    					fontTableS= font;
    				else if(sKennung.equals(".MModell"))
                        fontModell = font;
    				else if(sKennung.equals(".MRem"))
                        fontRem = font;
                }
			}
			if(fontBezeichnung==null)
                          fontBezeichnung=fontStandard;
                        if (fontTitelzeile==null)
                          fontTitelzeile=fontBezeichnung;
                        if(fontButton==null)
                          fontButton=fontTitel;

          }
        }

      public JCOutlinerNodeStyle setColor(JCOutlinerNodeStyle Sty,Color Col)
      {
	Sty = new JCOutlinerNodeStyle(Sty);
	Sty.setForeground(Col);
	return Sty;
      }

      public void setStyle(JCOutlinerNode Nod,Color Col)
      {
        JCOutlinerNodeStyle Sty = new JCOutlinerNodeStyle();
        Sty.setItemIcon(null);
        Sty.setForeground(Col);
        Nod.setStyle(Sty);
      }

      public Color setColor(Color C, int iAic)
      {
	int iPos=-2;
	if (iAic>0)
	  iPos=TabFarbe.getPos("Aic", iAic);
	if (iPos>=0)
	  return (Color)TabFarbe.getInhalt("Farbe",iPos);
	else
	  return C;
      }

      public int getColor(String sKennung)
      {
        int iPos=TabFarbe.getPos("Kennung", sKennung);
        return iPos<0 ? 0:((Color)TabFarbe.getInhalt("Farbe",iPos)).getRGB();
      }
      
      public int getColor(int iAic)
      {
        int iPos=TabFarbe.getPos("Aic", iAic);
        return iPos<0 ? 0:((Color)TabFarbe.getInhalt("Farbe",iPos)).getRGB();
      }
      
      public int getFontFaktor()
      {
    	return getFontFaktorO()*(Mal2()?2:1);
      }

    public int getFontFaktorO()
    {
      if (Static.FomStart==null && Static.bND)
    	  return 100;
      int i= ((iInfos&FONTFAKT)/0x1000+5)*10;
      if (i==50) i=100;
      else if (i>150) i=150;
      else if (i<80) i=80;
      return i;
    }

	public void addLabel(JPanel Pnl,String s)
	{
    if (Prog())
      fixtestError("nicht übersetzbar: Label "+s);
		JLabel Lbl=new JLabel(s==null || s.equals("") ? "":" "+s+":");
		Lbl.setFont(fontBezeichnung);
		Lbl.setForeground(ColBezeichnung);
		Pnl.add(Lbl);
	}

        public void addLabel(JPanel Pnl,String s,JComponent Comp)
        {
          addLabel(Pnl,s,Comp,null);
        }

        public void addLabel(JPanel Pnl,String s,JComponent Comp,String sPos)
        {
          boolean b=sPos!=null && sPos.equals("1");
          int iPos=getPosBegriff("Show",s);
          JLabel Lbl=new JLabel((b?" ":"")+(iPos<0?s:getBegBez3(iPos))+(b?": ":":"));
          Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
          Lbl.setFont(fontBezeichnung);
          Lbl.setForeground(ColBezeichnung);
          if (b)
        	  Lbl.setPreferredSize(new Dimension(150,16));
          if (iPos>=0)
          {
            setTooltip(TabBegriffe.getM(iPos, "Tooltip"), Lbl);
            if (!TabBegriffe.isNull(iPos, "HK"))
            {

              Lbl.setDisplayedMnemonic(TabBegriffe.getS(iPos, "HK").charAt(0));
              Lbl.setLabelFor(Comp);
            }
            if (Comp != null)
              setTooltip(TabBegriffe.getM(iPos, "Tooltip"), Comp);
          }
          if (sPos==null)
            addN(Lbl,Pnl);
          else
          {
            JPanel P=new JPanel(new BorderLayout(2,2));
            P.add("West",Lbl);
            P.add("Center",Comp);
            if (b || sPos.equals("0"))
              Pnl.add(P);
            else
              Pnl.add(sPos,P);
          }
	}

    public JLabel getLabelR(String s)
    {
    	int iPos=getPosBegriff("Show",s);
        JLabel Lbl=new JLabel(iPos<0?s:getBegBez3(iPos)+":");//getBegriffS("Show",s)+":");
        Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
        Lbl.setFont(fontBezeichnung);
        Lbl.setForeground(ColBezeichnung);
        if (iPos>=0)
        {
          setTooltip(TabBegriffe.getM(iPos, "Tooltip"), Lbl);
          Lbl.setName("L"+TabBegriffe.getI(iPos, "Aic"));
        }
        return Lbl;
    }
        
    public void addLabel2(JPanel Pnl,String s)
	{
          /*int iPos=getPosBegriff("Show",s);
          JLabel Lbl=new JLabel(iPos<0?s:getBegBez3(iPos)+":");//getBegriffS("Show",s)+":");
          Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
          Lbl.setFont(fontBezeichnung);
          Lbl.setForeground(ColBezeichnung);
          if (iPos>=0)
            setTooltip(TabBegriffe.getM(iPos, "Tooltip"), Lbl);*/
          Pnl.add(getLabelR(s));
	}

        public void addLabel3(JComponent Pnl,String s)
        {
          String sLabel=s==null ? null:getBegriffS("Label",s);
          JLabel Lbl=new JLabel(sLabel==null ? "":Static.cin(sLabel,":"));
          Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
          Lbl.setFont(fontBezeichnung);
          Lbl.setForeground(ColBezeichnung);
          Pnl.add(Lbl);
        }

        public void addLabel4(JComponent Pnl,String s)
        {
          int iPos=getPosBegriff("Label",s);
          JLabel Lbl=new JLabel(" "+Static.cin(iPos<0?s:getBegBez3(iPos),":"));
          Lbl.setHorizontalAlignment(SwingConstants.LEFT);
          Lbl.setFont(fontBezeichnung);
          Lbl.setForeground(ColBezeichnung);
          if (iPos>=0)
            setTooltip(TabBegriffe.getM(iPos, "Tooltip"), Lbl);
          Pnl.add(Lbl);
        }

	public void addComp(JPanel Pnl,Component Comp)
	{
		Comp.setFont(fontStandard);
		Comp.setForeground(ColStandard);
		Pnl.add(Comp);
	}

        public void addComp2(JPanel Pnl,Component Comp)
        {
                Comp.setFont(fontBezeichnung);
                Comp.setForeground(ColBezeichnung);
                Pnl.add(Comp);
        }


	public Font getFont(int iBegriffAIC)
	{
		if (iBegriffAIC==0)
		{
			printError("Global.getFont ohne Aic_Begriff aufgerufen!");
			return null;
		}
		//if (iBegriffAIC!=0)
		int iPos=TabBegriffe.getPos("Aic",new Integer(iBegriffAIC));
		int iSchrift = TabBegriffe.getI(iPos,"Schrift");
                int iPosS=iSchrift==0 ? -1:TabSchrift.getPos("Aic",new Integer(iSchrift));
		if (iPosS>=0)
			return (Font)TabSchrift.getInhalt("Schrift",iPosS);
		else
			return null;
	}

	/*public Font getFont2(int iPos)
	{
		if (iBegriffAIC!=0)
			TabBegriffe.posInhalt("Aic",new Integer(iBegriffAIC));
		int iSchrift = TabBegriffe.getI("Schrift");
		if (iSchrift != 0 && TabSchrift.posInhalt("Aic",new Integer(iSchrift)))
			return (Font)TabSchrift.getInhalt("Schrift");
		else
			return null;
	}*/

        public Font getSchrift(int iAic,Font rfont)
        {
          if (iAic==0)
            return rfont;
          else
          {
            int iPos=TabSchrift.getPos("aic",iAic);
            return iPos<0 ? rfont:(Font)TabSchrift.getInhalt("schrift",iPos);
          }
        }

	public void setSchrift(int iBegriffAIC,Component Lbl)
	{
		setSchrift(iBegriffAIC,Lbl,fontStandard);
	}

	public void setSchrift(int iBegriffAIC,Component Lbl,Font rfont)
	{
		Font font=getFont(iBegriffAIC);
		if (font==null)
                  Lbl.setFont(rfont);
                else
		{
			Lbl.setFont(font);
			//if (font != fontStandard)
			//{
				int iFarbe = TabSchrift.getI("Farbe");
				if (iFarbe != 0 && TabFarbe.posInhalt("Aic",new Integer(iFarbe)))
                                  Lbl.setForeground((Color)TabFarbe.getInhalt("Farbe"));
			//}
		}
	}

	public void setSchrift2(int iPos,Component Lbl,Font rfont)
	{
	  int iSchrift = TabBegriffe.getI(iPos,"Schrift");
	  if (iSchrift == 0)
	    Lbl.setFont(rfont != null ? rfont:fontStandard);
          else
          {
            int iPosS=TabSchrift.getPos("Aic",iSchrift);
            if (iPosS>=0)
            {
  		Lbl.setFont((Font)TabSchrift.getInhalt("Schrift",iPosS));
  		int iFarbe = TabSchrift.getI(iPosS,"Farbe");
  		if (iFarbe > 0)
  		{
  		  int iPosF=TabFarbe.getPos("Aic",iFarbe);
  		  if (iPosF>=0)
  		    Lbl.setForeground((Color)TabFarbe.getInhalt("Farbe",iPosF));
  		  else
  		    printError("Global.setSchrift2: Farbe "+iFarbe+" nicht gefunden!");
  		}
            }
            else
              printError("Global.setSchrift2: Schrift "+iSchrift+" nicht gefunden!");
          }
	}

	public void setSchrift2(int iPos,jclass.bwt.JCOutlinerNodeStyle Lbl,Font rfont)
	{
	  int iSchrift = TabBegriffe.getI(iPos,"Schrift");
	  if (iSchrift == 0)
	    Lbl.setFont(rfont != null ? rfont:fontStandard);
          else
          {
            int iPosS=TabSchrift.getPos("Aic",iSchrift);
            if (iPosS>=0)
            {
  		Lbl.setFont((Font)TabSchrift.getInhalt("Schrift",iPosS));
  		int iFarbe = TabSchrift.getI(iPosS,"Farbe");
  		if (iFarbe > 0)
  		{
  		  int iPosF=TabFarbe.getPos("Aic",iFarbe);
  		  if (iPosF>=0)
  		    Lbl.setForeground((Color)TabFarbe.getInhalt("Farbe",iPosF));
  		  else
  		    printError("Global.setSchrift2: Farbe "+iFarbe+" nicht gefunden!");
  		}
            }
            else
              printError("Global.setSchrift2: Schrift "+iSchrift+" nicht gefunden!");
          }
	}

	public void setSchrift(int iBegriffAIC,jclass.bwt.JCOutlinerNodeStyle Lbl)
	{
		Font font=getFont(iBegriffAIC);
		if (font != null)
		{
			Lbl.setFont(font);
			int iFarbe = TabSchrift.getI("Farbe");
			if (iFarbe != 0 && TabFarbe.posInhalt("Aic",new Integer(iFarbe)))
				Lbl.setForeground((Color)TabFarbe.getInhalt("Farbe"));
		}
	}

// Feiertage verwalten

	private void Feiertage_einlesen(int iJahr)
	{
          testInfo("          ******************           Feiertage_einlesen für "+iJahr);
		long lOsternW = Static.CalcWestEaster(iJahr).getTime();//+10800000l;
		long lOsternE = Static.CalcEastEaster(iJahr).getTime();
		for (int iPos=0;iPos<TabFeiertagspeicher.size();iPos++)
		{
			int iVon = TabFeiertagspeicher.getI(iPos,"Von");
			int iBis = TabFeiertagspeicher.getI(iPos,"Bis");
			if (iVon<=iJahr && (iBis<1500 || iBis>=iJahr))
			{
                          int iPosF=TabFeiertage.newLine();
                          DateWOD dt = null;
				if (TabFeiertagspeicher.getS(iPos,"Monat").equals("Ostersonntag"))
				{
					dt = new DateWOD(lOsternW+86400000l*(long)TabFeiertagspeicher.getI(iPos,"Tag"));
					dt.setTimeZero();
				}
				else if (TabFeiertagspeicher.getS(iPos,"Monat").equals("Ost-Ostern"))
				{
					dt = new DateWOD(lOsternE+86400000l*(long)TabFeiertagspeicher.getI(iPos,"Tag"));
					dt.setTimeZero();
				}
				else
					dt = new DateWOD(iJahr,TabFeiertagspeicher.getI(iPos,"Monat"),TabFeiertagspeicher.getI(iPos,"Tag"));
                                TabFeiertage.setInhalt(iPosF,"Datum",dt);
				TabFeiertage.setInhalt(iPosF,"Bezeichnung",TabFeiertagspeicher.getInhalt("Bezeichnung",iPos));
                                TabFeiertage.setInhalt(iPosF,"Kennung",TabFeiertagspeicher.getInhalt("Kennung",iPos));
                                TabFeiertage.setInhalt(iPosF,"Land",TabFeiertagspeicher.getI(iPos,"aic_land"));
                                TabFeiertage.setInhalt(iPosF,"Stamm",TabFeiertagspeicher.getI(iPos,"aic_stamm"));
                                TabFeiertage.setInhalt(iPosF,"Mandant",TabFeiertagspeicher.getI(iPos,"aic_mandant"));
                                TabFeiertage.setInhalt(iPosF,"Aic",TabFeiertagspeicher.getI(iPos,"aic_feiertag"));
                                int iLD=Static.DateToInt(dt.toTimestamp());
                                if (!VecFeiertage.contains(iLD))
                                  VecFeiertage.addElement(iLD);
			}
		}
		VecJahre.addElement(new Integer(iJahr));
                //TabFeiertage.showGrid("TabFeiertage");
	}

        public Zeit getFeiertagDatum(int iFt)
        {
          int iPos=TabFeiertage.getPos("Aic",iFt);
          return iPos<0 ? null:new Zeit(TabFeiertage.getTimestamp(iPos,"Datum"),"dd.MM.yyyy");
        }

        public void addFeiertag(DateWOD dt,String s)
        {
          int iPosF=TabFeiertage.newLine();
          TabFeiertage.setInhalt(iPosF,"Datum",dt);
          TabFeiertage.setInhalt(iPosF,"Bezeichnung",s==null || s.equals(Static.sLeer) ? "Hugo-Feiertag":s);
          TabFeiertage.setInhalt(iPosF,"Stamm",keinStamm);
          TabFeiertage.setInhalt(iPosF,"Mandant",getMandant());
          int iLD=Static.DateToInt(dt.toTimestamp());
          if (!VecFeiertage.contains(iLD))
            VecFeiertage.addElement(iLD);
          //TabFeiertage.showGrid("TabFeiertage");
        }

        public void clearFeiertag()
        {
          for (int i=0;i<TabFeiertage.size();i++)
            if (TabFeiertage.getI(i,"Land")==0)
            {
              TabFeiertage.clearInhalt(i);
              i--;
            }
        }

        @SuppressWarnings("unchecked")
        private boolean checkFeiertag()
        {
          //int iPos=TabLand.getPos("aic_land",riLand);
          //if (iPos>=0)
          //{
            //if (TabLand.isNull(iPos,"FTS"))
        	if (iSprFT != iSprache)
        	{
        		iSprFT=iSprache;
        		TabFeiertagspeicher=null;
        	}
            if (TabFeiertagspeicher==null)
            {
//              fixtestError("checkFeiertag mit Sprache="+iSprache+" und Land="+iLand);

              TabFeiertagspeicher = new Tabellenspeicher(this, "select code.kennung monat,tag,f.kennung" + AU_Bezeichnung("Feiertag", "f") +
                  " Bezeichnung,von,bis,z.aic_stamm,z.aic_land,z.aic_mandant,f.aic_feiertag from code join feiertag f on code.aic_code=f.aic_code"+
                  " join feiertag_zuordnung z on f.aic_feiertag=z.aic_feiertag"+/* where aic_land=" + riLand +*/" order by monat", true, "Feiertag" + "_" + iSprache);
              TabFeiertagspeicher.checkBezeichnung();
              TabFeiertagspeicher.setTitel("TabFeiertagspeicher");
              TabFeiertage = new Tabellenspeicher(this, new String[] {"Datum", "Bezeichnung","Kennung","Land","Stamm","Mandant","Aic"});
              TabFeiertage.setTitel("TabFeiertage");
              VecJahre = new Vector();
              //TabLand.setInhalt(iPos,"FTS",TabFeiertagspeicher);
              //TabLand.setInhalt(iPos,"FT",TabFeiertage);
              //TabLand.setInhalt(iPos,"Jahre",VecJahre);
              //TabLand.showGrid();
              //TabFeiertagspeicher.showGrid("Feiertagspeicher");

              return true;
            }
            else
              return false;
            /*else
            {
              TabFeiertagspeicher=(Tabellenspeicher)TabLand.getInhalt("FTS",iPos);
              TabFeiertage=(Tabellenspeicher)TabLand.getInhalt("FT",iPos);
              VecJahre=(Vector)TabLand.getInhalt("Jahre",iPos);
              return false;
            }*/
          /*}
          else
          {
            Static.printError("Land " + riLand + " nicht gefunden!");
            return false;
          }*/
        }

        public void RefreshFeiertage()
        {
          TabFeiertagspeicher=null;
          LoadFeiertage();
        }

	private void LoadFeiertage()
	{
		//if(riLand>0)
		//{
			printInfo(2,"LoadFeiertage");
			if (checkFeiertag())
                        {
                          DateWOD date = new DateWOD();
                          Feiertage_einlesen(date.getYear());
                          if (date.getMonth() > 6)
                            Feiertage_einlesen(date.getYear() + 1);
                          else
                            Feiertage_einlesen(date.getYear() - 1);
                        }
                        //TabFeiertage.showGrid("Feiertage");
		//}
	}

        public String Feiertag(DateWOD date)
        {
          //fixtestInfo("Feiertagsabfrage ohne Qry:"+date);
          return Feiertag(date,0,true);
        }

        public String Feiertag(DateWOD date,int iQry)
        {
          return Feiertag(date,iQry,true);
        }

        private String FTC(DateWOD date2,boolean bBez,int riLand,int iRegion,int iReligion)
        {
          //if (VecJahre.indexOf(new Integer(date.getYear())) == -1)
          //    Feiertage_einlesen(date.getYear());
          int iPos=-1;
          for (int i=0;iPos<0 && i<TabFeiertage.size();i++)
          {
            int iS=TabFeiertage.getI(i,"Stamm");
            int iL=TabFeiertage.getI(i,"Land");
            int iM=TabFeiertage.getI(i,"Mandant");
            if ((riLand==0 && iLand==iL || riLand==iL || iL==0) && (iS==keinStamm || iS==iRegion || iS==iReligion) &&
                (iM==1 || iMandant==1 || VecMandantRead.contains(iM)) && date2.equals(TabFeiertage.getInhalt("Datum",i)))
              iPos=i;
            if (iL==0 && Info())
              testInfo("FTC:"+iL+"/"+iS+"/"+iM+"/"+date2+"/"+TabFeiertage.getInhalt("Datum",i)+"->"+iPos);
            //int iPos = TabFeiertage.getPos("Datum", date2);
          }
          return iPos<0 ? "" : TabFeiertage.getS(iPos,bBez ? "Bezeichnung":"Kennung");
        }
        
    public void checkReadFeiertage(int iJahr)
    {
    	if (VecJahre.indexOf(new Integer(iJahr)) == -1)
            Feiertage_einlesen(iJahr);
    }

	public String Feiertag(DateWOD date,int iQry,boolean bBez)
	{
          long lClock2 = Static.get_Mikro_s();
          checkReadFeiertage(date.getYear());
          if (!VecFeiertage.contains(Static.DateToInt(date.toTimestamp())))
          {
            //clockµInfo("   kein Feiertag:"+date,lClock2);
            return "";
          }
          DateWOD date2 = (DateWOD)date.clone();
          date2.setTimeZero();
          //int iL=iLand;
          //int iPosL1=iQry==0 ? -1:TabLand.getPos("aic_land",iLand);
          //if (iPosL1<0 /*|| TabLand.isNull(iPosL,"ANR")*/)
          if (iQry==0)
          {
            //testInfo("Verwende Standard-Land");
            //String s=FTC(date,date2,bBez);
            //printInfo("                                    Feiertag1: "+date+","+iQry+"->"+s+","+iLand);
            String s=FTC(date2,bBez,0,0,0);
            if (Info())
              clockMikroInfo("   Feiertag:"+date2+"->"+s,lClock2);
            return s;
          }
          else
          {
            String s=null;
            int iL=0;
            /*Integer IQry=new Integer(iQry);
            for (int iPosL=0;iPosL<TabLand.size() && iL==0;iPosL++)
            {
              Object Obj=TabLand.getInhalt("ANR",iPosL);
              if (Obj != null && ((Vector)Obj).contains(IQry))
                iL=TabLand.getI(iPosL,"aic_land");
            }
            //testInfo("Verwende Land="+iL);

            if (iL>0)
            {
              checkFeiertag(iL);
              s=FTC(date,date2,bBez);
              checkFeiertag(iLand);
            }
            else if (TabLand.isNull(iPosL1,"ANR"))
            {
              s=FTC(date,date2,bBez);
            }
            else
            {
              defInfo2("Feiertag für "+iQry+" ist nicht ermittelbar!");
              s="";
            }*/
            int iR=0;
            int iRel=0;
            int iPosS=TabStamm.getPos("Aic",iQry);
            if (iPosS<0)
              fixInfo("Stammsatz "+iQry+" für Feiertag nicht gefunden!");
            else
            {
              iRel=TabStamm.getI(iPosS,"Religion");
              int iPosB=BSt_ANR(iQry);
              if (iPosB<0)
              {
                if (Info())
                  testInfo("BSt für " + iQry + " ist nicht ermittelbar!");
              }
              else
              {
                iR=TabBSt.getI(iPosB,"Region");
                int iPosL=TabLand.getPos("aic_stamm",TabBSt.getI(iPosB,"land"));
                if (iPosL<0)
                  testInfo("Land von "+TabStamm.getS(iPosS,"Bezeichnung")+" für Feiertag nicht gefunden!");
                else
                  iL=TabLand.getI(iPosL,"aic_land");
              }
            }
            s=FTC(date2,bBez,iL,iR,iRel);
            if (Info())
              clockMikroInfo("   Feiertag: "+date+"->"+s+", "+iL+"/"+iR+"/"+iRel,lClock2);
            //testInfo("                                   Feiertag2: "+date+","+iQry+"->"+s+","+iL+"/"+iR+"/"+iRel);
            return s;
          }
	}

// Label und Buttons verwalten

	/*public JLabel getLabel(int iAic,int iAlignment)
	{
		return TabBegriffe.posInhalt("Aic",new Integer(iAic)) ?  getLabel(iAlignment) : new JLabel("Lbl"+iAic,iAlignment);
	}

	public JLabel getLabel(String sKennung,int iAlignment)
	{
		getBegriff("Label",sKennung);
		return TabBegriffe.out() ? new JLabel(sKennung,iAlignment) : getLabel(iAlignment);
	}

	public JLabel getBildLabel(String sKennung,int iAlignment)
	{
	  progInfo("getBildLabel:"+TabBegriffe.getS("BildFile"));
		getBegriff("Bild",sKennung);
		return TabBegriffe.out() ? new JLabel(sKennung,iAlignment) : getLabel(iAlignment);
	}

	public JLabel getLabel(int iAlignment)
	{
		//progInfo("getLabel:"+TabBegriffe.getS("BildFile"));
		ImageIcon Img = LoadImageIcon();
		JLabel Lbl = Img==null ? new JLabel(getBegBez(),iAlignment) : new JLabel(Img,iAlignment);
		if (Img == null)
			setSchrift(0,Lbl,fontBezeichnung);
		return Lbl;
	}*/

        public JLabel getLabel(String sKennung,int iAlignment)
        {
          int iPos=getPosBegriff("Label",sKennung);
          return iPos<0 ? new JLabel(sKennung,iAlignment):getLabel2(iPos,iAlignment);
        }

        public JLabel getBildLabel(String sKennung,int iAlignment)
        {
          int iPos=getPosBegriff("Bild",sKennung);
          return iPos<0 ? new JLabel(sKennung,iAlignment):getLabel2(iPos,iAlignment);
        }


	public JLabel getLabel2(int iPos,int iAlignment)
	{
		//progInfo("getLabel:"+TabBegriffe.getS("BildFile"));
		ImageIcon Img = LoadImageIcon(iPos);
		JLabel Lbl = Img==null ? new JLabel(getBegBez2(iPos),iAlignment) : new JLabel(Img,iAlignment);
		if (Img == null)
		  setSchrift2(iPos,Lbl,null);
		Lbl.setName("L"+TabBegriffe.getI(iPos, "Aic"));
			//setSchrift(0,Lbl);
		/*if (TabFarbe.posInhalt("Aic",TabBegriffe.getInhalt("Farbe")))
			Btn.setBackground((Color)TabFarbe.getInhalt("Farbe"));*/
		return Lbl;
	}

        public JLabel getBild(int iPos,int iAlignment,int iFF)
        {
          ImageIcon Img = LoadImageIcon(iPos);
          if (iFF!=100 && Img != null)
          {
            float iWidth = Img.getIconWidth();
            float iHeight = Img.getIconHeight();
            Image rImg=Static.LoadImage(TabBegriffe.getS(iPos,Static.sBild),Static.DirImageSys);
            if (rImg != null)
              Img = new ImageIcon(rImg.getScaledInstance(Math.round(iWidth*iFF/100),Math.round(iHeight*iFF/100),Image.SCALE_SMOOTH));
          }
          JLabel Lbl = Img==null ? new JLabel("") : new JLabel(Img,iAlignment);
          return Lbl;
        }

	public AUCheckBox getCheckbox(int iAic)
	{
          int iPos=TabBegriffe.getPos("Aic",new Integer(iAic));
          return iPos>=0 ?  getCheckboxS(iPos,true) : new AUCheckBox("Cbx"+iAic);
	}

	public AUCheckBox getCheckbox(String sKennung)
	{
		return getCheckbox(sKennung,false);
	}

	public AUCheckBox getCheckbox(String sKennung,boolean b)
	{
	  AUCheckBox Cbx = null;
	  if (TabBegriffe==null)
	    Cbx=new AUCheckBox(sKennung);
	  else
	  {
	    int iPos=getPosBegriff("Checkbox",sKennung);
	    Cbx = iPos<0 ? new AUCheckBox(sKennung) : getCheckboxS(iPos,false);
		/*getBegriff("Checkbox",sKennung);
		AUCheckBox Cbx = TabBegriffe.out() ? new AUCheckBox(sKennung) : getCheckbox2(TabBegriffe.getPos());*/
	  }
	  Cbx.setSelected(b);
	  return Cbx;
	}

	public AUCheckBox getCheckboxS(int iPos,boolean bBInfo)
	{
		//ImageIcon Img = TabBegriffe.getS("BildFile").equals("") ? null : LoadImageIcon(TabBegriffe.getS("BildFile"));
		//AUCheckBox Cbx = Img==null ? new AUCheckBox(TabBegriffe.getS("Bezeichnung")) : new AUCheckBox(Img);
                //ImageIcon Img = LoadImageIcon(iPos);
                AUCheckBox Cbx = /*Img != null ? new AUCheckBox(getBegBez2(iPos),Img):*/new AUCheckBox(getBegBez2(iPos));
                if (bBInfo)
                  addBegriffInfo(iPos,"Checkbox."+TabBegriffe.getS(iPos,"Kennung"));
                if(TabBegriffe.getS(iPos,"HK").length()>0)
                {
                  //testInfo("Checkbox "+TabBegriffe.getS("DefBezeichnung")+": HotKey="+TabBegriffe.getS("HK"));
                  Cbx.setMnemonic(TabBegriffe.getS(iPos,"HK").charAt(0));
                  Cbx.setFocusable(false);
                }
		//if (Img == null)
                setSchrift2(iPos,Cbx,null);
		//setSchrift(0,Cbx);
                setTooltip(TabBegriffe.getM(iPos,"Tooltip"),Cbx);
                //fixtestInfo("Cbx "+TabBegriffe.getS(iPos,"Kennung")+":"+TabBegriffe.getM(iPos,"Tooltip"));
                Cbx.setName("C"+TabBegriffe.getI(iPos,"Aic"));
                /*Cbx.setEnabled(BerechtigungS(iPos));
                if (bInfoJeder && !isJederS(iPos))
                {
                  Cbx.setForeground(ColEF_Bez2);
                  fixInfo("Nicht Jeder: " + getBegBez2(iPos));
                }*/

		//if (Static.bMemo)
		//	Cbx.setToolTipText(!TabBegriffe.getM(iPos,"Info").equals("")?TabBegriffe.getS(iPos,"Info"):TabBegriffe.getS(iPos,"Bezeichnung"));
		return Cbx;
	}

        public void setTooltip(String s,JComponent C)
        {
          //fixtestInfo("setTooltip:"+s);
          if (C==null)
            return;
          if (s==null || s.equals(""))
            C.setToolTipText(null);
          else
          {
//        	  if (C instanceof JLabel)
//        		  fixtestError("setTooltip:"+s+" bei "+((JLabel)C).getText());
            C.setToolTipText(toHtml(/*s.indexOf("<br />")<0 && s.indexOf("<b>")<0  ? breakString(s,60):*/s));
          }
        }

        public void setEigTooltip(String sBez,String s,JComponent C,String sDt)
        {
          int iPos=getPosBegriff("Datentyp",sDt);
          String sTTDt=iPos<0 ? null:TabBegriffe.getM(iPos,"Tooltip");
          if ((sTTDt != null) && (sTTDt.equals("")))
            sTTDt=null;
          String sTT=toHtml("<b>"+sBez+"</b>"+(s==null || s.equals("") ?"":"<br><p width=\"250px\">"+getBody(s)+"</p>")+(sTTDt==null ?"":"<hr>"+sTTDt));
          //fixtestInfo("setEigTooltip:"+sTT);
          C.setToolTipText(sTT);
        }

        public String getHtmlPart(String s,boolean bReplace)
        {
          if (s==null)
            return Static.sLeer;
          else if (s.indexOf("<body")>0 && s.indexOf("</body>")>0)
          {
            s = s.substring(s.indexOf("<body") + 1, s.indexOf("</body>"));
            s = s.substring(s.indexOf(">")+1).trim();
            if (!bReplace)
              s=s.replaceAll("<p style=\"margin-top: 0\">","").replaceAll("</p>","");//"<br>");
            //fixtestInfo("getHtmlPart:" + s);
            return s;
          }
          else if (bReplace)
            return s.replaceAll("\n","<br>");
          else
            return s;
        }

        public String getBody(String s)
        {
          if (s.indexOf("<body")>0 && s.indexOf("</body>")>0)
          {
            s = s.substring(s.indexOf("<body") + 1, s.indexOf("</body>"));
            s = s.substring(s.indexOf(">")+1).trim();
            s = s.replaceAll("<p style=\"margin-top: 0\">", "").replaceAll("</p>","<br>");
            //fixtestError("getBody:" + s);
          }
          return s;
        }
        
        public boolean isHtml(String s)
        {
        	return s!=null && s.length()>3 && s.indexOf("<")<s.indexOf(">") && s.indexOf("<")>-1;
        }

        public String clearHtml(String s)
        {
          if (s != null && s.startsWith("<html"))
          {
        	  s=s.replaceAll("&nbsp;", " ");
            //s=s.substring(6,s.length()-7);
            int iPos=s.indexOf("<");
            while(iPos>=0)
            {
              int iPos2=s.indexOf(">");
              if (iPos2<0)
                iPos=-1;
              else
              {
                s = s.substring(0, iPos) + " " + s.substring(iPos2 + 1);
                iPos = s.indexOf("<");
              }
            }
            return s;
          }
          else
            return s;
        }

        /*public String clearHtml2(String s)
        {
          if (s != null)
          {
            int iPos=s.indexOf("<");
            while(iPos>=0)
            {
              int iPos2=s.indexOf(">");
              if (iPos2<0)
                iPos=-1;
              else
              {
                s = s.substring(0, iPos) + " " + s.substring(iPos2 + 1);
                iPos = s.indexOf("<");
              }
            }
            return s;
          }
          else
            return s;
        }*/

        /*public String breakString(String s,int i)
        {
          if (s.length()<=i)
            return s;
          String s2="";
          while (s.length()>i)
          {
            int iPos=s.substring(0,i+1).lastIndexOf(" ");
            if (iPos<i-20)
              iPos=i;
            s2+=s.substring(0,iPos)+"<br />";
            s=s.substring(iPos);
          }
          s2+=s;
          return s2;
        }*/

        public static String toHtml(String s)
        {
          return toHtml(s,true);
        }

        public static String toHtml(String s,boolean b)
        {
          int iP=s.indexOf("<Hilfe>");
          if (iP>0)
          {
            s = s.substring(0, iP);
            //progInfo("Rest gefunden ->"+s);
          }
          //progInfo(s);
          if (s.startsWith("<html") || (s.indexOf("&#")==-1 && s.indexOf("<")==-1 && s.indexOf("\n")==-1))
            return s;
          if(s.indexOf("\n")<0 || s.indexOf("<")<s.indexOf(">") || s.indexOf("&#")>=0)
            return b? "<html>"+s+"</html>":s;
          else
          {
            s= (b ? "<html>" : "") + s.replaceAll("\n", "<br>") + (b ? "</html>" : "");
            //progInfo(s);
            return s;
          }
        }
        
        public static String fillHtml(String s)
        {
        	if (s==null)
        		return "";
        	if (!s.startsWith("<html"))
        		s="<html><body>"+s;
        	if (!s.endsWith("</html>"))
        		s+="</body></html>";
        	return s;
        }

        public JRadioButton getRadiobutton(String sKennung)
        {
          if (TabBegriffe==null)
	    return new JRadioButton(sKennung);
          int iPos=getPosBegriff("Radiobutton",sKennung);
          JRadioButton Rad = iPos<0 ? new JRadioButton(sKennung) : getRadiobuttonS(iPos,false);
          return Rad;
        }

	public JRadioButton getRadiobutton(int iAic)
	{
          int iPos=TabBegriffe.getPos("Aic",new Integer(iAic));
          return iPos>=0 ?  getRadiobuttonS(iPos,true) : new JRadioButton("Rad"+iAic);
	}

        public void setRadiobuttonIcon(JRadioButton Rbn)
        {
          Rbn.setSelectedIcon(getImageIcon(Static.bND ? "rad1.png":"Rbn_ok.png"));
          Rbn.setPressedIcon(getImageIcon(Static.bND ? "rad1.png":"Rbn_ok.png"));
          Rbn.setIcon(getImageIcon(Static.bND ? "rad0.png":"Rbn_cancel.png"));
        }

	public JRadioButton getRadiobuttonS(int iPos,boolean bBInfo)
	{
		//ImageIcon Img = LoadImageIcon(iPos);
		JRadioButton Rad = /*Img==null ?*/ new JRadioButton(getBegBez2(iPos));// : new JRadioButton(Img);
                //Rad.setSelectedIcon(new DiamondIcon(Color.BLUE, true));//AUCheckBox.checked);
                setRadiobuttonIcon(Rad);
                if (bBInfo)
		  addBegriffInfo(iPos,"Radiobutton."+TabBegriffe.getS(iPos,"Kennung"));
		//if (Img == null)
		  setSchrift2(iPos,Rad,null);
		if(!Static.Leer((String)TabBegriffe.getInhalt("HK",iPos)))
		  Rad.setMnemonic(TabBegriffe.getS(iPos,"HK").charAt(0));
                setTooltip(TabBegriffe.getM(iPos,"Tooltip"),Rad);
                Rad.setName("R"+TabBegriffe.getI(iPos,"Aic"));
                Rad.setEnabled(BerechtigungS(iPos));
                //if (Static.bMemo)
                //  Rad.setToolTipText(!TabBegriffe.getM(iPos,"Info").equals("")?TabBegriffe.getS(iPos,"Info"):TabBegriffe.getS(iPos,"Bezeichnung"));
		return Rad;
	}

	public JButton getButton(int iAic,int iAlign)
	{
		//printInfo("getButton("+iAic+")");
	  //TabBegriffe.posInhalt("Aic",iAic);
	  	int iPos=TabBegriffe.getPos("Aic",iAic);
		return iPos>=0 ? getButtonS(iPos,iAlign,true) : new JButton("Btn"+iAic);
	}

	public JButton getButton(String sKennung)
	{
	  JButton Btn=getButtonS(sKennung);
          if (sKennung.equals("...") || sKennung.equals("*"))
          {
        	int iFF=getFontFaktor();
            Btn.setPreferredSize(new java.awt.Dimension(12*iFF/100,12*iFF/100));
            Btn.setMargin(inset0);
          }
          return Btn;
          /*if (TabBegriffe==null)
            return new JButton(sKennung);
		//testInfo("getButton("+sKennung+")");
		getBegriff("Button",sKennung);
		return TabBegriffe.pos() ? getButton2(TabBegriffe.getPos(),0) : new JButton(sKennung);*/
	}

        public JButton getButton2(String sKennung)
        {
          return getButton2(sKennung,null,null,null,"Button");
        }

        public JButton getButton(String sKennung,String s,ActionListener AL)
        {
          JButton Btn=getButtonS(sKennung);
          setAction(Btn,s==null ? sKennung:s,AL);
          /*if (s != null)
            Btn.setActionCommand(s);
          if (AL != null)
            Btn.addActionListener(AL);*/
          return Btn;
        }

        public JButton BtnAdd(JButton Btn,String s,ActionListener AL)
        {
          if (Btn != null)
          {
            Btn.setActionCommand(s);
            Btn.addActionListener(AL);
          }
          return Btn;
        }

        public AUCheckBox CbxAdd(AUCheckBox Cbx,String s,ActionListener AL)
        {
          if (Cbx != null)
          {
            Cbx.setActionCommand(s);
            Cbx.addActionListener(AL);
          }
          return Cbx;
        }

        public JRadioButton RadAdd(JRadioButton Rad,String s,ActionListener AL)
        {
          if (Rad != null)
          {
            Rad.setActionCommand(s);
            Rad.addActionListener(AL);
          }
          return Rad;
        }

        public JButton getButton2(String sKennung,String s,ActionListener AL)
        {
          return getButton2(sKennung,null,s,AL,"Button");
        }

        public JButton getButton2(String sKennungNeu,String sKennung,String s,ActionListener AL,String sBG)
        {
          int iPos=getPosBegriff(sBG,sKennungNeu);
          if (iPos<0 && sKennung != null)
        	  iPos=getPosBegriff(sBG,sKennung);
          return getButton2(iPos,s,AL);
        }

        public JButton getButton2(int iPos,String s,ActionListener AL)
        {
          JButton Btn= iPos>=0 ? getButtonS(iPos,0,false) : new JButton("x"+s);
          //Btn.setMargin(inset0);
          Btn.setBorder(null);
          if (Btn.getText() != null && Btn.getIcon() != null)
          {
            setTooltip2(getBegBez2(iPos),Btn.getToolTipText(),Btn);
            Btn.setText(null);
            Btn.setOpaque(false);
            //Btn.setMargin(new Insets(0,2,0,2));
          }
          Btn.setOpaque(Static.bND);
          if (Static.bND)
        	  Btn.setBackground(ColToolbar);
          addHoverT(Btn,iPos);
          //Btn.setMaximumSize(new java.awt.Dimension(36,26));
          setAction(Btn,s,AL);
          /*if (s != null)
            Btn.setActionCommand(s);
          if (AL != null)
            Btn.addActionListener(AL);*/
          return Btn;
        }
        
        public void ColBack(JToolBar PnlTB)
        {
        	if (!Static.bND)
        		return;
        	Color Col=PnlTB.getBackground();
        	Component[] Items=PnlTB.getComponents();
        	for (int i=0;i<Items.length;i++)
        	{
        		Component Comp=Items[i];
        		if (Comp instanceof JComponent)
        			Comp.setBackground(Col);
        	}
        }

        public static void setAction(javax.swing.AbstractButton C,String s,ActionListener AL)
        {
          if (C==null)
            return;
          if (s != null)
            C.setActionCommand(s);
          if (AL != null)
            C.addActionListener(AL);
        }

        public JToggleButton getTButton(String sKennung,ActionListener AL)
        {
          return getTButton(sKennung,null,AL);
        }
        
        public ImageIcon invert(ImageIcon img,String sKennung) {
        	 
        	BufferedImage image = new BufferedImage(img.getIconWidth(), img.getIconHeight(), BufferedImage.TYPE_INT_ARGB); 
        	image.getGraphics().drawImage(img.getImage(), 0,0, img.getImageObserver());
        	int w = image.getWidth(), h = image.getHeight();
        	fixtestError("invert "+sKennung+": "+w+"x"+h);
        	BufferedImage bearbeiten = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        	int iColTB=ColToolbar.getRGB();
        	for (int x = 0; x < w; x++) {
        		for (int y = 0; y < h; y++) {
        			int iPixel=image.getRGB(x, y);
        			//int iNeu = 255 + 256*255 + 256*256*255 - iPixel; 
        			//int iNeu=(iPixel==-16777216 ? ColToolbar:Color.BLACK).getRGB();
        			//int iNeu=(iPixel==-16777216 ? Color.WHITE:ColToolbar).getRGB();
        			int iNeu=iPixel==0 ? iColTB:0xFFFFFF - (iPixel&0xFFFFFF) + 0xFF000000;
//        			if (iPixel==-16777216)
//        				iNeu=0;
//        			else
       				//fixtestError("  setze bit "+x+","+y+" auf "+Integer.toHexString(iNeu)+" von "+Integer.toHexString(iPixel));
        			bearbeiten.setRGB(x, y, iNeu);       			       			
        		}
        	}
        	return new ImageIcon(bearbeiten);
        }

        public JToggleButton getTButton(String sKennung,String sAct,ActionListener AL)
        {
          int iPos=getPosBegriff("Button",sKennung);
          JToggleButton Btn=null;
          if (iPos>=0)
          {
            ImageIcon Img = LoadImageIcon(iPos);
            if (Mal2() && Img != null)
            	Img = new ImageIcon(Img.getImage().getScaledInstance(Math.round(Img.getIconWidth()*2),Math.round(Img.getIconHeight()*2),Image.SCALE_AREA_AVERAGING));// .SCALE_SMOOTH));
            Btn=new JToggleButton(Img);
            Btn.setOpaque(Static.bND);           
            if (Static.bND)
            {
            	ImageIcon Img2 = LoadImageIcon(iPos,2);
            	if (Mal2() && Img2 != null)
            		Img2 = new ImageIcon(Img2.getImage().getScaledInstance(Math.round(Img2.getIconWidth()*2),Math.round(Img2.getIconHeight()*2),Image.SCALE_AREA_AVERAGING));
            	
            	Btn.setBackground(ColToolbar);
            	if (Img2 == null && Img != null)
            		Img2=invert(Img,sKennung);           
	            if (Img2 != null)
	            {
	            	Btn.setSelectedIcon(Img2);
	            	Btn.setPressedIcon(Img2);
	            }
            }
            addHoverT(Btn,iPos);
            Btn.setBorder(null);
            
            setTooltip2(getBegBez2(iPos),TabBegriffe.getS(iPos,"Tooltip"),Btn);
          }
          else
            Btn=new JToggleButton("x"+sKennung);
          setAction(Btn,sAct==null ? sKennung:sAct,AL);
          /*Btn.setActionCommand(sKennung);
          if (AL != null)
            Btn.addActionListener(AL);*/
          return Btn;
        }

        public void setTooltip2(String sB,String sTT,JComponent C)
        {
          String sBez= "<html><b>"+sB+"</b>";
          if (sTT != null && !sTT.equals(sB))
              sBez+="<p>"+toHtml(sTT,false);//sTT.replaceAll("\n","<p>");
          sBez+="</html>";
          C.setToolTipText(sBez);
        }

	public JButton getButtonS(String sKennung)
	{
          if (TabBegriffe==null)
            return new JButton(sKennung.startsWith("_") ? sKennung.substring(1):sKennung);
          //getBegriff("Button",sKennung);
          int iPos=getPosBegriff("Button",sKennung);
          JButton Btn= iPos>=0 ? getButtonS(iPos,0,false) : new JButton(sKennung);
          if (iPos<0 && !Static.bDefShow)
            Btn.setEnabled(false);
          return Btn;
	}

	public JButton getFrameS(String sKennung)
	{
		//printInfo("getButton("+sKennung+")");
		int iPos=getPosBegriff("Frame",sKennung);
		return iPos>=0 ? getButtonS(iPos,0,false) : new JButton(sKennung);
	}

	public boolean Lizenz(int iPos)
	{
		if (Static.bInfoTod)
			fixInfo("Lizenz von "+iPos+" bei All="+AllUnlimited()+", Prüfe="+Static.bLizenz+", Jeder="+isJederS(iPos)+", VecBegLizenz="+VecBegLizenz);
		if (!Static.bDefShow && VecNicht != null && VecNicht.contains(TabBegriffe.getInhalt("aic",iPos)))
		{
			fixInfo(" -> nicht "+TabBegriffe.getS("DefBezeichnung"));
			return false;
		}
        return AllUnlimited() || !Static.bLizenz || VecBegJeder.isEmpty()/* bei Druck alles enabled*/ || isJederS(iPos) || VecBegLizenz.contains(TabBegriffe.getInhalt("aic",iPos));
	}
	
	public boolean TitelBerechtigt(int iPos)
	{
		boolean b=Lizenz(iPos);
		if (b && !isJederS(iPos) && (TabBegriffe.getI(iPos,"bits")&cstBer)>0)
			return BerechtigungS(iPos);
		return b;
	}

        public boolean Privileg(int iPos,int iUser,int iBG)
        {
          if (iPos<0)
            return false;
          if (Static.bLizenz && Lizenz(iPos))
            return true;
          if (iUser<0)
            iUser=SQL.getInteger(this,"select aic_benutzer from abfrage where aic_begriff="+TabBegriffe.getI(iPos,"Aic"));
          if (iUser>0)
            return getBenutzer()==iUser;
          if (iBG<0)
            iBG=SQL.getInteger(this,"select aic_benutzergruppe from abfrage where aic_begriff="+TabBegriffe.getI(iPos,"Aic"));
          if (iBG==0 && Static.bLizenz)
            return false;
          if ((TabBegriffe.getL(iPos,"bits")&/*cstPriviligiert*/0x40000000000l)==0)
            return true;
          else
            return checkBG(iBG);
        }

        /*private boolean isJeder()
        {
          return isJeder(TabBegriffe.getInhalt("aic"));
        }*/

        public boolean isJederS(int iPos)
        {
          return isJeder(TabBegriffe.getInhalt("aic",iPos));
        }

        public boolean isJeder(Object IntAic)
        {
          return VecBegJeder.contains(IntAic);
        }

        public boolean isTod(Object IntAic)
        {
          return VecBegTod.contains(IntAic);
        }
        
        public boolean isWeb(Object IntAic)
        {
          return VecBegWeb.contains(IntAic);
        }

        public boolean EigIsTod(Object IntAic)
        {
          boolean b=VecEigTod.contains(IntAic);
          //if (b)
          //  fixtestInfo("Eig "+IntAic+" ist Tod");
          return b;
        }

        public void setJeder(Object IntAic,boolean b)
        {
          boolean b2=VecBegJeder.contains(IntAic);
          if (b!=b2)
            if (b)
              VecBegJeder.addElement((Integer)IntAic);
            else
              VecBegJeder.removeElement(IntAic);
        }

        public String getBegBez(int iAic)
        {
          //TabBegriffe.push();
          int iPos=TabBegriffe.getPos("Aic",iAic);
          String s=iPos>=0 ? getBegBez2(iPos):"< ??? >";
          //TabBegriffe.pop();
          return s;
        }
        
        public String getDefBez(int iAic)
        {
          int iPos=TabBegriffe.getPos("Aic",iAic);
          String s=iPos>=0 ? TabBegriffe.getS(iPos,"DefBezeichnung"):"Beg"+iAic;
          return s;
        }

        /*public String getBegBez()
        {
          return clearHtml(TabBegriffe.getS(Static.bDefBezeichnung ?"DefBezeichnung":"Bezeichnung"));
        }*/

        /*public String getBez(Tabellenspeicher Tab)
        {
          return Tab.getS(Tab.getPos(), Static.bDefBezeichnung || Tab.getS(Tab.getPos(), "Bezeichnung").equals("") ? "DefBezeichnung" : "Bezeichnung");
        }*/

        /*public String getBBez(int iPos)
        {
          //fixInfo("getBez "+Tab.getTitel()+"/"+Tab.getS(iPos,"Kennung")+":"+Tab+"/"+TabBegriffe);
          return TabBegriffe.getS(iPos,Static.bDefBezeichnung || TabBegriffe.getS(iPos,"Bezeichnung").equals("") ? "DefBezeichnung":"Bezeichnung");
        }*/

        public String getBegBez2(int iPos)
        {
          return clearHtml(getBegBez3(iPos));//TabBegriffe.getS(iPos,Static.bDefBezeichnung ?"DefBezeichnung":"Bezeichnung"));
        }

        public String getBegBez3(int iPos)
        {
          return iPos<0 ? "(not found)":TabBegriffe.getS(iPos,Static.bDefBezeichnung || TabBegriffe.getS(iPos,"Bezeichnung").equals("") ? "DefBezeichnung":"Bezeichnung");
        }
        
    private void addHover(JComponent Btn,int iPos)
    {
    	if (Static.bND)	
		{
			//fixtestError("Hover bei Button "+(iPos<0 ? ((JButton)Btn).getText():getBegBez2(iPos)));
			if (MA_ND==null)
				MA_ND=new java.awt.event.MouseAdapter() {
				    public void mouseEntered(java.awt.event.MouseEvent evt) {
				    	JComponent Btn=(JComponent)evt.getSource();
				    	if (Btn.isEnabled())
				    		Btn.setBackground(ColHover);
				    }

				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	JComponent Btn=(JComponent)evt.getSource();
				    	Btn.setBackground(ColBtn);
				    }
				};
			Btn.addMouseListener(MA_ND);
		}
    }
    
    public void removeHover(JButton Btn)
    {
    	Btn.removeMouseListener(MA_ND);
    }
    
    private void addHoverT(JComponent Btn,int iPos)
    {
    	if (Static.bND)	
		{
			//fixtestError("Hover bei Button "+(iPos<0 ? ((JButton)Btn).getText():getBegBez2(iPos)));
			if (MA_NDT==null)
				MA_NDT=new java.awt.event.MouseAdapter() {
				    public void mouseEntered(java.awt.event.MouseEvent evt) {
				    	JComponent Btn=(JComponent)evt.getSource();
				    	if (Btn.isEnabled())
				    		Btn.setBackground(ColHover);
				    }

				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	JComponent Btn=(JComponent)evt.getSource();
				    	Btn.setBackground(Btn.getParent().getBackground());// ColToolbar);
				    }
				};
			Btn.addMouseListener(MA_NDT);
		}
    }
    
    private int URL()
    {
    	if (iBG_URL==0)
    	{
    		iBG_URL=TabBegriffgruppen.getAic("Web");
//    		fixtestError("iBG_URL="+iBG_URL);
    	}
    	return iBG_URL;
    }

	public JButton getButtonS(int iPos,int iAlign,boolean bBInfo)
	{
	  if (iPos<0)
	    return new JButton("Btn ???");
		//printInfo("getButton()->"+TabBegriffe.getS("BildFile"));
                ImageIcon Img = TabBegriffe.getI(iPos,"Gruppe")==iBG_Druck && Static.Leer(TabBegriffe.getS(iPos,Static.sBild)) ? ImgDruck:LoadImageIcon(iPos);
                if (Mal2() && Img != null)
                	Img = new ImageIcon(Img.getImage().getScaledInstance(Math.round(Img.getIconWidth()*2),Math.round(Img.getIconHeight()*2),Image.SCALE_AREA_AVERAGING));// .SCALE_SMOOTH));
		JButton Btn = Img==null ? new JButton(getBegBez2(iPos)) :
			TabBegriffe.getB(iPos,"Combo") ? new JButton(getBegBez2(iPos),Img): new JButton(Img);
		addHover(Btn,iPos);
        Btn.setHorizontalAlignment(iAlign);
		if (Img == null || TabBegriffe.getB(iPos,"Combo"))
			setSchrift2(iPos,Btn,fontButton);
                Btn.setMargin(inset);
                Btn.setName("B"+TabBegriffe.getI(iPos,"Aic"));
                if (bBInfo)
                  addBegriffInfo(iPos,"Button."+TabBegriffe.getS(iPos,"Kennung"));
                //addBegriffInfo("Button."+TabBegriffe.getS(iPos,"Kennung"),false,true); // !!!
		//int iFarbe = TabBegriffe.getI("Farbe");
		//if (iFarbe != 0 && TabFarbe.posInhalt("Aic",new Integer(iFarbe)))
		//	Btn.setBackground((Color)TabFarbe.getInhalt("Farbe"));
                //JToolTip.setUI(TT);
                //javax.swing.plaf.metal.MetalToolTipUI.createUI(Btn);
                //TT.installUI(Btn);
                //TT.createUI(Btn);
                //TT.uninstallUI(Btn);

                if(TabBegriffe.getInhalt("HK",iPos) != null && TabBegriffe.getS(iPos,"HK").length()>0)
                {
                  //testInfo("Hotkey für "+TabBegriffe.getS("DefBezeichnung")+":"+TabBegriffe.getS("HK"));
                  Btn.setMnemonic(TabBegriffe.getS(iPos,"HK").charAt(0));
                  //Btn.setUI(BtnTT);
                }
                setTooltip(TabBegriffe.getM(iPos,"Tooltip"),Btn);
		//if (Static.bMemo)
		//	Btn.setToolTipText(!TabBegriffe.getM(iPos,"Info").equals("")?TabBegriffe.getS(iPos,"Info"):TabBegriffe.getS(iPos,"Bezeichnung"));
		//if (TabBegriffe.getI("Stt")==0)
		//boolean b=BerechtigungS(iPos);
		//if (!b)
		//  testInfo("false bei getButton2:"+iPos+"/"+getBegBez2(iPos));
		Btn.setEnabled(BerechtigungS(iPos));
                if(bInfoJeder && !isJederS(iPos))
                {
                    Btn.setForeground(ColEF_Bez2);
                    defInfo2("Nicht Jeder: " + getBegBez2(iPos));
                }
        final String sURL=TabBegriffe.getS(iPos,"URL");
        int iBG=TabBegriffe.getI(iPos,"Gruppe");
        if(sURL!=null && iBG==URL() && !sURL.trim().equals("") && !sURL.endsWith(".css"))
		{
        	fixtestError("URL dazu bei "+TabBegriffe.getS(iPos,"Kennung")+"/"+iBG+"->"+sURL);
                  Btn.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      //OpenURL(sURL);
                      //if (sURL.startsWith("help:"))
                      //  sURL= Static.HilfeVerzeichnis+sURL.substring(5);
                      if (Static.cURL=='i')
                      {
                        JFrame f = new JFrame();
                        f.setSize(700, 500);
                        f.add(new JScrollPane(new AUBrowser(sURL.startsWith("help:") ? Static.HilfeVerzeichnis + sURL.substring(5) : sURL /*"http://www.orf.at"*/)));
                        //f.pack();
                        f.setVisible(true);
                        Static.centerComponent(f, (JButton)ev.getSource());
                      }
                      else
                        OpenURL(sURL);
                    }
                  });
		}
		/*
		SQL Qry = new SQL(this);
		if(Qry.open("SELECT Titel,Memo FROM Daten_memo JOIN Tabellenname WHERE Kennung='Begriff' AND AIC_Sprache="+iSprache+" AND AIC_Fremd="+TabBegriffe.getS("AIC")))
		{
			if(!Qry.eof())
			{
				String s =Qry.getS("Titel");
				if(!s.equals(""))
				{
					s=s+" - "+Qry.getS("Memo");
					Btn.setToolTipText(s);
				}
			}
			Qry.close();
		}
		*/

		return Btn;
	}
	
//	public void setDefaultCss()
//	{
//		Static.sDefaultStyle=getCss(getPosBegriff("Show","Formular"));
//		fixtestInfo("   ***   DefaultStyle="+Static.sDefaultStyle);
//	}

//        private String getCss(int iPos)
//        {
//        	//fixtestError("getCss "+iPos+" bei Dir="+Static.DirCss);
//          if (iPos>=0 && !Static.Leer(Static.DirCss) && !TabBegriffe.getS(iPos,"URL").equals(""))
//          {
//        	  String s=Static.DirCss+TabBegriffe.getS(iPos,"URL");
//        	  fixtestInfo("Style für "+TabBegriffe.getS(iPos,"DefBezeichnung")+":"+s);
//            return s;
//          }
//          else
//            return null;
//        }

        public void OpenURL(String sURL)
        {
          if (sURL != null && !sURL.equals(""))
            Static.OpenURL(sURL);
        }

	/*public void setButton(String sKennung,JButton Btn)
	{
		Btn.setText(getBegriff("Button",sKennung));
		setSchrift(0,Btn);
	}*/

	/*public void putTabBegriffe(int iGruppe,int iAic,String sKennung,String sBezeichnung,String sDefBezeichnung,String sBildFile,int iSchrift,String sURL, int iTyp,int iStt,int iErf,String sInfo,long lBits,int iCombo,int iAnzahl,boolean bNeu)
	{
		putTabBegriffe(iGruppe,iAic,sKennung,sBezeichnung,sDefBezeichnung,sBildFile,iSchrift,sURL,iTyp,iStt,iErf,0,sInfo,lBits,iCombo,iAnzahl,null,false,null,bNeu);
	}

        public void putTabBegriffe(int iGruppe,int iAic,String sKennung,String sBezeichnung,String sDefBezeichnung,String sBildFile,int iSchrift,String sURL, int iTyp,int iStt,int iErf,int iRolle,String sInfo,long lBits,int iCombo,int iAnzahl,String sHK,boolean bTod,boolean bNeu)
	{
          putTabBegriffe(iGruppe,iAic,sKennung,sBezeichnung,sDefBezeichnung,sBildFile,iSchrift,sURL,iTyp,iStt,iErf,iRolle,sInfo,lBits,iCombo,iAnzahl,sHK,bTod,null,bNeu);
        }*/
        
//    public int putTabBegriffe(int iGruppe,int iAic,String sKennung,String sBezeichnung,String sDefBezeichnung,String sBildFile,int iSchrift,
//            String sURL, int iTyp, int iProg,int iStt,int iErf,int iRolle,long lBits,int iCombo,int iAnzahl,String sHK,
//            boolean bTod,String sTooltip,boolean bNeu)
//    {
//    	return putTabBegriffe(iGruppe,iAic,sKennung,sBezeichnung,sDefBezeichnung,sBildFile,iSchrift,sURL,iTyp,iProg,iStt,iErf,iRolle,lBits,iCombo,iAnzahl,sHK,bTod,sTooltip,null,null,null,null,bNeu);
//    }

	public int putTabBegriffe(int iGruppe,int iAic,String sKennung,String sBezeichnung,String sDefBezeichnung,String sBildSwing,int iSchrift,
                                   String sURL, int iTyp,int iProg,int iStt,int iErf,int iRolle,long lBits,boolean bCombo,boolean bWeb,int iAnzahl,String sHK,
                                   boolean bTod,String sTooltip,String sKennzeichen,String sAlias,String sBildFX,String sBildSel,boolean bNeu)
	{
          //progInfo(sKennung+"/"+iGruppe+":"+iCombo+"/"+bTod);
          //boolean b=Static.bTest;
          //Static.bTest=false;
                int iPos=-1;
		if (TabBegriffe.bInsert && bNeu)
                  iPos=TabBegriffe.getPos("Gruppe",iGruppe);
		iPos=TabBegriffe.getPos(iAic,sKennung,false,Static.beiLeer(sBezeichnung,sDefBezeichnung),bNeu,iPos);
		TabBegriffe.setInhalt(iPos,"Gruppe",iGruppe);
                TabBegriffe.setInhalt(iPos,"DefBezeichnung",sDefBezeichnung);
		TabBegriffe.setInhalt(iPos,"Schrift",iSchrift);
		if (bNeu || sBildSwing != null)
			TabBegriffe.setInhalt(iPos,"BildFile",sBildSwing);
		if (bNeu || sBildFX!= null)
			TabBegriffe.setInhalt(iPos,"BildFX",sBildFX);
		if (bNeu || sBildFX!= null)
			TabBegriffe.setInhalt(iPos,"BildSel",sBildSel);
		if (sURL!=null) TabBegriffe.setInhalt(iPos,"URL",sURL);
		if (iTyp>-1) TabBegriffe.setInhalt(iPos,"Typ",iTyp);
		if (iProg>-1) TabBegriffe.setInhalt(iPos,"Prog",iProg);
		if (iStt>-1) TabBegriffe.setInhalt(iPos,"Stt",iStt);
		if (iErf>-1) TabBegriffe.setInhalt(iPos,"Erf",iErf);
		if (iRolle>-1) TabBegriffe.setInhalt(iPos,"Rolle",iRolle);
        if (bNeu || sTooltip != null)
			TabBegriffe.setInhalt(iPos,"Tooltip",Static.beiLeer(sTooltip,null));
        TabBegriffe.setInhalt(iPos,"Kennzeichen",Static.beiLeer(sKennzeichen,null));
        TabBegriffe.setInhalt(iPos,"Alias",Static.beiLeer(sAlias,null));
        if (bNeu || lBits>-1)
            TabBegriffe.setInhalt(iPos,"bits",lBits<0 ?0:lBits);
        if (bNeu || sHK!=null && sHK.length()>0)
          TabBegriffe.setInhalt(iPos,"HK", sHK);
        TabBegriffe.setInhalt(iPos,"Combo", bCombo);//new Boolean(iCombo==1));
          //TabBegriffe.putInhalt("Anzahl", iAnzahl, bNeu);
        TabBegriffe.setInhalt(iPos,"Tod",bTod);// ? Boolean.TRUE:null);
        TabBegriffe.setInhalt(iPos,"Web",bWeb);
        return iPos;
	}

	/*public void putTabCodes(int iGruppe,int iAic,String sKennung,String sBezeichnung,boolean bNeu)
	{
		if (TabCodes.bInsert && bNeu)
			TabCodes.posInhalt("Gruppe",iGruppe);
		TabCodes.putInhalt(iAic,sKennung,sBezeichnung,bNeu);
		TabCodes.putInhalt("Gruppe",iGruppe,bNeu);
	}*/

        private void loadWM()
        {
          String[] sAryWochentage = {"So","Mo","Di","Mi","Do","Fr","Sa"};
          String[] s1=DFS.getMonths();
          String[] s2=DFS.getShortMonths();
          String[] s3=DFS.getWeekdays();
          String[] s4=DFS.getShortWeekdays();
          for(int i=0;i<12;i++)
          {
            String s=getBegriffS("Monat",""+(i+1));
            if (s.length()>2)
            {
              s1[i]=s;
              s2[i]=s.substring(0,3);
            }
          }
          for(int i=0;i<sAryWochentage.length;i++)
          {
            String s=getBegriffS("Wochentag", sAryWochentage[i]);
            if (s.length()>2)
            {
              //fixInfo("vorher :"+s3[i+1]+"/"+s4[i+1]);
              s3[i+1]=s;
              s4[i+1]=s.substring(0,2);
              //fixInfo("nachher:"+s3[i+1]+"/"+s4[i+1]);
            }
          }
          DateWOD.DFS.setMonths(s1);
          DateWOD.DFS.setShortMonths(s2);
          DateWOD.DFS.setWeekdays(s3);
          DateWOD.DFS.setShortWeekdays(s4);
          
          DFS.setMonths(s1);
          DFS.setShortMonths(s2);
          DFS.setWeekdays(s3);
          DFS.setShortWeekdays(s4);
        }

        public void LoadBegriffMemo()
        {
          TabBegriffM=new Tabellenspeicher(this,"select aic_begriff,defbezeichnung,(select count(*) from darstellung where aic_begriff=begriff.aic_begriff) Anzahl,(select ein from logging where aic_logging=begriff.aic_logging) Log"+
                                            ",(SELECT memo FROM Daten_memo AUI WHERE aic_tabellenname="+iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Sprache="+iSprache+") Memo,jeder from begriff",true);
          TabBegriffM.setTitel("TabBegriffM");
        }

        public Zeit getLog(int iAic)
        {
          if (TabBegriffM==null)
            return null;
          else
          {
            int iPos=TabBegriffM.getPos("aic_begriff",iAic);
            if (iPos<0)
              return null;
            else
              return TabBegriffM.isNull(iPos,"Log") ? null:new Zeit(TabBegriffM.getTimestamp(iPos,"Log"),"dd.MM.yyyy");
          }
        }

        public int getAnzahl(int iAic)
        {
          if (TabBegriffM==null)
            return -1;
          else
          {
            int iPos=TabBegriffM.getPos("aic_begriff",iAic);
            if (iPos<0)
              return -2;
            else
              return TabBegriffM.getI(iPos,"Anzahl");
          }
        }

        public void setJeder(int iAic,int iJeder)
        {
          if (TabBegriffM!=null)
          {
            int iPos=TabBegriffM.getPos("aic_begriff",iAic);
            if (iPos>=0)
              TabBegriffM.setInhalt(iPos,"Jeder",iJeder);
          }
        }

        public String getJeder(int iAic,boolean bX)
        {
          if (TabBegriffM==null)
        	  LoadBegriffMemo();
          
          {
            int iPos=TabBegriffM.getPos("aic_begriff",iAic);
            if (iPos<0)
              return Static.sLeer;
            else if (bX)
              return Static.JaNein2(TabBegriffM.getI(iPos,"Jeder")==1);
            else
              return Static.JaNein(TabBegriffM.getI(iPos,"Jeder")==1);
          }
        }

        public String getMemo(int iAic)
        {
          if (TabBegriffM==null)
            return "!!! noch nicht geladen !!!";
          else
          {
            int iPos=TabBegriffM.getPos("aic_begriff",iAic);
            if (iPos<0)
              return "!!! nicht gefunden !!!";
            else
              return TabBegriffM.getM(iPos,"Memo");
          }
        }

        public String getMemo1(int iAic)
        {
          if (TabBegriffM==null)
            LoadBegriffMemo();
          int iPos=TabBegriffM.getPos("aic_begriff",iAic);
            if (iPos<0)
              return "!!! nicht gefunden !!!";
            else
              return TabBegriffM.getS(iPos,"DefBezeichnung")+": "+(Def()? TabBegriffM.getI(iPos,"Anzahl")+"x, "+new Zeit(TabBegriffM.getTimestamp(iPos,"Log"),"dd.MM.yyyy"):"")+"\n"+TabBegriffM.getM(iPos,"Memo");
        }

        public void fillTabBegriffgruppen()
        {
          TabBegriffgruppen = new Tabellenspeicher(this,"SELECT Aic_Begriffgruppe Aic,Kennung"+AU_Bild2("Begriffgruppe",iSpSw)+" Filename"+AU_Bild2("Begriffgruppe",iSpFX)+" BildFX"+
                                                         AU_Bezeichnung("Begriffgruppe","")+" Bezeichnung,Code,Anweisung FROM Begriffgruppe",true,"Begriffgruppe"+iSprache);
          TabBegriffgruppen.checkBezeichnung();
          TabBegriffgruppen.setTitel("TabBegriffgruppen");
        }

        public void refreshCodes()
        {
          TabCodes = new Tabellenspeicher(this,"SELECT AIC_BEGRIFFGRUPPE GRUPPE,AIC_CODE AIC,KENNUNG"+AU_BezeichnungFo("Code")+" Bezeichnung FROM CODE ORDER BY Gruppe,aic_code",true,"Code"+iSprache);
          TabCodes.checkBezeichnung();
          TabCodes.bInsert = true;
          TabCodes.setTitel("TabCodes");
          printSI("Codes");
        }

	private void LoadBegriffe()
	{
          TabBegriffInfo.clearAll();
		// fillTabBegriffgruppen(); // verschoben auf ErmittleJeder
                //TabBegriffgruppen.showGrid("TabBegriffgruppen");
                //int iAbfrage= Static.bMemo ? 0:TabBegriffgruppen.getAic("Abfrage");
                //testInfo("Abfrage="+iAbfrage);
                printSI("Begriffgruppen");
		refreshCodes();
		long lClock3=Static.get_ms();

		TabBegriffe= new Tabellenspeicher(this,new String[] {"Gruppe","Aic","Kennung","Bezeichnung","DefBezeichnung","Schrift","BildFile","BildFX","BildSel","URL","Typ","Prog","Stt","Erf"/*,"Info"*/,"Tooltip","Kennzeichen","Alias","bits","Combo","Web"/*,"Anzahl"*/,"HK","Rolle","Tod"});
                TabBegriffe.setTitel("TabBegriffe");
                //TabBegriffe.bN=true;
		//SQL Qry = new SQL(this);
		//String s = "";
		iTabBegriff=TabTabellenname.getAic("BEGRIFF");
		//fixInfo("************** iTabBegriff="+iTabBegriff);
                String sSQL="";

                  //String sSprache="case when fixe_sprache=1 then 1 else "+iSprache+" end";

                  sSQL="SELECT AIC_CODE Typ,Prog, Aic_Begriffgruppe Gruppe,Aic_Begriff,aic_schrift,Kennung,DefBezeichnung,aic_stammtyp,aic_bewegungstyp,aic_rolle,bits,combo,web,HotKey,Tod,Homepage,Kennzeichen,Alias"+
			//",AIC_Homepage"+//(Static.bMemo ? ",(SELECT memo FROM Daten_memo AUI WHERE aic_tabellenname="+iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Sprache="+iSprache+") Memo":"")+
                        ","+isnull()+"(SELECT memo2 FROM tooltip AUI WHERE aic_tabellenname="+iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Sprache="+iSprache+
                        "),(SELECT memo2 FROM tooltip AUI WHERE aic_tabellenname="+iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Sprache="+iStdSprache+")) Memo2"+
			",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+iSpSw+") Bild"+
			",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+iSpFX+") BildFX"+
			",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Zustand="+iZSel+") BildSel"+
			AU_Bezeichnung1("Begriff","Begriff")+" Bezeichnung"+
//			","+isnull()+"(SELECT Bezeichnung FROM Bezeichnung AUB WHERE aic_tabellenname="+iTabBegriff+" AND AUB.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUB.AIC_Sprache="+iSprache+//sSprache+
//			"),(SELECT Bezeichnung FROM Bezeichnung AUI WHERE aic_tabellenname="+iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AIC_Sprache="+iSpStd+")) Bezeichnung"+
                        //(Static.bMemo ? ",(select count(*) from darstellung where aic_begriff=begriff.aic_begriff) Anzahl":"")+
                        " FROM Begriff order by gruppe,defBezeichnung";
                        /*(iAbfrage>0 ? " where begriff.Aic_Begriffgruppe<>"+iAbfrage:"")+*/
                Tabellenspeicher Tab=new Tabellenspeicher(this,sSQL,true,"Begriff"+iSprache+".AU0");
                //Tab.showGrid("Begriffe");
                //if (Qry.open(sSQL))
		//{
			printSI("Begriffe Open");
                        String s = "";
			for(;!Tab.eof();Tab.moveNext())
			{

				//String sBild=Qry.getB("fixe_sprache")?Qry.getS("Bild2"):Qry.getS("Filename");

				s=Static.beiLeer(Tab.getM("Bezeichnung"),Tab.getS("DefBezeichnung"));
				//if (Qry.getB("fixe_sprache"))
				//	s=Qry.getStringValue("Bez2");
				if (s.equals(""))
					s=Tab.getS("Kennung");

                  putTabBegriffe(Tab.getI("Gruppe"),Tab.getI("Aic_Begriff"),Tab.getS("Kennung"),s,Tab.getS("DefBezeichnung"),Tab.getM("Bild"),Tab.getI("aic_schrift"),Tab.getS("Homepage"),
                		  Tab.getI("Typ"),Tab.getI("Prog"),Tab.getI("aic_stammtyp"),Tab.getI("aic_bewegungstyp"),Tab.getI("aic_Rolle"),Tab.getL("Bits"),Tab.getB("combo"),Tab.getB("web"),/*Static.bMemo ? Tab.getI("Anzahl"):*/0,
                				  Tab.isNull("HotKey") ?null:Tab.getS("HotKey"),Tab.getB("Tod"),Tab.getM("Memo2"),Tab.getS("Kennzeichen"),Tab.getS("Alias"),Tab.getM("BildFX"),Tab.getM("BildSel"),true);

				//Qry.moveNext();
			}
			TabBegriffe.bInsert = true;
//			if (Static.sDefaultStyle==null) setDefaultCss(); //Static.sDefaultStyle=getCss(getPosBegriff("Show","Formular"));
			//fixtestInfo("DefaultStyle1="+Static.sDefaultStyle);
			//TabBegriffe.showGrid("TabBegriffe");
		//}
		//Qry.free();
                //TabBegriffe.showGrid("Begriffe");
                if (Static.get_ms()-lClock3>3000)
                {
                  fixInfo("Slow aktiviert");
                  Static.bSlow = true;
                  if (!Static.cache())
                  {
                    Static.bLocal=false;
                    if (Static.cache())
                    {
                      fixInfo("Cache aktiviert");
                      checkCache();
                    }
                  }
                }
		clockInfo("TabBegriffe füllen",lClock3);
                lClock3=Static.get_ms();
                //getBegriff("Button","Druck");
                //if (!TabBegriffe.out())
                  ImgDruck = LoadImageIcon(getPosBegriff("Button","Druck"));
                iBG_Druck=TabBegriffgruppen.getAic("Druck");
                clockInfo("Drucker-Bild laden",lClock3);
                loadWM();
		//iFomZR = getBegriffAic("Frame","Zeitraum");
		//iFomDruckConfig  = getBegriffAic("Frame","Druckeinstellungen");
                TabStatus=new Tabellenspeicher(this,new String[] {"Nr","Kennung","Bezeichnung"});
                TabStatus.sAIC="Nr";
                TabStatus.addInhalt("Nr",-1);TabStatus.addInhalt("Kennung","Spaltenfehler");
                TabStatus.addInhalt("Nr",0);TabStatus.addInhalt("Kennung","ok");
                TabStatus.addInhalt("Nr",1);TabStatus.addInhalt("Kennung","update_aktuell");
                TabStatus.addInhalt("Nr",2);TabStatus.addInhalt("Kennung","alle_ausloggen");
                TabStatus.addInhalt("Nr",3);TabStatus.addInhalt("Kennung","Timer_deaktiv");
                TabStatus.addInhalt("Nr",4);TabStatus.addInhalt("Kennung","Timer_close");
                TabStatus.addInhalt("Nr",5);TabStatus.addInhalt("Kennung","undefiniert");
                TabStatus.addInhalt("Nr",6);TabStatus.addInhalt("Kennung","einzeln_ausloggen");
                for (TabStatus.moveFirst();!TabStatus.out();TabStatus.moveNext())
                  TabStatus.addInhalt("Bezeichnung",getShow(TabStatus.getS("Kennung")));
                //TabStatus.showGrid();
		//if (bAlle)
		//	TabBegriffe.showGrid();
	}

	/*public int FomZR_Aic()
	{
		return iFomZR;
	}*/

	/*public int FomDC_Aic()
	{
		return iFomDruckConfig;
	}*/

	private void putTabTabellenname(int iAic,String sKennung,String sBezeichnung,boolean bFixe_Sprache,String sFilename,boolean bNeu)
	{
		int iPos=TabTabellenname.getPos(iAic,sKennung,true,sBezeichnung,bNeu,-1);
		//TabTabellenname.putInhalt("Virtuell",new Boolean(bVirtuell),bNeu);
		//TabTabellenname.putInhalt("UseSystem",new Boolean(bUseSystem),bNeu);
                TabTabellenname.setInhalt(iPos,"Fixe_Sprache",new Boolean(bFixe_Sprache));
                //printInfo("Lade Bild "+sFilename);
		TabTabellenname.setInhalt(iPos,"Bild",sFilename);//Static.LoadImage(sFilename,Static.DirImageSys),bNeu);
                //printInfo("Bild geladen");
	}

	private void LoadTabellennamen()
	{
          String sBez=AU_Bezeichnung("Tabellenname","");
          String sBild=AU_Bild2("Tabellenname",iSpSw);
          TabTabellenname= new Tabellenspeicher(this,new String[] {"Aic","Kennung","Bezeichnung","Fixe_Sprache","Bild"});
          //TabTabellenname.bN=true;
          TabTabellenname.setTitel("TabTabellenname");
          Tabellenspeicher Tab=new Tabellenspeicher(this,"SELECT aic_tabellenname,kennung,Fixe_Sprache"+sBez+" Bezeichnung"+sBild+" Filename FROM Tabellenname order by kennung"
                                                          ,true,"Tabellenname"+iSprache);
          while( !Tab.eof())
          {
                  putTabTabellenname(Tab.getI("AIC_Tabellenname"),Tab.getS("Kennung"),Tab.getS("Bezeichnung"),Tab.getB("Fixe_Sprache"),Tab.getS("Filename"),true);
                  Tab.moveNext();
          }
          //TabTabellenname.showGrid("Tab");
          iTabStamm=TabTabellenname.getAic("STAMM");
	}

        public int getForm(int iStammtyp)
        {
          int iPos=TabStammtypen.getPos("Aic",iStammtyp);
          return iPos>=0 ? TabStammtypen.getI(iPos,"Form"):0;
        }

        public void setForm(int iStammtyp,int iAic)
        {
          if (iStammtyp>0)
          {
            int iPos=TabStammtypen.getPos("Aic",iStammtyp);
            if (iPos>=0)
            {
              defInfo("Setze Stt "+iStammtyp+" auf Form "+iAic);
              TabStammtypen.setInhalt(iPos,"Form",iAic);
            }
          }
        }
        
    public int getSyncStamm(int iStammtyp)
    {
    	if (iStammtyp==0)
    		return 0;
    	fixtestError("getSyncStamm "+iStammtyp+": Rolle fehlt");
    	//new Exception().printStackTrace();
    	return getSyncStamm(iStammtyp,0);
    }

	public int getSyncStamm(int iStammtyp,int iRolle)
	{
		int iRet=0;
	  int iPos=TabStammtypen.getPos("Aic",iStammtyp);
	  if (iRolle>0)
      {
    	  int iPosR=TabRollen.getPos("Aic",iRolle);
    	  if (TabRollen.getI(iPosR,"Stt")==iStammtyp)
    		  iRet=TabRollen.getI(iPosR,"Sync");
      }
	  if (iRet==0 && iPos>=0) iRet=TabStammtypen.getI(iPos,"Sync");
	  if (bSyncInfo) fixInfo("getSyncStamm "+iStammtyp+"/"+iRolle+"="+iRet);
	  return iRet;
	}
	
	public int getSyncBew(int iBew)
	{
		int iPos=TabErfassungstypen.getPos("Aic", iBew);
        if (iPos>=0)
          return TabErfassungstypen.getI(iPos,"Pool");
        else
          return 0;
	}

        /*public void setSyncStamm(int iStammtyp,int iAic)
        {
          setSyncStamm(iStammtyp,iAic,false);
          //if (iStammtyp==iSttANR)
          //  BSt_ANR(iAic);
        }*/
	
	public void setSyncStamm(int iStammtyp,int iAic)
	{
		if (iStammtyp==0)
			return;
		fixtestInfo("setSyncStamm "+iStammtyp+": Rolle fehlt");
		setSyncStamm(iStammtyp,iAic,0,0);
	}
	
	public void setSyncStamm(int iStammtyp,int iAic,int iRolle)
	{
		setSyncStamm(iStammtyp,iAic,iRolle,0);
	}
	
	public void setSyncStamm(int iStammtyp,Vector Vec)
	{
		setSyncStamm(iStammtyp,Vec,0);
	}

	public void setSyncStamm(int iStammtyp,int iAic,int iRolle,int iVB)//,boolean bPruef)
	{
	  if (iStammtyp>0)
	  {
	    int iPos=TabStammtypen.getPos("Aic",iStammtyp);
	    if (iPos>=0)
	    {
	    	if (bSyncInfo) 
	    	{
	    		fixInfo("setSyncStamm "+iStammtyp+"/"+iRolle+":"+iAic);
//	    		if (iAic==5680)
//	    			new Exception().printStackTrace();
	    	}
	          //defInfo2("Setze Stt "+iStammtyp+" auf "+iAic);
              if (TabStammtypen.getI(iPos,"Sync")!=iAic)
              {
                String s="Setze Stt " + TabStammtypen.getS(iPos, "Kennung") + ": " + TabStammtypen.getI(iPos, "Sync") + "-> " + iAic+" ("+getStamm(iAic)+")";
                defInfo2(s);
                if (bSyncInfo) fixInfo("       ---> Sync:      "+s);
                //if (!bPruef || iAic == 0 || iStammtyp == SQL.getInteger(this, "select aic_stammtyp from stamm where aic_stamm=?", 0, "" + iAic))
                  TabStammtypen.setInhalt(iPos, "Sync", iAic);
              }
              if (iRolle>0)
              {
            	  int iPosR=TabRollen.getPos("Aic",iRolle);
            	  if (TabRollen.getI(iPosR,"Stt")==iStammtyp)
            	  {
            		  if (bSyncInfo) fixInfo("       ---> Sync Rolle "+TabRollen.getS(iPosR,"Bezeichnung"));
            		  TabRollen.setInhalt(iPosR,"Sync",iAic);
            	  }
              }
	    }
	  }
	}

        public void setSyncStamm(int iStammtyp,Vector Vec,int iVB)
	{
          int iPos=TabStammtypen.getPos("Aic",iStammtyp);
          if (iPos>=0)
          {
            String s="Setze Stt "+TabStammtypen.getS(iPos, "Kennung")+" auf "+Vec;
            defInfo2(s);
            if (bSyncInfo) fixInfo(s);
            TabStammtypen.setInhalt(iPos,"Vec",Vec);
          }
	}

        public void clearSync()
        {
        	if (bSyncInfo) fixInfo("       ---> Sync:      clearSync");
          for (int iPos=0;iPos<TabStammtypen.size();iPos++)
          {
            if (TabStammtypen.getI(iPos,"Sync")>0)
                  defInfo2("Setze Stt "+TabStammtypen.getS(iPos,"Kennung")+": "+TabStammtypen.getI(iPos,"Sync")+"-> null");
            TabStammtypen.setInhalt(iPos,"Sync",null);
            TabStammtypen.setInhalt(iPos,"Form",null);
            TabStammtypen.setInhalt(iPos,"Vec",null);
          }
          for (int iPos=0;iPos<TabErfassungstypen.size();iPos++)
            TabErfassungstypen.setInhalt(iPos,"Pool",null);
        }
        
//        public Vector getSyncVec(String sStt)
//        {
//          int iPos=TabStammtypen.getPos("Kennung",sStt);
//          if (iPos<0)
//        	  printError("Global.getSyncVec: "+sStt+" nicht gefunden");
//          return iPos>=0 ? (Vector)TabStammtypen.getInhalt("Vec",iPos):null;
//        }
        
        public int getJokerStt(int iStt,int iVB)
        {
        	Tabellenspeicher Tab=/*iVB==0 ? */TabStammtypen;//:getJokerStt(iVB);
        	int iPos=Tab.getPos("Aic",iStt);
        	return iPos<0 ? 0:Tab.getI(iPos,"Sync");
        }
        
        public Vector<Integer> getJokerVec(int iStt,int iVB)
        {
        	Tabellenspeicher Tab=/*iVB==0 ? */TabStammtypen;//:getJokerStt(iVB);
        	int iPos=Tab.getPos("Aic",iStt);
        	return iPos<0 ? null:(Vector<Integer>)Tab.getInhalt("Vec",iPos);
        }

        public Vector getSyncVec(int iStammtyp)
        {
          int iPos=TabStammtypen.getPos("Aic",iStammtyp);
            return iPos>=0 ? (Vector)TabStammtypen.getInhalt("Vec",iPos):null;
        }

        @SuppressWarnings("unchecked")
        public void addSyncStamm(int iStammtyp,int iAic,int iVB)
        {
          int iPos=TabStammtypen.getPos("Aic",iStammtyp);
          if (iPos>=0)
          {
            Vector<Integer> Vec=(Vector)TabStammtypen.getInhalt("Vec",iPos);
            if (Vec==null)
            {
              Vec=new Vector();
              TabStammtypen.setInhalt(iPos,"Vec",Vec);
            }
            if (iAic>0 && !Vec.contains(new Integer(iAic)))
              Vec.addElement(new Integer(iAic));
          }
        }

        public void addSyncVec(int iStammtyp,Vector<Integer> VecAic,int iVB)
        {
          fixtestError("addSyncVec "+iStammtyp+": "+VecAic);
          int iPos=TabStammtypen.getPos("Aic",iStammtyp);
          if (iPos>=0)
          {
            Vector<Integer> Vec=(Vector<Integer>)TabStammtypen.getInhalt("Vec",iPos);
            if (Vec==null)
            {
              Vec=new Vector<Integer>();
              TabStammtypen.setInhalt(iPos,"Vec",Vec);
            }
            for (int i=0;i<VecAic.size();i++)
            {
              int iAic=Sort.geti(VecAic, i);
              if (iAic>0 && !Vec.contains(new Integer(iAic)))
              {
                fixtestError(i+". addSyncVec zu Stt "+iStammtyp+": "+iAic);             
                Vec.addElement(new Integer(iAic));
              }
            }
          }
        }

	public boolean InHierarchie(int iVon,int iBis)
	{
		while(iVon != 0 && iBis != iVon)
		{
			int iPos=TabStammtypen.getPos("Aic",new Integer(iVon));
			iVon = TabStammtypen.getI(iPos,"Darunter");
		}
		return iBis==iVon;
	}
	
	public String getStammI(int iAic)
	{
		return iAic<=0 ? ""+iAic:getStamm(iAic)+" ("+iAic+")";
	}

        public String getStamm(int iAic)
        {
          return getStamm(iAic,false,false);
        }

        public String getStamm(int iAic,boolean bTranslate,boolean bKennung)
        {
        	if (TabStamm==null)
        		return "???";
        	if (iAic==0)
        		return "-";
          int iPos=TabStamm.getPos("Aic",iAic);
          if (iPos<0)
          {
            String s=null;
            String sKen=null;
            if (bTranslate)
              s=SQL.getString(this,"SELECT Bezeichnung FROM Bezeichnung WHERE AIC_Tabellenname="+iTabStamm+" AND AIC_Fremd=? AND AIC_Sprache="+iSprache,""+iAic);
            if (Static.Leer(s))
                s=SQL.getString(this, "select bezeichnung from stammview2 where aic_rolle is null and aic_stamm=?",""+iAic);
            if (Static.Leer(s))
                s=SQL.getString(this, "select bezeichnung from stamm_protokoll where aic_rolle is null and aic_stamm=?",""+iAic);
            if (bKennung)
              sKen=SQL.getString(this, "select kennung from stammview2 where aic_rolle is null and aic_stamm=?",""+iAic);
            if (Static.Leer(s))
            	s="Aic="+iAic;//"unbekannt";
            else
            {
	            iPos=TabStamm.newLine();
	            TabStamm.setInhalt(iPos,"Aic",iAic);
              if (bKennung)
                TabStamm.setInhalt(iPos,"Kennung",sKen);
	            TabStamm.setInhalt(iPos,"Bezeichnung",s);
	            TabStamm.setInhalt(iPos,"Timestamp",new Date());
	            TabStamm.setInhalt(iPos,"Anzahl",1);
	            fixtestInfo("getStamm "+iAic+" ------------ Stamm "+s+" geladen");
            }
            return bKennung ? sKen:s;
          }
          else
          {
            TabStamm.setInhalt(iPos,"Timestamp",new Date());
            TabStamm.setInhalt(iPos,"Anzahl",TabStamm.getI(iPos,"Anzahl")+1);
            if (bKennung)
            {
              String sKen=TabStamm.getS(iPos, "Kennung");
              if (Static.Leer(sKen))
              {
                fixtestError("Hole Kennung von "+TabStamm.getS(iPos, "Bezeichnung"));
                sKen=SQL.getString(this, "select kennung from stammview2 where aic_rolle is null and aic_stamm=?",""+iAic);
                TabStamm.setInhalt(iPos,"Kennung",sKen);
              }
              return sKen;
            }
            return TabStamm.getS(iPos, "Bezeichnung");
          }
        }

        public Tabellenspeicher refreshTab(Tabellenspeicher Tab,String s,String sTitel)
        {
          if (Tab==null)
          {
            Tab = new Tabellenspeicher(this, s, true);
            Tab.setTitel(sTitel);
          }
          else
            Tab.refresh(this,s,true,true,null);
          return Tab;
        }

        public int BSt_ANR(int iAic)
        {
          //long lClock2 = Static.get_µs();
          int iBSt=0;
          int iPosS=TabStamm.getPos("Aic",iAic);
          if (iPosS<0)
          {
            printError("ANR "+iAic+" nicht gefunden");
            return -1;
          }
          if (TabStamm.isNull(iPosS,"BSt"))
          {
            int iA=iAic;
            while (iA>0 && iBSt==0)
            {
              int iPos=TabH.getPos("aic_stamm",iA);
              iA=iPos<0 ? 0:TabH.getI(iPos,"sta_aic_stamm");
              iBSt=iA>0 && TabBSt.existInhalt("aic_stamm",iA) ? iA:0;
            }
            //if (iBSt>0)
              TabStamm.setInhalt(iPosS,"BSt",iBSt);
          }
          else
            iBSt=TabStamm.getI(iPosS,"BSt");
          int iPosB=iBSt>0 ? TabBSt.getPos("aic_stamm",iBSt):-1;
          //clockµInfo("BSt_ANR:"+iAic+"->"+iBSt,lClock2);
          //if (iPosB>=0)
          //  fixInfo("BSt "+TabBSt.getS(iPosB,"bezeichnung")+": Land="+TabBSt.getI(iPosB,"land")+", Region="+TabBSt.getI(iPosB,"Region")+", Rel="+TabStamm.getI(iPosS,"Religion"));
          return iPosB;
        }

        public void LoadANR_BSt()
        {
          long lClock2 = Static.get_ms();
          testInfo("LoadANR_BSt: Stt="+iSttANR+"/"+iSttBSt+", Eig="+iEigReligion+"/"+iEigRegion+"/"+iEigLand);
          TabStamm=refreshTab(TabStamm,"select s.aic_stamm Aic,Bezeichnung,null BSt,null Timestamp,null Anzahl,(select p.sta_aic_stamm from poolview2 p where p.aic_eigenschaft="+iEigReligion+
                                         " and p.aic_stamm =s.aic_stamm) Religion,kennung from stammview2 s where aic_rolle is null and aic_stammtyp="+iSttANR,"TabStamm");
          TabBSt=refreshTab(TabBSt,"select s.aic_stamm,bezeichnung,p.sta_aic_stamm Region,p2.sta_aic_stamm Land from stammview2 s join poolview2 p on p.aic_eigenschaft="+iEigRegion+
                                       " and p.aic_stamm =s.aic_stamm join poolview2 p2 on p2.aic_eigenschaft="+iEigLand+" and p2.aic_stamm =p.sta_aic_stamm where aic_stammtyp in ("+iSttBSt+","+iSttFirma+")","TabBSt");
          /*if (TabStamm==null)
            TabStamm= new Tabellenspeicher(this,"select s.aic_stamm Aic,Bezeichnung,null Timestamp,null Anzahl,(select p.sta_aic_stamm from poolview2 p where p.aic_eigenschaft="+iEigReligion+
                                         " and p.aic_stamm =s.aic_stamm) Religion from stammview2 s where aic_stammtyp="+iSttANR,true);
          else
            TabStamm.refresh();
          if (TabBSt==null)
            TabBSt= new Tabellenspeicher(this,"select s.aic_stamm,bezeichnung,p.sta_aic_stamm Region,p2.sta_aic_stamm Land from stammview2 s join poolview2 p on p.aic_eigenschaft="+iEigRegion+
                                       " and p.aic_stamm =s.aic_stamm join poolview2 p2 on p2.aic_eigenschaft="+iEigLand+" and p2.aic_stamm =p.sta_aic_stamm where aic_stammtyp="+iSttBSt,true);
          else
            TabBSt.refresh();*/
          TabH=refreshTab(TabH,"select aic_stamm,sta_aic_stamm from poolview2 where aic_eigenschaft="+iEigFirma,"TabH");
          clockInfo("LoadANR_BSt",lClock2);
        }

	private void LoadStammBild()
	{
          //TabStamm= new Tabellenspeicher(this,new String[] {"Aic","Bezeichnung","Timestamp","Anzahl"});
          //TabStamm.setTitel("TabStamm");
		TabStammBild= new Tabellenspeicher(this,new String[] {"Aic","Kennung","Bezeichnung","Bild"});
                TabStammBild.setTitel("TabStammBild");
		//SQL Qry = new SQL(this);
                Tabellenspeicher Tab=new Tabellenspeicher(this,"select aic_stamm,aic_stammtyp,s.kennung,bezeichnung,filename from daten_bild join tabellenname t on daten_bild.aic_tabellenname=t.aic_tabellenname join stammview2 s on aic_fremd=aic_stamm where t.kennung='Stamm'",true,"StammBild");
		//if(Qry.open("select aic_stamm,aic_stammtyp,s.kennung,bezeichnung,filename from daten_bild join tabellenname t on daten_bild.aic_tabellenname=t.aic_tabellenname join stammview2 s on aic_fremd=aic_stamm where t.kennung='Stamm'"))
		for(;!Tab.eof();Tab.moveNext())
                {
			//while( !Qry.eof())
			//{
                          String sKennung=Tab.getS("Kennung").toUpperCase();
                          int iPos=TabStammBild.getPos(Tab.getI("AIC_Stamm"),sKennung,false,Tab.getS("Bezeichnung"),true,-1);
				TabStammBild.setInhalt(iPos,"Bild",Tab.getS("Filename"));//LoadImage(Tab.getS("Filename")),true);
                                if (iSttLight==Tab.getI("aic_stammtyp"))
                                {
                                  if (sKennung.equals("RED"))
                                    iLight_Red=Tab.getI("AIC_Stamm");
                                  else if (sKennung.equals("YELLOW"))
                                    iLight_Yellow=Tab.getI("AIC_Stamm");
                                  else if (sKennung.equals("GREEN"))
                                    iLight_Green=Tab.getI("AIC_Stamm");
                                  else if (sKennung.equals("OFF"))
                                    iLight_Off=Tab.getI("AIC_Stamm");
                                }
				//Qry.moveNext();
			//}
		}
                testInfo("Light="+iLight_Red+"/"+iLight_Yellow+"/"+iLight_Green+"/"+iLight_Off);
		//Qry.free();
                //TabImages.showGrid("Images");
	}

	public void putTabStammtyp(int iAic,String sKennung,String sBezeichnung,String sFilename,String sFileFX,int iDarunter,int iBits,int iANR_Eig,boolean bNeu)
	{
          //boolean b=Static.bTest;
          //Static.bTest=false;
        int iPos=TabStammtypen.getPos(iAic,sKennung,true,sBezeichnung,bNeu,-1);
        TabStammtypen.setInhalt(iPos,"Bild",sFilename);//Static.LoadImage(sFilename,Static.DirImageSys),bNeu);
        TabStammtypen.setInhalt(iPos,"BildFX",sFileFX);
		TabStammtypen.setInhalt(iPos,"Darunter",iDarunter);
		TabStammtypen.setInhalt(iPos,"bits",iBits);
        TabStammtypen.setInhalt(iPos,"ANR_Eig",iANR_Eig);
		TabStammtypen.setInhalt(iPos,"Anzahl",(iBits&cstLizenz)>0?0:-1);
		TabStammtypen.setInhalt(iPos,"Sync",null);
        TabStammtypen.setInhalt(iPos,"Form",null);
        TabStammtypen.setInhalt(iPos,"Vec",null);
//        TabStammtypen.setInhalt(iPos,"Top",null);
//        TabStammtypen.setInhalt(iPos,"aktiv",null);
		//TabStammtypen.putInhalt("Ende",new Boolean(bEnde),bNeu);
		//TabStammtypen.putInhalt("translate",new Boolean(bTranslate),bNeu);
          //Static.bTest=b;
	}

        public void putTabRolle(int iAic,String sKennung,String sBezeichnung,String sFilename,int iStt,int iDavor,boolean bNeu)
        {
          int iPos=TabRollen.getPos(iAic,sKennung,true,sBezeichnung,bNeu,-1);
          TabRollen.setInhalt(iPos,"Bild",sFilename);//Static.LoadImage(sFilename,Static.DirImageSys),bNeu);
          TabRollen.setInhalt(iPos,"Stt",iStt);
          TabRollen.setInhalt(iPos,"Davor",iDavor);
          int iPosStt=TabStammtypen.getPos("Aic",iStt);
          TabRollen.setInhalt(iPos,"Anzahl",iPosStt>=0 && (TabStammtypen.getI(iPosStt,"bits")&cstLizenz)==0 ? -1:0);
          TabRollen.setInhalt(iPos,"Sync",null);
          TabRollen.setInhalt(iPos,"Form",null);
          TabRollen.setInhalt(iPos,"Vec",null);
          //TabRollen.showGrid();
        }

        public boolean Translate(int iStt)
        {
          if (iStt==0)
            return false;
          //TabStammtypen.push();
          int iPos=TabStammtypen.getPos("Aic",iStt);
          boolean b=iPos>=0 ? (TabStammtypen.getI(iPos,"bits")&cstTranslate)>0:false;
          //TabStammtypen.pop();
          return b;
        }

	private void LoadStammtypen()
	{
	  	long lClock2 = Static.get_ms();
	  	if (bSyncInfo) fixInfo("       ---> Sync:      clear TabStammtypen");
		TabStammtypen= new Tabellenspeicher(this,new String[] {"Aic","Kennung","Bezeichnung","Bild","BildFX","Darunter","bits","Anzahl","Sync","Form","Vec","ANR_Eig"/*,"Top","aktiv"*/});
		//TabStammtypen.bN=true;
                TabStammtypen.setTitel("TabStammtypen");
                VecSttBed=new Vector<Integer>();
		//SQL Qry = new SQL(this);
                iTabStt=TabTabellenname.getAic("STAMMTYP");
                Tabellenspeicher Tab=new Tabellenspeicher(this,"SELECT Stammtyp.*"+
                		(Version.V18() ? iSprache2==0 ? AU_Bezeichnung("Stammtyp"):
                			","+isnull()+"(SELECT Bezeichnung FROM Bezeichnung AUB WHERE aic_tabellenname="+iTabStt+" AND AUB.AIC_Fremd=Stammtyp.AIC_Stammtyp AND AUB.AIC_Sprache="+iSprache+
                			"),(SELECT Bezeichnung FROM Bezeichnung AUB WHERE aic_tabellenname="+iTabStt+" AND AUB.AIC_Fremd=Stammtyp.AIC_Stammtyp AND AUB.AIC_Sprache="+iSprache2+")) Bezeichnung":
                  ","+isnull()+"(SELECT Bezeichnung FROM Bezeichnung AUB WHERE aic_tabellenname="+iTabStt+" AND AUB.AIC_Fremd=Stammtyp.AIC_Stammtyp AND AUB.AIC_Sprache="+iSprache+
                  "),(SELECT Bezeichnung FROM Sprache join Bezeichnung AUI on Sprache.aic_Sprache=aui.aic_Sprache WHERE aic_tabellenname="+iTabStt+" AND AUI.AIC_Fremd=Stammtyp.AIC_Stammtyp AND Sprache.Standard=1)) Bezeichnung")+
                  AU_Bild2("Stammtyp",iSpSw)+" Filename"+AU_Bild2("Stammtyp",iSpFX)+" FileFX FROM Stammtyp where tod is null",true,"Stammtyp"+iSprache);
		//if(Qry.open("SELECT Stammtyp.*"+AU_Bezeichnung("Stammtyp")+AU_Bild("Stammtyp",true)+" Filename FROM Stammtyp"))
		//{
			for( ;!Tab.eof();Tab.moveNext())
			{
				String sKennung=Tab.getS("Kennung");
				int iAicSTT = Tab.getI("AIC_Stammtyp");
				/*if (sKennung.equals("Einheit"))
					iAic[Einheit] = iAicSTT;
				else*/ if (sKennung.equals("Waehrung"))
					iSttWaehrung = iAicSTT;
                                else if (sKennung.equals("Status"))
                                  iSttLight = iAicSTT;
                                else if (sKennung.equals("Periode"))
                                  iSttPeriode=iAicSTT;
                                else if (sKennung.equals("Arbeitnehmer"))
                                  iSttANR=iAicSTT;
                                else if (sKennung.equals("BSt"))
                                  iSttBSt=iAicSTT;
                                else if (sKennung.equals("Firma"))
                                  iSttFirma=iAicSTT;
//                                else if (sKennung.equals("Zeitzone"))
//                                  iSttZone=iAicSTT;
                                if ((Tab.getI("SttBits")&cstSttBed)>0)
                                  VecSttBed.addElement(iAicSTT);
				putTabStammtyp(iAicSTT,sKennung,Tab.getS("Bezeichnung"),Tab.getS("Filename"),Tab.getS("FileFX"),Tab.getI("Sta_aic_stammtyp"),Tab.getI("SttBits"),Tab.getI("ANR_Eig"),true);
				//(Tab.getB("Ende")?cstEnde:0)+(Tab.getB("translate")?cstTranslate:0)+(Tab.getB("Benutzer")?cstBenutzer:0)+(Tab.getB("Einheit")?cstEinheit:0)+
				//(Tab.getB("Lizenz")?cstLizenz:0)+(Tab.getB("Copy")?cstCopy:0)+(Tab.getB("Std_Formular")?cstStdFormular:0)+(Tab.getB("KEIN_STICHTAG")?cstKeinStichtag:0),true);
				//Qry.moveNext();
			}
		//}
		//Qry.free();
			/*if (TabStammtypen.posInhalt("Kennung","PERIODE"))
			  iSttPeriode=TabStammtypen.getI("AIC");
			else if (TabStammtypen.posInhalt("Kennung","ARBEITNEHMER"))
			  iSttANR=TabStammtypen.getI("AIC");
                        else if (TabStammtypen.posInhalt("Kennung","FIRMA"))
			  iSttFirma=TabStammtypen.getI("AIC");*/
                //fixInfo(iSttWaehrung+"/"+iSttLight+"/"+iSttPeriode+"/"+iSttANR+"/"+iSttFirma);
                fixtestInfo("VecSttBed="+VecSttBed);
		clockInfo("LoadStammtypen",lClock2);
	}
	
	public void clearZone()
	{
//		fixtestError("clear Zone");
		if (TabZonen!=null)
		{
			TabZonen.clearAll();
			TabZonen=null;
		}
	}
	
	public boolean checkZone()
	{
		if (iSttZone==0 || iEigZoneMin==0)
		{
			printError("Stt oder Eig für Zone nicht gefunden");
			return false;
		}
		if (TabZonen==null)
		{
//			fixtestError("refresh TabZonen");
			TabZonen=new Tabellenspeicher(this,"select aic_stamm,kennung,bezeichnung,(select spalte_double from poolview2 where aic_eigenschaft="+iEigZoneMin+" and aic_stamm=s.aic_stamm) zone from stammview2 s where aic_stammtyp="+iSttZone,true);
			for (int i=0; i< TabZonen.size();i++)
			  TabZonen.setInhalt(i, "Zone",TabZonen.getI(i,"Zone"));
		}
//		TabZonen.showGrid();
		return TabZonen!=null && TabZonen.size()>0;
	}
	
	public String getZone(int iAic)
	{
//		fixtestError("getZone von "+iAic+"( iSttZone="+iSttZone+", iEigZoneMin="+iEigZoneMin+")");
		if (checkZone())
		{
			int iPos=TabZonen.getPos("aic_stamm",iAic);
			String sK= iPos>=0 && !TabZonen.isNull(iPos, "Kennung") ? TabZonen.getS(iPos, "Kennung"):null;
//			fixtestError("getZone "+iAic+" -> "+sK+" auf Pos="+iPos);
			return sK;
		}
		else
			return "unbekannt";
	}
	
	public int getZoneAic()
	{
		if (checkZone())
		{
			int iPos=TabZonen.getPos("Zone",getZone2());
			fixtestError("getZoneAic: iPos="+iPos+" bei "+getZone2());
			return iPos<0 ? 0:TabZonen.getI(iPos,"aic_stamm");
		}
		else
			return -1;
	}

        public String getVB(int iBew,int iEig)
        {
          if (iBew>0)
          {
            int iPos = TabErfassungstypen.getPos("aic", iBew);
            if (iPos<0)
              return "keinBew";
            int iANR2 = 0;
            for(int i = 0; i < iANRs && iANR2 == 0; i++)
                if(TabErfassungstypen.getI(iPos, "Eig" + (i + 1)) == iEig)
                  iANR2 = i + 1;
            if (iANR2 == 0)
            {
              for (int i=2;i<4;i++)
                  if (TabErfassungstypen.getI(iPos,"Datum_Eig"+i)==iEig)
                    return "LDATE"+i;
              for (int i=1;i<3;i++)
                  if (TabErfassungstypen.getI(iPos,"Bool_Eig"+i)==iEig)
                    return "BOOL"+i;
              if (TabErfassungstypen.getI(iPos,"VB_Eig")==iEig)
				return "VB";
              String sDt=TabEigenschaften.getS(TabEigenschaften.getPos("aic", iEig),"Datentyp");
              return sDt.equals("BewDatum") ? "LDATE" : sDt.equals("Protokoll") ? "AIC_PROTOKOLL" : sDt.equals("Mandant") ? "AIC_MANDANT":"Hugo";
            }
            else
              return iANR2 > 1 ? "ANR" + iANR2 : "ANR";
          }
          else
          {
            String sDt=TabEigenschaften.getS(TabEigenschaften.getPos("aic", iEig),"Datentyp");
            return sDt.equals("Gruppe") ? "ANR":sDt.equals("EinAustritt") ? "Eintritt":sDt;
          }
        }

        public int getEigANR(int iBew,String sANR)
        {
          if (sANR==null)
            return 0;
          else
          {
            int iPos=TabErfassungstypen.getPos("Aic", iBew);
            if (iPos<0)
              return 0;
            else
              return TabErfassungstypen.getI(iPos,"Eig"+(sANR.equals("ANR") ? "1":sANR.substring(3)));
          }
        }

        public boolean isANR(int iBew,int iEig)
        {
          /*if ((iInfos&NO_SPEED)>0)
          {
            iANR=0;
            return false;
          }*/
          //TabErfassungstypen.push();
          int iPos=TabErfassungstypen.getPos("aic",iBew);
          int iANR=0;
          if (iPos>=0)
            for (int i=0;i<iANRs && iANR==0;i++)
              if (TabErfassungstypen.getI(iPos,"Eig"+(i+1))==iEig)
                iANR=i+1;
          //iANR=iPos<0 ? 0:TabErfassungstypen.getI(iPos,"Eig1")==iEig ? 1:TabErfassungstypen.getI(iPos,"Eig2")==iEig ? 2:TabErfassungstypen.getI(iPos,"Eig3")==iEig ? 3:0;
          //testInfo("isANR bei "+TabErfassungstypen.getS("Bezeichnung")+"/"+iEig+"="+iANR);
          //TabErfassungstypen.pop();
          return iANR>0;
        }

        public boolean isANR_Stt(int iStt,int iEig)
        {
          int iPos=TabStammtypen.getPos("aic",iStt);
          return iPos>=0 && TabStammtypen.getI(iPos,"ANR_Eig")==iEig;
        }

        public String getLDATE2(int iBew,int iEig)
        {
          int iPos=TabErfassungstypen.getPos("aic",iBew);
          int iLDATE=0;
          if (iPos>=0)
           for (int i=2;i<4;i++)
            if (TabErfassungstypen.getI(iPos,"Datum_Eig"+i)==iEig)
              iLDATE=i;
          return iLDATE>0 ? "LDATE"+iLDATE:null;
        }

        public boolean isLDATE(int iBew,int iEig)
        {
          return getLDATE2(iBew,iEig)!=null;
        }
        
        public boolean isVB(int iBew,int iEig)
        {
        	int iPos=TabErfassungstypen.getPos("aic",iBew);
        	return iPos>=0 && TabErfassungstypen.getI(iPos,"VB_Eig")==iEig;
        }

        public String getBOOL(int iBew,int iEig)
        {
          int iPos=TabErfassungstypen.getPos("aic",iBew);
          int iBOOL=0;
          if (iPos>=0)
           for (int i=1;i<3;i++)
            if (TabErfassungstypen.getI(iPos,"Bool_Eig"+i)==iEig)
              iBOOL=i;
          return iBOOL>0 ? "BOOL"+iBOOL:null;
        }

        public boolean isBOOL(int iBew,int iEig)
        {
          return getBOOL(iBew,iEig)!=null;
        }

        public String getANRS(int iBew,int iEig,int iBeg/*boolean bZwingend*/)
        {
          int iPos=TabErfassungstypen.getPos("aic",iBew);
          int iANR2 = 0;
          if(iPos >= 0)
            for(int i = 0; i < iANRs && iANR2 == 0; i++)
              if(TabErfassungstypen.getI(iPos, "Eig" + (i + 1)) == iEig)
                iANR2 = i + 1;
          //progInfo("getANRS("+iBew+"/"+iEig+")="+iANR2);
          if (iBeg>0 && iANR2==0)
          {
        	  String s="ANR für Bew "+TabErfassungstypen.getKennung(iBew)+" von Eig "+TabEigenschaften.getKennung(iEig)+" nicht gefunden";
        	  printError(s,iBeg,!Def());
        	  if (Def() && Static.bX11)
        		  JOptionPane.showMessageDialog(new JFrame(), s, "Abfrage "+getBegBez(iBeg),JOptionPane.WARNING_MESSAGE);
        	  if (Static.bInfoExcept)
        		printStackTrace(new Exception());
          }
          return iANR2 == 0 ? null : iANR2 > 1 ? "ANR" + iANR2 : "ANR";
        }

        public int getSttANR(int iBew)
        {
          int iPos=TabErfassungstypen.getPos("Aic",iBew);
          if (iPos<0)
          {
            printError("Global.getSttAN: Bew " + iBew + " nicht gefunden");
            return -1;
          }
          else
          {
            iPos=TabEigenschaften.getPos("aic",TabErfassungstypen.getI(iPos,"Eig1"));
            return TabEigenschaften.getI(iPos,"aic_stammtyp");
          }
        }

        public String getANR_BS(int iBew,int iStt)
        {
          //if ((iInfos&NO_SPEED)>0)
          //  return null;
          String s=null;
          //TabErfassungstypen.push();
          //TabEigenschaften.push();
          int iPosB=TabErfassungstypen.getPos("aic",iBew);
          //testInfo("getANR:"+iBew+"/"+iStt+"="+TabErfassungstypen.getI("Eig1")+"/"+TabErfassungstypen.getI("Eig2"));
          if (iPosB<0)
          {
        	  printError("Global.getANR_BS: Bew "+iBew+" nicht gefunden");
        	  return null;
          }
          s=null;
          for (int i=0;i<iANRs && s==null;i++)
          {
            int iPosE=TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPosB, "Eig" + (i + 1)));
            if (iStt<0)
            {
            	if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic") == -iStt)
                    s = "ANR" + (i == 0 ? "" : "" + (i + 1));
            }
            else if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
              s = "ANR" + (i == 0 ? "" : "" + (i + 1));
          }
          /*s=TabEigenschaften.posInhalt("aic",TabErfassungstypen.getI("Eig1")) && TabEigenschaften.getI("aic_stammtyp")==iStt ? "ANR":
            TabEigenschaften.posInhalt("aic",TabErfassungstypen.getI("Eig2")) && TabEigenschaften.getI("aic_stammtyp")==iStt ? "ANR2":
            TabEigenschaften.posInhalt("aic",TabErfassungstypen.getI("Eig3")) && TabEigenschaften.getI("aic_stammtyp")==iStt ? "ANR3":null;//"ANR_Fehler";*/
          //TabEigenschaften.pop();
          //TabErfassungstypen.pop();
          progInfo("getANR_BS("+iBew+","+iStt+")="+s);
          return s;
        }

        public String getANRc(int iPos,int iStt)
        {
          int iBits=TabErfassungstypen.getI(iPos,"bits");
          int iPosE=(iBits&cstANR1)==0 ? -1:TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPos, "Eig1"));
          if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
            return "ANR";
          iPosE=(iBits&cstANR2)==0 ? -1:TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPos, "Eig2"));
          if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
            return "ANR2";
          iPosE=(iBits&cstANR3)==0 ? -1:TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPos, "Eig3"));
          if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
            return "ANR3";
          iPosE=(iBits&cstANR4)==0 ? -1:TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPos, "Eig4"));
          if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
            return "ANR4";
          iPosE=(iBits&cstANR5)==0 ? -1:TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPos, "Eig5"));
          if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
            return "ANR5";
          iPosE=(iBits&cstANR6)==0 ? -1:TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPos, "Eig6"));
          if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
            return "ANR6";
          iPosE=(iBits&cstANR7)==0 ? -1:TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPos, "Eig7"));
          if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
            return "ANR7";
          iPosE=(iBits&cstANR8)==0 ? -1:TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPos, "Eig8"));
          if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
            return "ANR8";
          iPosE=(iBits&cstANR9)==0 ? -1:TabEigenschaften.getPos("aic", TabErfassungstypen.getI(iPos, "Eig9"));
          if (iPosE>=0 && TabEigenschaften.getI(iPosE,"aic_stammtyp") == iStt)
            return "ANR9";
          return null;
        }

        private void LoadRollen()
        {
          TabRollen= new Tabellenspeicher(this,new String[] {"Aic","Kennung","Bezeichnung","Bild","Stt","Davor","Anzahl","Sync","Vec","Form"});
          TabRollen.setTitel("TabRollen");
          //int iTabRolle=TabTabellenname.getAic("ROLLE");
          Tabellenspeicher Tab=new Tabellenspeicher(this,"SELECT Rolle.*"+AU_Bezeichnung1("Rolle","Rolle")+" Bezeichnung"+
//            ","+isnull()+"(SELECT Bezeichnung FROM Bezeichnung AUB WHERE aic_tabellenname="+iTabRolle+" AND AUB.AIC_Fremd=Rolle.AIC_Rolle AND AUB.AIC_Sprache="+iSprache+
//            "),(SELECT Bezeichnung FROM Sprache join Bezeichnung AUI on Sprache.aic_Sprache=aui.aic_Sprache WHERE aic_tabellenname="+iTabRolle+" AND AUI.AIC_Fremd=Rolle.AIC_Rolle AND Sprache.Standard=1)) Bezeichnung"+
            AU_Bild2("ROLLE",iSpSw)+" filename FROM Rolle where tod is null",true,"Rolle"+iSprache);
          for( ;!Tab.eof();Tab.moveNext())
          {
            if (Tab.getS("Kennung").equals("Mitarbeiter"))
              iRolMA = Tab.getI("AIC_Rolle");
            putTabRolle(Tab.getI("AIC_Rolle"),Tab.getS("Kennung"),Tab.getS("Bezeichnung"),Tab.getS("Filename"),Tab.getI("aic_stammtyp"),Tab.getI("Rol_aic_Rolle"),true);
          }
        }

	public void putTabErfassungstyp(int iAic,String sKennung,String sBezeichnung,String sFilename,int[] iEig,int iBits,boolean bNeu)
	{
          //boolean b=Static.bTest;
          //Static.bTest=false;
		int iPos=TabErfassungstypen.getPos(iAic,sKennung,true,sBezeichnung,bNeu,-1);
                if (bNeu || sFilename != null)
                  TabErfassungstypen.setInhalt(iPos,"Bild",sFilename);//Static.LoadImage(sFilename,Static.DirImageSys),bNeu);
		TabErfassungstypen.setInhalt(iPos,"Pool",null);
                TabErfassungstypen.setInhalt(iPos,"bits",iBits);
                for (int i=0;i<iANRs;i++)
                  TabErfassungstypen.setInhalt(iPos,"Eig"+(i+1),iEig[i]);
                TabErfassungstypen.setInhalt(iPos,"Datum_Eig2",iEig[iANRs]);
                TabErfassungstypen.setInhalt(iPos,"Datum_Eig3",iEig[iANRs+1]);
                TabErfassungstypen.setInhalt(iPos,"Bool_Eig1",iEig[iANRs+2]);
                TabErfassungstypen.setInhalt(iPos,"Bool_Eig2",iEig[iANRs+3]);
                TabErfassungstypen.setInhalt(iPos,"VB_Eig",iEig[iANRs+4]);
                //TabErfassungstypen.putInhalt("Eig1",iEig1,bNeu);
                //TabErfassungstypen.putInhalt("Eig2",iEig2,bNeu);
                //TabErfassungstypen.putInhalt("Eig3",iEig3,bNeu);
          //Static.bTest=b;
	}

        private int[] getTabANRs(Tabellenspeicher Tab)
        {
          int[] iR=new int[iANRs+5];
          for (int i=0;i<iANRs;i++)
            iR[i]=Tab.getI("AIC_Eig"+(i+1));
          iR[iANRs]=Tab.getI("Datum_Eig2");
          iR[iANRs+1]=Tab.getI("Datum_Eig3");
          iR[iANRs+2]=Tab.getI("Bool_Eig1");
          iR[iANRs+3]=Tab.getI("Bool_Eig2");
          iR[iANRs+4]=Tab.getI("VonBis_Eig");
          return iR;
        }

	public void LoadErfassungstypen()
	{
		VecLokaleTimer=new Vector<Integer>();
                VecSperre=new Vector<Integer>();
		TabErfassungstypen= new Tabellenspeicher(this,new String[] {"Aic","Kennung","Bezeichnung","Bild","Pool","bits","Eig1","Eig2","Eig3","Eig4","Eig5","Eig6","Eig7","Eig8","Eig9","Datum_Eig2","Datum_Eig3","Bool_Eig1","Bool_Eig2","VB_Eig"});
                TabErfassungstypen.setTitel("TabErfassungstypen");
		//SQL Qry = new SQL(this);
                iTabBew=TabTabellenname.getAic("BEWEGUNGSTYP");
                Tabellenspeicher Tab=new Tabellenspeicher(this,"SELECT bewegungstyp.*"+AU_Bezeichnung1("bewegungstyp","bewegungstyp")+" Bezeichnung"+
//                  isnull()+"(SELECT Bezeichnung FROM Bezeichnung AUB WHERE aic_tabellenname="+iTabBew+" AND AUB.AIC_Fremd=bewegungstyp.AIC_bewegungstyp AND AUB.AIC_Sprache="+iSprache+
//                  "),(SELECT Bezeichnung FROM Bezeichnung AUI WHERE aic_tabellenname="+iTabBew+" AND AUI.AIC_Fremd=bewegungstyp.AIC_bewegungstyp AND AUI.AIC_Sprache="+iSpStd+")) Bezeichnung"+
                  AU_Bild2("BEWEGUNGSTYP",iSpSw)+" filename FROM bewegungstyp where tod is null",true,"Bewegungstyp"+iSprache);
		//if(Qry.open("SELECT bewegungstyp.*"+AU_Bezeichnung("bewegungstyp")+AU_Bild("BEWEGUNGSTYP",true)+" filename FROM bewegungstyp"))
		//{
			for( ;!Tab.eof();Tab.moveNext())
			{
				if ((Tab.getI("BEWBITS")&cstLokalTimer)>0)
					VecLokaleTimer.addElement(Tab.getInt("AIC_bewegungstyp"));
                if ((Tab.getI("BEWBITS")&cstSperre)>0)
					VecSperre.addElement(Tab.getInt("AIC_bewegungstyp"));
//				if (Tab.getS("Kennung").equals("Abschluss"))
//					iAbschluss=Tab.getI("AIC_bewegungstyp");
//				else if (Tab.getS("Kennung").equals("AbschlussDef"))
//					iAbschlussDef=Tab.getI("AIC_bewegungstyp");
                if (Tab.getS("Kennung").equals("Wechselwaehrung"))
                    iWechselwaehrung=Tab.getI("AIC_bewegungstyp");
				putTabErfassungstyp(Tab.getI("AIC_bewegungstyp"),Tab.getS("Kennung"),Tab.getS("Bezeichnung"),Tab.getS("Filename"),getTabANRs(Tab),Tab.getI("BewBits"),true);
				//Qry.moveNext();
			}
		//}
		//Qry.free();
                fixtestInfo("VecLokaleTimer="+VecLokaleTimer+", VecSperre="+VecSperre);
	}

        public boolean WW()
        {
          return VecWW.size()>0;
        }

        /*public Date getAbschlussDate(int iBew)
        {
          return null;//TabMA2.posInhalt("aic_bewegungstyp",iBew) ? TabMA2.getDate("Datum"):null;
        }*/

        public void setAbschluss(int iBew,Date dt,int iProt,int iArt)
        {
          Vector<Integer> Vec=SQL.getVector("select abschlusstyp.aic_abschlusstyp from abschlusstyp"+join2("abschlussdefinition","abschlusstyp")+" where aic_bewegungstyp="+iBew+" and "+bits("bits",3)+"="+iArt,this);
          SQL Qry=new SQL(this);
          for (int i=0;i<Vec.size();i++)
          {
            int iTyp=Tabellenspeicher.geti(Vec.elementAt(i));
            exec("update abschluss set pro_aic_protokoll="+iProt+" where aic_abschlusstyp="+iTyp+" and pro_aic_protokoll is null");
            Qry.add("aic_abschlusstyp",iTyp);
            Qry.add("Datum",dt);
            Qry.add("aic_protokoll",iProt);
            Qry.insert("abschluss",false);
          }
          Qry.free();
        }
        
        public void setAbschluss(String sAT,Date dt,int iProt,int iStamm)
        {
          int iAbf=0;
          int iAT=0;
          for(int iPos=0;iPos<TabMA.size() && iAT==0;iPos++)
        	  if (TabMA.getS(iPos,"Kennung").equals(sAT) && (TabMA.getI(iPos,"AIC_Abfrage")==0 || ((Vector)TabMA.getInhalt("Daten",iPos)).contains(iStamm)))
        	  {
        		  iAbf=TabMA.getI(iPos,"aic_Abfrage");
        		  iAT=TabMA.getI(iPos,"aic_abschlusstyp");
        	  }
          if (iAT>0)
          {
	          //Vector<Integer> Vec=SQL.getVector("select abschlusstyp.aic_abschlusstyp from abschlusstyp"+join2("abschlussdefinition","abschlusstyp")+" where aic_bewegungstyp="+iBew,this);
	          SQL Qry=new SQL(this);
	          //for (int i=0;i<Vec.size();i++)
	          {
	            //int iTyp=Tabellenspeicher.geti(Vec.elementAt(i));
	            exec("update abschluss set pro_aic_protokoll="+iProt+" where aic_abschlusstyp="+iAT+(iAbf==0 ?"":" and aic_abfrage="+iAbf)+" and pro_aic_protokoll is null");
	            Qry.add("aic_abschlusstyp",iAT);
	            Qry.add0("aic_abfrage",iAbf);
	            Qry.add("Datum",dt);
	            Qry.add("aic_protokoll",iProt);
	            Qry.insert("abschluss",false);
	          }
	          Qry.free();
	          ShowAbfrage.checkAbschluss(this);
          }
        }
        
        public Vector<Integer> getAbschlussVecStamm(String sAT,int iStamm)
        {
        	for(int iPos=0;iPos<TabMA.size();iPos++)
          	  if (TabMA.getS(iPos,"Kennung").equals(sAT) && TabMA.getI(iPos,"AIC_Abfrage")>0 && ((Vector<Integer>)TabMA.getInhalt("Daten",iPos)).contains(iStamm))
          		  return (Vector<Integer>)TabMA.getInhalt("Daten",iPos);
        	return null;
        }

        public Date getAbschluss(int iBew,int iStamm,int iArt)
        {
          Date dt=null;
          testInfo("getAbschluss:"+iBew+"/"+iStamm+"/"+TabMA);
          //if (TestPC() && TabMA != null)
          //  TabMA.showGrid("TabMA");
          for(int iPos=0;iPos<TabMA.size();iPos++)
          {
            //testInfo("Abfrage:"+TabMA.getI("AIC_Abfrage")+","+TabMA.getInhalt("Daten"));
            if (TabMA.getI(iPos,"aic_bewegungstyp")==iBew && (iArt==-1 || iArt==(TabMA.getI(iPos,"bits")&3)))
              if (iStamm == 0 || TabMA.getI(iPos,"AIC_Abfrage") == 0 || ((Vector)TabMA.getInhalt("Daten",iPos)).contains(iStamm))
              {
                Date dt2 = TabMA.getDate(iPos,"Datum");
                if (dt == null || dt2.after(dt))
                  dt = dt2;
              }
          }
          if (dt!=null)
            fixtestInfo("-> Abschluss="+dt);
          return dt;
        }
        
      public Date getAbschlussP(int iProg,int iStamm)
        {
        	Date dt=null;
        	for(int iPos=0;iPos<TabMA.size();iPos++)
            {
              //testInfo("Abfrage:"+TabMA.getI("AIC_Abfrage")+","+TabMA.getInhalt("Daten"));
              if (TabMA.getI(iPos,"Prog")==iProg && (TabMA.getI(iPos,"AIC_Abfrage")==0 || ((Vector)TabMA.getInhalt("Daten",iPos)).contains(iStamm)))
                {
                  Date dt2 = TabMA.getDate(iPos,"Datum");
                  if (dt == null || dt2.after(dt))
                    dt = dt2;
                }
            }
        	fixtestInfo("getAbschlussP:"+iProg+"/"+iStamm+"->"+dt);
            return dt;
        }

        /**
        * liefert untergeordnete Rollen
	*/

        public int getVorRolle(int iRolle) // liefert Rolle darüber
        {
          int i=0;
          //TabRollen.push();
          int iPos=TabRollen.getPos("Aic",iRolle);
          if (iPos>=0)
            i=TabRollen.getI(iPos,"Davor");
          //TabRollen.pop();
          return i;
        }

        public Vector<Integer> getVecRolleStt(int iStt) // liefert Rollen des Stammtyps
        {
          Vector<Integer> Vec=new Vector<Integer>();
          for(int iPos=0;iPos<TabRollen.size();iPos++)
            if (TabRollen.getI(iPos,"Stt")==iStt)
              Vec.addElement(TabRollen.getInt(iPos,"Aic"));
          return Vec;
        }

        public Vector<Integer> getVecRolle(int iRolle) // liefert Rolle + darüber
        {
          Vector<Integer> Vec=new Vector<Integer>();
          Vec.addElement(new Integer(iRolle));
          int iPos=TabRollen.getPos("Aic",iRolle);
          while(iPos>=0 && TabRollen.getI(iPos,"Davor")>0)
          {
            iRolle=TabRollen.getI(iPos,"Davor");
            Vec.addElement(new Integer(iRolle));
            iPos=TabRollen.getPos("Aic",iRolle);
          }
          return Vec;
        }

        public Vector<Integer> getVecRolle2(int iRolle,boolean bAlle) // liefert alle Rollen darunter
        {
          Vector<Integer> Vec=new Vector<Integer>();
          if (iRolle<0)
          {
            int iPos=TabRollen.getPos("Stt",-iRolle);
            if (iPos>=0)
              for(;iPos<TabRollen.size();iPos++)
                if (TabRollen.getI(iPos,"Stt") == -iRolle && (bAlle || TabRollen.getI(iPos,"Davor")==0))
                  Vec.addElement(TabRollen.getInt(iPos,"Aic"));
          }
          else
            getRecRolle(Vec,iRolle,bAlle);
          return Vec;
        }

        public Vector<Integer> getVecRolle3(int iRolle) // liefert Rolle + darunter
        {
          Vector<Integer> Vec = new Vector<Integer>();
          Vec.addElement(new Integer(iRolle));
          getRecRolle(Vec,iRolle,true);
          return Vec;
        }

        private void getRecRolle(Vector<Integer> Vec,int iRolle,boolean bAlle)
        {
          //TabRollen.push();
          int iPos=TabRollen.getPos("Davor",iRolle);
          if (iPos>=0)
          {
              for(;iPos<TabRollen.size();iPos++)
                if (TabRollen.getI(iPos,"Davor") == iRolle)
                {
                  Vec.addElement(TabRollen.getInt(iPos,"Aic"));
                  if (bAlle)
                    getRecRolle(Vec,TabRollen.getI(iPos,"Aic"),true);
                }
          }
          //TabRollen.pop();
        }

	public Date getAbschlussDate(int iBew,int iStamm)
	{
                if (!TabMA.isEmpty())
                  return TabMA.getPos("aic_bewegungstyp",iBew)>=0 ? getAbschluss(iBew,iStamm,-1):null;//getAbschlussDate(iBew);
		else
                    return null;
	}
	
	public Date getAbschlussDate(int iBegriff,int iBew,int iStamm)
	{
		if (!TabMA.isEmpty())
             return TabMA.getPos("aic_bewegungstyp",iBew)>=0 ? getAbschluss(iBew,iStamm,-1):null;//getAbschlussDate(iBew);
		else
             return null;
	}

//        public String getDatentyp(Object Obj)
//        {
//          String s=null;
//          //TabEigenschaften.push();
//          //if (Obj instanceof Long)
//          //  Obj=new Integer(((Long)Obj).intValue());
//          int iPos=TabEigenschaften.getPos("Aic",Sort.geti(Obj));
//          if (iPos>-1)
//            s=TabEigenschaften.getS(iPos,"Datentyp");
//          //TabEigenschaften.pop();
//          //testInfo("getDatentyp:"+Static.print(Obj)+"->"+s);
//          return s;
//        }

	public void putTabEigenschaft(int iAic,String sKennung,String sBezeichnung,String sDatentyp,String sInfo,String sFormat,Integer iLaenge,int iStamm,int iStammtyp,int iBits,Integer iMin,Integer iMax,int iAbfrage,int iEig2,int iRolle,int iBew,String sTooltip,int iBreite,boolean bNeu)
	{
          //boolean b=Static.bTest;
          //Static.bTest=false;
		int iPos=TabEigenschaften.getPos(iAic,sKennung,true,sBezeichnung,bNeu,-1);
		TabEigenschaften.setInhalt(iPos,"Datentyp",sDatentyp);
		TabEigenschaften.setInhalt(iPos,"Info",sInfo==null || sInfo.equals("") ? null : sInfo);
		TabEigenschaften.setInhalt(iPos,"Format",sFormat);
		TabEigenschaften.setInhalt(iPos,"Laenge",iLaenge);
		TabEigenschaften.setInhalt(iPos,"aic_stamm",iStamm);
		TabEigenschaften.setInhalt(iPos,"aic_stammtyp",iStammtyp);
		//TabEigenschaften.putInhalt("uni",new Boolean(bUni),bNeu);
		//TabEigenschaften.putInhalt("always",new Boolean(bAlways),bNeu);
		TabEigenschaften.setInhalt(iPos,"bits",iBits);
		TabEigenschaften.setInhalt(iPos,"min",iMin);
		TabEigenschaften.setInhalt(iPos,"max",iMax);
		TabEigenschaften.setInhalt(iPos,"Abfrage",iAbfrage);
		TabEigenschaften.setInhalt(iPos,"Eig2",iEig2==0 ? null:new Integer(iEig2));
        TabEigenschaften.setInhalt(iPos,"Rolle",iRolle==0 ? null:new Integer(iRolle));
        TabEigenschaften.setInhalt(iPos,"Bew",iBew==0 ? null:new Integer(iBew));
        TabEigenschaften.setInhalt(iPos,"Tooltip",sTooltip==null || sTooltip.equals("") ? null : sTooltip);
        TabEigenschaften.setInhalt(iPos,"Breite",iBreite);
          //Static.bTest=b;
	}

	private void LoadEigenschaften()
	{
		//printInfo("vor LoadEigenschaften");
                long lClock2 = Static.get_ms();
                //VecJederEig.clear();
                //VecLizEig.clear();
		TabEigenschaften= new Tabellenspeicher(this,new String[] {"Aic","Kennung","Bezeichnung","Datentyp","Info","Format","Laenge","Aic_Stamm","Aic_Stammtyp","bits","min","max","Abfrage","Eig2","Rolle","Bew","Tooltip","Breite"});
		//TabEigenschaften.bN=true;
		TabEigenschaften.setTitel("TabEigenschaften");
                //SQL Qry = new SQL(this);
                //int iTabEig=TabTabellenname.getAic("EIGENSCHAFT");
		String sSQL="SELECT aic_eigenschaft,eigenschaft.kennung,begriff.kennung datentyp,Format,feldlaenge,breite,eigenschaft.Bits,min,max,eigenschaft.aic_stamm,eigenschaft.aic_stammtyp,aic_abfrage,Eig_Aic_Eigenschaft,Eigenschaft.aic_rolle,Eigenschaft.aic_bewegungstyp"+
				AU_Bezeichnung1("eigenschaft","eigenschaft")+" Bezeichnung"+
//                            isnull()+"(SELECT Bezeichnung FROM Bezeichnung AUB WHERE aic_tabellenname="+iTabEig+" AND AUB.AIC_Fremd=Eigenschaft.AIC_Eigenschaft AND AUB.AIC_Sprache="+iSprache+
//			"),(SELECT Bezeichnung FROM Bezeichnung AUI WHERE aic_tabellenname="+iTabEig+" AND AUI.AIC_Fremd=Eigenschaft.AIC_Eigenschaft AND AIC_Sprache="+iSpStd+")) Bezeichnung"+
			(Static.bMemo ? AU_Memo("Eigenschaft"):"")+AU_Tooltip("Eigenschaft","Eigenschaft")+" FROM Eigenschaft join begriff on eigenschaft.aic_begriff=begriff.aic_begriff";
                Tabellenspeicher Tab=new Tabellenspeicher(this,sSQL,true,"Eigenschaft"+iSprache+(Static.bMemo ? "M":""));//
                //Tab.showGrid("LoadEigenschaften");
                //if (Qry.open(sSQL))
		//{
                //VecEigANR=new Vector();
                VecEigZone=new Vector<Integer>();
                VecEigDST=new Vector<Integer>();
                VecEigTake=new Vector<Integer>();
			for( ;!Tab.eof();Tab.moveNext())
			{
				String sKennung=Tab.getS("Kennung");
				int iAicEig = Tab.getI("AIC_Eigenschaft");
				int iBits=Tab.getI("Bits");
				if (sKennung.equals("Faktor"))
					iEigFaktor = iAicEig;
				else if (sKennung.equals("Eurofaktor"))
					iEigEurofaktor = iAicEig;
				else if (sKennung.equals("Farbe"))
					iEigFarbe = iAicEig;
                else if (sKennung.equals("ist_wechselwaehrung"))
					iEigIstWW = iAicEig;
                else if (sKennung.equals("vorueb_ Austritt"))
					iEigAustritt = iAicEig;
                else if (sKennung.equals("Zone_Min"))
					iEigZoneMin = iAicEig;
                else if (sKennung.equals("Firma"))
					iEigFirma = iAicEig;
                else if (sKennung.equals("Mitarbeiter"))
					iEigMA = iAicEig;
               else if (sKennung.equals("Religion"))
					iEigReligion = iAicEig;
               else if (sKennung.equals("LV_Region_Gruppe"))
                    iEigRegion = iAicEig;
               else if (sKennung.equals("HierarchieFH"))
					iEigLand = iAicEig;
               else if (sKennung.equals("Sperre"))
					iTimerSperre = iAicEig;
               else if (sKennung.equals("AufrufModell"))
                    iTimerModell=iAicEig;
               else if (sKennung.equals("fortsetzen"))
                    iTimerFort=iAicEig;
               else if (sKennung.equalsIgnoreCase("Abgearbeitet"))
                    iTimerFertig=iAicEig;
               else if (sKennung.equalsIgnoreCase("Modellzeitraum"))
                    iTimerZR=iAicEig;
               else if (sKennung.equalsIgnoreCase("TimerStamm"))
                    iTimerStamm=iAicEig;
               else if (sKennung.equalsIgnoreCase("nicht_berechnen"))
                    iTimerNicht=iAicEig;
               else if (sKennung.equalsIgnoreCase("ZE_abw_Aufrufzeitpun"))
                    iTimerAlt=iAicEig;
               else if (sKennung.equalsIgnoreCase("Bild_DB"))
                    iEigBild=iAicEig;
               else if (sKennung.equalsIgnoreCase("E-Mail"))
                   iEigEMail=iAicEig;
               else if (sKennung.equalsIgnoreCase("company_mobile"))
                   iEigTel=iAicEig;
                                //else if (sKennung.equals("Mitarbeiter"))
				//	iEigANR = iAicEig;
                                //if (Tab.getI("aic_stammtyp")==iSttANR && Tab.getS("datentyp").equals("BewStamm") && (Tab.getI("Bits")&cstKeinANR)==0)
                                //  VecEigANR.addElement(new Integer(iAicEig));
                                //else if (sKennung.equals("Calc"))
				//	iEigCalc = iAicEig;
                                //if ((Tab.getI("Bits")&cstJeder)>0)
                                //  VecJederEig.addElement(new Integer(iAicEig));
                                //if ((Tab.getI("Bits")&cstEigLizenz)>0)
                                //  VecLizEig.addElement(new Integer(iAicEig));
				if ((iBits&cstEigZone)>0)
				{
					VecEigZone.addElement(iAicEig);
					iSttZone=Tab.getI("aic_stammtyp");
				}
				if ((iBits&cstEigDST)>0)
					VecEigDST.addElement(iAicEig);
				if ((iBits&cstEigTake)>0)
					VecEigTake.addElement(iAicEig);
				putTabEigenschaft(iAicEig,sKennung,Static.beiLeer(Tab.getM("Bezeichnung"),sKennung),Tab.getS("datentyp"),Static.bMemo ? Tab.getM("Memo"):"",Tab.getS("Format"),
                                                  Tab.getInt("feldlaenge"),Tab.getI("aic_stamm"),Tab.getI("aic_stammtyp"),iBits,Tab.getInt("min"),Tab.getInt("max"),
                                                  Tab.getI("Aic_Abfrage"),Tab.getI("Eig_Aic_Eigenschaft"),Tab.getI("Aic_Rolle"),Tab.getI("Aic_Bewegungstyp"),Tab.getM("TT"),Tab.getI("Breite"),true);
				//Qry.moveNext();
			}
//			fixtestError("VecEigZone="+VecEigZone+", Stt="+iSttZone+", VecEigDST="+VecEigDST+", VecEigTake="+VecEigTake);
//			fixtestError("Zone="+getZone()+"/"+getZoneS());
			
                  //testInfo("VecJederEig="+VecJederEig);
		//}
		//Qry.free();
                //testInfo("Eig-Firma="+iEigFirma+", Eig-MA="+iEigMA);
                clockInfo("LoadEigenschaften",lClock2);
                //if (SAP())
                //  TabEigenschaften.showGrid("Eigenschaften");
		//printInfo("nach LoadEigenschaften");
	}

        public void LoadAuswahl()
        {
          TabAuswahl = new Tabellenspeicher(this,"select aic_auswahl aic,kennung"+AU_Bezeichnung("Auswahl")+AU_Bild2("Auswahl",iSpSw)+" Bild,aic_eigenschaft,nr from auswahl order by aic_eigenschaft,nr",true);
          TabAuswahl.checkBezeichnung();
          //TabAuswahl.sAIC="aic_auswahl";
          TabAuswahl.setTitel("TabAuswahl");
          //TabAuswahl.showGrid();
        }

        public int getAuswahlPos(String s,int iEig)
        {
          int iPos=TabAuswahl.getPos("aic_eigenschaft",iEig);
          if (iPos<0)
            return -1;
          iPos=s.length()==1 ? TabAuswahl.getNextPos(iPos-1,"nr",Integer.parseInt(s)):TabAuswahl.getNextPos(iPos-1,"kennung",s);
          return iPos;
          //return iPos<0 ? -1:TabAuswahl.getI(iPos,"aic_auswahl");
        }

        public int getAuswahlPosB(int iEig,String sBez)
        {
          int iPos=TabAuswahl.getPos("aic_eigenschaft",iEig);
          if (iPos<0)
            return -1;
          iPos=TabAuswahl.getNextPos(iPos-1,"Bezeichnung",sBez);
          return iPos<0 || TabAuswahl.getI(iPos,"aic_eigenschaft")!=iEig ? -1:iPos;
          //return iPos<0 ? -1:TabAuswahl.getI(iPos,"aic_auswahl");
        }

        public int getAuswahlAic(int iPos)
        {
          return iPos<0 ? 0:TabAuswahl.getI(iPos,"aic");
        }

        public String getAuswahl(int iPos)
        {
          return iPos<0 ? Static.sLeer:TabAuswahl.getS(iPos,"Bezeichnung");
        }

        public Tabellenspeicher readTabAW(int iEig,boolean bDef)
        {
          Tabellenspeicher Tab = new Tabellenspeicher(this, new String[] {"Aic", "Bez", "Nr","Komp"});
          for (int iPos = TabAuswahl.getPos("aic_eigenschaft", iEig)+(bDef?1:0); iPos < TabAuswahl.size() && TabAuswahl.getI(iPos, "aic_eigenschaft") == iEig; iPos++)
          {
            Tab.addInhalt("Aic", TabAuswahl.getI(iPos, "aic"));
            Tab.addInhalt("Bez", TabAuswahl.getS(iPos, "Bezeichnung"));
            Tab.addInhalt("Nr", TabAuswahl.getI(iPos, "Nr"));
          }
          return Tab;
        }

        public int getAuswahlNr(int iAic)
        {
          int iPos=TabAuswahl.getPos("aic",iAic);
          return iPos<0 ? 0:TabAuswahl.getI(iPos,"Nr");
        }

        /*public boolean isEigANR(int iEig)
        {
          return VecEigANR.contains(new Integer(iEig));
        }

        public String getEigANR()
        {
          return VecEigANR==null || VecEigANR.isEmpty() ? "=-1":Static.SQL_in(VecEigANR);
        }*/

        public int EigToStt(int iEig)
        {
          //TabEigenschaften.push();
          int iPos=TabEigenschaften.getPos("aic",iEig);
          int iSttEig=iPos>=0 ? TabEigenschaften.getI(iPos,"aic_stammtyp"):0;
          //TabEigenschaften.pop();
          return iSttEig;
        }

        public int RolleToStt(int iRolle)
        {
          //TabRollen.push();
          int iPos=TabRollen.getPos("aic",iRolle);
          int iSttRolle=iPos>=0 ? TabRollen.getI(iPos,"Stt"):0;
          //TabRollen.pop();
          return iSttRolle;
        }

        public Image getImageBegriff(int iPos)
        {
          return LoadImage(TabBegriffe.getS(iPos,Static.sBild));
        }

        public Image getImageBG(int iAic)
        {
          int iPos=TabBegriffgruppen.getPos("Aic",iAic);
          return iPos<0 ? null:LoadImage(TabBegriffgruppen.getS(iPos,"Filename"));
        }

	/*public Image getImage(String sTabellenname, int iAIC_Fremd)
	{

		return LoadImage(sTabellenname.equalsIgnoreCase("Begriff") ? (TabBegriffe.posInhalt("Aic",new Integer(iAIC_Fremd)) ? TabBegriffe.getS("BildFile"):"") :
			sTabellenname.equalsIgnoreCase("Begriffgruppe") ? (TabBegriffgruppen.posInhalt("Aic",new Integer(iAIC_Fremd)) ? TabBegriffgruppen.getS("Filename"):"") :
			SQL.getString(this,"SELECT Filename FROM Daten_Bild WHERE aic_tabellenname="+TabTabellenname.getAic(sTabellenname.toUpperCase())+" AND AIC_Fremd="+iAIC_Fremd+" AND AIC_SPRACHE="+iSprache));
	}*/
        
    public Image getImage(String sTabellenname, String sZeile)
    {
    	return getImage(sTabellenname,sZeile,false);
    }

	public Image getImage(String sTabellenname, String sZeile,boolean bFX)
	{
		/*Image Img = null;
		if (Static.bBilder)
		{
			SQL SQLQry = new SQL(this);
			if(SQLQry.open("SELECT AIC_"+sTabellenname+" FROM "+sTabellenname+" WHERE Kennung='"+sZeile+"'"))
			{
				if(!SQLQry.eof())
					Img=getImage(sTabellenname,SQLQry.getI("AIC_"+sTabellenname));
				SQLQry.close();
			}
		}
		return(Img);*/
                if (sTabellenname.equals("Begriff"))
                {
                  int iPos=TabBegriffe.getPos("Kennung",sZeile);
                  //if (bFX) fixtestInfo("getImage "+sZeile+"->"+iPos);
                  return iPos<0 ? null:LoadImage(TabBegriffe.getS(iPos,bFX ? "BildFX":"BildFile"));
                }
                else if (sTabellenname.equals("Begriffgruppe"))
                {
                  int iPos=TabBegriffgruppen.getPos("Kennung",sZeile);
                  return iPos<0 ? null:LoadImage(TabBegriffgruppen.getS(iPos,"Filename"));
                }
                else
		  return LoadImage(/*sTabellenname.equals("Begriff") ? (TabBegriffe.posInhalt("Kennung",sZeile) ? TabBegriffe.getS("BildFile"):"") :
			sTabellenname.equals("Begriffgruppe") ? (TabBegriffgruppen.posInhalt("Kennung",sZeile) ? TabBegriffgruppen.getS("Filename"):"") :*/
			SQL.getString(this,"SELECT Filename FROM "+sTabellenname+ " t,Daten_Bild JOIN Tabellenname WHERE Tabellenname.Kennung='"+sTabellenname+"' AND AIC_Fremd=t.aic_"+sTabellenname+" and t.kennung='"+sZeile+"'"+" AND AIC_ZUSTAND="+(bFX ? iSpFX:iSpSw)));
	}

	public Vector getImageVector(String sTabellenname, int iAIC_Fremd)
	{
		Vector<Object> Vec = new Vector<Object>();
		Vec.addElement("");
		Vec.addElement(null);
		SQL SQLQry = new SQL(this);
		if(SQLQry.open("SELECT Filename FROM Daten_Bild WHERE AIC_TABELLENNAME="+TabTabellenname.getAic(sTabellenname.toUpperCase())+" AND AIC_Fremd="+iAIC_Fremd+" AND AIC_ZUSTAND="+iSpSw))
		{
			if(!SQLQry.eof())
			{
				Vec.setElementAt(SQLQry.getS("Filename").trim(),0);
				//if (Static.bBilder)
					Vec.setElementAt(LoadImage(SQLQry.getS("Filename")),1);
			}
		}
		SQLQry.free();
		//progInfo("getImageVector:"+sTabellenname+"/"+iAIC_Fremd+"<"+Vec.elementAt(0)+">");
		return(Vec);
	}
	
//	public String getMsg(String sKennung, String[] sArray)
//	{
//		return getMsg("Message",sKennung,sArray);
//	}
//	
//	public String getMsg(String sGruppe,String sKennung, String[] sArray)
//	{
//		Vector<String> Vec2=getTranslate(sGruppe,sKennung,sArray,true);
//		return Sort.gets(Vec2,1);
//	}
	
	public Vector<String> getTranslate(String sGruppe,String sKennung, String[] sArray,boolean bWeb)
	{
		Vector<String> Vec;
		int iPos=getPosBegriff(sGruppe,sKennung);
		if (iPos>=0)
			Vec=getMemoVector("Begriff",TabBegriffe.getI(iPos,"Aic"));
		else
		{
			Vec = new Vector<String>();
			Vec.addElement(sKennung);
			Vec.addElement("*"+sKennung+"*\n/1");
			Vec.addElement(null);
		}
		if (!bWeb)
			Vec.setElementAt(Vec.elementAt(1).replaceAll("<br>", "\n"),1);
		if (sArray!=null)
			Vec.setElementAt(Static.makeString(Vec.elementAt(1), sArray),1);
		//fixtestError("getTranslate "+sGruppe+"/"+sKennung+"->"+Vec);
		return Vec;
	}

	public Vector<String> getMemoVector(String sTabellenname, int iAIC_Fremd)
	{
		Vector<String> Vec = new Vector<String>();
		Vec.addElement("");
		Vec.addElement("");
		Vec.addElement("");
		SQL SQLQry = new SQL(this);
                int iSpracheMom=iSprache>0 ? iSprache:1;
		if(SQLQry.open("SELECT * FROM Daten_Memo WHERE AIC_TABELLENNAME="+TabTabellenname.getAic(sTabellenname.toUpperCase())+" AND AIC_Fremd="+iAIC_Fremd+" AND AIC_SPRACHE="+iSpracheMom))
		{
			if (SQLQry.eof())
				SQLQry.open("SELECT * FROM Daten_Memo WHERE AIC_TABELLENNAME="+TabTabellenname.getAic(sTabellenname.toUpperCase())+" AND AIC_Fremd="+iAIC_Fremd+" AND AIC_SPRACHE="+iStdSprache);
			if(!SQLQry.eof())
			{
				Vec.setElementAt(SQLQry.getS("Titel"),0);
				Vec.setElementAt(SQLQry.getS("Memo"),1);
				Vec.setElementAt(SQLQry.exists("Header") ? SQLQry.getS("Header"):null,2);
			}
		}
		SQLQry.free();
		return(Vec);
	}

        public String getMemo2(int iAIC_Begriff)
        {
          return SQL.getString(this,"SELECT Memo FROM Daten_Memo WHERE AIC_TABELLENNAME="+iTabBegriff+" and aic_sprache="+iSprache+" and aic_fremd=?",""+iAIC_Begriff);
        }

	/*public int getAic(int iTyp)
	{
		return iTyp<Waehrung || iTyp>Eurofaktor ? 0:iAic[iTyp];
	}*/

	/*
	public static boolean Gleich(Object Obj1, Object Obj2)
	{
		if(Obj1==null && Obj2==null)
			return true;
		else if(Obj1==null)
			return false;
		else
			return(Obj1.equals(Obj2));
	}

	public static String beiLeer(String s,String s2)
	{
		return s.equals("") ? s2:s;
	}
	*/

        public double getFaktor(int iAic)
        {
          return Tabellenspeicher.getFaktor(iAic,this);
        }

	public boolean GleicheMassgruppe(int iGruppe,int iAic)
	{
		if (iAic==0)
			return true;
		getFaktor(iAic);
		return iGruppe==TabMass.getI("Gruppe");
	}

        /*public Vector getMass(int iAic)
	{
          return getMass(iAic,false);
        }*/

	public Vector<Object> getMass(int iAic,boolean bKeineEinheit)
	{
		double d=getFaktor(iAic);
		//if(d==0.0)
		//	progInfo("Mass von "+iAic+" ist null!");
		Vector<Object> Vec=new Vector<Object>();
		Vec.addElement(d==0.0?null:new Double(d));
		Vec.addElement(bKeineEinheit || TabMass.out()?null:TabMass.getInhalt("Kennung"));
		Vec.addElement(iAic==0?null:new Integer(iAic));
		return Vec;
	}
        public Vector getVecWaehrung2(boolean bKeineEinheit,int iW)
        {
          Vector<Object> Vec=new Vector<Object>();
          Vec.addElement(new Double(1.0));
          int iPos=bKeineEinheit ? -1:TabWaehrung.getPos("aic_stamm",iW);
          Vec.addElement(iPos<0 ? null:TabWaehrung.getInhalt("Bezeichnung",iPos));
          Vec.addElement(new Integer(iW));
          return Vec;
        }

	public Vector getVecWaehrung(boolean bKeineEinheit,int iW)
	{
          if (iW==0)
            iW=iWaehrung;
          Vector<Object> Vec=new Vector<Object>();
          int iPos=bKeineEinheit ? -1:TabWaehrung.getPos("aic_stamm",iW);
          //progInfo("getVecWaehrung:"+iW+"->"+TabWaehrung.getInhalt("Faktor"));
          //Vec.addElement(TabWaehrung.getInhalt("Faktor"));
          Vec.addElement(new Double(1.0));
          Vec.addElement(iPos<0 ? null:TabWaehrung.getInhalt("Bezeichnung",iPos));
          Vec.addElement(new Integer(iW));
          return Vec;
	}

        public Vector getVecWaehrung(boolean bKeineEinheit)
	{
          return getVecWaehrung(bKeineEinheit,0);
        }

        /*public Vector getVecWaehrung()
	{
          return getVecWaehrung(false,0);
        }*/

	/*public String getWaehrungskennung()
	{
		return sWaehrung;
	}*/

	public int SystemFormular()
	{
		return iSystemFormular;
	}

	/*public int getLog()
	{
		return iLog;
	}*/

	public boolean AllUnlimited()
	{
//		if (Static.bInfoTod)
//			fixInfo("AllUnlimited(): bLizenzFrei="+bLizenzFrei+", iMandant="+iMandant);
		return bLizenzFrei || iMandant==1;//iMandantLog==1;
	}

        public void initOutliner(AUOutliner Out,String s[])
        {
          for (int i=0;i<s.length;i++)
            s[i]=getBegriffS("Show",s[i]);
          Out.setColumnButtons(s);
          Out.setNumColumns(s.length);
          Out.setRootVisible(false);
          //Out.setColumnLabelSortMethod(Sort.sortMethod);
          //Out.setBackground(Color.white);
        }

        @SuppressWarnings("unchecked")
        public static AUOutliner getGid(Vector VecBeg,Global g,String sSQL)
        {
          AUOutliner Gid = new AUOutliner();
          Tabellenspeicher Tab=new Tabellenspeicher(g,sSQL,true);
          if (VecBeg!=null)//(i>4 && i<9)
            Static.addVector(VecBeg,Tab.getVecSpalte("aic_begriff"));
          Tab.showGrid(Gid);
          return Gid;
        }

        public void getChildAics(JCOutlinerNode Nod,Vector<Integer> Vec)
        {
          jclass.util.JCVector Vec2= Nod.getChildren();
          for(int i=0;i<Vec2.size();i++)
          {
            int iAic=Sort.geti(((JCOutlinerNode)Vec2.elementAt(i)).getUserData());
            if (iAic==0)
              getChildAics((JCOutlinerNode)Vec2.elementAt(i),Vec);
            else if (!Vec.contains(iAic))
              Vec.addElement(iAic);
          }
        }

        public Vector<Integer> getAics(JCOutliner rGid)
        {
        	return getAics(rGid,-1);
        }
        
        public Vector<Integer> getAics(JCOutliner rGid,int iSpalte)
        {
          Vector<Integer> Vec=new Vector<Integer>();
          JCOutlinerNode[] Nodes=rGid.getSelectedNodes();
          if (Nodes==null)
            return null;
          //fixInfo("getAics: Anzahl Nodes="+Nodes.length);
          for(int i=0;i<Nodes.length;i++)
            if (Nodes[i].getParent()!=null)
          {
            //fixInfo(i+".:"+(Nodes[i]==null ? null:Nodes[i].getParent()+"/"+Nodes[i]+"/ UD="+Nodes[i].getUserData()));
            	
            Integer Int=(Integer)(iSpalte==-1 ? Nodes[i].getUserData():Sort.geti(Nodes[i].getUserData(),iSpalte));
            if (Int==null)
              getChildAics(Nodes[i],Vec);
            else if (!Vec.contains(Int))
                Vec.addElement(Int);
          }
          progInfo("getAics:"+Vec);
          return Vec;
        }

        public Vector<Integer> getAllAics(JCOutliner rGid) // nur für Dublettenprüfung
        {
          Vector<Integer> Vec=new Vector<Integer>();
          Vector Vec2= rGid==null ? null:rGid.getRootNode().getChildren();
          //progInfo("getAllAics:"+Vec2);
          if (Vec2 != null)
           for(int i=0;i<Vec2.size();i++)
           {
            //Integer Int=(Integer)Nodes[i].getUserData();
            int iAic=Sort.geti(((JCOutlinerNode)Vec2.elementAt(i)).getUserData());
            //progInfo(i+".:"+iAic);
            if (!Vec.contains(iAic))
                Vec.addElement(iAic);
           }
          return Vec;
        }

        public JCOutlinerNodeStyle getTabStype(String sKennung)
        {
          int iPos=TabTabellenname.getPos("Kennung",sKennung);
//          fixtestError("getTabStype "+sKennung);
          return getStyle(iPos<0?null:getGif(TabTabellenname,TabTabellenname.getI(iPos,"Aic")));
        }

        public JCOutlinerNodeStyle getBGStype(String sKennung)
        {
          int iPos=TabBegriffgruppen.getPos("Kennung",sKennung);
          return getStyle(iPos<0?null:LoadImage(TabBegriffgruppen.getS(iPos,"Filename")));
        }

	public JCOutlinerNodeStyle getTabStyle(int riTab)
	{
		//Image Gif = TabTabellenname.posInhalt("Aic",riTab) ? (Image)TabTabellenname.getInhalt("Bild"): null;
		return getStyle(getGif(TabTabellenname,riTab));
	}

        public JCOutlinerNodeStyle getRolleStyle(int riRolle)
        {
                //Image Gif = TabRollen.posInhalt("Aic",riRolle) ? (Image)TabRollen.getInhalt("Bild"): null;
                return getStyle(getGif(TabRollen,riRolle));
        }

        public Image getSttGif(int riSTT)
        {
//        	fixtestError("getSttGif"+riSTT);
        	return getGif(TabStammtypen,riSTT);
        }

        public Image getBewGif(int riSTT)
        {
//        	fixtestError("getBewGif"+riSTT);
        	if (riSTT==0)
        		return null;
          return getGif(TabErfassungstypen,riSTT);
        }

        public Image getRolleGif(int riSTT)
        {
          return getGif(TabRollen,riSTT);
        }

        public Image getGif(Tabellenspeicher Tab,int riAic)
        {
          int iPos=Tab.getPos("Aic",riAic);
          if (iPos<0)
            return null;
          else
          {
            Object Obj=Tab.getInhalt("Bild",iPos);
//            fixtestError("getGif "+Tab.getS(iPos,"Kennung")+": "+Obj);
            Image Gif = null;
            if (Obj instanceof String && !Obj.equals(""))
            {
              Gif=LoadImage((String)Obj,Static.DirImageSys);
              //Tab.setInhalt(iPos, "Bild", Gif);
            }
            else if (Obj instanceof Image)
              Gif=(Image)Obj;
            return Gif;
          }
        }

        public Image getGif(Tabellenspeicher Tab,String rsKennung)
        {
          int iPos=Tab.getPos("Kennung",rsKennung);
          if (iPos<0)
            return null;
          else
            return getGif(Tab,Tab.getI(iPos,"Aic"));
        }
        /*public Image getGif(Tabellenspeicher Tab,String rsKennung)
        {
          int iPos=Tab.getPos("Kennung",rsKennung);
          if (iPos<0)
            return null;
          else
          {
            Object Obj=Tab.getInhalt("Bild",iPos);
            Image Gif = null;
            if (Obj instanceof String && !Obj.equals(""))
            {
              Gif=Static.LoadImage((String)Obj,Static.DirImageSys);
              Tab.setInhalt(iPos, "Bild", Gif);
            }
            else if (Obj instanceof Image)
              Gif=(Image)Obj;
            return Gif;
          }
        }*/

	public JCOutlinerNodeStyle getSttStyle(int riSTT)
	{
		//Image Gif = TabStammtypen.posInhalt("Aic",riSTT) ? (Image)TabStammtypen.getInhalt("Bild"): null;
		return getStyle(getGif(TabStammtypen,riSTT));
	}

        public ImageIcon getImageIcon(String sFile)
        {
          URL s=getClass().getResource("/images/"+sFile);
//          fixInfo("getImageIcon:"+s);
          return new ImageIcon(s);
        }

        public Image getImage(String sFile)
        {
        	try
        	{
        		return new ImageIcon(getClass().getResource("/images/"+sFile)).getImage();
        	}
        	catch (Exception e)  
        	{
        		Static.printError("Image "+sFile+" nicht gefunden");
        		return null;
        	}
        }

	public JCOutlinerNodeStyle getStyle(Image Gif)
	{
		if (Gif != null)
		{
			JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
			StyFolder.setFolderClosedIcon(Gif);
			StyFolder.setFolderOpenIcon(Gif);
			StyFolder.setItemIcon(Gif);
			StyFolder.setFont(getOutFont(false));//fontStandard);
			//StyFolder.setFont(fontBezeichnung);
			return StyFolder;
		}
		else
        {
          JCOutlinerNodeStyle StyFolder = (new JCOutlinerComponent()).getDefaultNodeStyle();
          StyFolder.setFont(getOutFont(false));//fontStandard);
          String sOpen=Static.bND ? "Folder-open.png":"open.png";
          String sClose=Static.bND ? "Folder-close.png":"close.png";
          StyFolder.setFolderOpenIcon(getImage(sOpen));
          StyFolder.setFolderOpenSelectedIcon(getImage(sOpen));
          StyFolder.setFolderClosedIcon(getImage(sClose));
          StyFolder.setFolderClosedSelectedIcon(getImage(sClose));
          return StyFolder;
        }
	}
	
//	public JCOutlinerNodeStyle getNullStyle()
//	{
//		JCOutlinerNodeStyle StyFolder = (new JCOutlinerComponent()).getDefaultNodeStyle();
//		StyFolder.setFolderOpenIcon(null);
//        StyFolder.setFolderOpenSelectedIcon(null);
//        StyFolder.setFolderClosedIcon(null);
//        StyFolder.setFolderClosedSelectedIcon(null);
//        StyFolder.setItemIcon(null);
//        StyFolder.setFont(getOutFont(false));
//		return StyFolder;
//	}

	public int getAlignment(String sDatentyp,int iAlign)
	{
		String s=iAlign>0?TabCodes.getKennung(iAlign):null;
		int i=s != null ? s.equals("LEFT") ? BWTEnum.MIDDLELEFT:s.equals("CENTER") ? BWTEnum.MIDDLECENTER : BWTEnum.MIDDLERIGHT:-1;
		return i>=0 ? i:sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2") || sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcField")
			|| sDatentyp.endsWith("Dauer") || sDatentyp.equals("Einheiten") || sDatentyp.equals("Aic") || sDatentyp.equals("SysAic") || sDatentyp.equals("BenMass")
			|| sDatentyp.equals("Integer") || sDatentyp.equals("Double") || sDatentyp.startsWith("BewZahl") || sDatentyp.equals("BewCount") || sDatentyp.equals("BewVon_Bis") ? BWTEnum.MIDDLERIGHT:
			sDatentyp.equals("Boolean") || sDatentyp.equals("BewBoolean") || sDatentyp.equals("BewBool3")? BWTEnum.MIDDLECENTER : BWTEnum.MIDDLELEFT;
	}

	public boolean TimerMessage()
	{
		boolean b= VecLokaleTimer != null && !VecLokaleTimer.isEmpty();
                //testInfo("TimerMessage:"+b+"/"+VecLokaleTimer);
                return b;
	}

        public boolean BewSperre(int iBew)
        {
          return VecSperre !=null && VecSperre.contains(iBew);
        }

	public String LokaleTimer()
	{
		String s= "bew_pool.aic_bewegungstyp"+(VecNull("LokaleTimer: VecLokaleTimer",VecLokaleTimer)?"=-1":Static.SQL_in(VecLokaleTimer));
                //testInfo("LokaleTimer:"+s);
                return s;
	}

	public Color getColor(int iStamm,Color ColErsatz)
	{
          int iPos=TabStammFarbe.getPos("Stamm",iStamm);
		if (iPos>=0)
			return (Color)TabStammFarbe.getInhalt("Farbe",iPos);
		else if (iEigFarbe>0)
		{
			TabStammFarbe.addInhalt("Stamm",iStamm);
			String s="select spalte_double from poolview where aic_stamm="+iStamm+" and aic_eigenschaft="+iEigFarbe;
			int i=SQL.getInteger(this,s,-1);
			Color Col=i==-1 ? ColErsatz:new Color(i);
			TabStammFarbe.addInhalt("Farbe",Col);
			return Col;
		}
		else
			return ColAendern;
	}

        /*public void execFile(String sFile,String sArgs)
        {
          try
          {
            String s="\""+sFile+"\""+sArgs;
            fixInfo("Ausführen:"+s);
            Runtime.getRuntime().exec(s);
          }
          catch(IOException io)
          {
                  Static.printError("Global.execFile: "+io);
          }
        }*/

	public void openFile(String sFile)
	{
		if (!sFile.equals("") /*&& new File(sFile).exists()*/)
		{
			try
			{
				String sExt=sFile.substring(sFile.lastIndexOf(".")+1).toUpperCase();
                                if (sFile.startsWith("net send "))
                                {
                                  fixtestInfo("Nachricht:"+sFile.substring(9));
                                  Runtime.getRuntime().exec(sFile);
                                }
				else if (sExt.equals("EXE") || sExt.equals("COM") || sExt.equals("BAT") || sExt.equals("SH"))
                                {
                                  fixtestInfo("exec-Aufruf von:"+sFile);
                                  Runtime.getRuntime().exec("\"" + sFile + "\"");
                                }
                                else
                                  Static.OpenURL(sFile.indexOf(':')==4 ? sFile:"file://"+sFile);
				/*else if(TabDateitypen.posInhalt("ext",sExt))
				{
					while(!TabDateitypen.eof() && TabDateitypen.getS("ext").equals(sExt) && !TabDateitypen.getB("ok"))
					{
                                          //progInfo(TabDateitypen.getS("file")+" | "+sFile);
						if (new File(TabDateitypen.getS("file")).exists())
							TabDateitypen.setInhalt("ok",Boolean.TRUE);
						else
							TabDateitypen.moveNext();
					}
					if(!TabDateitypen.eof() && TabDateitypen.getS("ext").equals(sExt) && TabDateitypen.getB("ok"))
                                        {
                                          String s="\"" + TabDateitypen.getS("file") + "\" \"" + sFile + "\"";
                                          //testInfo("Exec "+s);
                                          Runtime.getRuntime().exec(s);
                                        }
				}
				else
					Static.printError("Keine Datei für die Erweiterung <"+sExt+"> zugeordnet!");*/
				//System.out.println("Erweiterung="+sExt);
				//String sProg=sExt.equals("DOC") ? "WINWORD":sExt.equals("XLS") ? "EXCEL":sExt.equals("PPS") ? "POWERPNT":null;
				//if (sProg != null)

			}catch(IOException io)
			{
				printError("Global.openFile: "+io);//TabDateitypen.getS("Bezeichnung")+" kann nicht geöffnet werden!");
			}
		}
	}

        /*public void setDialog(int iEig,Object Obj)
        {
          int iPos=TabDialog.getPos("Eig",iEig);
          if (iPos>=0)
              TabDialog.setInhalt(iPos,"Obj",Obj);
          else
          {
            TabDialog.addInhalt("Eig",iEig);
            TabDialog.addInhalt("Obj",Obj);
          }
          //if (TestPC())
          //  TabDialog.showGrid();
        }

        public Object getDialog(int iEig)
        {
          int iPos=TabDialog.getPos("Eig",iEig);
          if (iPos<0)
            fixInfo("Lokale Eingabe für "+TabEigenschaften.getBezeichnungS(iEig)+" nicht gefunden");
          Object Obj=iPos>=0 ? TabDialog.getInhalt("Obj",iPos):null;
          //testInfo("getDialog:"+iEig+"="+Obj+"("+Static.className(Obj)+")");
          return Obj;
        }*/

        public void setAmpel(int iEig,int iStamm,int iStatus)
        {
          testInfo("***************** setAmpel von "+iEig+"/"+ iStamm+" <- "+iStatus);
          int iPos=TabAmpel.getPos(iEig,iStamm);
          if (iPos<0)
          {
            iPos=TabAmpel.newLine();
            TabAmpel.setInhalt(iPos,"Eig",iEig);
            TabAmpel.setInhalt(iPos,"Stamm",iStamm);
          }
          TabAmpel.setInhalt(iPos,"Status",iStatus);
        }

        public int getAmpel(int iEig,int iStamm)
        {
          //TabAmpel.showGrid("Ampel");
          int iPos=TabAmpel.getPos(iEig,iStamm);
          int iStatus=iPos<0 ? 0:TabAmpel.getI(iPos,"Status");
          testInfo("***************** getAmpel von "+iEig+"/"+ iStamm+" -> "+iStatus);
          return iStatus;
        }

        public void setBack(JButton Btn,Color col)
        {
          Btn.setBackground(col);
//          if (Static.Java8())
//          {
//            BtnBack=new com.sun.java.swing.plaf.motif.MotifButtonUI();
//            Btn.setUI(BtnBack);
//          }
        }
        
        public int meineFirma()
        {
        	if (iMeineFirma==0)
        		iMeineFirma=SQL.getInteger(this, "select firma from stammview2 where aic_stamm="+iStamm);
        	return iMeineFirma;
        }

        public void checkMeine(String rsJoker,int iEig,String sDatentyp)
        {
          //testInfo("checkMeine:"+rsJoker+"/"+iEig);
          if (TabMeine==null)
          {
            TabMeine = new Tabellenspeicher(this, new String[] {"Kennung", "Satz", "Replace"});
            TabMeine.setTitel("TabMeine");
          }
          int iPos=TabMeine.getPos("Kennung",rsJoker+iEig);
          if (iPos<0)
          {
            //String sDatentyp = TabEigenschaften.getS("datentyp");
            //testInfo("nicht gefunden: Datentyp="+sDatentyp);
            int iSatz=sDatentyp.equals("Firma") ? meineFirma():0;
//            fixtestError("checkMeine "+rsJoker+"/"+sDatentyp+"-> "+iSatz);
            if (sDatentyp.equals("Gruppe") || sDatentyp.equals("BewStamm") || sDatentyp.equals("Firma"))
            {
              if (iSatz==0)
              {
            	  int iPosEig=TabEigenschaften.getPos("Aic",iEig);
                  if (iSttFirma==TabEigenschaften.getI(iPosEig,"aic_stammtyp"))
                	  iSatz=meineFirma();
                  else
                	  iSatz = SQL.getInteger(this, "select sta_aic_stamm from poolview where aic_stamm=" + iStamm + " and aic_eigenschaft=" + iEig);
              }
              TabMeine.addInhalt("Kennung",rsJoker+iEig);
              TabMeine.addInhalt("Satz",iSatz);
              TabMeine.addInhalt("Replace","\\*"+rsJoker.substring(1,rsJoker.length()-1)+"\\*"+iEig);
            }
            else if (sDatentyp.equals("Hierarchie"))
            {
              iSatz=iStamm;
              //TabStammtypen.push();
              if (iSatz==0)
              {
                int iPosEig=TabEigenschaften.getPos("Aic",iEig);
                int iPosStt=TabStammtypen.getPos("Aic",TabEigenschaften.getI(iPosEig,"aic_stammtyp"));
                while (iPosStt>=0)
                {
                  rsJoker="*meine "+TabStammtypen.getS(iPosStt,"Kennung")+"*";
                  TabMeine.addInhalt("Kennung",rsJoker+iEig);
                  TabMeine.addInhalt("Satz",0);
                  TabMeine.addInhalt("Replace","\\*"+rsJoker.substring(1,rsJoker.length()-1)+"\\*"+iEig);
                  iPosStt=TabStammtypen.getPos("Aic",TabStammtypen.getI(iPosStt,"Darunter"));
                }
              }
              else
               while (iSatz>0)
               {
                iSatz = SQL.getInteger(this, "select sta_aic_stamm from poolview where aic_stamm=" + iSatz + " and aic_eigenschaft=" + iEig);
                if (iSatz>0)
                {
                  int iStt=SQL.getInteger(this, "select aic_stammtyp from stamm where aic_stamm=?",0,""+iSatz);
                  int iPosS=TabStammtypen.getPos("AIC",iStt);
                  rsJoker="*meine "+(iPosS>=0 ? TabStammtypen.getS(iPosS,"Kennung"):"")+"*";
                  TabMeine.addInhalt("Kennung",rsJoker+iEig);
                  TabMeine.addInhalt("Satz",iSatz);
                  TabMeine.addInhalt("Replace","\\*"+rsJoker.substring(1,rsJoker.length()-1)+"\\*"+iEig);
                }
               }
              //TabStammtypen.pop();
            }
            else
              printError("checkMeine mit "+sDatentyp+" wird noch nicht unterstützt");

          }
//          TabMeine.showGrid();
        }

        /*public int getMeine(String rsJoker)
        {
          TabMeine.posInhalt("Kennung",rsJoker);
          return TabMeine.getI("Satz");
        }*/

  public JFrame getFomLeer()
  {
    if (FomLeer==null)
      FomLeer=new JFrame();
    return FomLeer;
  }

  public void checkModelle()
  {
    if (TabModelle==null)
    {
      //fixtestError("refresh TabModelle");
      TabModelle = new Tabellenspeicher(this,
          "select aic_modell,begriff.aic_begriff,modell.aic_stamm iPeriode,stammview2.kennung sPeriode,bits,defbezeichnung Bezeichnung,modell.aic_eigenschaft,modell.aic_abfrage,null Abf" +
        		  (Version.getVer()>5150 ? ",modell.Max_B,modell.Farbe":"") +
                                        " from begriff" + join2("modell", "begriff") + " left outer" + join("stammview2", "modell","stamm"), true, "Modelle");
      TabModelle.sAIC="aic_modell";
      TabModelle.setTitel("TabModelle");
      //if (Prog()) TabModelle.showGrid("TabModelle");
    }
  }

  public String getModellBez(int i)
  {
    checkModelle();
    if (i<=0)
      return "<null>";
    int iPos=TabModelle.getPos("aic_modell", i);
    return iPos>=0 ? TabModelle.getS(iPos,"Bezeichnung"):"<???>";
  }
  
  public int getModellBits(int i,boolean bModell)
  {
    checkModelle();
    if (i<=0)
      return 0;
    int iPos=TabModelle.getPos(bModell ? "aic_modell":"aic_begriff", i);
    return iPos>=0 ? TabModelle.getI(iPos,"bits"):0;
  }

  public int BegriffToModell(int i)
  {
    checkModelle();
    if (i<=0)
      return 0;
    int iPos=TabModelle.getPos("aic_begriff", i);
    if (iPos<0)
    {
      printError("Global.BegriffToModell: Begriff "+i+" nicht gefunden!");
      if (Prog()) TabModelle.showGrid("TabModelle");
    }
    return iPos>=0 ? TabModelle.getI(iPos,"aic_modell"):0;
  }

  public int ModellToBegriff(int i)
  {
    checkModelle();
    if (i<=0)
      return 0;
    int iPos=TabModelle.getPos("aic_modell", i);
    if (iPos<0)
    {
      printError("Global.ModellToBegriff: Modell " + i + " nicht gefunden!");
      if (Static.bInfoExcept)
    	printStackTrace(new Exception());
    }
    return iPos>=0 ? TabModelle.getI(iPos,"aic_begriff"):0;
  }

  public void checkAbfragen()
  {
    if (TabAbfragen==null)
    {
      TabAbfragen=new Tabellenspeicher(this,"select DefBezeichnung,abfrage.aic_abfrage,begriff.aic_begriff,begriff.bits,Abfrage.Abits2,begriff.aic_stammtyp,begriff.aic_bewegungstyp Erf,begriff.aic_rolle Rolle"+
                                       ",aic_modell,mod_aic_modell,autorefresh,spalten,anzahl,aic_code,prog"+
                                       ",(select count(aic_bedingung) from bedingung where aic_abfrage=abfrage.aic_abfrage) Bed,abfrage.aic_benutzergruppe,abfrage.aic_benutzer"+
                                       ",(select ein from logging where aic_logging=begriff.aic_logging) last,WebStamm from abfrage"+join("begriff","abfrage"),true,"Abfragen.AU0");
      TabAbfragen.setTitel("TabAbfragen");
      TabAbfragen.sAIC="aic_begriff";
    }
  }
  
  public int BegToAbf(int iBeg)
  {
	  checkAbfragen();
	  int iPos=TabAbfragen.getPos("aic_begriff",iBeg);
	  return iPos<0 ? 0:TabAbfragen.getI(iPos,"aic_abfrage");
  }
  
  public int AbfToBeg(int iAbf)
  {
	  if (iAbf==0)
		  return 0;
	  checkAbfragen();
	  int iPos=TabAbfragen.getPos("aic_abfrage",iAbf);
	  return iPos<0 ? 0:TabAbfragen.getI(iPos,"aic_begriff");
  }
  
  public boolean AbfIsBit2(int iBeg,int iBits2)
  {
	  checkAbfragen();
	  int iPos=TabAbfragen.getPos("aic_begriff",iBeg);
	  return iPos<0 ? false:(TabAbfragen.getI(iPos,"Abits2")&iBits2)>0;
  }

  /*public boolean PME(int iPos)
  {
    return bEnable && iPos>=0 && BerechtigungS(iPos);
  }*/

  public void setMI(JMenuItem Mnu,int iPos,boolean bBP)
  {
    Mnu.setFont(fontPopup);
    if (iPos>=0)
    {
      setTooltip(TabBegriffe.getM(iPos, "Tooltip"), Mnu);
      Mnu.setIcon(LoadImageIcon(iPos));
    }
    if (bBP) // Berechtigungsprüfung
    {
      Mnu.setEnabled(bEnable && iPos >= 0 && BerechtigungS(iPos)); //PME(iPos));
      if(bInfoJeder && !isJederS(iPos)) {
        Mnu.setForeground(ColEF_Bez2);
        defInfo2("Nicht Jeder: " + getBegBez2(iPos));
      }
    }
  }

  public JMenuItem addMenuItem(String sKnopf,JComponent pop,String s)
  {
    if (bInfoEvent)
      fixInfo("addMenuItem: "+(pop instanceof JPopupMenu ? ((JPopupMenu)pop).getLabel()+".":"")+sKnopf+" noch ohne Event");
    return addMenuItem(sKnopf,pop,s,null,"Button");
  }
  
  public JMenuItem addMenuItem(String sKnopf,JComponent pop,String s,ActionListener AL)
  {
	  return addMenuItem(sKnopf,pop,s,AL,"Button");
  }

  public JMenuItem addMenuItem(String sKnopf,JComponent pop,String s,ActionListener AL,String sArt)
  {
    int iPos=getPosBegriff(sArt,sKnopf);
    JMenuItem Mnu = new JMenuItem(iPos<0?sKnopf:getBegBez3(iPos));
    Mnu.setActionCommand(s==null ? sKnopf:s);
    if (iPos<0)
    {
      fixtestInfo("nicht gefunden: "+sArt+" "+ sKnopf);
      if (!Static.bDefShow)
        Mnu.setEnabled(false);
    }
    else
      setMI(Mnu,iPos,true);
    pop.add(Mnu);
    if (AL != null && Mnu.isEnabled())
      Mnu.addActionListener(AL);
    return Mnu;
  }

  public JCheckBoxMenuItem addCbxItem(String sCbx,JPopupMenu pop,boolean b)
  {
    return addCbxItem(sCbx,pop,b,null,null);
  }

  public JCheckBoxMenuItem addCbxItem(String sCbx,JPopupMenu pop,boolean b,String s,ActionListener AL)
  {
    int iPos=getPosBegriff("Checkbox",sCbx);
    JCheckBoxMenuItem Mnu = new JCheckBoxMenuItem(iPos<0?sCbx:getBegBez3(iPos),b);
    /*Mnu.setFont(fontPopup);
    if (iPos>=0)
      setTooltip(TabBegriffe.getM(iPos, "Tooltip"), Mnu);
    Mnu.setEnabled(PME(iPos));*/
    setMI(Mnu,iPos,false);
    pop.add(Mnu);
    if (AL != null)
    {
      Mnu.setActionCommand(s == null ? sCbx : s);
      if (Mnu.isEnabled())
        Mnu.addActionListener(AL);
    }
    return Mnu;
  }

  public JMenu addMenu(String sKnopf,JPopupMenu pop)
  {
    /*int iPos=getPosBegriff("Button",sKnopf);
    JMenu Mnu = new JMenu(iPos<0?sKnopf:getBegBez2(iPos));
    Mnu.setIcon(LoadImageIcon(iPos));
    Mnu.setEnabled(PME(iPos));
    Mnu.setFont(fontPopup);
    if (pop != null)
      pop.add(Mnu);
    return Mnu;*/
    return addMenu(getPosBegriff("Button",sKnopf),sKnopf,pop);
  }

  public JMenu addMenu(int iPos,String sErsatz,JPopupMenu pop)
  {
    JMenu Mnu = new JMenu(iPos<0?sErsatz:getBegBez3(iPos));
    setMI(Mnu,iPos,true);
    /*Mnu.setFont(fontPopup);
    if (iPos<0)
      Mnu.setEnabled(false);
    else
    {
      Mnu.setIcon(LoadImageIcon(iPos));
      Mnu.setEnabled(PME(iPos));
      if (pop != null)
        pop.add(Mnu);
    }*/
    if (pop != null && iPos>=0)
      pop.add(Mnu);
    return Mnu;
  }

  public JMenuItem addMenuItem(String sKnopf,JComponent pop)
  {
    return addMenuItem(sKnopf,pop,sKnopf);
  }
  /*public JMenuItem addMenuItem(String sKnopf,JComponent pop,String s)
  {
    JMenuItem Mnu = new JMenuItem(getBegriffS("Button", sKnopf));
    Mnu.setFont(fontPopup);
    Mnu.setActionCommand(s);
    pop.add(Mnu);
    return Mnu;
  }*/


  public JMenuItem addMenuItem(String sBG,String sKnopf,JPopupMenu pop,String sAlias,ActionListener AL) // nur für DefModell für Checkbox als Buttons
  {
    int iPos=getPosBegriff(sBG, sKnopf);
    JMenuItem Mnu = new JMenuItem(iPos<0?sKnopf:getBegBez3(iPos));
    setMI(Mnu,iPos,false);
    /*JMenuItem Mnu = new JMenuItem(getBegriffS(sBG, sKnopf));
    Mnu.setFont(fontPopup);*/
    pop.add(Mnu);
    if (AL != null)
    {
      Mnu.setActionCommand(sAlias == null ? sKnopf : sAlias);
      if (Mnu.isEnabled())
        Mnu.addActionListener(AL);
    }
    return Mnu;
  }

  public void select(int iAic,AUOutliner Out)
  {
    //fixtestInfo("select "+iAic);
    if (iAic==0)
      return;
    progInfo("select "+iAic);
    Vector Vec = Out.getRootNode().getChildren();
    for (int i = 0; i < Vec.size(); i++)
    {
      JCOutlinerNode Nod = (JCOutlinerNode)Vec.elementAt(i);
      Object Obj=Nod.getUserData();
      int iVg=Obj==null ? 0:Obj instanceof Vector ? Sort.geti(Obj,0):Sort.geti(Obj);
      //fixtestInfo("vorhanden:"+iVg);
      //if (((Integer)((Vector)Nod.getUserData()).elementAt(0)).intValue() == iAic)
      if (iAic==iVg)
        Static.makeVisible(Out, Nod);
    }
  }

  public boolean Mac()
  {
    return bMac;
  }

  public void setAnzahl(JButton Btn,int iAnz)
  {
    Btn.setIconTextGap(1);
    Btn.setFont(fontStandard);
    int iFF=getFontFaktor();
    Btn.setMaximumSize(new java.awt.Dimension(36*iFF/100,Static.bND ? Mal2() ? 48:24:26*iFF/100));
    Btn.setText("<html><sup><font color=\"#FF0000 \">"+iAnz+" </font></sup></html>");
  }

  public void addN(Component C,JPanel Pnl)
  {
    JPanel P=new JPanel(new BorderLayout(2,2));
    P.add("North",C);
    Pnl.add(P);
  }
  
  public void addWCE(JPanel Pnl,Component CW,Component CC,Component CE)
  {
	  JPanel P=new JPanel(new BorderLayout(2,2));
	  if (CW!=null) P.add("West",CW);
	  if (CC!=null) P.add("Center",CC);
	  if (CE!=null) P.add("East",CE);
	  Pnl.add(P);
  }
  
  public JPanel addTwo(Component Cbx1,Component Cbx2)
  {
    JPanel Pnl=new JPanel(new GridLayout(1,0,2,2));
    Pnl.add(Cbx1);
    Pnl.add(Cbx2);
    return Pnl;
  }
  
  public JPanel add3(Component Cbx1,Component Cbx2,Component Cbx3)
  {
    JPanel Pnl=new JPanel(new GridLayout(1,0,2,2));
    Pnl.add(Cbx1);
    Pnl.add(Cbx2);
    Pnl.add(Cbx3);
    return Pnl;
  }
  
  public JPanel add4(Component Cbx1,Component Cbx2,Component Cbx3,Component Cbx4)
  {
    JPanel Pnl=new JPanel(new GridLayout(1,0,2,2));
    Pnl.add(Cbx1);
    Pnl.add(Cbx2);
    Pnl.add(Cbx3);
    Pnl.add(Cbx4);
    return Pnl;
  }

  public JLabel getLabel(String s)
  {
	int iPos=getPosBegriff("Label",s);
	if (iPos>=0)
		s=TabBegriffe.getS(iPos,"Bezeichnung");
//	s=getBegriffS("Label",s);
    JLabel Lbl=new JLabel(s.endsWith(":") ? s:s+":");
    Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
    Lbl.setVerticalAlignment(SwingConstants.CENTER);
    Lbl.setFont(fontBezeichnung);
    Lbl.setForeground(ColBezeichnung);
    if (iPos>=0)
    	Lbl.setName("L"+TabBegriffe.getI(iPos, "Aic"));
    Lbl.setBorder(new EmptyBorder(new Insets(3,1,2,1)));
    if (iPos>=0)
    	setTooltip(TabBegriffe.getM(iPos,"Tooltip"),Lbl);
    return Lbl;
  }

  public JLabel getLabelO(String s)
  {
    JLabel Lbl = new JLabel(s);
    Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
    Lbl.setFont(fontBezeichnung);
    Lbl.setForeground(ColBezeichnung);
    Lbl.setBorder(new EmptyBorder(new Insets(3,1,2,1)));
    return Lbl;
  }

  public void addN(String s,Component C,JPanel Pnl1,JPanel Pnl2)
  {
    addN(getLabel(s),Pnl1);
    addN(C,Pnl2);
  }

  public void changeZA(String s)
  {
    setZA(0,s);
    DateWOD DWvon=new DateWOD(getVon());
    DateWOD DWbis=new DateWOD(getBis());
    //testInfo("changeZA1:"+DWvon+" - "+DWbis);
    DWvon.setTimeZero();
    DWbis.setTimeZero();
    if (s.equals("Tag"))
    {
      //DWbis.tomorrow();
    }
    else if (s.equals("Woche"))
    {
      DWvon.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
      DWbis.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
      //DWbis.nextWeek();
    }
    else if (s.equals("Monat"))
    {
      DWvon.set(Calendar.DAY_OF_MONTH,1);
      DWbis.yesterday();
      DWbis.set(Calendar.DAY_OF_MONTH,1);
      DWbis.nextMonth();
    }
    //testInfo("changeZA2:"+DWvon+" - "+DWbis);
    setVonBis(DWvon.toTimestamp(),DWbis.toTimestamp());
  }

  public void changeZR(String s)
  {
    DateWOD DWvon = new DateWOD(getVon());
    DateWOD DWbis = new DateWOD(getBis());
    String sZeitart=getZA(0);
    if(s.startsWith("ZRplus")) {
      if(s.equals("ZRplus"))
        DWvon.next(sZeitart);
      DWbis.next(sZeitart);
    }
    else {
      DWbis.prev(sZeitart);
      if(s.equals("ZRminus") || DWbis.equals(DWvon))
        DWvon.prev(sZeitart);
    }
    setVonBis(DWvon.toTimestamp(), DWbis.toTimestamp());
  }

  public Date getBeginn()
  {
    if (dtBeginn == null)
    {
      dtBeginn = SQL.getTimestamp(this, "select min(ein) erste,aic_mandant,count(*) Anzahl from logging where aic_mandant>1 group by aic_mandant order by Anzahl desc");
      testInfo("dtBeginn="+dtBeginn);
    }
    return dtBeginn;
  }

 /* public Object getSelf(String s)
  {
    if (TabSelf==null)
      TabSelf=new Tabellenspeicher(this,new String[] {"Kennung","Object"});
    int iPos=TabSelf.getPos("Kennung",s);
    //progInfo("getSelf:"+iPos+", "+(iPos<0 ? "null":Static.print(TabSelf.getInhalt("Object",iPos))));
    return iPos<0 ? null:TabSelf.getInhalt("Object",iPos);
  }

  public void setSelf(String s,Object Obj)
  {
    int iPos=TabSelf.newLine();
    TabSelf.setInhalt(iPos,"Kennung",s);
    TabSelf.setInhalt(iPos,"Object",Obj);
    //TabSelf.showGrid("TabSelf");
  }*/
  
  
  // Thread-Handling für Web
  
  public int startThread(/*Thread thread,Global g,*/int iBeg,int iStamm,int iVB)
  {
    if (TabThread==null)
    {
      String[] sAry=new String[] {"Art","Stamm","ZR","Start", "Ende","Dauer","mom","max","Bemerkung"/*,"Thread","Global"*/,"Aic","del","Calc"};
      TabThread = new Tabellenspeicher(this, sAry);
      TabThread.setTitel("TabThread");
      //for (int i=0;i<sAry.length;i++)
      //  TabThread.setTitel(sAry[i],getBegriffS("Show", sAry[i]));
    }
    int iPosB=TabBegriffe.getPos("Aic",iBeg);
    int iPos=TabThread.newLine();
    TabThread.setInhalt(iPos,"Aic",iBeg);
    if (iPosB>=0)
    {
      TabThread.setInhalt(iPos, "Art", TabBegriffgruppen.getBezeichnungS(TabBegriffe.getI(iPosB, "Gruppe")));
    }
    TabThread.setInhalt(iPos, "Stamm",iStamm);
    TabThread.setInhalt(iPos, "ZR",getVB(iVB));
    TabThread.setInhalt(iPos,"Start",new java.sql.Timestamp(new Date().getTime()));
    return iPos;
  }
  
  public void maxThread(int iPos,int iMax)
  {
    if (TabThread==null)
      return;
//    int iPos=TabThread.getPos("Thread",Thread.currentThread());
    if (iPos>=0 && iMax>0)
    {
      TabThread.setInhalt(iPos,"max", iMax);
      TabThread.setInhalt(iPos,"mom",null);
    }
  }
  
  public void momThread(int iPos, int iMom,String s)
  {
    if (TabThread==null)
      return;
    if (iPos>=0 && (iMom>=0 || iMom==-2))
    {
      int iMax=TabThread.getI(iPos,"max");
      if (iMax>0)
      {
        //int iProz=TabThread.getI(iPos,"mom")*100/iMax;
        if (iMom==-2)
          iMom=TabThread.isNull(iPos,"mom") ? 0:TabThread.getI(iPos,"mom")+1;
        TabThread.setInhalt(iPos, "Bemerkung", s);
        TabThread.setInhalt(iPos, "mom", iMom);
      }
    }
  }
  
  public void endThread(int iPos,String sFehler,boolean bOk,long lClock2)
  {
    if (iPos>=0)
    {
      TabThread.setInhalt(iPos, "Ende", new java.sql.Timestamp(new Date().getTime()));
      TabThread.setInhalt(iPos, "Dauer",Static.get_ms()-lClock2);
        TabThread.setInhalt(iPos, "mom", TabThread.getI(iPos,"max"));
        TabThread.setInhalt(iPos, "Bemerkung", Static.beiLeer(sFehler,"#fertig"));
        TabThread.setInhalt(iPos,"del",bOk);// || Static.Leer(sFehler));
    }
  }
  
  // Thread in Tabellenspeicher-Handling

  public void startThread(Thread thread,Global g,int iBeg)
  {
    if (TabThread==null)
    {
      String[] sAry=new String[] {"Art","Bez","von","bis","Start", "Ende","mom","max","Bemerkung","Thread","Global","Aic","del"};
      TabThread = new Tabellenspeicher(this, sAry);
      TabThread.setTitel("TabThread");
      //for (int i=0;i<sAry.length;i++)
      //  TabThread.setTitel(sAry[i],getBegriffS("Show", sAry[i]));
    }
    int iPosB=TabBegriffe.getPos("Aic",iBeg);
    int iPos=TabThread.newLine();
    TabThread.setInhalt(iPos,"Aic",iBeg);
    TabThread.setInhalt(iPos,"Thread",thread);
    TabThread.setInhalt(iPos,"Global",g);
    if (iPosB>=0)
    {
      TabThread.setInhalt(iPos, "Art", TabBegriffgruppen.getBezeichnungS(TabBegriffe.getI(iPosB, "Gruppe")));
      TabThread.setInhalt(iPos, "Bez", getBegBez2(iPosB));
    }
    TabThread.setInhalt(iPos, "von", getVon());
    TabThread.setInhalt(iPos, "bis", getBis());
    TabThread.setInhalt(iPos,"Start",new java.sql.Timestamp(new Date().getTime()));
    showThreads();
    FomThread.toFront();
  }
  
  public boolean runThread(int iBeg)
  {
    if (TabThread==null)
      return false;
    int iPos=TabThread.getPos("Aic",iBeg);
    //fixInfo("iPos="+iPos+" ("+iBeg+")");
    while (iPos>=0 && !TabThread.isNull(iPos,"Ende"))
      iPos = TabThread.getNextPos(iPos, "Aic", iBeg);
    return iPos>=0;
  }

  public void maxThread(int iMax)
  {
    if (TabThread==null)
      return;
    int iPos=TabThread.getPos("Thread",Thread.currentThread());
    if (iPos>=0 && iMax>0)
    {
      TabThread.setInhalt(iPos, "max", iMax);
      TabThread.setInhalt(iPos,"mom",null);
    }
  }

  public void momThread(int iMom,String s)
  {
    if (TabThread==null)
      return;
    int iPos=TabThread.getPos("Thread",Thread.currentThread());
    if (iPos>=0 && (iMom>=0 || iMom==-2))
    {
      int iMax=TabThread.getI(iPos,"max");
      if (iMax>0)
      {
        //int iProz=TabThread.getI(iPos,"mom")*100/iMax;
        if (iMom==-2)
          iMom=TabThread.isNull(iPos,"mom") ? 0:TabThread.getI(iPos,"mom")+1;
        TabThread.setInhalt(iPos, "Bemerkung", s);
        TabThread.setInhalt(iPos, "mom", iMom);
        //if (iMom*100/iMax>iProz)
          showThreads();
      }
    }
  }


  /*public void prozThread(Thread thread,int iProz)
  {
    int iPos=TabThread.getPos("Thread",thread);
    if (iPos>=0 && TabThread.getI(iPos,"Proz")<iProz)
    {
      TabThread.setInhalt(iPos, "Proz", iProz);
      showThreads(false);
    }
  }*/

  public void zrThread(Thread thread)
  {
    int iPos=TabThread.getPos("Thread",thread);
    if (iPos>=0)
    {
      TabThread.setInhalt(iPos, "von", getVon());
      TabThread.setInhalt(iPos, "bis", getBis());
      showThreads();
    }
  }

  public void endThread(Thread thread)
  {
    int iPos=TabThread.getPos("Thread",thread);
    if (iPos>=0)
    {
      TabThread.setInhalt(iPos, "Ende", new java.sql.Timestamp(new Date().getTime()));
      Global g=(Global)TabThread.getInhalt("Global",iPos);
      if (g!= null && g.bThreadEscape)
        TabThread.setInhalt(iPos, "Bemerkung", "#abgebrochen");
      else
      {
        TabThread.setInhalt(iPos, "mom", -1); //TabThread.getI(iPos,"max"));
        TabThread.setInhalt(iPos, "Bemerkung", "#fertig");
      }
    }
    showThreads();
  }

  private void aktiveThreads(AUOutliner Gid)
  {
    //Tabellenspeicher TabAT=new Tabellenspeicher(this,new String[] {"Art","Bezeichnung","Zeitraum","Bemerkung","Prozent","seit","abgeschlossen","FARBE"});
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Gid.getRootNode();
    NodeRoot.removeChildren();
    if (TabThread != null)
    {
      String[] s=new String[] {"Art","Bezeichnung","Zeitraum","Bemerkung","Prozent","seit","abgeschlossen"};
      for (int i=0;i<s.length;i++)
              s[i]=getBegriffS("Show", s[i]);
      Gid.setColumnButtons(s);
      Gid.setNumColumns(s.length);
      Gid.setRootVisible(false);

      for (int i = TabThread.size() - 1; i >= 0; i--)
      if (TabThread.isNull(i,"del"))
      {
        Vector<Object> VecSpalten=new Vector<Object>();
        //int iPos = TabAT.newLine();
        VecSpalten.addElement(TabThread.getS(i, "Art"));
        VecSpalten.addElement(TabThread.getS(i, "Bez"));
        VecSpalten.addElement(new VonBis(this,TabThread.getTimestamp(i, "von"), TabThread.getTimestamp(i, "bis"), "dd.MM.yyyy"));
        String sBem=TabThread.getS(i, "Bemerkung");
        VecSpalten.addElement(sBem.startsWith("#") ? getBegriffS("Show",sBem.substring(1)):sBem);
        int iMax = TabThread.getI(i, "max");
        if (iMax > 0)
          VecSpalten.addElement(TabThread.getI(i, "mom")<0 ? 100:Math.min(TabThread.getI(i, "mom") * 100 / iMax,99));
        else
          VecSpalten.addElement(null);
        VecSpalten.addElement(new Zeit(TabThread.getDate(i, "Start"), "HH:mm:ss"));
        VecSpalten.addElement(new Zeit(TabThread.getDate(i, "Ende"), "HH:mm:ss"));
        JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
        Node.setUserData(TabThread.getInhalt("Thread",i));
        //if (!TabThread.isNull(i, "Ende"))
        //{
          JCOutlinerNodeStyle Sty=new JCOutlinerNodeStyle();
          Sty.setForeground(TabThread.isNull(i, "Ende") ? ColStandard:ColLoeschen);
          Node.setStyle(Sty);
        //}
        //TabAT.setInhalt(iPos, "FARBE", TabThread.isNull(i, "Ende") ? Color.BLACK.getRGB() : Color.RED.getRGB());
      }
      Gid.folderChanged(NodeRoot);
    }
    //TabAT.setPos(-1);
    //return TabAT;
  }

  @SuppressWarnings("deprecation")
  public void killThreads()
  {
    if (TabThread != null)
     for(int i=0;i<TabThread.size();i++)
      if (TabThread.isNull(i,"Ende"))
      {
        TabThread.setInhalt(i, "Ende", new java.sql.Timestamp(new Date().getTime()));
        ((Thread)TabThread.getInhalt("Thread",i)).stop();
      }
    if (FomThread != null)
      FomThread.setVisible(false);
  }

  private void cleanThreads()
  {
    if (TabThread != null)
     for(int i=0;i<TabThread.size();i++)
      if (!TabThread.isNull(i,"Ende") && TabThread.isNull(i,"del"))
        TabThread.setInhalt(i,"del", new java.sql.Timestamp(new Date().getTime()));
    showThreads();
  }

  public boolean ModellThread()
  {
    if (TabThread != null)
      for(int i=0;i<TabThread.size();i++)
        if (TabThread.isNull(i, "Ende") && TabThread.getS(i,"Art").equals("Modell"))
          return true;
    return false;
  }

  public boolean SonstThread()
  {
    if (TabThread != null)
      for(int i=0;i<TabThread.size();i++)
        if (TabThread.isNull(i, "Ende"))
          return true;
    return false;
  }

  private void showThreads()
  {
    if (FomThread==null)
    {
      FomThread = new JFrame(getBegriffS("Dialog", "Thread"));
      GidThread = new AUOutliner(new JCOutlinerFolderNode(""));
      /*GidThread.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
              if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
              {
                Thread Thr=(Thread)GidThread.getSelectedNode().getUserData();
                int iPos=TabThread.getPos("Thread",Thr);
                if (iPos>=0)
                {
                  if (TabThread.isNull(iPos,"Ende") && !TabThread.getS(iPos,"Art").equals("Modell"))
                  {
                    Thr.stop();
                    TabThread.setInhalt(iPos, "Ende", new java.sql.Timestamp(new Date().getTime()));
                    TabThread.setInhalt(iPos, "Bemerkung","abgebrochen");
                    showThreads();
                  }
                }
              }
          }
          public void mouseEntered(MouseEvent ev) {}
          public void mouseExited(MouseEvent ev)  {}
          public void mouseReleased(MouseEvent ev){}
        });*/
        ActionListener AL=new ActionListener()
             {
               @SuppressWarnings("deprecation")
               public void actionPerformed(ActionEvent ev)
               {
                 String s = ((JButton)ev.getSource()).getActionCommand();
                 if (s.equals("wait") || s.equals("cancel"))
                 {
                   Thread Thr = (Thread)GidThread.getSelectedNode().getUserData();
                   int iPos = TabThread.getPos("Thread", Thr);
                   //int iArt1=0;
                   //int iArt2=0;
                   if (iPos >= 0)
                   {
                     Global g=(Global)TabThread.getInhalt("Global",iPos);
                     //if (g!=null) iArt1=g.bThreadEscape ? 1:2;
                     if (s.equals("cancel") && g!= null)
                       g.bThreadEscape=true;
                     //if (g!=null) iArt2=g.bThreadEscape ? 1:2;
                     if (s.equals("wait") || TabThread.isNull(iPos, "Ende") && !TabThread.getS(iPos, "Art").equals("Modell"))
                     {
                       if (s.equals("cancel"))
                       {
                         Thr.stop();
                         TabThread.setInhalt(iPos, "Ende", new java.sql.Timestamp(new Date().getTime()));
                         TabThread.setInhalt(iPos, "Bemerkung", "#abgebrochen");
                       }
                       else
                       {
                         TabThread.setInhalt(iPos, "Bemerkung", "#wait");
                         Thread.yield();
                       }
                       showThreads();
                     }
                   }
                   //fixInfo(s+"->"+(iArt1==0?"null":iArt1==1?"true":"false")+" auf "+(iArt2==0?"null":iArt2==1?"true":"false"));
                 }
                 else if (s.equals("clean"))
                   cleanThreads();
                 else if (s.equals("end"))
                   FomThread.setVisible(false);
               }
              };
      JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,3,2));
      //PnlS.add(getButton("Wait", "wait",AL));
      PnlS.add(getButton("Clear", "clean",AL));
      PnlS.add(getButton("Abbruch", "cancel",AL));
      PnlS.add(getButton("Beenden","end",AL));
      FomThread.getContentPane().add("Center",GidThread);
      FomThread.getContentPane().add("South",PnlS);
      getFenster(FomThread,getBegriffAicS("Dialog", "Thread"),false,0,0,500,300,100); // !100!
      FomThread.setVisible(true);
    }
    //if (b || FomThread.isFocused())
//    fixtestError("showThreads: font-Size="+Static.fontStandard.getSize());
//    GidThread.setFont(Static.fontStandard);
    aktiveThreads(GidThread);
    if (!SonstThread())
    {
      //FomThread.setVisible(false);
      progInfo("fertig:"+BtnDruck);
      if (BtnDruck!= null && !BtnDruck.isEnabled())
      {
        BtnDruck.setEnabled(true);
        progInfo("enabled");
      }
    }
    /*else
    {*/
      //long lTimeNew=new Date().getTime();
      //if (b || lTimeNew>lTime+5000)
      //{
        //lTime=lTimeNew;
        if(!FomThread.isVisible())
          FomThread.setVisible(true);
        //Tab.showGrid(GidThread);
      //}
    //}
  }

  public void freeThreads()
  {
    if (FomThread != null)
    {
      FomThread.setVisible(true);
      setFenster(FomThread, getBegriffAicS("Dialog", "Thread"),100); // !100!
      killThreads();
      if (FomThread!=null)
      {
        FomThread.dispose();
        FomThread = null;
      }
      if (TabThread != null)
        TabThread.clearAll();
    }
  }

  public void ZeitMem(String s,long lClock)
  {
    debugInfo(s+", Zeit="+(Static.get_ms()-lClock));
    diskInfo(s+Static.Mem(TestPC()));
  }

  private void posVar(String sVar)
  {
    if (TabVar==null)
      TabVar = new Tabellenspeicher(this,new String[] {"Var","Wert","Stt","Art","perm","Logging","Global"});
    if (!TabVar.posInhalt("Var",sVar))
    {
     TabVar.newLine();
     TabVar.setInhalt("Var",sVar);
    }
  }

  public void setVar(String sVar,Object Obj)
  {
    posVar(sVar);
    TabVar.setInhalt("Wert",Obj);
  }
  
  public String getVarSQL(String sVar)
  {
	  int iPos=TabVar==null ? -1:TabVar.getPos("Var", sVar);
	  if (iPos<0)
		  return "is null";
	  else
	  {
		Object Obj=  TabVar.getInhalt("Wert",iPos);
		if (Obj==null)
			return "is null";
		else if (Obj instanceof String)
			return "like '"+TabVar.getS(iPos,"Wert")+"'";
		else if (Obj instanceof Integer)
			return "="+TabVar.getI(iPos,"Wert");
		else if (Obj instanceof Vector)
			return SQL_in((Vector)Obj);
		else
		{
			printError("Fehler bei getVarSQL mit "+sVar+": "+Static.print(Obj));
			return "xx";
		}
	  }
  }
  
  public void setTab(Tabellenspeicher TabAC)
  {
//	fixtestError("setTab:"+TabAC.getI("Begriff"));
	if (TabGAbf==null)
		  TabGAbf=new Tabellenspeicher(this,new String[] {"Aic","Stamm","von","bis","Tab","pos"});
	int iPos=TabGAbf.getPos("Aic",TabAC.getI("Begriff"));
	if (iPos<0)
	{
		iPos=TabGAbf.newLine(-1);
		TabGAbf.setInhalt(iPos,"Aic",TabAC.getI("Begriff"));
	}
	TabGAbf.setInhalt(iPos,"Stamm",TabAC.getI("Stamm"));
	TabGAbf.setInhalt(iPos,"von",TabAC.getDate("von"));
	TabGAbf.setInhalt(iPos,"bis",TabAC.getDate("bis"));
	Tabellenspeicher Tab=(Tabellenspeicher)TabAC.getInhalt("Abfrage"+(TabAC.isNull("Filter") ? "1":"2"));
	TabGAbf.setInhalt(iPos,"Tab",Tab);
//	fixtestError(" Pos="+Tab.getPos()+" von "+Tab.size());
	TabGAbf.setInhalt(iPos,"pos",Tab.getPos());
  }
  
  public void clearTab(int iBegriff)
  {
	  if (TabGAbf != null)
	  {
		  int iPos=TabGAbf.getPos("Aic",iBegriff);
		  if (iPos>=0)
		  {
			  TabGAbf.setInhalt(iPos,"Aic",-iBegriff);
			  TabGAbf.setInhalt(iPos,"Tab",null);
		  }
	  }
  }
  
  public Tabellenspeicher getTab(Tabellenspeicher TabAC)
  {
//	  fixtestError("getTab:"+TabAC.getI("Begriff"));
	  if (TabGAbf==null)
		  return null;
//	  if (Prog())
//		  TabGAbf.showGrid("TabGAbf");
	  int iPos=TabGAbf.getPos("Aic",TabAC.getI("Begriff"));
	  if (iPos>=0)
	  {
		  TabAC.setInhalt("Stamm",TabGAbf.getI(iPos,"Stamm"));
		  TabAC.setInhalt("von",TabGAbf.getDate(iPos,"von"));
		  TabAC.setInhalt("bis",TabGAbf.getDate(iPos,"bis"));
		  Tabellenspeicher Tab=((Tabellenspeicher)TabGAbf.getInhalt("Tab",iPos));//.showGrid("Tab"+iBeg);
		  TabAC.setInhalt("Abfrage"+(TabAC.isNull("Filter") ? "1":"2"),Tab);
		  iPos=TabGAbf.getI(iPos,"pos");
		  if (Tab.getPos() != iPos)
		  {
			  fixtestError("getTab:"+TabAC.getI("Begriff")+"! Pos="+Tab.getPos()+" statt "+iPos);
			  Tab.setPos(iPos);
		  }
//		  else
//			  fixtestError(" Pos="+Tab.getPos()+" von "+Tab.size());
		  return Tab;
	  }
	  else
		  return null;
  }

  /*public void checkError(boolean bExit)
  {
    if (Static.DirError != null)
    {
      File Fil=new File(Static.ErrorFile());
      if (Fil != null)
        Fil=Fil.getParentFile();
      if (Fil==null || !Fil.exists())
      {
        JOptionPane.showMessageDialog(new JFrame(), "Das Fehlerverzeichnis ist falsch!", "Fehler",JOptionPane.WARNING_MESSAGE);
        if (bExit)
        {
          unConnect();
          System.exit(0);
        }
      }
    }
  }*/
  
  // Java-FX-Anfang
//  public javafx.scene.image.Image getImageFX(Tabellenspeicher Tab,int iStt)
//  {
//		int iPos=Tab.getPos("Aic", iStt);
//		if (iPos>=0 && !Tab.isNull(iPos,"BildFX"))
//		{
//			String s=Tab.getS(iPos,"BildFX");
//			if (Static.Leer(s))
//				return null;
//			//g.fixtestInfo("getImage bei "+sKennung+":"+s);
//			return LoadImageFx(s,Static.DirImageSys);//new Image(Static.DirImageSys+s);
//		}
//		return null;
//  }
//  
//  public javafx.scene.image.Image getImageBeg(int iPos)
//  {
//	  return getImageBeg(iPos,iSpFX);
//  }
//  
//  public javafx.scene.image.Image getImageBeg(int iPos,int iArt)
//  {
//	  String sBild=iPos<0 ? null:TabBegriffe.getS(iPos,iArt==iSpFX ? "BildFX":iArt==iZSel ? "BildSel":"Bildxx");
//	  return getFxImage(sBild);
//  }
//  
//  public javafx.scene.image.Image getFxImage(String sBild)
//  {
//	  if (sBild!=null && sBild.contains("."))
//		  return (javafx.scene.image.Image)LoadImage(sBild,Static.DirImageSys,true);
//	  else
//		  return null;
//  }
  
//  public ImageView getImgBeg(int iPos)
//  {
//	  return getImgBeg(iPos,iSpFX);
//  }
//  
//  public ImageView getImgBeg(int iPos,int iArt)
//  {
//	  javafx.scene.image.Image image=getImageBeg(iPos,iArt);
//	  return image==null ? null:new ImageView(image);
//  }
//  
//  public ImageView getImgBG(String sBG)
//  {
//	  int iPos=TabBegriffgruppen.getPos("Kennung",sBG);
//	  String sBild=iPos<0 ? null:TabBegriffgruppen.getS(iPos,"BildFX");
//	  //fixtestError("getImgBG "+sBG+": Pos="+iPos+" ->"+sBild);
//	  javafx.scene.image.Image image=getFxImage(sBild);
//	  return image==null ? null:new ImageView(image);
//  }
//  
//  public ImageView getIV_Eig(int iEig)
//  {
//	  String sBild=SQL.getString(this, "select Filename from Daten_Bild where aic_tabellenname="+TabTabellenname.getAic("EIGENSCHAFT")+" and aic_fremd=? and aic_zustand="+iSpFX, ""+iEig);
//	  javafx.scene.image.Image image=getFxImage(sBild);
//	  return image==null ? null:new ImageView(image);
//  }
//  
//  public Button getBtn(String sKennung)
//  {
//	  int iPos=getPosBegriff("Button", sKennung);
//	  Button Btn= iPos<0 ? new Button(sKennung):getBtn("Button",iPos,0);
//	  return Btn;
//  }
//  
//  public Button getBtn(String sGruppe,String sKennung)
//	{
//		return getBtn(sGruppe,sKennung,null,null,0);
//	}
//  
//  public Button getBtn(String sGruppe,String sKennung,String sAlias,EventHandler EH)
//  {
//	  return getBtn(sGruppe,sKennung,sAlias,EH,0);
//  }
//  
//  public Button getBtn(String sGruppe,String sKennung,String sAlias,EventHandler EH,int iTyp)
//  {
//	  int iPos=getPosBegriff(sGruppe, sKennung);
//	  Button Btn= getBtn2(sGruppe,iPos,iTyp);
//	  Btn.setId(sAlias==null ? iPos<0 ?sKennung:getBegKennung(iPos):sAlias);
//	  if (EH != null)
//		  Btn.setOnAction(EH);
//	  return Btn;
//  }
//	
//	public Button getBtn(String sGruppe,int iPos,int iTyp) //Typ=0..normal, 1..in Dialog, 2..Eingabe-*, 3..Tabelle
//	{
//		//int iPos=g.getPosBegriff("Button", sKennung);
//		String sText=iPos<0 ? "Btn_Hugo":getBegBez3(iPos);
//		String sTT=iPos<0 ? null:TabBegriffe.getS(iPos,"Tooltip");
//		Button Btn=new Button(sText);
//		if (iPos<0)
//			return Btn;
//		//!!! Btn.setButtonType(iTyp==0 ? ButtonType.RAISED:ButtonType.FLAT);
//		//Btn.setRipplerFill(Color.ORANGE);
//		Btn.setId(getBegKennung(iPos));//TabBegriffe.getS(iPos,"Kennung"));
//		//g.fixtestInfo("add Btn:"+sText+"/"+sGruppe);
//		ImageView IV=checkImgSel(iPos,Btn);
//		if (iPos>=0 && sTT.trim().equals("") && (iTyp==3 || IV!=null && !TabBegriffe.getB(iPos,"Combo")))
//			sTT=sText;
//		setTooltip(Btn,sTT);
//		if (IV==null && sGruppe!=null && (sGruppe.equals("Modell") || sGruppe.equals("Druck")))
//		    	IV=getImgBG(sGruppe);
//		if (IV != null)
//		{
//			  //g.fixtestInfo("setImage:"+sBild+" für "+sText);
//			  Btn.setGraphic(IV);
//			  if (sGruppe.equals("Modell") && (TabBegriffe.getI(iPos,"Bits")&cstFxCombo)==0 || !sGruppe.equals("Modell") && !TabBegriffe.getB(iPos,"Combo"))
//				Btn.setText("");
//		}
//		if(sGruppe.equals("Web") && !TabBegriffe.getS(iPos,"URL").equals(""))
//		{
//			  final String sURL = TabBegriffe.getS(iPos,"URL");
//			  Btn.setOnAction((event) -> { OpenURL(sURL); });
//		}
//		/*if(TabBegriffe.getInhalt("HK",iPos) != null && TabBegriffe.getS(iPos,"HK").length()>0)
//        {
//          fixtestInfo("Hotkey für "+TabBegriffe.getS("DefBezeichnung")+":"+TabBegriffe.getS("HK"));
//          Btn.setAccelerator(new KeyCodeCombination(KeyCode.valueOf(TabBegriffe.getS(iPos,"HK")), KeyCombination.CONTROL_DOWN));//setMnemonic(TabBegriffe.getS(iPos,"HK").charAt(0));
//          //Btn.addMnemonic(arg0);
//          //Btn.setUI(BtnTT);
//        }*/
//		String sKennzeichen=iPos<0 ? null:TabBegriffe.getS(iPos,"Kennzeichen");
//		//Btn.getStylesheets().clear();
//		//if (!Static.Leer(sKennzeichen))
//		{
//			Btn.getStyleClass().add(Static.beiLeer(sKennzeichen,sGruppe!=null && (sGruppe.equals("Modell") || sGruppe.equals("Druck")) ? sGruppe:iTyp==0 ? "BtnDefault":iTyp==1 ? "BtnDialog":iTyp==2 ? "BtnEingabe":iTyp==3 ? "BtnTable":"BtnSonst"));
//			if (Static.bShowStyle)
//	    		fixInfo("Style-Btn "+sText+"="+Btn.getStyleClass());
////			else if (!Static.Leer(sKennzeichen))// || iTyp>0)
////				styleInfo(true,"Btn "+sText,Btn);
//		}
////		if (sGruppe!=null && (sGruppe.equals("Modell") || sGruppe.equals("Druck")))
////			fixtestError("Btn "+sText+":"+Btn.getText()+"/"+Btn.getGraphic());
//		//Btn.getStyleClass().add(Static.beiLeer(sKennzeichen,"BtnDefault"));
//		//if (sStyleBtn != null && sStyle==null)
//		//	Btn.getStylesheets().add(sStyleBtn);
//		return Btn;
//	}
//	
//	private ImageView checkImgSel(int iPos,Button Btn)
//	{
//		ImageView IV=getImgBeg(iPos,iSpFX);
//		if (IV != null)
//		{
//			ImageView IV2=getImgBeg(iPos,iZSel);
//			if (IV2!=null)
//			{
//				fixtestError("Btn "+Btn+" hat 2 Bilder");
//				if (TabBtnSel==null)
//					TabBtnSel=new Tabellenspeicher(this, new String[] {"Pos","Btn","IV1","IV2","mom"});
//				TabBtnSel.addInhalt("Pos",iPos);
//				TabBtnSel.addInhalt("Btn",Btn);
//				TabBtnSel.addInhalt("IV1",IV);
//				TabBtnSel.addInhalt("IV2",IV2);
//				TabBtnSel.addInhalt("mom",1);
////				Btn.setOnAction((event) -> {
////					
////				});
//			}
//		}
//		return IV;
//	}
//	
//	public void CheckEvent(ButtonBase Btn)
//	{
//		int iPos2=TabBtnSel.getPos("Btn",Btn);
//		if (iPos2<0)
//			return;
//		int i=3-TabBtnSel.getI(iPos2,"mom");
//		fixtestError("Btn "+Btn+" nun auf Bild "+i);
//		TabBtnSel.setInhalt(iPos2, "mom", i);
//		Btn.setGraphic((ImageView)TabBtnSel.getInhalt("IV"+i,iPos2));
//	}
//	
//	public void setStyle(Button Btn,String s)
//	{
//		Btn.getStyleClass().clear();
//		Btn.getStyleClass().addAll("button","Btn"+s);
////		styleInfo(true,"Btn "+Btn.getId(),Btn);
//	}
//	
//	public Button getBtnE(String sKennung)
//	{
//		int iPos=getPosBegriff("Button", sKennung);
//		Button Btn= iPos<0 ? new JFXButton(sKennung):getBtn("Button",iPos,2);
//		Btn.setFocusTraversable(false);
//		Btn.setPadding(new javafx.geometry.Insets(1,2,0,2));
//		Btn.setAlignment(Pos.BOTTOM_LEFT);
//		//Btn.getStyleClass().add("EdtStd");
//		return Btn;
//	}
//	
//	public Button getBtn2(String sGruppe,int iPos,int iType)
//	{
//		Button Btn=getBtn(sGruppe,iPos,iType);
//		Btn.setBorder(null);
//		//Btn.setButtonType(ButtonType.RAISED);
//		if (Btn.getGraphic()!=null)
//			Btn.setText(null);
//		return Btn;
//	}
//	
//	public ToggleButton getTBtn(String sKennung)
//	{
//		return getTBtn(getPosBegriff("Button", sKennung),sKennung);
//	}
//	
//	public ToggleButton getTBtn(int iPos,String sKennung)
//	{
//		String sText=iPos<0 ? sKennung:getBegBez3(iPos);
//		ImageView IV=getImgBeg(iPos);
//		//String sBild=iPos<0 ? null:TabBegriffe.getS(iPos,"BildFX");
//		String sTT=iPos<0 ? null:TabBegriffe.getS(iPos,"Tooltip");
//		ToggleButton Btn=new ToggleButton();
//		Btn.setBorder(null);
//		if (iPos<0 || sTT.trim().equals("")) sTT=sText;
//		setTooltip(Btn,sTT);
//		String sKennzeichen=iPos<0 ? null:TabBegriffe.getS(iPos,"Kennzeichen");
//		if (!Static.Leer(sKennzeichen)) Btn.getStyleClass().add(sKennzeichen);	
//		if (IV!=null)//sBild!=null && sBild.contains("."))
//		{
//			//javafx.scene.image.Image image=new javafx.scene.image.Image(Static.DirImageSys+sBild);
//			//if (image != null)
//			   Btn.setGraphic(IV);//new ImageView(image));
//		}
//		else
//			Btn.setText(sText);
//		return Btn;
//	}
//	
//	private Label getLbl(String sKennung)
//	{
//		return getLbl(getPosBegriff("Show", sKennung),true);
//	}
//	
//	public Label getLbl(int iPos,boolean b)
//	{
//		//int iPos=getPosBegriff("Show", sKennung);
//		Label Lbl=new Label(iPos<0 ? "Lbl_Hugo":getBegBez3(iPos));//+":"); // Doppelpunkt am 12.3.2018 für Button entfernt
//		String sTT=iPos<0 ? null:TabBegriffe.getS(iPos,"Tooltip");
//		if (iPos>=0 && !sTT.trim().equals(""))
//			setTooltip(Lbl,sTT);
//		String sKennzeichen=iPos<0 ? null:TabBegriffe.getS(iPos,"Kennzeichen");
//		if (!Static.Leer(sKennzeichen)) Lbl.getStyleClass().add(sKennzeichen);	
//		return Lbl;
//	}
//	
//	public Label getLblO(String s)
//	  {
//	    Label Lbl = new Label(s);
//	    Lbl.setAlignment(Pos.BASELINE_RIGHT);
//	    //Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
//	    //Lbl.setFont(fontBezeichnung);
//	    //Lbl.setForeground(ColBezeichnung);
//	    //Lbl.setBorder(new EmptyBorder(new Insets(3,1,2,1)));
//	    return Lbl;
//	  }
//	
//	public void addLbl(GridPane Pnl,int iZeile,String sKennung)
//	{
//		Pnl.add(getLbl(sKennung),0,iZeile);
//	}
	
//	public JFXCheckBox getCbx(String sKennung)
//	{
//		return getCbx(sKennung,false);
//	}
//	
//	public JFXCheckBox getCbx(String sKennung,boolean b)
//	{
//		int iPos=getPosBegriff("Checkbox", sKennung);
//		JFXCheckBox Cbx= iPos<0 ? new JFXCheckBox(sKennung):getCbx(iPos);
//		Cbx.setSelected(b);
//		return Cbx;
//	}
//	
//	public JFXCheckBox getCbx(int iPos)
//	{
//		String sText=iPos<0 ? "Cbx_Hugo":getBegBez3(iPos);
//		String sTT=iPos<0 ? null:TabBegriffe.getS(iPos,"Tooltip");
//		JFXCheckBox Cbx=new JFXCheckBox(sText);
//		//Cbx.setAlignment(Pos.BASELINE_LEFT);
//		setTooltip(Cbx,sTT);
//		String sKennzeichen=iPos<0 ? null:TabBegriffe.getS(iPos,"Kennzeichen");
//		if (!Static.Leer(sKennzeichen)) Cbx.getStyleClass().add(sKennzeichen);	
//		return Cbx;
//	}
//	
//	public RadioButton getRad(int iPos)
//	{
//		String sText=iPos<0 ? "Rad_Hugo":getBegBez3(iPos);
//		String sTT=iPos<0 ? null:TabBegriffe.getS(iPos,"Tooltip");
//		RadioButton Rad=new RadioButton(sText);
//		setTooltip(Rad,sTT);
//		String sKennzeichen=iPos<0 ? null:TabBegriffe.getS(iPos,"Kennzeichen");
//		if (!Static.Leer(sKennzeichen)) Rad.getStyleClass().add(sKennzeichen);	
//		//Rad.getStyleClass().add(Static.beiLeer(sKennzeichen, "RadDefault"));
//		/*if (sTT!=null && !sTT.trim().equals(""))
//		{
//			Tooltip tooltip=new Tooltip(g.toHtml(sTT));
//			Tooltip.install(Rad, tooltip);
//		}*/
//		return Rad;
//	}
	
//	public String getBegKennung(int iPos)
//	{
//		String s=Static.beiLeer(TabBegriffe.getS(iPos,"Alias"), TabBegriffe.getS(iPos,"Kennung"));
//		//fixtestInfo("getBegKennung von "+TabBegriffe.getS(iPos,"DefBezeichnung")+":"+s);
//		return s;
//	}
//	
//	public static WebView getWebView(String s,boolean bKlein)
//	{
////		WebView web=new WebView();
////		web.setMaxWidth(bKlein ? 250:340);
////		web.setMaxHeight(bKlein ? 150:190);
////		if (Static.sDefaultStyle != null)
////		{
////		  web.getStylesheets().add(Static.sDefaultStyle);
////		  web.getStyleClass().add("message");
////		}
////		WebEngine webEngine = web.getEngine();
////		webEngine.loadContent(s);
//		return setWebView(new WebView(),s,bKlein);
//	}
//	
//	public static WebView setWebView(WebView web,String s,boolean bKlein)
//	{
//		web.setMaxWidth(bKlein ? 250:500);
//		web.setMaxHeight(bKlein ? 150:300);
//		if (Static.sDefaultStyle != null)
//		{
//		  web.getStylesheets().add(Static.sDefaultStyle);
//		  web.getStyleClass().add("message");
//		}
//		WebEngine webEngine = web.getEngine();
//		webEngine.loadContent(s);
//		return web;
//	}
//	
//	public void setTooltip(Node Cnt,String sTT)
//	{
//		setTooltip(Cnt,sTT,true);
//	}
	
//	public static void setTooltip(Node Cnt,String sTT,boolean bKlein)
//	{
//		if (sTT!=null && !sTT.trim().equals(""))
//		{
//			Tooltip tooltip;
//			sTT=toHtml(sTT);
//			//fixtestError("setTooltip:"+bKlein+"/"+Static.iWeb+"/"+(sTT==null || sTT.length()<20 ? sTT:sTT.substring(0,20)));
//			if (Static.iWeb==Static.IMMER || sTT.startsWith("<html") && Static.iWeb==Static.AUTO)
//			{
//				tooltip=new Tooltip();
//				tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//				tooltip.setMaxWidth(bKlein ? 350:600);
//				tooltip.setMaxHeight(300);
//				//WebView web=new WebView();
//				//web.getStylesheets().add(Static.sDefaultStyle);
//				//web.getStyleClass().add("EdtModified");
//				//WebEngine webEngine = web.getEngine();
//				//webEngine.loadContent(sTT);
//				tooltip.setGraphic(getWebView(sTT,bKlein));
//			}
//			else
//			{
//				tooltip=new Tooltip(toHtml(sTT));
//				tooltip.setMaxWidth(bKlein ? 250:400);
//				tooltip.setWrapText(true);
//			}
//			Tooltip.install(Cnt, tooltip);
//		}
//	}
//	
//	public void setAction(ButtonBase C,String s,EventHandler AL)
//    {
//      if (C==null)
//        return;
//      if (s != null)
//        C.setId(s);
//      if (AL != null)
//        C.setOnAction(AL);
//    }
	
//	public MenuItem addMI(String sArt,String sKnopf,String sId,Object CM,EventHandler PopList)
//	  {
//		boolean b=sArt != null && (sArt.equals("Button") || sArt.equals("Frame") || sArt.equals("Modell") || sArt.equals("Applikation") || sArt.equals("Checkbox") || sArt.equals("Radiobutton"));
//		int iPos=sArt == null ? -1:b ? getPosBegriff(sArt, sKnopf):getPosBegriff("Frame",sArt);
//		if (sArt != null && iPos<0)
//			Static.printError("Global.addMI: "+sArt+"/"+sKnopf+" nicht gefunden");
//		String sBez=!b ? sKnopf:getBegriffS(sArt, sKnopf);
//		MenuItem Mnu = sArt!=null && sArt.equals("Checkbox") ? new CheckMenuItem(sBez):sArt!=null && sArt.equals("Radiobutton") ? new RadioMenuItem(sBez): new MenuItem(sBez);
//		if (Def() && sArt==null)
//			;
//		else if (!Static.bDefShow && (iPos<0 || !BerechtigungS(iPos)))
//		{
//			fixInfo("Global.addMI: keine Berechtigung für "+sKnopf);
//			Mnu.setDisable(true);
//			return Mnu;
//		}
//	    //String sBild=iPos<0 ? null:TabBegriffe.getS(iPos,"BildFX");
//	    ImageView IV=iPos<0 ? null:getImgBeg(iPos);
//	    if (IV==null && sArt!=null && (sArt.equals("Frame") || sArt.equals("Modell") || sArt.equals("Druck")))
//	    	IV=getImgBG(sArt);
//	    if (IV!=null)//!Static.Leer(sBild))
//	    {
//	    	//javafx.scene.image.Image image=new javafx.scene.image.Image(Static.DirImageSys+sBild);
//	    	Mnu.setGraphic(IV);//new ImageView(image));
//	    }
//	    	
//	    //if (g!= null)
//	    //  Mnu.setFont(g.fontStandard);
//	    if (CM instanceof ContextMenu)
//			((ContextMenu)CM).getItems().add(Mnu);
//		else if (CM instanceof Menu)
//			((Menu)CM).getItems().add(Mnu);
//	    Mnu.setId(sId==null ? sKnopf:sId);
//	    if (PopList != null)
//	    	Mnu.setOnAction(PopList);
//	    //fixtestInfo("addMI "+sArt+"/"+Mnu.getText()+" mit ID "+Mnu.getId());
//	    return Mnu;
//	 }
//	
//	public void addSep(Object CM)
//	{
//		if (CM instanceof ContextMenu)
//		  ((ContextMenu)CM).getItems().add(new SeparatorMenuItem());
//		else if (CM instanceof Menu)
//		  ((Menu)CM).getItems().add(new SeparatorMenuItem());
//		else
//			Static.printError("Global.addSep nicht möglich mit "+Static.print(CM));
//	}
	// JavaFX-Ende
	
	public void setTitel(Tabellenspeicher Tab,String s[])
	{
		for (int i=0;i<s.length;i++)
			Tab.setTitel(s[i], getBegriffS("Show", s[i]));
	}
	
	public void checkVon()
    {
		if (getLog()==0 || !Static.bCheckZR)
			return;
    	java.sql.Timestamp ts=SQL.getTimestamp(this, "select von from bereich where connid=?",""+Sid());
    	boolean b=Static.Gleich(ts, dtVon);
    	//fixtestError("checkVon:"+ts+"/"+dtVon+"->"+b);
    	if (!b)
    	{
    	    //fixtestError("von-DB="+ts+", von-intern="+dtVon+" -> "+b);
    	    //Static.printError("automatisches schließen, da Zeit unterschiedlich!");
    		printError("Bereichsprüfung nötig, da Von auf Datenbank "+ts+" statt "+dtVon);
    		//if (Static.bCheckZR)
    		{
//    	      exec("update logging set status="+NO_ZR+" where status is null and aic_logging="+getLog());
//    	      Logout(true);
//    	      if (Static.bX11)
//    	    	  new Message(Message.WARNING_MESSAGE,null,this).showDialog("Von_ungleich");
//    	    	  //JOptionPane.showMessageDialog(new JFrame(),"Euro fehlt","Fehler",JOptionPane.WARNING_MESSAGE);
//    	      System.exit(-5);
    	        if (SQL.getInteger(this, "select connid from bereich where connid=?", 0, ""+Sid())==0)
	  			{
	  				printError("Bereichszeile fehlt für "+Sid());
	  				exec("insert into bereich (connid) values ("+Sid()+")"); 				
	  			}
	  			else
	  			{
	  				Tabellenspeicher Tab=new Tabellenspeicher(this,"select * from bereich where connid="+Sid(),true);
	  				printError("Bereichszeile falsch für "+Sid()+" war "+Tab.getD("von")+"-"+Tab.getD("bis")+" statt "+getVon()+"-"+getBis());
	  			}
	  			setVonBis(getVon(),getBis(),true);
	    	}
    	}
    	
    }
	
	// Open-JDK-Funktionen
	
	public Font getOutFont(boolean bTitel)
    {
    	return /*Static.bOpenJDK*/Static.fontOutliner!=null ? bTitel ? fontOutlinerT:Static.fontOutliner : bTitel ? fontTitel:Static.fontStandard;
    }
	
	public Font getTabFont()
	{
		return fontTable!=null ? fontTable:getOutFont(false);
  }
	
	public Font getTabFontTZ() // für Titelzeile von Tabelle
	{
		return /*Static.bOpenJDK*/fontTableT!=null ? fontTableT:fontTitelzeile;
	}
	
	public Font getTabFontSum() // für Summe von Tabelle
	{
		return /*Static.bOpenJDK*/fontTableS!=null ? fontTableS:fontSumme;
	}
	
	// Web-Funktionen
	
	public void sendWebChanged(String sArt)
	{
		String sURL=Static.sWeb;
		fixInfo("sendWebChanged "+sArt+" von "+getDB()+" an "+sURL);
		if (!Static.Leer(sURL))
    	{
    		try {
    		URL url = new URL(sURL+"/webLog/changed");
    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod("POST");
    		con.setDoOutput(true);
    		con.setRequestProperty("Content-Type", "application/json");
    		con.setConnectTimeout(1000);
    		con.setReadTimeout(10000);
    		con.setRequestProperty("DB", getDB());
    		con.setRequestProperty("Changed", sArt);
    		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String sIn=in.readLine();
			while (sIn != null) {
				fixtestInfo(sIn);
				sIn=in.readLine();
			}
			//System.out.println("\nCrunchify REST Service Invoked Successfully..");
			in.close();
    		} catch (Exception e) {
				printError("sendWebChanged "+sArt+" geht nicht:"+e);
    		}
		}
	}
	
//	public GPS getGPS(Stack<String> Sta)
//	{
//		String sStreet=Sta.pop();
//		String sCity=Sta.pop();
//		String sLand="at";
//		int i=sStreet.lastIndexOf(' ');
//		if (i<1)
//		{
//			Static.printError("Hausnummer in "+sStreet+" nicht gefunden");
//			return null;
//		}
//		else
//		{
//			String sSt=Static.toHttpFile(sStreet.substring(0, i));
//			String sNr=sStreet.substring(i+1);
//			if (sNr.indexOf('/')>0)
//				sNr=sNr.substring(0, sNr.indexOf('/'));
//			if (!Sta.isEmpty())
//				sLand=Sta.pop();
//		  try {
//    		URL url = new URL("https://nominatim.openstreetmap.org/search/"+sLand+"/"+Static.toHttpFile(sCity)+"/"+sSt+"/"+sNr+"?format=json");
//    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
//    		con.setRequestMethod("GET");
//    		con.setDoOutput(true);
//    		con.setRequestProperty("Content-Type", "application/json");
//    		con.setConnectTimeout(1000);
//    		con.setReadTimeout(10000);
//    		String s=null;
//    		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String sIn=in.readLine();
//			while (sIn != null) {
////				fixtestError(sIn);
//				s=s==null ? sIn:s+"\n"+sIn;
//				sIn=in.readLine();
//			}
//			in.close();
//			JSONArray j= new JSONArray(s);
//			if (j.length()==0)
//			{
//				Static.printError("Adresse "+sCity+"/"+sStreet+" nicht gefunden");
//				return null;
//			}
//			JSONObject json=j.getJSONObject(0);
//			String sLat=json.getString("lat");
//			String sLen=json.getString("lon");
//			String sName=json.getString("display_name");
//			fixtestError("GPS von "+sName+": "+sLat+","+sLen);
//			return new GPS("["+sLat+":"+sLen+":"+sName+"]");
//		  } catch (Exception e) {
//			printError("getGPS "+sCity+"/"+sStreet+" geht nicht:"+e);
//			return null;
//		  }
//		}
//	}
	
//	public void sendWebDB(String sCommand)
//	{
//		sendWebDB(sCommand,null,null);
//	}
	
	
	public boolean testOAuth(int iAic,String sPW)
	{
	  try {
		Parameter Para=new Parameter(this,"Azure");
        Para.getMParameterH("Server");
//        String sServer;
        String sDom;
        String sID;
        String sTenant;
        List<String> sScope=null;
        if (Para.bGefunden)
        {
          String s = Para.sParameterzeile;
          String sAry[]=s.split("&");
//          sServer=sAry[0];
          sDom=sAry[1];
          sID=sAry[2];
          sTenant=sAry[3];
          if (sAry.length>4)
          {
        	  sScope=Arrays.asList(sAry[4].split(","));
//        	  sScope.add("openid");
          }
        }
        else
        {
          printError("Azure nicht definiert");
          return false;
        }
		String sUser=SQL.getString(this,"select Kennung from benutzer where aic_benutzer=?",""+iAic);
		if (sUser.indexOf('@')<0)
			sUser+="@"+sDom;
		String sPara="grant_type=password&username="+sUser+"&password="+sPW+"&client_id="+sID+"&tenant="+sTenant+"&scope="+sScope+"&resource=https://graph.microsoft.com";
//		fixtestInfo("Para="+sPara);
		
    		URL url = new URL("https://login.microsoftonline.com/common/oauth2/token");//"https://login.microsoftonline.com/common/oauth2/token");
    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod("POST");
    		con.setUseCaches(false);
    		con.setDoOutput(true);
    		con.setDoInput(true);//Allow receiving body of response
			con.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    		con.setConnectTimeout(5000);
    		con.setReadTimeout(10000);
    		con.getOutputStream().write(sPara.getBytes("utf-8"));
			con.getOutputStream().close(); // send
			JSONObject json=StreamToJSON(con.getInputStream());
//			String sToken=json.getString("access_token");
//			fixInfo("Token="+sToken);
//			fixInfo("Scope="+json.getString("scope"));
			fixInfo("Ablauf in "+json.getString("expires_in")+" s");
			String sToken2=json.getString("id_token");
//			fixInfo("Token2="+sToken2);
			
			return checkToken(sToken2,sUser,sTenant,sID);

//			return true;
		}
		catch (Exception e) {
			iLogFehler=5;	
			String s=e.getMessage();
			fixtestError("testOAuth liefert folgenden Fehler:"+s);
			if (!TestPC())
				return false;
//			fixInfo(s);
			return s.indexOf("401")>0;
	  }
	}
	
	private boolean checkToken(String sToken,String sUser,String sTenant,String sID)
	{
		String[] sAry=sToken.split("\\.");
		String s2=new String(Base64.getUrlDecoder().decode(sAry[1]));
//		fixInfo("Header="+sAry[0]);
//		fixInfo("Payload="+s2+" bei "+sT[1]);
//		fixInfo("Sign="+" bei "+sAry[2]);
		JSONObject json=null;
		try
		{
			json=new JSONObject(s2);
			String sN=json.getString("upn");
			String sT=json.getString("tid");
			String sC=json.getString("aud");
//			fixInfo("Name="+sN+", Tenant="+sT+", ClientID="+sC);
			return sUser.equals(sN) && sTenant.equals(sT) && sID.equals(sC);
		}catch(Exception e) {
			//s+="\nerrorException="+e.toString();
			fixtestError("checkToken:"+e+" bei JSON="+json);
			return false;
		}
	}
	
	public boolean checkToken(String sToken,String sUser)//,String sTenant,String sID)
	{
		fixtestError("checkToken für "+sUser);
    	Parameter Para=new Parameter(this,"Azure");
        Para.getMParameterH("Server");
        String sDom;
        String sID;
        String sTenant=null;
//        List<String> sScope=null;
        if (Para.bGefunden)
        {
          String s = Para.sParameterzeile;
          String sAry[]=s.split("&");
//          sServer=sAry[0];
          sDom=sAry[1];
          sID=sAry[2];
          sTenant=sAry[3];
//          if (sAry.length>4)
//        	  sScope=Arrays.asList(sAry[4].split(","));
        }
        else
        {
          fixtestError("Azure nicht definiert");
          return false;
        }
		String[] sAry=sToken.split("\\.");
		if (sAry.length<3)
		{
			fixtestError("Token unvollständig:"+sToken);
			return false;
		}
		if (sUser.indexOf('@')<0)
			sUser+="@"+sDom;
		String s2=new String(Base64.getUrlDecoder().decode(sAry[1]));
//		fixInfo("Header="+sAry[0]);
//		fixInfo("Payload="+s2+" bei "+sT[1]);
//		fixInfo("Sign="+" bei "+sAry[2]);
		JSONObject json=null;
		try
		{
			json=new JSONObject(s2);
			String sN=json.getString(json.has("upn") ? "upn":"preferred_username");
			String sT=json.getString("tid");
			String sC=json.getString(json.has("appid") ? "appid":"aud");
//			fixtestError("Name="+sN+", Tenant="+sT+", ClientID="+sC);
			Date dt=new Date();
			Date dt1=new Date(json.getLong("iat")*1000);
			Date dt2=new Date(json.getLong("exp")*1000);
//			fixtestError("Erzeugt:"+dt1+", Ablauf:"+dt2+", aktuell:"+dt);
			return sUser.equals(sN) && sTenant.equals(sT) && sID.equals(sC) && dt.before(dt2) && dt.after(dt1);
		}catch(Exception e) {
			//s+="\nerrorException="+e.toString();
			fixtestError("checkToken:"+e+" bei JSON="+json);
			return false;
		}
	}
	
	public JSONObject StreamToJSON(InputStream is)
	{
		try 
		{
//			InputStream is=url.openStream();// getContent();
			BufferedReader Buf=new BufferedReader(new InputStreamReader(is));
			String s2=Buf.readLine();
			String s=null;
			while (s2 != null) {
				if (!s2.trim().startsWith("//"))
//				{
//					g.fixInfo(s2);
					s=s==null ? s2:s+"\n"+s2;
//				}
				s2=Buf.readLine();
			}
			
			Buf.close();
			s=s.substring(s.indexOf("{"), s.length());
			return new JSONObject(s);
		}catch(Exception e) {
			//s+="\nerrorException="+e.toString();
			fixtestError("URLtoJSON:"+e);
			return null;
		}
	}
	
	public void sendWebDB(String sCommand,String sPara,JFrame Fom)
	{
		String sURL=Static.sWeb;
		fixInfo("sendWebDB "+sCommand+" von "+getDB()+" an "+sURL);
		if (!Static.Leer(sURL))
    	{
    		try {
    		URL url = new URL(sURL+"/webLog/"+sCommand+"/"+getDB()+(sPara==null ? "":":"+sPara));
    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod(sCommand.equals("newMsg") ? "GET":"POST");
    		con.setDoOutput(true);
    		con.setRequestProperty("Content-Type", "application/json");
    		con.setConnectTimeout(1000);
    		con.setReadTimeout(10000);
    		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String sIn=in.readLine();
			String s=null;
			while (sIn != null) {
				fixInfo(sIn);
				s=s==null ? sIn:s+"\n"+sIn;
				sIn=in.readLine();
			}
			JSONObject json=new JSONObject(s);
			if (Fom != null)
				new Message(Message.ERROR_MESSAGE, Fom,this,15).showDialog("WebserverInfo",new String[] {sCommand,""+json.getString("info")});
			//System.out.println("\nCrunchify REST Service Invoked Successfully..");
			in.close();
    		} catch (Exception e) {
				printError("sendWebDB "+sCommand+" geht nicht:"+e);
				new Message(Message.ERROR_MESSAGE, Fom,this,20).showDialog("WebserverFehler",new String[] {sCommand,""+e});
    		}
		}
		else if (Fom != null)
			new Message(Message.ERROR_MESSAGE, Fom,this,5).showDialog("keinWebserver");
	}
	
	public void sendLogout(String sUser,int iLog)
	{
		String sURL=Static.sWeb;
    	fixInfo("sendLogout an "+sURL);
		if (!Static.Leer(sURL))
    	{
    		try {
    		URL url = new URL(sURL+"/webLog/logoutOne/"+getDB()+":"+sUser+":"+iLog);
    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod("POST");
    		con.setDoOutput(true);
    		con.setRequestProperty("Content-Type", "application/json");
    		con.setConnectTimeout(5000);
    		con.setReadTimeout(5000);
    		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String sIn=in.readLine();
			while (sIn != null) {
				fixtestInfo(sIn);
				sIn=in.readLine();
			}
			//System.out.println("\nCrunchify REST Service Invoked Successfully..");
			in.close();
    		} catch (Exception e) {
				printError("sendLogout geht nicht:"+e);
    		}
		}
	}
	
	public String decodeMath(String s)
	{
		if (s!=null)
			return s.replaceAll("≠", "!=").replaceAll("≤", "!>").replaceAll("≥", "!<").replaceAll("∑","!S").replaceAll("Ω","!O").replaceAll("π","!P").replaceAll("∞","!U").replaceAll("∆","!D").replaceAll("√","!W");
		else
			return s;
	}
	
	public String encodeMath(Object Obj)
	{
		if (Obj==null)
			return null;
		if (!(Obj instanceof String))
		{
			printError("encodeMath:"+Obj+" ist kein String");
			return null;
		}
		String s=(String)Obj;
		if (s.contains("!"))
			return s.replaceAll("!=", "≠").replaceAll("!<", "≥").replaceAll("!>", "≤").replaceAll("!S", "∑").replaceAll("!O", "Ω").replaceAll("!P", "π").replaceAll("!U","∞").replaceAll("!D","∆").replaceAll("!W","√");
		else
			return s;
	}
	
	public Vector<Integer> sortBegriff(Vector<Integer> Vec)
	{
		Tabellenspeicher Tab=new Tabellenspeicher(this,new String[] {"aic","bez"});
		for (int i=0;i<Vec.size();i++)
		{
			int iAic=Sort.geti(Vec,i);
			Tab.addInhalt("aic", iAic);
			Tab.addInhalt("bez", getBegBez(iAic));
		}
		Tab.sort("bez", true);
		return Tab.getVecSpalteI("aic");
	}

  public void checkAus(String s)
  {
    long lCheckAus=Static.get_ms();
    long lDif=lCheckAus-lLastCheckAus;
    if (lDif>2000 && Static.FomStart != null)// || getLog()<=0)
    {
      fixInfoT("Global.checkAus aufgerufen bei "+lDif+"/"+s);
      if (getLog()<=0 || !exists("select aic_logging from logging where aic_logging=? and aus is null",""+getLog()))
      {
        fixtestError("Rausflug, da ausgeloggt");
        Meldung(Static.FomStart,"Rausflug","Sie wurden ausgeloggt, da Sie schon ausgeloggt sind!","Autologout");
        if (Static.FomStart != null)
          Static.FomStart.dispose();
      }
    }
    lLastCheckAus=lCheckAus;
  }
	
//	public Rectangle getSS(Component com)
//	{
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice[] gs = ge.getScreenDevices();
//		for (int i3 = 0; i3 < gs.length; i3++) {
//			//DisplayMode dm = gs[i3].getDisplayMode();
//			Rectangle dm = gs[i3].getDefaultConfiguration().getBounds();
//			if (com.getX()>=dm.x && com.getX()<dm.x+dm.width)
//				return dm;
//		}
//		return null;
//	}

    // add your data members here

  // Eigenschafts-Bits
  public static final int cstEindeutig=	  0x0001;
  public static final int cstAlways=	  0x0002;
  public static final int cstNurModell=	  0x0004;
  public static final int cstKeinAutoDate=0x0008;
  public static final int cstRound100=    0x0010;
  public static final int cstDefault=     0x0020;
  //public static final int               0x0040; // gesperrt wegen Abfrage
  //public static final int               0x0080; // gesperrt wegen Abfrage
  
  public static final int cstEE          =0x0F00; // Eingabe-Art (Combobox, Radiobutton, ...)
  public static final int cstEigAuto     =0x0000; // keine Vorabeinstellung, d.h. default Cbo bzw Cbx bzw DatePicker
  public static final int cstEigCho      =0x0100; // ChoiceBox
  public static final int cstEigRad      =0x0200; // Radio-Button
  public static final int cstEigBtn      =0x0300; // Toggle-Button
  public static final int cstEigSbo      =0x0400; // Spinbox
  public static final int cstEigPop      =0x0500; // PopOver
  public static final int cstEigCbo      =0x0600; // Combobox
  public static final int cstEigCbx      =0x0800; // Checkbox
  public static final int cstEigSwb      =0x0900; // Switch-Button
  public static final int cstEigAuC      =0x0A00; // Auto-Complete
  public static final int cstEigDT	     =0x0B00; // Date-Time-Picker
  public static final int cstEigTime	 =0x0C00; // Time-Picker
  public static final int cstEigSlider	 =0x0D00; // Slider
  public static final int cstEigWebTitel =0x0E00; // Web-Titel
  
  public static final int cstEigZone     =0x1000; // diese Eigenschaft (BewStamm) setzt/ändert die Zone
  public static final int cstEigDST      =0x2000; // diese Eigenschaft (BewDatum2) bestimmt die DST (Sommen-Winterzeit-Einstellung)
  public static final int cstEigTake     =0x4000; // diese Eigenschaft immer mitspeichern
  public static final int cstWebLizenz   =0x8000; // ob diese Eigenschaft im Web auf Lizenz geprüft werden soll 
  public static final int cstEigClean   =0x10000; // ob diese Eigenschaft auf doppelte Stammpools geprüft werden soll

  public static final int cstEigBrtg    =0x0100000; // Berechtigung 
  public static final int cstEigStichtag=0x0200000; // ob Stichtag im Web; war cstPromptText:JavaFX-PromtText verwenden
  public static final int cstKeinEigDate=0x0400000; // kein Stichtag bei/mit dieser Eigenschaft
  //public static final int cstEAneu=	0x00800000;

  public static final int cstNP=  	    0x03000000;
  public static final int cstNPkein=	0x00000000;
  public static final int cstNPklar=	0x01000000;
  public static final int cstNPzwang=	0x02000000;
  public static final int cstNPhoch=	0x03000000;

  public static final int cstTakeBez=	0x04000000;
  public static final int cstSysAic=	0x08000000;
  public static final int cstJeder=	    0x10000000;
  public static final int cstEigLizenz= 0x20000000; // ob diese Eigenschaft im All und Web auf Lizenz geprüft werden soll 
  public static final int cstKeinCopy=  0x40000000; // Eigenschaft darf nicht kopiert werden
  
  public Vector<Integer> VecEigZone=null;
  public Vector<Integer> VecEigDST=null;
  public Vector<Integer> VecEigTake=null;

  // Stammtyp-Bits
  public static final int cstEnde=	      0x0001; // unteste Ebene
  public static final int cstTranslate=	  0x0002; // übersetzbar
  public static final int cstBenutzer=	  0x0004; 
  public static final int cstEinheit=	  0x0008; // Mass-Einheit oder Währung
  public static final int cstLizenz=	  0x0010; // Lizenz nötig
  public static final int cstCopy=	      0x0020; // Stammsätze müssen kopiert werden (vom All-Mandant)
  public static final int cstStdFormular= 0x0040; // automatisches Formular
  public static final int cstKeinStichtag=0x0080; // Daten ohne Stichtag gespeichert
  public static final int cstModul       =0x0100; // für Moduldefinition
  public static final int cstHA          =0x0200; // in EF für horiz. Auflösung ansehen wo verwendet
  public static final int cstBFilter     =0x0400; // für Benutzer-Filter verwendbar
  public static final int cstSttDef      =0x0800; // mit All definierbar
  public static final int cstSttBed      =0x1000; // für bedingte Eingabe z.B. Geschlecht- oder Dienstverh.-Abhängig
  public static final int cstSttFav      =0x2000; // Favoritenauswahl in AUComboList
  public static final int cstSttCopy     =0x4000; // kann kopiert werden
  public static final int cstSttFirma    =0x8000; // Einschränkung auf Firma möglich
  public static final int cstForStatus  =0x10000; // für Status verfügbar
  public static final int cstFavWeb     =0x20000; // Favorit nur für Web


  // Bewegungstyp-Bits
  public static final int cstTimer=	  0x0001;
  public static final int cstLokalTimer=  0x0002;
  //public static final int cstNurModell= 0x0004; // wie bei Eigenschafts-Bits
  public static final int cstMandantIgnorieren=0x0008;
  public static final int cstBerechtigung=0x0010;
  public static final int cstANR1 =0x0020; // Mitkopieren bei ANR1
  public static final int cstANR2 =0x0040;
  public static final int cstANR3 =0x0080;
  public static final int cstANR4 =0x0100;
  public static final int cstANR5 =0x0200;
  public static final int cstANR6 =0x0400;
  public static final int cstANR7 =0x0800;
  public static final int cstANR8 =0x1000;
  public static final int cstANR9 =0x2000;

  public static final int cstDD   =0x10C000; // Löschabstandsart (DelDeleted)
  public static final int cstSK   =	 0x4000; // Löschung sehr kurz (ca 1 Tag)
  public static final int cstK    =  0x8000; // Löschung kurz (ca 7 Tage)
  public static final int cstN    =  0x0000; // Löschung normal (ca 30 Tage)
  public static final int cstL    =  0xC000; // Löschung lang (ca 1Jahr)
  public static final int cstNIE  =0x100000; // darf nie gelöscht werden

  public static final int cstDel       =0x10000; // Daten nach x Jahren löschen
  public static final int cstDefinition=0x20000; // Bewegung nur für Definition nötig
  public static final int cstSperre    =0x40000; // bei Tabelle werden Sätze bei Eingabe für andere gesperrt
  public static final int cstBewBrtg   =0x80000; // Berechtigung auf Bewegung
  public static final int cstBewVoll  =0x200000; // bei diesem Bewegungstyp immer vollständig speichern/ergänzen
  public static final int cstBewBew   =0x400000; // BewBew beim löschen mitlöschen
  public static final int cstBewZone  =0x800000; // verwendet/speichert Zone
  public static final int cstNOA     =0x1000000; // nicht ohne ANR speichern

  // Benutzer-Bits

  //public static final int cstBenutzerEbene=0x0043;
  public static final int cstSpezial      =4;
  public static final int cstMehrmals     =8;
  //public static final int cstSoftTerm     =0x0010;
  public static final int cstTimerBenutzer=0x0020;
  //public static final int cstUserManager  =0x0040;
  public static final int cstImport       =0x0080;
  public static final int cstHS           =0x0100; // Hauptschirm darf aufgerufen werden
  public static final int cstOhne         =0x0200; // Einstieg auch möglich wenn Lizenz erreicht
  public static final int cstPW			=0x100C00; // PW-Verschlüsselungsarten
  public static final int cstPW_AZURE     =0x0000; //   Azure, war Normale Verschlüsslung
  public static final int cstPW_MD5	      =0x0400; //   MD5, einfache MD5-Verschlüsselung, noch bei VBV in Verwendung
  public static final int cstPW_LDAP      =0x0800; //   LDAP bzw Active Directory
  public static final int cstPW_MD5B      =0x0C00; //   MD5 mit Aic und Datum
  public static final int cstPW_GOOGLE  =0x100000; //   Google-Auth
  public static final int cstPW_LTOKEN  =0x100400; //   ohne PW einloggen
  public static final int cstPW_EMAIL   =0x100800; //   Code über E-Mail
  public static final int cstPW_KOMBI   =0x100C00; //   Kombination aus LongToken und Code über E-Mail
  public static final int cstDB           =0x1000; // Benutzer auf Datenbank vorhanden, dieser wird statt MASTER-Username verwendet
  public static final int cstAServerUser  =0x2000;
  public static final int cstTerminalUser =0x4000;
  public static final int cstAutoFC       =0x8000; // Automatische Fehlerkonsole bei Fehler
  public static final int cstOC      	 =0x10000; // ohne Computer, alles pers. speichern
  public static final int cstVorlage     =0x20000; // Kopier-Vorlage für web 
  public static final int cstAPI     	 =0x40000; // nur für API erlaubt
  public static final int cst2FA     	 =0x80000; // Zwei-Faktor-Authentifizierung
  public static final int cstTest       =0x200000; // Test-Benutzer
  public static final int cstNoMobile   =0x400000; // keine Mobile-Devices (nicht am Handy/iPad)
  public static final int cstNoAktiv    =0x800000; // keine Aktivitätsprüfung -> eingeloggt bleiben bei Inaktivität
  
  public static final int cstNoLogin = cstTimerBenutzer+cstImport+cstAServerUser+cstTerminalUser;

  // Benutzer-Bits von Pers. Einstellung
  public static final int DEBUG           = 1;
  //public static final int LEER_LASSEN   = 2;
  public static final int SAVE_SHOW       = 4;
  public static final int TEST_FENSTER    = 8;
  public static final int FORMAT_SORTIERT =0x0010;
  public static final int ART_H           =0x0060;
  public static final int H_DEZ           =0x0020;
  public static final int H_MIN           =0x0040;
  public static final int DEFBEZ          =0x0080;
  public static final int SHOWSIZE        =0x0100;
  public static final int STDSIZE         =0x0200;
  public static final int NURSTART        =0x0400;
  public static final int FX              =0x0800; // Java-FX
  public static final int STYLE           =0x1000; // zeigt Style-Infos
  public static final int ALERT           =0x2000; // zeigt Alert-Meldungen
  public static final int BEEP	          =0x4000; // Beep (z.B. bei Fehler)
  public static final int NO_CSS          =0x8000; // Default-CSS ausschalten
  public static final int SAVE_ASK       =0x10000; // Fragt bei JavaFX bei Wechsel ob speichern
  public static final int CENTER_STAGE   =0x20000; // zentriert neue Stage immer auf aufgerufene
  public static final int ORIGINAL       =0x40000; // bei JavaFX Original-Spalten nehmen
  public static final int DEF_SHOW       =0x80000; // läßt Menü und Definition aufrufen obwohl normalerweiße disabled
  // Benutzergruppe-Bits
  public static final int cstHistory=     0x0001;
  public static final int cstAbfrage=     0x0002;
  public static final int cstReadOnly=    0x0004;
  public static final int cstNurFilter=   0x0008;
  public static final int cstGeloeschte=  0x0010;
  public static final int cstWorkflow=    0x0020;
  public static final int cstWorkflowZR=  0x0040;
  public static final int cstNurWeb=      0x0080;
  public static final int cstNurAll=      0x0100;
  public static final int cstBG_pers=     0x0200;

  // Modell-Bits
  public static final int cstPeriodeM=	  0x0001; // 00
  public static final int cstEF=	  	  0x0002; // 01
  public static final int cstZRhold=	  0x0004; // 02
  public static final int cstMenge=	  	  0x0008; // 03
  public static final int cstKeineFrage=  0x0010; // 04
  public static final int cstRefreshM=	  0x0020; // 05
  public static final int cstOhneStamm=	  0x0040; // 06
  public static final int cstRecalc=	  0x0080; // 07
  public static final int cstAServer=	  0x0100; // 08
  public static final int cstNurTest =	  0x0200; // 09 nur auf Test-Datenbanken aufrufbar (war cstAntwort)
  public static final int cstKnopf=       0x0400; // 10
  public static final int cstSave=        0x0800; // 11
  //public static final int cstnachSave=    0x1000; // 12
  public static final int cstThread=      0x2000; // 13
  public static final int cstBew=         0x4000; // 14
  public static final int cstCommit=      0x8000; // 15
  public static final int cstAbbruch=    0x10000; // 16
  public static final int cstUseQry=     0x20000; // 17
  public static final int cstMitSo=      0x40000; // 18
  public static final int cstJokerStt=   0x80000; // 19 JokerStt nach Modell wieder zurücksetzen
  public static final int cstErgebnis=  0x100000; // 20 Ergebnis wird auf Knopf geschrieben (z.B. für Gesamtsaldo)
  public static final int cstMassExport=0x200000; // 21 bei Export wird Mass umgerechnet
  public static final int cstDruckZR   =0x400000; // 22 steht für Druckdefinition zur Verfügung
  public static final int cstDelJS     =0x800000; // 23 JokerStt löschen
  public static final int cstNbAServer=0x1000000; // 24 Knopf nicht bei aktiven AServer anklickbar
  public static final int cstDefImport=0x2000000; // 25 wird bei DefImport gleich ausgeführt
  public static final int cstMDialog  =0x4000000; // 26 Modelldialog bei weg durch Abfrage // war cstMTitel: Titel für Accordion von Modell befüllt
  public static final int cstEigenG   =0x8000000; // 27 eigenes Global mit eigenem Zeitraum // war cstFertigMsg: Snackbar-Meldung am Schluss
  public static final int cstAmpel =  0x10000000; // 28 Ampel-Status anzeigen (war Combo bei JavaFX)
  public static final int cstClean   =0x20000000; // 29 Reinigung über den AServer
  public static final int cstMSperre =0x40000000; // 30 für Sperre
  public static final long cstAmpelWeb=0x80000000l; // 31 Ampel-Web
  public static final long cstWeiter=0x100000000l; // 32 weiter nach Frage oder Modell-Dialog ohne Übergabe
  
  //Titel-Bits
  public static final int cstBer=1; // Titel auch auf Berechtigung prüfen
  
  // pers. Abfragen
  public static final int WEG=	  0x0001; // 00 ausgeblendet
  public static final int VAUS=	  0x0002; // 01 vorrübergehende Austritte anzeigen
  public static final int DEL=	  0x0004; // 02 gelöschte anzeigen
  
  // Mandant-Versuche
  public static final int VMAX=15; // Anzahl Einstiegsversuche-Matrix: 0..3 versuche, 15=unendlich
  public static final int TIMP=16; // Stempelimport über AServer

  public final static int iANRs=9;
  
  public static final int iSpSw=1; // Zustand für Swing-Bild
  public static final int iSpFX=2; // Zustand für JavaFX-Bild
  public static final int iZSel=3; // Zustand für Select/Open bei JavaFX
  public static final int iSpStd=1;      // Standard-Sprache laut Datenbank

  public static Tabellenspeicher TabThread=null;
  public static long lTime=new Date().getTime();
  public long lClock=Static.get_ms();
  public Tabellenspeicher TabVar=null;
  public Tabellenspeicher TabGAbf=null;
  //private long lClock1=lClock2;

  //private double dEurofaktor=1;
  //private String sWaehrung="";

  private int iSystemFormular;

  //public static final int Einheit = 0;
  private int iSttWaehrung = 0;
  public int iEigEurofaktor = 0;
  public int iEigIstWW=0;
  public int iEigAustritt=0;
  public int iEigFirma=0;
  public int iEigMA=0;
  public int iEigReligion=0;
  public int iEigRegion=0;
  public int iEigLand=0;
  public int iEigBild=0;
  public int iEigEMail=0;
  public int iEigTel=0;
  public int iTimerSperre=0;
  public int iTimerModell=0;
  public int iTimerFort=0;
  public int iTimerFertig=0;
  public int iTimerZR=0;
  public int iTimerStamm=0;
  public int iTimerNicht=0; // Eigenschaft um Timersatz nicht zu berechnen
  public int iTimerAlt=0;   // Eigenschaft für alternativen Timeraufrufzeitpunkt

  //public int iEigANR=0;
  //private Vector VecEigANR=null;
  //public int iEigCalc=0;

  private int iStdSprache = 0; // Standard-Sprache laut Betriebssystem
  private int iStdLand = 0;
  private int iStdWaehrung = 0;
  private int iSprache = -1;
  private int iSprache2=0;
  private int iLand = -1;
  private int iWaehrung = -2;
  public int iEuro=-1;
  private boolean bSpracheGleich = false;
  private int iSprFT=-2;

  //private int iMandant=0;
  private int iAnlageBenutzer=0;
  private int iMandantLog=0;
  private int iMandantT=0;
  private int iSysinfo=0;
  //private int iComputer=0;
  //private int iBenutzer=0;
  private int iStamm=0;
  private int iStammHaupt=0;
  private int iMeineFirma=0;
  public Vector VecErsatz=null;
  //private int iStt=0;
  private int iBenutzergruppe=0;
  //private int iLog = 0;
  //private boolean bProg = false;
  private boolean bGesperrt = false;
  //private boolean bHistory = false;
  //private boolean bAbfrage = false;
  //private boolean bReadOnly = true;
  private int iBG_Bits=cstReadOnly;
  private int iFehlLogs=0;
  private int iMaxLog=3;
  //private int iMaxX=-1;
  //public int iMaxH=768;

  //private int[] iAic = {0,0,0,0};
  //private int iAicStunde = 0;
  private int iAicJahr = 0;
  public int iEigFarbe = -1;
  //public String sStandardSprache = new String("");
  private String sUserStamm=null;

  private Vector<Integer> VecJahre;
  public Tabellenspeicher TabMandanten;
  //public Tabellenspeicher TabSelf;
  public Tabellenspeicher TabFeiertage=null;
  public Vector<Integer> VecFeiertage=new Vector<Integer>();
  public Tabellenspeicher TabFeiertagspeicher=null;

  public Tabellenspeicher TabTabellenname;
  public Tabellenspeicher TabBegriffgruppen;
  public Tabellenspeicher TabBegriffe=null;
  public Tabellenspeicher TabBegriffM=null;
  public Tabellenspeicher TabCodes;
  public Tabellenspeicher TabFarbe;
  public Tabellenspeicher TabSchrift;
  public Tabellenspeicher TabStammtypen;
  public Tabellenspeicher TabRollen;
  public Tabellenspeicher TabStammBild;
  public Tabellenspeicher TabStamm;
  public Tabellenspeicher TabBSt;
  public Tabellenspeicher TabH;
  public Tabellenspeicher TabEigenschaften;
  public Tabellenspeicher TabErfassungstypen;
  public Tabellenspeicher TabAuswahl;

  public Tabellenspeicher TabStatus=null;
  public Tabellenspeicher TabImages=null;
  public Tabellenspeicher TabImageIcons=null;
  public Tabellenspeicher TabBegriffInfo;
  public Tabellenspeicher TabFormulare;
  public Tabellenspeicher TabComboboxen;

  public Tabellenspeicher TabFensterpos;
  public Tabellenspeicher TabSplitPos;
  //public Tabellenspeicher TabUserAbfragen;
  public Tabellenspeicher TabFilter; // Filter für Eigenschaften (Gruppen)
  public Tabellenspeicher TabPersAbfragen; // Abfragen statt angegebenen
  //public Tabellenspeicher TabPersJoker;
  public Tabellenspeicher TabPos; // für JavaFX-Tabellen (Spaltenpos, Breite, ausblenden(
  public Tabellenspeicher TabGF;  // für JavaFX-GruppenFilter (Hierarchie mit Top und aktiv)

  public Tabellenspeicher TabStammFarbe;

  public Tabellenspeicher TabMeine=null;
  private Tabellenspeicher TabStrings=null;

  public Tabellenspeicher TabBtnSel=null;
  //public Tabellenspeicher TabDateitypen=null;
//  private Tabellenspeicher TabFormularSperre=null; // für alten Monatsabschluss
  public Tabellenspeicher TabMA=null;             // für neuen Monatsabschluss
  //private Tabellenspeicher TabMA2=null;
  public Tabellenspeicher TabMass=null;
  public Tabellenspeicher TabWaehrung=null;
  public Tabellenspeicher TabLand=null;
  public Tabellenspeicher TabIndi=null;
  private String sIndi=null;
  private Vector<Object> VecWW=new Vector<Object>();
//  private int iAbschluss=0;
//  private int iAbschlussDef=0;
  public int iWechselwaehrung=0;
//  private Vector VecBG_Formular=new Vector();  // für alten Monatsabschluss

  // Lizenz
  //private boolean bSuperuserR=false;
  //private boolean bSuperuserW=false;
  //private int iStdLizenzEnde=0;
  //private Tabellenspeicher TabLizenz;
  private Vector<Integer> VecBegJeder=new Vector<Integer>();
  private Vector<Integer> VecBegTod=new Vector<Integer>();
  private Vector<Integer> VecBegWeb=new Vector<Integer>();
  private Vector<Integer> VecEigTod=new Vector<Integer>();
  public Vector VecBegLizenz=new Vector();
  //public String sMandant;
  private Vector<Object> VecMandantRead=new Vector<Object>();
  public Vector<Object> VecMandantWrite=new Vector<Object>();
  private Vector VecMandantCopy=new Vector();

  // Berechtigung
  public Vector<Object> VecBG=new Vector<Object>();
  //private Vector<Integer> VecJederEig=new Vector<Integer>();
  //private Vector<Integer> VecLizEig=new Vector<Integer>();
  private Vector<Integer> VecEigenschaften=new Vector<Integer>();
  public Vector<Integer> VecHS=new Vector<Integer>();
  private Vector<Integer> VecLizEigenschaften=new Vector<Integer>();
  private Vector<Integer> VecLizStt=new Vector<Integer>();
  private Vector<Integer> VecLizHS=new Vector<Integer>();
  private Vector<Integer> VecLizSprache=new Vector<Integer>();
  //public Tabellenspeicher TabModulLizenz;
  public Tabellenspeicher TabModulBerechtigt;
  //private Vector VecBew=new Vector();
  //private Tabellenspeicher TabBegBerechtigt;
  private Vector VecBegBerechtigt;
  private Vector VecNurSelbst;
  private Vector VecNicht;
  public Tabellenspeicher TabDialog;
  public Tabellenspeicher TabAmpel;
  //public Tabellenspeicher TabStammBer;
  public Tabellenspeicher TabBerechtigung;
  //private boolean bTimerMessage=false;
  private Vector<Integer> VecLokaleTimer=null;
  private Vector<Integer> VecSperre=null;
  public Vector<Integer> VecSttBed=null;
  public Vector<Integer> VecEigNot=null;   // Eigenschaft für ganzen Mandant verboten
  public Vector<Integer> VecEigNicht=null; // Eigenschaft persönlich nicht erlaubt
  public static Vector<Integer> VecBewNot=null;
  private Vector<Integer> VecIBE=new Vector<Integer>();

  public static Color ColHoliday = Color.red;
  public static Color ColTitel = Color.black;
  public static Color ColStandard = Color.black;
  public static Color ColBezeichnung = Color.black;
  public static Color ColEF_Bez = Color.black;  // Farbe Eingabe
  public static Color ColTab_Bez = Color.black; // Farbe Spalte editierbar
  public static Color ColEF_Bez2 = Color.red;   // Farbe Spalte zwingend
  public static Color ColLoeschen = Color.orange;
  public static Color ColNeu = Color.blue;
  public static Color ColAendern = Color.cyan;
  public static Color ColKein = Color.white;
  public static Color ColSelect = Color.blue;
  public static Color ColTSelect = Color.orange.darker();
  public static Color ColToday = Color.green;
  //public Color ColEF = null;
  public static Color ColHS = null;
  public static Color ColTable = null;
  public static Color ColPlanung = null;
  public static Color ColZwingend = Color.yellow;
  public static Color ColBreakpoint = Color.red;
  public static Color ColVar = Color.red.darker();
  public static Color ColMemo = Color.GREEN;
  public static Color ColSpalte = Color.YELLOW;
  public static Color ColBefehl = Color.YELLOW;
  public static Color ColZR = Color.RED;
  public static Color ColQRY = Color.RED;
  public static Color ColPush = Color.RED;
  public static Color ColKeinZR = Color.RED;
  public static Color ColDebug = Color.MAGENTA;
  public static Color ColHide = Color.GRAY;
  public static Color ColAustritt = Color.RED;
  public static Color ColMo=null;
  public static Color ColDi=null;
  public static Color ColMi=null;
  public static Color ColDo=null;
  public static Color ColFr=null;
  public static Color ColSa=null;
  public static Color ColSo=null;
  public static Color ColMo2=null;
  public static Color ColDi2=null;
  public static Color ColMi2=null;
  public static Color ColDo2=null;
  public static Color ColFr2=null;
  public static Color ColSa2=null;
  public static Color ColSo2=null;
  public static Color ColSumme= Color.BLACK;
  public static Color ColSumme2= Color.RED;
  public static Color ColZSum=new Color(0xEFE8EF);//=silber; gold=new Color(0xF0CF00);
  public static Color ColDisabled=Color.DARK_GRAY;
  public static Color ColDoppelt=Color.GRAY;
  public static Color Col1=Color.GREEN.darker();
  public static Color Col2=Color.BLUE;
  public static Color ColVorbedingung=Color.RED;
  public static Color ColRahmen=Color.RED.brighter();
  public static Color ColAlle=null;
  public static Color ColCalc=Color.RED;
  public static Color ColThread=Color.GREEN.darker();
  public static Color ColSperre=Color.MAGENTA.darker();
  public static Color ColTod=Color.RED;
  public static Color ColPers=Color.BLUE;
  public static Color ColJavaFX=Color.GREEN.darker();
  public static Color ColWeb=Color.BLUE.brighter();
  public static Color ColAdmin=Color.GREEN.darker();
  public static Color ColFavorit=null;
  public static Color ColEdit=Color.GREEN.darker();
  public static Color ColInfo=Color.RED.brighter();
  public static Color ColBild=Color.LIGHT_GRAY;
  public static Color ColZusatz=Color.GREEN.darker();
  public static Color ColDep=Color.PINK;
  public static Color ColToolbar=Color.GREEN.brighter();
  public static Color ColBtn=Color.YELLOW.brighter();
  public static Color ColHover=Color.WHITE;
  public static Color ColRem=Color.gray;
  public static Color ColCalc2=Color.ORANGE;
  public static Color ColText=Color.MAGENTA;
  private MouseAdapter MA_ND=null;
  private MouseAdapter MA_NDT=null;
  //public JCOutlinerNodeStyle StyDel=null;
  private static String sFont="Arial"; // "SansSerif"
  public static Font fontDisabled = new Font("Arial",Font.ITALIC,8);
  public static Font fontInaktiv = new Font("Arial",Font.ITALIC+Font.BOLD,9);
  public static Font fontTitel = new Font("Arial",Font.BOLD,11);
  public static Font fontTitelzeile = null;
  public static Font fontBezeichnung = null;
  public static Font fontEF_Bez = null;
  public static Font fontEF_Bez2 = null;
  public static Font fontButton = null;
  public static Font fontModell = fontStandard;
  public static Font fontRem = null;
  public static Font fontSumme = new Font("Arial",Font.BOLD,11);
  public static Font fontSumme2 = new Font("Arial",Font.BOLD,11);
  public static Font fontMessage = new Font("Arial",0,12);
  public static Font fontPopup = fontStandard;
  public static Font fontTooltip =null;
  public static Font fontAServer = new Font(sFont,Font.ITALIC+Font.BOLD,10);
  public static Font fontJConsole= new Font("Courier New",0,12);
  public static Font fontOutliner= new Font("Calibri",0,11);
  public static Font fontOutlinerT= new Font("Calibri",Font.BOLD,12);
  //public static Font fontOutlinerS= new Font("Calibri Light",Font.BOLD,12); // Spaltenüberschrift von Outliner
  private Font fontTable= null;
  private Font fontTableT= null; // Spaltenüberschrift von Table
  private Font fontTableS= null; // Summe bei Table
  public Font fontLogin=null; // Schrift fürs Login ND
  public Font fontBarcode=null; 
  
  private int iProzOld = 0;

  public boolean bSchutz=false;
  // public boolean bSperreIgnorieren=false;
  public boolean bErsatz=false;
  //public boolean bAutoInit=false;

  public Insets inset = new Insets(1,1,1,1);
  public Insets inset0 = new Insets(0,0,0,0);

  private JFrame FomLeer=null;
  private static JFrame FomThread=null;
  private static AUOutliner GidThread=null;
  public boolean bThreadEscape=false;
  public static int iTabBegriff=0;
  public static int iTabStt=0;
  public static int iTabBew=0;
  public static int iTabStamm=0;
  //private int iFirstProt=-1;

  public int iLight_Off=0;
  public int iLight_Red=0;
  public int iLight_Yellow=0;
  public int iLight_Green=0;
  public int iSttLight=0;
  public int iSttANR=0;
  public int iSttBSt=0;
  public int iSttFirma=0;
  public int iSttPeriode=0;
  public int iSttZone=0;
  private int iEigZoneMin=0;
  public int iRolMA=0;
  //private int iANR=0;
  public static int keinStamm=16;

  public static int iPopkey=525;
  public static int iRM=MouseEvent.ALT_DOWN_MASK;
  
  //private static int iFxMinY=50;

  private int iBG_Druck=0;
  private int iBG_URL=0;
  private ImageIcon ImgDruck=null;

  //private javax.swing.plaf.ButtonUI BtnBack=null;
  public SQL Qry;
  public boolean bCC=false;
  public boolean bTestdruck=false;
  public String sKennzeichen=null;
  public static boolean bSyncInfo=false;
  public static boolean bInfoAbfrage=false;
  public static boolean bInfoDruck=false;
  public static boolean bInfoJeder=false;
  public static boolean bInfoEvent=false;
  public static boolean bInfoGF=false;
  public boolean bEnable=true;
  //private javax.swing.plaf.ButtonUI BtnTT=new javax.swing.plaf.metal.MetalButtonUI();
  public Tabellenspeicher TabAbfragen=null;
  public Tabellenspeicher TabModelle=null;
  public Tabellenspeicher TabAustritt=null;
  private Tabellenspeicher TabZonen=null;
  private boolean bMac=false;
  //public Vector<Timestamp> VecPerioden=null;
  public Tabellenspeicher TabPerioden=null;
  public java.sql.Timestamp dtBeginn=null;
  public static JButton BtnDruck=null;
  public static JButton BtnLogout=null;
  public static boolean bLogout=true;
  public boolean bImport=false;
  public boolean bDruck=false;
  public boolean bUP=false; //Update-Programm
  public boolean bAbschluss=false;
  public boolean bLizenzFrei=false;
  //private boolean bUseJavaFX=false;
  public static boolean bAutoAll=false;
  public int iDefImport=0;
  private boolean bHS=false;
  public boolean bZoneChange=false;
  public boolean bTestLog=false;
  public boolean bAServer=false;
  public String sToken=null;
  public boolean bH_dez=false;
  public boolean bH_min=false;
  public int iAicStunde=0;
  private javax.swing.Timer timer=null;
  private long lLastProt=Static.get_ms();
  private long lLastCheckAus=lLastProt;

}

