/*
    All_Unlimited-Print-Druckdefinition.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Print;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Clock;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;
import All_Unlimited.Allgemein.Eingabe.ModellEingabe;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Schrift;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Hauptmaske.Abfrage;
//import javax.swing.JMenuItem;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.GroupBox;
import All_Unlimited.Allgemein.Eingabe.RolleEingabe;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;

import javax.swing.JTabbedPane;

public class Druckdefinition extends All_Unlimited.Allgemein.Formular
{
	private static Druckdefinition This=null;
	private boolean bBildOld=false;
	
  public static Druckdefinition get(Global g,boolean bDruckFrage,int riDruck,int riStt)
  {
    if (g.Clock2()) clock=new Clock(g,null);
    long lClock = Clock.startClock(clock);
//    Druckdefinition d = null;
//    int iPos=g.TabFormulare.getPos("Aic", g.getBegriffAicS("Frame", "Druckdefinition"));
//    if (iPos>=0)
    if (This != null)
    {
//      d = (Druckdefinition)g.TabFormulare.getInhalt("Formular",iPos);
      This.iDruck = riDruck;
      This.iStt = riStt;
      Clock.add(clock,"Formular","",lClock);
      This.show();
    }
    else
      This = new Druckdefinition(g, bDruckFrage, riDruck, riStt);
    //g.clockInfo("Druckdefinition "+riStt,lClock);
    Clock.showClock(g,clock,"Druckdefinition "+riStt, lClock);
    return This;
  }
  
  public static void free()
  {
    if (This != null)
    {
      This.g.winInfo("Druckdefinition.free");
      This.thisFrame.dispose();
      This = null;
    }
  }

private Druckdefinition(Global glob,boolean bDruckFrage,int riDruck,int riStt)
{
	super("Druckdefinition",null,glob);
	g=glob;
	g.winInfo("Druckdefinition.create");
	This=this;
        iDruck=riDruck;
        iStt=riStt;
        if (bDruckFrage)
          g.putTabFormulare(getBegriff()/*g.getBegriffAic("Frame","Druckdefinition")*/,0,this);

	Build();

      AL=new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          String s = ev.getActionCommand();
          g.progInfo("Action=" + s);
          if (s.equals("Speichern"))
            Save();
          else if (s.equals("Beenden"))
            hide();
          else if (s.equals("new print"))
            NeuerDruck();
          else if (s.equals("copy print"))
            EditDruck(true);
          else if (s.equals("edit print"))
          {
            int iLevel=OutDruck.getSelectedNode().getLevel();
            if (iLevel > 1)
              EditAbschnitt(false);
            else
              EditDruck(false);
          }
          else if (s.equals("delete print"))
          {
            int iLevel = OutDruck.getSelectedNode().getLevel();
            if (iLevel > 1)
              Entfernen();
            else
              DeleteDruck();
          }
          else if (s.equals("new segment"))
            NeuerAbschnitt();
          else if (s.equals("edit segment"))
            EditAbschnitt(false);
          else if (s.equals("delete segment"))
            DeleteSegment();
          else if (s.equals("copy segment"))
            EditAbschnitt(true);
          else if (s.equals(">"))
            Hinzufuegen();
          else if (s.equals("Ersetzen"))
            Ersetzen();
          else if (s.equals("<"))
            Entfernen();
          else if (s.equals("Rauf"))
            Rauf_Runter(true);
          else if (s.equals("Runter"))
            Rauf_Runter(false);
          else if (s.equals("Seperator"))
          {
            JCOutlinerNode[] NodesD = OutDruck.getSelectedNodes();
            for(int j=0;j<NodesD.length;j++)
              Seperator((JCOutlinerFolderNode)NodesD[j],true);
            OutDruck.folderChanged(OutDruck.getRootNode());
          }
          else if (s.equals("DruckAbbruch"))
          {
        	  hideDlg(FrmDruck);
          }
          else if (s.equals("AbschnittAbbruch"))
          {
        	  hideDlg(FrmAbschnitt);
          }
          else if (s.equals("Abfrage") || s.equals("Abfrage2"))
            DefAbfrageAufrufen(s.equals("Abfrage"));
          else if (s.equals("Refresh") || s.equals("alle Drucke"))
          {
            long lClock = Clock.startClock(clock);
            if (s.equals("alle Drucke"))
              iStt=0;
            FuelleOutliner();
            Clock.showClock(g,clock,"FuelleOutliner ", lClock);
          }
          else if (s.equals("show"))
            showInfo();
          else if (s.equals("DruckBits"))
            ShowDruckBits();
          else if (s.equals("AbschnittBits"))
            ShowAbschnittBits();
          else if (s.equals("Change"))
            g.progInfo("Änderungen:"+Sort.gets2(VecAenderung,';'));
          else if (s.equals("RefreshDB"))
          	g.sendWebDB("refreshDB",null,thisJFrame());
        }
      };
      g.BtnAdd(BtnAlleDrucke,"alle Drucke",AL);
      g.BtnAdd(BtnSpeichern,"Speichern",AL);
      g.BtnAdd(getButton("Beenden"),"Beenden",AL);
      JButton BtnNeu=getButton("new print");
      if (BtnNeu != null && !g.Def()) BtnNeu.setEnabled(false);
      g.BtnAdd(BtnNeu,"new print",AL);
      g.BtnAdd(getButton("copy"),"copy print",AL);
      g.BtnAdd(BtnEditPrint,"edit print",AL);
      g.BtnAdd(BtnDeletePrint,"delete print",AL);
      BtnNeu=getButton("new segment");
      if (BtnNeu != null && !g.Def()) BtnNeu.setEnabled(false);
      g.BtnAdd(BtnNeu,"new segment",AL);
      g.BtnAdd(BtnEditSegment,"edit segment",AL);
      g.BtnAdd(BtnDeleteSegment,"delete segment",AL);
      g.BtnAdd(BtnCopySegment,"copy segment",AL);
      g.BtnAdd(BtnHinzufuegen,">",AL);
      g.BtnAdd(BtnErsetzen,"Ersetzen",AL);
      g.BtnAdd(BtnEntfernen,"<",AL);
      g.BtnAdd(BtnRauf,"Rauf",AL);
      g.BtnAdd(BtnRunter,"Runter",AL);
      g.BtnAdd(BtnSeperator,"Seperator",AL);
      g.BtnAdd(getButton("RefreshDB"),"RefreshDB",AL);

      BtnDruckOk.addActionListener(Action_BtnDruckOk);
      g.BtnAdd(BtnDruckAbbruch,"DruckAbbruch",AL);

	BtnAbschnittOk.addActionListener(new ActionListener()
	{
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e)
		{
                  /*if (TxtAbschnittKennung.isNull())
                  {
                    new Message(Message.ERROR_MESSAGE, FrmDruck, g,10).showDialog("KennungLeer");
                    return;
                  }*/

			JCOutlinerNode NodeAbschnitt = !bNeu?OutAbschnitt.getSelectedNodes()[0]:null;
			int iAIC_Abschnitt=!bNeu?((Integer)((Vector)NodeAbschnitt.getUserData()).elementAt(0)).intValue():0;
                        if(SQL.exists(g,"select aic_abschnitt from  abschnitt WHERE aic_abschnitt<> "+iAIC_Abschnitt+" and Kennung='"+TxtAbschnittKennung.getText()+"'"))
                        {
                          new Message(Message.WARNING_MESSAGE, FrmAbschnitt, g,10).showDialog("KennungVorhanden");
                          return;
                        }
                        hideDlg(FrmAbschnitt);
                        SQL Qry = new SQL(g);

				Qry.add("Kennung",TxtAbschnittKennung.getText());
                Qry.add("Kennzeichen",TxtAbschnittKennzeichen.getText());
				Qry.add0("AIC_Begriff",CboAbfragen.getSelectedAIC());
				//Qry.add0("AIC_Schrift",CboSchriftTitel.getSelectedAIC());
				//Qry.add0("Sch_AIC_Schrift",CboSchriftText.getSelectedAIC());
				Qry.add0("AIC_Raster",CboVorlagen.getSelectedAIC());
                Qry.add("Zahl",EdtZahl.doubleValue());
				int iBits = (CbxUeberschrift.isSelected()?Drucken.cstUeberschrift:0)+/*(CbxTitelDarunter.isSelected()?Drucken.cstTitelDarunter:0)+*/
					//(CbxErsteSpalteFett.isSelected()?Drucken.cstErsteFett:0)+(CbxEinzeldaten.isSelected()?Drucken.cstEigZeilen:0)+
                                        //(CbxKeinTitel.isSelected()?Drucken.cstKeinTitel:0)+(CbxUeberschriftLinks.isSelected()?Drucken.cstUeberschriftLinks:0)+
                                        (RadRechts.isSelected()?Drucken.cstRechts1:RadNebeneinander.isSelected()?Drucken.cstNeben:RadRechts2.isSelected()?Drucken.cstRechts2:Drucken.cstLinks)+
                                        (RadDiagramm.isSelected()?Drucken.cstDiagramm:RadIstUeberschrift.isSelected()?Drucken.cstIstUeberschrift:RadBedingung.isSelected()?Drucken.cstBedingung:
                                         RadEMail_A.isSelected()?Drucken.cstEMail_A:RadEMail_D.isSelected()?Drucken.cstEMail_D:RadEMail_S.isSelected()?Drucken.cstEMail_S:
                                        RadFixPos.isSelected()?Drucken.cstFixPos:RadSchicht2.isSelected()?Drucken.cstSchicht2:RadSchichtTMZ.isSelected()?Drucken.cstSchichtTMZ:
                                        RadHtmlDirekt.isSelected()? Drucken.cstHtmlDirekt:RadPDF_Save.isSelected() ? Drucken.cstPDF_Save:RadPDF_Direkt.isSelected() ? Drucken.cstPDF_Direkt:Drucken.cstNormal)+(CbxLtAic.isSelected()?Drucken.cstLtAic:0)+
                                        (CbxGesamtsumme.isSelected()?Drucken.cstGesamtsumme:0)+(CbxNurEMail.isSelected()?Drucken.cstNurEMail:0)+(CbxKeineHS.isSelected()?Drucken.cstkeineHauptsumme:0)+(CbxFixRaster.isSelected()?Drucken.cstFixRaster:0)+
                                        (CbxKeinAbstand.isSelected()?Drucken.cstKeinAbstand:0)+(CbxKeineSumme.isSelected()?Drucken.cstKeineSumme:0)+(CbxGruppenRed.isSelected()?Drucken.cstGruppenRed:0)+
                                        (CbxStrecken.isSelected()?Drucken.cstStrecken:0)+(CbxNichtDrucken.isSelected()?Drucken.cstNichtDrucken:0)+(CbxImmer.isSelected()?Drucken.cstImmer:0)+
                                        (CbxBlocksatz.isSelected()?Drucken.cstBlocksatz:0)+(CbxLeere.isSelected()?Drucken.cstLeere:0)+
                                        (CbxBedST.isSelected()?Drucken.cstBedST:0)+(CbxTest.isSelected()?Drucken.cstTest:0)+(CbxNeueSeite.isSelected()?Drucken.cstNeueSeite:0)+
                                        (CbxNurSummeA.isSelected()?Drucken.cstNurSummeA:0)+(CbxTestfill.isSelected()?Drucken.cstTestfill:0)+(CbxNurMitTitel.isSelected()?Drucken.cstNurMitTitel:0)+
                                        (CbxColor.isSelected()?Drucken.cstColor:0);
				Qry.add("bits",iBits);
				if(bNeu)
					iAIC_Abschnitt=Qry.insert("Abschnitt",true);
				else
					Qry.update("Abschnitt",iAIC_Abschnitt);

				int iTabA=g.TabTabellenname.getAic("ABSCHNITT");
				g.setBezeichnung(bNeu?"":(String)((Vector)NodeAbschnitt.getLabel()).elementAt(0),TxtAbschnittBezeichnung.getText(),iTabA,iAIC_Abschnitt,0);
                if (MemoAbschnitt.Modified())
                  g.setMemo(MemoAbschnitt.getValue(),"Abschnitt-Titel",iTabA,iAIC_Abschnitt,0);
                if (BtnBildAbschnitt.Modified())
                {
                	String sFile=BtnBildAbschnitt.getFilename();
                	if (sFile.startsWith("file:"))
              		  sFile=sFile.substring(sFile.charAt(5)==File.separator.charAt(0) ? 5:6);
                	g.fixtestError("Bild verändert auf: "+sFile);
              	  	g.setImage(bBildOld ? sFile:sFile.substring(sFile.lastIndexOf(File.separator)+1),iTabA,iAIC_Abschnitt,0);
                	if (!bBildOld)
                	  g.UpdateMini(sFile, "update daten_bild set bild=? where aic_tabellenname="+g.TabTabellenname.getAic("ABSCHNITT")+" and aic_fremd="+iAIC_Abschnitt);
                }

                jclass.util.JCVector VecVisible = new jclass.util.JCVector();
				Vector<Comparable> VecInvisible = new Vector<Comparable>();

				VecVisible.addElement(Static.beiLeer(TxtAbschnittBezeichnung.getText(),TxtAbschnittKennung.getText()));
				VecVisible.addElement(TxtAbschnittKennung.getText());
                                VecVisible.addElement(TxtAbschnittKennzeichen.getText());
				VecVisible.addElement(new Integer(iAIC_Abschnitt));
                                VecVisible.addElement(Static.Int0);
                                int iPos=CboAbfragen.getSelectedAIC()>0 ? g.TabBegriffe.getPos("Aic",CboAbfragen.getSelectedAIC()):-2;
                                VecVisible.addElement(iPos<0 ?"":g.getBegBez2(iPos));
                                //VecVisible.addElement(CboSchriftTitel.getSelectedBezeichnung());
				//VecVisible.addElement(CboSchriftText.getSelectedBezeichnung());
				VecVisible.addElement(CboVorlagen.getSelectedBezeichnung());
                                VecVisible.addElement(iPos<0 ?"":g.TabBegriffe.getI(iPos,"Erf")>0 ? "Bew "+g.TabErfassungstypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Erf")):
                                              g.TabBegriffe.getI(iPos,"Stt")==0 ? Static.sKein:"Stt "+g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt")));
				/*VecVisible.addElement(CbxUeberschrift.isSelected()?Static.sJa:Static.sNein);
				VecVisible.addElement(CbxUnterschrift.isSelected()?Static.sJa:Static.sNein);
				VecVisible.addElement(CbxTitelDarunter.isSelected()?Static.sJa:Static.sNein);
				VecVisible.addElement(CbxErsteSpalteFett.isSelected()?Static.sJa:Static.sNein);
				VecVisible.addElement(CbxEinzeldaten.isSelected()?Static.sJa:Static.sNein);*/
				VecInvisible.addElement(new Integer(iAIC_Abschnitt));
				VecInvisible.addElement(new Integer(CboAbfragen.getSelectedAIC()));
				VecInvisible.addElement(new Double(EdtZahl.doubleValue()));//new Integer(CboSchriftTitel.getSelectedAIC()));
				VecInvisible.addElement(Static.Int0);//new Integer(CboSchriftText.getSelectedAIC()));
				VecInvisible.addElement(new Integer(iBits));
				VecInvisible.addElement(new Integer(CboVorlagen.getSelectedAIC()));
                                VecInvisible.addElement(MemoAbschnitt.getValue());
                                VecInvisible.addElement(BtnBildAbschnitt.getFilename());

				if(NodeAbschnitt==null)
					NodeAbschnitt = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutAbschnitt.getRootNode());
				else
					NodeAbschnitt.setLabel(VecVisible);
				NodeAbschnitt.setUserData(VecInvisible);

				OutAbschnitt.selectNode(NodeAbschnitt,null);

				if(!bNeu)
					for(JCOutlinerNode Node = OutDruck.getNextNode(OutDruck.getRootNode());Node!=null;Node=OutDruck.getNextNode(Node))
						if(Node.getLevel()==2 && ((Integer)Node.getUserData()).intValue()==iAIC_Abschnitt)
						{
							VecVisible=new jclass.util.JCVector(VecVisible);
							VecVisible.removeElementsAt(2,8);

							Node.setLabel(VecVisible);
							Node.setUserData(new Integer(iAIC_Abschnitt));
						}

			Qry.free();

			OutAbschnitt.folderChanged(OutAbschnitt.getRootNode());

		}
	});

        g.BtnAdd(BtnAbschnittAbbruch,"AbschnittAbbruch",AL);
        g.BtnAdd(getFormularButton("Abfrage"),"Abfrage",AL);
        g.BtnAdd(getButton("Refresh"),"Refresh",AL);
        g.BtnAdd(getButton("show"),"show",AL);

        addPopup();
        addPopupD();
        show();
        OutAbschnitt.folderChanged(OutAbschnitt.getRootNode());
        OutDruck.folderChanged(OutDruck.getRootNode());
        OutAbschnitt.setColumnLabelSortMethod(Sort.sortMethod);
        OutDruck.setColumnLabelSortMethod(Sort.sortMethod);

	OutDruck.addItemListener(new JCOutlinerListener()
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
                  JCOutlinerNode Node=OutDruck.getSelectedNode();
                  if (Node.getLevel()==2)
                  {
                    g.select(Sort.geti(Node.getUserData()),OutAbschnitt);
                    /*int iAic=((Integer)Node.getUserData()).intValue();
                    //g.progInfo("suche"+iAic);
                    Vector Vec=OutAbschnitt.getRootNode().getChildren();
                    for (int i=0;i<Vec.size();i++)
                    {
                      JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
                      if (((Integer)((Vector)Nod.getUserData()).elementAt(0)).intValue()==iAic)
                        OutAbschnitt.selectNode(Nod,null);
                    }*/
                  }
                  EnableButtons();
		}
	});

	OutAbschnitt.addItemListener(new JCOutlinerListener()
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

        KeyListener KL=new KeyListener()
        {
                public void keyPressed(KeyEvent e)
                {}
                public void keyReleased(KeyEvent e)
                {
                        EnableButtons();
                }
                public void keyTyped(KeyEvent e)
                {}
        };
	TxtDruckKennung.addKeyListener(KL);
	TxtAbschnittKennung.addKeyListener(KL);
        TxtAbschnittKennzeichen.addKeyListener(KL);

	EnableButtons();

}

