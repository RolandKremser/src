/*
    All_Unlimited-Allgemein-Eingabe-Text.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.Global;
//import java.awt.Color;
import All_Unlimited.Allgemein.Static;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;


// add your custom import statements here

public class Text extends JTextField
{
    /**
	 *
	 */
        public static final int KENNUNG=1;
        public static final int FILE=2;
        public static final int SONDER=3;
        public static final int ZAHL=4;
        public static final int EINGABE=5;  // für Modell-Eingabe-Wert
        public static final int KENNUNG2=6; // für Benutzer

	private static final long serialVersionUID = 2777544026817089479L;
	private int iLaenge;
        private int iTyp;
        /*public Text()
        {
          this("",30,false);
        }*/

	public Text(String rsText,int riLaenge)
	{
		this(rsText,riLaenge,0);
	}

	public Text(String rsText, int riLaenge, int riTyp)
	{
		super(rsText);
		sOldText = rsText;
		setColumns(Static.bND ? 23:riLaenge>200?100:riLaenge>100?50:riLaenge>36?25:riLaenge<4?3:riLaenge*2/3+1);
		iLaenge=riLaenge;
                iTyp=riTyp;
                if (Static.bND)
                  setBorder(new MatteBorder(0, 0, 1, 0, Static.ColLinie));
                else if (!Static.bRahmen)
                  setBorder(new javax.swing.border.EmptyBorder(1,1,1,1));
                setFont(Static.fontStandard);
		initText();
		Count.add(Count.Text);
	}

	public void finalize()
	{
		Count.sub(Count.Text);
	}

        private void CheckColor()
        {
          setBackground(Static.ColEdt(Modified(),isEditable()));
        }

	private void initText()
	{
		addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
                          String s=KeyEvent.getKeyText(e.getKeyCode());
                          //System.out.println("keyPressed:"+s+"/"+e.getModifiers());
                          if (e.getModifiers()==2)
                          {
                            if (s.equals("Z"))
                            {
                              Reset2();
                              e.consume();
                            }
                          }
                          if (e.getModifiers()==4)
                           {
                             if (s.equals("Y") || s.equals("Z"))
                               Reset2();
                             else if (s.equals("X"))
                               cut();
                             else if (s.equals("C"))
                               copy();
                             else if (s.equals("V"))
                               paste();
                             else if (s.equals("A"))
                               selectAll();
                             e.consume();
                           }
                        }
			public void keyReleased(KeyEvent e)
			{
                          CheckColor();
			}
			public void keyTyped(KeyEvent e)
			{
				char cKey=e.getKeyChar();
                                //System.out.println(cKey+":"+Character.getType(cKey));
                                boolean bUmlaut=cKey=='ö'||cKey=='Ö'||cKey=='ä'||cKey=='Ä'||cKey=='ü'||cKey=='Ü'||cKey=='ß'||cKey=='ö'||cKey=='µ';
				int iText=getText().length();
				if(iTyp!=EINGABE && cKey=='\''|| (cKey!=8 && iText>=iLaenge) || iTyp==FILE && (cKey=='<' || cKey=='>' || cKey=='"' || cKey==':' && iText!=1)
                                   || (iTyp==KENNUNG || iTyp==KENNUNG2) && !isKennung2(""+cKey,iTyp==KENNUNG) //cKey!='-'&&cKey!=' '&&cKey!='.'&&cKey!=':'&&(!Character.isJavaIdentifierPart(cKey)||bUmlaut))
                                   || (iTyp==SONDER && cKey!='_' && (!Character.isLetterOrDigit(cKey) || bUmlaut)) || (iTyp==ZAHL && !Character.isDigit(cKey)))
				   e.consume();

			}
		});
		
//		addActionListener(new java.awt.event.ActionListener() {
//		    public void actionPerformed(java.awt.event.ActionEvent e) {
//
////		        if (Integer.parseInt(textField.getText())<=0){
////		            JOptionPane.showMessageDialog(null,
////		                    "Error: Please enter number bigger than 0", "Error Message",
////		                    JOptionPane.ERROR_MESSAGE);
////		        }     
//		    	System.err.println("Text nun:"+getText());
//		    }
//		});
		
