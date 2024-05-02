/*
    All_Unlimited-Grunddefinition-AUInfo.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Cursor;
//import java.awt.DisplayMode;
import java.awt.Font;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

//import jclass.bwt.JCOutliner;
//import jclass.bwt.JCOutlinerComponent;
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
//import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Hauptmaske.Abfrage;
//import graphtutorial.App;
//import All_Unlimited.Hauptmaske.Calc;
import jclass.bwt.BWTEnum;

import java.awt.GridLayout;
//import java.awt.Rectangle;
import java.awt.Color;

import javax.swing.undo.*;

//import org.json.JSONArray;
import org.json.JSONObject;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
//import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import javax.naming.ldap.*;
import javax.naming.*;

public class AUInfo extends All_Unlimited.Allgemein.Formular
{
public AUInfo(Global glob)
{
	super("AUInfo",null,glob);
	g=glob;

	Build();

	show();
}

private void Build()
{
  ActionListener AL = new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            String s = e.getActionCommand();
            g.progInfo("AUInfo.Action=" + s);
            if (/*s.equals("Mem") ||*/ s.equals("Genau"))
            {
              thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
              /*if (s.equals("Mem")) Mem(); else*/ Genau();
              thisFrame.setCursor(Cursor.getDefaultCursor());
            }
            else if (s.equals("Calc"))
              showCalc();
            else if (s.equals("Exec"))
              showExec();
            else if (s.equals("Count"))
              TxtInfo.setText(TxtInfo.getText()+"\n"+(new Zeit(new Date(),"yyyy/MM/dd HH:mm:ss"))+"\n"+Count.print());
            else if (s.equals("Properties"))
              Props();
            else if (s.equals("Refresh"))
              Refresh();
            else if (s.equals("Export"))
              ExportBegriffInfo();
            else if (s.equals("Test"))
              Test();
            else if (s.equals("Copy"))
              TxtInfo.copy();
            else if (s.equals("Azure"))
            {
            	g.fixtestError("Azure-Test wieder entfernt");//App classUnderTest = new App(g,thisJFrame());
            }
            else if (s.equals("Konsole"))
            {
            	JavaKonsole.get(true,g);
            	new Thread(new Runnable()
				{
					public void run()
					{
		            	for (int i=0;i<10;i++)
		            	{
		            		if ((i%2)==0)
		            			g.fixInfo("Zeile "+(i+1));
		            		else
		            			g.fixtestError("Zeile "+(i+1));
		            		Thread.yield();
		            		Static.sleep(300);
		            	}
					}
				}).start();
            }
            else if (s.equals("Beenden"))
              hide();
            else
            	g.fixInfo(s+" gedrückt");
          }
        };

        BtnGenau = g.BtnAdd(getButton("Genau"),"Genau",AL);
        //if(BtnGenau==null) BtnGenau = new JButton();
        //g.BtnAdd(getButton("Mem"),"Mem",AL);
        g.BtnAdd(getButton("Calc"),"Calc",AL);
        g.BtnAdd(getButton("Exec"),"Exec",AL);
        g.BtnAdd(getButton("Print Count"),"Count",AL);
	g.BtnAdd(getButton("Properties"),"Properties",AL);
	g.BtnAdd(getButton("Refresh"),"Refresh",AL);
	g.BtnAdd(getButton("Export"),"Export",AL);
	g.BtnAdd(getButton("Test"),"Test",AL);
	g.BtnAdd(getButton("Konsole"),"Konsole",AL);
	g.BtnAdd(getButton("Azure"),"Azure",AL);
	g.BtnAdd(getButton("Kopie"),"Copy",AL);
	g.BtnAdd(getButton("Beenden"),"Beenden",AL);

	JPanel PnlBegriffInfo = getFrei("BegriffInfo");
        JPanel PnlBegriff = getFrei("Begriff");
        JPanel PnlEigenschaft = getFrei("Eigenschaft");
	JPanel PnlBild = getFrei("Bild");
	JPanel PnlFeiertage = getFrei("Feiertage");
	JPanel PnlFensterpos = getFrei("Fensterpos");
	JPanel PnlFormulare = getFrei("Formulare");
	JPanel PnlMass = getFrei("Mass");
	JPanel PnlComboboxen = getFrei("Comboboxen");
	JPanel PnlTextfeld = getFrei("Textfeld");
	JPanel PnlStt = getFrei("Stammtyp");
	JPanel PnlRolle = getFrei("Rolle");
	JPanel PnlBew = getFrei("Bewegungstyp");
	JPanel PnlTab = getFrei("Tabellen");
	JPanel PnlStamm = getFrei("Stamm");
	PnlZeilen = getFrei("Zeilen");
    PnlViews = getFrei("Views");
    PnlIndexe = getFrei("Indexe");
    PnlFunct = getFrei("Funktion");

	if(PnlBegriffInfo!=null)
	{
		PnlBegriffInfo.setLayout(new BorderLayout(2,2));
		OutBegriffInfo.setColumnLabelSortMethod(Sort.sortMethod);
		PnlBegriffInfo.add(OutBegriffInfo);
	}
        if(PnlBegriff!=null)
        {
                PnlBegriff.setLayout(new BorderLayout(2,2));
                OutBegriffe.setColumnLabelSortMethod(Sort.sortMethod);
                PnlBegriff.add(OutBegriffe);
        }
        if(PnlEigenschaft!=null)
        {
                PnlEigenschaft.setLayout(new BorderLayout(2,2));
                OutEigenschaft.setColumnLabelSortMethod(Sort.sortMethod);
                PnlEigenschaft.add(OutEigenschaft);
        }

	if(PnlBild!=null)
	{
		PnlBild.setLayout(new BorderLayout(2,2));
		OutBild.setColumnLabelSortMethod(Sort.sortMethod);
		PnlBild.add(OutBild);
	}
	if(PnlFeiertage!=null)
	{
		PnlFeiertage.setLayout(new BorderLayout(2,2));
		OutFeiertage.setColumnLabelSortMethod(Sort.sortMethod);
		PnlFeiertage.add(OutFeiertage);
	}
	if(PnlFensterpos!=null)
	{
		PnlFensterpos.setLayout(new BorderLayout(2,2));
		OutFensterpos.setColumnLabelSortMethod(Sort.sortMethod);
		PnlFensterpos.add(OutFensterpos);
	}
	if(PnlFormulare!=null)
	{
		PnlFormulare.setLayout(new BorderLayout(2,2));
		OutFormulare.setColumnLabelSortMethod(Sort.sortMethod);
		PnlFormulare.add(OutFormulare);
	}
	if(PnlMass!=null)
	{
		PnlMass.setLayout(new BorderLayout(2,2));
		OutMass.setColumnLabelSortMethod(Sort.sortMethod);
		PnlMass.add(OutMass);
	}
	if(PnlComboboxen!=null)
	{
		PnlComboboxen.setLayout(new BorderLayout(2,2));
		OutComboboxen.setColumnLabelSortMethod(Sort.sortMethod);
		PnlComboboxen.add(OutComboboxen);
	}
	if(PnlStt!=null)
	{
		PnlStt.setLayout(new BorderLayout(2,2));
		OutStt.setColumnLabelSortMethod(Sort.sortMethod);
		PnlStt.add(OutStt);
	}
	if(PnlBew!=null)
	{
		PnlBew.setLayout(new BorderLayout(2,2));
		OutBew=new AUOutliner();
		OutBew.setColumnLabelSortMethod(Sort.sortMethod);
		PnlBew.add(OutBew);
	}
	if(PnlRolle!=null)
	{
		PnlRolle.setLayout(new BorderLayout(2,2));
		OutRollen=new AUOutliner();
		OutRollen.setColumnLabelSortMethod(Sort.sortMethod);
		PnlRolle.add(OutRollen);
	}
	if(PnlTab!=null)
	{
		PnlTab.setLayout(new BorderLayout(2,2));
		OutTab=new AUOutliner();
		OutTab.setColumnLabelSortMethod(Sort.sortMethod);
		PnlTab.add(OutTab);
	}
	if(PnlStamm!=null)
	{
		PnlStamm.setLayout(new BorderLayout(2,2));
		OutStamm.setColumnLabelSortMethod(Sort.sortMethod);
		PnlStamm.add(OutStamm);
	}
	if(PnlZeilen!=null)
	{
		PnlZeilen.setLayout(new BorderLayout(2,2));
		OutZeilen.setColumnLabelSortMethod(Sort.sortMethod);
		PnlZeilen.add(OutZeilen);
	}
    if(PnlViews!=null)
    {
            PnlViews.setLayout(new BorderLayout(2,2));
            OutViews.setColumnLabelSortMethod(Sort.sortMethod);
            PnlViews.add(OutViews);
    }
    if(PnlIndexe!=null)
    {
    	PnlIndexe.setLayout(new BorderLayout(2,2));
            OutIndexe.setColumnLabelSortMethod(Sort.sortMethod);
            PnlIndexe.add(OutIndexe);
    }
    
    if(PnlFunct!=null)
    {
    	PnlFunct.setLayout(new BorderLayout(2,2));
            OutFunct.setColumnLabelSortMethod(Sort.sortMethod);
            PnlFunct.add(OutFunct);
    }

	if(PnlTextfeld!=null)
	{
		JScrollPane PnlScroll = new JScrollPane(TxtInfo);
		PnlTextfeld.setLayout(new BorderLayout(2,2));
		PnlTextfeld.add(PnlScroll);
		//TxtInfo.setEditable(false);
		TxtInfo.setFont(new Font("Courier New",0,12));
		TxtInfo.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
                          g.fixtestInfo("keyPressed "+e.getKeyCode());
			  TxtInfo.setText(TxtInfo.getText()+e.getKeyCode()+"/"+e.getModifiers()+":");
			}
			public void keyReleased(KeyEvent e)
			{
			  TxtInfo.setText(TxtInfo.getText()+"\n");
			}
			public void keyTyped(KeyEvent e)
			{
				char cKey=e.getKeyChar();
				int i=e.getModifiersEx();
                                g.fixtestInfo("keyTyped:"+cKey+"/"+i);
				//int iText=getText().length();
				//if(cKey=='\''|| (cKey!=8 && iText>=iLaenge) || (bKSZ && cKey!='-'&&cKey!=' '&&cKey!='.'&&cKey!=':'&&(!Character.isJavaIdentifierPart(cKey)||cKey=='ö'||cKey=='Ö'||cKey=='ä'||cKey=='Ä'||cKey=='ü'||cKey=='Ü'||cKey=='ß'||cKey=='ö')))
				e.consume();
				TxtInfo.setText(TxtInfo.getText()+(cKey+0)+"-"+i+" ("+KeyEvent.getModifiersExText(e.getModifiersEx())+")");
			}
		});
		TxtInfo.addMouseListener(new MouseListener()
	            {
	              public void mousePressed(MouseEvent ev)
	              {}

	              public void mouseClicked(MouseEvent ev)
	              {
	        	TxtInfo.setText(TxtInfo.getText()+"Maus:"+ev.getClickCount()+"x "+ev.getButton()+","+ev.getModifiersEx()+"\n");
	              }

	              public void mouseEntered(MouseEvent ev)
	              {}

	              public void mouseExited(MouseEvent ev)
	              {}

	              public void mouseReleased(MouseEvent ev)
	              {}
	            });
	}
}

