/*
    All_Unlimited-Grunddefinition-Reinigung.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Cursor;
//import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import All_Unlimited.Allgemein.BildUpdate;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Formular;
//import All_Unlimited.Allgemein.FormularFX;
import All_Unlimited.Allgemein.Gauge;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.Aufruf;
import All_Unlimited.Hauptmaske.Import;
import All_Unlimited.Hauptmaske.AClient;
//import All_Unlimited.Allgemein.Eingabe.AUCheckBox;

public class Reinigung extends All_Unlimited.Allgemein.Formular
{
	private Global g;
	private JTextArea Memo = new JTextArea();
	private JButton BtnFremd;
	private JButton BtnEig;
    private JButton BtnCleanDate;
	private JButton BtnLogging;
	private JButton BtnBeenden;
	private JButton BtnProtokoll;
	private JButton BtnBilder;
	private JButton BtnSpalten;
	private JButton BtnStamm;
	private JButton BtnDoppelte;
    private JButton BtnANR;
	private JButton BtnView;
    private JButton BtnIndex;
    private JButton BtnMandant;
	private JButton BtnAlles;
	private JButton BtnAlleBilder=null;
	private JButton BtnZone=null;
	private JButton BtnJavaFX=null;
        //private JButton BtnAFM_Del;
        private JButton BtnFormular;
        private JButton BtnModell;
        private JButton BtnAlt;
        private JButton BtnCheckAll;
        private JButton BtnCheckAlt;
        private JButton BtnClearCache;
        private JButton BtnTimerDeaktivieren;
        private JButton BtnTimerAktivieren;
        private JButton BtnTimerSchliessen;
        private JButton BtnAbfTest;
        //private JButton BtnTerminalStatus;
        public static boolean bFehler=false;
//        private JDialog DlgIndex=null;
        private static int iPU=0;
        private static Reinigung Self;

        public static Reinigung get(Global rg)
        {
          if (Self==null)
            Self=new Reinigung(rg);
          else
          {
            Self.show();
            Self.checkStatus();
          }
          return Self;
        }

        public static void free()
        {
           if (Self != null)
           {
             Self.g.winInfo("Reinigung.free");
             Self.thisFrame.dispose();
             Self = null;
           }
        }

	private Reinigung(Global rg)
	{
		super("Reinigung",null,rg);
		g = rg;
		g.winInfo("Reinigung.create");
		Aufbau();

                ActionListener AL=new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    JButton Btn=(JButton)e.getSource();
                    if (Btn==BtnLogging)
                    {
                      SQL.open_cursors("Reinigung",g);
                      if (Ausgeloggt(g,(JFrame)thisFrame,1))
                        new Message(Message.INFORMATION_MESSAGE,null,g).showDialog("allein eingeloggt");
                    }
                    else if (Btn==BtnProtokoll)
                    {
                      thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                      ProtokollCheck(g,null,20000,BtnProtokoll,Memo);
                      thisFrame.setCursor(Cursor.getDefaultCursor());
                    }
                    else if (Btn==BtnANR)
                      ANR_Check(true);
                    else if (Btn==BtnAbfTest)
                      Versionsupdate.Abfragen_testen(g,thisJFrame());
                    else if (Btn==BtnCheckAlt || Btn==BtnCheckAll || Btn==BtnClearCache || Btn==BtnEig)
                    {
                      thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                      if (Btn==BtnCheckAlt)
                        addMemo(Memo,OldCheck(g));
                      else if (Btn==BtnCheckAll)
                        addMemo(Memo,CheckAll());
                      else if (Btn==BtnClearCache)
                      {
                        if (g.ASA())
                          {
                            g.exec("call sa_flush_cache()");
                            addMemo(Memo,"\nSybase-Datenbank-Cache gelöscht\n");
                          }
                          if (Static.cache())
                            addMemo(Memo,"\n"+Static.clearCache()+" Dateien aus Cache gelöscht\n");
                      }
                      else  if (Btn==BtnEig)
                      {
                        long lClock = Static.get_ms();
                        CleanEig.get(g,0).setVisible(true);
                        g.clockInfo("CleanEig",lClock);
                      }
                      thisFrame.setCursor(Cursor.getDefaultCursor());
                    }
                    else if (Btn==BtnTimerDeaktivieren || Btn==BtnTimerSchliessen || Btn==BtnTimerAktivieren)
                    {
                      if (Btn==BtnTimerDeaktivieren && Prozesse.checkDeaktiv(g,false,true,thisJFrame()))
                      {                	  
                    		  g.SaveVerlauf("Timer deaktivieren");
                    		  g.exec("Update parameter set " + g.int1() + "=3 where aic_parameter=" + iPU);
                      }
                      else if (Btn==BtnTimerSchliessen && new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Timer_Schliessen")== Message.YES_OPTION)
                      {
                    	  g.SaveVerlauf("Timer schliessen");
                        g.exec("Update parameter set " + g.int1() + "=4 where aic_parameter=" + iPU);
                        AClient.send_AServer("x",g);
                        AClient.setNull();
                      }
                      else if (Btn==BtnTimerAktivieren)
                      {
                    	  g.SaveVerlauf("Timer aktivieren");
                        g.exec("Update parameter set " + g.int1() + "=0 where aic_parameter=" + iPU);
                        AClient.send_AServer("aktiv",g);
                      }
                      //checkStatus();
                    }
                    else if (Btn==BtnCleanDate)
                      new CleanDate(g);
                    else if (Btn==BtnBeenden)
                      hide();
                    else if (Btn==BtnDoppelte)
                      DoppelteAnzeigen();
                    else if (Btn==BtnAlleBilder)
                    {
                      Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_daten,filename,"+(g.MS() ?"data":"")+"length(bild2) size_Bild,"+(g.MS() ?"data":"")+"length(mini) size_Mini from daten_bild2",true);
                      if (Tab.size()>0)
                    	  Tab.showGrid("Bild2", thisJFrame());
                      Tab=new Tabellenspeicher(g,"select aic_daten,filename,"+(g.MS() ?"data":"")+"length(doku) size_Bild from daten_doku where filename not like '%.pdf'",true);
                      if (Tab.size()>0)
                    	  Tab.showGrid("Doku", thisJFrame());
                    }
                    else if (Btn==BtnZone)
                    {                   	
                    	addMemo(Memo,ClearZone(g));
                    	
                    	String sWhere=" where aic_bewegungstyp="+g.TabErfassungstypen.getAic("STEMPELUNG")+" and zone<>120 and pro_aic_protokoll is null and gueltig<"+
                  			  g.DateTimeToString(new DateWOD(2021,10,31).toDate())+"and gueltig>"+g.DateTimeToString(new DateWOD(2021,4,1).toDate());
                    	Tabellenspeicher Tab=new Tabellenspeicher(g,"select ANR,(select bezeichnung from stammview where aic_stamm=bew_pool.ANR and aic_rolle is null) Mitarbeiter,Gueltig,Zone,"+
                    			  "(select timestamp from protokoll where aic_protokoll=bew_pool.aic_protokoll) erfasst,AIC_BEW_POOL from bew_pool"+sWhere,true);
                    	if (Tab.size()==0)
                    		new Message(Message.INFORMATION_MESSAGE,thisJFrame(),g,10).showDialog("Zonen_ok");
                    	else if (new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,thisJFrame(),g,0).showDialog("clearZone",Tab)== Message.YES_OPTION)
                    	{
                    		//g.fixtestError(Tab.size()+" Zonen rücksetzen");
//                    		int iAnz=g.exec("update bew_pool set gueltig=null"+sWhere);
                    		addMemo(Memo,execInfo2("update bew_pool set gueltig=null,LDATE=0,LDATE3=0"+sWhere,"Gültigkeit mit Zone",g,"",true));
                    	}
                    }
                    else if (Btn==BtnJavaFX)
                    	addMemo(Memo,DelJavaFX(g,true));
                    else if ((Btn!=BtnStamm || new Message(Message.YES_NO_OPTION,null,g).showDialog("Langreinigung")==Message.YES_OPTION) && Ausgeloggt(g,(JFrame)thisFrame,1))
                    {
                      thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                      g.SaveVerlauf(Btn==BtnFremd ? "Fremdtabellen":Btn==BtnBilder ? "Bilder":Btn==BtnSpalten ? "Spalten":Btn==BtnStamm ? "Stamm":Btn==BtnAlt ?"altes":Btn==BtnAlles ? "Alles":
                    	  Btn==BtnView ? "View":Btn==BtnANR ? "ANR":Btn==BtnIndex ? "Index":Btn==BtnMandant ? "Mandant":Btn==BtnFormular ? "Formular":Btn==BtnModell ? "Modelle":"xx");
                      
                      if (Btn==BtnFremd)
                        FremdtabellenLoeschen(g,BtnFremd,Memo);
                      else if (Btn==BtnBilder)
                      {
                    	  NichtExistenteLoeschen(g,Memo,30);
                        addMemo(Memo,BildCheck(g,true));
                      }
                      else if (Btn==BtnSpalten)
                      {
                    	  
                        addMemo(Memo,SpaltenCheck(g,true));
                      }
                      else if (Btn==BtnStamm)
                        addMemo(Memo,StammCheck(g,true,getBegriff()));
                      else if (Btn==BtnView)
                        ViewRebuild();
                      else if (Btn==BtnIndex)
                        IndexRebuild(g,BtnIndex,Memo,thisJFrame());
                      else if (Btn==BtnMandant)
                        MandantenEntfernen();
                      else if (Btn==BtnFormular)
                        addMemo(Memo,FormularCheck(g,true,thisJFrame()));
                      else if (Btn==BtnModell)
                        addMemo(Memo,ModellCheck(g,true,thisJFrame()));
                      else if (Btn==BtnAlt)
                        addMemo(Memo,DelOld(g,true,thisJFrame()));
                      else if (Btn==BtnAlles)
                      {
                        Gauge Gau=new Gauge("Alles reinigen",0,6,"Views erneuern",g,false);
                        ViewRebuild();
                        Gau.setText("Spalten reinigen",1);
                        addMemo(Memo,SpaltenCheck(g,true));
                        Gau.setText("Stamm reinigen",2);
                        addMemo(Memo,StammCheck(g,true,getBegriff()));
                        Gau.setText("Protokoll reinigen",3);
                        ProtokollCheck(g,null,20000,BtnProtokoll,Memo);
                        Gau.setText("Bilder reinigen",4);
                        addMemo(Memo,BildCheck(g,true));
                        Gau.setText("Fremdtabellen reinigen",5);
                        FremdtabellenLoeschen(g,BtnFremd,Memo);
                        Gau.setText("fertig",6);
                      }
                      thisFrame.setCursor(Cursor.getDefaultCursor());
                    }
                    else if (Btn==BtnStamm || Btn==BtnSpalten)
                      addMemo(Memo,ImmerCheck(g));// DSP_Check(g,"doppelte Stammpools reinigen:\n",true,true));                 
                    checkStatus();
                  }
                };

		BtnLogging.addActionListener(AL);
		BtnFremd.addActionListener(AL);
		BtnProtokoll.addActionListener(AL);
		if (BtnAlleBilder!=null)
			BtnAlleBilder.addActionListener(AL);
		if (BtnZone!=null)
			BtnZone.addActionListener(AL);
		if (BtnJavaFX!=null)
			BtnJavaFX.addActionListener(AL);
		BtnBilder.addActionListener(AL);
		BtnSpalten.addActionListener(AL);
		BtnStamm.addActionListener(AL);
		BtnDoppelte.addActionListener(AL);
    BtnANR.addActionListener(AL);
    BtnView.addActionListener(AL);
    BtnIndex.addActionListener(AL);
    BtnMandant.addActionListener(AL);
    BtnFormular.addActionListener(AL);
    BtnModell.addActionListener(AL);
    BtnAlt.addActionListener(AL);
    BtnCheckAlt.addActionListener(AL);
    BtnCheckAll.addActionListener(AL);
    BtnClearCache.addActionListener(AL);
    BtnTimerDeaktivieren.addActionListener(AL);
    BtnTimerSchliessen.addActionListener(AL);
    BtnTimerAktivieren.addActionListener(AL);
		BtnAlles.addActionListener(AL);
		BtnEig.addActionListener(AL);
    BtnCleanDate=getFormularButton("CleanDate");
    BtnCleanDate.addActionListener(AL);
    if (BtnAbfTest!=null)
      BtnAbfTest.addActionListener(AL);
		BtnBeenden.addActionListener(AL);
	}

  public static Tabellenspeicher Eingeloggt(Global g,int iMax)
  {
    String s="select computer.kennung Computer,(select kennung from mandant where aic_mandant=logging.aic_mandant) Mandant,benutzer.kennung Benutzer"+
              ",(select kennung from code where aic_code=logging.aic_code) Anlage,ein seit"+g.bei("logging.status",Transact.WEBLOG,"web")+" from computer"+
              g.join2("logging","computer")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer where aus is null"+(iMax>1 ? " and logging.aic_code is null and logging.aic_logging<>"+g.getLog()+" and"+g.bitis("status",Transact.WEBLOG, 0):"")+" order by ein";
    return new Tabellenspeicher(g,s,true);
  }

  public static boolean Ausgeloggt(Global g,Window Frm,int iMax) // iMax=2 bei DefExport -> web nicht prüfen
  {
    if (iMax==0)
    {
      if (SQL.getInteger(g,"select count(*) from logging where aus is null and aic_code is not null")>0)
        if(Prozesse.checkDeaktiv(g,false,true,null))//new Message(Message.YES_NO_OPTION,null,g).showDialog("Timer_Deaktivieren")== Message.YES_OPTION)
        {
            g.exec("Update parameter set " + g.int1() + "=3 where aic_parameter=" + g.getPU());
            Static.sleep(10000);
        }
    }

          // String s="select computer.kennung Computer,(select kennung from mandant where aic_mandant=logging.aic_mandant) Mandant,benutzer.kennung Benutzer"+
          //     ",(select kennung from code where aic_code=logging.aic_code) Anlage,ein seit"+g.bei("logging.status",Transact.WEBLOG,"web")+" from computer"+
          //     g.join2("logging","computer")+" join benutzer on benutzer.aic_benutzer=logging.aic_benutzer where aus is null"+(iMax>1 ? " and logging.aic_code is null and logging.aic_logging<>"+g.getLog()+" and"+g.bitis("status",Transact.WEBLOG, 0):"")+" order by ein";
          // Tabellenspeicher Tab=new Tabellenspeicher(g,s,true);
          Tabellenspeicher Tab=Eingeloggt(g,iMax);
          int iLog=Tab.size();//Tab.getAnzahlElemente(null);//SQL.getInteger(g,"select count(*) from logging where aus is null");
          //int iLog2=g.ASA() || iMax>1 ? 0:SQL.getInteger(g,"select count(*) from bereich where connid<>"+g.Sid())+iMax;
//          g.fixtestError("Ausgeloggt "+iLog+"/"+iMax);
          if (iMax<2 && /*Math.max(iLog,iLog2)*/iLog>iMax && (Frm instanceof JDialog ? new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,(JDialog)Frm,g,0):
                            new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,/*Frm==null?new JFrame():*/(JFrame)Frm,g)).showDialog("checkConnects",Tab)== Message.YES_OPTION)
          {
            g.checkConnects(iLog<=iMax);
            iLog=SQL.getInteger(g,"select count(*) from logging where aus is null");
          }
          else if (iMax<2 && iLog==iMax)
        	  g.checkConnects(true);
          if (iMax>1)
            iMax=0;
          if (iLog>iMax)
            if (Frm instanceof JDialog)
              new Message(Message.INFORMATION_MESSAGE+Message.SHOW_TAB,(JDialog)Frm,g,0).showDialog("eingeloggt3",Tab);
            else
              new Message(Message.INFORMATION_MESSAGE+Message.SHOW_TAB,/*Frm==null?new JFrame():*/(JFrame)Frm,g).showDialog("eingeloggt3",Tab);
            //new Tabellenspeicher(g,s,true).showGrid("Es sind folgende eingeloggt:",Frm);
          return iLog<=iMax;
          //new Tabellenspeicher(g,"select logging.aic_logging Nr"+g.AU_Bezeichnung("Mandant","Logging")+" Mandant,Computer.kennung Computer"+
          //                     ",(select kennung from code where aic_code=logging.aic_code) Anlage"+
          //                     g.AU_Bezeichnung2("Benutzer","logging.aic_benutzer","Benutzer.Kennung")+" Benutzer,ein from Computer"+g.join2("logging","Computer")+
          //                     " join benutzer on benutzer.aic_benutzer=logging.aic_benutzer where aus is null order by ein",true).showGrid("Logging");

        }

	/*public static boolean LogCheck(Global g)
	{
		//g.checkConnects();
		int iLog=SQL.getInteger(g,"select count(*) from logging where aus is null");
		if (iLog>1)
			new Message(Message.WARNING_MESSAGE,null,g).showDialog("Eingeloggt",new String[] {""+(iLog-1)});

		return iLog==1;
	}*/

        /*private static int getPU(Global g)
        {
          if (iPU>0)
            return iPU;
          iPU=SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
          return iPU;
        }*/

	private void Aufbau()
	{
		BtnLogging = getButton("Logging");
		if(BtnLogging==null) BtnLogging = new JButton();
		BtnFremd = getButton("Fremdtabellen");
		if(BtnFremd==null) BtnFremd = new JButton();
		BtnProtokoll = getButton("Protokolle");
		if(BtnProtokoll==null) BtnProtokoll = new JButton();
		BtnBilder = getButton("Bilder");
		if(BtnBilder==null) BtnBilder = new JButton();
		BtnAlleBilder = getButton("AlleBilder");
		BtnZone = getButton("Zone");
		BtnJavaFX = getButton("JavaFX");
		if(BtnAlleBilder!=null && !g.Def())
			BtnAlleBilder.setVisible(false);
		BtnEig = getButton("CleanEig");
		if(BtnEig==null) BtnEig = new JButton();
		BtnSpalten = getButton("Spalten");
		if(BtnSpalten==null) BtnSpalten = new JButton();
		BtnAlles = getButton("Alles");
		if(BtnAlles==null) BtnAlles = new JButton();
		BtnStamm = getButton("Stamm");
		if(BtnStamm==null) BtnStamm = new JButton();
		BtnDoppelte = getButton("doppelte");
		if(BtnDoppelte==null) BtnDoppelte = new JButton();
        BtnANR = getButton("ANR");
		if(BtnANR==null) BtnANR = new JButton();
		BtnView = getButton("View");
		if(BtnView==null) BtnView = new JButton();
                BtnIndex = getButton("Index");
		if(BtnIndex==null) BtnIndex = new JButton();
                BtnMandant = getButton("Mandant");
		if(BtnMandant==null) BtnMandant = new JButton();
                BtnMandant.setEnabled(g.ASA());
                //BtnAFM_Del = getButton("AFM_Del");
                //if(BtnAFM_Del==null) BtnAFM_Del = new JButton();
                //BtnAFM_Del.setEnabled(g.Def());
		BtnBeenden = getButton("Beenden");
		if(BtnBeenden==null) BtnBeenden = new JButton();
                BtnFormular = getButton("Formular");
                if(BtnFormular==null) BtnFormular = new JButton();
                BtnModell = getButton("Modelle");
                if(BtnModell==null) BtnModell = new JButton();
                BtnAlt = getButton("altes");
                if(BtnAlt==null) BtnAlt = new JButton();
                //BtnAlt.setEnabled(g.ApplPort() || g.BasisPort());
                if (g.ApplPort())
                  BtnModell.setEnabled(false);
                BtnCheckAll = getButton("CheckAll");
                if(BtnCheckAll==null) BtnCheckAll = new JButton();
                BtnCheckAlt = getButton("Check altes");
                if(BtnCheckAlt==null) BtnCheckAlt = new JButton();
                BtnCheckAlt.setEnabled(g.Def());
                BtnAbfTest = getButton("Abfragen_testen");
                BtnClearCache = getButton("ClearCache");
                if(BtnClearCache==null) BtnClearCache = new JButton();
                BtnClearCache.setEnabled(Static.cache() || g.ASA());
                BtnTimerDeaktivieren = getButton("Timer deaktivieren");
                if(BtnTimerDeaktivieren==null) BtnTimerDeaktivieren = new JButton();
                BtnTimerAktivieren = getButton("Timer aktivieren");
                if(BtnTimerAktivieren==null) BtnTimerAktivieren = new JButton();
                BtnTimerSchliessen = getButton("Timer schliessen");
                if(BtnTimerSchliessen==null) BtnTimerSchliessen = new JButton();
                //BtnTerminalStatus = getButton("TerminalStatus");
                //if(BtnTerminalStatus==null) BtnTerminalStatus = new JButton();
                iPU=g.getPU();
                checkStatus();
		JPanel PnlTextfeld = getFrei("Textfeld");

		if(PnlTextfeld!=null)
		{
			PnlTextfeld.setLayout(new BorderLayout(2,2));
			PnlTextfeld.add(new javax.swing.JScrollPane(Memo));
		}

		show();
	}

        private void checkStatus()
        {
          g.progInfo("checkStatus bei "+iPU);
          int iStatus= iPU>0 ? SQL.getInteger(g,"select "+g.int1()+" from parameter where aic_parameter="+iPU):-2;
          setTitle(sFormularBezeichnung+": "+g.getShow("Status")+"= "+g.TabStatus.getBezeichnungS(iStatus)+" ("+iStatus+")");
          BtnTimerDeaktivieren.setEnabled(iStatus==0);
          BtnTimerAktivieren.setEnabled(iStatus==2 || iStatus==3 || iStatus==4 || iStatus==6);
          BtnTimerSchliessen.setEnabled(iStatus==0 || iStatus==3 || iStatus==6); // damit Timer schließen auch bei einzelausloggen möglich ist
        }

	private static void addMemo(JTextArea Memo,String s)
	{
		Memo.setText(Memo.getText()+s);
		Memo.repaint();
	}
	
	public static String ClearZone(Global g)
	{
		String s="";
		Vector<Integer> VecZone=SQL.getVector("select aic_bewegungstyp from bewegungstyp where "+g.bit("bewbits", g.cstBewZone), g);
    	if (VecZone.size()>0)
    		s=execInfo("update bew_pool set zone=null where zone is not null and aic_bewegungstyp not"+Static.SQL_in(VecZone),"Zonen",g,"",true);
    	return s;
	}
	
	public static String DelJavaFX(Global g,boolean bInfo)
	{
		String s=bInfo ? "JavaFX löschen:\n":"";
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select b.defbezeichnung,b.kennung,b.aic_begriff,f.aic_formular from begriff b join formular f on b.aic_begriff=f.aic_begriff where"+g.bit("bits", 0x10000000/*Formular.cstJavaFX*/),true);
		if (Tab.size()>0)
		{
			Vector<Integer> Vec=Tab.getVecSpalteI("aic_formular");
			s=execInfo("delete from darstellung where aic_formular"+Static.SQL_in(Vec),"Darstellungszeilen für JavaFX",g,s,true);
			s=execInfo("delete from formular where aic_formular"+Static.SQL_in(Vec),"Formulare für JavaFX",g,s,true);	
			Vec=Tab.getVecSpalteI("aic_begriff");
			s=execInfo("delete from defverlauf where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"DefVerlauf für JavaFX",g,s,true);
			s=execInfo("delete from fensterpos where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"Fensterpos für JavaFX",g,s,true);
			s=execInfo("delete from verlauf where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"Verlauf für JavaFX",g,s,true);
			s=execInfo("delete from fehler where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"Fehler für JavaFX",g,s,true);
			s=execInfo("delete from darstellung where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"JavaFX in anderen Formularen",g,s,true);
			s=execInfo("delete from begriff where aic_begriff"+Static.SQL_in(Vec),"Formulare-Begriffe für JavaFX",g,s,true);
			g.diskInfo("entfernte JavaFX-Formulare:"+Tab.getVecSpalte("Kennung"));
		}
		else
		{
			Tab=new Tabellenspeicher(g,"select defbezeichnung,kennung,aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame")+" and"+g.bit("bits", 0x10000000/*Formular.cstJavaFX*/),true);
			if (Tab.size()>0)
			{
				s=execInfo("delete from defverlauf where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"DefVerlauf für JavaFX",g,s,true);
				s=execInfo("delete from fensterpos where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"Fensterpos für JavaFX",g,s,true);
				s=execInfo("delete from verlauf where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"Verlauf für JavaFX",g,s,true);
				s=execInfo("delete from fehler where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"Fehler für JavaFX",g,s,true);
				s=execInfo("delete from darstellung where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"JavaFX in anderen Formularen",g,s,true);
				s=execInfo("delete from begriff where aic_begriff"+Static.SQL_in(Tab.getVecSpalteI("aic_begriff")),"Formulare-Begriffe für JavaFX",g,s,true);
			}
			else if (bInfo)
				s+="keine JavaFX-Formulare zum löschen\n";
		}
//		Tab.showGrid("JavaFX");
		return s;
	}
	
	private void DoppelteAnzeigen()
	{
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select (select bezeichnung from stammview2 s where s.aic_rolle is null and x.aic_stamm=s.aic_stamm) Bezeichnung,aic_stamm,(select kennung from rolle where x.aic_rolle=rolle.aic_Rolle) Rolle,aic_rolle "+
				"from (select aic_stamm,aic_rolle,count(*) Anzahl from stammview group by aic_stamm,aic_rolle) x where Anzahl>1",true);
		if (Tab.size()>0 && new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,thisJFrame(),g).showDialog("doppelteStamm",Tab)== Message.YES_OPTION)
		{
		  int iAnz=0;
		  for (int i=0;i<Tab.size();i++)
		  {
			  Tabellenspeicher Tab2=new Tabellenspeicher(g,"select * from stamm_protokoll where aic_stamm="+Tab.getI(i,"aic_stamm")+Abfrage.Rolle(Tab.getI(i,"aic_Rolle"))+" and pro_aic_protokoll is null order by ab desc",true);
			  Date dt=null;
			  for (int i2=0;i2<Tab2.size();i2++)
				  if (dt==null)
					  dt=Tab2.getDate(i2, "ab");
				  else
					 iAnz+=g.exec("update stamm_protokoll set weg="+g.SQL_Format(dt)+" where aic_stamm="+Tab2.getI(i2,"aic_stamm")+" and aic_protokoll="+Tab2.getI(i2,"aic_protokoll")+" and nr="+Tab2.getI(i2,"nr"));
					  //g.fixInfo("ändere weg auf "+dt+"bei "+Tab.getI(i,"aic_stamm")+"/"+Tab.getI(i,"aic_Rolle"));
		  }
		  addMemo(Memo,iAnz+" doppelte Stammsätze im Zeitraum "+g.getVonBis2()+" bereinigt\n");
		}
		if (Tab.size()==0)
			addMemo(Memo,"keine doppelte Stammsätze im Zeitraum "+g.getVonBis2()+"\n");
    addMemo(Memo,DSP2_Check(g,""));
		//Tab.showGrid("doppelte Stammsätze");
		
	}

        private String CheckAll()
        {
          String s="";
          Gauge Gau=new Gauge("alles prüfen",0,7,"Spalten prüfen",g,false,thisJFrame());
          // Spalten prüfen
          s=check("abfrage"+g.join2("spalte","abfrage")+" left outer"+g.join2("fixeigenschaft","spalte")+" where aic_fixeigenschaft is null","Spalten",s);
          s=check2("spalte"+g.join("abfrage","spalte")+" group by abfrage.aic_begriff,spalte.aic_abfrage having sum(reihenfolge)<>count(reihenfolge)*(count(reihenfolge)+1)/2","Reihenfolge zu ändern",s);
          s=check2("(select aic_abfrage,begriff.defbezeichnung"+g.AU_Bezeichnung2("Begriff")+" Bezeichnung,spalten,(select max(nummer) from spalte where aic_abfrage=abfrage.aic_abfrage) Anzahl from begriff"+g.join2("abfrage","begriff")+") x where Anzahl>spalten","Spaltenanzahl richtigzustellen",s);
          s=check2("spalte"+g.join("abfrage","spalte")+" group by abfrage.aic_begriff,nummer having count(*)>1","doppelten Spalten-Nummern",s);
          s=check("(select aic_modell,(select count(*) from befehl where aic_modell=modell.aic_modell) Anzahl from modell) x where Anzahl=0","leere Modelle",s);
          s=check("modell right outer"+g.join("begriff","modell")+g.join("begriffgruppe","begriff")+" where begriffgruppe.kennung='Modell' and aic_modell is null","defekte Modelle",s);
          s=check("abfrage right outer"+g.join("begriff","abfrage")+g.join("begriffgruppe","begriff")+" where begriffgruppe.kennung='Abfrage' and aic_abfrage is null","defekte Abfrage",s);
          s=check("begriff"+g.join2("formular","begriff")+" left outer"+g.join2("darstellung","formular")+" where aic_darstellung is null and web is null"/*+g.bitis("bits", Formular.cstTemplate, 0)*/,"Formulare",s);
          s=check("formular right outer"+g.join("begriff","formular")+g.join("begriffgruppe","begriff")+" where begriffgruppe.kennung='Frame' and aic_formular is null","defekte Formulare",s);
          s=check2("(select Begriffgruppe.kennung Gruppe,Begriff.kennung,count(*) Anzahl from begriff"+g.join("begriffgruppe","begriff")+" group by Begriffgruppe.kennung,Begriff.kennung"+(g.MS()?",len(Begriff.kennung+'*')":"")+") x where Anzahl>1 and kennung is not null","Begriffe mit doppelter Kennung",s);
          s=check("(select aic_begriff,(select count(*) from begriff_zuordnung where aic_begriff=begriff.aic_begriff) anzahl from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Kunde")+") x where anzahl=0","Modulgruppen",s);
          // Stamm prüfen
          Gau.setText("LDATE prüfen",1);
          s=check2("bew_pool where gueltig is not null and ldate is null","LDATE richtigzustellen",s);
          s=check2("bew_pool where gueltig is null and ldate is null","LDATE auf 0 stellen",s);
          Gau.setText("ANR prüfen",2);
          ANR_Check(false);
          Gau.setText("Stamm prüfen",3);
          s=check("stamm where aic_stamm in (select stamm.aic_stamm from stamm left outer join stamm_protokoll p on stamm.aic_stamm=p.aic_stamm where p.aic_stamm is null)","Stamm_protokolle",s);
          s=DSP_Check(g,s,false,false);
          //s=check("(select p.aic_stamm,p.aic_eigenschaft,begriff.kennung,count(*) Anzahl,min(aic_stammpool) pool from stammpool p"+g.join("eigenschaft", "p")+g.join("begriff", "eigenschaft")+
          //        " where pro2_aic_protokoll is null and pro_aic_protokoll is null and p.aic_stamm is not null and begriff.kennung <>'Mehrfach' group by p.aic_stamm,p.aic_eigenschaft,begriff.kennung) x where Anzahl>1","doppelte Stammpools",s);
          s=check("(select p.aic_bew_pool,p.aic_eigenschaft,count(*) Anzahl,min(aic_stammpool) pool from stammpool p" + g.join("eigenschaft", "p") +
                  " where p.aic_bew_pool is not null group by p.aic_bew_pool,p.aic_eigenschaft) x where Anzahl>1","doppelte Bew-Stammpools",s);
          // Fremdtabellen prüfen
          Gau.setText("Fremdtabellen prüfen",4);
          String[] AryTab = new String[] {"Bezeichnung","Daten_Memo","Tooltip","Daten_Bild","Berechtigung","Lizenz"};
          //for (g.TabTabellenname.moveFirst();!g.TabTabellenname.eof();g.TabTabellenname.moveNext())
          for (int iPos = 0; iPos < g.TabTabellenname.size(); iPos++)
          {
              String sTab = g.TabTabellenname.getS(iPos,"Kennung");
              for(int i=0;i<AryTab.length;i++)
              {
                      //iAnz+= SQL.getInteger(g,"select count(*) from "+AryTab[i]+" where aic_tabellenname="+g.TabTabellenname.getI("Aic")+
                      //                         " and (select aic_"+sTab+" from "+sTab+" where aic_fremd=aic_"+sTab+") is null");
                      s=check(AryTab[i]+" where aic_tabellenname="+g.TabTabellenname.getI(iPos,"Aic")+" and (select aic_"+sTab+
                            " from "+sTab+" where aic_fremd=aic_"+sTab+") is null","Fremdtabelle "+AryTab[i]+" von "+sTab,s);
              }
          }
          // Protokolle prüfen
          Gau.setText("Protokolle prüfen",5);
          s=check("protokoll"+Protokolle(),"Protokolle",s);
          Gau.setText("Null prüfen",6);
          // Null-Check
          s=check("stammpool where spalte_double=0.0","Stammpool 0-Daten",s);
          s=check("bew_boolean where spalte_boolean=0","bew_boolean 0-Daten",s);
          s=check("bew_wert where spalte_wert=0","bew_wert 0-Daten",s);
          Gau.setText("fertig",7);
          return "\nAlles Prüfen:\n"+(s.equals("")?"geprüftes OK":s);
        }

        private static int wrongANR(int iBewPos,Global g,int iNr,boolean bNurNeu)
        {
          long lClock1=Static.get_ms();
          String sSp="ANR" + (iNr > 1 ? "" + iNr : "");
          int iEig=g.TabErfassungstypen.getI(iBewPos,"Eig"+iNr);
          int iAnz=0;
          if (iEig>0)
          {
            int iX= SQL.getInteger(g,"select max(aic_bew_pool) from bew_stamm where aic_eigenschaft=?",0,""+iEig);
            if (iX>0)
            {
              int iBew=g.TabErfassungstypen.getI(iBewPos, "AIC");
              boolean bNicht=bNurNeu && SQL.getInteger(g,"select max("+sSp+") from bew_pool where aic_bewegungstyp=?",0,""+iBew)>0;
              if (bNicht)
                iAnz=0;
              else
                iAnz = SQL.getInteger(g,"select count(*) from bew_pool left outer join bew_stamm on bew_pool.aic_bew_pool=bew_stamm.aic_bew_pool and aic_eigenschaft=" + iEig +
                                    " where aic_bewegungstyp=? and (" + sSp + "<>aic_stamm or aic_stamm is not null and " + sSp +
                                    " is null or aic_stamm is null and " + sSp + ">0)", 0, ""+iBew);
              g.testInfo(g.TabErfassungstypen.getS(iBewPos, "Bezeichnung") + ";" + iNr + ";" + iEig + ";" + iAnz + ";" + (Static.get_ms() - lClock1));
            }
          }
          //else
          //  iAnz=0;//SQL.getInteger(g,"select count(*) from bew_pool where aic_bewegungstyp=? and "+sSp+" is not null",0,g.TabErfassungstypen.getS(iBewPos,"AIC"));
          return iAnz;
        }

        private static int ANR_Clean(int iBewPos,Global g,int iNr,int iSoll)
        {
          String sSp="ANR" + (iNr > 1 ? "" + iNr : "");
          int iEig=g.TabErfassungstypen.getI(iBewPos,"Eig"+iNr);
          if (iEig>0)
          {
            int iAnz=g.exec("update bew_pool set "+sSp+"=(select aic_stamm from bew_stamm where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" +iEig +
                            ") where aic_bewegungstyp=" + g.TabErfassungstypen.getI(iBewPos,"AIC") + " and "+sSp+"<>(select aic_stamm from bew_stamm where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" +iEig +")");
            if (iSoll>iAnz)
               iAnz+=g.exec("update bew_pool set "+sSp+"=(select aic_stamm from bew_stamm where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" +iEig +
                            ") where aic_bewegungstyp=" + g.TabErfassungstypen.getI(iBewPos,"AIC") + " and "+sSp+" is null and (select aic_stamm from bew_stamm where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" +iEig +") is not null");
            if (iSoll>iAnz)
              iAnz+=g.exec("update bew_pool set "+sSp+"=null where aic_bewegungstyp=" + g.TabErfassungstypen.getI(iBewPos,"AIC") +
                           " and "+sSp+" is not null and (select aic_stamm from bew_stamm where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" +iEig +") is null");
            return iAnz;
          }
          else
            return g.exec("update bew_pool set "+sSp+"= null where aic_bewegungstyp=" + g.TabErfassungstypen.getI(iBewPos,"AIC") +" and "+sSp+" is not null");
        }

        private void ANR_Check(final boolean bReinigen)
        {
          //s=check2("bew_pool p join bew_stamm s on p.aic_bew_pool=s.aic_bew_pool and s.aic_eigenschaft"+g.getEigANR()+" where s.aic_stamm is not null and anr is null","ANR richtigzustellen",s);
          if (BtnANR!=null)
            BtnANR.setEnabled(false);
          new Thread(new Runnable()
                  {
                    public void run()
                    {
                      //String s=bReinigen ? "ANR reinigen:\n":"";
                      if (bReinigen)
                        addMemo(Memo,"ANR reinigen:\n");
                      int iAnz=g.TabErfassungstypen.size();
                      Gauge Gau = new Gauge("ANR prüfen", 0, iAnz, "", g, false,thisJFrame());
                      long lClock = Static.get_ms();
                      Tabellenspeicher TabAnzBew = new Tabellenspeicher(g, "select aic_bewegungstyp,count(*) Anzahl from bew_pool group by aic_bewegungstyp", true);
                      for (int iPos=0; iPos<iAnz;iPos++)
                      {
                        Gau.setText(g.TabErfassungstypen.getS(iPos,"Bezeichnung"), iPos);
                        int iBew = g.TabErfassungstypen.getI(iPos,"AIC");
                        if (TabAnzBew.existInhalt("aic_bewegungstyp", iBew))
                          addMemo(Memo,ANR_Check(g, "", iBew, bReinigen, false));
                      }
                      if (BtnANR!=null)
                        BtnANR.setEnabled(true);
                      //addMemo(Memo,s);
                      g.clockInfo(bReinigen ? "ANR erstellen" : "ANR prüfen", lClock);
                      Gau.setText("fertig", iAnz);
                    }
                  }).start();
        }

        public static String ANR_Check(Global g,String s,int iBew,boolean bReinigen,boolean bNurNeu)
        {
          int iBewPos=g.TabErfassungstypen.getPos("Aic",iBew);
          if (iBewPos<0)
          {
            Static.printError("Reinigung.ANR_Check: Bewegung "+iBew+" nicht gefunden!");
            return s;
          }
          else
           for (int i=1;i<10;i++)
            {
              int iAnz = wrongANR(iBewPos,g, i,bNurNeu);
              if (iAnz > 0)
              {
                String s1 = g.TabErfassungstypen.getS(iBewPos,"Bezeichnung") + ":" + (bReinigen ? ANR_Clean(iBewPos,g, i,iAnz) :  iAnz) + " ANR"+i+(bReinigen ?" richtiggestellt !":" falsch!");
                if (bReinigen)
                  g.diskInfo(s1);
                s += s1 + "\n";
              }
            }
            return s;
        }
        
        public static String check1970(Global g)
        {
//        	g.fixtestError("check1970");
        	String s="";
        	String s1970=g.DateTimeToString(Static.dt1970);
        	String s1970o=g.DateTimeToString(Static.dt1970old);
        	s=execInfo("update stammpool set gultig_von="+s1970+" where gultig_VON="+s1970o,"Stammpool-1970 in von",g,s,false," richtiggestellt");
        	s=execInfo("update stammpool set gueltig_bis="+s1970+" where gueltig_BIS="+s1970o,"Stammpool-1970 in bis",g,s,false," richtiggestellt");
        	s=execInfo("delete from stammpool where gultig_von is not null and gultig_VON>=gueltig_bis","Stammpool ohne ZR",g,s,false);
        	if (!Static.Leer(s))
        		return "Bereinigte Stammpools:\n"+s;
        	return s;
        }

        private static int DSP_Check(Global g,int iStamm,int iEig)
        {
          int iAnz=0;
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_stammpool,gultig_von,gueltig_bis from stammpool where pro2_aic_protokoll is null and aic_stamm="+iStamm+" and aic_eigenschaft="+iEig+" order by "+g.orderD("gultig_von")+",aic_stammpool desc",true);
          java.sql.Date dtVon=Tab.getD("gultig_von");
          java.sql.Date dtBis=Tab.getD("gueltig_bis");
          int iP=Tab.getI("aic_stammpool");
          for(Tab.moveNext();!Tab.eof();Tab.moveNext())
          {
            //g.progInfo(dtVon+"/"+Tab.getD("gultig_von")+"/"+Tab.getD("gultig_von").equals(dtVon));
            if (Tab.isNull("gultig_von") || dtVon != null && Tab.getD("gultig_von").equals(dtVon))
              iAnz+=g.exec("delete from stammpool where aic_stammpool="+Tab.getI("AIC_Stammpool"));
            else
            {
              if (dtBis == null || !Tab.getD("gultig_von").equals(dtBis))
                iAnz+=g.exec("update stammpool set gueltig_bis=(select gultig_von from stammpool where aic_stammpool="+Tab.getI("aic_stammpool")+") where aic_stammpool="+iP);
              dtVon=Tab.getD("gultig_von");
              dtBis=Tab.getD("gueltig_bis");
              iP=Tab.getI("aic_stammpool");
            }
          }
          return iAnz;
        }

        private static String DSP_Check(Global g,String s,boolean bReinigen,boolean bProt)
        {
          int iMF=g.getBegriffAicS("Datentyp","Mehrfach");
          int iP=0;
          if (bProt)
          {
            DateWOD dt=new DateWOD();
            dt.yesterday();
            dt.yesterday();
            iP = SQL.getInteger(g, "select max(aic_protokoll) from protokoll where timestamp<" + g.DateTimeToString(dt.toTimestamp()));
          }
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from (select p.aic_stamm,p.aic_eigenschaft,count(*) Anzahl from poolview p join eigenschaft on eigenschaft.aic_eigenschaft=p.aic_eigenschaft"+
                                                    " where aic_begriff<>"+iMF+" and p.aic_stamm is not null"+(iP>0 ?" and aic_protokoll<="+iP:"")+" group by p.aic_stamm,p.aic_eigenschaft) x where Anzahl>1",true);
          for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            int iS=Tab.getI("aic_stamm");
            String s1 = SQL.getString(g,"select bezeichnung from stammview2 where aic_stamm="+iS)+" ("+iS+"): Eigenschaft "+g.TabEigenschaften.getBezeichnungS(Tab.getI("aic_eigenschaft"))+
                " "+(bReinigen ? DSP_Check(g,iS,Tab.getI("aic_eigenschaft")):Tab.getI("Anzahl"))+(bReinigen?" bereinigt":"x");
            g.progInfo(s1);
            s += s1 + "\n";
          }
          return s;
        }

        public static String DSP2_Check(Global g,String s)
        {
          long lClock = Static.get_ms();
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_eigenschaft,kennung,bits from eigenschaft where"+g.bit("bits", Global.cstEigClean),true);
          for(Tab.moveFirst();!Tab.out();Tab.moveNext())
          {
            int iAic=Tab.getI("aic_eigenschaft");
            int iAnzDel=SQL.getInteger(g, "select count(*) from stammpool where aic_eigenschaft="+iAic+" and aic_bew_pool is not null and pro2_aic_protokoll is not null");
            if (iAnzDel>0)
            {
              iAnzDel=g.exec("delete from stammpool where aic_eigenschaft="+iAic+" and aic_bew_pool is not null and pro2_aic_protokoll is not null");
              g.diskInfo(iAnzDel+" gelöschte von Eigenschaft "+Tab.getS("Kennung")+" richtig entfernt");
            }
            int iAnzDop=SQL.getInteger(g, "select count(*) from (select  aic_bew_pool,count(*) Anzahl from stammpool where aic_eigenschaft="+iAic+" and aic_bew_pool is not null group by aic_bew_pool) x where Anzahl>1");
            if (iAnzDop>0)
            {
              int iAnzD2=g.exec("delete from stammpool where aic_eigenschaft="+iAic+" and aic_bew_pool in (select aic_bew_pool from (select  aic_bew_pool,count(*) Anzahl from stammpool where aic_eigenschaft="+iAic+
                " and aic_bew_pool is not null group by aic_bew_pool) x where Anzahl>1) and aic_stammpool%2=0");
              g.diskInfo(iAnzD2+"x doppelte Eigenschaft "+Tab.getS("Kennung")+" richtig entfernt");
            }
            s+= "Clean "+Tab.getS("Kennung")+": del="+iAnzDel+", doppelt="+iAnzDop+"\n";
          }
          g.clockInfo("DSP2_Check",lClock);
          return s;
        }

        private String check(String sSQL,String sText,String s)
        {
          long lClock = Static.get_ms();
          int iAnz= SQL.getInteger(g,"select count(*) from "+sSQL);
          if (iAnz>0)
            s+=iAnz+" "+sText+" zu löschen!\n";
          g.clockInfo(sText,lClock,0);
          return s;
        }

        private String check2(String sSQL,String sText,String s)
        {
          long lClock = Static.get_ms();
          int iAnz= SQL.getInteger(g,"select count(*) from "+sSQL);
          if (iAnz>0)
            s+=iAnz+" x "+sText+"!\n";
          g.clockInfo(sText,lClock);
          return s;
        }
        
    public static void NichtExistenteLoeschen(Global g,JTextArea Txt,int iSek)
    {
    	DateWOD DW=new DateWOD();
    	DW.prevMonth();
    	int iP=SQL.getInteger(g, "select min(aic_protokoll) from protokoll where timestamp>"+g.DateTimeToString(DW.toTimestamp()));
    	g.fixtestError("DW="+DW+" -> Prot="+iP);
    	Vector<Integer> Vec=SQL.getVector("select aic_bew_pool from bew_pool where aic_protokoll=pro_aic_protokoll and aic_protokoll<"+iP, g);
    	g.fixtestError("Vec="+Vec);
    	String s=null;
    	if (Vec.size()>0)
    	{
    		int iAnz=Aufruf.destroyAll(g, Vec, iSek);
    		s=iAnz+" nie existente Bewegungsdaten entfernt";
    		g.diskInfo(s);		
    	}
    	s=execInfo("delete from stamm_protokoll where aic_protokoll=pro_aic_protokoll and aic_protokoll<"+iP,"NE-Stamm_Protokoll",g,s==null ? "":s+"\n",false);
    	s=execInfo("delete from stammpool where aic_protokoll=pro2_aic_protokoll and aic_protokoll<"+iP,"NE-StammPool",g,s.equals("") ? "":s,false);
		if (Txt != null)
            addMemo(Txt,"\n"+s+"\n");
    }

	public static void FremdtabellenLoeschen(final Global g,final JButton Btn,final JTextArea Txt)
	{
          new Thread(new Runnable()
                  {
                    public void run()
                    {
                      if (Btn != null)
                        Btn.setEnabled(false);
                      // offen bereinigen
                      Vector<Integer> VecO=SQL.getVector("select aic_auswahl from auswahl where nr=0", g);
                      String s=execInfo("delete from bew_aic where aic"+Static.SQL_in(VecO),"Bool3 auf offen",g,"",true);
                      // Module bereinigen
                      s+= DelModule(g,Btn != null);
                      // Fremdtabellen bereinigen
                      s+= Btn != null ? "\nFremd-Tabellen löschen:\n" : "";
                      String[] AryTab = new String[]
                          {
                          "Bezeichnung", "Daten_Memo", "Tooltip", "Daten_Bild", "Berechtigung", "Lizenz"};
                      boolean bKeine = true;
                      for (int iPos = 0; iPos < g.TabTabellenname.size(); iPos++)
                      {
						String sTab = g.TabTabellenname.getS(iPos,"Kennung");
						for(int i=0;i<AryTab.length;i++)
						{
							int iAnz= SQL.getInteger(g,"select count(*) from "+AryTab[i]+" where aic_tabellenname="+g.TabTabellenname.getI(iPos,"Aic")+
			                                                         " and (select aic_"+sTab+" from "+sTab+" where aic_fremd=aic_"+sTab+") is null");
							if (iAnz!=0)
							{
								s=execInfo("delete from "+AryTab[i]+" where aic_tabellenname="+g.TabTabellenname.getI(iPos,"Aic")+
									" and (select aic_"+sTab+" from "+sTab+" where aic_fremd=aic_"+sTab+") is null",
			                                                g.TabTabellenname.getS(iPos,"Bezeichnung") + "-" + AryTab[i],g,s,Btn!=null);
								bKeine=false;
							}
						}
                      }
                      if (bKeine && Btn!=null)
                    	  s+="Keine Fremdtabellen gelöscht!\n";
                      if (Btn!=null)
                        Btn.setEnabled(true);
                      if (Txt != null)
                        addMemo(Txt,s);
                    }
                  }).start();
	}

        private static String Protokolle()
        {
          return " where (select count(*) from stamm_protokoll s where s.aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from stamm_protokoll s where s.pro_aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from stammpool s where s.aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from stammpool s where s.pro_aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from stammpool s where s.pro2_aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from zwischenspeicher s where s.aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from zwischenspeicher s where s.pro_aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from abschluss s where s.aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from abschluss s where s.pro_aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from favorit s where s.aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from Sperre s where s.aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from Verboten s where s.aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from bew_pool s where s.aic_protokoll=protokoll.aic_protokoll)+"+
                                "(select count(*) from bew_pool s where s.pro_aic_protokoll=protokoll.aic_protokoll)=0";
        }

	public static void ProtokollCheck(final Transact g,final DateWOD dt1,final int iMax,final JButton Btn,final JTextArea Txt)
	{
          new Thread(new Runnable()
                  {
                    public void run()
                    {
                      if (Btn != null)
                        Btn.setEnabled(false);
                      DateWOD dt=dt1;
                      if (dt == null)
                      {
                        dt = new DateWOD();
                        dt.yesterday();
                        dt.yesterday();
                      }
                      //g.diskInfo("ProtokollCheck" + Static.Mem(g.TestPC()));
                      int iAic = SQL.getInteger(g, "select max(aic_protokoll) from protokoll where timestamp<" + g.DateTimeToString(dt.toTimestamp()));
                      g.testInfo("Max-Prot=" + iAic + " bei " + dt);
                      String s = Btn != null ? "\nLeere Protokolle löschen:\n" : "";
                      SQL Qry = new SQL(g);
                      String sWhere = Protokolle() + " and aic_protokoll<=" + iAic;
                      int iAnz = Qry.getInteger("select count(*) from protokoll" + sWhere);
                      if (iAnz>0)
                        g.diskInfo(iAnz + " Protokolle zum Löschen" + Static.Mem(g.TestPC()));
                      int iMom = 0;
                      if (iAnz == 0)
                        s += Btn != null ? "Keine Protokolle zum löschen!\n" : "";
                      else
                      {
			//s=execInfo(Qry,"protokoll","protokoll p"+sWhere,"Protokolle",g,s,bVollInfo);
                        g.fixInfo(iAnz+" Protokolle zum löschen");
                        int i=iAnz;
                        while(i>0 && iAnz>0 && iMom<iMax)
                        {
                          i = Qry.deleteTop("protokoll" + sWhere,5000);
                          g.diskInfo(i + " Protokolle gelöscht"+Static.Mem(g.TestPC()));
                          s += i + " von " + iAnz + " Protokolle gelöscht!\n";
                          iAnz-=i;
                          iMom+=i;
                          if (i>0 && iAnz>0)
                            g.fixInfo("Noch "+iAnz+" Protokolle zum löschen");
                        }
                      }
                      Qry.free();
                      if (Btn != null)
                        Btn.setEnabled(true);
                      if (Txt != null)
                        addMemo(Txt,s);
                    }
                  }).start();
	}

	public static String BildCheck(Global g,boolean bVollInfo)
	{
		String s=bVollInfo ? "\nBilder kontrollieren:\n":"";
                //int iTab_Begriff = g.TabTabellenname.posInhalt("Kennung","BEGRIFF") ? g.TabTabellenname.getI("Aic"):0;
                //s=execInfo("delete from daten_bild from daten_bild t1 join begriff t2 on t1.aic_fremd=t2.aic_begriff where (fixe_sprache=1 or combo=1) and aic_sprache>1 and aic_tabellenname="+g.iTabBegriff,
//                s=execInfo("delete from daten_bild where aic_sprache>1 and aic_fremd in (select aic_begriff from begriff where (fixe_sprache=1 or combo=1)) and aic_tabellenname="+g.iTabBegriff,
//                           "Bilder mit fix-Sprache oder Combo",g,s,bVollInfo);
        String[] sTab= new String[]{"daten_bild2","daten_doku","daten_image"};
        for (int i=0;i<sTab.length;i++)
		{
        	int iAnz=0;   		
			String sT=sTab[i];
			Tabellenspeicher Tab=new Tabellenspeicher(g,"select x.* from (select aic_daten,filename,(select count(*) from stammpool where aic_daten=d.aic_daten) Anzahl from "+sT+" d) x where anzahl=0",true);
			for (Tab.moveFirst();!Tab.out();Tab.moveNext())
				if (g.exec("delete from "+sT+" where aic_daten="+Tab.getI("aic_daten"))>0)
				{
					iAnz++;
					g.fixtestError("Lösche von "+sT+": "+Tab.getS("filename"));	
				}
			if (iAnz>0)
			{
				g.diskInfo(iAnz + " von "+sT+" gelöscht"); 
				s+=iAnz+" "+sT+" gelöscht\n";
			}
				
		}
        if (Static.bX11)
        {
        	s=KonvertMini(g,s); // erzeugt Mini bei daten_bild2 auf Breite=150 (Wenn Mini noch fehlt)
        	s=KonvertDatenBild2(g,s); // verkleiner Bilder in Daten_Bild2 auf Breite=1200 (wenn Bild>400k)
        	s=KonvertDatenDokuBilder(g,s); // verkleinert Bilder bei daten_doku auf Breite=1000 (Wenn Bild>500k)
//	        int iAnzA=KonvertBildAbschnitt(g);
//	        if (iAnzA>0)
//	        	s+=iAnzA + " Abschnitt-Bilder gespeichert\n";
//	        KonvertBildKF(g);
//	        Tabellenspeicher TabZ=new Tabellenspeicher(g,"select aic_zeile,bild filename,null bild from zeile where BildKF is not null",true);
//	        for (TabZ.moveFirst();!TabZ.out();TabZ.moveNext())
//	        {
//	        	TabZ.setInhalt("Bild", SQL.getImageZ(g,TabZ.getI("aic_zeile")));
//	        }
//	        TabZ.showGrid();
        }
		if (Static.bBilder)
			s+="\n"+new BildUpdate(g).getString()+"\n";

                Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,homepage from begriff where homepage like '%\\%'",true);
                int i=0;
                for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                  i+=g.exec("update begriff set homepage='"+Static.replaceString(Tab.getS("homepage"),"\\","/")+"' where aic_begriff="+Tab.getI("aic_begriff"));
                if (i>0)
                {
    				g.diskInfo(i+" Homepages geändert"); 
    				s+="\n"+i+" Homepages geändert\n";
                }
		return s;
	}
	
	public static String KonvertMini(Global g,String s)
	{
		long lClock3=Static.get_ms();
		Tabellenspeicher TabI=new Tabellenspeicher(g,"select aic_daten,filename from daten_bild2 where mini is null",true);
        int iAnzM=TabI.size();
        if (iAnzM>0)
        {
	        for (int i=0;i<iAnzM;i++)
	        {
	        	int iDaten=TabI.getI(i,"aic_daten");
	        	Image Img=SQL.getImage(g, iDaten);
	        	Import.ImageToMini(g,iDaten,Img,TabI.getS(i,"Filename"),true);
	        }
	        g.diskInfo(iAnzM + " auf Mini umgewandelt"); 
	        if (s!= null)
	        	s+=iAnzM + " auf Mini umgewandelt\n";
        }
        g.clockInfo3("Reinigung.KonvertMini",lClock3);  
        return s;
	}
	
	public static String KonvertDatenDokuBilder(Global g,String s)
	{
		long lClock3=Static.get_ms();
		String[] sAry=new String[] {"jpg","jpeg","png"};
		String sLen=g.MySQL() ? "length":"datalength";
		for (int iF=0;iF<sAry.length;iF++)
		{
			Tabellenspeicher TabI=new Tabellenspeicher(g,"select aic_daten,filename,"+sLen+"(doku) laenge from daten_doku where filename like '%."+sAry[iF]+"' and "+sLen+"(doku)>500000",true);
			int iAnzM=TabI.size();
	        if (iAnzM>0)
	        {
	        	g.fixtestError("KonvertDatenDokuBilder: "+iAnzM+" mit "+sAry[iF]);	
	        	int iAnz=0;
	        	for (int i=0;i<iAnzM;i++)
		        {
		        	int iDaten=TabI.getI(i,"aic_daten");//Sort.geti(Vec, i);
		        	Image Img=SQL.getImageD(g, iDaten);
		        	if (Import.ImageToMini2(g,iDaten,Img,TabI.getS(i,"Filename")) != null)
		        		iAnz++;
		        }
	        	if (iAnz>0)
		        {
		          g.diskInfo(iAnz + " "+sAry[iF]+"-Bilder auf Daten_Doku verkleinert"); 
		          if (s!= null)
		        	s+=iAnz + " "+sAry[iF]+"-Bilder auf Daten_Doku verkleinert\n";
		        }
	        }
	        g.clockInfo3("Reinigung.KonvertDatenDokuBilder "+sAry[iF],lClock3);
	        lClock3=Static.get_ms();
		}
        return s;
	}
	
	public static String KonvertDatenBild2(Global g,String s)
	{
		long lClock3=Static.get_ms();
		String[] sAry=new String[] {"jpg","jpeg","png"};
		String sLen=g.MySQL() ? "length":"datalength";
		for (int iF=0;iF<sAry.length;iF++)
		{
			Tabellenspeicher TabI=new Tabellenspeicher(g,"select aic_daten,filename,"+sLen+"(bild2) laenge from daten_bild2 where filename like '%."+sAry[iF]+"' and "+sLen+"(bild2)>400000",true);
//			TabI.showGrid("Daten_Bild2");
	        int iAnzM=TabI.size();
	        if (iAnzM>0)
	        {
	        	g.fixtestError("KonvertDatenBild2: "+iAnzM+" mit "+sAry[iF]);	
	        	int iAnz=0;
		        for (int i=0;i<iAnzM;i++)
		        {
		        	int iDaten=TabI.getI(i,"aic_daten");//Sort.geti(Vec, i);
		        	Image Img=SQL.getImage(g, iDaten);
		        	if (Import.ImageToMini(g,iDaten,Img,TabI.getS(i,"Filename"),false) != null)
		        		iAnz++;
		        }
		        if (iAnz>0)
		        {
		        	g.diskInfo(iAnz + " "+sAry[iF]+"-Bilder auf Daten_Bild2 verkleinert"); 
		        	if (s!= null)
		        		s+=iAnz + " "+sAry[iF]+"-Bilder auf Daten_Bild2 verkleinert\n";
		        }
	        }
	        g.clockInfo3("Reinigung.KonvertDatenBild2 "+sAry[iF],lClock3);
	        lClock3=Static.get_ms();
		}
        return s;
	}
	
	public static void KonvertBildKF(Global g)
	{
		Tabellenspeicher TabZ=new Tabellenspeicher(g,"select aic_zeile,bild from zeile where BildKF is null and bild is not null",true);
        int iAnz=0;
        for (TabZ.moveFirst();!TabZ.out();TabZ.moveNext())
        {
        	String sBild=TabZ.getS("bild");
        	Image Img=g.LoadImage(sBild, Static.DirImageDef);
        	String sFile2=Static.getTemp()+sBild;
			File file=new File(sFile2);
			try
    		{
				ImageIO.write(Static.imageToBufferedImage(sBild,Img), sBild.substring(sBild.lastIndexOf('.')+1).toLowerCase(), file);
				g.UpdateMini(sFile2,"update zeile set BildKF=? where aic_zeile="+TabZ.getI("aic_zeile"));
				iAnz++;
    		}
			catch (Exception e) {
				Static.printError("BildKF "+sFile2+" nicht konvertierbar");
//    			g.printStackTrace(e);
    		}
			file.delete();
        }
        if (iAnz>0)
        	g.diskInfo(iAnz + " Zeilen-Bilder konvertiert"); 
	}
	
	public static int KonvertBildAbschnitt(Global g)
	{
		int iTab=g.TabTabellenname.getAic("ABSCHNITT");
		Tabellenspeicher TabA=new Tabellenspeicher(g,"select aic_fremd,filename from daten_bild where Bild is null and aic_tabellenname="+iTab,true);
//		TabA.showGrid("TabA");
		int iAnz=0;
        for (TabA.moveFirst();!TabA.out();TabA.moveNext())
        {
        	String sBild=TabA.getS("filename");
        	Image Img=g.LoadImage(sBild, Static.DirImageSys);
        	String sFile2=Static.getTemp()+sBild;
			File file=new File(sFile2);
			try
    		{
				ImageIO.write(Static.imageToBufferedImage(sBild,Img), sBild.substring(sBild.lastIndexOf('.')+1).toLowerCase(), file);
				g.UpdateMini(sFile2,"update daten_bild set Bild=? where aic_tabellenname="+iTab+" and aic_fremd="+TabA.getI("aic_fremd"));
				iAnz++;
    		}
			catch (Exception e) {
				Static.printError("Abschnitt-Bild "+sFile2+" nicht konvertierbar");
//    			g.printStackTrace(e);
    		}
			file.delete();
        }
        if (iAnz>0)
        	g.diskInfo(iAnz + " Abschnitt-Bilder konvertiert"); 
        return iAnz;
	}
	

        /*private static void checkANR(Global g)
        {
          int iGruppe=SQL.getInteger(g,"select aic_begriffgruppe from begriffgruppe where kennung='Anzeigeart'");
          if (!SQL.exists(g,"select * from code where kennung='anr' and aic_begriffgruppe="+iGruppe))
          {
            SQL Qry=new SQL(g);
            Qry.add("AIC_Begriffgruppe",iGruppe);
            Qry.add("Kennung","anr");
            if (Qry.insert("Code",true)>0)
              g.diskInfo("Anzeigeart anr angelegt");
            Qry.free();
          }
          if (!SQL.exists(g,"select * from code where kennung='ldate' and aic_begriffgruppe="+iGruppe))
          {
            SQL Qry=new SQL(g);
            Qry.add("AIC_Begriffgruppe",iGruppe);
            Qry.add("Kennung","ldate");
            if (Qry.insert("Code",true)>0)
              g.diskInfo("Anzeigeart ldate angelegt");
            Qry.free();
          }
        }*/

  private static String DelEigNicht(String sKennung,Global g,String s)
  {
    if (SQL.getInteger(g,"select count(*) from eigenschaft where kennung='"+sKennung+"'")>1)
    {
      Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_eigenschaft,kennung from eigenschaft where kennung='"+sKennung+"'",true);
      int iPos=Tab.getPos("Kennung",sKennung);
      if (iPos<0)
      {
        s+="richtige Eigenschaft "+sKennung+" nicht gefunden\n";
      }
      else
      {
        int iAicR=Tab.getI(iPos,"aic_eigenschaft");
        int iAicF=Tab.getI(iPos==0 ? 1:0,"aic_eigenschaft");
        String sFalsch=Tab.getS(iPos==0 ? 1:0,"kennung");
        g.bISQL=true;
        g.exec("update bew_zuordnung set aic_eigenschaft="+iAicR+" where aic_eigenschaft="+iAicF);
        g.bISQL=false;
        g.exec("update fixeigenschaft set aic_eigenschaft="+iAicR+" where aic_eigenschaft="+iAicF);
        g.exec("update stammpool set aic_eigenschaft="+iAicR+" where aic_eigenschaft="+iAicF);
        if (g.exec("delete from eigenschaft where aic_eigenschaft="+iAicF)>0)
          s+="falsche Eigenschaft "+sFalsch+" gelöscht\n";
        else
          s+="falsche Eigenschaft "+sFalsch+" nicht löschbar!\n";
      }
    }
    return s;
  }
  
  private static String DoppelteEig(Global g,boolean bVollInfo)
  {
	  String s=bVollInfo ? "falsche Eigenschaften:\n":"";
	  s=DelEigNicht("Abschluß",g,s);
      s=DelEigNicht("LV_steuerfrei_Auslan",g,s);
      s=DelEigNicht("ArtikelGruppe",g,s);
      s=DelEigNicht("Deckungsbeitrag",g,s);
      s=DelEigNicht("Hohe_Schuhe",g,s);
      return s;
  }
  
  private static String StdErfCheck(Global g,boolean bVollInfo)
  {
	  int iAbf=SQL.getInteger(g, "select aic_abfrage from abfrage a join begriff b on a.aic_begriff=b.aic_begriff where kennung='LV_Stundenerfassung'");
	  if (iAbf>0)
	  {
		  int iSp=SQL.getInteger(g,"select aic_spalte from spalte where aic_abfrage="+iAbf+" and nummer=3");
		  return execInfo("update spalten2 set aic_stamm=null where aic_stamm is not null and aic_spalte<>"+iSp+" and aic_abfrage="+iAbf,"falsche Std-Erf-Def",g,"",bVollInfo);    
	  }
	  return "";
  }
  
  private static String Stt_setzen(Global g,boolean bVollInfo)
  {
	  return execInfo2("update stamm_protokoll set AIC_STAMMTYP2=(select aic_stammtyp from stamm where aic_stamm=stamm_protokoll.aic_stamm) where aic_stammtyp2 is null","Stammtyp2",g,"",bVollInfo);
  }
  
  private static String ImmerCheck(Global g)
  {
	  String s="\nPrüfung während andere eingeloggt:\n"+DoppelteEig(g,true)+StdErfCheck(g,true)+Stt_setzen(g,true);
	  s=DSP_Check(g,s+"doppelte Stammpools reinigen:\n",true,true);  
	  //s+=execInfo("update benutzer set kennung="+g.concat("'-'","kennung")+" where geloescht is not null and kennung not like '-%'","gelöschte Benutzer",g,"",true," geändert");
    s=DSP2_Check(g,s);
	  s=ANR_null(g,s,true);
	  s=PW_check(g,s);
    s=Firma_check(g,s);
    return s;
  }

  private static String Firma_check(Global g,String s)
  {
    int iF=g.getBegriffAicS("Datentyp", "Firma");
    int iAnz=g.exec("update eigenschaft set aic_stammtyp="+g.iSttFirma+" where aic_begriff="+iF+" and aic_stammtyp is null");
    s+="Firma-Eigenschaften angepasst: "+iAnz+"\n";
    return s;
  }
  
  private static String ANR_null(Global g,String s,boolean bInfo)
  {
	  return execInfo2("update bew_pool set anr=null where anr=0","ANR von 0 auf null",g,s,bInfo);
  }
  
  private static String PW_check(Global g,String s) /// prüft PW-Länge und korrigiert sie
  {
	  s+=execInfo("update benutzer set passwort="+g.concat("'0'","passwort")+" where "+(g.MS() ? "len":"length")+"(passwort)=31","Passwort31",g,"",true," angepasst");
	  s+=execInfo("update benutzer set passwort="+g.concat("'00'","passwort")+" where "+(g.MS() ? "len":"length")+"(passwort)=30","Passwort30",g,"",false," angepasst");
	  return s;
  }
  
  private static String LZ_Check(Global g,String s) // prüft ob Leerzeichen in Begriff-Kennung
  {
	  String[] sBG=new String [] {"Abfrage","Modell","Druck"};
	  for (int i=0;i<sBG.length;i++)
	  {
		  int iBG=g.TabBegriffgruppen.getAic(sBG[i]);
		  int iAnz=0;
		  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,kennung from begriff where aic_begriffgruppe="+iBG+" and kennung like '% %'",true);
		  for(Tab.moveFirst();!Tab.out();Tab.moveNext())
		  {
			  String sKen=Tab.getS("Kennung").replaceAll(" ", sBG[i].equals("Abfrage") ? "__":"_");
			  iAnz+=g.exec("update begriff set kennung='"+sKen+"' where aic_begriff="+Tab.getI("aic_begriff"));
		  }
		  if(iAnz>0)
          {
            s += "Kennungen von "+sBG[i]+": " + iAnz + " geändert!\n";
            g.diskInfo(iAnz+" Kennungen von "+sBG[i]+" geändert");
          }
		  //Tab.showGrid(sBG[i]);
	  }
	  return s;
  }
  
  public static String checkUTF8(Global g,boolean bVollInfo)
  {
	  String s="";
	  if (g.MySQL())
	  {
		  String[] sTab= new String[] {"befehl","bezeichnung","Daten_Stringx","fehler","Stamm_protokoll","Tooltip","Verlauf","Daten_Memo","Daten_Text","Daten_doku"};
		  for (int i=0;i<sTab.length;i++)
		    g.exec("alter table "+sTab[i]+" convert to character set utf8mb4 collate utf8mb4_general_ci");
		  s=bVollInfo ? sTab.length+" Tabellen auf UTF8 umstellen\n":"";
	  }
	  return s;
  }
  
	public static String SpaltenCheck(Global g,boolean bVollInfo)
	{
		int iFehler=0;
                //checkANR(g);
                //g.fixInfo(g.exec("update eigenschaft join begriff set feldlaenge=-1 where begriff.kennung in ('Double','BewZahl') and feldlaenge is null")+" Zahl-Eigenschaften auf beliebige Nachkommastellen");
                //g.fixInfo(g.exec("update eigenschaft join begriff set feldlaenge=2 where begriff.kennung like ('%Waehrung')")+" Währungs-Eigenschaften auf 2 Nachkommastellen");
		String s=ImmerCheck(g)+(bVollInfo ? "\nSpalten kontrollieren:\n":"");     
//		s+=checkUTF8(g,bVollInfo);
                s+=DoppelteEig(g,bVollInfo)+StdErfCheck(g,bVollInfo);
                s=PW_check(g,s);
                s=LZ_Check(g,s);
                s=execInfo("delete from tooltip where memo2 is null","leere Tooltips",g,s,bVollInfo);
                s=execInfo("delete from daten_memo where memo is null","leere Memos",g,s,bVollInfo);
                Vector Vec=SQL.getVector("select aic_code from code where kennung in ('Kopie')",g); // 'Tabelle','Planung',
                if(Vec!=null && Vec.size() > 0)
                {
                  s=execInfo2("update protokoll set aic_code=" + g.getBegriffAicS("Anlage", "Eingabe") + " where aic_code" + Static.SQL_in(Vec),"Anlagen auf Protokoll",g,s,false);
                }
                s=execInfo("delete from code where kennung in ('Kopie','Gesperrt')","Anlagetypen",g,s,false);
                Vector<Integer> VecC=SQL.getVector("select aic_code from code where kennung is null", g);
                if (VecC.size()>0)
                {
                  int iWeb=g.getCodeAic("Anlage", "Web",false);
                  g.exec("update protokoll set aic_code="+iWeb+" where aic_code"+Static.SQL_in(VecC));
                  s=execInfo("delete from code where kennung is null","Codes ohne Kennungen",g,s,false);
                }
                //s=execInfo("delete from bezeichnung where bezeichnung=''","leere Bezeichnungen",g,s,false); löscht auch Bezeichnungen mit nur Leerzeichen
                s=execInfo("delete from bezeichnung where bezeichnung is null or "+(g.MS() ?"datalength":"length")+"(bezeichnung)=0","leere Bezeichnungen",g,s,false);
                //s=execInfo("delete from bezeichnung where bezeichnung is null"+(g.MySQL() ?" or length(bezeichnung)=0":""),"leere Bezeichnungen",g,s,false);
                s=execInfo("delete from tooltip where memo2=''","leere Tooltips",g,s,false);
		int iAnz=0;
                int iAnzM=0;
		SQL Qry = new SQL(g);
		Qry.open("select abfrage.aic_begriff,spalte.aic_spalte,spalte.kennung,(select count(*) from befehl where aic_spalte=spalte.aic_spalte) Anzahl from abfrage"+g.join2("spalte","abfrage")+" left outer"+g.join2("fixeigenschaft","spalte")+" where aic_fixeigenschaft is null");
		while (!Qry.eof())
		{
			//if (Qry.getI("Anzahl")==0)
			//{
                        int i=Qry.getI("aic_spalte");
                          if (Qry.getI("Anzahl")>0)
                          {
                            g.exec("update begriff set importZeit=null where aic_begriff in (select aic_begriff from modell join befehl on modell.aic_modell=befehl.aic_modell where aic_spalte="+i+")");
                            iAnzM += g.exec("update befehl set aic_spalte=null where aic_spalte=" + i);
                          }
                          g.exec("delete from SPALTE_BERECHNUNG where aic_spalte="+i);
                          g.exec("delete from SPALTE_BERECHNUNG where spa_aic_spalte="+i);
                          g.exec("delete from spalte_zuordnung where aic_spalte="+i);
                          g.exec("delete from spalten2 where aic_spalte="+i);
                          g.exec("delete from spalte_z2 where aic_spalte="+i);
                          g.exec("delete from spalte where aic_spalte="+i);
                          iAnz++;
			//}
			/*else
			{
                          SQL Qry2=new SQL(g);
                          s+="Spalte "+Qry.getS("kennung")+" ("+Qry.getI("aic_spalte")+") von Abfrage "+g.TabBegriffe.getBezeichnung(Qry.getI("aic_begriff"))+" ("+Qry.getI("aic_begriff")+
                              ") kommt in Modell "+Qry2.list("begriff.defbezeichnung","begriff"+g.join2("modell","begriff")+",befehl where modell.aic_modell=befehl.aic_modell and aic_spalte="+Qry.getI("aic_spalte"))+" vor!\n";
                          iFehler++;
                          Qry2.free();
			}*/

			Qry.moveNext();
		}
                if (iAnzM>0)
                {
                  s+="aus "+iAnzM+" Befehlszeilen wurden die Spalten gelöscht!\n";
                  g.diskInfo(iAnzM+" Befehlszeilen-Spalten gelöscht");
                }
		if (iAnz==0)
			s+=bVollInfo ? "Keine Spalten zum löschen!\n":"";
		else
			s+=iAnz+" Spalten gelöscht!\n";
                iAnzM=g.exec("delete from befehl where aic_code is null and mod_aic_modell is null");
                if (iAnzM>0)
                  s+=iAnzM+" defekte Modell-Zeilen gelöscht\n";

		Tabellenspeicher Tab=new Tabellenspeicher(g,"select abfrage.aic_begriff,spalte.aic_abfrage from spalte"+g.join("abfrage","spalte")+" group by abfrage.aic_begriff,spalte.aic_abfrage having sum(reihenfolge)<>count(reihenfolge)*(count(reihenfolge)+1)/2",true);
		for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
                  if(bVollInfo)
			s+="Reihenfolge korrigiert bei:"+g.TabBegriffe.getBezeichnungS(Tab.getI("Aic_begriff"))+"\n";
			int i=0;
			Tabellenspeicher Tab2=new Tabellenspeicher(g,"select aic_spalte,reihenfolge from spalte where aic_abfrage="+Tab.getI("aic_abfrage")+" order by reihenfolge",true);
			for(Tab2.moveFirst();!Tab2.eof();Tab2.moveNext())
			{
				i++;
				if (i != Tab2.getI("Reihenfolge"))
				{
                                  if(bVollInfo)
					s+=Tab2.getI("reihenfolge")+" -> "+i+"\n";
					g.exec("update spalte set reihenfolge="+i+" where aic_spalte="+Tab2.getI("aic_spalte"));
				}
			}
		}
		if (Tab.isEmpty() && bVollInfo)
			s+="Keine Reihenfolge zu ändern!\n";

                Tab=new Tabellenspeicher(g,"select distinct abfrage.aic_begriff,spalte.aic_abfrage,nummer from spalte"+g.join("abfrage","spalte")+" group by abfrage.aic_begriff,spalte.aic_abfrage,nummer having count(*)>1",true);
                for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                {
                        int iPos=g.TabBegriffe.getPos("Aic",Tab.getI("Aic_Begriff"));
                        s+="Abfrage "+g.TabBegriffe.getS(iPos,"Bezeichnung")+"("+(g.TabBegriffe.getI(iPos,"Erf")>0?"Bewegung "+g.TabErfassungstypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Erf")):
                                "Stammtyp "+g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt")))+") enthält Spalten mit doppelten Nummern!\n";
                        //iFehler++;
                        Tabellenspeicher Tab2=new Tabellenspeicher(g,"select aic_spalte,Kennung,(select count(*) from befehl where aic_spalte=spalte.aic_spalte) Anzahl from spalte where aic_abfrage="+Tab.getI("aic_abfrage")+" and nummer="+Tab.getI("Nummer"),true);
                        for(Tab2.moveNext();!Tab2.eof();Tab2.moveNext())
                          if (Tab2.getI("Anzahl")>0)
                          {
                            SQL Qry2=new SQL(g);
                            s+="Spalte "+Tab2.getS("kennung")+" ("+Tab2.getI("aic_spalte")+") von Abfrage "+g.TabBegriffe.getBezeichnungS(Tab.getI("aic_begriff"))+" ("+Tab.getI("aic_begriff")+
                                ") kommt in Modell "+Qry2.list("begriff.defbezeichnung","begriff"+g.join2("modell","begriff")+",befehl where modell.aic_modell=befehl.aic_modell and aic_spalte="+Tab2.getI("aic_spalte"))+" vor!\n";
                            Qry2.free();
                            iFehler++;
                          }
                          else
                          {
                            g.exec("delete from SPALTE_BERECHNUNG where aic_spalte="+Tab2.getI("aic_spalte"));
                            g.exec("delete from SPALTE_BERECHNUNG where spa_aic_spalte="+Tab2.getI("aic_spalte"));
                            g.exec("delete from spalte_zuordnung where aic_spalte="+Tab2.getI("aic_spalte"));
                            g.exec("delete from fixeigenschaft where aic_spalte="+Tab2.getI("aic_spalte"));
                            g.exec("delete from spalten2 where aic_spalte="+Tab2.getI("aic_spalte"));
                            g.exec("delete from spalte where aic_spalte="+Tab2.getI("aic_spalte"));
                            s+="-> Spalte "+Tab2.getS("kennung")+" ("+Tab2.getI("aic_spalte")+") gelöscht!\n";
                            iAnz++;
                          }
                }
                if (Tab.isEmpty() && bVollInfo)
                        s+="Keine Abfrage mit doppelten Spalten-Nummern!\n";

		Tab=new Tabellenspeicher(g,"select * from (select aic_abfrage,begriff.defbezeichnung"+g.AU_Bezeichnung2("Begriff")+" Bezeichnung,spalten,(select max(nummer) from spalte where aic_abfrage=abfrage.aic_abfrage) Anzahl from begriff"+g.join2("abfrage","begriff")+") x where Anzahl>spalten",true);
		for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			s+=Static.beiLeer(Tab.getS("Bezeichnung"),Tab.getS("defbezeichnung"))+": Spaltenanzahl von "+Tab.getI("Spalten")+" -> "+Tab.getI("Anzahl")+"\n";
			g.exec("update Abfrage set Spalten="+Tab.getI("Anzahl")+" where aic_abfrage="+Tab.getI("aic_abfrage"));
		}
		if (Tab.isEmpty() && bVollInfo)
			s+="Keine Spaltenanzahl richtigzustellen!\n";
                s=execInfo2("update spalte_zuordnung set aic_stammtyp=(select aic_stammtyp from stamm where aic_stamm=spalte_zuordnung.aic_stamm) where aic_stammtyp is null and aic_stamm is not null",
                            "Stammtypen bei Spalte_Zuordnung",g,s,bVollInfo);

		Tab=new Tabellenspeicher(g,"select * from (select aic_modell,(select count(*) from befehl2 where aic_modell=modell.aic_modell) Anzahl from modell) x where Anzahl=0",true);
		if (!Tab.isEmpty())
		{
			iAnz=0;
			for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
			{
				int iModell=Tab.getI("aic_modell");
                                if (iModell>0)
                                {
                                  g.progInfo("lösche Modell:" + iModell);
                                  String sModell = SQL.getString(g, "select defbezeichnung from begriff" + g.join2("modell", "begriff") + " where aic_modell=" + iModell);
                                  int iDel = g.exec("update abfrage set aic_modell=null where aic_modell=" + iModell);
                                  iDel += g.exec("update abfrage set mod_aic_modell=null where mod_aic_modell=" + iModell);
                                  if (iDel > 0)
                                    s += " Modell " + sModell + " aus " + iDel + " Abfrage(n) gelöscht\n";
                                  g.exec("delete from befehl where aic_modell=" + iModell);
                                  g.exec("delete from befehl where mod_aic_modell=" + iModell);
                                  g.exec("update formular set aic_modell=null where aic_modell="+iModell);
                                  if (g.exec("delete from modell where aic_modell=" + iModell) > -2)
                                  {
                                    iAnz++;
                                    s += " Modell " + sModell + " gelöscht\n";
                                  }
                                  else
                                  {
                                    s += " Modell " + sModell + " nicht löschbar!\n";
                                    iFehler++;
                                  }
                                }
			}
			if (bVollInfo)
				s+=iAnz+" leere Modelle gelöscht\n";
		}

		Tab=new Tabellenspeicher(g,"select begriff.defbezeichnung,begriff.aic_begriff from modell right outer"+g.join("begriff","modell")+g.join("begriffgruppe","begriff")+" where begriffgruppe.kennung='Modell' and aic_modell is null",true);
		if (!Tab.isEmpty())
		{
			iAnz=0;
			String s2="";
			for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
			{
				int iBeg=Tab.getI("aic_begriff");
				g.progInfo("lösche Modell-Begriff:"+iBeg);
				g.exec("delete from Fehler where aic_begriff="+iBeg);
				g.exec("delete from verlauf where aic_begriff="+iBeg);
				g.exec("delete from defverlauf where aic_begriff="+iBeg);
				g.exec("delete from begriff_zuordnung where beg_aic_begriff="+iBeg);
                g.exec("delete from darstellung where aic_begriff="+iBeg);
                g.exec("delete from bew_begriff where aic_begriff="+iBeg);
                g.exec("update spalte set beg_aic_begriff=null where beg_aic_begriff="+iBeg);
                g.exec("update spalte set modell_begriff=null where modell_begriff="+iBeg);
                g.exec("delete from status_zuordnung where aic_begriff="+iBeg);
				if (g.exec("delete from begriff where aic_begriff="+Tab.getI("aic_begriff"))>-2)
					iAnz++;
				else
				{
					s2+=Tab.getS("defbezeichnung")+"! ";
					iFehler++;
				}
			}
			if(bVollInfo || !s2.equals(""))
				s+=iAnz+" defekte Modelle gelöscht, nicht gelöscht: "+s2+"\n";
		}

		Tab=new Tabellenspeicher(g,"select begriff.defbezeichnung,begriff.aic_begriff from abfrage right outer"+g.join("begriff","abfrage")+g.join("begriffgruppe","begriff")+" where begriffgruppe.kennung='Abfrage' and aic_abfrage is null",true);
		if (!Tab.isEmpty())
		{
			iAnz=0;
			//String s2="";
			for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
			{
				int iBegriff=Tab.getI("aic_begriff");
				g.exec("delete from Fehler where aic_begriff="+iBegriff);
				g.exec("delete from verlauf where aic_begriff="+iBegriff);
				g.exec("delete from defverlauf where aic_begriff="+iBegriff);
				String s2=Qry.list("defbezeichnung","darstellung d"+g.join("formular","d")+g.join("begriff","formular")+" where d.aic_begriff="+iBegriff);
				if (!s2.equals(" "))
				{
					//s2+=Tab.getS("Kennung")+"! ";
					s+="defekte Abfrage "+Tab.getS("defbezeichnung")+" kommt in Formular "+s2+" vor!\n";
					iFehler++;
				}
				else if (g.exec("delete from begriff where aic_begriff="+iBegriff)>-2)
                                {
                                  g.progInfo("lösche Abfrage:"+iBegriff);
                                  iAnz++;
                                }
				else
				{
					//s2+=Tab.getS("Kennung")+"! ";
					s+="Fehler beim löschen der Abfrage "+Tab.getS("defbezeichnung")+"!\n";
					iFehler++;
				}
			}
			if(bVollInfo)
				s+= iAnz+" defekte Abfragen gelöscht\n";
		}

		//iAnz=SQL.getInteger(g,"select count(*) from formular left outer join darstellung where aic_darstellung is null");
		//if (iAnz>0)
		//{
			iAnz=Qry.deleteFrom("formular","begriff"+g.join2("formular","begriff")+" left outer"+g.join2("darstellung","formular")+" where aic_darstellung is null and web is null"/*+g.bitis("bits", Formular.cstTemplate, 0)*/);
			if (bVollInfo && iAnz>0)
				s+=iAnz+" leere Formulare gelöscht\n";
		//}

		Tab=new Tabellenspeicher(g,"select begriff.defbezeichnung,begriff.aic_begriff from formular right outer"+g.join("begriff","formular")+g.join("begriffgruppe","begriff")+" where begriffgruppe.kennung='Frame' and aic_formular is null",true);
		if (!Tab.isEmpty())
		{
			iAnz=0;
			String s2="";
			for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
			{
				int iBegriff=Tab.getI("aic_begriff");
				g.progInfo("lösche Formular:"+iBegriff);
				g.exec("update mandant set aic_begriff=null where aic_begriff="+iBegriff);
                                g.exec("update benutzergruppe set aic_begriff=null where aic_begriff="+iBegriff);
				g.exec("delete from begriff_zuordnung where aic_begriff="+iBegriff);
				g.exec("delete from begriff_zuordnung where beg_aic_begriff="+iBegriff);
				g.exec("delete from fensterpos where aic_begriff="+iBegriff);
				g.exec("delete from Fehler where aic_begriff="+iBegriff);
				g.exec("delete from verlauf where aic_begriff="+iBegriff);
				g.exec("delete from defverlauf where aic_begriff="+iBegriff);
				if (g.exec("delete from begriff where aic_begriff="+iBegriff)>-2)
					iAnz++;
				else
				{
					s2+=Tab.getS("defbezeichnung")+"! ";
					iFehler++;
				}
			}
			if(bVollInfo || !s2.equals(""))
				s+=iAnz+" defekte Formulare gelöscht, nicht gelöscht: "+s2+"\n";
		}

		Tab=new Tabellenspeicher(g,"select * from (select Begriffgruppe.kennung Gruppe,Begriff.kennung,count(*) Anzahl from begriff"+g.join("begriffgruppe","begriff")+" group by Begriffgruppe.kennung,Begriff.kennung"+(g.MS()?",len(Begriff.kennung+'*')":"")+") x where Anzahl>1 and kennung is not null",true);
                //Tab.showGrid();
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
                  if (bVollInfo)
			s+=Tab.getS("Gruppe")+" <"+Tab.getS("Kennung")+"> doppelt!\n";
			//iFehler++;
		}
		if (Tab.isEmpty() && bVollInfo)
			s+="Keine Begriffe mit doppelter Kennung!\n";

                s=execInfo("delete from bew_boolean where aic_eigenschaft="+g.iTimerSperre,"Sperre(n)",g,s,bVollInfo);
                s=execInfo("delete from sperre","Planung-Sperren",g,s,bVollInfo);
                s=execInfo("update bew_pool set bewSperre=null where BewSperre is not null","Bew_Pool-Sperren",g,s,bVollInfo);
                String sWhereEigTod=" where aic_eigenschaft in (select aic_eigenschaft from eigenschaft where tod=1)";
                s=execInfo("delete from rolle_zuordnung"+sWhereEigTod,"Rolle-Zuordnungen",g,s,bVollInfo);
                s=execInfo("delete from stammtyp_zuordnung"+sWhereEigTod,"Stt-Zuordnungen",g,s,bVollInfo);
                s=execInfo("delete from bew_zuordnung"+sWhereEigTod,"Bew-Zuordnungen",g,s,bVollInfo);
                /*if (g.MySQL())
                {
                  int i0=g.exec("update schrift set bold=null where bold=0")+
                      g.exec("update schrift set italic=null where italic=0")+
                      g.exec("update befehl set hide=null where hide=0")+
                      g.exec("update fixeigenschaft set richtung=null where richtung=0");
                  if (i0>0 && bVollInfo)
                    s+=i0+" Werte von 0 auf null geändert\n";
                }*/
                //s=execInfo2("update abfrage set aic_benutzergruppe=("+SQL.top("aic_benutzergruppe from benutzer_zuordnung where aic_benutzer=abfrage.aic_benutzer",1)+")"+
                //          " where aic_benutzer is not null and aic_benutzergruppe is null","Abfrage-Benutzergruppen",g,s,bVollInfo);
                Qry.free();
        Tabellenspeicher TabR=new Tabellenspeicher(g,"select r.aic_stammtyp,z.* from rolle r join rolle_zuordnung z on r.aic_rolle=z.aic_rolle where (select aic_eigenschaft from stammtyp_zuordnung where r.aic_stammtyp=aic_stammtyp and z.aic_eigenschaft=aic_eigenschaft) is null",true);
        int iAnzR=0;
        for (TabR.moveFirst();!TabR.out();TabR.moveNext())
        	iAnzR+=g.exec("delete from rolle_zuordnung where aic_rolle="+TabR.getI("aic_rolle")+" and aic_eigenschaft="+TabR.getI("aic_eigenschaft"));
        s+=iAnzR+" von Rolle_Zuordnung entfernt\n";
		bFehler=iFehler>0;
		if(bVollInfo)
		{
			if(bFehler)
				s+="-> "+iFehler+" Fehler sind aufgetreten !!!\n";
                        /*Parameter Para = new Parameter(g,"Datenbank");
                        Para.setParameter("Update","",bFehler?-1:0,0,0,0,false,false);
                        Para.free();*/
                        if (iPU>0)
                          g.exec("Update parameter set "+g.int1()+"="+(bFehler?-1:0)+" where aic_parameter="+iPU);
			//SQL.exec(g,"Update Hauptgruppe H"+g.join("Parameter","H","Hauptgruppe")+g.join("Nebengruppe","N","Parameter","Nebengruppe")+" set int1="+(bFehler?"-1":"0")+" where h.kennung='Datenbank' and n.kennung='Update'");
		}
		return s;
	}

        public static String DelDrucke(Global g,boolean bVollInfo)
        {
          String s="";
          Vector<Integer> VecD=SQL.getVector("select begriff.aic_begriff from begriff left outer join druck_zuordnung z on begriff.aic_begriff=z.aic_begriff where z.aic_begriff is null and aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck"),g);
          g.progInfo("VecD="+VecD);
          s=execInfo("delete from darstellung where "+g.in("aic_begriff",VecD),"Formular-Zeilen",g,s,bVollInfo);
          s=execInfo("delete from begriff_Zuordnung where "+g.in("aic_begriff",VecD),"Zuordnung1",g,s,bVollInfo);
          s=execInfo("delete from begriff_Zuordnung where "+g.in("beg_aic_begriff",VecD),"Zuordnung2",g,s,bVollInfo);
          s=execInfo("delete from begriff where "+g.in("aic_begriff",VecD),"Drucke",g,s,bVollInfo);
          SQL Qry=new SQL(g);
          s=execInfo(Qry,"abschnitt","abschnitt left outer join druck_zuordnung z on abschnitt.aic_abschnitt=z.aic_abschnitt where z.aic_abschnitt is null","Abschnitt",g,s,false);
          Qry.free();
          s=execInfo("update begriff set tod=1 where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+" and "+g.bit("bits",Abfrage.cstAuswertung)+
                     " and (select count(*) from abschnitt where aic_begriff=begriff.aic_begriff)=0"+
        		     " and (select count(aic_befehl) from befehl2 b2 join spalte s on b2.aic_spalte=s.aic_spalte join abfrage a on s.aic_abfrage=a.aic_abfrage where a.aic_begriff=begriff.aic_begriff)=0"+
                     " and (select count(*) from darstellung where aic_begriff=begriff.aic_begriff)=0 and Tod is null","Druck-Abfragen",g,s,bVollInfo);
          s=execInfo2("update begriff set tod=null where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+" and "+g.bit("bits",Abfrage.cstAuswertung)+
                  " and (select count(*) from abschnitt where aic_begriff=begriff.aic_begriff)+"+
     		      " (select count(aic_befehl) from befehl2 b2 join spalte s on b2.aic_spalte=s.aic_spalte join abfrage a on s.aic_abfrage=a.aic_abfrage where a.aic_begriff=begriff.aic_begriff)+"+
                  " (select count(*) from darstellung where aic_begriff=begriff.aic_begriff)>0 and Tod=1","Druck-Abfragen",g,s,bVollInfo);
          return s;
        }

        public static String DelAbfrage(Global g,boolean bVollInfo)
        {
          String s="";
          // Abfragen (Begriff) der Module
          Vector<Integer> VecAbf=SQL.getVector("select distinct b1.aic_begriff from begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.beg_aic_begriff and b1.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+
                                             " join begriff b on b.aic_begriff=z.aic_begriff and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"),g);
          // Drucke der Module
          Vector<Integer> VecDrucke=SQL.getVector("select distinct b1.aic_begriff from begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.beg_aic_begriff and b1.tod is null and b1.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+
                                                " join begriff b on b.aic_begriff=z.aic_begriff and b.ende is null and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"),g);
          g.progInfo("VecDrucke="+VecDrucke);
          // Abfragen (Begriff) der Drucke
          Static.addVectorI(VecAbf,SQL.getVector("select distinct a.aic_begriff from abschnitt a join druck_zuordnung z on a.aic_abschnitt=z.aic_abschnitt where z.aic_begriff"+Static.SQL_in(VecDrucke),g));
          // Abfragen (Begriff) der Benutzer-Berechtigung
          Static.addVectorI(VecAbf,SQL.getVector("select distinct b2.aic_begriff from berechtigung b1 join begriff b2 on b1.aic_fremd=b2.aic_begriff and aic_tabellenname="+
                                             Global.iTabBegriff+" where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage"),g));
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,defbezeichnung from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+
                                                    " and (tod is null or tod=0) and kennung is not null and "+g.bit("bits",Abfrage.cstExportierbar)+(VecAbf.size()==0?"":" and aic_begriff not"+Static.SQL_in(VecAbf)),true);
          Vector Vec=Tab.getVecSpalte("aic_begriff");
          if(Vec!=null && Vec.size() > 0)
          {
            g.exec("delete from begriff_zuordnung where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from begriff_zuordnung where beg_aic_begriff" + Static.SQL_in(Vec));
            //g.exec("delete from darstellung where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from verlauf where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from fehler where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from fensterpos where aic_begriff" + Static.SQL_in(Vec));
            int iAnz = g.exec("update begriff set tod=1 where Tod is null and aic_begriff" + Static.SQL_in(Vec));
            g.diskInfo("folgende Abfragen gelöscht:"+Tab.getVecSpalte("defbezeichnung"));
            if(bVollInfo && iAnz > 0)
              s += "\n"+iAnz + " nicht verwendete Abfragen gelöscht\n";
          }
          return s;
        }

        public static String DelFrame(Global g,boolean bVollInfo)
        {
          String s="";
          int iBG_Frm=g.TabBegriffgruppen.getAic("Frame");
          Vector<Integer> VecHS=SQL.getVector("select b.aic_begriff from begriff b join code c on b.aic_code=c.aic_code and c.kennung='System' where b.aic_begriffgruppe="+iBG_Frm,g);
          int iAnz = g.exec("update begriff set tod=null where Tod=1 and aic_begriff" + Static.SQL_in(VecHS));
          if (iAnz>0)
        	  g.diskInfo(iAnz+" System-Formulare wieder aktiviert");
          Static.addVectorI(VecHS,SQL.getVector("select distinct b1.aic_begriff from begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.beg_aic_begriff and b1.aic_begriffgruppe="+iBG_Frm+
                                             " join begriff b on b.aic_begriff=z.aic_begriff and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"),g));
          /*Static.addVectorI(VecHS,SQL.getVector("select distinct b2.aic_begriff from berechtigung b1 join begriff b2 on b1.aic_fremd=b2.aic_begriff and aic_tabellenname="+
                                             g.iTabBegriff+" where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame"),g));*/
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,defbezeichnung from begriff where aic_begriffgruppe="+iBG_Frm+
                                                    " and (jeder is null or jeder=0) and (tod is null or tod=0)"+(VecHS.size()==0?"":" and aic_begriff not"+Static.SQL_in(VecHS)),true);
          Vector Vec=Tab.getVecSpalte("aic_begriff");
          if(Vec!=null && Vec.size() > 0)
          {
            g.exec("delete from berechtigung where aic_tabellenname="+g.iTabBegriff+" and aic_fremd" + Static.SQL_in(Vec));
            g.exec("delete from begriff_zuordnung where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from begriff_zuordnung where beg_aic_begriff" + Static.SQL_in(Vec));
            //g.exec("delete from darstellung where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from verlauf where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from fehler where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from fensterpos where aic_begriff" + Static.SQL_in(Vec));
            iAnz = g.exec("update begriff set tod=1 where Tod is null and aic_begriff" + Static.SQL_in(Vec));
            g.diskInfo("folgende Formulare gelöscht:"+Tab.getVecSpalte("defbezeichnung"));
            if(bVollInfo && iAnz > 0)
              s += "\n"+iAnz + " nicht verwendete Formulare gelöscht\n";
          }
          return s;
        }

        public static String DelModell(Global g,boolean bVollInfo)
        {
          String s="";
          Vector<Integer> VecModell=SQL.getVector("select distinct b1.aic_begriff from begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.beg_aic_begriff and b1.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell")+
                                             " join begriff b on b.aic_begriff=z.aic_begriff and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"),g);
          Static.addVectorI(VecModell,SQL.getVector("select distinct b1.aic_begriff from begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.beg_aic_begriff and b1.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell")+
                                             " join begriff b on b.aic_begriff=z.aic_begriff and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck"),g));
          /*Static.addVectorI(VecModell,SQL.getVector("select distinct b2.aic_begriff from berechtigung b1 join begriff b2 on b1.aic_fremd=b2.aic_begriff and aic_tabellenname="+
                                             g.iTabBegriff+" where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell"),g));*/
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,defbezeichnung from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell")+
                                                    " and (jeder is null or jeder=0) and (tod is null or tod=0)"+(VecModell.size()==0?"":" and aic_begriff not"+Static.SQL_in(VecModell)),true);
          Vector Vec=Tab.getVecSpalte("aic_begriff");
          if(Vec!=null && Vec.size() > 0)
          {
            g.exec("delete from berechtigung where aic_tabellenname="+g.iTabBegriff+" and aic_fremd" + Static.SQL_in(Vec));
            g.exec("delete from begriff_zuordnung where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from begriff_zuordnung where beg_aic_begriff" + Static.SQL_in(Vec));
            //g.exec("delete from darstellung where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from verlauf where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from fehler where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from fensterpos where aic_begriff" + Static.SQL_in(Vec));
            int iAnz = g.exec("update begriff set tod=1 where Tod is null and aic_begriff" + Static.SQL_in(Vec));
            g.diskInfo("folgende Modelle gelöscht:"+Tab.getVecSpalte("defbezeichnung"));
            if(bVollInfo && iAnz > 0)
              s += "\n"+iAnz + " nicht verwendete Modelle gelöscht\n";
          }
          return s;
        }

        public static String DelHS(Global g,boolean bVollInfo)
        {
          String s="";
          Vector<Integer> VecHS=SQL.getVector("select distinct b1.aic_begriff from begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.beg_aic_begriff and b1.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Applikation")+
                                             " join begriff b on b.aic_begriff=z.aic_begriff and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"),g);
          Static.addVectorI(VecHS,SQL.getVector("select distinct b2.aic_begriff from berechtigung b1 join begriff b2 on b1.aic_fremd=b2.aic_begriff and aic_tabellenname="+
                                             g.iTabBegriff+" where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Applikation"),g));
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,defbezeichnung from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Applikation")+
                                                    " and (tod is null or tod=0)"+(VecHS.size()==0?"":" and aic_begriff not"+Static.SQL_in(VecHS)),true);
          Vector Vec=Tab.getVecSpalte("aic_begriff");
          if(Vec!=null && Vec.size() > 0)
          {
            g.exec("delete from ansicht where aic_hauptschirm in (select aic_hauptschirm from hauptschirm where aic_begriff" + Static.SQL_in(Vec)+")");
            g.exec("delete from hauptschirm where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from applikation_zuordnung where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from begriff_zuordnung where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from begriff_zuordnung where beg_aic_begriff" + Static.SQL_in(Vec));
            //g.exec("delete from darstellung where aic_begriff" + Static.SQL_in(Vec));
            //g.exec("delete from defverlauf where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from verlauf where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from fehler where aic_begriff" + Static.SQL_in(Vec));
            g.exec("delete from fensterpos where aic_begriff" + Static.SQL_in(Vec));
            int iAnz = g.exec("update begriff set tod=1 where Tod is null and aic_begriff" + Static.SQL_in(Vec));
            g.diskInfo("folgende Applikation gelöscht:"+Tab.getVecSpalte("defbezeichnung"));
            if(bVollInfo && iAnz > 0)
              s += "\n"+iAnz + " nicht verwendete Applikationen gelöscht\n";
          }
          return s;
        }

        public static String DelEig(Global g,boolean bVollInfo)
        {
          String s="";
          Vector<Integer> VecEig=SQL.getVector("select distinct aic_fremd from modul where aic_tabellenname="+g.TabTabellenname.getAic("EIGENSCHAFT"),g);
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_eigenschaft,kennung from eigenschaft where"+g.bits("bits",g.cstJeder)+"=0 and kennung!='Kennung'"+
                                                    " and (tod is null or tod=0)"+(VecEig.size()==0?"":" and aic_eigenschaft not"+Static.SQL_in(VecEig)),true);
          g.progInfo("VecEig="+VecEig);
          if (g.Prog())
            Tab.showGrid("Eigenschaften löschen");
          Vector Vec=Tab.getVecSpalte("aic_eigenschaft");
          if(Vec!=null && Vec.size() > 0)
          {
            g.exec("delete from eigenschaft_zuordnung where aic_eigenschaft" + Static.SQL_in(Vec));
            g.exec("delete from bew_zuordnung where aic_eigenschaft" + Static.SQL_in(Vec));
            g.exec("delete from rolle_zuordnung where aic_eigenschaft" + Static.SQL_in(Vec));
            int iAnz = g.exec("update eigenschaft set tod=1 where aic_eigenschaft" + Static.SQL_in(Vec));
            g.diskInfo("folgende Eigenschaften gelöscht:"+Tab.getVecSpalte("kennung"));
            if(bVollInfo && iAnz > 0)
              s += "\n"+iAnz + " nicht verwendete eigenschaften gelöscht\n";
          }
          return s;
        }

        public static String DelRolle(Global g,boolean bVollInfo)
        {
          String s="";
          if (!g.ApplPort() && !g.BasisPort())
          {
            Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_rolle,kennung from Rolle where Tod=1",true);
            if (Tab.size()>0)
            {
              int iAnz = g.exec("delete from rolle_zuordnung where aic_rolle"+Static.SQL_in(Tab.getVecSpalte("aic_rolle")));
              if (iAnz > 0) g.diskInfo(iAnz + " Rolle_Zuordnung gelöscht");
              g.exec("update rolle set rol_aic_rolle=null where aic_rolle"+Static.SQL_in(Tab.getVecSpalte("aic_rolle")));
              Vector<Integer> VecBeg=SQL.getVector("select aic_begriff from begriff where aic_rolle"+Static.SQL_in(Tab.getVecSpalte("aic_rolle")),g);
              if (VecBeg.size()>0)
              {
                g.exec("update Begriff set tod=1 where aic_Begriff" + Static.SQL_in(VecBeg));
                g.exec("update Begriff set aic_rolle=null where aic_Begriff" + Static.SQL_in(VecBeg));
              }
              iAnz = g.exec("delete from Rolle where Tod=1");
              if (iAnz > 0) g.diskInfo("folgende Rollen gelöscht:"+Tab.getVecSpalte("kennung"));
              if (bVollInfo && iAnz > 0)
                s += "\n" + iAnz + " inaktive Rollen gelöscht\n";
            }
          }
          return s;
        }

        public static String DelStt(Global g,boolean bVollInfo)
        {
          String s="";
          if (!g.ApplPort() && !g.BasisPort())
          {
            Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_stammtyp,kennung from Stammtyp where Tod=1",true);
            if (Tab.size()>0)
            {
              String sInStt=Static.SQL_in(Tab.getVecSpalte("aic_Stammtyp"));
              int iAnz = g.exec("delete from Stammtyp_zuordnung where aic_Stammtyp"+sInStt);
              if (iAnz > 0) g.diskInfo(iAnz + " Stammtyp_Zuordnung gelöscht");
              g.exec("delete from fensterpos where aic_Stammtyp"+sInStt);
              Vector<Integer> VecStamm=SQL.getVector("select aic_stamm from stamm where aic_Stammtyp"+sInStt,g);
              if (VecStamm.size()>0)
              {
                iAnz = g.exec("delete from stamm_protokoll where aic_stamm"+Static.SQL_in(VecStamm));
                if (iAnz > 0) g.diskInfo(iAnz + " stamm_protokoll gelöscht");
                iAnz = g.exec("delete from stammpool where aic_stamm"+Static.SQL_in(VecStamm));
                iAnz += g.exec("delete from stammpool where sta_aic_stamm"+Static.SQL_in(VecStamm));
                if (iAnz > 0) g.diskInfo(iAnz + " stammpool gelöscht");
                g.exec("delete from verlauf where aic_stamm"+Static.SQL_in(VecStamm));
                iAnz = g.exec("delete from stamm where aic_stamm"+Static.SQL_in(VecStamm));
                if (iAnz > 0) g.diskInfo(iAnz + " stamm gelöscht");
              }
              g.exec("update Eigenschaft set tod=1 where aic_Stammtyp"+sInStt);
              iAnz = g.exec("update Eigenschaft set aic_Stammtyp=null where aic_Stammtyp"+sInStt);
              if (iAnz > 0) g.diskInfo(iAnz + " Eigenschaften deaktiviert");
              g.exec("update Begriff set tod=1 where aic_Stammtyp"+sInStt);
              iAnz = g.exec("update Begriff set aic_Stammtyp=null where aic_Stammtyp"+sInStt);
              if (iAnz > 0) g.diskInfo(iAnz + " Begriffe deaktiviert");
              iAnz = g.exec("delete from Stammtyp where Tod=1");
              if (iAnz > 0) g.diskInfo("folgende Stammtypen gelöscht:"+Tab.getVecSpalte("kennung"));
              if (bVollInfo && iAnz > 0)
                s += "\n" + iAnz + " inaktive Stammtypen gelöscht\n";
            }
          }
          return s;
        }
        
        public static void clearHistory(Global g)
        {
        	if (g.bTestPC)
        	{
        		// TODO History löschen
        		g.exec("delete from stammpool where pro2_aic_protokoll is not null");
        		g.exec("delete from stamm_protokoll where pro_aic_protokoll is not null");
        	}
        	else
        		Static.printError("Reinigung.clearHistory nur auf Test-Datenbanken erlaubt");
        }
        
        public static void replaceName(Global g,int iStt)
        {
        	if (iStt<0)
        		Static.printError("Reinigung.replaceName ohne Stammtyp nicht möglich");
        	else if (g.bTestPC)
        	{
        		g.exec("update stamm_protokoll set bezeichnung=aic_stamm where aic_stammtyp2="+iStt);
        	}
        	else
        		Static.printError("Reinigung.replaceName nur auf Test-Datenbanken erlaubt");
        }

        private static String DelModule(Global g,boolean bVollInfo)
        {
          //String s="";
          if (!g.ApplPort() && !g.BasisPort())
          {
            //if (bVollInfo)
            //  s+= "\nModule bereinigen:\n";
            Vector<Integer> VecProg=SQL.getVector("select distinct c.aic_code from lizenz join begriff b on aic_fremd=b.aic_begriff join code c on b.prog=c.aic_code"+
                            " and aic_tabellenname="+g.iTabBegriff,g);
            g.fixInfo("VecProg="+VecProg);
            Vector<Integer> VecModul=SQL.getVector("select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul")+(VecProg.size()==0 ? "":
                " and (prog is null or prog not"+Static.SQL_in(VecProg)+")"),g);
            String sWhere=" where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Programm")+(VecProg.size()==0 ? "":" and aic_code not"+Static.SQL_in(VecProg));
            int iAnzProg=SQL.getInteger(g,"select count(*) from code"+sWhere);
            if (iAnzProg>0 || VecModul.size()>0)
            {
              if (bVollInfo)
              {
                Static.clearError();
                g.start();
              }
              String s="<html>";
              if (VecModul.size() > 0)
              {
                //int iAnz = g.exec("delete from begriff_zuordnung where"+g.in("aic_begriff",VecModul)+" or"+g.in("beg_aic_begriff",VecModul));
                //if (iAnz > 0)g.diskInfo(iAnz+" begriff_zuordnungen gelöscht");
                s+="<p><b>Module zum löschen:</b><br>";
                Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,kennung,defbezeichnung from begriff where"+g.in("aic_begriff",VecModul),true);
                for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                  s+=Tab.getS("defbezeichnung")+" (" + Tab.getI("aic_begriff") + ","+Tab.getS("Kennung")+")<br>";
                g.exec("delete from begriff_zuordnung where" + g.in("aic_begriff", VecModul) + " or" + g.in("beg_aic_begriff", VecModul));//, "Begriff_Zuordnungen", g, s,bVollInfo);
                g.exec("delete from modul where" + g.in("aic_begriff", VecModul));//, "Modul_Zuordnungen", g, s, bVollInfo);
                g.exec("delete from defverlauf where" + g.in("aic_begriff", VecModul));//, "DefVerlauf", g, s, bVollInfo);
                //iAnz = g.exec("delete from modul where"+g.in("aic_begriff",VecModul));
                //if (iAnz > 0)g.diskInfo(iAnz+" modul_zuordnungen gelöscht");
                s+= "Module: " + g.exec("delete from begriff where" + g.in("aic_begriff", VecModul)) + " gelöscht!<br>";
                //s = execInfo("delete from begriff where" + g.in("aic_begriff", VecModul), "Module", g, s, bVollInfo);
                //iAnz = g.exec("delete from begriff where"+g.in("aic_begriff",VecModul));
                //if (iAnz > 0)g.diskInfo(iAnz+" Module gelöscht");
                //if (bVollInfo && iAnz > 0)
                //  s += "\n" + iAnz + " Module gelöscht\n";
              }
              if (iAnzProg>0)
              {
                s+="<p><b>Programme zum löschen:</b><br>";
                Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_code,kennung"+g.AU_Bezeichnung("code")+" from code" + sWhere,true);
                VecProg.clear();
                for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                {
                	int iProg=Tab.getI("aic_code");
                  s+=Tab.getS("bezeichnung")+" (" + iProg + ","+Tab.getS("Kennung")+")<br>";
                  VecProg.addElement(iProg);
                  Vector<Integer> VecAT=SQL.getVector("select aic_abschlusstyp from abschlusstyp where prog="+iProg, g);
                  if (VecAT.size()>0)
                  {
                	  g.exec("delete from abschluss_zuordnung where aic_abschlusstyp"+Static.SQL_in(VecAT));
                	  g.exec("delete from abschluss where aic_abschlusstyp"+Static.SQL_in(VecAT));
                	  g.exec("delete from abschlussdefinition where aic_abschlusstyp"+Static.SQL_in(VecAT));
                	  s+=g.exec("delete from abschlusstyp where aic_abschlusstyp"+Static.SQL_in(VecAT))+" Abschlusstypen von "+Tab.getS("bezeichnung")+" entfernt<br>";
                  }
                }
                if (VecProg.size()>0)
                {
                  s+= g.exec("update begriff set prog=null where prog"+Static.SQL_in(VecProg))+" Begriffe kein Programm gesetzt<br>";
                  s+= "Programm(e): " + g.exec("delete from code where aic_code" + Static.SQL_in(VecProg)) + " gelöscht!<br>";
                }
                //s = execInfo("delete from code" + sWhere, "Programm(e):", g, s, bVollInfo);
              }
              s+="</html>";
              //if (bVollInfo)
              String s2=g.clearHtml(s.replaceAll("<p>","").replaceAll("<br>","\n"));
              if (bVollInfo)
                s = DurchfuehrenFrage(g,"Modul",s, null, bVollInfo);
              //g.fixtestInfo(s2);
              if (s.equals(Static.sLeer))
                s="\nModul-Reinigung abgebrochen\n";
              else
              {
                g.diskInfo(s2);
                s=(bVollInfo ? "\nModule bereinigen:\n":"")+s2;
              }
              return s;
            }
            else if (bVollInfo)
              return "\nkeine Module zu bereinigen\n";
          }
          return "";
        }

        @SuppressWarnings("unchecked")
        public static String DelOld(Global g,boolean bVollInfo,JFrame Fom)
        {
          String s="";
          if (!g.ApplPort() && !g.BasisPort())
            s+=DelModell(g,true);
          if (bVollInfo)
            s+= "\nAltes löschen:\n";
          Static.clearError();
          g.start();
          SQL Qry=new SQL(g);

          // Tod-Begriffe löschen
          Vector Vec=SQL.getVector("select aic_begriff from begriff where tod=1",g);
          if (!Vec.isEmpty())
          {
            //java.util.Vector Vec = Tab.getVecSpalte("aic_begriff");

            // alle
            Vector<Integer> VecN=SQL.getVector("select distinct aic_begriff from darstellung where "+g.in("aic_begriff",Vec),g);
            // Modell
            //SQL.addVector(VecN,"select distinct modell.aic_begriff from modell"+g.join2("abfrage","modell")+" where "+g.in("modell.aic_begriff",Vec),g);//
            s=execInfo("delete from bew_begriff where "+g.in("aic_begriff",Vec),"BEW_BEGRIFF",g,s,bVollInfo);
            //SQL.addVector(VecN,"select distinct aic_begriff from bew_begriff where "+g.in("aic_begriff",Vec),g);
            //SQL.addVector(VecN,"select distinct m1.aic_begriff from modell m1 join befehl b on m1.aic_modell=b.mod_aic_modell"+
            //              " join modell m2 on m2.aic_modell=b.aic_modell where "+g.in("m1.aic_begriff",Vec),g);
            // Abfrage
            s=execInfo("delete from druck_zuordnung where aic_abschnitt in (select aic_abschnitt from abschnitt where "+g.in("aic_begriff",Vec)+")","Druck_Zuordnungen",g,s,bVollInfo);
            s=execInfo("delete from abschnitt where "+g.in("aic_begriff",Vec),"Abschnitte",g,s,bVollInfo);
            SQL.addVector(VecN,"select distinct aic_begriff from abschnitt where "+g.in("aic_begriff",Vec),g);
            SQL.addVector(VecN,"select distinct a.aic_begriff from abfrage a join planung p on a.aic_abfrage=p.aic_abfrage where"+g.in("a.aic_begriff",Vec),g);
            SQL.addVector(VecN,"select distinct a.aic_begriff from abfrage a join planung p on a.aic_abfrage=p.abf_aic_abfrage where"+g.in("a.aic_begriff",Vec),g);
            SQL.addVector(VecN,"select distinct beg_aic_begriff from spalte where"+g.in("beg_aic_begriff",Vec),g);
            SQL.addVector(VecN,"select distinct aic_begriff from benutzergruppe where"+g.in("aic_begriff",Vec),g);
            //SQL.addVector(VecN,"select distinct abfrage.aic_begriff a from abfrage"+g.join2("spalte","abfrage")+g.join2("befehl","spalte")+
            //              " where "+g.in("abfrage.aic_begriff",Vec),g);//
            g.progInfo("Vec vorher:"+Vec);
            for(int i=0;i<VecN.size();i++)
              Vec.removeElement(VecN.elementAt(i));
            g.progInfo("Vec nachher:"+Vec);
            Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct m1.aic_begriff B1,m2.aic_begriff B2 from modell m1 join befehl2 b on m1.aic_modell=b.mod_aic_modell"+
                          " join modell m2 on m2.aic_modell=b.aic_modell where "+g.in("m1.aic_begriff",Vec),true);
            for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
              if (Vec.contains(Tab.getInhalt("B1")) && !Vec.contains(Tab.getInhalt("B2")))
              {
                Vec.removeElement(Tab.getInhalt("B1"));
                VecN.addElement(Tab.getInt("B1"));
                g.progInfo("Modell-Modell:"+Tab.getI("B1")+" - "+Tab.getInhalt("B2"));
              }
            Tab=new Tabellenspeicher(g,"select distinct abfrage.aic_begriff b1,modell.aic_begriff b2 from abfrage"+g.join2("spalte","abfrage")+g.join2("befehl2","spalte")+
                          " join modell on befehl2.aic_modell=modell.aic_modell where "+g.in("abfrage.aic_begriff",Vec),true);
            for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
              if (Vec.contains(Tab.getInhalt("B1")) && !Vec.contains(Tab.getInhalt("B2")))
              {
                Vec.removeElement(Tab.getInhalt("B1"));
                VecN.addElement(Tab.getInt("B1"));
                g.progInfo("Abfrage-Modell:"+Tab.getI("B1")+" - "+Tab.getInhalt("B2"));
              }
            s=execInfo("update abfrage set aic_modell=null where aic_modell in (select aic_modell from modell where "+g.in("modell.aic_begriff",Vec)+")","Modelle bei Abfragen",g,s,bVollInfo);
            s=execInfo("update abfrage set mod_aic_modell=null where mod_aic_modell in (select aic_modell from modell where "+g.in("modell.aic_begriff",Vec)+")","Modelle2 bei Abfragen",g,s,bVollInfo);
            Tab=new Tabellenspeicher(g,"select distinct modell.aic_begriff B1,abfrage.aic_begriff B2 from modell"+g.join2("abfrage","modell")+" where "+g.in("modell.aic_begriff",Vec),true);
            for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
              if (Vec.contains(Tab.getInhalt("B1")) && !Vec.contains(Tab.getInhalt("B2")))
              {
                Vec.removeElement(Tab.getInhalt("B1"));
                VecN.addElement(Tab.getInt("B1"));
                g.progInfo("Modell-Abfrage:" + Tab.getI("B1") + " - " + Tab.getInhalt("B2"));
              }
            if (Vec.size()>0)
            {
              s = execInfo("delete from begriff_zuordnung where " + g.in("aic_begriff", Vec), "Begriff_Zuordnung1", g, s, false); // alle
              s = execInfo("delete from begriff_zuordnung where " + g.in("beg_aic_begriff", Vec), "Begriff_Zuordnung2", g, s, false); // alle
              s = execInfo("delete from darstellung where aic_formular in (select aic_formular from formular where " +
                           g.in("aic_begriff", Vec) + ")", "Darstellung", g, s, false); // Formulare
              s = execInfo("delete from formular where " + g.in("aic_begriff", Vec), "Formular", g, s, false); // Formulare
              s = execInfo("delete from befehl where aic_modell in (select aic_modell from modell where " +
                           g.in("aic_begriff", Vec) + ")", "Befehle", g, s, false); // Modell
              s = execInfo("delete from gruppe_zuordnung where " + g.in("aic_begriff", Vec), "Gruppe_Zuordnung", g, s, false); // Gruppe
              s = execInfo("delete from druck_zuordnung where " + g.in("aic_begriff", Vec), "druck_zuordnung", g, s, false); // Druck
              s += DelDrucke(g, bVollInfo);
              java.util.Vector VecSpalte = SQL.getVector("select aic_spalte from spalte" + g.join("abfrage", "spalte") + " where " + g.in("abfrage.aic_begriff", Vec), g);
              if (VecSpalte.size() > 0) // Abfrage
              {
                s = execInfo("delete from spalte_zuordnung where " + g.in("aic_spalte", VecSpalte), "spalte_Zuordnung", g, s, false);
                s = execInfo("delete from spalte_berechnung where " + g.in("aic_spalte", VecSpalte), "spalte_berechnung", g, s, false);
                s = execInfo("delete from fixeigenschaft where " + g.in("aic_spalte", VecSpalte), "fixeigenschaft", g, s, false);
                s = execInfo("delete from spalten2 where " + g.in("aic_spalte", VecSpalte), "spalten2", g, s, false);
                s = execInfo("delete from befehl where " + g.in("aic_spalte", VecSpalte), "befehl", g, s, false);
                s = execInfo("delete from spalte where " + g.in("aic_spalte", VecSpalte), "spalte", g, s, false);
              }
              java.util.Vector VecBed = SQL.getVector("select aic_bedingung from bedingung" + g.join("abfrage", "bedingung") + " where " + g.in("abfrage.aic_begriff", Vec), g);
              if (VecBed.size() > 0)
              {
                s = execInfo("delete from fixeigenschaft where " + g.in("aic_bedingung", VecBed), "fixeigenschaft2", g, s, false);
                s = execInfo("delete from bedingung where " + g.in("aic_bedingung", VecBed), "bedingung", g, s, false);
              }
              s = execInfo("delete from Abfrage where " + g.in("aic_begriff", Vec), "Abfrage", g, s, false); // Abfrage
              s = execInfo("delete from befehl where def_aic_defverlauf is not null and mod_aic_modell in (select aic_modell from modell where " +
                           g.in("aic_begriff", Vec) + ")", "Submodelle", g, s, false); // SubModelle
              s = execInfo("delete from modell where " + g.in("aic_begriff", Vec), "Modell", g, s, false); // Modell
              s = execInfo("delete from darstellung where " + g.in("aic_begriff", Vec), "Darstellung2", g, s, false); // alle
              s = execInfo("delete from bew_begriff where " + g.in("aic_begriff", Vec), "bew_begriff", g, s, false); // alle
              s = execInfo("delete from fensterpos where " + g.in("aic_begriff", Vec), "fensterpos", g, s, false); // alle
              s = execInfo("delete from Fehler where " + g.in("aic_begriff", Vec), "Fehler", g, s, false); // alle
              s = execInfo("delete from verlauf where " + g.in("aic_begriff", Vec), "verlauf", g, s, false); // alle
              s = execInfo("delete from defverlauf where " + g.in("aic_begriff", Vec), "defverlauf", g, s, false); // alle
              s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Show") + " and " + g.in("aic_begriff", Vec), "Show", g, s, false); // Show
              s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Checkbox") + " and " + g.in("aic_begriff", Vec), "Checkbox", g, s, false); // Checkbox
              s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Gruppe") + " and " + g.in("aic_begriff", Vec), "Gruppe", g, s, false); // Gruppe
              s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Abfrage") + " and " + g.in("aic_begriff", Vec), "Begriff-Abfrage", g, s, false); // Abfrage
              s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Modell") + " and " + g.in("aic_begriff", Vec), "Begriff-Modell", g, s, false); // Modell
              s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Frame") + " and " + g.in("aic_begriff", Vec), "Begriff-Frame", g, s, false); // Frame
              s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Button") + " and " + g.in("aic_begriff", Vec), "Button", g, s, false); // Button
              s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Druck") + " and " + g.in("aic_begriff", Vec), "Druck", g, s, false); // Druck
              s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Web") + " and " + g.in("aic_begriff", Vec), "Web", g, s, false); // Web
              //s = execInfo("delete from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Applikation") + " and " + g.in("aic_begriff", Vec), "Applikation", g, s, false); // Applikation
//              g.fixtestError("Altes Bereinigt:"+s);
              s += "folgende Begriffe gelöscht:\n";
              for (int i = 0; i < Vec.size(); i++)
              {
                int iPos=g.TabBegriffe.getPos("Aic",Tabellenspeicher.geti(Vec.elementAt(i)));
                s += g.TabBegriffe.getS(iPos,"Bezeichnung") + " (" + g.TabBegriffe.getI(iPos,"Aic") + ","+g.TabBegriffe.getS(iPos,"Kennung")+"," + g.TabBegriffgruppen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Gruppe")) + ")\n";
              }
//              g.fixtestError("fertig Vec.size>0");
            }
            if (VecN.size()>0)
            {
              s+="\nnicht gelöscht:\n";
              for(int i=0;i<VecN.size();i++)
              {
                int iPos=g.TabBegriffe.getPos("Aic",Tabellenspeicher.geti(VecN.elementAt(i)));
                s += g.TabBegriffe.getS(iPos,"Bezeichnung") + " (" + g.TabBegriffe.getI(iPos,"Aic") + ","+g.TabBegriffe.getS(iPos,"Kennung")+ "," + g.TabBegriffgruppen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Gruppe")) + ")\n";
              }
