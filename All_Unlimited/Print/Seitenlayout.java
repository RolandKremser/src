package All_Unlimited.Print;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
//import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Allgemein.Eingabe.Zeile;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.Schrift;
import java.awt.BorderLayout;
import java.awt.Image;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 4.3.2005</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class Seitenlayout extends Formular
{
  private Global g;
  private static int iAic=0;
  private ComboSort CboExtern;
  private ComboSort Cbo;
  private ComboSort CboStamm;
  private Schrift CboSchrift;
  private Text Txt=new Text("",30);
  private JCheckBox CbxJeder;
  private JCheckBox CbxStandard;
  private JRadioButton RadHoch;
  private JRadioButton RadQuer;
  private JCheckBox CbxA3;
  private JCheckBox CbxFarbe;
  private Zahl niLinks=new Zahl(20);
  private Zahl niRechts=new Zahl(15);
  private Zahl niUnten=new Zahl(15);
  private Zahl niOben=new Zahl(15);
  private JButton BtnRefresh=null;
  private JButton BtnNeu;
  private JButton BtnSave;
  private JButton BtnDel;
  private JButton BtnOk;
  private JButton BtnAbbruch;

  private AUTextArea TxfKopfLinks=new AUTextArea();
  private AUTextArea TxfKopfZentrum=new AUTextArea();
  private AUTextArea TxfKopfRechts=new AUTextArea();
  private AUTextArea TxfFussLinks=new AUTextArea();
  private AUTextArea TxfFussZentrum=new AUTextArea();
  private AUTextArea TxfFussRechts=new AUTextArea();
  private BildEingabe BtnKopf;
  private BildEingabe BtnFuss;
  private Zeile CboKopf;
  private Zeile CboFuss;
  private boolean bFK=false;
  private boolean bFF=false;
  //private JCheckBox CbxFK;
  //private JCheckBox CbxFF;
  private static Seitenlayout Self=null;

  public static Seitenlayout get(Global rg,ComboSort rCbo)
  {
    //int iPos=iAic==0 ? -1:rg.TabFormulare.getPos("Aic",iAic);
    //Self= iPos>=0 ? ((Seitenlayout)rg.TabFormulare.getInhalt("Formular",iPos)) : new Seitenlayout(rg,rCbo);
    return Self==null ? new Seitenlayout(rg,rCbo):Self;
  }

  public static void free()
  {
        if (Self!=null)
        {
                Self.g.testInfo("Seitenlayout.free");
                Zeile.free(Self.g);
                Self.thisFrame.dispose();
                Self=null;
        }
  }

  public static ComboSort getCbo(final Global g,JPanel PnlLayout)
  {
    final ComboSort CboLayout=new ComboSort(g);
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
    return CboLayout;
  }

  private Seitenlayout(Global rglobal,ComboSort rCbo)
  {
    super("Seitenlayout", rglobal.getFomLeer(), rglobal);
    g=rglobal;
    //g.testInfo("Seitenlayout.create");
    Self=this;
    CboExtern=rCbo;
    iAic=getBegriff();
    g.putTabFormulare(iAic,0,this);
    Build();
    setEnabled();
  }

  public static void fillCbo(ComboSort Cbo,Global g)
  {
    Cbo.fillCbo("select aic_layout,name bezeichnung,null kennung from layout where (aic_benutzer is null or aic_benutzer="+g.getBenutzer()+
                ") and (aic_mandant is null or aic_mandant="+g.getMandant()+")","Layout",true,false);
  }

  private void Build()
  {
    // oben
    Cbo=new ComboSort(g);
    fillCbo(Cbo,g);
    getFrei("Combobox").add(Cbo);
    getFrei("Textfeld").add(Txt);
    Cbo.addItemListener(new ItemListener ()
    {
            public void itemStateChanged(ItemEvent ev)
            {
                    if(ev.getStateChange() == ItemEvent.SELECTED)
                      fill();
            }
    });

    // Seitenlayout
    CbxJeder=getCheckbox("Jeder");
    CbxJeder.setSelected(g.UserManager());
    CbxJeder.setEnabled(g.UserManager());
    CbxStandard=getCheckbox("Standard");
    CboStamm=new ComboSort(g);
    CboStamm.fillStammTable(g.iSttFirma,true);
    CboSchrift=new Schrift(g);
    //CboSchrift.getCboSchrift().setFont(g.fontTitel);
    JPanel PnlStamm=getFrei("Combo Stammsatz");
    JPanel PnlSchrift=getFrei("SchriftAllgemein");
    if (PnlStamm != null)
      PnlStamm.add(CboStamm);
    if (PnlSchrift != null)
      PnlSchrift.add(CboSchrift);
    //CbxStandard.setEnabled(g.UserManager());
    /*CbxJeder.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
          if (!CbxJeder.isSelected())
            CbxStandard.setSelected(false);
          CbxStandard.setEnabled(g.UserManager() && CbxJeder.isSelected());
        }
    });
    CbxStandard.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
          if (CbxStandard.isSelected())
          {
            int iNr=SQL.getInteger(g,"select aic_layout from layout where"+g.bit("bits",Drucken.cstLayStd)+" and aic_mandant="+g.getMandant());
            if (iNr>0)
              if (new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Standard_setzen",new String[] {SQL.getString(g,"select name from layout where aic_layout="+iNr)})==Message.NO_OPTION)
                CbxStandard.setSelected(false);
              else
                g.exec("update layout set bits=bits-"+Drucken.cstLayStd+" where"+g.bit("bits",Drucken.cstLayStd)+" and aic_mandant="+g.getMandant());
          }
          else if(new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Standard_entfernen",new String[] {})==Message.NO_OPTION)
            CbxStandard.setSelected(true);
        }
    });*/


    RadHoch=getRadiobutton("Hochformat");
    RadQuer=getRadiobutton("Querformat");
    RadHoch.setSelected(true);
    CbxA3=getCheckbox("A3");
    CbxFarbe=getCheckbox("Farbe");
    if(CbxFarbe==null) CbxFarbe = new JCheckBox();

    // Seitenränder
    getFrei("Abstand Oben").add(niOben);
    getFrei("Abstand Unten").add(niUnten);
    getFrei("Abstand Rechts").add(niRechts);
    getFrei("Abstand Links").add(niLinks);

    // Kopf- und Fußzeile
    JPanel PnlKText=getFrei("Kopfzeile Text");
    TxfKopfLinks.setEditable(false);
    TxfKopfZentrum.setEditable(false);
    //TxfKopfZentrum.Edt.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
    TxfKopfRechts.setEditable(false);
    //TxfKopfRechts.Edt.setAlignmentY(java.awt.Component.RIGHT_ALIGNMENT);
    PnlKText.add(TxfKopfLinks);
    PnlKText.add(TxfKopfZentrum);
    PnlKText.add(TxfKopfRechts);
    JPanel PnlFText=getFrei("Fußzeile Text");
    TxfFussLinks.setEditable(false);
    TxfFussZentrum.setEditable(false);
    //TxfFussZentrum.Edt.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
    TxfFussRechts.setEditable(false);
    //TxfFussRechts.Edt.setAlignmentY(java.awt.Component.RIGHT_ALIGNMENT);
    PnlFText.add(TxfFussLinks);
    PnlFText.add(TxfFussZentrum);
    PnlFText.add(TxfFussRechts);
    BtnKopf=new BildEingabe(thisFrame,g);
    BtnKopf.setScale(550,100);
    BtnFuss=new BildEingabe(thisFrame,g);
    BtnFuss.setScale(550,100);
    getFrei("Kopfzeile Bild").add(BtnKopf);
    getFrei("Fußzeile Bild").add(BtnFuss);
    CboKopf=new Zeile(g,thisFrame);
    CboFuss=new Zeile(g,thisFrame);
//    CboKopf.setOther(CboFuss);
//    CboFuss.setOther(CboKopf);
    getFrei("Combo Kopfzeile").add(CboKopf);
    getFrei("Combo Fusszeile").add(CboFuss);
    /*CbxFK=getCheckbox("Firma-Kopfzeile");
    CbxFF=getCheckbox("Firma-Fusszeile");
    CbxFK.setVisible(false);
    CbxFF.setVisible(false);*/

    CboKopf.Cbo.addItemListener(new ItemListener ()
          {
                  public void itemStateChanged(ItemEvent ev)
                  {
                          if (CboKopf.TabZeilen.posInhalt("aic_zeile",CboKopf.Cbo.getSelectedAIC()))
                          {
                            TxfKopfLinks.setText(Zeile.checkParameters(g,CboKopf.TabZeilen.getS("Links")));
                            TxfKopfZentrum.setText(Zeile.checkParameters(g,CboKopf.TabZeilen.getS("Mitte")));
                            TxfKopfRechts.setText(Zeile.checkParameters(g,CboKopf.TabZeilen.getS("Rechts")));
                            BtnKopf.setValue(CboKopf.TabZeilen.isNull("Bild") ? null:SQL.getImageZ(g,CboKopf.TabZeilen.getI("aic")),CboKopf.TabZeilen.getS("Bild"));
//                            BtnKopf.setValue(g.LoadImage(CboKopf.TabZeilen.getS("Bild"),Static.DirImageDef),CboKopf.TabZeilen.getS("Bild"));
                          }
                          /*else
                          {
                            TxfKopfLinks.setText("");
                            TxfKopfZentrum.setText("");
                            TxfKopfRechts.setText("");
                            BtnKopf.setValue(null,"");
                          }*/
                  }
          });
          CboFuss.Cbo.addItemListener(new ItemListener ()
          {
              public void itemStateChanged(ItemEvent ev)
              {
                      if (CboFuss.TabZeilen.posInhalt("aic_zeile",CboFuss.Cbo.getSelectedAIC()))
                      {
                        TxfFussLinks.setText(Zeile.checkParameters(g,CboFuss.TabZeilen.getS("Links")));
                        TxfFussZentrum.setText(Zeile.checkParameters(g,CboFuss.TabZeilen.getS("Mitte")));
                        TxfFussRechts.setText(Zeile.checkParameters(g,CboFuss.TabZeilen.getS("Rechts")));
                        BtnFuss.setValue(CboFuss.TabZeilen.isNull("Bild") ? null:SQL.getImageZ(g,CboFuss.TabZeilen.getI("aic")),CboFuss.TabZeilen.getS("Bild"));
//                        BtnFuss.setValue(g.LoadImage(CboFuss.TabZeilen.getS("Bild"),Static.DirImageDef),CboFuss.TabZeilen.getS("Bild"));
                      }
                      /*else
                      {
                        TxfFussLinks.setText("");
                        TxfFussZentrum.setText("");
                        TxfFussRechts.setText("");
                        BtnFuss.setValue(null,"");
                      }*/
              }
          });

    // Knöpfe
    BtnRefresh=getButton("Refresh");
    BtnNeu=getButton("Neu");
    BtnSave=getButton("Speichern");
    BtnDel=getButton("Loeschen");
    BtnOk=getButton("Ok");
    BtnAbbruch=getButton("Abbruch");
    ActionListener AL=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        Object Btn = ev.getSource();
        if (Btn == BtnNeu)
        {
          Cbo.setSelectedAIC(0);
          Txt.setText("");
          CbxJeder.setSelected(g.UserManager());
          setEnabled();
        }
        else if (Btn == BtnSave)
        {
          if (Txt.getText().equals(""))
            new Message(Message.WARNING_MESSAGE, (JFrame)null, g, 10).showDialog("BezeichnungFehlt");
          else if (Txt.getText().length()>30)
        	  new Message(Message.WARNING_MESSAGE, (JFrame)null, g, 10).showDialog("zuLang");
          else
            Save();
        }
        else if (Btn == BtnDel)
        {
          int iAic = Cbo.getSelectedAIC();
          if (new Message(Message.YES_NO_OPTION, null, g).showDialog("Loeschen", new String[] {"[" + Cbo.getSelectedBezeichnung() + "]"}) == Message.YES_OPTION)
            g.exec("delete from Layout where aic_Layout=" + iAic);
          fillCbo(Cbo, g);
          Cbo.setSelectedAIC(0);
        }
        else if (Btn == BtnOk)
        {
          fillCbo(CboExtern,g);
          CboExtern.setSelectedAIC(Cbo.getSelectedAIC());
          hide();
        }
        else if (Btn == BtnAbbruch)
        {
          int iAic=CboExtern.getSelectedAIC();
          fillCbo(CboExtern,g);
          CboExtern.setSelectedAIC(iAic);
          hide();
        }
        else if (Btn == BtnRefresh)
          fillCbo(Cbo,g);
      }
    };
    BtnNeu.addActionListener(AL);
    BtnSave.addActionListener(AL);
    BtnDel.addActionListener(AL);
    if (BtnRefresh!=null) BtnRefresh.addActionListener(AL);
    BtnOk.addActionListener(AL);
    BtnAbbruch.addActionListener(AL);

    Cbo.setSelectedAIC(CboExtern.getSelectedAIC());

  }

  private void fill()
  {
    int iAic=Cbo.getSelectedAIC();
    if (iAic>0)
    {
      SQL Qry=new SQL(g);
      Qry.open("select * from layout where aic_layout="+iAic);
      if (!Qry.eof())
      {
        Txt.setText(Qry.getS("Name"));
        niLinks.setValue(Qry.getI("Links"));
        niRechts.setValue(Qry.getI("Rechts"));
        niOben.setValue(Qry.getI("Oben"));
        niUnten.setValue(Qry.getI("Unten"));
        TxfKopfLinks.setText("");
        TxfKopfZentrum.setText("");
        TxfKopfRechts.setText("");
        BtnKopf.setValue(null,"");
        TxfFussLinks.setText("");
        TxfFussZentrum.setText("");
        TxfFussRechts.setText("");
        BtnFuss.setValue(null,"");
        CboKopf.Cbo.setSelectedAIC(Qry.getI("AIC_ZEILE"));
        CboFuss.Cbo.setSelectedAIC(Qry.getI("ZEI_AIC_ZEILE"));
        CboStamm.setSelectedAIC(Qry.getI("AIC_STAMM"));
        CboSchrift.setSelectedAIC(Qry.getI("aic_schrift"));
        CbxJeder.setSelected(Qry.getI("AIC_Benutzer")==0);
        int iBits=Qry.getI("Bits");
        RadHoch.setSelected((iBits&Drucken.cstLayQuer)==0);
        RadQuer.setSelected((iBits&Drucken.cstLayQuer)>0);
        CbxA3.setSelected((iBits&Drucken.cstLayA3)>0);
        CbxFarbe.setSelected((iBits&Drucken.cstFarbe)>0);
        CbxStandard.setSelected((iBits&Drucken.cstLayStd)>0);
        bFK=(iBits&Drucken.cstLayFK)>0;
        bFF=(iBits&Drucken.cstLayFF)>0;
        setEnabled();
      }
      Qry.free();
    }
  }

  private void setEnabled()
  {
    boolean bIch=!CbxJeder.isSelected();
    //if (bIch)
    //  CbxStandard.setSelected(false);
    CbxStandard.setEnabled(g.UserManager());// && CbxJeder.isSelected());
    BtnSave.setEnabled(g.UserManager() || bIch);
    BtnDel.setEnabled(g.UserManager() || bIch);
  }

  private void Save()
  {
    SQL Qry=new SQL(g);
    Qry.add("Name",Txt.getText());
    Qry.add("Links",niLinks.intValue());
    Qry.add("Rechts",niRechts.intValue());
    Qry.add("Oben",niOben.intValue());
    Qry.add("Unten",niUnten.intValue());
    Qry.add0("AIC_ZEILE",CboKopf.Cbo.getSelectedAIC());
    Qry.add0("ZEI_AIC_ZEILE",CboFuss.Cbo.getSelectedAIC());
    Qry.add0("AIC_Benutzer",CbxJeder.isSelected()?0:g.getBenutzer());
    Qry.add("AIC_Mandant",g.getMandant());
    Qry.add0("AIC_Stamm",CboStamm.getSelectedAIC());
    Qry.add0("AIC_Schrift",CboSchrift.getSelectedAIC());
    Qry.add("Bits",(RadQuer.isSelected() ? Drucken.cstLayQuer:0)+(CbxA3.isSelected()?Drucken.cstLayA3:0)+(CbxStandard.isSelected()?Drucken.cstLayStd:0)
	+(CbxFarbe.isSelected()?Drucken.cstFarbe:0)+(bFK?Drucken.cstLayFK:0)+(bFF?Drucken.cstLayFF:0));
    int iAic=Cbo.getSelectedAIC();
    if (iAic==0)
      iAic=Qry.insert("Layout",true);
    else
      Qry.update("Layout",iAic);
    Qry.free();
    fillCbo(Cbo,g);
    Cbo.setSelectedAIC(iAic);
  }

}