//private void Mem()
//{
//  Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Nr","Wurzel","Text"});
//  long lClock= Static.get_ms();
//  String s="";
//  long lMem1 = Runtime.getRuntime().totalMemory();
//  long lMem2 = Runtime.getRuntime().freeMemory();
//  for (int i=1;i<=40000 && lMem2>100000;i++)
//  {
//    Tab.addInhalt("Nr",i);
//    Tab.addInhalt("Wurzel",new Double(Math.sqrt(i)));
//    s+="x";
//    Tab.addInhalt("Text",s);
//    lMem2 = Runtime.getRuntime().freeMemory();
//    if (i%500==0 || lMem2<100000)
//    {
//      System.gc();
//      lMem1 = Runtime.getRuntime().totalMemory();
//      Transact.fixInfo(Static.printZahl(i,6)+".Mem="+Static.printZahl(lMem1,11)+"/"+Static.printZahl(lMem1-lMem2,11)+":"+Static.printZahl(Static.get_ms()-lClock,7));
//      //if (lMem2<50000)
//      //  Tab.showGrid("Test");
//    }
//  }
//  s = TxtInfo.getText()+"\nSpeicher= "+(lMem1/1024/1024)+" MByte";
//  TxtInfo.setText(s);
//  //Tab.showGrid("Test");
//}