/*private void selectAbschnitt(int iAic)
{
  Vector Vec=OutAbschnitt.getRootNode().getChildren();
  for (int i=0;i<Vec.size();i++)
  {
    JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
    if (((Integer)((Vector)Nod.getUserData()).elementAt(0)).intValue()==iAic)
      Static.makeVisible(null,Nod);
  }
}

private static void select(int iAic,AUOutliner Out)
{
  Vector Vec=Out.getRootNode().getChildren();
  for (int i=0;i<Vec.size();i++)
  {
    JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
    if (((Integer)((Vector)Nod.getUserData()).elementAt(0)).intValue()==iAic)
      Static.makeVisible(null,Nod);
  }
}*/

private void DefAbfrageAufrufen(boolean b1)
{
  JCOutlinerNode Node = OutAbschnitt.getSelectedNodes()[0];
  Vector VecInvisible = (Vector)Node.getUserData();
  if (VecInvisible != null)
  {
    int iBegriff = ((Integer)VecInvisible.elementAt(1)).intValue();
    int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iBegriff);
    All_Unlimited.Grunddefinition.DefAbfrage.get(g, new All_Unlimited.Hauptmaske.ShowAbfrage(g, iBegriff, Abfrage.cstBegriff), iStt,null,false,b1 ? 0:1).show(false);
  }
        //Abfrage.Zeigen(g,RadAbfrage.isSelected()?CboAbfrage.getSelectedAIC():CboAbschnitt.getSelectedAIC(),(JFrame)thisFrame);

        //fillComboBoxen(false,true,false);
}

private void DeleteSegment()
{
  JCOutlinerNode[] Nodes = OutAbschnitt.getSelectedNodes();
  //String sSelected = "";
  //for(int i=0;i<Nodes.length;i++)
//	sSelected+=(sSelected.equals("")?"":",")+((String)((Vector)Nodes[i].getLabel()).elementAt(0));
  //int iMessage = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",new String[]{"<"+sSelected+">"});
  //if(iMessage==Message.YES_OPTION)
  {

	for(int i=0;i<Nodes.length;i++)
	{
		int iAIC_Abschnitt = ((Integer)((Vector)Nodes[i].getUserData()).elementAt(0)).intValue();
                SQL Qry=new SQL(g);
                String s2=Qry.list("defbezeichnung","begriff"+g.join2("druck_zuordnung","begriff")+" where aic_abschnitt="+iAIC_Abschnitt);
                //String s=(s2.equals(" ")?"":"\nFormulare:"+s2);
                if(!s2.equals(" "))
                  //new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Trotzdem_Loeschen", new String[] {"Abschnitt "+(String)((Vector)Nodes[i].getLabel()).elementAt(0),"\nDruck:" + s2})==Message.YES_OPTION)
                  new Message(Message.WARNING_MESSAGE,null,g).showDialog("BereitsVerwendet",new String[] {(s2.equals(" ")?"":"\nDruck:"+s2)});
                else if(new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",new String[]{Sort.gets(Nodes[i].getLabel(),0)})==Message.YES_OPTION)
                {

                  //g.progInfo("AIC_Tabellenspeicher: "+g.TabTabellenname.getAic("ABSCHNITT"));
                  //Qry.exec("delete from druck_zuordnung where aic_abschnitt="+iAIC_Abschnitt);
                  Qry.deleteFrom("abschnitt","abschnitt where aic_abschnitt="+iAIC_Abschnitt,g.TabTabellenname.getAic("ABSCHNITT"));
                  Nodes[i].getParent().removeChild(Nodes[i]);

                  Vector<JCOutlinerNode> VecRemoveFromDruck = new Vector<JCOutlinerNode>();
                  for(JCOutlinerNode Node=OutDruck.getNextNode(OutDruck.getRootNode());Node!=null;Node=OutDruck.getNextNode(Node))
			if(Node.getLevel()==2 && ((Integer)Node.getUserData()).intValue()==iAIC_Abschnitt)
				VecRemoveFromDruck.addElement(Node);
                  for(int j=0;j<VecRemoveFromDruck.size();j++)
                  {
			JCOutlinerNode Node = VecRemoveFromDruck.elementAt(j);
			Node.getParent().removeChild(Node);
                  }
                }
                Qry.free();
	}

	OutAbschnitt.folderChanged(OutAbschnitt.getRootNode());
	OutDruck.folderChanged(OutDruck.getRootNode());
  }
}

private void addPopupD()
{
  g.addMenuItem("Edit",popupD,"edit print",AL);
  g.addMenuItem("copy",popupD,"copy print",AL);
  g.addMenuItem("Neu",popupD,"new print",AL);
  g.addMenuItem("Loeschen",popupD,"delete print",AL);
  popupD.addSeparator();
  g.addMenuItem("Bits",popupD,"DruckBits",AL);
  if (g.Prog()) g.addMenuItem("Änderungen",popupD,"Change",AL);

  OutDruck.getOutliner().addMouseListener(new MouseListener()
  {
    public void mousePressed(MouseEvent ev) {}

    public void mouseClicked(MouseEvent ev)
    {
      if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM) && g.Def())
        popupD.show((Component)ev.getSource(), ev.getX(), ev.getY());
    }

    public void mouseEntered(MouseEvent ev) {}
    public void mouseExited(MouseEvent ev) {}
    public void mouseReleased(MouseEvent ev) {}
  });

}

private void addPopup()
{
  g.addMenuItem("edit segment",popup,null,AL);
  g.addMenuItem("copy segment",popup,null,AL);
  g.addMenuItem("new segment",popup,null,AL);
  g.addMenuItem("delete segment",popup,null,AL);
  popup.addSeparator();
  g.addMenuItem("Abfrage",popup,null,AL);
  g.addMenuItem("Abfrage2",popup,null,AL);
  g.addMenuItem("show",popup,null,AL);
  g.addMenuItem("Bits",popup,"AbschnittBits",AL);

  OutAbschnitt.getOutliner().addMouseListener(new MouseListener()
  {
    public void mousePressed(MouseEvent ev)
    {}

    public void mouseClicked(MouseEvent ev)
    {
      //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
      if ((ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM) && g.Def())
	popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
    }

    public void mouseEntered(MouseEvent ev)
    {}

    public void mouseExited(MouseEvent ev)
    {}

    public void mouseReleased(MouseEvent ev)
    {}
  });
}

