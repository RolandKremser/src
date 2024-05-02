/*
    All_Unlimited-Grunddefinition-Definition.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Farbwahl;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Memo;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.Passwort;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.EigenschaftsEingabe;
import All_Unlimited.Allgemein.Eingabe.RolleEingabe;
import All_Unlimited.Allgemein.Eingabe.Schrift;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.WWW;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import javax.swing.JTabbedPane;

public class Definition extends javax.swing.JFrame
{
    /**
	 *
	 */
	private static final long serialVersionUID = -1685292935912710585L;

	/**
     * CutAIC Method
     */

    public String CutAIC(String WithAIC)
    {
		int iPos=WithAIC.indexOf("AIC_");

		if(iPos != -1)
			return(WithAIC.substring(iPos+4));
		else
			return(WithAIC);
	}//CutAIC
    
    private String DateToString(Date date)
    {
    	return date == null ? Static.sLeer: new SimpleDateFormat("yyyy/MM/dd").format(date);
    }
    
    /**
     * method4 Method
     */

    private void Rekursion(int iVorgaenger,JCOutlinerFolderNode NodVorgaenger, String sSelect)
    {
		SQL Qry2 = new SQL(g);
		if(Qry2.open(sSelect+" WHERE "+sRekursion+"="+iVorgaenger))
		{
			while(!Qry2.eof())
			{
				/*if(Qry2.getB(sTop))
					NodVorgaenger.addNode((JCOutlinerNode)FuelleZeile(Qry2));
				else
				{*/
					JCOutlinerFolderNode NodNachfolger=(JCOutlinerFolderNode)FuelleZeile(Qry2);
					NodVorgaenger.addNode(NodNachfolger);
					Rekursion(Qry2.getI("AIC_"+sKennung),NodNachfolger,sSelect);
				//}

				Qry2.moveNext();
			}
			Qry2.close();
		}

		Qry2.free();

       		//NodVorgaenger.addNode(FuelleZeile());
    }//Rekursion
    /**
     * Memo_actionPerformed Method
     */
	/*
    private void Memo_actionPerformed(ActionEvent ev)
    {
        final int iElement=new Integer(ev.getActionCommand()).intValue();
		TabSpalten.setPos(iElement);
		final JFrame FrmMemo = new JFrame((String)TabSpalten.getInhalt("Bezeichnung"));
		JButton BtnMemoOk = g.getButton("Ok");
		JButton BtnMemoAbbruch = g.getButton("Abbruch");
		final JTextArea EdtMemo = new JTextArea((String)VecAenderung.elementAt(iElement));
		JScrollPane PnlSP = new JScrollPane(EdtMemo);
		final JButton BtnAufrufer = (JButton)ev.getSource();

		JPanel Pnl = new JPanel(new GridLayout(0,2));
		FrmMemo.getContentPane().setLayout(new BorderLayout());

		Pnl.add(BtnMemoOk);
		Pnl.add(BtnMemoAbbruch);
		FrmMemo.getContentPane().add("Center",PnlSP);
		FrmMemo.getContentPane().add("South",Pnl);

		//((JTextField)VecKomponenten.elementAt(i))
		FrmMemo.setSize(200,150);
		//FrmMemo.setLocation( (((JButton)VecKomponenten.elementAt(iElement)).getLocation().x)+10,(((JButton)VecKomponenten.elementAt(iElement)).getLocation().y) );
		FrmMemo.setLocation((BtnAufrufer.getLocationOnScreen().x)+20,(BtnAufrufer.getLocationOnScreen().y)+10);
		FrmMemo.show();
		BtnAufrufer.setEnabled(false);

		BtnMemoOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				VecAenderung.setElementAt(iElement,EdtMemo.getText());
				if(!EdtMemo.getText().equals(""))
					BtnAufrufer.setText(g.getBegriff("Show","Ja"));
				else
					BtnAufrufer.setText(g.getBegriff("Show","Nein"));
				BtnAufrufer.setEnabled(true);
				FrmMemo.dispose();
			}
		});

		BtnMemoAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnAufrufer.setEnabled(true);
				FrmMemo.dispose();
			}
		});
    }
	*/
    /**
     * Image_actionPerformed Method
     */
	/*
    private void Image_actionPerformed(ActionEvent ev)
    {
        final int iElement=new Integer(ev.getActionCommand()).intValue();
		TabSpalten.setPos(iElement);
		final JFrame FrmPic = new JFrame((String)TabSpalten.getInhalt("Bezeichnung"));
		JButton BtnPicOk = g.getButton("Ok");
		JButton BtnPicAbbruch = g.getButton("Abbruch");
		final JTextField EdtFile = new JTextField();
		final ImageIcon icon = null;//new ImageIcon((Image)VecAenderung.elementAt(iElement));
		final JLabel LblPic = new JLabel(icon);
		JScrollPane PnlSP = new JScrollPane(LblPic);
		final JButton BtnAufrufer = (JButton)ev.getSource();

		JPanel Pnl1 = new JPanel(new GridLayout(0,2));
		JPanel Pnl2 = new JPanel(new BorderLayout());
		FrmPic.getContentPane().setLayout(new BorderLayout());

		Pnl1.add(BtnPicOk);
		Pnl1.add(BtnPicAbbruch);
		Pnl2.add("North",EdtFile);
		Pnl2.add("South",Pnl1);
		FrmPic.getContentPane().add("Center",LblPic);
		FrmPic.getContentPane().add("South",Pnl2);

		//((JTextField)VecKomponenten.elementAt(i))
		if(icon!=null)
			FrmPic.setSize(icon.getIconWidth(),icon.getIconHeight()+10);
		else
			FrmPic.setSize(200,150);
		//FrmMemo.setLocation( (((JButton)VecKomponenten.elementAt(iElement)).getLocation().x)+10,(((JButton)VecKomponenten.elementAt(iElement)).getLocation().y) );
		FrmPic.setLocation((BtnAufrufer.getLocationOnScreen().x)+20,(BtnAufrufer.getLocationOnScreen().y)+10);
		FrmPic.show();
		BtnAufrufer.setEnabled(false);


		EdtFile.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
			}
			public void keyPressed(KeyEvent e)
			{
			}
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode()==e.VK_ENTER)
				{
					ImageIcon icon1=new ImageIcon(EdtFile.getText());
					if(icon1!=null)
					{
						LblPic.setIcon(icon1);
						LblPic.setBounds(0,0,icon1.getIconWidth(),icon1.getIconHeight());
						FrmPic.setSize(icon1.getIconWidth(),icon1.getIconHeight()+10);
						EdtFile.setText("jo");
					}
					else
					{
						//LblPic.setIcon(null);
						//FrmPic.setSize(icon.getIconWidth(),icon.getIconHeight()+10);
						EdtFile.setText("na");
					}

				}
			}
		});


		BtnPicOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				if(icon!=null)
				{
					VecAenderung.setElementAt(iElement,icon.getImage());
					BtnAufrufer.setText(g.sJa);
				}
				else
				{
					VecAenderung.setElementAt(iElement,null);
					BtnAufrufer.setText(g.sNein);
				}
				BtnAufrufer.setEnabled(true);
				FrmPic.dispose();
			}
		});

		BtnPicAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnAufrufer.setEnabled(true);
				FrmPic.dispose();
			}
		});
	}*/
    private /*JCOutlinerNode*/Object FuelleZeile(SQL Qry)
    {
		Vector<Comparable> VecSpalten = new Vector<Comparable>();
		Vector<Serializable> VecAIC = new Vector<Serializable>();
		Integer iAIC=new Integer(0);
		iAIC=new Integer(Qry.getS("AIC_"+sKennung));


		TabSpalten.moveFirst();
		for(int i=0;!TabSpalten.eof();i++)
		{
			String sKomponentenname = TabSpalten.getS("Komponentenname");
			String sSpaltenKennung = TabSpalten.getS("Kennung");
			if(sKomponentenname.equals("Checkbox"))
			{
				//int iJN;
				//ConstantDataValue iVor = Qry.getValue((String)TabSpalten.getInhalt("Kennung"));
				if(!Qry.isNull(sSpaltenKennung) && Qry.getB(sSpaltenKennung))
				{
					VecSpalten.addElement(Static.sJa);
					VecAIC.addElement(Boolean.TRUE);

				}
				else
				{
					VecSpalten.addElement(Static.sNein);
					VecAIC.addElement(Boolean.FALSE);

				}
			}
			else if(sKomponentenname.equals("Gesperrt"))
			{
				//ConstantDataValue iVor = Qry.getValue((String)TabSpalten.getInhalt("Kennung"));
				if(!Qry.isNull(sSpaltenKennung))
				{
					VecSpalten.addElement(Static.sJa);
					VecAIC.addElement(Qry.getTS(sSpaltenKennung));
				}
				else
				{
					VecSpalten.addElement(Static.sNein);
					VecAIC.addElement(null);
				}
			}
			else if(sKomponentenname.equals("Spinbox"))
			{
				//ConstantDataValue iVor = Qry.getValue(TabSpalten.getS("Kennung"));
				if(Qry.isNull(sSpaltenKennung))
				{
					VecSpalten.addElement(Static.sKein);
					VecAIC.addElement(new Integer(-2));
				}
				else
				{
					if(Qry.getI(sSpaltenKennung)==-1)
						VecSpalten.addElement(g.getBegriffS("Show","Alle"));
					else
						VecSpalten.addElement(new Integer(Qry.getI(sSpaltenKennung)));

					VecAIC.addElement(new Integer(Qry.getI(sSpaltenKennung)));
				}
			}
			else if(sKomponentenname.equals("Negativ"))
			{
				//ConstantDataValue iVor = Qry.getValue(TabSpalten.getS("Kennung"));
				if(Qry.isNull(sSpaltenKennung))
					VecSpalten.addElement(Static.sKein);
				else
					VecSpalten.addElement(new Integer(Qry.getI(sSpaltenKennung)));

				VecAIC.addElement(new Integer(Qry.getI(sSpaltenKennung)));
			}
			/*else if(sKomponentenname.equals("Schrift"))
			{
				if(Qry.isNull(sSpaltenKennung))
				{
					VecSpalten.addElement(Static.sKein);
					VecAIC.addElement(Static.Int0);
				}
				else
				{
					VecSpalten.addElement( ((Schrift)VecKomponenten.elementAt(i)).getBezeichnung(Qry.getI(sSpaltenKennung)));
					VecAIC.addElement(new Integer(Qry.getI(sSpaltenKennung)));
				}
			}*/
			else if(sKomponentenname.equals("Combobox") || sKomponentenname.equals("Schrift") || sKomponentenname.equals("Eigenschaft") || sKomponentenname.equals("Rolle"))
			{
				//VecSpalten.addElement(Qry.getStringValue(i));

				//ConstantDataValue iVor = Qry.getValue((String)TabSpalten.getInhalt("Kennung"));
				if(Qry.isNull(sSpaltenKennung))
				{
					VecSpalten.addElement(Static.sKein);
					VecAIC.addElement(Static.Int0);
				}
				else
				{
					VecSpalten.addElement( (sKomponentenname.equals("Combobox") ? (ComboSort)VecKomponenten.elementAt(i):
                                            sKomponentenname.equals("Eigenschaft") ? ((EigenschaftsEingabe)VecKomponenten.elementAt(i)).Cbo:
                                            sKomponentenname.equals("Rolle") ? ((RolleEingabe)VecKomponenten.elementAt(i)).Cbo:
                                            ((Schrift)VecKomponenten.elementAt(i)).getCboSchrift()).getBezeichnung(Qry.getI(sSpaltenKennung)));
					VecAIC.addElement(new Integer(Qry.getI(sSpaltenKennung)));
				}
			}
			else if(sKomponentenname.equals("Datum"))
			{
				//VecSpalten.addElement(Qry.getStringValue(i));

				//ConstantDataValue iVor = Qry.getValue((String)TabSpalten.getInhalt("Kennung"));
				if(Qry.isNull(sSpaltenKennung))
				{
					VecSpalten.addElement("");
					VecAIC.addElement(null);
				}
				else
				{
					Date date = Qry.getD(sSpaltenKennung);
					VecSpalten.addElement( DateToString(date));
					VecAIC.addElement(date);
				}
			}
			else if(sKomponentenname.equals("Double"))
			{
				//ConstantDataValue iVor = Qry.getValue((String)TabSpalten.getInhalt("Kennung"));
				if(!Qry.isNull(sSpaltenKennung))
				{
					VecAIC.addElement(new Double(Qry.getF(sSpaltenKennung)));
					VecSpalten.addElement(new Double(Qry.getF(sSpaltenKennung)));
				}
				else
				{
					VecSpalten.addElement("");
					VecAIC.addElement(new Double(0));
				}
			}
			else if(sKomponentenname.equals("Datum/Zeit"))
			{
				//ConstantDataValue iVor = Qry.getValue((String)TabSpalten.getInhalt("Kennung"));
				if(!Qry.isNull(sSpaltenKennung))
				{
					Date date = Qry.getTS(sSpaltenKennung);
					VecSpalten.addElement( DateToString(date));
					VecAIC.addElement(date);
				}
				else
				{
					VecSpalten.addElement("");
					VecAIC.addElement(null);
				}
			}
			else if(sKomponentenname.equals("Zeit"))
			{
				//ConstantDataValue iVor = Qry.getValue((String)TabSpalten.getInhalt("Kennung"));
				if(!Qry.isNull(sSpaltenKennung))
				{
					java.sql.Time time = Qry.getT(sSpaltenKennung);
					VecSpalten.addElement( g.TimeToString(time));
					VecAIC.addElement(time);
				}
				else
				{
					VecSpalten.addElement("");
					VecAIC.addElement(null);
				}
			}
			else if(sKomponentenname.equals("Image"))
			{
				Vector<Object> VecImage = new Vector<Object>();
				//ConstantDataValue iVor = Qry.getValue((String)TabSpalten.getInhalt("Kennung"));
				VecSpalten.addElement(Static.JaNein(!Qry.isNull(sSpaltenKennung)));
				VecImage.addElement(null); //Image übergeben
				VecImage.addElement(null); //Image-Filename übergeben
				VecAIC.addElement(VecImage);
			}
			else if(sKomponentenname.equals("Memo"))
			{
				Vector<String> VecMemo = new Vector<String>();
				//ConstantDataValue iVor = Qry.getValue((String)TabSpalten.getInhalt("Kennung"));

				VecSpalten.addElement(Static.JaNein(!Qry.isNull(sSpaltenKennung)));
				VecMemo.addElement("");
				VecMemo.addElement("");
				VecMemo.addElement("");
				VecAIC.addElement(VecMemo);
			}
			else if(sKomponentenname.equals("Passwort"))
			{
				if(!Qry.isNull(sSpaltenKennung))
				{
					String s="";
					for(long j=Math.round(Math.random()*9)+1;j>0;j--)
						s=s+"*";
					VecSpalten.addElement(s);
					VecAIC.addElement(Qry.getS("Passwort"));
				}
				else
				{
					VecSpalten.addElement("");
					VecAIC.addElement(null);
				}
			}
			else
			{
				VecSpalten.addElement(Qry.getS(TabSpalten.getS("Kennung")));
				VecAIC.addElement(iAIC);
			}

			TabSpalten.moveNext();
		}
		if (bAIC)
			VecSpalten.addElement(iAIC);
		if(sRekursion.equals("") /*|| Qry.getStringValue(sTop).equals("1")*/)
		{
			JCOutlinerNode tree = new JCOutlinerNode(VecSpalten);
			tree.setUserData(VecAIC);
			return(tree);
		}

		else
		{
			JCOutlinerFolderNode tree = new JCOutlinerFolderNode(null,VecSpalten);
			tree.setUserData(VecAIC);
			return(tree);
		}

	}//FuelleZeile
    /**
     * FillCboTabellen Method
     */

    private void FillCboTabellen(String rsTabelle)
    {
		int iAic=0;
		//g.TabTabellenname.moveFirst();
                int iPos=0;
		while(iPos<g.TabTabellenname.size())//!g.TabTabellenname.eof())
		{
			String s = g.TabTabellenname.getS(iPos,"Kennung");
			if (!s.equals("STAMM") && !s.equals("BEFEHL") && !s.equals("SPALTE") && !s.equals("HAUPTSCHIRM") && !s.equals("RASTER") && !s.equals("ABSCHNITT")
                            && !s.equals("TESTTYPEN") && !s.equals("EIGENSCHAFT") && !s.equals("BEWEGUNGSTYP") && !s.equals("FEIERTAG")
                            && !s.equals("BENUTZER") && !s.equals("BENUTZERGRUPPE") && !s.equals("ABSCHLUSSTYP") &&
                            (g.Prog() || !s.equals("TABELLENNAME") && !s.equals("SPALTENNAME") && !s.equals("KOMPONENTE") && !s.equals("ZUORDNUNG") //&& !s.equals("CODE")
                             && !s.equals("MANDANT") && !s.equals("LAND") && (!s.equals("STAMMTYP") || g.Def()) && !s.equals("AUSWAHL") && !s.equals("ZUSTAND") && !s.equals("STATUS")  
				/*&& !s.equals("BEGRIFFGRUPPE")*/ && !s.equals("COMPUTER") && !s.equals("SCHRIFT") /*&& !s.equals("SCHRIFTART")*/ && !s.equals("DRUCKER")))
			{
				int iMom = g.TabTabellenname.getI(iPos,"Aic");
				CboTabellen.addItem(g.TabTabellenname.getS(iPos,"Bezeichnung"),iMom,s);
				if (s.equals(rsTabelle))
					iAic = iMom;
			}
			//g.TabTabellenname.moveNext();
                        iPos++;
		}
		//CboTabellen.setSorted(true);
		if (iAic > 0)
			CboTabellen.setSelectedAIC(iAic);
    }
    /**
     * CboTabellen_itemStateChanged Method
     */

	private String AU_String(String s)
	{
		return s.charAt(0)+s.substring(1).toLowerCase();
	}

    private void CboTabellen_itemStateChanged(ItemEvent event)
    {
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		//tf1.setValue(tf1.intValue()+1);
		bGid_itemStateChanged = false;
		bPasswort=false;
		//sTop="";
		sRekursion="";
		String sSelect="";
		VecUntermenge.removeAllElements();
		iAIC_Tabellenname = CboTabellen.getSelectedAIC();
		int iPos=g.TabTabellenname.getPos("Aic",new Integer(iAIC_Tabellenname));
		//iAIC_Tabellenname = ((Integer)g.TabTabellenname.getInhalt("Aic")).intValue();
		sKennung = g.TabTabellenname.getS(iPos,"Kennung");
		if (event != null)
		{
		  boolean bSN=sKennung.equals("BEGRIFF") || sKennung.equals("CODE") || sKennung.equals("MANDANT") || sKennung.equals("ROLLE") || sKennung.equals("STAMMTYP") || sKennung.equals("SPALTENNAME");
		  CbxSpaltenname.setSelected(bSN);
		  CbxSpaltenname.setEnabled(bSN);		
		}
		Nod = new JCOutlinerFolderNode(null,CboTabellen.getSelectedItem().toString());

		TabSpalten.clearAll();
		VecKomponenten.removeAllElements();

		TabSpalten.addInhalt("Bezeichnung", g.getBegriffS("Show","Bezeichnung"));
		TabSpalten.addInhalt("Kennung", "BEZEICHNUNG");
		TabSpalten.addInhalt("Komponentenname", "Text");
		TabSpalten.addInhalt("Frei", "1");
		TabSpalten.addInhalt("Laenge",new Integer(255));
		TabSpalten.addInhalt("AIC_Begriffgruppe",new Integer(0));
		if (!sKennung.equals("SPRACHE") && !sKennung.equals("SCHRIFT") && !sKennung.equals("MANDANT"))
		{
			TabSpalten.addInhalt("Bezeichnung", g.getBegriffS("Show","Kennung"));
			TabSpalten.addInhalt("Kennung", "KENNUNG");
			TabSpalten.addInhalt("Komponentenname", "Text");
			TabSpalten.addInhalt("Frei", "");
			TabSpalten.addInhalt("Laenge",new Integer(sKennung.equals("BEGRIFF")?98:sKennung.equals("MANDANT")?40:20));
			TabSpalten.addInhalt("AIC_Begriffgruppe",new Integer(0));
		}

		//VecKomponenten.addElement(new JTextField());
		//VecKomponenten.addElement(new JTextField());

		SQL Qry = new SQL(g);
		SQL Qry2= new SQL(g);
		iAICFremd=-1;

		if(CbxSpaltenname.isSelected() && Qry.open("SELECT Spaltenname.Kennung Spalte, Komponente.Kennung Komponente, AIC_Begriffgruppe, Frei, Laenge,Untermenge"+g.AU_Bezeichnung("Spaltenname")+
					" FROM Spaltenname "+g.join("Komponente","Spaltenname")+" WHERE AIC_Tabellenname="+iAIC_Tabellenname+" order by untermenge"))
		{
			while(!Qry.eof())
			{
				String sSpalte = Qry.getS("Spalte").toUpperCase();
				if((Qry.getS("Untermenge").equals("")))
				{
					String sKomponente=Qry.getS("Komponente");
					if(sKomponente.equals("Rekursion"))
						sRekursion= sSpalte;
					//else if(sKomponente.equals("Top"))
					//	sTop=sSpalte;
					else
					{
						if(Qry.getS("Bezeichnung").equals(""))
							TabSpalten.addInhalt("Bezeichnung", Qry.getS("Spalte"));
						else
							TabSpalten.addInhalt("Bezeichnung", Qry.getS("Bezeichnung"));
						TabSpalten.addInhalt("Kennung", sSpalte);
						TabSpalten.addInhalt("Komponentenname", sKomponente);
						TabSpalten.addInhalt("Frei", Qry.getS("Frei"));
						TabSpalten.addInhalt("Laenge", new Integer(Qry.getI("Laenge")));
						int i123 = Qry.getI("AIC_Begriffgruppe");
						TabSpalten.addInhalt("AIC_Begriffgruppe",new Integer(i123));

					}
				}
				else
					VecUntermenge.addElement(sSpalte);

				Qry.moveNext();
			}
			Qry.close();
		}

		Pnl6.removeAll();
		if(sRekursion.equals(""))
		{
			//g.setButton("Neu",BtnNeu);
			Pnl6.setLayout(new GridLayout(0,2,2,5));
			Pnl6.add(BtnNeu);
			Pnl6.add(BtnLoeschen);
		}
		else
		{
			//g.setButton("< Neu",BtnNeu2);
			//g.setButton("Neu >",BtnNeu);
			Pnl6.setLayout(new GridLayout(0,3,2,5));
			Pnl6.add(BtnNeu2);
			Pnl6.add(BtnNeu);
			Pnl6.add(BtnLoeschen);
		}


		if(Qry.open(g.ASA() ?"SELECT Column_name, (SELECT Domain_name FROM sysdomain WHERE Domain_id=Syscolumn.Domain_id), Width, Nulls FROM Syscolumn JOIN Systable WHERE Table_name='"+sKennung+"'":
                      g.Oracle() ?"select column_name,data_type Domain_name,data_length width, NULLABLE nulls,data_precision p from user_tab_columns where table_name='"+sKennung+"'":
                      g.MySQL() ?"select column_name,data_type Domain_name,character_maximum_length width,is_nullable nulls from information_schema.columns where table_schema=DATABASE() and upper(table_name)='"+sKennung.toUpperCase()+"'":
                      g.MS()?"SELECT cast(c.name as varchar) Column_name,cast(t.name as varchar) Domain_name,c.length width,c.isnullable nulls"+
                             " FROM sysobjects o JOIN syscolumns c ON o.id = c.id JOIN systypes t ON c.xusertype = t.xusertype"+
                             " WHERE o.name = '"+sKennung+"' ORDER BY c.colorder":""))
		{
			bGeloescht=false;
                        bLogging=false;
			while(!Qry.eof())
			{
				String s = Qry.getS("Column_name").trim();
				bGeloescht = bGeloescht || s.equalsIgnoreCase("GELOESCHT");
				if((//g.Prog() && s.equalsIgnoreCase("PROG") || g.Def() && s.equalsIgnoreCase("ENTWICKLER") ||
					 /*!s.equalsIgnoreCase("PROG") &&*/ !s.equalsIgnoreCase("ENTWICKLER") /*&& !s.equals("AIC_LOGGING")*/ && !s.equals("DEFBEZEICHNUNG") &&
					!s.equalsIgnoreCase("GELOESCHT") && !s.equals("BITS") /*&& !s.equalsIgnoreCase("ImportZeit")*/ && !s.equals("AIC_"+sKennung.toUpperCase()))
					&& !TabSpalten.existInhalt("Kennung",s) && VecUntermenge.indexOf(s)==-1 && !sRekursion.equals(s) /*&& !sTop.equals(s)*/)
				{
					TabSpalten.addInhalt("Bezeichnung", s.startsWith("AIC") ? AU_String(s.substring(4)) : s.indexOf("_AIC_")==3 ? AU_String(s.substring(8)+"2") : AU_String(s));
					TabSpalten.addInhalt("Kennung", s.equals("AIC_LOGGING")?"LOGGING":s);
					TabSpalten.addInhalt("AIC_Begriffgruppe",new Integer(0));

					String s1 = Qry.getS("Domain_name").trim();
					if(s.equalsIgnoreCase("PASSWORT"))
						TabSpalten.addInhalt("Komponentenname", "Passwort");
                                        else if(s.equalsIgnoreCase("URL"))
						TabSpalten.addInhalt("Komponentenname", "URL");
                                        else if(s.equals("AIC_LOGGING"))
                                        {
                                          TabSpalten.addInhalt("Komponentenname", "Datum/Zeit");
                                          //TabSpalten.addInhalt("Komponentenname", "Spinbox");
                                          bLogging=true;
                                        }
					else if(s1.equals("char") || s1.equals("VARCHAR2") || s1.equals("varchar"))
						TabSpalten.addInhalt("Komponentenname", "Text");
					else if(s1.equals("date"))
						TabSpalten.addInhalt("Komponentenname", "Datum");
					else if(s1.equals("time"))
						TabSpalten.addInhalt("Komponentenname", "Zeit");
					else if(s1.equals("timestamp") || s1.equals("datetime") || g.Oracle() && s1.equals("DATE"))
						TabSpalten.addInhalt("Komponentenname", "Datum/Zeit");
					else if(s1.equals("long binary"))
						TabSpalten.addInhalt("Komponentenname", "Image");
					else if(s1.equals("long varchar"))
						TabSpalten.addInhalt("Komponentenname", "Memo");
                                        else if(s1.equals("integer") || s1.equals("int") || s1.equals("NUMBER") && Qry.getI("P")==10)
						if (s.equals("AIC_SCHRIFT"))
							TabSpalten.addInhalt("Komponentenname", "Schrift");
                                                else if (s.equals("AIC_EIGENSCHAFT"))
							TabSpalten.addInhalt("Komponentenname", "Eigenschaft");
                                                else if (s.equals("AIC_ROLLE"))
							TabSpalten.addInhalt("Komponentenname", "Rolle");
						else if(s.indexOf("AIC_") != -1 && !s.equals("LOG_AIC_LOGGING") || s.equalsIgnoreCase("PROG"))
							TabSpalten.addInhalt("Komponentenname", "Combobox");
						else
							TabSpalten.addInhalt("Komponentenname", "Spinbox");
					else if(s1.equals("smallint") || s1.equals("bit") || s1.equals("NUMBER") && Qry.getI("P")==1)
						TabSpalten.addInhalt("Komponentenname", "Checkbox");
					else if(s1.equals("float") || s1.equals("bigint") || s1.equals("NUMBER"))
						TabSpalten.addInhalt("Komponentenname", "Double");
					else
						Static.printError("Definition.CboTabellen_itemStateChanged(): Komponente "+s1+" nicht definiert!!!");

					if(Qry.getS("Nulls").equals("N"))
						TabSpalten.addInhalt("Frei", "0");
					else
						TabSpalten.addInhalt("Frei", "1");
					TabSpalten.addInhalt("Laenge", new Integer(Qry.getI("Width")));
				}
				Qry.moveNext();
			}
			Qry.close();
		}
		//TabSpalten.showGrid("TabSpalten");
		sSelect = "SELECT "+sKennung+".*"+(sKennung.equals("BEGRIFF")?",DefBezeichnung Bezeichnung":g.AU_Bezeichnung(sKennung))+
                    (bLogging?",(select ein from logging where aic_logging="+sKennung+".aic_logging) logging":"")+" FROM "+sKennung;
                String s =new String(sSelect);

		if(!sRekursion.equals(""))
			s=s+" WHERE "+sRekursion+" IS NULL";

		if(bGeloescht)
			s=s+" WHERE GELOESCHT IS NULL OR GELOESCHT > "+g.von();

		bVorgaenger=VecUntermenge.size()>0;
		if(bVorgaenger)
		{
			s=s+" ORDER BY "+VecUntermenge.elementAt(0);
			String s2 = CutAIC(VecUntermenge.elementAt(0));
			Qry.open("SELECT AIC_"+s2+",Kennung"+g.AU_Bezeichnung(s2)+" FROM "+s2+(sKennung.equals("BEGRIFF")?" where code is null":sKennung.equals("CODE")?" where code=1":"")+" ORDER BY "+VecUntermenge.elementAt(0));
		}

		Qry2.setSQL(s);

		//TabSpalten.showGrid();
		TabSpalten.moveFirst();
		while(!TabSpalten.eof())
		{
                  //g.testInfo("Komponentenname:"+TabSpalten.getInhalt("Komponentenname"));
			if(TabSpalten.getS("Komponentenname").equals("Text"))
				VecKomponenten.addElement(new Text("",TabSpalten.getI("Laenge"),TabSpalten.getS("Kennung").equals("KENNUNG")? Text.KENNUNG:0));
			else if(TabSpalten.getS("Komponentenname").equals("Checkbox"))
				VecKomponenten.addElement(new JCheckBox(TabSpalten.getS("Bezeichnung")));
			else if(TabSpalten.getS("Komponentenname").equals("Spinbox"))
			{
				SpinBox SB = new SpinBox();
				SB.setMinimum(-2);
                                //g.progInfo("Spinbox:"+TabSpalten.getS("Kennung"));
                                if (TabSpalten.getS("Kennung").equals("USED"))
                                {
                                  SB.setMinimum(0);
                                  //SB.setEnabled(false);
                                  //SB.setAutoArrowDisable(true);
                                  //SB.setIncrement(0);
                                  SB.setMaximum(0);
                                  SB.setEditable(false);
                                  //SB.getDecrementArrow().setEnabled(false);
                                  //SB.getIncrementArrow().setEnabled(false);
                                }
				VecKomponenten.addElement(SB);
			}
			else if(TabSpalten.getS("Komponentenname").equals("Negativ"))
			{
				SpinBox SB = new SpinBox();
				SB.setMinimum(-1000);
				VecKomponenten.addElement(SB);
			}
			else if(TabSpalten.getS("Komponentenname").equals("Schrift"))
			{
				Schrift Cbo = new Schrift(g);
				VecKomponenten.addElement(Cbo);
			}
                        else if(TabSpalten.getS("Komponentenname").equals("Eigenschaft"))
                        {
                                EigenschaftsEingabe Cbo = new EigenschaftsEingabe(g,this);
                                Cbo.fillCbo(g.TabEigenschaften,true);
                                VecKomponenten.addElement(Cbo);
                        }
                        else if(TabSpalten.getS("Komponentenname").equals("Rolle"))
                        {
                                RolleEingabe Cbo = new RolleEingabe(g,this);
                                Cbo.fillRolle(-1,true);
                                VecKomponenten.addElement(Cbo);
                        }
			else if(TabSpalten.getS("Komponentenname").equals("Combobox"))
			{
					ComboSort Cbo = new ComboSort(g);
					if(!TabSpalten.getS("Kennung").equalsIgnoreCase("AIC_Fremd"))
					{
						int i = TabSpalten.getI("AIC_Begriffgruppe");
						if (sKennung.equals("BEGRIFF") && TabSpalten.getS("Kennung").equals("AIC_CODE"))
						{
							Cbo.fillBegriffTable(new String[]{"Tabellentyp","Formulartyp"},true);
						}
						else if (sKennung.equals("BEGRIFF") && TabSpalten.getS("Kennung").equals("Prog"))
						{
							Cbo.fillBegriffTable(new String[]{"Programm"},true);
						}                      
						else if(i==0)
						{
							if(TabSpalten.getS("Kennung").equals("AIC_STAMM"))
								/*if (sKennung.equals("LAND"))
								  Cbo.fillStammTable(g.TabStammtypen.getAic("LAND"),true);//Cbo.fillWaehrung(true,false);
								else*/
								  Cbo.fillStammBitTable(g.cstEinheit,TabSpalten.getB("Frei"));
							else
								Cbo.fillDefTable(CutAIC(TabSpalten.getS("Kennung")),TabSpalten.getB("Frei"));
						}
						else
						{
							if(sKennung.equals("MANDANT"))
								Cbo.fillCbo("SELECT Begriff.AIC_Begriff,Begriff.Kennung,DefBezeichnung Bezeichnung from begriff"+g.join("code","Begriff")+" where code.kennung='Start'","Begriff",true,false);
							else
								Cbo.fillBegriffTable(i,TabSpalten.getB("Frei"),true);
						}

					}
					else
						iAICFremd=TabSpalten.getPos();
					VecKomponenten.addElement(Cbo);
			}
			else if(TabSpalten.getS("Komponentenname").equals("Datum"))
				VecKomponenten.addElement(new Datum(g,g.getBegriffS("Show","dd.MM.yyyy")));
			else if(TabSpalten.getS("Komponentenname").equals("Gesperrt"))
				VecKomponenten.addElement(new JCheckBox(TabSpalten.getS("Bezeichnung")));
			else if(TabSpalten.getS("Komponentenname").equals("Double"))
				VecKomponenten.addElement(new Zahl(0.0));
			else if(TabSpalten.getS("Komponentenname").equals("Datum/Zeit"))
				VecKomponenten.addElement(new Datum(g,g.getBegriffS("Show","dd.MM.yyyy  HH:mm:ss")));
			else if(TabSpalten.getS("Komponentenname").equals("Zeit"))
				VecKomponenten.addElement(new Datum(g,g.getBegriffS("Show","HH:mm:ss")));
			else if(TabSpalten.getS("Komponentenname").equals("Image"))
				VecKomponenten.addElement(new JButton());
			else if(TabSpalten.getS("Komponentenname").equals("Memo"))
				VecKomponenten.addElement(new JButton());
			else if(TabSpalten.getS("Komponentenname").equals("Passwort"))
				VecKomponenten.addElement(g.getButton("Benutzerpasswort"));
                        else if(TabSpalten.getS("Komponentenname").equals("URL"))
				VecKomponenten.addElement(new WWW(g));
			else
				VecKomponenten.addElement(null);


			TabSpalten.moveNext();
		}


		if(Qry2.open2())
		{

			int iDarueber=0;
			JCOutlinerFolderNode NodVorgaenger = Nod;
			if(bVorgaenger)
				NodVorgaenger = KnotenUebermenge(Qry);

			while(!Qry2.eof())
			{
				if(bVorgaenger)
				{
					int iMom = Qry2.getI(VecUntermenge.elementAt(0));
					if (iMom != iDarueber)
					{
						while(!Qry.eof() && Qry.getI(VecUntermenge.elementAt(0)) != iMom )
						{
							Qry.moveNext();
							NodVorgaenger = KnotenUebermenge(Qry);
						}
						iDarueber=iMom;
					}
				}

				if(sRekursion.equals("") /*|| Qry2.getB(sTop)*/)
				{
					if (NodVorgaenger != null)
					  NodVorgaenger.addNode((JCOutlinerNode)FuelleZeile(Qry2));
				}
				else
				{
					JCOutlinerFolderNode NodNachfolger=(JCOutlinerFolderNode)FuelleZeile(Qry2);
					NodVorgaenger.addNode(NodNachfolger);
					Rekursion(Qry2.getI("AIC_"+sKennung),NodNachfolger,sSelect);
				}


				Qry2.moveNext();
			}
			Qry2.close();

			if(bVorgaenger)
			{
				while( !Qry.eof() )
				{
					Qry.moveNext();
					KnotenUebermenge(Qry);
				}
				Qry.close();
			}


			Gid.setRootNode(Nod);
			Gid.sortByColumn(0,Sort.sortMethod);
                        Gid.setColumnWidth(0,200);
                        Gid.setColumnWidth(1,100);
			/*Vector VecJCSpalten = new Vector();
			for(int i=0;i<(TabSpalten.getVecSpalte("Bezeichnung")).size();i++)
				VecJCSpalten.addElement((TabSpalten.getVecSpalte("Bezeichnung")).elementAt(i));
				VecJCSpalten.addElement("Nr");*/
			if (bAIC)
			{
				TabSpalten.addInhalt("Bezeichnung",g.getBegriffS("Show","Nr"));
				TabSpalten.addInhalt("Komponentenname","AIC");
			}
			//Gid.setColumnButtons(VecJCSpalten);
			//Gid.setNumColumns(VecJCSpalten.size());
			Gid.setColumnButtons(new jclass.util.JCVector(TabSpalten.getVecSpalte("Bezeichnung")));
			Gid.setNumColumns((TabSpalten.getVecSpalte("Bezeichnung")).size());
		}


		Pnl8.removeAll();
		Pnl9.removeAll();
		TabSpalten.moveFirst();
		for(int i=0;!TabSpalten.eof();i++)
		{
			//VecEV.addElement(new Integer(i));
			String sKomponentenname = TabSpalten.getS("Komponentenname");
			if(sKomponentenname.equals("Text") || sKomponentenname.equals("Double"))
			{
				Pnl8.add(new JLabel("  "+TabSpalten.getS("Bezeichnung")));
				Pnl9.add((Text)VecKomponenten.elementAt(i));
				////////////////////////////////////////////
				//KeyListener für Bezeichnungs-TextFeld//
				////////////////////////////////////////////
				((Text)VecKomponenten.elementAt(i)).addKeyListener(new KeyListener()
				{
					public void keyPressed(KeyEvent ev)
					{
					}
					public void keyReleased(KeyEvent ev)
					{
						//Edt_keyTyped(ev);
  					      EnableButtons();
					}
					public void keyTyped(KeyEvent ev)
					{
					}
				});
			}
			else if(sKomponentenname.equals("Combobox") || sKomponentenname.equals("Schrift") || sKomponentenname.equals("Eigenschaft")
                                || sKomponentenname.equals("Rolle") || sKomponentenname.equals("URL"))
			{
				Pnl8.add(new JLabel("  "+TabSpalten.getS("Bezeichnung")));
				if (sKomponentenname.equals("Combobox"))
					Pnl9.add((ComboSort)VecKomponenten.elementAt(i));
                                else if (sKomponentenname.equals("URL"))
                                        Pnl9.add((WWW)VecKomponenten.elementAt(i));
                                else if (sKomponentenname.equals("Eigenschaft"))
					Pnl9.add((EigenschaftsEingabe)VecKomponenten.elementAt(i));
                                else if (sKomponentenname.equals("Rolle"))
					Pnl9.add((RolleEingabe)VecKomponenten.elementAt(i));
                                else
					Pnl9.add((Schrift)VecKomponenten.elementAt(i));

				if(iAICFremd>-1 && TabSpalten.getS("Kennung").equalsIgnoreCase("AIC_Tabellenname"))
				{
					iAICTab=i;
					//////////////////////////////////////////////
					//ItemListener für die ComboBox der Tabellen//
					//////////////////////////////////////////////
					((ComboSort)VecKomponenten.elementAt(i)).addItemListener(new ItemListener ()
					{
						public void itemStateChanged(ItemEvent ev)
						{
							if(ev.getStateChange() == ItemEvent.SELECTED)
							{
								((ComboSort)VecKomponenten.elementAt(iAICFremd)).fillDefTable(((ComboSort)VecKomponenten.elementAt(iAICTab)).getSelectedKennung(),false);
								EnableButtons();
							}
						}
					});
				}
				else
					//////////////////////////////////////////////
					//ItemListener für die ComboBox der Tabellen//
					//////////////////////////////////////////////
                                        if (sKomponentenname.equals("URL"))
                                          ((WWW)VecKomponenten.elementAt(i)).getEditor().addKeyListener(new KeyListener()
                                          {
                                            public void keyPressed(KeyEvent ev) {
                                            }

                                            public void keyReleased(KeyEvent ev) {
                                              //Edt_keyTyped(ev);
                                              EnableButtons();
                                            }

                                            public void keyTyped(KeyEvent ev) {
                                            }
                                          });
                                        else
					(sKomponentenname.equals("Combobox") ? ((ComboSort)VecKomponenten.elementAt(i)):
                                         sKomponentenname.equals("Eigenschaft") ? ((EigenschaftsEingabe)VecKomponenten.elementAt(i)).Cbo:
                                         sKomponentenname.equals("Rolle") ? ((RolleEingabe)VecKomponenten.elementAt(i)).Cbo:
                                         ((Schrift)VecKomponenten.elementAt(i)).getCboSchrift()).addItemListener(new ItemListener ()
						{
							public void itemStateChanged(ItemEvent ev)
							{
								if(ev.getStateChange() == ItemEvent.SELECTED)
								{
									EnableButtons();
								}
							}
						});
			}
			else if(sKomponentenname.equals("Gesperrt"))
			{
				Pnl8.add(new JLabel("  "+TabSpalten.getS("Bezeichnung")));
				Pnl9.add((JCheckBox)VecKomponenten.elementAt(i));

				////////////////////////////////////
				//ActionListener für Checkbox     //
				////////////////////////////////////
				((JCheckBox)VecKomponenten.elementAt(i)).addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						EnableButtons();
					}
				});

			}
			else if(sKomponentenname.equals("Spinbox") || sKomponentenname.equals("Negativ"))
			{
				Pnl8.add(new JLabel("  "+TabSpalten.getS("Bezeichnung")));
				Pnl9.add((SpinBox)VecKomponenten.elementAt(i));
                                ((SpinBox)VecKomponenten.elementAt(i)).addChangeListener(new ChangeListener()
                                {
                                  public void stateChanged(ChangeEvent ae)
                                  {
                                    EnableButtons();
                                  }
				});

			}
			else if(sKomponentenname.equals("Checkbox"))
			{
				Pnl8.add(new JLabel(""));
				Pnl9.add((JCheckBox)VecKomponenten.elementAt(i));

				////////////////////////////////////
				//ActionListener für Checkbox     //
				////////////////////////////////////
				((JCheckBox)VecKomponenten.elementAt(i)).addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						EnableButtons();
					}
				});

			}
			else if(sKomponentenname.equals("Datum") || sKomponentenname.equals("Datum/Zeit") || sKomponentenname.equals("Zeit"))
			{
				Pnl8.add((Datum)VecKomponenten.elementAt(i),0);
				Pnl9.add(new JLabel(TabSpalten.getS("Bezeichnung")),0);

				////////////////////////////////////////////
				//KeyListener für Bezeichnungs-TextFeld//
				////////////////////////////////////////////
				((Datum)VecKomponenten.elementAt(i)).addKeyListener(new KeyListener()
				{
					public void keyPressed(KeyEvent ev)
					{
					}
					public void keyReleased(KeyEvent ev)
					{
 				       EnableButtons();
					}
					public void keyTyped(KeyEvent ev)
					{
					}
				});
			}
			else if(sKomponentenname.equals("Memo"))
			{
				Pnl8.add(new JLabel("  "+TabSpalten.getS("Bezeichnung")));
				Pnl9.add((JButton)VecKomponenten.elementAt(i));
				((JButton)VecKomponenten.elementAt(i)).setActionCommand(""+i);
				////////////////////////////////////////////
				//KeyListener für Bezeichnungs-TextFeld//
				////////////////////////////////////////////
				((JButton)VecKomponenten.elementAt(i)).addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						int iElement=new Integer(ev.getActionCommand()).intValue();
						TabSpalten.setPos(iElement);
						//g.progInfo(VecAenderung+"/"+VecAenderungTitel+"/"+iElement);
						//new Memo(VecAenderung,VecAenderungTitel,iElement,TabSpalten.getS("Bezeichnung"),thisFrame,g);
						Static.printError("alte Memo-Eingabe existiert nicht mehr");
						//Memo_actionPerformed(ev);
						EnableButtons();
					}
				});
			}
			else if(sKomponentenname.equals("Passwort"))
			{
				bPasswort=true;
				Pnl8.add(new JLabel("  "+TabSpalten.getS("Bezeichnung")));
				Pnl9.add((JButton)VecKomponenten.elementAt(i));
				((JButton)VecKomponenten.elementAt(i)).setActionCommand(""+i);
				////////////////////////////////////////////
				//KeyListener für Bezeichnungs-TextFeld//
				////////////////////////////////////////////
				((JButton)VecKomponenten.elementAt(i)).addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						int iElement=new Integer(ev.getActionCommand()).intValue();
						TabSpalten.setPos(iElement);
						//new Memo(VecAenderung,VecAenderungTitel,iElement,(String)TabSpalten.getInhalt("Bezeichnung"),thisFrame,g);
						//g.printInfo("Tabelle:"+sKennung+" AIC:"+((Integer)((Vector)Gid.getSelectedNode().getUserData()).elementAt(0)).intValue());
						if (sKennung.equals("MANDANT") && Gid.getSelectedNode().getLevel()>0/* || sKennung.equals("BENUTZER") && Gid.getSelectedNode().getLevel()==2*/)
							sPasswort=new Passwort(null,g).setPasswort(sKennung,bNew?0:((Integer)((Vector)Gid.getSelectedNode().getUserData()).elementAt(0)).intValue(),false);
						else
							Static.printError("Definition.CboTabellen_itemStateChanged(): Passwort kann von dieser Ebene nicht aufgerufen werden!");

						//Memo_actionPerformed(ev);
						EnableButtons();
					}
				});
			}



			TabSpalten.moveNext();
		}

		Pnl8.add(new JLabel("  "+g.getBegriffS("Show","Bild")));
		Pnl9.add(BtnImage);
		Pnl8.add(new JLabel("  "+g.getBegriffS("Show","Memo")));
		Pnl9.add(BtnMemo);

		if(sKennung.equals("FARBE"))
		{
			BtnFarbe = new JButton();
                        g.setBack(BtnFarbe,Color.BLACK);
			Pnl8.add(new JLabel(g.getBezeichnung("Tabellenname","FARBE")));
			Pnl9.add(BtnFarbe);

			farbe = new Farbwahl(this,g);
			TabSpalten.posInhalt("Kennung","RED");
			SpinRed = (SpinBox)VecKomponenten.elementAt(TabSpalten.getPos());
			TabSpalten.posInhalt("Kennung","GREEN");
			SpinGreen = (SpinBox)VecKomponenten.elementAt(TabSpalten.getPos());
			TabSpalten.posInhalt("Kennung","BLUE");
			SpinBlue = (SpinBox)VecKomponenten.elementAt(TabSpalten.getPos());

			SpinRed.setMaximum(255);
			SpinRed.setMinimum(0);
			SpinGreen.setMaximum(255);
			SpinGreen.setMinimum(0);
			SpinBlue.setMaximum(255);
			SpinBlue.setMinimum(0);

			BtnFarbe.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{

					Color color = new Color(SpinRed.getIntValue(),SpinGreen.getIntValue(),SpinBlue.getIntValue());

					color = farbe.showDialog(color);

					SpinRed.setIntValue(color.getRed());
					SpinGreen.setIntValue(color.getGreen());
					SpinBlue.setIntValue(color.getBlue());

					BtnFarbe.setBackground(color);

				}
			});
		}

		if(Qry.open("SELECT Titel,Memo FROM Daten_Memo"+g.join("Tabellenname","Daten_Memo")+" WHERE Tabellenname.Kennung='Tabellenname' AND AIC_Sprache="+g.getSprache()+" AND AIC_Fremd="+iAIC_Tabellenname))
		{
			if(!Qry.eof())
			{
				String sTitel = Qry.getS("Titel");
				s = sTitel+"\n";
				for(int i=0;i<sTitel.length();i++)
					s=s+"-";
				TxtHilfe.setText(s+"\n"+Qry.getS("Memo"));
			}
			else
				TxtHilfe.setText("");
			Qry.close();
		}

		Qry.free();
		Qry2.free();

		setSize(getSize().width+1,getSize().height+1);
		setSize(getSize().width-1,getSize().height-1);

		bGid_itemStateChanged=true;
		Gid.folderChanged(Gid.getRootNode());
		Gid_itemStateChanged(null);

		//EnableButtons();
		setCursor(Cursor.getDefaultCursor());
    }

	private JCOutlinerFolderNode KnotenUebermenge(SQL Qry)
	{
		JCOutlinerFolderNode Nod2 = null;
		if(!Qry.eof())
		{
			String sBG = Qry.getS("Kennung");
			if (g.Prog() || !sKennung.equals("BEGRIFF") || g.Def() && (sBG.equals("Abfrage") || sBG.equals("Modell")) ||
				!sBG.equals("Abfrage") && !sBG.equals("Modell") && !sBG.equals("region") && !sBG.equals("Datentyp")&& !sBG.equals("Panel") && !sBG.equals("Vergleich") && !sBG.equals("Grafik"))
			{
				jclass.util.JCVector VecVorgaenger=new jclass.util.JCVector(new String[] {Qry.getS("Bezeichnung").equals("") ? sBG : Qry.getS("Bezeichnung"),sBG});
				Nod2 = new JCOutlinerFolderNode(null,VecVorgaenger);
				Nod2.setUserData(new Integer(Qry.getS(VecUntermenge.elementAt(0))));
				Nod2.setState(BWTEnum.FOLDER_CLOSED);
				Nod.addNode(Nod2);
			}
		}
		return Nod2;
	}//KnotenUebermenge

    @SuppressWarnings("unchecked")
	public void Gid_itemStateChanged(JCItemEvent event)
    {

	    if (bGid_itemStateChanged)
		{

			//sBezeichnung = "";
			JCOutlinerNode tree = Gid.getSelectedNode();

			if (tree!=NodVorher)
			{
				bEnableButtons=false;
				//tf2.setValue(tf2.intValue()+1);
				NodVorher=tree;
				//g.clockInfo("Start",lClock);

				if((tree == null) || bVorgaenger&&(tree.getLevel() < 2) || !bVorgaenger&&(tree.getLevel()==0))
				{
					KomponentenLoeschen();
					//VecBild = new Vector();
					//VecBild.addElement("");
					//VecBild.addElement(null);
					VecMemo = new Vector<String>();
					VecMemo.addElement("");
					VecMemo.addElement("");
					VecMemo.addElement("");
					OldMemo=new Vector(VecMemo);
				}
				else
				{
					Vector label = (Vector)tree.getLabel();

					Vector VecBild=g.getImageVector(sKennung,((Integer)((Vector)tree.getUserData()).elementAt(0)).intValue());
					//OldImage=(Image)VecBild.elementAt(1);
					//OldImageFile=(String)VecBild.elementAt(0);
					//g.progInfo("Vor:<"+OldImageFile+">");
					BtnImage.setValue((Image)VecBild.elementAt(1),(String)VecBild.elementAt(0));
					//g.progInfo("Nach:<"+VecBild.elementAt(0)+">");
					//g.clockInfo("nach Image",lClock);
					VecMemo=g.getMemoVector(sKennung,((Integer)((Vector)tree.getUserData()).elementAt(0)).intValue());
					OldMemo=new Vector(VecMemo);
					if(!VecMemo.elementAt(0).equals(""))
                                        {
                                          BtnMemo.setText(VecMemo.elementAt(0));
                                          g.setTooltip(VecMemo.elementAt(1),BtnMemo);
                                        }
					else
						BtnMemo.setText("...");
					//g.clockInfo("nach Memo",lClock);
                                        String sTab=CboTabellen.getSelectedKennung().toUpperCase();
                                        int iAIC = ((Integer)((Vector)tree.getUserData()).elementAt(0)).intValue();
                                        EdtTooltip.setText(SQL.getString(g,"SELECT Memo2 FROM Tooltip WHERE AIC_Tabellenname="+g.TabTabellenname.getAic(sTab)+
                                            " AND AIC_Fremd="+iAIC+" AND AIC_Sprache="+g.getSprache()));


					VecAenderung.removeAllElements();
					VecAenderungTitel.removeAllElements();
					TabSpalten.moveFirst();
					for(int i=0;!TabSpalten.eof();i++)
					{
						String sKomponentenname = TabSpalten.getS("Komponentenname");
						//g.clockInfo(i+":"+sKomponentenname,lClock);
						if(sKomponentenname.equals("Text"))
						{
							((Text)VecKomponenten.elementAt(i)).setText((String)label.elementAt(i));
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
						else if(sKomponentenname.equals("Schrift"))
						{
							((Schrift)VecKomponenten.elementAt(i)).setSelectedAIC(((Integer)((Vector)tree.getUserData()).elementAt(i)).intValue() );
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
                                                else if(sKomponentenname.equals("Eigenschaft"))
                                                {
                                                        ((EigenschaftsEingabe)VecKomponenten.elementAt(i)).setSelectedAIC(((Integer)((Vector)tree.getUserData()).elementAt(i)).intValue() );
                                                        VecAenderung.addElement(null);
                                                        VecAenderungTitel.addElement(null);
                                                }
                                                else if(sKomponentenname.equals("Rolle"))
                                                {
                                                        ((RolleEingabe)VecKomponenten.elementAt(i)).setSelectedAIC(((Integer)((Vector)tree.getUserData()).elementAt(i)).intValue() );
                                                        VecAenderung.addElement(null);
                                                        VecAenderungTitel.addElement(null);
                                                }
                                                else if(sKomponentenname.equals("URL"))
                                                {
                                                        ((WWW)VecKomponenten.elementAt(i)).setValue((String)label.elementAt(i));
                                                        VecAenderung.addElement(null);
                                                        VecAenderungTitel.addElement(null);
                                                }
						else if(sKomponentenname.equals("Combobox"))
						{
							((ComboSort)VecKomponenten.elementAt(i)).setSelectedAIC(((Integer)((Vector)tree.getUserData()).elementAt(i)).intValue() );
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
						else if(sKomponentenname.equals("Spinbox") || sKomponentenname.equals("Negativ"))
						{
							((SpinBox)VecKomponenten.elementAt(i)).setIntValue(((Integer)((Vector)tree.getUserData()).elementAt(i)).intValue());
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
						else if(sKomponentenname.equals("Checkbox"))
						{
							((JCheckBox)VecKomponenten.elementAt(i)).setSelected(((Boolean)((Vector)tree.getUserData()).elementAt(i)).booleanValue());
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
						else if(sKomponentenname.equals("Gesperrt"))
						{
							Date date = (Date)((Vector)tree.getUserData()).elementAt(i);
							((JCheckBox)VecKomponenten.elementAt(i)).setSelected(date != null);
							if(date == null)
								((JCheckBox)VecKomponenten.elementAt(i)).setText(new SimpleDateFormat().format(new Date()));
							else
								((JCheckBox)VecKomponenten.elementAt(i)).setText(new SimpleDateFormat().format(date));
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
						else if(sKomponentenname.equals("Datum"))
						{
							((Datum)VecKomponenten.elementAt(i)).setDate(((Date)((Vector)tree.getUserData()).elementAt(i)));
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
						else if(sKomponentenname.equals("Double"))
						{
							((Zahl)VecKomponenten.elementAt(i)).setText( ((Double)((Vector)tree.getUserData()).elementAt(i)).toString() );
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
						else if(sKomponentenname.equals("Datum/Zeit"))
						{
							((Datum)VecKomponenten.elementAt(i)).setDate(((Date)((Vector)tree.getUserData()).elementAt(i)));
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
						else if(sKomponentenname.equals("Zeit"))
						{
							((Datum)VecKomponenten.elementAt(i)).setDate(new Date(((java.sql.Time)((Vector)tree.getUserData()).elementAt(i)).getTime()) );
							VecAenderung.addElement(null);
							VecAenderungTitel.addElement(null);
						}
						else if(sKomponentenname.equals("Image"))
						{
							if( ((Vector)((Vector)tree.getUserData()).elementAt(i)).elementAt(0) != null )
							{
								((JButton)VecKomponenten.elementAt(i)).setIcon( new ImageIcon((Image)((Vector)((Vector)tree.getUserData()).elementAt(i)).elementAt(0)) );
								((JButton)VecKomponenten.elementAt(i)).setText("Ja");
								VecAenderung.addElement( new ImageIcon((Image)((Vector)((Vector)tree.getUserData()).elementAt(i)).elementAt(0)) );
								VecAenderungTitel.addElement((Image)((Vector)((Vector)tree.getUserData()).elementAt(i)).elementAt(1));
							}
							else
							{
								((JButton)VecKomponenten.elementAt(i)).setIcon(null);
								((JButton)VecKomponenten.elementAt(i)).setText(Static.sNein);
								VecAenderung.addElement(null);
								VecAenderungTitel.addElement(null);
							}

						}
						else if(sKomponentenname.equals("Memo"))
						{
							String s = (String)((Vector)((Vector)tree.getUserData()).elementAt(i)).elementAt(0);
							((JButton)VecKomponenten.elementAt(i)).setText(Static.JaNein(!(s.equals(""))));
                                                        VecAenderung.addElement(s);
							VecAenderungTitel.addElement((String)((Vector)((Vector)tree.getUserData()).elementAt(i)).elementAt(1));
						}
						else if(sKomponentenname.equals("Passwort"))
						{
							String s= (String)((Vector)tree.getUserData()).elementAt(i);
							sPasswort = s==null?"":new String(s);
						}
						TabSpalten.moveNext();
					}
				}

				if(sKennung.equals("FARBE") && Gid.getSelectedNode() != Gid.getRootNode())
					BtnFarbe.setBackground(new Color(SpinRed.getIntValue(),SpinGreen.getIntValue(),SpinBlue.getIntValue()));

				bNew = false;
				bEnableButtons=true;
				//g.clockInfo("vor Enable",lClock);
				EnableButtons();
				//g.clockInfo("nach Enable",lClock);
			}
		}
		//bGid_itemStateChanged = !bGid_itemStateChanged;
    }
    /**
     * BtnNeu_actionPerformed Method
     */

    public void BtnNeu_actionPerformed(ActionEvent event,boolean bDarunter)
    {
		bNeuDarunter=bDarunter;
        bNew=true;
		EnableButtons();

		((Text)VecKomponenten.elementAt(0)).requestFocus();

		KomponentenLoeschen();

		if(bPasswort)
		{
			sPasswort=new Passwort(null,g).setPasswort(sKennung,0,false);
			EnableButtons();
		}
    }
    /**
     * BtnLoeschen_actionPerformed Method
     */

    public void BtnLoeschen_actionPerformed(ActionEvent event)
    {
      /*if (g.MySQL())
      {
	new Message(Message.WARNING_MESSAGE,this,g).showDialog("bei MySQL nicht erlaubt");
	return;
      }*/
		JCOutlinerNode NodMom = Gid.getSelectedNode();
		//Vector label = (Vector)tree.getLabel();

		int pane = new Message(Message.YES_NO_OPTION,this,g).showDialog("Loeschen",new String[] {""});
		if(pane == Message.YES_OPTION)
		{
			bGid_itemStateChanged=false;
			JCOutlinerFolderNode NodParent = NodMom.getParent();
			Gid.selectNode(NodParent,null);
			if(!sRekursion.equals(""))
			{
				Vector VecNodChildren = NodMom.getChildren();
				String s = "";

				if(VecNodChildren!=null)
				{
					if(NodParent.getLevel()>0)
						s=""+((Vector)NodParent.getUserData()).elementAt(0);
					else
						s="null";
					g.exec("UPDATE "+sKennung+" SET "+sRekursion+"="+s+" WHERE "+sRekursion+"="+((Vector)NodMom.getUserData()).elementAt(0));

					for(int i=0;i<VecNodChildren.size();i++)
						NodParent.addNode((JCOutlinerNode)VecNodChildren.elementAt(i));

				}
				/*else
				{
					SQL.exec(g,"UPDATE "+sKennung+" SET "+sTop+"=1 WHERE AIC_"+sKennung+"="+((Vector)NodParent.getUserData()).elementAt(0));
				}*/
			}

			boolean bRemove=false;
			int iAic = ((Integer)((Vector)NodMom.getUserData()).elementAt(0)).intValue();
			if (sKennung.equals("BEGRIFF"))
				g.exec("delete from defverlauf where aic_begriff="+iAic);
			bRemove=g.exec("DELETE FROM "+sKennung+" WHERE AIC_"+sKennung+"="+iAic)>-2;
			if(bRemove)
			{
				
				g.exec("DELETE FROM Bezeichnung WHERE AIC_Tabellenname="+iAIC_Tabellenname+" AND AIC_Fremd="+iAic);
				g.exec("DELETE FROM Daten_Bild WHERE AIC_Tabellenname="+iAIC_Tabellenname+" AND AIC_Fremd="+iAic);
				g.exec("DELETE FROM Daten_Memo WHERE AIC_Tabellenname="+iAIC_Tabellenname+" AND AIC_Fremd="+iAic);
				if (sKennung.equals("BEGRIFF"))
					g.TabBegriffe.clearInhaltS("Aic",new Integer(iAic));
				else if (sKennung.equals("BEGRIFFGRUPPE"))
					g.TabBegriffgruppen.clearInhaltS("Aic",new Integer(iAic));
				else if (sKennung.equals("CODE"))
					g.TabCodes.clearInhaltS("Aic",new Integer(iAic));
				else if (sKennung.equals("FARBE"))
                                  g.LoadFarbe();
					//g.TabFarbe.clearInhaltS("Aic",new Integer(iAic));
				else if (sKennung.equals("SCHRIFT"))
                                  g.LoadSchrift(true,g.getFontFaktor());
				  //g.TabSchrift.clearInhaltS("Aic",new Integer(iAic));
				else if (sKennung.equals("TABELLENNAME"))
					g.TabTabellenname.clearInhaltS("Aic",new Integer(iAic));
				//else if (sKennung.equals("STAMMTYP"))
				//	g.TabStammtypen.clearInhaltS("Aic",new Integer(iAic));
				//else if (sKennung.equals("BEWEGUNGSTYP"))
				//	g.TabErfassungstypen.clearInhaltS("Aic",new Integer(iAic));
				//else if (sKennung.equals("EIGENSCHAFT"))
				//	g.TabEigenschaften.clearInhaltS("Aic",new Integer(iAic));
			}

			if(!bRemove)
			{
				if(bGeloescht)
				{
					g.exec("UPDATE "+sKennung+" SET geloescht="+g.now()+" WHERE AIC_"+sKennung+"="+((Vector)NodMom.getUserData()).elementAt(0));
					NodParent.removeChild(NodMom);
				}
				else
					new Message(Message.WARNING_MESSAGE,this,g).showDialog("KannNichtLoeschen");
			}
			else
			{
				NodParent.removeChild(NodMom);
			}

			Gid.repaint();
			//NodMom=(JCOutlinerNode) Gid.getSelectedNode();
			//label=(Vector)tree.getLabel();
			bGid_itemStateChanged = true;
			Gid_itemStateChanged(null);
		}
    }
    /**
     * BtnOk_actionPerformed Method
     */

    @SuppressWarnings("unchecked")
	public void BtnOk_actionPerformed(ActionEvent event)
    {
		boolean bKennung_exist=true;
		boolean bVollstaendig = true;
		JCOutlinerNode NodeSelected = Gid.getSelectedNode();

		if(bNew || bEdit && bKennung_changed)
		{
			String s;

			//g.printInfo("Kennung prüfen:"+((JTextField)VecKomponenten.elementAt(1)).getText());
			s = "SELECT AIC_"+sKennung+" AIC,Kennung FROM "+sKennung+" WHERE Kennung='"+((Text)VecKomponenten.elementAt(1)).getText()+"'";
			if(bVorgaenger)
			{
				JCOutlinerNode tree = NodeSelected;
				while(tree.getLevel()>1)
					tree=tree.getParent();
				//g.printInfo("Untermenge:"+VecUntermenge);
				s=s+" AND "+VecUntermenge.elementAt(0)+"="+tree.getUserData();
			}

			//bKennung_exist=new SQL(g).check(s);

			SQL Qry = new SQL(g);
			if(Qry.open(s))
			{
				bKennung_exist=false;
				while(!Qry.eof())
				{
					bKennung_exist=bKennung_exist || bEdit?Qry.getI("AIC")!=((Integer)((Vector)NodeSelected.getUserData()).elementAt(0)).intValue():true;
					Qry.moveNext();
				}
				Qry.close();
			}
			Qry.free();

		}
		else
			bKennung_exist=false;

		TabSpalten.moveFirst();
		for(int i=0;i<VecKomponenten.size();i++)
		{
			if(!TabSpalten.getS("Frei").equals("1"))
				if(TabSpalten.getS("Komponentenname").equals("Text"))
				{
					//g.printInfo("Vollstaendig"+i+" prüfen:"+((JTextField)VecKomponenten.elementAt(i)).getText());
					bVollstaendig= bVollstaendig && !(((Text)VecKomponenten.elementAt(i)).getText()).equals("");
				}

			TabSpalten.moveNext();
		}

		if(bKennung_exist )
			new Message(Message.WARNING_MESSAGE,this,g).showDialog("KennungVorhanden");
		else if(!bVollstaendig)
			new Message(Message.WARNING_MESSAGE,this,g).showDialog("NichtVollstaendig");
		else
		{

			int iAIC = 0;
			if( bNew)
			{
				//g.printInfo("Neu");
				//g.printInfo(""+(5/iAIC));
				TabSpalten.moveFirst();
				Vector<Object> VecElement = new Vector<Object>();
                                Vector<Comparable> VecAIC = new Vector<Comparable>();
				//Save Speichern = new Save();
				//Speichern.add("["+sKennung+"]");

				SQL insert = new SQL(g);
				//g.printInfo("Komponenten");
				for(int i=0;i<VecKomponenten.size();i++)
				{
					String sKomponentenname = TabSpalten.getS("Komponentenname");
                                        //g.progInfo(TabSpalten.getS("Bezeichnung")+"/"+TabSpalten.getS("Kennung")+":"+sKomponentenname);
					if(sKomponentenname.equals("Text"))
					{
                                          if (sKennung.equals("BEGRIFF") && TabSpalten.getS("Kennung").equals("BEZEICHNUNG"))
                                            insert.add("Defbezeichnung",((Text)VecKomponenten.elementAt(i)).getText());
                                          else if(!TabSpalten.getS("Kennung").equals("BEZEICHNUNG"))
                                            insert.add(TabSpalten.getS("Kennung"),((Text)VecKomponenten.elementAt(i)).getText());
                                          VecElement.addElement(((Text)VecKomponenten.elementAt(i)).getText());
                                          VecAIC.addElement(new Integer(0));
						//Speichern.add((String)TabSpalten.getInhalt("Kennung")+"=\""+((JTextField)VecKomponenten.elementAt(i)).getText()+"\"");
					}
					else if(sKomponentenname.equals("Datum") || sKomponentenname.equals("Datum/Zeit"))
					{
						Date date =sKomponentenname.equals("Datum") ? ((Datum)VecKomponenten.elementAt(i)).getDate():((Datum)VecKomponenten.elementAt(i)).getDateTime();
                                                //g.progInfo(sKomponentenname+":"+date);
						if(date==null)
						{
							VecElement.addElement("");
							VecAIC.addElement(null);
						}
						else
						{
                                                  if (!TabSpalten.getS("Kennung").equalsIgnoreCase("Logging"))
                                                    insert.add(TabSpalten.getS("Kennung"),date);
							VecElement.addElement(DateToString(date));
							VecAIC.addElement(date);
							//Speichern.add((String)TabSpalten.getInhalt("Kennung")+"=\""+g.DateToString(date)+"\"");
						}
					}
					else if(sKomponentenname.equals("Schrift"))
					{
						if(((Schrift)VecKomponenten.elementAt(i)).getSelectedAIC()!=0)
						{
							insert.add(TabSpalten.getS("Kennung"),((Schrift)VecKomponenten.elementAt(i)).getSelectedAIC());
							//Speichern.add((String)TabSpalten.getInhalt("Kennung")+"=\""+((ComboSort)VecKomponenten.elementAt(i)).getSelectedKennung()+"\"");
						}
						VecElement.addElement(((Schrift)VecKomponenten.elementAt(i)).getCboSchrift().getSelectedItem());
						VecAIC.addElement(new Integer(((Schrift)VecKomponenten.elementAt(i)).getSelectedAIC()));
					}
                                        else if(sKomponentenname.equals("Eigenschaft"))
                                        {
                                                if(((EigenschaftsEingabe)VecKomponenten.elementAt(i)).getSelectedAIC()!=0)
                                                {
                                                        insert.add(TabSpalten.getS("Kennung"),((EigenschaftsEingabe)VecKomponenten.elementAt(i)).getSelectedAIC());
                                                        //Speichern.add((String)TabSpalten.getInhalt("Kennung")+"=\""+((ComboSort)VecKomponenten.elementAt(i)).getSelectedKennung()+"\"");
                                                }
                                                VecElement.addElement(((EigenschaftsEingabe)VecKomponenten.elementAt(i)).Cbo.getSelectedItem());
                                                VecAIC.addElement(new Integer(((EigenschaftsEingabe)VecKomponenten.elementAt(i)).getSelectedAIC()));
                                        }
                                        else if(sKomponentenname.equals("Rolle"))
                                        {
                                                if(((RolleEingabe)VecKomponenten.elementAt(i)).getSelectedAIC()!=0)
                                                {
                                                        insert.add(TabSpalten.getS("Kennung"),((RolleEingabe)VecKomponenten.elementAt(i)).getSelectedAIC());
                                                        //Speichern.add((String)TabSpalten.getInhalt("Kennung")+"=\""+((ComboSort)VecKomponenten.elementAt(i)).getSelectedKennung()+"\"");
                                                }
                                                VecElement.addElement(((RolleEingabe)VecKomponenten.elementAt(i)).Cbo.getSelectedItem());
                                                VecAIC.addElement(new Integer(((RolleEingabe)VecKomponenten.elementAt(i)).getSelectedAIC()));
                                        }
                                        else if(sKomponentenname.equals("URL"))
                                        {
                                                //if(((Schrift)VecKomponenten.elementAt(i)).getSelectedAIC()!=0)
                                                //{
                                                        insert.add(TabSpalten.getS("Kennung"),((WWW)VecKomponenten.elementAt(i)).getValue());
                                                        //Speichern.add((String)TabSpalten.getInhalt("Kennung")+"=\""+((ComboSort)VecKomponenten.elementAt(i)).getSelectedKennung()+"\"");
                                                //}
                                                VecElement.addElement(((WWW)VecKomponenten.elementAt(i)).getValue());
                                                VecAIC.addElement(Static.Int0);
                                        }
					else if(sKomponentenname.equals("Combobox"))
					{
						if(((ComboSort)VecKomponenten.elementAt(i)).getSelectedAIC()!=0)
						{
							insert.add(TabSpalten.getS("Kennung"),((ComboSort)VecKomponenten.elementAt(i)).getSelectedAIC());
							//Speichern.add((String)TabSpalten.getInhalt("Kennung")+"=\""+((ComboSort)VecKomponenten.elementAt(i)).getSelectedKennung()+"\"");
						}
						VecElement.addElement(((ComboSort)VecKomponenten.elementAt(i)).getSelectedItem());
						VecAIC.addElement(new Integer(((ComboSort)VecKomponenten.elementAt(i)).getSelectedAIC()));
					}
					else if(sKomponentenname.equals("Spinbox"))
					{
						if(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()>-2)
						{
							insert.add(TabSpalten.getS("Kennung"),((SpinBox)VecKomponenten.elementAt(i)).getIntValue());
							//Speichern.add((String)TabSpalten.getInhalt("Kennung")+"="+((SpinBox)VecKomponenten.elementAt(i)).getIntValue());
							if(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()==-1)
								VecElement.addElement(g.getBegriffS("Show","Alle"));
							else
								VecElement.addElement(new Integer(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()));
						}
						else
							VecElement.addElement(Static.sKein);

						VecAIC.addElement(new Integer(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()));
					}
					else if(sKomponentenname.equals("Negativ"))
					{
						insert.add(TabSpalten.getS("Kennung"),((SpinBox)VecKomponenten.elementAt(i)).getIntValue());
						//Speichern.add((String)TabSpalten.getInhalt("Kennung")+"="+((SpinBox)VecKomponenten.elementAt(i)).getIntValue());
						VecElement.addElement(new Integer(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()));
						VecAIC.addElement(new Integer(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()));
					}
					else if(sKomponentenname.equals("Checkbox"))
					{
						if( ((JCheckBox)VecKomponenten.elementAt(i)).isSelected() )
						{
							insert.add(TabSpalten.getS("Kennung"),1);
							VecElement.addElement(Static.sJa);
							VecAIC.addElement(new Boolean(true));
							//Speichern.add((String)TabSpalten.getInhalt("Kennung")+"=1");
						}
						else
						{
							VecElement.addElement(Static.sNein);
							VecAIC.addElement(new Boolean(false));
						}
					}
					else if(sKomponentenname.equals("Gesperrt"))
					{
						if( ((JCheckBox)VecKomponenten.elementAt(i)).isSelected() )
						{
							insert.addnow(TabSpalten.getS("Kennung"));
							VecElement.addElement(Static.sJa);
							VecAIC.addElement(new Date());
							//Speichern.add((String)TabSpalten.getInhalt("Kennung")+"=1");
						}
						else
						{
							VecElement.addElement(Static.sNein);
							VecAIC.addElement(null);
						}
					}
					else if(sKomponentenname.equals("Passwort"))
					{
						insert.add(TabSpalten.getS("Kennung"),new String(sPasswort));
						VecElement.addElement("*******");
						VecAIC.addElement(new String(sPasswort));
					}


					TabSpalten.moveNext();
				}
				//g.printInfo("TabTabellenname");
				//int iPos=g.TabTabellenname.getPos("Kennung",sKennung);
				//if( ((Boolean)g.TabTabellenname.getInhalt("UseSystem")).booleanValue())
				//	insert.add("System",CboSystem.getSelectedAIC());
				//g.printInfo("NodMom");
				JCOutlinerNode NodMom = Gid.getSelectedNode();
				if(bVorgaenger)
				{
					while(NodMom.getLevel()>1)
						NodMom=NodMom.getParent();
					insert.add(VecUntermenge.elementAt(0),((Integer)NodMom.getUserData()).intValue());
				}
				if (/*sKennung.equals("STAMMTYP") || sKennung.equals("BEWEGUNGSTYP") ||*/ sKennung.equals("BEGRIFF"))// || sKennung.equals("EIGENSCHAFT"))
					insert.add("aic_logging",g.getLog());
				//g.printInfo("sRekursion");
				bGid_itemStateChanged=false;
				if(!sRekursion.equals(""))
				{
					if(bNeuDarunter)
					{
						if(NodMom.getLevel()>0)
						{
							int i = ((Integer)((Vector)NodMom.getUserData()).elementAt(0)).intValue();
							insert.add(sRekursion,i);
							if(NodMom.getChildren()==null)
							{
								//Gid.selectNode(Gid.getNextNode(NodMom),null);
								Vector Vec = (Vector)NodMom.getLabel();
								Vector Vec2 = (Vector)NodMom.getUserData();
								JCOutlinerFolderNode NodMomParent = NodMom.getParent();
								NodMomParent.removeChild(NodMom);
								NodMom=new JCOutlinerFolderNode(Vec);
								NodMom.setUserData(Vec2);
								NodMomParent.addNode(NodMom);

								//SQL update_top = new SQL(g);
								//update_top.add(sTop,"");
								//update_top.update(sKennung,i);
							}
						}
						//insert.add(sTop,1);
						iAIC = insert.insert(sKennung,true);
					}
					else
					{
						//Gid.selectNode(Gid.getNextNode(NodMom),null);
						JCOutlinerFolderNode NodMomParent = NodMom.getParent();
						if(NodMomParent.getLevel()>0)
							insert.add(sRekursion,((Integer)((Vector)NodMomParent.getUserData()).elementAt(0)).intValue());

						iAIC = insert.insert(sKennung,true);

						SQL update_vorg = new SQL(g);
						update_vorg.add(sRekursion,iAIC);
						update_vorg.update(sKennung,((Integer)((Vector)NodMom.getUserData()).elementAt(0)).intValue());

						NodMomParent.removeChild(NodMom);
						JCOutlinerFolderNode NodDummy = new JCOutlinerFolderNode(new Object());
						NodMomParent.addNode(NodDummy);
						NodDummy.addNode(NodMom);
						NodMom=NodDummy;

						update_vorg.free();

					}

				}
				else
					iAIC = insert.insert(sKennung,true);

				insert.free();

				bGid_itemStateChanged=true;
				//g.printInfo("VecAIC:"+VecAIC);
				VecAIC.setElementAt(new Integer(iAIC),0);

				TabSpalten.moveFirst();
				//g.printInfo("setBezeichnung:"+VecKomponenten.elementAt(0));
                                if (!sKennung.equals("BEGRIFF"))
                                  g.setBezeichnung("",((Text)VecKomponenten.elementAt(0)).getText(),iAIC_Tabellenname,iAIC,0);
				//g.printInfo("setImage:<"+VecBild.elementAt(0)+">");
                                if (EdtTooltip.Modified())
                                  g.setTooltip(EdtTooltip.getValue(),iAIC_Tabellenname,iAIC,g.getSprache());
				g.setImage(BtnImage.getFilename(),iAIC_Tabellenname,iAIC,Global.iSpSw);
                                BtnImage.Reset();
				//OldImage=(Image)VecBild.elementAt(1);
				//OldImageFile=(String)VecBild.elementAt(0);
				//g.printInfo("OldImageFile:<"+OldImageFile+">");
				//g.printInfo("setMemo:"+VecMemo);
				if(!(OldMemo.elementAt(1)).equals(VecMemo.elementAt(1)) || !(OldMemo.elementAt(0)).equals(VecMemo.elementAt(0)) || !(OldMemo.elementAt(2)).equals(VecMemo.elementAt(2)))
					g.setMemo(VecMemo.elementAt(1),VecMemo.elementAt(0),VecMemo.elementAt(2),iAIC_Tabellenname,iAIC,0);
				OldMemo = new Vector(VecMemo);

				if (bAIC)
				  VecElement.addElement(new Integer(iAIC));

				JCOutlinerNode tree = new JCOutlinerNode(VecElement);
				tree.setUserData(VecAIC);

				if(sRekursion.equals(""))
				{
					if(bVorgaenger)
						((JCOutlinerFolderNode)NodMom).addNode(tree);

					else
						Nod.addNode(tree);
				}
				else
				{
					if(bNeuDarunter)
						((JCOutlinerFolderNode)NodMom).addNode(tree);
					else
					{
						NodMom.setLabel(VecElement);
						NodMom.setUserData(VecAIC);
					}
				}

				//g.printInfo("Refresh_Tab_in_Global");
				Refresh_Tab_in_Global(iAIC);
				//g.printInfo("sortByColumn");
				//Gid.sortByColumn(0,Static.sortMethod);
				Gid.folderChanged(Gid.getRootNode());
				Gid.selectNode(tree,null);

				/*if(CbxSave.isSelected())
				{
					File filSave = new File("file:/N:/Analyse/Sys"+CboSystem.getSelectedAIC()+".all");
					Speichern.save(filSave,filSave.exists());
				}*/

				bNew = false;
				bGid_itemStateChanged=true;
				Gid_itemStateChanged(null);
			}
			else
			{
				Vector<Object> VecElement = new Vector<Object>();
				Vector<Comparable> VecAIC = new Vector<Comparable>();
				SQL update= new SQL(g);
				JCOutlinerNode tree = NodeSelected;

				iAIC = ((Integer)((Vector)tree.getUserData()).elementAt(0)).intValue();
				//int iAicVorher = iAIC;

				TabSpalten.moveFirst();
				for(int i=0;i<VecKomponenten.size();i++)
				{
					String sKomponentenname = TabSpalten.getS("Komponentenname");
                                        if(sKomponentenname.equals("Text"))
                                        {
                                          if (sKennung.equals("BEGRIFF") && TabSpalten.getS("Kennung").equals("BEZEICHNUNG"))
                                            update.add("Defbezeichnung",((Text)VecKomponenten.elementAt(i)).getText());
                                          else if(!TabSpalten.getS("Kennung").equals("BEZEICHNUNG"))
                                            update.add(TabSpalten.getS("Kennung"),((Text)VecKomponenten.elementAt(i)).getText());
                                          VecElement.addElement(((Text)VecKomponenten.elementAt(i)).getText());
                                          VecAIC.addElement(new Integer(iAIC));
					}
					else if(sKomponentenname.equals("Datum") || sKomponentenname.equals("Datum/Zeit"))
					{
						//Date date =((Datum)VecKomponenten.elementAt(i)).getDate();
                                                Date date =sKomponentenname.equals("Datum") ? ((Datum)VecKomponenten.elementAt(i)).getDate():((Datum)VecKomponenten.elementAt(i)).getDateTime();
                                                g.progInfo(TabSpalten.getS("Kennung")+"/"+sKomponentenname+":"+date);
						if(date==null)
						{
							VecElement.addElement("");
							VecAIC.addElement(null);
                                                        if (!TabSpalten.getS("Kennung").equalsIgnoreCase("Logging"))
                                                          update.add0(TabSpalten.getS("Kennung"),0);
						}
						else
						{
                                                  if (!TabSpalten.getS("Kennung").equalsIgnoreCase("Logging"))
                                                    update.add(TabSpalten.getS("Kennung"),date);
                                                  VecElement.addElement(DateToString(date));
                                                  VecAIC.addElement(date);
						}
					}
					else if(sKomponentenname.equals("Schrift"))
					{
						if(((Schrift)VecKomponenten.elementAt(i)).getSelectedAIC()!=0)
							update.add(TabSpalten.getS("Kennung"),((Schrift)VecKomponenten.elementAt(i)).getSelectedAIC());
						else
							update.add(TabSpalten.getS("Kennung"),"");
						VecElement.addElement(((Schrift)VecKomponenten.elementAt(i)).getCboSchrift().getSelectedItem());
						VecAIC.addElement(new Integer(((Schrift)VecKomponenten.elementAt(i)).getSelectedAIC()));
					}
                                        else if(sKomponentenname.equals("Eigenschaft"))
                                        {
                                                if(((EigenschaftsEingabe)VecKomponenten.elementAt(i)).getSelectedAIC()!=0)
                                                        update.add(TabSpalten.getS("Kennung"),((EigenschaftsEingabe)VecKomponenten.elementAt(i)).getSelectedAIC());
                                                else
                                                        update.add(TabSpalten.getS("Kennung"),"");
                                                VecElement.addElement(((EigenschaftsEingabe)VecKomponenten.elementAt(i)).Cbo.getSelectedItem());
                                                VecAIC.addElement(new Integer(((EigenschaftsEingabe)VecKomponenten.elementAt(i)).getSelectedAIC()));
                                        }
                                        else if(sKomponentenname.equals("Rolle"))
                                        {
                                                if(((RolleEingabe)VecKomponenten.elementAt(i)).getSelectedAIC()!=0)
                                                        update.add(TabSpalten.getS("Kennung"),((RolleEingabe)VecKomponenten.elementAt(i)).getSelectedAIC());
                                                else
                                                        update.add(TabSpalten.getS("Kennung"),"");
                                                VecElement.addElement(((RolleEingabe)VecKomponenten.elementAt(i)).Cbo.getSelectedItem());
                                                VecAIC.addElement(new Integer(((RolleEingabe)VecKomponenten.elementAt(i)).getSelectedAIC()));
                                        }
                                        else if(sKomponentenname.equals("URL"))
                                        {
                                                update.add(TabSpalten.getS("Kennung"),((WWW)VecKomponenten.elementAt(i)).getValue());
                                                VecElement.addElement(((WWW)VecKomponenten.elementAt(i)).getValue());
                                                VecAIC.addElement(Static.Int0);
                                        }
					else if(sKomponentenname.equals("Combobox"))
					{
						if(((ComboSort)VecKomponenten.elementAt(i)).getSelectedAIC()!=0)
							update.add(TabSpalten.getS("Kennung"),((ComboSort)VecKomponenten.elementAt(i)).getSelectedAIC());
						else
							update.add(TabSpalten.getS("Kennung"),"");
						VecElement.addElement(((ComboSort)VecKomponenten.elementAt(i)).getSelectedItem());
						VecAIC.addElement(new Integer(((ComboSort)VecKomponenten.elementAt(i)).getSelectedAIC()));
					}
					else if(sKomponentenname.equals("Spinbox"))
					{

						if(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()>-2)
						{
							update.add(TabSpalten.getS("Kennung"),((SpinBox)VecKomponenten.elementAt(i)).getIntValue());
							if(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()==-1)
								VecElement.addElement(g.getBegriffS("Show","Alle"));
							else
								VecElement.addElement(new Integer(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()));
						}
						else
						{
							VecElement.addElement(Static.sKein);
							update.add(TabSpalten.getS("Kennung"),"");
						}

						VecAIC.addElement(new Integer(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()));
					}
					else if(sKomponentenname.equals("Negativ"))
					{
						update.add(TabSpalten.getS("Kennung"),((SpinBox)VecKomponenten.elementAt(i)).getIntValue());
						VecElement.addElement(new Integer(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()));
						VecAIC.addElement(new Integer(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()));
					}
					else if(sKomponentenname.equals("Checkbox"))
					{
						if( ((JCheckBox)VecKomponenten.elementAt(i)).isSelected() )
						{
							update.add(TabSpalten.getS("Kennung"),1);
							VecElement.addElement(Static.sJa);
							VecAIC.addElement(new Boolean(true));
						}
						else
						{
							update.add(TabSpalten.getS("Kennung"),"");
							VecElement.addElement(Static.sNein);
							VecAIC.addElement(new Boolean(false));
						}
					}
					else if(sKomponentenname.equals("Gesperrt"))
					{
						if( ((JCheckBox)VecKomponenten.elementAt(i)).isSelected() )
						{
							update.addnow(TabSpalten.getS("Kennung"));
							VecElement.addElement(Static.sJa);
							VecAIC.addElement(new Date());
						}
						else
						{
							update.add(TabSpalten.getS("Kennung"),"");
							VecElement.addElement(Static.sNein);
							VecAIC.addElement(null);
						}
					}
					else if(sKomponentenname.equals("Passwort"))
					{
						update.add(TabSpalten.getS("Kennung"),sPasswort);
						VecElement.addElement("*******");
						VecAIC.addElement(sPasswort);
					}

					TabSpalten.moveNext();
				}

				if(bBezeichnung_changed && !sKennung.equals("BEGRIFF"))
					g.setBezeichnung( (String)((Vector)tree.getLabel()).elementAt(0),((Text)VecKomponenten.elementAt(0)).getText(),iAIC_Tabellenname,iAIC,0);
                                if (EdtTooltip.Modified())
                                  g.setTooltip(EdtTooltip.getValue(),iAIC_Tabellenname,iAIC,g.getSprache());
                                EdtTooltip.setAktText(EdtTooltip.getValue());
				g.setImage(BtnImage.getFilename(),iAIC_Tabellenname,iAIC,Global.iSpSw);
                                BtnImage.Reset();
				//OldImage=(Image)VecBild.elementAt(1);
				//OldImageFile=(String)VecBild.elementAt(0);

				if(!(OldMemo.elementAt(1)).equals(VecMemo.elementAt(1)) || !(OldMemo.elementAt(0)).equals(VecMemo.elementAt(0)) || !(OldMemo.elementAt(2)).equals(VecMemo.elementAt(2)))
					g.setMemo(VecMemo.elementAt(1),VecMemo.elementAt(0),VecMemo.elementAt(2),iAIC_Tabellenname,iAIC,0);
				OldMemo = new Vector(VecMemo);

				if (/*sKennung.equals("STAMMTYP") || sKennung.equals("BEWEGUNGSTYP") ||*/ sKennung.equals("BEGRIFF"))// || sKennung.equals("EIGENSCHAFT"))
					update.add("aic_logging",g.getLog());

				if(bKennung_changed || bSonst_changed)
						update.update(sKennung,iAIC);

				update.free();

				tree.setUserData(VecAIC);

				if (bAIC)
				  VecElement.addElement(new Integer(iAIC));

				tree.setLabel(VecElement);

				Refresh_Tab_in_Global(iAIC);

				Gid.folderChanged(Gid.getRootNode());
				Gid.selectNode(tree,null);

				EnableButtons();
			}
		}
    }//BtnOk_actionPerformed

	private void Refresh_Tab_in_Global(int iAIC)
	{
		String slKennung 	= TabSpalten.posInhalt("Kennung","KENNUNG") ? ((Text)VecKomponenten.elementAt(TabSpalten.getPos())).getText() : "";
		String slBezeichnung= TabSpalten.posInhalt("Kennung","BEZEICHNUNG") ? Static.beiLeer(((Text)VecKomponenten.elementAt(TabSpalten.getPos())).getText(),slKennung) : "";

		if(sKennung.equals("BEGRIFF"))
		{
			//g.printInfo("Refresh BEGRIFF");
			int iGruppe = CbxSpaltenname.isSelected()?((Integer)(Gid.getSelectedNode().getLevel() == 1 ? Gid.getSelectedNode() : Gid.getSelectedNode().getParent()).getUserData()).intValue():
						  TabSpalten.posInhalt("Kennung","AIC_BEGRIFFGRUPPE") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0;

			int iSchrift = TabSpalten.posInhalt("Kennung","AIC_SCHRIFT") ? ((Schrift)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
			int iURL = TabSpalten.posInhalt("Kennung","URL") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0;
			int iTyp = TabSpalten.posInhalt("Kennung","AIC_CODE") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
			int iStt = TabSpalten.posInhalt("Kennung","AIC_STAMMTYP") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
			int iErf = TabSpalten.posInhalt("Kennung","AIC_BEWEGUNGSTYP") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
                        int iRolle = TabSpalten.posInhalt("Kennung","AIC_ROLLE") ? ((RolleEingabe)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
			String sHK=TabSpalten.posInhalt("Kennung","HOTKEY") ? ((Text)VecKomponenten.elementAt(TabSpalten.getPos())).getText() : null;
                        if (sHK != null && sHK.length()!=1)
                          sHK=null;
                        boolean bTod=TabSpalten.posInhalt("Kennung","TOD") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected():false;
			//int iBits = TabSpalten.posInhalt("Kennung","BITS") ? ((SpinBox)VecKomponenten.elementAt(TabSpalten.getPos())).getIntValue() : 0 ;
			String sBild = BtnImage.getFilename();
			//g.printInfo("vor putTabBegriffe");
			//if (CbxSpaltenname.isSelected())
                        g.putTabBegriffe(iGruppe,iAIC,slKennung,slBezeichnung,slBezeichnung,sBild,iSchrift,""/*sURL*/,iTyp,0,iStt,iErf,iRolle,-1,false,false/*Web*/,0,sHK,bTod,null,null,null,null,null,bNew);
			/*else
			{
				g.TabBegriffe.clearInhalt("Aic",new Integer(iAIC));
				g.putTabBegriffe(iGruppe,iAIC,slKennung,slBezeichnung,slBezeichnung,sBild,iSchrift,iURL,iTyp,iStt,iErf,new All_Unlimited.Allgemein.Anzeige.Memo1((String)VecMemo.elementAt(1)).toString(),0,0,0,true);
			}*/
			//g.printInfo("nach putTabBegriffe");
			//g.TabBegriffe.showGrid();
		}
                else if(sKennung.equals("ROLLE"))
                {
                  int iStt = TabSpalten.posInhalt("Kennung","AIC_STAMMTYP") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
                  int iDavor = CbxSpaltenname.isSelected()?(Gid.getSelectedNode().getLevel() < 2 ? 0 :bNeuDarunter  ? ((Integer)((Vector)Gid.getSelectedNode().getUserData()).elementAt(0)).intValue():
                                  Gid.getSelectedNode().getLevel() == 0 ? 0 :((Integer)((Vector)Gid.getSelectedNode().getParent().getUserData()).elementAt(0)).intValue()):
                                  TabSpalten.posInhalt("Kennung","ROL_AIC_ROLLE") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0;
                  g.putTabRolle(iAIC,slKennung,slBezeichnung,"",iStt,iDavor /* Fehler !!! */,bNew);
                }
		else if(sKennung.equals("BEGRIFFGRUPPE"))
		{
			int iPos=g.TabBegriffgruppen.getPos(iAIC,slKennung,false,slBezeichnung,bNew,-1);
			g.TabBegriffgruppen.setInhalt(iPos,"Filename",BtnImage.getFilename());
			g.TabBegriffgruppen.setInhalt(iPos,"Code",new Boolean(TabSpalten.posInhalt("Kennung","CODE") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false));
			g.TabBegriffgruppen.setInhalt(iPos,"Anweisung",new Boolean(TabSpalten.posInhalt("Kennung","ANWEISUNG") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false));
			//g.TabBegriffgruppen.showGrid();
		}
		/*else if(sKennung.equals("CODE"))
		{
			int iGruppe = CbxSpaltenname.isSelected()?((Integer)(Gid.getSelectedNode().getLevel() == 1 ? Gid.getSelectedNode() : Gid.getSelectedNode().getParent()).getUserData()).intValue():
						  TabSpalten.posInhalt("Kennung","AIC_BEGRIFFGRUPPE") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0;
			if (CbxSpaltenname.isSelected())
				g.putTabCodes(iGruppe,iAIC,slKennung,slBezeichnung,bNew);
			else
			{
				g.TabCodes.clearInhalt("Aic",new Integer(iAIC));
				g.putTabCodes(iGruppe,iAIC,slKennung,slBezeichnung,true);
			}
			//g.TabCodes.showGrid();
		}*/
		/*else if(sKennung.equals("TABELLENNAME"))
		{
			//boolean blVirtuell 	= TabSpalten.posInhalt("Kennung","VIRTUELL") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
			//boolean blUseSystem = TabSpalten.posInhalt("Kennung","USESYSTEM") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
			boolean blFixe_Sprache = TabSpalten.posInhalt("Kennung","FIXE_SPRACHE") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
			g.putTabTabellenname(iAIC,slKennung,slBezeichnung,blFixe_Sprache,BtnImage.getFilename(),bNew);

			//b=false;
			//CboTabellen.Clear();
			//FillCboTabellen();
			//b=true;

		}*/
		else if(sKennung.equals("SCHRIFT"))
                  g.LoadSchrift(true,g.getFontFaktor());
		/*{
			int iFarbe = TabSpalten.posInhalt("Kennung","AIC_FARBE") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
			String slSchriftart=TabSpalten.posInhalt("Kennung","AIC_SCHRIFTART") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedKennung() : "" ;
			int iBold=TabSpalten.posInhalt("Kennung","BOLD") && ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() ? 1 : 0;
			int iItalic=TabSpalten.posInhalt("Kennung","ITALIC") && ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() ? 1 : 0;
			int iSize=TabSpalten.posInhalt("Kennung","SIZE") ? ((SpinBox)VecKomponenten.elementAt(TabSpalten.getPos())).getIntValue() : 8;
			Font font=new Font(slSchriftart,iBold*Font.BOLD+iItalic*Font.ITALIC,iSize);
			g.putTabSchrift(iAIC,slKennung,slBezeichnung,font,iFarbe,bNew);
			//g.TabSchrift.showGrid();
		}*/
		else if(sKennung.equals("FARBE"))
		{
                  g.LoadFarbe();
			//g.putTabFarbe(iAIC,slKennung,slBezeichnung,BtnFarbe.getBackground(),bNew);
		}
		/*else if(sKennung.equals("STAMMTYP"))
		{
			//String sBild = (String) VecBild.elementAt(0);
			int iDarunter = CbxSpaltenname.isSelected()?(Gid.getSelectedNode().getLevel() < 2 ? 0 :bNeuDarunter  ? ((Integer)((Vector)Gid.getSelectedNode().getUserData()).elementAt(0)).intValue():
				Gid.getSelectedNode().getLevel() == 0 ? 0 :((Integer)((Vector)Gid.getSelectedNode().getParent().getUserData()).elementAt(0)).intValue()):
				TabSpalten.posInhalt("Kennung","STA_AIC_STAMMTYP") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0;
			boolean bEnde 	= TabSpalten.posInhalt("Kennung","ENDE") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
			boolean bTranslate = TabSpalten.posInhalt("Kennung","translate") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
			boolean bBenutzer 	= TabSpalten.posInhalt("Kennung","BENUTZER") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
			boolean bEinheit 	= TabSpalten.posInhalt("Kennung","EINHEIT") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
			boolean bLizenz 	= TabSpalten.posInhalt("Kennung","LIZENZ") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
			boolean bCopy 	= TabSpalten.posInhalt("Kennung","COPY") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;

			int iBits=(bEnde?Global.cstEnde:0)+(bTranslate?Global.cstTranslate:0)+(bBenutzer?Global.cstBenutzer:0)+(bEinheit?Global.cstEinheit:0)+(bLizenz?Global.cstLizenz:0)+(bCopy?Global.cstCopy:0);
			//g.progInfo(sKennung+":"+bEnde+"/"+bTranslate+"/"+bBenutzer+"->"+iBits);
                        g.putTabStammtyp(iAIC,slKennung,slBezeichnung,BtnImage.getFilename(),iDarunter,iBits,bNew);
			//g.TabStammtypen.showGrid();
		}*/
		/*else if(sKennung.equals("BEWEGUNGSTYP"))
		{
                  boolean bTIMER 	= TabSpalten.posInhalt("Kennung","Timer") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
                  boolean bLOKALTIMER   = TabSpalten.posInhalt("Kennung","LOKALTIMER") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
                  boolean bNurModell 	= TabSpalten.posInhalt("Kennung","NUR_MODELL") ? ((JCheckBox)VecKomponenten.elementAt(TabSpalten.getPos())).isSelected() : false;
                  int iBits=(bTIMER?g.cstTimer:0)+(bLOKALTIMER?g.cstLokalTimer:0)+(bNurModell?g.cstNurModell:0);
                  //g.progInfo(sKennung+":"+bTIMER+"/"+bLOKALTIMER+"/"+bNurModell+"->"+iBits);
                  g.putTabErfassungstyp(iAIC,slKennung,slBezeichnung,BtnImage.getFilename(),iBits,bNew);
		}
		else if(sKennung.equals("EIGENSCHAFT"))
		{
			String slDatentyp = TabSpalten.posInhalt("Kennung","AIC_BEGRIFF") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedKennung() : "" ;
			String sFormat = TabSpalten.posInhalt("Kennung","FORMAT") ? ((Text)VecKomponenten.elementAt(TabSpalten.getPos())).getText() : "" ;
			Integer iSize = TabSpalten.posInhalt("Kennung","FELDLAENGE") ? SpinToInt((SpinBox)VecKomponenten.elementAt(TabSpalten.getPos())) : null;
			int iStamm = TabSpalten.posInhalt("Kennung","AIC_STAMM") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
			int iStammtyp = TabSpalten.posInhalt("Kennung","AIC_STAMMTYP") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
			Integer iMin = TabSpalten.posInhalt("Kennung","MIN") ? SpinToInt((SpinBox)VecKomponenten.elementAt(TabSpalten.getPos())) : null;
			Integer iMax = TabSpalten.posInhalt("Kennung","MAX") ? SpinToInt((SpinBox)VecKomponenten.elementAt(TabSpalten.getPos())) : null ;
			//Integer iBits = TabSpalten.posInhalt("Kennung","BITS") ? SpinToInt((SpinBox)VecKomponenten.elementAt(TabSpalten.getPos())) : null ;
			int iAbfrage = TabSpalten.posInhalt("Kennung","AIC_ABFRAGE") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
			int iEig2 = TabSpalten.posInhalt("Kennung","EIG_AIC_EIGENSCHAFT") ? ((ComboSort)VecKomponenten.elementAt(TabSpalten.getPos())).getSelectedAIC() : 0 ;
			g.putTabEigenschaft(iAIC,slKennung,slBezeichnung,slDatentyp,(String)VecMemo.elementAt(1),sFormat,iSize,iStamm,iStammtyp,0,iMin,iMax,iAbfrage,iEig2,bNew);
		}*/
	}

	/*private Integer SpinToInt(SpinBox Spn)
	{
		int i= Spn.getIntValue();
		return i==-2 ? null:new Integer(i);
	}*/


    /**
     * BtnAbbruch_actionPerformed Method
     */

    public void BtnAbbruch_actionPerformed(ActionEvent event)
    {
        //bNew=false;
		//bEdit=false;
		//bGid_itemStateChanged = true;
		NodVorher = null;
		Gid_itemStateChanged(null);
    }
    /**
     * BtnBeenden_actionPerformed Method
     */

    public void BtnBeenden_actionPerformed(ActionEvent event)
    {
		Parameter Para=new Parameter(g,"Definition");
		Para.setParameter("Tabelle",sKennung,true,false);
		Para.free();
		g.setFenster(this,g.getBegriffAicS("Button","Definition"),iFF);
		setVisible(false);
    }
    /**
     * EnableButtons Method
     */

    public void EnableButtons()
    {
		if(bEnableButtons)
		{
			boolean bBild_changed=false;
			boolean bMemo_changed=false;
			//tf3.setValue(tf3.intValue()+1);

			JCOutlinerNode NodFocus = Gid.getSelectedNode();
			int iLevel;
			if(NodFocus==null)
				iLevel=-1;
			else
				iLevel=NodFocus.getLevel();

			boolean bRoot = iLevel<1;

			if(!bRoot && (!bVorgaenger || iLevel>1))
			{
				Vector VecSpalte = (Vector) NodFocus.getLabel();
				bBild_changed=BtnImage.Modified();//!Static.Gleich(VecBild.elementAt(0),OldImageFile);
				//if(VecMemo.elementAt(0)!=null && VecMemo.elementAt(1)!=null && OldMemo.elementAt(0)!=null && OldMemo.elementAt(1)!=null)
				bMemo_changed=!VecMemo.elementAt(0).equals(OldMemo.elementAt(0)) || !VecMemo.elementAt(1).equals(OldMemo.elementAt(1)) || !VecMemo.elementAt(2).equals(OldMemo.elementAt(2));
				if (VecKomponenten.elementAt(1) instanceof Text)
				{
					bKennung_changed=!((String)VecSpalte.elementAt(1)).equals(((Text)VecKomponenten.elementAt(1)).getText());					
				}
				else
				{
					g.fixtestError("bKennung_changed nicht ermittelbar, da "+Static.print(VecKomponenten.elementAt(1)));
				}
				bBezeichnung_changed=!((String)VecSpalte.elementAt(0)).equals(((Text)VecKomponenten.elementAt(0)).getText());
				bSonst_changed=false;


				TabSpalten.moveFirst();
				for(int i=0;i<VecKomponenten.size();i++)
				{
					String sKomponentenname = TabSpalten.getS("Komponentenname");
                                        //g.progInfo(TabSpalten.getS("Bezeichnung")+"/"+TabSpalten.getS("Kennung")+":"+sKomponentenname);
					if(sKomponentenname.equals("Text"))
					{
						String s=TabSpalten.getS("Kennung");
						if(! (s.equalsIgnoreCase("Kennung") || s.equalsIgnoreCase("Bezeichung")) )
							bSonst_changed=bSonst_changed || !((String)VecSpalte.elementAt(i)).equals(((Text)VecKomponenten.elementAt(i)).getText());
					}
					else if(sKomponentenname.equals("Schrift"))
						bSonst_changed=bSonst_changed || ((Integer)((Vector)NodFocus.getUserData()).elementAt(i)).intValue()!=((Schrift)VecKomponenten.elementAt(i)).getSelectedAIC();

                                        else if(sKomponentenname.equals("Eigenschaft"))
                                          bSonst_changed=bSonst_changed || ((Integer)((Vector)NodFocus.getUserData()).elementAt(i)).intValue()!=((EigenschaftsEingabe)VecKomponenten.elementAt(i)).getSelectedAIC();
                                        else if(sKomponentenname.equals("Rolle"))
                                          bSonst_changed=bSonst_changed || ((Integer)((Vector)NodFocus.getUserData()).elementAt(i)).intValue()!=((RolleEingabe)VecKomponenten.elementAt(i)).getSelectedAIC();

                                        else if(sKomponentenname.equals("URL"))
                                          bSonst_changed=bSonst_changed || !((String)VecSpalte.elementAt(i)).equals(((WWW)VecKomponenten.elementAt(i)).getValue());

					else if(sKomponentenname.equals("Combobox"))
						bSonst_changed=bSonst_changed || ((Integer)((Vector)NodFocus.getUserData()).elementAt(i)).intValue()!=((ComboSort)VecKomponenten.elementAt(i)).getSelectedAIC();

					else if(sKomponentenname.equals("Spinbox") || sKomponentenname.equals("Negativ"))
						bSonst_changed=bSonst_changed || !(((Integer)((Vector)NodFocus.getUserData()).elementAt(i)).intValue()==(((SpinBox)VecKomponenten.elementAt(i)).getIntValue()));

					else if(sKomponentenname.equals("Checkbox"))
						bSonst_changed=bSonst_changed || ((Boolean)((Vector)NodFocus.getUserData()).elementAt(i)).booleanValue()!=((JCheckBox)VecKomponenten.elementAt(i)).isSelected();

					else if(sKomponentenname.equals("Gesperrt"))
						bSonst_changed=bSonst_changed || ((Date)((Vector)NodFocus.getUserData()).elementAt(i)!=null)!=((JCheckBox)VecKomponenten.elementAt(i)).isSelected();

					else if(sKomponentenname.startsWith("Datum") && !TabSpalten.getS("Kennung").equalsIgnoreCase("Logging"))
					{
						Date dt1=((Date)((Vector)NodFocus.getUserData()).elementAt(i));
						Date dt2=((Datum)VecKomponenten.elementAt(i)).getDateTime();
                                                //g.progInfo(sKomponentenname+":"+dt1+"/"+dt2);
						if (dt1 == null)
							bSonst_changed=bSonst_changed || dt2 != null;
						else
							bSonst_changed=bSonst_changed || !dt1.equals(dt2);
					}
					else if(sKomponentenname.equals("Passwort"))
					{
						String s=(String)((Vector)NodFocus.getUserData()).elementAt(i);
						bSonst_changed=bSonst_changed || !sPasswort.equals(s==null?"":s);
					}

					TabSpalten.moveNext();
				}


			}
			bEdit=bKennung_changed || bBezeichnung_changed || bSonst_changed || bBild_changed || bMemo_changed || EdtTooltip.Modified();
			BtnNeu.setEnabled(!bNew && !bEdit && (!bVorgaenger || iLevel>0));
			BtnNeu2.setEnabled(!bNew && !bEdit && iLevel>0 && (!bVorgaenger || iLevel>1));
			BtnLoeschen.setEnabled(g.Def() && (!bRoot && !bNew && !bEdit && (!bVorgaenger || iLevel>1)));
			BtnImage.setEnabled((!bRoot && (!bVorgaenger || iLevel>1)) || bNew);
			BtnMemo.setEnabled((!bRoot && (!bVorgaenger || iLevel>1)) || bNew);
			BtnFarbe.setEnabled((!bRoot && (!bVorgaenger || iLevel>1)) || bNew);
			BtnOk.setEnabled(g.Def() && (bNew || bEdit));
			BtnAbbruch.setEnabled(bNew || bEdit);
			//BtnEdit.setEnabled(!bRoot && !bNew && !bEdit);
			BtnBeenden.setEnabled(true);

			CboTabellen.setEnabled(!bNew && !bEdit);
			
			if(bNew || bEdit)
				getRootPane().setDefaultButton(BtnOk);
			else
				getRootPane().setDefaultButton(BtnNeu);

			//Gid.setEnabled(!bNew && !bEdit);

			//((JTextField)VecKomponenten.elementAt(0)).setEnabled(!bRoot || bNew);
			/*
			TabSpalten.moveFirst();
			TabSpalten.moveNext();
			for(int i=0;i<VecKomponenten.size();i++)
			{
				if(((String)TabSpalten.getInhalt("Komponentenname")).equals("Text"))
				{
					((JTextField)VecKomponenten.elementAt(i)).setEnabled(bNew || bEdit);
				}

				else if(((String)TabSpalten.getInhalt("Komponentenname")).equals("Combobox"))
				{
					((ComboSort)VecKomponenten.elementAt(i)).setEnabled(bNew || bEdit);
				}

				else if(((String)TabSpalten.getInhalt("Komponentenname")).equals("Spinbox"))
				{
					((SpinBox)VecKomponenten.elementAt(i)).setEnabled(bNew || bEdit);
				}
				else if(((String)TabSpalten.getInhalt("Komponentenname")).equals("Checkbox"))
				{
					((JCheckBox)VecKomponenten.elementAt(i)).setEnabled(bNew || bEdit);
				}

				TabSpalten.moveNext();
			}
			*/
		}
    }//EnableButtons


    /**
     * KomponentenLoeschen Method
     */

    public void KomponentenLoeschen()
    {
		TabSpalten.moveFirst();
		for(int i=0;i<VecKomponenten.size();i++)
		{
			TabSpalten.push();
			if(TabSpalten.getS("Komponentenname").equals("Text"))
			{
				((Text)VecKomponenten.elementAt(i)).setText("");
			}
			else if(TabSpalten.getS("Komponentenname").equals("Schrift"))
			{
				Schrift Cbo = (Schrift)VecKomponenten.elementAt(i);
				Cbo.setSelectedAIC(0);
			}
                        else if(TabSpalten.getS("Komponentenname").equals("Eigenschaft"))
                        {
                                EigenschaftsEingabe Cbo = (EigenschaftsEingabe)VecKomponenten.elementAt(i);
                                Cbo.setSelectedAIC(0);
                        }
                        else if(TabSpalten.getS("Komponentenname").equals("Rolle"))
                        {
                                RolleEingabe Cbo = (RolleEingabe)VecKomponenten.elementAt(i);
                                Cbo.setSelectedAIC(0);
                        }
                        else if(TabSpalten.getS("Komponentenname").equals("URL"))
                        {
                                WWW Cbo = (WWW)VecKomponenten.elementAt(i);
                                Cbo.setValue("");
                        }
			else if(TabSpalten.getS("Komponentenname").equals("Combobox"))
			{
				ComboSort Cbo = (ComboSort)VecKomponenten.elementAt(i);
				//if(Cbo.isKein())
				Cbo.setSelectedAIC(0);
			}
			else if(TabSpalten.getS("Komponentenname").equals("Spinbox"))
			{
                          //g.progInfo("KomponentenLoeschen:Spinbox:"+sKennung);
				if (!sKennung.equals("FARBE") && !sKennung.equals("BEGRIFF") || TabSpalten.getS("Kennung").equals("LOG_AIC_LOGGING"))
					((SpinBox)VecKomponenten.elementAt(i)).setIntValue(-2);
				else
					((SpinBox)VecKomponenten.elementAt(i)).setIntValue(0);
			}
			else if(TabSpalten.getS("Komponentenname").equals("Negativ"))
			{
				((SpinBox)VecKomponenten.elementAt(i)).setIntValue(0);
			}
			else if(TabSpalten.getS("Komponentenname").equals("Checkbox"))
			{
				((JCheckBox)VecKomponenten.elementAt(i)).setSelected(false);
			}
			else if(TabSpalten.getS("Komponentenname").equals("Datum"))
			{
				((Datum)VecKomponenten.elementAt(i)).setDate(null);
			}
			else if(TabSpalten.getS("Komponentenname").equals("Passwort"))
			{
				sPasswort="";
			}
			TabSpalten.pop();
			TabSpalten.moveNext();
		}
		BtnMemo.setText("...");
                EdtTooltip.setText("");
		//VecBild = new Vector();
		//VecBild.addElement("");
		//VecBild.addElement(null);
		VecMemo = new Vector<String>();
		VecMemo.addElement("");
		VecMemo.addElement("");
		VecMemo.addElement("");
		//g.progInfo("Vor:<"+VecBild.elementAt(0)+","+OldImageFile+">");
		BtnImage.setValue(null,"");
		//g.progInfo("Nach:<"+VecBild.elementAt(0)+","+OldImageFile+">");
    }
    /**
     * EdtBezeichnung_keyTyped Method
     */

    public void Edt_keyTyped(KeyEvent event)
    {
		/*
		if(i<2)
		{
			String sBez = ((JTextField)VecKomponenten.elementAt(0)).getText();
			String sKen = ((JTextField)VecKomponenten.elementAt(1)).getText();
			if(sKen.equals(sBezeichnung)||sKen.equals(""))
			{
				if(i==0)
					((JTextField)VecKomponenten.elementAt(1)).setText(sBez);
				else
					((JTextField)VecKomponenten.elementAt(0)).setText(sKen);
			}
			sBezeichnung = sBez;
		}
		*/
        EnableButtons();
    }

	/*public Definition(Global glob)
	{
		this(glob,"BEGRIFF");
	}*/

    public Definition(Global glob,String rsTabelle)
    {
		super("Definition");
		g=glob;
                iFF=g.getFontFaktor();
		BtnImage = new BildEingabe(this,g);
		Gid.setColumnLabelSortMethod(Sort.sortMethod);
		TabSpalten = new Tabellenspeicher(g,new String[] {"Bezeichnung","Kennung","Komponentenname","Frei","Laenge","AIC_Begriffgruppe"});
		thisFrame=this;
		CbxSpaltenname = new JCheckBox(g.getBegriffS("Checkbox","Spaltenname"),true);
		//CboSystem = new ComboSort(g);
		CboTabellen = new ComboSort(g);
                CboTabellen.setPreferredSize(new java.awt.Dimension(150,15));
                //CboTabellen.setFont(g.fontStandard);

		//g.addInfo("### Definition ###");
		BtnNeu = g.getButton("Neu");
		BtnNeu2 = g.getButton("< Neu");
		BtnLoeschen=g.getButton("Loeschen");
		BtnOk=g.getButton("Speichern");
		BtnAbbruch=g.getButton("Ruecksetzen");
		BtnBeenden=g.getButton("Beenden");
		//BtnEdit=g.getButton("Edit");
		LblTabellen =g.getLabel("Tabellen:",SwingConstants.RIGHT);
		//LblSystem =g.getLabel("System:",SwingConstants.RIGHT);
		CbxSave.setText(g.getBegriffS("Show","Speichern"));

		Parameter p = new Parameter(g,"Definition");
		rsTabelle = Static.beiLeer(rsTabelle.equals("")?p.getParameter("Tabelle",true,false):rsTabelle,"SPRACHE");
		p.free();
                g.getFenster(this,g.getBegriffAicS("Button","Definition"),false,100,100,800,600,iFF);
		/*if(!g.getFenster(this,g.getBegriffAic("Button","Definition")))
			setBounds(100,100,800,600);*/
		FillCboTabellen(rsTabelle);
		//CboSystem.fillDefTable("SYSTEM",false);
                JPanel Pnl1 = new JPanel(new GridLayout(0,5));

		Pnl1.add(LblTabellen);
		Pnl1.add(CboTabellen);
		//Pnl1.add(LblSystem);
		//Pnl1.add(CboSystem);
		if (g.Def())
                  Pnl1.add(CbxSpaltenname);
		//Pnl2.add("West",Pnl1);

		Pnl3.add("North",Pnl4);
		Pnl4.add("North",Pnl6);
		//Pnl4.add("South",Pnl7);
		Pnl5.setPreferredSize(new Dimension(300,500));
		Pnl5.setMaximumSize(new Dimension(300,2000));
		JScrollPane PnlE=new JScrollPane(Pnl5); 
        
		Pnl3.add("Center",PnlE);

		Pnl5.add("West",Pnl8);
		Pnl5.add("Center",Pnl9);

		//Pnl6.add(BtnEdit);
		//Pnl7.add(BtnOk);
		//Pnl7.add(BtnAbbruch);

		TxtHilfe = new AUTextArea();
		TxtHilfe.setEditable(false);
                EdtTooltip = new AUTextArea();
                EdtTooltip.Edt.addKeyListener(new KeyListener()
                {
                        public void keyTyped(KeyEvent e)
                        {
                          //g.progInfo("keyTyped");
                        }
                        public void keyPressed(KeyEvent e)
                        {
                          //g.progInfo("keyPressed");
                        }
                        public void keyReleased(KeyEvent e)
                        {
                          //g.progInfo("keyReleased");
                          EnableButtons();
                        }
                });

		//TxtHilfe.setColumns(40);
                JTabbedPane PnlTab = new JTabbedPane();
                PnlTab.add("Tooltip",new JScrollPane(EdtTooltip));
		PnlTab.add("Hilfe",new JScrollPane(TxtHilfe));
                //Pnl3.add("Center",PnlTab);
                JPanel PnlS=new JPanel(new GridLayout());
                PnlS.add(BtnOk);
                PnlS.add(BtnAbbruch);
                PnlS.add(BtnBeenden);
		Pnl3.add("South",PnlS);
		//Pnl3.add("Center",CbxSave);
                JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//,Gid,Pnl3);
                splitPane.setOneTouchExpandable(true);
                splitPane.setResizeWeight(0.65);
                JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
                splitPane2.setOneTouchExpandable(true);
                splitPane2.setResizeWeight(0.9);
                Gid.setMinimumSize(new java.awt.Dimension(100,100));
                Pnl3.setMinimumSize(new java.awt.Dimension(100,100));
                JPanel PnlW=new JPanel(new BorderLayout());
                PnlW.add("North",Pnl1);
                splitPane2.add(Gid);
                splitPane2.add(PnlTab);
                PnlW.add("Center",splitPane2);
                splitPane.add(PnlW);
                splitPane.add(Pnl3);            
		getContentPane().add("Center",splitPane);
		//getContentPane().add("East",Pnl3);

		/*if (g.Prog())
		{
			Pnl77.add(new JLabel("CboTabellen_itemStateChanged:"));
			Pnl77.add(tf1);
			Pnl77.add(new JLabel("Grid_itemStateChaged:"));
			Pnl77.add(tf2);
			Pnl77.add(new JLabel("EnableButtons:"));
			Pnl77.add(tf3);
			getContentPane().add("South",Pnl77);
		}*/

		CboTabellen_itemStateChanged(null);

		setVisible(true);


		//KeyStroke KS = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
		//Keymap map = JComponent.addKeymap("FontStyleMap",this);
/*
		addWindowListener(new WindowListener()
		{
			public void windowClosed(WindowEvent e)
			{
			}
			public void windowOpened(WindowEvent e)
			{
			}
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
			public void windowIconified(WindowEvent e)
			{
			}
			public void windowDeiconified(WindowEvent e)
			{
			}
			public void windowActivated(WindowEvent e)
			{
			}
			public void windowDeactivated(WindowEvent e)
			{
			}
		});*/

		CbxSpaltenname.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CboTabellen_itemStateChanged(null);
			}
		});

		//////////////////////////////////////////////
		//ItemListener für die ComboBox der Tabellen//
		//////////////////////////////////////////////
		CboTabellen.addItemListener(new ItemListener ()
		{
			public void itemStateChanged(ItemEvent ev)
			{
				if(ev.getStateChange() == ItemEvent.SELECTED)
					CboTabellen_itemStateChanged(ev);
			}
		});

		//////////////////////////////
		//ItemListener für das Grid //
		//////////////////////////////
		Gid.addItemListener(new JCItemListener()
		{
			public void itemStateChanged( JCItemEvent ev )
			{
				if(ev.getStateChange() == ItemEvent.SELECTED)
				{
					//lClock=Static.get_ms();
					Gid_itemStateChanged(ev);
					//g.clockInfo("Gid_itemStateChanged",lClock);
				}
			}
		});
		/*
		//////////////////////////////
		//ItemListener für das Grid //
		//////////////////////////////
		Gid.addActionListener(new JCActionListener()
		{
			public void actionPerformed( JCActionEvent ev )
			{
				if(ev.getStateChange() == ev.SELECTED)
					Gid_itemStateChanged(null);
			}
		});
		*/

		////////////////////////////////////
		//ActionListener für Neu-Button//
		////////////////////////////////////
		BtnNeu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnNeu_actionPerformed(ev,true);
			}
		});

		////////////////////////////////////
		//ActionListener für Neu2-Button//
		////////////////////////////////////
		BtnNeu2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnNeu_actionPerformed(ev,false);
			}
		});

		////////////////////////////////////
		//ActionListener für Löschen-Button//
		////////////////////////////////////
		BtnLoeschen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnLoeschen_actionPerformed(ev);
			}
		});

		////////////////////////////////////
		//ActionListener für Ok-Button//
		////////////////////////////////////
		BtnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnOk_actionPerformed(ev);
			}
		});

		////////////////////////////////////
		//ActionListener für Abbruch-Button//
		////////////////////////////////////
		BtnAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnAbbruch_actionPerformed(ev);
			}
		});

		////////////////////////////////////
		//ActionListener für Beenden-Button//
		////////////////////////////////////
		BtnBeenden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnBeenden_actionPerformed(ev);
			}
		});

		BtnImage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				BtnImage.LadeBild();
				EnableButtons();
			}
		});

		BtnMemo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				//int iElement=new Integer(ev.getActionCommand()).intValue();
				//TabSpalten.setPos(iElement);
				//new Memo(VecMemo,VecMemoTitel,0,g.getBegriff("Show","Memo")+": "+CboTabellen.getSelectedItem()+"-"+((Vector)Gid.getSelectedNode().getLabel()).elementAt(0),thisFrame,g);
				//g.debugInfo("BtnMemo.addActionListener");
				//g.debugInfo(g.getBegriff("Show","Memo"));
				//g.debugInfo("Label="+Gid.getSelectedNode().getLabel());
				//g.debugInfo("VecMemo="+VecMemo);
				new Memo(VecMemo,0,g.getBegriffS("Show","Memo")+": "+CboTabellen.getSelectedItem()+(bNew?"":"-"+((Vector)Gid.getSelectedNode().getLabel()).elementAt(0)),thisFrame,g);
				g.fixtestInfo("Memo zurück:"+VecMemo);
				
				if(!VecMemo.elementAt(0).equals(""))
					BtnMemo.setText(VecMemo.elementAt(0));
				else
					BtnMemo.setText("...");
				EnableButtons();
			}
		});

    }

	public void setTabelle(String s)
	{
		CboTabellen.setSelectedKennung(s);
	}

    // add your data members here
	private Global g;
	//private JPanel Pnl2 = new JPanel(new BorderLayout());

	private JPanel Pnl3 = new JPanel(new BorderLayout());
	private	JPanel Pnl4 = new JPanel(new BorderLayout());
	private JPanel Pnl5 = new JPanel(new BorderLayout(2,5));
	private JPanel Pnl6 = new JPanel(new GridLayout(0,2,2,5));
	//private JPanel Pnl7 = new JPanel(new GridLayout(0,2,2,5));
	private JPanel Pnl8 = new JPanel(new GridLayout(0,1,2,5));
	private JPanel Pnl9 = new JPanel(new GridLayout(0,1,2,5));

	private JLabel LblTabellen;
	//private JLabel LblSystem;
	//private ComboSort CboSystem;
	private ComboSort CboTabellen;
	private JCOutlinerFolderNode Nod;
	private AUOutliner Gid= new AUOutliner();
	private Tabellenspeicher TabSpalten;
	private Vector<JComponent> VecKomponenten = new Vector<JComponent>();
	private JButton BtnNeu;
	private JButton BtnNeu2;
	private JButton BtnLoeschen;
	private JButton BtnOk;
	private JButton BtnAbbruch;
	private JButton BtnBeenden;
	//private JButton BtnEdit = new JButton("Edit");
	private JCheckBox CbxSave = new JCheckBox("Speichern",false);

	private boolean bNew = false;
	private boolean bEdit = false;

	private boolean bGid_itemStateChanged = false;
	private int iAIC_Tabellenname;
	private String sKennung;
	//private String sBezeichnung;

	private boolean bKennung_changed;
	private boolean bBezeichnung_changed;
	private boolean bSonst_changed;
	private boolean bEnableButtons=true;
	private boolean bVorgaenger=false;
	//private boolean bRekursiv=false;
	private boolean bNeuDarunter=false;
	private int iAICFremd=-1;
	private int iAICTab = -1;
	private JCOutlinerNode NodVorher = null;

	private Vector<String> VecUntermenge = new Vector<String>();
	private Vector<Serializable> VecAenderung = new Vector<Serializable>();
	private Vector VecAenderungTitel = new Vector();

	private String sRekursion="";

	private boolean bAIC=true;

	//private Zahl tf1 = new Zahl(0);
	//private Zahl tf2 = new Zahl(0);
	//private Zahl tf3 = new Zahl(0);
	//private JPanel Pnl77 = new JPanel();

	//private int iTest = 0;

	//private Image OldImage=null;
	//private String OldImageFile="";
	private Vector OldMemo = new Vector();
	private BildEingabe BtnImage;
	private JButton BtnMemo = new JButton();
	private JButton BtnFarbe = new JButton();
	//private Vector VecBild;
	private Vector<String> VecMemo = new Vector<String>();

	private Farbwahl farbe;
	private SpinBox SpinRed;
	private SpinBox SpinGreen;
	private SpinBox SpinBlue;
	private JFrame thisFrame;

	private AUTextArea TxtHilfe;
        private AUTextArea EdtTooltip;

	private String sPasswort;
	private boolean bGeloescht = false;
	private boolean bPasswort = false;
        private boolean bLogging = false;

	private JCheckBox CbxSpaltenname;
        private int iFF;
	//private long lClock=0;
}