private void Genau()
{
  //double d=0;
  int i=0;
  long i2=0;
  long l=Static.get_ms();
  long l1=l;
  long l2=0;
  while (i<10)
  {
    l2=Static.get_ms();
    //g.clockInfo(i+".Wert",l);
    i2++;
    if (l2>l1)
    {
      i++;
      //g.clockInfo(i+".Wert",l);
      l1=l2;
    }
  }
  g.clockInfo(i2+" Werte",l);
  String s = TxtInfo.getText()+"\nGenauigkeit= "+((l2-l)/i)+" ms\n";
  Runtime rt=Runtime.getRuntime();
  s+="Java-max= "+(rt.maxMemory()/1024/1024)+" MByte\n";
  s+="Java-total="+(rt.totalMemory()/1024/1024)+" MByte\n";
  s+="Java-free= "+(rt.freeMemory()/1024/1024)+" MByte\n";
  if (g.MySQL())
	  s+="\n"+g.getAnzConnects()+"\n";
  
//  final OAuthProvider provider = 
//		    new DefaultOAuthProvider(REQ_TOKEN_URL, ACC_TOKEN_URL, AUTH_URL); 

  if (!Static.Leer(Static.sWeb))
  {
	  try 
		{
		  URL url=new URL(Static.sWeb);
		  String sP=url.getPath();
			String sApp=sP.substring(1,sP.substring(1).indexOf("/")+1);
//		  String sWeb=Static.sWeb.substring(0, 8+Static.sWeb.substring(8).indexOf('/'));
//		  g.fixtestError(url+"; sApp="+sApp);//+", sWeb="+sWeb);
		  String sURL=url.getProtocol()+"://"+url.getAuthority()+(sApp.length()>3 ? "/"+sApp.substring(3):"");
		  JSONObject json=g.StreamToJSON(new URL(sURL+"/version.js").openStream());
		  s+="\nwebVersion="+json.getString("version")+" am "+json.getString("created");
		  json=g.StreamToJSON(new URL(sURL+"/config.js").openStream());
		  s+="\nDB="+json.getString("dB");
//		  String sClientID=json.getJSONObject("azureConfig").getJSONObject("msalConfig").getJSONObject("auth").getString("clientId");
//		  JSONArray sScope=json.getJSONObject("azureConfig").getJSONArray("user");
//		  s+="\nAzure: ClientID="+sClientID+", Scope="+sScope+"\n";
		}catch(Exception e) {
			s+="\nerrorException="+e.toString();
			g.printStackTrace(e);
			s+="\nclockVersion="+(Static.get_ms()-l2)+"\n";
		}
  }
  TxtInfo.setText(s);
//  g.testOAuth();
  if (g.MySQL())
    new Thread(new Runnable()
          {
            public void run()
            {
              BtnGenau.setEnabled(false);
              String s = "\nDB = " + SQL.getString(g, "select round(sum(data_length+index_length)/1024/1024,2) from information_schema.tables where table_schema=DATABASE()") + " MByte\n";
              TxtInfo.setText(TxtInfo.getText() + s);
              BtnGenau.setEnabled(true);
            }
          }).start();
  else if (g.MS())
  {
    Tabellenspeicher Tab=new Tabellenspeicher(g,"sp_spaceused",true);
    s = Tab.getS("database_name")+": "+Tab.getS("database_size")+" ("+Tab.getS("unallocated space")+" frei)\n";
    TxtInfo.setText(TxtInfo.getText() + s);
  }

  //return "\nGenauigkeit= "+((l2-l)/i)+" ms\n";
}

