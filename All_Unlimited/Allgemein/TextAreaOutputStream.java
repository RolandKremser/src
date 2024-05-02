package All_Unlimited.Allgemein;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

import All_Unlimited.Grunddefinition.JavaKonsole;

public class TextAreaOutputStream extends OutputStream
{

// *************************************************************************************************
// INSTANCE MEMBERS
// *************************************************************************************************

private byte[]                          oneByte;                                                    // array for write(int val);
private Appender                        appender;                                                   // most recent action
private String sV=null;

//public TextAreaOutputStream(JTextArea txtara) {
//    this(txtara,10000);
//    }

public TextAreaOutputStream(JTextArea txtara, int maxlin,boolean bStart) {
    if(maxlin<1) { throw new IllegalArgumentException("TextAreaOutputStream maximum lines must be positive (value="+maxlin+")"); }
    oneByte=new byte[1];
    appender=new Appender(txtara,maxlin,bStart);

    }

/** Clear the current console text area. */
public synchronized void clear() {
    if(appender!=null) { appender.clear(); }
    }

public synchronized void close() {
    appender=null;
    }

public synchronized void flush() {
    }

public synchronized void write(int val) {
    oneByte[0]=(byte)val;
    write(oneByte,0,1);
    }

public synchronized void write(byte[] ba) {
    write(ba,0,ba.length);
    }

public synchronized void write(byte[] ba,int str,int len) {
	String s=Save.now();
	if (sV==null)
	{
		sV=s;
		s+="\n ";
		//s=s.substring(11);
	}
	else if (s.substring(11,20).equals(sV.substring(11,20)))
		s=" ";
	else
	{
		sV=s;
		s=(s.substring(0,10).equals(sV.substring(0,10)) ? s.substring(11,23):s)+"\n ";
	}
    if(appender!=null) { appender.append(s+bytesToString(ba,str,len)); }
    }

//@edu.umd.cs.findbugs.annotations.SuppressWarnings("DM_DEFAULT_ENCODING")
static private String bytesToString(byte[] ba, int str, int len) {
    try { return new String(ba,str,len,/*"Cp1252"*/"UTF-8"); } catch(UnsupportedEncodingException thr) { return new String(ba,str,len); } // all JVMs are required to support UTF-8
    }

// *************************************************************************************************
// STATIC MEMBERS
// *************************************************************************************************

    static class Appender
    implements Runnable
    {
    private final JTextArea             textArea;
    private final int                   maxLines;                                                   // maximum lines allowed in text area
    private final LinkedList<Integer>   lengths;                                                    // length of lines within text area
    private final List<String>          values;                                                     // values waiting to be appended

    private int                         curLength;                                                  // length of current line
    private boolean                     clear;
    private boolean                     queue;
    private boolean bStart=false;

    Appender(JTextArea txtara, int maxlin,boolean rbStart) {
        textArea =txtara;
        maxLines =maxlin;
        bStart=rbStart;
        lengths  =new LinkedList<Integer>();
        values   =new ArrayList<String>();

        curLength=0;
        clear    =false;
        queue    =true;
        }

    synchronized void append(String val) {
        values.add(val);
        if(queue) { queue=false; EventQueue.invokeLater(this); }
        }

    synchronized void clear() {
        clear=true;
        curLength=0;
        lengths.clear();
        values.clear();
        if(queue) { queue=false; EventQueue.invokeLater(this); }
        }

    // MUST BE THE ONLY METHOD THAT TOUCHES textArea!
    public synchronized void run() {
        if(clear) { textArea.setText(""); }
        for(String val: values) {
            curLength+=val.length();
            if(val.endsWith(EOL1) || val.endsWith(EOL2)) {
                if(lengths.size()>=maxLines) { textArea.replaceRange("",0,lengths.removeFirst()); }
                lengths.addLast(curLength);
                curLength=0;
                }
            if (bStart && Static.bAutoKonsole)
            	JavaKonsole.get(false);
            textArea.append(val);
            }
        values.clear();
        clear =false;
        queue =true;
        }

    static private final String         EOL1="\n";
    static private final String         EOL2=System.getProperty("line.separator",EOL1);
    }

}