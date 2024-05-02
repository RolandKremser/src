/*
    All_Unlimited-Grunddefinition-UserManager.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
//import All_Unlimited.Allgemein.Google;
import All_Unlimited.Allgemein.GroupBox;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.SimpleFileFilter;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Passwort;
import All_Unlimited.Allgemein.SMTP2;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Allgemein.Eingabe.StammEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Zeitraum;
import All_Unlimited.Hauptmaske.Abschluss;

import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import java.awt.Insets;
import java.awt.Window;

import javax.swing.JPopupMenu;

import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import jclass.bwt.JCOutlinerComponent;
import java.awt.event.MouseEvent;

import All_Unlimited.Allgemein.Parameter;

public class UserManager extends All_Unlimited.Allgemein.Formular
{
  private static UserManager Self=null;
  private ActionListener AL1=null;

  public static UserManager get(Global rg)
  {
    if (Self==null || Self.AL==null)
      Self=new UserManager(rg);
    else
      Self.show();
    return Self;
  }

  public static void free()
  {
     if (Self != null)
     {
       Self.g.winInfo("UserManager.free");
       Self.thisFrame.dispose();
       Self = null;
     }
  }
  
  private void addPopupForTab(JMenu Mnu,String s)
  {
    JMenuItem MnuTab = new JMenuItem(s);
    Mnu.add(MnuTab);
    MnuTab.setActionCommand(s);
    if (AL1 == null)
      AL1 = new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          String s2 = ev.getActionCommand();
//          ev.getSource();
          Tabellenspeicher.showGrid(TabAll, "TabAll", s2,thisFrame);
          Tabellenspeicher.showGrid(TabAbfrage, "TabAbfrage", s2,thisFrame);
          Tabellenspeicher.showGrid(TabErsatz, "TabErsatz", s2,thisFrame);
          Tabellenspeicher.showGrid(TabNachricht, "TabNachricht", s2,thisFrame);
        }
      };
      MnuTab.addActionListener(AL1);
    }
  
private void showFom(Formular Fom)
{
	if (!Fom.bFP)
		Static.centerComponent(Fom.thisFrame, thisFrame);
	Fom.show();
}

private void ShowBenutzer_Bits()
{
	Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr","Sub","Kennung","Bezeichnung","Anzahl"});
	addBArt(Tab2,"TEL","Tel",-7);
	addBArt(Tab2,"E_Mail","E-Mail",-6);
	addBArt(Tab2,"AIC_Sprache","Sprache",-5);
	addBArt(Tab2,"AIC_Land","Land",-4);
	addBArt(Tab2,"AIC_Stamm","Person",-3);
	addBArt(Tab2,"AIC_Benutzergruppe","Benutzergruppe",-2);
	addBArt(Tab2,"use_bis","bis",-1);
	addBSub(Tab2,"cstProg","Prog",Global.cstBenutzerEbene,Global.cstProg,0,1);
	addBSub(Tab2,"cstDef","Def",Global.cstBenutzerEbene,Global.cstDef,0,2);
	addBSub(Tab2,"cstVerw","Verwaltung",Global.cstBenutzerEbene,Global.cstVerw,0,3);
	addBSub(Tab2,"cstSuperUser","Superuser",Global.cstBenutzerEbene,Global.cstSuperUser,0,4);
	addBSub(Tab2,"cstUserManager","UserManager",Global.cstBenutzerEbene,Global.cstUserManager,0,5);
	addBBit(Tab2, "cstSpezial","Spezial", Global.cstSpezial, 2);
	addBBit(Tab2, "cstMehrmals","Mehrmals", Global.cstMehrmals, 3);
	addBBit(Tab2, "cstTimerBenutzer","Timer", Global.cstTimerBenutzer, 5);
	addBBit(Tab2, "cstImport","Import", Global.cstImport, 7);
	addBBit(Tab2, "cstHS","Hauptschirm", Global.cstHS, 8);
	addBBit(Tab2, "cstOhne","ohne Lizenz", Global.cstOhne, 9);
	addBSub(Tab2,"cstPW_MD5","PW_MD5",Global.cstPW,Global.cstPW_MD5,10,1);
	addBSub(Tab2,"cstPW_MD5B","PW_MD5B",Global.cstPW,Global.cstPW_MD5B,10,2);
	addBSub(Tab2,"cstPW_LDAP","PW_LDAP",Global.cstPW,Global.cstPW_LDAP,10,3);
	addBSub(Tab2,"cstPW_AZURE","PW_AZURE",Global.cstPW,Global.cstPW_AZURE,10,4);
	addBSub(Tab2,"cstPW_GOOGLE","PW_GOOGLE",Global.cstPW,Global.cstPW_GOOGLE,10,5);
  addBSub(Tab2,"cstPW_LTOKEN","PW_LTOKEN",Global.cstPW,Global.cstPW_LTOKEN,10,6);
  addBSub(Tab2,"cstPW_EMAIL","PW_EMAIL",Global.cstPW,Global.cstPW_EMAIL,10,7);
  addBSub(Tab2,"cstPW_KOMBI","PW_SMS",Global.cstPW,Global.cstPW_KOMBI,10,8);
	addBBit(Tab2, "cstDB","auf DB", Global.cstDB, 12);
	addBBit(Tab2, "cstAServerUser","AServer", Global.cstAServerUser, 13);
	addBBit(Tab2, "cstTerminalUser","Terminal", Global.cstTerminalUser, 14);
	addBBit(Tab2, "cstAutoFC","Auto-Fehlerkonsole", Global.cstAutoFC, 15);
	addBBit(Tab2, "cstOC","ohneComputer", Global.cstOC, 16);
	addBBit(Tab2, "cstVorlage","Vorlage", Global.cstVorlage, 17);
	addBBit(Tab2, "cstAPI","API", Global.cstAPI, 18);
	addBBit(Tab2, "cst2FA","2FA", Global.cst2FA, 19);
	addBBit(Tab2, "cstTest","TestB", Global.cstTest, 21);
	addBBit(Tab2, "cstNoMobile","noMobile", Global.cstNoMobile, 22);
  addBBit(Tab2, "cstNoAktiv","noAktiv", Global.cstNoAktiv, 23);
	
	JDialog FomGid = new JDialog((Frame)thisFrame, "Benutzer-Bits", false);
    AUOutliner Grid = new AUOutliner();
    FomGid.getContentPane().add("Center", Grid);
    Tab2.showGrid(Grid);
    FomGid.setSize(400, 600);
    Static.centerComponent(FomGid,thisFrame);
    Grid.addActionListener(new JCActionListener() {
      public void actionPerformed(JCActionEvent ev) {
        JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
        int i=Sort.geti(Nod.getLabel(),0);
        int i2=Sort.geti(Nod.getLabel(),1);
        long l=(long)Math.pow(2,i);
        long l2=0;
        String sSp=null;
        String sKen=null;
        if (i<0)
        {
        	sKen=Sort.gets(Nod.getLabel(),2);
        	sSp=sKen;
        	if (sSp.equals("AIC_Sprache") || sSp.equals("AIC_Land") || sSp.equals("AIC_Benutzergruppe"))
      		  sSp="(select kennung from "+sSp.substring(4)+" where "+sSp+"=benutzer."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
      	  else if (sSp.equals("AIC_Stamm"))
      		  sSp="(select bezeichnung from Stammview2 where aic_Rolle is null and aic_stamm=benutzer.aic_stamm) "+ Sort.gets(Nod.getLabel(),3);
        }
        else if (i == 0)
        {
          l=Global.cstBenutzerEbene;
          l2=i2==1 ? Global.cstProg:i2==2 ? Global.cstDef:i2==3 ? Global.cstVerw:i2==4 ? Global.cstSuperUser:i2==5 ? Global.cstUserManager:Global.cstNormalUser;
        }
        else if (i==10)
        {
        	l=Global.cstPW;
        	l2=i2==1 ? Global.cstPW_MD5:i2==2 ? Global.cstPW_MD5B:i2==3 ? Global.cstPW_LDAP:i2==4 ? Global.cstPW_AZURE:i2==5 ? Global.cstPW_GOOGLE:i2==6 ? Global.cstPW_LTOKEN:i2==7 ? Global.cstPW_EMAIL:i2==8 ? Global.cstPW_KOMBI:-1;
        }
        
            Tabellenspeicher Tab = new Tabellenspeicher(g,"select kennung"+g.AU_Bezeichnung("Benutzer")+g.AU_Bezeichnung1("Mandant","Benutzer")+" Mandant"+(i<0 ? ","+sSp:"")
              +",aic_benutzer aic from Benutzer where "+(i<0 ? sKen+" is not null":i2>0 ? g.bits("bits",l)+"="+l2+" and geloescht is null":g.bit("bits",l)),true);
            //g.fixtestError("SQL-Befehl für "+Sort.gets(Nod.getLabel(),3)+": "+Tab.getSQL());
            Tab.showGrid("Benutzer mit bit " + Sort.gets(Nod.getLabel(),3), FomGid);
          }
        });
        FomGid.setVisible(true);
}

private void ShowBG_Bits()
{
	Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr","Kennung","Bezeichnung","Anzahl"});
	addArt(Tab2,"AIC_Stamm","Stamm",-2);
	addArt(Tab2,"AIC_Begriff","Startformular",-1);
	addBit(Tab2, "cstHistory","History", Global.cstHistory, 0);
	addBit(Tab2, "cstAbfrage","Abfrage", Global.cstAbfrage, 1);
	addBit(Tab2, "cstReadOnly","ReadOnly", Global.cstReadOnly, 2);
	addBit(Tab2, "cstNurFilter","nur Filter", Global.cstNurFilter, 3);
	addBit(Tab2, "cstGeloeschte","geloeschte", Global.cstGeloeschte, 4);
	addBit(Tab2, "cstWorkflow","Workflow", Global.cstWorkflow, 5);
	addBit(Tab2, "cstWorkflowZR","WorkflowZR", Global.cstWorkflowZR, 6);
	addBit(Tab2, "cstNurWeb","nurWeb", Global.cstNurWeb, 7);
	addBit(Tab2, "cstNurAll","nurAll", Global.cstNurAll, 8);
	addBit(Tab2, "cstBG_pers","pers", Global.cstBG_pers, 9);
	addBit(Tab2, "cstJeder","Jeder", Global.cstJeder, 28);
//	Tab2.showGrid("Benutzergruppe-Bits",thisJFrame());
	JDialog FomGid = new JDialog((Frame)thisFrame, "Benutzergruppe-Bits", false);
    AUOutliner Grid = new AUOutliner();
    FomGid.getContentPane().add("Center", Grid);
    Tab2.showGrid(Grid);
    FomGid.setSize(400, 600);
    Static.centerComponent(FomGid,thisFrame);
    Grid.addActionListener(new JCActionListener() {
      public void actionPerformed(JCActionEvent ev) {
        JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
        int i=Sort.geti(Nod.getLabel(),0);
        long l=(long)Math.pow(2,i);
        String sSp=null;
        String sKen=null;
        if (i<0)
        {
        	sKen=Sort.gets(Nod.getLabel(),1);
        	sSp=sKen;
        	if (sSp.equals("AIC_Begriff"))
      		  sSp="(select kennung from Begriff where "+sSp+"=benutzergruppe."+sSp+") "+ Sort.gets(Nod.getLabel(),2);
      	  else if (sSp.equals("AIC_Stamm"))
      		  sSp="(select bezeichnung from Stammview2 where aic_Rolle is null and aic_stamm=benutzergruppe.aic_stamm) "+ Sort.gets(Nod.getLabel(),2);
        }
        Tabellenspeicher Tab = new Tabellenspeicher(g,"select kennung"+g.AU_Bezeichnung("Benutzergruppe")+g.AU_Bezeichnung1("Mandant","Benutzergruppe")+" Mandant"+(i<0 ? ","+sSp:"")
        		+",aic_benutzergruppe aic from Benutzergruppe where "+(i<0 ? sKen+" is not null":g.bit("bits",l)),true);
            //if(Tab.FrmGrid != null)
            //  Tab.FrmGrid.dispose();
            Tab.showGrid("Benutzergruppe mit bit " + Sort.gets(Nod.getLabel(),2), FomGid);
          }
        });
        FomGid.setVisible(true);
}

private void addArt(Tabellenspeicher Tab2,String sConst,String sBez,int i)
{
	  Tab2.addInhalt("Nr",i);
	  Tab2.addInhalt("Kennung",sConst);
	  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getShow(sBez));
	  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from benutzergruppe where "+sConst+" is not null"));
}

private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
{
  Tab2.addInhalt("Nr",i);
  Tab2.addInhalt("Kennung",sConst);
  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from benutzergruppe where "+g.bit("bits",l)));
}

private void addBArt(Tabellenspeicher Tab2,String sConst,String sBez,int i)
{
	  Tab2.addInhalt("Nr",i);
	  Tab2.addInhalt("Sub",0);
	  Tab2.addInhalt("Kennung",sConst);
	  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getShow(sBez));
	  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from benutzer where "+sConst+" is not null"));
}

private void addBBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
{
  Tab2.addInhalt("Nr",i);
  Tab2.addInhalt("Sub",0);
  Tab2.addInhalt("Kennung",sConst);
  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from benutzer where "+g.bit("bits",l)));
}

private void addBSub(Tabellenspeicher Tab2,String sConst,String sBez,long l,long l2,int i,int iSub)
{
  Tab2.addInhalt("Nr",i);
  Tab2.addInhalt("Sub",iSub);
  Tab2.addInhalt("Kennung",sConst);
  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Radiobutton",sBez));
  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from benutzer where "+g.bits("bits",l)+"="+l2));
}

private UserManager(Global glob)
{
	super("User Manager",null,glob);
        g=glob;
        Self = this;
	g.winInfo("UserManager.create");
	if (!g.UserManager())
	{
		//Self = null;
		//g.fixtestInfo("Self=null bei Usermanager");
		new Message(Message.WARNING_MESSAGE,null,g).showDialog("keineBerechtigung");
		thisFrame.dispose();
		//free();
		return;
	}
	//showToMuch();

        AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
            JCOutlinerNode NodB=OutBenutzer.getSelectedNode();
            int iUser=0;
            String sUser=null;
            String sKenn=null;
            if (NodB!=null && NodB.getLevel()==1)
            {
              iUser=Sort.geti(OutBenutzer.getSelectedNode().getUserData());
              sUser=Sort.gets(OutBenutzer.getSelectedNode().getLabel(),0);
              sKenn=Sort.gets(OutBenutzer.getSelectedNode().getLabel(),1);
            }
            g.progInfo("UserManager.Command=" + s);
            if (s.equals("Abschluss"))
            	showFom(Abschluss.get(g));
//            else if (s.equals("Aufgabe"))
//            	showFom(DefAufgabe.get(g,0));
            else if (s.equals("AdminPassword"))
            	showFom(AdminPassword.start(g));
            else if (s.equals("CleanComputer"))
            	showFom(CleanComputer.get(g));
            else if (s.equals("Verboten"))
            	showFom(Verboten.get(g));
            else if (s.equals("Logging"))
            	showFom(Logging.get(g));
            else if (s.equals("Init"))
              Init();
            else if (s.equals("Refresh"))
              Refresh(VecBenutzer.size()==0);
            else if (s.equals("RefreshAbf"))
              fillAbfragen();
            else if (s.equals("showBG"))
              showBG();
            else if (s.equals("showAbf"))
              showAbf();
            else if (s.equals("Zeitraum"))
            	showFom(Zeitraum.get(g));
            else if (s.equals("Test"))
            {
              JCOutlinerNode NodeAbfrage = OutAbfrage.getSelectedNode();
              if (NodeAbfrage != null && NodeAbfrage.getLevel() > 0)
              {
                int iAbf = ((Integer)((Vector)NodeAbfrage.getUserData()).elementAt(0)).intValue();
                if (iAbf > 0)
                  new ShowAbfrage(g, iAbf, Abfrage.cstAbfrage).showResult(thisFrame);
              }
            }
            else if (s.equals("Abfrage"))
            {
              JCOutlinerNode NodeAbfrage = OutAbfrage.getSelectedNode();
              int iAbf=0;
              int iSttAbf=g.iSttANR;
              if (NodeAbfrage != null && NodeAbfrage.getLevel()>0)
              {
                iAbf = ((Integer)((Vector)NodeAbfrage.getUserData()).elementAt(0)).intValue();
                iSttAbf = ((Integer)((Vector)NodeAbfrage.getUserData()).elementAt(2)).intValue();
              }
              DefAbfrage.get(g,new ShowAbfrage(g,iAbf,Abfrage.cstHS_Filter),iSttAbf,null,true,g.Def() ? -1:0).show(false);
            }
            else if (s.equals("UGHinzufuegen"))
              Hinzufuegen_Benutzergruppe();
            else if (s.equals("UGErsetzen"))
              Ersetzen_Benutzergruppe();
            else if (s.equals("AbfHinzufuegen"))
              Hinzufuegen_Abfrage();
            else if (s.equals("StHinzufuegen") || s.equals("StEntfernen"))
              Aendere_Stamm(s.equals("StHinzufuegen"));
            else if (s.equals("UGEntfernen") || s.equals("AbfEntfernen"))
              Entfernen_Benutzergruppe();
            else if (s.equals("NewUser"))
              UserNew();
            else if (s.equals("EditUser"))
              UserEdit(false);
            else if (s.equals("CopyUser"))
              UserEdit(true);
            else if (s.equals("DeleteUser"))
              Loeschen_Benutzer(false);
            else if (s.equals("DeleteUser2000"))
              Loeschen_Benutzer(true);
            else if (s.equals("UndeleteUser"))
              Wiederherstellen_Benutzer();
            else if (s.equals("DelLTOKEN"))
              g.sendWebDB("clearLToken", null, thisJFrame());
            else if (s.equals("InfoUser") && iUser>0)
              new Tabellenspeicher(g,"select aic_logging aic,ein,version,(select hostname from computer where aic_computer=logging.aic_computer) hostname from logging where aic_benutzer="+
                                   iUser+" order by ein desc",true).showGrid(sUser,thisFrame);
            else if (s.equals("Parameter") && iUser>0)
              new Tabellenspeicher(g,"select h.kennung Hauptgruppe,n.kennung Nebengruppe,(select kennung from computer where aic_computer=p.aic_computer) Computer,p.Parameterzeile Parameter,"+g.int1_int4()+
                                   ",(select ein from logging where aic_logging=p.aic_logging) Datum"+
                                   " from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe"+
                                   " where aic_benutzer=" + iUser,true).showGrid(g.getShow("Parameter")+" "+g.getShow("fuer")+" "+sUser,thisFrame);
            else if (s.equals("Fensterpos") && iUser>0)
            	new Tabellenspeicher(g,"select aic_fensterpos aic,(select defbezeichnung from begriff where aic_begriff=f.aic_begriff) Formular"+
            			g.AU_Bezeichnung1("Stammtyp","f")+" Stammtyp,l.aus changed"+
            			",x,y,w,h,zoom from fensterpos f join logging l on f.aic_logging=l.aic_logging and aic_benutzer="+iUser,true).showGrid(g.getShow("Fensterpos")+" "+g.getShow("fuer")+" "+sUser,thisFrame);
            else if (s.startsWith("DelFensterpos") && iUser>0)
            {
              String[] sDel = new String[] {g.getShow(s.substring(3))+" "+g.getShow("fuer")+" "+g.getShow("Benutzer")+" "+sUser};
              if(new Message(Message.YES_NO_OPTION,thisJFrame(),g).showDialog("Loeschen",sDel)==Message.YES_OPTION)
              {
            	  String sDate=null;
            	  if (s.equals("DelFensterposOld"))
            	  {
	            	  DateWOD dt=new DateWOD();
	            	  dt.prevYear();
	            	  sDate=g.DateTimeToString(dt.toTimestamp());
	            	  g.fixtestError("lösche Fensterpos vor"+sDate);
            	  }
            	  g.diskInfo(g.exec("delete from Fensterpos where aic_logging in (select aic_logging from logging where aic_benutzer=" + iUser+(sDate==null ?"":" and aus<"+sDate)+")")+" "+s.substring(3)+" von Benutzer "+sUser+" gelöscht");
              }
            }
            else if (s.equals("DelParameter") && iUser>0)
            {
              String[] sDel = new String[] {g.getShow("Parameter")+" "+g.getShow("fuer")+" "+g.getShow("Benutzer")+" "+sUser};
              if(new Message(Message.YES_NO_OPTION,thisJFrame(),g).showDialog("Loeschen",sDel)==Message.YES_OPTION)
                g.diskInfo(g.exec("delete from parameter where aic_benutzer=" + iUser)+" Parameter von Benutzer "+sUser+" gelöscht");
            }
            else if (s.equals("DeleteUserGroup"))
              Loeschen_Benutzergruppe();
            else if (s.equals("DB_User"))
              DB_User_Config();
            else if (s.equals("LDAP"))
              LDAP_Config();
            else if (s.equals("LDAP_Del"))
              LDAP_Del();
            else if (s.equals("LDAP_Ok"))
              LDAP_ok();
            else if (s.equals("LDAP_Abbruch"))
              DlgLDAP.setVisible(false);
            else if (s.equals("Google"))
              Google_Config();
//            else if (s.equals("Google_Test"))
//            	  Google_Test();
            else if (s.equals("Google_Test2"))
          	  Google_Test2();
            else if (s.equals("Google_Del"))
        	  Google_Del();
            else if (s.equals("Google_Ok"))
        	  Google_ok();
            else if (s.equals("Google_Abbruch"))
              DlgGoogle.setVisible(false);
            else if (s.equals("Azure"))
              Azure_Config();
            else if (s.equals("Azure_Del"))
              Azure_Del();
            else if (s.equals("Azure_Ok"))
              Azure_ok();
            else if (s.equals("Azure_Abbruch"))
              DlgAzure.setVisible(false);
            else if (s.equals("Grants"))
            	new Tabellenspeicher(g,"show grants for '" +  getDbUser(g,sKenn) + "'@'"+getHost(iUser)+"'",true).showGrid("Grants "+sKenn);
            else if (s.equals("Users"))
            	new Tabellenspeicher(g,"select User,Host,grant_priv,process_priv from mysql.user where user like '%*%'",true).showGrid("Users");
            else if (s.equals("SoftTerm"))
            	createSoftTerm();
            else if (s.equals("showBerechtigung"))
            	showBerechtigung(iUser,sUser,false);
            else if (s.equals("showBerechtigungWeb"))
            	showBerechtigung(iUser,sUser,true);
            else if (s.equals("showAbfragen"))
            	showAbfragen(iUser,sUser);
            else if (s.equals("showAbfragen2"))
            	showAbfragen2(iUser,sUser);
            else if (s.equals("showAbfragenJ"))
            	showAbfragenJ();
            else if (s.equals("showLizenz"))
            	showLizenz(iUser,sUser);
            else if (s.equals("showSperren"))
            	showSperren(iUser,sUser);
            else if (s.equals("showBerechtigung2"))
            	showBerechtigung(0,null,false);
            else if (s.equals("DB_User_Del"))
            {
              if(DB_User_Del(g,TxtUser.getText(),TxtHost.getText()))
            	    new Message(Message.INFORMATION_MESSAGE,(JFrame)thisFrame,g).showDialog("geloescht",new String[] {TxtUser.getText()});
            }
            else if (s.equals("DB_User_Ok"))
              DB_User_ok();
            else if (s.equals("DB_User_Abbruch"))
              DlgDB_User.setVisible(false);
            else if (s.equals("User_Check"))
              UserCheck();
            else if (s.equals("Export"))
            {
                Tabellenspeicher Tab=new Tabellenspeicher(g,(g.Def() ? "select aic_benutzer,"+g.AU_BezeichnungF("Benutzer")+" Benutzer,Kennung,Passwort,Bits,aic_stamm"+
                		",(select bezeichnung from stammview2 where aic_stamm=benutzer.aic_stamm and aic_rolle is null) MA"+
              		  	" from benutzer where geloescht is null":"select aic_stamm,kennung from benutzer where aic_stamm is not null")+" and aic_mandant="+g.getMandant(),true);
                Tab.showGrid("Benutzer",thisFrame);            
            }
            else if (s.equals("Import"))
            	ImportiereUser();
            else if (s.equals("RefreshDB"))
            	g.sendWebDB("refreshDB",null,thisJFrame());
            else if (s.equals("Benutzergruppenbits"))
                ShowBG_Bits();
            else if (s.equals("Benutzerbits"))
                ShowBenutzer_Bits();
            else if (s.equals("History"))
            	showHistory(g,0,null,thisFrame,OutBenutzer);
            else if (s.equals("Sprache_Del"))
            	Del_Sprache();
            else if (s.equals("CL_Ok"))
            	Del_Sprache_Ok();
            else if (s.equals("CL_Abbruch"))
            	DlgCL.setVisible(false);
            else if (s.equals("ParaSMTP"))
            	setParaSMTP(g,thisJFrame());
//            else if (s.equals("copy"))
//            	OutBenutzer.copy();
//            else if (s.equals("paste"))
//            	OutBenutzer.paste();
            else if (iUser>0)
              Static.printError("UserManager: "+s+" wird nicht unterstützt!");
          }
        };
        Build();
        g.BtnAdd(getButton("RefreshDB"),"RefreshDB",AL);
        g.BtnAdd(getFormularButton("Abschluss"),"Abschluss",AL);
//        g.BtnAdd(getFormularButton("Aufgabe"),"Aufgabe",AL);
        g.BtnAdd(getFormularButton("AdminPassword"),"AdminPassword",AL);
        JButton BtnComputer=g.BtnAdd(getFormularButton("CleanComputer"),"CleanComputer",AL);
        if (BtnComputer!=null && !g.Def())
        	BtnComputer.setVisible(false);
        g.BtnAdd(getFormularButton("Verboten"),"Verboten",AL);
        JButton BtnLogging=g.BtnAdd(getFormularButton("Logging"),"Logging",AL);
        if (BtnLogging!=null && !g.Def())
        	BtnLogging.setVisible(false);
        g.BtnAdd(getButton("Init"),"Init",AL);
        g.BtnAdd(getButton("User_Check"),"User_Check",AL);
        if (g.Def())
        	g.BtnAdd(getButton("Sprache_Del"),"Sprache_Del",AL);
        g.BtnAdd(getButton("ParaSMTP"),"ParaSMTP",AL);
        BtnBerechtigung.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                  JCOutlinerNode[] NodeBG = OutBenutzergruppe.getSelectedNodes();
                  Vector<Object> Vec=null;
                  if (NodeBG[0].getLevel()>0)
                  {
                    Vec=new Vector<Object>();
                    for (int i = 0; i < NodeBG.length; i++)
                      Vec.addElement(((Vector)NodeBG[i].getUserData()).elementAt(0));
                  }
                  Berechtigung BER=Berechtigung.get(g);
                  if (!BER.bFP)
    					Static.centerComponent2(BER.thisFrame, thisFrame);
                  BER.show(Vec);                
                }
        });

        BtnPeriodensperre.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                  JCOutlinerNode[] NodeBG = OutBenutzergruppe.getSelectedNodes();
                  Vector<Object> Vec=null;
                  if (NodeBG[0].getLevel()>0)
                  {
                    Vec=new Vector<Object>();
                    for (int i = 0; i < NodeBG.length; i++)
                      Vec.addElement(((Vector)NodeBG[i].getUserData()).elementAt(0));
                  }
                  Periodensperre PER=Periodensperre.get(g);
                  if (!PER.bFP)
  					Static.centerComponent2(PER.thisFrame, thisFrame);
                  PER.show(Vec);
                }
        });

        final JPopupMenu popupBG=new JPopupMenu();
        g.addMenuItem("show",popupBG,"showBG",AL);
        g.addMenuItem("showBerechtigung",popupBG,"showBerechtigung2",AL);
        if (g.Def())
        	g.addMenuItem("Benutzergruppenbits",popupBG,"Benutzergruppenbits",AL);
        OutBenutzergruppe.getOutliner().addMouseListener(new MouseListener()
            {
              public void mousePressed(MouseEvent ev)
              {}

              public void mouseClicked(MouseEvent ev)
              {
                //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                {
                  popupBG.show(OutBenutzergruppe, ev.getX(), ev.getY()+16);
                }
              }

              public void mouseEntered(MouseEvent ev) {}
              public void mouseExited(MouseEvent ev) {}
              public void mouseReleased(MouseEvent ev)  {}
            });


        g.BtnAdd(getButton("Test"),"Test",AL);
        g.BtnAdd(getFormularButton("Abfrage"),"Abfrage",AL);
        g.BtnAdd(getButton("Refresh2"),"RefreshAbf",AL);
//        g.BtnAdd(getButton("show2"),"showAbf",AL);
        final JPopupMenu popupB=new JPopupMenu();
          g.addMenuItem("New User",popupB,"NewUser",AL);
          g.addMenuItem("Edit User",popupB,"EditUser",AL);
          g.addMenuItem("Delete User",popupB,"DeleteUser",AL);
          if (g.Def())
          {
            g.addMenuItem("DeleteUser2000",popupB,"DeleteUser2000",AL);
        	  g.addMenuItem("Undelete",popupB,"UndeleteUser",AL);
          }
          popupB.addSeparator();
          g.addMenuItem("DelLTOKEN",popupB,"DelLTOKEN",AL);
        if (g.Def())
        {
          g.addMenuItem("History",popupB,"History",AL);
          g.addMenuItem("Benutzerbits",popupB,"Benutzerbits",AL);
          g.addMenuItem("showLizenz",popupB,"showLizenz",AL);
          g.addMenuItem("showBerechtigung",popupB,"showBerechtigung",AL);
          g.addMenuItem("showBerechtigungWeb",popupB,"showBerechtigungWeb",AL);
          g.addMenuItem("showSperren",popupB,"showSperren",AL);
          g.addMenuItem("showAbfragen",popupB,"showAbfragen",AL);
          g.addMenuItem("showAbfragen2",popupB,"showAbfragen2",AL);
          g.addMenuItem("showAbfragenJ",popupB,"showAbfragenJ",AL);
          popupB.addSeparator();
          if (g.MySQL())
          {
        	  g.addMenuItem("Users",popupB,"Users",AL);
        	  g.addMenuItem("Grants",popupB,"Grants",AL);      	  
          }
          g.addMenuItem("create SoftTerm",popupB,"SoftTerm",AL);
          g.addMenuItem("Info",popupB,"InfoUser",AL);
          g.addMenuItem("Parameter", popupB,null,AL);
          g.addMenuItem("DelParameter", popupB,null,AL);
          g.addMenuItem("Fensterpos", popupB,null,AL);
          g.addMenuItem("DelFensterposOld", popupB,null,AL);
        }
        g.addMenuItem("DelFensterpos", popupB,null,AL);
        if (g.Def())
        {
//          popupB.addSeparator();
//          g.addMenuItem("copy",popupB,null,AL);
//          g.addMenuItem("paste",popupB,null,AL);
          popupB.addSeparator();
          JMenu MnuTab=new JMenu("Tabellenspeicher");
          popupB.add(MnuTab);
          addPopupForTab(MnuTab,"TabAll");
          addPopupForTab(MnuTab,"TabAbfrage");
          addPopupForTab(MnuTab,"TabErsatz");
          addPopupForTab(MnuTab,"TabNachricht");
        }

        final JPopupMenu popupAbf=new JPopupMenu();
        g.addMenuItem("Abfrage",popupAbf,null,AL);
        g.addMenuItem("Refresh",popupAbf,"RefreshAbf",AL);
        g.addMenuItem("Test",popupAbf,null,AL);
        g.addMenuItem("show",popupAbf,"showAbf",AL);
        OutAbfrage.getOutliner().addMouseListener(new MouseListener()
            {
              public void mousePressed(MouseEvent ev)
              {}

              public void mouseClicked(MouseEvent ev)
              {
                //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                {
                  popupAbf.show(OutAbfrage, ev.getX(), ev.getY()+16);
                }
              }

              public void mouseEntered(MouseEvent ev) {}
              public void mouseExited(MouseEvent ev) {}
              public void mouseReleased(MouseEvent ev)  {}
            });


        BtnRefresh = g.BtnAdd(getButton("Refresh"),"Refresh",AL);
        g.BtnAdd(getButton("Zeitraum"),"Zeitraum",AL);
//        g.BtnAdd(getButton("show"),"showBG",AL);

        if (BtnSpeichern != null)
        {
          ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnSpeichern);
          BtnSpeichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              Save();
              Refresh(true);
            }
          });
        }

        if (BtnBeenden != null)
        {
          Action cancelKeyAction = new AbstractAction()
          {
            /**
			 *
			 */
			private static final long serialVersionUID = -5518496638844891016L;

			public void actionPerformed(ActionEvent e) {
              checkSave();
              hide();
            }
          };
          BtnBeenden.addActionListener(cancelKeyAction);
          Static.Escape(BtnBeenden,thisFrame,cancelKeyAction);
        }

	BtnUserOk.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//g.progInfo("PW1="+TxtNeuPasswort.getPassword()+", "+TxtBestPasswort.getPassword()+"/"+Static.className(TxtNeuPasswort.getPassword()));
			String sPW=TxtNeuPasswort.getValue();// new String(TxtNeuPasswort.getPassword());
                        int iAIC_Benutzer = bNewUser ? 0:((Integer)OutBenutzer.getSelectedNode().getUserData()).intValue();
                        String sKen=TxtBKennung.getText().trim();
                        TxtBKennung.setText2(sKen);
                        String sFehler=TxtBKennung.Fehler(true);
                        String sWarning=null;
                        //g.fixtestError("Kennung="+sKen+"->"+sFehler);
                        if (sFehler != null)
                        	sWarning="Kennung"+sFehler;
                        else if((bNewUser || TxtBKennung.Modified()) && SQL.exists(g,"select aic_Benutzer from  Benutzer WHERE aic_Benutzer<> "+iAIC_Benutzer+" and Kennung='"+sKen+"'")) // darf erst nach Fehlerprüfung aufgerufen werden
                            sWarning="KennungVorhanden";                
                        else if ((bNewUser || TxtBKennung.Modified()) && (sKen.equals("") || sKen.indexOf(' ')>=0 || sKen.length()<Static.iBenML))
                          sWarning="KennungLeer"+(sKen.equals("")?"":sKen.length()<Static.iBenML ? "_zu_kurz":"zeichen");
                        else if ((RadPW_EMAIL.isSelected() || RadPW_KOMBI.isSelected()) && TxtEMail.isNull())
                          sWarning="EMailFehlt";
                        // else if (RadPW_KEIN.isSelected())
                        // { 
                        //   if (!RadNormal.isSelected())
                        //     sWarning="KeinPW";
                        // }
                        else if(bNewUser && !RadPW_LDAP.isSelected() && (CbxDB.isSelected() || !CbxImport.isSelected() && !CbxTimer.isSelected() && !CbxTerminal.isSelected() && !CbxAServer.isSelected()))
                          sWarning=Passwort.PW_Fehler(g,sPW,new String(TxtBestPasswort.getPassword()),null);
                        g.fixtestInfo(sWarning+"/"+RadNormal.isSelected());
            if (sWarning != null && !((RadNormal.isSelected() || RadUserManager.isSelected()) && (sWarning.equals("Passwort_Default"))))
            	Passwort.PW_Message(g,FrmNewUser,sWarning);
              //new Message(Message.WARNING_MESSAGE, FrmNewUser, g,10).showDialog(sWarning);
			else
			{
                String sPWx=null;
				SQL Qry = new SQL(g);
				int iBits=0;
				if(bNewUser)
				{
					//Qry.open("SELECT COUNT(*) Anzahl FROM Benutzer WHERE AIC_Mandant="+g.getMandant()+" AND Kennung='"+TxtBKennung.getText()+"'");
					//if(Qry.getI("Anzahl")==0)
//					{
						Qry.add("Kennung",TxtBKennung.getText());
						int iPWArt=RadPW_MD5.isSelected() ? Transact.PWH:Transact.PWVH;
//                        sPWx=g.PasswordConvert(sPW,iPWArt,0);
                        if (hasHost())
                        	Qry.add("Host",TxtBHost.getText());
//						Qry.add("Passwort",sPWx);
						Qry.addnow("PW_Date");
						Qry.add("Tel",TxtTel.getText());
						Qry.add("E_Mail",TxtEMail.getText());
						Qry.add("AIC_Mandant",g.getMandant());
						if(CboSprache.getSelectedAIC()!=0)
							Qry.add("AIC_Sprache",CboSprache.getSelectedAIC());
						if(CboLand.getSelectedAIC()!=0)
							Qry.add("AIC_Land",CboLand.getSelectedAIC());
						//if(CboStamm.getSelectedAIC()!=0)
						//	Qry.add("AIC_Stamm",CboStamm.getSelectedAIC());
                                                if(CboStamm.getStamm()!=0)
                                                  Qry.add("AIC_Stamm",CboStamm.getStamm());
                                                if(CboHBG.getSelectedAIC()!=0)
							Qry.add("AIC_Benutzergruppe",CboHBG.getSelectedAIC());
                                                 iBits=(RadProg.isSelected() ? Global.cstProg : RadDef.isSelected() ? Global.cstDef : RadVerw.isSelected() ? Global.cstVerw : 
                                                	 RadSuper.isSelected() ? Global.cstSuperUser : RadUserManager.isSelected() ? Global.cstUserManager:Global.cstNormalUser)+
                                                    (CbxSpezial.isSelected() ? Global.cstSpezial:0)+(CbxMehrmals.isSelected() ? Global.cstMehrmals:0)+//(CbxSoftTerm.isSelected() ? Global.cstSoftTerm:0)+
                                                    (CbxTimer.isSelected() ? Global.cstTimerBenutzer:0)+(CbxImport.isSelected() ? Global.cstImport:0)+
                                                    (CbxAServer.isSelected() ? Global.cstAServerUser:0)+(CbxTerminal.isSelected() ? Global.cstTerminalUser:0)+(CbxAutoFC.isSelected() ? Global.cstAutoFC:0)+(CbxOC.isSelected() ? Global.cstOC:0)+
                                                    (CbxHS.isSelected() ? Global.cstHS:0)+(CbxOhne.isSelected() ? Global.cstOhne:0)+(CbxDB.isSelected() ? Global.cstDB:0)+(CbxVorlage.isSelected() ? Global.cstVorlage:0)+(CbxAPI.isSelected() ? Global.cstAPI:0)+
                                                    (CbxTest.isSelected() ? Global.cstTest:0)+(CbxNoMobile.isSelected() ? Global.cstNoMobile:0)+(CbxNoAktiv.isSelected() ? Global.cstNoAktiv:0)+
                                                    (RadPW_GOOGLE.isSelected() ? Global.cstPW_GOOGLE:RadPW_AZURE.isSelected() ? Global.cstPW_AZURE:RadPW_MD5.isSelected() ? Global.cstPW_MD5:RadPW_MD5B.isSelected() ? Global.cstPW_MD5B:
                                                    RadPW_LDAP.isSelected() ? Global.cstPW_LDAP:RadPW_LTOKEN.isSelected() ? Global.cstPW_LTOKEN:RadPW_EMAIL.isSelected() ? Global.cstPW_EMAIL:RadPW_KOMBI.isSelected() ? Global.cstPW_KOMBI:0)+(Cbx2FA.isSelected() ? Global.cst2FA:0);
						Qry.add("Bits",iBits);
                                                Qry.add("aic_logging",g.getLog());
                                                Qry.addnow("seit");
                                                DateWOD DW=null;
                                                if (!EdtBis.isNull())
                                                {
                                                	DW=new DateWOD(EdtBis.getDate());
                                                	DW.setTimeZero();
                                                	DW.tomorrow();
                                                	Qry.add("use_bis",DW.getTime());
                                                }
                                                iAIC_Benutzer=Qry.insert("Benutzer",true);
                                                g.SaveDefVerlauf(getBegriff(),"B"+iAIC_Benutzer+": Neuer Benutzer "+TxtBKennung.getText());
                                                if (RadPW_GOOGLE.isSelected() || RadPW_MD5B.isSelected() || RadPW_LTOKEN.isSelected() || RadPW_EMAIL.isSelected() || RadPW_KOMBI.isSelected())
                                                {
                                                  sPWx=g.PasswordConvert(sPW, iPWArt, iAIC_Benutzer);
                                                  Qry.add("Passwort",sPWx);
                                                  Qry.update("Benutzer",iAIC_Benutzer);
                                                }
                                                if (TxtBBezeichnung.isNull() && CboStamm.getStamm()>0)
                                                  TxtBBezeichnung.setText(CboStamm.getBezeichnung());
						g.setBezeichnung("",TxtBBezeichnung.getText(),g.TabTabellenname.getAic("BENUTZER"),iAIC_Benutzer,1);

						Vector<Comparable> VecVisible = new Vector<Comparable>();
						VecVisible.addElement(TxtBBezeichnung.getText());
						VecVisible.addElement(TxtBKennung.getText());
						VecVisible.addElement(DW==null ? null:new Zeit(DW.getTime(),"dd.MM.yyyy HH:mm"));
                        VecVisible.addElement(Security.getBerechtigung(g,iBits));
                        VecVisible.addElement(Static.JaNein2((iBits&Global.cstSpezial)>0));
                        VecVisible.addElement(Static.JaNein2((iBits&Global.cstHS)>0));
                        VecVisible.addElement(Static.JaNein2((iBits&Global.cstVorlage)>0));
						VecVisible.addElement(CboStamm.getBezeichnung());
                        VecVisible.addElement(null);
                        VecVisible.addElement(Static.Int0);
                        VecVisible.addElement(CboLand.getSelectedBezeichnung());
//                        VecVisible.addElement(Static.JaNein2((iBits&Global.cstDB)>0));
                        VecVisible.addElement(iAIC_Benutzer);
                        VecVisible.addElement(new Zeit(new Date(),"dd.MM.yyyy"));
                        VecVisible.addElement(TxtTel.getText());
                        VecVisible.addElement(TxtEMail.getText());
                        VecVisible.addElement(getBArt(iBits)); 
                        VecVisible.addElement(getPWArt(iBits));
                        VecVisible.addElement(CboSprache.getSelectedBezeichnung());
                        VecVisible.addElement(CboHBG.getSelectedBezeichnung());
                        // VecVisible.addElement((iBits&Global.cstTimerBenutzer)>0 ? "Timer":(iBits&Global.cstTerminalUser)>0 ? "Terminal":(iBits&Global.cstAServerUser)>0 ? "AServer":(iBits&Global.cstImport)>0 ? "Import":"");
                        JCOutlinerFolderNode NodeParent = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutBenutzer.getRootNode());
						NodeParent.setUserData(new Integer(iAIC_Benutzer));
						NodeParent.setStyle(StyleBenutzer);
						if (iCopyUser>0)
						{
							Tabellenspeicher Tab=new Tabellenspeicher(g,"select z.* from benutzer_zuordnung z where aic_benutzer="+iCopyUser,true);
							for(Tab.moveFirst();!Tab.out();Tab.moveNext())
							{
								Qry.add("aic_benutzer", iAIC_Benutzer);
								int iBG=Tab.getI("aic_benutzergruppe");
								Qry.add("aic_benutzergruppe", iBG);
								Qry.add0("aic_abfrage", Tab.getI("aic_abfrage"));
								Qry.add("Nr", Tab.getI("Nr"));
								Qry.add0("aic_stamm", Tab.getI("aic_stamm"));
								Qry.add0("sta_aic_stamm", Tab.getI("sta_aic_stamm"));
								Qry.add("bits", Tab.getI("bits"));
								Qry.insert("benutzer_zuordnung", false);
							}						
							iCopyUser=0;
							Static.makeVisible(OutBenutzer, NodeParent);
//							OutBenutzer.folderChanged(OutBenutzer.getRootNode());
							Refresh(true);
						}
						else
							Static.makeVisible(OutBenutzer, NodeParent);
//						OutBenutzer.folderChanged(NodeParent);
//					}
					//else
					//	new Message(Message.ERROR_MESSAGE,(JFrame)thisFrame,g).showDialog("Kennung");
				}
				else
				{
					JCOutlinerFolderNode NodeParent = (JCOutlinerFolderNode)OutBenutzer.getSelectedNode();
                                        iAIC_Benutzer = ((Integer)OutBenutzer.getSelectedNode().getUserData()).intValue();
                                        if (TxtBKennung.Modified())
                                          Qry.add("Kennung",TxtBKennung.getText());
					//Qry.add("AIC_Mandant",g.getMandant());
                    if (hasHost())
                    	Qry.add("Host",TxtBHost.getText());
					Qry.add("Tel",TxtTel.getText());
					Qry.add("E_Mail",TxtEMail.getText());
					Qry.add0("AIC_Sprache",CboSprache.getSelectedAIC());
					Qry.add0("AIC_Land",CboLand.getSelectedAIC());
					Qry.add0("AIC_Stamm",CboStamm.getStamm());
					Qry.add0("AIC_Benutzergruppe",CboHBG.getSelectedAIC());
                                        sPWx=SQL.getString(g,"select passwort from benutzer where aic_benutzer="+iAIC_Benutzer);
                                        iBits=SQL.getInteger(g,"select Bits from benutzer where aic_benutzer=?",-1,""+iAIC_Benutzer);
//                                        if (((iBits&Global.cstPW)==Global.cstPW_MD5B) != RadPW_MD5B.isSelected()) // RadPW_MD5.Modified()
//                                          if (RadPW_MD5B.isSelected())
//                                          {
//                                            sPWx=g.PasswordConvert(g.getPassword(sPWx,g.PWR,0),g.PWVH,iAIC_Benutzer);
//                                            Qry.add("Passwort",sPWx);
//                                          }
//                                          else
//                                          {
//                                            Static.printError("Rückumstieg nicht möglich");
//                                            RadPW_MD5B.setSelected(true);
//                                          }

                                        iBits=(RadProg.isSelected() ? Global.cstProg : RadDef.isSelected() ? Global.cstDef : RadVerw.isSelected() ? Global.cstVerw : 
                                       	 RadSuper.isSelected() ? Global.cstSuperUser : RadUserManager.isSelected() ? Global.cstUserManager:Global.cstNormalUser)+                                       	
                                                    (CbxSpezial.isSelected() ? Global.cstSpezial:0)+(CbxMehrmals.isSelected() ? Global.cstMehrmals:0)+//(CbxSoftTerm.isSelected() ? Global.cstSoftTerm:0)+
                                                    (CbxTimer.isSelected() ? Global.cstTimerBenutzer:0)+(CbxImport.isSelected() ? Global.cstImport:0)+
                                                    (CbxAServer.isSelected() ? Global.cstAServerUser:0)+(CbxTerminal.isSelected() ? Global.cstTerminalUser:0)+(CbxAutoFC.isSelected() ? Global.cstAutoFC:0)+(CbxOC.isSelected() ? Global.cstOC:0)+
                                                    (CbxHS.isSelected() ? Global.cstHS:0)+(CbxOhne.isSelected() ? Global.cstOhne:0)+(CbxDB.isSelected() ? Global.cstDB:0)+(CbxVorlage.isSelected() ? Global.cstVorlage:0)+(CbxAPI.isSelected() ? Global.cstAPI:0)+
                                                    (CbxTest.isSelected() ? Global.cstTest:0)+(CbxNoMobile.isSelected() ? Global.cstNoMobile:0)+(CbxNoAktiv.isSelected() ? Global.cstNoAktiv:0)+
                                                    (RadPW_GOOGLE.isSelected() ? Global.cstPW_GOOGLE:RadPW_AZURE.isSelected() ? Global.cstPW_AZURE:RadPW_MD5.isSelected() ? Global.cstPW_MD5:RadPW_MD5B.isSelected() ? Global.cstPW_MD5B:
                                                    RadPW_LDAP.isSelected() ? Global.cstPW_LDAP:RadPW_LTOKEN.isSelected() ? Global.cstPW_LTOKEN:RadPW_EMAIL.isSelected() ? Global.cstPW_EMAIL:RadPW_KOMBI.isSelected() ? Global.cstPW_KOMBI:0)+(Cbx2FA.isSelected() ? Global.cst2FA:0);
					Qry.add("Bits",iBits);
                    Qry.add("aic_logging",g.getLog());
                    DateWOD DW=null;
                    if (EdtBis.isNull())
                    	Qry.add0("use_bis", null);
                    else
                    {
                    	DW=new DateWOD(EdtBis.getDate());
                    	DW.setTimeZero();
                    	DW.tomorrow();
                    	Qry.add("use_bis",DW.getTime());
                    }
                    Qry.update("Benutzer",iAIC_Benutzer);
                    g.SaveDefVerlauf(getBegriff(),"B"+iAIC_Benutzer+": geändert:"+(TxtBKennung.Modified() ? " Kennung":"")+(CboSprache.Modified() ? " Sprache":"")+(CboLand.Modified() ? " Land":"")+(TxtBBezeichnung.Modified() ? " Bez":"")+
                    		(CbxSpezial.Modified() ? " Spezial":"")+(CbxHS.Modified() ? " HS":"")+(CbxVorlage.Modified() ? " Vorlage":"")+(CbxAPI.Modified() ? " API":"")+
                    		(CbxTest.Modified() ? " Test":"")+(CbxNoMobile.Modified() ? " noMobile":"")+(CbxNoAktiv.Modified() ? " noAktiv":"")+(Cbx2FA.Modified() ? " 2FA":"")+(EdtBis.Modified() ? " Bis":""));
					if (TxtBBezeichnung.isNull() && CboStamm.getStamm()>0)
                                          TxtBBezeichnung.setText(CboStamm.getBezeichnung());
                                        if (TxtBBezeichnung.Modified())
                                          g.setBezeichnung("",TxtBBezeichnung.getText(),g.TabTabellenname.getAic("BENUTZER"),iAIC_Benutzer,1);

					Vector<Comparable> VecVisible = new Vector<Comparable>();
					VecVisible.addElement(TxtBBezeichnung.getText());
					VecVisible.addElement(TxtBKennung.getText());
					VecVisible.addElement(DW==null ? null:new Zeit(DW.getTime(),"dd.MM.yyyy HH:mm"));
                    VecVisible.addElement(Security.getBerechtigung(g,iBits));
                    VecVisible.addElement(Static.JaNein2((iBits&Global.cstSpezial)>0));
                    VecVisible.addElement(Static.JaNein2((iBits&Global.cstHS)>0));
                    VecVisible.addElement(Static.JaNein2((iBits&Global.cstVorlage)>0));
                    VecVisible.addElement(CboStamm.getBezeichnung());
                    VecVisible.addElement(null);
                    VecVisible.addElement(SQL.getInteger(g,"select count(*) from logging where aic_benutzer=?",-1,""+iAIC_Benutzer));
                    VecVisible.addElement(CboLand.getSelectedBezeichnung());
//                    VecVisible.addElement(Static.JaNein2((iBits&Global.cstDB)>0));
                    VecVisible.addElement(iAIC_Benutzer);
                    VecVisible.addElement((Comparable)((Vector)NodeParent.getLabel()).elementAt(12));
                    VecVisible.addElement(TxtTel.getText());
                    VecVisible.addElement(TxtEMail.getText());
                    VecVisible.addElement(getBArt(iBits)); 
                    VecVisible.addElement(getPWArt(iBits));   
                    VecVisible.addElement(CboSprache.getSelectedBezeichnung());    
                    VecVisible.addElement(CboHBG.getSelectedBezeichnung());        
                    // VecVisible.addElement((iBits&Global.cstTimerBenutzer)>0 ? "Timer":(iBits&Global.cstTerminalUser)>0 ? "Terminal":(iBits&Global.cstAServerUser)>0 ? "AServer":(iBits&Global.cstImport)>0 ? "Import":"");
                    NodeParent.setLabel(VecVisible);
				}
				Qry.free();
				boolean bOk=true;
				if (CbxDB.Modified())
				{
				  bOk=CbxDB.isSelected() ? setUserOnDB(g,TxtBKennung.getText(),TxtBHost.getText(),sPWx,getArt(iBits)):DB_User_Del(g,TxtBKennung.getText(),null);//getHost(g));
				  int iBits2=CbxDB.isSelected() ? iBits-Global.cstDB:iBits+Global.cstDB;
				  if (!bOk)
					  g.exec("update benutzer set Bits="+iBits2+" where aic_benutzer="+iAIC_Benutzer);						  
				}
				OutBenutzer.folderChanged(OutBenutzer.getRootNode());
				if (bOk)
				  FrmNewUser.setVisible(false);
			}
		}
	});

	BtnUserAbbruch.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			bNewUser=false;
			iCopyUser=0;
			FrmNewUser.setVisible(false);
		}
	});

	BtnNewUserGroup.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			bNewUserGroup=true;
			TxtBGBezeichnung.setText("");
			TxtBGKennung.setText("");
			CbxHistory.setSelected(false);
			CbxAbfrage.setSelected(false);
			CbxReadOnly.setSelected(false);
                        CbxNurFilter.setSelected(false);
                        CbxGeloeschte.setSelected(false);
                        CbxWorkflow.setSelected(false);
                        CbxWorkflowZR.setSelected(false);
                        CbxNurAll.setSelected(false);
                        CbxNurWeb.setSelected(false);
                        CbxJeder.setSelected(false);
                        //CbxJeder.setEnabled(true);
			CboStartFormular.setSelectedAIC(0);
			//CboAbschluss.setSelectedAIC(0);
                        TxtBGBezeichnung.requestFocus();
            showUsergroup();
			//FrmNewUserGroup.setVisible(true);
		}
	});

	BtnCopyUserGroup.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			boolean bSave = new Message(Message.YES_NO_OPTION,null,g).showDialog("Kopieren")==Message.YES_OPTION;
			if(bSave)
			{
				//long lTime=Static.get_ms();
				SQL Qry=new SQL(g);
				SQL Qry2=new SQL(g);
				JCOutlinerNode[] NodeBG = OutBenutzergruppe.getSelectedNodes();
                                int iAIC_Benutzergruppe_New=0;
				for(int i=0;i<NodeBG.length;i++)
				{
					Vector VecVisible=(Vector)NodeBG[i].getLabel();
					Vector VecInvisible=(Vector)NodeBG[i].getUserData();
					int iAIC_Benutzergruppe=((Integer)VecInvisible.elementAt(0)).intValue();
					int iAIC_Mandant=((Integer)VecInvisible.elementAt(1)).intValue();
					String sKennung=(String)VecVisible.elementAt(1);
					if(iAIC_Mandant==g.getMandant())
                                        {
                                          if (sKennung.length()>39)
                                                  sKennung=sKennung.substring(0,39);
						Tabellenspeicher Tab = new Tabellenspeicher(g,"select upper(kennung) kennung from benutzergruppe where kennung like '"+sKennung+"%' and aic_mandant="+g.getMandant(),true);
						int iCopy=1;
                                                //Tab.showGrid();
                                                for(;Tab.posInhalt("kennung",(""+sKennung+iCopy).toUpperCase());iCopy++);
						sKennung+=""+iCopy;
					}
					Qry.add("Kennung",sKennung);
					Qry.add("AIC_Mandant",g.getMandant());
					int iBits=((Integer)VecInvisible.elementAt(2)).intValue();
					//Qry.add("History",(iBits&cstHistory)>0);
					//Qry.add("Abfrage",(iBits&cstAbfrage)>0);
					//Qry.add("ReadOnly",(iBits&cstReadOnly)>0);
                                        Qry.add("Bits",iBits);
					//if(VecInvisible.elementAt(4)!=null)
					Qry.add0("AIC_Begriff",(Integer)VecInvisible.elementAt(4));
					//if(VecInvisible.elementAt(5)!=null)
					//	Qry.add("AIC_STAMM",(Integer)VecInvisible.elementAt(5));
                                        Qry.add("aic_logging",g.getLog());
                                        iAIC_Benutzergruppe_New=Qry.insert("Benutzergruppe",true);
                                        g.SaveDefVerlauf(getBegriff(),"Benutzergruppe "+sKennung+" kopiert");
					g.setBezeichnung("",g.getBegriffS("Show","Copy")+" "+VecVisible.elementAt(0),g.TabTabellenname.getAic("BENUTZERGRUPPE"),iAIC_Benutzergruppe_New,1);

					if(Qry.open("select aic_tabellenname,aic_fremd,bits from berechtigung where aic_benutzergruppe="+iAIC_Benutzergruppe))
					{
						for(;!Qry.eof();Qry.moveNext())
						{
							Qry2.add("aic_benutzergruppe",iAIC_Benutzergruppe_New);
							Qry2.add("aic_tabellenname",Qry.getI("aic_tabellenname"));
							Qry2.add("aic_fremd",Qry.getI("aic_fremd"));
							Qry2.add("bits",Qry.getI("bits"));
							Qry2.insert("Berechtigung",false);
						}
						Qry.close();
					}
                    if(Qry.open("select aic_abschlusstyp from abschluss_Zuordnung where aic_benutzergruppe="+iAIC_Benutzergruppe))
                    {
                      for(;!Qry.eof();Qry.moveNext())
                      {
                        Qry2.add("aic_benutzergruppe",iAIC_Benutzergruppe_New);
                        Qry2.add("aic_abschlusstyp",Qry.getI("aic_abschlusstyp"));
                        Qry2.insert("abschluss_Zuordnung",false);
                      }
                      Qry.close();
                    }
				}
				Qry.free();
				Qry2.free();

				fillOutliners(true);
                                if (iAIC_Benutzergruppe_New>0 && TabAll.posInhalt("AIC", iAIC_Benutzergruppe_New))
                                  Static.makeVisible(null, ((JCOutlinerNode)TabAll.getInhalt("Node")));
			}
		}
	});

	BtnEditUserGroup.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			bNewUserGroup=false;
			JCOutlinerNode[] Node = OutBenutzergruppe.getSelectedNodes();
			Vector VecVisible = (Vector)Node[0].getLabel();
			Vector VecInvisible = (Vector)Node[0].getUserData();
			TxtBGBezeichnung.setText((String)VecVisible.elementAt(0));
			TxtBGKennung.setText((String)VecVisible.elementAt(1));
			int iBits=((Integer)VecInvisible.elementAt(2)).intValue();
			CbxHistory.setSelected((iBits&Global.cstHistory)>0);
			CbxAbfrage.setSelected((iBits&Global.cstAbfrage)>0);
			CbxReadOnly.setSelected((iBits&Global.cstReadOnly)>0);
                        CbxNurFilter.setSelected((iBits&Global.cstNurFilter)>0);
                        CbxGeloeschte.setSelected((iBits&Global.cstGeloeschte)>0);
                        CbxWorkflow.setSelected((iBits&Global.cstWorkflow)>0);
                        CbxWorkflowZR.setSelected((iBits&Global.cstWorkflowZR)>0);
                        CbxWorkflowZR.setText((CbxWorkflow.isSelected() ? Static.sKein+" ":"")+sWFZR);
                        CbxNurWeb.setSelected((iBits&Global.cstNurWeb)>0);
                        CbxNurAll.setSelected((iBits&Global.cstNurAll)>0);
                        CbxPers.setSelected((iBits&Global.cstBG_pers)>0);
                        CbxJeder.setSelected((iBits&Global.cstJeder)>0);
                        //CbxJeder.setEnabled(false);
			Integer Int=(Integer)VecInvisible.elementAt(4);
			CboStartFormular.setSelectedAIC(Int==null?0:Int.intValue());
			//Int=(Integer)VecInvisible.elementAt(5);
			//CboAbschluss.setSelectedAIC(Int==null?0:Int.intValue());
			showUsergroup();
			//FrmNewUserGroup.setVisible(true);
		}
	});

	BtnUserGroupOk.addActionListener(new ActionListener()
	{
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e)
		{
			SQL Qry = new SQL(g);
                        int iBG_Bits=(CbxHistory.isSelected() ? Global.cstHistory:0)+(CbxAbfrage.isSelected() ? Global.cstAbfrage:0)+
                                        (CbxReadOnly.isSelected() ? Global.cstReadOnly:0)+(CbxNurFilter.isSelected() ? Global.cstNurFilter:0)+
                                        (CbxGeloeschte.isSelected() ? Global.cstGeloeschte:0)+(CbxJeder.isSelected() ? Global.cstJeder:0)+
                                        (CbxWorkflow.isSelected() ? Global.cstWorkflow:0)+(CbxWorkflowZR.isSelected() ? Global.cstWorkflowZR:0)+
                                        (CbxNurWeb.isSelected() ? Global.cstNurWeb:0)+(CbxNurAll.isSelected() ? Global.cstNurAll:0)+(CbxPers.isSelected() ? Global.cstBG_pers:0);
			if(bNewUserGroup)
			{
				Qry.add("Kennung",TxtBGKennung.getText());

                                Qry.add("Bits",iBG_Bits);
				//Qry.add("History",CbxHistory.isSelected());
				//Qry.add("Abfrage",CbxAbfrage.isSelected());
				//Qry.add("ReadOnly",CbxReadOnly.isSelected());
				if (CboStartFormular.getSelectedAIC()>0)
					Qry.add("AIC_BEGRIFF",CboStartFormular.getSelectedAIC());
				//if (CboAbschluss.getSelectedAIC()>0)
				//	Qry.add("AIC_STAMM",CboAbschluss.getSelectedAIC());
				Qry.add("AIC_Mandant",g.getMandant());
                                Qry.add("aic_logging",g.getLog());
				int iAIC_Benutzergruppe=Qry.insert("Benutzergruppe",true);
				g.SaveDefVerlauf(getBegriff(),"Benutzergruppe "+TxtBGKennung.getText()+" angelegt");
				//int iPosT=g.TabTabellenname.getPos("Kennung","BENUTZERGRUPPE");
				g.setBezeichnung("",TxtBGBezeichnung.getText(),g.TabTabellenname.getAic("BENUTZERGRUPPE"),iAIC_Benutzergruppe,1);

				Vector VecVisible = new Vector();
				Vector VecInvisible = new Vector();
				VecVisible.addElement(TxtBGBezeichnung.getText());
				VecVisible.addElement(TxtBGKennung.getText());
				VecVisible.addElement(g.getMandant(0,"Bezeichnung"));
				VecVisible.addElement(Static.JaNein2(CbxHistory.isSelected()));
				VecVisible.addElement(Static.JaNein2(CbxAbfrage.isSelected()));
				VecVisible.addElement(Static.JaNein2(CbxNurAll.isSelected()));
				VecVisible.addElement(Static.JaNein2(CbxNurWeb.isSelected()));
				VecVisible.addElement(Static.JaNein2(CbxReadOnly.isSelected()));
				VecVisible.addElement(Static.JaNein2(CbxPers.isSelected()));
				VecInvisible.addElement(new Integer(iAIC_Benutzergruppe));
				VecInvisible.addElement(new Integer(g.getMandant()));
				VecInvisible.addElement(new Integer(iBG_Bits));
				VecInvisible.addElement(null);
				//VecInvisible.addElement(new Boolean(CbxHistory.isSelected()));
				//VecInvisible.addElement(new Boolean(CbxAbfrage.isSelected()));
				VecInvisible.addElement(new Integer(CboStartFormular.getSelectedAIC()));
				//VecInvisible.addElement(new Integer(CboAbschluss.getSelectedAIC()));
				JCOutlinerNode Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutBenutzergruppe.getRootNode());
				Node.setUserData(VecInvisible);
				Node.setStyle((iBG_Bits&Global.cstJeder)>0 ? g.setColor(StyleBenutzergruppe,g.ColEF_Bez2):StyleBenutzergruppe);

				VecVisible = new Vector();
				VecVisible.addElement(TxtBGBezeichnung.getText());
				VecVisible.addElement(TxtBGKennung.getText());
				//JCOutlinerFolderNode Node2 = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutBenutzergruppe2.getRootNode());
				//Node2.setUserData(new Integer(iAIC_Benutzergruppe));
				//Node2.setStyle(StyleBenutzergruppe);

				//Node2 = new JCOutlinerFolderNode((Object)new Vector(VecVisible),(JCOutlinerFolderNode)OutBenutzergruppeMA.getRootNode());
                                //Vector VecInvisible2 = new Vector();
                                //VecInvisible2.addElement(new Integer(iAIC_Benutzergruppe));
                                //VecInvisible2.addElement(Boolean.FALSE);
                                //Node2.setUserData(VecInvisible2);
				//Node2.setStyle(StyleBenutzergruppe);
                                Static.makeVisible(OutBenutzergruppe,Node);
			}
			else
			{
				JCOutlinerNode[] Node = OutBenutzergruppe.getSelectedNodes();
				Vector<String> VecVisible = (Vector)Node[0].getLabel();
				Vector VecInvisible = (Vector)Node[0].getUserData();
				int iAIC_Benutzergruppe = ((Integer)VecInvisible.elementAt(0)).intValue();

				Qry.add("Kennung",TxtBGKennung.getText());
                                Qry.add("Bits",iBG_Bits);
				//Qry.add("History",CbxHistory.isSelected());
				//Qry.add("Abfrage",CbxAbfrage.isSelected());
				//Qry.add("ReadOnly",CbxReadOnly.isSelected());
				Qry.add0("AIC_BEGRIFF",CboStartFormular.getSelectedAIC());
				//Qry.add0("AIC_STAMM",CboAbschluss.getSelectedAIC());
				Qry.add("AIC_Mandant",g.getMandant());
                                Qry.add("aic_logging",g.getLog());
				Qry.update("Benutzergruppe",iAIC_Benutzergruppe);
				g.SaveDefVerlauf(getBegriff(),"Benutzergruppe "+TxtBGKennung.getText()+" geändert");
				//g.TabTabellenname.posInhalt("Kennung","BENUTZERGRUPPE");
				g.setBezeichnung(VecVisible.elementAt(0),TxtBGBezeichnung.getText(),g.TabTabellenname.getAic("BENUTZERGRUPPE"),iAIC_Benutzergruppe,1);

				VecVisible.setElementAt(TxtBGBezeichnung.getText(),0);
				VecVisible.setElementAt(TxtBGKennung.getText(),1);
				VecVisible.setElementAt(Static.JaNein2(CbxHistory.isSelected()),3);
				VecVisible.setElementAt(Static.JaNein2(CbxAbfrage.isSelected()),4);
				VecVisible.setElementAt(Static.JaNein2(CbxNurAll.isSelected()),5);
				VecVisible.setElementAt(Static.JaNein2(CbxNurWeb.isSelected()),6);
				VecVisible.setElementAt(Static.JaNein2(CbxReadOnly.isSelected()),7);
				VecVisible.setElementAt(Static.JaNein2(CbxPers.isSelected()),8);
				VecInvisible.setElementAt(new Integer(iBG_Bits),2);
				//VecInvisible.addElement(null,3);

				//VecInvisible.setElementAt(new Boolean(CbxHistory.isSelected()),2);
				//VecInvisible.setElementAt(new Boolean(CbxAbfrage.isSelected()),3);
				VecInvisible.setElementAt(new Integer(CboStartFormular.getSelectedAIC()),4);
                                Node[0].setStyle((iBG_Bits&Global.cstJeder)>0 ? g.setColor(StyleBenutzergruppe,g.ColEF_Bez2):StyleBenutzergruppe);
				//VecInvisible.setElementAt(new Integer(CboAbschluss.getSelectedAIC()),5);
				/*Vector VecChildren = ((JCOutlinerFolderNode)OutBenutzergruppe2.getRootNode()).getChildren();
				for(int j=0;j<VecChildren.size();j++)
				{
					JCOutlinerFolderNode Node2 = (JCOutlinerFolderNode)VecChildren.elementAt(j);
					if(((Integer)Node2.getUserData()).intValue()==iAIC_Benutzergruppe)
					{
						VecVisible = (Vector)Node2.getLabel();
						VecVisible.setElementAt(TxtBGBezeichnung.getText(),0);
						VecVisible.setElementAt(TxtBGKennung.getText(),1);
					}
				}*/

				/*VecChildren = ((JCOutlinerFolderNode)OutBenutzergruppe3.getRootNode()).getChildren();
				for(int j=0;j<VecChildren.size();j++)
				{
					JCOutlinerFolderNode Node2 = (JCOutlinerFolderNode)VecChildren.elementAt(j);
					if(((Integer)Node2.getUserData()).intValue()==iAIC_Benutzergruppe)
					{
						VecVisible = (Vector)Node2.getLabel();
						VecVisible.setElementAt(TxtBGBezeichnung.getText(),0);
						VecVisible.setElementAt(TxtBGKennung.getText(),1);
					}
				}*/

				/*for(TabBenutzer.posInhalt("AIC_BG",iAIC_Benutzergruppe);!TabBenutzer.out();TabBenutzer.posNextInhalt("AIC_BG",iAIC_Benutzergruppe))
				{
					JCOutlinerNode Node2 = (JCOutlinerNode)TabBenutzer.getInhalt("Node");
					VecVisible = (Vector)Node2.getLabel();
					VecVisible.setElementAt(TxtBGBezeichnung.getText(),0);
					VecVisible.setElementAt(TxtBGKennung.getText(),1);
				}*/

				//OutBenutzer.folderChanged(OutBenutzer.getRootNode());
                                OutBenutzergruppe.folderChanged(OutBenutzergruppe.getRootNode());

				//OutBenutzergruppe2.folderChanged(OutBenutzergruppe2.getRootNode());
				//OutBenutzergruppeMA.folderChanged(OutBenutzergruppeMA.getRootNode());
			}


			FrmNewUserGroup.setVisible(false);
		}
	});

	BtnUserGroupAbbruch.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			FrmNewUserGroup.setVisible(false);
		}
	});

	/*CbxAendern.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent e)
		{
			Aendern_Benutzergruppe2();
		}
	});

	CbxAnlegen.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent e)
		{
			Aendern_Benutzergruppe2();
		}
	});

	CbxLoeschen.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent e)
		{
			Aendern_Benutzergruppe2();
		}
	});*/

	OutBenutzergruppe.addItemListener(new JCOutlinerListener()
	{
		public void itemStateChanged(JCItemEvent e)
		{
		}

		public void outlinerFolderStateChangeBegin(JCOutlinerEvent e)
		{
		}

		public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)
		{
		}

		public void outlinerNodeSelectBegin(JCOutlinerEvent e)
		{
		}

		public void outlinerNodeSelectEnd(JCOutlinerEvent e)
		{
			EnableButtons();
		}
	});

	OutBenutzer.addItemListener(new JCOutlinerListener()
	{
		public void itemStateChanged(JCItemEvent e){}
		public void outlinerFolderStateChangeBegin(JCOutlinerEvent e){}
		public void outlinerFolderStateChangeEnd(JCOutlinerEvent e){}
		public void outlinerNodeSelectBegin(JCOutlinerEvent e){}

		public void outlinerNodeSelectEnd(JCOutlinerEvent e)
		{
			EnableButtons();
		}
	});

        OutBenutzer.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
              if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
              {
                JCOutlinerNode Nod=OutBenutzer.getSelectedNode();
                g.fixtestError("UserData="+Nod.getUserData()+", Level="+Nod.getLevel());
                if (Nod!= null && Nod.getLevel()==1)// && g.Def())
                  popupB.show(OutBenutzer, ev.getX(), ev.getY()+16);
                else if (Nod!= null && Nod.getLevel()==2 && TabAll.posInhalt("AIC", Sort.geti((Vector)Nod.getUserData(),0)))
                {
                    Static.makeVisible(null, ((JCOutlinerNode)TabAll.getInhalt("Node")));
                    ((JTabbedPane)OutAbfrage.getParent().getParent().getParent().getParent().getParent()).setSelectedIndex(0);
                }
                else if (Nod!= null && Nod.getLevel()==3 && TabAll.posInhalt("AIC", -Sort.geti(Nod.getUserData())))
                {
                	g.fixtestError("pos Abfrage: Aic="+TabAll.getI("Aic")+", Node="+TabAll.getInhalt("Node"));
                    Static.makeVisible(null, ((JCOutlinerNode)TabAll.getInhalt("Node")));
                    //g.fixtestError("P2="+Static.print(OutAbfrage.getParent().getParent())+", P3="+Static.print(OutAbfrage.getParent().getParent().getParent())+", P4="+Static.print(OutAbfrage.getParent().getParent().getParent().getParent()));
                    ((JTabbedPane)OutAbfrage.getParent().getParent().getParent().getParent().getParent()).setSelectedIndex(1);
                }
              }
          }
          public void mouseEntered(MouseEvent ev) {}
          public void mouseExited(MouseEvent ev)  {}
          public void mouseReleased(MouseEvent ev){}
        });


        OutAbfrage.addItemListener(new JCOutlinerListener()
        {
                public void itemStateChanged(JCItemEvent e){}
                public void outlinerFolderStateChangeBegin(JCOutlinerEvent e){}
                public void outlinerFolderStateChangeEnd(JCOutlinerEvent e){}
                public void outlinerNodeSelectBegin(JCOutlinerEvent e){}

                public void outlinerNodeSelectEnd(JCOutlinerEvent e)
                {
                        EnableButtons();
                }
        });

	show();
        
	EnableButtons();
}

