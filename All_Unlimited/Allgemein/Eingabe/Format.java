package All_Unlimited.Allgemein.Eingabe;

import java.awt.BorderLayout;
//import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Anzeige.Memo1;

public class Format extends JPanel
{
  /**
	 *
	 */
	private static final long serialVersionUID = -4839174712694403488L;
private Global g;
  public ComboSort Cbo;
  private JButton Btn;
  private JDialog FomAuswahl=null;
  private AUOutliner Gid;
  private Text EdtBezeichnung;
  private Text EdtKennung;
  private AUCheckBox CbxDateTime;
  private AUCheckBox CbxNumber;
  private AUCheckBox CbxString;
  private AUCheckBox CbxBoolean;
  private AUCheckBox CbxUser;
  private AUTextArea Memo;
  private int iAic=0;
  private int iBG_Format=0;
  private int iTyp=0;
  private boolean bSave=false;
  private String sTable;
  private Window PFom;
  private boolean bUser=false;
  public boolean bFehler=false;

  public static final int cstFix     =1;
  public static final int cstDateTime=2;
  public static final int cstNumber  =4;
  public static final int cstString  =8;
  public static final int cstBoolean=16;
  public static final int cstUser =  32;

  public Format(Global rg,String rsTable,Window rFom)
  {
      g = rg;
      PFom=rFom;
      sTable=rsTable;
      Build();
  }

