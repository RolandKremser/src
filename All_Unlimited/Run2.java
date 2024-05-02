package All_Unlimited;
/*
    Run2_All_Unlimited.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/
// add your custom import statements here
//import com.ms.security.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Frame;
//import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Vector;

//import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;

import All_Unlimited.Allgemein.Eingabe.AUPasswort;

import javax.swing.JProgressBar;
//import javax.swing.JTextField;
import javax.swing.SwingConstants;
//import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import javax.swing.plaf.metal.MetalLookAndFeel;

import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.AUTable;
import All_Unlimited.Allgemein.Azure;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.GroupBox;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
//import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
//import All_Unlimited.Allgemein.Anzeige.Ampel;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
//import All_Unlimited.Allgemein.Eingabe.Schrift;
//import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Grunddefinition.*;
import All_Unlimited.Hauptmaske.AU_Formular;
import All_Unlimited.Hauptmaske.Run;
//import All_Unlimited.Hauptmaske.GruppenFilter;
//import All_Unlimited.Hauptmaske.Import;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Hauptmaske.TCalc;
//import All_Unlimited.Hauptmaske.Zeitraum;
//import All_Unlimited.Grunddefinition.DefEigenschaft;
import All_Unlimited.Hauptmaske.Hauptschirm;
import All_Unlimited.Hauptmaske.Import;

import java.awt.FlowLayout;

import All_Unlimited.Hauptmaske.EingabeFormular;
import jclass.bwt.JCOutlinerNodeStyle;

//import java.util.Locale;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Toolkit;
//import java.io.File;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import All_Unlimited.Allgemein.Passwort;
//import java.io.File;
//import All_Unlimited.Allgemein.SMTP2;

public class Run2
{
     /**
     * Btn_actionPerformed(ActionEvent e) Method
     */

    public static void main( String[] args )
    {
      if (args.length<1)
      {
        System.out.println("java -cp All2.jar;AllMy.jar Run2_All_Unlimited [Options]");
      }
      else
        new Run2(args);
      //showStatus("fertig main");
    }

    private Run2(String[] args)
    {
      //String sConnect=args[0];
      start(args);
      //showStatus("fertig Run2_All_Unlimited");
    }

    private static void showStatus(String s)
    {
      System.out.println("<Status>: "+s);
    }

