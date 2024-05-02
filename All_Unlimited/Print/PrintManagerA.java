package All_Unlimited.Print;

import java.awt.BasicStroke;
import java.awt.Color;
//import java.awt.color.ColorSpace;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
//import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
//import javax.swing.JLabel;

import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
//import javax.print.PrintService;
//import java.awt.*;
import javax.swing.ImageIcon;
//import javax.swing.DebugGraphics;
import java.awt.image.BufferedImage;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
//import All_Unlimited.Allgemein.Save;
import All_Unlimited.Allgemein.Tabellenspeicher;
import javax.swing.JEditorPane;
import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.ImageView;

/**
 * PrintManager ermöglicht leichtes Drucken unter Java
 *
*/

public class PrintManagerA implements Printable
{

  //Einheit
//public static int MM		= 0;
//public static int POINT		= 1;

//Ausrichtung
public static int LEFT		= 2;
public static int CENTER	= 3;
public static int RIGHT		= 4;

//Spaltendefinition
public static int BLINE		= 1;       // oben
public static int ULINE		= 2;       // unten
public static int LLINE		= 4;       // links
public static int RLINE		= 8;       // rechts
public static int DLINE		= 16;      // Doppellinie
public static int TLINE		= 32;      // Spaltentitellinie
public static int DH        = 64;      // Doppelte Zeilenhöhe

/**
 * Erzeugt ein PrintManager-Objekt
*/

	public PrintManagerA(String sTitel,int iLayout,long iDruckBits,Global rg)
	{
          g2=rg;
          iFF=/*b100 ? 100:*/g2.getFontFaktor();
          //g2.fixInfo("PrintManagerA mit FF="+iFF);
		VecPages=new Vector<Vector<String>>();
		VecHF=new Vector<String>();
		VecGraphics=new Vector<Graphics2D>();
                int iBits=iLayout==0 ? 0 : SQL.getInteger(g2,"select bits from layout where aic_layout="+iLayout);
                bA3=(iBits&2/*cstLayA3*/)>0;
                bSW=(iBits&8/*cstFarbe*/)==0 && (iDruckBits&0x4000000/*Drucken.cstDFFarbe*/)==0;
                rg.progInfo("PrintManagerA:"+sTitel+"/"+iLayout+"/"+iDruckBits+"/ S/W="+bSW);
                bHtml=(iDruckBits&0x800000/*cstPntHtml*/)>0 || Static.bHtmlDruck;
//                g2.clockInfo("vor getPrinterJob");
                pj=PrinterJob.getPrinterJob();
                /*Toolkit tk = Toolkit.getDefaultToolkit();
                PrintJob pj2 = tk.getPrintJob( new Frame(), "", null );
                if ( pj2 != null )
                {
                  int     res = pj2.getPageResolution();
                  pj2.getGraphics().
                  Dimension d = pj2.getPageDimension();
                  System.out.println( "Resolution : " + res + "\n" +
                                      "Width : " + d.width + "\n" +
                                      "Height : " + d.height + "\n" +
                                      "Pixel on page : " + (res * d.width * d.height) );
                }*/

                //g2.clockInfo("vor setJobName");
                pj.setJobName("All Unlimited - "+sTitel);
                //g2.clockInfo("vor defaultPage");
                pfGlobal=pj.defaultPage();
                bQuer=(iBits&1/*cstLayQuer*/)>0 || (iDruckBits&0x80/*cstPntQuerformat*/)>0;
                bDruckerAuswahl=(iDruckBits&0x100000/*Drucken.cstDruckerauswahl*/)>0;
                //bDruckerAuswahl2=(iDruckBits&0x2000000/*Drucken.cstDruckerauswahl2*/)>0;
                //g2.clockInfo("vor setOrientation:"+bDruckerAuswahl+"/"+bDruckerAuswahl2);//+"/"+bLeerseite);
                pfGlobal.setOrientation(bQuer ? PageFormat.LANDSCAPE:PageFormat.PORTRAIT);
		//PrintService[] services = PrinterJob.lookupPrintServices();
		//for (int i=0;i<services.length;i++)
		//  rg.testInfo("Service "+i+":"+services[i].toString()+"/"+services[i].getName());
                //services[0].createPrintJob();
                //g2.clockInfo("vor setPrintable");
                //if (!Static.bJBuilder && !Static.bDefBezeichnung)
                //  pj.setPrintable(this, pfGlobal);
//                g2.clockInfo("vor graphGlobal=");
                graphGlobal = (Graphics2D)new BufferedImage(1,1,BufferedImage.TYPE_USHORT_GRAY/* TYPE_INT_RGB*/).getGraphics();
//                g2.clockInfo("vor setColor");
                graphGlobal.setColor(Color.white);
	}

	private int iCount=1;

	public int print(Graphics g,PageFormat pf,int pageIndex) throws PrinterException
          {
            iPrint++;
            //g2.progInfo("printA"+iPrint+":"+pageIndex);
            int ret = PAGE_EXISTS;
            try
            {

		Graphics2D g2d=(Graphics2D)g;
		if(bGetPrintGraphics)
		{

			if(gFont!=null)
				g.setFont(gFont);
			if(iCount==1)
			{
				//pj.cancel();
				iCount++;
                                g2.progInfo("PrintmanagerA.print:1");
			}
			else
			{
				graphGlobal=g2d;
				bGetPrintGraphics=false;
				iCount=1;
				//pj.cancel();
				pfGlobal=pf;
                                bPrint=false;
                                g2.progInfo("PrintmanagerA.print:2");
                                //return NO_SUCH_PAGE;
			}
			ret=PAGE_EXISTS;

		}
		else
		{
			if(bVorschau)
			{
                            makePage(g2d,(Vector)VecPages.elementAt(pageIndex),1);
                                Static.printError("PrintmanagerA.print: Vorschau aufgerufen");
				ret=NO_SUCH_PAGE;
			}
			else
			{
			  //g2.progInfo("PrintmanagerA.print: Seite "+pageIndex+"/"+alPages.size());
				if(pageIndex < alPages.size())
				{
					makePage(g2d,(Vector)VecPages.elementAt((alPages.get(pageIndex)).intValue()-1),1);
                                        graphGlobal=g2d;
                                        bPrint=true;
                                        g2.progInfo("PrintmanagerA.print:3");
					ret=PAGE_EXISTS;
				}
				else
                                {
                                  //g2.progInfo("PrintmanagerA.print:4");
                                  if (!bPrint)
                                    pj.cancel();
                                  ret=NO_SUCH_PAGE;
                                }
			}
		}
          }
              catch (Exception e) {
                ret=NO_SUCH_PAGE;
                g2.defInfo2("PrintManagerA.print:"+e);
                //Static.printError("PrintManagerA.print:"+e);
                     throw new PrinterException(e.toString());
              }
              g2.progInfo("printE"+iPrint+":"+pageIndex);
              return ret;
	}

        private JEditorPane getLabel() {
          if (label == null)
          {
            label = new JEditorPane();
            label.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
            label.setContentType("text/html");
            label.setOpaque(false);
            label.setEditable(false);
            label.setEditorKit(new HTMLEditorKit() {
              private static final long serialVersionUID = -4958348301303764495L;
              @Override
              public ViewFactory getViewFactory()
              {
                return new HTMLFactory()
                {

                  @Override
                      public View create(Element elem)
                  {
                    View view = super.create(elem);
                    if (view instanceof ImageView)
                    {
                      ((ImageView)view).setLoadsSynchronously(true);
                    }
                    return view;
                  }
                };
              }
            });
          }
          return label;
        }

	/**
	* Baut eine Seite für Vorschau oder Druck auf
	*/

