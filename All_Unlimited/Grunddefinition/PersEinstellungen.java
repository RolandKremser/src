/*
    All_Unlimited-Grunddefinition-PersEinstellungen.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.Passwort;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Hauptmaske.EingabeFormular;
//import All_Unlimited.Hauptmaske.Hauptschirm;
import All_Unlimited.Hauptmaske.TCalc;
import All_Unlimited.Allgemein.Tabellenspeicher;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PersEinstellungen extends All_Unlimited.Allgemein.Formular
{
  public static PersEinstellungen get(Global rg)
  {
    return Self == null ? new PersEinstellungen(rg) : Self;
  }

  public void show(Window w) {
    if (w != null)
      Static.centerComponent(thisFrame, w);
    setCbxCbo();
    super.show();
  }

  public static void free()
  {
    if(Self != null) {
      Self.g.winInfo("PersEinstellungen.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

	private PersEinstellungen(Global glob)
	{
		super("PersEinstellungen"+(glob.Workflow()?"2":""),null,glob);
		g=glob;
        Self=this;
        g.winInfo("PersEinstellungen.create");
		CboSprache = new ComboSort(g);
		CboSprache.fillDefTable("Sprache",false);
                //CboSprache.setFont(g.fontStandard);
		CboLand = new ComboSort(g);
		CboLand.fillDefTable("Land",false);
                //CboLand.setFont(g.fontStandard);
//		CboWaehrung = new ComboSort(g);
//		CboWaehrung.fillWaehrung(false,false);
                //CboWaehrung.setFont(g.fontStandard);
                EdtSchrift=new SpinBox(100,80,150,10,Color.WHITE);
                if (iFF!=100)
                	EdtSchrift.setPreferredSize(new Dimension(50*iFF/100,20*iFF/100));
                //EdtSchrift.setMinimum(50);
                //EdtSchrift.setMaximum(200);
                //EdtSchrift.setIncrement(10);
                EdtSchrift.setEditable(false);
                //EdtSchrift.setFont(g.fontStandard);
		CboMandant = new ComboSort(g);
		CboMandant.fillDefTable("Mandant",false);
                //CboMandant.setFont(g.fontStandard);

		SQL Qry = new SQL(g);

		if(Qry.open("SELECT Kennung FROM Benutzer WHERE AIC_Benutzer="+g.getBenutzer()) && !Qry.eof())
		{
			//sBenutzerKennung = Qry.getS("Kennung");
			//iSprache = Qry.getI("AIC_Sprache");
			//iLand = Qry.getI("AIC_Land");

			EdtBenutzer.setText(Qry.getS("Kennung"));
                        EdtBenutzer.setEditable(false);
			Qry.close();
		}
		//else
		//	Static.printError("PersEinstellungen: Kennung von Benutzer konnte nicht ausgelesen werden!!!");

		if(Qry.open("SELECT Kennung FROM Computer WHERE AIC_Computer="+g.getComputer()) && !Qry.eof())
		{
			//sComputerKennung = Qry.getS("Kennung");
                        EdtComputer.setText(Qry.getS("Kennung"));
                        EdtComputer.setEditable(false);
			Qry.close();
		}
		//else
		//	Static.printError("PersEinstellungen: Kennung von Computer konnte nicht ausgelesen werden!!!");

		Qry.free();

                AL=new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    String s = ev.getActionCommand();
                    if (s.equals("ClearCache"))
                    	ClearCache();
//                      new Message(Message.INFORMATION_MESSAGE,(JFrame)null,g,15).showDialog("ClearCache",new String[] {""+Static.clearCache()});
                    else if (s.endsWith("passwort"))
                      new Passwort((JFrame)thisFrame,g).setPasswort(s.equals("Mandantpasswort") ? "Mandant":"Benutzer",s.equals("Mandantpasswort") ? CboMandant.getSelectedAIC():g.getBenutzer(),true);
                    else if (s.equals("Abbruch") || s.equals("Ok") && Ok())
                      hide();
                  }
                };

		Build();

		setCbxCbo();
		CbxInfo1.setVisible(g.Def());
		CbxInfo2.setVisible(g.Def());
		CbxInfo3.setVisible(g.Def());
		CbxInfo4.setVisible(g.Def());
                CbxFehlende.setVisible(g.Def());
                CbxFormatSortiert.setVisible(g.Def());
		//CbxSQL.setVisible(g.Prog());
		CbxInfo.setVisible(g.SuperUser());
                CbxTestFenster.setVisible(g.SuperUser());
                CbxAutoEdit.setVisible(g.Def());
                
                CbxClock.setVisible(true);
                CbxClockSub.setVisible(true);
                CbxClock3.setVisible(true);
		CbxDebug.setVisible(g.Def());
                CbxNoInfo.setVisible(g.Def());
                CbxNoFixTest.setVisible(g.bTest || g.bTestPC || g.bAppl);
                CbxNoSpeed.setVisible(g.Def());
                //CbxLDATE.setVisible(g.Def());
                CbxKeinLDate.setVisible(g.Def());
                CbxKeineEinheit.setVisible(g.Def());
		CboMandant.setVisible(g.SuperUser());    // 8.7. auf Wunsch von Doris wieder rein
                if (BtnMPassword != null)
                  BtnMPassword.setVisible(g.SuperUser()); // 8.7. auf Wunsch von Doris wieder rein
                if (CbxFixieren != null)
                  CbxFixieren.setVisible(g.SuperUser());
                //if(BtnClearCache!=null) BtnClearCache.setEnabled(Static.cache());

                CbxDefBezeichnung.setVisible(g.Def());
                CbxShowSize.setVisible(g.Def());
                CbxStdSize.setVisible(g.Def());
//                CbxOriginal.setVisible(g.Def());
                CbxDefShow.setVisible(g.Def());
                //CbxView2.setVisible(g.Def());
                //CbxInsert2.setVisible(g.Def());
                CbxNurJeder.setVisible(g.Def());

                CbxSpeichermeldung.setVisible(g.Def());
                CbxNoCache.setVisible(g.Def());
                CbxNoAServer.setVisible(g.Def());
                //CbxJavaFX.setVisible(g.Def());
                //VisibleJavaFX();

//                CbxJavaFX.addActionListener(new ActionListener()
//                {
//                                public void actionPerformed(ActionEvent e)
//                                {
//                                	VisibleJavaFX();
//                                }
//                });
                /*

		if (BtnPassword != null)
			BtnPassword.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new Passwort((JFrame)thisFrame,g).setPasswort(g.SuperUser() ? "Mandant":"Benutzer",g.SuperUser() ? CboMandant.getSelectedAIC():g.getBenutzer(),true);
				}
			});

		BtnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(Ok())
				{
                                  hide();
				}
			}
		});

		BtnAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				hide();
			}
		});*/

		//show();
	}
	
