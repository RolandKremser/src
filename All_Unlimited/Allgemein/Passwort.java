/*
    All_Unlimited-Allgemein-Passwort.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Grunddefinition.UserManager;

import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;

public class Passwort extends JDialog
{
	/**
	 *
	 */
	private static final long serialVersionUID = -4340994881275473751L;
	public Passwort(Frame Frm,Global glob)
	{
		super(Frm,true);
		g=glob;
                sTitel=g.getDB_Name()+g.getBegriffS("Dialog","Passwort");
		setTitle(sTitel);

		BtnOk = g.getButton("Ok");
                getRootPane().setDefaultButton(BtnOk);
		BtnAbbruch = g.getButton("Abbruch");

		getContentPane().setLayout(new BorderLayout(2,2));
		JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
		Pnl.add(g.getLabel("Altes Passwort:",SwingConstants.LEFT));
		Pnl.add(new JLabel());
		Pnl.add(g.getLabel("Neues Passwort:",SwingConstants.LEFT));
		Pnl.add(g.getLabel("Bestaetigung:",SwingConstants.LEFT));
                JScrollPane Scroll=new JScrollPane(Pnl);
                Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,0)));
                getContentPane().add("West",Scroll);

		Pnl = new JPanel(new GridLayout(0,1,2,2));
		Pnl.add(EdtAltesPasswort);
                EdtAltesPasswort.setFont(Transact.fontStandard);
		Pnl.add(new JLabel());
		Pnl.add(EdtNeuesPasswort);
                EdtNeuesPasswort.setFont(Transact.fontStandard);
		Pnl.add(EdtBestaetigung);
                EdtBestaetigung.setFont(Transact.fontStandard);
                Scroll=new JScrollPane(Pnl);
                Scroll.setBorder(new EmptyBorder(new Insets(2,0,0,2)));
                getContentPane().add("Center",Scroll);
                Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
		Pnl.add(BtnOk);
		Pnl.add(BtnAbbruch);
		getContentPane().add("South",Pnl);
		int iFF=g.getFontFaktor();
		setSize(350*iFF/100,200*iFF/100);

		//if(Frm!=null)
			Static.centerComponent(this,Frm);

		BtnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Ok();
			}
		});

		BtnAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				setVisible(false);
			}
		});

		EdtBestaetigung.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
			}
			public void keyPressed(KeyEvent e)
			{
			}
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					Ok();
			}

		});

	}

	public String setPasswort(String rsTabelle, int riAIC, boolean rbSpeichern)
	{
		EdtAltesPasswort.setText("");
		EdtNeuesPasswort.setText("");
		EdtBestaetigung.setText("");

		sTabelle = rsTabelle.toUpperCase();
		iAIC = riAIC;
		bSpeichern = rbSpeichern;

		sPasswort=null;

		if(iAIC > 0)
		{
			//SQL Qry = new SQL(g);
            Tabellenspeicher Tab=new Tabellenspeicher(g,"SELECT * FROM "+sTabelle+" WHERE AIC_"+sTabelle+"="+iAIC,true);
//			if(Tab!=null)
//			{
				if(Tab.size()>0)
                                {
                                  sPasswort = Tab.getS("Passwort");
                                  sKennung=g.TabTabellenname.getBezeichnungS(sTabelle)+" "+Tab.getS("Kennung");
                                  setTitle(sTitel+" "+sKennung);
                                  iBits=sTabelle.equals("BENUTZER") ? Tab.getI("bits"):0;
                                }
				else
                                  Static.printError("Passwort.setPasswort("+sTabelle+"): Ungültiger AIC übergeben!!!");
//			}
//			else
//                          Static.printError("Passwort.setPasswort("+sTabelle+"): Auf Datenbank konnten nicht zugegriffen werden!!!");
		}

		if(sPasswort==null)
		{
			EdtAltesPasswort.setEnabled(false);
		}

		setVisible(true);
		return(sPasswort);
	}

	private void Ok()
	{
		String sPWold=new String(EdtAltesPasswort.getPassword());
		int iPW_Art=iBits&Global.cstPW;
		int iPWArt=iPW_Art==Global.cstPW_MD5 ? Transact.PWH:Transact.PWVH;
		bOk = sPasswort==null || sPasswort.equals(g.PasswordConvert(sPWold,iPWArt,iAIC));

		if(bOk)
		{
			String sPW=new String(EdtNeuesPasswort.getPassword());
			//bOk = bOk && !sPW.equals("") && sPW.equals(new String(EdtBestaetigung.getPassword()));
                        String sFehler=PW_Fehler(g,sPW,new String(EdtBestaetigung.getPassword()),sPWold);
                        bOk=sFehler==null;
			if(bOk)
			{
				bOk = iAIC > 0 && sPasswort!= null && bSpeichern;
				sPasswort = g.PasswordConvert(sPW,iPWArt,iAIC);
                                if(bOk)
				{
					SQL Qry = new SQL(g);
                    if (sTabelle.equals("BENUTZER"))
                    {
                      Qry.add("aic_logging",g.getLog());
                      Qry.addnow("PW_Date");
                    }
					Qry.add("Passwort",sPasswort);
					if(!Qry.update(sTabelle,iAIC))
					{
						bOk=false;
						Static.printError("Passwort.Ok(): Neues Passwort konnte in der Datenbank nicht erneuert werden!!!");
					}
                    else
                    {
                    	if ((iBits&Global.cstDB)>0 && sTabelle.equals("BENUTZER"))
                    	{
                    		String sPWoldDB=g.PasswordConvert(sPWold,iPWArt,iAIC);
                    		bOk=UserManager.changePW(g, null/*SQL.getString(g,"select kennung from benutzer where aic_benutzer="+iAIC)*/, sPasswort,sPWoldDB);
                    	}
                      new Message(Message.INFORMATION_MESSAGE,this,g,10).showDialog("Password_changed", new String[]{"" + sKennung});
                    }
				}
                setVisible(false);
			}
			else
			{
				//new Message(Message.WARNING_MESSAGE,this,g,10).showDialog(sFehler);
				PW_Message(g,this,sFehler);
				EdtNeuesPasswort.setText("");
				EdtBestaetigung.setText("");
				EdtNeuesPasswort.requestFocus();

			}
		}
		else
		{
			new Message(Message.WARNING_MESSAGE,null,g).showDialog("Altes Passwort");
			EdtAltesPasswort.setText("");
			EdtNeuesPasswort.setText("");
			EdtBestaetigung.setText("");
			EdtAltesPasswort.requestFocus();
		}
	}
	
	public static void PW_Message(Global g, JDialog Dlg, String s)
	{
		int iPWM=Static.iPWM;
		if (s.equals("KennungLeer_zu_kurz"))
			new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog("KennungLeer_zu_kurz2",new String[] {""+Static.iBenML});
		if (s.equals("Passwort_zu_kurz"))
			new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog("Passwort_zu_kurz2",new String[] {""+iPWM/1000});
		else if (s.equals("Passwort_unsicher"))
			new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog("Passwort_unsicher3",new String[] {""+iPWM/100%10,""+iPWM/10%10,""+iPWM%10});
		else
			new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog(s);
	}
	
	public static void PW_Message(Global g, javax.swing.JFrame Dlg, String s)
	{
		int iPWM=Static.iPWM;
		if (s.equals("KennungLeer_zu_kurz"))
			new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog("KennungLeer_zu_kurz2",new String[] {""+Static.iBenML});
		if (s.equals("Passwort_zu_kurz"))
			new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog("Passwort_zu_kurz2",new String[] {""+iPWM/1000});
		else if (s.equals("Passwort_unsicher"))
			new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog("Passwort_unsicher3",new String[] {""+iPWM/100%10,""+iPWM/10%10,""+iPWM%10});
		else
			new Message(Message.WARNING_MESSAGE,Dlg,g,10).showDialog(s);
	}

