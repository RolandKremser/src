// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarComponent.java

package jclass.field;

import java.awt.*;
import java.text.DateFormatSymbols;
import java.util.*;
import javax.swing.JComponent;
import jclass.base.*;
import jclass.bwt.BWTEnum;
import jclass.bwt.JCComponent;
import jclass.util.JCVector;

// Referenced classes of package jclass.field:
//            JCCalendarEvent, JCCalendarListener, JCDateMonthYear, JCDayOfWeek, 
//            JCMonthDayOfMonth, JCMonthWeekDayOfWeek

public class JCCalendarComponent extends JCComponent
{

    public JCCalendarComponent()
    {
        this(TimeZone.getDefault(), Locale.getDefault());
    }

    public JCCalendarComponent(TimeZone timezone)
    {
        this(timezone, Locale.getDefault());
    }

    public JCCalendarComponent(Locale locale)
    {
        this(TimeZone.getDefault(), locale);
    }

    public JCCalendarComponent(TimeZone timezone, Locale locale)
    {
        this(Calendar.getInstance(timezone, locale), locale);
    }

    public JCCalendarComponent(Calendar calendar, Locale locale)
    {
        listeners = new JCVector(0);
        start_point = new Point(0, 0);
        end_point = new Point(0, 0);
        selected_point = new Point(0, 0);
        special_dates = new JCVector(0);
        selected = false;
        cell_highlight_thickness = 1;
        header_gap = 2;
        DateFormatSymbols dateformatsymbols = new DateFormatSymbols(locale);
        setBorderThickness(2);
        setHighlightThickness(0);
        days = dateformatsymbols.getShortWeekdays();
        selected_calendar = calendar;
        first_day_of_week = (selected_calendar.getFirstDayOfWeek() + 6) % 7;
    }

    public void addNotify()
    {
        Color color = getBackground();
        Color color1 = getForeground();
        super.addNotify();
        enableEvents(32L);
        if(header_foreground == null)
            header_foreground = color1;
        if(header_background == null)
            header_background = color;
        String s = getFont().getName();
        int i = getFont().getSize();
        if(header_font == null)
            header_font = new Font(s, 1, i);
        if(selected_foreground == null)
            selected_foreground = color1;
        if(selected_background == null)
            selected_background = color;
        if(special_day_background == null)
            special_day_background = color;
        if(special_day_foreground == null)
            special_day_foreground = color1;
        calculateCellSize();
    }

    public static boolean isLeapYear(int i)
    {
        boolean flag = false;
        if(i % 100 == 0)
        {
            if(i % 400 == 0)
                flag = true;
        } else
        if(i % 4 == 0)
            flag = true;
        return flag;
    }

    public static int dayOfWeek(int i, int j, int k)
    {
        if(j == 0 || j == 1)
            k--;
        int j1 = k % 100;
        int k1 = k / 100;
        int i1 = (j + 10) % 12 + 1;
        int l = (int)((((double)i + Math.floor(2.6000000000000001D * (double)i1 - 0.20000000000000001D)) - (double)(2 * k1)) + (double)j1 + Math.floor(j1 / 4) + Math.floor(k1 / 4)) % 7;
        l = (l + 7) % 7;
        return l;
    }

    protected synchronized void calculateCellSize()
    {
        int k = 0;
        Font font = getFont();
        if(!isDisplayable())
            return;
        int j = Math.max(getFontMetrics(font).getHeight(), getFontMetrics(header_font).getHeight());
        for(int i1 = 0; i1 < days.length; i1++)
        {
            int l;
            if(num_header_chars <= 0 || num_header_chars > days[i1].length())
                l = days[i1].length();
            else
                l = num_header_chars;
            if(k < getFontMetrics(header_font).stringWidth(days[i1].substring(0, l)))
                k = getFontMetrics(header_font).stringWidth(days[i1].substring(0, l));
        }

        int i = Math.max(getFontMetrics(font).stringWidth("00"), k);
        cell_size = Math.max(j, i) + 2 * cell_margin + 2 * cell_highlight_thickness;
        setPreferredSize(cell_size * 7, cell_size * 7 + header_gap);
    }

