/*
    All_Unlimited-Print-DefVorlagen.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Print;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Schrift;
import All_Unlimited.Allgemein.Eingabe.Text;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.JPopupMenu;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;

public class DefVorlagen extends javax.swing.JPanel
{

  /**
	 *
	 */
	private static final long serialVersionUID = -6445124236080096259L;

public void fillCbo(ComboSort Cbo,Global g,boolean bKartei)
  {
    Cbo.fillCbo("select aic_Raster"+g.AU_BezeichnungFo("Raster")+"Bezeichnung,kennung from Raster where (aic_benutzer is null"+
                (Static.bDefBezeichnung ? "":" and (tod is null or Tod=0)")+" or aic_benutzer="+g.getBenutzer()+")"+
                (bKartei ? " and "+g.bit("bits",Drucken.cstKartei):""),"Raster",true,false);
  }

  public DefVorlagen(Global glob,boolean rbKartei)
  {
	super(new BorderLayout(2,2));
	g=glob;
	//Build();
        bKartei=rbKartei;
        CboVorlagen=new ComboSort(g);
        fillCbo(CboVorlagen,g,bKartei);
        //CboVorlagen.fillDefTable("Raster",true);
        BtnVorlagen=g.getButton("...");
        BtnVorlagen.setMargin(g.inset);

        add("Center",CboVorlagen);
        add("East",BtnVorlagen);

        addEvents();
  }

  private void addEvents()
  {
    AL=new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        String s = ev.getActionCommand();
        if (s.equals("BtnVorlagen"))
        {
          checkBearbeiten();
          Laden();
          FrmVorlagen.show();
        }
        else if (s.equals("BtnBild"))
          BtnBild.LadeBild();
        else if (s.equals("CbxJeder"))
        {
          if (!CbxJeder.isSelected())
            CbxStandard.setSelected(false);
          CbxStandard.setEnabled(g.UserManager() && CbxJeder.isSelected());
        }
        else if (s.equals("CbxStandard"))
        {
          if (CbxStandard.isSelected())
          {
            String sWhere=" where" + g.bit("bits", Drucken.cstStandard)+" and "+(CbxKartei.isSelected()?"":"not ")+g.bit("bits", Drucken.cstKartei);
            int iNr = SQL.getInteger(g, "select aic_raster from raster"+sWhere);
            if (iNr > 0)
              if (new Message(Message.YES_NO_OPTION, (JDialog)FrmVorlagen.thisFrame, g,0).showDialog("Standard_setzen", new String[]
                  {SQL.getString(g, "select kennung from raster where aic_raster=" + iNr)}) == Message.NO_OPTION)
                CbxStandard.setSelected(false);
              else
                g.exec("update raster set bits=bits-" + Drucken.cstStandard + sWhere);
          }
          else if (new Message(Message.YES_NO_OPTION, (JDialog)FrmVorlagen.thisFrame, g,0).showDialog("Standard_entfernen", new String[]
              {}) == Message.NO_OPTION)
            CbxStandard.setSelected(true);
        }
        else if (s.equals("show"))
        {
          JCOutlinerNode Node = OutVorlagen.getSelectedNode();
          int iAIC_Raster = ((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
          String s2=Sort.gets(Node.getLabel());
          new Tabellenspeicher(g,"select kennung"+g.AU_Bezeichnung("abschnitt")+" from abschnitt where aic_raster="+iAIC_Raster,true).showGrid("Raster "+s2,(JDialog)FrmVorlagen.thisFrame);
        }
        else if (s.equals("Neu"))
        {
          NodeAkt = null;
          //checkBearbeiten();
          TxtBezeichnung.setText("");
          TxtKennung.setText("");

          CbxJeder.setSelected(g.UserManager());
          CbxStandard.setSelected(false);
          CbxTod.setSelected(false);

          setEnabled();
        }
        else if (s.equals("Loeschen"))
        {
                  JCOutlinerNode Node = OutVorlagen.getSelectedNode();
                  int iAIC_Raster = ((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue();
                  SQL Qry=new SQL(g);
                  String s1 = Qry.list("defbezeichnung","begriff"+g.join2("druck_zuordnung","begriff")+g.join("abschnitt","druck_zuordnung")+" where aic_raster="+iAIC_Raster);
                  if (s1.equals(" "))
                  {
                    Vector VecVisible=(Vector)NodeAkt.getLabel();
                    String s2=Static.beiLeer((String)VecVisible.elementAt(0),(String)VecVisible.elementAt(1));
                    if(new Message(Message.YES_NO_OPTION,g.getFomLeer(),g).showDialog("Loeschen",new String[] {"["+s2+"]"})==Message.YES_OPTION)
                    {
                      Qry.deleteFrom("Raster","Raster WHERE AIC_Raster=" + iAIC_Raster,g.TabTabellenname.getAic("RASTER"));
                      Node.getParent().removeChild(Node);
                      OutVorlagen.folderChanged(OutVorlagen.getRootNode());
                    }
                  }
                  else
                    new Message(Message.WARNING_MESSAGE,g.getFomLeer(),g).showDialog("BereitsVerwendet",new String[] {"["+s1+"]"});
                  Qry.free();
        }
        else if (s.equals("Ok"))
        {
            if (BtnSpeichern.isEnabled())
              Speichern();
            //CboVorlagen.fillDefTable("Raster", true);
            fillCbo(CboVorlagen,g,bKartei);
            JCOutlinerNode Node = OutVorlagen.getSelectedNode();
            if(Node != null)
              CboVorlagen.setSelectedAIC(((Integer)((Vector)Node.getUserData()).elementAt(0)).intValue());
            FrmVorlagen.hide();
         }
         else if (s.equals("Abbruch"))
         {
          int i=CboVorlagen.getSelectedAIC();
          //CboVorlagen.fillDefTable("Raster", true);
          fillCbo(CboVorlagen,g,bKartei);
          CboVorlagen.setSelectedAIC(i);
          FrmVorlagen.hide();
        }
        else if (s.equals("BtnSpeichern"))
          Speichern();
        else if (s.equals("Vorlagenbits"))
          ShowVorlagenBits();
        //else if (s.equals("Info"))
        else
          Static.printError("DefVorlagen.addEvents: "+s+" wird noch nicht unterstützt");
      }
    };
    g.BtnAdd(BtnVorlagen,"BtnVorlagen",AL);
  }

  private void checkBearbeiten()
  {
    if(FrmVorlagen == null)
    {
      FrmVorlagen = new Formular("VorlageBearbeiten", g.getFomLeer(), g);

      TxtBezeichnung = new Text("", 60);
      TxtKennung = new Text("", 20,Text.KENNUNG);
      FrmVorlagen.getFrei("TxtBezeichnung").add(TxtBezeichnung);
      FrmVorlagen.getFrei("TxtKennung").add(TxtKennung);

      String[] s = new String[] {g.getBegriffS("Show", "Bezeichnung"), g.getBegriffS("Show", "Kennung"), g.getBegriffS("Show", "Art")};
      OutVorlagen.setColumnButtons(s);
      OutVorlagen.setNumColumns(s.length);
      OutVorlagen.setRootVisible(false);
      OutVorlagen.setBackground(Color.white);
      OutVorlagen.setColumnLabelSortMethod(Sort.sortMethod);
      popup= new JPopupMenu();
      g.addMenuItem("Neu",popup).addActionListener(AL);
      g.addMenuItem("Loeschen",popup).addActionListener(AL);
      popup.addSeparator();
      g.addMenuItem("show",popup).addActionListener(AL);
      g.addMenuItem("Vorlagenbits",popup).addActionListener(AL);
      OutVorlagen.getOutliner().addMouseListener(new MouseListener()
      {
        public void mousePressed(MouseEvent ev)
        {}

        public void mouseClicked(MouseEvent ev)
        {
          //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
          if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM) && g.Def())
            popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
        }
        public void mouseEntered(MouseEvent ev) {}
        public void mouseExited(MouseEvent ev)  {}
        public void mouseReleased(MouseEvent ev){}
      });

      FrmVorlagen.getFrei("Outliner").add(OutVorlagen);

      CboSchriftAllgemein = new Schrift(g);
      FrmVorlagen.getFrei("SchriftAllgemein").add(CboSchriftAllgemein);

      BtnBild = new BildEingabe(g.getFomLeer(),g);
      BtnBild.setScale(400,300);
      BtnBild.setFont(g.fontButton);
      FrmVorlagen.getFrei("Bild").add(BtnBild);
      g.BtnAdd(BtnBild,"BtnBild",AL);
      CbxJeder = FrmVorlagen.getCheckbox("Jeder");
      CbxJeder.setSelected(g.UserManager());
      CbxJeder.setEnabled(g.UserManager());
      CbxStandard = FrmVorlagen.getCheckbox("Standard");
      CbxTod = FrmVorlagen.getCheckbox("Tod");
      if (CbxTod==null)
        CbxTod=new AUCheckBox();
      if (!g.Def())
        CbxTod.setVisible(false);
      g.CbxAdd(CbxJeder,"CbxJeder",AL);
      g.CbxAdd(CbxStandard,"CbxStandard",AL);

      CbxRahmen = FrmVorlagen.getCheckbox("Rahmen");
      CbxDoppelt = FrmVorlagen.getCheckbox("DoppelteZeilenhoehe");
      CbxAllgemeinHorizontal = FrmVorlagen.getCheckbox("horizLinien");
      CbxAllgemeinVertikal = FrmVorlagen.getCheckbox("vertLinien");

      CboSchriftLabel = new Schrift(g);
      FrmVorlagen.getFrei("Spaltentitelschrift").add(CboSchriftLabel);
      CboUeberschrift = new Schrift(g);
      CbxRahmen2 = FrmVorlagen.getCheckbox("Rahmen2");
      CbxLU3 = FrmVorlagen.getCheckbox("LinieUnten3");
      FrmVorlagen.getFrei("Ueberschrift").add(CboUeberschrift);
      CboSperrschrift = new Schrift(g);
      FrmVorlagen.getFrei("Sperrschrift").add(CboSperrschrift);
      CboNegativSchrift = new Schrift(g);
      FrmVorlagen.getFrei("negativSchrift").add(CboNegativSchrift);
      CboZwischenschrift = new Schrift(g);
      CbxLO1 = FrmVorlagen.getCheckbox("LinieOben");
      CbxLU1 = FrmVorlagen.getCheckbox("LinieUnten");
      FrmVorlagen.getFrei("Zwischenschrift").add(CboZwischenschrift);
      CboSummenschrift = new Schrift(g);
      CbxLO2 = FrmVorlagen.getCheckbox("LinieOben2");
      CbxLU2 = FrmVorlagen.getCheckbox("LinieUnten2");
      CbxHG_Farbe = FrmVorlagen.getCheckbox("Hintergrundfarbe");
      if (CbxHG_Farbe==null) CbxHG_Farbe=new AUCheckBox();
      CbxSpaltenAbstand = FrmVorlagen.getCheckbox("SpaltenAbstand");
      if (CbxSpaltenAbstand==null) CbxSpaltenAbstand=new AUCheckBox();
      CbxKartei = FrmVorlagen.getCheckbox("Kartei");
      if (CbxKartei==null) CbxKartei=new AUCheckBox();
      FrmVorlagen.getFrei("Summenschrift").add(CboSummenschrift);

      g.BtnAdd(FrmVorlagen.getButton("Neu"),"Neu",AL);

      OutVorlagen.addItemListener(new JCOutlinerListener()
      {
        public void itemStateChanged(JCItemEvent e) {}

        public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {}

        public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {}

        public void outlinerNodeSelectBegin(JCOutlinerEvent e) {}

        public void outlinerNodeSelectEnd(JCOutlinerEvent e)
        {
                NodeAkt=OutVorlagen.getSelectedNode();
                //checkBearbeiten();
                if (NodeAkt.getLevel()>0)
                  setValues();
        }
      });

      g.BtnAdd(FrmVorlagen.getButton("show"),"show",AL);

      BtnDel=FrmVorlagen.getButton("Loeschen");
      g.BtnAdd(BtnDel,"Loeschen",AL);
      g.BtnAdd(FrmVorlagen.getButton("Ok"),"Ok",AL);
      g.BtnAdd(FrmVorlagen.getButton("Abbruch"),"Abbruch",AL);
      BtnSpeichern=FrmVorlagen.getButton("Speichern");
      g.BtnAdd(BtnSpeichern,"BtnSpeichern",AL);

      setEnabled();
  }
}

