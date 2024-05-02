package All_Unlimited.Grunddefinition;

import java.awt.Color;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Hauptmaske.Abschluss;

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
public class Periodensperre extends Formular
{
  private Global g;
  private static Periodensperre Self=null;
  private Vector VecBG=null;
  private AUOutliner OutBenutzergruppeMA = new AUOutliner(new JCOutlinerFolderNode(""));
  private AUOutliner OutAbschluss = new AUOutliner(new JCOutlinerFolderNode(""));
  private JButton BtnRefreshMA;
  private JButton BtnSpeichern;
  private JButton BtnBeenden;
  private JButton BtnHinzufuegen;
  private JButton BtnEntfernen;


  public static Periodensperre get(Global rg)
  {
    return Self == null ? new Periodensperre(rg) : Self;
  }

  public static void free()
  {
    if (Self != null)
    {
      Self.g.winInfo("Periodensperre.free");
      Self.thisFrame.dispose();
      Self = null;
    }
  }

  private Periodensperre(Global glob)
  {
    super("Periodensperre", null, glob);
    Self=this;
    g = glob;
    g.winInfo("Periodensperre.create");
    Build();
  }

  public void show(Vector Vec)
  {
    VecBG=Vec;
    fillOutliners();
    thisFrame.setVisible(true);
  }


  private void Build()
  {
    OutBenutzergruppeMA.setBackground(Color.white);
    OutAbschluss.setBackground(Color.white);
    OutBenutzergruppeMA.setRootVisible(false);
    OutAbschluss.setRootVisible(false);
    OutBenutzergruppeMA.setAllowMultipleSelections(true);
    OutAbschluss.setAllowMultipleSelections(true);
    OutBenutzergruppeMA.setColumnLabelSortMethod(Sort.sortMethod);
    OutAbschluss.setColumnLabelSortMethod(Sort.sortMethod);

    String[] s = new String[] {g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung")};
    OutBenutzergruppeMA.setColumnButtons(s);
    OutBenutzergruppeMA.setNumColumns(2);
    OutAbschluss.setColumnButtons(s);
    OutAbschluss.setNumColumns(2);

    JPanel Pnl_Outliner_UsergroupMA = getFrei("Outliner Usergroup3");
    JPanel Pnl_Outliner_Abschluss = getFrei("Monatsabschluss");
    Pnl_Outliner_UsergroupMA.add(OutBenutzergruppeMA);
    Pnl_Outliner_Abschluss.add(OutAbschluss);

    BtnRefreshMA = getButton("Refresh2");
    BtnHinzufuegen = getButton(">3");
    BtnEntfernen = getButton("<3");
    BtnSpeichern = getButton("Speichern");
    BtnBeenden = getButton("Beenden");

    OutAbschluss.addItemListener(new JCOutlinerListener()
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

        OutBenutzergruppeMA.addItemListener(new JCOutlinerListener()
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
        JButton Btn=getFormularButton("DefAbschluss");
        if (Btn != null)
        {
        	if (g.Def())
	          Static.addActionListener(Btn,new ActionListener()
	                {
	                        public void actionPerformed(ActionEvent ev)
	                        {
	                                new DefAbschluss(g);
	                        }
	          });
        	else
        		Btn.setEnabled(false);
        }

        Static.addActionListener(getFormularButton("Abschluss"),new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                Abschluss.get(g).show();
                        }
        });

      BtnRefreshMA.addActionListener(new ActionListener()
        {
              public void actionPerformed(ActionEvent e)
              {
                RefreshMA();
              }
        });


    BtnHinzufuegen.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent e)
            {
                    Hinzufuegen_Abschluss();
            }
    });

    BtnEntfernen.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent e)
            {
                    Entfernen_Abschluss();
            }
    });
    if (BtnSpeichern != null)
    {
      ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnSpeichern);
      BtnSpeichern.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          Save();
        }
      });
    }
    if (BtnBeenden != null)
    {
      BtnBeenden.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          checkSave();
          hide();
        }
      });
      Action cancelKeyAction = new AbstractAction()
      {
        /**
		 *
		 */
		private static final long serialVersionUID = 8143449362534550103L;

		public void actionPerformed(ActionEvent e) {
          checkSave();
          hide();
        }
      };
      Static.Escape(BtnBeenden,thisFrame,cancelKeyAction);
    }
    fillOutliners();
  }

  private void fillOutliners()
  {
    ((JCOutlinerFolderNode)OutBenutzergruppeMA.getRootNode()).removeChildren();
    //g.TabTabellenname.posInhalt("Kennung","BENUTZERGRUPPE");
    JCOutlinerNodeStyle StyleBenutzergruppe = g.getStyle(g.getGif(g.TabTabellenname, "BENUTZERGRUPPE"));//(Image)g.TabTabellenname.getInhalt("Bild"));
    Tabellenspeicher TabBG = new Tabellenspeicher(g,
                                                  "select AIC_Benutzergruppe,kennung" + g.AU_Bezeichnung("Benutzergruppe") + " from benutzergruppe" +
                                                  (VecBG == null ? "" : " where aic_benutzergruppe" + Static.SQL_in(VecBG)), true);
    for (TabBG.moveFirst(); !TabBG.eof(); TabBG.moveNext())
    {
      Vector<String> VecVisible = new Vector<String>();
      VecVisible.addElement(TabBG.getS("Bezeichnung"));
      VecVisible.addElement(TabBG.getS("Kennung"));
      JCOutlinerFolderNode NodeParent = new JCOutlinerFolderNode((Object)VecVisible, (JCOutlinerFolderNode)OutBenutzergruppeMA.getRootNode());
      Vector<Comparable> VecInvisible = new Vector<Comparable>();
      VecInvisible.addElement(TabBG.getInt("AIC_Benutzergruppe"));
      VecInvisible.addElement(Boolean.FALSE);
      NodeParent.setUserData(VecInvisible);
      NodeParent.setStyle(StyleBenutzergruppe);
    }
    try
    {
      OutBenutzergruppeMA.sortByColumn(0, Sort.sortMethod);
    }
    catch(Exception E)
    {
            Static.printError("Periodensperre.fillOutliners: Fehler beim Sortieren");
    }

    OutBenutzergruppeMA.folderChanged(OutBenutzergruppeMA.getRootNode());

    RefreshMA();
  }

  @SuppressWarnings("unchecked")
