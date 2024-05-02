/*
    All_Unlimited-Print-DruckFrage.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Print;

import java.awt.Color;
// add your custom import statements here
//import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Event;
import java.awt.FlowLayout;
//import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
//import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
//import All_Unlimited.Allgemein.DateWOD;
//import All_Unlimited.Allgemein.Diagramm;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
//import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.SyncStamm;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.DruckEingabe;
import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Grunddefinition.DefAbfrage;
import All_Unlimited.Grunddefinition.DefAbfrage2;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.Export;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Zeitraum;
//import java.awt.event.MouseListener;
//import javax.swing.JComponent;
//import java.awt.event.MouseEvent;
import All_Unlimited.Grunddefinition.DefModell;
import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
import javax.swing.JToggleButton;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;

public class DruckFrage extends All_Unlimited.Allgemein.Formular
{
/*public static DruckFrage start(JCOutliner rGid, int riAIC_Stammtyp,int riAIC_Selected, Global glob)
{
	return start(rGid,riAIC_Stammtyp,riAIC_Selected,glob,0);
}*/

public static DruckFrage start(AUOutliner rGid,int riRolle,int riAIC_Stammtyp,int riAIC_Selected,Vector rVec,Global glob,int riDruck,int riBeg)
{
         //glob.fixtestInfo("DruckFrage:"+riDruck+"/"+riBeg+", Select="+riAIC_Selected);
         iBeg=riBeg;
	//glob.progInfo("DruckFrage:"+riAIC_Stammtyp+"/"+riAIC_Selected);
        //Vector VecModule=SQL.getVector("select z.aic_begriff from begriff_zuordnung z join begriff b on b.aic_begriff=z.aic_begriff"+glob.join("begriffgruppe","b")+
        //                        " where begriffgruppe.kennung='Modul' and beg_aic_begriff="+iBeg,glob);
        //glob.progInfo("VecModule="+VecModule);
        //VecDrucke=SQL.getVector("select distinct b.aic_begriff from begriff_zuordnung z join begriff b on b.aic_begriff=z.beg_aic_begriff"+glob.join("begriffgruppe","b")+
        //                        " where begriffgruppe.kennung='Druck' and z.aic_begriff"+Static.SQL_in(VecModule),glob);
        VecDrucke=SQL.getVector("select distinct b.aic_begriff from begriff_zuordnung z join begriff b on b.aic_begriff=z.aic_begriff and b.aic_begriffgruppe="+glob.TabBegriffgruppen.getAic("Druck")+
                                " where beg_aic_begriff="+iBeg,glob);
        //VecAbfragen=SQL.getVector("select distinct b.aic_begriff from begriff_zuordnung z join begriff b on b.aic_begriff=z.beg_aic_begriff"+glob.join("begriffgruppe","b")+
        //                        " where begriffgruppe.kennung='Abfrage' and z.aic_begriff"+Static.SQL_in(VecModule),glob);
        //glob.progInfo("VecAbfragen="+VecAbfragen);
	iDruck=riDruck>0 ? riDruck:0;
        //iAbfrage=riDruck<0 ? -riDruck:0;
        iRolle=riRolle;
        VecStamm=rVec;
	iAIC_Stammtyp = riAIC_Stammtyp;
	bHauptschirm=iAIC_Stammtyp==0;
	Gid=rGid;
	//RootNode = rRootNode;
	iAIC_Selected = riAIC_Selected;
	//sHeader=rsHeader==null?new String[] {"Bezeichnung"}:rsHeader;
	return This==null?new DruckFrage(glob):This;
}

public static void free()
{
	if (This!=null)
	{
          This.Para.free();
		This.g.testInfo("DruckFrage.free");
		This.thisFrame.dispose();
		This=null;
	}
}

private DruckFrage(Global glob)
{
	super(glob.Workflow()?"DruckFrage3":"DruckFrage2",null,glob);
	g = glob;

	This=this;
	//g.putTabFormulare(getBegriff(),0,this);

	//iTabBegriff=glob.TabTabellenname.getAic("BEGRIFF");

	Build();
        LoadParameter();
        //if (g.Workflow())
        if (!bHauptschirm)
          RadDruck.setSelected(true);
}

private void getRootNodeIch(JCOutlinerNode Nod,boolean bIch)
{
	Vector Vec = Nod.getChildren();
        if (Vec != null)
		for(int i=0;i<Vec.size();i++)
		{
			Nod=(JCOutlinerNode)Vec.elementAt(i);
			int iStamm = bHauptschirm ? ((Integer)((Vector)Nod.getUserData()).elementAt(0)).intValue():Sort.geti(Nod.getUserData());
			if (iStamm==g.getStamm())
			{
				RootNodeIch.removeChildren();			
				JCOutlinerNode NodNeu=new JCOutlinerNode(Nod.getLabel(),RootNodeIch);
				NodNeu.setUserData(Nod.getUserData());
                bIch=true;
//                g.fixtestError("getRootNodeIch "+iStamm+" -> "+NodNeu.getLabel());
			}
			else if (Nod instanceof JCOutlinerFolderNode)
				getRootNodeIch(Nod,bIch);
		}
//        g.fixtestError("getRootNodeIch:"+bIch+"/"+g.getStamm()+"/"+RootNodeIch.getChildren());
        if (!bIch && g.getStamm()>0 && RootNodeIch.getChildren()==null)
        {
          JCOutlinerNode NodNeu=new JCOutlinerNode(g.getStamm(g.getStamm())/*SQL.getString(g,"select bezeichnung from stammview2 where aic_rolle is null and aic_stamm="+g.getStamm())*/,RootNodeIch);
          if (bHauptschirm)
          {
            Vector<Integer> Vec2=new Vector<Integer>();
            Vec2.addElement(new Integer(g.getStamm()));
            Vec2.addElement(new Integer(g.iSttANR));
            Vec2.addElement(new Integer(0));
            NodNeu.setUserData(Vec2);
            bIch=true;
          }
          else
            NodNeu.setUserData(new Integer(g.getStamm()));
        }
}

private void checkNurIch()
{
	Out.setRootNode(g.NurSelbst(RadDruck.isSelected()? CboDruck.Cbo.getSelectedAIC():CboAbfrage.getSelectedAIC())?RootNodeIch:RootNode);
}

public void show()
{
//  if (g.bErsatz)
//  {
//    new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("kein_Ersatzrecht");
//    return;
//  }
	if(Gid==null)
	{
		RootNode=new JCOutlinerFolderNode("Root");
                if (VecStamm==null)
                  new JCOutlinerFolderNode(g.getStamm(iAIC_Selected),RootNode).setUserData(new Integer(iAIC_Selected));
                else
                  for (int i=0;i<VecStamm.size();i++)
                    new JCOutlinerFolderNode(g.getStamm(Sort.geti(VecStamm,i)),RootNode).setUserData(new Integer(Sort.geti(VecStamm,i)));
	}
	else
        {
          if (bHauptschirm)
          {
            RootNode = new JCOutlinerFolderNode("");
            Integer iStt=(Integer)((Vector)Gid.getSelectedNode().getUserData()).elementAt(1);
            int iPos=g.TabStammtypen.getPos("Aic",iStt);
            Object[] Node=iPos<0 || (g.TabStammtypen.getI(iPos,"bits")&Global.cstEnde)>0?(Gid.getSelectedNode().getParent()).getChildren().getArrayCopy():Gid.getSelectedNodes();
            for (int i=0;i<Node.length;i++)
            {
              /*Object Obj1=((JCOutlinerFolderNode)Vec.elementAt(i)).getLabel();
              Object Obj2=((JCOutlinerFolderNode)Vec.elementAt(i)).getUserData();
              JCOutlinerNode Nod2=new JCOutlinerNode(Obj1,RootNode);
              Nod2.setUserData(Obj2);*/
              JCOutlinerNode Nod2=(JCOutlinerFolderNode)Node[i];
              if (((Vector)Nod2.getUserData()).elementAt(1).equals(iStt))
                RootNode.addNode((JCOutlinerFolderNode)Nod2.clone());
            }
            //(JCOutlinerFolderNode)(Gid.getSelectedNode().getParent());
          }
          else
            RootNode = (JCOutlinerFolderNode)(Gid.getRootNode()).clone();
        }
	RootNodeIch=new JCOutlinerFolderNode("Root_ich");
	iAnzahl=RootNode.getChildren()==null? 0:RootNode.getChildren().size();
	getRootNodeIch(RootNode,false);

	checkNurIch();
	//Out.setRootNode(RootNodeIch);
	if (Gid != null)
	{
		Out.setColumnButtons(Gid.getColumnLabels());
		Out.setNumColumns(Gid.getColumnLabels().length);
		Out.setColumnAlignments(Gid.getColumnAlignments());
	}
        else
        {
          Out.setColumnButtons(new String[] {g.getBegriffS("Show","Bezeichnung")});
          Out.setNumColumns(1);
          Out.setColumnAlignment(0,0);
        }

	//g.progInfo("Anzahl="+iAnzahl);
        showZR();
	super.show();

	if(!bHauptschirm)
		fillComboBoxen(true,true,true);

	//getParameter(iStt);
        //CbxAlle.setSelected(false);
        bAlle=false;
        if (iAIC_Selected != 0)
          SelectByAIC(Gid,iAIC_Selected);
        //checkParameter(); // am 28.2.2013 nach unten versschoben, damit richtig geladen wird
	if (iDruck>0 && !bHauptschirm)
          RadDruck.setSelected(true);
        //else if (bHauptschirm)
        //  RadDruck.setSelected(true);
        //EnableButtons(); 29.11.2013
        checkGruppiert();
        checkParameter(0);
        setOutBackground();
}

private void setOutBackground()
{
	Out.setBackground(Static.bND ? (bAlle ? Color.WHITE: g.ColHS):(bAlle ? g.ColAlle:Color.WHITE));
}

private void checkAdd(JPanel Pnl,Component Comp)
{
  if (Pnl != null)
    Pnl.add(Comp);
}

private JButton BtnAdd(String s)
{
  JButton Btn=getButton(s);
  if (Btn != null)
  {
    if (Btn.isEnabled())
    {
      Btn.setActionCommand(s);
      Btn.addActionListener(AL);
    }
    else
      Btn.setVisible(false);
  }
  return Btn;
}

private JToggleButton BtnAddT(String s)
{
  JToggleButton Btn=getTButton(s);
  if (Btn != null)
  {
    Btn.setActionCommand(s);
    Btn.addActionListener(AL);
  }
  return Btn;
}

/*private JButton BtnAdd(String s,String s2)
{
  JButton Btn=getButton(s);
  if (Btn != null)
  {
    Btn.setActionCommand(s2);
    Btn.addActionListener(AL);
  }
  return Btn;
}*/