	public Graphics2D makePage(Graphics2D g,Vector VecPage,float dZoom)
	{
                BasicStroke pen = new BasicStroke((bHGF ? 0.5f:0.2f)*dZoom,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL);//   !!! 0.7f CAP_BUTT JOIN_ROUND
		g.setStroke(pen);

		cbgColor=Color.white;
		for(int i=0;i<VecPage.size();i++)
		{
			String sCommand=(String)VecPage.elementAt(i);
			if(sCommand.startsWith("dl"))
			{
				//System.out.println("dl");
				StringTokenizer str=new StringTokenizer(sCommand);
				str.nextToken();
				g.drawLine(makeInt(str,dZoom),makeInt(str,dZoom),makeInt(str,dZoom),makeInt(str,dZoom));
			}
			else if(sCommand.startsWith("dr"))
			{
				//System.out.println("dr");
				StringTokenizer str=new StringTokenizer(sCommand);
				str.nextToken();
				g.drawRoundRect(makeInt(str,dZoom),makeInt(str,dZoom),makeInt(str,dZoom),makeInt(str,dZoom),(int)(15*dZoom),(int)(15*dZoom));

			}
			else if(sCommand.startsWith("text")||sCommand.startsWith("rtext"))
			{
                          Static.printError("PrintmanagerA.makePage: "+sCommand+"-Commandos entfernt");
                          /*
				//System.out.println("text->"+sCommand);
				StringTokenizer str=new StringTokenizer(sCommand,"/?/!/");
				//System.out.println("count:"+str.countTokens());

				str.nextToken();
				String s=str.nextToken();


				//System.out.println(s+"->breite2:"+g.getFontMetrics().stringWidth(s));
				int iX=(int)(makeInt(str.nextToken())*dZoom);
				//System.out.println(sCommand);
				int iY=(int)(makeInt(str.nextToken())*dZoom);
				int iLine=makeInt(str.nextToken());
				int iWidth=(int)(makeInt(str.nextToken())*dZoom);
				int iHeight=(int)(makeInt(str.nextToken())*dZoom);
				int iDescent=(int)(makeInt(str.nextToken())*dZoom);

				//iY+=g.getFontMetrics().getAscent();
				//System.out.println("giY:"+iY+"/x:"+iX);
				int iXHilfe=iX;					//wird gebraucht da iX verändert werden könnte
				if(sCommand.startsWith("rtext"))
					iX-=g.getFontMetrics().stringWidth(s);
				if(bgColor)
				{
					Color hcolor=g.getColor();
					g.setColor(cbgColor);
					//int width=iWidth;//g.getFontMetrics().stringWidth(s);
					//int height=g.getFontMetrics().getHeight();
					//int descent=g.getFontMetrics().getDescent();
					g.fillRect(iX,iY-(iHeight-iDescent),iWidth,iHeight+(int)dZoom);
					g.setColor(hcolor);
				}
				g.drawString(s,iX+1,iY);
				Color cHilfe=g.getColor();
				g.setColor(Color.black);
				if((iLine&ULINE)!=0)
					g.drawLine(iX,iY-(iHeight-iDescent),iX+iWidth,iY-(iHeight-iDescent));
				if((iLine&BLINE)!=0)
					g.drawLine(iX,iY+iHeight,iX+iWidth,iY+iHeight);
				if((iLine&LLINE)!=0)
					//g.drawLine(iX,iY-(iHeight-iDescent),iX,iY);
					g.drawLine(iX,iY-(iHeight-iDescent),iX,iY+iHeight);
				if((iLine&RLINE)!=0)
					//g.drawLine(iX+iWidth,iY-(iHeight-iDescent),iX+iWidth,iY);
					g.drawLine(iX+iWidth,iY-(iHeight-iDescent),iX+iWidth,iY+iHeight);
				g.setColor(cHilfe);
                                */
			}
			else if(sCommand.startsWith("color"))
			{
				//System.out.println("color");
				StringTokenizer str=new StringTokenizer(sCommand);
				str.nextToken();
				int iRed=makeInt(str,1);
				int iGreen=makeInt(str,1);
				int iBlue=makeInt(str,1);
				g.setColor(new Color(iRed,iGreen,iBlue));
			}
			else if(sCommand.startsWith("font"))
			{
				//System.out.println("font");
				StringTokenizer str=new StringTokenizer(sCommand,"/?/!/");
				str.nextToken();
				String sName=str.nextToken();
				if (sName.equals("3 of 9 Barcode"))
				{
					try
					{
						Font font=Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/TTF/3OF9.ttf"));
						makeInt(str,1);
						int iSize=makeInt(str,dZoom);
						font=font.deriveFont((float)iSize);
//						g2.fixtestError("verwende1 nun Font "+font.getName()+" mit Size="+iSize+"/"+font.getSize()+", "+font.canDisplayUpTo("123"));
						g.setFont(font);
						//g2.fixtestError("verwende2 nun Font "+font.getName()+" mit Size="+iSize+"/"+font.getSize()+", "+font.canDisplayUpTo("123"));
					}
					catch(Exception e)
					{
						Static.printError("cant't create "+sName+"-Font:"+e);
					}				
				}
				else
					g.setFont(new Font(sName,makeInt(str,1),makeInt(str,dZoom)));

			}
			else if(sCommand.startsWith("spalte"))
			{
				//System.out.println("spalte");
				StringTokenizer str=new StringTokenizer(sCommand,"\n");
				//int iCountZeilen=0;
				str.nextToken();
				int iX=makeInt(str,dZoom);
				int iY=makeInt(str,dZoom);
				int iAusrichtung=makeInt(str,1);
				int gWidth=makeInt(str,dZoom);
				int iZeilen=makeInt(str,1);
				int iHeight=makeInt(str,dZoom);
				int iDescent=makeInt(str,dZoom);
				int iLine=makeInt(str,1);
				//iY+=g.getFontMetrics().getAscent();
				int iYPlus=0;
                                int iYPlus2=0;
				int iDLINEPlus=0;
                                if((iLine&(BLINE+DLINE+TLINE))>0)
				{
					iYPlus2=((int)dZoom); //!!!
					if((iLine&DLINE)>0)
						iDLINEPlus=(int)(dZoom);
                                        //iDLINEPlus=iYPlus;
				}
				iY+=iYPlus;
                                int iYTP=0;
                                if((iLine&DH)>0)
                                  iYTP=iHeight*3/5;
				//System.out.println("Spalte.iY:"+iY);
                                int iY1=iY-g.getFontMetrics().getAscent();
				if(bgColor)
				{
					Color hcolor=g.getColor();
					g.setColor(cbgColor);
					//int height=g.getFontMetrics().getHeight();
					//int descent=g.getFontMetrics().getDescent();
					//int ascent=g.getFontMetrics().getAscent();
					//System.out.println("Rect:"+(iY-height));
                                        g.fillRect(iX,iY1+1/*-iYPlus*/,gWidth,iHeight*iZeilen-2/*+iYPlus*//*+iDLINEPlus*/);
                                        /*g.setColor(Color.black);
                                        g.fillRect(iX,iY1-1,gWidth,1);
                                        g.fillRect(iX,iY1+iHeight*iZeilen-1,gWidth,1);*/
					g.setColor(hcolor);
				}
                                int iYu=iY1+(iHeight*iZeilen);
                                if((iLine&RLINE)>0)
                                        g.drawLine(iX+gWidth,iY1-iYPlus,iX+gWidth,iYu+iDLINEPlus+((iLine&TLINE)>0?iYPlus2:0)-((iLine&DLINE)>0?(int)(2*dZoom):0));
                                if((iLine&LLINE)>0)
                                        g.drawLine(iX,iY1-iYPlus,iX,iYu+iDLINEPlus+((iLine&TLINE)>0?iYPlus2:0)-((iLine&DLINE)>0?(int)(2*dZoom):0));
                                if((iLine&BLINE)>0)
                                  g.drawLine(iX,iY1-iYPlus,iX+gWidth,iY1-iYPlus);
                                if((iLine&(ULINE+TLINE))>0)
                                  g.drawLine(iX,iY1+(iHeight*iZeilen)-iYPlus,iX+gWidth,iYu-iYPlus);
                                if((iLine&TLINE)>0)
                                  g.drawLine(iX,iY1+(iHeight*iZeilen)-3*iYPlus/2,iX+gWidth,iYu-3*iYPlus/2);

				//System.out.println("gWidth:"+gWidth+"/iX:"+iX);

				int iXHilfe=iX;
                                //if(iLine == LINE)
				//	gWidth--;
				int gWidthText=gWidth-((iLine&(LLINE+RLINE))>0?1:0);
				while(str.hasMoreTokens())
				{

					boolean bLine=true;
					String s=str.nextToken();
					if(s.endsWith("*noline*"))
					{
						s=s.substring(0,s.length()-8);
						bLine=false;
					}
					//System.out.println("*>"+s);
					//g.drawString(s,iX-g.getFontMetrics().stringWidth(s),iY);
					if(iAusrichtung==CENTER)
						iX=iXHilfe+(gWidthText/2-g.getFontMetrics().stringWidth(s)/2);
					else if(iAusrichtung==RIGHT)
						iX=(iXHilfe+gWidthText)-g.getFontMetrics().stringWidth(s)-1;
                                        //System.out.print(s+":"+iX+"/"+iY+"\n");

                                        //g.drawString(s,iX+1,iY); // ersetzt
                                        if (bHtml)
                                        {
                                          int iH = iY + /*iYPlus2*/((iLine&TLINE)>0 ? 4:1)* iDescent/3 - iHeight+iYTP;
                                          g.translate(iX /*+ 1*/, iH);
                                          //JLabel label = new JLabel();
                                          /*getLabel();
                                          label.setFont(g.getFont());
                                          label.setText(g2.toHtml(s));
                                          //label.setAlignmentY(javax.swing.SwingConstants.BOTTOM);
                                          Dimension d = label.getPreferredSize();*/
                                          Dimension d = getDim(s,g.getFont());
                                          if (!label.getForeground().equals(g.getColor()))
                                          {
                                            g2.fixtestInfo("Color: " + label.getForeground() + "->" + g.getColor());
                                            label.setForeground(g.getColor());
                                          }
                                          label.setBounds(0, 0, gWidth/*d.width+(int)(dZoom*2)*/, d.height);
                                          //label.setBounds(0, 0, gWidth, iHeight * iZeilen);
                                          label.paint(g);
                                          g.translate( -iX /*- 1*/, -iH);
                                        }
                                        else
                                          g.drawString(s,iX+1,iY+iYTP);
					if((iLine&DLINE)!=0&&bLine)
					{
						g.drawLine(iXHilfe,iYu+iDLINEPlus,iXHilfe+gWidth,iYu+iDLINEPlus);
						g.drawLine(iXHilfe,iYu-iDescent+iDLINEPlus,iXHilfe+gWidth,iYu-iDescent+iDLINEPlus);
					}
					//else if((iLine&BLINE)!=0&&bLine)
					//	g.drawLine(iXHilfe,iY+iDescent,iXHilfe+gWidth,iY+iDescent);

					iY+=iHeight;

				}

				//System.out.println("pm.height:"+g.getFontMetrics().getHeight());
			}
			else if(sCommand.startsWith("bgcolor"))
			{

				//System.out.println("bgcolor");
				StringTokenizer str=new StringTokenizer(sCommand);
				str.nextToken();
				int iRed=makeInt(str,1);
				int iGreen=makeInt(str,1);
				int iBlue=makeInt(str,1);
				cbgColor=new Color(iRed,iGreen,iBlue);
				bgColor=true;
				//g.setColor(new Color(iRed,iGreen,iBlue));
			}
			else if(sCommand.startsWith("nobgcolor"))
			{
				bgColor=false;
			}
			else if(sCommand.startsWith("image"))
			{
				StringTokenizer str=new StringTokenizer(sCommand,"\n");
				str.nextToken();
				/*String sPfad=str.nextToken();
                                if (sPfad.startsWith("file:"))
                                  sPfad=sPfad.substring(6);*/
                                int iPos=makeInt(str,1);
                                int iX=makeInt(str,dZoom);
				int iY=makeInt(str,dZoom);
				int iMaxX=makeInt(str,dZoom);
				int iMaxY=makeInt(str,dZoom);
                                int iAusrichtung=makeInt(str,1);
                                //System.err.println("->"+sPfad+"/"+iX+"/"+iY+"/"+iWidth+"/"+iHeight);

				Toolkit t=Toolkit.getDefaultToolkit();
				Image img=VecImage.elementAt(iPos);//t.getImage(sPfad);
				ImageIcon ii=new ImageIcon(img);

                                int iWidth = ii.getIconWidth();
                                int iHeight = ii.getIconHeight();

                                if (iWidth*1.0/iHeight > iMaxX*1.0/iMaxY)
                                  iMaxY=(int)(iMaxX*iHeight*1.0/iWidth);
                                else
                                  iMaxX=(int)(iMaxY*iWidth*1.0/iHeight);
                                /*if (iMaxX < dWidth)
                                {
                                  dHeight = dHeight / dWidth * iMaxX;
                                  dWidth = iMaxX;
                                }

                                if (iMaxY < dHeight)
                                {
                                  dWidth = dWidth / dHeight * iMaxY;
                                  dHeight = iMaxY;
                                }*/
                                //int iWidth=makeInt(str,ii.getIconWidth()/ii.getIconHeight()*dZoom);
                                t.prepareImage(img,iMaxX,iMaxY,ii.getImageObserver());
                                if(iAusrichtung==CENTER)
                                  iX=iX-iMaxX/2;
                                else if(iAusrichtung==RIGHT)
                                  iX=iX-iMaxX;

				g.drawImage(img,iX,iY,iMaxX,iMaxY,ii.getImageObserver());
                                //g.drawImage
			}
		}
                return g;
	}

