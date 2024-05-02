package All_Unlimited.Grunddefinition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.EigenschaftsEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 21.04.2005</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class DefAbschluss extends Formular
{
  private Global g;
  private Tabellenspeicher Tab;
  private JCOutliner Out = new JCOutliner(new JCOutlinerFolderNode("Root"));
  private Text TxtBezeichnung = new Text("",60);
  private Text TxtKennung = new Text("",20,Text.KENNUNG);
  private ComboSort CboProg;
  private ComboSort CboBew;
  private EigenschaftsEingabe CboEig;
  private ComboSort CboStt;
  private JRadioButton RadGenerell;
  private JRadioButton RadVorgesetzt;
  private JRadioButton RadAllgemein;
  private AUCheckBox CbxTotal;
  private AUTextArea Memo=new AUTextArea();
  private JButton BtnNeu;
  private JButton BtnLoeschen;
  private JButton BtnHinzu;
  private JButton BtnTausch;
  private JButton BtnWeg;

  private JButton BtnSpeichern;
  private int iAktAIC=0;
  public static final int cstArt=3;
  public static final int cstAllgemein=0;
  public static final int cstGenerell=1;
  public static final int cstVorgesetzt=2;
  public static final int cstTotal=4; // Totalsperre .. auch lesen wird verboten

  public DefAbschluss(Global glob)
  {
    super("DefAbschluss", null, glob);
    g = glob;
    Build();
    FillOutliner();
    EnableButtons();
    show();
  }

  private void Build()
  {
    String [] s = {"Abschluß/Bewegung","Kennung/Eigenschaft","Stammtyp","Art","Prog","Aic"};
    Out.setColumnButtons(s);
    Out.setNumColumns(s.length);
    Out.setRootVisible(false);
    Out.setBackground(Color.white);
    getFrei("Outliner").add("Center",Out);

    BtnNeu = getButton("Neu");
    BtnLoeschen = getButton("Loeschen");
    BtnHinzu = getButton("<");
    BtnTausch = getButton("Ersetzen");
    BtnWeg = getButton(">");
    BtnSpeichern = getButton("Speichern");

    JPanel PnlEingabe = getFrei("Eingabe");
    JPanel PnlMemo = getFrei("Memo");
    CboProg=new ComboSort(g);
    CboProg.fillBegriffTable(g.TabBegriffgruppen.getAic("Programm"),true,true);
    CboBew=new ComboSort(g);
    CboBew.fillDefTable("Erfassungstyp",false);
    CboEig=new EigenschaftsEingabe(g,thisFrame);
    CboStt=new ComboSort(g);
    CboStt.fillDefTable("Stammtyp",true);
    RadGenerell=g.getRadiobutton("Generell");
    RadVorgesetzt=g.getRadiobutton("Vorgesetzt");
    RadAllgemein=g.getRadiobutton("Allgemein");
    CbxTotal=g.getCheckbox("Total");
    ButtonGroup RadNeu=new ButtonGroup();
    RadNeu.add(RadGenerell);
    RadNeu.add(RadVorgesetzt);
    RadNeu.add(RadAllgemein);

    PnlEingabe.setLayout(new BorderLayout(2,2));
    JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
    g.addLabel2(Pnl,"Bezeichnung");
    g.addLabel2(Pnl,"Kennung");
    g.addLabel2(Pnl,"Stammtyp");
    g.addLabel2(Pnl,"Art");
    //g.addLabel2(Pnl,"");
    //g.addLabel2(Pnl,"");
    Pnl.add(new JPanel());
    Pnl.add(new JPanel());
    Pnl.add(new JPanel());
    g.addLabel2(Pnl,"Programm");
    g.addLabel2(Pnl,"Erfassungstyp");
    g.addLabel2(Pnl,"Eigenschaft");
    PnlEingabe.add("West",Pnl);
    Pnl = new JPanel(new GridLayout(0,1,2,2));
    Pnl.add(TxtBezeichnung);
    Pnl.add(TxtKennung);
    Pnl.add(CboStt);
    Pnl.add(RadGenerell);
    Pnl.add(RadVorgesetzt);
    Pnl.add(RadAllgemein);
    Pnl.add(CbxTotal);
    Pnl.add(CboProg);
    Pnl.add(CboBew);
    Pnl.add(CboEig);
    PnlEingabe.add("Center",Pnl);
    PnlMemo.add(Memo);

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
                  EnableButtons();
                }
        });

        CboBew.addItemListener(new ItemListener()
                {
                  public void itemStateChanged(ItemEvent ev) {
                    if(ev.getStateChange() == ItemEvent.SELECTED) {
                      fillEigenschaft();
                    }
                  }
                });

                ActionListener AL=new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    String s = ev.getActionCommand();
                    if (s.equals("Neu"))
                    {
                      TxtBezeichnung.setText("");
                      TxtKennung.setText("");
                      CboProg.setSelectedAIC(0);
                      //TxtKennung.setEnabled(true);
                      Memo.setText("");
                      Out.selectNode(Out.getRootNode(), null);
                      iAktAIC = 0;
                      EnableButtons();
                    }
                    else if (s.equals("Del"))
                      Del();
                    else if (s.equals("Hinzu"))
                      Hinzu();
                    else if (s.equals("Tausch"))
                      Tausch();
                    else if (s.equals("Weg"))
                      Weg();
                    else if (s.equals("Save"))
                      Save();
                    else if (s.equals("Beenden"))
                      hide();
                  }
                };
                g.BtnAdd(BtnNeu,"Neu",AL);
                g.BtnAdd(BtnLoeschen,"Del",AL);
                g.BtnAdd(BtnHinzu,"Hinzu",AL);
                g.BtnAdd(BtnTausch,"Tausch",AL);
                g.BtnAdd(BtnWeg,"Weg",AL);
                g.BtnAdd(BtnSpeichern,"Save",AL);
                g.BtnAdd(getButton("Beenden"),"Beenden",AL);
  }

  private void FillOutliner()
  {
        JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
        NodeRoot.removeChildren();
        Tab=new Tabellenspeicher(g,"select aic_abschlusstyp,kennung,AIC_Stammtyp,Bits,prog,null Node,null Status"+g.AU_Bezeichnung("abschlusstyp")+g.AU_Memo("abschlusstyp")+" from Abschlusstyp",true);
        for(;!Tab.eof();Tab.moveNext())
        {
                Vector<String> VecVisible=new Vector<String>();
                Vector<Comparable> VecInvisible=new Vector<Comparable>();

                VecVisible.addElement(Tab.getS("bezeichnung"));
                VecVisible.addElement(Tab.getS("kennung"));
                VecVisible.addElement(CboStt.getBezeichnung(Tab.getI("AIC_Stammtyp")));
                int iBits=Tab.getI("Bits")&cstArt;
                VecVisible.addElement(iBits==cstGenerell ? "G":iBits==cstVorgesetzt ? "V":"-");
                VecVisible.addElement(CboProg.getBezeichnung(Tab.getI("prog"))+" ("+Tab.getI("prog")+")");
                VecVisible.addElement(Tab.getS("aic_Abschlusstyp"));
                VecInvisible.addElement(Tab.getInt("aic_Abschlusstyp"));
                VecInvisible.addElement(Tab.getM("Memo"));
                VecInvisible.addElement(Tab.getI("AIC_Stammtyp"));
                VecInvisible.addElement(Tab.getI("Bits"));
                VecInvisible.addElement(Tab.getI("prog"));
                JCOutlinerNode Nod=new JCOutlinerFolderNode((Object)VecVisible,NodeRoot);
                Tab.setInhalt("Node",Nod);
                Nod.setUserData(VecInvisible);
                if (iAktAIC==Tab.getI("aic_Abschlusstyp"))
                  Out.selectNode(Nod,null);
        }
        Tabellenspeicher Tab2=new Tabellenspeicher(g,"select * from Abschlussdefinition",true);
        for(;!Tab2.eof();Tab2.moveNext())
          if (Tab.posInhalt("AIC_Abschlusstyp",Tab2.getI("AIC_Abschlusstyp")))
          {
            Vector<String> VecVisible=new Vector<String>();
            Vector<Integer> VecInvisible=new Vector<Integer>();
            VecVisible.addElement(CboBew.getBezeichnung(Tab2.getI("AIC_Bewegungstyp"))+" ("+Tab2.getI("AIC_Bewegungstyp")+")");
            VecVisible.addElement(g.TabEigenschaften.getBezeichnungS(Tab2.getI("AIC_Eigenschaft")));
            VecInvisible.addElement(Tab2.getInt("AIC_Bewegungstyp"));
            VecInvisible.addElement(Tab2.getInt("AIC_Eigenschaft"));
            //VecInvisible.addElement(new Integer(Tab2.getI("AIC_Stammtyp")));
            JCOutlinerNode NodNeu = new JCOutlinerFolderNode((Object)VecVisible, (JCOutlinerFolderNode)Tab.getInhalt("Node"));
            NodNeu.setUserData(VecInvisible);
          }
        Out.folderChanged(NodeRoot);
  }

  private void fillEigenschaft()
  {
    CboEig.Cbo.fillCbo("select e.aic_eigenschaft,e.kennung"+g.AU_Bezeichnung2("Eigenschaft","e")+" from eigenschaft e join bew_zuordnung z on e.aic_eigenschaft=z.aic_eigenschaft and z.aic_bewegungstyp="+CboBew.getSelectedAIC(),"eigenschaft",false,false);
  }

  private void Save()
  {
    SQL Qry=new SQL(g);
    for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
      if (Tab.getS("Status").equals("Edit"))
      {
        Vector VecC = ((JCOutlinerFolderNode)Tab.getInhalt("Node")).getChildren();
        int iAic=Tab.getI("AIC_Abschlusstyp");
        Qry.exec("delete from Abschlussdefinition where aic_abschlusstyp="+iAic);
        for(int i=0;VecC!=null && i<VecC.size();i++)
        {
          Vector Vec=(Vector)((JCOutlinerFolderNode)VecC.elementAt(i)).getUserData();
          Qry.add("AIC_Abschlusstyp",iAic);
          Qry.add("AIC_Bewegungstyp",(Integer)Vec.elementAt(0));
          Qry.add("AIC_Eigenschaft",(Integer)Vec.elementAt(1));
          //Qry.add0("AIC_Stammtyp",(Integer)Vec.elementAt(2));
          Qry.insert("Abschlussdefinition",false);
        }
      }
    Qry.add("Kennung",TxtKennung.getText());
    Qry.add0("prog",CboProg.getSelectedAIC());
    Qry.add0("aic_stammtyp",CboStt.getSelectedAIC());
    Qry.add("Bits",(RadGenerell.isSelected()?cstGenerell:RadVorgesetzt.isSelected()?cstVorgesetzt:0)+(CbxTotal.isSelected()?cstTotal:0));
    if (iAktAIC==0)
      iAktAIC=Qry.insert("Abschlusstyp",true);
    else
      Qry.update("Abschlusstyp",iAktAIC);
    int iTab=g.TabTabellenname.getAic("ABSCHLUSSTYP");
    g.setBezeichnung("",TxtBezeichnung.getText(),iTab,iAktAIC,0);
    g.setMemo(Memo.getValue(),"",iTab,iAktAIC,0);
    Qry.free();

    FillOutliner();
  }

  private void Del()
  {
    int iAnz=SQL.getInteger(g,"select count(*) from abschluss where aic_abschlusstyp="+iAktAIC+" and pro_aic_protokoll is null");
    int iAnz2=SQL.getInteger(g,"select count(*) from abschluss_zuordnung where aic_abschlusstyp="+iAktAIC);
    if (iAnz+iAnz2>0)
      new Message(Message.WARNING_MESSAGE,(JFrame)thisFrame,g).showDialog("BereitsVerwendet",new String[] {iAnz+"x Abschluss, "+iAnz2+"x Zuordnung"});
    else
    {
      int iMessage = new Message(Message.YES_NO_OPTION, (JFrame)thisFrame, g).showDialog("Loeschen", new String[] {TxtBezeichnung.getText()});
      if(iMessage == Message.YES_OPTION) {
        //SQL Qry=new SQL(g);
        //Qry.exec("DELETE FROM Abschluss_Zuordnung WHERE AIC_Abschlusstyp="+iAktAIC);
        g.exec("DELETE FROM Abschluss WHERE AIC_Abschlusstyp=" + iAktAIC);
        g.exec("DELETE FROM Abschlussdefinition WHERE AIC_Abschlusstyp=" + iAktAIC);
        g.exec("DELETE FROM Abschlusstyp WHERE AIC_Abschlusstyp=" + iAktAIC);
        //Qry.deleteFrom("Begriff","Begriff WHERE AIC_Begriff="+iAktAIC,g.iTabBegriff);
        FillOutliner();
        //Qry.free();
      }
    }
  }

  private void Hinzu()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    if (Nod.getLevel()==2)
      Nod=Nod.getParent();
    if (Tab.posInhalt("Node",Nod))
      Tab.setInhalt("Status","Edit");
    //Tab.showGrid();
    Vector<String> VecVisible=new Vector<String>();
    Vector<Integer> VecInvisible=new Vector<Integer>();
    VecVisible.addElement(CboBew.getSelectedBezeichnung());
    VecVisible.addElement(CboEig.Cbo.getSelectedBezeichnung());
    VecVisible.addElement(CboStt.getSelectedBezeichnung());
    VecInvisible.addElement(CboBew.getSelectedAIC());
    VecInvisible.addElement(CboEig.getSelectedAIC());
    VecInvisible.addElement(CboStt.getSelectedAIC());
    JCOutlinerNode NodNeu=new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)Nod);
    NodNeu.setUserData(VecInvisible);
    Out.folderChanged(Nod);
  }
  @SuppressWarnings("unchecked")
