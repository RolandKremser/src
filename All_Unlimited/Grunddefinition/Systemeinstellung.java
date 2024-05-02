package All_Unlimited.Grunddefinition;

import All_Unlimited.Allgemein.*;

import java.awt.Window;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import All_Unlimited.Allgemein.Eingabe.AUCheckBox;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.SpinBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Hauptmaske.Run;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.AUPasswort;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.10
 */
public class Systemeinstellung extends Formular
{
  private static Systemeinstellung Self=null;
  private Global g;
  
  private JLabel LblInfo=null;
  private AUCheckBox CbxInsert2;
  //private AUCheckBox CbxCheckZR;
  private ComboSort CboMandant;
  private Text EdtName;
  private AUPasswort EdtPW;
  private SpinBox EdtGauge;
  private SpinBox EdtZeilen;
  private SpinBox EdtEMailSleep;
  private SpinBox EdtEMailTimer;
  private SpinBox EdtTokenTime;
  private SpinBox EdtLongTokenTime;
  private SpinBox EdtBenML;
  private SpinBox EdtPWML; // Mindestlänge
  private SpinBox EdtPWMG; // Großbuchstaben
  private SpinBox EdtPWMZ; // Ziffern
  private SpinBox EdtPWMS; // Sonderzeichen
  private SpinBox EdtPWMM; // Monate gültig
  private JButton BtnStatus=null;
  private String sBtnStatus=null;
  private static final int iAnzahl=19;
  private Text[] Edt=new Text[iAnzahl+1];
  /*private Text EdtProg = new Text("",100);
  private Text EdtImageSys = new Text("",100);
  private Text EdtImageStamm = new Text("",100);
  private Text EdtImageDef = new Text("",100);
  private Text EdtHilfe = new Text("",100);
  private Text EdtDoku = new Text("",100);
  private Text EdtError = new Text("",100);
  private Text EdtTemp = new Text("",100);
  private Text EdtAServer = new Text("",100);*/
  private SpinBox EdtAutoLogout;
  private AUCheckBox CbxStern;
  private AUCheckBox CbxRahmen;
  //private AUCheckBox CbxLeiste;
  // private AUCheckBox CbxNewDesign;
  private AUCheckBox CbxCboFill;
  private AUCheckBox CbxMandantSuche;
  // private AUCheckBox CbxTimer;
  private AUCheckBox CbxStellvertreter;
  private JLabel LblStellV=new JLabel();
  
  private JRadioButton RadHauto;
  private JRadioButton RadHmin;
  private JRadioButton RadHdez;
  
  private JRadioButton RadASauto;
  private JRadioButton RadASimmer;
  private JRadioButton RadASnie;

  private JRadioButton RadAD;
  private JRadioButton RadND;
  private JRadioButton RadOD;
  
  public static final int cstStern	=1;
  public static final int cstRahmen	=2;
  //public static final int cstLeiste	=4;
  public static final int cstND		=8; // New Design
  public static final int cstMS	= 0x10; // Mandanten-Suche
  public static final int cstIT	= 0x20; // interner Timer
  public static final int cstMIN= 0x40; // HH:mm
  public static final int cstDEZ= 0x80; // 0,00 h
  public static final int cstSV= 0x100; // Stellvertreter
  public static final int cstDG= 0x200; // Design gesetzt 

  //private static final int PROG=1; // Programm-Verzeichnis
  private static final int ISYS=2; // Image-System-Verzeichnis
  private static final int IANR=3; // Image-Stamm-Verzeichnis
  private static final int IDEF=4; // Image-Kopf/Fuss-Verzeichnis
  private static final int HELP=5; // Hilfe-Verzeichnis
  private static final int DOKU=6; // Dokumenten-Verzeichnis
  private static final int ERRORW=7;//Fehlerverzeichnis für Windows
  private static final int ASERV=8;// AServer IP-Adresse:Port
  private static final int TEMP=9; // Temp-Verzeicnis
  //private static final int CSS=10; // CSS-Verzeichnis (Style ab V 5.12.91)
  private static final int PW=11;  // Default-PW
  private static final int ERRORM=12;// Fehlerverzeichnis für Mac
  //private static final int FXML=13; // FXML-Verzeichnis (alternative Darstellung V 5.13)
  private static final int FILE=14; // Imagey-Sys als File zum Auflisten
  private static final int WEB =15; // Web-Server (z.B.: http://127.0.0.1:9090/ALL/UNLIMITED/)
  private static final int IND =16; // Image-System-Verzeichnis für New Design
  private static final int WSYS=17; // Images-System für Web
  private static final int WANR=18; // Images-Stamm für Web
  private static final int WDEF=19; // Images-Kopf-/Fuss-Dir für Web

  private static int iTT=0;
  private static int iLTT=0;

  private static String[] sArt=new String[]{"Prog","ImgSys","ImgStamm","ImgDef","Help","Doku","ErrorWin","AServer","Temp","CSS","PW","ErrorMac","FXML","FileSys","Web","ImgND","wImgSys","wImgStamm","wImgDef"};

  public static Systemeinstellung get(Global rg)
  {
    return Self == null ? new Systemeinstellung(rg) : Self;
  }

  public void show(Window w)
  {
    if (w != null)
      Static.centerComponent(thisFrame, w);
    CboMandant.setSelectedAIC(g.getMandant());
    LoadDir(g, Edt,g.getMandant());
    setValues();
    super.show();
  }