private void Hinzufuegen_Abschluss()
{
  JCOutlinerNode[] NodeBG=OutBenutzergruppeMA.getSelectedNodes();
  JCOutlinerNode[] NodeMA = OutAbschluss.getSelectedNodes();
  for(int i=0;i<NodeBG.length;i++)
  {
    ((Vector)NodeBG[i].getUserData()).setElementAt(Boolean.TRUE,1);
    for(int j=0;j<NodeMA.length;j++)
    {
      Vector VecVisible = new Vector((Vector)NodeMA[j].getLabel());
      JCOutlinerNode Node = new JCOutlinerNode(VecVisible, (JCOutlinerFolderNode)NodeBG[i]);
      Node.setUserData(NodeMA[j].getUserData());
    }
  }
  OutBenutzergruppeMA.folderChanged(OutBenutzergruppeMA.getRootNode());
}

@SuppressWarnings("unchecked")
private void Entfernen_Abschluss()
{
  JCOutlinerNode[] NodeBG=OutBenutzergruppeMA.getSelectedNodes();
  ((Vector)NodeBG[0].getParent().getUserData()).setElementAt(Boolean.TRUE,1);
  OutBenutzergruppeMA.selectNode(NodeBG[0].getParent(),null);
  for(int i=0;i<NodeBG.length;i++)
  {
    NodeBG[0].getParent().removeChild(NodeBG[i]);
  }
  OutBenutzergruppeMA.folderChanged(OutBenutzergruppeMA.getRootNode());
}