//private JSONObject URLtoJSON(URL url)
//{
//	try 
//	{
//		InputStream is=url.openStream();// getContent();
//		BufferedReader Buf=new BufferedReader(new InputStreamReader(is));
//		String s2=Buf.readLine();
//		String s=null;
//		while (s2 != null) {
//			if (!s2.trim().startsWith("//"))
////			{
////				g.fixInfo(s2);
//				s=s==null ? s2:s+"\n"+s2;
////			}
//			s2=Buf.readLine();
//		}
//		
//		Buf.close();
//		s=s.substring(s.indexOf("{"), s.length());
//		return new JSONObject(s);
//	}catch(Exception e) {
//		//s+="\nerrorException="+e.toString();
//		g.fixtestError("URLtoJSON:"+e);
//		return null;
//	}
//}

private void showExec()
{
  final JTextArea EdtText=new JTextArea();
  final JTextArea EdtResult=new JTextArea();
  final JFrame Fom=new JFrame("Exectest");
  JButton BtnExec=new JButton("Exec");
    BtnExec.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
          Runtime rt=Runtime.getRuntime();
          try {
            java.io.InputStream ops = rt.exec(EdtText.getText()).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ops));
            String s = "";
            String sZeile;
            int i = 0;
            while((sZeile = reader.readLine()) != null) {
              i++;
              s += Static.VorNull(i) + ":" + sZeile + "\n";
            }
            EdtResult.setText(s);
          }
          catch(Exception e) {
            EdtResult.setText("" + e);
          }
        }
    });
    JButton BtnBeenden=new JButton("Beenden");
    BtnBeenden.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
                Fom.dispose();
        }
    });
    JPanel PnlS=new JPanel(new GridLayout());
    PnlS.add(BtnExec);
    PnlS.add(BtnBeenden);
    EdtText.setBackground(Color.pink.brighter());
    Fom.getContentPane().add("North",EdtText);
    Fom.getContentPane().add("Center",EdtResult);
    Fom.getContentPane().add("South",PnlS);
    Fom.setVisible(true);
    Fom.pack();
}

public static String getDouble(String strNum) {
  try {
      return ""+Double.parseDouble(strNum);
  } catch (Exception e) {
      return "!"+e;
  }
}

