/*
    All_Unlimited-Allgemein-DateWOD.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.ParsePosition;
import java.time.LocalDate;

public class DateWOD extends java.util.GregorianCalendar
{
/**
	 *
	 */
private static final long serialVersionUID = -8631314222508978579L;

public static DateFormatSymbols DFS=new DateFormatSymbols();
public static long STUNDE=3600*1000;
public static long TAG=24*STUNDE;
public static long WOCHE=7*TAG;
private boolean bNull=false;
private static long ZO=-1; // für Datum für getMass/setMass
private int iZone=-1;
//private static int iAnz=0;

public DateWOD()
{
	super();
	Count.add(Count.DateWOD);
}

public DateWOD(LocalDate ld)
{
	super();
	//Calendar c =  Calendar.getInstance();
	set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth(),0,0,0);
	Count.add(Count.DateWOD);
}

public DateWOD(int year, int month, int date)
{
	super(year, month-1, date);
	Count.add(Count.DateWOD);
}

public DateWOD(int year, int month, int date, int hrs, int min)
{
	super(year, month-1, date, hrs, min);
	Count.add(Count.DateWOD);
}

public DateWOD(int year, int month, int date, int hrs, int min, int sec)
{
	super(year, month-1, date, hrs, min, sec);
	Count.add(Count.DateWOD);
}

public DateWOD(String s)
      {
        this(new SimpleDateFormat("yyyy-MM-dd").parse(s,new ParsePosition(0)));
      }

public DateWOD(long date)
{
	setTimeInMillis(date);
	Count.add(Count.DateWOD);
}

public DateWOD(long date,int riZone)
{
	setTimeInMillis(date);
	iZone=riZone;
	Count.add(Count.DateWOD);
}

public DateWOD(Date dt)
{
	this(dt,-1);
}

//public DateWOD(Date dt,double dAdd)
//{
//	this(dt,-1);
//	add((int)dAdd*1000);
//}

public DateWOD(Date dt,int riZone)
{
	if (dt==null)
		bNull=true;
	else
		setTime(dt);
	iZone=riZone;
//	if (iZone!=-1)
//	  System.err.println("erzeuge DateWOD "+dt+" mit Zone "+iZone);
	Count.add(Count.DateWOD);
}

public void setZone(int riZone)
{
	if (riZone != iZone)
	{
//		System.err.println("ändere Zone von "+iZone+" auf "+riZone);
		iZone=riZone;
	}
}

public void setZone(Global g,String sZone)
{
	if (bNull)
		return;
	int iZG=g.getZone();
	int iZD=iZG-getZone(sZone,toDate());
	if (iZD != 0)
	{
		add(iZD*60000);
		if (iZone!=-1)
			iZone=iZG;
	}
//	g.fixtestError("DateWOD.setZone "+sZone+"-> "+toString());
}

public boolean hasZone()
{
//	System.err.println("hasZone: Zone="+iZone+" bei "+toString());
	return iZone!=-1;
}

public int getZone2()
{
	return iZone;
}

public static int getZone(String sZone,Date dt)
{
	Calendar dtz = getInstance(TimeZone.getTimeZone(sZone));
	dtz.clear();
	dtz.setTime(dt);
	int iDST=dtz.get(Calendar.DST_OFFSET);
	int iZone=dtz.get(Calendar.ZONE_OFFSET);
	int iMin=(iDST+iZone)/60/1000;
//	Global.fixInfoS("getZone von "+sZone+"/"+dt+": "+iZone+"/"+iDST+"/"+iMin);
	return iMin;
}

public DateWOD(DateWOD dt)
{
	if (dt==null)
			bNull=true;
	else
	{
		setTime(dt.getTime());
		iZone=dt.iZone;
	}
	Count.add(Count.DateWOD);
}

public void finalize()
{
	Count.sub(Count.DateWOD);
}

public static DateWOD getNewYear(Date dt)
{
  DateWOD DW=new DateWOD(dt);
  DW.setTimeZero();
  DW.setDate(1);
  DW.setMonth(1);
  return DW;
}

public static long getZO() // für Datum für getMass/setMass
{
  if (ZO == -1)
    ZO=new DateWOD(0).get(ZONE_OFFSET);
  return ZO;
}

 public static int getInt(Date dt)
 {
   DateWOD DW=new DateWOD(dt);
   return DW.getYear()*10000+DW.getMonth()*100+DW.getDate();
 }

 public static java.sql.Timestamp getTS(int i)
 {
   DateWOD DW=new DateWOD(i/10000,i/100%100,i%100);
   return DW.toTimestamp();
 }

