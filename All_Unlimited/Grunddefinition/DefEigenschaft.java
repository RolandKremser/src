/*
    All_Unlimited-Grunddefinition-DefEigenschaft.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import jclass.bwt.JCActionListener;
import jclass.bwt.JCActionEvent;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.EigenschaftsEingabe;
import All_Unlimited.Allgemein.Eingabe.Format;
import All_Unlimited.Allgemein.Eingabe.RolleEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;


//import javax.swing.JSplitPane;
//import javax.swing.JScrollPane;
//import All_Unlimited.Allgemein.GroupBox;
import javax.swing.JPopupMenu;


//import javax.swing.JMenuItem;
import All_Unlimited.Allgemein.Tabellenspeicher;

import java.util.Date;

import javax.swing.JTabbedPane;

import java.awt.FlowLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class DefEigenschaft extends All_Unlimited.Allgemein.Formular
{

private static DefEigenschaft Self=null;

public static DefEigenschaft get(Global g,int iEig)
  {
    g.fixtestInfo("DefEigenschaft "+iEig);
    if (Self==null)
      Self=new DefEigenschaft(g);
    else
      Self.show();
    Self.pos(iEig);
    return Self;
  }

public static void free()
{
  if (Self != null)
  {
	  Self.g.winInfo("DefEigenschaft.free");
	  Self.thisFrame.dispose();
	  Self=null;
  }
}

private void pos(int iEig)
  {
    if (iEig<=0)
      return;
    Vector Vec = OutEigenschaft.getRootNode().getChildren();
    //boolean b=true;
    if (Vec != null)
      for(int i=0;i<Vec.size()/* && b*/;i++)
      {
        JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
        int iMom=Sort.geti(Nod.getUserData(),0);
        if (iMom==iEig)
        {
          //b=false;
          Static.makeVisible(null,Nod);
          return;
        }
      }
  }

  private void pos(String sSuch,boolean bWeiter)
  {
    //g.fixInfo("pos "+sSuch+(bWeiter?" weiter":" zurück"));
    JCOutlinerNode NodMom=OutEigenschaft.getSelectedNode();
    Vector Vec = OutEigenschaft.getRootNode().getChildren();
    int iStep=bWeiter?1:-1;
    sSuch=sSuch.toLowerCase();
    if (Vec != null)
      for(int i=NodMom==null ?0:Vec.indexOf(NodMom)+iStep;i<Vec.size() && i>=0;i+=iStep)
      {
        JCOutlinerNode Nod = (JCOutlinerNode)Vec.elementAt(i);
        String s1 = Sort.gets(Nod.getLabel(),0).toLowerCase();
        String s2 = Sort.gets(Nod.getLabel(),1).toLowerCase();
        if (s1.contains(sSuch) || s2.contains(sSuch))
        {
          Static.makeVisible(null, Nod);
          return;
        }
    }

  }

private DefEigenschaft(Global glob)
{
	super("DefEigenschaft",null,glob);
	g=glob;
	g.winInfo("DefEigenschaft.create");
	Build();
        addPopup();
        LoadParameter();
	Load();
	show();
}

private void  LoadParameter()
{
  Parameter Para = new Parameter(g,"DefEigenschaft");
  Para.getParameter("Optionen",true,false);
  if(Para.bGefunden)
  {
    iEinBits=Para.int1;
    iEinBitsOld=iEinBits;
  }
}

private void  SaveParameter()
{
	if (iEinBits != iEinBitsOld)
	{
	  Parameter Para = new Parameter(g,"DefEigenschaft");
	  Para.setParameter("Optionen","",iEinBits,-1,0,0,true,false);
	  iEinBitsOld=iEinBits;
	}
}

private void Build()
{
	OutEigenschaft.setBackground(Color.white);
	OutEigenschaft.setRootVisible(false);
        OutEigenschaft.setColumnLabelSortMethod(Sort.sortMethod);
	NodeStyle=g.getTabStyle(g.TabTabellenname.getAic("EIGENSCHAFT"));

	String[] s = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getBezeichnung("Begriffgruppe","Datentyp"),g.getBezeichnung("Tabellenname","STAMM"),
            g.getBezeichnung("Tabellenname","STAMMTYP"),g.getBezeichnung("Tabellenname","ROLLE"),g.getBegriffS("Show","Feldlaenge"),g.getBegriffS("Show","Format"),g.getBegriffS("Show","min"),g.getBegriffS("Show","max"),
            g.getBegriffS("Checkbox","Eindeutig"),g.getBegriffS("Checkbox","Always"),g.getBegriffS("Checkbox","kein_vorfuellen"),g.getBegriffS("Show","Subeigenschaft"),g.getBegriffS("Checkbox","Jeder"),
            g.getShow("Nr"),g.getBegriffS("Checkbox","keinStichtag"),g.getBegriffS("Checkbox","Centrunden"),g.getBegriffS("Checkbox","Tod"),g.getBegriffS("Show","NP"),g.getBegriffS("Show","Change"),g.getBegriffS("Checkbox","Lizenz"),
            g.getShow("Breite"),g.getBegriffS("Checkbox","Icon_Swing"),g.getBegriffS("Checkbox","WebLizenz"),g.getBegriffS("Checkbox","berechtigbar2"),g.getBegriffS("Checkbox","keine Kopie"),
            g.getBegriffS("Checkbox","FilterA"),g.getBegriffS("Checkbox","EingabeArt"),g.getBegriffS("Checkbox","BitsE"),g.getBegriffS("Checkbox","StichtagWeb")};
	OutEigenschaft.setColumnButtons(s);
	OutEigenschaft.setNumColumns(s.length);

	//CboStamm=new ComboSort(g);
	//CboStamm.fillStammTable("Einheit",false);
        CboMass1=new ComboSort(g);
        CboMass1.fillStammBitTable(g.cstEinheit,true);
        CboMass2=new ComboSort(g);
        CboMass2.fillMass(g.getStunde(),true);
        CboMass3=new ComboSort(g);
        CboMass3.fillWaehrung(false,true);

	CboDatentyp=new ComboSort(g);
	CboDatentyp.fillBegriffTable("Datentyp",false);
	CboStt=new ComboSort(g);
	CboStt.fillDefTable("Stammtyp",false);
        CboEig=new EigenschaftsEingabe(g,thisFrame);
        CboEig.Cbo.fillCbo("Select e.aic_Eigenschaft,e.kennung"+g.AU_Bezeichnung2("Eigenschaft","e")+
				" from Eigenschaft e"+g.join("Begriff","e")+" where Begriff.kennung in ('Hierarchie','Gruppe')","Eigenschaft",true,false);
        CboRolle=new RolleEingabe(g,thisFrame);
        CboRolle.fillRolle(-1,true);
        CboBew=new ComboSort(g);
        CboBew.fillDefTable("Erfassungstyp",true);
        CboAbfrage=new AbfrageEingabe(g,thisFrame,false,false);
        
        //CboAbfrage.fillDefTable("Abfrage",true);

	//Ja=g.getBegriff("Show","Ja");
	//Nein=g.getBegriff("Show","Nein");

	Memo = new AUTextArea();
	Memo.Edt.setEditable(false);

        BtnRefresh=getButton("Refresh");
	if(BtnRefresh==null) BtnRefresh=new JButton();
        BtnShow=getButton("show");
	if(BtnShow==null) BtnShow=new JButton();
	BtnNew=getButton("Neu");
	if(BtnNew==null) BtnNew=new JButton();
	BtnDel=getButton("Loeschen");
	if(BtnDel==null) BtnDel=new JButton();
	BtnEdt=getButton("Edit");
	if(BtnEdt==null) BtnEdt=new JButton();
	boolean bRO=g.ReadOnly();
