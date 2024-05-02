package All_Unlimited.Grunddefinition;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
//import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
//import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
//import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Hauptmaske.Zeitraum;
import All_Unlimited.Hauptmaske.Import;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Vector;

import All_Unlimited.Allgemein.Anzeige.Zeit;

import java.awt.BorderLayout;

public class ISQL extends Formular
{
  private Global g;
  private Global t;
  private AUTextArea Edt = new AUTextArea();
  private JTextArea Error = new JTextArea();
  private Text EdtStamm=new Text("",30);
  private FileEingabe EdtFilename;
  private JButton BtnReset;
  private JButton BtnBack;
  private JButton BtnWeiter;
  private JButton BtnShow;
  private JButton BtnBeenden;
  private JButton BtnInt;
  private int iMom=0;
  private int iMax=0;
  //private Vector Vec=new Vector();
  private Tabellenspeicher Tab;
  private Tabellenspeicher TabISQL;
  boolean bOK=false;
  //boolean bAbbruch=false;
  boolean bExtern=false;
  public static ISQL Self=null;
  private boolean bMemo=false;
  private boolean bBild=false;

  public static ISQL get(Global g,java.awt.Window w,boolean rbExtern)
  {
    if (Self==null)
      new ISQL(g,w,rbExtern);
    else
      Self.show();
    return Self;
  }

  public static void free()
  {
    if (Self != null)
    {
    	Self.g.winInfo("ISQL.free");   	  
        Self.thisFrame.dispose();
        Self=null;
    }
  }

