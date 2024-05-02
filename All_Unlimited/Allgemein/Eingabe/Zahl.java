/*
    All_Unlimited-Allgemein-Eingabe-Zahl.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Sort;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class Zahl extends JTextField
{
    /**
	 *
	 */
	private static final long serialVersionUID = 6041510859227973886L;
	// add your data members here
	public Zahl(double d)
	{
		//super(new Double(d).toString().replace('.',','));
		setValue(d);
		initZahl();
		Count.add(Count.Zahl);
	}

	public Zahl(double d,int riStellen)
	{
		this(d,riStellen,false);
	}

	public Zahl(double d,int riStellen,boolean rbOri)
	{
		//super();
		bOri=rbOri;
	    for(int iz=0;iz<riStellen;iz++)
		    str+='0';
		df=new DecimalFormat(str);
		//setText(df.format(d));
		iStellen=riStellen;
		setValue(d);
		initZahl();
		Count.add(Count.Zahl);
	}

	public Zahl(Object Obj,int riStellen)
	{
		//super();
                /*
		if(riStellen>0)
		{*/
			for(int iz=0;iz<riStellen;iz++)
				str+='0';
			df=new DecimalFormat(str);
			//setText(df.format(new Double(s).doubleValue()));
			iStellen=riStellen;
			setValue(Obj);
			initZahl();
		/*}
		else if(Obj instanceof Integer)
			super(((Integer)Obj).intValue());
		else if(Obj instanceof Double)
			super(((Double)Obj).intValue());
		else
			Static.printError("Falscher Datentyp!!!");*/

		Count.add(Count.Zahl);
	}

	public Zahl(String s,int riStellen)
	{
		this(s==null || s.equals("")?null:new Double(s),riStellen);
	}

	public Zahl(int i)
	{
		//super(new Integer(i).toString());
		iStellen=0;
		setValue(i);
		initZahl();
		Count.add(Count.Zahl);
	}
	
	public Zahl clone()
	{
		return new Zahl(doubleValue(),iStellen);
	}

	public void finalize()
	{
		Count.sub(Count.Zahl);
	}

	public void initZahl()
	{
          //setPreferredSize(new java.awt.Dimension(80,20));
		  setFont(Static.fontStandard);
          setColumns(7);
          setHorizontalAlignment(JTextField.RIGHT);
          if (Static.bND)
             setBorder(new MatteBorder(0, 0, 1, 0, Static.ColLinie));
          else if (!Static.bRahmen)
             setBorder(new javax.swing.border.EmptyBorder(1,1,1,1));
		addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent ev)
			{
                          //System.out.println("Zahl.keyPressed");
			}
			public void keyTyped(KeyEvent ev)
			{
                          if (!isEditable())
                          {
                            //System.out.println("Eingabe gesperrt");
                            ev.consume();
                            return;
                          }
                          bIrgendwas=false;
                          char ch = ev.getKeyChar();

				//System.err.println("Zahl.keyTyped: "+(int)ch);
				/*if(ch==10 || ch==13);
				{
					setSelectionStart(getText().length());
					setSelectionEnd(getText().length());
				}*/
				//System.err.println("ch="+((int)ch));
				String sHilfe=getText();
				if(Character.isDigit(ch) && getSelectedText()!=null)
				{
					if(getSelectionEnd()==getText().length())
					{
						//System.out.println("se:"+getSelectionEnd()+"te:"+getText().length());
						int iPos=getSelectionStart();
						sHilfe=getText().substring(0,getSelectionStart())+getText().substring(getSelectionEnd());
						setText(sHilfe);
						setCaretPosition(iPos);
					}
					else
						sHilfe=getText();
				}

				int iKomma = sHilfe.indexOf(",");

				if(ch==','&&iStellen==0) // Bei STELLE=0 wird KEIN BEISTRICH gestattet
				    ev.consume();
				else if(iKomma!=-1 && ch==',') // Wurde schon EIN BEISTRICH eingegeben, so darf KEIN WEITERER eingegeben werden
				    ev.consume();
				else if (!Character.isDigit(ch) /*&& !Character.isIdentifierIgnorable(ch)*/)  //Ob KEINE ZAHL eingegeben wurde
				{
                                  //System.err.println("keine Zahl:"+sHilfe+"/"+sOldCheck);
					if(ch=='-')
					{

						if((getCaretPosition()>0||!sHilfe.equals(""))&&getSelectionStart()!=0)//&&getText().equals(""))
							ev.consume();


					}
					else if(ch==','&&sHilfe.equals(""))
						setText("0");
					else if(ch!=',' && ch!=8 && ch!=22 || ch==8 && (sOldCheck.startsWith("-") && sOldCheck.length()>1 && getCaretPosition()==0
                                            || sOldCheck.length()>getCaretPosition()+1 && sOldCheck.charAt(getCaretPosition())==','))
					{
                                          //System.err.println("ch="+(ch+0));
					    ev.consume();
                                            if (ch==127)
                                              sOldCheck="";
                                            bIrgendwas = true;
                                            //return;
					}

				}
			    else if(ch!=8 && iStellen!=-1 && iKomma!=-1 && iKomma<=sHilfe.length()-iStellen-1 && getCaretPosition()>iKomma)
			    {
			    	int iC=getCaretPosition();
//			    	System.err.println("Zahl:"+sHilfe+"/"+getText()+"/ Komma="+iKomma+", Car="+iC+", Len="+getText().length());
			    	if (iC>iKomma  && iC<getText().length())
			    	{
			    		sHilfe=sHilfe.substring(0,iC)+ch+sHilfe.substring(iC+1);
			    		setText(sHilfe);
						setCaretPosition(iC+1);
//			    	    System.err.println("-> Hilfe="+sHilfe);
			    	}
				    ev.consume();
			    }
				else if(ch=='0'&&sHilfe.equals(""))
					ev.consume();
				else if(iMax!=null && !sHilfe.startsWith("-") && iKomma==-1 && doubleValue()*10+ch-'0'>iMax.doubleValue())
					ev.consume();
                            //System.err.println("setzte "+ sOldCheck+ " auf "+ sHilfe);
				//sOldCheck = sHilfe;
			}
			public void keyReleased(KeyEvent ev)
			{
                          //System.out.println("Zahl.keyReleased");
                          //sOldCheck=getText();
                          	if(bIrgendwas)
				//if(iMax!=null && doubleValue()>iMax.doubleValue())
				  setText(sOldCheck);
                                else
                                  sOldCheck=getText();
                                CheckColor();
                                //System.err.println("keyReleased:"+sOldCheck);
			}
		});
		addFocusListener(
			new FocusListener(){
				public void focusGained(FocusEvent fe)
				{
					//setFocus();
                                        //System.out.println("Zahl.focusGained");
                                        bIrgendwas=false;
                                        selectAll();
                                        //setCaretPosition(0);
                                        /*setSelectionStart(0);
                                        setSelectionEnd(getText().length());*/
				}
				public void focusLost(FocusEvent fe)
				{
                                  //System.out.println("Zahl.focusLost");
                                }
			}
			);
	}

        private void CheckColor()
        {
          setBackground(Static.ColEdt(Modified(),isEditable()));
        }

        /*private void setFocus()
        {
          //System.out.println("Zahl.setFocus");
          setSelectionStart(0);
          setSelectionEnd(getText().length());
          //super.requestFocus();
        }*/