private void Tausch()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    if (Tab.posInhalt("Node",Nod.getParent()))
      Tab.setInhalt("Status","Edit");
    Vector<String> VecVisible=(Vector)Nod.getLabel();
    Vector VecInvisible=new Vector();
    VecVisible.setElementAt(CboBew.getSelectedBezeichnung(),0);
    VecVisible.setElementAt(CboEig.Cbo.getSelectedBezeichnung(),1);
    VecVisible.setElementAt(CboStt.getSelectedBezeichnung(),2);
    VecInvisible.addElement(CboBew.getSelectedAIC());
    VecInvisible.addElement(CboEig.getSelectedAIC());
    VecInvisible.addElement(CboStt.getSelectedAIC());
    Nod.setUserData(VecInvisible);
    Out.folderChanged(Nod.getParent());
  }
  private void Weg()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    if (Tab.posInhalt("Node",Nod.getParent()))
      Tab.setInhalt("Status","Edit");
    Out.selectNode(Nod.getParent(),null);
    Nod.getParent().removeChild(Nod);
    Out.folderChanged(Nod);
  }

  private void EnableButtons()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    int iLevel= Nod==null ? 0:Nod.getLevel();
    BtnLoeschen.setEnabled(iLevel==1);
    CboBew.setEnabled(iLevel>0);
    CboEig.setEnabled(iLevel>0);
    //CboStt.setEnabled(iLevel>0);
    BtnHinzu.setEnabled(iLevel>0);
    BtnTausch.setEnabled(iLevel==2);
    BtnWeg.setEnabled(iLevel==2);
    if (iLevel==1)
    {
      Vector Vec=(Vector)Nod.getUserData();
      iAktAIC=((Integer)Vec.elementAt(0)).intValue();
      Memo.setText(""+Vec.elementAt(1));
      CboStt.setSelectedAIC(Sort.geti(Vec, 2));
      CboProg.setSelectedAIC(Sort.geti(Vec, 4));
      int iBits=((Integer)Vec.elementAt(3)).intValue();
      RadGenerell.setSelected((iBits&cstArt)==cstGenerell);
      RadVorgesetzt.setSelected((iBits&cstArt)==cstVorgesetzt);
      RadAllgemein.setSelected((iBits&cstArt)==cstAllgemein);
      CbxTotal.setSelected((iBits&cstTotal)>0);
      Vec=(Vector)Nod.getLabel();
      TxtBezeichnung.setText(""+Vec.elementAt(0));
      TxtKennung.setText(""+Vec.elementAt(1));
    }
    else if (iLevel==2)
    {
      Vector Vec=(Vector)Nod.getUserData();
      CboBew.setSelectedAIC(((Integer)Vec.elementAt(0)).intValue());
      CboEig.setSelectedAIC(((Integer)Vec.elementAt(1)).intValue());
      Vec=(Vector)Nod.getParent().getUserData();
      iAktAIC=((Integer)Vec.elementAt(0)).intValue();
      CboStt.setSelectedAIC(Sort.geti(Vec, 2));
      CboProg.setSelectedAIC(Sort.geti(Vec, 4));
      int iBits=((Integer)Vec.elementAt(3)).intValue();
      RadGenerell.setSelected((iBits&cstArt)==cstGenerell);
      RadVorgesetzt.setSelected((iBits&cstArt)==cstVorgesetzt);
      RadAllgemein.setSelected((iBits&cstArt)==cstAllgemein);
      CbxTotal.setSelected((iBits&cstTotal)>0);
      Memo.setText(""+Vec.elementAt(1));
      Vec=(Vector)Nod.getParent().getLabel();
      TxtBezeichnung.setText(""+Vec.elementAt(0));
      TxtKennung.setText(""+Vec.elementAt(1));
    }

  }

}