private void Build()
{
  loadParameter();
	CbxSeitenvorschau = getCheckbox("Seitenvorschau");
	if (CbxSeitenvorschau == null) CbxSeitenvorschau = new AUCheckBox();
        /*CbxPDF = getCheckbox("PDF");
        if (CbxPDF == null) CbxPDF = new AUCheckBox();
        CbxEMail = getCheckbox("E-Mail");
        if (CbxEMail == null) CbxEMail = new AUCheckBox();*/
        EdtPDF = new FileEingabe(g, true);
        checkAdd(getFrei("PDF"),EdtPDF);
        checkAdd(getFrei("PW"),EdtPW);
        EdtLogName=new Text("",40);
        EdtLogName.setFont(g.fontStandard);
        checkAdd(getFrei("Name"),EdtLogName);
//        checkAdd(getFrei("PW2"),EdtLogPW);
	//CbxAlle = getCheckbox("Alle");
	//if (CbxAlle == null) CbxAlle = new JCheckBox();
	//CbxDiaDrehen = getCheckbox("drehen");
	//if (CbxDiaDrehen == null) CbxDiaDrehen = new JCheckBox();
	CbxSeitenumbruch = getCheckbox("Seitenumbruch");
	//if (CbxSeitenumbruch == null) CbxSeitenumbruch = new JCheckBox();
	CbxGruppiert = getCheckbox("Gruppiert");
	if (CbxGruppiert == null) CbxGruppiert = new AUCheckBox();
	CbxNurSumme = getCheckbox("NurSumme");
	if (CbxNurSumme == null) CbxNurSumme = new AUCheckBox();
	//CbxKeinDiagramm = getCheckbox("KeinDiagramm");
	//if (CbxKeinDiagramm == null) CbxKeinDiagramm = new JCheckBox();
	//CbxKeinKopfFuss = getCheckbox("KeinKopfFuss");
	//if (CbxKeinKopfFuss == null) CbxKeinKopfFuss = new JCheckBox();
	//CbxLinien = getCheckbox("Linien");
	//if (CbxLinien == null) CbxLinien = new JCheckBox();
	CbxStammLinks = getCheckbox("StammLinks");
	//if (CbxStammLinks == null) CbxStammLinks = new JCheckBox();
	//CbxSeitencheck = getCheckbox("Seitencheck");
	//if (CbxSeitencheck == null) CbxSeitencheck = new JCheckBox();
        //CbxQuerformat = getCheckbox("Querformat");
	//if (CbxQuerformat == null) CbxQuerformat = new JCheckBox();
        //CbxA3 = getCheckbox("A3");
	//if (CbxA3 == null) CbxA3 = new JCheckBox();
        //CbxGesamtrahmen = getCheckbox("Rahmen");
	//if (CbxGesamtrahmen == null) CbxGesamtrahmen = new JCheckBox();
        CbxKeineSumme = getCheckbox("keine Summe");
	if (CbxKeineSumme == null) CbxKeineSumme = new AUCheckBox();
	CbxLetzteGruppeWeg = getCheckbox("LetzteGruppeWeg");
	if (CbxLetzteGruppeWeg == null) CbxLetzteGruppeWeg = new AUCheckBox();
	//CbxDoppelteZeilenhoehe = getCheckbox("DoppelteZeilenhoehe");
	//if (CbxDoppelteZeilenhoehe == null) CbxDoppelteZeilenhoehe = new JCheckBox();
	//CbxKeine0Werte = getCheckbox("Keine0Werte");
	//if (CbxKeine0Werte == null) CbxKeine0Werte = new JCheckBox();
        //CbxAlleDrucke=getCheckbox("alle Drucke");
        //if (CbxAlleDrucke == null) CbxAlleDrucke = new JCheckBox();
        //CbxAlleDrucke.setVisible(g.UserManager());
        CbxPeriode = getCheckbox("Periode");
	if (CbxPeriode == null) CbxPeriode = new AUCheckBox();
        CbxDruckerAuswahl = getCheckbox("Druckerauswahl");
        if (CbxDruckerAuswahl == null) CbxDruckerAuswahl = new AUCheckBox(true);
        //CbxDruckerAuswahl2 = getCheckbox("Druckerauswahl2");
        //if (CbxDruckerAuswahl2 == null) CbxDruckerAuswahl2 = new JCheckBox("",true);
        CbxFarbe = getCheckbox("Farbe");
//        CbxED = getCheckbox("ED");
//        if (CbxED == null) CbxED = new AUCheckBox("");
        CbxAlleSpalten = getCheckbox("alle Spalten");
        //if (CbxAlleSpalten == null) CbxAlleSpalten = new JCheckBox("",true);
        CbxSeiteProGruppe = getCheckbox("SeiteProGruppe");
	//if (CbxSeiteProGruppe == null) CbxSeiteProGruppe = new JCheckBox();
        CbxTMZ = getCheckbox("TMZ");
        CbxPAZ = getCheckbox("AZ");
        CbxPPS = getCheckbox("PPS");
        CbxIST = getCheckbox("ist");
        CbxSOLL = getCheckbox("soll");
        CbxDIFF = getCheckbox("Diff");
        CbxPAUSE = getCheckbox("Pause");
        if (CbxPAUSE!=null) CbxPAUSE.setSelected(true);
        CbxMEMO2 = getCheckbox("Memo2");
        CbxAIO = getCheckbox("AIO");
        if (CbxAIO!=null)
          CbxAIO.addActionListener(new ActionListener()
          {
                  public void actionPerformed(ActionEvent e)
                  {
                    if (CbxAIO.isSelected())
                      CbxSeiteProGruppe.setSelected(true);
                  }
          });

        CbxVB = getCheckbox("von_bis_Text");
        if (CbxVB!=null) CbxVB.setSelected(true);
        Rad_h_auto = getRadiobutton("h_auto");
        if(Rad_h_auto==null) Rad_h_auto=new JRadioButton();
        Rad_h_auto.setSelected(true);
        Rad_h_dez = getRadiobutton("h_dez");
        if(Rad_h_dez==null) Rad_h_dez=new JRadioButton();
        Rad_h_min = getRadiobutton("h_min");
        if(Rad_h_min==null) Rad_h_min=new JRadioButton();

	//Out = new JCOutliner(RootNode!=null?(JCOutlinerFolderNode)RootNode.clone():new JCOutlinerFolderNode("Root"));
	Out=new AUOutliner();
	Out.setRootVisible(false);
	Out.setAllowMultipleSelections(true);
	Out.setColumnLabelSortMethod(Sort.sortMethod);

	CboDruck = new DruckEingabe(g,thisJFrame());
        //CboDruck.setFont(g.fontStandard);
        CboAbfrage = new ComboSort(g);
        //CboAbfrage.setFont(g.fontStandard);
        //checkAdd(getFrei("Abfrage"),CboAbfrage);
        EdtText=new Text("",30);
        EdtText.setFont(g.fontStandard);
        
        //checkAdd(getFrei("Textfeld"),EdtText);

	CboAbschnitt = new ComboSort(g,ComboSort.kein);
        //CboSort = new ComboSort(g,ComboSort.kein);
        //CboAbschnitt.setFont(g.fontStandard);
	CboVorlagen = new DefVorlagen(g,false);
        checkAdd(getFrei("Vorlage"),CboVorlagen);
        CboVorlagen.setFont(g.fontStandard);
        JPanel PnlLayout=getFrei("Layout");
        if (PnlLayout != null)
          CboLayout=Seitenlayout.getCbo(g,PnlLayout);
        /*{

          CboLayout=new ComboSort(g);
          CboLayout.setFont(g.fontStandard);
          Seitenlayout.fillCbo(CboLayout,g);
          JButton BtnLayout = g.getButton("...");
          BtnLayout.setFont(g.fontStandard);
          BtnLayout.setMargin(g.inset);
          BtnLayout.addActionListener(new ActionListener()
          {
                  public void actionPerformed(ActionEvent e)
                  {
                    Seitenlayout.get(g,CboLayout).show();
                  }
          });
          PnlLayout.setLayout(new BorderLayout(2,2));
          PnlLayout.add("Center",CboLayout);
          PnlLayout.add("East",BtnLayout);
        }*/

	//RadDruck = getRadiobutton("Druck");
        //if (RadDruck == null)
        //{
          ButtonGroup RadGroup=new ButtonGroup();
          RadDruck = new JRadioButton();
          RadAbfrage=new JRadioButton();
          RadHS=new JRadioButton();
          RadGroup.add(RadDruck);
          RadGroup.add(RadAbfrage);
          RadGroup.add(RadHS);
        /*}
        else
        {
          RadAbfrage = getRadiobutton("Abfrage");
          RadHS = getRadiobutton("HS");
        }*/
        //RadDruck.setSelected(true);
        Rad_DF_Std = getRadiobutton("DF_Std");
        Rad_DF_Last = getRadiobutton("DF_Last");
        Rad_DF_Pers = getRadiobutton("DF_Pers");
        Rad_DF_Jeder = getRadiobutton("DF_Jeder");

	TxtMemo.Edt.setEditable(false);
        TxtMemo.setFont(g.fontStandard);
        TxtMemo.setPreferredSize(new java.awt.Dimension(100,48));

	/*BtnOk = getButton("Ok");
	if(BtnOk==null) BtnOk = new JButton();
	BtnAbbruch = getButton("Abbruch");
	if(BtnAbbruch==null) BtnAbbruch = new JButton();*/
	/*BtnDruckerAuswahl = getButton("Drucker Auswahl");
	if(BtnDruckerAuswahl==null) BtnDruckerAuswahl = new JButton();
	BtnPapierAuswahl = getButton("Papier Auswahl");
	if(BtnPapierAuswahl==null) BtnPapierAuswahl = new JButton();*/
	//BtnDruckeinstellungen = getButton("DruckConfig");
	//if(BtnDruckeinstellungen==null) BtnDruckeinstellungen = new JButton();
        //BtnSeitenLayout = getFormularButton("Seitenlayout");
	//if(BtnSeitenLayout==null) BtnSeitenLayout = new JButton();

        if (AL==null)
            AL=new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                String s = ev.getActionCommand();
                g.progInfo("Action=" + s);
                if (s.equals("ZRplus") || s.equals("ZRminus") || s.equals("Jetzt"))
                  changeZR(s);
                else if (s.equals("Zeitraum"))
                  Zeitraum.get(g).show();
                else if (s.equals("Tb_Alle"))
                {
                  //CbxAlle.setSelected(!CbxAlle.isSelected());
                  bAlle=!bAlle;
                  if (!Static.bND)
                  {
                	if (BtnAlle != null)
                      BtnAlle.setContentAreaFilled(bAlle);
                  }
                  else
                  {
//	                  if (BtnAlle != null)
	                	  BtnAlle.setSelected(bAlle);
	                  if (!bAlle)
	                  {
	                	  BtnAlle.setModel(new DefaultButtonModel());
	                	  BtnAlle.setActionCommand("Tb_Alle");
	                  }
	                //BtnAlle.setArmed(true);
	                  g.fixtestError("alle markieren:"+bAlle+"/"+BtnAlle.isSelected());	                      
                  }
                  setOutBackground();
                  AlleMarkieren();
                }
                else if (s.equals("DF_Druck") || s.equals("DF_Abfrage") || s.equals("DF_HS"))
                {
                  RadDruck.setSelected(s.equals("DF_Druck"));
                  RadAbfrage.setSelected(s.equals("DF_Abfrage"));
                  RadHS.setSelected(s.equals("DF_HS"));
                  RadEvent();
                }
                else if (s.equals("DF_Std"))
                {
                  setDF(DF_Std);
                  checkGruppiert();
                }
                else if (s.equals("DF_Last"))
                {
                  setDF(DF_Last);
                  getParameter(iAIC_Stammtyp);
                }
                else if (s.equals("DF_Save"))
                {
                  setDF(DF_Pers);
                  checkParameter(1);
                }
                else if (s.equals("DF_Jeder"))
                {
                  setDF(DF_Jeder);
                  checkParameter(2);
                }
                else if (s.equals("Refresh"))
                  fillComboBoxen(true,true,true);
                else if (s.equals("Test"))
                  showPrint();
                else if (s.equals("show"))
                  showParameter(CbxJeder!=null && CbxJeder.isSelected());
                else if (s.equals("Speichern"))
                  saveParameter();
                else if (s.equals("Loeschen"))
                  delParameter();
                else if (s.equals("Beenden"))
                {
                  //setParameter(iAIC_Stammtyp, getBits());
                  hide();
                  SaveParameter();
                }
                else if (s.equals("Druck") || s.startsWith("PDF") || s.equals("E-Mail2"))
                {
                  g.clockStart();
                  Drucken(s);
                }
                else if (s.equals("Export"))
                {
                  int iBitsDruck=new Double(CboDruck.Cbo.getSelectedFaktor()).intValue();
                  boolean bDruck = RadDruck.isSelected() && (iBitsDruck&Drucken.cstPntExport)>0;
                  int iBegriff = bDruck ? CboDruck.Cbo.getSelectedAIC() : RadAbfrage.isSelected() ? CboAbfrage.getSelectedAIC() : CboAbschnitt.getSelectedAIC();
                  new Export(g, (JFrame)thisFrame, iAIC_Stammtyp, new jclass.util.JCVector(getTabStamm(iBegriff).getVecSpalte("Aic")), bDruck ? -iBegriff : iBegriff);
                }
                else if (s.equals("Modell"))
                {
                	int iBegriff=RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():CboAbschnitt.getSelectedAIC();
                    int iAic=SQL.getInteger(g,"select aic_modell from abfrage where aic_begriff="+iBegriff);
                    if (iAic>0)
                      DefModell.get(g, g.ModellToBegriff(iAic)).show();
                }
                else if (s.equals("Abfrage") || s.equals("Abfrage2"))
                {
                      int iBegriff=RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():CboAbschnitt.getSelectedAIC();
                      g.progInfo("Abfrage-Begriff="+iBegriff);
                      int iStt=SQL.getInteger(g,"select "+g.isnull()+"-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff="+iBegriff);
                      DefAbfrage.get(g,new ShowAbfrage(g,iBegriff,Abfrage.cstBegriff),iStt,null,false,s.equals("Abfrage2") ? 1:0).show(false);
                }
                else if (s.equals("AbfrageUser"))
                {
                  int iBegriff=RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():CboAbschnitt.getSelectedAIC();
                  ShowAbfrage Abf = new ShowAbfrage(g,iBegriff,Abfrage.cstBegriff);
                  DefAbfrage2.get(g,DefAbfrage2.DRUCK,Abf,(JFrame)thisFrame);
                }
                else
                  Static.printError("DruckFrage: Knopf "+s+" wird nicht unterstützt");
              }
            };

        BtnDF_Druck=BtnAddT("DF_Druck");
        BtnDF_Abfrage=BtnAddT("DF_Abfrage");
        BtnDF_HS=BtnAddT("DF_HS");
        g.RadAdd(Rad_DF_Std,"DF_Std",AL);
        g.RadAdd(Rad_DF_Last,"DF_Last",AL);
        g.RadAdd(Rad_DF_Pers,"DF_Save",AL);
        g.RadAdd(Rad_DF_Jeder,"DF_Jeder",AL);
        if (BtnDF_Druck == null)
          checkAdd(getFrei("Druck"),CboDruck);
        else
        {
          JPanel Pnl=getFrei("Druck");
          //Pnl.setPreferredSize(new Dimension(100,40));
          Pnl.setLayout(new FlowLayout(FlowLayout.LEFT,2,2));
          CboDruck.setPreferredSize(new java.awt.Dimension(300*iFF/100,18*iFF/100));
          CboAbfrage.setPreferredSize(new java.awt.Dimension(300*iFF/100,18*iFF/100));
          EdtText.setPreferredSize(new java.awt.Dimension(300*iFF/100,18*iFF/100));
          //CboSort.setPreferredSize(new java.awt.Dimension(100,18));
          CboAbfrage.setVisible(false);
          EdtText.setVisible(false);
          LblTitel=g.getLabel("Auswaertung");
          //LblSort=g.getLabel("alternative Sortierung");
          //LblTitel.setVisible(false);
          Pnl.add(LblTitel);
          Pnl.add(CboDruck);
          Pnl.add(CboAbfrage);
          Pnl.add(EdtText);
          CbxAlleDrucke=g.getCheckbox("alle Drucke");
          //CbxAlleDrucke.setPreferredSize(new Dimension(80,16));
          //Pnl.add(LblSort);
          //Pnl.add(CboSort);
          Pnl.add(CbxAlleDrucke);
          BtnDF_Druck.setSelected(true);
        }
        /*BtnDF_Std=BtnAddT("DF_Std");
        BtnDF_Last=BtnAddT("DF_Last");
        BtnDF_Save=BtnAddT("DF_Save");
        if (BtnDF_Std!=null && !g.TestPC())
        {
          BtnDF_Std.setVisible(false);
          BtnDF_Last.setVisible(false);
          BtnDF_Save.setVisible(false);
        }*/
    JButton BtnModell = getButton("Modell",null,AL);
    if (BtnModell != null && !g.Def())
    	BtnModell.setVisible(false);
	JButton BtnAbfrage = g.BtnAdd(getFormularButton("Abfrage"),"Abfrage",AL);
	if(BtnAbfrage==null) BtnAbfrage = new JButton();
    BtnAbfrage.setEnabled(g.Abfrage()); 
    JButton BtnAbfrage2 = getButton("Abfrage2",null,AL);
	if(BtnAbfrage2==null) BtnAbfrage2 = new JButton();
    BtnAbfrage2.setVisible(g.Def());
    if (!g.Def())
    {
    	EdtLogName.setEnabled(false);
//    	EdtLogPW.setEnabled(false);
    	if (EdtLogName.getParent() != null)
    		EdtLogName.getParent().getParent().getParent().getParent().setVisible(false);
    }
    //g.fixtestError("BtnAbfrage2="+BtnAbfrage2);
    JButton BtnAbfrageUser=g.BtnAdd(getButton("Abfrage easy"),"AbfrageUser",AL);
    if(BtnAbfrageUser==null) BtnAbfrageUser = new JButton();
        //BtnAbfrage2.setEnabled(g.Abfrage());
	BtnAdd("Refresh");
	BtnTest = BtnAdd("Test");
        BtnShow = BtnAdd("show");
        if (BtnShow !=null)
          BtnShow.setVisible(g.Def());
        BtnSave = BtnAdd("Speichern");
        BtnDel = BtnAdd("Loeschen");
        CbxJeder= getCheckbox("Jeder");
        if (CbxJeder != null)
          CbxJeder.setVisible(g.Spezial());//g.UserManager());
	BtnDruckdefinition = getFormularButton("Druckdefinition");
	BtnSyncStamm = getFormularButton("SyncStamm");
	if(BtnSyncStamm==null) BtnSyncStamm = new JButton();
	BtnDrucken = BtnAdd("Druck");
        g.BtnDruck=BtnDrucken;
	//if(BtnDrucken==null) BtnDrucken = new JButton();
        ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnDrucken);
	//BtnDiagramm = getButton("Diagramm");
	//if(BtnDiagramm==null) BtnDiagramm = new JButton();
	BtnExport = BtnAdd("Export");
        BtnEMail = BtnAdd("E-Mail2");
        BtnPDF = BtnAdd("PDF");
        BtnPDF_View = BtnAdd("PDF_View");
        BtnPDF_DB = BtnAdd("PDF_DB");
        if (BtnPDF_DB==null)
        	BtnPDF_DB=new JButton();
	//if(BtnExport==null) BtnExport = new JButton();
	BtnBeenden = BtnAdd("Beenden");
	//if(BtnBeenden==null) BtnBeenden = new JButton();
	//BtnOLEtoWord = getFormularButton("OLEtoWord");
	//if(BtnOLEtoWord==null) BtnOLEtoWord = new JButton();

	JPanel Pnl_Outliner = getFrei("Outliner");
        Pnl_Outliner.add(Out);

	//JPanel Pnl_Druck_Abfrage = getFrei("Druck Abfrage");
	JPanel Pnl_Combo_Bewegung = getFrei("Combo Bewegung");
	//JPanel Pnl_Combo_Diagramm = getFrei("Combo Diagramm");
        //JPanel Pnl_Combo_Sort = getFrei("Combo Sort");
	JPanel Pnl_Memo = getFrei("Memo");
        if(Pnl_Memo!=null)
          Pnl_Memo.add(TxtMemo);
	//CbxLetzteGruppeWeg.setEnabled(false);

	/*if(Pnl_Druck_Abfrage!=null)
	{
		ButtonGroup group = new ButtonGroup();
		group.add(RadDruck);
		group.add(RadAbfrage);
		group.add(RadHS);

		Pnl_Druck_Abfrage.setLayout(new BorderLayout(2,2));
		JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
		Pnl.add(RadDruck);
		Pnl.add(RadAbfrage);
		Pnl.add(RadHS);
		Pnl_Druck_Abfrage.add("West",Pnl);

		Pnl = new JPanel(new GridLayout(0,1,2,2));
		Pnl.add(CboDruck);
		Pnl.add(CboAbfrage);
		Pnl.add(CboVorlagen);
		Pnl_Druck_Abfrage.add("Center",Pnl);
	}*/
        //if(Pnl_Combo_Sort!=null)
        //  Pnl_Combo_Sort.add(CboSort);
	if(Pnl_Combo_Bewegung!=null)
          Pnl_Combo_Bewegung.add(CboAbschnitt);
	/*if(Pnl_Combo_Diagramm != null)
	{
		CboDiagramm = new ComboSort(g);
                CboDiagramm.setFont(g.fontStandard);
		CboDiagramm.fillBegriffTable("Grafik",false);
		Pnl_Combo_Diagramm.add(CboDiagramm);
	}*/

	/*CbxGruppiert.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			CbxSeitenumbruch.setEnabled(!CbxGruppiert.isSelected());
			CbxLetzteGruppeWeg.setEnabled(CbxGruppiert.isSelected());
			//CbxNurSumme.setEnabled(CbxGruppiert.isSelected());
		}
	});

	CbxSeitenumbruch.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			CbxGruppiert.setEnabled(!CbxSeitenumbruch.isSelected());
		}
	});*/

        /*CbxAlle.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
              AlleMarkieren();
            }
        });*/
        CbxAlleDrucke.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
            {
              //g.progInfo("CbxAlleDrucke:"+CbxAlleDrucke.isSelected());
              fillComboBoxen(true,true,true);
            }
        });
        if (CbxJeder != null)
          CbxJeder.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              EnableButtons();
            }
          });
	/*BtnOk.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			hide();
			setParameter();
			DruckenOld();
		}
	});

	BtnAbbruch.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			hide();
		}
	});*/

	BtnBeenden.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
                  //setParameter(iAIC_Stammtyp,getBits());
			hide();
			SaveParameter();
		}
	});
        Action cancelKeyAction = new AbstractAction()
        {
            /**
			 *
			 */
			private static final long serialVersionUID = -696430393701521103L;

			public void actionPerformed(ActionEvent e) {
              hide();
              SaveParameter();
            }
        };
        Static.Escape(BtnBeenden,thisFrame,cancelKeyAction);

	/*BtnDruckenNew.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			DruckenNew();
		}
	});*/

        /*BtnDrucken.addMouseListener(new MouseListener()
        {
            public void mousePressed(MouseEvent ev)
            {}
            public void mouseClicked(MouseEvent ev)
            {}
            public void mouseEntered(MouseEvent ev)
            {
              EnableButtons();
            }
            public void mouseExited(MouseEvent ev)
            {}
            public void mouseReleased(MouseEvent ev)
            {}
        });*/

        //if (BtnDiagramm != null)
	/*BtnDiagramm.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JPanel Pnl = new JPanel();
                        String sTitel=RadDruck.isSelected()?CboDruck.getSelectedBezeichnung():RadAbfrage.isSelected() ? CboAbfrage.getSelectedBezeichnung():EdtText.getText();
                        int iBegriff=RadDruck.isSelected()?CboDruck.getSelectedAIC():RadAbfrage.isSelected() ? CboAbfrage.getSelectedAIC():iAbfrage;
			if (new Diagramm(g,Pnl,CboDiagramm.getSelectedKennung(),iBegriff,iAIC_Stammtyp,new jclass.util.JCVector(getTabStamm(iBegriff).getVecSpalte("Aic")),
			    getBits(),sTitel,RadDruck.isSelected(),true,RadHS.isSelected()?Out:null).isOK())
			{
				JFrame FomDia = new JFrame(sTitel);
				FomDia.getContentPane().add(Pnl);
				FomDia.setSize(800,600);
				FomDia.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				FomDia.setVisible(true);
			}
			//else
				//new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("Diagrammfehler");
		}
	});*/

	/*BtnDruckeinstellungen.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			DruckConfig.get(g).show();
		}
	});*/

	/*BtnAbfrage.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			int iBegriff=RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():CboAbschnitt.getSelectedAIC();
                        g.progInfo("Abfrage-Begriff="+iBegriff);
			//int iStt=iAIC_Stammtyp<0?iAIC_Stammtyp:-SQL.getInteger(g,"select aic_bewegungstyp from begriff where aic_begriff="+iBegriff);
			//iStt=iStt==0?iAIC_Stammtyp:iStt;
                        int iStt=SQL.getInteger(g,"select "+g.isnull()+"-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff="+iBegriff);
			DefAbfrage.get(g,new ShowAbfrage(g,iBegriff,Abfrage.cstBegriff),iStt).show(false);
			//Abfrage.Zeigen(g,RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():CboAbschnitt.getSelectedAIC(),(JFrame)thisFrame);

			//fillComboBoxen(false,true,false);
		}
	});*/
