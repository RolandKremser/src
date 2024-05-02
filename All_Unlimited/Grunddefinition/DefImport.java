/*
    All_Unlimited-Grunddefinition-DefImport.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.Vector;
import java.util.zip.ZipEntry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.AUZip;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Print.Drucken;

import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;

import All_Unlimited.Allgemein.GroupBox;
import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Import;
import All_Unlimited.Hauptmaske.Calc;

import java.io.File;

public class DefImport extends javax.swing.JDialog
{

  /**
	 *
	 */
	private static final long serialVersionUID = -3234784983633888678L;
public static DefImport start(Global rg,JDialog Dlg)
  {
    return This == null ? new DefImport(rg,Dlg) : This;
  }

  private DefImport(Global glob,JDialog Dlg)
  {
    super(new JFrame(), glob.getBegriffS("Show", "Import"), true);
    g = glob;
    bTest2=Static.bTest;
    Static.bTest=false;
    Build();
    if (Dlg != null)
    	Static.centerComponent(this, Dlg);
    This=this;
  }

  public void show2(JDialog rFrmOption)
  {
	  if (!Message.SecCheck(g,rFrmOption,"DefImport-Check","FS7615"))
	    {
	    	dispose();
	    	return;
	    }
	    
	//g.checkConnects(false);
	// int iLog=SQL.getInteger(g,"select count(*) from logging where aus is null");
    FrmOption=rFrmOption;
	// g.fixtestError("Eingeloggt aktuell: "+iLog);
	iPU=g.getPU();	
	if (iPU==0)
	{
		g.printError("Update-Parameter nicht gefunden");
		System.exit(10);
	}
	Tabellenspeicher Tab=Reinigung.Eingeloggt(g,0);
	if (Tab.size()==0)
	// if(Reinigung.Ausgeloggt(g,FrmOption,0))
		super.setVisible(true);
	else
	{
		if (new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,FrmOption,g,30).showDialog("checkConnects2",Tab)== Message.YES_OPTION)
		{
			g.checkConnects(false);
			Tab.refresh(g);
			int pane = new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,FrmOption,g,30).showDialog("Update_einspielen",Tab);
			if(pane == Message.YES_OPTION) //  && Prozesse.checkDeaktiv(g,false,false,FrmOption))
			{
				g.fixtestError("Update_einspielen mit Ja beantwortet");
				if (!Static.Leer(AClient.sAServerSoll))
					AClient.send_AServer("deaktiv",g); // wenn AServer eingeloggt

				g.exec("Update parameter set "+g.int1()+"=2 where aic_parameter=" + iPU);
				g.fixInfo("Rauswurf aktiviert");
				Global.iInfos=Global.iInfos|Global.ALLEIN;
				g.exec("Update computer set cbits="+Global.iInfos+" where aic_computer="+g.getComputer());
				if (!Static.Leer(Static.sWeb))
					g.sendWebDB("logoutDB",null,null);	
				super.setVisible(true);	
			}			
		}
	}
	if (BtnOk != null) BtnOk.setEnabled(true);
    // AClient.send_AServer("deaktiv",g);   
    // if(Reinigung.Ausgeloggt(g,FrmOption,0))
	// {
	// 	iPU=g.getPU();
	// 	if (iPU==0)
	// 	{
	// 		g.printError("Update-Parameter nicht gefunden");
	// 		System.exit(10);
	// 		// Parameter Para = new Parameter(g,"Datenbank");
	// 		// Para.setParameter("Update","",5,0,0,0,false,false);
	// 		// // g.fixInfo("Status angelegt!");
	// 		// iPU=SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
	// 	}
	// 	int iStatus=SQL.getInteger(g,"select "+g.int1()+" from parameter where aic_parameter=" + iPU);
	// 	boolean bOK=iStatus==1 ? new Message(Message.YES_NO_OPTION,FrmOption,g,0).showDialog("Update weiter")==Message.YES_OPTION : true;
	// 	if(bOK)
	// 	{
            
	// 		g.sendWebDB("logoutDB",null,null);
			
    //                     if (BtnOk != null)
    //                       BtnOk.setEnabled(true);
	// 		super.setVisible(true);
	// 	}
	// }
  }

private void Build()
{
	BtnOk = g.getButton("Ok");
        BtnOk.setEnabled(Static.bLocal || Static.bNotfall);
	JButton BtnAbbruch = g.getButton("Abbruch");
        //JButton BtnChange = g.getButton("Change");
        //JButton BtnView = g.getButton("View2");
        //BtnView.setEnabled(Static.bView2);
	EdtPfad = new FileEingabe(g,false);

	TxtProt = new AUTextArea(g,0);
	TxtProt.setEditable(false);

	final AUCheckBox CbxBezeichnung = g.getCheckbox("Bezeichnungen",true);
	final AUCheckBox CbxMemo = g.getCheckbox("Memos",true);
	final AUCheckBox CbxBild = g.getCheckbox("Bilder",true);
        final AUCheckBox CbxTooltip = g.getCheckbox("Tooltip2",true);
        final AUCheckBox CbxAbfragen = g.getCheckbox("alle Abfragen2");
        final AUCheckBox CbxModelle = g.getCheckbox("alle Modelle");
        final AUCheckBox CbxInfo= g.getCheckbox("auflisten");
        final AUCheckBox CbxANR= g.getCheckbox("ANR",false);
        //CbxView2= g.getCheckbox("View2",Static.bView2);
        final AUCheckBox CbxNurZeigen = g.getCheckbox("nur zeigen");
	    final AUCheckBox CbxFormulare = g.getCheckbox("Formulare",false);
        final AUCheckBox CbxkeinANR= g.getCheckbox("kein ANR",false);
        final AUCheckBox CbxDelAll= g.getCheckbox("keine Fremdsprache",false);
        final AUCheckBox CbxSR= g.getCheckbox("Stammreinigung",false);
        final AUCheckBox CbxkeineStamm= g.getCheckbox("keine Stamm",false);
		final AUCheckBox CbxCleanTM=g.getCheckbox("cleanTooltip",false);
		final AUCheckBox CbxStatus0=g.getCheckbox("Status0",true);

        Parameter Para=new Parameter(g,"DefImport");
        String s=Para.getParameter("Option",false,false);
        if (Para.bGefunden)
        {
          if (s!=null && s.length()>3 && s.endsWith(".up") && s.indexOf(File.separator)>-1)
            s=s.substring(0,s.lastIndexOf(File.separator));
          EdtPfad.setValue(s);


            CbxBezeichnung.setSelected((Para.int1&1)>0);
            CbxMemo.setSelected((Para.int1&2)>0);
            CbxBild.setSelected((Para.int1&4)>0);
            CbxTooltip.setSelected((Para.int1&8)>0);
            //CbxAbfragen.setSelected((Para.int1&1<<4)>0);
            //CbxModelle.setSelected((Para.int1&1<<5)>0);
            CbxInfo.setSelected((Para.int1&1<<6)>0);
            CbxANR.setSelected((Para.int1&1<<7)>0);
            CbxNurZeigen.setSelected((Para.int1&1<<8)>0);
            //CbxFormulare.setSelected((Para.int1&1<<9)>0);
            //CbxkeinANR.setSelected((Para.int1&1<<10)>0);
            CbxDelAll.setSelected((Para.int1&1<<11)>0);
            CbxSR.setSelected((Para.int1&1<<12)>0);
            //CbxkeineStamm.setSelected((Para.int1&1<<13)>0);
        }
        CbxkeineStamm.setSelected(g.BasisPort());

	/*CbxAbfragen = g.getCheckbox("Abfragen");
	CbxModelle = g.getCheckbox("Modelle");
	CbxEigenschaft = g.getCheckbox("Eigenschaften");
	CbxStammtyp = g.getCheckbox("Stammtyp");
	CbxBewegungstyp = g.getCheckbox("Bewegungstyp");

	CbxEigenschaft.setSelected(true);
	CbxMemo.setSelected(true);
	CbxBild.setSelected(true);
	CbxBewegungstyp.setSelected(true);
	CbxStammtyp.setSelected(true);
	CbxModelle.setSelected(true);

	if(!g.Def())
	{
		CbxBezeichnung.setSelected(true);
		CbxFormulare.setSelected(true);
		CbxAbfragen.setSelected(true);

		CbxBezeichnung.setEnabled(false);
		CbxMemo.setEnabled(false);
		CbxBild.setEnabled(false);
		CbxFormulare.setEnabled(false);
		CbxAbfragen.setEnabled(false);
		CbxModelle.setEnabled(false);
		CbxEigenschaft.setEnabled(false);
		CbxStammtyp.setEnabled(false);
		CbxBewegungstyp.setEnabled(false);
	}*/

	getContentPane().setLayout(new BorderLayout(2,2));

	JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
        //Pnl.add(BtnChange);
	Pnl.add(BtnOk);
	Pnl.add(BtnAbbruch);
        getContentPane().add("South",Pnl);

	JPanel Pnl2 = new JPanel(new BorderLayout(2,2));
        JScrollPane Scroll=new JScrollPane(Pnl2);
        Scroll.setBorder(new EmptyBorder(new Insets(5,5,0,2)));
	getContentPane().add("North",Scroll);
        JPanel PnlN=new JPanel(new BorderLayout());
        JLabel Lbl=new JLabel(g.getBegriffS("Show","Pfad:"));
        Lbl.setFont(Global.fontStandard);
        PnlN.add("North",Lbl);
	Pnl2.add("West",PnlN);
	Pnl2.add("Center",EdtPfad);

	Pnl = new JPanel(new GridLayout(0,2,2,2));
	Pnl.add(CbxBezeichnung);
	Pnl.add(CbxMemo);
	Pnl.add(CbxBild);
        Pnl.add(CbxTooltip);
        Pnl.add(CbxAbfragen);
        //CbxAbfragen.setEnabled(g.Def());
        Pnl.add(CbxModelle);
        Pnl.add(CbxFormulare);
        //Pnl.add(new JLabel());
        Pnl.add(CbxANR);
        //Pnl.add(CbxView2);
        Pnl.add(CbxInfo);
        Pnl.add(CbxNurZeigen);
        Pnl.add(CbxDelAll);
	Pnl.add(CbxkeinANR);
        Pnl.add(CbxSR);
        Pnl.add(CbxkeineStamm);
		Pnl.add(CbxCleanTM);
		Pnl.add(CbxStatus0);
	//Pnl.add(CbxAbfragen);
	/*Pnl.add(CbxModelle);
	Pnl.add(CbxEigenschaft);
	Pnl.add(CbxStammtyp);
	Pnl.add(CbxBewegungstyp);*/
	Pnl2.add("South",Pnl);
        GroupBox Group = new GroupBox(g.getBegriffS("Label","Fehlermeldungen"));
        Group.add(TxtProt);
	getContentPane().add("Center",Group);

        ActionListener AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
        	  if (g.Disconnected())
      			g.connect(null);
            Parameter Para=new Parameter(g,"DefImport");
            String sFile=EdtPfad.getValue();
            if (sFile.length() > 250)
            {
              g.fixInfo("DefImport: Datei gekürzt von " + sFile.length() + " auf 250 Zeichen");
              sFile = sFile.substring(0, 250);
            }
            Para.setParameter("Option", sFile, (CbxBezeichnung.isSelected()?1:0)+(CbxMemo.isSelected() ? 2 : 0) + (CbxBild.isSelected() ? 4 : 0) + (CbxTooltip.isSelected() ? 8 : 0)
                              +(CbxAbfragen.isSelected()?1<<4:0)+(CbxModelle.isSelected()?1<<5:0)+(CbxInfo.isSelected()?1<<6:0)+(CbxANR.isSelected()?1<<7:0)
                              +(CbxNurZeigen.isSelected()?1<<8:0)+(CbxFormulare.isSelected()?1<<9:0)+(CbxkeinANR.isSelected()?1<<10:0)+(CbxDelAll.isSelected()?1<<11:0)
                              +(CbxSR.isSelected()?1<<12:0)+(CbxkeineStamm.isSelected()?1<<13:0)+(CbxStatus0.isSelected()?1<<14:0) ,0,0,0,false,false);
            Para.free();
			bCleanTM=CbxCleanTM.isSelected() && new Message(Message.YES_NO_OPTION,This,g,0).showDialog("Tooltips_entfernen")==Message.YES_OPTION;
            String s = ev.getActionCommand();
            if (s.equals("Ok"))
            {
                TxtProt.setText("");
                ((JButton)ev.getSource()).setEnabled(false);
                int iMessage=Message.NO_OPTION;
				if(!Reinigung.Ausgeloggt(g,FrmOption,0))
				{
					if (!Static.Leer(Static.sWeb))
						g.sendWebDB("logoutDB",null,null);
					
					//g.exec("Update computer set cbits="+Global.iInfos+" where aic_computer="+g.getComputer());
					if (SQL.getInteger(g, "select aic_computer from computer where"+g.bit("cbits", Global.ALLEIN))!=g.getComputer())
					{
						if (!Static.Leer(AClient.sAServerSoll))
							AClient.send_AServer("deaktiv",g);
						g.exec("Update parameter set " + g.int1() + "=3 where aic_parameter=" + iPU);
						g.fixtestError("deaktiv aus DefImport gesetzt");
					}
					int iLogN=SQL.getInteger(g, "select count(*) from logging where aus is null and aic_code is null");
					if (iLogN>0 && new Message(Message.YES_NO_OPTION,This,g,0).showDialog("alle_ausloggen")==Message.YES_OPTION)
					{
						int iAnz=g.exec("update logging set aus="+g.now()+",status=(status&"+Transact.WEBLOG+")+"+Transact.MANUELL+" where aus is null and aic_code is null and aic_computer<>"+g.getComputer());
						g.fixtestError(iAnz+" von DefImport ausgeloggt");
					}
					iLogN=SQL.getInteger(g, "select count(*) from logging where aus is null and aic_code is not null");
					if (iLogN>0 && new Message(Message.YES_NO_OPTION,This,g,0).showDialog("Prozesse_ausloggen")==Message.YES_OPTION)
					{
						int iAnz=g.exec("update logging set aus="+g.now()+",status=(status&"+Transact.WEBLOG+")+"+Transact.MANUELL+" where aus is null and aic_code is not null");
						g.fixtestError(iAnz+" Prozesse von DefImport ausgeloggt");
					}
				}
				if(!Reinigung.Ausgeloggt(g,FrmOption,0))
					g.fixtestError("noch jemand eingeloggt");					
                else if (AClient.UseAServer1())
                {
                  AClient.send_AServer("backup", g);
                  iMessage=Message.YES_OPTION;
                  Static.sleep(500);
                  String sSt=AClient.get_AServer("status2");
                  if (sSt.equals("-2"))
                    Static.printError("AServer nicht erreicht");
                  else while(sSt.equals("1"))
                  {
                    Static.sleep(500);
                    sSt=AClient.get_AServer("status2");
                  }
                  g.fixInfo("Backup erstellt");
                }
                else
                  iMessage=new Message(Message.YES_NO_OPTION,This,g,0).showDialog("Sicherungsfrage");
                if(iMessage==Message.YES_OPTION)
                {
                        setCursor(new Cursor(Cursor.WAIT_CURSOR));                       
                        StartPfad(g,EdtPfad.getValue(),This);
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                Static.bTest=bTest2;
                if(TxtProt.getValue().equals(""))
                  dispose();
            }
            else if (s.equals("Abbruch"))
            {
			  Entsperren(g,FrmOption);
              Static.bTest=bTest2;
              //g.initSI();
              g.fixtestInfo("DefImport vor dispose: "+Static.printZahl(Static.Mem(),11));
              dispose();
              g.fixtestInfo("DefImport nach dispose:"+Static.printZahl(Static.Mem(),11));
              System.gc();
              g.fixtestInfo("DefImport nach g.c.:   "+Static.printZahl(Static.Mem(),11));
              //g.initSI();
            }
            //else if (s.equals("Change"))
            //  Versionsupdate.getChange(g).showGrid("change");
          }
        };
        //g.BtnAdd(BtnChange,"Change",AL);
        g.BtnAdd(BtnOk,"Ok",AL);
        g.BtnAdd(BtnAbbruch,"Abbruch",AL);
        //g.BtnAdd(BtnView,"View",AL);

        setSize(380,350);
}

public static boolean Entsperren(Global g,JDialog Dlg)
{
	// if (g.Disconnected())
    //   	g.connect(null);
	// g.fixtestError("Entsperren");
	int iPU=g.getPU();
	String[] sStatus=new String[]{"aktiv","Update","alle_gesperrt","deaktiv","close","xx","einzeln"};
	int iStatus=SQL.getInteger(g,"select "+g.int1()+" from parameter where aic_parameter=" + iPU);
	String sX=iStatus<0 ? ""+iStatus:g.getShow(sStatus[iStatus]);
	if (iStatus!=0 && new Message(Message.YES_NO_OPTION,Dlg,g,0).showDialog("Status_ruecksetzen",new String[] {sX})==Message.YES_OPTION)
	{
		g.exec("Update parameter set " + g.int1() + "=0 where aic_parameter=" + iPU);
		if (AClient.UseAServer())
			AClient.send_AServer("aktiv",g);
		if (!Static.Leer(Static.sWeb))
			g.sendWebDB("okDB",null,null);
		if ((Global.iInfos&Global.ALLEIN)>0)
		{
			Global.iInfos-=Global.ALLEIN;
			g.exec("Update computer set cbits="+Global.iInfos+" where aic_computer="+g.getComputer());
		}
		return true;
	}
	else
		return false;
}

      private static void addInfo(Tabellenspeicher Tab,boolean bVorhanden,String sGruppe,String sDefBezeichnung,String sKennung)
      {
        String sArt=bVorhanden ? "update":"neu";
        //g.testInfo(sGruppe+" ("+sArt+") "+sDefBezeichnung+" ("+sKennung+")");
        if (Tab != null)
        {
          Tab.newLine();
          Tab.setInhalt("Gruppe",sGruppe);
          Tab.setInhalt("Art",sArt);
          Tab.setInhalt("DefBezeichnung",sDefBezeichnung);
          Tab.setInhalt("Kennung",sKennung);
        }
      }

/*private static void Meldung(Global g,JDialog FomDefImport,String s)
      {
        if(FomDefImport==null)
          g.fixInfo(s);
        else
          new Message(Message.ERROR_MESSAGE,FomDefImport,g,20).showDialog(s);
      }*/
      
private static boolean LadeParameter(Global g)
{
	/*bBezeichnung=CbxBezeichnung.isSelected();
    bMemo=CbxMemo.isSelected();
    bBild=CbxBild.isSelected();
    bTooltip=CbxTooltip.isSelected();
    bFormulare=CbxFormulare.isSelected();
    bAbfragen=CbxAbfragen.isSelected();
    bModelle=CbxModelle.isSelected();
    bInfo=CbxInfo.isSelected();
    bANR=CbxANR.isSelected();
    bNurZeigen=CbxNurZeigen.isSelected();
    bkeinANR=CbxkeinANR.isSelected();
    bDelAll=CbxDelAll.isSelected();
    bSR=CbxSR.isSelected();
    bKeineStamm=CbxkeineStamm.isSelected();*/
	Parameter Para=new Parameter(g,"DefImport");
    Para.getParameter("Option",false,false);
    if (Para.bGefunden)
    {
        bBezeichnung=(Para.int1&1)>0;
        bMemo=(Para.int1&2)>0;
        bBild=(Para.int1&4)>0;
        bTooltip=(Para.int1&8)>0;
        bFormulare=(Para.int1&1<<9)>0;
        bAbfragen=(Para.int1&1<<4)>0;
        bModelle=(Para.int1&1<<5)>0;
        bInfo=(Para.int1&1<<6)>0;
        bANR=(Para.int1&1<<7)>0;
        bNurZeigen=(Para.int1&1<<8)>0;
        bkeinANR=(Para.int1&1<<10)>0;
        bDelAll=(Para.int1&1<<11)>0;
        bSR=(Para.int1&1<<12)>0;
        bKeineStamm=(Para.int1&1<<13)>0;
		bStatus0=(Para.int1&1<<14)>0;
        Para.free();
        if (bFormulare) g.fixtestInfo("alle Formulare importieren");
        if (bAbfragen) g.fixtestInfo("alle Abfragen importieren");
        if (bModelle) g.fixtestInfo("alle Modelle importieren");
        if (!bKeineStamm) g.fixtestInfo("Stammreinigung");
        if (bDelAll) g.fixtestInfo("alles löschen");
        return true;
    }
    else
    	return false;
}

public static String StartPfad(Global g, String sPfad,JDialog FomDefImport)
{
  //g.fixInfo("Pfad="+sPfad);
	if (!LadeParameter(g))
	{
		Static.printError("Parameter für DefImport nicht gefunden!");
		return "Parameter nicht ladbar";
	}
  sZInfo=null;
  if (FomDefImport==null)
  {
    Static.clearError();
    bSR=true;
  }
  if (TabStammImp==null)
    TabStammImp=new Tabellenspeicher(g,new String[]{"Abfrage","File"});
  else
    TabStammImp.clearAll();
  /*if (TabBew==null)
    TabBew=new Tabellenspeicher(g,new String[]{"Bew","File","ANR"});
  else
    TabBew.clearAll();*/
  Tabellenspeicher TabUp=new Tabellenspeicher(g,new String[] {"Filename","Datum","size"});
  Stack<String> Sta=new Stack<String>();
  Sta.push(sPfad);
  Sta.push("up");
  TabUp.getDir(Sta,0);
  TabUp.sort("Datum", true);
  int iAnz=0;
  if (TabUp.size()>1 && bCleanTM)
  {
	diskInfo(g,g.exec("delete from Tooltip"),"x Tooltip gelöscht");
	diskInfo(g,g.exec("delete from daten_memo"),"x Memo gelöscht");
  }
  if (iPU==0)
    iPU=g.getPU();//SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");    
  iStatusOld=SQL.getInteger(g,"select "+g.int1()+" from parameter where aic_parameter=" + iPU);
  for(TabUp.moveFirst();!TabUp.out();TabUp.moveNext())
	if (StartFile(g,Static.cin(sPfad,File.separator)+TabUp.getS("Filename"),FomDefImport))
      iAnz++;
  /*File[] fil = new File(sPfad).listFiles();
  if (fil==null)
    return "!keine Datei vorhanden";
  int iAnz=0;
  for(int i=0;i<fil.length;i++)
    if (fil[i].getName().endsWith(".up"))
    {
      String sFile=fil[i].getName();
      //g.fixInfo("File"+i+" zum importieren:"+sFile);
      if (StartFile(g,Static.cin(sPfad,File.separator)+sFile,FomDefImport))
        iAnz++;
    }*/
  g.fixInfo(iAnz+" Dateien importiert");
  //if (AClient.checkAServer(g))
  if (Static.bX11 && g.TestPC())
  {
    if (TabStammImp.size() > 0)
      TabStammImp.showGrid("TabStammImp");
    //if (TabBew.size() > 0)
    //  TabBew.showGrid("TabBew");
  }
  if (g.Disconnected())
  	g.connect(null);
  if (bSR)
  {
    String sR2=Reinigung.StammCheck(g, false, 0);
    if (!sR2.equals("") && TxtProt!= null)
      TxtProt.setText(TxtProt.getValue() + "\n\n" + sR2);
  }
  if (iStatusOld==0 || bStatus0)
  {
	if (iStatusOld>0)
		g.exec("Update parameter set "+g.int1()+"=0 where aic_parameter=" + iPU);
  	if (FomDefImport!=null && AClient.UseAServer())
	    AClient.send_AServer("aktiv",g);
  	if (!Static.Leer(Static.sWeb))
	  g.sendWebDB("okDB",null,null);
	if ((Global.iInfos&Global.ALLEIN)>0)
	{
		Global.iInfos-=Global.ALLEIN;
		g.exec("Update computer set cbits="+Global.iInfos+" where aic_computer="+g.getComputer());
	}
  }
  if (FomDefImport==null)  
  {
    g.fixInfo("Import fertig");
    Static.sleep(5000); // mehr Zeit für Fremdtabellen-Reinigung bis zum ausloggen
  }
  sZInfo=null;
  int iFehler=FomDefImport==null ? Static.getError():TxtProt==null ? 0:TxtProt.Edt.getLineCount()-1;
  if (iFehler>0)
    return "! "+iFehler+" Fehler bei DefImport:\n"+(FomDefImport==null ? Static.getErrorString("\n"):TxtProt.getValue());
  else
    return ""+iAnz+" Dateien importiert";
}