//	private void VisibleJavaFX()
//	{
//		boolean b=g.Def() && CbxJavaFX.isSelected();
//		CbxShowStyle.setVisible(b);
//        CbxAlert.setVisible(b);
//        CbxKeinCss.setVisible(b);
//        CbxCenterStage.setVisible(b);
//        CbxSQ.setVisible(b);
//	}
	
	private void ClearCache()
	{
		if (Static.cache())
			new Message(Message.INFORMATION_MESSAGE,(JFrame)null,g,15).showDialog("ClearCache",new String[] {""+Static.clearCache()});
		if (g.Def())
		{
			int iAnz=g.exec("update computer set cbits=cbits+"+Transact.CLRCACHE+" where" + g.bits("cbits", Transact.CLRCACHE)+"=0");
			g.fixtestInfo(iAnz+" Computer auf ClearCache gesetzt");
		}
	}

        private void setCbxCbo()
        {
          CbxInfo.setSelected(g.Info());
          CbxInfo1.setSelected((Transact.iInfos & Transact.INFO1) > 0);
          CbxInfo2.setSelected((Transact.iInfos & Transact.INFO2) > 0);
          CbxInfo3.setSelected((Transact.iInfos & Transact.INFO3) > 0);
          CbxInfo4.setSelected((Transact.iInfos & Transact.EXEC) > 0);
          CbxX.setSelected(Static.bX);
          CbxClock.setSelected(g.Clock());
          CbxClockSub.setSelected((Transact.iInfos & Transact.CLOCKSUB) > 0);
          CbxClock3.setSelected((Transact.iInfos & Transact.CLOCK3) > 0);
          CbxMal2.setSelected((Transact.iInfos & Transact.MAL2) > 0);
          CbxFehlende.setSelected((Transact.iInfos & Transact.FEHLENDE) > 0);
          CbxNoCache.setSelected((Transact.iInfos & Transact.NO_CACHE) > 0);
          CbxNoAServer.setSelected((Transact.iInfos & Transact.NO_ASERVER) > 0);
          //CbxFill.setSelected((Transact.iInfos & Transact.FILL) > 0);
          CbxNoInfo.setSelected((Transact.iInfos & Transact.NO_INFO) > 0);
          CbxNoFixTest.setSelected((Transact.iInfos & Transact.NO_FIX) > 0);
          CbxNoSpeed.setSelected((Transact.iInfos & Transact.NO_SPEED) > 0);
          CbxKeinLDate.setSelected(Static.bKeinLDate);
          //CbxLDATE.setSelected((g.iInfos&g.LDATE)>0);
          CbxKeineEinheit.setSelected(Static.bKeineEinheit);
          CbxFastOut.setSelected((Transact.iInfos & Transact.FAST_OUT) > 0);
          EdtSchrift.setIntValue(g.getFontFaktorO());
          //CbxLeerLassen.setSelected(Static.bLeerLassen);
          CbxDebug.setSelected(g.Debug());
          //CbxSQL.setSelected(g.bZeigeInfo);
          CbxSpeichermeldung.setSelected(Static.bSpeichernAnzeigen);
          CbxTestFenster.setSelected(Static.bTestFenster);
          CbxAutoEdit.setSelected(Static.bAutoEdit);
          CbxFormatSortiert.setSelected(Static.bFormatSortiert);
          if (CbxLizenzFrei!=null) CbxLizenzFrei.setSelected(g.bLizenzFrei);
//          CbxSQ.setSelected(Static.bSpeichernFrage);
          //CbxAutoInit.setSelected(g.bAutoInit);
          CboSprache.setSelectedAIC(g.getSprache());
          CboLand.setSelectedAIC(g.getLand());
//          CboWaehrung.setSelectedAIC(g.getWaehrung());
          CboMandant.setSelectedAIC(g.getMandant());
          CbxDefBezeichnung.setSelected(Static.bDefBezeichnung);
          CbxShowSize.setSelected(Static.bShowSize);
          CbxStdSize.setSelected(Static.bStdSize);
//          CbxOriginal.setSelected(Static.bOriginal);
          CbxDefShow.setSelected(Static.bDefShow);
          //CbxView2.setEnabled(Static.bView2);
          //CbxInsert2.setSelected(Static.bInsert2);
          CbxNurJeder.setSelected(Static.bNurJeder);
          CbxNurStart.setSelected(Static.bNurStart);
//          if (EdtCss!=null && !Static.Leer(Static.DirCss) && Static.DirCss!=Static.DirCssDefault)
//        	  EdtCss.setText(Static.DirCss);
          //CbxJavaFX.setSelected(Static.bJavaFX);
          //CbxShowStyle.setSelected(Static.bShowStyle);
          //CbxAlert.setSelected(Static.bAlert);
          CbxBeep.setSelected(Static.bBeep);
          //CbxKeinCss.setSelected(!Static.bUseDefaultCss);
//          CbxCenterStage.setSelected(Static.bCenterStage);
        }

	private void Build()
	{
		BtnOk = g.BtnAdd(getButton("Ok"),"Ok",AL);
		if(BtnOk==null) BtnOk = new JButton();
                ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnOk);
		BtnAbbruch = g.BtnAdd(getButton("Abbruch"),"Abbruch",AL);
		if(BtnAbbruch==null) BtnAbbruch = new JButton();

		CbxInfo = getCheckbox("Info");
		if(CbxInfo==null) CbxInfo=new AUCheckBox();
		CbxInfo1 = getCheckbox("Info1");
		if(CbxInfo1==null) CbxInfo1=new AUCheckBox();
		CbxInfo2 = getCheckbox("Info2");
		if(CbxInfo2==null) CbxInfo2=new AUCheckBox();
		CbxInfo3 = getCheckbox("Info3");
		if(CbxInfo3==null) CbxInfo3=new AUCheckBox();
		CbxInfo4 = getCheckbox("Info4");
		if(CbxInfo4==null) CbxInfo4=new AUCheckBox();
		CbxX = getCheckbox("x");
		if(CbxX==null) CbxX=new AUCheckBox();
        CbxClock = getCheckbox("Clock");
		if(CbxClock==null) CbxClock=new AUCheckBox();
        CbxClockSub = getCheckbox("ClockSub");
        if(CbxClockSub==null) CbxClockSub=new AUCheckBox();
        CbxClock3 = getCheckbox("Clock3");
        if(CbxClock3==null) CbxClock3=new AUCheckBox();
        CbxMal2 = getCheckbox("Mal2");
        if(CbxMal2==null) CbxMal2=new AUCheckBox();
        CbxDebug = getCheckbox("Debug");
		if(CbxDebug==null) CbxDebug=new AUCheckBox();
        CbxNoCache = getCheckbox("no Cache");
		if(CbxNoCache==null) CbxNoCache=new AUCheckBox();
        CbxNoAServer = getCheckbox("no AServer");
		if(CbxNoAServer==null) CbxNoAServer=new AUCheckBox();
        CbxNoInfo = getCheckbox("no Info");
		if(CbxNoInfo==null) CbxNoInfo=new AUCheckBox();
		CbxNoFixTest = getCheckbox("no fixtest");
		if(CbxNoFixTest==null) CbxNoFixTest=new AUCheckBox();
        CbxNoSpeed = getCheckbox("no Speed");
		if(CbxNoSpeed==null) CbxNoSpeed=new AUCheckBox();
		CbxKeinLDate = getCheckbox("keinLDate");
		if(CbxKeinLDate==null) CbxKeinLDate=new AUCheckBox();
                //CbxLDATE = getCheckbox("LDATE");
		//if(CbxLDATE==null) CbxLDATE=new JCheckBox();
		CbxKeineEinheit = getCheckbox("keine_Einheit");
		if(CbxKeineEinheit==null) CbxKeineEinheit=new AUCheckBox();
        CbxFastOut = getCheckbox("fast out");
		if(CbxFastOut==null) CbxFastOut=new AUCheckBox();
                //CbxFill = getCheckbox("vorfuellen");
		//if(CbxFill==null) CbxFill=new AUCheckBox();
		//CbxLeerLassen = getCheckbox("LeerLassen");
		//if(CbxLeerLassen==null) CbxLeerLassen=new JCheckBox();
		//CbxSQL = getCheckbox("SQL");
		//if(CbxSQL==null) CbxSQL=new JCheckBox();
		CbxSpeichermeldung = getCheckbox("Speichermeldung");
		if(CbxSpeichermeldung==null) CbxSpeichermeldung=new AUCheckBox();
                CbxTestFenster = getCheckbox("TestFenster");
		if(CbxTestFenster==null) CbxTestFenster=new AUCheckBox();
                CbxAutoEdit = getCheckbox("AutoEdit");
		if(CbxAutoEdit==null) CbxAutoEdit=new AUCheckBox();
                CbxFormatSortiert = getCheckbox("FormatSortiert");
		if(CbxFormatSortiert==null) CbxFormatSortiert=new AUCheckBox();
		//CbxAutoInit = getCheckbox("AutoInit");
		//if(CbxAutoInit==null) CbxAutoInit=new AUCheckBox();
                g.BtnAdd(getButton("Benutzerpasswort"),"Benutzerpasswort",AL);
                BtnMPassword=g.BtnAdd(getButton("Mandantpasswort"),"Mandantpasswort",AL);
                //BtnClearCache=
                g.BtnAdd(getButton("ClearCache"),"ClearCache",AL);
                //if(BtnClearCache==null) BtnClearCache=new JButton();
                CbxLizenzFrei = getCheckbox("lizenzfrei");
                if (CbxLizenzFrei!=null && (!g.isLogAll()))
                  CbxLizenzFrei.setVisible(false);
                CbxFixieren = getCheckbox("fix");
                CbxFehlende = getCheckbox("fehlendes");
		if(CbxFehlende==null) CbxFehlende=new AUCheckBox();