  public static void free()
  {
    if(Self != null) {
      Self.g.winInfo("Systemeinstellung.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

  private Systemeinstellung(Global glob)
  {
    super("System", null, glob);
    g = glob;
    Self = this;
    g.winInfo("Systemeinstellung.create");
    AusgabePara();
    Build();
  }

  private void AusgabePara()
  {
    g.fixInfoT("AServer="+AClient.sAServerSoll);
    g.fixInfoT("WebServer="+Static.sWeb);
    g.fixInfoT("Image="+Static.DirImageSys);
    g.fixInfoT("ImageND="+Static.DirImageND);
  }

  private void setBack()
  {
    if (Static.bLtHtml)
    {
      AClient.sAServerSoll=Run.sAServer;
      Static.sWeb=Run.sWebServer;
      String sSep = Run.sWeb == null ? null:Run.sWeb.startsWith("http") ? "/":Run.sWeb.substring(Run.sWeb.length()-1);
      Static.DirImageSys=Run.sWeb+"Images"+sSep;
      Static.DirImageND=Run.sWeb+"Images2020"+sSep;
    }
  }

  private void Build()
  {
    ActionListener AL=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s = ev.getActionCommand();
        g.progInfo("Systemeinstellung.Command="+s);
        if (s.equals("Ok"))
        {
          setBack();
          AusgabePara();
          Ok();
        }
        else if (s.equals("Abbruch"))
        {
          setBack();
          AusgabePara();
          hide();
        }
        else if (s.equals("PW"))
          new Passwort((JFrame)thisFrame,g).setPasswort("Mandant",CboMandant.getSelectedAIC(),true);
        else if (s.equals("set"))
          setNamePW();
        else if (s.equals("status"))
          setBtnStatus();
        else if (s.equals("Backup"))
        {
          boolean b=AClient.send_AServer("backup", g);
          if (b)
          {
            String sE = "x";
            while (sE.equals("x"))
            {
              Static.sleep(500);
              sE = AClient.get_AServer("i:backup");
            }
            boolean bFehler=sE.startsWith("!");
            new Message(bFehler ? Message.WARNING_MESSAGE:Message.INFORMATION_MESSAGE,thisJFrame(),g).showDialog(bFehler?"Backup_Fehler":"Backup_Datei",new String[] {sE});
          }
          else
            new Message(Message.WARNING_MESSAGE,thisJFrame(),g).showDialog("kein_AServer");
        }
        else if (s.equals("Update"))
          Updateeinstellung.get(g).show(thisJFrame());
        else if (s.equals("JNLP"))
          LadeLtJNLP();
      }
    };

    JButton BtnOk = getButton("Ok","Ok",AL);
    ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnOk);
    getButton("Abbruch","Abbruch",AL);
    JButton BtnSet = getButton("set","set",AL);
    if (BtnSet!=null) BtnSet.setEnabled(g.Def());
    EdtName=new Text("",12);
    EdtName.setEditable(g.Def());
    EdtPW=new AUPasswort(12);
    EdtPW.setColumns(12);
    EdtPW.setEnabled(g.Def());
    String s=SQL.getString(g,"select Parameterzeile from parameter where aic_parameter=" + g.getPU());
    int iS=s==null?-1:s.indexOf(";");
    if (iS>0)
    {
      EdtName.setText(g.getPassword(s.substring(0,iS),Transact.PWR,0));
      EdtPW.setValue(g.getPassword(s.substring(iS+1),Transact.PWR,0));
    }
    addFrei("Name",EdtName);
    addFrei("PW",EdtPW);
//    JPanel Pnl_Name = getFrei("Name");
//    if (Pnl_Name != null) Pnl_Name.add(EdtName);
//    JPanel Pnl_PW = getFrei("PW");
//    if (Pnl_PW != null) Pnl_PW.add(EdtPW);
    CbxInsert2 = getCheckbox("Insert2");
    CbxInsert2.setVisible(g.Def());
//    CbxCheckZR = getCheckbox("CheckZR");
//    CbxCheckZR.setVisible(g.Def());
    
    // wichtige Info
    LblInfo=new JLabel(g.getShow("Daten_von")+" "+g.getShow(Static.bLtHtml?"jnlp":"Datenbank"));
    LblInfo.setFont(Global.fontTitel);
    JPanel PnlLbl=getFrei("Label");
    if (PnlLbl != null)
    	PnlLbl.add(LblInfo);
    
    // Optionen
    EdtAutoLogout=new SpinBox(2,0,12,1,Color.white);
    JPanel PnlAutoLogout=getFrei("autoausloggen");
    if (PnlAutoLogout != null)
    	PnlAutoLogout.add(EdtAutoLogout);
    CbxStern = getCheckbox("Stern");
    //CbxLeiste = getCheckbox("Leiste");
    CbxRahmen = getCheckbox("Rahmen3");
    if (CbxRahmen != null) CbxRahmen.setVisible(g.Prog());
    
    // CbxNewDesign = getCheckbox("newDesign");
    CbxCboFill = getCheckbox("kein_vorfuellen");
    CbxMandantSuche = getCheckbox("mandantSuche");
    // CbxTimer =  getCheckbox("internerTimer");
    CbxStellvertreter = getCheckbox("Stellvertreter");
    JPanel PnlStellvertreter=getFrei("Stellvertreter");
    if (PnlStellvertreter != null)
    {
    	LblStellV.setFont(Transact.fontStandard);
    	PnlStellvertreter.add(LblStellV);
    }
    RadHauto=getRadiobutton("h_auto");
    RadHmin=getRadiobutton("h_min");
    RadHdez=getRadiobutton("h_dez");
    
    RadASauto=getRadiobutton("AS_auto");
    RadASimmer=getRadiobutton("AS_immer");
    RadASnie=getRadiobutton("AS_nie");

    RadAD=getRadiobutton("autoDesign");
    RadND=getRadiobutton("newDesign");
    RadOD=getRadiobutton("oldDesign");
    
    CboMandant = new ComboSort(g);
    CboMandant.fillDefTable("Mandant",false);
    CboMandant.addItemListener(new ItemListener ()
	{
		public void itemStateChanged(ItemEvent ev)
		{
			if(ev.getStateChange() == ItemEvent.SELECTED)
			{
				LoadDir(g, Edt,CboMandant.getSelectedAIC());
			    setValues();
			}
		}
	});
    getButton("Benutzerpasswort","PW",AL);
    BtnStatus=getButton("status","status",AL);
    if (BtnStatus!=null)
    {
      sBtnStatus = BtnStatus.getText();
      setBtnStatus();
    }
    JButton Btn=getButton("Backup","Backup",AL);
    if (Btn!=null) Btn.setEnabled(AClient.UseAServer1());
    g.BtnAdd(getFormularButton("Update"),"Update",AL);
    JPanel Pnl_Mandant = getFrei("Mandant");
    Pnl_Mandant.add("Center",CboMandant);
    getButton("JNLP","JNLP",AL);

    EdtGauge=new SpinBox(100,10,1000,10,Color.white);
    EdtZeilen=new SpinBox(1000,100,2000,100,Color.white);
    EdtEMailSleep=new SpinBox(5,1,30,1,Color.white);
    EdtEMailTimer=new SpinBox(60,30,600,30,Color.white);
    EdtTokenTime=new SpinBox(120,1,720,1,Color.white);
    EdtLongTokenTime=new SpinBox(14,1,90,1,Color.white);
    EdtBenML=new SpinBox(5,1,20,1,Color.white);
    EdtPWML=new SpinBox(5,1,16,1,Color.white);
    EdtPWMG=new SpinBox(5,0,9,1,Color.white);
    EdtPWMZ=new SpinBox(5,0,9,1,Color.white);
    EdtPWMS=new SpinBox(5,0,9,1,Color.white);
    EdtPWMM=new SpinBox(5,0,24,1,Color.white);
    addFrei("Gauge",EdtGauge);
    addFrei("Zeilen",EdtZeilen);
    addFrei("E-Mail_Sleep",EdtEMailSleep);
    addFrei("E-Mail_Timer",EdtEMailTimer);
    addFrei("Token",EdtTokenTime);
    addFrei("LongToken",EdtLongTokenTime);
    addFrei("Ben_ML",EdtBenML);
    addFrei("PW_ML",EdtPWML);
    addFrei("PW_MG",EdtPWMG);
    addFrei("PW_MZ",EdtPWMZ);
    addFrei("PW_MS",EdtPWMS);
    addFrei("PW_MM",EdtPWMM);
//    JPanel Pnl_Gauge = getFrei("Gauge");
//    Pnl_Gauge.add("Center",EdtGauge);
//    JPanel Pnl_Zeilen = getFrei("Zeilen");
//    Pnl_Zeilen.add("Center",EdtZeilen);
//    JPanel Pnl_ESleep = getFrei("E-Mail_Sleep");
//    if (Pnl_ESleep != null)
//      Pnl_ESleep.add("Center",EdtEMailSleep);
//    JPanel PnlBenML = getFrei("Ben_ML");
//    if (PnlBenML != null)
//    	PnlBenML.add("Center",EdtBenML);
//    JPanel PnlPWML = getFrei("PW_ML");
//    if (PnlPWML != null)
//    	PnlPWML.add("Center",EdtPWML);
//    JPanel PnlPWMZ = getFrei("PW_MZ");
//    if (PnlPWMZ != null)
//    	PnlPWMZ.add("Center",EdtPWMZ);
    //Client-Zugriff-Verzeichnisse
    for (int i=1;i<=iAnzahl;i++)
      Edt[i]=new Text("",100);
    if(!Static.bLtHtml)
    {
      LoadDir(g, Edt,-1);
      //LoadDir(g, Edt,true);
    }
//    if (Static.app !=null)
//      Edt[PROG].setText(""+Static.app.getDocumentBase());
//    Edt[PROG].setEditable(false);
    Edt[ISYS].setText2(Static.DirImageOD);
    Edt[IND].setText2(Static.DirImageND);
    Edt[IANR].setText2(Static.DirImageStamm);
    Edt[IDEF].setText2(Static.DirImageDef);
    //Edt[CSS].setText2(Static.DirCss);
    Edt[HELP].setText2(Static.HilfeVerzeichnis);
    Edt[PW].setText2(Static.sDefaultPW);
    //Edt[FXML].setText2(Static.DirFxml);
//    Edt[FXML].setEditable(g.Def());
    Edt[FILE].setText2(Static.DirImageFile);
    Edt[FILE].setEditable(g.Def());
    //if (Static.DirImageFile==null) Edt[FILE].setText2(Static.DirImageSys);
    if (Static.DirDoku!=null) Edt[DOKU].setText2(Static.DirDoku);
    if (Static.noDirError())
    {
    	Edt[ERRORM].setText2(Static.sLeer);
    	Edt[ERRORW].setText2(Static.sLeer);
    }
    else if (Static.DirError!=null) Edt[g.Mac() ? ERRORM:ERRORW].setText2(Static.DirError);
    if (AClient.sAServerSoll!=null) Edt[ASERV].setText2(AClient.sAServerSoll);
    if (Static.sWeb!=null) Edt[WEB].setText2(Static.sWeb);
    Edt[TEMP].setText(Static.getTemp());
    Edt[TEMP].setEditable(false);
    Edt[ASERV].setEditable(g.Def());
    Edt[WEB].setEditable(g.Def());
    if (Static.bLtHtml)
    {
    	Edt[WEB].setEditable(false);
    	Edt[ASERV].setEditable(false);
    	Edt[ISYS].setEditable(false);
    	Edt[IND].setEditable(false);
//    	Edt[IANR].setEditable(false);
//    	Edt[IDEF].setEditable(false);
    	Edt[HELP].setEditable(false);
    	Edt[WEB].setBackground(Transact.ColBackground);
    	Edt[ASERV].setBackground(Transact.ColBackground);
    	Edt[HELP].setBackground(Transact.ColBackground);
    	Edt[ISYS].setBackground(Transact.ColBackground);
    	Edt[IND].setBackground(Transact.ColBackground);
//    	Edt[IANR].setBackground(g.ColBackground);
//    	Edt[IDEF].setBackground(g.ColBackground);
    }
//    JPanel PnlProg=getFrei("Prog");
//    if (PnlProg!=null) PnlProg.add(Edt[PROG]);
    addFrei("Image-Sys",Edt[ISYS]);
    addFrei("Image-ND",Edt[IND]);
    addFrei("Image-Stamm",Edt[IANR]);
    addFrei("Image-Def",Edt[IDEF]);
    addFrei("Hilfe",Edt[HELP]);
    addFrei("Doku",Edt[DOKU]);
//    addFrei("Css",Edt[CSS]);
//    addFrei("fxml",Edt[FXML]);
    addFrei("file",Edt[FILE]);
    addFrei("FehlerWin",Edt[ERRORW]);
    addFrei("FehlerMac",Edt[ERRORM]);
    addFrei("Temp",Edt[TEMP]);
    addFrei("AServer",Edt[ASERV]);
    addFrei("DefaultPW",Edt[PW]);
    addFrei("Web",Edt[WEB]);
    
    addFrei("Web-Image-Sys",Edt[WSYS]);
    addFrei("Web-Image-Stamm",Edt[WANR]);
    addFrei("Web-Image-Def",Edt[WDEF]);

//    JPanel PnlImageSys=getFrei("Image-Sys");
//    if (PnlImageSys!=null) PnlImageSys.add(Edt[ISYS]);
//    JPanel PnlImageStamm=getFrei("Image-Stamm");
//    if (PnlImageStamm!=null) PnlImageStamm.add(Edt[IANR]);
//    JPanel PnlImageDef=getFrei("Image-Def");
//    if (PnlImageDef!=null) PnlImageDef.add(Edt[IDEF]);
//    JPanel PnlHilfe=getFrei("Hilfe");
//    if (PnlHilfe!=null) PnlHilfe.add(Edt[HELP]);
//    JPanel PnlDoku=getFrei("Doku");
//    if (PnlDoku!=null) PnlDoku.add(Edt[DOKU]);
//    JPanel PnlCss=getFrei("Css");
//    if (PnlCss!=null) PnlCss.add(Edt[CSS]);
//    JPanel PnlFxml=getFrei("fxml");
//    if (PnlFxml!=null) PnlFxml.add(Edt[FXML]);
//    JPanel PnlFile=getFrei("file");
//    if (PnlFile!=null) PnlFile.add(Edt[FILE]);
//    JPanel PnlErrorW=getFrei("FehlerWin");
//    if (PnlErrorW!=null) PnlErrorW.add(Edt[ERRORW]);
//    JPanel PnlErrorM=getFrei("FehlerMac");
//    if (PnlErrorM!=null) PnlErrorM.add(Edt[ERRORM]);
//    JPanel PnlTemp=getFrei("Temp");
//    if (PnlTemp!=null) PnlTemp.add(Edt[TEMP]);
//    JPanel PnlAServer=getFrei("AServer");
//    if (PnlAServer!=null) PnlAServer.add(Edt[ASERV]);
//    JPanel PnlPW=getFrei("DefaultPW");
//    if (PnlPW!=null) PnlPW.add(Edt[PW]);
  }

