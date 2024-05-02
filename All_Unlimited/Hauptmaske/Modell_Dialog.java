package All_Unlimited.Hauptmaske;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Vector;

import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Eingabe.*;
import javax.swing.border.EmptyBorder;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;

public class Modell_Dialog extends JDialog
{
  /**
   *
   */
  private static final long serialVersionUID = 7996253989314813166L;
  private Global g=null;
  public boolean bOk=false;
  private int iQry=0;
  private Tabellenspeicher Tab=null;
  private Tabellenspeicher TabS=null;
  private Tabellenspeicher TabD=null;
  private int iFF=100;

  public Modell_Dialog(JFrame FomA,Global rg,String s,int riSpalten,int riQry,Tabellenspeicher rTab,Tabellenspeicher rTabS)
  {
    super(FomA,s,true);
    g=rg;
    Tab=rTab;
    TabS=rTabS;
    iQry=riQry;
    int iSpalten=riSpalten%10;
    iFF=g.getFontFaktor();
    Build(iSpalten>0?iSpalten:2);
    int iW=riSpalten/10000*iFF/100;
    int iH=(riSpalten%10000)/10*iFF/100;
    //g.fixtestError("Modell_Dialog:"+s+", Spalten="+iSpalten+", FF="+iFF);
    //g.progInfo("iW="+iW+", iH="+iH+", iSpalten="+iSpalten);
    //pack();
    if (iW>0)
      setSize(new Dimension(iW,iH));
    else
    {
      pack();
      g.defInfo("Größe für Dialog " + s + " fehlt");
    }
    //g.fixInfo(" --------------- vor setVisible");
    Static.centerComponent(this,FomA);
    setVisible(true);
    //g.fixInfo(" -------------- nach setVisible");
  }