    protected synchronized void paintComponent(Graphics g)
    {
        int i = startx = (getSize().width - 7 * cell_size) / 2;
        int j = (getSize().height - (7 * cell_size + header_gap)) / 2;
        drawHeader(g, i, j);
        drawCalendar(g, i, j);
    }

    protected synchronized void drawHeader(Graphics g, int i, int j)
    {
        Point point = new Point(i, j);
        g.setColor(header_background);
        g.fillRect(0, 0, cell_size * 7, cell_size);
        g.setColor(header_foreground);
        g.setFont(header_font);
        if(header_background.equals(getBackground()))
        {
            g.drawLine(i, j + cell_size, i + 7 * cell_size, j + cell_size);
        } else
        {
            g.setColor(header_background);
            g.fillRect(0, 0, getSize().width, cell_size);
        }
        g.setFont(header_font);
        g.setColor(header_foreground);
        int k = getFontMetrics(header_font).getAscent();
        int l = 0;
        for(int i1 = first_day_of_week + 1; l < 7; i1++)
        {
            i1 = (i1 + 6) % 7 + 1;
            int j1;
            if(num_header_chars <= 0 || num_header_chars > days[i1].length())
                j1 = days[i1].length();
            else
                j1 = num_header_chars;
            Point point1 = centerInCell(days[i1].substring(0, j1), getHeaderFont(), i, j);
            g.drawString(days[i1].substring(0, j1), point1.x, point1.y + k);
            i += cell_size;
            l++;
        }

    }

    protected synchronized void drawCalendar(Graphics g, int i, int j)
    {
        int k1 = selected_calendar.get(2);
        int l1 = selected_calendar.get(1);
        int i2 = selected_calendar.get(5);
        Point point = new Point(i, j);
        Font font = getFont();
        Color color = getForeground();
        Color color1 = getBackground();
        num_of_days = days_in_month[k1];
        if(k1 == 1 && isLeapYear(l1))
            num_of_days++;
        int l = dayOfWeek(1, k1, l1);
        if(first_day_of_week != 0)
            l = ((l - first_day_of_week) + 7) % 7;
        i = startx + l * cell_size;
        starty = j += cell_size + header_gap;
        start_point.x = 0;
        start_point.y = l;
        g.setFont(font);
        g.setColor(color);
        int i1 = getFontMetrics(font).getAscent();
        num_rows = 0;
        int k = 1;
        int j1;
        for(j1 = l; k <= num_of_days; j1++)
        {
            if(j1 > 6)
            {
                j1 = 0;
                num_rows++;
                i = startx;
                j += cell_size;
            }
            Point point1 = centerInCell(String.valueOf(k), getFont(), i, j);
            if(k == i2)
            {
                g.setColor(selected_background);
                g.fillRect(i + cell_highlight_thickness, j + cell_highlight_thickness, cell_size - 2 * cell_highlight_thickness, cell_size - 2 * cell_highlight_thickness);
                g.setColor(selected_foreground);
                g.drawString(String.valueOf(k), point1.x, point1.y + i1);
                g.setColor(color);
                Border.draw(g, 3, cell_highlight_thickness, i, j, cell_size, cell_size, color1, color);
                selected_point.x = num_rows;
                selected_point.y = j1;
            } else
            if(isSpecialDay(l1, k1, k, num_rows + 1))
            {
                g.setColor(special_day_background);
                g.fillRect(i, j, cell_size, cell_size);
                g.setColor(special_day_foreground);
                g.drawString(String.valueOf(k), point1.x, point1.y + i1);
                g.setColor(color);
            } else
            {
                g.drawString(String.valueOf(k), point1.x, point1.y + i1);
            }
            i += cell_size;
            k++;
        }

        end_point.x = num_rows;
        end_point.y = j1 - 1;
    }

    protected boolean isSpecialDay(int i, int j, int k, int l)
    {
        return isInSpecialDayList(i, j, k, l) >= 0;
    }

