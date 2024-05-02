package All_Unlimited.Allgemein.Eingabe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.GPS;
import All_Unlimited.Allgemein.Anzeige.Zahl1;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class GPS_Eingabe extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Global g;
	private Zahl zLat;
	private Zahl zLng;
	private Text Edt;
	private SpinBox SbMap;
	public static boolean bExtern=false;
//	private int iMap=17;
	
	public GPS_Eingabe(Global glob)
	{
		g=glob;
		Build(true);
	}
	
	public GPS_Eingabe(Global glob,boolean bEdit)
	{
		g=glob;
		Build(bEdit);
	}
	
	private void Build(boolean bEdit)
	{
		setLayout(new BorderLayout());
		zLat=new Zahl(47.857,5,true);
		zLng=new Zahl(16.328,5,true);
		zLat.setToolTipText(g.getShow("lat"));
		zLng.setToolTipText(g.getShow("lng"));
		SbMap=new SpinBox(17,1,19,1,Color.WHITE);
		SbMap.setFont(Transact.fontStandard);
		int iFF=g.getFontFaktor();
        int iH=17*iFF/100;
        SbMap.setPreferredSize(new Dimension(35,iH));
		Edt=new Text("at_Home",50,Text.KENNUNG);
        JPanel PnlC=new JPanel(new GridLayout(0,1));
		 JPanel PnlEin=new JPanel(new GridLayout(1,0,2,2));
		  PnlEin.setPreferredSize(new java.awt.Dimension(iFF*(Static.bND ? 175:150)/100,iH));
		  PnlEin.add(zLat);
		  PnlEin.add(zLng);
		 if (bEdit)
			 PnlC.add(PnlEin);
		 JPanel Pnl2=new JPanel(new BorderLayout(2,2));
		 Pnl2.setPreferredSize(new java.awt.Dimension(iFF*(Static.bND ? 175:150)/100,iH));
		  Pnl2.add("West",g.getLabel("Name"));
		  Pnl2.add("Center",Edt);
		  if (bEdit)
		    Pnl2.add("East",SbMap);
		 PnlC.add(Pnl2);
//		JScrollPane SP=new JScrollPane(PnlC);
//		SP.setVerticalScrollBarPolicy(SP.VERTICAL_SCROLLBAR_ALWAYS);
//		SP.getVerticalScrollBar().setUnitIncrement(iH);
//		SP.getVerticalScrollBar().setBlockIncrement(iH);
//		SP.setHorizontalScrollBarPolicy(SP.HORIZONTAL_SCROLLBAR_NEVER);		 
//		SP.setPreferredSize(new java.awt.Dimension(iFF*((Static.bND ? 175:150)+20)/100,iH+1));
		add("Center",PnlC);//SP);
		if (Static.bStern)
		{
		    JButton BtnOpenURL = g.getButton("*");
		    BtnOpenURL.setFocusable(false);
		    BtnOpenURL.setBorder(null);
		    add("East",BtnOpenURL);
		    BtnOpenURL.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ev) {
	              OpenURL();
	            }
	          });
		}
		
	}
	
	public double getLat()
	{
		return zLat.doubleValue();
	}
	
	public double getLng()
	{
		return zLng.doubleValue();
	}
	
	public String getName()
	{
		return Edt.getText();
	}
	
	public String toString()
	{
		return isNull() ? "":Static.Leer(Edt.getText()) || Version.Test() && g.Def() ? new Zahl1(zLat.floatValue(),"0.0000")+"/"+new Zahl1(zLng.floatValue(),"0.0000"):Edt.getText();
	}
	
	public int getMap()
	{
		return SbMap.getIntValue();
	}
	
	public void set(double dLat,double dLng, String sName,int iMap)
	{
		zLat.setValue(dLat);
		zLng.setValue(dLng);
		Edt.setText(sName);
		SbMap.setIntValue(iMap==0 ? 17:iMap);
	}
	
	public void set(GPS gps)
	{
		if (gps==null || gps.isNull())
		{
			setZero();
		}
		else
		{
			zLat.setValue(gps.getLat());
			zLng.setValue(gps.getLng());
			Edt.setText(gps.getName());
			SbMap.setIntValue(gps.getMap());
		}
	}
	
	public void setZero()
	{
		zLat.setValue(0.0);
		zLng.setValue(0.0);
		Edt.setText("");
		SbMap.setIntValue(17);
	}
	
	public void OpenURL()
	{
		if (isNull())
			return;
	  String sK=zLat.OriValue()+"/"+zLng.OriValue();
	  String s="https://www.openstreetmap.org/#map="+SbMap.getIntValue()+"/"+sK;
//	  g.fixtestError("URL="+s);
	  if (bExtern)
		  Static.OpenURL(s);
	  else
	  {
		  JFrame f=new JFrame("GPS "+toString()+" ("+sK+")");//s);
		  f.setSize(600, 400);
//		  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  JMapViewer mapViewer = new JMapViewer();
	      f.add(mapViewer);
	      Coordinate coordinate = new Coordinate(zLat.OriValue(), zLng.OriValue());
	        MapMarkerDot marker = new MapMarkerDot(coordinate);

	        // Füge den Marker zur Karte hinzu
	        mapViewer.addMapMarker(marker);

	        // Zentriere die Karte auf den Marker
	        mapViewer.setDisplayPosition(coordinate, SbMap.getIntValue());
		  f.setVisible(true);
		  Static.centerComponent(f, this);
	  }
	}
	
	public boolean Modified()
	{
		boolean b= zLat.Modified() || zLng.Modified() || Edt.Modified() || SbMap.Modified();
		// if (b)
		// 	g.fixInfo("!!! GPS-Modified:"+(Edt.Modified() ? " Name="+Edt.getText()+"<-"+Edt.getOld():SbMap.Modified() ? " Map="+SbMap.getIntValue()+"<-"+SbMap.getOld():
		// 	zLat.Modified() ? " Lat="+zLat.getDouble()+"<-"+zLat.getOld():zLng.Modified() ? " Lng="+zLng.getDouble()+"<-"+zLng.getOld():"???"));
		return b;
	}
	
	public boolean isNull()
	{
		return zLat.doubleValue()==0.0 && zLng.doubleValue()==0.0;
	}
	
	public void Reset()
    {
		zLat.setAktValue(zLat.getDouble());
		zLng.setAktValue(zLng.getDouble());
		Edt.setAktText(Edt.getText());
		SbMap.setIntValue(SbMap.getIntValue());
    }
	
	public void Reset2()
    {
		zLat.Reset2();
		zLng.Reset2();
		Edt.Reset2();
		SbMap.Reset2();
    }
	
	public void setEditable(boolean b)
	{
		zLat.setEditable(b);
		zLng.setEditable(b);
		SbMap.setEditable(b);
		Edt.setEditable(b);
	}

}