private void ShowDruckBits()
{
  Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr","Sub","Kennung","Bezeichnung","Anzahl"});
  addArt(Tab2,"Web","Web",-8);
  addArt(Tab2,"AIC_ABFRAGE","ABFRAGE",-7);
  addArt(Tab2,"Kennzeichen","Kennzeichen",-6);
  addArt(Tab2,"AIC_Rolle","Rolle",-5);
  addArt(Tab2,"AIC_Schrift","Schrift",-4);
  addArt(Tab2,"AIC_BEWEGUNGSTYP","BEW",-3);
  addArt(Tab2,"PROG","PROG",-2);
  addArt(Tab2,"AIC_MODELL","MODELL",-1);
  addBitD(Tab2, "cstPntUnterschrift","Unterschrift", Drucken.cstPntUnterschrift, 0);
  addBitD(Tab2, "cstPntKeinDrucktitel","kein Drucktitel", Drucken.cstPntKeinDrucktitel, 1);
  addBitD(Tab2, "cstPntTagesanzeige","Tagesanzeige", Drucken.cstPntTagesanzeige, 2);
  addBitD(Tab2, "cstPntKeinKopfFuss","KeinKopfFuss", Drucken.cstPntKeinKopfFuss, 3);
  addBitD(Tab2, "cstPntStammLinks","StammLinks", Drucken.cstPntStammLinks, 4);
  addBitD(Tab2, "cstPntKeinStammtitel","kein Stammtitel", Drucken.cstPntKeinStammtitel, 5);
  addBitD(Tab2, "cstPntKeineGruppierung","keine Gruppierung", Drucken.cstPntKeineGruppierung, 6);
  addBitD(Tab2, "cstPntQuerformat","Querformat", Drucken.cstPntQuerformat, 7);
  addBitD(Tab2, "cstPntPers","pers", Drucken.cstPntPers, 8);
  addBitD(Tab2, "cstPntDrucktitelLinks","Drucktitel links", Drucken.cstPntDrucktitelLinks, 9);
  addBitD(Tab2, "del: cstPntNeuerDruck","neuer Druck", 0x0400, 10);
  addBitD(Tab2, "cstPntSchicht","Schichtdruck", Drucken.cstPntSchicht, 11);
  addBitD(Tab2, "cstPntPeriode","Periode", Drucken.cstPntPeriode, 12);
  addBitD(Tab2, "cstPntSeitenumbruch","Seitenumbruch", Drucken.cstPntSeitenumbruch, 13);
  addBitD(Tab2, "cstPntDirekt","Direkt", Drucken.cstPntDirekt, 14);
  addBitD(Tab2, "cstPntUseSync","use Sync", Drucken.cstPntUseSync, 15);
  addBitDS(Tab2, "cstPntZaTag","Tag", Drucken.cstPntZeitart,Drucken.cstPntZaTag, 16,1);
  addBitDS(Tab2, "cstPntZaWoche","Woche", Drucken.cstPntZeitart,Drucken.cstPntZaWoche, 16,2);
  addBitDS(Tab2, "cstPntZaMonat","Monat", Drucken.cstPntZeitart,Drucken.cstPntZaMonat, 16,3);
  addBitDS(Tab2, "cstPntZaQuartal","Quartal", Drucken.cstPntZeitart,Drucken.cstPntZaQuartal, 16,4);
  addBitDS(Tab2, "cstPntZaJahr","Jahr", Drucken.cstPntZeitart,Drucken.cstPntZaJahr, 16,5);
  addBitD(Tab2, "cstPntNewYear","new year", Drucken.cstPntNewYear, 19);
  addBitD(Tab2, "del: cstDruckerauswahl","-", Drucken.cstDruckerauswahl, 20);
  addBitD(Tab2, "cstPntKnopf","Knopf", Drucken.cstPntKnopf, 21);
  addBitD(Tab2, "cstPntExport","exportierbar2", Drucken.cstPntExport, 22);
  addBitD(Tab2, "cstPntHtml","Html-Druck", Drucken.cstPntHtml, 23);
  addBitD(Tab2, "cstPntKeinProg","kein Prog",Drucken.cstPntKeinProg, 24);
  addBitD(Tab2, "cstPntEMail","E-Mail", Drucken.cstPntEMail, 25);
  addBitD(Tab2, "cstPntFarbe","Farbe", Drucken.cstPntFarbe, 26);
  addBitD(Tab2, "cstPntKeinZRtitel","kein Zeitraumtitel", Drucken.cstPntKeinZRtitel, 27);
  addBitD(Tab2, "cstPntPdfSave","PDF-Save", Drucken.cstPntPdfSave, 28);
  addBitD(Tab2, "cstPntMenge","Menge", Drucken.cstPntMenge, 29);
  addBitD(Tab2, "cstPntBewZR","ZR_Bew", Drucken.cstPntBewZR, 30);
  addBitD(Tab2, "cstPntNurSumme","NurSumme", Drucken.cstPntNurSumme, 32);
  addBitD(Tab2, "cstPntKeineSumme","keine Summe", Drucken.cstPntKeineSumme, 33);
  addBitD(Tab2, "cstPntED","ED", Drucken.cstPntED, 34);
  JDialog FomGid = new JDialog((Frame)thisFrame, "Druckbits", false);
  AUOutliner Grid = new AUOutliner();
  FomGid.getContentPane().add("Center", Grid);
  Tab2.showGrid(Grid);
  FomGid.setSize(500, 600);
  Static.centerComponent(FomGid,thisFrame);
    Grid.addActionListener(new JCActionListener() {
      public void actionPerformed(JCActionEvent ev) {
        JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
        long i=Sort.geti(Nod.getLabel(),0);
        int i2=Sort.geti((Vector)Nod.getLabel(),1);
        String sSp=null;
        String sSp2=null;
        if (i<0)
        {
      	  sSp=Sort.gets(Nod.getLabel(), 2);
      	  if (sSp.equals("AIC_Rolle"))
      		  sSp2="(select kennung from Rolle where aic_Rolle=begriff."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
      	  else if (sSp.equals("AIC_Schrift"))
      		  sSp2="(select kennung from Schrift where aic_Schrift=begriff."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
      	  else if (sSp.equals("AIC_BEWEGUNGSTYP"))
    		  sSp2="(select kennung from BEWEGUNGSTYP where AIC_BEWEGUNGSTYP=begriff."+sSp+") "+ Sort.gets(Nod.getLabel(),3);
      	  else
      		  sSp2=sSp;
        }
        else if (i2>0)
        {
          if (i==16)
          {
            i=Drucken.cstPntZeitart;
            i2=i2==1 ? Drucken.cstPntZaTag:i2==2 ? Drucken.cstPntZaWoche:i2==3 ? Drucken.cstPntZaMonat:i2==4 ? Drucken.cstPntZaQuartal:Drucken.cstPntZaJahr;
          }

        }
        else
          i=(long)Math.pow(2,i);
        String s=(String)((Vector)Nod.getLabel()).elementAt(2);
        String sWhere=i==-1 || i==-7 ? "aic_begriff in (select aic_begriff from begriff_zuordnung where beg_aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic(i==-1 ? "Modell":"Abfrage")+"))":
        		i<-1 ? sSp+" is not null":i2>0 ? g.bits("bits",i)+"="+i2:g.bit("bits",i);
        Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_begriff,kennung,defbezeichnung,(select kennung from code where aic_code=prog) Programm"+g.bei("web", 1, "web")+g.bei("bits", Drucken.cstPntPers,"Pers")+(i<-1 && i!=-7 && i!=-8 ? ","+sSp2:"")+
        		" from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+" and "+sWhere,true);
        Tab.showGrid("Druck mit "+s+" (" + i+")", thisFrame);
        Tab.Grid.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
            if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
            {
              g.select(Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(),0),OutDruck);
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

private void ShowAbschnittBits()
{
    Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr","Sub", "Kennung","Bezeichnung","Anzahl"});
    addArtS(Tab2,"Bilder","Bilder",-4);
    addArtS(Tab2,"Zahl","Zahl",-3);
    addArtS(Tab2,"AIC_Raster","Vorlage",-2);
    addArtS(Tab2,"Kennzeichen","Kennzeichen",-1);
    addBitS(Tab2, "cstUeberschrift","Ueberschrift",Drucken.cstUeberschrift, 0);
    addBitS(Tab2, "cstkeineHauptsumme","keine_Hauptsumme",Drucken.cstkeineHauptsumme, 1);
    addBitS(Tab2, "cstGruppenRed","Gruppenreduktion",Drucken.cstGruppenRed, 2);
    addBitS(Tab2, "del: cstErsteFett","1. Spalte fett",              0x0008, 3);
    addBitS(Tab2, "del: cstEigZeilen","Einzeldaten",              0x0010, 4);
    addBitS(Tab2, "del: cstKeinTitel","kein Titel",              0x0020, 5);
    addBitS(Tab2, "cstGesamtsumme","Gesamtsumme",  Drucken.cstGesamtsumme, 6);
    addBitSS(Tab2, "cstLinks","links",        Drucken.cstRN,Drucken.cstLinks, 7,1);
    addBitSS(Tab2, "cstRechts1","rechts",     Drucken.cstRN,Drucken.cstRechts1, 7,2);
    addBitSS(Tab2, "cstNeben","Nebeneinander",Drucken.cstRN,Drucken.cstNeben, 7,3);
    addBitSS(Tab2, "cstRechts2","rechts ohne",Drucken.cstRN,Drucken.cstRechts2, 7,4);
    addBitS(Tab2, "cstNurEMail","nurE-Mail",         Drucken.cstNurEMail, 8);
    addBitS(Tab2, "cstLtAic","ltAic",   Drucken.cstLtAic, 9);
    addBitSS(Tab2, "cstNormal","Normalabschnitt",          Drucken.cstArt,Drucken.cstNormal,11,1);
    addBitSS(Tab2, "cstDiagramm","-",                      Drucken.cstArt,Drucken.cstDiagramm,11,2);
    addBitSS(Tab2, "cstIstUeberschrift","ist Ueberschrift",Drucken.cstArt,Drucken.cstIstUeberschrift,11,3);
    addBitSS(Tab2, "cstFixPos","Pos fixieren",             Drucken.cstArt,Drucken.cstFixPos,11,4);
    addBitSS(Tab2, "cstBedingung","Bedingung",             Drucken.cstArt,Drucken.cstBedingung,11,5);
    addBitSS(Tab2, "cstSchicht2","Schicht2",               Drucken.cstArt,Drucken.cstSchicht2,11,6);
    addBitSS(Tab2, "cstSchichtTMZ","SchichtTMZ",           Drucken.cstArt,Drucken.cstSchichtTMZ,11,7);
    addBitSS(Tab2, "cstEMail_A","E-Mail-Adressen",         Drucken.cstArt,Drucken.cstEMail_A,11,8);
    addBitSS(Tab2, "cstEMail_D","E-Mail-Daten",            Drucken.cstArt,Drucken.cstEMail_D,11,9);
    addBitSS(Tab2, "cstEMail_S","E-Mail-Signatur",         Drucken.cstArt,Drucken.cstEMail_S,11,10);
    addBitSS(Tab2, "cstHtmlDirekt","html-direkt",          Drucken.cstArt,Drucken.cstHtmlDirekt,11,11);
    addBitSS(Tab2, "cstPDF_Save","PDF-Save",           	   Drucken.cstArt,Drucken.cstPDF_Save,11,12);
    addBitSS(Tab2, "cstPDF_Direkt","PDF-Direkt",           Drucken.cstArt,Drucken.cstPDF_Direkt,11,13);
    addBitS(Tab2, "del: cstUeberschriftLinks","Ueberschrift Links",      0x1000,12);
    addBitS(Tab2, "cstKeinAbstand","kein Abstand", Drucken.cstKeinAbstand,13);
    addBitS(Tab2, "cstKeineSumme","keine Summe",   Drucken.cstKeineSumme,14);
    addBitS(Tab2, "cstFixRaster","Raster fixieren",Drucken.cstFixRaster,16);
    addBitS(Tab2, "cstStrecken","strecken",        Drucken.cstStrecken,18);
    addBitS(Tab2, "cstNichtDrucken","nicht drucken",Drucken.cstNichtDrucken,19);
    addBitS(Tab2, "cstImmer","immer",              Drucken.cstImmer,20);
    addBitS(Tab2, "cstBlocksatz","Blocksatz",      Drucken.cstBlocksatz,22);
    addBitS(Tab2, "cstLeere","Leere",              Drucken.cstLeere,23);
    addBitS(Tab2, "cstBedST","bedingter Spaltentitel",Drucken.cstBedST,24);
    addBitS(Tab2, "cstTest","Testmeldung",         Drucken.cstTest,25);
    addBitS(Tab2, "cstNeueSeite","Seitenumbruch",  Drucken.cstNeueSeite,26);
    addBitS(Tab2, "cstNurSummeA","NurSumme",  Drucken.cstNurSummeA,27);
    addBitS(Tab2, "cstTestfill","Testfill",  Drucken.cstTestfill,28);
    addBitS(Tab2, "cstnurMitTitel","NurMitTitel",  Drucken.cstNurMitTitel,29);
    addBitS(Tab2, "cstColor","Color",  Drucken.cstColor,30);
    JDialog FomGid = new JDialog((Frame)thisFrame, "Abschnittbits", false);
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
          if (i==7)
          {
            l=Drucken.cstRN;
            l2=i2==1 ? Drucken.cstLinks:i2==2 ? Drucken.cstRechts1:i2==3 ? Drucken.cstNeben:Drucken.cstRechts2;
          }
          else if (i==11)
          {
            l=Drucken.cstArt;
            l2=i2==1 ? 0:i2==2 ? Drucken.cstDiagramm:i2==3 ? Drucken.cstIstUeberschrift:i2==4 ? Drucken.cstFixPos:
               i2==5 ? Drucken.cstBedingung:i2==6 ? Drucken.cstSchicht2:i2==7 ? Drucken.cstSchichtTMZ:
               i2==8 ? Drucken.cstEMail_A:i2==9 ? Drucken.cstEMail_D:i2==10 ? Drucken.cstEMail_S:i2==11 ? Drucken.cstHtmlDirekt:i2==12 ? Drucken.cstPDF_Save:i2==13 ? Drucken.cstPDF_Direkt:-1;
          }

        }
        else
          l=(long)Math.pow(2,i);
        String s=(String)((Vector)Nod.getLabel()).elementAt(2);
        //long l=(long)Math.pow(2,i);
        String sSp=null;
//        String sSp2=null;
        String sWhere=null;
        if (i<0)
        {
      	  sSp=Sort.gets(Nod.getLabel(), 2);
      	  if (sSp.equals("Zahl"))
      		  sWhere="Zahl<>0";
      	  else if (sSp.equals("Bilder"))
      	  {
      		  int iTabA=g.TabTabellenname.getAic("ABSCHNITT");
      		  sWhere="(select count(*) from daten_bild where aic_tabellenname="+iTabA+" and aic_fremd=aic_abschnitt)>0";
      		  sSp="(select filename from daten_bild where aic_tabellenname="+iTabA+" and aic_fremd=aic_abschnitt and aic_zustand="+g.iSpSw+") "+ sSp;
      	  }
      	  else
      		  sWhere=sSp+" is not null";
      	  if (i==-2) //Vorlage
      		  sSp="(select kennung from Raster where aic_Raster=abschnitt.aic_Raster) "+ sSp;
//      	else if (i==-13) // Bilder
//        {
//      	  sSpalte="(select filename from daten_bild where aic_tabellenname="+g.iTabBegriff+" and aic_fremd=b.aic_begriff and aic_zustand="+g.iSpSw+") "+ sSpB+
//      			  ",(select filename from daten_bild where aic_tabellenname="+g.iTabBegriff+" and aic_fremd=b.aic_begriff and aic_zustand="+g.iSpFX+") "+ sSpB+"FX";
//      	  sSp="(select count(*) from daten_bild where aic_tabellenname="+g.iTabBegriff+" and aic_fremd=b.aic_begriff)>0";
//        }
        }
        Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_abschnitt,kennung"+g.AU_Bezeichnung("abschnitt")+(i<0 ? ","+sSp:"")+" from abschnitt where "+(i<0 ? sWhere:i2>0 ? g.bits("bits",l)+"="+l2:g.bit("bits",l)),true);
        //if(Tab.FrmGrid != null)
        //  Tab.FrmGrid.dispose();
        Tab.showGrid("Abschnitt mit "+s+" (" + i+(i2>0?"/"+i2:"")+")", /*bModal ? FomGid :*/ thisJFrame());
        Tab.Grid.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
            if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
            {
              g.select(Sort.geti(((jclass.bwt.JCOutlinerComponent)ev.getSource()).getSelectedNode().getLabel(),0),OutAbschnitt);
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

private void addBitS(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
{
  Tab2.addInhalt("Nr",i);
  Tab2.addInhalt("Sub",0);
  Tab2.addInhalt("Kennung",sConst);
  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from abschnitt where "+g.bit("bits",l)));
}

private void addBitSS(Tabellenspeicher Tab2,String sConst,String sBez,long l,long l2,int i,int iSub)
{
  Tab2.addInhalt("Nr",i);
  Tab2.addInhalt("Sub",iSub);
  Tab2.addInhalt("Kennung",sConst);
  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Radiobutton",sBez));
  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from abschnitt where "+g.bits("bits",l)+"="+l2));
}

private void addArt(Tabellenspeicher Tab2,String sConst,String sBez,int i)
{
	  Tab2.addInhalt("Nr",i);
	  Tab2.addInhalt("Sub",0);
	  Tab2.addInhalt("Kennung",sConst);
	  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getShow(sBez));
	  String s=i==-1 || i==-7 ?"begriff_zuordnung where aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+
			  ") and beg_aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic(i==-1 ? "Modell":"Abfrage")+")":
		  "begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+" and "+sConst+" is not null";
	  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from "+s));
}

private void addArtS(Tabellenspeicher Tab2,String sConst,String sBez,int i)
{
	  Tab2.addInhalt("Nr",i);
	  Tab2.addInhalt("Sub",0);
	  Tab2.addInhalt("Kennung",sConst);
	  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getShow(sBez));
//	  SQL.getInteger(g,i==-13 ? "select count(*) from ""daten_bild join modell on aic_tabellenname="+g.iTabBegriff+" and aic_fremd=modell.aic_begriff"
//	  String s=i==-1 || i==-7 ?"begriff_zuordnung where aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+
//			  ") and beg_aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic(i==-1 ? "Modell":"Abfrage")+")":
	  String s=i==-4 ? "daten_bild where aic_tabellenname="+g.TabTabellenname.getAic("ABSCHNITT"):"abschnitt where "+sConst+" is not null"+(i==-3 ? " and "+sConst+" <> 0":"");
	  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from "+s));
}

private void addBitD(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
{
  Tab2.addInhalt("Nr",i);
  Tab2.addInhalt("Sub",0);
  Tab2.addInhalt("Kennung",sConst);
  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+" and "+g.bit("bits",l)));
}

private void addBitDS(Tabellenspeicher Tab2,String sConst,String sBez,long l,long l2,int i,int iSub)
{
  Tab2.addInhalt("Nr",i);
  Tab2.addInhalt("Sub",iSub);
  Tab2.addInhalt("Kennung",sConst);
  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Radiobutton",sBez));
  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+" and "+g.bits("bits",l)+"="+l2));
}

private void Build()
{
  StyDruck=g.getStyle(g.getImage("Begriffgruppe","Druck"));
  //StyDruckAlt=new JCOutlinerNodeStyle(StyDruck);
  //StyDruckAlt.setForeground(Color.RED);
  StyAbschnitt=g.getStyle(g.getGif(g.TabTabellenname,"ABSCHNITT"));

	OutDruck.setBackground(Color.white);
	OutAbschnitt.setBackground(Color.white);
	OutDruck.setRootVisible(false);
	OutAbschnitt.setRootVisible(false);
	OutDruck.setAllowMultipleSelections(true);
	OutAbschnitt.setAllowMultipleSelections(true);
	//g.TabTabellenname.posInhalt("Kennung","STAMMTYP");
	String[] s = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Prog"),g.getShow("Web"),g.getShow("Pers"),g.TabTabellenname.getBezeichnungS("STAMMTYP"),g.getShow("Art"),
			g.TabTabellenname.getBezeichnungS("SCHRIFT"),g.getShow("Change"),g.TabTabellenname.getBezeichnungS("ROLLE"),g.getShow("Kennzeichen"),g.getShow("DefBezeichnung")};
	OutDruck.setColumnButtons(s);
	OutDruck.setNumColumns(s.length);
	s = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Kennzeichen"),g.getShow("Aic"),g.getShow("Anzahl"),g.getBegriffS("Frame","Abfrage")/*,g.getBegriffS("Show","Schrift Titel"),g.getBegriffS("Show","Schrift Text")*/,g.getShow("Vorlage"),
			g.TabTabellenname.getBezeichnungS("STAMMTYP")/*,g.getBegriff("Show","Ueberschrift"),g.getBegriff("Show","Unterschrift"),g.getBegriff("Show","Titel darunter"),g.getBegriff("Show","1. Spalte fett"),g.getBegriff("Show","Einzeldaten")*/};
	OutAbschnitt.setColumnButtons(s);
	OutAbschnitt.setNumColumns(s.length);
	CboProg = new ComboSort(g);
	CboProg.fillBegriffTable(g.TabBegriffgruppen.getAic("Programm"),true,true);
	CboStammtyp = new ComboSort(g);
    CboRolle =new RolleEingabe(g,thisFrame);
	CboBewegungstyp = new ComboSort(g);
        CboModell = new ModellEingabe(g,thisFrame);