public double getAllSeconds()
{
	return getTimeInMillis()/1000.0;
}

public double getAbsSeconds()
{
	return (getTimeInMillis()+get(ZONE_OFFSET)+get(DST_OFFSET))/1000.0;
}

public DateWOD setAllSeconds(double d)
{
	setTimeInMillis(Math.round(d*1000.0));
	return this;
}

public int getDate()
{
	return get(DAY_OF_MONTH);
}

public int getDay()
{
	return get(DAY_OF_WEEK);
}

public int getYearDay(boolean bVortag)
{
  if (bVortag)
    yesterday();
  return get(DAY_OF_YEAR);
}

public int getHours()
{
	return get(HOUR_OF_DAY);
}

public int getHours2()
      {
        int iDay=get(DAY_OF_MONTH);
        return (get(MONTH)>0 ? iDay+30:iDay-1)*24+get(HOUR_OF_DAY);
      }

public int getMinutes()
{
	return get(MINUTE);
}

public int getMonth()
{
	return (get(MONTH)+1);
}

public int getSeconds()
{
	return get(SECOND);
}

public int getMilliSeconds()
{
	return(MILLISECOND);
}

public boolean hasTime()
{
	return getMinutes()>0 || getHours()>0 || getSeconds()>0;
}

public void setTimezoneOffset()
{
  //setTimeInMillis(getTimeInMillis()-get(DST_OFFSET));
  int iDS=get(DST_OFFSET);
  //System.out.println("Datum1="+Format("yyyy-MM-dd HH:mmX")+", Zone="+get(ZONE_OFFSET)+", DST="+get(DST_OFFSET));
  setTimeInMillis(getTimeInMillis()-get(ZONE_OFFSET)-get(DST_OFFSET));
  iDS-=get(DST_OFFSET);
  if (iDS!=0)
	  setTimeInMillis(getTimeInMillis()+iDS);
  //System.out.println("Datum2="+Format("yyyy-MM-dd HH:mmX")+", DST="+get(DST_OFFSET)+" bei DS="+iDS);
  //
}

public int getAZone()
{
	return (get(ZONE_OFFSET)+get(DST_OFFSET))/1000/60;
}

public int getYearW()
{
  int iY=get(YEAR);
  return get(MONTH)==11 && get(WEEK_OF_YEAR)==1 ? iY+1:get(MONTH)==0 && get(WEEK_OF_YEAR)>50 ? iY-1:iY;
}

public int getYear()
{
  return get(YEAR);
}

public void setDate(int date)
{
	set(DAY_OF_MONTH, date);
}

public void setHours(int hours)
{
	set(HOUR_OF_DAY, hours);
}

public void setMinutes(int minutes)
{
	set(MINUTE, minutes);
}

public void setMonth(int month)
{
	set(MONTH, month-1);
}

public void setSeconds(int seconds)
{
	set(SECOND, seconds);
}

public void setMilliSeconds(int ms)
{
	set(MILLISECOND, ms);
}

public void setTimeInMilli(long time)
{
	setTimeInMillis(time);
}

public long getTimeInMilli()
{
	return getTimeInMillis();
}

public void setYear(int year)
{
	set(YEAR, year);
}

//public boolean isSameDay(DateWOD dw)
//{
//  boolean b= dw!=null && getDate()==dw.getDate() && get(MONTH)==dw.get(MONTH) && get(YEAR)==dw.get(YEAR);
//  //System.out.println("------------- isSameDay:"+this+"/"+dw+"->"+b);
//  return b;
//}

public boolean compareDay(char c,DateWOD dw)
{
	return compareDay(c,dw,false);
}

public boolean compareDay(char c,DateWOD dw,boolean bTime)
{
	if (dw==null || dw.isNull() || isNull())
		return false;
	long i1=dw.getDate()+dw.get(MONTH)*100+dw.get(YEAR)*10000;
	long i2=getDate()+get(MONTH)*100+get(YEAR)*10000;
	if (bTime)
	{
		i1=i1*10000+dw.getHours()*100+dw.getMinutes();
		i2=i2*10000+getHours()*100+getMinutes();
//		System.err.println("compareDay: i1="+i1+", i2="+i2);
	}
	return c=='=' && i2==i1 || c=='<' && i2<i1 || c=='>' && i2>i1 || c=='≥' && i2>=i1 || c=='≤' && i2<=i1 || (c=='≠' || c=='!') && i2 != i1;
}