private void showCalc()
{
  JFrame FomCalc=new JFrame("Calc");
  final JButton BtnTake=g.getButton("Pfeil oben");
  final JButton Btn=new JButton("Ergebnis");
  final Text Z1=new Text("0.0",30);//,Text.ZAHL);
  Z1.setPreferredSize(new java.awt.Dimension(150,20));
  final Zahl Z2=new Zahl(0.0);
  Z2.setPreferredSize(new java.awt.Dimension(150,20));
  final ComboSort Cbo=new ComboSort(g,ComboSort.kein);
  Cbo.addItem("+",1,"+",0);
  Cbo.addItem("-",2,"-",0);
  Cbo.addItem("*",3,"*",0);
  Cbo.addItem("/",4,"/",0);
  Cbo.addItem("%",5,"%",0);
  Cbo.addItem("round",6,"round",0);
  Cbo.addItem("rint",7,"rint",0);
  Cbo.addItem("random",8,"random",0);
  Cbo.addItem("pow",9,"pow",0);
  Cbo.addItem("Abfrage.Round",10,"Abfrage.Round",0);
  BtnTake.addActionListener(new ActionListener()
  {
    public void actionPerformed(ActionEvent e) {
    	Z1.setText(Btn.getText());
    }
  });
  Btn.addActionListener(new ActionListener()
  {
    public void actionPerformed(ActionEvent e) {
      String s1=Z1.getText();
      boolean b=/*Calc.isNumeric(s1)*/!getDouble(s1).startsWith("!") && Z2.getDouble() !=null;
      if (b)
      {
    	  double d1 = Double.parseDouble(s1);//Z1.getDouble();
	      double d2 = Z2.getDouble();
	      int i = Cbo.getSelectedAIC();
	      double d = i == 1 ? d1 + d2 : i == 2 ? d1 - d2 : i == 3 ? d1 * d2 : i == 4 ? d1 / d2 : i == 5 ? d1 % d2 :
	         i == 6 ? Math.round(d1):i == 7 ? Math.rint(d1):i == 8 ? Math.random():i == 9 ? Math.pow(d1,d2): i == 10 ? Abfrage.Round(d1,d2,true):0;
	      Btn.setText("" + d);
	      Btn.setToolTipText(Long.toHexString(Double.doubleToLongBits(d)));
      }
      else
      {
    	  Btn.setText(Z2.getDouble()==null ? "Z2=null":"Z1="+getDouble(s1));
    	  Btn.setToolTipText("ist keine Zahl");
      }
    }
  });

  JPanel PnlC=new JPanel(new GridLayout(1,3,2,0));
  PnlC.add(Z1);
  PnlC.add(Cbo);
  PnlC.add(Z2);
//  FomCalc.getContentPane().add("West",Z1);
//  FomCalc.getContentPane().add("East",Z2);  
  FomCalc.getContentPane().add("Center",PnlC);
  JPanel PnlS=new JPanel(new BorderLayout());
  PnlS.add("West",BtnTake);
  PnlS.add("Center",Btn);
  FomCalc.getContentPane().add("South",PnlS);
  FomCalc.pack();
  FomCalc.setVisible(true);
}

private Zahl1 toZahl(String s)
{
	double d=s.endsWith("MB") ? 0:1024;
	return new Zahl1(Double.parseDouble(s.substring(0,s.length()-3))/d,null);
}


private Tabellenspeicher getZeilen()
{
        Tabellenspeicher Tab=new Tabellenspeicher(g,g.MS() ? "SELECT cast(o.name as varchar) Tabelle,i.rows Anzahl,null max_aic,null Data_MB,null Index_MB FROM sysindexes AS i INNER JOIN sysobjects AS o ON  i.id = o.id"+
                                                  " WHERE i.indid < 2 AND OBJECTPROPERTY(o.id, 'IsMSShipped') = 0 ORDER BY i.rows desc":
          g.Oracle() ?"select table_name Tabelle,null Anzahl,null max_aic from user_tables where table_name not like 'SMP_%'":
          g.MySQL() ? "select table_name Tabelle,engine,Update_Time,table_rows Anzahl,auto_increment-1 max_aic,table_collation Zeichensatz,ROUND(DATA_LENGTH/1024) Data_MB,ROUND(INDEX_LENGTH/1024) Index_MB"+
          	" from information_schema.tables where table_schema=DATABASE() and table_type='BASE TABLE'":
          g.ASA() ?"select table_name Tabelle,column_name Aic,count Anzahl,max_identity max_aic"+
          " from systable left outer join syscolumn on systable.table_id=syscolumn.table_id and \"default\"='autoincrement'"+
          " where creator=1 and table_type='BASE' order by count desc":"select getZeilen",true);
//        if (g.MS() || g.Oracle() || g.MySQL())
          for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
          {
            String sTab=Tab.getS("Tabelle").toUpperCase();
//            if (!g.MS())
//              Tab.setInhalt("Anzahl",SQL.getInteger(g,"select count(*) from "+sTab));
//            if (!g.MySQL())
            if (SQL.VecTab.contains(sTab))
            {
            	Tabellenspeicher Tabx=new Tabellenspeicher(g,"select max(AIC_"+sTab+") m,count(AIC_"+sTab+") c from "+sTab,true);
            	Tab.setInhalt("max_aic",Tabx.getI("m"));
            	Tab.setInhalt("Anzahl",Tabx.getI("c"));
//            	Tabx.finalize();
            }
            if (g.MySQL())
            {
            	Tab.setInhalt("Data_MB",new Zahl1(Tab.getI("Data_MB")*1.0/1024.0,null));
                Tab.setInhalt("Index_MB",new Zahl1(Tab.getI("Index_MB")*1.0/1024.0,null));
            }
            if (g.MS())
            {
              try
              {
                Tabellenspeicher Tabx=new Tabellenspeicher(g,"sp_spaceused "+sTab,true);
                if (Tabx!=null && Tabx.size()>0)
                {
                  Tab.setInhalt("Data_MB",toZahl(Tabx.getS("data")));
                  Tab.setInhalt("Index_MB",toZahl(Tabx.getS("index_size")));
                }
              }
              catch(Exception e)
              {
                g.printError("AUInfo.getZeilen: Fehler bei Tabelle "+sTab+": "+e);
              }
//              Tabx.finalize();
            }
//              Tab.setInhalt("max_aic",SQL.VecTab.contains(sTab) ? SQL.getInteger(g,"select max(AIC_"+sTab+") from "+sTab):0);
          }
	return Tab;
}