  private void LadeLtJNLP()
  {
    g.fixtestError("LadeLtJNLP");
    if (Run.sWebServer != null)
      Edt[WEB].setText2(Run.sWebServer);
    if (Run.sAServer != null)
      Edt[ASERV].setText2(Run.sAServer);
    String sSep = Run.sWeb == null ? null:Run.sWeb.startsWith("http") ? "/":Run.sWeb.substring(Run.sWeb.length()-1);
    Edt[HELP].setText2(Run.sWeb+"Hilfe"+sSep);
    Edt[ISYS].setText2(Run.sWeb+"Images"+sSep);
    Edt[IANR].setText2(Run.sWeb+"Images"+sSep+"MA_Bilder"+sSep);
    Edt[IDEF].setText2(Run.sWeb+"Images"+sSep+"KopfFuss"+sSep);
    Edt[IND].setText2(Run.sWeb+"Images2020"+sSep);
  }

  private void setNamePW()
  {
    String s=EdtName.isNull() || EdtPW.isNull() ? "":g.PasswordConvert(EdtName.getText(),Global.PWR,0)+";"+g.PasswordConvert(EdtPW.getValue(),Global.PWR,0);
    int iPU=g.getPU();
    g.exec("Update parameter set Parameterzeile='"+s+"' where aic_parameter=" + g.getPU());
    g.fixtestInfo("setNamePW:"+s+" / "+iPU);
    //getNamePW();
  }