	/*private void draw(String st,Graphics2D g)
	{

		StringTokenizer str=new StringTokenizer(st,"/?/!/");
		//System.out.println("count:"+str.countTokens());
		str.nextToken();
		String s=str.nextToken();
		int iX=makeInt(str.nextToken());
		//System.out.println(sCommand);
		int iY=makeInt(str.nextToken());
		//System.out.println("giY:"+iY+"/x:"+iX);
		g.drawString(s,iX,iY);

	}*/

	/**
	 * Setzt die Seitenränder
	 */
        public void setMargins(double dLeft,double dRight,double dTop,double dBottom)
	{
          setMargins((int)dLeft,(int)dRight,(int)dTop,(int)dBottom,true);
        }

	private void setMargins(int iLeft,int iRight,int iTop,int iBottom,boolean b)
	{

		aset = new HashPrintRequestAttributeSet();
                aset.add(bQuer ? OrientationRequested.LANDSCAPE:OrientationRequested.PORTRAIT);
                /*if (bDruckerAuswahl2)// || bDruckerFehlt)
                {
                  pj.printDialog();//aset);
                  PS=pj.getPrintService();
                }*/
                //System.out.println(pfGlobal.getOrientation()==PageFormat.LANDSCAPE ? "<1------ LANDSCAPE":"<1--------- PORTRAIT");
		/*if(pfGlobal.getOrientation()==PageFormat.LANDSCAPE)
		{
			//System.out.println("QUERFORMAT!!!!!!!!!");
			int iIWidthMM=(int)(getFullWidth()*25.4/72)-(int)(dLeft)-(int)(dRight);
			int iIHeightMM=(int)(getFullHeight()*25.4/72)-(int)(dTop)-(int)(dBottom);
			aset.add(new MediaPrintableArea((int)dTop,(int)dRight,iIHeightMM,iIWidthMM,MediaPrintableArea.MM));
			//aset.add(new MediaPrintableArea(0,0,(int)(getFullHeight()*25.4/72),(int)(getFullWidth()*25.4/72),MediaPrintableArea.MM));

		}
		else
		{*/
                int iW=(int)(getFullWidth()*25.4/72);
                int iH=(int)(getFullHeight()*25.4/72);
                int iIWidthMM=iW-iLeft-iRight;
                //g2.fixtestInfo("iIWidthMM="+iIWidthMM+":"+iLeft+"/"+iW+"/"+iRight);
                if (iIWidthMM<100)
                {
                  if (iLeft > 50)
                  {
                    Static.printError("Links von "+iLeft+" auf 20 gekürzt");
                    iLeft = 20;
                  }
                  if (iRight > 50)
                  {
                    Static.printError("Rechts von " + iRight + " auf 20 gekürzt");
                    iRight = 20;
                  }
                  iIWidthMM=iW-iLeft-iRight;
                }
                int iIHeightMM=iH-iTop-iBottom;
                if (iIHeightMM<100)
                {
                  if (iTop > 50)
                  {
                    Static.printError("Oben von "+iTop+" auf 20 gekürzt");
                    iTop = 20;
                  }
                  if (iBottom > 50)
                  {
                    Static.printError("Unten von " + iBottom + " auf 20 gekürzt");
                    iBottom = 20;
                  }
                  iIHeightMM=iH-iTop-iBottom;
                }
                aset.add(new MediaPrintableArea(iLeft*10,iTop*10,iIWidthMM*10,iIHeightMM*10,100/*MediaPrintableArea.MM*/));
			//aset.add(new MediaPrintableArea(0,0,(int)(getFullWidth()*25.4/72),(int)(getFullHeight()*25.4/72),MediaPrintableArea.MM));

		//}




		dLeftMargin=((double)iLeft*72)/25.4;
		dRightMargin=((double)iRight*72)/25.4;
		dTopMargin=((double)iTop*72)/25.4;
		dBottomMargin=((double)iBottom*72)/25.4;

		//System.out.println(":)=>W"+getFullWidth());
		//System.out.println(":)=>W"+getFullWidth());
		dImageableWidth=getFullWidth()-dRightMargin-dLeftMargin;
		dImageableHeight=getFullHeight()-dTopMargin-dBottomMargin;
                iFootY=(int)(dTopMargin+dImageableHeight);

	}

	/**
	 * Liefert die Seitenhöhe
	 */

	public double getFullHeight()
	{
          /*System.out.println(pfGlobal.getOrientation()==PageFormat.LANDSCAPE ? "<2------ LANDSCAPE":"<2--------- PORTRAIT");
		if(pfGlobal.getOrientation()==PageFormat.LANDSCAPE&&!bDialog)
			return pfGlobal.getWidth();
		else*/
                //System.err.println("getFullHeight: A3="+bA3+"/"+pfGlobal.getHeight());
			return pfGlobal.getHeight()*(bA3?Math.sqrt(2):1);
	}

	/**
	 * Liefert die Seitenbreite
	 */

