/*
    All_Unlimited-Print-Doku.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Print;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import bmsg.awt.print.Preview;
import bmsg.awt.print.PrintException;
import bmsg.awt.print.PrintManager;
import bmsg.awt.print.Printable;
import bmsg.awt.print.PrintableImage;
import bmsg.awt.print.PrintableText;



// add your custom import statements here

public class Doku extends All_Unlimited.Allgemein.Formular
{
	private JButton BtnFirst=null;
	private JButton BtnPrev=null;
	private JButton BtnNext=null;
	private JButton BtnLast=null;
	private JButton BtnShow=null;
	private JLabel LblAnz=null;
	private JTextField TxfVon=null;
	private JTextField TxfBis=null;
	
	public Doku(Global rg)
	{
		super("Doku",null,rg);

//		fillTab(false,rg,1,"Code",false,0);
		final Global g=rg;

		final JRadioButton RbnCode=getRadiobutton("Code");
                final JRadioButton RbnRolle=getRadiobutton("Rolle");
		final JRadioButton RbnStammtyp=getRadiobutton("Stammtyp");
		final JRadioButton RbnBewtyp=getRadiobutton("Bewegungstyp");
		final JRadioButton RbnBegriff=getRadiobutton("Begriff");
		final JRadioButton RbnEigenschaft=getRadiobutton("Eigenschaft");
		final JCheckBox CbxBefehle=getCheckbox("Befehle")==null?new JCheckBox():getCheckbox("Befehle");
		final JCheckBox CbxNurLeere=getCheckbox("nurLeere")==null?new JCheckBox():getCheckbox("nurLeere");
		final ComboSort CboBG=new ComboSort(g);
		CboBG.fillDefTable("Begriffgruppe",true);
		JPanel PnlCombobox=getFrei("Combobox");
		if (PnlCombobox != null)
			PnlCombobox.add(CboBG);

		BtnFirst=getButton("<<")==null?new JButton():getButton("<<");
		BtnPrev=getButton("<")==null?new JButton():getButton("<");
		BtnNext=getButton(">")==null?new JButton():getButton(">");
		BtnLast=getButton(">>")==null?new JButton():getButton(">>");
		final JButton BtnEnde=getButton("Beenden")==null?new JButton():getButton("Beenden");
		BtnShow=getButton("show")==null?new JButton():getButton("show");
		final JButton BtnAktSeite=getButton("Seite drucken")==null?new JButton():getButton("Seite drucken");
		JButton BtnDruck=getButton("Druck")==null?new JButton():getButton("Druck");
		JButton BtnDA=getButton("Drucker Auswahl")==null?new JButton():getButton("Drucker Auswahl");

		preview=new Preview();
		JPanel PnlVorschau=getFrei("Vorschau");
		JPanel PnlCombo=getFrei("Combo Zoom")==null?new JPanel():getFrei("Combo Zoom");
		final JPanel PnlLbl=getFrei("Label")==null?new JPanel():getFrei("Label");
		final JPanel PnlVon=getFrei("von")==null?new JPanel():getFrei("von");
		final JPanel PnlBis=getFrei("bis")==null?new JPanel():getFrei("bis");
		//final JPanel PnlLbl2=getFrei("Label2")==null?new JPanel():getFrei("Label2");
		LblAnz=new JLabel("1/"+pm.getNumberOfPages(),SwingConstants.CENTER);
		TxfVon=new JTextField(3);//iStellen);
		TxfBis=new JTextField(3);//iStellen);
		final ComboSort CboZoom=new ComboSort(g);

		CboZoom.setSorted(false);
		CboZoom.addItem("25%",1,"25%");
		CboZoom.addItem("50%",2,"50%");
		CboZoom.addItem("75%",3,"75%");
		CboZoom.addItem("100%",4,"100%");
		CboZoom.addItem("150%",5,"150%");
		CboZoom.addItem("200%",6,"200%");
		CboZoom.addItem("2Page",8,"2 Pages");
		CboZoom.addItem("4Page",9,"4 Pages");
		CboZoom.addItem("PageWidth",10,"Page-Width");
		CboZoom.addItem("PageHeight",11,"Page-Height");

		CboZoom.setSelectedKennung("100%");
		PnlVorschau.add(preview);
		PnlCombo.add(CboZoom);
		PnlLbl.add(LblAnz);
		PnlVon.add(TxfVon);
		PnlBis.add(TxfBis);

		pm.preview(preview);

		anz=pm.getNumberOfPages();

		BtnFirst.setEnabled(false);
		BtnPrev.setEnabled(false);

		TxfVon.setText("1");
		TxfBis.setText(""+anz);

		RbnCode.setSelected(true);
		show();

		CboZoom.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent ie)
				{
					String sSelected=CboZoom.getSelectedKennung();
					if(sSelected.equals("25%"))
						preview.setView(Preview.VIEW_025);
					else if(sSelected.equals("50%"))
						preview.setView(Preview.VIEW_050);
					else if(sSelected.equals("75%"))
						preview.setView(Preview.VIEW_075);
					else if(sSelected.equals("100%"))
						preview.setView(Preview.VIEW_100);
					else if(sSelected.equals("150%"))
						preview.setView(Preview.VIEW_150);
					else if(sSelected.equals("200%"))
						preview.setView(Preview.VIEW_200);
					else if(sSelected.equals("1 Page"))
						preview.setView(Preview.VIEW_1PAGE);
					else if(sSelected.equals("2 Pages"))
						preview.setView(Preview.VIEW_2PAGES);
					else if(sSelected.equals("4 Pages"))
						preview.setView(Preview.VIEW_4PAGES);
					else if(sSelected.equals("Page-Width"))
						preview.setView(Preview.VIEW_WIDTH);
					else if(sSelected.equals("Page-Height"))
						preview.setView(Preview.VIEW_HEIGHT);
				}
			}
			);

		BtnDA.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					pm.selectDevice();
				}
			}
			);

		BtnDruck.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					pm.print(1,new Integer(TxfVon.getText()).intValue(),new Integer(TxfBis.getText()).intValue());
				}
			}
			);

		BtnAktSeite.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					pm.print(1,seite,seite);
				}
			}
		);



		BtnNext.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					preview.showNextPage();
					seite++;
					BtnFirst.setEnabled(true);
					BtnPrev.setEnabled(true);
					if(seite==anz)
					{
						BtnLast.setEnabled(false);
						BtnNext.setEnabled(false);
					}
					LblAnz.setText(seite+"/"+anz);


				}
			}
			);

		BtnLast.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					preview.showLastPage();
					seite=anz;
					BtnFirst.setEnabled(true);
					BtnPrev.setEnabled(true);
					BtnLast.setEnabled(false);
					BtnNext.setEnabled(false);
					LblAnz.setText(seite+"/"+anz);
				}
			}
			);

		BtnPrev.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					preview.showPreviousPage();
					seite--;
					BtnLast.setEnabled(true);
					BtnNext.setEnabled(true);
					if(seite==1)
					{
						BtnFirst.setEnabled(false);
						BtnPrev.setEnabled(false);
					}
					LblAnz.setText(seite+"/"+anz);





				}
			}
			);

		BtnFirst.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					preview.showFirstPage();
					seite=1;
					BtnLast.setEnabled(true);
					BtnNext.setEnabled(true);
					BtnFirst.setEnabled(false);
					BtnPrev.setEnabled(false);
					LblAnz.setText(seite+"/"+anz);


				}
			}
			);

		BtnEnde.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					hide();
				}
			}
			);

		TxfVon.addFocusListener(
			new FocusListener(){
				public void focusGained( java.awt.event.FocusEvent e)
				{}
				public void focusLost( java.awt.event.FocusEvent e)
				{
					pruefe(TxfVon,TxfBis);
				}
			}
			);

		TxfBis.addFocusListener(
			new FocusListener(){
				public void focusGained( java.awt.event.FocusEvent e)
				{}
				public void focusLost( java.awt.event.FocusEvent e)
				{
					pruefe(TxfVon,TxfBis);
				}
			}
			);

		BtnShow.addActionListener(
		  new ActionListener(){
			  public void actionPerformed(ActionEvent ae)
			  {
				BtnShow.setEnabled(false);
				if(RbnCode.isSelected())
					fillTab(CbxBefehle.isSelected(),g,1,"Code",CbxNurLeere.isSelected(),CboBG.getSelectedAIC());//CbxBefehle.isSelected(),g,1,"Code");
                else if(RbnRolle.isSelected())
					fillTab(false,g,6,"Rolle",CbxNurLeere.isSelected(),0);
                else if(RbnStammtyp.isSelected())
					fillTab(false,g,2,"Stammtyp",CbxNurLeere.isSelected(),0);
				else if(RbnBewtyp.isSelected())
					fillTab(false,g,3,"Bewegungstyp",CbxNurLeere.isSelected(),0);
				else if(RbnBegriff.isSelected())
					fillTab(false,g,4,"Begriff",CbxNurLeere.isSelected(),CboBG.getSelectedAIC());
				else if(RbnEigenschaft.isSelected())
					fillTab(false,g,5,"Eigenschaft",CbxNurLeere.isSelected(),0);				
			  }
		  }
		  );
		  preview.setView(Preview.VIEW_100);


	}

	Preview preview=null;
	PrintManager pm=new PrintManager();
	int anz=0,seite=1;


	private void pruefe(JTextField TxfVon,JTextField TxfBis)
	{
		int iVon=0;
		int iBis=0;
		try
		{
			iVon=new Integer(TxfVon.getText()).intValue();
		}catch(Exception exc)
		{
			TxfVon.setText("1");
		}
		try
		{
		    iBis=new Integer(TxfBis.getText()).intValue();
		}catch(Exception exc)
		{
			TxfBis.setText(""+anz);
		}
		if(iBis<1||iBis>anz)
			TxfBis.setText(""+anz);
		else if(iVon<1||iVon>anz)
		    TxfVon.setText("1");
		else if(iVon>iBis)
		{
			TxfVon.setText(""+iBis);
			TxfBis.setText(""+iVon);
	    }
	}
	
	private void fillTab(boolean bBefehle,Global g,int iAuswahl,String sKopf,boolean bNurLeere,int iSub)
	{
		new Thread(new Runnable() {
			public void run() {
				fillTabT(bBefehle,g,iAuswahl,sKopf,bNurLeere,iSub);
				
			}
		}).start();
	}


	private void fillTabT(boolean bBefehle,Global g,int iAuswahl,String sKopf,boolean bNurLeere,int iSub)
	{
		long lClock=Static.get_ms();
		DruckenOld drucken=new DruckenOld(g);
		thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		pm.clear();

		pm.setUpperMargin(20);
		pm.setLowerMargin(10);
		pm.setRightMargin(20);
		pm.setLeftMargin(20);

		//ImageIcon img=new ImageIcon((Image)(Static.LoadImage("Brownie.gif")));
		//pm.addImage(img,1,1,img.getIconWidth()/(img.getIconHeight()/10),10,true);
		int x=0,y=15;
		JLabel l=new JLabel();
		l.setFont(new Font("Serif", Font.BOLD, 12));
		FontMetrics fm= new JFrame().getFontMetrics(l.getFont());
		Tabellenspeicher ts1=null;

		//Code
		if(iAuswahl==1)
			ts1=new Tabellenspeicher(g,"select begriffgruppe.kennung kennung2"+g.AU_Bezeichnung("BegriffGruppe","")+" Ueberschrift"+g.AU_Bild2("Begriffgruppe")+" Gruppenbild"+
			g.AU_Bezeichnung("Code")+",code.kennung befehl,code.aic_code aic,"+
			"(select count(*) from befehl where befehl.aic_code=code.aic_code) Anzahl"+g.AU_Memo("code")+g.AU_Bild2("code")+" Filename "+
			"from begriffgruppe"+g.join2("code","begriffgruppe")+(bBefehle?" where begriffgruppe.Anweisung = 1 or Begriffgruppe.Kennung = 'Bedingung' ":
			(iSub==0?"":" where code.aic_begriffgruppe="+iSub))+" order by begriffgruppe.kennung,befehl",true); //where g.Anweisung = 1 or g.Kennung = "Bedingung"

                //Rolle
                if(iAuswahl==6)
                        ts1=new Tabellenspeicher(g,"select 'Rolle' Ueberschrift,'Rolle' kennung2,kennung befehl,Rolle.aic_Rolle aic"+ g.AU_Bezeichnung("Rolle")+
                        ",(select count(*) from stammview where Rolle.aic_Rolle=stammview.aic_Rolle) Anzahl"+ g.AU_Memo("Rolle")+g.AU_Bild2("Rolle")+" Filename "+
                        "from Rolle order by befehl",true);
                //Stammtyp
		if(iAuswahl==2)
			ts1=new Tabellenspeicher(g,"select 'Stammtyp' Ueberschrift,'Stammtyp' kennung2,kennung befehl,stammtyp.aic_stammtyp aic"+ g.AU_Bezeichnung("Stammtyp")+
			",(select count(*) from stamm where stammtyp.aic_stammtyp=stamm.aic_stammtyp) Anzahl"+ g.AU_Memo("Stammtyp")+g.AU_Bild2("Stammtyp")+" Filename "+
			"from stammtyp order by befehl",true); //where g.Anweisung = 1 or g.Kennung = "Bedingung"

		//Bewegungstyp
		if(iAuswahl==3)
			ts1=new Tabellenspeicher(g,"select 'Bewegungstyp' Ueberschrift,'Bewegungstyp' kennung2,kennung befehl"+ g.AU_Bezeichnung("Bewegungstyp")+
			",(select count(*) from bew_pool where Bewegungstyp.aic_Bewegungstyp=bew_pool.aic_Bewegungstyp) Anzahl,Bewegungstyp.aic_Bewegungstyp aic"+ g.AU_Memo("Bewegungstyp")+g.AU_Bild2("Bewegungstyp")+" Filename "+
			"from Bewegungstyp order by befehl",true); //where g.Anweisung = 1 or g.Kennung = "Bedingung"

		//Begriff
		if(iAuswahl==4)
			ts1=new Tabellenspeicher(g,"select begriffgruppe.kennung kennung2"+ g.AU_Bezeichnung("BegriffGruppe","")+" Ueberschrift"+g.AU_Bild2("Begriffgruppe")+" Gruppenbild"+
			",DefBezeichnung Bezeichnung,begriff.kennung befehl,"+
			"(select count(*) from darstellung where aic_begriff=begriff.aic_begriff) Anzahl,begriff.aic_begriff aic"+g.AU_Memo("Begriff")+g.AU_Bild2("Begriff")+" Filename "+
			"from begriffgruppe"+g.join2("begriff","begriffgruppe")+(iSub==0?"":" where begriff.aic_begriffgruppe="+iSub)+" order by begriffgruppe.kennung,Bezeichnung",true); //where g.Anweisung = 1 or g.Kennung = "Bedingung"

		//Eigenschaft
		if(iAuswahl==5)
			ts1=new Tabellenspeicher(g,"select 'Eigenschaft' Ueberschrift,'Eigenschaft' kennung2,eigenschaft.kennung befehl,eigenschaft.aic_eigenschaft aic"+ g.AU_Bezeichnung("Eigenschaft")+
			g.AU_Memo("Eigenschaft")+g.AU_Bild2("Begriff")+" Filename "+" from eigenschaft"+g.join("begriff","eigenschaft")+" order by befehl",true); //where g.Anweisung = 1 or g.Kennung = "Bedingung"

		add(new JLabel("  Seite \\p/\\s\\|"+sKopf+(bBefehle?" - Befehl":"") +"\\|"+new SimpleDateFormat("yyyy.MM.dd ',' HH:mm:ss").format(new java.util.Date())+"            "),0,2,185,6,1,true);
		drucken.drawLine(0,9,185,0.15,true);
		try
		{
		while(!ts1.eof())
		{
			String sUeber="";
			String sUBild="";
			String Sken=ts1.getS("kennung2");
			if(iAuswahl==1||iAuswahl==4)
			{
				sUeber=ts1.getS("ueberschrift");
				sUBild=iAuswahl!=5?ts1.getS("gruppenbild"):"";
				if(!sUBild.equals(""))
				{
					ImageIcon img=g.LoadImageIcon(sUBild);
					if (img != null)
						addImage(img,x,y,img.getIconWidth()/(img.getIconHeight()/6.1),6.1,false);
				}
				if(sUeber.equals(""))
					sUeber=ts1.getS("kennung2");
				add(new JLabel("\\|"+sUeber+" ("+Sken+")"),x+10,y,180,7.6,1,false);
				y+=14;
			}
			while(!ts1.eof() && ts1.getS("kennung2").equals(Sken))
			{
				String sBild1="Check\\"+(iAuswahl==2?"Stt":iAuswahl==3?"Bew":iAuswahl==6?"Rol":"")+ts1.getS("aic")+".GIF";
				//g.progInfo(Static.ImageVerzeichnis+sBild1);
				ImageIcon img2=g.LoadImageIcon(sBild1);
				//if (true)//img2 == null)
				if(!bNurLeere||img2==null)
				{
					//ImageIcon img2=Static.LoadImageIcon(sBild1);
					//g.progInfo(sBild1+":"+img2);
					x=0;
					String str=ts1.getM("memo");
                                        String sBild=ts1.getS("filename");
					//String sBild=iAuswahl!=5?ts1.getS("filename"):"";
					//int count=str.length()/80;
					//for(int i=0;i<str.length();i++)
					//if(str.charAt(i)=='\n')
						//count++;

					double iHoch=drucken.getMemoHoehe(str,160-x,fm);//(count+1)*6.1;
					if(y+iHoch+8>265)
					{
						try{
							pm.newPage();
						}catch(Exception pe){Static.printError("Doku.fillTab(): Exception - "+pe);}
						checkPages();
						x=0;
						y=15;
						if(iAuswahl==1||iAuswahl==4)
						{
							add(new JLabel("\\|"+sUeber+" ("+Sken+")"),x+10,y,180,7.6,1,false);
							if(!sUBild.equals(""))
							{
								ImageIcon img=g.LoadImageIcon(sUBild);
								if (img != null)
									addImage(img,x,y,img.getIconWidth()/(img.getIconHeight()/6.1),6.1,false);
							}
							y+=14;
						}
					}
					if(iAuswahl==2||iAuswahl==3||iAuswahl==6)
					{
						if (img2 != null)
							addImage(img2,x,y,img2.getIconWidth()/(img2.getIconHeight()/6.1),6.1,false);
						x+=8;
					}
					if(!sBild.equals(""))
					{
						ImageIcon img=g.LoadImageIcon(sBild);
						if (img != null)
							addImage(img,x,y,img.getIconWidth()/(img.getIconHeight()/6.1),6.1,false);
					}
                                        add(new JLabel(ts1.getS("bezeichnung")+" ("+ts1.getS("aic")+": "+ts1.getS("befehl")+")"+ (iAuswahl!=5?": "+ts1.getS("anzahl")+"x":"")),x+8,y,160-x,6.1,1,false);
					//}
					//else
					//	add(new JLabel(ts1.getS("bezeichnung")+" ("+ts1.getS("aic")+": "+ts1.getS("befehl")+")"+ (iAuswahl!=5?": "+ts1.getS("anzahl")+"x":"")),x,y,160-x,6.1,1,false);
						if(!str.equals(""))
						{
							//if(iAuswahl!=2&&iAuswahl!=3&&iAuswahl!=6)
							//	x+=5;
							add(new JLabel(str),x+8,y+7,168-x,iHoch,2,false);
							y+=9+iHoch;
						}
						else
							y+=9;
				}
				ts1.moveNext();
			}

			  try
			  {
				 if(!ts1.eof())
					pm.newPage();
			  }catch(Exception pe){Static.printError("Doku.fillTab(): Exception - "+pe);}
			  x=0;
			  y=15;
		  }
		}catch(Exception pe){
			Static.printError("Doku.fillTab1(): Exception - "+pe);
			g.printStackTrace(pe);
		}
		seite=1;
		checkPages();
		BtnShow.setEnabled(true);
		  thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		  g.clockInfo("fillTab "+sKopf, lClock);
	}
	
	private void checkPages()
	{
		anz=pm.getNumberOfPages();
		BtnLast.setEnabled(false);
		BtnNext.setEnabled(false);
		BtnFirst.setEnabled(false);
		BtnPrev.setEnabled(false);
		TxfVon.setText("1");
		TxfBis.setText(""+anz);
		LblAnz.setText("1/"+pm.getNumberOfPages());//,SwingConstants.CENTER);
		if(pm.getNumberOfPages()>1)
		{
			BtnLast.setEnabled(true);
			BtnNext.setEnabled(true);
		}
	}


	private void addImage(ImageIcon img,double x,double y,double width,double height,boolean repeat)
	{
		addImage(img.getImage(),x,y,width,height,repeat);
	}


	private void addImage(Image img,double x,double y,double width,double height,boolean repeat)
	{
		PrintableImage image = new PrintableImage(img);
		image.setBreakMethod(Printable.B_FIT);
		java.awt.geom.Rectangle2D r = new java.awt.geom.Rectangle2D.Double(x, y, width, height);
		try
		{
			pm.addObject(image, r, repeat);
		}
		catch (PrintException e)
		{
			Static.printError("Doku.addImage(): PrintException - "+e);
		}
	}

	private Object add(JLabel l,double x,double y,double length,double size,int schrift,boolean repeat)
	{
		if(schrift==2)
			l.setFont(new Font("Serif",Font.PLAIN,12));
		else
		{
			l.setFont(new Font("Serif",Font.BOLD,12));
		}
		l.setBackground(Color.white);
		l.setForeground(Color.black);
		PrintableText text = new PrintableText(l);

		java.awt.geom.Rectangle2D r = new java.awt.geom.Rectangle2D.Double(x, y,length,size);//
		Object id=new Object();
		try
		{
			id=pm.addObject(text, r, repeat);
		}catch(PrintException e)
		{
			Static.printError("Doku.add(): PrintException - Fehler beim Hinzufügen des Objektes - "+e);
		}
		return(id);
	}

	/*private void drawLine(double x,double y,double width,double height,boolean repeat)
	{
		Canvas theCanvas=new Canvas();
		theCanvas.setBackground(Color.black);
		theCanvas.setBounds(0,0,1000,3000);
		java.awt.geom.Rectangle2D r  = new java.awt.geom.Rectangle2D.Double(x,y,width,height);
		PrintableComponent pc=new PrintableComponent(theCanvas);	// 31.7.2002 ausgeklammert
		try
		{
			pm.addObject(pc,r, repeat);
		}catch (bmsg.awt.print.PrintException e)
		{
			Static.printError("Drucken.drawLine(): PrintException - "+e);
		}
	}*/

	// add your data members here
}

