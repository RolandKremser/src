/*
    All_Unlimited-Hauptmaske-Kalender.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.SpinBoxJahr;
import All_Unlimited.Allgemein.Eingabe.SpinBoxList;

public class Kalender extends All_Unlimited.Allgemein.Formular
{
  /*public Kalender(Global glob,JPanel Pnl)
  {
    this(glob,Pnl,glob.getFomLeer());
  }*/

  public Kalender(Global glob,JPanel Pnl,JFrame Fom)
	{
		super("Kalender",Fom==null?glob.getFomLeer():Fom,glob);
		g=glob;
                Static.centerComponent(thisFrame,Pnl);

		//ColBackground = new Button().getBackground();
		//CboMonat = new ComboSort(g,ComboSort.Aic);
                CboMonat = new SpinBoxList(DateWOD.DFS.getMonths(),12,g.ColBackground);
                CboMonat.setFont(g.fontStandard);
                SBJahr = new SpinBoxJahr(g.ColBackground,1900);
                SBJahr.setFont(g.fontStandard);
		//CboMonat.fillBegriffTable("Monat",false);
                final JButton BtnOk=getButton("Ok");
                thisFrame.addWindowListener(new WindowListener()
                        {
                                public void windowClosed(WindowEvent e)
                                {
                                }
                                public void windowOpened(WindowEvent e)
                                {
                                }
                                public void windowClosing(WindowEvent e)
                                {
                                }
                                public void windowIconified(WindowEvent e)
                                {
                                }
                                public void windowDeiconified(WindowEvent e)
                                {
                                }
                                public void windowActivated(WindowEvent e)
                                {
                                        BtnOk.requestFocus();
                                }
                                public void windowDeactivated(WindowEvent e)
                                {
                                }
                        });
                BtnOk.addKeyListener(new KeyListener()
                {
                  public void keyPressed(KeyEvent e)
                  {
                    //g.progInfo("keyPressed:"+e.getKeyCode()+"/"+(e.getKeyChar()+0));
                    boolean b=true;
                    if (e.getKeyCode()== KeyEvent.VK_DOWN)
                      date.nextWeek();
                    else if (e.getKeyCode()== KeyEvent.VK_UP)
                      date.prevWeek();
                    else if (e.getKeyCode()== KeyEvent.VK_RIGHT)
                      date.tomorrow();
                    else if (e.getKeyCode()== KeyEvent.VK_LEFT)
                      date.yesterday();
                    else
                      b=false;
                    if (b)
                    {
                      //showDate.setTime(date.getTime());
                      showDate.setMonth(date.getMonth());
                      showDate.setYear(date.getYear());
                      addMonth();
                      e.consume();
                    }
                  }

                  public void keyReleased(KeyEvent e)
                  {
                    //g.progInfo("keyReleased:"+e.getKeyCode()+"/"+(e.getKeyChar()+0));
                  }

                  public void keyTyped(KeyEvent e)
                  {
                    //g.progInfo(e.getKeyCode()+"/"+(e.getKeyChar()+0));
                    if (e.getKeyChar() == 27)
                    {
                      Abbruch();
                    }
                    else if (e.getKeyChar() == '-')
                    {
                      date.prevMonth();
                      showDate.prevMonth();
                      addMonth();
                    }
                    else if (e.getKeyChar() == '+')
                    {
                      date.nextMonth();
                      showDate.nextMonth();
                      addMonth();
                    }
                    else if (e.getKeyChar() == 'b')
                    {
                      TabButtons.showGrid("Buttons");
                    }


                  }
                });


		//SBJahr.setMinimum(1800);
		//SBJahr.setMaximum(2400);
		//SBJahr.setMaximumLength(4);

		Static.addContainer(getFrei("Monat"),CboMonat);
		Static.addContainer(getFrei("Jahr"),SBJahr);
		//Static.addContainer(getFrei("Test"),LblTest);
		//LblTest.setForeground(LblTest.getBackground());

                Static.addActionListener(BtnOk,new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				date.setTimeZero();
				bOk=true;
				hide();
			}
		});
                ((JDialog)thisFrame).getRootPane().setDefaultButton(BtnOk);

		Static.addActionListener(getButton("Abbruch"),new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Abbruch();
			}
		});

		Static.addActionListener(getButton("<"),new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				int month = showDate.getMonth();
				if(month != 1)
					showDate.setMonth(month-1);
				else
				{
					showDate.setMonth(12);
					showDate.setYear(showDate.getYear()-1);
				}
				addMonth();
			}
		});


		Static.addActionListener(getButton(">"),new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				int month = showDate.getMonth();
				if(month != 12)
					showDate.setMonth(month+1);
				else
				{
					showDate.setMonth(1);
					showDate.setYear(showDate.getYear()+1);
				}
				addMonth();
			}
		});

                SBJahr.addChangeListener(new ChangeListener()
                {
                  public void stateChanged(ChangeEvent ae)
                  {
                          showDate.setYear(SBJahr.getIntValue());
                          addMonth();
                  }
		});

                CboMonat.addChangeListener(new ChangeListener()
                {
                  public void stateChanged(ChangeEvent ae)
                  {
                        int month = new Integer(CboMonat.getSelectedKennung()).intValue();
                        showDate.setMonth(month);
                        addMonth();
                  }
		});
	}

        private void Abbruch()
        {
          if(oldDate==null)
                  date=null;
          else
                  date=new DateWOD(oldDate.getTimeInMilli());
          bOk=false;
          hide();
        }

	public Date showDialog(Date d)
	{
		date= new DateWOD();
		Date d2 = d == null ? new Date() : d;
		date.setTimeInMilli(d2.getTime());
		if(d!=null)
			oldDate= new DateWOD(d2.getTime());
		else
			oldDate=null;
		showDate.setTimeInMilli(d2.getTime());
		showDate.setDate(1);
		SBJahr.setIntValue(showDate.getYear());
		//bFirst=false;
		show();
		//g.debugInfo(""+date);
		return(date!=null?date.getTime():null);
	}

	public boolean isOkConfirm()
	{
		return(bOk);
	}

	private void addMonth()
	{

		if(bChangeMonat)
		{
			bChangeMonat=false;
			if(Pnl!=null)
			{
				Pnl.removeAll();
			}
			else
			{
				Pnl = new JPanel(new GridLayout(0,8));
				getFrei("Datum").add("Center",Pnl);
			}

			int weekday = showDate.getDay()-1;
			int days = new DateWOD(showDate.getYear(),showDate.getMonth(),1).monthLength();
			int month = showDate.getMonth();
			int year = showDate.getYear();
			if(weekday==0) weekday=7;

			String[] sAryWochentage = {"Mo","Di","Mi","Do","Fr","Sa","So"};
			String sWochentag;
                        Pnl.add(new JLabel("KW",SwingConstants.CENTER));
			for(int i=0;i<sAryWochentage.length;i++)
			{
				sWochentag=g.getBegriffS("Wochentag",sAryWochentage[i]);
				Pnl.add(new JLabel(sWochentag.length()>2?sWochentag.substring(0,2):sWochentag,SwingConstants.CENTER));
			}
                        int iWeek=showDate.get(DateWOD.WEEK_OF_YEAR);
                        Pnl.add(new JLabel(""+iWeek,SwingConstants.CENTER));
			for(int i=1;i<weekday;i++)
				Pnl.add(new JLabel(""));
                        int iPos=weekday;
			Insets inset = new Insets(1,1,1,1);
			TabButtons = new Tabellenspeicher(g,new String[] {"Button","Farbe"});
			DateWOD DateFeier = new DateWOD();
			DateFeier.setTimeInMilli(showDate.getTimeInMilli());
			DateFeier.setTimeZero();
			for(int i=1;i<=days;i++)
			{
				JButton Btn = new JButton(""+i);
				Btn.setFont(SBJahr.getFont());
				Btn.setMargin(inset);
				TabButtons.addInhalt("Button",Btn);

				DateFeier.setDate(i);
				String sFeiertag = g.Feiertag(DateFeier);
				if(!sFeiertag.equals("")) Btn.setToolTipText(sFeiertag);
				Color Col = (i+weekday)%7==1 || !sFeiertag.equals("") ? g.ColHoliday : g.ColBackground;
				if(showDate.getYear()==TodayDate.getYear() && showDate.getMonth()==TodayDate.getMonth() && i==TodayDate.getDate())
					Col=g.ColToday;
				TabButtons.addInhalt("Farbe",Col);
				g.setBack(Btn,Col);

				if ((iPos%8)==0)
                                {
                                  /*if (month==12)
                                  {
                                    DateFeier.set(DateFeier.DAY_OF_WEEK, DateFeier.MONDAY);
                                    iWeek = showDate.get(DateFeier.WEEK_OF_YEAR);
                                  }
                                  else*/ if (iWeek>50 && month==1 || month==12 && i>28)
                                    iWeek=1;
                                  else
                                    iWeek++;
                                  Pnl.add(new JLabel(""+iWeek,SwingConstants.CENTER));
                                  iPos++;
                                }
                                Pnl.add(Btn);
                                iPos++;

				if(showDate.getYear()==date.getYear() && showDate.getMonth()==date.getMonth() && i==date.getDate())
					Btn.setBackground(g.ColSelect);

				Btn.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						if(showDate.getYear()==date.getYear() && showDate.getMonth()==date.getMonth())
						{
							TabButtons.setPos(date.getDate()-1);
							((JButton)TabButtons.getInhalt("Button")).setBackground((Color)TabButtons.getInhalt("Farbe"));
						}
						else
							date.setTime(showDate.getTime());
						((JButton)ev.getSource()).setBackground(g.ColSelect);
						TabButtons.posInhalt("Button",ev.getSource());
						date.setDate(TabButtons.getPos()+1);
					}
				});
			}
                        //g.progInfo("iPos="+(iPos%8));
                        if ((iPos%8)>0)
                          for(int i=(iPos%8);i<8;i++)
                            Pnl.add(new JLabel(""));
			CboMonat.setSelectedKennung(month);
			if(year!=SBJahr.getIntValue())
			{
				SBJahr.setIntValue(year);
			}

			//Pnl.validate();
                        thisFrame.validate();//addNotify();
                        //Static.sleep(100);
			//LblTest.setText(""+(++j));
			bChangeMonat=true;
			EnableButtons();
		}

	}

	private void EnableButtons()
	{
		getButton(">").setEnabled(!(showDate.getYear()==2100 && showDate.getMonth()==12));
		getButton("<").setEnabled(!(showDate.getYear()==1900 && showDate.getMonth()==1));
	}

	// add your data members here
	private Global g;
	//private Color ColBackground;
	private DateWOD showDate= new DateWOD();
	private DateWOD date;
	private DateWOD oldDate= null;
	private DateWOD TodayDate = new DateWOD();
	private Tabellenspeicher TabButtons;
	private JPanel Pnl;
	private SpinBoxList CboMonat;
	private SpinBoxJahr SBJahr = null;
	//private JLabel LblTest = new JLabel();
	private int j=0;
	//private boolean bFirst=true;
	private boolean bOk=false;
	private boolean bChangeMonat=true;

}

