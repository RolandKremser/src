/*
    All_Unlimited-Allgemein-Gauge.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

import java.awt.Window;
// add your custom import statements here
//import java.awt.*;
//import java.awt.*;
//import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Gauge extends java.lang.Thread implements ActionListener
{
    // add your data members here
	/*public Gauge(int riMin,int riMax,String sLbl)
	{
		this(riMin,riMax,sLbl,null,false);
	}*/

        public Gauge(int riMin,int riMax,String sLbl,Transact rg,boolean bButton)
        {
          this("",riMin,riMax,sLbl,rg,bButton,null);
        }
        
        public Gauge(String sTitel,int riMin,int riMax,String sLbl,Transact rg,boolean bButton)
        {
        	this(sTitel,riMin,riMax,sLbl,rg,bButton,null);
        }

	public Gauge(String sTitel,int riMin,int riMax,String sLbl,Transact rg,boolean bButton,Window wP)
	{
		g=rg;
		//g.fixtestInfo(" - - - - - -   new Gauge:"+sTitel+", "+riMin+"-"+riMax+", "+sLbl);
		//Static.sleep(2000);
		//g.fixtestInfo("weiter mit Gauge");
                lClock=Static.get_ms();
                iAnzahl++;
		if(bButton)
                {
                  button = Static.BtnAbbruch;
                  button.setBounds(0,38,274,28);
                }
		iMin=riMin;
		iMax=riMax;

		LblText=new JLabel(sLbl);//"  ");
                LblText.setFont(Transact.fontStandard);
		LblCount=new JLabel("   0/"+riMax+"  ",SwingConstants.RIGHT);
                LblCount.setFont(Transact.fontStandard);
		//fo=LblText.getFontMetrics(LblText.getFont());
		pbar=new JProgressBar();

		pbar.setMinimum(iMin);
		pbar.setMaximum(iMax);

                if (!Static.bX11)
                {
                  Transact.fixInfoS("keine Gauge für "+sTitel+" weil Grafik fehlt");
                  if (!Static.Leer(sLbl))
                	  Transact.fixInfoS(iCount+":"+sLbl);
                  return;
                }
		fGauge=new JFrame(sTitel);
		fGauge.getContentPane().setLayout(new java.awt.BorderLayout());
		if(bButton)
			fGauge.setSize(290,105);
		else
			fGauge.setSize(290,75);


		/*fGauge.getContentPane().add("East",LblCount);
		fGauge.getContentPane().add("South",button);
		fGauge.getContentPane().add("Center",LblText);

		fGauge.getContentPane().add("North",pbar);//*/
		//----------------

		fGauge.getContentPane().setLayout(null);
		LblText.setBounds(5,0,180,20);
		LblCount.setBounds(180,0,94,20);
		pbar.setBounds(5,20,274,15);
		fGauge.getContentPane().add(LblText);
		fGauge.getContentPane().add(LblCount);
		fGauge.getContentPane().add(pbar);
		if(bButton)
                {
                  fGauge.getContentPane().add(button);
                  fGauge.getRootPane().setDefaultButton(button);
                }
		//----------------

		//fGauge.getContentPane().add(new JPanel());
		fGauge.setVisible(true);
		fGauge.setResizable(false);
                if (bButton)
                  fGauge.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                //else
                //  fGauge.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                //fGauge.pack();
                else
                  fGauge.addWindowListener(
			new WindowListener(){
				public void windowOpened( java.awt.event.WindowEvent e){ }//g.progInfo("--- windowOpened ---");}
  				public void windowClosing( java.awt.event.WindowEvent e)
				{
                                  //g.progInfo("--- windowClosing ---");
					iCount=iMax;
					bSet=true;
				}
 				public void windowClosed( java.awt.event.WindowEvent e){ }//g.progInfo("--- windowClosed ---");}
  				public void windowIconified( java.awt.event.WindowEvent e){ }//g.progInfo("--- windowIconified ---");}
  				public void windowDeiconified( java.awt.event.WindowEvent e){ }//g.progInfo("--- windowDeiconified ---");}
				public void windowActivated( java.awt.event.WindowEvent e){ }//g.progInfo("--- windowActivated ---");}
  				public void windowDeactivated( java.awt.event.WindowEvent e){ }//g.progInfo("--- windowDeactivated ---");}
			}
			);
                /*if(bButton)
		button.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					g.fixInfo("Abbruch!!");
					bEscape=true;
                                        fGauge.dispose();
				}
			}
			);*/

		 Static.centerComponent(fGauge,wP==null ? null:Static.getMiddle(wP),iAnzahl-1);

		start();

		fGauge.update(fGauge.getGraphics());
                //g.testInfo("Gauge "+iAnzahl);
                //if (iAnzahl==1)
                //   fGauge.toFront();
	}

	public void run()
	{

			int iUpdate=100;

                        if (button != null)
                          button.addActionListener(this);
                        //long lClock=Static.get_µs();
			while(iCount!=iMax)
			{
				if(iCount!=0)
				{
					if(iCount>iMax)
					{
						Static.printError("Übergebener Wert ("+iCount+") größer MaxWert ("+iMax+") bei "+fGauge.getTitle()+"!");
						break;
					}
					else
					{
						if(bSet)
						{
							/*try{
								ta[0].sleep(1000);
								ta[3].sleep(1000);
							}catch(Exception e){}*/
							pbar.setValue(iCount);
                                                        //iCountOld=iCount;
							fGauge.update(fGauge.getGraphics());
							bSet=false;
						}
					}
				}
				if(iUpdate==0)
				{
                                  if (iCountOld != iCount)
                                  {
                                    fGauge.update(fGauge.getGraphics());
                                    iCountOld=iCount;
                                    //g.clockµInfo("" + iCount, lClock);
                                  }
                                  iUpdate=100;
				}
                                //if (bInfo)
                                  Static.sleep(5);
				/*try
				{
					Thread.sleep(10);
				}catch(Exception e)
				{
					Static.printError("All_Unlimited.Allgemein.Gauge:run()->"+e.toString());
				}*/

				iUpdate--;
			}
                        iAnzahl--;
			pbar.setValue(iCount);
			fGauge.update(fGauge.getGraphics());
			try
			{
                          if (bInfo)
				Thread.sleep(200);
			}catch(Exception e)
			{
				Static.printError("Gauge.run:"+e.toString()+" bei "+fGauge.getTitle());
			}
			fGauge.dispose();


		}

        public void free()
        {
          if (fGauge != null)
          {
            iAnzahl--;
            fGauge.dispose();
          }
        }

	public void setText(String sLbl)
	{
		setText(sLbl,++iCount);
	}

	public void setText(String sLbl,int iCount)
	{
		this.iCount=iCount;
		bSet=true;

		//if(sLbl.length()>iStringLength)//<--
		  // sLbl=sLbl.substring(0,iStringLength-3)+"...";//<--
		//LblText.setText("       "+sLbl+" "+iCount+"/"+iMax);//<--

		/*if(fo.stringWidth(sLbl)>iStringLength)
		{
			sLbl=sLbl.substring(0,sLbl.length()-2);
			while(fo.stringWidth(sLbl+"...")>iStringLength)
				sLbl=sLbl.substring(0,sLbl.length()-1);
			sLbl+="...";
		}*/
             if (g.TestPC() && bInfo)
                g.clockInfo((iMax>iCount?"vor ":"")+sLbl+" ("+iCount+")",lClock);
		LblCount.setText((iCount)+"/"+iMax);
		LblText.setText(sLbl);
                iCountOld=-1;
            if (Static.bX11 && !Static.bDefBezeichnung)
            {
              //g.clockInfo("toFront",lClock);
              //fGauge.update(fGauge.getGraphics());
              fGauge.toFront();
            }
            else
            	Transact.fixInfoS(iCount+":"+sLbl);
	}

	public int getMax()
	{
		return(iMax);
	}

	public void actionPerformed(ActionEvent ae)
	{
		Transact.fixInfoS("Gauge-"+fGauge.getTitle()+"-Abbruch !!!");
         bEscape=true;
	}

        public void noInfo()
        {
          bInfo=false;
        }

        public static int getAnzahl()
        {
          return iAnzahl;
        }

	/*public void dispose()
	{
		try{Thread.sleep(350);}catch(Exception e){}
		fGauge.dispose();
		stop();
	}*/

	/*private static void centerFrame(Frame f)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = f.getSize();
		screenSize.height = screenSize.height/2;
		screenSize.width = screenSize.width/2;
		size.height = size.height/2;
		size.width = size.width/2;
		int y = screenSize.height - size.height;
		int x = screenSize.width - size.width;
		f.setLocation(x, y);
	}*/

	private All_Unlimited.Allgemein.Transact g=null;
	private static JButton button=null;
	//private FontMetrics fo=null;
	private JLabel LblText=null;
	private JLabel LblCount=null;
	private JProgressBar pbar=null;

	private JFrame fGauge=null;
        private long lClock=0;
	//private int iWert=0;
	private int iCount=0;
        private int iCountOld=-1;
	private int iMin=0;
	private int iMax=0;
	//int iCountSave=0;
	//int iStringLength=0;
	private boolean bSet=false;
	public boolean bEscape=false;
        private boolean bInfo=true;
        private static int iAnzahl=0;
}

