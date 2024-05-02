/*
    All_Unlimited-Grunddefinition-DefPlanung.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Planung;
import All_Unlimited.Allgemein.SQL;
//import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.AbfrageEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import javax.swing.JPopupMenu;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Component;
import All_Unlimited.Allgemein.Static;
import jclass.bwt.JCActionEvent;
import javax.swing.JDialog;
import jclass.bwt.JCActionListener;
import All_Unlimited.Allgemein.Anzeige.Zeit;

public class DefPlanung extends All_Unlimited.Allgemein.Formular
{
	private static DefPlanung Self=null;
	
	public static DefPlanung get(Global rg,int iP)
	  {
	      if (Self==null)
	    	  new DefPlanung(rg);
	      Self.show(iP);
	      return Self;
	  }

	  public static void free()
	  {
	    if (Self!=null)
	    {
	      Self.g.winInfo("DefPlanung.free");
	      Self.thisFrame.dispose();
	      Self=null;
	    }
	  }
	
private DefPlanung(Global glob)
{
	super("DefPlanung",null,glob);
	Self=this;
	g=glob;
	Self.g.winInfo("DefPlanung.create");
        ActionListener AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
            if (s.equals("show"))
            {
              String s2 = "select defbezeichnung,kennung,begriff.aic_begriff"+g.AU_Bezeichnung1("Stammtyp","Begriff")+" Stammtyp from begriff"+g.join2("formular","begriff")+g.join2("darstellung","formular")+" where darstellung.aic_begriff="+iAktAIC;
              new Tabellenspeicher(g,s2,true).showGrid(TxtBezeichnung.getText());
            }
            else if (s.equals("Neu"))
            {
                    TxtBezeichnung.setText("");
                    TxtKennung.setText("");
                    TxtKennung.setEnabled(true);
                    CboAbfFilter.setSelectedAIC(0);
                    CboAbfSonst.setSelectedAIC(0);
                    TxtSpaltenbreite.setValue(50);
                    iAktAIC=0;
            }
            else if (s.equals("Loeschen"))
            {
               SQL Qry=new SQL(g);
                     String s1 = Qry.list("defbezeichnung","begriff"+g.join2("formular","begriff")+g.join2("darstellung","formular")+" where darstellung.aic_begriff="+iAktAIC);
                     if(s1.equals(" "))
                     {
                             int iMessage=new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",new String[]{TxtKennung.getText()});
                             if(iMessage==Message.YES_OPTION)
                             {
                                     Qry.exec("DELETE FROM Planung WHERE AIC_Begriff="+iAktAIC);
                                     Qry.deleteFrom("Begriff","Begriff WHERE AIC_Begriff="+iAktAIC,g.iTabBegriff);
                                     g.TabBegriffe.clearInhaltS("AIC",new Integer(iAktAIC));
                                     FillOutliner();
                             }
                     }
                     else
                             new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("BereitsVerwendet",new String[] {s1});// String-Array-Cast -->,(String[])Vec.toArray());
               Qry.free();
            }
            else if (s.equals("Beenden"))
              hide();
            else if (s.equals("Save"))
            {
                if (Message.KennungWarnung(g,TxtKennung.getText(),iAktAIC,iBG,(JFrame)thisFrame))
                  return;

                      SQL Qry = new SQL(g);
                      int iBits=(CbxNotSelf.isSelected()?Planung.cstNotSelf:0)+(CbxBehind.isSelected()?Planung.cstBehind:0)+
                                  (CbxDays.isSelected()?Planung.cstDays:0)+(CbxWeeks.isSelected()?Planung.cstWeeks:0)+
                                  (CbxAZ.isSelected()?Planung.cstAZ:0)+(CbxStunden.isSelected()?Planung.cstStunden:0)+
                                  (CbxBewilligung.isSelected()?Planung.cstBewilligung:0)+
                                  (CbxSchichten.isSelected()?Planung.cstSchichten:0)+(CbxAndereFarbe.isSelected()?Planung.cstAndereFarbe:0)+
                                  (CbxKRNS.isSelected()?Planung.cstKRNS:0)+(CbxZR.isSelected()?Planung.cstZR:0)+(CbxFTT.isSelected()?Planung.cstFTT:0)+
                                  (CbxAuswahl.isSelected()?Planung.cstAuswahl:0)+(CbxMultiP.isSelected()?Planung.cstMultiP:0)+(CbxTGM.isSelected()?Planung.cstTGM:0)+(CbxVeil.isSelected()?Planung.cstPveil:0);

                      //int iAIC = Qry.getInteger("select b.aic_begriff from begriff b where aic_begriffgruppe="+iBG+" and b.kennung='"+TxtKennung.getText()+"'");
                      if(iAktAIC==0)
                      {
                              Qry.add("Kennung",TxtKennung.getText());
                              Qry.add("DefBezeichnung",TxtBezeichnung.getText());
                              Qry.add("Bits",iBits);
                              Qry.add("AIC_Begriffgruppe",iBG);
                              Qry.add("AIC_LOGGING",g.getLog());
                              iAktAIC=Qry.insert("begriff",true);

                              Qry.add("AIC_Abfrage",CboAbfDaten.getSelectedAIC());
                              Qry.add0("Abf_AIC_Abfrage",CboAbfFilter.getSelectedAIC());
                              Qry.add0("Abf2_AIC_Abfrage",CboAbfSonst.getSelectedAIC());
                              Qry.add("Spaltenbreite",TxtSpaltenbreite.intValue());
                              Qry.add("AIC_Begriff",iAktAIC);
                              Qry.insert("Planung",false);

                              //g.setBezeichnung("",TxtBezeichnung.getText(),iTab,iAktAIC,0);
                              g.setMemo(Memo.getValue(),"",g.iTabBegriff,iAktAIC,0);
                              g.putTabBegriffe(iBG,iAktAIC,TxtKennung.getText(),TxtBezeichnung.getText(),TxtBezeichnung.getText(),null,0,null,-1,-1,-1,-1,-1,iBits,false,false,0,null,false,null,null,null,null,null,true);
                      }
                      else
                      {
                              Qry.add("Kennung",TxtKennung.getText());
                              Qry.add("DefBezeichnung",TxtBezeichnung.getText());
                              Qry.add("Bits",iBits);
                              Qry.add("AIC_LOGGING",g.getLog());
                              Qry.update("begriff",iAktAIC);

                              Qry.add("AIC_Abfrage",CboAbfDaten.getSelectedAIC());
                              Qry.add0("Abf_AIC_Abfrage",CboAbfFilter.getSelectedAIC());
                              Qry.add0("Abf2_AIC_Abfrage",CboAbfSonst.getSelectedAIC());
                              Qry.add("Spaltenbreite",TxtSpaltenbreite.intValue());
                              Qry.addWhere("AIC_Begriff",iAktAIC);
                              Qry.update("Planung");

                              //g.setBezeichnung((String)((Vector)Out.getSelectedNode().getLabel()).elementAt(0),TxtBezeichnung.getText(),iTab,iAktAIC,0);
                              g.setMemo(Memo.getValue(),"",g.iTabBegriff,iAktAIC,0);
                              g.putTabBegriffe(iBG,iAktAIC,TxtKennung.getText(),TxtBezeichnung.getText(),TxtBezeichnung.getText(),null,0,null,-1,-1,-1,-1,-1,iBits,false,false,0,null,false,null,null,null,null,null,false);
                      }
                      Qry.free();

                      FillOutliner();
              }
              else if (s.equals("Back"))
                setSelected();
              else if (s.startsWith("Abfrage"))
                DefAbfrageAufrufen(s.equals("AbfrageD") ? CboAbfDaten:s.equals("AbfrageF") ? CboAbfFilter:CboAbfSonst);
              else if (s.startsWith("Planungsbits"))
                ShowPlanungsbits();
              else
                Static.printError("DefPlanung: "+s+" wird noch nicht unterstützt");
          }
        };
        Build(AL);
        g.BtnAdd(getButton("show"),"show",AL);
        g.BtnAdd(getButton("Neu"),"Neu",AL);
        g.BtnAdd(getButton("Loeschen"),"Loeschen",AL);
        g.BtnAdd(getButton("Beenden"),"Beenden",AL);
        g.BtnAdd(getButton("Speichern"),"Save",AL);
        g.BtnAdd(getButton("Ruecksetzen"),"Back",AL);

//	show();

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
			setSelected();
		}
	});

        popup= new JPopupMenu();
        g.addMenuItem("Neu", popup,null,AL);
        g.addMenuItem("Loeschen", popup,null,AL);
        popup.addSeparator();
        g.addMenuItem("show", popup,null,AL);
        g.addMenuItem("Planungsbits", popup,null,AL);
        Out.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev)
          {}

          public void mouseClicked(MouseEvent ev)
          {
            //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
            if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM) && g.Def())
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

  private void ShowPlanungsbits()
  {
    Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr", "Kennung", "Bezeichnung", "Anzahl"});
    addBit(Tab2, "cstNotSelf", "nicht selbst", 0x0001, 0);
    addBit(Tab2, "cstBehind", "Feiertage hinten", 0x0002, 1);
    addBit(Tab2, "cstDays", "Tage", 0x0004, 2);
    addBit(Tab2, "cstWeeks", "Wochen", 0x0008, 3);
    addBit(Tab2, "cstAZ", "Arbeitszeit", 0x00010, 4);
    addBit(Tab2, "cstStunden", "Stunden", 0x0020, 5);
    addBit(Tab2, "cstBewilligung", "Bewilligung", 0x0040, 6);
    addBit(Tab2, "cstSchichten", "Schicht", 0x0080, 7);
    addBit(Tab2, "cstAndereFarbe", "andere Farbe", 0x0100, 8);
    addBit(Tab2, "cstKRNS", "kein Save-Refresh", 0x0200, 9);
    addBit(Tab2, "cst3W", "3 Wochen", 0x0400, 10);
    addBit(Tab2, "cstZR", "Zeitraum", 0x0800, 11);
    addBit(Tab2, "cstFTT", "Feiertagtitel", Planung.cstFTT, 12);
    addBit(Tab2, "cstAuswahl", "Auswahl", Planung.cstAuswahl, 13);
    addBit(Tab2, "cstMultiP", "MultiP", Planung.cstMultiP, 14);
    addBit(Tab2, "cstTGM", "TGM", Planung.cstTGM, 15);
    addBit(Tab2, "cstPveil", "veil", Planung.cstPveil, 16);
    final JDialog FomGid = new JDialog((JFrame)thisFrame, "Planungsbits", false);
    AUOutliner Grid = new AUOutliner();
    FomGid.getContentPane().add("Center", Grid);
    Tab2.showGrid(Grid);
    FomGid.pack();//setSize(400, 600);
    Static.centerComponent(FomGid,thisFrame);
    Grid.addActionListener(new JCActionListener() {
          public void actionPerformed(JCActionEvent ev) {
            JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
            int i=Tabellenspeicher.geti(((Vector)Nod.getLabel()).elementAt(0));
            long l=(long)Math.pow(2,i);
            Tabellenspeicher Tab = new Tabellenspeicher(g,"select b.defbezeichnung,b.kennung from begriff b join Planung p on b.aic_begriff=p.aic_begriff where "+g.bit("b.bits",l),true);
            Tab.showGrid("Planung mit bit " + i, thisFrame);
          }
        });
    FomGid.setVisible(true);
  }

  private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
    {
      Tab2.addInhalt("Nr",i);
      Tab2.addInhalt("Kennung",sConst);
      Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
      Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from begriff b join Planung p on b.aic_begriff=p.aic_begriff where "+g.bit("b.bits",l)));
  }

private void Build(ActionListener AL)
{
	//iTab=g.TabTabellenname.getAic("BEGRIFF");
	iBG=g.TabBegriffgruppen.getAic("Planung");
	//g.progInfo("Tab="+iTab+", BG="+iBG);

	String [] s = {g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Abfrage Daten"),g.getBegriffS("Show","Abfrage Filter")
            ,g.getBegriffS("Show","Abfrage Sonst"),g.getBegriffS("Show","Spaltenbreite"),g.getBegriffS("Show","Change")};
	Out.setColumnButtons(s);
	Out.setNumColumns(s.length);
	Out.setRootVisible(false);
	Out.setBackground(Color.white);

	CboAbfDaten = new AbfrageEingabe(g,thisFrame,true,false);
        CboAbfFilter = new AbfrageEingabe(g,thisFrame,true,false);
        CboAbfSonst = new AbfrageEingabe(g,thisFrame,true,false);

	/*BtnNeu = getButton("Neu");
	if(BtnNeu==null) BtnNeu=new JButton();
	BtnLoeschen = getButton("Loeschen");
	if(BtnLoeschen==null) BtnLoeschen=new JButton();
	BtnBeenden = getButton("Beenden");
	if(BtnBeenden==null) BtnBeenden=new JButton();
	BtnSpeichern = getButton("Speichern");
	if(BtnSpeichern==null) BtnSpeichern=new JButton();
	BtnZurueck = getButton("Ruecksetzen");
	if(BtnZurueck==null) BtnZurueck=new JButton();*/

	JPanel PnlOutliner = getFrei("Outliner");
	JPanel PnlEingabe = getFrei("Eingabe");
	JPanel PnlMemo = getFrei("Memo");

	if(PnlOutliner!=null)
	{
		PnlOutliner.setLayout(new BorderLayout(2,2));
		PnlOutliner.add("Center",Out);
	}
	if(PnlEingabe!=null)
	{
		PnlEingabe.setLayout(new BorderLayout(2,2));
		JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
		g.addLabel2(Pnl,"Bezeichnung");
		g.addLabel2(Pnl,"Kennung");
		/*g.addLabel2(Pnl,"Abfrage Daten");
		g.addLabel2(Pnl,"Abfrage Filter");
                g.addLabel2(Pnl,"Abfrage Sonst");*/
                JButton BtnD=g.getButton("Abfrage Daten");
                JButton BtnF=g.getButton("Abfrage Filter");
                JButton BtnS=g.getButton("Abfrage Sonst");
                g.addComp2(Pnl,BtnD);
                g.addComp2(Pnl,BtnF);
                g.addComp2(Pnl,BtnS);
                g.BtnAdd(BtnD,"AbfrageD",AL);
                g.BtnAdd(BtnF,"AbfrageF",AL);
                g.BtnAdd(BtnS,"AbfrageS",AL);
                /*BtnD.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ev)
                    {
                      DefAbfrageAufrufen(CboAbfDaten);
                    }
                });
                BtnF.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ev)
                    {
                      DefAbfrageAufrufen(CboAbfFilter);
                    }
                });
                BtnS.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ev)
                    {
                      DefAbfrageAufrufen(CboAbfSonst);
                    }
                });*/
                g.addLabel2(Pnl,"Spaltenbreite");
		/*g.addLabel2(Pnl,"nicht selbst");
		g.addLabel2(Pnl,"Feiertage hinten");
		g.addLabel2(Pnl,"Tage");
		g.addLabel2(Pnl,"Wochen");
                g.addLabel2(Pnl,"Arbeitszeit");
                g.addLabel2(Pnl,"Stunden");
                g.addLabel2(Pnl,"Bewilligung");
                g.addLabel2(Pnl,"Schicht");
                g.addLabel2(Pnl,"andere Farbe");
                g.addLabel2(Pnl,"kein Save-Refresh");*/
                CbxNotSelf=g.getCheckbox("nicht selbst");
                CbxBehind=g.getCheckbox("Feiertage hinten");
                CbxDays=g.getCheckbox("Tage");
                CbxWeeks=g.getCheckbox("Wochen");
                CbxAZ=g.getCheckbox("Arbeitszeit");
                CbxStunden=g.getCheckbox("Stunden");
                CbxBewilligung=g.getCheckbox("Bewilligung");
                CbxSchichten=g.getCheckbox("Schicht");
                CbxAndereFarbe=g.getCheckbox("andere Farbe");
                CbxKRNS=g.getCheckbox("kein Save-Refresh");
                //Cbx3W=g.getCheckbox("3 Wochen");
                CbxZR=g.getCheckbox("Zeitraum");
                CbxFTT=g.getCheckbox("Feiertagtitel");
                CbxAuswahl=g.getCheckbox("Auswahl");
                CbxMultiP=g.getCheckbox("MultiP");
                CbxTGM=g.getCheckbox("Tagesmuster");
                CbxVeil=g.getCheckbox("veil");
                for (int i=0;i<8;i++)
                  Pnl.add(new JLabel());
		PnlEingabe.add("West",Pnl);
		Pnl = new JPanel(new GridLayout(0,1,2,2));
		Pnl.add(TxtBezeichnung);
		Pnl.add(TxtKennung);
		Pnl.add(CboAbfDaten);
		Pnl.add(CboAbfFilter);
                Pnl.add(CboAbfSonst);
		Pnl.add(TxtSpaltenbreite);

		Pnl.add(TwoInOne(CbxNotSelf,CbxBehind));
		Pnl.add(TwoInOne(CbxDays,CbxWeeks));
		Pnl.add(TwoInOne(CbxAZ,CbxStunden));
                Pnl.add(TwoInOne(CbxBewilligung,CbxSchichten));
                Pnl.add(TwoInOne(CbxAndereFarbe,CbxKRNS));
                //Pnl.add(Cbx3W);
                Pnl.add(TwoInOne(CbxZR,CbxFTT));
                Pnl.add(TwoInOne(CbxAuswahl,CbxMultiP));
                Pnl.add(TwoInOne(CbxTGM,CbxVeil));
		PnlEingabe.add("Center",Pnl);
	}
	if(PnlMemo!=null)
	{
		PnlMemo.add(Memo);
	}
}