//                CbxSQ = getCheckbox("Speichernfrage");
//                if(CbxSQ==null) CbxSQ=new AUCheckBox();
                Rad_h_auto = getRadiobutton("h_auto");
                if(Rad_h_auto==null) Rad_h_auto=new JRadioButton();
                Rad_h_auto.setSelected(true);
                Rad_h_dez = getRadiobutton("h_dez");
                if(Rad_h_dez==null) Rad_h_dez=new JRadioButton();
                Rad_h_min = getRadiobutton("h_min");
                if(Rad_h_min==null) Rad_h_min=new JRadioButton();
                Rad_h_dez.setSelected(g.bH_dez);
                Rad_h_min.setSelected(g.bH_min);
                CbxDefBezeichnung = getCheckbox("DefBezeichnung");
		if(CbxDefBezeichnung==null) CbxDefBezeichnung=new AUCheckBox();
        CbxShowSize = getCheckbox("show_size");
		if(CbxShowSize==null) CbxShowSize=new AUCheckBox();
        CbxStdSize = getCheckbox("Standardgroesse");
		if(CbxStdSize==null) CbxStdSize=new AUCheckBox();
//		CbxOriginal = getCheckbox("Original2");
//		if(CbxOriginal==null) CbxOriginal=new AUCheckBox();
		CbxDefShow = getCheckbox("DefShow");
		if(CbxDefShow==null) CbxDefShow=new AUCheckBox();
                //CbxView2 = getCheckbox("View2");
		//if(CbxView2==null) CbxView2=new AUCheckBox();
                //CbxInsert2 = getCheckbox("Insert2");
		//if(CbxInsert2==null) CbxInsert2=new AUCheckBox();
        CbxNurJeder = getCheckbox("nur Jeder");
		if(CbxNurJeder==null) CbxNurJeder=new AUCheckBox();
        CbxNurStart = getCheckbox("nur_Startformular");
		if(CbxNurStart==null) CbxNurStart=new AUCheckBox();