//	g.fixtestError("ReadOnly="+bRO);
    BtnEdt.setEnabled(!bRO);
    BtnNew.setEnabled(!bRO);
	BtnEnd=getButton("Beenden");
	if(BtnEnd==null) BtnEnd=new JButton();
        BtnEinstellung=getButton("Einstellung");
	if(BtnEinstellung==null) BtnEinstellung=new JButton();

	BtnNewEdtOk=g.getButton("Ok");
	BtnNewEdtAbbruch=g.getButton("Abbruch");
        BtnNewEdtDatentyp=g.getButton("Datentyp");
        PnlArt=getFrei("Combo Art");
        if (PnlArt != null)
        {
          CboArt = new ComboSort(g);
          CboArt.setKein(true);
          CboArt.addItem(g.getBezeichnung("Tabellenname","STAMMTYP"),1,"STAMMTYP");
          CboArt.addItem(g.getBezeichnung("Tabellenname","BEWEGUNGSTYP"),2,"BEWEGUNGSTYP");
          CboArt.addItem(g.getBezeichnung("Tabellenname","ROLLE"),3,"ROLLE");
          CboArt.addItemListener(new ItemListener()
          {
            public void itemStateChanged(ItemEvent ev)
            {
              if(ev.getStateChange() == ItemEvent.SELECTED)
                if (CboArt.getSelectedAIC()>0)
                  CboTyp.fillDefTable(CboArt.getSelectedKennung(),true);
                else
                {
                  CboTyp.Clear(true);
                  Load();
                }
            }
          });
          PnlArt.add(CboArt);

          CboTyp = new ComboSort(g);
          PnlArt.add(CboTyp);
          CboTyp.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ev) {
              if(ev.getStateChange() == ItemEvent.SELECTED)
                Load();
            }
          });
          EdtSuche = new Text("",30);
          EdtSuche.setFont(g.fontStandard);
          EdtSuche.addKeyListener(new KeyListener()
          {
                public void keyPressed(KeyEvent ev)
                {
                }
                public void keyReleased(KeyEvent ev)
                {
                  int iK=ev.getKeyCode();
                  if (iK==KeyEvent.VK_DOWN || iK==KeyEvent.VK_ENTER)
                    pos(EdtSuche.getText(),true);
                  else if (iK==KeyEvent.VK_UP)
                    pos(EdtSuche.getText(),false);
                }
                public void keyTyped(KeyEvent ev)
                {
                }
          });

          PnlArt.add(EdtSuche);
        }
        JPanel PnlTyp=getFrei("Combo Typ");
        if (PnlTyp != null)
        {
        	CboDT = new ComboSort(g);
        	CboDT.fillBegriffTable("Datentyp",true);
        	PnlTyp.add(CboDT);
        	CboDT.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ev) {
                  if(ev.getStateChange() == ItemEvent.SELECTED)
                    Load();
                }
              });
        }

        BtnNewEdtOk.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                  if (TxtKennung.isNull())
                  {
                    new Message(Message.ERROR_MESSAGE, FrmNewEdt, g,10).showDialog("KennungLeer");
                    return;
                  }
                  if(SQL.exists(g,"select aic_eigenschaft from  eigenschaft WHERE aic_eigenschaft<> "+(NodeAkt==null ? 0:((Integer)((Vector)NodeAkt.getUserData()).elementAt(0)).intValue())+" and Kennung='"+TxtKennung.getText()+"'"))
                  {
                    new Message(Message.WARNING_MESSAGE, FrmNewEdt, g,10).showDialog("KennungVorhanden");
                    return;
                  }

                                if (NodeAkt!=null)
                                        DatentypCheck(((Integer)((Vector)NodeAkt.getUserData()).elementAt(0)).intValue());

                                SQL Qry=new SQL(g);

                                int iAIC_Eigenschaft=0;
                                int iBits=(CbxEindeutig.isSelected()?Global.cstEindeutig:0)+(CbxAlways.isSelected()?Global.cstAlways:0)+(CbxKeinAutoDate.isSelected()?Global.cstKeinAutoDate:0)+
                                                  (CbxNurModell.isSelected()?Global.cstNurModell:0)+(CbxRound100.isSelected()?Global.cstRound100:0)+(CbxDefault.isSelected()?Global.cstDefault:0)+
                                                  (CbxJeder.isSelected()?Global.cstJeder:0)+(CbxLizenz.isSelected()?Global.cstEigLizenz:0)+(CbxKeinCopy.isSelected()?Global.cstKeinCopy:0)+
                                                  (CbxSysAic.isSelected()?Global.cstSysAic:0)+(CbxTakeBez.isSelected()?Global.cstTakeBez:0)+(CbxKeinEigDate.isSelected()?Global.cstKeinEigDate:0)+
                                                  (CbxBerechtigbar.isSelected()?Global.cstEigBrtg:0)+(CbxStichtag.isSelected()?Global.cstEigStichtag:0)+
                                                  (RadNPklar.isSelected()?Global.cstNPklar:RadNPzwang.isSelected()?Global.cstNPzwang:RadNPhoch.isSelected()?Global.cstNPhoch:Global.cstNPkein)+
                                                  (RadCho.isSelected()?Global.cstEigCho:RadCbo.isSelected()?Global.cstEigCbo:RadRad.isSelected()?Global.cstEigRad:RadBtn.isSelected()?Global.cstEigBtn:RadSbo.isSelected()?Global.cstEigSbo:
                                                	  RadPop.isSelected()?Global.cstEigPop:RadCbx.isSelected()?Global.cstEigCbx:RadSwb.isSelected()?Global.cstEigSwb:RadAuC.isSelected()?Global.cstEigAuC:
                                                	  RadDT.isSelected()?Global.cstEigDT:RadTime.isSelected()?Global.cstEigTime:Global.cstEigAuto)+(CbxEigClean.isSelected() ? Global.cstEigClean:0)+
                                                  (CbxZone.isSelected() ? Global.cstEigZone:0)+(CbxDST.isSelected() ? Global.cstEigDST:0)+(CbxTake.isSelected() ? Global.cstEigTake:0)+(CbxWebLiz.isSelected() ? Global.cstWebLizenz:0);

                                Qry.add("kennung",TxtKennung.getText());
                                Qry.add("aic_begriff",CboDatentyp.getSelectedAIC());
                                //if(CboStamm.isEnabled())
                                        Qry.add0("aic_stamm",CboStamm.getSelectedAIC());
                                //else
                                //        Qry.add("aic_stamm","");
                                //if(CboStt.isEnabled())
                                        Qry.add0("aic_stammtyp",CboStt.getSelectedAIC());
                                //else
                                //        Qry.add("aic_stammtyp","");
                                Qry.add0("eig_aic_eigenschaft",CboEig.getSelectedAIC());
                                Qry.add0("aic_rolle",CboRolle.getSelectedAIC());
                                Qry.add0("aic_bewegungstyp",CboBew.getSelectedAIC());
                                Qry.add0("aic_abfrage",CboAbfrage.getSelectedAIC());
                                Qry.add("feldlaenge",ZahlFeldlaenge.getInteger());
                                Qry.add0("Breite",ZahlBreite.getInteger());
                                Qry.add("format",TxtFormat.getText());
                                Qry.add("min",ZahlMin.getInteger());
                                Qry.add("max",ZahlMax.getInteger());
                                Qry.add("bits",iBits);
                                Qry.add0("Tod",CbxTod.isSelected() ? 1:0);
                                Qry.add("aic_logging",g.getLog());
                                if(NodeAkt==null)
                                {
                                        Qry.add("aic_mandant",g.getMandant());
                                        iAIC_Eigenschaft=Qry.insert("Eigenschaft",true);
                                }
                                else
                                {
                                        iAIC_Eigenschaft=((Integer)((Vector)NodeAkt.getUserData()).elementAt(0)).intValue();
                                        Qry.update("Eigenschaft",iAIC_Eigenschaft);
                                }
                                int iTab=g.TabTabellenname.getAic("EIGENSCHAFT");
                                if (BtnBild.Modified())
                                    g.setImage(BtnBild.getFilename(),iTab,iAIC_Eigenschaft,Global.iSpSw);
//                                if (BtnBildFX.Modified())
//                                  g.setImage(BtnBildFX.getFilename(),iTab,iAIC_Eigenschaft,Global.iSpFX);
                                if (VecAuswahl!=null)
                                {
                                  boolean bMod=false;
                                  for(int i=0;i<VecAuswahl.size();i++)
                                    bMod=bMod || VecAuswahl.elementAt(i).Modified();
                                  if (bMod)
                                  {
                                    Tabellenspeicher Tab = new Tabellenspeicher(g, "select * from Auswahl where aic_eigenschaft=" + iAIC_Eigenschaft, true);
                                    for(int i=0;i<VecAuswahl.size();i++)
                                      if (VecAuswahl.elementAt(i).Modified())
                                      {
                                        Qry.add("Kennung", VecAuswahl.elementAt(i).getText());
                                        if (Tab.posInhalt("Nr",i))
                                          Qry.update("Auswahl",Tab.getI("aic_auswahl"));
                                        else
                                        {
                                          Qry.add("Nr", i);
                                          Qry.add("aic_eigenschaft",iAIC_Eigenschaft);
                                          Qry.insert("Auswahl", false);
                                        }
                                      }
                                    g.LoadAuswahl();
                                  }
                                  Reinigung.checkDatentyp(g,iAIC_Eigenschaft,CboDatentyp.getSelectedKennung());
                                }
                                if (CboDatentyp.Modified() && CboDatentyp.getSelectedKennung().equals("Text"))
                                {
                                  g.fixtestInfo("geändert auf Text -> Reinigung");
                                  Reinigung.checkDatentyp(g,iAIC_Eigenschaft,CboDatentyp.getSelectedKennung());
                                }
                                g.setBezeichnung(NodeAkt==null?"":(String)((Vector)NodeAkt.getLabel()).elementAt(0),TxtBezeichnung.getText(),iTab,iAIC_Eigenschaft,0);
                                g.setMemo(TxtMemo.getValue(),TxtTitel.getText(),iTab,iAIC_Eigenschaft,0);
                                g.setTooltip(TxtTooltip.getValue(),iTab,iAIC_Eigenschaft,0);
                                //g.fixtestInfo("CboBew="+CboBew+", TxtTooltip="+TxtTooltip+", ZahlBreite="+ZahlBreite);
                                g.putTabEigenschaft(iAIC_Eigenschaft,TxtKennung.getText(),TxtBezeichnung.getText(),CboDatentyp.getSelectedKennung(),TxtMemo.getValue(),TxtFormat.getText(),ZahlFeldlaenge.getInteger(),
                                		CboStamm.getSelectedAIC(),CboStt.getSelectedAIC(),iBits,ZahlMin.getInteger(),ZahlMax.getInteger(),CboAbfrage.getSelectedAIC(),CboEig.getSelectedAIC(),CboRolle.getSelectedAIC(),
                                		CboBew.getSelectedAIC(),TxtTooltip.getValue(),ZahlBreite.intValue(),NodeAkt==null);

                                Vector<Comparable> VecVisible=new Vector<Comparable>();
                                Vector<Integer> VecInvisible=new Vector<Integer>();
                                VecVisible.addElement(TxtBezeichnung.getText());
                                VecVisible.addElement(TxtKennung.getText());
                                VecVisible.addElement(CboDatentyp.getSelectedBezeichnung());
                                VecVisible.addElement(CboStamm.getSelectedAIC()>0?CboStamm.getSelectedBezeichnung():"");
                                VecVisible.addElement(CboStt.getSelectedAIC()>0?CboStt.getSelectedBezeichnung():"");
                                VecVisible.addElement(CboRolle.getSelectedAIC()>0?CboRolle.Cbo.getSelectedBezeichnung():"");
                                VecVisible.addElement(ZahlFeldlaenge.getInteger());
                                VecVisible.addElement(TxtFormat.getText());
                                VecVisible.addElement(ZahlMin.getInteger());
                                VecVisible.addElement(ZahlMax.getInteger());
                                VecVisible.addElement(Static.JaNein2(CbxEindeutig.isSelected()));
                                VecVisible.addElement(Static.JaNein2(CbxAlways.isSelected()));
                                VecVisible.addElement(Static.JaNein2(CbxKeinAutoDate.isSelected()));
                                VecVisible.addElement(CboEig.getSelectedAIC()>0?CboEig.Cbo.getSelectedBezeichnung():"");
                                VecVisible.addElement(Static.JaNein2(CbxJeder.isSelected()));
                                VecVisible.addElement(new Integer(iAIC_Eigenschaft));
                                VecVisible.addElement(Static.JaNein2(CbxKeinEigDate.isSelected()));
                                VecVisible.addElement(Static.JaNein2(CbxRound100.isSelected()));
                                VecVisible.addElement(Static.JaNein2(CbxTod.isSelected()));
                                VecVisible.addElement(RadNPkein.isSelected() ?"":g.getBegriffS("Radiobutton",RadNPhoch.isSelected() ? "NPhoch":RadNPzwang.isSelected() ? "NPzwang":RadNPklar.isSelected() ? "NPklar":""));
                                VecVisible.addElement(new java.sql.Date(new Date().getTime()));
                                VecVisible.addElement(Static.JaNein2(CbxLizenz.isSelected()));
                                VecVisible.addElement(ZahlBreite.getInteger());
                                VecVisible.addElement(BtnBild.getFilename());
                                VecVisible.addElement(Static.JaNein2(CbxWebLiz.isSelected())); // BtnBildFX.getFilename());
                                VecVisible.addElement(Static.JaNein2(CbxBerechtigbar.isSelected()));
                                VecVisible.addElement(Static.JaNein2(CbxKeinCopy.isSelected()));
                                //TODO Filter, Art, Bits
                                VecVisible.addElement(CboAbfrage.getSelectedAIC()==0 ? "":CboAbfrage.Cbo.getSelectedBezeichnung());
                                VecVisible.addElement(RadAuto.isSelected() ? "":g.getBegriffS("Radiobutton",RadCho.isSelected() ? "Cho":RadRad.isSelected() ? "Rad":RadCbo.isSelected() ? "Cbo":RadBtn.isSelected() ? "Btn":
                                	RadSbo.isSelected() ? "Sbo":RadPop.isSelected() ? "Pop":RadCbx.isSelected() ? "Cbx":RadSwb.isSelected() ? "Swb":
                                		RadAuC.isSelected() ? "AutoComplete":RadDT.isSelected() ? "DT":RadTime.isSelected() ? "Time":""));
                                VecVisible.addElement(CbxDST.isSelected() ? "DST":CbxZone.isSelected() ? "Zone":CbxSysAic.isSelected() ? "SysAic":"");
                                VecVisible.addElement(Static.JaNein2(CbxStichtag.isSelected()));
                                
                                VecInvisible.addElement(new Integer(iAIC_Eigenschaft));
                                VecInvisible.addElement(new Integer(CboDatentyp.getSelectedAIC()));
                                VecInvisible.addElement(new Integer(CboStamm.getSelectedAIC()));
                                VecInvisible.addElement(new Integer(CboStt.getSelectedAIC()));
                                VecInvisible.addElement(new Integer(iBits));
                                VecInvisible.addElement(new Integer(CboEig.getSelectedAIC()));
                                VecInvisible.addElement(new Integer(CboAbfrage.getSelectedAIC()));
                                VecInvisible.addElement(new Integer(CboRolle.getSelectedAIC()));
                                VecInvisible.addElement(new Integer(CboBew.getSelectedAIC()));
                                if(NodeAkt==null)
                                {
                                        NodeAkt=new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutEigenschaft.getRootNode());
                                        NodeAkt.setUserData(VecInvisible);
                                        NodeAkt.setStyle(NodeStyle);
                                        Static.makeVisible(OutEigenschaft,NodeAkt);
                                }
                                else
                                {
                                        NodeAkt.setLabel(VecVisible);
                                        NodeAkt.setUserData(VecInvisible);
                                        LoadMemo(iAIC_Eigenschaft);
                                }
                                FrmNewEdt.dispose();
                                OutEigenschaft.folderChanged(OutEigenschaft.getRootNode());
                }
        });

        BtnNewEdtAbbruch.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                        FrmNewEdt.dispose();
                }
        });

        BtnNewEdtDatentyp.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                        if (new Message(Message.YES_NO_OPTION, null,g).showDialog("Datentyp_aendern", new String[] {CboDatentyp.getCombo().getBezeichnung(), ""}) == Message.YES_OPTION)
                        {
                          FrmNewEdt.setVisible(false);
                          Static.centerComponent(FrmDatentyp,thisFrame);
                          FrmDatentyp.setVisible(true);
                          String sDatentyp=CboDatentyp.getSelectedKennung();
                          BuildDetail(false,sDatentyp);
                        }
                }
        });


	JPanel Pnl_Eigenschaft = getFrei("Outliner Eigenschaft");
	JPanel Pnl_Memo = getFrei("Memo");

	if(Pnl_Eigenschaft!=null)
		Pnl_Eigenschaft.add("Center",OutEigenschaft);
	if(Pnl_Memo!=null)
		Pnl_Memo.add("Center",Memo);

	TxtBezeichnung=new Text("",200);
	TxtKennung=new Text("",98,Text.KENNUNG);
	TxtFormat=new Format(g,"Eigenschaft",thisFrame);
	TxtTitel=new Text("",40);

	TxtMemo=new AUTextArea(g,0);
  TxtMemo.setMaxLength(4000);
  TxtTooltip=new AUTextArea(g,0);
  TxtTooltip.setMaxLength(4000);
    BtnBild = new BildEingabe(thisJFrame(),g);
    BtnBild.activateEvent();