  private Component getComp(String sDt,boolean bSteuern)
  {
    String sK=TabS.getS("Kennung");
    String sKZ=TabS.getS("Stil");
//    g.fixtestError("Modelldialog bei "+sDt+": Kennzeichen="+sKZ);
    if (sDt.endsWith("Boolean"))
      return (JComponent)new AUCheckBox(Tab.getB(sK),TabS.getS("Bezeichnung"));
    else if (sDt.equals("Datum") || sDt.equals("Zeit") || sDt.equals("BewDatum") || sDt.equals("BewDatum2")
             || sDt.equals("Eintritt") || sDt.equals("EinAustritt") || sDt.equals("Austritt"))
    {
      String sFormat=TabS.getS("Format");
      if (sFormat.equals("")) sFormat="dd.MM.yyyy";
      Datum dt=new Datum(g,sFormat);
      dt.setDate(Tab.getTimestamp(sK));
      return dt;
    }
    else if (sKZ!=null && (sKZ.equals("Wochentag") || sKZ.equals("Monat")))
    {
    	RadioAuswahl Rad=new RadioAuswahl(g,-g.TabBegriffgruppen.getAic(sKZ));
    	return Rad;
    }
    else if ((sDt.equals("Gruppe") || sDt.equals("BewStamm")) && (TabS.getI("bits")&Abfrage.cstShow)==Abfrage.cstCombo2)
    {
    	int iPos=g.TabEigenschaften.getPos("Aic", TabS.getI("Eig"));
        int iStt2=g.TabEigenschaften.getI(iPos, "aic_stammtyp");
        boolean bKein=(TabS.getI("bits") & (Global.cstAlways * 0x10000)) == 0;
        
        AUComboList Cbo=new AUComboList(this,0,0,TabS.getI("Eig"),iStt2,g,TabS.getI("Filter"),true,bKein,false,false);
        Cbo.setPreferredSize(new java.awt.Dimension(200*iFF/100,16*iFF/100));
        Cbo.getComboBox().setSelectedAIC(Tab.getI(sK));
        return Cbo;
    }
    else if (sDt.equals("Gruppe") || sDt.equals("BewStamm") || sDt.equals("Mehrfach"))
    {
      ComboSort Cbo=new ComboSort(g);
      //g.fixtestError(sDt+", FF="+iFF);
      Cbo.setPreferredSize(new java.awt.Dimension(200*iFF/100,16*iFF/100));
      int iPos=g.TabEigenschaften.getPos("Aic", TabS.getI("Eig"));
      int iStt2=g.TabEigenschaften.getI(iPos, "aic_stammtyp");
      int iRolle=g.TabEigenschaften.getI(iPos,"Rolle");
      //g.fixtestError(sDt+", Stt="+iStt2+" Cbo="+Cbo.getPreferredSize());
      boolean bKein=(TabS.getI("bits") & (Global.cstAlways * 0x10000)) == 0;
      if (!TabS.isNull("Vec"))
        Cbo.fillCbo("select aic_stamm,kennung,Bezeichnung from stammview2 where"+g.in("aic_stamm",(Vector)TabS.getInhalt("Vec"))+Abfrage.Rolle(iRolle),"Stamm",bKein,false);
      else if (sDt.equals("Mehrfach"))
        Cbo.fillCbo("select p2.aic_stamm,kennung,Bezeichnung from stammview p2 join poolview p on p.sta_aic_stamm=p2.aic_stamm "+
                    "and p.aic_eigenschaft="+TabS.getI("Eig")+" and p.aic_stamm="+iQry,"Stamm",bKein,false);
      else if (iRolle>0)
	Cbo.fillStammRolle(iRolle,g.getSyncStamm(g.iSttFirma),bKein);
      else
	Cbo.fillStammTable(iStt2,bKein);
      Cbo.setSelectedAIC(Tab.getI(sK));
      return Cbo;
    }
    else if (sDt.equals("Hierarchie") || sDt.equals("BewHierarchie"))
    {
      int iPos=g.TabEigenschaften.getPos("Aic", TabS.getI("Eig"));
      int iStt2=g.TabEigenschaften.getI(iPos, "aic_stammtyp");
      HierarchieEingabe Cbo=new HierarchieEingabe(0,iStt2,0,TabS.getI("Eig"),g);
      Cbo.setValue(Tab.getI(TabS.getS("Kennung2")),Tab.getI(sK));
      return Cbo;
    }
    else if (sDt.equals("Pfad") || sDt.equals("Filename"))
    {
      FileEingabe Edt = new FileEingabe(g, !sDt.equals("Pfad"));
      Edt.setValue(Tab.getS(sK));
      return Edt;
    }
    else if (sDt.equals("Integer") || sDt.equals("Double"))
    {
      return new Zahl(Tab.getF(sK),TabS.getI("Laenge"));
    }
    else if (sDt.equals("BewVon_Bis") || sDt.equals("von_bis"))
    {
      VonBisEingabe VB=new VonBisEingabe(Tab.getDate(sK),Tab.getDate(TabS.getS("Kennung2")),TabS.getS("Format"),g,0,0);//istamm,TabS.getI("Laenge"));
      //VB.setValue(Tab.getDate(sK),Tab.getDate(TabS.getS("Kennung2")),Tab.getF(TabS.getS("Kennung3")));
      return VB;
    }
    else if (sDt.equals("Passwort"))
      return new AUPasswort(TabS.getI("Laenge"));
    else if (sDt.equals("Text") || sDt.equals("Memo"))
    {
        /*final MemoF Edt=new MemoF(this,TabS.getS("Bezeichnung"),g,true);
        Edt.Txt.Edt.addFocusListener(new FocusListener()
        {
                        public void focusGained(FocusEvent fe)
                        {
                          //((MemoF)((AUTextArea)fe.getSource()).getParent()).show2();
                          Edt.show2();
                        }
                        public void focusLost(FocusEvent fe)
                        {
                        }
        });*/
        Component Edt=bSteuern ? new AUEkitCore(g,null,7):new AUTextArea(g, 7);
        Edt.setPreferredSize(new Dimension(250*iFF/100, 80*iFF/100));
        if (bSteuern) ((AUEkitCore)Edt).setText(Tab.getM(sK)); else ((AUTextArea)Edt).setText(Tab.getM(sK));
        if (!bSteuern && TabS.getI("Laenge")>0)
        	((AUTextArea)Edt).setMaxLength(TabS.getI("Laenge"));
        return Edt;
    }
    else if (sDt.equals("Filler"))
      return null;
    else
      return new Text(Tab.getS(sK), TabS.getI("Laenge")<1?100:TabS.getI("Laenge"),(TabS.getI("bits2")&Abfrage.cstZiffern)>0 ? Text.ZAHL:(TabS.getI("bits2")&Abfrage.cstBuchZahl)>0 ? Text.SONDER:0);
  }