public String Format(String s)
{
	return bNull ? "":Format(null,s,getTime());//new SimpleDateFormat(s,DFS).format(getTime()));
}

public String Format(Transact g,String s)
{
	//DateFormatSymbols DFS=g!=null ? g.DFS:new DateFormatSymbols();
	return(bNull?"":(g==null ? new SimpleDateFormat(s):new SimpleDateFormat(s,g.DFS)).format(getTime()));
}

public static String Format(Global g,String sF,Date dt)
{
	if (dt==null)
		return "";
//	iAnz++;
	java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(sF,g==null ? DFS:g.DFS);
	Calendar c=sdf.getCalendar();
	c.setFirstDayOfWeek(Calendar.MONDAY);
	c.setMinimalDaysInFirstWeek(4);
	String s= sdf.format(dt.getTime());
//	System.err.println("DateWOD"+iAnz+" mit "+sF+" -> "+s);
	return s;
}

/*public String SQL_Date()
{
	return(bNull?"null":"'"+Format("yyyy/MM/dd")+"'");
}*/

/*public String SQL_Timestamp()
{
	return(bNull?"null":"'"+Format("yyyy/MM/dd HH:mm:ss")+"'");
}*/

public String toString()
{
	return bNull?"null":Format("yyyy-MM-dd HH:mm:ss")+(iZone!=-1 ? " ("+iZone+")":"");
}

public DateWOD setDay1()
{
        setDate(1);
        setHours(0);
        setMinutes(0);
        setSeconds(0);
        setMilliSeconds(0);
        return this;
}

public DateWOD setDateZero()
{
  setDate(1);
  setMonth(1);
  setYear(1970);
  return this;
}

public DateWOD setTimeZero()
{
	setHours(0);
	setMinutes(0);
	setSeconds(0);
	setMilliSeconds(0);
	return this;
}

public DateWOD setTimeZero2()
{
	if (getHours()!=0 || getMinutes()!=0 || getSeconds()!=0)
	{
		setTimeZero();
		tomorrow();
	}
	return this;
}

public void trunc(long l)
{
  setTimeInMilli(getTimeInMilli()/l*l);
}

public int monthLength()
{
	// Java 1.3
	//return getActualMaximum(DAY_OF_MONTH);

	// Java 1.1
	int MONTH_LENGTH[] = {31,28,31,30,31,30,31,31,30,31,30,31}; // 0-based
    int LEAP_MONTH_LENGTH[] = {31,29,31,30,31,30,31,31,30,31,30,31}; // 0-based
	return isLeapYear(get(YEAR)) ? LEAP_MONTH_LENGTH[get(MONTH)] : MONTH_LENGTH[get(MONTH)];
}

public int yearLength()
{
	return isLeapYear(get(YEAR)) ? 366:365;
}

public void next(String s)
{
  if (s.equals("Tag"))
    tomorrow();
  else if (s.equals("Woche"))
    nextWeek();
  else if (s.equals("Monat"))
    nextMonth();
  else if (s.equals("Quartal"))
  {
    nextMonth();
    nextMonth();
    nextMonth();
  }
  else if (s.equals("Jahr"))
    nextYear();
}

public void prev(String s)
{
  if (s.equals("Tag"))
    yesterday();
  else if (s.equals("Woche"))
    prevWeek();
  else if (s.equals("Monat"))
    prevMonth();
  else if (s.equals("Quartal"))
  {
    prevMonth();
    prevMonth();
    prevMonth();
  }
  else if (s.equals("Jahr"))
    prevYear();
}

public void nextYear()
{
	roll(YEAR,true);
}

public void nextMonth()
{
	//System.out.println("vorher: MONTH:"+get(MONTH)+", Year:"+get(YEAR));
	if (get(MONTH)==11)
		roll(YEAR,true);
	roll(MONTH,true);
	//System.out.println("nachher: MONTH:"+get(MONTH)+", Year:"+get(YEAR));
}