//    BtnBildFX = new BildEingabe(thisJFrame(),g);
//    BtnBildFX.activateEvent();
	ZahlFeldlaenge=new Zahl(0);
	ZahlBreite=new Zahl(0);
	ZahlMin=new Zahl(0);
	ZahlMax=new Zahl(0);

	CbxEindeutig=g.getCheckbox("Eindeutig");
	CbxAlways=g.getCheckbox("Always");
	CbxKeinAutoDate= g.getCheckbox("kein_vorfuellen");
	CbxNurModell=g.getCheckbox("nur Modell2");
        CbxRound100=g.getCheckbox("Centrunden");
        CbxDefault=g.getCheckbox("Default");
        CbxSysAic=g.getCheckbox("SysAic");
        CbxKeinCopy=g.getCheckbox("keine Kopie");
        //CbxEAneu=g.getCheckbox("Eintritt mit Rolle");
        CbxBerechtigbar=g.getCheckbox("berechtigbar2");
        CbxStichtag=g.getCheckbox("StichtagWeb");
        CbxKeinEigDate=g.getCheckbox("keinStichtag");
        CbxTakeBez=g.getCheckbox("take Bez");
	CbxJeder=g.getCheckbox("Jeder");
        CbxLizenz=g.getCheckbox("Lizenz");
        CbxTod=g.getCheckbox("Tod");
        //CbxGruppierbar=g.getCheckbox("Gruppierbar");
        RadNPkein=g.getRadiobutton("NPkein");
        RadNPhoch=g.getRadiobutton("NPhoch");
        RadNPzwang=g.getRadiobutton("NPzwang");
        RadNPklar=g.getRadiobutton("NPklar");
        ButtonGroup group = new ButtonGroup();
        group.add(RadNPkein);
        group.add(RadNPhoch);
        group.add(RadNPzwang);
        group.add(RadNPklar);
        
        RadAuto=g.getRadiobutton("Auto3");
        RadCho=g.getRadiobutton("Cho"); // ChoiceBox
        RadCbo=g.getRadiobutton("Cbo"); // ComboBox
        RadRad=g.getRadiobutton("Rad"); // Radiobuttons
        RadBtn=g.getRadiobutton("Btn");
        RadSbo=g.getRadiobutton("Sbo");
        RadPop=g.getRadiobutton("Pop"); // PopOver
        RadCbx=g.getRadiobutton("Cbx");
        RadSwb=g.getRadiobutton("Swb");
        RadAuC=g.getRadiobutton("AutoComplete");
        RadDT=g.getRadiobutton("DT");
        RadTime=g.getRadiobutton("Time");
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
        
        CbxZone=g.getCheckbox("Zone");
        CbxDST =g.getCheckbox("DST");
        CbxTake=g.getCheckbox("take");
        CbxWebLiz=g.getCheckbox("WebLizenz");
        CbxEigClean=g.getCheckbox("EigClean");

        FrmDatentyp=new JDialog((JFrame)thisFrame,true);
        FrmDatentyp.getContentPane().setLayout(new BorderLayout(2,2));
        FrmDatentyp.getContentPane().add("North",CboDatentyp);
        final JButton BtnOkDT=g.getButton("Ok");
        FrmDatentyp.getRootPane().setDefaultButton(BtnOkDT);
        FrmDatentyp.getContentPane().add("South",BtnOkDT);
        FrmDatentyp.setSize(200,300);

        ActionListener AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            Object Btn = ev.getSource();
            if (Btn == BtnOkDT)
              FrmDatentyp.setVisible(false);
            else if (Btn == BtnShow)
              showEig();
            else if (Btn == BtnRefresh)
              Load();
            else if (Btn == BtnNew)
              NewEig();
            else if (Btn == BtnEdt)
              EditEig();
            else if (Btn == BtnDel)
              DelEig();
            else if (Btn == BtnEnd)
            {
              SaveParameter();
              hide();
            }
            else if (Btn == BtnEinstellung)
              Einstellung();
          }
        };
        BtnOkDT.addActionListener(AL);
        BtnShow.addActionListener(AL);
        BtnRefresh.addActionListener(AL);
	BtnNew.addActionListener(AL);
	BtnEdt.addActionListener(AL);
	BtnDel.addActionListener(AL);
	BtnEnd.addActionListener(AL);
        BtnEinstellung.addActionListener(AL);

	/*CboDatentyp.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent ev)
		{
			if(ev.getStateChange()==ev.SELECTED)
                        {
                          TxtFormat.setTyp(CboDatentyp.getSelectedKennung());
                          EnableButtons();
                        }
		}
	});

	CboStt.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent ev)
		{
			if(ev.getStateChange()==ev.SELECTED)
				EnableButtons();
		}
	});*/

	/*CboStamm.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent ev)
		{
			if(ev.getStateChange()==ev.SELECTED)
				EnableButtons();
		}
	});*/

	OutEigenschaft.addItemListener(new JCItemListener()
	{
		public void itemStateChanged(JCItemEvent ev)
		{
			if(ev.getStateChange()==ItemEvent.SELECTED)
			{
				JCOutlinerNode Node=OutEigenschaft.getSelectedNode();
				LoadMemo(Node!=null && Node.getLevel()==1?((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue():0);
			}
		}
	});
}

private void addPopup()
{
  ActionListener AL=new ActionListener()
  {
    public void actionPerformed(ActionEvent ev)
    {
      String s = ev.getActionCommand();
      g.progInfo("DefEigenschaft.Command=" + s);
      if (s.equals("Neu"))
        NewEig();
      else if (s.equals("Edit"))
        EditEig();
      else if (s.equals("Loeschen"))
        DelEig();
      else if (s.equals("Bits"))
        ShowEigBits();
      else if (s.equals("show"))
        showEig();
      else if (s.equals("show Modelle"))
        showEigModelle();
      else if (s.equals("Einstellung"))
        Einstellung();
    }
  };
  g.addMenuItem("Edit",popup,null,AL);
  g.addMenuItem("Neu",popup,null,AL);
  g.addMenuItem("Loeschen",popup,null,AL);
  g.addMenuItem("Bits",popup,null,AL);
  g.addMenuItem("show",popup,null,AL);
  g.addMenuItem("show Modelle",popup,null,AL);
  g.addMenuItem("Einstellung",popup,null,AL);

  OutEigenschaft.getOutliner().addMouseListener(new MouseListener()
    {
      public void mousePressed(MouseEvent ev)
      {}

      public void mouseClicked(MouseEvent ev)
      {
        //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
        if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM) && g.Def())
          popup.show((java.awt.Component)ev.getSource(), ev.getX(), ev.getY());
      }

      public void mouseEntered(MouseEvent ev)
      {}

      public void mouseExited(MouseEvent ev)
      {}

      public void mouseReleased(MouseEvent ev)
      {}
  });
}