  /*private void getNamePW()
  {
    String s=SQL.getString(g,"select Parameterzeile from parameter where aic_parameter=" + g.getPU());
    int iS=s.indexOf(";");
    if (iS>0)
    {
      String sName= g.getPassword(s.substring(0,iS),false,0);
      String sPW=g.getPassword(s.substring(iS+1),false,0);
      g.fixtestInfo("Name="+sName+", PW="+sPW);
    }
    else
      g.fixtestInfo("Name/PW nicht ermittelbar");
  }*/

  private void setBtnStatus()
  {
    int i=AClient.getStatus();
    BtnStatus.setText(sBtnStatus+":"+AClient.getStatus(i,g)+(g.Def()?" ("+i+")":""));
  }

  private void setValues()
  {
    CbxInsert2.setSelected(Static.bInsert2);
    //CbxCheckZR.setSelected(Static.bCheckZR);

    EdtGauge.setIntValue(AUTable.iGaugeAb);
    EdtZeilen.setIntValue(AUTable.iMaxZeilen);

    EdtEMailSleep.setIntValue(Static.iESleep);
    EdtEMailTimer.setIntValue(Static.iETimer);
    EdtTokenTime.setIntValue(iTT);
    EdtLongTokenTime.setIntValue(iLTT);

    EdtBenML.setIntValue(Static.iBenML);
    EdtPWML.setIntValue(Static.iPWM/1000);
    EdtPWMG.setIntValue(Static.iPWM/100%10);
    EdtPWMZ.setIntValue(Static.iPWM/10%10);
    EdtPWMS.setIntValue(Static.iPWM%10);
    EdtPWMM.setIntValue(Static.iPWMM);
//    g.fixtestError("Sys.setValues: PWM="+Static.iPWM+", Monate="+Static.iPWMM);
    // Optionen
    EdtAutoLogout.setIntValue(Static.iAutoAus==0 ? 12:Static.iAutoAus/3600);
    if (CbxStern != null) CbxStern.setSelected(Static.bStern);
    if (CbxRahmen != null) CbxRahmen.setSelected(Static.bRahmen);
    //if (CbxLeiste != null) CbxLeiste.setSelected(Static.bLeiste);
    // if (CbxNewDesign != null) CbxNewDesign.setSelected(Static.bND);
    if (CbxCboFill != null) CbxCboFill.setSelected(Static.iComboLeer==Static.NIE);
    if (CbxMandantSuche != null) CbxMandantSuche.setSelected(Static.bMandantensuche);
    // if (CbxTimer != null) CbxTimer.setSelected(Static.bInternerTimer);
    if (CbxStellvertreter != null) CbxStellvertreter.setSelected(Static.sAbfrageErsatz!=null);
    LblStellV.setText(Static.sAbfrageErsatz);
    if (RadHauto != null)
    	if (g.bH_dez)
    		RadHdez.setSelected(true);
    	else if (g.bH_min)
        	RadHmin.setSelected(true);
    	else
    		RadHauto.setSelected(true);
    if (RadASauto != null)
    	if (Static.iAServer==Static.NIE)
    		RadASnie.setSelected(true);
    	else if (Static.iAServer==Static.IMMER)
        	RadASimmer.setSelected(true);
    	else
    		RadASauto.setSelected(true);
    if (RadAD != null)
      if (!Static.bDS)
        RadAD.setSelected(true);
      else if (Static.bND)
        RadND.setSelected(true);
      else
        RadOD.setSelected(true);
    if (Static.bLtHtml)
    {
    	EdtAutoLogout.setEnabled(false);
    	if (CbxStern != null) CbxStern.setEnabled(false);
    	if (CbxRahmen != null) CbxRahmen.setEnabled(false);
    	// if (CbxNewDesign != null) CbxNewDesign.setEnabled(false);
    	if (CbxMandantSuche != null) CbxMandantSuche.setEnabled(false);
    	// if (CbxTimer != null) CbxTimer.setEnabled(false);
    	if (CbxStellvertreter != null) CbxStellvertreter.setEnabled(false);
    	if (RadHauto != null)
    	{
    		RadHauto.setEnabled(false);
    		RadHmin.setEnabled(false);
    		RadHdez.setEnabled(false);
    	}
    }
    else if (RadHauto != null)
    {
    	Parameter Para2 = new Parameter(g,"System");
        Para2.getParameter("Option2", false, false);
//        g.fixtestError("Para-Option="+Para2.int1+"/"+Para2.int2);
        if (Para2.int1>1)
        {
        	int iBits=Para2.int2/16;
        	if((iBits & cstDEZ)>0)
        		RadHdez.setSelected(true);
        	else if ((iBits & cstMIN)>0)
        		RadHmin.setSelected(true);
        	else
        		RadHauto.setSelected(true);
        }
        Para2.free();
    }
    if (CbxCboFill != null) CbxCboFill.setEnabled(false);
	
  }