    protected Point centerInCell(String s, Font font, int i, int j)
    {
        Point point = new Point(i, j);
        int k = getFontMetrics(font).stringWidth(s);
        int l = getFontMetrics(font).getHeight();
        if(i + cell_size > k)
            point.x = i + (cell_size - k) / 2;
        if(j + cell_size > l)
            point.y = j + (cell_size - l) / 2;
        return point;
    }

    protected boolean getNewSelectedDate(Event event, int i, int j)
    {
        if(i < startx || j < starty || i > startx + cell_size * 7 || j > cell_size * 7)
            return false;
        int l = (i - startx) / cell_size;
        int k = (j - starty) / cell_size;
        if(k < start_point.x || k > end_point.x || k == start_point.x && l < start_point.y || k == end_point.x && l > end_point.y)
        {
            selected = false;
            return false;
        } else
        {
            selected = true;
            calculateNewDate(k, l);
            time = selected_calendar.getTime();
            repaint();
            requestFocus();
            postDateChangedEvent();
            return true;
        }
    }

    public boolean mouseDrag(Event event, int i, int j)
    {
        return getNewSelectedDate(event, i, j);
    }

    public boolean mouseUp(Event event, int i, int j)
    {
        if(selected)
        {
            postDateSelectedEvent();
            selected = false;
        }
        return true;
    }

    public boolean mouseDown(Event event, int i, int j)
    {
        return getNewSelectedDate(event, i, j);
    }

    public boolean mouseEnter(Event event, int i, int j)
    {
        return true;
    }

    private void calculateNewDate(int i, int j)
    {
        int k = selected_calendar.get(5);
        if(i == selected_point.x)
            k += j - selected_point.y;
        if(i > selected_point.x)
        {
            k += 7 * (i - selected_point.x - 1);
            k += (j - selected_point.y) + 7;
        } else
        if(i < selected_point.x)
        {
            k += 7 * ((i - selected_point.x) + 1);
            k += j - selected_point.y - 7;
        }
        if(k <= 0 || k > num_of_days)
        {
            return;
        } else
        {
            selected_calendar.set(5, k);
            return;
        }
    }

    public boolean keyDown(Event event, int i)
    {
        switch(i)
        {
        case 1004: 
            selected_calendar.add(5, -7);
            break;

        case 1005: 
            selected_calendar.add(5, 7);
            break;

        case 1006: 
            selected_calendar.add(5, -1);
            break;

        case 1007: 
            selected_calendar.add(5, 1);
            break;

        case 1002: 
            selected_calendar.add(2, -1);
            break;

        case 1003: 
            selected_calendar.add(2, 1);
            break;

        default:
            return false;
        }
        time = selected_calendar.getTime();
        repaint();
        postDateChangedEvent();
        return true;
    }

    protected void postDateChangedEvent()
    {
        if(listeners.size() == 0)
            return;
        JCCalendarEvent jccalendarevent = new JCCalendarEvent(this, 0, selected_calendar);
        for(int i = 0; i < listeners.size(); i++)
            ((JCCalendarListener)listeners.elementAt(i)).dateChanged(jccalendarevent);

    }

    protected void postDateSelectedEvent()
    {
        if(listeners.size() == 0)
            return;
        JCCalendarEvent jccalendarevent = new JCCalendarEvent(this, 0, selected_calendar);
        for(int i = 0; i < listeners.size(); i++)
            ((JCCalendarListener)listeners.elementAt(i)).dateSelected(jccalendarevent);

    }

    public void addCalendarListener(JCCalendarListener jccalendarlistener)
    {
        listeners.addUnique(jccalendarlistener);
    }

    public void removeCalendarListener(JCCalendarListener jccalendarlistener)
    {
        listeners.removeElement(jccalendarlistener);
    }

    public synchronized void setFont(Font font)
    {
        super.setFont(font);
        calculateCellSize();
        setSize(getPreferredSize());
        repaint();
    }

    public Color getHeaderBackground()
    {
        return header_background;
    }

    public void setHeaderBackground(Color color)
    {
        header_background = color;
        repaint();
    }

    public Color getHeaderForeground()
    {
        return header_foreground;
    }

    public void setHeaderForeground(Color color)
    {
        header_foreground = color;
        repaint();
    }