private void Del_Sprache()
{
//	g.fixtestError("Del_Sprache()");
  if (DlgCL==null)
  {
	DlgCL=new JDialog(FrmNewUser, g.getBegriffS("Dialog", "change_Sprache"), true);
	DlgCL.getContentPane().setLayout(new BorderLayout(2, 2));
	JPanel Pnl = new JPanel(new GridLayout(0,1,0,2));
//	 JLabel Lbl1=new JLabel("folgende Sprache löschen:");
//	 JLabel Lbl2=new JLabel("Zuordnungen ändern auf:");
	 CboCL=new ComboSort(g);
	 CboCL.fillDefTable("Sprache",true);
	 CboCL.setSelectedAIC(SQL.getInteger(g, "select max(aic_sprache) from sprache"));
//	 Pnl.add(Lbl1);
	 g.addLabel4(Pnl, "delete_language");
//	 JPanel Pnl2 = new JPanel(new GridLayout(1,2,0,2));
	 Pnl.add(CboCL);
//	 Pnl.add(Lbl2);
	 g.addLabel4(Pnl, "change_to");
	 JLabel LblSp=new JLabel(CboCL.getBezeichnung(g.getSprache()));
	 LblSp.setFont(g.fontStandard);
	 Pnl.add(LblSp);
//	 Pnl.add(Pnl2);
	DlgCL.getContentPane().add("Center", Pnl);
	Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
     JButton BtnOk = g.getButton("Ok","CL_Ok",AL);
     JButton BtnAbbruch = g.getButton("Abbruch","CL_Abbruch",AL);
     DlgCL.getRootPane().setDefaultButton(BtnOk);
     Pnl.add(BtnOk);
     Pnl.add(BtnAbbruch);
    DlgCL.getContentPane().add("South", Pnl);
    DlgCL.pack();
  }
  Static.centerComponent(DlgCL,thisFrame);
  DlgCL.setVisible(true);
}