  private static void setDir(int iConst,String sDir,Text[] Edt,Global g)
  {
//	  g.fixtestError("setDir "+iConst+":"+sDir);
    if (iConst>iAnzahl)
      return;
    if (Edt!=null)
        Edt[iConst].setText(sDir);
    if (Static.Leer(sDir))
      return;
    if (iConst==ISYS || Static.bWeb && iConst==WSYS)
          Static.DirImageOD=sDir;
    else if (iConst==IND)
        Static.DirImageND=sDir;
      else if (iConst==IANR || Static.bWeb && iConst==WANR)
          Static.DirImageStamm=sDir;
      else if (iConst==IDEF || Static.bWeb && iConst==WDEF)
    	Static.DirImageDef=sDir;
      else if (iConst==HELP)
          Static.HilfeVerzeichnis=sDir;
      else if (iConst==DOKU)
        Static.DirDoku=sDir;
//      else if (iConst==CSS)
//      {
//        Static.DirCss=sDir;
//        Static.DirCssDefault=sDir;
//        //g.fixtestError("DirCss="+Static.DirCss);
//        if (g.TabBegriffe != null)
//        	g.setDefaultCss();
//        //Static.sDefaultStyle=g.getCss(g.getPosBegriff("Show","Formular"));
//        //g.fixtestError("DefaultStyle2="+Static.sDefaultStyle);
//      }
//      else if (iConst==FXML)
//          Static.DirFxml=sDir;
      else if (iConst==FILE)
          Static.DirImageFile=sDir;        
      else if (iConst==ERRORW && Static.DirErrorWin==null)
        Static.DirErrorWin=sDir;
      else if (iConst==ERRORM && Static.DirErrorMac==null)
        Static.DirErrorMac=sDir;
      else if (iConst==ASERV)
        AClient.sAServerSoll=sDir;
      else if (iConst==WEB)
    	  Static.sWeb=sDir;
      else if (iConst==PW)
      {
        Static.sDefaultPW=sDir;
//        g.fixtestError("sDefaultPW="+Static.sDefaultPW);
      }
    
    Static.DirImageSys=Static.bND ? Static.beiLeer(Static.DirImageND,Static.DirImageOD):Static.DirImageOD;
  }