private void ShowVorlagenBits()
  {
    Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[]
                                                 {"Nr", "Kennung", "Bezeichnung", "Anzahl"});
    addBit(Tab2, "cstAllgemeinHorizontallinien", "horizLinien", Drucken.cstAllgemeinHorizontallinien, 2);
    addBit(Tab2, "cstAllgemeinVertikallinien", "vertLinien", Drucken.cstAllgemeinVertikallinien, 3);
    addBit(Tab2, "cstStandard", "Standard", Drucken.cstStandard, 5);
    addBit(Tab2, "cstRahmen", "Rahmen", Drucken.cstRahmen, 6);
    addBit(Tab2, "cstDoppelt", "DoppelteZeilenhoehe", Drucken.cstDoppelt, 7);
    addBit(Tab2, "cstRahmen2", "Rahmen2", Drucken.cstRahmen2, 8);
    addBit(Tab2, "cstLU3", "LinieUnten3", Drucken.cstLU3, 9);
    addBit(Tab2, "cstLO1", "LinieOben", Drucken.cstLO1, 10);
    addBit(Tab2, "cstLU1", "LinieUnten", Drucken.cstLU1, 11);
    addBit(Tab2, "cstLO2", "LinieOben2", Drucken.cstLO2, 12);
    addBit(Tab2, "cstLU2", "LinieUnten2", Drucken.cstLU2, 13);
    addBit(Tab2, "cstHG_Farbe", "Hintergrundfarbe", Drucken.cstHG_Farbe, 14);
    addBit(Tab2, "cstSpaltenAbstand", "SpaltenAbstand", Drucken.cstSpaltenAbstand, 15);
    addBit(Tab2, "cstKartei", "Kartei", Drucken.cstKartei, 16);
    final JDialog FomGid = new JDialog((JDialog)FrmVorlagen.thisFrame, "Vorlagenbits", false);
    AUOutliner Grid = new AUOutliner();
    FomGid.getContentPane().add("Center", Grid);
    Tab2.showGrid(Grid);
    FomGid.pack();//setSize(400, 600);
    Static.centerComponent(FomGid,FrmVorlagen.thisFrame);
    Grid.addActionListener(new JCActionListener() {
          public void actionPerformed(JCActionEvent ev) {
            JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
            int i=Tabellenspeicher.geti(((Vector)Nod.getLabel()).elementAt(0));
            long l=(long)Math.pow(2,i);
            Tabellenspeicher Tab = new Tabellenspeicher(g,"select kennung"+g.AU_BezeichnungFo("Raster")+"Bezeichnung from Raster where "+g.bit("bits",l),true);
            //if(Tab.FrmGrid != null)
            //  Tab.FrmGrid.dispose();
            Tab.showGrid("Vorlagen mit bit " + i, FomGid);
          }
        });
    FomGid.setVisible(true);
  }

  private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
  {
    Tab2.addInhalt("Nr",i);
    Tab2.addInhalt("Kennung",sConst);
    Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
    Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from Raster where "+g.bit("bits",l)));
  }