public void nextWeek()
{
	//System.out.println("vorher: WEEK_OF_YEAR:"+get(WEEK_OF_YEAR)+", Year:"+get(YEAR));
	/*roll(WEEK_OF_YEAR,true);
	if (get(WEEK_OF_YEAR)==1)
		roll(YEAR,true);*/
	long lDSTVorher = get(DST_OFFSET);
	setTimeInMilli(getTimeInMilli()+WOCHE);

	long lDSTNachher = get(DST_OFFSET);
	if(lDSTVorher != lDSTNachher)
		setTimeInMilli(getTimeInMilli()+lDSTVorher-lDSTNachher);

	//System.out.println("nachher: WEEK_OF_YEAR:"+get(WEEK_OF_YEAR)+", Year:"+get(YEAR));
}

public void prevYear()
{
	roll(YEAR,false);
}

public void prevMonth()
{
	if (get(MONTH)==0)
		roll(YEAR,false);
	roll(MONTH,false);
}

public void prevWeek()
{
	//System.out.println("vorher: WEEK_OF_YEAR:"+get(WEEK_OF_YEAR)+", Year:"+get(YEAR));
	/*if (get(WEEK_OF_YEAR)==1)
		roll(YEAR,false);
	roll(WEEK_OF_YEAR,false);*/
	long lDSTVorher = get(DST_OFFSET);
	setTimeInMilli(getTimeInMilli()-WOCHE);

	long lDSTNachher = get(DST_OFFSET);
	if(lDSTVorher != lDSTNachher)
		setTimeInMilli(getTimeInMilli()+lDSTVorher-lDSTNachher);
	//System.out.println("nachher: WEEK_OF_YEAR:"+get(WEEK_OF_YEAR)+", Year:"+get(YEAR));
}

public void setWeek(int iWeek,int iYear)
{
	clear();
    setFirstDayOfWeek(Calendar.MONDAY);
    setMinimalDaysInFirstWeek(4);
    setYear(iYear);
    set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    set(Calendar.WEEK_OF_YEAR, iWeek);
    //set(Calendar.HOUR,2);
    //computeTime();
}

public void setWeekday(int i)
{
	int iWeek=get(WEEK_OF_YEAR);
	int iYear=getYearW();
	//System.out.println(i+"/"+getFirstDayOfWeek()+"/"+java.util.Calendar.MONDAY);
	clear();
	set(YEAR,iYear);
	set(WEEK_OF_YEAR,iWeek);
	set(DAY_OF_WEEK,getFirstDayOfWeek()+i);
	//computeFields();
}

public long tomorrow()
{
	long lDSTVorher = get(DST_OFFSET);
	setTimeInMilli(getTimeInMilli()+TAG);

	long lDSTNachher = get(DST_OFFSET);
	setTimeInMilli(getTimeInMilli()+lDSTVorher-lDSTNachher);

	return getTimeInMilli();
}

public long add(long l)
{
   setTimeInMilli(getTimeInMilli()+l);
   return getTimeInMilli();
}

public long yesterday()
{
	long lDSTVorher = get(DST_OFFSET);
	//System.err.println("Nach: "+this+" / "+lDSTVorher);
	setTimeInMilli(getTimeInMilli()-TAG);

	long lDSTNachher = get(DST_OFFSET);
	setTimeInMilli(getTimeInMilli()+lDSTVorher-lDSTNachher);
	//System.err.println("Vor: "+this+" / "+lDSTNachher);

	return getTimeInMilli();
}

public static java.sql.Date toDate(double d)
{
  DateWOD DW=new DateWOD();
  DW.setAllSeconds(d);
  DW.setTimezoneOffset();
  return new java.sql.Date(DW.getTimeInMilli());
}

public static java.sql.Timestamp toTime(double d)
{
  DateWOD DW=new DateWOD();
  DW.setAllSeconds(d);
  DW.setTimezoneOffset();
  return new java.sql.Timestamp(DW.getTimeInMilli());
}

public java.sql.Date toDate()
{
	return bNull?null:new java.sql.Date(getTimeInMilli());
}

public java.sql.Timestamp toTimestamp()
{
	return bNull?null:new java.sql.Timestamp(getTimeInMilli());
}

public boolean isNull()
{
	return bNull;
}

public static String AbsFormat(String sFormat, double dMilli)
{
	DateWOD d=new DateWOD(Math.round(dMilli));
	d.setTimezoneOffset();
	return d.Format(sFormat);
}



// add your data members here
}