  public static void LoadDir(Global g,Text[] Edt,int iMandant)
  {
//	  g.fixtestError("LoadDir mit Mandant="+iMandant);
//	  g.fixInfo("AServer1=" + AClient.sAServerSoll);
//      g.fixInfo("Web-Server1=" + Static.sWeb);
    Parameter Para = new Parameter(g,"System");
    if (iMandant>=0)
    {
	    Para.getMParameter("ML", iMandant);
	    if (Para.bGefunden)
	    {
	  	  Static.iBenML=Para.int1;
	  	  if (Para.int2==99)
    	  {
    		Static.iPWM=Para.int3;
    		Static.iPWMM=Para.int4;
    	  }
    	  else
    	  {
    		Static.iPWM=Para.int2*1000+Para.int3*10+Para.int4;
    		Static.iPWMM=0;
    	  }
//	  	  Static.iPWML=Para.int2;
//	  	  Static.iPWMZ=Para.int3;
//	  	  Static.iPWMS=Para.int4;
//	  	  g.fixtestInfo("Parameter von Mandant "+iMandant+": "+Para.int1+"/"+Para.int2+"/"+Para.int3+"/"+Para.int4);
	    }
	    else
	    {
	    	Static.iPWM=8020;
	    	Static.iPWMM=0;
        Static.iBenML=6;
	    }

      Para.getParameter("Option",false,false);
      boolean bG=Para.bGefunden;
      //int i1=bG ? Para.int1:0;
      int i2=bG ? Para.int2:0;
      int i4=bG ? Para.int4:0;

      Para.getMParameter("Time", iMandant);
      if (Para.bGefunden)
	    {
        iTT=Para.int1/60;
        iLTT=Para.int2;
        Static.iESleep=Para.int3;
        Static.iETimer=Para.int4;
      }
      else
      {
        iLTT=14;
        Static.iETimer=60;
        if (bG)
        {
          Static.iESleep=i4%100;
          if (i4>100)
          {
            iTT=i4/100;
            if (iTT>1000)
              if (iTT%30==0)
                iTT/=60;
              else
                iTT=120;
          }
        }
        else
        {
          iTT=120;
          Static.iESleep=5;
        }
      }

      Para.getMParameter("Table", iMandant);
      if (Para.bGefunden)
	    {
        AUTable.iMaxZeilen=Para.int1;
        AUTable.iGaugeAb=Para.int2;
      }
      else
      {
        if (bG)
        {
          AUTable.iMaxZeilen=i2/1024;
          AUTable.iGaugeAb=i2%1024;
        }
        else
        {
          AUTable.iMaxZeilen=1000;
          AUTable.iGaugeAb=100;
        }
      }
    }
    setDir(PW,"start",Edt,g);
    Tabellenspeicher Tab=Para.getMParaTab("Dir",iMandant==1 ? -1:iMandant);
//    Tab.showGrid("Para für "+iMandant);
//    Tab.showGrid("Tab-Dir"+iMandant);
    for (Tab.moveFirst();!Tab.out();Tab.moveNext())
      setDir(Tab.getI("int1"), Tab.getS("Parameterzeile"),Edt,g);
//    if (Static.DirImageFile==null) Static.DirImageFile=Static.DirImageSys;
    //Tabellenspeicher TabM=Para.getMParaTab("Dir",true);
    //TabM.showGrid("Tab-DirM");
    //for (TabM.moveFirst();!TabM.out();TabM.moveNext())
    //  setDir(TabM.getI("int1"), TabM.getS("Parameterzeile"),Edt);
    if (Edt==null)
    {
      g.fixInfo("AServer=" + AClient.sAServerSoll);
      g.fixInfo("Web-Server=" + Static.sWeb);
      Static.DirError=g.Mac()  && Static.DirErrorMac!=null ? Static.DirErrorMac:Static.DirErrorWin!=null ? Static.DirErrorWin:Static.DirError;
      g.fixInfo("DirError=" + Static.DirError);
      g.fixInfo("DirImageSys=" + Static.DirImageSys);
      g.fixInfo("DirImageDef=" + Static.DirImageDef);
      g.fixInfo("DirImageStamm=" + Static.DirImageStamm);
      g.fixInfo("DirHilfe=" + Static.HilfeVerzeichnis);
      g.fixInfo("DirDoku=" + Static.DirDoku);
//      g.fixtestInfo("DirCss=" + Static.DirCss);
//      g.fixtestInfo("DirFXML=" + Static.DirFxml);
    }
    Para.free();
  }