  private ISQL(Global rg,java.awt.Window w,boolean rbExtern)
  {
    super("ISQL", null, rg);
    g = rg;
    g.winInfo("ISQL.create");
    t=g;
    bExtern=rbExtern;
    bOK=false;
    if (!bExtern && g.Prog())
      bOK=true;
    else if (bExtern || g.Def())
    {
    	bOK=Message.SecCheck(g,w,"ISQL-Check","wegen M$");
            /*final JDialog Dlg=new JDialog(new JFrame(),"Sicherheits-Frage",true);
            Dlg.getContentPane().add("North",new JLabel("Warum benutzen?"));
            final JPasswordField EdtPW = new JPasswordField();
            JButton BtnOk = g.getButton("Ok");
            JButton BtnAbbruch = g.getButton("Abbruch");
            Dlg.getRootPane().setDefaultButton(BtnOk);
            BtnOk.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev)
                {
                  bOK=new String(EdtPW.getPassword()).equals("wegen M$");
                  Dlg.setVisible(false);
                }
            });
            BtnAbbruch.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev)
                {
                  //bAbbruch=true;
                  Dlg.dispose();
                }
            });
            JPanel PnlS=new JPanel(new GridLayout());
            PnlS.add(BtnOk);
            PnlS.add(BtnAbbruch);
            Dlg.getContentPane().add("Center",EdtPW);
            Dlg.getContentPane().add("South",PnlS);
            Dlg.pack();
            Static.centerComponent(Dlg,w);
            Dlg.setVisible(true);*/
    }
    /*if (bAbbruch)
      return;
    else*/ if (!bOK)
    {
            //new Message(Message.WARNING_MESSAGE,(JFrame)w,g).showDialog("keineBerechtigung");
            if (bExtern)
            {
              g.unConnect();
              System.exit(0);
            }
            thisFrame.dispose();
            return;
    }
    Self=this;
    Tab=new Tabellenspeicher(g,new String[] {"Nr","Statement","Error"});
    TabISQL=new Tabellenspeicher(g,new String[] {"Uhrzeit","Dauer","Error","Anzahl","Statement"});
    JPanel PnlTextfeld = getFrei("Textfeld");
    PnlTextfeld.add(Edt);
    //Edt.setWrapStyleWord(true);
    JPanel PnlError = getFrei("Label");
    JPanel PnlStamm = getFrei("Eingabe");
    JPanel PnlFilename = getFrei("Filename");
    if (PnlStamm != null)
      PnlStamm.add(EdtStamm);
    if (PnlFilename != null)
    {
      EdtFilename=new FileEingabe(g);
      PnlFilename.add(EdtFilename);
    }
    PnlError.setLayout(new BorderLayout(0, 0));
    PnlError.add("Center",Error);

    ActionListener AL = new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String s = e.getActionCommand();
        g.progInfo("ISQL.Action=" + s);
        if (s.equals("Zeitraum"))
          Zeitraum.get(g).show();
        else if (s.equals("Clear"))
          Edt.setText("");
        else if (s.equals("show TabISQL"))
          TabISQL.showGrid("TabISQL",thisFrame);
        else if (s.equals("DB"))
          changeDB();
        else if (s.equals("Reset"))
        {
          t.disconnect();
          t=g;
          setTitle("ISQL");
          BtnReset.setEnabled(false);
        }
        else if (s.equals("Back"))
        { iMom--;showMom(); }
        else if (s.equals("Weiter"))
        { iMom++;showMom(); }
        else if (s.equals("Int"))
        {
        	BtnInt.setText(""+SQL.getInteger(g, Edt.getValue(), -1));
        }
        else if (s.equals("Memo"))
        {
        	bMemo=true;
        	execSQL(Edt.getValue(),false);
        	bMemo=false;
        }
        else if (s.equals("Bild"))
        {
        	bBild=true;
        	execSQL(Edt.getValue(),false);
        	bBild=false;
        }
        else if (s.equals("Import"))
        	Import();
      }
    };

    JButton BtnTab=g.BtnAdd(g.getButton("show"),"show TabISQL",AL);
    PnlError.add("East",BtnTab);
    Error.setEditable(false);
    //BtnBack = getButton("<");
    //BtnWeiter = getButton(">");
    BtnShow = getButton("show");
    BtnBeenden = getButton("Beenden");
    BtnReset = getButton("Zurueck");
    if (BtnReset==null) BtnReset=new JButton();
    BtnReset.setEnabled(false);
    g.BtnAdd(getButton("Zeitraum"),"Zeitraum",AL);
    g.BtnAdd(getButton("Clear"),"Clear",AL);
    g.BtnAdd(getButton("DB"),"DB",AL);
    g.BtnAdd(BtnReset,"Reset",AL);
    BtnBack=g.BtnAdd(getButton("<"),"Back",AL);
    BtnWeiter=g.BtnAdd(getButton(">"),"Weiter",AL);
    BtnInt=getButton("Int");
    g.BtnAdd(BtnInt,"Int",AL);
    g.BtnAdd(getButton("Memo"),"Memo",AL);
    g.BtnAdd(getButton("Bild"),"Bild",AL);
    g.BtnAdd(getButton("Import"),"Import",AL);
    
    BtnShow.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent e)
            {
              new Thread(new Runnable()
              {
                public void run()
                {
                  try
                  {
                    BtnShow.setEnabled(false);
                    BtnBack.setEnabled(false);
                    BtnWeiter.setEnabled(false);
                    Edt.setEnabled(false);
                    if (EdtFilename == null || EdtFilename.getValue().equals(""))
                      execSQL(Edt.getValue(),false);
                    else
                    {
                      try
                      {
                        java.net.URL url=new java.net.URL("file:/"+EdtFilename.getValue());
                        InputStream is=(InputStream)url.getContent();
                        //DataInputStream Buf=new DataInputStream(is);
                        BufferedReader Buf=new BufferedReader(new InputStreamReader(is));
                        String sR=Buf.readLine();
                        String s=null;
                        while (sR != null)
                        {
                          if (!sR.startsWith("/*"))
                            s=s==null ? sR:s+" "+sR;
                          if (s!=null && s.endsWith(";"))
                          {
                            execSQL(s,true);
                            s=null;
                          }
                          sR = Buf.readLine();
                        }
                        Buf.close();
                        TabISQL.showGrid("TabISQL",thisFrame);
                      }
                      catch(IOException io)
                      {
                        Static.printError("ISQL.execute(): IOException - "+io);
                      }
                    }
                    Edt.setEnabled(true);
                    EnableButtons();
                    BtnShow.setEnabled(true);
                  }
                  catch(Exception e) { Static.printError("ISQL-Fehler:" + e); }
                }
              }).start();
            }
    });
    BtnBeenden.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent e)
            {
              if (bExtern)
              {
                g.unConnect();
                System.exit(0);
              }
              else
                hide();
            }
    });
    EnableButtons();
    show();
  }

  private void execSQL(String s,boolean bError)
  {
    iMax++;
                    iMom = iMax - 1;
                    Tab.addInhalt("Nr", iMom);
                    Tab.addInhalt("Statement", s);
                    TabISQL.addInhalt("Uhrzeit",new Zeit(new java.util.Date(),"dd.MM.yyyy HH:mm:ss"));
                    TabISQL.addInhalt("Statement", s.replaceAll("\n"," "));
                    t.bISQL = true;
                    t.sError = null;
                    long lSQL = t.getSqlms();
                    //g.progInfo("SQL_Zeit1:"+lSQL);
                    long lClock = Static.get_ms();
                    int iAnz = -1;
                    if (bMemo)
                    {
                    	String sMemo=SQL.getString(g, s);
                    	AUTextArea Txt=new AUTextArea(g,8);
    					Txt.setText(sMemo);
    					JDialog DlgMemo=new JDialog(thisFrame,s);
    					DlgMemo.getContentPane().add(Txt, "Center");
    					DlgMemo.pack();
    					Static.centerComponent(DlgMemo, thisFrame);
    					DlgMemo.setVisible(true);
                    }
                    if (bBild)
                    {
                    	JDialog DlgBild=new JDialog(thisFrame,s);
    					String sFile=SQL.getString(g, s);
                    	JLabel LblBild=new JLabel("");
                    	JLabel LblBild2=new JLabel("");
                    	int iDaten=Integer.parseInt(sFile.substring(0,sFile.indexOf("_")));
                    	Image Img=null;
                    	Image Img2=null;
                    	s=s.toLowerCase();
                    	if (s.indexOf(" daten_bild2")>0)
                    	{
                    		Img=SQL.getImage(g, iDaten);
                    		Img2=SQL.getImage(g, iDaten,true);
                    	}
                    	else if (s.indexOf(" daten_doku")>0)
                    		Img=SQL.getImageD(g, iDaten);
                    	else if (s.indexOf(" zeile")>0)
                    		Img=SQL.getImageZ(g, iDaten);
                    	else if (s.indexOf(" daten_bild")>0)
                    		Img=SQL.getImageA(g, iDaten);
                    	if (Img!=null)
                    	{              		
                    		LblBild.setIcon(new ImageIcon(Img));
                    		if (Img2!=null)
                    			LblBild2.setIcon(new ImageIcon(Img2));
                    		JScrollPane PnlSP=new JScrollPane(LblBild);
                    		DlgBild.getContentPane().add(PnlSP, "Center");
                    		JPanel Pnl=new JPanel(new GridLayout(2, 1));
                    		 Pnl.add(LblBild2);
                    		 if (s.indexOf("daten_bild2")>0)
                    		 {
	                    		 BufferedImage Img3=Import.resizeImage(Static.imageToBufferedImage(sFile,Img), 150);//Import.ImageToMini(g,iDaten,Img,sFile,true);
	                    		 if (Img3 !=null)
	                    			 Pnl.add(new JLabel(new ImageIcon(Img3)));
                    		 }
                    		DlgBild.getContentPane().add(Pnl, "East");                  		
                    	}
    					DlgBild.getContentPane().add(new JLabel(sFile), "South");
    					DlgBild.pack();
    					Static.centerComponent(DlgBild, thisFrame);
    					DlgBild.setVisible(true);
                    }
                    else if(s.toLowerCase().startsWith("select") || s.toLowerCase().startsWith("call") || s.toLowerCase().startsWith("show") || s.toUpperCase().startsWith("OPTIMIZE") || s.toLowerCase().startsWith("sp_")
                       || s.toLowerCase().startsWith("explain") || s.toLowerCase().startsWith("desc")) {
                      Tabellenspeicher Tab = new Tabellenspeicher(t, s,EdtStamm.getText(), true);
                      if(Tab != null) {
                    	if (!Tab.fehler())
                    		Tab.showGrid(iMax+": "+s,thisFrame);
                        iAnz = Tab.fehler() ? -1 : Tab.getAnzahlElemente(null);
                      }
                    }
                    //else if (s.toLowerCase().startsWith("call"))
                    //  new Tabellenspeicher(g,s,false).showGrid("Call-Statement "+iMax);
                    else if (s.equals("start"))
                      t.start();
                    else if (s.equals("commit"))
                      t.commit();
                    else if (s.equals("rollback"))
                      t.rollback();
                    else
                      iAnz = t.exec(s);
                    lClock = Static.get_ms() - lClock;
                    t.bISQL = false;
                    //g.progInfo("SQL_Zeit2:"+g.getSqlms());
                    if (bError && t.sError != null)
                      Static.printError(s);
                    TabISQL.addInhalt("Error",t.sError);
                    Zahl1 Z=new Zahl1((t.getSqlms() - lSQL) / 1000.0, "0.000");
                    TabISQL.addInhalt("Dauer",Z);
                    TabISQL.addInhalt("Anzahl",iAnz);
                    s = (t.sError == null ? "ok" : t.sError) + "\n" + (iMom + 1) + ": SQL=" + Z +
                        " s, Gesamt=" + new Zahl1(lClock / 1000.0, "0.000") + " s: " + iAnz + " Zeilen";
                    if (bError && t.sError != null)
                      Static.printError(t.sError);
                    Tab.addInhalt("Error", s);
                    Error.setText(s);

  }
  
  private void Import()
  {
	  Tabellenspeicher Tab=new Tabellenspeicher(g,Edt.getValue(),true);
	  Tab.readFile(';', EdtFilename.getValue(), true);
	  Tab.showGrid(EdtStamm.getText());
	  SQL Qry=new SQL(g);
	  Vector<String> Vec=Tab.VecTitel;
	  int iFehler=0;
	  int iOk=0;
	  for (Tab.moveFirst();!Tab.out();Tab.moveNext())
	  {
		  for (int i=0;i<Vec.size();i++)
		  {
			  String sSp=Vec.elementAt(i);
			  Qry.add(sSp, Tab.getInhalt(sSp));
		  }
		  if (Qry.insert(EdtStamm.getText(), false)<0)
			  iFehler++;
		  else
			  iOk++;
	  }
	  String s=iOk+" importiert, "+iFehler+" fehlerhaft";
	  Error.setText(s);
	  g.fixtestInfo(s);
  }

  private void changeDB()
  {
    final JDialog Dlg=new JDialog((JFrame)thisFrame,"andere Datenbank",true);
    JPanel PnlW=new JPanel(new GridLayout(0,1));
    g.addLabel4(PnlW,"JDBC");
    g.addLabel4(PnlW,"Name");
    g.addLabel4(PnlW,"PW");
    PnlW.add(new JLabel());

    JPanel PnlC=new JPanel(new GridLayout(0,1));
    final Text EdtJDBC=new Text("",60);
    final Text EdtName=new Text("",20);
    final AUPasswort EdtPW=new AUPasswort();
    final AUCheckBox CbxAll=g.getCheckbox("All Unlimited");
    g.addComp(PnlC,EdtJDBC);
    g.addComp(PnlC,EdtName);
    g.addComp(PnlC,EdtPW);
    g.addComp(PnlC,CbxAll);

    JPanel PnlS=new JPanel(new GridLayout(1,0,2,2));
    JButton BtnOk = g.getButton("Ok");
    JButton BtnAbbruch = g.getButton("Abbruch");
    Dlg.getRootPane().setDefaultButton(BtnOk);
    PnlS.add(BtnOk);
    PnlS.add(BtnAbbruch);
    Dlg.getContentPane().add("West",PnlW);
    Dlg.getContentPane().add("Center",PnlC);
    Dlg.getContentPane().add("South",PnlS);
    BtnOk.addActionListener(new ActionListener()
    {
              public void actionPerformed(ActionEvent ev)
              {
        	t=new Global();
        	boolean b=false;
        	if (CbxAll.isSelected())
        	  b=t.connect(EdtJDBC.getText());
        	else
        	  b=t.connect3(EdtJDBC.getText(), EdtName.getText(), new String(EdtPW.getPassword()));
        	if (b)
        	{
        	  setTitle("ISQL für "+EdtJDBC.getText());
        	  BtnReset.setEnabled(true);
        	}
        	else
        	  t=g;
                Dlg.dispose();
              }
    });
    BtnAbbruch.addActionListener(new ActionListener()
    {
              public void actionPerformed(ActionEvent ev)
              {
                      Dlg.dispose();
              }
    });
    Dlg.pack();
    Static.centerComponent(Dlg,thisFrame);
    Dlg.setVisible(true);
  }

  private void showMom()
  {
    EnableButtons();
    Tab.posInhalt("Nr",iMom);
    Edt.setText(Tab.getS("Statement"));
    Error.setText(Tab.getS("Error"));
  }

  private void EnableButtons()
  {
      BtnBack.setEnabled(iMom>0);
      BtnWeiter.setEnabled(iMom+1<iMax);
  }

}