private void Del_Sprache_Ok()
{
	int iSp=CboCL.getSelectedAIC();
	if (iSp<3)
		return;
//	g.fixtestError("ändere Sprache von "+iSp+" auf "+g.getSprache());
	String sSp=CboCL.getSelectedBezeichnung();
	Reinigung.execInfo("update benutzer set aic_sprache="+g.getSprache()+" where aic_sprache="+iSp,"Benutzer-"+sSp,g,"",false," neu zugeordnet");
	Reinigung.execInfo("delete from bezeichnung where aic_sprache="+iSp,"Bezeichnung-"+sSp,g,"",false," gelöscht");
	Reinigung.execInfo("delete from tooltip where aic_sprache="+iSp,"Tooltip-"+sSp,g,"",false," gelöscht");
	Reinigung.execInfo("delete from daten_memo where aic_sprache="+iSp,"Memo-"+sSp,g,"",false," gelöscht");
	Reinigung.execInfo("delete from sprache where aic_sprache="+iSp,"Sprache "+sSp,g,"",false," gelöscht");
	CboCL.fillDefTable("Sprache",true);
	CboCL.setSelectedAIC(SQL.getInteger(g, "select max(aic_sprache) from sprache"));
	DlgCL.setVisible(false);
}

private void showBerechtigung(int iUser,String sUser,boolean bWeb)
{
	int iBits=0;
  String sTitel=iUser==0 ? "":bWeb ? "Web-":"All-";
	if (iUser>0)
		iBits=SQL.getInteger(g, "select bits from benutzer where aic_benutzer="+iUser)&Transact.cstBenutzerEbene;
	if (iUser>0 && iBits!=Global.cstNormalUser && iBits!=Global.cstUserManager)
		new Message(Message.INFORMATION_MESSAGE,thisJFrame(),g).showDialog("volleBerechtigung");
	else
	{
		Vector<Integer> VecBG;
		if (iUser==0)
		{
			JCOutlinerNode NodeBG = OutBenutzergruppe.getSelectedNode();
			sUser=Sort.gets((Vector)NodeBG.getLabel(),0);
			VecBG=Static.AicToVec(Sort.geti((Vector)NodeBG.getUserData(),0));
		}
		else
			VecBG=SQL.getVector("select z.aic_benutzergruppe from benutzergruppe b join benutzer_zuordnung z on b.aic_benutzergruppe=z.aic_benutzergruppe where aic_benutzer="+iUser+" and"+g.bitis("b.bits",bWeb ? Global.cstNurAll:Global.cstNurWeb,0), g);
//		Tabellenspeicher TabModulBerechtigt=new Tabellenspeicher(g,"select distinct modul.aic_tabellenname,modul.aic_fremd from berechtigung join modul on modul.aic_begriff=berechtigung.aic_fremd and berechtigung.aic_tabellenname="+Global.iTabBegriff+
//                " where aic_benutzergruppe"+Static.SQL_in(VecBG)+" order by modul.aic_tabellenname",true);
		if (VecBG.size()==0)
			new Message(Message.INFORMATION_MESSAGE,thisJFrame(),g).showDialog("keineBerechtigung3");
		else
		{
			Vector<Integer> VecModuleA=new Vector<Integer>();
			Vector<Integer> VecA=new Vector<Integer>();
			Vector<Integer> VecP=new Vector<Integer>();
			Vector<Integer> VecU=new Vector<Integer>();
			for(int i=0;i<VecBG.size();i++)
            {
				int iBG=Sort.geti(VecBG,i);
				Vector<Integer> Vec=new Vector<Integer>();
				Vector<Integer> VecModule=SQL.getVector("select b.aic_begriff from tabellenname t"+g.join("berechtigung","t","tabellenname")+",begriff b where aic_benutzergruppe="+iBG+
						" and t.kennung='Begriff' and b.aic_begriff=aic_fremd and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"),g);
				SQL.addVector(Vec,"select distinct beg_aic_begriff from begriff_zuordnung where aic_begriff"+Static.SQL_in(VecModule),g);
				SQL.addVector(Vec,"select distinct b.aic_fremd aic from berechtigung b join tabellenname on tabellenname.aic_tabellenname=b.aic_tabellenname where tabellenname.kennung='Begriff' and aic_benutzergruppe="+iBG/*+" and not"+g.bit("b.bits",Berechtigung.NICHT)*/,g);	
				Static.addVectorI(VecA, Vec);
				boolean bPers=(SQL.getInteger(g, "select bits from benutzergruppe where aic_benutzergruppe="+iBG)&Global.cstBG_pers)>0;
//				g.fixtestError("BG "+iBG+":"+bPers);
				if (bPers)
					Static.addVectorI(VecP, Vec);
				else
					Static.addVectorI(VecU, Vec);
            }
			
//			g.fixtestError("VecModule für "+VecBG+"="+VecModule);
			
			// Berechtigte Begriffe
	        
	        Tabellenspeicher TabM=null;
	        if (VecModuleA.size()>0)	          
	          TabM=new Tabellenspeicher(g,"select distinct beg_aic_begriff,aic_begriff from begriff_zuordnung where aic_begriff"+Static.SQL_in(VecModuleA),true);
	       
	        Vector<Integer> VecNicht=SQL.getVector("select distinct b.aic_fremd aic from berechtigung b join tabellenname on tabellenname.aic_tabellenname=b.aic_tabellenname where tabellenname.kennung='Begriff' and "+g.bit("b.bits",Berechtigung.NICHT)+" and aic_benutzergruppe"+Static.SQL_in(VecBG),g);            	        
	        if (VecNicht.size()>0)
	        	VecA.removeAll(VecNicht);
	        g.fixtestError("Berechtigte Begriffe:"+VecA);
	        boolean bKeine=false;
	        if (VecNicht.size()>0)
	        {
	        	Tabellenspeicher Tab=new Tabellenspeicher(g,"select "+g.AU_BezeichnungK("Begriffgruppe","begriff")+" Gruppe,defbezeichnung,kennung,aic_begriff,web,'x' nicht,null Pers,null Allg"+(TabM==null ?"":",null Modul")+" from begriff where aic_begriff"+Static.SQL_in(VecNicht),true);
	        	showBegBer(Tab,VecA,sUser,TabM,VecP,VecU,sTitel);
//	        	new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("Begriffgruppe","begriff")+" Gruppe,defbezeichnung,kennung,aic_begriff,web from begriff where aic_begriff"+Static.SQL_in(Vec),true).addTo(Tab, true);
//	        	Tab.showGrid("Begriffe-Berechtigung von "+sUser,thisJFrame());
	        }
	        else if (VecA.size()>0)
	        	showBegBer(null,VecA,sUser,TabM,VecP,VecU,sTitel);
	        else
	        	bKeine=true;
	        
	        // Berechtigte Eigenschaften
	        Vector<Integer> VecEigNicht=SQL.getVector("select distinct b.aic_fremd aic from berechtigung b join tabellenname on tabellenname.aic_tabellenname=b.aic_tabellenname where tabellenname.kennung='Eigenschaft' and "+g.bit("b.bits",Berechtigung.NICHT)+" and aic_benutzergruppe"+Static.SQL_in(VecBG),g);
	        Vector<Integer> Vec=new Vector<Integer>();
	        int iTabEig=g.TabTabellenname.getAic("EIGENSCHAFT");
	        SQL.addVector(Vec,"select distinct aic_fremd from berechtigung where aic_tabellenname="+iTabEig+" and aic_benutzergruppe"+Static.SQL_in(VecBG)/*+" and not"+g.bit("berechtigung.bits",Berechtigung.NICHT)*/,g);
	        Tabellenspeicher TabModulBerechtigt=new Tabellenspeicher(g,"select distinct modul.aic_tabellenname,modul.aic_fremd from berechtigung join modul on modul.aic_begriff=berechtigung.aic_fremd and berechtigung.aic_tabellenname="+g.iTabBegriff+
                    " where aic_benutzergruppe"+Static.SQL_in(VecBG)+" order by modul.aic_tabellenname",true);
	        TabModulBerechtigt.sGruppe="aic_tabellenname";
	        TabModulBerechtigt.sAIC="aic_fremd";
	        if (VecEigNicht.size()>0)
	        	Vec.removeAll(VecEigNicht);
	        Static.addVectorI(Vec,TabModulBerechtigt.getVec("aic_tabellenname",iTabEig,"aic_fremd"));
	        if (VecEigNicht.size()>0)
	        {
	        	Tabellenspeicher Tab=new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("eigenschaft")+" bezeichnung,kennung,aic_eigenschaft,'x' nicht from eigenschaft where aic_eigenschaft"+Static.SQL_in(VecEigNicht),true);
	        	new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("eigenschaft")+" bezeichnung,kennung,aic_eigenschaft from eigenschaft where aic_eigenschaft"+Static.SQL_in(Vec),true).addTo(Tab, true);
	        	Tab.showGrid("Eigenschaft-"+sTitel+"Berechtigung von "+sUser,thisJFrame());
	        			
	        }
	        else if (Vec.size()>0)
	        	new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("eigenschaft")+" bezeichnung,kennung,aic_eigenschaft from eigenschaft where aic_eigenschaft"+Static.SQL_in(Vec),true).showGrid("Eigenschaft-"+sTitel+"Berechtigung von "+sUser,thisJFrame());
	        else if (bKeine)
	        	new Message(Message.INFORMATION_MESSAGE,thisJFrame(),g).showDialog("keineBerechtigung4");
		}
	}
}

private void showBegBer(Tabellenspeicher Tab,Vector<Integer> Vec,String sUser,Tabellenspeicher TabM,Vector<Integer> VecP,Vector<Integer> VecU,String sTitel)
{
	Tabellenspeicher Tabx=new Tabellenspeicher(g,"select "+g.AU_BezeichnungK("Begriffgruppe","begriff")+" Gruppe,defbezeichnung,kennung,aic_begriff,web,null Pers,null Allg"+(Tab==null ?"":",null nicht")+(TabM==null ?"":",null Modul")+" from begriff where aic_begriff"+Static.SQL_in(Vec),true);
	if (TabM != null)
	  for (int i=0;i<TabM.size();i++)
	  {
//		  TabM.showGrid("TabM");
		  if (Tabx.posInhalt("aic_begriff",TabM.getI(i,"beg_aic_begriff")))
		  {
			  String s=Tabx.getS("Modul");
			  s+=(Static.Leer(s)?"":",")+g.getBegBez(TabM.getI(i,"aic_begriff"));
			  Tabx.setInhalt("Modul", s);
		  }
	  }
	if (Tab==null)
		Tab=Tabx;
	else
		Tabx.addTo(Tab, true);
	for (Tab.moveFirst();!Tab.out();Tab.moveNext())
	{
		int iBegriff=Tab.getI("AIC_Begriff");
		Tab.setInhalt("web", Static.JaNein2(Tab.getB("web")));
		Tab.setInhalt("pers", Static.JaNein2(VecP.contains(iBegriff)));
		Tab.setInhalt("Allg", Static.JaNein2(VecU.contains(iBegriff)));
	}
	Tab.showGrid("Begriffe-"+sTitel+"Berechtigung von "+sUser,thisJFrame());
}

private void showAbfragen(int iUser,String sUser)
{
//	g.fixtestError("Zeige alle Abfragen von "+sUser+" ("+iUser+")");
	AbfrageAuswahl("select b.defbezeichnung,b.kennung"+g.AU_Bezeichnung("bewegungstyp", "b")+" Bewegungstyp"+g.AU_Bezeichnung("stammtyp", "b")+
			" Stammtyp,b.aic_begriff from begriff b join abfrage a on b.aic_begriff=a.aic_begriff where a.aic_benutzer="+iUser,"pers. Abfragen von "+sUser);
}

private void showAbfragen2(int iUser,String sUser)
{
//	g.fixtestError("Zeige alle Benutzer-Abfragen von "+sUser+" ("+iUser+")");
	AbfrageAuswahl("select distinct b.defbezeichnung,b.kennung"+g.AU_Bezeichnung("bewegungstyp", "b")+" Bewegungstyp"+g.AU_Bezeichnung("stammtyp", "b")+
			" Stammtyp,b.aic_begriff from spalten2 s2 join abfrage a on s2.aic_abfrage=a.aic_abfrage join begriff b on b.aic_begriff=a.aic_begriff where s2.aic_benutzer="+iUser,"Benutzer-Abfragen von "+sUser);
}

private void showAbfragenJ()
{
//	g.fixtestError("Zeige alle Benutzer-Abfragen von "+sUser+" ("+iUser+")");
	AbfrageAuswahl("select distinct b.defbezeichnung,b.kennung"+g.AU_Bezeichnung("bewegungstyp", "b")+" Bewegungstyp"+g.AU_Bezeichnung("stammtyp", "b")+
			" Stammtyp,b.aic_begriff from spalten2 s2 join abfrage a on s2.aic_abfrage=a.aic_abfrage join begriff b on b.aic_begriff=a.aic_begriff where s2.aic_benutzer is null and s2.aic_benutzergruppe is null","Benutzer-Abfragen für jeden");
}