//		CbxJavaFX = getCheckbox("JavaFX");
//		if(CbxJavaFX==null) CbxJavaFX=new AUCheckBox();
//		CbxShowStyle = getCheckbox("show Style");
//		if(CbxShowStyle==null) CbxShowStyle=new AUCheckBox();
//		CbxAlert = getCheckbox("Alert");
//		if(CbxAlert==null) CbxAlert=new AUCheckBox();
		CbxBeep = getCheckbox("Beep");
		if(CbxBeep==null) CbxBeep=new AUCheckBox();
//		CbxKeinCss = getCheckbox("keinCss");
//		if(CbxKeinCss==null) CbxKeinCss=new AUCheckBox();
//		CbxCenterStage = getCheckbox("centerStage");
//		if(CbxCenterStage==null) CbxCenterStage=new AUCheckBox();

		JPanel Pnl_TxtBenutzer = getFrei("TxtBenutzer");
		JPanel Pnl_TxtComputer = getFrei("TxtComputer");
		JPanel Pnl_Combo_Sprache = getFrei("Combo Sprache");
		JPanel Pnl_Combo_Land = getFrei("Combo Land");
//		JPanel Pnl_Combo_Waehrung = getFrei("Combo Waehrung");
        JPanel Pnl_Schrift = getFrei("SchriftAllgemein");
		JPanel Pnl_Mandant = getFrei("Mandant");
        JPanel Pnl_Look = getFrei("Look");
//        JPanel Pnl_Css = getFrei("Css");
//        JPanel Pnl_Style = getFrei("Style");
        if (!g.Def())
        {
        	showLabel("Look",false);
        	showLabel("Css",false);
        	showLabel("Style",false);
        }

		if(Pnl_TxtBenutzer!=null)
		{
			//Pnl_TxtBenutzer.setLayout(new BorderLayout(2,2));
			//Pnl_TxtBenutzer.add(EdtBenutzer);
                        g.addComp(Pnl_TxtBenutzer,EdtBenutzer);
		}
		if(Pnl_TxtComputer!=null)
		{
			//Pnl_TxtComputer.setLayout(new BorderLayout(2,2));
			//Pnl_TxtComputer.add("Center",EdtComputer);
                        g.addComp(Pnl_TxtComputer,EdtComputer);
		}
		if(Pnl_Combo_Sprache!=null)
		{
			//Pnl_Combo_Sprache.setLayout(new BorderLayout(2,2));
			//Pnl_Combo_Sprache.add("Center",CboSprache);
                        g.addComp(Pnl_Combo_Sprache,CboSprache);
		}
		if(Pnl_Combo_Land!=null)
		{
			//Pnl_Combo_Land.setLayout(new BorderLayout(2,2));
			//Pnl_Combo_Land.add("Center",CboLand);
                        g.addComp(Pnl_Combo_Land,CboLand);
		}