//        CboModell.fillCbo(1);
        CboAbfrage = new AbfrageEingabe(g,thisFrame,true,false);
        
	CboSchrift = new Schrift(g);
	CboAbfragen = new AbfrageEingabe(g,thisFrame,true,true);
	//CboSchriftTitel = new Schrift(g);
	//CboSchriftText = new Schrift(g);
	CboVorlagen = new DefVorlagen(g,false);

	CbxUeberschrift = getCheckboxM("Ueberschrift",false);
	//CbxTitelDarunter = g.getCheckbox("Titel darunter");
	//CbxErsteSpalteFett = g.getCheckbox("1. Spalte fett");
	//CbxEinzeldaten = g.getCheckbox("Einzeldaten");
	//CbxKeinTitel = g.getCheckbox("kein Titel");
	CbxGesamtsumme = getCheckboxM("Gesamtsumme",false);
	CbxNurEMail = getCheckboxM("nurE-Mail",false);
	CbxLtAic = getCheckboxM("ltAic",false);
    CbxKeineHS = getCheckboxM("keine_Hauptsumme",false);
    CbxGruppenRed = getCheckboxM("Gruppenreduktion",false);
        RadLinks = getRadiobuttonM("links");
	RadRechts = getRadiobuttonM("rechts");
        RadRechts2 = getRadiobuttonM("rechts ohne");
	RadNebeneinander = getRadiobuttonM("Nebeneinander");
	//CbxUeberschriftLinks = g.getCheckbox("Ueberschrift Links");
	CbxKeinAbstand = getCheckboxM("kein Abstand",false);
        CbxKeineSumme = getCheckboxM("keine Summe",false);
        CbxFixRaster = getCheckboxM("Raster fixieren",false);
        RadNormal = getRadiobuttonM("Normalabschnitt");
        RadFixPos = getRadiobuttonM("Pos fixieren");
        RadDiagramm = getRadiobuttonM("Diagramm");
        RadIstUeberschrift = getRadiobuttonM("ist Ueberschrift");
        RadBedingung = getRadiobuttonM("Bedingung");
        RadEMail_A = getRadiobuttonM("E-Mail-Adressen");
        RadEMail_D = getRadiobuttonM("E-Mail-Daten");
        RadEMail_S = getRadiobuttonM("E-Mail-Signatur");
        RadSchicht2 = getRadiobuttonM("Schicht2");
        RadSchichtTMZ = getRadiobuttonM("SchichtTMZ");
        RadPDF_Save = getRadiobuttonM("PDF-Save");
        RadPDF_Direkt = getRadiobuttonM("PDF-Direkt");
        RadHtmlDirekt = getRadiobuttonM("html-direkt");
        CbxStrecken = getCheckboxM("strecken",false);
        CbxNichtDrucken = getCheckboxM("nicht drucken",false);
        CbxImmer = getCheckboxM("immer",false);
        CbxBlocksatz = getCheckboxM("Blocksatz",false);
        CbxLeere = getCheckboxM("leere",false);
        CbxBedST = getCheckboxM("bedingter Spaltentitel",false);
        CbxTest = getCheckboxM("Testmeldung",false);
        CbxNeueSeite = getCheckboxM("Seitenumbruch",false);
        CbxNurSummeA = getCheckboxM("NurSumme",false);
        CbxTestfill = getCheckboxM("Testfill",false);
        CbxNurMitTitel = getCheckboxM("NurMitTitel",false);
        CbxColor = getCheckboxM("Color",false);
        MemoAbschnitt = new AUTextArea();
        BtnBildAbschnitt = new BildEingabe((JFrame)thisFrame,g);
        if (bBildOld)
        	BtnBildAbschnitt.activateEvent();
        else
        {
        	BtnBildAbschnitt.setDB(true);
        	BtnBildAbschnitt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
              	  ((BildEingabe)e.getSource()).LadeBild();
                }
            });
        }
	MemoDruck = new AUTextArea();
        TooltipDruck = new AUTextArea();

	/*BtnOk = getButton("Ok");
	if(BtnOk==null) BtnOk=new JButton();
	BtnAbbruch = getButton("Abbruch");
	if(BtnAbbruch==null) BtnAbbruch=new JButton();*/
        BtnAlleDrucke = getButton("alle Drucke");

        if(BtnAlleDrucke==null)
          BtnAlleDrucke=new JButton();
        else
          BtnAlleDrucke.setVisible(g.Def());
        BtnSpeichern = getButton("Speichern");
        if(BtnSpeichern==null) BtnSpeichern=new JButton();
        //BtnBeenden = getButton("Beenden");
        //if(BtnBeenden==null) BtnBeenden=new JButton();
	//BtnNewPrint = getButton("new print");
	//if(BtnNewPrint==null) BtnNewPrint=new JButton();
	BtnEditPrint = getButton("edit print");
	if(BtnEditPrint==null) BtnEditPrint=new JButton();
	BtnDeletePrint = getButton("delete print");
	if(BtnDeletePrint==null) BtnDeletePrint=new JButton();
	//BtnNewSegment = getButton("new segment");
	//if(BtnNewSegment==null) BtnNewSegment=new JButton();
	BtnEditSegment = getButton("edit segment");
	if(BtnEditSegment==null) BtnEditSegment=new JButton();
	BtnDeleteSegment = getButton("delete segment");
	if(BtnDeleteSegment==null) BtnDeleteSegment=new JButton();
        BtnCopySegment = getButton("copy segment");
	if(BtnCopySegment==null) BtnCopySegment=new JButton();
	BtnHinzufuegen = getButton(">");
	if(BtnHinzufuegen==null) BtnHinzufuegen=new JButton();
	BtnEntfernen = getButton("<");
	if(BtnEntfernen==null) BtnEntfernen=new JButton();
	BtnRauf = getButton("Pfeil oben");
	if(BtnRauf==null) BtnRauf=new JButton();
	BtnRunter = getButton("Pfeil unten");
	if(BtnRunter==null) BtnRunter=new JButton();
	BtnSeperator = getButton("Seperator");
	if(BtnSeperator==null) BtnSeperator=new JButton();
        //BtnShow = getButton("show");
        //if(BtnShow==null) BtnShow=new JButton();
        //BtnAbfrage = getFormularButton("Abfrage");
	//if(BtnAbfrage==null) BtnAbfrage = new JButton();
	//BtnRefresh = getButton("Refresh");
	//if(BtnRefresh==null) BtnRefresh = new JButton();
        BtnErsetzen = getButton("Ersetzen");
        if(BtnErsetzen==null) BtnErsetzen=new JButton();

	BtnDruckOk=g.getButton("Ok");
	BtnDruckAbbruch=g.getButton("Abbruch");
	BtnAbschnittOk=g.getButton("Ok");
	BtnAbschnittAbbruch=g.getButton("Abbruch");

	JPanel Pnl_Outliner_Druck = getFrei("Outliner Druck");
	JPanel Pnl_Outliner_Abschnitt = getFrei("Outliner Abschnitt");

	if(Pnl_Outliner_Druck!=null)
		Pnl_Outliner_Druck.add(OutDruck);
	if(Pnl_Outliner_Abschnitt!=null)
		Pnl_Outliner_Abschnitt.add(OutAbschnitt);

	//Frame Druck
	FrmDruck = new JDialog((JFrame)thisFrame,g.TabBegriffgruppen.getBezeichnungS("Druck"),false);
	FrmDruck.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	//FrmDruck.setTitle(g.TabBegriffgruppen.getBezeichnung("Druck"));
	FrmDruck.getContentPane().setLayout(new BorderLayout(2,2));

	JPanel PnlNorth = new JPanel(new BorderLayout());
	JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
//	Pnl.add(new JLabel(g.getBegriffS("Show","DefBezeichnung")+":"));
//    Pnl.add(new JLabel(g.getShow("Bezeichnung")+":"));
//    Pnl.add(new JLabel(g.getShow("Kennung")+":"));
//    Pnl.add(new JLabel(g.getShow("KennzeichenD")+":"));
    g.addLabel4(Pnl,"DefBezeichnung");
    g.addLabel4(Pnl,"Bezeichnung");
    g.addLabel4(Pnl,"Kennung");
    g.addLabel4(Pnl,"KennzeichenD");
    g.addLabel4(Pnl,"Programm");
    g.addLabel4(Pnl,"Bewegungstyp");
    g.addLabel4(Pnl,"Stammtyp");
    g.addLabel4(Pnl,"Rolle");
    g.addLabel4(Pnl,"Schrift");
    g.addLabel4(Pnl,"Modell");
    g.addLabel4(Pnl,"Abfrage");
//    Pnl.add(new JLabel(g.getShow("Programm")+":"));  
//	Pnl.add(new JLabel(g.TabTabellenname.getBezeichnungS("BEWEGUNGSTYP")+":"));
//	Pnl.add(new JLabel(g.TabTabellenname.getBezeichnungS("STAMMTYP")+":"));
//    Pnl.add(new JLabel(g.TabTabellenname.getBezeichnungS("ROLLE")+":"));
//	Pnl.add(new JLabel(g.TabTabellenname.getBezeichnungS("SCHRIFT")+":"));
//    Pnl.add(new JLabel(g.TabTabellenname.getBezeichnungS("MODELL")+":"));
//    Pnl.add(new JLabel(g.TabTabellenname.getBezeichnungS("ABFRAGE")+":"));

	CbxPntKeinKopfFuss = getCheckboxM("KeinKopfFuss",false);
	CbxPntStammtitelLinks = getCheckboxM("StammLinks",false);
	CbxPntUnterschrift = getCheckboxM("Unterschrift",false);
	CbxPntKeinDrucktitel = getCheckboxM("kein Drucktitel",false);
	CbxPntTagesanzeige = getCheckboxM("Tagesanzeige",false);
	CbxPntKeinStammtitel = getCheckboxM("kein Stammtitel",false);
        CbxPntKeinZRtitel = getCheckboxM("kein Zeitraumtitel",false);
        CbxPntPdfSave = getCheckboxM("PDF-Save",false);
        CbxEMail = getCheckboxM("E-Mail",false);
        CbxPntMenge = getCheckboxM("Menge",false);
        CbxPntBewZR = getCheckboxM("ZR_Bew",false);
        CbxNurSummeD = getCheckboxM("NurSumme",false);
        CbxKeineSummeD = getCheckboxM("keine Summe",false);
        CbxED = getCheckboxM("ED",false);
	CbxPntKeineGruppierung = getCheckboxM("keine Gruppierung",false);
	CbxPntQuerformat = getCheckboxM("Querformat",false);
	CbxPntPers = getCheckboxM("pers",false);
	CbxPntDrucktitelLinks = getCheckboxM("Drucktitel links",false);
        //CbxPntNeuerDruck = g.getCheckbox("neuer Druck");
        //CbxPntBewegung = g.getCheckbox("Bewegungsdruck");
        CbxPntSchicht=getCheckboxM("Schichtdruck",false);
        CbxPntPeriode = getCheckboxM("Periode",false);
        CbxPntSeitenumbruch = getCheckboxM("Seitenumbruch",false);
        CbxPntDirekt = getCheckboxM("Direkt",false);
        CbxPntUseSync = getCheckboxM("use Sync",false);
        CbxPntNewYear = getCheckboxM("all-the-year",false);
        CbxPntKnopf = getCheckboxM("Knopf",false);
        CbxPntExport = getCheckboxM("exportierbar2",false);
        CbxPntHtml = getCheckboxM("Html-Druck",false);
        CbxKeinProg = getCheckboxM("kein Prog",false);
        CbxWeb=getCheckboxM("Web",false);
        CbxTod=getCheckboxM("Tod",false);
        CbxFarbe = getCheckboxM("Farbe",false);
        RadOffen = getRadiobuttonM("offen");
        RadTag = getRadiobuttonM("Tag");
        RadWoche = getRadiobuttonM("Woche");
        RadMonat = getRadiobuttonM("Monat");
        RadQuartal = getRadiobuttonM("Quartal");
        RadJahr = getRadiobuttonM("Jahr");
        ButtonGroup group = new ButtonGroup();
        group.add(RadOffen);
        group.add(RadTag);
        group.add(RadWoche);
        group.add(RadMonat);
        group.add(RadQuartal);
        group.add(RadJahr);
        ButtonGroup group2 = new ButtonGroup();
        group2.add(RadLinks);
        group2.add(RadRechts);
        group2.add(RadRechts2);
        group2.add(RadNebeneinander);
        ButtonGroup group3 = new ButtonGroup();
        group3.add(RadNormal);
        group3.add(RadFixPos);
        group3.add(RadDiagramm);
        group3.add(RadIstUeberschrift);
        group3.add(RadBedingung);
        group3.add(RadEMail_A);
        group3.add(RadEMail_D);
        group3.add(RadEMail_S);
        group3.add(RadSchicht2);
        group3.add(RadSchichtTMZ);
        group3.add(RadHtmlDirekt);
        group3.add(RadPDF_Save);
        group3.add(RadPDF_Direkt);
        RadLinks.setSelected(true);

	/*Pnl.add(new JLabel(g.getBegriff("Show","keine Kopf-Fusszeile")+":"));
	Pnl.add(new JLabel(g.getBegriff("Show","Stammtitel links")+":"));
	Pnl.add(new JLabel(g.getBegriff("Show","Unterschrift")+":"));
	Pnl.add(new JLabel(g.getBegriff("Show","kein Drucktitel")+":"));
	Pnl.add(new JLabel(g.getBegriff("Show","Tagesanzeige")+":"));
	Pnl.add(new JLabel(g.getBegriff("Show","kein Stammtitel")+":"));
	Pnl.add(new JLabel(g.getBegriff("Show","keine Gruppierung")+":"));
	Pnl.add(new JLabel(g.getBegriff("Show","Querformat")+":"));
	Pnl.add(new JLabel(g.getBegriff("Show","Auch HS")+":"));
	Pnl.add(new JLabel(g.getBegriff("Show","Drucktitel links")+":"));*/

        //g.addLabel(Pnl,"außerhalb Druck");
        //Pnl.add(CbxPntNeuerDruck);
        
	//g.addLabel(Pnl,"neuer Druck später");
        PnlNorth.add("West",Pnl);

	Pnl = new JPanel(new GridLayout(0,1,2,2));
        Pnl.add(TxtDruckDefBezeichnung);
        Pnl.add(TxtDruckBezeichnung);
        Pnl.add(TxtDruckKennung);
        Pnl.add(TxtDruckKennzeichen);
    Pnl.add(g.addTwo(CboProg,CbxKeinProg));
	Pnl.add(CboBewegungstyp);
	Pnl.add(CboStammtyp);
        Pnl.add(CboRolle);
	Pnl.add(CboSchrift);
        Pnl.add(CboModell);
        Pnl.add(CboAbfrage);
    PnlNorth.add("Center",Pnl); 
    
    JPanel PnlBits = new JPanel(new GridLayout(0,3,1,3));
    JPanel PnlZR = new JPanel(new GridLayout(0,3,1,3));
    
    PnlBits.add(CbxPntSchicht);
    PnlBits.add(CbxPntKnopf);
    PnlBits.add(CbxPntDirekt);
    PnlBits.add(CbxPntSeitenumbruch);
        
    PnlBits.add(CbxPntKeinDrucktitel);
    PnlBits.add(CbxPntDrucktitelLinks);
        
    PnlBits.add(CbxPntKeinKopfFuss);
        
        PnlBits.add(CbxPntPdfSave);
        PnlBits.add(CbxPntExport);
        PnlBits.add(CbxEMail);
        PnlBits.add(CbxWeb);
        PnlBits.add(CbxFarbe);
        
        PnlBits.add(CbxPntKeineGruppierung);
        PnlBits.add(CbxPntPers);
        PnlBits.add(CbxPntUseSync);
        PnlBits.add(CbxPntQuerformat);
        
        PnlBits.add(CbxPntKeinStammtitel);
        PnlBits.add(CbxPntStammtitelLinks);
        PnlBits.add(CbxPntUnterschrift);
	
        PnlBits.add(CbxPntMenge);
        PnlBits.add(CbxPntHtml);
        PnlBits.add(CbxNurSummeD);
        PnlBits.add(CbxKeineSummeD);
        PnlBits.add(CbxED);
        
        // Panel Zeitraum
        PnlZR.add(CbxPntKeinZRtitel); 
        PnlZR.add(CbxPntTagesanzeige);
        PnlZR.add(CbxPntPeriode);
        
        PnlZR.add(RadOffen);
        PnlZR.add(RadTag);
        PnlZR.add(RadWoche);
        
        PnlZR.add(RadMonat);
        PnlZR.add(RadQuartal);
        PnlZR.add(RadJahr);
        
        PnlZR.add(CbxPntNewYear);
        PnlZR.add(CbxPntBewZR);
	

	Pnl = new JPanel(new GridLayout(1,0,2,2));
	Pnl.add(BtnDruckOk);
	Pnl.add(BtnDruckAbbruch);
	FrmDruck.getContentPane().add("North",PnlNorth);
        GroupBox GB1=new GroupBox(g.getBegriffS("Show","Memo"));
        GB1.add(MemoDruck);
        GroupBox GB2=new GroupBox(g.getBegriffS("Show","Tooltip"));
        GB2.add(TooltipDruck);
        JPanel PnlCenter2=new JPanel(new GridLayout(2,0));
        PnlCenter2.add(GB1);
        PnlCenter2.add(GB2);
        
        JPanel PnlInfo = new JPanel(new BorderLayout(3, 5));
        JPanel PnlWI = new JPanel(new GridLayout(0,1,2,2));
        g.addLabel4(PnlWI,"Aic");
        g.addLabel4(PnlWI,"changed");
        PnlWI.add(new JLabel());
        PnlInfo.add("West",PnlWI);
        JPanel PnlCI = new JPanel(new GridLayout(0,1,2,2));
        PnlCI.add(LblAic);
        PnlCI.add(LblChanged);
        PnlCI.add(CbxTod);
        PnlInfo.add("Center",PnlCI);
        
        JTabbedPane PnlTab=new JTabbedPane();
		PnlTab.add(g.getShow("Memo"),PnlCenter2);
		PnlTab.add(g.getShow("Bits"),PnlBits);
		PnlTab.add(g.getShow("ZR"),PnlZR);
		PnlTab.add(g.getShow("Info"),PnlInfo);
		
	FrmDruck.getContentPane().add("Center",PnlTab);
	FrmDruck.getContentPane().add("South",Pnl);
	//FrmDruck.pack();
	FrmDruck.setSize(350,550);

	//Frame Abschnitt
	FrmAbschnitt = new JDialog((JFrame)thisFrame,g.TabTabellenname.getBezeichnungS("ABSCHNITT"),false);
	FrmAbschnitt.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	//FrmAbschnitt.setTitle(g.TabTabellenname.getBezeichnung("ABSCHNITT"));
	FrmAbschnitt.getContentPane().setLayout(new BorderLayout(2,2));
	JPanel Pnl2 = new JPanel(new BorderLayout());
	Pnl = new JPanel(new GridLayout(0,1,2,2));
	Pnl.add(new JLabel(g.getBegriffS("Show","Bezeichnung")+":"));
	Pnl.add(new JLabel(g.getBegriffS("Show","Kennung")+":"));
        Pnl.add(new JLabel(g.getBegriffS("Show","Kennzeichen")+":"));
	Pnl.add(new JLabel(g.getBegriffS("Frame","Abfrage")+":"));
	//Pnl.add(new JLabel(g.getBegriff("Show","Schrift Titel")+":"));
	//Pnl.add(new JLabel(g.getBegriff("Show","Schrift Text")+":"));
	Pnl.add(new JLabel(g.getBegriffS("Show","Vorlage")+":"));
        Pnl.add(new JLabel(g.getBegriffS("Show","Zahl")+":"));
        Pnl.add(new JLabel(g.getBegriffS("Show","Bild")+":"));

	Pnl2.add("West",Pnl);
	Pnl = new JPanel(new GridLayout(0,1,2,2));
	Pnl.add(TxtAbschnittBezeichnung);
	Pnl.add(TxtAbschnittKennung);
        Pnl.add(TxtAbschnittKennzeichen);
	Pnl.add(CboAbfragen);
	//Pnl.add(CboSchriftTitel);
	//Pnl.add(CboSchriftText);
	Pnl.add(CboVorlagen);
        Pnl.add(EdtZahl);
        Pnl.add(BtnBildAbschnitt);
	Pnl2.add("Center",Pnl);
	FrmAbschnitt.getContentPane().add("North",Pnl2);
	Pnl = new JPanel(new GridLayout(0,2,2,2));
	//Pnl.add(CbxKeinDrucktitel);
        //g.addLabel(Pnl,"neu + alter Druck");
        //Pnl.add(new JLabel());
	//Pnl.add(new JLabel());
	g.addLabel4(Pnl,"Art");
	Pnl.add(CbxUeberschrift);
	    Pnl.add(RadNormal);
        Pnl.add(RadIstUeberschrift);
        Pnl.add(RadFixPos);
        Pnl.add(RadBedingung);
        Pnl.add(RadDiagramm);
        Pnl.add(RadEMail_D);
        Pnl.add(RadEMail_A);
        Pnl.add(RadEMail_S);
        Pnl.add(RadHtmlDirekt);
        Pnl.add(new JLabel());
        Pnl.add(RadSchicht2);
        Pnl.add(RadSchichtTMZ);
        Pnl.add(RadPDF_Save);
        Pnl.add(RadPDF_Direkt);
        g.addLabel4(Pnl,"Ort");
        Pnl.add(new JLabel());
        Pnl.add(RadLinks);
        Pnl.add(RadRechts);
        Pnl.add(RadNebeneinander);
        Pnl.add(RadRechts2);
        Pnl.add(CbxGesamtsumme);
        Pnl.add(CbxKeineHS);
        //g.addLabel(Pnl,"nur neuer Druck");
        //Pnl.add(new JLabel());
        Pnl.add(CbxKeineSumme);
        Pnl.add(CbxNurSummeA);
        Pnl.add(CbxKeinAbstand);
        Pnl.add(CbxFixRaster);
        Pnl.add(CbxBlocksatz);
        Pnl.add(CbxStrecken);
        Pnl.add(CbxColor);
        Pnl.add(CbxBedST);
        Pnl.add(CbxNeueSeite);
        Pnl.add(CbxLeere);
        Pnl.add(CbxTest);
        Pnl.add(CbxTestfill);
        Pnl.add(CbxNichtDrucken);
        Pnl.add(CbxImmer);
        Pnl.add(CbxGruppenRed);
        Pnl.add(CbxNurEMail);
        Pnl.add(CbxLtAic);
        //Pnl.add(CbxNurMitTitel);
        /*g.addLabel(Pnl,"nur alter Druck");
        Pnl.add(new JLabel());
        Pnl.add(CbxErsteSpalteFett);
        Pnl.add(CbxUeberschriftLinks);
        Pnl.add(CbxEinzeldaten);
        Pnl.add(CbxKeinTitel);
        Pnl.add(new JLabel());*/
        JPanel PnlCenter=new JPanel(new BorderLayout());
        PnlCenter.add("North",Pnl);
        PnlCenter.add("Center",MemoAbschnitt);
	FrmAbschnitt.getContentPane().add("Center",PnlCenter);
	Pnl = new JPanel(new GridLayout(1,0,2,2));
	Pnl.add(BtnAbschnittOk);
	Pnl.add(BtnAbschnittAbbruch);
	FrmAbschnitt.getContentPane().add("South",Pnl);
	//FrmAbschnitt.pack();
        FrmAbschnitt.setSize(350,800);
}