  private void SaveDir(int iConst,Parameter P)
  {
    String sDir=Edt[iConst].getText();
    if (Edt[iConst].Modified() || Static.Leer(sDir))
    {
      String sWhere=P.getWhere("Dir");
      boolean bAufMandant=/*iConst==CSS || */iConst==IANR || iConst==IDEF || iConst==PW || iConst==DOKU;
      int iM=CboMandant.getSelectedAIC();//g.getMandant();
      int iAic=SQL.getInteger(g,"select aic_parameter from parameter"+sWhere+(bAufMandant?" and aic_mandant"+(iM==1?" is null":"="+iM):"")+" and "+g.int1()+"=?",0,""+iConst);
      if (Static.Leer(sDir))
      {
        if (iAic>0)
          P.exec("delete from parameter where aic_parameter="+iAic);
      }
      else
      {
        P.add("parameterzeile", sDir);
        P.add("aic_logging", g.getLog());
        if (iAic > 0)
          P.update("Parameter", iAic);
        else
        {
          P.add(g.int1(), iConst);
          P.addGruppen();
          if (bAufMandant && iM>1)
            P.add("aic_mandant",iM);
          P.insert("Parameter", false);
        }
        if (sArt != null && sArt.length>=iConst)
          g.SaveDefVerlauf(getBegriff(), sArt[iConst-1]+" auf "+sDir);
        if (iConst==ASERV)
        {
        	g.fixtestError("ändere AServer auf "+sDir);
        	AClient.setNull();
        	AClient.sAServerSoll=sDir;
        }
        Edt[iConst].setAktText(sDir);
        setDir(iConst,sDir,null,g);
      }
    }
  }