//		if(Pnl_Combo_Waehrung!=null)
//		{
//			//Pnl_Combo_Waehrung.setLayout(new BorderLayout(2,2));
//			//Pnl_Combo_Waehrung.add("Center",CboWaehrung);
//                        g.addComp(Pnl_Combo_Waehrung,CboWaehrung);
//		}
//		if(Pnl_Css!=null && g.Def())
//			g.addComp(Pnl_Css,EdtCss);
        if(Pnl_Schrift!=null)
            g.addComp(Pnl_Schrift,EdtSchrift);
                
		if(Pnl_Mandant!=null)
		{
			Pnl_Mandant.setLayout(new BorderLayout(2,2));
                        if(BtnMPassword==null)
                        {
                          BtnMPassword = g.getButton("Mandantpasswort","Mandantpasswort",AL);
                          Pnl_Mandant.add("West", BtnMPassword);
                        }
			Pnl_Mandant.add("Center",CboMandant);
                        if (CbxFixieren==null)
                        {
                          CbxFixieren = g.getCheckbox("fix");
                          Pnl_Mandant.add("East", CbxFixieren);
                        }
		}

                if(Pnl_Look!=null && g.Def())
                {
                  CboLook = new ComboSort(g);
                  int i=0;
                  TabLook=new Tabellenspeicher(g,new String[]{"Nr","Info"});
                  for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                  {
                    i++;
                    TabLook.addInhalt("Nr",i);
                    TabLook.addInhalt("Info",info);
                    CboLook.addItem(info.getName(),i,info.getName());
                  }
                  //if (g.Prog())
                  //  TabLook.showGrid();
                  g.progInfo("Look="+UIManager.getLookAndFeel().getName());
                  CboLook.setSelectedKennung(UIManager.getLookAndFeel().getName());
                  Pnl_Look.add(CboLook);
                  CboLook.addItemListener(new ItemListener ()
                  {
                    public void itemStateChanged(ItemEvent ev) {
                      if(ev.getStateChange() == ItemEvent.SELECTED)
                      {
                        TabLook.posInhalt("Nr",CboLook.getSelectedAIC());
                        UIManager.LookAndFeelInfo info=(UIManager.LookAndFeelInfo)TabLook.getInhalt("Info");
                        g.fixInfo("set "+info.getName());
                        try { UIManager.setLookAndFeel(info.getClassName()); }
                        catch (Exception e) { Static.printError("PersEinstellung.Pnl_Look:"+e); }
                        SwingUtilities.updateComponentTreeUI(thisFrame);
                      }
                    }
                  });
                }
//                if (Pnl_Style!=null && g.Def())
//                {
//                	CboStyle = new ComboSort(g,ComboSort.kein);
//                	CboStyle.addItem(g.getShow("modena"),1,"modena");
//                	CboStyle.addItem(g.getShow("caspian"),2,"caspian");
//                	CboStyle.setKein(true);
//                	CboStyle.setSelectedAIC(Static.iStyleTyp);
//                	Pnl_Style.add(CboStyle);
//                	CboStyle.addItemListener(new ItemListener ()
//                    {
//                      public void itemStateChanged(ItemEvent ev) {
//                        if(ev.getStateChange() == ItemEvent.SELECTED)
//                        {
//                        	Static.iStyleTyp=CboStyle.getSelectedAIC();
//                        }
//                      }
//                    });
//                }
	}

