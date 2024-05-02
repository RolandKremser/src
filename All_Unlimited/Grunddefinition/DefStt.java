package All_Unlimited.Grunddefinition;

import java.awt.*;

import javax.swing.JPanel;

import jclass.bwt.*;

import java.util.Vector;

import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.ComboSort;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPopupMenu;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import All_Unlimited.Allgemein.Eingabe.EigenschaftsEingabe;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: Dient zum definieren von Stammtypen</p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.10
 */
public class DefStt extends Formular
{
  private Global g;
  private AUOutliner Out = new AUOutliner(null);
  private AUOutliner OutZ = new AUOutliner(null);
  private Text TxtBezeichnung = new Text("",200);
  private Text TxtKennung = new Text("",98,Text.KENNUNG);
  private Text TxtWebIcon = new Text("",98);
  private Text TxtWebIconOffen = new Text("",98);
  private Text TxtWebFarbe = new Text("",98);
  private BildEingabe BtnBild=null;
  private BildEingabe BtnBildFX=null;
  private ComboSort CboStt=null;
  private EigenschaftsEingabe CboANR=null;
  private AUCheckBox CbxEnde=null;
  private AUCheckBox CbxTranslate=null;
  private AUCheckBox CbxBenutzer=null;
  private AUCheckBox CbxEinheit=null;
  private AUCheckBox CbxLizenz=null;
  private AUCheckBox CbxCopy=null;
  private AUCheckBox CbxStdFormular=null;
  private AUCheckBox CbxKeinStichtag=null;
  private AUCheckBox CbxModul=null;
  private AUCheckBox CbxHA=null;
  private AUCheckBox CbxBFilter=null;
  private AUCheckBox CbxSttDef=null;
  private AUCheckBox CbxSttBed=null;
  private AUCheckBox CbxSttFav=null;
  private AUCheckBox CbxSttCopy=null;
  private AUCheckBox CbxSttFirma=null;
  private AUCheckBox CbxForStatus=null;
  private AUCheckBox CbxTod=null;
  private AUCheckBox CbxFavWeb=null;
  private ComboSort CboAufgabe=null;
  private ComboSort CboStatus=null;
  private static int iAktAIC=0;
  private AUTextArea Memo=new AUTextArea();
  private JCOutlinerFolderNode NodeMom=null;
  private JPopupMenu popup;
  private static DefStt Self;

  public static DefStt get(Global g,int riStt)
    {
      iAktAIC=riStt;
      if (Self==null)
        new DefStt(g);
      else
        Self.show();
      return Self;
  }