  private void Ok()
  {
	  Parameter Para = new Parameter(g,"System");
		
		int iBenML=EdtBenML.getIntValue();
//		int iPWML=EdtPWML.getIntValue();
//		int iPWMG=EdtPWMG.getIntValue();
//		int iPWMZ=EdtPWMZ.getIntValue();
//		int iPWMS=EdtPWMS.getIntValue();
		int iPWMM=EdtPWMM.getIntValue();
		int iPWM=EdtPWML.getIntValue()*1000+EdtPWMG.getIntValue()*100+EdtPWMZ.getIntValue()*10+EdtPWMS.getIntValue();
		if (EdtPWML.getIntValue()<=EdtPWMG.getIntValue()+EdtPWMZ.getIntValue()+EdtPWMS.getIntValue())
		{
			new Message(Message.WARNING_MESSAGE,thisJFrame(),g).showDialog("PW_Einstellung");
			return;
		}
		if (iBenML != Static.iBenML || iPWM != Static.iPWM || iPWMM != Static.iPWMM)
		{	
			Static.iBenML=iBenML;
			Static.iPWM=iPWM;
			Static.iPWMM=iPWMM;
//			Static.iPWML=iPWML;
//			Static.iPWMZ=iPWMZ;
//			Static.iPWMS=iPWMS;
			Para.setMParameter("ML","",iBenML,99,iPWM,iPWMM/* Monate */,CboMandant.getSelectedAIC());
		}
    if (AUTable.iGaugeAb != EdtGauge.getIntValue() || AUTable.iMaxZeilen != EdtZeilen.getIntValue())
    {
        AUTable.iGaugeAb=EdtGauge.getIntValue();
		    AUTable.iMaxZeilen=EdtZeilen.getIntValue();
        Para.setMParameter("Table","",AUTable.iMaxZeilen,AUTable.iGaugeAb,0,0, CboMandant.getSelectedAIC());
    }
    if (Static.bInsert2 != CbxInsert2.isSelected())
    {
       Static.bInsert2=CbxInsert2.isSelected();
       //Static.bCheckZR=CbxCheckZR.isSelected();
       // Info 19.10.2022: Mandant wird seit 22.3.2013 nur als Info gespeichert, aber nirgends verwendet
       Para.setParameter("Option","",/*(CbxView2.isSelected()?1:0)+*/(Static.bInsert2?2:0)/*+(Static.bCheckZR?4:0)*/,AUTable.iMaxZeilen*1024+AUTable.iGaugeAb
          ,CboMandant.getSelectedAIC(),Static.iESleep+100*iTT/*+100000*Static.iLTT+10000000*Static.iETimer/30*/,false,false);
    }
		if (Static.iESleep != EdtEMailSleep.getIntValue() || Static.iETimer != EdtEMailTimer.getIntValue() || iTT != EdtTokenTime.getIntValue() || iLTT != EdtLongTokenTime.getIntValue())
		{  
		    Static.iESleep=EdtEMailSleep.getIntValue();
        Static.iETimer=EdtEMailTimer.getIntValue();
		    iTT=EdtTokenTime.getIntValue();
        iLTT=EdtLongTokenTime.getIntValue();		    
        Para.setMParameter("Time", "", iTT*60, iLTT, Static.iESleep, Static.iETimer, CboMandant.getSelectedAIC());
        	
		}
    if (RadND != null)
    {
    	int i1=EdtAutoLogout.getIntValue();
    	if (i1>15) i1=15;
    	//V1: int i2=(CbxStern.isSelected() ? cstStern:0)+(CbxRahmen.isSelected() ? cstRahmen:0)+(CbxLeiste.isSelected() ? cstLeiste:0);
    	int i2Old=(Static.bStern ? cstStern:0)+(Static.bRahmen ? cstRahmen:0)+(Static.bND ? cstND:0)+(Static.bMandantensuche ? cstMS:0)+(Static.bInternerTimer ? cstIT:0)+(g.bH_min ? cstMIN:0)+(g.bH_dez ? cstDEZ:0)+(Static.sAbfrageErsatz!=null ? cstSV:0);
    	int i2=(CbxStern.isSelected() ? cstStern:0)+(CbxRahmen.isSelected() ? cstRahmen:0)/*+(CbxLeiste.isSelected() ? cstLeiste:0)*/+(RadND.isSelected() ? cstND:0)+(CbxMandantSuche.isSelected() ? cstMS:0)+(RadAD.isSelected() ? 0:cstDG);//+(CbxTimer.isSelected() ? cstIT:0);
    	i2+=(RadHmin.isSelected() ? cstMIN: RadHdez.isSelected() ? cstDEZ:0)+(CbxStellvertreter!=null && CbxStellvertreter.isSelected() ? cstSV:0);
    	if (i2 != i2Old || Static.iAutoAus/3600 != i1)
    	{
    		g.fixtestError("i2:"+i2Old+"->"+i2+", iAutoAus"+(Static.iAutoAus/3600)+"->"+i1);
	    	Para.setParameter("Option2","",2,i1+i2*16,0,0,false,false);
	    	Static.iAutoAus=i1==0 ? 5*60:i1*3600;
	    	Static.setND(RadND.isSelected());
	    	Static.bStern=CbxStern.isSelected();
	    	Static.bRahmen=CbxRahmen.isSelected();
	    	//Static.bLeiste=CbxLeiste.isSelected();
	    	Static.bMandantensuche=CbxMandantSuche.isSelected();
	    	// Static.bInternerTimer=CbxTimer.isSelected();
	    	g.bH_dez=RadHdez.isSelected();
	    	g.bH_min=RadHmin.isSelected();
	    	if (CbxStellvertreter!=null) 
	    		if (CbxStellvertreter.isSelected())
	    			Static.sAbfrageErsatz="ZE_Pruefung_Stellvertreter";
	    		else
	    			Static.sAbfrageErsatz=null;
	    	LblStellV.setText(Static.sAbfrageErsatz);
    	}
    	if (RadASauto != null)
    		Static.iAServer=RadASnie.isSelected() ? Static.NIE:RadASimmer.isSelected() ? Static.IMMER:Static.AUTO;
    }
    //SaveDir(EdtProg,PROG,Para);
    boolean bLogin=g.getLog()<1;
    if (bLogin)
    {
    	g.setAnlage("Import");
    	g.Login();
    }
    for (int i=ISYS;i<=iAnzahl;i++)
      SaveDir(i,Para);
    if (bLogin)
    	g.Logout(false);
    /*SaveDir(EdtImageSys,ISYS,Para);
    SaveDir(EdtImageStamm,IANR,Para);
    SaveDir(EdtImageDef,IDEF,Para);
    SaveDir(EdtHilfe,HELP,Para);
    SaveDir(EdtDoku,DOKU,Para);
    SaveDir(EdtError,ERROR,Para);
    SaveDir(EdtAServer,ASERV,Para);*/
    Para.free();

    hide();
  }

}
