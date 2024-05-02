/*
    All_Unlimited-Allgemein-Eingabe-Datum.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Hauptmaske.Kalender;

public class Datum extends javax.swing.JPanel
{
	/**
	 *
	 */
	private static final long serialVersionUID = -2166518781958880612L;
	/**
     * initDatum Method
     */

    private void initDatum()
    {
		Edt.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)
			{}
                        public void focusLost(FocusEvent e)
			{
                          CheckColor();
                            /*bLeer = Edt.getText().equals(sMask);
			    if(!bLeer)
                              CheckDatum();*/
			}
		});

		Edt.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
                          //g.fixInfo("pos keyPressed="+e.getKeyCode()+":"+Edt.getSelectionStart()+"-"+Edt.getSelectionEnd());
                          int ik = e.getKeyCode();
                          if (isEnabled())
                            if (e.getModifiers() == 2 || e.getModifiers() == 4)
                            {
                              String s = KeyEvent.getKeyText(ik);
//                              g.fixtestError("keyPressed:"+s+"/"+e.getModifiers()+"/"+bEdit);
                              if (s.equals("C"))
                              {
                                dtCopy = getDateTime();
                                sFormatC = new String(format);
                              }
                              else if (s.equals("V") && bEdit)
                              {
                                if (sFormatC.equals(format))
                                  setDate2(dtCopy);
                              }
                              else if (s.equals("A"))
                    		  {
//                            	  g.fixtestError("alles markieren");
                            	  Edt.selectAll();
                    		  }
                              else if (s.equals("Z"))
                    		  {
//                            	  g.fixtestError("zurück");
                            	  Reset2();
                    		  }
                              bCtrl=true;
                              e.consume();
                            }
                            else if (ik == KeyEvent.VK_DELETE || sMask.length() == Edt.getSelectionEnd() - Edt.getSelectionStart())
                            {
                              e.consume();
                              clear();
                            }
                        }
			public void keyReleased(KeyEvent e)
			{
//				g.fixtestError("keyReleased "+e.getModifiers()+"/"+bCtrl);			
				if (!bCtrl)				
				{
					int x=Edt.getCaretPosition();
					Edt.setText(str.toString());
					Edt.setCaretPosition(x);
                    //if (!bMass)
                    Check();
				}
				else if (e.getModifiers() == 0)
						bCtrl=false;
			}
			public void keyTyped(KeyEvent e)
			{
                          if(!bEdit || bCtrl)
                            return;

                          if (Edt.getSelectionStart()<Edt.getSelectionEnd())
                            Edt.setCaretPosition(Edt.getSelectionStart());
                          char key,key2;
                          key=e.getKeyChar();
                          //System.out.println("keyT="+key+"/"+e.getModifiers()+"/"+(bCopy? "COPY":bPaste ? "PASTE":"---"));

                          if(bDate && g!=null && key=='*')
                          {
                            e.consume();
                            OpenKalender();
                            return;
                          }
				int pos=Edt.getCaretPosition();
                                if(key=='\b')
                                {
                                  /*String sJavaVer = System.getProperty("java.version");
                                  if(sJavaVer.startsWith("1.4"))
                                  {
                                    pos = pos > 0 ? pos - 1 : pos;
                                    Edt.setCaretPosition(pos);
                                  }*/
                                  key2=str.charAt(pos);

                                  if(!Character.isDigit(key2)&&key2!='_')
                                    pos--;
                                  str.setCharAt(pos,'_');
				}
                                else if (key==':')
                                {
                                  pos=str.indexOf(":")+1;
                                }
                                else if (key==' ' || key=='_')
                                {
                                  pos++;
                                }
                                else if ( key=='-' && bMass)
                                {
                                  str.setCharAt(0,'-');
                                  str.setCharAt(1,'0');
                                  //str.setCharAt(2,'0');
                                  pos=1;
                                }
				else if(Character.isDigit(key) &&pos!=sMask.length())
				{
                                  key2=str.charAt(pos);
                                  if(!Character.isDigit(key2)&&key2!='_')
                                    pos++;
                                  str.setCharAt(pos,key);
                                  pos++;
				}
                                e.consume();
				Edt.setText(str.toString());
				Edt.setCaretPosition(pos);

			}
		});

		Edt.addMouseListener(new MouseListener()
	            {
	              public void mousePressed(MouseEvent ev)
	              {}

	              public void mouseClicked(MouseEvent ev)
	              {
	                //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
                        if (isEnabled())
	                  if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                          {
                            OpenKalender();
                          }
	              }

	              public void mouseEntered(MouseEvent ev)
	              {}

	              public void mouseExited(MouseEvent ev)
	              {}

	              public void mouseReleased(MouseEvent ev)
	              {}
	            });

		addFocusListener(new FocusListener()
		{
				public void focusGained(FocusEvent fe)
				{
					Edt.requestFocus();
                                        Edt.setCaretPosition(0);
                                        //Edt.setCaretPosition(iHourPos>0 ? iHourPos:0);
				}
				public void focusLost(FocusEvent fe)
				{
				}
		});
    }

   private void clear()
   {
     if(iNanoPos > -1)
       str = str.replace(iNanoPos, iNanoPos + 9, "_________");
     if(iSecondPos > -1)
       str = str.replace(iSecondPos, iSecondPos + 2, "__");
     if(iMinutePos > -1)
       str = str.replace(iMinutePos, iMinutePos + 2, "__");
     if(iHourPos3 > -1)
       str = str.replace(iHourPos3, iHourPos3 + 3, "___");
     else if(iHourPos > -1)
       str = str.replace(iHourPos, iHourPos + 2, "__");
     if(iYear4Pos > -1)
       str = str.replace(iYear4Pos, iYear4Pos + 4, "____");
     if(iYearPos > -1)
       str = str.replace(iYearPos, iYearPos + 2, "__");
     if(iMonthPos > -1)
       str = str.replace(iMonthPos, iMonthPos + 2, "__");
     if(iDayPos > -1)
       str = str.replace(iDayPos, iDayPos + 2, "__");
     if(iWeekPos > -1)
       str = str.replace(iWeekPos, iWeekPos + 2, "__");
     Edt.setText(str.toString());
     Edt.setCaretPosition(0);
   }

    /**
     * getSQLDate Method
     */

    public java.sql.Date getDate()
    {
      FehlerReset();
      DateWOD DW;
      if (iWeekPos>-1)
      {
        DW = new DateWOD();
        /*DW.clear();
        DW.setFirstDayOfWeek(Calendar.MONDAY);
        DW.setMinimalDaysInFirstWeek(3);
        DW.setYear(iYear);
        DW.set(Calendar.WEEK_OF_YEAR, iWeek);
        DW.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);*/
        DW.setWeek(iWeek,iYear);
      }
      else
        DW=new DateWOD(iYear,iMonth,iDay);

		return(bLeer ? null : new java.sql.Date(DW.getTimeInMilli()));
    }
    /**
     * getDateTime Method
     */
	public String getSQLDate()
	{
		FehlerReset();
		return(bLeer ? "":""+iYear+"/"+iMonth+"/"+iDay);
	}

	/*public String getSQLTime()
	{
		FehlerReset();
		return(bLeer ? "":""+iHour+":"+iMinute+":"+iSecond);
	}*/

	/*public String getSQL()
	{
		FehlerReset();
		return(bLeer ? "" : ""+iYear+"/"+iMonth+"/"+iDay+(bTime?" "+iHour+":"+iMinute+":"+iSecond:""));
			   //bDate ? ""+iYear+"/"+iMonth+"/"+iDay+(bTime?" "+iHour+":"+iMinute+":"+iSecond:""):
			   //bTime ? ""+iHour+":"+iMinute+":"+iSecond:"");
	}*/

	public long getMillis()
	{
		FehlerReset();
		return /*!bMass || Modified() true ?*/ new DateWOD(iYear,iMonth,iDay,iHour,iMinute,iSecond).getTimeInMilli();//:lAlt;
	}

	public java.sql.Timestamp getDateTime()
        {
          FehlerReset();
          DateWOD DW;
          if (iWeekPos>-1)
          {
            DW = new DateWOD();
            /*DW.clear();
            DW.setFirstDayOfWeek(Calendar.MONDAY);
            DW.setMinimalDaysInFirstWeek(3);
            DW.setYear(iYear);
            DW.set(Calendar.WEEK_OF_YEAR, iWeek);
            DW.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);*/
            DW.setWeek(iWeek,iYear);
            iMonth=DW.getMonth();
            iDay=DW.getDate();
            //g.fixtestInfo("use: Day="+iDay+", Week="+iWeek+", Month="+iMonth+", Year="+iYear);
            DW.setHours(iHour);
            DW.setMinutes(iMinute);
            DW.setSeconds(iSecond);
            //g.progInfo("Jahr="+iYear+", Woche="+iWeek+", Monat="+iMonth);
          }
          else
            DW=new DateWOD(iYear,iMonth,iDay,iHour,iMinute,iSecond);
          return(bLeer ? null : new java.sql.Timestamp(DW.getTimeInMilli()));
        }

    /*public java.sql.Time getTime()
    {
		FehlerReset();
        return(bLeer ? null : new java.sql.Time(new DateWOD(1970,1,1,iHour,iMinute,iSecond).getTimeInMilli()));
    }*/
    /**
     * setDate Method
     */
	public void setDate(Date d)
	{
          setDate(d,false,false);
	}

        public void setDate2(Date d)
        {
          Date dt=dOldDate;
          setDate(d,false,false);
          setAktDate(dt);
        }

        public void setDate(Date d,boolean b24)
        {
          setDate(d,b24,false);
        }

        public void setMass(double d)
        {
          //lAlt=Math.round(d);
          bMass=true;
          bMinus=d<0;
          bMod=iMax>0 && d>iMax;
          if (bMod)
        	  d=iMax;
          if (d==0)
          {
            clear();
            dOldDate=null;
          }
          else
            setDate(new Date(Math.round(Math.abs(d)*DateWOD.STUNDE-DateWOD.getZO())),false,false);
          Check();
          //g.progInfo("setMass:"+d+"->"+(Date)getDate()+"/"+dOldDate);
        }
        
        public double getMass()
        {
        	double d=(double)(getMillis()+DateWOD.getZO())/DateWOD.STUNDE;
        	if (iMax>0 && d>iMax)
          	  d=iMax;
        	return d;
        }

	public void setDate(Date d,boolean b24,boolean rbFull)
        {
//          g.fixtestError("setDate:"+d+"/"+b24+"/"+rbFull);
		//dOldDate = d;
		bBis=b24;
                bFull=rbFull;
		bLeer = d == null;
		if(bLeer)
		{
                  if (!bFull)
                  {
                    iYear = 1970;
                    iMonth = 1;
                  }
			iDay=1;
                        iWeek=1;
			iHour=0;
			iMinute=0;
			iSecond=0;
			iNanos=0;
			str=new StringBuffer(sMask);
			Edt.setText(sMask);
		}
		else
		{
			DateWOD date = new DateWOD();
			date.setTime(d);
			iDay=date.getDate();
                        iWeek=date.get(Calendar.WEEK_OF_YEAR);
			iMonth=date.getMonth();
			iYear=date.getYear();
			//g.fixtestInfo("set: Day="+iDay+", Week="+iWeek+", Month="+iMonth);
			if (d instanceof java.sql.Date || !bTime)
			{
				iHour=0;
				iMinute=0;
				iSecond=0;
				iNanos=0;
			}
			else
			{
				iHour=(bMass? date.getHours2():date.getHours())*(bMinus ? -1:1);
				iMinute=date.getMinutes();
                                //if (bMass && bMinus && iHour==0)
                                //  iMinute=-iMinute;
				iSecond=date.getSeconds();
				iNanos= (d instanceof java.sql.Timestamp) ? ((java.sql.Timestamp)d).getNanos():0;
				if (b24 && iHour==0 && iMinute==0 && iSecond==0 && iNanos==0)
				{
					iHour=24;
					date.yesterday();
					iDay=date.getDate();
					iMonth=date.getMonth();
					iYear=date.getYear();
				}
			}
			String s=Edt.getText();
                        boolean bDay0= iMonthPos==-1 && iYear==1969;
                        if (!bFull)
                        {
                          if (iYear4Pos > -1)
                            s = replaceString(s, "" + iYear, iYear4Pos);
                          else if (iYearPos > -1)
                            s = replaceString(s, ("" + iYear).substring(2), iYearPos);
                          else
                            iYear = 1970;

                          if (iMonthPos > -1)
                              s = replaceString(s, iMonth < 10 ? "0" + iMonth:"" + iMonth, iMonthPos);
                          else if (iWeekPos > -1)
                            iMonth =-1;
                          else
                            iMonth = 1;
                        }

                        if(iWeekPos>-1)
                           s=replaceString(s,iWeek<10 ? "0"+iWeek:""+iWeek,iWeekPos);
                        //if (bMass)
                        //  iHour=(iDay-1)*24+iHour;
			if(iDayPos>-1)
                          s=replaceString(s,iDay<10 ? "0"+iDay:bDay0 ? "00":""+iDay,iDayPos);
                        else if (iWeekPos > -1)
                          iDay =-1;
			else if (!bFull)
                          iDay=1;

                        if(iHourPos3>-1)
                          s=replaceString(s,iHour<=-10?""+iHour:iHour==0 && bMinus ? "_-0":iHour<0?"_"+iHour:iHour<10 ? "00"+iHour:iHour<100 ? "0"+iHour:""+iHour,iHourPos3);
			else if(iHourPos>-1)
				s=replaceString(s,iHour<10 ? "0"+iHour:""+iHour,iHourPos);
			else
				iHour=0;

			if(iMinutePos>-1)
				s=replaceString(s,iMinute<10 ? "0"+iMinute:""+iMinute,iMinutePos);
			else
				iMinute=0;
			g.fixtestInfo("Datum: iHourPos3="+iHourPos3+", iHour="+iHour+", bMinus="+bMinus+"-> s="+s);
			if(iSecondPos>-1)
				s=replaceString(s,iSecond<10 ? "0"+iSecond:""+iSecond,iSecondPos);
			else
				iSecond=0;
			if(iNanoPos>-1)
			{
				String str=new String(""+iNanos);
				while(str.length()<9)
				    str="0"+str;
			    s=replaceString(s,str,iNanoPos);
			}
			else
				iNanos=0;
			str=new StringBuffer(s);
			Edt.setText(s);
		}
		if (!bCtrl)
			Edt.setCaretPosition(0);
                //g.progInfo(iYear+"-"+iMonth+"-"+iDay);
                dOldDate = getDateTime();
                //g.fixtestInfo("-> Date="+dOldDate);
                CheckColor();
    }

    public void setAktDate(Date d)
    {
		dOldDate = d;
                CheckColor();
    }

    /*public Date getAktDate()
    {
      return dOldDate;
    }*/

    public void Reset2()
    {
      setDate(dOldDate);
      CheckColor();
    }
    /**
     * setFormat Method
     */

    public void setFormat(String sFormat)
    {
		format=sFormat;
                Edt.setToolTipText(format);
		sMask=new String(sFormat);
		sMask=sMask.replace('d','_');
		sMask=sMask.replace('M','_');
		sMask=sMask.replace('y','_');
                sMask=sMask.replace('w','_');
		sMask=sMask.replace('H','_');
		sMask=sMask.replace('m','_');
		sMask=sMask.replace('s','_');
		sMask=sMask.replace('o','_');

		iDayPos   =sFormat.indexOf("dd");
		iMonthPos =sFormat.indexOf("MM");
		iYearPos  =sFormat.indexOf("yy");
		iYear4Pos =sFormat.indexOf("yyyy");
                iWeekPos  =sFormat.indexOf("ww");
		bDate = (iDayPos > -1) && (iMonthPos > -1) || iWeekPos > -1;
                iHourPos3  =sFormat.indexOf("HHH");
		iHourPos  =sFormat.indexOf("HH");
		iMinutePos=sFormat.indexOf("mm");
		iSecondPos=sFormat.indexOf("ss");
		iNanoPos=sFormat.indexOf("ooooooooo");
		bTime = (iHourPos > -1) || (iMinutePos > -1) || (iSecondPos > -1);

		Edt.setText(sMask);
		str=new StringBuffer(Edt.getText());
		Edt.setColumns(sFormat.length()*2/3+1);
	}
    /**
     * replaceString Method
     */

    public String replaceString(String sHaupt, String sTeil, int iPos)
    {
        String sPart1=sHaupt.substring(0,iPos);
		String sPart2=sHaupt.substring(iPos+sTeil.length(),sHaupt.length());
		return(sPart1+sTeil+sPart2);
    }
    /**
     * Fehler Method
     */

    public boolean isValid2()
    {
        return(!bFehler);
    }

    public boolean isNull()
    {
        return(bLeer);
    }

    public boolean wasNull()
    {
        return(dOldDate==null);
    }

	public void Check()
	{
		String sText=Edt.getText();
		bLeer = sText.equals(sMask);
		bFehler = false;
		if(bLeer)
			return;

                if(iYear4Pos!=-1)
		{
			String s = sText.substring(iYear4Pos,iYear4Pos+4);

			int iPos = s.indexOf('_');
			if(iPos==-1 || s.substring(iPos).replace('_',' ').trim().equals(""))
				iYear=s.equals("____")?0:new Integer(s.replace('_',' ').trim()).intValue();
			else
				bFehler = true;
		}
		else if(iYearPos!=-1)
		{
			String s=sText.substring(iYearPos,iYearPos+2);
			iYear=s.equals("__")?0:new Integer(s.replace('_',' ').trim()).intValue();
			iYear+=1900;
			if(iYear<1930)
			   iYear+=100;

		}

		if(iMonthPos!=-1)
		{
			String s=sText.substring(iMonthPos,iMonthPos+2);
			iMonth=new Integer(s.equals("__")?"1":s.replace('_',' ').trim()).intValue();
			if(iMonth==0)
				iMonth++;
		}

                if(iWeekPos!=-1)
                {
                        String s=sText.substring(iWeekPos,iWeekPos+2);
                        iWeek=new Integer(s.equals("__")?"1":s.replace('_',' ').trim()).intValue();
                        if(iWeek==0)
                                iWeek++;
                }

		if(iDayPos!=-1)
                {
			String s=sText.substring(iDayPos,iDayPos+2);
			iDay=new Integer(s.equals("__")?"1":s.replace('_',' ').trim()).intValue();
			if(iDay==0 && iMonthPos > -1)
				iDay++;
		}
                if(iHourPos3!=-1)
                {
                        String s=sText.substring(iHourPos3,iHourPos3+3);
                        try
                        {
                          iHour = new Integer(s.equals("___") ? "0" : s.replace('_', ' ').trim()).intValue();
                        }
                        catch(Exception e)
                        {
                          iHour = 0;
                        }
                        bMinus= s.indexOf("-")>-1;

		}
		else if(iHourPos!=-1)
		{
			String s=sText.substring(iHourPos,iHourPos+2);
			iHour=new Integer(s.equals("__")?"0":s.replace('_',' ').trim()).intValue();
		}

		if(iMinutePos!=-1)
		{

			String s=sText.substring(iMinutePos,iMinutePos+2);
			iMinute=(iHour<0 ? -1:1)*new Integer(s.equals("__")?"0":s.replace('_',' ').trim()).intValue();
                        if (bMass && bMinus && iHour==0)
                          iMinute=-iMinute;
		}
		/*
		else
			iMinute=0;*/

		if(iSecondPos!=-1)
		{
			String s=sText.substring(iSecondPos,iSecondPos+2);
			iSecond=new Integer(s.equals("__")?"0":s.replace('_',' ').trim()).intValue();
		}
		/*
		else
			iSecond=0;*/

                if(iNanoPos!=-1)
		{
			String s=sText.substring(iNanoPos,iNanoPos+9);
			iNanos=new Integer(s.replace('_','0')).intValue();
		}

		CheckFehler();
	}
    private void Build()
	{

		setLayout(new BorderLayout());
		add("Center",Edt);
                if(bDate && g!=null && Static.bStern)
		{
			BtnKalender = g.getButton("...");
                        BtnKalender.setFocusable(false);
			BtnKalender.setMargin(g.inset);
                        BtnKalender.setBorder(null);
                        //BtnKalender.setBackground(g.ColEF);
			add("East",BtnKalender);

			BtnKalender.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					OpenKalender();
				}
			});
		}
                setEnabled(true);
	}

        private void CheckColor()
        {
          Edt.setBackground(Static.ColEdt(Modified(),bEdit));
        }

        public void OpenKalender()
        {
            //g.progInfo(Static.printZahl(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory(),11));
            Edt.requestFocus();
            Kalender Kal=new Kalender(g,this,Fom);
            Date dt=Kal.showDialog(getDate());
            if (Kal.isOkConfirm())
            {
                    Date dCopyOldDate = dOldDate;
                    if(iHourPos>-1 && bBis && dt != null)
                    {
                            DateWOD DW=new DateWOD(dt);
                            DW.tomorrow();
                            dt=DW.toTimestamp();
                    }
                    Static.bRefreshStop=true;
                    setDate(dt,bBis);
                    setAktDate(dCopyOldDate);
                    CheckColor();
            }
            else
              Static.bRefreshStop=true;
            Kal.thisFrame.dispose();
            //g.progInfo(Static.printZahl(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory(),11));
        }

      public Datum(Global rg,String sFormat)
      {
        this(rg,sFormat,null);
      }

      public Datum(Global rg,String sFormat,JFrame rFom)
	{
		g=rg;
                Fom=rFom;
		initDatum();
                if (sFormat != null && sFormat.startsWith("EE "))
                  sFormat=sFormat.substring(3);
		setFormat(sFormat);
                if (!Static.bRahmen)
                  Edt.setBorder(new javax.swing.border.EmptyBorder(1,1,1,1));
                //Edt.setColumns(8);
		Build();
                Edt.setCaretPosition(0);
		Count.add(Count.Datum);
	}
	public void finalize()
	{
		Count.sub(Count.Datum);
	}
	public boolean Modified()
	{
		FehlerReset();
		Date d = dOldDate instanceof java.sql.Date ? (Date)getDate() : getDateTime();
		boolean b = bMod || (d==null && dOldDate==null ? false : d==null || dOldDate==null ? true : dOldDate.getTime()!=d.getTime());
		//System.out.println("Datum.Modified: alt="+dOldDate+", neu="+d+" -> "+b);
		return b;
	}
        public long DaysChanged()
        {
          Date d = dOldDate instanceof java.sql.Date ? (Date)getDate() : getDateTime();
          return Modified() ? d==null ? 1000:(d.getTime()-dOldDate.getTime())/86400000l:0;
        }
	public void setEnabled(boolean b)
	{
          if (g != null)
            setFont(b?Transact.fontStandard:Global.fontDisabled);
          //b=b&bEdit;
		Edt.setEnabled(b);
		if(BtnKalender!=null)
			BtnKalender.setEnabled(b&bEdit);
	}
	public boolean isEnabled()
	{
		return Edt.isEnabled() && (BtnKalender==null?true:BtnKalender.isEnabled());
	}

        public void setEditable(boolean b)
        {
          bEdit=b;
          Edt.setEditable(b);
          if(BtnKalender!=null)
            BtnKalender.setEnabled(b);
        }

        public void setBackground(Color c)
        {
          if (Edt != null)
            Edt.setBackground(c);
          if(BtnKalender!=null)
            BtnKalender.setBackground(c);
        }

	public String toString()
	{
		return Edt.getText();
	}
	public JTextField getDatumEditor()
	{
		return Edt;
	}
	private boolean CheckFehler()
	{
		bFehler = bFehler || (iYear>2100||iYear<1900);
                if (iWeekPos>-1)
                  bFehler = bFehler || (iWeek > 53 || iWeek < 1);
                else
                {
                  bFehler = bFehler || (iMonth > 12 || iMonth < 1);
                  bFehler = bFehler || (iDay > new DateWOD(iYear, iMonth, 1).monthLength() || iDay<1 && iMonthPos > -1);
                }
                //g.testInfo("CheckFehler:"+bFehler+"/"+iDay+"/"+iMonthPos);
                if (!bMass)
                {
                  bFehler = bFehler || iHour > 24;
                  bFehler = bFehler || (iHour == 24 && (iMinute > 0 || iSecond > 0));
                }
		bFehler = bFehler || iMinute > 59;
		bFehler = bFehler || iSecond > 59;

		bSetzen=true;

		return bFehler;
	}
	private void FehlerReset()
	{
		if(bFehler&&bSetzen)
		{
			bSetzen=false;
			//g.progInfo("FehlerReset");
			Static.beep();
			bLeer=true;
                        if (!bFull || iYear4Pos>-1 || iYearPos>-1)
                          iYear=1970;
                        if (!bFull || iMonthPos>-1)
                          iMonth=1;
			iDay=1;
			iHour=0;
			iMinute=0;
			iSecond=0;
			iNanos=0;
			str=new StringBuffer(sMask);
			Edt.setText(sMask);
                        //Edt.setEditable(bEdit);
		}
	}

	public void setFont(Font font)
{
	if (Edt !=null)
	{
		Edt.setFont(font);
		if (BtnKalender != null)
			BtnKalender.setFont(font);
	}
}
	
	public void setMax(Integer riMax)
	{
		iMax = riMax;
		if (iMax>0 && iMax<25)
			setFormat("HH:mm");
	}

    // add your data members here
	private Global g;
    private JFrame Fom;
	public JTextField Edt=new JTextField();
	private JButton BtnKalender;

	private int iDayPos;
	private int iMonthPos;
	private int iYearPos;
	private int iYear4Pos;
        private int iWeekPos;
	private int iHourPos;
        private int iHourPos3;
	private int iMinutePos;
	private int iSecondPos;
	private int iNanoPos;

	private int iDay=1;
	private int iMonth=1;
	private int iYear=1970;
        private int iWeek=0;
	private int iHour=0;
	private int iMinute=0;
	private int iSecond=0;
	private int iNanos=0;
	private boolean bFehler=false;
	private boolean bLeer=true;
	//private int iAnzahlZiffern;
	private StringBuffer str;
	private String sMask;
	private boolean bDate=false;
	private boolean bTime=false;
        private boolean bMinus=false;
        public boolean bMass=false;
        //private long lAlt=0;
	private String format="";
	private Date dOldDate=null;
	private boolean bSetzen=false;
	private boolean bBis=false;
        private boolean bFull=false;
        private boolean bEdit=true;

        private static java.sql.Timestamp dtCopy=null;
        private static String sFormatC="xx";
        private int iMax=0;
        private boolean bMod=false;
        private boolean bCtrl=false;
}