public static boolean PWok(Global g,String s)
      {
        int iZ = 0;
        int iB = 0;
        int iG = 0;
        int iF = 0;
        int iS = 0;
        String sNicht=";\",'";
        String sSonder="![]{}$%&/\\()?=#<>+-*@";
        for (char c : s.toCharArray())
        {
          if (c >= 'a' && c <= 'z')
            iB++;
          else if (c >= 'A' && c <= 'Z')
            iG++;
          else if (c >= '0' && c <= '9')
            iZ++;
          else if (c>(char)127 || sNicht.indexOf(c)>=0)
        	iF++;
          else if (sSonder.indexOf(c)>=0)
        	iS++;
          // ö, ü, ä, ß
//          if (c == 'ö' || c == 'ß' || c == 'ä' || c == 'ü')
//            iB++;
        }
//        int iS=s.length()-iZ-iB-iG;
        //System.out.println("BZ="+iB+"/"+iZ);
        //System.err.println("BenML="+Static.iBenML+", PWML="+Static.iPWML+", PWMZ="+Static.iPWMZ+" bei iZ="+iZ+", iB="+iB);
        g.fixtestError("PWok: B="+iB+", G="+iG+", Z="+iZ+", S="+iS+", F="+iF);
        int iPWMG=Static.iPWM/100%10;
        int iPWMZ=Static.iPWM/10%10;
        int iPWMS=Static.iPWM%10;
        return iB>1 && iG>=iPWMG && iZ>=iPWMZ && iS>=iPWMS && iF==0;
      }

private static boolean PW_Abgelaufen(Global g)
{
	if (Static.iPWMM==0)
		return false;
	DateWOD DW=new DateWOD(SQL.getTimestamp(g,"select pw_date from benutzer where aic_benutzer="+g.getBenutzer()));
	g.fixtestError("pw erzeugt am "+DW.Format("dd.MM.yyyy"));
	for (int i=0; i<Static.iPWMM;i++)
		DW.nextMonth();
	g.fixtestError("pw läuft ab am "+DW.Format("dd.MM.yyyy"));
	return DW.before(new DateWOD());
}

public static String PW_Fehler(Global g,String sPW1,String sPW2,String sPWold)
      {
        if (sPW1==null || sPW1.equals(""))
          return "Passwort";
        else if (sPW2!=null && !sPW1.equals(sPW2))
          return "Passwort_ungleich";
        else if (sPW2==null && !g.Def() && PW_Abgelaufen(g))
          return "Passwort_abgelaufen";
        else if (sPW1.equals(Static.sDefaultPW))
            return "Passwort_Default";
        else if (sPWold!=null && sPW1.equals(sPWold))
            return "Passwort_gleich";
        else if (sPW2==null && g.Def())
        	return null;
        else if (sPW1.length()<(/*bAll ? 6:*/Static.iPWM/1000))
          return "Passwort_zu_kurz";
        else if (!PWok(g,sPW1))
          return "Passwort_unsicher";
        return null;
      }

public boolean isOK()
{
	return bOk;
}


// add your data members here
private Global g;
private String sTitel;
private String sKennung;
private AUPasswort EdtAltesPasswort = new AUPasswort(10);
private AUPasswort EdtNeuesPasswort = new AUPasswort(10);
private AUPasswort EdtBestaetigung = new AUPasswort(10);

private JButton BtnOk = new JButton("");
private JButton BtnAbbruch = new JButton("");

private String sPasswort = null;
private int iBits = 0;
private String sTabelle;
private int iAIC;
private boolean bSpeichern;
private boolean bOk=false;
}