//        if (g.Def())
//        {
//          BtnAbfrage.removeAll();
//          BtnAbfrage.addMouseListener(new MouseListener()
//          {
//            public void mousePressed(MouseEvent ev)
//            {}
//
//            public void mouseClicked(MouseEvent ev)
//            {
//              if ((ev.getButton()==3 || ev.getButton()==1) && ev.getModifiersEx()==Global.iRM)
//              {
//            	  g.fixInfo("Knopf BtnAbfrage gedrückt:"+ev.getButton());
//                int iBegriff=RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():CboAbschnitt.getSelectedAIC();
//                int iAic=SQL.getInteger(g,"select aic_modell from abfrage where aic_begriff="+iBegriff);
//                if (iAic>0)
//                  DefModell.get(g, g.ModellToBegriff(iAic)).show();
//              }
//            }
//
//            public void mouseEntered(MouseEvent ev)
//            {}
//
//            public void mouseExited(MouseEvent ev)
//            {}
//
//            public void mouseReleased(MouseEvent ev)
//            {}
//          });
//        }

        if (BtnSyncStamm != null)
          BtnSyncStamm.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              SyncStamm.start(g,null).show();
            }
          });
        if (BtnDruckdefinition != null)
        {
          BtnDruckdefinition.setVisible(g.SuperUser());
          if (g.SuperUser())
            BtnDruckdefinition.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                Druckdefinition.get(g, true, CboDruck.Cbo.getSelectedAIC(), iAIC_Stammtyp);
              }
            });
        }
        g.BtnAdd(getButton("Tb_Zeitraum"),"Zeitraum",AL);
        g.BtnAdd(getButton("Tb_ZRplus"),"ZRplus",AL);
        g.BtnAdd(getButton("Jetzt"),"Jetzt",AL);
        g.BtnAdd(getButton("Tb_ZRminus"),"ZRminus",AL);
        BtnAlle=BtnAddT("Tb_Alle");
        if (BtnAlle == null)
          BtnAdd("Tb_Alle");
	RadDruck.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			RadEvent();
		}
	});

	RadAbfrage.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			RadEvent();
		}
	});

	RadHS.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
                  RadEvent();
		}
	});

	Out.addItemListener(new JCOutlinerListener()
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
                  if (bFertig)
                    DatensatzMarkieren();
		}
	});

	CboDruck.Cbo.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(e.getStateChange()==ItemEvent.SELECTED)
			{
                setDF(DF_Std);
                int iDBits=Sort.geti(CboDruck.Cbo.getSelectedFaktor());
                CbxFarbe.setSelected((iDBits & Drucken.cstPntFarbe) > 0);
                          
				fillComboBoxen(false,false,true);

				showMemo(CboDruck.Cbo.getSelectedAIC());
				checkNurIch();
				//EnableButtons(); 29.11.2013
				checkGruppiert();
                                checkParameter(0);
			}
		}
	});

	CboAbfrage.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(e.getStateChange()==ItemEvent.SELECTED)
			{
				showMemo(CboAbfrage.getSelectedAIC());
				checkNurIch();
				//EnableButtons(); 29.11.2013
				//CbxGruppiert.setEnabled(true);
                                checkParameter(0);
			}
		}
	});

        /*CboAbschnitt.addItemListener(new ItemListener()
        {
          public void itemStateChanged(ItemEvent e)
          {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
              fillSort();
            }
          }
        });*/

        ActionListener ALEB=new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                        EnableButtons();
                }
        };
        CbxGruppiert.addActionListener(ALEB);
        CbxSeiteProGruppe.addActionListener(ALEB);
        CbxSeitenumbruch.addActionListener(ALEB);
        CbxNurSumme.addActionListener(ALEB);
        CbxStammLinks.addActionListener(ALEB);
        CbxKeineSumme.addActionListener(ALEB);
        CbxLetzteGruppeWeg.addActionListener(ALEB);
        CbxPeriode.addActionListener(ALEB);
        CbxDruckerAuswahl.addActionListener(ALEB);
        CbxFarbe.addActionListener(ALEB);
//        CbxED.addActionListener(ALEB);
        CbxAlleSpalten.addActionListener(ALEB);

        thisFrame.addWindowListener(new WindowListener()
                        {
                                public void windowClosed(WindowEvent e)
                                {
                                }
                                public void windowOpened(WindowEvent e)
                                {
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
                                        showZR();
                                }
                                public void windowDeactivated(WindowEvent e)
                                {
                                }
                        });


}

private void RadEvent()
{
  if (BtnDF_Druck != null)
  {
    CboDruck.setVisible(RadDruck.isSelected());
    CboAbfrage.setVisible(RadAbfrage.isSelected());
    //LblTitel.setVisible(RadHS.isSelected());
    LblTitel.setText(g.getBegriffS("Label",RadDruck.isSelected()?"Auswaertung":RadAbfrage.isSelected() ? "Abfrage":"Titel")+":");
    EdtText.setVisible(RadHS.isSelected());
    CbxAlleDrucke.setVisible(RadDruck.isSelected());
    //LblSort.setVisible(RadDruck.isSelected());
    //CboSort.setVisible(RadDruck.isSelected());
  }
  if  (RadDruck.isSelected())
  {
          showMemo(CboDruck.Cbo.getSelectedAIC());
          checkNurIch();
          checkParameter(0);
          //EnableButtons();
          checkGruppiert();
  }
  else if  (RadAbfrage.isSelected())
  {
          showMemo(CboAbfrage.getSelectedAIC());
          checkNurIch();
          CbxKeineSumme.setSelected(false);
          CbxNurSumme.setSelected(false);
          CbxPeriode.setSelected(false);
          CbxSeiteProGruppe.setSelected(false);
          CbxGruppiert.setSelected(false);
          CbxLetzteGruppeWeg.setSelected(false);
          checkParameter(0);
          //EnableButtons();
          CbxGruppiert.setEnabled(true);
  }
  else if  (RadHS.isSelected())
  {
    showMemo(0);
    EnableButtons();
  }
}

