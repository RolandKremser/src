package All_Unlimited.Allgemein.Eingabe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Print.Drucken;
import java.util.Date;
import java.util.Vector;

import All_Unlimited.Print.DruckHS;
import All_Unlimited.Allgemein.Anzeige.Zeit;

/**
 * <p>Überschrift: Kopf- und Fußzeile</p>
 *
 * <p>Beschreibung: dient zur Auswahl und Definition der Kopf- bzw. Fußzeile</p>
 *
 * <p>Copyright: Copyright (c) 4.3.2005</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class Zeile extends JPanel
{
  /**
	 *
	 */
	private static final long serialVersionUID = -1435848679286586276L;
private Global g;
  public ComboSort Cbo;
  private JButton Btn;
  public Tabellenspeicher TabZeilen=null;
  private Tabellenspeicher TabAuswahl=null;
  private Window FrmVor;

  private JDialog FomAuswahl=null;
  private ComboSort CboAuswahl;
  private Text Txt;
  private Text TxtLinks;
  private Text TxtZentrum;
  private Text TxtRechts;
  private BildEingabe BtnBild;
  private JCheckBox CbxLinie;
  private ComboSort CboAusrichtung;
  private Schrift CboSchrift;
  private Zahl niHoehe=new Zahl(10);
  //private static ComboSort CboStamm;
  private int iStamm=0;
  /*private static JRadioButton RadKopf;
  private static JRadioButton RadFuss;
  private static JRadioButton RadBeide;*/
  private boolean bKopf=false;
  private boolean bFuss=false;
  //private static boolean bBeide=true;
  private JButton BtnDatum;
  private JButton BtnZeit;
  private JButton BtnAktSeite;
  private JButton BtnSeitenanzahl;
  private JButton BtnBenutzer;
  private JButton BtnFirma;

  private JButton BtnNeu;
  private JButton BtnDel;
  private JButton BtnSave;
  private JButton BtnTest=null;
  private JButton BtnOk;
  private JButton BtnAbbruch;
  private int iAktiv=0;
  private static int iAnz=0;
  private int iMom;
//  private Zeile z;
  private JLabel LblRechts=new JLabel("",SwingConstants.RIGHT);
  private JLabel LblMitte=new JLabel("",SwingConstants.CENTER);
  private JLabel LblLinks=new JLabel("",SwingConstants.LEFT);
  private JPanel PnlTest=null;
  private static Vector<JDialog> VecFomAuswahl=null;

  public Zeile(Global rg, Window rFrmVor)
  {
          g = rg;
          //g.testInfo("Zeile.create");
          FrmVor=rFrmVor;
          //FomThis = this;
          Build();
  }

  public static void free(Global g)
  {
	  if (VecFomAuswahl != null)
	  {
		  g.fixtestError("Zeile.free: "+VecFomAuswahl.size()+"x");
		  for (int i=0;i<VecFomAuswahl.size();i++)
			  VecFomAuswahl.elementAt(i).dispose();
		  VecFomAuswahl.clear();
	  }
//    TabZeilen=null;
//    TabAuswahl=null;
//    if (FomAuswahl!=null)
//    {
//      FomAuswahl.dispose();
//      FomAuswahl=null;
//    }
  }

//  public void setOther(Zeile rz)
//  {
//    z=rz;
//  }

  private void Build()
        {
          iAnz++;
          iMom=iAnz;
          if (TabAuswahl==null)
              TabAuswahl = new Tabellenspeicher(g,new String[] {"Nr","Cbo"});
          if (TabZeilen==null)
            Load();

          TabAuswahl.addInhalt("Nr",iMom);
          Cbo = new ComboSort(g);
          Cbo.fillCbo(TabZeilen,true);
          TabAuswahl.addInhalt("Cbo",Cbo);

                setLayout(new BorderLayout(2,2));
                add("Center",Cbo);

                //if (g.SuperUser())
                //{
                        Btn = g.getButton("...");
                        Btn.setMargin(g.inset);
                        add("East",Btn);
                        Btn.addActionListener(new ActionListener()
                        {
                                public void actionPerformed(ActionEvent e)
                                {
                                  ComboSort Cbo1=TabAuswahl.posInhalt("Nr",iMom)?(ComboSort)TabAuswahl.getInhalt("Cbo"):null;
                                  if(FomAuswahl==null)
                                    createFomAuswahl();
                                  CboAuswahl.setSelectedAIC(Cbo1.getSelectedAIC());
                                  fill();
                                  FomAuswahl.setVisible(true);
                                }
                        });
                //}
        }

        private void createFomAuswahl()
	{
          final Formular Fom=new Formular("Zeile",FrmVor,g);
          FomAuswahl=(JDialog)Fom.thisFrame;
          if (VecFomAuswahl==null)
        	  VecFomAuswahl=new Vector<JDialog>();
          VecFomAuswahl.addElement(FomAuswahl);
          CboAuswahl=new ComboSort(g);
          CboAuswahl.fillCbo(TabZeilen,true);
          //CboStamm=new ComboSort(g);
          //CboStamm.fillStammTable(g.TabStammtypen.getAic("FIRMA"),true);
          //CboStamm.setFont(g.fontStandard);
          Txt=new Text("",30);
          TxtLinks=new Text("",255);
          TxtZentrum=new Text("",255);
          TxtRechts=new Text("",255);
          BtnBild=new BildEingabe(FrmVor,g);
          BtnBild.setDB(true);
//          BtnKopf.setDir(Static.DirImageDef);
//          BtnKopf.activateEvent();
          BtnBild.setScale(550,100);
          BtnBild.Delete(true);
          BtnBild.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e)
              {
            	  ((BildEingabe)e.getSource()).LadeBild();
              }
          	});
              
