package All_Unlimited.Grunddefinition;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Eingabe.*;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Abfrage;
import jclass.bwt.*;
import All_Unlimited.Allgemein.Static;
import java.awt.event.ActionListener;
//import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.Vector;
//import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.9
 */
public class DefAbfrage2 extends Formular
{
  private Global g;
  private AUOutliner Out1 = new AUOutliner(new JCOutlinerFolderNode(""));
  private AUOutliner Out2 = new AUOutliner(new JCOutlinerFolderNode(""));
  private JCOutlinerFolderNode Root1;
  private JCOutlinerFolderNode Root2;
  private int iTyp=0;
  private int iArt=0;
  private int iNeu=1;
  public ShowAbfrage A;
  public boolean bOk=false;
  private ComboSort CboBenutzergruppe;
  private ComboSort CboMandant;
  private JLabel LblInfo;
  private JPanel PnlCombo;
  //private JRadioButton RadZ;
  //private JRadioButton RadS;
  private JRadioButton RadA;
  private JRadioButton RadD;
  private JRadioButton RadPers;
  private JRadioButton RadBG;
  private JRadioButton RadJeder;
  //private JRadioButton RadStamm;
  private JRadioButton RadStd;
  private JButton BtnWeg;
  private JButton BtnErsetzen;
  private JButton BtnHinzu;
  private JButton BtnRauf;
  private JButton BtnRunter;
  private JButton BtnOk2;
//  private JButton BtnOk3;
  private JButton BtnEdit;
  private JButton BtnOk;
  private JButton BtnAbbruch;
  private JButton BtnInit;
  private JButton BtnSave;
  private JButton BtnBack;
  private JButton BtnBeenden;
  private AUCheckBox CbxMobil;
  private AUCheckBox CbxZwingend;
  private AUCheckBox CbxNimm;
  private AUCheckBox CbxVZD;
  private boolean bEdit=false;
  private boolean bReady=false;
  private static DefAbfrage2 Self;
  private int iStatus=0;
  //private JPanel PnlEingabe;
  private Format EdtFormat;
  private ComboSort CboFarbe;
  private Text EdtTitel;
  private Text EdtTitel2;
  private int iSttFill=0;
  private int iNrFill=0;
  private Tabellenspeicher TabSE=null;
  private boolean bHA=false;
//  private boolean bSE=false;
  private Color ColOk=null;
  private Color ColNot=null;
  private boolean bRefresh=false;
  private int iANR=0;

  public static final int OUTLINER=0;
  public static final int TABELLE =1;
  public static final int DRUCK   =2;
  
  public static final int SHOW = 1; // zeigen
  public static final int SoDe = 2; // Sort Desc
  public static final int MOBIL= 4; // nur auf Mobile-Devices im Web
  public static final int ZWANG= 8; // Eingabe zwingend erforderlich
  public static final int NIMM= 16; // verwendet diese Bezeichnung auch im Web
  public static final int VZD=  32; // Vorzeichen drehen

  public static DefAbfrage2 get(Global glob,int riTyp,ShowAbfrage Abf,JFrame Fom)
  {
    if (Self==null)
      Self=new DefAbfrage2(glob,riTyp,Abf,Fom);
    else
    {
      Self.iTyp=riTyp;
      Self.iArt=0;
      Self.iNeu=1;
      Self.A=Abf;
      Self.VecTC.elementAt(0).setSelectedIndex(0);
      Self.EnableButtons();
    }
    Self.setTitle(glob.getBegBez(Abf.iBegriff));
    Self.TabSE=null;
    Self.readBG();
    Self.iANR=glob.getSyncStamm(glob.iSttANR);
    if (glob.Def() && riTyp==DRUCK)
      Self.Refresh("Def-Start");
    Self.checkRad();
    Self.show();
    return Self;
  }