	public double getFullWidth()
	{
          /*System.out.println(pfGlobal.getOrientation()==PageFormat.LANDSCAPE ? "<3------ LANDSCAPE":"<3--------- PORTRAIT");
		if(pfGlobal.getOrientation()==PageFormat.LANDSCAPE&&!bDialog)
			return pfGlobal.getHeight();
		else*/
                //System.err.println("getFullWidth: A3="+bA3+"/"+pfGlobal.getWidth());
			return pfGlobal.getWidth()*(bA3?Math.sqrt(2):1);
	}

	/**
	 * Liefert die Breite des beschreibbaren Bereichs der Seiten
	 */

	public double getIWidth(boolean b)
	{
		return b?getFullWidth()/2-dRightMargin:dImageableWidth;
	}

	/**
	 * Liefert die Höhe des beschreibbaren Bereichs der Seiten
	 */

	public double getIHeight()
	{
		return iHeadHeight==0?dImageableHeight:iHeadHeight;
	}

	/**
	 * Liefert den X-Punkt der linken oberen Ecke des beschreibbaren Bereichs
	 */

	public double getIX(boolean b)
	{
		return b?getFullWidth()/2:dLeftMargin;
	}

	/**
	 * Liefert den Y-Punkt der linken oberen Ecke des beschreibbaren Bereichs
	 */

	public double getIY()
	{
		return iHeadY==0?dTopMargin:iHeadY;
	}

	/**
	 * Berechnet die Stringbreite des übergebenen Strings in mm/25.4*72
	 */

	public int stringWidths(String sText)
	{
          //return (int)graphGlobal.getFontMetrics().getStringBounds(sText,graphGlobal).getWidth();
          if (bHtml)
            return getDim(sText,graphGlobal.getFont()).width;
          else
            return graphGlobal.getFontMetrics().stringWidth(sText);
	}

	/**
	 * Berechnet die Stringhöhe
	 */

        public int getZeilen(String sText,int iW)
        {
         if (Static.Leer(sText))
           return 0;
         else if (bHtml)
         {
           getLabel();
           label.setBounds(0, 0,iW,1000);
           int iH=getDim(sText,graphGlobal.getFont()).height;
           int iH2=stringHeight();
           int iZ=iH/iH2;
           g2.fixtestInfo(iZ+" Zeilen bei "+iH+"/"+iH2+","+sText.replaceAll("\n"," "));
           return iZ;
         }
         else
           return 1;
        }

	public int stringHeight()
	{
		return graphGlobal.getFontMetrics().getHeight();
	}

	/**
	 * Setzt den Jobname des aktuellen Druckauftrages
	 */

	public void setJobName(String sName)
	{
		pj.setJobName(sName);
	}

	/**
	 * Zeichnet ein Rechteck auf die aktuelle Seite
	 */

	public void drawRect(int iX,int iY,int iWidth,int iHeight)
	{
		drawRect(iX,iY,iWidth,iHeight,false);
	}

      /**
	 * Zeichnet ein Rechteck auf die aktuelle Seite und auf fortlaufende wenn für bRepeat <i>true</i> übergeben wird
	 */

	public void drawRect(int iX,int iY,int iWidth,int iHeight,boolean bRepeat)
	{
		VecCurPage.addElement("dr " +iX+ " "+iY+ " "+ iWidth+" "+iHeight);
		if(bRepeat)
			VecHF.addElement("dr " +iX+ " "+iY+ " "+ iWidth+" "+iHeight);

	}

	/**
	 * Zeichnet eine Linie auf die aktuelle Seite
	 */

	public void drawLine(int iX1,int iY1,int iX2,int iY2)
	{
		VecCurPage.addElement("dl " +iX1+ " "+iY1+ " "+ iX2+" "+iY2);
		if(bRepeat)
			VecHF.addElement("dl " +iX1+ " "+iY1+ " "+ iX2+" "+iY2);
	}

	private Dimension getDim(String s,Font fo)
        {
          //iMom++;
          //g2.fixInfo("getDim"+iMom+":"+s);
          getLabel();
          label.setFont(fo);
          label.setText(g2.toHtml(s));
          return label.getPreferredSize();
          //return d.width;
        }

        /**
         * Gibt eine Spalte auf die aktuelle Seite
         */

	public int drawSpalte(String sSpalte,int x,int y,int iAusrichtung,int iLine,int iWidth)
	{
		return drawSpalte(sSpalte,x,y,iAusrichtung,iLine,iWidth,false,false);
	}
	
	public int drawSpalte(String sSpalte,int x,int y,int iAusrichtung,int iLine,int iWidth,boolean bSpaltenAbstand,boolean bBarcode)
	{
          /*if (sZeile==null)
            sZeile=sSpalte;
          else if (y==yold)
            sZeile+="\t"+sSpalte;
          else
          {
            Save.prot("Zeilen",new java.io.File(Static.DirError + "Druck.txt"), sZeile);
            sZeile=sSpalte;
          }
          yold=y;*/
          //g.diskInfo("drawSpalte:"+x+":"+y+":"+iWidth+":"+iAusrichtung+":"+iLine+":"+sSpalte);
          //System.err.println("drawSpalte:"+x+"/"+y+":"+sSpalte);
          //System.out.println(sSpalte+":"+stringWidths(sSpalte)+"/"+iWidth+",xy="+x+"/"+y+"->"+iLine);
          if (sSpalte == null || sSpalte.length()==0)
          {
            iCurY=y;
            return 0;
          }
        String sBC=bBarcode ? "":" ";
		int iCountZeilen=0;
		int gWidth=0;
                if (bSpaltenAbstand && sSpalte !=null && sSpalte.length()>0)
                {
                  //sSpalte = iAusrichtung == LEFT ? " " + sSpalte : iAusrichtung == RIGHT ? sSpalte + " " : sSpalte;
                  sSpalte = iAusrichtung == LEFT ? sBC + sSpalte.replaceAll("\n","\n "):
                      iAusrichtung == RIGHT ? sSpalte.replaceAll("\n"," \n") + sBC : sSpalte;
                }
                if(bHtml && bHtmlOk)
                {
                  iCountZeilen =  (iLine&TLINE)>0 ? 2:1;
                  gWidth = iWidth;
                  sSpalte=sSpalte.replaceAll("\n"," ").replaceAll("  "," ");
                }
                else
                {
                  StringTokenizer str = new StringTokenizer(sSpalte, "\n");
                  //if(iWidth==0)
                  //{
                  while (str.hasMoreTokens())
                  {
                    String s = str.nextToken();
                    if (stringWidths(s) > gWidth)
                      gWidth = stringWidths(s);
                    iCountZeilen++;

                  }

                  gWidth++; //weil der Text um 1 weiter eingerückt wird
                  if ((iLine & RLINE) > 0)
                    gWidth++;
                  if ((iLine & LLINE) > 0)
                    gWidth++;

                  //}
                  //else
                  if (iWidth != 0)
                    gWidth = iWidth;
                }

		//iCurX=x+gWidth;
		//System.out.println("dS.height:"+stringHeight());
                int iSH=stringHeight();
                //int iPlus=0;
                if((iLine&DH)>0)
                {
                  iCountZeilen++;
                  //iPlus=iSH/2;
                  //iLine-=DH;
                }
		iCurY=y+(iCountZeilen*iSH);//+iPlus;
		if((iLine&DLINE)>0)
			iCurY+=3;
		else if((iLine&BLINE)>0)
			iCurY+=1;
                boolean bTitel=(iLine&TLINE)>0;
                /*if(bTitel)
                {
                  iCurY += 2;
                }*/
		y+=graphGlobal.getFontMetrics().getAscent();
		int iDescent=graphGlobal.getFontMetrics().getDescent();
//                g2.fixtestError("drawSpalte "+x+"/"+y+":"+sSpalte+"|"+iAusrichtung);
		VecCurPage.addElement("spalte\n"+x+"\n"+(y/*-(bTitel?2:0)*/)+"\n"+iAusrichtung+"\n"+gWidth+"\n"+iCountZeilen+"\n"+(iSH+(bTitel?1:0))+"\n"+iDescent+"\n"+iLine+"\n"+sSpalte);
		if(bRepeat)
			VecHF.addElement("spalte\n"+x+"\n"+y+"\n"+iAusrichtung+"\n"+gWidth+"\n"+iCountZeilen+"\n"+stringHeight()+"\n"+iDescent+"\n"+iLine+"\n"+sSpalte);
                if(bTitel)
                  iCurY += 2;
                //iCurY+=iPlus;
		return gWidth;
	}