private JPanel TwoInOne(JCheckBox Cbx1,JCheckBox Cbx2)
{
  JPanel Pnl=new JPanel(new GridLayout(1,2,2,2));
  Pnl.add(Cbx1);
  if (Cbx2!=null)
	  Pnl.add(Cbx2);
  return Pnl;
}

private void DefAbfrageAufrufen(AbfrageEingabe Cbo)
{
  int iDefAbf=Cbo.getSelectedAIC();
  int iDefStt=-SQL.getInteger(g,"select aic_bewegungstyp from begriff"+g.join2("abfrage","begriff")+" where aic_abfrage="+iDefAbf);
  if (iDefStt==0)
    iDefStt=SQL.getInteger(g,"select aic_stammtyp from begriff"+g.join2("abfrage","begriff")+" where aic_abfrage="+iDefAbf);
  DefAbfrage.get(g,new ShowAbfrage(g,iDefAbf,Abfrage.cstAbfrage),iDefStt,null,false,0).show(false);
}

public void show(int iP)
{
	CboAbfDaten.fillCbo( "select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("bits",Abfrage.cstPlanungD)+" and not"+g.bit("bits",Abfrage.cstPlanungF),"Abfrage",false);
	CboAbfFilter.fillCbo("select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("bits",Abfrage.cstPlanungF)+" and not"+g.bit("bits",Abfrage.cstPlanungD),"Abfrage",true);
        CboAbfSonst.fillCbo( "select aic_abfrage,kennung,DefBezeichnung bezeichnung from abfrage"+g.join("begriff","abfrage")+" where"+g.bit("bits",Abfrage.cstPlanungD)+" and"+g.bit("bits",Abfrage.cstPlanungF),"Abfrage",true);
    iAktAIC=iP;
	FillOutliner();
	super.show();
	setSelected();
}

private void FillOutliner()
{
	SQL Qry = new SQL(g);
	JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
	NodeRoot.removeChildren();
	JCOutlinerNodeStyle Sty=g.getStyle(g.getImage("Begriffgruppe","Planung"));
	if(Qry.open("select begriff.aic_begriff,kennung, DefBezeichnung Bezeichnung"+g.AU_Memo("Begriff")+",spaltenbreite,aic_abfrage,abf_aic_abfrage,abf2_aic_abfrage,bits"+
                    ",(select ein from logging where aic_logging=begriff.aic_logging) Log from planung"+g.join("begriff","planung")))
	{
		for(;!Qry.eof();Qry.moveNext())
		{
			Vector<Comparable> VecVisible=new Vector<Comparable>();
			Vector<Comparable> VecInvisible=new Vector<Comparable>();

			VecVisible.addElement(Qry.getS("bezeichnung"));
			VecVisible.addElement(Qry.getS("kennung"));
			VecVisible.addElement(CboAbfDaten.Cbo.getBezeichnung(Qry.getI("aic_abfrage")));
			VecVisible.addElement(CboAbfFilter.Cbo.getBezeichnung(Qry.getI("abf_aic_abfrage")));
                        VecVisible.addElement(CboAbfSonst.Cbo.getBezeichnung(Qry.getI("abf2_aic_abfrage")));
			VecVisible.addElement(Qry.getInt("spaltenbreite"));
                        VecVisible.addElement(Qry.isNull("log") ? null :new Zeit(Qry.getTS("log"), "dd.MM.yyyy"));

			VecInvisible.addElement(Qry.getInt("aic_begriff"));
			VecInvisible.addElement(new Integer(Qry.getI("aic_abfrage")));
			VecInvisible.addElement(new Integer(Qry.getI("abf_aic_abfrage")));
                        VecInvisible.addElement(new Integer(Qry.getI("abf2_aic_abfrage")));
			VecInvisible.addElement(Qry.getInt("bits"));
			VecInvisible.addElement(Qry.getS("Memo"));
			//g.progInfo("Memo="+Qry.getS("Memo"));

			JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodeRoot);
                        Nod.setUserData(VecInvisible);
                        Nod.setStyle(Sty);
                        if (iAktAIC==Qry.getI("aic_begriff"))
                          Out.selectNode(Nod,null);
		}
		Qry.close();
	}
	Qry.free();

	Out.folderChanged(NodeRoot);
}

private void setSelected()
{
  TxtKennung.setEnabled(false);
	JCOutlinerNode Node=Out.getSelectedNode();
	if(Node!=null&&Node.getLevel()==1)
	{
		Vector VecVisible=(Vector)Node.getLabel();
		Vector VecInvisible=(Vector)Node.getUserData();
		TxtBezeichnung.setText((String)VecVisible.elementAt(0));
		TxtKennung.setText((String)VecVisible.elementAt(1));
		CboAbfDaten.setSelectedAIC(((Integer)VecInvisible.elementAt(1)).intValue());
		CboAbfFilter.setSelectedAIC(((Integer)VecInvisible.elementAt(2)).intValue());
                CboAbfSonst.setSelectedAIC(((Integer)VecInvisible.elementAt(3)).intValue());
		int iBit=VecInvisible.elementAt(4)==null?0:((Integer)VecInvisible.elementAt(4)).intValue();
		CbxNotSelf.setSelected((iBit&Planung.cstNotSelf)>0);
		CbxBehind.setSelected((iBit&Planung.cstBehind)>0);
		CbxDays.setSelected((iBit&Planung.cstDays)>0);
		CbxWeeks.setSelected((iBit&Planung.cstWeeks)>0);
                CbxAZ.setSelected((iBit&Planung.cstAZ)>0);
                CbxStunden.setSelected((iBit&Planung.cstStunden)>0);
                CbxBewilligung.setSelected((iBit&Planung.cstBewilligung)>0);
                CbxSchichten.setSelected((iBit&Planung.cstSchichten)>0);
                CbxAndereFarbe.setSelected((iBit&Planung.cstAndereFarbe)>0);
                CbxKRNS.setSelected((iBit&Planung.cstKRNS)>0);
                //Cbx3W.setSelected((iBit&Planung.cst3W)>0);
                CbxZR.setSelected((iBit&Planung.cstZR)>0);
                CbxFTT.setSelected((iBit&Planung.cstFTT)>0);
                CbxAuswahl.setSelected((iBit&Planung.cstAuswahl)>0);
                CbxMultiP.setSelected((iBit&Planung.cstMultiP)>0);
                CbxTGM.setSelected((iBit&Planung.cstTGM)>0);
                CbxVeil.setSelected((iBit&Planung.cstPveil)>0);
		//CboAbfFilter.setSelectedAIC(0);
		TxtSpaltenbreite.setValue(((Integer)VecVisible.elementAt(5)).intValue());
		Memo.setText((String)VecInvisible.elementAt(5));
		iAktAIC=((Integer)VecInvisible.elementAt(0)).intValue();
		//g.progInfo("VecInvisible:"+VecInvisible);
	}
	else
		iAktAIC=0;
}

/*private void EnableButtons()
{

}*/

// add your data members here
private Global g;

private int iAktAIC=0;

private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
//private JButton BtnNeu;
//private JButton BtnBearbeiten;
//private JButton BtnLoeschen;
//private JButton BtnBeenden;
//private JButton BtnSpeichern;
//private JButton BtnZurueck;

private Text TxtBezeichnung = new Text("",250);
private Text TxtKennung = new Text("",98,Text.KENNUNG);
private AbfrageEingabe CboAbfDaten;
private AbfrageEingabe CboAbfFilter;
private AbfrageEingabe CboAbfSonst;
private JCheckBox CbxNotSelf;
private JCheckBox CbxBehind;
private JCheckBox CbxDays;
private JCheckBox CbxWeeks;
private JCheckBox CbxAZ;
private JCheckBox CbxStunden;
private JCheckBox CbxBewilligung;
private JCheckBox CbxSchichten;
private JCheckBox CbxAndereFarbe;
private JCheckBox CbxKRNS;
//private JCheckBox Cbx3W;
private JCheckBox CbxZR;
private JCheckBox CbxFTT;
private JCheckBox CbxAuswahl;
private JCheckBox CbxMultiP;
private JCheckBox CbxTGM;
private JCheckBox CbxVeil;
private Zahl TxtSpaltenbreite=new Zahl(50);
private AUTextArea Memo=new AUTextArea();
private JPopupMenu popup=null;
//private int iTab=0;
private int iBG=0;
}