private void AbfrageAuswahl(String s,String sTitel)
{
	Tabellenspeicher Tab=new Tabellenspeicher(g,s,true);
	Tab.showGrid(sTitel,thisJFrame());
	Tab.Grid.getOutliner().addMouseListener(new MouseListener()
    {
      public void mousePressed(MouseEvent ev) {}
      public void mouseClicked(MouseEvent ev)
      {
        //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
        if (ev.getButton() == 3 || ev.getButton() == 1 && (ev.getModifiersEx() == Global.iRM || ev.getClickCount()==2))
        {
          JCOutlinerNode Nod = Tab.Grid.getSelectedNode();//((JCOutliner)ev.getSource()).getSelectedNode();
          int iAic = Sort.geti(Nod.getLabel(), 4);
          int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iAic);
          DefAbfrage.get(g, new ShowAbfrage(g, iAic, Abfrage.cstBegriff), iStt, null, false, -1).show();
        }
      }
      public void mouseEntered(MouseEvent ev) {}
      public void mouseExited(MouseEvent ev) {}
      public void mouseReleased(MouseEvent ev) {}
    });
}

//TODO Titel aus Lizenz ermitteln
private void showLizenz(int iUser,String sUser)
{
	int iMandant=0;
	if (iUser>0)
		iMandant=SQL.getInteger(g, "select aic_mandant from benutzer where aic_benutzer="+iUser);
	if (iMandant==0)
		new Message(Message.WARNING_MESSAGE,thisJFrame(),g).showDialog("keinMandant");
	else if (iMandant==1)
		new Message(Message.INFORMATION_MESSAGE,thisJFrame(),g).showDialog("volleLizenz");
	else
	{
		//g.fixtestError("Benutzer "+sUser+" Lizenz noch nicht ermittelbar");
		Vector<Integer> Vec = new Vector<Integer>();
		SQL.addVector(Vec,"select aic_fremd from lizenz where aic_tabellenname=" + g.iTabBegriff + " and aic_mandant=" + iMandant,g);
		Tabellenspeicher TabModule=new Tabellenspeicher(g,"select distinct aic_begriff,beg_aic_begriff from begriff_zuordnung where aic_begriff" + Static.SQL_in(Vec),true);
		Static.addVectorI(Vec, TabModule.getVecSpalteI("beg_aic_begriff"));//"select distinct beg_aic_begriff from begriff_zuordnung where aic_begriff" + Static.SQL_in(Vec), g);
		g.fixtestError("Lizenzierte Begriffe:"+Vec);
        if (Vec.size()>0)
        {
        	Tabellenspeicher Tab=new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("Begriffgruppe","begriff")+" Gruppe,defbezeichnung,kennung,aic_begriff,web,null Modul from begriff where aic_begriff"+Static.SQL_in(Vec),true);
        	for (int i=0;i<Tab.size();i++)
        		if (TabModule.posInhalt("beg_aic_begriff", Tab.getI(i,"aic_begriff")))
        			Tab.setInhalt(i, "Modul", g.TabBegriffe.getKennung(TabModule.getI("aic_begriff")));
        	Tab.showGrid("Begriffe-Lizenz von "+sUser);
        }
	}
		
}

private void showSperren(int iUser,String sUser)
{
	g.fixInfo("zeige Periodensperren von "+sUser+" ("+iUser+")");
	Vector<Integer> VecBG;
	if (iUser==0)
	{
		JCOutlinerNode NodeBG = OutBenutzergruppe.getSelectedNode();
		sUser=Sort.gets((Vector)NodeBG.getLabel(),0);
		VecBG=Static.AicToVec(Sort.geti((Vector)NodeBG.getUserData(),0));
	}
	else
		VecBG=SQL.getVector("select aic_benutzergruppe from benutzer_zuordnung where aic_benutzer="+iUser, g);
	
	Tabellenspeicher TabMA=new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("abschlusstyp","t")+" bezeichnung,t.kennung,t.aic_abschlusstyp aic,a.datum,a.weiter,a.ab,a.aic_abfrage abfrage,t.bits,t.prog from abschluss a"+
            " join abschlusstyp t on a.aic_abschlusstyp=t.aic_abschlusstyp and a.pro_aic_protokoll is null"+g.getReadMandanten("a")+
            " left outer join abschluss_Zuordnung z on t.aic_abschlusstyp=z.aic_abschlusstyp where "+g.bit("t.bits",1)+" or z.aic_benutzergruppe"+Static.SQL_in(VecBG),true);
	for (int i=0;i<TabMA.size();i++)
	{
		int iW=TabMA.getI(i,"weiter");
		if (iW<0)
			TabMA.setInhalt(i, "weiter", iW==-2 ? "Monat":iW==-3 ? "Quartal":iW==-4 ? "Jahr":"Woche");
		int iP=TabMA.getI(i,"prog");
		if (iP>0)
			TabMA.setInhalt(i, "prog", g.TabCodes.getKennung(iP));
		int iA=TabMA.getI(i,"abfrage");
		if (iA>0)
		{
			iA=g.AbfToBeg(iA);
			if (iA>0)
				TabMA.setInhalt(i, "abfrage", g.getBegBez(iA));
		}
	}
	TabMA.showGrid("Periodensperren von "+sUser);
}

private int getArt(int iBits)
{
	int iUE=iBits&g.cstBenutzerEbene;
	return (iBits&g.cstAServerUser)>0 || iUE==g.cstProg || iUE==g.cstDef ? FULL:iUE>0 && (iBits&g.cstTimerBenutzer)==0 ? ADMIN:(iBits&g.cstTerminalUser)>0 ? TERM:USER;
}

//private static String getHost(Global g)
//{
////	String sHost=SQL.getString(g, "select host from information_schema.processlist");//"%";
////	int iPos=sHost.indexOf(':');
////	if (iPos>0)
////		sHost=sHost.substring(0, iPos);
////	g.fixtestError("Host="+sHost);
//	String sUH=SQL.getString(g, "select user()");
//	int iAt=sUH.indexOf('@');
//	String sHost=sUH.substring(iAt+1);
//	return sHost;
//}

public static String getDbUser(Global g,String sUser)
{
	String s= (g.getMandant()==1?"All":"K")+(g.MySQL() ? "*":"@")+sUser;
	if (s.length()>16) s=s.substring(0, 16);
	//g.fixtestError("User="+s);
	return s;
}

private void createSoftTerm()
{
	String sDB=g.getDB();
	if (g.MySQL())
	{
		g.bISQL=true;
		String sSTU="'softterm'@'localhost'";
		g.exec("create user "+sSTU+" identified by '#Hard2018'");
		g.bISQL=false;
		g.exec("grant select on "+sDB+".* to "+sSTU);
		g.exec("grant insert on "+sDB+".zwischenspeicher to "+sSTU);
		g.exec("grant insert on "+sDB+".protokoll to "+sSTU);
		g.exec("grant update on "+sDB+".benutzer to "+sSTU);
		new Tabellenspeicher(g,"show grants for "+sSTU,true).showGrid("Grants SoftTerm");
	}
	else
	{
		String sUser="softterm";
		g.bISQL=true;
		g.exec("create LOGIN "+sUser+" WITH PASSWORD=N'#Hard2018', DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[Deutsch], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF");
	    g.exec("CREATE USER ["+sUser+"] FOR LOGIN ["+sUser+"] WITH DEFAULT_SCHEMA=[dbo]");
	    g.bISQL=false;
	    g.exec("grant select on database::"+sDB+" to "+sUser);
	    g.exec("grant insert on object::zwischenspeicher to "+sUser);
	    g.exec("grant insert on object::protokoll to "+sUser);
	    g.exec("grant update on object::benutzer to "+sUser);
	}
	g.SaveDefVerlauf(getBegriff(),"SoftTerm angelegt");
}

private static boolean setUserOnDB(Global g,String sUser,String sHost,String sPWx,int iArt)
{
	boolean bOk=false;
	String sDB=g.getDB();
	//String sMandant=g.getMandant(g.getMandant(),"Kennung");
	sUser=getDbUser(g,sUser);//sMandant+"*"+sUser;
	if (g.MySQL())
    {
		//String sHost=getHost(g);		
		String sUH="'" + sUser + "'@'"+sHost+"'";
		if (iArt==FULL)
		{
			bOk=g.exec("GRANT ALL PRIVILEGES ON " + sDB + ".* TO " + sUH+" IDENTIFIED BY '" + sPWx + "' WITH GRANT OPTION")>-2;
			bOk=bOk && g.exec("GRANT ALL PRIVILEGES ON mysql.user TO " + sUH)>-2;
			bOk=bOk && g.exec("GRANT RELOAD,PROCESS ON *.* TO " + sUH)>-2;
			bOk=bOk && g.exec("grant create user on *.* to "+sUH+" with grant option")>-2;
		}
		else
		{
			bOk=g.exec("create user " + sUH+" identified by '" + sPWx + "'")>-2;
			if (g.AllUnlimited())
				bOk=true;
			bOk=bOk && g.exec("grant select,update,insert,delete on " + sDB + ".* to " + sUH)>-2;
			if (iArt==ADMIN)
			{
			  bOk=bOk && g.exec("GRANT RELOAD,PROCESS ON *.* TO " + sUH/*+" WITH GRANT OPTION"*/)>-2;
			  //bOk=bOk && g.exec("GRANT ALL PRIVILEGES ON mysql.user TO " + sUH)>-2;
			  bOk=bOk && g.exec("grant create user on *.* to "+sUH+" with grant option")>-2;
			  bOk=bOk && g.exec("grant select,update,delete on mysql.user to " + sUH)>-2;
			}
			g.exec("flush privileges;");
		}
    }
    else if (g.MS())
    {
      bOk=g.exec("create LOGIN "+sUser+" WITH PASSWORD=N'"+sPWx+"', DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[Deutsch], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF")>-2;
      //bOk=bOk && g.exec("EXEC sys.sp_addsrvrolemember @loginame = N'"+sUser+"', @rolename = N'processadmin'")>-2;
      bOk=bOk && g.exec("CREATE USER ["+sUser+"] FOR LOGIN ["+sUser+"] WITH DEFAULT_SCHEMA=[dbo]")>-2;
      if (g.AllUnlimited())
			bOk=true;
      if (iArt==FULL)
    	  bOk=bOk && g.exec("EXEC sp_addrolemember N'db_owner', N'"+sUser+"'")>-2;
    	  //bOk=bOk && g.exec("GRANT ALL PRIVILEGES ON DATABASE::"+sDB+" TO "+sUser+" WITH GRANT OPTION")>-2;
      else
    	  bOk=bOk && g.exec("GRANT SELECT, INSERT, UPDATE, DELETE ON DATABASE::"+sDB+" TO "+sUser)>-2;
      bOk=bOk && g.exec("ALTER SERVER ROLE [processadmin] ADD MEMBER ["+sUser+"]")>-2;
      //if (bFull)
      if (iArt==ADMIN || iArt==FULL)
    	  bOk=bOk && g.exec("ALTER SERVER ROLE [securityadmin] ADD MEMBER ["+sUser+"]")>-2;
    }
	return bOk;
}

private static boolean DB_User_Del(Global g,String sUser,String sHost)
{
  //boolean b=false;
  //String sMandant=g.getMandant(g.getMandant(),"Kennung");
  sUser=getDbUser(g,sUser);//sMandant+"*"+sUser;
  if (g.MySQL())
  {
	  if (sHost==null)
		  sHost=SQL.getString(g, "select host from mysql.user where User='"+sUser+"'");
    g.exec("revoke ALL PRIVILEGES, GRANT OPTION from '" + sUser + "'@'" + sHost + "'");
    /*b=*/g.exec("delete from mysql.user where Host='"+sHost+"' and User='"+sUser+"'");//>0;
    g.exec("flush privileges;");
    return true;
  }
  else if (g.MS())
  {
	int iAnz=g.exec("drop user "+sUser);
	g.exec("drop login "+sUser);
    g.fixInfo("Entferne "+sUser+"->"+iAnz+" entfernt");
    //b=iAnz>0;
    return true;
  }
  return false;
}

public static boolean changePW(Global g,String sUser,String sPWx,String sPWold)
{
	boolean b=false;
	//String sMandant=g.getMandant(g.getMandant(),"Kennung");
	String sUH=null;
	boolean bAdmin=sUser!=null;
	if (bAdmin)
	{
		sUser=getDbUser(g,sUser);//sMandant+"*"+sUser;
		if (g.MySQL())
		  sUH=SQL.getString(g, "select host from mysql.user where User='"+sUser+"'");
	}
	else
		sUH=g.getSqlUser();
	
	if (g.MySQL())
    {
      //g.exec("update mysql.user set password=PASSWORD('"+sPWx+"') where User='"+sUser+"';");
	  //String sUH=SQL.getString(g, "select user()");
	  //g.fixtestError("changePW for User="+sUser+"/"+sUH);
	  int iAt=sUH.indexOf('@');
	  String sHost=sUH.substring(iAt+1);
	  if (sUser==null) sUser=sUH.substring(0, iAt);
	  sUH="'"+sUser+"'@'"+sHost+"'";
	  if (bAdmin)
		  b=g.exec("UPDATE mysql.user SET Password=PASSWORD('"+sPWx+"') where USER='"+sUser+"'")>-2;
	  else
		  b=g.exec("SET PASSWORD = PASSWORD('"+sPWx+"');")>-2;
		  //b=g.exec("SET PASSWORD FOR "+sUH+" = PASSWORD('"+sPWx+"');")>-2;
	  if (g.UserManager())
		  g.exec("flush privileges;");
    }
	else if (g.MS())
	{
		b=g.exec("ALTER LOGIN "+(sUser==null ? sUH:sUser)+" WITH PASSWORD = '"+sPWx+"'"+(sPWold==null ? ";":" OLD_PASSWORD='"+sPWold+"';"))>-2;
	}
	return b; 		
}

private void UserNew()
{
        bNewUser = true;
        TxtBBezeichnung.setText("");
        EdtBis.setDate(null);
        TxtBKennung.setText("");
        TxtBKennung.setEnabled(true);
        TxtBHost.setText("%");
        TxtNeuPasswort.setText(Static.sDefaultPW);
        TxtNeuPasswort.setEnabled(true);
        TxtBestPasswort.setText(Static.sDefaultPW);
        TxtBestPasswort.setEnabled(true);
        TxtTel.setText("");
        TxtEMail.setText("");
        CboSprache.setSelectedAIC(g.getSprache());
        CboLand.setSelectedAIC(g.getLand());
        //CboStamm.setSelectedAIC(0);
        CboStamm.setStamm(0);
        CboHBG.setSelectedAIC(0);
//        RadProg.setSelected(false);
//        RadDef.setSelected(false);
//        RadVerw.setSelected(false);
//        RadSuper.setSelected(false);
        CbxSpezial.setSelected(false);
        CbxMehrmals.setSelected(false);
        //CbxSoftTerm.setSelected(false);
        CbxTimer.setSelected(false);
        CbxTerminal.setSelected(false);
        CbxAutoFC.setSelected(false);
        CbxOC.setSelected(false);
        CbxVorlage.setSelected(false);
        CbxAPI.setSelected(false);
        CbxTest.setSelected(false);
        CbxNoMobile.setSelected(false);
        CbxNoAktiv.setSelected(false);
        Cbx2FA.setSelected(false);
        CbxAServer.setSelected(false);
        CbxImport.setSelected(false);
        CbxHS.setSelected(false);
        CbxOhne.setSelected(false);
        CbxDB.setSelected(false);
        //RadPW_MD5.setSelected(true);
        RadPW_MD5B.setSelected(true);
        //CbxPWneu.setSelected(false);
        //CbxLDAP.setSelected(false);
        RadUserManager.setSelected(false);
        RadNormal.setSelected(true);
        TxtBBezeichnung.requestFocus();
        showUser();
}

private boolean hasHost()
{
	return g.MySQL() && Version.getVer2()>513099;
}

private String getHost(int iUser)
{
	if (hasHost())
		return SQL.getString(g, "select host from benutzer where aic_benutzer="+iUser);
	else
		return "%";
}

private void showUsergroup()
{
	if (bFirstFUG)
		Static.centerComponent(FrmNewUserGroup,thisFrame);
	bFirstFUG=false;
    FrmNewUserGroup.setVisible(true);
}

private void showUser()
{
	if (bFirstFU)
		Static.centerComponent(FrmNewUser,thisFrame);
	FrmNewUser.setTitle(g.getShow(iCopyUser>0 ? "copy":bNewUser ? "new":"edit")+" "+g.getShow("Benutzer"));
	bFirstFU=false;
	if (bCheckLDAP)
	{
		RadPW_AZURE.setEnabled(!Static.Leer(getAzurePara()));
		RadPW_GOOGLE.setEnabled(!Static.Leer(getGooglePara()));
    String s=getEMailPara();
    RadPW_EMAIL.setEnabled(!Static.Leer(s));
    RadPW_KOMBI.setEnabled(!Static.Leer(s));
		Parameter Para=getLDAP_Para();
		RadPW_LDAP.setEnabled(Para.bGefunden);
		Para.free();
		bCheckLDAP=false;
	}
    FrmNewUser.setVisible(true);
}

private void UserEdit(boolean bCopy)
{
        SQL Qry = new SQL(g);
        int iAIC_Benutzer = ((Integer)OutBenutzer.getSelectedNode().getUserData()).intValue();
        iCopyUser=bCopy ? iAIC_Benutzer:0;
        bNewUser = bCopy;
        if(Qry.open("SELECT Kennung,"+g.AU_BezeichnungF("Benutzer")+" Bezeichnung,AIC_Land,use_bis, AIC_Sprache,Bits,aic_stamm,aic_benutzergruppe"+(hasHost() ?",host":"")+",tel,e_mail FROM Benutzer WHERE AIC_Benutzer="+iAIC_Benutzer) && !Qry.eof())
        {
                TxtBBezeichnung.setText(bCopy ? "":Qry.getS("Bezeichnung"));
                //TODO EdtBis setzen
                if (Qry.isNull("use_bis"))
                	EdtBis.setDate(null);
                else
                {
                	DateWOD DW=new DateWOD(Qry.getD("use_bis"));
                	DW.yesterday();
                	EdtBis.setDate(DW.toTimestamp());
                }
                TxtBKennung.setText(bCopy ? "":Qry.getS("Kennung"));
                if (hasHost())
                	TxtBHost.setText(Qry.getS("host"));
                TxtNeuPasswort.setText("");
                TxtNeuPasswort.setEnabled(bCopy);
                TxtBestPasswort.setText("");
                TxtBestPasswort.setEnabled(bCopy);
                TxtTel.setText(Qry.getS("Tel"));
                TxtEMail.setText(Qry.getS("E_Mail"));
                CboSprache.setSelectedAIC(Qry.getI("AIC_Sprache"));
                CboLand.setSelectedAIC(Qry.getI("AIC_Land"));
                //CboStamm.setSelectedAIC(Qry.getI("Aic_Stamm"));
                CboStamm.setStamm(Qry.getI("Aic_Stamm"));
                CboHBG.setSelectedAIC(Qry.getI("Aic_Benutzergruppe"));
                int iUE=Qry.getI("Bits")&Global.cstBenutzerEbene;
                RadProg.setSelected(iUE==Global.cstProg);
                RadDef.setSelected(iUE==Global.cstDef);   
                RadVerw.setSelected(iUE==Global.cstVerw);
                RadSuper.setSelected(iUE==Global.cstSuperUser);
                CbxSpezial.setSelected((Qry.getI("Bits")&Global.cstSpezial)>0);
                CbxMehrmals.setSelected((Qry.getI("Bits")&Global.cstMehrmals)>0);
                //CbxSoftTerm.setSelected((Qry.getI("Bits")&Global.cstSoftTerm)>0);
                CbxTimer.setSelected((Qry.getI("Bits")&Global.cstTimerBenutzer)>0);
                CbxTerminal.setSelected((Qry.getI("Bits")&Global.cstTerminalUser)>0);
                CbxAutoFC.setSelected((Qry.getI("Bits")&Global.cstAutoFC)>0);
                CbxOC.setSelected((Qry.getI("Bits")&Global.cstOC)>0);
                CbxVorlage.setSelected((Qry.getI("Bits")&Global.cstVorlage)>0);
                CbxAPI.setSelected((Qry.getI("Bits")&Global.cstAPI)>0);
                CbxTest.setSelected((Qry.getI("Bits")&Global.cstTest)>0);
                CbxNoMobile.setSelected((Qry.getI("Bits")&Global.cstNoMobile)>0);
                CbxNoAktiv.setSelected((Qry.getI("Bits")&Global.cstNoAktiv)>0);
                Cbx2FA.setSelected((Qry.getI("Bits")&Global.cst2FA)>0);
                CbxAServer.setSelected((Qry.getI("Bits")&Global.cstAServerUser)>0);
                CbxImport.setSelected((Qry.getI("Bits")&Global.cstImport)>0);
                CbxHS.setSelected((Qry.getI("Bits")&Global.cstHS)>0);
                CbxOhne.setSelected((Qry.getI("Bits")&Global.cstOhne)>0);
                boolean bDB=(Qry.getI("Bits")&Global.cstDB)>0;
                CbxDB.setSelected(bDB);
                TxtBKennung.setEnabled(!bDB);
//                RadPW_Normal.setSelected((Qry.getI("Bits")&Global.cstPW)==0);
                RadPW_GOOGLE.setSelected((Qry.getI("Bits")&Global.cstPW)==Global.cstPW_GOOGLE);
                RadPW_AZURE.setSelected((Qry.getI("Bits")&Global.cstPW)==Global.cstPW_AZURE);
                RadPW_MD5.setSelected((Qry.getI("Bits")&Global.cstPW)==Global.cstPW_MD5);
                RadPW_MD5B.setSelected((Qry.getI("Bits")&Global.cstPW)==Global.cstPW_MD5B);
                RadPW_LDAP.setSelected((Qry.getI("Bits")&Global.cstPW)==Global.cstPW_LDAP);
                RadPW_LTOKEN.setSelected((Qry.getI("Bits")&Global.cstPW)==Global.cstPW_LTOKEN);
                RadPW_EMAIL.setSelected((Qry.getI("Bits")&Global.cstPW)==Global.cstPW_EMAIL);
                RadPW_KOMBI.setSelected((Qry.getI("Bits")&Global.cstPW)==Global.cstPW_KOMBI);
                //CbxPWneu.setSelected((Qry.getI("Bits")&Global.cstPWneu)>0);
                //CbxLDAP.setSelected((Qry.getI("Bits")&Global.cstLDAP)>0);
                RadUserManager.setSelected(iUE==Global.cstUserManager);
                RadNormal.setSelected(iUE==Global.cstNormalUser);
//                boolean bDef= (RadProg.isSelected() || RadDef.isSelected()) && (iAIC_Benutzer!=g.getBenutzer());
//                RadVerw.setEnabled(!bDef);
//                RadSuper.setEnabled(!bDef);
//                RadUserManager.setEnabled(!bDef);
//                RadNormal.setEnabled(!bDef);
                showUser();
                Qry.close();
        }
        Qry.free();
}

//private void UserCopy()
//{
//	int iAIC_Benutzer = ((Integer)OutBenutzer.getSelectedNode().getUserData()).intValue();
//	iCopyUser=iAIC_Benutzer;
//	bNewUser = true;
//	g.fixtestError("Copy User "+iAIC_Benutzer);
//	TxtBBezeichnung.setText("");
//	TxtBKennung.setText("");
//	TxtNeuPasswort.setText("");
//	TxtBestPasswort.setText("");
//	showUser();
//}

private void UserCheck()
      {
        Tabellenspeicher Tab=new Tabellenspeicher(g,"select Aic,Kennung,Mandant,null neu from (select aic_benutzer Aic,Kennung,(select kennung from mandant where aic_mandant=b.aic_mandant) Mandant"+
                                                  ",(select count(*) from benutzer where kennung=b.kennung and geloescht is null) Anzahl from benutzer b where geloescht is null) x where Anzahl>1",true);
        Vector<String> Vec=new Vector<String>();
        for (Tab.moveFirst();!Tab.out();)
        {
          String s=Tab.getS("Kennung");
          if (Vec.contains(s))
          {
            s+="_"+Tab.getS("Mandant");
            if (s.length()>40)
              s=s.substring(0,40);
            Tab.setInhalt("neu",s);
            Tab.moveNext();
          }
          else
          {
            Vec.addElement(s);
            Tab.clearInhalt();
          }
        }
        int i=0;
        if (Tab.isEmpty())
          new Message(Message.INFORMATION_MESSAGE,thisJFrame(),g).showDialog("keineBenutzerDoppelt");
        else
          if (new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,thisJFrame(),g,0).showDialog("doppelte_Benutzer",Tab)== Message.YES_OPTION)
          {
            Tab.Export();
            for (Tab.moveFirst(); !Tab.out(); Tab.moveNext())
              if (!Tab.getS("Kennung").equals(Tab.getS("neu")))
                i += g.exec("update benutzer set kennung='" + Tab.getS("neu") + "' where aic_benutzer=" + Tab.getI("Aic"));
          }
        if (i>0)
          g.diskInfo(i+" Benutzer-Kennungen geändert");
      }

private void showAbf()
      {
        JCOutlinerNode[] NodeAbf = OutAbfrage.getSelectedNodes();
        Vector<Integer> Vec=new Vector<Integer>();
        String s="";
        if (NodeAbf != null && NodeAbf[0] != null && NodeAbf[0].getUserData() != null)
          for(int i=0;i<NodeAbf.length;i++)
          {
            Vec.addElement(Sort.geti(NodeAbf[i].getUserData(), 0));
            s+=" "+NodeAbf[i];
          }
        if (Vec.size()>0)
        {
          Tabellenspeicher Tab=new Tabellenspeicher(g, "select distinct * from (select " + g.AU_Bezeichnungo("Benutzer") + " Benutzer,benutzer.kennung Benutzerkennung," +
                               g.AU_BezeichnungF("benutzergruppe") + " Benutzergruppe" + g.AU_Bezeichnung1("Mandant", "Benutzer") + " Mandant,benutzer.geloescht entfernt"+
                               " from benutzergruppe" + g.join2("benutzer_zuordnung", "benutzergruppe") + g.join("benutzer", "benutzer_zuordnung") +
                               " where benutzer_zuordnung.aic_abfrage" + Static.SQL_in(Vec) +") x order by Benutzergruppe,Benutzer", true);
          Tab.setTitel("Benutzer",g.getBegriffS("Show","Benutzer"));
          Tab.setTitel("Benutzerkennung",g.getBegriffS("Show","Benutzerkennung"));
          Tab.setTitel("Benutzergruppe",g.getBegriffS("Show","Benutzergruppe"));
          Tab.setTitel("Mandant",g.getBegriffS("Show","Mandant"));
          Tab.showGrid("Abfrage"+s,thisFrame);
        }
      }

      private void showBG()
      {
        JCOutlinerNode[] NodeBG = OutBenutzergruppe.getSelectedNodes();
        Vector<Integer> Vec=new Vector<Integer>();
        if (NodeBG != null && NodeBG[0] != null && NodeBG[0].getUserData() != null)
          for(int i=0;i<NodeBG.length;i++)
            Vec.addElement((Integer)((Vector)NodeBG[i].getUserData()).elementAt(0));
        if (Vec.size()>0)
        {
          Tabellenspeicher Tab=new Tabellenspeicher(g, "select distinct * from (select " + g.AU_Bezeichnungo("Benutzer") + " Benutzer,benutzer.kennung Benutzerkennung," +
                               g.AU_BezeichnungF("benutzergruppe") + " Benutzergruppe" + g.AU_Bezeichnung1("Mandant", "Benutzer") + " Mandant,benutzer.geloescht entfernt"+
                               " from benutzergruppe" + g.join2("benutzer_zuordnung", "benutzergruppe") + g.join("benutzer", "benutzer_zuordnung") +
                               " where benutzergruppe.aic_benutzergruppe" + Static.SQL_in(Vec) +") x order by Benutzergruppe,Benutzer", true);
          Tab.setTitel("Benutzer",g.getBegriffS("Show","Benutzer"));
          Tab.setTitel("Benutzerkennung",g.getBegriffS("Show","Benutzerkennung"));
          Tab.setTitel("Benutzergruppe",g.getBegriffS("Show","Benutzergruppe"));
          Tab.setTitel("Mandant",g.getBegriffS("Show","Mandant"));
          Tab.showGrid("Zuordnung",thisFrame);
        }
      }


/*private void checkPW()
      {
        int iL=SQL.getInteger(g,g.ASA() ? "SELECT Width FROM Syscolumn JOIN Systable WHERE Column_name='PASSWORT' and table_name='BENUTZER'":
            g.MySQL() ? "select character_maximum_length from information_schema.columns where table_schema=DATABASE() and upper(table_name)='BENUTZER' and column_name='PASSWORT'":
            g.MS() ? "SELECT c.length width FROM sysobjects o JOIN syscolumns c ON o.id = c.id WHERE o.name = 'BENUTZER' and c.name='PASSWORT'":"");
      }*/