public void show()
{
	FuelleOutliner();
	super.show();
}

private void showInfo()
{
        JCOutlinerNode[] NodeBegriff=OutDruck.getSelectedNodes();
        if (NodeBegriff != null && NodeBegriff[0].getLevel()==1)
        {
          Vector<Object> Vec = new Vector<Object>();
          for (int i = 0; i < NodeBegriff.length; i++)
          {
            Vec.addElement(((Vector)NodeBegriff[i].getUserData()).elementAt(0));
            //g.progInfo("******************* Show:" + Obj);
          }
          JFrame DlgTab=new JFrame("Druck");
          JTabbedPane Pane = new JTabbedPane();//TODO Prog-Bez richtig holen
          Pane.add("Module",g.getGid(null/*1*/,g,"select b.defbezeichnung Modul,b2.defBezeichnung Druck"+g.AU_Bezeichnung1("Code", "b")+" Programm from begriff b join begriff_zuordnung z on z.aic_begriff=b.aic_begriff"+
                      " and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul")+" join begriff b2 on z.beg_aic_begriff=b2.aic_begriff and z.beg_aic_begriff" +Static.SQL_in(Vec)));
          Pane.add("Hauptschirme",g.getGid(null/*2*/,g,"select b.aic_begriff,b.defbezeichnung,b.kennung,b2.defBezeichnung Druck from begriff b join begriff_zuordnung z on z.beg_aic_begriff=b.aic_begriff"+
                      " and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Applikation")+" join begriff b2 on z.aic_begriff=b2.aic_begriff and z.aic_begriff"+Static.SQL_in(Vec)));
          Pane.add("Formulare",g.getGid(null/*3*/,g,"select b.aic_begriff,b.defbezeichnung,b.kennung"+g.AU_Bezeichnung1("Stammtyp","b")+" Stammtyp,b2.defBezeichnung Druck"+
                      " from begriff b join begriff_zuordnung z on z.beg_aic_begriff=b.aic_begriff and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame")+
                      " join begriff b2 on z.aic_begriff=b2.aic_begriff and z.aic_begriff"+Static.SQL_in(Vec)));
          DlgTab.getContentPane().add("Center",Pane);
          DlgTab.setSize(700,400);
          Static.centerComponent(DlgTab,thisFrame);
          DlgTab.setVisible(true);
        }
        NodeBegriff=OutAbschnitt.getSelectedNodes();
        if (NodeBegriff != null && NodeBegriff[0].getLevel()>0)
        {
          Vector<Object> Vec = new Vector<Object>();
          for (int i = 0; i < NodeBegriff.length; i++)
          {
            Vec.addElement(((Vector)NodeBegriff[i].getUserData()).elementAt(0));
            //g.progInfo("******************* Show:" + Obj);
          }
          new Tabellenspeicher(g,"select a.kennung Abschnitt,defbezeichnung Druck from abschnitt a"+g.join("druck_zuordnung","a","abschnitt")+g.join("begriff","druck_zuordnung")+" where a.aic_abschnitt" +
                               Static.SQL_in(Vec), true).showGrid("Drucke der Abschnitte");
        }
      }

private void FuelleOutliner()
{
  long lClock = Static.get_ms();
	CboStammtyp.fillDefTable("Stammtyp",false);
    CboRolle.fillRolle(-1,true);
	CboBewegungstyp.fillDefTable("Bewegungstyp",true);
	CboModell.fillCboM(-3);
//    CboModell.Cbo.fillCbo("select aic_begriff,kennung,defBezeichnung Bezeichnung from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell")+" and "+g.bit("bits",Global.cstDruckZR),"begriff",true,false);
    CboAbfrage.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("abits2",Abfrage.cstModellDlg),"Abfrage",true);
	CboAbfragen.fillCbo("select begriff.aic_begriff,kennung,defBezeichnung Bezeichnung from begriff"+g.join2("abfrage","begriff")+" where "+g.bit("bits",Abfrage.cstAuswertung),"begriff",false);

	VecAenderung = new Vector<Integer>();
	((JCOutlinerFolderNode)OutDruck.getRootNode()).removeChildren();
        OutDruck.folderChanged(OutDruck.getRootNode());
	((JCOutlinerFolderNode)OutAbschnitt.getRootNode()).removeChildren();
        OutAbschnitt.folderChanged(OutAbschnitt.getRootNode());
        thisFrame.invalidate();
        thisFrame.repaint();
        Tabellenspeicher TabRaster=new Tabellenspeicher(g,"select aic_raster,aic_schrift from raster",true);
	SQL Qry = new SQL(g);
	Clock.add(clock,"CboAbfragen","",lClock);
        lClock = Static.get_ms();
	//Outliner Druck
	if(Qry.open("select d.bits,d.web,reihenfolge,d.kennung druck_kennung,d.kennzeichen druck_kennzeichen,d.defbezeichnung druck_defbez"+g.AU_Bezeichnung("Begriff","d")+" druck_bezeichnung,d.prog,a.kennung abschnitt_kennung"+g.AU_Bezeichnung("Abschnitt","a")+" abschnitt_bezeichnung"+
                    ",af.aic_stammtyp af_stt,af.aic_bewegungstyp af_bew,a.aic_raster,(select ein from logging where aic_logging=d.aic_logging) Log"+
                    ",d.aic_stammtyp,d.aic_rolle,d.aic_bewegungstyp,d.aic_begriff,d.aic_schrift,a.aic_abschnitt,a.bits abits FROM Begriffgruppe bg"+g.join("Begriff","d","bg","Begriffgruppe")+" left outer"+g.join("Druck_Zuordnung","d","Begriff")+" left outer"+g.join("Abschnitt","a","Druck_Zuordnung","Abschnitt")+" left outer"+g.join("Begriff","af","a","Begriff")+" where bg.kennung='Druck'"+(iStt>0?" and d.aic_stammtyp="+iStt:"")+" order by druck_defbez,d.aic_begriff,reihenfolge"))
	{
		JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)OutDruck.getRootNode();

		Vector<Object> VecVisible;
		Vector<Number> VecInvisible;
		String sDruck_Kennung = null;
		JCOutlinerFolderNode NodeDruck = null;
		int iReihenfolge = 0;

		for(;!Qry.eof();Qry.moveNext())
		{
			if(!Qry.getS("druck_kennung").equals(sDruck_Kennung))
			{
				sDruck_Kennung=Qry.getS("druck_kennung");
				VecVisible = new Vector<Object>();
				VecInvisible = new Vector<Number>();

				VecVisible.addElement(Static.beiLeer(Qry.getS("druck_bezeichnung"),Qry.getS("druck_defbez")));
				VecVisible.addElement(Qry.getS("druck_kennung"));
				VecVisible.addElement((Qry.getL("bits")&Drucken.cstPntKeinProg)>0 ? Static.sKein:g.TabCodes.getBezeichnungS(Qry.getI("prog")));
				VecVisible.addElement(Static.JaNein2(Qry.getB("web"))); // Web
				VecVisible.addElement(Static.JaNein2((Qry.getL("bits")&Drucken.cstPntPers)>0));
				VecVisible.addElement(Qry.isNull("aic_bewegungstyp")?CboStammtyp.getBezeichnung(Qry.getI("aic_stammtyp")):"*"+CboBewegungstyp.getBezeichnung(Qry.getI("aic_bewegungstyp")));
				VecVisible.addElement(null); // Art
                VecVisible.addElement(CboSchrift.getBezeichnung(Qry.getI("aic_schrift")));
                VecVisible.addElement(Qry.isNull("log")?null:new Zeit(Qry.getD("log"),"dd.MM.yyyy"));
                VecVisible.addElement(Qry.isNull("aic_rolle") ? "":g.TabRollen.getBezeichnungS(Qry.getI("aic_rolle")));
                VecVisible.addElement(Qry.getS("druck_kennzeichen"));
                VecVisible.addElement(Qry.getS("druck_defbez"));
				VecInvisible.addElement(Qry.getInt("aic_begriff"));
				VecInvisible.addElement(Qry.getInt("aic_stammtyp"));
				VecInvisible.addElement(Qry.getL("bits"));
				VecInvisible.addElement(Qry.getI("aic_bewegungstyp"));
				VecInvisible.addElement(Qry.getI("aic_schrift"));
                                VecInvisible.addElement(new Integer(Qry.getI("aic_Rolle")));

				NodeDruck = new JCOutlinerFolderNode((Object)VecVisible,NodeRoot);
				NodeDruck.setUserData(VecInvisible);

				NodeDruck.setStyle(/*(Qry.getI("bits")&Drucken.cstPntNeuerDruck)==0 ? StyDruckAlt:*/StyDruck);

                                if (Qry.getI("aic_begriff")==iDruck)
                                {
                                  thisFrame.setVisible(true);
                                  Static.makeVisible(OutDruck,NodeDruck);
                                }
                                else
                                  NodeDruck.setState(BWTEnum.FOLDER_CLOSED);
				iReihenfolge = 0;
			}

			if(Qry.getI("aic_abschnitt")>0)
			{
				for(;iReihenfolge+1<Qry.getI("reihenfolge");iReihenfolge++)
					Seperator(NodeDruck,false);

				iReihenfolge=Qry.getI("reihenfolge");

				VecVisible = new Vector<Object>();
				VecVisible.addElement(Static.beiLeer(Qry.getS("abschnitt_bezeichnung"),Qry.getS("abschnitt_kennung")));
				VecVisible.addElement(Qry.getS("abschnitt_kennung"));
				VecVisible.addElement(null);
				VecVisible.addElement(null);
				VecVisible.addElement(null);
                VecVisible.addElement(Qry.isNull("af_bew")?Qry.isNull("af_stt")?"":CboStammtyp.getBezeichnung(Qry.getI("af_stt")):"*"+CboBewegungstyp.getBezeichnung(Qry.getI("af_bew")));
                VecVisible.addElement(getAbArt(Qry.getI("abits")));
                                if (TabRaster.posInhalt("aic_raster",Qry.getI("aic_raster")))
                                  VecVisible.addElement(CboSchrift.getBezeichnung(TabRaster.getI("aic_schrift")));
                                //VecVisible.addElement(Static.JaNein(!Qry.isNull("aic_raster")));
				JCOutlinerNode NodeAbschnitt = new JCOutlinerNode(VecVisible,NodeDruck);
				NodeAbschnitt.setUserData(Qry.getInt("aic_abschnitt"));
                                NodeAbschnitt.setStyle((Qry.getI("abits")&Drucken.cstNichtDrucken)>0 ? g.setColor(StyAbschnitt,g.ColLoeschen):StyAbschnitt);
			}

		}
                OutDruck.folderChanged(NodeDruck);
		Qry.close();
	}
        else
          Static.printError("Druckdefinition.FuelleOutliner: Druck-Outliner nicht gefüllt");
        iStt=0;
        Clock.add(clock,"Outliner Druck","",lClock);
        lClock = Static.get_ms();
	//Outliner Abschnitt
        String s="select kennung"+g.AU_Bezeichnung2("Abschnitt")+" Bezeichnung,kennzeichen,bits,aic_abschnitt,aic_begriff,aic_raster,zahl"+g.AU_Memo("Abschnitt")+
                ",(select count(*) from druck_zuordnung where aic_abschnitt=abschnitt.aic_abschnitt) Anzahl"+
        	g.AU_Bild2("Abschnitt")+" Filename from abschnitt order by Bezeichnung";
        //g.progInfo(s);
	if(Qry.open(s))
	{
		JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)OutAbschnitt.getRootNode();
		for(;!Qry.eof();Qry.moveNext())
		{
			Vector<Comparable> VecVisible = new Vector<Comparable>();
			Vector<Comparable> VecInvisible = new Vector<Comparable>();

			VecVisible.addElement(Static.beiLeer(Qry.getS("Bezeichnung"),Qry.getS("Kennung")));
			VecVisible.addElement(Qry.getS("Kennung"));
                        VecVisible.addElement(Qry.getS("Kennzeichen"));
                        VecVisible.addElement(Qry.getInt("aic_abschnitt"));
                        VecVisible.addElement(Qry.getInt("Anzahl"));
                        int iPos=Qry.getI("aic_begriff")>0 ? g.TabBegriffe.getPos("Aic",Qry.getI("aic_begriff")):-2;
			//VecVisible.addElement(CboAbfragen.getBezeichnung(Qry.getI("aic_begriff")));
			VecVisible.addElement(iPos<0 ?"":g.getBegBez2(iPos));
			//VecVisible.addElement(CboSchriftTitel.getBezeichnung(Qry.getI("aic_schrift")));
			//VecVisible.addElement(CboSchriftText.getBezeichnung(Qry.getI("sch_aic_schrift")));
			VecVisible.addElement(CboVorlagen.getBezeichnung(Qry.getI("aic_raster")));
                        //g.testInfo("iPos="+iPos+"->"+g.TabBegriffe.getI(iPos,"Erf")+"/"+g.TabBegriffe.getI(iPos,"Stt"));
                        VecVisible.addElement(iPos<0 ?"":g.TabBegriffe.getI(iPos,"Erf")>0 ? "Bew "+g.TabErfassungstypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Erf")):
                                              g.TabBegriffe.getI(iPos,"Stt")==0 ? Static.sKein:"Stt "+g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt")));
			/*VecVisible.addElement((Qry.getI("bits")&Drucken.cstUeberschrift)!=0?Static.sJa:Static.sNein);
			VecVisible.addElement((Qry.getI("bits")&Drucken.cstUnterschrift)!=0?Static.sJa:Static.sNein);
			VecVisible.addElement((Qry.getI("bits")&Drucken.cstTitelDarunter)!=0?Static.sJa:Static.sNein);
			VecVisible.addElement((Qry.getI("bits")&Drucken.cstErsteFett)!=0?Static.sJa:Static.sNein);
			VecVisible.addElement((Qry.getI("bits")&Drucken.cstEigZeilen)!=0?Static.sJa:Static.sNein);
			VecVisible.addElement((Qry.getI("bits")&Drucken.cstKeinTitel)!=0?Static.sJa:Static.sNein);*/

			VecInvisible.addElement(Qry.getInt("aic_abschnitt"));
			VecInvisible.addElement(Qry.getInt("aic_begriff"));
			VecInvisible.addElement(new Double(Qry.getF("Zahl")));
			VecInvisible.addElement(Static.Int0);
			VecInvisible.addElement(Qry.getInt("bits"));
			VecInvisible.addElement(new Integer(Qry.getI("aic_raster")));
                        VecInvisible.addElement(Qry.getS("Memo"));
                        VecInvisible.addElement(Qry.getS("Filename"));

			JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeRoot);
			Node.setUserData(VecInvisible);
			Node.setStyle((Qry.getI("bits")&Drucken.cstNichtDrucken)>0 ? g.setColor(StyAbschnitt,g.ColLoeschen):StyAbschnitt);
		}
		Qry.close();
	}
        else
          Static.printError("Druckdefinition.FuelleOutliner: Abschnitt-Outliner nicht gefüllt");
	Clock.add(clock,"Outliner Abschnitt","",lClock);
	Qry.free();
}

