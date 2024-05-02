/*
    All_Unlimited-Allgemein-Anzeige-Zeit.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Anzeige;

import java.io.Serializable;
import java.util.Calendar;
// add your custom import statements here
import java.util.Date;

import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;

public class Zeit extends Object implements Serializable,Comparable<Zeit>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Zeit(Transact rg,Date d,String format)
	{
		this(d,format);
	}
	
	public Zeit(Date d,String format)
	{
		sFormat=format==null||format.equals("")?"dd.MM.yyyy HH:mm:ss":format;
                date=d;
                if(d!=null)
                {
                  DateWOD DW=new DateWOD(d);
                  if (sFormat.indexOf("HH")==-1)
                  {
	                  if (sFormat.indexOf("dd") > -1 || sFormat.indexOf("w") > -1)
	                    DW = DW.setTimeZero();
	                  else if (sFormat.indexOf("MM") > -1)
	                  {
	                    DW.setTimeZero();
	                    DW.setDate(1);
	                  }
	                  else if (sFormat.indexOf("yy") > -1)
	                  {
	                    DW.setTimeZero();
	                    DW.setDate(1);
	                    DW.setMonth(1);
	                  }
                  }
                  else if (sFormat.indexOf("dd")==-1)
                	  DW.setDateZero();
                  if (sFormat.indexOf("ss")==-1)
                	  DW.setSeconds(0);
                  DW.setMilliSeconds(0);
//                  if (sFormat.indexOf("w") > -1)
//                  {
//                	  System.err.println("Zeit-Format="+sFormat+": FDoW="+DW.getFirstDayOfWeek()+", MD="+DW.getMinimalDaysInFirstWeek());
//                	  DW.setFirstDayOfWeek(DW.MONDAY);
//                	  DW.setMinimalDaysInFirstWeek(4);
//                	  System.err.println("danach FDoW="+DW.getFirstDayOfWeek()+", MD="+DW.getMinimalDaysInFirstWeek());
//                  }
                  date=DW.toTimestamp();
                }
//        System.err.println("new Zeit:"+date+" mit Format "+format);
		Count.add(Count.Zeit);
	}
	
	public Zeit(Transact rg,double d,String format)
	{
		this(d,format);
	}
	
	public Zeit(double d,String format)
	{
		sFormat=format==null||format.equals("")?"dd.MM.yyyy HH:mm:ss":format;		
		date=new Date(Math.round(d));
		Count.add(Count.Zeit);
	}

	public void finalize()
	{
		Count.sub(Count.Zeit);
	}
	
	private String format()
	{
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(sFormat,DateWOD.DFS);
		if (sFormat.indexOf("w") > -1)
		{
			Calendar c=sdf.getCalendar();
	//		sdf.applyPattern(sFormat);
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.setMinimalDaysInFirstWeek(4);
		}
		return sdf.format(date);
	}

	public boolean isNull()
	{
		return(date==null);
	}

        @Override
        public String toString()
	{
		return isNull() ? "":Static.bZeitzone ? showFullDate():format();
	}
        
    private String showFullDate()
    {
    	String s=new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ssX",DateWOD.DFS).format(date);
    	boolean bDate=s.substring(11,19).equals("00:00:00");
    	boolean bNoSek=s.substring(17,19).equals("00");
    	return bDate ? s.substring(0,10):bNoSek ? s.substring(0,16)+s.substring(19):s;
    }

	public long getValue()
	{
		return(date==null ? 0:date.getTime());
	}

        public Date getDate()
	{
		return isNull()? null : date;
	}

	/*public String sqlDate()
	{
		return(isNull()?"":"'"+new java.text.SimpleDateFormat("yyyy/MM/dd").format(date)+"'");
	}

	public String sqlDateTime()
	{
		return(isNull()?"":new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date));
	}*/

	public boolean equals(Zeit z)
	{
		return date==null || z==null?date==null && z==null:getValue()==z.getValue();
	}

	public boolean equals(Date d)
	{
		return date==null || d==null?date==null && d==null:date.equals(d);
	}
	
	public boolean equals(Object o)
	{
		return o instanceof Zeit && equals((Zeit)o);
	}

        public int compareTo(Zeit z2)
        {
          if( getValue() < z2.getValue() )
            return -1;
          if( getValue() > z2.getValue() )
            return 1;
          return 0;
        }

	private Date date;
	private String sFormat;
}

