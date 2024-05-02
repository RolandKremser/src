package All_Unlimited.Allgemein;

/**
 * <p>Überschrift: </p>
 * <p>Beschreibung: </p>
 * <p>Copyright: Copyright (c) 2.10.2003</p>
 * <p>Organisation: DVH</p>
 * @author Roland
 * @version 1.0
 */

 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JDialog;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.StammEingabe;
import javax.swing.WindowConstants;

public class SyncStamm extends Formular
{
  private static SyncStamm This=null;
  private Global g;

  private StammEingabe Cbo;
  private AUOutliner Out;
  private AUOutliner OutR;
  private AUOutliner OutBew;
  //private JButton BtnRefresh;
  //private JButton BtnSave;
  //private JButton BtnBeenden;

  public static SyncStamm start(Global rg,JDialog Dlg)
  {
    return This==null?new SyncStamm(rg,Dlg):This;
  }

  public static void free()
  {
        if (This!=null)
        {
                This.g.testInfo("SyncStamm.free");
                This.thisFrame.dispose();
                This=null;
        }
  }

  public void show()
  {
    Refresh();
    super.show();
  }

  private SyncStamm(Global rg,JDialog Dlg)
  {
    super("SyncStamm",Dlg,rg);
    g=rg;
    This=this;
    if(thisFrame instanceof JDialog)
      ((JDialog)thisFrame).setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    else
      ((javax.swing.JFrame)thisFrame).setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    Cbo=new StammEingabe(thisFrame,g);
    JPanel Pnl_Stt = getFrei("Stammtyp");
    if (Pnl_Stt != null)
      Pnl_Stt.add(Cbo);

    Out=new AUOutliner();
    Out.setRootNode(new JCOutlinerFolderNode(""));
    //Out.setColumnLabelSortMethod(Sort.sortMethod);
    Out.setRootVisible(false);
    //Out.setFont(g.fontBezeichnung);
    Out.setColumnButtons(new jclass.util.JCVector(new String[] {"Stammtyp","Stammsatz","Menge"}));
    Out.setNumColumns(3);
    //Out.setRootNode(new JCOutlinerFolderNode("Stammtyp - Stamm"));
    OutR=new AUOutliner();
    OutR.setRootNode(new JCOutlinerFolderNode(""));
    //OutR.setColumnLabelSortMethod(Sort.sortMethod);
    OutR.setRootVisible(false);
    //OutR.setFont(g.fontBezeichnung);
    OutR.setColumnButtons(new jclass.util.JCVector(new String[] {"Rolle","Stammsatz","Menge"}));
    OutR.setNumColumns(3);
    OutBew=new AUOutliner();
    OutBew.setRootNode(new JCOutlinerFolderNode(""));
    //OutBew.setColumnLabelSortMethod(Sort.sortMethod);
    OutBew.setRootVisible(false);
    //OutBew.setFont(g.fontBezeichnung);
    OutBew.setColumnButtons(new jclass.util.JCVector(new String[] {"Bewegungstyp","Pool"}));
    OutBew.setNumColumns(2);

    JPanel Pnl_Outliner = getFrei("Outliner");
    if (Pnl_Outliner != null)
      Pnl_Outliner.add(Out);
    JPanel Pnl_OutlinerR = getFrei("Outliner Rolle");
    if (Pnl_OutlinerR != null)
      Pnl_OutlinerR.add(OutR);
    JPanel Pnl_OutlinerBew = getFrei("Outliner Bewegung");
    if (Pnl_OutlinerBew != null)
      Pnl_OutlinerBew.add(OutBew);

    //Refresh();

    ActionListener AL=new ActionListener()
        {
          @SuppressWarnings("unchecked")
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
            if (s.equals("Beenden"))
              free();
            else
            {
              if (s.equals("Init") || s.equals("Save"))
              {
                int iStt = Cbo.getStt();
                if (iStt > 0)
                  if (s.equals("Save"))
                    g.setSyncStamm(iStt,Cbo.getStamm());
                  else
                    g.setSyncStamm(iStt,null);
              }
              else if (s.equals("Und"))
              {
                int iStt = Cbo.getStt();
                int iPos = iStt > 0 ? g.TabStammtypen.getPos("Aic", iStt) : -1;
                if (iPos >= 0)
                {
                  Vector<Integer> Vec = (Vector)g.TabStammtypen.getInhalt("Vec", iPos);
                  if (Vec == null)
                  {
                    Vec = new Vector<Integer>();
                    g.TabStammtypen.setInhalt(iPos, "Vec", Vec);
                  }
                  Vec.addElement(new Integer(Cbo.getStamm()));
                }
              }
              Refresh();
            }
          }
        };