private String getAbArt(int iABits)
{
	/*
	 * addBitSS(Tab2, "cstNormal","Normalabschnitt",          Drucken.cstArt,Drucken.cstNormal,11,1);
    addBitSS(Tab2, "cstDiagramm","-",                      Drucken.cstArt,Drucken.cstDiagramm,11,2);
    addBitSS(Tab2, "cstIstUeberschrift","ist Ueberschrift",Drucken.cstArt,Drucken.cstIstUeberschrift,11,3);
    addBitSS(Tab2, "cstFixPos","Pos fixieren",             Drucken.cstArt,Drucken.cstFixPos,11,4);
    addBitSS(Tab2, "cstBedingung","Bedingung",             Drucken.cstArt,Drucken.cstBedingung,11,5);
    addBitSS(Tab2, "cstSchicht2","Schicht2",               Drucken.cstArt,Drucken.cstSchicht2,11,6);
    addBitSS(Tab2, "cstSchichtTMZ","SchichtTMZ",           Drucken.cstArt,Drucken.cstSchichtTMZ,11,7);
    addBitSS(Tab2, "cstEMail_A","E-Mail-Adressen",         Drucken.cstArt,Drucken.cstEMail_A,11,8);
    addBitSS(Tab2, "cstEMail_D","E-Mail-Daten",            Drucken.cstArt,Drucken.cstEMail_D,11,9);
    addBitSS(Tab2, "cstEMail_S","E-Mail-Signatur",         Drucken.cstArt,Drucken.cstEMail_S,11,10);
    addBitSS(Tab2, "cstHtmlDirekt","html-direkt",          Drucken.cstArt,Drucken.cstHtmlDirekt,11,11);
	 */
	int i=iABits&Drucken.cstArt;
	return i==Drucken.cstNormal ? "-":i==Drucken.cstIstUeberschrift ? "ist Ueberschrift":i==Drucken.cstFixPos ? "Pos fixieren":i==Drucken.cstBedingung ? "Bedingung":i==Drucken.cstSchicht2 ? "Schicht2":i==Drucken.cstSchichtTMZ ? "SchichtTMZ"
			:i==Drucken.cstEMail_A ? "E-Mail-Adressen":i==Drucken.cstEMail_D ? "E-Mail-Daten": i==Drucken.cstEMail_S ? "E-Mail-Signatur":i==Drucken.cstHtmlDirekt ? "html-direkt":i==Drucken.cstPDF_Save ? "PDF-Save":i==Drucken.cstPDF_Direkt ? "PDF-Direkt":"?";
}

private void Hinzufuegen()
{
	JCOutlinerNode[] NodesD = OutDruck.getSelectedNodes();
	JCOutlinerNode[] NodesA = OutAbschnitt.getSelectedNodes();

	for(int j=0;j<NodesD.length;j++)
	{
		for(int i=0;i<NodesA.length;i++)
		{
			jclass.util.JCVector VecVisible = new jclass.util.JCVector((Vector)NodesA[i].getLabel());
			//Vector VecInvisible = new Vector((Vector)NodesA[i].getUserData());

			VecVisible.removeElementsAt(2,7);
			//VecInvisible.removeElementsAt(1,4);

			JCOutlinerNode Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)NodesD[j]);
			Node.setUserData(((Vector)NodesA[i].getUserData()).elementAt(0));
		}
		Aenderung((Integer)((Vector)NodesD[j].getUserData()).elementAt(0));
	}

	OutDruck.folderChanged(OutDruck.getRootNode());
}

private void Ersetzen()
{
  JCOutlinerNode NodesD = OutDruck.getSelectedNode();
  JCOutlinerNode NodesA = OutAbschnitt.getSelectedNode();
  NodesD.setLabel(new jclass.util.JCVector((Vector)NodesA.getLabel()));
    NodesD.setUserData(((Vector)NodesA.getUserData()).elementAt(0));
    //change(NodeZ.getParent());
    //setColor(NodeZ,ColHinzu);
    //checkReihenfolge(NodeZ.getParent());
    OutDruck.folderChanged(NodesD.getParent());
    Aenderung((Integer)((Vector)NodesD.getParent().getUserData()).elementAt(0));
}

private void Entfernen()
{
	JCOutlinerNode[] NodesA = OutDruck.getSelectedNodes();

	for(int i=0;i<NodesA.length;i++)
	{
          if (i==0)
            OutDruck.selectNode(OutDruck.getPreviousNode(NodesA[i]),null);
          NodesA[i].getParent().removeChild(NodesA[i]);
          Aenderung((Integer)((Vector)NodesA[i].getParent().getUserData()).elementAt(0));
	}

	OutDruck.folderChanged(OutDruck.getRootNode());
}

@SuppressWarnings("unchecked")
private void Rauf_Runter(boolean bRauf)
{
	JCOutlinerNode[] NodesA = OutDruck.getSelectedNodes();

	Vector<JCOutlinerNode> VecNodes = null;
	if(NodesA.length>0)
	{
		VecNodes = NodesA[0].getParent().getChildren();
		Aenderung((Integer)((Vector)NodesA[0].getParent().getUserData()).elementAt(0));
	}

	for(int i=0;VecNodes!=null && i<NodesA.length;i++)
	{
		int iPos = VecNodes.indexOf(NodesA[i]);
		if(iPos>-1 && (bRauf?iPos>0:iPos<VecNodes.size()-1))
		{
			VecNodes.removeElementAt(iPos);
			VecNodes.insertElementAt(NodesA[i],iPos+(bRauf?-1:1));
		}
	}

	OutDruck.folderChanged(OutDruck.getRootNode());
}

private void Seperator(JCOutlinerFolderNode NodeDruck,boolean bChange)
{
	Vector<String> VecVisible = new Vector<String>();
	VecVisible.addElement("----------");
	VecVisible.addElement("----------");
	JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeDruck);
	Node.setUserData(new Integer(0));
        if (bChange)
          Aenderung((Integer)((Vector)NodeDruck.getUserData()).elementAt(0));
}

private void Save()
{
	SQL Qry = new SQL(g);
	Vector VecDruck = OutDruck.getRootNode().getChildren();

	int iAIC_Druck = 0;
	for(int i=0;VecDruck!=null && i<VecDruck.size();i++)
	{
		JCOutlinerFolderNode NodeDruck = (JCOutlinerFolderNode)VecDruck.elementAt(i);
		iAIC_Druck=((Integer)((Vector)NodeDruck.getUserData()).elementAt(0)).intValue();

		if(VecAenderung.contains(new Integer(iAIC_Druck)))
		{
			g.progInfo("NodeDruck: "+NodeDruck);
			Qry.exec("delete from druck_zuordnung where aic_begriff="+iAIC_Druck);
			Vector VecAbschnitt = NodeDruck.getChildren();

			for(int j=0;VecAbschnitt!=null && j<VecAbschnitt.size();j++)
			{
				g.progInfo("NodeAbschnitt: "+VecAbschnitt.elementAt(j));
				if(((Integer)((JCOutlinerNode)VecAbschnitt.elementAt(j)).getUserData()).intValue()>0)
				{
					Qry.add("AIC_Begriff",iAIC_Druck);
					Qry.add("AIC_Abschnitt",(Integer)((JCOutlinerNode)VecAbschnitt.elementAt(j)).getUserData());
					Qry.add("Reihenfolge",j+1);
					Qry.insert("Druck_Zuordnung",false);
				}
			}
		}
	}
}

private void Aenderung(Integer i)
{
	if(!VecAenderung.contains(i))
		VecAenderung.addElement(i);
}