//		addInputMethodListener(new InputMethodListener() {
//
//			public void textValueChanged(TextEvent e) {
//				TextField f = (TextField)e.getSource();
//				if(f.getText().length() > 2) {
//					f.setText(f.getText().substring(0,2));
//					f.setCaretPosition(f.getText().length());
//				}
//			}
//		});
		
//		getDocument().addDocumentListener(new DocumentListener() {
//			  public void changedUpdate(DocumentEvent e) {
////				  System.err.println("changedUpdate: Text nun:"+getText());
//			  }
//			  public void removeUpdate(DocumentEvent e) {
//				  System.err.println("removeUpdate: Text nun:"+getText());
//			  }
//			  public void insertUpdate(DocumentEvent e) {
//				  System.err.println("insertUpdate: Text nun:"+getText());
//				  String s=getText();
////				  if (s.contains("'"))
////					  setText2(s.replaceAll("'", ""));
//				  int i=s.indexOf('\'');
//				  try
//				  {
//				    if (i>=0)
//					  e.getDocument().remove(i, 1);
//				  }
//				  catch(Exception e2) {};
//			  }
//		});
                /*addFocusListener(new FocusListener()
                {
                                public void focusGained(FocusEvent fe)
                                {
                                  //selectAll();
                                  //System.out.println("Cbo.focusGained");
                                }
                                public void focusLost(FocusEvent fe)
                                {
                                  //System.out.println("Cbo.focusLost");
                                }
                });*/

	}

	public void setText(String s)
	{
		sOldText = s;
		super.setText(s);
                //System.out.println("Offset="+getScrollOffset()+"/"+getHorizontalVisibility()+":"+s);
                CheckColor();
                /*int iMax=getHorizontalVisibility().getMaximum();
                int iEx=getHorizontalVisibility().getExtent();
                if (iMax>iEx)
                setScrollOffset(iMax-iEx);*/
                setCaretPosition(0);
                //scrollRectToVisible(new java.awt.Rectangle());
	}

        public void setText2(String s)
        {
          super.setText(s);
          CheckColor();
        }

	public void setAktText(String s)
	{
		sOldText = s==null ? getText():s;
                CheckColor();
	}

	public boolean isNull()
	{
		return getText().equals("");
	}

	public boolean Modified()
	{
		return sOldText==null ? !isNull():!sOldText.equals(getText());
	}

	public String getOld()
	{
		return sOldText;
	}

    public void setEnabled(boolean b)
    {
      super.setFocusable(b);
      //System.out.println("setEnabled:"+b);
      //setBackground(b?java.awt.Color.white:java.awt.Color.black);
    }

    public void Reset2()
    {
      setText(sOldText);
    }
    
    public String Fehler(boolean bZwingend)
    {
    	if (isNull())
    		return bZwingend ? "Leer":null;
    	String s=getText();
    	if (s.length()>iLaenge)
    		return "_zu_lang";
    	if ((iTyp==KENNUNG || iTyp==KENNUNG2) && !isKennung2(s,iTyp==KENNUNG))
    		return "Umlaut";
    	return null;
    }
    
    public static boolean isKennung(String s)
    {
    	return s.matches("[a-zA-Z0-9]");
    }
    
    public static boolean isKennung2(String s,boolean bLeer) {
    	int i=0;
        for (char c : s.toCharArray()) {
        	i++;
        	// 0 - 9
            if (c >= '0' && c <= '9')
                continue;
            
        	// a - z
            if (c >= 'a' && c <= 'z')
                continue;

            // A - Z
            if (c >= 'A' && c <= 'Z')
                continue;

            // Sonderzeichen
            if (c == '@' || c == '-' || c == '_' || c == '.')
                continue;
            
            // Leerzeichen nur bei normaler Kennung
            if (bLeer && (c == ':'/* || c == ' '*/))
            	continue;
            Global.fixInfoS("isKennung2: Ungültiges "+i+".Zeichen ["+c+"]");
            return false;
        }
        return true;
    }

	private String sOldText=Static.sLeer;
}