private Tabellenspeicher getViews()
      {
        Tabellenspeicher Tab=new Tabellenspeicher(g,g.MS() ? "select name,crdate from sysobjects where xtype='V '":
             g.Oracle() ? "select view_name, text from all_views where owner='WatchMe'":
             g.MySQL() ? "select table_name,definer,view_definition from information_schema.views where table_schema=DATABASE()":
             g.ASA() ? "select table_name,view_def from systable where creator=1 and table_type='VIEW'":"select views",true);
        return Tab;
      }

private Tabellenspeicher getFuncts()
{
	if (!g.MySQL())
		return null;
	
  Tabellenspeicher Tab=new Tabellenspeicher(g,"select specific_name,Routine_Definition,created from information_schema.routines where routine_schema=DATABASE() and specific_name like 'von%'",true);
  return Tab;
}

private Tabellenspeicher getIndexe()
{
  Tabellenspeicher Tab=new Tabellenspeicher(g,g.MS() ? "":g.MySQL() ? "SELECT c.table_name,c.constraint_name,c.column_name,c.referenced_table_name,c.referenced_column_name "+
		  " FROM information_schema.key_column_usage c WHERE c.table_schema=DATABASE() order by c.TABLE_NAME":"select indexe",true);
  return Tab;  
}

private void Refresh()
{
  g.TabBegriffInfo.showGrid(OutBegriffInfo);
  g.TabBegriffe.showGrid(OutBegriffe);
  g.TabEigenschaften.showGrid(OutEigenschaft);
  g.TabFeiertage.showGrid(OutFeiertage);
  g.TabFensterpos.showGrid(OutFensterpos);
  g.TabFormulare.showGrid(OutFormulare);
  g.TabComboboxen.showGrid(OutComboboxen);
  if (g.TabImages != null)
    g.TabImages.showGrid(OutBild);
  //if (g.Prog())
  //  g.TabImageIcons.showGrid("ImageIcons");
  g.TabStammtypen.showGrid(OutStt);
  if (OutBew != null)
    g.TabErfassungstypen.showGrid(OutBew);
  if (OutRollen != null)
    g.TabRollen.showGrid(OutRollen);
  if (OutTab != null)
    g.TabTabellenname.showGrid(OutTab);
  g.TabStammBild.showGrid(OutStamm);
  if (g.TabMass != null)
    g.TabMass.showGrid(OutMass);
  new Thread(new Runnable()
        {
          public void run()
          {
        	long lClock = Static.get_ms();      
            if (PnlViews != null)
            	getViews().showGrid(OutViews);
            if (PnlIndexe != null && g.MySQL())
            	getIndexe().showGrid(OutIndexe);
            if (PnlFunct != null && g.MySQL())
            	getFuncts().showGrid(OutFunct);
            if (g.MS()) OutZeilen.setColumnAlignment(1, BWTEnum.TOPRIGHT);
            if (g.MS()) OutZeilen.setColumnAlignment(2, BWTEnum.TOPRIGHT);
            OutZeilen.setColumnAlignment(3, BWTEnum.TOPRIGHT);
            OutZeilen.setColumnAlignment(4, BWTEnum.TOPRIGHT);
            if (g.MS()) OutZeilen.setColumnAlignment(5, BWTEnum.TOPRIGHT);
            if (g.MySQL()) OutZeilen.setColumnAlignment(6, BWTEnum.TOPRIGHT);
            if (g.MySQL()) OutZeilen.setColumnAlignment(7, BWTEnum.TOPRIGHT);
            if (g.MySQL()) OutZeilen.setColumnAlignment(8, BWTEnum.TOPRIGHT);
            if (PnlZeilen != null)
            	getZeilen().showGrid(OutZeilen);
            g.clockInfo("Zeilen, Views", lClock);
          }
        }).start();
}

public void ExportBegriffInfo()
{
  Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Gruppe","Kennung","DefBezeichnung"});
  for (g.TabBegriffInfo.moveFirst();!g.TabBegriffInfo.out();g.TabBegriffInfo.moveNext())
    if (!g.TabBegriffInfo.getB("übersetzt"))
    {
      String s=g.TabBegriffInfo.getS("Kennung");
      Tab.addInhalt("Gruppe",s.substring(0,s.indexOf('.')));
      Tab.addInhalt("Kennung",s.substring(s.indexOf('.')+1));
      Tab.addInhalt("DefBezeichnung",g.TabBegriffInfo.getS("DefBezeichnung"));
    }
  Tab.showGrid("nicht übersetzt");
}