private void EnableButtons()
{
	JCOutlinerNode[] NodesD = OutDruck.getSelectedNodes();
	JCOutlinerNode[] NodesA = OutAbschnitt.getSelectedNodes();

	BtnEditPrint.setEnabled(NodesD.length==1 && NodesD[0].getLevel()==1);
	BtnDeletePrint.setEnabled(g.Def() && NodesD.length>0 && NodesD[0].getLevel()==1);
	BtnEditSegment.setEnabled(NodesA.length==1 && NodesA[0].getLevel()==1);
	BtnDeleteSegment.setEnabled(g.Def() && NodesA.length>0 && NodesA[0].getLevel()==1);
    BtnCopySegment.setEnabled(g.Def() && NodesA.length>0 && NodesA[0].getLevel()==1);
	BtnHinzufuegen.setEnabled(g.Def() && NodesA.length>0 && NodesA[0].getLevel()==1 && NodesD.length>0 && NodesD[0].getLevel()==1);
	BtnEntfernen.setEnabled(g.Def() && NodesD.length>0 && NodesD[0].getLevel()==2);
	BtnRauf.setEnabled(g.Def() && NodesD.length>0 && NodesD[0].getLevel()==2);
    BtnErsetzen.setEnabled(g.Def() && NodesA.length>0 && NodesA[0].getLevel()==1 && NodesD.length>0 && NodesD[0].getLevel()==2);
	BtnRunter.setEnabled(g.Def() && NodesD.length>0 && NodesD[0].getLevel()==2);
	BtnSeperator.setEnabled(g.Def() && NodesD.length>0 && NodesD[0].getLevel()==1);
        BtnSpeichern.setEnabled(g.Def());
	BtnDruckOk.setEnabled(g.Def() && !TxtDruckKennung.getText().equals(""));
	BtnAbschnittOk.setEnabled(!TxtAbschnittKennung.getText().equals(""));
}

      private void NeuerDruck()
      {
        bNeu = true;
        TxtDruckBezeichnung.setText("");
        TxtDruckDefBezeichnung.setText("");
        TxtDruckKennzeichen.setText("");
        TxtDruckKennung.setText("");
        TxtDruckKennung.setEnabled(true);
        MemoDruck.setText("");
        TooltipDruck.setText("");
        CbxPntKeinKopfFuss.setSelected(false);
        CbxPntStammtitelLinks.setSelected(false);
        CbxPntUnterschrift.setSelected(false);
        CbxPntKeinDrucktitel.setSelected(false);
        CbxPntTagesanzeige.setSelected(false);
        CbxPntKeinStammtitel.setSelected(false);
        CbxPntKeinZRtitel.setSelected(false);
        CbxPntPdfSave.setSelected(false);
        CbxEMail.setSelected(false);
        CbxPntMenge.setSelected(false);
        CbxPntBewZR.setSelected(false);
        CbxNurSummeD.setSelected(false);
        CbxKeineSummeD.setSelected(false);
        CbxED.setSelected(false);
        CbxPntKeineGruppierung.setSelected(false);
        CbxPntQuerformat.setSelected(false);
        CbxPntPers.setSelected(false);
        CbxPntDrucktitelLinks.setSelected(false);
        //CbxPntNeuerDruck.setSelected(true);
        //CbxPntBewegung.setSelected(false);
        CbxPntSchicht.setSelected(false);
        CbxPntPeriode.setSelected(false);
        CbxPntSeitenumbruch.setSelected(false);
        CbxPntDirekt.setSelected(false);
        CbxPntUseSync.setSelected(false);
        CbxPntNewYear.setSelected(false);
        CbxPntKnopf.setSelected(false);
        CbxPntExport.setSelected(false);
        CbxPntHtml.setSelected(false);
        CbxWeb.setSelected(false);
        CbxTod.setSelected(false);
        CbxFarbe.setSelected(false);
        RadOffen.setSelected(true);
        CboBewegungstyp.setSelectedAIC(0);
        CboModell.setSelectedAIC(0);
        CboAbfrage.setSelectedAIC(0);
        CboSchrift.setSelectedAIC(0);
        LblAic.setText("");
        LblChanged.setText(""+new Zeit(new Date(),null));
        Static.centerComponent(FrmDruck,thisFrame);
        EnableButtons();
        thisJFrame().setEnabled(false);
        FrmDruck.setVisible(true);
      }

      private void EditDruck(boolean bCopy)
      {
        bNeu=bCopy;
        TxtDruckKennung.setEnabled(bCopy);
        JCOutlinerNode Node = OutDruck.getSelectedNodes()[0];
        if (bCopy)
          NodCopy=(JCOutlinerFolderNode)Node;
        Vector VecVisible = (Vector)Node.getLabel();
        Vector VecInvisible = (Vector)Node.getUserData();
        int iBegriff=Sort.geti(VecInvisible,0);
        String sDefBez=(bCopy ? "Kopie ":"")+(String)VecVisible.elementAt(11);
        TxtDruckDefBezeichnung.setText(sDefBez);
        TxtDruckKennung.setText(Sort.gets(VecVisible,1));
        TxtDruckKennzeichen.setText(Sort.gets(VecVisible, 10));
        //g.fixtestInfo("Druck "+sDefBez+":"+iBegriff+"-> Code="+g.TabBegriffe.getI_AIC("Typ", iBegriff));
        CboProg.setSelectedAIC(g.TabBegriffe.getI_AIC("Prog", iBegriff));
        CboStammtyp.setSelectedAIC(((Integer)VecInvisible.elementAt(1)).intValue());
        CboBewegungstyp.setSelectedAIC(((Integer)VecInvisible.elementAt(3)).intValue());
        CboModell.setSelectedAIC(SQL.getInteger(g,"select beg_aic_begriff from begriff_zuordnung z join begriff b on z.beg_aic_begriff=b.aic_begriff and z.aic_begriff="+iBegriff+
                                                " and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell")));
        CboAbfrage.setSelectedAIC(SQL.getInteger(g,"select aic_abfrage from abfrage join begriff_zuordnung z on z.beg_aic_begriff=abfrage.aic_begriff join begriff b on z.beg_aic_begriff=b.aic_begriff and z.aic_begriff="+iBegriff+
                " and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")));
        LblAic.setText(""+iBegriff);
        LblChanged.setText(""+new Zeit(SQL.getTimestamp(g, "select l.ein from logging l join begriff d on l.aic_logging=d.aic_logging and d.aic_begriff="+iBegriff),null));
        CboSchrift.setSelectedAIC(((Integer)VecInvisible.elementAt(4)).intValue());
        CboRolle.setSelectedAIC(((Integer)VecInvisible.elementAt(5)).intValue());
        long iPntBits = ((Long)VecInvisible.elementAt(2)).longValue();
        CbxPntKeinKopfFuss.setSelected((iPntBits & Drucken.cstPntKeinKopfFuss) > 0);
        CbxPntStammtitelLinks.setSelected((iPntBits & Drucken.cstPntStammLinks) > 0);
        CbxPntUnterschrift.setSelected((iPntBits & Drucken.cstPntUnterschrift) > 0);
        CbxPntKeinDrucktitel.setSelected((iPntBits & Drucken.cstPntKeinDrucktitel) > 0);
        CbxPntTagesanzeige.setSelected((iPntBits & Drucken.cstPntTagesanzeige) > 0);
        CbxPntKeinStammtitel.setSelected((iPntBits & Drucken.cstPntKeinStammtitel) > 0);
        CbxPntKeinZRtitel.setSelected((iPntBits & Drucken.cstPntKeinZRtitel) > 0);
        CbxPntPdfSave.setSelected((iPntBits & Drucken.cstPntPdfSave) > 0);
        CbxEMail.setSelected((iPntBits & Drucken.cstPntEMail) > 0);
        CbxPntMenge.setSelected((iPntBits & Drucken.cstPntMenge) > 0);
        CbxPntBewZR.setSelected((iPntBits & Drucken.cstPntBewZR) > 0);
        CbxPntKeineGruppierung.setSelected((iPntBits & Drucken.cstPntKeineGruppierung) > 0);
        CbxPntQuerformat.setSelected((iPntBits & Drucken.cstPntQuerformat) > 0);
        CbxPntPers.setSelected((iPntBits & Drucken.cstPntPers) > 0);
        CbxPntDrucktitelLinks.setSelected((iPntBits & Drucken.cstPntDrucktitelLinks) > 0);
        //CbxPntNeuerDruck.setSelected((iPntBits & Drucken.cstPntNeuerDruck) > 0);
        //CbxPntBewegung.setSelected((iPntBits&Drucken.cstPntBewegung)>0);
        CbxPntSchicht.setSelected((iPntBits & Drucken.cstPntSchicht) > 0);
        CbxPntPeriode.setSelected((iPntBits & Drucken.cstPntPeriode) > 0);
        CbxPntSeitenumbruch.setSelected((iPntBits & Drucken.cstPntSeitenumbruch) > 0);
        CbxPntDirekt.setSelected((iPntBits & Drucken.cstPntDirekt) > 0);
        CbxPntUseSync.setSelected((iPntBits & Drucken.cstPntUseSync) > 0);
        CbxPntNewYear.setSelected((iPntBits & Drucken.cstPntNewYear) > 0);
        CbxPntKnopf.setSelected((iPntBits & Drucken.cstPntKnopf) > 0);
        CbxPntExport.setSelected((iPntBits & Drucken.cstPntExport) > 0);
        CbxPntHtml.setSelected((iPntBits & Drucken.cstPntHtml) > 0);
        CbxKeinProg.setSelected((iPntBits & Drucken.cstPntKeinProg) > 0);
        RadOffen.setSelected((iPntBits & Drucken.cstPntZeitart) == 0);
        RadTag.setSelected((iPntBits & Drucken.cstPntZeitart) == Drucken.cstPntZaTag);
        RadWoche.setSelected((iPntBits & Drucken.cstPntZeitart) == Drucken.cstPntZaWoche);
        RadMonat.setSelected((iPntBits & Drucken.cstPntZeitart) == Drucken.cstPntZaMonat);
        RadQuartal.setSelected((iPntBits & Drucken.cstPntZeitart) == Drucken.cstPntZaQuartal);
        RadJahr.setSelected((iPntBits & Drucken.cstPntZeitart) == Drucken.cstPntZaJahr);
        Vector Vec = g.getMemoVector("BEGRIFF",iBegriff);// ((Integer)VecInvisible.elementAt(0)).intValue());
        int iPos=g.TabBegriffe.getPos("Aic",iBegriff);
        String sBez=iPos<0 ? "":g.TabBegriffe.getS(iPos,"Bezeichnung");
        CbxWeb.setSelected(iPos<0 ? false:g.TabBegriffe.getB(iPos,"Web"));
        CbxTod.setSelected(iPos<0 ? false:g.TabBegriffe.getB(iPos,"Tod"));
        CbxFarbe.setSelected((iPntBits & Drucken.cstPntFarbe) > 0);
        CbxNurSummeD.setSelected((iPntBits & Drucken.cstPntNurSumme) > 0);
        CbxKeineSummeD.setSelected((iPntBits & Drucken.cstPntKeineSumme) > 0);
        CbxED.setSelected((iPntBits & Drucken.cstPntED) > 0);
        TxtDruckBezeichnung.setText(sDefBez.equals(sBez) ? "":sBez);
        MemoDruck.setText(Vec.elementAt(1).toString());
        TooltipDruck.setText(g.TabBegriffe.getM(iPos,"Tooltip"));
        Static.centerComponent(FrmDruck, thisFrame);
        EnableButtons();
        thisJFrame().setEnabled(false);
        FrmDruck.setVisible(true);
      }
      
      private void addBegToTab(Vector<Integer> Vec,Tabellenspeicher Tab)
      {
    	  for (int i=0;i<Vec.size();i++)
    	  {
    		  int iPos=g.TabBegriffe.getPos("Aic",Sort.geti(Vec, i));
    		  if (iPos>0)
    		  {
    			  int iGruppe=g.TabBegriffe.getI(iPos,"Gruppe");
    			  Tab.addInhalt("Art", g.TabBegriffgruppen.getBezeichnungS(iGruppe));
    			  Tab.addInhalt("Bezeichnung", g.getBegBez3(iPos));
    			  int iStt=g.TabBegriffe.getI(iPos,"Stt");
    			  Tab.addInhalt("Stt",iStt<1 ? "-":g.TabStammtypen.getBezeichnungS(iStt));
    			  int iTyp=g.TabBegriffe.getI(iPos,"Typ");
    			  Tab.addInhalt("Prog",iTyp<1 ? "":g.TabCodes.getBezeichnungS(iTyp));
    		  }
    	  }
      }

      private void DeleteDruck()
      {
              JCOutlinerNode[] Nodes = OutDruck.getSelectedNodes();
              //String sSelected = "";
              //for(int i=0;i<Nodes.length;i++)
              //        sSelected+=(sSelected.equals("")?"":",")+((String)((Vector)Nodes[i].getLabel()).elementAt(0));
              //int iMessage = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",new String[]{"<"+sSelected+">"});
              //if(iMessage==Message.YES_OPTION)
              //{
            	      for(int i=0;i<Nodes.length;i++)
                      {
            	    	  Tabellenspeicher Tab=new Tabellenspeicher(g, new String[] {"Art","Bezeichnung","Stt","Prog"});                         
                              int iAIC_Druck = ((Integer)((Vector)Nodes[i].getUserData()).elementAt(0)).intValue();
                              SQL Qry=new SQL(g);
                              Vector<Integer> Vec=SQL.getVector("select distinct formular.aic_begriff from darstellung d"+g.join("formular","d")+" where d.aic_begriff="+iAIC_Druck, g);
                              addBegToTab(Vec,Tab);
                              Vec=SQL.getVector("select distinct z.aic_begriff from begriff_zuordnung z join begriff on z.beg_aic_begriff=begriff.aic_begriff"+" where begriff.aic_begriff="+iAIC_Druck, g);
                              addBegToTab(Vec,Tab);
//                              String s2=Qry.list("b2.defbezeichnung","begriff"+g.join2("darstellung","begriff")+g.join("formular","darstellung")+g.join("begriff","b2","formular","begriff")+" where begriff.aic_begriff="+iAIC_Druck);
//                              String s3=Qry.list("b2.defbezeichnung","begriff b2 join begriff_zuordnung z on z.aic_begriff=b2.aic_begriff join begriff on z.beg_aic_begriff=begriff.aic_begriff"+" where begriff.aic_begriff="+iAIC_Druck);

                              //String s=(s2.equals(" ")?"":"\nFormulare:"+s2);
                              //if (!s2.equals(" ") || !s3.equals(" "))
                              if (Tab.size()>0)
                                new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB,null,g).showDialog("BereitsVerwendet",new String[] {Sort.gets(Nodes[i].getLabel(),0)},Tab);
                              else if(new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",new String[]{Sort.gets(Nodes[i].getLabel(),0)})==Message.YES_OPTION)
                              {
                                boolean b=Static.bTest;
                                Static.bTest=false;
                                g.TabBegriffe.clearInhalt("aic", new Integer(iAIC_Druck));
                                Static.bTest=b;
                                Qry.exec("delete from druck_zuordnung where aic_begriff=" + iAIC_Druck);
                                Qry.exec("delete from begriff_zuordnung where aic_begriff=" + iAIC_Druck);
                                Qry.exec("delete from begriff_zuordnung where beg_aic_begriff=" + iAIC_Druck);
                                Qry.deleteFrom("begriff","begriff where aic_begriff=" + iAIC_Druck,g.iTabBegriff);
                                Nodes[i].getParent().removeChild(Nodes[i]);
                              }
                              Qry.free();
                      }

                      OutDruck.folderChanged(OutDruck.getRootNode());
              //}
      }

      private void NeuerAbschnitt()
      {
        bNeu=true;
        TxtAbschnittBezeichnung.setText("");
        TxtAbschnittKennung.setText("");
        TxtAbschnittKennzeichen.setText("");
        TxtAbschnittKennung.setEnabled(true);
        //CboSchriftTitel.setSelectedAIC(0);
        //CboSchriftText.setSelectedAIC(0);
        /*CbxUeberschrift.setSelected(true);
        //CbxTitelDarunter.setSelected(false);
        //CbxErsteSpalteFett.setSelected(false);
        //CbxEinzeldaten.setSelected(false);
        //CbxKeinTitel.setSelected(false);
        CbxGesamtsumme.setSelected(false);
        RadLinks.setSelected(true);
        //CbxRechts.setSelected(false);
        //CbxZeilenende.setSelected(false);
        //CbxNebeneinander.setSelected(false);
        //CbxUeberschriftLinks.setSelected(false);
        CbxKeinAbstand.setSelected(false);
        CbxKeineSumme.setSelected(false);
        RadNormal.setSelected(true);
        CbxFixRaster.setSelected(false);
        CbxStrecken.setSelected(false);
        CbxNichtDrucken.setSelected(false);
        CbxSchicht2.setSelected(false);
        CbxBlocksatz.setSelected(false);
        CbxLeere.setSelected(false);
        CbxBedST.setSelected(false);
        CbxTest.setSelected(false);
        CbxNeueSeite.setSelected(false);*/
        MemoAbschnitt.setText("");
        BtnBildAbschnitt.Delete(true);
        Static.centerComponent(FrmAbschnitt,thisFrame);
        EnableButtons();
        thisJFrame().setEnabled(false);
        FrmAbschnitt.setVisible(true);
      }

      private void EditAbschnitt(boolean bCopy)
      {
        bNeu=bCopy;
        TxtAbschnittKennung.setEnabled(bCopy);
        JCOutlinerNode Node = OutAbschnitt.getSelectedNodes()[0];
        Vector VecVisible = (Vector)Node.getLabel();
        Vector VecInvisible = (Vector)Node.getUserData();
        TxtAbschnittBezeichnung.setText(bNeu ? "":(String)VecVisible.elementAt(0));
        TxtAbschnittKennung.setText(bNeu ? "":(String)VecVisible.elementAt(1));
        TxtAbschnittKennzeichen.setText(bNeu ? "":(String)VecVisible.elementAt(2));
        CboAbfragen.setSelectedAIC(Sort.geti(VecInvisible,1));
        EdtZahl.setValue(Sort.getf(VecInvisible.elementAt(2)));
        //CboSchriftTitel.setSelectedAIC(((Integer)VecInvisible.elementAt(2)).intValue());
        //CboSchriftText.setSelectedAIC(((Integer)VecInvisible.elementAt(3)).intValue());
        CbxUeberschrift.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstUeberschrift)>0);
        //CbxTitelDarunter.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstTitelDarunter)>0);
        //CbxErsteSpalteFett.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstErsteFett)>0);
        //CbxEinzeldaten.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstEigZeilen)>0);
        CbxNurEMail.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstNurEMail)>0);
        CbxLtAic.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstLtAic)>0);
        CbxGesamtsumme.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstGesamtsumme)>0);
        CbxKeineHS.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstkeineHauptsumme)>0);
        CbxGruppenRed.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstGruppenRed)>0);
        RadLinks.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstRN)==Drucken.cstLinks);
        RadRechts.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstRN)==Drucken.cstRechts1);
        RadRechts2.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstRN)==Drucken.cstRechts2);
        RadNebeneinander.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstRN)==Drucken.cstNeben);
        //CbxUeberschriftLinks.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstUeberschriftLinks)>0);
        CbxKeinAbstand.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstKeinAbstand)>0);
        CbxKeineSumme.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstKeineSumme)>0);
        CbxFixRaster.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstFixRaster)>0);
        RadNormal.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==0);
        RadFixPos.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstFixPos);
        RadDiagramm.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstDiagramm);
        RadIstUeberschrift.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstIstUeberschrift);
        RadBedingung.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstBedingung);
        RadEMail_A.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstEMail_A);
        RadEMail_D.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstEMail_D);
        RadEMail_S.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstEMail_S);
        RadSchicht2.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstSchicht2);
        RadSchichtTMZ.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstSchichtTMZ);
        RadPDF_Save.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstPDF_Save);
        RadPDF_Direkt.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstPDF_Direkt);
        RadHtmlDirekt.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstArt)==Drucken.cstHtmlDirekt);
        CbxStrecken.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstStrecken)>0);
        CbxNichtDrucken.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstNichtDrucken)>0);
        CbxImmer.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstImmer)>0);
        CbxBlocksatz.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstBlocksatz)>0);
        CbxLeere.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstLeere)>0);
        CbxBedST.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstBedST)>0);
        CbxTest.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstTest)>0);
        CbxNeueSeite.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstNeueSeite)>0);
        CbxNurSummeA.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstNurSummeA)>0);
        CbxTestfill.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstTestfill)>0);
        CbxNurMitTitel.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstNurMitTitel)>0);
        CbxColor.setSelected((((Integer)VecInvisible.elementAt(4)).intValue()&Drucken.cstColor)>0);
        CboVorlagen.setSelectedAIC(((Integer)VecInvisible.elementAt(5)).intValue());
        MemoAbschnitt.setText((String)VecInvisible.elementAt(6));
        String sBild=(String)VecInvisible.elementAt(7);
        if (sBild.equals(""))
          BtnBildAbschnitt.Delete(true);
        else if (bBildOld)
          BtnBildAbschnitt.setValue(g.LoadImage(sBild),sBild);
        else 
        {
        	JCOutlinerNode NodeAbschnitt = !bNeu?OutAbschnitt.getSelectedNodes()[0]:null;
        	int iAIC_Abschnitt=!bNeu?((Integer)((Vector)NodeAbschnitt.getUserData()).elementAt(0)).intValue():0;
        	Image Img=Static.Leer(sBild) ? null:SQL.getImageA(g,iAIC_Abschnitt);
        	BtnBildAbschnitt.setValue(Img,sBild,"Zeile");
        }
        Static.centerComponent(FrmAbschnitt,thisFrame);
        EnableButtons();
        thisJFrame().setEnabled(false);
        FrmAbschnitt.setVisible(true);
      }