        public void drawStreck(String sSpalte,double x,double y,double dStreck,int iAusrichtung,int iWidth)
        {
          //System.out.println("drawStreck:"+x+"/"+y+"/"+dStreck+"/"+iWidth+"/"+iAusrichtung);
          dStreck=dStreck * 72.0 / 25.4;
          x=x*72.0 / 25.4;
          y=y*72.0 / 25.4;
          iCurY=(int)y+stringHeight();
          y+=graphGlobal.getFontMetrics().getAscent();
          if (iAusrichtung==RIGHT)
            x+=iWidth-dStreck*sSpalte.length();
          int iDescent=graphGlobal.getFontMetrics().getDescent();
          for (int i=0;i<sSpalte.length();i++)
          {
            VecCurPage.addElement("spalte\n"+(int)x+"\n"+(int)y+"\n"+LEFT+"\n"+((int)dStreck)+"\n"+1+"\n"+(stringHeight())+"\n"+iDescent+"\n"+0+"\n"+sSpalte.substring(i,i+1));
            //if (iAusrichtung==RIGHT)
            //  VecCurPage.addElement("spalte\n"+(int)x+"\n"+(int)(y+iWidth+dStreck*(i-sSpalte.length()))+"\n"+LEFT+"\n"+((int)dStreck)+"\n"+1+"\n"+(stringHeight())+"\n"+iDescent+"\n"+0+"\n"+sSpalte.substring(i,i+1));
            //else
            //  Static.printError("PrintManagerA.drawStreck: Ausrichtung "+iAusrichtung+" wird noch nicht unterstützt!");
            //if(bRepeat)
            //  VecHF.addElement("spalte\n"+x+"\n"+y+"\n"+LEFT+"\n"+((int)dStreck)+"\n"+1+"\n"+stringHeight()+"\n"+iDescent+"\n"+0+"\n"+sSpalte.substring(i,i+1));
            x+=dStreck;
          }
        }

	/**
	 * Zeichnet ein Bild auf die aktuelle Seite
	 */
        
//        private static BufferedImage createBlackAndWhite(final BufferedImage src) {
//        	final int width = src.getWidth();
//        	final int height = src.getHeight();
//        	final BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        	for (int x = 0; x < width; x++)
//        		for (int y = 0; y < height; y++)
//        			result.setRGB(x, y, black(src.getRGB(x, y)) ? 0x000000 : 0xffffff);
//        	return result;
//        }
//
//        private static boolean black(final int rgb) {
//        	final int red = (rgb >> 16) & 0xff;
//        	final int green = (rgb >> 8) & 0xff;
//        	final int blue = (rgb) & 0xff;
//        	final float[] hsb = Color.RGBtoHSB(red, green, blue, null);
//        	return hsb[2] < 0.5f; // willkürlich: brightness < 50 % = Schwarz
//        }

	public void drawImage(Image img,int iX,int iY,int iWidth,int iHeight,int iAusrichtung)
	{
	  //if (bSW)
	  //  return;
          if (img==null)
            return;
//      g2.fixtestError("drawImage auf "+iY+":"+img);
	  BufferedImage image=null;
	  if (bSW)
	  {
            if (TabImage==null)
              TabImage = new Tabellenspeicher(g2,new String[] {"normal","sw"});
            int iPos=TabImage.getPos("normal",img);
            if (iPos<0)
            {
              try
              {
            	ImageIcon img1=new ImageIcon(img);
            	image = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            	image.getGraphics().drawImage(img1.getImage(), 0,0, img1.getImageObserver());
            	int w = image.getWidth(), h = image.getHeight();
            	BufferedImage bearbeiten = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
            	int iColW=Color.WHITE.getRGB();
            	for (int x = 0; x < w; x++) {
            		for (int y = 0; y < h; y++) {
            			int iPixel=image.getRGB(x, y);
            			int iNeu=iPixel==0 ? iColW:iPixel;//0xFFFFFF - (iPixel&0xFFFFFF) + 0xFF000000;
            			bearbeiten.setRGB(x, y, iNeu);       			       			
            		}
            	}
//                image = new BufferedImage(iWidth2, iHeight2, BufferedImage.TYPE_INT_ARGB);// TYPE_USHORT_GRAY);// TYPE_BYTE_GRAY); // TYPE_BYTE_BINARY);
//                image=createBlackAndWhite(bearbeiten);
            	image=bearbeiten;
              }
              catch (Exception e)
              {
                g2.defInfo2("PrintManagerA.drawImage:" + e);
                return;
              }
//              image.getGraphics().drawImage(img, 0, 0, new ImageIcon(img).getImageObserver());
              iPos=TabImage.newLine();
              TabImage.setInhalt(iPos,"normal",img);
              TabImage.setInhalt(iPos,"sw",image);
              g2.fixInfo((iPos+1)+". Image auf s/w konvertiert");
            }
            else
              image=(BufferedImage)TabImage.getInhalt("sw",iPos);
            //int width = image.getWidth();
            //int height = image.getHeight();
            /*for (int x = 0 ; x < iWidth2 ; ++x)
            {
              for (int y = 0 ; y < iHeight2 ; ++y)
              {
      	      	int rgbValue = image.getRGB(x, y);
      	      	Color color = new Color(rgbValue);
      	      	int red = 255 - color.getRed();
      	      	int green = 255 - color.getGreen();
      	      	int blue = 255 - color.getBlue();
      	      	g2.progInfo(x+"/"+y+":"+red+"/"+green+"/"+blue+"/"+color.getAlpha());
	      	color = new Color (red, green, blue);
      	      	image.setRGB(x,y,color.getRGB());// color.getAlpha()>200 ? Color.white.getRGB():Color.black.getRGB());
              }
            }*/
	  }
          //System.err.println("drawImage:"+iX+"/"+iY+"/"+iWidth+"/"+iHeight+"/"+iAusrichtung);
          int iPos=VecImage.indexOf(bSW ? image:img);
          if (iPos==-1)
          {
            iPos=VecImage.size();
            //img=All_Unlimited.Allgemein.Eingabe.BildEingabe.shrink(img,iWidth,iHeight);
            //ImageIcon Img = new ImageIcon(img);
            //iWidth = Img.getIconWidth();
            //iHeight = Img.getIconHeight();
            //System.err.println("drawImage:"+iPos+"/"+iWidth+"/"+iHeight);
            VecImage.addElement(bSW ? image:img);
          }
		//iCurX=iX+iWidth;
		iCurY=iY+iHeight;
		VecCurPage.addElement("image\n"+iPos+"\n" +iX+ "\n"+iY+ "\n"+ iWidth+"\n"+iHeight+"\n"+iAusrichtung);
		if(bRepeat)
			VecHF.addElement("image\n"+iPos+"\n" +iX+ "\n"+iY+ "\n"+ iWidth+"\n"+iHeight+"\n"+iAusrichtung);
	}

	/*public int mmToPoint(int iMM)
	{
		return (int)(iMM/25.4*72);
	}*/

	/**
	 * Erzeugt eine neue Seite
	 */

	@SuppressWarnings("unchecked")
	public void newP()
	{
		VecCurPage=(Vector)VecHF.clone();
		VecPages.addElement(VecCurPage);
		iCurPage=VecPages.size();
		iCurY=0;
	}

	/**
       * Wechsel auf die nächste Seite
	 */

	@SuppressWarnings("unchecked")
	public void nextPage()
	{
		if(iCurPage<countPages())
		{
			iCurPage++;
			VecCurPage=(Vector)VecPages.elementAt(iCurPage-1);
		}
	}

	/**
	 * Wechsel auf die vorherige Seite
	 */

	@SuppressWarnings("unchecked")
	public void previousPage()
	{
		if(countPages()>1&&iCurPage!=1)
		{
			iCurPage--;
			VecCurPage=(Vector)VecPages.elementAt(iCurPage-1);
		}
	}

	/**
	 * Liefert die Anzahl der Seiten zurück
	 */

	public int countPages()
	{
		return VecPages.size();
	}

	/**
	 * Liefert die Zahl der aktuellen Seite zurück
	 */

	public int curPage()
	{
		return iCurPage;
	}

	/**
	 * Setzt die Farbe
       */

        public boolean Farbe()
        {
          return !bSW;
        }

	public void setColor(Color color)
	{
	  if (bSW)
	    return;
          //System.out.println("setColor:"+color);
          if (color != null)
          {
            VecCurPage.addElement("color " + color.getRed() + " " + color.getGreen() +
                                  " " + color.getBlue());
            if (bRepeat)
              VecHF.addElement("color " + color.getRed() + " " + color.getGreen() +
                               " " + color.getBlue());
          }
	}

	/**
	 * Setzt die Hintergrundfarbe
	 */

	public void setBGColor(Color color)
	{

          if (bSW || color==null && bNobgcolor)
            return;
	    //color=new Color(ColorSpace.getInstance(ColorSpace.TYPE_GRAY),color);
          if (color==null || color==Color.WHITE)
          {
            //noBGColor();
            VecCurPage.addElement("nobgcolor");
                if(bRepeat)
                        VecHF.addElement("nobgcolor");
            bNobgcolor=true;
          }
          else
            {
              bNobgcolor=false;
              bHGF=true;
              VecCurPage.addElement("bgcolor " + color.getRed() + " " + color.getGreen() + " " + color.getBlue());
              if(bRepeat)
                VecHF.addElement("bgcolor " + color.getRed() + " " + color.getGreen() + " " + color.getBlue());
            }
	}

