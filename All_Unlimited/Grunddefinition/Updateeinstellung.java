package All_Unlimited.Grunddefinition;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import java.awt.Window;
import All_Unlimited.Allgemein.Static;
import java.awt.event.ActionEvent;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Passwort;
import javax.swing.JFrame;
import javax.swing.JButton;
import All_Unlimited.Hauptmaske.AClient;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SQL;
//import All_Unlimited.Allgemein.Eingabe.Text;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.8
 */
public class Updateeinstellung extends Formular
{
  private static Updateeinstellung Self=null;
  private Global g;
  private int iAnzahl=10;
  private FileEingabe[] Txt=new FileEingabe[iAnzahl+1];
  /*private FileEingabe TxtInst;
  private FileEingabe TxtWeb;
  private FileEingabe TxtImg;
  private FileEingabe TxtHelp;
  private FileEingabe TxtAServer;
  private FileEingabe TxtTimer;
  private FileEingabe TxtTerminal;
  private FileEingabe TxtBackup;*/

  public static final int INST=1;
  public static final int WEB=2;
  public static final int IMG=3;
  public static final int HELP=4;
  public static final int ASERVER=5;
  public static final int TIMER=6;
  public static final int TERM=7;
  public static final int BACKUP=8;
  public static final int PROG=9;
  //public static final int CSS=10;
  
  private static String[] sArt=new String[]{"Install","Web","Img","Help","AServer","Timer","Term","Backup","Prog"};//,"CSS"};

  public static Updateeinstellung get(Global rg)
  {
    return Self == null ? new Updateeinstellung(rg) : Self;
  }

  public void show(Window w)
  {
    if (w != null)
      Static.centerComponent(thisFrame, w);
    LoadValues();
    super.show();
  }

  public static void free()
  {
    if(Self != null) {
      Self.g.winInfo("Updateeinstellung.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

  private Updateeinstellung(Global glob)
  {
    super("Update", null, glob);
    g = glob;
    Self = this;
    g.winInfo("Updateeinstellung.create");
    Build();
  }

  private void Build()
  {
    ActionListener AL=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s = ev.getActionCommand();
        if (s.equals("Ok"))
          Ok();
        else if (s.equals("Abbruch"))
          hide();
        else if (s.equals("Init"))
          Init();
      }
    };
    JPanel PnlEin=getFrei("Eingabe");
    JPanel Pnl=new JPanel(new BorderLayout(2,2));
     JPanel PnlW = new JPanel(new GridLayout(0,1,2,2));
      g.addLabel3(PnlW,"Installation");
      g.addLabel3(PnlW,"Web-Server");
      g.addLabel3(PnlW,"Prog2");
      g.addLabel3(PnlW,"Images");
      g.addLabel3(PnlW,"Hilfe");
      //g.addLabel3(PnlW,"Css");
      g.addLabel3(PnlW,"AServer");
      g.addLabel3(PnlW,"Timer");
      g.addLabel3(PnlW,"Terminal");
      g.addLabel3(PnlW,"Backup");
     Pnl.add("West",PnlW);
     JPanel PnlC = new JPanel(new GridLayout(0,1,2,2));
     for (int i=1;i<=iAnzahl;i++)
       Txt[i] = new FileEingabe(g,false);
      /*TxtInst = new FileEingabe(g,false);
      TxtWeb = new FileEingabe(g,false);
      TxtImg = new FileEingabe(g,false);
      TxtHelp = new FileEingabe(g,false);
      TxtAServer = new FileEingabe(g,false);
      TxtTimer = new FileEingabe(g,false);
      TxtTerminal = new FileEingabe(g,false);
      TxtBackup = new FileEingabe(g,false);*/
      g.addComp(PnlC,Txt[INST]);
      g.addComp(PnlC,Txt[WEB]);
      g.addComp(PnlC,Txt[PROG]);
      g.addComp(PnlC,Txt[IMG]);
      g.addComp(PnlC,Txt[HELP]);
      //g.addComp(PnlC,Txt[CSS]);
      g.addComp(PnlC,Txt[ASERVER]);
      g.addComp(PnlC,Txt[TIMER]);
      g.addComp(PnlC,Txt[TERM]);
      g.addComp(PnlC,Txt[BACKUP]);
     Pnl.add("Center",PnlC);
     JPanel PnlN=new JPanel(new BorderLayout(2,2));
     PnlN.add("North",Pnl);
    PnlEin.add(PnlN);
    JButton BtnOk = getButton("Ok","Ok",AL);
    ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnOk);
    getButton("Abbruch","Abbruch",AL);
    getButton("Init","Init",AL);

  }

  /*private void setDir(int iConst,String sDir)
  {
    Txt[iConst].setValue(sDir);
  }*/


  private void LoadValues()
  {
    //g.fixInfo("LoadValues");
    Parameter Para = new Parameter(g,"Update");
    Tabellenspeicher Tab=Para.getParameter("Dir");
    for (Tab.moveFirst();!Tab.out();Tab.moveNext())
      Txt[Tab.getI("int1")].setValue(Tab.getS("Parameterzeile"));//setDir(Tab.getI("int1"),Tab.getS("Parameterzeile"));
    Para.free();
  }

  private void SaveDir(int iConst,Parameter P)
  {
    if (Txt[iConst].Modified())
    {
      String sWhere=P.getWhere("Dir");
      int iAic=SQL.getInteger(g,"select aic_parameter from parameter"+sWhere+" and "+g.int1()+"=?",0,""+iConst);
      String sDir=Txt[iConst].getValue();
      P.add("parameterzeile",sDir);
      if (g.getLog()>0)
        P.add("aic_logging",g.getLog());
      if (iAic>0)
        P.update("Parameter",iAic);
      else
      {
        P.add(g.int1(), iConst);
        P.addGruppen();
        P.insert("Parameter",false);
      }
      g.SaveDefVerlauf(getBegriff(), sArt[iConst-1]+"-Pfad auf "+sDir);
      //Txt[iConst].setAktValue(sDir);
      Txt[iConst].setValue(sDir);
      //setDir(iConst,sDir);
    }
  }


  private void SaveValues()
  {
    //g.fixInfo("SaveValues");
    Parameter Para = new Parameter(g,"Update");
    boolean bLogin=g.getLog()<1;
    if (bLogin)
    {
    	g.setAnlage("Import");
    	g.Login();
    }
    for (int i=1;i<=iAnzahl;i++)
      SaveDir(i,Para);
    if (bLogin)
    	g.Logout(false);
    Para.free();
  }

  private void Init()
  {
    String sWeb="C:\\InetPub\\wwwroot";
    String sHaupt="C:\\ALL_UNLIMITED\\";
    Txt[WEB].setText(sWeb);
    Txt[PROG].setText(sHaupt+"AU_PROG\\Prog");
    Txt[IMG].setText(sWeb+"\\Images");
    Txt[HELP].setText(sWeb+"\\Hilfe");
    //Txt[CSS].setText(sWeb+"\\css");
    Txt[INST].setText(sHaupt+"AU_Install");
    Txt[ASERVER].setText(sHaupt+"AU_AServer");
    Txt[TIMER].setText(sHaupt+"AU_Timer");
    Txt[TERM].setText(sHaupt+"AU_Terminal");
    Txt[BACKUP].setText(sHaupt+"AU_Backup");
  }

  private void Ok()
  {
    SaveValues();
    hide();
  }

}