private void DB_User_Config()
{
  if (DlgDB_User == null)
  {
    DlgDB_User = new JDialog(FrmNewUser, g.getBegriffS("Dialog", "DB_User"), true);
    DlgDB_User.getContentPane().setLayout(new BorderLayout(2, 2));
    TxtUser= new Text("",30);
    TxtPW1 = new JPasswordField();
    TxtPW2 = new JPasswordField();
    TxtHost= new Text("localhost",30);
    JPanel PnlN=new JPanel(new BorderLayout(2,2));
    JPanel Pnl = new JPanel(new GridLayout(0,1,0,2));
      g.addLabel(Pnl,"User",TxtUser);
      g.addLabel(Pnl,"neues Passwort",TxtPW1);
      g.addLabel(Pnl,"Bestaetigung Passwort",TxtPW2);
      g.addLabel(Pnl,"Host",TxtHost);
    PnlN.add("West",Pnl);
    Pnl = new JPanel(new GridLayout(0,1,0,2));
      g.addN(TxtUser,Pnl);
      g.addN(TxtPW1,Pnl);
      g.addN(TxtPW2,Pnl);
      g.addN(TxtHost,Pnl);
    PnlN.add("Center",Pnl);
    JScrollPane Scroll=new JScrollPane(PnlN);
      Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
    DlgDB_User.getContentPane().add("North",Scroll);

    Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
    JButton BtnDel = g.getButton("Loeschen","DB_User_Del",AL);
    JButton BtnOk = g.getButton("Ok","DB_User_Ok",AL);
    JButton BtnAbbruch = g.getButton("Abbruch","DB_User_Abbruch",AL);
    DlgDB_User.getRootPane().setDefaultButton(BtnOk);
    Pnl.add(BtnDel);
    Pnl.add(BtnOk);
    Pnl.add(BtnAbbruch);
    DlgDB_User.getContentPane().add("South", Pnl);
    DlgDB_User.pack();
  }
  Static.centerComponent(DlgDB_User,thisFrame);
  DlgDB_User.setVisible(true);
}

private void DB_User_ok()
{
	g.fixtestInfo("DB_User_ok");
  String sPW=new String(TxtPW1.getPassword());
  if(sPW.equals("") || !sPW.equals(new String(TxtPW2.getPassword())))
    new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog(sPW.equals("") ? "Passwort":"Passwort_ungleich");
  else if(TxtUser.getText().equals("") || g.MySQL() && TxtHost.getText().equals(""))
    new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("unvollstaendig");
  else 
  {
    setUserOnDB(g,TxtUser.getText(),TxtHost.getText(),sPW,FULL);
    DlgDB_User.setVisible(false);
  }
}

// Azure

private String getAzurePara()
{
//	g.fixtestError("getAzurePara");
	bCheckLDAP=true;
	Parameter Para=new Parameter(g,"Azure");
	Para.getMParameter("Server",true);
	String s=Para.sParameterzeile;
	Para.free();
	return s;
}

private void Azure_Config()
{
  if (DlgAzure==null)
  {
	  DlgAzure = new JDialog(FrmNewUser, g.getBegriffS("Dialog", "Azure-Parameter"), true);
	  DlgAzure.getContentPane().setLayout(new BorderLayout(2, 2));
    TxtServ= new Text("http://localhost",60);//"https://login.microsoftonline.com/common/oauth2",60);
    TxtDom2= new Text("",60);
    TxtClientId= new Text("",60);
    TxtTenantId= new Text("",60);
    TxtScope= new Text("",60);
    JPanel PnlN=new JPanel(new BorderLayout(2,2));
    JPanel Pnl = new JPanel(new GridLayout(0,1,0,2));
      g.addLabel(Pnl,"RedirectUrl",TxtServ,"0");
      g.addLabel(Pnl,"Domaene",TxtDom2,"0");
      g.addLabel(Pnl,"ClientID",TxtClientId,"0");
      g.addLabel(Pnl,"TenantID",TxtTenantId,"0");
      g.addLabel(Pnl,"Scope",TxtScope,"0");
    PnlN.add("West",Pnl);
    JScrollPane Scroll=new JScrollPane(PnlN);
      Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
      DlgAzure.getContentPane().add("North",Scroll);

    Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
    JButton BtnDel = g.getButton("Loeschen","Azure_Del",AL);
    JButton BtnOk = g.getButton("Ok","Azure_Ok",AL);
    JButton BtnAbbruch = g.getButton("Abbruch","Azure_Abbruch",AL);
    DlgAzure.getRootPane().setDefaultButton(BtnOk);
    Pnl.add(BtnDel);
    Pnl.add(BtnOk);
    Pnl.add(BtnAbbruch);
    DlgAzure.getContentPane().add("South", Pnl);
    DlgAzure.pack();
  }
  String s=getAzurePara();		    
  if (!Static.Leer(s))
  {
    String sAry[]=s.split("&");
    int i=sAry.length;
    TxtServ.setText(sAry[0]);
    TxtDom2.setText(sAry[1]);
    TxtClientId.setText(sAry[2]);
    if (i>3)
    	TxtTenantId.setText(sAry[3]);
    if (i>4)
    	TxtScope.setText(sAry[4]);
  }
  else
  {
    TxtServ.setText("http://localhost");//"https://login.microsoftonline.com/common/oauth2");
    TxtDom2.setText("");
    TxtClientId.setText("");
    TxtTenantId.setText("");
    TxtScope.setText("User.Read");
  }
  
  if (FrmNewUser!= null && FrmNewUser.isVisible())
    Static.centerComponent(DlgAzure,FrmNewUser);
  else
    Static.centerComponent(DlgAzure,thisFrame);
  g.fixtestError(Count.print("SQL",Count.SQL));
  DlgAzure.setVisible(true);
}

private void Azure_Del()
{
  Parameter Para = new Parameter(g, "Azure");
  Para.delMParameter("Server", true);
  Para.free();
  TxtServ.setText("https://login.microsoftonline.com/common/oauth2");
  TxtDom2.setText("");
  TxtClientId.setText("");
  TxtTenantId.setText("");
  TxtScope.setText("User.Read");
}

private void Azure_ok()
{
  Parameter Para=new Parameter(g,"Azure");
  Para.setMParameter("Server",TxtServ.getText()+"&"+TxtDom2.getText()+"&"+TxtClientId.getText()+"&"+TxtTenantId.getText()+"&"+TxtScope.getText(),0,0,0,0,g.getMandant());
  Para.free();

  DlgAzure.setVisible(false);
}

// Google - Parameter einstellen/löschen

private String getGooglePara()
{
	g.fixInfoT("getGooglePara");
//	bCheckLDAP=true;
	Parameter Para=new Parameter(g,"Google");
	Para.getMParameter("Server",true);
	String s=Para.sParameterzeile;
	Para.free();
	return s;
}

private void Google_Config()
{
  if (DlgGoogle==null)
  {
	  DlgGoogle = new JDialog(FrmNewUser, g.getBegriffS("Dialog", "Google-Parameter"), true);
	  DlgGoogle.getContentPane().setLayout(new BorderLayout(2, 2));
    TxtClientIdG= new Text("",150);
    TxtTokenG= new AUTextArea(g,0);
    TxtScopeG= new Text("",60);
    CbxOfflineG=g.getCheckbox("grantOffline");
    JPanel PnlN=new JPanel(new BorderLayout(2,2));
    JPanel Pnl = new JPanel(new GridLayout(0,1,0,2));
      g.addLabel(Pnl,"ClientID",TxtClientIdG,"0");
      g.addLabel(Pnl,"Scope",TxtScopeG,"0");
      Pnl.add(CbxOfflineG);
      Pnl.add(new JLabel());
//      g.addLabel(Pnl,"TestToken",TxtTokenG,"0");
      
//      g.addLabel(Pnl,"",CbxOfflineG,"0");
    PnlN.add("West",Pnl);
    GroupBox PnlC=new GroupBox("Test-Token");
    PnlC.add(TxtTokenG);
    DlgGoogle.getContentPane().add("Center", PnlC);
    JScrollPane Scroll=new JScrollPane(PnlN);
      Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
      DlgGoogle.getContentPane().add("North",Scroll);

    Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
    JButton BtnOk = g.getButton("Ok","Google_Ok",AL);
    JButton BtnAbbruch = g.getButton("Abbruch","Google_Abbruch",AL);
    DlgGoogle.getRootPane().setDefaultButton(BtnOk);
    JButton BtnTest2 = g.getButton("Test2","Google_Test2",AL);
//    BtnTest2.setMinimumSize(new Dimension(150,30));
    BtnTest2.setPreferredSize(new Dimension(80,28));
//    Pnl.add(g.getButton("Test","Google_Test",AL));
    Pnl.add(BtnTest2);
    Pnl.add(g.getButton("Loeschen","Google_Del",AL));
    Pnl.add(BtnOk);
    Pnl.add(BtnAbbruch);
    DlgGoogle.getContentPane().add("South", Pnl);
    DlgGoogle.pack();
  }
  String s=getGooglePara();		    
  if (!Static.Leer(s))
  {
    String sAry[]=s.split("&");
//    int i=sAry.length;
    TxtClientIdG.setText(sAry[0]);
    TxtScopeG.setText(sAry[1]);
  }
  else
  {
    TxtClientIdG.setText("");
    TxtScopeG.setText("profile,email");
  }
  
  if (FrmNewUser!= null && FrmNewUser.isVisible())
    Static.centerComponent(DlgGoogle,FrmNewUser);
  else
    Static.centerComponent(DlgGoogle,thisFrame);
  g.fixtestError(Count.print("SQL",Count.SQL));
  DlgGoogle.setVisible(true);
}

private void Google_Test2()
{
	String sEMail=null;
	String sToken=TxtTokenG.getValue();
	try {
		URL url = new URL("https://www.googleapis.com/oauth2/v1/tokeninfo?access_token="+sToken);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setConnectTimeout(1000);
		con.setReadTimeout(10000);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String sIn=in.readLine();
		String s=null;
		while (sIn != null) {
			g.fixInfo(sIn);
			s=s==null ? sIn:s+"\n"+sIn;
			sIn=in.readLine();
		}
		JSONObject json=new JSONObject(s);
		sEMail=json.getString("email");
	}
	catch(Exception e)
	{
		Static.printError("Google_Test2 liefert:"+e);
	}
	g.fixtestError("Google-Test2 liefert:" +sEMail);
}

//private void Google_Test()
//{
//	String s=new Google().checkGoogleToken(g,TxtTokenG.getValue());
//	g.fixtestError("Google-Test liefert:" +s);
//}

private void Google_Del()
{
  Parameter Para = new Parameter(g, "Google");
  Para.delMParameter("Server", true);
  Para.free();
}

private void Google_ok()
{
  Parameter Para=new Parameter(g,"Google");
  Para.setMParameter("Server",TxtClientIdG.getText()+"&"+TxtScopeG.getText(),CbxOfflineG.isSelected() ? 1:0,0,0,0,g.getMandant());
  Para.free();

  DlgGoogle.setVisible(false);
}

// LDAP - Konfiguration

private Parameter getLDAP_Para()
{
	bCheckLDAP=true;
	Parameter Para=new Parameter(g,"LDAP");
	Para.getMParameter("IP",true);
	return Para;
}

private void LDAP_Config()
{
  if (DlgLDAP==null)
  {
    DlgLDAP = new JDialog(FrmNewUser, g.getBegriffS("Dialog", "LDAP-Parameter"), true);
    DlgLDAP.getContentPane().setLayout(new BorderLayout(2, 2));
    TxtIP= new Text("",60);
    TxtPort=new Zahl(389);
    TxtDom= new Text("",60);
    JPanel PnlN=new JPanel(new BorderLayout(2,2));
    JPanel Pnl = new JPanel(new GridLayout(0,1,0,2));
      g.addLabel(Pnl,"IP-Adresse",TxtIP,"0");
      g.addLabel(Pnl,"Port",TxtPort,"0");
      g.addLabel(Pnl,"Domaene",TxtDom,"0");
    PnlN.add("West",Pnl);
//    Pnl = new JPanel(new GridLayout(0,1,0,2));
//      g.addN(TxtIP,Pnl);
//      g.addN(TxtPort,Pnl);
//      g.addN(TxtDom,Pnl);
//    PnlN.add("Center",Pnl);
    JScrollPane Scroll=new JScrollPane(PnlN);
      Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
    DlgLDAP.getContentPane().add("North",Scroll);

    Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
    JButton BtnDel = g.getButton("Loeschen","LDAP_Del",AL);
    JButton BtnOk = g.getButton("Ok","LDAP_Ok",AL);
    JButton BtnAbbruch = g.getButton("Abbruch","LDAP_Abbruch",AL);
    DlgLDAP.getRootPane().setDefaultButton(BtnOk);
    Pnl.add(BtnDel);
    Pnl.add(BtnOk);
    Pnl.add(BtnAbbruch);
    DlgLDAP.getContentPane().add("South", Pnl);
    DlgLDAP.pack();
  }
  Parameter Para=getLDAP_Para();
  if (Para.bGefunden)
  {
    String s=Para.sParameterzeile;
    TxtPort.setValue(Para.int1);
    TxtIP.setText(s.substring(0,s.indexOf("/")));
    TxtDom.setText(s.substring(s.indexOf("/")+1));
  }
  else
  {
    TxtPort.setValue(389);
    TxtIP.setText("");
    TxtDom.setText("");
  }
  Para.free();
  g.fixtestError(Count.print("SQL",Count.SQL));
  if (FrmNewUser!= null && FrmNewUser.isVisible())
    Static.centerComponent(DlgLDAP,FrmNewUser);
  else
    Static.centerComponent(DlgLDAP,thisFrame);
  DlgLDAP.setVisible(true);
}

private void LDAP_Del()
{
  Parameter Para = new Parameter(g, "LDAP");
  Para.delMParameter("IP", true);
  Para.free();
  TxtPort.setValue(389);
  TxtIP.setText("");
  TxtDom.setText("");
}

private void LDAP_ok()
{
  Parameter Para=new Parameter(g,"LDAP");
  Para.setMParameter("IP",TxtIP.getText()+"/"+TxtDom.getText(),TxtPort.intValue(),0,0,0,g.getMandant());
  Para.free();

  DlgLDAP.setVisible(false);
}

private void Init()
      {
        Vector VecHS=SQL.getVector("select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Applikation"),g);
        Vector VecBG=SQL.getVector("select distinct aic_benutzergruppe from berechtigung where aic_tabellenname=6 and "+g.in("aic_fremd",VecHS),g);
        g.exec("update benutzer set bits=bits | 0x0100 where aic_benutzer in (select aic_benutzer from benutzer_zuordnung where "+g.in("aic_benutzergruppe",VecBG)+")");
        Refresh(true);
      }

public void show()
{
  if (Self==null)
  {
    g.testInfo("UserManager nochmals erzeugen");
    Self = new UserManager(g);
    Self.show();
    return;
  }
	if (g.getMandant() != iMandantOld)
          Refresh(true);
	super.show();
	OutBenutzer.folderChanged(OutBenutzer.getRootNode());
	OutBenutzergruppe.folderChanged(OutBenutzergruppe.getRootNode());
	//OutBenutzergruppe2.folderChanged(OutBenutzergruppe2.getRootNode());
	//OutBenutzergruppeMA.folderChanged(OutBenutzergruppeMA.getRootNode());
	//OutGruppen.folderChanged(OutGruppen.getRootNode());
	//OutStamm.folderChanged(OutStamm.getRootNode());
}

private void Refresh(boolean bAll)
{
  long lClock=Static.get_ms();
  bCheckLDAP=true;
  setTitle(sFormularBezeichnung+" - "+g.getMandant(0,"Bezeichnung"));
  JCOutlinerNode Nod=OutBenutzer.getSelectedNode();
  int iUser=0;
  if (Nod!=null && Nod.getLevel()>0)
  {
    while(Nod.getLevel()>1)
      Nod=Nod.getParent();
    iUser=((Integer)Nod.getUserData()).intValue();
    g.progInfo("iUser="+iUser);
  }
        CboStartFormular.fillCbo("SELECT distinct Begriff.AIC_Begriff,Begriff.defBezeichnung Kennung"+g.AU_Bezeichnung("Begriff")+
                " from begriff"+g.join("code","begriff")+" where code.kennung='Start' and begriff.aic_stammtyp is null","Begriff",true,false);

  //g.TabRollen.posInhalt("Kennung","MITARBEITER");
  //CboStamm.fillCbo("Select aic_stamm,"+SQL.concat(SQL.ifnull("austritt","''","'_'"),"bezeichnung")+" kennung"+g.AU_Bezeichnung2("Stamm","stammview2")+
  //                 " from Stammview2 where aic_rolle="+g.TabRollen.getI("Aic")+(g.AllUnlimited() ?"":" and aic_mandant="+g.getMandant()),"Stamm",true,false);
        fillOutliners(bAll);
        //fill_OutStamm();
        iMandantOld=g.getMandant();
    if (iUser>0)
    {
      Vector Vec=OutBenutzer.getRootNode().getChildren();
      boolean bGefunden=false;
      for(int i=0;i<Vec.size() && !bGefunden;i++)
      {
        Nod=(JCOutlinerNode)Vec.elementAt(i);
        if (((Integer)Nod.getUserData()).intValue()==iUser)
        {
          bGefunden=true;
          ((JCOutlinerFolderNode)Nod).setState(BWTEnum.FOLDER_OPEN_ALL);
          Static.makeVisible(OutBenutzer,Nod);
        }
      }
    }
    //RefreshMA();
    g.clockInfo("Refresh UserManager", lClock);
}

private void Build()
{
	CboSprache = new ComboSort(g);
	CboSprache.fillDefTable("Sprache",true);
	CboLand = new ComboSort(g);
	CboLand.fillDefTable("Land",true);
        CboStamm = new StammEingabe(thisFrame,g,g.iSttANR,g.TabRollen.getAic("MITARBEITER"));
	//CboStamm = new ComboSort(g);
	//CboStamm.fillStammTable("Benutzer",true);
        CboHBG = new ComboSort(g);
	CboHBG.fillDefTable("BENUTZERGRUPPE",true);

	CboStartFormular = new ComboSort(g);

	//CboAbschluss = new ComboSort(g);
	//CboAbschluss.fillStammTable(g.TabStammtypen.getAic("ABSCHLUSS"),true);

	/*TabBenutzer = new Tabellenspeicher(g,new String[]{"AIC_B","AIC_BG","Art","Node"});
	TabBenutzer.sGruppe="AIC_B";
	TabBenutzer.sAIC="AIC_BG";*/
        VecBenutzer = new Vector<Integer>();


	//TabStamm = new Tabellenspeicher(g,new String[] {"Tab","AIC_BG","AIC_Eig","AIC_Stm","Art","Node"});

	//OutBenutzergruppe.setBackground(Color.white);
        //OutAbfrage.setBackground(Color.white);
	//OutBenutzer.setBackground(Color.white);
	//OutBenutzergruppe.setRootVisible(false);
        //OutAbfrage.setRootVisible(false);
	//OutBenutzer.setRootVisible(false);
	OutBenutzergruppe.setAllowMultipleSelections(true);
        OutAbfrage.setAllowMultipleSelections(true);
	OutBenutzer.setAllowMultipleSelections(true);
	//OutBenutzergruppe.setColumnLabelSortMethod(Sort.sortMethod);
        //OutAbfrage.setColumnLabelSortMethod(Sort.sortMethod);
	//OutBenutzer.setColumnLabelSortMethod(Sort.sortMethod);
	String[] sAry=new String[] {"Bezeichnung","Kennung","Austritt_NT","berechtigt","spezial","HS","Vorlage","Stamm","MA-Austritt","Anzahl","Land","Aic","PW-Date","Tel","E-Mail","Art","PW-Art","Sprache","Hauptbenutzergruppe"};//:new String[] {"Bezeichnung","Kennung","Austritt_NT","berechtigt","spezial","HS","Vorlage","Stamm","MA-Austritt","Anzahl","Land"};
        g.initOutliner(OutBenutzer,sAry);
        OutBenutzer.setRootVisible(true);
        JCOutlinerNodeStyle StyRoot=g.getStyle(null);
        OutBenutzer.getRootNode().setStyle(StyRoot);
	//String[] s = new String[] {g.getBegriff("Show","Bezeichnung"),g.getBegriff("Show","Kennung"),g.getBegriff("Show","Geloescht"),g.getBegriff("Show","berechtigt"),g.getBegriff("Show","spezial"),g.getBegriff("Show","Stamm")};
	//OutBenutzer.setColumnButtons(s);
	//OutBenutzer.setNumColumns(s.length);
        g.initOutliner(OutBenutzergruppe,new String[] {"Bezeichnung","Kennung","Mandant","History","Abfrage","nurAll","nurWeb","ReadOnly","Pers","Aic"});
	//s = new String[] {g.getBegriff("Show","Bezeichnung"),g.getBegriff("Show","Kennung"),(g.TabTabellenname.posInhalt("Kennung","MANDANT")?g.TabTabellenname.getS("Bezeichnung"):"Mandant"),g.getBegriff("Show","History"),g.getBegriff("Frame","Abfrage"),g.getBegriff("Show","ReadOnly")};
	//OutBenutzergruppe.setColumnButtons(s);
//        if (!g.Def()) OutBenutzergruppe.setNumColumns(7);
	//OutBenutzergruppe.setNumColumns(s.length);
        g.initOutliner(OutAbfrage,new String[] {"Bezeichnung","Stammtyp"});      
        //s = new String[] {g.getBegriff("Show","Bezeichnung"),g.getBezeichnung("Tabellenname","STAMMTYP")};
        //OutAbfrage.setColumnButtons(s);
	//OutAbfrage.setNumColumns(s.length);

	BtnCopyUserGroup = getButton("Kopie_BG");
	if(BtnCopyUserGroup==null) BtnCopyUserGroup=new JButton();
	BtnCopyUser = getButton("Kopie_User","CopyUser",AL);
	if(BtnCopyUser==null) BtnCopyUser=new JButton();
	BtnEditUser = getButton("Edit User","EditUser",AL);
	if(BtnEditUser==null) BtnEditUser=new JButton();
	BtnEditUserGroup = getButton("Edit Usergroup");
	//if(BtnEditUserGroup==null) BtnEditUserGroup=new JButton();
	BtnDeleteUser = getButton("Delete User","DeleteUser",AL);
  getButton("DelLTOKEN","DelLTOKEN",AL);
	//if(BtnDeleteUser==null) BtnDeleteUser=new JButton();
	BtnUndeleteUser = getButton("Undelete","UndeleteUser",AL);
	if(BtnUndeleteUser==null) BtnUndeleteUser=new JButton();
	BtnDeleteUserGroup = getButton("Delete Usergroup","DeleteUserGroup",AL);
	//if(BtnDeleteUserGroup==null) BtnDeleteUserGroup=new JButton();
	getButton("New User","NewUser",AL);
	//if(BtnNewUser==null) BtnNewUser=new JButton();
	BtnNewUserGroup = getButton("New Usergroup");
	//if(BtnNewUserGroup==null) BtnNewUserGroup=new JButton();
	//BtnOk = getButton("Ok");
	//if(BtnOk==null) BtnOk=new JButton();
	//BtnAbbruch = getButton("Abbruch");
	//if(BtnAbbruch==null) BtnAbbruch=new JButton();
        //BtnTest = getButton("Test");
        //BtnRefresh = getButton("Refresh");
        //BtnShow = getButton("Show");

	BtnSpeichern = getButton("Speichern");
        g.BtnAdd(getButton("Export"),"Export",AL);
        g.BtnAdd(getButton("Import"),"Import",AL);
	//if(BtnSpeichern==null) BtnSpeichern=new JButton();
	BtnBeenden = getButton("Beenden");
	//if(BtnBeenden==null) BtnBeenden=new JButton();
	BtnUGHinzufuegen = getButton(">","UGHinzufuegen",AL);
	BtnUGErsetzen = getButton("Ersetzen","UGErsetzen",AL);
	if(BtnUGErsetzen==null) BtnUGErsetzen=new JButton();
        BtnAbfHinzufuegen = getButton(">4","AbfHinzufuegen",AL);
        BtnStHinzufuegen = getButton(">5","StHinzufuegen",AL);
	//if(BtnUGHinzufuegen==null) BtnUGHinzufuegen=new JButton();
	BtnUGEntfernen = getButton("<","UGEntfernen",AL);
        BtnAbfEntfernen = getButton("<4","AbfEntfernen",AL);
        BtnStEntfernen = getButton("<5","StEntfernen",AL);
        //BtnErsetzen = getButton("Ersetzen");
	//if(BtnUGEntfernen==null) BtnUGEntfernen=new JButton();

	//BtnAdminPassword = getFormularButton("AdminPassword");
	//if(BtnAdminPassword==null) BtnAdminPassword=new JButton();
        //BtnCleanComputer = getFormularButton("CleanComputer");
	//if(BtnCleanComputer==null) BtnCleanComputer = new JButton();
        //BtnLogging = getFormularButton("Logging");
	//if(BtnLogging==null) BtnLogging = new JButton();
        BtnBerechtigung = getFormularButton("Berechtigung");
        if(BtnBerechtigung==null) BtnBerechtigung = new JButton();
        BtnPeriodensperre = getFormularButton("Periodensperre");
        if(BtnPeriodensperre==null) BtnPeriodensperre = new JButton();
        BtnPeriodensperre.setEnabled(g.SuperUser());
        JButton BtnDB_User = getButton("DB_User","DB_User",AL);
        if (BtnDB_User!=null) BtnDB_User.setVisible(g.Def());
        getButton("LDAP","LDAP",AL);
        getButton("Azure","Azure",AL);
        getButton("Google","Google",AL);
	BtnUserOk = g.getButton("Ok");
	BtnUserAbbruch = g.getButton("Abbruch");
	BtnUserGroupOk = g.getButton("Ok");
	BtnUserGroupAbbruch = g.getButton("Abbruch");

	//CbxAendern = getCheckbox("Aendern");
	//if(CbxAendern==null) CbxAendern=new JCheckBox();
	//CbxAnlegen = getCheckbox("Anlegen");
	//if(CbxAnlegen==null) CbxAnlegen=new JCheckBox();
	//CbxLoeschen = getCheckbox("Loeschen");
	//if(CbxLoeschen==null) CbxLoeschen=new JCheckBox();

	//CbxProg = g.getCheckbox("Prog");
	//CbxDef = g.getCheckbox("Def");
	//CbxSuper = g.getCheckbox("Superuser");
        CbxSpezial = getCheckboxM("Spezial",false);
        CbxMehrmals = getCheckboxM("Mehrmals",false);
        //CbxSoftTerm = g.getCheckbox("SoftTerm");
        CbxTimer = getCheckboxM("Timer",false);
        CbxTerminal = getCheckboxM("Terminal",false);
        CbxAutoFC = getCheckboxM("Auto-Fehlerkonsole",false);
        CbxOC = getCheckboxM("ohneComputer",false);
        CbxVorlage = getCheckboxM("Vorlage",false);
        CbxAPI = getCheckboxM("API",false);
        CbxTest = getCheckboxM("TestB",false);
        CbxNoMobile = getCheckboxM("noMobile",false);
        CbxNoAktiv = getCheckboxM("noAktiv",false);
        Cbx2FA = getCheckboxM("2FA",false);
        Cbx2FA.setVisible(false);
        CbxAServer = getCheckboxM("AServer",false);
        CbxImport = getCheckboxM("Import",false);
        CbxHS = getCheckboxM("Hauptschirm",false);
        CbxOhne = getCheckboxM("ohne Lizenz",false);
        CbxDB = getCheckboxM("auf DB",false);
        RadPW_GOOGLE = g.getRadiobutton("PW_GOOGLE");
        RadPW_AZURE = g.getRadiobutton("PW_AZURE");
        RadPW_MD5 = g.getRadiobutton("PW_MD5");
        RadPW_MD5B = g.getRadiobutton("PW_MD5B");
        RadPW_LDAP = g.getRadiobutton("PW_LDAP");
        RadPW_LTOKEN = g.getRadiobutton("PW_LTOKEN");
        RadPW_EMAIL = g.getRadiobutton("PW_EMAIL");
        RadPW_KOMBI = g.getRadiobutton("PW_KOMBI");
        RadPW_MD5.setEnabled(false);   
        ButtonGroup RadPW=new ButtonGroup();
        RadPW.add(RadPW_MD5);
        RadPW.add(RadPW_MD5B);
        RadPW.add(RadPW_LDAP);
        RadPW.add(RadPW_AZURE);
        RadPW.add(RadPW_GOOGLE);
        RadPW.add(RadPW_LTOKEN);
        RadPW.add(RadPW_EMAIL);
        RadPW.add(RadPW_KOMBI);
        //CbxPWneu = g.getCheckbox("PW neu");
        //CbxLDAP = g.getCheckbox("LDAP");
        //CbxUserManager = g.getCheckbox("UserManager");
        RadProg = g.getRadiobutton("Prog");
        RadDef = g.getRadiobutton("Def");
        RadVerw = g.getRadiobutton("Verwaltung");
        RadSuper = g.getRadiobutton("Superuser");
        RadUserManager = g.getRadiobutton("UserManager");
        RadNormal = g.getRadiobutton("Normal");
        ButtonGroup RadNeu=new ButtonGroup();
        RadNeu.add(RadProg);
        RadNeu.add(RadDef);
        RadNeu.add(RadVerw);
        RadNeu.add(RadSuper);
        RadNeu.add(RadUserManager);
        RadNeu.add(RadNormal);

	JPanel Pnl_Outliner_Usergroup = getFrei("Outliner Usergroup");
        JPanel Pnl_Outliner_Abfrage = getFrei("Abfrage");
	JPanel Pnl_Outliner_Benutzer = getFrei("Outliner Benutzer");
	JPanel Pnl_Stamm = getFrei("Stamm");

	Pnl_Outliner_Usergroup.add(OutBenutzergruppe);
        Pnl_Outliner_Abfrage.add(OutAbfrage);
	Pnl_Outliner_Benutzer.add(OutBenutzer);
        String [] s2 = new String[]{g.getBegriffS("Show","Stellvertreter")};
        OutErsatz.setColumnButtons(s2);
        OutErsatz.setNumColumns(s2.length);
        OutErsatz.getRootNode().setStyle(StyRoot);
	Pnl_Stamm.add(OutErsatz);
        s2 = new String[]{g.getBegriffS("Show","Nachrichtentyp")};
        OutNachricht.setColumnButtons(s2);
        OutNachricht.setNumColumns(s2.length);
        OutNachricht.getRootNode().setStyle(StyRoot);
        Pnl_Stamm.add(OutNachricht);
        JPanel PnlN=new JPanel(new GridLayout(0,2,4,4)); 
        JPanel PnlN1=new JPanel(new BorderLayout(2,2));
        JPanel PnlN2=new JPanel(new BorderLayout(2,2));
	FrmNewUser = new JDialog((JFrame)thisFrame,g.getBegriffS("Show","Benutzer"),true);
	FrmNewUser.getContentPane().setLayout(new BorderLayout(2,2));
	EdtBis=new Datum(g,"dd.MM.yyyy");
	JPanel Pnl1 = new JPanel(new GridLayout(0,1,0,2));
	JPanel Pnl2 = new JPanel(new GridLayout(0,1,0,2));
	g.addLabel(Pnl1,"Bezeichnung",TxtBBezeichnung);
	JPanel PnlV = null;
	if (g.Def())
	{
		PnlV = new JPanel(new GridLayout(1,2));
		TxtBHost.setEnabled(g.MySQL());
		TxtBHost.setEditable(g.Def());
		PnlV.add(TxtBHost);			
	}
	if (g.Def())
		PnlV.add(CbxVorlage);
	if (g.Def())
	  g.addLabel(Pnl2,"Host",TxtBHost);
	else
	{
		PnlV=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		PnlV.add(CbxVorlage);
		Pnl2.add(new JLabel());
	}
	g.addLabel(Pnl1,"Kennung",TxtBKennung);
	g.addLabel(Pnl2,"bis",EdtBis);
	g.addLabel(Pnl1,"neues Passwort",TxtNeuPasswort);
	g.addLabel(Pnl1,"Bestaetigung Passwort",TxtBestPasswort);
	if (g.Def())
		g.addLabel(Pnl2,"Tel",TxtTel);	
  boolean bEMail= !Static.Leer(getEMailPara());
  if (g.Def() || bEMail)
    g.addLabel(Pnl1,"E-Mail",TxtEMail);
	g.addLabel(Pnl2,"Sprache",CboSprache);
	g.addLabel(Pnl2,"Land",CboLand);
	g.addLabel(Pnl1,"Person",CboStamm);
    g.addLabel(Pnl2,"Hauptbenutzergruppe",CboHBG);
        
    PnlN1.add("West",Pnl1);
    PnlN2.add("West",Pnl2);
    PnlN.add(PnlN1);
    PnlN.add(PnlN2);
	JPanel PnlNC1 = new JPanel(new GridLayout(0,1,0,2));
	JPanel PnlNC2 = new JPanel(new GridLayout(0,1,0,2));
	g.addN(TxtBBezeichnung,PnlNC1);
	g.addN(PnlV,PnlNC2);
	//JPanel PnlUser=new JPanel(new GridLayout(1,2,5,2));
	g.addN(TxtBKennung,PnlNC1);
	g.addN(EdtBis,PnlNC2);
	//g.addN(PnlUser,PnlNC);
	g.addN(TxtNeuPasswort,PnlNC1);
	g.addN(TxtBestPasswort,PnlNC1);
	if (g.Def())
	  g.addN(TxtTel,PnlNC2);
  if (g.Def() || bEMail)
    g.addN(TxtEMail,PnlNC1);
	g.addN(CboSprache,PnlNC2);
	g.addN(CboLand,PnlNC2);
	g.addN(CboStamm,PnlNC1);
    g.addN(CboHBG,PnlNC2);
        PnlN1.add("Center",PnlNC1);
        PnlN2.add("Center",PnlNC2);
        JScrollPane Scroll=new JScrollPane(PnlN);
        Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
        FrmNewUser.getContentPane().add("North",Scroll);

        JPanel PnlW = new JPanel(new GridLayout(0,1,0,2));
//        PnlW.add(new JLabel());
        g.addLabel(PnlW,"Benutzer",null);
        g.addLabel(PnlW,"Passwort",null);
        g.addLabel(PnlW,"Berechtigung",null);
        if (g.Def())
        	g.addLabel(PnlW,"Anmeldung",null);
        //JPanel PnlC = new JPanel(new GridLayout(0,1,0,2));
        JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.LEFT,0,1));
        JPanel PnlNC3 = new JPanel(new GridLayout(0,1,0,2));
          //Pnl.add(new JLabel(g.getBegriffS("Show","Benutzer")+":"));
	    if(g.Prog())
            g.addComp(Pnl,RadProg);
        if(g.Def())
            g.addComp(Pnl,RadDef);
        if(g.Verwaltung())
        	g.addComp(Pnl,RadVerw);
        if(g.SuperUser())
            g.addComp(Pnl,RadSuper);
          g.addComp(Pnl,RadUserManager);
          g.addComp(Pnl,RadNormal);
        PnlNC3.add(Pnl);
        Pnl = new JPanel(new FlowLayout(FlowLayout.LEFT,0,1));
          //Pnl.add(new JLabel(g.getBegriffS("Show","Passwort")+":"));
        Pnl.add(RadPW_LTOKEN);
        if (g.Def())
          Pnl.add(RadPW_MD5);
        Pnl.add(RadPW_MD5B);
        Pnl.add(RadPW_LDAP);
        Pnl.add(RadPW_AZURE);
        Pnl.add(RadPW_GOOGLE);
        // Pnl.add(RadPW_SMS);
        Pnl.add(RadPW_EMAIL);
        Pnl.add(RadPW_KOMBI);
        Pnl.add(Cbx2FA);
        PnlNC3.add(Pnl);
        Pnl = new JPanel(new FlowLayout(FlowLayout.LEFT,0,1));
          CbxSpezial.setEnabled(g.Def());
          CbxOhne.setEnabled(g.Def());
          CbxDB.setEnabled(g.Grant());
          CbxDB.setVisible(g.MySQL() || g.MS());
          //Pnl.add(new JLabel(g.getBegriffS("Show","Berechtigung")+":"));
          Pnl.add(CbxSpezial);
          Pnl.add(CbxHS);
          if (g.Def())
          {
            Pnl.add(CbxOhne);
            Pnl.add(CbxDB);
          }
          Pnl.add(CbxNoMobile);
          if (g.Prog())
            Pnl.add(CbxNoAktiv);