        /**
         * Setzt keine Hintergrundfarbe
         */

	/*private void noBGColor()
	{
		VecCurPage.addElement("nobgcolor");
		if(bRepeat)
			VecHF.addElement("nobgcolor");
	}*/

	/**
	 * Wenn dieser Funktion <i>true</i> übergeben wird, dann werden alle nachfolgenden Befehle auf jeder Seite wiederholt
	 */

	public void setRepeat(boolean brRepeat)
	{
		bRepeat=brRepeat;
	}

	/**
	 * Setzt die Schrift
	 */

	public void setFont(Font fo)
	{
		setFont(fo.getName(),fo.getStyle(),fo.getSize());
	}

	/**
	 * Setzt die Schrift
	 */

	private void setFont(String sName,int iStyle,double dSize)
	{
          //System.out.println("setFont:"+sName+"/"+dSize+"/"+iStyle);
          int iSize=(int)Math.floor((dSize*dFaktor*100+50)/iFF);
          //System.out.println("setFont:"+iSize+"er "+sName+" bei "+dSize+",Faktor="+dFaktor+", FF="+iFF);
          //g2.clockInfo("vor setFont2");
          Font font=null;
//          if (sName.equals("3 of 9 Barcode"))
//			{
//				try
//				{
//					font=Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/TTF/3OF9.ttf"));
//					font=font.deriveFont(iSize);
//					g2.fixtestError("verwende Font "+sName+" mit Size="+iSize);
//				}
//				catch(Exception e)
//				{
//					Static.printError("cant't create "+sName+"-Font:"+e);
//				}				
//			}
//			else
				font=new Font(sName,iStyle,iSize);
          if (graphGlobal != null)
        	  graphGlobal.setFont(font);

		holeGraphics(font);
          //g2.clockInfo("nach holeGraphics");
		VecCurPage.addElement("font/?/!/"+sName+"/?/!/"+iStyle+"/?/!/"+iSize);
		if(bRepeat)
			VecHF.addElement("font/?/!/"+sName+"/?/!/"+iStyle+"/?/!/"+iSize);
	}

	/**
	 * Liefert die Seiten in einem Vector zurück
	 */

	public Vector getPages()
	{
		return VecPages;
	}

	public void startPrint()
	{
		startPrint(1,"1-"+countPages());
	}

	public void startPrint(int iCopies,String sPages)
	{
          //pj.cancel();
          pj=PrinterJob.getPrinterJob();
          pj.setJobName("All Unlimited - abc");
          pfGlobal=pj.defaultPage();
          pfGlobal.setOrientation(bQuer ? PageFormat.LANDSCAPE:PageFormat.PORTRAIT);
          //bPrintReal=true;
          //System.out.println(" ----------------- Druck gestartet -----------------------");
          pj.setPrintable(this, pfGlobal);

		aset = new HashPrintRequestAttributeSet();
                if (bA3)
                {
                  aset.add(MediaSizeName.ISO_A3);
                  aset.add(new MediaPrintableArea(0, 0, 4200, 4200, 100 /*MediaPrintableArea.MM*/));
                }
                else
                {
                  aset.add(MediaSizeName.ISO_A4);
                  aset.add(new MediaPrintableArea(0, 0, 2980, 2980, 100 /*MediaPrintableArea.MM*/));
                }
                //if (bDruckerAuswahl || bDruckerFehlt)
                //{
                  try
                  {
                    if (PS != null)
                      pj.setPrintService(PS);
                  }
                  catch(Exception e)
                  {
                    g2.defInfo2("PrintManagerA.startPrint:"+e);
                  }
                  if (bDruckerAuswahl)
                    pj.printDialog();//aset);
                //}
               //aset.add(OrientationRequested.LANDSCAPE);
                //aset.add(new MediaPrintableArea(0,0,(int)(getFullHeight()*25.4/72),(int)(getFullWidth()*25.4/72),MediaPrintableArea.MM));
		if(writeAlPages(sPages))
		{
                  //System.out.println("********************* "+(pfGlobal.getOrientation()==PageFormat.LANDSCAPE?"Querformat":"Hochformat")+" ****************************");
                  //System.out.println(pfGlobal.getOrientation()+"/"+PageFormat.LANDSCAPE+"--");
                  aset.add(bQuer ? OrientationRequested.LANDSCAPE:OrientationRequested.PORTRAIT);
			/*if(pfGlobal.getOrientation()==PageFormat.LANDSCAPE)
				aset.add(new MediaPrintableArea(0,0,(int)(getFullHeight()*25.4/72),(int)(getFullWidth()*25.4/72),MediaPrintableArea.MM));
			else
				aset.add(new MediaPrintableArea(0,0,(int)(getFullWidth()*25.4/72),(int)(getFullHeight()*25.4/72),MediaPrintableArea.MM));*/

			try
			{
				aset.add(new Copies(iCopies));
				bGetPrintGraphics=false;
                                pj.print(aset);
			}catch(PrinterException pe)
			{
                          StackTraceElement STE[]=pe.getStackTrace();
                          for (int i=0;i<STE.length;i++)
                          {
                            if(STE[i].getClassName().startsWith("All_Unlimited"))
                              System.out.println(i+".:"+STE[i]);
                              //System.out.println(i + ":" + STE[i].getClassName() + "." + STE[i].getFileName() + "." +
                              //  STE[i].getMethodName() + "." +STE[i].getLineNumber());
                          }
                          //pe.printStackTrace();
				Static.printError("PrintmanagerA.startPrint: Fehler beim Drucken des Dokumentes -> "+pe);
			}
		}
                //bPrintReal=false;
                //System.out.println(" ----------------- Druck fertig -----------------------");
	}

	public boolean writeAlPages(String sPages)
	{
		alPages=new ArrayList<Integer>();
		StringTokenizer str=new StringTokenizer(sPages,";");
		//System.out.println("func writeAlPages("+sPages+")...");
		while(str.hasMoreTokens())
		{
			String sToken=str.nextToken();

			int iIndex=sToken.indexOf("-");
			if(iIndex!=-1)
			{
				StringTokenizer sDigits=new StringTokenizer(sToken,"-");
				String sDigit1=(sDigits.nextToken()).trim();
				String sDigit2=(sDigits.nextToken()).trim();
				if(!isString_Digit(sDigit1)||!isString_Digit(sDigit2))
				{
					System.err.println("Error: no Digit ->"+sDigit1+" or "+sDigit2);
					return false;
				}
				for(int i=new Integer(sDigit1).intValue();i<=new Integer(sDigit2).intValue();i++)
					alPages.add(new Integer(i));
			}
			else
			{
				sToken=sToken.trim();
			      if(isString_Digit(sToken))
					alPages.add(new Integer(sToken));
				else
				{
					System.err.println("Error: no Digit ->"+sToken);
					return false;
				}
			}

		}
		//for(int i=0;i<alPages.size();i++)
		//	System.out.println(">"+alPages.get(i));
		return true;

	}

	/**
	 * Prüft ob ein String eine Zahl enthält
	 */

	private boolean isString_Digit(String sText)
	{

		if(sText.equals(""))
			return false;
		else
		{
			char [] sChars=new char[sText.length()];
			sText.getChars(0,sText.length(),sChars,0);
			for(int i=0;i<sChars.length;i++)
				if(!Character.isDigit(sChars[i]))
					return false;
		}
		return true;
	}

	private int makeInt(StringTokenizer str,double d)
	{
          String sText=str.nextToken();
          if (sText.equals("-"))
            return 0;
          else
            return (int)(new Integer(sText).intValue()*d);
	}

	/**
	 * Zeichnet einen Rand um den druckbaren Bereich
	 */

	public void makeFrame(boolean bDoppel,boolean bRepeat)
	{
		int x1=(int)dLeftMargin-1;
		int x2=(int)dImageableWidth+2;
		int y1=(int)dTopMargin;
		int y2=(int)dImageableHeight;
		drawRect(x1,y1,x2,y2,bRepeat);
		if(bDoppel)
		{
			x1-=2;
			y1-=2;
			x2+=4;
			y2+=4;
			drawRect(x1,y1,x2,y2,bRepeat);
		}

	}


      private int [] getMemoSize(String sMemo)
	{
		int [] iSize= new int[2];
		StringTokenizer str=new StringTokenizer(sMemo,bHtmlOk?"<br>":"\n");
		int iWidth=0;
		int iCount=0;
		while(str.hasMoreTokens())
		{
			String s=str.nextToken();
			if(graphGlobal.getFontMetrics().stringWidth(s)>iWidth)
				iWidth=graphGlobal.getFontMetrics().stringWidth(s);
			iCount++;
		}
		iSize[0]=iWidth;
		iSize[1]=iCount;
		return iSize;
	}