private void ShowEigBits()
{
  Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr","Sub", "Kennung","Bezeichnung","Anzahl"});
  addBit(Tab2, "cstEindeutig","Eindeutig",Global.cstEindeutig, 0);
  addBit(Tab2, "cstAlways","Always",Global.cstAlways, 1);
  addBit(Tab2, "cstNurModell","nur Modell2",Global.cstNurModell, 2);
  addBit(Tab2, "cstKeinAutoDate","kein_vorfuellen",Global.cstKeinAutoDate, 3);
  addBit(Tab2, "cstRound100","Centrunden",Global.cstRound100, 4);
  addBit(Tab2, "cstDefault","Default",Global.cstDefault, 5);
  
  addBitS(Tab2, "cstEigAuto","Auto",Global.cstEE,Global.cstEigAuto,8,1);
  addBitS(Tab2, "cstEigCho","Cho", Global.cstEE,Global.cstEigCho, 8,2);
  addBitS(Tab2, "cstEigRad","Rad",  Global.cstEE,Global.cstEigRad, 8,3);
  addBitS(Tab2, "cstEigBtn","Btn",  Global.cstEE,Global.cstEigBtn, 8,4);
  addBitS(Tab2, "cstEigSbo","Sbo",  Global.cstEE,Global.cstEigSbo, 8,5);
  addBitS(Tab2, "cstEigPop","Pop",  Global.cstEE,Global.cstEigPop, 8,6);
  addBitS(Tab2, "cstEigCbo","Cbo",  Global.cstEE,Global.cstEigCbo, 8,7);
  addBitS(Tab2, "cstEigCbx","Cbx",  Global.cstEE,Global.cstEigCbx, 8,9);
  addBitS(Tab2, "cstEigSwb","Swb",  Global.cstEE,Global.cstEigSwb, 8,10);
  addBitS(Tab2, "cstEigAuC","AutoComplete",  Global.cstEE,Global.cstEigAuC, 8,11);
  addBitS(Tab2, "cstEigDT","DT",  Global.cstEE,Global.cstEigDT, 8,12);
  addBitS(Tab2, "cstEigTime","Time",  Global.cstEE,Global.cstEigTime, 8,13);
  
  addBit(Tab2, "cstEigZone","Zone",Global.cstEigZone, 12);
  addBit(Tab2, "cstEigDST", "DST",Global.cstEigDST, 13);
  addBit(Tab2, "cstEigTake","take",Global.cstEigTake, 14);
  addBit(Tab2, "cstWebLizenz","WebLizenz",Global.cstWebLizenz, 15);
  addBit(Tab2, "cstEigClean","EigClean",Global.cstEigClean, 16);
  addBit(Tab2, "cstReserve1","",0x20000, 17);
  addBit(Tab2, "cstReserve2","",0x40000, 18);
  addBit(Tab2, "cstReserve3","",0x80000, 19);
  addBit(Tab2, "cstEigBrtg","berechtigbar2",Global.cstEigBrtg, 20);
  addBit(Tab2, "cstEigStichtag","StichtagWeb",Global.cstEigStichtag, 21);
  addBit(Tab2, "cstKeinEigDate","keinStichtag",Global.cstKeinEigDate, 22);
  addBit(Tab2, "cstEAneu","Eintritt mit Rolle",0x00800000/*cstEAneu*/, 23);
  addBitS(Tab2, "cstNPkein","NPkein",  Global.cstNP,Global.cstNPkein, 24,1);
  addBitS(Tab2, "cstNPklar","NPklar",  Global.cstNP,Global.cstNPklar, 24,2);
  addBitS(Tab2, "cstNPzwang","NPzwang",Global.cstNP,Global.cstNPzwang, 24,3);
  addBitS(Tab2, "cstNPhoch","NPhoch",  Global.cstNP,Global.cstNPhoch, 24,4);
  addBit(Tab2, "cstTakeBez","take Bez",Global.cstTakeBez, 26);
  addBit(Tab2, "cstSysAic","SysAic",Global.cstSysAic, 27);
  addBit(Tab2, "cstJeder","Jeder",Global.cstJeder, 28);
  addBit(Tab2, "cstEigLizenz","Lizenz",Global.cstEigLizenz, 29);
  addBit(Tab2, "cstKeinCopy","keine Kopie",Global.cstKeinCopy, 30);
  JDialog FomGid = new JDialog((JFrame)thisFrame, "Eigenschaftsbits", false);
    AUOutliner Grid = new AUOutliner();
    FomGid.getContentPane().add("Center", Grid);
    Tab2.showGrid(Grid);
    FomGid.setSize(500, 600);
    Static.centerComponent(FomGid,thisFrame);
    Grid.addActionListener(new JCActionListener() {
      public void actionPerformed(JCActionEvent ev) {
        JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
        int i=Sort.geti(Nod.getLabel(),0);
        int i2=Sort.geti((Vector)Nod.getLabel(),1);
        long l=0;
        long l2=0;
        if (i2>0)
        {
          if (i==8)
          {
        	  l=Global.cstEE;
        	  l2=i2==2 ? Global.cstEigCho:i2==3 ? Global.cstEigRad:i2==4 ? Global.cstEigBtn:i2==5 ? Global.cstEigSbo:i2==6 ? Global.cstEigPop:i2==7 ? Global.cstEigCbo:i2==9 ? Global.cstEigCbx:i2==10 ? Global.cstEigSwb:i2==11 ? Global.cstEigAuC:
        		  i2==12 ? Global.cstEigDT:i2==13 ? Global.cstEigTime:Global.cstEigAuto;
          }
          else if (i==24)
          {
            l=Global.cstNP;
            l2=i2==1 ? Global.cstNPkein:i2==2 ? Global.cstNPklar:i2==3 ? Global.cstNPzwang:Global.cstNPhoch;
          }

        }
        else
          l=(long)Math.pow(2,i);
        String s=(String)((Vector)Nod.getLabel()).elementAt(2);
        //long l=(long)Math.pow(2,i);
        Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_eigenschaft,kennung"+g.AU_Bezeichnung("eigenschaft")+" from eigenschaft where "+(i2>0 ? g.bits("bits",l)+"="+l2:g.bit("bits",l)),true);
        //if(Tab.FrmGrid != null)
        //  Tab.FrmGrid.dispose();
        Tab.showGrid("Abschnitt mit "+s+" (" + i+(i2>0?"/"+i2:"")+")", /*bModal ? FomGid :*/ FomGid);
        Tab.Grid.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
            if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
            {
              g.select(Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(),0),OutEigenschaft);
            }
          }
          public void mouseEntered(MouseEvent ev) {}
          public void mouseExited(MouseEvent ev) {}
          public void mouseReleased(MouseEvent ev) {}
        });

      }
    });
    FomGid.setVisible(true);

}

private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
{
  Tab2.addInhalt("Nr",i);
  Tab2.addInhalt("Sub",0);
  Tab2.addInhalt("Kennung",sConst);
  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from eigenschaft where "+g.bit("bits",l)));
}

private void addBitS(Tabellenspeicher Tab2,String sConst,String sBez,long l,long l2,int i,int iSub)
{
  Tab2.addInhalt("Nr",i);
  Tab2.addInhalt("Sub",iSub);
  Tab2.addInhalt("Kennung",sConst);
  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Radiobutton",sBez));
  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from eigenschaft where "+g.bits("bits",l)+"="+l2));
}

private void setCboStamm(String sDatentyp,int iStamm)
{
  CboStamm = sDatentyp.endsWith("Waehrung") ? CboMass3:sDatentyp.equals("BewVon_Bis") || sDatentyp.equals("CalcDauer") ? CboMass2:CboMass1;
  CboStamm.setSelectedAIC(iStamm);
}

private void BuildDetail(boolean bNeu,String sDT)
{
  FrmNewEdt=new JDialog((JFrame)thisFrame,true);
    FrmNewEdt.setTitle(g.getBegriffS("Show",bNeu ? "new":"edit")+" "+sDT);

        FrmNewEdt.getContentPane().setLayout(new BorderLayout(2,2));
        JPanel Pnl1a = new JPanel(new BorderLayout());
        JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
        //JPanel Pnl2a = new JPanel(new BorderLayout());
        JPanel Pnl2 = new JPanel(new GridLayout(0,1,2,2));
        //JPanel Pnl4 = new JPanel(new GridLayout(0,1,2,2));
        JPanel Pnl5 = new JPanel(new GridLayout(1,0,2,2));
        g.addLabel(Pnl,"  "+g.getBegriffS("Show","Bezeichnung"));
        g.addLabel(Pnl,"  "+g.getBegriffS("Show","Kennung"));
        //g.addLabel(Pnl,"  "+g.getBezeichnung("Begriffgruppe","Datentyp"));

        Pnl2.add(TxtBezeichnung);
        Pnl2.add(TxtKennung);
        //Pnl2.add(CboDatentyp);
        //CboDatentyp.setEnabled(bNeu);
        if (sDT.equals("BewVon_Bis") || sDT.endsWith("Mass") || sDT.equals("BewMass2") || sDT.equals("BewWaehrung2") || sDT.equals("CalcDauer") || sDT.endsWith("Waehrung"))
        {
          g.addLabel(Pnl,"  "+g.getBezeichnung("Tabellenname","STAMM"));
          Pnl2.add(CboStamm);
        }
        if (sDT.equals("Hierarchie")||sDT.equals("Gruppe")||sDT.equals("Mehrfach")||sDT.equals("BewStamm")||sDT.equals("BewHierarchie")
            ||sDT.equals("BewZahl2")||sDT.equals("BewMass2")||sDT.equals("BewWaehrung2"))
        {
          g.addLabel(Pnl,"  "+g.getBezeichnung("Tabellenname", "STAMMTYP"));
          Pnl2.add(CboStt);
        }
        g.addLabel(Pnl,"  "+g.getBezeichnung("Tabellenname","EIGENSCHAFT"));
        Pnl2.add(CboEig);
        if (sDT.equals("BewStamm") || sDT.equals("Gruppe") || sDT.equals("Eintritt") || sDT.equals("Austritt") || sDT.equals("Mandant"))
        {
          g.addLabel(Pnl, "  " + g.getBegriffS("Show", "Filter"));
          JPanel PnlFilter = new JPanel(new BorderLayout());
          PnlFilter.add("Center",CboAbfrage);
          JButton Btn=g.getButton("*");
          PnlFilter.add("East",Btn);
          Btn.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              new ShowAbfrage(g,CboAbfrage.getSelectedAIC(),Abfrage.cstAbfrage).showResult(FrmNewEdt);
            }
          });
          Pnl2.add(PnlFilter);
          g.addLabel(Pnl,"  "+g.getBezeichnung("Tabellenname","ROLLE"));
          Pnl2.add(CboRolle);
        }
        if (sDT.equals("BewBew"))
        {
          g.addLabel(Pnl,"  "+g.getBezeichnung("Tabellenname","BEWEGUNGSTYP"));
          Pnl2.add(CboBew);
        }
        g.addLabel(Pnl,"  "+g.getShow("Bild-Swing"));    
        Pnl2.add(BtnBild);