/*public static JMenu createLookAndFeelMenu(Component component) {
  JMenu result = new JMenu("Look and Feel");
  ButtonGroup group = new ButtonGroup();
  for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    JRadioButtonMenuItem item = new JRadioButtonMenuItem(info.getName());
    group.add(item);
    result.add(item);
    item.putClientProperty("INFO", info);
    item.putClientProperty("COMPONENT", component);
    item.setSelected(UIManager.getLookAndFeel().getName().equals(info.getName()));
    item.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent ie) {
        JRadioButtonMenuItem item =
          (JRadioButtonMenuItem)ie.getSource();
        if (item.isSelected()) {
          UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo)item.getClientProperty("INFO");
          Component component = (Component)item.getClientProperty("COMPONENT");
          try { UIManager.setLookAndFeel(info.getClassName()); }
          catch (Exception e) { e.printStackTrace(); }
          SwingUtilities.updateComponentTreeUI(component);
        }
      }
    });
  }
  return result;
}*/


	private boolean Ok()
	{
		boolean bOk=true;

		SQL Qry = new SQL(g);

		/*if(Pruefen())
                if(true)
		{*/

			//g.debugInfo(sBenutzerKennung+" - "+EdtBenutzer.getText()+"\n"+iSprache+" - "+iAIC_Sprache+"\n"+iLand+" - "+iAIC_Land);

			if(CboSprache.Modified() || CboLand.Modified())
			{
				int iAIC_Land = CboLand.getSelectedAIC();

				//if(!sBenutzerKennung.equals(EdtBenutzer.getText()))
				//	Qry.add("Kennung",EdtBenutzer.getText());
				if(CboLand.Modified())
				{
					if(iAIC_Land!=0)
						Qry.add("AIC_Land",iAIC_Land);
					else
						Qry.add("AIC_Land","");
					g.setLand(iAIC_Land,-1);
					CboLand.setAktAIC(iAIC_Land);
				}
				if(CboSprache.Modified())
				{
					int iAIC_Sprache = CboSprache.getSelectedAIC();
					if(iAIC_Sprache!=0)
						Qry.add("AIC_Sprache",iAIC_Sprache);
					else
						Qry.add("AIC_Sprache","");
					g.setSprache(iAIC_Sprache,iAIC_Land);
					CboSprache.setAktAIC(iAIC_Sprache);
				}

				if(!Qry.update("Benutzer",g.getBenutzer()))
				/*{
					sBenutzerKennung=EdtBenutzer.getText();
				}
				else*/
					Static.printError("PersEinstellungen.Ok(): Kennung, Sprache und Land konnte in der Tabelle Benutzer nicht gespeichert werden!!!");
			}

                        int iInfoNeu=(CbxInfo1.isSelected()?Transact.INFO1:0)+(CbxInfo2.isSelected()?Transact.INFO2:0)+(CbxInfo3.isSelected()?Transact.INFO3:0)+(CbxInfo4.isSelected()?Transact.EXEC:0)+
                            (CbxFehlende.isSelected()?Transact.FEHLENDE:0)+(CbxInfo.isSelected()?Transact.INFO:0)+(Transact.iInfos&Transact.TERMINAL)+(CbxClock.isSelected()?Transact.CLOCK:0)+
                            (CbxNoCache.isSelected()?Transact.NO_CACHE:0)/*+(CbxFill.isSelected()?Transact.FILL:0)*/+(CbxNoInfo.isSelected()?Transact.NO_INFO:0)+(CbxNoSpeed.isSelected()?Transact.NO_SPEED:0)+
                            ((EdtSchrift.getIntValue()/10)-5)*0x1000+(CbxClockSub.isSelected()?Transact.CLOCKSUB:0)+(Transact.iInfos&Transact.MEHRMALS)+(CbxFastOut.isSelected()?Transact.FAST_OUT:0)+
                            (CbxNoAServer.isSelected()?Transact.NO_ASERVER:0)+(CbxNoFixTest.isSelected()?Transact.NO_FIX:0)+(CbxClock3.isSelected()?Transact.CLOCK3:0)+(CbxMal2.isSelected()?Transact.MAL2:0);
//                        Static.bFixTest=!CbxNoFixTest.isSelected();
                        //g.progInfo("iInfoNeu="+iInfoNeu+", g.iInfos="+g.iInfos);
            Static.bKeineEinheit=CbxKeineEinheit.isSelected();
            Static.bKeinLDate=CbxKeinLDate.isSelected();
			if(iInfoNeu !=Transact.iInfos)
			{
				//if(!sComputerKennung.equals(EdtComputer.getText()))
				//	Qry.add("Kennung",EdtComputer.getText());
				Qry.add("CBits",iInfoNeu);
				if(Qry.update("Computer",g.getComputer()))
				{
					//g.setInfo(CbxInfo.isSelected());
                                        Transact.iInfos=iInfoNeu;
                                        if ((iInfoNeu & Transact.NO_CACHE)>0)
                                          Static.iCache=Static.NIE;
					//sComputerKennung=EdtComputer.getText();
                                        g.LoadSchrift(false,g.getFontFaktor());
				}
				else
					Static.printError("PersEinstellungen.Ok(): Info konnte in der Tabelle Computer nicht gespeichert werden!!!");
			}
                        g.bLizenzFrei=CbxLizenzFrei!=null && CbxLizenzFrei.isSelected();

            Static.bX=CbxX.isSelected();
			if(g.Debug()!=CbxDebug.isSelected() /*|| Static.bLeerLassen!=CbxLeerLassen.isSelected()*/ || Static.bSpeichernAnzeigen!=CbxSpeichermeldung.isSelected()
                            || Static.bTestFenster!=CbxTestFenster.isSelected() || Static.bFormatSortiert!=CbxFormatSortiert.isSelected()// || EdtCss.Modified()// || CboWaehrung.Modified()
                            || g.bH_dez!=Rad_h_dez.isSelected() || g.bH_min!=Rad_h_min.isSelected() || CboMandant.Modified()
                            //|| Static.bJavaFX!=CbxJavaFX.isSelected() || Static.bShowStyle!=CbxShowStyle.isSelected() || Static.bAlert!=CbxAlert.isSelected() || Static.bUseDefaultCss==CbxKeinCss.isSelected() 
                            || Static.bBeep!=CbxBeep.isSelected() /*|| Static.bSpeichernFrage!=CbxSQ.isSelected() || Static.bOriginal!=CbxOriginal.isSelected()*/ || Static.bDefShow!=CbxDefShow.isSelected()
                            || Static.bDefBezeichnung!=CbxDefBezeichnung.isSelected() || Static.bShowSize!=CbxShowSize.isSelected() || Static.bStdSize!=CbxStdSize.isSelected() || Static.bNurStart!=CbxNurStart.isSelected())// || Static.bCenterStage!=CbxCenterStage.isSelected())
			{
				Parameter Para = new Parameter(g,"Pers. Einstellungen");
				//Para.setParameter("Benutzer","",CbxDebug.isSelected()?1:0,0,CbxLeerLassen.isSelected()?1:0,CbxSpeichermeldung.isSelected()?1:0,true,false);
				Para.setParameter("Benutzer",/*g.Def() ? EdtCss.getText():*/"",3,(CbxDebug.isSelected()?Global.DEBUG:0)/*+(CbxLeerLassen.isSelected()?g.LEER_LASSEN:0)*/+(CbxSpeichermeldung.isSelected()?Global.SAVE_SHOW:0)+
                                                  (CbxTestFenster.isSelected()?Global.TEST_FENSTER:0)+(CbxFormatSortiert.isSelected()?Global.FORMAT_SORTIERT:0)+
                                                  (Rad_h_dez.isSelected()?Global.H_DEZ:Rad_h_min.isSelected()?Global.H_MIN:0)+
                                                  (CbxDefBezeichnung.isSelected()?Global.DEFBEZ:0)+(CbxShowSize.isSelected()?Global.SHOWSIZE:0)+(CbxStdSize.isSelected()?Global.STDSIZE:0)+(CbxNurStart.isSelected()?Global.NURSTART:0)+
                                                  //(CbxJavaFX.isSelected() ? Global.FX:0)+(CbxShowStyle.isSelected() ? Global.STYLE:0)+(CbxAlert.isSelected() ? Global.ALERT:0)+(CbxKeinCss.isSelected() ? Global.NO_CSS:0)+
                                                  (CbxBeep.isSelected() ? Global.BEEP:0)+//(CbxSQ.isSelected() ? Global.SAVE_ASK:0)+(CbxCenterStage.isSelected() ? Global.CENTER_STAGE:0)+(CbxOriginal.isSelected()?Global.ORIGINAL:0)+
                                                  (CbxDefShow.isSelected()?Global.DEF_SHOW:0),g.iEuro/*CboWaehrung.getSelectedAIC()*/,CboMandant.getSelectedAIC(),true,false);
                                Para.free();
//                                Static.DirCss=EdtCss.getText();
//                                if (!Static.Leer(Static.DirCss)) g.setDefaultCss();
                                CboMandant.setAktAIC(CboMandant.getSelectedAIC());
//                                Static.bJavaFX=CbxJavaFX.isSelected();
//                                Static.bShowStyle=CbxShowStyle.isSelected();
//                                Static.bAlert=CbxAlert.isSelected();
                                Static.bBeep=CbxBeep.isSelected();
//                                Static.bUseDefaultCss=!CbxKeinCss.isSelected();
//                                Static.bSpeichernFrage=CbxSQ.isSelected();
//                                Static.bCenterStage=CbxCenterStage.isSelected();
                                Static.bStdSize=CbxStdSize.isSelected();
//                                Static.bOriginal=CbxOriginal.isSelected();
                                Static.bDefShow=CbxDefShow.isSelected();
			}
                        /*if (CbxView2.Modified() || CbxInsert2.Modified())
                        {
                          Parameter Para = new Parameter(g,"Datenbank");
                          Para.setParameter("Option","",(CbxView2.isSelected()?1:0)+(CbxInsert2.isSelected()?2:0),0,0,0,false,false);
                          Para.free();
                          Static.bView2=CbxView2.isSelected();
                          Static.bInsert2=CbxInsert2.isSelected();
                        }*/
                        //Static.bLeerLassen=CbxLeerLassen.isSelected();
                        g.setDebug(CbxDebug.isSelected());
                        Static.bSpeichernAnzeigen=CbxSpeichermeldung.isSelected();
                        Static.bTestFenster=CbxTestFenster.isSelected();
                        Static.bAutoEdit=CbxAutoEdit.isSelected();
                        Static.bFormatSortiert=CbxFormatSortiert.isSelected();
                        g.bH_dez=Rad_h_dez.isSelected();
                        g.bH_min=Rad_h_min.isSelected();
//                        if(CboWaehrung.Modified())
//                        {
//                          g.setWaehrung(CboWaehrung.getSelectedAIC());
//                          CboWaehrung.setAktAIC(CboWaehrung.getSelectedAIC());
//                        }
			//g.bAutoInit=CbxAutoInit.isSelected();
                        Static.bDefBezeichnung=CbxDefBezeichnung.isSelected();
                        Static.bShowSize=CbxShowSize.isSelected();
                        
                        Static.bNurJeder=CbxNurJeder.isSelected();
                        Static.bNurStart=CbxNurStart.isSelected();
                        /*if(CboWaehrung.Modified())
			{
				Parameter Para = new Parameter(g,"Pers. Einstellungen");
				Para.setParameter("Optionen","",CboWaehrung.getSelectedAIC(),0,0,0,true,false);
				Para.free();
				g.setWaehrung(CboWaehrung.getSelectedAIC());
				CboWaehrung.setAktAIC(CboWaehrung.getSelectedAIC());
			}*/

			if(CbxFixieren != null && CbxFixieren.isSelected())
			{
				Parameter Para = new Parameter(g,"Datenbank");
				Para.setParameter("Default","",CboMandant.getSelectedAIC(),0,0,0,false,false);
				Para.free();
			}
			int iM=CboMandant.getSelectedAIC();
			if(g.getMandant()!=iM)
            {
			  if (SQL.exists(g,"select aic_mandant from mandant where aic_mandant=? and Tod is null", ""+iM))
			  {
	              g.setMandant(iM);
	              for(int iPos=0;iPos<g.TabFormulare.size();)
	              {
	                Object Obj = g.TabFormulare.getInhalt("Formular",iPos);
	                if(Obj instanceof EingabeFormular)
	                {
	                  ((Formular)Obj).thisFrame.dispose();
	                  g.TabFormulare.clearInhalt(iPos);
	                }
	                /*else if(Obj instanceof Hauptschirm)
	                {
	                  ((Hauptschirm)Obj).dispose();
	                  //JButton Btn=TabFormular.posInhalt("Button",e.getSource());
	                  g.TabFormulare.clearInhalt();
	                }*/
	                else
	                  iPos++;
	              }
	              TCalc.TabCalc=null;
			  }
			  else
				  g.fixtestError("Mandant "+CboMandant.getSelectedBezeichnung()+" bereits Tod");
            }
			//Qry.close();
		/*}
		else
		{
			new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("KennungVorhanden");
			bOk=false;
		}*/

		Qry.free();

		return(bOk);
	}

	/*private boolean Pruefen()
	{
		boolean bOk=true;

		SQL Qry = new SQL(g);
		if(!sBenutzerKennung.equals(EdtBenutzer.getText()))
			if(Qry.open("SELECT AIC_Benutzer FROM Benutzer WHERE Kennung='"+EdtBenutzer.getText()+"' and geloescht is null AND AIC_Mandant="+g.getMandant()))
                        {
                          if (!Qry.eof())
                            bOk = Qry.getI("AIC_Benutzer") == g.getBenutzer();
                        }
			else
				Static.printError("PersEinstellungen.Pruefen(): Auf Benutzer kann nicht zugegriffen werden!!!");

		if(bOk && !sComputerKennung.equals(EdtComputer.getText()))
			if(Qry.open("SELECT AIC_Computer FROM Computer WHERE Kennung='"+EdtComputer.getText()+"' and geloescht is null"))
                        {
                          if (!Qry.eof())
                            bOk = Qry.getI("AIC_Computer") == g.getComputer();
                        }
			else
				Static.printError("PersEinstellungen.Pruefen(): Auf Computer kann nicht zugegriffen werden!!!");

		Qry.free();

		return bOk;
	}*/