  public void Build()
  {
    Cbo = new ComboSort(g);
    Cbo.fillFormat(0,true,sTable,false);
    setLayout(new BorderLayout(2,2));
    add("Center",Cbo);
    if (g.Def())
    {
      Btn = g.getButton("...");
      Btn.setMargin(g.inset);
      add("East",Btn);
      Btn.addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
                  if(FomAuswahl==null)
                    createFomAuswahl();
                  bSave=false;
                  //if(TabSchrift.posInhalt("Aic",Cbo.getSelectedAIC()))
                  //  Gid.selectNode((JCOutlinerNode)TabSchrift.getInhalt("Knoten"),null);
                  Pos(Cbo.getSelectedAIC());
                  FomAuswahl.setVisible(true);
          }
      });
      Btn.addMouseListener(new MouseListener()
      {
        public void mousePressed(MouseEvent ev)
        {
        }

        public void mouseClicked(MouseEvent ev)
        {
          if (ev.getButton()==3 && ev.getClickCount()==2)
          {
            Cbo.setEnabled(true);
            setTyp("Datum",null,false);
          }
        }

        public void mouseEntered(MouseEvent ev)
        {
        }

        public void mouseExited(MouseEvent ev)
        {
        }

        public void mouseReleased(MouseEvent ev)
        {
        }

      });
    }
  }

  private void Pos(int iPos)
  {
    if (iPos>0)
    {
      Vector Vec = Gid.getRootNode().getChildren();
      JCOutlinerNode NodNeu=null;
      if (Vec != null)
      {
        boolean b=true;
        for (int i = 0; i < Vec.size() && b; i++)
        {
          NodNeu=(JCOutlinerNode)Vec.elementAt(i);
          Vector Vec2=(Vector)NodNeu.getUserData();
          int iMom=Sort.geti(Vec2,0);
          b=iMom != iPos;
        }
        if (!b)
          Static.makeVisible(Gid,NodNeu);
      }
    }
  }

  private void createFomAuswahl()
{
        Gid = new AUOutliner(new JCOutlinerFolderNode("Root"));
        String[] s = new String[] {g.getShow("Bezeichnung"), g.getShow("Format")};
        Gid.setColumnButtons(s);
        Gid.setNumColumns(s.length);

        Gid.setRootVisible(false);
        JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Gid.getRootNode();
        Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,defBezeichnung,begriff.kennung,bits"+g.AU_Memo("Begriff")+" from begriff"+g.join("begriffgruppe","g","begriff","begriffgruppe")+" where g.kennung='Format'",true);
        for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
        {
          Vector<String> VecVisible=new Vector<String>();
          VecVisible.addElement(Tab.getS("DefBezeichnung"));
          VecVisible.addElement(Tab.getS("Kennung"));
          Vector<Object> VecInvisible=new Vector<Object>();
          VecInvisible.addElement(Tab.getInhalt("aic_begriff"));
          VecInvisible.addElement(Tab.getInhalt("bits"));
          VecInvisible.addElement(Tab.getM("Memo"));
          VecInvisible.addElement(Tab.getInhalt("Bits"));
          JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodeRoot);
          Nod.setUserData(VecInvisible);
        }

        //if (g.TabBegriffgruppen.posInhalt("Kennung","Format"))
          iBG_Format=g.TabBegriffgruppen.getAic("Format");
        //else
        //  Static.printError("Begriffgruppe Format nicht gefunden");

        if (PFom instanceof JFrame)
          FomAuswahl = new JDialog((JFrame)PFom, "Format-Auswahl", false);
        else
          FomAuswahl = new JDialog((JDialog)PFom, "Format-Auswahl", ((JDialog)PFom).isModal());

        //FomAuswahl.getContentPane().setLayout(new BorderLayout(5,5));
        JPanel PnlSouth=new JPanel(new GridLayout());
        JPanel PnlCenterMain=new JPanel(new GridLayout(0,2,5,5));
        JPanel PnlCenter=new JPanel(new BorderLayout(5,5));
        JPanel PnlEingabe=new JPanel(new BorderLayout());
        JPanel PnlText=new JPanel(new GridLayout(0,1));
        JPanel PnlWert=new JPanel(new GridLayout(0,1));
        JTabbedPane PnlTest=new JTabbedPane();

        EdtBezeichnung=new Text("",250);
        EdtKennung=new Text("",98);
        CbxDateTime = g.getCheckbox("DateTime");
        CbxNumber = g.getCheckbox("Number");
        CbxString = g.getCheckbox("String");
        CbxBoolean = g.getCheckbox("Boolean");
        CbxUser = g.getCheckbox("User");
        Memo=new AUTextArea();

        PnlText.add(new JLabel(g.getBegriffS("Label","Bezeichnung:")));
        PnlWert.add(EdtBezeichnung);
        PnlText.add(new JLabel(g.getBegriffS("Label","Format:")));
        PnlWert.add(EdtKennung);
        for (int i=0;i<5;i++)
          PnlText.add(new JLabel());
        PnlWert.add(CbxDateTime);
        PnlWert.add(CbxNumber);
        PnlWert.add(CbxString);
        PnlWert.add(CbxBoolean);
        PnlWert.add(CbxUser);

        PnlEingabe.add("West",PnlText);
        PnlEingabe.add("Center",PnlWert);

        PnlCenter.add("North",PnlEingabe);
        PnlCenter.add("Center",Memo);

        // Datum
        final Datum EdtDatum=new Datum(g,"yyyy-MM-dd HH:mm");
        EdtDatum.setDate(new java.util.Date());
        final JButton BtnDatum=new JButton();
        BtnDatum.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            try
            {
              BtnDatum.setText(""+new Zeit(EdtDatum.getDateTime(),EdtKennung.getText()));
            }
            catch (Exception e2)
            {
              BtnDatum.setText("! Error !");
            }
          }
        });
        JPanel PnlDatum=new JPanel(new GridLayout(0,1));
        PnlDatum.add(EdtDatum);
        PnlDatum.add(BtnDatum);
        PnlTest.add(g.getBegriffS("Show","Datum"),PnlDatum);
        // Zahl
        final Zahl EdtZahl=new Zahl(0.0);
        final JButton BtnZahl=new JButton();
        BtnZahl.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            try
            {
              BtnZahl.setText("" + new Zahl1(EdtZahl.getDouble(), EdtKennung.getText()));
            }
            catch (Exception e2)
            {
              BtnZahl.setText("! Error !");
            }
          }
        });
        JPanel PnlZahl=new JPanel(new GridLayout(0,1));
        PnlZahl.add(EdtZahl);
        PnlZahl.add(BtnZahl);
        PnlTest.add(g.getBegriffS("Show","Zahl"),PnlZahl);
        // VonBis
        final VonBisEingabe EdtVonBis=new VonBisEingabe(g.getVon(),g.getBis(),"yyyy-MM-dd HH:mm",g,g.getStunde(),2);
        EdtDatum.setDate(new java.util.Date());
        final JButton BtnVonBis=new JButton();
        BtnVonBis.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            try
            {
              BtnVonBis.setText(""+new VonBis(g,EdtVonBis.getVon(),EdtVonBis.getBis(),EdtVonBis.getSeconds(),EdtKennung.getText(),EdtVonBis.getFaktor()));
            }
            catch (Exception e2)
            {
              BtnVonBis.setText("! Error !");
            }
          }
        });
        JPanel PnlVonBis=new JPanel(new GridLayout(0,1));
        PnlVonBis.add(EdtVonBis);
        PnlVonBis.add(BtnVonBis);
        PnlTest.add(g.getBegriffS("Show","Von-Bis"),PnlVonBis);

        PnlCenter.add("South",PnlTest);

        Gid.addItemListener(new JCItemListener()
        {
                public void itemStateChanged(JCItemEvent ev)
                {
                        if(ev.getStateChange()==ItemEvent.SELECTED)
                        {
                                Vector Vec=(Vector)((AUOutliner)ev.getSource()).getSelectedNode().getLabel();
                                if (Vec != null)
                                {
                                  EdtBezeichnung.setText((String)Vec.elementAt(0));
                                  EdtKennung.setText((String)Vec.elementAt(1));
                                  EdtKennung.setText((String)Vec.elementAt(1));
                                  Vector Vec2=(Vector)((AUOutliner)ev.getSource()).getSelectedNode().getUserData();
                                  Memo.setText((String)Vec2.elementAt(2));
                                  iAic=((Integer)Vec2.elementAt(0)).intValue();
                                  int iBits=Vec2.elementAt(3)==null?0:((Long)Vec2.elementAt(3)).intValue();
                                  CbxDateTime.setSelected((iBits&cstDateTime)>0);
                                  CbxNumber.setSelected((iBits&cstNumber)>0);
                                  CbxString.setSelected((iBits&cstString)>0);
                                  CbxBoolean.setSelected((iBits&cstBoolean)>0);
                                  CbxUser.setSelected((iBits&cstUser)>0);
                                }
                        }
                }
        });


        JButton BtnNeu=g.getButton("Neu");
                BtnNeu.setEnabled(true);
                BtnNeu.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                iAic=0;
                                EdtBezeichnung.setText("");
                                EdtKennung.setText("");
                                Memo.setText("");
                                /*CbxDateTime.setSelected(true);
                                CbxNumber.setSelected(true);
                                CbxString.setSelected(true);
                                CbxBoolean.setSelected(true);*/
                        }
                });
                JButton BtnSave=g.getButton("Speichern");
                BtnSave.setEnabled(true);
                BtnSave.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                Save();
                        }
                });
                JButton BtnDel=g.getButton("Loeschen");
                BtnDel.setEnabled(true);
                BtnDel.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                          Delete();
                        }
                });
                JButton BtnOk=g.getButton("Ok");
                BtnOk.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                          fillAll();
                          Cbo.setSelectedAIC(iAic);
                          FomAuswahl.setVisible(false);
                        }
                });
                JButton BtnAbbruch=g.getButton("Abbruch");
                BtnAbbruch.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                          fillAll();
                          FomAuswahl.setVisible(false);
                        }
                });
                PnlSouth.add(BtnNeu);
                PnlSouth.add(BtnSave);
                PnlSouth.add(BtnDel);
                PnlSouth.add(BtnOk);
                PnlSouth.add(BtnAbbruch);

                //FomAuswahl.getContentPane().add("West",Gid);
                FomAuswahl.getContentPane().add("South",PnlSouth);
                PnlCenterMain.add(Gid);
                PnlCenterMain.add(PnlCenter);
                FomAuswahl.getContentPane().add("Center",PnlCenterMain);
                FomAuswahl.setSize(600,600);
                Static.centerComponent(FomAuswahl,PFom);

}