//        g.addLabel(Pnl,"  "+g.getShow("Bild-FX"));
//        Pnl2.add(BtnBildFX);
        g.addLabel(Pnl,"  "+g.getShow("Feldlaenge"));
        Pnl2.add(ZahlFeldlaenge);
        g.addLabel(Pnl,"  "+g.getShow("Breite"));
        Pnl2.add(ZahlBreite);
        if (TxtFormat.isEnabled())
        {
          g.addLabel(Pnl,"  "+g.getShow("Format"));
          Pnl2.add(TxtFormat);
        }
        //TxtFormat.setEnabled(sDT.equals("Datum")||sDT.equals("Double")||sDT.equals("von_bis")||sDT.equals("Mass")||sDT.equals("Eintritt")||
        //                     sDT.equals("Austritt")||sDT.equals("Waehrung")||sDT.equals("BenMass")||sDT.equals("Stichtag")||sDT.equals("Auto_von_bis")||
        //					 sDT.equals("BewWaehrung")||sDT.equals("BewDatum")||sDT.equals("BewVon_Bis")||sDT.equals("BewDatum2")||sDT.equals("BewZahl"));

        g.addLabel(Pnl,"  "+g.getBegriffS("Show", "min"));
        Pnl2.add(ZahlMin);
        g.addLabel(Pnl,"  "+g.getBegriffS("Show", "max"));
        Pnl2.add(ZahlMax);

        boolean bMinMax= (sDT.equals("Integer")||sDT.equals("Double")||sDT.equals("Einheiten")||sDT.equals("Mass")||sDT.equals("BenMass")||
                          sDT.equals("BewMass")||sDT.equals("BewWaehrung")||sDT.equals("BewVon_Bis")||sDT.equals("BewDauer")||sDT.equals("BewZahl"));
        ZahlMin.setEnabled(bMinMax);
        ZahlMax.setEnabled(bMinMax);
        Pnl.add(CbxEindeutig); // 1
        Pnl.add(CbxKeinAutoDate);     // 2
        Pnl.add(CbxDefault);   // 3
        Pnl.add(CbxKeinEigDate);//CbxKeinANR);
        Pnl.add(CbxZone); // 5
        Pnl.add(CbxTakeBez);   // 6
        //Pnl.add(CbxEAneu);
        Pnl.add(RadNPkein);
        
        Pnl.add(RadAuto);
        Pnl.add(RadCho);
        Pnl.add(RadSbo);

        Pnl.add(CbxStichtag);
        Pnl2.add(CbxAlways);   // 1
        
        JPanel PnlSub=new JPanel(new GridLayout(1,3));// 2
        PnlSub.add(CbxJeder);
        PnlSub.add(CbxLizenz);
        PnlSub.add(CbxWebLiz);
       Pnl2.add(PnlSub);
        PnlSub=new JPanel(new GridLayout(1,3)); // 3
        PnlSub.add(CbxRound100);
        PnlSub.add(new JLabel());
        PnlSub.add(CbxTod);
       Pnl2.add(PnlSub);
        PnlSub=new JPanel(new GridLayout(1,3)); // 4
        PnlSub.add(CbxNurModell);
        PnlSub.add(CbxKeinCopy);
        PnlSub.add(CbxEigClean);
       Pnl2.add(PnlSub);
        PnlSub=new JPanel(new GridLayout(1,3)); // 5
        PnlSub.add(CbxDST);
        PnlSub.add(CbxTake);
        PnlSub.add(new JLabel());
       Pnl2.add(PnlSub);
        Pnl2.add(CbxSysAic);   // 6
         PnlSub=new JPanel(new GridLayout(1,3));
         PnlSub.add(RadNPhoch);
         PnlSub.add(RadNPzwang);
         PnlSub.add(RadNPklar);
        Pnl2.add(PnlSub);
        PnlSub=new JPanel(new GridLayout(1,3));
         PnlSub.add(RadCbx);
         PnlSub.add(RadSwb);
         PnlSub.add(RadAuC);
        Pnl2.add(PnlSub);
        PnlSub=new JPanel(new GridLayout(1,3));
         PnlSub.add(RadCbo);
         PnlSub.add(RadRad);
         PnlSub.add(RadBtn);
        Pnl2.add(PnlSub);
        PnlSub=new JPanel(new GridLayout(1,3));
         PnlSub.add(RadPop);
         PnlSub.add(RadDT);
         PnlSub.add(RadTime);
        Pnl2.add(PnlSub);
        Pnl2.add(CbxBerechtigbar);
        JPanel Pnl3 = new JPanel(new BorderLayout(2,2));
        Pnl3.setPreferredSize(new java.awt.Dimension(300, 300));
        Pnl3.add("North",TxtTitel);
        Pnl3.add("Center",TxtMemo);
        JTabbedPane PnlMemo=new JTabbedPane();
        PnlMemo.add(g.getBegriffS("Show","Tooltip"),TxtTooltip);
        PnlMemo.add(g.getBegriffS("Show","Memo"),Pnl3);
        if (sDT.equals("BewBits") || sDT.equals("Bits"))
          Static.printError("Datentyp "+sDT+" ist noch nicht definierbar");
        if (sDT.equals("BewBool3") || sDT.equals("Bool3")/* || sDT.equals("BewBits") || sDT.equals("Bits")*/)
        {
          JPanel PnlAuswahl = new JPanel(new BorderLayout(2, 2));
          final JPanel PnlAW = new JPanel(new GridLayout(0, 1, 2, 2));
          JPanel PnlAE = new JPanel(new FlowLayout());
          g.addLabel(PnlAW, g.getBegriffS("Show", "undefiniert"));
          g.addLabel(PnlAW, Static.sJa + " (1)");
          g.addLabel(PnlAW, Static.sNein + " (2)");
          final JPanel PnlAC = new JPanel(new GridLayout(0, 1, 2, 2));
          VecAuswahl = new Vector<Text>();
          for (int i = 0; i < 3; i++)
          {
            VecAuswahl.addElement(new Text("", 20, Text.KENNUNG));
            PnlAC.add(VecAuswahl.elementAt(i));
          }
          JButton BtnAdd=g.getButton("Und");
          BtnAdd.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              checkAuswahl(PnlAW,PnlAC,VecAuswahl.size());
              /*int iAnz=VecAuswahl.size();
              g.addLabel(PnlAW, " ("+iAnz+")");
              VecAuswahl.addElement(new Text("", 20, Text.KENNUNG));
              PnlAC.add(VecAuswahl.elementAt(iAnz));*/
              //PnlAuswahl.addNotify();
              //PnlAuswahl.repaint();
            }
          });

          PnlAE.add(BtnAdd);
          fillAuswahl(PnlAW,PnlAC);
          PnlAuswahl.add("West", PnlAW);
          PnlAuswahl.add("Center", PnlAC);
          PnlAuswahl.add("East", PnlAE);
          PnlMemo.add(g.getBegriffS("Show", "Auswahl"), PnlAuswahl);     
        }
        else
          VecAuswahl=null;
        Pnl5.add(BtnNewEdtDatentyp);
        Pnl5.add(BtnNewEdtOk);
        Pnl5.add(BtnNewEdtAbbruch);
        Pnl1a.add("Center",Pnl2);
        Pnl1a.add("West",Pnl);
        //Pnl2a.add("North",Pnl2);
        //JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,Pnl2a,splitPane);
        FrmNewEdt.getContentPane().add("North",Pnl1a);
        FrmNewEdt.getContentPane().add("Center",PnlMemo);
        FrmNewEdt.getContentPane().add("South",Pnl5);
        FrmNewEdt.pack();

        Static.centerComponent(FrmNewEdt,thisFrame);
        FrmNewEdt.setVisible(true);
}

private void fillAuswahl(JPanel PnlAW,JPanel PnlAC)
      {
        if(NodeAkt!=null)
        {
          int iEig=Sort.geti(NodeAkt.getUserData(),0);
          Tabellenspeicher Tab = new Tabellenspeicher(g, "select Nr,Kennung from Auswahl where aic_eigenschaft=" + iEig, true);
          for (Tab.moveFirst();!Tab.out();Tab.moveNext())
          {
            checkAuswahl(PnlAW,PnlAC,Tab.getI("Nr"));
            Text Txt=VecAuswahl.elementAt(Tab.getI("Nr"));
            Txt.setText(Tab.getS("Kennung"));
          }
        }
      }

private void checkAuswahl(JPanel PnlAW,JPanel PnlAC,int iNr)
      {
        int iAnz = VecAuswahl.size();
        if (iNr>=iAnz)
        {
          g.addLabel(PnlAW, " (" + iAnz + ")");
          VecAuswahl.addElement(new Text("", 20, Text.KENNUNG));
          PnlAC.add(VecAuswahl.elementAt(iAnz));
          JPanel Pnl=(JPanel)PnlAC.getParent();
          if (Pnl!=null)
          {
            Pnl.revalidate();
            Pnl.repaint();
          }
        }
      }

private boolean DatentypCheck(int iEig)
{
	String sNeu=CboDatentyp.getSelectedKennung();
	String sAlt=CboDatentyp.getOldKennung();
	if (sNeu.equals(sAlt))
		return true;
	g.progInfo("Datentyp von "+sAlt+"->"+sNeu);
	boolean b=true;
	if (sNeu.equals("Stringx"))
	{
		if (sAlt.equals("StringSehrKurz"))
			g.exec("update stammpool set spalte_double=10 where aic_eigenschaft="+iEig);
		else if (sAlt.equals("StringKurz"))
			g.exec("update stammpool set spalte_double=30 where aic_eigenschaft="+iEig);
		else if (sAlt.equals("String60"))
			g.exec("update stammpool set spalte_double=60 where aic_eigenschaft="+iEig);
		else if (sAlt.equals("StringLang"))
			g.exec("update stammpool set spalte_double=255 where aic_eigenschaft="+iEig);
		else
			b=false;
	}
        else if(sNeu.startsWith("Calc") && sAlt.startsWith("Calc"))
          b=true;
	else
		b=false;
        if (!b)
          if (new Message(Message.YES_NO_OPTION, null,g).showDialog("Datentyp_aendern", new String[] {sAlt, sNeu}) == Message.YES_OPTION)
          {
            Static.printError("Datentyp von " + sAlt + " auf "+sNeu+" bei Eigenschaft "+TxtKennung.getText()+" geändert!");
          }
          else
          {
            //new Message(Message.WARNING_MESSAGE,null,g).showDialog("keineKonvertierung",new String[] {"<"+sAlt+"->"+sNeu+">"});
            CboDatentyp.setSelectedKennung(sAlt);
          }
	return b;
}