private void setValues()
{
  //g.progInfo("setValues");
  Vector VecVisible=(Vector)NodeAkt.getLabel();
                  Vector VecInvisible=(Vector)NodeAkt.getUserData();
                  TxtBezeichnung.setText((String)VecVisible.elementAt(0));
                  TxtKennung.setText((String)VecVisible.elementAt(1));
                  CboSchriftAllgemein.setSelectedAIC(((Integer)VecInvisible.elementAt(1)).intValue());
                  CboUeberschrift.setSelectedAIC(((Integer)VecInvisible.elementAt(2)).intValue());
                  CboSchriftLabel.setSelectedAIC(((Integer)VecInvisible.elementAt(3)).intValue());
                  CboSperrschrift.setSelectedAIC(((Integer)VecInvisible.elementAt(4)).intValue());
                  CboNegativSchrift.setSelectedAIC(((Integer)VecInvisible.elementAt(5)).intValue());
                  CboZwischenschrift.setSelectedAIC(((Integer)VecInvisible.elementAt(6)).intValue());
                  CboSummenschrift.setSelectedAIC(((Integer)VecInvisible.elementAt(7)).intValue());
                  CbxStandard.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstStandard)>0);
                  CbxTod.setSelected(((Boolean)VecInvisible.elementAt(11)).booleanValue());
                  CbxRahmen.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstRahmen)>0);
                  CbxDoppelt.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstDoppelt)>0);
                  CbxAllgemeinHorizontal.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstAllgemeinHorizontallinien)>0);
                  CbxAllgemeinVertikal.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstAllgemeinVertikallinien)>0);
                  CbxRahmen2.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstRahmen2)>0);
                  CbxLU3.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstLU3)>0);
                  CbxLO1.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstLO1)>0);
                  CbxLU1.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstLU1)>0);
                  CbxLO2.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstLO2)>0);
                  CbxLU2.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstLU2)>0);
                  CbxHG_Farbe.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstHG_Farbe)>0);
                  CbxSpaltenAbstand.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstSpaltenAbstand)>0);
                  CbxKartei.setSelected((((Integer)VecInvisible.elementAt(8)).intValue()&Drucken.cstKartei)>0);
                  BtnBild.setValue(g.LoadImage((String)VecInvisible.elementAt(9)),(String)VecInvisible.elementAt(9));
              CbxJeder.setSelected(VecInvisible.elementAt(10)==null);
              setEnabled();
}