private void fillAll()
{
  if (bSave)
  {
    g.progInfo("Format.fillAll");
    int i=Cbo.getSelectedAIC();
    Cbo.fillFormat(iTyp,true,sTable,bUser);
    Cbo.setSelectedAIC(i);
  }
}

public static String get(Object Obj,String sDT,String sFormat)
{
  int iNew=getTyp(sDT,true);
  return Obj==null ? "":iNew==cstDateTime ?new SimpleDateFormat(sFormat).format((java.util.Date)Obj):
      iNew==cstNumber ?new Zahl1(Obj,sFormat).toString():Obj instanceof Memo1 ? ((Memo1)Obj).getValue():Obj.toString();
}

private static int getTyp(String sDT,boolean bFremd)
{
  return sDT.equals("Datum") || sDT.equals("CalcDatum") || sDT.equals("von_bis") || sDT.equals("Auto_von_bis") || sDT.equals("Eintritt") || sDT.equals("Austritt") || sDT.equals("EinAustritt")
      || sDT.equals("Zeit")|| sDT.equals("Stichtag") || sDT.equals("BewDatum") || sDT.equals("BewVon_Bis") || sDT.equals("BewDatum2") || !bFremd && sDT.endsWith("Bezeichnung")
      || sDT.equals("Timestamp") || sDT.equals("entfernt") ? cstDateTime:
      sDT.equals("Double") || sDT.equals("Integer") || sDT.endsWith("Mass") || sDT.endsWith("Waehrung") || sDT.equals("BewDauer") || sDT.equals("Einheiten")
      || sDT.startsWith("BewZahl") || sDT.equals("BewMass2") || sDT.equals("BewWaehrung2") || sDT.equals("CalcDauer") || sDT.equals("CalcField") ? cstNumber:
      sDT.equals("StringSehrKurz") || sDT.equals("StringKurz") || sDT.equals("String60") || sDT.equals("StringLang") || sDT.equals("Stringx") || sDT.equals("Text") || sDT.equals("E-Mail")
      || sDT.equals("WWW") || sDT.equals("Memo") || sDT.equals("Pfad") || sDT.equals("Filename") || sDT.equals("StringKurzOhne") || sDT.equals("Passwort") || sDT.equals("Filler") || sDT.equals("CalcString")
      || bFremd && sDT.endsWith("Bezeichnung") ? cstString : sDT.endsWith("Boolean") ? cstBoolean:0;
}