private void Load()
{
	CboAbfrage.fillCbo("select aic_abfrage,kennung,DefBezeichnung Bezeichnung from abfrage" + g.join("begriff", "abfrage") + " where" +g.bit("bits", All_Unlimited.Hauptmaske.Abfrage.cstFilter), "Abfrage", true);
    
  Vector Vec2=null;
  //if (PnlArt != null)
  //  g.progInfo("Load: "+CboArt.getSelectedBezeichnung()+"."+CboTyp.getSelectedBezeichnung());
  if (PnlArt != null && CboTyp.getSelectedAIC()>0)
  {
      String sZ= CboArt.getSelectedKennung().equals("BEWEGUNGSTYP") ? "BEW":CboArt.getSelectedKennung();
      Vec2=SQL.getVector("select aic_eigenschaft from "+sZ+"_zuordnung where aic_"+CboArt.getSelectedKennung()+"="+CboTyp.getSelectedAIC(),g);
      g.fixtestInfo("Vec2="+Vec2);
  }
        int iEig=OutEigenschaft.getSelectedNode()==null?0:Sort.geti(OutEigenschaft.getSelectedNode().getUserData(),0);
	((JCOutlinerFolderNode)OutEigenschaft.getRootNode()).removeChildren();
        SQL Qry = new SQL(g);
//        OutEigenschaft.setVisible(false);
        int iTab=g.TabTabellenname.getAic("EIGENSCHAFT");
        String sWhere="";
        if (CboDT != null && !CboDT.isNull())
        {
        	sWhere=" where aic_begriff="+CboDT.getSelectedAIC();
//        	g.fixtestError("Lade mit "+sWhere+"/"+OutEigenschaft.isVisible());
        }
	//if(Qry.open("select kennung"+g.AU_Bezeichnung("eigenschaft","")+",aic_eigenschaft,aic_stammtyp,aic_stamm,aic_begriff,feldlaenge,format,eindeutig,always,keinautodate,nur_modell,min,max,jeder from eigenschaft")&&!Qry.eof())
	if(Qry.open("select kennung"+g.AU_Bezeichnung("eigenschaft")+",aic_eigenschaft,aic_stammtyp,aic_stamm,(select bezeichnung from stammview where aic_stamm=eigenschaft.aic_stamm) BezStamm,"+
                    "aic_begriff,feldlaenge,breite,format,min,max,bits,eig_aic_eigenschaft,aic_abfrage,aic_rolle,aic_bewegungstyp,Tod,(select ein from logging where aic_logging=eigenschaft.aic_logging) Log"+
                    ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+iTab+" AND AUI.AIC_Fremd=AIC_eigenschaft AND AUI.AIC_Zustand="+Global.iSpSw+") Bild"+
//        			",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+iTab+" AND AUI.AIC_Fremd=AIC_eigenschaft AND AUI.AIC_Zustand="+Global.iSpFX+") BildFX"+
			" from eigenschaft"+sWhere/*+g.getWriteMandanten(true)*/)&&!Qry.eof())
	{
		for(;!Qry.eof();Qry.moveNext())
                  if ((Vec2==null || Vec2.contains(Qry.getInt("aic_eigenschaft"))) && (g.bLizenzFrei || g.existsEigenschaft(Qry.getInt("aic_eigenschaft"))))
		{	/*
			int iBits=0;
			iBits+=Qry.getB("eindeutig")?Global.cstEindeutig:0;
			iBits+=Qry.getB("always")?Global.cstAlways:0;
			iBits+=Qry.getB("KeinAutoDate")?Global.cstKeinAutoDate:0;
			iBits+=Qry.getB("nur_modell")?Global.cstNurModell:0;
			iBits+=Qry.getB("Jeder")?Global.cstJeder:0;
			*/
			int iBits=Qry.getI("bits");

			Vector<Comparable> VecVisible=new Vector<Comparable>();
			Vector<Integer> VecInvisible=new Vector<Integer>();
			VecVisible.addElement(Qry.getS("Bezeichnung"));
			VecVisible.addElement(Qry.getS("Kennung"));
			VecVisible.addElement(CboDatentyp.getBezeichnung(Qry.getI("aic_begriff")));
			//VecVisible.addElement(Qry.getI("aic_stamm")>0?CboStamm.getBezeichnung(Qry.getI("aic_stamm")):"");
            VecVisible.addElement(Qry.getS("BezStamm"));
			VecVisible.addElement(Qry.getI("aic_stammtyp")>0?CboStt.getBezeichnung(Qry.getI("aic_stammtyp")):"");
            VecVisible.addElement(Qry.getI("aic_Rolle")>0?CboRolle.Cbo.getBezeichnung(Qry.getI("aic_Rolle")):"");
			VecVisible.addElement(Qry.getInt("Feldlaenge"));
			VecVisible.addElement(Qry.getS("Format"));
			VecVisible.addElement(Qry.getInt("min"));
			VecVisible.addElement(Qry.getInt("max"));
			VecVisible.addElement(Static.JaNein2((iBits&Global.cstEindeutig)>0));
			VecVisible.addElement(Static.JaNein2((iBits&Global.cstAlways)>0));
			VecVisible.addElement(Static.JaNein2((iBits&Global.cstKeinAutoDate)>0));
			VecVisible.addElement(Qry.getI("eig_aic_eigenschaft")>0?CboEig.Cbo.getBezeichnung(Qry.getI("eig_aic_eigenschaft")):"");
			VecVisible.addElement(Static.JaNein2((iBits&Global.cstJeder)>0));
            VecVisible.addElement(Qry.getInt("aic_eigenschaft"));
            VecVisible.addElement(Static.JaNein2((iBits&Global.cstKeinEigDate)>0));
            VecVisible.addElement(Static.JaNein2((iBits&Global.cstRound100)>0));
            VecVisible.addElement(Static.JaNein2(Qry.getB("Tod")));
            int iNP=iBits & Global.cstNP;
            VecVisible.addElement(iNP==Global.cstNPkein ? "":g.getBegriffS("Radiobutton",iNP==Global.cstNPhoch ? "NPhoch":iNP==Global.cstNPzwang ? "NPzwang":iNP==Global.cstNPklar ? "NPklar":""));
            VecVisible.addElement(Qry.getD("Log"));
            VecVisible.addElement(Static.JaNein2((iBits&Global.cstEigLizenz)>0));
            VecVisible.addElement(Qry.getInt("Breite"));
            VecVisible.addElement(Qry.getS("Bild"));
            VecVisible.addElement(Static.JaNein2((iBits&Global.cstWebLizenz)>0)); //Qry.getS("BildFX"));
            VecVisible.addElement(Static.JaNein2((iBits&Global.cstEigBrtg)>0));
            VecVisible.addElement(Static.JaNein2((iBits&Global.cstKeinCopy)>0));
            VecVisible.addElement(Qry.getI("aic_Abfrage")==0 ? "":CboAbfrage.Cbo.getBezeichnung(Qry.getI("aic_Abfrage")));
            int iEA=iBits & Global.cstEE;
            VecVisible.addElement(iEA==Global.cstEigAuto ? "":g.getBegriffS("Radiobutton",iEA==Global.cstEigCho ? "Cho":iEA==Global.cstEigRad ? "Rad":iEA==Global.cstEigCbo ? "Cbo":iEA==Global.cstEigBtn ? "Btn":
            	iEA==Global.cstEigSbo ? "Sbo":iEA==Global.cstEigPop ? "Pop":iEA==Global.cstEigCbx ? "Cbx":iEA==Global.cstEigSwb ? "Swb":
            		iEA==Global.cstEigAuC ? "AutoComplete":iEA==Global.cstEigDT ? "DT":iEA==Global.cstEigTime ? "Time":""));
            VecVisible.addElement((iBits&Global.cstEigDST)>0 ? "DST":(iBits&Global.cstEigZone)>0 ? "Zone":(iBits&Global.cstSysAic)>0 ? "SysAic":"");
            VecVisible.addElement(Static.JaNein2((iBits&Global.cstEigStichtag)>0));
            
            VecInvisible.addElement(Qry.getInt("aic_eigenschaft"));
			VecInvisible.addElement(Qry.getInt("aic_begriff"));
			VecInvisible.addElement(new Integer(Qry.getI("aic_stamm")));
			VecInvisible.addElement(new Integer(Qry.getI("aic_stammtyp")));
			VecInvisible.addElement(new Integer(iBits));
            VecInvisible.addElement(new Integer(Qry.getI("eig_aic_eigenschaft")));
            VecInvisible.addElement(new Integer(Qry.getI("aic_Abfrage")));
            VecInvisible.addElement(new Integer(Qry.getI("aic_Rolle")));
            VecInvisible.addElement(new Integer(Qry.getI("aic_bewegungstyp")));

			JCOutlinerNode Node=new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutEigenschaft.getRootNode());
			Node.setUserData(VecInvisible);
			Node.setStyle(NodeStyle);
		}
		Qry.close();
                int iMom=1;
                for(int i=0;i<OutEigenschaft.getColumnLabels().length;i++)
                {
                  if ((iEinBits&iMom)==0 || TabEin != null)
                    OutEigenschaft.setColumnDisplayWidth(i,(iEinBits&iMom)>0 ? -999:0);
                  iMom*=2;
                }
	}
	Qry.free();
//        OutEigenschaft.setVisible(true);
        //OutEigenschaft.folderChanged(OutEigenschaft.getRootNode());
        OutEigenschaft.sortByColumn(0,Sort.sortMethod);
        OutEigenschaft.folderChanged(OutEigenschaft.getRootNode());
        pos(iEig);
}