private void Speichern()
{
  //SQL Qry=new SQL(g);
  if(NodeAkt!=null && NodeAkt.getLevel()==0)
    NodeAkt=null;
  int iAIC_Raster=NodeAkt==null ? 0:((Integer)((Vector)NodeAkt.getUserData()).elementAt(0)).intValue();
  if(!TxtKennung.isNull() && !SQL.exists(g,"select aic_raster from raster where aic_raster<>"+iAIC_Raster+" and kennung='"+TxtKennung.getText()+"'"))
  {
    SQL Qry=new SQL(g);
    //int iAIC_Raster=0;
    //g.progInfo("iStandard="+iStandard+"/"+CbxStandard.isSelected()+"/"+CbxStandard.Modified());
    /*if (CbxStandard.isSelected())
      if (CbxStandard.Modified() && iStandard>0)
        new Message(Message.WARNING_MESSAGE,(JDialog)FrmVorlagen.thisFrame,g,10).showDialog("StandardVorhanden");
      else
        iStandard++;
    else
      if (CbxStandard.Modified())
        iStandard--;*/
    //g.progInfo("iStandard="+iStandard);
    int iBits=(CbxStandard.isSelected()?Drucken.cstStandard:0)+(CbxRahmen.isSelected()?Drucken.cstRahmen:0)+(CbxDoppelt.isSelected()?Drucken.cstDoppelt:0)+
        (CbxAllgemeinHorizontal.isSelected()?Drucken.cstAllgemeinHorizontallinien:0)+(CbxAllgemeinVertikal.isSelected()?Drucken.cstAllgemeinVertikallinien:0)+
        (CbxRahmen2.isSelected()?Drucken.cstRahmen2:0)+(CbxLU3.isSelected()?Drucken.cstLU3:0)+(CbxLO1.isSelected()?Drucken.cstLO1:0)+
        (CbxLU1.isSelected()?Drucken.cstLU1:0)+(CbxLO2.isSelected()?Drucken.cstLO2:0)+(CbxLU2.isSelected()?Drucken.cstLU2:0)+
        (CbxHG_Farbe.isSelected()?Drucken.cstHG_Farbe:0)+(CbxSpaltenAbstand.isSelected()?Drucken.cstSpaltenAbstand:0)+(CbxKartei.isSelected()?Drucken.cstKartei:0);
    Qry.add("Kennung",TxtKennung.getText());
    Qry.add0("aic_schrift",CboSchriftAllgemein.getSelectedAIC());
    Qry.add0("sch2_aic_schrift",CboUeberschrift.getSelectedAIC());
    Qry.add0("sch_aic_schrift",CboSchriftLabel.getSelectedAIC());
    Qry.add0("sch3_aic_schrift",CboSperrschrift.getSelectedAIC());
    Qry.add0("sch6_aic_schrift",CboNegativSchrift.getSelectedAIC());
    Qry.add0("sch4_aic_schrift",CboZwischenschrift.getSelectedAIC());
    Qry.add0("sch5_aic_schrift",CboSummenschrift.getSelectedAIC());
    Qry.add0("aic_benutzer",CbxJeder.isSelected()?0:g.getBenutzer());
    Qry.add("bits",iBits);
    Qry.add("Tod",CbxTod.isSelected());
    if(NodeAkt==null)
      iAIC_Raster=Qry.insert("Raster",true);
    else
    {
      //iAIC_Raster=((Integer)((Vector)NodeAkt.getUserData()).elementAt(0)).intValue();
      Qry.update("Raster",iAIC_Raster);
    }

    g.setBezeichnung(NodeAkt==null?"":(String)((Vector)NodeAkt.getLabel()).elementAt(0),TxtBezeichnung.getText(),g.TabTabellenname.getAic("RASTER"),iAIC_Raster,0);
    g.setImage(BtnBild.getFilename(),g.TabTabellenname.getAic("RASTER"),iAIC_Raster,0);
    Vector<String> VecVisible=new Vector<String>();
    Vector<Comparable> VecInvisible=new Vector<Comparable>();
    VecVisible.addElement(TxtBezeichnung.getText());
    VecVisible.addElement(TxtKennung.getText());
    VecVisible.addElement(getArt(iBits,CbxTod.isSelected()));//Static.JaNein(CbxStandard.isSelected()));

    VecInvisible.addElement(new Integer(iAIC_Raster));
    VecInvisible.addElement(new Integer(CboSchriftAllgemein.getSelectedAIC()));
    VecInvisible.addElement(new Integer(CboUeberschrift.getSelectedAIC()));
    VecInvisible.addElement(new Integer(CboSchriftLabel.getSelectedAIC()));
    VecInvisible.addElement(new Integer(CboSperrschrift.getSelectedAIC()));
    VecInvisible.addElement(new Integer(CboNegativSchrift.getSelectedAIC()));
    VecInvisible.addElement(new Integer(CboZwischenschrift.getSelectedAIC()));
    VecInvisible.addElement(new Integer(CboSummenschrift.getSelectedAIC()));
    VecInvisible.addElement(new Integer(iBits));
    VecInvisible.addElement(BtnBild.getFilename());
    VecInvisible.addElement(CbxJeder.isSelected()?null:new Integer(g.getBenutzer()));
    VecInvisible.addElement(CbxTod.isSelected());
    if(NodeAkt==null)
      NodeAkt=new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutVorlagen.getRootNode());
    else
      NodeAkt.setLabel(VecVisible);
    NodeAkt.setUserData(VecInvisible);
    OutVorlagen.selectNode(NodeAkt,null);
    setValues();
    OutVorlagen.folderChanged(OutVorlagen.getRootNode());
    Qry.free();
  }
  else
    new Message(Message.WARNING_MESSAGE,(JDialog)FrmVorlagen.thisFrame,g,10).showDialog(TxtKennung.isNull()?"KennungLeer":"KennungVorhanden");

}

