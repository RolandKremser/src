/*
    All_Unlimited-Grunddefinition-DefAbfrage.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JSplitPane;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
// import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import jclass.util.JCVector;
import All_Unlimited.Allgemein.AUVector;
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.SyncStamm;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.BewEig;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUEkitCore;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Datum;
// import All_Unlimited.Allgemein.Eingabe.DruckEingabe;
import All_Unlimited.Allgemein.Eingabe.Format;
// import All_Unlimited.Allgemein.Eingabe.FormularEingabe;
import All_Unlimited.Allgemein.Eingabe.MassEingabe;
import All_Unlimited.Allgemein.Eingabe.ModellEingabe;
import All_Unlimited.Allgemein.Eingabe.RolleEingabe;
import All_Unlimited.Allgemein.Eingabe.Schrift;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Allgemein.Eingabe.StammEingabe;
import All_Unlimited.Allgemein.Eingabe.SubEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.Calc;
import All_Unlimited.Hauptmaske.Export;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Zeitraum;

import javax.swing.JMenuItem;



//import javax.swing.JScrollPane;
import All_Unlimited.Allgemein.GroupBox;
import All_Unlimited.Allgemein.Eingabe.SpinBoxAuswahl;
//import All_Unlimited.Allgemein.Eingabe.AUFarbe;

public class DefAbfrage extends All_Unlimited.Allgemein.Formular
{
    // add your data members here
    private static final int MF  =1;
    private static final int BEW =2;
    private static final int SYS =4;
    private static final int CALC=8;
    private static final int ALLE=16;
    private static final int PERS=32;
    
    private static String sMemoSpalte=null;

	final int kein=1;
	final int Und=1;
	final int Oder=2;
	final int Edit=3;
	private int iArt=kein;

	private int iBG_Abfrage=0;
	private int iBG_Vergleich=0;
	private int iBG_Platzhalter=0;
	//private int iTab_Begriff=0;
	private int iTab_Spalte=0;

	private Global g;
	private ShowAbfrage A=null;
	private ShowAbfrage Aold=null;
    private Vector VecStt=null;
    private boolean bRechte=false;
	private JDialog DlgInit=null;
	private JDialog Dlg=null;
	private JFrame DlgTest=null;
  private AUCheckBox CbxWeb2=null;
	private AUCheckBox CbxFWeb=null;
	private AUOutliner Gid=new AUOutliner(new JCOutlinerFolderNode(""));
	private AUTextArea Memo=null;//new AUTextArea();
	private AUTextArea MemoBew=null;//new AUTextArea();
  private AUEkitCore MemoAllg=null;
  private AUEkitCore MemAllgA=null;

	private JButton BtnNeuAbfrage;
	private JButton BtnKopie;
	private JButton BtnInit;
	private JButton BtnRefreshDB=null;
    private JButton BtnSetEdit=null;
	private JButton BtnEditAbfrage;
	private JButton BtnDelAbfrage;
	private AbfrageEingabe CboAbfrage;
	private JButton BtnTest;
	private JButton BtnSpeichern;
	private JButton BtnRuecksetzen;

	private JButton BtnEdit;
	private JButton BtnLoeschen;
	private JButton BtnUp;
	private JButton BtnErsetzen;
	private JButton BtnDown;
	private AUOutliner GidEigenschaft=new AUOutliner(new JCOutlinerFolderNode(""));
    private AUTextArea MemoEig;
	private JButton BtnUnd;
	private JButton BtnOder;

	private JButton BtnOk=null;
	private JButton BtnAbbruch=null;
	private JButton BtnView=null;

	private Text EdtBezeichnung = new Text("",255);
    private Text EdtDefBezeichnung = new Text("",255);
	private Text EdtKennungA = new Text("",98,Text.KENNUNG);
	private Text EdtKennzeichen = new Text("",98,Text.KENNUNG);
  private Text EdtKennungS = new Text("",20,Text.KENNUNG);
	private Text EdtSpBezO = new Text("",255);
	private Text EdtSpBez1 = new Text("",255);
	private Text EdtSpBez2 = new Text("",255);
	private Schrift CboSchrift;
	private ModellEingabe CboModell;
    private ModellEingabe CboModell2;
    private StammEingabe CboWebStamm;
    private AbfrageEingabe CboAbfFilter;
    private AbfrageEingabe CboAbfDetail;
    private ComboSort CboFomDetail; //TODO gehört FormularEingabe 
    private AbfrageEingabe CboAbfErw;
	private ComboSort CboBenutzergruppe;
	private ComboSort CboStammtyp;
	private ComboSort CboSprache;
        private boolean bFillRolle=true;
        private RolleEingabe CboRolle2;
        private ComboSort CboStt=null;
        private boolean bCboStt=false;
	private ComboSort CboTabellentyp;
	private ComboSort CboProg;
        private int iBitEig=0;

	// für Bedingung
	private ComboSort CboVergleich;
	private ComboSort CboPlatzhalter;
	private ComboSort CboPlatzhalter2;
	private JLabel Lbl1;
	private Container Edt1;
        private RolleEingabe CboR2;
	private ComboSort Cbo1;
	private JLabel Lbl2;
	private Container Edt2;
	private Text Edt3;
	private ComboSort Cbo2;
	private int iPlatzOld=0;
	private int iNeu=0;
	private int iNeuAic=0;
	private Text EdtVar=null;

	// für Spalten
	private Format EdtFormat;
	private ComboSort CboExportformat;
        private ComboSort CboAnzeigeart;
	private ComboSort CboErgebnis;
	private ComboSort CboAusrichtung;
        private ComboSort CboHierarchie;
	private ComboSort CboMass;
	private ComboSort CboMass1;
	private ComboSort CboMass2;
        private ComboSort CboMass3;
        private ComboSort CboRel;
        private StammEingabe CboBed=null;
        private int iRel=0;
	private AbfrageEingabe CboFilter;
	private boolean bCboFilter=false;
	private SubEingabe CboSub1=null;
	private SubEingabe CboSub2=null;
	private SubEingabe CboSub3=null;
	// private JPanel PnlSub1=new JPanel(new BorderLayout());
	// private JPanel PnlSub2=new JPanel(new BorderLayout());
	// private JPanel PnlSub3=new JPanel(new BorderLayout());
	// private FormularEingabe CboSubFom;
	// private AbfrageEingabe CboSubAbf;
	// private ModellEingabe CboSubMod;
	// private DruckEingabe CboSubPnt;
	// private ComboSort CboSubDia;
  private ComboSort CboTyp;

  private ComboSort CboSubSpalte=null;
  
	private Tabellenspeicher TabStamm;
	private StammEingabe SteStamm = null;
	private AUCheckBox CbxAlleKein=null;
	private AUCheckBox CbxModell=null;
	private AUCheckBox CbxDebug=null;
        //private AUCheckBox CbxView2b=null;
	private Tabellenspeicher TabAbfragen;
	//private Tabellenspeicher TabSpalten;
	private boolean bSpeichern=false;
	private boolean bOkErlaubt = true;
	private boolean bReihenfolge=false;
	private boolean bCboLaden = true;
	private boolean bFirstSpEdit=true;
	private boolean bFirstBedEdit=true;
	private boolean bFirstAbfEdit=true;
	private boolean bAndererStt=true;
    private boolean bAndereBits=false;

	private int iStammtyp=0;
        private int iRolle=0;
	private String sTitel;
	private String sTitel2;
	//private int iAbfrage=0;
	//private int iSpalten=0;

	//private boolean bOk=true;

    private Datum DatVon;
	private Datum DatBis;
	private Text TxtZA;
	private Zahl EdtGKKAb=new Zahl(1);

        private JRadioButton RadVon=null;
        private JRadioButton RadBis=null;
        private JRadioButton RadDauer=null;
        private AUCheckBox CbxZeit=null;
        private AUCheckBox CbxOhneJahr=null;
        private AUCheckBox CbxKen=null;
        private JPanel PnlEdt1;
        private JPanel PnlEdt2;
        private boolean bFirst=true;
        private ComboSort CboSTT=null;
        private RolleEingabe CboRolle=null;
        private boolean bEdit=false;
        private boolean bSave=false;
        //private AUCheckBox CbxNotChange=null;
        private Vector<Integer> VecSperre=new Vector<Integer>();
        private JPopupMenu popEig= new JPopupMenu();
        private JPopupMenu popSpalte= new JPopupMenu();
        private JMenuItem MnuHide;
        private JMenuItem MnuEdit;
        private JMenuItem MnuCopy;
        private JMenuItem MnuDel;
        private JMenuItem MnuDel2;
        private AUOutliner GidGesamt;
        private JDialog DlgTab;
        private JDialog DlgProg;
        private Text TxtBezP=new Text("",60);
        private Text TxtKenP=new Text("",20,Text.KENNUNG);
        private String sSave="";

//        private javax.swing.JCheckBox CbxCompress2 = null;

        private static DefAbfrage This1=null;
        private static DefAbfrage This2=null;
        private int iNr=-1;

        public static DefAbfrage get(Global rg,ShowAbfrage rA,int riStt)
        {
          return get(rg,rA,riStt,null,false,0);
        }

        public static DefAbfrage get(Global rg,ShowAbfrage rA,int riStt,Vector rVec)
        {
          return get(rg,rA,riStt,rVec,false,0);
        }

	public static DefAbfrage get(Global rg,ShowAbfrage rA,int riStt,Vector rVec,boolean rbRechte,int iNr)
	{
//		long lClock3=Static.get_ms();
        DefAbfrage ThisX=iNr==0?This1:iNr==1 ? This2:null;
        if (rbRechte)
            rg.fixtestInfo("DefAbfrage-Aufruf mit Rechte-bit");
		if(ThisX!=null)
		{
            if (rbRechte || rg.Def()) ThisX.iBitEig=SYS;
			ThisX.setStt(riStt,rA.iRolle,ThisX.iBitEig);
			ThisX.Aold=rA;
            ThisX.VecStt=rVec;
            ThisX.bRechte=rbRechte;
            ThisX.bFirst=true;
            ThisX.iNr=iNr;
			//rg.progInfo("DefAbfrage.get: Spalten="+rA.Spalten());
			ThisX.A=new ShowAbfrage(rA);
                        //rg.progInfo("Rolle1="+This.A.iRolle);
		}
//		rg.clockInfo3("DefAbfrage.get davor",lClock3);       
		DefAbfrage DA= ThisX==null?new DefAbfrage(rg,rA,riStt,rVec,rbRechte,iNr):ThisX;
        return DA;
	}
	
	public int getNr()
	{
		return iNr;
	}

    public void show()
    {
      show(true);
    }

	public void show(boolean bTake)
	{
		long lClock3=Static.get_ms();
	    if (bFirst)
	        bFirst=false;
	    else
	        VecStt=null;
	    if (g.Abfrage() && (iStammtyp>0 || g.existsBew(-iStammtyp)))
	    {
	        //g.testInfo("vor fillCbo");
	        bEdit=BtnSetEdit==null;
	        bSave=bEdit;
	        g.testInfo("VecSperre="+VecSperre+", iBegriff="+A.iBegriff);
	        if (VecSperre.contains(new Integer(A.iBegriff)))
	        {
	            bEdit=true;
	            bSave=true;
	            //VecSperre.removeElement(new Integer(A.iBegriff));
	        }
//	        lClock3=g.clockInfo3("DefAbfrage.show-Anfang",lClock3);
          	fillCbo();
          	lClock3=g.clockInfo3("DefAbfrage.fillCbo",lClock3);
          	Load(false,false);
          	lClock3=g.clockInfo3("DefAbfrage.Load",lClock3);
	        JPanel Pnl=getFrei("Combo Stammtyp");
	        Pnl.setLayout(new BorderLayout());
	        if (Pnl != null)
	        {
	          Pnl.removeAll();
	          if (VecStt!=null)
	          {
	            bCboStt=false;
	            if (CboStt==null)
	            {
	              CboStt = new ComboSort(g);
	              CboStt.addItemListener(new ItemListener()
	              {
	                public void itemStateChanged(ItemEvent ev)
	                {
	                  //g.progInfo("CboStt.addItemListener");
	                  if (ev.getStateChange() == ItemEvent.SELECTED && bCboStt)
	                  {
	                    setStt(CboStt.getSelectedAIC(),0,iBitEig);
	                    fillEig();
	                  }
	                }
	              });
	            }
	            CboStt.fillStt(VecStt,iStammtyp);
	            //fillEig();
	            Pnl.add("Center",CboStt);
	            bCboStt=true;
	          }
	          if (g.Spezial() /*&& A.bSpalten*/ || bRechte)
	            Pnl.add("East",BtnInit);
	        }
	        //getFrei("Outliner").add(Gid);
	        //BtnInit.setVisible(VecStt==null && g.Def());

			//iAbfrage=A.iAbfrage;
			bOkErlaubt=bTake;
                        //g.testInfo("vor EnableButtons");
			EnableButtons();
			lClock3=g.clockInfo3("DefAbfrage.show-Rest",lClock3);
                        //g.testInfo("vor show");
			super.show();
			lClock3=g.clockInfo3("DefAbfrage.show-show",lClock3);
		}
		else
			new Message(Message.WARNING_MESSAGE,null,g).showDialog("keineBerechtigung");
	}

	public static void free()
	{
		if (This1!=null)
		{
			This1.g.testInfo("DefAbfrage.free");
			This1.thisFrame.dispose();
			This1=null;
		}
                if (This2!=null)
                {
                        This2.g.testInfo("DefAbfrage2.free");
                        This2.thisFrame.dispose();
                        This2=null;
                }
	}

        /*private JMenuItem addMenuItem(String sKnopf,JPopupMenu pop)
        {
          JMenuItem Mnu = new JMenuItem(g.getBegriff("Button", sKnopf));
          Mnu.setFont(g.fontStandard);
          pop.add(Mnu);
          return Mnu;
        }*/

        /*private JCheckBoxMenuItem addCbxItem(String sCbx,boolean b,JPopupMenu pop)
        {
          JCheckBoxMenuItem Mnu = new JCheckBoxMenuItem(g.getBegriff("Checkbox", sCbx), b);
          Mnu.setFont(g.fontStandard);
          pop.add(Mnu);
          return Mnu;
        }*/

        private JCheckBoxMenuItem addCbxItem2(String sCbx,JPopupMenu pop,final int i,boolean b)
        {
          JCheckBoxMenuItem Mnu = new JCheckBoxMenuItem(g.getBegriffS("Checkbox", sCbx), b);
          Mnu.setFont(Global.fontStandard);
          pop.add(Mnu);
          Mnu.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent ev)
                {
                  boolean b=((JCheckBoxMenuItem)ev.getSource()).getState();
                  boolean b2=(iBitEig&i)>0;
                  if (b && !b2)
                    iBitEig+=i;
                  else if(!b && b2)
                    iBitEig-=i;
                  //g.fixInfo("iBitEig2="+iBitEig);
                }
              });
          return Mnu;
        }

	private void setStt(int riStt,int riRolle,int riBitEig)
	{
          g.testInfo("setStt "+riStt+"/"+riRolle+"/"+riBitEig);
          if(!bAndererStt)
            bAndererStt= riRolle != iRolle && riStt>0 || riStt != iStammtyp || (riBitEig&(ALLE+PERS)) != (iBitEig&(ALLE+PERS));
          //g.progInfo("setStt "+iStammtyp+"/"+riStt+"/"+riRolle+"/"+bAndererStt);
          bAndereBits=(riBitEig&(MF+BEW+SYS+CALC)) != (iBitEig&(MF+BEW+SYS+CALC));
          iBitEig=riBitEig;
              if (bAndererStt)
              {
                  iRolle=riRolle;
                  iStammtyp = riStt;
                int iPosB=iStammtyp<0 ? g.TabErfassungstypen.getPos("Aic",new Integer(-iStammtyp)):-1;
                int iPosS=iStammtyp>0 ? g.TabStammtypen.getPos("Aic",new Integer(iStammtyp)):-1;
                boolean bRolle=iRolle>0 && iStammtyp>0 && (!A.isHS() || iStammtyp==g.iSttANR);
		sTitel=g.getBegriffS("Label","Abfrage")+(iNr+1)+" - "+g.getBezeichnung("Tabellenname",bRolle ?"ROLLE":iStammtyp<0?"BEWEGUNGSTYP":"STAMMTYP")+" - "+
                    (bRolle ? g.TabRollen.getBezeichnungS(iRolle):
			iPosB>=0 ? g.TabErfassungstypen.getInhalt("Bezeichnung",iPosB):iPosS>=0 ? g.TabStammtypen.getInhalt("Bezeichnung",iPosS):g.getBegriffS("Show","Allgemein"));
		setTitle(sTitel);
		sTitel2=sTitel+" ("+g.getBegriffS("Show","gesperrt")+")";
              }
	}

	private DefAbfrage(Global rg,ShowAbfrage rA,int riStt,Vector rVec,boolean rbRechte,int iNr)
	{
		super("Abfrage",null,rg);
		long lClock3=Static.get_ms();
		Count.add(Count.Abfrage);
		g=rg;
        if (iNr==0)
          This1=this;
        else if (iNr==1)
          This2=this;
		//g.putTabFormulare(getBegriff(),0,this);
        this.iNr=iNr;
		Aold=rA;
        VecStt=rVec;
        bRechte=rbRechte;
        if (bRechte || g.Def())
          iBitEig=SYS;
        A=new ShowAbfrage(rA);
//        lClock3=g.clockInfo3("Abfrage erzeugen", lClock3);
		getFix();
//		lClock3=g.clockInfo3("Abfrage getFix", lClock3);
//        lClock3=g.clockInfo3("Abfrage-Beginn", lClock3);
		getElements();
		lClock3=g.clockInfo3("Abfrage getElements", lClock3);
        addPopup();
        lClock3=g.clockInfo3("Abfrage addPopup", lClock3);
                //g.fixInfo("iBitEig0="+iBitEig);
		setStt(riStt,A.iRolle,iBitEig);
//		lClock3=g.clockInfo3("Abfrage setStt", lClock3);
		Events();
//		lClock3=g.clockInfo3("Abfrage Events", lClock3);
	}

	public void finalize()
	{
		Count.sub(Count.Abfrage);
	}

	private void getFix()
	{
		/*if (g.TabBegriffgruppen.posInhalt("Kennung","Abfrage"))
			iBG_Abfrage=g.TabBegriffgruppen.getI("Aic");
		else
			Static.printError("Begriffgruppe Abfrage nicht gefunden");
		if (g.TabBegriffgruppen.posInhalt("Kennung","Vergleich"))
			iBG_Vergleich=g.TabBegriffgruppen.getI("Aic");
		else
			Static.printError("Begriffgruppe Vergleich nicht gefunden");
		if (g.TabBegriffgruppen.posInhalt("Kennung","Platzhalter"))
			iBG_Platzhalter=g.TabBegriffgruppen.getI("Aic");
		else
			Static.printError("Begriffgruppe Platzhalter nicht gefunden");*/
                iBG_Abfrage=g.TabBegriffgruppen.getAic("Abfrage");
                iBG_Vergleich=g.TabBegriffgruppen.getAic("Vergleich");
                iBG_Platzhalter=g.TabBegriffgruppen.getAic("Platzhalter");
                g.progInfo("BG_Abfrage="+iBG_Abfrage+", BG_Vergleich="+iBG_Vergleich+", BG_Platzhalter="+iBG_Platzhalter);
		//if (g.TabTabellenname.posInhalt("Kennung","BEGRIFF"))
		//	iTab_Begriff=g.TabTabellenname.getI("Aic");
		//else
		//	Static.printError("Tabelle Begriff nicht gefunden");
                int iPos=g.TabTabellenname.getPos("Kennung","SPALTE");
		if (iPos>=0)
			iTab_Spalte=g.TabTabellenname.getI(iPos,"Aic");
		else
			Static.printError("Tabelle Spalte nicht gefunden");
	}

	private void getElements()
	{
		Gid.setRootVisible(false);
		Gid.setCopy(true);
		CbxFWeb=getCheckbox("Web");
		if (CbxFWeb != null)
		  CbxFWeb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fillCbo();				
			}
		});
		getFrei("Outliner").add(Gid);
                if (g.Def())
                {
                  if (getFrei("Memo")!=null)
                  {
                    Memo=new AUTextArea(g, 3);
                    Memo.setMaxLength(4000);
	                  getFrei("Memo").add(Memo);
	                  Memo.Edt.addKeyListener(new KeyListener()
                    {
                      public void keyPressed(KeyEvent e)
                      {}
                      public void keyReleased(KeyEvent e)
                      {
                        if (Memo.Modified())
                          EnableButtons();
                      }
                      public void keyTyped(KeyEvent e)
                      {}
                    });
                  }
                  if (getFrei("MemoBew")!=null)
                  {
                    MemoBew=new AUTextArea(g, 3);
                	  getFrei("MemoBew").add(MemoBew);
                    MemoBew.setMaxLength(4000);
                	  MemoBew.setEditable(false);
                  }
                  if (getFrei("Memo_Allgemein")!=null)
                  {
                    MemoAllg=new AUEkitCore(g,thisJFrame(),7);
                    MemoAllg.setMaxLength(4000);
                    getFrei("Memo_Allgemein").add(MemoAllg);
                    MemoAllg.setText(SQL.getString(g, "select memo from daten_memo where aic_tabellenname="+Global.iTabBegriff+" and aic_sprache=1 and aic_fremd="+getBegriff()));
                    MemoAllg.Edt.addFocusListener(new FocusListener()
                    {
                      public void focusGained(FocusEvent e)
                      {}
                      public void focusLost(FocusEvent e)
                      {
                        if (MemoAllg.Modified())
                        {
                          String sMemo=MemoAllg.getValue();
                          // g.fixtestError("speichere: "+sMemo);
							            g.setMemo(sMemo, "", Global.iTabBegriff, getBegriff(), 1);
                          MemoAllg.setText(sMemo);
                        }
                      }
                    });
                  }
                }
                else
                  getFrei("Memo").setMinimumSize(new java.awt.Dimension(100,1));

		BtnNeuAbfrage=getButton("Neu");
		BtnKopie=getButton("Kopieren");
                JButton BtnEdit1=getButton("Edit4");
                if (BtnEdit1==null)
                  BtnEdit1=getButton("Edit");
                JButton BtnEdit2=getButton("Parameter");
		BtnEditAbfrage=BtnEdit2==null ? BtnEdit1:BtnEdit2;
                BtnSetEdit=BtnEdit2==null ? null:BtnEdit1;
		/*BtnInit=getButton("Init");
                if (BtnInit==null)*/ BtnInit=g.getButton("Init");
                BtnRefreshDB=getButton("RefreshDB");
		BtnDelAbfrage=getButton("Loeschen");
		CboAbfrage = new AbfrageEingabe(g,thisFrame,false,false);
                /*CboAbfrage.setPreferredSize(new java.awt.Dimension(300,20));
                CboAbfrage.setMaximumRowCount(15);
                CboAbfrage.setFont(g.fontStandard);*/
		getFrei("Combo Abfragen").add(CboAbfrage);
		JPanel PnlSprache=getFrei("Combo Sprache");
		CboSprache = new ComboSort(g);
		CboSprache.fillDefTable("Sprache", false);
		CboSprache.setSelectedAIC(g.getSprache());
		if (PnlSprache != null && g.Def())
		{
			PnlSprache.add(CboSprache);
			Abfrage.iSprache=g.getSprache();
			CboSprache.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					long lClock=Static.get_ms();
					Abfrage.iSprache=CboSprache.getSelectedAIC();
					Load(true,true);
					g.clockInfo("Sprache "+CboSprache.getSelectedBezeichnung()+" ("+Abfrage.iSprache+") setzen",lClock);
				}
			});
		}
		BtnTest=getButton("Test");
		BtnSpeichern=getButton("Speichern");
		BtnView=getButton("View");
		BtnRuecksetzen=getButton("Ruecksetzen");
		//g.testInfo("BtnRuecksetzen="+BtnRuecksetzen);

		BtnEdit=getButton("EditEig");
		//g.testInfo("BtnEdit="+BtnEdit);
		BtnLoeschen=getButton("DelEig");
		//g.testInfo("BtnLoeschen="+BtnLoeschen);
		BtnUp=getButton("Pfeil oben");
		BtnErsetzen=getButton("Ersetzen");
		BtnDown=getButton("Pfeil unten");
                MemoEig=new AUTextArea(g,0);
                MemoEig.setMaxLength(4000);
                JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,GidEigenschaft,MemoEig);
                splitPane.setOneTouchExpandable(true);
                splitPane.setResizeWeight(1);
		getFrei("Outliner Eigenschaft").add(splitPane);
		GidEigenschaft.setRootVisible(false);
		GidEigenschaft.setColumnLabelSortMethod(Sort.sortMethod);
                String[] s = new String[] {g.getShow("Bezeichnung"),g.getShow("Nr"),g.getShow("Datentyp"),g.getBegriffS("Checkbox","Jeder"),g.getBegriffS("Checkbox","Lizenz"),g.getBezeichnung("Tabellenname","STAMMTYP"),g.getShow("Kennung")};
                GidEigenschaft.setColumnButtons(s);
                GidEigenschaft.setNumColumns(g.Def()? 7:1);
		BtnUnd=getButton("Und");
		BtnOder=getButton("Oder");

		/*BtnOk=getButton("Ok");
                if (BtnOk==null)*/
                  BtnOk=getButton("Uebernehmen");
                ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnOk);
		/*BtnAbbruch=getButton("Abbruch");
                if (BtnAbbruch==null)*/
                  BtnAbbruch=getButton("Beenden");

                /*JButton BtnShow = getButton("show");
                if(BtnShow!=null)
                {
                  BtnShow.setEnabled(g.Def());
                  BtnShow.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      ShowAll();
                    }
                  });
                }*/

                JButton BtnSpalten = getButton("Spalten");
                if(BtnSpalten!=null)
                {
                  BtnSpalten.setEnabled(g.Def());
                  BtnSpalten.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      ShowSpalten();
                    }
                  });
                }

	}

        private void addPopup()
        {
          ActionListener ALS=new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              String s=ev.getActionCommand();
              if (s.equals("add"))
                Eig_add();
              else if (s.equals("Refresh"))
                Refresh(true);
              else if (s.equals("ausblenden") || s.startsWith("Loeschen"))
                ZeileLoeschen(s.equals("Loeschen2"),s.startsWith("Loeschen"));
              else if (s.equals("Edit"))
              {
                if (BtnEdit.isEnabled())
                  Edit();
              }
              else if (s.equals("Kopie"))
                copySpalte();
              else if (s.equals("Info"))
                InfoAbfrage(g,A.iAbfrage).showGrid(A.sDefBez);
              else if (s.equals("show"))
              {
                if (A.TabSp.posInhalt("Nummer",Gid.getSelectedNode().getUserData()))
                  getModelle_der_Spalte(g,A.TabSp.getI("aic_spalte"),null).showGrid(""+Gid.getSelectedNode().getLabel());
              }
              else if (s.equals("showAll2"))
            	  getModelle_der_Spalte(g,-A.iAbfrage,null).showGrid(A.sBez);
              else if (s.equals("DefVerlauf"))
              {
                new Tabellenspeicher(g,"select tat,timestamp,(select benutzer.kennung from benutzer join logging l on benutzer.aic_benutzer=l.aic_benutzer where l.aic_logging=v.aic_logging) Benutzer"+
                                     " from defverlauf v where aic_begriff="+A.iBegriff+" order by timestamp desc",true).showGrid("DefVerlauf");
              }
              else if (s.equals("Spalten"))
                ShowSpalten();
              else if (s.equals("Abfragen"))
                ShowAbfragen();
              else if (s.equals("Abfragebits"))
                ShowAbfrageBits();
              else if (s.equals("Web-Abfragen"))
            	  showBitoW(null,0,true,null);
              else if (s.equals("Spaltenbits"))
                ShowSpaltenBits();
              else if (s.equals("Last"))
              {
                Tabellenspeicher Tab = new Tabellenspeicher(g,g.top("x.*,v2.timestamp from (select distinct b.defbezeichnung,b.kennung,(select max(aic_defverlauf) from defverlauf where aic_begriff=b.aic_begriff) last"+
                    g.AU_Bezeichnung1("bewegungstyp","b")+" Bewegungstyp"+g.AU_Bezeichnung1("stammtyp","b")+" Stammtyp,b.aic_begriff,b.Web,b.Tod"+
                    " from defverlauf v join begriff b on v.aic_begriff=b.aic_begriff and b.aic_begriffgruppe=30) x join defverlauf v2 on x.last=v2.aic_defverlauf order by last desc",30),true);
                ShowOpenAbf(g,thisJFrame(),"letzten Abfragen",Tab);
              }
              else if (s.equals("ohneStt"))
              {
                Tabellenspeicher Tab = new Tabellenspeicher(g,g.top("x.* from (select distinct b.defbezeichnung,b.kennung,(select max(timestamp) from defverlauf where aic_begriff=b.aic_begriff) last"+
                    g.AU_Bezeichnung1("bewegungstyp","b")+" Bewegungstyp"+g.AU_Bezeichnung1("stammtyp","b")+" Stammtyp,b.aic_begriff"+g.bei("bits", Abfrage.cstKeinStamm, "kein_Stamm")+
                    " from begriff b where b.aic_begriffgruppe=30 and aic_stammtyp is null and aic_bewegungstyp is null and "+g.bit("bits", Abfrage.cstNurModell)+" and bits&0x200000000000=0"+
                    ") x order by last desc",30),true);
                ShowOpenAbf(g,thisJFrame(),"Abfragen ohne Stammtyp mit Daten im Modell",Tab);
              }
              else if (s.equals("lt_Eig"))
              {
            	int iEig=Sort.geti(getEig());
                Tabellenspeicher Tab = new Tabellenspeicher(g,"select b.DefBezeichnung,b.kennung,(select max(aic_defverlauf) from defverlauf where aic_begriff=b.aic_begriff) last"+
                		g.AU_Bezeichnung("Bewegungstyp","b")+" Bewegungstyp"+ g.AU_Bezeichnung("Stammtyp","b")+" Stammtyp,b.aic_begriff"+g.AU_Bezeichnung2("Spalte")+" Spalten_Bez"+
                		" from begriff b "+g.join("abfrage","b","begriff")+g.join2("spalte","abfrage")+g.join2("fixeigenschaft","spalte")+" where aic_eigenschaft="+iEig+(iStammtyp<0 ? " and b.aic_bewegungstyp="+(-iStammtyp):""),true);
                String sEig=g.TabEigenschaften.getBezeichnungS(iEig);
                ShowOpenAbf(g,thisJFrame(),"mit Eigenschaft "+sEig,Tab);
              }
              else if (s.equals("TabSp"))
                A.TabSp.showGrid("TabSp");
//              else if (s.equals("TabJoker"))
//              {
//            	g.fixtestError("show TabJoker");
//            	if (TabJoker != null)
//            		TabJoker.showGrid("TabJoker");
//              }
              else if (s.equals("TabV") && Calc.TabV != null)
                	Calc.TabV.showGrid("TabV");
              else if (s.equals("TabVar") && g.TabVar != null)
              	g.TabVar.showGrid("TabVar");
              else if (s.equals("Spalte_Zuordnung"))
                ShowSpalteZuordnung();
              else if (s.equals("DefEigenschaft"))
            	  DefEigenschaft.get(g, Sort.geti(getEig()));
              else if (s.equals("DefEigenschaft2"))
                DefEigenschaft.get(g, getEigenschaft());
              else if (s.equals("Filter"))
              {
                if (!bEdit)
                {
                  int iA1=CboAbfrage.getSelectedAIC();
                  CboAbfrage.setFilterEig(getEigenschaft());
                  int iA2=CboAbfrage.getSelectedAIC();
                  g.fixtestError("Filter -> "+iA1+" -> "+iA2);
                  if (iA1 != iA2)
                  {
                    A.iAbfrage = iA2;
                    Load(true,false);
                  }
                }
                else
                  g.fixtestError("Filter nur erlaubt, wenn nicht im Edit-Modus");
              }
              else if (s.equals("Abfrage easy"))
              {
            	  DefAbfrage2.get(g,DefAbfrage2.OUTLINER,A,thisJFrame());
              }
              else if (s.equals("Export"))
                new Export(g, (JFrame)thisFrame, A.iBew>0 ? 0:A.iStt, null, A.iBegriff);
            }
          };
          popEig.setLabel("DefAbfrage-Eigenschaft");
          g.addMenuItem("open",popEig).addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              JCOutlinerNode Nod=GidEigenschaft.getSelectedNode();
              if (Nod!=null && Nod instanceof JCOutlinerFolderNode)
              {
                Eig_open(null);
                ((JCOutlinerFolderNode)Nod).setState(BWTEnum.FOLDER_OPEN_ALL);
                GidEigenschaft.folderChanged(Nod);
                GidEigenschaft.sortByColumn(0,Sort.sortMethod);
              }
              //g.progInfo("open "+GidEigenschaft.getSelectedNode());
            }
          });
          g.addMenuItem("Close",popEig).addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              JCOutlinerNode Nod=GidEigenschaft.getSelectedNode();
              if (Nod!=null && Nod instanceof JCOutlinerFolderNode)
              {
                ((JCOutlinerFolderNode)Nod).setState(BWTEnum.FOLDER_CLOSED);
                GidEigenschaft.folderChanged(Nod);
              }
              //g.progInfo("close "+GidEigenschaft.getSelectedNode());
            }
          });
          g.addMenuItem("Filter",popEig,null,ALS);
          g.addMenuItem("DefEigenschaft",popEig,"DefEigenschaft2",ALS);
          g.addMenuItem("add",popEig,null,ALS);
          g.addMenuItem("Refresh",popEig,null,ALS);
          if (g.Spezial() || bRechte)
          {
            popEig.addSeparator();
            addCbxItem2("System", popEig,SYS,bRechte || g.Def());
            if (g.Spezial())
            {
              addCbxItem2("Berechnung", popEig, CALC, false);
              addCbxItem2("Mehrfach", popEig, MF, false);
              addCbxItem2("Bewegung", popEig, BEW, false);
            }
          }
          GidEigenschaft.getOutliner().addMouseListener(new MouseListener()
          {
            public void mousePressed(MouseEvent ev) {}
            public void mouseClicked(MouseEvent ev)
            {
                //if(ev.getModifiers()==MouseEvent.BUTTON3_MASK)
                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                  popEig.show((Component)ev.getSource(),ev.getX(),ev.getY());
                else if (ev.getModifiers()==MouseEvent.BUTTON1_MASK && ev.getClickCount()==2)
                  Eig_add();
            }
            public void mouseEntered(MouseEvent ev) {}
            public void mouseExited(MouseEvent ev)  {}
            public void mouseReleased(MouseEvent ev){}
          });
          GidEigenschaft.getOutliner().addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{}
			public void keyReleased(KeyEvent e)
			{
			  if (e.getKeyCode()==Global.iPopkey)
			    popEig.show((Component)e.getSource(),0,0);
			}
			public void keyTyped(KeyEvent e)
			{}
		});

          /* ------------------------------- Spalten --------------------------------------- */
          popSpalte.setLabel("DefAbfrage-Spalte");
          MnuHide=g.addMenuItem("ausblenden",popSpalte);
          MnuHide.addActionListener(ALS);
          MnuEdit=g.addMenuItem("Edit",popSpalte);
          MnuEdit.addActionListener(ALS);
          MnuCopy=g.addMenuItem("Kopie",popSpalte);
          MnuCopy.addActionListener(ALS);
          MnuDel=g.addMenuItem("Loeschen",popSpalte);
          MnuDel.addActionListener(ALS);
          MnuDel2=g.addMenuItem("Loeschen2",popSpalte);
          MnuDel2.addActionListener(ALS);
          if (g.Def())
            g.addMenuItem("Export",popSpalte,null,ALS);
          g.addMenuItem("Abfrage easy",popSpalte,null,ALS);
          g.addMenuItem("show",popSpalte,null,ALS);
          g.addMenuItem("showAll2",popSpalte,null,ALS);
          g.addMenuItem("DefVerlauf",popSpalte,null,ALS);
        if (g.Def())
        {
          g.addMenuItem("Info",popSpalte,null,ALS);
          popSpalte.addSeparator();
          g.addMenuItem("Spalten",popSpalte,null,ALS);
          g.addMenuItem("Abfragen",popSpalte,null,ALS);
          g.addMenuItem("Abfragebits",popSpalte,null,ALS);
          g.addMenuItem("Web-Abfragen",popSpalte,null,ALS);
          g.addMenuItem("Spaltenbits",popSpalte,null,ALS);
          g.addMenuItem("Last",popSpalte,null,ALS);
          g.addMenuItem("ohneStt",popSpalte,null,ALS);
          g.addMenuItem("lt_Eig",popSpalte,null,ALS);
          popSpalte.addSeparator();
          g.addMenuItem("TabSp",popSpalte,null,ALS);
//          g.addMenuItem("TabJoker",popSpalte,null,ALS);
          g.addMenuItem("TabV",popSpalte,null,ALS);
          g.addMenuItem("TabVar",popSpalte,null,ALS);
          g.addMenuItem("Spalte_Zuordnung",popSpalte,null,ALS);
          g.addMenuItem("DefEigenschaft",popSpalte,null,ALS);
        }
          /*g.addMenuItem("TabFix",popSpalte).addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              A.TabFixEigenschaften.showGrid("FixEig");
            }
          });*/

        }

        private String checkBit(String s,int i,String sBez,int iV,int iNr)
        {
          if ((i&iV)>0)
            s=(s==null ? "":s+", ")+g.getBegriffS("Checkbox",sBez);
          return s;
        }

        private String getBitsString(int i1,int i2,int i3)
        {
          String s=null;
          s=checkBit(s,i1,"DESC",      0x0002, 1);
          s=checkBit(s,i1,"SpLeer",      0x0004, 2);
          //s=checkBit(s,i1,"Anzeigen",  0x0008, 3);
          s=checkBit(s,i1,"Editierbar",0x0010, 4);
          s=checkBit(s,i1,"Periode",    0x0020, 5);
          s=checkBit(s,i1,"Periodensumme",0x0040, 6);
          s=checkBit(s,i1,"nur positive Werte",0x0080, 7);
          s=checkBit(s,i1,"kein_vorfuellen",0x0100, 8);
          s=checkBit(s,i1,"Sperre",        0x0400, 10);
          s=checkBit(s,i1,"Hochkomma",  0x0800, 11);
          s=checkBit(s,i1,"Unsichtbar",0x1000, 12);
          s=checkBit(s,i1,"Steuern",      0x4000, 14);
          s=checkBit(s,i1,"AutoInc",      0x8000, 15);
          s=checkBit(s,i1,"Eindeutig",0x00010000,16);
          s=checkBit(s,i1,"Always",0x00020000,17);
          s=checkBit(s,i1,"Gueltig2",    0x01000000, 24);
          s=checkBit(s,i1,"keine_Einheit",0x02000000, 25);
          s=checkBit(s,i1,"Mobil",        Abfrage.cstMobil, 26);
          s=checkBit(s,i1,"Gueltig",      0x08000000, 27);
          s=checkBit(s,i1,"Verteiler",0x10000000, 28);
          s=checkBit(s,i1,"Bild",            0x20000000, 29);
          s=checkBit(s,i1,"Refresh",    0x40000000, 30);
          
          s=checkBit(s,i2,"GMT",        0x0002, 33);
          s=checkBit(s,i2,"not save",   0x0004, 34);
          s=checkBit(s,i2,"keine_Gesamtsumme",0x0008, 35);
          s=checkBit(s,i2,"keine_Hauptsumme",0x0010, 36);
          s=checkBit(s,i2,"nachsortieren", 0x0020, 37);
          s=checkBit(s,i2,"kein_Ohr",     0x0040, 38);
          s=checkBit(s,i2,"Importbedingung",  0x0080, 39);
          s=checkBit(s,i2,"kumuliert",  0x0100, 40);
          s=checkBit(s,i2,"ab_Neujahr", 0x0200, 41);
          s=checkBit(s,i2,"Z_Schrift", 0x0400, 42);
          s=checkBit(s,i2,"keine_Leerzeilen", 0x0800, 43);
          s=checkBit(s,i2,"VorZR", 0x1000, 44);
          s=checkBit(s,i2,"angleichen", 0x2000, 45);
          s=checkBit(s,i2,"S_Schrift", 0x4000, 46);
          s=checkBit(s,i2,"nur_Erste", 0x8000, 47);
          s=checkBit(s,i2,"gleiche_Spalte", 0x10000, 48);
          s=checkBit(s,i2,"Stichtag", 0x20000, 49);
          s=checkBit(s,i2,"bei_Stichtag", 0x40000, 50);
          s=checkBit(s,i2,"keine_Sonderzeichen", 0x80000, 51);
          s=checkBit(s,i2,"nur Ziffern", 0x100000, 52);
          s=checkBit(s,i2,"kein_CompressGroup", Abfrage.cstKeinCG, 53);
          s=checkBit(s,i2,"kein_Spalte-Entfernen", 0x400000, 54);
          s=checkBit(s,i2,"set Sync", 0x800000, 55);
          s=checkBit(s,i2,"bedingt_zwingend", 0x1000000, 56);
          s=checkBit(s,i2,"negativ Rot", 0x2000000, 57);
          s=checkBit(s,i2,"Multiselect2", 0x4000000, 58);
          s=checkBit(s,i2,"nicht existent", 0x8000000, 59);
          s=checkBit(s,i2,"nicht gesperrt", 0x10000000, 60);
          s=checkBit(s,i2,"ganzes Jahr", Abfrage.cstGanzesJahr, 61);
          s=checkBit(s,i2,"keinKnoten", Abfrage.cstKeinKnoten, 62);
          
          s=checkBit(s,i3,"Boolean", Abfrage.cstBool, 64);
          s=checkBit(s,i3,"UG", Abfrage.cstUG, 65);
          s=checkBit(s,i3,"FilterOK", Abfrage.cstFilterOK, 66);
          s=checkBit(s,i3,"kein_Titel", Abfrage.cstSpKeinTitel, 67);
          s=checkBit(s,i3,"HH_mm", Abfrage.cstHH_mm, 68);
          s=checkBit(s,i3,"Html", Abfrage.cstHtml, 69);
          s=checkBit(s,i3,"in_Stunden", Abfrage.cstInStunden, 70);
          s=checkBit(s,i3,"Barcode", Abfrage.cstBarcode, 71);
          s=checkBit(s,i3,"keinSubformular", Abfrage.cstNoSubFom, 72);
          // s=checkBit(s,i3,"SaveSoon", Abfrage.cstSaveSoon, 73);
          s=checkBit(s,i3,"keinFav", Abfrage.cstNoFav, 74);
          s=checkBit(s,i3,"Zusatzspalte", Abfrage.cstZusatz, 75);
          s=checkBit(s,i3,"KNZW", Abfrage.cstKNZW, 76);
          // s=checkBit(s,i3,"Detail", Abfrage.cstDetail, 77);
          s=checkBit(s,i3,"nicht_Benutzer", Abfrage.cstNotUser, 78);
          s=checkBit(s,i3,"Farbzeile", Abfrage.cstColorRow, 79);
          s=checkBit(s,i3,"Periodenschnitt", Abfrage.cstPerSchnitt, 80);
          s=checkBit(s,i3,"Gruppierbar", Abfrage.cstGruppierbar, 81);
          s=checkBit(s,i3,"Automatisch", Abfrage.cstAuto, 82);
          s=checkBit(s,i3,"TabEin", Abfrage.cstTabEin, 83);
          s=checkBit(s,i3,"FullScreen", Abfrage.cstFullScr, 84);
          s=checkBit(s,i3,"PDF-Viewer", Abfrage.cstPdfViewer, 85);
          s=checkBit(s,i3,"InPlace", Abfrage.cstInPlace, 86);
          s=checkBit(s,i3,"Banned", Abfrage.cstBanned, 87);
          s=checkBit(s,i3,"Suche", Abfrage.cstSuche, 88);
          s=checkBit(s,i3,"Filter2", Abfrage.cstFilter2, 89);
          s=checkBit(s,i3,"FilterInit", Abfrage.cstFilterInit, 90);
          return s==null?"":s;          
        }

        private void ShowSpalteZuordnung()
        {
          if (A.TabSp.posInhalt("Nummer",Gid.getSelectedNode().getUserData()))
          {
            int iSpalte=A.TabSp.getI("aic_spalte");
            new Tabellenspeicher(g,"select Reihenfolge,z.Titel,v.bezeichnung Stamm"+g.AU_Bezeichnung1("Eigenschaft","z")+" Eigenschaft"+g.AU_Bezeichnung1("Stammtyp","z")+" Stammtyp"+
                                 " from stammview2 v join spalte_Zuordnung z on v.aic_stamm=z.aic_stamm where aic_spalte="+iSpalte,true).showGrid("Spalte "+Gid.getSelectedNode());
          }
        }

        private void ShowSpalten()
        {
          Tabellenspeicher TabS=new Tabellenspeicher(g, new String[] {"Nr", "Bezeichnung", "Bez2", "Original","KennungEig","Kennung","Aic","Datentyp","Lizenz","E","Anzahl","sort","group","Ohrwaschl","Summe","Auswertung","Anzeige","Filter","Format","Pos",
        		  "LängeEig","LängeSp","Breite","Webbreite","Schirm","Druck","X","Y","W","H","Sub1","Sub2","Sub3","KZ","Bits","Calc","HA","Rel","ToggleSight","Icon","Sub","Farbe","Status"});
          Tabellenspeicher TabBefehle=new Tabellenspeicher(g,"select befehl2.aic_spalte,count(*) Anzahl from befehl2"+
              " where aic_spalte"+Static.SQL_in(A.TabSp.getVecSpalte("aic_spalte"))+" group by befehl2.aic_spalte",true);
          int iPos=EdtGKKAb.intValue();
          for(A.TabSp.moveFirst();!A.TabSp.eof();A.TabSp.moveNext())
          {
            TabS.addInhalt("Nr",A.TabSp.getS("Reihenfolge"));
            Vector VecEig = (Vector)A.TabSp.getInhalt("Vec");
            int iEPos=g.TabEigenschaften.getPos("Aic",Sort.geti(VecEig.elementAt(VecEig.size()-1)));
            String sOri=Abfrage.getEigenschaftBezeichnung(g,VecEig);
            TabS.addInhalt("Bezeichnung",Static.beiLeer(A.TabSp.getS("Bezeichnung"),sOri));
            TabS.addInhalt("Bez2",A.TabSp.getS("Bez2"));
            TabS.addInhalt("Original",sOri);
            TabS.addInhalt("KennungEig",g.TabEigenschaften.getS(iEPos,"Kennung"));
            TabS.addInhalt("Kennung",A.TabSp.getS("Kennung"));
            TabS.addInhalt("Aic",g.TabEigenschaften.getI(iEPos,"AIC"));
            TabS.addInhalt("Datentyp",g.TabEigenschaften.getS(iEPos,"Datentyp"));
            TabS.addInhalt("Lizenz",Static.JaNein2((g.TabEigenschaften.getI(iEPos,"bits")&Global.cstEigLizenz)>0));
            TabS.addInhalt("E",VecEig.size());
            TabS.addInhalt("Anzahl",TabBefehle.posInhalt("aic_spalte",A.TabSp.getI("aic_spalte")) ? TabBefehle.getI("Anzahl") : 0);
            TabS.addInhalt("sort",A.TabSp.getI("sortiert"));
            String sOhr=null;
            if (g.TabEigenschaften.getI(iEPos,"Eig2")>0 && (A.TabSp.getI("bits2")&Abfrage.cstKeinOhr)==0)
              sOhr=g.TabEigenschaften.getBezeichnungS(g.TabEigenschaften.getI(iEPos,"Eig2"));
            TabS.addInhalt("Ohrwaschl",sOhr);
            TabS.addInhalt("group",Static.JaNein2((A.TabSp.getI("bits")&Abfrage.cstGruppiert)>0));
            TabS.addInhalt("Summe",A.TabSp.getI("cod_aic_code")==0?"":g.TabCodes.getKennung(A.TabSp.getI("cod_aic_code")));
            TabS.addInhalt("Auswertung",A.TabSp.getI("aic_code")==0?"":g.TabCodes.getKennung(A.TabSp.getI("aic_code")));
            TabS.addInhalt("Anzeige",A.TabSp.getI("Anzeige")==0?"":g.TabCodes.getKennung(A.TabSp.getI("Anzeige")));
            int iFilter=A.TabSp.getI("Filter")==0 && (A.iBits&Abfrage.cstTTO)==0 ? g.TabEigenschaften.getI(iEPos,"abfrage"):A.TabSp.getI("Filter");
            TabS.addInhalt("Filter",iFilter==0?"":g.getBegBez(g.AbfToBeg(iFilter)));
            TabS.addInhalt("Format",A.TabSp.getI("aic_begriff")==0 ? g.TabEigenschaften.getS(iEPos,"Format"):g.TabBegriffe.getKennung(A.TabSp.getI("aic_begriff")));
            int iL=Abfrage.getLaenge(A.TabSp);
            TabS.addInhalt("Pos",iPos);
            iPos=iPos>0 && iL>0 ? iPos+iL:0;
            TabS.addInhalt("LängeEig",g.TabEigenschaften.getI(iEPos,"Laenge"));
            TabS.addInhalt("LängeSp",/*iL==0?g.TabEigenschaften.getI(iEPos,"Laenge"):*/iL);
            TabS.addInhalt("Breite",g.TabEigenschaften.getI(iEPos,"Breite"));
            TabS.addInhalt("Schirm",Abfrage.getLaengeB(A.TabSp));
            TabS.addInhalt("Druck",Abfrage.getLaengeD(A.TabSp));
            TabS.addInhalt("Webbreite",Abfrage.getLaengeW(A.TabSp));
            int iSubFom=A.TabSp.getI("Sub1");
            int iSubAbf=A.TabSp.getI("Sub2");
            int iSubMod=A.TabSp.getI("Sub3");
//            String sGruppeSF=Static.sLeer;
//            String sBezSF=Static.sLeer;
//            if (iSubFom>0)
//            {
//            	int iPosSF=g.TabBegriffe.getPos("Aic",iSubFom);
//            	sGruppeSF=iPosSF<0 ? Static.sLeer:g.TabBegriffgruppen.getKennung(g.TabBegriffe.getI(iPosSF,"Gruppe"));
//            	sBezSF=iPosSF<0  ? Static.sLeer:g.getBegBez3(iPosSF);
////            	if (sGruppe.equals("Abfrage"))
////            		TabS.addInhalt("SubAbf",);
////            	else if (sGruppe.equals("Frame"))
////            		TabS.addInhalt("SubFom",g.getBegBez3(iPosSF));
//            }
            TabS.addInhalt("Sub1",iSubFom>0 ? g.getBegBez(iSubFom):Static.sLeer);
            TabS.addInhalt("Sub2",iSubAbf>0 ? g.getBegBez(iSubAbf):Static.sLeer);
            TabS.addInhalt("Sub3",iSubMod>0 ? g.getBegBez(iSubMod):Static.sLeer);
            TabS.addInhalt("X",A.TabSp.getI("x"));
            TabS.addInhalt("Y",A.TabSp.getI("y"));
            TabS.addInhalt("W",A.TabSp.getI("w"));
            TabS.addInhalt("H",A.TabSp.getI("h"));
            TabS.addInhalt("KZ",A.TabSp.getS("Stil"));
            TabS.addInhalt("ToggleSight",A.TabSp.getS("ToggleSight"));
            TabS.addInhalt("Icon",A.TabSp.getS("Icon"));
            TabS.addInhalt("Sub",A.TabSp.getI("sub"));
            TabS.addInhalt("Farbe",A.TabSp.getS("Farbe"));
            TabS.addInhalt("bits",getBitsString(A.TabSp.getI("bits"),A.TabSp.getI("bits2"),A.TabSp.getI("bits3")));
            TabS.addInhalt("Calc",A.TabSp.isNull("Calc") ? 0:((Tabellenspeicher)A.TabSp.getInhalt("Calc")).getAnzahlElemente(null));
            TabS.addInhalt("HA",A.TabSp.isNull("Gruppe") ? 0:((Vector)A.TabSp.getInhalt("Gruppe")).size());
            TabS.addInhalt("Rel",A.TabSp.isNull("Rel") ? null:g.getStamm(A.TabSp.getI("Rel")));
            TabS.addInhalt("Status",A.TabSp.getS("Status"));
          }

          TabS.delIf("Kennung","");
          TabS.delIf("E",new Integer(1));
          TabS.delIf("Anzahl",Static.Int0);
          TabS.delIf("sort",Static.Int0);
          TabS.delIf("group",Static.sNein);
          TabS.delIf("Ohrwaschl",null);
          TabS.delIf("Rel",null);
          TabS.delIf("Summe","");
          TabS.delIf("Auswertung","");
          TabS.delIf("Anzeige","");
          TabS.delIf("Filter","");
          TabS.delIf("Format","");
          //TabS.delIf("Pos",iPos==0);
          TabS.delIf("LängeEig",Static.Int0);
          TabS.delIf("LängeSp",Static.Int0);
          TabS.delIf("Breite",Static.Int0);
          TabS.delIf("Schirm",Static.Int0);
          TabS.delIf("Druck",Static.Int0);
          TabS.delIf("X",Static.Int0);
          TabS.delIf("Y",Static.Int0);
          TabS.delIf("W",Static.Int0);
          TabS.delIf("H",Static.Int0);
          TabS.delIf("Sub1",Static.sLeer);
          TabS.delIf("Sub2",Static.sLeer);
          TabS.delIf("Sub3",Static.sLeer);
          TabS.delIf("KZ","");
          TabS.delIf("ToggleSight","");
          TabS.delIf("Farbe","");
          TabS.delIf("Icon","");
          TabS.delIf("sub",Static.Int0);
          TabS.delIf("Calc",Static.Int0);
          //TabS.delIf("HA",Static.Int0);
          TabS.delIf("Status","");
          TabS.showGrid("Spalten v. "+A.sDefBez);
        }

        private void ShowAbfragen()
        {
          new Tabellenspeicher(g,"select x.*,Formular+Druck+Export Anzahl from(select b.aic_begriff aic,b.defbezeichnung,b.kennung"+
                               g.AU_Bezeichnung1("bewegungstyp","b")+" Bewegungstyp"+g.AU_Bezeichnung1("stammtyp","b")+" Stammtyp"+
                               ",(case when"+g.bit("bits",Abfrage.cstNurModell)+" then 1 else 0 end) nur_Modell"+
                               ",(select count(*) from befehl2 join spalte s on befehl2.aic_spalte=s.aic_spalte where s.aic_abfrage=a.aic_abfrage) Modell"+
                               ",(case when "+g.bit("bits",Abfrage.cstTTO)+" then 1 else 0 end)"+" TTO"+
                               ",(select count(*) from darstellung where aic_begriff=b.aic_begriff) Formular"+
                               ",(select count(*) from abschnitt where aic_begriff=b.aic_begriff) Druck"+
                               ",(case when"+g.bit("bits",Abfrage.cstExportierbar)+" then 1 else 0 end) Export"+
                               " from begriff b join abfrage a on b.aic_begriff=a.aic_begriff) x order by TTO",true).showGrid("Abfragen");
        }

        /*private void ShowAll()
        {
          ShowAbfragen();
          if (g.Info())
            ShowAbfrageBits();
        }*/

        /*private JDialog createTabDlg(String s,Tabellenspeicher Tab,AUOutliner Out)
        {
          JDialog FomGid2=new JDialog((JDialog)null, s, false);
          FomGid2.getContentPane().add("Center",Out);
          Tab.showGrid(Out);
          return FomGid2;
        }*/

        private void ShowSpaltenBits()
        {
          Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr", "Sub", "Kennung","Bezeichnung","Anzahl"});
          addBitS(Tab2, "aic_Rolle","Rolle", 0, -15);
          addBitS(Tab2, "aic_Farbe","Farbe2", 0, -14);
          addBitS(Tab2, "sta_aic_stamm","Bedingt", 0, -13);
          addBitS(Tab2, "spa_aic_spalte","SubSpalte", 0, -12);
          addBitS(Tab2, "cod3_aic_code","Anzeigeart", 0, -11);
          addBitS(Tab2, "cod2_aic_code","Ausrichtung", 0, -10);
          addBitS(Tab2, "aic_code","Auswertformat", 0, -9);
          addBitS(Tab2, "cod_aic_code","Ergebnis", 0, -8);
          addBitS(Tab2, "Filter","Filter", 0, -7);
          addBitS(Tab2, "aic_begriff","Format", 0, -6);
          addBitS(Tab2, "aic_stammtyp","Hierarchie", 0, -5);
          addBitS(Tab2, "ToggleSight","ToggleSight", 0, -4);
          addBitS(Tab2, "Icon","Icon", 0, -3);
          addBitS(Tab2, "Farbe","Farbe", 0, -2);
          addBitS(Tab2, "STIL","KZ", 0, -1);
          addBitS(Tab2, "cstGruppiert","Gruppiert",0x0001, 0);
          addBitS(Tab2, "cstSortDesc","DESC",      0x0002, 1);
          addBitS(Tab2, "cstSpLeer","SpLeer",      0x0004, 2);
          addBitS(Tab2, "cstAnzeigen","Anzeigen",  0x0008, 3);
          addBitS(Tab2, "cstEditierbar","Editierbar",0x0010, 4);
          addBitS(Tab2, "cstPeriode","Periode",    0x0020, 5);
          addBitS(Tab2, "cstPeriodensumme","Periodensumme",0x0040, 6);
          addBitS(Tab2, "cstPositiv","nur positive Werte",0x0080, 7);
          addBitS(Tab2, "cstKeinAutoDate","kein_vorfuellen",0x0100, 8);
          addBitS(Tab2, "cstSperre","Sperre",        0x0400, 10);
          addBitS(Tab2, "cstHochkomma","Hochkomma",  0x0800, 11);
          addBitS(Tab2, "cstUnsichtbar","Unsichtbar",0x1000, 12);
          addBitS(Tab2, "cstLast","last",0x2200,0x0000, 9,1);
          addBitS(Tab2, "cstKeinAutoLast","leer",0x2200,0x0200, 9,2);
          addBitS(Tab2, "cstNimmSync","Sync",   0x2200,0x2000, 9,3);
          addBitS(Tab2, "cstLimit","limit",     0x2200,0x2200, 9,4);
          addBitS(Tab2, "cstSteuern","Steuern",      0x4000, 14);
          addBitS(Tab2, "cstAutoInc","AutoInc",      0x8000, 15);
          addBitS(Tab2, "cstCombo","Combobox",   Abfrage.cstShow,    0x0000, 22,1);
          addBitS(Tab2, "cstRadio","Radiobutton",Abfrage.cstShow,0x00400000, 22,2);
          addBitS(Tab2, "cstButtons","Button",   Abfrage.cstShow,0x00800000, 22,3);
          addBitS(Tab2, "cstCombo2","Combobox2", Abfrage.cstShow,0x00C00000, 22,4);
          addBitS(Tab2, "cstGueltig2","Gueltig2",    0x01000000, 24);
          addBitS(Tab2, "cstKeineEinheit","keine_Einheit",0x02000000, 25);
          addBitS(Tab2, "cstMobil","Mobil",      Abfrage.cstMobil, 26);
          addBitS(Tab2, "cstGueltig","Gueltig",      0x08000000, 27);
          addBitS(Tab2, "cstSpVerteiler","Verteiler",0x10000000, 28);
          addBitS(Tab2, "cstBild","Bild",            0x20000000, 29);
          addBitS(Tab2, "cstSpRefresh","Refresh",    0x40000000, 30);
          //------- Spaltenbits2
          addBitS(Tab2, "cstStt",null,              0x0001, 32);
          addBitS(Tab2, "cstSpGMT","GMT",            0x0002, 33);
          addBitS(Tab2, "cstSpNotSave","not save",   0x0004, 34);
          addBitS(Tab2, "cstKeineGSumme","keine_Gesamtsumme",0x0008, 35);
          addBitS(Tab2, "cstKeine1Summe","keine_Hauptsumme",0x0010, 36);
          addBitS(Tab2, "cstSpSort","nachsortieren", 0x0020, 37);
          addBitS(Tab2, "cstKeinOhr","kein_Ohr",     0x0040, 38);
          addBitS(Tab2, "cstImportBed","Importbedingung",  0x0080, 39);
          addBitS(Tab2, "cstKumuliert","kumuliert",  0x0100, 40);
          addBitS(Tab2, "cstAbNeujahr","ab_Neujahr", 0x0200, 41);
          addBitS(Tab2, "cstFett","Z_Schrift", 0x0400, 42);
          addBitS(Tab2, "cstKeineLeerzeilen","keine_Leerzeilen", 0x0800, 43);
          addBitS(Tab2, "cstVorZR","VorZR", 0x1000, 44);
          addBitS(Tab2, "cstErgaenzen","angleichen", 0x2000, 45);
          addBitS(Tab2, "cstFett2","S_Schrift", 0x4000, 46);
          addBitS(Tab2, "cstNurErste","nur_Erste", 0x8000, 47);
          addBitS(Tab2, "cstSpGleiche","gleiche_Spalte", 0x10000, 48);
          addBitS(Tab2, "cstStichtag","Stichtag", 0x20000, 49);
          addBitS(Tab2, "cstAIC_Bez","AIC_Bez", Abfrage.cstAIC_Bez, 50);
          addBitS(Tab2, "cstBuchZahl","keine_Sonderzeichen", 0x80000, 51);
          addBitS(Tab2, "cstZiffern","nur Ziffern", 0x100000, 52);
          addBitS(Tab2, "cstKeinCG","kein_CompressGroup", Abfrage.cstKeinCG, 53);
          addBitS(Tab2, "cstSpKeinDel","kein_Spalte-Entfernen", 0x400000, 54);
          addBitS(Tab2, "cstSetSync","set_Sync", 0x800000, 55);
          addBitS(Tab2, "cstBedZwang","bedingt_zwingend", 0x1000000, 56);
          addBitS(Tab2, "cstNegativRot","negativ_Rot", 0x2000000, 57);
          addBitS(Tab2, "cstMulti","Multiselect2", 0x4000000, 58);
          addBitS(Tab2, "cstWeg","nicht_existent", 0x8000000, 59);
          addBitS(Tab2, "cstOrigEinh","Original-Einheit", Abfrage.cstOrigEinh, 60);
          //addBitS(Tab2, "cstOffen","nicht gesperrt", 0x10000000, 60);
          addBitS(Tab2, "cstGanzesJahr","ganzes_Jahr", 0x20000000, 61);
          addBitS(Tab2, "cstKeinKnoten","keinKnoten", Abfrage.cstKeinKnoten, 62);
          //------- Spaltenbits3
          addBitS(Tab2, "cstBool","Boolean", Abfrage.cstBool, 64);
          addBitS(Tab2, "cstUG","UG", Abfrage.cstUG, 65);
          addBitS(Tab2, "cstFilterOK","FilterOK", Abfrage.cstFilterOK, 66);
          addBitS(Tab2, "cstSpKeinTitel","kein_Titel", Abfrage.cstSpKeinTitel, 67);
          addBitS(Tab2, "cstHH_mm","HH_mm", Abfrage.cstHH_mm, 68);
          addBitS(Tab2, "cstHtml","Html", Abfrage.cstHtml, 69);
          addBitS(Tab2, "cstInStunden","in Stunden", Abfrage.cstInStunden, 70);
          addBitS(Tab2, "cstBarcode","Barcode", Abfrage.cstBarcode, 71);
          addBitS(Tab2, "cstEigCho","Cho",   Abfrage.cstEE,    Global.cstEigCho, 72,1);
          addBitS(Tab2, "cstEigRad","Rad",   Abfrage.cstEE,    Global.cstEigRad, 72,2);
          addBitS(Tab2, "cstEigBtn","Btn",   Abfrage.cstEE,    Global.cstEigBtn, 72,3);
          addBitS(Tab2, "cstEigSbo","Sbo",   Abfrage.cstEE,    Global.cstEigSbo, 72,4);
          addBitS(Tab2, "cstEigPop","Pop",   Abfrage.cstEE,    Global.cstEigPop, 72,5);
          addBitS(Tab2, "cstEigCbo","Cbo",   Abfrage.cstEE,    Global.cstEigCbo, 72,6);
          addBitS(Tab2, "cstEigCbx","Cbx",   Abfrage.cstEE,    Global.cstEigCbx, 72,8);
          addBitS(Tab2, "cstEigSwb","Swb",   Abfrage.cstEE,    Global.cstEigSwb, 72,9);
          addBitS(Tab2, "cstEigAuC","AutoComplete", Abfrage.cstEE, Global.cstEigAuC, 72,10);
          addBitS(Tab2, "cstEigDT","DT",     Abfrage.cstEE,    Global.cstEigDT,  72,11);
          addBitS(Tab2, "cstEigTime","Time", Abfrage.cstEE,    Global.cstEigTime,72,12);
          addBitS(Tab2, "cstEigSlider","Slider", Abfrage.cstEE,    Global.cstEigSlider,72,13);
          addBitS(Tab2, "cstEigWebTitel","WebTitel", Abfrage.cstEE,    Global.cstEigWebTitel,72,14);
          addBitS(Tab2, "cstNoSubFom","keinSubformular", Abfrage.cstNoSubFom, 76);
          // addBitS(Tab2, "cstSaveSoon","SaveSoon", Abfrage.cstSaveSoon, 77); -> cstDatenHolen
          addBitS(Tab2, "cstNoFav","keinFav", Abfrage.cstNoFav, 78);
          addBitS(Tab2, "cstZusatz","Zusatzspalte", Abfrage.cstZusatz, 79);
          addBitS(Tab2, "cstKNZW","KNZW", Abfrage.cstKNZW, 80);
          // addBitS(Tab2, "cstDetail","Detail", Abfrage.cstDetail, 81); -> cstDatenHolen
          addBitS(Tab2, "cstNurAicD","nurAicDaten",   Abfrage.cstDatenHolen,    Abfrage.cstNurAicD, 81,1);
          addBitS(Tab2, "cstDanachD","danachDaten",   Abfrage.cstDatenHolen,    Abfrage.cstDanachD, 81,2);
          addBitS(Tab2, "cstXxxDaten","XxxDaten",   Abfrage.cstDatenHolen,    Abfrage.cstXxxDaten, 81,3);
          addBitS(Tab2, "cstNotUser","nicht Benutzer", Abfrage.cstNotUser, 82);
          addBitS(Tab2, "cstColorRow","Farbzeile", Abfrage.cstColorRow, 83);
          addBitS(Tab2, "cstPerSchnitt","Periodenschnitt", Abfrage.cstPerSchnitt, 84);
          addBitS(Tab2, "cstGruppierbar","Gruppierbar", Abfrage.cstGruppierbar, 85);
          addBitS(Tab2, "cstAuto","Automatisch", Abfrage.cstAuto, 86);
          addBitS(Tab2, "cstTabEin","TabEin", Abfrage.cstTabEin, 87);
          addBitS(Tab2, "cstFullScr","FullScreen", Abfrage.cstFullScr, 88);
          addBitS(Tab2, "cstPdfViewer","PDF-Viewer", Abfrage.cstPdfViewer, 89);
          addBitS(Tab2, "cstInPlace","InPlace", Abfrage.cstInPlace, 90);
          addBitS(Tab2, "cstBanned","Banned", Abfrage.cstBanned, 91);
          addBitS(Tab2, "cstSuche","Suche", Abfrage.cstSuche, 92);
          addBitS(Tab2, "cstFilter2","Filter2", Abfrage.cstFilter2, 93);
          addBitS(Tab2, "cstFilterInit","FilterInit", Abfrage.cstFilterInit, 94);
          final JDialog FomGid = new JDialog((Frame)thisFrame, "Tabellen", false);
          AUOutliner Grid = new AUOutliner();
          FomGid.getContentPane().add("Center", Grid);
          Tab2.showGrid(Grid);
          FomGid.setSize(400, 600);
          Static.centerComponent(FomGid,thisFrame);
          Grid.addActionListener(new JCActionListener()  {
              public void actionPerformed(JCActionEvent ev) {
                JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
                int i=Sort.geti((Vector)Nod.getLabel(),0);
                int i2=Sort.geti((Vector)Nod.getLabel(),1);
                boolean b3=i>63;
                boolean b2=!b3 && i>31;
                if (b3)
                  i-=64;
                else if (b2)
                  i-=32;
                if (b3)
                	g.fixtestInfo(Nod+":"+i+"/"+b2);
                long l=(long)Math.pow(2,i);
                long l2=0;
                if (i2>0)
                {
                  if (i==9)
                  {
                    l=0x2200;
                    l2=i2==1 ? 0x0000:i2==2 ? 0x0200:i2==3 ? 0x2000:l;
                  }
                  else if (i==22)
                  {
                    l=Abfrage.cstShow;
                    l2=i2==1 ? 0x0000:i2==2 ? 0x00400000:i2==3 ? 0x00800000:l;
                  }
                  else if (i==8) // 72-64 Einzeleingabe-Art
                  {
                	  l=Abfrage.cstEE;
                	  l2=i2==1 ? Global.cstEigCho:i2==2 ? Global.cstEigRad:i2==3 ? Global.cstEigBtn:i2==4 ? Global.cstEigSbo:i2==5 ? Global.cstEigPop:i2==6 ? Global.cstEigCbo:i2==8 ? Global.cstEigCbx:
                		  i2==9 ? Global.cstEigSwb:i2==10 ? Global.cstEigAuC:i2==11 ? Global.cstEigDT:i2==12 ? Global.cstEigTime:i2==13 ? Global.cstEigSlider:i2==14 ? Global.cstEigWebTitel:999;
                  }
                  else if (i==17) // 81-64 Daten gleich oder später holen
                  {
                	  l=Abfrage.cstDatenHolen;
                    l2=i2==3 ? Abfrage.cstXxxDaten:i2==1 ? Abfrage.cstNurAicD:i2==2 ? Abfrage.cstDanachD:999;
                  }
                }
                String sBits="s.bits"+(b3?"3":b2?"2":"");
                //g.fixtestError("i/i2="+i+"/"+i2+", sBits="+sBits);
                String sSp=i<0 ? "s."+Sort.gets(Nod.getLabel(),2):null;
                if (sSp==null)
                	;
                else if (sSp.indexOf("aic_code")>0)
                	sSp="(select kennung from code where aic_code="+sSp+") "+ Sort.gets(Nod.getLabel(),3);
                else if (sSp.indexOf("aic_Rolle")>0)
                	sSp="(select kennung from Rolle where aic_Rolle="+sSp+") "+ Sort.gets(Nod.getLabel(),3);
                else if (sSp.indexOf("aic_Farbe")>0)
                	sSp="(select kennung from Farbe where aic_Farbe="+sSp+") "+ Sort.gets(Nod.getLabel(),3);
                else if (sSp.indexOf("sta_aic_stamm")>0)
                	sSp="(select bezeichnung from stammview2 where aic_rolle is null and aic_stamm="+sSp+") "+ Sort.gets(Nod.getLabel(),3);
                else if (sSp.indexOf("aic_begriff")>0)
                	sSp="(select kennung from begriff where aic_begriff="+sSp+") "+ Sort.gets(Nod.getLabel(),3);
                else if (sSp.equals("s.Filter"))
                	sSp="(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage="+sSp+") "+ Sort.gets(Nod.getLabel(),3);
                else if (sSp.indexOf("aic_stammtyp")>0)
                	sSp="(select kennung from stammtyp where aic_stammtyp="+sSp+") "+ Sort.gets(Nod.getLabel(),3);
                Tabellenspeicher Tab = new Tabellenspeicher(g,"select defbezeichnung,b.kennung,reihenfolge pos"+g.AU_Bezeichnung1("bewegungstyp","b")+" Bewegungstyp"+
                    g.AU_Bezeichnung1("stammtyp","b")+" Stammtyp,b.aic_begriff,b.web,b.Tod"+g.bei("s.bits", Abfrage.cstEditierbar, "edit")+(sSp!=null ? ","+sSp:"") +" from spalte s join abfrage a on s.aic_abfrage=a.aic_abfrage join begriff b on b.aic_begriff=a.aic_begriff where "+
                    (i<0 ? "s."+Sort.gets(Nod.getLabel(),2)+" is not null":l2>0 ? g.bits(sBits,l)+"="+l2:g.bit(sBits,l)),true);
                //g.fixtestError("SQL="+Tab.getSQL());
                //if(Tab.FrmGrid != null)
                //  Tab.FrmGrid.dispose();
                //Tab.showGrid("Spalten mit bit " + Sort.gets(((Vector)Nod.getLabel()).elementAt(3)), /*bModal ? FomGid :*/ null);
                ShowOpenAbf(g,FomGid,"Spalten mit bit " + Sort.gets(Nod.getLabel(),3),Tab);

              }
          });
          FomGid.setVisible(true);
        }

        public static void ShowOpenAbf(Global g,Window FomGid,String sTitel,Tabellenspeicher Tab)
        {
          JDialog FomGid2=FomGid instanceof JDialog ? new JDialog((JDialog)FomGid,sTitel ,false):new JDialog((JFrame)FomGid,sTitel ,false);
                AUOutliner Grid2 = new AUOutliner();
                //FomGid2.getContentPane().add("Center", Grid2);
                Tab.showGrid(Grid2);
                Container CP=FomGid2.getContentPane();
                CP.add("Center",Grid2);
                Tab.addSouth(CP,FomGid2,Grid2);
                FomGid2.setSize(1200, 600);
                Grid2.addActionListener(new JCActionListener() {
                  public void actionPerformed(JCActionEvent ev) {
                    JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
                    int iAic=Sort.geti(Nod.getLabel(),5);
                    //g.testInfo("Begriff="+iAic);
                    int iStt=SQL.getInteger(g,"select "+g.isnull()+"-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff="+iAic);
                    get(g, new ShowAbfrage(g, iAic, Abfrage.cstBegriff), iStt,null,false,-1/*1-iNr*/).show();
                  }
                });
                Grid2.addItemListener(new JCOutlinerListener()
                {
                  public void itemStateChanged(JCItemEvent e) {}
                  public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {}
                  public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {}
                  public void outlinerNodeSelectBegin(JCOutlinerEvent e) {}

                  public void outlinerNodeSelectEnd(JCOutlinerEvent e)
                  {
                    Tab.showColumnInTextfield(Grid2);
                  }
                });
                FomGid2.toFront();
                FomGid2.setVisible(true);
                Static.centerComponent(FomGid2,FomGid);
        }

        private void ShowAbfrageBits()
        {
            Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr", "Kennung","Bezeichnung","Anzahl"});
            addArt(Tab2,"Kennzeichen","Kennzeichen",-18);
            addArt(Tab2,"WebStamm","WebStamm",-17);
            addArt(Tab2,"AbfErw","Erweitert",-16);
            addArt(Tab2,"AbfDetail","Detail",-15);
            addArt(Tab2,"AbfFilter","Filter",-14);
            addArt(Tab2,"aic_benutzergruppe","Benutzergruppe",-11);
            addArt(Tab2,"aic_code","Tabellentyp",-10);
            addArt(Tab2,"Mod_aic_modell","Modell2",-9);
            addArt(Tab2,"aic_modell","Modell",-8);
            addArt(Tab2,"aic_Rolle","Rolle",-7);
            addArt(Tab2,"Prog","Programm",-6);
            addBit(Tab2, "JEDER","Jeder", 0, -5);
            addBit(Tab2, "TOD","Tod", 0, -4);
            addBit(Tab2, "WEB","Web", 0, -3);
            addBit(Tab2, "AIC_SCHRIFT","Schrift", 0, -2);
            addBit(Tab2, "Autorefresh","Autorefresh", 0, -1);
            addBit(Tab2, "cstKeinZeitraum","kein_Bew-Zeitraum", 0x0001, 0);
            addBit(Tab2, "cstKeinStamm","kein_Stammsatz", 0x0002, 01);
            addBit(Tab2, "cstSumme","Summe", 0x0004, 02);
            addBit(Tab2, "cstKeinRefresh","kein_Refresh", 0x0008, 03);
            addBit(Tab2, "cstNurModell","nur_Modell", 0x0010, 04);
            addBit(Tab2, "cstExportierbar","exportierbar", 0x0020, 05);
            addBit(Tab2, "cstDruckbar","druckbar", 0x0040, 06);
            addBit(Tab2, "cstMengen","Mengen", 0x0080, 07);
            addBit(Tab2, "cstKeinNeu","kein_Neu", 0x0100, 8);
            addBit(Tab2, "cstKeinSave","kein_Speichern", 0x0200, 9);
            addBit(Tab2, "cstNichtUpdaten","nicht_updaten", 0x0400, 10);
            addBit(Tab2, "cstSubrollen","Subrollen", 0x0800, 11);
            addBit(Tab2, "cstAuswertung","Auswertung", 0x1000, 12);
            addBit(Tab2, "cstImportierbar","importierbar", 0x2000, 13);
            addBit(Tab2, "cstVersionsup","Versionsupdate", 0x4000, 14);
            addBit(Tab2, "cstKeinDel","kein_Entfernen", 0x8000, 15);
            addBit(Tab2, "cstAnhaengen","anhaengen", 0x00010000, 16);
            addBit(Tab2, "cstKeinTitel","kein_Titel", 0x00020000, 17);
            addBit(Tab2, "cstKeinStt","kein_Stammtyp", 0x00040000, 18);
            addBit(Tab2, "cstFirst  ","First", 0x00080000, 19);
            addBit(Tab2, "cstPlanung ",null, 0x00100000, 20);
            addBit(Tab2, "cstPlanungF ",null, 0x00200000, 21);
            addBit(Tab2, "cstKeinStammZeitraum","kein_Stamm-Zeitraum", 0x400000, 22);
            addBit(Tab2, "cstEntfernte","auch_entfernte", 0x00800000, 23);
            addBit(Tab2, "cstPersoenlich","persoenlich", 0x01000000, 24);
            addBit(Tab2, "cstStempelimport","Stempelimport", 0x02000000, 25);
            addBit(Tab2, "cstView","View", 0x04000000, 26);
            addBit(Tab2, "cstBewVoll","BewVoll", 0x08000000, 27);
            addBit(Tab2, "cstFilter","Filter", 0x10000000, 28);
            addBit(Tab2, "cstZR_Wahl","ZR_Wahl", Abfrage.cstZR_Wahl, 29);
            addBit(Tab2, "cstSynchron","Synchron", 0x40000000, 30);
            addBit(Tab2, "cstChanges","Changes", 0x80000000l, 31);
            addBit(Tab2, "cstVerteiler","Verteiler", 0x100000000l, 32);
            addBit(Tab2, "cstNoChange","no_change", 0x200000000l, 33);
            addBit(Tab2, "cstFeiertage","Feiertage", 0x400000000l, 34);
            addBit(Tab2, "cstFremdStamm","FremdStamm", 0x800000000l, 35);
            addBit(Tab2, "cstDistinct","Distinct", 0x1000000000l, 36);
            addBit(Tab2, "cstVerdichten","verdichten", 0x2000000000l, 37);
            addBit(Tab2, "cstEinzeldaten","Einzeldaten", 0x4000000000l, 38);
            addBit(Tab2, "cstTTO","TTO", 0x8000000000l, 39);
            addBit(Tab2, "cstCompress","Compress", 0x10000000000l, 40);
            addBit(Tab2, "cstKeineGesamtsumme","keine_Gesamtsumme", 0x20000000000l, 41);
            addBit(Tab2, "cstPriviligiert","priviligiert", 0x40000000000l, 42);
            addBit(Tab2, "cstVorletzteEbene","vorletzte Ebene", 0x80000000000l, 43);
            addBit(Tab2, "cst0Werte","0-Werte", 0x100000000000l, 44);
            addBit(Tab2, "cstLeer","leer", 0x200000000000l, 45);
            addBit(Tab2, "cstKeinAustritt","kein_Austritt", 0x400000000000l, 46);
            addBit(Tab2, "cstStammstichtag","Stammstichtag", 0x800000000000l, 47);
            addBit(Tab2, "cstLDATE","LDATE", 0x1000000000000l, 48);
            addBit(Tab2, "cstNoSort","nicht_sortierbar", 0x2000000000000l, 49);
            addBit(Tab2, "cstGleiche","Gleiche", 0x4000000000000l, 50);
            addBit(Tab2, "cstFremdJoker","FremdJoker", 0x8000000000000l, 51);
            addBit(Tab2, "cstUmsortieren","Umsortieren", 0x10000000000000l, 52);
            addBit(Tab2, "cstAusblenden","Ausblenden", 0x20000000000000l, 53);
            addBit(Tab2, "cstNachSave","nach Save", 0x40000000000000l, 54);
            addBit(Tab2, "cstWeiterfuehren","Weiterfuehren", 0x80000000000000l, 55);
            addBit(Tab2, "cstKeinTest","kein_Test", 0x100000000000000l, 56);
            addBit(Tab2, "cstBerechtigung","Berechtigung", 0x200000000000000l, 57);
            addBit(Tab2, "cstHierarchie",null, 0x400000000000000l, 58);
            addBit(Tab2, "cstHS","HS", 0x800000000000000l, 59);
            addBit(Tab2, "cstGruppe","horiz. Auflösung", 0x1000000000000000l, 60);
            addBit(Tab2, "cstCalc",null, 0x2000000000000000l, 61);
            addBit(Tab2, "cstANR","ANR", 0x4000000000000000l, 62);
            
            addBit(Tab2, "del: cstAbfFX","JavaFX", /*Abfrage.cstAbfFX*/1, 64);
            addBit(Tab2, "cstRefreshNS","refreshNS", Abfrage.cstRefreshNS, 65);
            addBit(Tab2, "cstKeinBez","kein_Abfragetitel", Abfrage.cstKeinBez, 66);
            addBit(Tab2, "cstNoSB","noSB", Abfrage.cstNoSB, 67);
            addBit(Tab2, "cstAbfMulti","Multiselect3", Abfrage.cstAbfMulti, 68);
            addBit(Tab2, "cstAbfAPI","API", Abfrage.cstAbfAPI, 69);
            addBit(Tab2, "del: cstErsteOffen","erste_offen", /*Abfrage.cstErsteOffen*/0x0040, 70);
            addBit(Tab2, "cstCompress2","compress2", Abfrage.cstCompress2, 71);
            addBit(Tab2, "del: cstNullWeg","Nullebene_weg", 0x0100, 72);
            addBit(Tab2, "cstSubFom","Subformular", Abfrage.cstSubFom, 73);
            addBit(Tab2, "cstSuche2","Suche2", Abfrage.cstSuche2, 74);
            addBit(Tab2, "cstKeinMandant","kein_Mandant", Abfrage.cstKeinMandant, 75);
            addBit(Tab2, "cstTEdit","TEdit", Abfrage.cstTEdit, 76);
            addBit(Tab2, "del: cstAbfWeb","Web", 0x2000, 77);
            addBit(Tab2, "cstAbfSystem","System", Abfrage.cstAbfSystem, 78);
            addBit(Tab2, "del: cstHochTitel","HochTitel", 0x8000, 79);
            addBit(Tab2, "del: cstWebFom","Web-Formular", /*Abfrage.cstWebFom*/0x10000, 80);
            addBit(Tab2, "cstDialogEdit","DialogEdit", Abfrage.cstDialogEdit, 81);
            addBit(Tab2, "cstLokZR","lokaler_Zeitraum", Abfrage.cstLokZR, 82);
            addBit(Tab2, "cstJVecAlle","JokerVec_alle", Abfrage.cstJVecAlle, 83);
            addBit(Tab2, "cstkeinSyncVec","kein_SyncVec", Abfrage.cstkeinSyncVec, 84);
            addBit(Tab2, "cstAbfJahr","Abfrage_Jahr", Abfrage.cstAbfJahr, 85);
            addBit(Tab2, "cstDelZw","Delete_Empty", Abfrage.cstDelZw, 86);
            addBit(Tab2, "cstModellDlg","Modell_Dialog", Abfrage.cstModellDlg, 87);
            addBit(Tab2, "cstQryAlle","alleBeiKein", Abfrage.cstQryAlle, 88);
            addBit(Tab2, "cstForStatus","forStatus", Abfrage.cstForStatus, 89);
            addBit(Tab2, "cstSbRefresh","SB-Refresh", Abfrage.cstSbRefresh, 90);
            addBit(Tab2, "cstModFarbe","ModellFarbe", Abfrage.cstModFarbe, 91);
            addBit(Tab2, "cstPersSperre","PersSperre", Abfrage.cstPersSperre, 92);
            addBit(Tab2, "cstKeineEinheit","keine_Einheit", Abfrage.cstKeineME, 93);
            addBit(Tab2, "cstSQL2","SQL_aufgeteilt", Abfrage.cstSQL2, 94);

            final JDialog FomGid = new JDialog((Frame)thisFrame, "Tabellen", false);
            AUOutliner Grid = new AUOutliner();
            FomGid.getContentPane().add("Center", Grid);
            Tab2.showGrid(Grid);
            FomGid.setSize(400, 600);
            Static.centerComponent(FomGid,thisFrame);
            Grid.addActionListener(new JCActionListener() {
              public void actionPerformed(JCActionEvent ev) {
                JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
                int i=Tabellenspeicher.geti(((Vector)Nod.getLabel()).elementAt(0));
                showBitoW(FomGid,i,false,ev);    
              }
            });
            FomGid.setVisible(true);
        }
        
        private void showBitoW(JDialog FomGid,int i,boolean b,JCActionEvent ev)
        {
        	JCOutlinerNode Nod = ev==null ? null:((AUOutliner)ev.getSource()).getSelectedNode();
        	boolean b2=i>63;
            if (b2) i-=64;
            long l=i<0 ? 0:(long)Math.pow(2,i);
            String sSp=null;
            String sSp2=null;
            if (i<0)
            {
          	  sSp=Sort.gets(Nod.getLabel(), 1);
          	  if (sSp.equals("Prog"))
          		  sSp2="(select kennung from code where aic_code=b."+sSp+") "+ Sort.gets(Nod.getLabel(),2);
          	  else if (sSp.equals("aic_Rolle"))
          		  sSp2="(select kennung from Rolle where aic_Rolle=b."+sSp+") "+ Sort.gets(Nod.getLabel(),2);
          	  else if (sSp.equals("aic_code"))
          		  sSp2="(select kennung from Code where aic_Code=b."+sSp+") "+ Sort.gets(Nod.getLabel(),2);
          	  else if (sSp.equals("AIC_SCHRIFT"))
          		  sSp2="(select kennung from SCHRIFT where AIC_SCHRIFT=b."+sSp+") "+ Sort.gets(Nod.getLabel(),2);
          	  else if (sSp.equals("WebStamm"))
        		  sSp2="(select bezeichnung from stammview2 where AIC_Stamm=a."+sSp+") "+ Sort.gets(Nod.getLabel(),2);
          	  else if (sSp.equals("aic_benutzergruppe"))
          		  sSp2="(select kennung from benutzergruppe where AIC_benutzergruppe=a."+sSp+") "+ Sort.gets(Nod.getLabel(),2);
          	  else if (sSp.startsWith("Abf"))
          	  {
          		  int iC=g.getCodeAic("Zuordnungsart", i==-14 ?"Filter":i==-15?"Detail":"erweitert");
          		  sSp="(select beg_aic_begriff from begriff_zuordnung where aic_begriff=b.aic_begriff and Art="+iC+")";
          		  sSp2="(select defbezeichnung from begriff join begriff_zuordnung z on z.beg_aic_begriff=begriff.aic_begriff where z.aic_begriff=b.aic_begriff and z.Art="+iC+") "+Sort.gets(Nod.getLabel(),2);	  
          	  }
          	  else
          		  sSp2=sSp;
          		
            }
            //g.fixtestInfo("l="+l);
            Tabellenspeicher Tab = new Tabellenspeicher(g,"select defbezeichnung,kennung,a.Spalten"+
                g.AU_Bezeichnung1("bewegungstyp","b")+" Bewegungstyp"+g.AU_Bezeichnung1("stammtyp","b")+" Stammtyp,b.aic_begriff,b.web"+
                	",(select defbezeichnung from begriff join modell on begriff.aic_begriff=modell.aic_begriff where aic_modell=a.aic_modell) Modell1"+
                	",(select defbezeichnung from begriff join modell on begriff.aic_begriff=modell.aic_begriff where aic_modell=a.mod_aic_modell) Modell2"+(sSp2==null ? "":","+sSp2)+
                	(b ? ",b.web>0 webAbf,(abits2&"+Abfrage.cstAbfSystem+")>0 webSystem,(abits2&"+Abfrage.cstLokZR+")>0 lokZR,(select kennung from code where aic_code=b.prog) Prog":"")+
                	" from begriff b join abfrage a on b.aic_begriff=a.aic_begriff where "+(b ? g.bits("abits2", 0x0001)+">0 or web=1":l>0 ? g.bit(b2 ? "abits2":"b.bits",l):
                		(i==-1 ? "autorefresh":i==-2 ? "AIC_SCHRIFT":i==-3 ? "WEB":i==-4 ? "TOD":i==-5 ? "JEDER":sSp)+" is not null")+" order by defbezeichnung",true);
            //g.fixtestInfo(Tab.getSQL());
            ShowOpenAbf(g,FomGid,"Abfragen mit " + (b ? "JavaFX/WEB":"bit "+i),Tab); 
        }
        
        private void addArt(Tabellenspeicher Tab2,String sConst,String sBez,int i)
        {
      	  Tab2.addInhalt("Nr",i);
      	  Tab2.addInhalt("Kennung",sConst);
      	  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getShow(sBez));
      	  if (sConst.startsWith("Abf"))
      	  {
      		int iC=g.getCodeAic("Zuordnungsart", i==-14 ?"Filter":i==-15?"Detail":"erweitert");
      		Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from begriff_zuordnung where Art="+iC));
      	  }
      	  else
      		  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from begriff b join Abfrage a on b.aic_begriff=a.aic_begriff where "+sConst+" is not null"));
        }

        private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
        {
          Tab2.addInhalt("Nr",i);
          Tab2.addInhalt("Kennung",sConst);
          Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS(i<0 ? "Show":"Checkbox",sBez));
          Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from begriff b join abfrage a on b.aic_begriff=a.aic_begriff where "+
                                                 (i<0 ? sConst+" is not null":g.bit(i>63?"a.abits2":"b.bits",l))));
        }

        private void addBitS(Tabellenspeicher Tab2,String sConst,String sBez,long l,long l2,int i,int iSub)
        {
          Tab2.addInhalt("Nr",i);
          Tab2.addInhalt("Sub",iSub);
          Tab2.addInhalt("Kennung",sConst);
          Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Radiobutton",sBez));
          Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from spalte where "+g.bits("bits"+(i>63 ? "3":i>31 ? "2":""),l)+"="+l2));
        }

        private void addBitS(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
        {
          Tab2.addInhalt("Nr",i);
          Tab2.addInhalt("Sub",0);
          Tab2.addInhalt("Kennung",sConst);
          Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS(i<0 ? "Show":"Checkbox",sBez));
          Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from spalte where "+(i<0 ? sConst+" is not null":g.bit("bits"+(i>63 ? "3":i>31 ? "2":""),l))));
        }

        private int getBew(Vector Vec)
        {
          int i=((Integer)Vec.lastElement()).intValue();
          return i<0 ? -i:0;
        }

        private void fillEig()
        {
          ((JCOutlinerFolderNode)GidEigenschaft.getRootNode()).removeChildren();
          fillGidEigenschaft(GidEigenschaft.getRootNode(),iStammtyp);
          GidEigenschaft.sortByColumn(0,Sort.sortMethod);
        }
        
    private void fillMemoBew()
    {
//    	g.fixtestError("fillMemoBew "+(-iStammtyp));
    	if (iStammtyp<0 && MemoBew != null)
    		MemoBew.setText(g.getMemo("Bewegungstyp",-iStammtyp));
    }

	private void fillCbo()
	{
		long lClock3=Static.get_ms();
//		g.testInfo("fillCbo-Anfang:"+iBitEig);
		if(bAndererStt)
		{
			fillEig();
			lClock3=g.clockInfo3("-fillCbo fillEig", lClock3);
			bAndererStt=false;
			fillMemoBew();
			lClock3=g.clockInfo3("-fillCbo fillMemoBew", lClock3);
		}
		
		//g.testInfo("nach fillGidEigenschaft");
                //g.progInfo("VecStt="+VecStt);
                int iBew2=VecStt==null ? 0:getBew(VecStt);
		String s="select aic_abfrage aic,aic_benutzergruppe,kennung,DefBezeichnung DefBez"+g.AU_Bezeichnung2("begriff")+" Bezeichnung"+
			",aic_modell,mod_aic_modell,bits,Abits2,autorefresh,anzahl,abfrage.aic_begriff,aic_code,prog,aic_stammtyp,aic_schrift,Tod from abfrage"+g.join("begriff","abfrage")+" where aic_abfrage="+A.iAbfrage+" or "+(g.Def() ? "":"Tod is null and ")+
			(iRolle>0 && iStammtyp>0 && VecStt==null ?"aic_rolle="+iRolle+" and aic_bewegungstyp is null":
                         iStammtyp>0 || VecStt!= null ?(iBew2>0?"(":"")+"aic_stammtyp"+(VecStt==null?"="+iStammtyp:Static.SQL_in(VecStt))+" and aic_bewegungstyp is null"+(iBew2>0?" or aic_bewegungstyp="+iBew2+")":""):
                         iStammtyp<0?"aic_bewegungstyp="+(-iStammtyp):"aic_stammtyp is null and aic_bewegungstyp is null")+
                        (A.isHS()? " and":" and not")+g.bit("bits",Abfrage.cstHS)+
                        //(A.isHS()?" and mod(bits/"+0x1000000000l+","+Abfrage.#*2+")&"+Abfrage.cstHS/0x1000000000l+">0":"")+
                        //(!A.bSpalten?" and mod(bits,"+Abfrage.cstFilter*2+")&"+Abfrage.cstFilter+">0":"")+
			//" and bits&"+g.cstFilter+(A.bSpalten?"=":">")+"0"
                        " and web"+(CbxFWeb==null || !CbxFWeb.isSelected() ? " is null":"=1")+
                        " and "+(bRechte?"":"not")+g.bit("bits",Abfrage.cstBerechtigung)+
                        ((iBitEig&PERS)>0 ? " and aic_benutzer="+g.getBenutzer():
			 g.Def()?"":" and (aic_benutzer is null and not"+g.bit("bits",Abfrage.cstNurModell)+((iBitEig&ALLE)==0 ? " and "+g.getAllBG():"")+" or aic_benutzer="+g.getBenutzer()+")");
                         //" and not"+SQL.bit("bits",Abfrage.cstNurModell)+" and (aic_benutzergruppe is null or "+g.getAllBG()+")");
		//g.testInfo("Spalten:"+A.bSpalten);
		g.testInfo(s);
		TabAbfragen=new Tabellenspeicher(g,s,true);
//		lClock3=g.clockInfo3("-fillCbo TabAbfragen", lClock3);
        //if (g.Prog()) 
		//  TabAbfragen.showGrid();
                if (A.isHS() && !bRechte)
                {
                  for (TabAbfragen.moveFirst();!TabAbfragen.eof();)
                  {
                    if(TabAbfragen.getI("aic")==A.iAbfrage)
                      TabAbfragen.moveNext();
                    else if((TabAbfragen.getL("bits")&Abfrage.cstHS)==0)
                    {
                      //g.progInfo("lösche nicht HS: "+TabAbfragen.getS("Bezeichnung"));
                      TabAbfragen.clearInhalt();
                    }
                    else if(((TabAbfragen.getL("bits")&Abfrage.cstFilter)>0) == A.bSpalten)
                    {
                      //g.progInfo("lösche"+(A.bSpalten?"":" kein")+" Filter: "+TabAbfragen.getS("Bezeichnung"));
                      TabAbfragen.clearInhalt();
                    }
                    else
                    {
                      //g.progInfo("aic=" + TabAbfragen.getI("aic"));
                      TabAbfragen.moveNext();
                    }
                  }
                }
		//g.testInfo("nach new Tabellenspeicher");
		if (A.iAbfrage<=0)
		{
			TabAbfragen.newLine();
			A.iAbfrage=A.iAbfrage==0?-999:A.iAbfrage;
			TabAbfragen.setInhalt("Aic",A.iAbfrage);
			TabAbfragen.setInhalt("Bezeichnung","-"+g.getBegriffS("Show","Neu"));
            TabAbfragen.setInhalt("Bits",new Long(A.iBits));
		}

		//TabAbfragen.showGrid("Abfragen");
		//if (g.Prog())
		//	TabAbfragen.showGrid("TabAbfragen");
		bCboLaden=false;
		CboAbfrage.fillCbo(TabAbfragen,true);
		CboAbfrage.setSelectedAIC(A.iAbfrage);
		if (!TabAbfragen.posInhalt("Aic",A.iAbfrage))
			g.progInfo("Abfrage "+A.iAbfrage+" ist nicht vorhanden!");
		bCboLaden=true;
		g.progInfo("fillCbo:"+A.iAbfrage+"/"+CboAbfrage.getSelectedAIC()+"/"+TabAbfragen.getI("Aic"));
		lClock3=g.clockInfo3("-fillCbo CboAbfrage", lClock3);		
	}

        private void checkSperre()
        {
          //g.progInfo("checkSperre: bEdit="+bEdit+", bSave="+bSave+", A.iBegriff="+A.iBegriff);
          if (bEdit && bSave && !VecSperre.contains(new Integer(A.iBegriff)))
          {
            g.exec("update begriff set log_aic_logging=null where aic_begriff="+A.iBegriff);
            //if (VecSperre.contains(new Integer(A.iBegriff)))
            //  VecSperre.removeElement(new Integer(A.iBegriff));
            g.testInfo("******************** Abfrage "+g.TabBegriffe.getBezeichnungS(A.iBegriff)+" entsperrt");
          }
        }

	private void Events()
	{
          Gid.setAllowMultipleSelections(true);
		Gid.addItemListener(new JCItemListener()
		{
			public void itemStateChanged( JCItemEvent ev )
			{
				if(ev.getStateChange() == ItemEvent.SELECTED)
                                {
                                  /*g.progInfo("A.TabSp="+A.TabSp);
                                  g.progInfo("Gid.getSelectedNode()="+Gid.getSelectedNode());
                                  g.progInfo("Gid.getSelectedNode().getUserData()="+Gid.getSelectedNode().getUserData());*/
                                  EnableButtons();
                                  if (A.TabSp != null)
                                  {
                                    //g.TabEigenschaften.push();
                                    if(A.TabSp.posInhalt("Nummer", Gid.getSelectedNode().getUserData()))
                                    {
                                      int iPos=g.TabEigenschaften.getPos("Aic", ((Vector)A.TabSp.getInhalt("Vec")).lastElement());
                                      if (iPos>=0)
                                        g.setTooltip((g.Def() ? "<b>"+Static.cin(g.getShow("Kennung"),":") + "</b> "+g.TabEigenschaften.getS(iPos,"Kennung")+
                                            "<br><b>"+Static.cin(g.getShow("Datentyp"),":") + "</b> "+g.TabEigenschaften.getS(iPos,"Datentyp") + "<br><hr>" : "")+
                                            "<p width=\"250px\">" + g.TabEigenschaften.getS(iPos,"Info")+"</p>",Gid);
                                            //g.breakString(g.TabEigenschaften.getS(iPos,"Info"),60),Gid);
                                        //Gid.setToolTipText((g.Def() ? g.TabEigenschaften.getS(iPos,"Kennung") + "\n" + g.TabEigenschaften.getS(iPos,"Datentyp") + "\n" : "") + g.TabEigenschaften.getS(iPos,"Info"));
                                    }
                                    //g.TabEigenschaften.pop();
                                  }
                                }
			}
		});
                Gid.getOutliner().addMouseListener(new MouseListener()
                {
                  public void mousePressed(MouseEvent ev)
                  {}

                  public void mouseClicked(MouseEvent ev)
                  {
                    //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
                    if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                      popSpalte.show((Component)ev.getSource(), ev.getX(), ev.getY());
                    else if (ev.getModifiers() == MouseEvent.BUTTON1_MASK && ev.getClickCount() == 2)
                    {
                      /*int iLevel = (Gid.getSelectedNode() == null ? -1 : Gid.getSelectedNode().getLevel());
                      if (iLevel == 2 && isSpalten(Gid.getSelectedNode().getParent()))
                        SpalteBearbeiten();*/
                      if (BtnEdit.isEnabled())
                	Edit();
                    }
                  }

                  public void mouseEntered(MouseEvent ev)
                  {}

                  public void mouseExited(MouseEvent ev)
                  {}

                  public void mouseReleased(MouseEvent ev)
                  {}
                });
                Gid.getOutliner().addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{}
			public void keyReleased(KeyEvent e)
			{
			  if (e.getKeyCode()==Global.iPopkey)
			    popSpalte.show((Component)e.getSource(),0,0);
			  else if (e.getKeyCode()==KeyEvent.VK_DELETE)
			    if (BtnLoeschen.isEnabled())
		        	ZeileLoeschen(true,true);
			}
			public void keyTyped(KeyEvent e)
			{}
		});
                
                ActionListener ALH=new ActionListener()
    	        {
    	          public void actionPerformed(ActionEvent ev)
    	          {
    	            String s = ev.getActionCommand();
    	            if (s.equals("Init"))
    	            	TypAuswahl();
    	            else if (s.equals("NeueAbfrage"))
    	            	NeueAbfrage(true,false);
    	            else if (s.equals("Kopie"))
    	            {
    	            	 checkSperre();
                         KopiereAbfrage();
    	            }
    	            else if (s.equals("SetEdit"))
    	            	Edit(true);
    	            else if (s.equals("EditAbfrage"))
    	            	NeueAbfrage(false,false);
    	            else if (s.equals("DelAbfrage"))
    	            	DeleteAbfrage(false);
    	            else if (s.equals("RefreshDB"))
    	            	g.sendWebDB("refreshDB",null,thisJFrame());
    	            else if (s.equals("TSearch"))
                    	Tsearch.get(g,A.sDefBez);
    	            else
    	                Static.printError("DefAbfrage.ALH: "+s+" wird nicht unterstützt!");
    	          }
    	        };
    	        g.BtnAdd(BtnRefreshDB,"RefreshDB",ALH);
    	        g.BtnAdd(BtnInit,"Init",ALH);
    	        g.BtnAdd(BtnNeuAbfrage,"NeueAbfrage",ALH);
    	        g.BtnAdd(BtnKopie,"Kopie",ALH);
    	        g.BtnAdd(BtnSetEdit,"SetEdit",ALH);
    	        g.BtnAdd(BtnEditAbfrage,"EditAbfrage",ALH);
    	        g.BtnAdd(BtnDelAbfrage,"DelAbfrage",ALH);
    	        g.BtnAdd(getFormularButton("translate-search"),"TSearch",ALH);


		BtnErsetzen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				if (isSpalten(Gid.getSelectedNode().getParent()))
                                {
                                  if(A.TabSp != null && A.TabSp.posInhalt("Nummer",Gid.getSelectedNode().getUserData()))
                                  {
                                    boolean bDelOk=true;
                                    if(A.TabSp.getI("aic_spalte")!=0)
                                    {
                                      //SQL Qry=new SQL(g);
                                      /*Tabellenspeicher Tab=new Tabellenspeicher(g,"select defbezeichnung,begriff.kennung,begriff.aic_begriff"+
                                          ",aic_befehl+1-(select min(aic_befehl) from befehl where aic_modell=modell.aic_modell) Zeile from spalte"+g.join2("befehl","spalte")+
                                          ",modell"+g.join("begriff","modell")+" where befehl.aic_modell=modell.aic_modell and spalte.aic_spalte="+A.TabSp.getI("aic_spalte"),true);*/

                                      //String s=Qry.list("defbezeichnung","spalte"+g.join2("befehl","spalte")+",modell"+g.join("begriff","modell")+" where befehl.aic_modell=modell.aic_modell and spalte.aic_spalte="+A.TabSp.getI("aic_spalte"));
                                      //Qry.free();
                                      Tabellenspeicher Tab=getModelle_der_Spalte(g,A.TabSp.getI("aic_spalte"),null);
                                      bDelOk=Tab.isEmpty();
                                      String sSpalte=g.getBegriffS("Show","Spalte")+" "+(A.TabSp.isNull("Bezeichnung") ? Abfrage.getEigenschaftBezeichnung(g, (Vector)A.TabSp.getInhalt("Vec")) : A.TabSp.getS("Bezeichnung"));
                                      if (!bDelOk)
                                        new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,null,g).showDialog("BereitsVerwendet",new String[] {sSpalte},Tab);
                                    }
                                    if(bDelOk || g.Def())
                                    {
                                      int iAic=Sort.geti(GidEigenschaft.getSelectedNode().getUserData(),0);
                                      int iPos=g.TabEigenschaften.getPos("Aic",iAic);
                                      String sDT= iPos>=0 ? g.TabEigenschaften.getS(iPos,"Datentyp"):"";
                                      if (!sDT.startsWith("Calc"))
                                        A.TabSp.setInhalt("Calc",null);
                                      else
                                        g.defInfo("Calc nicht gelöscht bei Datentyp "+sDT);
                                      A.TabSp.setInhalt("Gruppe",null);
                                      NeueSpalte(false);
                                    }
                                  }
                                }
                                else
				{
					iArt = Und;
					NeueBedingung(false);
				}
				bSpeichern=false;
				EnableButtons();
			}
		});

		BtnUnd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				if (isSpalten(Gid.getSelectedNode()) || Gid.getSelectedNode().getLevel()==2 && isSpalten(Gid.getSelectedNode().getParent()))
					NeueSpalte(true);
				else
				{
					iArt = Und;
					NeueBedingung(true);
				}
				bSpeichern=false;
				EnableButtons();
				//TabSpalten.showGrid("Spalten");
			}
		});

		BtnOder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				iArt = Oder;
				NeueBedingung(true);
				bSpeichern=false;
				EnableButtons();
			}
		});

		BtnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				bSpeichern=false;
				int iLevel= (Gid.getSelectedNode()==null?-1:Gid.getSelectedNode().getLevel());
				if (iLevel==2 && isSpalten(Gid.getSelectedNode().getParent()))
					SpalteBearbeiten();
				else
				{
					iArt = Edit;
					NeueBedingung(true);
				}
				EnableButtons();
			}
		});

		BtnLoeschen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				ZeileLoeschen(false,true);
			}
		});

		BtnTest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				checkReihenfolge();
				checkDoppelte();
				showTestparameter();
				//TabSpalten.showGrid("Spalten");
			}
		});

                Static.addActionListener(getButton("Zeitraum"),new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev) {
                    Zeitraum.get(g).show();
                  }
                });


		//g.testInfo("Events: BtnSpeichern");
		BtnSpeichern.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				if(CboAbfrage.getSelectedAIC()==-999)
					NeueAbfrage(false,true);
				else
					Save();
			}
		});
		
		if (BtnView != null)
		  BtnView.addActionListener(new ActionListener()
		  {
			  public void actionPerformed(ActionEvent ev)
			  {
				  if ((TabAbfragen.getL("bits")&Abfrage.cstView)>0)
						saveView(true);
			  }
		  });

		BtnRuecksetzen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Ruecksetzen();
//                if(BtnSpeichern.isEnabled() && Changed() && new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Speichern")==Message.YES_OPTION)
//                  Save();
//				checkSperre();
//                bEdit=false;
//                Load(true,true);
//				EnableButtons();
			}
		});


		BtnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				/*NodUebergabe.setChildren(Gid.getRootNode().getChildren());
				NodUebergabe.setUserData(Gid.getRootNode().getUserData());*/
                           if (g.Def() || new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("uebernehmen") == Message.YES_OPTION)
                                {
                                  if (bEdit && bSave && !VecSperre.contains(new Integer(A.iBegriff)))
                                    VecSperre.addElement(new Integer(A.iBegriff));
                                  g.testInfo("VecSperre="+VecSperre);
                                  checkReihenfolge();
                                  Aold.copy(A);
                                  Aold.SQL_String();
                                }
				g.setFenster(thisFrame,g.getBegriffAicS("Frame","Abfrage"),iFF);
                                //g.progInfo("Rolle3="+Aold.iRolle);
				//bOk=true;
				//g.progInfo("DefAbfrage.BtnOk.addActionListener: Spalten="+Aold.Spalten());
                                //checkSperre();
				hide();
			}
		});


		/*BtnAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				g.setFenster(thisFrame,g.getBegriffAic("Frame","Abfrage"));
				//bOk=false;
                                checkSperre();
				hide();
			}
		});*/
                Action cancelKeyAction = new AbstractAction()
                {
                  /**
					 *
					 */
					private static final long serialVersionUID = 5714011165709293353L;

				public void actionPerformed(ActionEvent e) {
                    g.setFenster(thisFrame,g.getBegriffAicS("Frame","Abfrage"),iFF);
                    //bOk=false;
                    checkSperre();
                    hide();
                  }
                };
                Static.Escape(BtnAbbruch,thisFrame,cancelKeyAction);
                BtnAbbruch.addActionListener(cancelKeyAction);

		BtnUp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				moveEigenschaft(true);
			}
		});

		BtnDown.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				moveEigenschaft(false);
			}
		});

		CboAbfrage.Cbo.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange() == ItemEvent.SELECTED && bCboLaden)
				{
					g.progInfo("CboAbfrage.addItemListener:"+A.iBegriff);
                                        checkSperre();
					A.iAbfrage = CboAbfrage.getSelectedAIC();
					//iTop = iAbfrage>0 ? TabAbfragen.getI("autorefresh"):iTop;
					if (A.iAbfrage==0 || TabAbfragen.posInhalt("Aic",A.iAbfrage))
					{
						Load(true,false);
                                                if (!Static.bAutoEdit)
                                                  bEdit=BtnSetEdit==null;
						EnableButtons();
					}
					else
						g.progInfo("CboAbfrage.addItemListener: Abfrage "+A.iAbfrage+" nicht gefunden!");
					g.progInfo("CboAbfrage.itemStateChanged:"+A.iAbfrage+"/"+CboAbfrage.getSelectedAIC()+"/"+TabAbfragen.getI("Aic"));
				}
			}
		});

		GidEigenschaft.addItemListener(new JCOutlinerListener()
		{
			public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
			{
                          ev.setNewState(ev.getState());
				//Eig_open(ev);
                                //Eig_add();
			}
			public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
			{
				GidEigenschaft.sortByColumn(0,Sort.sortMethod);
			}

			public void outlinerNodeSelectBegin(JCOutlinerEvent ev) {}
			public void outlinerNodeSelectEnd(JCOutlinerEvent ev) {
                          //g.fixInfo("outlinerNodeSelectEnd:"+GidEigenschaft.getSelectedNode());
                          String sEigenschaft = Sort.gets(GidEigenschaft.getSelectedNode().getLabel());
                          JCOutlinerFolderNode NodParent = GidEigenschaft.getSelectedNode().getParent();
                          while (NodParent != GidEigenschaft.getRootNode())
                          {
                            sEigenschaft = Sort.gets(NodParent.getLabel()) + '.' + sEigenschaft;
                            NodParent = NodParent.getParent();
                          }
                          MemoEig.setText(sEigenschaft);
                        }
			public void itemStateChanged(JCItemEvent ev)
			{
                          //g.fixInfo("itemStateChanged:"+GidEigenschaft.getSelectedNode());
				if(ev.getStateChange() == ItemEvent.SELECTED)
				{
                                  int iAic=GidEigenschaft.getSelectedNode().getLevel()==0 ? 0:Tabellenspeicher.geti(((Vector)(GidEigenschaft.getSelectedNode()).getUserData()).elementAt(0));
                                  //g.TabEigenschaften.push();
                                  int iPos=g.TabEigenschaften.getPos("Aic",iAic);
                                  if (iPos>=0)
                                    g.setTooltip((g.Def()?"<b>"+Static.cin(g.getShow("Kennung"),":") + "</b> "+g.TabEigenschaften.getS(iPos,"Kennung")+
                                                  "<br><b>"+Static.cin(g.getShow("Datentyp"),":") + "</b> "+g.TabEigenschaften.getS(iPos,"Datentyp")+"<br><hr>":"")+
                                                 "<p width=\"250px\">" + g.TabEigenschaften.getS(iPos,"Info")+"</p>"/*g.breakString(g.TabEigenschaften.getS(iPos,"Info"),60)*/,GidEigenschaft);
                                    //GidEigenschaft.setToolTipText((g.Def()?g.TabEigenschaften.getS(iPos,"Kennung")+"|"+g.TabEigenschaften.getS(iPos,"Datentyp")+": ":"")+g.TabEigenschaften.getS(iPos,"Info"));
                                  //g.TabEigenschaften.pop();
                                  //g.progInfo("itemStateChanged:"+iAic);
					EnableButtons();
				}
			}
		});
	}
	
	private void Ruecksetzen()
	{
		if(BtnSpeichern.isEnabled() && Changed() && new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Speichern")==Message.YES_OPTION)
            Save();
		checkSperre();
        bEdit=false;
        Load(true,true);
		EnableButtons();
	}

	private void Edit()
	{
//		g.fixtestError("Edit");
	  bSpeichern=false;
	  int iLevel= (Gid.getSelectedNode()==null?-1:Gid.getSelectedNode().getLevel());
	  if (iLevel==2 && isSpalten(Gid.getSelectedNode().getParent()))
      		SpalteBearbeiten();
	  else
	  {
      		iArt = Edit;
      		NeueBedingung(true);
	  }
	  EnableButtons();
	}

	public static Tabellenspeicher getModelle_der_Spalte(Global g,int iSpalte,Vector Vec)
	{
		if (iSpalte==0)
			return null;
		String sSp;
		if (iSpalte>0)
			sSp="="+iSpalte;
		else
			sSp=" in (select aic_spalte from spalte where aic_abfrage="+(-iSpalte)+")";
		Tabellenspeicher Tab= new Tabellenspeicher(g,"select defbezeichnung,begriff.kennung"+(/*Vec==null ? "":*/",(select kennung from code where aic_code=befehl2.aic_code) Befehl")+
			  ",null _save,null alle,null Gruppe,var"+
              ",Eingabe,aic_befehl+1-(select min(aic_befehl) from befehl2 where aic_modell=modell.aic_modell) Zeile,modell.aic_modell,aic_befehl,hide"+
			  ",null refr,null mArt,mbits,(case when befehl2.hide>0 then "+Global.ColTod.getRGB()+" else 0 end) FARBE from spalte"+
			  g.join2("befehl2","spalte")+" join modell on befehl2.aic_modell=modell.aic_modell"+g.join("begriff","modell")+" where spalte.aic_spalte"+sSp+
              (Vec==null ? "":" and"+g.in("befehl2.aic_modell",Vec)),true);	
		for (Tab.moveFirst();!Tab.out();Tab.moveNext())
		{
			int iB=Tab.getI("mbits");
			Tab.setInhalt("_save", Static.JaNein2((iB & Calc.SAVE)>0)); // "_save", da "save" bei MS nicht erlaubt
			Tab.setInhalt("alle",  Static.JaNein2((iB & Calc.ALLE)>0));
			Tab.setInhalt("Gruppe",  Static.JaNein2((iB & Calc.GRUPPE)>0));
//			if ((iB & Calc.VAR)>0)
//				Tab.setInhalt("var", (iB & Calc.VART)==Calc.VLOK ? "lokal":(iB & Calc.VART)==Calc.VICH ? "ich":(iB & Calc.VART)==Calc.V_R ? "lesen":"alle");
//			else
//				Tab.setInhalt("var",Static.sLeer);
			Tab.setInhalt("refr", (iB & Calc.REFRESH)==Calc.IMMER ? "immer":(iB & Calc.REFRESH)==Calc.NIE ? "nie":"");
			Tab.setInhalt("mArt", (iB & Calc.M_ART)==Calc.ADD ? "add":(iB & Calc.M_ART)==Calc.SUB ? "sub":(iB & Calc.M_ART)==Calc.MINIT ? "init":"");
		}
		return Tab;
	}

        private void Eig_add()
        {
//        	g.fixtestError("Eig_add");
          if (A.bSpalten)
          {
            if (!bEdit)
              Edit(true);
            if (!(isSpalten(Gid.getSelectedNode()) || Gid.getSelectedNode().getLevel()==2 && isSpalten(Gid.getSelectedNode().getParent())))
              Gid.selectNode(A.NodSpalten, null);
            NeueSpalte(true);
          }
        }

        private void Eig_open(JCOutlinerEvent ev)
        {
          if (GidEigenschaft.getSelectedNode()==null || !(GidEigenschaft.getSelectedNode() instanceof JCOutlinerFolderNode))
            return;
          JCOutlinerFolderNode folder = (JCOutlinerFolderNode)GidEigenschaft.getSelectedNode();// ev.getNode();
          //JCOutliner outliner = (JCOutliner) ev.getSource();
          if (folder == GidEigenschaft.getRootNode())
                  return;
          else if (ev==null || ev.getNewState() == BWTEnum.FOLDER_OPEN_ALL)
          {
                  if (folder.getChildren() == null)
                  {
                          int i=Tabellenspeicher.geti(((Vector)folder.getUserData()).elementAt(1));
                          g.progInfo("Eig_open: i="+i);
                          fillGidEigenschaft(folder,i);
                          //if (ev==null)
                          //  folder.setState(BWTEnum.FOLDER_OPEN_ALL);
                          //GidEigenschaft.folderChanged(folder);
                  }
          }
          else if (ev.getNewState() == BWTEnum.FOLDER_CLOSED)
          {
          }
        }

        private boolean Def_Edit()
        {
          if (g.Def())
          {
            boolean b=SQL.getInteger(g,"select log_aic_logging from begriff where aic_begriff=" + A.iBegriff)==g.getLog();
            if (b)
              g.diskInfo("Änderung trotz Sperre: "+A.sDefBez+"("+A.iBegriff+") von Log="+g.getLog());
            return b;
          }
          else
            return false;
        }

        private void Edit(boolean bLoad)
        {
//        	g.fixtestError("Edit2");
//        	g.setSprache(CboSprache.getSelectedAIC(), 0);
          if (A.iBegriff <= 0 || Def_Edit() || g.exec("update begriff set log_aic_logging=" + g.getLog() +
                                        " where log_aic_logging is null and aic_begriff=" + A.iBegriff) == 1)
          {
            bSave = true;
//            Abfrage.iSprache=CboSprache.getSelectedAIC();
            if (A.iBegriff > 0)
              g.testInfo("******************** Abfrage " + g.TabBegriffe.getBezeichnungS(A.iBegriff) + " gesperrt");
            if (bLoad)
              Load(true,true);
          }
          else if (!VecSperre.contains(new Integer(A.iBegriff)))
          {
            String sUser = SQL.getString(g,"select benutzer.kennung from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer"+
                                         " join begriff on logging.aic_logging=begriff.log_aic_logging where aic_begriff=" + A.iBegriff);
            new Message(Message.WARNING_MESSAGE, null, g).showDialog("Abfrage_gesperrt", new String[] {sUser});
            //new Exception().printStackTrace();
            bSave = false;
          }
          bEdit = true;
          EnableButtons();
        }

	private void KopiereAbfrage()
	{
		g.progInfo("KopiereAbfrage");
		String sBez=g.getBegriffS("Show","Kopie")+" "+CboAbfrage.Cbo.getSelectedItem();
		iNeu--;
		A.iAbfrage = iNeu;
                bEdit=true;
                bSave=true;
                TabAbfragen.copyLine();
                if (TabAbfragen.getI("aic_modell")>0 || TabAbfragen.getI("mod_aic_modell")>0)
                {
                  new Message(Message.WARNING_MESSAGE,null,g).showDialog("Modell_hinterlegt");
                  A.iModell = 0;
                  A.iModell2 = 0;
                  TabAbfragen.setInhalt("aic_modell",0);
                  TabAbfragen.setInhalt("mod_aic_modell",0);
                }
                if (!g.Spezial())
                  A.iBits=A.iBits|Abfrage.cstPersoenlich;
                if (!g.Def())
                {
                  if ((A.iBits&Abfrage.cstNurModell)>0)
                    A.iBits-=Abfrage.cstNurModell;
                  if (A.NodVBed !=null && A.NodVBed.getChildren()!=null && A.NodVBed.getChildren().size()>0)
                  {
                    new Message(Message.WARNING_MESSAGE,null,g).showDialog("Vorbedingung_hinterlegt");
                    A.NodVBed.removeChildren();
                  }
                }
//                if ((A.iBits&A.cstAuswertung)>0) // laut Skype Entwicklung am 22.9. 9:43 entfernt
//                	A.iBits-=A.cstAuswertung;
                //g.testInfo("Nur Modell1="+(A.iBits&A.cstNurModell)+" / "+(TabAbfragen.getL("Bits")&A.cstNurModell));
                TabAbfragen.setInhalt("Bits",A.iBits);
                TabAbfragen.setInhalt("Aic",iNeu);
		bCboLaden=false;
		CboAbfrage.addItem(sBez,iNeu,"",0);
		CboAbfrage.setSelectedAIC(iNeu);
		bCboLaden=true;
    if (Memo != null)
		  Memo.setText("");
		TabAbfragen.setInhalt(g.Def() ? "DefBez":"Bezeichnung",sBez);
		TabAbfragen.setInhalt(g.Def() ? "Bezeichnung":"DefBez",null);
		TabAbfragen.setInhalt("Kennung","");
		TabAbfragen.setInhalt("Aic_Benutzergruppe",g.getHBG());
		if (A.TabSp != null)
		  for (A.TabSp.moveFirst();!A.TabSp.eof();)
			if (A.TabSp.getS("Status").equals("Del"))
                          A.TabSp.clearInhalt();
                        else
                        {
                          A.TabSp.setInhalt("Status", "Neu");
                          A.TabSp.setInhalt("aic_spalte",null);
                          A.TabSp.moveNext();
                        }
                EnableButtons();
		//if (g.Prog())
		//	A.TabSp.showGrid("A.TabSp");
		//g.testInfo("Abfrage="+A.iAbfrage+" / Spalten="+A.Spalten());
	}

	@SuppressWarnings("unchecked")
	private void fillGidEigenschaft(JCOutlinerNode rNod,int riStammtyp)
	{
		// long lClock3=Static.get_ms();
//		g.fixtestError("fillGidEigenschaft "+riStammtyp);
          int iPosE=rNod.getLevel()>0 ? g.TabEigenschaften.getPos("Aic",((Vector)rNod.getUserData()).elementAt(0)):-1;
          if (iPosE>=0 && g.TabEigenschaften.getS(iPosE,"Datentyp").equals("Hierarchie"))
          {
            //JCOutlinerFolderNode Nod=new JCOutlinerFolderNode((Object)new Vector((Vector)rNod.getLabel()),(JCOutlinerFolderNode)rNod);
            Vector VecVisible = new Vector((Vector)rNod.getLabel());
            if (!VecVisible.elementAt(0).toString().startsWith(" "))
              VecVisible.setElementAt(" "+VecVisible.elementAt(0),0);
            JCOutlinerFolderNode Nod=new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)rNod);
            Nod.setStyle(rNod.getStyle());
            Nod.setState(BWTEnum.FOLDER_CLOSED);
            Nod.setUserData(rNod.getUserData());
          }
		Tabellenspeicher TabEigenschaften;
                int iRolle2=rNod.getLevel()>0 && iPosE>=0 ? g.TabEigenschaften.getI(iPosE,"Rolle"):(riStammtyp<0 || A.isHS() && riStammtyp!=g.iSttANR ? 0:iRolle);
                //g.fixInfo("fillGidEigenschaft:"+riStammtyp+"/"+iRolle2);
                if (riStammtyp>0)// || iRolle2>0)
		{
			// Standard
                        String sTab=iRolle2>0 ? "Rolle":"Stammtyp";
                        int iAic=iRolle2>0 ? iRolle2:riStammtyp;
			TabEigenschaften = new Tabellenspeicher(g,"select e.aic_eigenschaft aic,e.aic_stammtyp,e.kennung,begriff.kennung datentyp"+g.AU_BezeichnungFo("Eigenschaft","e")+"Bezeichnung from begriff"+g.join("eigenschaft","e","begriff","begriff")+g.join(sTab+"_zuordnung","z","e","eigenschaft")+" where z.aic_"+sTab+"="+iAic+(g.Def()?"":" and not"+g.bit("e.bits",Global.cstNurModell)),true);
                        //TabEigenschaften.showGrid("TabEigenschaften");
//			lClock3=g.clockInfo3("--fillGidEigenschaft TabEigenschaften-Stamm",lClock3);
			if (g.Spezial())
			{
                          Tabellenspeicher Tab=null;

                                if ((iBitEig & BEW)>0 && rNod.getLevel()==0) // Bewegungstypen aus Stammtyp
                                {
                                  Tab = new Tabellenspeicher(g,
                                      "select e.aic_eigenschaft aic,-z.aic_bewegungstyp aic_bewegungstyp,e.kennung,begriff.kennung datentyp,bewegungstyp.kennung bewegungstyp" +
                                      g.AU_BezeichnungFo("Bewegungstyp") + "BezBew" + g.AU_BezeichnungFo("Eigenschaft", "e") + "Bezeichnung from begriff" +
                                      g.join("eigenschaft", "e", "begriff", "begriff") + g.join("bew_zuordnung", "z", "e", "eigenschaft") +
                                      g.join("bewegungstyp", "z") + " where e.aic_stammtyp=" + riStammtyp, true);
                                  //Tab.showGrid("Tab");
                                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                                    if (g.existsBew(-Tab.getI("aic_bewegungstyp")))
                                    {
                                      TabEigenschaften.addInhalt("Aic", Tab.getInhalt("Aic"));
                                      TabEigenschaften.addInhalt("aic_stammtyp", Tab.getI("aic_bewegungstyp"));
                                      TabEigenschaften.addInhalt("kennung", "*" + Tab.getInhalt("kennung") + '.' + Tab.getS("bewegungstyp"));
                                      TabEigenschaften.addInhalt("datentyp", Tab.getInhalt("datentyp"));
                                      TabEigenschaften.addInhalt("Bezeichnung",
                                          "*" + Static.beiLeer(Tab.getS("Bezeichnung"), Tab.getS("kennung")) + '-' +
                                          Static.beiLeer(Tab.getS("BezBew"), Tab.getS("bewegungstyp")));
                                    }
//                                  lClock3=g.clockInfo3("--fillGidEigenschaft Bewegungstypen aus Stammtyp",lClock3);
                                }

                                if ((iBitEig & MF)>0) // Sub-Gruppen
                                {
                                  Tab = new Tabellenspeicher(g,
                                      "select -e.aic_eigenschaft aic,z.aic_stammtyp,e.kennung,begriff.kennung datentyp,stammtyp.kennung stammtyp" +
                                      g.AU_BezeichnungFo("Eigenschaft", "e") + "Bezeichnung from begriff" + g.join("eigenschaft", "e", "begriff", "begriff") +
                                      g.join("stammtyp_zuordnung", "z", "e", "eigenschaft") + g.join("stammtyp", "z") + " where e.aic_stammtyp=" +
                                      riStammtyp, true);
                                  while(!Tab.eof()) {
                                    TabEigenschaften.addInhalt("Aic", Tab.getInhalt("Aic"));
                                    TabEigenschaften.addInhalt("aic_stammtyp", Tab.getInhalt("aic_stammtyp"));
                                    TabEigenschaften.addInhalt("kennung", "#" + Tab.getInhalt("kennung") + '.' + Tab.getS("Stammtyp"));
                                    TabEigenschaften.addInhalt("datentyp", Tab.getInhalt("datentyp"));
                                    TabEigenschaften.addInhalt("Bezeichnung",
                                        "#" + Static.beiLeer(Tab.getS("Bezeichnung"), Tab.getS("kennung")) + '-' + Tab.getS("Stammtyp"));
                                    Tab.moveNext();
                                  }
//                                  lClock3=g.clockInfo3("--fillGidEigenschaft Sub-Gruppen",lClock3);
                                }

                                if (g.Def())
                                {
                                  Tab = new Tabellenspeicher(g,"select e.aic_eigenschaft aic,e.aic_stammtyp,e.kennung,begriff.kennung datentyp" +
                                                                              //g.AU_Bezeichnung("Eigenschaft", "e") + " from begriff join eigenschaft e where datentyp in ('Timestamp','SysAic','Aic','Benutzer','Anlage','Mandant','entfernt','LoeschBenutzer','CalcField','CalcWaehrung','CalcMass','CalcDauer')", true);
                                                                              g.AU_BezeichnungFo("Eigenschaft", "e") + "Bezeichnung from begriff"+g.join("eigenschaft","e","begriff","begriff")+" where e.aic_stammtyp="+riStammtyp+" and"+g.bit("e.bits",Global.cstSysAic), true);

                                  for(Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                                  {
                                    TabEigenschaften.addInhalt("Aic", Tab.getInhalt("Aic"));
                                    TabEigenschaften.addInhalt("aic_stammtyp", 0);
                                    TabEigenschaften.addInhalt("kennung", '&' + Tab.getS("kennung"));
                                    TabEigenschaften.addInhalt("datentyp", Tab.getInhalt("datentyp"));
                                    TabEigenschaften.addInhalt("Bezeichnung", '&' + Static.beiLeer(Tab.getS("Bezeichnung"), Tab.getS("kennung")));
                                  }
//                                  lClock3=g.clockInfo3("--fillGidEigenschaft Sys",lClock3);
                                }

			}

		}
		else if(riStammtyp<0) // Eigenschaften des Bewegungstyps
                {
                  TabEigenschaften = new Tabellenspeicher(g,
                      "select e.aic_eigenschaft aic,e.aic_stammtyp,e.kennung,begriff.kennung datentyp" + g.AU_BezeichnungFo("Eigenschaft", "e") + "Bezeichnung from begriff" +
                      g.join("eigenschaft", "e", "begriff", "begriff") + g.join("bew_zuordnung", "z", "e", "eigenschaft") + " where z.aic_bewegungstyp=" + ( -riStammtyp), true);
                  if ((iBitEig & BEW)>0 && rNod.getLevel()==0) // Bewegungstypen rückwärts aus Bewegung
                  {
                    Tabellenspeicher Tab = new Tabellenspeicher(g,
                      "select e.aic_eigenschaft aic,-z.aic_bewegungstyp aic_bewegungstyp,e.kennung,begriff.kennung datentyp,bewegungstyp.kennung bewegungstyp" +
                      g.AU_BezeichnungFo("Bewegungstyp") + "BezBew" + g.AU_BezeichnungFo("Eigenschaft", "e") + "Bezeichnung from begriff" +
                      g.join("eigenschaft", "e", "begriff", "begriff") + g.join("bew_zuordnung", "z", "e", "eigenschaft") +
                      g.join("bewegungstyp", "z") + " where e.aic_bewegungstyp=" + (-riStammtyp), true);
                    //Tab.showGrid("Tab");
                    for (Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                      if (g.existsBew( -Tab.getI("aic_bewegungstyp")))
                      {
                        TabEigenschaften.addInhalt("Aic", Tab.getInhalt("Aic"));
                        TabEigenschaften.addInhalt("aic_stammtyp", Tab.getI("aic_bewegungstyp"));
                        TabEigenschaften.addInhalt("kennung", "*" + Tab.getInhalt("kennung") + '.' + Tab.getS("bewegungstyp"));
                        TabEigenschaften.addInhalt("datentyp", Tab.getInhalt("datentyp"));
                        TabEigenschaften.addInhalt("Bezeichnung",
                            "*" + Static.beiLeer(Tab.getS("Bezeichnung"), Tab.getS("kennung")) + '-' +
                            Static.beiLeer(Tab.getS("BezBew"), Tab.getS("bewegungstyp")));
                      }
                  }
//                  lClock3=g.clockInfo3("--fillGidEigenschaft Eigenschaften des Bewegungstyps",lClock3);
                  //g.progInfo("");
                }
		else  // alle Eigenschaften
			TabEigenschaften = new Tabellenspeicher(g,"select e.aic_eigenschaft aic,e.aic_stammtyp,e.kennung,begriff.kennung datentyp"+g.AU_BezeichnungFo("Eigenschaft","e")+"Bezeichnung from begriff"+g.join("eigenschaft","e","begriff","begriff"),true);

                //Hierarchie
                for (TabEigenschaften.moveFirst();!TabEigenschaften.eof();TabEigenschaften.moveNext())
                {
                        if (TabEigenschaften.getS("Datentyp").endsWith("Hierarchie"))
                        {
                                int iVon = TabEigenschaften.getI("aic_stammtyp");
                                while(iVon > 0 && riStammtyp != iVon)
                                {
                                        int iPosS=g.TabStammtypen.getPos("Aic",new Integer(iVon));
                                        iVon = iPosS<0 ? -1:g.TabStammtypen.getI(iPosS,"Darunter");
                                        if (iPosS>=0 && (g.TabStammtypen.getI(iPosS,"bits")&Global.cstEnde)==0 || riStammtyp<=0)
                                        {
                                                TabEigenschaften.addInhalt("Aic",TabEigenschaften.getInhalt("Aic"));
                                                //TabEigenschaften.addInhalt("aic_stamm",TabEigenschaften.getInhalt("aic_stamm"));
                                                TabEigenschaften.addInhalt("aic_stammtyp",iPosS<0 ? 0:g.TabStammtypen.getInhalt("Aic",iPosS));
                                                TabEigenschaften.addInhalt("kennung",TabEigenschaften.getS("kennung")+(iPosS<0?"":'.'+g.TabStammtypen.getS(iPosS,"Kennung")));
                                                TabEigenschaften.addInhalt("datentyp","Hierarchie2");
                                                String s=Static.beiLeer(TabEigenschaften.getS("Bezeichnung"),TabEigenschaften.getS("kennung"))+(iPosS<0?"":'-'+g.TabStammtypen.getS(iPosS,"Bezeichnung"));
                                                TabEigenschaften.addInhalt("Bezeichnung",s);
                                        }
                                }
                        }
                }
                //Hierarchie-Ende

              //g.fixInfo("iBitEig="+iBitEig);
              if (/*g.Spezial() &&*/ (iBitEig & (SYS+CALC))>0)
              {
                Tabellenspeicher Tab = new Tabellenspeicher(g,"select e.aic_eigenschaft aic,e.aic_stammtyp,e.kennung,begriff.kennung datentyp" +
                                                            //g.AU_Bezeichnung("Eigenschaft", "e") + " from begriff join eigenschaft e where datentyp in ('Timestamp','SysAic','Aic','Benutzer','Anlage','Mandant','entfernt','LoeschBenutzer','CalcField','CalcWaehrung','CalcMass','CalcDauer')", true);
                                                            g.AU_Bezeichnung("Eigenschaft", "e") + "Bezeichnung from begriff"+g.join("eigenschaft","e","begriff","begriff")+
                                                            " where begriff.kennung in ('CalcField','CalcWaehrung','CalcMass','CalcDauer','CalcString','CalcDatum','CalcBoolean','CalcBool3') or"+g.bit("e.bits",Global.cstDefault), true);
                //Tab.showGrid("Defaults");
                for(Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                {
                  char c = Tab.getS("datentyp").startsWith("Calc") ? '$' : '+';
                  if (c=='$' && (iBitEig&CALC)>0 || c=='+' && (iBitEig&SYS)>0)
                  {
                    TabEigenschaften.addInhalt("Aic", Tab.getInhalt("Aic"));
                    TabEigenschaften.addInhalt("aic_stammtyp", Tab.getI("aic_stammtyp"));
                    TabEigenschaften.addInhalt("kennung", c + Tab.getS("kennung"));
                    TabEigenschaften.addInhalt("datentyp", Tab.getInhalt("datentyp"));
                    TabEigenschaften.addInhalt("Bezeichnung", c + Static.beiLeer(Tab.getS("Bezeichnung"), Tab.getS("kennung")));
                  }
                }
//                lClock3=g.clockInfo3("--fillGidEigenschaft SYS+CALC",lClock3);
              }

                TabEigenschaften.checkBezeichnung();
		//CboEigenschaft.fillCbo(TabEigenschaften,false);

		//TabEigenschaften.showGrid();
//                lClock3=g.clockInfo3("--fillGidEigenschaft holen",lClock3);
		TabEigenschaften.moveFirst();
		while (!TabEigenschaften.eof())
		{
			String s=TabEigenschaften.getS("datentyp");
      int iObj=TabEigenschaften.getI("Aic");
      boolean bMF=TabEigenschaften.getI("Aic")<0;
      int iPosE2=g.TabEigenschaften.getPos("aic",Math.abs(iObj));
			if (bMF || !g.EigIsTod(iObj) && g.existsEigenschaft(iObj) && !(/*s.equals("Stichtag") ||*/ s.equals("Trennzeichen")) && (rNod.getLevel()==0 || !s.equals("BewBew")))
			{
				Vector<Integer> Vec=new Vector<Integer>();
				Vec.addElement(iObj);
				int iStt= s.equals("Firma") ? g.iSttFirma:TabEigenschaften.getI("aic_stammtyp");
				if (s.equals("BewBew") && iStt == 0)
                {
                  
                  Vec.addElement(-g.TabEigenschaften.getI(iPosE2,"Bew"));
                }
                else
                  Vec.addElement(iStt);
                Vector<Object> VecVisible = new Vector<Object>();
                VecVisible.addElement(TabEigenschaften.getS("Bezeichnung"));
                VecVisible.addElement(iObj);
                VecVisible.addElement(s);
                VecVisible.addElement(Static.JaNein2(g.isJederEig(iObj)));
                VecVisible.addElement(Static.JaNein2((g.TabEigenschaften.getI(iPosE2,"bits")&Global.cstEigLizenz)>0));
                VecVisible.addElement(TabEigenschaften.getI("aic_stammtyp")>0 ? g.TabStammtypen.getBezeichnungS(TabEigenschaften.getI("aic_stammtyp")):"");
                VecVisible.addElement(TabEigenschaften.getS("Kennung"));
                if (!s.equals("BewBew") && (iStt==0 || s.equals("Mehrfach") && TabEigenschaften.getI("Aic")>0 || !s.equals("Mehrfach")&& bMF))
				{
					JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)rNod);
					Nod.setUserData(Vec);
                    int iPos= g.getPosBegriff("Datentyp",s);
                    String sBildFile=Static.beiLeer(iPos<0 ? null:g.TabBegriffe.getS(iPos,"BildFile"),"bu_bi_fragezeichen.gif");
                    Image Gif = this==This2 ? null:iPos<0 ? null:g.LoadImage(sBildFile);
					{
						JCOutlinerNodeStyle StyFolder = this==This2 ? g.getStyle(null):new JCOutlinerNodeStyle();
                        if (Gif != null)
                          StyFolder.setItemIcon(Gif);
                        Nod.setStyle(StyFolder);
                        if (g.Def() && rNod == GidEigenschaft.getRootNode() && ForBefore(riStammtyp,TabEigenschaften.getI("Aic")))
                          Nod.getStyle().setForeground(Global.ColVorbedingung);
					}
				}
				else
				{
					JCOutlinerFolderNode Nod=new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)rNod);
					Nod.setUserData(Vec);
                    if (s.equals("BewBew") && iStt == 0)
                    {
                      int iPos=g.TabEigenschaften.getPos("aic",TabEigenschaften.getInhalt("Aic"));
                      iStt=-g.TabEigenschaften.getI(iPos,"Bew");
                    }
                    //TODO Bilder schneller laden
					Image Gif = this==This2 ? null:iStt>0 ? g.getSttGif(iStt):g.getBewGif(-iStt); // temporär entfernt
                    if (Gif==null)
                      Gif=g.getImage("open.png");
					//if (Gif !=null)
                    {
                      Nod.setStyle(Gif == null ? new JCOutlinerNodeStyle():g.getStyle(Gif));
                      if (g.Def() && rNod == GidEigenschaft.getRootNode() && ForBefore(riStammtyp,TabEigenschaften.getI("Aic")))
                        Nod.getStyle().setForeground(Global.ColVorbedingung);
                    }					
					Nod.setState(BWTEnum.FOLDER_CLOSED);
				}
			}
			TabEigenschaften.moveNext();
		}
		GidEigenschaft.folderChanged(rNod);
//		lClock3=g.clockInfo3("--fillGidEigenschaft füllen",lClock3);
	}

        private boolean ForBefore(int iStt,int iEig)
        {
          //int iRolleS=CboRolle==null?0:CboRolle.getSelectedAIC();
          int iPos=g.TabEigenschaften.getPos("Aic",iEig);
          //g.fixtestInfo("ForBefore: Eig="+iEig+"-> Pos="+iPos+" bei Stt="+iStt);
          if (iPos<0)
            return false;
          String sDt=g.TabEigenschaften.getS(iPos,"Datentyp");
          //g.fixtestInfo("-> Eig="+iEig+", Dt="+sDt+" ("+g.TabEigenschaften.getS(iPos,"Bezeichnung")+")");
          int iRolleI=g.TabEigenschaften.getI(iPos,"Rolle");
          if (iStt>0)
            return sDt.equals("Bezeichnung") || sDt.equals("Rolle") || sDt.equals("Mandant") || sDt.equals("Firma") || sDt.equals("Gruppe") && g.isANR_Stt(iStt,iEig)
                || iRolle==iRolleI && (sDt.equals("Eintritt") || sDt.equals("Austritt") || sDt.equals("EinAustritt"));
          else if (iStt<0)
            return sDt.equals("BewDatum") || sDt.equals("SysAic") || sDt.equals("Mandant") || sDt.equals("Protokoll") || g.isANR(-iStt,iEig)
                || sDt.equals("BewDatum2") && g.isLDATE(-iStt,iEig) || sDt.startsWith("BewBool") && g.isBOOL(-iStt,iEig) || sDt.equals("BewVon_Bis") && g.isVB(-iStt,iEig);
          else
            return false;
        }

	private void EnableButtons()
	{
          //g.progInfo("EnableButtons");
		int iLevel= (Gid.getSelectedNode()==null?-1:Gid.getSelectedNode().getLevel());

		// Abfrage
		BtnNeuAbfrage.setEnabled(!bEdit);
                BtnKopie.setEnabled(true);//!bEdit);
        CboSprache.setEnabled(!bEdit);
		BtnInit.setEnabled(!bEdit);
		boolean bDef= A.iAbfrage>0 && TabAbfragen !=null && TabAbfragen.posInhalt("Aic",CboAbfrage.getSelectedAIC()) && (g.Def()
                    || g.Spezial() && (A.iBits&Abfrage.cstNurModell)==0 && (/*(A.iBits&Abfrage.cstAuswertung)>0 ||*/ TabAbfragen.getI("Aic_Benutzergruppe")>0)
                    || (A.iBits&Abfrage.cstPersoenlich)>0 || (A.iBits&Abfrage.cstBerechtigung)>0/*&&  g.checkBG(TabAbfragen.getI("Aic_Benutzergruppe"))*/);
                //g.progInfo("EnableButtons: iModell="+(TabAbfragen==null?"null":TabAbfragen.getS("aic_modell")));
		//g.testInfo("EnableButtons: bDef / A.iAbfrage="+g.Def()+"/"+A.iAbfrage);
                //if (TabAbfragen!=null && !TabAbfragen.out())
                //  g.testInfo(TabAbfragen.getI("Aic")+":"+TabAbfragen.getI("Aic_Benutzergruppe")+"->"+g.checkBG(TabAbfragen.getI("Aic_Benutzergruppe")));
                setTitle(A.iAbfrage<0 || bDef ? sTitel:sTitel2);
		BtnEditAbfrage.setEnabled(A.iAbfrage != 0);
		BtnDelAbfrage.setEnabled(bDef && (g.Def() || !bEdit));
                if (BtnSetEdit != null)
                  BtnSetEdit.setEnabled(!bEdit);
        if (BtnView != null) BtnView.setEnabled(TabAbfragen !=null && (TabAbfragen.getL("bits")&Abfrage.cstView)>0);

		BtnTest.setEnabled(A.iAbfrage != 0  && (A.bSpalten || bRechte)/*bSpalten/*iStammtyp != 0 || iBewegungstyp != 0 && iAbfrage !=0 && TabAbfragen.getI("aic_stammtyp")>0*/);
		//if (BtnTest.isEnabled())
		//	CheckCboStamm(iStammtyp != 0 ? iStammtyp:TabAbfragen.getI("aic_stammtyp"));
        g.fixtestInfo("bSave="+bSave+",bEdit="+bEdit+",bDef="+bDef+"/"+g.Def()+",iAbfrage="+A.iAbfrage+",bSpeichern="+bSpeichern+", bSpalten="+A.bSpalten);
		BtnSpeichern.setEnabled(bSave && bEdit && (A.iAbfrage<0 || bDef) && (g.Def() || bSpeichern || !A.bSpalten));
		BtnRuecksetzen.setEnabled(bEdit);

		// Eigenschaft
                boolean bSpalte = iLevel==2 && isSpalten(Gid.getSelectedNode().getParent());
                boolean bNurFilter=g.NurFilter();

		BtnEdit.setEnabled(iLevel>1);
        MnuEdit.setVisible(BtnEdit.isEnabled());
		BtnErsetzen.setEnabled(bEdit && iLevel>1 && (!bSpalte || !bNurFilter));
		BtnLoeschen.setEnabled(bEdit && iLevel>1);
    if (Memo != null)
		  Memo.setEditable(bEdit);
                int iPos=-1;
                if(bEdit && bSpalte && Gid.getSelectedNode()!=null)
                {
                  iPos=A.TabSp.getPos("Nummer", Gid.getSelectedNode().getUserData());
                  int iPosE=iPos<0 ? -1:g.TabEigenschaften.getPos("Aic", ((Vector)A.TabSp.getInhalt("Vec",iPos)).lastElement());
                  String sDT=iPosE<0 ? "":g.TabEigenschaften.getS(iPosE,"Datentyp");
                  MnuCopy.setVisible(sDT.equals("BewZahl2") || sDT.equals("BewMass2") || sDT.equals("BewWaehrung2"));
                }
                else
                  MnuCopy.setVisible(false);
                MnuDel.setVisible(BtnLoeschen.isEnabled());
                MnuDel2.setVisible(BtnLoeschen.isEnabled());
                MnuHide.setEnabled(iPos>=0);// && A.TabSp.getI(iPos,"sortiert")==0);
		BtnUp.setEnabled(bEdit && bSpalte && Static.allow_up_down(Gid.getSelectedNode(),true));
		BtnDown.setEnabled(bEdit && bSpalte && Static.allow_up_down(Gid.getSelectedNode(),false));

		BtnUnd.setEnabled(bEdit && A.iAbfrage != 0 && (iLevel>0 && !(Gid.getSelectedNode().getChildren()!=null && Gid.getSelectedNode().getChildren().size()>0) ||
				(iLevel==1 && isSpalten(Gid.getSelectedNode()) || bSpalte) && !bNurFilter));
                if (bNurFilter && iLevel==1 && isSpalten(Gid.getSelectedNode()))
                    BtnUnd.setEnabled(false);
                //g.testInfo("bNurFilter="+bNurFilter+",Und="+BtnUnd.isEnabled());
		BtnOder.setEnabled(bEdit && iLevel>1 && !bSpalte);

		BtnOk.setEnabled(bOkErlaubt && TabAbfragen!=null);// && TabAbfragen.getI("aic_modell")==0);
		BtnAbbruch.setEnabled(true);
		//BtnRuecksetzen.setEnabled(CboAbfrage.getSelectedAIC()!=0);
		//BtnOk.setEnabled(Gid.getRootNode().getChildren()!=null && Gid.getRootNode().getChildren().size()>0);
	}

	private void TypAuswahl()
	{
          	if (DlgInit==null)
		{
			DlgInit = new JDialog((Frame)thisFrame,g.getBegriffS("Dialog","Typwahl"),true);
                        CboSTT = new ComboSort(g);
                        CboTyp = new ComboSort(g,ComboSort.kein);
                        CboRolle = new RolleEingabe(g,thisFrame);
                        //final AUCheckBox CbxMehrfach = g.getCheckbox("Mehrfach");
                        //final AUCheckBox CbxBewegung = g.getCheckbox("Bewegung");
                        //final AUCheckBox CbxSystem = g.getCheckbox("System");
                        //final AUCheckBox CbxBerechnung = g.getCheckbox("Berechnung");
                        final AUCheckBox CbxAlle = getCheckboxM("alle Abfragen",false);
                        final AUCheckBox CbxPers = getCheckboxM("nur eigene",false);
			CboTyp.setKein(true);
			CboTyp.addItem(g.getBezeichnung("Tabellenname","STAMMTYP"),1,"Stammtyp",0);
			CboTyp.addItem(g.getBezeichnung("Tabellenname","BEWEGUNGSTYP"),2,"Bewegungstyp",0);
			CboSTT.Clear();
			CboSTT.setEnabled(CboTyp.getSelectedAIC()!=0);
                        CboSTT.setPreferredSize(new java.awt.Dimension(200,20));
                        CboSTT.setMaximumRowCount(15);
			CboTyp.addItemListener(new ItemListener ()
			{
				public void itemStateChanged(ItemEvent ev)
				{
					if(ev.getStateChange() == ItemEvent.SELECTED)
					{
						if (CboTyp.getSelectedKennung().equals("Stammtyp"))
						{
                                                    //CboSTT.fillDefTable("Stammtyp", false);
                                                    if (bRechte)
                                                      CboSTT.fillCbo("SELECT aic_Stammtyp,kennung" + g.AU_Bezeichnung("Stammtyp") + " FROM Stammtyp where "+g.bit("SttBits",Global.cstBFilter), "Stammtyp", false, false);
                                                    else
                                                      CboSTT.fillCbo(g.TabStammtypen,false);
                                                    CboSTT.setEnabled(true);
						}
						else if(CboTyp.getSelectedKennung().equals("Bewegungstyp"))
						{
							CboSTT.fillDefTable("Erfassungstyp",false);
							CboSTT.setEnabled(true);
						}
						else
						{
							CboSTT.Clear();
							CboSTT.setEnabled(false);
						}
					}
				}
			});
                        if (!g.TabRollen.isEmpty())
                          CboSTT.addItemListener(new ItemListener()
                          {
                            public void itemStateChanged(ItemEvent ev)
                            {
                              if (ev.getStateChange() == ItemEvent.SELECTED)
                              {
                                if (CboTyp.getSelectedKennung().equals("Stammtyp"))
                                  CboRolle.fillRolle(CboSTT.getSelectedAIC(), true);
                                else
                                  CboRolle.Cbo.Clear(true);
                              }
                            }
                          });
			JButton BtnOk = g.getButton("Ok");
			JButton BtnAbbruch = g.getButton("Abbruch");
                        DlgInit.getRootPane().setDefaultButton(BtnOk);
			BtnOk.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					int iStt=VecStt != null ? iStammtyp:
                                             CboTyp.getSelectedKennung().equals("Stammtyp")?CboSTT.getSelectedAIC():
						CboTyp.getSelectedKennung().equals("Bewegungstyp")?-CboSTT.getSelectedAIC():0;

					//bOk = true;
					DlgInit.setVisible(false);
                                        setStt(iStt,CboRolle.getSelectedAIC(),(iBitEig&(MF+BEW+SYS+CALC))+(CbxAlle.isSelected()?ALLE:0)+(CbxPers.isSelected()?PERS:0));
					//setStt(iStt,CboRolle.getSelectedAIC(),(CbxMehrfach.isSelected()?MF:0)+(CbxBewegung.isSelected()?BEW:0)+
                                        //    (CbxSystem.isSelected()?SYS:0)+(CbxBerechnung.isSelected()?CALC:0)+(CbxAlle.isSelected()?ALLE:0)+(CbxPers.isSelected()?PERS:0));
                                     if (bAndererStt)
                                     {
                                       bOkErlaubt = false;
                                       A.clear(0);
                                       fillCbo();
                                       Load(true,true);
                                     }
                                     else if (bAndereBits)
                                       fillEig();
				}
			});
			BtnAbbruch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					DlgInit.setVisible(false);
					//bOk = false;
				}
			});
			JPanel Pnl = new JPanel(new BorderLayout());
			JPanel PnlWest = new JPanel(new GridLayout(0,1,2,2));
			JPanel PnlCenter = new JPanel(new GridLayout(0,1,2,2));
                        g.addLabel4(PnlWest,"Art");
			//PnlWest.add(new JLabel(g.getBegriff("Show","Art")+':'));
			PnlCenter.add(CboTyp);
                        g.addLabel4(PnlWest,"Typ");
                        //PnlWest.add(new JLabel(g.getBegriff("Show","Typ")+':'));
			PnlCenter.add(CboSTT);
                        if (!g.TabRollen.isEmpty())
                        {
                          g.addLabel4(PnlWest,"Rolle");
                          PnlCenter.add(CboRolle);
                        }
                        //PnlWest.add(CbxMehrfach);
                        //PnlWest.add(CbxSystem);
                        PnlWest.add(new JLabel());
                        JPanel PnlC2 = new JPanel(new GridLayout(1,2,2,2));
                        //PnlC2.add(CbxBewegung);
                        PnlC2.add(CbxAlle);
                        //PnlCenter.add(PnlC2);
                        //PnlC2 = new JPanel(new GridLayout(1,2,2,2));
                        //PnlC2.add(CbxBerechnung);
                        PnlC2.add(CbxPers);
                        PnlCenter.add(PnlC2);
			Pnl.add("West",PnlWest);
			Pnl.add("Center",PnlCenter);
			DlgInit.getContentPane().add("North",Pnl);
			JPanel PnlSouth = new JPanel(new GridLayout(1,0,2,2));
			PnlSouth.add(BtnOk);
			PnlSouth.add(BtnAbbruch);
			DlgInit.getContentPane().add("South",PnlSouth);
                        DlgInit.pack();
			//DlgInit.setSize(230,180);
			Static.centerComponent(DlgInit,thisFrame);
		}
                if ((bRechte || VecStt!=null || iStammtyp>0) && CboTyp.getSelectedAIC()!=1)
                  CboTyp.setSelectedAIC(1);
                CboTyp.setEnabled(!bRechte && VecStt==null); // && !bEdit);

                  //CboRolle.setEnabled(true);
                  if (VecStt != null)
                  {
                    CboSTT.setSelectedAIC(CboStt.getSelectedAIC());
                    CboSTT.setEnabled(false);
                  }
                  else if (iStammtyp > 0)
                  {
                    CboSTT.setSelectedAIC(iStammtyp);
                    CboSTT.setEnabled(true);
                    if (iRolle > 0)
                      CboRolle.setSelectedAIC(iRolle);
                  }
                  else if (iStammtyp < 0)
                  {
                    CboTyp.setSelectedKennung("Bewegungstyp");
                    CboSTT.setSelectedAIC( -iStammtyp);
                    //CboSTT.setEnabled(true);
                  }
                /*if (bEdit)
                {
                  CboSTT.setEnabled(false);
                  CboRolle.setEnabled(false);
                }*/
		DlgInit.setVisible(true);
	}

	// ----------------------------------------------------------------------
	// -------------   * * * * *   L O A D   * * * * *   --------------------
	// ----------------------------------------------------------------------

	private void Load(boolean bLoad,boolean bZurueck)
	{
		long lClock3=Static.get_ms();
//		g.fixtestError("Load");
		//g.testInfo("DefAbfrage.Load1 "+A.iAbfrage+", iStammtyp="+iStammtyp+", iStt="+A.iStt);
        if(bLoad || A.NodSpalten.getChildren()==null && A.bSpalten || Static.bAutoEdit && !VecSperre.contains(new Integer(A.iBegriff)))
        {
          boolean b=iStammtyp>0 && iStammtyp==A.iStt;
          A = new ShowAbfrage(g, false,A.iAbfrage, A.bBedingung && A.bSpalten ? Abfrage.cstDefAbfrage : A.bBedingung ? Abfrage.cstHS_Filter : Abfrage.cstHS_Spalte,true);
          sSave="";
          if (b && A.iStt==0) A.iStt=iStammtyp;
        }
        lClock3=g.clockInfo3("-DefAbfrage.Load ShowAbfrage",lClock3);
        if (Static.bAutoEdit && !bZurueck)
          Edit(false);
//        lClock3=g.clockInfo3("-DefAbfrage.Load Edit",lClock3);
		if (A.iAbfrage<1)
		{
			A.iStt=iStammtyp>0?iStammtyp:0;
			A.iBew=iStammtyp<0?-iStammtyp:0;
		}
        else
          iRolle=A.iRolle;
        g.testInfo("DefAbfrage.Load2 "+A.iAbfrage+", iStammtyp="+iStammtyp+", iStt="+A.iStt);
		//g.progInfo("Stt="+A.iStt+", Bew="+A.iBew);
		if (A.bBedingung)
        {
          checkBedingung(A.NodVBed);
          checkBedingung(A.NodBed);
        }
		JCOutlinerFolderNode NodRoot= (JCOutlinerFolderNode)Gid.getRootNode();
		NodRoot.removeChildren();
		if (A.bBedingung)
		{
			A.NodBed.setUserData("*");
                        A.NodVBed.setUserData("V");
                        if (g.Def())
                          NodRoot.addNode(A.NodVBed); // 10.8.: vorübergehend Ausgeblendet, da es nur bei Sybase funktioniert
			NodRoot.addNode(A.NodBed);
		}
		if (A.bSpalten)
		{
			NodRoot.addNode(A.NodSpalten);
			//TabSpalten = new Tabellenspeicher(g,A.TabSp);
			/*if (g.Prog())
			{
				TabSpalten.showGrid("neu");
				A.TabSp.showGrid("alt");
			}*/
		}
		Gid.folderChanged(NodRoot);
		//if (TabAbfragen.posInhalt("Aic",iAbfrage))
		//	setSpalten(TabAbfragen.getI("Spalten"));
		//g.progInfo("vor setSpalten: "+A.Spalten()+" Spalten");
//		lClock3=g.clockInfo3("-DefAbfrage.Load div",lClock3);
		setSpalten();
//		lClock3=g.clockInfo3("-DefAbfrage.Load setSpalte",lClock3);
                if (A.bBedingung)
                {
                  A.NodVBed.setLabel(g.getBegriffS("Show", "Vorbedingung"));
                  A.NodBed.setLabel(g.getBegriffS("Show", "Bedingung"));
                }
		//g.progInfo("nach setSpalten: "+A.Spalten()+" Spalten");
		//g.progInfo("Spalten="+A.Spalten());
                if (g.Def())
                {
                  //Vector Vec = (Vector)g.getMemoVector("Begriff", A.iBegriff /*SQL.getInteger(g,"Select aic_begriff from abfrage where aic_abfrage="+A.iAbfrage)*/);
                  if (Memo != null)
                    Memo.setText(g.getMemo2(A.iBegriff));//"" + Vec.elementAt(1));
                  g.progInfo("Load:" + A.iAbfrage + "/" + CboAbfrage.getSelectedAIC() + "/" + TabAbfragen.getI("Aic"));
                }
                lClock3=g.clockInfo3("-DefAbfrage.Load Rest",lClock3);
	}

	private void checkBedingung(JCOutlinerFolderNode Nod)
	{
		//g.progInfo("checkBedingung");

		Vector VecChildren = Nod.getChildren();
		if (VecChildren != null)
			for(int i=0;i<VecChildren.size();i++)
			{
				JCOutlinerFolderNode NodNeu=(JCOutlinerFolderNode)VecChildren.elementAt(i);
				Vector Vec=(Vector)NodNeu.getUserData();
				//g.progInfo(NodNeu.getLabel()+":"+Static.VecToString(Vec));
				String sVergleichsart=(String)Vec.elementAt(2);
				String sVergleich;
//				String s2=(String)Vec.elementAt(3);
				if (sVergleichsart.equals("is null") || sVergleichsart.equals("is not null"))
                                {
                                  sVergleich = "";
                                  Object Obj=Vec.elementAt(3);
                                  //if (Obj!=null && Obj instanceof Integer)
                                  //  sVergleich="("+g.TabRollen.getBezeichnungS(Sort.geti(Obj))+")";
                                }
				else
				{
					String sDatentyp=(String)Vec.elementAt(0);
					String s2=(String)Vec.elementAt(3);
					int iS=s2==null ? -1: s2.indexOf(" ");
//					if (iS>0)
//						g.fixtestError("checkBedingung:"+iS+"/"+s2.substring(iS+1, s2.length()-1)+"->"+g.TabStammtypen.getBezeichnung(s2.substring(iS+1, s2.length()-1)));
					if (sDatentyp.equals("Anlage"))
						sVergleich = SQL.getString(g,"select Kennung from Code where aic_Code="+s2);
					else if (sDatentyp.equals("Benutzer") || sDatentyp.equals("LoeschBenutzer") || sDatentyp.equals("User") || sDatentyp.equals("BewUser"))
						sVergleich = SQL.getString(g,A.checkJoker("select Kennung from Benutzer where aic_Benutzer="+s2));
					else if (sDatentyp.equals("Aufgabe") || sDatentyp.equals("Formular") || sDatentyp.equals("Abfrage") || sDatentyp.equals("Modell"))
						sVergleich = SQL.getString(g,A.checkJoker("select Kennung from begriff where aic_Begriff="+s2));
					else if (sDatentyp.equals("Status"))
						sVergleich = SQL.getString(g,A.checkJoker("select Kennung from Status where aic_Status="+s2));
					else if (sDatentyp.equals("Land"))
						sVergleich = SQL.getString(g,A.checkJoker("select Kennung from Land where aic_Land="+s2));
					else if (sDatentyp.equals("Modell"))
						sVergleich = SQL.getString(g,A.checkJoker("select Kennung from xx where aic_xx="+s2));
                    else if (sDatentyp.equals("Mandant"))
						sVergleich = s2.equals("*aktMandant*")?g.getShow("akt_Mandant"):SQL.getString(g,"select Kennung from mandant where aic_mandant="+s2);
					else if (sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular"))
						sVergleich = SQL.getString(g,"select Kennung from Code where aic_Code="+s2);
					else if (sDatentyp.equals("Gruppe") || sDatentyp.equals("Hierarchie") || sDatentyp.equals("Firma") || sDatentyp.equals("SysAic") || sDatentyp.equals("Protokoll") || sDatentyp.equals("BewStamm") || sDatentyp.equals("BewHierarchie"))
						sVergleich = s2.startsWith("*Joker") || s2.startsWith("*UseVec") || s2.startsWith("*Reserve1") || s2.startsWith("*meine") || s2.startsWith("*Qry*") || s2.startsWith("*Checkbox*") || s2.startsWith("*Radiobutton*") ? 
								g.getBegriffS("Platzhalter", iS<0 ? s2:s2.substring(0, iS)+"*")+(iS>0 ? " "+g.getShow("aus")+" "+g.TabStammtypen.getBezeichnungS(s2.substring(iS+1, s2.length()-1)):"")://s2.substring(1,s2.lastIndexOf('*')):
                                                    s2.equals("*meinStamm*") ? "mein Stamm":s2.startsWith("*Qry von") ? "von Qry":s2.startsWith("*Joker von") ? s2.substring(1,s2.lastIndexOf(',')):isVar(s2) ? s2:
                                                    SQL.getString(g,"select bezeichnung from stammview where aic_stamm="+s2);
					else if (sDatentyp.endsWith("Boolean"))
						sVergleich = Static.JaNein(s2.startsWith("1"));
                    else if (sDatentyp.endsWith("Bool3"))
                    	sVergleich = SQL.getString(g,"select Kennung from Auswahl where aic_Auswahl="+s2);
					else if (s2.equals("*Reserve2*") || s2.equals("now()"))
						sVergleich = g.getBegriffS("Platzhalter",s2);
					else 
						sVergleich = s2;
				}
                int iBBits=Vec.size()>4 ? Tabellenspeicher.geti(Vec.elementAt(4)):0;
				String sB=Abfrage.getEigenschaftBezeichnung(g,(Vector)Vec.elementAt(1))+((iBBits&Abfrage.VON)>0?".von":(iBBits&Abfrage.BIS)>0?".bis":(iBBits&Abfrage.KEN)>0?".Ken":"")+((iBBits&Abfrage.OJ)>0?".ohne_Jahr ":(iBBits&Abfrage.ZEIT)>0 ? ".Zeit ":" ")+
                                    g.getBegriffS("Vergleich",sVergleichsart)+" "+sVergleich;
//				g.fixtestError("checkBedingung:"+s2+"->"+g.getBegriffS("Platzhalter", s2)+"->\n"+sB);
				NodNeu.setLabel(sB);
				checkBedingung(NodNeu);
			}
	}
	
	private boolean isVar(String s)
	{
		return s!=null &&  (s.startsWith("@") || s.startsWith("$"));
	}


	// ----------------------------------------------------------------------
	// -------------   * * * * *   S A V E   * * * * *   --------------------
	// ----------------------------------------------------------------------

	private void checkReihenfolge()
	{
		if(bReihenfolge && A.bSpalten && !A.TabSp.isEmpty())
		{
			Vector VecChildren = /*findNode(Gid.getRootNode(),false)*/A.NodSpalten.getChildren();
			//g.progInfo("VecChildren="+VecChildren);
			for(int i=0;i<VecChildren.size();i++)
			{
				//g.progInfo("i:"+((JCOutlinerNode)VecChildren.elementAt(i)).getUserData());
				if(A.TabSp.posInhalt("Nummer",((JCOutlinerNode)VecChildren.elementAt(i)).getUserData()))
					A.TabSp.setInhalt("Reihenfolge",i+1);
				else
					Static.printError("DefAbfrage.checkReihenfolge: Nummer nicht gefunden:"+((JCOutlinerNode)VecChildren.elementAt(i)).getUserData());
			}
			A.TabSp.sort("Reihenfolge",true);
			//if (g.Prog())
			//	A.TabSp.showGrid("Spalten");
		}
	}
	
	private boolean checkDoppelte()
	{
		if (A.TabSp==null)
			return false;
		Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Name","Nr","Anzahl"});
		for(int i=0;i<A.TabSp.size();i++)
		{
		  Vector VecEig = (Vector)A.TabSp.getInhalt("Vec",i);
		  int iEPos=g.TabEigenschaften.getPos("Aic",Sort.geti(VecEig.elementAt(VecEig.size()-1)));
		  String sDatentyp=iEPos<0 ? "Filler":g.TabEigenschaften.getS(iEPos,"Datentyp");
		  if ((A.TabSp.getI(i,"bits2")&Abfrage.cstSpNotSave)==0 && (A.TabSp.getI(i,"bits")&Abfrage.cstAnzeigen)>0 && !sDatentyp.equals("Filler") || sDatentyp.startsWith("Calc"))
		  {
		    //g.fixtestError(A.TabSp.getS(i,"Bezeichnung")+"/"+A.TabSp.getS(i,"Bez2")+"/"+A.TabSp.getS(i,"Kennung")+"/"+A.TabSp.getI(i,"aic_spalte")+":"+A.TabSp.getInhalt("Vec",i));
		    Vector<Object> Vec=(Vector)A.TabSp.getInhalt("Vec",i);
		    if (Vec.size()==1)
		    {
		    	int iAic=Sort.geti(Vec, 0);
		    	if (Tab.posInhalt("Nr", iAic))
		    		Tab.addI("Anzahl", 1);
		    	else
		    	{
		    		Tab.addInhalt("Name", g.TabEigenschaften.getBezeichnungS(iAic));
		    		Tab.addInhalt("Nr",iAic);
		    		Tab.addInhalt("Anzahl",1);
		    	}
		    }		    
		  }
		}
		Tab.remove("Anzahl",1);
//		for (Tab.moveFirst();!Tab.out();Tab.moveNext())
//			if (Tab.getI("Anzahl")==1)
//				Tab.d
		//Tab.showGrid();
		if (!Tab.isEmpty())
			new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB,thisJFrame(),g,30).showDialog("Spalten_doppelt",Tab);
    else if ((A.iBits&Abfrage.cstSumme)>0)
      for(int i=0;i<A.TabSp.size();i++)
        if (A.TabSp.getI(i,"Cod_Aic_Code")==0)
        {
            Tab.addInhalt("Name", A.TabSp.getS(i,"Node"));
		    		Tab.addInhalt("Nr",A.TabSp.getI(i,"Reihenfolge"));
		    		Tab.addInhalt("Anzahl",1);
        }
    if (!Tab.isEmpty())
			new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB,thisJFrame(),g,30).showDialog("ohne_Ergebnisart",Tab);
		return !Tab.isEmpty();
	}

	/*private String dropView(String sExists,String sTab)
	{
		boolean bExist=SQL.exists(g,sExists+sTab+"'");
		String sCreateAlter=bExist && g.ASA() ? "alter" : "create";
		if(bExist && !g.ASA())
			g.exec("drop view "+sTab);
		return sCreateAlter;
	}*/


	private void saveView(boolean bExtern)
	{
    if ((A.iBits&Abfrage.cstKeinStamm)==0)
      new Message(Message.WARNING_MESSAGE,null,g).showDialog("View_keinStamm");
//		boolean bEdit2=bEdit;
		// Ruecksetzen();
          //if (g.ASA() && !bExtern)
          //  return;
		A.setViewSQL();
    String s = A.sSQL;
          // g.fixtestInfo("saveView:"+s);

		String sMandant=(bExtern ? SQL.getString(g,"select kennung from mandant where aic_mandant="+g.getMandant()).toUpperCase():"AUV")+"_";
		/*if(!SQL.exists(g,"select * from "+(g.ASA()?"sysuserlist where name":"dba_users where username")+"='"+sMandant+"'"))
			g.exec("grant connect to "+sMandant+" IDENTIFIED BY x"+g.getPassword(SQL.getString(g,"select passwort from mandant where aic_mandant="+g.getMandant())));*/
		String sTab=sMandant+Static.beiLeer(TabAbfragen.getS("Bezeichnung"), TabAbfragen.getS("DefBez")).toUpperCase();
		Tabellenspeicher TabSpalten=A.getSpalten();
		if (TabSpalten!=null && !TabSpalten.isEmpty())
		{
      String sExists="select * from "+(g.ASA()?"systable where table_name":g.Oracle()?"user_views where view_name":
          g.MS()?"sysobjects where xtype='V' and name":g.MySQL()?"information_schema.views where table_schema=DATABASE() and table_name":"")+"='";
			boolean bExist=SQL.exists(g,sExists+sTab+(bExtern?"TEMP'":"'"));
                        if (bExtern)
                        {
                          s = s.replaceAll("Stammview ", "stammview5 ");
                          s = s.replaceAll("stammview ", "stammview5 ");
                          s = s.replaceAll("bewview ", "bewview2 ");
                          s = s.replaceAll("BewView ", "bewview2 ");
                          s = s.replaceAll("BewView3 ", "bew_pool ");
                          s = s.replaceAll("poolview ", "poolview3 ");
                          //if(g.ASA())
                          //	s = s.replaceAll("stringXview ", "stringXview3 ");
                          //g.progInfo(s);
                          /*String sHeute = g.today();//g.DateTimeToString(g.dtStichtag);
                          if (s.indexOf("stammview5")>0)
                          {
                        	  g.exec(dropView(sExists,"stammview5") + " view stammview5 as select stamm.aic_stamm,kennung,bezeichnung,aic_stammtyp,aic_mandant,ab,firma,eintritt,austritt,aic_rolle,aic_code from stamm"+g.join2("stamm_protokoll","stamm")+
                                  " where pro_aic_protokoll is null and (austritt is null or austritt >= "+sHeute+") and (eintritt is null or eintritt <= "+sHeute+") and ((ab is null or "+sHeute+">=ab) and (weg is null or "+sHeute+"<=weg))");
                          }
                          if (s.indexOf("poolview3")>0)
                          {
                        	  g.exec(dropView(sExists,"poolview3") + " view poolview3 as select aic_stamm,aic_eigenschaft,aic_bew_pool,sta_aic_stamm,spalte_double,gultig_von,aic_daten from stammpool where pro2_aic_protokoll is null and (gultig_von is null or gultig_von <= " +
                        			  sHeute + ") and (gueltig_bis is null or gueltig_bis > " + sHeute + ")");
                          }*/
                        }
                        String sTab2=sTab+(bExtern?"TEMP":"");
                        if (bExist && !g.ASA())
                        {
                          g.bISQL=true;
                          g.exec("drop view "+sTab);
                          if (bExtern)
                            g.exec("drop view "+sTab+"TEMP");
                          if (g.MySQL())
                          	for (int i=A.getEbenen();i>0;i--)
                          		g.exec("drop view "+sTab2+i);
                          g.bISQL=false;
                        }
                        boolean bViewOk=true;
                        if (g.ASA() || g.MySQL())
                        {
                        	int iPos=s.lastIndexOf("(SelecT");
                        	String s3=null;
                        	int i=0;
                        	//g.testInfo(s);
                                //g.testInfo("iPos="+iPos);
                        	while (iPos>0)
                        	{
                        		String s2=s3==null ? s.substring(iPos+1,s.indexOf(") E1")):s.substring(iPos+1);
                        		if (s3 != null)
                        			s2=s2.replace("SelecT E","SelecT "+sTab2)+sTab2+i;
                        		i++;
                        		//g.testInfo("s2."+i+"="+s2);
                          	int iPos2=s3==null ? 0:s3.indexOf(") E");
                        		s3=s3==null? s.substring(s.indexOf(") E1")+4): iPos2<0 ? s3:s3.substring(iPos2+4);
                        		//g.testInfo("s3."+i+"="+s3);
                        		s=s.substring(0, iPos);
                        		bViewOk=bViewOk && g.exec((bExist && g.ASA()?"alter":"create")+" view "+sTab2+i+" as "+s2)>-2;
                        		iPos=s.lastIndexOf("(SelecT");
                        	}
                        	s=s+sTab2+i+s3;
                        }

                        bViewOk=bViewOk && g.exec((bExist && g.ASA()?"alter":"create")+" view "+sTab2+" as "+s)>-2;
                        if (bViewOk && bExtern)
                        {
                          TabSpalten.moveFirst();
                          s=TabSpalten.getS("Kennung")+" "+TabSpalten.getS("Bezeichnung");
                          for(TabSpalten.moveNext();!TabSpalten.eof();TabSpalten.moveNext())
                          {
				String sTyp=TabSpalten.getS("Datentyp");
				String sKZ=TabSpalten.getS("Stil");
				if(sTyp.equals("von_bis") || sTyp.equals("Auto_von_bis"))
					s+=",v"+TabSpalten.getS("Kennung")+" "+TabSpalten.getS("Bezeichnung")+"_von"+
						",b"+TabSpalten.getS("Kennung")+" "+TabSpalten.getS("Bezeichnung")+"_bis";
				else if(sTyp.equals("BewVon_Bis"))
					if (Static.Leer(sKZ))
					  s+=",v"+TabSpalten.getS("Kennung")+" "+TabSpalten.getS("Bezeichnung")+"_von"+
						",b"+TabSpalten.getS("Kennung")+" "+TabSpalten.getS("Bezeichnung")+"_bis"+
						",d"+TabSpalten.getS("Kennung")+" "+TabSpalten.getS("Bezeichnung")+"_Dauer";
					else
						s+=","+/*(sKZ.equals("von") ? "v":sKZ.equals("bis") ? "b":"d")+*/TabSpalten.getS("Kennung")+" "+TabSpalten.getS("Bezeichnung");
				else
					s+=","+TabSpalten.getS("Kennung")+" "+TabSpalten.getS("Bezeichnung");
                          }
                          bExist=SQL.exists(g,sExists+sTab+"'");
                          bViewOk=g.exec((bExist && g.ASA()?"alter":"create")+" view "+sTab+" as select "+s+" from "+sTab+"TEMP")>-2;
                          //if(!bExist)
				                  //g.exec("grant SELECT ON "+sTab+" TO "+sMandant);
                        }
                        if (!bViewOk)
                        {
                          new Message(Message.WARNING_MESSAGE,null,g).showDialog("ViewFehlerhaft",new String[] {g.sLastError});
                          Edit(true); 
                        }
		}
	}

	private boolean Save_Abfrage()
	{
          //g.progInfo("Rolle2="+iRolle);
		boolean bNeu=A.iAbfrage<=0;
		long lBits=TabAbfragen.getL("bits");		
		String sKennung=TabAbfragen.getS("Kennung");
                if(!sKennung.equals("") && SQL.exists(g,"select aic_abfrage from abfrage"+g.join("Begriff","abfrage")+" WHERE begriff.aic_begriff<> "+(bNeu ? 0:TabAbfragen.getI("aic_begriff"))+" and Begriff.Kennung='"+sKennung+"'"))
                {
                  new Message(Message.WARNING_MESSAGE,null,g).showDialog("KennungVorhanden");
                  return false;
                }
                else if ((bNeu || EdtKennungA.Modified()) && (g.Def() || g.bAppl || g.bBasis))
                {
	                String sFehler=EdtKennungA.Fehler(g.bAppl || g.bBasis || (lBits&Abfrage.cstNurModell)>0);
	                if (sFehler != null)
	                {
	                	g.fixtestError(sFehler+" da Kennung="+EdtKennungA.getText()+"/"+sKennung);
	                	new Message(Message.WARNING_MESSAGE, thisJFrame(),g,10).showDialog("Kennung"+sFehler);
	                    return false;
	                }
                }

		/*if (bNeu && !sKennung.equals("") && SQL.exists(g,"select aic_abfrage from abfrage join begriff where kennung='"+sKennung+"'"))
		{
			new Message(Message.WARNING_MESSAGE,null,g).showDialog("KennungVorhanden");
			return false;
		}*/

		
                //if ((TabAbfragen.getL("bits")&Abfrage.cstView2)>0 && Static.bView2)
		//	saveView(false);

		SQL Qry = new SQL(g);
		Qry.add("Aic_Begriffgruppe",iBG_Abfrage);
		Qry.add("Kennung",sKennung);
        Qry.add("DefBezeichnung",TabAbfragen.getS("DefBez"));
		Qry.add0("Aic_Code",TabAbfragen.getI("aic_code"));
		Qry.add0("prog",TabAbfragen.getI("prog"));
                Qry.add0("Aic_Rolle",iRolle);
                if(VecStt!=null)
                {
                  int i=CboStt.getSelectedAIC();
                  Qry.add0("Aic_Stammtyp", i>0?i:0);
                  Qry.add0("Aic_Bewegungstyp",i<0?-i:0);
                }
                else
                {
                  if (bNeu || iStammtyp < 0)
                    Qry.add0("Aic_Stammtyp", iStammtyp > 0 ? iStammtyp : TabAbfragen.getI("aic_stammtyp"));
                  if (iStammtyp < 0)
                    Qry.add("Aic_Bewegungstyp", -iStammtyp);
                }
		if (!A.bSpalten || A.TabSp.isEmpty())
			lBits|=Abfrage.cstFilter;
		else
		{
			for (A.TabSp.moveFirst();!A.TabSp.eof();A.TabSp.moveNext())
			{
				if (!A.TabSp.isNull("Gruppe"))
					lBits|=Abfrage.cstGruppe;
				if (!A.TabSp.isNull("Calc"))
					lBits|=Abfrage.cstCalc;
			}
		}
                if (A.isHS())
                    lBits|=Abfrage.cstHS;
                if (bRechte)
                  lBits|=Abfrage.cstBerechtigung;
                A.iBits=lBits;
		//g.progInfo("Save_Abfrage: bits="+Long.toHexString(lBits));
		Qry.add("bits",lBits);
        Qry.add0("aic_schrift",TabAbfragen.getI("aic_schrift"));
                //if (!g.Def() || CbxNotChange==null || !CbxNotChange.isSelected())
        Qry.add("aic_logging",g.getLog());
        Qry.add("aic_mandant",g.getMandant());
        Qry.add0("Tod", A.bTod);
        Qry.add0("Web", A.bWeb);
        Qry.add0("Jeder", A.bJeder);
		A.iBegriff = bNeu ? Qry.insert("Begriff",true):TabAbfragen.getI("aic_begriff");
		if (bNeu)
			Qry.add("Aic_Begriff",A.iBegriff);
		else
			Qry.update("Begriff",A.iBegriff);
		g.setJeder(A.iBegriff, A.bJeder);
		g.testInfo("Speichere neue Abfrage mit "+A.Spalten()+" Spalten");
		Qry.add("Spalten",A.Spalten());
		Qry.add("Anzahl", A.iAnzahl);
			//iTop=CbxFirst.isSelected()?SboAutoRefresh.getIntValue():0;
		Qry.add0("Autorefresh",TabAbfragen.getI("Autorefresh"));
		A.iModell=TabAbfragen.getI("Aic_Modell");
		Qry.add0("Aic_Modell",A.iModell);
		A.iModell2=TabAbfragen.getI("Mod_Aic_Modell");
        Qry.add0("Mod_Aic_Modell",A.iModell2);
		Qry.add0("Aic_Benutzergruppe",TabAbfragen.getI("Aic_Benutzergruppe"));
		Qry.add0("Aic_Benutzer",Abfrage.is(TabAbfragen.getL("bits"),Abfrage.cstPersoenlich)?g.getBenutzer():0);
		Qry.add("ABits2",TabAbfragen.getL("Abits2"));
		Qry.add0("WebStamm", A.iWebStamm);
		if (bNeu)
		{
			A.iAbfrage = Qry.insert("Abfrage",true);
			iNeuAic=A.iAbfrage;
			TabAbfragen.setInhalt("Aic",A.iAbfrage);
			TabAbfragen.setInhalt("aic_begriff",A.iBegriff);
		}
		else
			Qry.update("Abfrage",A.iAbfrage);
		int iCF=g.getCodeAic("Zuordnungsart", "Filter");
		int iCD=g.getCodeAic("Zuordnungsart", "Detail");
		int iCDF=g.getCodeAic("Zuordnungsart", "DetailForm");
		int iCE=g.getCodeAic("Zuordnungsart", "erweitert");
		int iAnz=g.exec("delete from begriff_zuordnung where aic_begriff="+A.iBegriff+" and Art in ("+iCF+(A.iAbfD>=0 ? ","+iCD+","+iCDF+","+iCE:"")+")");
    	if (A.iAbfF>0 )
    		g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff,Art) values ("+A.iBegriff+","+g.AbfToBeg(A.iAbfF)+","+iCF+")");
//		iAnz+=g.exec("delete from begriff_zuordnung where aic_begriff="+A.iBegriff+" and Art="+iCD);		
		if (A.iAbfD>0 )
			g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff,Art) values ("+A.iBegriff+","+g.AbfToBeg(A.iAbfD)+","+iCD+")");
		if (A.iFomD>0)
			g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff,Art) values ("+A.iBegriff+","+A.iFomD+","+iCDF+")");
//		iAnz+=g.exec("delete from begriff_zuordnung where aic_begriff="+A.iBegriff+" and Art="+iCE);		
		if (A.iAbfE>0 )
			g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff,Art) values ("+A.iBegriff+","+g.AbfToBeg(A.iAbfE)+","+iCE+")");
		Qry.free();
		if (iAnz>0)
			g.fixtestError(iAnz+" begriff_zuordnung(en) entfernt");
		g.progInfo("Abfrage="+A.iAbfrage+", Begriff="+A.iBegriff);

		g.setBezeichnung("?",TabAbfragen.getS("Bezeichnung"),Global.iTabBegriff,A.iBegriff,0);
		if (g.Def() && (Memo != null) && Memo.Modified())
		{
			g.setMemo(Memo.getValue(),"",Global.iTabBegriff,A.iBegriff,0);
			Memo.setAktText(Memo.getValue());
		}
		g.putTabBegriffe(iBG_Abfrage,A.iBegriff,sKennung,TabAbfragen.getS("Bezeichnung"),TabAbfragen.getS("DefBez"),null,TabAbfragen.getI("aic_schrift"),null,TabAbfragen.getI("aic_code"),TabAbfragen.getI("prog"),
				TabAbfragen.getI("aic_stammtyp"),iStammtyp<0?-iStammtyp:0,iRolle,lBits,false/*Combo*/,A.bWeb,0,null,A.bTod,null,null,null,null,null,bNeu);
		g.SaveDefVerlauf(A.iBegriff,bNeu ? "neu":sSave.equals("") ? "geändert":"u.a."+sSave);
		//TabAbfragen.push();
		//TabAbfragen.showGrid("Abfragen");
		//TabAbfragen.pop();
		if (!bNeu && (TabAbfragen.getL("bits")&Abfrage.cstBerechtigung)>0)
		{
			AClient.send_AServer("abschluss",g);
	        g.sendWebChanged("periodenSperre");
		}
    sSave="";
		return true;
	}

	private void Save()
	{
		if (checkDoppelte() && !g.Def())
		{
			BtnOk.setEnabled(false);
			return;
		}
		BtnSpeichern.setEnabled(false);
		Thread.yield();
		g.progInfo("-- Save:"+A.iAbfrage);
                checkReihenfolge();
		//iAbfrage=riAbfrage;
		//SQL Qry = new SQL(g);
		boolean bNeu=A.iAbfrage<=0;
		if (!bNeu)
			DeleteAbfrage(true);

		if (Save_Abfrage())
		{
		//Qry.exec("delete from fixeigenschaft from fixeigenschaft join bedingung where aic_abfrage="+iAbfrage);
		//Qry.exec("DELETE FROM Bedingung WHERE Aic_Abfrage="+iAbfrage);
		//Qry.exec("DELETE FROM Spalte WHERE Aic_Abfrage="+iAbfrage);

		/*SQL Qry = new SQL(g);
		g.testInfo("Speichere gebrauchte Abfrage "+iAbfrage+" mit "+iSpalten+" Spalten");
		Qry.add("Spalten",iSpalten);
		Qry.update("Abfrage",iAbfrage);	*/

			if (A.bBedingung)
                        {
                          Save_Rekursion(A.NodVBed /*findNode(Gid.getRootNode(),true)*/, 0,true);
                          Save_Rekursion(A.NodBed /*findNode(Gid.getRootNode(),true)*/, 0,false);
                        }
			if (A.bSpalten)
				Save_Spalten();
                        //g.testInfo("Nur Modell2="+(A.iBits&A.cstNurModell)+" / "+(TabAbfragen.getL("Bits")&A.cstNurModell));
                        if (g.TabAbfragen !=null)
                        {
                          //Abfrage.TabAbfragen = null;
                          //aic_abfrage,aic_begriff,bits,aic_stammtyp,aic_bewegungstyp,aic_modell,autorefresh,spalten,Bed
                          if (bNeu)
                          {
                        	g.TabAbfragen.addInhalt("DefBezeichnung",A.sDefBez);
                            g.TabAbfragen.addInhalt("aic_abfrage",A.iAbfrage);
                            g.TabAbfragen.addInhalt("aic_begriff",A.iBegriff);
                            g.TabAbfragen.addInhalt("bits",A.iBits);
                            g.TabAbfragen.addInhalt("Abits2",A.lBits2);
                            g.TabAbfragen.addInhalt("aic_stammtyp",A.iStt==0?null:A.iStt);
                            g.TabAbfragen.addInhalt("Erf",A.iBew==0 ? null:A.iBew);
                            g.TabAbfragen.addInhalt("Rolle",A.iRolle==0 ? null:A.iRolle);
                            g.TabAbfragen.addInhalt("aic_modell",A.iModell==0?null:A.iModell);
                            g.TabAbfragen.addInhalt("mod_aic_modell",A.iModell2==0?null:A.iModell2);
                            g.TabAbfragen.addInhalt("WebStamm",A.iWebStamm==0?null:A.iWebStamm);
                            g.TabAbfragen.addInhalt("autorefresh",A.iTop==0?null:A.iTop);
                            g.TabAbfragen.addInhalt("spalten",A.Spalten());
                            g.TabAbfragen.addInhalt("Anzahl",A.iAnzahl);
                            g.TabAbfragen.addInhalt("aic_code",TabAbfragen.getI("aic_code"));
                            g.TabAbfragen.addInhalt("prog",TabAbfragen.getI("prog")); // gibt es seit 26.6.2023 wieder in g.TabAbfragen
                            Vector VecC = A.bBedingung ? A.NodBed.getChildren():null;
                            Vector VecC2 = A.bBedingung ? A.NodVBed.getChildren():null;
                            int iBed=(VecC==null ? 0:VecC.size())+(VecC2==null ? 0:VecC2.size());
                            g.TabAbfragen.addInhalt("Bed",iBed);
//                            g.fixtestError(A.sDefBez+": Neu Bed="+iBed+"/"+VecC+"/"+VecC2);
                            g.TabAbfragen.addInhalt("aic_benutzergruppe",TabAbfragen.getInhalt("Aic_Benutzergruppe"));
                            g.TabAbfragen.addInhalt("aic_benutzer",Abfrage.is(TabAbfragen.getL("bits"),Abfrage.cstPersoenlich)?new Integer(g.getBenutzer()):null);
                            g.TabAbfragen.addInhalt("last",new java.util.Date());
                          }
                          else
                          {
                            int iPos=g.TabAbfragen.getPos("aic_abfrage", A.iAbfrage);
                            if (iPos>=0)
                            {
                              g.TabAbfragen.setInhalt(iPos,"DefBezeichnung",A.sDefBez);
                              g.TabAbfragen.setInhalt(iPos,"bits", A.iBits);
                              g.TabAbfragen.setInhalt(iPos,"Abits2",A.lBits2);
                              g.TabAbfragen.setInhalt(iPos,"aic_stammtyp", A.iStt == 0 ? null : A.iStt);
                              g.TabAbfragen.setInhalt(iPos,"Erf",A.iBew==0 ? null:A.iBew);
                              g.TabAbfragen.setInhalt(iPos,"Rolle",A.iRolle==0 ? null:A.iRolle);
                              g.TabAbfragen.setInhalt(iPos,"aic_modell", A.iModell == 0 ? null : A.iModell);
                              g.TabAbfragen.setInhalt(iPos,"mod_aic_modell",A.iModell2==0?null:A.iModell2);
                              g.TabAbfragen.setInhalt(iPos,"WebStamm",A.iWebStamm==0?null:A.iWebStamm);
                              g.TabAbfragen.setInhalt(iPos,"autorefresh", A.iTop == 0 ? null : A.iTop);
                              g.TabAbfragen.setInhalt(iPos,"spalten", A.Spalten());
                              g.TabAbfragen.setInhalt(iPos,"Anzahl", A.iAnzahl);
                              g.TabAbfragen.setInhalt(iPos,"aic_code",TabAbfragen.getI("aic_code"));
                              g.TabAbfragen.setInhalt(iPos,"prog",TabAbfragen.getI("prog"));
                              Vector VecC = A.bBedingung ? A.NodBed.getChildren() : null;
                              Vector VecC2 = A.bBedingung ? A.NodVBed.getChildren():null;
                              int iBed=(VecC == null ? 0 : VecC.size())+(VecC2==null ? 0:VecC2.size());
                              g.TabAbfragen.setInhalt(iPos,"Bed", iBed);
//                              g.fixtestError(A.sDefBez+": Neu Bed="+iBed);
                              g.TabAbfragen.setInhalt(iPos,"aic_benutzergruppe",TabAbfragen.getInhalt("Aic_Benutzergruppe"));
                              g.TabAbfragen.setInhalt(iPos,"last", new java.util.Date());
                              //g.TabAbfragen.showGrid("TabAbfragen");
                              //g.fixtestError("setze Modell bei TabAbfrage"+iPos+": 1="+A.iModell+", 2="+A.iModell2);
                            }
                            else
                              Static.printError("DefAbfrage.Save: Abfrage " + A.iAbfrage + " nicht gefunden!");
                          }
                          //A.TabAbfragen.showGrid("TabAbfragen");
                          if (Static.cache())
                          {
                            g.TabAbfragen.SaveAU("Abfragen.AU0");
                            Static.clearAbfrage(A.iAbfrage);
                          }
                        }
            String sBez=Static.beiLeer(TabAbfragen.getS("Bezeichnung"), TabAbfragen.getS("DefBez"));
			if (bNeu)
			{
				bCboLaden=false;
				CboAbfrage.setItem(A.iAbfrage,sBez,TabAbfragen.getS("Kennung"),0);
				bCboLaden=true;
				//CboAbfrage.setSelectedAIC(A.iAbfrage);
			}
			g.fixtestInfo("Abfrage "+sBez+" wurde gespeichert");
//			g.sendWebDB("refreshDB",null,thisJFrame());
			//new Message(Message.INFORMATION_MESSAGE,(JFrame)thisFrame,g,2).showDialog("gespeichert",new String[] {sBez});
      if ((A.iBits&Abfrage.cstView)>0 && new Message(Message.YES_NO_OPTION,thisJFrame(),g).showDialog("View_erstellen")==Message.YES_OPTION)
        saveView(true);
		}
		//if (g.Debug())
		//	TabSpalten.showGrid("nach Speichern");
		g.progInfo("Save:"+A.iAbfrage+"/"+CboAbfrage.getSelectedAIC()+"/"+TabAbfragen.getI("Aic"));
		
	}

        private boolean Changed()
        {
          boolean b=false;
          if (A.TabSp != null)
            for (A.TabSp.moveFirst();!A.TabSp.out();A.TabSp.moveNext())
              if (!A.TabSp.isNull("Status"))
                b=true;
          return b;
        }

  private int getSubSpalte(boolean bSub)
  {
    int iSub=A.TabSp.getI("sub");
    if (iSub==0)
      return 0;
    if (iSub<=40)
    {
      if (!bSub)
        return iSub;
      int iPos= A.TabSp.getPos("Nummer",iSub);
      if (iPos<0)
        return 0;
      else
        return A.TabSp.getI(iPos,"aic_spalte");
    }
    else
    {
      if (bSub)
        return iSub;
      int iPos= A.TabSp.getPos("aic_spalte",iSub);
      if (iPos<0)
        return 0;
      else
        return A.TabSp.getI(iPos,"Nummer");
    }
  }

	private void Save_Spalten()
	{
			//Vector VecChildren = OutNod.getChildren();
			//g.progInfo("Save_Spalten");
			//TabSpalten.showGrid("Spalten");
			SQL Qry = new SQL(g);
			A.TabSp.moveFirst();
                        Vector<Integer> VecCS=new Vector<Integer>();
			while(!A.TabSp.eof())
			{
				//int iReihe=VecChildren.indexOf(TabSpalten.getInhalt("Node"));
				int iSpalte=A.TabSp.getI("aic_spalte");
                                int iPosE=g.TabEigenschaften.getPos("Aic",Sort.geti(A.TabSp.getInhalt("Vec"),((Vector)A.TabSp.getInhalt("Vec")).size() - 1));
                                if (iPosE<0)
                                  g.fixInfo("nicht gefunden:"+A.TabSp.getInhalt("Vec"));
                                String sDatentyp = g.TabEigenschaften.getS(iPosE,"Datentyp");
				if(A.TabSp.getS("Status").equals("Neu") || A.TabSp.getS("Status").equals("Edit") || A.TabSp.getS("Status").equals("Ersetzt"))
				{
					Qry.add("Aic_Abfrage",A.iAbfrage);
					Qry.add("Kennung",A.TabSp.getS("Kennung"));
					Qry.add("bits",A.TabSp.getI("bits"));
                    Qry.add("bits2",A.TabSp.getI("bits2"));
                    Qry.add("bits3",A.TabSp.getI("bits3"));
					Qry.add0("Soritiert",A.TabSp.getI("Sortiert"));
					Qry.add0("aic_code",A.TabSp.getI("aic_code"));
					Qry.add0("cod_aic_code",A.TabSp.getI("cod_aic_code"));
					Qry.add0("cod2_aic_code",A.TabSp.getI("Ausrichtung"));
                    Qry.add0("cod3_aic_code",A.TabSp.getI("Anzeige"));
                    Qry.add0("sta_aic_stamm",A.TabSp.getI("Rel"));
					Qry.add("Laenge",A.TabSp.getI("Laenge"));
					Qry.add("WebLaenge",A.TabSp.getI("WebLaenge"));
					Qry.add0("BEG_AIC_BEGRIFF",A.TabSp.getI("Sub1"));
					Qry.add0("Abfrage_BEGRIFF",A.TabSp.getI("Sub2"));
					Qry.add0("Modell_BEGRIFF",A.TabSp.getI("Sub3"));
					Qry.add0("Faktor",A.TabSp.getF("Faktor"));
                    Qry.add0("Min",A.TabSp.getF("Min"));
                    Qry.add0("Max",A.TabSp.getF("Max"));
                    Qry.add0("x",A.TabSp.getI("x"));
                    Qry.add0("y",A.TabSp.getI("y"));
                    Qry.add0("w",A.TabSp.getI("w"));
                    Qry.add0("h",A.TabSp.getI("h"));
                    Qry.add("Stil", A.TabSp.getS("Stil"),30);
                    Qry.add("ToggleSight", A.TabSp.getS("ToggleSight"),50);
                    Qry.add0("Spa_aic_Spalte",getSubSpalte(true));
                    Qry.add("Icon", A.TabSp.getS("Icon"),30);
                    Qry.add("Farbe", A.TabSp.getS("Farbe"),30);
					Qry.add0("Filter",A.TabSp.getI("Filter"));
                    Qry.add0("aic_Farbe",A.TabSp.getI("aic_farbe"));
					Qry.add0("aic_Stamm",A.TabSp.getI("Mass"));
                    Qry.add0("aic_Stammtyp",A.TabSp.getI("aic_stammtyp"));
					Qry.add("Reihenfolge",A.TabSp.getI("Reihenfolge"));
					Qry.add0("aic_begriff",A.TabSp.getI("aic_begriff"));
				}
				if(A.TabSp.getS("Status").equals("Del") || A.TabSp.getS("Status").equals("Ersetzt"))
				{
					if (iSpalte>0)
					{
                                          Qry.exec("delete from fixeigenschaft where aic_spalte="+iSpalte);
                                          if(A.TabSp.getS("Status").equals("Del"))
                                          {
                                            Qry.exec("update befehl set aic_spalte=null where def_aic_defverlauf is not null and aic_spalte="+iSpalte);
                                            Qry.exec("delete from SPALTE_BERECHNUNG where aic_spalte="+iSpalte);
                                            Qry.exec("delete from SPALTE_BERECHNUNG where spa_aic_spalte="+iSpalte);
                                            Qry.exec("delete from spalte_zuordnung where aic_spalte="+iSpalte);
                                            Qry.exec("delete from spalten2 where aic_spalte=" + iSpalte);
                                            Qry.exec("delete from spalte_z2 where aic_spalte=" + iSpalte);
                                              Qry.exec("delete from Spalte where aic_spalte="+iSpalte);
                                              Qry.exec("delete from Bezeichnung where aic_tabellenname="+iTab_Spalte+" and aic_fremd="+iSpalte);
                                          }
                                          Qry.exec("delete from Daten_Memo where aic_tabellenname="+iTab_Spalte+" and aic_fremd="+iSpalte);
					}
				}
				if(A.TabSp.getS("Status").equals("Edit"))
				{
					g.setTooltip(A.TabSp.getS("TT"),iTab_Spalte,iSpalte,0);
					//g.progInfo("Update Spalte:"+iSpalte+" von Abfrage "+A.iAbfrage);
					Qry.update("Spalte",iSpalte);
					saveSpalteZuordnung(iSpalte);
          if (sDatentyp.startsWith("Calc") && !sDatentyp.equals("CalcBezeichnung") || A.bWeb && A.iBew==0)
            VecCS.addElement(iSpalte);
					//saveSpalteCalc(iSpalte);

					g.setBezeichnung("Mary ist super",A.TabSp.getS("Bez2"),iTab_Spalte,iSpalte,CboSprache.getSelectedAIC());
				}
				else if(A.TabSp.getS("Status").equals("Neu") || A.TabSp.getS("Status").equals("Ersetzt"))
				{
					Qry.add("Nummer",A.TabSp.getI("Nummer"));
					if(A.TabSp.getS("Status").equals("Neu"))
					  iSpalte = Qry.insert("Spalte",true);
					else
					  Qry.update("Spalte",iSpalte);
					saveSpalteZuordnung(iSpalte);
                                        if (sDatentyp.startsWith("Calc") && !sDatentyp.equals("CalcBezeichnung") || A.bWeb && A.iBew==0)
                                          VecCS.addElement(iSpalte);
					//saveSpalteCalc(iSpalte);
					if (!A.TabSp.getS("TT").equals(""))
						g.setTooltip(A.TabSp.getS("TT"),iTab_Spalte,iSpalte,0);
					g.setBezeichnung("",A.TabSp.getS("Bez2"),iTab_Spalte,iSpalte,CboSprache.getSelectedAIC());
					A.TabSp.setInhalt("aic_spalte",iSpalte);
					//Vector VecEig=(Vector)((JCOutlinerNode)VecChildren.elementAt(iReihe)).getUserData();
					Vector VecEig=(Vector)A.TabSp.getInhalt("Vec");
					for(int i2=0;i2<VecEig.size();i2++)
					{
						Qry.add("Aic_Spalte",iSpalte);
						Object Obj=VecEig.elementAt(i2);
						int iEig=Obj instanceof Integer ? ((Integer)Obj).intValue() : ((BewEig)Obj).Eig();
						if (iEig>0)
							Qry.add("Aic_Eigenschaft",iEig);
						else
						{
							Qry.add("Aic_Eigenschaft",-iEig);
							Qry.add("Richtung",1);
						}
						if (Obj instanceof BewEig)
							Qry.add0("Aic_Bewegungstyp",((BewEig)Obj).Bew());
						Qry.insert("FixEigenschaft",false);
					}
				}
				else if(bReihenfolge && !A.TabSp.getS("Status").equals("Del"))
				{
					Qry.add("Reihenfolge",A.TabSp.getI("Reihenfolge"));
					Qry.update("Spalte",iSpalte);
				}
				if(A.TabSp.getS("Status").equals("Del"))
					A.TabSp.clearInhalt();
				else
				{
					A.TabSp.setInhalt("Status",null);
					A.TabSp.moveNext();
				}
			}
			Qry.free();
      g.fixInfoT("VecCS="+VecCS);
                        if (VecCS.size()>0)
                          for (int i=0;i<VecCS.size();i++)
                            if (A.TabSp.posInhalt("aic_spalte",Sort.getInt(VecCS,i)))
                              saveSpalteCalc(A.TabSp.getI("aic_spalte"));

	}

	private void saveSpalteZuordnung(int iSpalte)
	{
		g.exec("delete from spalte_zuordnung where aic_spalte="+iSpalte);

		if (A.TabSp.getInhalt("Gruppe") != null)
		{
		  	SQL Qry = new SQL(g);
			Vector Vec=(Vector)A.TabSp.getInhalt("Gruppe");
			for(int i=0;i<Vec.size();i++)
			{
				JCOutlinerFolderNode Nod=((JCOutlinerFolderNode)Vec.elementAt(i));
				String s=""+Nod.getLabel();
                                //if (s.length()>20)
                                //  s=s.substring(0,20);
				Vector VecChildren = Nod.getChildren();
				for(int i2=0; VecChildren!=null && VecChildren.size()>i2;i2++)
				{
					Qry.add("aic_spalte",iSpalte);
					Qry.add("Reihenfolge",i*1000+i2+1001);
					Qry.add("Titel",s);
                                        Vector Vec2=(Vector)((JCOutlinerNode)VecChildren.elementAt(i2)).getUserData();
                                        //int iAic=((Combo)((JCOutlinerNode)VecChildren.elementAt(i2)).getLabel()).getAic();
                                        //if (iAic>0)
                                        Qry.add0("aic_stamm",Sort.geti(Vec2,0));
                                        //else
                                        Qry.add0("aic_stammtyp",Sort.geti(Vec2,1));
                                        Qry.add0("aic_eigenschaft",Sort.geti(Vec2,2));
					Qry.insert("Spalte_Zuordnung",false);
				}
			}
			Qry.free();
		}

	}

	private void saveSpalteCalc(int iSpalte)
	{
		g.exec("delete from SPALTE_BERECHNUNG where aic_spalte="+iSpalte);

		if (A.TabSp.getInhalt("Calc") != null)
		{
		  	SQL Qry = new SQL(g);
			Tabellenspeicher Tab=(Tabellenspeicher)A.TabSp.getInhalt("Calc");
			int i=0;
			for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
			{
				i++;
				Qry.add("aic_spalte",iSpalte);
				Qry.add("Pos",i);
				Qry.add("aic_code",((Combo)Tab.getInhalt("Operation")).getAic());
				if (!Tab.isNull("Spalte"))
				{
					A.TabSp.push();
					A.TabSp.posInhalt("Nummer",((Combo)Tab.getInhalt("Spalte")).getAic());
					Qry.add("SPA_AIC_SPALTE",A.TabSp.getI("aic_spalte"));
					A.TabSp.pop();
				}
				if (!Tab.isNull("Wert"))
					Qry.add("WERT",Tab.getF("Wert"));
				if (!Tab.isNull("Eingabe"))
					Qry.add("Eingabe",Tab.getS("Eingabe"));
				Qry.insert("SPALTE_BERECHNUNG",false);
			}
			Qry.free();
		}

	}

	private void Save_Rekursion(JCOutlinerNode OutNod,int iAIC,boolean bVor)
	{
		Vector VecChildren = OutNod.getChildren();
                //g.progInfo("Save_Rekursion-"+bVor+":"+VecChildren);
		for(int i=0; VecChildren!=null && VecChildren.size()>i;i++)
		{
			JCOutlinerNode OutChild = (JCOutlinerNode)VecChildren.elementAt(i);
			Save_Rekursion(OutChild,Save_Element(OutChild,iAIC,bVor),bVor);
		}
	}

	private int Save_Element(JCOutlinerNode OutNod,int iAIC,boolean bVor)
	{
		Vector Vec2 = (Vector)OutNod.getUserData();
		//g.progInfo("Save_Element: Vec2="+Vec2);
//		g.fixtestError("SaveElement:"+OutNod.getLabel()+"="+Vec2);
		SQL Qry = new SQL(g);
		if (iAIC != 0)
			Qry.add("Bed_Aic_Bedingung",iAIC);
		//Qry.add("Aic_eigenschaft",((Integer)Vec2.elementAt(1)).intValue());
        String sVG=(String)Vec2.elementAt(2);
		int iPos=g.getPosBegriff("Vergleich",sVG);
		Qry.add("Aic_Begriff",g.TabBegriffe.getI(iPos,"Aic"));
        Object ObjVW=Vec2.elementAt(3);
		if (ObjVW != null && (!(ObjVW instanceof Integer) || Sort.geti(ObjVW)>0))
                {
                  //g.fixInfo(OutNod.getLabel()+":ObjVW="+Static.print(ObjVW));
                  String s=ObjVW instanceof String ? (String)ObjVW:ObjVW instanceof Integer && (sVG.equals("is null") || sVG.equals("is not null")) ? g.TabRollen.getKennung(Sort.geti(ObjVW)):"???";
                  g.defInfo2("Vergleichswert="+Static.print(ObjVW)+" bei "+sVG+" ->"+s);
                  if (s.startsWith("*Qry von"))
                    s="*Qry von*";
                  else if (s.startsWith("*Joker von"))
                    s=s.substring(0,s.indexOf(","))+"*";
                  else if (s.startsWith("*meine"))
                    s=s.substring(0,s.lastIndexOf('*')+1);
                  //g.fixInfo(OutNod.getLabel()+": Save Vergleichswert="+s);
                  Qry.add("Vergleichswert",s);
                  //Qry.add("Vergleichswert", s.startsWith("*JokerVec") ? "*JokerVec*":
                  //        s.startsWith("*Joker") && !s.equals("*Joker*") ? "*JokerStt*":s);
                }
		Qry.add("Aic_Abfrage",A.iAbfrage);
        int iBBits=Vec2.size()>4 ? Tabellenspeicher.geti(Vec2.elementAt(4)):0;
        if ((iBBits & Abfrage.INDEX)>0)
          iBBits-=Abfrage.INDEX;
        Qry.add("BBits",iBBits+(bVor ? Abfrage.INDEX:0));

		int iBedingung = Qry.insert("Bedingung",true);

		Vector VecEig=(Vector)Vec2.elementAt(1);
		for(int i=0;i<VecEig.size();i++)
		{
			Qry.add("Aic_Bedingung",iBedingung);
			Object Obj=VecEig.elementAt(i);
			int iEig=Obj instanceof Integer ? ((Integer)Obj).intValue() : Obj instanceof Long ? ((Long)Obj).intValue(): ((BewEig)Obj).Eig();
			if (iEig>0)
				Qry.add("Aic_Eigenschaft",iEig);
			else
			{
				Qry.add("Aic_Eigenschaft",-iEig);
				Qry.add("Richtung",1);
			}
			if (Obj instanceof BewEig)
				Qry.add0("Aic_Bewegungstyp",((BewEig)Obj).Bew());
			Qry.insert("FixEigenschaft",false);
		}
		Qry.free();
		return iBedingung;
	}

	// --------------------------------------------------------------------
	// -------------   * * * * *   D E L   * * * * *   --------------------
	// --------------------------------------------------------------------

	public static Tabellenspeicher InfoAbfrage(Global g,int iAbf)
	{
          // in Modell (Spalten bei Befehlen)
	  Tabellenspeicher Tab=new Tabellenspeicher(g,"select distinct 'Modell' Art,defbezeichnung,begriff.kennung,begriff.aic_begriff aic,null Ast"+
              g.AU_Bezeichnung1("stammtyp","begriff")+" Stt"+g.AU_Bezeichnung1("bewegungstyp","begriff")+" Bew"+" from abfrage"+
              g.join2("spalte","abfrage")+g.join2("befehl2","spalte")+",modell"+g.join("begriff","modell")+" where befehl2.aic_modell=modell.aic_modell and abfrage.aic_abfrage="+iAbf,true);
          // in Formular-Darstellung (für Outliner, Tabelle, ...)
          new Tabellenspeicher(g,"select distinct 'Formular' Art,b2.defbezeichnung,b2.kennung,b2.aic_begriff aic"+
                               ",(select kennung from begriff join darstellung d on begriff.aic_begriff=d.aic_begriff where d.aic_darstellung=darstellung.dar_aic_darstellung) Ast"+
                               g.AU_Bezeichnung1("stammtyp","b2")+" Stt"+g.AU_Bezeichnung1("bewegungstyp","b2")+" Bew"+" from abfrage"+
                               g.join("begriff","abfrage")+g.join2("darstellung","begriff")+g.join("formular","darstellung")+
                               g.join("begriff","b2","formular","begriff")+" where abfrage.aic_abfrage="+iAbf,true).addTo(Tab,true);
          // in Formular (Direkt)
          new Tabellenspeicher(g,"select 'Formular' Art,defbezeichnung,kennung,begriff.aic_begriff aic,null Ast"+
                               g.AU_Bezeichnung1("stammtyp","begriff")+" Stt"+g.AU_Bezeichnung1("bewegungstyp","begriff")+" Bew from formular"+
                               g.join("begriff","formular")+" where aic_abfrage="+iAbf,true).addTo(Tab,true);
          // in Druck-Abschnitt
          new Tabellenspeicher(g,"select distinct 'Abschnitt' Art"+g.AU_Bezeichnung1("abschnitt","b2")+" defbezeichnung,b2.kennung,b2.aic_abschnitt aic"+
                               ",null Ast,null Stt,null Bew"+" from abfrage"+
                               g.join("begriff","abfrage")+g.join("abschnitt","b2","begriff","begriff")+" where abfrage.aic_abfrage="+iAbf,true).addTo(Tab,true);
          // Filter in Eigenschaften
          new Tabellenspeicher(g,"select 'Eigenschaft' Art"+g.AU_Bezeichnung2("Eigenschaft")+" defbezeichnung,kennung,aic_Eigenschaft aic,null Ast,null Stt,null Bew from Eigenschaft where aic_abfrage="+iAbf,true).addTo(Tab,true);
          // Filter in Abfrage
          new Tabellenspeicher(g,"select 'Abfrage' Art,defbezeichnung,begriff.kennung,begriff.aic_begriff aic,null Ast"+
                               g.AU_Bezeichnung1("stammtyp","begriff")+" Stt"+g.AU_Bezeichnung1("bewegungstyp","begriff")+" Bew"+" from begriff"+
                               g.join2("abfrage","begriff")+g.join2("spalte","abfrage")+" where filter="+iAbf,true).addTo(Tab,true);
          // in Planung
          new Tabellenspeicher(g,"select 'Planung' Art,defbezeichnung,begriff.kennung,begriff.aic_begriff aic,null Ast"+
                               g.AU_Bezeichnung1("stammtyp","begriff")+" Stt"+g.AU_Bezeichnung1("bewegungstyp","begriff")+" Bew"+" from planung"+
                               g.join("begriff","planung")+" where aic_abfrage="+iAbf+" or abf_aic_abfrage="+iAbf+" or abf2_aic_abfrage="+iAbf,true).addTo(Tab,true);
          // in Hauptschirm-Einstellungen
          new Tabellenspeicher(g,"select 'HS-Filter' Art,defbezeichnung,begriff.kennung,begriff.aic_begriff aic,null Ast"+
                               g.AU_Bezeichnung1("stammtyp","begriff")+" Stt"+g.AU_Bezeichnung1("bewegungstyp","begriff")+" Bew"+" from begriff"+
                               g.join2("hauptschirm","begriff")+g.join2("ansicht","hauptschirm")+" where ansicht.aic_abfrage="+iAbf,true).addTo(Tab,true);
          new Tabellenspeicher(g,"select 'HS-Spalten' Art,defbezeichnung,begriff.kennung,begriff.aic_begriff aic"+
                               g.AU_Bezeichnung1("stammtyp","begriff")+" Stt"+g.AU_Bezeichnung1("bewegungstyp","begriff")+" Bew"+" from begriff"+
                               g.join2("hauptschirm","begriff")+" where aic_abfrage="+iAbf,true).addTo(Tab,true);
          // in Modulen
          new Tabellenspeicher(g,"select bg.kennung Art,b2.defbezeichnung,b2.kennung,b2.aic_begriff aic,null Ast"+
                               g.AU_Bezeichnung1("stammtyp","b2")+" Stt"+g.AU_Bezeichnung1("bewegungstyp","b2")+" Bew"+
                               " from begriffgruppe bg join begriff b2 on bg.aic_begriffgruppe=b2.aic_begriffgruppe join begriff_zuordnung z on z.aic_begriff=b2.aic_begriff join begriff on z.beg_aic_begriff=begriff.aic_begriff"+
                               g.join2("abfrage","begriff")+" where aic_abfrage="+iAbf,true).addTo(Tab,true);
          // bei Benutzer (als Einschränkung)
          new Tabellenspeicher(g,"select 'Benutzer' Art"+g.AU_Bezeichnung2("Benutzer")+" defbezeichnung,kennung,z.aic_benutzer aic,null Ast,null Stt,null Bew from benutzer_zuordnung z"+
                               g.join("benutzer","z")+" where z.aic_abfrage="+iAbf,true).addTo(Tab,true);
          // bei Abschlusstyp
          new Tabellenspeicher(g,"select distinct 'Abschluss' Art"+g.AU_Bezeichnung1("Abschlusstyp","a")+" defbezeichnung,A.kennung,a.aic_Abschlusstyp aic,null Ast,null Stt,null Bew from Abschluss"+
                               " join abschlusstyp a on a.aic_abschlusstyp=abschluss.aic_abschlusstyp where aic_abfrage="+iAbf,true).addTo(Tab,true);
          return Tab;
	}

	private void DeleteAbfrage(boolean bSilent)
	{
		boolean bDelOk=true;
		if (!bSilent)
		{
                  //SQL Qry=new SQL(g);

			//String s1=Qry.list("begriff.defbezeichnung","abfrage"+g.join2("spalte","abfrage")+g.join2("befehl","spalte")+",modell"+g.join("begriff","modell")+" where befehl.aic_modell=modell.aic_modell and abfrage.aic_abfrage="+A.iAbfrage);
			//String s2=Qry.list("b2.defbezeichnung","abfrage"+g.join("begriff","abfrage")+g.join2("darstellung","begriff")+g.join("formular","darstellung")+g.join("begriff","b2","formular","begriff")+" where abfrage.aic_abfrage="+A.iAbfrage);
			//String s3=Qry.list("b2.kennung","abfrage"+g.join("begriff","abfrage")+g.join("abschnitt","b2","begriff","begriff")+" where abfrage.aic_abfrage="+A.iAbfrage);
			//String s4=Qry.list("kennung","eigenschaft where aic_abfrage="+A.iAbfrage);
			//String s5=Qry.list("defbezeichnung","begriff"+g.join2("abfrage","begriff")+g.join2("spalte","abfrage")+" where filter="+A.iAbfrage);
                        //String s6=Qry.list("defbezeichnung","planung"+g.join("begriff","planung")+" where aic_abfrage="+A.iAbfrage+" or abf_aic_abfrage="+A.iAbfrage+" or abf2_aic_abfrage="+A.iAbfrage);
                        //String s7=Qry.list("defbezeichnung","begriff"+g.join2("hauptschirm","begriff")+g.join2("ansicht","hauptschirm")+" where ansicht.aic_abfrage="+A.iAbfrage);
                        //String s8=Qry.list("defbezeichnung","begriff"+g.join2("hauptschirm","begriff")+" where aic_abfrage="+A.iAbfrage);
                        //String s9=Qry.list("b2.defbezeichnung","begriff b2 join begriff_zuordnung z on z.aic_begriff=b2.aic_begriff join begriff on z.beg_aic_begriff=begriff.aic_begriff"+g.join2("abfrage","begriff")+" where aic_abfrage="+A.iAbfrage);
			//g.debugInfo("s1:["+s1+"], s2:["+s2+"]");
			//String s=(s1.equals(" ")?"":"\nModelle:"+s1)+(s2.equals(" ")?"":"\nFormulare:"+s2)+(s3.equals(" ")?"":"\nAbschnitte:"+s3)
			//		+(s4.equals(" ")?"":"\nEigenschaften:"+s4)+(s5.equals(" ")?"":"\nAbfragen:"+s5)+(s6.equals(" ")?"":"\nPlanung:"+s6)
                        //                +(s7.equals(" ")?"":"\nHS-Filter für "+s7)+(s8.equals(" ")?"":"\nHS-Spalten für "+s8)+(s9.equals(" ")?"":"\nModule:"+s9);
		  Tabellenspeicher Tab=InfoAbfrage(g,A.iAbfrage);
		  bDelOk = Tab.isEmpty();

			/*bDelOk=Qry.eof();
			String s="";
			for(Qry.moveFirst();!Qry.eof();Qry.moveNext())
				s= s+(s.equals("") ? "":",")+Qry.getS("Kennung");*/
			if (!bDelOk)
				new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,null,g).showDialog("BereitsVerwendet",new String[] {""+CboAbfrage.Cbo.getSelectedItem()},Tab);
                        else if (bEdit)
                        {
                          new Message(Message.INFORMATION_MESSAGE,(JFrame)thisFrame,g,3).showDialog("nicht verwendet");
                          bDelOk=false;
                        }
                        else
                        {
                          int iLog=SQL.getInteger(g,"select log_aic_logging from begriff where aic_begriff="+A.iBegriff);
                          if (iLog>0)
                          {
                            String sUser=SQL.getString(g,"select benutzer.kennung from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer where logging.aic_logging="+iLog);
                            new Message(Message.WARNING_MESSAGE, null, g).showDialog("Abfrage_nicht_entfernbar", new String[] {sUser});
                            bDelOk=false;
                          }
                        }
		}
		String[] sArr = new String[] {"["+CboAbfrage.Cbo.getSelectedItem()+"]"};
		if (bDelOk && (bSilent || (new Message(Message.YES_NO_OPTION,null,g).showDialog("Loeschen",sArr)== Message.YES_OPTION)))
		{
			SQL Qry=new SQL(g);
			if (A.bBedingung)
			{
				Qry.deleteFrom("fixeigenschaft","fixeigenschaft"+g.join("bedingung","fixeigenschaft")+" where aic_abfrage="+A.iAbfrage);
				Qry.exec("DELETE FROM Bedingung WHERE Aic_Abfrage="+A.iAbfrage);
			}
                        //if (!g.Def() || CbxNotChange==null || !CbxNotChange.isSelected())
                          Qry.add("aic_logging",g.getLog());
			Qry.update("Begriff",SQL.getInteger(g,"SELECT AIC_Begriff FROM abfrage WHERE AIC_abfrage="+A.iAbfrage));

			if (!bSilent)
			{
				if (A.bSpalten)
				{
                                  Vector VecSpalten=SQL.getVector("select aic_spalte from spalte where aic_abfrage="+A.iAbfrage,g);
                                  if (!VecSpalten.isEmpty())
                                  {
                                    Qry.exec("delete from SPALTE_BERECHNUNG where aic_spalte" + Static.SQL_in(VecSpalten));
                                    Qry.exec("delete from SPALTE_BERECHNUNG where spa_aic_spalte" + Static.SQL_in(VecSpalten));
                                    Qry.exec("delete from spalte_zuordnung where aic_spalte" + Static.SQL_in(VecSpalten));
                                    Qry.exec("update befehl set aic_spalte=null where def_aic_defverlauf is not null and aic_spalte" + Static.SQL_in(VecSpalten));
                                    Qry.exec("delete from fixeigenschaft where aic_spalte" + Static.SQL_in(VecSpalten));
                                    Qry.exec("delete from spalten2 where aic_spalte" + Static.SQL_in(VecSpalten));
                                    Qry.exec("DELETE FROM Spalte where aic_spalte" + Static.SQL_in(VecSpalten));
                                  }
				}
				int iBegriff=SQL.getInteger(g,"Select aic_begriff from abfrage where aic_abfrage="+A.iAbfrage);
				Qry.exec("DELETE FROM Abfrage WHERE Aic_Abfrage="+A.iAbfrage);
				Qry.deleteFrom("Begriff","Begriff WHERE Aic_Begriff="+iBegriff,g.iTabBegriff);
				//Qry.exec("delete from bezeichnung from bezeichnung join tabellenname where tabellenname.kennung='Begriff' and aic_fremd="+iBegriff);
				//setSpalten(0);
                                g.TabBegriffe.clearInhalt(g.TabBegriffe.getPos("AIC",new Integer(iBegriff)));
				CboAbfrage.removeItemAt(A.iAbfrage);
                                //TabAbfragen.showGrid("TabAbfragen");
                                //g.testInfo("Begriff="+iBegriff+", Abfrage="+A.iAbfrage);
				TabAbfragen.clearInhalt(TabAbfragen.getPos("Aic",new Integer(A.iAbfrage)));
				A.iAbfrage = CboAbfrage.getSelectedAIC();
				TabAbfragen.posInhalt("Aic",A.iAbfrage);

				Load(true,true);
				EnableButtons();
			}
			Qry.free();
		}

	}

	// --------------------------------------------------------------------------------
	// -------------   * * * * *   E D I T   A b f r a g e   * * * * *   --------------
	// --------------------------------------------------------------------------------

	private void checkFirstAbfragenEdit()
	{
		if (bFirstAbfEdit)
		{
			g.testInfo("checkFirstAbfragenEdit");
			CboBenutzergruppe=new ComboSort(g);
			CboBenutzergruppe.fillDefTable("BENUTZERGRUPPE",true);
			CboSchrift=new Schrift(g);
			CboModell=new ModellEingabe(g,thisFrame);
            CboModell.fillCboM(1);
            CboModell2=new ModellEingabe(g,thisFrame);
            CboModell2.fillCboM(1);
            CboWebStamm=new StammEingabe(thisJFrame(),g,SQL.getInteger(g, "select aic_stammtyp from stammtyp where "+g.bit("SttBits", Global.cstForStatus)),0);
            CboAbfFilter=new AbfrageEingabe(g,thisFrame,true,false);
            CboAbfFilter.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where spalten>0 and"+g.bit("begriff.bits",Abfrage.cstFilter)+" and"+g.not("begriff.bits",Abfrage.cstBerechtigung),"Abfrage",true);
            CboAbfDetail=new AbfrageEingabe(g,thisFrame,true,false);
            CboAbfDetail.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where begriff.web=1 and"+g.bit("abfrage.abits2",Abfrage.cstSubFom),"Abfrage",true);
            CboFomDetail=new ComboSort(g);
            CboFomDetail.fillCbo("select aic_begriff,kennung,defbezeichnung Bezeichnung from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame")+" and "+g.bit("bits", Formular.cstTabFom), "begriff", true,false);
            CboAbfErw=new AbfrageEingabe(g,thisFrame,true,false);
            CboAbfErw.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where begriff.web=1 and"+g.bit("abfrage.abits2",Abfrage.cstSubFom),"Abfrage",true);
			//CboModell.setMaximumRowCount(15);
			CboStammtyp=new ComboSort(g);
			CboStammtyp.fillDefTable("STAMMTYP",true);
                        CboStammtyp.addItemListener(new ItemListener()
                              {
                                public void itemStateChanged(ItemEvent ev)
                                {
                                  if (ev.getStateChange() == ItemEvent.SELECTED && bFillRolle)
                                  {
                                    //g.progInfo("----------------------------------------------    CboRolle2 fill");
                                    CboRolle2.fillRolle(CboStammtyp.getSelectedAIC(), true);
                                  }
                                }
                              });

                        CboRolle2=new RolleEingabe(g,thisFrame);
			//CboRolle2.fillDefTable("ROLLE",true);
			CboTabellentyp=new ComboSort(g);
			CboTabellentyp.fillBegriffTable("Tabellentyp",true);
			CboProg = new ComboSort(g);
			CboProg.fillBegriffTable(g.TabBegriffgruppen.getAic("Programm"),true,true);
			bFirstAbfEdit=false;
		}
	}


	private void NeueAbfrage(final boolean bNeu,final boolean rbSave)
	{
          if (bNeu)
          {
            A.iStt = iStammtyp > 0 ? iStammtyp : 0;
            bSave=true;
            A.bJeder=true;
          }
          g.progInfo("NeueAbfrage:"+bNeu+"/"+A.iStt+"/"+A.iRolle);
		checkFirstAbfragenEdit();
		//g.progInfo("NeueAbfrage:"+A.iAbfrage+"/"+TabAbfragen.getI("Aic")+"/"+CboAbfrage.getSelectedAIC());
                int iAic=CboAbfrage.getSelectedAIC();
		if(!bNeu)
			TabAbfragen.posInhalt("Aic",iAic);
		final long iVorher=bNeu?g.Def()?Abfrage.cstNurModell:0:TabAbfragen.getL("bits");
		final long lVorher2=bNeu?0:TabAbfragen.getL("Abits2");
                //g.progInfo("NeueAbfrage: bits="+iVorher);
		final String sDefBezeichnungVorher = bNeu ? "":TabAbfragen.getS("DefBez");
        final String sBezeichnungVorher = bNeu ? "":TabAbfragen.getS("Bezeichnung");
		final String sKennungVorher = bNeu ? "":TabAbfragen.getS("Kennung");
		final int iModellVorher = bNeu ? 0:TabAbfragen.getI("aic_modell");
        final int iModellVorher2 = bNeu ? 0:TabAbfragen.getI("mod_aic_modell");
		final int iSchriftVorher = bNeu ? 0:TabAbfragen.getI("aic_schrift");
		final int iBGVorher = bNeu || rbSave ? g.getHBG():TabAbfragen.getI("Aic_Benutzergruppe");
		final int iSttVorher = bNeu ? iStammtyp:TabAbfragen.getI("Aic_Stammtyp");
		final int iTypVorher = bNeu ? 0:TabAbfragen.getI("Aic_Code");
		final int iAutoRefreshVorher = bNeu ? 0:TabAbfragen.getI("Autorefresh");
		EdtBezeichnung.setText(sBezeichnungVorher);
    EdtDefBezeichnung.setText(sDefBezeichnungVorher);
		EdtKennungA.setText(sKennungVorher);
    EdtKennungA.setEditable(iAic<0 || bNeu || iNeuAic==iAic || g.Def() && (EdtKennungA.getText().equals("") || A.bWeb));//(lVorher2&Abfrage.cstAbfWeb)>0));
                //g.testInfo("Benutzergruppe vorher:"+iBGVorher);
		CboBenutzergruppe.setSelectedAIC(iBGVorher);
                //g.testInfo("Benutzergruppe nachher:"+CboBenutzergruppe.getSelectedAIC());
		CboModell.setSelectedAIC(iModellVorher);
        CboModell2.setSelectedAIC(iModellVorher2);
        if (A.iAbfD<0)
        {
//        	int iCF=g.getCodeAic("Zuordnungsart", "Filter");
        	int iCD=g.getCodeAic("Zuordnungsart", "Detail");
        	int iCDF=g.getCodeAic("Zuordnungsart", "DetailForm");
        	int iCE=g.getCodeAic("Zuordnungsart", "erweitert");
//        	A.iAbfF=SQL.getInteger(g,"select aic_abfrage from abfrage join begriff_zuordnung z on z.beg_aic_begriff=abfrage.aic_begriff join begriff b on z.beg_aic_begriff=b.aic_begriff and z.aic_begriff="+A.iBegriff+" and z.Art="+iCF);
        	A.iAbfD=SQL.getInteger(g,"select aic_abfrage from abfrage join begriff_zuordnung z on z.beg_aic_begriff=abfrage.aic_begriff join begriff b on z.beg_aic_begriff=b.aic_begriff and z.aic_begriff="+A.iBegriff+" and z.Art="+iCD);
        	A.iFomD=SQL.getInteger(g,"select beg_aic_begriff from begriff_zuordnung z where z.aic_begriff="+A.iBegriff+" and z.Art="+iCDF);
        	A.iAbfE=SQL.getInteger(g,"select aic_abfrage from abfrage join begriff_zuordnung z on z.beg_aic_begriff=abfrage.aic_begriff join begriff b on z.beg_aic_begriff=b.aic_begriff and z.aic_begriff="+A.iBegriff+" and z.Art="+iCE);
        	// g.fixtestError("iAbfF="+A.iAbfF+", iAbfD="+A.iAbfD+", iFomD="+A.iFomD+", iAbfE="+A.iAbfE);//+" bei iCF="+iCF+", iCD="+iCD);
        }
        CboWebStamm.setStamm(A.iWebStamm);
        CboAbfFilter.setSelectedAIC(A.iAbfF);
        CboAbfDetail.setSelectedAIC(A.iAbfD);
        CboFomDetail.setSelectedAIC(A.iFomD);
        CboAbfErw.setSelectedAIC(A.iAbfE);
		CboSchrift.setSelectedAIC(iSchriftVorher);
                bFillRolle=false;
		CboStammtyp.setSelectedAIC(iSttVorher);
                CboRolle2.fillRolle(iRolle==0 || g.RolleToStt(iRolle)==iSttVorher ? iSttVorher:-1, true);
                CboRolle2.setSelectedAIC(iRolle);
                bFillRolle=true;
		CboTabellentyp.setSelectedAIC(iTypVorher);
		CboProg.setSelectedAIC(g.TabBegriffe.getI_AIC("Prog", A.iBegriff));
                //if (CbxNotChange==null)
                //  CbxNotChange=g.getCheckbox("Stichtag lassen");
                //CbxNotChange.setSelected(g.Def() && A.is(iVorher,Abfrage.cstGruppe));
		final JCheckBox CbxVersionsup = getCheckboxM("Versionsupdate",Abfrage.is(iVorher,Abfrage.cstVersionsup));
		final JCheckBox CbxExpo = getCheckboxM("exportierbar",Abfrage.is(iVorher,Abfrage.cstExportierbar));
		final JCheckBox CbxImpo = getCheckboxM("importierbar",Abfrage.is(iVorher,Abfrage.cstImportierbar));
		final JCheckBox CbxSumme = getCheckboxM("Summe",Abfrage.is(iVorher,Abfrage.cstSumme));
		final JCheckBox CbxNurModell = getCheckboxM("nur_Modell",Abfrage.is(iVorher,Abfrage.cstNurModell));
		final JCheckBox CbxKeinZR = getCheckboxM("kein_Bew-Zeitraum",Abfrage.is(iVorher,Abfrage.cstKeinZeitraum));
		final JCheckBox CbxKeinSZR = getCheckboxM("kein_Stamm-Zeitraum",Abfrage.is(iVorher,Abfrage.cstKeinStammZeitraum));
		final JCheckBox CbxKeinRefresh = getCheckboxM("kein_Refresh",Abfrage.is(iVorher,Abfrage.cstKeinRefresh));
		final AUCheckBox CbxKeinStamm = getCheckboxM("kein_Stammsatz",Abfrage.is(iVorher,Abfrage.cstKeinStamm));
		final JCheckBox CbxDruckbar = getCheckboxM("druckbar",Abfrage.is(iVorher,Abfrage.cstDruckbar));
		final JCheckBox CbxAuswertung = getCheckboxM("Auswertung",Abfrage.is(iVorher,Abfrage.cstAuswertung));
		final AUCheckBox CbxMengen = getCheckboxM("Mengen",Abfrage.is(iVorher,Abfrage.cstMengen));
    final JCheckBox CbxFremdStamm = getCheckboxM("FremdStamm",Abfrage.is(iVorher,Abfrage.cstFremdStamm));
		final JCheckBox CbxKeinNeu = getCheckboxM("kein_Neu",Abfrage.is(iVorher,Abfrage.cstKeinNeu));
		final JCheckBox CbxKeinSave = getCheckboxM("kein_Speichern",Abfrage.is(iVorher,Abfrage.cstKeinSave));
		final JCheckBox CbxKeinDel = getCheckboxM("kein_Entfernen",Abfrage.is(iVorher,Abfrage.cstKeinDel));
		final JCheckBox CbxNichtUpdaten = getCheckboxM("nicht_updaten",Abfrage.is(iVorher,Abfrage.cstNichtUpdaten));
		final JCheckBox CbxSubrollen = getCheckboxM("Subrollen",Abfrage.is(iVorher,Abfrage.cstSubrollen));
		final JCheckBox CbxAnhaengen = getCheckboxM("anhaengen",Abfrage.is(iVorher,Abfrage.cstAnhaengen));
		final JCheckBox CbxKeinTitel = getCheckboxM("kein_Titel",Abfrage.is(iVorher,Abfrage.cstKeinTitel));
		final JCheckBox CbxKeinStt = getCheckboxM("kein_Stammtyp",Abfrage.is(iVorher,Abfrage.cstKeinStt));
		final JCheckBox CbxFirst = getCheckboxM("First",Abfrage.is(iVorher,Abfrage.cstFirst));
                final JRadioButton RadPlanungX = getRadiobuttonM("keine_Planung"); RadPlanungX.setSelected((iVorher&Abfrage.cstPlanung)==0);
		final JRadioButton RadPlanungD = getRadiobuttonM("Planungsdaten"); RadPlanungD.setSelected((iVorher&Abfrage.cstPlanung)==Abfrage.cstPlanungD);
		final JRadioButton RadPlanungF = getRadiobuttonM("Planungsfilter");RadPlanungF.setSelected((iVorher&Abfrage.cstPlanung)==Abfrage.cstPlanungF);
                final JRadioButton RadPlanungS = getRadiobuttonM("Planung_sonst"); RadPlanungS.setSelected((iVorher&Abfrage.cstPlanung)==Abfrage.cstPlanungS);
                ButtonGroup RadPlanung=new ButtonGroup();
                RadPlanung.add(RadPlanungX);
                RadPlanung.add(RadPlanungD);
                RadPlanung.add(RadPlanungF);
                RadPlanung.add(RadPlanungS);
		final JCheckBox CbxEntfernte = getCheckboxM("auch_entfernte",Abfrage.is(iVorher,Abfrage.cstEntfernte));
		final JCheckBox CbxPersoenlich = getCheckboxM("persoenlich",bNeu || iAic<0 ? !g.Spezial():Abfrage.is(iVorher,Abfrage.cstPersoenlich));
                CbxPersoenlich.setEnabled(g.Spezial());
		final JCheckBox CbxStempelimport = getCheckboxM("Stempelimport",Abfrage.is(iVorher,Abfrage.cstStempelimport));
		final JCheckBox CbxView = getCheckboxM("View",Abfrage.is(iVorher,Abfrage.cstView));
		final JCheckBox CbxZR_Wahl = getCheckboxM("ZR_Wahl",Abfrage.is(iVorher,Abfrage.cstZR_Wahl));
        final JCheckBox CbxBewVoll = getCheckboxM("BewVoll",Abfrage.is(iVorher,Abfrage.cstBewVoll));
		//final JCheckBox CbxSuchen = getCheckboxM("Suchen",A.is(iVorher,Abfrage.cstSuchen));
		final JCheckBox CbxSynchron = getCheckboxM("Synchron",Abfrage.is(iVorher,Abfrage.cstSynchron));
		final JCheckBox CbxChanges = getCheckboxM("Changes",Abfrage.is(iVorher,Abfrage.cstChanges));
		final JCheckBox CbxVerteiler = getCheckboxM("Verteiler",Abfrage.is(iVorher,Abfrage.cstVerteiler));
		final JCheckBox CbxNoChange = getCheckboxM("no_change",Abfrage.is(iVorher,Abfrage.cstNoChange));
		final JCheckBox CbxFeiertage = getCheckboxM("Feiertage",Abfrage.is(iVorher,Abfrage.cstFeiertage));
		final JCheckBox CbxFilter = getCheckboxM("Filter",bNeu ? !A.bSpalten:Abfrage.is(iVorher,Abfrage.cstFilter));
                final JCheckBox CbxHS = getCheckboxM("HS",bNeu ? A.isHS():Abfrage.is(iVorher,Abfrage.cstHS));
                final JCheckBox CbxBer=getCheckboxM("Berechtigung",bRechte || Abfrage.is(iVorher,Abfrage.cstBerechtigung));
		//final JCheckBox CbxJoker = getCheckboxM("Joker-Sync",A.is(iVorher,Abfrage.cstJoker));
		final AUCheckBox CbxDistinct = getCheckboxM("Distinct",Abfrage.is(iVorher,Abfrage.cstDistinct));
                final JCheckBox CbxVerdichten = getCheckboxM("verdichten",Abfrage.is(iVorher,Abfrage.cstVerdichten));
                final JCheckBox CbxEinzeldaten = getCheckboxM("Einzeldaten",Abfrage.is(iVorher,Abfrage.cstEinzeldaten));
                final AUCheckBox CbxTTO = getCheckboxM("TTO",Abfrage.is(iVorher,Abfrage.cstTTO));
                final AUCheckBox CbxCompress = getCheckboxM("Compress",Abfrage.is(iVorher,Abfrage.cstCompress));
                final JCheckBox CbxKeineGesamtsumme = getCheckboxM("keine_Gesamtsumme",Abfrage.is(iVorher,Abfrage.cstKeineGesamtsumme));
                final JCheckBox CbxPriviligiert = getCheckboxM("priviligiert",Abfrage.is(iVorher,Abfrage.cstPriviligiert));
                final JCheckBox CbxVorletzteEbene = getCheckboxM("vorletzte Ebene",Abfrage.is(iVorher,Abfrage.cstVorletzteEbene));
                final JCheckBox Cbx0Werte = getCheckboxM("0-Werte",Abfrage.is(iVorher,Abfrage.cst0Werte));
                final JCheckBox CbxLeer = getCheckboxM("leer",Abfrage.is(iVorher,Abfrage.cstLeer));
                final JCheckBox CbxKeinAustritt = getCheckboxM("kein_Austritt",Abfrage.is(iVorher,Abfrage.cstKeinAustritt));
                final JCheckBox CbxStammstichtag = getCheckboxM("Stammstichtag",Abfrage.is(iVorher,Abfrage.cstStammstichtag));
                //final JCheckBox CbxWiePeriode = getCheckboxM("wie Periode",false);//A.is(iVorher,Abfrage.cstWiePeriode));
                final JCheckBox CbxNoSort = getCheckboxM("nicht sortierbar",Abfrage.is(iVorher,Abfrage.cstNoSort));
                final JCheckBox CbxKeinTest = getCheckboxM("kein_Test",Abfrage.is(iVorher,Abfrage.cstKeinTest));
                final JCheckBox CbxGleiche = getCheckboxM("Gleiche",Abfrage.is(iVorher,Abfrage.cstGleiche));
                final JCheckBox CbxFremdJoker = getCheckboxM("FremdJoker",Abfrage.is(iVorher,Abfrage.cstFremdJoker));
                final JCheckBox CbxUmsortieren = getCheckboxM("Umsortieren",Abfrage.is(iVorher,Abfrage.cstUmsortieren));
                final JCheckBox CbxAusblenden = getCheckboxM("Ausblenden",Abfrage.is(iVorher,Abfrage.cstAusblenden));
                final JCheckBox CbxNachSave = getCheckboxM("nach_Save",Abfrage.is(iVorher,Abfrage.cstNachSave));
                final JCheckBox CbxWeiterfuehren = getCheckboxM("Weiterfuehren",Abfrage.is(iVorher,Abfrage.cstWeiterfuehren));
                //final JCheckBox CbxANR = getCheckboxM("ANR",A.is(iVorher,Abfrage.cstANR));
                final AUCheckBox CbxLDATE = getCheckboxM("LDATE",Abfrage.is(iVorher,Abfrage.cstLDATE));
                //--- Abfrage-Bits2 ---
                //final JCheckBox CbxJavaFX = getCheckboxM("JavaFX",Abfrage.is(lVorher2,Abfrage.cstAbfFX));
                final JCheckBox CbxRefreshNS = getCheckboxM("refreshNS",Abfrage.is(lVorher2,Abfrage.cstRefreshNS));
                final JCheckBox CbxKeinBez = getCheckboxM("kein_Abfragetitel",Abfrage.is(lVorher2,Abfrage.cstKeinBez));
                final JCheckBox CbxNoSB = getCheckboxM("noSB",Abfrage.is(lVorher2,Abfrage.cstNoSB));
                final AUCheckBox CbxMulti = getCheckboxM("Multiselect3",Abfrage.is(lVorher2,Abfrage.cstAbfMulti));
                final JCheckBox CbxAPI = getCheckboxM("API",Abfrage.is(lVorher2,Abfrage.cstAbfAPI));
                //final JCheckBox CbxErsteOffen = getCheckboxM("erste_offen",Abfrage.is(lVorher2,Abfrage.cstErsteOffen));
                final AUCheckBox CbxCompress2 = getCheckboxM("compress2",Abfrage.is(lVorher2,Abfrage.cstCompress2));
//                final JCheckBox CbxNullWeg = getCheckboxM("Nullebene_weg",Abfrage.is(lVorher2,Abfrage.cstNullWeg));
                final JCheckBox CbxSubFom = getCheckboxM("Subformular",Abfrage.is(lVorher2,Abfrage.cstSubFom));
                final JCheckBox CbxSuche2 = getCheckboxM("Suche2",Abfrage.is(lVorher2,Abfrage.cstSuche2));
                final JCheckBox CbxKeinMandant = getCheckboxM("kein_Mandant",Abfrage.is(lVorher2,Abfrage.cstKeinMandant));
                final JCheckBox CbxTEdit = getCheckboxM("TEdit",Abfrage.is(lVorher2,Abfrage.cstTEdit));
                final JCheckBox CbxWeb = getCheckboxM("Web",A.bWeb);//Abfrage.is(lVorher2,Abfrage.cstAbfWeb));
                final AUCheckBox CbxJeder = getCheckboxM("Jeder",A.bJeder);
                final JCheckBox CbxSystem = getCheckboxM("System",Abfrage.is(lVorher2,Abfrage.cstAbfSystem));
//                final JCheckBox CbxHochTitel = getCheckboxM("HochTitel",Abfrage.is(lVorher2,Abfrage.cstHochTitel));
                //final JCheckBox CbxWebFom = g.getCheckbox("Web-Formular",Abfrage.is(lVorher2,Abfrage.cstWebFom));
                final JCheckBox CbxDialogEdit = getCheckboxM("DialogEdit",Abfrage.is(lVorher2,Abfrage.cstDialogEdit));
                final JCheckBox CbxLokZR = getCheckboxM("lokaler_Zeitraum",Abfrage.is(lVorher2,Abfrage.cstLokZR));
                final JCheckBox CbxJVecAlle = getCheckboxM("JokerVec_alle",Abfrage.is(lVorher2,Abfrage.cstJVecAlle));
                final JCheckBox CbxKeinSyncVec = getCheckboxM("kein_SyncVec",Abfrage.is(lVorher2,Abfrage.cstkeinSyncVec));
                final JCheckBox CbxAbfJahr = getCheckboxM("Abfrage_Jahr",Abfrage.is(lVorher2,Abfrage.cstAbfJahr));
                final JCheckBox CbxDelZw = getCheckboxM("Delete_Empty",Abfrage.is(lVorher2,Abfrage.cstDelZw));
                final JCheckBox CbxModellDlg = getCheckboxM("Modell_Dialog",Abfrage.is(lVorher2,Abfrage.cstModellDlg));
                final JCheckBox CbxQryAlle = getCheckboxM("alleBeiKein",Abfrage.is(lVorher2,Abfrage.cstQryAlle));
                final JCheckBox CbxForStatus = getCheckboxM("forStatus",Abfrage.is(lVorher2,Abfrage.cstForStatus));
                final JCheckBox CbxSbRefresh = getCheckboxM("SB-Refresh",Abfrage.is(lVorher2,Abfrage.cstSbRefresh));
                final JCheckBox CbxModFarbe = getCheckboxM("ModellFarbe",Abfrage.is(lVorher2,Abfrage.cstModFarbe));
                final JCheckBox CbxPersSperre = getCheckboxM("PersSperre",Abfrage.is(lVorher2,Abfrage.cstPersSperre));
                final JCheckBox CbxKeineEinheit = getCheckboxM("keine_Einheit",Abfrage.is(lVorher2,Abfrage.cstKeineME));
                final JCheckBox CbxSQL2 = getCheckboxM("SQL_aufgeteilt",Abfrage.is(lVorher2,Abfrage.cstSQL2));
                final Zahl EdtPagAnzahl = new Zahl(0);
                final Zahl EdtSpAnzahl = new Zahl(0);
        		
        final AUCheckBox CbxTod=getCheckboxM("Tod",A.bTod);
        /*if (A.iBegriff>0)
        {
        	int iPos=g.TabBegriffe.getPos("Aic", A.iBegriff);
        	if (iPos>=0)
        		CbxTod.setSelected(g.TabBegriffe.getB(iPos,"Tod"));
        }*/
		final SpinBox SboAutoRefresh = new SpinBox();
		SboAutoRefresh.setIntValue(iAutoRefreshVorher);
		thisJFrame().setEnabled(false);
		Dlg = new JDialog((Frame)thisFrame,g.getBegriffS("Dialog","Neue Abfrage"),false);//bEdit);
		Dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		JPanel PnlOben = new JPanel(new BorderLayout());
		JPanel PnlCenter = new JPanel(new GridLayout(0,3));
		JPanel PnlMet = new JPanel(new GridLayout(0,3));
		JPanel PnlDar = new JPanel(new GridLayout(0,3));
		JPanel PnlWeb = new JPanel(new GridLayout(0,3,1,3));
		JPanel PnlInfo = new JPanel(new GridLayout(0,3));
			JPanel PnlLbl = new JPanel(new GridLayout(0,1,2,2));
			JPanel PnlEdt = new JPanel(new GridLayout(0,1,2,2));
			//if (g.Def())
            {
              g.addLabel4(PnlLbl, "DefBezeichnung");
              PnlEdt.add(EdtDefBezeichnung);
            }
            EdtDefBezeichnung.setEditable(g.Def());
            g.addLabel4(PnlLbl,"Bezeichnung");
            PnlEdt.add(EdtBezeichnung);
            if (g.Def())
            {
              g.addLabel4(PnlLbl,"Kennung");
              PnlEdt.add(EdtKennungA);
              g.addLabel4(PnlLbl,"KennzeichenA");
              if (A.iBegriff>0)
              	EdtKennzeichen.setText(SQL.getString(g,"select kennzeichen from begriff where aic_begriff="+A.iBegriff));
              PnlEdt.add(EdtKennzeichen);
              g.addLabel4(PnlLbl,"Programm");
              PnlEdt.add(CboProg);
            }
			g.addLabel4(PnlLbl,"Stammtyp");
                        JPanel PnlStammtyp=new JPanel(new BorderLayout());
                        JPanel PnlFill=new JPanel(new GridLayout());
                        if (A.iBew>0)
                        {
                          JButton BtnAlle=g.getButton("all");
                          BtnAlle.addActionListener(new ActionListener()
                          {
                            public void actionPerformed(ActionEvent ev)
                            {
                              CboStammtyp.fillDefTable("STAMMTYP", true);
                            }
                          });
                          JButton BtnANR = g.getButton("ANR");
                          BtnANR.addActionListener(new ActionListener()
                          {
                            public void actionPerformed(ActionEvent ev)
                            {
                              CboStammtyp.fillANR( -iStammtyp);
                            }
                          });
                          PnlFill.add(BtnAlle);
                          PnlFill.add(BtnANR);
                          PnlStammtyp.add("West", PnlFill);
                        }
                        PnlStammtyp.add("Center",CboStammtyp);
                        if (A.iBew>0)
                        {
                          String s= CboStammtyp.isNull()? null:g.getANR_BS(A.iBew,CboStammtyp.getSelectedAIC());
                          JLabel Lbl=new JLabel(s==null ? "":s);
                          if (s!=null)
                          {
                            Lbl.setFont(Global.fontBezeichnung);
                            int iEig=g.getEigANR(A.iBew,s);
                            if (iEig>0)
                              Lbl.setToolTipText(g.TabEigenschaften.getBezeichnungS(iEig)+" ("+iEig+")");
                          }
                          PnlStammtyp.add("East", Lbl);
                        }
			PnlEdt.add(PnlStammtyp);
                        //PnlEdt.add(PnlDown);
			//if (g.Spezial())
			//{
                        CboStammtyp.setEnabled(A.iBew>0);
                        //if (A.iBew==0)
                        {
                          bFillRolle=false;
                          CboStammtyp.setSelectedAIC(A.iStt);
                          bFillRolle=true;
                          g.addLabel4(PnlLbl,"Rolle");
                          JButton BtnAlle=g.getButton("all");
                          BtnAlle.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent ev) {
                              CboRolle2.fillRolle(-1, true);
                            }
                          });

                            JPanel PnlRolle = new JPanel(new BorderLayout());
                            PnlRolle.add("West", BtnAlle);
                            PnlRolle.add("Center", CboRolle2);
                            PnlEdt.add(PnlRolle);
                        }
			//}
                        if (g.Def())
			{
                                //PnlDown = new JPanel(new GridLayout(0,1));
				if (!A.isHS())
                                {
                                  g.addLabel4(PnlLbl, "Modell");
                                  PnlEdt.add(CboModell);
                                  g.addLabel4(PnlLbl, "Modell2");
                                  PnlEdt.add(CboModell2);
                                  g.addLabel4(PnlLbl, "Filter");
                                  PnlEdt.add(CboAbfFilter);
                                  g.addLabel4(PnlLbl, "Schrift");
                                  PnlEdt.add(CboSchrift);
                                }
                                //PnlEdt.add(PnlDown);
			}
                        //JPanel PnlDownW = new JPanel(new GridLayout(0,1));
                        //JPanel PnlDownC = new JPanel(new GridLayout(0,1));
                        //JPanel PnlDownE = new JPanel(new GridLayout(0,1));

			//if (g.Spezial())
                          CboBenutzergruppe.setEnabled(g.Spezial());

			if (A.bSpalten)
			{
                          if (g.Spezial() && !A.isHS())
                          {
                            /*g.addLabel(PnlLbl,g.getBezeichnung("Begriffgruppe", "Tabellentyp"));
                            JPanel PnlSp=new JPanel(new GridLayout(1,3,5,0));
                            PnlSp.add(CboTabellentyp);
                            g.addLabel2(PnlSp, "Schrift");
                            PnlSp.add(CboSchrift);
                            PnlEdt.add(PnlSp);*/

                            g.addLabel4(PnlLbl,"Autorefresh");
                            //PnlLbl.add(CbxFirst);
                            //JPanel PnlAutoRefresh=new JPanel(new GridLayout(1,2,5,0));
                            //g.addLabel2(PnlAutoRefresh,"Autorefresh");
                            SboAutoRefresh.setSize(10,10);
                            JPanel PnlB=new JPanel(new GridLayout(1,2,/*g.Def() ?6:*/2,0));
                            //PnlB.add(SboAutoRefresh);
                            //PnlB.add(CbxFirst);
                            g.addWCE(PnlB,SboAutoRefresh,CbxFirst,null);
                            //PnlB.add(PnlAutoRefresh);
                            //g.addLabel2(PnlB,"Tabellentyp");
                            JLabel LblTT=getLabelR("Tabellentyp");
                            //PnlB.add(CboTabellentyp);
                            JPanel PnlTT=null;
                            if (g.Def())
                            {
                            	ActionListener AL2=new ActionListener()
                          		{
                          			public void actionPerformed(ActionEvent ev)
                          			{
                          				String s = ev.getActionCommand();
                          				if (s.equals("Neu"))
                          					TabellentypNeu(this);
                          				else if (s.equals("ProgDel"))
                                            ProgDel();
                                          else if (s.equals("OkP"))
                                            ProgSave();
                                          else if (s.equals("AbbruchP"))
                                            DlgProg.setVisible(false);
                          			}
                          		};
                          		JButton BtnTT = g.getButton("Neu");   
                          		g.BtnAdd(BtnTT, "Neu", AL2);
                          		JButton BtnDel=g.getButton("Loeschen");
                          		g.BtnAdd(BtnDel, "ProgDel", AL2);
                          		PnlTT=g.addTwo(BtnTT,BtnDel);
                          		/*                       	
                            	BtnTT.addActionListener(new ActionListener()
                        		{
                        			public void actionPerformed(ActionEvent ev)
                        			{
                        				TabellentypNeu(AL2);
                        			}
                        		});*/
                            }
                            	//PnlB.add(BtnTT);
                            g.addWCE(PnlB,LblTT,CboTabellentyp,PnlTT);
                            PnlEdt.add(PnlB);
                            //PnlEdt.add(PnlAutoRefresh);
                          }
                          if (!A.isHS())
                          {
//                            g.addLabel4(PnlCenter, "Verwendung");
//                            PnlCenter.add(new JLabel());
//                            PnlCenter.add(new JLabel());

                            if (g.Def())
                            {
                              PnlCenter.add(CbxNurModell);
                              PnlMet.add(CbxLeer);
                              PnlCenter.add(CbxVersionsup);
                              PnlCenter.add(CbxVerteiler);                            
                              //PnlCenter.add(CbxSuchen);
                              //PnlCenter.add(CbxOLE);
                              PnlCenter.add(CbxStempelimport);//CbxNoRule);
                              PnlCenter.add(CbxAuswertung);
                              PnlCenter.add(CbxNichtUpdaten);
                            }
                            PnlCenter.add(CbxExpo);
                            PnlCenter.add(CbxImpo);
                            PnlCenter.add(CbxDruckbar);

                            if (g.Spezial())
                            {
                              PnlDar.add(CbxTTO);
                              PnlDar.add(CbxVerdichten);
                              PnlDar.add(CbxEinzeldaten);
                            }
                            
                            if (g.Def())
                            {
						      PnlCenter.add(CbxAPI);
						      PnlCenter.add(new JLabel());
						      PnlCenter.add(new JLabel());
                            	
                              PnlMet.add(CbxSynchron);
                              PnlDar.add(CbxNoSort);

                              PnlMet.add(CbxChanges);
                              PnlCenter.add(RadPlanungX);
                              PnlCenter.add(RadPlanungD);
                              PnlCenter.add(RadPlanungF);
                              PnlCenter.add(RadPlanungS);

                              PnlMet.add(CbxFremdJoker);
                              PnlDar.add(CbxAusblenden);
                              PnlMet.add(CbxWeiterfuehren);   
                              
//                              g.addLabel4(PnlWeb, "AbfFilter");
//                              PnlWeb.add(CboAbfFilter);
//                              PnlWeb.add(new JLabel());
                              
                              g.addLabel4(PnlWeb, "Stamm");
                              PnlWeb.add(CboWebStamm);
                              PnlWeb.add(new JLabel());
                              
                              g.addLabel4(PnlWeb, "AbfDetail");
                              PnlWeb.add(CboAbfDetail);
                              PnlWeb.add(new JLabel());
                              
                              g.addLabel4(PnlWeb, "FomDetail");
                              PnlWeb.add(CboFomDetail);
                              PnlWeb.add(new JLabel());
                              
                              g.addLabel4(PnlWeb, "AbfErweitert");
                              PnlWeb.add(CboAbfErw);
                              PnlWeb.add(new JLabel());
                              
                              EdtPagAnzahl.setMax(4000);
                              EdtSpAnzahl.setMax(15);
                              
                              EdtPagAnzahl.setValue(A.getPagAnzahl());
                              EdtSpAnzahl.setValue(A.getSpAnzahl());
                              
                              g.addLabel4(PnlWeb,"PagAnzahl");
                              PnlWeb.add(EdtPagAnzahl);
                              PnlWeb.add(new JLabel());
                              
                              g.addLabel4(PnlWeb,"SpAnzahl");
                              PnlWeb.add(EdtSpAnzahl);
                              PnlWeb.add(new JLabel());
                              
                              PnlWeb.add(new JLabel());
                              PnlWeb.add(new JLabel());
                              PnlWeb.add(new JLabel());

//                              g.addLabel4(PnlWeb,"JavaFx");
//                              PnlWeb.add(new JLabel());
//                              PnlWeb.add(new JLabel());
//                              PnlWeb.add(CbxJavaFX);
//                              //PnlWeb.add(CbxResize);//CbxView2);
//                              PnlWeb.add(CbxKeinBez);

                            }
                            if (g.Spezial())
                            {
                              PnlDar.add(CbxGleiche);
                              PnlDar.add(CbxCompress);
                              if (g.Def())
                                PnlCenter.add(CbxNachSave);
                              else
                                PnlCenter.add(new JLabel());
                            }
                            if (g.Def())
                            {
                            	PnlDar.add(CbxCompress2);
                                PnlMet.add(CbxLDATE);
                                PnlDar.add(new JLabel());
                                
//                                PnlWeb.add(CbxKeinMulti);
//                                PnlWeb.add(CbxSelCol);
//                                PnlWeb.add(CbxGa1S);
//                                
//                                PnlWeb.add(CbxGF);                                
//                                PnlWeb.add(CbxErsteOffen);
//                                PnlWeb.add(CbxKeinBez);
                                                               
//                                g.addLabel4(PnlWeb,"Web");
//                                PnlWeb.add(new JLabel());
//                                PnlWeb.add(new JLabel());
                                
                                PnlWeb.add(CbxWeb);//CbxSubFom);                             
                                PnlWeb.add(CbxTEdit);                              
                                PnlWeb.add(CbxSubFom);
                            
                                PnlWeb.add(CbxSystem);                                
                                PnlWeb.add(CbxDialogEdit);                               
                                PnlWeb.add(CbxLokZR);
                                
                                PnlMet.add(CbxJVecAlle);
                                PnlMet.add(CbxKeinSyncVec);
                                PnlMet.add(CbxKeinRefresh);
                            	
                                PnlMet.add(CbxDelZw);                                
                                
                                PnlWeb.add(CbxSuche2);
                                PnlWeb.add(CbxKeinBez);
                                PnlWeb.add(CbxQryAlle);
                                
                                PnlWeb.add(CbxForStatus);
                                PnlWeb.add(CbxSbRefresh);
                                PnlWeb.add(CbxModFarbe);
                                
                                PnlWeb.add(CbxPersSperre);
                                PnlWeb.add(CbxZR_Wahl);

                                PnlWeb.add(CbxRefreshNS);
                                PnlWeb.add(CbxMulti);
                                
//                                g.addLabel4(PnlInfo,"ohne_Funktion");
//                                PnlInfo.add(new JLabel());                                
//                                PnlInfo.add(new JLabel());
                                                               
						         //PnlWeb.add(new JLabel());
						         PnlInfo.add(new JLabel("Abfrage="+A.iAbfrage));
						         PnlInfo.add(new JLabel("Begriff="+A.iBegriff));						         
                            }
                          }
//                          if (!A.isHS() || g.Spezial())
//                            g.addLabel4(PnlCenter,"Funktion");
                          if (g.Def())
                          {
                              PnlMet.add(CbxKeinMandant);
                              PnlWeb.add(CbxNoSB);
                          }
//                          JLabel Lbl=g.Def() ?  getLabelR("GKK_Ab")//new JLabel(g.Def() ? g.getShow("GKK_Ab"):"");
//                          Lbl.set
//                          PnlCenter.add(g.Def() ? g.getLabelR("GKK_Ab"):new JLabel());
//                          PnlCenter.add(g.Def()?EdtGKKAb:new JLabel());
                          if (g.Spezial() && !A.isHS())
                          {
                            PnlDar.add(CbxVorletzteEbene);
                            PnlDar.add(CbxKeineGesamtsumme);
                            PnlDar.add(Cbx0Werte);
                          }
                              if (A.isHS())
                              {
                                if (g.Spezial())
                                {
                                  /*PnlCenter.add(CbxMengen);
                                  PnlCenter.add(CbxExpo);
                                  PnlCenter.add(CbxDruckbar);*/
                                  PnlCenter.add(CbxKeinAustritt);
                                  PnlCenter.add(CbxUmsortieren);
                                  PnlCenter.add(CbxSubrollen);
                                  PnlCenter.add(CbxWeiterfuehren);
                                  PnlCenter.add(CbxKeinZR);
                                  PnlCenter.add(CbxKeinSZR);
                                }
                              }
                              else
                              {
                                if (g.Spezial())
                                  PnlMet.add(CbxKeinStamm);
                                if (g.Def())
                                  PnlMet.add(CbxFremdStamm);
//                                else if (g.Spezial())
//                                  PnlCenter.add(new JLabel());
                                PnlMet.add(CbxMengen);
                                if (g.Geloeschte())
                                    PnlMet.add(CbxEntfernte);
                                if (g.Spezial())
                                {
                                  PnlMet.add(CbxKeinZR);
                                  PnlMet.add(CbxKeinSZR);
                                }
                                if (g.Def())
                                	PnlMet.add(CbxAbfJahr);                              
//                                else if (g.Spezial())
//                                  PnlCenter.add(new JLabel());

                                PnlMet.add(CbxKeinAustritt);
                                if (g.Spezial())
                                	PnlMet.add(CbxSumme);
//                                else
//                                  PnlCenter.add(new JLabel());
                                if (g.Spezial())
                                	PnlMet.add(CbxDistinct);

                                if (g.Def())
                                {
                                	CbxBewVoll.setEnabled(A.iBew>0 && (g.getBewBits(A.iBew)&Global.cstBewVoll)==0);
                                	PnlMet.add(CbxBewVoll);
                                  PnlDar.add(CbxFeiertage);

                                  PnlMet.add(CbxKeinStt);
                                  PnlMet.add(CbxStammstichtag); 
                                  PnlMet.add(CbxKeineEinheit);
                                  PnlMet.add(CbxSQL2);
                                  PnlCenter.add(CbxSubrollen);
                                                                  
                                  //PnlCenter.add(CbxWiePeriode);
                                  //CbxStammstichtag.setEnabled(false);
                                  //CbxWiePeriode.setEnabled(false);
                                }
                                if (g.Spezial())
                                  PnlCenter.add(CbxView);
                                PnlDar.add(CbxKeinTitel);
                                PnlCenter.add(CbxAnhaengen);
                                if (g.Spezial())
                                  PnlDar.add(CbxUmsortieren);
                                if (g.Def())
                                {
                                  PnlCenter.add(CbxModellDlg);
                                  g.addLabel4(PnlCenter, "Tabelle");
                                  PnlCenter.add(new JLabel());
                                  PnlCenter.add(new JLabel());

                                  PnlCenter.add(CbxKeinNeu);
                                  PnlCenter.add(CbxKeinDel);
                                  PnlCenter.add(CbxKeinSave);
                                }
                              }
			}
                        else
                        {
                          PnlCenter.add(CbxKeinStamm);
                          PnlCenter.add(CbxKeinZR);
                          PnlCenter.add(CbxKeinSZR);
                        }
                        if (g.Spezial())
                        {
                          g.addLabel4(PnlCenter, "Berechtigung");
                          PnlCenter.add(new JLabel());
                          PnlCenter.add(new JLabel());
                          PnlCenter.add(CbxPersoenlich);
                          g.addLabel2(PnlCenter, "Benutzergruppe");
                          PnlCenter.add(CboBenutzergruppe);

                          PnlCenter.add(CbxPriviligiert);
                          if (g.Def() && !A.isHS())
                            PnlCenter.add(CbxFilter);
                          else
                            PnlCenter.add(new JLabel());
                          PnlCenter.add(new JLabel());
                        }
                        if (g.Def())
                        {
                          PnlCenter.add(CbxNoChange);
                          PnlCenter.add(CbxJeder);
                          PnlInfo.add(new JLabel(A.iBegriff>0 ? SQL.getString(g,"select ein from logging l join begriff b on l.aic_logging=b.aic_logging where aic_begriff="+A.iBegriff):""));
                          PnlInfo.add(CbxKeinTest);//CbxKeinTest; CbxLand);                         
                          PnlInfo.add(CbxTod);
                          // PnlInfo.add(new JLabel("Begriff="+A.iBegriff));
                          PnlInfo.add(new JLabel(A.iBegriff>0 ? SQL.getString(g,"select importzeit from begriff where aic_begriff="+A.iBegriff):""));
                          //PnlCenter.add(CbxNotChange);
                          
//                          PnlCenter.add(new JLabel());
//                          PnlCenter.add(new JLabel());
//                          PnlCenter.add(new JLabel());

                          PnlInfo.add(CbxHS);
                          //CbxHS.setEnabled(false);
                          //JCheckBox CbxBer=getCheckboxM("Berechtigung",bRechte || Abfrage.is(iVorher,Abfrage.cstBerechtigung));
                          //CbxBer.setEnabled(false);
                          PnlInfo.add(CbxBer);
                          JCheckBox CbxHA=getCheckboxM("horiz. Auflösung",Abfrage.is(iVorher,Abfrage.cstGruppe));
                          CbxHA.setEnabled(false);
                          PnlInfo.add(CbxHA);
                        }
			PnlOben.add("West",PnlLbl);
			PnlOben.add("Center",PnlEdt);
		Dlg.getContentPane().add("North",PnlOben);
		//if (g.Def())
		{
			JTabbedPane PnlTab=new JTabbedPane();
			PnlTab.add(g.getShow("Verwendung"),PnlCenter);
			if (A.bSpalten)
			{
			  PnlTab.add(g.getShow("Methode"),PnlMet);
			  PnlTab.add(g.getShow("Darstellung"),PnlDar);
			}
			if (g.Def())
			{
			  if (A.bSpalten)
			    PnlTab.add(g.getShow("Web"),PnlWeb);
			  PnlTab.add(g.getShow("Info"),PnlInfo);
        if (MemAllgA==null)
          MemAllgA = new AUEkitCore(g,Dlg,7);
        if (MemoAllg==null)
        {
          MemoAllg=new AUEkitCore(g,thisJFrame(),7);
          MemoAllg.setMaxLength(4000);
          MemoAllg.setText(SQL.getString(g, "select memo from daten_memo where aic_tabellenname="+Global.iTabBegriff+" and aic_sprache=1 and aic_fremd="+getBegriff()));
        }
        MemAllgA.setMaxLength(4000);
        MemAllgA.setText(MemoAllg.getValue());
        PnlTab.add(g.getShow("AllgemeinA"),MemAllgA);
			}
			Dlg.getContentPane().add("Center",PnlTab);
		}
//		else
//			Dlg.getContentPane().add("Center",PnlCenter);

		JPanel PnlUnten = new JPanel(new GridLayout());
			JButton BtnOk = g.getButton("Ok");
                        BtnOk.setEnabled(bEdit || bNeu);
			JButton BtnAbbruch = g.getButton("Abbruch");
                        Dlg.getRootPane().setDefaultButton(BtnOk);
			PnlUnten.add(BtnOk);
			PnlUnten.add(BtnAbbruch);
			//if (bNeu)
				BtnOk.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						long l=(CbxKeinZR.isSelected()?Abfrage.cstKeinZeitraum:0)+(CbxKeinStamm.isSelected()?Abfrage.cstKeinStamm:0)+(CbxSumme.isSelected()?Abfrage.cstSumme:0)+
							(CbxKeinRefresh.isSelected()?Abfrage.cstKeinRefresh:0)+(CbxNurModell.isSelected()?Abfrage.cstNurModell:0)+(CbxExpo.isSelected()?Abfrage.cstExportierbar:0)+
							(CbxDruckbar.isSelected()?Abfrage.cstDruckbar:0)+(CbxMengen.isSelected()?Abfrage.cstMengen:0)+(CbxSubrollen.isSelected()?Abfrage.cstSubrollen:0)+
							(CbxKeinNeu.isSelected()?Abfrage.cstKeinNeu:0)+(CbxKeinSave.isSelected()?Abfrage.cstKeinSave:0)+(CbxNichtUpdaten.isSelected()?Abfrage.cstNichtUpdaten:0)+
							(CbxImpo.isSelected()?Abfrage.cstImportierbar:0)+(CbxAuswertung.isSelected()?Abfrage.cstAuswertung:0)+(CbxVersionsup.isSelected()?Abfrage.cstVersionsup:0)+
							(CbxKeinDel.isSelected()?Abfrage.cstKeinDel:0)+(CbxAnhaengen.isSelected()?Abfrage.cstAnhaengen:0)+(CbxKeinTitel.isSelected()?Abfrage.cstKeinTitel:0)+
							(CbxKeinStt.isSelected()?Abfrage.cstKeinStt:0)+(CbxFirst.isSelected()?Abfrage.cstFirst:0)+(CbxKeinSZR.isSelected()?Abfrage.cstKeinStammZeitraum:0)+
                                                        (RadPlanungD.isSelected()?Abfrage.cstPlanungD:RadPlanungF.isSelected()?Abfrage.cstPlanungF:RadPlanungS.isSelected()?Abfrage.cstPlanungS:0)+
							(CbxEntfernte.isSelected()?Abfrage.cstEntfernte:0)+(CbxPersoenlich.isSelected()?Abfrage.cstPersoenlich:0)+
							(CbxStempelimport.isSelected()?Abfrage.cstStempelimport:0)+(CbxView.isSelected()?Abfrage.cstView:0)+(CbxBewVoll.isSelected()?Abfrage.cstBewVoll:0)+
							(CbxFilter.isSelected()?Abfrage.cstFilter:0)+(CbxZR_Wahl.isSelected()?Abfrage.cstZR_Wahl:0)+(CbxSynchron.isSelected()?Abfrage.cstSynchron:0)+
							(CbxChanges.isSelected()?Abfrage.cstChanges:0)+(CbxVerteiler.isSelected()?Abfrage.cstVerteiler:0)+
							(CbxNoChange.isSelected()?Abfrage.cstNoChange:0)+(CbxFeiertage.isSelected()?Abfrage.cstFeiertage:0)+(CbxFremdStamm.isSelected()?Abfrage.cstFremdStamm:0)+
                                                        (CbxDistinct.isSelected()?Abfrage.cstDistinct:0)+(CbxVerdichten.isSelected()?Abfrage.cstVerdichten:0)+(CbxHS.isSelected()?Abfrage.cstHS:0)+(CbxBer.isSelected()?Abfrage.cstBerechtigung:0)+
							(CbxEinzeldaten.isSelected()?Abfrage.cstEinzeldaten:0)+(CbxTTO.isSelected()?Abfrage.cstTTO:0)+(CbxCompress.isSelected()?Abfrage.cstCompress:0)+
                                                        (CbxKeineGesamtsumme.isSelected()?Abfrage.cstKeineGesamtsumme:0)+(CbxPriviligiert.isSelected()?Abfrage.cstPriviligiert:0)+
                                                        (CbxVorletzteEbene.isSelected()?Abfrage.cstVorletzteEbene:0)+(Cbx0Werte.isSelected()?Abfrage.cst0Werte:0)+(CbxLeer.isSelected()?Abfrage.cstLeer:0)+
                                                        (CbxKeinAustritt.isSelected()?Abfrage.cstKeinAustritt:0)+(CbxStammstichtag.isSelected()?Abfrage.cstStammstichtag:0)+//(CbxWiePeriode.isSelected()?Abfrage.cstWiePeriode:0)+
                                                        (CbxNoSort.isSelected()?Abfrage.cstNoSort:0)+(CbxKeinTest.isSelected()?Abfrage.cstKeinTest:0)+(CbxGleiche.isSelected()?Abfrage.cstGleiche:0)+(CbxFremdJoker.isSelected()?Abfrage.cstFremdJoker:0)+
                                                        (CbxUmsortieren.isSelected()?Abfrage.cstUmsortieren:0)+(CbxAusblenden.isSelected()?Abfrage.cstAusblenden:0)+(CbxNachSave.isSelected()?Abfrage.cstNachSave:0)+
                                                        (CbxWeiterfuehren.isSelected()?Abfrage.cstWeiterfuehren:0)+/*(CbxANR.isSelected()?Abfrage.cstANR:0)+*/(CbxLDATE.isSelected()?Abfrage.cstLDATE:0);
						long l2=/*(CbxJavaFX.isSelected()?Abfrage.cstAbfFX:0)+*/(CbxRefreshNS.isSelected()? Abfrage.cstRefreshNS:0)+(CbxKeinBez.isSelected()?Abfrage.cstKeinBez:0)+(CbxCompress2.isSelected()?Abfrage.cstCompress2:0)+(CbxAPI.isSelected()?Abfrage.cstAbfAPI:0)+
								(CbxMulti.isSelected()?Abfrage.cstAbfMulti:0)+//(CbxErsteOffen.isSelected()?Abfrage.cstErsteOffen:0)+
								(CbxNoSB.isSelected()?Abfrage.cstNoSB:0)+(CbxSubFom.isSelected()?Abfrage.cstSubFom:0)+(CbxSuche2.isSelected()?Abfrage.cstSuche2:0)+(CbxKeinMandant.isSelected()?Abfrage.cstKeinMandant:0)+
								(CbxTEdit.isSelected()?Abfrage.cstTEdit:0)/*+(CbxWeb.isSelected()?Abfrage.cstAbfWeb:0)*/+(CbxSystem.isSelected()?Abfrage.cstAbfSystem:0)+//(CbxHochTitel.isSelected()?Abfrage.cstHochTitel:0)+
								(CbxDialogEdit.isSelected()?Abfrage.cstDialogEdit:0)+(CbxLokZR.isSelected()?Abfrage.cstLokZR:0)+(CbxJVecAlle.isSelected()?Abfrage.cstJVecAlle:0)+(CbxKeinSyncVec.isSelected()?Abfrage.cstkeinSyncVec:0)+
								(CbxAbfJahr.isSelected()?Abfrage.cstAbfJahr:0)+(CbxDelZw.isSelected()?Abfrage.cstDelZw:0)+(CbxModellDlg.isSelected()?Abfrage.cstModellDlg:0)+(CbxQryAlle.isSelected()?Abfrage.cstQryAlle:0)+
								(CbxForStatus.isSelected()?Abfrage.cstForStatus:0)+(CbxSbRefresh.isSelected()?Abfrage.cstSbRefresh:0)+(CbxModFarbe.isSelected()?Abfrage.cstModFarbe:0)+(CbxPersSperre.isSelected()?Abfrage.cstPersSperre:0)+
								(CbxKeineEinheit.isSelected()?Abfrage.cstKeineME:0)+(CbxSQL2.isSelected()?Abfrage.cstSQL2:0);
								//+(CbxWebFom.isSelected()?Abfrage.cstWebFom:0);
            if (CbxTod.Modified())
            {
						  A.bTod=CbxTod.isSelected();
              sSave+=" Tod "+(A.bTod ? "gesetzt":"entfernt");
            }
            if (CbxLDATE.Modified())
              sSave+=" LDATE "+(CbxLDATE.isSelected() ? "gesetzt":"entfernt");
            if (CbxJeder.Modified())
              sSave+=" Jeder "+(CbxJeder.isSelected() ? "gesetzt":"entfernt");
            if (CbxDistinct.Modified())
              sSave+=" Distinct "+(CbxDistinct.isSelected() ? "gesetzt":"entfernt");
            if (CbxMengen.Modified())
              sSave+=" Mengen "+(CbxMengen.isSelected() ? "gesetzt":"entfernt");
            if (CbxCompress.Modified())
              sSave+=" Compress "+(CbxCompress.isSelected() ? "gesetzt":"entfernt");
            if (CbxCompress2.Modified())
              sSave+=" Compress2 "+(CbxCompress2.isSelected() ? "gesetzt":"entfernt");
            if (CbxMulti.Modified())
              sSave+=" Multi "+(CbxMulti.isSelected() ? "gesetzt":"entfernt");
            if (CbxKeinStamm.Modified())
              sSave+=" keinStamm "+(CbxKeinStamm.isSelected() ? "gesetzt":"entfernt");
            if (CbxTTO.Modified())
              sSave+=" TTO "+(CbxTTO.isSelected() ? "gesetzt":"entfernt");

            if (MemAllgA.Modified())
            {
              MemoAllg.setText(MemAllgA.getValue());
              g.setMemo(MemAllgA.getValue(), "", Global.iTabBegriff, getBegriff(), 1);
            }
						boolean bWeb=CbxWeb.isSelected();
						boolean bJeder=CbxJeder.isSelected();
						int iStt=CboStammtyp.getSelectedAIC();
                                                A.iStt=iStt;
                                                iRolle=CboRolle2.getSelectedAIC();
						int iTabTyp=CboTabellentyp.getSelectedAIC();
						int iModell=CboModell.getSelectedAIC();
                                                int iModell2=CboModell2.getSelectedAIC();
						int iSchrift=CboSchrift.getSelectedAIC();
						int iBG=CboBenutzergruppe.getSelectedAIC();
						A.iAbfD=CboAbfDetail.getSelectedAIC();
						A.iFomD=CboFomDetail.getSelectedAIC();
						A.iAbfF=CboAbfFilter.getSelectedAIC();
						A.iAbfE=CboAbfErw.getSelectedAIC();
						A.iWebStamm=CboWebStamm.getStamm();
						g.fixInfoT("Detail="+A.iAbfD+"/"+A.iFomD+", Filter="+A.iAbfF+", Erweitert="+A.iAbfE);
						if (EdtKennzeichen != null && EdtKennzeichen.Modified() && A.iBegriff>0)
							g.exec("update begriff set kennzeichen='"+EdtKennzeichen.getText()+"' where aic_begriff="+A.iBegriff);
						//if (CboAbfrage.getSelectedAIC()==0)


						//CboAbfrage.addItem(EdtBezeichnung.getText(),iAic,EdtKennung.getText());
						if (bNeu)
						{
							iNeu--;
							A.clear(iNeu);
							setSpalten();
							Gid.folderChanged(Gid.getRootNode());
							TabAbfragen.newLine();
							TabAbfragen.setInhalt("Aic",iNeu);
							//A.iAbfrage=iNeu;
							bCboLaden=false;
							CboAbfrage.addItem(Static.beiLeer(g.Def() ? EdtDefBezeichnung.getText():Static.beiLeer(EdtBezeichnung.getText(),EdtDefBezeichnung.getText()),"Abf"+iNeu),iNeu,EdtKennungA.getText(),0);
							CboAbfrage.setSelectedAIC(iNeu);
							bCboLaden=true;
						}
						else
						{
							if(!rbSave)
							{
								bCboLaden=false;
								CboAbfrage.setItem(A.iAbfrage,g.Def() ? EdtDefBezeichnung.getText():Static.beiLeer(EdtBezeichnung.getText(),EdtDefBezeichnung.getText()),EdtKennungA.getText(),0);
								bCboLaden=true;
							}
							if (A.bSpalten)
							{
								boolean bGruppe=false;
								boolean bCalc=false;
								for (A.TabSp.moveFirst();!A.TabSp.eof();A.TabSp.moveNext())
								{
									if (!A.TabSp.isNull("Gruppe"))
										bGruppe=true;
									if (!A.TabSp.isNull("Calc"))
										bCalc=true;
								}
								l+=(bGruppe?Abfrage.cstGruppe:0)+(bCalc?Abfrage.cstCalc:0);
								g.progInfo("Bits="+Long.toHexString(l));
							}
						}
						TabAbfragen.setInhalt("DefBez",EdtDefBezeichnung.getText());
                        TabAbfragen.setInhalt("Bezeichnung",EdtBezeichnung.getText());
						TabAbfragen.setInhalt("Kennung",EdtKennungA.getText());
						TabAbfragen.setInhalt("Aic_Modell",iModell);
                        TabAbfragen.setInhalt("Mod_Aic_Modell",iModell2);
						TabAbfragen.setInhalt("Aic_Schrift",iSchrift);
						TabAbfragen.setInhalt("bits",new Long(l));
						TabAbfragen.setInhalt("Abits2",new Long(l2));
						A.iAnzahl=EdtSpAnzahl.intValue()+(EdtPagAnzahl.intValue()<0?4001:EdtPagAnzahl.intValue())*16;
						//g.fixtestError("Anzahl="+A.iAnzahl);
						// A.bTod=bTod;
						A.bWeb=bWeb;
						A.bJeder=bJeder;
						A.iBits=l;
						A.lBits2=l2;
						A.iTop=SboAutoRefresh.getIntValue();
						TabAbfragen.setInhalt("Autorefresh",A.iTop);
						TabAbfragen.setInhalt("Anzahl",A.iAnzahl);
						TabAbfragen.setInhalt("Aic_Benutzergruppe",iBG);
						TabAbfragen.setInhalt("aic_code",iTabTyp);
						TabAbfragen.setInhalt("Prog",CboProg.getSelectedAIC());
						TabAbfragen.setInhalt("aic_stammtyp",iStt);
						//TabAbfragen.setInhalt("Spalten",iSpalten);
						if (rbSave)
							Save();

						//CboAbfrage.setSelectedAIC(0);
						g.progInfo("Ok:"+bNeu+"/"+rbSave+"/"+A.iAbfrage+"/"+CboAbfrage.getSelectedAIC()+"/"+TabAbfragen.getI("Aic"));
						bEdit=true;
            disposeDlg(Dlg);
					}
				});
			/*else
				BtnOk.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						int iStt=CboStammtyp.getSelectedAIC();
						int iTabTyp=CboTabellentyp.getSelectedAIC();
						int i=(CbxKeinZR.isSelected()?Abfrage.cstKeinZeitraum:0)+(CbxKeinStamm.isSelected()?Abfrage.cstKeinStamm:0)+(CbxSumme.isSelected()?Abfrage.cstSumme:0)+
							(CbxKeinRefresh.isSelected()?Abfrage.cstKeinRefresh:0)+(CbxNurModell.isSelected()?Abfrage.cstNurModell:0)+(CbxExpo.isSelected()?Abfrage.cstExportierbar:0)+
							(CbxDruckbar.isSelected()?Abfrage.cstDruckbar:0)+(CbxMengen.isSelected()?Abfrage.cstMengen:0)+(CbxTitelzeile.isSelected()?Abfrage.cstTitelzeile:0)+
							(CbxKeinNeu.isSelected()?Abfrage.cstKeinNeu:0)+(CbxKeinSave.isSelected()?Abfrage.cstKeinSave:0)+(CbxKeineLeiste.isSelected()?Abfrage.cstKeineLeiste:0)+
							(CbxImpo.isSelected()?Abfrage.cstImportierbar:0)+(CbxAuswertung.isSelected()?Abfrage.cstAuswertung:0)+(CbxVersionsup.isSelected()?Abfrage.cstVersionsup:0)+
							(CbxKeinDel.isSelected()?Abfrage.cstKeinDel:0)+(CbxAnhaengen.isSelected()?Abfrage.cstAnhaengen:0)+(CbxKeinTitel.isSelected()?Abfrage.cstKeinTitel:0)+
							(CbxKeinStt.isSelected()?Abfrage.cstKeinStt:0)+(CbxFirst.isSelected()?Abfrage.cstFirst:0)+(CbxPlanung.isSelected()?Abfrage.cstPlanung:0)+
							(CbxPlanungF.isSelected()?Abfrage.cstPlanungF:0)+(CbxKeinSZR.isSelected()?Abfrage.cstKeinStammZeitraum:0)+(CbxEntfernte.isSelected()?Abfrage.cstEntfernte:0);

						int iModell = CboModell.getSelectedAIC();
						int iBG=CboBenutzergruppe.getSelectedAIC();

						A.iTop=SboAutoRefresh.getIntValue();
						bCboLaden=false;
						CboAbfrage.setItem(EdtBezeichnung.getText(),A.iAbfrage,EdtKennung.getText(),0);
						bCboLaden=true;
						TabAbfragen.setInhalt("Bezeichnung",EdtBezeichnung.getText());
						TabAbfragen.setInhalt("Kennung",EdtKennung.getText());
						TabAbfragen.setInhalt("Aic_Modell",iModell);
						TabAbfragen.setInhalt("bits",i);
						A.iBits=i;
						TabAbfragen.setInhalt("Autorefresh",new Integer(A.iTop));
						TabAbfragen.setInhalt("Aic_Benutzergruppe",iBG);
						TabAbfragen.setInhalt("aic_code",iTabTyp);
						TabAbfragen.setInhalt("aic_stammtyp",iStt);
						if (rbSave)
							Save();
						Dlg.dispose();
					}
				});*/
			BtnAbbruch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					g.progInfo("Abbruch:"+bNeu+"/"+rbSave+"/"+A.iAbfrage+"/"+CboAbfrage.getSelectedAIC()+"/"+TabAbfragen.getI("Aic"));
					disposeDlg(Dlg);
				}
			});
		Dlg.getContentPane().add("South",PnlUnten);
		//Dlg.setSize(230,250);
                if (g.Def() && !A.isHS())
                  Dlg.setSize(500,792);
                else
                  Dlg.pack();
		g.progInfo("Abfragedef-Größe:"+Dlg.getHeight()+"x"+Dlg.getWidth());
		Static.centerComponent(Dlg,thisFrame);
		//Dlg.setBounds(getLocationOnScreen().x+50,getLocationOnScreen().y+50,230,130);
		Dlg.addWindowListener(new WindowListener()
		{
			public void windowClosed(WindowEvent e)
			{
			}
			public void windowOpened(WindowEvent e)
			{
				if (g.Def())
					EdtDefBezeichnung.requestFocus();
				else
					EdtBezeichnung.requestFocus();
			}
			public void windowClosing(WindowEvent e)
			{
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
		Dlg.setVisible(true);
		EnableButtons();
	}
	
	private void TabellentypNeu(ActionListener AL)
    {
      if (DlgProg==null)
      {
    	DlgProg = new JDialog(Dlg, "neuer Tabellentyp", true);
        DlgProg.getContentPane().setLayout(new BorderLayout(2, 2));
        JPanel Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
        g.addLabel2(Pnl1, "Bezeichnung");
        g.addLabel2(Pnl1, "Kennung");
        DlgProg.getContentPane().add("West", Pnl1);
        Pnl1 = new JPanel(new GridLayout(0, 1, 2, 2));
        Pnl1.add(TxtBezP);
        Pnl1.add(TxtKenP);
        DlgProg.getContentPane().add("Center", Pnl1);
        JPanel Pnl = new JPanel(new GridLayout(1, 0, 2, 2));
        JButton BtnOk = g.getButton("Ok");
        JButton BtnAbbruch = g.getButton("Abbruch");
        g.BtnAdd(BtnOk, "OkP", AL);
        g.BtnAdd(BtnAbbruch, "AbbruchP", AL);
        Pnl.add(BtnOk);
        Pnl.add(BtnAbbruch);
        DlgProg.getContentPane().add("South", Pnl);
        DlgProg.pack();
      }
      Static.centerComponent(DlgProg,Dlg);
      TxtBezP.setText("");
      TxtKenP.setText("");
      DlgProg.setVisible(true);
    }

private void ProgDel()
    {
      int iAic=CboTabellentyp.getSelectedAIC();
      String sBez=CboTabellentyp.getSelectedBezeichnung();
      //g.fixInfo("Programm="+iAic+"/"+CboProgramm.getSelectedBezeichnung());
      Tabellenspeicher Tab=new Tabellenspeicher(g,"select defbezeichnung,kennung,aic_begriff from begriff where aic_code=?",""+iAic,true);
      if (Tab.isEmpty())
      {
        if (new Message(Message.YES_NO_OPTION,Dlg,g,10).showDialog("Loeschen", new String[] {"(" + sBez + ")"}) == Message.YES_OPTION)
        {
          g.exec("delete from code where aic_code="+iAic);
          CboTabellentyp.fillBegriffTable(g.TabBegriffgruppen.getAic("Tabellentyp"),true,true);
        }
      }
      else
        Tab.showGrid(sBez+" schon verwendet",Dlg);
    }

private void ProgSave()
    {
      SQL Qry=new SQL(g);
      Qry.add("Kennung",TxtKenP.getText());
      Qry.add("AIC_Begriffgruppe",g.TabBegriffgruppen.getAic("Tabellentyp"));
      int iAic=Qry.insert("Code",true);
      g.setBezeichnung("",TxtBezP.getText(),g.TabTabellenname.getAic("CODE"),iAic,1);
      Qry.free();
      CboTabellentyp.addItem(TxtBezP.getText(),iAic,TxtKenP.getText());
      DlgProg.setVisible(false);
    }

	// --------------------------------------------------------------------------------
	// -------------   * * * * *   E D I T   Bedingung * * * * *   --------------------
	// --------------------------------------------------------------------------------

	private int getEigenschaft()
	{
		if (iArt==Edit)
		{
			Vector Vec=(Vector)((Vector)Gid.getSelectedNode().getUserData()).elementAt(1);
			return Sort.geti(Vec,Vec.size()-1);// Vec.elementAt(Vec.size()-1);
		}
		else
			return ((Vector<Integer>)GidEigenschaft.getSelectedNode().getUserData()).elementAt(0);
	}
	
	private int getEig()
	{
		if (A.TabSp.posInhalt("Nummer",Gid.getSelectedNode().getUserData()))
			return Sort.geti(A.TabSp.getInhalt("Vec"), ((Vector)A.TabSp.getInhalt("Vec")).size()-1);
		else
			return 0;
	}

	private void FillCboVergleich(String sDatentyp)
	{
		g.testInfo("FillCboVergleich:"+sDatentyp);
		CboVergleich.Clear();

		boolean bVergleich = sDatentyp.equals("Datum") || sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Stichtag") ||
			sDatentyp.equals("Double") || sDatentyp.equals("Integer") || sDatentyp.equals("Protokoll") || sDatentyp.equals("Mass") || sDatentyp.equals("Waehrung")|| sDatentyp.equals("Mehrfach") ||
			sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("Zeit") || sDatentyp.equals("BewDauer") || sDatentyp.equals("BewVon_Bis") ||
			sDatentyp.equals("BewMass") || sDatentyp.equals("BewWaehrung") || sDatentyp.equals("BewZahl") || sDatentyp.equals("Timestamp") || sDatentyp.equals("entfernt");
		boolean bText = sDatentyp.startsWith("String") || sDatentyp.equals("WWW") || sDatentyp.equals("Bezeichnung") || sDatentyp.equals("CalcBezeichnung") || sDatentyp.equals("Kennung") ||
			sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("E-Mail") || sDatentyp.equals("Pfad") || sDatentyp.equals("Filename");
		boolean bCbo = sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe") || sDatentyp.equals("SysAic") || sDatentyp.equals("Hierarchie") || sDatentyp.equals("BewHierarchie")
			|| sDatentyp.equals("Benutzer") || sDatentyp.equals("LoeschBenutzer") || sDatentyp.endsWith("Boolean") || sDatentyp.endsWith("Bool3")
                        || sDatentyp.equals("BewBew") || sDatentyp.equals("Anlage") || sDatentyp.equals("Mandant") || sDatentyp.equals("Firma")
                        || sDatentyp.equals("Aufgabe") || sDatentyp.equals("Status") || sDatentyp.equals("Land") || sDatentyp.equals("Formular") || sDatentyp.equals("Abfrage") || sDatentyp.equals("Modell")
                        || sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular");
		int iPos=g.TabBegriffe.getPos("Gruppe",iBG_Vergleich);
		while (iPos>=0 && g.TabBegriffe.getI(iPos,"Gruppe")==iBG_Vergleich)
		{
			String s = g.TabBegriffe.getS(iPos,"Kennung");
			if (s.equals("is null") || s.equals("is not null") ||
				bVergleich && (s.equals("=") || s.equals("<") || s.equals(">") || s.equals("<=") || s.equals(">=") || s.equals("<>") || s.equals("between")) ||
				bText && (s.equals("like") || s.equals("not like") || s.equals("soundex")) || bCbo && (s.equals("=") || s.equals("<>")))
					CboVergleich.addItem(g.TabBegriffe.getS(iPos,"Bezeichnung"), g.TabBegriffe.getI(iPos,"Aic"),s );
			iPos++;
		}
		CboVergleich.setKein(false);
	}

	private void FillCboPlatzhalter(int iPlatzArt)
	{
		//final int iPlatzOld;
		if (iPlatzArt != iPlatzOld)
		{
			g.fixtestInfo("FillCboPlatzhalter:"+iPlatzArt);
			CboPlatzhalter.Clear();
                        CboPlatzhalter.setKein(true);
			int iPos=g.TabCodes.getPos("Gruppe",iBG_Platzhalter);
			while (iPos>=0 && g.TabCodes.getI(iPos,"Gruppe")==iBG_Platzhalter)
			{
				String s = g.TabCodes.getS(iPos,"Kennung");
				if (iPlatzArt==1 == (s.equals("*Joker*") && iPlatzArt!=6 ||s.equals("*JokerStt*")||s.equals("*JokerVec*")||s.equals("*UseVec*")||s.equals("*Reserve1*")||s.equals("*meinStamm*")||s.equals("*meine*")
				    ||s.equals("*Joker von*")||s.equals("*Qry*")||s.equals("*Qry von*")||s.equals("*Checkbox*")||s.equals("*Radiobutton*"))
                                    && iPlatzArt==3 ==  (s.equals("*StringJoker*") || s.equals("*Reserve2*")) && iPlatzArt==4 ==  s.equals("*ich*") && iPlatzArt==5 == s.equals("*JokerBew*")
                                    && iPlatzArt==6 == s.equals("*Joker*"))
                                {
                                  g.fixtestInfo(" dazu: "+s);
                                  CboPlatzhalter.addItem(g.TabCodes.getS(iPos, "Bezeichnung"), g.TabCodes.getI(iPos, "Aic"), s);
                                }
				iPos++;
			}
			iPlatzOld = iPlatzArt;
		}
	}

	private void checkFirstBedingungEdit()
	{
		if (bFirstBedEdit)
		{
			g.testInfo("checkFirstBedingungEdit");

			CboVergleich = new ComboSort(g);
			CboVergleich.addItemListener(new ItemListener ()
			{
				public void itemStateChanged(ItemEvent ev)
				{
					if(ev.getStateChange() == ItemEvent.SELECTED)
						CboVergleichChanged();
				}
			});

			CboPlatzhalter = new ComboSort(g);
			CboPlatzhalter.addItemListener(new ItemListener ()
			{
				public void itemStateChanged(ItemEvent ev)
				{
					if(ev.getStateChange() == ItemEvent.SELECTED)
						CboVergleichChanged();
				}
			});

			CboPlatzhalter2 = new ComboSort(g);
			CboPlatzhalter2.fillBegriffTable("Platzhalter",true);
			CboPlatzhalter2.addItemListener(new ItemListener ()
			{
				public void itemStateChanged(ItemEvent ev)
				{
					if(ev.getStateChange() == ItemEvent.SELECTED)
						CboVergleichChanged();
				}
			});

			bFirstBedEdit=false;
		}
	}

	private void NeueBedingung(final boolean bNeu)
	{
		checkFirstBedingungEdit();
//		Vector VecUser = (Vector)GidEigenschaft.getSelectedNode().getUserData();
//		g.fixtestInfo("NeueBedingung:"+VecUser);
		int iPosE=g.TabEigenschaften.getPos("aic",getEigenschaft());
                if (iPosE<0)
                  return;
		String sDatentyp = g.TabEigenschaften.getS(iPosE,"datentyp");
		sDatentyp = sDatentyp.equals("BewMass")?"Mass":sDatentyp.equals("BewWaehrung")?"Waehrung":sDatentyp.equals("BewStamm")?"Gruppe":
			sDatentyp.equals("BewZahl")?"Double":sDatentyp;
		//g.testInfo("sDatentyp="+sDatentyp);
		Dlg = new JDialog((Frame)thisFrame,iArt==Edit?Abfrage.getEigenschaftBezeichnung(g,((Vector)Gid.getSelectedNode().getUserData()).elementAt(1)):Sort.gets(GidEigenschaft.getSelectedNode().getLabel()),true);
		JPanel PnlOben = new JPanel(new BorderLayout());
			JPanel PnlText = new JPanel(new GridLayout(0,1));
			JPanel PnlAuswahl = new JPanel(new GridLayout(0,1,2,2));
			//PnlText.add(new JLabel(g.getBegriff("Show","Vergleich")+':'));
			g.addLabel2(PnlText,"Vergleich");
            PnlAuswahl.add(CboVergleich);
            if (EdtVar==null)
            	EdtVar=new Text("",30);
            else
            	EdtVar.setText("");
            g.addLabel2(PnlText,"Variable");
            PnlAuswahl.add(EdtVar);
			//Lbl1=new JLabel(g.getBegriff("Show","Wert")+':');
			//PnlText.add(Lbl1);
            g.addLabel2(PnlText,"Wert");
            if (sDatentyp.equals("BewVon_Bis"))
              PnlText.add(new JLabel());
			//g.testInfo("iArt="+iArt);
            Vector VecAlt=iArt==Edit ? (Vector)Gid.getSelectedNode().getUserData():null;
			Object ObjAlt = iArt==Edit ? VecAlt.elementAt(3):null;
                        String sVG=iArt==Edit ? Sort.gets(Gid.getSelectedNode().getUserData(),2):"";
            int iBBits=VecAlt != null && VecAlt.size()>4 ? Sort.geti(VecAlt, 4):0;
			//g.testInfo("ObjAlt="+ObjAlt+", sDatentyp="+sDatentyp);
                        CboPlatzhalter.setSelectedAIC(0);
                        CboR2=null;
            if (CbxKen!=null) CbxKen.setSelected(false);
            if (CbxZeit!=null) CbxZeit.setSelected(false);
            if (CbxOhneJahr!=null) CbxOhneJahr.setSelected(false);
            if (RadDauer!=null) RadDauer.setSelected(true);
			if (sDatentyp.equals("Gruppe") || sDatentyp.equals("Firma") || sDatentyp.equals("BewStamm") || sDatentyp.equals("SysAic") && A.iBew==0)
			{
                CboR2 = new RolleEingabe(g,thisFrame);
				Edt1 = new ComboSort(g);
                ((ComboSort)Edt1).setPreferredSize(new java.awt.Dimension(200,20));
                Edt2 = new ComboSort(g);
                String sVGW=(String)ObjAlt;
                
                ((ComboSort)Edt2).fillDefTable("Stammtyp",false);
                int iStt2=sDatentyp.equals("SysAic") ? A.iStt:sDatentyp.equals("Firma") ? g.iSttFirma:g.TabEigenschaften.getI(iPosE,"aic_stammtyp");
                ((ComboSort)Edt2).setSelectedAIC(iStt2);
                ((ComboSort)Edt1).fillStammTable(iStt2,false);
                CboR2.fillRolle(iStt2, true);
                CboR2.setVisible(false);
				FillCboPlatzhalter(1);
				if(iArt==Edit && ObjAlt != null)
					if(sVGW.equals("*Joker*"))
						CboPlatzhalter.setSelectedKennung("*Joker*");
                    else if(sVGW.startsWith("*JokerVec"))
						CboPlatzhalter.setSelectedKennung("*JokerVec*");
                    else if(sVGW.startsWith("*UseVec"))
						CboPlatzhalter.setSelectedKennung("*UseVec*");
                    else if(sVGW.startsWith("*Reserve1"))
						CboPlatzhalter.setSelectedKennung("*Reserve1*");
					else if(sVGW.startsWith("*JokerStt"))
						CboPlatzhalter.setSelectedKennung("*JokerStt*");
					else if(sVGW.equals("*meinStamm*"))
						CboPlatzhalter.setSelectedKennung("*meinStamm*");
                    else if(sVGW.startsWith("*meine"))
						CboPlatzhalter.setSelectedKennung("*meine*");
                    else if(sVGW.equals("*Qry*"))
						CboPlatzhalter.setSelectedKennung("*Qry*");
                    else if(sVGW.equals("*Checkbox*"))
						CboPlatzhalter.setSelectedKennung("*Checkbox*");
                    else if(sVGW.equals("*Radiobutton*"))
						CboPlatzhalter.setSelectedKennung("*Radiobutton*");
                    else if(sVGW.startsWith("*Qry von"))
						CboPlatzhalter.setSelectedKennung("*Qry von*");
                    else if(sVGW.startsWith("*Joker von"))
                    {
                      CboPlatzhalter.setSelectedKennung("*Joker von*");
//                      String s=ObjAlt.toString();
                      ((ComboSort)Edt2).setSelectedKennung(sVGW.toUpperCase().substring(11,sVGW.indexOf(",")));
                    }
					else
					{
						CboPlatzhalter.setSelectedAIC(0);
                        // boolean bNull = CboVergleich.getSelectedKennung().equals("is null") || CboVergleich.getSelectedKennung().equals("is not null");
                        if (sVG.equals("is null") || sVG.equals("is not null"))
                        {
                          CboR2.setSelectedAIC(Sort.geti(ObjAlt));
                          CboR2.setVisible(true);
                        }
                        else if (isVar(sVGW)) //!=null && (sVGW.startsWith("@") || sVGW.startsWith("$")))
                        {
                        	EdtVar.setText(sVGW);
                        	((ComboSort)Edt1).setSelectedAIC(0);
                        }
                        else
                        {
                          ((ComboSort)Edt1).setSelectedAIC(Sort.geti(ObjAlt));
                          //CboR2.setVisible(false);
                        }
					}
				JPanel Pnl2=new JPanel(new BorderLayout());
				Pnl2.add("West",CboPlatzhalter);
				Pnl2.add("East",Edt1);
                JPanel PnlF=new JPanel(new FlowLayout());
                PnlF.add(Edt2);
                PnlF.add(CboR2);
//                PnlF.add(EdtVar);
                Pnl2.add("Center",PnlF);
				PnlAuswahl.add(Pnl2);
			}
			else if (sDatentyp.equals("Benutzer") || sDatentyp.equals("LoeschBenutzer") || sDatentyp.endsWith("User"))
			{
				if (CbxKen==null)
	                  CbxKen=getCheckboxM("Ken",false);
				CbxKen.setSelected((iBBits&Abfrage.KEN)>0);
				Edt1 = new ComboSort(g);
				((ComboSort)Edt1).fillCbo(new Tabellenspeicher(g,"select aic_benutzer aic,benutzer.kennung"+g.AU_Bezeichnung("benutzer")+" from benutzer"+g.join("mandant","benutzer")+" where geloescht is null and mandant.aic_mandant="+g.getMandant(),true),false);
				FillCboPlatzhalter(4);
				if(iArt==Edit && ObjAlt != null)
					if(ObjAlt.equals("*ich*"))
						CboPlatzhalter.setSelectedKennung("*ich*");
					else
					{
						CboPlatzhalter.setSelectedAIC(0);
						((ComboSort)Edt1).setSelectedAIC(new Integer((String)ObjAlt).intValue());
					}
				JPanel Pnl2=new JPanel(new BorderLayout());
				Pnl2.add("West",CboPlatzhalter);
				Pnl2.add("Center",Edt1);
				Pnl2.add("East",CbxKen);
				PnlAuswahl.add(Pnl2);
			}
			else if (sDatentyp.equals("Anlage") || sDatentyp.equals("Mandant") || sDatentyp.equals("Aufgabe") || sDatentyp.equals("Formuar") || sDatentyp.equals("Land") || sDatentyp.equals("Status"))
			{
				Edt1 = new ComboSort(g);
				if (sDatentyp.equals("Anlage") || sDatentyp.equals("Aufgabe") || sDatentyp.equals("Formuar"))
				{
					((ComboSort)Edt1).fillBegriffTable(sDatentyp,false);
					if(iArt==Edit && ObjAlt != null)
						((ComboSort)Edt1).setSelectedAIC(new Integer((String)ObjAlt).intValue());
				}
				else if (sDatentyp.equals("Mandant") || sDatentyp.equals("Land") || sDatentyp.equals("Status"))
				{
					((ComboSort)Edt1).fillDefTable(sDatentyp,true);
					if(iArt==Edit && ObjAlt != null)
	                                  ((ComboSort)Edt1).setSelectedAIC(ObjAlt.equals("*aktMandant*")?0:Sort.geti(ObjAlt));
				}
				PnlAuswahl.add(Edt1);
			}
			else if (sDatentyp.endsWith("Hierarchie"))
			{
				//Edt1 = new HierarchieEingabe(g.TabEigenschaften.getI("aic_stammtyp"),iStammtyp,g.TabEigenschaften.getI("Aic"),g);
				Edt1 = new ComboSort(g);
                                Edt2 = new ComboSort(g);
                                Edt1.setPreferredSize(new java.awt.Dimension(200,20));
                                ((ComboSort)Edt2).fillHierachieBis(g.TabEigenschaften.getI(iPosE,"aic_stammtyp"),sDatentyp.equals("Hierarchie")?iStammtyp:0,true);
                                ((ComboSort)Edt1).fillStammTable(((ComboSort)Edt2).getSelectedAIC(),true);
                                ((ComboSort)Edt2).addItemListener(new ItemListener()
                                {
                                  public void itemStateChanged(ItemEvent ev)
                                  {
                                    if (ev.getStateChange() == ItemEvent.SELECTED)
                                      ((ComboSort)Edt1).fillStammTable(((ComboSort)Edt2).getSelectedAIC(), true);
                                  }
                                });
                                FillCboPlatzhalter(1);
                                if(iArt==Edit && ObjAlt != null)
                                {
                                  String s1=ObjAlt.toString();
                                        if(s1.equals("*Joker*"))
                                                CboPlatzhalter.setSelectedKennung("*Joker*");
                                        else if(s1.startsWith("*JokerVec"))
                                        {
                                          CboPlatzhalter.setSelectedKennung("*JokerVec*");
                                          ((ComboSort)Edt2).setSelectedKennung(s1.substring(10,s1.length()-1));
                                        }
                                        else if(s1.startsWith("*UseVec"))
                                        {
                                          CboPlatzhalter.setSelectedKennung("*UseVec*");
                                          ((ComboSort)Edt2).setSelectedKennung(s1.substring(8,s1.length()-1));
                                        }
                                        else if(s1.startsWith("*Reserve1"))
                                        {
                                          CboPlatzhalter.setSelectedKennung("*Reserve1*");
                                          ((ComboSort)Edt2).setSelectedKennung(s1.substring(10,s1.length()-1));
                                        }
                                        else if(s1.startsWith("*JokerStt"))
                                        {
                                          CboPlatzhalter.setSelectedKennung("*JokerStt*");
                                          ((ComboSort)Edt2).setSelectedKennung(s1.substring(10,s1.length()-1));
                                        }
                                        else if(s1.equals("*meinStamm*"))
                                          CboPlatzhalter.setSelectedKennung("*meinStamm*");
                                        else if(s1.equals("*Qry*"))
                                          CboPlatzhalter.setSelectedKennung("*Qry*");
                                        else if(s1.equals("*Checkbox*"))
                                          CboPlatzhalter.setSelectedKennung("*Checkbox*");
                                        else if(s1.equals("*Radiobutton*"))
                                          CboPlatzhalter.setSelectedKennung("*Radiobutton*");
                                        else if(s1.startsWith("*meine"))
                                        {
                                          CboPlatzhalter.setSelectedKennung("*meine*");
                                          ((ComboSort)Edt2).setSelectedKennung(s1.substring(7,s1.lastIndexOf("*")));
                                        }
                                        else if (s1.startsWith("*Qry von"))
                                          CboPlatzhalter.setSelectedKennung("*Qry von*");
                                        else
                                        {
                                                if (s1.startsWith("*Joker von") && s1.indexOf(",")>0)
                                                {
                                                  CboPlatzhalter.setSelectedKennung("*Joker von*");
                                                  ((ComboSort)Edt2).setSelectedAIC(g.TabStammtypen.getAic(s1.substring(11,s1.indexOf(","))));
                                                }
                                                else
                                                {
                                                  CboPlatzhalter.setSelectedAIC(0);
                                                  int iStammMom = Sort.geti2(ObjAlt); //new Integer((String)ObjAlt).intValue();
                                                  ((ComboSort)Edt2).setSelectedAIC(SQL.getInteger(g, "select aic_stammtyp from stamm where aic_stamm=" + iStammMom));
                                                  ((ComboSort)Edt1).setSelectedAIC(iStammMom);
                                                }
                                        }
                                }

                                JPanel Pnl2=new JPanel(new BorderLayout());
				Pnl2.add("West",CboPlatzhalter);
				Pnl2.add("Center",Edt2);
                                Pnl2.add("East",Edt1);
				PnlAuswahl.add(Pnl2);
			}
			else if(sDatentyp.equals("Austritt")||sDatentyp.equals("Eintritt")||sDatentyp.equals("EinAustritt")||sDatentyp.equals("Datum")||sDatentyp.equals("Zeit")||
				sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("Timestamp") || sDatentyp.equals("entfernt") || sDatentyp.equals("Stichtag"))
			{
				Edt1 = new Datum(g,"dd.MM.yyyy");
				Lbl2 = new JLabel(g.getBegriffS("Show","und")+':');
				Edt2 = new Datum(g,"dd.MM.yyyy");
				Edt3 = new Text("",10);
                Edt3.setToolTipText("-3m ... 3 Monate zurück");
                if (CbxZeit==null)
                  CbxZeit=getCheckboxM("Zeit",false);
                if (CbxOhneJahr==null)
                  CbxOhneJahr=getCheckboxM("ohne_Jahr",false);
                CbxZeit.setSelected((iBBits&Abfrage.ZEIT)>0);
                CbxOhneJahr.setSelected((iBBits&Abfrage.OJ)>0);
				JPanel Pnl2=new JPanel(new BorderLayout());
				FillCboPlatzhalter(2);
				Pnl2.add("West",CboPlatzhalter);
				Pnl2.add("Center",Edt1);
                //if (g.Def())
                JPanel PnlE=new JPanel(new GridLayout());
                PnlE.add(Edt3);
                PnlE.add(CbxZeit);
                PnlE.add(CbxOhneJahr);
                Pnl2.add("East",PnlE);
				PnlAuswahl.add(Pnl2);
				PnlText.add(Lbl2);
				Pnl2=new JPanel(new BorderLayout());
				Pnl2.add("West",CboPlatzhalter2);
				Pnl2.add("Center",Edt2);
				PnlAuswahl.add(Pnl2);
				CboVergleichChanged();
				boolean bVB=isVorbedingung(Gid.getSelectedNode());
				String sBedArt=bVB ? "Vorbedingung":"Bedingung";
				int iPos=g.getPosBegriff("Show",sBedArt);
				String sTT=iPos<0 ? null:g.TabBegriffe.getM(iPos,"Tooltip");
				if (!Static.Leer(sTT))
					g.setTooltip(sTT,CboPlatzhalter);
//				g.fixtestError(sBedArt+":"+sTT);		
			}
			else if(sDatentyp.startsWith("String") || sDatentyp.equals("WWW") || sDatentyp.equals("Bezeichnung") || sDatentyp.equals("CalcBezeichnung") || sDatentyp.equals("Kennung") ||
				sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("E-Mail") || sDatentyp.equals("Pfad") || sDatentyp.equals("Filename") ||
                                sDatentyp.equals("Doku") || sDatentyp.startsWith("Bild"))
			{
				Edt1 = new JTextField(12);
				JPanel Pnl2=new JPanel(new BorderLayout());
				FillCboPlatzhalter(3);
				Pnl2.add("West",CboPlatzhalter);
				Pnl2.add("Center",Edt1);
				String s=(String)ObjAlt;
				if (Static.Leer(s))
					((JTextField)Edt1).setText("");
				else if (isVar(s))// s.startsWith("$") || s.startsWith("@"))
					EdtVar.setText(s);
				//if(iArt==Edit && ObjAlt != null)
				else
					((JTextField)Edt1).setText(((String)ObjAlt).substring(1,((String)ObjAlt).length()-1));
				PnlAuswahl.add(Pnl2);
			}
			else if(sDatentyp.endsWith("Boolean"))
			{
				Edt1 = new JCheckBox("",true);
                                Edt1.setEnabled(false);
				PnlAuswahl.add(Edt1);
			}
                        else if(sDatentyp.endsWith("Bool3"))
                        {
                          Edt1=new SpinBoxAuswahl(g,getEigenschaft(),false);
                          PnlAuswahl.add(Edt1);
                        }
			else if(sDatentyp.equals("Integer") || /*sDatentyp.equals("Protokoll") ||*/ sDatentyp.equals("Mehrfach"))
			{
				Edt1 = new Zahl(0);
				Lbl2 = new JLabel(g.getBegriffS("Show","und")+':');
				Edt2 = new Zahl(0);
                                /*if (sDatentyp.equals("Protokoll"))
                                {
                                  FillCboPlatzhalter(6);
                                  PnlText.add(new JLabel());
                                  PnlAuswahl.add(CboPlatzhalter);
                                }*/
				PnlAuswahl.add(Edt1);
				PnlText.add(Lbl2);
				PnlAuswahl.add(Edt2);
				CboVergleichChanged();
			}
			else if(sDatentyp.equals("Double"))
			{
				Edt1 = new Zahl(0.0);
				Lbl2 = new JLabel(g.getBegriffS("Show","und")+':');
				Edt2 = new Zahl(0.0);
                                Edt3 = new Text("",10);
                                JPanel Pnl2=new JPanel(new BorderLayout());
				Pnl2.add("Center",Edt1);
                                if (g.Def())
                                  Pnl2.add("East",Edt3);
                                PnlAuswahl.add(Pnl2);
				PnlText.add(Lbl2);
				PnlAuswahl.add(Edt2);
				CboVergleichChanged();
			}
			else if(sDatentyp.equals("BewDauer"))
			{
				Edt1 = new MassEingabe(g.getStunde(),g,true,true,-1,null,false);
				Lbl2 = new JLabel(g.getBegriffS("Show","und")+':');
				Edt2 = new MassEingabe(g.getStunde(),g,true,true,-1,null,false);
				PnlAuswahl.add(Edt1);
				PnlText.add(Lbl2);
				PnlAuswahl.add(Edt2);
				CboVergleichChanged();
			}
            else if(sDatentyp.equals("BewVon_Bis"))
            {
              PnlEdt1=new JPanel(new GridLayout());
              PnlEdt2=new JPanel(new GridLayout());
              if (CbxZeit==null)
                  CbxZeit=getCheckboxM("Zeit",false);
              if (RadVon==null)
              {
                RadVon=getRadiobuttonM("von");
                RadBis=getRadiobuttonM("bis");
                RadDauer=getRadiobuttonM("Dauer");

                ActionListener Act=new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    JRadioButton Rad=(JRadioButton)e.getSource();
                    checkRad(Rad);
                  }
                };
                RadVon.addActionListener(Act);
                RadBis.addActionListener(Act);
                RadDauer.addActionListener(Act);
              }
                Edt1 = new MassEingabe(g.getStunde(),g,true,true,-1,null,false);
                Lbl2 = new JLabel(g.getBegriffS("Show","und")+':');
                Edt2 = new MassEingabe(g.getStunde(),g,true,true,-1,null,false);
                PnlEdt1.add(Edt1);
                PnlEdt2.add(Edt2);
                PnlAuswahl.add(PnlEdt1);
                PnlText.add(Lbl2);
                PnlAuswahl.add(PnlEdt2);
                ButtonGroup RadNeu=new ButtonGroup();
                RadNeu.add(RadVon);
                RadNeu.add(RadBis);
                RadNeu.add(RadDauer);
                JPanel PnlRad=new JPanel(new GridLayout());
                PnlRad.add(RadVon);
                PnlRad.add(RadBis);
                PnlRad.add(RadDauer);
                PnlRad.add(CbxZeit);
                if ((iBBits&Abfrage.VBART)==Abfrage.VON)
                {
                	RadVon.setSelected(true);
                	checkRad(RadVon);
                }
                else if ((iBBits&Abfrage.VBART)==Abfrage.BIS)
                {
                	RadBis.setSelected(true);
                	checkRad(RadBis);
                }
                PnlAuswahl.add(PnlRad);
                CboVergleichChanged();
            }
			else if(sDatentyp.equals("Mass")||sDatentyp.equals("Waehrung"))
			{
				Edt1 = new Zahl(0.0);
				Cbo1 = new ComboSort(g);
				if(sDatentyp.equals("Mass"))
				{
					Cbo1.fillMass(g.TabEigenschaften.getI(iPosE,"aic_stamm"),true);
					Cbo1.setSelectedAIC(g.TabEigenschaften.getI(iPosE,"aic_stamm"));
				}
				else if(sDatentyp.equals("Waehrung"))
				{
					Cbo1.fillWaehrung(true,false);
					Cbo1.setSelectedAIC(g.getWaehrung());
				}
				Lbl2 = new JLabel(g.getBegriffS("Show","und")+':');
				Edt2 = new Zahl(0.0);
				Cbo2 = new ComboSort(g);
				if(sDatentyp.equals("Mass"))
				{
					Cbo2.fillMass(g.TabEigenschaften.getI(iPosE,"aic_stamm"),true);
					Cbo2.setSelectedAIC(g.TabEigenschaften.getI(iPosE,"aic_stamm"));
				}
				else if(sDatentyp.equals("Waehrung"))
				{
					Cbo2.fillWaehrung(true,false);
					Cbo2.setSelectedAIC(g.getWaehrung());
				}
				JPanel PnlTemp = new JPanel(new GridLayout(1,0,2,2));
					PnlTemp.add(Edt1);
					PnlTemp.add(Cbo1);
				PnlAuswahl.add(PnlTemp);
				PnlText.add(Lbl2);
				PnlTemp = new JPanel(new GridLayout(1,0,2,2));
					PnlTemp.add(Edt2);
					PnlTemp.add(Cbo2);
				PnlAuswahl.add(PnlTemp);
				CboVergleichChanged();
			}
                        else if(sDatentyp.equals("BewModell")||sDatentyp.equals("BewDruck")||sDatentyp.equals("BewFormular"))
                        {
                          Cbo1 = new ComboSort(g);
                          Cbo1.fillBegriffTable("Programm", true);
                          PnlAuswahl.add(Cbo1);
                          CboVergleichChanged();
                        }
                        else if (sDatentyp.equals("BewBew") || sDatentyp.equals("Protokoll") || sDatentyp.equals("SysAic") && A.iBew>0)
                        {
                          FillCboPlatzhalter(sDatentyp.equals("Protokoll") ? 6:5);
                          //PnlAuswahl.removeAll();
                          PnlAuswahl.add(CboPlatzhalter);
                          CboVergleichChanged();
                        }
			else if (!sDatentyp.equals("GPS")) //if(!sDatentyp.equals("von_bis") && !sDatentyp.equals("Auto_von_bis") && !sDatentyp.equals("BewVon_Bis"))
			{
				new Message(Message.WARNING_MESSAGE,null,g).showDialog("DatentypFalsch");
				return;
			}
			FillCboVergleich(sDatentyp);
			if (iArt==Edit)
                        {
                          CboVergleich.setSelectedKennung(((Vector)Gid.getSelectedNode().getUserData()).elementAt(2).toString());

                        }
                        PnlOben.add("West",PnlText);
			PnlOben.add("Center",PnlAuswahl);
		Dlg.getContentPane().add("North",PnlOben);

		JPanel PnlUnten = new JPanel(new GridLayout());
			JButton BtnOk = g.getButton("Ok");
                        BtnOk.setEnabled(bEdit);
			JButton BtnAbbruch = g.getButton("Abbruch");
                        Dlg.getRootPane().setDefaultButton(BtnOk);
			PnlUnten.add(BtnOk);
			PnlUnten.add(BtnAbbruch);
			BtnOk.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					NeueZeile(bNeu);
					Dlg.dispose();
				}
			});
			BtnAbbruch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					Dlg.dispose();
				}
			});
		Dlg.getContentPane().add("South",PnlUnten);
		//Dlg.setSize(230,180);
		Dlg.pack();
		Static.centerComponent(Dlg,thisFrame);
		Dlg.setVisible(true);
	}
	
	private void checkRad(JRadioButton Rad)
	{
		if (Rad==RadVon || Rad==RadBis)
        {
          g.fixtestError((Rad==RadVon ?"RadVon":"RadBis")+" gedrückt");
          Edt1 = new Datum(g, "dd.MM.yyyy");
          Edt2 = new Datum(g, "dd.MM.yyyy");
          PnlEdt1.removeAll();
          PnlEdt2.removeAll();
          FillCboPlatzhalter(2);
          PnlEdt1.add(CboPlatzhalter);
          PnlEdt1.add(Edt1);
          Edt3 = new Text("",10);
          Edt3.setToolTipText("-3m ... 3 Monate zurück");
          PnlEdt1.add(Edt3);
          PnlEdt2.add(CboPlatzhalter2);
          PnlEdt2.add(Edt2);
          Dlg.validate();
        }
        else
          g.fixtestError("RadDauer gedrückt");
	}

	private void CboVergleichChanged()
	{
          String sPlatz=CboPlatzhalter.getSelectedKennung();
		g.testInfo("CboVergleichChanged:"+CboVergleich.getSelectedKennung()+"/"+sPlatz);
		boolean bBetween = CboVergleich.getSelectedKennung().equals("between");
		boolean bNull = CboVergleich.getSelectedKennung().equals("is null") || CboVergleich.getSelectedKennung().equals("is not null");
		boolean bEingabe = !bNull && CboPlatzhalter.getSelectedAIC()==0;
		boolean bEingabe2 = bBetween && CboPlatzhalter2.getSelectedAIC()==0;
		if (Lbl1 != null)
			Lbl1.setVisible(!bNull);
		if (Edt1 != null)
			Edt1.setVisible(bEingabe);
                if (CboR2 != null)
                  CboR2.setVisible(bNull && !CboR2.Cbo.isEmpty());
		CboPlatzhalter.setVisible(!bNull);
		CboPlatzhalter2.setVisible(bBetween);
		if (Lbl2 != null)
			Lbl2.setVisible(bBetween);
		if (Edt2 != null)
			Edt2.setVisible(bEingabe2 || !bNull && Edt2 instanceof ComboSort && (bEingabe || sPlatz.equals("*JokerStt*") || sPlatz.equals("*JokerVec*") || sPlatz.equals("*UseVec*") || sPlatz.equals("*Reserve1*") || sPlatz.equals("*meine*") || sPlatz.startsWith("*Joker von")));
		if (Cbo2 != null)
			Cbo2.setVisible(bEingabe2);
	}

	@SuppressWarnings("unchecked")
	private void NeueZeile(boolean bNeu) // neue oder geänderte Bedingung
	{
//        g.fixtestError("NeueZeile:"+bNeu);
		Vector<Serializable> VecAIC = new Vector<Serializable>();
		Integer IntAic = getEigenschaft();
		int iPosE=g.TabEigenschaften.getPos("aic",IntAic);
		String sDatentyp = g.TabEigenschaften.getS(iPosE,"datentyp");
		VecAIC.addElement(sDatentyp);

		//String sEigenschaft=(String)GidEigenschaft.getSelectedNode().getLabel();
		Vector<Object> Vec2;
		if (iArt==Edit)
			Vec2=(Vector)((Vector)Gid.getSelectedNode().getUserData()).elementAt(1);
		else
		{
			Vec2=new Vector();
			Vec2.addElement(((Vector)GidEigenschaft.getSelectedNode().getUserData()).elementAt(0));
			JCOutlinerFolderNode NodParent = GidEigenschaft.getSelectedNode().getParent();
			while (NodParent != GidEigenschaft.getRootNode())
			{
				//sEigenschaft=(String)NodParent.getLabel()+'.'+sEigenschaft;
				Vec2.insertElementAt(((Vector)NodParent.getUserData()).elementAt(0),0);
				NodParent=NodParent.getParent();
			}
		}

		/*
		Vector Vec2=null;
		if (iArt==Edit)
			Vec2=(Vector)((Vector)Gid.getSelectedNode().getUserData()).elementAt(1);
		else
		{
			Vec2=(Vector)VecEigenschaften.clone();
			Vec2.addElement(new Integer(CboEigenschaft.getSelectedAIC()));
		}*/
                if (/*iArt==Edit && */CboR2 != null && CboR2.getSelectedAIC()>0)
                {
                  int iPosBE=Vec2.size()-1;
                  Vec2.setElementAt(new BewEig(0,Sort.geti(Vec2,iPosBE),CboR2.getSelectedAIC(),null),iPosBE);
                }
                //g.fixInfo("Art="+iArt+", Vec2="+Vec2);
		VecAIC.addElement(Vec2);
		String sVergleichKennung = CboVergleich.getSelectedKennung();
		VecAIC.addElement(sVergleichKennung);
		boolean bZwischen = sVergleichKennung.equals("between");
		int iBBits=(RadVon==null ? 0:RadVon.isSelected()? Abfrage.VON:RadBis.isSelected()? Abfrage.BIS:Abfrage.DAUER) + (CbxZeit !=null && CbxZeit.isSelected() ? Abfrage.ZEIT:0) + (CbxOhneJahr !=null && CbxOhneJahr.isSelected() ? Abfrage.OJ:0)+ (CbxKen !=null && CbxKen.isSelected() ? Abfrage.KEN:0);
//		g.fixtestError("NeueZeile: iBBits="+iBBits+"/"+CbxZeit);
		String s = Abfrage.getEigenschaftBezeichnung(g,Vec2)+((iBBits&Abfrage.VON)>0?".von":(iBBits&Abfrage.BIS)>0?".bis":(iBBits&Abfrage.KEN)>0?".Ken":"")+((iBBits&Abfrage.OJ)>0?".ohne_Jahr ":(iBBits&Abfrage.ZEIT)>0 ? ".Zeit ":" ")+CboVergleich.getSelectedItem()+" ";
//                g.progInfo("CboPlatzhalter="+CboPlatzhalter.getSelectedItem()+"/"+CboPlatzhalter.getSelectedKennung()+"/"+CboPlatzhalter.getSelectedAIC());
		String sPlatz = CboPlatzhalter.getSelectedAIC()>0 ? CboPlatzhalter.getSelectedKennung():null;
//                g.fixtestError("NeueZeile: sPlatz="+sPlatz+", s="+s);
		if (sVergleichKennung.equals("is null") || sVergleichKennung.equals("is not null"))
                {
                  VecAIC.addElement(CboR2 == null ? null : CboR2.getSelectedAIC());
                  //if (CboR2 != null && CboR2.getSelectedAIC()>0)
                  //  s+=" ("+CboR2.Cbo.getSelectedBezeichnung()+")";
                }
                else if (sPlatz != null && (sPlatz.equals("*JokerStt*")||sPlatz.equals("*JokerVec*")||sPlatz.equals("*UseVec*")||sPlatz.equals("*Reserve1*")))
                {
                  int iJStt=0;
                  if (sDatentyp.endsWith("Hierarchie") || sDatentyp.endsWith("SysAic"))
                    iJStt=((ComboSort)Edt2).getSelectedAIC();
                  else if (sDatentyp.endsWith("Firma"))
                      iJStt=g.iSttFirma;
                  else
                  {
                    //Integer Int = (Integer)A.VecToStt(Vec2);
                    iJStt = Tabellenspeicher.geti(A.VecToStt(Vec2));
                    if (iJStt == 0)
                    {
                      iJStt = iStammtyp;
                      //Int = new Integer(iJStt);
                    }
                  }
                  String sVg=(sPlatz.equals("*UseVec*")?sPlatz.substring(0,7):sPlatz.substring(0,9))+" "+g.TabStammtypen.getKennung(iJStt) + "*";
                  g.progInfo("Vergleichswert="+sVg);
                  g.checkJoker(sVg);
                  VecAIC.addElement(sVg);
                  s+=sVg;
                  //VecAIC.addElement("*Joker"+(sPlatz.equals("*JokerVec*")?"Vec":"")+iJStt+"*");
                  //s+=sPlatz.equals("*JokerStt*")?"JokerStt":"JokerVec";
                }
                else if (sPlatz != null && sPlatz.startsWith("*Qry von*"))
                {
                  int iEig=IntAic.intValue();
                  String sVg="*Qry von "+iEig+"*";
                  if (A.VecQry==null)
                    A.VecQry=new Vector<String>();
                  A.VecQry.addElement(sVg);
                  VecAIC.addElement(sVg);
                  s+=sVg;
                }
                else if (sPlatz != null && sPlatz.startsWith("*Joker von*"))
                {
                  int iEig=IntAic.intValue();
                  String sVg="*Joker von "+((ComboSort)Edt2).getSelectedKennung().toUpperCase()+","+iEig+"*";
                  if (A.VecQry==null)
                    A.VecQry=new Vector<String>();
                  A.VecQry.addElement(sVg);
                  VecAIC.addElement(sVg);
                  s+=sVg;
                }
                else if (sPlatz != null && sPlatz.equals("*meine*"))
                {
                  //String sVg=TabBedingungen.getS("vergleichswert");
                  String sVg=sDatentyp.equals("Hierarchie") || sDatentyp.endsWith("BewStamm") || sDatentyp.endsWith("Firma") ? "*meine "+
                      (((ComboSort)Edt2).getSelectedAIC()==0 ? g.TabStammtypen.getKennung(A.iStt):((ComboSort)Edt2).getSelectedKennung())+"*":
                      /*sDatentyp.equals("SysAic") ? "*meine "+g.TabStammtypen.getKennung(A.iStt)+"*":*/sPlatz;
                  int iEig=IntAic.intValue();
                  g.checkMeine(sVg,iEig,sDatentyp);
                  VecAIC.addElement(sVg+iEig);
                  s+=sVg;
                }
		else if (sDatentyp.equals("Anlage") || sDatentyp.equals("Mandant") || sDatentyp.equals("Land") || sDatentyp.equals("Formular") || sDatentyp.equals("Modell") || sDatentyp.equals("Abfrage") || sDatentyp.equals("Aufgabe") || sDatentyp.equals("Status"))
		{
                  int iAic=((ComboSort)Edt1).getSelectedAIC();
			VecAIC.addElement(sDatentyp.equals("Mandant") && iAic==0 ? "*aktMandant*":""+iAic);
			s=s+(iAic>0 || !sDatentyp.equals("Mandant") ? ((ComboSort)Edt1).getSelectedItem():g.getShow("akt_Mandant"));
		}
		else if (sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe") || sDatentyp.equals("Firma") || sDatentyp.equals("SysAic") && A.iBew==0 || sDatentyp.equals("Benutzer") || sDatentyp.equals("LoeschBenutzer"))
		{
			String sVGW=EdtVar==null || EdtVar.isNull() ? null:EdtVar.getText();
			VecAIC.addElement(Static.beiLeer(sVGW==null ? sPlatz:sVGW,""+((ComboSort)Edt1).getSelectedAIC()));
			s=s+(sVGW!=null ? sVGW: sPlatz==null ? ((ComboSort)Edt1).getSelectedItem():CboPlatzhalter.getSelectedItem());
		}
		else if (sDatentyp.endsWith("Hierarchie"))
		{
			VecAIC.addElement(Static.beiLeer(sPlatz,""+((ComboSort)Edt1).getSelectedAIC()));
			s=s+(sPlatz==null?((ComboSort)Edt1).getSelectedItem():CboPlatzhalter.getSelectedItem());
		}
		else if (sDatentyp.startsWith("String") || sDatentyp.equals("WWW") || sDatentyp.equals("Bezeichnung") || sDatentyp.equals("CalcBezeichnung") || sDatentyp.equals("Kennung") ||
			sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("E-Mail") || sDatentyp.equals("Pfad") || sDatentyp.equals("Filename"))
		{
			String sVar=EdtVar.getText();
			String sVG=((JTextField)Edt1).getText();
			String sVGW=EdtVar.isNull() ? "'"+sVG+"'":sVar;
			VecAIC.addElement(Static.beiLeer(sPlatz,sVGW));
			s=s+(sPlatz==null ? sVGW:CboPlatzhalter.getSelectedItem());
		}
		else if (sDatentyp.equals("Boolean"))
		{
			VecAIC.addElement(((JCheckBox)Edt1).isSelected()?"1.0":"0.0");
			s=s+Static.JaNein(((JCheckBox)Edt1).isSelected());
		}
		else if (sDatentyp.equals("BewBoolean"))
		{
			VecAIC.addElement(((JCheckBox)Edt1).isSelected()?"1":"0");
			s=s+Static.JaNein(((JCheckBox)Edt1).isSelected());
		}
                else if (sDatentyp.endsWith("Bool3"))
                {
                  VecAIC.addElement(""+((SpinBoxAuswahl)Edt1).getAic());
                  s=s+((SpinBoxAuswahl)Edt1).getValue();
                }
		else if (sDatentyp.equals("Integer") /*|| sDatentyp.equals("Protokoll")*/ || sDatentyp.equals("Mehrfach"))
		{
			VecAIC.addElement(((Zahl)Edt1).intValue()+(bZwischen ? " and "+((Zahl)Edt2).intValue():""));
			s=s+((JTextField)Edt1).getText()+(bZwischen ?" und "+((JTextField)Edt2).getText():"");
		}
		else if (sDatentyp.equals("BewDauer") || sDatentyp.equals("BewVon_Bis") && RadDauer.isSelected()) // !!!
		{
			VecAIC.addElement(((MassEingabe)Edt1).getAbsValue()+(bZwischen ? " and "+((MassEingabe)Edt2).getAbsValue():""));
			s=s+Edt1+(bZwischen ?" und "+Edt2:"");
		}
        else if (sDatentyp.equals("BewVon_Bis") && !RadDauer.isSelected())
        {
        	boolean bZeit=CbxZeit != null && CbxZeit.isSelected();
        	if (sPlatz != null && !Edt3.getText().equals(""))
  			  sPlatz+=Edt3.getText();
                VecAIC.addElement(bZeit ? "'"+Edt3.getText()+"'":Static.beiLeer(sPlatz,"'"+((Datum)Edt1).getSQLDate()+"'")+(bZwischen ? " and "+
                        (CboPlatzhalter2.getSelectedAIC()==0?"'"+((Datum)Edt2).getSQLDate()+"'":CboPlatzhalter2.getSelectedKennung()):""));
                s = s + (sPlatz==null ? bZeit ? Edt3.getText():((Datum)Edt1).getSQLDate():CboPlatzhalter.getSelectedItem()+" "+Edt3.getText())+(bZwischen ? " und "+
                        (CboPlatzhalter2.getSelectedAIC()==0?((Datum)Edt2).getSQLDate():CboPlatzhalter2.getSelectedItem()):"");
        }
		else if (sDatentyp.equals("Double") || sDatentyp.equals("BewZahl"))
		{
                  sPlatz=""+((Zahl)Edt1).doubleValue();
                  if (!Edt3.getText().equals(""))
                    sPlatz="*"+Edt3.getText()+"*";
                  VecAIC.addElement(sPlatz+(bZwischen ? " and "+((Zahl)Edt2).doubleValue():""));
                  s=s+sPlatz/*((JTextField)Edt1).getText()*/+(bZwischen ?" und "+((JTextField)Edt2).getText():"");
		}
		else if (sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Datum") || sDatentyp.equals("Zeit") ||
			sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("Timestamp") || sDatentyp.equals("entfernt") || sDatentyp.equals("Stichtag"))
		{
			//g.progInfo("Joker="+sPlatz+"/"+CboPlatzhalter2.getSelectedKennung()+"/"+((Datum)Edt1).getSQLDate()+"/"+((Datum)Edt2).getSQLDate());
			if (sPlatz != null && !Edt3.getText().equals(""))
			  sPlatz+=Edt3.getText();
            boolean bZeit=CbxZeit != null && CbxZeit.isSelected();
            boolean bVB=isVorbedingung(Gid.getSelectedNode());
//            g.fixtestError("bVB="+bVB+" bei "+Gid.getSelectedNode());
            String sDt1=bVB ? ""+Static.DateToInt(((Datum)Edt1).getDate()):"'"+((Datum)Edt1).getSQLDate()+"'";
		  	VecAIC.addElement(bZeit ? "'"+Edt3.getText()+"'":Static.beiLeer(sPlatz,sDt1)+(bZwischen ? " and "+
				(CboPlatzhalter2.getSelectedAIC()==0?"'"+((Datum)Edt2).getSQLDate()+"'":CboPlatzhalter2.getSelectedKennung()):""));
			s = s + (sPlatz==null? bZeit ? Edt3.getText():((Datum)Edt1).getSQLDate():
                                 CboPlatzhalter.getSelectedItem()+" "+Edt3.getText())+(bZwischen ? " und "+
                                 (CboPlatzhalter2.getSelectedAIC()==0?((Datum)Edt2).getSQLDate():CboPlatzhalter2.getSelectedItem()):"");
		}
		else if (sDatentyp.equals("Mass") || sDatentyp.equals("BewMass"))
		{
			VecAIC.addElement(((Zahl)Edt1).doubleValue()*Cbo1.getSelectedFaktor()+(bZwischen ? " and "+((Zahl)Edt2).doubleValue()*Cbo2.getSelectedFaktor():""));
			s = s + (bZwischen ? ((Zahl)Edt1).getText()+Cbo1.getSelectedKennung()+" und "+((Zahl)Edt2).getText()+Cbo2.getSelectedKennung():((Zahl)Edt1).getText()+Cbo1.getSelectedKennung());
		}
		else if (sDatentyp.equals("Waehrung") || sDatentyp.equals("BewWaehrung"))
		{
			VecAIC.addElement(((Zahl)Edt1).doubleValue()/Cbo1.getSelectedFaktor()+(bZwischen ? " and "+((Zahl)Edt2).doubleValue()/Cbo2.getSelectedFaktor():""));
			s = s + (bZwischen ? ((Zahl)Edt1).getText()+Cbo1.getSelectedKennung()+" und "+((Zahl)Edt2).getText()+Cbo2.getSelectedKennung():((Zahl)Edt1).getText()+Cbo1.getSelectedKennung());
		}
		else if(sDatentyp.equals("BewModell")||sDatentyp.equals("BewDruck")||sDatentyp.equals("BewFormular"))
		{
			VecAIC.addElement(""+Cbo1.getSelectedAIC());
			s = s + Cbo1.getSelectedKennung();
		}
        else if (sDatentyp.endsWith("BewBew") || sDatentyp.equals("Protokoll") || sDatentyp.equals("SysAic") && A.iBew>0)
        {
                VecAIC.addElement(sPlatz);
                s=s+CboPlatzhalter.getSelectedItem();
        }
		else
		{
			s = null;
			new Message(Message.INFORMATION_MESSAGE,null,g).showDialog("Datentyp",new String[] {sDatentyp});
		}
		VecAIC.addElement(iBBits);
		g.fixtestError("VecAic="+VecAIC);
                /*if (sDatentyp.equals("BewVon_Bis"))
                  RadVon==null ? Abfrage.DAUER:RadVon.isSelected()? Abfrage.VON:RadBis.isSelected()? Abfrage.BIS:Abfrage.DAUER);
                else*/ if (CbxZeit != null && CbxZeit.isSelected())
                {
//                  VecAIC.addElement(Abfrage.ZEIT);
                  CbxZeit.setSelected(false);
                }
                else if (CbxOhneJahr != null && CbxOhneJahr.isSelected())
                {
//                  VecAIC.addElement(Abfrage.OJ);
                  CbxOhneJahr.setSelected(false);
                }
		if (s != null)
		{
                  //g.progInfo("VecAic="+VecAIC);
			JCOutlinerFolderNode Nod = iArt==Edit || !bNeu ? (JCOutlinerFolderNode)Gid.getSelectedNode():
				new JCOutlinerFolderNode((String)null,iArt==Und ? (JCOutlinerFolderNode)Gid.getSelectedNode():Gid.getSelectedNode().getParent());
			Nod.setLabel(s);
			Nod.setUserData(VecAIC);
			Gid.folderChanged(Gid.getRootNode());
			Gid.selectNode(Nod,null);
			EnableButtons();
		}
	}

	// --------------------------------------------------------------------------------
	// -------------   * * * * *   E D I T   Spalten   * * * * *   --------------------
	// --------------------------------------------------------------------------------

        private void ZeileLoeschen(boolean b,boolean bDel)
        {
          JCOutlinerNode NodN=Gid.getPreviousNode(Gid.getSelectedNode());
          JCOutlinerNode[] Nodes=Gid.getSelectedNodes();
          boolean bOk=true;
          for (int i=0;i<Nodes.length;i++)
            bOk=bOk && ZeileLoeschen(b, bDel,Nodes[i]);
          if (bDel && bOk)
            Static.makeVisible(Gid,NodN);
        }

	private boolean ZeileLoeschen(boolean b,boolean bDel,JCOutlinerNode NodMom)
	{
          //g.fixInfo("ZeileLoeschen:"+NodMom.getLabel()+"/"+b+"/"+bDel);
		bReihenfolge=true;
		boolean bDelOk=true;
		boolean bBed=true;
		boolean bMsg=true;
		if(A.TabSp != null && A.TabSp.posInhalt("Nummer",NodMom.getUserData()))
		{
		  bBed=false;
			if(A.TabSp.getI("aic_spalte")!=0)
			{
                Tabellenspeicher Tab=null;
                if (bDel || (A.TabSp.getI("bits2")&Abfrage.cstWeg)==0)
                {
                  Tab=getModelle_der_Spalte(g,A.TabSp.getI("aic_spalte"),null);
				  bDelOk=Tab.isEmpty();
                }
				/*String s="";
				for(Qry.moveFirst();!Qry.eof();Qry.moveNext())
					s= s+(s.equals("") ? "":",")+Qry.getS("Kennung");
				Qry.free();*/
                                String sSpalte=g.getBegriffS("Show","Spalte")+" "+(A.TabSp.isNull("Bezeichnung") ? Abfrage.getEigenschaftBezeichnung(g, (Vector)A.TabSp.getInhalt("Vec")) : A.TabSp.getS("Bezeichnung"));
                bMsg=false;
				if (!bDelOk)
                  new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,null,g).showDialog("BereitsVerwendet",new String[] {sSpalte},Tab);
                else if (!bEdit)
                  new Message(Message.INFORMATION_MESSAGE,(JFrame)thisFrame,g,3).showDialog("nicht verwendet");
			}
                        if (!bEdit || !bMsg && !bDelOk)
                          return false;
                        g.fixInfo("ZeileLoeschen:"+bDelOk+"/"+bDel);
			if(bDelOk || !bDel)
                        {
//                          if (A.TabSp.getI("Sortiert")>0)
//                          {
//                            new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g,5).showDialog("Sortierspalte");
//                            return false;
//                          }
                          int iNr=A.TabSp.getI("Nummer");
                          A.TabSp.push();
                          String s=null;
                          for(A.TabSp.moveFirst();!A.TabSp.eof();A.TabSp.moveNext())
                            if (A.TabSp.getInhalt("Calc") != null && !A.TabSp.getS("Status").equals("Del"))
                            {
                              Tabellenspeicher Tab = (Tabellenspeicher)A.TabSp.getInhalt("Calc");
                              //int i = 0;
                              for (Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                              {
                                if (!Tab.isNull("Spalte") && ((Combo)Tab.getInhalt("Spalte")).getAic()==iNr)
                                {
                                  String s2= A.TabSp.isNull("Bezeichnung") ? Abfrage.getEigenschaftBezeichnung(g, (Vector)A.TabSp.getInhalt("Vec")) : A.TabSp.getS("Bezeichnung");
                                  s = s == null ? s2:s+","+s2;
                                  bDelOk=false;
                                }
                              }
                            }
                          A.TabSp.pop();
//                          g.fixtestError(A.TabSp.getS("Bezeichnung")+": delOk="+bDelOk);
                          if (bDelOk)// || !bDel)
                          {
                            if (bDel)
                              if (A.TabSp.isNull("aic_spalte"))
                                A.TabSp.clearInhalt();
                              else
                                A.TabSp.setInhalt("Status", "Del");
                            else
                            {
                              //A.TabSp.setInhalt("bits2",A.TabSp.getI("bits2")^Abfrage.cstWeg);
                              //int iBitWeg=Abfrage.cstWeg+Abfrage.cstUnsichtbar;
                              if ((A.TabSp.getI("bits2")&Abfrage.cstWeg)==0)// && A.TabSp.getI("sortiert")==0)
                              {
                                A.TabSp.setInhalt("bits2", A.TabSp.getI("bits2") | Abfrage.cstWeg); //+Abfrage.cstOffen));
                                A.TabSp.setInhalt("bits", A.TabSp.getI("bits") | Abfrage.cstUnsichtbar);
//                                g.fixtestError("Weg nehmen");
                              }
                              else if ((A.TabSp.getI("bits2")&Abfrage.cstWeg)>0)
                              {
                                A.TabSp.setInhalt("bits2", A.TabSp.getI("bits2") - Abfrage.cstWeg);
                                A.TabSp.setInhalt("bits", A.TabSp.getI("bits") - (A.TabSp.getI("bits") & Abfrage.cstUnsichtbar));
//                                g.fixtestError("wieder aktivieren");
                              }
                              if (A.TabSp.getInhalt("Status")==null)
                                A.TabSp.setInhalt("Status","Edit");
                              //if (!bDelOk)
                              //  new Message(Message.INFORMATION_MESSAGE,null,g).showDialog("BereitsVerwendet",new String[] {"<"+s+">"});
                            }
                            //if(g.Prog())
                            //  A.TabSp.showGrid("A.TabSp");
                          }
                          else //if (bMsg)
                            new Message(Message.WARNING_MESSAGE,null,g).showDialog("BereitsVerwendet",new String[] {"["+s+"]"});
                        }
		}
		if(bDelOk)// || !bDel)
		{
		  JCOutlinerNode Nod=NodMom;
                  JCOutlinerFolderNode NodP=Nod.getParent();
                  if (!bDel)
                  {
                    Nod.getStyle().setForeground(A.getColor("xx"));//(A.TabSp.getI("bits2")&Abfrage.cstWeg)>0 ? g.ColHide: g.ColStandard);
                    return true;
                  }
		  else if (bBed && b)
		  {
		    Vector VecC=Nod.getChildren();
		    NodP.removeChild(Nod);
		    if (VecC!=null && VecC.size()>0)
	              for (int i=VecC.size()-1;i>=0;i--)
	        	NodP.addNode((JCOutlinerNode)VecC.elementAt(i));
		  }
		  else
		    NodP.removeChild(Nod);
		  Gid.folderChanged(NodP);
		  EnableButtons();
		}
                return bDelOk;
		//if(g.Prog())
		//	TabSpalten.showGrid();
	}

        @SuppressWarnings("unchecked")
	private void NeueSpalte(boolean bNeu)
	{
		String sEigenschaft=Sort.gets(GidEigenschaft.getSelectedNode().getLabel());
		Vector<Object> Vec2=new Vector<Object>();
                int iStt2=0;
		//Vec2.addElement(((Vector)GidEigenschaft.getSelectedNode().getUserData()).elementAt(0));
		//Object Obj=GidEigenschaft.getSelectedNode().getUserData();
		//g.progInfo("NeueSpalte="+Obj.getClass().getName()+"/"+Obj);
		Vec2.addElement(GidEigToObj((Vector)GidEigenschaft.getSelectedNode().getUserData(),null));
		JCOutlinerFolderNode NodParent = GidEigenschaft.getSelectedNode().getParent();
		while (NodParent != GidEigenschaft.getRootNode())
		{
			sEigenschaft=Sort.gets(NodParent.getLabel())+'.'+sEigenschaft;
			//Vec2.insertElementAt(((Vector)NodParent.getUserData()).elementAt(0),0);
                        //Obj=NodParent.getUserData();
                        //g.progInfo(sEigenschaft+"="+Obj.getClass().getName()+"/"+Obj);
                        Vector Vec3=(Vector)NodParent.getUserData();
                        if (Tabellenspeicher.geti(Vec3.elementAt(0))<0)
                          iStt2=Tabellenspeicher.geti(Vec3.elementAt(1));
			Vec2.insertElementAt(GidEigToObj(Vec3,null),0);
			NodParent=NodParent.getParent();
		}

		JCOutlinerNode Nod=bNeu ? new JCOutlinerNode(null,Gid.getSelectedNode().getLevel()==1 ? (JCOutlinerFolderNode)Gid.getSelectedNode():Gid.getSelectedNode().getParent()):Gid.getSelectedNode();
		Nod.setLabel(sEigenschaft);
		Nod.setStyle(GidEigenschaft.getSelectedNode().getStyle());
		//Nod.setUserData(Vec2);
		//TabSpaltenHinzu(Nod,Vec2,bNeu);
		if (bNeu)
		{
			A.addSpalte();
			setSpalten();
			//if (iAbfrage>0)
			//	TabAbfragen.setInhalt("Spalten",iSpalten);
			A.TabSp.newLine();
			A.TabSp.setInhalt("bits",Abfrage.cstAnzeigen+Abfrage.cstKeinAutoLast+(Vec2.size()==1 ? Abfrage.cstEditierbar:0));
			A.TabSp.setInhalt("Nummer",A.Spalten());
			A.TabSp.setInhalt("Reihenfolge",A.Spalten());
                        if (iStt2>0)
                          A.TabSp.setInhalt("aic_stammtyp",iStt2);
			Nod.setUserData(new Integer(A.Spalten()));			
                        JCOutlinerNode NodSel=Gid.getSelectedNode();
                        if (NodSel.getLevel()>1)
                        {
                          bReihenfolge = true;
                          Vector VecChildren=NodSel.getParent().getChildren();
                          VecChildren.remove(Nod);
                          VecChildren.insertElementAt(Nod,VecChildren.indexOf(NodSel));
                        }
		}
		else
			A.TabSp.posInhalt("Nummer",Nod.getUserData());
		A.TabSp.setInhalt("Vec",Vec2);
		A.TabSp.setInhalt("Status",bNeu || A.TabSp.getS("Status").equals("Neu")?"Neu":"Ersetzt");

		Gid.folderChanged(Gid.getSelectedNode());
		//if (g.Prog())
		//	A.TabSp.showGrid();
                //g.progInfo("iStt2="+iStt2);
	}

        private void copySpalte()
        {
          JCOutlinerNode NodV=Gid.getSelectedNode();
          JCOutlinerNode Nod=new JCOutlinerNode(null,NodV.getParent());
          Nod.setLabel("Kopie "+NodV.getLabel());
          Nod.setStyle(Gid.getSelectedNode().getStyle());
          A.addSpalte();
          setSpalten();
          int iBits=Abfrage.cstAnzeigen;
          Vector Vec2=null;
          int iLaenge=0;
          int iFormat=0;
          if(A.TabSp.posInhalt("Nummer", Gid.getSelectedNode().getUserData()))
          {
            iBits=A.TabSp.getI("bits");
            Vec2=(Vector)A.TabSp.getInhalt("Vec");
            iLaenge=A.TabSp.getI("Laenge");
            iFormat=A.TabSp.getI("aic_begriff");
          }
          A.TabSp.newLine();
          A.TabSp.setInhalt("bits",iBits);
          A.TabSp.setInhalt("Nummer",A.Spalten());
          A.TabSp.setInhalt("Reihenfolge",A.Spalten());
          A.TabSp.setInhalt("Laenge",iLaenge);
          A.TabSp.setInhalt("aic_begriff",iFormat);
          //if (iStt2>0)
          //  A.TabSp.setInhalt("aic_stammtyp",iStt2);
          Nod.setUserData(new Integer(A.Spalten()));
          A.TabSp.setInhalt("Vec",Vec2);
          A.TabSp.setInhalt("Status","Neu");
          Gid.folderChanged(Gid.getSelectedNode());
          Gid.selectNode(Nod);
          Edit();
        }

	private Object GidEigToObj(Vector Vec,String sArt)
	{
		//g.progInfo("Vec="+Vec.getClass().getName());
		//g.progInfo("Vec="+Vec.size()+Vec.elementAt(0).getClass().getName());
		int iEig=Tabellenspeicher.geti(Vec.elementAt(0));
		int iBew=Vec.elementAt(1)==null ? 0:((Integer)Vec.elementAt(1)).intValue();
		return iBew<0?(Object)new BewEig(-iBew,iEig,0,sArt):new Integer(iEig);
	}

	/*private void TabSpaltenHinzu(JCOutlinerNode Nod,Vector Vec,boolean bNeu)
	{
		if (bNeu)
		{
			setSpalten(iSpalten+1);
			if (iAbfrage>0)
				TabAbfragen.setInhalt("Spalten",iSpalten);
			TabSpalten.newLine();
			TabSpalten.setInhalt("bits",Abfrage.cstAnzeigen);
			TabSpalten.setInhalt("Nummer",iSpalten);
			Nod.setUserData(new Integer(iSpalten));
		}
		else
			TabSpalten.posInhalt("Node",Nod);
		TabSpalten.setInhalt("Vec",Vec);
		TabSpalten.setInhalt("Status",bNeu?"Neu":"Ersetzt");
	}*/

	private void checkFirstSpaltenEdit()
	{
		if (bFirstSpEdit)
		{
			//g.testInfo("checkFirstSpaltenEdit");
                        EdtFormat=new Format(g,"Spalte",thisFrame);
			CboExportformat=new ComboSort(g);
			CboAnzeigeart=new ComboSort(g);
			//g.progInfo("CboArt.fillBegriffTable");
			CboErgebnis=new ComboSort(g);
			//g.progInfo("CboErgebnis.fillBegriffTable");
			CboAusrichtung=new ComboSort(g);
			//g.progInfo("CboAusrichtung.fillBegriffTable");
			CboMass1=new ComboSort(g);
			CboMass2=new ComboSort(g);
			CboMass3=new ComboSort(g);
			
            CboRel=new ComboSort(g);
			//g.progInfo("CboMass.fillBegriffTable");
			CboFilter=new AbfrageEingabe(g,thisFrame,true,false);
            CboHierarchie=new ComboSort(g,Combo.kein);
			//CboFilter.fillDefTable("Abfrage",true);	20.8.2003
			//g.progInfo("CboFilter.fillBegriffTable");
            String[] sTypen= {"Frame","Abfrage","Modell","Druck","Grafik"};
            CboSub1=new SubEingabe(g);
            CboSub2=new SubEingabe(g);
            CboSub3=new SubEingabe(g);
            Refresh(false);
            bFirstSpEdit=false;
		}
	}
	
	private void Refresh(boolean bAll)
	{
		if (CboExportformat!=null) CboExportformat.fillBegriffTable("Auswertformat",true);        
		if (CboAnzeigeart!=null) CboAnzeigeart.fillBegriffTable("Anzeigeart",true);
		if (CboErgebnis!=null) CboErgebnis.fillBegriffTable("Ergebnisart",true);
		if (CboAusrichtung!=null) CboAusrichtung.fillBegriffTable("Alignment",true);
		if (CboMass1!=null) CboMass1.fillStammBitTable(Global.cstEinheit,true);
		if (CboMass2!=null) CboMass2.fillMass(g.getStunde(),true);   
		if (CboMass3!=null) CboMass3.fillWaehrung(true,true);
    // g.fixtestError("refresh CboSub1-3");
    if (CboSub1 != null) CboSub1.init();
    if (CboSub2 != null) CboSub2.init();
    if (CboSub3 != null) CboSub3.init();
		if (bAll)
			fillEig();
	}
	
	// private void setPnl(JPanel PnlSub,String sSub,int iSub)
	// {
	// 	if (iSub==0 || sSub==null)
	// 		PnlSub.removeAll();// .add("Center",new JLabel());
	// 	else if (sSub.equals("Frame"))
	// 	{
	// 		CboSubFom.Cbo.setSelectedAIC(iSub);
	// 		PnlSub.add("Center",CboSubFom);
	// 	}
	// 	else if (sSub.equals("Modell"))
	// 	{
	// 		CboSubMod.setSelectedAIC(iSub);
	// 		PnlSub.add("Center",CboSubMod);
	// 	}
	// 	else if (sSub.equals("Abfrage"))
	// 	{
	// 		CboSubAbf.setSelectedAIC(iSub);
	// 		PnlSub.add("Center",CboSubAbf);
	// 	}
	// 	else if (sSub.equals("Druck"))
	// 	{
	// 		CboSubPnt.setSelectedAIC(iSub);
	// 		PnlSub.add("Center",CboSubPnt);
	// 	}
	// 	else if (sSub.equals("Grafik"))
	// 	{
	// 		CboSubDia.setSelectedAIC(iSub);
	// 		PnlSub.add("Center",CboSubDia);
	// 	}
	// }

  private void fillSubSpalte()
  {
    CboSubSpalte.Clear();
    JCVector Vec=A.NodSpalten.getChildren();
    for (int i=0;i<Vec.size();i++)
    {
      JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
      //int iPos=A.TabSp.getPos("Nummer",Gid.getSelectedNode().getUserData());
      //if (iPos>=0 && !A.TabSp.isNull(iPos, "Aic"))
      CboSubSpalte.addItem((String)Nod.getLabel(), (int)Nod.getUserData(), null);
    }
    CboSubSpalte.setKein(true);
    CboSubSpalte.Sort();
  }

	private void SpalteBearbeiten()
	{
		//g.progInfo("SpalteBearbeiten");
		if (A.TabSp.posInhalt("Nummer",Gid.getSelectedNode().getUserData()))
		{
                  //A.TabSp.showGrid("TabSp");
//			g.fixtestError("SpalteBearbeiten "+A.TabSp.getInhalt("Vec"));
			Vector VecSp=(Vector)A.TabSp.getInhalt("Vec");
			int iPosE=g.TabEigenschaften.getPos("Aic",Abfrage.getEig(VecSp.lastElement()));
			String sDatentyp = g.TabEigenschaften.getS(iPosE,"Datentyp");
//			g.fixtestError("-> Pos="+iPosE+", Dt="+sDatentyp);
			//g.progInfo("SpalteBearbeiten:"+sDatentyp);
			//g.progInfo("checkFirstSpaltenEdit");
			checkFirstSpaltenEdit();
                        int i=A.TabSp.getI("bits");
                        int i2=A.TabSp.getI("bits2");
                        int i3=A.TabSp.getI("bits3");
			final JCheckBox CbxSpLeer= getCheckboxM("SpLeer",Abfrage.is(i,Abfrage.cstSpLeer));
			final SpinBox SBoSortiert= new SpinBox();
			final JCheckBox CbxSortdesc= getCheckboxM("DESC",Abfrage.is(i,Abfrage.cstSortDesc));
			final Zahl EdtLaenge = new Zahl(0);
			final Zahl EdtLaengeD = new Zahl(0);
			final Zahl EdtLaengeB = new Zahl(0);
			final Zahl EdtLaengeW = new Zahl(0);
			final Zahl EdtLaengeN = new Zahl(0);
			final JCheckBox CbxAnzeigen = getCheckboxM("Anzeigen",Abfrage.is(i,Abfrage.cstAnzeigen));
			final JCheckBox CbxEditierbar= getCheckboxM("Editierbar",Abfrage.is(i,Abfrage.cstEditierbar));
			final JCheckBox CbxGruppiert= getCheckboxM("Gruppiert",Abfrage.is(i,Abfrage.cstGruppiert));
			final JCheckBox CbxPeriode= getCheckboxM("Periode2",Abfrage.is(i,Abfrage.cstPeriode));
			final JCheckBox CbxPeriodensumme= getCheckboxM("Periodensumme",Abfrage.is(i,Abfrage.cstPeriodensumme));
			final JCheckBox CbxPositiv= getCheckboxM("nur positive Werte",Abfrage.is(i,Abfrage.cstPositiv));
			final JCheckBox CbxEindeutig = getCheckboxM("Eindeutig",Abfrage.is(i,Global.cstEindeutig*0x10000));
			final JCheckBox CbxAlways = getCheckboxM("Always",Abfrage.is(i,Global.cstAlways*0x10000));
			final JCheckBox CbxKeinAutoDate = getCheckboxM("kein_vorfuellen",Abfrage.is(i,Abfrage.cstKeinAutoDate));

			final JCheckBox CbxSperre= getCheckboxM("Sperre",Abfrage.is(i,Abfrage.cstSperre));
			final JCheckBox CbxHochkomma= getCheckboxM("Hochkomma",Abfrage.is(i,Abfrage.cstHochkomma));
			final JCheckBox CbxUnsichtbar= getCheckboxM("Unsichtbar",Abfrage.is(i,Abfrage.cstUnsichtbar));
                        final JRadioButton RadLeer = getRadiobuttonM("leer");
			final JRadioButton RadNimmSync= getRadiobuttonM("Sync");
                        final JRadioButton RadLast= getRadiobuttonM("last");
                        final JRadioButton RadLimit= getRadiobuttonM("limit");
			final JCheckBox CbxSteuern= getCheckboxM("Steuern",Abfrage.is(i,Abfrage.cstSteuern));
			final JCheckBox CbxAutoInc= getCheckboxM("AutoInc",Abfrage.is(i,Abfrage.cstAutoInc));
			final JCheckBox CbxBild= getCheckboxM("Bild",Abfrage.is(i,Abfrage.cstBild));
			final JCheckBox CbxSpVerteiler= getCheckboxM("Verteiler",Abfrage.is(i,Abfrage.cstSpVerteiler));
			final JCheckBox CbxGueltig= getCheckboxM("Gueltig",Abfrage.is(i,Abfrage.cstGueltig));
                        final JCheckBox CbxGueltig2= getCheckboxM("Gueltig2",Abfrage.is(i,Abfrage.cstGueltig2));
                        final JRadioButton RadCombo= getRadiobuttonM("Combobox");
                        final JRadioButton RadRadio= getRadiobuttonM("Radiobutton");
                        final JRadioButton RadButtons= getRadiobuttonM("Button");
                        final JRadioButton RadCombo2= getRadiobuttonM("Combobox2");
                        final JCheckBox CbxSpRefresh= getCheckboxM("Refresh",Abfrage.is(i,Abfrage.cstSpRefresh));
                        final JCheckBox CbxMobil= getCheckboxM("Mobil",Abfrage.is(i,Abfrage.cstMobil));
                        final JCheckBox CbxKeineEinheit= getCheckboxM("keine_Einheit",Abfrage.is(i,Abfrage.cstKeineEinheit));
                      //--- bits2
                        final JCheckBox CbxSpGMT= getCheckboxM("GMT",Abfrage.is(i2,Abfrage.cstSpGMT));
                        final JCheckBox CbxSpNotSave= getCheckboxM("not_save",Abfrage.is(i2,Abfrage.cstSpNotSave));
                        final JCheckBox CbxKeineGSumme= getCheckboxM("keine_Gesamtsumme",Abfrage.is(i2,Abfrage.cstKeineGSumme));
                        final JCheckBox CbxKeine1Summe= getCheckboxM("keine_Hauptsumme",Abfrage.is(i2,Abfrage.cstKeine1Summe));
                        final JCheckBox CbxSpSort= getCheckboxM("nachsortieren",Abfrage.is(i2,Abfrage.cstSpSort));
                        final JCheckBox CbxKeinOhr= getCheckboxM("kein_Ohr",Abfrage.is(i2,Abfrage.cstKeinOhr));
                        final JCheckBox CbxImportBed= getCheckboxM("Importbedingung",Abfrage.is(i2,Abfrage.cstImportBed));
                        final JCheckBox CbxKumuliert= getCheckboxM("kumuliert",Abfrage.is(i2,Abfrage.cstKumuliert));
                        final JCheckBox CbxAbNeujahr= getCheckboxM("ab_Neujahr",Abfrage.is(i2,Abfrage.cstAbNeujahr));
                        final JCheckBox CbxFett= getCheckboxM("Z_Schrift",Abfrage.is(i2,Abfrage.cstFett));
                        final JCheckBox CbxKeineLeerzeilen= getCheckboxM("keine_Leerzeilen",Abfrage.is(i2,Abfrage.cstKeineLeerzeilen));
                        final JCheckBox CbxVorZR= getCheckboxM("VorZR",Abfrage.is(i2,Abfrage.cstVorZR));
                        final JCheckBox CbxErgaenzen= getCheckboxM("angleichen",Abfrage.is(i2,Abfrage.cstErgaenzen));
                        final JCheckBox CbxFett2= getCheckboxM("S_Schrift",Abfrage.is(i2,Abfrage.cstFett2));
                        final JCheckBox CbxNurErste= getCheckboxM("nur_Erste",Abfrage.is(i2,Abfrage.cstNurErste));
                        final JCheckBox CbxSpGleiche= getCheckboxM("gleiche Spalte",Abfrage.is(i2,Abfrage.cstSpGleiche));
                        final JCheckBox CbxStichtag= getCheckboxM("Stichtag",Abfrage.is(i2,Abfrage.cstStichtag));
                        //final JCheckBox CbxbeiStichtag= getCheckboxM("bei Stichtag",Abfrage.is(i2,Abfrage.cstbeiStichtag));
                        final JCheckBox CbxAIC_Bez= getCheckboxM("AIC_Bez",Abfrage.is(i2,Abfrage.cstAIC_Bez));
                        final JCheckBox CbxBuchZahl= getCheckboxM("keine_Sonderzeichen",Abfrage.is(i2,Abfrage.cstBuchZahl));
                        final JCheckBox CbxZiffern= getCheckboxM("nur_Ziffern",Abfrage.is(i2,Abfrage.cstZiffern));
                        // final JCheckBox CbxUmbruch= getCheckboxM("Umbruch",Abfrage.is(i2,Abfrage.cstUmbruch));
                        final JCheckBox CbxKeinCG= getCheckboxM("kein_CompressGroup",Abfrage.is(i2,Abfrage.cstKeinCG));
                        final JCheckBox CbxSpKeinDel= getCheckboxM("kein_Spalte-Entfernen",Abfrage.is(i2,Abfrage.cstSpKeinDel));
                        final JCheckBox CbxSetSync= getCheckboxM("set_Sync",Abfrage.is(i2,Abfrage.cstSetSync));
                        final JCheckBox CbxBedZwang= getCheckboxM("bedingt_zwingend",Abfrage.is(i2,Abfrage.cstBedZwang));
                        final JCheckBox CbxNegativRot= getCheckboxM("negativ_Rot",Abfrage.is(i2,Abfrage.cstNegativRot));
                        final JCheckBox CbxMulti= getCheckboxM("Multiselect2",Abfrage.is(i2,Abfrage.cstMulti));
                        //final JCheckBox CbxOffen= getCheckboxM("nicht gesperrt",Abfrage.is(i2,Abfrage.cstOffen));
                        final JCheckBox CbxOrigEinh= getCheckboxM("Original-Einheit",Abfrage.is(i2,Abfrage.cstOrigEinh));
                        final JCheckBox CbxGanzesJahr= getCheckboxM("ganzes_Jahr",Abfrage.is(i2,Abfrage.cstGanzesJahr));
                        // final JCheckBox CbxDtJoker= getCheckboxM("Datumsjoker",Abfrage.is(i2,Abfrage.cstDtJoker));
                        final JCheckBox CbxKeinKnoten= getCheckboxM("keinKnoten",Abfrage.is(i2,Abfrage.cstKeinKnoten));
                      //--- bits3
                        final JCheckBox CbxBool= getCheckboxM("Boolean",Abfrage.is(i3,Abfrage.cstBool));
                        final JCheckBox CbxUG= getCheckboxM("UG",Abfrage.is(i3,Abfrage.cstUG));
                        final JCheckBox CbxFilterOK= getCheckboxM("FilterOK",Abfrage.is(i3,Abfrage.cstFilterOK));
                        final JCheckBox CbxSpKeinTitel= getCheckboxM("kein_Titel",Abfrage.is(i3,Abfrage.cstSpKeinTitel));
                        final JCheckBox CbxHH_mm= getCheckboxM("HH_mm",Abfrage.is(i3,Abfrage.cstHH_mm));
                        final JCheckBox CbxHtml= getCheckboxM("Html",Abfrage.is(i3,Abfrage.cstHtml));
                        final JCheckBox CbxInStunden= getCheckboxM("in_Stunden",Abfrage.is(i3,Abfrage.cstInStunden));
                        final JCheckBox CbxBarcode= getCheckboxM("Barcode",Abfrage.is(i3,Abfrage.cstBarcode));
                        final JCheckBox CbxNoSubFom= getCheckboxM("keinSubformular",Abfrage.is(i3,Abfrage.cstNoSubFom));
                        // final JCheckBox CbxSS= getCheckboxM("SaveSoon",Abfrage.is(i3,Abfrage.cstSaveSoon));
                        final JCheckBox CbxNoFav= getCheckboxM("keinFav",Abfrage.is(i3,Abfrage.cstNoFav));
                        final JCheckBox CbxZusatz= getCheckboxM("Zusatzspalte",Abfrage.is(i3,Abfrage.cstZusatz));
                        final JCheckBox CbxKNZW= getCheckboxM("KNZW",Abfrage.is(i3,Abfrage.cstKNZW));
                        // final JCheckBox CbxDetail= getCheckboxM("Detail",Abfrage.is(i3,Abfrage.cstDetail));
                        final JCheckBox CbxNotUser= getCheckboxM("nicht_Benutzer",Abfrage.is(i3,Abfrage.cstNotUser));
                        final JCheckBox CbxFarbzeile= getCheckboxM("Farbzeile",Abfrage.is(i3,Abfrage.cstColorRow));
                        final JCheckBox CbxPerSchnitt= getCheckboxM("Periodenschnitt",Abfrage.is(i3,Abfrage.cstPerSchnitt));
                        final JCheckBox CbxGruppierbar= getCheckboxM("Gruppierbar",Abfrage.is(i3,Abfrage.cstGruppierbar));
                        final JCheckBox CbxAuto= getCheckboxM("Automatisch",Abfrage.is(i3,Abfrage.cstAuto));
                        final JCheckBox CbxTabEin= getCheckboxM("TabEin",Abfrage.is(i3,Abfrage.cstTabEin));
                        final JCheckBox CbxFullScr= getCheckboxM("FullScreen",Abfrage.is(i3,Abfrage.cstFullScr));
                        final JCheckBox CbxPDF= getCheckboxM("PDF-Viewer",Abfrage.is(i3,Abfrage.cstPdfViewer));
                        final JCheckBox CbxInPlace= getCheckboxM("InPlace",Abfrage.is(i3,Abfrage.cstInPlace));
                        final JCheckBox CbxSuche= getCheckboxM("Suche",Abfrage.is(i3,Abfrage.cstSuche));
                        final JCheckBox CbxFilter2= getCheckboxM("Filter2",Abfrage.is(i3,Abfrage.cstFilter2));
                        final JCheckBox CbxFilterInit= getCheckboxM("FilterInit",Abfrage.is(i3,Abfrage.cstFilterInit));
                        final JRadioButton RadAuto=g.getRadiobutton("Auto3");
                        final JRadioButton RadCho=g.getRadiobutton("Cho"); // ChoiceBox
                        final JRadioButton RadCbo=g.getRadiobutton("Cbo"); // ComboBox
                        final JRadioButton RadRad=g.getRadiobutton("Rad"); // Radiobuttons
                        final JRadioButton RadBtn=g.getRadiobutton("Btn");
                        final JRadioButton RadSbo=g.getRadiobutton("Sbo");
                        final JRadioButton RadPop=g.getRadiobutton("Pop"); // PopOver
                        final JRadioButton RadCbx=g.getRadiobutton("Cbx");
                        final JRadioButton RadSwb=g.getRadiobutton("Swb");
                        final JRadioButton RadAuC=g.getRadiobutton("AutoComplete");
                        final JRadioButton RadDT=g.getRadiobutton("DT");
                        final JRadioButton RadTime=g.getRadiobutton("Time");
                        final JRadioButton RadSlider=g.getRadiobutton("Slider");
                        final JRadioButton RadTitel=g.getRadiobutton("WebTitel");
                        ButtonGroup groupEE = new ButtonGroup();
                        groupEE.add(RadAuto);
                        groupEE.add(RadCho);
                        groupEE.add(RadCbo);
                        groupEE.add(RadRad);
                        groupEE.add(RadBtn);
                        groupEE.add(RadSbo);
                        groupEE.add(RadPop);
                        groupEE.add(RadCbx);
                        groupEE.add(RadSwb);
                        groupEE.add(RadAuC);
                        groupEE.add(RadDT);
                        groupEE.add(RadTime);
                        groupEE.add(RadSlider);
                        groupEE.add(RadTitel);
                        RadAuto.setSelected((i3 & Global.cstEE) == Global.cstEigAuto);
                        RadCho.setSelected((i3 & Global.cstEE) == Global.cstEigCho);
                        RadCbo.setSelected((i3 & Global.cstEE) == Global.cstEigCbo);
                        RadRad.setSelected((i3 & Global.cstEE) == Global.cstEigRad);
                        RadBtn.setSelected((i3 & Global.cstEE) == Global.cstEigBtn);
                        RadSbo.setSelected((i3 & Global.cstEE) == Global.cstEigSbo);
                        RadPop.setSelected((i3 & Global.cstEE) == Global.cstEigPop);
                        RadCbx.setSelected((i3 & Global.cstEE) == Global.cstEigCbx);
                        RadSwb.setSelected((i3 & Global.cstEE) == Global.cstEigSwb);
                        RadAuC.setSelected((i3 & Global.cstEE) == Global.cstEigAuC);
                        RadDT.setSelected((i3 & Global.cstEE) == Global.cstEigDT);
                        RadTime.setSelected((i3 & Global.cstEE) == Global.cstEigTime);
                        RadSlider.setSelected((i3 & Global.cstEE) == Global.cstEigSlider);
                        RadTitel.setSelected((i3 & Global.cstEE) == Global.cstEigWebTitel);

                        final JRadioButton RadDefDaten=g.getRadiobutton("Default_Daten");
                        final JRadioButton RadNurAicD=g.getRadiobutton("nurAicDaten");
                        final JRadioButton RadDanachD=g.getRadiobutton("danachDaten");
                        ButtonGroup groupDH = new ButtonGroup();
                        groupDH.add(RadDefDaten);
                        groupDH.add(RadNurAicD);
                        groupDH.add(RadDanachD);
                        if ((i3&Abfrage.cstDatenHolen)==Abfrage.cstNurAicD)
                          RadNurAicD.setSelected(true);
                        else if ((i3&Abfrage.cstDatenHolen)==Abfrage.cstDanachD)
                          RadDanachD.setSelected(true);
                        else
                          RadDefDaten.setSelected(true);
//                        final JRadioButton RadGrKein=g.getRadiobutton("GrKein");
//                        final JRadioButton RadGrX=g.getRadiobutton("GrX");
//                        final JRadioButton RadGrY=g.getRadiobutton("GrY");
//                        ButtonGroup groupGr = new ButtonGroup();
//                        groupGr.add(RadGrKein);
//                        groupGr.add(RadGrX);
//                        groupGr.add(RadGrY);
//                        RadGrKein.setSelected((i3 & Abfrage.cstGrafik) == 0);
//                        RadGrX.setSelected((i3 & Abfrage.cstGrafik) == Abfrage.cstGrX);
//                        RadGrY.setSelected((i3 & Abfrage.cstGrafik) == Abfrage.cstGrY);
                        
			final AUTextArea MemSpalte = new AUTextArea(g, 3);
      MemSpalte.setMaxLength(4000);
			final Zahl EdtFaktor = new Zahl(0.0);
                        final Zahl EdtMin = new Zahl(0.0);
                        final Zahl EdtMax = new Zahl(0.0);
                        final Zahl EdtX = new Zahl(0);
                        final Zahl EdtY = new Zahl(0);
                        final Zahl EdtW = new Zahl(0);
                        final Zahl EdtH = new Zahl(0);
                        final Text EdtStil=new Text("",30,Text.KENNUNG);
                        final Text EdtTS=new Text("",20,Text.KENNUNG); // ToggleSight
                        final Text EdtWD=new Text("",20,Text.KENNUNG); // WebDarstellung
                        final Text EdtStep=new Text("",2,Text.ZAHL);  // Step
                        final Text EdtIcon=new Text("",30,Text.KENNUNG);
                        final Text EdtFarbe=new Text("",30,Text.KENNUNG);
			final JButton BtnGruppe=new JButton(""+(A.TabSp.isNull("Gruppe")?0:((Vector)A.TabSp.getInhalt("Gruppe")).size()));
			final JButton BtnCalc=new JButton(A.TabSp.isNull("Calc")?"*":""+((Tabellenspeicher)A.TabSp.getInhalt("Calc")).getAnzahlElemente(null));
                        //final AUFarbe EdtFarbe = new AUFarbe( (JFrame) thisFrame, g, 0);
                        final ComboSort CboFarbe=new ComboSort(g);
                        CboFarbe.fillFarbe(true);
      if (CboSubSpalte==null)
        CboSubSpalte=new ComboSort(g);
      fillSubSpalte();
      CboSubSpalte.setSelectedAIC(getSubSpalte(false));
			//g.progInfo("nach Variablen");
			bCboFilter=false;
			EdtLaenge.setMax(500);
			EdtLaengeD.setMax(1000);
			EdtLaengeB.setMax(1000);
			EdtLaengeW.setMax(1000);
			EdtLaengeN.setMax(3);
			MemSpalte.setText(A.TabSp.getM("TT"));
			EdtSpBezO.setEditable(false);
			EdtSpBezO.setText(A.TabSp.getS("Bezeichnung"));
			if (g.Def())
			{
				EdtSpBez1.setEditable(false);
				EdtSpBez2.setEditable(false);			
				EdtSpBez1.setText(Abfrage.getEigenschaftBezeichnung(g, VecSp));
				EdtSpBez2.setText(Abfrage.getEigenschaftBezeichnung(g, VecSp,Abfrage.iSprache));
			}
			EdtBezeichnung.setText(A.TabSp.getS("Bez2"));
			EdtKennungS.setText(A.TabSp.getS("Kennung"));
      EdtKennungS.setEditable(true);

			SBoSortiert.setIntValue(A.TabSp.getI("Sortiert"));
			CboExportformat.setSelectedAIC(A.TabSp.getI("Aic_Code"));
			CboMass = sDatentyp.endsWith("Waehrung") ? CboMass3:
                            sDatentyp.equals("BewVon_Bis") || sDatentyp.equals("CalcDauer") ? CboMass2:CboMass1;
			CboMass.setSelectedAIC(A.TabSp.getI("Mass"));
                        CboFarbe.setSelectedAIC(A.TabSp.getI("aic_farbe"));
			CboAusrichtung.setSelectedAIC(A.TabSp.getI("Ausrichtung"));
                        CboAnzeigeart.setSelectedAIC(A.TabSp.getI("Anzeige"));
			CboErgebnis.setSelectedAIC(A.TabSp.getI("Cod_Aic_Code"));
			// nur entsprechendes setzen, anderes aud <kein>
			int iSub1=A.TabSp.getI("Sub1");
			int iSub2=A.TabSp.getI("Sub2");
			int iSub3=A.TabSp.getI("Sub3");
//			int iPosSF=g.TabBegriffe.getPos("Aic",iSubFom);
//        	String sGruppe=iPosSF<0 ? "":g.TabBegriffgruppen.getKennung(g.TabBegriffe.getI(iPosSF,"Gruppe"));
//        	CboSubFom.setSelectedAIC(0);
//        	CboSubAbf.setSelectedAIC(0);
//        	if (iSubFom==0)
//        	{
//        		CboSubFom.setEnabled(true);
//        		CboSubAbf.setEnabled(true);
//        	}
//        	else
//        	{
//        		CboSubFom.setEnabled(!sGruppe.equals("Abfrage"));
//        		CboSubAbf.setEnabled(sGruppe.equals("Abfrage"));
//	        	  if (sGruppe.equals("Frame"))
//	        		CboSubFom.setSelectedAIC(iSubFom);
//	        	  else if (sGruppe.equals("Abfrage"))      
//	        		CboSubAbf.setSelectedAIC(iSubFom);
//        	}

			// String sSub1=Abfrage.getSubBG(g,A.TabSp,1);
			// if (sSub1 != null)
			// 	CboSub1.setSelectedKennung(sSub1);
			// else
			// 	CboSub1.setSelectedAIC(0);
			// setPnl(PnlSub1,sSub1,iSub1);
			// String sSub2=Abfrage.getSubBG(g,A.TabSp,2);
			// if (sSub2 != null)
			// 	CboSub2.setSelectedKennung(sSub2);
			// else
			// 	CboSub2.setSelectedAIC(0);
			// setPnl(PnlSub2,sSub2,iSub2);
			// String sSub3=Abfrage.getSubBG(g,A.TabSp,3);
			// if (sSub3 != null)
			// 	CboSub3.setSelectedKennung(sSub3);
			// else
			// 	CboSub3.setSelectedAIC(0);
			// setPnl(PnlSub3,sSub3,iSub3);
//        	CboSubFom.setSelectedAIC(iSub1);
//        	CboSubAbf.setSelectedAIC(iSub2);
//        	CboSubMod.setSelectedAIC(iSub3);
			EdtLaenge.setValue(Abfrage.getLaenge(A.TabSp));
			EdtLaengeD.setValue(Abfrage.getLaengeD(A.TabSp));
			EdtLaengeB.setValue(Abfrage.getLaengeB(A.TabSp));
			EdtLaengeW.setValue(Abfrage.getLaengeW(A.TabSp));
			EdtLaengeN.setValue(Abfrage.getLaengeN(A.TabSp));
                        ButtonGroup RadNeu=new ButtonGroup();
                        RadNeu.add(RadLeer);
                        RadNeu.add(RadNimmSync);
                        RadNeu.add(RadLast);
                        RadNeu.add(RadLimit);
                        if ((i&Abfrage.cstNeu)==Abfrage.cstKeinAutoLast)
                          RadLeer.setSelected(true);
                        else if ((i&Abfrage.cstNeu)==Abfrage.cstNimmSync)
                          RadNimmSync.setSelected(true);
                        else if ((i&Abfrage.cstNeu)==Abfrage.cstLast)
                          RadLast.setSelected(true);
                        else if ((i&Abfrage.cstNeu)==Abfrage.cstLimit)
                          RadLimit.setSelected(true);

                        ButtonGroup RadShow=new ButtonGroup();
                        RadShow.add(RadCombo);
                        RadShow.add(RadRadio);
                        RadShow.add(RadButtons);
                        RadShow.add(RadCombo2);
                        if ((i&Abfrage.cstShow)==Abfrage.cstRadio)
                          RadRadio.setSelected(true);
                        else if((i&Abfrage.cstShow)==Abfrage.cstButtons)
                          RadButtons.setSelected(true);
                        else if((i&Abfrage.cstShow)==Abfrage.cstCombo2)
                          RadCombo2.setSelected(true);
                        else
                          RadCombo.setSelected(true);
                        EdtFormat.setTyp(sDatentyp,CboAnzeigeart.getSelectedKennung(),false);
			                  EdtFormat.setAIC(A.TabSp.getI("aic_begriff"));
                        EdtFaktor.setValue(A.TabSp.getF("Faktor"));
                        EdtMin.setValue(A.TabSp.getF("Min"));
                        EdtMax.setValue(A.TabSp.getF("Max"));
                        EdtX.setValue(A.TabSp.getI("x"));
                        EdtY.setValue(A.TabSp.getI("y"));
                        EdtW.setValue(A.TabSp.getI("w"));
                        EdtH.setValue(A.TabSp.getI("h"));
                        EdtStil.setText(A.TabSp.getS("Stil"));
                        String sTS=A.TabSp.getS("ToggleSight");
                        if (sTS==null || sTS.indexOf(';')==-1)
                        {
                          EdtTS.setText(sTS);
                          EdtWD.setText("");
                          EdtStep.setText("");
                        }
                        else
                        {
                          String[] sAryStrings= sTS.split(";");
                          EdtTS.setText(sAryStrings[0]);
                          if (sAryStrings.length>1)
                            EdtStep.setText(sAryStrings[1]);
                          if (sAryStrings.length>2)
                            EdtWD.setText(sAryStrings[2]);
                        }
                        EdtIcon.setText(A.TabSp.getS("Icon"));
                        EdtFarbe.setText(A.TabSp.getS("Farbe"));
                        thisJFrame().setEnabled(false);
			Dlg = new JDialog((JFrame)thisFrame,g.getBegriffS("Dialog","Spalte")+" "+Abfrage.getEigenschaftBezeichnung(g,VecSp)+(g.Def()?" | "+sDatentyp:""),false);//,bEdit);
			Dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      final AUEkitCore MemSAllg = new AUEkitCore(g,Dlg,7);
      MemSAllg.setMaxLength(4000);
			if (sMemoSpalte==null)
				sMemoSpalte=SQL.getString(g, "select memo from daten_memo where aic_tabellenname=1 and aic_sprache=1 and aic_fremd="+iTab_Spalte);
			MemSAllg.setText(sMemoSpalte);
      CboSub1.setAic(iSub1,Abfrage.getSubBG(g,A.TabSp,1),Dlg);
      CboSub2.setAic(iSub2,Abfrage.getSubBG(g,A.TabSp,2),Dlg);
      CboSub3.setAic(iSub3,Abfrage.getSubBG(g,A.TabSp,3),Dlg);
			JPanel PnlOben = new JPanel(new BorderLayout());
			JPanel PnlCbx = new JPanel(new GridLayout(0,4));
      JPanel PnlWebG = new JPanel(new BorderLayout());
      JPanel PnlWebN = new JPanel(new BorderLayout());
      JPanel PnlWebW = new JPanel(new GridLayout(0,1,2,2));
      JPanel PnlWebC = new JPanel(new GridLayout(0,1,2,2));
			JPanel PnlWeb = new JPanel(new GridLayout(0,4));
      PnlWebN.add("West",PnlWebW);
      PnlWebN.add("Center",PnlWebC);
      PnlWebG.add("North",PnlWebN);
      PnlWebG.add("Center",PnlWeb);
				JPanel PnlText = new JPanel(new GridLayout(0,1,2,2));
				JPanel PnlAuswahl = new JPanel(new GridLayout(0,1,2,2));
				if (g.Def())
				{
					g.addLabel4(PnlText,"Original");
					 JPanel PnlOri=new JPanel(new GridLayout(1,0,2,2));
					 PnlOri.add(EdtSpBezO);
					 PnlOri.add(EdtSpBez1);
					PnlAuswahl.add(PnlOri);
				}
				else if (EdtBezeichnung.isNull())
					EdtBezeichnung.setText(EdtSpBezO.getText());
                                g.addLabel4(PnlText,"Bezeichnung");
				//PnlText.add(new JLabel(g.getBegriffS("Show","Bezeichnung")+':'));
                if (g.Def())
				{
					 JPanel PnlBez=new JPanel(new GridLayout(1,0,2,2));
					 PnlBez.add(EdtBezeichnung);
					 PnlBez.add(EdtSpBez2);
					PnlAuswahl.add(PnlBez);
				}
                else
                	PnlAuswahl.add(EdtBezeichnung);
                                //g.addLabel(PnlText,g.getBegriffS("Show","Kennung"));
				//PnlText.add(new JLabel(g.getBegriffS("Show","Kennung")+':'));
				//PnlAuswahl.add(EdtKennung);

                                g.addLabel4(PnlText,"Sortiert");
                                JPanel PnlAuswahl2=new JPanel(new GridLayout(1,4,5,0));
                                if (sDatentyp.startsWith("Calc") && !sDatentyp.equals("CalcBezeichnung"))
                                {
                                  PnlAuswahl2.add(new JLabel());
                                  //PnlAuswahl2.add(new JLabel());
                                }
                                else
                                {
                                  //PnlAuswahl2.add(SBoSortiert);
                                  //PnlAuswahl2.add(CbxSortdesc);
                                  g.addWCE(PnlAuswahl2, SBoSortiert, CbxSortdesc, null);
                                }
                                g.addLabel2(PnlAuswahl2,"Bereich");
                                //PnlAuswahl2.add(g.getLabelR("min"));
                                //PnlAuswahl2.add(EdtMin);
                                g.addWCE(PnlAuswahl2, getLabelR("min"), EdtMin, null);
                                //PnlAuswahl2.add(g.getLabelR("max"));
                                //PnlAuswahl2.add(EdtMax);
                                g.addWCE(PnlAuswahl2, getLabelR("max"), EdtMax, null);
                                PnlAuswahl.add(PnlAuswahl2);
                                g.addLabel4(PnlText,"Position");
                                PnlAuswahl2=new JPanel(new GridLayout(1,4,5,0));
                                g.addWCE(PnlAuswahl2, getLabelR("x"), EdtX, null);
                                g.addWCE(PnlAuswahl2, getLabelR("y"), EdtY, null);
                                g.addWCE(PnlAuswahl2, getLabelR("w"), EdtW, null);
                                g.addWCE(PnlAuswahl2, getLabelR("h"), EdtH, null);
                                PnlAuswahl.add(PnlAuswahl2);
                                
                                g.addLabel4(PnlText,"Ergebnis");
                                PnlAuswahl2=new JPanel(new GridLayout(1,6,5,0));
				PnlAuswahl2.add(CboErgebnis);
                                PnlAuswahl2.add(g.Def() ? (JComponent)CboAnzeigeart:new JLabel());
                                g.addLabel2(PnlAuswahl2,"Ausrichtung");
                                PnlAuswahl2.add(CboAusrichtung);
                                g.addLabel(PnlAuswahl2, "Faktor",EdtFaktor,"0");
                                //g.addLabel2(PnlAuswahl2,"Faktor");
                                //PnlAuswahl2.add(EdtFaktor);
				PnlAuswahl.add(PnlAuswahl2);

				//PnlText.add(new JLabel(g.getBegriffS("Show","Zeichen")+':'));
				//PnlAuswahl.add(EdtLaenge);
                                g.addLabel4(PnlText,"Zeichen");
                                PnlAuswahl2=new JPanel(new GridLayout(1,8,5,0));
                                PnlAuswahl2.add(EdtLaenge);
                                PnlAuswahl2.add(g.Def() ? (JComponent)CboExportformat:new JLabel());
                                g.addLabel2(PnlAuswahl2,"Druck");
                                PnlAuswahl2.add(EdtLaengeD);
                                g.addLabel2(PnlAuswahl2,"Bildschirm");
                                PnlAuswahl2.add(EdtLaengeB);
                                g.addLabel2(PnlAuswahl2,"Nr");
                                PnlAuswahl2.add(EdtLaengeN);
                                PnlAuswahl.add(PnlAuswahl2);                                
                                
	                                g.addLabel4(PnlText,"Format");
	                                PnlAuswahl2=new JPanel(new GridLayout(1,2,5,0));
	                                PnlAuswahl2.add(EdtFormat);
	                                PnlAuswahl.add(PnlAuswahl2);
	                                
                                
                                if (sDatentyp.equals("BewVon_Bis") || sDatentyp.endsWith("Mass") || sDatentyp.equals("CalcDauer") || sDatentyp.endsWith("Waehrung"))
                                {
                                  g.addLabel(PnlAuswahl2,"Mass",CboMass,"0");
                                  /*JPanel PnlMass=new JPanel(new BorderLayout());
                                        g.addLabel2(PnlAuswahl2,"Mass");
                                        PnlAuswahl2.add(CboMass);
                                  PnlAuswahl2.add(PnlMass);*/
                                }
                                if (sDatentyp.endsWith("Hierarchie") || Tabellenspeicher.geti(((Vector)A.TabSp.getInhalt("Vec")).firstElement())<0)
                                {
                                  g.addLabel2(PnlAuswahl2,sDatentyp.equals("Hierarchie") ? "Hierarchie":"Stammtyp");
                                  if (sDatentyp.endsWith("Hierarchie"))
                                    CboHierarchie.fillHierachieBis(g.TabEigenschaften.getI(iPosE,"aic_stammtyp"), iStammtyp<0?0:iStammtyp, true);
                                  else
                                    CboHierarchie.fillCbo(g.TabStammtypen,true);
                                  CboHierarchie.setSelectedAIC(A.TabSp.getI("aic_stammtyp"));
                                  if (iRel>0)
                                    CboRel.setSelectedAIC(0);
                                  PnlAuswahl2.add(CboHierarchie);
                                }
                                else
                                {
                                  CboHierarchie.setSelectedAIC(0);
                                  if (sDatentyp.endsWith("BewZahl2") || sDatentyp.endsWith("BewMass2") || sDatentyp.endsWith("BewWaehrung2")/* || CbxBedZwang.isSelected()*/)
                                  {
                                    int iNeu = g.TabEigenschaften.getI(iPosE,"aic_stammtyp");
                                    g.fixInfo(sDatentyp+":"+iNeu+"/"+iRel);
                                    if (iNeu != iRel)
                                      CboRel.fillStammTable(iNeu,true);
                                    CboRel.setSelectedAIC(A.TabSp.getI("Rel"));
                                    iRel=iNeu;
                                    PnlAuswahl2.add(CboRel);
                                  }
                                  else if (/*CbxBedZwang.isSelected()*/g.Def() && iStammtyp>0)
                                  {
                                    if (CboBed==null)
                                      CboBed= new StammEingabe(Dlg,g,0,-1);
                                    CboBed.useKennung(true);
                                    iRel=A.TabSp.getI("Rel");
                                    CboBed.setStamm2(iRel);
                                    PnlAuswahl2.add(CboBed);
                                    CboRel.setSelectedAIC(0);
                                  }
                                  else if (iRel>0)
                                    CboRel.setSelectedAIC(0);
                                }

				//PnlText.add(new JLabel(g.getBegriffS("Show","Format")+':'));

				//PnlText.add(new JLabel(g.getBegriffS("Show","Ausrichtung")+':'));
                                //g.testInfo("Prog="+g.Prog()+", Def="+g.Def()+", Spezial="+g.Spezial()+", HS="+A.isHS());
                                if (g.Def() && !A.isHS())
                                {
                                  if (sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe") || sDatentyp.endsWith("Bool3"))
                                  {
                                    PnlCbx.add(RadCombo);
                                    PnlCbx.add(RadRadio);
                                    PnlCbx.add(RadButtons);
                                    PnlCbx.add(RadCombo2);
                                  }
//                                  else if (sDatentyp.equals("Formular"))
//                                  {
//                                	  PnlCbx.add(new JLabel(g.getShow("SubFom")));
//                                	  PnlCbx.add(CboSubFom);
//                                	  PnlCbx.add(new JLabel(""));
//                                	  PnlCbx.add(new JLabel(""));
//                                  }
                                  PnlCbx.add(RadLeer);
                                  if (sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe") || sDatentyp.startsWith("BewDatum"))
                                    PnlCbx.add(RadNimmSync);
                                  else
                                    PnlCbx.add(new JLabel(""));
                                  PnlCbx.add(RadLast);
                                  PnlCbx.add(RadLimit);

                                  PnlCbx.add(CbxSpLeer);
                                  PnlCbx.add(CbxAutoInc);
                                  PnlCbx.add(CbxKeinAutoDate);
                                  PnlCbx.add(CbxAuto);
                                  PnlCbx.add(CbxGueltig);
                                  PnlCbx.add(CbxGueltig2);
                                  //PnlCbx.add(CbxDialog);
                                  PnlCbx.add(CbxSperre);
                                }

                                PnlCbx.add(CbxAnzeigen);
                                CbxAnzeigen.setEnabled(g.Def());
                                PnlCbx.add(CbxUnsichtbar);

                                if (!A.isHS())
                                {
                                  if (g.Def())
                                    PnlCbx.add(CbxEditierbar);
                                  if (g.Spezial())
                                    PnlCbx.add(CbxEindeutig);
                                }
                                if (g.Def())
                                {
                                  if (!A.isHS())
                                  {
					PnlCbx.add(CbxAlways);

					PnlCbx.add(CbxSpVerteiler);
					PnlCbx.add(CbxSteuern);
					PnlCbx.add(CbxSpRefresh);
                                  }
					//PnlText.add(new JLabel(g.getBegriffS("Show","kein vorfuellen")+':'));
					//PnlAuswahl.add(CbxKeinAutoDate);
					int iStt2=sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe") ? ((Integer)A.VecToStt((Vector)A.TabSp.getInhalt("Vec"))).intValue():A.iStt;
					//{
						bCboFilter=true;
                                                //g.progInfo("Filter:"+A.iStt+"/"+iStt2+"/"+A.iBew);
						CboFilter.fillCbo("select aic_abfrage,kennung,DefBezeichnung Bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("bits",Abfrage.cstFilter)+" and"+g.not("begriff.bits",Abfrage.cstBerechtigung)
                                                    /*(A.TabSp.isNull("Gruppe") && (A.iBew==0 && !sDatentyp.equals("Hierarchie") || iStt2 != A.iStt) ? " and aic_stammtyp="+iStt2:"")*/,"Abfrage",true);
						CboFilter.setSelectedAIC(A.TabSp.getI("Filter"));
                                                g.addLabel4(PnlText,"Filter");
                                                JPanel PnlFilter = new JPanel(new BorderLayout());
                                                PnlFilter.add("Center",CboFilter);
                                                JButton Btn=g.getButton("*");
                                                PnlFilter.add("East",Btn);
                                                Btn.addActionListener(new ActionListener()
                                                {
                                                  public void actionPerformed(ActionEvent ev)
                                                  {
                                                    new ShowAbfrage(g,CboFilter.getSelectedAIC(),Abfrage.cstAbfrage).showResult(Dlg);
                                                  }
                                                });
                                                PnlAuswahl2=new JPanel(new GridLayout(1,2,3,0));
                                                PnlAuswahl2.add(PnlFilter);
                                                //g.addLabel(PnlAuswahl2,"Farbe",CboFarbe,"0");
                                                g.addWCE(PnlAuswahl2, getLabelR("Farbe"), CboFarbe, null);
                                                //g.addWCE(PnlAuswahl2, g.getLabelR("KZ"), EdtStil, null);
                                                PnlAuswahl.add(PnlAuswahl2);
					//}
					boolean bText = sDatentyp.startsWith("String") || sDatentyp.equals("WWW") || sDatentyp.equals("Bezeichnung") || sDatentyp.equals("CalcBezeichnung") || sDatentyp.equals("Kennung") ||
						sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("E-Mail") || sDatentyp.equals("Pfad") || sDatentyp.equals("Filename");
					EdtFaktor.setEnabled(!sDatentyp.equals("BewStamm") && !sDatentyp.equals("Gruppe") && !sDatentyp.equals("BewHierarchie") && !sDatentyp.equals("Hierarchie") && !bText);
                                        //EdtMin.setEnabled(EdtFaktor.isEnabled());
                                        //EdtMax.setEnabled(EdtFaktor.isEnabled());
					/*{
						PnlText.add(new JLabel(g.getBegriffS("Show","Faktor")+':'));
						PnlAuswahl.add(EdtFaktor);
					}*/
				}
        else if (!g.Spezial())
        {
          EdtFaktor.setEnabled(false);
          EdtMin.setEnabled(false);
          EdtMax.setEnabled(false);
        }
				//PnlText.add(new JLabel(g.getBegriffS("Show","kein letzten Werte")+':'));
				//PnlText.add(new JLabel(g.getBegriffS("Show","Gruppiert")+':'));
				if (!A.isHS())
        {
          PnlCbx.add(CbxMulti);
          PnlCbx.add(CbxGruppiert);
          PnlCbx.add(CbxKeinKnoten);
          PnlCbx.add(CbxKeinCG);
        }
                                if (g.Spezial())
                                  PnlCbx.add(CbxPositiv);
                                if (!A.isHS())
                                  PnlCbx.add(CbxHochkomma);
                                PnlCbx.add(CbxBild);

                                PnlCbx.add(CbxSpGMT);
                                PnlCbx.add(CbxSpNotSave);
				PnlCbx.add(CbxKeineGSumme);
                                PnlCbx.add(CbxKeine1Summe);
                                PnlCbx.add(CbxSpSort);
                                if (g.Def())
                                {
                                  PnlCbx.add(CbxKeinOhr);
                                  if (!A.isHS())
                                    PnlCbx.add(CbxImportBed);
                                  PnlCbx.add(CbxKumuliert);
                                  PnlCbx.add(CbxAbNeujahr);
                                  PnlCbx.add(CbxFett);
                                  PnlCbx.add(CbxKeineLeerzeilen);
                                  PnlCbx.add(CbxVorZR);
                                  if (!A.isHS())
                                    PnlCbx.add(CbxErgaenzen);
                                  PnlCbx.add(CbxFett2);
                                  if (!A.isHS())
                                  {
                                    PnlCbx.add(CbxNurErste);
                                    PnlCbx.add(CbxSpGleiche);
                                    PnlCbx.add(CbxStichtag);
                                  }
                                  PnlCbx.add(new JLabel());
                                  if (!A.isHS())
                                  {
                                    PnlCbx.add(CbxBuchZahl);
                                    PnlCbx.add(CbxZiffern);

                                    //PnlCbx.add(CbxIsNull);
                                    //PnlCbx.add(CbxIsJoker);
                                    //PnlCbx.add(CbxIsJokerVec);
                                    PnlCbx.add(CbxBedZwang);
                                    PnlCbx.add(CbxNegativRot);
                                    
                                    PnlCbx.add(CbxSpKeinDel);
                                    
                                  }
                                  PnlCbx.add(CbxGanzesJahr);
                                  PnlCbx.add(CbxSetSync);
                                  //PnlCbx.add(CbxOffen);
//                                  PnlCbx.add(new JLabel());
//                                  PnlCbx.add(CbxOverflow);
//                                  PnlCbx.add(CbxLost);
//                                  PnlCbx.add(CbxEnter);
                                  PnlCbx.add(CbxSpKeinTitel);
                                  PnlCbx.add(CbxNotUser);
                                  PnlCbx.add(CbxHtml);
                                  PnlCbx.add(CbxOrigEinh);
                                  PnlCbx.add(CbxInStunden);
                                  PnlCbx.add(CbxKeineEinheit);
                                  PnlCbx.add(CbxKNZW);
                                  PnlCbx.add(CbxNoFav);
                                  
                                  g.addLabel4(PnlWebW,"Kennung");
                                  PnlWebC.add(g.add3(EdtKennungS,getLabelM("KZ"),EdtStil));
                                  // PnlWeb.add(EdtKennungS);                                
                                  // PnlWeb.add(getLabelM("KZ"));
                                  // PnlWeb.add(EdtStil);

                                  g.addLabel4(PnlWebW,"SubSpalte");
                                  PnlWebC.add(g.add3(CboSubSpalte,getLabelM("ToggleSight"),EdtTS));
                                  // g.fixtestError("Icon dazu");
                                  g.addLabel4(PnlWebW,"Icon");
                                  PnlWebC.add(g.add3(EdtIcon,getLabelM("Farbe"),EdtFarbe));
                                  // g.fixtestError("Step dazu");
                                  g.addLabel4(PnlWebW,"Step");
                                  PnlWebC.add(g.add3(EdtStep,getLabelM("WebDarstellung"),EdtWD));
                                  
                                  g.addLabel4(PnlWebW,"Webschirm");
                                  PnlWebC.add(g.add3(EdtLaengeW,new JLabel(),CbxUG));
                                  // PnlWeb.add(EdtLaengeW);
                                  // PnlWeb.add(new JLabel());
                                  // PnlWeb.add(new JLabel());
                                  
                                  if (sDatentyp.equals("Formular") || sDatentyp.equals("Abfrage") || sDatentyp.equals("Modell") || A.bWeb)//(A.lBits2&A.cstAbfWeb)>0)
                                  {                                
                                  	g.addLabel4(PnlWebW,"Sub1");      
                                    PnlWebC.add(CboSub1);                    
                                	  g.addLabel4(PnlWebW,"Sub2");      
                                    PnlWebC.add(CboSub2);                             
                                	  g.addLabel4(PnlWebW,"Sub3");   
                                  	PnlWebC.add(CboSub3);
                                  }
//                                  else if (sDatentyp.equals("Abfrage"))
//                                  {	
//                                	  PnlWeb.add(CbxUG);
//                                      PnlWeb.add(g.getLabel("SubAbf"));
//                                      PnlWeb.add(CboSubAbf);
//                                  }
//                                  else if (sDatentyp.equals("Modell"))
//                                  {	
//                                	  PnlWeb.add(CbxUG);
//                                      PnlWeb.add(g.getLabel("SubMod"));
//                                      PnlWeb.add(CboSubMod);
//                                  }
                                  PnlWeb.add(CbxZusatz);
                                  PnlWeb.add(CbxFarbzeile);
                                  PnlWeb.add(CbxGruppierbar);
                                  
                                  PnlWeb.add(CbxTabEin);
                                  PnlWeb.add(CbxFullScr);
                                  PnlWeb.add(CbxPDF);
                                  
                                  PnlWeb.add(CbxInPlace);
                                  PnlWeb.add(CbxBool);
                                  PnlWeb.add(CbxFilterOK);

                                  PnlWeb.add(CbxHH_mm);
                                  PnlWeb.add(CbxNoSubFom);
                                  PnlCbx.add(CbxBarcode);
                                  PnlWeb.add(CbxFilter2);
                                  
                                  PnlWeb.add(CbxAIC_Bez);
                                  PnlWeb.add(CbxMobil);
                                  PnlWeb.add(CbxFilterInit);
                                  
                                  PnlWeb.add(CbxSuche);     
                                  
                                  PnlWeb.add(getLabelM("Auswahlart"));
                                  PnlWeb.add(RadAuto);
                                  PnlWeb.add(RadTitel);
                                  PnlWeb.add(RadCbx);
                                  PnlWeb.add(RadSwb);                                 
                                  PnlWeb.add(RadCho);
                                  PnlWeb.add(RadCbo);
                                  PnlWeb.add(RadRad);
                                  PnlWeb.add(RadBtn);                                
                                  PnlWeb.add(RadSbo);
                                  PnlWeb.add(RadPop);
                                  PnlWeb.add(RadAuC);
                                  
                                  PnlWeb.add(RadDT);                                  
                                  PnlWeb.add(RadTime);
                                  PnlWeb.add(RadSlider);
                                  
                                  // PnlWeb.add(new JLabel());                                  
                                  // PnlWeb.add(new JLabel());
                                  // PnlWeb.add(new JLabel());
                                  
//                                  g.addLabel4(PnlWeb,"JavaFx");
//                                  PnlWeb.add(new JLabel());
//                                  PnlWeb.add(new JLabel());
//                                  PnlWeb.add(CbxZukunft);
//                                  PnlWeb.add(CbxPrompt);
//                                  PnlWeb.add(CbxBnoEdt);
//                                  
//                                  PnlWeb.add(new JLabel());
//                                  PnlWeb.add(new JLabel());
//                                  PnlWeb.add(new JLabel());
                                  
                                  // g.addLabel4(PnlWeb,"ohne_Funktion");
                                  // PnlWeb.add(new JLabel());
                                  // PnlWeb.add(new JLabel());
                                  // PnlWeb.add(CbxSS);
                                  
//                                  PnlCbx.add(g.getLabel("Diagramm"));
//                                  PnlCbx.add(RadGrKein);
//                                  PnlCbx.add(RadGrX);
//                                  PnlCbx.add(RadGrY);
                                  if (!A.isHS())
                                  {
                                    PnlCbx.add(getLabelM("Daten_holen"));
                                    PnlCbx.add(RadDefDaten);    
                                    PnlCbx.add(RadNurAicD);
                                    PnlCbx.add(RadDanachD);
                                  }  
                                }
                                if (g.Spezial())
                                {
                                  if (A.isHS())
                                    PnlCbx.add(CbxSteuern);
                                  if (sDatentyp.equals("BewVon_Bis") || sDatentyp.equals("BewWaehrung") || sDatentyp.equals("BewMass") || sDatentyp.equals("BewZahl")
                                      || sDatentyp.equals("BewStamm") || sDatentyp.startsWith("Calc") && !sDatentyp.equals("CalcBezeichnung")
                                      || sDatentyp.equals("Hierarchie") || sDatentyp.equals("Gruppe") || sDatentyp.equals("Boolean") || sDatentyp.equals("Double") || sDatentyp.equals("Integer")
                                      || sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2") || sDatentyp.equals("BewBoolean"))
                                  {

                                    PnlAuswahl2=new JPanel(new GridLayout(1,3,5,0));
                                    if (!sDatentyp.startsWith("Bew"))
                                    {
                                      // g.addLabel(PnlText,"");
                                      PnlText.add(new JLabel());
                                      PnlAuswahl2.add(new JLabel(""));
                                    }
                                    else
                                    {
                                      g.addLabel4(PnlText,"Gruppe");
                                      PnlAuswahl2.add(BtnGruppe);
                                      BtnGruppe.addActionListener(new ActionListener()
                                      {
                                        public void actionPerformed(ActionEvent ev)
                                        {
                                          Gruppenfestlegung(Dlg, BtnGruppe);
                                        }
                                      });
                                    }
                                    PnlAuswahl.add(PnlAuswahl2);
                                    PnlAuswahl2=new JPanel(new GridLayout(1,3,5,0));
                                    // g.addLabel(PnlText,"");
                                    PnlText.add(new JLabel());
                                    PnlAuswahl2.add(CbxPeriode);
                                    PnlAuswahl2.add(CbxPeriodensumme);
                                    PnlAuswahl2.add(CbxPerSchnitt);
                                    PnlAuswahl.add(PnlAuswahl2);
                                  }
                                  if (sDatentyp.startsWith("Calc") && !sDatentyp.equals("CalcBezeichnung") || A.bWeb && A.iBew==0)
                                  {
                                    g.addLabel4(PnlText,"Calc");
                                    PnlAuswahl.add(BtnCalc);
                                    BtnCalc.addActionListener(new ActionListener()
                                    {
                                      public void actionPerformed(ActionEvent ev)
                                      {
                                        Calcfestlegung(Dlg, BtnCalc);
                                      }
                                    });
                                  }
                                }
				PnlOben.add("West",PnlText);
				PnlOben.add("Center",PnlAuswahl);
			Dlg.getContentPane().add("North",PnlOben);

			JPanel PnlCenter=new JPanel(new BorderLayout());
			if (g.Def())
			{
				JTabbedPane PnlTab=new JTabbedPane();
				PnlTab.add(g.getShow("Swing"),PnlCbx);
				PnlTab.add(g.getShow("Web"),PnlWebG);
				PnlTab.add(g.getShow("Tooltip"),MemSpalte);
				PnlTab.add(g.getShow("AllgemeinS"),MemSAllg);
				PnlCenter.add("Center",PnlTab);
			}
			else
			  PnlCenter.add("North",PnlCbx);
//                        GroupBox GBTT=new GroupBox(g.getBegriffS("Show","Tooltip"));
//                        GBTT.add(MemSpalte);
//			PnlCenter.add("Center",GBTT);
			Dlg.getContentPane().add("Center",PnlCenter);

			JPanel PnlUnten = new JPanel(new GridLayout());
				JButton BtnOk = g.getButton("Ok");
                                BtnOk.setEnabled(bEdit);
				JButton BtnAbbruch = g.getButton("Abbruch");
                                Dlg.getRootPane().setDefaultButton(BtnOk);
				PnlUnten.add(BtnOk);
				PnlUnten.add(BtnAbbruch);
				BtnOk.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
                                          /*if (CbxGruppiert.isSelected() && SBoSortiert.getIntValue()==0)
                                            new Message(Message.WARNING_MESSAGE,null,g).showDialog("Sortierung fehlt");
                                          else*/
                                          {
                                            //g.testInfo("Bezeichnung1:"+EdtBezeichnung.getText());
                                            if (CboRel.isVisible() && CboRel.Modified() && CboRel.getSelectedAIC()>0)
                                              EdtBezeichnung.setText(CboRel.getSelectedBezeichnung());
                                            //g.testInfo("Bezeichnung2:"+EdtBezeichnung.getText());
						A.TabSp.setInhalt("Bez2",EdtBezeichnung.isNull()?null:EdtBezeichnung.getText());
						A.TabSp.setInhalt("Kennung",EdtKennungS.isNull()?null:EdtKennungS.getText());
						A.TabSp.setInhalt("bits",(CbxGruppiert.isSelected()?Abfrage.cstGruppiert:0)+(CbxSortdesc.isSelected()?Abfrage.cstSortDesc:0)+(CbxSpLeer.isSelected()?Abfrage.cstSpLeer:0)+
							(CbxAnzeigen.isSelected()?Abfrage.cstAnzeigen:0)+(CbxEditierbar.isSelected()?Abfrage.cstEditierbar:0)+(CbxPeriode.isSelected()?Abfrage.cstPeriode:0)+(CbxPeriodensumme.isSelected()?Abfrage.cstPeriodensumme:0)+
							(CbxKeinAutoDate.isSelected()?Abfrage.cstKeinAutoDate:0)+(CbxSperre.isSelected()?Abfrage.cstSperre:0)+
                                                        (RadLeer.isSelected()?Abfrage.cstKeinAutoLast:RadNimmSync.isSelected()?Abfrage.cstNimmSync:RadLimit.isSelected()?Abfrage.cstLimit:0)+
							(CbxHochkomma.isSelected()?Abfrage.cstHochkomma:0)+(CbxUnsichtbar.isSelected()?Abfrage.cstUnsichtbar:0)+(CbxSteuern.isSelected()?Abfrage.cstSteuern:0)+
							(CbxAutoInc.isSelected()?Abfrage.cstAutoInc:0)+(CbxGueltig.isSelected()?Abfrage.cstGueltig:0)+(CbxSpVerteiler.isSelected()?Abfrage.cstSpVerteiler:0)+
                                                        (CbxSpRefresh.isSelected()?Abfrage.cstSpRefresh:0)+(CbxBild.isSelected()?Abfrage.cstBild:0)+
                                                        (CbxMobil.isSelected()?Abfrage.cstMobil:0)+(CbxKeineEinheit.isSelected()?Abfrage.cstKeineEinheit:0)+(CbxGueltig2.isSelected()?Abfrage.cstGueltig2:0)+
                                                        (RadRadio.isSelected()?Abfrage.cstRadio:RadButtons.isSelected()?Abfrage.cstButtons:RadCombo2.isSelected()?Abfrage.cstCombo2:Abfrage.cstCombo)+
                                                        (CbxPositiv.isSelected()?Abfrage.cstPositiv:0)+(CbxEindeutig.isSelected()?Global.cstEindeutig*0x10000:0)+(CbxAlways.isSelected()?Global.cstAlways*0x10000:0));
                                                A.TabSp.setInhalt("bits2",(CbxSpGMT.isSelected()?Abfrage.cstSpGMT:0)+(CbxSpNotSave.isSelected()?Abfrage.cstSpNotSave:0)+(CbxKeineGSumme.isSelected()?Abfrage.cstKeineGSumme:0)+
                                                    (CbxKeine1Summe.isSelected()?Abfrage.cstKeine1Summe:0)+(CbxSpSort.isSelected()?Abfrage.cstSpSort:0)+(CbxKeinOhr.isSelected()?Abfrage.cstKeinOhr:0)+
                                                    (CbxImportBed.isSelected()?Abfrage.cstImportBed:0)+(CbxKumuliert.isSelected()?Abfrage.cstKumuliert:0)+(CbxAbNeujahr.isSelected()?Abfrage.cstAbNeujahr:0)+
                                                    (CbxFett.isSelected()?Abfrage.cstFett:0)+(CbxKeineLeerzeilen.isSelected()?Abfrage.cstKeineLeerzeilen:0)+(CbxVorZR.isSelected()?Abfrage.cstVorZR:0)+
                                                    (CbxErgaenzen.isSelected()?Abfrage.cstErgaenzen:0)+(CbxFett2.isSelected()?Abfrage.cstFett2:0)+(CbxStichtag.isSelected()?Abfrage.cstStichtag:0)+
                                                    (CbxAIC_Bez.isSelected()?Abfrage.cstAIC_Bez:0)+(CbxNurErste.isSelected()?Abfrage.cstNurErste:0)+(CbxSpGleiche.isSelected()?Abfrage.cstSpGleiche:0)+
                                                    (CbxBuchZahl.isSelected()?Abfrage.cstBuchZahl:0)+(CbxZiffern.isSelected()?Abfrage.cstZiffern:0)+(CbxKeinCG.isSelected()?Abfrage.cstKeinCG:0)+
                                                    (CbxSpKeinDel.isSelected()?Abfrage.cstSpKeinDel:0)+(CbxBedZwang.isSelected()?Abfrage.cstBedZwang:0)+(CbxOrigEinh.isSelected()?Abfrage.cstOrigEinh:0)+
                                                    (CbxNegativRot.isSelected()?Abfrage.cstNegativRot:0)+(CbxMulti.isSelected()?Abfrage.cstMulti:0)+(CbxSetSync.isSelected()?Abfrage.cstSetSync:0)+
                                                    (A.TabSp.getI("bits2")&Abfrage.cstWeg)+(CbxGanzesJahr.isSelected()?Abfrage.cstGanzesJahr:0)+(CbxKeinKnoten.isSelected()?Abfrage.cstKeinKnoten:0));
                                                A.TabSp.setInhalt("bits3",(CbxBool.isSelected()?Abfrage.cstBool:0)+(CbxUG.isSelected()?Abfrage.cstUG:0)+(CbxFilterOK.isSelected()?Abfrage.cstFilterOK:0)+
                                                		(CbxSpKeinTitel.isSelected()?Abfrage.cstSpKeinTitel:0)+(CbxHH_mm.isSelected()?Abfrage.cstHH_mm:0)+(CbxHtml.isSelected()?Abfrage.cstHtml:0)+(CbxInStunden.isSelected()?Abfrage.cstInStunden:0)+
                                                		(RadCho.isSelected()?Global.cstEigCho:RadCbo.isSelected()?Global.cstEigCbo:RadRad.isSelected()?Global.cstEigRad:RadBtn.isSelected()?Global.cstEigBtn:RadSbo.isSelected()?Global.cstEigSbo:
                                                      	  RadPop.isSelected()?Global.cstEigPop:RadCbx.isSelected()?Global.cstEigCbx:RadSwb.isSelected()?Global.cstEigSwb:RadAuC.isSelected()?Global.cstEigAuC
                                                      			:RadDT.isSelected()?Global.cstEigDT:RadTime.isSelected()?Global.cstEigTime:RadSlider.isSelected()?Global.cstEigSlider:RadTitel.isSelected()?Global.cstEigWebTitel:Global.cstEigAuto)
                                                      	+(CbxBarcode.isSelected()?Abfrage.cstBarcode:0)/*+(CbxSS.isSelected()?Abfrage.cstSaveSoon:0)*/+(CbxNoFav.isSelected()?Abfrage.cstNoFav:0)+(CbxNoSubFom.isSelected()?Abfrage.cstNoSubFom:0)
                                                      	+(CbxZusatz.isSelected()?Abfrage.cstZusatz:0)+(CbxKNZW.isSelected()?Abfrage.cstKNZW:0)/*+(CbxDetail.isSelected()?Abfrage.cstDetail:0)*/+(CbxNotUser.isSelected()?Abfrage.cstNotUser:0)
                                                      	+(CbxFarbzeile.isSelected()?Abfrage.cstColorRow:0)+(CbxPerSchnitt.isSelected()?Abfrage.cstPerSchnitt:0)+(CbxGruppierbar.isSelected()?Abfrage.cstGruppierbar:0)
                                                      	+(CbxAuto.isSelected()?Abfrage.cstAuto:0)+(CbxTabEin.isSelected()?Abfrage.cstTabEin:0)+(CbxFullScr.isSelected()?Abfrage.cstFullScr:0)+(CbxPDF.isSelected()?Abfrage.cstPdfViewer:0)
                                                      	+(CbxInPlace.isSelected()?Abfrage.cstInPlace:0)+(CbxSuche.isSelected()?Abfrage.cstSuche:0)+(CbxFilter2.isSelected()?Abfrage.cstFilter2:0)+(CbxFilterInit.isSelected()?Abfrage.cstFilterInit:0)
                                                        +(RadNurAicD.isSelected() ? Abfrage.cstNurAicD:RadDanachD.isSelected() ? Abfrage.cstDanachD:0));
						A.TabSp.setInhalt("Sortiert",SBoSortiert.getIntValue());
						A.TabSp.setInhalt("Aic_Code",CboExportformat.getSelectedAIC());
						A.TabSp.setInhalt("Cod_Aic_Code",CboErgebnis.isNull()?null:new Integer(CboErgebnis.getSelectedAIC()));
						A.TabSp.setInhalt("Laenge",(EdtLaenge.intValue()<0?501:EdtLaenge.intValue())+(EdtLaengeD.intValue()<0?1001:EdtLaengeD.intValue())*512+
								(EdtLaengeB.intValue()<0 ? 0:EdtLaengeB.intValue()*512*1024)+(EdtLaengeN.intValue()<0 ? 0:EdtLaengeN.intValue()*512*1024*1024));
						A.TabSp.setInhalt("WebLaenge",EdtLaengeW.intValue());
						A.TabSp.setInhalt("aic_begriff",EdtFormat.isNull() ?null:new Integer(EdtFormat.getAIC()));
						A.TabSp.setInhalt("Ausrichtung",CboAusrichtung.getSelectedAIC());
						A.TabSp.setInhalt("Sub1",CboSub1.getAic());// CboSubFom.getSelectedAIC());
						A.TabSp.setInhalt("Sub2",CboSub2.getAic());// CboSubAbf.getSelectedAIC());
						A.TabSp.setInhalt("Sub3",CboSub3.getAic());// CboSubMod.getSelectedAIC());
                        A.TabSp.setInhalt("Anzeige",CboAnzeigeart.getSelectedAIC());
                        A.TabSp.setInhalt("Rel",CboBed!=null && !CboBed.isNull() ? CboBed.getStamm() : CboRel.getSelectedAIC());
						A.TabSp.setInhalt("Mass",CboMass.getSelectedAIC());
                                                if (CboHierarchie.getSelectedAIC()==0)
                                                  A.TabSp.setInhalt("aic_stammtyp",null);
                                                else
                                                  A.TabSp.setInhalt("aic_stammtyp",CboHierarchie.getSelectedAIC());
						if (bCboFilter)
							A.TabSp.setInhalt("Filter",CboFilter.getSelectedAIC());
						A.TabSp.setInhalt("Faktor",new Double(EdtFaktor.doubleValue()));
                                                A.TabSp.setInhalt("Min",EdtMin.isNull() ? null:new Double(EdtMin.doubleValue()));
                                                A.TabSp.setInhalt("Max",EdtMax.isNull() ? null:new Double(EdtMax.doubleValue()));
                                                A.TabSp.setInhalt("x",EdtX.isNull() ? null:EdtX.intValue());
                                                A.TabSp.setInhalt("y",EdtY.isNull() ? null:EdtY.intValue());
                                                A.TabSp.setInhalt("w",EdtW.isNull() ? null:EdtW.intValue());
                                                A.TabSp.setInhalt("h",EdtH.isNull() ? null:EdtH.intValue());
                                                A.TabSp.setInhalt("Stil",EdtStil.getText());
                                                A.TabSp.setInhalt("ToggleSight",EdtTS.getText()+(EdtStep.isNull() && EdtWD.isNull() ? "":";"+EdtStep.getText()+";"+EdtWD.getText()));
                                                A.TabSp.setInhalt("Icon",EdtIcon.getText());
                                                A.TabSp.setInhalt("sub",CboSubSpalte.getSelectedAIC());
                                                A.TabSp.setInhalt("Farbe",EdtFarbe.getText());
						A.TabSp.setInhalt("TT",MemSpalte.getValue());
                                                A.TabSp.setInhalt("aic_farbe",CboFarbe.getSelectedAIC());
                                                //g.testInfo("Bezeichnung3:"+EdtBezeichnung.getText());
						if (!EdtBezeichnung.getText().equals(""))
							Gid.getSelectedNode().setLabel(EdtBezeichnung.getText());
						if (MemSAllg != null && MemSAllg.Modified())
						{
							sMemoSpalte=MemSAllg.getValue();
							g.setMemo(sMemoSpalte, "", 1, iTab_Spalte, 1);
						}
						Gid.getSelectedNode().getStyle().setForeground(A.getColor(sDatentyp));
						Gid.folderChanged(Gid.getSelectedNode().getParent());
						if (A.TabSp.getInhalt("Status")==null)
							A.TabSp.setInhalt("Status","Edit");
						disposeDlg(Dlg);
                                            }
                                          }
				});
				BtnAbbruch.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						disposeDlg(Dlg);
					}
				});

			Dlg.getContentPane().add("South",PnlUnten);
// *******************************************************************************
			Dlg.setSize(700,720);
			//g.progInfo("Spaltendef-Größe:"+Dlg.getHeight()+"x"+Dlg.getWidth());
			Static.centerComponent(Dlg,thisFrame);
			Dlg.setVisible(true);
		}
		else
			Static.printError("Abfrage.SpalteBearbeiten: Spalte "+(Gid!=null ? Gid.getSelectedNode()+" ":"")+"nicht gefunden!");
	}
	
	private void disposeDlg(JDialog Dlg)
	{
		Dlg.dispose();
		thisJFrame().setEnabled(true);
		thisJFrame().toFront();
	}
	
	private String getMemo(int iOpt)
	{
		//TODO Memo richtig ermitteln
		return SQL.getString(g, "select memo from daten_memo where aic_tabellenname="+g.TabTabellenname.getAic("CODE")+" and aic_fremd="+iOpt+" and aic_sprache="+g.getSprache());	
	}

  static final AUVector Bdead=new AUVector(new String[] {"reserveOP16","reserveOP17","reserveOP18","reserveOP19","reserveOP20"});
  static final AUVector BBed=new AUVector(new String[] {"reserveOP11","reserveOP12","reserveOP13","reserveOP14","reserveOP6","reserveOP7","reserveOP8"});
  static final AUVector BString=new AUVector(new String[] {"string concat","string right","string fix","string fillnull","add char","string_left","trunc_left","reserveOP4","reserveOP8","reserveOP9","reserveOP10","reserveOP15"});
  static final AUVector BBool=new AUVector(new String[] { "<",">","=","<=",">=","<>","reserveOP3","reserveOP6","reserveOP7","reserveOP8","reserveOP9","reserveOP10"});

  private void fillOperation(ComboSort Cbo, String sDatentyp)
  {
    // g.fixtestError("fillOperation mit "+sDatentyp);
    boolean bDate=sDatentyp.equals("CalcDatum");
    boolean bString=sDatentyp.equals("CalcString");
    boolean bBool=sDatentyp.equals("CalcBoolean");
    boolean bNum=sDatentyp.equals("CalcField") || sDatentyp.equals("CalcMass") || sDatentyp.equals("CalcWaehrung") || sDatentyp.equals("CalcDauer");
    g.fixtestError(sDatentyp+"->"+bDate+"/"+bString+"/"+bBool+"/"+bNum);
    int iPos=g.TabBegriffgruppen.getPos("Kennung", "Operation");
    if (iPos>=0)
    {
      int iGruppe=g.TabBegriffgruppen.getI(iPos,"AIC");
      Cbo.Clear();
      // int iPosBG=g.TabBegriffgruppen.getPos("AIC",iGruppe);
		  Tabellenspeicher Tab = g.TabCodes;
      int iP=Tab.getPos("Gruppe",iGruppe);
		  while (iP>=0 && iP<Tab.size() && Tab.getI(iP,"Gruppe")==iGruppe)
		  {
        String sKen=Tab.getS(iP,"Kennung");
        boolean b=Bdead.contains(sKen) ? false:bDate || bNum ? true: bBool ? BBool.contains(sKen): bString ? BString.contains(sKen):BBed.contains(sKen);
        // if (!Bdead.contains(sKen) && (bDate || bString || bBool || bNum || BBed.contains(sKen)))
        if (b)
			    Cbo.addItem2(Tab.getS(iP,"Bezeichnung"),Tab.getI(iP,"Aic"),sKen);
			  Tab.moveNext();
        iP++;
		  }
		  //g.addTemp("B");
		  Cbo.setKein(false);
		  Cbo.Sort();
    }
  }

	private void NeueRechenoperation(final boolean bNeu,JDialog rDlg2,final Tabellenspeicher TabCalc,final AUOutliner GidDaten)
	{
          if (!bNeu && GidDaten.getSelectedNode().getLevel()==0)
            return;
		final JDialog Dlg3= new JDialog(rDlg2,g.getBegriffS("Dialog","Rechenoperation"));

		JPanel PnlText = new JPanel(new GridLayout(0,1,2,2));
		JPanel PnlAuswahl = new JPanel(new GridLayout(0,1,2,2));
		AUTextArea EdtMemo=new AUTextArea(g,3);
    EdtMemo.setMaxLength(4000);
//		EdtMemo.setMinimumSize(new Dimension(100,100));

		final ComboSort CboOperation=new ComboSort(g);
		// CboOperation.fillBegriffTable("Operation",false);
		CboOperation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
//				g.fixtestError("CboOperation - Changed auf "+CboOperation.getSelectedAIC());
				String s=getMemo(CboOperation.getSelectedAIC());
				EdtMemo.setText(s);
				CboOperation.setToolTipText(s);
			}
		});
		final ComboSort CboSpalte=new ComboSort(g);
		int iEPos=g.TabEigenschaften.getPos("Aic",((Vector)A.TabSp.getInhalt("Vec")).lastElement());
		String sDatentyp = g.TabEigenschaften.getS(iEPos,"Datentyp");
    fillOperation(CboOperation,sDatentyp);
//		A.TabSp.push();
//		for(A.TabSp.moveFirst();!A.TabSp.eof();A.TabSp.moveNext())
		for(int i=0;i<A.TabSp.size();i++)
                {
                  //g.progInfo(A.TabSp.getS("Bezeichnung")+"/"+A.TabSp.isNull("Bezeichnung"));
                  if (!A.TabSp.getS(i,"Status").equals("Del") && (A.TabSp.getI(i,"bits2")&Abfrage.cstWeg)==0)
                  {
                	  Vector VecEig = (Vector)A.TabSp.getInhalt("Vec",i);
                      iEPos=g.TabEigenschaften.getPos("Aic",Sort.geti(VecEig.elementAt(VecEig.size()-1)));
                	String sDt=g.TabEigenschaften.getS(iEPos,"Datentyp");
//                	if(!sDatentyp.startsWith("CalcBool") || sDatentyp.equals("CalcBoolean") && sDt.endsWith("Boolean"))  
                      CboSpalte.addItem((A.TabSp.isNull(i,"Bezeichnung") ? Abfrage.getEigenschaftBezeichnung(g, (Vector)A.TabSp.getInhalt("Vec",i)) :A.TabSp.getS(i,"Bezeichnung"))+" | "+sDt, A.TabSp.getI(i,"Nummer"), A.TabSp.getS(i,"Kennung"));
                  }
                }
//		A.TabSp.pop();
		CboSpalte.setKein(true);

		final Zahl EdtZahl=new Zahl(0.0);
		final Text EdtString=new Text(Static.sLeer,90);

		if (!bNeu /*&& GidDaten.getSelectedNode().getLevel()>0*/)
		{
			TabCalc.setPos(((Integer)((Vector)GidDaten.getSelectedNode().getLabel()).elementAt(4)).intValue());
			CboOperation.setSelectedAIC(TabCalc.getI("Operation"));
			CboSpalte.setSelectedAIC(TabCalc.getI("Spalte"));
			EdtZahl.setValue(TabCalc.getF("Wert"));
			EdtString.setText(TabCalc.getS("Eingabe"));
			EdtMemo.setText(getMemo(CboOperation.getSelectedAIC()));
		}

		//PnlText.add(new JLabel(g.getBegriffS("Show","Operation")+':'));
                g.addLabel2(PnlText,"Operation");
		PnlAuswahl.add(CboOperation);
		//PnlText.add(new JLabel(g.getBegriffS("Show","Spalte")+':'));
                g.addLabel2(PnlText,"Spalte");
                CboSpalte.setPreferredSize(new java.awt.Dimension(200,20));
		PnlAuswahl.add(CboSpalte);
		//PnlText.add(new JLabel(g.getBegriffS("Show","Wert")+':'));
                g.addLabel2(PnlText,"Wert");
        PnlAuswahl.add(EdtZahl);
                g.addLabel2(PnlText,"String");
		PnlAuswahl.add(EdtString);
		JPanel PnlC=new JPanel(new BorderLayout());
         PnlC.add("West",PnlText);
         PnlC.add("Center",PnlAuswahl);
         GroupBox GB=new GroupBox("Memo");
         GB.setFont(g.fontStandard);
         GB.add(EdtMemo);
//         PnlC.add("South",GB);
		//Dlg3.getContentPane().add("West",PnlText);
        Dlg3.getContentPane().add("North",PnlC);
		Dlg3.getContentPane().add("Center",GB);
		//Dlg3.getContentPane().add("South",EdtMemo);

		JPanel PnlUnten = new JPanel(new GridLayout());
			JButton BtnOk = g.getButton("Ok");
			JButton BtnAbbruch = g.getButton("Abbruch");
                        Dlg3.getRootPane().setDefaultButton(BtnOk);
			PnlUnten.add(BtnOk);
			PnlUnten.add(BtnAbbruch);

		BtnOk.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					if (bNeu)
						TabCalc.newLine();
					TabCalc.setInhalt("Operation",CboOperation.getSelectedItem());
					if (CboSpalte.getSelectedAIC() !=0)
						TabCalc.setInhalt("Spalte",CboSpalte.getSelectedItem());
					else if (!bNeu)
						TabCalc.setInhalt("Spalte",null);
					if (!EdtZahl.isNull())
						TabCalc.setInhalt("Wert",new Double(EdtZahl.doubleValue()));
					else if (!bNeu)
						TabCalc.setInhalt("Wert",null);
					if (!EdtString.isNull())
						TabCalc.setInhalt("Eingabe",EdtString.getText());
					else if (!bNeu)
						TabCalc.setInhalt("Eingabe",null);
					Dlg3.dispose();
					TabCalc.showGrid(GidDaten);
				}
			});
			BtnAbbruch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					Dlg3.dispose();
				}
			});
		Dlg3.getContentPane().add("South",PnlUnten);
		Dlg3.setSize(350, 300);
		Static.centerComponent(Dlg3,rDlg2);
		Dlg3.setVisible(true);
	}

	private void Calcfestlegung(JDialog rDlg,final JButton rBtn)
	{
		final Tabellenspeicher TabCalc=A.TabSp.isNull("Calc") ?new Tabellenspeicher(g,new String[] {"Operation","Spalte","Wert","Eingabe"}):
			new Tabellenspeicher(g,(Tabellenspeicher)A.TabSp.getInhalt("Calc"));
		final JDialog Dlg2= new JDialog(rDlg,g.getBegriffS("Dialog","SPALTE_BERECHNUNG"));

		final AUOutliner GidDaten = new AUOutliner();
		TabCalc.showGrid(GidDaten);
		Dlg2.getContentPane().add("Center",GidDaten);

		JPanel PnlUnten = new JPanel(new GridLayout(2,2));
                JPanel PnlWest = new JPanel(new GridLayout(0,1));
			JButton BtnNeu = g.getButton("Neu");
			JButton BtnLoeschen = g.getButton("Loeschen");
			JButton BtnEdit = g.getButton("Edit");
			JButton BtnHelp = g.getButton("HelpCalc");
			JButton BtnOk = g.getButton("Ok");
			JButton BtnAbbruch = g.getButton("Abbruch");
                        JButton BtnUp2=g.getButton("Pfeil oben");
                        JButton BtnDown2=g.getButton("Pfeil unten");

                        Dlg2.getRootPane().setDefaultButton(BtnOk);
			PnlWest.add(BtnNeu);
                        PnlWest.add(BtnUp2);
                        PnlWest.add(BtnDown2);
			PnlWest.add(BtnLoeschen);
			PnlUnten.add(BtnEdit);
			PnlUnten.add(BtnHelp);
			PnlUnten.add(BtnOk);
			PnlUnten.add(BtnAbbruch);
			BtnNeu.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					NeueRechenoperation(true,Dlg2,TabCalc,GidDaten);
				}
			});
                        BtnUp2.addActionListener(new ActionListener()
                        {
                                public void actionPerformed(ActionEvent ev)
                                {
                                        int i=((Integer)((Vector)GidDaten.getSelectedNode().getLabel()).elementAt(4)).intValue();
                                        TabCalc.verschieben(i,i-1);
                                        TabCalc.setPos(i-1);
                                        //Static.move_up_down(GidDaten.getSelectedNode(),true);
                                        TabCalc.showGrid(GidDaten);
                                }
                        });
                        BtnDown2.addActionListener(new ActionListener()
                        {
                                public void actionPerformed(ActionEvent ev)
                                {
                                        int i=((Integer)((Vector)GidDaten.getSelectedNode().getLabel()).elementAt(4)).intValue();
                                        TabCalc.verschieben(i+1,i);
                                        TabCalc.setPos(i+1);
                                        //Static.move_up_down(GidDaten.getSelectedNode(),false);
                                        TabCalc.showGrid(GidDaten);
                                }
                        });
			BtnEdit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					NeueRechenoperation(false,Dlg2,TabCalc,GidDaten);
				}
			});
			BtnLoeschen.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					TabCalc.setPos(((Integer)((Vector)GidDaten.getSelectedNode().getLabel()).elementAt(4)).intValue());
					TabCalc.clearInhalt();
					TabCalc.showGrid(GidDaten);
				}
			});
			BtnOk.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{

					A.TabSp.setInhalt("Calc",TabCalc);
					rBtn.setText(""+TabCalc.getAnzahlElemente(null));
					rBtn.repaint();
					Dlg2.dispose();
				}
			});
			BtnAbbruch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					Dlg2.dispose();
				}
			});
                Dlg2.getContentPane().add("West",PnlWest);
		Dlg2.getContentPane().add("South",PnlUnten);
		Dlg2.pack();
		Static.centerComponent(Dlg2,rDlg);
		Dlg2.setVisible(true);
	}

	private void Gruppenfestlegung(JDialog rDlg,final JButton rBtn) // horizonale Auflösung
	{
		final JDialog Dlg2= new JDialog(rDlg,g.getBegriffS("Dialog","Spalten_Zuordnung"));
		final AUOutliner GidGruppe=new AUOutliner(new JCOutlinerFolderNode("Gruppen"));
		final JCOutlinerFolderNode NodRoot=(JCOutlinerFolderNode)GidGruppe.getRootNode();
		final Text EdtBez=new Text("",255);

		if (A.TabSp.getInhalt("Gruppe")!=null)
		{

			//NodRoot.setChildren((jclass.util.Vector)A.TabSp.getInhalt("Gruppe"));

			Vector Vec=(Vector)A.TabSp.getInhalt("Gruppe");
			for(int i=0;i<Vec.size();i++)
				NodRoot.addNode((JCOutlinerFolderNode)Vec.elementAt(i));
			GidGruppe.folderChanged(NodRoot);
		}
		JPanel PnlOben = new JPanel(new BorderLayout());
			JButton BtnInit = g.getButton("Init");
                        Object Obj0=((Vector)A.TabSp.getInhalt("Vec")).elementAt(0);
                        int iBew=A.iBew>0?A.iBew:Obj0 instanceof BewEig ? ((BewEig)Obj0).Bew():0;
			final StammEingabe SteGruppe = new StammEingabe(Dlg2,g,-iBew,0);
                        //SteGruppe.setStt(0);
			PnlOben.add("West",BtnInit);
			PnlOben.add("Center",SteGruppe);
			//PnlOben.add("East",BtnUnd);
			BtnInit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					NodRoot.removeChildren();
					GidGruppe.folderChanged(NodRoot);
				}
			});

		Dlg2.getContentPane().add("North",PnlOben);

		JPanel PnlEast = new JPanel(new GridLayout(0,1));
		final JButton BtnUnd = g.getButton("<");
		final JButton BtnRauf = g.getButton("Pfeil oben");
		final JButton BtnRunter = g.getButton("Pfeil unten");
		final JButton BtnStatt = g.getButton("Ersetzen");
		final JButton BtnWeg = g.getButton(">");

		BtnUnd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				//int i=GidGruppe.getSelectedIndex();
				//JCOutlinerNode Nod=new JCOutlinerNode(SteGruppe.isNull() ? SteGruppe.getComboStt():SteGruppe.getCombo());
                                JCOutlinerNode Nod=new JCOutlinerNode(SteGruppe.getBezeichnung2(true));
                                Nod.setUserData(SteGruppe.getVec());
				//g.progInfo(SteGruppe.getCombo().getBezeichnung()+":"+SteGruppe.getCombo().getAic());
				int iLevel=GidGruppe.getSelectedNode().getLevel();
				if (iLevel==1)
					((JCOutlinerFolderNode)GidGruppe.getSelectedNode()).addNode(Nod);
				else if (iLevel==0)
				{
					JCOutlinerFolderNode Nod2=new JCOutlinerFolderNode(SteGruppe.getBezeichnung2(false),NodRoot);
					Nod2.addNode(Nod);
				}
				GidGruppe.folderChanged(NodRoot);
			}
		});
		BtnRauf.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Static.move_up_down(GidGruppe.getSelectedNode(),true);
				BtnRauf.setEnabled(Static.allow_up_down(GidGruppe.getSelectedNode(),true));
				BtnRunter.setEnabled(true);
			}
		});
		BtnStatt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                          JCOutlinerNode Nod=GidGruppe.getSelectedNode();
                          int iLevel=Nod.getLevel();
                          if (iLevel==1)
                                  Nod.setLabel(EdtBez.getText());
                          else if (iLevel==2)
                          {
                            //GidGruppe.getSelectedNode().setLabel(SteGruppe.getCombo());
                            Nod.setLabel(SteGruppe.getBezeichnung2(true));
                            Nod.setUserData(SteGruppe.getVec());
                          }
                          GidGruppe.folderChanged(NodRoot);
			}
		});
		BtnRunter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Static.move_up_down(GidGruppe.getSelectedNode(),false);
				BtnRauf.setEnabled(true);
				BtnRunter.setEnabled(Static.allow_up_down(GidGruppe.getSelectedNode(),false));
			}
		});
		BtnWeg.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				JCOutlinerNode Nod=GidGruppe.getPreviousNode(GidGruppe.getSelectedNode());
				GidGruppe.getSelectedNode().getParent().removeChild(GidGruppe.getSelectedNode());
				GidGruppe.selectNode(Nod,null);
				GidGruppe.folderChanged(NodRoot);
			}
		});

		GidGruppe.addItemListener(new JCItemListener()
		{
			public void itemStateChanged(JCItemEvent e)
			{
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					int iLevel=GidGruppe.getSelectedNode().getLevel();
					EdtBez.setText(""+GidGruppe.getSelectedNode().getLabel());
					BtnUnd.setEnabled(iLevel<2);
					BtnWeg.setEnabled(iLevel>0);
					BtnStatt.setEnabled(iLevel>0);
					BtnRauf.setEnabled(iLevel==1 && Static.allow_up_down(GidGruppe.getSelectedNode(),true));
					BtnRunter.setEnabled(iLevel==1 && Static.allow_up_down(GidGruppe.getSelectedNode(),false));
                                        if (iLevel==2)
                                        {
                                          Object Vec=GidGruppe.getSelectedNode().getUserData();
                                          g.progInfo("GidGruppe.itemStateChanged:"+Vec);
                                          int iEig=Sort.geti(Vec,2);
                                          if (iEig>0)
                                          {
                                            SteGruppe.setStt(iEig);
                                            SteGruppe.setStamm(Sort.geti(Vec,0));
                                          }
                                        }
				}
			}
		});

		PnlEast.add(BtnUnd);
		PnlEast.add(BtnRauf);
		PnlEast.add(BtnStatt);
		PnlEast.add(BtnRunter);
		PnlEast.add(BtnWeg);
		Dlg2.getContentPane().add("East",PnlEast);

		JPanel PnlCenter = new JPanel(new BorderLayout(0,1));
			PnlCenter.add("Center",GidGruppe);
			JPanel PnlCenterSouth = new JPanel(new BorderLayout(0,1));
				PnlCenterSouth.add("West",new JLabel("Titel:"));
				PnlCenterSouth.add("Center",EdtBez);
			PnlCenter.add("South",PnlCenterSouth);
		Dlg2.getContentPane().add("Center",PnlCenter);
		JPanel PnlUnten = new JPanel(new GridLayout());
			JButton BtnOk = g.getButton("Ok");
			JButton BtnAbbruch = g.getButton("Abbruch");
			boolean bOK=g.ApplPort() || g.BasisPort();
			BtnOk.setEnabled(bOK);
            Dlg2.getRootPane().setDefaultButton(bOK ? BtnOk:BtnAbbruch);
			PnlUnten.add(BtnOk);
			PnlUnten.add(BtnAbbruch);
			BtnOk.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					Vector VecNod=NodRoot.getChildren();
					Vector<JCOutlinerNode> Vec=VecNod==null || VecNod.size()==0?null:new Vector<JCOutlinerNode>();
					if (Vec != null)
						for(int i=0;i<VecNod.size();i++)
							Vec.addElement((JCOutlinerNode)VecNod.elementAt(i));
					g.progInfo("Vec-Gruppe="+Vec);
					A.TabSp.setInhalt("Gruppe",Vec);
					rBtn.setText(""+(Vec==null?0:Vec.size()));
					rBtn.repaint();
					Dlg2.dispose();
				}
			});
			BtnAbbruch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					Dlg2.dispose();
				}
			});
		Dlg2.getContentPane().add("South",PnlUnten);
		Dlg2.pack();
		Static.centerComponent(Dlg2,rDlg);
		Dlg2.setVisible(true);
	}

	private void moveEigenschaft(boolean bRauf)
	{
		bReihenfolge = true;
		Static.move_up_down(Gid.getSelectedNode(),bRauf);
		EnableButtons();
	}

	// --------------------------------------------------------------------------------
	// -------------   * * * * *   T E S T   * * * * *   ------------------------------
	// --------------------------------------------------------------------------------

	private void showTestparameter()
	{
		//java.sql.Date dtStichtag = g.getStichtag();

		if (DlgTest==null)
		{
			final Text EdtName=new Text("",40);
//			final AUPasswort EdtPW=new AUPasswort();
			TabStamm=new Tabellenspeicher(g,new String[] {"Aic","Bezeichnung"});
			DlgTest = new JFrame("Teste Abfrage");
			//final ComboSort CboStamm=new ComboSort(g);
			SteStamm = new StammEingabe(DlgTest,g);
			final Zahl EdtBew = new Zahl(0);
			DatVon = new Datum(g,"yyyy-MM-dd HH:mm:ss");
			DatBis = new Datum(g,"yyyy-MM-dd HH:mm:ss");
			TxtZA=new Text("Tag",10);
			final StammEingabe SteJoker = new StammEingabe(DlgTest,g);
			final Text EdtJoker = new Text("",40);
                        final Zahl EdtBewJoker=new Zahl(0);
                        EdtJoker.setColumns(15);
			final Datum DatStichtag = new Datum(g,"yyyy-MM-dd");
                        final Zahl EdtZeilen = new Zahl(g.Def()?10000:100);
                        final Zahl EdtHoehe = new Zahl(18);
                        final Zahl EdtSpalten = new Zahl(1);
                        final Zahl EdtDoubleJoker = new Zahl(0.0);
                        EdtZeilen.setMax(new Integer(g.Oracle() ? 999999:32767));

                        JButton BtnSync = g.getFrameS("SyncStamm");
			CbxAlleKein = getCheckboxM("alle bei kein",true);
                        final AUCheckBox CbxDruck = getCheckboxM("Druck",false);
                        final AUCheckBox CbxAlleSpalten = getCheckboxM("alle Spalten",false);
                        final AUCheckBox CbxUseVec = getCheckboxM("use Vec",false);
                        final AUCheckBox CbxForExport = getCheckboxM("for Export",false);
                        //final AUCheckBox CbxJavaFX = getCheckboxM("JavaFX",false);
                        CbxWeb2 = getCheckboxM("Web2",A.bWeb);
                        //CbxView2b = getCheckboxM("View2",false);

			//CbxAlle.setSelected(true);
			CbxModell = getCheckboxM("mit Modell",false);
			CbxDebug = getCheckboxM("Debug",false);
			CbxDebug.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					if (CbxDebug.isSelected())
						CbxModell.setSelected(true);
				}
			});
            AUCheckBox CbxClearCache = getCheckboxM("clear Cache",false);
            AUCheckBox CbxZeitzone = getCheckboxM("Zeitzone",false);
            AUCheckBox CbxExplain = getCheckboxM("explain",false);
            AUCheckBox CbxThread = getCheckboxM("Thread",false);
            AUCheckBox CbxNeu = getCheckboxM("Neu",true);
            AUCheckBox CbxOriginal = getCheckboxM("Original",false);
            final JButton BtnTestInfo=g.getButton("Info");
            final JButton BtnBewInfo=g.getButton("Info");
            Vector<Integer> VecBew = new Vector<Integer> ();
            ActionListener AL=new ActionListener()
	        {
	          public void actionPerformed(ActionEvent ev)
	          {
	            String s = ev.getActionCommand();
	            if (s.equals("Sinit"))
	            {
					TabStamm.clearAll();
					BtnTestInfo.setText("0");
				}
	            else if (s.equals("Sund"))
	            {
					TabStamm.addInhalt("Aic",SteStamm.getStamm());
					TabStamm.addInhalt("Bezeichnung",SteStamm.getBezeichnung());
					BtnTestInfo.setText(""+TabStamm.getAnzahlElemente(null));
				}
	            else if (s.equals("Sinfo"))
	            	TabStamm.showGrid("Stamm",DlgTest);
	            else if (s.equals("Binit"))
	            {
					VecBew.removeAllElements();
					BtnBewInfo.setText("0");
				}
	            else if (s.equals("Bund"))
	            {
	            	VecBew.addElement(EdtBew.getInteger());
					BtnBewInfo.setText(""+VecBew.size());
				}
	            else if (s.equals("Binfo"))
	            	new Tabellenspeicher(g,"select aic_bew_pool,anr,gueltig,(select bezeichnung from stammview2 where aic_stamm=p.anr and aic_rolle is null) Stammsatz"+
	            			g.AU_Bezeichnung1("Bewegungstyp","p")+" Bew_Typ,(select timestamp from protokoll where aic_protokoll=p.aic_protokoll) angelegt from bew_pool p where aic_bew_pool"+Static.SQL_in(VecBew),true).showGrid("VecBew",DlgTest);
//	            	g.fixtestError("VecBew="+VecBew);
	            else
	            	Static.printError("DefAufgabe: "+s+" wird noch nicht unterstützt");
	          }
	        };
			final JButton BtnTestInit = g.BtnAdd(g.getButton("Init"),"Sinit",AL);
			final JButton BtnTestUnd = g.BtnAdd(g.getButton("Und"),"Sund",AL);
			g.BtnAdd(BtnTestInfo,"Sinfo",AL);
			final JButton BtnBewInit = g.BtnAdd(g.getButton("Init"),"Binit",AL);
			final JButton BtnBewUnd = g.BtnAdd(g.getButton("Und"),"Bund",AL);
			g.BtnAdd(BtnBewInfo,"Binfo",AL);

			//CboStamm.fillStammTable(riStammtyp,true);

			DatStichtag.setDate(g.dtStichtag);


			DlgTest.getContentPane().setLayout(new BorderLayout(2,2));

			JPanel PnlN = new JPanel(new BorderLayout(2,2));
			PnlN.add("West",BtnTestInit);
                        JPanel PnlG=new JPanel(new GridLayout(1,1,5,0));
                        PnlG.add(BtnTestUnd);
                        PnlG.add(CbxAlleKein);
			PnlN.add("Center",PnlG);
			PnlN.add("East",BtnTestInfo);
//			DlgTest.getContentPane().add("North",Pnl);
			JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
			if (g.Def())
			{
				g.addLabel2(Pnl,"Name");
//				g.addLabel2(Pnl,"PW");
			}
            g.addLabel2(Pnl,"Stamm");       // 1
            Pnl.add(new JLabel());			// 2
            g.addLabel2(Pnl,"BewA"); 		// 3
			g.addLabel2(Pnl,"Datum von");   // 4
            g.addLabel2(Pnl,"StringJoker"); // 5
            g.addLabel2(Pnl,"BewJoker");    // 6
            g.addLabel2(Pnl,"Joker");       // 7
			g.addLabel2(Pnl,"Zeilen");      // 8
			g.addLabel2(Pnl,"Checkboxen");	// 9
                        if (g.Def())
                        	Pnl.add(new JLabel());
			DlgTest.getContentPane().add("West",Pnl);
			Pnl = new JPanel(new GridLayout(0,1,2,2));
			if (g.Def())
			{
				Pnl.add(EdtName);	
//				Pnl.add(EdtPW);	
			}
			Pnl.add(SteStamm);    	// 1 Stamm
			Pnl.add(PnlN);			// 2
			  JPanel PnlAuswahl2=new JPanel(new GridLayout(1,4,5,0));
			  PnlAuswahl2.add(EdtBew);
			  PnlAuswahl2.add(BtnBewUnd);
			  PnlAuswahl2.add(BtnBewInfo);
			  PnlAuswahl2.add(BtnBewInit);
            Pnl.add(PnlAuswahl2);	// 3 Bewegung
              PnlAuswahl2=new JPanel(new GridLayout(1,3,5,0));
              PnlAuswahl2.add(DatVon);
              g.addLabel2(PnlAuswahl2,"Datum bis");
              PnlAuswahl2.add(DatBis);
              g.addLabel2(PnlAuswahl2,"Zeitart");
              PnlAuswahl2.add(TxtZA);     
            Pnl.add(PnlAuswahl2);	// 4
                        PnlAuswahl2=new JPanel(new GridLayout(1,3,5,0));
                        PnlAuswahl2.add(EdtJoker);
                        g.addLabel2(PnlAuswahl2,"Stichtag");
                        PnlAuswahl2.add(DatStichtag);
                        Pnl.add(PnlAuswahl2); // 3
                        /*PnlAuswahl2=new JPanel(new GridLayout(1,3,5,0));
                        PnlAuswahl2.add(SteJoker);*/
                        //Pnl.add(PnlAuswahl2);
                        PnlAuswahl2=new JPanel(new GridLayout(1,0,5,0));
                        PnlAuswahl2.add(EdtBewJoker);
                        g.addLabel2(PnlAuswahl2,"DoubleJoker");
                        PnlAuswahl2.add(EdtDoubleJoker);
                        //PnlAuswahl2.add(new JLabel());
                        Pnl.add(PnlAuswahl2); // 4
                        Pnl.add(SteJoker);    // 5
                        PnlAuswahl2=new JPanel(new GridLayout(1,0,5,0));
                        PnlAuswahl2.add(EdtZeilen);
                        g.addLabel2(PnlAuswahl2,"Zeilenhöhe");
                        PnlAuswahl2.add(EdtHoehe);
                        g.addLabel2(PnlAuswahl2,"Spalten");
                        PnlAuswahl2.add(EdtSpalten);
                        Pnl.add(PnlAuswahl2); // 6
                        PnlAuswahl2=new JPanel(new FlowLayout(FlowLayout.LEFT));//new JPanel(new GridLayout(1,4,5,0));
                        //Panel PnlRest=new Panel(new FlowLayout(FlowLayout.LEFT));
                        PnlAuswahl2.add(CbxModell);
                        if (g.Def())
                        	PnlAuswahl2.add(CbxDebug);
                        PnlAuswahl2.add(CbxDruck);
                        JPanel PnlAuswahl3=null;
                        if (g.Def())
                        {
                          PnlAuswahl2.add(CbxOriginal);
                          PnlAuswahl2.add(CbxNeu);
                          PnlAuswahl3=new JPanel(new FlowLayout(FlowLayout.LEFT));
                          if (g.ASA())
                            PnlAuswahl3.add(CbxClearCache);
                          if (g.MySQL() || g.MS())
                            PnlAuswahl3.add(CbxExplain);
                          PnlAuswahl2.add(CbxAlleSpalten);
//                          PnlAuswahl2.add(CbxCompress2);
                          PnlAuswahl2.add(CbxUseVec);
                          //PnlAuswahl2.add(CbxView2b);
                          PnlAuswahl3.add(CbxThread);
                          PnlAuswahl3.add(CbxForExport);
                          PnlAuswahl3.add(CbxWeb2);
                          PnlAuswahl3.add(CbxZeitzone);                       
                        }
                        PnlAuswahl2.add(BtnSync);
                        Pnl.add(PnlAuswahl2);
                        if (PnlAuswahl3!=null)
                        	Pnl.add(PnlAuswahl3);
			DlgTest.getContentPane().add("Center",Pnl);
			JButton BtnAufrufOk = g.getButton("Ok");
                        ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnAufrufOk);
                        BtnSync.addActionListener(new ActionListener()
                        {
                          public void actionPerformed(ActionEvent ev)
                          {
                            SyncStamm.start(g,null).show();
                          }
                        });


			BtnAufrufOk.addActionListener(new ActionListener()
			{
                          public void actionPerformed(ActionEvent ev)
                          {
                            java.sql.Timestamp dtVon = g.getVon();
                            java.sql.Timestamp dtBis = g.getBis();
                            String sName=EdtName.getText();
                            boolean bName=!Static.Leer(sName);
                            Global g2=g;
                            Static.bWeb=CbxWeb2.isSelected();
                            if (bName)
            				{
            					g2=new Global(g,!bName);
            					if (!g2.Login(DlgTest,sName, null))//EdtPW.getValue()))
            					{
            						new Message(Message.WARNING_MESSAGE, DlgTest, g).showDialog("Einloggfehler", new String[] {sName});
            						return;
            					}
            					else
            					{
            						ShowAbfrage.refreshBerechtigung(g2);
            					}
            					if (!CbxOriginal.isSelected())
            					{
            						A.g=g2;
            						g2.VecJoker=g.VecJoker;
            					}
            				}
                            
                            g2.dtStichtag=DatStichtag.getDate();
                            if ((A.lBits2&Abfrage.cstLokZR)>0)
                            {
                            	//A.setVonBis(DatVon.getDateTime(),DatBis.getDateTime());
                            	if (g.MySQL() || g.MS())
                            	{
                            		A.iVB=g.setVonBisL(DatVon.getDateTime(),DatBis.getDateTime(),TxtZA.getText(),0,A.sDefBez,null);
                            		Zeitraum.PeriodeToVec2(g,A.iVB,TxtZA.getText(),false,false);
                            		if ((A.iBits&A.cstLDATE)>0)
                            		{
                            			A.iBits-=A.cstLDATE;
                            			g.fixtestError("LDATE bei lok. ZR ausgeschaltet");
                            		}
                            	}
                            	else
                            	{
                            		Static.printError("Lokaler Zeitraum aktuell nur mit MySQL möglich!");
//                            		g.setVonBis(DatVon.getDateTime(),DatBis.getDateTime());
//                            		Zeitraum.PeriodeToVec(g,g.sZeitart);
                            	}
                            }
                            else
                            {
	                            g2.setVonBis(DatVon.getDateTime(),DatBis.getDateTime());
	                            Zeitraum.PeriodeToVec(g,g.getZA(0));
                            }
                            g2.iJoker=SteJoker.getStamm();
                            g2.dJoker=EdtDoubleJoker.doubleValue();
                            g2.sJoker=EdtJoker.getText();
                            if (EdtBewJoker.intValue()!=0)
                            {
                              g2.initJokerBew();
                              g2.addJokerBew(EdtBewJoker.intValue());
                            }
                            //g.progInf("ZR="+g.getVon()+"-"+g.getBis());
                            if (CbxClearCache.isSelected())
                              g.exec("call sa_flush_cache()");
                            Static.bZeitzone=CbxZeitzone.isSelected();
                            //g.fixtestError("Zeitzone="+Static.bZeitzone+"/"+CbxZeitzone.isSelected());
                            TesteAbfrage(SteStamm.getStamm(),SteStamm.getStt(),TabStamm.isEmpty()?SteStamm.getStamm()==0 ? null:Static.AicToVec(SteStamm.getStamm()):TabStamm.getVecSpalte("Aic"),
                                         EdtZeilen.intValue(),EdtHoehe.intValue(),EdtSpalten.intValue(),CbxModell.isSelected(),CbxAlleKein.isSelected() && TabStamm.isEmpty(),
                                         CbxDruck.isSelected(),CbxAlleSpalten.isSelected(),CbxUseVec.isSelected(),CbxExplain.isSelected(),
                                         CbxThread.isSelected(),CbxNeu.isSelected(),CbxForExport.isSelected(),CbxWeb2.isSelected(),CbxOriginal.isSelected(),CbxDebug.isSelected(),
                                         VecBew,g2,bName);
                            DlgTest.setVisible(false);
                            if (!bName)
                              if (A.iVB>0)
                              {
                            	g2.setVB_Fertig(A.iVB);
                            	A.iVB=0;
                              }
                              else
                              {
                            	g2.setVon(dtVon);
	                            g2.setBis(dtBis);
	                            Zeitraum.PeriodeToVec(g2,g2.getZA(0));
                              }
                            //Static.bZeitzone=false;
                          }
			});
			JButton BtnAufrufAbbruch = g.getButton("Abbruch");
			BtnAufrufAbbruch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					DlgTest.setVisible(false);
                                        //g.setVon(dtVon);
                                        //g.setBis(dtBis);
                                        //Zeitraum.PeriodeToVec(g,Static.sZeitart);
				}
			});
			Pnl = new JPanel(new GridLayout(1,0,2,2));
			Pnl.add(BtnAufrufOk);
			Pnl.add(BtnAufrufAbbruch);
			DlgTest.getContentPane().add("South",Pnl);
			//DlgTest.pack();
			DlgTest.setSize(580, 400);
                        //g.progInfo("Spaltendef-Größe:"+DlgTest.getHeight()+"x"+DlgTest.getWidth());
			Static.centerComponent(DlgTest,thisFrame);
		}
    CbxWeb2.setSelected(A.bWeb);
                //CbxView2b.setSelected(Static.bView2 && (A.iBits&A.cstView2)>0);
		CbxModell.setEnabled(A.iModell>0);
		CbxDebug.setEnabled(A.iModell>0);
		if (A.iModell==0)
		{
			CbxModell.setSelected(false);
			CbxDebug.setSelected(false);
		}
		boolean bABK=(A.lBits2&Abfrage.cstQryAlle)>0;
		CbxAlleKein.setEnabled(TabStamm==null || TabStamm.isEmpty() || bABK);//(A.iBits&A.cstMengen)==0);
		if (bABK)
			CbxAlleKein.setSelected(true);
                int iStt2=TabAbfragen.getI("aic_stammtyp");
                if (iStammtyp<0 && (iStt2==0 || iStt2==g.iSttANR) && g.getStamm()>0 && SteStamm.getStamm()==0)// && (iStammtyp==0 || iStammtyp==g.getStt()))
                {
                  SteStamm.setStt(g.iSttANR);
                  SteStamm.setStamm(g.getStamm());
                }
                else if (iStammtyp<0 && A.iStt != SteStamm.getStt())
                  SteStamm.setStt(A.iStt);
                else if (!Static.bTestFenster || SteStamm.getStamm()==0)
                  SteStamm.setStt(iStammtyp>0?iStammtyp:0);
                if (A.iStt>0)
                  SteStamm.setStamm(g.getSyncStamm(A.iStt,A.iRolle));
                if ((A.lBits2&Abfrage.cstLokZR)==0 || DatVon.isNull() || !g.MySQL() && !g.MS())
                {
                	DatVon.setDate(g.getVon());
                	DatBis.setDate(g.getBis());
                }
                if (Static.bTestFenster)
                  DlgTest.setVisible(true);
                else
                {
                  TesteAbfrage(SteStamm.getStamm(),SteStamm.getStt(),TabStamm.isEmpty()?SteStamm.getStamm()==0 ? null:Static.AicToVec(SteStamm.getStamm()):TabStamm.getVecSpalte("Aic"),
                                         100,18,1,false,true,false,false,false,false,false,true,false,false,false,false,null,g,false);
                }
		//g.setStichtag(dtStichtag);

		//g.progInfo("ZR="+g.getVon()+"-"+g.getBis());
	}

	private boolean AbfrageOK()
	{
		if (A.bSpalten)
		{
			int iMax=(int)A.TabSp.max("sortiert");
			//g.fixInfo("max Sortiert="+iMax);
			for (int i=1;i<=iMax;i++)
				if (A.TabSp.count("sortiert",new Integer(i))>1)
				{
					new Message(Message.WARNING_MESSAGE,null,g).showDialog("Sortierung ungueltig");
					return false;
				}
      Vector<String> Vec=new Vector<String>(); 
      for(int i=0;i<A.TabSp.size();i++)
      {
        String s=A.TabSp.getS(i,"Stil");
        if (!Static.Leer(s))
          if (Vec.contains(s))
          {
            new Message(Message.WARNING_MESSAGE,null,g).showDialog("Kennzeichen_mehrfach",new String[]{s});
			return true;
          }
          else
            Vec.addElement(s);
      }
		}
		return true;
	}

	private void TesteAbfrage(int iStamm_Mom,int iStt_Mom,Vector VecStamm,int iZeilen,int iHoehe,int iSpalten,boolean bMitModell,boolean bAlle,boolean bDruck,
                                  boolean bAlleSpalten,boolean bUseVec,boolean bExplain,boolean bThread,boolean bNeu,boolean bForExport,boolean bWeb2,boolean bOriginal,boolean rbDebug,
                                  Vector<Integer> VecBew,Global g2,boolean bName)
	{
//		g.fixInfo("TesteAbfrage:"+iStamm_Mom+"/"+VecStamm);
		g.initSI();
		
                if (A.sDefBez==null)
                  A.sDefBez=TabAbfragen.getS("DefBez");
                              
		g.progInfo("TesteAbfrage:"+A.sDefBez+"/"+A.iAbfrage+"/"+TabAbfragen.getI("Aic"));

		//if(iAbfrage !=0)
		//	TabAbfragen.posInhalt("Aic",iAbfrage);
		Static.bWeb=false;
		if (!AbfrageOK())
			return;

		if(A.iAbfrage>0 && A.iAbfrage != TabAbfragen.getI("Aic"))
			return;
		Static.bWeb=bWeb2;
		new Thread(new Runnable()
		{
			public void run()
			{
				long lClock=Static.get_ms();				
//				g.fixtestError(sName+"/"+sPW+"/"+VecBew);
				boolean bDebug=g.Debug();
//				Global g2=g;
				
                g2.setDebug(rbDebug);             
				ShowAbfrage Acopy=null;		        
				if (bOriginal)
		        {
		        	Acopy=A;
		        	A=new ShowAbfrage(g2,bWeb2,A.iAbfrage,Abfrage.cstAbfrage,false);
		        }
				else if (bWeb2)
	                	A.setWeb();
//				else if (bName)
//				{
//					A.g=g2;
//					g2.VecJoker=g.VecJoker;
////					A.SQL_String();
//				}
                A.iRolle=iRolle;
                A.bAlleSpalten=bAlleSpalten;
                A.bForExport=bForExport;
		//long l=TabAbfragen.getL("bits");
		//g.progInfo("TesteAbfrage.bits="+i);

		/*Vector VecAbf=new Vector();
		SQL_String(g,i,iStammtyp,iBewegungstyp,(JCOutlinerFolderNode)Gid.getRootNode(),VecAbf,false);
		Tabellenspeicher Tab2 = (Tabellenspeicher)VecAbf.elementAt(0);
		Vector Vec = (Vector)VecAbf.elementAt(1);*/
		//A.TabSp=TabSpalten;
		//A.NodBed=findNode(Gid.getRootNode(),true);
                if (VecStt != null)
                {
                  //g.progInfo("Stt vorher=" + A.iStt + ", nachher=" +iStammtyp);
                  if (iStammtyp>0)
                  {
                    A.iStt = iStammtyp;
                    A.iBew = 0;
                  }
                  else
                    A.iBew=-iStammtyp;
                }
                if (A.TabSp != null)
                  for (A.TabSp.moveFirst();!A.TabSp.eof();A.TabSp.moveNext())
                        {
                                if (!A.TabSp.isNull("Gruppe"))
                                        A.iBits|=Abfrage.cstGruppe;
                                if (!A.TabSp.isNull("Calc"))
                                        A.iBits|=Abfrage.cstCalc;
                        }
		long l=A.iBits;
                int iTopOld=A.iTop;
                //A.TabSp.showGrid("TabSp");
                if (A.TabSp != null)
                  for (A.TabSp.moveFirst();!A.TabSp.eof();A.TabSp.moveNext())
                    if (!A.TabSp.isNull("aic_stammtyp"))
                      A.iBits=A.iBits | Abfrage.cstHierarchie;
                if ((A.iBits & (Abfrage.cstSumme+Abfrage.cstFirst))==0)
                {
                  A.iTop = iZeilen;
                  A.iBits=A.iBits | Abfrage.cstFirst;
                }
//                if ((A.lBits2&Abfrage.cstLokZR)>0)
//                {
//                	long lV=A.iBits;
//                	A.iBits=A.iBits | (Abfrage.cstKeinZeitraum+Abfrage.cstKeinStammZeitraum);
//                	if (lV != A.iBits)
//                		g.fixtestError("bits geändert von "+lV+" auf "+A.iBits);
//                }
                //g.progInfo("Bits="+A.iBits+", Top="+A.iTop);

		if (bMitModell)
                  A.iModell=TabAbfragen.getI("AIC_Modell");
                else
                  A.iModell=0;
		boolean bBewVec=VecBew != null && VecBew.size()>0;
		if (bAlle && iStamm_Mom==0 && !bMitModell && !bBewVec)
			A.iBits=A.iBits | Abfrage.cstKeinStamm;
		//g.progInfo("vor SQL_String");
                if (bNeu || A.VecAbf==null)
                {
                  if (iStt_Mom>0)
                    A.iStt=iStt_Mom;
                  A.clearCalc();
                  A.SQL_String();
                }
                //A.TabSp.showGrid("TabSp");
                //A.getSpalten().showGrid("TabSpalten");
		//g.printSI("Init");

		//String s=VecAbf.elementAt(2)+(iStammtyp>=0 && iStamm_Mom==0||A.is(i,Abfrage.cstKeinStamm)?"":" and aic_stamm="+iStamm_Mom)+VecAbf.elementAt(3);
		//g.progInfo(s);
		//g.progInfo("vor getDaten");
                if (bThread && !bName)
                  A.g=new Global(g,true);
                Tabellenspeicher Tab=null;
                int iPos=g.TabBegriffe.getPos("Aic", A.iBegriff);
                String sTyp= iPos<0 ? "":g.TabCodes.getKennung(g.TabBegriffe.getI(iPos,"Typ"));

//                g.fixtestError("Web="+bWeb2+", Typ="+sTyp);
                A.bTest=true;
                Timestamp TSZeitraumVon=g.getVon(A.iVB);
                Timestamp TSZeitraumBis=g.getBis(A.iVB);
                boolean bJahr=(A.lBits2&Abfrage.cstAbfJahr)>0;
                A.setYear(bJahr);
                if (bWeb2 && sTyp != null && sTyp.equals("Haupt") || bBewVec)
    			{
//                	g.fixtestError("verwende VecBew:"+VecBew);
    				Tab=A.getEF_Daten(A.iBegriff, A.iStt, A.iRolle, 0/*iStammVor*/, iStamm_Mom, 0/*iBewSatz*/, false/*iFT==2*/,bBewVec, false/*iFirma>0*/, bBewVec ? VecBew:VecStamm, sTyp, 0/*iEig*/, true, false/*bKopierbar*/, null);
    			}
    			else
    				Tab=A.getDaten(iStt_Mom>0 ? iStt_Mom:iStammtyp>0?iStammtyp:0,iStamm_Mom,VecStamm,bUseVec,false,thisFrame);//new Tabellenspeicher(g,s,true);
                A.bTest=false;
                //g.progInfo("String="+A.TabToString(Tab));
                g.progInfo(A.sSQL);
		A.iTop=iTopOld;

		//g.progInfo("Abfrage"+A.iAbfrage+":"+A.sSQL);
                //g.progInfo("Druckbreite="+A.getVecDruckbreite());
		bSpeichern= Tab != null && !Tab.fehler();
		//g.printSI("Get");
                Tabellenspeicher TabSpalten=A.getSpalten();
		if (!bSpeichern)
                {
                  A.iModell=TabAbfragen.getI("AIC_Modell");
                  A.iBits=l;
                  EnableButtons();
                  new Message(Message.WARNING_MESSAGE,null,g).showDialog("AbfrageFehlerhaft");
                  if (bName)
                  {
                  	g2.Logout();
                  	A.g=g;
                  }
                  Static.bWeb=false;
                  return;
                }
//                else if (CbxCompress2.isSelected())
//                  Tab.compress2(TabSpalten,g.TabCodes);


		DlgTab=new JDialog((Frame)thisFrame,g.getBegriffS("Dialog","Test-Daten"),false);
		JTabbedPane Pane = new JTabbedPane();
                GidGesamt = new AUOutliner();
		ShowAbfrage.initOutliner(g,GidGesamt);
                AUOutliner GidDaten = new AUOutliner();
		AUOutliner GidSpalten = new AUOutliner();
                AUOutliner GidExplain = new AUOutliner();
//        if (bJavaFX)
//        	A.setJavaFX();     
        
		A.TabToOutliner(GidGesamt,Tab,null,null,iSpalten);
		if (bJahr && A.iVB==0)
		 {
        	g.setVonBis(TSZeitraumVon,TSZeitraumBis,A.iVB);
        	Zeitraum.PeriodeToVec2(g,A.iVB,g.getZA(A.iVB),true,false);
        }
		if (bWeb2)
			A.clearWeb();
		Static.bWeb=false;
//		else
            GidGesamt.setNodeHeight(iHoehe);
		//Tabellenspeicher TabGesamt=A.bSpalten ? A.TabToOutliner(g,GidGesamt,Tab,A):null;
		//if (A.bSpalten || !TabSpalten.isEmpty())
		//{
			Pane.add(g.getBegriffS("Show","Gesamt"),GidGesamt);
                        //GidGesamt.setBackground(g.ColBackground);
			//TabGesamt.showGrid(GidGesamt);
			//GidGesamt.setColumnAlignments(A.getAusrichtung());
		//}
                if (g.Def())
                {
                	
                  if (A.TabD != null)
                  {
                	  AUOutliner GidTree = new AUOutliner();
                	  Pane.add(g.getShow("Tree"), GidTree);
                	  A.TabD.showGrid(GidTree);
                  }
                  Pane.add(g.getShow("Daten"), GidDaten);
                  Pane.add(g.getShow("Spalten"), GidSpalten);
                  if (bExplain)
                  {
                    if (g.MySQL())
                      new Tabellenspeicher(g,"explain "+Tab.sSQL,Tab.sBV,true).showGrid(GidExplain);
                    else if (g.MS())
                    {
                      g.exec("SET SHOWPLAN_ALL ON");
                      new Tabellenspeicher(g,Tab.sSQL,Tab.sBV,true).showGrid(GidExplain);
                      g.exec("SET SHOWPLAN_ALL OFF");
                    }
                    Pane.add(g.getBegriffS("Show", "explain"), GidExplain);
                  }
                  Tab.showGrid(GidDaten);
                  TabSpalten.showGrid(GidSpalten);

                //  if (A.iBew>0)
                 {
                  ActionListener AL=new ActionListener()
                  {
                    public void actionPerformed(ActionEvent ev)
                    {
                      String s = ev.getActionCommand();
                      g.progInfo("DefAbfrage.Test=" + s);
                      if (s.equals("Loeschen") || s.equals("Undelete"))
                      {
                        Vector<Integer> Vec=g.getAics(GidGesamt);
                        if (Vec.size()>0)
                        {
                          boolean bDel=s.equals("Loeschen");
                          g.progInfo((bDel ? "lösche ":"wiederherstelle") + Vec);
                          int iAnz=0;
                          if (A.iBew>0)
                            iAnz= g.exec("update bew_pool set pro_aic_protokoll="+(bDel ? g.Protokoll("Entfernen", getBegriff())+" where pro_aic_protokoll is null and":
                              "null where pro_aic_protokoll is not null and")+g.in("aic_bew_pool",Vec));
                          else if (new Message(Message.YES_NO_OPTION,DlgTab,g,15).showDialog("Entfernen_oP") == Message.YES_OPTION)
                            iAnz=g.exec("update stamm_protokoll set pro_aic_protokoll="+g.Protokoll("Entfernen", getBegriff())+" where pro_aic_protokoll is null and"+g.in("aic_stamm",Vec));
                          if (iAnz>0)
                          {
                            g.diskInfo(iAnz+(A.iBew>0 ? " Bewegungsdaten ":" Stammdaten ")+(bDel ? "gelöscht":"wiederhergestellt"));
                            JCOutlinerNode[] Nodes=GidGesamt.getSelectedNodes();
                            for (int i=0;i<Nodes.length;i++)
                            {
                              JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
                              Nodes[i].setStyle(StyFolder);
                              Nodes[i].getStyle().setForeground(bDel ? Color.red : Color.green);
                              //Nodes[i].setStyle(Nodes[i].getStyle());
                            }
                          }
                        }
                      }
                      else if (s.equals("History"))
                      {
                    	  int iPool=Sort.geti(GidGesamt.getSelectedNode().getUserData());
                        if (A.iBew>0)
                    	    A.showHistory2(iNr==0?This1:This2, iPool, A.iModell2, false);
                        else
                          ShowAbfrage.showHistory3(g, iNr==0?This1:This2, iPool, null);
                      }
                      else if (s.equals("set_Sync"))
                      {
                        int iAic=Sort.geti(GidGesamt.getSelectedNode().getUserData());
                        int iPos = g.TabErfassungstypen.getPos("Aic", A.iBew);
                        g.TabErfassungstypen.setInhalt(iPos, "Pool", iAic);
                      }
                      else if (s.equals("set_Joker"))
                        g.setJokerBew(g.getAics(GidGesamt));
                    }
                  };
                  final JPopupMenu popupGes=new JPopupMenu();
                  if (g.History())
                	  g.addMenuItem("History",popupGes).addActionListener(AL);
                  g.addMenuItem("Loeschen",popupGes).addActionListener(AL);
                  if (A.iBew>0)
                  {
                    g.addMenuItem("Undelete",popupGes).addActionListener(AL);
                    g.addMenuItem("set_Sync",popupGes).addActionListener(AL);
                    g.addMenuItem("set_Joker",popupGes).addActionListener(AL);
                  }
                  GidGesamt.getOutliner().addMouseListener(new MouseListener()
                  {
                    public void mousePressed(MouseEvent ev) {}
                    public void mouseClicked(MouseEvent ev)
                    {
                      //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
                      if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
                      {
                        popupGes.show(GidGesamt, ev.getX(), ev.getY() + 16);
                      }
                    }
                    public void mouseEntered(MouseEvent ev) {}
                    public void mouseExited(MouseEvent ev) {}
                    public void mouseReleased(MouseEvent ev) {}
                  });
                 }

                }
                if (bName)
                	g2.Logout();
		DlgTab.getContentPane().add("Center",Pane);
		DlgTab.setSize(800,600);
		Static.centerComponent(DlgTab, thisFrame);
		g.printSI("Show");
        g.setDebug(bDebug);
		DlgTab.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		g.clockInfo(TabAbfragen.getS("DefBez"),lClock);
                DlgTab.setVisible(true);
		if (bDruck)
                {
                 All_Unlimited.Print.DruckHS dh=new All_Unlimited.Print.DruckHS(g,"DefAbfrage",0,0,0);
                 dh.LoadRaster(0);
                 //dh.TabRaster=new Tabellenspeicher(g,"select aic_raster,kennung "+g.AU_Bezeichnung("raster")+",aic_schrift,sch_aic_schrift,sch2_aic_schrift,sch3_aic_schrift,sch4_aic_schrift,sch5_aic_schrift,sch6_aic_schrift,bits from raster where "+
                 //                                 g.bit("bits",All_Unlimited.Print.Drucken.cstStandard),true);
                 dh.setDTitel(Static.beiLeer(TabAbfragen.getS("Bezeichnung"), TabAbfragen.getS("DefBez")),false,g.fontTitel);
                 dh.printTitel(false,true,false,false);
                 dh.addOutliner(GidGesamt,A,0,-1,0,A.getTabDruckbreite(0));
                 dh.vorschau();
                }
                if (!bMitModell)
                  A.iModell=TabAbfragen.getI("AIC_Modell");
                A.iBits=l;
                A.bAlleSpalten=false;
                A.bForExport=false;
//                TabJoker=new Tabellenspeicher(A.TabJoker,g);
                if (Acopy != null)
                	A=Acopy;
                A.g=g;
		EnableButtons();
                if (iHoehe>18)
                new Thread(new Runnable()
                {
                  public void run() {
                    try {
                      //g.progInfo("1:"+System.currentTimeMillis());
                      //Static.sleep(1000);
                      /*GidGesamt.validate();
                      g.progInfo("GidGesamt.validate()");
                      g.progInfo("2:"+System.currentTimeMillis());
                      Static.sleep(2000);
                      DlgTab.doLayout();
                      g.progInfo("DlgTab.validate()");*/
                      //g.progInfo("3:"+System.currentTimeMillis());
                      java.awt.Dimension dim=DlgTab.getSize();
                      DlgTab.setSize(50,50);
                      Static.sleep(200);
                      DlgTab.setSize(dim);
                      //g.progInfo("4:"+System.currentTimeMillis());
                    }
                    catch(Exception e) {}
                }
              }).start();
			}
		}).start();

	}

// ----------------------------------------------------------------------------------------------------------------



	private boolean isSpalten(JCOutlinerNode rNod)
	{
          g.progInfo("isSpalten:"+rNod);
          if (rNod != null)
            g.progInfo(rNod.getLevel()+"/"+rNod.getUserData());
		return rNod != null && rNod.getLevel()==1 && rNod.getUserData()==null;
	}
	
	private boolean isVorbedingung(JCOutlinerNode rNod)
	{
		while (rNod.getLevel()>1)
			rNod=rNod.getParent();
		
		return rNod != null && rNod.getLevel()==1 && rNod.getUserData().equals("V");
	}

	private void setSpalten()
	{
		//iSpalten=i;
		if (g.Def())
		{
			//JCOutlinerFolderNode NodSpalten=findNode(Gid.getRootNode(),false);
			if (A.bSpalten)
				A.NodSpalten.setLabel(A.Spalten()+" "+g.getBegriffS("Show","Spalten"));
		}
		g.testInfo("Abfrage="+A.iAbfrage+" / Spalten="+A.Spalten());
	}

	/*
	private JCOutlinerFolderNode findNode(JCOutlinerNode NodSuche,boolean bBedingung)
	{
		Vector Vec=NodSuche.getChildren();
		return Vec==null || Vec.size()==0 ? null:Vec.size()==2 ? (JCOutlinerFolderNode)Vec.elementAt(bBedingung ? 0:1):
			bBedingung ^ ( (JCOutlinerFolderNode)Vec.elementAt(0)).getUserData()==null ? (JCOutlinerFolderNode)Vec.elementAt(0):null;
	}*/
}