//          Pnl.add(CbxVorlage);
          //Pnl.add(CbxPWneu);
          //Pnl.add(CbxLDAP);
        PnlNC3.add(Pnl);
        if (g.Def())
        {
         Pnl = new JPanel(new FlowLayout(FlowLayout.LEFT,0,1));
//          CbxTimer.setEnabled(g.Def());
//          CbxTerminal.setEnabled(g.Def());
//          CbxAServer.setEnabled(g.Def());
          //Pnl.add(new JLabel(g.getBegriffS("Show","Anmeldung")+":"));
          Pnl.add(CbxMehrmals);
          //Pnl.add(CbxSoftTerm);
          Pnl.add(CbxTimer);
          Pnl.add(CbxTerminal);
          Pnl.add(CbxAServer);
          Pnl.add(CbxImport);
          Pnl.add(CbxAPI);
          Pnl.add(CbxAutoFC);
          Pnl.add(CbxOC);
          Pnl.add(CbxTest);
//          CbxImport.setVisible(g.Def());
         PnlNC3.add(Pnl);
        }
        //}
    FrmNewUser.getContentPane().add("West",PnlW);
	FrmNewUser.getContentPane().add("Center",PnlNC3);
	Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
//        if (BtnLDAP==null)
//        {
//          BtnLDAP = g.getButton("LDAP", "LDAP", AL);
//          Pnl.add(BtnLDAP);
//        }
	Pnl.add(BtnUserOk);
        FrmNewUser.getRootPane().setDefaultButton(BtnUserOk);
	Pnl.add(BtnUserAbbruch);
	FrmNewUser.getContentPane().add("South",Pnl);
	//FrmNewUser.setSize(400,200);
	FrmNewUser.pack();

	FrmNewUserGroup = new JDialog((JFrame)thisFrame,g.getBegriffS("Show","Benutzergruppe"),true);
	FrmNewUserGroup.getContentPane().setLayout(new BorderLayout(2,2));
        PnlN=new JPanel(new BorderLayout(2,2));
	 Pnl = new JPanel(new GridLayout(0,1,0,2));
	  g.addLabel(Pnl,"Bezeichnung",TxtBGBezeichnung);
	  g.addLabel(Pnl,"Kennung",TxtBGKennung);
	  g.addLabel(Pnl,"Startformular",CboStartFormular);
	  //g.addLabel(Pnl,"Abschluss",CboAbschluss);
         PnlN.add("West",Pnl);
         Pnl = new JPanel(new GridLayout(0,1,0,2));
          g.addN(TxtBGBezeichnung,Pnl);
          g.addN(TxtBGKennung,Pnl);
          g.addN(CboStartFormular,Pnl);
          //g.addN(CboAbschluss,Pnl);
         PnlN.add("Center",Pnl);
        Scroll=new JScrollPane(PnlN);
        Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
        FrmNewUserGroup.getContentPane().add("North",Scroll);

	CbxHistory=getCheckboxM("History",false);
	CbxAbfrage=getCheckboxM("Abfrage",false);
	CbxReadOnly=getCheckboxM("ReadOnly",false);
        CbxNurFilter=getCheckboxM("nur Filter",false);
        CbxGeloeschte=getCheckboxM("geloeschte",false);
        CbxWorkflow=getCheckboxM("Workflow",false);
        CbxWorkflowZR=getCheckboxM("WorkflowZR",false);
        sWFZR=CbxWorkflowZR.getText();
        CbxWorkflow.addActionListener(new ActionListener()
        {
    		public void actionPerformed(ActionEvent ev)
			{
		        CbxWorkflowZR.setText((CbxWorkflow.isSelected() ? Static.sKein+" ":"")+sWFZR);
			}
    	});
        CbxNurWeb=getCheckboxM("nurWeb",false);
        CbxNurAll=getCheckboxM("nurAll",false);
        CbxPers=getCheckboxM("pers",false);
        CbxJeder=getCheckboxM("Jeder",false);
        CbxJeder.setEnabled(g.SuperUser());
	JPanel PnlC = new JPanel(new GridLayout(0,1,0,2));
         Pnl = new JPanel(new FlowLayout(FlowLayout.CENTER,2,1));
	 Pnl.add(CbxHistory);
	 Pnl.add(CbxAbfrage);
         Pnl.add(CbxNurFilter);
        PnlC.add(Pnl);
         Pnl = new JPanel(new FlowLayout(FlowLayout.CENTER,2,1));
         Pnl.add(CbxGeloeschte);
         Pnl.add(CbxJeder);
        PnlC.add(Pnl);
        Pnl = new JPanel(new FlowLayout(FlowLayout.CENTER,2,1));
        Pnl.add(CbxWorkflow);
        Pnl.add(CbxWorkflowZR);
        PnlC.add(Pnl);
        Pnl = new JPanel(new FlowLayout(FlowLayout.CENTER,2,1));
        Pnl.add(CbxNurWeb);
        Pnl.add(CbxNurAll);
        PnlC.add(Pnl);
        Pnl = new JPanel(new FlowLayout(FlowLayout.CENTER,2,1));
        Pnl.add(CbxReadOnly);
        Pnl.add(CbxPers);
       PnlC.add(Pnl);
	FrmNewUserGroup.getContentPane().add("Center",PnlC);
	Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
        FrmNewUserGroup.getRootPane().setDefaultButton(BtnUserGroupOk);
	Pnl.add(BtnUserGroupOk);
	Pnl.add(BtnUserGroupAbbruch);
	FrmNewUserGroup.getContentPane().add("South",Pnl);
	FrmNewUserGroup.pack();
//	FrmNewUserGroup.setSize(350*iFF/100,230*iFF/100);

}

private void fillAbfragen()
      {
        ((JCOutlinerFolderNode)OutAbfrage.getRootNode()).removeChildren();
        int iPosBG=g.TabBegriffgruppen.getPos("Kennung","Abfrage");
        JCOutlinerNodeStyle StyleAbfrage=g.getStyle(g.LoadImage(g.TabBegriffgruppen.getS(iPosBG,"Filename")));
        TabAbfrage=new Tabellenspeicher(g,"select * from (SELECT aic_abfrage,begriff.aic_begriff,aic_stammtyp,bits"+g.AU_Begriff()+" Bezeichnung"+g.AU_Bezeichnung("Stammtyp","Begriff")+" Stammtyp FROM Abfrage"+g.join("Begriff","Abfrage")+
            " where "+g.bit("bits",Abfrage.cstBerechtigung)+g.getReadMandanten("Begriff")+") x order by bezeichnung",true);
        for(;!TabAbfrage.eof();TabAbfrage.moveNext())
          if ((TabAbfrage.getL("bits")&Abfrage.cstBerechtigung)>0)
          {
                Vector<String> VecVisible = new Vector<String>();
                Vector<Integer> VecInvisible = new Vector<Integer>();
                VecVisible.addElement(TabAbfrage.getS("bezeichnung"));
                VecVisible.addElement(TabAbfrage.getS("Stammtyp"));
                JCOutlinerNode Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutAbfrage.getRootNode());
                VecInvisible.addElement(TabAbfrage.getInt("AIC_Abfrage"));
                VecInvisible.addElement(TabAbfrage.getInt("AIC_Begriff"));
                VecInvisible.addElement(TabAbfrage.getInt("AIC_Stammtyp"));
                Node.setUserData(VecInvisible);
                Node.setStyle(StyleAbfrage);
                TabAll.addInhalt("Aic",-TabAbfrage.getInt("AIC_Abfrage"));
                TabAll.addInhalt("Node",Node);
          }
        OutAbfrage.folderChanged(OutAbfrage.getRootNode());
//        return TabAbfrage;
      }

private String getBArt(int iBits) // Benutzer-Art
{
  return ((iBits&Global.cstMehrmals)>0 ? "MF ":"")+((iBits&Global.cstTimerBenutzer)>0 ? "Timer ":(iBits&Global.cstTerminalUser)>0 ? "Terminal ":(iBits&Global.cstAServerUser)>0 ? "AServer ":(iBits&Global.cstImport)>0 ? "Import ":(iBits&Global.cstAPI)>0 ? "API ":(iBits&Global.cstTest)>0 ? "Test ":"");
}

private String getPWArt(int iBits) // Passwort-Art
{
  int iPW_Art=iBits&Global.cstPW;
  return g.getBegriffS("Radiobutton","PW_"+(iPW_Art==Global.cstPW_LTOKEN ? "LTOKEN":iPW_Art==Global.cstPW_AZURE ? "AZURE":iPW_Art==Global.cstPW_GOOGLE ? "GOOGLE":iPW_Art==Global.cstPW_LDAP ? "LDAP":iPW_Art==Global.cstPW_EMAIL ? "EMAIL":iPW_Art==Global.cstPW_KOMBI ? "KOMBI":
    iPW_Art==Global.cstPW_MD5 ? "MD5":iPW_Art==Global.cstPW_MD5B ? "MD5B":"xx"));
}

private void fillOutliners(boolean bAll)
{
        long lClock=Static.get_ms();
	g.debugInfo("Anfang fillOutliners()");

	//TabBenutzer.clearAll();

	Tabellenspeicher TabGruppe = new Tabellenspeicher(g,new String[]{"AIC_Benutzergruppe","AIC_Eigenschaft","Node"});
	TabGruppe.sGruppe="AIC_Benutzergruppe";
	TabGruppe.sAIC="AIC_Eigenschaft";

	//VecEigenschaft = new Vector();

	/////////////
	//OutGruppe//
	/////////////
	SQL Qry = new SQL(g);
        TabAll=new Tabellenspeicher(g,new String[]{"Aic","Node"});
	((JCOutlinerFolderNode)OutBenutzergruppe.getRootNode()).removeChildren();
        ((JCOutlinerFolderNode)OutErsatz.getRootNode()).removeChildren();
        ((JCOutlinerFolderNode)OutNachricht.getRootNode()).removeChildren();
	/////////////////////
	//OutBenutzergruppe//
	/////////////////////
	//g.TabTabellenname.posInhalt("Kennung","BENUTZERGRUPPE");
	StyleBenutzergruppe=g.getStyle(g.getGif(g.TabTabellenname, "BENUTZERGRUPPE"));//(Image)g.TabTabellenname.getInhalt("Bild"));
	for(Qry.open("SELECT bg.bits,AIC_Benutzergruppe AIC,bg.aic_begriff,bg.aic_stamm,bg.Kennung,"+g.AU_BezeichnungF("Benutzergruppe","bg",1)+" Bezeichnung"+g.AU_Bezeichnung("Mandant","")+" Mandant,Mandant.AIC_Mandant,Mandant.Tod FROM Benutzergruppe bg"+g.join("Mandant","bg")+g.getReadMandanten(true,"Mandant"));!Qry.eof();Qry.moveNext())
          if (!Qry.getB("Tod"))
          {
		Vector<String> VecVisible = new Vector<String>();
		Vector<Integer> VecInvisible = new Vector<Integer>();
		VecVisible.addElement(Qry.getS("Bezeichnung"));
		VecVisible.addElement(Qry.getS("Kennung"));
		VecVisible.addElement(Qry.getS("Mandant"));
                int iBG_Bits=Qry.getI("Bits");
		VecVisible.addElement(Static.JaNein2((iBG_Bits&Global.cstHistory)>0));
		VecVisible.addElement(Static.JaNein2((iBG_Bits&Global.cstAbfrage)>0));
		VecVisible.addElement(Static.JaNein2((iBG_Bits&Global.cstNurAll)>0));
		VecVisible.addElement(Static.JaNein2((iBG_Bits&Global.cstNurWeb)>0));
		VecVisible.addElement(Static.JaNein2((iBG_Bits&Global.cstReadOnly)>0));
		VecVisible.addElement(Static.JaNein2((iBG_Bits&Global.cstBG_pers)>0));
		VecVisible.addElement(Qry.getS("AIC"));
		VecInvisible.addElement(Qry.getInt("AIC"));
		VecInvisible.addElement(Qry.getInt("AIC_Mandant"));
		//VecInvisible.addElement(new Boolean(Qry.getB("History")));
		//VecInvisible.addElement(new Boolean(Qry.getB("Abfrage")));
		//VecInvisible.addElement(new Integer((Qry.getB("History")?cstHistory:0)+(Qry.getB("Abfrage")?cstAbfrage:0)+(Qry.getB("ReadOnly")?cstReadOnly:0)));
		VecInvisible.addElement(new Integer(iBG_Bits));
                VecInvisible.addElement(null);
		VecInvisible.addElement(Qry.getInt("aic_begriff"));
		VecInvisible.addElement(Qry.getInt("aic_stamm"));
		JCOutlinerNode Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutBenutzergruppe.getRootNode());
		Node.setUserData(VecInvisible);
                Node.setStyle((iBG_Bits&Global.cstJeder)>0 ? g.setColor(StyleBenutzergruppe,g.ColEF_Bez2):StyleBenutzergruppe);
		//Node.setStyle(StyleBenutzergruppe);
                TabAll.addInhalt("Aic",Qry.getInt("AIC"));
                TabAll.addInhalt("Node",Node);
          }
	Qry.close();
        g.clockInfo2("OutBenutzergruppe",lClock);lClock=Static.get_ms();
        //////////////
        //OutAbfrage//
        //////////////
        fillAbfragen();
        g.clockInfo2("OutAbfrage",lClock);lClock=Static.get_ms();
        /////////////
        //OutErsatz//
        /////////////
        //g.TabRollen.posInhalt("Kennung","MITARBEITER");
        int iRolleMA=g.TabRollen.getAic("MITARBEITER");//g.TabRollen.out() ? 0:g.TabRollen.getI("Aic");
        JCOutlinerNodeStyle StyleErsatz=g.getStyle(g.getRolleGif(iRolleMA));
        //g.testInfo("ARBEITNEHMER="+g.TabStammtypen.getI("AIC"));
        TabErsatz=new Tabellenspeicher(g,"SELECT aic_stamm,bezeichnung"/*+g.AU_Bezeichnung("Stammtyp","Begriff")*/+" FROM stammview where aic_rolle="+iRolleMA+" and aic_mandant="+g.getMandant()+" order by Bezeichnung",true);
        for(;!TabErsatz.eof();TabErsatz.moveNext())
        {
                Vector<String> VecVisible = new Vector<String>();
                VecVisible.addElement(TabErsatz.getS("bezeichnung"));
                JCOutlinerNode Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutErsatz.getRootNode());
                Node.setUserData(TabErsatz.getInhalt("aic_stamm"));
                Node.setStyle(StyleErsatz);
        }
        OutErsatz.folderChanged(OutErsatz.getRootNode());
        g.clockInfo2("OutErsatz",lClock);lClock=Static.get_ms();
        ////////////////
        //OutNachricht//
        ////////////////
        int iPosS=g.TabStammtypen.getPos("Kennung","PM_NACHRICHTENKLASSE");
        JCOutlinerNodeStyle StyleNachricht=g.getStyle(g.getSttGif(g.TabStammtypen.getI(iPosS,"Aic")));
        //g.testInfo("PM_NACHRICHTENKLASSE="+g.TabStammtypen.getI(iPosS,"AIC"));
        TabNachricht=new Tabellenspeicher(g,"SELECT aic_stamm,bezeichnung"/*+g.AU_Bezeichnung("Stammtyp","Begriff")*/+" FROM stammview where aic_stammtyp="+g.TabStammtypen.getI(iPosS,"AIC")+" and aic_mandant="+g.getMandant()+" order by Bezeichnung",true);
        for(;!TabNachricht.eof();TabNachricht.moveNext())
        {
                Vector<String> VecVisible = new Vector<String>();
                VecVisible.addElement(TabNachricht.getS("bezeichnung"));
                JCOutlinerNode Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutNachricht.getRootNode());
                Node.setUserData(TabNachricht.getInhalt("aic_stamm"));
                Node.setStyle(StyleNachricht);
        }
        OutNachricht.folderChanged(OutNachricht.getRootNode());
        g.clockInfo2("OutNachricht",lClock);lClock=Static.get_ms();

	///////////////
	//OutBenutzer//
	///////////////
	//g.TabTabellenname.posInhalt("Kennung","BENUTZER");
     if (bAll)
     {
       VecBenutzer.removeAllElements();
       ((JCOutlinerFolderNode)OutBenutzer.getRootNode()).removeChildren();
	StyleBenutzer=g.getStyle(g.getGif(g.TabTabellenname,"BENUTZER"));//(Image)g.TabTabellenname.getInhalt("Bild"));
	int iOldParent=0;
        int iOldBG=-1;
	JCOutlinerFolderNode NodeParent = null;
        JCOutlinerFolderNode NodeBG = null;
	Vector<Integer> VecRoot = new Vector<Integer>();
        for (int i=0;i<10;i++)
          VecRoot.addElement(null);
        OutBenutzer.getRootNode().setLabel(VecRoot);
        Vector<Comparable> VecVisible;
        JCOutlinerNodeStyle StyleAbfrage=g.getStyle(g.LoadImage(g.TabBegriffgruppen.getS(g.TabBegriffgruppen.getPos("Kennung","Abfrage"),"Filename")));
	for(Qry.open("SELECT Benutzer.geloescht,use_bis,Benutzer.AIC_Benutzer BAIC,Benutzer.Kennung BKennung,"+g.AU_BezeichnungF("Benutzer")+" BBezeichnung,Benutzer.PW_Date,Benutzer.Tel,Benutzer.E_Mail,Benutzer.aic_Land,Benutzer.aic_Sprache"+
                     ",Benutzer.AIC_Benutzergruppe HBG,Z.aic_abfrage,Z.aic_stamm,Z.sta_aic_stamm,Benutzer.Bits,(select bezeichnung from stammview2 where aic_stamm=Benutzer.aic_stamm and aic_rolle is null) Stamm"+
                     ",(select austritt from stammview2 where aic_stamm=Benutzer.aic_stamm and aic_rolle="+iRolleMA+") austritt"+
                     ",(select count(aic_logging) from logging where logging.aic_benutzer=Benutzer.aic_benutzer) Anzahl"+//g.AU_Bezeichnung1("Land","Benutzer")+" Land"+
	             ",Benutzergruppe.AIC_Benutzergruppe BGAIC, Benutzergruppe.Kennung BGKEnnung,"+g.AU_BezeichnungF("Benutzergruppe")+" BGBezeichnung "+
				 "FROM Benutzer LEFT OUTER "+g.join("Benutzer_Zuordnung","Z","Benutzer","Benutzer")+" LEFT OUTER "+g.join("Benutzergruppe","Z")+" WHERE Benutzer.AIC_Mandant="+g.getMandant()+" AND (Benutzer.Geloescht IS NULL OR Benutzer.Geloescht>"+g.von()+")"+
	             (g.Def() ? "":" AND"+g.bitis("Benutzer.Bits", Global.cstNoLogin, 0))+
				 (g.Def()?"":" AND"+g.bits("Benutzer.Bits",3)+"<>1")+(g.Def()?"":" AND"+g.bits("Benutzer.Bits",3)+"<>2")+(g.SuperUser()?"":" AND"+g.bits("Benutzer.Bits",3)+"<>3")+
				 " ORDER BY BAIC,BGBezeichnung,BBezeichnung");!Qry.eof();Qry.moveNext())
	{

		if(iOldParent!=Qry.getI("BAIC"))
		{
			iOldParent=Qry.getI("BAIC");
			VecVisible = new Vector<Comparable>();
			VecVisible.addElement(Qry.getS("BBezeichnung"));
			VecVisible.addElement(Qry.getS("BKennung"));
            Sort.add(VecRoot,1);
			VecVisible.addElement(Qry.isNull("geloescht") ? new Zeit(Qry.getD("use_bis"),"dd.MM.yyyy HH:mm"):"del "+Qry.getD("geloescht"));//TODO Bemerkung mit gelöscht
            if (!Qry.isNull("geloescht")) Sort.add(VecRoot,2);
            int iBits=Qry.getI("Bits");
            VecVisible.addElement(Security.getBerechtigung(g,iBits));
            if ((iBits&Global.cstBenutzerEbene)>0) Sort.add(VecRoot,3);
            VecVisible.addElement(Static.JaNein2((iBits&Global.cstSpezial)>0));
            if ((iBits&Global.cstSpezial)>0) Sort.add(VecRoot,4);
            //int iBitGes=Global.cstBenutzerEbene+Global.cstUserManager+Global.cstHS;
            //g.fixtestInfo(iBits+"/"+iBitGes+"->"+((iBits&iBitGes)>0));
            boolean bHS=(iBits&(Global.cstBenutzerEbene+Global.cstHS))>0 && (iBits&Global.cstNoLogin)==0;// && (iBits&Global.cstTerminalUser)==0 && (iBits&Global.cstAServerUser)==0;
            //g.fixtestInfo(Qry.getS("BKennung")+":"+iBits+"->"+bHS);
            VecVisible.addElement(Static.JaNein2(bHS));
            if (bHS) Sort.add(VecRoot,5);
            VecVisible.addElement(Static.JaNein2((iBits&Global.cstVorlage)>0));
			VecVisible.addElement(Qry.getS("Stamm"));
            VecVisible.addElement(Qry.getD("austritt"));
            if (!Qry.isNull("austritt")) Sort.add(VecRoot,7);
            VecVisible.addElement(Qry.getI("Anzahl"));
            VecVisible.addElement(CboLand.getBezeichnung(Qry.getI("AIC_Land")));
//            VecVisible.addElement(Static.JaNein2((iBits&Global.cstDB)>0));
            VecVisible.addElement(iOldParent);
            VecVisible.addElement(new Zeit(Qry.getD("PW_Date"),"dd.MM.yyyy"));
            VecVisible.addElement(Qry.getS("Tel"));
            VecVisible.addElement(Qry.getS("E_Mail"));
            VecVisible.addElement(getBArt(iBits)); 
            VecVisible.addElement(getPWArt(iBits));
            VecVisible.addElement(CboSprache.getBezeichnung(Qry.getI("AIC_Sprache")));
            VecVisible.addElement(CboHBG.getBezeichnung(Qry.getI("HBG")));
            // int iPW_Art=iBits&Global.cstPW;
            // VecVisible.addElement((iBits&Global.cstTimerBenutzer)>0 ? "Timer":(iBits&Global.cstTerminalUser)>0 ? "Terminal":(iBits&Global.cstAServerUser)>0 ? "AServer":(iBits&Global.cstImport)>0 ? "Import":(iBits&Global.cstAPI)>0 ? "API":
            // (iBits&Global.cstTest)>0 ? "Test":iPW_Art==Global.cstPW_KEIN ? "keinPW":iPW_Art==Global.cstPW_AZURE ? "Azure":iPW_Art==Global.cstPW_GOOGLE ? "Google":iPW_Art==Global.cstPW_LDAP ? "LDAP":iPW_Art==Global.cstPW_EMAIL ? "E-Mail":iPW_Art==Global.cstPW_SMS ? "SMS":"");
//            VecVisible.addElement(Static.JaNein2((iBits&Global.cstVorlage)>0));
            NodeParent = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutBenutzer.getRootNode());
			NodeParent.setUserData(Qry.getInt("BAIC"));
            NodeParent.setState(BWTEnum.FOLDER_CLOSED);
			NodeParent.setStyle(StyleBenutzer);
            iOldBG=-1;
		}

		if(Qry.getI("BGAIC")!=0 && Qry.getI("BGAIC")!=iOldBG)
		{
			VecVisible = new Vector<Comparable>();
                        Vector<Integer> VecInvisible = new Vector<Integer>();
			VecVisible.addElement(Qry.getS("BGBezeichnung"));
			//VecVisible.addElement(Qry.getS("BGKennung"));
                        VecVisible.addElement(Qry.getI("aic_stamm")>0 ?SQL.getString(g,"select bezeichnung from stammview2 where aic_stamm="+Qry.getI("aic_stamm")):null);
                        VecVisible.addElement(Qry.getI("sta_aic_stamm")>0 ?SQL.getString(g,"select bezeichnung from stammview2 where aic_stamm="+Qry.getI("sta_aic_stamm")):null);
			NodeBG = new JCOutlinerFolderNode((Object)VecVisible,NodeParent);
                        VecInvisible.addElement(Qry.getInt("BGAIC"));
                        VecInvisible.addElement(Qry.getInt("aic_stamm"));
                        VecInvisible.addElement(Qry.getInt("sta_aic_stamm"));
			NodeBG.setUserData(VecInvisible);
			NodeBG.setStyle(StyleBenutzergruppe);

			/*TabBenutzer.addInhalt("AIC_BG",Qry.getInt("BGAIC"));
			TabBenutzer.addInhalt("AIC_B",Qry.getInt("BAIC"));
			TabBenutzer.addInhalt("Art","Vorhanden");
			TabBenutzer.addInhalt("Node",NodeBG);*/
		}
                iOldBG=Qry.getI("BGAIC");
                if (Qry.getI("aic_abfrage")>0)
                {
                  VecVisible = new Vector<Comparable>();
                  if (TabAbfrage.posInhalt("AIC_Abfrage",Qry.getI("aic_abfrage")))
                  {
                    VecVisible.addElement(TabAbfrage.getS("bezeichnung"));
                    VecVisible.addElement(TabAbfrage.getS("Stammtyp"));
                  }
                  else
                  {
                    VecVisible.addElement(Qry.getS("aic_abfrage"));
                    VecVisible.addElement("???");
                  }
//                  VecVisible.addElement(Qry.getInt("aic_abfrage"));
                  JCOutlinerNode Node2 = new JCOutlinerNode((Object)VecVisible,NodeBG);
                  Node2.setUserData(Qry.getInt("aic_abfrage"));
                  Node2.setStyle(StyleAbfrage);
                }
		}
		Qry.close();
		g.clockInfo2("OutBenutzer",lClock);lClock=Static.get_ms();
     }
	//if(g.Debug())
	//	TabBenutzer.showGrid("TabBenutzer");
        
	//////////////////////
	//OutBenutzergruppe2//
	//////////////////////
	//JCOutlinerFolderNode NodeParent2 = null;
	//iOldParent=0;

        /*for(Qry.open("SELECT BG.AIC_Benutzergruppe, BG.Kennung"+g.AU_Bezeichnung("Benutzergruppe","BG")+" Benutzergruppe, AIC_Fremd, T.AIC_Tabellenname,T.Kennung Tabelle,B.bits"+
	             " from berechtigung B right outer join benutzergruppe BG on"+SQL.bit("B.bits",1)+" and bg.aic_benutzergruppe=b.aic_benutzergruppe left outer "+SQL.join("tabellenname","T","B","tabellenname")+g.getReadMandanten(true,"BG")+" order by BG.AIC_Benutzergruppe,AIC_Fremd");!Qry.eof();Qry.moveNext())
	{
		if(iOldParent!=Qry.getI("AIC_Benutzergruppe"))
		{
			iOldParent=Qry.getI("AIC_Benutzergruppe");
		}
                String sTab=Qry.isNull("Tabelle") ? "":Qry.getS("Tabelle");
		boolean bBegriff = sTab.equalsIgnoreCase("BEGRIFF");
                boolean bEig=sTab.equalsIgnoreCase("EIGENSCHAFT");
                if(Qry.getI("AIC_Fremd")!=0 && (bBegriff || bEig))
		{
			if(bBegriff)
			{
				g.TabBegriffe.posInhalt("AIC",Qry.getI("AIC_Fremd"));
				g.TabBegriffgruppen.posInhalt("AIC",g.TabBegriffe.getI("Gruppe"));
			}
			else if (bEig)
			{
				g.TabEigenschaften.posInhalt("AIC",Qry.getI("AIC_Fremd"));
				g.TabTabellenname.posInhalt("AIC",Qry.getI("AIC_Tabellenname"));
			}

			JCOutlinerNodeStyle Style = g.getStyle(bBegriff?g.getImage("Begriffgruppe",g.TabBegriffgruppen.getI("AIC")):(Image)g.TabTabellenname.getInhalt("Bild"));
			VecVisible = new Vector();
			Vector VecInvisible = new Vector();
			VecVisible.addElement(bBegriff?g.TabBegriffe.getS("Bezeichnung"):bEig?g.TabEigenschaften.getS("Bezeichnung"):"");
			VecVisible.addElement(bBegriff?g.TabBegriffe.getS("Kennung"):bEig?g.TabEigenschaften.getS("Kennung"):"");
			VecVisible.addElement(bBegriff?g.TabBegriffgruppen.getS("Bezeichnung"):g.TabTabellenname.getS("Bezeichnung"));
			VecVisible.addElement((Qry.getI("bits")&1)>0?Static.sJa:Static.sNein);
			VecVisible.addElement((Qry.getI("bits")&2)>0?Static.sJa:Static.sNein);
			VecInvisible.addElement(Qry.getInt("AIC_Fremd"));
			VecInvisible.addElement(Qry.getInt("AIC_Tabellenname"));
			VecInvisible.addElement(new Integer(Qry.getI("bits")));
			JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeParent);
			Node.setUserData(VecInvisible);
			Node.setStyle(Style);
		}

	}
        //if (g.Prog())
        //  TabBenutzergruppe.showGrid();
	Qry.close();
        g.clockInfo("OutBenutzergruppe2",lClock);lClock=Static.get_ms();*/
	Qry.free();

	OutBenutzergruppe.sortByColumn(0,Sort.sortMethod);
	//OutBenutzergruppe3.sortByColumn(0,Sort.sortMethod);
	OutBenutzer.sortByColumn(0,Sort.sortMethod);

	OutBenutzergruppe.folderChanged(OutBenutzergruppe.getRootNode());
	//OutBenutzergruppe3.folderChanged(OutBenutzergruppe3.getRootNode());
	OutBenutzer.folderChanged(OutBenutzer.getRootNode());
	g.debugInfo("Ende fillOutliners()");
}