// add your data members here
private static PersEinstellungen Self=null;
private Global g;

private Text EdtBenutzer = new Text("",20,Text.KENNUNG);
private Text EdtComputer = new Text("",12,Text.KENNUNG);
private ComboSort CboSprache;
private ComboSort CboLand;
//private ComboSort CboWaehrung;
private ComboSort CboLook;
//private ComboSort CboStyle;
private Tabellenspeicher TabLook=null;
private SpinBox EdtSchrift;
//private Text EdtCss = new Text("",100); 
private AUCheckBox CbxInfo;
private AUCheckBox CbxInfo1;
private AUCheckBox CbxInfo2;
private AUCheckBox CbxInfo3;
private AUCheckBox CbxInfo4;
private AUCheckBox CbxX;
private AUCheckBox CbxClock;
private AUCheckBox CbxClockSub;
private AUCheckBox CbxClock3;
private AUCheckBox CbxMal2;
private AUCheckBox CbxDebug;
private AUCheckBox CbxNoCache;
private AUCheckBox CbxNoAServer;
//private AUCheckBox CbxFill;
private AUCheckBox CbxNoInfo;
private AUCheckBox CbxNoFixTest;
private AUCheckBox CbxNoSpeed;
private AUCheckBox CbxKeinLDate;
//private JCheckBox CbxLDATE;
private AUCheckBox CbxKeineEinheit;
private AUCheckBox CbxFastOut;
//private JCheckBox CbxLeerLassen;
//private AUCheckBox CbxAutoInit;
//private JCheckBox CbxSQL;
private AUCheckBox CbxSpeichermeldung;
private AUCheckBox CbxTestFenster;
private AUCheckBox CbxAutoEdit;
private AUCheckBox CbxFormatSortiert;
private AUCheckBox CbxFehlende;
//private AUCheckBox CbxSQ;
private AUCheckBox CbxDefBezeichnung;
private AUCheckBox CbxShowSize;
private AUCheckBox CbxStdSize;
//private AUCheckBox CbxOriginal;
private AUCheckBox CbxDefShow;
//private AUCheckBox CbxInsert2;
//private AUCheckBox CbxView2;
private AUCheckBox CbxNurJeder;
private AUCheckBox CbxNurStart;
//private AUCheckBox CbxJavaFX;
//private AUCheckBox CbxShowStyle;
//private AUCheckBox CbxAlert;
//private AUCheckBox CbxKeinCss;
private AUCheckBox CbxBeep;
//private AUCheckBox CbxCenterStage;

//private JButton BtnBPassword;
private JButton BtnMPassword;
//private JButton BtnClearCache;
private ComboSort CboMandant;
private AUCheckBox CbxFixieren;
private AUCheckBox CbxLizenzFrei;

private JRadioButton Rad_h_auto;
private JRadioButton Rad_h_dez;
private JRadioButton Rad_h_min;


private JButton BtnOk;
private JButton BtnAbbruch;
private ActionListener AL;

//private String sBenutzerKennung;
//private String sComputerKennung;
//private int iLand;
//private int iSprache;
}