private String getArt(int iBits,boolean bTod)
{
  return bTod?"Tod":(iBits&Drucken.cstStandard)>0 ? "Std":null;
}

private void Laden()
{
	JCOutlinerFolderNode NodeRoot=(JCOutlinerFolderNode)OutVorlagen.getRootNode();
	NodeRoot.removeChildren();
	SQL Qry=new SQL(g);

	if(Qry.open("select aic_raster,kennung"+g.AU_BezeichnungFo("raster")+"Bezeichnung"+g.AU_Bild2("raster")+" filename,aic_schrift,sch_aic_schrift,sch2_aic_schrift,sch3_aic_schrift,sch4_aic_schrift,sch5_aic_schrift,sch6_aic_schrift,bits,aic_benutzer,Tod from raster"+
                    " where (aic_benutzer is null"+(Static.bDefBezeichnung ? "":" and (tod is null or Tod=0)")+" or aic_benutzer="+g.getBenutzer()+")"+
                    (bKartei ? " and "+g.bit("bits",Drucken.cstKartei):"")+" order by bezeichnung"))
	{
		for(;!Qry.eof();Qry.moveNext())
		{
			Vector<String> VecVisible=new Vector<String>();
			Vector<Comparable> VecInvisible=new Vector<Comparable>();
			VecVisible.addElement(Qry.getS("bezeichnung"));
			VecVisible.addElement(Qry.getS("kennung"));
                        VecVisible.addElement(getArt(Qry.getI("bits"),Qry.getB("Tod")));//Static.JaNein((Qry.getI("bits")&Drucken.cstStandard)>0));
			VecInvisible.addElement(Qry.getInt("aic_raster"));
			VecInvisible.addElement(new Integer(Qry.getI("aic_schrift")));
			VecInvisible.addElement(new Integer(Qry.getI("sch2_aic_schrift")));
			VecInvisible.addElement(new Integer(Qry.getI("sch_aic_schrift")));
			VecInvisible.addElement(new Integer(Qry.getI("sch3_aic_schrift")));
			VecInvisible.addElement(new Integer(Qry.getI("sch6_aic_schrift")));
			VecInvisible.addElement(new Integer(Qry.getI("sch4_aic_schrift")));
                        VecInvisible.addElement(new Integer(Qry.getI("sch5_aic_schrift")));
			VecInvisible.addElement(Qry.getInt("bits"));
                        VecInvisible.addElement(Qry.getS("filename"));
                        VecInvisible.addElement(Qry.getInt("aic_benutzer"));
                        VecInvisible.addElement(Qry.getB("Tod"));
                        //if ((Qry.getI("bits")&Drucken.cstStandard)>0)
                        //  iStandard++;
			JCOutlinerNode Node=new JCOutlinerNode(VecVisible,NodeRoot);
			Node.setUserData(VecInvisible);
                        if (Qry.getI("aic_raster")==CboVorlagen.getSelectedAIC())
                          OutVorlagen.selectNode(Node,null);
		}
		Qry.close();
	}
	Qry.free();

	OutVorlagen.folderChanged(NodeRoot);
}