  public boolean Modified(Object Obj)
  {
    boolean bModified;
    if (Obj==null)
	  bModified=false;
    else if (Obj instanceof AUCheckBox)
	  bModified=((AUCheckBox)Obj).Modified();
    else if (Obj instanceof Datum)
	  bModified=((Datum)Obj).Modified();
    else if (Obj instanceof FileEingabe)
	  bModified=((FileEingabe)Obj).Modified();
    else if (Obj instanceof ComboSort)
      bModified=((ComboSort)Obj).Modified();
    else if (Obj instanceof HierarchieEingabe)
      bModified=((HierarchieEingabe)Obj).Modified();
    else if (Obj instanceof VonBisEingabe)
	  bModified=((VonBisEingabe)Obj).Modified();
    else if (Obj instanceof Text)
	  bModified=((Text)Obj).Modified();
    else if (Obj instanceof Zahl)
	  bModified=((Zahl)Obj).Modified();
    else if (Obj instanceof AUTextArea)
      bModified=((AUTextArea)Obj).Modified();
    else if (Obj instanceof AUEkitCore)
      bModified=((AUEkitCore)Obj).Modified();
    else if (Obj instanceof AUPasswort)
      bModified=((AUPasswort)Obj).Modified();
    else if (Obj instanceof AUComboList)
        bModified=((AUComboList)Obj).Modified();
    else if (Obj instanceof RadioAuswahl)
        bModified=((RadioAuswahl)Obj).Modified();
    else if (Obj instanceof JLabel)
	  bModified=false;
    else
    {
	  Static.printError("Modell_Dialog.Modified(): Objekt "+Obj.getClass().getName()+" kann nicht auf Veränderung geprüft werden!");
	  bModified = false;
    }
    return bModified;
  }

  public boolean isNull(Object Obj)
  {
    if (Obj==null)
      return false;
    else if (Obj instanceof AUCheckBox)
      return ((AUCheckBox)Obj).isNull();
    else if (Obj instanceof Datum)
      return ((Datum)Obj).isNull();
    else if (Obj instanceof FileEingabe)
      return ((FileEingabe)Obj).isNull();
    else if (Obj instanceof ComboSort)
      return ((ComboSort)Obj).isNull();
    else if (Obj instanceof HierarchieEingabe)
      return ((HierarchieEingabe)Obj).isNull();
    else if (Obj instanceof VonBisEingabe)
      return ((VonBisEingabe)Obj).isNull();
    else if (Obj instanceof Text)
      return ((Text)Obj).isNull();
    else if (Obj instanceof Zahl)
      return ((Zahl)Obj).isNull();
    else if (Obj instanceof AUTextArea)
      return ((AUTextArea)Obj).isNull();
    else if (Obj instanceof AUEkitCore)
      return ((AUEkitCore)Obj).isNull();
    else if (Obj instanceof JLabel)
      return false;
    else if (Obj instanceof AUPasswort)
      return ((AUPasswort)Obj).isNull();
    else if (Obj instanceof AUComboList)
        return ((AUComboList)Obj).isNull();
    else if (Obj instanceof RadioAuswahl)
        return ((RadioAuswahl)Obj).isNull();
    else
    {
        Static.printError("Modell_Dialog.isNull(): Objekt "+Obj.getClass().getName()+" kann nicht auf Null geprüft werden!");
        return false;
    }

  }