private static boolean StartFile(Global g, String sFilename,JDialog FomDefImport)
{
	long lClock=Static.get_ms();
	// Verbinden, wenn noch nicht geschehen
	if (g.Disconnected())
			g.connect(null);
	    boolean bShow=bNurZeigen;
        Tabellenspeicher TabInfo= bInfo ? new Tabellenspeicher(g,new String[]{"Gruppe","Art","DefBezeichnung","Kennung","Fehler"}):null;
	//g.bSaveExec = true;
        String sFile1=sFilename.lastIndexOf(File.separator)>0 ? sFilename.substring(sFilename.lastIndexOf(File.separator)+1):sFilename;
	g.printExec("***DefImport: "+sFile1+" - Anfang");
	SQL Qry = new SQL(g);
	Tabellenspeicher TabFormulare = new Tabellenspeicher(g,new String[]{"Name","Outliner"});
	Tabellenspeicher TabModelle = new Tabellenspeicher(g,new String[]{"Name","Outliner"});
        Tabellenspeicher TabHauptschirme = new Tabellenspeicher(g,new String[]{"Name","Outliner"});
	//Lesen vom Zip-File
	AUZip Zip = new AUZip(sFilename,true);
        boolean bTest;

	ZipEntry ze1=Zip.getNextEntry();
        boolean bNormal=false;
        boolean bClean=false;
        boolean bMC=false;
        boolean bSpezial=false;
        boolean bWeb=false;
        String sProg=null;
        String sVer=null;
	if(ze1!=null)
	{
		int iVersion = ze1.getName().equals("Version.up")?Zip.ReadInteger().intValue():0;
		int iExpVersion = ze1.getName().equals("Version.up")?Zip.ReadInteger().intValue():0;
		bTest=iExpVersion>9 ? Zip.ReadBoolean().booleanValue():false;
                bNormal=iExpVersion>23 ? Zip.ReadBoolean().booleanValue():false;
                bClean=iExpVersion>34 ? Zip.ReadBoolean().booleanValue():false;
                bMC=iExpVersion>34 ? Zip.ReadBoolean().booleanValue():false;
                sProg=iExpVersion>51 ? Zip.ReadString():"";
                sVer=iExpVersion>51 ? Zip.ReadString():"";
                if (iExpVersion>52)
                {
                  g.setVonBis(Static.StrToDate(Zip.ReadString2()),Static.StrToDate(Zip.ReadString2()),false);
                }
                bWeb=iExpVersion>71 ? Zip.ReadBoolean().booleanValue():false;
                if (bNormal)
                  bSpezial=!SQL.exists(g,"select version from defimport where version is not null and"+g.bit("art",2));
                if (bDelAll)
                  bSpezial=true;
                if (g.ApplPort() || g.BasisPort())
                  bSpezial=false;
                if(iVersion!=Version.getVer() || iExpVersion!=DefExport.cstVersion)
                {
                  //Meldung(g,FomDefImport,"FalscheVersion");
                  Static.printError(sFile1+": Version falsch ("+iVersion+"/"+iExpVersion+" statt "+Version.getVer()+"/"+DefExport.cstVersion+")");
                  return false;
                }
                if (!sProg.equals("x") && !SQL.exists(g,"select aic_code from code where kennung='"+sProg+"'"))
                //"select distinct c.aic_code from lizenz join begriff b on aic_fremd=b.aic_begriff join code c on b.aic_code=c.aic_code"+
                            //" where c.kennung='"+sProg+"' and aic_tabellenname="+g.iTabBegriff))
                {
                  //Meldung(g,FomDefImport,"keineLizenz");
                  g.fixInfo(sFile1+": keine Lizenz für "+sProg);
                  return false;
                }
		if(bTest && !bShow)
		{
//                  if(FomDefImport==null || !g.TestPC())
//                  {
//                    //Meldung(g,FomDefImport,"TestVersionsverbot");
//                    Static.printError(sFile1+": Testupdate");
//                    return false;
//                  }
//                  else
                    if (FomDefImport == null || new Message(Message.YES_NO_OPTION,FomDefImport,g,0).showDialog("TestVersion")==Message.NO_OPTION)
                        return false;
		}
                if (g.ApplPort())
                {
                  bClean = false;
                  if (FomDefImport == null || new Message(Message.YES_NO_OPTION, FomDefImport, g, 0).showDialog("Appl_Import") == Message.NO_OPTION)
                    return false;
                }
                if (!bKeineStamm)
                {
                  int iUser=SQL.getInteger(g,"select aic_benutzer from Benutzer where "+g.bit("bits",Global.cstImport));
                  if (iUser>0)
                    g.fixtestInfo("Import-Benutzer="+iUser);
                  else
                  {
                    Static.printError(sFile1+": Import-Benutzer fehlt");
                    return false;
                  }
                }
	}
	else
	{
          //if(FomDefImport==null)
            Static.printError(sFile1+": keine Zip-Datei");
          //  g.fixInfo("No Zip");
          //else
          //  new Message(Message.ERROR_MESSAGE,FomDefImport,g,20).showDialog("No Zip");
          return false;
	}
        sZInfo="#importiere "+sFile1;
        //setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Gauge Gau=FomDefImport==null ? null:new Gauge(sFile1,0,8,"Lesen",g,false);
        g.start();
		g.exec("Update parameter set "+g.int1()+"=1 where aic_parameter=" + iPU);
        int iBits=0;

	String sR = Reinigung.SpaltenCheck(g, false);
          if (Reinigung.bFehler && FomDefImport!=null && TabStammImp==null)
          {
            int iMessage = new Message(Message.YES_NO_OPTION, FomDefImport, g,0).showDialog("Weiter", new String[] {sR});
            if (iMessage == Message.NO_OPTION)
            {
              //Para.setParameter("Update", "", -1, 0, 0, 0, false, false);
              g.rollback();
              g.exec("Update parameter set "+g.int1()+"=3 where aic_parameter=" + iPU);
              return false;
            }
            else
              lClock = Static.get_ms();
          }
        //String sFile1=file.getValue();
        if (sFilename.length()>255)
          sFilename=sFilename.substring(sFilename.length()-255);
        //g.testInfo("Filename="+sFile1);
        Qry.add("Filename",sFilename);
        Qry.add("AIC_COMPUTER",g.getComputer());
        Qry.addnow("ANFANG");
        Qry.add("Version",Version.getVer2());
        Qry.add("ART",(bTest?1:0)+(bNormal?2:0)+(bWeb?4:0));
        if (!sProg.equals("x"))
        {
          Qry.add("Programm", sProg);
          Qry.add("Prog_Ver", sVer);
        }
        g.iDefImport=Qry.insert("DefImport",true);
        int iFehlerStart=Static.getError();
        int iFehlerText=TxtProt==null ? 1:TxtProt.Edt.getLineCount();
        Tabellenspeicher TabBedingung=null;
        TabBegriff=null;
        Tabellenspeicher TabBewegungstyp=null;
        Tabellenspeicher TabStammtyp=null;
        Tabellenspeicher TabRolle=null;
        Tabellenspeicher TabEigenschaft=null;
        Tabellenspeicher TabAuswahl=null;
        //TabHomepage=null;
        Tabellenspeicher TabPlanung=null;
        Tabellenspeicher TabFormAbf=null;
        Tabellenspeicher TabFormModell=null;
        Tabellenspeicher TabFormBegriffe=null;
        Tabellenspeicher TabFormStt=null;
        //Tabellenspeicher TabDruckModell=null;
        Tabellenspeicher TabSprache=null;
        Tabellenspeicher TabFarbe=null;
        Tabellenspeicher TabSchrift=null;
        Tabellenspeicher TabAbschnitt=null;
        Tabellenspeicher TabRaster=null;
        Tabellenspeicher TabSpalte=null;
        Tabellenspeicher TabFix_Spalte=null;
        Tabellenspeicher TabFix_Bedingung=null;
        Tabellenspeicher TabSpalte_Zuordnung=null;
        Tabellenspeicher TabSpalte_Berechnung=null;
        Tabellenspeicher TabSubAbfragen=null;
        //TabNurBegriff=null;
        //TabNurEigenschaft=null;
        //TabNurStammtyp=null;
        //TabVersionsupdate=null;
        Tabellenspeicher TabHauptschirm=null;
        Tabellenspeicher TabStammAbf=null;
        JCOutliner OutFormular=null;
        JCOutliner OutModell=null;
        JCOutliner OutAbfrage=null;
        //OutDruck=null;

        //TabBeg2Hp=null;
        Tabellenspeicher TabBeg2Bew=null;
        Tabellenspeicher TabBeg2Schrift=null;
        Tabellenspeicher TabBeg2Stt=null;
        Tabellenspeicher TabBeg2Rolle=null;
        Tabellenspeicher TabStt2Stt=null;
        Tabellenspeicher TabRol2Rol=null;
        Tabellenspeicher TabRol2Stt=null;
        Tabellenspeicher TabForm2Stamm=null;
        Tabellenspeicher TabDruck_Zuordnung=null;
        Tabellenspeicher TabBew_Zuordnung=null;
        Tabellenspeicher TabEig_Zuordnung=null;
        Tabellenspeicher TabApplikation_Zuordnung=null;
        Tabellenspeicher TabGruppe_Zuordnung=null;
        Tabellenspeicher TabModul_Zuordnung=null;
        Tabellenspeicher TabModul_Zuordnung2=null;
        Tabellenspeicher TabKunde_Zuordnung=null;
        Tabellenspeicher TabDruck_Zuordnung2=null;
        Tabellenspeicher TabEig2Stt=null;
        Tabellenspeicher TabEig2Bew=null;
        Tabellenspeicher TabEig2Rolle=null;
        Tabellenspeicher TabEig2Eig=null;
        Tabellenspeicher TabStt2Eig=null;
        Tabellenspeicher TabStt2Status=null;
        Tabellenspeicher TabStt2Aufgabe=null;
        Tabellenspeicher TabForm2Eig=null;
        Tabellenspeicher TabForm2FXML=null;
        Tabellenspeicher TabStt_Zuordnung=null;
        Tabellenspeicher TabRol_Zuordnung=null;
        Tabellenspeicher TabSchrift2Farbe=null;
        Tabellenspeicher TabSchrift2Farbe2=null;
        Tabellenspeicher TabAbf2Mod=null;
        Tabellenspeicher TabAbf2Mod2=null;
        Tabellenspeicher TabAbf2Stamm=null;
        Tabellenspeicher TabMod2Abf=null;
        Tabellenspeicher TabMod2Abf2=null;
        Tabellenspeicher TabBeg2Code=null;
        Tabellenspeicher TabEig2Stamm=null;
        Tabellenspeicher TabProg2Eig=null;
        Tabellenspeicher TabProg2Stt=null;
        Tabellenspeicher TabBeg_AIC_Beg_Zuordnung=null;
        Tabellenspeicher TabAppl2_Zuordnung=null;
        Tabellenspeicher TabBeg2AIC_Beg_Zuordnung=null;
        Tabellenspeicher TabBeg3AIC_Beg_Zuordnung=null;
        Tabellenspeicher TabBezeichnung=null;
        Tabellenspeicher TabDaten_Bild=null;
        Tabellenspeicher TabDaten_Memo=null;
        Tabellenspeicher TabTooltip=null;
        Tabellenspeicher TabAbschlusstyp=null;
        Tabellenspeicher TabAbschlussdef=null;
        Tabellenspeicher TabStatus=null;
        Tabellenspeicher TabStatusZ=null; // Status-Zuordnung
        Tabellenspeicher TabBAT=null; // Begriff-Abschlusstyp-Zuordnung
        Tabellenspeicher TabBefehle=new Tabellenspeicher(g,new String[]{"neu","alt"});
        Vector<String> VecMD=null;
        Tabellenspeicher TabBewSet=new Tabellenspeicher(g,new String[]{"Art","Nr","Bew","Eig"});

	g.clockInfo("Start",lClock);
        if (bClean)
        {
          // Verlauf/Fehler
          diskInfo(g,g.exec("delete from Verlauf"),"x Verlauf gelöscht");
          diskInfo(g,g.exec("delete from befehl"),"x Befehlszeilen gelöscht");
          //diskInfo(g,g.exec("update befehl set def_aic_defverlauf=null where def_aic_defverlauf is not null"),"x DefVerlauf2 aus Befehl gelöscht");
          //diskInfo(g,g.exec("update befehl set aic_defverlauf=null where aic_defverlauf is not null"),"x DefVerlauf aus Befehl gelöscht");
          diskInfo(g,g.exec("delete from DefVerlauf"),"x DefVerlauf gelöscht");
          diskInfo(g,g.exec("delete from Fehler where aic_begriff is not null"),"x Fehler gelöscht");
          diskInfo(g,g.exec("delete from fensterpos"),"x fensterpos gelöscht");

		  diskInfo(g,g.exec("delete from Tooltip"),"x Tooltip gelöscht");
		  diskInfo(g,g.exec("delete from daten_memo"),"x Memo gelöscht");

          diskInfo(g,g.exec("delete from begriff_zuordnung"),"x begriff_zuordnung gelöscht");
          diskInfo(g,g.exec("delete from applikation_zuordnung"),"x applikation_zuordnung gelöscht");
          diskInfo(g,g.exec("delete from gruppe_zuordnung"),"x gruppe_zuordnung gelöscht");
          diskInfo(g,g.exec("delete from stammtyp_Zuordnung"),"x stammtyp_Zuordnung gelöscht");
          diskInfo(g,g.exec("delete from bew_Zuordnung"),"x bew_Zuordnung gelöscht");
          diskInfo(g,g.exec("delete from planung"),"x planung gelöscht");
          // Druck
          diskInfo(g,g.exec("delete from druck"),"x druck gelöscht");
          diskInfo(g,g.exec("delete from Druck_Zuordnung"),"x Druck_Zuordnung gelöscht");
          diskInfo(g,g.exec("delete from Abschnitt"),"x Abschnitt gelöscht");

          diskInfo(g,g.exec("delete from darstellung "),"x darstellung  gelöscht");
          //diskInfo(g,g.exec("delete from begriff where aic_begriffgruppe in (select aic_begriffgruppe from begriffgruppe where kennung='Button')"),"x Knöpfe gelöscht");
          //diskInfo(g,g.exec("delete from begriff where aic_begriffgruppe in (select aic_begriffgruppe from begriffgruppe where kennung='Checkbox')"),"x Checkboxen gelöscht");
          //diskInfo(g,g.exec("delete from begriff where aic_begriffgruppe in (select aic_begriffgruppe from begriffgruppe where kennung='Radiobutton')"),"x Radioknöpfe gelöscht");
          diskInfo(g,g.exec("delete from begriff where aic_begriffgruppe in (select aic_begriffgruppe from begriffgruppe where kennung='Show')"),"x Anzeigen gelöscht");
          diskInfo(g,g.exec("delete from begriff where aic_begriffgruppe in (select aic_begriffgruppe from begriffgruppe where kennung='Message')"),"x Meldungen gelöscht");
          diskInfo(g,g.exec("delete from begriff where aic_begriffgruppe in (select aic_begriffgruppe from begriffgruppe where kennung='Label')"),"x Texte gelöscht");
          diskInfo(g,g.exec("delete from daten_bild where aic_tabellenname="+Global.iTabBegriff),"x Begriff-Bilder gelöscht");
          diskInfo(g,g.exec("delete from formular "),"x formular gelöscht");
          diskInfo(g,g.exec("delete from modul"),"x modul gelöscht");
          // Hauptschirm-Einstellung
          Vector<Integer> VecHS=SQL.getVector("select aic_hauptschirm from hauptschirm where kennung<>'*' and kennung is not null",g);
          if (VecHS.size()>0)
          {
            diskInfo(g, g.exec("delete from ansicht where aic_hauptschirm" + Static.SQL_in(VecHS)), "x ansicht gelöscht");
            diskInfo(g, g.exec("delete from hauptschirm where aic_hauptschirm" + Static.SQL_in(VecHS)), "x hauptschirm gelöscht");
          }
          // Abfrage
          //Vector<Integer> VecAbf=SQL.getVector("select distinct aic_abfrage from land",g); // gibt es seit V 5.10 nicht mehr
          Vector<Integer> VecAbf=SQL.getVector("select distinct aic_abfrage from benutzer_zuordnung",g);
          SQL.addVector(VecAbf,"select distinct aic_abfrage from abschluss",g);
          SQL.addVector(VecAbf,"select distinct aic_abfrage from eigenschaft",g);
          SQL.addVector(VecAbf,"select distinct aic_abfrage from hauptschirm",g);
          SQL.addVector(VecAbf,"select distinct aic_abfrage from ansicht",g);
          //SQL.addVector(VecAbf,"select aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff where kennung is null",g);
          SQL.addVector(VecAbf,"select aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff where b.bits&0x1000000000000400>0",g);
          Vector<Integer> VecB=SQL.getVector("select b.aic_begriff from abfrage a join begriff b on a.aic_begriff=b.aic_begriff"+
            " where (tod is null or tod=0) and kennung is not null"+(VecAbf.size()==0?"":" and aic_abfrage not"+Static.SQL_in(VecAbf)),g);
          diskInfo(g, g.exec("update begriff set tod=1 where aic_begriff" + Static.SQL_in(VecB)),"x abfragen gelöscht");
          // Modelle
          diskInfo(g,g.exec("update abfrage set aic_modell=null where aic_modell is not null"),"x Modell aus Abfrage gelöscht");
          diskInfo(g,g.exec("update abfrage set mod_aic_modell=null where mod_aic_modell is not null"),"x Modell2 aus Abfrage gelöscht");
          //diskInfo(g,g.exec("update formular set aic_modell=null where aic_modell is not null"),"x Modell aus Formular gelöscht");
          diskInfo(g,g.exec("delete from modell"),"x Modelle gelöscht");

          diskInfo(g,g.exec("delete from bew_begriff where aic_begriff in (select aic_begriff from begriff b join begriffgruppe g on b.aic_begriffgruppe=g.aic_begriffgruppe where g.kennung in ('Frame','Druck'))"),"x bew_begriff gelöscht");
          Vector<Integer> Vec=SQL.getVector("select distinct aic_begriff from mandant",g);
          SQL.addVector(Vec,"select distinct aic_begriff from benutzergruppe",g);
          SQL.addVector(Vec,"select distinct aic_fremd from berechtigung"+g.join("tabellenname","berechtigung")+" where tabellenname.kennung='Begriff'",g);
          diskInfo(g,g.exec("delete from begriff where aic_begriffgruppe in (select aic_begriffgruppe from begriffgruppe "+
              "where kennung in('Planung','Druck','Modul','Kunde','Frame')) and aic_begriff not "+Static.SQL_in(Vec)),"x begriff gelöscht");
          g.exec("update begriff set importzeit=null");
          //Reinigung.ModellCheck(g,false);
          Reinigung.FremdtabellenLoeschen(g, null,null);
          g.clockInfo("Clean", lClock);
        }
	for(ZipEntry ze=Zip.getNextEntry();ze!=null && Zip.available()!=0;ze=Zip.getNextEntry())
	{
		String sFile = ze.getName();
		//g.printExec("*"+ze.getName());

		if(sFile.equals("Formular.up"))
		{
			OutFormular = Zip.ReadOutliner();
			String[] s = new String[]{"Kennung","zwingend"};
			OutFormular.setColumnButtons(s);
                        OutFormular.setNumColumns(s.length);
                        //OutFormular.folderChanged(OutFormular.getRootNode());
                        //OutFormular.addNotify();  // 30.7.2012
                        /*if (FomDefImport != null)
                        {
                          JFrame Frm = new JFrame("Formular.up");
                          Frm.getContentPane().add(OutFormular);
                          Frm.addNotify();
                        }*/
			/*Frm.setSize(800,800);
			Frm.setVisible(true);
			if(!g.Debug())
				Frm.setVisible(false);*/
		}
		else if(sFile.startsWith("Frame_"))
		{
			JCOutliner Out = Zip.ReadOutliner();
			TabFormulare.addInhalt("Outliner",Out);
			TabFormulare.addInhalt("Name",sFile.substring(6,sFile.indexOf(".up")));
			/*
			String[] s = new String[]{"Begriff","Begriffgruppe","Reihenfolge","x","y","w","h"};
			Out.setColumnButtons(s);
			Out.setNumColumns(s.length);
			JFrame Frm = new JFrame(ze.getName());
			Frm.getContentPane().add(Out);
			Frm.setSize(800,800);
			Frm.show();*/
		}
		else if(sFile.equals("Modell.up"))
		{
			OutModell = Zip.ReadOutliner();
			String[] s = new String[]{"Kennung","zwingend"};
			OutModell.setColumnButtons(s);
			OutModell.setNumColumns(s.length);
                        //OutModell.folderChanged(OutModell.getRootNode());
                        //OutModell.addNotify();  // 30.7.2012
                        /*if (FomDefImport != null)
                        {
                          JFrame Frm = new JFrame("Modell.up");
                          Frm.getContentPane().add(OutModell);
                          Frm.addNotify();
                        }*/
			/*Frm.setSize(800,800);
			Frm.setVisible(true);
			if(!g.Debug())
				Frm.setVisible(false);*/
		}
		else if(sFile.startsWith("Modell_"))
		{
			JCOutliner Out = Zip.ReadOutliner();
			TabModelle.addInhalt("Outliner",Out);
			TabModelle.addInhalt("Name",sFile.substring(7,sFile.indexOf(".up")));

			/*if(g.Prog() && FomDefImport != null)
			{
				Out.setNumColumns(6);
				JFrame Frm = new JFrame(sFile.substring(7,sFile.indexOf(".up")));
				Frm.getContentPane().add(Out);
				//Frm.show();
			}*/
		}
                else if(sFile.equals("Hauptschirm.up"))
                {
                  TabHauptschirm = ReadBSEH(g,Zip,new String[]{"Applikation","Kennung","DefBez","Prog","bits","abfrage","Stt","AIC"},new char[]{'S','S','S','S','I','S','S','N'});
                  //TabHauptschirm.showGrid("TabHauptschirm");
                      /*new Tabellenspeicher(g,new String[]{"Applikation","Kennung","bits","abfrage"});
                  TabHauptschirm.addInhalt("Applikation",Zip.ReadString());
                  TabHauptschirm.addInhalt("Kennung",Zip.ReadString());
                  TabHauptschirm.addInhalt("bits",Zip.ReadInteger());
                  TabHauptschirm.addInhalt("abfrage",Zip.ReadString());*/
                }
                else if(sFile.startsWith("Hauptschirm_"))
		{
                  JCOutliner Out = Zip.ReadOutliner();
                  TabHauptschirme.addInhalt("Outliner",Out);
                  TabHauptschirme.addInhalt("Name",sFile.substring(12,sFile.indexOf(".up")));

                  /*Out.setNumColumns(5);
                  JFrame Frm = new JFrame(sFile.substring(12,sFile.indexOf(".up")));
                  Frm.getContentPane().add(Out);
                  Frm.setVisible(true);*/
                }
		else if(sFile.equals("Abfrage.up"))
		{
			OutAbfrage = Zip.ReadOutliner();
		}
		else if(sFile.equals("Druck.up"))
		{
			Zip.ReadOutliner();
		}
		else if(sFile.equals("Bedingung.up"))
		{
			TabBedingung = new Tabellenspeicher(g,new String[]{"abfrage","vergleichswert","bbits","aic_bedingung","bed_aic_bedingung","vo","tabelle","new_aic_bedingung","gruppe","edit"});
			String sAbfrage = "";
			for(String sVergleich=Zip.ReadString();sVergleich!=null;sVergleich=Zip.ReadString())
			{
				if(sVergleich.equals("*!*"))
				{
					sAbfrage = Zip.ReadString();
					sVergleich = Zip.ReadString();
				}
				TabBedingung.addInhalt("abfrage",sAbfrage);
				TabBedingung.addInhalt("vergleichswert",sVergleich);
                                TabBedingung.addInhalt("bbits",Zip.ReadInteger());
				TabBedingung.addInhalt("aic_bedingung",Zip.ReadInteger());
				TabBedingung.addInhalt("bed_aic_bedingung",Zip.ReadInteger());
				TabBedingung.addInhalt("vo",Zip.ReadString());
				TabBedingung.addInhalt("tabelle",Zip.ReadString());
				TabBedingung.addInhalt("gruppe",Zip.ReadString());
				TabBedingung.addInhalt("new_aic_bedingung",null);
				TabBedingung.addInhalt("edit",new Boolean(false));
			}
		}
		else if(sFile.equals("Spalte.up"))
		{
			TabSpalte = new Tabellenspeicher(g,new String[]{"abfrage","aic_spalte","new_aic_spalte","kennung","sortiert","laenge","weblaenge","reihenfolge","nummer","bits","format","faktor","filter","farbe","Hierarchie","code_kennung1","code_kennung2","code_kennung3","code_kennung4",
					"bits2","bits3","bits4","min","max","x","y","w","h","Stil","ToggleSight","Icon","Farbe2","Sub1G","Sub1","Sub2G","Sub2","Sub3G","Sub3","subSpalte","stamm","stammtyp","stamm2","stt2","edit","del","Vorhanden"});
			String sAbfrage = "";
			for(String sKennung=Zip.ReadString();sKennung!=null;sKennung=Zip.ReadString())
			{
				if(sKennung.equals("*!*"))
				{
					sAbfrage = Zip.ReadString();
					sKennung = Zip.ReadString();
				}
				TabSpalte.addInhalt("abfrage",sAbfrage);
				TabSpalte.addInhalt("kennung",sKennung);
				TabSpalte.addInhalt("aic_spalte",Zip.ReadInteger());
				TabSpalte.addInhalt("sortiert",Zip.ReadInteger());
				TabSpalte.addInhalt("laenge",Zip.ReadInteger());
				TabSpalte.addInhalt("weblaenge",Zip.ReadInteger());
				TabSpalte.addInhalt("reihenfolge",Zip.ReadInteger());
				TabSpalte.addInhalt("nummer",Zip.ReadInteger());
				TabSpalte.addInhalt("bits",Zip.ReadInteger());
				TabSpalte.addInhalt("format",Zip.ReadString());
				TabSpalte.addInhalt("faktor",Zip.ReadDouble());
				TabSpalte.addInhalt("filter",Zip.ReadString());
                TabSpalte.addInhalt("farbe",Zip.ReadString());
				TabSpalte.addInhalt("code_kennung1",Zip.ReadString());
				TabSpalte.addInhalt("code_kennung2",Zip.ReadString());
				TabSpalte.addInhalt("code_kennung3",Zip.ReadString());
                TabSpalte.addInhalt("code_kennung4",Zip.ReadString());
                TabSpalte.addInhalt("bits2",Zip.ReadInteger());
                TabSpalte.addInhalt("bits3",Zip.ReadInteger());
                TabSpalte.addInhalt("bits4",Zip.ReadInteger());
                TabSpalte.addInhalt("min",Zip.ReadDouble());
                TabSpalte.addInhalt("max",Zip.ReadDouble());
                TabSpalte.addInhalt("x",Zip.ReadInteger());
                TabSpalte.addInhalt("y",Zip.ReadInteger());
                TabSpalte.addInhalt("w",Zip.ReadInteger());
                TabSpalte.addInhalt("h",Zip.ReadInteger());
                TabSpalte.addInhalt("Stil",Zip.ReadString());
                TabSpalte.addInhalt("ToggleSight",Zip.ReadString());
                TabSpalte.addInhalt("Icon",Zip.ReadString());
                TabSpalte.addInhalt("Farbe2",Zip.ReadString());
                TabSpalte.addInhalt("Sub1G",Zip.ReadString());
                TabSpalte.addInhalt("Sub1",Zip.ReadString());
                TabSpalte.addInhalt("Sub2G",Zip.ReadString());
                TabSpalte.addInhalt("Sub2",Zip.ReadString());
                TabSpalte.addInhalt("Sub3G",Zip.ReadString());
                TabSpalte.addInhalt("Sub3",Zip.ReadString());
				TabSpalte.addInhalt("subSpalte",Zip.ReadInteger());
				TabSpalte.addInhalt("stamm",Zip.ReadString());
				TabSpalte.addInhalt("stammtyp",Zip.ReadString());
                TabSpalte.addInhalt("Hierarchie",Zip.ReadString());
                TabSpalte.addInhalt("stt2",Zip.ReadString());
                TabSpalte.addInhalt("stamm2",Zip.ReadString());
				TabSpalte.addInhalt("new_aic_spalte",null);
				TabSpalte.addInhalt("edit",Boolean.FALSE);
                                TabSpalte.addInhalt("del",Boolean.FALSE);
				TabSpalte.addInhalt("Vorhanden",Boolean.FALSE);
			}
		}
		else if(sFile.equals("Spalte_Zuordnung.up"))
		{
			TabSpalte_Zuordnung = new Tabellenspeicher(g,new String[]{"aic_spalte","reihenfolge","stamm","stammtyp","eigenschaft","titel"});
			for(Integer iAIC_Spalte=Zip.ReadInteger();iAIC_Spalte!=null;iAIC_Spalte = Zip.ReadInteger())
			{
				TabSpalte_Zuordnung.addInhalt("aic_spalte",iAIC_Spalte);
				TabSpalte_Zuordnung.addInhalt("reihenfolge",Zip.ReadInteger());
				TabSpalte_Zuordnung.addInhalt("stamm",Zip.ReadString());
				TabSpalte_Zuordnung.addInhalt("stammtyp",Zip.ReadString());
                                TabSpalte_Zuordnung.addInhalt("eigenschaft",Zip.ReadString());
				TabSpalte_Zuordnung.addInhalt("titel",Zip.ReadString());
			}
		}
		else if(sFile.equals("Spalte_Berechnung.up"))
		{
			TabSpalte_Berechnung = new Tabellenspeicher(g,new String[]{"aic_spalte","pos","kennung","spa_aic_spalte","wert","Eingabe"});
			for(Integer iAIC_Spalte=Zip.ReadInteger();iAIC_Spalte!=null;iAIC_Spalte = Zip.ReadInteger())
			{
				TabSpalte_Berechnung.addInhalt("aic_spalte",iAIC_Spalte);
				TabSpalte_Berechnung.addInhalt("pos",Zip.ReadInteger());
				TabSpalte_Berechnung.addInhalt("kennung",Zip.ReadString());
				TabSpalte_Berechnung.addInhalt("spa_aic_spalte",Zip.ReadInteger());
				TabSpalte_Berechnung.addInhalt("wert",Zip.ReadDouble());
				TabSpalte_Berechnung.addInhalt("Eingabe",Zip.ReadString());
			}
		}
		else if(sFile.equals("SubAbfragen.up"))
		{
			TabSubAbfragen = new Tabellenspeicher(g,new String[]{"Abfrage","SubAbfrage","Art"});
			for(String sKennung=Zip.ReadString();sKennung!=null;sKennung=Zip.ReadString())
			{
				TabSubAbfragen.addInhalt("Abfrage",sKennung);
				TabSubAbfragen.addInhalt("SubAbfrage",Zip.ReadString());
				TabSubAbfragen.addInhalt("Art",Zip.ReadString());
			}
		}
		else if(sFile.equals("Fix_Spalte.up"))
		{
			TabFix_Spalte = new Tabellenspeicher(g,new String[]{"aic_spalte","richtung","eigenschaft","bewegungstyp"});
			for(Integer iAIC_Spalte=Zip.ReadInteger();iAIC_Spalte!=null;iAIC_Spalte = Zip.ReadInteger())
			{
				TabFix_Spalte.addInhalt("aic_spalte",iAIC_Spalte);
				TabFix_Spalte.addInhalt("richtung",Zip.ReadBoolean());
				TabFix_Spalte.addInhalt("eigenschaft",Zip.ReadString());
				TabFix_Spalte.addInhalt("bewegungstyp",Zip.ReadString());
			}
		}
		else if(sFile.equals("Fix_Bedingung.up"))
		{
			TabFix_Bedingung = new Tabellenspeicher(g,new String[]{"aic_bedingung","richtung","eigenschaft","bewegungstyp"});
			for(Integer iAIC_Bedingung=Zip.ReadInteger();iAIC_Bedingung!=null;iAIC_Bedingung = Zip.ReadInteger())
			{
				TabFix_Bedingung.addInhalt("aic_bedingung",iAIC_Bedingung);
				TabFix_Bedingung.addInhalt("richtung",Zip.ReadBoolean());
				TabFix_Bedingung.addInhalt("eigenschaft",Zip.ReadString());
				TabFix_Bedingung.addInhalt("bewegungstyp",Zip.ReadString());
			}
		}
		/*else if(sFile.equals("Nur_Begriff.up"))
		{
			TabNurBegriff = new Tabellenspeicher(g,new String[]{"Begriffgruppe","Begriff","Fix","Jeder","Combo","bits","Schrift","DefBezeichnung"});
			TabNurBegriff.sGruppe="Begriffgruppe";
			TabNurBegriff.sAIC="Begriff";
			String sBegriffgruppe="";
			for(String sBegriff=Zip.ReadString();sBegriff!=null;sBegriff=Zip.ReadString())
			{
				if(sBegriff.equals("*!*"))
				{
					sBegriffgruppe=Zip.ReadString();
					sBegriff=Zip.ReadString();
				}
				TabNurBegriff.addInhalt("Begriffgruppe",sBegriffgruppe);
				TabNurBegriff.addInhalt("Begriff",sBegriff);
				TabNurBegriff.addInhalt("Fix",Zip.ReadBoolean());
				TabNurBegriff.addInhalt("Jeder",Zip.ReadBoolean());
				TabNurBegriff.addInhalt("Combo",Zip.ReadBoolean());
				TabNurBegriff.addInhalt("bits",Zip.ReadInteger());
				TabNurBegriff.addInhalt("Schrift",Zip.ReadString());
                                TabNurBegriff.addInhalt("DefBezeichnung",Zip.ReadString());
			}
		}
		else if(sFile.equals("Nur_Stammtyp.up"))
		{
			TabNurStammtyp = new Tabellenspeicher(g,new String[]{"Kennung","Translate","Ende","Benutzer","Einheit","Lizenz","Copy"});
			for(String sKennung=Zip.ReadString();sKennung!=null;sKennung=Zip.ReadString())
			{
				TabNurStammtyp.addInhalt("Kennung",sKennung);
				TabNurStammtyp.addInhalt("Translate",Zip.ReadBoolean());
				TabNurStammtyp.addInhalt("Ende",Zip.ReadBoolean());
				TabNurStammtyp.addInhalt("Benutzer",Zip.ReadBoolean());
				TabNurStammtyp.addInhalt("Einheit",Zip.ReadBoolean());
				TabNurStammtyp.addInhalt("Lizenz",Zip.ReadBoolean());
				TabNurStammtyp.addInhalt("Copy",Zip.ReadBoolean());
			}
		}
		else if(sFile.equals("Nur_Eigenschaft.up"))
		{
			TabNurEigenschaft = new Tabellenspeicher(g,new String[]{"Kennung","Feldlaenge","Format","Min","Max","Bits","Datentyp"});
			for(String sKennung=Zip.ReadString();sKennung!=null;sKennung=Zip.ReadString())
			{
				TabNurEigenschaft.addInhalt("Kennung",sKennung);
				TabNurEigenschaft.addInhalt("Feldlaenge",Zip.ReadInteger());
				TabNurEigenschaft.addInhalt("Format",Zip.ReadString());
				TabNurEigenschaft.addInhalt("Min",Zip.ReadInteger());
				TabNurEigenschaft.addInhalt("Max",Zip.ReadInteger());
				TabNurEigenschaft.addInhalt("Datentyp",Zip.ReadString());
				TabNurEigenschaft.addInhalt("Bits",Zip.ReadInteger());
			}
		}*/
		/*else if(sFile.equals("Versionsupdate.up"))
		{
			TabVersionsupdate = new Tabellenspeicher(g,new String[]{"Version","Erstellt","Exportiert","Tabelle","Kennung","Gruppe","Bits"});
			String sVersion = Zip.ReadString();
			java.util.Date dErstellt = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(Zip.ReadString(),new java.text.ParsePosition(0));
			String sExportiert = Zip.ReadString();
			java.util.Date dExportiert = sExportiert.equals("null")?null:new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sExportiert,new java.text.ParsePosition(0));
			String sTabelle="";
			for(String sKennung=Zip.ReadString();sKennung!=null;sKennung=Zip.ReadString())
			{
				if(sKennung.equals("*!*"))
					sTabelle=Zip.ReadString();
				TabVersionsupdate.addInhalt("Version",sVersion);
				TabVersionsupdate.addInhalt("Erstellt",dErstellt);
				TabVersionsupdate.addInhalt("Exportiert",dExportiert);
				TabVersionsupdate.addInhalt("Tabelle",sTabelle);
				TabVersionsupdate.addInhalt("Kennung",Zip.ReadString());
				TabVersionsupdate.addInhalt("Gruppe",sTabelle.equals("BEGRIFF")?Zip.ReadString():null);
                                TabVersionsupdate.addInhalt("Bits",Zip.ReadInteger());
			}
                        //TabVersionsupdate.showGrid();
                        //return;
		}*/
                else if(sFile.equals("Module.up"))
                {
                  VecModule=new java.util.Vector<Object>();
                  for(String sKennung=Zip.ReadString();sKennung!=null;sKennung=Zip.ReadString())
                  {
                    java.util.Date dt = Static.StrToDateTime(Zip.ReadString());
                   // new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Zip.ReadString(), new java.text.ParsePosition(0));
                    if(TabBegriff.posInhalt((Object)"Modul", sKennung))
                      g.exec("update begriff set importzeit=" + g.DateTimeToString(dt) + " where aic_begriff=" + TabBegriff.getI("AIC"));
                    else
                      Static.printError("DefImport: Modul " + sKennung + " nicht gefunden");
                    VecModule.addElement(TabBegriff.getInhalt("AIC"));
                  }
                }
		else if(sFile.equals("Begriff.up"))
		{
			long lClock2=Static.get_ms();
			TabBegriff = new Tabellenspeicher(g,new String[]{"Begriff","Begriffgruppe","Code","Code_BG","DefBezeichnung","Hotkey","Homepage","Kennzeichen","Alias","Bildname","Pos","Fix","Jeder","Combo","Tod","Web","Prog","bits","edit","editold","Vorhanden","AIC"});
			TabBegriff.sGruppe="Begriffgruppe";
                        TabBegriff.sKennung="Begriff";
			TabBegriff.sAIC="Begriff";
			String sBegriffgruppe="";
			for(String sBegriff=Zip.ReadString();sBegriff!=null;sBegriff=Zip.ReadString())
			{
				if(sBegriff.equals("*!*"))
				{
					sBegriffgruppe=Zip.ReadString();
					sBegriff=Zip.ReadString();
				}
				TabBegriff.addInhalt("Begriffgruppe",sBegriffgruppe);
				TabBegriff.addInhalt("Begriff",sBegriff);
				TabBegriff.addInhalt("Code",Zip.ReadString());
				TabBegriff.addInhalt("Code_BG",Zip.ReadString());
                TabBegriff.addInhalt("Fix",Zip.ReadBoolean());
				TabBegriff.addInhalt("Jeder",Zip.ReadBoolean());
				TabBegriff.addInhalt("Combo",Zip.ReadBoolean());
                TabBegriff.addInhalt("Tod",Zip.ReadBoolean());
                TabBegriff.addInhalt("Web",Zip.ReadBoolean());
                TabBegriff.addInhalt("Prog",Zip.ReadString());
				TabBegriff.addInhalt("bits",Zip.ReadLong());
                TabBegriff.addInhalt("DefBezeichnung",Zip.ReadString());
                TabBegriff.addInhalt("Hotkey",Zip.ReadString());
                TabBegriff.addInhalt("Homepage",Zip.ReadString());
                TabBegriff.addInhalt("Kennzeichen",Zip.ReadString());
                TabBegriff.addInhalt("Alias",Zip.ReadString());
                TabBegriff.addInhalt("Bildname",Zip.ReadString());
                TabBegriff.addInhalt("Pos",Zip.ReadInteger());
				TabBegriff.addInhalt("edit",Static.StrToDateTime(Zip.ReadString()));
                                //new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Zip.ReadString(),new java.text.ParsePosition(0)));
				TabBegriff.addInhalt("editold",null);
				TabBegriff.addInhalt("Vorhanden",null);
				TabBegriff.addInhalt("AIC",null);
			}
			//TabBegriff.showGrid();
                        if (bNormal)
                        {
                          //long lClock3=Static.get_ms();
                          Vector<Integer> VecBegriff=SQL.getVector("select begriff.aic_begriff from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" left outer"+g.join("darstellung","d","begriff","begriff")+" where g.kennung in ('Button') and d.aic_begriff is null and begriff.kennung not"+Static.SQL_in(TabBegriff.getVecKennung("Button")),g);
                          int iAnzBtn=VecBegriff.size();
                          SQL.addVector(VecBegriff,"select begriff.aic_begriff from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" left outer"+g.join("darstellung","d","begriff","begriff")+" where g.kennung in ('Checkbox') and d.aic_begriff is null"+(bSpezial?"":" and begriff.kennung not"+Static.SQL_in(TabBegriff.getVecKennung("Checkbox"))),g);
                          int iAnzCbx=VecBegriff.size();
                          SQL.addVector(VecBegriff,"select begriff.aic_begriff from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" left outer"+g.join("darstellung","d","begriff","begriff")+" where g.kennung in ('Radiobutton') and d.aic_begriff is null"+(bSpezial?"":" and begriff.kennung not"+Static.SQL_in(TabBegriff.getVecKennung("Radiobutton"))),g);
                          int iAnzRbn=VecBegriff.size();
                          SQL.addVector(VecBegriff,"select begriff.aic_begriff from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" where g.kennung in ('Show') and begriff.kennung not"+Static.SQL_in(TabBegriff.getVecKennung("Show")),g);
                          int iAnzShow=VecBegriff.size();
                          SQL.addVector(VecBegriff,"select begriff.aic_begriff from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" where g.kennung in ('Message') and begriff.kennung not"+Static.SQL_in(TabBegriff.getVecKennung("Message")),g);
                          int iAnzMsg=VecBegriff.size();
                          if (VecBegriff.size()>0)
                          {
                            diskInfo(g,iAnzBtn," Knöpfe gelöscht");
                            diskInfo(g,iAnzCbx-iAnzBtn," Checkboxen gelöscht");
                            diskInfo(g,iAnzRbn-iAnzCbx," Radiobuttons gelöscht");
                            diskInfo(g,iAnzShow-iAnzRbn," Anzeigen gelöscht");
                            diskInfo(g,iAnzMsg-iAnzShow," Meldungen gelöscht");
                            diskInfo(g,g.exec("delete from begriff_zuordnung where beg_aic_begriff" + Static.SQL_in(VecBegriff))," begriff_zuordnungen gelöscht");
                            diskInfo(g,g.exec("delete from fensterpos where aic_begriff" + Static.SQL_in(VecBegriff))," fensterpositionen gelöscht");
                            diskInfo(g,g.exec("delete from begriff where aic_begriff" + Static.SQL_in(VecBegriff))," begriffe gelöscht");
                          }
                          if (bSpezial)
                          {
                            //g.diskInfo("daten_bild1 löschen:" +g.exec("delete from daten_bild where aic_sprache>1"));
                            g.diskInfo("daten_memo1 löschen:" +g.exec("delete from daten_memo where aic_sprache>1"));
                            g.diskInfo("Tooltip1 löschen:" +g.exec("delete from Tooltip where aic_sprache>1"));
                            g.diskInfo("Bezeichnung1 löschen:" +g.exec("delete from Bezeichnung where aic_sprache>1"));
                            g.diskInfo("daten_bild2 löschen:" +g.exec("delete from daten_bild from daten_bild,begriff"+g.join("begriffgruppe","g","begriff","begriffgruppe")+" where aic_tabellenname="+Global.iTabBegriff+" and aic_begriff=aic_fremd and g.kennung in ('Button','Checkbox','Radiobutton','Show','Message')"));
                            g.diskInfo("daten_memo2 löschen:" +g.exec("delete from daten_memo from daten_memo,begriff"+g.join("begriffgruppe","g","begriff","begriffgruppe")+" where aic_tabellenname="+Global.iTabBegriff+" and aic_begriff=aic_fremd and g.kennung in ('Button','Checkbox','Radiobutton','Show','Message')"));
                            g.diskInfo("Tooltip2 löschen:" +g.exec("delete from Tooltip from Tooltip,begriff"+g.join("begriffgruppe","g","begriff","begriffgruppe")+" where aic_tabellenname="+Global.iTabBegriff+" and aic_begriff=aic_fremd and g.kennung in ('Button','Checkbox','Radiobutton','Show','Message')"));
                            g.diskInfo("Bezeichnung2 löschen:" +g.exec("delete from Bezeichnung from Bezeichnung,begriff"+g.join("begriffgruppe","g","begriff","begriffgruppe")+" where aic_tabellenname="+Global.iTabBegriff+" and aic_begriff=aic_fremd and g.kennung in ('Button','Checkbox','Radiobutton','Show','Message')"));
                            Reinigung.FremdtabellenLoeschen(g, null, null);
                            g.diskInfo("Benutzer-Sprachen löschen:" +g.exec("update benutzer set aic_sprache=null where aic_sprache>1"));
                            g.diskInfo("Sprachen löschen:" +g.exec("delete from Sprache where aic_sprache>1"));
                          }
                          //g.testInfo("Löschdauer: " + (Static.get_ms() - lClock3) + " ms");
                        }
			sBegriffgruppe="";
			//Vector VecVorhanden=null;
			for(TabBegriff.moveFirst();!TabBegriff.eof();TabBegriff.moveNext())
			{
				if(!sBegriffgruppe.equals(TabBegriff.getS("Begriffgruppe")))
				{
					if(!sBegriffgruppe.equals(""))
					{
						TabBegriff.push();
						if(Qry.open("SELECT B.AIC_Begriff,B.Kennung,B.ImportZeit FROM Begriff B"+g.join("begriffgruppe","BG","B","begriffgruppe")+" WHERE BG.Kennung='"+sBegriffgruppe+"'"))// AND B.Kennung "+Static.SQL_in(VecVorhanden)))
						{
							for(;!Qry.eof();Qry.moveNext())
								if(TabBegriff.posInhalt((Object)sBegriffgruppe,Qry.getS("Kennung")))
								{
									java.util.Date DEditOld=Qry.getTS("ImportZeit");
									TabBegriff.setInhalt("editold",DEditOld);
									TabBegriff.setInhalt("Vorhanden",new Boolean(true));
									TabBegriff.setInhalt("AIC",Qry.getInt("AIC_Begriff"));
								}
							Qry.close();
						}

						TabBegriff.pop();
					}
					sBegriffgruppe=TabBegriff.getS("Begriffgruppe");
					//VecVorhanden = new Vector();
				}
				//VecVorhanden.addElement(TabBegriff.getS("Begriff"));
			}
			if(!TabBegriff.isEmpty() && Qry.open("SELECT B.AIC_Begriff,B.Kennung,B.ImportZeit FROM Begriff B JOIN Begriffgruppe BG ON b.aic_begriffgruppe=bg.aic_begriffgruppe WHERE BG.Kennung='"+sBegriffgruppe+"'"))// AND B.Kennung "+Static.SQL_in(VecVorhanden)))
			{
				for(;!Qry.eof();Qry.moveNext())
					if(TabBegriff.posInhalt((Object)sBegriffgruppe,Qry.getS("Kennung")))
					{
						java.util.Date DEditOld=Qry.getTS("ImportZeit");
						TabBegriff.setInhalt("editold",DEditOld);
						TabBegriff.setInhalt("Vorhanden",new Boolean(true));
						TabBegriff.setInhalt("AIC",Qry.getInt("AIC_Begriff"));
					}
				Qry.close();
			}
			//TabBegriff.showGrid();

			for(TabBegriff.moveFirst();!TabBegriff.eof();TabBegriff.moveNext())
			{
                if(g.TabBegriffgruppen.existInhalt("Kennung",TabBegriff.getS("Begriffgruppe")))
				{
					Qry.add0("fixe_Sprache",TabBegriff.getB("Fix"));
					Qry.add0("Jeder",TabBegriff.getB("Jeder"));
					Qry.add0("combo",TabBegriff.getB("Combo"));
                    Qry.add0("tod",TabBegriff.getB("tod"));
                    Qry.add0("web",TabBegriff.getB("web"));
					Qry.add("bits",TabBegriff.getL("bits"));
                    Qry.add("DefBezeichnung",TabBegriff.getS("DefBezeichnung"));
                    Qry.add("Hotkey",TabBegriff.getS("Hotkey"));
					//Qry.add("ImportZeit",new java.sql.Timestamp(TabBegriff.getDate("edit").getTime()));
					Qry.add("AIC_Stammtyp",(String)null);
                    Qry.add("AIC_Rolle",(String)null);
					Qry.add("AIC_Bewegungstyp",(String)null);
                    Qry.add("AIC_Schrift",(String)null);
                    Qry.add("Homepage",TabBegriff.getS("Homepage"));
                    Qry.add("Kennzeichen",TabBegriff.getS("Kennzeichen"));
                    Qry.add("Alias",TabBegriff.getS("Alias"));
                    Qry.add("Bildname",TabBegriff.getS("Bildname"));
                    Qry.add0("Pos",TabBegriff.getI("Pos"));
                    //Qry.add("AIC_Homepage",(String)null);
                    boolean bCode=!TabBegriff.getS("Code").equals("");
					if(bCode)
						Qry.add0("AIC_Code",g.getCodeAic(TabBegriff.getS("Code_BG"),TabBegriff.getS("Code")));
					Qry.add0("prog", Static.Leer(TabBegriff.getS("Prog")) ? 0:g.getCodeAic("Programm", TabBegriff.getS("Prog")));
					if(TabBegriff.getB("Vorhanden"))
					{
						if (!bCode && TabBegriff.getS("Begriffgruppe").equals("Druck"))
							Qry.add("aic_code", (String)null);
						Qry.update("Begriff",TabBegriff.getI("Aic"));
					}
					else
					{
						Qry.add("AIC_Begriffgruppe",g.TabBegriffgruppen.getAic(TabBegriff.getS("Begriffgruppe")));
						Qry.add("Kennung",TabBegriff.getS("Begriff"));
						Qry.add("AIC_Mandant",1);
						TabBegriff.setInhalt("AIC",Qry.insert("Begriff",true));
					}
				}
				else
					printError("DefImport: Begriffgruppe("+TabBegriff.getS("Begriffgruppe")+") konnten nicht gefunden werden!!!");
			}
			g.clockInfo("Begriff",lClock2);
			//if(g.Debug())
			//	TabBegriff.showGrid("TabBegriff");

		}
		else if(sFile.equals("Form2FXML.up"))
		{
        	TabForm2FXML = ReadZuordnung(g,Zip, false, false, false); 
        	
		}
		else if(sFile.equals("Bewegungstyp.up"))
		{
			long lClock2=Static.get_ms();
			TabBewegungstyp = ReadBSEH(g,Zip,new String[]{"Kennung","BewBits","Eig1","Eig2","Eig3","Eig4","Eig5","Eig6","Eig7","Eig8","Eig9","LDate2","LDate3","Bool1","Bool2","VB","Tod","Vorhanden","AIC","ANR"},new char[] {'S','I','S','S','S','S','S','S','S','S','S','S','S','S','S','S','B','N','N','N'});

			if(!TabBewegungstyp.isEmpty())// && Qry.open("SELECT AIC_Bewegungstyp,Kennung FROM Bewegungstyp WHERE Kennung "+Static.SQL_in(TabBewegungstyp.getVecSpalte("Kennung"))))
			{
				/*for(;!Qry.eof();Qry.moveNext())
					if(TabBewegungstyp.posInhalt("Kennung",Qry.getS("Kennung")))
					{
						TabBewegungstyp.setInhalt("Vorhanden",new Boolean(true));
						TabBewegungstyp.setInhalt("AIC",Qry.getInt("AIC_Bewegungstyp"));
					}
				Qry.close();*/
                           for(TabBewegungstyp.moveFirst();!TabBewegungstyp.eof();TabBewegungstyp.moveNext())
                           {
                             int iPos=g.TabErfassungstypen.getPos("Kennung",TabBewegungstyp.getS("Kennung").toUpperCase());
                             if (iPos<0)
                               g.fixtestInfo("Bewegungstyp "+TabBewegungstyp.getS("Kennung")+" nicht gefunden!");
                             else
                             {
                               TabBewegungstyp.setInhalt("Vorhanden",new Boolean(true));
                               TabBewegungstyp.setInhalt("AIC",g.TabErfassungstypen.getI(iPos,"aic"));
                             }
                           }
			}

			for(TabBewegungstyp.moveFirst();!TabBewegungstyp.eof();TabBewegungstyp.moveNext())
                          if (TabBewegungstyp.getB("Vorhanden") || g.BasisPort() || g.ApplPort() || !TabBewegungstyp.getB("Tod"))
                          {
                            //boolean b=TabBew ==null;
                            String sKennung=TabBewegungstyp.getS("Kennung");                          
                            {
                              int iPos=g.TabErfassungstypen.getPos("Kennung",sKennung.toUpperCase());
				Qry.add("Kennung",sKennung);
                                Qry.add("BewBits",TabBewegungstyp.getI("BewBits"));
                                Qry.add0("Tod",TabBewegungstyp.getB("Tod"));
                                boolean bANR=iPos<0;
                                for (int i=1;i<10;i++)
                                {
                                  int iEig=getEig(g,TabBewegungstyp.getS("Eig" + i));//g.TabEigenschaften.getAic(TabBewegungstyp.getS("Eig" + i).toUpperCase());
                                  Qry.add0("AIC_EIG" + i, iEig);
                                  bANR = bANR || iEig>0 && (iEig != g.TabErfassungstypen.getI(iPos,"Eig" + i));
                                }
                                if (bANR)
                                {
                                  //g.fixtestInfo("ANR-Eig "+(iPos<0 ? "neu":"geändert")+" bei "+sKennung);
                                  if (iPos>=0)
                                    TabBewegungstyp.setInhalt("ANR",new Boolean(true));
                                }
                                for (int i=2;i<4;i++)
                                {
                                  int iEig=getEig(g,TabBewegungstyp.getS("LDate" + i));//g.TabEigenschaften.getAic(TabBewegungstyp.getS("LDate" + i).toUpperCase());
                                  Qry.add0("Datum_Eig" + i, iEig);
                                  if ((iEig>0) && (iPos>=0) && (iEig != g.TabErfassungstypen.getI(iPos,"Datum_Eig" + i)))
                                  {
                                    //g.fixtestInfo("Datum_Eig" + i+":"+g.TabErfassungstypen.getI(iPos,"Datum_Eig" + i)+"->"+iEig);
                                    addTab(TabBewSet,"LDate",i, TabBewegungstyp.getI("AIC"), iEig);
                                    //Reinigung.setLDate(i, g, TabBewegungstyp.getI("AIC"), iEig);
                                  }
                                }
                                for (int i=1;i<3;i++)
                                {
                                  int iEig=getEig(g,TabBewegungstyp.getS("Bool" + i));//g.TabEigenschaften.getAic(TabBewegungstyp.getS("Bool" + i).toUpperCase());
                                  Qry.add0("Bool_Eig" + i, iEig);
                                  if ((iEig>0) && (iPos>=0) && (iEig != g.TabErfassungstypen.getI(iPos,"Bool_Eig" + i)))
                                  {
                                    //g.fixtestInfo("Bool_Eig" + i+":"+g.TabErfassungstypen.getI(iPos,"Bool_Eig" + i)+"->"+iEig);
                                    addTab(TabBewSet,"BOOL",i, TabBewegungstyp.getI("AIC"), iEig);
                                    //Reinigung.setBOOL(i, g, TabBewegungstyp.getI("AIC"), iEig);
                                  }
                                }
                                int iEig=getEig(g,TabBewegungstyp.getS("VB"));
                                Qry.add0("VonBis_Eig", iEig);
                                if ((iEig>0) && (iPos>=0) && (iEig != g.TabErfassungstypen.getI(iPos,"VB_Eig")))
                                	addTab(TabBewSet,"VB",1, TabBewegungstyp.getI("AIC"), iEig);
								if(!TabBewegungstyp.getB("Vorhanden"))
				                                  TabBewegungstyp.setInhalt("AIC",Qry.insert("Bewegungstyp",true));
								else
                                  Qry.update("Bewegungstyp",TabBewegungstyp.getI("AIC"));
                                /*for (int i=2;i<4;i++)
                                {
                                  int iEig=g.TabEigenschaften.getAic(TabBewegungstyp.getS("LDate" + i).toUpperCase());
                                  if (iEig>0)// && (!TabBewegungstyp.getB("Vorhanden") || verändert))
                                    Reinigung.setLDate(i,g,TabBewegungstyp.getI("AIC"),iEig);
                                }*/

                            }
                          }
                        g.exec("Update Bewegungstyp set tod=null where tod=0");
			g.clockInfo("Bewegungstyp",lClock2);
			//if(g.Debug())
			//	TabBewegungstyp.showGrid("TabBewegungstyp");
		}
		else if(sFile.equals("Stammtyp.up"))
		{
			long lClock2=Static.get_ms();
			g.fixInfo("Stammtypen schreiben");
			TabStammtyp=ReadBSEH(g,Zip,new String[]{"Kennung","SttBits","ANR","WebIcon","WebIconOffen","WebFarbe","Tod","Vorhanden","AIC"},new char[]{'S','I','S','S','S','S','B','N','N'});

			if(!TabStammtyp.isEmpty() && Qry.open("SELECT AIC_Stammtyp,Kennung FROM Stammtyp WHERE Kennung "+Static.SQL_in(TabStammtyp.getVecSpalte("Kennung"))))
			{
				for(;!Qry.eof();Qry.moveNext())
					if(TabStammtyp.posInhalt("Kennung",Qry.getS("Kennung")))
					{
						TabStammtyp.setInhalt("Vorhanden",new Boolean(true));
						TabStammtyp.setInhalt("AIC",Qry.getInt("AIC_Stammtyp"));
					}
				Qry.close();
			}

			for(TabStammtyp.moveFirst();!TabStammtyp.eof();TabStammtyp.moveNext())
                          if (TabStammtyp.getB("Vorhanden") || g.BasisPort() || g.ApplPort() || !TabStammtyp.getB("Tod"))
                          {
                            Qry.add("Kennung", TabStammtyp.getS("Kennung"));
                            Qry.add("SttBits", TabStammtyp.getI("SttBits"));
                            int iEig=g.TabEigenschaften.getAic(TabStammtyp.getS("ANR").toUpperCase());
                            Qry.add0("anr_eig",iEig);
                            Qry.add("WebIcon", TabStammtyp.getS("WebIcon"));
                            Qry.add("WebIconOffen", TabStammtyp.getS("WebIconOffen"));
                            Qry.add("WebFarbe", TabStammtyp.getS("WebFarbe"));
                            Qry.add0("Tod", TabStammtyp.getB("Tod"));
                            if (!TabStammtyp.getB("Vorhanden"))
                              TabStammtyp.setInhalt("AIC", Qry.insert("Stammtyp", true));
                            else
                              Qry.update("Stammtyp", TabStammtyp.getI("AIC"));

                            if (iEig>0)// && (!TabStammtyp.getB("Vorhanden") || verändert))
                              Reinigung.setSttANR(g,TabStammtyp.getI("AIC"),iEig);
                          }
                        g.exec("Update Stammtyp set tod=null where tod=0");
			g.clockInfo("Stammtyp",lClock2);
			if(bShow)
                          TabStammtyp.showGrid("TabStammtyp");
		}
                else if(sFile.equals("Rolle.up"))
                {
                        long lClock2=Static.get_ms();
                        g.fixInfo("Rollen schreiben");
                        TabRolle=ReadBSEH(g,Zip,new String[]{"Kennung","Vorhanden","AIC","Tod"},new char[]{'S','N','N','B'});

                        if(!TabRolle.isEmpty() && Qry.open("SELECT AIC_Rolle,Kennung FROM Rolle"))
                        {
                                for(;!Qry.eof();Qry.moveNext())
                                        if(TabRolle.posInhalt("Kennung",Qry.getS("Kennung")))
                                        {
                                                TabRolle.setInhalt("Vorhanden",new Boolean(true));
                                                TabRolle.setInhalt("AIC",Qry.getInt("AIC_Rolle"));
                                        }
                                Qry.close();

                                for(TabRolle.moveFirst();!TabRolle.eof();TabRolle.moveNext())
                                {
                                  if (!TabRolle.getB("Vorhanden") && (g.BasisPort() || g.ApplPort() || !TabRolle.getB("Tod")))
                                  {
                                    Qry.add("Kennung", TabRolle.getS("Kennung"));
                                    if (TabRolle.getB("Tod"))
                                      Qry.add("Tod",1);
                                    TabRolle.setInhalt("AIC", Qry.insert("Rolle", true));
                                  }
                                  else if (TabRolle.getB("Vorhanden") && TabRolle.getB("Tod"))
                                  {
                                    Qry.add("Tod",1);
                                    Qry.update("Rolle",TabRolle.getI("AIC"));
                                  }
                                }
                        }

                        g.clockInfo("Rolle",lClock2);
                }
		else if(sFile.equals("Eigenschaft.up"))
		{
			long lClock2=Static.get_ms();

                        g.fixInfo("Eigenschaften schreiben");
			TabEigenschaft=ReadBSEH(g,Zip,new String[]{"Kennung","Feldlaenge","Format","min","max","bits","Begriff","Abfrage","Tod","Vorhanden","AIC"},new char[]{'S','I','S','I','I','I','S','S','B','N','N'});
			if(!TabEigenschaft.isEmpty() && Qry.open("SELECT AIC_Eigenschaft,Kennung FROM Eigenschaft WHERE "+g.in("Kennung",TabEigenschaft.getVecSpalte("Kennung"))))
			{
				for(;!Qry.eof();Qry.moveNext())
					if(TabEigenschaft.posInhalt("Kennung",Qry.getS("Kennung")))
					{
						TabEigenschaft.setInhalt("Vorhanden",new Boolean(true));
						TabEigenschaft.setInhalt("AIC",Qry.getInt("AIC_Eigenschaft"));
					}
				Qry.close();
			}

			for(TabEigenschaft.moveFirst();!TabEigenschaft.eof();TabEigenschaft.moveNext())
                          if (TabEigenschaft.getB("Vorhanden") || g.BasisPort() || g.ApplPort() || !TabEigenschaft.getB("Tod"))
                          {

					Qry.add("Kennung",TabEigenschaft.getS("Kennung"));
                                        String sDatentyp=TabEigenschaft.getS("Begriff");
					Qry.add("AIC_Begriff",g.getBegriffAicS("Datentyp",g.ASA() && sDatentyp.equals("Bild2")?"Bild":sDatentyp));
					Qry.add("Feldlaenge",TabEigenschaft.getInt("Feldlaenge"));
					Qry.add("Format",TabEigenschaft.getS("Format"));
					Qry.add("min",TabEigenschaft.getInt("min"));
					Qry.add("max",TabEigenschaft.getInt("max"));
					Qry.add("bits",TabEigenschaft.getInt("bits"));
                                        Qry.add0("Tod",TabEigenschaft.getB("Tod"));

				if(!TabEigenschaft.getB("Vorhanden"))
					TabEigenschaft.setInhalt("AIC",Qry.insert("Eigenschaft",true));
				else
				{
					Qry.add("aic_abfrage",(String)null);
					Qry.add("aic_stamm",(String)null);
					Qry.add("aic_stammtyp",(String)null);
                                        Qry.add("aic_Rolle",(String)null);
                                        Qry.add("eig_aic_eigenschaft",(String)null);
					Qry.update("Eigenschaft",TabEigenschaft.getI("AIC"));
				}
                          }
                        g.exec("Update Eigenschaft set tod=null where tod=0");
			g.clockInfo("Eigenschaft",lClock2);
			if(bShow)
                          TabEigenschaft.showGrid("TabEigenschaft");
		}
                else if(sFile.equals("Auswahl.up"))
                {
                  long lClock2=Static.get_ms();
                  g.fixInfo("Auswahl schreiben");
                  TabAuswahl=ReadBSEH(g,Zip,new String[]{"Eig","Kennung","Nr"},new char[]{'S','S','I'});
                  if (TabAuswahl.size()>0)
                  {
                    String sEig = "?";
                    int iEig = 0;
                    //boolean bImport = false;
                    for (TabAuswahl.moveFirst(); !TabAuswahl.eof(); TabAuswahl.moveNext())
                    {
                      if (!TabAuswahl.getS("Eig").equals(sEig))
                      {
                        sEig = TabAuswahl.getS("Eig");
                        iEig = SQL.getInteger(g, "select aic_eigenschaft from eigenschaft where kennung='" + sEig + "'");
                        //bImport = iEig > 0 && SQL.getInteger(g, "select count(*) from auswahl where aic_eigenschaft=" + iEig) == 0;
                      }
                      if (iEig > 0 && SQL.getInteger(g, "select count(*) from auswahl where aic_eigenschaft=" + iEig+" and Nr="+TabAuswahl.getI("Nr")) == 0)
                      {
                        Qry.add("kennung", TabAuswahl.getS("Kennung"));
                        Qry.add("Nr", TabAuswahl.getI("Nr"));
                        Qry.add("aic_eigenschaft", iEig);
                        Qry.insert("auswahl", true);
                      }
                    }
                    g.clockInfo("Auswahl", lClock2);
                    if (bShow)
                      TabAuswahl.showGrid("TabAuswahl");
                    Reinigung.checkDatentyp(g);
                  }
                }
		else if(sFile.equals("Planung.up"))
		{
			TabPlanung=ReadBSEH(g,Zip,new String[]{"Kennung","AbfDaten","AbfFilter","AbfSonst","Spaltenbreite","Vorhanden","AIC"},new char[]{'S','S','S','S','I','N','N'});

			if(!TabPlanung.isEmpty() && Qry.open("SELECT p.AIC_Begriff,Kennung FROM Planung p join begriff b on p.aic_begriff=b.aic_begriff WHERE kennung "+Static.SQL_in(TabPlanung.getVecSpalte("Kennung"))))
			{
				for(;!Qry.eof();Qry.moveNext())
					if(TabPlanung.posInhalt("Kennung",Qry.getS("Kennung")))
					{
						TabPlanung.setInhalt("Vorhanden",new Boolean(true));
						TabPlanung.setInhalt("AIC",Qry.getInt("AIC_Begriff"));
					}
				Qry.close();
			}

			if(bShow)
				TabPlanung.showGrid("TabPlanung");
		}
                else if(sFile.equals("Formular_Abfrage.up"))
                  TabFormAbf=ReadBSEH(g,Zip,new String[]{"Formular","Abfrage"},new char[]{'S','S'});                 
                else if(sFile.equals("Formular_Modell.up"))
                  TabFormModell=ReadBSEH(g,Zip,new String[]{"Formular","Modell"},new char[]{'S','S'});
                else if(sFile.equals("Formular_Begriffe.up"))
                    TabFormBegriffe=ReadBSEH(g,Zip,new String[]{"Formular","BG","Begriff","Reihe"},new char[]{'S','S','S','I'});
                else if(sFile.equals("Formular_Stt.up"))
                    TabFormStt=ReadBSEH(g,Zip,new String[]{"Formular","Stt","Reihe"},new char[]{'S','S','I'});
                /*else if(sFile.equals("Druck_Modell.up"))
                {
                  TabDruckModell=ReadBSEH(g,Zip,new String[]{"Druck","Modell"},new char[]{'S','S'});
                  //TabDruckModell.showGrid("TabDruckModell");
                }*/

		else if(sFile.equals("Sprache.up"))
		{
			TabSprache=ReadBSEH(g,Zip,new String[]{"Kennung","iso639","K2","Vorhanden","AIC"},new char[]{'S','S','S','N','N'});

			if(!TabSprache.isEmpty() && Qry.open("SELECT AIC_Sprache,Kennung FROM Sprache WHERE Kennung "+Static.SQL_in(TabSprache.getVecSpalte("Kennung"))))
			{
				for(;!Qry.eof();Qry.moveNext())
					if(TabSprache.posInhalt("Kennung",Qry.getS("Kennung")))
					{
						TabSprache.setInhalt("Vorhanden",new Boolean(true));
						TabSprache.setInhalt("AIC",Qry.getInt("AIC_Sprache"));						
					}
				Qry.close();
			}

			for(TabSprache.moveFirst();!TabSprache.eof();TabSprache.moveNext())
			{
				String sK2=TabSprache.getS("K2");
				int iSp2=0;
				if (!sK2.equals("-"))
				{
					int iPos=TabSprache.getPos("Kennung",sK2);
					iSp2=iPos<0 ? 0:TabSprache.getI(iPos,"AIC");
				}
				if(!TabSprache.getB("Vorhanden"))
				{
					Qry.add("Kennung",TabSprache.getS("Kennung"));
					Qry.add("iso639",TabSprache.getS("iso639"));
					if (iSp2>0)
						Qry.add("Spr_aic_sprache", iSp2);
					TabSprache.setInhalt("AIC",Qry.insert("Sprache",true));
				}
				else if (iSp2>0)
				{
					Qry.add("Spr_aic_sprache", iSp2);
					Qry.update("Sprache",TabSprache.getI("AIC"));
				}
			}

			if(bShow)
				TabSprache.showGrid("TabSprache");
		}
                /*else if(sFile.equals("Land.up"))
                {
                        Tabellenspeicher TabLand=ReadBSEH(g,Zip,new String[]{"Kennung","iso3166","Vorhanden","AIC"},new char[]{'S','S','N','N'});

                        if(!TabLand.isEmpty() && Qry.open("SELECT AIC_Land,Kennung FROM Land"))
                        {
                                for(;!Qry.eof();Qry.moveNext())
                                        if(TabLand.posInhalt("Kennung",Qry.getS("Kennung")))
                                        {
                                                TabLand.setInhalt("Vorhanden",new Boolean(true));
                                                TabLand.setInhalt("AIC",Qry.getInt("AIC_Land"));
                                        }
                                Qry.close();
                        }

                        for(TabLand.moveFirst();!TabLand.eof();TabLand.moveNext())
                        {
                                if(!TabLand.getB("Vorhanden"))
                                {
                                        Qry.add("Kennung",TabLand.getS("Kennung"));
                                        Qry.add("iso3166",TabLand.getS("iso3166"));
                                        TabLand.setInhalt("AIC",Qry.insert("Land",true));
                                }
                        }

                        //if(g.Debug())
                        //	TabLand.showGrid("TabLand");
                }*/
                else if(sFile.equals("Abschlusstyp.up"))
                {
                  TabAbschlusstyp=ReadBSEH(g,Zip,new String[]{"Kennung","Stt","Bits","Vorhanden","AIC"},new char[]{'S','S','I','N','N'});
                  if(!TabAbschlusstyp.isEmpty() && Qry.open("SELECT AIC_Abschlusstyp,Kennung FROM Abschlusstyp"))
                        {
                                for(;!Qry.eof();Qry.moveNext())
                                        if(TabAbschlusstyp.posInhalt("Kennung",Qry.getS("Kennung")))
                                        {
                                                TabAbschlusstyp.setInhalt("Vorhanden",new Boolean(true));
                                                TabAbschlusstyp.setInhalt("AIC",Qry.getInt("AIC_Abschlusstyp"));
                                        }
                                Qry.close();
                        }
                }
                else if(sFile.equals("Abschlussdef.up"))
                {
                  TabAbschlussdef=ReadBSEH(g,Zip,new String[]{"Kennung","Bew","Eig"},new char[]{'S','S','S'});
                }
                else if(sFile.equals("Status.up"))
                {
                  TabStatus=ReadBSEH(g,Zip,new String[]{"Kennung","Filter","HS","Dar","bits","Bildname","Stamm","Stt"},new char[]{'S','S','S','S','I','S','S','S'});
                }
                else if(sFile.equals("StatusZ.up"))
                {
                  TabStatusZ=ReadBSEH(g,Zip,new String[]{"Kennung","Beg","BG","Pos"},new char[]{'S','S','S','I'});
                }
                else if(sFile.equals("BAT.up"))
                {
                  TabBAT=ReadBSEH(g,Zip,new String[]{"AST","Beg","BG"},new char[]{'S','S','S'});
                }
		else if(sFile.equals("Farbe.up"))
		{
			TabFarbe=ReadBSEH(g,Zip,new String[]{"Kennung","red","green","blue","Vorhanden","AIC"},new char[]{'S','I','I','I','N','N'});

			if(!TabFarbe.isEmpty() && Qry.open("SELECT AIC_Farbe,Kennung FROM Farbe WHERE Kennung "+Static.SQL_in(TabFarbe.getVecSpalte("Kennung"))))
			{
				for(;!Qry.eof();Qry.moveNext())
					if(TabFarbe.posInhalt("Kennung",Qry.getS("Kennung")))
					{
						TabFarbe.setInhalt("Vorhanden",new Boolean(true));
						TabFarbe.setInhalt("AIC",Qry.getInt("AIC_Farbe"));
					}
				Qry.close();
			}

			for(TabFarbe.moveFirst();!TabFarbe.eof();TabFarbe.moveNext())
			{
                                Qry.add("red",TabFarbe.getI("red"));
                                Qry.add("green",TabFarbe.getI("green"));
                                Qry.add("blue",TabFarbe.getI("blue"));
                                if(TabFarbe.getB("Vorhanden"))
                                  Qry.update("Farbe",TabFarbe.getI("AIC"));
                                else
                                {
                                  Qry.add("Kennung",TabFarbe.getS("Kennung"));
                                  TabFarbe.setInhalt("AIC",Qry.insert("Farbe",true));
                                }
			}

			//if(g.Debug())
			//	TabFarbe.showGrid("TabFarbe");
		}
		else if(sFile.equals("Schrift.up"))
		{
			TabSchrift=ReadBSEH(g,Zip,new String[]{"Kennung","Schriftart","size","bold","italic","Vorhanden","AIC"},new char[]{'S','S','I','B','B','N','N'});

			if(!TabSchrift.isEmpty() && Qry.open("SELECT AIC_Schrift,Kennung FROM Schrift WHERE Kennung "+Static.SQL_in(TabSchrift.getVecSpalte("Kennung"))))
			{
				for(;!Qry.eof();Qry.moveNext())
					if(TabSchrift.posInhalt("Kennung",Qry.getS("Kennung")))
					{
						TabSchrift.setInhalt("Vorhanden",new Boolean(true));
						TabSchrift.setInhalt("AIC",Qry.getInt("AIC_Schrift"));
					}
				Qry.close();
			}

			for(TabSchrift.moveFirst();!TabSchrift.eof();TabSchrift.moveNext())
			{

				int iAIC_Schriftart=Qry.getInteger("SELECT AIC_Schriftart FROM Schriftart WHERE Kennung='"+TabSchrift.getS("Schriftart")+"'");
				if(iAIC_Schriftart==0)
				{
					Qry.add("Kennung",TabSchrift.getS("Schriftart"));
					iAIC_Schriftart=Qry.insert("Schriftart",true);
				}
				Qry.add("AIC_Schriftart",iAIC_Schriftart);
				Qry.add(g.SIZE(),TabSchrift.getI("size"));
				Qry.add("bold",TabSchrift.getB("bold"));
				Qry.add("italic",TabSchrift.getB("italic"));
				if(TabSchrift.getB("Vorhanden"))
					Qry.update("Schrift",TabSchrift.getI("AIC"));
				else
				{
					Qry.add("Kennung",TabSchrift.getS("Kennung"));
					TabSchrift.setInhalt("AIC",Qry.insert("Schrift",true));
				}
			}

			//if(g.Debug())
			//	TabSchrift.showGrid("TabSchrift");
		}
		else if(sFile.equals("Abschnitt.up"))
		{
			TabAbschnitt=ReadBSEH(g,Zip,new String[]{"Kennung","bits","Begriff","Raster","Zahl","Kennzeichen","AIC","Vorhanden"},new char[]{'S','I','S','S','I','S','N','N'});
		}
                else if(sFile.equals("Raster.up"))
                {
                        TabRaster=ReadBSEH(g,Zip,new String[]{"Kennung","bits","Schrift1","Schrift2","Schrift3","Schrift4","Schrift5","Schrift6","Schrift7","Tod","AIC","Vorhanden"},new char[]{'S','I','S','S','S','S','S','S','S','B','N','N'});
                }
                else if(sFile.equals("Stamm_Abfragen.up"))
                {
                  g.fixInfo("Stamm_Abfragen gefunden");
                }
                else if(sFile.startsWith("Stamm_Abf_"))
                {
                  JCOutliner Out = Zip.ReadOutliner();
                  if (!bKeineStamm)
                  {
                    if (TabStammAbf==null)
                      TabStammAbf=new Tabellenspeicher(g,new String[]{"Abf","Out"});
                    String sAbf = sFile.substring(10, sFile.length() - 3);
                    g.fixInfo("Stamm-Abfrage <" + sAbf + "> geladen");
                    TabStammAbf.addInhalt("Abf", sAbf);
                    TabStammAbf.addInhalt("Out", Out.getRootNode()==null || Out.getRootNode().getChildren()==null ? null:Out.getRootNode().getChildren().elementAt(0));
                  }
                }
                else if(sFile.equals("Modelle_DefImport.up"))
                {
                  VecMD=new Vector<String>();
                  for(String sKennung=Zip.ReadString();sKennung!=null;sKennung=Zip.ReadString())
                    VecMD.addElement(sKennung);
                }
		else if(sFile.equals("Bezeichnung.up"))
		{
			TabBezeichnung = new Tabellenspeicher(g,new String[]{"Tabellenname","Bezeichnung","Sprache","Begriff","Begriffgruppe"});
			String sTabellenname = "";
                        Tabellenspeicher TabBG=new Tabellenspeicher(g,"select aic_begriffgruppe aic,kennung,Anweisung Anw from begriffgruppe",true);
                        Tabellenspeicher TabCode=new Tabellenspeicher(g,"select c.AIC_BEGRIFFGRUPPE GRUPPE,c.AIC_CODE AIC,c.kennung from code c join begriffgruppe bg on c.aic_begriffgruppe=bg.aic_begriffgruppe where bg.anweisung=1",true);
                        for(String sBezeichnung=Zip.ReadString();sBezeichnung!=null;sBezeichnung=Zip.ReadString())
			{
				if(sBezeichnung.equals("*!*"))
				{
					sTabellenname=Zip.ReadString();
					sBezeichnung=Zip.ReadString();
                                        //g.testInfo("Bezeichnung.up:"+sTabellenname+"/"+sBezeichnung);
				}
                                TabBezeichnung.newLine();
				TabBezeichnung.setInhalt("Tabellenname",sTabellenname);
				TabBezeichnung.setInhalt("Bezeichnung",sBezeichnung);
				TabBezeichnung.setInhalt("Sprache",Zip.ReadString());
				TabBezeichnung.setInhalt("Begriff",Zip.ReadString());
				TabBezeichnung.setInhalt("Begriffgruppe",sTabellenname.equals("BEGRIFF") || sTabellenname.equals("CODE") || sTabellenname.equals("STAMM") || sTabellenname.equals("AUSWAHL")?Zip.ReadString():null);
                                String sBG=sTabellenname.equals("CODE") || sTabellenname.equals("BEGRIFFGRUPPE") ? TabBezeichnung.getS(sTabellenname.equals("CODE") ? "Begriffgruppe":
                                    sTabellenname.equals("AUSWAHL")?"Eigenschaft":"Begriff"):null;
                                if (sBG != null && sTabellenname.equals("CODE"))
                                {
                                  if (!TabBG.posInhalt("Kennung",sBG))
                                  {
                                    g.testInfo("Begriffgruppe "+sBG+" anlegen");
                                    Qry.add("Kennung", sBG);
                                    Qry.add("Code", 1);
                                    Qry.add("Anweisung", 1);
                                    int iBG = Qry.insert("BEGRIFFGRUPPE", true);
                                    TabBG.addInhalt("aic",iBG);
                                    TabBG.addInhalt("Kennung",sBG);
                                    TabBG.addInhalt("Anw",true);
                                  }
                                }
                                if (sTabellenname.equals("CODE") && TabBG.posInhalt("Kennung",sBG) && TabBG.getB("Anw"))
                                {
                                  String sKennung=TabBezeichnung.getS("Begriff");
                                  int iBG=(Integer)TabBG.getInhalt("Aic");
                                  if (TabCode.posInhalt("Kennung",sKennung) && iBG!=TabCode.getI("GRUPPE"))
                                  {
                                    g.exec("update code set aic_begriffgruppe="+iBG+" where aic_code="+TabCode.getI("AIC"));
                                    g.testInfo("Befehl "+sKennung+": ändere Begriffgruppe von "+TabBG.getKennung(TabCode.getI("GRUPPE"))+" auf "+sBG);
                                  }
                                }
			}

            if(bShow)
				TabBezeichnung.showGrid("TabBezeichnung");
		}
                else if(sFile.equals("Tooltip.up"))
                {
                        TabTooltip = new Tabellenspeicher(g,new String[]{"Tabellenname","Tooltip","Sprache","Begriff","Begriffgruppe"});
                        String sTabellenname = "";
                        for(String sTooltip=Zip.ReadString();sTooltip!=null;sTooltip=Zip.ReadString())
                        {
                                if(sTooltip.equals("*!*"))
                                {
                                        sTabellenname=Zip.ReadString();
                                        sTooltip=Zip.ReadString();
                                        //g.testInfo("Bezeichnung.up:"+sTabellenname+"/"+sBezeichnung);
                                }

                                TabTooltip.addInhalt("Tabellenname",sTabellenname);
                                TabTooltip.addInhalt("Tooltip",sTooltip);
                                TabTooltip.addInhalt("Sprache",Zip.ReadString());
                                TabTooltip.addInhalt("Begriff",Zip.ReadString());
                                TabTooltip.addInhalt("Begriffgruppe",sTabellenname.equals("BEGRIFF") || sTabellenname.equals("CODE") || sTabellenname.equals("STAMM") || sTabellenname.equals("AUSWAHL")?Zip.ReadString():null);
                        }

                        if(bShow)
                        	TabTooltip.showGrid("TabTooltip");
                }
		else if(sFile.equals("Daten_Bild.up"))
		{
			TabDaten_Bild = new Tabellenspeicher(g,new String[]{"Tabellenname","Filename","Sprache","Begriff","Begriffgruppe"});
			String sTabellenname = "";
			for(String sFN=Zip.ReadString();sFN!=null;sFN=Zip.ReadString())
			{
				if(sFN.equals("*!*"))
				{
					sTabellenname=Zip.ReadString();
					sFN=Zip.ReadString();
				}

				TabDaten_Bild.addInhalt("Tabellenname",sTabellenname);
				TabDaten_Bild.addInhalt("Filename",sFN);
				TabDaten_Bild.addInhalt("Sprache",Zip.ReadString());
				TabDaten_Bild.addInhalt("Begriff",Zip.ReadString());
				TabDaten_Bild.addInhalt("Begriffgruppe",sTabellenname.equals("BEGRIFF") || sTabellenname.equals("CODE") || sTabellenname.equals("STAMM") || sTabellenname.equals("AUSWAHL")?Zip.ReadString():null);
			}

			if(bShow)
				TabDaten_Bild.showGrid("TabDaten_Bild");
		}
		else if(sFile.equals("Daten_Memo.up"))
		{
			TabDaten_Memo = new Tabellenspeicher(g,new String[]{"Tabellenname","Titel","Memo","Sprache","Begriff","Begriffgruppe"});
			String sTabellenname = "";
			for(String sMemo=Zip.ReadString();sMemo!=null;sMemo=Zip.ReadString())
			{
				if(sMemo.equals("*!*"))
				{
					sTabellenname=Zip.ReadString();
					sMemo=Zip.ReadString();
				}

				TabDaten_Memo.addInhalt("Tabellenname",sTabellenname);
				TabDaten_Memo.addInhalt("Memo",sMemo);
				TabDaten_Memo.addInhalt("Titel",Zip.ReadString());
				TabDaten_Memo.addInhalt("Sprache",Zip.ReadString());
				TabDaten_Memo.addInhalt("Begriff",Zip.ReadString());
				TabDaten_Memo.addInhalt("Begriffgruppe",sTabellenname.equals("BEGRIFF") || sTabellenname.equals("CODE") || sTabellenname.equals("STAMM") || sTabellenname.equals("AUSWAHL")?Zip.ReadString():null);
			}

			//if(g.Debug())
			//	TabDaten_Memo.showGrid("TabDaten_Memo");
		}
                /*else if(sFile.equals("Stamm_Bild.up"))
		{
			TabStamm_Bild = new Tabellenspeicher(g,new String[]{"Stt","Kennung","Filename"});
			for(String sStt=Zip.ReadString();sStt!=null;sStt=Zip.ReadString())
			{
				TabStamm_Bild.addInhalt("Stt",sStt);
				TabStamm_Bild.addInhalt("Kennung",Zip.ReadString());
				TabStamm_Bild.addInhalt("Filename",Zip.ReadString());
			}

			//if(g.Debug())
			//	TabDaten_Memo.showGrid("TabDaten_Memo");
		}
                else if(sFile.equals("Stamm_Memo.up"))
                {
                        TabStamm_Memo = new Tabellenspeicher(g,new String[]{"Stt","Kennung","Sprache","Memo","Titel"});
                        for(String sStt=Zip.ReadString();sStt!=null;sStt=Zip.ReadString())
                        {
                                TabStamm_Memo.addInhalt("Stt",sStt);
                                TabStamm_Memo.addInhalt("Kennung",Zip.ReadString());
                                TabStamm_Memo.addInhalt("Sprache",Zip.ReadString());
                                TabStamm_Memo.addInhalt("Memo",Zip.ReadString());
                                TabStamm_Memo.addInhalt("Titel",Zip.ReadString());
                        }

                        //if(g.Debug())
                        //	TabDaten_Memo.showGrid("TabDaten_Memo");
                }*/
                else if(sFile.equals("Modul_Zuordnung2.up"))
		{
                  TabModul_Zuordnung2 = new Tabellenspeicher(g,new String[]{"Modul","Tab","Kennung"});
                  for(String sModul=Zip.ReadString();sModul!=null;sModul=Zip.ReadString())
                  {
                    TabModul_Zuordnung2.addInhalt("Modul", sModul);
                    TabModul_Zuordnung2.addInhalt("Tab", Zip.ReadString());
                    TabModul_Zuordnung2.addInhalt("Kennung", Zip.ReadString());
                  }
                }
		else if(sFile.equals("Modul_Zuordnung.up"))
			TabModul_Zuordnung = ReadZuordnung(g,Zip,false,true,false);
                else if(sFile.equals("Kunde_Zuordnung.up"))
                        TabKunde_Zuordnung = ReadZuordnung(g,Zip,false,true,false);
                else if(sFile.equals("Druck_Zuordnung2.up"))
			TabDruck_Zuordnung2 = ReadZuordnung(g,Zip,false,true,false);
		//else if(sFile.equals("Beg2Hp.up"))
		//{
			//TabBeg2Hp = ReadZuordnung(Zip,false,true,false);
			//if(g.Debug())
			//	TabBeg2Hp.showGrid("TabBeg2Hp");
		//}
		else if(sFile.equals("Beg2Bew.up"))
			TabBeg2Bew = ReadZuordnung(g,Zip,false,true,false);
		else if(sFile.equals("Stt2Stt.up"))
			TabStt2Stt = ReadZuordnung(g,Zip,false,false,false);
                else if(sFile.equals("Rol2Rol.up"))
			TabRol2Rol = ReadZuordnung(g,Zip,false,false,false);
		else if(sFile.equals("Beg2Schrift.up"))
			TabBeg2Schrift = ReadZuordnung(g,Zip,false,true,false);
		else if(sFile.equals("Beg2Stt.up"))
			TabBeg2Stt = ReadZuordnung(g,Zip,false,true,false);
        else if(sFile.equals("Beg2Rolle.up"))
			TabBeg2Rolle = ReadZuordnung(g,Zip,false,true,false);
		else if(sFile.equals("Bew_Zuordnung.up"))
			TabBew_Zuordnung = ReadZuordnung(g,Zip,true,false,false);
		else if(sFile.equals("Eig_Zuordnung.up"))
			TabEig_Zuordnung = ReadZuordnung(g,Zip,true,false,false);
		else if(sFile.equals("Eig2Stamm.up"))
			TabEig2Stamm = ReadZuordnung(g,Zip,false,true,false);
		 else if(sFile.equals("Prog2Eig.up"))
			TabProg2Eig = ReadZuordnung(g,Zip,true,false,false);
        else if(sFile.equals("Rol2Stt.up"))
			TabRol2Stt = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Prog2Stt.up"))
			TabProg2Stt = ReadZuordnung(g,Zip,true,false,false);
        else if(sFile.equals("Form2Stamm.up"))
			TabForm2Stamm = ReadZuordnung(g,Zip,false,true,false);
		else if(sFile.equals("Eig2Stt.up"))
			TabEig2Stt = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Eig2Bew.up"))
			TabEig2Bew = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Eig2Rolle.up"))
			TabEig2Rolle = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Eig2Eig.up"))
			TabEig2Eig = ReadZuordnung(g,Zip,false,false,false);
		else if(sFile.equals("Stt2Eig.up"))
			TabStt2Eig = ReadZuordnung(g,Zip,false,false,false);
		else if(sFile.equals("Stt2Status.up"))
			TabStt2Status = ReadZuordnung(g,Zip,false,false,false);
		else if(sFile.equals("Stt2Aufgabe.up"))
			TabStt2Aufgabe = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Form2Eig.up"))
        	TabForm2Eig = ReadZuordnung(g,Zip, false, false, false);                 
		else if(sFile.equals("Schrift2Farbe.up"))
			TabSchrift2Farbe = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Schrift2Farbe2.up"))
			TabSchrift2Farbe2 = ReadZuordnung(g,Zip,false,false,false);
		else if(sFile.equals("Stt_Zuordnung.up"))
			TabStt_Zuordnung = ReadZuordnung(g,Zip,true,false,false);
        else if(sFile.equals("Rol_Zuordnung.up"))
			TabRol_Zuordnung = ReadZuordnung(g,Zip,false,false,false);
		else if(sFile.equals("Appl_Zuordnung.up"))
			TabApplikation_Zuordnung = ReadZuordnung(g,Zip,true,false,false);
		else if(sFile.equals("Gruppe_Zuordnung.up"))
			TabGruppe_Zuordnung = ReadZuordnung(g,Zip,true,false,false);
		else if(sFile.equals("Abf2Mod.up"))
			TabAbf2Mod = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Abf2Mod2.up"))
        	TabAbf2Mod2 = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Abf2Stamm.up"))
        	TabAbf2Stamm = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Mod2Abf.up"))
			TabMod2Abf = ReadZuordnung(g,Zip,false,false,false);
        else if(sFile.equals("Mod2Abf2.up"))
			TabMod2Abf2 = ReadZuordnung(g,Zip,false,false,false);
		else if(sFile.equals("Beg2Code.up"))
			TabBeg2Code = ReadZuordnung(g,Zip,false,true,true);
		else if(sFile.equals("Beg_AIC_Beg_Zuordnung.up"))
			TabBeg_AIC_Beg_Zuordnung = ReadZuordnung(g,Zip,true,true,true);
                else if(sFile.equals("Appl2_Zuordnung.up"))
			TabAppl2_Zuordnung = ReadZuordnung(g,Zip,true,true,true);
		else if(sFile.equals("Beg2AIC_Beg_Zuordnung.up"))
			TabBeg2AIC_Beg_Zuordnung = ReadZuordnung(g,Zip,true,true,true);
                else if(sFile.equals("Beg3AIC_Beg_Zuordnung.up"))
			TabBeg3AIC_Beg_Zuordnung = ReadZuordnung(g,Zip,true,true,true);
		else if(sFile.equals("Druck_Zuordnung.up"))
		{
			TabDruck_Zuordnung = ReadZuordnung(g,Zip,true,false,false);
			//if(g.Debug())
			//	TabDruck_Zuordnung.showGrid("TabDruck_Zuordnung");
		}
	}
	g.clockInfo("- Lesen",lClock);
    if (TabRaster != null)
        for(TabRaster.moveFirst();!TabRaster.eof();TabRaster.moveNext())
        {
                Qry.add("Kennung",TabRaster.getS("Kennung"));
                Qry.add("bits",TabRaster.getI("bits"));
                Qry.add0("AIC_Schrift",TabSchrift.posInhalt("Kennung",TabRaster.getS("Schrift1")) ? TabSchrift.getI("AIC"):0);
                Qry.add0("Sch_AIC_Schrift",TabSchrift.posInhalt("Kennung",TabRaster.getS("Schrift2")) ? TabSchrift.getI("AIC"):0);
                Qry.add0("Sch2_AIC_Schrift",TabSchrift.posInhalt("Kennung",TabRaster.getS("Schrift3")) ? TabSchrift.getI("AIC"):0);
                Qry.add0("Sch3_AIC_Schrift",TabSchrift.posInhalt("Kennung",TabRaster.getS("Schrift4")) ? TabSchrift.getI("AIC"):0);
                Qry.add0("Sch4_AIC_Schrift",TabSchrift.posInhalt("Kennung",TabRaster.getS("Schrift5")) ? TabSchrift.getI("AIC"):0);
                Qry.add0("Sch5_AIC_Schrift",TabSchrift.posInhalt("Kennung",TabRaster.getS("Schrift6")) ? TabSchrift.getI("AIC"):0);
                Qry.add0("Sch6_AIC_Schrift",TabSchrift.posInhalt("Kennung",TabRaster.getS("Schrift7")) ? TabSchrift.getI("AIC"):0);
                Qry.add0("Tod",TabRaster.getB("Tod"));
                int iAIC_Raster = SQL.getInteger(g,"select aic_Raster from Raster where kennung='"+TabRaster.getS("Kennung")+"'");

                if(iAIC_Raster>0)
                        Qry.update("Raster",iAIC_Raster);
                else
                        iAIC_Raster = Qry.insert("Raster",true);

                TabRaster.setInhalt("AIC",iAIC_Raster);
        }
    if (TabAbschnitt != null)
    {
	  for(TabAbschnitt.moveFirst();!TabAbschnitt.eof();TabAbschnitt.moveNext())
	  {
		Qry.add("Kennung",TabAbschnitt.getS("Kennung"));
                int iAbits=SQL.getInteger(g,"select bits from abschnitt where kennung='"+TabAbschnitt.getS("Kennung")+"'",-1);
                int iAbitsN=TabAbschnitt.getI("bits");
                if (iAbits>=0 && iAbits!=iAbitsN)
                {
                  if ((iAbits&Drucken.cstNichtDrucken)>0)
                    iAbitsN|=Drucken.cstNichtDrucken;
                  else if ((iAbitsN&Drucken.cstNichtDrucken)>0)
                    iAbitsN-=Drucken.cstNichtDrucken;
                }
		Qry.add("bits",iAbitsN);//TabAbschnitt.getI("bits") | (iAbits&Drucken.cstNichtDrucken));
                Qry.add("Zahl",TabAbschnitt.getI("Zahl"));
                Qry.add("Kennzeichen",TabAbschnitt.getS("Kennzeichen"));
		if(TabBegriff.posInhalt((Object)"Abfrage",TabAbschnitt.getInhalt("Begriff")))
			Qry.add("AIC_Begriff",TabBegriff.getI("AIC"));
                Qry.add0("aic_raster",TabAbschnitt.getS("Raster").equals("") ? 0:SQL.getInteger(g,"select aic_raster from raster where kennung='"+TabAbschnitt.getS("Raster")+"'"));

		int iAIC_Abschnitt = SQL.getInteger(g,"select aic_abschnitt from abschnitt where kennung='"+TabAbschnitt.getS("Kennung")+"'");

		if(iAIC_Abschnitt>0)
			Qry.update("Abschnitt",iAIC_Abschnitt);
		else
			iAIC_Abschnitt = Qry.insert("Abschnitt",true);

		TabAbschnitt.setInhalt("AIC",iAIC_Abschnitt);
	  }
      g.clockInfo("Abschnitt",lClock);
    }
        if (Static.cache())
          Static.clearCache();
        g.Refresh();
        g.clockInfo("Refresh",lClock);
	g.printExec("*Zuordnung Anfang");
        if (Gau != null)
          Gau.setText("Zuordnung",1);
	g.printExec("*TabBeg2Hp");
	/*if(TabBeg2Hp!=null)
	for(TabBeg2Hp.moveFirst();!TabBeg2Hp.eof();TabBeg2Hp.moveNext())
	{
		if(TabBegriff.posInhalt(TabBeg2Hp.getInhalt("Begriffgruppe"),TabBeg2Hp.getInhalt("Kennung2")) && TabHomepage.posInhalt("Kennung",TabBeg2Hp.getInhalt("Kennung1")))
		{
			Qry.add("AIC_Homepage",TabHomepage.getI("AIC"));
			Qry.addWhere("AIC_Begriff",TabBegriff.getI("AIC"));
			Qry.update("Begriff");
		}
		else
			printError("DefImport(Beg2Hp): Zuordnung zwischen <"+TabBeg2Hp.getInhalt("Kennung2")+"-"+TabBeg2Hp.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	}*/
	g.printExec("*TabBeg2Bew");
	if(TabBeg2Bew!=null)
	{
	  //TabBeg2Bew.showGrid("TabBeg2Bew");
	  for(TabBeg2Bew.moveFirst();!TabBeg2Bew.eof();TabBeg2Bew.moveNext())
	  {
            int iPos=g.getPosBegriff(TabBeg2Bew.getS("Begriffgruppe"),TabBeg2Bew.getS("Kennung2"));
            int iPosB=iPos<0 ? -1:g.TabErfassungstypen.getPos("Kennung",TabBeg2Bew.getS("Kennung1").toUpperCase());
		if(iPosB>=0)
		{
			Qry.add("AIC_Bewegungstyp",g.TabErfassungstypen.getI(iPosB,"AIC"));
			Qry.addWhere("AIC_Begriff",g.TabBegriffe.getI(iPos,"AIC"));
			Qry.update("Begriff");
		}
		else
			printError("DefImport(Beg2Bew): Zuordnung zwischen <"+TabBeg2Bew.getInhalt("Kennung2")+"-"+TabBeg2Bew.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	  }
	}
	g.printExec("*TabBeg2Schrift");
	if(TabBeg2Schrift!=null && bNormal)
          for(TabBeg2Schrift.moveFirst();!TabBeg2Schrift.eof();TabBeg2Schrift.moveNext())
          {
		if(TabBegriff.posInhalt(TabBeg2Schrift.getInhalt("Begriffgruppe"),TabBeg2Schrift.getInhalt("Kennung2")) && TabSchrift.posInhalt("Kennung",TabBeg2Schrift.getInhalt("Kennung1")))
		{
			Qry.add("AIC_Schrift",TabSchrift.getI("AIC"));
			Qry.addWhere("AIC_Begriff",TabBegriff.getI("AIC"));
			Qry.update("Begriff");
		}
		else
			printError("DefImport(Beg2Schrift): Zuordnung zwischen <"+TabBeg2Schrift.getInhalt("Kennung2")+"-"+TabBeg2Schrift.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
          }
	g.printExec("*TabBeg2Stt");
	//TabBeg2Stt.showGrid("TabBeg2Stt");
	if(TabBeg2Stt!=null)
	for(TabBeg2Stt.moveFirst();!TabBeg2Stt.eof();TabBeg2Stt.moveNext())
	{
          int iPos=g.getPosBegriff(TabBeg2Stt.getS("Begriffgruppe"),TabBeg2Stt.getS("Kennung2"));
          int iPosS=iPos<0 ? -1:g.TabStammtypen.getPos("Kennung",TabBeg2Stt.getS("Kennung1").toUpperCase());
 		if(iPosS>=0)
		{
			Qry.add("AIC_Stammtyp",g.TabStammtypen.getI(iPosS,"AIC"));
			Qry.addWhere("AIC_Begriff",g.TabBegriffe.getI(iPos,"AIC"));
			Qry.update("Begriff");
		}
		else
			printError("DefImport(Beg2Stt): Zuordnung zwischen <"+TabBeg2Stt.getInhalt("Kennung2")+"-"+TabBeg2Stt.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	}
        g.printExec("*TabBeg2Rolle");
        if(TabBeg2Rolle!=null)
        {
          Tabellenspeicher TabRolle2=new Tabellenspeicher(g,"select aic_rolle,kennung from rolle",true);
          for(TabBeg2Rolle.moveFirst();!TabBeg2Rolle.eof();TabBeg2Rolle.moveNext())
          {
                if(TabBegriff.posInhalt(TabBeg2Rolle.getInhalt("Begriffgruppe"),TabBeg2Rolle.getInhalt("Kennung2")) && TabRolle2.posInhalt("Kennung",TabBeg2Rolle.getInhalt("Kennung1")))
                {
                        Qry.add("AIC_Rolle",TabRolle2.getI("aic_rolle"));
                        Qry.addWhere("AIC_Begriff",TabBegriff.getI("AIC"));
                        Qry.update("Begriff");
                }
                else
                        printError("DefImport(Beg2Rolle): Zuordnung zwischen <"+TabBeg2Rolle.getInhalt("Kennung2")+"-"+TabBeg2Rolle.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
            }
        }
	g.printExec("*TabBeg2Code");
	if(TabBeg2Code!=null)
	for(TabBeg2Code.moveFirst();!TabBeg2Code.eof();TabBeg2Code.moveNext())
	{
		int iAIC_Code = g.getCodeAic((String)TabBeg2Code.getInhalt("Codegruppe"),(String)TabBeg2Code.getInhalt("Kennung1"));
		if (iAIC_Code==0)
			;
		else if(TabBegriff.posInhalt(TabBeg2Code.getInhalt("Begriffgruppe"),TabBeg2Code.getInhalt("Kennung2")))// && g.TabCodes.pos())// && g.TabCodes.posInhalt(TabBeg2Code.getInhalt("Codegruppe"),TabBeg2Code.getInhalt("Kennung1")))
		{
			Qry.add("AIC_Code",iAIC_Code);
			Qry.addWhere("AIC_Begriff",TabBegriff.getI("AIC"));
			Qry.update("Begriff");
		}
		else
			printError("DefImport(Beg2Code): Zuordnung zwischen <"+TabBeg2Code.getInhalt("Kennung2")+"-"+TabBeg2Code.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	}
	g.clockInfo("Begriff-Relationen",lClock);
	g.printExec("*TabEig2Stamm");
	if(TabEig2Stamm!=null)
	for(TabEig2Stamm.moveFirst();!TabEig2Stamm.eof();TabEig2Stamm.moveNext())
	{
		int iAIC_Stamm = Qry.getInteger("select aic_stamm from stammview st join stammtyp stt on st.aic_stammtyp=stt.aic_stammtyp where st.kennung='"+TabEig2Stamm.getInhalt("Kennung1")+"' and stt.kennung='"+TabEig2Stamm.getInhalt("Begriffgruppe")+"'");
		if (iAIC_Stamm==0)
                  iBits|=1;
                if(iAIC_Stamm>0 && TabEigenschaft.posInhalt("Kennung",TabEig2Stamm.getInhalt("Kennung2")))
		{
				Qry.add("AIC_Stamm",iAIC_Stamm);
				Qry.addWhere("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
				Qry.update("Eigenschaft");
		}
		else
			printError("DefImport(Eig2Stamm): Zuordnung zwischen <"+TabEig2Stamm.getInhalt("Kennung2")+"-"+TabEig2Stamm.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	}
	g.printExec("*TabRol2Stt");
        if(TabRol2Stt!=null)
          for(TabRol2Stt.moveFirst();!TabRol2Stt.eof();TabRol2Stt.moveNext())
          {
            int iPosS=g.TabStammtypen.getPos("Kennung",TabRol2Stt.getS("Kennung1").toUpperCase());
                if(TabRolle.posInhalt("Kennung",TabRol2Stt.getInhalt("Kennung2")) && iPosS>=0)
                {
                                Qry.add("AIC_Stammtyp",g.TabStammtypen.getI(iPosS,"AIC"));
                                Qry.addWhere("AIC_Rolle",TabRolle.getI("AIC"));
                                Qry.update("Rolle");
                }
                else
                        printError("DefImport(Rol2Stt): Zuordnung zwischen <"+TabRol2Stt.getInhalt("Kennung2")+"-"+TabRol2Stt.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
                    }
        g.printExec("*TabForm2Stamm");
        if(TabForm2Stamm!=null)
                for(TabForm2Stamm.moveFirst();!TabForm2Stamm.eof();TabForm2Stamm.moveNext())
                {
                        int iAIC_Stamm = Qry.getInteger("select aic_stamm from stammview st join stammtyp stt on st.aic_stammtyp=stt.aic_stammtyp where st.kennung='"+TabForm2Stamm.getInhalt("Kennung1")+"' and stt.kennung='"+TabForm2Stamm.getInhalt("Begriffgruppe")+"'");
                        if (iAIC_Stamm==0)
                          iBits|=1;
                        if(iAIC_Stamm>0 && TabBegriff.posInhalt((Object)"Frame",TabForm2Stamm.getInhalt("Kennung2")))
                        {
                                        Qry.add("AIC_Stamm",iAIC_Stamm);
                                        Qry.addWhere("AIC_Begriff",TabBegriff.getI("AIC"));
                                        Qry.update("Formular");
                        }
                        else
                                printError("DefImport(Form2Stamm): Zuordnung zwischen <"+TabForm2Stamm.getInhalt("Kennung2")+"-"+TabForm2Stamm.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
                }
	g.printExec("*TabEig2Stt");
	if(TabEig2Stt!=null)
	for(TabEig2Stt.moveFirst();!TabEig2Stt.eof();TabEig2Stt.moveNext())
	{
		if(TabEigenschaft.posInhalt("Kennung",TabEig2Stt.getInhalt("Kennung2")) && TabStammtyp.posInhalt("Kennung",TabEig2Stt.getInhalt("Kennung1")))
		{
				Qry.add("AIC_Stammtyp",TabStammtyp.getI("AIC"));
				Qry.addWhere("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
				Qry.update("Eigenschaft");
		}
		else
			printError("DefImport(Eig2Stt): Zuordnung zwischen <"+TabEig2Stt.getInhalt("Kennung2")+"-"+TabEig2Stt.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	}
        g.printExec("*TabEig2Bew");
        if(TabEig2Bew!=null)
        for(TabEig2Bew.moveFirst();!TabEig2Bew.eof();TabEig2Bew.moveNext())
        {
                if(TabEigenschaft.posInhalt("Kennung",TabEig2Bew.getInhalt("Kennung2")) && TabBewegungstyp.posInhalt("Kennung",TabEig2Bew.getInhalt("Kennung1")))
                {
                                Qry.add("AIC_Bewegungstyp",TabBewegungstyp.getI("AIC"));
                                Qry.addWhere("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
                                Qry.update("Eigenschaft");
                }
                else
                        printError("DefImport(Eig2Bew): Zuordnung zwischen <"+TabEig2Bew.getInhalt("Kennung2")+"-"+TabEig2Bew.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
        }
        g.printExec("*TabEig2Rolle");
        if(TabEig2Rolle!=null)
        for(TabEig2Rolle.moveFirst();!TabEig2Rolle.eof();TabEig2Rolle.moveNext())
        {
                if(TabEigenschaft.posInhalt("Kennung",TabEig2Rolle.getInhalt("Kennung2")) && TabRolle.posInhalt("Kennung",TabEig2Rolle.getInhalt("Kennung1")))
                {
                                Qry.add("AIC_Rolle",TabRolle.getI("AIC"));
                                Qry.addWhere("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
                                Qry.update("Eigenschaft");
                }
                else
                        printError("DefImport(Eig2Rolle): Zuordnung zwischen <"+TabEig2Rolle.getInhalt("Kennung2")+"-"+TabEig2Rolle.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
        }
        g.printExec("*TabEig2Eig");
	if(TabEig2Eig!=null)
	for(TabEig2Eig.moveFirst();!TabEig2Eig.eof();TabEig2Eig.moveNext())
	{
		if(TabEigenschaft.posInhalt("Kennung",TabEig2Eig.getInhalt("Kennung2")))
                {
                  int iEig=TabEigenschaft.getI("AIC");
                      if (TabEigenschaft.posInhalt("Kennung",TabEig2Eig.getInhalt("Kennung1")))
                      {
                        Qry.add("Eig_AIC_Eigenschaft", TabEigenschaft.getI("AIC"));
                        Qry.addWhere("AIC_Eigenschaft", iEig);
                        Qry.update("Eigenschaft");
                      }
                      else
			printError("DefImport(Eig2Eig): Zuordnung zwischen <"+TabEig2Eig.getInhalt("Kennung2")+"-*"+TabEig2Eig.getInhalt("Kennung1")+"*> konnte nicht hergestellt werden");
		}
		else
			printError("DefImport(Eig2Eig): Zuordnung zwischen <*"+TabEig2Eig.getInhalt("Kennung2")+"*-"+TabEig2Eig.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	}
	g.clockInfo("Eigenschaft-Relationen",lClock);
	g.printExec("*TabStt2Eig");
    if(TabStt2Eig!=null)
	  for(TabStt2Eig.moveFirst();!TabStt2Eig.eof();TabStt2Eig.moveNext())
	  {
		if(TabEigenschaft.posInhalt("Kennung",TabStt2Eig.getInhalt("Kennung1")) && TabStammtyp.posInhalt("Kennung",TabStt2Eig.getInhalt("Kennung2")))
		{
				Qry.addWhere("AIC_Stammtyp",TabStammtyp.getI("AIC"));
				Qry.add("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
				Qry.update("Stammtyp");
		}
		else
			printError("DefImport(Stt2Eig): Zuordnung zwischen <"+TabStt2Eig.getInhalt("Kennung2")+"-"+TabStt2Eig.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	  }
    g.printExec("*TabProg2Eig");
    int iProgOld=0;
//    TabProg2Eig.showGrid("TabProg2Eig");
    if(TabProg2Eig!=null)
	  for(TabProg2Eig.moveFirst();!TabProg2Eig.eof();TabProg2Eig.moveNext())
	  {
		int iProg=g.getCodeAic("Programm", TabProg2Eig.getS("Kennung2"));		
		if (iProg>0)
		{
			if (iProgOld!=iProg)
			{
				iProgOld=iProg;
				g.exec("delete from Prog2_Zuordnung where aic_code="+iProg);
			}
			if(TabEigenschaft.posInhalt("Kennung",TabProg2Eig.getInhalt("Kennung1")))
			{
					Qry.add("AIC_Code",iProg);
					Qry.add("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
					Qry.add("Reihenfolge",TabProg2Eig.getI("Reihenfolge"));
					Qry.insert("Prog2_Zuordnung",false);
			}
			else
				printError("DefImport(Prog2Eig): Zuordnung zwischen <"+TabProg2Eig.getInhalt("Kennung2")+"-"+TabProg2Eig.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
		}
	  }
    g.printExec("*TabProg2Stt");
    iProgOld=0;
//    TabProg2Stt.showGrid("TabProg2Stt");
    if(TabProg2Stt!=null)
	  for(TabProg2Stt.moveFirst();!TabProg2Stt.eof();TabProg2Stt.moveNext())
	  {
		  int iProg=g.getCodeAic("Programm", TabProg2Stt.getS("Kennung2"));
		  if (iProg>0)
		  {
			if (iProgOld!=iProg)
			{
				iProgOld=iProg;
				g.exec("delete from Prog_Stt_Zuordnung where aic_code="+iProg);
			}
			if(TabStammtyp.posInhalt("Kennung",TabProg2Stt.getInhalt("Kennung1")))
			{
					Qry.add("AIC_Code",iProg);
					Qry.add("AIC_Stammtyp",TabStammtyp.getI("AIC"));
					Qry.add("Reihenfolge",TabProg2Stt.getI("Reihenfolge"));
					Qry.insert("Prog_Stt_Zuordnung",false);
			}
			else
				printError("DefImport(Prog2Stt): Zuordnung zwischen <"+TabProg2Stt.getInhalt("Kennung2")+"-"+TabProg2Stt.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
		  }
	  }
	g.printExec("*TabStt2Status");
    if(TabStt2Status!=null && TabStatus!=null)
	  for(TabStt2Status.moveFirst();!TabStt2Status.eof();TabStt2Status.moveNext())
	  {
		if(TabStatus.posInhalt("Kennung",TabStt2Status.getInhalt("Kennung1")) && TabStammtyp.posInhalt("Kennung",TabStt2Status.getInhalt("Kennung2")))
		{
				Qry.addWhere("AIC_Stammtyp",TabStammtyp.getI("AIC"));
				Qry.add("AIC_Status",TabStatus.getI("AIC"));
				Qry.update("Stammtyp");
		}
		else
			printError("DefImport(Stt2Status): Zuordnung zwischen <"+TabStt2Status.getInhalt("Kennung2")+"-"+TabStt2Status.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	  }
    g.printExec("*TabStt2Aufgabe");
    if(TabStt2Aufgabe!=null)
	  for(TabStt2Aufgabe.moveFirst();!TabStt2Aufgabe.eof();TabStt2Aufgabe.moveNext())
	  {
			if(TabBegriff.posInhalt("Kennung",TabStt2Aufgabe.getInhalt("Kennung1")) && TabStammtyp.posInhalt("Kennung",TabStt2Aufgabe.getInhalt("Kennung2")))
			{
					Qry.addWhere("AIC_Stammtyp",TabStammtyp.getI("AIC"));
					Qry.add("Aufgabe",TabBegriff.getI("AIC"));
					Qry.update("Stammtyp");
			}
			else
				printError("DefImport(Stt2Aufgabe): Zuordnung zwischen <"+TabStt2Aufgabe.getInhalt("Kennung2")+"-"+TabStt2Aufgabe.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	  }
	g.printExec("*TabStt2Stt");
	if (TabStammtyp != null)
	{
		java.util.Vector<Object> VecStt = TabStammtyp.getVecSpalte("AIC");
		if(VecStt.size()>0 && bNormal)
			Qry.exec("update Stammtyp set sta_aic_stammtyp=null where aic_Stammtyp "+Static.SQL_in(VecStt));
		if(TabStt2Stt!=null)
		{
			for(TabStt2Stt.moveFirst();!TabStt2Stt.eof();TabStt2Stt.moveNext())
			{
				if(TabStammtyp.posInhalt("Kennung",TabStt2Stt.getInhalt("Kennung1")))
				{
					int iSta_AIC_Stammtyp = TabStammtyp.getI("AIC");
					if(TabStammtyp.posInhalt("Kennung",TabStt2Stt.getInhalt("Kennung2")))
					{
							Qry.addWhere("AIC_Stammtyp",TabStammtyp.getI("AIC"));
							Qry.add("Sta_AIC_Stammtyp",iSta_AIC_Stammtyp);
							Qry.update("Stammtyp");
					}
					else
						printError("DefImport(Stt2Stt): Zuordnung2 zwischen <"+TabStt2Stt.getInhalt("Kennung2")+"-"+TabStt2Stt.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
				}
				else
					printError("DefImport(Stt2Stt): Zuordnung1 zwischen <"+TabStt2Stt.getInhalt("Kennung2")+"-"+TabStt2Stt.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
			}
		}
		g.clockInfo("Stammtyp-Relationen",lClock);
	
        g.printExec("*TabRol2Rol");
        java.util.Vector<Object> VecRolle = TabRolle.getVecSpalte("AIC");
        if(VecStt.size()>0)
                Qry.exec("update Rolle set Rol_aic_Rolle=null where aic_Rolle "+Static.SQL_in(VecRolle));
        if(TabRol2Rol!=null)
        {
                for(TabRol2Rol.moveFirst();!TabRol2Rol.eof();TabRol2Rol.moveNext())
                {
                        if(TabRolle.posInhalt("Kennung",TabRol2Rol.getInhalt("Kennung1")))
                        {
                                int iSta_AIC_Stammtyp = TabRolle.getI("AIC");
                                if(TabRolle.posInhalt("Kennung",TabRol2Rol.getInhalt("Kennung2")))
                                {
                                                Qry.addWhere("AIC_Rolle",TabRolle.getI("AIC"));
                                                Qry.add("Rol_AIC_Rolle",iSta_AIC_Stammtyp);
                                                Qry.update("Rolle");
                                }
                                else
                                        printError("DefImport(Rol2Rol): Zuordnung2 zwischen <"+TabRol2Rol.getInhalt("Kennung2")+"-"+TabRol2Rol.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
                        }
                        else
                                printError("DefImport(Rol2Rol): Zuordnung1 zwischen <"+TabRol2Rol.getInhalt("Kennung2")+"-"+TabRol2Rol.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
                }
        }
        g.clockInfo("Rolle-Relationen",lClock);
	}
	g.printExec("*TabSchrift2Farbe");
	if(TabSchrift2Farbe!=null)
	for(TabSchrift2Farbe.moveFirst();!TabSchrift2Farbe.eof();TabSchrift2Farbe.moveNext())
	{
		if(TabFarbe.posInhalt("Kennung",TabSchrift2Farbe.getInhalt("Kennung1")) && TabSchrift.posInhalt("Kennung",TabSchrift2Farbe.getInhalt("Kennung2")))
		{
			Qry.addWhere("AIC_Schrift",TabSchrift.getI("AIC"));
			Qry.add("AIC_Farbe",TabFarbe.getI("AIC"));
			Qry.update("Schrift");
		}
			else
				printError("DefImport(Schrift2Farbe): Zuordnung zwischen <"+TabSchrift2Farbe.getInhalt("Kennung2")+"-"+TabSchrift2Farbe.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	}
        g.printExec("*TabSchrift2Farbe2");
	if(TabSchrift2Farbe2!=null)
	for(TabSchrift2Farbe2.moveFirst();!TabSchrift2Farbe2.eof();TabSchrift2Farbe2.moveNext())
	{
		if(TabFarbe.posInhalt("Kennung",TabSchrift2Farbe2.getInhalt("Kennung1")) && TabSchrift.posInhalt("Kennung",TabSchrift2Farbe2.getInhalt("Kennung2")))
		{
			Qry.addWhere("AIC_Schrift",TabSchrift.getI("AIC"));
			Qry.add("Far_AIC_Farbe",TabFarbe.getI("AIC"));
			Qry.update("Schrift");
		}
			else
				printError("DefImport(Schrift2Farbe): Zuordnung zwischen <"+TabSchrift2Farbe2.getInhalt("Kennung2")+"-"+TabSchrift2Farbe2.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
	}
	g.printExec("*TabBew_Zuordnung");
	if(TabBew_Zuordnung!=null)
	{
		Vector<Object> VecDelete = new Vector<Object>();
		for(TabBew_Zuordnung.moveFirst();!TabBew_Zuordnung.eof();TabBew_Zuordnung.moveNext())
		{
			if(TabBewegungstyp.posInhalt("Kennung",TabBew_Zuordnung.getInhalt("Kennung2")) && !VecDelete.contains(TabBewegungstyp.getInt("AIC")))
				VecDelete.addElement(TabBewegungstyp.getInt("AIC"));
		}
		if(VecDelete.size()>0)
			Qry.exec("delete from Bew_Zuordnung where aic_Bewegungstyp "+Static.SQL_in(VecDelete));
		for(TabBew_Zuordnung.moveFirst();!TabBew_Zuordnung.eof();TabBew_Zuordnung.moveNext())
		{
			if(TabBewegungstyp.posInhalt("Kennung",TabBew_Zuordnung.getInhalt("Kennung2")) && TabEigenschaft.posInhalt("Kennung",TabBew_Zuordnung.getInhalt("Kennung1")))// && !TabBewegungstyp.getB("Vorhanden"))
			{
				Qry.add("AIC_Bewegungstyp",TabBewegungstyp.getI("AIC"));
				Qry.add("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
				Qry.add("Reihenfolge",TabBew_Zuordnung.getI("Reihenfolge"));
				Qry.insert("Bew_Zuordnung",false);
			}
		}
	}
	g.printExec("*TabStt_Zuordnung");
	if(TabStt_Zuordnung!=null)
	{
		//java.util.Vector VecDelete = TabStammtyp.getVecSpalte("AIC");
		//if(VecDelete.size()>0)
		//	Qry.exec("delete from Stammtyp_Zuordnung where aic_Stammtyp "+Static.SQL_in(VecDelete));
                int iSttAlt=0;
		for(TabStt_Zuordnung.moveFirst();!TabStt_Zuordnung.eof();TabStt_Zuordnung.moveNext())
		{
			//g.printInfo("Stt_Zuordnung: "+TabStt_Zuordnung.getInhalt("Kennung2")+" : "+TabStt_Zuordnung.getInhalt("Kennung1"));
			if(TabStammtyp.posInhalt("Kennung",TabStt_Zuordnung.getInhalt("Kennung2")) && TabEigenschaft.posInhalt("Kennung",TabStt_Zuordnung.getInhalt("Kennung1")))// && !TabStammtyp.getB("Vorhanden"))
			{
                          int iStt=TabStammtyp.getI("AIC");
                          if (iStt!=iSttAlt)
                          {
                            iSttAlt=iStt;
                            Qry.exec("delete from Stammtyp_Zuordnung where aic_Stammtyp="+iStt);
                          }
                          Qry.add("AIC_Stammtyp",iStt);
                          Qry.add("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
                          Qry.add("Reihenfolge",TabStt_Zuordnung.getI("Reihenfolge"));
                          Qry.insert("Stammtyp_Zuordnung",false);
			}
                        else
                        {
                          if (TabStammtyp.out())
                            printError("DefImport: Stammtyp " + TabStt_Zuordnung.getInhalt("Kennung2") + " für Stammtyp_Zuordnung nicht gefunden!");
                          if (TabEigenschaft.out())
                            printError("DefImport: Eigenschaft " + TabStt_Zuordnung.getInhalt("Kennung1") + " für Stammtyp_Zuordnung nicht gefunden!");
                        }

		}
	}
        g.printExec("*TabRol_Zuordnung");
        if(TabRol_Zuordnung!=null)
        {
          //TabRol_Zuordnung.showGrid();
                java.util.Vector VecDelete = TabRolle.getVecSpalte("AIC");
                if(VecDelete.size()>0)
                        Qry.exec("delete from Rolle_Zuordnung where aic_Rolle "+Static.SQL_in(VecDelete));

                for(TabRol_Zuordnung.moveFirst();!TabRol_Zuordnung.eof();TabRol_Zuordnung.moveNext())
                {
                        if(TabRolle.posInhalt("Kennung",TabRol_Zuordnung.getInhalt("Kennung2")) && TabEigenschaft.posInhalt("Kennung",TabRol_Zuordnung.getInhalt("Kennung1")))// && !TabStammtyp.getB("Vorhanden"))
                        {
                                Qry.add("AIC_Rolle",TabRolle.getI("AIC"));
                                Qry.add("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
                                Qry.insert("Rolle_Zuordnung",false);
                        }
                        else
                        {
                          if (TabRolle.out())
                            printError("DefImport: Rolle " + TabRol_Zuordnung.getInhalt("Kennung2") + " für Rolle_Zuordnung nicht gefunden!");
                          if (TabEigenschaft.out())
                            printError("DefImport: Eigenschaft " + TabRol_Zuordnung.getInhalt("Kennung1") + " für Rolle_Zuordnung nicht gefunden!");
                        }
                }
        }
	g.printExec("*TabApplikation_Zuordnung");
	if(TabApplikation_Zuordnung!=null)
	{
		Vector<Object> VecDelete = new Vector<Object>();
		for(TabApplikation_Zuordnung.moveFirst();!TabApplikation_Zuordnung.eof();TabApplikation_Zuordnung.moveNext())
		{
			if(TabBegriff.posInhalt((Object)"Applikation",TabApplikation_Zuordnung.getInhalt("Kennung2")) && !VecDelete.contains(TabBegriff.getInt("AIC")))
				VecDelete.addElement(TabBegriff.getInt("AIC"));
		}
		if(VecDelete.size()>0)
			Qry.exec("delete from Applikation_Zuordnung where AIC_Begriff "+Static.SQL_in(VecDelete));

		for(TabApplikation_Zuordnung.moveFirst();!TabApplikation_Zuordnung.eof();TabApplikation_Zuordnung.moveNext())
		{
                  int iPosS=g.TabStammtypen.getPos("Kennung",TabApplikation_Zuordnung.getS("Kennung1").toUpperCase());
			if(iPosS>=0 && TabBegriff.posInhalt((Object)"Applikation",TabApplikation_Zuordnung.getInhalt("Kennung2")))// && !TabBegriff.getB("Vorhanden"))
			{
				Qry.add("AIC_Stammtyp",g.TabStammtypen.getI(iPosS,"AIC"));
				Qry.add("AIC_Begriff",TabBegriff.getI("AIC"));
				Qry.add("Reihenfolge",TabApplikation_Zuordnung.getI("Reihenfolge"));
				Qry.insert("Applikation_Zuordnung",false);
			}
		}
	}
	g.printExec("*TabGruppe_Zuordnung");
	if(TabGruppe_Zuordnung!=null)
	{
		Vector<Object> VecDelete = new Vector<Object>();
		for(TabGruppe_Zuordnung.moveFirst();!TabGruppe_Zuordnung.eof();TabGruppe_Zuordnung.moveNext())
		{
			if(TabBegriff.posInhalt((Object)"Gruppe",TabGruppe_Zuordnung.getInhalt("Kennung2")) && !VecDelete.contains(TabBegriff.getInt("AIC")))
				VecDelete.addElement(TabBegriff.getInt("AIC"));
		}
		if(VecDelete.size()>0)
			Qry.exec("delete from Gruppe_Zuordnung where AIC_Begriff "+Static.SQL_in(VecDelete));

		for(TabGruppe_Zuordnung.moveFirst();!TabGruppe_Zuordnung.eof();TabGruppe_Zuordnung.moveNext())
		{
			if(TabEigenschaft.posInhalt("Kennung",TabGruppe_Zuordnung.getInhalt("Kennung1")) && TabBegriff.posInhalt((Object)"Gruppe",TabGruppe_Zuordnung.getInhalt("Kennung2")))// && !TabBegriff.getB("Vorhanden"))
			{
				Qry.add("AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
				Qry.add("AIC_Begriff",TabBegriff.getI("AIC"));
				Qry.add("Reihenfolge",TabGruppe_Zuordnung.getI("Reihenfolge"));
				Qry.insert("Gruppe_Zuordnung",false);
			}
		}
	}
	g.printExec("*TabEig_Zuordnung");
	if(TabEig_Zuordnung!=null)
	{
		Vector<Object> VecDelete = new Vector<Object>();
		for(TabEig_Zuordnung.moveFirst();!TabEig_Zuordnung.eof();TabEig_Zuordnung.moveNext())
		{
			if(TabEigenschaft.posInhalt("Kennung",TabEig_Zuordnung.getInhalt("Kennung2")) && !VecDelete.contains(TabEigenschaft.getInt("AIC")))
				VecDelete.addElement(TabEigenschaft.getInt("AIC"));
		}
		if(VecDelete.size()>0)
			Qry.exec("delete from Eigenschaft_Zuordnung where AIC_Eigenschaft "+Static.SQL_in(VecDelete));

		for(TabEig_Zuordnung.moveFirst();!TabEig_Zuordnung.eof();TabEig_Zuordnung.moveNext())
		{
			if(TabEigenschaft.posInhalt("Kennung",TabEig_Zuordnung.getInhalt("Kennung2")))// && !TabEigenschaft.getB("Vorhanden"))
			{
				int iAIC_Eigenschaft=TabEigenschaft.getI("AIC");
				if(TabEigenschaft.posInhalt("Kennung",TabEig_Zuordnung.getInhalt("Kennung1")))
				{
					Qry.add("Eig_AIC_Eigenschaft",TabEigenschaft.getI("AIC"));
					Qry.add("AIC_Eigenschaft",iAIC_Eigenschaft);
					Qry.add("Reihenfolge",TabEig_Zuordnung.getI("Reihenfolge"));
					Qry.insert("Eigenschaft_Zuordnung",false);
				}
			}
		}
	}
	/*g.printExec("*TabAIC_Beg_Zuordnung");
	if(TabAIC_Beg_Zuordnung!=null)
	{
		Vector VecDelete = new Vector();
		for(TabAIC_Beg_Zuordnung.moveFirst();!TabAIC_Beg_Zuordnung.eof();TabAIC_Beg_Zuordnung.moveNext())
		{
			if(TabBegriff.posInhalt(TabAIC_Beg_Zuordnung.getInhalt("Codegruppe"),TabAIC_Beg_Zuordnung.getInhalt("Kennung2")) && !VecDelete.contains(TabBegriff.getInt("AIC")))
				VecDelete.addElement(TabBegriff.getInt("AIC"));
		}
		if(VecDelete.size()>0)
			Qry.exec("delete from Begriff_Zuordnung where Beg_AIC_Begriff "+Static.SQL_in(VecDelete));

		for(TabAIC_Beg_Zuordnung.moveFirst();!TabAIC_Beg_Zuordnung.eof();TabAIC_Beg_Zuordnung.moveNext())
		{
			if(TabBegriff.posInhalt(TabAIC_Beg_Zuordnung.getInhalt("Codegruppe"),TabAIC_Beg_Zuordnung.getInhalt("Kennung2")))
			{
				int iAIC_Zuord=TabBegriff.getI("AIC");
				if(TabBegriff.posInhalt(TabAIC_Beg_Zuordnung.getInhalt("Begriffgruppe"),TabAIC_Beg_Zuordnung.getInhalt("Kennung1")))
				{
					Qry.add("AIC_Begriff",TabBegriff.getI("AIC"));	// Applikation
					Qry.add("Beg_AIC_Begriff",iAIC_Zuord);			// Modell
					Qry.add("Reihenfolge",TabAIC_Beg_Zuordnung.getI("Reihenfolge"));
					Qry.insert("Begriff_Zuordnung",false);
				}
				else
					printError("DefImport(AIC_Beg_Zuordnung): Applikation <"+TabAIC_Beg_Zuordnung.getInhalt("Kennung1")+"> nicht gefunden!");
			}
			else
					printError("DefImport(AIC_Beg_Zuordnung): Modell <"+TabAIC_Beg_Zuordnung.getInhalt("Kennung2")+"> nicht gefunden!");
		}
	}*/

	g.printExec("*TabBeg_AIC_Beg_Zuordnung");
	if(TabBeg_AIC_Beg_Zuordnung!=null)
	{
		Vector<Object> VecDelete = new Vector<Object>();
		for(TabBeg_AIC_Beg_Zuordnung.moveFirst();!TabBeg_AIC_Beg_Zuordnung.eof();TabBeg_AIC_Beg_Zuordnung.moveNext())
		{
			if(TabBegriff.posInhalt((Object)"Applikation"/*TabBeg_AIC_Beg_Zuordnung.getInhalt("Begriffgruppe")*/,TabBeg_AIC_Beg_Zuordnung.getInhalt("Kennung1")) && !VecDelete.contains(TabBegriff.getInt("AIC")))
				VecDelete.addElement(TabBegriff.getInt("AIC"));
		}
                if(VecDelete.size()>0)
                  Qry.exec("delete from begriff_zuordnung where aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame")+") and "+g.in("beg_aic_begriff",VecDelete));
			//Qry.exec("delete from Begriff_Zuordnung from Begriff_Zuordnung z join begriff on z.aic_begriff=begriff.aic_begriff where aic_begriffgruppe="+
			//	g.TabBegriffgruppen.getAic("Frame")+" and BEG_AIC_Begriff "+Static.SQL_in(VecDelete));

		for(TabBeg_AIC_Beg_Zuordnung.moveFirst();!TabBeg_AIC_Beg_Zuordnung.eof();TabBeg_AIC_Beg_Zuordnung.moveNext())
		{
                  int iAIC_Zuord=g.getBegriffAicS(TabBeg_AIC_Beg_Zuordnung.getS("Codegruppe"),TabBeg_AIC_Beg_Zuordnung.getS("Kennung2"));
			if(iAIC_Zuord>0)
			{
				if(TabBegriff.posInhalt(TabBeg_AIC_Beg_Zuordnung.getInhalt("Begriffgruppe"),TabBeg_AIC_Beg_Zuordnung.getInhalt("Kennung1")))
				{
					Qry.add("Beg_AIC_Begriff",TabBegriff.getI("AIC"));	// Applikation
					Qry.add("AIC_Begriff",iAIC_Zuord);			// Frame
					Qry.add("Reihenfolge",TabBeg_AIC_Beg_Zuordnung.getI("Reihenfolge"));
					Qry.insert("Begriff_Zuordnung",false);
				}
				else
					printError("DefImport(TabBeg_AIC_Beg_Zuordnung): Applikation <"+TabBeg_AIC_Beg_Zuordnung.getInhalt("Kennung1")+"> nicht gefunden!");
			}
			//else
			//		g.testInfo("DefImport(TabBeg_AIC_Beg_Zuordnung): Formular <"+TabBeg_AIC_Beg_Zuordnung.getInhalt("Kennung2")+"> nicht gefunden!");
		}
	}
        g.printExec("*TabAppl2_Zuordnung");
        if(TabAppl2_Zuordnung!=null)
	{
          Vector<Object> VecDelete = new Vector<Object>();
          for(TabAppl2_Zuordnung.moveFirst();!TabAppl2_Zuordnung.eof();TabAppl2_Zuordnung.moveNext())
          {
              if(TabBegriff.posInhalt(TabAppl2_Zuordnung.getInhalt("Begriffgruppe"),TabAppl2_Zuordnung.getInhalt("Kennung1")) && !VecDelete.contains(TabBegriff.getInt("AIC")))
                VecDelete.addElement(TabBegriff.getInt("AIC"));
          }
          g.testInfo("VecDelete für TabAppl2_Zuordnung="+VecDelete);
          if(VecDelete.size()>0)
            g.exec("delete from begriff_zuordnung where Beg_aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Applikation")+") and "+g.in("aic_begriff",VecDelete));

          for(TabAppl2_Zuordnung.moveFirst();!TabAppl2_Zuordnung.eof();TabAppl2_Zuordnung.moveNext())
                {
                  int iAIC_Zuord=g.getBegriffAicS(TabAppl2_Zuordnung.getS("Codegruppe"),TabAppl2_Zuordnung.getS("Kennung2"));
                        if(iAIC_Zuord>0)
                        {
                                if(TabBegriff.posInhalt(TabAppl2_Zuordnung.getInhalt("Begriffgruppe"),TabAppl2_Zuordnung.getInhalt("Kennung1")))
                                {
                                        Qry.add("AIC_Begriff",TabBegriff.getI("AIC"));	// Applikation
                                        Qry.add("Beg_AIC_Begriff",iAIC_Zuord);		// Sub-Applikation
                                        Qry.add("Reihenfolge",TabAppl2_Zuordnung.getI("Reihenfolge"));
                                        Qry.insert("Begriff_Zuordnung",false);
                                }
                                else
                                        printError("DefImport(TabBeg_AIC_Beg_Zuordnung): Applikation <"+TabAppl2_Zuordnung.getInhalt("Kennung1")+"> nicht gefunden!");
                        }
                        //else
                        //                g.testInfo("DefImport(TabBeg_AIC_Beg_Zuordnung): Applikation <"+TabAppl2_Zuordnung.getInhalt("Kennung2")+"> nicht gefunden!");
                }
        }
	g.printExec("*TabBeg2AIC_Beg_Zuordnung");
	if(TabBeg2AIC_Beg_Zuordnung!=null)
	{
		for(TabBeg2AIC_Beg_Zuordnung.moveFirst();!TabBeg2AIC_Beg_Zuordnung.eof();TabBeg2AIC_Beg_Zuordnung.moveNext())
		{
			if(TabBegriff.posInhalt(TabBeg2AIC_Beg_Zuordnung.getInhalt("Codegruppe"),TabBeg2AIC_Beg_Zuordnung.getInhalt("Kennung2")))
			{
				int iPos=g.getPosBegriff(TabBeg2AIC_Beg_Zuordnung.getS("Begriffgruppe"),TabBeg2AIC_Beg_Zuordnung.getS("Kennung1"));
				if(iPos>=0)
				{
					if(!SQL.exists(g,"select aic_begriff from Begriff_Zuordnung where aic_begriff="+TabBegriff.getI("AIC")+" and Beg_AIC_Begriff="+g.TabBegriffe.getI(iPos,"AIC")))
					{
						Qry.add("Beg_AIC_Begriff",g.TabBegriffe.getI(iPos,"AIC"));	// Applikation
						Qry.add("AIC_Begriff",TabBegriff.getI("AIC"));		// Frame
						Qry.add("Reihenfolge",TabBeg2AIC_Beg_Zuordnung.getI("Reihenfolge"));
						Qry.insert("Begriff_Zuordnung",false);
					}
				}
				//else
				//	g.testInfo("DefImport(TabBeg2AIC_Beg_Zuordnung): Applikation <"+TabBeg2AIC_Beg_Zuordnung.getInhalt("Kennung1")+"> nicht gefunden!");
			}
			else
					printError("DefImport(TabBeg2AIC_Beg_Zuordnung): Formular <"+TabBeg2AIC_Beg_Zuordnung.getInhalt("Kennung2")+"> nicht gefunden!");
		}
	}
        g.printExec("*Formularmenge");
        if(TabBeg3AIC_Beg_Zuordnung!=null)
        {
                Vector<Object> VecDelete = new Vector<Object>();
                for(TabBeg3AIC_Beg_Zuordnung.moveFirst();!TabBeg3AIC_Beg_Zuordnung.eof();TabBeg3AIC_Beg_Zuordnung.moveNext())
                {
                        if(TabBegriff.posInhalt((Object)"Formularmenge"/*TabBeg_AIC_Beg_Zuordnung.getInhalt("Begriffgruppe")*/,TabBeg3AIC_Beg_Zuordnung.getInhalt("Kennung1")) && !VecDelete.contains(TabBegriff.getInt("AIC")))
                                VecDelete.addElement(TabBegriff.getInt("AIC"));
                }
                if(VecDelete.size()>0)
                        Qry.exec("delete from Begriff_Zuordnung where AIC_Begriff "+Static.SQL_in(VecDelete));

                for(TabBeg3AIC_Beg_Zuordnung.moveFirst();!TabBeg3AIC_Beg_Zuordnung.eof();TabBeg3AIC_Beg_Zuordnung.moveNext())
                {
                  int iAIC_Zuord=g.getBegriffAicS(TabBeg3AIC_Beg_Zuordnung.getS("Codegruppe"),TabBeg3AIC_Beg_Zuordnung.getS("Kennung2"));
                        if(iAIC_Zuord>0)
                        {
                                if(TabBegriff.posInhalt(TabBeg3AIC_Beg_Zuordnung.getInhalt("Begriffgruppe"),TabBeg3AIC_Beg_Zuordnung.getInhalt("Kennung1")))
                                {
                                        Qry.add("AIC_Begriff",TabBegriff.getI("AIC"));	// Menge
                                        Qry.add("Beg_AIC_Begriff",iAIC_Zuord);		// Frame
                                        Qry.add("Reihenfolge",TabBeg3AIC_Beg_Zuordnung.getI("Reihenfolge"));
                                        Qry.insert("Begriff_Zuordnung",false);
                                }
                                else
                                        printError("DefImport(TabBeg3AIC_Beg_Zuordnung): Menge <"+TabBeg3AIC_Beg_Zuordnung.getInhalt("Kennung1")+"> nicht gefunden!");
                        }
                        //else
                        //                g.testInfo("DefImport(TabBeg3AIC_Beg_Zuordnung): Formular <"+TabBeg3AIC_Beg_Zuordnung.getInhalt("Kennung2")+"> nicht gefunden!");
                }
        }
	g.printExec("*TabDruck_Zuordnung");
	if(TabDruck_Zuordnung!=null)
	{
		Vector<Object> VecDelete = new Vector<Object>();
		for(TabDruck_Zuordnung.moveFirst();!TabDruck_Zuordnung.eof();TabDruck_Zuordnung.moveNext())
		{
			if(TabBegriff.posInhalt((Object)"Druck",TabDruck_Zuordnung.getInhalt("Kennung1")) && !VecDelete.contains(TabBegriff.getInt("AIC")))
				VecDelete.addElement(TabBegriff.getInt("AIC"));
		}
		if(VecDelete.size()>0)
			Qry.exec("delete from Druck_Zuordnung where AIC_Begriff "+Static.SQL_in(VecDelete));

		for(TabDruck_Zuordnung.moveFirst();!TabDruck_Zuordnung.eof();TabDruck_Zuordnung.moveNext())
		{
			if(TabBegriff.posInhalt((Object)"Druck",TabDruck_Zuordnung.getInhalt("Kennung1")))
			{
				if(TabAbschnitt.posInhalt("Kennung",TabDruck_Zuordnung.getInhalt("Kennung2")))
				{
					Qry.add("AIC_Begriff",TabBegriff.getI("AIC"));
					Qry.add("AIC_Abschnitt",TabAbschnitt.getI("AIC"));
					Qry.add("reihenfolge",TabDruck_Zuordnung.getI("reihenfolge"));
					Qry.insert("Druck_Zuordnung",false);
				}
			}
		}
	}
        if (bNormal)
          Reinigung.DelDrucke(g,false);
	g.printExec("*Zuordnung Ende");
	g.clockInfo("Zuordnungen",lClock);

	g.printExec("*Abfragen Start");
        if (Gau != null)
          Gau.setText("Abfrage",2);
        Vector<Object> VecWeg=new Vector<Object>();
    Vector<Integer> VecAbf=new Vector<Integer>();
	if(OutAbfrage!=null)
	{
		Vector VecChildren = ((JCOutlinerFolderNode)OutAbfrage.getRootNode()).getChildren();
		//g.printInfo("VecChildren: "+VecChildren);
		for(int i=0;VecChildren!=null && i<VecChildren.size();i++)
		{
			JCOutlinerNode NodeAbfrage = (JCOutlinerNode)VecChildren.elementAt(i);
			Vector VecVisible = (Vector)NodeAbfrage.getLabel();
			/*
			String s = "VecVisible: "+VecVisible.size();
			for(int ii=0;ii<VecVisible.size();ii++)
				s+=","+VecVisible.elementAt(ii);
			g.printInfo(s);*/
			String sKennungAbfrage = (String)VecVisible.elementAt(0);
			int iAIC_Abfrage = SQL.getInteger(g,"SELECT aic_abfrage FROM Abfrage a JOIN Begriff b ON a.aic_begriff=b.aic_begriff JOIN Begriffgruppe bg ON b.aic_begriffgruppe=bg.aic_begriffgruppe WHERE bg.kennung='Abfrage' AND b.kennung='"+sKennungAbfrage+"'");
			if(iAIC_Abfrage==0)
			{
				int iAIC_Begriff = SQL.getInteger(g,"select aic_begriff from begriff b join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Abfrage' and b.kennung='"+sKennungAbfrage+"'");
				if(iAIC_Begriff==0)
				{
					printError("DefImport.DefImport(): Begriff <"+sKennungAbfrage+"> nicht gefunden!!!!");
					System.exit(1);
				}
				else
					Qry.add("aic_begriff",iAIC_Begriff);
				VecAbf.addElement(iAIC_Begriff);
			}
			else
				VecAbf.addElement(SQL.getInteger(g, "select aic_begriff from abfrage where aic_abfrage=?",0,""+iAIC_Abfrage));
			if(!TabBegriff.posInhalt((Object)"Abfrage",sKennungAbfrage))
				printError("Abfrage "+sKennungAbfrage+" nicht gefunden!");
			else
			{
			if(TabBegriff.getInhalt("edit")==null)
				Static.printError("Abfrage "+sKennungAbfrage+" enthält keinen Timestamp!");
                        //if (g.TestPC())
                        //  g.testInfo("Abfrage "+TabBegriff.getS("DefBezeichnung")+":"+(TabBegriff.getB("Vorhanden")? g.TS_String(TabBegriff.getInhalt("editold"))+"->"+g.TS_String(TabBegriff.getInhalt("edit")):"nicht vorhanden"));
			if(!bShow && (bAbfragen && (TabBegriff.getL("bits")&Abfrage.cstNichtUpdaten)==0 || !TabBegriff.getB("Vorhanden") ||
                           (TabBegriff.getL("bits")&Abfrage.cstNichtUpdaten)==0 && (TabBegriff.getInhalt("edit")==null || TabBegriff.getInhalt("editold")==null || TabBegriff.getDate("edit").getTime()>TabBegriff.getDate("editold").getTime())))
			{
				bOk=true;
                                //g.testInfo("lege Abfrage an:"+TabBegriff.getS("Begriff"));
                                addInfo(TabInfo,iAIC_Abfrage>0,"Abfrage",TabBegriff.getS("DefBezeichnung"),TabBegriff.getS("Begriff"));
                if(VecVisible.elementAt(2)!=null)
				  Qry.add("Abits2",Long.parseLong((String)VecVisible.elementAt(2)));
				Qry.add0("autorefresh",Sort.geti(VecVisible,3));
//				if(VecVisible.elementAt(3)!=null)
//					Qry.add0("autorefresh",new Integer((String)VecVisible.elementAt(3)));
//				else
//					Qry.add("autorefresh","");
				Qry.add0("spalten",Sort.geti(VecVisible,4));
				Qry.add0("Anzahl",Sort.geti(VecVisible,5));
//				if(VecVisible.elementAt(4)!=null)
//					Qry.add("spalten",new Integer((String)VecVisible.elementAt(4)));
                                java.util.Vector VecSpalten=null;
                                java.util.Vector VecNicht=null;
                                if (iAIC_Abfrage>0)
                                {
                                  VecSpalten = SQL.getVector("select aic_spalte from spalte where aic_abfrage=" + iAIC_Abfrage, g);
                                  VecNicht = SQL.getVector("select distinct aic_spalte from befehl where aic_spalte" + Static.SQL_in(VecSpalten), g);
                                  for (int i2 = 0; i2 < VecNicht.size(); i2++)
                                  {
                                    VecWeg.addElement(VecNicht.elementAt(i2));
                                    VecSpalten.remove(VecNicht.elementAt(i2));
                                  }
                                }
				if(iAIC_Abfrage==0)
					iAIC_Abfrage = Qry.insert("Abfrage",true);
				else
				{
					Qry.add("AIC_Modell","");
                                        Qry.add("MOD_AIC_Modell","");
					Qry.update("Abfrage",iAIC_Abfrage);

                                        if (VecSpalten.size()>0)
                                        {
                                          Qry.exec("delete from SPALTE_BERECHNUNG where aic_spalte" + Static.SQL_in(VecSpalten));
                                          //Qry.exec("delete from spalte_zuordnung where aic_spalte" + Static.SQL_in(VecSpalten));
                                          Qry.exec("delete from fixeigenschaft where aic_spalte" + Static.SQL_in(VecSpalten));
                                        }
                                        java.util.Vector<Integer> VecBedingung=SQL.getVector("select aic_bedingung from Bedingung where aic_abfrage="+iAIC_Abfrage,g);
                                        if (VecBedingung.size()>0)
                                          Qry.exec("delete from fixeigenschaft where aic_bedingung"+Static.SQL_in(VecBedingung));
					//Qry.exec("DELETE FROM FixEigenschaft FROM Spalte s RIGHT OUTER JOIN FixEigenschaft f ON s.aic_spalte=f.aic_spalte LEFT OUTER JOIN Bedingung bed ON f.aic_bedingung=bed.aic_bedingung WHERE s.AIC_Abfrage="+iAIC_Abfrage+" OR bed.AIC_Abfrage="+iAIC_Abfrage);
					//Qry.exec("DELETE FROM Spalte_Zuordnung FROM Spalte s JOIN Spalte_Zuordnung sz ON s.aic_spalte=sz.aic_spalte WHERE s.AIC_Abfrage="+iAIC_Abfrage);
					//Qry.exec("DELETE FROM Spalte_Berechnung FROM Spalte s JOIN Spalte_Berechnung sz ON s.aic_spalte=sz.aic_spalte WHERE s.AIC_Abfrage="+iAIC_Abfrage);
					Qry.exec("DELETE FROM Bedingung WHERE AIC_Abfrage="+iAIC_Abfrage);
				}

				for(TabBedingung.posInhalt("abfrage",sKennungAbfrage);!TabBedingung.eof() && TabBedingung.getS("abfrage").equals(sKennungAbfrage);TabBedingung.moveNext())
				{
					String sVergleichswert = TabBedingung.getS("vergleichswert");
					String sTabelle = TabBedingung.getS("tabelle");
                                        if (sVergleichswert.startsWith("*"))
                                          g.progInfo("verwende Vergleichswert "+sVergleichswert);
					else if(sTabelle.equals("Code"))
                                        {
                                          int iCode=SQL.getInteger(g,"select aic_code from code c join begriffgruppe bg on c.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='" +
                                              TabBedingung.getS("gruppe") + "' and c.kennung='" + sVergleichswert + "'");
                                          if (iCode<=0)
                                          {
                                            int iBG=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Anlage'");
                                            Qry.add("kennung", sVergleichswert);
                                            Qry.add("aic_begriffgruppe", iBG);
                                            iCode = Qry.insert("code", true);
                                          }
                                          sVergleichswert = "" +iCode;
                                        }
					else if(sTabelle.equals("Benutzer"))
						sVergleichswert = ""+SQL.getInteger(g,"select aic_benutzer from benutzer b join mandant m on b.aic_mandant=m.aic_mandant where m.kennung='"+TabBedingung.getS("gruppe")+"' and b.kennung='"+sVergleichswert+"'");
					else if(sTabelle.equals("Stamm"))
						sVergleichswert = ""+SQL.getInteger(g,"select aic_stamm from stammview s join stammtyp st on s.aic_stammtyp=st.aic_stammtyp where st.kennung='"+TabBedingung.getS("gruppe")+"' and s.kennung='"+sVergleichswert+"'");
					else if(sTabelle.equals("Mandant"))
						sVergleichswert = ""+SQL.getInteger(g,"select aic_mandant from mandant where kennung='"+sVergleichswert+"'");
                                        else if(sTabelle.equals("Auswahl"))
                                          sVergleichswert = ""+SQL.getInteger(g,"select aic_auswahl from auswahl a join eigenschaft e on a.aic_eigenschaft=e.aic_eigenschaft where e.kennung='"+TabBedingung.getS("gruppe")+"' and a.kennung='"+sVergleichswert+"'");
					if((sTabelle.equals("Code") || sTabelle.equals("Benutzer") || sTabelle.equals("Stamm") || sTabelle.equals("Mandant") || sTabelle.equals("Auswahl")) && sVergleichswert.equals("0"))
					{
						bOk=false;
						String s="DefImport(Abfrage.Bedingung): Abfrage <"+sKennungAbfrage+"> Aic von Vergleichswert <"+TabBedingung.getS("vergleichswert")+","+TabBedingung.getS("gruppe")+","+sTabelle+"> konnte nicht gefunden werden";
						printError(s);
                                                iBits|=2;
						//g.exec("update begriff b join abfrage a on a.aic_begriff=b.aic_begriff set importzeit=null where aic_abfrage="+iAIC_Abfrage);
					}

					Qry.add("vergleichswert",sVergleichswert);
                                        Qry.add("bbits",TabBedingung.getI("bbits"));
					Qry.add("aic_abfrage",iAIC_Abfrage);
					if(TabBedingung.getI("bed_aic_bedingung")>0)
					{
						TabBedingung.push();
						if(TabBedingung.posInhalt("aic_bedingung",TabBedingung.getI("bed_aic_bedingung")))
							Qry.add("bed_aic_bedingung",TabBedingung.getI("new_aic_bedingung"));
						TabBedingung.pop();
					}
					Qry.add("aic_begriff",g.getBegriffAicS("Vergleich",TabBedingung.getS("vo")));
					TabBedingung.setInhalt("new_aic_bedingung",Qry.insert("Bedingung",true));
					TabBedingung.setInhalt("edit",Boolean.TRUE);
				}
                                //TabBedingung.showGrid("TabBedingung");
				Tabellenspeicher TabSubSp=null;
				for(TabSpalte.posInhalt("abfrage",sKennungAbfrage);!TabSpalte.eof() && TabSpalte.getS("abfrage").equals(sKennungAbfrage);TabSpalte.moveNext())
				{
					int iAIC_Spalte = Qry.getInteger("SELECT AIC_Spalte FROM SPALTE WHERE AIC_Abfrage="+iAIC_Abfrage+" AND nummer="+TabSpalte.getI("nummer"));
                                        if (bBezeichnung && iAIC_Spalte>0)
                                          Qry.exec("delete from bezeichnung where aic_tabellenname="+g.TabTabellenname.getAic("SPALTE")+" and aic_fremd="+iAIC_Spalte);

					Qry.add("aic_abfrage",iAIC_Abfrage);
					Qry.add("kennung",TabSpalte.getS("kennung"));
					Qry.add0("soritiert",TabSpalte.getI("sortiert"));
					Qry.add("laenge",TabSpalte.getI("laenge"));
					Qry.add("weblaenge",TabSpalte.getI("weblaenge"));
					Qry.add("reihenfolge",TabSpalte.getI("reihenfolge"));
					Qry.add("nummer",TabSpalte.getI("nummer"));
					Qry.add("bits",TabSpalte.getI("bits"));
                                        g.TabBegriffe.sAIC="Kennung";
                                        int iPos=g.getPosBegriff("Format",TabSpalte.getS("format"));
                                        if(iPos>=0)
                                          Qry.add("aic_begriff",g.TabBegriffe.getI(iPos,"AIC"));
                                        else if (TabSpalte.getS("format").equals(""))
                                          Qry.add0("aic_begriff",0);
                                        else
                                        {
                                          bOk=false;
                                          iBits|=1;
                                          printError("DefImport: Format " + TabSpalte.getS("format") + " für Abfrage <"+sKennungAbfrage+"> nicht gefunden!");
                                        }
                                        g.TabBegriffe.sAIC="AIC";
                                        //Qry.add("format",TabSpalte.getS("format"));
					Qry.add0("faktor",TabSpalte.getF("faktor"));
					Qry.add0("filter",0);
					Qry.add0("aic_farbe",0);
					if(!TabSpalte.getS("code_kennung1").equals(""))
						Qry.add("aic_code",g.getCodeAic("Auswertformat",TabSpalte.getS("code_kennung1")));
					else
						Qry.add("aic_code","");
					if(!TabSpalte.getS("code_kennung2").equals(""))
						Qry.add("cod_aic_code",g.getCodeAic("Ergebnisart",TabSpalte.getS("code_kennung2")));
					else
						Qry.add("cod_aic_code","");
					if(!TabSpalte.getS("code_kennung3").equals(""))
						Qry.add("cod2_aic_code",g.getCodeAic("Alignment",TabSpalte.getS("code_kennung3")));
					else
						Qry.add("cod2_aic_code","");
                    if(!TabSpalte.getS("code_kennung4").equals(""))
                            Qry.add("cod3_aic_code",g.getCodeAic("Anzeigeart",TabSpalte.getS("code_kennung4")));
                    else
                            Qry.add("cod3_aic_code","");
                    Qry.add("bits2",TabSpalte.getI("bits2"));
                    Qry.add("bits3",TabSpalte.getI("bits3"));
                    Qry.add("bits4",TabSpalte.getI("bits4"));
                    Qry.add("min",TabSpalte.getF("min"));
                    Qry.add("max",TabSpalte.getF("max"));
                    Qry.add("x",TabSpalte.getI("x"));
                    Qry.add("y",TabSpalte.getI("y"));
                    Qry.add("w",TabSpalte.getI("w"));
                    Qry.add("h",TabSpalte.getI("h"));
                    Qry.add("Stil",TabSpalte.getS("Stil"));
                    Qry.add("ToggleSight",TabSpalte.getS("ToggleSight"));
                    Qry.add("Icon",TabSpalte.getS("Icon"));
                    Qry.add("Farbe",TabSpalte.getS("Farbe2"));
                    if (TabSpalte.getS("sub1").equals("") && TabSpalte.getS("sub2").equals("") && TabSpalte.getS("sub3").equals(""))
                    {
                    	Qry.add("beg_aic_begriff","");
                    	Qry.add("Abfrage_begriff","");
                    	Qry.add("Modell_begriff","");
                    }
                    else
                    {
	                    TabBegriff.push();
	                    if(TabSpalte.getS("sub1").equals(""))
	                    	Qry.add("beg_aic_begriff","");
	                    else if(TabBegriff.posInhalt((Object)TabSpalte.getS("sub1G"),TabSpalte.getS("sub1")))
							Qry.add("beg_aic_begriff",TabBegriff.getI("Aic"));
	                    else
						{	
							bOk=false;
							printError("DefImport(Abfrage.Spalten): sub1 "+TabSpalte.getS("sub1G")+"/"+TabSpalte.getS("sub1")+" nicht gefunden");
						}
	                    if(TabSpalte.getS("sub2").equals(""))
	                    	Qry.add("Abfrage_begriff","");
	                    else if(TabBegriff.posInhalt((Object)TabSpalte.getS("sub2G"),TabSpalte.getS("sub2")))
							Qry.add("Abfrage_begriff",TabBegriff.getI("Aic")); 
						else
						{	
							bOk=false;
							printError("DefImport(Abfrage.Spalten): sub2 "+TabSpalte.getS("sub2G")+"/"+TabSpalte.getS("sub2")+" nicht gefunden");
						}
	                    if(TabSpalte.getS("sub3").equals(""))
	                    	Qry.add("Modell_begriff","");
	                    else if(TabBegriff.posInhalt((Object)TabSpalte.getS("sub3G"),TabSpalte.getS("sub3")))
							Qry.add("Modell_begriff",TabBegriff.getI("Aic")); 
						else
						{	
							bOk=false;
							printError("DefImport(Abfrage.Spalten): sub3 "+TabSpalte.getS("sub3G")+"/"+TabSpalte.getS("sub3")+" nicht gefunden");
						}
	                    TabBegriff.pop();
                    }
					int iSub=TabSpalte.getI("subSpalte");
					Qry.add0("spa_aic_spalte",0);
					// if (iSub>0)
					// {
					// 	// int iSp = Qry.getInteger("SELECT AIC_Spalte FROM SPALTE WHERE AIC_Abfrage="+iAIC_Abfrage+" AND nummer="+TabSpalte.getI("nummer"));
					// 	int iPosS=TabSpalte.getPos("aic_Spalte",iSub);
					// 	int iSp=iPosS<0 ? 0:TabSpalte.getI(iPosS,"new_aic_spalte");
					// 	// g.fixtestError("subSpalte "+iSub+"->"+iSp);
					// 	if (iSp>0)
					// 	 	Qry.add("spa_aic_spalte",iSp);
					// 	else
					// 	{
					// 	   bOk=false;
					// 	   printError("DefImport(Abfrage.Spalten): SubSpalte "+iSub+" nicht gefunden bei Abfrage "+iAIC_Abfrage);
					// 	}
					// }
					if(!TabSpalte.getS("stammtyp").equals(""))
					{
						int iAIC_Stamm = Qry.getInteger("select aic_stamm from stammview s join stammtyp stt on s.aic_stammtyp=stt.aic_stammtyp where stt.kennung='"+TabSpalte.getS("stammtyp")+"' and s.kennung='"+TabSpalte.getS("stamm")+"'");
						if(iAIC_Stamm>0)
							Qry.add("aic_stamm",iAIC_Stamm);
						else
						{
							bOk=false;
							String s = "DefImport(Abfrage.Spalten): Abfrage <"+sKennungAbfrage+"> Stamm <"+TabSpalte.getS("stamm")+","+TabSpalte.getS("stammtyp")+"> konnte nicht gefunden werden!!!";
							printError(s);
                            iBits|=1;
						}
					}
					else
						Qry.add("aic_stamm","");
                                        if(!TabSpalte.getS("stt2").equals(""))
                                              {
                                                      int iAIC_Stamm = Qry.getInteger("select aic_stamm from stammview s join stammtyp stt on s.aic_stammtyp=stt.aic_stammtyp where stt.kennung='"+TabSpalte.getS("stt2")+"' and s.kennung='"+TabSpalte.getS("stamm2")+"'");
                                                      if(iAIC_Stamm>0)
                                                              Qry.add("sta_aic_stamm",iAIC_Stamm);
                                                      else
                                                      {
                                                              bOk=false;
                                                              String s = "DefImport(Abfrage.Spalten): Abfrage <"+sKennungAbfrage+"> Stamm2 <"+TabSpalte.getS("stamm2")+","+TabSpalte.getS("stt2")+"> konnte nicht gefunden werden!!!";
                                                              printError(s);
                                                              iBits|=1;
                                                              //g.exec("update begriff b join abfrage a on a.aic_begriff=b.aic_begriff set importzeit=null where aic_abfrage="+iAIC_Abfrage);
                                                      }
                                              }
                                              else
                                                      Qry.add("sta_aic_stamm","");

                                        if(!TabSpalte.getS("Hierarchie").equals(""))
                                        {
                                                int iAIC_Stammtyp = Qry.getInteger("select aic_stammtyp from stammtyp where kennung='"+TabSpalte.getS("Hierarchie")+"'");
                                                if(iAIC_Stammtyp>0)
                                                        Qry.add("aic_stammtyp",iAIC_Stammtyp);
                                                else
                                                {
                                                        bOk=false;
                                                        String s = "DefImport(Abfrage.Spalten): Abfrage <"+sKennungAbfrage+"> Stamm <"+TabSpalte.getS("Hierarchie")+"> konnte nicht gefunden werden!!!";
                                                        printError(s);
                                                        iBits|=1;
                                                        //g.exec("update begriff b join abfrage a on a.aic_begriff=b.aic_begriff set importzeit=null where aic_abfrage="+iAIC_Abfrage);
                                                }
                                        }
                                        else
                                                Qry.add("aic_stammtyp","");

					if(iAIC_Spalte==0)
						iAIC_Spalte = Qry.insert("Spalte",true);
					else
					{
						Qry.update("Spalte",iAIC_Spalte);
                        VecWeg.removeElement(new Integer(iAIC_Spalte));
						TabSpalte.setInhalt("Vorhanden",Boolean.TRUE);
					}
					if (iSub>0)
					{
						if (TabSubSp==null)
								TabSubSp=new Tabellenspeicher(g,new String[]{"Spalte","Sub"});
						TabSubSp.newLine();
						TabSubSp.setInhalt("Spalte",iAIC_Spalte);
						TabSubSp.setInhalt("Sub", iSub);
					}

					TabSpalte.setInhalt("new_aic_spalte",iAIC_Spalte);
					TabSpalte.setInhalt("edit",Boolean.TRUE);
					if (VecNicht != null && VecNicht.contains(new Integer(iAIC_Spalte)))
					{
						TabSpalte.setInhalt("del", Boolean.TRUE);
						//bOk=false;
					}
				}
				if (TabSubSp!=null)
				{
					for(TabSubSp.moveFirst();!TabSubSp.out();TabSubSp.moveNext())
					{
						int iSub=TabSubSp.getI("Sub");
						int iPosS=TabSpalte.getPos("aic_Spalte",iSub);
						int iSp=iPosS<0 ? 0:TabSpalte.getI(iPosS,"new_aic_spalte");
						// g.fixtestError("subSpalte "+iSub+"->"+iSp);
						if (iSp>0)
						{
						 	//Qry.add("spa_aic_spalte",iSp);
							g.fixtestError("Subspalte "+iSp+" bei Spalte "+TabSubSp.getI("Spalte"));
							g.exec("update Spalte set spa_aic_spalte="+iSp+" where aic_spalte="+TabSubSp.getI("Spalte"));
						}
						else
						{
							bOk=false;
						   	printError("DefImport(Abfrage.Spalten): SubSpalte "+iSub+" nicht gefunden bei Abfrage "+sKennungAbfrage);
						}
					}
				}
                                //TabSpalte.showGrid("TabSpalte");

				if(bOk)
                                {
                                  Qry.add("ImportZeit", TabBegriff.getTimestamp("edit"));
                                  Qry.add0("aic_logging",0);
                                }
				else
                                {
                                  Qry.add("ImportZeit", "");
                                  if (TabInfo != null) TabInfo.setInhalt("Fehler",Static.JaNein(true));
                                }
				Qry.update("Begriff",TabBegriff.getI("AIC"));
			}
			else
			{
				for(TabSpalte.posInhalt("abfrage",sKennungAbfrage);!TabSpalte.eof() && TabSpalte.getS("abfrage").equals(sKennungAbfrage);TabSpalte.moveNext())
				{
					int iAIC_Spalte = Qry.getInteger("SELECT AIC_Spalte FROM SPALTE WHERE AIC_Abfrage="+iAIC_Abfrage+" AND nummer="+TabSpalte.getI("nummer"));
					TabSpalte.setInhalt("new_aic_spalte",iAIC_Spalte);
				}
				Qry.clear();
			}
			}
		}

		//g.progInfo("*FixEigenschaft");
		if(!bShow && TabFix_Spalte!=null)
		{
                  //TabFix_Spalte.showGrid("TabFix_Spalte");
			for(TabFix_Spalte.moveFirst();!TabFix_Spalte.eof();TabFix_Spalte.moveNext())
			{
				if(TabSpalte.posInhalt("aic_spalte",TabFix_Spalte.getI("aic_spalte")))
				{
					if(TabSpalte.getB("edit"))
					{
                                          int iAIC_Spalte = TabSpalte.getI("new_aic_spalte");
                                          if(TabSpalte.getB("del"))
                                          {
                                            g.exec("delete from FixEigenschaft where aic_spalte=" + iAIC_Spalte);
                                            g.exec("delete from Spalte_Zuordnung where aic_spalte=" + iAIC_Spalte);
                                            g.exec("delete from Spalte_Berechnung where aic_spalte=" + iAIC_Spalte);
                                            TabSpalte.setInhalt("del",Boolean.FALSE);
                                            //g.testInfo("Lösche Spalte "+iAIC_Spalte);
                                          }
						int iAIC_Bew = TabFix_Spalte.getS("bewegungstyp").equals("")?0:SQL.getInteger(g,"SELECT AIC_Bewegungstyp FROM Bewegungstyp WHERE Kennung='"+TabFix_Spalte.getS("bewegungstyp")+"'");
						int iAIC_Eig = g.TabEigenschaften.getAic(TabFix_Spalte.getS("eigenschaft").toUpperCase());

						if(iAIC_Spalte>0)
						{
							Qry.add("aic_spalte",iAIC_Spalte);
							Qry.add("richtung",TabFix_Spalte.getB("richtung"));
							if(iAIC_Bew>0)
								Qry.add("aic_bewegungstyp",iAIC_Bew);

							Qry.add("aic_eigenschaft",iAIC_Eig);
							Qry.insert("FixEigenschaft",false);
						}
						else
							printError("DefImport: Fehler bei FixEigenschaft-Spaltenteil ("+TabSpalte.getS("Abfrage")+"."+TabSpalte.getS("Kennung")+":"+TabFix_Spalte.getS("Eigenschaft")+"/"+TabFix_Spalte.getI("aic_spalte")+")");
					}
				}
				else
					printError("DefImport: FixEigenschaft-Spalte nicht gefunden ("+TabFix_Spalte.getS("Eigenschaft")+"/"+TabFix_Spalte.getI("aic_spalte")+")");
			}
		}

		//g.progInfo("*FixBedingung");
		if(!bShow && TabFix_Bedingung!=null)
		{
                  //TabFix_Bedingung.showGrid("TabFix_Bedingung");
			for(TabFix_Bedingung.moveFirst();!TabFix_Bedingung.eof();TabFix_Bedingung.moveNext())
			{
				if(TabBedingung.posInhalt("aic_bedingung",TabFix_Bedingung.getI("aic_bedingung")))
				{
					if(TabBedingung.getB("edit"))
					{
						int iAIC_Bed = TabBedingung.getI("new_aic_bedingung");
						//int iAIC_Bew = SQL.getInteger(g,"SELECT AIC_Bewegungstyp FROM Bewegungstyp WHERE Kennung='"+TabFix_Bedingung.getS("bewegungstyp")+"'");
						int iAIC_Eig = g.TabEigenschaften.getAic(TabFix_Bedingung.getS("eigenschaft").toUpperCase());
						if(iAIC_Bed>0)
							Qry.add("aic_bedingung",iAIC_Bed);
						Qry.add("richtung",TabFix_Bedingung.getB("richtung"));
						//if(iAIC_Bew>0)
						//	Qry.add("aic_bewegungstyp",iAIC_Bew);

						Qry.add("aic_eigenschaft",iAIC_Eig);
						Qry.insert("FixEigenschaft",false);
					}
				}
				else
					printError("DefImport: FixEigenschaft-Bedingung nicht gefunden ("+TabFix_Bedingung.getS("Eigenschaft")+"/"+TabFix_Bedingung.getI("aic_bedingung")+")");
			}
		}

		//g.progInfo("*Spalte_Zuordnung");
		if(!bShow && TabSpalte_Zuordnung!=null)
		{
                  int iSpalteLast=0;
                  //boolean bImport=true;
                  for(TabSpalte_Zuordnung.moveFirst();!TabSpalte_Zuordnung.eof();TabSpalte_Zuordnung.moveNext())
                  {
				if(TabSpalte.posInhalt("aic_spalte",TabSpalte_Zuordnung.getI("aic_spalte"))&&TabSpalte.getB("edit"))
				{
                                  int iAIC_Spalte = TabSpalte.getI("new_aic_spalte");
                                  if (iAIC_Spalte != iSpalteLast)
                                  {
                                    //int iAnz= SQL.getInteger(g, "select count(*) from spalte_Zuordnung where aic_spalte=" + iAIC_Spalte);
                                    //bImport = iAnz==0 || SQL.getInteger(g, "select count(*) from spalte_Zuordnung where aic_stamm is null and aic_spalte=" + iAIC_Spalte)>0;
                                    //if (bImport && iAnz>0)
                                      g.exec("delete from spalte_Zuordnung where aic_spalte=" + iAIC_Spalte);
                                    iSpalteLast=iAIC_Spalte;
                                  }
                                  //if (bImport)
                                  {
					int iAIC_Stamm = TabSpalte_Zuordnung.getS("stamm").equals("") ? -1:
                                            SQL.getInteger(g,"select aic_stamm from stammview2 s join stammtyp stt on s.aic_stammtyp=stt.aic_stammtyp where s.kennung='"+TabSpalte_Zuordnung.getS("stamm")+"' and stt.kennung='"+TabSpalte_Zuordnung.getS("stammtyp")+"'");

                                        if(iAIC_Spalte<=0)
                                          printError("DefImport: Fehler bei Spalten_Zuordnung");
                                        else
                                        {
                                          if(iAIC_Stamm==0)
                                          {
                                               g.testInfo("DefImport.Spalte_Zuordnung: Stammsatz <"+TabSpalte_Zuordnung.getS("stamm")+"> bei Stammtyp <"+TabSpalte_Zuordnung.getS("stammtyp")+"> nicht gefunden!");
                                               iBits|=4;
                                          }
                                          else if (iAIC_Stamm>0)
                                            Qry.add("aic_stamm",iAIC_Stamm);
                                          Qry.add("aic_spalte",iAIC_Spalte);
                                          Qry.add("reihenfolge",TabSpalte_Zuordnung.getI("reihenfolge"));
                                          Qry.add("titel",TabSpalte_Zuordnung.getS("titel"));
                                          Qry.add("aic_stammtyp",SQL.getInteger(g,"select aic_stammtyp from stammtyp where kennung='"+TabSpalte_Zuordnung.getS("stammtyp")+"'"));
                                          if (!TabSpalte_Zuordnung.getS("eigenschaft").equals(""))
                                            Qry.add("aic_eigenschaft",SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='"+TabSpalte_Zuordnung.getS("eigenschaft")+"'"));
                                          Qry.insert("Spalte_Zuordnung",false);
                                        }
                                  }
				}
                  }
		}

		//g.progInfo("*Spalte_Berechnung");
		if(!bShow && TabSpalte_Berechnung!=null)
		{
			for(TabSpalte_Berechnung.moveFirst();!TabSpalte_Berechnung.eof();TabSpalte_Berechnung.moveNext())
			{
				if(TabSpalte.posInhalt("aic_spalte",TabSpalte_Berechnung.getI("aic_spalte"))&&TabSpalte.getB("edit"))
				{
					int iAIC_Spalte = TabSpalte.getI("new_aic_spalte");
					int iAIC_Code = SQL.getInteger(g,"select aic_code from code join begriffgruppe bg on code.aic_begriffgruppe=bg.aic_begriffgruppe where code.kennung='"+TabSpalte_Berechnung.getS("kennung")+"' and bg.kennung='Operation'");
					if(iAIC_Code==0)
						printError("DefImport.Spalte_Berechnung: Code <"+TabSpalte_Berechnung.getS("kennung")+"> bei Begriffgruppe <Operation> nicht gefunden!");
					else if(iAIC_Spalte>0)
					{
						Qry.add("aic_spalte",iAIC_Spalte);
						Qry.add("pos",TabSpalte_Berechnung.getI("pos"));
						Qry.add("aic_code",iAIC_Code);
						Qry.add0("spa_aic_spalte",TabSpalte.posInhalt("aic_spalte",TabSpalte_Berechnung.getI("spa_aic_spalte")) ? TabSpalte.getI("new_aic_spalte"):0);
						Qry.add("wert",TabSpalte_Berechnung.getF("wert"));
						Qry.add("Eingabe",TabSpalte_Berechnung.getS("Eingabe"));
						Qry.insert("Spalte_Berechnung",false);
					}
					else
						printError("DefImport: Fehler bei Spalte_Berechnung");
				}
			}
		}
                //if (bShow)
                //  TabSpalte.showGrid("TabSpalte");
	}
	if (TabSubAbfragen != null)
	{
//		g.fixtestError("VecAbf="+VecAbf);
		g.exec("delete from begriff_zuordnung where"+g.in("aic_begriff", VecAbf)+" and Art is not null");	
		for(TabSubAbfragen.moveFirst();!TabSubAbfragen.out();TabSubAbfragen.moveNext())
		{
			int iBeg=g.getBegriffAicS("Abfrage", TabSubAbfragen.getS("Abfrage"));
			String sArt=TabSubAbfragen.getS("Art");
			boolean bForm=sArt.equals("DetailForm");
			int iBeg2=g.getBegriffAicS(bForm ? "Frame":"Abfrage", TabSubAbfragen.getS("SubAbfrage"));
			int iArt=g.getBegriffAicS("Zuordnungsart",sArt);
			if (iArt==0)
				iArt=g.getCodeAic("Zuordnungsart",TabSubAbfragen.getS("Art"));
//			g.exec("delete from begriff_zuordnung where aic_begriff="+iBeg+" and Art="+iArt);		
			if (iBeg==0 || iBeg2==0)
				printError((bForm ? "SubFormular ":"SubAbfrage ")+TabSubAbfragen.getS("SubAbfrage")+" für "+TabSubAbfragen.getS("Abfrage")+" nicht gefunden");
			else if (iArt==0)
				printError("Zuordnungsart "+TabSubAbfragen.getS("Art")+" nicht gefunden");
			else
			{
				g.exec("delete from begriff_zuordnung where aic_begriff="+iBeg+" and beg_aic_begriff="+iBeg2);
				g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff,Art) values ("+iBeg+","+iBeg2+","+iArt+")");
			}
		}
	}
        if (!bShow && TabHauptschirm != null)
        {
          Tabellenspeicher TabHS=new Tabellenspeicher(g,"select AIC_Hauptschirm,kennung,selbst from hauptschirm where kennung is not null",true);
          int iBG_HS=g.TabBegriffgruppen.getAic("Hauptschirm"); 	  
          for(TabHauptschirm.moveFirst();!TabHauptschirm.eof();TabHauptschirm.moveNext())
          {
        	  String sKennung=TabHauptschirm.getS("Kennung");
              boolean bUpdate=TabHS.posInhalt("Kennung",sKennung);
              String sDefBez=TabHauptschirm.getS("DefBez");
              int iB = 0;
              if (!Static.Leer(sDefBez))
              {
            	iB= bUpdate ? TabHS.getI("selbst"):0;
            	String sProg2=TabHauptschirm.getS("Prog");
            	int iProg=Static.Leer(sProg2) ? 0:g.getCodeAic("Programm",sProg2);
	          	if (iB==0)
	          	{
	          	  Qry.add("defBezeichnung",sDefBez);
	          	  Qry.add("Kennung",sKennung);
//	          	  Qry.add("aic_logging",g.getLog());
	          	  Qry.add("aic_begriffgruppe",iBG_HS);
	          	  Qry.add0("prog", iProg);
	          	  Qry.add("web",true);
	          	  iB = Qry.insert("begriff",true);
	          	}
	          	else
	          	{
	          		Qry.add("defBezeichnung",sDefBez);
//	            	Qry.add("Kennung",sKenn);
	            	Qry.add0("aic_logging",0);
	            	Qry.add0("prog", iProg);
	          		Qry.update("begriff",iB);
	          	}
              }
                  Qry.add("aic_begriff",g.getBegriffAicS("Applikation",TabHauptschirm.getS("Applikation")));
                  if (!TabHauptschirm.getS("abfrage").equals(""))
                    Qry.add("aic_abfrage",SQL.getInteger(g,"select aic_abfrage from abfrage join begriff on abfrage.aic_begriff=begriff.aic_begriff where kennung='"+TabHauptschirm.getS("abfrage")+"'"));
                  
//                  if (!bUpdate)
                	  Qry.add("bits",TabHauptschirm.getI("bits"));
                  if (TabHauptschirm.getS("Stt").equals("-"))
                    Qry.add0("AIC_STAMMTYP",0);
                  else
                    Qry.add("AIC_STAMMTYP",SQL.getInteger(g,"select aic_stammtyp from stammtyp where kennung='"+TabHauptschirm.getS("Stt")+"'"));
                  Qry.add0("selbst", iB);
                  int iAic=0;
                  if (bUpdate)
                  {
                    iAic=TabHS.getI("AIC_Hauptschirm");
                    Qry.add0("aic_logging",0);
                    Qry.update("HAUPTSCHIRM", iAic);
                  }
                  else
                  {
                    Qry.add("Kennung",sKennung);
                    iAic=Qry.insert("HAUPTSCHIRM",true);
                    TabHauptschirm.setInhalt("AIC",iAic);
                  }
                  JCOutliner OutHS = TabHauptschirme.posInhalt("Name",sKennung)?(JCOutliner)TabHauptschirme.getInhalt("Outliner"):null;
                  if(OutHS!=null)
                  {
                    if (bUpdate)
                      Qry.exec("DELETE FROM Ansicht WHERE AIC_Hauptschirm="+iAic);
                    Save_Rekursion(g,(JCOutlinerFolderNode)OutHS.getRootNode(),0,iAic,false);
                  }

          }
        }
	g.printExec("*Abfragen Ende");
	g.clockInfo("Abfragen",lClock);
	g.printExec("*Filter");
        if (bShow)
        {
          if (Gau != null)
            Gau.setText("Fertig",8);
          g.rollback();
          g.unConnect();
          return false;
        }
	if (TabSpalte != null)
		for(TabSpalte.moveFirst();!TabSpalte.eof();TabSpalte.moveNext())
		{
			if(!TabSpalte.getS("filter").equals(""))
			{
				Qry.add("filter",SQL.getInteger(g,"select aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff where kennung='"+TabSpalte.getS("filter")+"'"));
				Qry.update("Spalte",TabSpalte.getI("new_aic_spalte"));
			}
                        if(!TabSpalte.getS("farbe").equals(""))
                        {
                                Qry.add0("aic_farbe",SQL.getInteger(g,"select aic_farbe from farbe where kennung='"+TabSpalte.getS("farbe")+"'"));
                                Qry.update("Spalte",TabSpalte.getI("new_aic_spalte"));
                        }
		}

  if (TabEigenschaft != null)
	for(TabEigenschaft.moveFirst();!TabEigenschaft.eof();TabEigenschaft.moveNext())
	{
		if(!TabEigenschaft.getS("Abfrage").equals(""))
		{
			Qry.add("aic_abfrage",SQL.getInteger(g,"select aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff where kennung='"+TabEigenschaft.getS("Abfrage")+"'"));
			Qry.update("Eigenschaft",TabEigenschaft.getI("AIC"));
		}
	}
	g.clockInfo("Filter",lClock);

	g.printExec("*Formulare");
        if (Gau != null)
          Gau.setText("Formulare",3);
	//if(g.Prog())
	//	TabFormulare.showGrid("TabFormulare");
    int iCDF=g.getCodeAic("Zuordnungsart", "DetailForm");
    Vector<Integer> VecBegZ=new Vector<Integer>();
    if (TabBegriff != null)
    {
        int iFom=0;
        Vector<JCOutlinerNode> VecFom=new Vector<JCOutlinerNode>();
        Static.getAll(OutFormular.getRootNode(),VecFom);
        //for(JCOutlinerNode Node=OutFormular.getNextNode(OutFormular.getRootNode());Node!=null;Node=OutFormular.getNextNode(Node))
        for (int i=0;i<VecFom.size();i++)
		{
	          Vector VecVisible = (Vector)VecFom.elementAt(i).getLabel();
			String sKennung = (String)VecVisible.elementAt(0);
	                iFom++;
	                //boolean bZwingend = true;
			if(TabBegriff.posInhalt((Object)"Frame",sKennung))
			{
				boolean bVorhanden = TabBegriff.getB("Vorhanden");
				//if(TabBegriff.getInhalt("edit")==null)
					//Static.printError("Formular "+sKennung+" enthält keinen Timestamp!");
	                        if(bFormulare || !bVorhanden || (TabBegriff.getInhalt("editold")==null || TabBegriff.getDate("edit").getTime()>TabBegriff.getDate("editold").getTime()))
				{
	                          g.fixInfo("Fom"+iFom+":"+sKennung);
	                          addInfo(TabInfo,bVorhanden,"Formular",TabBegriff.getS("DefBezeichnung"),sKennung);
					bOk=true;
					TabBegriff.push();
					SaveFormular(g,TabFormulare,sKennung,TabBegriff.getI("AIC"),TabForm2FXML,bVorhanden);
					TabBegriff.pop();
					if(bOk)
	                                {
	                                  Qry.add("ImportZeit", TabBegriff.getTimestamp("edit"));
	                                  Qry.add0("aic_logging",0);
	                                }
					else
						Qry.add("ImportZeit","");
					Qry.update("Begriff",TabBegriff.getI("AIC"));
					if (TabBegriff.getB("Web"))
						g.exec("delete from Beg2_Z where AIC_Begriff="+TabBegriff.getI("AIC")+" and Art="+iCDF);
				}
			}
			else
				printError("Formular "+sKennung+" in TabBegriff nicht gefunden!");
		}
	        if(TabForm2Eig!=null)
		for(TabForm2Eig.moveFirst();!TabForm2Eig.eof();TabForm2Eig.moveNext())
		{
	          int iPosE=g.TabEigenschaften.getPos("Kennung",TabForm2Eig.getS("Kennung1").toUpperCase());
			if(iPosE>=0 && TabBegriff.posInhalt((Object)"Frame",TabForm2Eig.getInhalt("Kennung2")))
			{
	                  //g.testInfo("TabForm2Eig:"+TabForm2Eig.getInhalt("Kennung2")+"/"+TabForm2Eig.getInhalt("Kennung1"));
					Qry.addWhere("AIC_Begriff",TabBegriff.getI("AIC"));
					Qry.add("AIC_Eigenschaft",g.TabEigenschaften.getI(iPosE,"AIC"));
					Qry.update("Formular");
	                                //g.testInfo("TabForm2Eig: update "+TabBegriff.getI("AIC")+"/"+TabEigenschaft.getI("AIC"));
			}
			else
				printError("DefImport(Form2Eig): Zuordnung zwischen <"+TabForm2Eig.getInhalt("Kennung2")+"-"+TabForm2Eig.getInhalt("Kennung1")+"> konnte nicht hergestellt werden");
		}
		g.clockInfo("Formulare",lClock);
    
	g.printExec("*Modelle");
        if (Gau != null)
          Gau.setText("Modelle",4);
	//if(g.Prog())
	//	TabModelle.showGrid("TabModelle");
        //if (g.TestPC())
        //  TabBegriff.showGrid("TabBegriff");
	Tabellenspeicher Tab = new Tabellenspeicher(g,new String[]{"AIC_Begriff","AIC_Modell","Node"});
        Vector<JCOutlinerNode> VecModell=new Vector<JCOutlinerNode>();
        Static.getAll(OutModell.getRootNode(),VecModell);
	//for(JCOutlinerNode Node=OutModell.getNextNode(OutModell.getRootNode());Node!=null;Node=OutModell.getNextNode(Node))
        for (int i=0;i<VecModell.size();i++)
	{
		Vector VecVisible = (Vector)VecModell.elementAt(i).getLabel();
		String sKennung = (String)VecVisible.elementAt(0);
		if (sKennung==null)
			printError("Kennung eines Modells ist leer");
		boolean bZwingend = sKennung!=null || ((String)VecVisible.elementAt(1)).equals("true");
		boolean bVorhanden = sKennung==null || TabBegriff.posInhalt((Object)"Modell",sKennung) && TabBegriff.getB("Vorhanden");
                //g.testInfo("Modell "+sKennung+":"+bZwingend+":"+TabBegriff.getDate("editold")+"->"+TabBegriff.getDate("edit"));
		if(TabBegriff.getInhalt("edit")==null)
			Static.printError("Modell "+sKennung+" enthält keinen Timestamp!");
		else if(bModelle || ((bZwingend || !bVorhanden)&&(TabBegriff.getInhalt("editold")==null || TabBegriff.getDate("edit").getTime()>TabBegriff.getDate("editold").getTime())))
		{
                  //g.testInfo("update Modell "+sKennung);

			int iAIC_Begriff = TabBegriff.getI("AIC");
			int iAIC_Modell=bVorhanden?Qry.getInteger("SELECT AIC_Modell FROM Modell WHERE AIC_Begriff="+iAIC_Begriff):0;
                        addInfo(TabInfo,iAIC_Modell>0,"Modell",TabBegriff.getS("DefBezeichnung"),sKennung);
			JCOutliner OutMod = TabModelle.posInhalt("Name",sKennung)?(JCOutliner)TabModelle.getInhalt("Outliner"):null;
			if(OutMod!=null)
			{
				String sPeriode = OutMod.getRootNode().getLabel()!=null && ((Vector)OutMod.getRootNode().getLabel()).size()>1?(String)((Vector)OutMod.getRootNode().getLabel()).elementAt(1):"";
				String sMax_B = OutMod.getRootNode().getLabel()!=null && ((Vector)OutMod.getRootNode().getLabel()).size()>2?(String)((Vector)OutMod.getRootNode().getLabel()).elementAt(2):"0";
				boolean bP0=Static.Leer(sPeriode);
				if(iAIC_Modell==0)
				{
					Qry.add("AIC_Begriff",iAIC_Begriff);
					if(!bP0)
					{
						int iPeriode = Qry.getInteger("select aic_stamm from stammview where aic_stammtyp="+g.iSttPeriode+" and kennung='"+sPeriode+"'");
						if(iPeriode>0)
							Qry.add("AIC_Stamm",iPeriode);
					}
					Qry.add0("Max_B", Sort.geti(sMax_B));
					//Qry.add("AIC_Mandant",1); //Darf nicht so sein!!!
					iAIC_Modell=Qry.insert("Modell",true);
				}
				else
				{
					Qry.exec("DELETE FROM Befehl WHERE AIC_Modell="+iAIC_Modell);

					Qry.add0("AIC_Stamm",bP0 ? 0:Qry.getInteger("select aic_stamm from stammview where aic_stammtyp="+g.iSttPeriode+" and kennung='"+sPeriode+"'"));
					Qry.add0("Max_B", Sort.geti(sMax_B));
					Qry.add0("AIC_Abfrage",0);
					Qry.addWhere("AIC_Begriff",iAIC_Begriff);
					Qry.update("Modell");
				}

				Tab.addInhalt("AIC_Begriff",new Integer(iAIC_Begriff));
				Tab.addInhalt("AIC_Modell",new Integer(iAIC_Modell));
				Tab.addInhalt("Node",OutMod.getRootNode());
			}
			else
			{
				Qry.add("ImportZeit","");
				Qry.update("Begriff",iAIC_Begriff);
                                if (TabInfo != null) TabInfo.setInhalt("Fehler",Static.JaNein(true));
				printError("DefImport.SaveModell(): Modell<"+sKennung+"> in SaveModell() nicht gefunden!!!");
			}
		}
	}
	for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
	{
		boolean bOk=Modell_Rekursion(g,TabSpalte,TabBefehle,(JCOutlinerFolderNode)Tab.getInhalt("Node"),0,Tab.getI("AIC_Modell"),' ');
		if(TabBegriff.posInhalt("AIC",Tab.getI("AIC_Begriff")))
		{
			Qry.add("ImportZeit",bOk ? TabBegriff.getTimestamp("edit"):null);
                        if (bOk)
                          Qry.add0("aic_logging",0);
			Qry.update("Begriff",TabBegriff.getI("AIC"));
                        if (!bOk)
                          printError("Modell "+TabBegriff.getS("DefBezeichnung")+" fehlerhaft!");
		}
	}
        if (VecWeg.size()>0)
        {
          g.testInfo("VecWeg="+VecWeg);
          for (int i=0;i<VecWeg.size();i++)
          {
            int iSpalte=Sort.geti(VecWeg,i);
            Vector VecMod2 = SQL.getVector("select distinct aic_modell from befehl where aic_spalte="+iSpalte, g);
            if (VecMod2.size()==0)
            {
              //g.testInfo("lösche Spalte "+iSpalte);
              g.exec("delete from fixeigenschaft where aic_spalte=" + iSpalte);
              g.exec("delete from SPALTE_BERECHNUNG where aic_spalte="+iSpalte);
              g.exec("delete from SPALTE_BERECHNUNG where spa_aic_spalte="+iSpalte);
              g.exec("delete from spalte_zuordnung where aic_spalte="+iSpalte);
              g.exec("delete from spalten2 where aic_spalte="+iSpalte);
              g.exec("delete from spalte_z2 where aic_spalte=" + iSpalte);
              g.exec("delete from spalte where aic_spalte="+iSpalte);
            }
            else
            	g.fixInfo("Spalte "+iSpalte+" wird weiter in folgenden Modell(en) verwendet:\n"+
                  SQL.getSVector("select distinct defbezeichnung from begriff join modell on begriff.aic_begriff=modell.aic_begriff where aic_modell"+Static.SQL_in(VecMod2),g));
          }
        }
    }
	g.printExec("*TabAbf2Mod");
	if(TabAbf2Mod!=null)
	for(TabAbf2Mod.moveFirst();!TabAbf2Mod.eof();TabAbf2Mod.moveNext())
	{
		int iAIC_Modell = Qry.getInteger("select m.aic_modell from modell m join begriff b on m.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Modell' and b.kennung='"+TabAbf2Mod.getS("Kennung1")+"'");
		if(iAIC_Modell>0)
		{
			Qry.addWhere("AIC_Abfrage",Qry.getInteger("select a.aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Abfrage' and b.kennung='"+TabAbf2Mod.getS("Kennung2")+"'"));
			Qry.add("AIC_Modell",iAIC_Modell);
			Qry.update("Abfrage");
		}
	}
    g.printExec("*TabAbf2Mod2");
    if(TabAbf2Mod2!=null)
    for(TabAbf2Mod2.moveFirst();!TabAbf2Mod2.eof();TabAbf2Mod2.moveNext())
    {
            int iAIC_Modell = Qry.getInteger("select m.aic_modell from modell m join begriff b on m.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Modell' and b.kennung='"+TabAbf2Mod2.getS("Kennung1")+"'");
            if(iAIC_Modell>0)
            {
                    Qry.addWhere("AIC_Abfrage",Qry.getInteger("select a.aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Abfrage' and b.kennung='"+TabAbf2Mod2.getS("Kennung2")+"'"));
                    Qry.add("MOD_AIC_Modell",iAIC_Modell);
                    Qry.update("Abfrage");
            }
    }
    g.printExec("*TabMod2Abf");
	if(TabMod2Abf!=null)
	{
	  for(TabMod2Abf.moveFirst();!TabMod2Abf.eof();TabMod2Abf.moveNext())
 	  {
		int iAIC_Modell = Qry.getInteger("select m.aic_modell from modell m join begriff b on m.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Modell' and b.kennung='"+TabMod2Abf.getS("Kennung1")+"'");
		int iAIC_Abfrage= Qry.getInteger("select a.aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Abfrage' and b.kennung='"+TabMod2Abf.getS("Kennung2")+"'");
		//g.fixtestError("TabMod2Abf: Modell="+TabMod2Abf.getS("Kennung1")+"->"+iAIC_Modell+", Abfrage="+TabMod2Abf.getS("Kennung2")+"->"+iAIC_Abfrage);
		if(iAIC_Modell>0 && iAIC_Abfrage>0)
		{
			Qry.addWhere("AIC_Modell",iAIC_Modell);
			Qry.add("AIC_Abfrage",iAIC_Abfrage);
			Qry.update("Modell");
		}
	  }
	  //TabMod2Abf.showGrid("TabMod2Abf");
	}
	g.printExec("*TabMod2Abf2");
	if(TabMod2Abf2!=null)
	{
	  for(TabMod2Abf2.moveFirst();!TabMod2Abf2.eof();TabMod2Abf2.moveNext())
 	  {
		int iAIC_Modell = Qry.getInteger("select m.aic_modell from modell m join begriff b on m.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Modell' and b.kennung='"+TabMod2Abf2.getS("Kennung1")+"'");
		int iAIC_Abfrage= Qry.getInteger("select a.aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Abfrage' and b.kennung='"+TabMod2Abf2.getS("Kennung2")+"'");
		//g.fixtestError("TabMod2Abf: Modell="+TabMod2Abf.getS("Kennung1")+"->"+iAIC_Modell+", Abfrage="+TabMod2Abf.getS("Kennung2")+"->"+iAIC_Abfrage);
		if(iAIC_Modell>0 && iAIC_Abfrage>0)
		{
			Qry.addWhere("AIC_Modell",iAIC_Modell);
			Qry.add("Farbe",iAIC_Abfrage);
			Qry.update("Modell");
		}
	  }
	  //TabMod2Abf2.showGrid("TabMod2Abf");
	}
	g.clockInfo("Modelle",lClock);

	g.printExec("*TabPlanung");
	if(TabPlanung!=null)
	for(TabPlanung.moveFirst();!TabPlanung.eof();TabPlanung.moveNext())
	{
		int iAIC_AbfDaten=Qry.getInteger("select aic_abfrage from begriff b join abfrage a on b.aic_begriff=a.aic_begriff where kennung='"+TabPlanung.getS("AbfDaten")+"'");
		int iAIC_AbfFilter=!TabPlanung.getS("AbfFilter").equals("")?Qry.getInteger("select aic_abfrage from begriff b join abfrage a on b.aic_begriff=a.aic_begriff where kennung='"+TabPlanung.getS("AbfFilter")+"'"):0;
                int iAIC_AbfSonst=!TabPlanung.getS("AbfSonst").equals("")?Qry.getInteger("select aic_abfrage from begriff b join abfrage a on b.aic_begriff=a.aic_begriff where kennung='"+TabPlanung.getS("AbfSonst")+"'"):0;

		Qry.add("AIC_Abfrage",iAIC_AbfDaten);
		Qry.add0("Abf_AIC_Abfrage",iAIC_AbfFilter);
                Qry.add0("Abf2_AIC_Abfrage",iAIC_AbfSonst);
		Qry.add("Spaltenbreite",TabPlanung.getI("Spaltenbreite"));
		if(TabPlanung.getB("Vorhanden"))
		{
			Qry.addWhere("AIC_Begriff",TabPlanung.getI("AIC"));
			Qry.update("Planung");
		}
		else
		{
			Qry.add("AIC_Begriff",Qry.getInteger("select b.aic_begriff from begriff b join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Planung' and b.kennung='"+TabPlanung.getS("Kennung")+"'"));
			Qry.insert("Planung",false);
		}
	}
	g.clockInfo("Planung",lClock);

        g.printExec("*TabFormAbf");
        if (TabFormAbf != null)
        for(TabFormAbf.moveFirst();!TabFormAbf.eof();TabFormAbf.moveNext())
        {
          int iAIC_Abf = Qry.getInteger("select aic_abfrage from begriff b join abfrage a on b.aic_begriff=a.aic_begriff where kennung='" + TabFormAbf.getS("Abfrage") + "'");
          int iAIC_Form = Qry.getInteger("select aic_formular from begriff b join formular f on b.aic_begriff=f.aic_begriff where kennung='" + TabFormAbf.getS("Formular") + "'");
          if (iAIC_Abf>0 && iAIC_Form>0)
          {
            Qry.add("AIC_Abfrage",iAIC_Abf);
            Qry.addWhere("AIC_Formular",iAIC_Form);
            Qry.update("Formular");
          }
          else
            Static.printError((iAIC_Abf==0 ? "DefImport.FormAbf: Abfrage "+TabFormAbf.getS("Abfrage")+" nicht gefunden! ":"")+
                              (iAIC_Form==0 ? "DefImport.FormAbf: Formular "+TabFormAbf.getS("Formular")+" nicht gefunden!":""));
        }
      g.printExec("*TabFormModell");
      if (TabFormModell != null)
        for(TabFormModell.moveFirst();!TabFormModell.eof();TabFormModell.moveNext())
        {
          int iAIC_Modell = Qry.getInteger("select aic_Modell from begriff b join Modell m on b.aic_begriff=m.aic_begriff where kennung='" + TabFormModell.getS("Modell") + "'");
          int iAIC_Form = Qry.getInteger("select aic_formular from begriff b join formular f on b.aic_begriff=f.aic_begriff where kennung='" + TabFormModell.getS("Formular") + "'");
          if (iAIC_Modell>0 && iAIC_Form>0)
          {
            Qry.add("AIC_Modell",iAIC_Modell);
            Qry.addWhere("AIC_Formular",iAIC_Form);
            Qry.update("Formular");
          }
          else
            Static.printError((iAIC_Modell==0 ? "DefImport.FormModell: Modell "+TabFormModell.getS("Modell")+" nicht gefunden! ":"")+
                              (iAIC_Form==0 ? "DefImport.FormModell: Formular "+TabFormModell.getS("Formular")+" nicht gefunden!":""));
        }
      g.printExec("*TabFormBegriffe");
      if (TabFormBegriffe != null)
      {
//    	int iCDF=g.getCodeAic("Zuordnungsart", "DetailForm");
//    	g.exec("delete from Beg2_Z where AIC_Tabellenname="+g.iTabBegriff+" and Art="+iCDF); //TODO nur aktuelle löschen
        for(TabFormBegriffe.moveFirst();!TabFormBegriffe.eof();TabFormBegriffe.moveNext())
        {
          String sBG=TabFormBegriffe.getS("BG");
          int iAIC_Begriff = Qry.getInteger("select aic_begriff from begriff where kennung='" + TabFormBegriffe.getS("Begriff") + "'"+" and aic_begriffgruppe="+g.TabBegriffgruppen.getAic(sBG));
          int iAIC_Form = Qry.getInteger("select aic_begriff from begriff where kennung='" + TabFormBegriffe.getS("Formular") + "'"+" and aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame"));
          if (iAIC_Begriff>0 && iAIC_Form>0)
          {
        	  if (!VecBegZ.contains(iAIC_Form))
        	  {
        		  VecBegZ.addElement(iAIC_Form);
        		  g.exec("delete from Beg2_Z where AIC_Begriff="+iAIC_Form+" and Art="+iCDF);
        	  }
            Qry.add("AIC_Begriff", iAIC_Form);
  			Qry.add("AIC_Tabellenname",g.iTabBegriff);
  			Qry.add("AIC_Fremd",iAIC_Begriff);
  			Qry.add("Reihe",TabFormBegriffe.getI("Reihe"));
  			Qry.add("Art",iCDF);
  			Qry.insert("Beg2_Z", false);
          }
          else
            Static.printError((iAIC_Begriff==0 ? "DefImport.FormModelle: "+sBG+" "+TabFormBegriffe.getS("Begriff")+" nicht gefunden! ":"")+
                              (iAIC_Form==0 ? "DefImport.FormModelle: Formular "+TabFormBegriffe.getS("Formular")+" nicht gefunden!":""));
        }
      }
      g.printExec("*TabFormStt");
      if (TabFormStt != null)
      {
//    	int iCDF=g.getCodeAic("Zuordnungsart", "DetailForm");
//    	g.exec("delete from Beg2_Z where AIC_Tabellenname="+g.iTabStt+" and Art="+iCDF);
        for(TabFormStt.moveFirst();!TabFormStt.eof();TabFormStt.moveNext())
        {
          int iAIC_Stt = Qry.getInteger("select aic_Stammtyp from Stammtyp where kennung='" + TabFormStt.getS("Stt") + "'");
          int iAIC_Form = Qry.getInteger("select aic_begriff from begriff where kennung='" + TabFormStt.getS("Formular") + "'");
          if (iAIC_Stt>0 && iAIC_Form>0)
          {
        	  if (!VecBegZ.contains(iAIC_Form))
        	  {
        		  VecBegZ.addElement(iAIC_Form);
        		  g.exec("delete from Beg2_Z where AIC_Begriff="+iAIC_Form+" and Art="+iCDF);
        	  }
            Qry.add("AIC_Begriff", iAIC_Form);
  			Qry.add("AIC_Tabellenname",g.iTabStt);
  			Qry.add("AIC_Fremd",iAIC_Stt);
  			Qry.add("Reihe",TabFormStt.getI("Reihe"));
  			Qry.add("Art",iCDF);
  			Qry.insert("Beg2_Z", false);
          }
          else
            Static.printError((iAIC_Stt==0 ? "DefImport.FormStt: Stammtyp "+TabFormStt.getS("Stt")+" nicht gefunden! ":"")+
                              (iAIC_Form==0 ? "DefImport.FormStt: Formular "+TabFormStt.getS("Formular")+" nicht gefunden!":""));
        }
      }
      
        g.printExec("*TabForm2FXML");
        if(TabForm2FXML!=null)
    		for(TabForm2FXML.moveFirst();!TabForm2FXML.eof();TabForm2FXML.moveNext())
    		{
    	        if(TabBegriff.posInhalt((Object)"Frame",TabForm2FXML.getInhalt("Kennung2")))
    			{
    	        	if (g.exists("select aic_formular from formular where aic_begriff=?", TabBegriff.getS("AIC")))
    	        	{
    	                Qry.addWhere("AIC_Begriff",TabBegriff.getI("AIC"));
    					Qry.add("FXML",TabForm2FXML.getS("Kennung1"));
    					Qry.update("Formular");
    	        	}
    	        	else
    	        	{
    	        		printError("DefImport(Form2FXML): WebSite-Kennung für Formular "+TabForm2FXML.getInhalt("Kennung2")+"nicht anlegbar, da nicht gefunden");
    	        	}
    			}
    			else
    				printError("DefImport(Form2FXML): "+TabForm2FXML.getInhalt("Kennung2")+" nicht gefunden");
    		}	
        /*g.printExec("*TabDruckModell");
                for(TabDruckModell.moveFirst();!TabDruckModell.eof();TabDruckModell.moveNext())
                {
                  int iAIC_Modell = Qry.getInteger("select aic_begriff from begriff where kennung='" + TabDruckModell.getS("Modell") + "'");
                  int iAIC_Druck = Qry.getInteger("select aic_begriff from begriff where kennung='" + TabDruckModell.getS("Druck") + "'");
                  if (iAIC_Modell>0 && iAIC_Druck>0)
                  {
                    g.exec("delete from begriff_zuordnung where aic_begriff="+iAIC_Druck);
                    Qry.add("beg_aic_begriff",iAIC_Modell);
                    Qry.add("aic_begriff",iAIC_Druck);
                    Qry.insert("begriff_zuordnung",false);
                  }
                  else
                    Static.printError((iAIC_Modell==0 ? "DefImport.FormModell: Modell "+TabDruckModell.getS("Modell")+"nicht gefunden! ":"")+
                                      (iAIC_Druck==0 ? "DefImport.FormModell: Druck "+TabDruckModell.getS("Druck")+"nicht gefunden!":""));
        }*/
	/*g.printExec("*Nur_Begriff");
	if(TabNurBegriff!=null)
	{
		for(TabNurBegriff.moveFirst();!TabNurBegriff.eof();TabNurBegriff.moveNext())
		{
			int iAIC=SQL.getInteger(g,"select b.aic_begriff from begriff b join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where b.kennung='"+TabNurBegriff.getS("Begriff")+"' and bg.kennung='"+TabNurBegriff.getS("Begriffgruppe")+"'");
			if(iAIC>0)
			{
				Qry.add0("fixe_Sprache",TabNurBegriff.getB("Fix"));
				Qry.add0("jeder",TabNurBegriff.getB("Jeder"));
				Qry.add0("combo",TabNurBegriff.getB("Combo"));
				Qry.add("bits",TabNurBegriff.getI("bits"));
                                Qry.add("DefBezeichnung",TabNurBegriff.getS("DefBezeichnung"));
				if(!TabNurBegriff.getS("Schrift").equals(""))
				{
					if(TabSchrift.posInhalt("Kennung",TabNurBegriff.getS("Schrift")))
						Qry.add("AIC_Schrift",TabSchrift.getI("AIC"));
					else
						Static.printError("Nur_Begriff: Schrift konnte nicht gefunden werden!!!");
				}
				Qry.update("Begriff",iAIC);
			}
		}
	}
	g.printExec("*Nur_Eigenschaft");
	if(TabNurEigenschaft!=null)
	{
		for(TabNurEigenschaft.moveFirst();!TabNurEigenschaft.eof();TabNurEigenschaft.moveNext())
		{
			int iAIC=SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='"+TabNurEigenschaft.getS("Kennung")+"'");
			if(iAIC>0)
			{
				Qry.add("feldlaenge",TabNurEigenschaft.getInt("Feldlaenge"));
				Qry.add("format",TabNurEigenschaft.getS("Format"));
				Qry.add("min",TabNurEigenschaft.getInt("Min"));
				Qry.add("max",TabNurEigenschaft.getInt("Max"));
				Qry.add("bits",TabNurEigenschaft.getInt("Bits"));
				int iDT=g.getBegriffS("Datentyp",TabNurEigenschaft.getS("Datentyp"));
				if(iDT>0)
					Qry.add("aic_begriff",iDT);

				Qry.update("Eigenschaft",iAIC);
			}
		}
	}
	g.printExec("*Nur_Stammtyp");
	if(TabNurStammtyp!=null)
	{
		for(TabNurStammtyp.moveFirst();!TabNurStammtyp.eof();TabNurStammtyp.moveNext())
		{
			int iAIC=SQL.getInteger(g,"select aic_stammtyp from stammtyp where kennung='"+TabNurStammtyp.getS("Kennung")+"'");
			if(iAIC>0)
			{
				Qry.add0("translate",TabNurStammtyp.getB("Translate"));
				Qry.add0("ende",TabNurStammtyp.getB("Ende"));
				Qry.add0("benutzer",TabNurStammtyp.getB("Benutzer"));
				Qry.add0("einheit",TabNurStammtyp.getB("Einheit"));
				Qry.add0("lizenz",TabNurStammtyp.getB("Lizenz"));
				Qry.add0("copy",TabNurStammtyp.getB("Copy"));
				Qry.update("Stammtyp",iAIC);
			}
		}
	}*/
        if (TabStammAbf!=null && !bKeineStamm || VecMD!=null && VecMD.size()>0)
        {
          g.printExec("*StammImport Anfang");
          if (Gau != null)
            Gau.setText("Stammimport",5);
          if (!bShow)
          {
        	  g.commit();
        	  g.TabAbfragen=null;
              g.TabModelle=null;
              if (Static.cache())
                Static.clearCache();
        	  g.Refresh();
        	  g.start();
          }
          else
        	  g.Refresh();
          g.setAnlage("Import");
          //TabStammAbf.showGrid("TabStammAbf");
          if (TabStammAbf!=null)
           for (TabStammAbf.moveFirst();!TabStammAbf.out();TabStammAbf.moveNext())
           {
            String sAbf=TabStammAbf.getS("Abf");
            if (TabStammAbf.getInhalt("Out")!=null && (TabStammImp==null || !TabStammImp.posInhalt("Abfrage",sAbf)))
            {
              if (TabStammImp!=null)
              {
                TabStammImp.addInhalt("Abfrage", sAbf);
                TabStammImp.addInhalt("File", sFile1);
              }
              if (g.getLog() <= 0)
                g.Login();
              ShowAbfrage Abf = new ShowAbfrage(g, sAbf);
              Tabellenspeicher TabSA = Abf.OutToTab((JCOutlinerNode)TabStammAbf.getInhalt("Out"));
              //TabSA.showGrid(sAbf);
              //g.fixInfo("vor Import "+sAbf);
              new Import(g, sAbf, TabSA);
              //g.fixInfo("nach Import "+sAbf);
            }
           }
          g.TabModelle=null;
          if (VecMD!=null && VecMD.size()>0)
            for (int i=0;i<VecMD.size();i++)
            {
              String sKennung=Sort.gets(VecMD,i);
              g.printExec("*Modellaufruf "+sKennung);
              if (g.getLog() <= 0)
                g.Login();
              //TCalc.Berechnen(g, TabFormular.getI("Aic"), iMbits, iStamm, Vec, Self, 0,(JButton)e.getSource());
              try
              {
                g.fixInfo("vor Calc "+sKennung);
                Calc calc = new Calc(null, g, g.BegriffToModell(g.getBegriffAicS("Modell", sKennung)), false, Static.AicToVec(0),false, -1, null, 0, null, null,null,null,0);
                g.fixInfo("nach Calc "+sKennung);
              }
              catch(Exception e)
              {
                e.printStackTrace();
                printError("Modellaufruf von "+sKennung+" fehlerhaft!");
              }
            }
          g.setAnlage("x");
        }
        if (TabAbschlusstyp != null)
        {
      	int iProg=g.getCodeAic("Programm",sProg);
          for(TabAbschlusstyp.moveFirst();!TabAbschlusstyp.eof();TabAbschlusstyp.moveNext())
          {
            int iAicS=TabAbschlusstyp.getS("Stt").equals("") ? 0:g.TabStammtypen.getAic(TabAbschlusstyp.getS("Stt").toUpperCase());
              //if (TabAbschlusstyp.getS("Stt").equals("") || g.TabStammtypen.posInhalt("Kennung",TabAbschlusstyp.getS("Stt").toUpperCase()))
              //{
                Qry.add0("aic_stammtyp", iAicS);//TabAbschlusstyp.getS("Stt").equals("") ? 0:g.TabStammtypen.getI("AIC"));
                Qry.add("bits", TabAbschlusstyp.getI("bits"));
                Qry.add("Prog", iProg);
                if (TabAbschlusstyp.getB("Vorhanden"))
                {
                  Qry.update("Abschlusstyp", TabAbschlusstyp.getI("AIC"));
                  g.exec("delete from abschlussdefinition where aic_Abschlusstyp="+TabAbschlusstyp.getI("AIC"));
                }
                else
                {
                  Qry.add("Kennung", TabAbschlusstyp.getS("Kennung"));                
                  TabAbschlusstyp.setInhalt("AIC", Qry.insert("Abschlusstyp", true));
                }
              //}
          }
          if (TabAbschlussdef != null)
           for(TabAbschlussdef.moveFirst();!TabAbschlussdef.eof();TabAbschlussdef.moveNext())
           {
             int iPosB=g.TabErfassungstypen.getPos("Kennung",TabAbschlussdef.getS("Bew").toUpperCase());
             int iPosE=g.TabEigenschaften.getPos("Kennung",TabAbschlussdef.getS("Eig").toUpperCase());
             if (iPosB>=0 && iPosE>=0)
             {
               Qry.add("aic_Abschlusstyp",TabAbschlusstyp.getAic(TabAbschlussdef.getS("Kennung")));
               Qry.add("aic_bewegungstyp",g.TabErfassungstypen.getI(iPosB,"AIC"));
               Qry.add("aic_eigenschaft",g.TabEigenschaften.getI(iPosE,"AIC"));
               Qry.insert("Abschlussdefinition", false);
             }
           }
        }
        
        g.printExec("*TabAbf2Stamm");
    	if(TabAbf2Stamm!=null)
    	for(TabAbf2Stamm.moveFirst();!TabAbf2Stamm.eof();TabAbf2Stamm.moveNext())
    	{
    		int iAIC_Stamm = Qry.getInteger("select aic_stamm from stammview2 where kennung='"+TabAbf2Stamm.getS("Kennung1")+"'");
    		if(iAIC_Stamm>0)
    		{
    			Qry.addWhere("AIC_Abfrage",Qry.getInteger("select a.aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Abfrage' and b.kennung='"+TabAbf2Stamm.getS("Kennung2")+"'"));
    			Qry.add("WebStamm",iAIC_Stamm);
    			Qry.update("Abfrage");
    		}
    		else
    			g.printError("TabAbf2Stamm: Stamm "+TabAbf2Stamm.getS("Kennung1")+" nicht gefunden");
    	}
    	
//        String sOld="";
//        int iAufgabe=0;
//        int iBGAG=g.TabBegriffgruppen.getAic("Aufgabe");
        int iBGA=g.TabBegriffgruppen.getAic("Abfrage");
        if (TabStatus != null)
        {
        	// Aufgabe-Status auf neue Struktur umstellen
//        	DefAufgabe.checkKonvert(g);
        	//inaktive merken
        	int TOD=0x8000; 		  
        	Vector<Integer> VecIA=SQL.getVector("select aic_status from status where"+g.bit("bits",TOD),g); 
        	g.fixInfo("inaktive Status:"+VecIA);
//        	//alle Status alten Status löschen
//	          Vector<Integer> VecS=SQL.getVector("select aic_status from status where kennung is not null"+(VecIA.size()>0 ? " and aic_status not"+Static.SQL_in(VecIA):""),g); 
//	          if (VecS.size()>0)
//	          {
//	        	  g.exec("delete from status_zuordnung where aic_status"+Static.SQL_in(VecS));
//	        	  g.exec("delete from status where aic_status"+Static.SQL_in(VecS));
//	          }
      	  for(TabStatus.moveFirst();!TabStatus.eof();TabStatus.moveNext())
            {
//      		  String sAufgabe=TabStatus.getS("Aufgabe");
//      		  if (!sAufgabe.equals(sOld))
//      		  {
//      			  sOld=sAufgabe;
//      			  iAufgabe=SQL.getInteger(g, "select aic_begriff from begriff where aic_begriffgruppe="+iBGAG+" and kennung='"+sAufgabe+"'");
//      			  
//      			  //g.exec("delete from status where aufgabe="+iAufgabe);
//      		  }
//      		  if (iAufgabe>0)
      		  {
      			String sK=TabStatus.getS("Kennung");
      			int iStatus=SQL.getInteger(g, "select aic_status from status where kennung='"+sK+"'");
      			String sFilter=TabStatus.getS("Filter");
      			String sHS=TabStatus.getS("HS");
  	    		  Qry.add0("Abfrage",Static.Leer(sFilter) ? 0:SQL.getInteger(g, "select aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff where aic_begriffgruppe="+iBGA+" and kennung='"+sFilter+"'"));
  	    		  Qry.add0("Hauptschirm",Static.Leer(sHS) ? 0:SQL.getInteger(g, "select aic_hauptschirm from hauptschirm where kennung='"+sHS+"'"));
  	    		  Qry.add0("aic_code", g.getCodeAic("Darstellung", TabStatus.getS("Dar")));
  	    		  Qry.add("bits",TabStatus.getI("bits"));
//  	    		  Qry.add("Reihenfolge",TabStatus.getI("Reihe")); 	 
  	    		  Qry.add("Bildname",TabStatus.getS("Bildname"));
  	    		  String sStamm=TabStatus.getS("Stamm");
  	    		  if (Static.Leer(sStamm))
  	    			Qry.add0("aic_stamm",0);
  	    		  else
  	    		  {
  	    			  String sStt=TabStatus.getS("Stt");
  	    			  int iAic=SQL.getInteger(g, "select s.aic_stamm from stammview2 s join stammtyp stt on s.aic_stammtyp=stt.aic_stammtyp where s.kennung='"+sStamm+"' and stt.kennung='"+sStt+"'");
  	    			  if (iAic>0)
  	    				Qry.add("aic_stamm",iAic);
  	    			  else
  	    				g.printError("TabStatus: Stamm "+sStamm+" von Stt "+sStt+" nicht gefunden");
  	    		  }
//  	    		  Qry.add("Aufgabe",iAufgabe);    			  
  	    		  if (iStatus>0)
  	    		  {
  	    			  Qry.update("Status", iStatus);
  	    			  g.exec("delete from status_zuordnung where aic_status="+iStatus);
  	          	  
  	    		  }
  	    		  else
  	    		  {
  	    			  Qry.add("Kennung",sK);
  	    			  Qry.insert("Status", false);
  	    		  }
      		  }
//      		  else
//      			  g.printError("TabStatus: Aufgabe "+sAufgabe+" nicht gefunden");
            }
      	  if (VecIA.size()>0)
      	  {
      		  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_status,bits from status where aic_status"+Static.SQL_in(VecIA),true);
      		  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
      			if ((Tab.getI("bits")&TOD)==0)
      			  g.exec("update status set bits="+(Tab.getI("bits")+TOD)+" where aic_status="+Tab.getI("aic_status"));
      	  }
//      	  Vector<Integer> VecA=SQL.getVector("select aic_begriff from begriff where aic_begriffgruppe="+iBGAG+" and (select count(aic_status) from status where aic_begriff=aufgabe)=0",g); // Aufgaben ohne Status ermitteln
//      	  if (VecA.size()>0)
//      		g.exec("update begriff set tod=1 where aic_begriff"+Static.SQL_in(VecA));
        }
        //Status-Zuordnung
        Vector<Integer> VecA=new Vector<Integer>();
        if (TabStatusZ != null)      	  
      	  for(TabStatusZ.moveFirst();!TabStatusZ.eof();TabStatusZ.moveNext())
            {
      		  String sK=TabStatusZ.getS("Kennung");
      		  String sB=TabStatusZ.getS("Beg");
      		  String sBG=TabStatusZ.getS("BG");
      		  int iPos=TabStatusZ.getI("pos");
      		  int iStatus=SQL.getInteger(g, "select aic_status from status where kennung='"+sK+"'");
      		  int iBG=g.TabBegriffgruppen.getAic(sBG);
      		  int iZ=SQL.getInteger(g, "select aic_begriff from begriff where aic_begriffgruppe="+iBG+" and kennung='"+sB+"'");
      		  if (iStatus>0 && iZ>0)
      		  {
      			  if (iPos>60000 && !VecA.contains(iZ))
      			  {
      				VecA.addElement(iZ);
      				int iAnz=g.exec("delete from status_zuordnung where AIC_BEGRIFF="+iZ+" and Pos>60000");
      				if (iAnz>0)
      					g.fixtestError("lösche "+iAnz+" Zuordnungen von Auftrag "+sB);
      			  }
      			  Qry.add("AIC_STATUS",iStatus);
      			  Qry.add("AIC_BEGRIFF",iZ);
      			  Qry.add("Pos",iPos);
      			  Qry.insert("STATUS_ZUORDNUNG", false);
      		  }
      		  else if (iStatus==0)
      			  g.printError("TabStatusZ: Status "+sK+" nicht gefunden");
      		  else if (iZ==0)
      			  g.fixtestError("TabStatusZ: Begriff "+sB+" in "+sBG+" nicht gefunden");    			  
            }
      //Abschlusstyp-Zuordnung
        if (TabBAT != null)      	  
      	  for(TabBAT.moveFirst();!TabBAT.eof();TabBAT.moveNext())
            {
      		  String sK=TabBAT.getS("AST");
      		  String sB=TabBAT.getS("Beg");
      		  String sBG=TabBAT.getS("BG");
      		  int iAT=SQL.getInteger(g, "select aic_Abschlusstyp from Abschlusstyp where kennung='"+sK+"'");
      		  int iBG=g.TabBegriffgruppen.getAic(sBG);
      		  int iZ=SQL.getInteger(g, "select aic_begriff from begriff where aic_begriffgruppe="+iBG+" and kennung='"+sB+"'");
      		  if (iAT>0 && iZ>0)
      			  g.exec("update begriff set AIC_Abschlusstyp="+iAT+" where aic_begriff="+iZ);
      		  else if (iAT==0)
      			  g.printError("TabBAT: Abschlusstyp "+sK+" nicht gefunden");
      		  else if (iZ==0)
      			  g.printError("TabBAT: Begriff "+sB+" in "+sBG+" nicht gefunden");    			  
            }

	g.printExec("*Bezeichnung,Memo,Bild Anfang");
        if (Gau != null)
          Gau.setText("Bezeichnung,Memo,Bild",6);
        Feiertage.checkFT(g);
    if (TabBegriff != null)
    {
        if (bBezeichnung)
          g.exec("delete from bezeichnung where aic_tabellenname="+g.iTabBegriff+" and"+g.in("aic_fremd",TabBegriff.getVecSpalteI("AIC")));
        if (bBild)
        {
          g.exec("delete from DATEN_BILD where aic_tabellenname=" + g.iTabBegriff + " and" + g.in("aic_fremd", TabBegriff.getVecSpalteI("AIC")));
          g.exec("delete from DATEN_BILD where aic_tabellenname=" + g.TabTabellenname.getAic("TABELLENNAME"));
        }
		Tabellenspeicher TabBezMemBil = new Tabellenspeicher(g,new String[]{"Art","Tab"});
		TabBezMemBil.addInhalt("Art","BEZEICHNUNG");
		TabBezMemBil.addInhalt("Tab",TabBezeichnung);
		TabBezMemBil.addInhalt("Art","DATEN_MEMO");
		TabBezMemBil.addInhalt("Tab",TabDaten_Memo);
		TabBezMemBil.addInhalt("Art","DATEN_BILD");
		TabBezMemBil.addInhalt("Tab",TabDaten_Bild);
	        TabBezMemBil.addInhalt("Art","TOOLTIP");
		TabBezMemBil.addInhalt("Tab",TabTooltip);
		Tabellenspeicher TabZustand=new Tabellenspeicher(g,"select aic_zustand aic,kennung from zustand",true);
	  for(TabBezMemBil.moveFirst();!TabBezMemBil.eof();TabBezMemBil.moveNext())
	  {
		Tabellenspeicher TabTAB=(Tabellenspeicher)TabBezMemBil.getInhalt("Tab");
		String sArt=TabBezMemBil.getS("Art");

		String sTN="";
		String sSprache="";
		int iAIC_Tabellenname=0;
		int iAIC_Sprache=0;
		int iAic_Zustand=0;
		boolean bBegSpaCod=false;
		Tabellenspeicher TabBMB=null;
		for(TabTAB.moveFirst();!TabTAB.eof();TabTAB.moveNext())
		{
			if(!sTN.equals(TabTAB.getS("Tabellenname").toUpperCase())||!sSprache.equals(TabTAB.getS("Sprache")))
			{
				sTN=TabTAB.getS("Tabellenname").toUpperCase();
				sSprache=TabTAB.getS("Sprache");
				iAIC_Tabellenname = g.TabTabellenname.getAic(sTN);
				if (sArt.equals("DATEN_BILD"))
				  iAic_Zustand=TabZustand.posInhalt("Kennung",TabTAB.getS("Sprache")) ? TabZustand.getI("AIC"):0;
				else
				  iAIC_Sprache = TabSprache.posInhalt("Kennung",TabTAB.getS("Sprache")) ? TabSprache.getI("AIC"):0;
				bBegSpaCod = sTN.equals("BEGRIFF")||sTN.equals("SPALTE")||sTN.equals("CODE")||sTN.equals("STAMM")||sTN.equals("AUSWAHL");
                                if (sTN.equals("BEFEHL"))
                                  TabBMB = null;
                                else
                                  TabBMB=new Tabellenspeicher(g,"select aic_"+sTN+" aic,be."+(sTN.equals("SPALTE")?"nummer":"kennung")+" kennung"+(bBegSpaCod?",bg.kennung gruppe":"")+
                                      ",(select "+(sArt.equals("BEZEICHNUNG")?"bezeichnung":sArt.equals("TOOLTIP")?"memo2":sArt.equals("DATEN_MEMO")?g.concat("titel","memo"):"filename")+" from "+sArt+" aub"+
                                		  " where aub.aic_tabellenname="+g.TabTabellenname.getAic(sTN)+" and aub.aic_fremd=be.aic_"+sTN+" and aub.aic_"+(iAic_Zustand>0 ? "zustand="+iAic_Zustand:"sprache="+iAIC_Sprache)+") Bezeichnung"+
                                      (sArt.equals("DATEN_MEMO")? ",(select fix from DATEN_MEMO where aic_tabellenname="+g.TabTabellenname.getAic(sTN)+" and aic_fremd=be.aic_"+sTN+
                                    		  (iAic_Zustand>0 ? " and aic_zustand="+iAic_Zustand:" and aic_sprache="+iAIC_Sprache)+") fix":"")+
                                      " from "+(sTN.equals("STAMM") ? "stammview":sTN)+" be "+(bBegSpaCod?"right outer join "+(sTN.equals("SPALTE")?"abfrage a on be.aic_abfrage=a.aic_abfrage join begriff bg on a.aic_begriff=bg.aic_begriff ":
                                      sTN.equals("STAMM")?"stammtyp bg on be.aic_stammtyp=bg.aic_stammtyp ":sTN.equals("AUSWAHL")?"eigenschaft bg on be.aic_eigenschaft=bg.aic_eigenschaft":"begriffgruppe bg on be.aic_begriffgruppe=bg.aic_begriffgruppe "):"")+
                                      " where be."+(sTN.equals("SPALTE")?"nummer":"kennung")+" is not null order by "+(bBegSpaCod?"gruppe,":"")+"kennung",true);
				if(bBegSpaCod)
				{
					TabBMB.sGruppe="GRUPPE";
					TabBMB.sAIC="KENNUNG";
				}
			}

			boolean bVorhanden=true;
			//g.testInfo("sTN="+sTN+", bBegSpaCod="+bBegSpaCod+" Begriff="+TabTAB.getS("Begriff")+":"+Static.className(TabTAB.getInhalt("Begriff"))+" Gruppe="+TabTAB.getS("Begriffgruppe")+":"+Static.className(TabTAB.getInhalt("Begriffgruppe")));
                        boolean bPos=false;
			if(sTN.equals("SPALTE"))
			{
				bVorhanden = TabSpalte.posInhalt("aic_spalte",new Integer(TabTAB.getS("Begriff")))?TabSpalte.getB("Vorhanden"):true;
				bPos=TabBMB.posInhalt(TabSpalte.getInhalt("abfrage"),TabSpalte.getInhalt("nummer"));
			}
			else if(bBegSpaCod)
                          bPos=TabBMB.posInhalt(TabTAB.getInhalt("Begriffgruppe"),TabTAB.getInhalt("Begriff"));
                        else if (!sTN.equals("BEFEHL"))
                                bPos=TabBMB.posInhalt("kennung",TabTAB.getInhalt("Begriff"));
			//TabTAB.showGrid("TAB");
			//TabBMB.showGrid("BMB");
			//TabBegriff.showGrid("Begriff");
			if(sTN.equals("BEFEHL") || bPos)
			{
				if(sTN.equals("BEGRIFF"))
					bVorhanden = TabBegriff.posInhalt("AIC",TabBMB.getI("aic"))?TabBegriff.getB("Vorhanden"):true;
				else if(sTN.equals("BEWEGUNGSTYP"))
					bVorhanden = TabBewegungstyp.posInhalt("AIC",TabBMB.getI("aic"))?TabBewegungstyp.getB("Vorhanden"):true;
				else if(sTN.equals("STAMMTYP"))
					bVorhanden = TabStammtyp.posInhalt("AIC",TabBMB.getI("aic"))?TabStammtyp.getB("Vorhanden"):true;
				else if(sTN.equals("EIGENSCHAFT"))
					bVorhanden = TabEigenschaft.posInhalt("AIC",TabBMB.getI("aic"))?TabEigenschaft.getB("Vorhanden"):true;
				else if(sTN.equals("FARBE"))
					bVorhanden = TabFarbe.posInhalt("AIC",TabBMB.getI("aic"))?TabFarbe.getB("Vorhanden"):true;
                                else if(sTN.equals("ABSCHLUSSTYP"))
					bVorhanden = TabAbschlusstyp.posInhalt("AIC",TabBMB.getI("aic"))?TabAbschlusstyp.getB("Vorhanden"):true;
				else if(sTN.equals("SCHRIFT"))
					bVorhanden = TabSchrift.posInhalt("AIC",TabBMB.getI("aic"))?TabSchrift.getB("Vorhanden"):true;
				else if(sTN.equals("ABSCHNITT"))
					bVorhanden = false;//TabAbschnitt.posInhalt("AIC",iAIC_Fremd)?TabAbschnitt.getB("Vorhanden"):true;
				else if(sTN.equals("CODE") || sTN.equals("STAMM") || sTN.equals("BEFEHL"))
					bVorhanden = false;
                else if(sTN.equals("ROLLE"))
					bVorhanden = TabRolle.posInhalt("AIC",TabBMB.getI("aic"))?TabRolle.getB("Vorhanden"):true;
                else if(sTN.equals("AUSWAHL") || sTN.equals("STATUS") || sTN.equals("SPRACHE") || sTN.equals("ZUSTAND") || sTN.equals("LAND") || sTN.equals("RASTER"))
					bVorhanden = false;

				if(sArt.equals("BEZEICHNUNG")&&(bBezeichnung || !bVorhanden))
					g.setBezeichnung(TabBMB.getS("Bezeichnung"),TabTAB.getS("Bezeichnung"),iAIC_Tabellenname,TabBMB.getI("aic"),iAIC_Sprache);
                                else if(sArt.equals("TOOLTIP")&&(bTooltip || !bVorhanden) && !TabBMB.getM("Bezeichnung").equals(TabTAB.getS("Tooltip")))
                                  g.setTooltip(TabTAB.getS("Tooltip"),iAIC_Tabellenname,TabBMB.getI("aic"),iAIC_Sprache);
				else if(sArt.equals("DATEN_MEMO")&& (bMemo || !bVorhanden) &&
                                        (sTN.equals("BEFEHL") || !TabBMB.getM("Bezeichnung").equals(TabTAB.getS("Titel")+TabTAB.getM("Memo")) && !TabBMB.getB("fix")))
				{
					//g.progInfo("Ändere Memo von "+TabBMB.getM("Bezeichnung")+"->"+TabTAB.getS("Titel")+"/"+TabTAB.getM("Memo"));
                                        if (sTN.equals("BEFEHL"))
                                        {
                                          if (TabBefehle.posInhalt("alt",TabTAB.getI("Begriff")))
                                            g.setMemo(TabTAB.getM("Memo"), TabTAB.getS("Titel"), iAIC_Tabellenname, TabBefehle.getI("neu"), iAIC_Sprache);
                                        }
                                        else
                                          g.setMemo(TabTAB.getM("Memo"),TabTAB.getS("Titel"),iAIC_Tabellenname,TabBMB.getI("aic"),iAIC_Sprache);
				}
				else if(sArt.equals("DATEN_BILD") && (bBild || !bVorhanden) && (sTN.equals("ABSCHNITT") || !TabBMB.getS("Bezeichnung").equals(TabTAB.getS("Filename"))))
				{
					String sBild=TabTAB.getS("Filename");
					g.setImage(TabTAB.getS("Filename"),iAIC_Tabellenname,TabBMB.getI("aic"),iAic_Zustand);
					if(sTN.equals("ABSCHNITT"))
					{
						int iF=sFilename.lastIndexOf(File.separator);
						String sFile2=sFilename.substring(0,iF+1)+"Images"+File.separator+sBild;
//						ImageIO.write(Static.imageToBufferedImage(Img), sBild.substring(sBild.lastIndexOf('.')+1).toLowerCase(), file);
						g.UpdateMini(sFile2,"update daten_bild set Bild=? where aic_tabellenname="+g.TabTabellenname.getAic("ABSCHNITT")+" and aic_fremd="+TabBMB.getI("aic"));
					}
				}
			}
                        else
                          g.testInfo(sArt+"/"+sTN+":"+(bBegSpaCod?TabTAB.getInhalt("Begriffgruppe")+"/":"")+TabTAB.getInhalt("Begriff")+" nicht gefunden!");
		}
		g.clockInfo(TabBezMemBil.getS("Art"),lClock);
	  }
    }
        /*if (TabStamm_Bild != null)
        {
          int iTab=g.TabTabellenname.getAic("STAMM");
          for (TabStamm_Bild.moveFirst(); !TabStamm_Bild.eof(); TabStamm_Bild.moveNext())
          {
            int iStt = g.TabStammtypen.getAic(TabStamm_Bild.getS("Stt").toUpperCase());
            int iStamm = SQL.getInteger(g, "select aic_stamm from stammview2 where aic_stammtyp=" + iStt + " and Kennung='" + TabStamm_Bild.getS("Kennung") + "'");
            //g.progInfo("TabStamm_Bild: Tab="+iTab+", Stt="+iStt+", Stamm="+iStamm);
            if (iStamm>0)
              g.setImage(TabStamm_Bild.getS("filename"), iTab, iStamm, 0);
          }
        }*/

        /*if (TabStamm_Memo != null)
        {
          int iTab=g.TabTabellenname.getAic("STAMM");
          for (TabStamm_Memo.moveFirst(); !TabStamm_Memo.eof(); TabStamm_Memo.moveNext())
          {
            int iStt = g.TabStammtypen.getAic(TabStamm_Memo.getS("Stt").toUpperCase());
            int iStamm = SQL.getInteger(g, "select aic_stamm from stammview2 where aic_stammtyp=" + iStt + " and Kennung='" + TabStamm_Memo.getS("Kennung") + "'");
            int iAIC_Sprache = TabSprache.posInhalt("Kennung",TabStamm_Memo.getS("Sprache")) ? TabSprache.getI("AIC"):0;
            //g.progInfo("TabStamm_Bild: Tab="+iTab+", Stt="+iStt+", Stamm="+iStamm);
            if (iStamm>0 && iAIC_Sprache>0)
              g.setMemo(TabStamm_Memo.getM("memo"),TabStamm_Memo.getS("titel"), iTab, iStamm, iAIC_Sprache);
          }
        }*/

	g.printExec("*Bezeichnung,Memo,Bild Ende");
	Zip.close();
        if (Gau != null)
          Gau.setText("Rest",7);
	//g.bSaveExec = false;
        g.TabAbfragen=null;
        Formular.clearForm();
        g.TabModelle=null;
        if (Static.cache())
          Static.clearCache();
        sR=Reinigung.SpaltenCheck(g,false);
        if (Reinigung.bFehler)
          iBits |= 16;
        else
          sR="";
        g.Refresh();
        //if (CbxANR.isSelected())
        g.ErmittleJeder();



        //Reinigung.NullZeilen(g, false);
        if (!sR.equals("") && TxtProt!= null)
          TxtProt.setText(TxtProt.getValue() + "\n\n" + sR);

	g.clockInfo("check/refresh",lClock);
        g.TabBegriffe.sAIC="Kennung";
        //Kunde_Zuordnung//
        Integer iAIC_Kunde_Bg=new Integer(g.TabBegriffgruppen.getAic("Kunde"));
        if(TabKunde_Zuordnung!=null)
        {
                int iAIC_Begriff=0;
                int iNicht=0;
                int iOk=0;
                for(TabKunde_Zuordnung.moveFirst();!TabKunde_Zuordnung.eof();TabKunde_Zuordnung.moveNext())
                {
                        if(g.TabBegriffe.posInhalt(iAIC_Kunde_Bg,TabKunde_Zuordnung.getS("Kennung1")))
                        {
                                if(iAIC_Begriff!=g.TabBegriffe.getI("AIC"))
                                {
                                        iAIC_Begriff = g.TabBegriffe.getI("AIC");
                                        g.exec("delete from begriff_zuordnung where aic_begriff="+iAIC_Begriff);
                                }
                                Integer iAIC_Bg=new Integer(g.TabBegriffgruppen.getAic(TabKunde_Zuordnung.getS("Begriffgruppe")));
                                if(g.TabBegriffe.posInhalt(iAIC_Bg,TabKunde_Zuordnung.getS("Kennung2")))
                                {
                                        int iBeg_AIC_Begriff = g.TabBegriffe.getI("AIC");

                                        if(iBeg_AIC_Begriff>0&&iAIC_Begriff>0)
                                        {
                                                Qry.add("aic_begriff",iAIC_Begriff);
                                                Qry.add("beg_aic_begriff",iBeg_AIC_Begriff);
                                                Qry.insert("Begriff_Zuordnung",false);
                                                iOk++;
                                        }
                                        else
                                                iNicht++;//printError("DefImport: Fehler bei Kunde_Zuordnung");
                                }
                        }
                }
                g.testInfo("Kunde: gefunden="+iOk+", nicht gefunden="+iNicht);
        }
        else
        {
          Vector Vec = SQL.getVector("select aic_begriff from(select aic_begriff,(select count(*) from begriff_zuordnung where aic_begriff=begriff.aic_begriff) anzahl" +
                                       " from begriff where aic_begriffgruppe=" + iAIC_Kunde_Bg + ") x where anzahl=0", g);
          if (Vec != null && Vec.size() > 0)
            g.exec("delete from begriff where " + g.in("aic_begriff", Vec));
        }

        if(TabDruck_Zuordnung2!=null)
        {
                int iAIC_Begriff=0;
                int iAicOld=0;
                int iNicht=0;
                int iOk=0;
                for(TabDruck_Zuordnung2.moveFirst();!TabDruck_Zuordnung2.eof();TabDruck_Zuordnung2.moveNext())
                {
                  iAIC_Begriff=g.getBegriffAicS("Druck",TabDruck_Zuordnung2.getS("Kennung1"));
                  if (iAIC_Begriff==0)
                    g.fixtestInfo("Druck "+TabDruck_Zuordnung2.getS("Kennung1")+" nicht gefunden");
                  else if (iAIC_Begriff  != iAicOld)
                  {
                    g.exec("delete from begriff_zuordnung where aic_begriff="+iAIC_Begriff);
                    iAicOld=iAIC_Begriff;
                  }
                  int iBeg_AIC_Begriff=g.getBegriffAicS(TabDruck_Zuordnung2.getS("Begriffgruppe"),TabDruck_Zuordnung2.getS("Kennung2"));

                  if(iBeg_AIC_Begriff>0 && iAIC_Begriff>0)
                  {
                          Qry.add("aic_begriff",iAIC_Begriff);
                          Qry.add("beg_aic_begriff",iBeg_AIC_Begriff);
                          Qry.insert("Begriff_Zuordnung",false);
                          iOk++;
                  }
                  else
                    iNicht++;
                }
                g.testInfo("Drucke: gefunden="+iOk+", nicht gefunden="+iNicht);
        }
        //Modul_Zuordnung//
        if (bMC) // 24.6.2013 statt bNormal
          g.exec("delete from begriff_zuordnung where aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul")+")");
          //SQL.exec(g,"delete from begriff_zuordnung from begriff_zuordnung z join begriff on z.aic_begriff=begriff.aic_begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"));
        //Integer iAIC_Modul_Bg=new Integer(g.TabBegriffgruppen.getAic("Modul"));
        if(TabModul_Zuordnung!=null)
	{
		int iAIC_Begriff=0;
                int iAicOld=0;
                int iNicht=0;
                int iOk=0;
		for(TabModul_Zuordnung.moveFirst();!TabModul_Zuordnung.eof();TabModul_Zuordnung.moveNext())
		{
                  iAIC_Begriff=g.getBegriffAicS("Modul",TabModul_Zuordnung.getS("Kennung1"));
                  if (iAIC_Begriff==0)
                    ;//g.fixtestInfo("Modul "+TabModul_Zuordnung.getS("Kennung1")+" nicht gefunden");
                  else if (!bMC && iAIC_Begriff  != iAicOld)
                  {
                    g.exec("delete from begriff_zuordnung where aic_begriff="+iAIC_Begriff);
                    iAicOld=iAIC_Begriff;
                  }
                  int iBeg_AIC_Begriff=g.getBegriffAicS(TabModul_Zuordnung.getS("Begriffgruppe"),TabModul_Zuordnung.getS("Kennung2"));

                  if(iBeg_AIC_Begriff>0 && iAIC_Begriff>0)
                  {
                          Qry.add("aic_begriff",iAIC_Begriff);
                          Qry.add("beg_aic_begriff",iBeg_AIC_Begriff);
                          Qry.insert("Begriff_Zuordnung",false);
                          iOk++;
                  }
                  else
                    iNicht++;
		}
                g.testInfo("Module: gefunden="+iOk+", nicht gefunden="+iNicht);
	}
        if(TabModul_Zuordnung2!=null)
	{
          int iModulAlt=0;
          Tabellenspeicher TabSprache2 = new Tabellenspeicher(g, "select aic_sprache aic,kennung from Sprache", true);
          Tabellenspeicher TabHS = new Tabellenspeicher(g, "select aic_hauptschirm aic,kennung from Hauptschirm where kennung is not null", true);
          for(TabModul_Zuordnung2.moveFirst();!TabModul_Zuordnung2.eof();TabModul_Zuordnung2.moveNext())
          {
            String sTab=TabModul_Zuordnung2.getS("Tab");

            int iModul=g.getBegriffAicS("Modul",TabModul_Zuordnung2.getS("Modul"));
            if (iModul==0)
              ;//g.fixtestInfo("Modul "+TabModul_Zuordnung2.getS("Modul")+" nicht gefunden");
            else if (iModul != iModulAlt)
            {
              iModulAlt=iModul;
              g.exec("delete from modul where aic_begriff="+iModul);
            }
            int iTab=g.TabTabellenname.getAic(sTab);
            int iFremd=(sTab.equals("EIGENSCHAFT") ? g.TabEigenschaften : sTab.equals("STAMMTYP") ? g.TabStammtypen :sTab.equals("BEWEGUNGSTYP") ? g.TabErfassungstypen
                        :sTab.equals("SPRACHE") ? TabSprache2:sTab.equals("HAUPTSCHIRM") ? TabHS:null).getAic(TabModul_Zuordnung2.getS("Kennung"));
            if (iModul>0 && iTab>0 && iFremd>0)
            {
              Qry.add("aic_begriff", iModul);
              Qry.add("aic_tabellenname", iTab);
              Qry.add("aic_fremd", iFremd);
              Qry.insert("Modul", false);
            }
          }
        }
        g.TabBegriffe.sAIC="AIC";
	/*if(TabVersionsupdate!=null)
	{
		TabVersionsupdate.moveFirst();
		boolean bNewVersion=true;
		if(Qry.open("select aic_version,kennung,erstellt from version where kennung='"+TabVersionsupdate.getS("Version")+"'")&&!Qry.eof())
		{
			for(;!Qry.eof();Qry.moveNext())
			{
				bNewVersion = !Qry.getS("kennung").equals(TabVersionsupdate.getS("Version"))||Qry.getD("erstellt").getTime()<TabVersionsupdate.getDate("Erstellt").getTime();
				if(bNewVersion)
				{
					SQL.exec(g,"delete from versionsupdate where aic_version="+Qry.getI("aic_version"));
					SQL.exec(g,"delete from version where aic_version="+Qry.getI("aic_version"));
				}
			}
			Qry.close();
		}
		if(bNewVersion)
		{
			Qry.add("Kennung",TabVersionsupdate.getS("Version"));
			Qry.add("Erstellt",TabVersionsupdate.getDate("Erstellt"));
			Qry.add("Exportiert",TabVersionsupdate.getDate("Exportiert"));
			int iAIC_Version=Qry.insert("Version",true);

			for(;!TabVersionsupdate.eof();TabVersionsupdate.moveNext())
			{
				int iAIC_Fremd=0;
				if(TabVersionsupdate.getS("Tabelle").equals("BEGRIFF"))
					iAIC_Fremd=g.getBegriffS(TabVersionsupdate.getS("Gruppe"),TabVersionsupdate.getS("Kennung"));
				else if(TabVersionsupdate.getS("Tabelle").equals("STAMMTYP"))
					iAIC_Fremd=g.TabStammtypen.posInhalt("Kennung",TabVersionsupdate.getS("Kennung"))?g.TabStammtypen.getI("AIC"):0;
				else if(TabVersionsupdate.getS("Tabelle").equals("EIGENSCHAFT"))
					iAIC_Fremd=g.TabEigenschaften.posInhalt("Kennung",TabVersionsupdate.getS("Kennung"))?g.TabEigenschaften.getI("AIC"):0;
				int iAIC_Tabellenname = g.TabTabellenname.posInhalt("Kennung",TabVersionsupdate.getS("Tabelle"))?g.TabTabellenname.getI("AIC"):0;
				//g.progInfo("Fremd="+iAIC_Fremd+", Tab="+iAIC_Tabellenname+", Version="+iAIC_Version);
				if(iAIC_Fremd>0&&iAIC_Tabellenname>0&&iAIC_Version>0)
				{
					Qry.add("AIC_Fremd",iAIC_Fremd);
					Qry.add("AIC_Tabellenname",iAIC_Tabellenname);
					Qry.add("AIC_Version",iAIC_Version);
                                        Qry.add("Bits",TabVersionsupdate.getI("Bits"));
					Qry.insert("Versionsupdate",false);
				}
				else
                                {
                                  iBits|=8;
                                  Static.printError("DefImport: in Versionsupdate fehlt:"+
                                    (TabVersionsupdate.getS("Tabelle").equals("BEGRIFF") ? TabVersionsupdate.getInhalt("Gruppe"):TabVersionsupdate.getS("Tabelle"))+
                                    "/"+TabVersionsupdate.getS("Kennung")+" ("+iAIC_Tabellenname+"/"+iAIC_Fremd+")");
                                }
			}
		}
	}
       */
      
        if (bMC)
        {
          Vector Vec=SQL.getVector("select distinct aic_begriff from begriff join begriffgruppe g on g.aic_begriffgruppe=begriff.aic_begriffgruppe where g.kennung='Modul'"+(VecModule==null?"":" and aic_begriff not"+Static.SQL_in(VecModule)),g);
          g.exec("delete from begriff_zuordnung where aic_begriff"+Static.SQL_in(Vec));
          g.exec("delete from modul where aic_begriff"+Static.SQL_in(Vec));
          g.exec("delete from defverlauf where aic_begriff"+Static.SQL_in(Vec));
          g.exec("delete from begriff where aic_begriff"+Static.SQL_in(Vec));
        }
        if (!g.ApplPort() && bNormal)
        {
          g.fixInfo(Reinigung.DelJavaFX(g,false));
          Reinigung.DelHS(g, false);
          Reinigung.DelAbfrage(g, false);
          Reinigung.DelFrame(g, false);
          // Reinigung.DelModell(g, false); // am 23.8.2020 nach Besprechung entfernt
          Reinigung.DelRolle(g, false);
          Reinigung.ClearZone(g);
        }
	g.clockInfo("Module",lClock);
        //Para.setParameter("Update","",Reinigung.bFehler?-1:0,0,0,0,false,false);
        g.exec("Update parameter set "+g.int1()+"="+(iStatusOld>0 ? iStatusOld:Reinigung.bFehler?-1:0)+" where aic_parameter=" + iPU);
        g.printExec("*Fertig!!!!");
        if (Gau != null)
          Gau.setText("Fertig",8);
	//setCursor(Cursor.getDefaultCursor());
        Qry.addnow("ENDE");
        int iFehler=TxtProt==null ? Static.getError()-iFehlerStart:TxtProt.Edt.getLineCount()-iFehlerText;
        if (iFehler>0)
        	g.fixInfo(iFehler+" Fehler bei DefImport aufgetreten!");
        if (iFehler>255)
          iFehler=255;
        Qry.add("Status",iBits+32+iFehler*256);
        Qry.update("DefImport",g.iDefImport);
        g.iDefImport=0;
        iAnzahl++;
        if (bShow)
          g.rollback();
        else
        {
          g.commit();
		  Reinigung.DSP2_Check(g, "");
          if (bClean)
            Reinigung.DelOld(g,false,null);
          /*if (bSR)
          {
            String sR2=Reinigung.StammCheck(g, false, 0);
            if (!sR2.equals("") && TxtProt!= null)
              TxtProt.setText(TxtProt.getValue() + "\n\n" + sR2);
          }*/
        }
        Qry.free();
        if (Static.cache())
          Static.clearCache();
        //Reinigung.FremdtabellenLoeschen(g, null,null); // mehr Zeit bis zum ausloggen
        g.Refresh();
        g.clockInfo("Refresh",lClock);
        if (!bkeinANR && TabBewegungstyp != null && !TabBewegungstyp.isEmpty())
        {
          Gauge Gau2 = FomDefImport==null ? null:new Gauge("ANR prüfen", 0, TabBewegungstyp.getAnzahlElemente(null), "", g, false);
          for(TabBewegungstyp.moveFirst(); !TabBewegungstyp.out(); TabBewegungstyp.moveNext())
          {
            //Reinigung.ANR_Check(g, sR, true);
            String sKennung=TabBewegungstyp.getS("Kennung");
            /*boolean b=TabBew==null;
            if (TabBew !=null && TabBew.posInhalt("Bew",sKennung) && !TabBew.getB("ANR"))
            {
              TabBew.setInhalt("ANR",true);
              b=true;
            }*/
            if (TabBewegungstyp.getB("ANR"))
            {
              g.fixtestInfo("prüfe ANR von "+sKennung);
              if (Gau2 != null)
                Gau2.setText(sKennung, TabBewegungstyp.getPos());
              String sANR = Reinigung.ANR_Check(g, "", TabBewegungstyp.getI("AIC"), true, !bANR);
              if (!sANR.equals(""))
              {
                g.fixInfo(sANR);
                if (TxtProt!=null)
                  TxtProt.setText(TxtProt.getValue() + "\n" + sANR);
              }
            }
          }
          if (Gau2 != null)
            Gau2.setText("fertig",TabBewegungstyp.getAnzahlElemente(null));
          if (TabBewSet != null && TabBewSet.size()>0)
          {
            Gau2 = FomDefImport==null ? null:new Gauge("Bew-Indexe prüfen", 0, TabBewSet.size(), "", g, false);
            for(TabBewSet.moveFirst(); !TabBewSet.out(); TabBewSet.moveNext())
            {
              String sArt=TabBewSet.getS("Art");
              if (sArt.equals("LDate"))
                Reinigung.setLDate(TabBewSet.getI("Nr"), g, TabBewSet.getI("Bew"), TabBewSet.getI("Eig"));
              else if (sArt.equals("BOOL"))
                Reinigung.setBOOL(TabBewSet.getI("Nr"), g, TabBewSet.getI("Bew"), TabBewSet.getI("Eig"));
              else if (sArt.equals("VB"))
            	Reinigung.setLDateVB(g, TabBewSet.getI("Bew"), TabBewSet.getI("Eig"));
              else
                Static.printError("Index für Art "+sArt+" nicht möglich (Bew="+TabBewSet.getI("Bew")+")");
            }
            if (Gau2 != null)
            	Gau2.setText("fertig",TabBewSet.size());
          }
        }
        //if (CbxView2.isSelected())
        //  createView2();
        if (iFehler==0)
          g.fixInfo("Import mit "+sFile1+" fertig");
        else
          Static.printError(sFile1+": Import enhält "+iFehler+" Fehler",false);
        if (FomDefImport!=null && TabStammImp==null) // Meldung nur bei einzelimport
          new Message(Message.INFORMATION_MESSAGE,FomDefImport,g,30).showDialog(iFehler==0/*TxtProt.getValue().equals("")*/ ? "Import fertig":"Import fehlerhaft");
        
	g.printExec("***DefImport: "+sFile1+" - Ende");
        if(bInfo && FomDefImport!=null && FrmOption != null)
          TabInfo.showGrid("Info",FrmOption);

        if (g.getLog()>0)
          g.Logout(false);
        else
          g.unConnect();
        return true;
}

private static int getEig(Global g,String s)
{
  if (s.equals(Static.sLeer))
    return 0;
  int iEig=g.TabEigenschaften.getAic(s.toUpperCase());
  if (iEig==0)
    iEig=SQL.getInteger(g,"select aic_Eigenschaft from eigenschaft where kennung='"+s+"'");
  if (iEig==0)
  {
    SQL Qry = new SQL(g);
    Qry.add("kennung", s);
    Qry.add("aic_begriff", g.getBegriffAicS("Datentyp","SttReserve"));
    iEig = Qry.insert("Eigenschaft", true);
    Qry.free();
    g.fixInfo("Eigenschaft "+s+" vorzeitig angelegt");
  }
  return iEig;
}

private static void addTab(Tabellenspeicher Tab,String sArt,int i,int iBew,int iEig)
{
  Tab.addInhalt("Art",sArt);
  Tab.addInhalt("Nr",i);
  Tab.addInhalt("Bew",iBew);
  Tab.addInhalt("Eig",iEig);
}

private static void printError(String s)
{
  if (TxtProt != null)
    TxtProt.setText(TxtProt.getValue()+"\n"+s);
  Static.printError(s);
}

private static Tabellenspeicher ReadBSEH(Global g,AUZip Zip,String[] sSpalten,char[] cDT)
{
	Tabellenspeicher Tab = new Tabellenspeicher(g,sSpalten);
	String sKennung="";
	for(sKennung=Zip.ReadString();sKennung!=null;sKennung=Zip.ReadString())
	{
		Tab.addInhalt(sSpalten[0],sKennung);
		for(int i=1;i<sSpalten.length;i++)
		{
			if(cDT[i]=='S')
				Tab.addInhalt(sSpalten[i],Zip.ReadString());
			else if(cDT[i]=='B')
				Tab.addInhalt(sSpalten[i],Zip.ReadBoolean());
			else if(cDT[i]=='I')
				Tab.addInhalt(sSpalten[i],Zip.ReadInteger());
			else if(cDT[i]=='N')
				Tab.addInhalt(sSpalten[i],null);
			else
				printError("DefImport.ReadBSEH(): Fehler in ReadBSEH()");
		}
	}
	return Tab;
}

private static Tabellenspeicher ReadZuordnung(Global g,AUZip Zip,boolean bReihenfolge,boolean bBegriffgruppe,boolean bCodegruppe)
{
	String[] s = bReihenfolge?bBegriffgruppe?bCodegruppe?new String[]{"Kennung1","Kennung2","Reihenfolge","Begriffgruppe","Codegruppe"}:new String[]{"Kennung1","Kennung2","Reihenfolge","Begriffgruppe"}:new String[]{"Kennung1","Kennung2","Reihenfolge"}:bBegriffgruppe?bCodegruppe?new String[]{"Kennung1","Kennung2","Begriffgruppe","Codegruppe"}:new String[]{"Kennung1","Kennung2","Begriffgruppe"}:new String[]{"Kennung1","Kennung2"};
	Tabellenspeicher Tab = new Tabellenspeicher(g,s);

	String sKennung1="";
	for(String sKennung2=Zip.ReadString();sKennung2!=null;sKennung2=Zip.ReadString())
	{
		if(sKennung2.equals("*!*"))
		{
			sKennung1=Zip.ReadString();
			sKennung2=Zip.ReadString();
		}

		Tab.addInhalt("Kennung1",sKennung1);
		Tab.addInhalt("Kennung2",sKennung2);
		if(bBegriffgruppe)
			Tab.addInhalt("Begriffgruppe",Zip.ReadString());
		if(bCodegruppe)
			Tab.addInhalt("Codegruppe",Zip.ReadString());
		if(bReihenfolge)
			Tab.addInhalt("Reihenfolge",Zip.ReadInteger());
	}

	return Tab;
}

private static void SaveFormular(Global g,Tabellenspeicher TabFormulare,String sFormular,int iAIC_Begriff,Tabellenspeicher TabForm2FXML,boolean bDelete)
{
	g.printExec("*SaveFormular: "+sFormular);
	SQL Qry = new SQL(g);
	int iAIC_Formular=bDelete?Qry.getInteger("SELECT AIC_Formular FROM Formular WHERE AIC_Begriff="+iAIC_Begriff):0;
	if(iAIC_Formular==0)
	{
		Qry.add("AIC_Begriff",iAIC_Begriff);
		iAIC_Formular=Qry.insert("Formular",true);
	}
	else
        {
          Qry.exec("update formular set aic_abfrage=null,aic_eigenschaft=null,aic_stamm=null,aic_modell=null WHERE AIC_Formular=" + iAIC_Formular);
          Qry.exec("DELETE FROM Darstellung WHERE AIC_Formular=" + iAIC_Formular);
        }
	JCOutliner OutFrame = TabFormulare.posInhalt("Name",sFormular)?(JCOutliner)TabFormulare.getInhalt("Outliner"):null;
	if (TabForm2FXML!=null && TabForm2FXML.posInhalt("Kennung2",sFormular) || OutFrame==null && TabBegriff.posInhalt((Object)"Frame",sFormular) && TabBegriff.getB("Web"))
	{
		// FXML und Template ohne Inhalt
		bOk=true;
	}
	else if(OutFrame!=null)
	{
		Save_Rekursion(g,OutFrame.getRootNode(),0,iAIC_Formular,true);
	}
	else //if(TabForm2FXML==null || !TabForm2FXML.posInhalt("Kennung2",sFormular))//TabBegriff.posInhalt((Object)"Frame",TabForm2FXML.getInhalt("Kennung2")))
		if (!TabBegriff.getB("web"))
	{
		bOk=false;
		printError("DefImport.SaveFormular(): Formular<"+sFormular+"> in SaveFormular() nicht gefunden!!!");
	}
	Qry.free();
	g.printExec("*Ende SaveFormular: "+sFormular);
}

private static void Save_Rekursion(Global g,JCOutlinerNode Node,int iAIC,int iAIC_H,boolean bFormular)
{
	if(iAIC==0)
		Save_Rekursion(g,Node,bFormular ? Save_DZ(g,Node,iAIC,iAIC_H):Save_AZ(g,Node,iAIC,iAIC_H),iAIC_H,bFormular);
	else
	{
		Vector VecChildren = Node.getChildren();

		for(int i=0; VecChildren!=null && VecChildren.size()>i;i++)
		{
			JCOutlinerNode OutChild = (JCOutlinerNode)VecChildren.elementAt(i);
			Save_Rekursion(g,OutChild,bFormular ? Save_DZ(g,OutChild,iAIC,iAIC_H):Save_AZ(g,OutChild,iAIC,iAIC_H),iAIC_H,bFormular);
		}
	}
}

private static int Save_DZ(Global g,JCOutlinerNode OutChild,int iAIC,int iAIC_Formular)
{
	SQL Qry = new SQL(g);
	Vector VecVisible = (Vector)OutChild.getLabel();
	int iAIC_Darstellung=0;

	if(iAIC==0 || TabBegriff.posInhalt(VecVisible.elementAt(1),VecVisible.elementAt(0)))
	{
		Qry.add("AIC_Formular",iAIC_Formular);
		if(iAIC!=0)
		{
			Qry.add("AIC_Begriff",TabBegriff.getI("AIC"));
			Qry.add("Dar_AIC_Darstellung",iAIC);
			Qry.add2("Reihenfolge",(String)VecVisible.elementAt(2));
		}
		if(VecVisible.size()>3)
			Qry.add2("x",(String)VecVisible.elementAt(3));
		if(VecVisible.size()>4)
			Qry.add2("y",(String)VecVisible.elementAt(4));
		if(VecVisible.size()>5)
			Qry.add2("w",(String)VecVisible.elementAt(5));
		if(VecVisible.size()>6)
			Qry.add2("h",(String)VecVisible.elementAt(6));
		if(VecVisible.size()>7 && VecVisible.elementAt(7)!=null)
		{
			String sAlign=(String)VecVisible.elementAt(7);
			int iAic=g.getBegriffAicS("AlignFX", sAlign);
			//g.fixtestInfo("Align "+sAlign+":"+iAic);
			Qry.add0("Align",iAic>0 ? iAic:0);
		}
		if(VecVisible.size()>8)
			Qry.add2("HGap",(String)VecVisible.elementAt(8));
		if(VecVisible.size()>9)
			Qry.add2("VGap",(String)VecVisible.elementAt(9));
		if(VecVisible.size()>10)
			Qry.add2("Pos",(String)VecVisible.elementAt(10));
		if(VecVisible.size()>11)
			Qry.add2("i1",(String)VecVisible.elementAt(11));
		if(VecVisible.size()>12)
			Qry.add2("i2",(String)VecVisible.elementAt(12));
		iAIC_Darstellung=Qry.insert("Darstellung",true);
	}
	else
	{
		bOk=false;
		printError("DefImport.Save_DZ(): Begriff<"+VecVisible.elementAt(0)+"/"+VecVisible.elementAt(1)+"> konnte nicht gefunden werden!!!");
	}

	Qry.free();
	return iAIC_Darstellung;
}

private static int Save_AZ(Global g,JCOutlinerNode OutChild,int iAIC,int iAIC_HS)
{
  SQL Qry = new SQL(g);
  Vector VecVisible = (Vector)OutChild.getLabel();
  Qry.add("AIC_Hauptschirm", iAIC_HS);
  Qry.add("int1",g.TabStammtypen.getAic(Sort.gets(VecVisible,1)));
  String sTyp=Sort.gets(VecVisible,2);
  int i2=sTyp.startsWith("S") ? g.TabStammtypen.getAic(sTyp.substring(1)):sTyp.startsWith("R") ? -g.TabRollen.getAic(sTyp.substring(1)):sTyp.startsWith("B") ? -g.TabErfassungstypen.getAic(sTyp.substring(1)):0;
  if (i2==0)
    printError("Hauptschirmansicht: "+(sTyp.startsWith("S")?"Stammtyp ":sTyp.startsWith("R") ? "Rolle ":"Bewegungstyp ")+sTyp.substring(1)+" nicht gefunden");
  else
    Qry.add("int2",i2);
  Qry.add("int3",g.TabEigenschaften.getAic(Sort.gets(VecVisible,3)));
  Qry.add("int4",Sort.geti(VecVisible,4));
  if (!Sort.gets(VecVisible,0).equals(""))
    Qry.add0("aic_abfrage",SQL.getInteger(g,"select aic_abfrage from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where Kennung='"+Sort.gets(VecVisible,0)+"'"));
  if(iAIC!=0)
    Qry.add("ans_AIC_ansicht",iAIC);
  int iNew_AIC = Qry.insert("ansicht", true);
  return iNew_AIC;
}


/*
private void SaveModell(String sModell,int iAIC_Begriff, boolean bDelete)
{
	g.printInfo("SaveModell: "+sModell);
	SQL Qry = new SQL(g);
	int iAIC_Modell=bDelete?Qry.getInteger("SELECT AIC_Modell FROM Modell WHERE AIC_Begriff="+iAIC_Begriff):0;
	JCOutliner OutMod = TabModelle.posInhalt("Name",sModell)?(JCOutliner)TabModelle.getInhalt("Outliner"):null;
	if(OutMod!=null)
	{
		String sPeriode = OutMod.getRootNode().getLabel()!=null && ((Vector)OutMod.getRootNode().getLabel()).size()>1?(String)((Vector)OutMod.getRootNode().getLabel()).elementAt(1):"";
		if(iAIC_Modell==0)
		{
			Qry.add("AIC_Begriff",iAIC_Begriff);
			if(!sPeriode.equals(""))
			{
				int iPeriode = Qry.getInteger("select aic_stamm from stamm where kennung='"+sPeriode+"'");
				if(iPeriode>0)
					Qry.add("AIC_Stamm",iPeriode);
			}
			Qry.add("AIC_Mandant",1); //Darf nicht so sein!!!
			iAIC_Modell=Qry.insert("Modell");
		}
		else
		{
			Qry.exec("DELETE FROM Befehl WHERE AIC_Modell="+iAIC_Modell);

			if(!sPeriode.equals(""))
			{
				int iPeriode = Qry.getInteger("select aic_stamm from stamm where kennung='"+sPeriode+"'");
				if(iPeriode>0)
				{
					Qry.add("AIC_Stamm",iPeriode);
					Qry.addWhere("AIC_Begriff",iAIC_Begriff);
					Qry.update("Modell");
				}
			}
		}

		//Modell_Rekursion((JCOutlinerFolderNode)OutModell.getRootNode(),0,iAIC_Modell,' ');
		TabModelle.posInhalt("Name",sModell);
		Modell_Rekursion((JCOutlinerFolderNode)((JCOutliner)TabModelle.getInhalt("Outliner")).getRootNode(),0,iAIC_Modell,' ');
	}
	else
		Static.printError("DefImport.SaveModell(): Modell<"+sModell+"> in SaveModell() nicht gefunden!!!");
	Qry.free();
	g.printInfo("Ende SaveModell: "+sModell);
}*/

private static boolean Modell_Rekursion(Global g,Tabellenspeicher TabSpalte,Tabellenspeicher TabBefehle,JCOutlinerFolderNode NodeParent,int iAIC_Befehl, int iAIC_Modell,char cArt)
{
	SQL Qry = new SQL(g);
	Vector VecChildren = NodeParent.getChildren();
        bOk=true;
	for(int i=0;VecChildren!= null && i<VecChildren.size();i++)
	{
		JCOutlinerFolderNode Node = (JCOutlinerFolderNode)VecChildren.elementAt(i);
		Vector VecVisible = (Vector)Node.getLabel();
                //g.testInfo("Modell_Rekursion: VecVisible="+VecVisible);
		/*
		String s = "VecVisible:"+VecVisible.size();
		for(int ii =0;ii<VecVisible.size();ii++)
			s+=","+VecVisible.elementAt(ii);
		g.printInfo(s);*/
		boolean bSchleife = VecVisible.elementAt(0)!=null && ((String)VecVisible.elementAt(0)).equals("Schleife");
		int iAIC_Code = VecVisible.elementAt(0) !=null ?g.getBegriffAicS(bSchleife?"Bedingung":(String)VecVisible.elementAt(0),(String)VecVisible.elementAt(1)):0;
		int iMod_AIC_Modell = VecVisible.elementAt(2) !=null?Qry.getInteger("select aic_modell from modell m join begriff b on m.aic_begriff=b.aic_begriff where kennung='"+VecVisible.elementAt(2)+"'"):0;
		boolean bHide = ((String)VecVisible.elementAt(5)).equals("T");
        String sEingabe=VecVisible.size()>7 ? Static.changeK2(Sort.gets(VecVisible,7)):"";
        String sVar=VecVisible.size()>8 ? Sort.gets(VecVisible,8):"";
        //g.fixtestError("Eingabe="+sEingabe);        
        int iMBits=VecVisible.size()>9 ? Sort.geti(VecVisible,9):0;
		int iAIC_Spalte = 0;
                //if (!bHide)
                {
                  if(VecVisible.elementAt(3) != null) {
                    TabSpalte.sGruppe = "abfrage";
                    TabSpalte.sAIC = "nummer";
                    if(TabSpalte.posInhalt(VecVisible.elementAt(4), new Integer((String)VecVisible.elementAt(3))))
                      iAIC_Spalte = TabSpalte.getI("new_aic_spalte");
                    if (iAIC_Spalte==0)
                    {
                      String sModell=SQL.getString(g,"select defbezeichnung from modell"+g.join("begriff","modell")+" where aic_modell="+iAIC_Modell);
                      printError("Modell_Rekursion: bei Modell <"+sModell+"> fehlt Abfrage <" + VecVisible.elementAt(4) +"/"+VecVisible.elementAt(3)+">");
                      bOk = false;
                    }
                  }

                  Qry.add("AIC_Modell", iAIC_Modell);
                  if(iMod_AIC_Modell > 0)
                    Qry.add("Mod_AIC_Modell", iMod_AIC_Modell);
                  if(iAIC_Code > 0)
                    Qry.add("AIC_Code", iAIC_Code);

                  if(iAIC_Spalte > 0)
                    Qry.add("AIC_Spalte", iAIC_Spalte);

                  if(iAIC_Befehl > 0 && cArt == 'N')
                    Qry.add("Bef2_AIC_Befehl", iAIC_Befehl);
                  else if(iAIC_Befehl > 0)
                    Qry.add("Bef_AIC_Befehl", iAIC_Befehl);

                  Qry.add("Hide", bHide);
                  if (!sEingabe.equals(""))
                    Qry.add("Eingabe",sEingabe);
                  if (!sVar.equals(""))
                      Qry.add("Var",sVar);
                  Qry.add("mbits", iMBits);

                  int iNew_AIC_Befehl = Qry.insert("Befehl", true);
                  TabBefehle.addInhalt("neu",iNew_AIC_Befehl);
                  TabBefehle.addInhalt("alt",Tabellenspeicher.geti(VecVisible.elementAt(6)));
                  bOk = bOk && iNew_AIC_Befehl>0;
                  if(VecVisible.elementAt(0) != null && ((String)VecVisible.elementAt(0)).equals("Bedingung") || bSchleife) {
                    Vector VecJaNein = Node.getChildren();
                    for(int j = 0; j < VecJaNein.size(); j++) {
                      JCOutlinerFolderNode NodeJaNein = (JCOutlinerFolderNode)VecJaNein.elementAt(j);
                      Vector VecV = (Vector)NodeJaNein.getLabel();
                      Modell_Rekursion(g,TabSpalte,TabBefehle,NodeJaNein, iNew_AIC_Befehl, iAIC_Modell, ((String)VecV.elementAt(0)).equals("Nein") ? 'N' : 'J');
                    }
                  }
                }
	}
	Qry.free();
        return bOk;
}

/*private void createView2()
{
  if (g.ASA())
    return;
  g.testInfo("createView2");
  int i=0;
  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,defbezeichnung from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+" and"+g.bit("bits",Abfrage.cstView2),true);
  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
  {
    g.fixInfo("Erzeuge View "+Tab.getS("defbezeichnung"));
    String sTab="AUV_"+Tab.getS("defbezeichnung").toUpperCase();
    String sExists="select * from "+(g.Oracle()?"user_views where view_name":g.MS()?"sysobjects where xtype='V' and name":
    	g.MySQL()?"information_schema.views where table_schema=DATABASE() and  table_name":"Viewtabelle")+"='";
    if (SQL.exists(g,sExists+sTab+"'"))
      g.exec("drop view "+sTab);
    ShowAbfrage A = new ShowAbfrage(g, Tab.getI("aic_begriff"), Abfrage.cstBegriff);
    A.setViewSQL();
    if (g.exec("create view "+sTab+" as "+A.sSQL)<-1)
      Static.printError("View "+Tab.getS("defbezeichnung")+" konnte nicht erzeugt werden!");
    else
      i++;
  }
  g.fixInfo(i+" von "+Tab.getAnzahlElemente(null)+" Views erstellt");
}*/
/*
		if(sArt.equals("If"))
		{
			Vector VecBed = Node.getChildren();

			Speichern_Rekursiv(iAIC_Modell,(JCOutlinerFolderNode)VecBed.elementAt(0),new Integer(iAIC));
			Speichern_Rekursiv(iAIC_Modell,(JCOutlinerFolderNode)VecBed.elementAt(1),new Integer(iAIC));
		}
		else if(sArt.equals("While"))
			Speichern_Rekursiv(iAIC_Modell,(JCOutlinerFolderNode)Node,new Integer(iAIC));
	}
	Qry.free();
}*/

private static void diskInfo(Global g,int i,String sText)
{
  if (i>0)
     g.diskInfo(i+sText);
}

/*private void Stop(String sTitel,Tabellenspeicher Tab)
{
	JCOutliner Out = new JCOutliner();
	if(g.Debug())
		Tab.showGrid(Out);
	Dia=new JDialog(new JFrame(),true);
	Dia.setTitle(sTitel);
	JButton BtnWeiter = new JButton("Weiter");
	Dia.getContentPane().setLayout(new BorderLayout(2,2));
	Dia.getContentPane().add("Center",Out);
	Dia.getContentPane().add("South",BtnWeiter);
	Dia.setSize(800,800);

	BtnWeiter.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Dia.hide();
		}
	});

	Dia.show();
}*/

// add your data members here
private Global g;
//private JCOutliner OutTest = new JCOutliner();
/*private Tabellenspeicher TabFormulare;
private Tabellenspeicher TabModelle;
private Tabellenspeicher TabHauptschirm;
private Tabellenspeicher TabHauptschirme;
private Tabellenspeicher TabBedingung;*/
private static Tabellenspeicher TabBegriff;
/*private Tabellenspeicher TabBewegungstyp;
private Tabellenspeicher TabStammtyp;
private Tabellenspeicher TabRolle;
private Tabellenspeicher TabEigenschaft;
//private Tabellenspeicher TabHomepage;
private Tabellenspeicher TabPlanung;
private Tabellenspeicher TabFormAbf;
private Tabellenspeicher TabFormModell;
private Tabellenspeicher TabDruckModell;
private Tabellenspeicher TabSprache;
private Tabellenspeicher TabFarbe;
private Tabellenspeicher TabSchrift;
private Tabellenspeicher TabAbschnitt;
private Tabellenspeicher TabRaster;
private Tabellenspeicher TabSpalte;
private Tabellenspeicher TabFix_Spalte;
private Tabellenspeicher TabFix_Bedingung;
private Tabellenspeicher TabSpalte_Zuordnung;
private Tabellenspeicher TabSpalte_Berechnung;*/
private static java.util.Vector<Object> VecModule;
//private Tabellenspeicher TabNurBegriff;
//private Tabellenspeicher TabNurEigenschaft;
//private Tabellenspeicher TabNurStammtyp;
//private Tabellenspeicher TabVersionsupdate;

//private static JCOutliner OutFormular;
//private static JCOutliner OutModell;
//private static JCOutliner OutAbfrage;
//private JCOutliner OutDruck;

//private Tabellenspeicher TabBeg2Hp;
/*private Tabellenspeicher TabBeg2Bew;
private Tabellenspeicher TabBeg2Schrift;
private Tabellenspeicher TabBeg2Stt;
private Tabellenspeicher TabBeg2Rolle;
private Tabellenspeicher TabStt2Stt;
private Tabellenspeicher TabRol2Rol;
private Tabellenspeicher TabRol2Stt;
private Tabellenspeicher TabForm2Stamm;
private Tabellenspeicher TabDruck_Zuordnung;
private Tabellenspeicher TabBew_Zuordnung;
private Tabellenspeicher TabEig_Zuordnung;
private Tabellenspeicher TabApplikation_Zuordnung;
private Tabellenspeicher TabGruppe_Zuordnung;
private Tabellenspeicher TabKunde_Zuordnung;
private Tabellenspeicher TabModul_Zuordnung;
private Tabellenspeicher TabModul_Zuordnung2;
private Tabellenspeicher TabEig2Stt;
private Tabellenspeicher TabEig2Bew;
private Tabellenspeicher TabEig2Rolle;
private Tabellenspeicher TabEig2Eig;
private Tabellenspeicher TabStt2Eig;
private Tabellenspeicher TabForm2Eig;
private Tabellenspeicher TabStt_Zuordnung;
private Tabellenspeicher TabRol_Zuordnung;
private Tabellenspeicher TabSchrift2Farbe;
private Tabellenspeicher TabSchrift2Farbe2;
private Tabellenspeicher TabAbf2Mod;
private Tabellenspeicher TabBeg2Code;
private Tabellenspeicher TabEig2Stamm;
private Tabellenspeicher TabBeg_AIC_Beg_Zuordnung;
private Tabellenspeicher TabAppl2_Zuordnung;
private Tabellenspeicher TabBeg2AIC_Beg_Zuordnung;
private Tabellenspeicher TabBeg3AIC_Beg_Zuordnung;
//private Tabellenspeicher TabAIC_Beg_Zuordnung;

private Tabellenspeicher TabBezeichnung;
private Tabellenspeicher TabDaten_Bild;
private Tabellenspeicher TabDaten_Memo;
private Tabellenspeicher TabTooltip;
private Tabellenspeicher TabAbschlusstyp;
private Tabellenspeicher TabAbschlussdef;
private Tabellenspeicher TabBefehle;*/

private static AUTextArea TxtProt=null;
private FileEingabe EdtPfad;
private static boolean bBezeichnung=true;
private static boolean bMemo=true;
private static boolean bBild=true;
private static boolean bTooltip=true;
private static boolean bFormulare=false;
private static boolean bAbfragen=false;
private static boolean bModelle=false;
private static boolean bInfo=false;
private static boolean bANR=false;
//private JCheckBox CbxView2;
private static boolean bNurZeigen=false;
private static boolean bkeinANR=false;
private static boolean bDelAll=false;
private static boolean bSR=false;
private static boolean bKeineStamm=false;
private static boolean bCleanTM=false;
private static boolean bStatus0=false;
//private JCheckBox CbxEigenschaft;
//private JCheckBox CbxStammtyp;
//private JCheckBox CbxBewegungstyp;
private JButton BtnOk=null;

//private Parameter Para;
private static int iPU=0;
private static boolean bOk=true;
private static DefImport This=null;
//private int iBits;
//private boolean bNormal;
//private boolean bClean;
//private boolean bMC;
//private boolean bSpezial=false;
private static JDialog FrmOption=null;
private boolean bTest2;
private static Tabellenspeicher TabStammImp=null;
//private static Tabellenspeicher TabBew=null;
public static String sZInfo=null;
public static int iAnzahl=0;
private static int iStatusOld=0;

}