  public static void free()
  {

    if (Self != null)
    {
      Self.g.winInfo("DefStt.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

  private DefStt(Global glob)
  {
    super("DefStt", null, glob);
    Self=this;
    glob.winInfo("DefStt.create");
    Build(glob);
    show();
  }

  public void show()
  {
    FillOutliner();
    super.show();
    Static.makeVisible(Out,null);
    setSelected();
  }

  private void Build(Global glob)
  {
    g = glob;
    g.initOutliner(Out,new String[] {"Bezeichnung","Kennung","Sub","Change","Bits","Nr","WebIcon","WebIconOffen","WebFarbe","WebAnzahl"});
    g.initOutliner(OutZ,new String[] {"Bezeichnung","Reihenfolge","Datentyp"});

    JPanel PnlOutliner = getFrei("Outliner Stamm");
      PnlOutliner.add(Out);
    JPanel PnlZuordnung = getFrei("Outliner Zuordnung");
      PnlZuordnung.add(OutZ);
    JPanel PnlMemo = getFrei("Memo");
      PnlMemo.add(Memo);
    JPanel PnlEingabe = getFrei("Eingabe");
      PnlEingabe.setLayout(new BorderLayout(2,2));
        JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
          g.addLabel2(Pnl,"Bezeichnung");
          g.addLabel2(Pnl,"Kennung");
          g.addLabel2(Pnl,"WebIcon");
          g.addLabel2(Pnl,"WebIconOffen");
          g.addLabel2(Pnl,"WebFarbe");
          g.addLabel2(Pnl,"Bild-Swing");
          g.addLabel2(Pnl,"Bild-FX");
          g.addLabel2(Pnl,"Sub");
          g.addLabel2(Pnl,"ANR");
          g.addLabel2(Pnl,"Aufgabe");
          g.addLabel2(Pnl,"Status");
          g.addLabel2(Pnl,"Bits");
          Pnl.add(new JPanel());
          Pnl.add(new JPanel());
          Pnl.add(new JPanel());
          Pnl.add(new JPanel());
          Pnl.add(new JPanel());
          Pnl.add(new JPanel());
          Pnl.add(new JPanel());
          Pnl.add(new JPanel());
          Pnl.add(new JPanel());
        PnlEingabe.add("West",Pnl);
        Pnl = new JPanel(new GridLayout(0,1,2,2));
            g.addComp(Pnl,TxtBezeichnung);
            g.addComp(Pnl,TxtKennung);
            g.addComp(Pnl,TxtWebIcon);
            g.addComp(Pnl,TxtWebIconOffen);
            g.addComp(Pnl,TxtWebFarbe);
            BtnBild = new BildEingabe(thisJFrame(),g);
            BtnBild.activateEvent();
            BtnBildFX = new BildEingabe(thisJFrame(),g);
            BtnBildFX.activateEvent();
            g.addComp(Pnl,BtnBild);
            g.addComp(Pnl,BtnBildFX);
            CboStt = new ComboSort(g);
            CboStt.fillDefTable("Stammtyp", true);
            g.addComp(Pnl,CboStt);
            CboANR = new EigenschaftsEingabe(g,thisFrame);
            g.addComp(Pnl,CboANR);
            CboAufgabe = new ComboSort(g);
            CboAufgabe.fillBegriffTable("Aufgabe", true);
            g.addComp(Pnl,CboAufgabe);
            CboStatus = new ComboSort(g);
            CboStatus.fillDefTable("Status", true);
            g.addComp(Pnl,CboStatus);
            CbxEnde=getCheckboxM("Ende",false);
            CbxTranslate=getCheckboxM("translate2",false);
            CbxBenutzer=getCheckboxM("Benutzer",false);
            CbxEinheit=getCheckboxM("Einheit",false);
            CbxLizenz=getCheckboxM("Lizenz",false);
            CbxCopy=getCheckboxM("Copy",false);
            CbxStdFormular=getCheckboxM("Std_formular",false);
            CbxKeinStichtag=getCheckboxM("kein_Stichtag",false);
            CbxModul=getCheckboxM("Modul",false);
            CbxHA=getCheckboxM("HA",false);
            CbxBFilter=getCheckboxM("BFilter",false);
            CbxSttDef=getCheckboxM("SttDef",false);
            CbxSttBed=getCheckboxM("bedingt",false);
            CbxSttFav=getCheckboxM("Favoriten",false);
            CbxSttCopy=getCheckboxM("SttCopy",false);
            CbxSttFirma=getCheckboxM("SttFirma",false);
            CbxForStatus=getCheckboxM("forStatus",false);
            CbxFavWeb=getCheckboxM("FavWeb",false);
            CbxTod=getCheckboxM("Tod",false);
            JPanel PnlSub = new JPanel(new GridLayout(1,2,2,2));
              PnlSub.add(CbxEnde);
              PnlSub.add(CbxTranslate);
            g.addComp(Pnl,PnlSub);
            PnlSub = new JPanel(new GridLayout(1,2,2,2));
              PnlSub.add(CbxBenutzer);
              PnlSub.add(CbxEinheit);
            g.addComp(Pnl,PnlSub);
            PnlSub = new JPanel(new GridLayout(1,2,2,2));
              PnlSub.add(CbxLizenz);
              PnlSub.add(CbxCopy);
            g.addComp(Pnl,PnlSub);
            PnlSub = new JPanel(new GridLayout(1,2,2,2));
              PnlSub.add(CbxStdFormular);
              PnlSub.add(CbxKeinStichtag);
            g.addComp(Pnl,PnlSub);
            PnlSub = new JPanel(new GridLayout(1,2,2,2));
              PnlSub.add(CbxModul);
              PnlSub.add(CbxHA);
            g.addComp(Pnl,PnlSub);
            PnlSub = new JPanel(new GridLayout(1,2,2,2));
              PnlSub.add(CbxBFilter);
              PnlSub.add(CbxTod);
            g.addComp(Pnl,PnlSub);
            PnlSub = new JPanel(new GridLayout(1,2,2,2));
              PnlSub.add(CbxSttDef);
              PnlSub.add(CbxSttBed);
            g.addComp(Pnl,PnlSub);
            PnlSub = new JPanel(new GridLayout(1,2,2,2));
              PnlSub.add(CbxSttFav);
              PnlSub.add(CbxFavWeb);
            g.addComp(Pnl,PnlSub);
            PnlSub = new JPanel(new GridLayout(1,2,2,2));
              PnlSub.add(CbxSttCopy);
              PnlSub.add(CbxSttFirma);
            g.addComp(Pnl,PnlSub);
            g.addComp(Pnl,CbxForStatus);

        PnlEingabe.add("Center",Pnl);
        // Events
        Out.addItemListener(new JCOutlinerListener()
        {
            public void itemStateChanged(JCItemEvent e) {}
            public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {}
            public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {}
            public void outlinerNodeSelectBegin(JCOutlinerEvent e) {}
            public void outlinerNodeSelectEnd(JCOutlinerEvent e)
            {
                    setSelected();
            }
        });

        ActionListener AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
            if (s.equals("Neu"))
              SttNew();
            else if (s.equals("Loeschen"))
              SttDel();
            else if (s.equals("Info"))
              Info(false).showGrid("Stt "+iAktAIC);
            else if (s.equals("Test"))
              Reinigung.TestStt(g,iAktAIC);
            else if (s.equals("show"))
              sqlShow();
            else if (s.equals("Abfrage"))
            	DefAbfrage.get(g, new ShowAbfrage(g,0,Abfrage.cstAbfrage), iAktAIC).show(false);
            else if (s.equals("Speichern"))
              SttSave();
            else if (s.equals("ANR"))
              Reinigung.setSttANR(g,iAktAIC,CboANR.getSelectedAIC());
            else if (s.equals("Beenden"))
              hide();
            else if (s.startsWith("Hierarchie"))
                ShowHierarchie();
            else if (s.startsWith("Sttbits"))
                ShowSttbits();
          }
        };
        g.BtnAdd(getFormularButton("Abfrage"),"Abfrage",AL);
        g.BtnAdd(getButton("Neu"),"Neu",AL);
        g.BtnAdd(getButton("Loeschen"),"Loeschen",AL);
        g.BtnAdd(getButton("Info"),"Info",AL);
        g.BtnAdd(getButton("Test"),"Test",AL);
        g.BtnAdd(getButton("show"),"show",AL);
        g.BtnAdd(getButton("Speichern"),"Speichern",AL);
        g.BtnAdd(getButton("ANR"),"ANR",AL);
        g.BtnAdd(getButton("Hierarchie"),"Hierarchie",AL);
        g.BtnAdd(getButton("Beenden"),"Beenden",AL);

        popup= new JPopupMenu();
        g.addMenuItem("Neu", popup,null,AL);
        g.addMenuItem("Loeschen", popup,null,AL);
        popup.addSeparator();
        g.addMenuItem("Info", popup,null,AL);
        g.addMenuItem("Hierarchie", popup,null,AL);
        g.addMenuItem("Sttbits", popup,null,AL);
        Out.getOutliner().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev) {}
          public void mouseClicked(MouseEvent ev)
          {
            //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK && g.Def())
            if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM) && g.Def())
              popup.show((Component)ev.getSource(), ev.getX(), ev.getY());
          }

          public void mouseEntered(MouseEvent ev) {}
          public void mouseExited(MouseEvent ev) {}
          public void mouseReleased(MouseEvent ev) {}
        });

  }
  
  private String getBildFile(int iAic,boolean bFX)
  {
	  String s=SQL.getString(g, "select filename from daten_bild where aic_tabellenname="+g.TabTabellenname.getAic("STAMMTYP")+" and aic_fremd="+iAic+" and aic_Zustand="+(bFX ? Global.iSpFX:Global.iSpSw));
	  //g.fixtestInfo("getBildFile von "+iAic+"/"+bFX+"="+s);
	  return s;
  }

  private void setSelected()
  {
    JCOutlinerNode Node = Out.getSelectedNode();
    if (Node != null && Node.getLevel() == 1)
      iAktAIC = Sort.geti((Vector)Node.getUserData(), 0);
    else
      iAktAIC = 0;
    g.fixtestInfo("DefStt.setSelected:"+iAktAIC);
    long lClock=Static.get_ms();
    if (iAktAIC > 0)
    {
      Vector VecVisible=(Vector)Node.getLabel();
      Vector VecInvisible=(Vector)Node.getUserData();
      TxtBezeichnung.setText((String)VecVisible.elementAt(0));
      TxtKennung.setText((String)VecVisible.elementAt(1));
      Memo.setText((String)VecInvisible.elementAt(2));
      CboStt.setSelectedAIC(Sort.geti(VecInvisible,3));
      CboAufgabe.setSelectedAIC(Sort.geti(VecInvisible,5));
      CboStatus.setSelectedAIC(Sort.geti(VecInvisible,6));
      TxtWebIcon.setText((String)VecVisible.elementAt(6));
      TxtWebIconOffen.setText((String)VecVisible.elementAt(7));
      TxtWebFarbe.setText((String)VecVisible.elementAt(8));
      int iBits=Sort.geti(VecInvisible,1);
      CbxEnde.setSelected((iBits&Global.cstEnde)>0);
      CbxTranslate.setSelected((iBits&Global.cstTranslate)>0);
      CbxBenutzer.setSelected((iBits&Global.cstBenutzer)>0);
      CbxEinheit.setSelected((iBits&Global.cstEinheit)>0);
      CbxLizenz.setSelected((iBits&Global.cstLizenz)>0);
      CbxCopy.setSelected((iBits&Global.cstCopy)>0);
      CbxStdFormular.setSelected((iBits&Global.cstStdFormular)>0);
      CbxKeinStichtag.setSelected((iBits&Global.cstKeinStichtag)>0);
      CbxModul.setSelected((iBits&Global.cstModul)>0);
      CbxHA.setSelected((iBits&Global.cstHA)>0);
      CbxBFilter.setSelected((iBits&Global.cstBFilter)>0);
      CbxSttDef.setSelected((iBits&Global.cstSttDef)>0);
      CbxSttBed.setSelected((iBits&Global.cstSttBed)>0);
      CbxSttFav.setSelected((iBits&Global.cstSttFav)>0);
      CbxSttCopy.setSelected((iBits&Global.cstSttCopy)>0);
      CbxSttFirma.setSelected((iBits&Global.cstSttFirma)>0);
      CbxForStatus.setSelected((iBits&Global.cstForStatus)>0);
      CbxFavWeb.setSelected((iBits&Global.cstFavWeb)>0);
      CbxTod.setSelected(SQL.getInteger(g,"select tod from stammtyp where aic_stammtyp=?",0,""+iAktAIC)==1);
      Tabellenspeicher Tab = new Tabellenspeicher(g,"Select e.aic_Eigenschaft aic,e.kennung" + g.AU_Bezeichnung2("Eigenschaft", "e") +
                                                  " from Stammtyp_Zuordnung z join Eigenschaft e on e.aic_Eigenschaft=z.aic_Eigenschaft and e.aic_stammtyp="+g.iSttANR +
                                                  g.join("Begriff", "e") +" where Begriff.kennung='Gruppe' and z.aic_Stammtyp="+iAktAIC, true);
      CboANR.Cbo.fillCbo(Tab, true);
      CboANR.setSelectedAIC(Sort.geti(VecInvisible,4));
      String sFile=getBildFile(Sort.geti(VecInvisible,0),false);
      Image Img=Static.Leer(sFile) ? null:g.LoadImage(sFile);
      if (Img==null)
        BtnBild.Delete();
      else
        BtnBild.setValue(Img,sFile,null);
      sFile=getBildFile(Sort.geti(VecInvisible,0),true);
      Img=Static.Leer(sFile) ? null:g.LoadImage(sFile);
      if (Img==null)
          BtnBildFX.Delete();
        else
      	BtnBildFX.setValue(Img,sFile,null);
      
    }
    else
    {
      TxtBezeichnung.setText("");
      TxtKennung.setText("");
      TxtWebIcon.setText("");
      TxtWebIconOffen.setText("");
      TxtWebFarbe.setText("");
      Memo.setText("");
      CboStt.setSelectedAIC(0);
      CboAufgabe.setSelectedAIC(0);
      CboStatus.setSelectedAIC(0);
      CboANR.setSelectedAIC(0);
      CbxEnde.setSelected(false);
      CbxTranslate.setSelected(false);
      CbxBenutzer.setSelected(false);
      CbxEinheit.setSelected(false);
      CbxLizenz.setSelected(false);
      CbxCopy.setSelected(false);
      CbxStdFormular.setSelected(false);
      CbxKeinStichtag.setSelected(false);
      CbxModul.setSelected(false);
      CbxHA.setSelected(false);
      CbxBFilter.setSelected(false);
      CbxSttDef.setSelected(false);
      CbxSttBed.setSelected(false);
      CbxTod.setSelected(false);
      CbxSttFav.setSelected(false);
      CbxSttCopy.setSelected(false);
      CbxSttFirma.setSelected(false);
      CbxForStatus.setSelected(false);
      CbxFavWeb.setSelected(false);
      BtnBild.Delete();
      BtnBildFX.Delete();
    }
    fillOutZ();
    g.clockInfo("DefStt-Select "+g.TabStammtypen.getBezeichnungS(iAktAIC), lClock);
  }

  private void SttNew()
  {
    Out.selectNode(Out.getRootNode(),null);
  }

  private void SttSave()
  {
    if (TxtKennung.isNull())
    {
      new Message(Message.ERROR_MESSAGE, thisJFrame(), g, 10).showDialog("KennungLeer");
      return;
    }
    if (SQL.exists(g, "select aic_stammtyp from stammtyp WHERE aic_stammtyp<> " + iAktAIC + " and Kennung='" + TxtKennung.getText() + "'"))
    {
      new Message(Message.WARNING_MESSAGE, thisJFrame(), g, 10).showDialog("KennungVorhanden");
      return;
    }
    SQL Qry = new SQL(g);
    int iBits = (CbxEnde.isSelected() ? Global.cstEnde : 0)+(CbxTranslate.isSelected() ? Global.cstTranslate : 0)+
        (CbxBenutzer.isSelected() ? Global.cstBenutzer : 0)+(CbxEinheit.isSelected() ? Global.cstEinheit : 0)+
        (CbxLizenz.isSelected() ? Global.cstLizenz : 0)+(CbxCopy.isSelected() ? Global.cstCopy : 0)+
        (CbxStdFormular.isSelected() ? Global.cstStdFormular : 0)+(CbxKeinStichtag.isSelected() ? Global.cstKeinStichtag : 0)+
        (CbxModul.isSelected() ? Global.cstModul : 0)+(CbxHA.isSelected() ? Global.cstHA : 0)+(CbxBFilter.isSelected() ? Global.cstBFilter : 0)+
        (CbxSttDef.isSelected() ? Global.cstSttDef : 0)+(CbxSttBed.isSelected() ? Global.cstSttBed : 0)+(CbxSttFav.isSelected() ? Global.cstSttFav : 0)+
        (CbxSttCopy.isSelected() ? Global.cstSttCopy : 0)+(CbxSttFirma.isSelected() ? Global.cstSttFirma : 0)+(CbxForStatus.isSelected() ? Global.cstForStatus : 0)+(CbxFavWeb.isSelected() ? Global.cstFavWeb : 0);
    Qry.add("Kennung",TxtKennung.getText());
    Qry.add0("STA_AIC_STAMMTYP",CboStt.getSelectedAIC());
    Qry.add0("Aufgabe",CboAufgabe.getSelectedAIC());
    Qry.add0("AIC_Status",CboStatus.getSelectedAIC());
    Qry.add0("ANR_Eig",CboANR.getSelectedAIC());
    Qry.add("SttBits",iBits);
    Qry.add0("Tod",CbxTod.isSelected());
    Qry.add("aic_logging",g.getLog());
    Qry.add("WebIcon",TxtWebIcon.getText());
    Qry.add("WebIconOffen",TxtWebIconOffen.getText());
    Qry.add("WebFarbe",TxtWebFarbe.getText());
    boolean bNew=iAktAIC==0;
    if (bNew)
      iAktAIC=Qry.insert("Stammtyp",true);
    else
      Qry.update("Stammtyp",iAktAIC);
    Qry.free();
    if (CboANR.Cbo.Modified())
      Reinigung.setSttANR(g,iAktAIC,CboANR.getSelectedAIC());
    int iTabStt=g.TabTabellenname.getAic("STAMMTYP");
    g.setBezeichnung("",TxtBezeichnung.getText(),iTabStt,iAktAIC,0);
    if (Memo.Modified())
      g.setMemo(Memo.getValue(),"",iTabStt,iAktAIC,0);
    if (BtnBild.Modified())
      g.setImage(BtnBild.getFilename(),iTabStt,iAktAIC,Global.iSpSw);
    if (BtnBildFX.Modified())
      g.setImage(BtnBildFX.getFilename(),iTabStt,iAktAIC,Global.iSpFX);
    if (!CbxTod.isSelected())
      g.putTabStammtyp(iAktAIC,TxtKennung.getText(),TxtBezeichnung.getText(),BtnBild.getFilename(),BtnBildFX.getFilename(),CboStt.getSelectedAIC(),iBits,CboANR.getSelectedAIC(),bNew);
    FillOutliner();
    Static.makeVisible(Out,null);
  }

  private void sqlShow()
  {
    String s = "select aic_stammtyp aic, kennung" + g.AU_Bezeichnung3("Stammtyp", "stt") +
        ",(select count(*) from stamm where aic_stammtyp=stt.aic_stammtyp) Gesamt,(select count(*) from stammview2 where aic_stammtyp=stt.aic_stammtyp and aic_rolle is null) aktiv" +
        ",(select count(*) from stammview where aic_stammtyp=stt.aic_stammtyp and aic_rolle is null) ZR,(select count(*) from stamm where aic_stammtyp=stt.aic_stammtyp)-(select count(*) from stammview2 where aic_stammtyp=stt.aic_stammtyp and aic_rolle is null) del"+
        g.bei("sttbits",Global.cstEnde,"Ende")+g.bei("sttbits",Global.cstTranslate,"Translate")+g.bei("sttbits",Global.cstBenutzer,"Benutzer")+g.bei("sttbits",Global.cstEinheit,"Einheit")+
        g.bei("sttbits",Global.cstLizenz,"Lizenz")+g.bei("sttbits",Global.cstCopy,"Copy")+g.bei("sttbits",Global.cstStdFormular,"StdFormular")+g.bei("sttbits",Global.cstKeinStichtag,"KeinStichtag")+
        " from stammtyp stt";
    new Tabellenspeicher(g,s,true).showGrid("Stammtypliste",thisJFrame());
  }

  private void ShowHierarchie()
        {
          JDialog Frm = new JDialog(thisJFrame(),"Stammtypen",false);
          AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
          String [] s = new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Nr")};
          Out.setColumnButtons(s);
          Out.setNumColumns(s.length);
          Frm.getContentPane().add("Center",Out);
          JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
          int iMom=iAktAIC;
          int iPos=g.TabStammtypen.getPos("Aic",iMom);
          while (g.TabStammtypen.getI(iPos,"Darunter")>0)
          {
            iMom=g.TabStammtypen.getI(iPos,"Darunter");
            iPos=g.TabStammtypen.getPos("Aic", iMom);
          }
          NodeMom=null;
          fillHierarchie(iMom,fillNode(g.TabStammtypen.getPos("Aic",iMom),NodeRoot));
          if (NodeMom==null)
            Out.folderChanged(NodeRoot);
          else
            Static.makeVisible(Out,NodeMom);
          Frm.pack();
          Static.centerComponent(Frm,thisFrame);
          Frm.setVisible(true);
        }

        private void fillHierarchie(int riStt,JCOutlinerFolderNode NodeP)
        {
          for (int i = 0; i < g.TabStammtypen.size(); i++)
            if (g.TabStammtypen.getI(i, "Darunter") == riStt)
              fillHierarchie(g.TabStammtypen.getI(i, "Aic"), fillNode(i,new JCOutlinerFolderNode("", NodeP)));
        }

        private JCOutlinerFolderNode fillNode(int iPos,JCOutlinerFolderNode Node)
        {
          int iAic=g.TabStammtypen.getI(iPos,"Aic");
          Vector<Object> VecSpalten = new Vector<Object>();
          VecSpalten.addElement(g.TabStammtypen.getS(iPos, "Bezeichnung"));
          VecSpalten.addElement(g.TabStammtypen.getS(iPos, "Kennung"));
          VecSpalten.addElement(iAic);
          Node.setLabel(VecSpalten);
          Node.setUserData(iAic);
          Node.setStyle(g.getStyle(g.getSttGif(iAic)));
          if (iAic == iAktAIC)
            NodeMom = Node;
          return Node;
        }

  private void ShowSttbits()
  {
    Tabellenspeicher Tab2 = new Tabellenspeicher(g, new String[] {"Nr", "Kennung", "Bezeichnung", "Anzahl"});
    addArt(Tab2,"Aufgabe","Aufgabe",-3);
    addArt(Tab2,"AIC_Status","Status",-2);
    addArt(Tab2,"ANR_Eig","ANR",-1);
    addBit(Tab2, "cstEnde", "Ende", g.cstEnde, 0);
    addBit(Tab2, "cstTranslate", "translate2", g.cstTranslate, 1);
    addBit(Tab2, "cstBenutzer", "Benutzer", g.cstBenutzer, 2);
    addBit(Tab2, "cstEinheit", "Einheit", g.cstEinheit, 3);
    addBit(Tab2, "cstLizenz", "Lizenz", g.cstLizenz, 4);
    addBit(Tab2, "cstCopy", "Copy", g.cstCopy, 5);
    addBit(Tab2, "cstStdFormular", "Std_formular", g.cstStdFormular, 6);
    addBit(Tab2, "cstKeinStichtag", "kein_Stichtag", g.cstKeinStichtag, 7);
    addBit(Tab2, "cstModul", "Modul", g.cstModul, 8);
    addBit(Tab2, "cstHA", "HA", g.cstHA, 9);
    addBit(Tab2, "cstBFilter", "BFilter", g.cstBFilter, 10);
    addBit(Tab2, "cstSttDef", "SttDef", g.cstSttDef, 11);
    addBit(Tab2, "cstSttBed", "bedingt", g.cstSttBed, 12);
    addBit(Tab2, "cstSttFav", "Favoriten", g.cstSttFav, 13);
    addBit(Tab2, "cstSttCopy", "SttCopy", g.cstSttCopy, 14);
    addBit(Tab2, "cstSttFirma", "SttFirma", g.cstSttFirma, 15);
    addBit(Tab2, "cstForStatus", "forStatus", g.cstForStatus, 16);
    addBit(Tab2, "cstFavWeb", "FavWeb", g.cstFavWeb, 17);
    final JDialog FomGid = new JDialog(thisJFrame(), "Stammtypbits", false);
    AUOutliner Grid = new AUOutliner();
    FomGid.getContentPane().add("Center", Grid);
    Tab2.showGrid(Grid);
    FomGid.pack();//setSize(400, 600);
    Static.centerComponent(FomGid,thisFrame);
    Grid.addActionListener(new JCActionListener() {
          public void actionPerformed(JCActionEvent ev) {
            JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
            String sSp=null;
            int i=Sort.geti(Nod.getLabel(),0);
            if (i<0)
            {
          	  sSp=Sort.gets(Nod.getLabel(), 1);
          	  if (sSp.equals("ANR_Eig"))
          		  sSp="(select kennung from eigenschaft where aic_eigenschaft="+sSp+") "+ Sort.gets(Nod.getLabel(),2);
          	  else if (sSp.equals("AIC_Status"))
          		  sSp="(select kennung from Status where aic_Status=stammtyp."+sSp+") "+ Sort.gets(Nod.getLabel(),2);
          	  else if (sSp.equals("Aufgabe"))
          		  sSp="(select kennung from begriff where aic_begriff=stammtyp."+sSp+") "+ Sort.gets(Nod.getLabel(),2);
            }
            Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_stammtyp aic"+g.AU_Bezeichnung("Stammtyp")+",kennung"+(i<0 ? ","+sSp:"")+" from Stammtyp where "+(i<0 ? Sort.gets(Nod.getLabel(),1)+" is not null":g.bit("SttBits",(long)Math.pow(2,i))),true);
            Tab.showGrid("Stammtyp mit bit " + Sort.gets(Nod.getLabel(),2), thisFrame);
          }
        });
    FomGid.setVisible(true);
  }

  private void addBit(Tabellenspeicher Tab2,String sConst,String sBez,long l,int i)
  {
      Tab2.addInhalt("Nr",i);
      Tab2.addInhalt("Kennung",sConst);
      Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getBegriffS("Checkbox",sBez));
      Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from Stammtyp where "+g.bit("SttBits",l)));
  }
  
  private void addArt(Tabellenspeicher Tab2,String sConst,String sBez,int i)
  {
	  Tab2.addInhalt("Nr",i);
	  Tab2.addInhalt("Kennung",sConst);
	  Tab2.addInhalt("Bezeichnung",sBez==null?"":g.getShow(sBez));
	  Tab2.addInhalt("Anzahl",SQL.getInteger(g,"select count(*) from Stammtyp where "+sConst+" is not null"));
  }

  private Tabellenspeicher TabCount(String s)
  {
    return new Tabellenspeicher(g, "select '"+s+"' Gruppe,null Bezeichnung,null Kennung,null aic,Anzahl from (select aic_stammtyp,count(*) Anzahl from "+s+
                                " group by aic_stammtyp) x where aic_stammtyp=?",""+iAktAIC, true);
  }

  private Tabellenspeicher Info(boolean bDel)
  {
    Tabellenspeicher Tab = new Tabellenspeicher(g, "select (select kennung from begriffgruppe where aic_begriffgruppe=begriff.aic_begriffgruppe) Gruppe,defbezeichnung Bezeichnung,begriff.kennung,aic_begriff aic,null Anzahl from begriff where aic_stammtyp=?",""+iAktAIC,true);
    new Tabellenspeicher(g, "select 'Eigenschaft' Gruppe" + g.AU_Bezeichnung("eigenschaft") +",kennung,aic_eigenschaft aic,null Anzahl from eigenschaft where aic_stammtyp=?",""+iAktAIC, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'Rolle' Gruppe" + g.AU_Bezeichnung("Rolle") +",kennung,aic_Rolle aic,null Anzahl from Rolle where aic_stammtyp=?",""+iAktAIC, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'Abschlusstyp' Gruppe" + g.AU_Bezeichnung("Abschlusstyp") +",kennung,aic_Abschlusstyp aic,null Anzahl from Abschlusstyp where aic_stammtyp=?",""+iAktAIC, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'Hauptschirm' Gruppe" + g.AU_Bezeichnung("Hauptschirm") +",kennung,aic_Hauptschirm aic,null Anzahl from Hauptschirm where aic_stammtyp=?",""+iAktAIC, true).addTo(Tab, true);
    if (!bDel)
      new Tabellenspeicher(g,"select 'DATEN' Gruppe,null Bezeichnung,null Kennung,null aic,count(*) Anzahl from stamm where aic_stammtyp=?",""+iAktAIC, true).addTo(Tab, true);
    TabCount("APPLIKATION_ZUORDNUNG").addTo(Tab, true);
    TabCount("STAMMTYP_ZUORDNUNG").addTo(Tab, true);
    TabCount("SPALTE").addTo(Tab, true);
    TabCount("SPALTE_ZUORDNUNG").addTo(Tab, true);
    TabCount("FENSTERPOS").addTo(Tab, true);
    TabCount("VERLAUF").addTo(Tab, true);
    /*new Tabellenspeicher(g, "select 'STAMMTYP_ZUORDNUNG' Gruppe,null Bezeichnung,null Kennung,null aic,Anzahl from (select aic_stammtyp,count(*) Anzahl from STAMMTYP_ZUORDNUNG group by aic_stammtyp) x where aic_stammtyp=?",""+iAktAIC, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'FENSTERPOS' Gruppe,null Bezeichnung,null Kennung,null aic,Anzahl from (select aic_stammtyp,count(*) Anzahl from FENSTERPOS group by aic_stammtyp) x where aic_stammtyp=?",""+iAktAIC, true).addTo(Tab, true);
    new Tabellenspeicher(g, "select 'VERLAUF' Gruppe,null Bezeichnung,null Kennung,null aic,Anzahl from (select aic_stammtyp,count(*) Anzahl from verlauf group by aic_stammtyp) x where aic_stammtyp=?",""+iAktAIC, true).addTo(Tab, true);
    */
    return Tab;
  }

  private void SttDel()
  {
    Tabellenspeicher Tab=Info(true);
    String s=TxtBezeichnung.getText();
    if(Tab.isEmpty())
    {
      int pane = new Message(Message.YES_NO_OPTION, thisJFrame(), g).showDialog("Loeschen", new String[] {s});
      if (pane == Message.YES_OPTION)
      {
        g.bISQL=true;
        g.exec("delete from Stammtyp where aic_Stammtyp="+iAktAIC);
        g.bISQL=false;
        if (g.sError==null)
          FillOutliner();
        else
          new Message(Message.WARNING_MESSAGE, thisJFrame(), g,10).showDialog("BereitsVerwendet", new String[] {s});
      }
    }
    else
      new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB+Message.UNMODAL,thisJFrame(),g).showDialog("BereitsVerwendet",new String[] {s},Tab);

  }

  private String addBits(int iBits)
  {
    String sBits = "";
    if ((iBits&Global.cstEnde)>0)
      sBits+=g.getBegriffS("Checkbox","Ende")+" ";
    if ((iBits&Global.cstTranslate)>0)
      sBits+=g.getBegriffS("Checkbox","translate2")+" ";
    if ((iBits&Global.cstBenutzer)>0)
      sBits+=g.getBegriffS("Checkbox","Benutzer")+" ";
    if ((iBits&Global.cstEinheit)>0)
      sBits+=g.getBegriffS("Checkbox","Einheit")+" ";
    if ((iBits&Global.cstLizenz)>0)
      sBits+=g.getBegriffS("Checkbox","Lizenz")+" ";
    if ((iBits&Global.cstCopy)>0)
      sBits+=g.getBegriffS("Checkbox","Copy")+" ";
    if ((iBits&Global.cstStdFormular)>0)
      sBits+=g.getBegriffS("Checkbox","Std_formular")+" ";
    if ((iBits&Global.cstKeinStichtag)>0)
      sBits+=g.getBegriffS("Checkbox","kein_Stichtag")+" ";
    if ((iBits&Global.cstModul)>0)
      sBits+=g.getBegriffS("Checkbox","Modul")+" ";
    if ((iBits&Global.cstHA)>0)
      sBits+=g.getBegriffS("Checkbox","HA")+" ";
    if ((iBits&Global.cstBFilter)>0)
      sBits+=g.getBegriffS("Checkbox","BFilter")+" ";
    if ((iBits&Global.cstSttDef)>0)
      sBits+=g.getBegriffS("Checkbox","SttDef")+" ";
    if ((iBits&Global.cstSttBed)>0)
      sBits+=g.getBegriffS("Checkbox","bedingt")+" ";
    if ((iBits&Global.cstSttFav)>0)
        sBits+=g.getBegriffS("Checkbox","Favoriten")+" ";
    if ((iBits&Global.cstSttCopy)>0)
        sBits+=g.getBegriffS("Checkbox","SttCopy")+" ";
    if ((iBits&Global.cstSttFirma)>0)
        sBits+=g.getBegriffS("Checkbox","SttFirma")+" ";
    if ((iBits&Global.cstForStatus)>0)
        sBits+=g.getBegriffS("Checkbox","forStatus")+" ";
    if ((iBits&Global.cstFavWeb)>0)
        sBits+=g.getBegriffS("Checkbox","FavWeb")+" ";
    return sBits;
  }

  private void FillOutliner()
  {
          SQL Qry = new SQL(g);
          JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
          NodeRoot.removeChildren();

          if(Qry.open("select * from (select stammtyp.*"+g.AU_Bezeichnung("stammtyp")+g.AU_Memo("stammtyp")+
        		  	  ",(select count(*) from begriff where web=1 and aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+" and aic_stammtyp=stammtyp.aic_stammtyp and aic_bewegungstyp is null) WebAnzahl"+
                      ",(select ein from logging where aic_logging=stammtyp.aic_logging) log from stammtyp) x order by bezeichnung"))
          {
              for(;!Qry.eof();Qry.moveNext())
              {
                      Vector<Comparable> VecVisible=new Vector<Comparable>();
                      Vector<Comparable> VecInvisible=new Vector<Comparable>();
                      int iStt=Qry.getI("aic_stammtyp");
                      VecVisible.addElement(Qry.getS("bezeichnung"));
                      VecVisible.addElement(Qry.getS("kennung"));
                      VecVisible.addElement(g.TabStammtypen.getBezeichnungS(Qry.getI("sta_aic_stammtyp")));
                      VecVisible.addElement(Qry.getD("log"));
                      VecVisible.addElement(addBits(Qry.getI("Sttbits")));
                      VecVisible.addElement(iStt);
                      VecVisible.addElement(Qry.getS("WebIcon"));
                      VecVisible.addElement(Qry.getS("WebIconOffen"));
                      VecVisible.addElement(Qry.getS("WebFarbe"));
                      VecVisible.addElement(Qry.getI("WebAnzahl"));

                      VecInvisible.addElement(iStt);
                      VecInvisible.addElement(Qry.getInt("Sttbits"));
                      VecInvisible.addElement(Qry.getS("Memo"));
                      VecInvisible.addElement(Qry.getI("sta_aic_stammtyp"));
                      VecInvisible.addElement(Qry.getI("ANR_Eig"));
                      VecInvisible.addElement(Qry.getI("Aufgabe"));
                      VecInvisible.addElement(Qry.getI("aic_status"));

                      JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodeRoot);
                      Nod.setUserData(VecInvisible);
                      Image Gif = g.getSttGif(iStt);
                      Nod.setStyle(g.setColor(g.getStyle(Gif),Qry.getB("Tod") ? g.ColTod:g.ColStandard));
                      if (iAktAIC==iStt)
                        Out.selectNode(Nod,null);
              }
              Qry.close();
          }
          Qry.free();
          Out.folderChanged(NodeRoot);
  }

  private void fillOutZ()
    {
      JCOutlinerFolderNode NodP=(JCOutlinerFolderNode)OutZ.getRootNode();
      NodP.removeChildren();
      if (iAktAIC<=0)
        return;
      Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from Stammtyp_zuordnung where aic_Stammtyp="+iAktAIC+" order by reihenfolge",true);
      for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
      {
        int iAic=Tab.getI("aic_eigenschaft");
        int iPos=g.TabEigenschaften.getPos("aic",iAic);
        Vector<Object> VecVisible=new Vector<Object>();
        VecVisible.addElement(g.TabEigenschaften.getS(iPos,"Bezeichnung"));
        VecVisible.addElement(Tab.getInhalt("reihenfolge"));
        String sDt=g.TabEigenschaften.getS(iPos,"Datentyp");
        int iEigStt=g.TabEigenschaften.getI(iPos,"aic_stammtyp");
        int iPosB=g.getPosBegriff("Datentyp",sDt);
        VecVisible.addElement(iPosB<0 ? sDt:g.getBegBez3(iPosB));
        JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodP);
        Nod.setUserData(Tab.getInhalt("aic_eigenschaft"));
        Image Gif = iPosB<0?null:g.LoadImage(g.TabBegriffe.getS(iPosB,"BildFile"));
        JCOutlinerNodeStyle Sty=g.getStyle(Gif);
        if (sDt.equals("Bezeichnung") || sDt.equals("Firma") || sDt.equals("Gruppe") && iEigStt==g.iSttANR)
          Sty.setForeground(!sDt.equals("Gruppe") || iAic==CboANR.getSelectedAIC() ? Color.GREEN.darker():Color.RED);
        else if (sDt.equals("Eintritt") || sDt.equals("Austritt") || sDt.equals("EinAustritt"))
          Sty.setForeground(Color.YELLOW.darker());
          //Sty.setForeground(zugeordnet(iAic) ? Color.GREEN.darker():Color.RED);
        Nod.setStyle(Sty);
      }
      OutZ.folderChanged(NodP);
    }

}