/*private void fill_OutStamm()
{
	((JCOutlinerFolderNode)OutStamm.getRootNode()).removeChildren();

	if(VecEigenschaft.size()>0)
	{
		SQL Qry = new SQL(g);

		g.TabTabellenname.posInhalt("KENNUNG","EIGENSCHAFT");
		JCOutlinerNodeStyle Style_Eig = g.getStyle((Image)g.TabTabellenname.getInhalt("Bild"));

		String s = "SELECT sv.aic_stamm, sv.bezeichnung kennung_stm, sv.aic_stammtyp, e.aic_eigenschaft, e.kennung kennung_eig "+
				   g.AU_Bezeichnung("eigenschaft","e")+" bezeichnung_eig"+g.AU_Bezeichnung("Stamm","sv")+" bezeichnung_stm "+
				   "FROM stammview2 sv, eigenschaft e WHERE e.aic_stammtyp=sv.aic_stammtyp AND aic_eigenschaft IN(";

		s=s+VecEigenschaft.elementAt(0);
		for(int i=1;i<VecEigenschaft.size();i++)
			s=s+","+VecEigenschaft.elementAt(i);
		s=s+") ORDER BY e.aic_eigenschaft";

		if(Qry.open(s))
		{
			Tabellenspeicher TabHierarchie=null;
			int iEig=0;
			String sDatentyp=null;
			int iAIC_Stammtyp = 0;
			JCOutlinerFolderNode NodeParent=null;
			//JCOutlinerNodeStyle Style=null;
			for(;!Qry.eof();Qry.moveNext())
			{
				Vector VecVisible=null;
				if(iEig!=Qry.getI("aic_eigenschaft"))
				{
					if(iEig>0 && sDatentyp.equalsIgnoreCase("Hierarchie"))
						makeStammHierarchie(TabHierarchie,iAIC_Stammtyp,iEig);

					iEig=Qry.getI("aic_eigenschaft");
					iAIC_Stammtyp = Qry.getI("aic_stammtyp");
					g.TabEigenschaften.posInhalt("AIC",iEig);
					sDatentyp = g.TabEigenschaften.getS("DATENTYP");

					if(sDatentyp.equalsIgnoreCase("Hierarchie"))
						TabHierarchie = new Tabellenspeicher(g,new String[]{"Stamm","Node"});

					VecVisible = new Vector();
					VecVisible.addElement(Static.beiLeer(Qry.getS("bezeichnung_eig"),Qry.getS("kennung_eig")));
					VecVisible.addElement(Qry.getS("kennung_eig"));
					NodeParent = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutStamm.getRootNode());
					NodeParent.setUserData(Qry.getInt("aic_eigenschaft"));
					NodeParent.setStyle(Style_Eig);

				}

				VecVisible = new Vector();
				VecVisible.addElement(Static.beiLeer(Qry.getS("bezeichnung_stm"),Qry.getS("kennung_stm")));
				VecVisible.addElement(Qry.getS("kennung_stm"));
				JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,NodeParent);
				Node.setUserData(Qry.getInt("aic_stamm"));
				Node.setStyle(g.getSttStyle(iAIC_Stammtyp));

				if(sDatentyp.equalsIgnoreCase("Hierarchie"))
				{
					TabHierarchie.addInhalt("Stamm",Qry.getInt("aic_stamm"));
					TabHierarchie.addInhalt("Node",Node);
				}
			}

			if(iEig>0 && sDatentyp.equalsIgnoreCase("Hierarchie"))
				makeStammHierarchie(TabHierarchie,iAIC_Stammtyp,iEig);
		}
		else
			Static.printError("UserManager.fill_OutStamm(): Fehler beim Zugriff auf die Datenbank in der Funktion fill_OutStamm!!!");

		Qry.free();
	}
	OutStamm.folderChanged(OutStamm.getRootNode());
}

private void makeStammHierarchie(Tabellenspeicher Tab, int iAIC_Stammtyp,int iAIC_Eigenschaft)
{
	SQL Qry = new SQL(g);

	g.TabStammtypen.posInhalt("AIC",iAIC_Stammtyp);

	while(g.TabStammtypen.posInhalt("AIC",g.TabStammtypen.getI("Darunter")) && (g.TabStammtypen.getI("bits")&g.cstEnde)==0)
	{
		if(Qry.open("select v.aic_stamm, bezeichnung, sta_aic_stamm from stammview2 v natural join stammpool where aic_eigenschaft="+iAIC_Eigenschaft+" and aic_stammtyp="+g.TabStammtypen.getI("AIC")+" and pro2_aic_protokoll is null and sta_aic_stamm "+Static.SQL_in(Tab.getVecSpalte("Stamm"))))
		{
			for(;!Qry.eof();Qry.moveNext())
			{
				if(Tab.posInhalt("Stamm",Qry.getI("sta_aic_stamm")))
				{
					Vector VecVisible = new Vector();
					VecVisible.addElement(Qry.getS("bezeichnung"));
					VecVisible.addElement(Qry.getS("bezeichnung"));
					JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)Tab.getInhalt("Node"));
					Node.setUserData(Qry.getInt("aic_stamm"));
					Node.setStyle(g.getSttStyle(g.TabStammtypen.getI("AIC")));

					Tab.addInhalt("Stamm",Qry.getInt("aic_stamm"));
					Tab.addInhalt("Node",Node);
				}
			}
		Qry.close();
		}
	}

	Qry.free();
}*/

private void checkBenutzer(JCOutlinerNode Node)
      {
        while (Node.getLevel()>1)
          Node=Node.getParent();
        Integer Int=(Integer)Node.getUserData();
        if (!VecBenutzer.contains(Int))
          VecBenutzer.addElement(Int);
        if (BtnRefresh != null) BtnRefresh.setEnabled(false);
        g.progInfo("checkBenutzer:"+VecBenutzer);
      }

@SuppressWarnings("unchecked")
private void Aendere_Stamm(boolean bHinzu)
{
        JCOutlinerNode[] NodeB = OutBenutzer.getSelectedNodes();
        for(int i=0;i<NodeB.length;i++)
        {
          checkBenutzer(NodeB[i]);
          Vector<String> VecVisible = (Vector)NodeB[i].getLabel();
          Vector<Object> VecInVisible = (Vector)NodeB[i].getUserData();
          JCOutlinerNode NodErsatz=bHinzu ? OutErsatz.getSelectedNode():null;
          JCOutlinerNode NodNachricht=bHinzu ? OutNachricht.getSelectedNode():null;
          VecVisible.setElementAt(NodErsatz==null || NodErsatz.getLevel()!=1 ? null:""+NodErsatz.getLabel(),1);
          VecVisible.setElementAt(NodNachricht==null || NodNachricht.getLevel()!=1 ? null:""+NodNachricht.getLabel(),2);
          VecInVisible.setElementAt(NodErsatz==null || NodErsatz.getLevel()!=1 ? null:NodErsatz.getUserData(),1);
          VecInVisible.setElementAt(NodNachricht==null || NodNachricht.getLevel()!=1 ? null:NodNachricht.getUserData(),2);
          //Node.setUserData(((Vector)NodeAbf[j].getUserData()).elementAt(0));
        }
        OutBenutzer.folderChanged(OutBenutzer.getRootNode());
}

@SuppressWarnings("unchecked")
private void Hinzufuegen_Abfrage()
{
  JCOutlinerNode[] NodeB = OutBenutzer.getSelectedNodes();
  JCOutlinerNode[] NodeAbf = OutAbfrage.getSelectedNodes();
  for(int i=0;i<NodeB.length;i++)
  {
    checkBenutzer(NodeB[i]);
    for(int j=0;j<NodeAbf.length;j++)
    {
      Vector VecVisible = new Vector((Vector)NodeAbf[j].getLabel());
      JCOutlinerNode Node = new JCOutlinerNode(VecVisible, (JCOutlinerFolderNode)NodeB[i]);
      Node.setUserData(((Vector)NodeAbf[j].getUserData()).elementAt(0));
    }
  }
  OutBenutzer.folderChanged(OutBenutzer.getRootNode());
}

private boolean dazu(Vector VecChildren,Object Obj)
        {
          boolean b=true;
          if (VecChildren != null)
            for (int i=0;b && i<VecChildren.size();i++)
              b=!Obj.equals(((Vector)((JCOutlinerNode)VecChildren.elementAt(i)).getUserData()).elementAt(0));
          return b;
        }

@SuppressWarnings("unchecked")
private void Hinzufuegen_Benutzergruppe()
{
	JCOutlinerNode[] NodeB = OutBenutzer.getSelectedNodes();
	JCOutlinerNode[] NodeBG = OutBenutzergruppe.getSelectedNodes();

	for(int i=0;i<NodeB.length;i++)
	{
          checkBenutzer(NodeB[i]);
          Vector VecChildren=NodeB[i].getChildren();
		for(int j=0;j<NodeBG.length;j++)
                  if(dazu(VecChildren,((Vector)NodeBG[j].getUserData()).elementAt(0)))
		{
			//g.debugInfo(NodeB[i].getUserData()+" / "+((Vector)NodeBG[j].getUserData()).elementAt(0));
			//boolean b = TabBenutzer.posInhalt(NodeB[i].getUserData(),((Vector)NodeBG[j].getUserData()).elementAt(0));
                        //if(!b||TabBenutzer.getS("Art").equals("Loeschen"))
			//{
				//g.debugInfo(b+" / "+TabBenutzer.getS("Art").equals("Loeschen"));
				Vector VecVisible = new Vector((Vector)NodeBG[j].getLabel());
				//VecVisible.removeElementAt(2);
				Vector VecVisible2 = new Vector();
				VecVisible2.addElement(VecVisible.elementAt(0));
				VecVisible2.addElement(VecVisible.elementAt(1));
                                VecVisible2.addElement(null);
				JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible2,(JCOutlinerFolderNode)NodeB[i]);
                                Vector VecInvisible2 = new Vector();
                                VecInvisible2.addElement(((Vector)NodeBG[j].getUserData()).elementAt(0));
                                VecInvisible2.addElement(null);
                                VecInvisible2.addElement(null);
				Node.setUserData(VecInvisible2);
				Node.setStyle(NodeBG[j].getStyle());

				/*if(!b || !TabBenutzer.getS("Art").equals("Loeschen"))
				{
					g.debugInfo(""+NodeB[i].getUserData());
					TabBenutzer.posInhalt("AIC_B",(Integer)NodeB[i].getUserData());
					TabBenutzer.putInhalt("AIC_BG",((Vector)NodeBG[j].getUserData()).elementAt(0),true);
					TabBenutzer.putInhalt("AIC_B",NodeB[i].getUserData(),true);
					TabBenutzer.putInhalt("Art","Neu",true);
					TabBenutzer.putInhalt("Node",Node,true);
				}
				else
				{
					TabBenutzer.setInhalt("Art","Vorhanden");
					TabBenutzer.setInhalt("Node",Node);
				}*/
			//}
		}

	}

	//if(g.Debug())
	//	TabBenutzer.showGrid("TabBenutzer");

	OutBenutzer.folderChanged(OutBenutzer.getRootNode());
}

private void Ersetzen_Benutzergruppe()
{
	JCOutlinerNode NodeB = OutBenutzer.getSelectedNode();
	g.fixtestError("ersetze "+ NodeB.getLabel()+"/"+NodeB.getUserData());
	JCOutlinerNode NodeBG = OutBenutzergruppe.getSelectedNode();
	Vector VecVisible=new Vector((Vector)NodeBG.getLabel());
	Vector VecVisible2=(Vector)NodeB.getLabel();
	VecVisible2.setElementAt(VecVisible.elementAt(0),0);
	VecVisible2.setElementAt(VecVisible.elementAt(1),1);
	((Vector)NodeB.getUserData()).setElementAt(Sort.geti(NodeBG.getUserData(),0),0);
	OutBenutzer.folderChanged(NodeB.getParent());
	checkBenutzer(NodeB);
	g.fixtestError("->"+ NodeB.getLabel()+"/"+NodeB.getUserData());
}

private void Entfernen_Benutzergruppe()
{
	JCOutlinerNode[] NodeBG = OutBenutzer.getSelectedNodes();

	for(int i=0;i<NodeBG.length;i++)
	{
		//g.debugInfo(""+i);
                checkBenutzer(NodeBG[i]);
                if (i==0)
                  OutBenutzer.selectNode(OutBenutzer.getPreviousNode(NodeBG[i]),null);
                //if (NodeBG[i].getLevel()==3)
                  NodeBG[i].getParent().removeChild(NodeBG[i]);
		/*else if(TabBenutzer.posInhalt(NodeBG[i].getParent().getUserData(),NodeBG[i].getUserData()))
		{
			g.debugInfo(""+i);
			if(TabBenutzer.getS("Art").equals("Vorhanden"))
				TabBenutzer.setInhalt("Art","Loeschen");
			else
				TabBenutzer.clearInhalt();
                        NodeBG[i].getParent().removeChild(NodeBG[i]);
		}
		else
		{
			Static.printError("UserManager.Entfernen_Benutzergruppe(): "+NodeBG[i].getParent().getUserData()+" "+NodeBG[i].getUserData());
			Static.printError("UserManager.Entfernen_Benutzergruppe(): Fehler beim positionieren in TabBenutzer!!! Darf nicht vorkommen!!!");
		}*/
	}

	//if(g.Debug())
	//	TabBenutzer.showGrid("TabBenutzer");
	OutBenutzer.folderChanged(OutBenutzer.getRootNode());
}


/*private void Hinzufuegen_Benutzergruppe3()
{
	JCOutlinerNode[] NodeSTM = OutStamm.getSelectedNodes();
	JCOutlinerNode[] NodeE = OutBenutzergruppe3.getSelectedNodes();

	for(int j=0;j<NodeE.length;j++)
	{
		for(int i=0;i<NodeSTM.length;i++)
		{
			Vector VecNodes = new Vector();
			int iAIC_BG=((Integer)NodeE[j].getParent().getUserData()).intValue();
			int iAIC_E=0;

			for(JCOutlinerNode Node=NodeSTM[i];Node.getLevel()>0;Node=Node.getParent())
			{
				if(Node.getLevel()==1)
					iAIC_E=((Integer)Node.getUserData()).intValue();
				else
					VecNodes.addElement(Node);
			}

			for(JCOutlinerNode Node=OutStamm.getNextNode(NodeSTM[i]);Node!=null && Node.getLevel()>NodeSTM[i].getLevel();Node=OutStamm.getNextNode(Node))
				VecNodes.addElement(Node);

			for(int x=0;x<VecNodes.size();x++)
			{
				JCOutlinerNode NodeSTM2 = (JCOutlinerNode)VecNodes.elementAt(x);

				if(((Integer)NodeE[j].getUserData()).intValue()==iAIC_E && !TabStamm.posInhalt("Tab",iAIC_BG+"."+iAIC_E+"."+NodeSTM2.getUserData())||TabStamm.getS("Art").equals("Loeschen"))
				{
					JCOutlinerNode Node = new JCOutlinerNode(new Vector((Vector)NodeSTM2.getLabel()),(JCOutlinerFolderNode)NodeE[j]);
					Node.setUserData(new Integer(((Integer)NodeSTM2.getUserData()).intValue()));
					Node.setStyle(NodeSTM2.getStyle());

					if(!TabStamm.getS("Art").equals("Loeschen"))
					{
						TabStamm.addInhalt("Tab",NodeE[j].getParent().getUserData()+"."+NodeE[j].getUserData()+"."+NodeSTM2.getUserData());
						TabStamm.addInhalt("AIC_BG",new Integer(iAIC_BG));
						TabStamm.addInhalt("AIC_Eig",new Integer(iAIC_E));
						TabStamm.addInhalt("AIC_Stm",NodeSTM2.getUserData());
						TabStamm.addInhalt("Art","Neu");
						TabStamm.addInhalt("Node",Node);
					}
					else
						TabStamm.setInhalt("Art","Vorhanden");
				}
			}
		}
	}

	if(g.Debug())
		TabStamm.showGrid("TabStamm");
	OutBenutzergruppe3.folderChanged((JCOutlinerFolderNode)OutBenutzergruppe3.getRootNode());

}

private void Entfernen_Benutzergruppe3()
{
	JCOutlinerNode[] NodeSTM = OutBenutzergruppe3.getSelectedNodes();

	for(int i=0;i<NodeSTM.length;i++)
	{
		if(TabStamm.posInhalt("Tab",NodeSTM[i].getParent().getParent().getUserData()+"."+NodeSTM[i].getParent().getUserData()+"."+NodeSTM[i].getUserData()))
		{
			if(TabStamm.getS("Art").equals("Neu"))
				TabStamm.clearInhalt();
			else
				TabStamm.setInhalt("Art","Loeschen");

			NodeSTM[i].getParent().removeChild(NodeSTM[i]);
		}
		else
		{
			Static.printError("UserManager.Entfernen_Benutzergruppe3(): "+NodeSTM[i].getParent().getParent().getUserData()+"."+NodeSTM[i].getParent().getUserData()+"."+NodeSTM[i].getUserData());
			Static.printError("UserManager.Entfernen_Benutzergruppe3(): Fehler beim positionieren in TabStamm!!! Darf nicht vorkommen!!!");
		}
	}

	if(g.Debug())
		TabStamm.showGrid("TabStamm");
	OutBenutzergruppe3.folderChanged(OutBenutzergruppe3.getRootNode());
}*/

private void checkSave()
{
  boolean bSave=VecBenutzer.size()>0;
  if (bSave)
   if (new Message(Message.YES_NO_OPTION,(JFrame)this.thisFrame,g).showDialog("Speichern")==Message.YES_OPTION)
     Save();
   else
   {
     VecBenutzer.removeAllElements();
     iMandantOld=-1;
     if (BtnRefresh != null) BtnRefresh.setEnabled(true);
   }

}

private void Save()
{
	SQL Qry = new SQL(g);
        if (VecBenutzer.size()>0)
        {
          Vector VecC = OutBenutzer.getRootNode().getChildren();
          for(int i=0;VecC!=null && i<VecC.size();i++)
          {
            JCOutlinerFolderNode NodC=(JCOutlinerFolderNode)VecC.elementAt(i);
            Integer Int=(Integer)NodC.getUserData();
            if (VecBenutzer.contains(Int))
            {
              //g.progInfo("Save Benutzer "+Int);
              Qry.exec("delete from Benutzer_Zuordnung where aic_benutzer=" + Int);
              Vector VecB = NodC.getChildren();
              g.SaveDefVerlauf(getBegriff(),"Benutzer_Zuordnung von "+((Vector)NodC.getLabel()).elementAt(1)+" geändert");             
              for(int i2=0;VecB!=null && i2<VecB.size();i2++)
              {
            	JCOutlinerFolderNode NodB=(JCOutlinerFolderNode)VecB.elementAt(i2);
            	Vector VecInvisible=(Vector)NodB.getUserData();
                Integer iBG=(Integer)VecInvisible.elementAt(0);//((Integer)NodB.getUserData()).intValue();
                Integer iErsatz=(Integer)VecInvisible.elementAt(1);
                Integer iNRK=(Integer)VecInvisible.elementAt(2);
                Vector VecA = NodB.getChildren();
                if (VecA==null || VecA.size()==0)
                {
                  Qry.add("AIC_Benutzer",Int);
                  Qry.add("AIC_Benutzergruppe",iBG);
                  Qry.add("AIC_Stamm",iErsatz);
                  Qry.add("STA_AIC_Stamm",iNRK);
                  Qry.add("Nr",0);
                  Qry.insert("Benutzer_Zuordnung",false);
                }
                else
                  for(int i3=0;i3<VecA.size();i3++)
                  {
                    int iAbf = ((Integer)((JCOutlinerNode)VecA.elementAt(i3)).getUserData()).intValue();
                    Qry.add("AIC_Benutzer", Int);
                    Qry.add("AIC_Benutzergruppe", iBG);
                    Qry.add("AIC_Stamm",iErsatz);
                    Qry.add("STA_AIC_Stamm",iNRK);
                    Qry.add("Nr", i3 + 1);
                    Qry.add("AIC_Abfrage", iAbf);
                    Qry.insert("Benutzer_Zuordnung", false);
                  }
              }
            }
          }
        }
        VecBenutzer.removeAllElements();
	/*for(TabBenutzer.moveFirst();!TabBenutzer.eof();TabBenutzer.moveNext())
	{
		String sArt = TabBenutzer.getS("Art");
		if(!sArt.equals("Vorhanden"))
		{
			Qry.add("AIC_Benutzer",TabBenutzer.getI("AIC_B"));
			Qry.add("AIC_Benutzergruppe",TabBenutzer.getI("AIC_BG"));
			if(sArt.equals("Neu"))
				Qry.insert("Benutzer_Zuordnung",false);
			else
			{
				Qry.delete("Benutzer_Zuordnung");
				Qry.clear();
			}
			TabBenutzer.setInhalt("Art","Vorhanden");
		}
	}*/

      Qry.free();
//      g.sendWebDB("refreshDB",null,thisJFrame());
	if(g.Debug())
	{
		//TabBenutzer.showGrid("TabBenutzer");
		//TabBenutzergruppe.showGrid("TabBenutzergruppe");
	}
	  if (BtnRefresh != null) BtnRefresh.setEnabled(true);
}