  public static void free()
  {
    if (Self != null)
    {
      Self.g.testInfo("DefAbfrage2.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

  private DefAbfrage2(Global glob,int riTyp,ShowAbfrage Abf,JFrame Fom)
  {
    super("Abfrage2",Fom,glob);
    g=glob;
    iTyp=riTyp;
    A=Abf;
    Build();
  }

  private void Build()
  {
    // Zuordnung (Folder 1)
    EdtFormat=new Format(g,"Spalte",thisFrame);
    getFrei("Eingabe").add(EdtFormat);
    /*ItemListener IL=new ItemListener ()
    {
      @SuppressWarnings("unchecked")
      public void itemStateChanged(ItemEvent ev)
      {
              if(ev.getStateChange() == ItemEvent.SELECTED)
              {
                JCOutlinerNode Nod=Out1.getSelectedNode();
                boolean bFormat=ev.getSource()==EdtFormat.Cbo;
                //if (Nod!=null)
                //  g.fixInfo("bFormat="+bFormat+";"+Nod.getLevel()+":"+Nod.getLabel()+";"+EdtFormat.getText()+", Userdata="+Nod.getUserData());
                if(Nod != null && Nod.getLevel() > 0 && A.TabSp.posInhalt("Nummer", Nod.getUserData())) {
                  //g.fixInfo(Nod + "/" + EdtFormat.getText());
                  if (A.TabSp.getI("rel")>0)
                  {
                    //g.fixInfo("Bez vorher:"+A.TabSp.getS("Bezeichnung"));
                    while (!Sort.gets(Nod.getLabel(),0).equals(A.TabSp.getS("Bezeichnung")))
                      A.TabSp.moveNext();
                    //g.fixInfo("Bez nachher:"+A.TabSp.getS("Bezeichnung"));
                  }
                  if (bFormat)
                  {
                    ((Vector)Nod.getLabel()).setElementAt(EdtFormat.getText(), 1);
                    A.TabSp.setInhalt("aic_begriff", EdtFormat.getAIC());
                  }
                  else
                  {
                    ((Vector)Nod.getLabel()).setElementAt(CboFarbe.getSelectedBezeichnung(),3);
                    A.TabSp.setInhalt("aic_farbe", CboFarbe.getSelectedAIC());
                  }
                  Out1.folderChanged(Root1);
                }
              }
      }
    };*/
    //EdtTitel2.addItemListener(IL);
    //EdtFormat.Cbo.addItemListener(IL);
    ActionListener ALok=new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
    	  OkTitel2();
      }
    };
    EdtFormat.Cbo.addActionListener(ALok);  
    CbxMobil=getCheckbox("Mobil");
    if (CbxMobil==null) CbxMobil=new AUCheckBox("Mobil");
    CbxMobil.addActionListener(ALok);
    CbxZwingend=getCheckbox("Always");
    if (CbxZwingend==null) CbxZwingend=new AUCheckBox("Always");
    CbxZwingend.addActionListener(ALok);
    CbxNimm=getCheckbox("take Bez");
    if (CbxNimm==null) CbxNimm=new AUCheckBox("take Bez");
    CbxNimm.addActionListener(ALok);
    CbxVZD=getCheckbox("VZ_drehen");
    if (CbxVZD==null) CbxVZD=new AUCheckBox("VZ_drehen");
    CbxVZD.addActionListener(ALok);
    CboFarbe=new ComboSort(g);
    CboFarbe.fillFarbe(false);
    CboFarbe.addActionListener(ALok);
    //CboFarbe.addItemListener(IL);
    /*CboFarbe.addItemListener(new ItemListener ()
                {
                  @SuppressWarnings("unchecked")
                  public void itemStateChanged(ItemEvent ev)
                  {
                          if(ev.getStateChange() == ItemEvent.SELECTED)
                          {
                            JCOutlinerNode Nod=Out1.getSelectedNode();
                            if(CboFarbe.Modified() && Nod != null && Nod.getLevel() > 0 && A.TabSp.posInhalt("Nummer", Nod.getUserData())) {
                              //g.progInfo(Nod + "/" + EdtFormat.getText());
                              ((Vector)Nod.getLabel()).setElementAt(CboFarbe.getSelectedBezeichnung(),2);
                              A.TabSp.setInhalt("aic_farbe", CboFarbe.getSelectedAIC());
                              Out1.folderChanged(Root1);
                            }
                          }
                  }
                });*/

    getFrei("Farbe").add(CboFarbe);
    // Sortiert (Folder 2)
    RadA = getRadiobutton("aufsteigend");
    RadD = getRadiobutton("absteigend");
    // Horiz. Auflösung (Folder 3)
    EdtTitel=new Text("",99);
    EdtTitel2=new Text("",99);
    EdtTitel2.addActionListener(ALok);
    RadPers=getRadiobutton("Abf_Pers");
    RadBG=getRadiobutton("Abf_BG");

    //if (RadBG!=null && !g.Abfrage())
    //  RadBG.setEnabled(false);
    RadJeder=getRadiobutton("Abf_Jeder");
    //RadStamm=getRadiobutton("Abf_Stamm");
    RadStd=getRadiobutton("Abf_Std");
    if (RadStd!=null)
    {
      RadStd.setSelected(true);
      ColOk=RadStd.getForeground();
      ColNot=Color.RED;
    }
    //if (RadJeder!=null && !g.Spezial() && !g.SuperUser())
    //  RadJeder.setEnabled(false);
    /*EdtTitel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        g.progInfo("actionPerformed:"+EdtTitel.getText());
        JCOutlinerNode Nod=Out1.getSelectedNode();
        int iL=Nod==null ? 0:Nod.getLevel();
        if (iArt==3 && iL==2)
        {
          Nod.setLabel(EdtTitel.getText());
          Out1.folderChanged(Nod.getParent());
          //merkeHA();
        }
      }
    });*/
    getFrei("Titel").add(EdtTitel);
    JPanel PnlTitel2=getFrei("Titel2");
    if (PnlTitel2!=null) PnlTitel2.add(EdtTitel2);
    // Mandant
    CboMandant=new ComboSort(g,ComboSort.Farbe);
    CboMandant.fillDefTable("Mandant", true);
    CboMandant.setSelectedAIC(g.getMandant());
    CboMandant.setEnabled(g.Def());
    Abfrage.readSpalten2(g,g.getMandant());
    CboMandant.addItemListener(new ItemListener ()
	{
		public void itemStateChanged(ItemEvent ev)
		{
			if(ev.getStateChange() == ItemEvent.SELECTED)
			{
				Abfrage.readSpalten2(g,CboMandant.getSelectedAIC());
				Refresh("Mandant");
			}
		}
	});
    JPanel PnlMandant=getFrei("Mandant");
    if (PnlMandant != null)
    	PnlMandant.add(CboMandant);
    // Benutzergruppe
    CboBenutzergruppe=new ComboSort(g,ComboSort.Farbe);
    LblInfo=new JLabel();
    LblInfo.setFont(g.fontBezeichnung);
    //readBG();
    //CboBenutzergruppe.fillDefTable("BENUTZERGRUPPE",true);
    PnlCombo=getFrei("Combobox");
    if (PnlCombo != null)
      PnlCombo.add(LblInfo);
    CboBenutzergruppe.setEnabled(g.Abfrage());

    // Outliner
    Root1=(JCOutlinerFolderNode)Out1.getRootNode();
    Root2=(JCOutlinerFolderNode)Out2.getRootNode();
    Static.addContainer(getFrei("Outliner"),Out1);
    Static.addContainer(getFrei("Outliner Sub"),Out2);
    Out1.setColumnLabelSort(false);
    Out1.setRootVisible(false);
    Out2.setRootVisible(false);
    //RadZ = g.getRadiobutton("Zuordnung");
    //RadS = g.getRadiobutton("Sortierung");
    //RadZ.setSelected(true);
    //EdtFormat Text=new Text("",10);
    //if (PnlEingabe != null)
    //  PnlEingabe.add(new Text("",10));
    VecTC.elementAt(0).addChangeListener(new ChangeListener ()
    {
      public void stateChanged(ChangeEvent ev)
      {
        iNeu=((JTabbedPane)ev.getSource()).getSelectedIndex()+1;
        //g.testInfo("Tab="+iNeu);
        EnableButtons();
      }
    });

    ActionListener AL=new ActionListener()
    {
      @SuppressWarnings("unchecked")
      public void actionPerformed(ActionEvent e)
      {
        String s = e.getActionCommand();
        g.progInfo("Action="+s);
        if (s.equals("Speichern"))
        {
          if (bEdit && (RadBG==null || !RadBG.isSelected() || RadBG.isEnabled()) && (RadJeder==null || !RadJeder.isSelected() || RadJeder.isEnabled()))
          {
            saveIntern();
            saveDb();
          }
          else
            g.fixInfo("Speichern nicht erlaubt");
          if (BtnEdit!=null) bEdit=false;
          EnableButtons();
        }
        else if (s.equals("Ok"))
        {
        	saveIntern();
        	bOk = true;
            hide();
        }
        else if (s.equals("Beenden"))
        {
          if (BtnEdit!=null) bEdit=false;
          bOk=false;
          hide();
          Abfrage.readSpalten2(g,g.getMandant());
        }
        else if (s.equals("Init"))
        {
          if ((RadBG==null || !RadBG.isSelected() || RadBG.isEnabled()) && (RadJeder==null || !RadJeder.isSelected() || RadJeder.isEnabled()))
            Init();
          else
            g.fixInfo("Init nicht erlaubt");
        }
        else if (s.equals("Info"))
          showTabSp();
        else if (s.equals("Edit"))
        {
          bEdit=true;
          EnableButtons();
        }
        //else if (s.equals("Zuordnung") || s.equals("Sortierung"))
        //  EnableButtons();
        else if (s.equals("ASC") || s.equals("DESC"))
        {
          if (A.TabSp.posInhalt("Nummer",Out1.getSelectedNode().getUserData()) && (A.TabSp.getI("bits")&A.cstSortDesc)>0 != s.equals("DESC"))
          {
            A.TabSp.setInhalt("bits", A.TabSp.getI("bits") ^ A.cstSortDesc);
            ((Vector)Out1.getSelectedNode().getLabel()).setElementAt(g.getBegriffS("Radiobutton",(A.TabSp.getI("bits") & A.cstSortDesc)>0 ? "absteigend":"aufsteigend"),1);
            Out1.folderChanged(Root1);
          }
        }
        else if (s.startsWith("Abf_") || s.equals("Back"))
          Refresh(s);
        else if (s.equals("hinzu") || s.equals("weg"))
          hinzu(s.equals("hinzu"));
        else if (s.equals("Ersetzen"))
          Ersetzen();
        else if (s.equals("rauf") || s.equals("runter"))
        {
          Static.move_up_down(Out1.getSelectedNode(), s.equals("rauf"));
          //if (iArt==3)
          //  merkeHA();
        }
        else if (s.equals("Ok2"))
          OkTitel();
//        else if (s.equals("Ok3"))
//          OkTitel2();
      }
    };
    JButton BtnInfo=getButton("Info");
    if (BtnInfo != null) BtnInfo.setVisible(g.Def());
    g.BtnAdd(BtnInfo,"Info",AL);
    //g.BtnAdd(getButton("Init"),"Init",AL);
    BtnInit=g.BtnAdd(getButton("Init2"),"Init",AL);
    //g.setAction(RadZ,"Zuordnung",AL);
    //g.setAction(RadS,"Sortierung",AL);
    g.setAction(RadA,"ASC",AL);
    g.setAction(RadD,"DESC",AL);
    g.setAction(RadPers,"Abf_Pers",AL);
    g.setAction(RadBG,"Abf_BG",AL);
    CboBenutzergruppe.addItemListener(new ItemListener ()
    {
        public void itemStateChanged(ItemEvent ev)
        {
            if(ev.getStateChange() == ItemEvent.SELECTED)
                    Refresh("Cbo");
        }
    });
    //g.setAction(RadStamm,"Abf_Stamm",AL);
    g.setAction(RadJeder,"Abf_Jeder",AL);
    g.setAction(RadStd,"Abf_Std",AL);
    BtnWeg=g.BtnAdd(getButton(">"),"weg",AL);
    BtnHinzu=g.BtnAdd(getButton("<"),"hinzu",AL);
    BtnErsetzen=g.BtnAdd(getButton("Ersetzen"),"Ersetzen",AL);
    BtnRauf=g.BtnAdd(getButton("Pfeil oben"),"rauf",AL);
    BtnRunter=g.BtnAdd(getButton("Pfeil unten"),"runter",AL);
    BtnOk2=g.BtnAdd(getButton("Uebernehmen"),"Ok2",AL);
//    BtnOk3=g.BtnAdd(getButton("ZRspeichern"),"Ok3",AL);
    BtnEdit=g.BtnAdd(getButton("Edit"),"Edit",AL);
    bEdit=BtnEdit==null;
    BtnOk=getButton("Ok");
    //if (BtnOk!=null)
    //{
      g.BtnAdd(BtnOk, "Ok", AL);

    //}
    BtnAbbruch=g.BtnAdd(getButton("Abbruch"),"Beenden",AL);
    BtnSave=g.BtnAdd(getButton("Speichern"),"Speichern",AL);
    BtnBack=g.BtnAdd(getButton("Zurueck"),"Back",AL);
    BtnBeenden=g.BtnAdd(getButton("Beenden"),"Beenden",AL);
    EnableButtons();
    JCOutlinerListener OutList=new JCOutlinerListener ()
    {
            public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
            {
              if (g.Prog() && ev.getSource()==Out1)
              {
                JCOutlinerNode Nod = Out1.getSelectedNode();
                if (Nod != null)
                  g.progInfo(Nod.getLevel() + "-" + Nod.getLabel() + ":" + Nod.getUserData());
              }
                    if (bReady)
                      EnableButtons();
            }
            public void outlinerNodeSelectBegin(JCOutlinerEvent ev)
            {
            }
            public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev) {}
            public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev) {}
            public void itemStateChanged(JCItemEvent ev) {}
    };
    Out1.addItemListener(OutList);
    Out2.addItemListener(OutList);
    MouseMotionListener MML=new MouseMotionListener()
    {
      public void mouseDragged(MouseEvent ev)
      {
        //g.progInfo("1.mouseDragged:"+ev.getPoint()+"/"+Out1.getSelectedNode());
        if (iStatus==0)
          iStatus=ev.getSource()==Out1.getOutliner() ? 1:-1;
      }
      public void mouseMoved(MouseEvent ev) {}
    };
    Out1.getOutliner().addMouseMotionListener(MML);
    Out2.getOutliner().addMouseMotionListener(MML);

