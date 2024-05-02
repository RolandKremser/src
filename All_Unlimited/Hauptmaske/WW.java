package All_Unlimited.Hauptmaske;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import java.awt.FlowLayout;

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
public class WW extends JDialog
{
  /**
	 *
	 */
	private static final long serialVersionUID = 6392812379707442323L;
private Global g;
  private static WW This=null;
  private FileEingabe EdtFile;
  private Datum EdtDate;
  private JButton BtnShow;
  private JButton BtnDate;
  private JButton BtnImport;
  private JButton BtnBeenden;

  public static WW start(Global rg,JFrame Fom)
  {
    return This==null?new WW(rg,Fom):This;
  }

  private WW(Global rg,JFrame Fom)
  {
          super(Fom,"WW",true);
          g = rg;
          build(Fom);
          if(Fom!=null)
            Static.centerComponent(this,Fom);
          This=this;
  }

  public void show2()
  {
    super.setVisible(true);
  }

  public static void free()
  {
    if (This != null)
    {
      This.g.testInfo("WW.free");
      This.dispose();
      This = null;
    }
  }

  private void build(final JFrame Fom)
  {
    EdtFile = new FileEingabe(g);
    EdtDate = new Datum(g,"dd.MM.yyyy");

    loadParameter();
    JPanel PnlN=new JPanel(new BorderLayout());
    JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,3,2));
    JPanel PnlW=new JPanel(new GridLayout(0,1));
    JPanel PnlC=new JPanel(new GridLayout(0,1));
    //g.addLabel4(PnlW,"File");
    //PnlC.add(EdtFile);
    g.addN("File",EdtFile,PnlW,PnlC);
    //g.addLabel4(PnlW,"Datum");
    //PnlC.add(EdtDate);
    g.addN("Datum",EdtDate,PnlW,PnlC);
    PnlN.add("Center",PnlC);
    PnlN.add("West",PnlW);
    getContentPane().add("North",PnlN);
    getContentPane().add("South",PnlS);
    BtnShow=g.getButton("show");
    BtnDate = g.getButton("Date");
    BtnImport = g.getButton("Import");
    BtnBeenden = g.getButton("Beenden");
    PnlS.add(BtnShow);
    PnlS.add(BtnDate);
    PnlS.add(BtnImport);
    PnlS.add(BtnBeenden);
    pack();

    BtnShow.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
                showWW();
        }
    });

    BtnDate.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
                EdtDate.setDate(getDate());
        }
    });

    BtnImport.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
                importiere();
        }
    });

    BtnBeenden.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
          saveParameter();
          setVisible(false);
        }
    });

  }

  private Date getDate()
  {
    try
    {
      Date dt=null;
      BufferedReader Buf = new BufferedReader(new FileReader(new File(EdtFile.getValue())));
      String s=Buf.readLine();
      while (s != null)
      {
        //g.progInfo(s);
        if (s.indexOf("Date:")>-1)
        {
          s=s.substring(s.indexOf("Date:") + 5,s.lastIndexOf(" "));
          s=s.substring(0,s.lastIndexOf(" "));
          int iJahr=Integer.parseInt(s.substring(s.indexOf(", ")+2));
          int iTag=Integer.parseInt(s.substring(s.indexOf(" ")+1,s.indexOf(", ")));
          int iMonat=0;
          String[] sM=new String[] {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
          for (int i=0;i<sM.length;i++)
            if (s.startsWith(sM[i]))
              iMonat=i+1;
          //java.util.Date dt=new SimpleDateFormat("MMMM dd, yyyy").parse(s.substring(0,s.lastIndexOf(" ")),new ParsePosition(0));
          dt=new DateWOD(iJahr,iMonat,iTag).toTimestamp();
          g.fixInfo(s+"/"+iTag+"/"+iJahr);
        }
        s = Buf.readLine();
      }
      Buf.close();
      return dt;
    }
    catch(Exception io)
    {
            Static.printError("WW.getDate(): Exception - "+io);
            return null;
    }
  }

  private void importiere()
  {
    Date dt=getDate();
    SQL Qry=new SQL(g);
    try
    {
      boolean bKurse=false;
      int iPos=0;
      String sVon="";
      String sBis="";
      double d=0;
      BufferedReader Buf = new BufferedReader(new FileReader(new File(EdtFile.getValue())));
      String s=Buf.readLine();
      while (s != null)
      {
        if (s.equals("<TABLE BORDER  CELLPADDING=2>"))
          bKurse=true;
        if (bKurse && s.startsWith("<TD BG"))
          iPos++;
        else
          iPos=0;
        if (iPos==1)
          sVon=s.substring(33,36);
        else if (iPos==2)
          sBis=s.substring(33,36);
        else if (iPos==3)
        {
          d=new Double(s.substring(33,s.indexOf("<",33))).doubleValue();
          int iAic=0;
          if (sVon.equals("EUR"))
          {
            if (g.TabWaehrung.posInhalt("Kennung", sBis))
              iAic = g.TabWaehrung.getI("aic_stamm");
            if (iAic>0)
            {
              DateWOD DW=new DateWOD(dt);
              Tabellenspeicher Tab=new Tabellenspeicher(g,"select * from ww where aic_stamm="+iAic+" order by Datum",true);
              if (Tab.isEmpty())
                for (int iWW=0; iWW<366;iWW++)
                {
                  String sDatum = g.DateTimeToString(DW.toDate());
                  Qry.add2("Datum", sDatum);
                  Qry.add("aic_stamm", iAic);
                  Qry.add("Faktor", d);
                  Qry.add("BITS", iWW == 0 ? 1 : 0);
                  Qry.insert("WW", false);
                  DW.tomorrow();
                }
              else
              {
                boolean bWeiter=true;
                for (int iWW=0; bWeiter && iWW<366;iWW++)
                {
                  String sDatum = g.DateTimeToString(DW.toDate());
                  int iAicWW = Tab.posInhalt("Datum",DW.toTimestamp()) ? Tab.getI("AIC_WW"):0;
                  if (iWW==0 || iAicWW==0 || (Tab.getI("Bits")&1)==0)
                  {
                    Qry.add2("Datum", sDatum);
                    Qry.add("aic_stamm", iAic);
                    Qry.add("Faktor", d);
                    Qry.add("BITS", iWW == 0 ? 1 : 0);
                    if (iAicWW > 0)
                      Qry.update("WW", iAicWW);
                    else
                      Qry.insert("WW", false);
                  }
                  else
                    bWeiter=false;
                  DW.tomorrow();
                }
              }
              g.fixInfo(sVon + " -> " + sBis + "/" + iAic + " : " + d);
            }
          }
        }
        s = Buf.readLine();
      }
      Buf.close();
    }
    catch(Exception io)
    {
            Static.printError("WW.importiere(): Exception - "+io);
    }
    Qry.free();
  }

  private void loadParameter()
  {
    Parameter Para=new Parameter(g,"WW");
    String s=Para.getParameter("Option",false,true);
    if (Para.bGefunden)
    {
      EdtFile.getFileEditor().setText(s);
    }
    Para.free();
  }

  private void saveParameter()
  {
    Parameter Para=new Parameter(g,"WW");
    Para.setParameter("Option",EdtFile.getFileEditor().getText(),0,0,0,0,false,true);
    Para.free();
  }


  private void showWW()
  {
    new Tabellenspeicher(g,"select datum,faktor,bezeichnung Einheit,bits Fix from ww"+g.join("stammview2","ww","stamm"),true).showGrid("Wechselkurse",This);
  }

}