  public static boolean isValid(Object Obj)
  {
      boolean b=true;
      if(Obj instanceof Datum)
        b=((Datum)Obj).isValid2();
      else if(Obj instanceof VonBisEingabe)
        b=((VonBisEingabe)Obj).isValid2();
      return b;
  }

  private void writeBack()
  {
    TabS.push();
    for (TabD.moveFirst();!TabD.eof();TabD.moveNext())
    {
      Component Comp=(Component)TabD.getInhalt("Comp");
      if (Modified(Comp) && TabS.posInhalt("Aic",TabD.getI("Aic")))
      {
        Object Obj=null;
        if(Comp instanceof AUCheckBox)
          Obj = ((AUCheckBox)Comp).isSelected();
        else if(Comp instanceof Datum)
          Obj = ((Datum)Comp).getDateTime();
        else if(Comp instanceof FileEingabe)
          Obj = ((FileEingabe)Comp).getValue();
        else if(Comp instanceof ComboSort)
          Obj = ((ComboSort)Comp).getSelectedAIC();
        else if(Comp instanceof HierarchieEingabe)
          Obj = ((HierarchieEingabe)Comp).getValueStamm();
        else if(Comp instanceof Text)
          Obj = ((Text)Comp).getText();
        else if (Comp instanceof VonBisEingabe)
          Obj = ((VonBisEingabe)Comp).getVon();
        else if (Comp instanceof Zahl)
          Obj = ((Zahl)Comp).doubleValue();
        else if (Comp instanceof AUTextArea)
          Obj = ((AUTextArea)Comp).getValue();
        else if (Comp instanceof AUEkitCore)
          Obj = ((AUEkitCore)Comp).getValue();
        else if (Comp instanceof AUPasswort)
            Obj = ((AUPasswort)Comp).getValue();
        else if (Comp instanceof AUComboList)
            Obj = ((AUComboList)Comp).getAIC();
        else if (Comp instanceof RadioAuswahl)
            Obj = ((RadioAuswahl)Comp).getNr();
	    g.fixtestInfo("writeBack "+TabS.getS("Bezeichnung")+"("+TabS.getS("Kennung")+"):"+Tab.getInhalt(TabS.getS("Kennung"))+"->"+Obj);
	Tab.setInhalt(TabS.getS("Kennung"), Obj);
        if(Comp instanceof ComboSort)
          Tab.setInhalt(TabS.getS("Kennung3"), ((ComboSort)Comp).getSelectedBezeichnung());
        else if(Comp instanceof AUComboList)
            Tab.setInhalt(TabS.getS("Kennung3"), ((AUComboList)Comp).getComboBox().getSelectedBezeichnung());
        else if(Comp instanceof RadioAuswahl)
            Tab.setInhalt(TabS.getS("Kennung3"), ((RadioAuswahl)Comp).getValue());
        else if (Comp instanceof VonBisEingabe)
          Tab.setInhalt(TabS.getS("Kennung2"), ((VonBisEingabe)Comp).getBis());
        else if (Comp instanceof HierarchieEingabe)
        {
          Tab.setInhalt(TabS.getS("Kennung2"), ((HierarchieEingabe)Comp).getValueStt());
          Tab.setInhalt(TabS.getS("Kennung3"), ((HierarchieEingabe)Comp).toString());
        }
      }
    }
    TabS.pop();
  }