/*private void EnableButtons()
{
	String sDT = CboDatentyp.getSelectedKennung();
	CboStt.setEnabled(sDT.equals("Hierarchie")||sDT.equals("Gruppe")||sDT.equals("Mehrfach")||sDT.equals("BewStamm")||sDT.equals("BewHierarchie"));
	//TxtFormat.setEnabled(sDT.equals("Datum")||sDT.equals("Double")||sDT.equals("von_bis")||sDT.equals("Mass")||sDT.equals("Eintritt")||
	//                     sDT.equals("Austritt")||sDT.equals("Waehrung")||sDT.equals("BenMass")||sDT.equals("Stichtag")||sDT.equals("Auto_von_bis")||
	//					 sDT.equals("BewWaehrung")||sDT.equals("BewDatum")||sDT.equals("BewVon_Bis")||sDT.equals("BewDatum2")||sDT.equals("BewZahl"));
	boolean bMinMax = sDT.equals("Integer")||sDT.equals("Double")||sDT.equals("Einheiten")||sDT.equals("Mass")||sDT.equals("BenMass")||
	                  sDT.equals("BewMass")||sDT.equals("BewWaehrung")||sDT.equals("BewVon_Bis")||sDT.equals("BewDauer")||sDT.equals("BewZahl");
	ZahlMin.setEnabled(bMinMax);
	ZahlMax.setEnabled(bMinMax);
	if(!CboStt.isEnabled())
		CboStt.setSelectedAIC(0);
	//if(!CboStamm.isEnabled())
	//	CboStamm.setSelectedAIC(0);
	if(!TxtFormat.isEnabled())
		TxtFormat.setText("");
	if(!bMinMax)
	{
		ZahlMin.setValue(null);
		ZahlMax.setValue(null);
	}

	//BtnNewEdtOk.setEnabled((!CboStt.isEnabled()||CboStt.getSelectedAIC()>0)&&(!CboStamm.isEnabled()||CboStamm.getSelectedAIC()>0));
}*/

private void LoadMemo(int iEig)
{
  //g.progInfo("LoadMemo:"+iEig);
	sAktTitel="";
	sAktMemo="";
        sAktTooltip="";
	if(iEig>0)
	{
		Vector Vec=g.getMemoVector("EIGENSCHAFT",iEig);
		sAktTitel=(String)Vec.elementAt(0);
		sAktMemo=(String)Vec.elementAt(1);
                int iPos=g.TabEigenschaften.getPos("Aic",iEig);
                if (iPos>=0)
                  sAktTooltip=g.TabEigenschaften.getM(iPos,"Tooltip");
	}
	Memo.setText((sAktTitel.equals("")?"":"Titel: "+sAktTitel+"\n")+"Memo: "+sAktMemo+"\n\nTooltip: "+sAktTooltip);
}

      private void NewEig()
      {
        NodeAkt=null;
        //g.progInfo("vor setVisible");
        Static.centerComponent(FrmDatentyp,thisFrame);
        FrmDatentyp.setVisible(true);
        //g.progInfo("nach setVisible");
        String sDatentyp=CboDatentyp.getSelectedKennung();
        setCboStamm(sDatentyp,0);
        CboStt.setSelectedAIC(0);
        CboAbfrage.setSelectedAIC(0);
        CboEig.setSelectedAIC(0);
        CboRolle.setSelectedAIC(0);
        CboBew.setSelectedAIC(0);
        TxtBezeichnung.setText("");
        TxtKennung.setEditable(true);
        TxtKennung.setText("");
        BtnBild.Delete();
//        BtnBildFX.Delete();
        ZahlFeldlaenge.setValue(null);
        ZahlBreite.setValue(null);
        TxtFormat.setTyp(sDatentyp,null,false);
        TxtFormat.setText("");
        ZahlMin.setValue(null);
        ZahlMax.setValue(null);
        CbxEindeutig.setSelected(false);
        CbxAlways.setSelected(false);
        CbxKeinAutoDate.setSelected(false);
        CbxNurModell.setSelected(false);
        CbxRound100.setSelected(false);
        CbxDefault.setSelected(false);
        CbxSysAic.setSelected(false);
        CbxKeinCopy.setSelected(false);
        //CbxEAneu.setSelected(false);
        CbxZone.setSelected(false);
        CbxDST.setSelected(false);
        CbxTake.setSelected(false);
        CbxWebLiz.setSelected(false);
        CbxEigClean.setSelected(false);
        CbxBerechtigbar.setSelected(false);
        CbxStichtag.setSelected(false);
        CbxKeinEigDate.setSelected(false);
        CbxTakeBez.setSelected(false);
        CbxJeder.setSelected(true);
        CbxLizenz.setSelected(false);
        CbxTod.setSelected(false);
        RadNPkein.setSelected(true);
        RadAuto.setSelected(true);
        //CbxGruppierbar.setSelected(false);
        TxtTitel.setText("");
        TxtMemo.setText("");
        TxtTooltip.setText("");
        CboAbfrage.Cbo.Clear();
        BuildDetail(true,sDatentyp);
        //EnableButtons();
      }
      
      private String getBildFile(int iAic,boolean bFX)
      {
    	  String s=SQL.getString(g, "select filename from daten_bild where aic_tabellenname="+g.TabTabellenname.getAic("EIGENSCHAFT")+" and aic_fremd="+iAic+" and aic_Zustand="+(bFX ? Global.iSpFX:Global.iSpSw));
    	  //g.fixtestInfo("getBildFile von "+iAic+"/"+bFX+"="+s);
    	  return s;
      }

      private void EditEig()
      {
        TxtKennung.setEditable(false);
        NodeAkt = OutEigenschaft.getSelectedNode();
        Vector VecVisible = (Vector)NodeAkt.getLabel();
        Vector VecInvisible = (Vector)NodeAkt.getUserData();

        CboAbfrage.fillCbo("select aic_abfrage,kennung,DefBezeichnung Bezeichnung from abfrage" + g.join("begriff", "abfrage") + " where" +
                               g.bit("bits", All_Unlimited.Hauptmaske.Abfrage.cstFilter) +
                               " and aic_stammtyp=" + ((Integer)VecInvisible.elementAt(3)).intValue(), "Abfrage", true);

        TxtBezeichnung.setText((String)VecVisible.elementAt(0));
        TxtKennung.setText((String)VecVisible.elementAt(1));
        CboDatentyp.setSelectedAIC(((Integer)VecInvisible.elementAt(1)).intValue());
        String sDatentyp = CboDatentyp.getSelectedKennung();
        setCboStamm(sDatentyp, ((Integer)VecInvisible.elementAt(2)).intValue());
        CboStt.setSelectedAIC(((Integer)VecInvisible.elementAt(3)).intValue());
        String sFile=getBildFile(Sort.geti(VecInvisible,0),false);
        Image Img=Static.Leer(sFile) ? null:g.LoadImage(sFile);
        if (Img==null)
          BtnBild.Delete();
        else
          BtnBild.setValue(Img,sFile,null);
//        sFile=getBildFile(Sort.geti(VecInvisible,0),true);
//        Img=Static.Leer(sFile) ? null:g.LoadImage(sFile);
//        if (Img==null)
//            BtnBildFX.Delete();
//          else
//        	BtnBildFX.setValue(Img,sFile,null);
        ZahlFeldlaenge.setValue((Integer)VecVisible.elementAt(6));
        ZahlBreite.setValue((Integer)VecVisible.elementAt(22));
        TxtFormat.setTyp(sDatentyp, null,false);
        TxtFormat.setText((String)VecVisible.elementAt(7));
        //g.progInfo("Typ="+CboDatentyp.getSelectedKennung()+", Format="+(String)VecVisible.elementAt(6));
        ZahlMin.setValue((Integer)VecVisible.elementAt(8));
        ZahlMax.setValue((Integer)VecVisible.elementAt(9));
        CboEig.setSelectedAIC(((Integer)VecInvisible.elementAt(5)).intValue());
        CboAbfrage.setSelectedAIC(((Integer)VecInvisible.elementAt(6)).intValue());
        CboRolle.setSelectedAIC(((Integer)VecInvisible.elementAt(7)).intValue());
        CboBew.setSelectedAIC(((Integer)VecInvisible.elementAt(8)).intValue());
        int iBit = ((Integer)VecInvisible.elementAt(4)).intValue();
        CbxEindeutig.setSelected((iBit & Global.cstEindeutig) > 0);
        CbxAlways.setSelected((iBit & Global.cstAlways) > 0);
        CbxKeinAutoDate.setSelected((iBit & Global.cstKeinAutoDate) > 0);
        CbxNurModell.setSelected((iBit & Global.cstNurModell) > 0);
        CbxRound100.setSelected((iBit & Global.cstRound100) > 0);
        CbxDefault.setSelected((iBit & Global.cstDefault) > 0);
        CbxSysAic.setSelected((iBit & Global.cstSysAic) > 0);
        CbxKeinCopy.setSelected((iBit&Global.cstKeinCopy)>0);
        //CbxEAneu.setSelected((iBit & Global.cstEAneu) > 0);
        CbxBerechtigbar.setSelected((iBit & Global.cstEigBrtg) > 0);
        CbxStichtag.setSelected((iBit & Global.cstEigStichtag) > 0);
        CbxKeinEigDate.setSelected((iBit & Global.cstKeinEigDate) > 0);
        CbxTakeBez.setSelected((iBit & Global.cstTakeBez) > 0);
        CbxJeder.setSelected((iBit & Global.cstJeder) > 0);
        CbxLizenz.setSelected((iBit & Global.cstEigLizenz) > 0);
        CbxTod.setSelected((Static.bX ? "x":Static.sJa).equals(Sort.gets(VecVisible,18)));
        //CbxTod.setSelected(SQL.exists(g,"select aic_eigenschaft from eigenschaft where tod=1 and aic_eigenschaft="+Sort.geti(VecInvisible,0)));
        //CbxGruppierbar.setSelected((iBit&Global.cstGruppierbar)>0);
        RadNPkein.setSelected((iBit & Global.cstNP) == Global.cstNPkein);
        RadNPhoch.setSelected((iBit & Global.cstNP) == Global.cstNPhoch);
        RadNPzwang.setSelected((iBit & Global.cstNP) == Global.cstNPzwang);
        RadNPklar.setSelected((iBit & Global.cstNP) == Global.cstNPklar);
        
        RadAuto.setSelected((iBit & Global.cstEE) == Global.cstEigAuto);
        RadCho.setSelected((iBit & Global.cstEE) == Global.cstEigCho);
        RadCbo.setSelected((iBit & Global.cstEE) == Global.cstEigCbo);
        RadRad.setSelected((iBit & Global.cstEE) == Global.cstEigRad);
        RadBtn.setSelected((iBit & Global.cstEE) == Global.cstEigBtn);
        RadSbo.setSelected((iBit & Global.cstEE) == Global.cstEigSbo);
        RadPop.setSelected((iBit & Global.cstEE) == Global.cstEigPop);
        RadCbx.setSelected((iBit & Global.cstEE) == Global.cstEigCbx);
        RadSwb.setSelected((iBit & Global.cstEE) == Global.cstEigSwb);
        RadAuC.setSelected((iBit & Global.cstEE) == Global.cstEigAuC);
        RadDT.setSelected((iBit & Global.cstEE) == Global.cstEigDT);
        RadTime.setSelected((iBit & Global.cstEE) == Global.cstEigTime);
        CbxZone.setSelected((iBit & Global.cstEigZone) > 0);
        CbxDST.setSelected((iBit & Global.cstEigDST) > 0);
        CbxTake.setSelected((iBit & Global.cstEigTake) > 0);
        CbxWebLiz.setSelected((iBit & Global.cstWebLizenz) > 0);
        CbxEigClean.setSelected((iBit & Global.cstEigClean) > 0);
        TxtTitel.setText(sAktTitel);
        TxtMemo.setText(sAktMemo);
        TxtTooltip.setText(sAktTooltip);
        BuildDetail(false, sDatentyp);
        //EnableButtons();
      }

      private void DelEig()
      {
        JCOutlinerNode Node = OutEigenschaft.getSelectedNode();
        CleanEig.get(g, Node != null && Node.getLevel() == 1 ? ((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue() : 0).setVisible(true);
      }

      private void showEig()
      {
        Vector Vec=(Vector)OutEigenschaft.getSelectedNode().getUserData();
        CleanEig.showInfo(Sort.geti(Vec,0),g,(JFrame)thisFrame,Sort.gets(OutEigenschaft.getSelectedNode().getLabel()));
      }

      private void addCbx(JPanel Pnl,String s,int i)
      {
        AUCheckBox Cbx=g.getCheckbox(s,(iEinBits&i)>0);
        Pnl.add(Cbx);
        TabEin.newLine();
        TabEin.setInhalt("Cbx",Cbx);
        TabEin.setInhalt("bit",i);
      }

      private void Einstellung()
      {
        final JDialog Dlg=new JDialog((JFrame)thisFrame, g.getBegriffS("Dialog","Einstellungen"));
        JPanel Pnl=new JPanel(new GridLayout(0,1,0,0));
        Dlg.getContentPane().add("Center", Pnl);
        if (TabEin==null)
          TabEin=new Tabellenspeicher(g,new String[] {"Cbx","bit"});
        else
          TabEin.clearAll();
        addCbx(Pnl,"Bezeichnung",BEZ);
        addCbx(Pnl,"Kennung",KEN);
        addCbx(Pnl,"Datentyp",DT);
        addCbx(Pnl,"Stammsatz",ST);
        addCbx(Pnl,"Stammtyp",STT);
        addCbx(Pnl,"Rolle",ROL);
        addCbx(Pnl,"Laenge",L);
        addCbx(Pnl,"Format",F);
        addCbx(Pnl,"min",MIN);
        addCbx(Pnl,"max",MAX);
        addCbx(Pnl,"Eindeutig",EIN);
        addCbx(Pnl,"Always",ZW);
        addCbx(Pnl,"kein_vorfuellen",KV);
        addCbx(Pnl,"Subeigenschaft",SUB);
        addCbx(Pnl,"Jeder",JED);
        addCbx(Pnl,"Aic",AIC);
        addCbx(Pnl,"keinStichtag",KS);
        addCbx(Pnl,"Centrunden",CR);
        addCbx(Pnl,"Tod",TOD);
        addCbx(Pnl,"NP",NP);
        addCbx(Pnl,"Change",LC);
        addCbx(Pnl,"Lizenz",LIZ);
        addCbx(Pnl,"Breite",BR);
        addCbx(Pnl,"Icon_Swing",ICSW);
        addCbx(Pnl,"WebLizenz",WLIZ); //"Icon_JavaFX",ICFX);
        addCbx(Pnl,"berechtigbar2",BER);
        addCbx(Pnl,"keine Kopie",KK);
        addCbx(Pnl,"FilterA",FLT);
        addCbx(Pnl,"EingabeArt",ART);
        addCbx(Pnl,"BitsE",BIT);
        addCbx(Pnl,"StichtagWeb",STW);
        JPanel PnlButton = new JPanel(new GridLayout(1, 2, 2, 2));
        final JButton BtnOk=g.getButton("Ok");
        JButton BtnAbbruch=g.getButton("Abbruch");
        ActionListener ALx=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            if (ev.getSource()==BtnOk)
            {
              //TabEin.showGrid("TabEin");
              iEinBits=0;
              for(TabEin.moveFirst();!TabEin.out();TabEin.moveNext())
              {
                if (((AUCheckBox)TabEin.getInhalt("Cbx")).isSelected())
                  iEinBits+=TabEin.getI("bit");
              }
            }
            Load();
            Dlg.setVisible(false);
          }
        };
        BtnOk.addActionListener(ALx);
        BtnAbbruch.addActionListener(ALx);
        PnlButton.add(BtnOk);
        PnlButton.add(BtnAbbruch);
        Dlg.getContentPane().add("South", PnlButton);
        Dlg.pack();
        Static.centerComponent(Dlg, thisFrame);
        Dlg.setVisible(true);
      }

      private void showEigModelle()
      {
        java.util.Vector Vec=(java.util.Vector)OutEigenschaft.getSelectedNode().getUserData();
        Tabellenspeicher Tab = new Tabellenspeicher(g,CleanEig.getModelle(Sort.geti(Vec,0),g),true);
        Tab.showGrid("Modelle von "+Sort.gets(OutEigenschaft.getSelectedNode().getLabel()), (JFrame)thisFrame);
        Tab.Grid.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
            if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
            {
              DefModell.get(g, Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(),0)).show();
              //g.select(Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(),0),OutEigenschaft);
            }
          }
          public void mouseEntered(MouseEvent ev) {}
          public void mouseExited(MouseEvent ev) {}
          public void mouseReleased(MouseEvent ev) {}
        });

      }