@SuppressWarnings("unchecked")
private void Loeschen_Benutzer(boolean b2000)
{
  JCOutlinerNode[] NodeB = OutBenutzer.getSelectedNodes();
  String sSQL = " WHERE AIC_Benutzer in(";
  if(NodeB.length>0)
          sSQL = sSQL+NodeB[0].getUserData();
  for(int i=1;i<NodeB.length;i++)
          sSQL = sSQL+","+NodeB[0].getUserData();
  sSQL = sSQL+")";
  String sKennung=SQL.getString(g,"select kennung from benutzer"+sSQL);
  String[] s = new String[] {g.getBegriffS("Show","Benutzer")+" "+sKennung};
	if(new Message(Message.YES_NO_OPTION,(JFrame)this.thisFrame,g).showDialog("Loeschen",s)==Message.YES_OPTION)
	{
		SQL Qry = new SQL(g);
		//sSQL = "SELECT DISTINCT AIC_Benutzer FROM logging "+sSQL;
                Vector<Integer> VecBL=SQL.getVector("SELECT DISTINCT AIC_Benutzer FROM logging "+sSQL,g);
                Vector<Integer> VecBP=SQL.getVector("SELECT DISTINCT AIC_Benutzer FROM Parameter "+sSQL,g);
		//Tabellenspeicher TabLoeschen = new Tabellenspeicher(g,sSQL,true);
		//if(g.Debug())
		//	TabLoeschen.showGrid("TabLoeschen");
		for(int i=0;i<NodeB.length;i++)
		{
            int iB=Sort.geti(NodeB[i].getUserData());
            g.SaveDefVerlauf(getBegriff(),"B"+iB+": Benutzer "+sKennung+" entfernt");
            if(VecBL.contains(iB) || VecBP.contains(iB))
			{			
				LoescheBenutzer(g,iB,getBegriff(),b2000);
				((Vector)NodeB[i].getLabel()).setElementAt(new Zeit(new java.util.Date(),"yyyy-MM-dd"),2);
			}
			else
			{
				//Vector VecChildren = ((JCOutlinerFolderNode)NodeB[i]).getChildren();
				//for(int j=0;VecChildren!=null && j<VecChildren.size();j++)
				//	TabBenutzer.clearInhalt("Node",VecChildren.elementAt(j));

				Qry.add("AIC_Benutzer",((Integer)NodeB[i].getUserData()).intValue());
				Qry.delete("Benutzer_Zuordnung");
                                //Qry.delete("Parameter");
				Qry.delete("Benutzer");
				Qry.clear();

				NodeB[i].getParent().removeChild(NodeB[i]);
			}
			
		}
        Qry.free();
		OutBenutzer.folderChanged(OutBenutzer.getRootNode());
	}
}

public static void LoescheBenutzer(Global g,int iB,int iDef,boolean b2000)
{
	String sKennung=SQL.getString(g,"select kennung from benutzer where aic_benutzer="+iB);
	if (!Static.Leer(sKennung))
	{
		boolean bDB=(SQL.getInteger(g, "select bits from benutzer where aic_benutzer=?", 0, ""+iB)&g.cstDB)>0;	
		SQL Qry = new SQL(g);
    if (b2000)
      Qry.add("geloescht",new DateWOD(2000,1, 1).toTimestamp());
    else
		  Qry.addnow("geloescht");
		//Qry.add0("use_bis", 0);
		Qry.add("kennung","-"+sKennung);
	    Qry.add("aic_logging",g.getLog());
		Qry.update("Benutzer",iB);
		Qry.free();
		if (bDB)
			DB_User_Del(g,sKennung,null);//,getHost(g));
		g.diskInfo("Benutzer "+sKennung+" entfernt");
		g.SaveDefVerlauf(iDef>0 ? iDef:g.getBegriffAicS("Button", "Delete User"),"B"+iB+": Benutzer "+sKennung+" entfernt");
//		g.SaveVerlauf(g.getBegriffAicS("Button", "Delete User"), 0, 0, 0, "Benutzer "+sKennung+" entfernt");
	}
}

public static void showHistory(Global g,int iB,String sUser,Window Dlg,AUOutliner Gid)
{
	if (Gid!=null)
	{
		JCOutlinerNode NodeB = Gid.getSelectedNode();
		if (NodeB==null)
				return;
		iB=Sort.geti(NodeB.getUserData());
		sUser=Sort.gets(NodeB.getLabel(),2);
	}
	new Tabellenspeicher(g,"select (select b.kennung from benutzer b join logging l on b.aic_benutzer=l.aic_benutzer where l.aic_logging=v.aic_logging) wer,v.Timestamp wann,(select defbezeichnung from begriff where aic_begriff=v.aic_begriff) wo"+
            ",v.Tat was from defverlauf v where v.tat like 'B"+iB+":%'",true).showGrid("Benutzer "+sUser,Dlg);
}

private String getEMailPara()
{
	g.fixInfoT("getEMailPara");
	Parameter Para=new Parameter(g,"SMTP");
	String s=Para.getMParameter("TLS",true);
	Para.free();
	return s;
}

public static void setParaSMTP(Global g,JFrame Frm)
{
	Parameter Para=new Parameter(g,"SMTP");
	String s=Para.getMParameter("TLS",true);
	boolean b=Para.bGefunden;
	JDialog Dlg = new JDialog(Frm, g.getBegriffS("Dialog", "ParaSMTP"), true);
    Dlg.getContentPane().setLayout(new BorderLayout(2, 2));
    JLabel LblMandant=new JLabel(g.getMandant(0, null));
    SpinBox EdtPort = new SpinBox();
    EdtPort.setValue(b ? Para.int1:25);
    Text EdtServer=new Text("",40);
    Text EdtSend=new Text("",50);
    Text EdtDest=new Text("",50);
    AUPasswort EdtPW=new AUPasswort(30);
    Text EdtName=new Text("",50);
    if (!Static.Leer(s))
    {
      // g.fixtestError("s="+s);
      String sAry[]=s.split("&");
      int i=sAry.length;
      for (int i2=0;i2<i;i2++)
        g.fixtestError("SMTP"+i2+":"+sAry[i2]);
      if (i>0)
        EdtServer.setText(sAry[0]);
      if (i>1)
        EdtSend.setText(sAry[1]);
      if (i>2)
      {
        EdtDest.setText(sAry[2]);
        if (i==5)
        {
          EdtPW.setValue(g.getPassword(sAry[3],Global.PWR,0));
          EdtName.setText(sAry[4]);
        }
      }
    }
    AUCheckBox CbxStartTLS=g.getCheckbox("StartTLS");
    if (b && (Para.int2&4)>0)
    	CbxStartTLS.setSelected(true);
    JPanel PnlRad=new JPanel(new GridLayout(1,0,2,2));
     JRadioButton RadKein=g.getRadiobutton("kein");
     JRadioButton Rad1=g.getRadiobutton("TLSv1.1");
     JRadioButton Rad2=g.getRadiobutton("TLSv1.2");
     JRadioButton Rad3=g.getRadiobutton("TLSv1.3");
     ButtonGroup RadTLS=new ButtonGroup();
     RadTLS.add(RadKein);
     RadTLS.add(Rad1);
     RadTLS.add(Rad2);
     RadTLS.add(Rad3);
     PnlRad.add(RadKein);
     if (g.Def())
    	 PnlRad.add(Rad1);
     PnlRad.add(Rad2);
     if (b)
     {
    	 int iTLS=Para.int2&3;
    	 if (iTLS==1)
    		 Rad1.setSelected(true);
    	 else if (iTLS==2)
    		 Rad2.setSelected(true);
    	 else if (iTLS==3)
    		 Rad3.setSelected(true);
    	 else
    		 RadKein.setSelected(true);
     }
     else
    	 RadKein.setSelected(true);

    JPanel PnlN=new JPanel(new BorderLayout(2,2));
    JPanel Pnl = new JPanel(new GridLayout(0,1,0,2));
      g.addLabel(Pnl,"Mandant",LblMandant);
      g.addLabel(Pnl,"Host",EdtServer);
      g.addLabel(Pnl,"Port",EdtPort);
      g.addLabel(Pnl,"Sender",EdtSend);
      g.addLabel(Pnl,"Auth-Name",EdtName);
      g.addLabel(Pnl,"Auth-PW",EdtPW);
      g.addLabel(Pnl,"Empfaenger",EdtDest);
      Pnl.add(new JLabel());
      g.addLabel(Pnl,"TLS",PnlRad);
    PnlN.add("West",Pnl);
    Pnl = new JPanel(new GridLayout(0,1,0,2));
      g.addN(LblMandant,Pnl);
      g.addN(EdtServer,Pnl);
      g.addN(EdtPort,Pnl);
      g.addN(EdtSend,Pnl);
      g.addN(EdtName,Pnl);
      g.addN(EdtPW,Pnl);
      g.addN(EdtDest,Pnl);
      g.addN(CbxStartTLS,Pnl);
      g.addN(PnlRad,Pnl);
    PnlN.add("Center",Pnl);
    JScrollPane Scroll=new JScrollPane(PnlN);
      Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
    Dlg.getContentPane().add("North",Scroll);

    ActionListener ALD=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s = ev.getActionCommand();
        if (s.equals("Ok"))
        {
        	int iBits=(Rad1.isSelected() ? 1:Rad2.isSelected() ? 2:Rad3.isSelected() ? 3:0)+(CbxStartTLS.isSelected() ? 4:0);
        	Para.setMParameter("TLS", EdtServer.getText()+"&"+EdtSend.getText()+"&"+EdtDest.getText()+"&"+(EdtPW.isNull() ? "":g.PasswordConvert(EdtPW.getValue(),Global.PWR,0))+"&"+EdtName.getText(), EdtPort.getIntValue(), iBits, 0, 0, g.getMandant());
        	Para.free();
        	Dlg.dispose();
        }
	      else if (s.equals("Abbruch"))
	      {
	    	  Para.free();
	        Dlg.dispose();
	      }
        else if (s.equals("Test"))
        {
          int iBits=(Rad1.isSelected() ? 1:Rad2.isSelected() ? 2:Rad3.isSelected() ? 3:0)+(CbxStartTLS.isSelected() ? 4:0);
        	Para.setMParameter("TLS", EdtServer.getText()+"&"+EdtSend.getText()+"&"+EdtDest.getText()+"&"+(EdtPW.isNull() ? "":g.PasswordConvert(EdtPW.getValue(),Global.PWR,0))+"&"+EdtName.getText(), EdtPort.getIntValue(), iBits, 0, 0, g.getMandant());
          SMTP2 smtp=new SMTP2(g, null);
          smtp.sendTo(EdtDest.getText());
          Vector<String> Vec=g.getTranslate("Message", "2FA", new String[] {getCode()}, false);
          smtp.subject(Sort.gets(Vec, 0)/* "2-Faktorcode" */);
          smtp.sendText(Sort.gets(Vec, 1)/* "Code von ALL: "+sCode */);
          smtp.quit();
          if (smtp.fehler())
            new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog("E-Mail_fehlerhaft");
          else
            new Message(Message.INFORMATION_MESSAGE,Dlg,g,10).showDialog("Test_E-Mail_versandt");
          // smtp.subject("Test");
          // smtp.sendText("Testcode von ALL:"+getCode());
        }
        else if (s.equals("Code"))
        { 
          ((JButton)ev.getSource()).setText(getCode());
        }
      }
    };
    Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
    JButton BtnTest = g.getButton("Test","Test",ALD);
    JButton BtnOk = g.getButton("Ok","Ok",ALD);
    JButton BtnAbbruch = g.getButton("Abbruch","Abbruch",ALD);
    Dlg.getRootPane().setDefaultButton(BtnOk);
    if (g.Prog())
      Pnl.add(g.getButton("Code","Code",ALD));
    Pnl.add(BtnTest);
    Pnl.add(BtnOk);
    Pnl.add(BtnAbbruch);
    Dlg.getContentPane().add("South", Pnl);
    Dlg.pack();
    Static.centerComponent(Dlg,Frm);
    Dlg.setVisible(true);
}

public static String getCode()
{
  String s="";
  for(int i=0;i<6;i++)
    s+=""+(int)Math.floor(Math.random()*10);
    return s;
}


@SuppressWarnings("unchecked")
private void Wiederherstellen_Benutzer()
{
	if(new Message(Message.YES_NO_OPTION,(JFrame)this.thisFrame,g).showDialog("Undelete User")==Message.YES_OPTION)
	{
		SQL Qry = new SQL(g);
		JCOutlinerNode[] NodeB = OutBenutzer.getSelectedNodes();
		for(int i=0;i<NodeB.length;i++)
		{
			Vector<Object> VecVisible = (Vector)NodeB[i].getLabel();
			String sKennung=Sort.gets(VecVisible, 1);
			if (sKennung.startsWith("-"))
				sKennung=sKennung.substring(1);
			if (SQL.exists(g, "select aic_benutzer from benutzer where kennung='"+sKennung+"'"))
				new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("KennungVorhanden",new String[] {sKennung});
			else if(VecVisible.elementAt(2)!=null)
			{
				int iAIC_Benutzer=((Integer)NodeB[i].getUserData()).intValue();
				Qry.add("geloescht","");
				Qry.add("Kennung",sKennung);
                Qry.add("aic_logging",g.getLog());
				Qry.update("Benutzer",iAIC_Benutzer);
				int iBits=SQL.getInteger(g, "select bits from benutzer where aic_benutzer=?", 0, ""+iAIC_Benutzer);
				boolean bDB=(iBits&g.cstDB)>0;
				///TODO: Host gehört noch ermittelt
				if (bDB)
					setUserOnDB(g,sKennung,getHost(iAIC_Benutzer),SQL.getString(g,"select passwort from benutzer where aic_benutzer="+iAIC_Benutzer),getArt(iBits));
				g.SaveDefVerlauf(g.getBegriffAicS("Button", "Undelete"),"B"+iAIC_Benutzer+": Benutzer "+sKennung+" wiederhergestellt");
				VecVisible.setElementAt(sKennung,1);
				VecVisible.setElementAt(null,2);
			}
		}

		OutBenutzer.folderChanged(OutBenutzer.getRootNode());
	}
}

private void Loeschen_Benutzergruppe()
{
  JCOutlinerNode[] NodeBG = OutBenutzergruppe.getSelectedNodes();
  String[] s = new String[] {g.getBegriffS("Show","Benutzergruppe")+" "+((Vector)NodeBG[0].getLabel()).elementAt(0)};
	if(new Message(Message.YES_NO_OPTION,(JFrame)this.thisFrame,g).showDialog("Loeschen",s)==Message.YES_OPTION)
	{

		for(int i=0;i<NodeBG.length;i++)
		{
			if(((Integer)((Vector)NodeBG[i].getUserData()).elementAt(1)).intValue()==g.getMandant())
			{
                          int iBG=((Integer)((Vector)NodeBG[i].getUserData()).elementAt(0)).intValue();
                          SQL Qry=new SQL(g);
                          String s2=Qry.list("kennung","benutzer"+g.join2("benutzer_zuordnung","benutzer")+" where benutzer_zuordnung.aic_benutzergruppe="+iBG);
                          String s3=Qry.list("kennung","benutzer where benutzer.aic_benutzergruppe="+iBG);
                                        //String s=(s2.equals(" ")?"":"\nFormulare:"+s2);
                          if(s2.equals(" ") && s3.equals(" ") || new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Trotzdem_Loeschen", new String[] {"Benutzergruppe "+(String)((Vector)NodeBG[i].getLabel()).elementAt(0),"\nBenutzer:" + s2+"\nals Hauptbenutzergruppe: "+s3})==Message.YES_OPTION)
                          {
				g.exec("update Abfrage set aic_benutzergruppe=null where aic_benutzergruppe="+iBG);
                                g.exec("delete from benutzer_zuordnung where aic_benutzergruppe="+iBG);
                                g.exec("delete from berechtigung where aic_benutzergruppe="+iBG);
                                g.exec("delete from abschluss_zuordnung where aic_benutzergruppe="+iBG);
				Qry.add("AIC_Benutzergruppe",iBG);
				Qry.delete("Berechtigung");
				if (Qry.delete("Benutzergruppe"))
                                  NodeBG[i].getParent().removeChild(NodeBG[i]);
                          }
                          Qry.free();
                        }
			else
				new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("Falscher Mandant");
		}

		OutBenutzer.folderChanged(OutBenutzer.getRootNode());
		OutBenutzergruppe.folderChanged(OutBenutzergruppe.getRootNode());
		//OutBenutzergruppe3.folderChanged(OutBenutzergruppe3.getRootNode());
	}
}

private void EnableButtons()
{
	JCOutlinerNode[] NodeBG = OutBenutzergruppe.getSelectedNodes();
        JCOutlinerNode[] NodeAbf = OutAbfrage.getSelectedNodes();
	JCOutlinerNode[] NodeB = OutBenutzer.getSelectedNodes();

	//BtnNewUser.setEnabled(true);//NodeB!=null && NodeB.length==1 && NodeB[0].getLevel()==1);
	boolean bUserSelected = NodeB!=null && NodeB.length==1 && NodeB[0].getLevel()==1;
	Vector<Comparable> Vec=bUserSelected ? (Vector<Comparable>)NodeB[0].getLabel():null;
	boolean bUserDeleted = bUserSelected && Vec.elementAt(2)!=null && !(Vec.elementAt(2) instanceof Zeit); //TODO nur bei gelöscht
    BtnEditUser.setEnabled(bUserSelected && !bUserDeleted);
	BtnCopyUser.setEnabled(bUserSelected && !bUserDeleted);
	BtnDeleteUser.setEnabled(bUserSelected && !bUserDeleted);//NodeB!=null && NodeB.length>0 && NodeB[0].getLevel()==1);
	BtnUndeleteUser.setEnabled(bUserSelected && bUserDeleted);
	BtnNewUserGroup.setEnabled(true);//NodeBG!=null && NodeBG.length==1 && NodeBG[0].getLevel()==1);
	boolean bUserGroupSelected = NodeBG!=null && NodeBG.length==1 && NodeBG[0].getLevel()==1 && ((Integer)((Vector)NodeBG[0].getUserData()).elementAt(1)).intValue()==g.getMandant();
        boolean bUG_use= NodeBG!=null && NodeBG.length==1 && NodeBG[0].getLevel()==1 && (((Integer)((Vector)NodeBG[0].getUserData()).elementAt(2)).intValue()&Global.cstJeder)==0;
	BtnCopyUserGroup.setEnabled(NodeBG!=null && NodeBG.length==1 && NodeBG[0].getLevel()==1);
	BtnEditUserGroup.setEnabled(bUserGroupSelected);
	BtnDeleteUserGroup.setEnabled(bUserGroupSelected);//NodeBG!=null && NodeBG.length>0 && NodeBG[0].getLevel()==1);

	BtnUGHinzufuegen.setEnabled(NodeB!=null && NodeB.length>0 && NodeBG!=null && NodeBG.length>0 && NodeBG[0].getLevel()==1 && NodeB[0].getLevel()==1 && bUG_use);
	BtnUGErsetzen.setEnabled(NodeB!=null && NodeB.length>0 && NodeBG!=null && NodeBG.length>0 && NodeBG[0].getLevel()==1 && NodeB[0].getLevel()==2);
    BtnAbfHinzufuegen.setEnabled(NodeB!=null && NodeB.length>0 && NodeAbf!=null && NodeAbf.length>0 && NodeAbf[0].getLevel()==1 && NodeB[0].getLevel()==2);
    BtnStHinzufuegen.setEnabled(NodeB!=null && NodeB.length>0 && NodeB[0].getLevel()==2);
        //  (NodeBG!=null && NodeBG.length>0 && NodeBG[0].getLevel()==1 && NodeB[0].getLevel()==1 ||
        //   NodeAbf!=null && NodeAbf.length>0 && NodeAbf[0].getLevel()==1 && NodeB[0].getLevel()==2));
	BtnUGEntfernen.setEnabled(NodeB!=null && NodeB.length>0 && NodeB[0].getLevel()==2);
        BtnAbfEntfernen.setEnabled(NodeB!=null && NodeB.length>0 && NodeB[0].getLevel()==3);
        BtnStEntfernen.setEnabled(NodeB!=null && NodeB.length>0 && NodeB[0].getLevel()==2);
        //BtnErsetzen.setEnabled(NodeB!=null && NodeB.length>0 && NodeB[0].getLevel()==2);
}

private void ImportiereUser()
{
	if (!g.Def())
	{
		g.fixInfo("Importieren nur mit Def erlaubt");
		new Message(Message.WARNING_MESSAGE,thisJFrame(),g).showDialog("keineBerechtigung");
		return;
	}
	//g.fixtestInfo("importiere ...");
	JFileChooser chooser = new JFileChooser();
    SimpleFileFilter filefilter = new SimpleFileFilter(new String[] {"csv"},"Textdatei (*.csv)");
    chooser.setMultiSelectionEnabled(false);
    chooser.addChoosableFileFilter(filefilter);
    chooser.setCurrentDirectory(new java.io.File("C:\\"));
    chooser.setFileFilter(filefilter);
    int option = chooser.showOpenDialog(null);
    if(option == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile()!=null)
    {
            String sFileName=chooser.getSelectedFile().getPath();
            Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"aic_benutzer","Benutzer","Kennung","Passwort","Bits","aic_stamm","MA"});
            Tab.readFile(';',sFileName,true);
            for (Tab.moveFirst();!Tab.out();Tab.moveNext())
            	if (!Tab.isNull("MA"))
            		Tab.setInhalt("aic_stamm", SQL.getInteger(g, "select aic_stamm from stammview2 where aic_rolle="+g.iRolMA+" and bezeichnung='"+Tab.getS("MA")+"'"));
            //Tab.showGrid();
            int iAnzahl=0;
            SQL Qry=new SQL(g);
            if (new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,thisJFrame(),g).showDialog("importieren",Tab) == Message.YES_OPTION)
            	for (Tab.moveFirst();!Tab.out();Tab.moveNext())
            	{
            		Qry.add("passwort", Tab.getS("passwort"));
            		Qry.addnow("PW_Date");
            		Qry.add("Bits", Tab.getI("Bits"));
            		Qry.add("aic_sprache",g.iSpStd);
            		Qry.add("aic_land",1);
            		Qry.add("aic_logging", g.getLog());
            		if (Tab.getI("aic_stamm")>0)
            			Qry.add("aic_stamm", Tab.getI("aic_stamm"));
            		int iUser=SQL.getInteger(g, "select aic_benutzer from benutzer where geloescht is null and kennung='"+Tab.getS("kennung")+"' and aic_mandant="+g.getMandant());
            		if (iUser>0)
            			Qry.update("Benutzer",iUser);
            		else
            		{
            			Qry.add("kennung", Tab.getS("kennung"));
            			Qry.add("aic_mandant", g.getMandant());
            			Qry.addnow("seit");
            			iUser=Qry.insert("Benutzer", true);
            			iAnzahl++;
            		}
            		int iPWx=Tab.getI("Bits")&Global.cstPW;
            		if (iPWx==Global.cstPW_MD5 || iPWx==Global.cstPW_MD5B)
            		{
                        String sPWx=g.PasswordConvert(Static.sDefaultPW, iPWx==Global.cstPW_MD5 ? g.PWH:g.PWVH, iUser);
                        Qry.add("Passwort",sPWx);
                        Qry.update("Benutzer",iUser);
                      }
            		g.setBezeichnung("", Tab.getS("Benutzer"), g.TabTabellenname.getAic("BENUTZER"), iUser, 1);
            	}
            g.fixtestInfo(iAnzahl+" Benutzer importiert");
            Qry.free();
    }
}


// add your data members here

private static final int USER=0; // normale User ohne spezielle Berechtigung
private static final int FULL=1; // Volle Berechtigung: Def, AServer 
private static final int ADMIN=2;// Grant-Berechtigung: Usermanager (außer Timer)
private static final int TERM=3; // Terminal: nur auf Zwischenspeicher schreiben

private Global g;

//private JButton BtnNewUser;
private JButton BtnNewUserGroup;
private JButton BtnDeleteUser;
private JButton BtnUndeleteUser;
private JButton BtnDeleteUserGroup;
private JButton BtnEditUser;
private JButton BtnEditUserGroup;
private JButton BtnCopyUserGroup;
private JButton BtnCopyUser;
//private JButton BtnOk;
//private JButton BtnAbbruch;
//private JButton BtnTest;
//private JButton BtnAbfrage;
private JButton BtnRefresh;
//private JButton BtnShow;
private JButton BtnSpeichern;
//private JButton BtnExport;
private JButton BtnBeenden;
private JButton BtnUGHinzufuegen;
private JButton BtnUGErsetzen;
private JButton BtnAbfHinzufuegen;
private JButton BtnStHinzufuegen;
private JButton BtnUGEntfernen;
private JButton BtnAbfEntfernen;
private JButton BtnStEntfernen;

//private JButton BtnLDAP;
private JButton BtnUserOk;
private JButton BtnUserAbbruch;
private JButton BtnUserGroupOk;
private JButton BtnUserGroupAbbruch;
//private JButton BtnAdminPassword;
//private JButton BtnCleanComputer;
//private JButton BtnLogging;
private JButton BtnBerechtigung;
private JButton BtnPeriodensperre;

//private JCheckBox CbxAendern;
//private JCheckBox CbxAnlegen;
//private JCheckBox CbxLoeschen;

private Tabellenspeicher TabAll;
private Tabellenspeicher TabAbfrage;
private Tabellenspeicher TabErsatz;
private Tabellenspeicher TabNachricht;
private AUOutliner OutBenutzer = new AUOutliner(new JCOutlinerFolderNode("Root Benutzer"));
private AUOutliner OutBenutzergruppe = new AUOutliner(new JCOutlinerFolderNode("Root Benutzergruppe"));
private AUOutliner OutAbfrage = new AUOutliner(new JCOutlinerFolderNode("Root Abfrage"));
private AUOutliner OutErsatz = new AUOutliner(new JCOutlinerFolderNode(Static.sKein));
private AUOutliner OutNachricht = new AUOutliner(new JCOutlinerFolderNode(Static.sKein));

private Vector<Integer> VecBenutzer;
private JCOutlinerNodeStyle StyleBenutzer;
private JCOutlinerNodeStyle StyleBenutzergruppe;
//private JCOutlinerNodeStyle StyleAbfrage;

private boolean bNewUser = false;
private int iCopyUser=0;
private boolean bNewUserGroup = false;

private JDialog FrmNewUser;
private Text TxtBBezeichnung = new Text("",100);
private Text TxtBKennung = new Text("",40,Text.KENNUNG2);
private Text TxtBHost = new Text("",60);
private Text TxtTel = new Text("",98);
private Text TxtEMail = new Text("",98);
private Datum EdtBis = null;
private AUPasswort TxtNeuPasswort = new AUPasswort();
private AUPasswort TxtBestPasswort = new AUPasswort();
private ComboSort CboSprache;
private ComboSort CboLand;
private StammEingabe CboStamm;
private ComboSort CboHBG;
private JRadioButton RadProg;
private JRadioButton RadDef;
private JRadioButton RadVerw;
private JRadioButton RadSuper;
private AUCheckBox CbxSpezial;
private AUCheckBox CbxMehrmals;
//private AUCheckBox CbxSoftTerm;
private AUCheckBox CbxTimer;
private AUCheckBox CbxAServer;
private AUCheckBox CbxTerminal;
private AUCheckBox CbxAutoFC;
private AUCheckBox CbxOC;
private AUCheckBox CbxVorlage;
private AUCheckBox CbxAPI;
private AUCheckBox CbxTest;
private AUCheckBox CbxNoMobile;
private AUCheckBox CbxNoAktiv;
private AUCheckBox Cbx2FA;
private AUCheckBox CbxImport;
private AUCheckBox CbxHS;
private AUCheckBox CbxOhne;
private AUCheckBox CbxDB;
private JRadioButton RadPW_MD5;
private JRadioButton RadPW_AZURE;
private JRadioButton RadPW_MD5B;
private JRadioButton RadPW_LDAP;
private JRadioButton RadPW_LTOKEN;
private JRadioButton RadPW_EMAIL;
private JRadioButton RadPW_KOMBI;
private JRadioButton RadPW_GOOGLE;
//private AUCheckBox CbxPWneu;
//private AUCheckBox CbxLDAP;
private JRadioButton RadUserManager;
private JRadioButton RadNormal;
private ComboSort CboStartFormular;
//private ComboSort CboAbschluss;
// LDAP-Parameter
private Text TxtIP;
private Zahl TxtPort;
private Text TxtDom;
// Azure-Parameter
private Text TxtServ;
private Text TxtDom2;
private Text TxtClientId;
private Text TxtTenantId;
private Text TxtScope;
// Google-Parameter
private Text TxtClientIdG;
private Text TxtScopeG;
private AUCheckBox CbxOfflineG;
private AUTextArea TxtTokenG;

private Text TxtUser;
private JPasswordField TxtPW1;
private JPasswordField TxtPW2;
private Text TxtHost;

private JDialog FrmNewUserGroup;
private Text TxtBGBezeichnung = new Text("",100);
private Text TxtBGKennung = new Text("",40,Text.KENNUNG2);
private AUCheckBox CbxHistory;
private AUCheckBox CbxAbfrage;
private AUCheckBox CbxReadOnly;
private AUCheckBox CbxNurFilter;
private AUCheckBox CbxGeloeschte;
private AUCheckBox CbxWorkflow;
private AUCheckBox CbxWorkflowZR;
private AUCheckBox CbxNurWeb;
private AUCheckBox CbxNurAll;
private AUCheckBox CbxPers;
private AUCheckBox CbxJeder;
private JDialog DlgLDAP=null;
private JDialog DlgAzure=null;
private JDialog DlgGoogle=null;
private JDialog DlgDB_User=null;
private JDialog DlgCL=null;
private ComboSort CboCL=null;
private ActionListener AL=null;
//private Vector VecEigenschaft;
private int iMandantOld=-1;
private String sWFZR=null;
private boolean bFirstFU=true;
private boolean bFirstFUG=true;
private boolean bCheckLDAP=true;
}