public void Test()
{
//	long objectCount=1000000;
//	HashMap myMap = new HashMap();
//    for (int i=0; i<objectCount; i++) {
//        myMap.put(i, String.valueOf(i));
//    }  
	
//	int iA=1;
//	Map<Integer, String> dataMap = new HashMap<>();
//    Random r = new Random();
//    while (iA==1) {
//        dataMap.put(r.nextInt(), String.valueOf(r.nextInt()));
//    }
	
  if (FomUndo==null)
  {
    FomUndo = new JFrame("Undo-test");
    final JTextArea txt = new JTextArea(20, 40);
    txt.setName("nameTextField");
    txt.getAccessibleContext().setAccessibleName("Name Field");
    txt.getAccessibleContext().setAccessibleDescription("Enter a name");
    txt.setToolTipText("Name");
    final UndoManager um = new UndoManager();
    txt.getDocument().addUndoableEditListener(um);
    um.setLimit(1000);
    JButton BtnUndo = new JButton("Undo");
    JButton BtnRedo = new JButton("Redo");
    JButton BtnUndoA = new JButton("Search");
    JButton BtnIP = new JButton("IP-Info");
    JButton BtnPW = new JButton("PW-Test");
    JButton BtnSS = new JButton("SS3-Test");
    JPanel PnlS = new JPanel(new GridLayout());
    PnlS.add(BtnUndo);
    PnlS.add(BtnRedo);
    PnlS.add(BtnUndoA);
    PnlS.add(BtnIP);
    PnlS.add(BtnPW);
    PnlS.add(BtnSS);
    BtnSS.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        long lClock = Static.get_ms();
        try {
          /*String sIP=Static.sAServer.substring(0,Static.sAServer.indexOf(':'));
          int iPort=Integer.parseInt(Static.sAServer.substring(Static.sAServer.indexOf(':')+1));
          Socket server = new Socket(sIP, iPort);
          server.setSoTimeout(2000);*/
          Socket server=AClient.connect();
          ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
          String s = txt.getText();
          /*if(s.equals("mul")) {
            out.writeObject("mul");
            out.writeObject("" + Math.round(Math.random() * 1000));
            out.writeObject("" + Math.round(Math.random() * 1000));
            //Scanner in=new Scanner(server.getInputStream());
            g.fixInfo(new Scanner(server.getInputStream()).nextLine());
          }
          else*/ if(s.equals("ver")) {
            out.writeObject("ver");
            g.fixInfo(new Scanner(server.getInputStream()).nextLine());
          }
          /*else if(s.equals("v")) {
            out.writeObject("Vec");
            out.writeObject(g.getSyncVec(g.iSttANR));
          }*/
          else if(s.startsWith("select")) {
            long lClock2 = Static.get_ms();
            out.writeObject("sql");
            out.writeObject(s);
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
            Tabellenspeicher Tab = (Tabellenspeicher)in.readObject();
            g.fixInfo("SQL-Dauer:" + (Static.get_ms() - lClock2));
            Tab.showGrid();
          }
          else
            out.writeObject(s);
          out.close();
          server.close();
        }
        catch(Exception e2) {
          System.err.println("SS3-Error:" + e2);
        }
        g.fixInfo("Gesamtdauer:" + (Static.get_ms() - lClock));
      }
    });
    BtnIP.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          InetAddress inet = InetAddress.getByName(txt.getText());
          System.out.println("CHN=" + inet.getCanonicalHostName());
          System.out.println(" HA=" + inet.getHostAddress());
          System.out.println(" HN=" + inet.getHostName());
          System.out.println("  s=" + inet.toString());
          System.out.println("loc=" + inet.isSiteLocalAddress());
          System.out.println("LBA=" + inet.isLoopbackAddress());
        }
        catch(Exception e2) {
          System.err.println("IP-Error:" + e2);
        }
      }
    });
    BtnPW.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int iPos=txt.getText().indexOf(":");
        String ip=txt.getText().substring(0,iPos);
        String s=txt.getText().substring(iPos+1);
        iPos=s.indexOf(":");
        String user_name=s.substring(0,iPos);
        String user_password=s.substring(iPos+1);
        g.fixInfo("IP="+ip);
        g.fixInfo("User="+user_name);
        //g.fixInfo("PW="+user_password);

        try {
          //DirContext Context = new InitialDirContext();
          //LDAPConnectionConfig config = (LDAPConnectionConfig)params.getObjectParam("ldap.config").getObject() ;
        Hashtable<String, String> env1 = new Hashtable<String, String>();
        env1.put(DirContext.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        //env1.put(Context.SECURITY_AUTHENTICATION, config.getAuthenticationType());
        //env1.put(Context.SECURITY_PRINCIPAL, config.getRootDN());
        env1.put(DirContext.SECURITY_AUTHENTICATION, "simple");
        env1.put(DirContext.SECURITY_PRINCIPAL, user_name);
        env1.put(DirContext.SECURITY_CREDENTIALS, user_password);
        env1.put(DirContext.PROVIDER_URL,"ldap://"+ip+":389");
        try {
            //Connect with ldap
            new InitialLdapContext(env1, null);

            //Connection succeeded
            System.out.println("Connection succeeded!");
            //return true;
        } catch (AuthenticationException e2) {

            //Connection failed
            System.out.println("Connection failed!"+e2);
            e2.printStackTrace();
            //return false;
        }
    }
    catch (Exception e2) { System.out.println("Sonstiger Fehler:"+e2);}

      }
    });
    BtnUndoA.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          //String s = "p=" + URLEncoder.encode(txt.getText().trim(), "UTF-8");
          String s = txt.getText().trim();
          URL u = new URL(s); //"http://de.search.yahoo.com/search?" + s);
          //String s2 = new Scanner(u.openStream()).useDelimiter("\\Z").next();
          //System.out.println(s2);
          URLConnection con = u.openConnection();
          System.out.println(con);
          System.out.println("Date=" + new java.util.Date(con.getDate()));
          System.out.println("Last=" + new java.util.Date(con.getLastModified()));
          System.out.println("Code=" + con.getContentEncoding());
          System.out.println("Type=" + con.getContentType());
          System.out.println("Länge=" + con.getContentLength());
          if(s.startsWith("jar:")) {
            JarURLConnection conn = (JarURLConnection)con;
            JarFile jarFile = conn.getJarFile();
            for(Enumeration<JarEntry> it = jarFile.entries(); it.hasMoreElements(); ) {
              JarEntry entry = (JarEntry)it.nextElement();
              if(!entry.isDirectory())
                System.out.println(entry + ", " + entry.getSize());
            }
          }
        }
        catch(Exception e2) {
          System.err.println("Search-Fehler:" + e2);
        }
      }
    });
    BtnUndo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //System.out.println("Undo="+um);
        if(um.canUndo())
          um.undo();
        txt.requestFocus();
      }
    });
    BtnRedo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(um.canRedo())
          um.redo();
        txt.requestFocus();
      }
    });

    FomUndo.add("Center", txt);
    FomUndo.add("South", PnlS);
    FomUndo.pack();
  }
  FomUndo.setVisible(true);
}