//          g.fixtestError("set BtnKopf auf "+Static.DirImageDef);
          CboAusrichtung=new ComboSort(g);
          CboAusrichtung.fillBegriffTable("Alignment",false);
          CboAusrichtung.setFont(Transact.fontStandard);
          CboSchrift=new Schrift(g);
          CboSchrift.getCboSchrift().setFont(Transact.fontStandard);

          Fom.getFrei("Combobox").add(CboAuswahl);
          Fom.getFrei("Textfeld").add(Txt);
          JPanel PnlKText=Fom.getFrei("Kopfzeile Text");
          PnlKText.add(TxtLinks);
          PnlKText.add(TxtZentrum);
          PnlKText.add(TxtRechts);
          PnlTest=Fom.getFrei("Test");
          if (PnlTest != null)
          {
            PnlTest.add(LblLinks);
            PnlTest.add(LblMitte);
            PnlTest.add(LblRechts);
          }
          Fom.getFrei("Kopfzeile Bild").add(BtnBild);
          CbxLinie=Fom.getCheckbox("Linie");
          Fom.getFrei("Kopf Ausrichtung").add(CboAusrichtung);
          Fom.getFrei("SchriftAllgemein").add(CboSchrift);
          Fom.getFrei("Kopfhoehe").add(niHoehe);
          /*CboStamm.setVisible(false);
          Fom.getFrei("Stamm").add(CboStamm);
          RadKopf=Fom.getRadiobutton("Kopfzeile");
          RadFuss=Fom.getRadiobutton("Fusszeile");
          RadBeide=Fom.getRadiobutton("Kopf- und Fusszeile");
          RadKopf.setVisible(false);
          RadFuss.setVisible(false);
          RadBeide.setSelected(true);
          RadBeide.setVisible(false);*/

          BtnDatum=Fom.getButton("datum");
          BtnZeit=Fom.getButton("Zeit");
          BtnAktSeite=Fom.getButton("Aktuelle Seite");
          BtnSeitenanzahl=Fom.getButton("Seitenanzahl");
          BtnBenutzer=Fom.getButton("user");
          BtnFirma=Fom.getButton("company");
          BtnNeu=Fom.getButton("Neu");
          BtnDel=Fom.getButton("Loeschen");
          BtnSave=Fom.getButton("Speichern");
          BtnTest=Fom.getButton("Test");
          BtnOk=Fom.getButton("Ok");
          ((JDialog)Fom.thisFrame).getRootPane().setDefaultButton(BtnOk);
          BtnAbbruch=Fom.getButton("Abbruch");

          BtnNeu.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ae)
            {
              CboAuswahl.setSelectedAIC(0);
              Txt.setText("");
            }
          });
          if (BtnDel != null)
          BtnDel.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ae) {
              int iAic = CboAuswahl.getSelectedAIC();
              if (iAic>0)
              {
                if(new Message(Message.YES_NO_OPTION, (JDialog)Fom.thisFrame, g, 0).showDialog("Loeschen",
                    new String[] {"[" + CboAuswahl.getSelectedBezeichnung() + "]"}) ==Message.YES_OPTION)
                  if (g.exec("delete from Zeile where aic_Zeile=" + iAic)>-2)
                  {
                    Load();
                    CboAuswahl.fillCbo(TabZeilen, true);
                    Cbo.getSelectedAIC();
                  }
                  else
                    new Message(Message.WARNING_MESSAGE,null,g).showDialog("BereitsVerwendet",new String[] {CboAuswahl.getSelectedBezeichnung()});
              }
            }
          });

          BtnSave.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ae)
            {
              if (Txt.getText().equals(""))
                new Message(Message.WARNING_MESSAGE, (JDialog)Fom.thisFrame, g,10).showDialog("BezeichnungFehlt");
              else if (Txt.getText().length()>30)
            	  new Message(Message.WARNING_MESSAGE, (JFrame)null, g, 10).showDialog("zuLang");
              else
                Save();
            }
          });

          if (BtnTest != null && PnlTest != null)
            BtnTest.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ae)
            {
              Test();
            }
          });

          BtnOk.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ae)
            {
              ComboSort Cbo1=(ComboSort)TabAuswahl.getInhalt("Cbo");
              FomAuswahl.setVisible(false);
              Cbo1.fillCbo(TabZeilen,true);
              Cbo1.setSelectedAIC(CboAuswahl.getSelectedAIC());
            }
          });
          BtnAbbruch.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent ae)
            {
              ComboSort Cbo1=(ComboSort)TabAuswahl.getInhalt("Cbo");
              FomAuswahl.setVisible(false);
              int iAic=Cbo1.getSelectedAIC();
              Cbo1.fillCbo(TabZeilen,true);
              Cbo1.setSelectedAIC(iAic);
            }
          });

          CboAuswahl.addItemListener(new ItemListener ()
          {
                  public void itemStateChanged(ItemEvent ev)
                  {
                          if(ev.getStateChange() == ItemEvent.SELECTED)
                            fill();
                  }
          });

          FocusListener FL=new FocusListener(){
            public void focusGained(FocusEvent fe)
                  {
                    Object Txt = fe.getSource();
                    if (Txt == TxtLinks)
                      iAktiv=1;
                    else if (Txt == TxtZentrum)
                      iAktiv=2;
                    else if (Txt == TxtRechts)
                      iAktiv=3;
                  }
                  public void focusLost(FocusEvent fe)
                  {}
          };
          TxtLinks.addFocusListener(FL);
          TxtZentrum.addFocusListener(FL);
          TxtRechts.addFocusListener(FL);

          ActionListener AL=new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              Object Btn = ev.getSource();
              if (Btn == BtnDatum)
                setString("\\d");
              else if (Btn == BtnSeitenanzahl)
                setString("\\s");
              else if (Btn == BtnAktSeite)
                setString("\\p");
              else if (Btn == BtnZeit)
                setString("\\t");
              else if (Btn == BtnBenutzer)
                setString("\\u");
              else if (Btn == BtnFirma)
                setString("\\c");
            }
          };
          BtnDatum.addActionListener(AL);
          BtnSeitenanzahl.addActionListener(AL);
          BtnAktSeite.addActionListener(AL);
          BtnZeit.addActionListener(AL);
          if (BtnBenutzer != null) BtnBenutzer.addActionListener(AL);
          if (BtnFirma != null) BtnFirma.addActionListener(AL);
        }

        private void setString(String sText)
        {
                if(iAktiv==1)
                        setText(TxtLinks,sText);
                else if(iAktiv==2)
                        setText(TxtZentrum,sText);
                else if(iAktiv==3)
                        setText(TxtRechts,sText);
        }

        private void setText(JTextField TxfText,String sText)
        {
                TxfText.setText(TxfText.getText()+sText);
                TxfText.requestFocus();
        }

        private int ComboToInt(ComboSort Cbo)
        {
            String s=Cbo.getSelectedKennung();
            if(s.equals("RIGHT"))
                    return(FlowLayout.RIGHT);
            else if(s.equals("CENTER"))
                    return(FlowLayout.CENTER);
            else
                    return(FlowLayout.LEFT);
        }

        private void setCombo(ComboSort Cbo, int i)
        {
            if(i==FlowLayout.RIGHT)
                    Cbo.setSelectedKennung("RIGHT");
            else if(i==FlowLayout.CENTER)
                    Cbo.setSelectedKennung("CENTER");
            else
                    Cbo.setSelectedKennung("LEFT");
	}

        private void Load()
        {
          TabZeilen=new Tabellenspeicher(g,"select Zeile.*,aic_zeile aic,Name Bezeichnung,null Kennung from Zeile"+g.getReadMandanten(null),true);
          //TabZeilen.showGrid("Zeilen");
        }

        private void fill()
        {
          if (TabZeilen.posInhalt("aic_zeile",CboAuswahl.getSelectedAIC()))
          {
            FomAuswahl.setTitle("Zeile "+TabZeilen.getS("Bezeichnung")+" | "+g.TabMandanten.getBezeichnungS(TabZeilen.getI("aic_mandant")));
            Txt.setText(TabZeilen.getS("Bezeichnung"));
            TxtLinks.setText(TabZeilen.getS("Links"));
            TxtZentrum.setText(TabZeilen.getS("Mitte"));
            TxtRechts.setText(TabZeilen.getS("Rechts"));
            Image Img=TabZeilen.isNull("Bild") ? null:SQL.getImageZ(g,TabZeilen.getI("aic"));
            BtnBild.setValue(Img,TabZeilen.getS("Bild"),"Zeile");
            niHoehe.setValue(TabZeilen.getI("Hoehe"));
            setCombo(CboAusrichtung,TabZeilen.getI("Ausrichtung"));
            CboSchrift.setSelectedAIC(TabZeilen.getI("aic_schrift"));
            CbxLinie.setSelected((TabZeilen.getI("Bits")&Drucken.cstLinie)>0);
            bKopf=(TabZeilen.getI("Bits")&Drucken.cstKopf)>0;
            bFuss=(TabZeilen.getI("Bits")&Drucken.cstFuss)>0;
            //bBeide=(TabZeilen.getI("Bits")&Drucken.cstKF)==Drucken.cstKF;
            iStamm=TabZeilen.getI("AIC_Stamm");
            if (PnlTest != null)
              Test();
          }
          EnableButtons();
        }

        private void Save()
        {
        	boolean bKF=false;
          SQL Qry=new SQL(g);
          Qry.add("Name",Txt.getText());
          Qry.add("Links",TxtLinks.getText());
          Qry.add("Mitte",TxtZentrum.getText());
          Qry.add("Rechts",TxtRechts.getText());
          String sFile=BtnBild.getFilename();
          if (Static.Leer(sFile))
          {
        	  Qry.add0("Bild",0);
        	  Qry.add0("BildKF",0);
          }
          else if (BtnBild.Modified())
          {
        	  if (sFile.startsWith("file:"))
        		  sFile=sFile.substring(sFile.charAt(5)==File.separator.charAt(0) ? 5:6);
        	  String sF=sFile.substring(sFile.lastIndexOf(File.separator)+1);
        	  g.fixtestError("Bild="+sF+", "+sFile);
        	  Qry.add("Bild",sF);
        	  bKF=true;      			  
          }
          Qry.add("Hoehe",niHoehe.intValue());
          Qry.add("Ausrichtung",ComboToInt(CboAusrichtung));
          Qry.add("Bits",(CbxLinie.isSelected()?Drucken.cstLinie:0)+(bKopf?Drucken.cstKopf:bFuss?Drucken.cstFuss:Drucken.cstKF));
          Qry.add0("AIC_Stamm",iStamm);
          Qry.add0("AIC_Schrift",CboSchrift.getSelectedAIC());
          Qry.add("AIC_Mandant",g.getMandant());
          int iAic=CboAuswahl.getSelectedAIC();
          if (iAic==0)
            iAic=Qry.insert("Zeile",true);
          else
            Qry.update("Zeile",iAic);
          Qry.free();
          if (bKF)
        	  g.UpdateMini(sFile,"update zeile set BildKF=? where aic_zeile="+iAic);
          Load();
          CboAuswahl.fillCbo(TabZeilen,true);
          CboAuswahl.setSelectedAIC(iAic);
//          int iAic2 = Cbo.getSelectedAIC();
//          z.Cbo.fillCbo(TabZeilen, true);
//          z.Cbo.setSelectedAIC(iAic2);
        }

         private void EnableButtons()
         {
           BtnNeu.setEnabled(CboAuswahl.getSelectedAIC()>0);
         }

         private void Test()
         {
           //g.progInfo("Test");
           LblLinks.setFont(CboSchrift.getSelectedFont());
           LblMitte.setFont(CboSchrift.getSelectedFont());
           LblRechts.setFont(CboSchrift.getSelectedFont());
           LblLinks.setText(Global.toHtml(checkParameters(g,TxtLinks.getText())));
           LblMitte.setText(Global.toHtml(checkParameters(g,TxtZentrum.getText())));
           LblRechts.setText(Global.toHtml(checkParameters(g,TxtRechts.getText())));
           PnlTest.invalidate();
           PnlTest.repaint();
         }

  public static String checkParameters(Global g,String sText)
  {
          sText=Static.replaceString(sText,"\\p",""+1);
          sText=Static.replaceString(sText,"\\s",""+3);
          sText=Static.replaceString(sText,"\\t",new Zeit(new Date(),"HH:mm").toString());
          sText=Static.replaceString(sText,"\\d",new Zeit(new Date(),"dd.MM.yyyy").toString());
          sText=Static.replaceString(sText,"\\u",g.getUser());
          //sText=Static.replaceString(sText,"\\c","D.V.H.");
          if (sText.indexOf("\\c") > -1) // Firma
              sText = Static.replaceString(sText, "\\c",SQL.getString(g,"select bezeichnung from stammview where aic_stamm="+g.getSyncStamm(g.iSttFirma)));
          sText=DruckHS.PH(g,"\\Ort","ORT",sText);
          sText=DruckHS.PH(g,"\\Strasse","STRASSE",sText);
          sText=DruckHS.PH(g,"\\PLZ","PLZ",sText);
          sText=DruckHS.PH(g,"\\DVR","DVR_NR_STRING",sText);
          sText=Static.replaceString(sText,"\\n","\n");
          return sText;
    }

}