//              g.fixtestError("fertig VecN.size>0");
            }

          }
          else if (bVollInfo)
            s+="nichts altes zum Löschen\n";
          Qry.free();
//          g.fixtestError("Qry free");
          s=DurchfuehrenFrage(g,"altes",s,Fom,false);
          //Tab.showGrid("Tab");
          return s;
        }

        private static String DurchfuehrenFrage(Global g,String sTitel,String s,JFrame Fom,boolean bFrage)
        {
//          g.fixtestError("DurchfuehrenFrage "+sTitel);
          if (Static.getError()>0)
          {
            g.rollback();
            Static.printError(sTitel+"-Reinigung: nichts gelöscht da Fehler aufgetreten");
            return "";
          }
          else
          {
            int pane = Fom == null && !bFrage ? Message.YES_OPTION : new Message(Message.YES_NO_OPTION, Fom, g).showDialog("Durchfuehren", new String[] {s});
            if (pane == Message.YES_OPTION)
              g.commit();
            else
            {
              g.rollback();
              return "";
            }
          }
          return s;
        }

        public String OldCheck(Global g)
        {
          String s = "\nAltes prüfen:\n";
          //SQL Qry=new SQL(g);
          Vector Vec=SQL.getVector("select aic_begriff from begriff where tod=1",g);
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct aic_begriff,aic_formular from darstellung where "+g.in("aic_begriff",Vec),true);
          for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            int iPos=g.TabBegriffe.getPos("Aic",Tab.getI("aic_begriff"));
            Formular.TAB_Frames.posInhalt("aic_formular",Tab.getI("aic_formular"));
            s+=g.TabBegriffgruppen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Gruppe"))+" "+g.TabBegriffe.getS(iPos,"DefBezeichnung")+" ("+g.TabBegriffe.getS(iPos,"Kennung")+
                ") kommt in Formular "+g.TabBegriffe.getBezeichnungS(Formular.TAB_Frames.getI("aic_begriff"))+" vor!\n";
          }
          Tab=new Tabellenspeicher(g,"select distinct abfrage.aic_begriff A,modell.aic_begriff M from modell"+g.join2("abfrage","modell")+" where "+g.in("modell.aic_begriff",Vec),true);
          for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            int iPos=g.TabBegriffe.getPos("Aic",Tab.getI("A"));
            String sZusatz;
            if (g.TabBegriffe.getI(iPos,"Erf")>0)
              sZusatz="Bewegung "+g.TabErfassungstypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Erf"));
            else
              sZusatz="Stammtyp "+g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt"));
            s+="Modell "+g.TabBegriffe.getBezeichnungS(Tab.getI("M"))+" ("+g.TabBegriffe.getKennung(Tab.getI("M"))+
                ") kommt in Abfrage "+sZusatz+"."+g.TabBegriffe.getBezeichnungS(Tab.getI("A"))+" ("+g.TabBegriffe.getKennung(Tab.getI("A"))+") vor!\n";
          }
          Tab=new Tabellenspeicher(g,"select distinct modell.aic_begriff m,abfrage.aic_begriff a from abfrage"+g.join2("spalte","abfrage")+g.join2("befehl","spalte")+
                                   " join modell on befehl.aic_modell=modell.aic_modell where "+g.in("abfrage.aic_begriff",Vec),true);
          for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            s+="Abfrage "+g.TabBegriffe.getBezeichnungS(Tab.getI("A"))+" ("+g.TabBegriffe.getKennung(Tab.getI("A"))+
                ") kommt in Modell "+g.TabBegriffe.getBezeichnungS(Tab.getI("M"))+" ("+g.TabBegriffe.getKennung(Tab.getI("M"))+") vor!\n";
          }

          return s;
        }

        public static String ModellCheck(Global g,boolean bVollInfo,JFrame Fom)
        {
          long lClock = Static.get_ms();
          if (bVollInfo)
          {
            Static.clearError();
            g.start();
          }
          String s = bVollInfo ? "\nModelle reinigen:\n" : "";
          String s2=" from (select aic_modell,defbezeichnung"+
              ",(select count(*) from darstellung where aic_begriff=begriff.aic_begriff) F"+
              ",(select count(*) from formular where aic_modell=modell.aic_modell) F2"+
              ",(select count(*) from begriff_zuordnung where beg_aic_begriff=begriff.aic_begriff) Modul"+
              ",(select count(*) from abfrage where aic_modell=modell.aic_modell) M"+
              ",(select count(*) from abfrage where mod_aic_modell=modell.aic_modell) M2"+
              ",(select count(*) from befehl where mod_aic_modell=modell.aic_modell) B"+
              ",(select count(*) from berechtigung where aic_fremd=begriff.aic_begriff and aic_tabellenname="+g.iTabBegriff+") BG"+
              ",(select count(*) from bew_begriff where aic_begriff=begriff.aic_begriff) D"+
              " from begriff"+g.join2("modell","begriff")+(bVollInfo?" where not"+g.bit("bits",Global.cstEF):"")+") x where F+F2+M+M2+B+BG+D+Modul=0";
          int iBefehle=0;
          int iB=1;
          while (iB>0)
          {
            iB=g.exec("delete from befehl where aic_modell in (select aic_modell" + s2 + ")");
            iBefehle+=iB;
          }
          String s3=iBefehle+" Befehlszeilen gelöscht";
          s+=s3+"\n";
          g.diskInfo(s3);
          Vector Vec=SQL.getSVector("select defbezeichnung"+s2,g);
          for(int i=0;i<Vec.size();i++)
          {
            s3="Modell "+Vec.elementAt(i)+" gelöscht";
            s+=s3+"\n";
            g.diskInfo(s3);
          }
          g.exec("delete from modell where aic_modell in (select aic_modell" + s2 + ")");
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select begriff.defbezeichnung,begriff.aic_begriff from modell right outer"+g.join("begriff","modell")+g.join("begriffgruppe","begriff")+" where begriffgruppe.kennung='Modell' and aic_modell is null",true);
          s2="";
          for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
                  g.exec("delete from begriff_zuordnung where beg_aic_begriff="+Tab.getI("aic_begriff"));
                  g.exec("delete from Fehler where aic_begriff="+Tab.getI("aic_begriff"));
                  g.exec("delete from verlauf where aic_begriff="+Tab.getI("aic_begriff"));
                  g.exec("delete from defverlauf where aic_begriff="+Tab.getI("aic_begriff"));
                  if (g.exec("delete from begriff where aic_begriff="+Tab.getI("aic_begriff"))<-1)
                          s2+=Tab.getS("defbezeichnung")+"! ";
          }
          if(!s2.equals(""))
                  s+=" defekte Modelle nicht gelöscht: "+s2+"\n";
          g.clockInfo("Modelle löschen",lClock);
          if (bVollInfo)
            s=DurchfuehrenFrage(g,"Modell",s,Fom,bVollInfo);
          /*{
            int pane = new Message(Message.YES_NO_OPTION, null, g).showDialog("Durchfuehren", new String[] {s});
            if (pane == Message.YES_OPTION)
              g.commit();
            else
            {
              g.rollback();
              s = "";
            }
          }*/
          return s;
        }

        private String FormularCheck(Global g,boolean bVollInfo,JFrame Fom)
        {
          String s=bVollInfo ? "\nFormulare kontrollieren:\n":"";
          g.start();
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from (select b.aic_begriff,f.aic_formular,defbezeichnung,b.kennung"+
                                                    ",(select count(*) from darstellung where aic_begriff=b.aic_begriff) Form"+
                                                    ",(select count(*) from mandant where aic_begriff=b.aic_begriff) Mandant"+
                                                    ",(select count(*) from benutzergruppe where aic_begriff=b.aic_begriff) bg"+
                                                    ",(select count(*) from begriff_zuordnung where aic_begriff=b.aic_begriff) zu"+
                                                    ",(select count(*) from begriff_zuordnung where beg_aic_begriff=b.aic_begriff) zu2"+
                                                    " from code c"+g.join("begriff","b","c","code")+g.join("formular","f","b","begriff")+" where c.kennung <>'System') x where Form+Mandant+bg+zu+zu2=0",true);
          if (!Tab.isEmpty())
          {
            g.exec("delete from darstellung where aic_formular "+Static.SQL_in(Tab.getVecSpalte("aic_formular")));
            g.exec("delete from formular  where aic_formular "+Static.SQL_in(Tab.getVecSpalte("aic_formular")));
            g.exec("delete from fensterpos  where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.exec("delete from bew_begriff where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.exec("delete from defverlauf where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.exec("delete from verlauf where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.exec("delete from begriff where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.diskInfo(Tab.getAnzahlElemente(null)+" Formulare gelöscht:"+Static.SQL_in(Tab.getVecSpalte("defbezeichnung")));
            s+=Tab.getAnzahlElemente(null)+" Formulare gelöscht:\n"+Tab.getVecSpalte("defbezeichnung")+"\n\n";
          }

          Tab=new Tabellenspeicher(g,"select * from (select b.aic_begriff,f.aic_formular,defbezeichnung,b.kennung"+
                                   ",(select count(*) from darstellung where aic_begriff=b.aic_begriff) Form"+
                                   ",(select count(*) from begriff_zuordnung where aic_begriff=b.aic_begriff) zu"+
                                   ",(select count(*) from begriff_zuordnung where beg_aic_begriff=b.aic_begriff) zu2"+
                                   " from begriff b"+g.join("formular","f","b","begriff")+" where aic_stammtyp>0) x where Form+zu+zu2=0",true);
          if (!Tab.isEmpty())
          {
            g.exec("delete from darstellung where aic_formular "+Static.SQL_in(Tab.getVecSpalte("aic_formular")));
            g.exec("delete from formular  where aic_formular "+Static.SQL_in(Tab.getVecSpalte("aic_formular")));
            g.exec("delete from fensterpos  where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.exec("delete from bew_begriff where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.exec("delete from defverlauf where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.exec("delete from verlauf where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.exec("delete from begriff where aic_begriff "+Static.SQL_in(Tab.getVecSpalte("aic_begriff")));
            g.diskInfo(Tab.getAnzahlElemente(null)+" Formulare gelöscht:"+Static.SQL_in(Tab.getVecSpalte("defbezeichnung")));
            s+=Tab.getAnzahlElemente(null)+" Eingabe-Formulare gelöscht:\n"+Tab.getVecSpalte("defbezeichnung")+"\n\n";
          }

          s=execInfo(g.Qry,"begriff","begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" left outer"+g.join("darstellung","d","begriff","begriff")+" where d.aic_begriff is null and g.kennung='frei'",
                  "nicht verwendete Freiflächen",g,s,bVollInfo);
          s=execInfo(g.Qry,"begriff","begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" left outer"+g.join("darstellung","d","begriff","begriff")+" where d.aic_begriff is null and g.kennung='Bild'",
                  "nicht verwendete Bild-Begriffe",g,s,bVollInfo);
          s=execInfo(g.Qry,"begriff","begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" left outer"+g.join("darstellung","d","begriff","begriff")+" where d.aic_begriff is null and g.kennung='Titel'",
                  "nicht verwendete Titel",g,s,bVollInfo);
          //s=execInfo("delete from gruppe_zuordnung from gruppe_zuordnung z,begriffgruppe g join begriff b left outer join darstellung d where d.aic_begriff is null and g.kennung='Gruppe' and b.aic_begriff=z.aic_begriff",
          s=execInfo("delete from gruppe_zuordnung where aic_begriff in (select begriff.aic_begriff from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" left outer"+g.join("darstellung","d","begriff","begriff")+" where d.aic_begriff is null and g.kennung='Gruppe')",
                     "gruppe_zuordnung",g,s,bVollInfo);
          s=execInfo(g.Qry,"begriff","begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" left outer"+g.join("darstellung","d","begriff","begriff")+" where d.aic_begriff is null and g.kennung='Gruppe'",
                  "nicht verwendete Gruppen",g,s,bVollInfo);
          if (!g.ApplPort())
          {
            s+=DelAbfrage(g,true);
            s+=DelFrame(g,true);
            s+=DelModell(g,true);
            s+=DelHS(g,true);
            s+=DelRolle(g,true);
            s+=DelStt(g,true);
            if (Static.bTest)
              s+=DelEig(g,true);
            /*java.util.Vector Vec=SQL.getVector("select begriff.aic_begriff from begriffgruppe g"+g.join("begriff","g","begriffgruppe")+" left outer"+g.join("darstellung","d","begriff","begriff")+" where d.aic_begriff is null and g.kennung='Applikation'",g);
            if(Vec.size() > 0)
            {
              g.exec("delete from ansicht where aic_hauptschirm in (select aic_hauptschirm from hauptschirm where aic_begriff" + Static.SQL_in(Vec)+")");
              g.exec("delete from hauptschirm where aic_begriff" + Static.SQL_in(Vec));
              g.exec("delete from applikation_zuordnung where aic_begriff" + Static.SQL_in(Vec));
              g.exec("delete from begriff_zuordnung where aic_begriff" + Static.SQL_in(Vec));
              g.exec("delete from begriff_zuordnung where beg_aic_begriff" + Static.SQL_in(Vec));
              g.exec("delete from fensterpos where aic_begriff" + Static.SQL_in(Vec));
              int iAnz = g.exec("delete from begriff where aic_begriff" + Static.SQL_in(Vec));
              if(bVollInfo && iAnz > 0)
                s += iAnz + " nicht verwendete Applikationen gelöscht\n";
            }*/
          }
          s=DurchfuehrenFrage(g,"Formular",s,Fom,false);
          /*int pane = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Durchfuehren",new String[] {s});
          if(pane == Message.YES_OPTION)
            g.commit();
          else
          {
            g.rollback();
            s="";
          }*/
          return s;
        }

        public static String NullZeilen(Transact g,boolean bVollInfo)
        {
          String s="";
          if (SQL.getInteger(g,"select count(*) from stammpool where spalte_double=0.0")>0)
            s=execInfo("delete from stammpool where spalte_double=0.0","Stammpool 0-Daten",g,s,bVollInfo);
          if (SQL.getInteger(g,"select count(*) from bew_boolean where spalte_boolean=0")>0)
            s=execInfo("delete from bew_boolean where spalte_boolean=0","bew_boolean 0-Daten",g,s,bVollInfo);
          if (SQL.getInteger(g,"select count(*) from bew_wert where spalte_wert=0")>0)
            s=execInfo("delete from bew_wert where spalte_wert=0","bew_wert 0-Daten",g,s,bVollInfo);
          return s;
        }

        private static String execInfo(SQL Qry,String sTab,String sExec,String sInfo,Transact g,String s,boolean bInfo)
        {
          //SQL Qry=new SQL(g);
          int iAnz=Qry.deleteFrom(sTab,sExec);
          //Qry.free();
          if(iAnz>0)
          {
            s += sInfo+": " + iAnz + " gelöscht!\n";
            g.diskInfo(iAnz+" "+sInfo+" gelöscht");
          }
          else if (bInfo)
            s+="Keine "+sInfo+" gelöscht!\n";
          return s;
        }

        private static String execInfo(String sExec,String sInfo,Transact g,String s,boolean bInfo)
        {
        	return execInfo(sExec,sInfo,g,s,bInfo," gelöscht");
        }
        
        private static String execInfo2(String sExec,String sInfo,Transact g,String s,boolean bInfo)
        {
        	return execInfo(sExec,sInfo,g,s,bInfo," rückgesetzt");
        }

        public static String execInfo(String sExec,String sInfo,Transact g,String s,boolean bInfo,String sTat)
        {
          int iAnz=g.exec(sExec);
          if(iAnz>0)
          {
            s += sInfo+": " + iAnz + sTat+"!\n";
            g.diskInfo(iAnz+" "+sInfo+sTat);
          }
          else if (bInfo)
            s+="Keine "+sInfo+sTat+"!\n";
          return s;
        }

        public static void checkDatentyp(Global g)
        {
          g.fixtestInfo("Reinigung.checkDatentyp BewBool3");
          int iDt=g.getBegriffAicS("Datentyp","BewBool3");
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_eigenschaft,(select count(*) from bew_boolean where aic_eigenschaft=e.aic_eigenschaft) Anzahl from eigenschaft e where aic_begriff="+iDt,true);
          for(Tab.moveFirst();!Tab.out();Tab.moveNext())
            if (Tab.getI("Anzahl")>0)
              checkDatentyp(g,Tab.getI("aic_eigenschaft"),"BewBool3");
          g.fixtestInfo("Reinigung.checkDatentyp Bool3");
          iDt=g.getBegriffAicS("Datentyp","Bool3");
          Tab=new Tabellenspeicher(g,"select aic_eigenschaft,(select count(*) from stammpool where aic_eigenschaft=e.aic_eigenschaft and Spalte_Double=1.0) Anzahl from eigenschaft e where aic_begriff="+iDt,true);
          for(Tab.moveFirst();!Tab.out();Tab.moveNext())
            if (Tab.getI("Anzahl")>0)
              checkDatentyp(g,Tab.getI("aic_eigenschaft"),"Bool3");
          g.fixtestInfo("Reinigung.checkDatentyp fertig");
        }

        /*public static void checkDatentypText(Global g)
        {
          g.fixtestInfo("Reinigung.checkDatentypText");
          int iDt=g.getBegriffAicS("Datentyp","Text");
          Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_eigenschaft,(select count(daten_stringx.aic_daten) from daten_stringx join stammpool on daten_stringx.aic_daten=stammpool.aic_daten where aic_eigenschaft=e.aic_eigenschaft) Anzahl from eigenschaft e where aic_begriff="+iDt,true);
          for(Tab.moveFirst();!Tab.out();Tab.moveNext())
            if (Tab.getI("Anzahl")>0)
              checkDatentyp(g,Tab.getI("aic_eigenschaft"),"Text");
          g.fixtestInfo("Reinigung.checkDatentypText fertig");
        }*/

        public static void checkDatentyp(Global g,int iEig,String sDt)
        {
          int iArt=sDt.equals("Text") ? 2:sDt.equals("BewBool3") ? 0:1;
        	int iAic=iArt>1 ? 0:SQL.getInteger(g,"select aic_auswahl from auswahl where aic_eigenschaft="+iEig+" and nr=1");
        	if (iArt<2 && iAic==0)
        	{
        		Static.printError("Auswahl1 für Eig"+iEig+" nicht gefunden!");
        		return;
        	}
        	if (iArt==0) // BewBool3
        	{
        		Vector<Integer> Vec=SQL.getVector("select aic_bew_pool from bew_boolean where aic_eigenschaft="+iEig,g);
        		if (Vec.size()>0)
        		{
	        		g.diskInfo(Vec.size()+" auf BewBool3 zum umkopieren");
	        		SQL Qry=new SQL(g);
	        		int iAnz=0;
	        		for(int  iPos=0; iPos<Vec.size(); iPos++)
	        		{
	        			int iAIC_Bew=Sort.geti(Vec, iPos);
	        			Qry.add("AIC_Bew_Pool",iAIC_Bew);
                                        Qry.add("AIC_Eigenschaft",iEig);
                                        Qry.add("Aic",iAic);
                                        if (Qry.insert("Bew_Aic",false)==0)
                                          iAnz+=g.exec("delete from bew_boolean where AIC_Bew_Pool="+iAIC_Bew+" and aic_eigenschaft="+iEig);
	        		}
	        		Qry.free();
	        		g.diskInfo(g.TabEigenschaften.getBezeichnungS(iEig)+"("+iEig+"): "+iAnz+" auf BewBool3 umkopiert");
        		}
        	}
        	else if (iArt==1) // Bool3
        	{
        		int iAnz=g.exec("update stammpool set Spalte_Double="+iAic+" where aic_eigenschaft="+iEig+" and Spalte_Double=1.0");
        		g.diskInfo(g.TabEigenschaften.getBezeichnungS(iEig)+"("+iEig+"): "+iAnz+" auf Bool3 umkopiert");
        	}
                else if (iArt==2) // Memo->Text
                {
                  Vector<Integer> Vec=SQL.getVector("select aic_daten from stammpool where aic_eigenschaft="+iEig,g);
                  Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from daten_stringx where"+g.in("aic_daten",Vec),true);
                  SQL Qry=new SQL(g);
                  int iAnz=0;
                  Vector<Integer> VecDa=SQL.getVector("select aic_daten from daten_text",g);
                  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
                  {
                    int iD=Tab.getI("aic_daten");
                    if (!VecDa.contains(iD))
                    {
                      Qry.add("AIC_Daten", iD);
                      Qry.add("spalte_text", Tab.getM("spalte_stringx"));
                      if (Qry.insert("daten_text", false) == 0)
                        iAnz +=1;// g.exec("delete from daten_stringx where aic_daten=" + iD);
                    }
                  }
                  Qry.free();
                  if (iAnz>0)
                    g.diskInfo(g.TabEigenschaften.getBezeichnungS(iEig)+"("+iEig+"): "+iAnz+" auf Text umkopiert");
                }

        }

        public static void setSttANR(Global g,int iStt,int iEig)
        {
          if (iEig>0)
          {
            int iAnz = g.exec("update stamm_protokoll set ANR=(select sta_aic_stamm from poolview2 where stamm_protokoll.aic_stamm=aic_stamm and aic_eigenschaft=" + iEig +
                              ") where pro_aic_protokoll is null and aic_stamm in (select aic_stamm from stamm where aic_stammtyp=" + iStt + ")");
            g.fixInfo("setSttANR "+g.TabStammtypen.getBezeichnungS(iStt)+" für Eig="+g.TabEigenschaften.getBezeichnungS(iEig)+" (" + iEig + "): " + iAnz + "x");
          }
        }

        public static String toLDate(Global g,String sSpalte)
        {
          return g.Oracle() ? "to_number(to_char("+sSpalte+",'YYYYMMDD')":"year("+sSpalte+")*10000+month("+sSpalte+")*100+day("+sSpalte+")";
        }

        public static void setLDate(int iNr,Global g,int iBew,int iEig)
        {
          g.fixtestInfo("setLDate"+iNr+":"+iBew+"/"+iEig);
          //g.exec("update bew_pool set LDATE"+iNr+"=0 where aic_bewegungstyp="+iBew);
          if (iEig>0)
          {
            int iAnz=g.exec("update bew_pool set LDATE" + iNr + "=(select " +toLDate(g,"bew_von_bis.von")+
                   " from bew_von_bis where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" + iEig + ") where aic_bewegungstyp=" + iBew);
            g.fixInfo("setLdate"+iNr+" "+g.TabErfassungstypen.getBezeichnungS(iBew)+" für Eig="+g.TabEigenschaften.getBezeichnungS(iEig)+" ("+iEig+"): "+iAnz+"x");
          }
          g.exec("update bew_pool set LDATE" + iNr + "=0 where aic_bewegungstyp=" + iBew + " and LDATE" + iNr + " is null");
        }

        public static void setLDate(Global g,int iBew)
        {
          int iPos=g.TabErfassungstypen.getPos("aic",iBew);
          for (int i=2;i<4;i++)
            if (g.TabErfassungstypen.getI(iPos,"Datum_Eig"+i)>0)
              setLDate(i,g,iBew,g.TabErfassungstypen.getI(iPos,"Datum_Eig"+i));
        }
        
        public static void setLDateVB(Global g,int iBew,int iEig)
        {
          g.fixtestInfo("setLDateVB:"+iBew+"/"+iEig);
          if (iEig>0)
          {
            int iAnz=g.exec("update bew_pool set LDATEVON=(select " +toLDate(g,"bew_von_bis.von")+
                   " from bew_von_bis where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" + iEig + ") where aic_bewegungstyp=" + iBew);
            g.fixInfo("setLdateVon "+g.TabErfassungstypen.getBezeichnungS(iBew)+" für Eig="+g.TabEigenschaften.getBezeichnungS(iEig)+" ("+iEig+"): "+iAnz+"x");
            iAnz=g.exec("update bew_pool set LDATEBIS=(select " +toLDate(g,"bew_von_bis.bis")+
                    " from bew_von_bis where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" + iEig + ") where aic_bewegungstyp=" + iBew);
            g.fixInfo("setLdateBis "+g.TabErfassungstypen.getBezeichnungS(iBew)+" für Eig="+g.TabEigenschaften.getBezeichnungS(iEig)+" ("+iEig+"): "+iAnz+"x");
          }
          g.exec("update bew_pool set LDateVON=0 where aic_bewegungstyp=" + iBew + " and LDateVON is null");
          g.exec("update bew_pool set LDateBIS=0 where aic_bewegungstyp=" + iBew + " and LDateBIS is null");
        }
        
        public static void setLDateVB(Global g,int iBew)
        {
          int iPos=g.TabErfassungstypen.getPos("aic",iBew);
          //for (int i=2;i<4;i++)
            if (g.TabErfassungstypen.getI(iPos,"VB_Eig")>0)
              setLDateVB(g,iBew,g.TabErfassungstypen.getI(iPos,"VB_Eig"));
        }

        public static void setBOOL(int iNr,Global g,int iBew,int iEig)
        {
          //g.fixtestInfo("setBOOL"+iNr+":"+iBew+"/"+iEig);
          if (iEig>0)
          {
            g.exec("update bew_pool set BOOL"+iNr+"=0 where aic_bewegungstyp="+iBew+" and (BOOL"+iNr+">0 or BOOL"+iNr+" is null)");
            //int iAnz=g.exec("update bew_pool set BOOL" + iNr + "=(select bew_boolean.spalte_boolean"+
            //       " from bew_boolean where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" + iEig + ") where aic_bewegungstyp=" + iBew);
            int iPosE=g.TabEigenschaften.getPos("Aic",iEig);
            String sDt = g.TabEigenschaften.getS(iPosE,"Datentyp");
            int iAnz=SQL.getInteger(g,"select count(*) from "+(sDt.equals("BewBoolean") ? "bew_boolean":"bew_aic")+" where aic_eigenschaft=" + iEig);
            if (iAnz>0)
            {
             if (sDt.equals("BewBoolean"))
              iAnz=g.exec("update bew_pool set BOOL" + iNr + "=1 where aic_bewegungstyp="+iBew+
                            " and (select spalte_boolean from bew_boolean where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" + iEig + ")=1");
             else if (sDt.equals("BewBool3"))
             {
              iAnz = g.exec("update bew_pool set BOOL" + iNr +
                            "=(select aic from bew_aic where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft=" + iEig +
                            ") where aic_bewegungstyp=" + iBew);
              g.exec("update bew_pool set BOOL"+ iNr+"=0 where aic_bewegungstyp=" + iBew+" and BOOL"+ iNr+" is null");
             }
             else
                Static.printError("Reinigung.setBOOL: Datentyp "+sDt+" wird nicht unterstützt");
             g.fixInfo("setBOOL"+iNr+" "+g.TabErfassungstypen.getBezeichnungS(iBew)+" für Eig="+g.TabEigenschaften.getBezeichnungS(iEig)+" ("+iEig+"): "+iAnz+"x");
            }
          }
        }

        public static void setBOOL(Global g,int iBew)
        {
          int iPos=g.TabErfassungstypen.getPos("aic",iBew);
          for (int i=1;i<3;i++)
            if (g.TabErfassungstypen.getI(iPos,"Bool_Eig"+i)>0)
              setBOOL(i,g,iBew,g.TabErfassungstypen.getI(iPos,"Bool_Eig"+i));
        }

        public static void setBOOL3Index(Global g)
        {
          int iAnz=g.TabErfassungstypen.size();
          for (int iPos=0; iPos<iAnz;iPos++)
            for (int i=1;i<3;i++)
            if (g.TabErfassungstypen.getI(iPos,"Bool_Eig"+i)>0)
            {
              int iEig=g.TabErfassungstypen.getI(iPos,"Bool_Eig"+i);
              int iPosE=g.TabEigenschaften.getPos("Aic",iEig);
              String sDt = g.TabEigenschaften.getS(iPosE,"Datentyp");
              if (sDt.equals("BewBool3"))
                setBOOL(i,g,g.TabErfassungstypen.getI(iPos,"aic"),iEig);
            }
        }
        
        public static void CleanBew(Global g, int iBew,int iBegriff)
        {
        	int iPos=g.TabErfassungstypen.getPos("aic",iBew);
        	int iEig=0;
        	if (iPos<0)
        		iEig=SQL.getInteger(g, "select Aic_Eig1 from bewegungstyp where aic_bewegungstyp="+iBew);
        	else
        		iEig=g.TabErfassungstypen.getI(iPos,"Eig1");
        	if (iEig>0)
  		  	{
  			  int iStt=g.TabEigenschaften.getI_AIC("aic_stammtyp", iEig);
  			  String sSQL=" from protokoll p join bewview2 b on p.aic_protokoll=b.aic_protokoll join stamm_protokoll on b.ANR=aic_stamm and aic_stammtyp2<>"+iStt+" and aic_rolle is null where aic_bewegungstyp="+iBew;
  			  Vector<Integer> Vec=SQL.getVector("select b.aic_bew_pool"+sSQL,g);
  			  if (Vec.size()>0)
  			  {
	  			  g.fixtestError("CleanBew "+iBew+": "+Vec.size());
	  			  int iProt=g.Protokoll("Entfernen", iBegriff);
	  			  int iAnz=g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and"+g.in("aic_bew_pool", Vec));
	  			  g.diskInfo(iAnz+" Bew"+iBew+" wegen falschem ANR entfernt");
  			  }
  		  	}
        }

        public static void TestBew(Global g, int iBew,JFrame Fom)
        {
          int iPos=g.TabErfassungstypen.getPos("aic",iBew);
          int iAnz=0;
          if (iPos>=0)
          {
              String sBew=g.TabErfassungstypen.getS(iPos,"Bezeichnung")+" ("+iBew+")";
        	  for (int i=0;i<9;i++) // ANR prüfen
        	  {
        		  int iEig=g.TabErfassungstypen.getI(iPos,"Eig"+(i+1));
        		  if (iEig>0)
        		  {
        			  int iStt=g.TabEigenschaften.getI_AIC("aic_stammtyp", iEig);
        			  String sANR="ANR"+(i<1 ? "":""+(i+1));
        			  g.fixtestError(sANR+": Eig="+iEig+" -> Stt="+iStt);
        			  Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct timestamp,place,b.aic_protokoll,b."+sANR+",bezeichnung from protokoll p join bewview2 b on p.aic_protokoll=b.aic_protokoll join stamm_protokoll on b."+sANR+
        					  "=aic_stamm and aic_stammtyp2<>"+iStt+" and aic_rolle is null where aic_bewegungstyp="+iBew,true);
        			  if (Tab.size()>0)
        			  {
        				  iAnz+=Tab.size();
        				  Tab.showGrid(sANR+" bei "+sBew,Fom);
        			  }
        		  }
        	  }
           for (int i=1;i<4;i++) //LDATE prüfen
            if (i==1 || g.TabErfassungstypen.getI(iPos,"Datum_Eig"+i)>0)
            {
              int iEig=i==1 ? 0:g.TabErfassungstypen.getI(iPos,"Datum_Eig"+i);
              String sLDate=i==1 ? "LDATE":"LDATE"+i;
              Tabellenspeicher Tab=new Tabellenspeicher(g,(i==1 ? "select aic_bew_pool,aic_protokoll,ldate,gueltig from bew_pool":
                  "select p.aic_bew_pool,p.aic_protokoll,p."+sLDate+",vb.von from bew_pool p join bew_von_bis vb on p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig)+
                  " where aic_bewegungstyp="+iBew+" and "+sLDate+"<>"+toLDate(g,i==1 ?"gueltig":"vb.von"),true);
              new Tabellenspeicher(g,(i==1 ? "select aic_bew_pool,aic_protokoll,ldate,gueltig from bew_pool":"select p.aic_bew_pool,p.aic_protokoll,p."+sLDate+",null von from bew_pool p")+
                                   " where aic_bewegungstyp="+iBew+" and ("+sLDate+" is null or "+sLDate+">0 and "+
                                   (i==1 ?"gueltig":"(select vb.von from bew_von_bis vb where p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig+")")+" is null)",true).addTo(Tab,true);
              g.fixInfo(sLDate+(i==1 ? "":" (Eig"+iEig+")")+":"+Tab.size());
              if (Tab.size()>0)
			  {
				  iAnz+=Tab.size();
				  Tab.showGrid(sLDate+" falsch bei "+sBew,Fom);
			  }
            }
           for (int i=1;i<3;i++) // BOOL prüfen
            if (g.TabErfassungstypen.getI(iPos,"Bool_Eig"+i)>0)
            {
              int iEig=g.TabErfassungstypen.getI(iPos,"Bool_Eig"+i);
              String sBOOL="BOOL"+i;
              int iPosE=g.TabEigenschaften.getPos("Aic",iEig);
              String sDt = g.TabEigenschaften.getS(iPosE,"Datentyp");
              Tabellenspeicher Tab=null;
              if (sDt.equals("BewBoolean"))
              {
                Tab=new Tabellenspeicher(g,
                  "select p.aic_bew_pool,p.aic_protokoll,p."+sBOOL+",vb.spalte_boolean from bew_pool p join bew_boolean vb on p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig+
                  " where aic_bewegungstyp="+iBew+" and "+sBOOL+"<>vb.spalte_boolean",true);
                new Tabellenspeicher(g,"select p.aic_bew_pool,p.aic_protokoll,p."+sBOOL+",null spalte_boolean from bew_pool p"+
                                   " where aic_bewegungstyp="+iBew+" and ("+sBOOL+" is null or "+sBOOL+">0 and "+
                                   "(select spalte_boolean from bew_boolean vb where p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig+")"+" is null)",true).addTo(Tab,true);
              }
              else if (sDt.equals("BewBool3"))
              {
                Tab=new Tabellenspeicher(g,
                  "select p.aic_bew_pool,p.aic_protokoll,p."+sBOOL+",vb.aic from bew_pool p join bew_aic vb on p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig+
                  " where aic_bewegungstyp="+iBew+" and "+sBOOL+"<>vb.aic",true);
                new Tabellenspeicher(g,"select p.aic_bew_pool,p.aic_protokoll,p."+sBOOL+",null aic from bew_pool p"+
                                   " where aic_bewegungstyp="+iBew+" and ("+sBOOL+" is null or "+sBOOL+">0 and "+
                                   "(select aic from bew_aic vb where p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig+")"+" is null)",true).addTo(Tab,true);
              }
              else
                Static.printError("Reinigung.TestBew: Datentyp "+sDt+" wird nicht unterstützt");
              g.fixInfo(sBOOL+(" (Eig"+iEig+")")+":"+Tab.size());
              if (Tab.size()>0)
			  {
				  iAnz+=Tab.size();
				  Tab.showGrid(sBOOL+" falsch bei "+sBew,Fom);
			  }
            }
           if (g.TabErfassungstypen.getI(iPos,"VB_Eig")>0) // LDATE-von/bis
           {
               int iEig=g.TabErfassungstypen.getI(iPos,"VB_Eig");
               Tabellenspeicher Tab=new Tabellenspeicher(g,"select p.aic_bew_pool,p.aic_protokoll,p.LDateVon,vb.von from bew_pool p join bew_von_bis vb on p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig+
                   " where aic_bewegungstyp="+iBew+" and LDateVon<>"+toLDate(g,"vb.von"),true);
               new Tabellenspeicher(g,"select p.aic_bew_pool,p.aic_protokoll,p.LDateVon,null von from bew_pool p where aic_bewegungstyp="+iBew+" and (LDateVon is null or LDateVon>0 and "+
                                    "(select vb.von from bew_von_bis vb where p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig+") is null)",true).addTo(Tab,true);
               g.fixInfo("LDateVon (Eig"+iEig+"):"+Tab.size());
               if (Tab.size()>0)
                 Tab.showGrid("LDateVon falsch bei Bew "+iBew);
               Tab=new Tabellenspeicher(g,"select p.aic_bew_pool,p.aic_protokoll,p.LDateBis,vb.bis from bew_pool p join bew_von_bis vb on p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig+
                       " where aic_bewegungstyp="+iBew+" and LDateBis<>"+toLDate(g,"vb.bis"),true);
                   new Tabellenspeicher(g,"select p.aic_bew_pool,p.aic_protokoll,p.LDateBis,null bis from bew_pool p where aic_bewegungstyp="+iBew+" and (LDateBis is null or LDateBis>0 and "+
                                        "(select vb.bis from bew_von_bis vb where p.aic_bew_pool=vb.aic_bew_pool and vb.aic_eigenschaft="+iEig+") is null)",true).addTo(Tab,true);
                   g.fixInfo("LDateBis (Eig"+iEig+"):"+Tab.size());
                   if (Tab.size()>0)
     			  {
     				  iAnz+=Tab.size();
     				  Tab.showGrid("LDateBis falsch bei "+sBew,Fom);
     			  }
             }

           if (iAnz>0)
        	   g.fixtestError(sBew+" mit "+iAnz+" Fehler");
           else
        	   JOptionPane.showMessageDialog(Fom, "Kein Fehler bei "+sBew,"Test-Bew",JOptionPane.INFORMATION_MESSAGE);
          }
        }

        public static void TestStt(Global g, int iStt)
        {
          int iPos=g.TabStammtypen.getPos("aic",iStt);
          if (iPos>=0)
          {
            int iEig=g.TabStammtypen.getI(iPos,"ANR_Eig");
            if (iEig>0)
            {
              Tabellenspeicher Tab=new Tabellenspeicher(g,"select s.aic_stamm,bezeichnung,p.ANR,v.sta_aic_stamm ANR_Soll,p.aic_protokoll from stamm s join stamm_protokoll p on s.aic_stamm=p.aic_stamm and p.pro_aic_protokoll is null"+
                  " join poolview2 v on p.aic_stamm=v.aic_stamm and v.aic_eigenschaft="+iEig+" where s.aic_stammtyp="+iStt+" and (p.ANR is null or p.ANR<>v.sta_aic_stamm)",true);
              g.fixInfo("Eig"+iEig+":"+Tab.size());
              if (Tab.size()>0)
                Tab.showGrid("ANR falsch bew Stt "+iStt);
            }
          }
        }

	@SuppressWarnings("unchecked")
	public static String StammCheck(Global g,boolean bVollInfo,int iBegriff)
	{
            //return "Stamm-Reinigung wird auf dieser Datenbank noch nicht unterstützt";
            //checkDatentypText(g);
		String s=bVollInfo ? "\nStamm kontrollieren:\n":"";
		long lClock = Static.get_ms();
		long lClock2=lClock;
                Gauge Gau=new Gauge("Stamm reinigen",0,8/*+(g.MySQL() ? 1:0)*/,"Nullzeilen löschen",g,false);
                s+=NullZeilen(g,bVollInfo);
                g.clockInfo2("Nullzeilen löschen",lClock2);
                lClock2 = Static.get_ms();
                Gau.setText("LDATE reinigen",1);
                //new Tabellenspeicher(g,"select bew_pool.* from bew_pool where LDATE=0 and gueltig is not null",true).showGrid("LDATE=0 mit Gültigkeit");
                //new Tabellenspeicher(g,"select bew_pool.* from bew_pool where gueltig is null and (ldate is null or ldate>0)",true).showGrid("LDATE falsch ohne Gültigkeit");
                //new Tabellenspeicher(g,"select bew_pool.* from bew_pool where gueltig is not null and (ldate is null or ldate<>year(gueltig)*10000+month(gueltig)*100+day(gueltig))",true).showGrid("LDATE falsch mit Gültigkeit");
                if (SQL.getInteger(g,"select count(*) from bew_pool where gueltig is not null and ldate is null")>0)
                {
                  int iAnz=g.exec("update bew_pool set ldate="+toLDate(g,"gueltig")+" where gueltig is not null and ldate is null");
                  g.diskInfo(iAnz+" LDATE richtiggestellt");
                  s+= iAnz + " LDATE rückgesetzt!\n";
                }
                s=execInfo2("update bew_pool set ldate=0 where gueltig is null and ldate is null","LDATE auf 0",g,s,bVollInfo);
                g.clockInfo2("LDATE reinigen",lClock2);
                Gau.setText("Firma ermitteln",2);
                s+=Version.FirmaRebuild(g,0);
                //Gau.setText("ANR reinigen",2);
                //s=ANR_Check(g,s,true);
                //int iANR=SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='Mitarbeiter'");
                /*if (SQL.getInteger(g,"select count(*) from bew_pool p join bew_stamm s on p.aic_bew_pool=s.aic_bew_pool and s.aic_eigenschaft"+g.getEigANR()+
                               " where s.aic_stamm is not null and anr is null")>0)
                {
                  int iAnz=g.exec("update bew_pool set anr=("+SQL.first("aic_stamm from bew_stamm where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft"+g.getEigANR())+
                                  ") where ANR is null and ("+SQL.first("aic_stamm from bew_stamm where aic_bew_pool=bew_pool.aic_bew_pool and aic_eigenschaft"+g.getEigANR())+") is not null");
                  g.diskInfo(iAnz+" ANR richtiggestellt");
                  s+= iAnz + " ANR rückgesetzt!\n";
                }*/
                lClock2 = Static.get_ms();
                Gau.setText("Stammsätze reinigen",3);
                Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from (select aic_stamm,(select bezeichnung from stammview2 where aic_stamm=s.aic_stamm and aic_rolle is null) Person"+
                  ",(select bezeichnung from stammview2 where aic_stamm=s.aic_stamm and aic_rolle="+g.TabRollen.getAic("MITARBEITER")+") ANR from stamm s where aic_stammtyp="+g.iSttANR+
                  ") x where anr is not null and anr<>person",true);
                if (!Tab.isEmpty())
                {
                  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                    g.exec("update stamm_protokoll set bezeichnung='"+Tab.getS("ANR")+"' where aic_stamm="+Tab.getI("aic_stamm")+" and pro_aic_protokoll is null and aic_rolle is null");
                  s+= Tab.getAnzahlElemente(null) + " Personen-Bezeichnung(en) richtiggestellt!\n";
                }
                Tab=new Tabellenspeicher(g,"select aic_stamm,aic_rolle,bezeichnung from stammview2 where bezeichnung like ' %' or bezeichnung like '% '",true);
                if (!Tab.isEmpty())
                {
                  for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
                    g.exec("update stamm_protokoll set bezeichnung='"+Tab.getS("Bezeichnung").trim()+"' where aic_stamm="+Tab.getI("aic_stamm")+
                           " and pro_aic_protokoll is null and aic_rolle"+(Tab.isNull("aic_rolle")?" is null":"="+Tab.getI("aic_rolle")));
                  s+= Tab.getAnzahlElemente(null) + " Bezeichnung(en) gekürzt!\n";
                }

                s=execInfo("delete from stamm_protokoll where aic_protokoll=pro_aic_protokoll","gelöschte Elemente von stamm_protokoll",g,s,bVollInfo);
                s=execInfo("delete from stamm_protokoll where bezeichnung is null","leere Elemente von stamm_protokoll",g,s,bVollInfo);
		//s+=delCheck1(g,"stamm_protokoll where bezeichnung is null","stamm_protokoll",bVollInfo);
                s=execInfo("delete from bew_stamm where aic_stamm in (select stamm.aic_stamm from stamm left outer join stamm_protokoll p on stamm.aic_stamm=p.aic_stamm where p.aic_stamm is null)","Elemente von bew_stamm",g,s,bVollInfo);
                s=execInfo("delete from stammpool where aic_stamm in (select stamm.aic_stamm from stamm left outer join stamm_protokoll p on stamm.aic_stamm=p.aic_stamm where p.aic_stamm is null)","Elemente von stammpool",g,s,bVollInfo);
                s=execInfo("delete from stammpool where sta_aic_stamm in (select stamm.aic_stamm from stamm left outer join stamm_protokoll p on stamm.aic_stamm=p.aic_stamm where p.aic_stamm is null)","Elemente von stammpool2",g,s,bVollInfo);
                {
                  //s=execInfo("delete from stamm where aic_stamm in (select stamm.aic_stamm from stamm left outer join stamm_protokoll p on stamm.aic_stamm=p.aic_stamm where p.aic_stamm is null)","Elemente von stamm",g,s,bVollInfo);
                  SQL Qry=new SQL(g);
                  s=execInfo(Qry,"stamm","stamm left outer join stamm_protokoll p on stamm.aic_stamm=p.aic_stamm where p.aic_stamm is null","Elemente von stamm",g,s,false);
                  Qry.free();
                }
                g.clockInfo2("Stammsätze ohne Bezeichnung löschen",lClock2);
                int iDtDatum=g.getBegriffAicS("Datentyp","Datum");
                if (iDtDatum>0)
                {
                  g.progInfo("iDtDatum="+iDtDatum);
                  s = execInfo2("update stammpool set gultig_von=null where aic_stamm in (select aic_stamm from stamm" + g.join("stammtyp", "stamm") +
                                " where"+g.bit("STTBITS",Global.cstKeinStichtag)+") and aic_eigenschaft not in(select aic_eigenschaft from eigenschaft where aic_begriff="+iDtDatum+") and gultig_von is not null and gueltig_bis is null",
                                "Gültigkeiten auf null", g, s, bVollInfo);
                }
                s=execInfo2("update stammpool set gultig_von=null where aic_stamm is null and aic_bew_pool is not null and gultig_von is not null",
                           "Bew-Stamm-Gültigkeiten auf null",g,s,bVollInfo);
                //s=execInfo2("update stammpool set pro2_aic_protokoll=null where aic_bew_pool is not null and pro2_aic_protokoll is not null",
                //           "Bewegungs-Stammpool-Löschungen",g,s,bVollInfo);
	
				lClock2 = Static.get_ms();
				s+=delCheck(g,"stammpool where aic_daten>0 and (select count(*) from daten_stringx where aic_daten=stammpool.aic_daten)+(select count(*) from daten_Text where aic_daten=stammpool.aic_daten)"+
					"+(select count(*) from Zeit_von_bis where aic_daten=stammpool.aic_daten)+(select count(*) from daten_Image where aic_daten=stammpool.aic_daten)+(select count(*) from daten_GPS where aic_daten=stammpool.aic_daten)"+
					"+(select count(*) from daten_Bild2 where aic_daten=stammpool.aic_daten)+(select count(*) from daten_Doku where aic_daten=stammpool.aic_daten)=0","stammpool",bVollInfo);
				g.clockInfo2("Unnötige Daten-Verknüpfungen aus Stammpool löschen",lClock2);
		
				lClock2 = Static.get_ms();
//				for (int i=2;i<28957;i++)
//					g.exec("insert into daten (aic_daten) values ("+i+")");
				s+=delCheck(g,"daten where (select count(*) from daten_stringx where aic_daten=daten.aic_daten)+(select count(*) from daten_Text where aic_daten=daten.aic_daten)"+
					"+(select count(*) from Zeit_von_bis where aic_daten=daten.aic_daten)+(select count(*) from daten_Image where aic_daten=daten.aic_daten)"+
					"+(select count(*) from daten_Bild2 where aic_daten=daten.aic_daten)+(select count(*) from daten_Doku where aic_daten=daten.aic_daten)=0","Daten",bVollInfo);
				g.clockInfo2("Unnötige Daten aus Daten löschen",lClock2);
				
				lClock2 = Static.get_ms();
				Tab = new Tabellenspeicher(g,"select aic_daten,spalte_text from daten_text where spalte_text like '%  '",true);
				int iAnz=0;
				if (Tab.size()>0)
				{
				  for (Tab.moveFirst(); !Tab.out(); Tab.moveNext())
				  {
					  String sm=Tab.getM("spalte_text");
					  iAnz+=sm.length()-sm.trim().length();
					g.exec("update daten_text set spalte_text="+Static.StringForSQL(sm.trim())+" where aic_daten="+Tab.getI("aic_daten"));
				  }
				  g.diskInfo(iAnz+" Leerzeichen am Schluss aus "+Tab.size()+" Daten_Text entfernt");
				}
				g.clockInfo2("Leerzeichen aus Daten_Text löschen",lClock2);

				lClock2 = Static.get_ms();
              
              Gau.setText("Doppelte Stammpools löschen",4);
                int iAnz2=1;
                int i=0;
                while (iAnz2>0)
                {
                  Vector Vec = new Vector();
                  /*SQL.addVector(Vec,
                                "select pool from (select p.aic_stamm,p.aic_eigenschaft,begriff.kennung,count(*) Anzahl,min(aic_stammpool) pool from stammpool p" +
                                g.join("eigenschaft", "p") + g.join("begriff", "eigenschaft") +
                                " where pro2_aic_protokoll is null and pro_aic_protokoll is null and p.aic_stamm is not null and begriff.kennung <>'Mehrfach'" +
                                " group by p.aic_stamm,p.aic_eigenschaft,begriff.kennung) x where Anzahl>1", g);*/
                  SQL.addVector(Vec, "select pool from (select p.aic_bew_pool,p.aic_eigenschaft,count(*) Anzahl,min(aic_stammpool) pool from stammpool p" +
                                g.join("eigenschaft", "p") + " where p.aic_bew_pool is not null group by p.aic_bew_pool,p.aic_eigenschaft) x where Anzahl>1", g);
                  iAnz2 = Vec.size();
                  i++;
                  if (!Vec.isEmpty())
                    s = execInfo("delete from stammpool where aic_stammpool" + Static.SQL_in(Vec), i+".doppelte Stammpool-Daten", g, s, bVollInfo);
                  else if (i==1)
                    s += bVollInfo ? "Keine doppelten Stammpools gelöscht!\n" : "";
                }
                s=DSP_Check(g,s,true,false);
		g.clockInfo2("Doppelte Stammpools löschen",lClock2);
                s=execInfo("delete from eigenschaft_zuordnung where aic_eigenschaft not in (select aic_eigenschaft from eigenschaft"+g.join("begriff","eigenschaft")+" where begriff.kennung='CalcBezeichnung')",
                           "Eigenschaft_zuordnung",g,s,bVollInfo);
                if (g.getLog()>0)
                {
                  Gau.setText("Alte Stammpools löschen", 5);
                  int iProt = g.Protokoll("Entfernen", iBegriff); //getBegriff());
                  String sdt0 = g.DateTimeToString(Static.dt0);
                  s = execInfo("update stammpool set pro2_aic_protokoll=" + iProt + " where pro2_aic_protokoll is null and gueltig_bis<" + sdt0, "alte stammpools", g, s, bVollInfo);
                  s = execInfo("update stamm_protokoll set pro_aic_protokoll=" + iProt + " where pro_aic_protokoll is null and weg<" + sdt0, "alte stamm_protokolle", g, s,bVollInfo);
                  s = execInfo("update stamm_protokoll set pro_aic_protokoll=" + iProt + " where pro_aic_protokoll is null and austritt<" + sdt0, "alte stamm_protokolle", g, s,bVollInfo);
                }
                Gau.setText("Doppelte neue Stamm_Protokolle richtigstellen",6);
                int iNeu=g.getCodeAic("Status","new");
                int iChange=g.getCodeAic("Status","change");
                Vector<Integer> Vec=SQL.getVector("select aic_stamm from (select aic_stamm,count(*) Anzahl from stamm_protokoll where aic_code="+iNeu+" group by aic_stamm) x where Anzahl>1",g);
                if (Vec.size()>0)
                {
                  Tab = new Tabellenspeicher(g,"select aic_stamm,aic_protokoll,aic_code,nr from stamm_protokoll where" + g.in("aic_stamm", Vec) +
                                             " and aic_code=" + iNeu + " order by aic_stamm,aic_protokoll", true);
                  //Tab.showGrid("Stamm_Protokolle");
                  iAnz = 0;
                  int iStamm_old = 0;
                  for (Tab.moveFirst(); !Tab.out(); Tab.moveNext())
                  {
                    if (Tab.getI("aic_stamm") != iStamm_old)
                      iStamm_old = Tab.getI("aic_stamm");
                    else
                    {
                      iAnz++;
                      g.exec("update stamm_protokoll set aic_code=" + iChange + " where aic_stamm=" + Tab.getI("aic_stamm") + " and aic_protokoll=" + Tab.getI("aic_protokoll") +
                             " and aic_code=" + iNeu+" and nr="+Tab.getI("Nr"));
                    }
                  }
                  g.diskInfo(iAnz + " neu bei stamm_protokoll richtiggestellt");
                  s+="neu bei stamm_protokoll:"+iAnz+" richtiggestellt!\n";
                }
                else if (bVollInfo)
                  s+="Keine stamm_protokolls doppelt auf neu!\n";

                /*if (Static.bStringX)
                {
                  Gau.setText("StringX",5);
                  s +=Version.StringX_Rebuild(g);
                  Gau.setText("fertig",6);
                }
                else*/
                Gau.setText("ANR auf null zurück",7);
                s=ANR_null(g,s,true);
                Gau.setText("fertig",8);
                g.clockInfo("Stamm-Reinigung",lClock);
		return s;
	}

//        private void CheckIndexAdd(JPanel Pnl,AUCheckBox Cbx,String s)
//        {
//          Pnl.add(Cbx);
//          Cbx.setSelected(SQL.exists(g,"select table_id from sysindex where index_name='"+s+"'"));
//        }
//
//        private void CheckIndex(AUCheckBox Cbx,String sIndex,String s)
//        {
//          if (Cbx.Modified())
//          {
//            long lClock = Static.get_ms();
//            if (Cbx.isSelected())
//              s="create index "+sIndex+" on "+s;
//            else
//              s="drop index "+sIndex;
//            g.exec(s);
//            Cbx.setSelected(Cbx.isSelected());
//            g.clockInfo(s,lClock);
//          }
//        }

        private static String ReIndex(Global g,String sIndex,String sTable,String sElements,boolean bUnique)
        {
          g.bISQL = true;
          g.exec("drop index "+(g.MS()?sTable+".":"")+sIndex+(g.MySQL()?" on "+sTable:""));
          g.bISQL = false;
          if (g.exec("create "+(bUnique?"unique ":"")+"index "+sIndex+" on "+sTable+" ("+sElements+")")>=-1)
            return " "+sIndex;
          else
            return "";
        }
        
        public static String Optimize(Global g,Gauge gauge)
        {
          String[] sTbl=new String[] {"Stammpool","Stamm_protokoll","zwischenspeicher","protokoll","logging","spalte","bew_pool","bew_wert"};
      	  String s=null;
      	  for (int i=0;i<sTbl.length;i++)
      	  {
      		  long lClock = Static.get_ms();
      		  if (gauge!=null)
      			  gauge.setText(sTbl[i], i);
      		  if (g.MS())
      			  g.exec("alter index ALL on "+sTbl[i]+" rebuild with (fillfactor = 80, SORT_IN_TEMPDB = ON, STATISTICS_NORECOMPUTE = ON)");
      		  else
      			  g.exec("OPTIMIZE TABLE "+sTbl[i]);
      		  if (s==null)
      			  s=sTbl[i];
      		  else
      			  s+=", "+sTbl[i];
      		  g.clockInfo("Indexe von "+sTbl[i], lClock);
      	  }
      	  return s;
        }

        public static void IndexRebuild(Global g,JButton BtnIndex,JTextArea Memo,JFrame Frm)
        {
//          if (g.Def() && g.ASA())
//          {
//            if (DlgIndex==null)
//            {
//              DlgIndex = new JDialog((JFrame)thisFrame, "Indexe setzen");
//              JPanel PnlC = new JPanel(new GridLayout(0, 2));
//              final AUCheckBox CbxANR = new AUCheckBox("ANR");
//              final AUCheckBox CbxLDATE = new AUCheckBox("LDATE");
//              final AUCheckBox CbxANR_LDATE = new AUCheckBox("ANR+LDATE");
//              final AUCheckBox CbxBEW_TYP = new AUCheckBox("BEW-TYP");
//              final AUCheckBox CbxBEW_STAMM = new AUCheckBox("BEW_STAMM");
//              final AUCheckBox CbxSP1 = new AUCheckBox("Stammpool1");
//              final AUCheckBox CbxSP2 = new AUCheckBox("Stammpool2");
//              final AUCheckBox CbxSP3 = new AUCheckBox("Stammpool3");
//              final AUCheckBox CbxSP4 = new AUCheckBox("Stammpool4");
//              CheckIndexAdd(PnlC, CbxANR, "BEW_TEST3");
//              CheckIndexAdd(PnlC, CbxLDATE, "BEW_TEST2");
//              CheckIndexAdd(PnlC, CbxANR_LDATE, "BEW_TEST1");
//              CheckIndexAdd(PnlC, CbxBEW_TYP, "Ref_686_FK");
//              CheckIndexAdd(PnlC, CbxSP1, "SP_TEST1");
//              CheckIndexAdd(PnlC, CbxSP2, "SP_TEST2");
//              CheckIndexAdd(PnlC, CbxSP3, "SP_TEST3");
//              CheckIndexAdd(PnlC, CbxSP4, "SP_TEST4");
//              CheckIndexAdd(PnlC, CbxBEW_STAMM, "Ref_703_FK");
//              JPanel PnlS = new JPanel(new GridLayout(1, 0));
//              JButton BtnOk = g.getButton("Ok");
//              JButton BtnAbbruch = g.getButton("Abbruch");
//              DlgIndex.getRootPane().setDefaultButton(BtnOk);
//              PnlS.add(BtnOk);
//              PnlS.add(BtnAbbruch);
//              BtnOk.addActionListener(new ActionListener()
//              {
//                public void actionPerformed(ActionEvent e)
//                {
//                  CheckIndex(CbxANR, "BEW_TEST3","BEW_POOL (AIC_BEWEGUNGSTYP ASC,ANR ASC)");
//                  CheckIndex(CbxLDATE, "BEW_TEST2","BEW_POOL (AIC_BEWEGUNGSTYP ASC,LDATE ASC)");
//                  CheckIndex(CbxANR_LDATE, "BEW_TEST1","BEW_POOL (AIC_BEWEGUNGSTYP ASC,ANR ASC,LDATE ASC)");
//                  CheckIndex(CbxSP1, "SP_TEST1","STAMMPOOL (AIC_EIGENSCHAFT,PRO2_AIC_PROTOKOLL asc)");
//                  CheckIndex(CbxSP2, "SP_TEST2","STAMMPOOL (AIC_EIGENSCHAFT,AIC_STAMM asc)");
//                  CheckIndex(CbxSP3, "SP_TEST3","STAMMPOOL (AIC_EIGENSCHAFT,STA_AIC_STAMM asc)");
//                  CheckIndex(CbxSP4, "SP_TEST4","STAMMPOOL (AIC_STAMM,AIC_EIGENSCHAFT asc)");
//                  CheckIndex(CbxBEW_TYP, "Ref_686_FK","BEW_POOL (AIC_BEWEGUNGSTYP asc)");
//                  CheckIndex(CbxBEW_STAMM, "Ref_703_FK","BEW_STAMM (AIC_STAMM asc,AIC_EIGENSCHAFT asc)");
//                  DlgIndex.setVisible(false);
//                }
//              });
//
//              BtnAbbruch.addActionListener(new ActionListener()
//              {
//                public void actionPerformed(ActionEvent e)
//                {
//                  DlgIndex.setVisible(false);
//                }
//              });
//
//              DlgIndex.getContentPane().add("Center", PnlC);
//              DlgIndex.getContentPane().add("South", PnlS);
//              DlgIndex.pack();
//              Static.centerComponent(DlgIndex, thisFrame);
//            }
//            DlgIndex.setVisible(true);
//          }
//          else
          if (g.MySQL() || g.MS())
          {
        	  if (BtnIndex != null)
        		  BtnIndex.setEnabled(false);
//        	  thisJFrame().validate();
//        	  thisJFrame().addNotify();
        	  new Thread(new Runnable()
              {
                public void run()
                {
	        	  Gauge gauge=Frm==null ? null:new Gauge("Indexe erneuern",0,8,"",g,false,Frm);
	        	  String s=Optimize(g,gauge);
	        	  if (gauge!=null)
	        		  gauge.setText("fertig",8);
	        	  if (BtnIndex != null)
	        		  BtnIndex.setEnabled(true);
	        	  if (Memo != null)
	        		  addMemo(Memo,"\nBei folgenden Tabellen wurden die Indexe erneuert:\n"+s+"\n");
                }
              }).start();
          }
          else
          {
            String s="";
            s+=ReIndex(g,"SP_TEST4","STAMMPOOL","AIC_STAMM,AIC_EIGENSCHAFT asc",false);
            s+=ReIndex(g,"BEW_TEST1","BEW_POOL","AIC_BEWEGUNGSTYP,ANR,LDATE asc",false);
            s+=ReIndex(g,"BEW_TEST2","BEW_POOL","AIC_BEWEGUNGSTYP,LDATE asc",false);
            s+=ReIndex(g,"BEW_TEST4","BEW_POOL","AIC_BEWEGUNGSTYP,ANR2,LDATE asc",false);
            s+=ReIndex(g,"BEW_TEST5","BEW_POOL","PRO_AIC_PROTOKOLL,AIC_BEWEGUNGSTYP",false);
            //s+=ReIndex("BEW_STAMM_PK","BEW_STAMM","AIC_EIGENSCHAFT, AIC_BEW_POOL",true);
            s+=ReIndex(g,"BEW_WERT_PK","BEW_WERT","AIC_EIGENSCHAFT, AIC_BEW_POOL",true);
            //s+=ReIndex("IND_Import_ZSS","Zwischenspeicher","Kennung,Terminal,Pro_AIC_Protokoll,aic_Mandant",true); //8.5.2013 entfernt, da er Eintragungen verhindert
            addMemo(Memo,"\nFolgende Indexe wurden erneuert:"+s+"\n");
            /*g.bISQL = true;
            if (g.MS())
            {
              g.exec("drop index BEW_POOL.Ref_686_FK");
              g.exec("drop index BEW_STAMM.Ref_703_FK");
              g.exec("drop index Zwischenspeicher.IND_DBC_ZSS");
            }
            else
            {
              g.exec("drop index Ref_686_FK");
              g.exec("drop index Ref_703_FK");
              g.exec("drop index IND_DBC_ZSS");
            }
            g.bISQL = false;
            g.exec("create index Ref_686_FK on BEW_POOL (AIC_BEWEGUNGSTYP asc)");
            g.exec("create index Ref_703_FK on BEW_STAMM (AIC_STAMM asc,AIC_EIGENSCHAFT asc)");
            g.exec("create index IND_DBC_ZSS on Zwischenspeicher (Kennung,Terminal,Zwischentext asc)");
            addMemo("\n3 Indexe erneuert\n");
            int i = g.exec("delete from zwischenspeicher where terminal is null");
            if (i > 0)
              addMemo("\n" + i + " Zeilen aus Zwischenspeicher gelöscht\n");*/
          }
        }

	private void ViewRebuild()
	{
		     // interne Views erneuern
             addMemo(Memo,Version.ViewRebuild(g,Version.getVer())+" Views erneuert\n");
             // Sequences erneuern
             if (g.Oracle())
             {
               SQL Qry=new SQL(g);
               Qry.createAllSequences();
               Qry.free();
               addMemo(Memo,"Sequences erneuert\n");
             }
             // Views und Functions für lokalen Zeitraum löschen
             if (g.MySQL())
             {
            	// MySQL-Parameter setzen
            	 g.exec("set global key_buffer_size=384*1024*1024");
            	 g.exec("set global sort_buffer_size=20*1024*1024");
            	 g.exec("set global join_buffer_size=20*1024*1024");
            	// Views in Tabellen löschen
                 Vector<String> Vec=SQL.getSVector("select table_name from information_schema.tables where table_schema=DATABASE() and table_type='BASE TABLE' and table_name like '%view%'",g);
                 for (int i=0;i<Vec.size();i++)
                   g.exec("drop table "+Sort.gets(Vec, i));
                 if (Vec.size()>0)
                	 addMemo(Memo,Vec.size()+" View-Tabellen entfernt\n");
                 // Views für lok. ZR löschen
            	 int iNr=6;
            	 int iOld=4;
            	 int iAnz=0;
            	 boolean bExists=true;
            	 while (bExists)
            	 {
//            	   String sNr=iNr+"";
            	   bExists=g.exists("select table_name from information_schema.views where table_schema=database() and table_name='stammview"+iNr+"'",null);
            	   if (bExists)
            	   {
            		   iOld=iNr;
            		   g.exec("drop view stammview"+iNr);
            		   g.exec("drop view bewview"+iNr);
            		   g.exec("drop view bewview"+iNr+"d");
            		   g.exec("drop view poolview"+iNr);
            		   addMemo(Memo,"View "+iNr+" entfernt\n");  
            		   boolean bFE=g.exists("select specific_name from information_schema.routines where routine_schema=DATABASE() and specific_name=?","bis"+iNr+g.getDB());
            		   if(bFE)
            		   {
            		     g.exec("drop function von"+iNr+g.getDB());
            		     g.exec("drop function bis"+iNr+g.getDB());
            		     addMemo(Memo,"function von/bis"+iNr+g.getDB()+" entfernt\n");  
            		   }
            		   iAnz++;   		   
            	   }
            	   else if ((iNr-iOld)<3)
            		   bExists=true;
            	   iNr++;
            	 }
            	 iNr=6;bExists=true;
            	 while (bExists)
            	 {
            	   String sNr=iNr+g.getDB();
            	   bExists=g.exists("select table_name from information_schema.views where table_schema=database() and table_name='stammview"+sNr+"'",null);
            	   if (bExists)
            	   {
            		   g.exec("drop view stammview"+sNr);
            		   g.exec("drop view bewview"+sNr);
            		   g.exec("drop view bewview"+sNr+"d");
            		   g.exec("drop view poolview"+sNr);
            		   g.exec("drop function von"+sNr);
            		   g.exec("drop function bis"+sNr);
            		   iAnz++;
            		   addMemo(Memo,"View/Function "+sNr+" entfernt\n");
            		   iNr++;
            	   }
            	 }     
            	 if (iAnz>0)
            		 g.sendWebDB("cleanViewsDB",null,null);
             }
             else if (g.MS())
             {
            	 int iNr=6;
            	 boolean bExists=true;
            	 while (bExists)
            	 {
            	   bExists=g.exists("select name,crdate from sysobjects where xtype='V ' and name='stammview"+iNr+"'",null);
            	   if (bExists)
            	   {
            		   g.exec("drop view stammview"+iNr);
            		   g.exec("drop view bewview"+iNr);
//            		   g.bISQL=true;
            		   g.exec("drop view bewview"+iNr+"d");
//            		   g.bISQL=false;
            		   g.exec("drop view poolview"+iNr);
            		   addMemo(Memo,"View "+iNr+" entfernt\n");
            		   iNr++;
            	   }
            	 }
            	 if (iNr>6)
            		 g.sendWebDB("cleanViewsDB",null,null);
             }
	}

        private void MandantenEntfernen()
        {
          if (!g.ASA())
          {
            addMemo(Memo,"Mandanten-Reinigung wird auf dieser Datenbank nicht unterstützt");
            return;
          }
          java.util.Vector<Integer> Vec=SQL.getVector("select aic_mandant,"+g.now()+"-(select max(ein) from logging where aic_mandant=mandant.aic_mandant) last,(select count(*) from protokoll join logging where aic_mandant=mandant.aic_mandant) Anzahl2 from mandant where (last is null or last>365) and anzahl2=0",g);
          if (Vec.size()>0)
          {
            String sWhere="aic_mandant"+Static.SQL_in(Vec);
            g.diskInfo("Lösche Mandanten:"+SQL.getString(g,"select list(kennung) from Mandant where "+sWhere));

            g.fixInfo(g.exec("delete from lizenz where "+sWhere)+"x Lizenz gelöscht");
            g.fixInfo(g.exec("delete from berechtigung from berechtigung join benutzergruppe where "+sWhere)+"x berechtigung gelöscht");
            g.fixInfo(g.exec("delete from benutzer_zuordnung from benutzer_zuordnung join benutzergruppe where "+sWhere)+"x benutzer_zuordnung gelöscht");
            g.fixInfo(g.exec("delete from benutzer_zuordnung from benutzer_zuordnung join benutzer l where l."+sWhere)+"x benutzer_zuordnung gelöscht");
            g.fixInfo(g.exec("delete from bew_stamm from bew_stamm join bew_pool where "+sWhere)+"x bew_stamm gelöscht");
            g.fixInfo(g.exec("delete from bew_boolean from bew_boolean join bew_pool where "+sWhere)+"x bew_boolean gelöscht");
            g.fixInfo(g.exec("delete from bew_von_bis from bew_von_bis join bew_pool where "+sWhere)+"x bew_von_bis gelöscht");
            g.fixInfo(g.exec("delete from stammpool from stammpool join bew_pool where "+sWhere)+"x stammpool gelöscht");
            g.fixInfo(g.exec("delete from bew_pool where "+sWhere)+"x bew_pool gelöscht");
            g.fixInfo(g.exec("delete from stamm_protokoll from stamm_protokoll where "+sWhere)+"x stamm_protokoll gelöscht");
            g.fixInfo(g.exec("delete from stammpool from stammpool p join stamm_protokoll on p.aic_stamm=stamm_protokoll.aic_stamm where "+sWhere)+"x stammpool gelöscht");
            g.fixInfo(g.exec("delete from spalte_zuordnung from spalte_zuordnung join stamm_protokoll on spalte_zuordnung.aic_stamm=stamm_protokoll.aic_stamm where "+sWhere)+"x spalte_zuordnung gelöscht");
            //g.fixInfo(g.exec("delete from stamm where "+sWhere)+"x stamm gelöscht");

            g.fixInfo(g.exec("update abfrage join benutzergruppe set abfrage.aic_benutzergruppe=null where "+sWhere)+"x abfrage geändert");
            //g.fixInfo(g.exec("delete from stamm_berechtigung from stamm_berechtigung join benutzergruppe where "+sWhere)+"x stamm_berechtigung gelöscht");
            g.fixInfo(g.exec("delete from benutzergruppe where "+sWhere)+"x benutzergruppe gelöscht");
            g.fixInfo(g.exec("delete from parameter from parameter join logging l where l."+sWhere)+"x parameter gelöscht");
            g.fixInfo(g.exec("delete from parameter from parameter join benutzer l where l."+sWhere)+"x parameter gelöscht");
            g.fixInfo(g.exec("delete from FENSTERPOS from FENSTERPOS join logging l where l."+sWhere)+"x FENSTERPOS gelöscht");
            //ProtokollCheck(g,false);
            //g.fixInfo(g.exec("select * from protokoll join logging l where l."+sWhere)+"x berechtigung gelöscht");
            g.fixInfo(g.exec("delete from logging where "+sWhere)+"x logging gelöscht");
            g.fixInfo(g.exec("delete from logging from logging join benutzer l on logging.aic_benutzer=l.aic_benutzer where l."+sWhere)+"x logging gelöscht");
            g.fixInfo(g.exec("delete from benutzer where "+sWhere)+"x benutzer gelöscht");
            int iAnz=g.exec("delete from mandant where "+sWhere);
            if (iAnz>0)
              addMemo(Memo,iAnz+" Mandanten gelöscht\n");
          }
        }

	private static String delCheck(Transact g,String sSQL,String sTbl,boolean bVollInfo)
	{
            int i=SQL.getInteger(g,"select count(*) from "+sSQL);
            if (i>0)
              return execInfo("delete from "+sSQL," Elemente von "+sTbl,g,"",bVollInfo);
            else
              return bVollInfo ? "Keine Elemente von "+sTbl+" gelöscht!\n":"";
	}

        /*private static String delCheck1(Transact g,String sSQL,String sTbl,boolean bVollInfo)
        {
            int i=SQL.getInteger(g,"select count(*) from "+sSQL);
            if (i>0)
              return execInfo("delete from "+sSQL," Elemente von "+sTbl,g,"",bVollInfo);
            else
              return bVollInfo ? "Keine Elemente von "+sTbl+" gelöscht!\n":"";
        }*/

//	private static String upCheck(Transact g,String sWhere,String sTbl,String sCol,boolean bVollInfo)
//	{
//		int i=SQL.getInteger(g,"select count(*) from "+sTbl+sWhere);
//		if (i>0)
//		{
//			g.exec("update "+sTbl+" set "+sCol+"=null"+sWhere);
//			return i+" Elemente von "+sCol+" auf Null gesetzt!\n";
//		}
//		else
//			return bVollInfo ? "Keine Elemente von "+sCol+" auf Null gesetzt!\n":"";
//	}

        /*private void AFM_Del()
        {
                String s=Memo.getText()+"\nAlle Abfragen, EingabeFormulare und Modelle löschen:\n";
                //g.progInfo("*1*");
                int pane = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("AFM_Delete");
                //g.progInfo("*2*");
                if(pane == Message.YES_OPTION)
                {
                        SQL Qry = new SQL(g);
                        Qry.open("Select bg.kennung,count(*) anzahl from begriff join begriffgruppe bg on bg.aic_begriffgruppe=begriff.aic_begriffgruppe where bg.kennung in ('Abfrage','Modell','Frame','Druck','Planung','Applikation','Modul','Kunde') and (bg.kennung <> 'Frame' or aic_stammtyp is not null) group by bg.kennung");
                        if (Qry.eof())
                                s+=" nichts zu löschen!\n";
                        else
                        {
                                while(!Qry.eof())
                                {
                                  if (Qry.getI("Anzahl")>0)
                                    s+=Qry.getI("Anzahl")+"x "+Qry.getS("kennung")+" gelöscht\n";
                                  Qry.moveNext();
                                }
                        }
                        g.exec("delete from lizenz");
                        //int iKunde=g.TabBegriffgruppen.getAic("Kunde");
                        //int iModul=g.TabBegriffgruppen.getAic("Modul");
                        Qry.exec("delete from applikation_zuordnung");
                        Qry.exec("delete from Begriff_Zuordnung");
                        //g.fixInfo(g.exec("delete from begriff where aic_begriffgruppe="+iModul)+" Module gelöscht");
                        //g.fixInfo(g.exec("delete from begriff where aic_begriffgruppe="+iKunde)+" Kunden gelöscht");

                        g.fixInfo(g.exec("delete from fixeigenschaft")+"x fixeigenschaft gelöscht");
                        g.fixInfo(g.exec("delete from bedingung")+"x bedingung gelöscht");
                        g.fixInfo(g.exec("delete from befehl")+"x befehl gelöscht");
                        g.fixInfo(g.exec("delete from SPALTE_BERECHNUNG")+"x SPALTE_BERECHNUNG gelöscht");
                        g.fixInfo(g.exec("delete from spalte_zuordnung")+"x spalte_zuordnung gelöscht");
                        g.fixInfo(g.exec("delete from spalte")+"x spalte gelöscht");
                        Qry.exec("update eigenschaft set aic_abfrage=null");
                        g.fixInfo(g.exec("delete from planung")+"x planung gelöscht");
                        Qry.exec("delete from ABSCHLUSSDEFINITION");
                        Qry.exec("delete from ABSCHLUSS_ZUORDNUNG");
                        Qry.exec("delete from ABSCHLUSS");
                        Qry.exec("delete from ABSCHLUSSTYP");
                        Qry.exec("delete from benutzer_zuordnung where aic_abfrage is not null");
                        Qry.exec("delete from ansicht");
                        Qry.exec("delete from hauptschirm");
                        Qry.exec("delete from druck");
                        g.fixInfo(g.exec("delete from abfrage")+"x abfrage gelöscht");
                        g.fixInfo(g.exec("delete from modell")+"x modell gelöscht");
                        Qry.exec("delete from fensterpos");
                        Qry.exec("delete from Druck_Zuordnung");
                        g.fixInfo(g.exec("delete from Abschnitt")+"x Abschnitt gelöscht");
                        //Qry.exec("delete from darstellung from darstellung join formular join begriff where aic_stammtyp is not null");
                        Qry.exec("delete from darstellung where aic_darstellung in (select aic_darstellung from darstellung"+g.join("formular","darstellung")+g.join("begriff","formular")+" where aic_stammtyp is not null)");
                        //g.fixInfo(g.exec("delete from formular from formular join begriff where aic_stammtyp is not null")+"x formular gelöscht");
                        g.fixInfo(g.exec("delete from formular where aic_formular in (select aic_formular from formular"+g.join("begriff","formular")+" where aic_stammtyp is not null)")+"x formular gelöscht");
                        Qry.exec("delete from bew_begriff");
                        //Qry.exec("delete from darstellung from darstellung join begriff join begriffgruppe bg where bg.kennung in('Modell','Abfrage','Planung') or bg.kennung='Frame' and aic_stammtyp is not null");
                        Qry.exec("delete from darstellung where aic_darstellung in (select aic_darstellung from darstellung"+g.join("begriff","darstellung")+" join begriffgruppe bg on bg.aic_begriffgruppe=begriff.aic_begriffgruppe where bg.kennung in('Modell','Abfrage','Planung','Applikation') or bg.kennung='Frame' and aic_stammtyp is not null)");
                        //int i=g.exec("delete from begriff from begriff join begriffgruppe bg where bg.kennung in ('Modell','Abfrage','Planung','Druck')");
                        int i=g.exec("delete from begriff where aic_begriffgruppe in (select aic_begriffgruppe from begriffgruppe bg where bg.kennung in ('Modell','Abfrage','Planung','Druck','Applikation','Modul','Kunde'))");
                        //i+=g.exec("delete from begriff from begriff join begriffgruppe bg where bg.kennung='Frame' and aic_stammtyp is not null");
                        i+=g.exec("delete from begriff where aic_begriff in (select aic_begriff from begriff join begriffgruppe bg on bg.aic_begriffgruppe=begriff.aic_begriffgruppe where bg.kennung='Frame' and aic_stammtyp is not null)");
                        g.fixInfo(i+" x Begriffe gelöscht");
                        g.exec("update begriff set ImportZeit=null");
                        g.fixInfo(g.exec("delete from gruppe_Zuordnung")+"x Gruppe_Zuordnung gelöscht");
                        g.fixInfo(g.exec("delete from stammtyp_Zuordnung")+"x Stammtyp_Zuordnung gelöscht");
                        g.fixInfo(g.exec("delete from bew_Zuordnung")+"x Bew_Zuordnung gelöscht");
                        Qry.free();
                        Memo.setText(s);
                        FremdtabellenLoeschen(g,false);
                }
        }*/

}

