package All_Unlimited.Grunddefinition;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.WWW;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;


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
public class DefHtml extends Formular
{
  private Global g;
  private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
  private Text TxtBezeichnung = new Text("",255);
  private Text TxtKennung1 = new Text("",40,Text.KENNUNG);
  //private Text TxtKennung2 = new Text("",20,Text.KENNUNG);
  private WWW TxtURL;
  private AUCheckBox CbxJeder;
  //private int iAktAIC=0;
  private static int iAktBeg=0;
  private int iBG_Web;
  private BildEingabe BtnBild;
  private static DefHtml Self=null;

  public static DefHtml get(Global g,int riBew)
     {
       iAktBeg=riBew;
       if (Self==null)
         new DefHtml(g);
       else
         Self.show();
       return Self;
   }

   public static void free()
   {

     if (Self != null)
     {
       Self.g.winInfo("DefHtml.free");
       Self.thisFrame.dispose();
       Self = null;
     }
  }

  private DefHtml(Global glob)
  {
    super("DefHTML", null, glob);
    Self = this;
    g = glob;
    Self.g.winInfo("DefHTML.create");
    Build();
    show();
  }

  private void Build()
  {
    iBG_Web=g.TabBegriffgruppen.getAic("Web");
    TxtURL = new WWW(g);
    CbxJeder=g.getCheckbox("Jeder");
    BtnBild = new BildEingabe((JFrame)thisFrame,g);
    BtnBild.activateEvent();

    g.initOutliner(Out,new String[] {"Bezeichnung","Kennung","URL","Jeder","Change","Anzahl","Filename","Nr"});
    JPanel PnlOutliner = getFrei("Outliner");
    JPanel PnlEingabe = getFrei("Eingabe");
    PnlOutliner.add(Out);
    PnlEingabe.setLayout(new BorderLayout(2, 2));
    JPanel Pnl = new JPanel(new GridLayout(0, 1, 2, 2));
    g.addLabel2(Pnl, "Bezeichnung");
    g.addLabel2(Pnl, "Kennung");
    //g.addLabel2(Pnl, "Kennung2");
    g.addLabel2(Pnl, "URL");
    Pnl.add(new JPanel());
    g.addLabel2(Pnl,"Bild");
    PnlEingabe.add("West", Pnl);
    Pnl = new JPanel(new GridLayout(0, 1, 2, 2));
    g.addComp(Pnl, TxtBezeichnung);
    g.addComp(Pnl, TxtKennung1);
    //g.addComp(Pnl, TxtKennung2);
    g.addComp(Pnl, TxtURL);
    g.addComp(Pnl, CbxJeder);
    Pnl.add(BtnBild);
    PnlEingabe.add("Center", Pnl);

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
          Out.selectNode(Out.getRootNode(),null);
        else if (s.equals("Speichern"))
          BewSave();
        else if (s.equals("Loeschen"))
          BewDel();
        else if (s.equals("show"))
        {
          s = "select begriff.defbezeichnung"+g.AU_Bezeichnung1("bewegungstyp","begriff")+" Bewegungstyp"+g.AU_Bezeichnung1("stammtyp","begriff")+
              " Stammtyp,begriff.aic_begriff from begriff"+g.join2("formular","begriff")+g.join2("darstellung","formular")+" where darstellung.aic_begriff="+iAktBeg;
          new Tabellenspeicher(g,s,true).showGrid(TxtURL.getValue());
        }
        else if (s.equals("Refresh"))
          FillOutliner();
        else if (s.equals("Beenden"))
          hide();
      }
    };
    g.BtnAdd(getButton("Neu"),"Neu",AL);
    g.BtnAdd(getButton("Speichern"),"Speichern",AL);
    g.BtnAdd(getButton("Loeschen"),"Loeschen",AL);
    g.BtnAdd(getButton("show"),"show",AL);
    g.BtnAdd(getButton("Refresh"),"Refresh",AL);
    g.BtnAdd(getButton("Beenden"),"Beenden",AL);
  }

  public void show()
  {
    FillOutliner();
    super.show();
    setSelected();
  }

  private void FillOutliner()
  {
          SQL Qry = new SQL(g);
          JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
          NodeRoot.removeChildren();
          JCOutlinerNode NodSelect=null;
          JCOutlinerNodeStyle Sty=g.getStyle(g.LoadImage(g.TabBegriffgruppen.getInfoS(iBG_Web,"Filename")));

          if(Qry.open("select kennung,defbezeichnung,aic_begriff,jeder,homepage"+
                      ",(SELECT Filename FROM Daten_Bild AUI WHERE aic_tabellenname="+g.iTabBegriff+" AND AUI.AIC_Fremd=B.AIC_BEGRIFF AND AUI.AIC_Zustand="+g.iSpSw+") Filename"+
                      ",(select ein from logging where aic_logging=b.aic_logging) log"+
                      ",(select count(*) from darstellung where aic_begriff=b.aic_begriff) Anzahl"+
                      " from begriff b where aic_begriffgruppe="+iBG_Web))
          {
              for(;!Qry.eof();Qry.moveNext())
              {
                      Vector<Comparable> VecVisible=new Vector<Comparable>();
                      Vector<Comparable> VecInvisible=new Vector<Comparable>();

                      VecVisible.addElement(Qry.getS("defbezeichnung"));
                      VecVisible.addElement(Qry.getS("kennung"));
                      VecVisible.addElement(Qry.getS("homepage"));
                      VecVisible.addElement(Static.JaNein2(Qry.getB("Jeder")));
                      VecVisible.addElement(Qry.getD("log"));
                      VecVisible.addElement(Qry.getInt("Anzahl"));
                      VecVisible.addElement(Qry.getS("Filename"));
                      VecVisible.addElement(Qry.getInt("aic_begriff"));
                      VecInvisible.addElement(Qry.getB("Jeder")?1:0);
                      VecInvisible.addElement(Qry.getInt("aic_begriff"));
                      VecInvisible.addElement(Qry.getS("Filename"));

                      JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodeRoot);
                      Nod.setUserData(VecInvisible);
                      Nod.setStyle(Sty);
                      //Image Gif = g.TabErfassungstypen.posInhalt("Aic",Qry.getInt("aic_bewegungstyp"))? (Image)g.TabErfassungstypen.getInhalt("Bild"):null;
                      //Nod.setStyle(g.getStyle(Gif));
                      if (iAktBeg==Qry.getI("aic_begriff"))
                        NodSelect=Nod;//Out.selectNode(Nod,null);
              }
              Qry.close();
          }
          Qry.free();

          //Out.folderChanged(NodeRoot);
          Static.makeVisible(Out,NodSelect);
  }

  private void setSelected()
    {
      JCOutlinerNode Node=Out.getSelectedNode();
      if(Node!=null&&Node.getLevel()==1)
      {
        //iAktAIC = Sort.geti((Vector)Node.getUserData(), 0);
        iAktBeg = Sort.geti((Vector)Node.getUserData(), 1);
      }
      else
      {
        //iAktAIC = 0;
        iAktBeg = 0;
      }
      g.testInfo("iAktBeg="+iAktBeg);
      if(iAktBeg>0)
      {

        Vector VecVisible=(Vector)Node.getLabel();
        //Vector VecInvisible=(Vector)Node.getUserData();
        TxtBezeichnung.setText((String)VecVisible.elementAt(0));
        TxtKennung1.setText((String)VecVisible.elementAt(1));
        //TxtKennung2.setText((String)VecVisible.elementAt(2));
        TxtURL.setValue((String)VecVisible.elementAt(2));
        CbxJeder.setSelected(Sort.geti((Vector)Node.getUserData(), 0)==1);
        String sBild=(String)((Vector)Node.getUserData()).elementAt(2);
        g.testInfo("sBild="+sBild);
        if (sBild==null || sBild.equals(""))
        {
          BtnBild.Delete();
          BtnBild.Reset();
        }
        else
          BtnBild.setValue(g.LoadImage(sBild),sBild);

        setTitle(sFormularBezeichnung+" - "+(VecVisible.elementAt(0)));
      }
      else
      {
        TxtBezeichnung.setText("");
        TxtKennung1.setText("");
        //TxtKennung2.setText("");
        TxtURL.setValue("");
        BtnBild.Delete();
        BtnBild.Reset();
        CbxJeder.setSelected(true);
      }
      TxtKennung1.setEnabled(iAktBeg==0);
      //TxtKennung2.setEnabled(iAktAIC==0);
      g.progInfo("DefHtml.setSelected="+iAktBeg);
  }

  private void BewSave()
  {
    //int iBtn=g.TabBegriffgruppen.getAic("Button");
    g.testInfo("BewSave:"+iBG_Web+"/"+iAktBeg);
    if (TxtKennung1.isNull())
    {
      new Message(Message.ERROR_MESSAGE, (JFrame)thisFrame, g,10).showDialog("KennungLeer");
      return;
    }
    if(SQL.exists(g,"select aic_begriff from begriff WHERE aic_begriff<> "+iAktBeg+" and Kennung='"+TxtKennung1.getText()+"'"))
    {
      new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g,10).showDialog("KennungVorhanden");
      return;
    }
    SQL Qry = new SQL(g);
    Qry.add("DefBezeichnung",TxtBezeichnung.getText());
    Qry.add("Kennung",TxtKennung1.getText());
    Qry.add("Homepage",TxtURL.getValue());
    Qry.add("Jeder",CbxJeder.isSelected());
    Qry.add("aic_begriffgruppe",iBG_Web);
    Qry.add("aic_logging",g.getLog());
    boolean bNew=iAktBeg==0;
    if (bNew)
      iAktBeg=Qry.insert("begriff",true);
    else
      Qry.update("begriff",iAktBeg);
    Qry.free();
    if (BtnBild.Modified())
       g.setImage(BtnBild.getFilename(),g.iTabBegriff,iAktBeg,Global.iSpSw);

    g.putTabBegriffe(iBG_Web,iAktBeg,TxtKennung1.getText(),TxtBezeichnung.getText(),TxtBezeichnung.getText(),null,0,TxtURL.getValue(),-1,-1,-1,-1,-1,-1,false,false,0,null,false,null,null,null,null,null,bNew);
    FillOutliner();
  }

  private void BewDel()
  {
    int iMessage=new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Loeschen",new String[]{TxtBezeichnung.getText()});
    if(iMessage==Message.YES_OPTION)
    {
      g.bISQL = true;
      g.exec("delete from begriff where aic_begriff=" + iAktBeg);
      g.bISQL = false;
      if (g.sError == null)
      {
        FillOutliner();
      }
      else
        new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g, 10).showDialog("BereitsVerwendet", new String[]
            {TxtURL.getValue()});
    }
  }


}