private void RefreshMA()
  {
    SQL Qry = new SQL(g);
        JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)OutAbschluss.getRootNode();
        NodeRoot.removeChildren();

        if(Qry.open("select aic_abschlusstyp,kennung"+g.AU_Bezeichnung("abschlusstyp")+" from Abschlusstyp where "+g.bits("bits",1)+"=0"))
        {
                for(;!Qry.eof();Qry.moveNext())
                {
                        Vector<String> VecVisible=new Vector<String>();
                        VecVisible.addElement(Qry.getS("bezeichnung"));
                        VecVisible.addElement(Qry.getS("kennung"));
                        JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodeRoot);
                        Nod.setUserData(Qry.getInt("aic_Abschlusstyp"));
                }
                Qry.close();
        }
        Qry.free();
        OutAbschluss.folderChanged(NodeRoot);

        Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_benutzergruppe,aic_abschlusstyp"+g.AU_Bezeichnung2("abschlusstyp","abschluss_Zuordnung")+" from abschluss_Zuordnung order by aic_benutzergruppe",true);
        Vector VecC2 = OutBenutzergruppeMA.getRootNode().getChildren();
        for(int i=0;VecC2!=null && i<VecC2.size();i++)
        {
          JCOutlinerFolderNode NodC = (JCOutlinerFolderNode)VecC2.elementAt(i);
          NodC.removeChildren();
          int iBG=((Integer)((Vector)NodC.getUserData()).elementAt(0)).intValue();
          if (Tab.posInhalt("aic_benutzergruppe",iBG))
            while(!Tab.eof() && Tab.getI("aic_benutzergruppe")==iBG)
            {
              Vector<String> VecVisible = new Vector<String>();
              VecVisible.addElement(Tab.getS("bezeichnung"));
              JCOutlinerNode Node = new JCOutlinerNode(VecVisible, NodC);
              Node.setUserData(Tab.getInt("AIC_Abschlusstyp"));
              Tab.moveNext();
            }
        }
        OutBenutzergruppeMA.folderChanged(OutBenutzergruppeMA.getRootNode());
  }

  private void EnableButtons()
  {
    JCOutlinerNode[] NodeBG3 = OutBenutzergruppeMA.getSelectedNodes();
    JCOutlinerNode[] NodeMA = OutAbschluss.getSelectedNodes();
    BtnHinzufuegen.setEnabled(NodeMA!=null && NodeMA.length>0 && NodeMA[0].getLevel()==1 && NodeBG3!=null && NodeBG3.length>0 && NodeBG3[0].getLevel()==1);
    BtnEntfernen.setEnabled(NodeBG3!=null && NodeBG3.length>0 && NodeBG3[0].getLevel()==2);
  }

  private void checkSave()
  {
    boolean bSave = false;
    Vector VecC = OutBenutzergruppeMA.getRootNode().getChildren();
    for (int i = 0; VecC != null && i < VecC.size(); i++)
      if (((Vector)((JCOutlinerFolderNode)VecC.elementAt(i)).getUserData()).elementAt(1) == Boolean.TRUE)
        bSave = true;
    if (bSave && new Message(Message.YES_NO_OPTION, (JFrame)this.thisFrame, g).showDialog("Speichern") == Message.YES_OPTION)
      Save();
  }

  @SuppressWarnings("unchecked")
private void Save()
  {
    SQL Qry = new SQL(g);
    Vector VecC2 = OutBenutzergruppeMA.getRootNode().getChildren();
      for(int i=0;VecC2!=null && i<VecC2.size();i++)
      {
        JCOutlinerFolderNode NodC=(JCOutlinerFolderNode)VecC2.elementAt(i);
        if (((Vector)NodC.getUserData()).elementAt(1) == Boolean.TRUE)
        {
          Vector VecC = NodC.getChildren();
          int iAic = ((Integer)((Vector)NodC.getUserData()).elementAt(0)).intValue();
          Qry.exec("delete from Abschluss_Zuordnung where aic_Benutzergruppe=" + iAic);
          for (int i2 = 0; VecC != null && i2 < VecC.size(); i2++)
          {
            Qry.add("AIC_Benutzergruppe", iAic);
            Qry.add("AIC_Abschlusstyp", (Integer)((JCOutlinerNode)VecC.elementAt(i2)).getUserData());
            Qry.insert("Abschluss_Zuordnung", false);
          }
          ((Vector)NodC.getUserData()).setElementAt(Boolean.FALSE,1);
        }
      }
      Qry.free();
      AClient.send_AServer("abschluss",g);
      g.sendWebChanged("periodenSperre");
  }

}