// add your data members here
private static final int BEZ=      1;
private static final int KEN=      2;
private static final int DT=       4;
private static final int ST=       8;
private static final int STT=   0x10;
private static final int ROL=   0x20;
private static final int L=     0x40;
private static final int F=     0x80;
private static final int MIN= 0x0100;
private static final int MAX= 0x0200;
private static final int EIN= 0x0400;
private static final int ZW=  0x0800;
private static final int KV=  0x1000;
private static final int SUB= 0x2000;
private static final int JED= 0x4000;
private static final int AIC= 0x8000;
private static final int KS= 0x10000;
private static final int CR= 0x20000;
private static final int TOD=0x40000;
private static final int NP =0x80000;
private static final int LC=    0x100000; // last change = Datum der letzen Änderung
private static final int LIZ=   0x200000; // Lizenz
private static final int BR=    0x400000; // Breite in Pixel
private static final int ICSW=  0x800000; // Bild (Icon) in Swing
private static final int WLIZ= 0x1000000; // Web-Lizent; war Bild in JavaFX
private static final int BER=  0x2000000; // Berechtigungs-bit
private static final int KK=   0x4000000; // keine Kopie
private static final int FLT=  0x8000000; // Filter-Abfrage
private static final int ART= 0x10000000; // Anzeige-Art
private static final int BIT= 0x20000000; // div. Bits
private static final int STW= 0x40000000; // Stichtag bei Web
private int iEinBits=BEZ+KEN+DT+ST+STT+ROL+AIC;
private int iEinBitsOld=-1;
private Global g;

private AUOutliner OutEigenschaft=new AUOutliner(new JCOutlinerFolderNode(""));
private JCOutlinerNodeStyle NodeStyle;
private Tabellenspeicher TabEin=null;

private JPanel PnlArt=null;
private JButton BtnShow;
private JButton BtnRefresh;
private JButton BtnNew;
private JButton BtnDel;
private JButton BtnEdt;
private JButton BtnEnd;
private JButton BtnNewEdtOk;
private JButton BtnNewEdtAbbruch;
private JButton BtnNewEdtDatentyp;
private JButton BtnEinstellung;

private AUTextArea Memo;

private Text TxtBezeichnung;
private Text TxtKennung;
private Format TxtFormat;

private Zahl ZahlFeldlaenge;
private Zahl ZahlBreite;
private Zahl ZahlMin;
private Zahl ZahlMax;

private JCheckBox CbxEindeutig;
private JCheckBox CbxAlways;
private JCheckBox CbxKeinAutoDate;
private JCheckBox CbxNurModell;
private JCheckBox CbxRound100;
private JCheckBox CbxDefault;
private JCheckBox CbxTakeBez;
private JCheckBox CbxSysAic;
private JCheckBox CbxKeinEigDate;
private JCheckBox CbxBerechtigbar;
private JCheckBox CbxStichtag;
private JCheckBox CbxKeinCopy;
//private JCheckBox CbxEAneu;
private JCheckBox CbxJeder;
private JCheckBox CbxTod;
private JCheckBox CbxLizenz;

private JCheckBox CbxZone;
private JCheckBox CbxDST;
private JCheckBox CbxTake;
private JCheckBox CbxWebLiz;
private JCheckBox CbxEigClean;
//private JCheckBox CbxGruppierbar;
private JRadioButton RadNPkein;
private JRadioButton RadNPhoch;
private JRadioButton RadNPzwang;
private JRadioButton RadNPklar;

private JRadioButton RadAuto;
private JRadioButton RadCho; // ChoiceBox
private JRadioButton RadCbo; // ComboBox
private JRadioButton RadRad; // RadioButton
private JRadioButton RadBtn; // ToggleButton
private JRadioButton RadSbo; // SpinBox
private JRadioButton RadPop;
private JRadioButton RadCbx;
private JRadioButton RadSwb;
private JRadioButton RadAuC;
private JRadioButton RadDT;
private JRadioButton RadTime;

private ComboSort CboArt;
private ComboSort CboTyp;
private ComboSort CboDT;
private Text EdtSuche;

private ComboSort CboStt;
private ComboSort CboStamm;
private ComboSort CboMass1;
private ComboSort CboMass2;
private ComboSort CboMass3;

private ComboSort CboDatentyp;
private EigenschaftsEingabe CboEig;
private RolleEingabe CboRolle;
private ComboSort CboBew;
private AbfrageEingabe CboAbfrage;

private Text TxtTitel;
private AUTextArea TxtMemo;
private AUTextArea TxtTooltip;
private Vector<Text> VecAuswahl;

//private String Ja;
//private String Nein;
private JDialog FrmNewEdt;
private JDialog FrmDatentyp;

private JCOutlinerNode NodeAkt=null;
private String sAktTitel="";
private String sAktMemo="";
private String sAktTooltip="";
private JPopupMenu popup= new JPopupMenu();
private BildEingabe BtnBild;
//private BildEingabe BtnBildFX;
}