    g.BtnAdd(getButton("Init"),"Init",AL);
    g.BtnAdd(getButton("Und"),"Und",AL);
    g.BtnAdd(getButton("Speichern"),"Save",AL);
    g.BtnAdd(getButton("Refresh"),"Refresh",AL);
    g.BtnAdd(getButton("Beenden"),"Beenden",AL);

    Out.addItemListener(new JCItemListener()
    {
      public void itemStateChanged(JCItemEvent ev)
      {
        Vector Vec=(Vector)Out.getSelectedNode().getUserData();
        if (Vec != null && Vec.size()>1)
        {
          Cbo.setStt(Sort.geti(Vec,0));
          Cbo.setStamm(Sort.geti(Vec,1));
        }
      }
    });

  }

  public void Refresh()
  {
    //Transact.fixInfo("Refresh");
    int iStt=Cbo.getStt();
    Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_stamm,bezeichnung from stammview2 where aic_stamm"+Static.SQL_in(g.TabStammtypen.getVecSpalte("Sync")),true);
    Tabellenspeicher Tab2=new Tabellenspeicher(g,"select aic_stamm,bezeichnung from stammview2 where aic_stamm"+Static.SQL_in(g.TabStammtypen.getVec2Spalte("Vec")),true);
    //Tab.showGrid("Stamm");
    //g.TabStammtypen.showGrid("Stt");
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
    //StyFolder.setFont(g.fontStandard);
    NodeRoot.removeChildren();
    //for(g.TabStammtypen.moveFirst();!g.TabStammtypen.eof();g.TabStammtypen.moveNext())
    JCOutlinerNode NodMom=null;
    for(int iPos=0;iPos<g.TabStammtypen.size();iPos++)
    {
      int iStamm=g.TabStammtypen.getI(iPos,"Sync");
      if(iStamm>0 || !g.TabStammtypen.isNull(iPos,"Vec"))
      {
        //Vector VecV=new Vector();
        //VecV.addElement(g.TabStammtypen.getS(iPos,"Bezeichnung"));
        String sSatz=iStamm>0 && Tab.posInhalt("Aic_Stamm",iStamm)?Tab.getS("Bezeichnung"):""+iStamm;
        Vector Vec=(Vector)g.TabStammtypen.getInhalt("Vec",iPos);
        String sVec=Vec==null || Vec.size()==0 ? "":Tab2.posInhalt("Aic_Stamm",Vec.elementAt(0))?Tab2.getS("Bezeichnung"):"";
        if (Vec!=null)
          for(int i=1;i<Vec.size();i++)
            sVec+=Tab2.posInhalt("Aic_Stamm",Vec.elementAt(i))?","+Tab2.getS("Bezeichnung"):"";
        //VecV.addElement(Tab.getS("Bezeichnung"));
        JCOutlinerNode Nod = new JCOutlinerNode(new jclass.util.JCVector(new String[] {g.TabStammtypen.getS(iPos,"Bezeichnung"),sSatz,sVec}),NodeRoot);
        Nod.setStyle(StyFolder);
        if (g.TabStammtypen.getI(iPos,"AIC")==iStt)
          NodMom=Nod;
        Nod.setUserData(new jclass.util.JCVector(new Object[] {g.TabStammtypen.getInhalt("AIC",iPos),iStamm>0?Tab.getInhalt("Aic_Stamm"):new Integer(0)}));
      }
    }
    if (NodMom != null)
      Static.makeVisible(Out,NodMom);
    else
      Out.folderChanged(NodeRoot);
    JCOutlinerFolderNode NodeRolRoot = (JCOutlinerFolderNode)OutR.getRootNode();
    NodeRolRoot.removeChildren();
    for(int iPos=0;iPos<g.TabRollen.size();iPos++)
    {
      int iSync=g.TabRollen.getI(iPos,"Sync");
      if (iSync>0)
      {
        JCOutlinerNode Nod = new JCOutlinerNode(new jclass.util.JCVector(new Object[] {g.TabRollen.getS(iPos,"Bezeichnung"),g.getStamm(iSync)}),NodeRolRoot);
        Nod.setStyle(StyFolder);
      }
    }
    OutR.folderChanged(NodeRolRoot);
    JCOutlinerFolderNode NodeBewRoot = (JCOutlinerFolderNode)OutBew.getRootNode();
    NodeBewRoot.removeChildren();
    for(int iPos=0;iPos<g.TabErfassungstypen.size();iPos++)
    {
      int iPool=g.TabErfassungstypen.getI(iPos,"Pool");
      if (iPool>0)
      {
        JCOutlinerNode Nod = new JCOutlinerNode(new jclass.util.JCVector(new Object[] {g.TabErfassungstypen.getS(iPos,"Bezeichnung"),new Integer(iPool)}),NodeBewRoot);
        Nod.setStyle(StyFolder);
      }
    }
    OutBew.folderChanged(NodeBewRoot);
  }

}