//    private static String getParameter(String s,String[] args)
//    {
//      for(int i=0;i<args.length;i++)
//        if(args[i].startsWith(s+"="))
//          return args[i].substring(s.length()+1);
//      return null;
//    }

    private void Meldung(String sKennung,String sText,String sTitel)
    {
      if (g.TabBegriffe != null)
        new Message(Message.WARNING_MESSAGE,Fom,g).showDialog(sKennung);
      else
        JOptionPane.showMessageDialog(new JFrame(),new JLabel(sText), sTitel,JOptionPane.INFORMATION_MESSAGE);
      BtnAnmelden.setEnabled(true);
    }

    private void BtnAnmelden_actionPerformed(ActionEvent e)
    {
      //g.testInfo("**********************************************************        "+Save.now()+": Login-start");
		  Ausgabe("BtnAnmelden-Anfang");
      showStatus("try to login ..");
      boolean bAlt=e==null ? false:(e.getModifiers()&ActionEvent.ALT_MASK)>0;
		  if (BtnAnmelden!=null) BtnAnmelden.setEnabled(false);
      //bOption=false;
      if (FrmOption != null)
        FrmOption.setVisible(false);
      if (bAlt && Static.cache())
        Static.clearCache();
      if (EdtNamePasswort==null)
        EdtNamePasswort= new AUPasswort();
      if (EdtName==null)
        EdtName = new Text("",40);
      String sPasswort=e==null ? Run.sPW:new String(EdtNamePasswort.getPassword());
      EdtNamePasswort.setText("");
      //BtnAnmelden.repaint();
      if (g.Disconnected())
        g.connect(null);
		int iStatus=SQL.getInteger(g,"select "+g.int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
    if (iStatus>0 && EdtName != null && g.isDef(EdtName.getText()))
      Meldung("Status_ignorieren","Trotz Sperre darf Def einsteigen","Sperre ignorieren");
    else if (iStatus==1)	// Versionsupdate
    {
      Meldung("Update_lauft","Es läuft gerade ein Update","Update");
      return;
    }
    else if (iStatus==2 && !Static.bNotfall)	// Sperre durch Rauswurf
    {
      Meldung("gesperrt","Der Computer ist gesperrt","Sperre");
      return;
    }
    else if (Run.sBenutzer==null && Run.sToken==null && sPasswort.equals(""))
    {
      //g.fixtestError("PW_fehlt bei "+EdtName.getText());
      String sK=g.S(EdtName.getText(),40);
      // Tabellenspeicher Tab=new Tabellenspeicher(g, "select aic_benutzer,kennung,bits,E_MAIL,aic_mandant from benutzer where kennung="+sK,true);
      // if (Tab.size()==0)
      // {
      //   Meldung("PW_fehlt","Es wurde noch kein Passwort eingegeben","Passwort");
      //   return;
      // }
      int iPW_Art=/*Tab.getI("bits")&Global.cstPW; */SQL.getInteger(g, "select bits from benutzer where kennung="+sK)&Global.cstPW;
      /*if (iPW_Art == Global.cstPW_EMAIL)
      {
        String sEMail=Tab.getS("E_MAIL");//SQL.getString(g, "select E_MAIL from benutzer where kennung="+sK);
        if (Static.Leer(sEMail) || sEMail.indexOf('@')<0)
        {
          Meldung("E-Mail_fehlt","E-Mail-Adresse fehlt bei Benutzer","Passwort");
          return;
        }
        String sCode=UserManager.getCode();
        g.fixtestInfo("Benutzer:"+Tab.getS("Kennung")+"/"+Tab.getI("aic_benutzer")+" -> Mandant="+Tab.getI("aic_mandant"));
        SMTP2 smtp=new SMTP2(g, null,Tab.getI("aic_mandant"));
          smtp.sendTo(sEMail);
          smtp.subject("2-Faktorcode");
          smtp.sendText("Code von ALL: "+sCode);
          smtp.quit();

        JDialog Dlg = new JDialog(Fom, g.getBegriffS("Dialog", "Code"), true);
        Dlg.getContentPane().setLayout(new BorderLayout(2, 2));
        Dlg.getContentPane().add("North", new JLabel("Bitte Code eingeben:"));   
        Text EdtCode=new Text("",6);
        Dlg.getContentPane().add("Center",EdtCode);
        JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        JButton BtnOk = g.getButton("Ok");
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
        Dlg.getContentPane().add("South", Pnl);
        Dlg.pack();
        Static.centerComponent(Dlg,Fom);
        Dlg.setVisible(true);
        boolean b=sCode.equals(EdtCode.getText());
        g.fixtestError("Code ist "+b);
        if (!b)
        {
          Meldung("Code_falsch","Der eingegebene Code ist falsch","Passwort");
          return;
        }  
      }
      else if (iPW_Art == Global.cstPW_SMS)
      {
        Meldung("SMS_nicht","Passwort über SMS wird noch nicht unterstützt","Passwort");
        return;
      }
      else*/ if (sPasswort.equals("")) //iPW_Art != Global.cstPW_KEIN)
      {
        Meldung("PW_fehlt","Es wurde noch kein Passwort eingegeben","Passwort");
        return;
      }
    }
		else if (iStatus==-1 && g.SuperUser())  // Fehler bei Versionsupdate
		{
                  int iLog=SQL.getInteger(g,"select count(*) from logging where aus is null");
                  String sFehler=null;
                  if (iLog>0)
                    sFehler="nicht ermittelbar, da "+iLog+" eingeloggt";
                  else
                  {
                    String sR = Reinigung.SpaltenCheck(g, false);
                    if (Reinigung.bFehler)
                      sFehler=sR;
                  }
                  if (sFehler != null)
                  {
                    if (new Message(Message.YES_NO_OPTION, Fom, g).showDialog("SpaltenFehler",new String[] {sFehler}) == Message.NO_OPTION)
                      return;
                  }
                  else
                  {
                    int iPU=SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
                    g.exec("Update parameter set "+g.int1()+"=0 where aic_parameter="+iPU);
                  }
		}
                else if ((Global.iInfos&Global.ALLEIN)>0)
                {
                   if (new Message(Message.YES_NO_OPTION, Fom, g).showDialog("EinloggenErlauben") == Message.YES_OPTION)
                   {
                     Global.iInfos-=Global.ALLEIN;
                     g.exec("Update computer set cbits="+Global.iInfos+" where aic_computer="+g.getComputer());
                   }
                }
		//lbl.setText("Bitte warten...");
                String sMandant=EdtMandant.getText();
                if (sMandant.contains(";"))
                	sMandant=sMandant.replace(';', '@');
                if (sMandant.contains("\'"))
                	sMandant=sMandant.replace('\'', '-');
//        g.fixtestError("vor Login mit Benutzer="+Run.sBenutzer+"/"+EdtName.getText()+", bNewComputer="+bNewComputer+", Mandant="+sMandant);
        g.sToken=Run.sToken;
//        g.fixtestError("vor Login");
		boolean bLogin = Run.sBenutzer != null ? g.Login(Fom,Run.sBenutzer,Run.sPW,Run.iPArt):bNewComputer ?
		  g.Login(Fom,sMandant,new String(EdtMandantPasswort.getPassword()),EdtName.getText(),sPasswort,CbxAufnehmen.isSelected()) :
		  g.Login(Fom,EdtName.getText(),sPasswort);
		if(bLogin)
		{
			//checkStammBerechtigung();
                        //checkAbschluss();
                        Global.bLogout=false;
//                        iAnzBG=0;
                        Run.bAlt=bAlt;
                        Run.setUserPara(g);
                        showStatus("Welcome to All Unlimited");
                        timer.setDelay(Run.sBenutzer==null ? /*SboRefresh.getIntValue()*/5000:10000);
			//if (sBenutzer!=null || CbxRefresh.isSelected())
                        if (g.bAbschluss)
                        {
                          AClient.send_AServer("abschluss",g);
                          g.bAbschluss=false;
                        }
						//g.testInfo(Count.print());
//                        String sBG=g.getBenutzergruppe()>0 ? "aic_benutzergruppe="+g.getBenutzergruppe():g.getAllBG();
//			int iFormular=SQL.getInteger(g,"select aic_formular from benutzergruppe join formular on benutzergruppe.aic_begriff=formular.aic_begriff where benutzergruppe."+sBG);
//			if (iFormular==0)
//				iFormular=SQL.getInteger(g,"select aic_formular from mandant join formular on mandant.aic_begriff=formular.aic_begriff where mandant.aic_mandant="+g.getMandant());
            int iFormular=g.getStartFom();
			Run.setMandant(g);
			Static.FomStart = null;
			if (iFormular==0)
				if (g.Def())
					Static.FomStart = (JFrame)DefFormular.get(g,0).thisFrame;
				else
				{
					Static.printError("Startformular für "+EdtName.getText()+" nicht gefunden!");
					new Message(Message.WARNING_MESSAGE, Fom, g, 10).showDialog("StartformularFehlt",new String[] {""+EdtName.getText()});
					Abmelden(false);
					return;
				}
			else
			{
				AU_Formular AF=new AU_Formular(iFormular,g);
				if (!AF.bFP)
					Static.centerComponent(AF.thisJFrame(),Fom);
				Static.FomStart = AF.thisJFrame();				
			}
			g.LoadSchrift(false, g.getFontFaktor());
//                        bVerlauf=Static.bVerlauf;
                        Static.bVerlauf=false;
                        if (g.VecErsatz != null && g.VecErsatz.size()>0)
                          Ersatzwahl();
                        else if (!Run.bKAF && !bAlt && (Transact.iInfos&Global.FAST_OUT)==0 && g.HS() && !Static.bNurStart)
                          Run.RestFenster(g);
                        else
                          Run.checkReady(g);
                        if (bAlt)
                          for (int iPos=0;iPos<g.TabFensterpos.size();iPos++)
                            if((g.TabFensterpos.getI(iPos, "bits") & 1) > 0 && !g.TabFensterpos.getS(iPos,"Status").equals("del"))
                            {
                              g.TabFensterpos.setInhalt(iPos, "Bits", 0);
                              g.TabFensterpos.setInhalt(iPos, "Status", "update");
                            }
                        /*if (iFormular>0 && g.Def())
                          Sicherheitsformular();
                        else*/
                        lLastMessage=0;
                        timer.start();
                        //if (sAServer != null)// && AClient.sAServer==null)
                        //  AClient.sAServerSoll = sAServer;
                        //AClient.checkAServer(g);
      if (Static.FomStart != null)
			Static.FomStart.addWindowListener(new WindowListener()
			{
				public void windowClosed(WindowEvent e)
				{
					//g.progInfo("windowClosed");
					if (g.getBenutzer()>0)
						Abmelden(false);
                                        if (Fom==null)
                                          System.exit(0);
				}
				public void windowOpened(WindowEvent e)
				{
				}
				public void windowClosing(WindowEvent e)
				{
					//g.progInfo("windowClosing");
          if (Static.FomStart != null)
					  Static.FomStart.dispose();
				}
				public void windowIconified(WindowEvent e)
				{
				}
				public void windowDeiconified(WindowEvent e)
				{
				}
				public void windowActivated(WindowEvent e)
				{
				}
				public void windowDeactivated(WindowEvent e)
				{
				}
			});
			if (e!=null && !g.ohnePW() && !g.isLDAP() && !Static.bAzure && !Static.bNotfall)
			{
                String sPW_Warning=Passwort.PW_Fehler(g,sPasswort,null,null);
                if (sPW_Warning != null)
                {
                  g.fixtestError("sPW_Warning="+sPW_Warning+", Fom-Pos="+Static.FomStart.getLocation());         
                  Passwort.PW_Message(g,Static.FomStart,sPW_Warning);
                  //new Message(Message.WARNING_MESSAGE, Static.FomStart, g, 10).showDialog(sPW_Warning);
                  Passwort PW=new Passwort(Static.FomStart,g);
                  PW.setPasswort("Benutzer",g.getBenutzer(),true);
                  if (!PW.isOK())
                  {
                	  g.printError("Passwort-Änderungsmaske vorzeitig geschlossen, deshalb logout");
                	  Static.FomStart.dispose();
                  }
                }
			}
		}
		else
		{
			//lbl.setText("");
			BtnAnmelden.setEnabled(!g.gesperrt());
			showStatus("Access denied");
			if (isGesperrt()) return;
			int iB=g.getBenutzer();
			if(bLogin)
				new Message(Message.WARNING_MESSAGE,null,g).showDialog("Versionskonflikt");
			else if (g.TabBegriffe != null)
                          new Message(Message.WARNING_MESSAGE, Fom, g).showDialog(iB==-2 ? "User-Lizenz erreicht":iB==-1 ? "BenutzerAktiv":iB==-4 ? "LDAP_falsch":iB==-5 ? "Azure_falsch":iB==0 ? "PasswortFalsch":"login failed");
            else
                          JOptionPane.showMessageDialog(Fom,new JLabel(iB==-2 ? "User-Lizenz erreicht": iB==-1 ? "Benutzer bereits eingeloggt":
                                iB==0 ? "Benutzer "+(Run.sBenutzer != null ? Run.sBenutzer:EdtName.getText())+" nicht gefunden (User does not exist)!":"Einloggen nicht möglich (login failed)!"),
                                "Login",JOptionPane.WARNING_MESSAGE);
		}
		Ausgabe("BtnAnmelden-Ende");
                //g.testInfo("**********************************************************        "+Save.now()+": Login-fertig");
    }

    public void Ersatzwahl()
        {
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct bezeichnung,benutzer.kennung,benutzer.aic_benutzer,benutzer.aic_stamm from stammview2"+g.join("benutzer","stammview2","stamm")+
            //" where (aic_benutzer="+g.getBenutzer()+" or benutzer.aic_stamm"+Static.SQL_in(g.VecErsatz)+")"
            " where benutzer.aic_stamm"+Static.SQL_in(g.VecErsatz)+" and aic_rolle is null and geloescht is null and benutzer.aic_mandant="+g.getMandant(),true);
          if (Tab.isEmpty())
            return;
          if (OutErsatz == null)
          {
            OutErsatz = new JCOutliner(new JCOutlinerFolderNode(""));
            String[] s = new String[]{g.getBegriffS("Show", "Benutzer")/*, g.getBegriffS("Show", "Kennung")*/};
            OutErsatz.setColumnButtons(s);
            OutErsatz.setNumColumns(s.length);
            OutErsatz.setRootVisible(false);
          }
          else
            ((JCOutlinerFolderNode)OutErsatz.getRootNode()).removeChildren();
          JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)OutErsatz.getRootNode();
          JCOutlinerNodeStyle Sty=new JCOutlinerNodeStyle();
          Sty.setForeground(Global.ColStandard);
          for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            Vector<String> VecSpalten=new Vector<String>();
            VecSpalten.addElement(Tab.getS("Bezeichnung"));
            //VecSpalten.addElement(Tab.getS("Kennung"));
            JCOutlinerNode Node = new JCOutlinerNode((Object)VecSpalten, NodeRoot);
            Vector<Integer> VecDaten=new Vector<Integer>();
            VecDaten.addElement(Tab.getInt("aic_benutzer"));
            VecDaten.addElement(Tab.getInt("aic_stamm"));
            Node.setUserData(VecDaten);
            Node.setStyle(Sty);
          }
          OutErsatz.folderChanged(NodeRoot);
          if (FomErsatz == null)
          {
            FomErsatz = new JFrame(g.getBegriffS("Dialog","Ersatz-Wahl"));
            JButton BtnWechseln = g.getButton("wechseln");
            BtnWechseln.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                Vector VecDaten = (Vector)OutErsatz.getSelectedNode().getUserData();
                g.bErsatz=true;
                for(int iPos=0;iPos<g.TabFormulare.size();)
                {
                  Object Obj = g.TabFormulare.getInhalt("Formular",iPos);
                  if(Obj instanceof EingabeFormular)
                  {
                    ((Formular)Obj).thisFrame.dispose();
                    g.TabFormulare.clearInhalt(iPos);
                  }
                  else if(Obj instanceof Hauptschirm)
                  {
                    ((Hauptschirm)Obj).dispose();
                    g.TabFormulare.clearInhalt(iPos);
                  }
                  else
                    iPos++;
                }
                Hauptschirm.freeAllSwing(g);
                int iAic = Tabellenspeicher.geti(VecDaten.elementAt(0));
                g.setStamm(Tabellenspeicher.geti(VecDaten.elementAt(1)));
                g.fillBerechtigung(iAic);
                ShowAbfrage.refreshBerechtigungSmall(g);
                FomErsatz.setVisible(false);
              }
            });
            FomErsatz.getRootPane().setDefaultButton(BtnWechseln);
            JButton BtnBeenden = g.getButton("Beenden");
            BtnBeenden.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                FomErsatz.setVisible(false);
              }
            });
            JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
            Pnl.add(BtnWechseln);
            Pnl.add(BtnBeenden);
            FomErsatz.getContentPane().add("Center", OutErsatz);
            FomErsatz.getContentPane().add("South", Pnl);
            FomErsatz.setSize(300,300);
            Static.centerComponent(FomErsatz, Fom);
          }
          Run.checkReady(g);
          FomErsatz.setVisible(true);
        }


    /*private void Sicherheitsformular()
    {
      Static.FomStart.addMouseListener(new MouseListener()
        {
            public void mousePressed(MouseEvent ev)
            {
            }
            public void mouseClicked(MouseEvent ev)
            {
              if(ev.getModifiers()==MouseEvent.BUTTON2_MASK && ev.getClickCount()==3)
              {
                new DefFormular(g);
              }
            }
            public void mouseEntered(MouseEvent ev)
            {}
            public void mouseExited(MouseEvent ev)
            {}
            public void mouseReleased(MouseEvent ev)
            {
            }
        });

    }*/

	private void Abmelden(boolean bAuto)
	{
		//g.fixInfo("Abmelden:"+g.getBenutzer());
                showStatus("try to disconnect..");
		timer.stop();
		lRausStart=0;
        bFirstMessage = true;
		g.printSI("Abmelden");
		Run.sToken=null;
		if (BtnToken != null)
			BtnToken.setEnabled(true);
        if (/*Static.iZeitart != 0 && */(Transact.iInfos&Transact.FAST_OUT)==0)
		{
			Parameter ParaEinst = new Parameter(g,"Pers. Einstellungen");
			ParaEinst.getParameter("Periode",true,false);
			int iLeiste=Static.bZR_Leiste?1:0;
			int iV=DateWOD.getInt(g.getVon());
			int iB=DateWOD.getInt(g.getBis());
			if (!ParaEinst.bGefunden || !g.getZA(0).equals(ParaEinst.sParameterzeile) || iLeiste != ParaEinst.int2 || iV != ParaEinst.int3 || iB != ParaEinst.int4)
				ParaEinst.setParameter("Periode",g.getZA(0),0/*Static.iZeitart*/,iLeiste,iV,iB,true,false);
			if (Static.bND)
			{
				ParaEinst.getParameter("Computer",true,false);
	        	if (!ParaEinst.bGefunden || ParaEinst.int1 != Transact.iInfos)
	        		ParaEinst.setParameter("Computer","",Transact.iInfos,0,0,0,true,false);
			}
			ParaEinst.getParameter("Benutzer",true,false);
			//g.fixtestError("Para-Aic="+ParaEinst.iAic+": Vergleiche "+ParaEinst.int4+" mit "+g.getMandant());
			if (!ParaEinst.bGefunden)
				ParaEinst.setParameter("Benutzer","",0,0,g.iEuro,g.getMandant(),true,false);
			else if (ParaEinst.int4!=g.getMandant())
				g.exec("Update Parameter set "+g.int4()+"="+g.getMandant()+" where aic_parameter="+ParaEinst.iAic);
			ParaEinst.free();
		}
		g.printSI("save Periode");
		g.TabComboboxen.clearAll();
		g.printSI("clear TabComboboxen");
		int iAnzahl=0;
		for(int iPos=g.TabFormulare.size()-1;iPos>=0;iPos--)
		{
			Object Obj = g.TabFormulare.getInhalt("Formular",iPos);
			g.TabFormulare.setInhalt(iPos,"Formular",null);
			//System.runFinalization();
			//System.gc();
                        try
                        {
                          if (Obj instanceof Formular)
                            ((Formular)Obj).thisFrame.dispose();
//                          else if (Obj instanceof FormularFX)
//                              ((FormularFX)Obj).free();
                          else if (Obj instanceof JDialog)
                            ((JDialog)Obj).dispose();
                          else if (Obj instanceof JFrame)
                            ((JFrame)Obj).dispose();
                          else
                            Static.printError("Run2_All_Unlimited.Abmelden(): Fehler in g.TabFormulare!!! In Spalte Formular ist ein falscher Datentyp!!!");
                        }
                        catch (Exception e2)
                              {
                                Static.printError("Run2.Abmelden: "+e2+" bei "+Static.print(Obj));
                              }
			iAnzahl++;
		}
		g.TabFormulare.clearAll();
		Hauptschirm.freeAllSwing(g);

                //All_Unlimited.Print.Drucken.bAktiv=false;
                AUTable.TabColumn=null;
                TCalc.TabCalc=null;
		Formular.clearForm();
		//System.runFinalization();
		//System.gc();
		g.printSI("TabFormulare schließen");
		g.printInfo(""+iAnzahl+" Formulare vernichtet");
		VecMail.removeAllElements();

		g.setDebug(false);
		if (bAuto && g.getLog()>0)
			g.exec("update logging set status="+Transact.AUTOLOGOUT+" where aic_logging="+g.getLog());
		g.Logout(Run.sBenutzer==null);
		BtnAnmelden.setEnabled(true);
                showStatus("Disconnected");
                //if (sBenutzer!=null)
                //  System.exit(0);
	}
    private void Ausgabe(String s)
	{
		iClock1 = iClock2;
		iClock2 = Static.get_ms();
		if (g == null)
			Transact.fixInfoS(s+":"+(iClock2-iClock1));
		else
			g.printInfo(0,s+":"+(iClock2-iClock1));
	}

        /*private void showOption()
        {

        }*/

        private void BuildOptionen()
        {
          //FrmOption = new JFrame(g.getBegriffS("Dialog","Option"));
          FrmOption = new JDialog(Fom,g.getBegriffS("Dialog","Option"),false);
          FrmOption.getContentPane().setLayout(new BorderLayout(2,2));
          //JPanel Pnl4 = new JPanel(new GridLayout(0,1,2,2));

                  GroupBox GB = new GroupBox(g.getBegriffS("Show","Mandant wechseln"));
                  GB.setLayout(new GridLayout(0,1,2,2));
                  //JPanel Pnl = new JPanel(new GridLayout(4,0,2,2));
                  //GB.add(new JLabel(" "+g.getBegriff("Label","Mandant")+":"));
                  JPanel Pnl=new JPanel(new FlowLayout(FlowLayout.CENTER,2,2));
                   g.addLabel3(Pnl,"Mandant_momentan");
                   LblMandant=new JLabel();
                   LblMandant.setFont(Global.fontTitel);
                   Pnl.add(LblMandant);
                  GB.add(Pnl);
                  Pnl=new JPanel(new FlowLayout(FlowLayout.CENTER,2,2));
                   EdtMandant.setFont(Transact.fontStandard);
                   g.addLabel3(Pnl,"Mandant");
                   Pnl.add(EdtMandant);
                   EdtMandantPasswort.setFont(Transact.fontStandard);
                   g.addLabel3(Pnl,"Passwort");
                   Pnl.add(EdtMandantPasswort);
                  GB.add(Pnl);
                  Pnl=new JPanel(new FlowLayout(FlowLayout.CENTER,2,2));
                   //CbxAufnehmen.setFont(g.fontBezeichnung);
                   CbxAufnehmen.setHorizontalAlignment(SwingConstants.RIGHT);
                   Pnl.add(CbxAufnehmen);
                   Pnl.add(CbxGauge);
                  GB.add(Pnl);
                  FrmOption.getContentPane().add("North",GB);
                  //GB.add(new JLabel(" "+g.getBegriff("Label","Passwort")+":"));

                  //Pnl4.add(GB);

                  JPanel Pnl3=new JPanel(new GridLayout(0,1,2,2));
                  // Def-Import
                  //GB = new GroupBox(g.getBegriff("Show","Import")+" "+g.getBegriff("Show","Update"));
                  //GB.setLayout(new FlowLayout(FlowLayout.CENTER,2,2));
                  JButton BtnChange = g.getButton("Change");
                  BtnChange.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent e)
                    {
                    	boolean b=g.Disconnected();
                    	if (b) g.connect(null);
                    	Versionsupdate.getChange(g).showGrid("change",FrmOption);
                    	if (b) g.unConnect();
                    }
                  });
                  JButton BtnEntsperren=g.getButton("Entsperren");
                  JButton BtnImport_D = g.getButton("DefImport");
                  if (Static.bNotfall)
                	  BtnImport_D.setEnabled(false);
                  else
                    BtnImport_D.addActionListener(new ActionListener()
                    {
                      public void actionPerformed(ActionEvent e)
                      {
                        if (g.Disconnected())
                          g.connect(null);
                        if (EdtMandant.getText().equals("All") && new String(EdtMandantPasswort.getPassword()).equals("All"))
                        {
                          //int iAnz=SQL.getInteger(g,"select count(*) from logging where aus is null");
                          if (!Reinigung.Ausgeloggt(g,FrmOption,0) && new Message(Message.YES_NO_OPTION,FrmOption,g,20).showDialog("Eingeloggt2") == Message.YES_OPTION)
                            g.exec("update logging set aus=mom,status=(status&"+Transact.WEBLOG+")+"+Transact.MANUELL+" where aus is null");
                        }
                        else //if (Static.bLocal || Static.bNotfall)
                        {
                          BtnAnmelden.setEnabled(false);
                          DefImport.start(g,FrmOption).show2(FrmOption);
                        }
                        //else
                        //  new Message(Message.WARNING_MESSAGE,null,g).showDialog("nur_Lokal_aufrufbar");
                      }
                    });
                  JButton BtnTabDefImport = g.getButton("Versionsverlauf");
                  BtnTabDefImport.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                    	boolean b=g.Disconnected();
                    	if (b) g.connect(null);
                      new Tabellenspeicher(g,"select Anfang,Filename,Version,Programm,Prog_Ver"+g.bei("Art",2,"normal")+g.bei("Art",1,"test")+g.bei("Art",4,"web")+g.bei("Status",1,"Fehler")+g.bei("Status",32,"Fehlerzeilen","status/256","-1")+
                                           ",Ende,(select kennung from computer where aic_computer=defimport.aic_computer) computer"+(g.MySQL()? ",TIMEDIFF(Ende,Anfang) Dauer":"")+" from defimport"+
                                           (g.Def() ? "":" where Anfang>"+g.DateTimeToString(g.getBeginn()))+" order by aic_defimport desc",true).showGrid("DefImporte",FrmOption);
                      if (b) g.unConnect();
                    }
                  });

                  //GB.add(BtnImport_D);
                  //GB.add(BtnTabDefImport);
                  //Pnl3.add(GB);

                  // Lizenz-Import
                  JButton BtnImport_L = g.getButton("LizImport");
                  final FileEingabe FE_Import_L = new FileEingabe(g);
                  if (Static.bNotfall)
                	  BtnImport_L.setEnabled(false);
                  else
                    BtnImport_L.addActionListener(new ActionListener()
                        {
                                public void actionPerformed(ActionEvent e)
                                {
                                	if (g.Disconnected())
                              			g.connect(null);
                                    new ImpLizenz(FE_Import_L.getValue(),g,FrmOption);
                                }
                        });
                  GB = new GroupBox(g.getBegriffS("Show","Import Lizenz"));
                  GB.setLayout(new FlowLayout(FlowLayout.CENTER,2,2));
                  JLabel Lbl=new JLabel(g.getBegriffS("Show","File")+":");
                  Lbl.setFont(Global.fontBezeichnung);
                  GB.add(Lbl);
                  GB.add(FE_Import_L);
                  GB.add(BtnImport_L);
                  Pnl3.add(GB);
                  //Pnl4.add(Pnl3);

                  /*GB = new GroupBox(g.getBegriff("Show","Speicherwarnung"));
                  GB.setLayout(new GridLayout(0,6,5,10));
                  g.addLabel3(GB,"ab");
                  GB.add(SboMeldungStart);
                  GB.add(new JLabel("MB"));
                  g.addLabel3(GB,"alle");
                  GB.add(SboMeldungWeiter);
                  GB.add(new JLabel("MB"));
                  GB.add(new JLabel());
                  GB.add(g.Def() ? (JComponent)CbxGauge:new JLabel());*/
                  //Pnl4.add(CbxGauge);

                  FrmOption.getContentPane().add("Center",Pnl3);

                  Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
                  JButton BtnOk = g.getButton("Ok");
                  FrmOption.getRootPane().setDefaultButton(BtnOk);
                  JButton BtnAbbruch = g.getButton("Abbruch");
                  BtnOk.addActionListener(new ActionListener()
                  {
                          public void actionPerformed(ActionEvent e)
                          {
                                  //lSpeicher=SboMeldungStart.intValue()*1024*1024;
                        	  bNewComputer = !EdtMandant.getText().equals("");// && new String(EdtMandantPasswort.getPassword()).equals(""));
                        	  boolean b=g.isConnected();
                        	  if (EdtMandant.Modified() || CbxGauge.Modified())
                        		  if (!b)
                                	  g.connect(null);
                              if (CbxGauge.Modified())
                        	  {
                        		  g.fixtestError("gauge speichern:"+CbxGauge.isSelected());
                                  
                                  
                                  Para.setParameter("OptionIE","123",/*SboMeldungStart.intValue()*/new Long(Runtime.getRuntime().maxMemory()*8/10).intValue(),
                                      /*SboMeldungWeiter.intValue()*/new Long(Runtime.getRuntime().maxMemory()/10).intValue(),CbxGauge.isSelected()?1:0,0,false,true);
                                  //bOption=false;
                                  
                                  CbxGauge.setAktSelected(CbxGauge.isSelected());
                        	  }
//                              String s=EdtMandant.Fehler(false);
                              if (Text.isKennung2(EdtMandant.getText(),false))
                            	  FrmOption.setVisible(false);
                              else
                            	  Meldung("KennungUmlaut","Es wurde kein gültiger Mandant eingegeben","Mandantfehler");
                              if (!b)
                                  g.unConnect();
                              BtnAnmelden.setEnabled(DefImport.iAnzahl==0);
//                            	  Passwort.PW_Message(g,FrmOption,s);
                          }
                  });

                  BtnAbbruch.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent e)
                    {
                      boolean bNoPara0 = Para.sParameterzeile.equals("");
                      bNewComputer = /*!EdtMandant.getText().equals("") ||*/ !Static.bMandantensuche && g.getMandant()==0;
                      //g.fixInfo("Abbruch: bNewComputer="+bNewComputer+", Mandant="+g.getMandant(0,"Kennung")+"("+g.getMandant()+")");
                      CbxGauge.setSelected(bNoPara0 ? false : Para.int3==1);
                      EdtMandant.setText("");
                      FrmOption.setVisible(false);
                      BtnAnmelden.setEnabled(DefImport.iAnzahl==0);
                    }
                  });
                  BtnEntsperren.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent e)
                    {
                      boolean b=g.Disconnected();
      	              if (b) g.connect(null);
                      if (Message.SecCheck(g,FrmOption,"DefImport-Check","FS7615"))
                      {
                        if (DefImport.Entsperren(g,FrmOption))
                          new Message(Message.WARNING_MESSAGE, FrmOption, g,10).showDialog("entsperrt");
                        else if (!Static.Leer(Static.sWeb))
						              g.sendWebDB("okDB",null,new JFrame());
                      }
                      if (b) g.unConnect();
                    }
                  });
                  Pnl.add(BtnChange);
                  Pnl.add(BtnImport_D);
                  Pnl.add(BtnEntsperren);
                  Pnl.add(BtnTabDefImport);
                  Pnl.add(BtnOk);
                  Pnl.add(BtnAbbruch);
                  FrmOption.getContentPane().add("South",Pnl);

                  FrmOption.pack();
                  Static.centerComponent(FrmOption,Fom);
        }

        private void showGauge()
        {
          if (FrmGauge==null)
          {
            FrmGauge = new JFrame(g.getBegriffS("Show", "Gauge"));
            FrmGauge.getContentPane().setLayout(new GridLayout(2, 1));
            Bar1 = new JProgressBar(0);
            Bar2 = new JProgressBar(0, 0, new Long(Runtime.getRuntime().maxMemory()/iMemD).intValue());
            Bar1.setStringPainted(true);
            Bar2.setStringPainted(true);
            FrmGauge.getContentPane().add(Bar1);
            FrmGauge.getContentPane().add(Bar2);
            FrmGauge.setSize(300, 100);
          }
          if(!FrmGauge.isShowing())
            FrmGauge.setVisible(true);
        }
        
    private void createFom()
    {
    	if (Fom==null)
    	{
            Fom=new JFrame(Run.sTitel==null ? "All Unlimited ":Run.sTitel);
            Fom.setResizable(false);
    	}
    }

	private void Build()
	{
		//Font FontNormal = new Font("Sans Serif",Font.PLAIN,14);
                //if (sBenutzer==null)
                //  Static.printError("Run.Build mit "+sBenutzer);
                int iFF=Static.bND ? 100:g.getFontFaktor();
                g.fixInfo("FF="+iFF);
                createFom();
	 	JPanel Pnl = new JPanel(null);//new JPanel(new GridLayout(0,1,0,5));

		/*
		getContentPane().add("West",Pnl);
		//add("West",Pnl);

		JLabel Lbl = new JLabel(" "+g.getBegriff("Label","Version")+":");
		Lbl.setFont(FontNormal);
		Pnl.add(Lbl);
		Lbl = new JLabel(" "+g.getBegriff("Label","Name")+":");
		Lbl.setFont(FontNormal);
		Pnl.add(Lbl);
		Lbl = new JLabel(" "+g.getBegriff("Label","Passwort")+":");
		Lbl.setFont(FontNormal);
		Pnl.add(Lbl);

	 	Pnl = new JPanel(new GridLayout(0,1,0,5));
		getContentPane().add("Center",Pnl);
		//add("Center",Pnl);

		BtnVersion.setFont(FontNormal);
		Pnl.add(BtnVersion);
		EdtName.setFont(FontNormal);
		Pnl.add(EdtName);
		EdtNamePasswort.setEchoChar('*');
		EdtNamePasswort.setFont(FontNormal);
		Pnl.add(EdtNamePasswort);

		JPanel PnlSouth = new JPanel(new BorderLayout());
		BtnAnmelden = new JButton(g.getBegriff("Button","Anmelden"));
		BtnAnmelden.setFont(FontNormal);
		BtnAnmelden.setEnabled(!g.gesperrt());
		BtnOption = new JButton("*");
		BtnOption.setFont(FontNormal);

		PnlSouth.add("Center",BtnAnmelden);
		PnlSouth.add("East",BtnOption);
		getContentPane().add("South",PnlSouth);
		//add("South",PnlSouth);
		*/

		BtnAnmelden = g.getButton("Anmelden");
		BtnAnmelden.setFont(Global.fontButton);
		BtnAnmelden.setEnabled(Static.bNotfall || !g.gesperrt());
		g.removeHover(BtnAnmelden);
        Fom.getRootPane().setDefaultButton(BtnAnmelden);
		BtnOption = g.getButton("...");
		BtnOption.setFont(Global.fontButton);
        JLabel LblVersion=new JLabel(Version.getVersion()+" ("+Version.getDate()+")",SwingConstants.CENTER);
        EdtName = new Text("",40);
        EdtNamePasswort=new AUPasswort();
        EdtNamePasswort.addKeyListener(new KeyListener()
                                          {
                                            public void keyPressed(KeyEvent e) { }

                                            public void keyReleased(KeyEvent e) {
                                              //g.fixInfo("keyReleased:"+e.getKeyCode()+"/"+e.getModifiers());
                                              //if (e.getKeyCode()==76 && e.getModifiers()==11)
                                              int iC=e.getKeyCode();
                                              int iM=e.getModifiers();
                                              if ((iC==76 || iC==521) && iM==11 || iC==106 && iM==10 || (iC==106 || iC==93) && g.Mac())
                                                showOptiondialog();
                                            }

                                            public void keyTyped(KeyEvent e) { }
                          });
                //BtnVersion.setToolTipText(""+Version.getDate());
		LblVersion.setFont(Global.fontBezeichnung);
		EdtName.setFont(Transact.fontStandard);
        
		EdtNamePasswort.setEchoChar('*');
		EdtNamePasswort.setFont(Transact.fontStandard);

		Pnl.setLayout(new BorderLayout(2,2));
		//Color Col=new Color(1,41,130);
                Color Col=Static.bND ? Global.ColHS:Color.white;
		Pnl.setBackground(Col);
                //if (sBenutzer==null)
                //{
                  Pnl.add("Center", BtnAnmelden);
                  if (Run.bUseOption)
                    Pnl.add("East", BtnOption);
                //}

		BtnOption.setBackground(Col);
		//Color ColFore = System.getProperty("os.name").equals("Mac OS X")?Color.black:Color.white;
                Color ColFore = Color.black;
		BtnOption.setForeground(ColFore);
		BtnAnmelden.setBackground(Col);
		BtnAnmelden.setForeground(ColFore);
		//BtnVersion.setBackground(Col);
		//BtnVersion.setForeground(ColFore);

		//Fom.getContentPane().setLayout(new GridLayout(0,2,2,2));
		//g.fixtestError("FF="+iFF);
		Fom.getContentPane().setBackground(Col);
		if (Static.bND)
		{
            EdtName.setBounds(70,150,170,26);
            EdtNamePasswort.setBounds(70,230,170,26);
            LblVersion.setBounds(70,280,170,26);
            Pnl.setBounds(30,320,250,26);
            BtnAnmelden.setText("LOGIN");
            BtnAnmelden.setBackground(Color.RED);
            BtnAnmelden.setForeground(Color.WHITE);
            if (g.fontLogin != null)
            {
            	EdtName.setFont(g.fontLogin);
            	EdtNamePasswort.setFont(g.fontLogin);
            	LblVersion.setFont(g.fontLogin);
            	BtnAnmelden.setFont(g.fontLogin);
            }
		}
        else
        {
            EdtName.setBounds(326*iFF/100,145*iFF/100,130*iFF/100,26*iFF/100);
            EdtNamePasswort.setBounds(460*iFF/100,145*iFF/100,130*iFF/100,26*iFF/100);
            LblVersion.setBounds(326*iFF/100,175*iFF/100,130*iFF/100,26*iFF/100);
            Pnl.setBounds(460*iFF/100,175*iFF/100,130*iFF/100,26*iFF/100);
        }      
        Fom.getContentPane().add(EdtName);
        if (Static.bAzure)
        {
        	EdtName.setEditable(false);
        	BtnToken=g.getButton("Azure-Login");
        	BtnToken.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                	BtnToken.setEnabled(false);
                	g.connect(null);
                	Azure api = new Azure();
//                    api.init("98202219-5afd-4568-846b-c7c8b82dfa2f","89ba77e5-10b6-4ba3-9f4e-9021df032708","User.Read");
                	if (api.init(g))
                	{
                	  Run.sToken= api.aquireToken(g);
//                	  g.fixtestError("Token="+Run.sToken);
                	  if (Run.sToken != null)
                	  {
                    	String sUser=api.getUserName();
                    	EdtName.setText(sUser);
//                    	g.fixtestError("prüfe Token: "+g.checkToken(Run.sToken, sUser));
                    	BtnAnmelden_actionPerformed(null);
                	  }
                	  else
                		  BtnToken.setEnabled(true);
                	}
                	else
                	{
                		Static.printError("Azure nicht initioalisierbar");
                		BtnToken.setEnabled(true);
                	}
                }
            });
        	if (Static.bND)
        		BtnToken.setBounds(70,230,170,26);
        	else
        		BtnToken.setBounds(460*iFF/100,145*iFF/100,130*iFF/100,26*iFF/100);
        	Fom.getContentPane().add(BtnToken);
        }
        else
        	Fom.getContentPane().add(EdtNamePasswort);
        
		Fom.getContentPane().add(LblVersion);
                Fom.getContentPane().add(Pnl);
                //g.fixInfo("Bitmap für "+(Static.bND ? "ND":Run.bSiemens ? "Siemens":"Normal"));
                String sBild=Static.bND ? "Startbitmap-ND.png":Run.bSiemens ? "startbitmap_siemens.jpg":"startbitmap.jpg";
                ImageIcon Img=g.getImageIcon(sBild);
                if (!Static.bND && iFF!=100 && Img != null)
                {
                  float iWidth = Img.getIconWidth();
                  float iHeight = Img.getIconHeight();
                  Image rImg = g.getImage(sBild);
                  if (rImg != null)
                    Img = new ImageIcon(rImg.getScaledInstance(Math.round(iWidth * iFF / 100), Math.round(iHeight * iFF / 100), Image.SCALE_SMOOTH));
                }
                JLabel LblBild=new JLabel(Img,SwingConstants.LEFT);
                //LblBild.setBounds(0,0,600,300);
                Fom.getContentPane().add(LblBild);
                Fom.pack();
                //Fom.setDefaultCloseOperation(Fom.DISPOSE_ON_CLOSE);
                Fom.addWindowListener(new WindowListener()
                        {
                                public void windowClosed(WindowEvent e)
                                {
                                  showStatus("windowClosed");
                                }
                                public void windowOpened(WindowEvent e) {
                                	//g.fixtestError("Fom.open:"+Fom.getWidth()+"x"+Fom.getHeight());
                                	Dimension DimS=Toolkit.getDefaultToolkit().getScreenSize();
                                	Fom.setLocation((DimS.width-Fom.getWidth())/2, (DimS.height-Fom.getHeight())/2);
                                }
                                public void windowClosing(WindowEvent e)
                                {
                                  if (g != null && g.getLog()>0)
                                  {
                                    Fom=null;
                                  }
                                  else
                                  {
                                    showStatus("window Closing");
                                    Fom.dispose();
                                    System.exit(0);
                                  }
                                }
                                public void windowIconified(WindowEvent e) {}
                                public void windowDeiconified(WindowEvent e) {}
                                public void windowActivated(WindowEvent e) {}
                                public void windowDeactivated(WindowEvent e) {}
                        });

                Fom.setVisible(true);
	}
	public void start(String[] args)
        {
			Static.addJavaKonsole();
          showStatus("try to connect..");
		//long lClock=Static.get_ms();
                //System.out.println("start:"+Transact.lClock0);
		
                Run.list.clear();
                for(int i=0;i<args.length;i++)
                	Run.list.add(args[i]);
                Run.setPara();
                Run.setOpt();
                
		//Global.fixInfo("vor PArt");
		
                //Static.HilfeVerzeichnis=sHelp==null ? sImage+"hmtl\\":sHelp;
                //Transact.fixInfo("DirHilfe="+Static.HilfeVerzeichnis);
                if (Static.sAbfrageErsatz != null)
                  Transact.fixInfoS("Ersatz="+Static.sAbfrageErsatz);
                //Static.app = this;
                //Transact.fixInfo("Adresse ermitteln");
                try
                {
                  String sIP2=java.net.InetAddress.getLocalHost().getHostAddress();
                  Static.sIP="IP"+sIP2.substring(sIP2.lastIndexOf('.')+1);
                }
                catch(Exception e)
                {}
                //Transact.fixInfo("Verbindung prüfen");
                Transact.bConnected=false;
                if (Run.sConnection2 != null && Run.sConnection2.length()>0)
                {
                  Transact t = new Transact();
                  t.bExit = false;
                  if (t.connect(Run.sConnection))
                    t.unConnect();
                  else
                    Run.sConnection=Run.sConnection2;
                }
                //Transact.fixInfo("Global erzeugen");
		g = new Global(Run.sConnection,null,Run.sQryUser,Run.sQryPW,Run.iQryPArt);
		if (!g.isConnected())
		{
//			Static.sleep(1000);
//			System.exit(0);
			return;
		}
                showStatus("connected");
                Run.setDbPara(g);
//                Reinigung.check1970(g);
                //if (g.ASA() || g.MySQL()) Static.bView2=false;

                //Static.bComboLeer=Static.bComboLeer && !g.MS();
                /*double d1=-1;
                double d2=-1.0;
                g.testInfo("-1="+d1+", -1.0="+d2);*/
//                Transact.fixInfo("Datum/Uhrzeit="+new DateWOD().Format("dd.MM.yyyy HH:mm"));
//                Transact.fixInfo("All Unlimited-Version="+Version.getVersion()+(g.TestPC() ? " ("+Version.getDate()+")":""));
//                Ampel.check(g);
                try
                {
//                  if (!Static.Java8())
//                  	UIManager.setLookAndFeel(/*new MetalLookAndFeel());*/"com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//                  else 
                	  if (Static.cLook=='-' || Static.cLook=='+')
                    UIManager.setLookAndFeel(Static.bDefaultLook ? UIManager.getCrossPlatformLookAndFeelClassName():UIManager.getSystemLookAndFeelClassName());
                  else //if (Static.Java8())
                  {
                	  if (Static.cLook=='W')
	                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	                  else if (Static.cLook=='C')
	                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//	                  else if (Static.Java8() && Static.cLook=='G')
//	                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
	                  else if (Static.cLook=='/') // CDE / Motif
	                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	                  else if (Static.cLook=='M') // Metall
		                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	                  else if (Static.cLook=='N') // Nimbus
	                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	                  else if (Static.cLook=='A') // Aqua, Appl
		                    UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
	                  else
	                  {
	                	  Static.printError("Look&Feel "+Static.cLook+" wird nicht unterstützt\n");
	                	  for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
	                		  g.fixInfo(" unterstützt wird "+info.getClassName());
	                  }
                  }
                }
                catch (UnsupportedLookAndFeelException ex1)
                {g.testInfo("ex1="+ex1);}
                catch (ClassNotFoundException ex2)
                {g.testInfo("ex2="+ex2);}
                catch (IllegalAccessException ex3)
                {g.testInfo("ex3="+ex3);}
                catch (InstantiationException ex4)
                {g.testInfo("ex4="+ex4);}

		//Ausgabe("Anfang:"+Runtime.getRuntime().freeMemory()+"/"+Runtime.getRuntime().totalMemory());
		
		//g.printInfo(2,Static.bBilder?"Bild-Verzeichnis="+Static.ImageVerzeichnis:"keine Bilder");
		//g.printInfo(2,"Hilfe-Verzeichnis="+Static.HilfeVerzeichnis);
		Ausgabe("new Global");
                g.ComputerErmitteln();
//                g.fixtestError("voreingest. Mandant="+g.MandantBez(g.getMandant())+" ("+g.getMandant()+")");
//                Reinigung.Ausgeloggt(g, null, 0); // nur zum testen
                if (Static.bNotfall)
                  new Message(Message.WARNING_MESSAGE, Fom, g).showDialog("Notfall");
                else
                {
                  if (isGesperrt())
                    return;
                  // if (g.getMandant() == -2)
                  // {
                  //   //JOptionPane.showMessageDialog(new JFrame(),new JLabel("Update wird eingespielt, Sie dürfen nicht starten!"),"gesperrt",JOptionPane.ERROR_MESSAGE);
                  //   new Message(Message.WARNING_MESSAGE, Fom, g).showDialog("gesperrt2");
                  //   System.exit(0);
                  // }
                  if (Version.UpdateNoetig(g) && !Reinigung.Ausgeloggt(g, null, 0))
                  {
                    //JOptionPane.showMessageDialog(new JFrame(),new JLabel("Bitte ensprechende Benutzer ausloggen und dann erneut starten!"),"ausloggen nötig",JOptionPane.ERROR_MESSAGE);
                    new Message(Message.WARNING_MESSAGE, Fom, g).showDialog("ausloggen");
                    System.exit(0);
                  }
                }
		Version ver= new Version(g);
		if(ver.OK())
		{

			Ausgabe("Versionscheck:");
			//g.ComputerErmitteln();
			//if(!bNoPara)
			//	Static.ImageVerzeichnis = Para.sParameterzeile;
			lSpeicher=Runtime.getRuntime().maxMemory()*8/10;
                        if (Run.sBenutzer==null)
                        {
                          //g.checkError(false);
                          g.checkSpracheLand();
                          //String sJava=System.getProperty("java.version");
                          //if (sJava.startsWith("1.4"))
                          //  new Message(Message.WARNING_MESSAGE,(JFrame)null,g,30).showDialog("falsches_Plugin",new String[] {sJava});
                        }
                        else
                        {
                          //g.checkError(true);
                          //g.ComputerErmitteln();
                          //g.fixInfo("xxx: vor checkAServer");
                          BtnAnmelden = new JButton("");
                          //g.fixInfo("xxx: vor setLayout");
                          //Fom.getContentPane().setLayout(new GridLayout(0,2,2,2));
                          ///createFom();
                          ///Fom.getContentPane().setBackground(Color.WHITE);
                          ///JLabel LblV=new JLabel(Version.getVersion()+" ("+Version.getDate()+")",SwingConstants.CENTER);
                          ///Fom.getContentPane().add(LblV);
                          //lSpeicher=250*1024*1024;
                          //g.fixInfo("xxx: vor timer");
                          timer = new javax.swing.Timer(10000,new ActionListener()
                          {
                                public void actionPerformed(ActionEvent e)
                                {
                                        TimerBearbeitung();
                                }
                          });
                          //g.fixInfo("xxx: vor new JLabel");
                          g.printSI("fertig geladen");
                          new JLabel("",SwingConstants.CENTER);
                          ///Fom.getContentPane().add(LblUser);
                          //bAutologin=true;
                          BtnAnmelden_actionPerformed(null);
                          /*getContentPane().add(new JLabel(g.getMandant(0,"Bezeichnung")+" - "+sBenutzer,SwingConstants.CENTER));
                          g.clockInfo("Startapplet mit Auto-Login",lClock);
                          Static.FomStart.toFront();*/
                          //g.fixInfo("xxx: return");
                          //repaint();
                          return;
                        }
                        //if (sMandant != null && !sMandant.equals(""))
                        //  g.setMandant(sMandant);
			if (g.getComputer()==0)
			{
				//Fom.destroy();
				return;
			}
                        //checkAServer(sConnection);
			Para = new Parameter(g,"Login");
			boolean bNoPara = Para.getParameter("OptionIE",false,true).equals("");
			g.printInfo(0,"start:"+Runtime.getRuntime().freeMemory()+"/"+Runtime.getRuntime().totalMemory());


			Ausgabe("Global");

			bNewComputer=!Static.bMandantensuche && g.getMandant()==0;

                        /*if (sBenutzer != null)
                        {
                          EdtName.setText(sBenutzer);
                          EdtNamePasswort.setEnabled(false);
                        }
			else*/

			CbxGauge = g.getCheckbox("gauge",bNoPara || !g.Def() ? false : Para.int3==1);
			//g.setDebug(false);
			//SboMeldungStart.setValue(!bNoPara && Para.int1>=2 ?Para.int1:150);
			//SboMeldungStart.setMinimum(2);
			//SboMeldungWeiter.setValue(!bNoPara && Para.int2>=2 ?Para.int2:10);
			//SboMeldungWeiter.setMinimum(1);
			//lSpeicher=SboMeldungStart.intValue()*1024*1024;

			//SboRefresh.setIntValue(5);
			//SboRefresh.setMinimum(1);
			//CbxRefresh = g.getCheckbox("Refresh",true);
			//CbxAnzeige = g.getCheckbox("show",false);

			CbxAufnehmen = g.getCheckbox("Mandant merken",false);

			Build();

			if(Run.bUseOption)
				BtnOption.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
                                          showOptiondialog();
					}
				});


			/*EdtNamePasswort.addKeyListener(new KeyListener()
			{
				public void keyTyped(KeyEvent e)
				{
				}
				public void keyPressed(KeyEvent e)
				{
				}
				public void keyReleased(KeyEvent e)
				{
					if(e.getKeyCode() == e.VK_ENTER && BtnAnmelden.isEnabled())
					{
						long lClock0=Static.get_ms();
						BtnAnmelden_actionPerformed(null);
						g.clockInfo("Anmelden",lClock0);
					}
				}

			});*/

			/*
			BtnVersion.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Static.OpenURL(Static.app,"file://Server/au/test/help.html");
				}
			});
			*/

			BtnAnmelden.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					long lClock0=Static.get_ms();
					BtnAnmelden_actionPerformed(e);
					g.clockInfo("Anmelden",lClock0);
				}
			});

			timer = new javax.swing.Timer(5000,new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					TimerBearbeitung();
				}
			});

			//bLoaded=true;
			//JScrollPane Scr = new JScrollPane(Pnl);
			//JPanel Pnl2 = new JPanel();
			//Pnl2.add(Scr);
			//g.bZeigeInfo=false;

			Ausgabe("Aufbau");
                        //if (g.SuperUser())
                        //  g.printLastBenutzer(g.Prog() ? 20:10);
			//Ausgabe("Benutzerliste");
                        g.printInfo(0,"vor gc:"+Runtime.getRuntime().freeMemory()+"/"+Runtime.getRuntime().totalMemory());
			System.gc();
			g.printInfo(0,"nach gc:"+Runtime.getRuntime().freeMemory()+"/"+Runtime.getRuntime().totalMemory());
			Ausgabe("gc");
			//bPaint=true;
			//repaint();
			/*g.testInfo("Name=<"+EdtName.getText()+">");
			if (EdtName.getText().equals(""))
				EdtName.requestFocus();
			else
				EdtNamePasswort.requestFocus();*/
			//CheckGesperrt();
			g.clockInfo("Startapplet",lClock0);
                        if (Version.Test() && !Run.bSilent)
                          JOptionPane.showMessageDialog(Fom,new JLabel("Diese Testversion kann Daten zerstören!"),"Achtung Testversion",JOptionPane.WARNING_MESSAGE);
                        //g.fixtestError("Run.bOS_User="+Run.bOS_User+", Run.bLastUser="+Run.bLastUser);
                        if (Run.bOS_User)
                        	EdtName.setText(System.getProperty("user.name"));
                        else if (Run.bLastUser && !bNewComputer)
                          EdtName.setText(g.getLastBenutzer());
                        if (Run.bOS_User || Run.bLastUser)
                        	EdtNamePasswort.requestFocus();
                        g.unConnect();
                        showStatus("please login");
		}
		else
			g.fixInfo("Versionscheck:"+ver.sStatus);
    }

    /*private void checkAServer()
    {
      if (sAServer != null)
        if (TCalc.checkAServer(g,sAServer))
          Transact.fixInfo("Soap-Webserver auf " + sAServer + " gefunden");
        //else
        //  Static.sAServer = null;
    }*/

    private void showOptiondialog()
    {
        //g.fixInfo("bNewComputer="+bNewComputer+", Mandant="+g.getMandant(0,"Kennung")+"("+g.getMandant()+")");
        EdtMandant.setText("");
        EdtMandantPasswort.setText("");
        //bOption=true;
        if (FrmOption==null)
          BuildOptionen();
        LblMandant.setText(Static.bMandantensuche ? g.getShow("auto"):g.getMandant(0,"Bezeichnung"));
        FrmOption.setVisible(true);
        FrmOption.toFront();
        EdtMandant.requestFocus();
    }

    public void TimerBearbeitung()
    {
//      g.fixInfo("*** TimerBearbeitung:"+Static.bInternerTimer+"/"+g.TimerMessage()+"/"+g.isConnected()+"/"+lLastMessage);
      if (Static.bInternerTimer && g.TimerMessage() && g.isConnected() && (lLastMessage==0 || Static.get_ms()-lLastMessage>3599500))
        checkMessages();
        //if (CbxAnzeige.isSelected())
                                           	//g.fixtestError("<-Timer aufgerufen:"+(Static.get_ms()-lLogCheck));
                                           boolean bRaus=false;
                                           if (Static.get_ms()-lLogCheck>239500)
                                           {
                                                   //if (CbxAnzeige.isSelected())
                                                   //	g.progInfo("<-Timer: Logcheck");
                                        	       int iLC=g.Logcheck();
                                                   bRaus =iLC==0;
                                                   if (iLC>1)
                                                	   lLogCheck = Static.get_ms();

                                                   int iUpdate=SQL.getInteger(g,"select "+g.int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
                                                   //g.testInfo("**********************************************************        Rauswurf-Status:"+iUpdate);
                                                   if(!g.bSchutz && iUpdate==2 || iUpdate==6 && !bRaus)
                                                   {
                                                     bRaus=true;
                                                     if (iUpdate==6)
                                                     {
                                                       Vector Vec=SQL.getVector("select "+g.int1()+" from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='raus'",g);
                                                       bRaus=Vec.contains(new Integer(g.getLog()));
                                                     }

                                                   }
                                           }
                                           if (bRaus || !bFirstMessage || g.AutoLogout())
                                           {
                                        	 //g.fixtestError("aktueller Zeitstand="+(Static.get_ms()-lRausStart)+", bFirstMessage="+bFirstMessage);
                                             if (bFirstMessage)
                                             {
                                               lRausStart=Static.get_ms();
                                               bFirstMessage = false;
                                               //g.fixtestError("Warnung vor schließen");
                                               int iRet=0;
                                               if (!bRaus && g.AutoLogout())
                                                 iRet=new Message(Message.YES_NO_OPTION, Fom, g, 50).showDialog("Schliessfrage");
                                               else
                                            	   new Message(Message.WARNING_MESSAGE, Fom, g, 50).showDialog("Schliesswarnung");
                                               if (iRet==Message.YES_OPTION)
                                               {
                                            	   bFirstMessage=true;
                                            	   g.SaveVerlauf(g.getBegriffAicS("Message", "Schliessfrage"),0,0,0,"länger eingeloggt bleiben",0);
                                               }
                                             }
                                             else if ((Static.get_ms()-lRausStart)>59000)
                                             {
                                               bFirstMessage = true;
                                               //g.fixtestError("alles schließen");
                                               Static.sleep(1000);
                                               if (g.getBenutzer()>0)
                                                 Abmelden(true);
                                               Static.sleep(500);
                                               if (Static.FomStart != null)
                                               {
                                            	   Static.FomStart.dispose();
                                                   Static.sleep(2000);
                                               }
                                               System.exit(0);
                                             }
                                           }

                                           Runtime rt=Runtime.getRuntime();
                                           long lSpeicherMom = rt.totalMemory()/iMemD;
                                           //g.progInfo("****************************** "+Save.now()+": Speicher:"+Static.printZahl(lSpeicherMom,15));
                                           if(Run.sBenutzer==null && CbxGauge.isSelected())
                                           {
                                             showGauge();
                                                   //g.printInfo("Set Bar:"+(33554432-Runtime.getRuntime().totalMemory()));
                                                   if (Bar1.getMaximum() != lSpeicherMom)
                                                           Bar1.setMaximum(new Long(lSpeicherMom).intValue());
                                                   int iFree=(int)(rt.freeMemory()/iMemD);
                                                   Bar1.setValue(iFree);
                                                   Bar1.setString(new DecimalFormat().format(iFree)+" kB");
                                                   Bar2.setValue(new Long(rt.maxMemory()/iMemD-lSpeicherMom).intValue());
                                                   Bar2.setString(new DecimalFormat().format(lSpeicherMom)+" kB");
                                           }
                                           //g.progInfo("mom:"+lSpeicherMom+", max:"+lSpeicher);


                                           if (g!=null && !g.Mac() && lSpeicherMom>=lSpeicher)
                                           {
                                                   //lDialogTime=Static.get_ms();
                                                   lSpeicher=lSpeicherMom+Runtime.getRuntime().maxMemory()/10;//SboMeldungWeiter.intValue()*1024*1024;
                                                   //if (CbxAnzeige.isSelected())
                                                   //        g.progInfo("<-Timer: nächste Speichermeldung:"+lSpeicher);
                                                   new Message(Message.WARNING_MESSAGE,Fom,g,15).showDialog("Speicherwarnung",new String[] {""+Math.round(lSpeicherMom/1024/1024)+" MB"});
                                           }
    }

	/*public void paint(Graphics gr)
	{
          //if (g != null)
          //  g.fixInfo("paint:"+Transact.bConnected);
		try
		{
			super.paint(gr);
                        if (!Transact.bConnected)
                          ;
                        else if (bAutologin)
                        {
                          bAutologin=false;
                          BtnAnmelden_actionPerformed(null);
                          LblUser.setText(g.getMandant(0,"Bezeichnung")+" - "+sBenutzer);
                          g.printSI("fertig geladen");
                          g.clockInfo("Startapplet mit Auto-Login",lClock0);
                          //Static.FomStart.toFront();
                        }
			else if (!bOption && g.getBenutzer()==0)
			{
				//bFirst = false;
				//g.testInfo("paint: Name=<"+EdtName.getText()+">");
				if (sBenutzer != null)
                                  RF(BtnAnmelden,1);
                                else if (EdtName.getText().equals(""))
					RF(BtnOption,2);
				else
					RF(EdtNamePasswort,3);
			}
		}
		catch(NullPointerException e){}
	}*/

        /*private void RF(javax.swing.JComponent Comp,int i)
        {
          if (i != iRF)
          {
            Comp.requestFocus();
            iRF = i;
            g.testInfo("paint: requestFocus " + i);
          }
        }*/

	private void checkMessages()
	{
          lLastMessage = Static.get_ms();
          g.fixInfoT("*** checkMessages");
          //g.testInfo("**********************************************************        "+Save.now()+": checkMessages");
          if (iEigRead==-1)
            iEigRead=SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='read'");
          if (iEigMemo==-1)
            iEigMemo=SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='Memo'");
          if (iEigRead<=0 || iEigMemo<=0)
          {
        	  g.printError("Eigenschaft "+(iEigRead<=0 ? "read":"Memo")+" nicht vorhanden");
        	  return;
          }
		SQL Qry = new SQL(g);
		Qry.open((iEigRead>0 ? "select * from (":"")+
                        "select (select Bezeichnung from bezeichnung join tabellenname on bezeichnung.aic_tabellenname=tabellenname.aic_tabellenname,protokoll where tabellenname.kennung='Benutzer' and aic_fremd=aic_benutzer and aic_protokoll=bew_pool.aic_protokoll) von"+
                         (iEigRead>0 ? ",(select spalte_boolean from bew_boolean where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft="+iEigRead+") read2":"")+
			",bew_pool.aic_bew_pool,bew_pool.aic_bewegungstyp,gueltig,(select Spalte_Text from stammpool join daten_text on stammpool.aic_daten=daten_text.aic_daten where bew_pool.aic_bew_pool=stammpool.aic_bew_pool and aic_eigenschaft="+iEigMemo+") memo"+
                        " from eigenschaft join bew_stamm on eigenschaft.aic_eigenschaft=bew_stamm.aic_eigenschaft join bew_pool on bew_stamm.aic_bew_pool=bew_pool.aic_bew_pool where eigenschaft.kennung='receiver' and bew_pool.pro_aic_protokoll is null and gueltig<="+g.now()+" and bew_stamm.aic_stamm="+g.getStamm()+" and "+g.LokaleTimer()+
                         (iEigRead>0 ? ") x where read2 is null":""));
          g.clockInfo("checkMessages",lLastMessage);
		for(;!Qry.eof();Qry.moveNext())
		{
			//g.printInfo("-Message:"+Qry.getI("aic_bew_pool")+" / "+Qry.getTS("gueltig")+" / "+new All_Unlimited.Allgemein.Anzeige.Memo1(Qry.getS("Memo"),20));
			int iBew=Qry.getI("aic_bewegungstyp");
			int iBewPool=Qry.getI("aic_bew_pool");
			if (!VecMail.contains(iBewPool))
			{
				int pane = new Message(Message.OK_CANCEL_OPTION,null,g).showDialog("Nachricht",new String[] {Qry.getS("von")+", "+new SimpleDateFormat("dd.MM.yyyy HH:mm").format(Qry.getTS("gueltig"))+"\n\n"+Qry.getS("Memo")});
				if(pane == Message.OK_OPTION)
				{
//                  if (iEigRead>0)
//                  {
//                    Qry.add("aic_bew_pool",iBewPool);
//                    Qry.add("aic_eigenschaft",iEigRead);
//                    Qry.add("spalte_boolean",1);
//                    Qry.insert("bew_boolean",false);
//                  }
//                  else
//					  g.exec("update bew_pool set pro_aic_protokoll="+g.Protokoll("Eingabe",0/*unknown*/)+" where aic_bew_pool="+iBewPool);
					Import.insertBewBool(g, iBew, iBewPool, iEigRead);
				}
				VecMail.addElement(iBewPool);
			}
		}
	}
	private boolean isGesperrt()
	{
		if (g.gesperrt())
		{
			JFrame FomGesperrt =new JFrame("Falsche Eingabe");
			FomGesperrt.getContentPane().add("Center",new JLabel(g.getImageIcon("falsepass.png")));
			//FomGesperrt.setBounds(100,100,450,550);
                        FomGesperrt.pack();
                        FomGesperrt.setResizable(false);
                        Static.centerComponent(FomGesperrt, Fom);
			FomGesperrt.setVisible(true);
                        return true;
		}
                else
                  return false;
	}

//  private void OpenHS(final Global g,final String s,final int iAic)
//  {
//    new Thread(new Runnable()
//    {
//      public void run()
//      {
//        try
//        {
//          Object Obj = Hauptschirm.get(s, g);
//          //g.putTabFormulare(iAic, 0, Obj);
//        }
//        catch(Exception e)
//        {
//          g.printError("Fehler bei öffnen von Hauptschirm " + s + ":" + e,0);
//        }
//        checkReady();
//      }
//    }).start();
//  }
//
//  private void OpenEF(final Global g,final int iAic,final int iSatz)
//  {
//    new Thread(new Runnable()
//    {
//      public void run()
//      {
//        try
//        {
//          int iStt=g.getStt(iAic);
//          int iStamm=0;
//          if (iStt>0)
//              iStamm=g.getSyncStamm(iStt);
//          if (iStamm==0)
//          {
//            iStamm=iSatz;
//            if (iStt>0)
//              g.setSyncStamm(iStt,iStamm);
//          }
//          g.progInfo("EF mit Stt="+iStt+", iStamm="+iStamm+" statt "+iSatz);
//          EingabeFormular.HoleFormular(g, iAic, Static.AicToVec(iStamm), iStamm, false);
//        }
//        catch(Exception e)
//        {
//          g.printError("Fehler bei öffnen von EingabeFormular "+SQL.getString(g,"select defbezeichnung from begriff where aic_begriff="+iAic)+" (" + iAic + "):" + e,iAic);
//        }
//        checkReady();
//      }
//    }).start();
//  }
//
//  private void checkReady()
//  {
//    iAnzBG--;
//    if (iAnzBG<=0)
//    {
//      g.bLogout=true;
//      Static.bVerlauf=bVerlauf;
//      if (g.BtnLogout != null)
//        g.BtnLogout.setEnabled(true);
//    }
//  }
//
//  private void RestFenster(Global g)
//  {
//    iAnzBG=g.TabFensterpos.size();
//    if (iAnzBG==0)
//      checkReady();
//    else
//     for (int iPos=0;iPos<g.TabFensterpos.size();iPos++)
//     {
//      if ((g.TabFensterpos.getI(iPos,"bits")&1)>0)
//      {
//        int iPosB=g.TabBegriffe.getPos("Aic",g.TabFensterpos.getI(iPos,"vfenster"));
//        int iPosBG=iPosB<0 ? -1:g.TabBegriffgruppen.getPos("Aic",g.TabBegriffe.getI(iPosB,"Gruppe"));
//        //g.progInfo("Rest:"+iPosB+"/"+iPosBG);
//        String sBG= iPosBG<0 ? "":g.TabBegriffgruppen.getS(iPosBG,"Kennung");
//        if (sBG.equals("Applikation"))
//        {
//          OpenHS(g,g.TabBegriffe.getS(iPosB,"Kennung"),g.TabBegriffe.getI(iPosB,"Aic"));
//          //Static.sleep(100);
//          //Object Obj=new Hauptschirm(g.TabBegriffe.getS(iPosB,"Kennung"), g);
//          //g.putTabFormulare(g.TabBegriffe.getI(iPosB,"Aic"),0,Obj);
//        }
//        else if (sBG.equals("Frame"))
//        {
//          int iAic=g.TabBegriffe.getI(iPosB,"Aic");
//          Object Obj=null;
//          if (g.TabBegriffe.getI(iPosB,"Typ")==g.SystemFormular())
//          {
//            AU_Formular.OpenSystem(g,Fom, g.TabBegriffe.getS(iPosB, "Kennung"));
//            checkReady();
//          }
//          else if (g.TabBegriffe.getI(iPosB,"Stt")>0)
//            /*checkReady();*/OpenEF(g,iAic,g.TabFensterpos.getI(iPos,"satz"));
//          else
//          {
//            Obj = new AU_Formular(Formular.getForm(g, iAic), g);
//            ((AU_Formular)Obj).thisFrame.setVisible(true);
//            checkReady();
//          }
//          if (Obj != null)
//            g.putTabFormulare(iAic,0,Obj);
//          //Fom.thisFrame.setVisible(true);
//        }
//      }
//      else
//        checkReady();
//     }
//  }

	/*public void run()
	{

		JFrame Frm = new JFrame();
		JLabel Lbl = new JLabel(".");
		Frm.getContentPane().setLayout(new BorderLayout());
		Frm.getContentPane().add("Center",Lbl);
		Frm.setSize(200,100);
		Frm.show();

		try
		{
			while(!bLoaded)
			{
				Lbl.setText(Lbl.getText()+".");
				Frm.repaint();
				thread.sleep(1000);
			}
			//Frm.hide();

			thread.stop();
		}
		catch(InterruptedException e){}


	}
	*/
	/*public void paint(Graphics gr)
	{
		if(bPaint)
		{
			bPaint=false;
			if (!g.bLinux)
			{
				int iWidth=ImgBackground.getWidth(this);
				int iHeight=ImgBackground.getHeight(this);
				int iAppWidth=getSize().width;
				int iAppHeight=getSize().height;
				for(int iY=0;iY<iAppHeight;iY+=iHeight)
				   for(int iX=0;iX<iAppWidth;iX+=iWidth)
					  gr.drawImage(ImgBackground,iX,iY,iWidth,iHeight,this);
			}

			Component[] comp = Pnl.getComponents();
			for(int i=0;i<comp.length;i++)
			{
				comp[i].repaint();
				((JComponent)comp[i]).setOpaque(false);
			}
			comp = PnlWest.getComponents();
			for(int i=0;i<comp.length;i++)
			{
				comp[i].repaint();
				((JComponent)comp[i]).setOpaque(false);
			}
			comp = PnlSouth.getComponents();
			for(int i=0;i<comp.length;i++)
			{
				comp[i].repaint();
				//((JComponent)comp[i]).setOpaque(false);
			}

			if (bFirst)
				if(bNewComputer)
				{
					EdtMandant.requestFocus();
					Pnl3.repaint();
				}
				else
					if (EdtName.getText().equals(""))
						EdtName.requestFocus();
					else
						EdtNamePasswort.requestFocus();
			bFirst = false;
			bPaint = true;
		}
	}*/


    // add your data members here
        private long lClock0 = Static.get_ms();
	//private Zahl SboMeldungStart= new Zahl(0);
	//private Zahl SboMeldungWeiter= new Zahl(0);
	private long lSpeicher;
	private long iClock1 = 0;
	private long iClock2 = lClock0;
	private long lLogCheck = lClock0;
	//private long lDialogTime;
	private Text EdtName = null;//new JTextField();
	private AUPasswort EdtNamePasswort= null;
	private Text EdtMandant = new Text("",40);
	private AUPasswort EdtMandantPasswort = new AUPasswort(20);
	private AUCheckBox CbxAufnehmen;
	private AUCheckBox CbxGauge;
	private JButton BtnAnmelden;
	private JButton BtnOption;
	//private JButton BtnVersion;
	//private JButton BtnOk;
	//private JButton BtnAbbruch;
	private Global g=null;
	//private JLabel lbl = new JLabel("                        ");

	//private JCheckBox CbxRefresh;
	//private JCheckBox CbxAnzeige;

	//private boolean bGesperrt;
	private boolean bNewComputer = false;
        //private boolean bKAF=false; // keine automatische Formularletzstandherstellung

	private javax.swing.Timer timer;
	//private Memo SqlInfo = null;

	//private Image ImgBackground;

	//private boolean bPaint=false;
	//private boolean bOption = false;
	//private boolean bUseOption=true;
    //private boolean bSiemens=false;

	private JDialog FrmOption;
	//private Message DlgSpeicher=null;
	private JFrame FrmGauge;

        private JLabel LblMandant;
	private Parameter Para;
	private JProgressBar Bar1;
	private JProgressBar Bar2;

	//private FileEingabe FE_Import_D;
	//private JCheckBox CbxImport_AN = new JCheckBox();
	//private JButton BtnImport_D;
	//private FileEingabe FE_Import_L;
	//private JButton BtnImport_L;

	private java.util.Vector<Integer> VecMail=new java.util.Vector<Integer>();
        //private String sBenutzer=null;
        //private String sPW=null;
        //private String sAbfrageErsatz=null;
        //private int iPArt=0;
        private boolean bFirstMessage=true;
        //private boolean bInternerTimer=true;
        private long lLastMessage=0;
        private long lRausStart=0;
        private int iEigRead=-1;
        private int iEigMemo=-1;

        private JFrame FomErsatz=null;
        private JCOutliner OutErsatz=null;
        //private String sAServer=null;
//        private int iAnzBG=0;
//        private boolean bVerlauf=false;
        private JFrame Fom=null;
        //private String sTitel;
        private final int iMemD=1024;
        private JButton BtnToken=null;
	//private boolean bLoaded=false;
	//private Thread thread;
    // add your data members here
}