    MouseListener ML=new MouseListener()
    {
      public void mousePressed(MouseEvent ev) {}
      public void mouseClicked(MouseEvent ev) {}
      public void mouseEntered(MouseEvent ev)
      {
        //g.progInfo("mouseEntered");
        boolean bOut2=ev.getSource()==Out2.getOutliner();
        if (iStatus==(bOut2 ? 1:-1))
          iStatus=(bOut2 ? 2:-2);
      }
      public void mouseExited(MouseEvent ev) {}
      public void mouseReleased(MouseEvent ev)
      {
        //g.progInfo("1.mouseReleased:"+iStatus);
        boolean bOut2=ev.getSource()==Out2.getOutliner();
        if (bOut2 && iStatus==-2 && BtnHinzu.isEnabled() || !bOut2 && iStatus==2 && BtnWeg.isEnabled())
          hinzu(bOut2);
        iStatus=0;
      }
    };
    /*Out2.getOutliner().addMouseListener(new MouseListener()
    {
      public void mousePressed(MouseEvent ev) {}
      public void mouseClicked(MouseEvent ev) {}
      public void mouseEntered(MouseEvent ev)
      {
        if (iStatus==1)
          iStatus=2;
        //g.progInfo("2.mouseEntered -> "+iStatus);
      }
      public void mouseExited(MouseEvent ev) {}
      public void mouseReleased(MouseEvent ev)
      {
        //g.progInfo("2.mouseReleased"+iStatus);
        if (iStatus==-2 && BtnHinzu.isEnabled())
          hinzu(true);
        iStatus=0;

      }
    });*/
    Out1.getOutliner().addMouseListener(ML);
    Out2.getOutliner().addMouseListener(ML);
    bReady=true;
  }

  private void readBG()
  {
    g.progInfo("readBG");
    //CboBenutzergruppe.fillDefTable("BENUTZERGRUPPE", true);
    int iAic=CboBenutzergruppe.getSelectedAIC();
    Tabellenspeicher Tab=new Tabellenspeicher(g,"SELECT aic_BENUTZERGRUPPE,kennung," + g.AU_BezeichnungF("BENUTZERGRUPPE") + " Bezeichnung FROM BENUTZERGRUPPE"+
                          (g.getMandant()==1 ? "":g.getReadMandanten(true,"BENUTZERGRUPPE")+" or "+g.bit("bits",Global.cstJeder)),true);
    CboBenutzergruppe.Clear();
    Vector VecSpalten=SQL.getVector("select aic_spalte from spalte where aic_abfrage="+A.iAbfrage,g);
    for(Tab.moveFirst();!Tab.out();Tab.moveNext())
      CboBenutzergruppe.addItem2(Tab.getS("Bezeichnung"),Tab.getI("aic_BENUTZERGRUPPE"),Tab.getS("Kennung"),0,getCol(VecSpalten,Tab.getI("aic_BENUTZERGRUPPE")));
                                 //Abfrage.TabSpalten2b.getPos("AIC_Benutzergruppe",Tab.getI("aic_BENUTZERGRUPPE"))<0 ? java.awt.Color.RED:null);
    CboBenutzergruppe.setKein(false);
    CboBenutzergruppe.Sort();
    CboBenutzergruppe.setSelectedAIC(iAic);
  }

  private Color getCol(Vector Vec,int iBG)
  {
    Abfrage.readSpalten2(g,CboMandant.getSelectedAIC());
    Tabellenspeicher Tab=Abfrage.getBA(g,"2b");
    for (int i=0;i<Tab.size();i++)
      if (Tab.getI(i,"AIC_Benutzergruppe")==iBG && Vec.contains(Tab.getI(i,"AIC_Spalte")))
        return java.awt.Color.GREEN.darker();
    return null;
  }

  private void checkRad()
  {
    //g.fixtestInfo("  checkRad:"+A.iS2_Art);
    if (RadPers!=null)
      RadPers.setSelected(A.iS2_Art==1);
    if (RadBG!=null)
      RadBG.setSelected(A.iS2_Art==2);
    if (RadJeder!=null)
      RadJeder.setSelected(A.iS2_Art==3);
    if (RadStd!=null)
      RadStd.setSelected(A.iS2_Art==0);
    if (A.iS2_Art==2)
      CboBenutzergruppe.setSelectedAIC(A.iBG_Soll);
    else
      LblInfo.setText(A.iS2_Art==0 ? "-":A.iS2_Art==3 ? "*Jeder*":A.iS2_Art==1?Static.sUser:A.iS2_Art==5?g.getStamm(iANR):"-");
    if (PnlCombo!=null)
    {
      PnlCombo.removeAll();
      PnlCombo.add(A.iS2_Art==2 ? CboBenutzergruppe:LblInfo);
    }
    colorExists();
  }

  private void OkTitel()
  {
    //g.progInfo("actionPerformed:"+EdtTitel.getText());
    JCOutlinerNode Nod=Out1.getSelectedNode();
    int iL=Nod==null ? 0:Nod.getLevel();
    if (iArt==3 && iL==2)
    {
      Object Obj=Nod.getLabel();
      //if (Obj instanceof String)
        Obj=EdtTitel.getText();
      //else if (Obj instanceof Vector)
      //  ((Vector)Obj).setElementAt(EdtTitel.getText(),0);
      Nod.setLabel(Obj);
      Out1.folderChanged(Nod.getParent());
      //merkeHA();
    }
  }
  
  private void setBit(Tabellenspeicher Tab,String sSpalte,int iBit,boolean b)
  {
	  int iBits=Tab.getI(sSpalte);
	  //g.fixtestError("setBits vorher :"+iBits);
	  if ((iBits&iBit)>0) iBits-=iBit;
	  if (b) iBits+=iBit;
	  Tab.setInhalt(sSpalte, iBits);
	  //g.fixtestError("setBits nachher:"+iBits);
  }

  @SuppressWarnings("unchecked")
  private void OkTitel2()
  {
	if (!bEdit)
		return;
    JCOutlinerNode Nod=Out1.getSelectedNode();
//    g.fixtestError("OkTitel2 bei "+Nod.getLabel()+"/"+Nod.getUserData());
    if(/*(bFormat ? EdtFormat.Modified():CboFarbe.Modified()) &&*/ Nod != null && Nod.getLevel() > 0 && A.TabSp.posInhalt("Nummer", Nod.getUserData())) {
      //g.fixInfo(Nod + "/" + EdtFormat.getText());
      if (A.TabSp.getI("rel") > 0)
      {
        //g.fixInfo("Bez vorher:"+A.TabSp.getS("Bezeichnung"));
        while (!Sort.gets(Nod.getLabel(), 0).equals(A.TabSp.getS("Bezeichnung")))
          A.TabSp.moveNext();
        //g.fixInfo("Bez nachher:"+A.TabSp.getS("Bezeichnung"));
      }
      ((Vector)Nod.getLabel()).setElementAt(EdtTitel2.getText(), 0);
      A.TabSp.setInhalt("Bezeichnung",EdtTitel2.getText());
      if (EdtFormat.Modified())
      {
    	  ((Vector)Nod.getLabel()).setElementAt(EdtFormat.getText(), 2);
    	  A.TabSp.setInhalt("aic_begriff", EdtFormat.getAIC());
    	  EdtFormat.Cbo.Reset2();
      }
      boolean bMobil=CbxMobil.isSelected();
      ((Vector)Nod.getLabel()).setElementAt(Static.JaNein2(bMobil),4);
      setBit(A.TabSp,"bits",Abfrage.cstMobil,bMobil);
      boolean bZwang=CbxZwingend.isSelected();
      ((Vector)Nod.getLabel()).setElementAt(Static.JaNein2(bZwang),6);
      setBit(A.TabSp,"bits",Global.cstAlways*0x10000,bZwang);
      boolean bNimm=CbxNimm.isSelected();
      ((Vector)Nod.getLabel()).setElementAt(Static.JaNein2(bNimm),1);
      boolean bVZD=CbxVZD.isSelected();
      ((Vector)Nod.getLabel()).setElementAt(Static.JaNein2(bVZD),8);
      A.TabSp.setInhalt("bBits",(bMobil ? MOBIL:0)+(bZwang ? ZWANG:0)+(bNimm ? NIMM:0)+(bVZD ? VZD:0));
//      setBit(A.TabSp,"bits",Abfrage.cstXX,bNimm);
      ((Vector)Nod.getLabel()).setElementAt(CboFarbe.getSelectedBezeichnung(), 5);
      A.TabSp.setInhalt("aic_farbe", CboFarbe.getSelectedAIC());
      Out1.folderChanged(Root1);
    }
  }

  private void saveIntern()
  {
      Vector VecNod=Root1.getChildren();
      if (VecNod != null)
        for (int i=0;i<VecNod.size();i++)
          if (A.TabSp.posInhalt("Nummer",(Integer)((JCOutlinerNode)VecNod.elementAt(i)).getUserData()))
          {
            if (iArt==1)
            {
              if (A.TabSp.getI("Rel")>0)
                A.TabSp.posInhalt("Bezeichnung",Sort.gets(((JCOutlinerNode)VecNod.elementAt(i)).getLabel(),0));
              A.TabSp.setInhalt("Reihenfolge", i + 1);
              if ((A.TabSp.getI("bits") & Abfrage.cstUnsichtbar) > 0)
                A.TabSp.setInhalt("bits", A.TabSp.getI("bits") - Abfrage.cstUnsichtbar);
            }
            else if (iArt==2)
              A.TabSp.setInhalt("sortiert",i+1);
            else if (iArt==3)
            {
              Vector VecNodC=((JCOutlinerFolderNode)VecNod.elementAt(i)).getChildren();
              Vector<JCOutlinerNode> Vec=VecNodC==null || VecNodC.size()==0?null:new Vector<JCOutlinerNode>();
              if (Vec != null)
                for(int i2=0;i2<VecNodC.size();i2++)
                  Vec.addElement((JCOutlinerFolderNode)VecNodC.elementAt(i2));
              g.progInfo("Vec-Gruppe="+Vec);
              A.TabSp.setInhalt("Gruppe",Vec);
              A.setBA_Null(g,"Z2a");
              A.setBA_Null(g,"Z2b");
              A.setBA_Null(g,"Z2c");
            }
          }
      if (iArt>2)
        return;
      int iP=VecNod==null ? 1:VecNod.size()+1;
      VecNod=Root2.getChildren();
      if (VecNod != null)
        for (int i=0;i<VecNod.size();i++)
          if (A.TabSp.posInhalt("Nummer",(Integer)((JCOutlinerNode)VecNod.elementAt(i)).getUserData()))
          {
            if (iArt==1)
            {
              if (A.TabSp.getI("Rel")>0)
                A.TabSp.posInhalt("Bezeichnung",Sort.gets(((JCOutlinerNode)VecNod.elementAt(i)).getLabel(),0));
              A.TabSp.setInhalt("Reihenfolge", i + iP);
              A.TabSp.setInhalt("bits", A.TabSp.getI("bits") | Abfrage.cstUnsichtbar);
            }
            else if (iArt==2)
              A.TabSp.setInhalt("sortiert",0);
          }
      checkReihe();
      //A.TabSp.showGrid("TabSp");
  }

  private void checkReihe()
  {
    g.progInfo("checkReihe");
    A.TabSp.sort("Reihenfolge",true);
    int iRf=0;
    for(int i=0;i<A.TabSp.size();i++)
      if ((A.TabSp.getI(i,"bits") & Abfrage.cstUnsichtbar) == 0)
      {
        iRf++;
        if (iRf!=A.TabSp.getI(i,"Reihenfolge"))
        {
          g.progInfo("Reihenfolge bei "+A.TabSp.getS(i,"Bezeichnung")+":"+A.TabSp.getI(i,"Reihenfolge")+"->"+iRf);
          A.TabSp.setInhalt(i,"Reihenfolge",iRf);
        }
      }
  }

  private void showTabSp()
  {
    //A.TabSp.showGrid();
    Tabellenspeicher Tab=new Tabellenspeicher(g,new String[]{"Reihe","Aic","Bezeichnung","Stamm","Color","bits"});
    for (int i=0;i<A.TabSp.size();i++)
    {
      Tab.addInhalt("Reihe",A.TabSp.getI(i,"Reihenfolge"));
      Tab.addInhalt("Aic",A.TabSp.getI(i,"aic_Spalte"));
      Tab.addInhalt("Bezeichnung",A.TabSp.getS(i,"Bezeichnung"));
      Tab.addInhalt("Stamm",g.getStamm(A.TabSp.getI(i,"Rel")));
      Tab.addInhalt("Color",CboFarbe.getBezeichnung(A.TabSp.getI(i,"aic_farbe")));
      Tab.addInhalt("bits",A.TabSp.getI(i,"bits2"));
    }
    Tab.showGrid("Spalten",thisFrame);
    if (Abfrage.getBA(g,"Z2a")!=null)
      Abfrage.getBA(g,"Z2a").showGrid("TabSpalteZ2",thisFrame);
    A.TabSp.showGrid("TabSp",thisFrame);
  }

  private void Refresh(String s)
  {
    if (bRefresh)
      return;
    bRefresh=true;
    g.testInfo("DefAbfrage2.Refresh "+s);
    //g.fixtestError("Refresh "+s+":"+A.iBegriff+"/"+getS2()+"/"+CboBenutzergruppe.getSelectedAIC());
    A=new ShowAbfrage(g,A.iBegriff,getS2(),s.endsWith("BG") || s.equals("Cbo") ? CboBenutzergruppe.getSelectedAIC():0);
    iArt=0;
    bEdit=false;
    if (!s.equals("Cbo"))
    	checkRad();
    EnableButtons();
    bRefresh=false;
  }

  private int getS2()
  {
    if (RadStd==null)// || RadStd.isSelected())
      return 0;
    return RadPers.isSelected() ? 1:RadBG.isSelected() ? 2:RadJeder.isSelected() ? 3:/*RadStamm!=null && RadStamm.isSelected() ? 5:*/4;
  }

  private void Init()
  {
    int iBG=RadBG==null || RadBG.isSelected() ? CboBenutzergruppe.getSelectedAIC():0;
    boolean bJeder=RadJeder.isSelected();
    boolean bPers=RadPers.isSelected();
    if (new Message(Message.YES_NO_OPTION,null,g).showDialog("Benutzerabfrage_loeschen",new String[] {bJeder ? g.getShow("Jeder"):bPers ? g.getShow("Pers"):
        iBG>0 ? g.getShow("Benutzergruppe")+" "+CboBenutzergruppe.getSelectedBezeichnung():"??"})==Message.NO_OPTION)
      return;
    Vector VecSpalten=SQL.getVector("select aic_spalte from spalte where aic_abfrage="+A.iAbfrage,g);
    int iAnz=0;
    if (iBG>0)
    {
      iAnz = g.exec("delete from spalten2 where AIC_Benutzergruppe=" + iBG + " and" + g.in("aic_spalte", VecSpalten));
      iAnz+= g.exec("delete from SPALTE_Z2 where AIC_Benutzergruppe=" + iBG + " and" + g.in("aic_spalte", VecSpalten));
      Abfrage.setBA_Null(g,"2b");
      Abfrage.setBA_Null(g,"Z2b");
    }
    /*else if (RadStamm==null || RadStamm.isSelected())
    {
      iAnz = g.exec("delete from spalten2 where ANR=" + iANR + " and" + g.in("aic_spalte", VecSpalten));
      //iAnz+= g.exec("delete from SPALTE_Z2 where ANR=" + iANR + " and" + g.in("aic_spalte", VecSpalten));
      A.TabSpalten2d=null;
      //A.TabSpalteZ2d=null;
    }*/
    else if (bPers)//RadPers==null || RadPers.isSelected())
    {
      iAnz = g.exec("delete from spalten2 where AIC_Benutzer=" + g.getBenutzer() + " and" + g.in("aic_spalte", VecSpalten));
      iAnz+= g.exec("delete from SPALTE_Z2 where AIC_Benutzer=" + g.getBenutzer() + " and" + g.in("aic_spalte", VecSpalten));
      Abfrage.setBA_Null(g,"2a");
      Abfrage.setBA_Null(g,"Z2a");
    }
    else if (bJeder)//RadJeder==null || RadJeder.isSelected())
    {
      String sM=" aic_mandant"+(CboMandant.isNull() ? " is null":"="+CboMandant.getSelectedAIC())+" and";
      iAnz = g.exec("delete from spalten2 where AIC_Benutzer is null and AIC_Benutzergruppe is null and"+sM + g.in("aic_spalte", VecSpalten));
      iAnz+= g.exec("delete from SPALTE_Z2 where AIC_Benutzer is null and AIC_Benutzergruppe is null and"+sM + g.in("aic_spalte", VecSpalten));
      Abfrage.setBA_Null(g,"2c");
      Abfrage.setBA_Null(g,"Z2c");
    }
    g.testInfo(iAnz+" Spalten gelöscht");
    A=new ShowAbfrage(g,A.iBegriff,getS2(),CboBenutzergruppe.getSelectedAIC());
    if (iBG>0)
        readBG();
    iArt=0;
    if (BtnEdit != null && !bEdit)
      A.iS2_Art=0;
    checkRad();
    EnableButtons();
    //thisFrame.repaint();
  }

  private int getNummer(int iSpalte)
  {
    for (int i=0;i<A.TabSp.size();i++)
      if (A.TabSp.getI(i,"Nummer")>0 && A.TabSp.getI(i,"aic_spalte")==iSpalte)
        return A.TabSp.getI(i,"Nummer");
    return -100;
  }

  private void saveDb()
  {
    SQL Qry=new SQL(g);
    int iBG=RadBG==null || RadBG.isSelected() ? CboBenutzergruppe.getSelectedAIC():0;
    g.fixtestError("saveDb BG="+iBG);
    //Vector<Integer> VecHA=new Vector<Integer>();
    //boolean bSE2=false;
    Abfrage.readSpalten2(g,CboMandant.getSelectedAIC());
    boolean bJeder=RadJeder!=null && RadJeder.isSelected();
    //boolean bStamm=RadStamm!=null && RadStamm.isSelected();
    Tabellenspeicher Tab=/*bStamm ? Abfrage.TabSpalten2d:*/bJeder ? Abfrage.getBA(g,"2c"):iBG>0 ? Abfrage.getBA(g,"2b"):Abfrage.getBA(g,"2a");
    for(A.TabSp.moveFirst(); !A.TabSp.out(); A.TabSp.moveNext())
        if((A.TabSp.getI("bits") & Abfrage.cstAnzeigen) > 0)// && (A.TabSp.getI("bits") & Abfrage.cstUnsichtbar)==0)
        {
          int iSpalte=A.TabSp.getI("aic_spalte");
          int iPos=Tab.getPos("aic_spalte",iSpalte);
          while (iPos>=0 && iBG>0 && Tab.getI(iPos,"AIC_Benutzergruppe") != iBG)
            iPos=Tab.getNextPos(iPos,"aic_spalte",A.TabSp.getInt("aic_spalte"));
          boolean bSE=A.TabSp.getI("Nummer")<0;
          if (bSE)
          {
            //bSE2 = true;
            int iNrNeu=getNummer(iSpalte);
            g.progInfo("Nummer:"+A.TabSp.getI("Nummer")+"->2"+iNrNeu);
            A.TabSp.setInhalt("Nummer",iNrNeu);
          }
          boolean bNeu=iPos<0 || bSE;
          if (!bNeu && A.TabSp.getI("Rel")>0 && Tab.getI(iPos,"Aic_Stamm") != A.TabSp.getI("Rel"))
          {
            //g.fixInfo("Stamm unterschiedlich:"+A.TabSp.getI("Rel")+"/"+Tab.getI(iPos,"Aic_Stamm"));
            while(iPos<Tab.size() && Tab.getI(iPos,"Aic_Stamm") != A.TabSp.getI("Rel"))
              iPos++;
            if (iPos==Tab.size())
            {
              iPos=-1;
              bNeu=true;
            }
            //else
              //g.fixInfo("Stamm gleich:"+A.TabSp.getI("Rel")+"/"+Tab.getI(iPos,"Aic_Stamm"));
          }
          if (bNeu)
            iPos=Tab.newLine();
          if (bNeu && A.TabSp.getI("Rel")>0)
          {
            g.exec("delete from spalten2 where aic_abfrage="+A.iAbfrage+" and aic_stamm="+A.TabSp.getI("Rel")+" and AIC_Benutzergruppe"+(iBG>0 ? "="+iBG:" is null")+" and (aic_mandant is null or aic_mandant="+CboMandant.getSelectedAIC()+")"+
                (/*bStamm?" and anr="+iANR:*/" and aic_benutzer"+(iBG==0 && !bJeder ? "="+g.getBenutzer():" is null")));
          }
          if (iBG>0)
            Qry.add("AIC_Benutzergruppe",iBG);
          //else if (bStamm)
          //  Qry.add("ANR",iANR);
          else if (!bJeder)
            Qry.add("AIC_Benutzer",g.getBenutzer());
          Qry.add("Reihe",A.TabSp.getI("Reihenfolge"));
          Qry.add("sortiert",A.TabSp.getI("sortiert"));
          Qry.add("Titel",Static.Gleich(A.TabSp.getS("Bez2"),A.TabSp.getS("Bezeichnung")) ? null:A.TabSp.getS("Bezeichnung"));
          Qry.add0("aic_begriff",A.TabSp.getI("aic_begriff"));
          Qry.add0("aic_farbe",A.TabSp.getI("aic_farbe"));
          boolean bSichtbar=(A.TabSp.getI("bits") & Abfrage.cstUnsichtbar) == 0;
          if (bSichtbar && (A.TabSp.getI("bits2") & Abfrage.cstWeg)>0)
            A.TabSp.setInhalt("bits2",A.TabSp.getI("bits2") - Abfrage.cstWeg);
          int iBits=(bSichtbar ? SHOW:0)+((A.TabSp.getI("bits")&A.cstSortDesc)>0 ? SoDe:0)+((A.TabSp.getI("bits")&A.cstMobil)>0 ? MOBIL:0)+((A.TabSp.getI("bits")&Global.cstAlways*0x10000)>0 ? ZWANG:0)+
        		  ((A.TabSp.getI("bBits")&NIMM)>0 ? NIMM:0)+((A.TabSp.getI("bBits")&VZD)>0 ? VZD:0);
          Qry.add("bits",iBits);
          if (bNeu)
          {
            Qry.add0("aic_stamm",A.TabSp.getI("Rel"));
            Qry.add("aic_spalte",iSpalte);
            Qry.add("aic_abfrage",A.iAbfrage);
            Qry.add0("aic_mandant",CboMandant.getSelectedAIC());
            int iAic=Qry.insert("spalten2",true);
            Tab.setInhalt(iPos,"aic_spalten2",iAic);
            Tab.setInhalt(iPos,"aic_spalte",iSpalte);
            Tab.setInhalt(iPos,"aic_abfrage",A.iAbfrage);
            if (iBG>0)
              Tab.setInhalt(iPos,"AIC_Benutzergruppe",iBG);
          }
          else
            Qry.update("spalten2",Tab.getI(iPos,"aic_spalten2"));
          Tab.setInhalt(iPos,"Reihe",A.TabSp.getI("Reihenfolge"));
          Tab.setInhalt(iPos,"sortiert",A.TabSp.getI("sortiert"));
          Tab.setInhalt(iPos,"bits",iBits);
          if (A.TabSp.getInhalt("Gruppe") != null)
           //if (bStamm)
           //  Static.printError("Stammabhänge horizontale Auflösung nicht möglich");
           //else
           {
            g.exec("delete from SPALTE_Z2 where AIC_Benutzer"+(bJeder ? " is null and aic_benutzergruppe is null":iBG>0?"gruppe="+iBG:"=" + g.getBenutzer()) + " and aic_spalte="+iSpalte+" and (aic_mandant is null or aic_mandant="+CboMandant.getSelectedAIC()+")");
            Vector Vec=(Vector)A.TabSp.getInhalt("Gruppe");
            for(int i=0;i<Vec.size();i++)
            {
                    JCOutlinerFolderNode Nod=((JCOutlinerFolderNode)Vec.elementAt(i));
                    String s=""+Nod.getLabel();
                    Vector VecChildren = Nod.getChildren();
                    for(int i2=0; VecChildren!=null && VecChildren.size()>i2;i2++)
                    {
                    	Vector Vec2=(Vector)((JCOutlinerNode)VecChildren.elementAt(i2)).getUserData();
                    	int iStamm=Sort.geti(Vec2,0);
                    	//if (iStamm>0)
                    	{
                            Qry.add("aic_spalte",iSpalte);
                            Qry.add("Reihe",i*1000+i2+1001);
                            Qry.add("Titel",s);                           
                            Qry.add0("aic_stamm",iStamm==0 ? g.iEuro:iStamm);
                            if (iBG>0)
                              Qry.add("AIC_Benutzergruppe", iBG);
                            else if (!bJeder)
                              Qry.add("AIC_Benutzer", g.getBenutzer());
                            Qry.add0("aic_mandant",CboMandant.getSelectedAIC());
                            Qry.insert("SPALTE_Z2",false);
                    	}
                    }
            }
            //new Tabellenspeicher(g,"select * from SPALTE_Z2 where aic_spalte="+iSpalte,true).showGrid("Spalte "+iSpalte);
//            if (bJeder)
//              Abfrage.setBA_Null(g,"Z2c");
//            else if (iBG>0)
//              Abfrage.setBA_Null(g,"Z2b");
//            else
//              Abfrage.setBA_Null(g,"Z2a");
           }
        }
      //if (bSE2)
      //{
        //if (bStamm)
        //  Abfrage.TabSpalten2d = null;
        //else 
//        if (bJeder)
//          Abfrage.setBA_Null(g,"2c");
//        else if (iBG>0)
//          Abfrage.setBA_Null(g,"2b");
//        else
//          Abfrage.setBA_Null(g,"2a");
      Abfrage.free(g);
//      Abfrage.readSpalten2(g,-2);
      //}
      Abfrage.readSpalten2(g,CboMandant.getSelectedAIC());
      if (iBG>0)
        readBG();
      //if (g.Prog() && bJeder)
      //	  Abfrage.TabSpalten2c.showGrid("TabSpalten2-Jeder");
      //A.TabSp.showGrid("TabSp");

        /*else //if (!bNeu)
        {
          if (iBG>0)
            Qry.add("AIC_Benutzergruppe",iBG);
          else
            Qry.add("AIC_Benutzer",g.getBenutzer());
          Qry.add("aic_spalte",A.TabSp.getI("aic_spalte"));
          Qry.delete("spalten2");
          Qry.clear();
        }*/

    Qry.free();
    colorExists();
    //A.TabSp.showGrid("TabSp-vorher");
  }

  private void hinzu(boolean b)
  {
    JCOutlinerNode Nod=(b ? Out2:Out1).getSelectedNode();
    int iL=Nod==null ? 0:Nod.getLevel();
    //g.progInfo((b?"hinzu:":"weg:")+Nod+"/ Level="+iL+"/ Art="+iArt);
    if (iL<1)
      return;
    if (iArt==4)
    {
      if (b)
      {
        JCOutlinerNode Nod2 = Out1.getSelectedNode();
        iL = Nod2 == null ? 0 : Nod2.getLevel();
        if (iL < 1)
          return;
        if (iL == 2)
        {
          //g.fixtestInfo(Nod.getLabel()+"/"+Nod.getUserData()+" hinzu auf "+Nod2.getLabel()+"/"+Nod2.getUserData());
          if (Sort.geti(Nod2.getUserData(),0)==0)
          {
            Ersetzen();
            return;
          }
          Nod2 = Nod2.getParent();
        }
        JCOutlinerNode NodNew=new JCOutlinerNode(Nod.getLabel());
        Vector<Integer> Vec=new Vector<Integer>();
          Vec.addElement(Sort.geti(Nod.getUserData()));      // Stamm
          Vec.addElement(iSttFill);// Stammtyp
          int iNr=(int)A.TabSp.min("Nummer");
          iNr=iNr>0 ? -1:iNr-1;
          Vec.addElement(iNr);        // Nr
          //Vec.addElement(Sort.geti(Nod2.getUserData()));
          g.fixtestInfo("Neue SE:"+Vec);
        NodNew.setUserData(Vec);
        ((JCOutlinerFolderNode)Nod2).addNode(NodNew);
        if (A.TabSp.posInhalt("Nummer",Sort.geti(Nod2.getUserData())))
        {
          A.TabSp.copyLine();
          A.TabSp.setInhalt("Rel",Sort.geti(Vec,0));
          A.TabSp.setInhalt("Bezeichnung",g.getStamm(Sort.geti(Vec,0)));
          A.TabSp.setInhalt("Nummer",Sort.geti(Vec,2));
          //A.TabSp.showGrid("TabSp");
        }
      }
      else
      {
        int iNr=Sort.geti(Nod.getUserData(),2);
        int iPos=A.TabSp.getPos("Nummer",iNr);
        if (iPos>=0)
          A.TabSp.setInhalt(iPos,"bits", A.TabSp.getI(iPos,"bits") | Abfrage.cstUnsichtbar);
          //A.TabSp.clearInhalt(iPos);
        Out1.selectNode(Nod.getParent());
        Nod.getParent().removeChild(Nod);
      }
      Out1.folderChanged(Root1);
      //merkeSE();
    }
    else if (iArt==3)
    {
      if (b)
      {
        JCOutlinerNode Nod2=Out1.getSelectedNode();
        iL=Nod2==null ? 0:Nod2.getLevel();
        //g.progInfo("auf "+Nod2+"/"+iL);
        String sTitel=Sort.gets(Nod.getLabel());
        JCOutlinerNode NodNew=new JCOutlinerNode(sTitel);
        if (g.Def())
        {
          Vector<String> Vec=new Vector<String>();
          Vec.addElement(sTitel);
          Vec.addElement(g.TabMandanten.getBezeichnungS(SQL.getInteger(g,"select aic_mandant from stammview2 where aic_stamm=?",-1,""+Nod.getUserData())));
          NodNew=new JCOutlinerNode(Vec);
        }
        Vector<Integer> Vec=new Vector<Integer>();
          Vec.addElement(Sort.geti(Nod.getUserData()));      // Stamm
          Vec.addElement(iSttFill);// Stammtyp
          Vec.addElement(null);        // Eigenschaft
        NodNew.setUserData(Vec);
        if (iL==3)
          Nod2.getParent().addNode(NodNew);
        else if (iL==2)
          ((JCOutlinerFolderNode)Nod2).addNode(NodNew);
        else if (iL==1)
        {
          JCOutlinerFolderNode NodNewP = new JCOutlinerFolderNode(sTitel,(JCOutlinerFolderNode)Nod2);
          NodNewP.addNode(NodNew);
        }
      }
      else
      {
        //g.progInfo("remove "+Nod);
        Out1.selectNode(Nod.getParent());
        Nod.getParent().removeChild(Nod);
      }
      Out1.folderChanged(Root1);
      //merkeHA();
    }
    else if (b || iArt==2 || Root1.getChildren().size()>1)
    {
      (b ? Root1 : Root2).addNode(Nod);
      (b ? Root2 : Root1).removeChild(Nod);
      Out1.folderChanged(Root1);
      Out2.folderChanged(Root2);
      //if (!b)
      //  g.fixInfo("entferne "+Nod.getLabel()+"/"+Nod.getUserData());
    }
  }

  /*private void merkeHA()
  {
    JCOutlinerNode Nod=Out1.getSelectedNode();
      while (Nod.getLevel()>1)
        Nod=Nod.getParent();
      Vector VecNod=Nod.getChildren();
      Vector<JCOutlinerNode> Vec=VecNod==null || VecNod.size()==0?null:new Vector<JCOutlinerNode>();
      if (Vec != null)
        for(int i=0;i<VecNod.size();i++)
          Vec.addElement((JCOutlinerNode)VecNod.elementAt(i));
      g.progInfo("Vec-Gruppe="+Vec);
      if (A.TabSp.posInhalt("Nummer",(Integer)Nod.getUserData()))
        A.TabSp.setInhalt("Gruppe",Vec);
      A.TabSpalteZ2=null;
      A.TabSpalteZ2b=null;
  }*/

  /*private void merkeSE()
  {
    JCOutlinerNode Nod=Out1.getSelectedNode();
    if (Nod.getLevel()>1)
      Nod=Nod.getParent();
    Vector VecNod=Nod.getChildren();
    Vector<JCOutlinerNode> Vec=VecNod==null || VecNod.size()==0?null:new Vector<JCOutlinerNode>();
  }*/

  private void Ersetzen()
  {
    JCOutlinerNode Node1 = Out1.getSelectedNode();
    JCOutlinerNode Node2 = Out2.getSelectedNode();
    Object ObjL=Node1.getLabel();
    Object ObjU=Node1.getUserData();
    Node1.setLabel(Node2.getLabel());
    if (iArt==3 && g.Def())
    {
      Vector<String> Vec=new Vector<String>();
          Vec.addElement(Sort.gets(Node2.getLabel()));
          Vec.addElement(g.TabMandanten.getBezeichnungS(SQL.getInteger(g,"select aic_mandant from stammview2 where aic_stamm=?",-1,""+Node2.getUserData())));
      Node1.setLabel(Vec);
    }
    if (iArt<3)
    {
      Node1.setUserData(Node2.getUserData());
      Node2.setLabel(ObjL);
      Node2.setUserData(ObjU);
      Out2.folderChanged(Root2);
    }
    else if (iArt==3)
    {
      Vector<Integer> Vec=new Vector<Integer>();
          Vec.addElement(Sort.geti(Node2.getUserData()));      // Stamm
          Vec.addElement(iSttFill);// Stammtyp
          Vec.addElement(null);        // Eigenschaft
        Node1.setUserData(Vec);
    }
    else if (iArt==4)
    {
      //g.fixInfo("fehlt noch");
      JCOutlinerNode Nod2 = Out1.getSelectedNode().getParent();
      if (A.TabSp.posInhalt("Nummer",Sort.geti(Nod2.getUserData())))
      {
        int iAic=Sort.geti(Out2.getSelectedNode().getUserData());
        A.TabSp.setInhalt("Rel",iAic);
        A.TabSp.setInhalt("Bezeichnung",g.getStamm(iAic));
        Vector<Integer> Vec=new Vector<Integer>();
          Vec.addElement(iAic);    // Stamm
          Vec.addElement(iSttFill);// Stammtyp
          int iNr=(int)A.TabSp.min("Nummer");
          iNr=iNr>0 ? -1:iNr-1;
          Vec.addElement(iNr);     // Nr
          //Vec.addElement(Sort.geti(Nod2.getUserData()));
          g.fixtestInfo("Ersetze SE:"+Vec);
        Node1.setUserData(Vec);
      }
    }
    Out1.folderChanged(Root1);
    Out1.setEnabled(true);
  }

  private void colorExists()
  {
    if (ColOk != null)
    {
      //g.fixInfo("colorExists");
      int iSpalte=A.TabSp.getI(0,"aic_Spalte");
      RadPers.setForeground (A.getBA(g,"2a").getPos("aic_spalte",iSpalte)<0 ? ColNot:ColOk);
      RadBG.setForeground (A.getBA(g,"2b").getPos("aic_spalte",iSpalte)<0 ? ColNot:ColOk);
      RadJeder.setForeground (A.getBA(g,"2c").getPos("aic_spalte",iSpalte)<0 ? ColNot:ColOk);
      //if (RadStamm!=null)
      //  RadStamm.setForeground (A.TabSpalten2d.getPos("aic_spalte",iSpalte)<0 ? ColNot:ColOk);
    }
  }

  private void fillOut(int iNeu)
  {
    //A.TabSp.showGrid("TabSp");
    g.progInfo("fillOut "+iNeu);
    bReady=false;
    if (iArt>0)
      saveIntern(); //Save
    else
    {
      bHA=false;
//      bSE=false;
      for (A.TabSp.moveFirst(); !A.TabSp.out(); A.TabSp.moveNext())
        if (/*g.Spezial() &&*/ A.TabSp.getInhalt("Gruppe") != null)
          bHA=true;
        /*else // für Schnellerfassung
        {
          Vector VecEig = (Vector)A.TabSp.getInhalt("Vec");
          Object ObjEig = VecEig.elementAt(VecEig.size() - 1);
          int iPos = g.TabEigenschaften.getPos("Aic", Tabellenspeicher.geti(ObjEig));
          String sDatentyp = g.TabEigenschaften.getS(iPos, "Datentyp");
          if (sDatentyp.endsWith("BewZahl2") || sDatentyp.endsWith("BewMass2") || sDatentyp.endsWith("BewWaehrung2"))
            bSE=true;
        }*/
    }
    Root1.removeChildren();
    Root2.removeChildren();
    Out2.setHeader(null);
    Out2.setBackground(Color.white);
    if (iNeu==1)
    {
      //JCOutlinerNodeStyle StyStd = new JCOutlinerComponent().getDefaultNodeStyle();
      //StyStd.setForeground(Color.BLACK);
      //JCOutlinerNodeStyle StyZw = new JCOutlinerNodeStyle(StyStd);
      //StyZw.setForeground(Color.RED);
      //A.TabSp.showGrid("TabSp");
      String[] s1 = new String[] {g.getShow("Bezeichnung"), g.getBegriffS("Checkbox","take Bez"), g.getShow("Format"), g.getShow("Einheit"), g.getBegriffS("Checkbox","Mobil"), g.getShow("Farbe"), g.getBegriffS("Checkbox","Always"),g.getShow("Orig-Bez"), g.getBegriffS("Checkbox","VZ_drehen")};
      Out1.setColumnButtons(s1);
      Out1.setNumColumns(s1.length);//iTyp==TABELLE ? 5:4);
      checkReihe();
      for(A.TabSp.moveFirst(); !A.TabSp.out(); A.TabSp.moveNext())
        if((A.TabSp.getI("bits") & Abfrage.cstAnzeigen) > 0 && (A.TabSp.getI("bits3") & Abfrage.cstNotUser)==0)// && (A.TabSp.getI("bits") & Abfrage.cstUnsichtbar)==0)
        {
          int i = (A.TabSp.getI("bits") & Abfrage.cstUnsichtbar) > 0 ? 2 : 1;
          JCOutlinerFolderNode NodRoot = i == 1 ? Root1 : Root2;
          Vector VecEig = (Vector)A.TabSp.getInhalt("Vec");
          int iPos=g.TabEigenschaften.getPos("Aic",Tabellenspeicher.geti(VecEig.elementAt(VecEig.size()-1)));
          if (g.existsEigenschaft(g.TabEigenschaften.getI(iPos,"Aic")))
          {
            Vector<String> Vec = new Vector<String>();
            String sBez = Static.beiLeer(A.TabSp.getS("Bezeichnung"), A.getEigenschaftBezeichnung(g, (Vector)A.TabSp.getInhalt("Vec")));
            String sBez2 = Static.beiLeer(A.TabSp.getS("Bez2"), A.getEigenschaftBezeichnung(g, (Vector)A.TabSp.getInhalt("Vec")));
            //if (A.TabSp.getI("Rel")>0)
            //  sBez = g.getStamm(A.TabSp.getI("Rel"));
            Vec.addElement(sBez);
            Vec.addElement(Static.JaNein2((A.TabSp.getI("bBits") & NIMM)>0));
            Vec.addElement(A.TabSp.getI("aic_begriff") == 0 ? g.TabEigenschaften.getS(iPos, "Format") : g.TabBegriffe.getKennung(A.TabSp.getI("aic_begriff")));
            Vec.addElement(A.TabSp.getI("Mass") == 0 ? "":g.getStamm(A.TabSp.getI("Mass")));
            Vec.addElement(Static.JaNein2((A.TabSp.getI("bits") & Abfrage.cstMobil)>0));
            Vec.addElement(CboFarbe.getBezeichnung(A.TabSp.getI("aic_farbe")));
            Vec.addElement(Static.JaNein2((A.TabSp.getI("bits") & (Global.cstAlways * 0x10000))>0));
            Vec.addElement(sBez2);
            Vec.addElement(Static.JaNein2((A.TabSp.getI("bBits") & VZD)>0));
            JCOutlinerNode Nod = new JCOutlinerNode(Vec, NodRoot);
            Nod.setUserData(A.TabSp.getI("Nummer"));
            setStyle(Nod, g.TabEigenschaften.getS(iPos, "Datentyp"), (A.TabSp.getI("bits") & (Global.cstAlways * 0x10000)) > 0);
          }
          /*int iPosB= g.getPosBegriff("Datentyp",g.TabEigenschaften.getS(iPos, "Datentyp"));
          Image Gif = iPosB<0 ? null:g.LoadImage(g.TabBegriffe.getS(iPosB,"BildFile"));
          if (Gif != null)
          {
                  JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
                  StyFolder.setItemIcon(Gif);
                  Nod.setStyle(StyFolder);
                  if ((A.TabSp.getI("bits")&(Global.cstAlways*0x10000))>0)
                    Nod.getStyle().setForeground(Global.ColEF_Bez2);
          }*/

          //Nod.setStyle((A.TabSp.getI("bits")&(Global.cstAlways*0x10000))>0?StyZw:StyStd);
        }
    }
    else if (iNeu==2)
    {
      A.TabSp.sort("sortiert",true);
      String[] s2 = new String[] {g.getBegriffS("Show", "Bezeichnung"), g.getBegriffS("Show", "Sortiert")};
      Out1.setColumnButtons(s2);
      Out1.setNumColumns(2);
      for (A.TabSp.moveFirst(); !A.TabSp.out(); A.TabSp.moveNext())
        if ((A.TabSp.getI("bits") & Abfrage.cstAnzeigen) > 0 && ((A.TabSp.getI("bits") & Abfrage.cstUnsichtbar)==0) && A.TabSp.isNull("Gruppe"))// && (A.TabSp.getI("bits2") & Abfrage.cstWeg) == 0)
        {
          int i = A.TabSp.getI("sortiert")> 0 ? 1 : 2;
          //g.progInfo("sortiert "+A.TabSp.getS("Bezeichnung")+":"+i);
          JCOutlinerFolderNode NodRoot = i == 1 ? Root1 : Root2;
          String sBez = Static.beiLeer(A.TabSp.getS("Bezeichnung"), A.getEigenschaftBezeichnung(g, (Vector)A.TabSp.getInhalt("Vec")));
          Vector<String> Vec=new Vector<String>();
          Vec.addElement(sBez);
          Vec.addElement(g.getBegriffS("Radiobutton",(A.TabSp.getI("bits") & Abfrage.cstSortDesc)>0 ? "absteigend":"aufsteigend"));         
          JCOutlinerNode Nod = new JCOutlinerNode(Vec, NodRoot);
          Nod.setUserData(A.TabSp.getI("Nummer"));
          Vector VecEig = (Vector)A.TabSp.getInhalt("Vec");
          int iPos=g.TabEigenschaften.getPos("Aic",Tabellenspeicher.geti(VecEig.elementAt(VecEig.size()-1)));
          setStyle(Nod,g.TabEigenschaften.getS(iPos, "Datentyp"),false);
        }
      A.TabSp.sort("Reihenfolge",true);
    }
    else if (iNeu==3)
    {
      iSttFill=0;
      String[] s2 = new String[] {g.getBegriffS("Show", "Bezeichnung"), g.getBegriffS("Show", "Einheit")};
      Out1.setColumnButtons(s2);
      Out1.setNumColumns(2);
      for (A.TabSp.moveFirst(); !A.TabSp.out(); A.TabSp.moveNext())
        if (A.TabSp.getInhalt("Gruppe") != null)
        {
          Vector<String> VecS=new Vector<String>();
          VecS.addElement(A.TabSp.getS("Bezeichnung"));
          VecS.addElement(g.getStamm(A.TabSp.getI("Mass")));
          //g.progInfo("add H.A.:"+VecS);
          JCOutlinerFolderNode Nod = new JCOutlinerFolderNode(VecS, Root1);
          Nod.setUserData(A.TabSp.getI("Nummer"));
          Vector Vec=(Vector)A.TabSp.getInhalt("Gruppe");
          for (int i=0;i<Vec.size();i++)
            Nod.addNode((JCOutlinerFolderNode)Vec.elementAt(i));
          Out1.folderChanged(Nod);
        }
    }
    else if (iNeu==4)
    {
      iSttFill=0;
      String[] s2 = new String[] {g.getBegriffS("Show", "Bezeichnung")};
      Out1.setColumnButtons(s2);
      if (TabSE==null)
        TabSE=new Tabellenspeicher(g,new String[]{"Aic","Node"});
      else
        TabSE.clearAll();
      for (A.TabSp.moveFirst(); !A.TabSp.out(); A.TabSp.moveNext())
      {
        Vector VecEig = (Vector)A.TabSp.getInhalt("Vec");
        Object ObjEig = VecEig.elementAt(VecEig.size() - 1);
        int iPos = g.TabEigenschaften.getPos("Aic", Tabellenspeicher.geti(ObjEig));
        String sDatentyp = g.TabEigenschaften.getS(iPos, "Datentyp");
        if (sDatentyp.endsWith("BewZahl2") || sDatentyp.endsWith("BewMass2") || sDatentyp.endsWith("BewWaehrung2"))
        {
          JCOutlinerFolderNode Nod = null;
          if (TabSE.posInhalt("Aic",ObjEig))
            Nod=(JCOutlinerFolderNode)TabSE.getInhalt("Node");
          else
          {
            Nod = new JCOutlinerFolderNode(A.TabSp.getS("Bez2"), Root1);
            //Nod.setUserData(ObjEig);
            Nod.setUserData(A.TabSp.getI("Nummer"));
            TabSE.addInhalt("Aic",ObjEig);
            TabSE.addInhalt("Node",Nod);
          }
          if ((A.TabSp.getI("bits") & Abfrage.cstUnsichtbar)==0)
          {
            int iStamm = A.TabSp.getI("Rel");
            JCOutlinerNode NodNeu = new JCOutlinerNode(g.getStamm(iStamm));
            Vector<Integer> Vec = new Vector<Integer>();
            Vec.addElement(iStamm); // Stamm
            Vec.addElement(A.TabSp.isNull("Filter") ? g.TabEigenschaften.getI(iPos, "aic_stammtyp"):-A.TabSp.getI("Filter")); // Stammtyp bzw Filter
            Vec.addElement(A.TabSp.getI("Nummer")); // Nr der Spalte
            NodNeu.setUserData(Vec);
            Nod.addNode(NodNeu);
          }
        }
      }
      Out1.folderChanged(Root1);
      JCOutlinerNode Nod=Out1.getSelectedNode();
      if (Nod==null) Nod=Root1;
      //g.progInfo("Nod1="+Nod.getLabel());
      if (Nod.getLevel()<2)
      {
        while (Nod instanceof JCOutlinerFolderNode && ((JCOutlinerFolderNode)Nod).getChildren() != null)
          Nod = (JCOutlinerNode)((JCOutlinerFolderNode)Nod).getChildren().elementAt(0);
        //g.progInfo("Nod2="+Nod.getLabel());
        Out1.selectNode(Nod);
      }
      /*if (Sort.geti(Nod.getUserData(),0)==0)
      {
        Out1.setEnabled(false);
        g.fixInfo("disable Outliner");
      }*/
    }
    Out1.folderChanged(Root1);
    Out2.folderChanged(Root2);
    iArt=iNeu;
    bReady=true;
    EnableButtons();
  }

  private void setStyle(JCOutlinerNode Nod,String sDT,boolean b)
  {
    int iPosB= g.getPosBegriff("Datentyp",sDT);
    Image Gif = iPosB<0 ? null:g.LoadImage(g.TabBegriffe.getS(iPosB,"BildFile"));
    if (Gif != null)
    {
            JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
            StyFolder.setItemIcon(Gif);
            Nod.setStyle(StyFolder);
            if (b)
              Nod.getStyle().setForeground(Global.ColEF_Bez2);
    }
  }

  private void EnableButtons()
  {
    if (BtnBeenden!=null) BtnBeenden.setVisible(iTyp==DRUCK);
    if (BtnOk!=null) BtnOk.setVisible(iTyp!=DRUCK);
    if (BtnAbbruch!=null) BtnAbbruch.setVisible(iTyp!=DRUCK);
    ((JDialog)thisFrame).getRootPane().setDefaultButton(BtnOk!=null && BtnOk.isVisible()?BtnOk:BtnBeenden);
    //int iNeu=RadS.isSelected() ? 2:1;
    //boolean bSort=A.iModell==0 || (A.iBits&A.cstNachSave)>0;
    //RadA.getParent().getParent().setVisible(iNeu==2);
    //if (PnlEingabe != null)
    //  PnlEingabe.getParent().getParent().setVisible(iNeu<2);
    //if (!bSort && RadS.isSelected())
    //  RadZ.setSelected(true);
    //RadS.setEnabled(bSort);
    //RadA.setVisible(iNeu==2);
    //RadD.setVisible(iNeu==2);
    if (iNeu != iArt)
      fillOut(iNeu);
    //int iTC=VecTC.elementAt(0).getTabCount();
    VecTC.elementAt(0).setEnabledAt(1,(A.iBits&A.cstLeer)==0);
    VecTC.elementAt(0).setEnabledAt(2,bHA);
    if (VecTC.elementAt(0).getTabCount()>3)
      VecTC.elementAt(0).remove(3);
    //VecTC.elementAt(0).setEnabledAt(3,bSE);
    JCOutlinerNode Nod=Out1.getSelectedNode();
    JCOutlinerNode Nod2=Out2.getSelectedNode();
    int iL=Nod==null ? 0:Nod.getLevel();
    int iL2=Nod2==null ? 0:Nod2.getLevel();
    boolean b=iL==1 && iArt<4 && A.TabSp.posInhalt("Nummer",(Integer)(Nod.getUserData()));
    CboMandant.setEnabled(g.Def() && !bEdit);
    CboFarbe.setEnabled(bEdit && iTyp==TABELLE);
    EdtFormat.setEnabled(bEdit);
    CbxNimm.setEnabled(bEdit);
    CbxMobil.setEnabled(bEdit);
    CbxZwingend.setEnabled(bEdit);
    CbxVZD.setEnabled(bEdit);
    //g.progInfo("Zwingend"+A.TabSp.getI("Nummer")+":"+(A.TabSp.getI("bits")&(Global.cstAlways*0x10000))+"/"+b+",iL="+iL+",iArt="+iArt);
    BtnWeg.setEnabled(bEdit && (b || iL>1 && iArt>2) && (iArt>1 || Root1.getChildren().size()>1 && (A.TabSp.getI("bits")&(Global.cstAlways*0x10000))==0));// && (A.TabSp.getI("sortiert")==0));
    if (Nod!=null && iArt==4 && iL>1 && Sort.geti(Nod.getUserData(),0)==0)
      BtnWeg.setEnabled(false);
    BtnHinzu.setEnabled(bEdit && iL2>0 && (iArt<3 || iArt==3 && iL>0 || iArt==4 && iL>1));
    BtnErsetzen.setEnabled(bEdit && iL>0 && iL2>0 && (iArt<3 || iArt==3 && iL==3 || iArt==4 && iL==2));
    BtnRauf.setEnabled(bEdit && (b && iArt<3 || iArt==3 && iL==2) && Static.allow_up_down(Nod,true));
    BtnRunter.setEnabled(bEdit && (b && iArt<3 || iArt==3 && iL==2) && Static.allow_up_down(Nod,false));
    if (BtnOk2 != null)
      BtnOk2.setEnabled(bEdit && iArt==3 && iL==2);
//    if (BtnOk3 != null)
//      BtnOk3.setEnabled(bEdit && iArt==1 && iL==1);
    if (BtnEdit!=null)
    {
      BtnEdit.setEnabled(!bEdit && !RadStd.isSelected());
      boolean bSave=RadPers.isSelected() || RadJeder.isSelected() && (g.Spezial() || g.SuperUser()) || RadBG.isSelected() && g.Abfrage();
      BtnSave.setEnabled(bEdit && bSave);
      //if (BtnOk!=null) BtnOk.setEnabled(/*Edit && */bSave);
      BtnInit.setEnabled(!bEdit && bSave);//!RadStd.isSelected());
      RadJeder.setEnabled((!bEdit && g.Def() || RadJeder.isSelected()));// && (g.Spezial() || g.SuperUser()));
      RadStd.setEnabled(!bEdit);
      RadPers.setEnabled(!bEdit || RadPers.isSelected());
      RadBG.setEnabled((!bEdit && g.UserManager() || RadBG.isSelected()));// && g.Abfrage());
      RadA.setEnabled(bEdit);
      RadD.setEnabled(bEdit);
      if (BtnBack!=null) BtnBack.setEnabled(bEdit);
    }
    //BtnOk.setEnabled(bEdit); // bin mir nicht sicher
    EdtTitel.setEnabled(iArt==3 && iL==2);
    if (Nod != null && Nod.getLevel()>0 && Nod.getLevel()<2 && A.TabSp.posInhalt("Nummer",Nod.getUserData()))
      if (iArt == 2) // sortiert
      {
        boolean bDesc = (A.TabSp.getI("bits") & A.cstSortDesc) > 0;
        RadA.setSelected(!bDesc);
        RadD.setSelected(bDesc);
      }
      else if (iArt == 1) // Zuordnung
      {
        Vector VecEig = (Vector)A.TabSp.getInhalt("Vec");
        Object ObjEig = VecEig.elementAt(VecEig.size() - 1);
        int iPos = g.TabEigenschaften.getPos("Aic", Tabellenspeicher.geti(ObjEig));
        /*if (Nod != null && Nod.getLevel() > 0)
          while (!Sort.gets(Nod.getLabel(),0).equals(A.TabSp.getS("Bezeichnung")))
            A.TabSp.moveNext();*/
        String sDatentyp = g.TabEigenschaften.getS(iPos, "Datentyp");
        EdtTitel2.setEnabled(bEdit);
        if (bEdit)
        {
          bEdit=false;
//          g.fixtestError("EnableButtons bei Edit=true");
          EdtTitel2.setText(Sort.gets(Nod.getLabel(),0));
          EdtFormat.setTyp(sDatentyp,null,true); //Sort.gets(Out1.getSelectedNode().getLabel(),1));
          EdtFormat.setText(Sort.gets(Nod.getLabel(),2));
          if (EdtFormat.bFehler)
        	  EdtFormat.Cbo.Reset();
          CboFarbe.setSelectedKennung2(Sort.gets(Nod.getLabel(),5));
          CbxMobil.setSelected(Sort.gets(Nod.getLabel(),4).equals("x"));
          CbxNimm.setSelected(Sort.gets(Nod.getLabel(),1).equals("x"));
          CbxVZD.setSelected(Sort.gets(Nod.getLabel(),8).equals("x"));
          int iNr=Sort.geti(Nod.getUserData());
          if (A.VecAlw != null && A.VecAlw.contains(iNr))
          {
        	  CbxZwingend.setSelected(true);
        	  CbxZwingend.setEnabled(false);
          }
          else
          {
        	  CbxZwingend.setSelected(Sort.gets(Nod.getLabel(),6).equals("x"));
        	  CbxZwingend.setEnabled(true);
          }
          bEdit=true;
        }
        else
          EdtTitel2.setText("");
        //CboFarbe.setSelectedAIC(A.TabSp.getI("Aic_Farbe"));
      }
    if (iArt ==3 || iArt==4) // 3=horizontale Auflösung bzw 4=Schnellerfassung
    {
      int iNr=iL==0 ? 0:Sort.geti(iL==1 ? Nod.getUserData():iL==2 ? Nod.getParent().getUserData():Nod.getParent().getParent().getUserData());
      int iFilter=0;
      if (iNr>0)
      {
        int iPos=A.TabSp.getPos("Nummer",iNr);
        if (iPos>=0)
          iFilter=A.TabSp.getI(iPos,"Filter");
      }
      g.progInfo("Art="+iArt+", iL="+iL+":"+Nod.getUserData()+", Nr="+iNr+", Filter="+iFilter);
      if (iL==2 && iArt==3)
        EdtTitel.setText(Nod.getLabelString());
      if (iFilter>0)
        fillStt(-iFilter,iNr);
      else if (iL==3 && iArt==3 || iL==2 && iArt==4)
        fillStt(Sort.geti(Nod.getUserData(),1),iNr);
      else if (iNr != iNrFill)
        fillStt(0,iNr);
    }
    thisFrame.validate();
  }

  private void fillStt(int iStt,int iNr)
  {
    if (iStt != iSttFill)
    {
      iSttFill=iStt;
      iNrFill=iNr;
      g.progInfo("fillStt "+iStt+"/"+iNr);
      Root2.removeChildren();
      if (iStt>0)  // Stammtyp
      {
        int iPos=g.TabStammtypen.getPos("Aic",iStt);
        boolean bCopy=iPos<0 ? false:(g.TabStammtypen.getI(iPos,"bits")&Global.cstCopy)>0;
        String sMBed=bCopy ? g.getCopyMandanten(false):g.getReadMandanten(0);
        g.fixtestInfo("sMBed="+sMBed+", iPos="+iPos); // p2 seit 15.10.2015: für Mandant nötig
        Tabellenspeicher Tab = new Tabellenspeicher(g, "select aic_stamm,bezeichnung from stammview p2 where aic_stammtyp=" + iStt +sMBed+" order by bezeichnung", true);
        for (Tab.moveFirst(); !Tab.out(); Tab.moveNext())
        {
          JCOutlinerNode Nod = new JCOutlinerNode(Tab.getS("Bezeichnung"), Root2);
          Nod.setUserData(Tab.getI("aic_stamm"));
        }
      }
      else if (iStt<0) // Filter
      {
        //g.fixInfo("Filter mit Abfrage "+(-iStt));
        ShowAbfrage Abf=new ShowAbfrage(g,-iStt,Abfrage.cstAbfrage);
        Abf.iBits|=Abf.cstKeinStamm;
        Tabellenspeicher Tab=Abf.getDaten0();
        //Tab.showGrid("Abfrage "+(-iStt));
        //Abf.showResult(thisFrame);
        Abf.initOutliner(g, Out2);
        Abf.TabToOutliner(Out2, Tab, null);
        Root2=(JCOutlinerFolderNode)Out2.getRootNode();
      }
      Out2.folderChanged(Root2);
      EnableButtons();
    }
  }

}