  private boolean EingabenOk()
  {
    Vector<String> Vec=new Vector<String>();
    for (TabD.moveFirst();!TabD.eof();TabD.moveNext())
    {
      Component Comp=(Component)TabD.getInhalt("Comp");
      TabS.posInhalt("Aic",TabD.getI("Aic"));
      if ((TabS.getI("bits") & Abfrage.cstEditierbar) > 0)
      {
    	if ((TabS.getI("bits") & (Global.cstAlways * 0x10000)) > 0 && isNull(Comp))
    	  Vec.addElement("[" + TabS.getS("Bezeichnung") + "] " + g.getShow("benoetigt Wert"));
    	else if (!isValid(Comp))
    	  Vec.addElement("["+TabS.getS("Bezeichnung")+"] "+g.getShow("ist nicht gueltig"));
      }
    }
    if (Vec.size()==0)
      return true;
    else
    {
      String s=Static.VecToString(Vec);
      new Message(Message.WARNING_MESSAGE, this, g,0).showDialog("Fehlerliste", new String[] {s});
      return false;
    }
  }

  private void Build(int riSpalten)
  {
    //getContentPane().setBackground(g.ColEF_Bez);
    TabS.push();
    JPanel PnlCenter = new JPanel(new GridLayout(0,riSpalten,0,0));
    PnlCenter.setBackground(Static.ColEF);
    TabD=new Tabellenspeicher(g,new String[] {"Aic","Comp"});
    int iAbfrage=TabS.getI("Abfrage");
    for(TabS.posInhalt("Abfrage",iAbfrage);!TabS.eof()&&TabS.getI("Abfrage")==iAbfrage;TabS.moveNext())
    {
      if ((TabS.getI("bits")&Abfrage.cstUnsichtbar)>0)
        PnlCenter.add(new JLabel());
      else if ((TabS.getI("bits")&Abfrage.cstAnzeigen)>0)
      {
        boolean bEdit = (TabS.getI("bits") & Abfrage.cstEditierbar) > 0;
        boolean bZwingend = (TabS.getL("bits") & (Global.cstAlways * 0x10000)) > 0;
        Component Comp = getComp(TabS.getS("Datentyp"),(TabS.getL("bits3")&Abfrage.cstHtml)>0);
        if (Comp!=null)
          Comp.setFont(g.fontStandard);
        TabD.addInhalt("Aic", TabS.getI("Aic"));
        TabD.addInhalt("Comp", Comp);
        String sText = Comp instanceof Text ? Tab.getS(TabS.getS("Kennung3")) : Comp instanceof Datum ?
            new DateWOD(Tab.getTimestamp(TabS.getS("Kennung"))).Format(TabS.getS("Format")) : "";
        EF_Eingabe EF = new EF_Eingabe(g, Comp, sText, TabS.getS("Bezeichnung"), bEdit, bZwingend, null /*g.TabEigenschaften.getS("Info")*/,TabS.getS("Datentyp"));
        JPanel Pnl22 = new JPanel(new FlowLayout(FlowLayout.LEFT,2,2));
        Pnl22.setBackground(Static.ColEF);
        Pnl22.add(EF);
        PnlCenter.add(Pnl22);
      }
    }
    //TabD.showGrid("TabD");
    TabS.pop();
    JScrollPane Scroll=new JScrollPane(PnlCenter);
    Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
    getContentPane().add("Center",Scroll);

    JButton BtnOk=g.getButtonS("Ok");
    getRootPane().setDefaultButton(BtnOk);
    JButton BtnAbbruch = g.getButtonS("Abbruch");
    JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
    PnlS.add(BtnOk);
    PnlS.add(BtnAbbruch);
    getContentPane().add("South",PnlS);

    BtnOk.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent ev)
            {
              if (EingabenOk())
              {
                writeBack();
//                setCursor(new Cursor(Cursor.WAIT_CURSOR));
                bOk = true;
                setVisible(false);
              }
            }
    });
    BtnAbbruch.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent ev)
            {
              setVisible(false);
            }
    });

  }

}