public void setSelectedAIC(int iAIC)
{
	CboVorlagen.setSelectedAIC(iAIC);
}

public int getSelectedAIC()
{
	return CboVorlagen.getSelectedAIC();
}

public String getBezeichnung(int iAIC)
{
	return CboVorlagen.getBezeichnung(iAIC);
}

public String getSelectedBezeichnung()
{
	return CboVorlagen.getSelectedBezeichnung();
}

public void setEnabled(boolean bEnabled)
{
	BtnVorlagen.setEnabled(bEnabled);
	CboVorlagen.setEnabled(bEnabled);
}

  private void setEnabled()
  {
    boolean bIch=!CbxJeder.isSelected();
    if (bIch)
      CbxStandard.setSelected(false);
    CbxStandard.setEnabled(g.UserManager() && CbxJeder.isSelected());
    if (BtnSpeichern!= null)
      BtnSpeichern.setEnabled(g.UserManager() || bIch);
    if (BtnDel!= null)
      BtnDel.setEnabled(g.UserManager() || bIch);
  }


public void setFont(Font font)
{
        if (g !=null)
	{
          if (font == null)
            font = g.fontStandard;
        }
        if (font != null && CboVorlagen != null)
          CboVorlagen.setFont(font);
}

// add your data members here
private Global g;
private ComboSort CboVorlagen;
private JButton BtnVorlagen;

