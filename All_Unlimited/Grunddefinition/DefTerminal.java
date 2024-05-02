package All_Unlimited.Grunddefinition;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;

import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Hauptmaske.Zeitraum;

import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;

import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.SpinBox;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class DefTerminal extends Formular
{
  private Global g;
  private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
  private Parameter Para;
  private Tabellenspeicher TabTerminals=null;
  private Zahl TxtAnzKST = new Zahl(6);
  private Zahl TxtAnzTaet = new Zahl(3);
  private Zahl TxtAnzAuft = new Zahl(15);
  private JCheckBox CbxBDENull = new JCheckBox("");
  private JCheckBox CbxKarteNull = new JCheckBox("");
  private JCheckBox CbxDatei = new JCheckBox("");
  private JCheckBox CbxMecs = new JCheckBox("");
  private JRadioButton RadKein = new JRadioButton("");
  private JRadioButton RadHitag = new JRadioButton("");
  private JRadioButton RadMiro = new JRadioButton("");
  private JRadioButton RadLegic = new JRadioButton("");
  private JRadioButton RadRo = new JRadioButton("");
  private JRadioButton RadMifare = new JRadioButton("");
  private JRadioButton RadMifare2 = new JRadioButton("");
  private JRadioButton RadMifareD = new JRadioButton("");
  private JRadioButton RadNova = new JRadioButton("");
  private JRadioButton RadSymbol = new JRadioButton("");
  private JRadioButton RadHitagHex = new JRadioButton("");
  private JRadioButton RadMifare8 = new JRadioButton("");
  private JRadioButton RadSonst = new JRadioButton("");
  private SpinBox EdtKL = new SpinBox();
  private JLabel LblKL= new JLabel();
  private JButton BtnRefresh=null;
  private JButton BtnRefresh2=null;
  //private JButton BtnSave=null;
  private JDialog Dlg=null;

  private static DefTerminal Self;

        public static DefTerminal get(Global rg)
        {
          if (Self==null)
            Self=new DefTerminal(rg);
          else
            Self.show();
          return Self;
        }

        public static void free()
        {
           if (Self != null)
           {
             Self.g.winInfo("DefTerminal.free");
             Self.thisFrame.dispose();
             Self = null;
           }
        }

  private DefTerminal(Global rg)
  {
    super("DefTerminal", null, rg);
    g=rg;
    g.winInfo("DefTerminal.create");
    Build();
    Refresh();
    Refresh_Parameter();
    show();
  }

  private void Build()
  {
    getFrei("Outliner").add(Out);
    Out.setAllowMultipleSelections(true);
                //JPanel Pnl2 = getFrei("Eingabe");
                //Pnl2.setLayout(new BorderLayout(4,4));
                Dlg=new JDialog((javax.swing.JFrame)thisFrame,"Parameter",true);
                JPanel Pnl3 = new JPanel(new GridLayout(0,1,2,2));
                g.addLabel4(Pnl3,"Anz. KST");
                g.addLabel4(Pnl3,"Anz. Taetigkeit");
                g.addLabel4(Pnl3,"Anz. Auftrag");
                g.addLabel4(Pnl3,"Karte ohne fuehrende Nullen");
                g.addLabel4(Pnl3,"BDE mit fuehrende Nullen");
                g.addLabel4(Pnl3,"In Datei sichern");
                g.addLabel4(Pnl3,"Mecs-Terminal");
                g.addLabel4(Pnl3,"<kein>-Leser");
                g.addLabel4(Pnl3,"Hitag-Leser");
                g.addLabel4(Pnl3,"Miro-Leser");
                g.addLabel4(Pnl3,"Legic-Leser");
                g.addLabel4(Pnl3,"Ro-Leser");
                g.addLabel4(Pnl3,"Symbol-Leser");
                g.addLabel4(Pnl3,"Mifare-Leser");
                g.addLabel4(Pnl3,"Mifare2-Leser");
                g.addLabel4(Pnl3,"Mifare Desfire-Leser");
                g.addLabel4(Pnl3,"Novachron");
                g.addLabel4(Pnl3,"HitagHex-Leser");
                g.addLabel4(Pnl3,"Mifare8-Leser");
                g.addLabel4(Pnl3,"Sonst-Leser");
                g.addLabel4(Pnl3,"Kartenlaenge");
                Dlg.getContentPane().add("West",Pnl3);
                Pnl3 = new JPanel(new GridLayout(0,1,2,2));
                Pnl3.add(TxtAnzKST);
                Pnl3.add(TxtAnzTaet);
                Pnl3.add(TxtAnzAuft);
                Pnl3.add(CbxKarteNull);
                Pnl3.add(CbxBDENull);
                Pnl3.add(CbxDatei);
                Pnl3.add(CbxMecs);
                ButtonGroup RadGroup=new ButtonGroup();
                RadGroup.add(RadKein);
                RadGroup.add(RadHitag);
                RadGroup.add(RadMiro);
                RadGroup.add(RadLegic);
                RadGroup.add(RadRo);
                RadGroup.add(RadSymbol);
                RadGroup.add(RadMifare);
                RadGroup.add(RadMifare2);
                RadGroup.add(RadMifareD);
                RadGroup.add(RadNova);
                RadGroup.add(RadHitagHex);
                RadGroup.add(RadMifare8);
                RadGroup.add(RadSonst);
                Pnl3.add(RadKein);
                Pnl3.add(RadHitag);
                Pnl3.add(RadMiro);
                Pnl3.add(RadLegic);
                Pnl3.add(RadRo);
                Pnl3.add(RadSymbol);
                Pnl3.add(RadMifare);
                Pnl3.add(RadMifare2);
                Pnl3.add(RadMifareD);
                Pnl3.add(RadNova);
                Pnl3.add(RadHitagHex);
                Pnl3.add(RadMifare8);
                Pnl3.add(RadSonst);
                Pnl3.add(LblKL);
                Dlg.getContentPane().add("Center",Pnl3);
                JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,3,2));
                BtnRefresh2=g.getButton("Refresh2");
                JButton BtnSave=g.getButton("Speichern");
                JButton BtnDel=g.getButton("Loeschen");
                JButton BtnBeenden2=g.getButton("Beenden");
                EdtKL.setMinimum(1);
                EdtKL.setMaximum(31);
                PnlS.add(EdtKL);
                PnlS.add(BtnRefresh2);
                PnlS.add(BtnDel);
                PnlS.add(BtnSave);
                PnlS.add(BtnBeenden2);
                Dlg.getContentPane().add("South",PnlS);

      ActionListener AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
            g.progInfo("Command="+s);
            if (s.equals("Parameter"))
            {
                Refresh_Parameter();
                Dlg.pack();
                Static.centerComponent(Dlg,thisFrame);
                Dlg.setVisible(true);
            }
            else if (s.equals("Para_Save"))
              Para.setMParameter("Werte","",showBits(getBits()),TxtAnzKST.intValue(),TxtAnzTaet.intValue(),TxtAnzAuft.intValue(),g.getMandant());
            else if (s.equals("Para_Del"))
            {
              Para.delMParameter("Werte", true);
              Refresh_Parameter();
            }
            else if (s.equals("Para_Beenden"))
              Dlg.setVisible(false);
            else if (s.equals("DelAll"))
              DelAll();
            else if (s.equals("Refresh"))
              Refresh();
            else if (s.equals("Para_Refresh"))
              Refresh_Parameter();
            else if (s.equals("ZRplus") || s.equals("ZRminus") || s.equals("Jetzt"))
              changeZR(s);
            else if (s.equals("Zeitraum"))
              Zeitraum.get(g).show();
            else if (s.equals("StammUpload"))
              ShowDaten("StammUpload");
            else if (s.equals("Zutritt"))
              ShowDaten("ZeitZutritt");
            else if (s.equals("Feiertag"))
              ShowDaten("Feiertag");
            else if (s.startsWith("Import"))
              ShowDaten(s);
            else if (s.equals("PHP"))
              ShowDaten("PHP");
            else if (s.equals("Export"))
              ExportTerm();
            else if (s.equals("Beenden"))
              hide();
            else
              Static.printError("DefTermional.ActionListener: Knopf "+s+" ohne Funktion!");
          }
        };
        g.BtnAdd(getButton("Parameter"),"Parameter",AL);
        g.BtnAdd(BtnRefresh2,"Para_Refresh",AL);
        g.BtnAdd(BtnSave,"Para_Save",AL);
        g.BtnAdd(BtnDel,"Para_Del",AL);
        g.BtnAdd(BtnBeenden2,"Para_Beenden",AL);

        JButton BtnDelAll=getButton("Loeschen3");
        if (BtnDelAll != null)
        {
          BtnDelAll.setEnabled(g.Def());
          g.BtnAdd(BtnDelAll,"DelAll",AL);
        }
        BtnRefresh=g.BtnAdd(getButton("Refresh"),"Refresh",AL);
        g.BtnAdd(getButton("Zeitraum"),"Zeitraum",AL);
        g.BtnAdd(getButton("Tb_ZRplus"),"ZRplus",AL);
        g.BtnAdd(getButton("Jetzt"),"Jetzt",AL);
        g.BtnAdd(getButton("Tb_ZRminus"),"ZRminus",AL);
        g.BtnAdd(getButton("StammUpload"),"StammUpload",AL);
        g.BtnAdd(getButton("Zutritt"),"Zutritt",AL);
        g.BtnAdd(getButton("Feiertag"),"Feiertag",AL);
        g.BtnAdd(getButton("Importe"),"Import",AL);
        g.BtnAdd(getButton("Importe2"),"Import2",AL);
        g.BtnAdd(getButton("PHP"),"PHP",AL);
        g.BtnAdd(getButton("Beenden"),"Beenden",AL);
        g.BtnAdd(getButton("ExportTerm"),"Export",AL);
  }

   private void changeZR(String sArt)
   {
     boolean bJetzt=sArt.equals("Jetzt");
     if (bJetzt)
       g.setAktDate(true);
     else
       g.changeZR(sArt);//bPlus ? "ZRplus":"ZRminus");
     Zeitraum.PeriodeToVec(g,g.getZA(0),bJetzt);
     showZR();
   }

   private void showZR()
   {
     setTitle(sFormularBezeichnung + ": " + g.getVonBis("dd.MM.yyyy", true));
   }

   private void Refresh()
   {
     /*g.bISQL=true;
     g.exec("create index IND_DBC_ZSS on Zwischenspeicher (Kennung,Terminal,Zwischentext asc)");
     g.bISQL=false;*/
     showZR();
     if (BtnRefresh != null)
       BtnRefresh.setEnabled(false);
     thisFrame.repaint();
     new Thread(new Runnable()
     {
       public void run()
       {
         long lClock=Static.get_ms();
         int iEigIP = g.TabEigenschaften.getAic("IP ADDRESS");
         int iSttT = g.TabStammtypen.getAic("TERMINAL");
         //g.progInfo("iSttT="+iSttT+", iEigIP="+iEigIP);
         TabTerminals = new Tabellenspeicher(g, "select bezeichnung,spalte_stringx IP,aic_mandant from stammview p2 join poolview2 p on p2.aic_stamm = p.aic_stamm" +
             " join daten_stringx d on p.aic_daten = d.aic_daten where p2.aic_stammtyp=" + iSttT + " and aic_eigenschaft=" + iEigIP + g.getReadMandanten()+" order by IP", true);
         boolean bMandant=TabTerminals.posInhalt("IP", "PHP");
         Vector<Integer> VecZ=SQL.getVector("select min(aic_zwischenspeicher),terminal"+(bMandant ?",aic_mandant":"")+" from zwischenspeicher z where kennung in ('Import','StammUpload')"+ g.getReadMandanten(false,"z")
                                            +(g.Def()?"":" and terminal"+Static.SQL_in(TabTerminals.getVecSpalte("IP")))+" group by terminal"+(bMandant ?",aic_mandant":""),g);

         Tabellenspeicher Tab = new Tabellenspeicher(g, "select distinct null Name,terminal" +
             ",(select max(gueltig) from zwischenspeicher where z.terminal=terminal and kennung='Status') StatusZeit" +
             ",(select max(zwischentext) from zwischenspeicher where z.terminal=terminal and kennung='Status') Status" +
             ",(select max(gueltig) from zwischenspeicher where z.terminal=terminal"+(bMandant ? " and z.aic_mandant=aic_mandant":"")+" and kennung='StammUpload') StammUpload" +
             ",(select max(gueltig) from zwischenspeicher where z.terminal=terminal"+(bMandant ? " and z.aic_mandant=aic_mandant":"")+" and kennung='ZeitZutritt') ZeitZutritt" +
             ",(select max(gueltig) from zwischenspeicher where z.terminal=terminal"+(bMandant ? " and z.aic_mandant=aic_mandant":"")+" and kennung='Import') Import" +
             ",aic_Mandant Mandant from zwischenspeicher z where"+g.in("aic_zwischenspeicher",VecZ),true);
             //" from zwischenspeicher z where kennung in ('Import','StammUpload')"+ g.getReadMandanten(false,"z"), true);//terminal" + Static.SQL_in(TabTerminals.getVecSpalte("IP")), true);
         for (Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
         {
           int iPosT=TabTerminals.getPos("IP", Tab.getS("Terminal"));
           if (iPosT>=0)
           {
             int iPosM=bMandant ? TabTerminals.getNextPos(iPosT-1,"aic_mandant",Tab.getI("Mandant")):iPosT;
             if (iPosM>=iPosT)
               Tab.setInhalt("Name", TabTerminals.getS(iPosM,"Bezeichnung"));
           }
           Tab.setInhalt("Mandant", g.TabMandanten.getBezeichnungS(Tab.getI("Mandant")));
         }
         g.setTitel(Tab, new String[] {"Name","Terminal","StatusZeit","Status","StammUpload","ZeitZutritt","Mandant"});
         /*Tab.setTitel("Name", g.getBegriffS("Show", "Name"));
         Tab.setTitel("Terminal", g.getBegriffS("Show", "Terminal"));
         Tab.setTitel("StatusZeit", g.getBegriffS("Show", "StatusZeit"));
         Tab.setTitel("Status", g.getBegriffS("Show", "Status"));
         Tab.setTitel("StammUpload", g.getBegriffS("Show", "StammUpload"));
         Tab.setTitel("ZeitZutritt", g.getBegriffS("Show", "ZeitZutritt"));*/
         if (!g.MySQL())
           Tab.setTitel("Import", g.getBegriffS("Show", "Import"));
         Tab.showGrid(Out);
         if (BtnRefresh != null)
           BtnRefresh.setEnabled(true);
         g.clockInfo("Refresh Terminal-Info", lClock);
       }
     }).start();
     if (BtnRefresh2 == null)
           Refresh_Parameter();
   }

   private int getBits()
   {
     int iBits=(CbxBDENull.isSelected()? TabTerminal.BDE00:0)+(CbxDatei.isSelected()? TabTerminal.DATEI:0)+
                                            (CbxKarteNull.isSelected()? TabTerminal.KARTE0:0)+(CbxMecs.isSelected()?TabTerminal.MECS:0)+
                                            (RadMiro.isSelected()?TabTerminal.MIRO:RadLegic.isSelected()?TabTerminal.LEGIC:RadRo.isSelected()?TabTerminal.RO:RadSymbol.isSelected()?TabTerminal.SYMBOL:
                                             RadMifare.isSelected()?TabTerminal.MIFARE:RadMifare2.isSelected()?TabTerminal.MIFARE2:RadMifareD.isSelected()?TabTerminal.MIFARED:
                                             RadNova.isSelected()?TabTerminal.NOVA:RadHitagHex.isSelected()?TabTerminal.HITAGHEX:RadMifare8.isSelected()?TabTerminal.MIFARE8:
                                             RadSonst.isSelected()?TabTerminal.SONST+EdtKL.getIntValue()*TabTerminal.KL_pos:0);
     return iBits;
   }

   private int showBits(int iBits)
   {
     /*int iBits=(CbxBDENull.isSelected()? TabTerminal.BDE00:0)+(CbxDatei.isSelected()? TabTerminal.DATEI:0)+
                                            (CbxKarteNull.isSelected()? TabTerminal.KARTE0:0)+(CbxMecs.isSelected()?TabTerminal.MECS:0)+
                                            (RadMiro.isSelected()?TabTerminal.MIRO:RadLegic.isSelected()?TabTerminal.LEGIC:RadRo.isSelected()?TabTerminal.RO:RadSymbol.isSelected()?TabTerminal.SYMBOL:
                                             RadMifare.isSelected()?TabTerminal.MIFARE:RadMifare2.isSelected()?TabTerminal.MIFARE2:RadMifareD.isSelected()?TabTerminal.MIFARED:
                                             RadNova.isSelected()?TabTerminal.NOVA:RadHitagHex.isSelected()?TabTerminal.HITAGHEX:RadMifare8.isSelected()?TabTerminal.MIFARE8:
                                             RadSonst.isSelected()?TabTerminal.SONST+EdtKL.getIntValue()*TabTerminal.KL_pos:0);*/
     EdtKL.setIntValue(TabTerminal.Kartenlaenge(false,iBits));
     String s=""+EdtKL.getIntValue();
       if (RadHitag.isSelected() || RadLegic.isSelected() || RadMifare2.isSelected())
         s+=" - 2";
       else if (RadMiro.isSelected())
         s+=" Miro";
       else if (RadHitagHex.isSelected())
         s+=" Hex";
       LblKL.setText(s);
       return iBits;
   }

   private void Refresh_Parameter()
   {
     if (Para==null)
       Para=new Parameter(g,"Terminal");
     Para.getMParameter("Werte",true);
     if(Para.bGefunden)
     {
       int int1=Para.int1;
       TxtAnzKST.setValue(Para.int2);
       TxtAnzTaet.setValue(Para.int3);
       TxtAnzAuft.setValue(Para.int4);
       CbxBDENull.setSelected((int1 & TabTerminal.BDE00) > 0);
       CbxDatei.setSelected((int1 & TabTerminal.DATEI) > 0);
       CbxKarteNull.setSelected((int1 & TabTerminal.KARTE0) > 0);
       int iKarte=int1 & TabTerminal.KARTE;
       RadHitag.setSelected(iKarte == TabTerminal.HITAG);
       RadMiro.setSelected(iKarte == TabTerminal.MIRO);
       RadLegic.setSelected(iKarte == TabTerminal.LEGIC);
       RadRo.setSelected(iKarte == TabTerminal.RO);
       RadSymbol.setSelected(iKarte == TabTerminal.SYMBOL);
       RadMifare.setSelected(iKarte == TabTerminal.MIFARE);
       RadMifare2.setSelected(iKarte == TabTerminal.MIFARE2);
       RadMifareD.setSelected(iKarte == TabTerminal.MIFARED);
       RadNova.setSelected(iKarte == TabTerminal.NOVA);
       RadHitagHex.setSelected(iKarte == TabTerminal.HITAGHEX);
       RadMifare8.setSelected(iKarte == TabTerminal.MIFARE8);
       RadSonst.setSelected(iKarte == TabTerminal.SONST);
       CbxMecs.setSelected((int1 & TabTerminal.MECS) > 0);
       if (RadSonst.isSelected())
         EdtKL.setIntValue(int1/TabTerminal.KL_pos);
       showBits(int1);
     }
     else
     {
       g.progInfo("Parameter nicht gefunden");
       TxtAnzKST.setValue(6);
       TxtAnzTaet.setValue(3);
       TxtAnzAuft.setValue(15);
       RadKein.setSelected(true);
       CbxBDENull.setSelected(false);
       CbxDatei.setSelected(true);
       CbxKarteNull.setSelected(false);
       CbxMecs.setSelected(false);
     }
     //if (BtnSave != null)
     //  BtnSave.setEnabled(g.isLogMandant());
   }

   private void ExportTerm()
   {
	   JCOutlinerNode Nod=Out.getSelectedNode();
	   if (Nod!=null && Nod.getLevel()==1)
	   {
		   String sTerm=Sort.gets(Nod.getLabel(),1);
		   Tabellenspeicher Tab=new Tabellenspeicher(g, "select zwischentext from zwischenspeicher where kennung in('StammUpload','ZeitZutritt','Feiertag')"+
				   " and terminal='"+sTerm+"'"+" order by aic_zwischenspeicher", true);
		   if (Tab.Export((char)0))
		   /*Tab.writeFile((char)0, Static.getTemp()+"p74.cfg");
		   Tab=new Tabellenspeicher(g, "select zwischentext from zwischenspeicher where kennung='StammUpload'"+
				   " and terminal='"+sTerm+"'"+" order by aic_zwischenspeicher", true);
		   Tab.writeFile((char)0, Static.getTemp()+"p76.cfg");*/
			   new Message(Message.INFORMATION_MESSAGE,thisJFrame(),g).showDialog("Export_fertig");
	   }
   }

   private void ShowDaten(String sKennung)
   {
     //String sTerminal=null;
     JCOutlinerNode Nod[]=Out.getSelectedNodes();
     if (sKennung.equals("PHP"))
       showPHP(sKennung);
     else if (Nod.length>0 && Nod[0].getLevel()==1)
     {
       //sTerminal=(String)((java.util.Vector)Nod.getLabel()).elementAt(1);
       Vector<String> Vec=new Vector<String>();
       for(int i=0;i<Nod.length;i++)
         Vec.addElement(Sort.gets(Nod[i].getLabel(),1));
       boolean bImport=sKennung.startsWith("Import");
       if (bImport)
         showImport(Vec,sKennung.equals("Import"));
       else
       {
         Refresh_Parameter();
         boolean bSU=sKennung.equals("StammUpload");
         Tabellenspeicher Tab=new Tabellenspeicher(g,"select terminal,gueltig"+(bSU?",null Person,null Saldo,null Urlaub":"")+",zwischentext," + g.AU_BezeichnungF("Mandant", "zwischenspeicher", 1) + " Mandant"+
             " from zwischenspeicher where kennung='" + sKennung +"' and" + g.in("terminal", Vec) +g.getReadMandanten(false,"zwischenspeicher")+" order by aic_zwischenspeicher", true);
         Tabellenspeicher TabStamm=getTabStamm();
         //TabStamm.showGrid("TabStamm");
         if (bSU)
         for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
         {
           int iPos=10+TabTerminal.Kartenlaenge(Tab.getS("Terminal").equals("Symbol"),getBits());
           String s=Tab.getS("zwischentext");
           if (s.length()>iPos)
           {
             String sKartenNr = s.substring(10, iPos);
             boolean bKarteNullen = !CbxKarteNull.isSelected();//Para.bGefunden?(Para.int1&TabTerminal.KARTE0)==0:true;
             if (!bKarteNullen)
            	 sKartenNr=Static.CutNull(sKartenNr);
             else if (VN(Tab.getS("Terminal")))
               sKartenNr = sKartenNr.substring(2);
             //g.fixtestInfo("sKartenNr="+sKartenNr);
             try
             {
               if (TabStamm.posInhalt("Karte", sKartenNr))
                 Tab.setInhalt("Person", TabStamm.getS("Bezeichnung"));

               Tab.setInhalt("Saldo", new Zahl1(s.substring(iPos + 18, iPos + 26).trim().replace(',','.'),null));
               Tab.setInhalt("Urlaub", new Zahl1(s.substring(iPos + 28, iPos + 34).trim().replace(',','.'),null));
             }
             catch (Exception e) {};
           }
         }
         Tab.showGrid(sKennung + " " + Vec);
       }
     }
   }

   private void DelAll()
   {
     JCOutlinerNode Nod[]=Out.getSelectedNodes();
     if (Nod.length>0 && Nod[0].getLevel()==1)
     {
       Vector<String> Vec=new Vector<String>();
       for(int i=0;i<Nod.length;i++)
         Vec.addElement(Sort.gets(Nod[i].getLabel(),1));
       if (new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g,0).showDialog("Del4", new String[]{""+Vec}) == Message.YES_OPTION)
       {
         g.fixInfo("Terminals löschen:" + Vec);
         int iAnz=g.exec("Delete from zwischenspeicher where"+g.in("terminal",Vec));
         g.fixInfo(iAnz+" Zeilen aus Zwischenspeicher entfernt");
         Refresh();
       }
     }
   }

   private Tabellenspeicher getTabStamm()
   {
     int iRolleMA=g.TabRollen.getAic("MITARBEITER");
     int iEigKarte=g.TabEigenschaften.getAic("KARTENNUMMER");
     int iEigKarte2=g.TabEigenschaften.getAic("ZE_KARTENNUMMER_2");
     Tabellenspeicher TabStamm=new Tabellenspeicher(g, "select bezeichnung,spalte_stringx Karte from stammview v join poolview p on v.aic_stamm = p.aic_stamm"+
                                                    " join daten_stringx d on p.aic_daten = d.aic_daten where aic_Rolle="+iRolleMA+" and aic_eigenschaft in ("+iEigKarte+","+iEigKarte2+")"+
                                                    g.getReadMandanten(false,"v"),true);
     //TabStamm.showGrid();
     return TabStamm;
   }

   /*private int getKL(String sTerminal)
   {
     boolean bSymbol = RadSymbol.isSelected() || sTerminal.equals("Symbol");
     int iL = bSymbol ? 16 : RadMifare.isSelected() || RadMifare2.isSelected() || RadHitagHex.isSelected() ? 10 : RadMifareD.isSelected() ? 17 :
           RadNova.isSelected() ? 14 : RadRo.isSelected() ? 12 : 8;
     return iL;
   }*/

   private boolean VN(String sTerminal)
   {
       return !sTerminal.equals("Symbol") && (RadHitag.isSelected() || RadLegic.isSelected() || RadMifare2.isSelected());
   }

   private void showPHP(String sKennung)
   {
     Tabellenspeicher TabStamm=getTabStamm();
     Tabellenspeicher TabC=new Tabellenspeicher(g,"select ip_adresse,kennung from computer order by aic_computer desc",true);
     //TabC.showGrid("TabC");
     Tabellenspeicher Tab=new Tabellenspeicher(g, "select gueltig,zwischentext,null Name,terminal Computer,aic_mandant Mandant"+
         ",(case when aic_protokoll=17 then null else (select timestamp from protokoll where aic_protokoll=z.aic_protokoll) end) erfasst"+
         ",(case when pro_aic_protokoll is null then null else (select timestamp from protokoll where aic_protokoll=z.pro_aic_protokoll) end) abgeholt"+
         " from zwischenspeicher z where kennung='"+sKennung+"' and gueltig>="+g.von()+" and gueltig<"+g.bis()+g.getReadMandanten(false,"z")+" order by aic_zwischenspeicher", true);
     for (int i=0;i<Tab.size();i++)
     {
       String s=Tab.getS(i,"zwischentext");
       String sKarte=s.substring(0,s.indexOf(","));
       if (TabStamm.posInhalt("Karte", sKarte))
           Tab.setInhalt(i,"Name", TabStamm.getS("Bezeichnung"));
       int iPosM=Tab.getI(i,"Mandant")>0 ? g.TabMandanten.getPos("aic_mandant",Tab.getI(i,"Mandant")):-1;
       if (iPosM>=0)
         Tab.setInhalt(i,"Mandant",g.TabMandanten.getS(iPosM,"Bezeichnung"));
       if (TabC.posInhalt("ip_adresse", Tab.getS(i,"Computer")))
         Tab.setInhalt(i, "Computer", TabC.getS("kennung"));
     }
     Tab.showGrid("PHP");
   }

   private void showImport(final Vector<String> VecT,final boolean bZR)
   {
     Refresh_Parameter();
     Tabellenspeicher TabStamm=getTabStamm();
     Tabellenspeicher Tab=new Tabellenspeicher(g, "select aic_zwischenspeicher,aic_mandant,gueltig,zwischentext,pro_aic_protokoll,Terminal"+
                                               ",(select kennung from protokoll"+g.join("logging","protokoll")+g.join("computer","logging")+" where aic_protokoll=zwischenspeicher.aic_protokoll) Computer"+
                                               ",(select timestamp from protokoll where aic_protokoll=zwischenspeicher.aic_protokoll) erstellt"+
                                               ",(select timestamp from protokoll where aic_protokoll=zwischenspeicher.pro_aic_protokoll) verarbeitet"+
                                               " from zwischenspeicher where kennung='Import' and"+g.in("terminal",VecT)+g.getReadMandanten(false,"zwischenspeicher")+" and "+
                                               (bZR ? "gueltig>="+g.von()+" and gueltig<"+g.bis():"pro_aic_protokoll is null")+" order by aic_zwischenspeicher desc", true);
     final Tabellenspeicher Tab2=new Tabellenspeicher(g,new String[]{"Terminal","Zeit","Person","Karte","Art","erstellt","Computer","verarbeitet","Sub","Fehler","KST","Tat","Auftrag","Memo","Aic","Mandant","Farbe"});
     Tab2.setTitel("Terminal",g.getBegriffS("Show","Terminal"));
     Tab2.setTitel("Zeit",g.getBegriffS("Show","Zeit"));
     Tab2.setTitel("Person",g.getBegriffS("Show","Person"));
     Tab2.setTitel("Karte",g.getBegriffS("Show","Kartennummer"));
     Tab2.setTitel("Art",g.getBegriffS("Show","Art"));
     Tab2.setTitel("Sub",g.getBegriffS("Show","Sub-Leser"));
     Tab2.setTitel("Fehler",g.getBegriffS("Show","Fehler"));
     Tab2.setTitel("Computer",g.getBegriffS("Show","Computer"));
     Tab2.setTitel("KST",g.getBegriffS("Show","KST"));
     Tab2.setTitel("Tat",g.getBegriffS("Show","Tat"));
     Tab2.setTitel("Auftrag",g.getBegriffS("Show","Auftrag"));
     Tab2.setTitel("Memo",g.getBegriffS("Show","Memo"));
     Tab2.setTitel("Aic",g.getBegriffS("Show","Aic"));
     Tab2.setTitel("Mandant",g.getBegriffS("Show","Mandant"));
     Tab2.setTitel("erstellt",g.getBegriffS("Show","erstellt"));
     Tab2.setTitel("verarbeitet",g.getBegriffS("Show","verarbeitet"));
     int iFehler=0;
     for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
     {
       Tab2.newLine();
       String sSatz=Tab.getS("zwischentext");
       String sMemo=sSatz.substring(sSatz.indexOf('\r')+1);
       //sSatz=sSatz.substring(0,sSatz.indexOf('\r'))+"\r";
       String sTerminal=Tab.getS("Terminal");
       int iPosT= TabTerminals.getPos("IP", sTerminal);
       Tab2.setInhalt("Terminal",iPosT<0 ? sTerminal:TabTerminals.getS(iPosT,"Bezeichnung"));
       Tab2.setInhalt("Memo",sMemo);
       Tab2.setInhalt("Zeit",Tab.getTimestamp("gueltig"));
       String sArt=sSatz.substring(8,10);
       Tab2.setInhalt("Sub",sSatz.substring(6, 8));
       Tab2.setInhalt("Art",sArt);
       Tab2.setInhalt("Computer", Tab.getS("Computer"));
       Tab2.setInhalt("erstellt", Tab.getTimestamp("erstellt"));
       Tab2.setInhalt("verarbeitet", Tab.getTimestamp("verarbeitet"));
       Tab2.setInhalt("Aic", Tab.getI("Aic_Zwischenspeicher"));
       Tab2.setInhalt("Mandant", g.TabMandanten.getBezeichnungS(Tab.getI("aic_mandant")));

       //boolean bSymbol = RadSymbol.isSelected() || sTerminal.equals("Symbol");
       //int iL = bSymbol ? 16 : RadMifare.isSelected() || RadMifare2.isSelected() || RadHitagHex.isSelected() ? 10 : RadMifareD.isSelected() ? 17 :
       //	   RadNova.isSelected() ? 14 : RadRo.isSelected() ? 12 : 8;
       int iL=TabTerminal.Kartenlaenge(sTerminal.equals("Symbol"),getBits());//getKL(sTerminal);
       String sTest=sSatz.substring(10+iL,12+iL);
       if (!sTest.equals("20"))
         iFehler++;
       /*{
         new Message(Message.WARNING_MESSAGE, null, g).showDialog("Datensatz_fehlerhaft", new String[]{});
         return;
       }*/
       else
       {
         boolean bDel=false;
         String sKartenNr = sSatz.substring(10, 10 + iL);
         if (VN(sTerminal))//(!sTerminal.equals("Symbol") && (RadHitag.isSelected() || RadLegic.isSelected() || RadMifare2.isSelected()))
           sKartenNr = sKartenNr.substring(2);
         else if (sArt.equals("PN"))
           g.testInfo("Personalnummer=" + sKartenNr);
         else if (RadHitagHex.isSelected())
           sKartenNr = Static.FillNull("" + Long.decode("#" + sKartenNr), 12);
         else if (RadMiro.isSelected())
           sKartenNr = Static.MiroCalc(sKartenNr, false);
         if (CbxKarteNull.isSelected())
           sKartenNr = Static.CutNull(sKartenNr);
         Tab2.setInhalt("Karte", sKartenNr);
         if (TabStamm.posInhalt("Karte", sKartenNr))
           Tab2.setInhalt("Person", TabStamm.getS("Bezeichnung"));
         else if (!g.Def() && Tab.getI("aic_mandant")!=g.getMandant())
           bDel=true;
         String sFehler = sSatz.substring(24 + iL, 25 + iL);
         Tab2.setInhalt("Fehler", sFehler);
         String sTaetigkeit = "";
         String sAuftrag = "";
         String sKostenstelle = "";

         int iA = sSatz.indexOf(';') + 1;
         int iE = 0;
         do
         {
           iE = sSatz.indexOf(';', iA);
           if (iE == -1)
             iE = sSatz.length() - 3;

           String sTAK = sSatz.substring(iA, iE).trim();
           if (sTAK.length() == TxtAnzTaet.intValue())
             sTaetigkeit = sTAK;
           else if (sTAK.length() == TxtAnzAuft.intValue())
             sAuftrag = sTAK;
           else if (sTAK.length() == TxtAnzKST.intValue())
             sKostenstelle = sTAK;

           iA = iE + 1;
         }
         while (iE != sSatz.length() - 3);
         Tab2.setInhalt("KSt", sKostenstelle);
         Tab2.setInhalt("Tat", sTaetigkeit);
         Tab2.setInhalt("Auftrag", sAuftrag);
         if (!sFehler.equals("0"))
           Tab2.setInhalt("Farbe", Color.red.getRGB());
         else if (Tab.isNull("pro_aic_protokoll"))
         {
           //g.testInfo("nicht abheholt:"+Tab.getTimestamp("gueltig"));
           Tab2.setInhalt("Farbe", Color.green.darker().getRGB());
         }
         if (bDel)
           Tab2.clearInhalt();
       }
     }
     if (iFehler>0)
       g.fixInfo(iFehler+" Stempelungen mit falschen Kartentyp");
     //Tab2.showGrid("Stempelungen" + " " + sTerminal);
     final JDialog FomGid = new JDialog((JFrame)thisFrame, ""+VecT, false);
     final AUOutliner Grid = new AUOutliner(new JCOutlinerFolderNode(""));
     FomGid.getContentPane().add("Center", Grid);
     JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
     ActionListener AL = new ActionListener()
     {
       public void actionPerformed(ActionEvent e)
        {
          String s= e.getActionCommand();
          if (s.equals("End"))
            FomGid.dispose();
          else if (s.equals("Del") || s.equals("Del3") || s.equals("Undo"))
          {
            JCOutlinerNode Nod[] = Grid.getSelectedNodes();
            if (Nod.length==0 || Nod[0].getLevel()==0)
              return;
            g.progInfo("Selectiert:"+Nod.length);
            Vector<Integer> Vec =new Vector<Integer>();
            for (int i=0;i<Nod.length;i++)
              Vec.addElement(Sort.geti(Nod[i].getLabel(),14));
            if (new Message(Message.YES_NO_OPTION,FomGid,g,0).showDialog(s) == Message.YES_OPTION)
            {
              if (s.equals("Del3"))
                g.exec("Delete from zwischenspeicher where aic_zwischenspeicher" + Static.SQL_in(Vec));
              else if (s.equals("Del"))
                g.exec("update zwischenspeicher set pro_aic_protokoll="+g.Protokoll("Entfernen",0)+" where pro_aic_protokoll is null and aic_zwischenspeicher" + Static.SQL_in(Vec));
              else if (s.equals("Undo"))
                g.exec("update zwischenspeicher set pro_aic_protokoll=null where aic_zwischenspeicher" + Static.SQL_in(Vec));
              FomGid.dispose();
              showImport(VecT,bZR);
            }
          }
          else if (s.equals("Export"))
            Tab2.Export();
        }
     };
     if (g.Def())
       PnlS.add(g.getButton("Loeschen4", "Del3", AL));
     if (g.SuperUser())
     {
       PnlS.add(g.getButton("Loeschen", "Del", AL));
       PnlS.add(g.getButton("Undo", "Undo", AL));
     }
     PnlS.add(g.getButton("Export", "Export", AL));
     PnlS.add(g.getButton("Beenden","End",AL));
     FomGid.getContentPane().add("South",PnlS);
     Tab2.showGrid(Grid);
     Grid.setAllowMultipleSelections(true);
     FomGid.setSize(950, 400);
     Static.centerComponent(FomGid,(JFrame)thisFrame);
     FomGid.toFront();
     FomGid.setVisible(true);
   }

}