public static int getTyp2(String sAA,int iTyp)
{
  return sAA==null || sAA.equals("") ? iTyp:sAA.equals("date") || sAA.equals("timestamp") ? cstDateTime:0;
}

public void setTyp(String sDT,String sAA,boolean rbUser)
{
  bUser=rbUser;
  int iNew=getTyp(sDT,bUser);
  //g.progInfo("Format:"+sDT+"->"+iNew);
  iNew=getTyp2(sAA,iNew);
  //g.progInfo(sAA+"->"+iNew);
  Cbo.setEnabled(iNew>0);
  if (Btn != null)
    Btn.setEnabled(iNew>0);
  if (iNew!=iTyp)
  {
    g.progInfo("Typ von "+iTyp+" auf "+iNew);
    iTyp=iNew;
    int i=Cbo.getSelectedAIC();
    Cbo.fillFormat(iTyp,true,sTable,bUser);
    Cbo.setSelectedAIC(i);
  }
}

@SuppressWarnings("unchecked")
public void Save()
  {
    if (Message.KennungWarnung(g,EdtKennung.getText(),iAic,iBG_Format,FomAuswahl))
      return;
    //if(!EdtKennung.isNull() && (!EdtKennung.Modified()||(!SQL.exists(g,"select aic_begriff from begriff where aic_begriffgruppe="+iBG_Format+" and kennung='"+EdtKennung.getText()+"'"))))
    //{
      bSave = true;
      String sKennung=EdtKennung.getText();
      SQL Qry = new SQL(g);
      Qry.add("Kennung", sKennung);
      Qry.add("DefBezeichnung", EdtBezeichnung.getText());
      long lBits=cstFix+(CbxDateTime.isSelected()?cstDateTime:0)+(CbxNumber.isSelected()?cstNumber:0)+(CbxString.isSelected()?cstString:0)+(CbxBoolean.isSelected()?cstBoolean:0)+(CbxUser.isSelected()?cstUser:0);
      Qry.add("Bits",lBits);
      Qry.add("aic_logging",g.getLog());
      boolean bNeu=iAic==0;
      if (bNeu)
      {
        Qry.add("AIC_BEGRIFFGRUPPE", iBG_Format);
        iAic = Qry.insert("Begriff", true);
        g.putTabBegriffe(iBG_Format,iAic,sKennung,EdtBezeichnung.getText(),EdtBezeichnung.getText(),null,0,null,-1,-1,-1,-1,-1,lBits,false,false,0,null,false,null,null,null,null,null,true);
        Vector<String> VecVisible = new Vector<String>();
        VecVisible.addElement(EdtBezeichnung.getText());
        VecVisible.addElement(sKennung);
        Vector<Comparable> VecInvisible = new Vector<Comparable>();
        VecInvisible.addElement(new Integer(iAic));
        VecInvisible.addElement(null);
        VecInvisible.addElement(Memo.getValue());
        VecInvisible.addElement(new Long(lBits));
        JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)Gid.getRootNode());
        Nod.setUserData(VecInvisible);
        Static.makeVisible(Gid, Nod);
      }
      else
      {
        if (Qry.update("Begriff", iAic))
          g.putTabBegriffe(iBG_Format,iAic,sKennung,EdtBezeichnung.getText(),EdtBezeichnung.getText(),null,0,null,-1,-1,-1,-1,-1,lBits,false,false,0,null,false,null,null,null,null,null,false);
        Vector<String> VecVisible=(Vector)Gid.getSelectedNode().getLabel();
        VecVisible.setElementAt(EdtBezeichnung.getText(), 0);
        VecVisible.setElementAt(sKennung, 1);
        Vector VecInvisible=(Vector)Gid.getSelectedNode().getUserData();
        VecInvisible.setElementAt(Memo.getValue(), 2);
        VecInvisible.setElementAt(new Long(lBits), 3);
        Gid.folderChanged(Gid.getRootNode());
      }
      g.SaveDefVerlauf(iAic, sKennung+(bNeu ? " neu":" geändert"));
      if (Memo.Modified())
        g.setMemo(Memo.getValue(),"",null,Global.iTabBegriff,iAic,0);
    //}
    //else
    //  new Message(Message.ERROR_MESSAGE,null,g).showDialog(EdtKennung.isNull()?"KennungLeer":"KennungVorhanden");
  }

  public void Delete()
  {
    SQL Qry=new SQL(g);
    String s4=Qry.list("e.kennung","eigenschaft e,begriff where Format=begriff.kennung and begriff.aic_begriff="+iAic);
    String s5=Qry.list("b.defbezeichnung","begriff b"+g.join("abfrage","b","begriff")+g.join2("spalte","abfrage")+" where spalte.aic_begriff="+iAic);
    String s=(s4.equals(" ")?"":"\nEigenschaften:"+s4+"\n")+(s5.equals(" ")?"":"\nAbfragen:"+s5+"\n");

    if (!s.equals(""))
            new Message(Message.WARNING_MESSAGE,(JFrame)PFom,g).showDialog("BereitsVerwendet",new String[] {s});
    else if(new Message(Message.YES_NO_OPTION,(JFrame)PFom,g).showDialog("Loeschen",new String[] {"["+Gid.getSelectedNode().getLabel()+"]"})==Message.YES_OPTION)
          if (Qry.deleteFrom("Begriff","Begriff where aic_Begriff="+iAic,Global.iTabBegriff)>-1)
          {
            bSave=true;
            g.TabBegriffe.clearInhaltS("aic",new Integer(iAic));
            g.SaveDefVerlauf(g.getBegriffAicS("Show","Format"), EdtKennung.getText()+" entfernt");
            JCOutlinerNode Nod=Gid.getSelectedNode();
            Gid.selectNode(Gid.getNextNode(Nod),null);
            Nod.getParent().removeChild(Nod);
            Gid.folderChanged(Gid.getRootNode());
          }
    Qry.free();
  }

public void setEnabled(boolean b)
{
        Cbo.setEnabled(b);
        //if (!b)
        //  Cbo.setFirst();
        if (g.Def())
          Btn.setEnabled(b);
}

  public void setAIC(int iAIC)
  {
    Cbo.setSelectedAIC(iAIC);
  }

  public void setText(String s)
  {
	bFehler=false;
    if (s==null || s.equals(""))
      Cbo.setSelectedAIC(0);
    else
    {
      Cbo.setSelectedKennung(s);
      bFehler=!s.equals(Cbo.getSelectedKennung());
      if (bFehler)
    	  g.fixtestError("Format "+s+" nicht setzbar");
    }
  }

  public boolean isNull()
  {
    return Cbo.isNull();
  }

  public String getText()
  {
    return Cbo.isNull()?null:Cbo.getSelectedKennung();
  }

  public int getAIC()
  {
    return Cbo.getSelectedAIC();
  }

  public boolean Modified()
  {
    return Cbo.Modified();
  }

}