private Formular FrmVorlagen=null;
private AUOutliner OutVorlagen=new AUOutliner(new JCOutlinerFolderNode("Root"));
private JCOutlinerNode NodeAkt=null;
//private JButton BtnNew;
private JButton BtnSpeichern;
private JButton BtnDel;
//private JButton BtnOk;
//private JButton BtnAbbruch;

private Schrift CboSchriftLabel;
private Schrift CboSchriftAllgemein;
private Schrift CboUeberschrift;
private Schrift CboSperrschrift;
private Schrift CboNegativSchrift;
private Schrift CboZwischenschrift;
private Schrift CboSummenschrift;

private AUCheckBox CbxJeder;
private AUCheckBox CbxStandard;
private AUCheckBox CbxTod;
private AUCheckBox CbxRahmen;
private AUCheckBox CbxDoppelt;
private AUCheckBox CbxAllgemeinHorizontal;
private AUCheckBox CbxAllgemeinVertikal;
private AUCheckBox CbxRahmen2;
private AUCheckBox CbxLU1;
private AUCheckBox CbxLU2;
private AUCheckBox CbxLU3;
private AUCheckBox CbxLO1;
private AUCheckBox CbxLO2;
private AUCheckBox CbxHG_Farbe;
private AUCheckBox CbxSpaltenAbstand;
private AUCheckBox CbxKartei;

private Text TxtBezeichnung;
private Text TxtKennung;

private BildEingabe BtnBild;
private ActionListener AL;
private JPopupMenu popup=null;
private boolean bKartei=false;
//private int iStandard=0;
//private int iTabRaster;

//private String sJa;
//private String sNein;


}