    public Font getHeaderFont()
    {
        return header_font;
    }

    public void setHeaderFont(Font font)
    {
        header_font = font;
        calculateCellSize();
        setSize(getPreferredSize());
        repaint();
    }

    public Color getSelectedBackground()
    {
        return selected_background;
    }

    public void setSelectedBackground(Color color)
    {
        selected_background = color;
        repaint();
    }

    public Color getSelectedForeground()
    {
        return selected_foreground;
    }

    public void setSelectedForeground(Color color)
    {
        selected_foreground = color;
        repaint();
    }

    public Color getSpecialDayBackground()
    {
        return special_day_background;
    }

    public void setSpecialDayBackground(Color color)
    {
        special_day_background = color;
        repaint();
    }

    public Color getSpecialDayForeground()
    {
        return special_day_foreground;
    }

    public void setSpecialDayForeground(Color color)
    {
        special_day_foreground = color;
        repaint();
    }

    public int getCellMargin()
    {
        return cell_margin;
    }

    public void setCellMargin(int i)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException("invalid value for cell margin");
        } else
        {
            cell_margin = i;
            calculateCellSize();
            setSize(getPreferredSize());
            repaint();
            return;
        }
    }

    public int getNumHeaderChars()
    {
        return num_header_chars;
    }

    public void setNumHeaderChars(int i)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException("invalid value for number of header chars");
        } else
        {
            num_header_chars = i;
            calculateCellSize();
            setSize(getPreferredSize());
            repaint();
            return;
        }
    }

    public void setSelectedDate(int i, int j, int k)
    {
        if(i <= 0)
            i = 1;
        if(j < 0)
            j = 0;
        if(j > 11)
            j = 11;
        int l = days_in_month[j];
        if(j == 1 && isLeapYear(i))
            l++;
        if(k > l)
            k = l;
        if(k < 1)
            k = 1;
        selected_calendar.set(i, j, k);
        time = selected_calendar.getTime();
        repaint();
        postDateChangedEvent();
    }

    public Calendar getValue()
    {
        return selected_calendar;
    }

    public void setValue(Calendar calendar)
    {
        if(calendar == null)
        {
            throw new IllegalArgumentException("invalid value");
        } else
        {
            selected_calendar = calendar;
            repaint();
            postDateChangedEvent();
            return;
        }
    }

    public int getCellHighlightThickness()
    {
        return cell_highlight_thickness;
    }

    public void setCellHighlightThickness(int i)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException("invalid value for highlight thickness");
        } else
        {
            cell_highlight_thickness = i;
            calculateCellSize();
            setSize(getPreferredSize());
            repaint();
            return;
        }
    }

    public void addSpecialDay(JCDateMonthYear jcdatemonthyear)
    {
        special_dates.addUnique(jcdatemonthyear);
        repaint();
    }

    public void addSpecialDay(JCDayOfWeek jcdayofweek)
    {
        special_dates.addUnique(jcdayofweek);
        repaint();
    }

    public void addSpecialDay(JCMonthDayOfMonth jcmonthdayofmonth)
    {
        special_dates.addUnique(jcmonthdayofmonth);
        repaint();
    }

    public void addSpecialDay(JCMonthWeekDayOfWeek jcmonthweekdayofweek)
    {
        special_dates.addUnique(jcmonthweekdayofweek);
        repaint();
    }

    public void removeSpecialDay(JCDateMonthYear jcdatemonthyear)
    {
        for(int i = 0; i < special_dates.size(); i++)
        {
            Object obj = special_dates.elementAt(i);
            if(obj instanceof JCDateMonthYear)
            {
                JCDateMonthYear jcdatemonthyear1 = (JCDateMonthYear)obj;
                if(jcdatemonthyear1.year == jcdatemonthyear.year && jcdatemonthyear1.month == jcdatemonthyear.month && jcdatemonthyear1.date == jcdatemonthyear.date)
                    special_dates.removeElementAt(i);
            }
        }

        repaint();
    }

    public void removeSpecialDay(JCDayOfWeek jcdayofweek)
    {
        for(int i = 0; i < special_dates.size(); i++)
        {
            Object obj = special_dates.elementAt(i);
            if(obj instanceof JCDayOfWeek)
            {
                JCDayOfWeek jcdayofweek1 = (JCDayOfWeek)obj;
                if(jcdayofweek1.day_of_week == jcdayofweek.day_of_week)
                    special_dates.removeElementAt(i);
            }
        }

        repaint();
    }

    public void removeSpecialDay(JCMonthDayOfMonth jcmonthdayofmonth)
    {
        for(int i = 0; i < special_dates.size(); i++)
        {
            Object obj = special_dates.elementAt(i);
            if(obj instanceof JCMonthDayOfMonth)
            {
                JCMonthDayOfMonth jcmonthdayofmonth1 = (JCMonthDayOfMonth)obj;
                if(jcmonthdayofmonth1.month == jcmonthdayofmonth.month && jcmonthdayofmonth1.day_of_month == jcmonthdayofmonth.day_of_month)
                    special_dates.removeElementAt(i);
            }
        }

        repaint();
    }

    public void removeSpecialDay(JCMonthWeekDayOfWeek jcmonthweekdayofweek)
    {
        for(int i = 0; i < special_dates.size(); i++)
        {
            Object obj = special_dates.elementAt(i);
            if(obj instanceof JCMonthWeekDayOfWeek)
            {
                JCMonthWeekDayOfWeek jcmonthweekdayofweek1 = (JCMonthWeekDayOfWeek)obj;
                if(jcmonthweekdayofweek1.month == jcmonthweekdayofweek.month && jcmonthweekdayofweek1.week == jcmonthweekdayofweek.week && jcmonthweekdayofweek1.day_of_week == jcmonthweekdayofweek.day_of_week)
                    special_dates.removeElementAt(i);
            }
        }

        repaint();
    }

    protected int isInSpecialDayList(int i, int j, int k, int l)
    {
        for(int k1 = 0; k1 < special_dates.size(); k1++)
        {
            Object obj = special_dates.elementAt(k1);
            if(obj instanceof JCDateMonthYear)
            {
                JCDateMonthYear jcdatemonthyear = (JCDateMonthYear)obj;
                if(jcdatemonthyear.year == i && jcdatemonthyear.month == j && jcdatemonthyear.date == k)
                    return k1;
            } else
            if(obj instanceof JCDayOfWeek)
            {
                JCDayOfWeek jcdayofweek = (JCDayOfWeek)obj;
                int i1 = dayOfWeek(k, j, i);
                if(jcdayofweek.day_of_week == i1)
                    return k1;
            } else
            if(obj instanceof JCMonthDayOfMonth)
            {
                JCMonthDayOfMonth jcmonthdayofmonth = (JCMonthDayOfMonth)obj;
                if(jcmonthdayofmonth.month == j && jcmonthdayofmonth.day_of_month == k)
                    return k1;
            } else
            if(obj instanceof JCMonthWeekDayOfWeek)
            {
                JCMonthWeekDayOfWeek jcmonthweekdayofweek = (JCMonthWeekDayOfWeek)obj;
                int j1 = dayOfWeek(k, j, i);
                if(jcmonthweekdayofweek.month == j && jcmonthweekdayofweek.day_of_week == j1 && jcmonthweekdayofweek.week == l)
                    return k1;
            }
        }

        return -1;
    }

    private String days[];
    static final int days_in_month[] = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 
        30, 31
    };
    protected JCVector listeners;
    private int num_of_days;
    private int num_rows;
    private int startx;
    private int starty;
    private Point start_point;
    private Point end_point;
    private Point selected_point;
    private Date time;
    protected JCVector special_dates;
    protected boolean selected;
    protected Color selected_foreground;
    protected Color selected_background;
    protected Color special_day_background;
    protected Color special_day_foreground;
    protected Color header_foreground;
    protected Color header_background;
    protected Font header_font;
    protected int cell_margin;
    protected int cell_size;
    protected Calendar selected_calendar;
    protected int first_day_of_week;
    protected int cell_highlight_thickness;
    protected int header_gap;
    protected int num_header_chars;

}