private void DatensatzMarkieren()
                          {
                                int iAIC_StammtypNeu=0;
                                /*boolean bNurIch=Out.getRootNode()==RootNodeIch;
                                if (bNurIch)
                                  iAIC_StammtypNeu=g.iSttANR;
                                else*/
                                if (Out.getSelectedNode().getLevel()==0)
                                        return;
                                  iAIC_StammtypNeu=!bHauptschirm ? iAIC_Stammtyp:
                                      ((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(1)).intValue();
                                if (iAIC_StammtypNeu != iAIC_Stammtyp)
                                {
                                        iAIC_Stammtyp=iAIC_StammtypNeu;
                                        fillComboBoxen(true,true,true);
                                        showMemo(RadDruck.isSelected() ? CboDruck.Cbo.getSelectedAIC():RadAbfrage.isSelected() ? CboAbfrage.getSelectedAIC():0);
                                }

                                Object[] Nod=Out.getSelectedNodes();
                                Vector<Object> VecS=new Vector<Object>();
                                /*if (bNurIch)
                                  VecS.addElement(new Integer(g.getStamm()));
                                else*/
                                  for(int i = 0; i < Nod.length; i++) {
                                    if(bHauptschirm)
                                    {
                                      Vector VecUD = (Vector)((JCOutlinerNode)Nod[i]).getUserData();
                                      //if(iStt2 == 0)
                                      //  iStt2 = ((Integer)VecUD.elementAt(1)).intValue();
                                      //int iStamm2=((Integer)VecUD.elementAt(0)).intValue();
                                      if(VecUD != null && iAIC_StammtypNeu == ((Integer)VecUD.elementAt(1)).intValue())
                                        VecS.addElement(VecUD.elementAt(0));
                                    }
                                    else
                                    {
                                      int iAic=Sort.geti(((JCOutlinerNode)Nod[i]).getUserData());
                                      if (iAic>0)
                                        VecS.addElement(new Integer(iAic));
                                    }
                                    //g.progInfo("VecUD"+i+"="+VecUD);
                                  }
                                if (VecS.size()>0)
                                {
                                  g.setSyncStamm(iAIC_StammtypNeu, ((Integer)VecS.elementAt(0)).intValue());
                                  g.setSyncStamm(iAIC_StammtypNeu, VecS);
                                }
                        EnableButtons();

                      }

          private void AlleMarkieren()
          {
            {
              bFertig=false;
              if (bAlle)
              {
                java.awt.Event ev=new java.awt.Event(null,0,null);
                ev.modifiers=Event.SHIFT_MASK;
                JCOutlinerNode Nod=Out.getSelectedNode();
                int iiAIC_Stammtyp = bHauptschirm ? ( (Integer) ( (Vector) Nod.getUserData()).elementAt(1)).intValue():0;
                for (JCOutlinerNode NodeAlle = Out.getNextNode(Out.getRootNode()); NodeAlle != null; NodeAlle = Out.getNextNode(NodeAlle))
                  if (!bHauptschirm || ( (Integer) ( (Vector) NodeAlle.getUserData()).elementAt(1)).intValue() == iiAIC_Stammtyp)
                    Out.selectNode(NodeAlle,ev);
                Out.selectNode(Nod,ev);

                    //Out.getOutliner().multiSelectNode(NodeAlle, ev);
                //Out.folderChanged(Out.getRootNode());
                //CbxAlle.setSelected(false);
              }
              else
              {
                //Out.setAllowMultipleSelections(false);
                JCOutlinerNode Nod=Out.getSelectedNode();
                java.awt.Event ev=new java.awt.Event(null,0,null);
                ev.modifiers=Event.CTRL_MASK;
                Out.selectNode(Nod,ev);
                //Out.selectNode(Nod,null);
                Static.makeVisible(Out,Nod);
                //Out.selectNode(Out.getSelectedNode(), null);
                //Out.folderChanged(Out.getRootNode());
                //Out.setAllowMultipleSelections(true);
              }
              bFertig=true;
              DatensatzMarkieren();
            }
          }

/*private void fillSort()
{
  g.testInfo(" ---- fillSort "+CboAbschnitt.getSelectedAIC());
      CboSort.Clear();
      CboSort.setKein(true);
      Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_eigenschaft,nummer,bits"+g.AU_Bezeichnung2("Spalte")+
          " Spalte_Bez from fixeigenschaft"+g.join("spalte","fixeigenschaft")+g.join("abfrage","spalte")+" where abfrage.aic_begriff="+CboAbschnitt.getSelectedAIC()+" order by reihenfolge,aic_fixeigenschaft",true);
      for (Tab.moveFirst();!Tab.out();)
      {
        String sText=Tab.getS("Spalte_Bez");
        int iAIC=Tab.getI("nummer");
        int iBits=Tab.getI("Bits");
        Vector<Object> VecFixEigenschaft = new Vector<Object>();
        while (!Tab.out() && Tab.getI("nummer")==iAIC)
        {
          VecFixEigenschaft.addElement(Tab.getInhalt("aic_eigenschaft"));
          Tab.moveNext();
        }
        if (sText.equals(""))
          sText=Abfrage.getEigenschaftBezeichnung(g,VecFixEigenschaft);
        if ((iBits&Abfrage.cstAnzeigen)>0 && (iBits&Abfrage.cstUnsichtbar)==0)
          CboSort.addItem(sText, iAIC, true);
      }
}*/

private void changeZR(String sArt)//boolean bPlus)
{
  boolean bJetzt=sArt.equals("Jetzt");
  if (bJetzt)
    g.setAktDate(true);
  else
    g.changeZR(sArt);//bPlus ? "ZRplus":"ZRminus");
  Zeitraum.PeriodeToVec(g,g.getZA(0),bJetzt);
  showZR();
  EnableButtons();
}

private void showZR()
{
  setTitle(sFormularBezeichnung+": "+g.getVonBis("dd.MM.yyyy",true));
}

private void showParameter(boolean bAlle)
{
  if (bAlle)
    new Tabellenspeicher(g,"select aic_druck AIC, defbezeichnung Druck"+g.AU_Bezeichnung("Raster","druck")+" Vorlage,Layout.Name Layout"+
                         g.bei("druck.bits",Drucken.cstKeineSumme,"keine_Summe")+g.bei("druck.bits",Drucken.cstNurSumme,"nur_Summe")+
                         " from begriff"+g.join2("druck","begriff")+" left outer join layout on druck.aic_layout=layout.aic_layout where druck.aic_benutzer is null",true).showGrid("allgemeine Druck-Konfiguration");
    //TabDZa.showGrid("TabDZa");
  else
    new Tabellenspeicher(g,"select aic_druck AIC, defbezeichnung Druck"+g.AU_Bezeichnung("Raster","druck")+" Vorlage,Layout.Name Layout"+
                         g.bei("druck.bits",Drucken.cstKeineSumme,"keine_Summe")+g.bei("druck.bits",Drucken.cstNurSumme,"nur_Summe")+
                         " from begriff"+g.join2("druck","begriff")+" left outer join layout on druck.aic_layout=layout.aic_layout where druck.aic_benutzer="+g.getBenutzer(),true).showGrid("persönliche Druck-Konfiguration");

    //TabDZp.showGrid("TabDZp");
}

private void loadParameter()
{
  TabDZa=new Tabellenspeicher(g,"select * from druck where aic_benutzer is null",true);
  TabDZp=new Tabellenspeicher(g,"select * from druck where aic_benutzer="+g.getBenutzer(),true);
}

private boolean checkParameter(int iArt) // 0..Auto, 1..Persönlich, 2..Jeder
{
  boolean b=false;
  if (!bDF_Action)
  {
    Tabellenspeicher Tab = null;
    int iBegriff = RadDruck.isSelected() ? CboDruck.Cbo.getSelectedAIC() : CboAbfrage.getSelectedAIC();
    /*if (iArt==0)
       {
      boolean b=TabDZp.posInhalt("AIC_BEGRIFF",iBegriff);
      if (Rad_DF_Pers!=null) Rad_DF_Pers.setEnabled(b);
      if (BtnDF_Save!=null) BtnDF_Save.setEnabled(b);
      b=TabDZa.posInhalt("AIC_BEGRIFF",iBegriff);
      if (Rad_DF_Jeder!=null) Rad_DF_Jeder.setEnabled(b);
       }*/
    if (iArt != 2 && TabDZp.posInhalt("AIC_BEGRIFF", iBegriff))
      Tab = TabDZp;
    else if (iArt != 1 && TabDZa.posInhalt("AIC_BEGRIFF", iBegriff))
      Tab = TabDZa;
    if (Tab != null)
    {
      g.progInfo("checkParameter:" + Tab.getI("AIC_Raster") + "/" + Tab.getI("AIC_Layout"));
      CboVorlagen.setSelectedAIC(Tab.getI("AIC_Raster"));
      CboLayout.setSelectedAIC(Tab.getI("AIC_Layout"));
      int iBits = Tab.getI("Bits");
      CbxSeitenumbruch.setSelected((iBits & Drucken.cstSeitenumbruch) > 0);
      //CbxDiaDrehen.setSelected((iBits&Drucken.cstDiaDrehen)>0);
      //CbxKeinDiagramm.setSelected((iBits&Drucken.cstKeinDiagramm)>0);
      CbxNurSumme.setSelected((iBits & Drucken.cstNurSumme) > 0);
      CbxPeriode.setSelected((iBits & Drucken.cstPeriode) > 0);
      CbxDruckerAuswahl.setSelected((iBits & Drucken.cstDruckerauswahl) > 0);
      //CbxDruckerAuswahl2.setSelected((iBits&Drucken.cstDruckerauswahl2)>0);
//      CbxED.setSelected((iBits & Drucken.cstED) > 0);
      CbxFarbe.setSelected((iBits & Drucken.cstDFFarbe) > 0);
      CbxAlleSpalten.setSelected((iBits & Drucken.cstAlleSpalten) > 0);
      CbxSeitenvorschau.setSelected((iBits & Drucken.cstSeitenvorschau) > 0);
      CbxStammLinks.setSelected((iBits & Drucken.cstStammLinks) > 0);
      //CbxGesamtrahmen.setSelected((iBits&Drucken.cstGesamtrahmen)>0);
      CbxKeineSumme.setSelected((iBits & Drucken.cstKeineSumme) > 0);
      CbxGruppiert.setSelected((iBits & Drucken.cstGruppiert) > 0);
      CbxLetzteGruppeWeg.setSelected((iBits & Drucken.cstLetzteGruppeWeg) > 0);
      CbxSeiteProGruppe.setSelected((iBits & Drucken.cstSeiteProGruppe) > 0);
      if ((iBits & Drucken.cstPb)>0 && CbxTMZ!=null)
      {
        CbxTMZ.setSelected((iBits & Drucken.cstTMZ) > 0);
        if (CbxPAZ!=null) CbxPAZ.setSelected((iBits & Drucken.cstPAZ) > 0);
        if (CbxPPS!=null) CbxPPS.setSelected((iBits & Drucken.cstPPS) > 0);
        if (CbxIST!=null) CbxIST.setSelected((iBits & Drucken.cstIST) > 0);
        if (CbxSOLL!=null) CbxSOLL.setSelected((iBits & Drucken.cstSOLL) > 0);
        if (CbxDIFF!=null) CbxDIFF.setSelected((iBits & Drucken.cstDIFF) > 0);
        if (CbxPAUSE!=null) CbxPAUSE.setSelected((iBits & Drucken.cstPAUSE) > 0);
        if (CbxMEMO2!=null) CbxMEMO2.setSelected((iBits & Drucken.cstMEMO2) > 0);
        if (CbxAIO!=null) CbxAIO.setSelected((iBits & Drucken.cstAIO) > 0);
        if (CbxVB!=null) CbxVB.setSelected((iBits & Drucken.cstVB) > 0);
      }
      if ((iBits & Drucken.cstART_H) == 0)
        Rad_h_auto.setSelected(true);
      else if ((iBits & Drucken.cstART_H) == Drucken.cstH_DEZ)
        Rad_h_dez.setSelected(true);
      else if ((iBits & Drucken.cstART_H) == Drucken.cstH_MIN)
        Rad_h_min.setSelected(true);
      setDF(Tab == TabDZp ? DF_Pers : DF_Jeder); // oder DF_Jeder
      b=true;
    }
    //else if (BtnDF_Save != null && BtnDF_Save.isSelected())
    //  setDF(DF_Std);
  }
  EnableButtons();
  return b;
}

private void saveParameter()
{
  SQL Qry=new SQL(g);
  int iBegriff=RadDruck.isSelected()?CboDruck.Cbo.getSelectedAIC():CboAbfrage.getSelectedAIC();
  Qry.add("AIC_BEGRIFF",iBegriff);
  Qry.add0("AIC_Raster",CboVorlagen.getSelectedAIC());
  Qry.add0("AIC_Layout",CboLayout.getSelectedAIC());
  Qry.add("Bits",getBits("Druck"));
  if (CbxJeder==null || !CbxJeder.isSelected())
    Qry.add("AIC_Benutzer",g.getBenutzer());
  if ((CbxJeder!=null && CbxJeder.isSelected()?TabDZa:TabDZp).posInhalt("AIC_BEGRIFF",iBegriff))
    Qry.update("Druck",(CbxJeder!=null && CbxJeder.isSelected()?TabDZa:TabDZp).getI("AIC_Druck"));
  else
    Qry.insert("Druck",false);
  Qry.free();
  loadParameter();
  EnableButtons();
  setDF(CbxJeder==null || !CbxJeder.isSelected() ? DF_Pers:DF_Jeder);
}

private void delParameter()
{
  int iBegriff=RadDruck.isSelected()?CboDruck.Cbo.getSelectedAIC():CboAbfrage.getSelectedAIC();
  g.exec("delete from druck where aic_begriff="+iBegriff+" and aic_benutzer"+(CbxJeder!=null && CbxJeder.isSelected()?" is null":"="+g.getBenutzer()));
  loadParameter();
  setDF(DF_Std);
  checkGruppiert();
  EnableButtons();
  //checkParameter(0);
}

private void showMemo(int iAIC_Begriff)
{
  /*String s="";
  if (iAIC_Begriff>0)
    s=SQL.getString(g,"SELECT Memo FROM Daten_Memo WHERE AIC_TABELLENNAME="+g.iTabBegriff+" and aic_sprache="+g.getSprache()+" and aic_fremd=?",""+iAIC_Begriff);
   */
  TxtMemo.setText(iAIC_Begriff<=0 ? "":g.getMemo2(iAIC_Begriff));
}

public void fillComboBoxen(boolean bDruck,boolean bAbfrage,boolean bAbschnitt)
{
	//g.testInfo("fillComboBoxen: "+bDruck+"/"+bAbfrage+"/"+iAIC_Stammtyp);
	if (bDruck)
		CboDruck.fillDruck(iRolle,iAIC_Stammtyp,iDruck,VecDrucke,CbxAlleDrucke.isSelected(),false);
	if (bAbfrage)
		CboAbfrage.fillAbfrage(iAIC_Stammtyp);//,CbxAlleDrucke.isSelected()? null : VecAbfragen);

	if (bAbschnitt||bDruck)
        {
          CboAbschnitt.fillCbo("select kennung" + g.AU_Bezeichnung2("abschnitt") + " bezeichnung,abschnitt.aic_begriff from druck_zuordnung dz" + g.join("abschnitt", "dz") +
                               " where dz.aic_begriff=" + CboDruck.Cbo.getSelectedAIC() + " and not " + g.bit("abschnitt.bits", Drucken.cstNichtDrucken) + " order by reihenfolge", "Begriff", false, false);
          //fillSort();
        }
	//g.tempInfo("");
        if (bDruck)
          getParameter(iAIC_Stammtyp);
}

private void EnableButtons()
{
	//g.fixInfo("EnableButtons:"+CboDruck.Cbo.getSelectedAIC()+"/"+CboAbfrage.getSelectedAIC()+": Druck/Abfrage/HS="+RadDruck.isSelected()+"/"+RadAbfrage.isSelected()+"/"+RadHS.isSelected());
        int iLevel=Out.getSelectedNode()==null ? 0:Out.getSelectedNode().getLevel();
        //g.progInfo("EnableButtons: Level="+iLevel/*+"/"+Out.getSelectedNode().getParent()*/);
        if (!RadHS.isSelected() && !RadDruck.isSelected() && !RadAbfrage.isSelected())
          RadDruck.setSelected(true);
        boolean bDruck=RadDruck.isSelected();
        CboDruck.setEnabled(bDruck);
        CboAbschnitt.setEnabled(bDruck);
        CboAbfrage.setEnabled(RadAbfrage.isSelected());
        CbxGruppiert.setEnabled(!RadHS.isSelected() && !CbxSeiteProGruppe.isSelected());
        if (!CbxGruppiert.isEnabled())
          CbxGruppiert.setSelected(false);
        CbxLetzteGruppeWeg.setEnabled(CbxGruppiert.isSelected());
        if (!CbxLetzteGruppeWeg.isEnabled())
          CbxLetzteGruppeWeg.setSelected(false);
        CbxSeiteProGruppe.setEnabled(bHauptschirm && !RadHS.isSelected() && !CbxGruppiert.isSelected() && iLevel>1);
        if (!CbxSeiteProGruppe.isEnabled())
        {
          CbxSeiteProGruppe.setSelected(false);
          if (CbxAIO!=null)
          {
            CbxAIO.setSelected(false);
            //CbxAIO.setEnabled(false);
          }
        }
        //else if (CbxAIO!=null)
        //  CbxAIO.setEnabled(true);
        EdtText.setEnabled(RadHS.isSelected());
	//CboVorlagen.setEnabled(RadHS.isSelected()||RadAbfrage.isSelected());
	boolean bDrucken=iAnzahl>0 && (bDruck?CboDruck.Cbo.getSelectedAIC()!=0:RadAbfrage.isSelected() ? CboAbfrage.getSelectedAIC()!=0:RadHS.isSelected());
	//BtnOk.setEnabled(bDrucken);
	long lBits=0;//RadAbfrage.isSelected() ? new Double(CboAbfrage.getSelectedFaktor()).longValue():0;
        if (RadAbfrage.isSelected())
        {
          int iPos=g.TabBegriffe.getPos("Aic",CboAbfrage.getSelectedAIC());
          lBits=iPos<0 ? 0:g.TabBegriffe.getL(iPos,"bits");
        }
        //g.progInfo("lBits="+Long.toHexString(lBits));
	BtnDrucken.setEnabled(/*!Drucken.bAktiv &&*/ bDrucken && (!RadAbfrage.isSelected() || (lBits&Abfrage.cstDruckbar)>0));
        if (BtnTest !=null)
          BtnTest.setEnabled((g.Def() || g.bInfoDruck) && bDrucken && (RadAbfrage.isSelected() || bDruck));
        //if (BtnDiagramm !=null)
        //  BtnDiagramm.setEnabled(CboDiagramm != null && bDrucken);
        if (BtnExport !=null)
          BtnExport.setEnabled(bDrucken && (RadDruck.isSelected() || RadAbfrage.isSelected() && (lBits&Abfrage.cstExportierbar)>0));
	//BtnOLEtoWord.setEnabled(bDrucken && (iBits&Abfrage.cstOLE)>0);

        int iBegriff=RadDruck.isSelected()?CboDruck.Cbo.getSelectedAIC():CboAbfrage.getSelectedAIC();
        boolean bJeder=CbxJeder!=null && CbxJeder.isSelected();
        boolean bSok=bJeder && iStatusDF != DF_Pers || !bJeder && iStatusDF != DF_Jeder;
        BtnSave.setEnabled(bDrucken && bDruck && bSok);//!RadHS.isSelected());
        boolean b=TabDZp.posInhalt("AIC_BEGRIFF",iBegriff) && !bJeder;
        if (Rad_DF_Pers != null)Rad_DF_Pers.setEnabled(b && bDruck);
        //if (BtnDF_Save != null)BtnDF_Save.setEnabled(b);
        if (BtnDel != null && !bJeder) BtnDel.setEnabled(b && bDruck && bSok);
        b = TabDZa.posInhalt("AIC_BEGRIFF", iBegriff);
        if (BtnDel != null && bJeder) BtnDel.setEnabled(b && bDruck && bSok);
        if (Rad_DF_Jeder != null) Rad_DF_Jeder.setEnabled(b && bDruck);
        if (Rad_DF_Std != null) Rad_DF_Std.setEnabled(bDruck);
        
        int iDBits=Sort.geti(CboDruck.Cbo.getSelectedFaktor());
        if (CbxTMZ!=null)
          {           
            boolean bSchicht=(iDBits & Drucken.cstPntSchicht)>0;
            CbxTMZ.setEnabled(bSchicht);
            if (CbxPAZ!=null) CbxPAZ.setEnabled(bSchicht);
            if (CbxPPS!=null) CbxPPS.setEnabled(bSchicht);
            if (CbxIST!=null) CbxIST.setEnabled(bSchicht);
            if (CbxSOLL!=null) CbxSOLL.setEnabled(bSchicht);
            if (CbxDIFF!=null) CbxDIFF.setEnabled(bSchicht);
            if (CbxPAUSE!=null) CbxPAUSE.setEnabled(bSchicht);
            if (CbxMEMO2!=null) CbxMEMO2.setEnabled(bSchicht);
            if (CbxAIO!=null) CbxAIO.setEnabled(bSchicht && CbxSeiteProGruppe.isEnabled());
            if (CbxVB!=null) CbxVB.setEnabled(bSchicht);
            VecTC.elementAt(0).setEnabledAt(2,bSchicht);
            //CbxTMZ.getParent().getParent().setEnabled(bSchicht);
          }
        //CbxFarbe.setEnabled(!CbxFarbe.isSelected());
}

public void SelectByAIC(JCOutliner Gid,int SelectByAIC)
{
  //g.fixtestInfo("Druckfrage.SelectByAIC:"+SelectByAIC);
	/*if (Gid != null)
	{
		JCOutlinerNode Nod[]=Gid.getSelectedNodes();
		java.awt.Event ev=new java.awt.Event(null,0,null);
    ev.modifiers=Event.CTRL_MASK;
		for (int i=0;i<Nod.length;i++)

			Out.selectNode(Nod[i],ev);
	}
	else*/ if(Gid != null || iAIC_Selected>0)
	{
		JCOutlinerNode Node = null;
		Vector<Integer> Vec=null;
		if (Gid != null)
		{
                  JCOutlinerNode Nod[]=Gid.getSelectedNodes();
                  if (Nod!=null && Nod.length>0)
                  {
                    Vec = new Vector<Integer>();
                    for (int i = 0; i < Nod.length; i++)
                      Vec.addElement(bHauptschirm ? (Integer)((Vector)Nod[i].getUserData()).elementAt(0) : (Integer)Nod[i].getUserData());
                  }
		}
		java.awt.Event ev=new java.awt.Event(null,0,null);
    ev.modifiers=Event.CTRL_MASK;
    Out.selectNode(Out.getRootNode(),null);
		for(JCOutlinerNode NodeS=Out.getNextNode(Out.getRootNode());Node==null && NodeS!=null;NodeS=Out.getNextNode(NodeS))
		{
			Integer Int=bHauptschirm?(Integer)((Vector)NodeS.getUserData()).elementAt(0):(Integer)NodeS.getUserData();
			if (Vec != null)
			{
				//JCOutlinerNode Nod2[]=Out.getSelectedNodes();
				if (Vec.contains(Int))
				  Out.selectNode(NodeS,ev);
			}
			else if(Int.intValue()==iAIC_Selected)
                        {
                          Node = NodeS;
                          //g.fixtestInfo("gefunden:"+Node.getLabel());
                        }
		}

		if(Node!=null)// && Gid == null)
                  Static.makeVisible(Out,Node);
			//Out.selectNode(Node,null);
	}
}

private void addTabStamm(Tabellenspeicher Tab,Object[] Node)
{
  for(int i=0;i<Node.length;i++)
  {
    if (!bHauptschirm && ((JCOutlinerNode)Node[i]).getUserData()==null)
      addTabStamm(Tab,((JCOutlinerFolderNode)Node[i]).getChildren().getArrayCopy());
    else if((JCOutlinerNode)Node[i] != Out.getRootNode())
    {
	Tab.addInhalt("Aic",bHauptschirm?((Vector)((JCOutlinerNode)Node[i]).getUserData()).elementAt(0):((JCOutlinerNode)Node[i]).getUserData());
	Tab.addInhalt("Bezeichnung",CbxAlleSpalten.isSelected() ? Sort.gets2(((JCOutlinerNode)Node[i]).getLabel(),' '):Sort.gets(((JCOutlinerNode)Node[i]).getLabel()));
    }
  }
}

private Tabellenspeicher getTabStamm(int iBegriff)
{
  int iPos=g.TabBegriffe.getPos("Aic", iBegriff);
  int iBew=iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Erf");
  int iStt=iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Stt");
  //g.fixtestInfo("getTabStamm:"+iBegriff+"-> Stt="+iStt+", Bew="+iBew);
  //g.progInfo("getTabStamm "+iBegriff+":"+iAIC_Stammtyp+"->"+iStt+"/"+iBew);
	Tabellenspeicher TabStamm=new Tabellenspeicher(g,new String[] {"Aic","Bezeichnung"});
	Object[] Node;
	if(bHauptschirm)
	{
		//int iiAIC_Stammtyp=((Integer)((Vector)Out.getSelectedNode().getUserData()).elementAt(1)).intValue();
		Vector<JCOutlinerNode> VecNodes = new Vector<JCOutlinerNode>();

		for(JCOutlinerNode NodeAlle = Out.getNextNode(Out.getRootNode());bAlle && NodeAlle!=null;NodeAlle = Out.getNextNode(NodeAlle))
			if(((Integer)((Vector)NodeAlle.getUserData()).elementAt(1)).intValue()==iAIC_Stammtyp)
				VecNodes.addElement(NodeAlle);

		Node=bAlle?VecNodes.toArray():Out.getSelectedNodes();
	}
	else
		Node=bAlle?Out.getRootNode().getChildren().getArrayCopy():Out.getSelectedNodes();
	addTabStamm(TabStamm,Node);
	/*for(int i=0;i<Node.length;i++)
	{
		if((JCOutlinerNode)Node[i] != Out.getRootNode())
		{
			TabStamm.addInhalt("Aic",bHauptschirm?((Vector)((JCOutlinerNode)Node[i]).getUserData()).elementAt(0):((JCOutlinerNode)Node[i]).getUserData());
			TabStamm.addInhalt("Bezeichnung",CbxAlleSpalten.isSelected() ? Sort.gets2(((JCOutlinerNode)Node[i]).getLabel()):Sort.gets(((JCOutlinerNode)Node[i]).getLabel()));
		}
	}*/
	//if (g.TestPC())
	//  	TabStamm.showGrid("Stamm2");
	if (iBew==0 && iAIC_Stammtyp<0)
	{
	  int iEig=SQL.getInteger(g,"select z.aic_eigenschaft from bew_zuordnung z join eigenschaft e on z.aic_eigenschaft=e.aic_eigenschaft where z.aic_bewegungstyp="+
	      (-iAIC_Stammtyp)+" and e.aic_stammtyp="+iStt);
	  //g.progInfo("iEig="+iEig+" bei Bew "+(-iAIC_Stammtyp)+" mit Stt "+iStt);
	  TabStamm=new Tabellenspeicher(g,"select distinct bew_stamm.aic_stamm Aic,Bezeichnung from bew_stamm join stammview2 on bew_stamm.aic_stamm=stammview2.aic_stamm and aic_rolle is null"+
            " where "+g.in("aic_bew_pool",TabStamm.getVecSpalte("Aic"))+" and aic_eigenschaft="+iEig, true);

	}
	//if (g.TestPC())
	//  	TabStamm.showGrid("TabStamm");
	return TabStamm;
}

private Vector getVecE()
{
  Vector<Integer> VecE=new Vector<Integer>();
  int iPos=VecEbenen.indexOf(new Integer(iAIC_Stammtyp));
  if (iPos>-1)
    for (int i=0;i<=iPos-(CbxLetzteGruppeWeg.isSelected()?1:0);i++)
      VecE.addElement(VecEbenen.elementAt(i));
  return VecE;
}

private void showPrint()
      {
        //Pnt.druckeAbfrage(CboAbfrage.getSelectedBezeichnung(),CboAbfrage.getSelectedAIC(),iAIC_Stammtyp,getTabStamm(),CboDiagramm==null?"":CboDiagramm.getSelectedKennung(),CbxGruppiert.isSelected()?Out:null,Pnt.getPrintManager(),Pnt.bQuer,iBits);;
  	int iBegriff=RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():CboAbschnitt.getSelectedAIC();
  	Tabellenspeicher TabStamm=getTabStamm(iBegriff);
        ShowAbfrage Abf=new ShowAbfrage(g,iBegriff,ShowAbfrage.cstBegriff);
        AUOutliner GidGesamt = new AUOutliner();
        ShowAbfrage.initOutliner(g, GidGesamt);
        JFrame f = new JFrame("Vorschau");
        f.getContentPane().add(GidGesamt);

        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        if (CbxGruppiert.isSelected())
        {
          //GidGesamt.setRootNode((JCOutlinerFolderNode)RootNode.clone());
          GidGesamt.setRootNode(checkOutliner(false).getRootNode());
          //GidGesamt.folderChanged(GidGesamt.getRootNode());
          for (JCOutlinerFolderNode Nod=(JCOutlinerFolderNode)GidGesamt.getRootNode();Nod != null;Nod=(JCOutlinerFolderNode)GidGesamt.getNextNode(Nod))
          {
            //g.progInfo("showPrint1:"+Nod+"/"+Nod.getLevel()+"/"+Nod.getUserData()+"/"+iAIC_Stammtyp);
            if (Nod.getLevel()>0 && iAIC_Stammtyp==( (Integer) ( (Vector) Nod.getUserData()).elementAt(1)).intValue())
            {
              Nod.setState((Abf.iBits&Abfrage.cstVerdichten)==0 ? BWTEnum.FOLDER_OPEN_ALL:BWTEnum.FOLDER_CLOSED);
              //int iAic=( (Integer) ( (Vector) Nod.getUserData()).elementAt(0)).intValue();
              Abf.TabToOutliner(GidGesamt, Abf.getDaten(iAIC_Stammtyp,Tabellenspeicher.geti(((Vector)Nod.getUserData()).elementAt(0)), null,null), CbxLetzteGruppeWeg.isSelected()? Nod.getParent():Nod,getVecE(),1);
              if (CbxLetzteGruppeWeg.isSelected())
                Nod.getParent().removeChild(Nod);
            }
          }
        }
        else
        {
          //g.progInfo("showPrint:"+iAIC_Stammtyp+"/"+TabStamm.getI("AIC"));
          Tabellenspeicher Tab=Abf.getDaten(iAIC_Stammtyp, TabStamm.getI("AIC"), TabStamm.getVecSpalte("AIC"),null);
          //Tab.showGrid("Daten");
          Abf.TabToOutliner(GidGesamt,Tab,  null);
          //Abf.getSpalten().showGrid("Spalten2");
        }
        GidGesamt.folderChanged(GidGesamt.getRootNode());
        //f.show();
        f.pack();
      }

      private int getBits(String sArt)
      {
        int i= (/*sArt.equals("Druck") && */CbxSeitenvorschau.isSelected() ? Drucken.cstSeitenvorschau : 0) + (CbxNurSumme.isSelected() ? Drucken.cstNurSumme : 0) +
            //(CbxDiaDrehen.isSelected() ? Drucken.cstDiaDrehen : 0) + (CbxKeinDiagramm.isSelected() ? Drucken.cstKeinDiagramm :0) +
                    (CbxSeitenumbruch.isSelected() ? Drucken.cstSeitenumbruch : 0) + (CbxStammLinks.isSelected() ? Drucken.cstStammLinks : 0) +
                    (CbxKeineSumme.isSelected()?Drucken.cstKeineSumme:0)+(CbxGruppiert.isSelected()?Drucken.cstGruppiert:0)+
                    (CbxLetzteGruppeWeg.isSelected()?Drucken.cstLetzteGruppeWeg:0)+(CbxPeriode.isSelected()?Drucken.cstPeriode:0)+
                    (CbxDruckerAuswahl.isSelected()?Drucken.cstDruckerauswahl:0)+(CbxAlleSpalten.isSelected()?Drucken.cstAlleSpalten:0)+
                    (CbxSeiteProGruppe.isSelected()?Drucken.cstSeiteProGruppe:0)+(Rad_h_dez.isSelected()?Drucken.cstH_DEZ:Rad_h_min.isSelected()?Drucken.cstH_MIN:0)+
                    /*(CbxDruckerAuswahl2.isSelected()?Drucken.cstDruckerauswahl2:0)*/+(CbxFarbe.isSelected()?Drucken.cstDFFarbe:0)+//(CbxED.isSelected()?Drucken.cstED:0)+
                    (sArt.equals("PDF") || sArt.equals("PDF_View")?Drucken.cstPDF:sArt.equals("E-Mail2")?Drucken.cstEMail:sArt.equals("PDF_DB")?Drucken.cstPDF_DB:0);
        int iDBits=new Double(CboDruck.Cbo.getSelectedFaktor()).intValue();
        if (CbxTMZ!=null && (iDBits & Drucken.cstPntSchicht)>0)
          i+=(CbxTMZ.isSelected() ? Drucken.cstTMZ : 0)+(CbxPAZ!=null && CbxPAZ.isSelected() ? Drucken.cstPAZ : 0)+(CbxIST!=null && CbxIST.isSelected() ? Drucken.cstIST : 0)+
            (CbxSOLL!=null && CbxSOLL.isSelected() ? Drucken.cstSOLL : 0)+(CbxDIFF!=null && CbxDIFF.isSelected() ? Drucken.cstDIFF : 0)+Drucken.cstPb+
            (CbxPAUSE==null || CbxPAUSE.isSelected() ? Drucken.cstPAUSE : 0)+(CbxMEMO2!=null && CbxMEMO2.isSelected() ? Drucken.cstMEMO2 : 0)+
            (CbxAIO!=null && CbxAIO.isSelected() ? Drucken.cstAIO : 0)+(CbxVB==null || CbxVB.isSelected() ? Drucken.cstVB : 0)+(CbxPPS!=null && CbxPPS.isSelected() ? Drucken.cstPPS : 0);
        return i;
      }

      private void Drucken(String sArt)
      {
        //long lClock = Static.get_ms();
        if (sArt.equals("Druck"))
          g.BtnDruck=BtnDrucken;
        else if (sArt.equals("PDF"))
          g.BtnDruck=BtnPDF;
        else if (sArt.equals("PDF_View"))
            g.BtnDruck=BtnPDF_View;
        else if (sArt.equals("PDF_DB"))
            g.BtnDruck=BtnPDF_DB;
        else if (sArt.equals("E-Mail2"))
          g.BtnDruck=BtnEMail;
        g.BtnDruck.setEnabled(false);
        //boolean bPDF=sArt.equals("PDF");
        //boolean bEMail=sArt.equals("E-Mail");
        //boolean bDruck=sArt.equals("Druck");
              try
              {
                int iBits = getBits(sArt);
                int iLayout=CboLayout==null ? 0:CboLayout.getSelectedAIC();
                setParameter(iAIC_Stammtyp,iBits);
                g.clockInfo("Drucken.setParameter");
                if (RadDruck.isSelected())
                {
                  /*int iBitsDruck=new Double(CboDruck.getSelectedFaktor()).intValue();
                  if ((iBitsDruck & Drucken.cstPntNeuerDruck)==0)
                    Static.printError("Alter Druck wird nicht mehr unterstützt!");
                  else*/
                  {
                	long lClock=Static.get_ms();
                    g.clockInfo("vor DruckAufrufA");
                    Global g2 = g;
                    String sName=EdtLogName.getText();
                    if (!sName.equals(""))//!Static.bX11)
                    {
                      g2=new Global(g,false);
                      if (!g2.Login(thisJFrame(),sName, null))//EdtLogPW.getValue()))
	  					{
	  						new Message(Message.WARNING_MESSAGE, null, g).showDialog("Einloggfehler", new String[] {sName});
	  						Global.BtnDruck.setEnabled(true);
	  						return;
	  					}
	  					else
	  					{
	  						g2.bTestLog=true;
	  						ShowAbfrage.refreshBerechtigung(g2);
	  					}
                    }
                    DruckAufrufA daa = new DruckAufrufA(g2,iLayout);
                    /*JCOutlinerFolderNode Nod=null;
                    if (CbxGruppiert.isSelected())
                      Nod=(JCOutlinerFolderNode)RootNode.clone();*/
                    //JCOutlinerFolderNode Nod=CbxGruppiert.isSelected() || CbxSeiteProGruppe.isSelected() ? (JCOutlinerFolderNode)RootNode.clone():null;
                    /*JCOutliner Out2=null;
                    if (CbxGruppiert.isSelected())
                      Out2=checkOutliner(false);*/
                    JCOutlinerFolderNode Nod=CbxGruppiert.isSelected() ? (JCOutlinerFolderNode)checkOutliner(false).getRootNode() :
                        CbxSeiteProGruppe.isSelected() ? (JCOutlinerFolderNode)RootNode.clone():null;
                    g.addUsed(CboDruck.Cbo.getSelectedAIC());
                    g.clockInfo("vor druckeDruck");
                    /*int iPos=g.TabBegriffe.getPos("Aic", CboDruck.getSelectedAIC());
                    int iBew=g.TabBegriffe.getI(iPos,"Erf");
                    int iStt=iBew>0 ? -iBew:g.TabBegriffe.getI(iPos,"Stt");*/
                    //g.progInfo("------------------- druckeDruck:"+CboDruck.getSelectedAIC()+"/"+iStt);
                    g.fixtestInfo("Drucke "+CboDruck.Cbo.getSelectedBezeichnung()+", Seitenumbruch="+((iBits&Drucken.cstSeitenumbruch)>0));
                    String sPDF_File=EdtPDF.getValue();
                    int iDruck=CboDruck.Cbo.getSelectedAIC();
                    if (sArt.equals("PDF_View"))
                    {
                    	String sPDF="Druck"+iDruck+new SimpleDateFormat("_yyyyMMdd_HHmmss").format(new Date())+".pdf";
                		sPDF_File=Static.DirError+sPDF;
                    }
                    Thread thr=daa.druckeDruck(iDruck, getTabStamm(CboDruck.Cbo.getSelectedAIC()),sPDF_File,EdtPW.getValue(),
                                    Nod, iBits,CboVorlagen.getSelectedAIC(),CbxGruppiert.isSelected() ? getVecE():null,0/*CboSort.getSelectedAIC()*/,0);
//                    g.clockInfo("fertig");
//                    thr.setPriority(Thread.MAX_PRIORITY);
//                    thr.setName("Druck "+CboDruck.Cbo.getSelectedBezeichnung());
                    if (thr != null)
                    {
                    	g.fixInfoT(thr.getName()+"-Dauer: "+(Static.get_ms()-lClock)+" ms");
                    	g.fixInfoT("Threads:"+Thread.currentThread().getName()+":"+Thread.currentThread().getPriority()+"/"+thr.getName()+":"+thr.getPriority());
	                    //TimeUnit.SECONDS.timedJoin(thr, 6);
	//                    int iS=0;
	//                    Static.sleep(5000);
	//                    while (thr.isAlive())
	//                    {
	//                    	Static.sleep(100);
	//                    	iS++;
	//                    }
	//                    thr.resume();
	//                    thr.join();
                    }
                    g.fixInfoT("Druck fertig -> Dauer: "+(Static.get_ms()-lClock)+" ms / "+(thr==null ? "":thr.isAlive()+"/"+thr.isDaemon()));
                  }
                }
                else if (RadAbfrage.isSelected())
                {
                  g.clockInfo("vor DruckAufrufA");
                  DruckAufrufA daa=new DruckAufrufA(g,iLayout);
                  g.clockInfo("vor druckeAbfrage");
                  daa.druckeAbfrage(CboAbfrage.getSelectedBezeichnung(),EdtPDF.getValue(),EdtPW.getValue(),CboAbfrage.getSelectedAIC(),iAIC_Stammtyp,getTabStamm(CboAbfrage.getSelectedAIC()),CboVorlagen.getSelectedAIC(),iBits,
                      CbxGruppiert.isSelected() || CbxSeiteProGruppe.isSelected() ? (JCOutlinerFolderNode)checkOutliner(false).getRootNode():null,CbxGruppiert.isSelected() ? getVecE():null);
                  g.clockInfo("fertig");
                }
                else
                {
                  long lClock = Static.get_ms();
                  JCOutliner Out2=checkOutliner(true);
                  /*Drucken.bAktiv=false;
                  if (true)
                    return;*/
                  int iHS_Bits=iBits&(Drucken.cstDruckerauswahl+Drucken.cstDFFarbe);
                  g.testInfo("iHS_Bits="+iHS_Bits);
                  g.clockInfo("vor new DruckHS");
                  DruckHS dh = new DruckHS(g,"Hauptschirm",iLayout,iHS_Bits,0);
                  g.clockInfo("nach new DruckHS");
                  //dh.iVSpace=3;
                  int iAIC_Raster=CboVorlagen.getSelectedAIC();
                  dh.LoadRaster(iAIC_Raster);
                  //dh.TabRaster=new Tabellenspeicher(g,"select aic_raster,kennung "+g.AU_Bezeichnung("raster")+",aic_schrift,sch_aic_schrift,sch2_aic_schrift,sch3_aic_schrift,sch4_aic_schrift,sch5_aic_schrift,sch6_aic_schrift,bits from raster where "+
                  //                                  (iAIC_Raster>0 ? "aic_raster="+iAIC_Raster:g.bit("bits",Drucken.cstStandard)),true);
                  //dh.setTitle("",PrintManagerA.CENTER);
                  dh.setDTitel(EdtText.getText(),false,g.fontTitel);
                  dh.printTitel(false,true,false,false);
                  Tabellenspeicher Tab=null;
                  if (Abf != null)//iAbfrage>0)
                    Tab = Abf.getTabDruckbreite(VecEbenen.size()-1);
                  g.clockInfo("Druck.Raster");
                  DruckHS.showOutliner(Out2);
                  dh.addOutliner(Out2,null,iAIC_Raster,0,iBits,Tab);
                  g.clockInfo("Druck.addOutliner");
                  if (sArt.equals("PDF"))
                    dh.printPDF(null,1,dh.pages(),EdtPW.getValue(),null);
                  else if (CbxSeitenvorschau.isSelected())
                    dh.vorschau();
                  else
                    dh.print();
                  g.clockInfo("Druck.vorschau");
                  g.clockInfo("Hauptschirm-Druck",lClock);
                }
              }
              catch(Exception e)
              {
                //Drucken.bAktiv=false;
                      e.printStackTrace();
                      Static.printError("DruckFrage.Drucken:"+e);
                      Static.printError("bei "+(RadDruck.isSelected()?"Druck "+CboDruck.Cbo.getSelectedKennung():"Abfrage "+CboAbfrage.getSelectedKennung()));
              }
            //g.clockInfo("Druck - "+(RadDruck.isSelected()?"Druck "+CboDruck.getSelectedBezeichnung():"Abfrage "+CboAbfrage.getSelectedBezeichnung()),lClock);
      }

/*private String getBez(Object Obj)
{
  int i=Tabellenspeicher.geti(Obj);
  return i>0 ? g.TabStammtypen.getBezeichnung(i):g.TabErfassungstypen.getBezeichnung(-i);
}*/

@SuppressWarnings("unchecked")
private JCOutliner checkOutliner(boolean b)
{
  JCOutliner OutNew=new JCOutliner(new JCOutlinerFolderNode(""));
  OutNew.setRootVisible(false);
  Vector<String> VecB;
  //int iStt=0;
  int iPosStt=-1;
  if (b && bHauptschirm)
  {
    VecB = new jclass.util.JCVector(Out.getColumnLabels());
    VecB.setElementAt(g.getBez(VecEbenen.elementAt(0)),0);
    for (int i = 1; i < VecEbenen.size(); i++)
      VecB.insertElementAt(g.getBez(VecEbenen.elementAt(i)), i);
  }
  else
  {
    VecB=new Vector<String>();
    //iStt=Tabellenspeicher.geti(((Vector)Out.getSelectedNode().getUserData()).elementAt(1));
    iPosStt=VecEbenen.indexOf(new Integer(iAIC_Stammtyp));
    //g.progInfo("iStt="+iStt+", Pos="+iPosStt);
    for (int i = 0; i <= iPosStt; i++)
      VecB.addElement(g.getBez(VecEbenen.elementAt(i)));
  }
  OutNew.setColumnButtons(new jclass.util.JCVector(VecB));
  OutNew.setNumColumns(VecB.size());
  for (int i=VecEbenen.size();i<VecB.size();i++)
    OutNew.setColumnAlignment(i,Out.getColumnAlignment(i+1-VecEbenen.size()));
  Vector<JCOutlinerNode> VecR=new Vector<JCOutlinerNode>();
  VecR.addElement(OutNew.getRootNode());
  Vector Vec=DruckHS.getOutlinerNodes(Out);
  for (int i=0;i<Vec.size();i++)
  {
    JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
    int iLevel=Nod.getLevel();
    JCOutlinerNode NodNew=null;
    Vector<Object> VecDaten=null;
    g.progInfo("UserData="+Nod.getUserData());
    //boolean bWert=Sort.geti(Nod.getUserData(),0)!=0;
    int iPos=VecEbenen.indexOf(Nod.getUserData() instanceof Vector ? ((Vector)Nod.getUserData()).elementAt(1):Nod.getUserData());
    if (b)
      VecDaten=new Vector((Vector)Nod.getLabel());
    else if (iPos<=iPosStt)
    {
      VecDaten=new Vector();
      //if (bWert)
        VecDaten.addElement(((Vector)Nod.getLabel()).elementAt(0));
    }
    //g.fixtestInfo("VecDaten"+iPos+"="+VecDaten+"/"+Nod.getUserData());
    if (VecDaten != null)
    {
      for (int i2 = 0; i2 < iPos; i2++)
        VecDaten.insertElementAt(null, 0);
      for (int i2 = iPos + 1; i2 < (b ? VecEbenen.size():iPosStt+1); i2++)
        VecDaten.insertElementAt(null, iPos + 1);
      if (Nod instanceof JCOutlinerFolderNode)
        NodNew = new JCOutlinerFolderNode((Object)VecDaten, (JCOutlinerFolderNode)VecR.elementAt(iLevel - 1));
      else
        NodNew = new JCOutlinerNode(VecDaten, (JCOutlinerFolderNode)VecR.elementAt(iLevel - 1));
      if (!b)
        NodNew.setUserData((Vector)Nod.getUserData());
      if (iLevel >= VecR.size())
        VecR.addElement(NodNew);
      else
        VecR.setElementAt(NodNew,iLevel);
    }
    //g.fixtestInfo(i+":"+Nod.getLevel()+"/"+Nod.getLabel()+"/"+Nod.getUserData());
  }
  /*JFrame frm=new JFrame("OutNew");
  frm.getContentPane().add("Center",OutNew);
  frm.pack();
  frm.setVisible(true);*/
  return OutNew;
}

private void setParameter(int iStt,int iBits)
{
  iBits=iBits+(RadDruck.isSelected()?Drucken.cstCboDruck:RadAbfrage.isSelected()?Drucken.cstCboAbfrage:RadHS.isSelected()? Drucken.cstCboHS:Drucken.cstCboRest);
  int iD=RadDruck.isSelected()?CboDruck.Cbo.getSelectedAIC():RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():0;
  iD+=CboVorlagen.getSelectedAIC()*0x20000;
        if (TabDrucke.posInhalt(new Integer(iBeg),new Integer(iStt)))
        {
          TabDrucke.setInhalt("DruckNeu",iD);
          TabDrucke.setInhalt("bitsNeu",iBits);
        }
        else
        {
          //TabDrucke.newLine();
          TabDrucke.addInhalt("aic",null);
          TabDrucke.addInhalt("Druck",null);
          TabDrucke.addInhalt("bits",null);
          TabDrucke.addInhalt("HS",iBeg);
          TabDrucke.addInhalt("Stt",iStt);
          TabDrucke.addInhalt("DruckNeu",iD);
          TabDrucke.addInhalt("bitsNeu",iBits);
        }
        //if (g.Prog())
        //  TabDrucke.showGrid();
}

private void getParameter(int iStt)
{
        if (TabDrucke.posInhalt(new Integer(iBeg),new Integer(iStt)))
        {
          bDF_Action=true;
          int iBits=TabDrucke.getI("bitsNeu");
          int iArt=iBits&Drucken.cstCboArt;
          int iCboDruck=TabDrucke.getI("DruckNeu") % 0x20000;
          int iCboRaster=TabDrucke.getI("DruckNeu") / 0x20000;
          CboVorlagen.setSelectedAIC(iCboRaster);
          CboLayout.setSelectedAIC(0);
          g.progInfo("getParameter (Last):"+iCboRaster+"/0");
          /*if (iDruck>0)
          {
            RadDruck.setSelected(true);
            CboDruck.setSelectedAIC(iDruck);
          }
          else*/ if (iDruck>0 || iArt==Drucken.cstCboDruck)
          {
            RadDruck.setSelected(true);
            //set(BtnDF_Druck);
            if (BtnDF_Druck!=null) BtnDF_Druck.doClick();
            CboDruck.Cbo.setSelectedAIC(iDruck>0 ? iDruck:iCboDruck);
          }
          else if (iArt==Drucken.cstCboAbfrage)
          {
            RadAbfrage.setSelected(true);
            //set(BtnDF_Abfrage);
            if (BtnDF_Abfrage!=null) BtnDF_Abfrage.doClick();
            CboAbfrage.setSelectedAIC(iCboDruck);
          }
          else if (iArt==Drucken.cstCboHS)
          {
            RadHS.setSelected(true);
            //set(BtnDF_HS);
            if (BtnDF_HS!=null) BtnDF_HS.doClick();
          }

          CbxSeitenumbruch.setSelected((iBits&Drucken.cstSeitenumbruch)>0);
          //CbxDiaDrehen.setSelected((iBits&Drucken.cstDiaDrehen)>0);
          //CbxKeinDiagramm.setSelected((iBits&Drucken.cstKeinDiagramm)>0);
          CbxNurSumme.setSelected((iBits&Drucken.cstNurSumme)>0);
          CbxPeriode.setSelected((iBits&Drucken.cstPeriode)>0);
          CbxDruckerAuswahl.setSelected((iBits&Drucken.cstDruckerauswahl)>0);
          //CbxDruckerAuswahl2.setSelected((iBits&Drucken.cstDruckerauswahl2)>0);
          CbxFarbe.setSelected((iBits&Drucken.cstDFFarbe)>0);
//          CbxED.setSelected((iBits&Drucken.cstED)>0);
          CbxAlleSpalten.setSelected((iBits&Drucken.cstAlleSpalten)>0);
          //CbxLinien.setSelected((iBits&Drucken.cstLinien)>0);
          CbxSeitenvorschau.setSelected((iBits&Drucken.cstSeitenvorschau)>0);
          //CbxKeinKopfFuss.setSelected((iBits&Drucken.cstKeinKopfFuss)>0);
          CbxStammLinks.setSelected((iBits&Drucken.cstStammLinks)>0);
          //CbxSeitencheck.setSelected((iBits&Drucken.cstSeitencheck)>0);
          CbxLetzteGruppeWeg.setSelected((iBits&Drucken.cstLetzteGruppeWeg)>0);
          //CbxDoppelteZeilenhoehe.setSelected((iBits&Drucken.cstDoppelteZeilenhoehe)>0);
          //CbxKeine0Werte.setSelected((iBits&Drucken.cstKeine0Werte)>0);
          //CbxQuerformat.setSelected((iBits&Drucken.cstQuerformat)>0);
          //CbxA3.setSelected((iBits&Drucken.cstA3)>0);
          //CbxGesamtrahmen.setSelected((iBits&Drucken.cstGesamtrahmen)>0);
          CbxKeineSumme.setSelected((iBits&Drucken.cstKeineSumme)>0);
          CbxGruppiert.setSelected((iBits&Drucken.cstGruppiert)>0);
          CbxSeiteProGruppe.setSelected((iBits&Drucken.cstSeiteProGruppe)>0);
          if ((iBits & Drucken.cstPb)>0 && CbxTMZ!=null)
          {
            CbxTMZ.setSelected((iBits & Drucken.cstTMZ) > 0);
            if (CbxPAZ!=null) CbxPAZ.setSelected((iBits & Drucken.cstPAZ) > 0);
            if (CbxPPS!=null) CbxPPS.setSelected((iBits & Drucken.cstPPS) > 0);
            if (CbxIST!=null) CbxIST.setSelected((iBits & Drucken.cstIST) > 0);
            if (CbxSOLL!=null) CbxSOLL.setSelected((iBits & Drucken.cstSOLL) > 0);
            if (CbxDIFF!=null) CbxDIFF.setSelected((iBits & Drucken.cstDIFF) > 0);
            if (CbxPAUSE!=null) CbxPAUSE.setSelected((iBits & Drucken.cstPAUSE) > 0);
            if (CbxMEMO2!=null) CbxMEMO2.setSelected((iBits & Drucken.cstMEMO2) > 0);
            if (CbxAIO!=null) CbxAIO.setSelected((iBits & Drucken.cstAIO) > 0);
            if (CbxVB!=null) CbxVB.setSelected((iBits & Drucken.cstVB) > 0);
          }

          if ((iBits&Drucken.cstART_H)==0)
            Rad_h_auto.setSelected(true);
          else if ((iBits & Drucken.cstART_H) == Drucken.cstH_DEZ)
            Rad_h_dez.setSelected(true);
          else if ((iBits & Drucken.cstART_H) == Drucken.cstH_MIN)
            Rad_h_min.setSelected(true);
          setDF(DF_Last);
          bDF_Action=false;
        }
        else if (iDruck>0)
          {
            setDF(DF_Std);
            RadDruck.setSelected(true);
            //CboDruck.setSelectedAIC(iDruck);
          }
}

/*private void setArt(JToggleButton Btn)
        {
          if (Btn!=null)
          {
            g.progInfo("set "+Btn.getActionCommand());
            Btn.setSelected(true);
          }
        }*/

private void setDF(int iDF)
        {
          g.progInfo("setDF="+(iDF==DF_Std ? "Std":iDF==DF_Last ? "Last":iDF==DF_Pers ? "persönlich":iDF==DF_Jeder ? "Jeder":"?"));
          iStatusDF=iDF;
          if (iDF==DF_Std)
          {
            if (Rad_DF_Std!=null) Rad_DF_Std.setSelected(true);
            //if (BtnDF_Std!=null) BtnDF_Std.setSelected(true);
          }
          else if (iDF==DF_Last)
          {
            if (Rad_DF_Last!=null) Rad_DF_Last.setSelected(true);
            //if (BtnDF_Last!=null) BtnDF_Last.setSelected(true);
          }
          else if (iDF==DF_Pers)
          {
            if (Rad_DF_Pers!=null) Rad_DF_Pers.setSelected(true);
            //if (BtnDF_Save!=null) BtnDF_Save.setSelected(true);
          }
          else if (iDF==DF_Jeder)
          {
            if (Rad_DF_Jeder!=null) Rad_DF_Jeder.setSelected(true);
          }
        }

private void SaveParameter()
{
	//Para.setParameter("DruckFrage","",CbxSeitenvorschau.isSelected()?1:0,CbxAlle.isSelected()?1:0,RadHS.isSelected()?2:RadAbfrage.isSelected()?1:0,RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():CboDruck.getSelectedAIC(),true,false);
        for (TabDrucke.moveFirst();!TabDrucke.eof();TabDrucke.moveNext())
        {
          if (TabDrucke.getI("Druck") !=TabDrucke.getI("DruckNeu") || TabDrucke.getI("bits") !=TabDrucke.getI("bitsNeu"))
          {
            Para.add("int3",TabDrucke.getI("DruckNeu"));
            Para.add("int4",TabDrucke.getI("bitsNeu"));
            Para.add("aic_logging",g.getLog());
            TabDrucke.setInhalt("Druck",TabDrucke.getInhalt("DruckNeu"));
            TabDrucke.setInhalt("bits",TabDrucke.getInhalt("bitsNeu"));
            if (TabDrucke.getI("aic")>0)
              Para.update("Parameter",TabDrucke.getI("aic"));
            else
            {
              Para.add("int1",TabDrucke.getI("HS"));
              Para.add("int2",TabDrucke.getI("Stt"));
              Para.add("aic_benutzer",g.getBenutzer());
              Para.addGruppen();
              TabDrucke.setInhalt("aic",Para.insert("Parameter",true));
            }
          }
        }
}

private void LoadParameter()
{
        Para = new Parameter(g,"DruckFrage");

        TabDrucke=new Tabellenspeicher(g,"select aic_parameter aic,"+g.int1()+" HS,"+g.int2()+" Stt,"+g.int3()+" Druck,"+g.int4()+" bits,"+g.int3()+" DruckNeu,"+g.int4()+" bitsNeu from parameter"+Para.getBWhere("Optionen")+" order by HS",true);
        if (TabDrucke.isEmpty())
        {
          Rad_h_dez.setSelected(g.bH_dez);
          Rad_h_min.setSelected(g.bH_min);
        }
        //TabDrucke.showGrid();
        TabDrucke.sGruppe="HS";
        TabDrucke.sAIC="Stt";

	/*Para.getParameter("DruckFrage",true,false);
	if(Para.bGefunden)
	{
		CbxSeitenvorschau.setSelected(Para.int1!=0);
		//CbxAlle.setSelected(Para.int2!=0);

                if(Para.int3==2)
		{
                  RadHS.setSelected(true);
                }
		else if(Para.int3==1)
		{
			RadAbfrage.setSelected(true);
			if(Para.int4>0)
				CboAbfrage.setSelectedAIC(Para.int4);
		}
		else if(Para.int3==0)
		{
			RadDruck.setSelected(true);
			if(Para.int4>0)
				CboDruck.setSelectedAIC(Para.int4);
		}
	}
	else
	{
		CbxSeitenvorschau.setSelected(true);
	}*/
        CbxSeitenvorschau.setSelected(true);
}

private void checkGruppiert()
{
  if (bDF_Action)
    return;
	//int iDruckBits=SQL.getInteger(g,"select bits from begriff where aic_begriff="+CboDruck.getSelectedAIC());
        if (BtnEMail!=null) BtnEMail.setEnabled(false);
        if (BtnPDF_DB!=null) BtnPDF_DB.setEnabled(false);
//        if (BtnPDF_View!=null) BtnPDF_View.setEnabled(false);
        if (RadDruck.isSelected())
        {
          int iDBits=new Double(CboDruck.Cbo.getSelectedFaktor()).intValue();
          boolean bKeineGruppierung = (iDBits & Drucken.cstPntKeineGruppierung) > 0;
          //g.fixInfo("checkGruppiert: Schicht-Enable verschoben");
          if (BtnEMail!=null) BtnEMail.setEnabled((iDBits & Drucken.cstPntEMail) > 0);
          if (BtnPDF_DB!=null) BtnPDF_DB.setEnabled((iDBits & Drucken.cstPntPdfSave) > 0);
          //if (BtnDF_Last==null || !BtnDF_Last.isSelected())
          if (iStatusDF!=DF_Last)
          {
            CbxSeitenumbruch.setSelected((iDBits & Drucken.cstPntSeitenumbruch) > 0);
            CbxPeriode.setSelected((iDBits & Drucken.cstPntPeriode) > 0);
//            CbxED.setSelected((iDBits & Drucken.cstPntED) > 0);
//            g.fixtestError("checkGruppiert bei "+CboDruck.Cbo.getSelectedBezeichnung()+"/"+Long.toHexString(iDBits));
            //BtnEMail.setEnabled((iDBits & Drucken.cstPntEMail) > 0);
            //if (CbxEMail.isSelected())
            //  CbxEMail.setSelected((iDBits & Drucken.cstPntEMail) > 0);
            //CbxKeinKopfFuss.setSelected((iDBits & Drucken.cstPntKeinKopfFuss) > 0);
            //CbxQuerformat.setSelected((iDBits & Drucken.cstPntQuerformat) > 0);
            CbxStammLinks.setSelected((iDBits & Drucken.cstPntStammLinks) > 0);
            CbxKeineSumme.setSelected(false);
            CbxNurSumme.setSelected(false);
            CbxLetzteGruppeWeg.setSelected(false);
            CboLayout.setSelectedAIC(0);
            CboVorlagen.setSelectedAIC(0);
            g.progInfo("checkGruppiert (Std):0/0");
            CbxGruppiert.setSelected(false);
            if (g.bH_dez)
              Rad_h_dez.setSelected(true);
            else if (g.bH_min)
              Rad_h_min.setSelected(true);
            else
              Rad_h_auto.setSelected(true);
          }
          else if(bKeineGruppierung)
            CbxGruppiert.setSelected(false);
          CbxGruppiert.setEnabled(!bKeineGruppierung);
        }
}

public boolean setEbenen(Tabellenspeicher Tab,int iStt)
{
  VecEbenen=new Vector<Integer>();
  if (iStt==0)
    VecEbenen.addElement(0);
  //Tab.showGrid();
  //g.fixtestInfo("setEbenen1:"+VecEbenen);
  boolean b=iStt==0 ? Tab.moveFirst():Tab.posInhalt("int2",iStt);
  if (b)
  {
    int iHStt = Tab.getI("int1");
    for(;!Tab.eof() && Tab.getI("int1")==iHStt;Tab.moveNext())
    {
      //g.fixtestInfo("setEbenen:"+Tab.getPos()+"->"+Tab.getI("int1")+"/"+Tab.getI("int2")+"/"+Tab.getI("int4"));
      if (Tab.getI("int4")>0)
      {
        int i=Tab.getI("int2");
        VecEbenen.addElement(new Integer(i));
        //g.progInfo("setEbenen:"+VecEbenen.size()+":"+i+"="+(i>0 ? g.TabStammtypen.getBezeichnung(i):g.TabErfassungstypen.getBezeichnung(-i)));
      }
    }
  }
  //g.fixtestInfo("setEbenen2:"+VecEbenen);
  //g.fixInfo("Druck/Abfrage:"+RadDruck.isSelected()+"/"+RadAbfrage.isSelected()+"/"+RadHS.isSelected());
  //if (!RadHS.isSelected() && !RadDruck.isSelected() && !RadAbfrage.isSelected())
  //  RadDruck.setSelected(true);
  return b;
}

public void setAbfrage(ShowAbfrage rAbf)
{
  Abf=rAbf;
}

/*private void testOLEVB()
{
    //TabStamm.moveFirst();
	//ShowAbfrage Abf=new ShowAbfrage(g,iAbfrage,Abfrage.cstBegriff);
	try
	{
		File wordInput = new File("WordInp.txt");
		FileWriter wordInputWriter = new FileWriter(wordInput);
		ShowAbfrage Abf=new ShowAbfrage(g,CboAbfrage.getSelectedAIC(),Abfrage.cstBegriff);
		Tabellenspeicher TabSpalten=Abf.getSpalten();
		//TabSpalten.showGrid();
		//Tabellenspeicher TabDaten=Abf.getDaten(iStammtyp,0,TabStamm.getVecSpalte("AIC"));
		Tabellenspeicher TabDaten=Abf.getDaten(iAIC_Stammtyp,0,getTabStamm().getVecSpalte("AIC"));
		//TabDaten.showGrid();
		wordInputWriter.write(""+CboAbfrage.getSelectedBezeichnung() + "\r\n");
		wordInputWriter.write(""+TabSpalten.getAnzahlElemente(null) + "\r\n");
		wordInputWriter.write(""+(TabDaten.getAnzahlElemente(null)+1) + "\r\n");
		String sZeile="";
		for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
		{
			sZeile+=TabSpalten.getS("bezeichnung");
			if(TabSpalten.getPos()!=(TabSpalten.getAnzahlElemente(null)-1))
				sZeile+=";";

		}
		wordInputWriter.write(sZeile + "\r\n");
		for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
		{
			sZeile="";
			for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
			{
				sZeile+=TabDaten.getS(TabSpalten.getS("kennung"));
				if(TabSpalten.getPos()!=(TabSpalten.getAnzahlElemente(null)-1))
					sZeile+=";";
			}
			wordInputWriter.write(sZeile + "\r\n");

		}
		//TabDaten.CalcExport(",","c:\\test.txt",TabSpalten,false);
		wordInputWriter.close();
		String cmd = "ole.exe";
    	Runtime.getRuntime().exec(cmd);


	}catch(Exception e)
	{
		Static.printError("DruckFrage.testOLEVB:"+e);
	}
}*/

// add your data members here
private Global g;

private Tabellenspeicher TabDrucke;
private Vector<Integer> VecEbenen;
private ShowAbfrage Abf=null;

private static boolean bHauptschirm = false;
private static int iDruck = 0;
//private static int iAbfrage = 0;
private static int iRolle = 0;
private static int iAIC_Stammtyp = 0;
private static int iBeg = 0;
private static Vector VecDrucke=null;
//private static Vector VecAbfragen=null;
//private int iTabBegriff=0;
private JCOutlinerFolderNode RootNode;
private JCOutlinerFolderNode RootNodeIch;
private static int iAIC_Selected;
private static Vector VecStamm=null;
//private boolean bPrint=false;
//private String[] sHeader;

private AUOutliner Out;
private static AUOutliner Gid;

//private JButton BtnOk;
//private JButton BtnAbbruch;
//private JButton BtnDruckerAuswahl;
//private JButton BtnPapierAuswahl;
//private JButton BtnDruckeinstellungen;
//private JButton BtnSeitenLayout;
//private JButton BtnAbfrage;
private JButton BtnDruckdefinition;
private JButton BtnSyncStamm;
private static JButton BtnDrucken;
private JButton BtnExport;
private JButton BtnPDF;
private JButton BtnPDF_View;
private JButton BtnPDF_DB;
private JButton BtnEMail;
private JButton BtnBeenden;
//private JButton BtnOLEtoWord;
private JButton BtnTest;
private JCheckBox CbxJeder;
private JButton BtnShow;
private JButton BtnSave;
private JButton BtnDel;
private Tabellenspeicher TabDZa;
private Tabellenspeicher TabDZp;
private JToggleButton BtnAlle=null;

private JToggleButton BtnDF_Druck=null;
private JToggleButton BtnDF_Abfrage=null;
private JToggleButton BtnDF_HS=null;

//private JToggleButton BtnDF_Std=null;
//private JToggleButton BtnDF_Last=null;
//private JToggleButton BtnDF_Save=null;

private AUCheckBox CbxSeitenvorschau;
//private AUCheckBox CbxPDF;
//private AUCheckBox CbxEMail;
private FileEingabe EdtPDF;
private AUPasswort EdtPW = new AUPasswort();
private Text EdtLogName;
//private AUPasswort EdtLogPW = new AUPasswort();
//private JCheckBox CbxAlle;
private boolean bAlle;
private AUCheckBox CbxSeitenumbruch;
private AUCheckBox CbxGruppiert;
private AUCheckBox CbxNurSumme;
//private JCheckBox CbxKeinDiagramm;
//private JCheckBox CbxLinien;
//private JCheckBox CbxKeinKopfFuss;
private AUCheckBox CbxStammLinks;
//private JCheckBox CbxSeitencheck;
//private JCheckBox CbxQuerformat;
//private JCheckBox CbxA3;
//private JCheckBox CbxGesamtrahmen;
private AUCheckBox CbxKeineSumme;
private AUCheckBox CbxLetzteGruppeWeg;
//private JCheckBox CbxDoppelteZeilenhoehe;
private AUCheckBox CbxAlleDrucke=new AUCheckBox();
private AUCheckBox CbxPeriode;
private AUCheckBox CbxDruckerAuswahl;
//private JCheckBox CbxDruckerAuswahl2;
private AUCheckBox CbxFarbe;
//private AUCheckBox CbxED;
private AUCheckBox CbxAlleSpalten;
private AUCheckBox CbxSeiteProGruppe;
//private JCheckBox CbxKeine0Werte;
// neue Planungsbits:
private AUCheckBox CbxTMZ;
private AUCheckBox CbxPAZ;
private AUCheckBox CbxPPS;
private AUCheckBox CbxIST;
private AUCheckBox CbxSOLL;
private AUCheckBox CbxDIFF;
private AUCheckBox CbxPAUSE;
private AUCheckBox CbxMEMO2;
private AUCheckBox CbxAIO;
private AUCheckBox CbxVB;

private JLabel LblTitel;
private Text EdtText;

private DruckEingabe CboDruck;
private ComboSort CboAbfrage;
private ComboSort CboAbschnitt;
private DefVorlagen CboVorlagen;
//private JLabel LblSort;
//private ComboSort CboSort;

private JRadioButton RadDruck;
private JRadioButton RadAbfrage;
private JRadioButton RadHS;

private Parameter Para;

private JRadioButton Rad_h_auto;
private JRadioButton Rad_h_dez;
private JRadioButton Rad_h_min;

private JRadioButton Rad_DF_Std;
private JRadioButton Rad_DF_Last;
private JRadioButton Rad_DF_Pers;
private JRadioButton Rad_DF_Jeder;
//private int iStatusArt=-1; // Druck, Abfrage, HS
private int iStatusDF=0;  // Std, Last, Pers, Jeder
private static final int DF_Std=1;
private static final int DF_Last=2;
private static final int DF_Pers=3;
private static final int DF_Jeder=4;

//private boolean bDruckdefinition=false;

//private JCheckBox CbxDiaDrehen;
//private ComboSort CboDiagramm;
//private JButton BtnDiagramm;
private ComboSort  CboLayout;
//private JButton BtnLayout;

private AUTextArea TxtMemo = new AUTextArea();

private static DruckFrage This=null;
private int iAnzahl=0;
private ActionListener AL=null;
private boolean bFertig=true;
private boolean bDF_Action=false;
}