private void Props()
{
  java.util.Properties ps = System.getProperties();
  String s2 = TxtInfo.getText();
  for (Object key : ps.keySet())
    s2+="\n"+String.format("%s='%s'", key, ps.get(key));
  
  TxtInfo.setText(s2);
  
  Tabellenspeicher Tab=new Tabellenspeicher(g,new String[] {"Gruppe","Element","Inhalt","Farbe"});
  UIDefaults defaults = UIManager.getDefaults();
  s2+="\n\n"+defaults.size()+ " properties defined !";
  
//  String[ ] colName = {"Key", "Value"};
//  String[ ][ ] rowData = new String[ defaults.size() ][ 2 ];
//  int i = 0;
  for(Enumeration<Object> e = defaults.keys(); e.hasMoreElements(); ){
      Object key = e.nextElement();
      String sKey = key.toString();
      Object Obj = defaults.get(key);
//      if (rowData[i][0].startsWith("Button"))
//    	  s2+="\n"+rowData[i][0]+" -> "+rowData[i][1];
//      String[] s=sKey.split(".");
//      g.fixtestError("s"+i+"="+s);
      int iPos=sKey.indexOf('.');
      //if (s.length>0)
      {
	      Tab.addInhalt("Gruppe",iPos<0 ? sKey:sKey.substring(0, iPos));
	      Tab.addInhalt("Element",iPos<0 ? null:sKey.substring(iPos+1));
	      Tab.addInhalt("Inhalt", Obj);
	      Tab.addInhalt("Farbe", Obj instanceof Color ? ((Color)Obj).getRGB():0);
      }
  }
  TxtInfo.setText(s2);
  Tab.showGrid("UIDefaults", thisJFrame());

}

public void show()
{
	Refresh();
	super.show();
}

// add your data members here
private Global g;
//private ActionListener AL;
private JFrame FomUndo=null;
private JButton BtnGenau;
//private JButton BtnMem;
//private JButton BtnCalc;
//private JButton BtnExec;
/*private JButton BtnPrintCount;
private JButton BtnProperties;
private JButton BtnRefresh;
private JButton BtnExport;
private JButton BtnTest;
private JButton BtnBeenden;*/

private AUOutliner OutBegriffInfo = new AUOutliner();
private AUOutliner OutBegriffe = new AUOutliner();
private AUOutliner OutEigenschaft = new AUOutliner();
private AUOutliner OutFeiertage = new AUOutliner();
private AUOutliner OutFensterpos = new AUOutliner();
private AUOutliner OutFormulare = new AUOutliner();
private AUOutliner OutMass = new AUOutliner();
private AUOutliner OutComboboxen = new AUOutliner();
private AUOutliner OutBild = new AUOutliner();
private AUOutliner OutStt = new AUOutliner();
private AUOutliner OutBew = null;
private AUOutliner OutRollen = null;
private AUOutliner OutTab = null;
private AUOutliner OutStamm = new AUOutliner();
private AUOutliner OutZeilen = new AUOutliner();
private AUOutliner OutViews = new AUOutliner();
private AUOutliner OutIndexe = new AUOutliner();
private AUOutliner OutFunct = new AUOutliner();
private JTextArea  TxtInfo = new JTextArea();
private JPanel PnlZeilen=null;
private JPanel PnlViews=null;
private JPanel PnlIndexe=null;
private JPanel PnlFunct=null;
}

