/*
    All_Unlimited-Print-Drucken.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Print;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import bmsg.awt.print.PrintException;
import bmsg.awt.print.PrintManager;
import bmsg.awt.print.Printable;
import bmsg.awt.print.PrintableComponent;
import bmsg.awt.print.PrintableImage;
import bmsg.awt.print.PrintableText;

public class DruckenOld //extends java.applet.Applet
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1218162646603798260L;
	

	/*public static boolean is(int i,int iArt)
	{
		return (i & iArt) == iArt;
	}*/

	public DruckenOld(Global rg)
	{
		g=rg;
		vecObjekte=new Vector();
		//System.out.println("anfang...");
		java.awt.print.PrinterJob pj= java.awt.print.PrinterJob.getPrinterJob();
		java.awt.print.PageFormat format = new java.awt.print.PageFormat();
		//java.awt.print.Paper paper = new java.awt.print.Paper();
		format = pj.defaultPage();
		//pjTest.pageDialog(format);
		//paper.setSize(format.getWidth()+10, format.getHeight()+10);
		//paper.setImageableArea(0, 0, format.getWidth(), format.getHeight());
		//format.setPaper(paper);
		try
		{
			pm.setPageFormat(format);
		}catch (bmsg.awt.print.PrintException e)
		{
		   Static.printError("Drucken: PrintException - "+e);
		}
		//g.debugInfo("pHeight: "+paper.getHeight()+"/pWidth: "+paper.getWidth());
		//g.debugInfo("Height: "+pm.getHeight()+"/Width: "+pm.getWidth());
		//g.debugInfo("Height2: "+format.getHeight()+"/Width2: "+format.getWidth());

		try
		{
			if(pm.getPageRotation().equals("HEADLEFT"))
			{

				/*java.awt.print.PageFormat pf=new java.awt.print.PageFormat();
				java.awt.print.Paper pa=new java.awt.print.Paper();
				pa.setSize(297*2.54,210*3.54);
				pf.setPaper(pa);
				pm.setPageFormat(pf);*/
				pm.setPageRotation(PrintManager.R_HEADRIGHT);
				//pr("LeM:"+pm.getLeftMargin()+"/RiM:"+pm.getRightMargin()+"/UpM"+pm.getUpperMargin()+"/LoM:"+pm.getLowerMargin());
				/*pm.setRightMargin(0);
				pm.setLeftMargin(0);
				pm.setLowerMargin(0);
				pm.setUpperMargin(0);*/
				//pr("LeM:"+pm.getLeftMargin()+"/RiM:"+pm.getRightMargin()+"/UpM"+pm.getUpperMargin()+"/LoM:"+pm.getLowerMargin());
			}
			ParaDruck=new Parameter(g,"Druckeinstellungen");
			ParaDruck.getMParameter("Rand",false);
			if(ParaDruck.bGefunden)
			{
				UpperMargin=ParaDruck.int3;
				LowerMargin=ParaDruck.int4;
				RightMargin=ParaDruck.int2;
				LeftMargin=ParaDruck.int1;
				pm.setUpperMargin(ParaDruck.int3);
				pm.setLowerMargin(ParaDruck.int4);
				pm.setRightMargin(ParaDruck.int2);
				pm.setLeftMargin(ParaDruck.int1);
			}
			else
			{
				pm.setUpperMargin(2.0);
				pm.setLowerMargin(10.0);
				pm.setRightMargin(10.0);
				pm.setLeftMargin(10.0);
			}
			//System.out.println("getPageSize...");
			getPageSize();
		}catch(PrintException pe)
		{
			Static.printError("Drucken:"+pe);
		}
		//System.out.println("Konstr. ok");
	}

	public void setRaender()
	{
		pm.setUpperMargin(UpperMargin);
		pm.setLowerMargin(LowerMargin);
		pm.setRightMargin(RightMargin);
		pm.setLeftMargin(LeftMargin);
	}


	public void addImage(ImageIcon img,double x,double y,double width,double height,boolean repeat)
	{
		addImage(img.getImage(),x,y,width,height,repeat);
	}

	public void addImage(Image img,double x,double y,double width,double height,boolean repeat)
	{

		try
		{
			//pr(".");
			PrintableImage image = new PrintableImage(img);
			image.setBreakMethod(Printable.B_FIT);
			//pr("..");
			Rectangle2D r = new Rectangle2D.Double(x, y, width, height);
			//pr("adding Image...");
			try
			{
				pm.addObject(image, r, repeat);
			}
			catch (PrintException pe)
			{
				Static.printError("Drucken.addImage(): PrintException - "+pe);
			}
			//pr("Image added");
		}catch(Exception e)
		{
			Static.printError("Drucken.addImage(): "+e);
		}

	}


	//DefaultListModel objectListModel = new DefaultListModel();
	//JList objectList= new JList(objectListModel);

	/*public Object add(JLabel l,double x,double y,double length,double size,int schrift,boolean repeat)
	{
		TextField txf=new TextField(l.getText());
		Object id=add(txf,x,y,length,size,schrift,repeat);
		return(id);
	}*/

	public Object add(JLabel l,double x,double y,double length,double size,int schrift,boolean repeat)
	{
		if(schrift==1)
		{
			l.setForeground(coText);
			l.setFont(new Font(foText.getFamily(), Font.BOLD, foText.getSize()));
		}
		else if(schrift==2)
		{
			l.setFont(foText);
			l.setForeground(coText);
		}
		else if(schrift==3)
		{
			l.setFont(foTitel);
			l.setForeground(coTitel);
		}
		else if(schrift==4)
		{
			//l.setFont(new Font("Serif",Font.PLAIN,9));
			l.setFont(foDruck);
			l.setForeground(Color.black);
		}
		else
			l.setFont(new Font(foText.getFamily(),Font.BOLD,12));
		l.setBackground(Color.white);
		PrintableText text = new PrintableText(l);

		Rectangle2D r = new Rectangle2D.Double(x, y,length,size);//
		Object id=new Object();
		try
		{
			id=pm.addObject(text, r, repeat);
		}catch(PrintException e)
		{
			Static.printError("Drucken.add(): PrintException - Fehler beim Hinzufügen des Objektes - "+e);
		}
		//System.out.println("add ok");
		return(id);

	}


	public void drawLine(double x,double y,double width,double height,boolean repeat)
	{
		//Canvas theCanvas=new Canvas();
		//theCanvas.setBackground(Color.black);
		//theCanvas.setBounds(0,0,1000,3000);
		//JComponent c=null;
		JPanel button=new JPanel();
		button.setBackground(Color.black);
		button.setBounds(0,0,1000,3000);
		Rectangle2D r  = new Rectangle2D.Double(x,y,width,height);
		PrintableComponent pc=new PrintableComponent(button);	// 31.7.2002 ausgeklammert
		try
		{
			pm.addObject(pc,r, repeat);
		}catch (bmsg.awt.print.PrintException e)
		{
			Static.printError("Drucken.drawLine(): PrintException - "+e);
		}
	}

	/*public void view()
	{
		new Vorschau(g,pm,true);//,vecObjekte);
		//PreviewFrame f=new PreviewFrame();
		//pm.preview(f);
	}*/

	public void printer()
	{
		pm.selectDevice();
	}

	/*public void paper()
	{
		pm.selectPaper();
		if(pm.getPageRotation().equals("HEADLEFT")||pm.getPageRotation().equals("HEADRIGHT"))
			bQuer=true;
		else
			bQuer=false;
	}*/

	public void print(int anz)
	{
		pm.print(anz);
	}

	public void print(int anz,int firstPage,int lastPage)
	{
		g.debugInfo("Width: "+pm.getWidth()+"/Height: "+pm.getHeight());
		pm.print(anz,firstPage,lastPage);
	}

	protected void loadData()
	{
		getPageSize();
		int iX=1;

		//boolean bKBild=false;
		//boolean bFBild=false;
		try
		{
			boolean bKopfText=false,bFussText=false;
			ParaDruck=new Parameter(g,"Druckeinstellungen");
			String sKL=checkString(ParaDruck.getMParameter("Kopfzeile Text",false));
			String sKZ=checkString(ParaDruck.getMParameter("Kopfzeile Mitte",false));
			String sKR=checkString(ParaDruck.getMParameter("Kopfzeile Rechts",false))+"      ";
			String sFL=checkString(ParaDruck.getMParameter("Fusszeile Text",false));
			String sFZ=checkString(ParaDruck.getMParameter("Fusszeile Mitte",false));
			String sFR=checkString(ParaDruck.getMParameter("Fusszeile Rechts",false))+"      ";
			String sKH="";//checkString(ParaDruck.getMParameter("Kopfzeile Text",false));
			String sFH="";//checkString(ParaDruck.getMParameter("Fusszeile Text",false));
			if(!sKL.trim().equals("")||!sKZ.trim().equals("")||!sKR.trim().equals(""))
			{
				bKopfText=true;
				if(!sKL.equals(""))
					sKH+=sKL;
				if(!sKZ.equals(""))
					sKH+="\\|"+sKZ;
				if(!sKR.equals(""))
				{
					if(!sKZ.equals(""))
						sKH+="\\|"+sKR;
					else
						sKH+="\\|\\|"+sKR;

				}

			}
			if(!sFL.trim().equals("")||!sFZ.trim().equals("")||!sFR.trim().equals(""))
			{
				bFussText=true;
				if(!sFL.equals(""))
					sFH+=sFL;
				if(!sFZ.equals(""))
					sFH+="\\|"+sFZ;
				if(!sFR.equals(""))
				{
					if(!sFZ.equals(""))
						sFH+="\\|"+sFR;
					else
						sFH+="\\|\\|"+sFR;

				}

			}
			JLabel LblKopf=new JLabel("");
			JLabel LblFuss=new JLabel("");
			if(!sKH.equals(""))
				LblKopf=new JLabel(sKH);
			if(!sFH.equals(""))
				LblFuss=new JLabel(sFH);


			ParaDruck.getMParameter("Titel Schrift",false);
			if(ParaDruck.bGefunden)
			{
				g.TabSchrift.posInhalt("AIC",ParaDruck.int1);
				g.TabFarbe.posInhalt("AIC",g.TabSchrift.getInhalt("Farbe"));
				foTitel=(Font)g.TabSchrift.getInhalt("Schrift");
				coTitel=(Color)g.TabFarbe.getInhalt("Farbe");
			}
			else
			{
				foTitel=new Font("TimesRoman",Font.BOLD,14);
				coTitel=Color.black;
			}

			ParaDruck.getMParameter("Text Schrift",false);
			if(ParaDruck.bGefunden)
			{
				g.TabSchrift.posInhalt("AIC",ParaDruck.int1);
				g.TabFarbe.posInhalt("AIC",g.TabSchrift.getInhalt("Farbe"));
				foText=(Font)g.TabSchrift.getInhalt("Schrift");
				coText=(Color)g.TabFarbe.getInhalt("Farbe");
			}
			else
			{
				foText=new Font("SansSerif",Font.PLAIN,8);
				coText=Color.black;
			}

			/*ParaDruck.getMParameter("Rand",false);     // steht jetzt beim Konstruktor
			if(ParaDruck.bGefunden)
			{
				UpperMargin=ParaDruck.int3;
				LowerMargin=ParaDruck.int4;
				RightMargin=ParaDruck.int2;
				LeftMargin=ParaDruck.int1;
				pm.setUpperMargin(ParaDruck.int3);
				pm.setLowerMargin(ParaDruck.int4);
				pm.setRightMargin(ParaDruck.int2);
				pm.setLeftMargin(ParaDruck.int1);
			}
			else
			{
				pm.setUpperMargin(2.0);
				pm.setLowerMargin(10.0);
				pm.setRightMargin(10.0);
				pm.setLeftMargin(10.0);
			}
			getPageSize();*/

			if(foText==null)
				foText=new Font("Serif",Font.PLAIN,12);
			if(foTitel==null)
				foTitel=new Font("Serif",Font.PLAIN,12);
			if(coTitel==null)
				coTitel=Color.black;
			if(coText==null)
				coText=Color.black;

			if(foText.getSize()==0)
			foText=new Font(foText.getName(),foText.getStyle(),8);
			if(foTitel.getSize()==0)
			foTitel=new Font(foTitel.getName(),foTitel.getStyle(),14);


			LblKopf.setForeground(Color.black);
			LblFuss.setForeground(Color.black);

			String sKBild=ParaDruck.getMParameter("Kopfzeile Bild",false);
			if(!sKBild.equals(""))
			{
				bKopf=true;
				//bKBild=true;
				ImageIcon img=null;
				try
				{
					img=new ImageIcon(g.LoadImage(sKBild));
				}
				catch(Exception e)
				{
					Static.printError("Drucken.loadData(): Bild fuer Kopfzeile konnte nicht geladen werden ->"+e);
				}
				if(img!=null)
				{
					if(ParaDruck.int1==FlowLayout.RIGHT)
					{
						add(LblKopf,iX,5,dPageWidth-10,6.1,0,true);//180,6.1,0,true);
						addImage(img,(dPageWidth-10)-(img.getIconWidth()/(img.getIconHeight()/10)),1,img.getIconWidth()/(img.getIconHeight()/10),10,true);
					}
					else if(ParaDruck.int1==FlowLayout.CENTER)
					{
						add(LblKopf,iX,5,dPageWidth-10,6.1,0,true);
						addImage(img,((dPageWidth-10)/2)-((img.getIconWidth()/(img.getIconHeight()/10))/2),1,img.getIconWidth()/(img.getIconHeight()/10),10,true);
					}
					else
					{
						addImage(img,iX,1,img.getIconWidth()/(img.getIconHeight()/10),10,true);
						add(LblKopf,iX+img.getIconWidth()/(img.getIconHeight()/10)+2,3,(dPageWidth-10)-(img.getIconWidth()/(img.getIconHeight()/10)),6.1,0,true);
					}
					drawLine(iX,12,dPageWidth-2,0.15,true);//188,0.15,true);
				}
				else
				{
					//bKBild=false;
					add(LblKopf,iX,3,dPageWidth-10,6.1,0,true);
					//System.out.println("*"+LblKopf.getText()+"*");
					drawLine(iX,12,dPageWidth-2,0.15,true);
				}
			}

			else
			{

				//if(!LblKopf.getText().trim().equals(""))
				if(bKopfText)
				{
					bKopf=true;
					add(LblKopf,iX,3,dPageWidth-10,6.1,0,true);
					//System.out.println("*"+LblKopf.getText()+"*");
					drawLine(iX,12,dPageWidth-2,0.15,true);
				}
			}
			String sFBild=ParaDruck.getMParameter("Fusszeile Bild",false);


			if(!sFBild.equals(""))
			{

				bFuss=true;
				//bFBild=true;
				ImageIcon img=null;
				try
				{
					img=new ImageIcon(g.LoadImage(sFBild));
				}
				catch(Exception e)
				{
					Static.printError("Drucken.loadData(): Bild fuer Fusszeile konnte nicht geladen werden ->"+e);
				}
				if(img!=null)
				{
					if(ParaDruck.int1==FlowLayout.LEFT)
					{
						addImage(img,iX,dPageHeight-10,img.getIconWidth()/(img.getIconHeight()/10),10,true);
						add(LblFuss,img.getIconWidth()/(img.getIconHeight()/10)+5,dPageHeight-10,dPageWidth-(img.getIconWidth()/(img.getIconHeight()/10)),6.1,0,true);
					}
					else if(ParaDruck.int1==FlowLayout.CENTER)//((g.TabBegriffe.getS("kennung")).toLowerCase().equals("center"))
					{
						add(LblFuss,iX,dPageHeight-10,dPageWidth-2,6.1,0,true);
						addImage(img,(dPageWidth/2)-((img.getIconWidth()/(img.getIconHeight()/10))/2),dPageHeight-9,img.getIconWidth()/(img.getIconHeight()/10),10,true);
					}
					else// if((g.TabBegriffe.getS("kennung")).toLowerCase().equals("right"))
					{
						add(LblFuss,iX,dPageHeight-10,dPageWidth-2,6.1,0,true);
						addImage(img,iX+dPageWidth-(img.getIconWidth()/(img.getIconHeight()/10)),dPageHeight-9,img.getIconWidth()/(img.getIconHeight()/10),10,true);
					}
					drawLine(iX,dPageHeight-12,dPageWidth-2,0.15,true);
				}
				else
				{
					add(LblFuss,iX,UpperMargin+LowerMargin>=7?dPageHeight-10:dPageHeight-16,dPageWidth-2,6.1,0,true);
					//add(LblFuss,1,275,dPageWidth-2,6.1,0,true);Graphics
					//drawLine(1,dPageHeight-13,dPageWidth-2,0.15,true);
					drawLine(iX,UpperMargin+LowerMargin>=7?dPageHeight-13:dPageHeight-19,dPageWidth-2,0.15,true);
					bFuss=true;
				}

			}
			else
			{
				//pr("<-->dPageWidth:"+dPageWidth+"/dPageHeight:"+dPageHeight);
				//if(!LblFuss.getText().trim().equals(""))
				if(bFussText)
				{
					bFuss=true;
					//add(LblFuss,1,dPageHeight-10,dPageWidth-2,6.1,0,true);
					add(LblFuss,iX,UpperMargin+LowerMargin>=7?dPageHeight-10:dPageHeight-16,dPageWidth-2,6.1,0,true);

					//add(LblFuss,1,275,dPageWidth-2,6.1,0,true);Graphics
					//drawLine(1,dPageHeight-13,dPageWidth-2,0.15,true);
					drawLine(iX,UpperMargin+LowerMargin>=7?dPageHeight-13:dPageHeight-19,dPageWidth-2,0.15,true);
					//bFBild=false;
				}
			}
		}catch(NullPointerException npointer)
		{
			Static.printError("Drucken.loadData(): "+npointer);
			pm.clear();
			bKopf=false;
			bFuss=false;
			foText=new Font("Serif",Font.PLAIN,12);
			foTitel=new Font("Serif",Font.PLAIN,12);
			coTitel=Color.black;
			coText=Color.black;
		}
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		/*java.awt.print.PageFormat pf=new java.awt.print.PageFormat();PrinterJob
			java.awt.print.Paper p=new java.awt.print.Paper();
			p.setSize(296.9,210.1);
			pf.setPaper(p);

			try{pm.setPageFormat(pf);}catch(Exception e){}


			pm.setLeftMargin(92.35);
			pm.setRightMargin(5.08);
			pm.setUpperMargin(25);
			pm.setLowerMargin(25);

		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
  }

	private String checkString(String sZeile)
	{
		String hilfe="";
		int iPosD=sZeile.indexOf("\\d");
		if(iPosD!=-1)
		{
			hilfe=sZeile.substring(0,iPosD);
			hilfe+=new Zeit(new Date(),"dd.MM.yyyy").toString();
			sZeile=hilfe+sZeile.substring(iPosD+2);
		}
		int iPosT=sZeile.indexOf("\\t");
		if(iPosT!=-1)
		{
			hilfe=sZeile.substring(0,iPosT);
			hilfe+=new Zeit(new Date(),"HH:mm").toString();
			sZeile=hilfe+sZeile.substring(iPosT+2);
		}
		return(sZeile);
	}


	protected void setUnterschrift()
	{
		JLabel laU=new JLabel("\\|Ort, Datum");
		add(laU,15,dPageHeight-30,60,15,4,false);
		laU=new JLabel("\\|Firmenstempel, Unterschrift");
		add(laU,90,dPageHeight-30,75,15,4,false);
		drawLine(15,dPageHeight-31,60,0.15,false);
		drawLine(90,dPageHeight-31,75,0.15,false);
	}

	protected void getPageSize()
	{
		if(pm.getPageRotation().equals("HEADRIGHT")||pm.getPageRotation().equals("HEADLEFT"))
		{
			dPageWidth=pm.getWidth()-UpperMargin-LowerMargin;
			dPageHeight=pm.getHeight()-LeftMargin-RightMargin;
		}
		else
		{
			//pr("-->pmWidth:"+pm.getWidth()+"/pmHeight:"+pm.getHeight());
			//pr("-->LeftMargin:"+LeftMargin+"/RightMargin:"+RightMargin+"/UpperMargin:"+UpperMargin+"/LowerMargin:"+LowerMargin);
			dPageWidth=pm.getWidth()-LeftMargin-RightMargin;
			dPageHeight=pm.getHeight()-UpperMargin-LowerMargin;
			//pr("dPageHeight:"+dPageHeight+"/dPageWidth:"+dPageWidth);
		}
	}

	protected double getBreite()
	{
		return pm.getWidth();
	}

	protected double getHoehe()
	{
		return pm.getHeight();
	}


	protected void newPage()
	{
		try
		{
			//IGlobalX=-1;
			//IGlobalY=-1;
			//IGlobalY2=-1;
			pm.newPage();
		}catch(PrintException pe)
		{
			Static.printError("Drucken.newPage(): PrintException - "+pe);
		}
	}

	protected void setUpperMargin(double dRand)
	{
		pm.setUpperMargin(dRand);
	}
	protected void setLowerMargin(double dRand)
	{
		pm.setLowerMargin(dRand);
	}
	protected void setRightMargin(double dRand)
	{
		pm.setRightMargin(dRand);
	}
	protected void setLeftMargin(double dRand)
	{
		pm.setLeftMargin(dRand);
	}


	public void printImage(ImageIcon img)
	{
		addImage(img,0,0,img.getIconWidth(),img.getIconHeight(),true);
		//view();
	}

	protected void pr(String sText)
	{
		g.progInfo(sText);
	}

	/*public void addChart(Printable pa,double x,double y,double width,double height,boolean repeat,boolean bVonDiagramm)
	{
		//Canvas theCanvas=new Canvas();
		//theCanvas.setBackground(Color.black);
		//theCanvas.setBounds(0,0,1000,3000);
		if(bVonDiagramm)
		{
			loadData();
			if(bKopf)
			  y+=20;
			getPageSize();

			double dHilfe=0.0;

			if(width>dPageWidth)
			{
				dHilfe=width;
				width=dPageWidth;
				dHilfe/=dPageWidth;
				height/=dHilfe;
			}
			if(height>dPageHeight)
			{
				dHilfe=height;
				height=dPageHeight;
				dHilfe/=dPageHeight;
				width/=dHilfe;
			}
		}

		Rectangle2D r  = new Rectangle2D.Double(x,y,width,height);
		//PrintableComponent pc=new PrintableComponent(theCanvas);
		try
		{
			pm.addObject(pa,r, repeat);
		}catch (bmsg.awt.print.PrintException e)
		{
			Static.printError("Drucken.drawLine(): PrintException - "+e);
		}
		//if(bVonDiagramm)
		//	view();
	}*/

//	public double getFoHeight(Font foFont,FontMetrics fm)
//	{
//		//double dt=((fm.getHeight())/2.54)-((foFont.getSize()/2.0)/10+0.05);
//		FontMetrics fmHilfe=getFontMetrics(foFont);
//		return((fmHilfe.getHeight())/2.54)-((foFont.getSize()/2.0)/10+0.05);
//	}

	public double getFoWidth(String sText,FontMetrics fm)
	{
		if(sText==null)
			sText="";
		return(fm.stringWidth(sText)*25.4)/72+(12-10)+0.5;
	}

	public double getMemoHoehe(String sText,double dWidth,FontMetrics fm)
	{
		int iCount=0;
		if(sText.endsWith("\n"))
			sText=sText.substring(0,sText.length()-1);
		for(int i=0;i<sText.length();i++)
			if(sText.charAt(i)=='\n')
				iCount++;
		iCount++;
		StringTokenizer sToken=new StringTokenizer(sText,"\n");
		while(sToken.hasMoreTokens())
		{
			double dSWidth=getFoWidth(sToken.nextToken(),fm);
			//pr("dSWidth:"+dSWidth+"/dWidth"+dWidth);
			if(dSWidth>dWidth)
				iCount+=(int)(dSWidth/dWidth);
		}
		return iCount*6;//getFoHeight(foText,fm));
	}

	public int getMemoZeilen(String sText,double dWidth,FontMetrics fm)
	{
		int iCount=0;
		if(sText.endsWith("\n"))
			sText=sText.substring(0,sText.length()-1);
		for(int i=0;i<sText.length();i++)
			if(sText.charAt(i)=='\n')
				iCount++;
		iCount++;
		StringTokenizer sToken=new StringTokenizer(sText,"\n");
		while(sToken.hasMoreTokens())
		{
			double dSWidth=getFoWidth(sToken.nextToken(),fm);
			//pr("dSWidth:"+dSWidth+"/dWidth"+dWidth);
			if(dSWidth>dWidth)
				iCount+=(int)(dSWidth/dWidth);
		}
		return(iCount);
	}

	public PrintManager getPrintManager()
	{
		return(pm);
	}

	protected Font foTitel=new Font("Serif",Font.PLAIN,12);
	protected Font foText=new Font("Serif",Font.PLAIN,12);
	protected Font foDruck=new Font("Serif",Font.BOLD,12);
	protected Color coTitel=Color.black;
	protected Color coText=Color.black;

	protected PrintManager pm=new PrintManager();
	private Parameter ParaDruck;
	protected Global g;
	protected boolean bKopf=false;
	protected boolean bFuss=false;



	protected double dPageWidth=0;
	protected double dPageHeight=0;


	//Wird nur vorübergehend verwendet
	public double LeftMargin=10;
	public double RightMargin=10;
	public double LowerMargin=10;
	public double UpperMargin=2;
	/*JFrame fadd=new JFrame("Add-Console");
	TextArea ta=new TextArea();*/

	protected Vector vecObjekte=null;
	public boolean bQuer=false;

	//DefaultListModel objectListGruppe=null;
	// add your data members here
}