public ActionListener Action_BtnDruckOk = new ActionListener()
{
	public void actionPerformed(ActionEvent e)
	{
          /*if (TxtDruckKennung.isNull())
          {
            new Message(Message.ERROR_MESSAGE, FrmDruck,g,10).showDialog("KennungLeer");
            return;
          }*/

		JCOutlinerNode NodeDruck = !bNeu?OutDruck.getSelectedNodes()[0]:null;
		int iAIC_Druck=!bNeu?((Integer)((Vector)NodeDruck.getUserData()).elementAt(0)).intValue():0;
                if (Message.KennungWarnung(g,TxtDruckKennung.getText(),iAIC_Druck,g.TabBegriffgruppen.getAic("Druck"),FrmDruck))
                  return;
                /*
                if(SQL.exists(g,"select b.aic_begriff from begriff b join begriffgruppe bg where bg.kennung='Druck' and b.aic_begriff<> "+iAIC_Druck+" and b.Kennung='"+TxtDruckKennung.getText()+"'"))
                {
                  new Message(Message.WARNING_MESSAGE, FrmDruck, g,10).showDialog("KennungVorhanden");
                  return;
                }*/
                hideDlg(FrmDruck);
                SQL Qry = new SQL(g);

			Qry.add("Kennung",TxtDruckKennung.getText());
            Qry.add("DefBezeichnung",TxtDruckDefBezeichnung.getText());
            Qry.add("Kennzeichen",TxtDruckKennzeichen.getText());
            Qry.add0("prog", CboProg.getSelectedAIC());
			Qry.add("AIC_Stammtyp",CboStammtyp.getSelectedAIC());
            Qry.add0("AIC_Rolle",CboRolle.getSelectedAIC());
			Qry.add0("AIC_Bewegungstyp",CboBewegungstyp.getSelectedAIC());
			Qry.add0("AIC_Schrift",CboSchrift.getSelectedAIC());
			Qry.add("AIC_Begriffgruppe",g.TabBegriffgruppen.getAic("Druck"));
			long iPntBits=(CbxPntUnterschrift.isSelected()?Drucken.cstPntUnterschrift:0)+(CbxPntKeinDrucktitel.isSelected()?Drucken.cstPntKeinDrucktitel:0)+
				(CbxPntTagesanzeige.isSelected()?Drucken.cstPntTagesanzeige:0)+(CbxPntKeinKopfFuss.isSelected()?Drucken.cstPntKeinKopfFuss:0)+
				(CbxPntStammtitelLinks.isSelected()?Drucken.cstPntStammLinks:0)+(CbxPntKeinStammtitel.isSelected()?Drucken.cstPntKeinStammtitel:0)+
				(CbxPntKeineGruppierung.isSelected()?Drucken.cstPntKeineGruppierung:0)+(CbxPntQuerformat.isSelected()?Drucken.cstPntQuerformat:0)+
				+(CbxPntPers.isSelected()?Drucken.cstPntPers:0)+(CbxPntDrucktitelLinks.isSelected()?Drucken.cstPntDrucktitelLinks:0)+
                                //(CbxPntNeuerDruck.isSelected()?Drucken.cstPntNeuerDruck:0)+(CbxPntBewegung.isSelected()?Drucken.cstPntBewegung:0)+
                                (CbxPntSchicht.isSelected()?Drucken.cstPntSchicht:0)+
                                (CbxPntPeriode.isSelected()?Drucken.cstPntPeriode:0)+(CbxPntSeitenumbruch.isSelected()?Drucken.cstPntSeitenumbruch:0)+
                                (CbxPntDirekt.isSelected()?Drucken.cstPntDirekt:0)+(CbxPntUseSync.isSelected()?Drucken.cstPntUseSync:0)+(CbxPntNewYear.isSelected()?Drucken.cstPntNewYear:0)+
                                (RadTag.isSelected()?Drucken.cstPntZaTag:RadWoche.isSelected()?Drucken.cstPntZaWoche:RadMonat.isSelected()?Drucken.cstPntZaMonat:
                                 RadQuartal.isSelected()?Drucken.cstPntZaQuartal:RadJahr.isSelected()?Drucken.cstPntZaJahr:0)+
                                (CbxPntKeinZRtitel.isSelected()?Drucken.cstPntKeinZRtitel:0)+(CbxPntKnopf.isSelected()?Drucken.cstPntKnopf:0)+(CbxEMail.isSelected()?Drucken.cstPntEMail:0)+(CbxFarbe.isSelected()?Drucken.cstPntFarbe:0)+
                                (CbxPntPdfSave.isSelected()?Drucken.cstPntPdfSave:0)+(CbxPntMenge.isSelected()?Drucken.cstPntMenge:0)+(CbxKeinProg.isSelected()?Drucken.cstPntKeinProg:0)+
                                (CbxPntExport.isSelected()?Drucken.cstPntExport:0)+(CbxPntHtml.isSelected()?Drucken.cstPntHtml:0)+(CbxPntBewZR.isSelected()?Drucken.cstPntBewZR:0)+
                                (CbxNurSummeD.isSelected()?Drucken.cstPntNurSumme:0)+(CbxKeineSummeD.isSelected()?Drucken.cstPntKeineSumme:0)+(CbxED.isSelected()?Drucken.cstPntED:0);
			Qry.add("bits",iPntBits);
            Qry.add("aic_logging",g.getLog());
            Qry.add0("Web", CbxWeb.isSelected());
            Qry.add0("Tod", CbxTod.isSelected());
			if(bNeu)
				iAIC_Druck=Qry.insert("Begriff",true);
			else
				Qry.update("Begriff",iAIC_Druck);

			g.putTabBegriffe(g.TabBegriffgruppen.getAic("Druck"),iAIC_Druck,TxtDruckKennung.getText(),TxtDruckBezeichnung.getText(),TxtDruckDefBezeichnung.getText(),null,CboSchrift.getSelectedAIC(),null,-1,CboProg.getSelectedAIC(),
							CboStammtyp.getSelectedAIC(),CboBewegungstyp.getSelectedAIC(),CboRolle.getSelectedAIC(),iPntBits,false,CbxWeb.isSelected(),0,null,CbxTod.isSelected(),TooltipDruck.getValue(),TxtDruckKennzeichen.getText(),null,null,null,bNeu);

			if (TxtDruckBezeichnung.Modified())
                          g.setBezeichnung("",TxtDruckBezeichnung.getText(),g.iTabBegriff,iAIC_Druck,0);
			//g.setBezeichnung(bNeu?"":(String)((Vector)NodeDruck.getLabel()).elementAt(0),TxtDruckBezeichnung.getText(),g.TabTabellenname.getI("AIC"),iAIC_Druck,0);

			if (MemoDruck.Modified())
                          g.setMemo(MemoDruck.getValue(),"",g.iTabBegriff,iAIC_Druck,0);
                        if (TooltipDruck.Modified())
                          g.setTooltip(TooltipDruck.getValue(),g.iTabBegriff,iAIC_Druck,0);
                        if (CboModell.Cbo.Modified())
                        {
                          int iAnz=g.exec("delete from begriff_zuordnung where aic_begriff="+iAIC_Druck+
                                          " and beg_aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell")+")");
                          if (iAnz>0)
                            g.fixInfo(iAnz+" Druck-Modell-Zuordnungen gelöscht");
                          int iAicM=CboModell.getSelectedAIC();
                          if (iAicM>0)
                            g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff) values ("+iAIC_Druck+","+iAicM+")");
                        }
                        if (CboAbfrage.Cbo.Modified())
                        {
                          
                          int iAnz=g.exec("delete from begriff_zuordnung where aic_begriff="+iAIC_Druck+
                                          " and beg_aic_begriff in (select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+")");
                          if (iAnz>0)
                            g.fixInfo(iAnz+" Druck-Abfrage-Zuordnungen gelöscht");
                          int iAicA=CboAbfrage.getSelectedAIC();
//                          g.fixtestError("Druck="+iAIC_Druck+" hat Abfrage="+iAicA);
                          if (iAicA>0)
                          {
                        	  iAicA=SQL.getInteger(g, "select aic_begriff from abfrage where aic_abfrage=?",0,""+iAicA);
                        	  g.exec("insert into begriff_zuordnung (aic_begriff,beg_aic_begriff) values ("+iAIC_Druck+","+iAicA+")");
                          }
                        }
			Vector<Object> VecVisible = new Vector<Object>();
			Vector<Number> VecInvisible = new Vector<Number>();

			VecVisible.addElement(Static.beiLeer(TxtDruckBezeichnung.getText(),TxtDruckDefBezeichnung.getText()));
			VecVisible.addElement(TxtDruckKennung.getText());
			VecVisible.addElement((iPntBits&Drucken.cstPntKeinProg)>0 ? Static.sKein:CboProg.isNull()?Static.sLeer:CboProg.getSelectedBezeichnung());
			VecVisible.addElement(Static.JaNein2(CbxWeb.isSelected()));
			VecVisible.addElement(Static.JaNein2(CbxPntPers.isSelected()));
			//VecVisible.addElement(CboStammtyp.getSelectedBezeichnung());
			VecVisible.addElement(CboBewegungstyp.getSelectedAIC()==0?CboStammtyp.getSelectedBezeichnung():"*"+CboBewegungstyp.getSelectedBezeichnung());
			VecVisible.addElement(null);
			VecVisible.addElement(CboSchrift.getSelectedBezeichnung());
            VecVisible.addElement(new Zeit(new java.util.Date(),"dd.MM.yyyy"));
            VecVisible.addElement(CboRolle.getSelectedAIC()==0 ? "":g.TabRollen.getBezeichnungS(CboRolle.getSelectedAIC()));
            VecVisible.addElement(TxtDruckKennzeichen.getText());
            VecVisible.addElement(TxtDruckDefBezeichnung.getText());
            
			VecInvisible.addElement(new Integer(iAIC_Druck));
			VecInvisible.addElement(new Integer(CboStammtyp.getSelectedAIC()));
			VecInvisible.addElement(new Long(iPntBits));
			VecInvisible.addElement(new Integer(CboBewegungstyp.getSelectedAIC()));
			VecInvisible.addElement(new Integer(CboSchrift.getSelectedAIC()));
                        VecInvisible.addElement(new Integer(CboRolle.getSelectedAIC()));
			if(NodeDruck==null)
				NodeDruck = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutDruck.getRootNode());
			else
				NodeDruck.setLabel(VecVisible);
			NodeDruck.setUserData(VecInvisible);
                        NodeDruck.setStyle(/*(iPntBits&Drucken.cstPntNeuerDruck)>0 ? */StyDruck/*:StyDruckAlt*/);

			OutDruck.selectNode(NodeDruck,null);

		Qry.free();
                if (NodCopy != null)
                {
                  Vector VecNodes = NodCopy.getChildren();
                  for (int i=0;i<VecNodes.size();i++)
                  {
                    //jclass.util.JCVector VecVisible2 = new jclass.util.JCVector((Vector)((JCOutlinerNode)VecNodes.elementAt(i)).getLabel());
                    JCOutlinerNode Node = new JCOutlinerNode(((JCOutlinerNode)VecNodes.elementAt(i)).getLabel(),(JCOutlinerFolderNode)NodeDruck);
                    Node.setUserData(((JCOutlinerNode)VecNodes.elementAt(i)).getUserData());
                  }
                  Aenderung(new Integer(iAIC_Druck));
                  NodCopy=null;
                  Static.makeVisible(OutDruck,NodeDruck);
                }
                else
                  OutDruck.folderChanged(OutDruck.getRootNode());

	}
};

// add your data members here
private Global g;

private AUOutliner OutDruck = new AUOutliner(new JCOutlinerFolderNode("Root Druck"));
private AUOutliner OutAbschnitt = new AUOutliner(new JCOutlinerFolderNode("Root Abschnitt"));

//private JButton BtnOk;
//private JButton BtnAbbruch;
private JButton BtnSpeichern;
//private JButton BtnBeenden;
//private JButton BtnNewPrint;
private JButton BtnEditPrint;
private JButton BtnDeletePrint;
//private JButton BtnNewSegment;
private JButton BtnEditSegment;
private JButton BtnDeleteSegment;
private JButton BtnCopySegment;
private JButton BtnHinzufuegen;
private JButton BtnErsetzen;
private JButton BtnEntfernen;
private JButton BtnRauf;
private JButton BtnRunter;
private JButton BtnSeperator;
//private JButton BtnShow;
//private JButton BtnAbfrage;
//private JButton BtnRefresh;
private JButton BtnAlleDrucke;
private boolean bNeu = false;

private Vector<Integer> VecAenderung;

//Frame Druck
private JDialog FrmDruck;
public JButton BtnDruckOk; // public wegen DruckFrage
private JButton BtnDruckAbbruch;
private Text TxtDruckBezeichnung = new Text("",255);
private Text TxtDruckDefBezeichnung = new Text("",255);
private Text TxtDruckKennung = new Text("",40,Text.KENNUNG);
private Text TxtDruckKennzeichen = new Text("",40,Text.KENNUNG);
private AUCheckBox CbxPntUnterschrift;
private AUCheckBox CbxPntKeinDrucktitel;
private AUCheckBox CbxPntTagesanzeige;
private AUCheckBox CbxPntKeinKopfFuss;
private AUCheckBox CbxPntStammtitelLinks;
private AUCheckBox CbxPntKeinStammtitel;
private AUCheckBox CbxPntKeinZRtitel;
private AUCheckBox CbxPntPdfSave;
private AUCheckBox CbxEMail;
private AUCheckBox CbxPntMenge;
private AUCheckBox CbxPntBewZR;
private AUCheckBox CbxPntKeineGruppierung;
private AUCheckBox CbxPntQuerformat;
private AUCheckBox CbxPntPers;
private AUCheckBox CbxPntDrucktitelLinks;
//private AUCheckBox CbxPntNeuerDruck;
//private AUCheckBox CbxPntBewegung;
private AUCheckBox CbxPntSchicht;
private AUCheckBox CbxPntPeriode;
private AUCheckBox CbxPntSeitenumbruch;
private AUCheckBox CbxPntDirekt;
private AUCheckBox CbxPntUseSync;
private AUCheckBox CbxPntNewYear;
private AUCheckBox CbxPntKnopf;
private AUCheckBox CbxPntExport;
private AUCheckBox CbxPntHtml;
private AUCheckBox CbxKeinProg;
private AUCheckBox CbxWeb;
private AUCheckBox CbxTod;
private AUCheckBox CbxFarbe;
private AUCheckBox CbxNurSummeD;
private AUCheckBox CbxKeineSummeD;
private AUCheckBox CbxED;
private JRadioButton RadOffen;
private JRadioButton RadTag;
private JRadioButton RadWoche;
private JRadioButton RadMonat;
private JRadioButton RadQuartal;
private JRadioButton RadJahr;
private ComboSort CboStammtyp;
private RolleEingabe CboRolle;
private ComboSort CboProg;
private ComboSort CboBewegungstyp;
private Schrift CboSchrift;
private ModellEingabe CboModell;
private AbfrageEingabe CboAbfrage; //für Modell-Dialog
private JLabel LblAic=new JLabel(); 
private JLabel LblChanged=new JLabel(); 

//Frame Abschnitt
private JDialog FrmAbschnitt;
private JButton BtnAbschnittOk;
private JButton BtnAbschnittAbbruch;
private Text TxtAbschnittBezeichnung = new Text("",255);
private Text TxtAbschnittKennung = new Text("",40,Text.KENNUNG);
private Text TxtAbschnittKennzeichen = new Text("",255);
private Zahl EdtZahl = new Zahl(0.0);
private AbfrageEingabe CboAbfragen;
//private Schrift CboSchriftTitel;
//private Schrift CboSchriftText;
private DefVorlagen CboVorlagen;
private AUCheckBox CbxUeberschrift;
//private JCheckBox CbxTitelDarunter;
//private JCheckBox CbxErsteSpalteFett;
//private JCheckBox CbxEinzeldaten;
//private JCheckBox CbxKeinTitel;
private AUCheckBox CbxGesamtsumme;
private AUCheckBox CbxNurEMail;
private AUCheckBox CbxLtAic;
private AUCheckBox CbxKeineHS;
private AUCheckBox CbxGruppenRed;
private JRadioButton RadLinks;
private JRadioButton RadRechts;
private JRadioButton RadRechts2;
private JRadioButton RadNebeneinander;
private JRadioButton RadNormal;
private JRadioButton RadDiagramm;
//private JCheckBox CbxUeberschriftLinks;
private AUCheckBox CbxKeinAbstand;
private AUCheckBox CbxKeineSumme;
private JRadioButton RadIstUeberschrift;
private JRadioButton RadBedingung;
private JRadioButton RadSchicht2;
private JRadioButton RadSchichtTMZ;
private JRadioButton RadPDF_Save;
private JRadioButton RadPDF_Direkt;
private JRadioButton RadEMail_A;
private JRadioButton RadEMail_D;
private JRadioButton RadEMail_S;
private JRadioButton RadHtmlDirekt;
private AUCheckBox CbxFixRaster;
private JRadioButton RadFixPos;
private AUCheckBox CbxStrecken;
private AUCheckBox CbxNichtDrucken;
private AUCheckBox CbxImmer;
private AUCheckBox CbxBlocksatz;
private AUCheckBox CbxLeere;
private AUCheckBox CbxBedST;
private AUCheckBox CbxTest;
private AUCheckBox CbxNeueSeite;
private AUCheckBox CbxNurSummeA;
private AUCheckBox CbxTestfill;
private AUCheckBox CbxNurMitTitel;
private AUCheckBox CbxColor;
private AUTextArea MemoDruck;
private AUTextArea TooltipDruck;
private AUTextArea MemoAbschnitt;
private BildEingabe BtnBildAbschnitt;
public int iDruck=0;
public int iStt=0;
private JCOutlinerNodeStyle StyAbschnitt;
private JCOutlinerNodeStyle StyDruck;
//private JCOutlinerNodeStyle StyDruckAlt;
private ActionListener AL;
private JPopupMenu popup= new JPopupMenu();
private JPopupMenu popupD= new JPopupMenu();
private JCOutlinerFolderNode NodCopy=null;
private static Clock clock=null;

}