/*
	public boolean isValid()
	{
		int iAnzKomma=0;
		char[] cZahl = getText().trim().toCharArray();
		//boolean bFehler = cZahl[0]==',';
		boolean bFehler = false;

		for(int i=0;!bFehler && i<cZahl.length;i++)
		{
			iAnzKomma+=cZahl[i]==',' ? 1:0;
			bFehler = iAnzKomma>1 || !(Character.isDigit(cZahl[i]) || cZahl[i]==',');
		}

		return(!bFehler);
	}*/

	/*public void setStellen(int riStellen)
	{
		iStellen=riStellen;
		str="0";
		for(int iz=0;iz<iStellen;iz++)
		    str+='0';
		df=new DecimalFormat(str);
		setText(df.format(new Double(getText().replace(',','.')).doubleValue()));
		//selectAll();
	}*/

	public void setAktValue(Object ObjValue)
	{
		if(ObjValue==null || ObjValue instanceof Double)
			dOld = (Double)ObjValue;
		else if(ObjValue instanceof Integer)
			dOld = new Double(((Integer)ObjValue).intValue());

		sOldCheck=getText();
		bIrgendwas=false;
	}

	public void setValue(double dValue)
	{
		//boolean bMax=iMax!=null && iMax>0 && dValue>iMax;
		bMod=iMax!=null && iMax>0 && dValue>iMax;
		if (bMod)
		{
			//dOld = new Double(dValue);
			dValue=iMax;
		}
		if(iStellen==-1)
			setText(new Double(dValue).toString().replace('.',','));
		else if(iStellen==0)
		    setText(new Integer((int)Math.round(dValue)).toString());
		else
			setText(df.format(dValue));
                /*setSelectionStart(0);
                setSelectionEnd(getText().length());
                System.out.println("Zahl.setValue->"+getText());*/
		//if (!bMax)
		if (bOri)
		{
			dOld = doubleValue();
			dOri = dValue;
		}
		else
			dOld = new Double(dValue);
		sOldCheck=getText();
		bIrgendwas=false;
        CheckColor();
        //System.out.println("Zahl.setValue:"+getText()+"/"+dValue+", old="+dOld);
	}

	public void setValue(int iValue)
	{
		iStellen=0;
		setValue((double)iValue);
		//setText(new Integer(iValue).toString());
		//dOld = new Double(iValue);
		//selectAll();
	}

	/*public void setValue(float fValue)
	{
		if (iMax>0 && fValue>iMax)
			fValue=iMax;
		if(iStellen!=-1)
		    setText(df.format(new Double(fValue).doubleValue()));
		else
		    setText(new Float(fValue).toString().replace('.',','));
		dOld = new Double(fValue);

		sOldCheck=getText();
		bIrgendwas=false;
	}*/

	public void setValue(Object Obj)
	{
		if(Obj==null)
		{
			setText("");
			dOld =null;
		}
		else if(Obj instanceof Double)
		{/*
			if(iStellen!=-1)
				setText(df.format(((Double)Obj).doubleValue()));
			else
				setText(((Double)Obj).toString().replace('.',','));*/
		if(iStellen==-1)
			setText(((Double)Obj).toString().replace('.',','));
		else if(iStellen==0)
		    setText(new Integer(((Double)Obj).intValue()).toString());
		else
			setText(df.format(((Double)Obj).doubleValue()));

			dOld = (Double)Obj;
		}
		else if(Obj instanceof Integer)
		{
			setText(((Integer)Obj).toString());
			dOld = new Double(((Integer)Obj).intValue());
		}
		else
			Static.printError("Zahl.setValue(): Falscher Datentyp!!!");

		sOldCheck=getText();
		bIrgendwas=false;
                CheckColor();
	}

	public double doubleValue(int iNachkomma)
	{
		getD();
		double dHoch = Math.pow(10,iNachkomma);
		return(d!=null?Math.rint(d.doubleValue()*dHoch)/dHoch:0);
	}

	public double doubleValue()
	{
		getD();
		return(d!=null?d.doubleValue():0);
	}

	public double OriValue()
	{
		return Modified() ? doubleValue():dOri;
	}

	public int intValue()
	{
		getD();
		return(d!=null?d.intValue():0);
	}

	public float floatValue(int iNachkomma)
	{
		getD();
		double dHoch = Math.pow(10,iNachkomma);
		return(d!=null?new Double(Math.rint(d.doubleValue()*dHoch)/dHoch).floatValue():0);
	}

	public float floatValue()
	{
		getD();
		return(d!=null?d.floatValue():0);
	}

	public long longValue()
	{
		getD();
		return(d!=null?d.longValue():0);
	}

	public short shortValue()
	{
		getD();
		return(d!=null?d.shortValue():0);
	}

	private void getD()
	{
		String s = getText().trim();

		if(s.equals("") || s.equals("-"))
		{
			d=null;
		}
		else
		{
			s=s.replace(',','.');
			try
			{
				d=new Double(s);
				if (iMax!=null && iMax>0 && d.doubleValue()>iMax)
					d=new Double(iMax);
			}
			catch(NumberFormatException ex)
			{
				//Static.printError("Zahl.getD(): NumberFormatException - "+ex);
                                System.err.println("keine Zahl:"+s);
				d=new Double(0);
                                setValue(0);
			}
		}
                //System.err.println("d="+d);
	}

	public Double getDouble()
	{
		getD();
		return d;
	}

	public Integer getInteger()
	{
		getD();
		return(d!=null?new Integer(d.intValue()):null);
	}

	public boolean isNull()
	{
		getD();
		return d==null || d.doubleValue()==0.0;
	}

	public boolean Modified()
	{
		getD();
		//return dOld==null && d==null?false:dOld==null || d==null?true:dOld.doubleValue()!=d.doubleValue();
		boolean b= bMod || d!=dOld &&!(d!=null && dOld!=null && Static.Round6(Sort.getf(d))==Static.Round6(Sort.getf(dOld)));
		//System.out.println("Zahl.Modified="+b+":"+getText()+"/"+d+", old="+dOld+"/ bMod="+bMod);
		return b;
	}

	public double getOld()
	{
		return dOld;
	}

        public void setAktValue(Double rd)
        {
          dOld=rd;
          CheckColor();
        }

	public void setMax(Integer riMax)
	{
		iMax = riMax;
	}

        public void Reset2()
        {
          setValue(dOld);
        }


// add your data members here
private Double d;
private int iStellen=-1;
private String str=new String("0.");
private DecimalFormat df;
private Double dOld;
private double dOri=0;
private boolean bOri=false;
private Integer iMax = null;
private boolean bMod=false;
private String sOldCheck="";
private boolean bIrgendwas = false;
}