	public int getRowWidth(Vector [] vecZeilen,boolean bLines)
	{
          //System.err.println("getRowWidth");
		Font foHilfe=gFont;
		int gWidth=0;
		//System.out.println("VecSize:"+vecZeilen.length);
		for(int i=0;i<vecZeilen.length;i++)
		{
			String s=(String)vecZeilen[i].elementAt(0);
			Font f=(Font)vecZeilen[i].elementAt(1);
            if (f!=null)
            {
              if (iFF!=100)
    			f=new Font(f.getFontName(),f.getStyle(),(f.getSize()*100+50)/iFF);

              holeGraphics(f);
              int iCR=s.indexOf('\n');
              String s1=iCR<0 ? s:iCR==0 ? "":s.substring(0,iCR);
              if(stringWidths(s1)>gWidth)
              {
                gWidth = stringWidths(s1);
                //System.out.println("Breite1: "+gWidth+" mit <"+s1+">");
              }
              if (iCR>=0)
              {
                s1=s.substring(iCR+1);
                if (stringWidths(s1) > gWidth)
                {
                  gWidth = stringWidths(s1);
                  //System.out.println("Breite2: " + gWidth + " mit <" + s1 + ">");
                }
              }
			}
		}
                if(bLines)
                  gWidth+=gWidth==0?0:2;
		holeGraphics(foHilfe);
		return gWidth;
	}


        /**
         * Zeigt alle Seiten-Befehle an
         */

	/*public void showPageCommands()                     //zeigt alle Ereignisse der aktuellen Seite
	{
		System.out.println("============Page Commands============");
		for(int i=0;i<VecCurPage.size();i++)
			System.out.println("<--\n"+VecCurPage.elementAt(i)+"\n-->");
		System.out.println("==========Page Commands End==========");
	}*/

	private void holeGraphics(Font fo)               //sieht VecGraphics durch ob schon ein Graphics-obj mit font vorhanden ist.
									//wenn nicht wird es vom Drucker geholt und in VecGraphics gespeichert.
	{

		boolean bAvailable=false;
		for(int i=0;i<VecGraphics.size();i++)
		{
                  //if (bPrintReal)
                    //System.out.println(iPrint+":Font"+i+"="+((Graphics2D)VecGraphics.elementAt(i)).getFont());
			if((VecGraphics.elementAt(i)).getFont().equals(fo))
			{
				graphGlobal=VecGraphics.elementAt(i);
				//System.out.println("gefunden");
				bAvailable=true;
				break;
			}
		}
                //g2.clockInfo("holeGraphics2");
		if(!bAvailable)
		{
                  //System.out.println("holeGraphics: "+fo+" nicht verfügbar ");
			if (graphGlobal != null)
                          graphGlobal.setFont(fo);
			try
			{
				gFont=fo;//new Font(sName,iStyle,iSize);
				bGetPrintGraphics=true;
                                /*if (false) //!bLeerseite)
                                {
                                  g2.clockInfo("-------------------------------------- vor pj.print(aset)");
                                  //if (bPrintReal)
                                  pj.print(aset); //!!!
                                  //pj.print();
                                  g2.clockInfo("nach pj.print(aset)");
                                }*/
			}catch(Exception pe)
                        {
                          g2.defInfo2("PrintManagerA.holeGraphics:"+pe);
                          //g2.clockInfo("holeGraphics-Exception");
                          //System.err.println("PrintManagerA.holeGraphics:"+pe);
                        }
                        //bPrintReal=false;
			//Graphics2D gH=(Graphics2D)graphGlobal.clone()
			//System.out.println("HG.Schriftart:"+fo.getName()+"/Size:"+fo.getSize());
			VecGraphics.addElement(graphGlobal);
                        //int i=VecGraphics.size()-1;
                        //System.out.println("add Font"+i+"="+((Graphics2D)VecGraphics.elementAt(i)).getFont()+"/"+fo);
		}
                //else
                //  g2.clockInfo("holeGraphics:Font "+fo+" gefunden");
                //bPrintReal=false;
	}

        /**
         * Setzt die Kopfzeile
         */

	public void setHeadrow(String sLeft,String sCenter,String sRight,Image Img,int iAlignment,Font fo,int iHoehe,boolean bLinie)
	{
//          g2.clockInfo("setHeadrow start");
          bKL=bLinie;
          iKH=iHoehe * 720 / 254;
		int iY=iCurY;
                //g2.clockInfo("vor setFont");
                setFont(fo);
                //g2.clockInfo("nach setFont");
		foHead=fo;
//		sImgHead=sImgPath.trim();
		ImgHead=Img;
		iImgHeadAlignment=iAlignment;
		int [] iMemoSize=getMemoSize(sLeft);
		iY=iMemoSize[1]*stringHeight();
		iMemoSize=getMemoSize(sCenter);
		if(iMemoSize[1]*stringHeight()>iY)
			iY=iMemoSize[1]*stringHeight();
		iMemoSize=getMemoSize(sRight);
		if(iMemoSize[1]*stringHeight()>iY)
			iY=iMemoSize[1]*stringHeight();
		if(Img!=null && iKH>iY)
			iY=iKH;
		iY=(int)getIY()+iY;
		sHeadLeft=sLeft;
		sHeadCenter=sCenter;
		sHeadRight=sRight;
		bHeadrow=true;
		//setRepeat(true);
		//setFont(fo);
		//noBGColor();
		//drawSpalte(sLeft,(int)getIX(),(int)getIY(),LEFT,0);
		//iY=iCurY;
		//drawSpalte(sCenter,(int)getIX(),(int)getIY(),CENTER,0,(int)getIWidth());
		//if(iCurY>iY)
		//	iY=iCurY;
		//drawSpalte(sRight,(int)getIX(),(int)getIY(),RIGHT,0,(int)getIWidth());
		//if(iCurY>iY)
		//	iY=iCurY;
		//drawLine((int)getIX(),iY+2,(int)getIX()+(int)getIWidth(),iY+2);
		iHeadY=iY+3;
		iHeadHeight=(int)(getFullHeight()-iHeadY-dBottomMargin);
		//dTopMargin=iY+3;
		//dImageableHeight=getFullHeight()-dTopMargin-dBottomMargin;

		//iCurY=0;
		//setRepeat(false);
	}

        /**
         * Setzt die Fußzeile
         */

	public void setFootrow(String sLeft,String sCenter,String sRight,Image Img,int iAlignment,Font fo,int iHoehe,boolean bLinie)
	{
          bFL=bLinie;
          iFH=iHoehe * 720 / 254;
		int iY=0;
                setFont(fo);
		foFoot=fo;
//		sImgFoot=sImgPath.trim();
		ImgFoot=Img;
		iImgFootAlignment=iAlignment;
		int [] iMemoSize=getMemoSize(sLeft);
		iY=iMemoSize[1]*stringHeight();
		iMemoSize=getMemoSize(sCenter);
		if(iMemoSize[1]*stringHeight()>iY)
			iY=iMemoSize[1]*stringHeight();
		iMemoSize=getMemoSize(sRight);
		if(iMemoSize[1]*stringHeight()>iY)
			iY=iMemoSize[1]*stringHeight();
		if(Img!=null && iFH>iY)
			iY=iFH;
		//iY=(int)getIY()+iY;
		iY+=3;
		sFootLeft=sLeft;
		sFootCenter=sCenter;
		sFootRight=sRight;
		bFootrow=true;
		iFootY=(int)(dTopMargin+dImageableHeight-iY);

		//iHeadY=iY+3;
		//iHeadHeight=(int)(getFullHeight()-iHeadY-dBottomMargin);
		if(bHeadrow)
			iHeadHeight-=iY;
		else
			iHeadHeight=(int)dImageableHeight-iY;

	}

        /**
         * Gibt Kopf- und Fußzeile aus
         */
	
	private boolean tryURL(URL url)
	{
		boolean b=false;
		URLConnection con = null;
	    InputStream in = null;
	    try {
	        String webadd="urls go here try the two you have had probelms with and success";
	        //URL url = new URL(webadd);

	        con = url.openConnection();
	        con.setConnectTimeout(5000);
	        con.setReadTimeout(5000);
	        in = con.getInputStream();
	        Image img = ImageIO.read(in);
	        if (img != null) {
	        	//g2.fixInfo("Loaded: "+url);
	        	b=true;
	        } else {
	        	g2.fixInfo("Could not load "+url);

	        }
	    } catch (IOException ex) {
	    	Static.printError(ex+" Bild nicht geladen:"+url);
//	        ex.printStackTrace();
	    } finally {
//	        if(is != null) {
//	            try {
//	                 is.close();
//	            } catch(IOException ex) {
//	                 // handle close failure
//	            }
//	        }

//	        if(con != null) {
//	            con.disconnect();
//	        }
	    }
	    return b;
	}

	private void makeHeadFoot()
	{
//		g2.fixInfo("makeHeadFoot: Headrow");
		if(bHeadrow)
		{
			int iY=(int)dTopMargin;
			//foHead=fo;
			int iX=(int)dLeftMargin;
			int iWidth=(int)dImageableWidth;
			setFont(foHead);
                        setColor(Color.black);
			setBGColor(null);
                        //System.out.println("sImgHead="+sImgHead);
			if(ImgHead!=null)//!sImgHead.equals("") && Static.toURL(sImgHead)!=null)
			{
				int iXImage=0;
				if(iImgHeadAlignment==RIGHT)
					iXImage=iX+iWidth;
                                else if (iImgHeadAlignment==CENTER)
                                  iXImage=iWidth/2;
				else
				{
					iXImage=iX;
					iX+=iKH+3;
				}
//				URL url=Static.toURL(sImgHead);			
////				g2.fixInfo("drawImage URL="+url);
//				boolean b=tryURL(url);
////				g2.fixInfo("tryURL="+b);
//				if (b)
//				{
//					Image img=new ImageIcon(url).getImage();//Toolkit.getDefaultToolkit().getImage(url);
	                drawImage(ImgHead,iXImage,(int)dTopMargin,iKH*20,iKH,iImgHeadAlignment);
					iY+=iKH;
					iWidth-=iKH+3;
//				}
			}
			String sLeft=checkParameters(sHeadLeft);
			drawSpalte(sLeft,iX,(int)dTopMargin,LEFT,0,0);
			if(iCurY>iY)
				iY=iCurY;
			String sCenter=checkParameters(sHeadCenter);
			drawSpalte(sCenter,iX,(int)dTopMargin,CENTER,0,iWidth);
			if(iCurY>iY)
				iY=iCurY;
			String sRight=checkParameters(sHeadRight);
			drawSpalte(sRight,iX,(int)dTopMargin,RIGHT,0,iWidth);
			if(iCurY>iY)
				iY=iCurY;
                        if (bKL)
                          drawLine((int)dLeftMargin,iY+2,(int)dLeftMargin+(int)dImageableWidth,iY+2);
			//iHeadY=iY+3;
			//iHeadHeight=(int)(getFullHeight()-iHeadY-dBottomMargin);
			//dTopMargin=iY+3;
			//dImageableHeight=getFullHeight()-dTopMargin-dBottomMargin;

			//iCurY=0;
		}
//		g2.fixInfo("makeHeadFoot: Footrow");
		if(bFootrow)
		{
			int iY=iFootY;
			//foHead=fo;
                        if (bFL)
                          drawLine((int)dLeftMargin,iY,(int)dLeftMargin+(int)dImageableWidth,iY);
			iY+=2;
			setFont(foFoot);
                        setColor(Color.black);
                        setBGColor(null);
			//noBGColor();
			int iX=(int)dLeftMargin;
			int iWidth=(int)dImageableWidth;
			if(ImgFoot!=null)//!sImgFoot.equals("") && Static.toURL(sImgFoot)!=null)
			{
				int iXImage=0;
				if(iImgFootAlignment==RIGHT)
					iXImage=iX+iWidth;
                                else if (iImgFootAlignment==CENTER)
                                  iXImage=iWidth/2;
				else
				{
					iXImage=iX;
					iX+=iFH+3;
				}
//				URL url=Static.toURL(sImgFoot);	
////				g2.fixInfo("drawImage:"+sImgHead);
//				boolean b=tryURL(url);
//				if (b)
//				{
					drawImage(ImgFoot,iXImage,iY,iFH*20,iFH,iImgFootAlignment);
					iWidth-=iFH+3;
//				}
			}
			String sLeft=checkParameters(sFootLeft);
			drawSpalte(sLeft,(int)dLeftMargin,iY,LEFT,0,0);
			//if(iCurY>iY)
			//	iY=iCurY;
			String sCenter=checkParameters(sFootCenter);
			drawSpalte(sCenter,(int)dLeftMargin,iY,CENTER,0,iWidth);
			//if(iCurY>iY)
			//	iY=iCurY;
			String sRight=checkParameters(sFootRight);
			drawSpalte(sRight,(int)dLeftMargin,iY,RIGHT,0,iWidth);
			//if(iCurY>iY)
			//	iY=iCurY;

			//iHeadY=iY+3;
			//iHeadHeight=(int)(getFullHeight()-iHeadY-dBottomMargin);
			//dTopMargin=iY+3;
			//dImageableHeight=getFullHeight()-dTopMargin-dBottomMargin;

			//iCurY=0;

		}
//		g2.fixInfo("makeHeadFoot: fertig");
	}

	private String checkParameters(String sText)
	{
		sText=Static.replaceString(sText,"\\p",""+iCurPage);
		sText=Static.replaceString(sText,"\\s",""+VecPages.size());
		return sText;
	}

        /**
         * druckt Kopf- oder Fußzeile
         */

	@SuppressWarnings("unchecked")
	public void printEnd()
	{
		if(!bEnd && (bHeadrow||bFootrow))
		{
                  bEnd=true;
                  dFaktor=1;
			for(int i=0;i<VecPages.size();i++)
			{
				iCurPage=i+1;
				VecCurPage=(Vector)VecPages.elementAt(i);
				makeHeadFoot();
			}
			//new TestScrollen(this).show();
		}
	}

        public boolean Quer()
        {
          return bQuer;
        }

        public boolean A3()
        {
          return bA3;
        }
        
        public void addPDF(String sFile)
        {
        	if (VecPDF==null)
        		VecPDF=new Vector<String>();
//        	g2.fixtestError("PM.addPDF: "+sFile);
        	VecPDF.addElement(sFile);
        }
        
        public int pdfCount()
        {
        	return VecPDF==null ? 0:VecPDF.size();
        }
        
        public Vector<String> getPDF()
        {
        	return VecPDF;
        }
        
        public void printPDFs()
        {
        	if (VecPDF != null)
            	for (int i=0;i<VecPDF.size();i++)
            		Static.PrintFile(g2,Sort.gets(VecPDF, i));
        }
        
        public void openPDFs()
        {
        	if (VecPDF != null)
            	for (int i=0;i<VecPDF.size();i++)
            		g2.openFile(Sort.gets(VecPDF, i));
        }

private PrinterJob pj=null;
private Graphics2D graphGlobal;
private PageFormat pfGlobal;
//private int iCurX=0;
int iCurY=0;
//private int iCopies=1;								//Anzahl der Kopien (wird in der print-Funk. verwendet)
private ArrayList<Integer> alPages=new ArrayList<Integer>();

//private boolean bDialog=false;                              //PageFormat liefert Seitenmaße erst nach Druck- oder Seitendialog
						   				//richtig. (Ansonsten liefert es statt Höhe die Breite und umgekehrt)
private int iCurPage=1;

private Font gFont=null;
private PrintRequestAttributeSet aset=null;
private boolean bHeadrow=false;
private Font foHead=null;
private String sHeadLeft="";
private String sHeadCenter="";
private String sHeadRight="";
private int iHeadY=0;
private int iHeadHeight=0;
private Image ImgHead=null;
private int iImgHeadAlignment=0;

private boolean bFootrow=false;
private Font foFoot=null;
private String sFootLeft="";
private String sFootCenter="";
private String sFootRight="";
int iFootY=0;
private Image ImgFoot=null;
private int iImgFootAlignment=0;

private boolean bVorschau=false;
private boolean bRepeat=false;
private boolean bgColor=false;
private boolean bNobgcolor=true;
private boolean bHGF=false;
private Color cbgColor=null;
private boolean bGetPrintGraphics=true;
Vector<Graphics2D> VecGraphics;						//enthält Grafiken mit verschiedenen Fonts damit sie nicht nochmal
										//geholt werden müssen
private Vector<Vector<String>> VecPages;                                    //enthält alle Seiten
private Vector<String> VecCurPage=null;					//ist die aktuelle Seite
private Vector<String> VecHF=null;						//enthält Informationen die auf jeder Seite vorkommen sollen

private double dLeftMargin=0;
private double dRightMargin=0;
private double dTopMargin=0;
private double dBottomMargin=0;
private double dImageableWidth=0;
private double dImageableHeight=0;

//private int i10mm=28;
private int iKH=28;
private int iFH=28;
private boolean bKL=true;
private boolean bFL=true;
//private int i11mm=31;
private boolean bA3=false;
private boolean bQuer=false;
private boolean bSW=false;
public boolean bHtml=false;
private boolean bDruckerAuswahl=false;
//private boolean bDruckerAuswahl2=true;

public double dFaktor=1;
private Vector<Image> VecImage=new Vector<Image>();
private boolean bPrint=false;
private Global g2;
private int iPrint=0;
//private boolean bDruckerFehlt=true;
private javax.print.PrintService PS=null;
private static Tabellenspeicher TabImage = null;
private boolean bEnd=false;
private JEditorPane label = null;
public boolean bHtmlOk=false;
private int iMom=0;
private Vector<String> VecPDF=null;

//private String sZeile=null;
//private int yold=0;
//private boolean bPrintReal=true;
//private static int iAnzahl=0;
private int iFF=100;

}
