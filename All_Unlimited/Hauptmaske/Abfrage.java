/*
    All_Unlimited-Hauptmaske-Abfrage.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.Color;
import java.awt.Image;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import All_Unlimited.Allgemein.AUVector;
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Favorit;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
// import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Anzeige.BewEig;
import All_Unlimited.Allgemein.Anzeige.Combo;
//import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Eingabe.Format;
import All_Unlimited.Grunddefinition.DefAbfrage2;
import jclass.bwt.JCOutlinerNodeStyle;

public class Abfrage extends java.lang.Object
{

  // add your data members here

	// Ganze Tabelle (Abfrage)
	public static final int cstKeinZeitraum=	0x0001;	// 00
	public static final int cstKeinStamm=		0x0002;	// 01
	public static final int cstSumme=		0x0004; // 02
	public static final int cstKeinRefresh=		0x0008; // 03
	public static final int cstNurModell=		0x0010; // 04
	public static final int cstExportierbar=	0x0020; // 05
	public static final int cstDruckbar=		0x0040; // 06
	public static final int cstMengen=		0x0080; // 07
	public static final int cstKeinNeu=		0x0100; // 08
	public static final int cstKeinSave=		0x0200; // 09
	public static final int cstNichtUpdaten=	0x0400; // 10
	public static final int cstSubrollen=	        0x0800; // 11
	public static final int cstAuswertung=		0x1000; // 12
	public static final int cstImportierbar=	0x2000; // 13
	public static final int cstVersionsup=		0x4000; // 14
	public static final int cstKeinDel=		0x8000; // 15
	public static final int cstAnhaengen=	    0x00010000; // 16
	public static final int cstKeinTitel=	    0x00020000; // 17
	public static final int cstKeinStt=	    0x00040000; // 18
	public static final int cstFirst  =	    0x00080000; // 19

	public static final int cstPlanung =	    0x00300000; // 20,21
	public static final int cstPlanungD =	    0x00100000; //
	public static final int cstPlanungF =	    0x00200000; //
	public static final int cstPlanungS =	    0x00300000; //

	public static final int cstKeinStammZeitraum=     0x400000;  // 22
	public static final int cstEntfernte=	        0x00800000;  // 23
	public static final int cstPersoenlich=	        0x01000000;  // 24
	public static final int cstStempelimport=       0x02000000;  // 25
	public static final int cstView=	            0x04000000;  // 26
	public static final int cstBewVoll=	            0x08000000;  // 27
	public static final int cstFilter=              0x10000000;  // 28
	public static final int cstZR_Wahl=	            0x20000000;  // 29 Zeitraum-Auswahl im web, war View2
	public static final int cstSynchron=	        0x40000000;  // 30
	public static final long cstChanges=	        0x80000000l; // 31
	public static final long cstVerteiler=         0x100000000l; // 32
	public static final long cstNoChange=          0x200000000l; // 33
	public static final long cstFeiertage=         0x400000000l; // 34
	public static final long cstFremdStamm=        0x800000000l; // 35
	public static final long cstDistinct=         0x1000000000l; // 36
	public static final long cstVerdichten=       0x2000000000l; // 37
	public static final long cstEinzeldaten=      0x4000000000l; // 38
	public static final long cstTTO=              0x8000000000l; // 39
	public static final long cstCompress=        0x10000000000l; // 40
	public static final long cstKeineGesamtsumme=0x20000000000l; // 41
	public static final long cstPriviligiert=    0x40000000000l; // 42
	public static final long cstVorletzteEbene=  0x80000000000l; // 43
	public static final long cst0Werte=         0x100000000000l; // 44
	public static final long cstLeer=           0x200000000000l; // 45
	public static final long cstKeinAustritt=   0x400000000000l; // 46
	public static final long cstStammstichtag=  0x800000000000l; // 47
	public static final long cstLDATE=         0x1000000000000l; // 48
	public static final long cstNoSort=        0x2000000000000l; // 49
	public static final long cstGleiche=       0x4000000000000l; // 50
	public static final long cstFremdJoker=    0x8000000000000l; // 51
	public static final long cstUmsortieren=  0x10000000000000l; // 52
	public static final long cstAusblenden=   0x20000000000000l; // 53
	public static final long cstNachSave =    0x40000000000000l; // 54
	public static final long cstWeiterfuehren=0x80000000000000l; // 55
	public static final long cstKeinTest=    0x100000000000000l; // 56 kein Test, davor: Land
	public static final long cstBerechtigung=0x200000000000000l; // 57
	public static final long cstHierarchie=  0x400000000000000l; // 58
	public static final long cstHS=          0x800000000000000l; // 59
	public static final long cstGruppe=     0x1000000000000000l; // 60
	public static final long cstCalc=       0x2000000000000000l; // 61
	//public static final long cstANR=        0x4000000000000000l; // 62
	//public static final long cstWiePeriode= 0x0000000000000000l; // noch nicht verwendet
	
	// Abfrage-bits2
	//public static final int cstAbfFX=         1; // 00 Abfrage für JavaFX
	public static final int cstRefreshNS=       2; // 01 Refresh im Web nach speichern; war cstResize: Tabellenspalten anpassen
	public static final int cstKeinBez=         4; // 02 keine Tabellen-Überschrift mit Abfrage-Bezeichnung
	public static final int cstNoSB=	          8; // 03 keine Stamm-Berechtigungseinschränkung, war cstKeinMulti: kein Multiselekt in der Tabelle
	public static final int cstAbfMulti=   0x0010; // 04 bei Web Mutliselect; war cstSelCol: Spalte einzeln anwählbar
	public static final int cstAbfAPI=     0x0020; // Abfrage für API verwendbar, war cstGa1S: Gruppe auf erste Spalte
	//public static final int cstErsteOffen=0x0040; // 06 erste Gruppe offen
	public static final int cstCompress2  =0x0080; // 07 Compress wie im Modell mit Art-Berücksichtigung (Sum/Last..)
//	public static final int cstNullWeg  =0x0100; // 08 zeigt keine Leeren Ebenen an, sondern gibt sie nach oben, entfernt am 4.7.2023 da nicht verwendet
	public static final int cstSubFom     =0x0200; // 09 Sub-Formular lt Zeile aufrufbar	
	public static final int cstSuche2	    =0x0400; // 10 Web-Suche aktivieren, war GruppenFilter aufrufbar für JavaFX 
	public static final int cstKeinMandant=0x0800; // 11 keine Mandanteneinschränkung
	public static final int cstTEdit      =0x1000; // 12 auf Template (WebServer) editierbar
	//public static final int cstAbfWeb   =0x2000; // 13 wird auf WebServer verwendet 
	public static final int cstAbfSystem  =0x4000; // 14 für Standardabfragen am WebServer
//	public static final int cstHochTitel  =0x8000; // 15 bei Spaltentitel immer nur 1 Zeichen pro Zeile, entfernt am 4.7.2023 da nicht verwendet
	//public static final int cstWebFom  =0x10000; // 16 Web-Formular, d.h. der Spalten zuordenbar
	public static final int cstDialogEdit=0x20000; // 17 bei Web ob als Dialog
	public static final int cstLokZR     =0x40000; // 18 Abfrage verwendet nur lokalen Zeitraum
	public static final int cstJVecAlle  =0x80000; // 19 alle, wenn JokerVec leer ist
	public static final int cstkeinSyncVec= 0x100000; // 20 setSyncStamm mit Vector nicht durchführen
	public static final int cstAbfJahr    = 0x200000; // 21 Abfrage wird vor Aufruf auf Jahr gestellt
	public static final int cstDelZw      = 0x400000; // 22 löscht Zeile wenn alle zwingenden Spalten keine Daten haben
	public static final int cstModellDlg  = 0x800000; // 23 steht für Modelldialog zur Verfügung
	public static final int cstQryAlle   = 0x1000000; // 24 alle bei Qry=0 nehmen
	public static final int cstForStatus = 0x2000000; // 25 Steht bei Status (für Aufgaben) zur Verfügung
	public static final int cstSbRefresh = 0x4000000; // 26 Stamm-Berechtigung vor Aufruf refreshen
	public static final int cstModFarbe  = 0x8000000; // 27 färben des Modellknopfs
	public static final int cstPersSperre=0x10000000; // 28 Abschluss-Sperre laut persönlich
	public static final int cstKeineME   =0x20000000; // 29 keine Einheit bei BewMass
	public static final int cstSQL2      =0x40000000; // 30 Abfrage aufgeteilt auf 2 SQL-Statements 

  // Einzelne Spalte:
	// Spaltenbits1
	public static final int cstGruppiert=		0x0001; // 00 erste
	public static final int cstSortDesc=		0x0002; // 01
    public static final int cstSpLeer=	        0x0004; // 02
	public static final int cstAnzeigen=		0x0008; // 03
	public static final int cstEditierbar=		0x0010; // 04
	public static final int cstPeriode=			0x0020; // 05
	public static final int cstPeriodensumme=	0x0040; // 06
	public static final int cstPositiv=			0x0080; // 07
	public static final int cstKeinAutoDate=	0x0100; // 08
	public static final int cstSperre=			0x0400; // 10
	public static final int cstHochkomma=		0x0800; // 11
	public static final int cstUnsichtbar=		0x1000; // 12

        public static final int cstNeu=		        0x2200; // 09,13
        public static final int cstLast=	        0x0000; //
        public static final int cstKeinAutoLast=	0x0200; //
        public static final int cstNimmSync=		0x2000; //
        public static final int cstLimit=			0x2200; //

	public static final int cstSteuern=		0x4000; // 14
	public static final int cstAutoInc=		0x8000; // 15

        //public static final int cstEindeutig=	    0x00010000; // 16
        //public static final int cstAlways=        0x00020000; // 17
        //public static final int cstNurModell=	    0x00040000; // 18
        //public static final int cstKeinAutoDate=  0x00080000; // 19
        //public static final int cstRound100=      0x00100000; // 20
        //public static final int cstDefault=       0x00200000; // 21

        public static final int cstShow=            0x00C00000; // 22,23
        public static final int cstCombo=           0x00000000; //
        public static final int cstRadio=           0x00400000; //
        public static final int cstButtons=         0x00800000; //
        public static final int cstCombo2=          0x00C00000; // Combobox mit Eingabe

        public static final int cstGueltig2=        0x01000000; // 24
        public static final int cstKeineEinheit=    0x02000000; // 25
        public static final int cstMobil=	    	0x04000000; // 26 war cstDialog
        public static final int cstGueltig=	    	0x08000000; // 27
        public static final int cstSpVerteiler=     0x10000000; // 28
        public static final int cstBild=	    	0x20000000; // 29
        public static final int cstSpRefresh=       0x40000000; // 30

      // Spaltenbits2
        public static final int cstStt=		     0x0001; // 00
        public static final int cstSpGMT=        0x0002; // 01
        public static final int cstSpNotSave=    0x0004; // 02
        public static final int cstKeineGSumme=  0x0008; // 03
        public static final int cstKeine1Summe=  0x0010; // 04
        public static final int cstSpSort=       0x0020; // 05
        public static final int cstKeinOhr=      0x0040; // 06
        public static final int cstImportBed=    0x0080; // 07
        public static final int cstKumuliert=    0x0100; // 08
        public static final int cstAbNeujahr=    0x0200; // 09
        public static final int cstFett=         0x0400; // 10 als Zwischensumme markieren
        public static final int cstKeineLeerzeilen=0x0800; // 11
        public static final int cstVorZR=        0x1000; // 12
        public static final int cstErgaenzen=    0x2000; // 13 den Filter zur Sortierung, Filterung und zum Ergänzen verwernden
        public static final int cstFett2=        0x4000; // 14 als Gesamtsumme markieren
        public static final int cstNurErste=     0x8000; // 15 nur 1. Zeile von jeder Art
        public static final int cstSpGleiche=   0x10000; // 16 alle mit gleichem Datum (nur in Kombination mit NurErste in anderer Spalte)
        public static final int cstStichtag=    0x20000; // 17 Stichtag zwingend
        public static final int cstAIC_Bez= 	0x40000; // 18 liefert AIC und wenn leer auch Bezeichnung darüber; war cstbeiStichtag: nur wenn in Zeitraum neuer Stichtag
        public static final int cstBuchZahl=    0x80000; // 19 nur Buchstaben und Zahlen eingebbar
        public static final int cstZiffern=    0x100000; // 20 nur Ziffern eingebbar
        public static final int cstKeinCG=     0x200000; // 21 keine berücksichtigung bei Compress // war cstUmbruch: Text wird umgebrochen
        public static final int cstSpKeinDel=  0x400000; // 22 Zeile darf nicht gelöscht werden sobald diese Spalte einen Wert hat
        public static final int cstSetSync=    0x800000; // 23 setzt Sync-Wert beim anwählen und nach Änderung
        public static final int cstBedZwang=  0x1000000; // 24 bedingtlaut sta_aic_stamm zwingend
        public static final int cstNegativRot=0x2000000; // 25 negative Zahlen rot färben
        public static final int cstMulti     =0x4000000; // 26 Multiselekt für Tabellenfüllen
        public static final int cstWeg       =0x8000000; // 27 Spalten ausgeblendet -> Zusatzspalte, wenn gesetzt dann wird diese Spalte nicht verwendet
        public static final int cstOrigEinh =0x10000000; // 28 Original-Einheit (Spalten-Mass ignorieren) 
        public static final int cstGanzesJahr=0x20000000;// 29 Ganzes Jahr unabhängig von Zeitraum verwenden
        public static final int cstKeinKnoten=0x40000000;// 30 kein Knoten bei Gruppiert erstellen // war cstDtJoker: Setzt den Datumsjoker laut Spalte
        
      // Spaltenbits3
        public static final int cstBool=         0x0001; // 00 boolean statt Zahl (war cstOverflow)
        public static final int cstUG=           0x0002; // 01W Untergruppierung war cstLost: Commit on Lost (Wert auch beim verlassen annehmen)
        public static final int cstFilterOK=     0x0004; // 02W Filter bei weg möglich (war cstEnter)
        public static final int cstSpKeinTitel=  0x0008; // 03 kein Spaltentitel
        public static final int cstHH_mm =       0x0010; // 04 Mass bzzw Dauer in HH:mm war cstNeben: Titel daneben (statt darüber)
        public static final int cstHtml=         0x0020; // 05 Memo mit Html-Tag-Eingabe
        public static final int cstInStunden=    0x0040; // 06 Umrechnung von s in h bei View
        public static final int cstBarcode=      0x0080; // 07 Barcode bei Druck (war cstZukunft: Eingabe in Zukunft)
        public static final int cstEE=           0x0F00; // 08-11 Eingabe-Art (Combobox, Radiobutton, ...)
        public static final int cstNoSubFom=     0x1000; // 12 SubFormular aufrufbar (war cstPrompt: Eingabe mit Prompt-Text)
        // public static final int cstSaveSoon=     0x2000; // 13 Rad s. unten // war cstSaveSoon:speichert bei verlassen des Feldes
        public static final int cstNoFav=        0x4000; // 14 kein Favorit war cstBnoEdt: kein Edititieren bei Inhalt       
        public static final int cstZusatz=		 0x8000; // 15W Zusatzspalte (wird bei Web zuerst ausgeblendet)
        public static final int cstKNZW=	    0x10000; // 16 keine negativen Zwischensummen (war cstGrX);
//        public static final int cstGrafik=      0x30000; // 20,21 Grafik-Art (x-Koordinate oder Wert)
//        public static final int cstGrX=         0x10000; //   für x-Koordinate/Bezeichnung
//        public static final int cstGrY=         0x20000; //   für y-Koordinate/Wert
        // public static final int cstDetail=       0x20000; // 17W nur separat in Detail-Pane darstellen, nicht in Tabelle/Outliner 
        public static final int cstDatenHolen=   0x22000; // 13,17 wie Daten geholt werden // war cstSaveSoon bzw Detail
          public static final int cstDefDaten=    0x0000; // Default Daten holen
          public static final int cstNurAicD=     0x2000; // nur AIC_Daten holen
          public static final int cstXxxDaten=   0x20000; // vorerst gesperrt
          public static final int cstDanachD=    0x22000; // Daten danach laut AIC füllen
        public static final int cstNotUser=      0x40000; // 18 nicht von Benutzer (in Benutzerabfrage) änderbar
        public static final int cstColorRow=     0x80000; // 19 Zeile laut der Farbe dieser Spalte färben
        public static final int cstPerSchnitt=  0x100000; // 20 Periodenschnitt (statt Summe)
        public static final int cstGruppierbar =0x200000; // 21 in VU-Tabelle auf gruppiert stellbar
        public static final int cstAuto	       =0x400000; // 22 Automatisch (nicht änderbar) z.B. Zeitpunkt für Stempelung     
        public static final int cstTabEin      =0x800000; // 23 Tabelleneingabe bei Web (statt Dialog)
        public static final int cstFullScr    =0x1000000; // 24 Full-Screen bei Web
        public static final int cstPdfViewer  =0x2000000; // 25 PDF-Viewer bei Web
        public static final int cstInPlace    =0x4000000; // 26 InPlaceEditierbar bei Web
        public static final int cstBanned     =0x8000000; // 27 nicht berechtigt, wird indirekt gesetzt
        public static final int cstSuche     =0x10000000; // 28 im Web-Teil kann danach gesucht werden
        public static final int cstFilter2   =0x20000000; // 29 andere Filterkomponente z.B.: Alle/Ja/Nein
        public static final int cstFilterInit=0x40000000; // 30 Filter vorinitialsisieren bei Web
        
      // Bedingung
        public static final int VBART=0x0003; // Art ob von, bis oder Dauer
        public static final int DAUER=0x0000; // Bedingung auf Dauer
        public static final int VON=  0x0001; // Bedingung auf von
        public static final int BIS=  0x0002; // Bedingung auf bis
        public static final int ZEIT= 0x0008; // Bedingung auf Zeit-Teil
        public static final int BART= 0x0014; // Art der Bedingung (Vorbedingung, Index, Normal)
        public static final int INDEX=0x0004; // war Vorbedingung, Indizierte Bedingung (direkt)
        public static final int VB=   0x0010; // Vorbedingung: Bedingung ob überhaupt verwenden
        public static final int OJ=   0x0020; // Bedingung ohne Jahr
        public static final int KEN=  0x0040; // prüfen auf Kennung
        
        public static final int BUSE= 0x006B; // alle außer Index und VB
        
	// sonstige Konstanten

	public static final int cstBegriff=  0;
	//public static final int cstSpalte=   1;
	public static final int cstAbfrage=  2;
	public static final int cstHS_Filter=3;
	public static final int cstHS_Spalte=4;
    public static final int cstExport=5;
    public static final int cstDefAbfrage=6;

	// Variablen
	public int iAbfrage=0;
	public int iBegriff=0;
	public long iBits=0;
    public long iBitsO=0;
    public long lBits2=0;
    public long lBits2O=0;
    public int iRolle=0;
	public int iStt=0;
	public int iBew=0;
	public int iTop=0;
	public int iModell=0;
    public int iModell2=0;
    public int iAbfF=-1;
    public int iAbfD=-1;
    public int iFomD=-1;
    public int iAbfE=-1;
    public int iWebStamm=-1;
    private int iBG=0;
    // public int iDtJ=-1;
    public boolean bTod=false;
    public boolean bWeb=false;
    public boolean bJeder=false;
    // public boolean bDistinct=true;
    public int iAnzahl=0; // für PagAnzahl und SpAnzahl
    public Vector<Integer> VecAlw=null; // always-Spalten

	private int iSpalten=0;
	private int iArt=-1;
        public JCOutlinerFolderNode NodVBed=new JCOutlinerFolderNode("Vorbedingung");
	public JCOutlinerFolderNode NodBed=new JCOutlinerFolderNode("Bedingung");
	public JCOutlinerFolderNode NodSpalten=new JCOutlinerFolderNode("Spalten");
	public Tabellenspeicher TabSp;
	public Global g;
	public boolean bBedingung=true;
	public boolean bSpalten=true;
	public boolean bHasBed=false;
	public boolean bTable=false;
	private Tabellenspeicher TabBedingungen;
	private Tabellenspeicher TabFixEigenschaften;
	protected Vector VecPerioden=null;
	private int iEbenen=1;

	private boolean bFehler=false;
        public String sAnfang=null;
        public boolean bEntfernte=false;	// Entfernte auch anzeigen und markieren
        public boolean bAlle=false;		// um Alle im EF zu markieren
        public int iQry=0;
        public Vector<String> VecQry=null;
        protected Vector VecMenge=null;
        public String sDefBez=null;
        public String sBez=null;
        public boolean bAlleSpalten=false;	// Alle Spalte in DefAbfrage anzeigen
        public int iSort=0;
        public Tabellenspeicher TabJoker;
        public Vector<String> VecDateJoker=null;
        public int iJokerZahl=0;
        protected String sSort=null;
        public boolean bFill=false;		// Outliner füllen
        private boolean bLetLeer=true;
        public Tabellenspeicher TabSync=null;
        public int iS2_Art=0;
        public int iBG_Soll=0;
        public static Tabellenspeicher TabBA=null;
//        public static Tabellenspeicher TabSpalten2=null; // Benutzerabfrage persönlich
//        public static Tabellenspeicher TabSpalten2b=null;// Benutzerabfrage laut Benutzergruppe
//        public static Tabellenspeicher TabSpalten2c=null;// Benutzerabfrage für jeden
//        public static Tabellenspeicher TabSpalteZ2=null; // Benutzerabfrage Horiz. Auflösung persönlich
//        public static Tabellenspeicher TabSpalteZ2b=null;// Benutzerabfrage Horiz. Auflösung laut Benutzergruppe
//        public static Tabellenspeicher TabSpalteZ2c=null;// Benutzerabfrage Horiz. Auflösung für jeden
        //public boolean bVB=false;
        public boolean bRefresh=false;
        public String sBind=null;
        public String sStammTitel=null;
//        private Date dtVon=null;
//        private Date dtBis=null;
        public int iVB=0;
        protected boolean bWeb2=false;
        
        public static int iSprache=0;
        private static int iMold=-1;

        public static AUVector VVonBis=new AUVector(new String[] {"von_bis","Auto_von_bis","BewVon_Bis"});
        public static AUVector VVerkn=new AUVector(new String[] {"Hierarchie","Gruppe","Einheiten","Mass","Waehrung","BewWaehrung","BewWaehrung2","Benutzer","User","BewStamm","BewMass","BewMass2",
                                            "BewHierarchie","BewModell","Anlage","Mandant","Firma","BewDruck","BewFormular","LoeschBenutzer","BewModul","BewLand","BewBG","BewUser","Land","Aufgabe","Status","BewHoliday","BewBool3","Bool3"});
        public static AUVector VRel=new AUVector(new String[] {"BewZahl2","BewMass2","BewWaehrung2"});
        private static AUVector VecME=new AUVector(new String[] {"Gruppe","Hierarchie","BewStamm","BewHierarchie","Benutzer","User","LoeschBenutzer","Mandant","Firma","Anlage","Mehrfach",
        		"BewModell","BewDruck","BewFormular","BewModul","LoeschBenutzer","BewLand","BewBG","BewUser","Land","Aufgabe","Status","BewHoliday","BewBool3","Bool3","GPS"});

	// ------------------------------------- Erzeugen und Laden -------------------------------------------------------

	public Abfrage(Global rg,String sKennung)
	{
		g = rg;
		Count.add(Count.AbfrageLaden);
		Load("kennung='"+sKennung+"'",false);
	}

	public Abfrage(Global rg,int riAic,int riArt)
	{
	  this(rg,riAic,riArt,false,0,0,false);
	}

        public Abfrage(Global rg,int riAic,int riArt,boolean b)
        {
          this(rg,riAic,riArt,b,0,0,false);
        }
        
        public Abfrage(Global rg,int riAic,int riArt,boolean b,boolean bW)
        {
        	this(rg,riAic,riArt,b,0,0,bW);
        }

	public Abfrage(Global rg,int riAic,int riArt,boolean b,int riS2_Soll,int riBG,boolean bW)
	{
		g = rg;
		Count.add(Count.AbfrageLaden);
		bWeb2=bW;
		iArt=riArt;
//		dtVon=g.getVon();
//		dtBis=g.getBis();
		//Global.fixInfo("Abfrage "+riAic+" mit ZR="+dtVon+"-"+dtBis);
		bFill=b;
                iS2_Art=riS2_Soll;
                iBG_Soll=riBG;
                //if (riS2_Soll>0)
                if (Global.bInfoAbfrage)
                  g.fixInfo("Abfrage "+riAic+"/"+riArt+"/"+riS2_Soll+"/"+riBG);
		if(iArt==cstHS_Filter)
			bSpalten=false;
		else if(iArt==cstHS_Spalte)
			bBedingung=false;
		if (riAic>0)
                {
                  //if (iArt == cstSpalte)
                  //  Load("aic_spalte=" + riAic,true);
                  //else
                    Load(riAic);
                  //Load(iArt == cstSpalte ? "aic_spalte=" + riAic : iArt == cstBegriff ? "Begriff.aic_Begriff=" + riAic :
                  //     "Abfrage.Aic_Abfrage=" + riAic, iArt == cstSpalte);
                }
		else
			clear(riAic);
	}

	protected Abfrage(Abfrage Aold)
	{
		//this(Aold.g,-1,Aold.iArt);
		g = Aold.g;
		Count.add(Count.AbfrageLaden);
		iArt=Aold.iArt;
//		dtVon=g.getVon();
//		dtBis=g.getBis();
		if(iArt==cstHS_Filter)
			bSpalten=false;
		else if(iArt==cstHS_Spalte)
			bBedingung=false;

		if (bBedingung)
                {
                  NodVBed = (JCOutlinerFolderNode)Aold.NodVBed.clone();
                  NodBed = (JCOutlinerFolderNode)Aold.NodBed.clone();
                }
		if (bSpalten)
		{
			TabSp=new Tabellenspeicher(g,Aold.TabSp);
			NodSpalten=(JCOutlinerFolderNode)Aold.NodSpalten.clone();
		}
		iAbfrage=Aold.iAbfrage;
		iBegriff=Aold.iBegriff;
		iBits=Aold.iBits;
        iBitsO=Aold.iBitsO;
        lBits2=Aold.lBits2;
        lBits2O=Aold.lBits2O;
		iStt=Aold.iStt;
        iRolle=Aold.iRolle;
		iBew=Aold.iBew;
		iTop=Aold.iTop;
                //g.progInfo("Abfrage aus Abfrage "+sDefBez+":"+iModell+"/"+VecQry);
                sDefBez=Aold.sDefBez;
                sBez=Aold.sBez;
                iModell=Aold.iModell;
                iModell2=Aold.iModell2;
                iWebStamm=Aold.iWebStamm;
                VecQry=Aold.VecQry;
                //g.progInfo("-> "+sDefBez+":"+iModell+"/"+VecQry);
		iSpalten=Aold.iSpalten;
		iAnzahl=Aold.iAnzahl;
		//bFill=true;
	}

        public static void free(Global g)
        {
        	if (TabBA!=null && g!=null)
        	{
        		String sDB_User=g.getDB()+":"+g.getBenutzer();
        		int iP=TabBA.getPos("DB_User", sDB_User);
        		if (iP>=0)
        		{
        			TabBA.setInhalt(iP, "2a",null);
        			TabBA.setInhalt(iP, "2b",null);	
        			TabBA.setInhalt(iP, "2c",null);
        			
        			TabBA.setInhalt(iP, "Z2a",null);	
        			TabBA.setInhalt(iP, "Z2b",null);	
        			TabBA.setInhalt(iP, "Z2c",null);	
        		}
        	}
        	iMold=-1;
//          TabSpalten2=null;
//          TabSpalten2b=null;
//          TabSpalten2c=null;
//          //TabSpalten2d=null;
//          TabSpalteZ2=null;
//          TabSpalteZ2b=null;
//          TabSpalteZ2c=null;
//          //TabSpalteZ2d=null;
        }

	public void finalize()
	{
		Count.sub(Count.AbfrageLaden);
	}
	
	public static Tabellenspeicher getBA(Global g,String s)
	{
		String sDB_User=g.getDB()+":"+g.getBenutzer();
		int iP=TabBA.getPos("DB_User", sDB_User);
		if (iP<0)
			g.fixInfoT("Abfrage.getBA: Benutzerabfrage für "+sDB_User+" nicht gefunden, lade sie deshalb neu");
//		else if (TabBA.isNull(iP,s))
//			readSpalten2(g,0);
		if (iP<0 || TabBA.isNull(iP,s))
			iP=readSpalten2(g,0);
		return iP<0 ? null : (Tabellenspeicher)TabBA.getInhalt(s,iP);
	}
	
	public static void setBA_Null(Global g,String s)
	{
		String sDB_User=g.getDB()+":"+g.getBenutzer();
		int iP=TabBA.getPos("DB_User", sDB_User);
		if (iP>=0)
			TabBA.setInhalt(iP,s,null);
		iMold=-1;
	}
	
//	public static void readSpalten2(Global g)
//	{
//		readSpalten2(g,0);
//	}

        public static int readSpalten2(Global g,int iMandant)
        {
        	if (iMandant>0 && iMold==iMandant)
        		return -1;
        	long lClock=Static.get_ms();
          if (TabBA==null)
        	  TabBA=new Tabellenspeicher(g,new String[] {"DB_User","2a","2b","2c","Z2a","Z2b","Z2c"});
          int iM=iMandant>0 ? iMandant:g.getMandant();
          String sDB_User=g.getDB()+":"+g.getBenutzer();
          int iP=TabBA.getPos("DB_User", sDB_User);
          if (iP<0)
          {
        	  iP=TabBA.newLine();
        	  TabBA.setInhalt(iP,"DB_User",sDB_User);
          }
          if (TabBA.isNull(iP,"2a")) // TabSpalten2
            TabBA.setInhalt(iP, "2a", new Tabellenspeicher(g, "select * from spalten2 where ANR is null and aic_benutzer=" + g.getBenutzer()+" order by aic_spalte", true));
          String sBG=(g.Def() ? g.bLizenzFrei ? "aic_benutzergruppe is not null":"aic_benutzergruppe in (select aic_benutzergruppe from benutzergruppe"+g.getReadMandanten(true,null)+")":
                g.SuperUser() ? "aic_benutzergruppe in (select aic_benutzergruppe from benutzergruppe where aic_mandant="+iM+")":g.getAllBG());
          if (TabBA.isNull(iP,"2b") || iMandant>0) // Benutzergruppe: TabSpalten2b
        	TabBA.setInhalt(iP, "2b", new Tabellenspeicher(g, "select * from spalten2 where ANR is null and "+sBG+(/*g.Def() ?"":*/" and (aic_mandant is null or aic_mandant="+iM+")")+" order by aic_benutzergruppe,aic_spalte", true));
          if (TabBA.isNull(iP,"2c") || iMandant>0) // Jeder: TabSpalten2c
        	TabBA.setInhalt(iP, "2c", new Tabellenspeicher(g, "select * from spalten2 where ANR is null and aic_benutzer is null and aic_benutzergruppe is null"+(/*g.Def() ?"":*/" and (aic_mandant is null or aic_mandant="+iM+")")+" order by aic_spalte", true));

          //if (TabSpalten2d==null)
          //  TabSpalten2d = new Tabellenspeicher(g, "select * from spalten2 where ANR is not null order by anr,aic_spalte", true);
          if (TabBA.isNull(iP,"Z2a")) // TabSpalteZ2
        	  TabBA.setInhalt(iP, "Z2a", new Tabellenspeicher(g, "select * from SPALTE_Z2 where aic_benutzer=" + g.getBenutzer()+" order by aic_spalte,reihe", true));
          if (TabBA.isNull(iP,"Z2b") || iMandant>0) // TabSpalteZ2b
        	  TabBA.setInhalt(iP, "Z2b", new Tabellenspeicher(g, "select * from SPALTE_Z2 where "+sBG+(/*g.Def() ?"":*/" and (aic_mandant is null or aic_mandant="+iM+")")+" order by aic_benutzergruppe,aic_spalte,reihe", true));
          if (TabBA.isNull(iP,"Z2c") || iMandant>0) // TabSpalteZ2c
        	  TabBA.setInhalt(iP, "Z2c", new Tabellenspeicher(g, "select * from SPALTE_Z2 where aic_benutzer is null and aic_benutzergruppe is null"+(/*g.Def() ?"":*/" and (aic_mandant is null or aic_mandant="+iM+")")+" order by aic_spalte,reihe", true));
          //if (TabSpalteZ2d==null)
          //  TabSpalteZ2d = new Tabellenspeicher(g, "select * from SPALTE_Z2 where ANR is not null order by aic_spalte,reihe", true);
          iMold=iM;
          g.clockInfo("readSpalten2 für Mandant="+iMandant, lClock);
          return iP;
        }

        public boolean isHS()
        {
          return iArt==cstHS_Filter || iArt==cstHS_Spalte;
        }

	public void copy(Abfrage A)
	{
		NodVBed=A.NodVBed;
                NodBed=A.NodBed;
		NodSpalten=A.NodSpalten;
		TabSp=A.TabSp;
		iAbfrage=A.iAbfrage;
		iBegriff=A.iBegriff;
		iBits=A.iBits;
        iBitsO=A.iBitsO;
        lBits2=A.lBits2;
        lBits2O=A.lBits2O;
		iSpalten=A.iSpalten;
		iAnzahl=A.iAbfrage;
        iStt=A.iStt;
        iRolle=A.iRolle;
        iBew=A.iBew;
        iTop=A.iTop;
	}

	/*public Object clone()
	{
		return this.clone();
		//Abfrage A=new Abfrage(g,0,iArt);
	}*/
	
//	public void setVonBis(Date dtVon1,Date dtBis1)
//	{
//		dtVon=dtVon1;
//		dtBis=dtBis1;
//		g.fixtestError("setze lokalen Zeitraum auf "+dtVon+"-"+dtBis);
//	}

	public boolean Fehler()
	{
		return bFehler;
	}
	
	public static int getLaenge(Tabellenspeicher Tab)
	{
		return getLaenge(Tab.getPos(),Tab);
	}

	public static int getLaenge(int iPos,Tabellenspeicher Tab)
	{
		int i=Tab.getI(iPos,"Laenge");
		return i<0 || (i&511)==501 ? -1:i&511;
	}

	public static int getLaengeD(Tabellenspeicher Tab)
	{
		int i=Tab.getI("Laenge");
		i= i<0 ? 0:(i/512)&1023;
                return i>1000 ? -1:i;
	}

	public static int getLaengeB(Tabellenspeicher Tab)
	{
		int i=Tab.getI("Laenge");
		return i<0 ? 0:(i/512/1024)&1023;
	}
	
	public static int getLaengeN(Tabellenspeicher Tab)
	{
		int i=Tab.getI("Laenge");
		return i<0 ? 0:i/512/1024/1024;
	}
	
	public static int getLaengeW(Tabellenspeicher Tab)
	{
		return Tab.getI("WebLaenge");
	}
	
	public int getPagAnzahl()
	{
		int i=iAnzahl/16;
		return i>4000 ? -1:i;
	}
	
	public int getSpAnzahl()
	{
		return iAnzahl & 15;
	}

        private void Load(int riAic)
        {
          g.checkAbfragen();
          readSpalten2(g,iMold);
          int iPos= iArt==cstBegriff ? g.TabAbfragen.getPos("aic_begriff",riAic):g.TabAbfragen.getPos("aic_abfrage",riAic);
          if (iPos<0)
          {
        	  g.printError((iArt==cstBegriff ? "Begriff ":"Abfrage ")+riAic+" in TabAbfragen nicht gefunden!");
        	  g.TabAbfragen=null;
        	  g.checkAbfragen();
        	  iPos= iArt==cstBegriff ? g.TabAbfragen.getPos("aic_begriff",riAic):g.TabAbfragen.getPos("aic_abfrage",riAic);
          }
          bFehler=iPos<0;
          boolean bBed=false;
          if (!bFehler)
          {
                  iAbfrage=g.TabAbfragen.getI(iPos,"aic_abfrage");
                  bBed=g.TabAbfragen.getI(iPos,"Bed")>0;
                  iBegriff=g.TabAbfragen.getI(iPos,"aic_begriff");
                  iBG=g.TabAbfragen.getI(iPos,"aic_benutzergruppe");
                  iBits=g.TabAbfragen.getL(iPos,"bits");
                  iBitsO=iBits;
                  lBits2=/*g.TabAbfragen.exists("Abits2") ? */g.TabAbfragen.getL(iPos,"Abits2");//:0;
                  lBits2O=lBits2;
                  iStt=g.TabAbfragen.getI(iPos,"aic_stammtyp");
                  iBew=g.TabAbfragen.getI(iPos,"Erf");
                  iRolle=g.TabAbfragen.getI(iPos,"Rolle");
                  sDefBez=g.TabAbfragen.getS(iPos,"DefBezeichnung");                
                  int iPosB=g.TabBegriffe.getPos("Aic",iBegriff);
                  if (iPosB>=0)
                  {
                    //iStt=g.TabBegriffe.getI("Stt");                    
                    sBez=g.TabBegriffe.getS(iPosB,"Bezeichnung");
                    bTod=g.TabBegriffe.getB(iPosB,"Tod");
                    bWeb=g.TabBegriffe.getB(iPosB,"Web");
                    bJeder=g.isJederS(iPosB);
                    //g.fixtestInfo("Abfrage "+sBez+" ist "+(bTod?"Tod":"lebendig"));
                    //iBew=TabAbfragen.getI("aic_bewegungstyp");
                  }
                  else
                  {
                	  sBez=sDefBez;
                	  bJeder=true;
                	  g.printError("Abfrage.Load: Begriff "+iBegriff+" in TabBegriffe nicht gefunden!");
                  }
                  iModell=g.TabAbfragen.getI(iPos,"aic_modell");
                  iModell2=g.TabAbfragen.getI(iPos,"mod_aic_modell");
                  iWebStamm=g.TabAbfragen.getI(iPos,"WebStamm");
                  iAbfF=SQL.getInteger(g,"select aic_abfrage from abfrage join begriff_zuordnung z on z.beg_aic_begriff=abfrage.aic_begriff join begriff b on z.beg_aic_begriff=b.aic_begriff and z.aic_begriff="+iBegriff+" and z.Art="+g.getCodeAic("Zuordnungsart", "Filter"));
                  iAbfD=-1;
                  iFomD=-1;
                  iAbfE=-1;
                  iTop=g.TabAbfragen.getI(iPos,"autorefresh");
                  iSpalten=g.TabAbfragen.getI(iPos,"spalten");
                  iAnzahl=g.TabAbfragen.getI(iPos,"anzahl");
                  //g.progInfo("Load "+sDefBez+": Bew="+iBew+", Stt="+iStt+", Rolle="+iRolle);
          }
          else
        	  g.printError(" auch nach TabAbfragen-Refresh nicht gefunden:"+riAic);
          LoadRest(bBed);
        }

	private void Load(String sWhere,boolean bSpalte)
	{
          g.checkAbfragen();
          readSpalten2(g,iMold);
		SQL Qry= new SQL(g);
		//g.progInfo("Abfrage.Load:"+sWhere+", Art="+iArt);
		Qry.open("select DefBezeichnung,abfrage.aic_abfrage,begriff.aic_begriff,begriff.bits,Abfrage.Abits2,begriff.aic_stammtyp,begriff.aic_bewegungstyp Erf,begriff.aic_rolle Rolle,aic_modell,autorefresh,spalten,anzahl"+
                         ",(select count(aic_bedingung) from bedingung where aic_abfrage=abfrage.aic_abfrage) Bed,mod_aic_modell,WebStamm"+
                         " from "+(bSpalte?"spalte"+g.join("abfrage","spalte"):"abfrage")+g.join("begriff","abfrage")+" where "+sWhere);
		bFehler=Qry.eof();
        boolean bBed=false;
		if (!bFehler)
		{
			iAbfrage=Qry.getI("aic_abfrage");
            bBed=Qry.getI("Bed")>0;
			iBegriff=Qry.getI("aic_begriff");
			iBits=Qry.getL("bits");
            iBitsO=iBits;
            lBits2=Qry.getL("Abits2");
            /*int iPos=g.TabAbfragen.getPos("aic_abfrage",iAbfrage);
            if (iPos<0)
            {
          	  Static.printError("Begriff "+sWhere+" nicht gefunden!");
          	  g.TabAbfragen=null;
          	  g.checkAbfragen();
          	  iPos= g.TabAbfragen.getPos("aic_abfrage",iAbfrage);
            }*/
            iStt=Qry.getI("aic_stammtyp");
            iBew=Qry.getI("Erf");
            iRolle=Qry.getI("Rolle");
            sDefBez=Qry.getS("DefBezeichnung");  
			int iPosB=g.TabBegriffe.getPos("Aic",iBegriff);
			if (iPosB>=0)
            {
              sBez = g.TabBegriffe.getS(iPosB,"Bezeichnung");
            }
			else
            {
          	  sBez=sDefBez;
          	  g.printError("Abfrage.Load: Begriff "+iBegriff+" bei "+sWhere+" nicht gefunden!");
            }
			iModell=Qry.getI("aic_modell");
            iModell2=Qry.getI("mod_aic_modell");
            iWebStamm=Qry.getI("WebStamm");
			iTop=Qry.getI("autorefresh");
			iSpalten=Qry.getI("spalten");
			iAnzahl=Qry.getI("anzahl");
			//g.progInfo("Abfrage.Load: setze Spalten auf "+iSpalten);
		}
		else
      	  g.printError("Abfrage mit Bedingung ["+sWhere+"] nicht gefunden!");
		//g.progInfo("Lade Abfrage "+Qry.getS("defbezeichnung")+":"+iAbfrage+"/"+iBegriff+", Stt="+iStt+", Bew="+iBew);
		Qry.free();
		//g.testInfo(iAbfrage+"/"+iBits+"/"+iStt+"/"+iBew+"/"+iModell+"/"+iTop);
                LoadRest(bBed);
        }

        private void LoadRest(boolean bBed)
        {
          /*if ((g.iInfos&g.LDATE)>0 && (iBits&cstLDATE)==0 && iBew>0)
          {
            g.testInfo("aktiviere LDATE bei " + sDefBez);
            iBits += cstLDATE;
          }*/
          if ((Transact.iInfos&Transact.NO_SPEED)>0 || Static.bKeinLDate)
          {           
            if((iBits&cstLDATE)>0)
            {
              g.fixInfo("deaktiviere LDATE bei " + sDefBez);
              iBits -= cstLDATE;
            }
          }
          /*else if(!Static.bLDATE && (iBits&cstLDATE)>0)
          {
            iBits -= cstLDATE;
          }*/

		if (bBedingung && bBed)
		{
			if (iAbfrage != 0)
			{
				TabBedingungen = new Tabellenspeicher(g,"select aic_bedingung,bed_aic_bedingung,aic_begriff,vergleichswert,bbits from bedingung where aic_abfrage="+iAbfrage+" order by "+g.order("bed_aic_bedingung"),true,"Bed"+iAbfrage+(iBG>0?".AU2":""));
                          /*if (iAbfrage==2845 || iAbfrage==2987)
                          {
                            g.fixInfo("Abfrage=" + iAbfrage);
                            TabBedingungen.showGrid("Bed. für Abf "+iAbfrage);
                          }*/
				if (!TabBedingungen.isEmpty())
				{
					TabFixEigenschaften  = new Tabellenspeicher(g,"select aic_eigenschaft,aic_bewegungstyp,e.aic_bedingung,richtung from fixeigenschaft e"+g.join("bedingung","e")+" where aic_abfrage="+iAbfrage+" order by e.aic_bedingung,e.aic_fixeigenschaft",true,"FixBed"+iAbfrage+(iBG>0?".AU2":""));
					//fuelleTabStamm();
                  //if (Static.bTest)
                    Load_Rekursion(NodVBed,0,INDEX); // 10.8.: vorübergehend ausgeblendet, da es nur bei Sybase funktioniert
					Load_Rekursion(NodBed,0,0);
					TabFixEigenschaften.clearAll();
					TabFixEigenschaften=null;
					TabBedingungen.clearAll();
					bHasBed=true;
				}
				TabBedingungen=null;
			}
		}
		//g.progInfo("Abfrage: Load Spalten");
		if (bSpalten)
		{
			boolean bBez2=g.Def() && iSprache>0;
                  String sSpSQL="select aic_spalte,kennung"+g.AU_BezeichnungFo("Spalte")+"Bezeichnung,"+(bBez2 ? g.AU_BezeichnungF("Spalte", "Spalte", iSprache):"null")+" Bez2,soritiert sortiert,bits,aic_code,cod_aic_code,laenge,weblaenge,null Vec,Reihenfolge,null Status,aic_begriff,cod2_aic_code Ausrichtung,aic_stamm Mass,aic_stammtyp"+
			g.AU_Tooltip("Spalte","Spalte")+",Nummer,Faktor,Filter,null Gruppe,null Calc,min,max,x,y,w,h,Stil,ToggleSight,Icon,Farbe,bits2,bits3,cod3_aic_code Anzeige,sta_aic_stamm rel,spa_aic_spalte sub,aic_farbe,beg_aic_begriff Sub1,Abfrage_begriff Sub2,Modell_begriff Sub3,null Node,null bBits from spalte where aic_abfrage="+iAbfrage+" order by reihenfolge";
//                  g.fixtestError("Load Spalten mit Sprache="+iSprache+": "+sSpSQL);
                  //g.progInfo(sSpSQL);
                  TabSp = new Tabellenspeicher(g,sSpSQL,true,"Spalten"+iAbfrage+"_"+g.getSprache()+(iBG>0?".AU2":""));
                  boolean b=false;
                  int iSE=0;
                  boolean bSort=iModell==0 || !Static.bWeb;
                  //int iBG=0;
                  if (iArt!=cstDefAbfrage)
                        for(TabSp.moveFirst(); !TabSp.out(); TabSp.moveNext())
                        {
                          Tabellenspeicher Tab=getBA(g,"2a");// TabSpalten2;
                          int iPos=-1;
                          if (iS2_Art<2) // persönlich
                            iPos = Tab.getPos("aic_spalte", TabSp.getI("aic_spalte"));
                          if (iPos >= 0)
                            iS2_Art = 1;
                          else
                          {
                            /*if (iS2_Art==0 || iS2_Art==5)
                            {
                              Tab = TabSpalten2d;
                              iPos = Tab.getPos("aic_spalte", TabSp.getI("aic_spalte"));
                              if (iS2_Art == 0 && iPos >= 0)
                                iS2_Art=5;
                            }*/
                            if (iS2_Art==0 || iS2_Art==2)
                            {
                              Tab = getBA(g,"2b");// TabSpalten2b;
                              iPos = Tab.getPos("aic_spalte", TabSp.getI("aic_spalte"));
                              if (iPos >= 0 && iBG_Soll == 0)
                              {
                                iBG_Soll = Tab.getI(iPos, "aic_benutzergruppe");
                                iS2_Art=2;
                              }
                            }
                            if (iS2_Art == 0 || iS2_Art==3) // Jeder
                            {
                                Tab = getBA(g,"2c");// TabSpalten2c;
                                iPos = Tab.getPos("aic_spalte", TabSp.getI("aic_spalte"));
                                if (iS2_Art == 0 && iPos>=0)
                                  iS2_Art=3;
                            }
                          }
                          if ((TabSp.getI("bits") & (Global.cstAlways*0x10000))>0)
                          {
                          	if (VecAlw==null)
                          		VecAlw=new <Integer>Vector();
                          	VecAlw.addElement(TabSp.getI("Nummer"));
                          }	
//                          Tab.showGrid("TabBA");
                          if (!bBez2)
                        	  TabSp.setInhalt("Bez2",TabSp.getS("Bezeichnung"));
                          if (iPos<0 && TabSp.getI("Rel")>0 && iBew>0)
                          {
                            TabSp.setInhalt("Bezeichnung",g.getStamm(TabSp.getI("Rel")));
                          }
                          else while (iPos>=0)
                          {
                            if (iS2_Art==2)
                              while (iPos>=0 && Tab.getI(iPos, "aic_benutzergruppe") != iBG_Soll)
                                iPos = Tab.getNextPos(iPos, "aic_spalte", TabSp.getI("aic_spalte"));
                           if (iPos>=0)
                           {
                            if (Tab.getI(iPos,"Reihe") != TabSp.getI("Reihenfolge"))
                            {
                              b = true;
                              TabSp.setInhalt("Reihenfolge", Tab.getI(iPos, "Reihe"));
                            }
                            if (bSort)
                            	TabSp.setInhalt("sortiert",Tab.getI(iPos,"sortiert"));
                            TabSp.setInhalt("Aic_Begriff",Tab.getI(iPos,"Aic_Begriff")); // Format
                            TabSp.setInhalt("Aic_Farbe",Tab.getI(iPos,"Aic_Farbe"));
                            int iRel=Tab.getI(iPos,"Aic_Stamm");
                            if (iRel>0)
                            {
                              TabSp.setInhalt("rel", iRel); // für Schnellerfassung
                              TabSp.setInhalt("Bezeichnung",g.getStamm(iRel));
                            }
                            int iBits=Tab.getI(iPos,"bits");
                            TabSp.setInhalt("bBits",iBits);
                            if (!Tab.isNull(iPos,"Titel") && (!bWeb && !bWeb2 || (iBits&DefAbfrage2.NIMM)>0) && !Tab.getS(iPos,"Titel").equals(TabSp.getS("Bezeichnung")))
                            {
                            	g.fixtestError("Ändere Spalte "+TabSp.getS("Bezeichnung")+" auf Benutzerspalte "+Tab.getS(iPos,"Titel")+" bei Web="+bWeb+"/"+bWeb2+", nimm="+((iBits&DefAbfrage2.NIMM)>0));
                              TabSp.setInhalt("Bezeichnung",Tab.getS(iPos,"Titel"));
                            }
//                            g.fixtestError(iPos+":"+iBits+" bei "+TabSp.getS("Bezeichnung")+"/"+TabSp.getI("aic_spalte"));
                            // int iBits=((A.TabSp.getI("bits2") & Abfrage.cstWeg) == 0 ? 1:0)+((A.TabSp.getI("bits")&A.cstSortDesc)>0 ? 2:0);
                            if (((iBits&DefAbfrage2.SHOW)>0) != ((TabSp.getI("bits") & cstUnsichtbar) == 0))
                              TabSp.setInhalt("bits",TabSp.getI("bits")^cstUnsichtbar);
                            if (bSort && ((iBits&DefAbfrage2.SoDe)>0) != ((TabSp.getI("bits") & cstSortDesc)>0))
                            {
                              TabSp.setInhalt("bits",TabSp.getI("bits")^cstSortDesc);
                              g.fixtestError("drehe SortDesc bei "+TabSp.getS("Bezeichnung")+"/"+TabSp.getI("aic_spalte"));
                            }
                            if (((iBits&DefAbfrage2.MOBIL)>0) != ((TabSp.getI("bits") & cstMobil)>0))
                            {
                                TabSp.setInhalt("bits",TabSp.getI("bits")^cstMobil);
                                g.fixtestError("setze Mobil bei "+TabSp.getS("Bezeichnung")+"/"+TabSp.getI("aic_spalte"));
                            }
                            if (((iBits&DefAbfrage2.ZWANG)>0) && ((TabSp.getI("bits") & (Global.cstAlways*0x10000))==0))
                            {
                                TabSp.setInhalt("bits",TabSp.getI("bits")^(Global.cstAlways*0x10000));
                                g.fixtestError("setze Zwingend bei "+TabSp.getS("Bezeichnung")+"/"+TabSp.getI("aic_spalte"));
                            }
                            if ((iBits&DefAbfrage2.VZD)>0)
                            	TabSp.setInhalt("Faktor",-1);	
                            iPos++;
                            if (Tab.size()>iPos && Tab.getI(iPos,"aic_spalte")==TabSp.getI("aic_spalte"))
                            {
                              TabSp.bInsert=true;
                              TabSp.copyLine();
                              TabSp.moveNext();
                              TabSp.bInsert=false;
                              iSE++; }
                            else
                              iPos=-1;
                           }
                          }
                        }
                        if (b)
                        {
                          TabSp.sort("Reihenfolge", true);
                          //TabSp.showGrid("TabSp");
                        }
//            			g.fixtestError(sBez+": VecAlw="+VecAlw);
			/*if (g.Prog())
			{
				TabSpalten.showGrid("TabSpalten");
				if (Abf.TabSpaltenVorher != null)
					Abf.TabSpaltenVorher.showGrid("TestTemp");
			}*/
			//NodSpalten.setUserData(TabSpalten);
			if (!TabSp.isEmpty())
			{
				if (is(iBits,cstGruppe))
					LoadSpalteZuordnung();
				TabFixEigenschaften  = new Tabellenspeicher(g,"select aic_eigenschaft,aic_bewegungstyp,e.aic_spalte,richtung from fixeigenschaft e"+g.join("spalte","e")+" where aic_abfrage="+iAbfrage+" order by reihenfolge,e.aic_fixeigenschaft",true,"FixSp"+iAbfrage+(iBG>0?".AU2":""));
                                //if (iAbfrage==6237)
                                //  g.progInfo("Abfrage=6237");
				Load_Spalten();
                if (is(iBits,cstCalc))
					LoadSpalteCalc();
				TabFixEigenschaften.clearAll();
				TabFixEigenschaften=null;
                                if (iSE>0)
                                {
                                  for (int i=0;i<TabSp.size();i++)
                                    if (TabSp.isNull(i,"Vec"))
                                      for (int i2=0;i2<TabSp.size();i2++)
                                        if (!TabSp.isNull(i2,"Vec") && TabSp.getI(i,"aic_spalte")==TabSp.getI(i2,"aic_spalte"))
                                          TabSp.setInhalt(i,"Vec",TabSp.getInhalt("Vec",i2));
                                  //TabSp.showGrid("TabSp");
                                }
			}
			//if (g.Debug())
			//	TabSp.showGrid("TabSp");
		}
		//g.progInfo("Abfrage: Load fertig");

	}

	private void LoadSpalteZuordnung()
	{
		Tabellenspeicher TabSZ=new Tabellenspeicher (g,"select aic_spalte,Titel,reihenfolge,aic_stamm,aic_stammtyp,aic_eigenschaft,(select bezeichnung from stammview2 where aic_stamm=z.aic_stamm) Bezeichnung from spalte_zuordnung z where aic_spalte"+Static.SQL_in(TabSp.getVecSpalte("aic_spalte"))+" order by aic_spalte,reihenfolge",true,"SpalteZ"+iAbfrage+"_"+g.getSprache()+(iBG>0?".AU2":""));
		//TabSZ.showGrid("TabSZ");
                Vector VecSfZ=TabSZ.getVecD("aic_spalte");
                g.progInfo("VecSfZ="+VecSfZ);
                
        Vector<Integer> VecLoad=new Vector<Integer>();
		int iSpalte=-1;
		int iReiheAlt=0;
		Vector<JCOutlinerFolderNode> Vec=null;
		JCOutlinerFolderNode Nod=null;
		for (TabSZ.moveFirst();!TabSZ.eof();TabSZ.moveNext())
		{
                int iPosZ = -1;
                //int iBG = 0;
                Tabellenspeicher Tab=null;
                //g.fixtestError("LoadSpalteZuordnung: prüfe "+TabSZ.getI("aic_spalte")+" bei iS2_Art="+iS2_Art+", iArt="+iArt);
                if (iArt!=cstDefAbfrage)
                {
                  Tab = getBA(g,"Z2a");// TabSpalteZ2;
                  if (iS2_Art<2)
                    iPosZ=Tab.getPos("aic_spalte", TabSZ.getI("aic_spalte"));
                  if (iPosZ < 0)
                  {
                    if (iS2_Art==0 || iS2_Art==2) // laut Benutzergruppe
                    {
                      Tab = getBA(g,"Z2b");// TabSpalteZ2b;
                      int iSpalte2=TabSZ.getI("aic_spalte");
                      //g.fixInfo("suche Spalte "+iSpalte2+", BG="+iBG_Soll);
                      iPosZ = Tab.getPos("aic_spalte", iSpalte2);
                      if (iS2_Art==2 && iBG_Soll>0 && iPosZ >= 0)
                        while (iPosZ > -1 && Tab.getI(iPosZ, "aic_benutzergruppe") != iBG_Soll)
                          iPosZ = Tab.getNextPos(iPosZ, "aic_spalte", iSpalte2);
                      if (iPosZ >= 0 && iBG_Soll == 0)
                      {
                        iBG_Soll = Tab.getI(iPosZ, "aic_benutzergruppe");
                        iS2_Art=2;
                      }
                      //g.fixInfo("BG"+iBG_Soll+",Art="+iS2_Art+", iPosZ="+iPosZ);
                      //if (iPosZ<0)
                      //  TabSpalteZ2b.showGrid("Spalte "+TabSZ.getI("aic_spalte")+" nicht gefunden");
                    }
                    if (iS2_Art == 0 || iS2_Art==3) // Jeder
                    {
                      Tab = getBA(g,"Z2c");// TabSpalteZ2c;
                      iPosZ = Tab.getPos("aic_spalte", TabSZ.getI("aic_spalte"));
                    }
                  }
                }
            if (iPosZ<0) // nicht gefunden, deshalb von von Hauptabfrage
            {
			//g.fixtestError("LoadSpalteZuordnung: Pos nicht gefunden, iPosZ="+iPosZ+"/"+iSpalte);
			if (iSpalte != TabSZ.getI("aic_spalte"))
			{
				iSpalte=TabSZ.getI("aic_spalte");
				//g.progInfo("Neue Spalte:"+iSpalte);
				iReiheAlt=0;
				Vec=new Vector<JCOutlinerFolderNode>();
				TabSp.posInhalt("aic_spalte",iSpalte);
				TabSp.setInhalt("Gruppe",Vec);
			}
			if(TabSZ.getI("Reihenfolge")<1000 || TabSZ.getI("Reihenfolge")/1000 > iReiheAlt)
			{
				iReiheAlt=TabSZ.getI("Reihenfolge")/1000;
				//g.progInfo("Neue Wert:"+Static.beiLeer(TabSZ.getS("Titel"),TabSZ.getS("Bezeichnung"))+"/"+iReiheAlt);
				Nod=new JCOutlinerFolderNode(Static.beiLeer(TabSZ.getS("Titel"),TabSZ.getS("Bezeichnung")));
				Vec.addElement(Nod);
			}
                        String sBezNod=TabSZ.isNull("aic_eigenschaft") ? Static.sLeer:g.TabEigenschaften.getBezeichnungS(TabSZ.getI("aic_eigenschaft"))+": ";
                        sBezNod+=TabSZ.isNull("aic_stamm") ? g.TabStammtypen.getBezeichnungS(TabSZ.getI("aic_stammtyp")):TabSZ.getS("Bezeichnung");
                        Object Obj=null;
                        if (Static.bDefShow && TabSZ.getI("aic_stamm")==0)
                          Static.printError(sDefBez+": kein Stammsatz für Spalte "+TabSZ.getS("Titel")+"("+sBezNod+") vorhanden");
                        if (g.Def() && bFill && TabSZ.getI("aic_stamm")>0)
                        {
                          Vector<String> VecD=new Vector<String>();
                          VecD.addElement(sBezNod);
                          //g.fixInfo("Stamm für HA="+Static.print(TabSZ.getS("aic_stamm")));
                          VecD.addElement(g.TabMandanten.getBezeichnungS(SQL.getInteger(g,"select aic_mandant from stammview2 where aic_stamm=?",-1,TabSZ.getS("aic_stamm"))));
                          Obj=VecD;
                        }
                        else
                          Obj=sBezNod;
                        JCOutlinerNode Nod2=new JCOutlinerNode(Obj);
                        Nod.addNode(Nod2);
                        Vector<Object> Vec2=new Vector<Object>();
                        Vec2.addElement(TabSZ.getInhalt("aic_stamm"));
                        Vec2.addElement(TabSZ.getInhalt("aic_stammtyp"));
                        if (TabSZ.getI("aic_stammtyp")>0 && Static.bX11)
                          Nod2.setStyle(g.getSttStyle(TabSZ.getI("aic_stammtyp")));
                        Vec2.addElement(TabSZ.getInhalt("aic_eigenschaft"));
                        Nod2.setUserData(Vec2);
			//g.progInfo("LoadSpalteZuordnung:"+Vec);
		  }
                else
                  for(int i=0;i<VecSfZ.size();i++)
                  {
                	  int iSpalteMom=Sort.geti(VecSfZ,i);                  
                	  if (!VecLoad.contains(iSpalteMom))
                	  {
                		  VecLoad.addElement(iSpalteMom);              	  
                	  TabSZ.push();
//                	  g.fixtestError("VecSfZ="+VecSfZ);
                    iPosZ=Tab.getPos("aic_spalte", iSpalteMom);
                    if (iBG_Soll>0)
                      while (iPosZ > -1 && Tab.getI(iPosZ, "aic_benutzergruppe") != iBG_Soll)
                          iPosZ = Tab.getNextPos(iPosZ, "aic_spalte", iSpalteMom);
                    TabSZ.posInhalt("aic_spalte",iSpalteMom);
                    //Tab.showGrid("Spalte "+iSpalteMom);
                    if (iPosZ>-1)
                     for (int iPos = iPosZ; iPos < Tab.size() && ((iS2_Art!=2) || Tab.getI(iPos, "aic_benutzergruppe") == iBG_Soll) && Tab.getI(iPos, "aic_spalte")==iSpalteMom; iPos++)
                     {
//                    	 g.fixtestError("iPos="+iPos+", Spalte="+Tab.getI(iPos, "aic_spalte"));
                      if (iSpalte != Tab.getI(iPos, "aic_spalte"))
                      {
                        iSpalte = iSpalteMom;//TabSZ.getI("aic_spalte");
                        //g.fixInfo("Neue Spalte:"+iSpalte);
                        iReiheAlt = 0;
                        Vec = new Vector<JCOutlinerFolderNode>();
                        TabSp.posInhalt("aic_spalte", iSpalte);
                        TabSp.setInhalt("Gruppe", Vec);
                      }
                      if (Tab.getI(iPos, "Reihe") < 1000 || Tab.getI(iPos, "Reihe") / 1000 > iReiheAlt)
                      {
                        iReiheAlt = Tab.getI(iPos, "Reihe") / 1000;
                        Nod = new JCOutlinerFolderNode(Tab.getS(iPos, "Titel"));
                        Vec.addElement(Nod);
                        //g.fixInfo("Neue Wert:"+Tab.getS("Titel")+"/"+iReiheAlt+"/"+Vec);
                      }
                      //g.fixInfo(iSpalte+":"+Vec);
                      int iStamm = Tab.getI(iPos, "aic_stamm");
                      //JCOutlinerNode Nod2 = new JCOutlinerNode(g.getStamm(iStamm));
                      if (iStamm==g.iEuro)
                    	  iStamm=0;
                      Object Obj=iStamm==0 ? Static.sKein:g.getStamm(iStamm);
                      if (g.Def() && bFill && iStamm>0)
                      {
                        Vector<String> VecD=new Vector<String>();
                        VecD.addElement((String)Obj);
                        //g.fixInfo("Stamm2 für HA="+Static.print(""+iStamm));
                        VecD.addElement(g.TabMandanten.getBezeichnungS(SQL.getInteger(g,"select aic_mandant from stammview2 where aic_stamm=?",-1,""+iStamm)));
                        Obj=VecD;
//                        g.fixtestError("Spalte "+iSpalteMom+", VecD="+VecD);
                      }
                      JCOutlinerNode Nod2=new JCOutlinerNode(Obj);

                      Nod.addNode(Nod2);
                      Vector<Object> Vec2 = new Vector<Object>();
                      Vec2.addElement(iStamm);
                      Vec2.addElement(TabSZ.getInhalt("aic_stammtyp"));
                      Vec2.addElement(TabSZ.getInhalt("aic_eigenschaft"));
                      if (TabSZ.getI("aic_stammtyp")>0 && Static.bX11)
                        Nod2.setStyle(g.getSttStyle(TabSZ.getI("aic_stammtyp")));
                      Nod2.setUserData(Vec2);
                      //g.progInfo("LoadSpalteZuordnung:"+Vec);
                     }
                    TabSZ.pop();
                  }
                  }
		}
	}

	private void LoadSpalteCalc()
	{
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_spalte,aic_code,spa_aic_spalte,wert,eingabe from spalte_berechnung where aic_spalte"+Static.SQL_in(TabSp.getVecSpalte("aic_spalte"))+" order by aic_spalte,pos",true,"SpalteC"+iAbfrage+(iBG>0?".AU2":""));
		//Tab.showGrid("LoadSpalteCalc");
		Tabellenspeicher TabNeu=null;
		int iSpalte=-1;
		for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			if (iSpalte != Tab.getI("aic_spalte"))
			{
				iSpalte = Tab.getI("aic_spalte");
				TabSp.posInhalt("aic_spalte",iSpalte);
				TabNeu=new Tabellenspeicher(g,new String[] {"Operation","Spalte","Wert","Eingabe"});
				TabSp.setInhalt("Calc",TabNeu);
				if (Static.bX11 && !Static.bWeb)
				{
					JCOutlinerNode Nod=TabSp.exists("Node") ? (JCOutlinerNode)TabSp.getInhalt("Node"):null;
					if (Nod!=null)
						Nod.getStyle().setForeground(getColor("Calc"));
				}
			}
			TabNeu.newLine();
                        int iPosC=g.TabCodes.getPos("Aic",Tab.getI("aic_code"));
			if (iPosC>=0)
				TabNeu.setInhalt("Operation",new Combo(g.TabCodes.getS(iPosC,"Bezeichnung"),Tab.getI("aic_code"),g.TabCodes.getS(iPosC,"Kennung"),0));
			if (TabSp.posInhalt("aic_spalte",Tab.getInhalt("spa_aic_spalte")))
				TabNeu.setInhalt("Spalte",new Combo(Static.beiLeer(TabSp.getS("Bezeichnung"),getEigenschaftBezeichnung(g,(Vector)TabSp.getInhalt("Vec"))/*"Spalte "+TabSp.getI("Nummer")*/),TabSp.getI("Nummer"),TabSp.getS("Kennung"),0));
			TabNeu.setInhalt("Wert",Tab.getInhalt("Wert"));
			TabNeu.setInhalt("Eingabe",Tab.getInhalt("Eingabe"));
			//TabNeu.showGrid("LoadSpalteCalc: TabNeu");
		}
	}

	public void clear(int riAbfrage)
	{
		//g.progInfo("Abfrage.clear:"+riAbfrage);
		if (TabSp==null)
			TabSp=new Tabellenspeicher(g,new String[] {"aic_spalte","kennung","Bezeichnung","Bez2","bits","sortiert","aic_code","cod_aic_code","laenge","weblaenge","Vec","Reihenfolge","Status","aic_begriff","Ausrichtung","Mass","aic_stammtyp","TT","Nummer","Faktor","Filter",
					"Gruppe","Calc","Min","Max","x","y","w","h","Stil","ToggleSight","Icon","Farbe","bits2","bits3","Anzeige","Rel","sub","aic_farbe","Sub1","Sub2","Sub3","Node","bBits"});
		TabSp.clearAll();
                NodVBed.removeChildren();
		NodBed.removeChildren();
		NodSpalten.removeChildren();
		iAbfrage=riAbfrage;
		iBegriff=0;
		iSpalten=0;
		iModell=0;
        iModell2=0;
        iWebStamm=0;
        if (iArt==cstHS_Filter || iArt==cstHS_Spalte)
          iBits=iBits|cstHS;
        if (iArt==cstHS_Filter)
          iBits=iBits|cstFilter;
                //g.progInfo("clear: bits="+iBits);
	}

	public void addSpalte()
	{
		iSpalten++;
		//g.progInfo("Abfrage.addSpalte: setze Spalten auf "+iSpalten);
	}

	public int Spalten()
	{
		return iSpalten;
	}

	private void Load_Spalten()
	{
		//g.progInfo("Abfrage: Load_Spalten");
		int iSpalteVorher=0;
		Vector<Object> Vec=null;
		//String s=null;
		int iSpalte=TabFixEigenschaften.getI("aic_spalte");
		//g.progInfo("Abfrage: Load_Spalten: "+iSpalte+"/"+TabFixEigenschaften.out());
		while (!TabFixEigenschaften.eof())
		{
			TabSp.posInhalt("aic_spalte",iSpalte);
			int iEig=TabFixEigenschaften.getI("Aic_eigenschaft");
			//g.progInfo("Abfrage: "+IntEig+"/"+TabSpalten.out());
			//g.TabEigenschaften.posInhalt("Aic",IntEig);
			if(iSpalteVorher != iSpalte)
			{
				Vec=new Vector<Object>();
				iSpalteVorher = iSpalte;
				//s="";
			}
			//else
				//s=s+'.';
			//s=s+(TabFixEigenschaften.getB("Richtung")?"#":"")+g.TabEigenschaften.getS("Bezeichnung");
			String sArt= TabSp.getI("cod_aic_code")==0 ? null:g.TabCodes.getKennung(TabSp.getI("cod_aic_code"));
			Vec.addElement(TabFixEigenschaften.getI("aic_bewegungstyp")>0?(Object)new BewEig(TabFixEigenschaften.getI("aic_bewegungstyp"),iEig,0,sArt):new Integer(TabFixEigenschaften.getB("Richtung")?-iEig:iEig));
			TabFixEigenschaften.moveNext();
			if(!TabFixEigenschaften.eof())
				iSpalte=TabFixEigenschaften.getI("aic_spalte");
			if(TabFixEigenschaften.eof() || iSpalteVorher != iSpalte)
			{
			  if (bFill)
			  {
				JCOutlinerNode Nod=new JCOutlinerNode(Static.beiLeer(TabSp.getS("Bezeichnung"),getEigenschaftBezeichnung(g,Vec)),NodSpalten);
				Nod.setUserData(TabSp.getInhalt("Nummer"));
				int iPos=g.TabEigenschaften.getPos("aic", iEig);
				String sDatentyp=g.TabEigenschaften.getS(iPos,"Datentyp");
				iPos=g.getPosBegriff("Datentyp",sDatentyp);
                if (Static.bX11 && iPos>=0)
                {
                   Image Gif = g.LoadImage(g.TabBegriffe.getS(iPos, "BildFile"));
                   JCOutlinerNodeStyle Sty = g.getStyle(Gif);
                   Sty.setForeground(getColor(sDatentyp));
//                             (TabSp.getI("bits2") & Abfrage.cstWeg) > 0 ? g.ColHide : (TabSp.getI("bits3") & cstZusatz) > 0 ? g.ColZusatz:(TabSp.getI("bits") & Global.cstAlways*0x10000) > 0 ? g.ColZwingend:g.ColStandard);
                   Nod.setStyle(Sty);
                }
                if (!Static.bWeb)
                	TabSp.setInhalt("Node", Nod);
				//g.fixInfo(" ---------------------------------------- Load_Spalten fill -----------------------------");
			  }
			  TabSp.setInhalt("Vec",Vec);			  
			}
		}
	}
	
	public Color getColor(String sDt)
	{
		return (TabSp.getI("bits2") & Abfrage.cstWeg) > 0 ? g.ColHide : sDt.equals("Text") ? g.ColText:
      !TabSp.isNull("Calc") || sDt.startsWith("Calc") ? g.ColCalc2:!TabSp.isNull("Gruppe") ? Color.BLUE:(TabSp.getI("bits3") & cstZusatz) > 0 ? g.ColZusatz:
      	  (TabSp.getI("bits") & Global.cstAlways*0x10000) > 0 ? g.ColZwingend:g.ColStandard;
	}

	private void Load_Rekursion(JCOutlinerNode OutNod,int iAic,int iVor)
	{
		//g.progInfo("Abfrage: Load_Rekursion:"+sAIC);
		
             //int iVor=bVor?4:0;
             for(int iPos=0;iPos<TabBedingungen.size();iPos++)
             {
               if ((TabBedingungen.getI(iPos,"bbits")&BART)==iVor && TabBedingungen.getI(iPos,"bed_aic_bedingung")==iAic)
                 Load_Rekursion(Load_Element(OutNod,iPos),TabBedingungen.getI(iPos,"aic_bedingung"),iVor);
             }
	}

	private JCOutlinerNode Load_Element(JCOutlinerNode OutNod,int iPosB)
	{
		Vector<Object> Vec2= new Vector<Object>();
		//Integer IntEig=null;
		int iEig=0;
		//String s = "";
		boolean bFirst = true;
		TabFixEigenschaften.posInhalt("aic_bedingung",TabBedingungen.getInhalt("aic_bedingung",iPosB));
		while(!TabFixEigenschaften.out() && TabFixEigenschaften.getInhalt("aic_bedingung").equals(TabBedingungen.getInhalt("aic_bedingung",iPosB)))
		{
			if (bFirst)
				bFirst = false;
			//else
			//	s=s+'.';
			iEig=TabFixEigenschaften.getI("Aic_eigenschaft");
			//g.TabEigenschaften.posInhalt("Aic",iEig);
			//s=s+(TabFixEigenschaften.getB("Richtung")?"#":"")+g.TabEigenschaften.getS("Bezeichnung");
			Vec2.addElement(TabFixEigenschaften.getI("aic_bewegungstyp")>0?(Object)new BewEig(TabFixEigenschaften.getI("aic_bewegungstyp"),iEig,0,null):new Integer(TabFixEigenschaften.getB("Richtung")?-iEig:iEig));

			//Vec2.addElement(TabFixEigenschaften.getB("Richtung")?new Integer(-IntEig.intValue()):IntEig);
			TabFixEigenschaften.moveNext();
		}

		int iPos=g.TabBegriffe.getPos("Aic",new Integer(TabBedingungen.getI(iPosB,"AIC_BEGRIFF")));
                int iPosE=g.TabEigenschaften.getPos("aic",iEig);
		if (iPosE>=0)
		{
			Vector<Object> VecAIC = new Vector<Object>();
			//Integer IntAic = new Integer(CboEigenschaft.getSelectedAIC());
			//TabEigenschaften.posInhalt("aic_eigenschaft",IntAic.toString());
			String sDatentyp = g.TabEigenschaften.getS(iPosE,"datentyp");
			VecAIC.addElement(sDatentyp);
			VecAIC.addElement(Vec2);
			String sVergleichsart=g.TabBegriffe.getS(iPos,"Kennung");
			VecAIC.addElement(sVergleichsart);
			//String sVergleich;
			if (sVergleichsart.equals("is null") || sVergleichsart.equals("is not null"))
			{
				//sVergleich = "";
                                if (TabBedingungen.isNull(iPosB,"vergleichswert") )
                                  VecAIC.addElement(null);
                                else
                                {
                                  int iRolle=g.TabRollen.getAic(TabBedingungen.getS(iPosB, "vergleichswert"));
                                  VecAIC.addElement(iRolle);
                                  int iPosBE=Vec2.size()-1;
                                  Vec2.setElementAt(new BewEig(0,Sort.geti(Vec2,iPosBE),iRolle,null),iPosBE);
                                }
			}
            else
            {
              String sVergl=TabBedingungen.getS(iPosB,"vergleichswert");
              if (sVergl.startsWith("*meine"))
              {
                //String sVg = TabBedingungen.getS(iPosB,"vergleichswert");
                g.checkMeine(sVergl, iEig,sDatentyp);
                VecAIC.addElement(sVergl + iEig);
              }
              else if (sVergl.startsWith("*Qry von"))
              {
                String sVg = "*Qry von " + iEig + "*";
                if (VecQry == null)
                  VecQry = new Vector<String>();
                VecQry.addElement(sVg);
                VecAIC.addElement(sVg);
              }
              else if (sVergl.startsWith("*Joker von"))
              {
                String sVg = sVergl.substring(0,sVergl.length()-1) + "," + iEig + "*";
                if (VecQry == null)
                  VecQry = new Vector<String>();
                VecQry.addElement(sVg);
                VecAIC.addElement(sVg);
              }
              else if (sVergl.startsWith("*JokerStt") || sVergl.startsWith("*JokerVec") || sVergl.startsWith("*UseVec") || sVergl.startsWith("*Reserve1"))
              {
                String sVg = sVergl;
                if (sVg.length() == 10 && !sVergl.startsWith("*UseVec") || sVg.length() == 8 && sVergl.startsWith("*UseVec"))
                {
                  Integer Int = (Integer)VecToStt(Vec2);
                  int iJStt = Int.intValue();
                  if (iJStt == 0)
                  {
                    iJStt = iStt;
                    Int = new Integer(iJStt);
                  }
                  //VecAIC.addElement("*Joker" + (TabBedingungen.getS(iPosB,"vergleichswert").equals("*JokerVec*") ? "Vec " : "Stt ") + g.TabStammtypen.getKennung(iJStt) + "*");
                  sVg = (sVergl.startsWith("*UseVec") ? sVg.substring(0, 7):sVg.substring(0, 9)) + " " + g.TabStammtypen.getKennung(iJStt) + "*";
                }
                //g.progInfo("Vergleichswer="+sVg);
                g.checkJoker(sVg);
                VecAIC.addElement(sVg);
              }
              else
              {
            	if (sVergl != null && (sVergl.startsWith("$") || sVergl.startsWith("@")))
            		g.checkJoker(sVergl);
                VecAIC.addElement(sVergl);
              }
            }
            VecAIC.addElement(TabBedingungen.getI(iPosB,"bbits") & BUSE);
            //g.progInfo("Load_Element: VecAIC="+VecAIC);
			/*{
				String s2=TabBedingungen.getS(iPosB,"vergleichswert");
				VecAIC.addElement(s2);
				if (sDatentyp.equals("Anlage"))
					sVergleich = bBezeichnungLaden ? SQL.getString(g,"select Kennung from Code where aic_Code="+s2):s2;
				else if (sDatentyp.equals("Benutzer"))
					sVergleich = bBezeichnungLaden ? SQL.getString(g,"select Kennung from Benutzer where aic_Benutzer="+s2):s2;
				else if (sDatentyp.equals("Mandant"))
					sVergleich = bBezeichnungLaden ? SQL.getString(g,"select Kennung from mandant where aic_mandant="+s2):s2;
				else if (sDatentyp.equals("Gruppe") || sDatentyp.equals("Hierarchie") || sDatentyp.equals("BewStamm") || sDatentyp.equals("BewHierarchie"))
					sVergleich = bBezeichnungLaden ? s2.equals("Joker") ? "Joker":SQL.getString(g,"select bezeichnung from stammview where aic_stamm="+s2):s2;
				else if (sDatentyp.equals("Boolean") || sDatentyp.equals("BewBoolean") || sDatentyp.equals("BewBool3"))
					sVergleich = Static.JaNein(TabBedingungen.getB(iPosB,"vergleichswert"));
				else
					sVergleich = s2;
			}*/
			//g.debugInfo("<"+sDatentyp+"/"+sVergleichsart+"/"+sVergleich+">");
			//JCOutlinerNode Nod = new JCOutlinerFolderNode(s+" "+g.TabBegriffe.getS("Bezeichnung")+" "+sVergleich,(JCOutlinerFolderNode)OutNod);
			JCOutlinerNode Nod = new JCOutlinerFolderNode("xx",(JCOutlinerFolderNode)OutNod);
			Nod.setUserData(VecAIC);
			return Nod;
		}
		else
			return OutNod;
	}


	// ----------------------------------------------------------------------------------------------------------------

	public static boolean is(long l,long lArt)
	{
		return (l & lArt) == lArt;
	}

	/*public static String FullCheck(Vector VecSpalten,Vector Vec,String sGroup)
	{
		//g.progInfo("Abfrage: FullCheck:");
		String s=Check(VecSpalten,Vec,"");
                return s;
		//return s.equals("") ? s:" group by "+sGroup+s;
	}*/

	/*private static String G(String s2,String s,boolean b)
	{
		return false?s2+"(p2."+s+")":s;
	}

	private static String G3(String s2,String s,boolean b)
	{
		return false?s2+"(p2."+s+")":"p2."+s;
	}

	public static String G2(String s2,String s,boolean b)
	{
		return false?s2+"("+s+") "+s:s;
	}*/

	public static void contains(Vector<Object> VecAlle,Vector VecNeu,int iNum)
	{
		if (VecNeu==null)
			return;
                //System.out.println("contains:"+iNum+"|"+VecNeu+"/"+VecAlle);
                if (iNum>0)
                {
                  VecAlle.addElement(""+iNum);
                  return;
                }
		//System.out.println("contains:"+VecAlle+"/"+VecNeu);
		if (VecNeu.size()==1 ? VecAlle.contains(VecNeu.elementAt(0)):VecAlle.toString().indexOf(VecNeu.toString())>-1)
			return;
		//System.out.println(VecNeu+" nicht enthalten in "+VecAlle);
                if (!VecAlle.contains(VecNeu.elementAt(0)))
			VecAlle.addElement(VecNeu.elementAt(0));
		for(int i2=1;i2<VecNeu.size();i2++)
		{
			Vector<Object> Vec=new Vector<Object>();
			for(int i3=0;i3<=i2;i3++)
				Vec.addElement(VecNeu.elementAt(i3));
			//System.out.println("Vec="+Vec);
			if (VecAlle.toString().indexOf(Vec.toString())==-1)
				VecAlle.addElement(Vec);
		}
		//System.out.println("contains-Ende:"+VecAlle);
	}

	/*public static boolean PeriodenCheck(Global g,int iBit)
	{
		//g.progInfo("Abfrage: PeriodenCheck:");
		if ((iBit&cstPeriode)>0)
		{
			if (VecEigPer == null)
				VecEigPer = new Vector();
			g.TabEigenschaften.setInhalt("Periode",Boolean.TRUE);
			VecEigPer.addElement(g.TabEigenschaften.getInt("Aic"));
			g.testInfo("VecEigPer="+VecEigPer);
			return true;
		}
		else
			return false;
	}*/
	
	public static String getEigenschaftBezeichnung(Global g,Object rVec)
	{
		return getEigenschaftBezeichnung(g,rVec,g.getSprache());
	}

	public static String getEigenschaftBezeichnung(Global g,Object rVec,int iSpr)
	{
		//g.progInfo("getEigenschaftBezeichnung:"+rVec);
		String s="";//rVec.elementAt(0);
		boolean bFirst=true;
		//g.TabEigenschaften.push();
		for (int i=0;i<((Vector)rVec).size();i++)
		{
			if (bFirst)
				bFirst=false;
			else
				s=s+'.';
                        Object Obj=((Vector)rVec).elementAt(i);
			int iEig =getEig(Obj);
                        int iBew=Obj instanceof BewEig ? ((BewEig)Obj).Bew():0;
                        int iRolle=Obj instanceof BewEig ? ((BewEig)Obj).Rolle():0;
			int iPos=g.TabEigenschaften.getPos("Aic",Math.abs(iEig));
			if (iEig<0)
				s=s+'#';
                        if (iBew>0)
                          s+="*";
                        if (iSpr!=g.getSprache())
                          s+=SQL.getString(g, "select bezeichnung from bezeichnung where aic_tabellenname="+g.TabTabellenname.getAic("EIGENSCHAFT")+" and aic_fremd=? and aic_sprache="+iSpr, ""+Math.abs(iEig));
                        else if ((g.TabEigenschaften.getI(iPos,"bits") & Global.cstTakeBez)==0)
                          s+=g.TabEigenschaften.getS(iPos,"Bezeichnung");
                        else
                        {
                          int iStt2=g.TabEigenschaften.getI(iPos,"aic_stammtyp");
                          int iPosS=iStt2>0 ? g.TabStammtypen.getPos("Aic",iStt2):-1;
                          if (iPosS>=0)
                            s +=g.TabStammtypen.getS(iPos,"Bezeichnung");
                          else
                            g.printError("Abfrage.getEigenschaftBezeichnung: Stammtyp "+iStt2+" von Eigenschaft "+g.TabEigenschaften.getS(iPos,"Bezeichnung")+" nicht gefunden");
                        }
                        if (iBew>0)
                          s+="-"+g.TabErfassungstypen.getBezeichnungS(iBew);
                        if (iRolle>0)
                          s+=" ("+g.TabRollen.getBezeichnungS(iRolle)+")";
			//g.progInfo(""+i+':'+s+'/'+iEig);
		}
		//g.TabEigenschaften.pop();
		return s;
	}

	public void checkAbfJoker()
	{
	  if (TabJoker != null)
	  {
	    TabJoker.moveFirst();
	    if (!TabJoker.isNull("Komponente"))
	      return;
	    //g.fixInfo(" ---- checkAbfJoker:"+TabJoker.getAnzahlElemente(null));
	    TabSp.push();
	    for (;!TabJoker.eof();TabJoker.moveNext())
	    {
	      for(TabSp.moveFirst();!TabSp.eof();TabSp.moveNext())
	      {
		Vector VecEig = (Vector)TabSp.getInhalt("Vec");
		if (VecEig.size()==1 && Sort.geti(VecEig, 0)==TabJoker.getI("Eigenschaft"))
		{
		  TabJoker.setInhalt("Abfrage",TabSp.getInhalt("Filter"));
		  if (!TabSp.getS("Bezeichnung").equals(""))
		    TabJoker.setInhalt("Bez",TabSp.getS("Bezeichnung"));
		}
	      }
	    }
	    TabSp.pop();
	    //TabJoker.showGrid("TabJoker");
	  }
	}

	/*public Tabellenspeicher getTabJoker()
	{
	  return TabJoker;
	}*/
	
	private String getVBV(String sKZ)
	{
		if (sKZ==null)
			return "";
		if (sKZ.equals("von"))
			return "v";
		if (sKZ.equals("bis"))
			return "b";
		if (sKZ.equals("Dauer"))
			return "d";
		return "";
	}

	@SuppressWarnings("unchecked")
	public Tabellenspeicher getAnzeigeSpalten(Vector rVecSpalten)
	{
		//g.progInfo("Abfrage.getAnzeigeSpalten:"+rVecSpalten);
		//if (g.Prog())
		//	TabSp.showGrid("TabSp");
		Tabellenspeicher Tab = new Tabellenspeicher(g,new String[] {"Bezeichnung","Kennung","Kennung2","Datentyp","Ergebnisart","Auswertformat","Format","Laenge","Stamm","Stt","Component"/*,"Locked"*/,"Ausrichtung","bits","Nummer","Filter","Faktor","Gruppe","Calc","mehr","Eig2","Min","Max","x","y","w","h","Stil","bits2","bits3","Anzeige","Tooltip","Aic","Farbe","rel","Sub1","Sub2","Sub3"});
		if (rVecSpalten==null || !bSpalten || TabSp.isEmpty())
			return Tab;
		int iGruppen=0;
		for(TabSp.moveFirst();!TabSp.eof();TabSp.moveNext())
			if (is(TabSp.getI("bits"),cstGruppiert) && TabSp.getI("sortiert")==0)
				iGruppen++;
		//g.progInfo("Zusätzliche Sortierungen:"+iGruppen);
		if(iGruppen>0)
		{
			int iGruppe=0;
			for(TabSp.moveFirst();!TabSp.eof();TabSp.moveNext())
			{

				if(TabSp.getI("sortiert")>0)
					TabSp.setInhalt("sortiert",TabSp.getI("sortiert")+iGruppen);
				if (is(TabSp.getI("bits"),cstGruppiert) && TabSp.getI("sortiert")==0)
				{
					iGruppe++;
					TabSp.setInhalt("sortiert",iGruppe);
				}
			}
		}
		///ClearVecEigPer(g);
		//Vector VecAbfSpalten = NodSpalten.getChildren();
		//g.progInfo("Abfrage-VecAbfSpalten="+VecAbfSpalten);
		//if (VecAbfSpalten != null)
		//	for(int i=0;VecAbfSpalten.size()>i;i++)
		//g.progInfo("von "+TabSp.getVecSpalte("Bezeichnung"));
			for(TabSp.moveFirst();!TabSp.eof();TabSp.moveNext())
			{
				//Vector VecEig = (Vector)((JCOutlinerNode)VecAbfSpalten.elementAt(i)).getUserData();
                              Vector VecEig = (Vector)TabSp.getInhalt("Vec");
                              if (VecEig != null && VecEig.size()>0 && !TabSp.getS("Status").equals("Del") && (bAlleSpalten || !(is(TabSp.getI("bits2"),cstWeg) && is(TabSp.getI("bits"),cstUnsichtbar))))
                              {
                                int iEig=Math.abs(Sort.geti(VecEig.elementAt(VecEig.size()-1)));
				int iPos=g.TabEigenschaften.getPos("Aic",iEig);
                                String sDatentyp = g.TabEigenschaften.getS(iPos,"Datentyp");
//                                g.fixtestError("Spalte "+TabSp.getPos()+": "+sDatentyp+"/"+iPos+"/"+VecEig);
                                boolean bRel=VRel.contains(sDatentyp);//sDatentyp.equals("BewZahl2") || sDatentyp.equals("BewMass2") || sDatentyp.equals("BewWaehrung2");
                                contains(rVecSpalten,VecEig,TabSp.getInhalt("Gruppe") == null && (TabSp.getI("bits")&cstPeriode)==0 && !bRel ? 0:TabSp.getI("Nummer"));
				if (bAlleSpalten || g.bImport || is(TabSp.getI("bits"),cstAnzeigen) && ((iBits&cstLeer)>0 || is(TabSp.getI("bits"),cstSpLeer) || (TabSp.getI("bits")&cstMobil)==0 && g.existsEigenschaft(iEig)))
				{

					//g.debugInfo("Vec2="+Vec2);
					//g.TabEigenschaften.posInhalt("Aic",Tabellenspeicher.geti(ObjEig));
					//PeriodenCheck(g,TabSp.getI("bits"));
					/*
					boolean bPeriode=(TabSp.getI("bits")&cstPeriode)>0;
					if (bPeriode)
					{
						if (VecEigPer == null)
							VecEigPer = new Vector();
						g.TabEigenschaften.setInhalt("Periode",Boolean.TRUE);
						VecEigPer.addElement(ObjEig);
					}
					*/
					//String sDatentyp = g.TabEigenschaften.getS("Datentyp");
					//g.progInfo("Datentyp="+sDatentyp+"/"+ObjEig);
					///g.TabEigenschaften.setInhalt("Art",TabSp.getInhalt("cod_aic_code"));

					String sAic= VecToKennung(VecEig);
          if (!TabSp.isNull("aic_stammtyp"))
            iBits=iBits | cstHierarchie;
          int iBits2=TabSp.getI("bits2");
          int iBits3=TabSp.getI("bits3");
          if (!TabSp.isNull("aic_stammtyp"))
            iBits2=iBits2 | cstStt;
          if ((iBits&cstHierarchie)>0 && sDatentyp.equals("Hierarchie") && !TabSp.isNull("aic_stammtyp") && (TabSp.getI("bits")&cstEditierbar)==0)
          {
            sAic = getVecHierarchie(VecEig).elementAt(0) + "_H" + TabSp.getI("aic_stammtyp");
          }
          else if (bRel)
            sAic+="_"+TabSp.getI("Rel");
					Tab.addInhalt("Bezeichnung",Static.beiLeer(TabSp.getS("Bezeichnung"),getEigenschaftBezeichnung(g,VecEig)));
					String sKZ=TabSp.getS("Stil");
                    String sAnzeige = TabSp.getI("Anzeige")==0 ? null:g.TabCodes.getKennung(TabSp.getI("Anzeige"));
					Tab.addInhalt("Kennung",(sAnzeige==null ? sDatentyp.equals("Mehrfach") && iBew==0 ? "A" : (sDatentyp.equals("Eintritt") || sDatentyp.equals("BewDatum")) && (TabSp.getI("bits")&cstBild)>0 ? "M":
					  sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("BewVon_Bis") ? getVBV(sKZ):sDatentyp.equals("Protokoll") ? "V":"E":
                                          sAnzeige.equals("date")?"A":sAnzeige.equals("timestamp")?"T":sAnzeige.equals("zone")?"Z":"X")+sAic);
					Tab.addInhalt("Kennung2",sAic);
					Tab.addInhalt("Datentyp",sDatentyp);
					Tab.addInhalt("Ergebnisart",TabSp.getInhalt("cod_aic_code"));
					Tab.addInhalt("Auswertformat",TabSp.getInhalt("aic_code"));
					Tab.addInhalt("Format",TabSp.getI("aic_begriff")==0 ? g.TabEigenschaften.getS(iPos,"Format"):g.TabBegriffe.getKennung(TabSp.getI("aic_begriff")));
                                        int iEigL=g.TabEigenschaften.getI(iPos,"Laenge");
                                        //g.progInfo("getAnzeigeSpalten "+g.TabEigenschaften.getS("Bezeichnung")+":"+iEigL+"/"+getLaenge(TabSp)+"->"+(getLaenge(TabSp)==0?(iEigL<0?501:iEigL):TabSp.getI("Laenge")&511));
					//int iTest=getLaenge(TabSp)==0?(iEigL<0?501:iEigL)+(TabSp.getI("Laenge")&((1024*1024-1)*512)):TabSp.getI("Laenge");
                                        //g.progInfo("-> "+Integer.toHexString(iTest));
                                        Tab.addInhalt("Laenge",getLaenge(TabSp)==0 ? (iEigL<0?501:iEigL)+TabSp.getI("Laenge") : TabSp.getI("Laenge"));
					Tab.addInhalt("Ausrichtung",TabSp.getInhalt("Ausrichtung"));
					Tab.addInhalt("Stamm",TabSp.getI("Mass")==0?g.TabEigenschaften.getInhalt("aic_stamm",iPos):TabSp.getInhalt("Mass"));
					Tab.addInhalt("bits",TabSp.getI("bits")|((g.TabEigenschaften.getI(iPos,"bits")&0x00ff)*0x10000));
								
          Tab.addInhalt("Stt",TabSp.isNull("aic_stammtyp") ? VecToStt(VecEig):TabSp.getInhalt("aic_stammtyp"));
					Tab.addInhalt("Component",null);	// für AU_Table
					//Tab.addInhalt("Locked",new Integer(VecEig.size()==1 && is(TabSp.getI("bits"),cstEditierbar) ? 0 : 2));
					Tab.addInhalt("Nummer",TabSp.getInhalt("Nummer"));
					Tab.addInhalt("Filter",TabSp.getI("Filter")==0 && (iBits&cstTTO)==0 ?g.TabEigenschaften.getInhalt("Abfrage",iPos):TabSp.getInhalt("Filter"));
					Tab.addInhalt("Faktor",TabSp.getInhalt("Faktor"));
          Tab.addInhalt("Min",TabSp.getInhalt("Min")==null /*&& g.TabEigenschaften.getI(iPos,"Min")!=0*/ ? g.TabEigenschaften.getInhalt("Min",iPos):TabSp.getInhalt("Min"));
          Tab.addInhalt("Max",TabSp.getInhalt("Max")==null /*&& g.TabEigenschaften.getI(iPos,"Max")!=0*/ ? g.TabEigenschaften.getInhalt("Max",iPos):TabSp.getInhalt("Max"));
          Tab.addInhalt("x",TabSp.getInhalt("x"));
          Tab.addInhalt("y",TabSp.getInhalt("y"));
          Tab.addInhalt("w",TabSp.getInhalt("w"));
          Tab.addInhalt("h",TabSp.getInhalt("h"));
          Tab.addInhalt("Stil",TabSp.getInhalt("Stil"));
          Tab.addInhalt("Gruppe",TabSp.getInhalt("Gruppe"));
          Tab.addInhalt("Calc",TabSp.getInhalt("Calc"));
          Tab.addInhalt("bits2",iBits2);
          Tab.addInhalt("bits3",iBits3);
          Tab.addInhalt("Anzeige",TabSp.getInhalt("Anzeige"));
          Tab.addInhalt("Tooltip",TabSp.isNull("TT") && (TabSp.getI("bits")&cstEditierbar)>0 ? g.TabEigenschaften.getInhalt("Tooltip",iPos):TabSp.getInhalt("TT"));
          Tab.addInhalt("Aic",TabSp.getInhalt("aic_spalte"));
          Tab.addInhalt("Farbe",TabSp.getInhalt("aic_Farbe"));
          Tab.addInhalt("rel",TabSp.getInhalt("rel"));
          Tab.addInhalt("Sub1",TabSp.getInhalt("Sub1"));
          Tab.addInhalt("Sub2",TabSp.getInhalt("Sub2"));
          Tab.addInhalt("Sub3",TabSp.getInhalt("Sub3"));
          // if ((iBits2&cstDtJoker)>0)
          //   iDtJ=Tab.getPos("Aic",TabSp.getInhalt("aic_spalte"));
					boolean bGruppe=TabSp.getInhalt("Gruppe") != null;
					if (bGruppe || ((TabSp.getI("bits")&cstPeriode)>0 || (TabSp.getI("bits2")&cstVorZR)>0)  && g.getVecPer(iVB)!=null)
					{
						Vector<Object> Vec=new Vector<Object>();
                                                if ((TabSp.getI("bits2")&cstVorZR)>0)
                                                {
                                                  Vec.addElement("vor");
                                                  Vec.addElement("");
                                                }
                                                else
                                                {
                                                  boolean bGanzesJahr=(TabSp.getI("bits2") & cstGanzesJahr) > 0;
                                                  if (bGanzesJahr)
                                                    bRefresh=true;
                                                  int iAnz = bGruppe ? ((Vector)TabSp.getInhalt("Gruppe")).size() : bGanzesJahr ? 12:PerAnz();//Zeitraum.VecPerioden.size() - 1;
                                                  for(int i = 0; i < iAnz; i++)
                                                    Vec.addElement((bGruppe ? "g" + TabSp.getInhalt("Nummer") + "_" : "p") + i);
                                                }
						Tab.addInhalt("mehr",Vec);
					}
					else
						Tab.addInhalt("mehr",null);
					Tab.addInhalt("Eig2",(iBits2&cstKeinOhr)==0 ? g.TabEigenschaften.getInhalt("Eig2",iPos):null);
				}
                              }
			}
		//if (g.Prog())
		//	Tab.showGrid("TabSpalten");
		//g.progInfo("->"+Tab.getVecSpalte("Bezeichnung"));
		return Tab;
	}

	public String Sortiert()
	{
		if (TabSp==null)
		{
			g.progInfo("Abfrage.Sortiert ohne TabSp aufgerufen!!");
			return "";
		}
		//g.progInfo("Sortiert");
//		g.fixtestError("Sortiert mit "+sDefBez);
			//Tabellenspeicher Tab=(Tabellenspeicher)Nod.getUserData();
			//Tab.showGrid("sortiert");
			String s="";
            String sOld="";
			TabSp.sort("sortiert",true);
			// int i=1;
			for(TabSp.posNull("sortiert",false);!TabSp.out();TabSp.moveNext())
			// while(TabSp.posInhalt("sortiert",i))
			{
				//Vector Vec=(Vector)((JCOutlinerNode)TabSp.getInhalt("Node")).getUserData();
				Vector Vec=(Vector)TabSp.getInhalt("Vec");
				int iPos=g.TabEigenschaften.getPos("Aic",Tabellenspeicher.geti(Vec.elementAt(Vec.size()-1)));
				String sDatentyp=g.TabEigenschaften.getS(iPos,"Datentyp");
        // g.fixtestError("Sort"+i+":"+sDatentyp+":"+TabSp.getI("sortiert"));
//				g.fixtestError(i+":"+sDatentyp+"/"+Vec);
                                //g.progInfo("Sortiert:"+sDatentyp);
				char c= sDatentyp.equals("BewVon_Bis") || sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") ? 'v':'e';
                                if ((sDatentyp.equals("Eintritt") || sDatentyp.equals("BewDatum")) && (TabSp.getI("bits")&cstBild)>0)
                                  c='m';
        if (sDatentyp.equals("Text"))
          g.fixtestError("Datentyp Text nicht sortiert bei Eigenschaft "+g.TabEigenschaften.getS(iPos,"Kennung"));
        else if (TabSp.getI("sortiert") != 0 && !TabSp.getS("Status").equals("Del") && ((TabSp.getI("bits2")&Abfrage.cstWeg)==0 || TabSp.getI("bBits")>0))
          {
            String s2=(iBits&cstHierarchie)>0 && sDatentyp.equals("Hierarchie") && !TabSp.isNull("aic_stammtyp")?
                getVecHierarchie(Vec).elementAt(0)+"_H"+TabSp.getI("aic_stammtyp"):VecToKennung(TabSp.getInhalt("Vec"));
            String s4=sDatentyp.equals("BewStamm") || sDatentyp.equals("Gruppe") ? ",v"+s2:"";
            String s3=sDatentyp.endsWith("Waehrung") || sDatentyp.endsWith("Mass") || sDatentyp.endsWith("Boolean") ||
                sDatentyp.equals("Double") || sDatentyp.equals("BewZahl") ? g.order(c + s2):
                sDatentyp.equals("BewStamm") || sDatentyp.startsWith("String") ? g.orderS(c + s2):
                c!='m' && (sDatentyp.equals("Eintritt") || sDatentyp.startsWith("Austritt") || sDatentyp.startsWith("EinAustritt") ||
                sDatentyp.startsWith("BewDatum") || sDatentyp.startsWith("BewDatum2") || sDatentyp.startsWith("Datum") ||
                sDatentyp.startsWith("Zeit") || sDatentyp.startsWith("entfernt")) ? g.orderD(c + s2):c + s2;
            if (!s2.equals(sOld))
              s = s + (s.equals("") ? " order by " : ",") + s3 + (is(TabSp.getI("bits"), cstSortDesc) ? " desc" : "")+s4;
            sOld=s2;
          }
				// i++;
			}
      TabSp.sort("Reihenfolge",true);
			//g.progInfo("Sortiert:"+s);
//			if (!Static.Leer(s))
//				g.fixtestError(sDefBez+" sortiert:"+s);
			return s;

	}
	
	public int SpToEig(int iSp)
	{
		int iPos=TabSp.getPos("aic_spalte",iSp);
		if (iPos<0)
			return iPos;
		else
		{
			Vector VecEig=(Vector)TabSp.getInhalt("Vec",iPos);
            return g.TabEigenschaften.getPos("Aic",Tabellenspeicher.geti(VecEig.lastElement()));
		}
	}

	public static int getEig(Object Obj)
	{
          return Obj instanceof BewEig ? ((BewEig)Obj).Eig():Sort.geti(Obj);
	}

	public static String VecToKennung(Object Obj)
	{
		String s= Obj == null ? "":Obj instanceof Vector ? EigElement(((Vector)Obj).elementAt(0)):EigElement(Obj);
		if (Obj instanceof Vector)
			for(int i=1;i<((Vector)Obj).size();i++)
				s=s+"_"+EigElement(((Vector)Obj).elementAt(i));
		return s;
	}

	public Object VecToStt(Vector Vec)
	{
		//g.TabEigenschaften.push();
                int iPos=g.TabEigenschaften.getPos("aic",getEig(Vec.elementAt(Vec.size()-1)));
                //g.progInfo("VecToStt("+Vec+"):"+iPos);
                for(int i=Vec.size()-1;i>=0 && iPos>=0 && g.TabEigenschaften.getI(iPos,"aic_stammtyp")==0;i--)
                  if (i>0)
                    iPos=g.TabEigenschaften.getPos("aic",getEig(Vec.elementAt(i-1)));
		//g.progInfo("VecToStt("+Vec+"):"+g.TabEigenschaften.getS(iPos,"Kennung"));
		Object ObjRet=iPos<0 ?null:g.TabEigenschaften.getInhalt("aic_stammtyp",iPos);
		//g.TabEigenschaften.pop();
		return ObjRet;
	}

	private Vector NodToVec(Object Obj) // für Horizontale Auflösung
	{
		Vector<Object> Vec=new Vector<Object>();
		Vector VecChildren = ((JCOutlinerFolderNode)Obj).getChildren();
		for(int i2=0; VecChildren!=null && VecChildren.size()>i2;i2++)
                  Vec.addElement(((Vector)((JCOutlinerNode)VecChildren.elementAt(i2)).getUserData()).elementAt(0));
		return Vec;
	}

        private int HA_Eig(Object Obj)
        {
          Vector VecChildren = ((JCOutlinerFolderNode)Obj).getChildren();
          return Sort.geti(((JCOutlinerNode)VecChildren.elementAt(0)).getUserData(),2);
        }

        public Tabellenspeicher checkNurErste(Tabellenspeicher TabDaten,Tabellenspeicher TabSpalten, Tabellenspeicher Tab)
        {
          boolean bCalc=TabSpalten.exists("Abfrage");
          int iPos=TabSpalten.getPos();
          if (bCalc)
          {
            TabSpalten.push();
            for(TabSpalten.posInhalt("Abfrage",iAbfrage);!TabSpalten.eof() && TabSpalten.getI("Abfrage")==iAbfrage;TabSpalten.moveNext())
              if ((TabSpalten.getI("bits2")&(cstErgaenzen+cstNurErste))>0)
        	iPos=TabSpalten.getPos();
            g.progInfo(" ------------------  iPos = "+iPos+", Stt = "+TabSpalten.getI(iPos,"STT")+" / "+iStt);
            TabSpalten.pop();
          }
          if ((TabSpalten.getI(iPos,"bits2")&(cstErgaenzen+cstNurErste))>0 )
          {
            String sGleiche=null;
            if ((TabSpalten.getI(iPos,"bits2")&(cstNurErste))>0)
            {
              TabSpalten.push();
              if (bCalc) TabSpalten.posInhalt("Abfrage",iAbfrage); else TabSpalten.moveFirst();
              for(;!TabSpalten.eof() && (!bCalc || TabSpalten.getI("Abfrage")==iAbfrage);TabSpalten.moveNext())
                if ((TabSpalten.getI("bits2")&(cstSpGleiche))>0)
                  sGleiche=TabSpalten.getS("Kennung");
              TabSpalten.pop();
            }
            if ((Tab == null) && (TabSpalten.getI(iPos,"bits2")&cstErgaenzen)>0 )
            {
              int iStt2=TabSpalten.getI(iPos,"STT");
              if (iStt2==0)
              {
        	int iPos2=g.TabEigenschaften.getPos("Aic", TabSpalten.getI(iPos,"Eig"));
        	iStt2=g.TabEigenschaften.getI(iPos2, "aic_stammtyp");
        	//g.progInfo(" ------------------  iPos2 = "+iPos2+", Stt2 = "+iStt2+" / "+iStt);
              }
              Tab=new Tabellenspeicher(g,"select aic_stamm,bezeichnung from stammview where "+
        	  (iStt2==iStt && iRolle>0 ? "aic_rolle="+iRolle :"aic_stammtyp="+iStt2)+" order by bezeichnung",true);
            }
            Tabellenspeicher TabDaten2=TabDaten;
            TabDaten=new Tabellenspeicher(g,TabDaten2.VecTitel);
            String sVg=bCalc ? TabSpalten.getS(iPos,"Kennung"):"v"+TabSpalten.getS(iPos,"Kennung2");
            if (!TabDaten2.exists(sVg)) sVg="e"+TabSpalten.getS(iPos,"Kennung2");
            String sNeu=TabSpalten.getS(iPos,bCalc? "Kennung3":"Kennung");
            //g.progInfo("sVg="+sVg+", sNeu="+sNeu+",sGleiche="+sGleiche);
            if (Tab==null)
              for (TabDaten2.moveFirst();!TabDaten2.eof();)
              {
        	String iVg=TabDaten2.getS(sVg);
        	if (sGleiche==null && (TabSpalten.getI(iPos,"bits2")&(cstNurErste))>0)
                  TabDaten.copyLine(TabDaten2);
                else
                {
                  Object Obj=sGleiche==null ? null:TabDaten2.getInhalt(sGleiche);
                  while((sGleiche==null || Static.Gleich(Obj,TabDaten2.getInhalt(sGleiche))) && !TabDaten2.eof() && iVg.equals(TabDaten2.getS(sVg))) {
                    TabDaten.copyLine(TabDaten2);
                    TabDaten2.moveNext();
                  }
                }
                while (!TabDaten2.eof() && iVg.equals(TabDaten2.getS(sVg)))
                  TabDaten2.moveNext();
              }
            else
             for (Tab.moveFirst();!Tab.eof();Tab.moveNext())
             {
              if (TabDaten2.posInhalt(sVg, Tab.getI("aic_stamm")))
              {
                if (sGleiche==null && (TabSpalten.getI(iPos,"bits2")&(cstNurErste))>0)
                  TabDaten.copyLine(TabDaten2);
                else
                {
                  Object Obj=sGleiche==null ? null:TabDaten2.getInhalt(sGleiche);
                  while((sGleiche==null || Static.Gleich(Obj,TabDaten2.getInhalt(sGleiche))) && !TabDaten2.eof() && TabDaten2.getI(sVg) == Tab.getI("aic_stamm")) {
                    TabDaten.copyLine(TabDaten2);
                    TabDaten2.moveNext();
                  }
                }
              }
              else
              {
        	TabDaten.newLine();
        	TabDaten.setInhalt(sVg, Tab.getI("aic_stamm"));
        	TabDaten.setInhalt(sNeu, Tab.getS(1));
              }
             }
            //TabDaten.showGrid("TabDaten");
            return TabDaten;
          }
          else
            return TabDaten;
        }

        /*public void checkDialog(Tabellenspeicher TabDaten)
        {
          if (TabSp==null)
            return;
          TabSp.push();
          for(TabSp.moveFirst();!TabSp.eof();TabSp.moveNext())
          {
            if ((TabSp.getI("bits")&cstDialog)>0)
            {
              if (TabDaten.out())
                TabDaten.newLine();
              Vector VecEig=(Vector)TabSp.getInhalt("Vec");
              String sAic= VecToKennung(VecEig);
              int iEig=Sort.geti(VecEig.lastElement());//((Integer)VecEig.lastElement()).intValue();
              Object Obj=g.getDialog(iEig);
              if (Obj==null)
              {
                g.fixtestInfo("Lokale Eingabe für "+iEig+" ist null");
              }
              else if (Obj instanceof Combo)
              {
                if (((Combo)Obj).getAic()>0)
                {
                  TabDaten.setInhalt("E" + sAic, ((Combo)Obj).getBezeichnung());
                  TabDaten.setInhalt("V" + sAic, ((Combo)Obj).getAic());
                }
              }
              else if (Obj instanceof VonBis)
              {
                TabDaten.setInhalt("V"+sAic,((VonBis)Obj).getVon());
                TabDaten.setInhalt("B"+sAic,((VonBis)Obj).getBis());
                double dDauer=((VonBis)Obj).getDauer();
                if (dDauer != 0.0)
                  TabDaten.setInhalt("D"+sAic,new Double(dDauer));
              }
              else
                TabDaten.setInhalt("E"+sAic,Obj);
            }
          }
          TabSp.pop();
        }*/

        private int getJokerStt(String s)
        {
          //g.progInfo("getJokerStt von:"+s.substring(11,s.indexOf(",")));
          int iPos=g.TabStammtypen.getPos("Kennung",s.toUpperCase().substring(11,s.indexOf(",")));
          return iPos>=0 ? g.TabStammtypen.getI(iPos,"Sync"):0;
        }

        /*private String getCheckboxen()
        {
          Vector<Integer> Vec=new Vector<Integer>();
          for (TabJoker.moveFirst();!TabJoker.eof();TabJoker.moveNext())
            if (!TabJoker.isNull("Komponente") && ((JCheckBox)TabJoker.getInhalt("Komponente")).isSelected())
              Vec.addElement(TabJoker.getInt("Stamm"));
          return Vec.isEmpty() ? ">0":Static.SQL_in(Vec);
        }

        private String getRadiobutton()
        {
          int i=23;
          for (TabJoker.moveFirst();!TabJoker.eof();TabJoker.moveNext())
            if (!TabJoker.isNull("Komponente") && ((JRadioButton)TabJoker.getInhalt("Komponente")).isSelected())
              i=TabJoker.getInt("Stamm");
          return ""+i;
        }*/

        private String replaceCbxRbt(String s)
        {
          //g.fixInfo("---------------------------");
          //g.fixInfo("----------- replaceCbxRbt:"+TabJoker.getAnzahlElemente(null));
          //TabJoker.showGrid();
          for (TabJoker.moveFirst();!TabJoker.eof();)
          {
            String sK=TabJoker.getS("Kennung");
            boolean bCbx=TabJoker.getB("Cbx");
            //int iEig=TabJoker.getI("Eigenschaft");
            //g.fixInfo("----------- replaceCbxRbt1:"+sK+"/"+bCbx+"/"+TabJoker.getI("Eigenschaft"));
            Vector<Integer> Vec=new Vector<Integer>();
            int iS=23;
            for (;!TabJoker.eof() && TabJoker.getS("Kennung").equals(sK);TabJoker.moveNext())
            {
              //g.fixInfo("----------- replaceCbxRbt2:"+TabJoker.getS("Kennung")+"/"+TabJoker.getPos());
              if (!TabJoker.isNull("Komponente"))
              {
        	if (bCbx && ((JCheckBox)TabJoker.getInhalt("Komponente")).isSelected())
        	  Vec.addElement(TabJoker.getInt("Stamm"));
        	else if (!bCbx && ((JRadioButton)TabJoker.getInhalt("Komponente")).isSelected())
        	  iS=TabJoker.getInt("Stamm");
              }
              else
              {
        	Vec=null;
        	iS=-1;
              }
            }
            String sRep=bCbx ? Vec==null || Vec.isEmpty() ? ">0":Static.SQL_in2(Vec):iS==-1 ? ">0":"="+iS;
            //g.fixInfo("----------- replaceCbxRbt3:"+sK+"->"+"v"+TabJoker.getI("Eigenschaft")+sRep+"/"+TabJoker.eof());
            //g.progInfo("replaceCbxRbt:"+Vec+"->"+sRep);
            s=replaceAll(s,sK, sK.substring(0, sK.length()-(bCbx ? 13:16))+sRep);
          }
          //g.fixInfo("----------- replaceCbxRbt4:");
          return s;
        }
        
        private Date getVon()
        {
        	return g.getVon(iVB); // (lBits2&cstLokZR)>0 ? dtVon:g.getVon();
        }
        
        private Date getBis()
        {
        	return g.getBis(iVB); // (lBits2&cstLokZR)>0 ? dtBis:g.getBis();
        }

        public String checkJoker(String s)
        {
        	return checkJoker(s,iBegriff);
        }
        
        private String replaceAll(String s,String sWas,String sDurch)
        {
        	g.testInfo("ersetze "+sWas+" durch "+sDurch);
        	return s.replaceAll(sWas,sDurch);
        }

        public String checkJoker(String s,int iBegBer)
        {
//          g.fixtestError("checkJoker: VecJoker="+g.VecJoker);//+" bei "+s);         
          if (s.indexOf("*ich*")>0)
            s=replaceAll(s,"\\*ich\\*",""+g.getBenutzer());
          if (s.indexOf("*meinStamm*")>0)
            s=replaceAll(s,"\\*meinStamm\\*",""+g.getStamm());
          if (s.indexOf("*aktMandant*")>0)
            s=replaceAll(s,"\\*aktMandant\\*",""+g.getMandant());
          if (s.indexOf("*Qry*")>0)
            s = replaceAll(s,"\\*Qry\\*", (iBits&cstMengen)==0 ? "" + iQry:Static.SQL_in(VecMenge));
          if (s.indexOf("*Checkbox*")>0 || s.indexOf("*Radiobutton*")>0)
            s = replaceCbxRbt(s);
          /*  s = s.replaceAll("\\*Checkbox\\*",getCheckboxen());
          if (s.indexOf("*Radiobutton*")>0)
            s = s.replaceAll("\\*Radiobutton\\*",getRadiobutton());*/
          if (VecQry != null)
            for(int i=0;i<VecQry.size();i++)
            {
              String s1=VecQry.elementAt(i);
              int iMom=s1.startsWith("*Qry von") ? iQry:getJokerStt(s1);
              //g.progInfo(s1+":"+iStart+"/"+(s1.length()-1));
              String sEig=s1.substring(s1.startsWith("*Qry von") ? 8:s1.indexOf(",")+1,s1.length()-1);
              int iNeu=0;
              if (iBew==0) // z.B.: für RK_Filter_Dienstort mit einschränkung auf Dienstort und Wohnort aus Stammtyp Ort nötig
                iNeu = SQL.getInteger(g,"select sta_aic_stamm from poolview where aic_stamm="+iMom+" and aic_eigenschaft="+sEig);
              else         // z.B.: für AW_PV_Eignung / STelle /MA für Einschränkung aus Bewegung
                iNeu = SQL.getInteger(g,"select p.sta_aic_stamm from poolview p join stamm s on p.sta_aic_stamm=s.aic_stamm where p.aic_stamm=" + iMom +
                                      " and s.aic_stammtyp=" + g.EigToStt(Integer.parseInt(sEig.trim())));
              //g.progInfo(s1+","+iMom+":"+iNeu+"/"+sEig);
              if (s.indexOf(s1)>0)
                s=s.replaceAll("\\*"+s1.substring(1,s1.length()-1)+"\\*",""+iNeu);
            }
          if (g.TabMeine !=null)
            for(int iPos=0;iPos<g.TabMeine.size();iPos++)
            {
              String s1=g.TabMeine.getS(iPos,"Kennung");
              String sRep=g.TabMeine.getS(iPos,"Replace");
              if (s.indexOf(s1)>0)
                s=s.replaceAll(sRep,""+g.TabMeine.getI(iPos,"Satz"));
            }
          if (s.indexOf("*Joker*")>0)
            s=replaceAll(s,"\\*Joker\\*",""+g.iJoker);
          if (s.indexOf("*JokerZahl*")>0)
            s=replaceAll(s,"\\*JokerZahl\\*",""+g.dJoker);
          if (s.indexOf("*StringJoker*")>0)
            s=replaceAll(s,"\\*StringJoker\\*",Static.StringForSQL(g.sJoker.replaceAll("\\$", "\\\\\\$")));
          if (s.indexOf("*Reserve2*")>0)
              s=replaceAll(s,"\\*Reserve2\\*",Static.StringForSQL(g.sJoker.replaceAll("\\$", "\\\\\\$"))); //TODO ersetzen durch lokalen Such-Wert
          if (s.indexOf("*Stich2tag*")>0)
            s=replaceAll(s,"\\*Stich2tag\\*",g.DateTimeToString(g.dtStichtag));
          if (s.indexOf("*Stich3tag*")>0)
              s=replaceAll(s,"\\*Stich3tag\\*",""+Static.DateToInt(g.dtStichtag));
          if (s.indexOf("*to3day*")>0)
              s=replaceAll(s,"\\*to3day\\*",""+Static.DateToInt(new Date()));
          if (s.indexOf("*vonInt*")>0) // war *Reserve1*
              s=replaceAll(s,"\\*vonInt\\*",""+Static.DateToInt(g.getVon(iVB)));
          if (s.indexOf("*bisInt*")>0) // war *Reserve2*
              s=replaceAll(s,"\\*bisInt\\*",""+Static.DateToInt(g.getVortag(g.getBis(iVB))));
          if (s.indexOf("*von*")>0)
              s=replaceAll(s,"\\*von\\*",""+g.DateTimeToString(g.getVon(iVB)));
          if (s.indexOf("*bis*")>0)
              s=replaceAll(s,"\\*bis\\*",""+g.DateTimeToString(g.getBis(iVB)));
          if (s.indexOf("*JokerBew*")>0)
            s=replaceAll(s,"= \\*JokerBew\\*",g.getJokerBew());
          if (s.indexOf("*new2year*")>0)
          {
            DateWOD DW=DateWOD.getNewYear(getVon());
            s=replaceAll(s,"\\*new2year\\*",g.DateTimeToString(DW.toTimestamp()));
          }
          if (s.indexOf("*new3year*")>0)
          {
        	  DateWOD DW=DateWOD.getNewYear(getVon());
              s=replaceAll(s,"\\*new3year\\*",""+Static.DateToInt(DW.toTimestamp()));
          }
          for (int i=0;i<g.VecJoker.size();i++)
          {
            //int iStt=((Integer)g.VecJoker.elementAt(i)).intValue();
            String s1=g.VecJoker.elementAt(i);
            String sRep="\\*"+s1.substring(1,s1.length()-1)+"\\*";
//            g.fixtestError("checkJoker"+i+":"+s1+"/"+sRep);
            //g.progInfo("versuche zu ersetzen:"+s1);
            if (s1.startsWith("@") || s1.startsWith("$"))
            {
            	String sR=s1.startsWith("@") ? g.getVarSQL(s1):Calc.getVar(g, s1);
          	  	s=replaceAll(s,"like \\"+s1,sR);
          	  	s=replaceAll(s,"= \\"+s1,sR);
            }
//            if (s1.startsWith("@"))
//            {
//          	  s=replaceAll(s,"like \\"+s1,g.getVarSQL(s1));
//          	  s=replaceAll(s,"= \\"+s1,g.getVarSQL(s1));
//            }
//            else if (s1.startsWith("$"))
//          	  s=replaceAll(s,"\\"+s1,"'Roland'");
            else if (s1.startsWith("*JokerStt") && s.indexOf(s1)>0)
            {
              int iPos=g.TabStammtypen.getPos("Kennung", s1.substring(10, s1.length() - 1));
              int i2=iPos>=0 ? g.TabStammtypen.getI(iPos,"Sync") : 0;
              //g.progInfo(s1+":"+i2);
              //int iStt2=g.TabStammtypen.getI("Aic");
              if ((iBits&cstFremdJoker)>0)
              {
                if (iStt>0 && iStt != g.TabStammtypen.getI(iPos,"Aic"))
                {
                  iPos = g.TabStammtypen.getPos("Aic", iStt);
                  i2 = iPos >= 0 ? g.TabStammtypen.getI(iPos, "Sync") : 0;
                  //g.progInfo(g.TabStammtypen.getS("Kennung")+":"+i2);
                  if (i2 > 0)
                  {
                    Vector Vec = SQL.getVector("select sta_aic_stamm from poolview where aic_stamm=" + i2 + " and sta_aic_stamm is not null", g);
                    //g.progInfo("->" + Vec);
                    s = replaceAll(s,"= " + sRep, Static.SQL_in(Vec));
                  }
                  else
                    s = replaceAll(s,"= " + sRep, "=-1");
                }
                else
                {
                  s = replaceAll(s,"= " + sRep, "=?");
                  sBind=""+i2;
                }
              }
              else
                s = replaceAll(s,sRep, "" + i2);
            }
            else if (s1.startsWith("*JokerVec") && s.indexOf(s1)>0)
            {
              String sStt=s1.substring(10,s1.length()-1);
              int iPos=g.TabStammtypen.getPos("Kennung",sStt);
              String sIn=iPos<0 ? null:Static.SQL_in((Vector)g.TabStammtypen.getInhalt("Vec",iPos));
              if (sIn.equals(" is null ") && (lBits2&cstJVecAlle)>0)
            	  sIn="is not null";
//              g.fixtestError("*JokerVec sIn="+sIn);
              g.progInfo("ersetze:"+sStt+"/"+sRep+":"+sIn);
              s = replaceAll(s,"= "+sRep, iPos>=0 ? "" + sIn : "=0");
              s = replaceAll(s,"<> "+sRep, iPos>=0 ? sIn.equals(" is null ") ? " is not null":" not" + sIn : ">0");
            }
            else if (s1.startsWith("*UseVec") && s.indexOf(s1)>0) // Stamm-Berechtigung
            {
              String sStt=s1.substring(8,s1.length()-1);
              //g.progInfo("ersetze:"+sStt+"/"+sRep);
              int iPos=g.TabStammtypen.getPos("Kennung",sStt);
              Vector VecUse=Static.bWeb ? g.getVecStamm(iBegBer, g.TabStammtypen.getI(iPos,"Aic")):g.getVecStamm2(g.TabStammtypen.getI(iPos,"Aic"));
              // g.fixtestError("*UseVec:"+bWeb2+"/"+((lBits2&cstNoSB)>0)+"-> "+VecUse);
              s = replaceAll(s,"= "+sRep, iPos>=0 ? VecUse==null ?">0":"" + Static.SQL_in(VecUse) : "=0");
              s = replaceAll(s,"<> "+sRep, iPos>=0 ? VecUse==null ?" is null":"not" + Static.SQL_in(VecUse) : ">0");
            }
            else if (s1.startsWith("*Reserve1") && s.indexOf(s1)>0) // Favoriten
            {
              String sStt=s1.substring(10,s1.length()-1);
              //g.progInfo("ersetze:"+sStt+"/"+sRep);
              int iPos=g.TabStammtypen.getPos("Kennung",sStt);
              Vector VecFav=Favorit.get(g,g.TabStammtypen.getI(iPos,"Aic"),Favorit.FAV);//g.getVecStamm2(g.TabStammtypen.getI(iPos,"Aic"));
              s = replaceAll(s,"= "+sRep, iPos>=0 ? VecFav==null ?"<0":"" + Static.SQL_in(VecFav) : "=0");
              s = replaceAll(s,"<> "+sRep, iPos>=0 ? VecFav==null ?" is null":"not" + Static.SQL_in(VecFav) : ">0");
            }
          }
          if (VecDateJoker != null)
          {
        	  g.progInfo("VecDateJoker="+VecDateJoker);
            for(int i=0;i<VecDateJoker.size();i++)
            {
              String sRep=VecDateJoker.elementAt(i);
              String sRep2=useDiff(sRep);
              g.progInfo("VecDateJoker "+sRep+"->"+sRep2);
              s = Static.replaceString(s,sRep,sRep2);
              //g.progInfo(s);
            }
          }
//          g.fixtestError("-> checkJoker="+s);
          return s;
        }

//  public void checkCalc(Tabellenspeicher TabDaten)
//  {
//    checkCalc(TabDaten,null);
//  }

  private void checkArt(int iArt,boolean b,Tabellenspeicher TabSpalten)
  {
    if (iArt==0)
      return;
    //if (b)
    {
      int iNr=TabSp.getI("Nummer");
      int iPosS=TabSpalten.getPos("Nummer",iNr);
      g.fixtestError((iArt==1 && !b ? "unsichtbar":iArt==2 ? "zwingend":iArt==3 ? "edit":"filler")+" setzen bei "+TabSpalten.getS(iPosS,"Bezeichnung"));
      if (iArt==1 && !b) 
        TabSpalten.setInhalt(iPosS,"bits", TabSpalten.getI(iPosS,"bits")|cstUnsichtbar);
      else if (iArt==2 && b)
        TabSpalten.setInhalt(iPosS,"bits", TabSpalten.getI(iPosS,"bits")|(Global.cstAlways*0x10000));
      else if (iArt==3 && b)
        TabSpalten.setInhalt(iPosS,"bits", TabSpalten.getI(iPosS,"bits")|cstEditierbar);
      else if (iArt==4 && !b)
        TabSpalten.setInhalt(iPosS,"Datentyp", "Filler");
    }
  }

  public void checkFilter(Tabellenspeicher TabDaten,Tabellenspeicher TabSpalten)
  {
    if (TabSpalten==null)
      return;
    for (int i=0;i<TabSpalten.size();i++)
    {
      if ((TabSpalten.getI(i,"bits")&cstSpLeer)>0 && TabSpalten.getI(i,"Filter")>0)
      {
        // g.fixtestError("neuer Filter mit Leer bei Spalte "+TabSpalten.getS(i,"Bezeichnung")+" mit Filter="+TabSpalten.getI(i,"Filter"));
        ShowAbfrage AbfFilter=new ShowAbfrage(g, TabSpalten.getI(i,"Filter"), cstAbfrage);
        // AbfFilter.showResult(null);
        AbfFilter.iBits |=cstKeinStamm;
        Tabellenspeicher TabF=AbfFilter.getDaten(0, 0, null, null);
        // if (TabF != null)
        //   TabF.showGrid("TabF");
        Tabellenspeicher TabS=AbfFilter.getSpalten();
        // if (TabS != null)
        //   TabS.showGrid("TabS");
        int iPosG=TabS.getPos("Stil","Gruppe");
        int iPosD=TabS.getPos("Stil","Datum");
        int iPosB=TabS.getPos("Stil","Wert");
        String sGH=iBew>0 ? "v"+TabSpalten.getS(0,"Kennung").substring(1):"aic_stamm";
        String sG=iPosG<0 ? null:AbfFilter.iBew>0 ? "v"+TabS.getS(iPosG,"Kennung").substring(1):TabS.getS(iPosG,"Kennung");
        String sD=iPosD<0 ? null:TabS.getS(iPosD,"Kennung");
        String sEA=iPosB<0 || TabS.isNull(iPosB, "Ergebnisart") ? null:g.TabCodes.getKennung(TabS.getI(iPosB, "Ergebnisart"));
        String sDt=iPosB<0 ? null:TabS.getS(iPosB,"Datentyp");
        String sB=iPosB<0 ? null:(sDt!=null && sDt.equals("BewVon_Bis")  ? "d":"")+TabS.getS(iPosB,"Kennung");
        boolean bPeriode=(TabSpalten.getI(i,"bits")&cstPeriode)>0;
        g.fixtestError("neuer Filter bei Spalte "+TabSpalten.getS(i,"Bezeichnung")+" -> "+iPosG+"/"+iPosD+"/"+iPosB+" -> "+sGH+"/"+sG+"/"+sD+"/"+sB+" | "+sDt+(sEA==null ? "":" mit "+sEA+" Periode="+bPeriode));
        for (int iD=0;iD<TabDaten.size();iD++)
        {
          int iAic=TabDaten.getI(iD,sGH);
          if (sEA==null)
          {
            if (TabF.posInhalt(sG, iAic))
              TabDaten.setInhalt(iD,TabSpalten.getS(i,"Kennung"), TabF.getInhalt(sB));
          }
          else
          {
            int iPAnz=bPeriode ? PerAnz():1;
            for (int iP=0;iP<iPAnz;iP++)
            {
              double d=0;
              int iAnz=0;
              Date dtV=g.getVecPer(iVB).elementAt(iP);
              Date dtB=g.getVecPer(iVB).elementAt(iP+1);
              for (TabF.moveFirst();!TabF.out();TabF.moveNext())
                if (iAic==TabF.getI(sG))
                {
                  Date dt=bPeriode && sD!=null ? TabF.getDate(sD):null;
                  if (!bPeriode || dt!=null && !dt.before(dtV) && dt.before(dtB))
                  {
                    if (!TabF.isNull(sB))
                      iAnz++;
                    if (!sEA.equals("count"))
                      d+=TabF.getF(sB);
                  }
                }
              TabDaten.setInhalt(iD,TabSpalten.getS(i,"Kennung")+(bPeriode ? "p"+iP:""),sEA.equals("count") ? iAnz:d);
              // g.fixInfo("Stamm "+iAic+": "+TabSpalten.getS(i,"Kennung")+"="+d+" bei p"+iP); 
            }
          }
        }
      }
    }
  }

	public void checkCalc(Tabellenspeicher TabDaten,Tabellenspeicher TabSpalten)
	{
          	if (TabSp==null || !Abfrage.is(iBits,cstCalc))
                    return;
                //g.progInfo("checkCalc");
		TabSp.push();
		for(TabSp.moveFirst();!TabSp.eof();TabSp.moveNext())
		{
			if (!TabSp.isNull("Calc") && !TabSp.getS("Status").equals("Del") && !(is(TabSp.getI("bits2"),cstWeg) && is(TabSp.getI("bits"),cstUnsichtbar)))//(TabSp.getI("bits2")&cstWeg)==0)
			{
                          boolean bSumme= is(iBits,cstSumme);
                          boolean bPeriode=(TabSp.getI("Bits")&cstPeriode)>0;
                          Vector VecEig=(Vector)TabSp.getInhalt("Vec");
                          int iPos=g.TabEigenschaften.getPos("Aic",Tabellenspeicher.geti(VecEig.lastElement()));
                          String sDt=g.TabEigenschaften.getS(iPos,"Datentyp");
                          boolean bDate=sDt.equals("CalcDatum");
                          boolean bString=sDt.equals("CalcString");
                          boolean bBool=sDt.equals("CalcBoolean");
                          boolean bNum=sDt.equals("CalcField") || sDt.equals("CalcMass") || sDt.equals("CalcWaehrung");
                          boolean bST=false;//(TabSp.getI("bits2") & cstbeiStichtag) > 0;
                          // g.fixtestError(sDefBez+"/"+VecEig+":"+bDate+"/"+bString+"/"+bBool+"/"+bNum);
                          Tabellenspeicher TabEA=null;
                          if (bST)
                            TabEA=new Tabellenspeicher(g,"select eintritt,austritt from stammview2 where aic_stamm="+TabDaten.getI("ANR")+" and aic_rolle="+g.iRolMA,true);
                          //if (TabEA!=null)
                          //  TabEA.showGrid();
                          //g.progInfo("Calc-Datentyp="+g.TabEigenschaften.getS(iPos,"Datentyp"));
                          if (!bDate && !bString && !bBool && !bNum)
                          {
//                            g.fixtestError(sDefBez+": Spaltenberechnung ohne Calc-Spalte");
                            TabDaten.moveFirst();
                            // if (!TabDaten.out())
                            //{
                              Tabellenspeicher Tab=(Tabellenspeicher)TabSp.getInhalt("Calc");
                              int iArt=0;
                              boolean bX=true;
                              for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                              {
                                String s=((Combo)Tab.getInhalt("Operation")).getKennung();
                                String sE=Tab.isNull("Eingabe") ? null:Tab.getS("Eingabe");
                                int iP=TabSp.getPos("Nummer",Tab.getI("Spalte"));
                                // if (iP<0)
                                // {
                                //   g.fixtestError("Spalte fehlt bei Berechnung in "+TabSp.getS("Bezeichnung"));
                                //   return;
                                // }
                                // VecEig=(Vector)TabSp.getInhalt("Vec",iP);
                                String sAic= iP<0 ? null:VecToKennung((Vector)TabSp.getInhalt("Vec",iP));
                                String sVg=TabDaten.out() || iP<0 ? null:TabDaten.exists("v"+sAic) ? sE==null ? TabDaten.isNull("v"+sAic) ? null:TabDaten.getS("v"+sAic):
                                  g.getStamm(TabDaten.getI("v"+sAic), false, true):TabDaten.isNull("e"+sAic) ? null:TabDaten.getS("e"+sAic);
                                boolean b=TabDaten.out() || iP<0 ? false:Static.Gleich(sE, sVg);
                                g.fixtestError("Calc "+s+"/"+sAic+" bei "+(iP<0 ? "keine Spalte":TabDaten.out() ? "out":TabDaten.getS("e"+sAic))+"-> "+sE+"/"+sVg+"="+b);
                                if (TabSpalten==null)
                                {
                                  g.fixtestError("TabSpalten nicht übergeben");
                                  return;
                                }
                                if (s.equals("reserveOP11") || s.equals("reserveOP12") || s.equals("reserveOP13") || s.equals("reserveOP14"))
                                {
                                  checkArt(iArt,bX,TabSpalten);
                                  iArt=s.equals("reserveOP11") ? 1:s.equals("reserveOP12") ? 2:s.equals("reserveOP13") ? 3:s.equals("reserveOP14") ? 4:0;
                                  bX=b;
                                }
                                else if (s.equals("reserveOP6"))
                                  bX&=b;
                                else if (s.equals("reserveOP7"))
                                  bX|=b;
                                else if (s.equals("reserveOP8"))
                                  bX=!b;                              
                              }
                              checkArt(iArt,bX,TabSpalten);
                              // if (!bShow)
                              //   {
                              //     int iNr=TabSp.getI("Nummer");
                              //     int iPosS=TabSpalten.getPos("Nummer",iNr);
                              //     g.fixtestError("unsichtbar setzen bei "+TabSpalten.getS(iPosS,"Bezeichnung"));
                              //     TabSpalten.setInhalt(iPosS,"bits", TabSpalten.getI(iPosS,"bits")|cstUnsichtbar);
                              //   }
                            //}
                          }
                          else
                          for (int i=0;i<(bPeriode?PerAnz():1);i++)
                          {
                        	  double dV=0;
                              boolean bV=true;
                              Tabellenspeicher Tab=(Tabellenspeicher)TabSp.getInhalt("Calc");
                              for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
                              {
                            	  double d=0;
                            	  boolean b=false;
                                  String s2=null;
                                  boolean bNull=false;
                                  boolean bIf=true;
                                  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
                                  {
                                	  double d2=Tab.getF("Wert");
                                	  String s=((Combo)Tab.getInhalt("Operation")).getKennung();
                                	  String sE=Tab.isNull("Eingabe") ? null:Tab.getS("Eingabe");
                                	  //bString=s.startsWith("string");
                                	  String s3=null;
                                	  String sAic=null;
                                	  String sDatentyp=null;
                                	  if (!Tab.isNull("Spalte"))
                                	  {
                                		TabSp.push();
                                		TabSp.posInhalt("Nummer",Tab.getI("Spalte"));
                                        VecEig=(Vector)TabSp.getInhalt("Vec");
	                                    sAic= VecToKennung(VecEig);
	                                    iPos=g.TabEigenschaften.getPos("Aic",Tabellenspeicher.geti(VecEig.lastElement()));
	                                    sDatentyp = g.TabEigenschaften.getS(iPos,"Datentyp");
	                                    //String sFormat=Static.beiLeer(TabSp.getS("format"),g.TabEigenschaften.getS("Format"));
	                                    String sFormat=TabSp.getI("aic_begriff")==0 ? g.TabEigenschaften.getS(iPos,"Format"):g.TabBegriffe.getKennung(TabSp.getI("aic_begriff"));
	                                    if (bPeriode && (TabSp.getI("Bits")&cstPeriode)>0)
	                                      sAic="E"+sAic+"p"+i;
	                                    else if (TabSp.isNull("Gruppe"))
	                                      sAic=(sDatentyp.equals("BewVon_Bis")?"D":"E")+sAic;
	                                    else
	                                      sAic="E"+sAic+"G"+TabSp.getI("Nummer")+"_0";
	                                    if (bSumme)
	                                    {
	                                      int iErgArt = TabSp.getI("cod_aic_code");
	                                      int iPosC=iErgArt == 0 ? -1:g.TabCodes.getPos("Aic", iErgArt);
	                                      String sErgArt = iPosC<0 ? "sum" : g.TabCodes.getS(iPosC,"Kennung");
	                                      sAic=sErgArt+"_"+sAic;
	                                    }
	                                    TabSp.pop();
	                                    if (s.equals("reserveOP15"))
	                                    {
//	                                    	g.fixtestError("sort "+sAic);
	                                    	if (TabDaten.bof())
	                                    		TabDaten.sort(sAic, true);
	                                    	bNull=true;
	                                    }
	                                    else if (s.equals("add prev days"))
	                                    {
	                                      TabDaten.push();
	                                      TabDaten.movePrevious();
	                                      d2+=TabDaten.bof() ? 0:TabDaten.getF(sAic);
	                                      TabDaten.pop();
	                                    }
	                                    else if (s.equals("use unit"))
	                                    {
	                                    	int iMass=TabDaten.getI("v"+sAic.substring(1));
	                                    	TabDaten.setInhalt("v"+VecToKennung((Vector)TabSp.getInhalt("Vec"))+(bPeriode?"P"+i:""),iMass);
	                                    }
	                                    else if (s.equals("reserveOP3")) // is all day
	                                    {
	                                    	Date dtv=TabDaten.getDate(sDatentyp.equals("BewVon_Bis") ? "v"+sAic.substring(1):sAic);
	                                    	Date dtb=TabDaten.getDate(sDatentyp.equals("BewVon_Bis") ? "b"+sAic.substring(1):sAic);
	                                    	if (dtv==null || dtb==null)
	                                    		d=1;
	                                    	else
	                                    	{
	                                    		String sDv=new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss",DateWOD.DFS).format(dtv);
	                                    		String sDb=new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss",DateWOD.DFS).format(dtb);
	                                    		d=sDv.substring(11).equals("00:00:00") && sDb.substring(11).equals("00:00:00") ? 1:0;
	                                    	}
	                                    	//g.fixtestError("OP3:"+dt+"/"+sD+"->"+d);
	                                    }
	                                    else if (!bBool && s.equals("reserveOP8")) // if not
	                                    	bIf=!TabDaten.getB(sAic);
	                                    else if (s.equals("reserveOP9")) // if
	                                    	bIf=TabDaten.getB(sAic);
	                                    else if (s.equals("reserveOP10")) // if null
	                                    	bIf=sAic==null ? true:TabDaten.isNull(sAic);
	                                    else if (!bBool && TabDaten.isNull(sAic) && !s.equals("prev") && !s.equals("add") && !s.equals("sub") && !s.equals("mul if"))
	                                      bNull=true;
	                                    else if (bString)
	                                      s3=/*TabDaten.isNull(sAic) ? Static.sLeer:*/Format.get(TabDaten.getInhalt(sAic),sDatentyp,sFormat);
	                                    else if (!bBool)
	                                      d2+=TabDaten.getF(sAic);
                                	  }
                                	  else if (!bBool && s.equals("reserveOP8")) // not if
	                                    	bIf= !bIf;
                                	  else if (s.equals("reserveOP10")) // end if
	                                    	bIf=true;
	                                    
                                	  //g.fixtestError("Operation "+s+"/"+TabDaten.getI("aic_stamm")+": Null="+bNull+", bIf="+bIf+", s3="+s3+", bBool="+bBool);  
                                	  if (!bNull && bIf)
                                      {
                                		  if (!bString && !bBool)
                                		  {
	                                          if (s.equals("diff prev"))
	                                          {
	                                            if (TabDaten.bof())
	                                            {
	                                              d=d2;
	                                              bNull=true;
	                                            }
	                                            else
	                                            {
	                                              d = d2 - dV;
	                                              dV = d2;
	                                              bV=false;
	                                            }
	                                          }
	                                          if (s.equals("init"))
	                                          {
	                                            if (bV)
	                                            {
	                                              d = sAic == null ? new Date().getTime()/1000 : d2;
	                                              dV=d;
	                                              bV=false;
	                                            }
	                                            else
	                                              d=dV;
	                                          }
	                                          if (s.equals("<"))
	                                          {
	                                            if (d>=d2)
	                                              d=0;
	                                          }
	                                          else if (s.equals("<="))
	                                          {
	                                            if (d>d2)
	                                              d=0;
	                                          }
	                                          else if (s.equals(">"))
	                                          {
	                                            if (d<=d2)
	                                              d=0;
	                                          }
	                                          else if (s.equals(">="))
	                                          {
	                                            if (d<d2)
	                                              d=0;
	                                          }
	                                          else if (s.equals("="))
	                                          {
	                                            if (d!=d2)
	                                              d=0;
	                                          }
	                                          else if (s.equals("<>"))
	                                          {
	                                            if (d==d2)
	                                              d=0;
	                                          }
	                                          else if (s.equals("add"))
	                                            d += d2;
	                                          else if (s.equals("add days") || s.equals("add prev days"))
	                                          {
	                                            d += d2 * 24 * 3600;
	                                            dV=d;
	                                          }
	                                          else if (s.equals("add months"))
	                                          {
	                                            DateWOD DW=new DateWOD();
	                                            DW.setAllSeconds(d);
	                                            DW.setTimezoneOffset();
	                                            for (int i2=0;i2<d2-0.5;i2++)
	                                              DW.nextMonth();
	                                            d=DW.getAbsSeconds();
	                                          }
	                                          else if (s.equals("add years"))
	                                          {
	                                            DateWOD DW=new DateWOD();
	                                            DW.setAllSeconds(d);
	                                            DW.setTimezoneOffset();
	                                            for (int i2=0;i2<d2-0.5;i2++)
	                                              DW.nextYear();
	                                            d=DW.getAbsSeconds();
	                                          }
	                                          else if (s.equals("sub"))
	                                            d -= d2;
	                                          else if (s.equals("mul"))
	                                            d *= d2;
	                                          else if (s.equals("mul if"))
	                                          {
	                                            if (d2 != 0)
	                                              d *= d2;
	                                          }
	                                          else if (s.equals("reserveOP5"))
	                                          {
	                                              if (d == 0)
	                                                d = d2;
	                                          }                                              
	                                          else if (s.equals("div"))
	                                            d /= d2;
	                                          else if (s.equals("prev"))
	                                            d += d2 + dV;
	                                          else if (s.equals("proz"))
	                                            d /= TabDaten.sum(sAic);
	                                          else if (s.equals("min"))
	                                            d = Math.min(d,d2);
	                                          else if (s.equals("max"))
	                                            d = Math.max(d,d2);
	                                          else if (s.equals("age"))
	                                            d = (new DateWOD(getBis())).getAbsSeconds() - d2;
	                                          else if (s.equals("round"))
	                                            d = Round(d,d2,true);
                                		  }
                                          else if (bString)
                                          {
                                            if (s.equals("string concat") && s3!=null)
                                            {
//                                            	g.fixtestError("string concat: s2="+s2+", s3="+s3+", d2="+d2);
                                              s3 = s2 == null ? s3 : Static.FillSpace("",(int)d2) + s3;
//                                              g.fixtestError("-> s3="+s3);
                                            }
                                            else if (s.equals("string right"))
                                              s3 = s3 == null ? s3:s3.length()>(int)d2 ? s3.substring(s3.length()-(int)d2,s3.length()):s3;
                                            else if (s.equals("string_left")) // war reserveOP1
                                              s3 = s3 == null ? s3:s3.length()>(int)d2 ? s3.substring(0,(int)d2):s3;
                                            else if (s.equals("trunc_left"))  // war reserveOP2
                                              s3 = s3 == null ? s3:s3.length()>(int)d2 ? s3.substring((int)d2):"";
                                            else if (s.equals("string fix"))
                                              s3 = Static.FillSpace(s3,(int)d2);
                                            else if (s.equals("string fillnull"))
                                              s3 = Static.FillNull(s3,(int)d2);
                                            else if (s.equals("add char"))
                                              s3 = s3 == null ? ""+(char)(int)d2:s3+(char)(int)d2;
                                            if (sE != null)
                                              s3 = s3 == null ? sE:s3+sE;
                                            s2 = Static.Leer(s2) ? s3:s2+(s.equals("reserveOP4") ?"":s3);
                                          }
                                          else if (bBool)
                                          {
                                        	  boolean b3=sAic==null ? false:d2==0 ? TabDaten.getB(sAic) : TabDaten.getI(sDatentyp.endsWith("Bool3") ? "n"+sAic.substring(1):sAic)==d2;
//                                        	  g.fixtestError("bBool:"+b3+", d2="+d2+", Zeile="+TabDaten.getPos()+"/"+sDatentyp);
//                                        	  boolean bx=b;
                                        	  if (s.equals("reserveOP6")) // and
                                        		  b=b && b3;
                                        	  else if (s.equals("reserveOP7")) // or
                                        		  b=b || b3;
                                        	  else if (s.equals("reserveOP8")) // not
                                        		  b=!b;
                                        	  else if (s.equals(">") || s.equals("<") || s.equals("<=") || s.equals(">=") || s.equals("=") || s.equals("<>"))
                                        	  {
                                        		  d=TabDaten.getF(sAic);
//                                        		  g.fixtestError("CalcBool mit "+s+":"+d2+"/"+d);
                                        		  if (s.equals(">"))
                                            		  b=d>d2;
                                            	  else if (s.equals("<"))
                                            		  b=d<d2;
                                            	  else if (s.equals("<="))
                                            		  b=d<=d2;
                                            	  else if (s.equals(">="))
                                            		  b=d>=d2;
                                                  else if (s.equals("="))
                                                      b=d==d2;
                                                  else if (s.equals("<>"))
                                                      b=d!=d2;
                                            
                                        	  }
//                                                	  g.fixtestError("bBool "+TabDaten.getI("aic_stamm")+": "+bx+"/"+b3+"/"+s+"->"+b);                                              	  
                                          }
                                      }
                                      if (bString)
                                        bNull=false;
                                        //g.progInfo(bNull+"/"+d+"/"+d2+"/"+dV);
                                  }
                                        if (bV)
                                          dV=d;
                                        String sAic="E"+VecToKennung((Vector)TabSp.getInhalt("Vec"))+(bPeriode?"P"+i:"");
                                        if (bSumme)
                                        {
                                          int iErgArt = TabSp.getI("cod_aic_code");
                                          int iPosC = iErgArt == 0 ? -1:g.TabCodes.getPos("Aic", iErgArt);
                                          String sErgArt = iPosC<0 ? "sum" : g.TabCodes.getS(iPosC,"Kennung");
                                          sAic=sErgArt+"_"+sAic;
                                        }
                                        //g.fixtestInfo("Calc "+sAic+" -> "+d+(bPeriode ?" ("+g.VecPerioden.elementAt(i)+")":""));
                                        if (TabEA!=null && TabEA.size()>0)
                                        {
                                          if (!TabEA.getTimestamp("Eintritt").before(g.getVecPer(iVB).elementAt(i+1))
                                              || !TabEA.isNull("Austritt") && TabEA.getTimestamp("Austritt").before(g.getVecPer(iVB).elementAt(i)))
                                            d=0;
                                        }
					TabDaten.setInhalt(sAic,bNull?null:bBool ? b:bDate ? DateWOD.toDate(d):bString ? (Object)s2:new Double(d));
				}
                            }
                      }
		}
		TabSp.pop();
                TabDaten.moveFirst();
	}

        public String Ebenen()
        {
          String s="";
          for (int i=1;i<=iEbenen;i++)
            s+=") E"+i;
          return s;
        }

        public int getEbenen()
        {
        	return iEbenen;
        }

        public void checkGleiche(Tabellenspeicher TabDaten)
        {
          if ((iBits&cstGleiche)>0)
          {
            TabSp.push();
            TabSp.moveFirst();
            String sAic= "E"+VecToKennung((Vector)TabSp.getInhalt("Vec"));
            TabSp.pop();
            Object Obj=TabDaten.getInhalt(sAic);
            while(!TabDaten.eof())
            {
              //g.progInfo("mom:"+TabDaten.getPos());
              if (TabDaten.isNull(sAic) || Obj==null || !TabDaten.getInhalt(sAic).equals(Obj))
              {
                //g.progInfo("mom:"+TabDaten.getPos());
                //int i = TabDaten.getPos();
                //g.progInfo("clear:"+TabDaten.getPos());
                TabDaten.clearInhalt();
                //TabDaten.setPos(i);
              }
              else
                TabDaten.moveNext();
            }
            TabDaten.moveFirst();
          }
        }

        public void checkWeiter(Tabellenspeicher TabSpalten,Tabellenspeicher TabDaten)
        {
          if ((iBits&cstWeiterfuehren)>0)
          {
            for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
              for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                if ((TabSpalten.getI("Bits")&cstPeriode)>0 && g.getVecPer(iVB)!=null)
                {
                  String sSpalte="E"+TabSpalten.getS("Kennung2")+"p";
                  double d=TabDaten.getF(sSpalte+"0");
                  //int iAnz=Zeitraum.VecPerioden.size()-1;
                  for(int i=1;i<PerAnz();i++)
                    if (TabDaten.isNull(sSpalte+i))
                      TabDaten.setInhalt(sSpalte+i,new Double(d));
                    else
                      d=TabDaten.getF(sSpalte+i);
                }
            TabDaten.moveFirst();
          }
        }

        /*public String checkFast(Tabellenspeicher TabSpalten)
        {
          String s=null;
          if (TabSpalten==null)
            return "";
          boolean bCalc=TabSpalten.exists("Abfrage");
          int iPos=bCalc ? TabSpalten.getPos("Abfrage",iAbfrage):0;
          int iAnz=0;
          for (int i=iPos;i<TabSpalten.size() && (!bCalc || TabSpalten.getI(i,"Abfrage")==iAbfrage);i++)
            if ((TabSpalten.getI(i,"bits2")&(cstIsJoker+cstIsNull+cstIsJokerVec))>0)
            {
              int iEig=bCalc ? TabSpalten.getI(i,"Eig"):TabSpalten.getI(i,"Kennung2");
              String sANR=g.getANRS(iBew,iEig);
              if (sANR!=null)
              {
                if ((TabSpalten.getI(i,"bits2")&(cstIsJoker+cstIsJokerVec))>0)
                {
                  boolean bVec= (TabSpalten.getI(i,"bits2")&cstIsJokerVec)>0;
                  iAnz++;
                  String sVG = (bVec ? "*JokerVec ":"*JokerStt ") + g.TabStammtypen.getKennung(g.EigToStt(iEig)).toUpperCase() + "*";
                  //g.progInfo("checkFast: "+sVG);
                  s = (s==null ? "":s+(iAnz>1?" or ":" and ")) + sANR + " = " + sVG;
                  if ((TabSpalten.getI(i,"bits2")&cstIsNull)>0)
                  {
                    s += " or " + sANR + " is null";
                    iAnz++;
                  }
                  g.checkJoker(sVG);

                }
                else
                  s = (s==null ? "":s+" and ") + sANR+" is null";
              }
              else
                Static.printError("Abfrage.checkFast: kein ANR für "+sDefBez+"."+iEig+"gefunden");
            }
          if (s==null)
            return "";
          s=iAnz>1 ? " and ("+s+")":s==null ?"":" and "+s;
          g.progInfo("checkFast:"+s);
          return s;
        }*/

	public void checkHierarchie(Tabellenspeicher TabSpalten,Tabellenspeicher TabDaten)
	{
		//A.g.progInfo(A+"/"+TabSpalten+"/"+TabDaten);
                //g.progInfo("checkHierarchie");
		if (TabSpalten==null || TabDaten.size()==0)
			return;
		TabSpalten.push();
    TabDaten.push();
    String sAnfangold=sAnfang;
		boolean bCalc=TabSpalten.exists("Abfrage");
		//int iA=iAbfrage;
		if (bCalc)
			TabSpalten.posInhalt("Abfrage",iAbfrage);
		else
			TabSpalten.moveFirst();
		//A.g.progInfo("iA="+iA);
                Tabellenspeicher TabHall=null;
		for(;!TabSpalten.eof() && (!bCalc || TabSpalten.getI("Abfrage")==iAbfrage);TabSpalten.moveNext())
		{
      try
      {
        int iDArt=TabSpalten.getI("bits3")&cstDatenHolen;
        if (iDArt==cstDanachD)
        {
          String sDatentyp=TabSpalten.getS("Datentyp");
          if (sDatentyp.equals("Favorit"))
          {
            String sSp=TabSpalten.getS("Kennung");
            // g.fixtestError("Daten danach mit "+sDatentyp+" bie "+TabSpalten.getS("Kennung"));
            for (int iD=0;iD<TabDaten.size();iD++)
              TabDaten.setInhalt(iD,sSp,Favorit.is(g,TabDaten.getI(iD, sSp)));
          }
          else
          {
            String sSp=TabSpalten.getS("Kennung2");
            if (Static.Leer(sSp))
              sSp=TabSpalten.getS("Kennung").substring(1);
            Vector<Integer> Vec=TabDaten.getVecD("d"+sSp);
            String sTyp=TabSpalten.getS("Datentyp");
            g.fixtestError(sTyp+"-Daten von "+sSp+": "+Vec);
            Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_daten,spalte_"+sTyp+" Dat from daten_"+sTyp+" where aic_daten"+g.SQL_in(Vec),true);
            // Tab.showGrid();
            for (int iD=0;iD<TabDaten.size();iD++)
            {
              if (!TabDaten.isNull(iD,"d"+sSp) && Tab.posInhalt("aic_daten", TabDaten.getI(iD,"d"+sSp)))
                TabDaten.setInhalt(iD, "e"+sSp, Tab.getM("Dat"));
            }
          }
        }
			  else if (TabSpalten.getInhalt("Eig2")!=null && (TabSpalten.getI("bits2")&cstKeinOhr)==0)
			  {			
          //g.progInfo("Bezeichnung="+TabSpalten.getS("Bezeichnung"));
				  String sDatentyp=TabSpalten.getS("Datentyp");
				  String sH=bCalc ? TabSpalten.getS("Kennung"):			    
					(sDatentyp.equals("Gruppe") || sDatentyp.equals("BewStamm") || sDatentyp.equals("Hierarchie") || sDatentyp.equals("BewHierarchie") ||
						sDatentyp.equals("Benutzer") || sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("BewVon_Bis") ||
						sDatentyp.equals("BewModul") || sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular") ? 'v':'e')+TabSpalten.getS("Kennung2");
//				String sO="o"+TabSpalten.getS("Kennung2");
//				g.defInfo("checkHierarchie mit "+sDefBez+": "+sH+"/"+sDatentyp);
				String sH2=bCalc ? TabSpalten.getS("Kennung3"):sH.startsWith("e") ? null : "e"+TabSpalten.getS("Kennung2");
				if ((TabSpalten.getI("bits2")&cstSpSort)>0)
				    sSort=sH2;
				//A.g.progInfo(sH+"/"+sH2);
				Vector<Integer> Vec=new Vector<Integer>();
                                if (TabHall==null)
                                  TabHall=new Tabellenspeicher(g,new String[] {"Eig","Tab"});
				Tabellenspeicher TabH=null;
                                if (TabHall.posInhalt("Eig",TabSpalten.getI("Eig2")))
                                  TabH=(Tabellenspeicher)TabHall.getInhalt("Tab");
                                else
                                {
                                  TabH=new Tabellenspeicher(g,"select aic_stamm s1,sta_aic_stamm s2 from poolview where aic_stamm is not null and aic_eigenschaft=?",TabSpalten.getS("Eig2"), true);
                                  TabHall.addInhalt("Eig",TabSpalten.getI("Eig2"));
                                  TabHall.addInhalt("Tab",TabH);
                                }
                                //TabH.showGrid("TabH");
				//g.progInfo("sH="+sH+", sH2="+sH2+" / "+"V"+sH.substring(1,sH.indexOf("_")));
        for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
				{
					if (TabDaten.getInhalt(sH)==null)
					{
						int i=iBew==0 ? TabDaten.getI("aic_stamm"):TabDaten.getI("V"+sH.substring(1,sH.indexOf("_")));
						while (TabH.posInhalt("s1",i))
						{
							i=TabH.getI("s2");
							Integer I=new Integer(i);
							if (!Vec.contains(I))
								Vec.addElement(I);
						}
					}
				}
				Vector<Integer> VecE=new Vector<Integer>();
                //String sX=TabSpalten.getS(bCalc?"Eig":"Kennung2");
                String sX=TabSpalten.getS("Kennung");
                if (!Character.isDigit(sX.charAt(0)))
                  sX=sX.substring(1);
                String sO="o"+sX;
                //g.progInfo("sX="+sX);
				VecE.addElement(new Integer(iBew==0 ? sX:sX.substring(sX.indexOf("_")+1)));
                //g.progInfo("VecE="+VecE);
                sAnfang="p2.aic_stamm";
                //g.progInfo("sAnfang="+sAnfang);
                long iBitsOld=iBits;
                if ((iBits&cstHierarchie)>0)
                  iBits-=cstHierarchie;
                bLetLeer=false;
                if (Vec.size()>0)
                {
					Tabellenspeicher TabN=new Tabellenspeicher(g,"select * from (select "+ZusaetzlicheSpalten(VecE,Formular.Stamm,false)+" from stammview p2 where aic_stamm"+Static.SQL_in(Vec)+Ebenen(),true);
					bLetLeer=true;
		            iBits=iBitsOld;
//		            TabN.showGrid("TabN"); //TODO wieder ausklammern
		            String sH3=iBew==0 ? sH:sH.charAt(0)+sX.substring(sX.indexOf("_")+1);
		            String sH4= sH2 == null ? sH2:iBew==0 ? sH2:sH2.charAt(0)+sX.substring(sX.indexOf("_")+1);
		            //g.progInfo(sH3+"/"+sH4);
	                for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
					{
						boolean bLeer=TabDaten.getInhalt(sH)==null;
						if (bLeer)
						{
							int i=iBew==0 ? TabDaten.getI("aic_stamm"):TabDaten.getI("V"+sH.substring(1,sH.indexOf("_")));
							while (bLeer && TabH.posInhalt("s1",i))
							{
								i=TabH.getI("s2");
								if(TabN.posInhalt("aic_stamm",i) && TabN.getInhalt(sH3)!=null)
								{
									TabDaten.setInhalt(sH,TabN.getInhalt(sH3));
									TabDaten.setInhalt(sO,TabN.getI("aic_stamm"));
									if (sH2 != null)
										TabDaten.setInhalt(sH2,TabN.getInhalt(sH4));
									bLeer=false;
								}
							}
						}
	//					else if (TabDaten.isNull(sO))
	//						TabDaten.setInhalt(sO,TabDaten.getS(sH));
					}
                }
             }
           }
           catch(Exception e)
           {
             g.defInfo2("Abfrage.checkHierarchie:"+TabSpalten.getS("Kennung")+" bei Abfrage <"+sDefBez+">:"+e);
           }
		}
                sAnfang=sAnfangold;
                TabDaten.pop();
		TabSpalten.pop();
	}
	
	private String getDatentyp(Global g,int iAic)
	{
		int iPos=g.TabEigenschaften.getPos("AIC", iAic);
        if (iPos>=0)
          return g.TabEigenschaften.getS(iPos,"Datentyp");
        else
          return "???";
	}

        public Vector getVecHierarchie(Vector VecEig)
        {
//        	g.fixtestError("-> getVecHierarchie:"+VecEig);
          Vector<String> Vec=new Vector<String>();
          Object ObjLast=VecEig.elementAt(VecEig.size()-1);
          int iFirst=VecEig.size()-2;
          while (iFirst>=0 && ObjLast.equals(VecEig.elementAt(iFirst)))
            iFirst--;
          Vector<Object> Vec2=new Vector<Object>();
          for (int i=0;i<=iFirst;i++)
            Vec2.addElement(VecEig.elementAt(i));
          if (iBew>0 && getDatentyp(g,Sort.geti(Vec2, 0)).equals("BewHierarchie"))
        	  Vec.addElement(VecToKennung(Vec2));
          for (int i=iFirst+1;i<VecEig.size();i++)
          {
            Vec2.addElement(VecEig.elementAt(i));
            Vec.addElement(VecToKennung(Vec2));
          }
//          g.fixtestError("<- getVecHierarchie:"+Vec);
          return Vec;
        }

        public static String Rolle(int i)
        {
          return i > 0 ? " and aic_rolle=" + i : " and aic_rolle is null";
        }

        public static String Rolle2(int i)
        {
          return i > 0 ? " aic_rolle=" + i : " aic_rolle is null";
        }

    public int PerAnz()
    {
      if (g.TabPerioden == null || iVB>0)
        return g.getVecPer(iVB).size()-1;
      else
        return g.TabPerioden.getAnzahlElemente(null);
    }

    public String PerVon(int iPer,boolean bAbNeujahr)
    {
      if (bAbNeujahr)
        return g.DateTimeToString(DateWOD.getNewYear(getVon()).toDate());
      else if (g.TabPerioden == null)
        return g.DateTimeToString((Date)VecPerioden.elementAt(iPer));
      else
        return g.DateTimeToString((java.sql.Timestamp)g.TabPerioden.getInhalt("von",iPer));
    }

    public String PerBis(int iPer)
    {
      if (g.TabPerioden == null)
        return g.DateTimeToString((Date)VecPerioden.elementAt(iPer+1));
      else
        return g.DateTimeToString((java.sql.Timestamp)g.TabPerioden.getInhalt("bis",iPer));
    }

    @SuppressWarnings("unchecked")
	public String ZusaetzlicheSpalten(Vector rVecSpalten1,int iTyp,boolean b)
	{
//    	if (bTable)
//          g.fixtestError("ZusaetzlicheSpalten bei "+sBez+": Table="+bTable+", Vec="+rVecSpalten1);
          Vector rVecSpalten=new Vector();
          for (int i=0;i<rVecSpalten1.size();i++)
          {
            Object Obj=rVecSpalten1.elementAt(i);
            //g.progInfo(i+":"+Static.print(Obj));
            if (Obj instanceof Long)
              Obj=Integer.parseInt(Obj.toString());
            if (!rVecSpalten.contains(Obj))
              rVecSpalten.addElement(Obj);
          }
          Vector<String> VecSp=new Vector<String>();
          //g.fixInfo("ZusaetzlicheSpalten2 "+rVecSpalten);
          //Vector<String> VecAll=new Vector<String>();
          //g.progInfo("ZusaetzlicheSpalten:"+rVecSpalten+"/"+iTyp+"/"+b);
//          String sDB=g.getVDB();
          String sBewView=(iBits&cstKeinZeitraum)>0 ? "bewview2":"bewview"+(iVB>0 ? ""+iVB:"");
          String sStammView=(iBits&cstKeinStammZeitraum)>0 || iStt!=g.iSttANR ? "stammview2":"stammview"+(iVB>0 ? ""+iVB:"");
          String sPoolViewx=(iBits&cstChanges)>0 ? "poolview4":(iBits&cstKeinStammZeitraum)>0 ? "poolview2":"poolview"+(iVB>0 ? ""+iVB:"");
          //g.fixtestError("SV="+sStammView+", BV="+sBewView);
          //String sXView=(iBits&cstChanges)>0 ? "stringXview4":(iBits&cstKeinStammZeitraum)>0 ? "stringXview2":"stringXview";
          iEbenen=1;
          boolean bEbenePlus=false;
          for(int i=0;i<rVecSpalten.size();i++)
          {
            Object Obj=rVecSpalten.elementAt(i);
            if (!(Obj instanceof String))
            {
              boolean bEinfach = !(Obj instanceof Vector) && !(Obj instanceof String);
              //if (!bEinfach && !(Obj instanceof Vector))
              //g.progInfo("Obj="+Obj+"/"+Static.className(Obj));
              int iEbene=bEinfach ? 1:((Vector)Obj).size();

              int iAic = bEinfach ? getEig(rVecSpalten.elementAt(i)) : Sort.geti(((Vector)Obj).elementAt(((Vector)Obj).size() - 1));
              int iPos=g.TabEigenschaften.getPos("AIC", iAic);
              if (iPos>=0)
              {
                String sTyp = g.TabEigenschaften.getS(iPos,"Datentyp");
                if (iBew==0 && iStt==g.TabEigenschaften.getI(iPos,"aic_stammtyp") && (g.TabEigenschaften.getI(iPos,"bits")&Global.cstSysAic)>0)
                  ;//sTyp="SysAic";
                //if (sTyp.equals("Gruppe") || sTyp.equals("Hierarchie") || sTyp.equals("BewStamm") || sTyp.equals("BewHierarchie") ||
                //    sTyp.equals("Benutzer") || sTyp.equals("Mandant") || sTyp.equals("Anlage"))
                else if (VecME.contains(sTyp))
                  iEbene += 1;
              }
              if (iEbene > iEbenen)
                iEbenen = iEbene;
            }
          }
          //boolean bBB=iBew>0 ? (g.getBewBits(iBew)&Global.cstBerechtigung)>0:false;
          String sA=sAnfang != null ? sAnfang:(is(iBits,cstDistinct)?"null aic_":(iBew==0 && (iStt==0 || is(iBits,cstKeinStt))?"aic_stammtyp,":"")+"p2.aic_")+
              (iBew==0 || iTyp==Formular.Stamm ?"stamm":"Bew_Pool,zone,anr");
          if (bEntfernte || is(iBits,cstEntfernte) && iTyp==Formular.Bewegung)
              sA+=",pro_aic_protokoll";
          String sGesamt=iEbenen==1 ? sA:"E"+(iEbenen-1)+".*";
          if ((iBits&cstHierarchie)>0)
          {
            String sEinzeln=Static.sLeer;
            Vector VecH2=new Vector();
            for(TabSp.moveFirst();!TabSp.eof();TabSp.moveNext())
              if(!TabSp.isNull("aic_stammtyp") && !(is(TabSp.getI("bits2"),cstWeg) && is(TabSp.getI("bits"),cstUnsichtbar)))
              {
                Vector VecEig = (Vector)TabSp.getInhalt("Vec");
                if (VecEig != null && VecEig.size()>0 && !TabSp.getS("Status").equals("Del"))
                {
                  int iPos=g.TabEigenschaften.getPos("Aic",Tabellenspeicher.geti(VecEig.elementAt(VecEig.size()-1)));
                  if (iPos>=0)
                  {
                   int iSttH=TabSp.getI("aic_stammtyp");
                   if (g.TabEigenschaften.getS(iPos,"Datentyp").equals("Hierarchie"))
                   {
                    Vector VecH=getVecHierarchie(VecEig);
                    //g.progInfo("Hierarchie:"+VecH+"/"+VecEig+"/"+TabSp.getI("Aic_Spalte")+":"+TabSp.getS("Kennung")+":"+TabSp.getI("Reihenfolge"));
                    String sNameH=VecH.elementAt(0)+"_H"+iSttH;
//                    g.fixtestError("Hierarchie:"+sNameH+"/"+VecH);
                    if (!VecH2.contains(sNameH))
                    {
                      VecH2.addElement(sNameH);
                      sEinzeln += ",(case";
                      for (int i = 0; i < VecH.size(); i++)
                        sEinzeln += " when w" + VecH.elementAt(i) + "=" + iSttH + " then v" + VecH.elementAt(i);
                      sEinzeln += " end) v" + sNameH;
                      sEinzeln += ",(case";
                      for (int i = 0; i < VecH.size(); i++)
                        sEinzeln += " when w" + VecH.elementAt(i) + "=" + iSttH + " then e" + VecH.elementAt(i);
                      sEinzeln += " end) e" + sNameH + "," + iSttH + " w" + sNameH;
                    }
                   }
                  }
                }
              }
            if (sEinzeln != Static.sLeer)
            {
              bEbenePlus=true;
              sGesamt ="E"+iEbenen+".*"+sEinzeln+" from (SelecT "+sGesamt;
            }
          }
          //g.progInfo("Ebenen="+iEbenen);
          for (int j=iEbenen;j>0;j--)
          {
            if (j<iEbenen)
              sGesamt+=" from (SelecT "+(j>1?"E"+(j-1)+".*":sA);

		for(int i=0;i<rVecSpalten.size();i++)
		{
			Object Obj=rVecSpalten.elementAt(i);
                        //if (Obj==null)
                        //  break;
			boolean bEinfach = !(Obj instanceof Vector) && !(Obj instanceof String);// && !(Obj instanceof BewEig);
                        int iEbene=!(Obj instanceof Vector)  ? 1:((Vector)Obj).size();
//                        g.fixtestError(sDefBez+": "+i+"/"+Static.print(Obj)+"/"+iEbene);
			String sAic="";
			String sVor="";
			int iAic=0;
			int iBew0=0;
			int iEig0=0;
                        int iRollex=0;
			boolean bSumme=false;
                        boolean bBewS=false;
			String sNull=null;
			String sVor2=null;
			String sN2=bEinfach?") ":")"+g.ifnullE();
                        String sg=null;
                        Vector VecGruppe=null;
                        boolean bGruppe=false;
                        boolean bPeriode=false;
                        boolean bOhr=false;
                        String sArt=null;
                        int iStamm=0;
                        //g.progInfo(i+".Obj="+Static.print(Obj)+"/"+bEinfach);
                        if (Obj instanceof String)
                        {
                          if (TabSp.posInhalt("Nummer",new Integer((String)Obj)))
                          {
                            Obj=TabSp.getInhalt("Vec");
                            int iNr=getLaengeN(TabSp);
                            
                            Object Obj0=((Vector)Obj).elementAt(0);
                            iEig0=getEig(Obj0);
                            iBew0=Obj0 instanceof BewEig ? ((BewEig)Obj0).Bew():0;
                            if (iBew0==0)
                            {
                              sVor = iTyp==Formular.Stamm ? "aic_stamm=p2.aic_stamm":"aic_bew_pool=p2.aic_bew_pool"; // 19.1.2012 auf Stamm ergänzt
                              sNull=",(";
                              sN2=") ";
                            }
                            iAic=Sort.geti(((Vector)Obj).elementAt(((Vector)Obj).size()-1));
                            sAic=VecToKennung(Obj)+(iNr>0 ? "x"+iNr:"");
//                            g.fixtestError(sDefBez+": "+Obj+", Nr="+iNr+" -> sAic="+sAic);
                            sg="g"+TabSp.getInhalt("Nummer")+"_";
                            VecGruppe=(Vector)TabSp.getInhalt("Gruppe");
                            bGruppe=VecGruppe != null;
                            //g.fixInfo("VecPerioden="+g.VecPerioden+"/"+VecPerioden);
                            bPeriode = (TabSp.getI("bits") & cstPeriode) > 0 && (g.getVecPer(iVB) != null || g.TabPerioden!=null && g.TabPerioden.size()>0);
                            if(bPeriode && VecPerioden == null && g.TabPerioden==null)
                                VecPerioden = new Vector(g.getVecPer(iVB));
                            //g.progInfo(TabSp.getI("Nummer")+":"+"Periode="+bPeriode+"/"+VecPerioden+"/"+g.VecPerioden+"/"+(TabSp.getI("bits") & cstPeriode));
                            bSumme = 	iTyp==Formular.Stamm && iEig0>0;
                            if (bSumme)
                            {
                              int iPos=g.TabEigenschaften.getPos("AIC",iEig0);
                              if (iPos>=0)
                        	bSumme=g.TabEigenschaften.getS(iPos,"Datentyp").equals("BewStamm");
                              else
                        	bSumme=false;
                            }
                            sArt=(bSumme && TabSp.getI("cod_aic_code")>0) ? g.TabCodes.getKennung(TabSp.getI("cod_aic_code")):"sum";
                            if (iBew0>0)
                            {
                              boolean bNormal = iTyp == Formular.Stamm || !(Obj0 instanceof BewEig) || ((Vector)Obj).size() > 2;
                              sVor2 = "BEW2_AIC_BEW_POOL"; //(bNormal ? "v" : "e") + EigElement(Obj0);
                              sNull=","+g.ifnullA(sVor2)+"(";
                              sVor = (bNormal ? "aic_stamm=" : "aic_bew_pool=") + sVor2;
                            }
                          }
                          else
                            g.printError("Abfrage.ZusaetzlicheSpalten: Nummer nicht gefunden!",iBegriff);
                          //iAic=0;
                          //sAic="";
                          //sVor="";
                        }
			else if (bEinfach)
			{
				iAic=getEig(rVecSpalten.elementAt(i));
//				int iPosE=g.TabEigenschaften.getPos("Aic",iAic);
//				bOhr=g.TabEigenschaften.getI(iPosE,"Eig2")>0;
				sAic=EigElement(rVecSpalten.elementAt(i));
				sVor= iTyp==Formular.Stamm ? iAic<0 ? "sta_aic_stamm=p2.aic_stamm":"aic_stamm=p2.aic_stamm":
					iTyp==Formular.Bewegung ? "aic_bew_pool=p2.aic_bew_pool":"";
				bEinfach = iAic>0;
				iAic=Math.abs(iAic);
                                iRollex=Obj instanceof BewEig ? ((BewEig)Obj).Rolle():0;
                                //if (iRollex>0)
                                //  g.fixInfo("Obj="+Obj+"-> Rolle="+iRollex+" bei Aic="+iAic);
				sNull=",(";
                                //if (iAic==0)
				//  g.progInfo("Einfach: Obj="+Obj+" / sAic="+sAic+" / sVor="+sVor+"/"+i);
			}
			else
			{
				/*boolean bFirst=true;
				for(int i2=0;i2<((Vector)Obj).size();i2++)
					if(bFirst)
					{
						sAic=EigElement(((Integer)((Vector)Obj).elementAt(0)).intValue());
						bFirst=false;
					}
					else
					{
						sAic=sAic+"_"+EigElement(((Integer)((Vector)Obj).elementAt(i2)).intValue());
					}*/
				//g.progInfo("Obj="+Obj+"/"+Static.className(Obj));
				Object Obj0=((Vector)Obj).elementAt(0);
				//g.progInfo("Obj0="+Obj0+"/"+Static.className(Obj0));
				iEig0=getEig(Obj0);
				//g.progInfo("iEig0="+iEig0);
				int iPos=iEig0>0 ? g.TabEigenschaften.getPos("AIC",iEig0):-1;
				bSumme = iTyp==Formular.Stamm && iPos>=0 && g.TabEigenschaften.getS(iPos,"Datentyp").equals("BewStamm");
                                bBewS= iTyp==Formular.Bewegung && iPos>=0 && g.TabEigenschaften.getS(iPos,"Datentyp").equals("BewBew") && g.TabEigenschaften.getI(iPos,"Bew")==iBew;
				iBew0= bSumme && Obj0 instanceof BewEig ? ((BewEig)Obj0).Bew():0;
                                //iRollex=Obj0 instanceof BewEig ? ((BewEig)Obj0).Rolle():0;
                                //g.progInfo("Obj="+Obj+"/"+Static.className(Obj));
				sAic=VecToKennung(Obj);
				//g.progInfo("sAic="+sAic);
                                Object Objx=((Vector)Obj).elementAt(((Vector)Obj).size()-1);
				iAic=Math.abs(Sort.geti(Objx));
                                iRollex=Objx instanceof BewEig ? ((BewEig)Objx).Rolle():0;
                                //g.fixInfo("Objx="+Objx+"-> Rolle="+iRollex+" bei Aic="+iAic);
				//g.progInfo("iAic="+iAic);
                                boolean bNormal=iTyp==Formular.Stamm || !(Obj0 instanceof BewEig) || ((Vector)Obj).size()>2;
				sVor2=(bNormal ? "v":"e")+EigElement(Obj0);
				for(int i2=1;i2<((Vector)Obj).size()-1;i2++)
					sVor2+="_"+EigElement(((Vector)Obj).elementAt(i2));
				sNull=","+g.ifnullA(sVor2)+"(";
				sVor=(bNormal ? "aic_stamm=":"aic_bew_pool=")+sVor2;
                                //if (iAic==0)
				//  g.progInfo("Obj="+Obj+" / sAic="+sAic+" / sVor2="+sVor2);
			}

			// Spaltenbezeichnung:
			// e ... Standard
			// v ... Verknüpft , von
			// a ... Anzeige für Mehrfach
			// b ... bis
			// d ... Dauer
                        // t ... timestamp
                        // u ... user

                        String sTyp = null;
                        boolean bWST=false; // Stichtag im Web
                        int iEigStt=0;
                        int iRolle2=iRolle;
                        //boolean bKeinANR=false;
                        //boolean bRolle=true; //false; 5.2. nachkorrigiert
                        int iPos=g.TabEigenschaften.getPos("AIC",iAic);
                        if (iPos>=0)
                        {
                          sTyp = g.TabEigenschaften.getS(iPos,"Datentyp");
                          iEigStt=g.TabEigenschaften.getI(iPos,"aic_stammtyp");
                          bOhr=g.TabEigenschaften.getI(iPos,"Eig2")>0;
                          bWST=bWeb2 && (g.TabEigenschaften.getI(iPos,"bits")&Global.cstEigStichtag)>0;
                          //bKeinANR=(g.TabEigenschaften.getI("bits")&g.cstKeinANR)>0;
                          //bRolle=(g.TabEigenschaften.getI(iPos,"bits")&Global.cstEAneu)>0;  // am 14.1.2010 entfernt, weil immer Rolle berücksichtigt werden soll
                          //if (bRolle)// && g.TabEigenschaften.getI("Rolle")>0)
                            iRolle2=g.TabEigenschaften.getI(iPos,"Rolle");
                          if (iBew==0 && iStt==iEigStt && (g.TabEigenschaften.getI(iPos,"bits")&Global.cstSysAic)>0)
                            sTyp="SysAic2";
                        }
                        //g.progInfo("ZusaetzlicheSpalten: "+g.TabEigenschaften.getS("Bezeichnung")+"="+iRolle2);
                        String sEinzeln=Static.sLeer;
                        if (Obj==null)
                          g.fixtestInfo("Eigenschaft "+(i+1)+" ist null");
                        else if (sTyp ==null)
                          g.printError("Abfrage.ZusaetzlicheSpalten: Eigenschaft "+iAic+" existiert nicht",iBegriff);
                        else if(j==iEbene+1)
                        {
                          String sBeiNull=null;
                          boolean bLeer=false;
                          //boolean bPeriode=false;
                          boolean bBild=false; // bei Datum für Monat
                          boolean bWS=false;
                          if (bSpalten)
                          {
                            boolean bRel=VRel.contains(sTyp);//sTyp.equals("BewZahl2") || sTyp.equals("BewMass2") || sTyp.equals("BewWaehrung2");
                            if (bGruppe || bPeriode || bRel || TabSp.posInhalt("Vec",Obj instanceof Integer ? Static.AicToVec(((Integer)Obj).intValue()):Obj))
                            {
                              //bPeriode = (TabSp.getI("bits") & cstPeriode) > 0 && Zeitraum.VecPerioden != null;
                              bLeer=(TabSp.getI("bits")&cstSpLeer)>0 && bLetLeer || (iBits&cstLeer)>0;
                              bBild=(TabSp.getI("bits")&cstBild)>0;
                              bWS=true;
                              if ((TabSp.getI("bits") & cstPeriode) > 0 && (TabSp.getI("bits") & cstBild) > 0)
                                sTyp="";
                              //if (bRel)
                              //  g.progInfo("Rel="+TabSp.getI("Rel"));
                            }
                          }
                          /*if (VecAll.contains(sAic))
                            break;
                          VecAll.addElement(sAic);
                          g.fixInfo("  sAic="+sAic+";"+VecAll);*/
                          String sProg=null;
                          if (!bWS && !sTyp.endsWith("Hierarchie") && bLetLeer)
                            ;
                          else if (sTyp.equals("Gruppe") || sTyp.equals("Hierarchie") || sTyp.equals("Firma") || iTyp == Formular.Bewegung && (sTyp.equals("BewStamm") || sTyp.equals("BewHierarchie")))
                            sBeiNull="select bezeichnung from "+sStammView+" p where aic_rolle is null and aic_stamm"; //sStammView
                          else if (sTyp.equals("Benutzer") || sTyp.equals("User") || sTyp.equals("BewUser"))
                            sBeiNull="select kennung from benutzer where geloescht is null and (use_bis is null or use_bis>"+g.now()+") and aic_benutzer";
                          else if (sTyp.equals("BewBG"))
                        	sBeiNull="select kennung from benutzergruppe where aic_benutzergruppe";
                          else if (sTyp.endsWith("Land"))
                            sBeiNull="select kennung from Land where aic_Land";
                          else if (sTyp.endsWith("Aufgabe"))
                              sBeiNull="select kennung from Begriff where aic_Begriff";
                          else if (sTyp.endsWith("Status"))
                              sBeiNull="select kennung from Status where aic_Status";
                          else if (sTyp.equals("BewHoliday"))
                            sBeiNull="select kennung from Feiertag where aic_Feiertag";
                          else if (sTyp.equals("Anlage"))
                            sBeiNull="select kennung from code where aic_code";
                          else if (sTyp.equals("Mandant"))
                            sBeiNull="select kennung from mandant where aic_mandant";
                          else if (sTyp.equals("BewModell") || sTyp.equals("BewDruck") || sTyp.equals("BewFormular") || sTyp.equals("BewModul"))
                          {
                            sBeiNull="select defBezeichnung from begriff where aic_begriff";
                            sProg="select prog from begriff where aic_begriff"; 
                          }
                          else if (sTyp.equals("Mehrfach"))
                          {
                            if (iTyp == Formular.Bewegung && bEinfach)
                              sBeiNull="select bezeichnung from "+sStammView+" p where aic_rolle is null and aic_stamm"; //sStammView
                          }
                          else if (sTyp.equals("LoeschBenutzer"))
                          {
                            if (iTyp==Formular.Bewegung)
                              sBeiNull="select kennung from benutzer where aic_benutzer";
                          }
                          else if (sTyp.equals("BewBool3") || sTyp.equals("Bool3"))
                            sBeiNull="select kennung from Auswahl where aic_Auswahl";
                          else if (bBild && (sTyp.equals("Eintritt") || sTyp.equals("BewDatum")))
                            sBeiNull=","+g.fDate("e"+sAic,TabSp.getI("aic_begriff")==0 ?"mm":g.TabBegriffe.getKennung(TabSp.getI("aic_begriff")))+" m"+sAic;
                          //  sBeiNull=(g.Oracle()?",to_number(to_char(e"+sAic+",'MM'),'00'":",month(e"+sAic)+") m"+sAic;
                          if (sBeiNull != null)
                          {
                            String sTab=sTyp.equals("Gruppe") || sTyp.equals("Hierarchie") || sTyp.equals("BewHierarchie") || sTyp.equals("Firma")
                                || sTyp.equals("BewStamm") && iTyp == Formular.Bewegung || sTyp.equals("Mehrfach") && iTyp == Formular.Bewegung && bEinfach ? "Stamm":
                                sTyp.equals("BewModell") || sTyp.equals("BewDruck") || sTyp.equals("BewFormular") || sTyp.equals("BewModul") || sTyp.equals("Aufgabe") ? "Begriff":
                                sTyp.equals("Benutzer") || sTyp.equals("Mandant") ? sTyp : sTyp.equals("Anlage") ? "Code" : sTyp.equals("LoeschBenutzer") || sTyp.endsWith("User") ? "Benutzer":sTyp.equals("BewBG") ? "Benutzergruppe":
                                sTyp.endsWith("Land") ? "Land":sTyp.endsWith("Status") ? "Status":sTyp.equals("BewHoliday") ? "Feiertag":sTyp.endsWith("Bool3") ? "Auswahl":null;
                            if (sTab != null && sTab.equals("Benutzer"))
                            	sGesamt+=",("+sBeiNull + "=v" + sAic + ") k" + sAic;	
                            if ((sTyp.equals("Gruppe") || sTyp.equals("BewStamm")) & !g.Translate(iEigStt))
                            {
                              if(bPeriode)
                              {
                                sEinzeln="";
                                for (int iPer = 0; iPer < PerAnz(); iPer++)
                                  sEinzeln += "," + g.ifnull("v"+sAic+"p"+iPer, "null", "(" + sBeiNull + "=v"+sAic+"p"+iPer + ")") + " e"+sAic+"p"+iPer;
                              }
                              else
                                sEinzeln = "," + g.ifnull("v" + sAic, "null", "(" + sBeiNull + "=v" + sAic + ")") + " e" + sAic;
                            }
                            else if (sTyp.equals("Eintritt") || sTyp.equals("BewDatum"))
                              sEinzeln=sBeiNull;
                            else if (sTab != null)
                            {
                              if(bPeriode)
                              {
                                sEinzeln="";
                                for (int iPer = 0; iPer < PerAnz(); iPer++)
                                  sEinzeln += g.AU_Bezeichnung2(sTab, "v" + sAic + "p" + iPer, "(" + sBeiNull + "=v" + sAic + "p" + iPer + ")",iAic) + " e" + sAic + "p" + iPer;
                              }
                              else
                              {
                                sEinzeln = g.AU_Bezeichnung2(sTab, "v" + sAic, "(" + sBeiNull + "=v" + sAic + ")",iAic) + " e" + sAic;
//                                g.fixtestError(sAic+":"+sEinzeln);
                              }
                              if (sProg != null)
                            	  sEinzeln+=",("+sProg+"=v" + sAic + ") p"+sAic;
                            }
                          }
                          if (!bPeriode && (sTyp.equals("BewBool3") || sTyp.equals("Bool3")))
                        	  sEinzeln+=","+g.ifnull("v"+sAic,"0","(select nr from auswahl where aic_auswahl=v"+sAic+")")+"n"+sAic;
                          if (sTyp.equals("GPS"))
                        	  sEinzeln+=","+g.ifnull("d"+sAic,"null","(select lat from Daten_GPS where aic_daten=d"+sAic+")")+"b"+sAic+
                        			    ","+g.ifnull("d"+sAic,"null","(select lng from Daten_GPS where aic_daten=d"+sAic+")")+"l"+sAic+
                        			    ","+g.ifnull("d"+sAic,"null","(select name from Daten_GPS where aic_daten=d"+sAic+")")+"e"+sAic;
                          else if (sTyp.equals("Hierarchie") || sTyp.equals("BewHierarchie"))
                            if(bPeriode)
                              for(int iPer=0;iPer<PerAnz();iPer++)
                                sEinzeln+=","+g.ifnull("v"+sAic+"p"+iPer,"null","(select aic_stammtyp from stamm where aic_stamm=v"+sAic+"p"+iPer+")")+" w"+sAic+"p"+iPer;
                            else
                              sEinzeln+=","+g.ifnull("v"+sAic,"null","(select aic_stammtyp from stamm where aic_stamm=v"+sAic+")")+" w"+sAic;
                          if (!bLeer)
                            sGesamt+=sEinzeln;
                          //g.progInfo("sEinzeln="+sEinzeln);
                        }
			else if (j==iEbene)
			{
                          //g.progInfo("Ebene "+j+":"+sTyp);
				//String sTyp = g.TabEigenschaften.getS("Datentyp");
				//g.progInfo(g.TabEigenschaften.getS("Kennung")+":"+sTyp+"/"+bSumme+"/"+iEig0+"/"+iBew0);
				//boolean bPeriode=false;
                                boolean bVorZR=false;
                                boolean bKumuliert=false;
                                boolean bAbNeujahr=false;
                                boolean bGanzesjahr=false;
                                boolean bInStunden=false;
                                boolean bEdit=false;
                                boolean bLeer=false;
                                boolean bLeer2=false;
                                boolean bST=false;
                                //int iRel=0;
                                boolean bWS=false;
				if (bSpalten)
                                {
                                  //boolean bRel=VRel.contains(sTyp);//sTyp.equals("BewZahl2") || sTyp.equals("BewMass2") || sTyp.equals("BewWaehrung2");
					if (bGruppe || bPeriode || VRel.contains(sTyp) || TabSp.posInhalt("Vec",Obj instanceof Integer ? Static.AicToVec(((Integer)Obj).intValue()):Obj))
					{
						//bPeriode=(TabSp.getI("bits")&cstPeriode)>0 && Zeitraum.VecPerioden!=null;
						int iNr=getLaengeN(TabSp);
						Vector<Timestamp> VecPer=g.getVecPer(iVB);
                                                bVorZR= (TabSp.getI("bits2") & cstVorZR) > 0 && VecPer != null;
                                                bKumuliert = (TabSp.getI("bits2") & cstKumuliert) > 0 && VecPer != null;
                                                bAbNeujahr = (TabSp.getI("bits2") & cstAbNeujahr) > 0 && VecPer != null;
                                                bGanzesjahr = (TabSp.getI("bits2") & cstGanzesJahr) > 0;// && g.getZA(iVB)!=null && !g.getZA(iVB).equals("Jahr");
//                                                if (bGanzesjahr)
//                                                	g.fixtestError("Ganzes Jahr bei "+g.getZA(iVB));
                                                bST=false;//(TabSp.getI("bits2") & cstbeiStichtag) > 0;
                                                bEdit=(TabSp.getI("bits")&cstEditierbar)>0;
                                                bLeer=(TabSp.getI("bits")&cstSpLeer)>0 && bLetLeer || (iBits&cstLeer)>0;
                                                bLeer2=(TabSp.getI("bits")&cstSpLeer)>0 || (iBits&cstLeer)>0;
//                                                g.fixtestError("Leer="+bLeer+" bei "+Obj+"/"+((TabSp.getI("bits")&cstSpLeer)>0)+"/"+bLetLeer);
                                                bInStunden = (TabSp.getI("bits3") & cstInStunden) > 0;
                                                iStamm=TabSp.getI("Mass");
                                                bWS=true;
                                                //iRel=TabSp.getI("Rel");
                                                //g.progInfo(TabSp.getI("Reihenfolge")+":"+TabSp.getS("Format")+"/"+((TabSp.getI("bits")&cstEditierbar)>0));
						//if (bPeriode && VecPerioden==null)
						//	VecPerioden = new Vector(Zeitraum.VecPerioden);
                        //g.fixtestError("vor Art ermitteln: Code="+TabSp.getI("cod_aic_code")+", Typ="+sTyp);                      
            int iDArt=TabSp.getI("bits3")&cstDatenHolen;
              // g.fixtestError(sTyp+" holen "+(iDArt==cstNurAicD ? "nur Aic":iDArt==cstDanachD ? "Danach füllen":"normal"));
						sArt=((bSumme || sTyp.equals("Timestamp") || sTyp.equals("Mehrfach") || sTyp.equals("Rolle") || (iDArt==cstNurAicD) || (iDArt==cstDanachD)) && TabSp.getI("cod_aic_code")>0) ? g.TabCodes.getKennung(TabSp.getI("cod_aic_code")):sTyp.equals("BewStamm")?g.ASA()?"first":"":"sum";
//						g.fixtestError(sDefBez+": "+Obj+", Nr="+iNr+" -> sAic="+sAic+", sArt="+sArt+" bei "+TabSp.getI("Nummer"));
						//g.fixtestError("Art ermitteln: "+sArt);
						//VecGruppe=(Vector)TabSp.getInhalt("Gruppe");
						//bGruppe=VecGruppe != null;
                                                //if (bGruppe)
                                                //  sg="g"+TabSp.getInhalt("Nummer")+"_";
						//g.progInfo(TabSp.getS("Bezeichnung")+": Periode="+bPeriode+", Art="+sArt+", Gruppe="+VecGruppe);
					}
                                      }
                                //g.testInfo(sAic+":"+bWS+"/"+bEdit+"/"+bLeer+"/"+iStamm+"/"+sArt);
				String sPoolView=bST ? "poolview5":sPoolViewx;
					/*else
					{
						g.progInfo("nicht gefunden:"+Obj);
						if (g.Prog())
							TabSp.showGrid("TabSp");
					}*/
				//if (bSumme)
				//	g.progInfo(g.TabEigenschaften.getS("Kennung")+":"+sArt);
				///boolean bPeriode=sPeriode != null && g.TabEigenschaften.getB("Periode") && Zeitraum.VecPerioden!=null;

				//if (bPeriode)
				//	 g.progInfo(g.TabEigenschaften.getS("Kennung")+":"+sPeriode);
//				if (bWST)
//					g.fixtestError("Stichtag bei Typ "+sTyp+", Eig="+g.TabEigenschaften.getBezeichnungS(iAic)+"("+iAic+")");
				if (sTyp.equals("WWW") || sTyp.equals("Memo") || sTyp.equals("Pfad") || sTyp.equals("Filename"))
					sTyp = "Stringx";//"StringLang";
				else if (sTyp.equals("StringKurzOhne") || sTyp.equals("Passwort"))
					sTyp = "Stringx";//"StringKurz";
				else if (sTyp.equals("E-Mail") || sTyp.equals("FixDoku"))
					sTyp = "Stringx";//"String60";
				else if (sTyp.equals("Auto_von_bis"))
					sTyp = "von_bis";

                                if (bSpalten)
                                {
                                  //TabSp.push();
                                  //TabSp.showGrid("TabSp");
                                  if (!(Obj instanceof Integer || Obj instanceof Vector))
                                    g.defInfo("Vec="+Static.print(Obj));
                                  Vector Vec= TabSp.getVec("Vec",Obj instanceof Integer || Obj instanceof Long ? Static.AicToVec(Sort.geti(Obj)):Obj,"Anzeige");
                                  //g.progInfo("Vec="+Vec);
                                  //TabSp.pop();
                                  for(int i2=0;i2<Vec.size();i2++)
                                  {
                                    String sAnzeige = g.TabCodes.getKennung(((Integer)Vec.elementAt(i2)).intValue());
                                    boolean bStamm=!sTyp.startsWith("Bew");//iTyp==Formular.Stamm;
                                    String s1=bStamm ? sTyp.equals("Datum") ? "poolview2":sPoolView:"bew_pool";
                                    String s2=bStamm ? " and p.aic_eigenschaft=" + iAic:"";
                                    //g.progInfo(sAnzeige + "/" + iAic + "/" + sVor);
                                    if(sAnzeige.equals("user"))
                                      sGesamt += sNull + "select benutzer.kennung from benutzer join logging on logging.aic_benutzer=benutzer.aic_benutzer"+g.join("protokoll","p3","logging","logging")+" join " + s1 +
                                          " p on p.aic_protokoll=p3.aic_protokoll where p." + sVor + s2 + sN2 + "u" +sAic;
                                    else if(sAnzeige.equals("computer"))
                                      sGesamt += sNull + "select computer.kennung from computer"+g.join2("logging","computer")+g.join("protokoll","p3","logging","logging")+" join " + s1 +
                                          " p on p.aic_protokoll=p3.aic_protokoll where p." + sVor + s2 + sN2 + "c" +sAic;
                                    else if(sAnzeige.equals("os"))
                                      sGesamt += sNull + "select betriebssystem from systeminfo"+g.join2("logging","systeminfo")+g.join("protokoll","p3","logging","logging")+" join " + s1 +
                                          " p on p.aic_protokoll=p3.aic_protokoll where p." + sVor + s2 + sN2 + "o" +sAic;
                                    else if(sAnzeige.equals("place"))
                                      sGesamt += sNull + "select defbezeichnung from begriff join protokoll p3 on place=aic_begriff join " + s1 +
                                          " p on p.aic_protokoll=p3.aic_protokoll where p." + sVor + s2 + sN2 + "p" +sAic;
                                    else if(sAnzeige.equals("usage"))
                                      sGesamt += sNull + "select code.kennung from code"+g.join("protokoll","p3","code","code")+" join " + s1 +
                                          " p on p.aic_protokoll=p3.aic_protokoll where p." + sVor + s2 + sN2 + "q" +sAic;
                                    else if(sAnzeige.equals("timestamp"))
                                      sGesamt += sNull + "select timestamp from protokoll p3 join " + s1 + " p on p.aic_protokoll=p3.aic_protokoll where p." + sVor + s2 + sN2 + "t" +sAic;
                                    else if(sAnzeige.equals("date")) // Stichtag bzw Gültigkeit
                                      sGesamt += sNull + "select "+(bStamm ? "gultig_von":"gueltig")+" from " + s1 + " p where p." + sVor + s2 + sN2 + "a" +sAic;
                                    else if(sAnzeige.startsWith("AIC_") && !(sAnzeige.equals("AIC_Daten") && sTyp.equals("GPS"))) // Daten, Stammpool oder Protokoll
                                        sGesamt += sNull + "select "+sAnzeige+" from " + s1 + " p where p." + sVor + s2 + sN2 + (sAnzeige.equals("AIC_Daten") ? "d":"s") +sAic;
                                    else if(sAnzeige.equals("anr"))
                                      sGesamt += ",anr r"+sAic;
                                    else if(sAnzeige.startsWith("anr"))
                                      sGesamt += ","+sAnzeige+" r"+sAnzeige.substring(3)+"_"+sAic;
//                                    else if(sAnzeige.equals("anr2"))
//                                      sGesamt += ",anr2 r2_"+sAic;
//                                    else if(sAnzeige.equals("anr3"))
//                                      sGesamt += ",anr3 r3_"+sAic;
                                    else if(sAnzeige.equals("zone"))
                                      sGesamt += /*",(select bitsbew from bew_pool p where p."+sVor+") z"+sAic; */ ",zone z"+sAic;
                                    else if(sAnzeige.equals("ldate"))
                                      sGesamt += ",(select ldate from bew_pool p where p."+sVor+") l"+sAic;
                                    else if(sAnzeige.equals("ldate2"))
                                        sGesamt += ",(select ldate2 from bew_pool p where p."+sVor+") l2"+sAic;
                                    else if(sAnzeige.equals("ldate3"))
                                        sGesamt += ",(select ldate3 from bew_pool p where p."+sVor+") l3"+sAic;
                                  }
                                }
//                                g.fixtestError("Spalte "+sAic+": Ohr="+bOhr);
                                if (bOhr)
                                	sGesamt+=",null o"+sAic;
                                if (bWST)
                                	sGesamt+=",(select gultig_von from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+") s"+sAic;
//                                	sGesamt+=",(select gultig_von from "+sPoolView+" p join daten_"+sTyp+" d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+") s"+sAic;
                                //g.fixtestInfo(" <-------- "+sTyp+"/"+bPeriode+"/"+bGruppe+"/"+iTyp+"/"+iBew0);
				if((bPeriode || bGruppe) && (bLeer || //(iTyp==Formular.Stamm) != (iBew0>0)/*bSumme*/ ||
                                    !(sTyp.equals("BewVon_Bis")||sTyp.equals("BewWaehrung")||sTyp.equals("BewMass")||sTyp.equals("BewZahl")||sTyp.equals("BewStamm")||sTyp.equals("BewDatum")||sTyp.equals("BewDatum2")))
                                    && !sTyp.equals("Hierarchie") && !sTyp.equals("Gruppe") && !sTyp.equals("Boolean") && !sTyp.equals("BewBoolean"))
				{
					for(int iPer=0;iPer<(bPeriode ?PerAnz():VecGruppe.size());iPer++)
						sEinzeln+=",null e"+sAic+(bPeriode?"p":sg)+iPer;
				}
                                /*else if(bGruppe && ((iTyp==Formular.Stamm) != bSumme || !(sTyp.equals("BewVon_Bis")||sTyp.equals("BewWaehrung")||sTyp.equals("BewMass")||sTyp.equals("BewZahl"))))
                                {
                                        for(int iG=0;iG<VecGruppe.size();iG++)
                                                sEinzeln+=",null e"+sAic+"g"+iG;
                                }*/
				else if (sTyp.equals("Filler"))
					sEinzeln=","+(TabSp == null || TabSp.getI("aic_begriff")==0 ?"null":"'"+g.TabBegriffe.getKennung(TabSp.getI("aic_begriff"))+"'")+" s"+i;
				else if (sTyp.equals("SysAic") || sTyp.equals("SysAic2"))
					sEinzeln=(bEinfach?",p2.aic_"+(iTyp==Formular.Stamm?"stamm":"bew_pool"):","+sVor2)+(sTyp.equals("SysAic")?" e":" v")+sAic; //",(select "+sVor2+")")+" e"+sAic;
				else if (bLeer)
                                {
                                  sEinzeln="";
                                  if (VVerkn.contains(sTyp))
                                    sEinzeln += ",null v" + sAic;
                                  if (sTyp.endsWith("Hierarchie"))
                                    sEinzeln += ",null w" + sAic;
                                  if (VVonBis.contains(sTyp))
                                    sEinzeln+=",null v"+sAic+",null b"+sAic+(sTyp.equals("BewVon_Bis")?",null d"+sAic:"");
                                  else if (sTyp.equals("GPS"))
                                	sEinzeln+=",null d"+sAic+",null b"+sAic+",null l"+sAic+",null e"+sAic;
                                  else
                                    sEinzeln +=  ",null e" + sAic;
                                }
                else if (sTyp.equals("Kennung"))
					sEinzeln=(bEinfach?",kennung ":sNull+"select kennung from stammview2 s where aic_rolle is null and s."+sVor+sN2)+"e"+sAic;
				else if (sTyp.equals("Bezeichnung") || sTyp.equals("CalcBezeichnung"))
				{
				  //g.progInfo("          ------   "+sTyp+"/"+bEinfach+"/"+iStt+"/"+g.Translate(iStt));
                                  if (bEinfach && iTyp==Formular.Stamm && g.Translate(iStt))
                                    sEinzeln=g.AU_Bezeichnung2("Stamm","p2.aic_stamm","p2.Bezeichnung")+" e"+sAic;
                                  else
					sEinzeln=(bEinfach ? ","+(iTyp==Formular.Stamm?"p2.Bezeichnung":"Gueltig")+" ":sNull+"select Bezeichnung from "+sStammView+" p where aic_rolle is null and p."+sVor+sN2)+"e"+sAic; //sStammView
					//if (bEinfach && iTyp!=Formular.Stamm)
					//	sEinzeln+="=null";
				}
                                else if (sTyp.equals("Firma"))
                                {
                                  sEinzeln=(bEinfach ? ","+(iTyp==Formular.Stamm?"p2.Firma":"null")+" ":sNull+"select firma from "+sStammView+" p where aic_rolle is null and p."+sVor+sN2)+"v"+sAic;
                                }
				else if (sTyp.equals("Bezeichnung2"))
				{
					sEinzeln=/*iTyp!=Formular.Stamm ? ",null e"+sAic:*/",(select bezeichnung from bezeichnung where aic_tabellenname="+g.iTabStamm+
							" and aic_fremd="+(bEinfach ? "p2.aic_stamm":sVor2)+" and aic_sprache="+g.getSprache()+") e"+sAic;
				}
                                else if (sTyp.startsWith("CalcWaehrung"))
                                  sEinzeln=((iBits&cstSumme)>0?",0 e":",null e")+sAic+","+g.getWaehrung()+" v"+sAic;
                                else if (sTyp.equals("CalcString") && TabSp != null && TabSp.getI("aic_begriff")!=0)
                                  sEinzeln=",'"+g.TabBegriffe.getKennung(TabSp.getI("aic_begriff"))+"' e"+sAic;
				else if (sTyp.startsWith("Calc") || sTyp.equals("Ampel"))
					sEinzeln=((iBits&cstSumme)>0?",0 e":",null e")+sAic+(sTyp.equals("CalcMass") ? ",null v"+sAic:"");
                                else if (sTyp.equals("Stichtag"))
                                      sEinzeln=","+(iVB>0 ? "von"+iVB+"()":"(select von from zr)")+" e"+sAic;
                                else if (sTyp.equals("Rolle"))
                                {
                                  g.testInfo(sTyp+"/"+sArt);
                                  if (sArt==null || sArt.equals("sum"))
                                    sArt = "max";
                                  sEinzeln = sNull + "select "+sArt+"(aic_rolle) from " + sStammView + " p where aic_rolle is not null and p." + sVor + sN2 + "e" + sAic;
                                  //sEinzeln=(bEinfach?",aic_rolle ":"null ")+"e"+sAic;
                                }
				else if (sTyp.equals("Eintritt") || sTyp.equals("EinAustritt"))
                                {
                                  //sEinzeln=(bEinfach && iRolle==iRolle2 ?","+(iTyp==Formular.Stamm?"Eintritt":SQL.von())+" " :
                                  //g.fixInfo(sAic+"/"+sTyp+": bEinfach="+bEinfach+", iTyp="+iTyp+"/"+iRolle+"/"+iRolle2);
                                  sEinzeln = (bEinfach && (iRolle==iRolle2/*!bRolle*/ || iTyp == Formular.Bewegung) ? "," + (iTyp == Formular.Stamm ? "Eintritt" : g.von()) + " " :
                                              sNull + "select eintritt from "+sStammView+" p where" + Rolle2(iRolle2) +
                                              " and p." + sVor + sN2) + "e" + sAic;
                                }
				else if (sTyp.equals("Austritt"))
					//sEinzeln=(bEinfach && iRolle==iRolle2 ? ","+(iTyp==Formular.Stamm?"Austritt":SQL.bis())+" " :
                                        sEinzeln=(bEinfach && (iRolle==iRolle2/*!bRolle*/ || iTyp==Formular.Bewegung) ? ","+(iTyp==Formular.Stamm?"Austritt":g.bis())+" " :
                                        sNull+"select austritt from "+sStammView+" p where"+Rolle2(iRolle2)+" and p."+sVor+sN2)+"e"+sAic;
				else if (sTyp.equals("Integer") || sTyp.equals("Double") || sTyp.equals("Farbe"))
				{
					if(bPeriode)
                      for(int iPer=0;iPer<PerAnz();iPer++)
                      {
                        String sBis = PerBis(iPer);//g.DateTimeToString((Date)VecPerioden.elementAt(iPer + 1));
                        sEinzeln+=",(select "+sArt+"(spalte_Double) from stammpool p where p.pro2_aic_protokoll is null and p."+sVor+" and p.aic_eigenschaft="+iAic+
                            " and (gultig_von is null or gultig_von < " + sBis + ") and (gueltig_bis is null or gueltig_bis >= " + sBis + ")) e"+sAic+"p"+iPer;
                      }
                    else
                    	sEinzeln=sNull+"select spalte_Double from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
				}
				else if (sTyp.equals("Land") || sTyp.equals("Aufgabe") || sTyp.equals("Status"))
					sEinzeln=sNull+"select spalte_Double from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
				else if (sTyp.equals("Bool3"))
                  sEinzeln=sNull+"select spalte_Double from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
                                else if (sTyp.equals("Boolean"))
                                {
                                  String sSpalte=g.ASA() || g.MS() && (iBits&cstSumme)==0 ? "cast(spalte_Double as bit)":g.MySQL() && (iBits&cstSumme)==0 ? "cast(spalte_double as binary(1))":"spalte_Double";
                                  if(bPeriode)
                                    for(int iPer=0;iPer<PerAnz();iPer++)
                                    {
                                      String sBis = PerBis(iPer);//g.DateTimeToString((Date)VecPerioden.elementAt(iPer + 1));
                                      sEinzeln+=",(select "+sSpalte+" from stammpool p where p.pro2_aic_protokoll is null and p."+sVor+" and p.aic_eigenschaft="+iAic+
                                          " and (gultig_von is null or gultig_von < " + sBis + ") and (gueltig_bis is null or gueltig_bis >= " + sBis + ")) e"+sAic+"p"+iPer;
                                    }
                                  else
                                    sEinzeln=sNull+"select "+sSpalte+" from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
                                }               
				else if (sTyp.equals("Einheiten"))
					sEinzeln=sNull+"select spalte_Double from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic+
						sNull+"select v.kennung from "+sPoolView+" p,stammview v where p.sta_aic_stamm=v.aic_stamm and p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
				else if (sTyp.equals("StringSehrKurz") || sTyp.equals("StringKurz") || sTyp.equals("String60") || sTyp.equals("StringLang")
                                         || sTyp.equals("Text") || sTyp.equals("Stringx"))
        {
          // if (bDistinct && g.MS() && sTyp.equals("Text"))
          // {
          //   bDistinct=false;
          //   g.fixtestError("distinct ausgeschaltet bei "+sDefBez+" ("+iBegriff+")");//+" wegen Spalte "+sAic+")");
          // }
          if(TabSp == null || TabSp.getI("aic_begriff")==0)
					{
						if (bSumme)
						{
							if (iBew0==0)
								sEinzeln=",null e"+sAic;
							else
							{
								g.fixtestError("Stamm auf Bewegung mit "+sTyp);
								String sANR=g.getANRS(iBew0,iEig0,iBegriff);
								//,(select min(spalte_Text) from bewview v join poolview p on v.aic_bew_pool=p.aic_bew_pool and v.aic_bewegungstyp=4 and p.aic_eigenschaft=118 join daten_Text d on d.aic_daten=p.aic_daten where v.ANR=e1.aic_stamm) e4b136_118
								String sArt2=g.MS() && sTyp.equals("Text") ? "":sArt;
								if (sANR!=null)
	                                sEinzeln=",(select "+sArt2+"(spalte_"+sTyp+") from "+sBewView+" v join "+sPoolView+" p on v.aic_bew_pool=p.aic_bew_pool and v.aic_bewegungstyp="+iBew0+" and p.aic_eigenschaft="+iAic+                                		
	                                		" join daten_"+sTyp+" d on d.aic_daten=p.aic_daten where v."+sANR+"=e1.aic_stamm) e"+sAic;
	                            else
	                            	g.printError("Stamm auf Bew mit "+sTyp+" ohne ANR nicht möglich",iBegriff);
							}
						}
						else
            {
              int iDArt=TabSp==null ? cstDefDaten:TabSp.getI("bits3")&cstDatenHolen;
              // g.fixtestError(sTyp+" holen "+(iDArt==cstNurAicD ? "nur Aic":iDArt==cstDanachD ? "Danach füllen":"normal"));
              String sSp=(iDArt==cstNurAicD) || (iDArt==cstDanachD) ? sArt.equals("sum") ? "p.aic_daten":sArt+"(p.aic_daten)":"spalte_"+sTyp;//sArt.equals("sum") ? "spalte_"+sTyp:sArt+"(spalte_"+sTyp+")";
              // g.fixtestError(sTyp+" -> "+sSp+"/"+sArt);
              if (iDArt==cstNurAicD)
                sEinzeln=sNull+"select "+sSp+" from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"d"+sAic;
              else if (iDArt==cstDanachD)
                sEinzeln=sNull+"select "+sSp+" from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"d"+sAic+",null e"+sAic;
              else
							  sEinzeln=sNull+"select "+sSp+" from "+sPoolView+" p join daten_"+sTyp+" d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
            }
					}
					else
						sEinzeln=",'"+g.TabBegriffe.getKennung(TabSp.getI("aic_begriff"))+"' e"+sAic;
        }
        else if (sTyp.equals("Bild2") || sTyp.equals("Doku"))
            sEinzeln=sNull+"select filename from "+sPoolView+" p join daten_"+sTyp+" d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
				else if (sTyp.endsWith("Bild"))
					sEinzeln=sNull+"select filename from "+sPoolView+" p join daten_image d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
				else if (sTyp.equals("Datum"))
					sEinzeln=sNull+"select gultig_von from "+((iBits&cstChanges)>0 ? "poolview4":"poolview2")+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
				else if (sTyp.equals("Zeit"))
					sEinzeln=sNull+"select zeit_von from "+sPoolView+" p join zeit_von_bis d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
				//else if (sTyp.equals("Gruppe"))
				//	sEinzeln=sNull+"select p.sta_aic_stamm from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
				else if (sTyp.equals("Hierarchie") || sTyp.equals("Gruppe"))
                                {
                                  if(bPeriode)
                                    for(int iPer=0;iPer<PerAnz();iPer++)
                                    {
                                      String sBis = PerBis(iPer);//g.DateTimeToString((Date)VecPerioden.elementAt(iPer + 1));
                                      sEinzeln += ",(select p.sta_aic_stamm from stammpool p where p.pro2_aic_protokoll is null and p." + sVor + " and p.aic_eigenschaft=" + iAic +
                                          " and (gultig_von is null or gultig_von < " + sBis + ") and (gueltig_bis is null or gueltig_bis >= " + sBis + ")) v"+sAic+"p"+iPer;
                                    }
                                  else
                                    sEinzeln = sNull + "select p.sta_aic_stamm from " + sPoolView + " p where p." + sVor + " and p.aic_eigenschaft=" + iAic + sN2 + "v" + sAic;
                                }
				else if (sTyp.equals("von_bis"))
					sEinzeln=sNull+"select zeit_von from "+sPoolView+" p join zeit_von_bis d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic+
						sNull+"select zeit_bis from "+sPoolView+" p join zeit_von_bis d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"b"+sAic;
				else if (sTyp.equals("GPS"))
//					sEinzeln=sNull+"select lat from "+sPoolView+" p join daten_gps d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"b"+sAic+
//					sNull+"select lng from "+sPoolView+" p join daten_gps d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"l"+sAic+
//				    sNull+"select name from "+sPoolView+" p join daten_gps d on d.aic_daten=p.aic_daten where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
					sEinzeln=sNull+"select aic_daten from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"d"+sAic;
				else if (sTyp.equals("BenMass"))
					sEinzeln=sNull+"select spalte_double from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
				else if (sTyp.equals("Waehrung"))
                                {
                                  int iW=g.getWaehrung();
                                  //if (g.WW())
                                  //{
                                    //g.progInfo("Wechselwährung");
                                    //String sGueltig=bSumme && bGruppe ? "v.gueltig":"gueltig";
                                    String sFaktor=!g.bCC?"1":"(case when p.sta_aic_stamm="+iW+" then 1 else "+(iW==g.iEuro ?"1":"(select faktor from ww where aic_stamm="+iW+" and datum=gultig_von)")+
                                          "/(case when p.sta_aic_stamm="+g.iEuro+" then 1 else (select faktor from ww where aic_stamm=p.sta_aic_stamm and datum=gultig_von) end) end)";
                                    sEinzeln=sNull+"select p.spalte_double*"+sFaktor+" from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic+","+iW+" v"+sAic;
                                  /*}
                                  else if (g.bCC)
                                    sEinzeln=sNull+"select p.spalte_double/p4.spalte_double from "+sPoolView+" p, "+sPoolView+" p4 where p4.aic_eigenschaft="+g.iEigEurofaktor+" and p."+sVor+" and p4.aic_stamm=p.sta_aic_stamm and p.aic_eigenschaft="+iAic+sN2+"e"+sAic+","+iW+" v"+sAic;
                                  else
                                    sEinzeln=sNull+"select p.spalte_double from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic+","+iW+" v"+sAic;
                                  */
                                }
                                else if (sTyp.equals("Mass"))
					sEinzeln=sNull+"select p.spalte_double*p4.spalte_double from "+sPoolView+" p, "+sPoolView+" p4 where p4.aic_eigenschaft="+g.iEigFaktor+" and p."+sVor+" and p4.aic_stamm=p.sta_aic_stamm and p.aic_eigenschaft="+iAic+sN2+"e"+sAic+
						sNull+"select p.sta_aic_stamm from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
				else if (sTyp.equals("Mehrfach"))
					if (iTyp == Formular.Bewegung && bEinfach)
						sEinzeln=sNull+"select p.aic_stamm from Bew_Stamm p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic+",null a"+sAic;
					else if (sVor.startsWith("sta") || iTyp == Formular.Bewegung)
						sEinzeln=sNull+"select p.aic_stamm from "+sPoolView+" p where p."+(iTyp == Formular.Bewegung ? sVor.replaceFirst("aic_stamm", "sta_aic_stamm"):sVor)+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
					else
                        sEinzeln=sNull+"select count(*) from "+sPoolView+" p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic+
                                                (g.ASA() && sArt!= null && sArt.equals("list") ? ",(select list(bezeichnung) from poolview p,stammview v where p.sta_aic_stamm=v.aic_stamm and p."+sVor+" and p.aic_eigenschaft="+iAic+") a"+sAic:
							sNull+g.first("bezeichnung from stammview v,"+sPoolView+" p where p.sta_aic_stamm=v.aic_stamm and p."+sVor+" and p.aic_eigenschaft="+iAic)+sN2+"a"+sAic);
                else if (sTyp.equals("Timestamp"))
                {
                	//g.fixtestError(sTyp+": Art="+sArt+", iTyp="+iTyp);
                	if (sArt!=null && sArt.equals("max") && iTyp==Formular.Stamm)
                		sEinzeln=sNull+"select max(timestamp) from stamm_protokoll p,protokoll p4 where p.aic_protokoll=p4.aic_protokoll and p."+sVor+sN2+"e"+sAic;
                	else
                		sEinzeln=sNull+"select timestamp from "+(iTyp==Formular.Stamm ? "stamm_protokoll":"bew_pool")+" p,protokoll p4 where p.aic_protokoll=p4.aic_protokoll and p."+sVor+(iTyp==Formular.Stamm ? " and p.aic_code="+g.getBegriffAicS("Status","new"):Static.sLeer)+sN2+"e"+sAic;
                }
				else if (sTyp.equals("entfernt"))
				{
					if (iTyp==Formular.Stamm)
						sEinzeln=",null e"+sAic;
					else
						sEinzeln=sNull+"select timestamp from bew_pool p,protokoll p4 where p.pro_aic_protokoll=p4.aic_protokoll and p."+sVor+sN2+"e"+sAic;
				}
				else if (sTyp.equals("LoeschBenutzer"))
				{
					if (iTyp==Formular.Stamm)
						sEinzeln=",null v"+sAic+",null e"+sAic;//Static.printError("Datentyp 'entfernt' mit Stamm nicht möglich!");
					else
						sEinzeln=sNull+"select aic_benutzer from bew_pool p,protokoll p4 where p.pro_aic_protokoll=p4.aic_protokoll and p."+sVor+sN2+"v"+sAic;
				}
				else if (sTyp.equals("Benutzer"))
				{
					boolean bS=iTyp==Formular.Stamm || !bEinfach;
					sEinzeln=sNull+"select aic_benutzer from "+(bS ? "stamm_protokoll p,":"")+"protokoll p4 where "+(bS ?"p":"p2")+".aic_protokoll=p4.aic_protokoll"+
							(bS ? " and p."+sVor+" and p.aic_code="+g.getBegriffAicS("Status","new"):Static.sLeer)+sN2+"v"+sAic;
				}
                else if (sTyp.equals("BenutzerPasswort"))
                	sEinzeln=",'???' e"+sAic;
                else if (sTyp.equals("Anlage"))
					sEinzeln=sNull+"select p4.aic_code from "+(iTyp==Formular.Stamm ? "stamm_protokoll":"bew_pool")+" p,protokoll p4"+g.join("code","p4")+" where p.aic_protokoll=p4.aic_protokoll and p."+sVor+(iTyp==Formular.Stamm ? " and p.aic_code="+g.getBegriffAicS("Status","new"):Static.sLeer)+sN2+"v"+sAic;
				else if (sTyp.equals("Mandant"))
                                {
                                  boolean bStamm=iTyp == Formular.Stamm || !bEinfach;
                                  sEinzeln = sNull + "select aic_Mandant from " + (bStamm ? sStammView : "bew_pool") + " p where p." + sVor + (bStamm? Rolle(iRolle2):"") + sN2 + "v" + sAic;
                                }
				else if (sTyp.equals("Aic"))
					sEinzeln=(bLeer2 ? ",null e":",number(*) e")+sAic;
		        else if (sTyp.equals("Vorgaenger"))
		        {
		          if (iTyp == Formular.Bewegung)
		            sEinzeln = sNull + "select bew_aic_bew_pool from bew_pool p where p." + sVor + sN2 + "e" + sAic;
		          else
		            sEinzeln = sNull + "select sta_aic_stamm from stamm s where s."+sVor+sN2+"e"+sAic;
		        }
				else if (sTyp.equals("BewBool3"))
                                  sEinzeln=sNull+"select p.aic from Bew_Aic p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
                else if (sTyp.equals("BewBoolean"))
                  if (bPeriode)
                      for(int iPer=0;iPer<PerAnz();iPer++)
                        sEinzeln+=",(case when gueltig>="+PerVon(iPer,bAbNeujahr)+" and gueltig<"+PerBis(iPer)+
                          " then (select max(spalte_Boolean) from Bew_Boolean p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic+"p"+iPer;
                  else if (bSumme)
						sEinzeln=",("+g.first("p.spalte_Boolean from Bew_Boolean p"+g.join(sBewView,"v","p","bew_pool")+g.join("bew_stamm","s","v","bew_pool")+" where v.aic_bewegungstyp="+
							iBew0+" and p.aic_eigenschaft="+iAic+" and s.aic_eigenschaft="+iEig0+" and s.aic_stamm=e1.aic_stamm")+") e"+sAic;
					else
						sEinzeln=iTyp==Formular.Stamm ? ",null e"+sAic:sNull+"select "+
                                                    ((iBits&cstSumme)>0 && g.MS() && bWS ? "cast(p.Spalte_Boolean as int)":"p.Spalte_Boolean")+
                                                    " from Bew_Boolean p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
				else if (sTyp.equals("BewDatum"))
                                {
                                  if (bSumme)
                                    if(bGruppe)  // Horizontale Auflösung von Stamm
                                            for(int iG=0;iG<VecGruppe.size();iG++)
                                            {
                                              Vector Vec=NodToVec(VecGruppe.elementAt(iG));
                                              String sANR=g.getANRS(iBew0,iEig0,iBegriff);
                                              String sANR2=g.getANRS(iBew0,HA_Eig(VecGruppe.elementAt(iG)),iBegriff);
                                              if (sANR!=null && sANR2!=null)
                                              {
                                                sEinzeln += ",(select " + sArt + "(gueltig) from "+sBewView+" p" +
                                                    " where aic_bewegungstyp=" + iBew0 + " and " + sANR + "=p2.aic_stamm"+" and "+sANR2+Static.SQL_in2(Vec)+") e"+sAic+sg+iG;
                                              }
                                              else
                                                Static.printError("Horizontale Auflösung von BewDatum ohne ANR nicht möglich");
                                            }
                                    else
                                      sEinzeln=",(select "+sArt+"(gueltig) from "+sBewView+" p"+g.join("bew_stamm","s","p","bew_pool")+" where p.aic_bewegungstyp="+
							iBew0+" and s.aic_eigenschaft="+iEig0+" and s.aic_stamm=e1.aic_stamm) e"+sAic;
                                  else
                                    sEinzeln = iTyp == Formular.Stamm ? ",null e" + sAic :(bEinfach ? ",Gueltig" :
                                        ",(select p.Gueltig from bew_pool p where p." + sVor + ")") + " e" + sAic;
                                }
                                else if (sTyp.equals("Protokoll"))
                                  sEinzeln = iTyp == Formular.Stamm ? ",null v" + sAic :(bEinfach ? ",aic_protokoll" :
                                        ",(select p.aic_protokoll from bew_pool p where p." + sVor + ")") + " v" + sAic;
                                else if (sTyp.equals("BewBew") && !bBewS)
                                {
                                  sEinzeln = (bEinfach ? ",BEW2_AIC_BEW_POOL" :",(select p.BEW2_AIC_BEW_POOL from bew_pool p where p." + sVor + ")") + " e" + sAic;
                                }
				else if (sTyp.equals("BewDauer"))
					sEinzeln=iTyp==Formular.Stamm ? ",null e"+sAic:sNull+"select p.dauer from Bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
				else if (sTyp.equals("BewStamm"))
                                {
                                  //g.fixInfo(" ---------->    Typ="+sTyp+"/"+iTyp+"/"+bPeriode+"/"+bEinfach+"/"+bSumme);
					if (iTyp == Formular.Bewegung)
                                        {
                                          //g.testInfo(g.TabEigenschaften.getBezeichnungS(iAic)+":"+iRolle+"->"+g.RolleToStt(iRolle)+","+iAic+"->"+g.EigToStt(iAic));
                                          if (bPeriode)
                                          {
                                            boolean bBild=(TabSp.getI("bits")&cstBild)>0;
                                            for(int iPer=0;iPer<PerAnz();iPer++)
                                              sEinzeln+=",(case when gueltig>="+PerVon(iPer,bAbNeujahr)+" and gueltig<"+PerBis(iPer)+
                                                  " then (select p.aic_stamm from Bew_Stamm p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) "+(bBild?"e":"v")+sAic+"p"+iPer;
                                          }
                                          else if (bEinfach && g.isANR(iBew,iAic))// && (Transact.iInfos&Transact.NO_SPEED)==0)//!bKeinANR && (iBits&cstANR)>0 && iEigStt==g.iSttANR)
                                          {
                                            String sANR=g.getANRS(iBew,iAic,iBegriff);
                                            if (iRolle>0 && iRollex==0)
                                              iRollex=iRolle;
                                            if (iRollex>0 && g.RolleToStt(iRollex)==g.EigToStt(iAic)) // sStammView laut Beschluss mit Kurt&Isi am 3.7.2012 wieder entfernt
                                              sEinzeln=sNull+"select v.aic_stamm from stammview2 v where v.aic_stamm=p2."+sANR+" and aic_rolle="+iRollex+sN2+"v"+sAic;
                                            else
                                              sEinzeln = "," + sANR + " v" + sAic;
                                          }
                                          else if (iRolle>0 && g.RolleToStt(iRolle)==g.EigToStt(iAic))
                                            sEinzeln=sNull+"select p.aic_stamm from Bew_Stamm p join stammview2 v on p.aic_stamm=v.aic_stamm and aic_rolle="+iRolle+" where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
                                          else
						sEinzeln=sNull+"select p.aic_stamm from Bew_Stamm p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
                                        }
                                        else
						if (bSumme)
                                                {
                                                  boolean bBild=(TabSp.getI("bits")&cstBild)>0;
                                                  g.progInfo(sAic+": Bild="+bBild);
                                                  //boolean bANR1=g.isANR(iBew0,iEig0) && (Transact.iInfos&Transact.NO_SPEED)==0;
                                                  String sANR=g.getANRS(iBew0,iEig0,iBegriff);
                                                  //boolean bANR2=g.isANR(iBew0,iAic) && (Transact.iInfos&Transact.NO_SPEED)==0;
                                                  String sANR2=g.getANRS(iBew0,iAic,bPeriode ? iBegriff:0);
                                                  if (Static.Leer(sArt) || sArt.equals("last") || sArt.equals("list"))
                                                	  sArt="min";
                                                  else if (sArt.indexOf("count_")>-1)
                                                	  sArt="count";
                                                  //g.progInfo(sTyp+"/"+bPeriode+"/"+sANR+"/"+sANR2+", Art="+sArt);
                                                  if(bPeriode)
                                                  {
                                                    if (sANR!=null && sANR2!=null)
                                                    {
                                                      if (bGanzesjahr)
                                                      {
                                                        DateWOD DW=DateWOD.getNewYear(getVon());
                                                        for(int iPer = 0; iPer < 12; iPer++)
                                                        {
                                                          sEinzeln += ",(select " + sANR2 + " from bewview2 where aic_bewegungstyp=" + iBew0 +
                                                              " and p2.aic_stamm=" + sANR + " and " + g.DateTimeToString(DW.toDate()) + "<=gueltig and ";
                                                          DW.nextMonth();
                                                          sEinzeln += g.DateTimeToString(DW.toDate()) + ">gueltig) v" + sAic + "p" + iPer;
                                                        }
                                                      }
                                                      else
                                                        for(int iPer = 0; iPer < PerAnz(); iPer++)
                                                          sEinzeln += ",(select " + sANR2 + " from bewview2 where aic_bewegungstyp=" + iBew0 + " and " + sANR + "=p2.aic_stamm and "
                                                            + PerVon(iPer, bAbNeujahr) + "<=gueltig and " + PerBis(iPer) + ">gueltig) v" + sAic + "p" + iPer;
                                                    }
                                                    else
                                                      for (int iPer = 0; iPer < PerAnz(); iPer++)
                                                        sEinzeln += ",(" +g.first("p.aic_stamm from bew_stamm p" + g.join("bew_pool", "v", "p", "bew_pool") +
                                                          g.join("bew_stamm", "s", "v", "bew_pool") + " where v.aic_bewegungstyp=" + iBew0 +
                                                          " and v.pro_aic_protokoll is null and " + PerVon(iPer, bAbNeujahr) + "<=gueltig and " + PerBis(iPer) + ">gueltig" +
                                                          " and p.aic_eigenschaft=" + iAic + " and s.aic_eigenschaft=" + iEig0 + " and s.aic_stamm=p2.aic_stamm") + ") v" + sAic +
                                                          "p" + iPer;
                                                  }
                                                          //","+SQL.ifnull("v"+sAic+"p"+iPer,"null","(select bezeichnung from stamm_protokoll p where p.pro_aic_protokoll is null and aic_stamm=v"+sAic+"p"+iPer+")")+" x"+sAic+"p"+iPer+g.AU_Bezeichnung2("Stamm","v"+sAic+"p"+iPer,"x"+sAic+"p"+iPer)+" e"+sAic+"p"+iPer;
                                                  else if (sANR!=null && sANR2!=null)
                                                      sEinzeln = ",(select " + sArt + "(v."+sANR2+") from "+sBewView+" v where v.aic_bewegungstyp="+iBew0+
                                                          " and v."+sANR+"=e1.aic_stamm and v."+sANR2+" is not null) v" + sAic+(bBild?"":
                                                          ",(select " + sArt + "(bezeichnung) from "+sStammView+" join "+sBewView+" v on "+sStammView+".aic_stamm=v."+sANR2+
                                                          " where v.aic_bewegungstyp=" + iBew0 + " and v."+sANR+"=e1.aic_stamm and v."+sANR2+" is not null) e" + sAic);
                                                    else if (sANR!=null) //((iBits&cstANR)>0 && !bKeinANR)
                                                      sEinzeln = ",(select " + sArt + "(p.aic_stamm) from bew_stamm p"+g.join(sBewView,"v","p","bew_pool")+" where v.aic_bewegungstyp=" +
                                                        iBew0 + " and p.aic_eigenschaft=" + iAic + " and v."+sANR+"=e1.aic_stamm) v" + sAic+(bBild?"":
                                                          ",(select " + sArt + "(bezeichnung) from "+sStammView+g.join("bew_stamm","p",sStammView,"stamm")+g.join(sBewView,"v","p","bew_pool")+" where v.aic_bewegungstyp=" +
                                                        iBew0 + " and p.aic_eigenschaft=" + iAic + " and v."+sANR+"=e1.aic_stamm) e" + sAic);
                                                    else
                                                      sEinzeln = ",(select " + sArt + "(p.aic_stamm) from bew_stamm p"+g.join(sBewView,"v","p","bew_pool")+g.join("bew_stamm","s","v","bew_pool")+" where v.aic_bewegungstyp=" +
                                                        iBew0 + " and p.aic_eigenschaft=" + iAic + " and s.aic_eigenschaft=" + iEig0 + " and s.aic_stamm=e1.aic_stamm) v" + sAic+(bBild?"":
                                                          ",(select " + sArt + "(bezeichnung) from "+sStammView+g.join("bew_stamm","p",sStammView,"stamm")+g.join(sBewView,"v","p","bew_pool")+g.join("bew_stamm","s","v","bew_pool")+" where v.aic_bewegungstyp=" +
                                                        iBew0 + " and p.aic_eigenschaft=" + iAic + " and s.aic_eigenschaft=" + iEig0 + " and s.aic_stamm=e1.aic_stamm) e" + sAic);
                                                }
						else
							sEinzeln=",null v"+sAic+",null e"+sAic;
                                }
                                else if (sTyp.equals("BewHierarchie"))
                                  if (iTyp == Formular.Bewegung)
					sEinzeln=sNull+"select p.aic_stamm from Bew_Stamm p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
                                      else
							sEinzeln=",null v"+sAic+",null e"+sAic;
				else if (sTyp.equals("BewModell") || sTyp.equals("BewDruck") || sTyp.equals("BewFormular") || sTyp.equals("BewModul"))
					sEinzeln=sNull+"select p.aic_begriff from Bew_Begriff p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
				else if (sTyp.equals("BewLand") || sTyp.equals("BewHoliday") || sTyp.equals("BewBG") || sTyp.equals("BewUser"))
					sEinzeln=sNull+"select p.aic from Bew_Aic p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic;
				else if (sTyp.equals("User"))
					sEinzeln=sNull+"select max(aic_benutzer) from benutzer p where p."+sVor+sN2+"v"+sAic;
				else if (sTyp.equals("BewDatum2"))
					if(bSumme)
                                          if(bGruppe)  // Horizontale Auflösung von Stamm
                                            for(int iG=0;iG<VecGruppe.size();iG++)
                                            {
                                              Vector Vec=NodToVec(VecGruppe.elementAt(iG));
                                              int iEig1=HA_Eig(VecGruppe.elementAt(iG));
                                              String sANR=g.getANRS(iBew0,iEig0,iBegriff);
                                              String sANR2=g.getANRS(iBew0,iEig1,iBegriff);
                                              if (sANR!=null && sANR2!=null)
                                              {
                                                sEinzeln += ",(select " + sArt + "(von) from bew_von_bis p" + g.join(sBewView, "v", "p", "bew_pool")+
                                                    " where v.aic_bewegungstyp=" + iBew0 + " and " + sANR + "=p2.aic_stamm"+" and "+sANR2+Static.SQL_in2(Vec)+
                                                    " and p.aic_eigenschaft=" + iAic +") e"+sAic+sg+iG;
                                              }
                                              else
                                                Static.printError("Horizontale Auflösung von BewDatum2 ohne ANR nicht möglich");
                                            }
                                          else
                                          {
                                            String sANR=g.getANRS(iBew0,iEig0,iBegriff);
                                            if (sANR!=null)
                                              sEinzeln=",(select "+sArt+"(von) from bew_von_bis p"+g.join(sBewView, "v", "p", "bew_pool")+" where v.aic_bewegungstyp="+iBew0+
                                                  " and p.aic_eigenschaft="+iAic+" and v."+sANR+"=e1.aic_stamm) e" + sAic;
                                            else
                                            {
                                              g.fixInfo("ANR für BewDatum2 bei Abfrage ["+sDefBez+"] nicht vorhanden");
                                              sEinzeln = ",(select " + sArt + "(von) from bew_von_bis p" + g.join(sBewView, "v", "p", "bew_pool") +
                                                  g.join("bew_stamm", "s", "v", "bew_pool") + " where v.aic_bewegungstyp=" +
                                                  iBew0 + " and p.aic_eigenschaft=" + iAic + " and s.aic_eigenschaft=" + iEig0 + " and s.aic_stamm=e1.aic_stamm) e" + sAic;
                                            }
                                          }
					else
						sEinzeln= iTyp==Formular.Stamm ? ",null e"+sAic:
							sNull+"select p.von from Bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
				else if (sTyp.equals("BewVon_Bis")) // ----------- BewVon_Bis --------------
                                {
                                  if (bBewS)
                                    {
                                      if(bPeriode)
                                        for(int iPer=0;iPer<PerAnz();iPer++)
                                          sEinzeln+=",(select "+sArt+"(dauer) from bew_von_bis p"+g.join("bew_pool","v","p","bew_pool")+" where v.bew2_aic_bew_pool=p2.aic_bew_pool and p.aic_eigenschaft="+iAic+
                                              " and v.pro_aic_protokoll is null and "+PerVon(iPer,bAbNeujahr)+"<=gueltig and "+PerBis(iPer)+">gueltig) e"+sAic+"p"+iPer;
                                      else
                                        sEinzeln=",null v"+sAic+",null b"+sAic+",(select "+sArt+"(dauer) from bew_von_bis p"+g.join(sBewView,"v","p","bew_pool")+
                                            " where v.bew2_aic_bew_pool=e1.aic_bew_pool and p.aic_eigenschaft="+iAic+") e"+sAic;
                                    }
                                    else if (bSumme)
					{
                                          String sANR1=g.getANRS(iBew0,iEig0,iBegriff);
                                          String s1=sANR1!=null ?"":g.join("bew_stamm","s","v","bew_pool");
                                          String s2=sANR1!=null ? "v."+sANR1:"s.aic_eigenschaft="+iEig0+" and s.aic_stamm";
                                          if(bGruppe)  // Horizontale Auflösung von Stamm
                                            for(int iG=0;iG<VecGruppe.size();iG++)
                                            {
                                              Vector Vec=NodToVec(VecGruppe.elementAt(iG));
                                              boolean bDat=Sort.geti(Vec,0)>0;
                                              int iEig1=HA_Eig(VecGruppe.elementAt(iG));
                                              String sANR2=bDat && iEig1>0 ? g.getANRS(iBew0,iEig1,iBegriff):null;
                                              if (sANR2!=null && sANR1!=null)
                                              {
                                                sEinzeln += ",(select " + sArt + "(dauer) from bew_von_bis p" + g.join(sBewView, "v", "p", "bew_pool")+
                                                    " where v.aic_bewegungstyp=" + iBew0 + " and " + s2 + "=p2.aic_stamm"+" and v."+sANR2+Static.SQL_in2(Vec)+
                                                    " and p.aic_eigenschaft=" + iAic + ") e" + sAic + sg + iG;
                                              }
                                              else
                                              sEinzeln += ",(select " + sArt + "(dauer) from bew_von_bis p" + g.join(sBewView, "v", "p", "bew_pool") + s1 +
                                                  (sANR1!=null ? "" : g.join(sBewView, "s", "bew_pool")) + g.join("bew_stamm", "s2", sANR1!=null ? "v" : sBewView, "bew_pool") +
                                                  " where v.aic_bewegungstyp=" + iBew0 + " and p.aic_eigenschaft=" + iAic +
                                                  " and " + s2 + "=p2.aic_stamm"+(iEig1>0?" and s2.aic_eigenschaft=" + iEig1:"")+
                                                  (bDat ? " and s2.aic_stamm"+Static.SQL_in2(Vec):"") + ") e" + sAic + sg + iG;
                                            }
                                          else if(bPeriode) // Periodische Auflösung von Stamm
							for(int iPer=0;iPer<PerAnz();iPer++)
								sEinzeln+=",(select "+sArt+"(dauer) from bew_von_bis p"+g.join("bew_pool","v","p","bew_pool")+s1+" where v.aic_bewegungstyp="+iBew0+
									" and v.pro_aic_protokoll is null and "+PerVon(iPer,bAbNeujahr)+"<=gueltig and "+PerBis(iPer)+">gueltig"+
									" and p.aic_eigenschaft="+iAic+" and "+s2+"=p2.aic_stamm) e"+sAic+"p"+iPer;
                                          else  // Summenbildung von Stamm
							sEinzeln=",null v"+sAic+",null b"+sAic+",(select "+sArt+"(dauer) from bew_von_bis p"+g.join(sBewView,"v","p","bew_pool")+s1+" where v.aic_bewegungstyp="+iBew0+
								" and p.aic_eigenschaft="+iAic+" and "+s2+"=e1.aic_stamm) d"+sAic;
						//g.progInfo("BewVon_Bis: bSumme="+bSumme+", bPeriode="+bPeriode);
					}
					else
                                          if (iTyp==Formular.Stamm)
						sEinzeln=",null v"+sAic+",null b"+sAic+",null d"+sAic;
                                          else if(bGruppe)   // Horizontale Auflösung von Bewegung
                                            for(int iG=0;iG<VecGruppe.size();iG++)
                                            {
                                              Vector Vec=NodToVec(VecGruppe.elementAt(iG));
                                              int iEig1=HA_Eig(VecGruppe.elementAt(iG));
                                              //g.progInfo("HA-Bew:"+Vec+"/"+iEig1);
                                              sEinzeln += ",(case when (select count(*) from bew_stamm where " + sVor + (iEig1>0?" and aic_eigenschaft=" + iEig1:"")+
                                                  (Sort.geti(Vec,0)>0 ?" and aic_stamm" + Static.SQL_in2(Vec):"") +
                                                  ")>0 then (select dauer from bew_von_bis p where p." + sVor + " and p.aic_eigenschaft=" + iAic + ") else null end) e" + sAic + sg + iG;
                                            }
                                          else if (bPeriode) // Periodische Auflösung von Bewegung
                                            for(int iPer=0;iPer<PerAnz();iPer++)
                                              sEinzeln+=",(case when gueltig>="+PerVon(iPer,bAbNeujahr)+" and gueltig<"+PerBis(iPer)+
                                                  " then (select dauer from bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic+"p"+iPer;
                                          else if (bVorZR)
                                          {
                                            sEinzeln=sNull+"select von from Bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic+
                                            sNull+"select bis from Bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"b"+sAic+
                                            ",(case when gueltig<"+g.von()+" then (select dauer from Bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic+"vor"+
                                            ",(case when gueltig>="+g.von()+" and gueltig<"+g.bis()+" then (select dauer from Bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic;
                                          }
                                          else if (sVor.startsWith("aic_stamm"))
                                            sEinzeln=",null v"+sAic+",null b"+sAic+",null d"+sAic;
                                          else
                                            sEinzeln=sNull+"select von from Bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic+
                                                sNull+"select bis from Bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"b"+sAic+
                                                sNull+"select dauer"+(bInStunden ?"/3600":"")+" from Bew_von_bis p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"d"+sAic;
                                }
                                else if (sTyp.equals("BewWaehrung")) // ----------- BewWaehrung --------------
                                  //if (g.WW())
                                  {
                                    int iW=iStamm==0 ? g.getWaehrung():iStamm;
                                    String sGueltig=bSumme && bGruppe ? "v.gueltig":"gueltig";
                                    String sFaktor=!g.bCC?"":"*(case when p.aic_stamm="+iW+" then 1 else "+(iW==g.iEuro ?"1":"(select faktor from ww where aic_stamm="+iW+" and datum="+sGueltig+")")+
                                          "/(case when p.aic_stamm="+g.iEuro+" then 1 else (select faktor from ww where aic_stamm=p.aic_stamm and datum="+sGueltig+") end) end)";
                                    if (bBewS)
                                    {
                                      if(bPeriode)
                                      {
                                        //DateWOD DW=bAbNeujahr ? DateWOD.getNewYear(g.getVon()):null;
                                        for(int iPer=0;iPer<PerAnz();iPer++)
                                          sEinzeln+=",(select "+sArt+"(spalte_Wert"+sFaktor+") from bew_Wert p"+g.join("bew_pool","v","p","bew_pool")+
                                              " where v.bew2_aic_bew_pool=p2.aic_bew_pool and p.aic_eigenschaft="+iAic+" and v.pro_aic_protokoll is null and "+
                                              (bKumuliert && !bAbNeujahr ? "": PerVon(iPer,bAbNeujahr)+"<=gueltig and ")+PerBis(iPer)+">gueltig) e"+sAic+"p"+iPer;
                                      }
                                      else
                                        sEinzeln=",(select "+sArt+"(spalte_Wert"+sFaktor+") from bew_Wert p"+g.join(sBewView,"v","p","bew_pool")+
                                            " where v.bew2_aic_bew_pool=e1.aic_bew_pool and p.aic_eigenschaft="+iAic+") e"+sAic;
                                    }
                                    else if (bSumme)
                                    {
                                      String sANR1=g.getANRS(iBew0,iEig0,iBegriff);//=(iBits&cstANR)>0 && !bKeinANR;
                                      String s1=sANR1!=null ?"":g.join("bew_stamm","s","v","bew_pool");
                                      String s2=sANR1!=null ?"v."+sANR1:"s.aic_eigenschaft="+iEig0+" and s.aic_stamm";
                                      if(bGruppe)
                                        for(int iG=0;iG<VecGruppe.size();iG++)
                                        {
                                          Vector Vec=NodToVec(VecGruppe.elementAt(iG));
                                          boolean bDat=Sort.geti(Vec,0)>0;
                                          int iEig1=HA_Eig(VecGruppe.elementAt(iG));
                                          String sANR2=bDat && iEig1>0 ? g.getANRS(iBew0,iEig1,iBegriff):null;
                                          if (sANR2!=null && sANR1!=null)
                                            sEinzeln += ",(select " + sArt + "(spalte_Wert" + sFaktor + ") from bew_Wert p" + g.join(sBewView, "v", "p", "bew_pool")+
                                                " where v.aic_bewegungstyp=" + iBew0 + " and " + s2 + "=p2.aic_stamm"+" and v."+sANR2+Static.SQL_in2(Vec)+
                                                " and p.aic_eigenschaft=" + iAic + ") e" + sAic + sg + iG;
                                          else
                                            sEinzeln += ",(select " + sArt + "(spalte_Wert" + sFaktor + ") from bew_Wert p" + g.join(sBewView, "v", "p", "bew_pool") + s1 +
                                              (sANR1!=null ? "" : g.join(sBewView, "s", "bew_pool")) + g.join("bew_stamm", "s2", sANR1!=null ? "v" : sBewView, "bew_pool") +
                                              " where v.aic_bewegungstyp=" + iBew0 +" and p.aic_eigenschaft=" + iAic +
                                              " and " + s2 + "=p2.aic_stamm"+(iEig1>0?" and s2.aic_eigenschaft=" + iEig1:"")+
                                              (bDat ? " and s2.aic_stamm" + Static.SQL_in2(Vec):"") +") e" + sAic + sg + iG;
                                        }
                                      else if(bPeriode)
                                      {
                                        //DateWOD DW=bAbNeujahr ? DateWOD.getNewYear(g.getVon()):null;
                                        for(int iPer=0;iPer<PerAnz();iPer++)
                                          sEinzeln+=",(select "+sArt+"(spalte_Wert"+sFaktor+") from bew_Wert p"+g.join("bew_pool","v","p","bew_pool")+s1+
                                              " where v.aic_bewegungstyp="+iBew0+" and v.pro_aic_protokoll is null and "+
                                              (bKumuliert && !bAbNeujahr ? "": PerVon(iPer,bAbNeujahr)+"<=gueltig and ")+PerBis(iPer)+">gueltig"+
                                              " and p.aic_eigenschaft="+iAic+" and "+s2+"=p2.aic_stamm) e"+sAic+"p"+iPer;
                                      }
                                      else
                                        sEinzeln=",(select "+sArt+"(spalte_Wert"+sFaktor+") from bew_Wert p"+g.join(sBewView,"v","p","bew_pool")+s1+" where v.aic_bewegungstyp="+iBew0+
                                            " and p.aic_eigenschaft="+iAic+" and "+s2+"=e1.aic_stamm) e"+sAic;
						//g.progInfo("BewWaehrung: bSumme="+bSumme+", bPeriode="+bPeriode+", bGruppe="+bGruppe);
                                    }
                                    else if (iTyp==Formular.Stamm)
                                      sEinzeln=",null e"+sAic;
                                    else if(bGruppe)
                                     for(int iG=0;iG<VecGruppe.size();iG++)
                                     {
                                       Vector Vec=NodToVec(VecGruppe.elementAt(iG));
                                       int iEig1=HA_Eig(VecGruppe.elementAt(iG));
                                       sEinzeln+=",(case when (select count(*) from bew_stamm where "+sVor+(iEig1>0?" and aic_eigenschaft=" + iEig1:"")+
                                                  (Sort.geti(Vec,0)>0 ?" and aic_stamm" + Static.SQL_in2(Vec):"") +
                                                  ")>0 then (select spalte_Wert"+sFaktor+" from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic+sg+iG;
                                    }
                                    else if (bPeriode)
                                    {
                                      //DateWOD DW=bAbNeujahr ? DateWOD.getNewYear(g.getVon()):null;
                                      for(int iPer=0;iPer<PerAnz();iPer++)
                                        sEinzeln+=",(case when"+(bKumuliert && !bAbNeujahr ? "":" gueltig>="+PerVon(iPer,bAbNeujahr)+" and")+" gueltig<"+PerBis(iPer)+
                                            " then (select spalte_Wert"+sFaktor+" from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic+"p"+iPer;
                                    }
                                    else if (bEdit)
                                      sEinzeln=",(select spalte_Wert from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") e"+sAic+",(select aic_stamm from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") v"+sAic;
                                    else
                                      sEinzeln=",(select spalte_Wert"+sFaktor+" from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") e"+sAic+","+iW+" v"+sAic;
                                  }
                                  /*else
                                  {
					if (bSumme)
					{
						if(bGruppe)
							for(int iG=0;iG<VecGruppe.size();iG++)
								sEinzeln+=",(select "+sArt+"(spalte_Wert"+(!g.bCC?"":"/(select spalte_double from poolview p4 where p4.aic_stamm=p.aic_stamm and p4.aic_eigenschaft="+g.iEigEurofaktor+")")+
									") from bew_Wert p"+g.join(sBewView,"v","p","bew_pool")+g.join("bew_stamm","s","v","bew_pool")+g.join(sBewView,"s","bew_pool")+g.join("bew_stamm","s2",sBewView,"bew_pool")+" where v.aic_bewegungstyp="+iBew0+
									" and p.aic_eigenschaft="+iAic+" and s.aic_eigenschaft="+iEig0+" and s.aic_stamm=p2.aic_stamm and s2.aic_stamm"+Static.SQL_in(NodToVec(VecGruppe.elementAt(iG)))+") e"+sAic+sg+iG;
						else if(bPeriode)
							for(int iPer=0;iPer<VecPerioden.size()-1;iPer++)
								sEinzeln+=",(select "+sArt+"(spalte_Wert"+(!g.bCC?"":"/(select spalte_double from poolview p4 where p4.aic_stamm=p.aic_stamm and p4.aic_eigenschaft="+g.iEigEurofaktor+")")+
									") from bew_Wert p"+g.join("bew_pool","v","p","bew_pool")+g.join("bew_stamm","s","v","bew_pool")+" where v.aic_bewegungstyp="+iBew0+
									" and v.pro_aic_protokoll is null and "+SQL.DateTimeToString((Date)VecPerioden.elementAt(iPer),g)+"<=gueltig and "+SQL.DateTimeToString((Date)VecPerioden.elementAt(iPer+1),g)+">gueltig "+
									" and p.aic_eigenschaft="+iAic+" and s.aic_eigenschaft="+iEig0+" and s.aic_stamm=e1.aic_stamm) e"+sAic+"p"+iPer;
						else
							sEinzeln=",(select "+sArt+"(spalte_Wert"+(!g.bCC?"":"/(select spalte_double from poolview p4 where p4.aic_stamm=p.aic_stamm and p4.aic_eigenschaft="+g.iEigEurofaktor+")")+
								") from bew_Wert p"+g.join(sBewView,"v","p","bew_pool")+g.join("bew_stamm","s","v","bew_pool")+" where v.aic_bewegungstyp="+iBew0+
								" and p.aic_eigenschaft="+iAic+" and s.aic_eigenschaft="+iEig0+" and s.aic_stamm=e1.aic_stamm) e"+sAic;
						//g.progInfo("BewWaehrung: bSumme="+bSumme+", bPeriode="+bPeriode+", bGruppe="+bGruppe);
					}
					else
                                          if (iTyp==Formular.Stamm)
                                            sEinzeln=",null e"+sAic;
                                          else if(bGruppe)
                                            for(int iG=0;iG<VecGruppe.size();iG++)
                                              sEinzeln+=",(case when (select count(*) from bew_stamm where "+sVor+" and aic_stamm"+Static.SQL_in(NodToVec(VecGruppe.elementAt(iG)))+
                                                  ")>0 then (select spalte_Wert"+(!g.bCC?"":"/(select spalte_double from poolview p4 where p4.aic_stamm=p.aic_stamm and p4.aic_eigenschaft="+g.iEigEurofaktor+")")+
							" from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic+sg+iG;
                                          else if (bPeriode)
                                            for(int iPer=0;iPer<VecPerioden.size()-1;iPer++)
                                              sEinzeln+=",(case when gueltig>="+SQL.DateTimeToString((Date)VecPerioden.elementAt(iPer),g)+" and gueltig<"+SQL.DateTimeToString((Date)VecPerioden.elementAt(iPer+1),g)+
                                                  " then (select spalte_Wert"+(!g.bCC?"":"/(select spalte_double from poolview p4 where p4.aic_stamm=p.aic_stamm and p4.aic_eigenschaft="+g.iEigEurofaktor+")")+
							" from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic+"p"+iPer;
                                          else
                                            if (bEdit)
                                              sEinzeln=",(select spalte_Wert from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") e"+sAic+",(select aic_stamm from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") v"+sAic;
                                            else
                                              sEinzeln=sNull+"select spalte_Wert"+(!g.bCC?"":"/(select spalte_double from poolview p4 where p4.aic_stamm=p.aic_stamm and p4.aic_eigenschaft="+g.iEigEurofaktor+")")+
							" from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic+","+g.getWaehrung()+" v"+sAic;
                                  }*/
                                else if (sTyp.equals("BewMass"))
                                {
                                  //g.fixtestInfo(sTyp+"/"+bBewS+"/"+bPeriode+"/"+bSumme+"/"+bGruppe);
                                  boolean bKeineME=(Static.bKeineEinheit && !bTable || (lBits2&cstKeineME)>0) && (TabSp.getI("bits2") & cstOrigEinh)==0;
                                  // if (bKeineME)
                                	//   g.fixtestError("Keine Einheit bei "+sBez+"."+sAic);
                                  if (bBewS)
                                    {
                                      if(bPeriode)
                                        for(int iPer=0;iPer<PerAnz();iPer++)
                                          sEinzeln+=",(select "+sArt+"(p.GrundWert) from bew_Wert p"+g.join("bew_pool","v","p","bew_pool")+
                                              " where v.bew2_aic_bew_pool=p2.aic_bew_pool and p.aic_eigenschaft="+iAic+
                                              " and v.pro_aic_protokoll is null and "+PerVon(iPer,bAbNeujahr)+"<=gueltig and "+PerBis(iPer)+">gueltig) e"+sAic+"p"+iPer;
                                      else
                                        sEinzeln=",(select "+sArt+"(p.grundWert) from bew_Wert p"+g.join(sBewView,"v","p","bew_pool")+
                                            " where v.bew2_aic_bew_pool=e1.aic_bew_pool and p.aic_eigenschaft="+iAic+") e"+sAic;
                                    }
                                    else if (bSumme)
                                    {
                                          String sANR1=g.getANRS(iBew0,iEig0,iBegriff);//(iBits&cstANR)>0 && !bKeinANR;
                                          String s1=sANR1!=null ?"":g.join("bew_stamm","s","v","bew_pool");
                                          String s2=sANR1!=null ?"v."+sANR1:"s.aic_eigenschaft="+iEig0+" and s.aic_stamm";
                                          if(bGruppe)
                                            for(int iG=0;iG<VecGruppe.size();iG++)
                                            {
                                              Vector Vec = NodToVec(VecGruppe.elementAt(iG));
                                              boolean bDat=Sort.geti(Vec,0)>0;
                                              int iEig1=HA_Eig(VecGruppe.elementAt(iG));
                                              String sANR2=bDat && iEig1>0 ? g.getANRS(iBew0,iEig1,iBegriff):null;
                                              if (sANR2!=null && sANR1!=null)
                                                sEinzeln += ",(select " + sArt + "(p.GrundWert) from bew_Wert p" + g.join(sBewView, "v", "p", "bew_pool")+
                                                    " where v.aic_bewegungstyp=" + iBew0 + " and " + s2 + "=p2.aic_stamm"+" and v."+sANR2+Static.SQL_in2(Vec)+
                                                    " and p.aic_eigenschaft=" + iAic + ") e" + sAic + sg + iG;
                                              else
                                                sEinzeln += ",(select " + sArt + "(p.GrundWert) from bew_Wert p" + g.join(sBewView, "v", "p", "bew_pool") + s1 +
                                                  (sANR1!=null ? "" : g.join(sBewView, "s", "bew_pool")) + g.join("bew_stamm", "s2", sANR1!=null ? "v" : sBewView, "bew_pool") +
                                                  " where v.aic_bewegungstyp=" + iBew0 + " and p.aic_eigenschaft=" + iAic +
                                                  " and " + s2 + "=p2.aic_stamm"+(iEig1>0?" and s2.aic_eigenschaft=" + iEig1:"")+
                                                  (bDat ? " and s2.aic_stamm"+Static.SQL_in2(Vec):"") + ") e" + sAic + sg + iG;
                                            }
                                          else if(bPeriode)
                                                  for(int iPer=0;iPer<PerAnz();iPer++)
                                                          sEinzeln+=",(select "+sArt+"(p.GrundWert) from bew_Wert p"+g.join("bew_pool","v","p","bew_pool")+s1+
                                                                  " where v.aic_bewegungstyp="+iBew0+" and v.pro_aic_protokoll is null and "+PerVon(iPer,bAbNeujahr)+"<=gueltig and "+PerBis(iPer)+">gueltig"+
                                                                  " and p.aic_eigenschaft="+iAic+" and "+s2+"=p2.aic_stamm) e"+sAic+"p"+iPer;
                                          else
                                                  sEinzeln=",(select "+sArt+"(p.GrundWert) from bew_Wert p"+
                                                      g.join(sBewView,"v","p","bew_pool")+s1+" where v.aic_bewegungstyp="+iBew0+" and p.aic_eigenschaft="+iAic+" and "+s2+"=e1.aic_stamm) e"+sAic;
                                            //g.progInfo("BewMass: bSumme="+bSumme+", bPeriode="+bPeriode+", bGruppe="+bGruppe);
                                    }
                                    else
                                          if (iTyp==Formular.Stamm)
                                            sEinzeln=",null e"+sAic+",null v"+sAic;
                                          else if(bGruppe)
                                            for(int iG=0;iG<VecGruppe.size();iG++)
                                            {
                                              Vector Vec=NodToVec(VecGruppe.elementAt(iG));
                                              int iEig1=HA_Eig(VecGruppe.elementAt(iG));
                                              sEinzeln += ",(case when (select count(*) from bew_stamm where " + sVor + (iEig1>0?" and aic_eigenschaft=" + iEig1:"")+
                                                  (Sort.geti(Vec,0)>0 ?" and aic_stamm" + Static.SQL_in2(Vec):"") +
                                                  ")>0 then (select p.GrundWert from bew_Wert p where p." + sVor + " and p.aic_eigenschaft=" + iAic + ") else null end) e" + sAic + sg + iG;
                                            }
                                          else if (bPeriode)
                                            for(int iPer=0;iPer<PerAnz();iPer++)
                                              sEinzeln+=",(case when gueltig>="+PerVon(iPer,bAbNeujahr)+" and gueltig<"+PerBis(iPer)+
                                                " then (select p.GrundWert from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic+"p"+iPer;
                                          else
                                            sEinzeln=sNull+"select "+((iBits&cstDistinct)>0 ? "round(p.Grundwert,6)":"p.GrundWert")+" from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic+
                                              (bKeineME ? "": sNull+"select p.aic_stamm from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"v"+sAic);
                                }
                                else if (sTyp.equals("BewZahl"))
                                {
                                  if (bBewS)
                                  {
                                    if (bPeriode)
                                      for (int iPer = 0; iPer < PerAnz(); iPer++)
                                        sEinzeln += ",(select " + sArt + "(p.spalte_Wert) from bew_Wert p" + g.join("bew_pool", "v", "p", "bew_pool") +
                                            " where v.bew2_aic_bew_pool=p2.aic_bew_pool and p.aic_eigenschaft=" + iAic +
                                            " and v.pro_aic_protokoll is null and " + PerVon(iPer,bAbNeujahr) + "<=gueltig and " +
                                            PerBis(iPer) + ">gueltig) e" + sAic + "p" + iPer;
                                    else
                                      sEinzeln = ",(select " + sArt + "(p.spalte_Wert) from bew_Wert p" + g.join(sBewView, "v", "p", "bew_pool") +
                                          " where v.bew2_aic_bew_pool=e1.aic_bew_pool and p.aic_eigenschaft=" + iAic + ") e" + sAic;
                                  }
                                  else if (bSumme)
					{
                                          String sANR1=g.getANRS(iBew0,iEig0,iBegriff);//(iBits&cstANR)>0 && !bKeinANR;
                                          String s1=sANR1!=null ?"":g.join("bew_stamm","s","v","bew_pool");
                                          String s2=sANR1!=null ?" and v."+sANR1:" and s.aic_eigenschaft="+iEig0+" and s.aic_stamm";

                                          if(bGruppe)
                                            for (int iG = 0; iG < VecGruppe.size(); iG++)
                                            {
                                              Vector Vec = NodToVec(VecGruppe.elementAt(iG));
                                              boolean bDat = Sort.geti(Vec, 0) > 0;
                                              int iEig1 = HA_Eig(VecGruppe.elementAt(iG));
                                              String sANR2=bDat && iEig1>0 ? g.getANRS(iBew0,iEig1,iBegriff):null;
                                              if (sANR2!=null && sANR1!=null)
                                                sEinzeln += ",(select " + sArt + "(p.spalte_Wert) from bew_Wert p" + g.join(sBewView, "v", "p", "bew_pool")+
                                                    " where v.aic_bewegungstyp=" + iBew0 + s2 + "=p2.aic_stamm"+" and v."+sANR2+Static.SQL_in2(Vec)+
                                                    " and p.aic_eigenschaft=" + iAic + ") e" + sAic + sg + iG;
                                              else
                                                sEinzeln += ",(select " + sArt + "(p.spalte_Wert) from bew_Wert p" + g.join(sBewView, "v", "p", "bew_pool") + s1 +
                                                  (sANR1!=null ? "" : g.join(sBewView, "s", "bew_pool")) + g.join("bew_stamm", "s2", sANR1!=null ? "v" : sBewView, "bew_pool") +
                                                  " where v.aic_bewegungstyp=" + iBew0 +" and p.aic_eigenschaft=" + iAic +
                                                  s2 + "=p2.aic_stamm"+(iEig1>0?" and s2.aic_eigenschaft=" + iEig1:"")+
                                                  (bDat ? " and s2.aic_stamm" + Static.SQL_in2(Vec):"") + ") e" + sAic + sg + iG;
                                            }
                                          else if(bPeriode)
                                                  for(int iPer=0;iPer<PerAnz();iPer++)
                                                          sEinzeln+=",(select "+sArt+"(p.spalte_Wert) from bew_Wert p"+g.join("bew_pool","v","p","bew_pool")+s1+" where v.aic_bewegungstyp="+
                                                          iBew0+" and v.pro_aic_protokoll is null and "+PerVon(iPer,bAbNeujahr)+"<=gueltig and "+PerBis(iPer)+">gueltig "+
                                                                  " and p.aic_eigenschaft="+iAic+s2+"=p2.aic_stamm) e"+sAic+"p"+iPer;
                                          else
                                                  sEinzeln=",(select "+sArt+"(p.spalte_Wert) from bew_Wert p"+g.join(sBewView,"v","p","bew_pool")+s1+" where v.aic_bewegungstyp="+
                                                  iBew0+" and p.aic_eigenschaft="+iAic+s2+"=e1.aic_stamm) e"+sAic;
						//g.progInfo("BewZahl: bSumme="+bSumme+", bPeriode="+bPeriode);
					}
					else
                                          if (iTyp==Formular.Stamm)
                                            sEinzeln=",null e"+sAic;
                                          else if(bGruppe)
                                            for(int iG=0;iG<VecGruppe.size();iG++)
                                            {
                                              Vector Vec = NodToVec(VecGruppe.elementAt(iG));
                                              int iEig1 = HA_Eig(VecGruppe.elementAt(iG));
                                              sEinzeln += ",(case when (select count(*) from bew_stamm where " + sVor + (iEig1 > 0 ? " and aic_eigenschaft=" + iEig1 : "") +
                                                  (Sort.geti(Vec, 0) > 0 ? " and aic_stamm" + Static.SQL_in2(Vec) : "") +
                                                  ")>0 then (select spalte_Wert from bew_Wert p where p." + sVor + " and p.aic_eigenschaft=" + iAic + ") else null end) e" + sAic + sg + iG;
                                            }
                                          else if (bPeriode)
                                            for(int iPer=0;iPer<PerAnz();iPer++)
                                              sEinzeln+=",(case when gueltig>="+PerVon(iPer,bAbNeujahr)+" and gueltig<"+PerBis(iPer)+
                                                " then (select spalte_Wert from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+") else null end) e"+sAic+"p"+iPer;
                                          else
                                            sEinzeln=sNull+"select spalte_Wert from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+sN2+"e"+sAic;
                                }
                                else if (sTyp.equals("BewZahl2") || sTyp.equals("BewMass2") || sTyp.equals("BewWaehrung2") && bEdit)
                                {
                                  sEinzeln="";
                                  int iNr=TabSp.getI("Nummer");
                                  for (int i2=0;i2<TabSp.size();i2++)
                                  {
                                    String sSpalte=sAic+"_"+TabSp.getI(i2,"Rel");
                                    if (TabSp.getI(i2,"Nummer")==iNr && !VecSp.contains(sSpalte))
                                    {
                                      if (sTyp.equals("BewZahl2"))
                                        sEinzeln+=",(select Wert from bew_Spalte p where p."+sVor+" and p.aic_eigenschaft="+iAic+" and p.aic_stamm="+TabSp.getI(i2,"Rel")+") e"+sAic+"_"+TabSp.getI(i2,"Rel");
                                      else if (sTyp.equals("BewMass2"))
                                        sEinzeln += ",(select p.GrundWert from bew_Spalte p where p." + sVor + " and p.aic_eigenschaft=" + iAic + " and p.aic_stamm=" + TabSp.getI(i2, "Rel") + ") e" + sSpalte +
                                          ",(select p.sta_aic_stamm from bew_Spalte p where p." + sVor + " and p.aic_eigenschaft=" + iAic + " and p.aic_stamm=" + TabSp.getI(i2, "Rel") + ") v" + sSpalte;
                                      else if (sTyp.equals("BewWaehrung2"))
                                        sEinzeln=",(select Wert from bew_Spalte p where p."+sVor+" and p.aic_eigenschaft="+iAic+" and p.aic_stamm="+TabSp.getI(i2,"Rel")+") e"+sSpalte+
                                          ",(select sta_aic_stamm from bew_Spalte p where p."+sVor+" and p.aic_eigenschaft="+iAic+" and p.aic_stamm="+TabSp.getI(i2,"Rel")+") v"+sSpalte;
                                      VecSp.addElement(sSpalte);
                                    }
                                  }
                                }
                                else if (sTyp.equals("BewCount"))
                                {
                                  if (iTyp==Formular.Stamm)
                                    sEinzeln=",null e"+sAic;
                                  else
                                    sEinzeln=","+g.isnull()+"(select spalte_Wert from bew_Wert p where p."+sVor+" and p.aic_eigenschaft="+iAic+"),1) e"+sAic;
                                }
                                else  if (sTyp.equals("Formular"))
                                {
                                	sEinzeln=","+getSub(g,TabSp,"Frame")+" e"+sAic;
                                }
                                else  if (sTyp.equals("Abfrage"))
                                {
                                	sEinzeln=","+getSub(g,TabSp,"Abfrage")+" e"+sAic;
                                }
                                else  if (sTyp.equals("Modell"))
                                {
                                	sEinzeln=","+getSub(g,TabSp,"Modell")+" e"+sAic;
                                }
                                else  if (sTyp.equals("Favorit"))
                                {
                                	sEinzeln=(bEinfach ? ",aic_stamm e":",v"+sAic.substring(0,sAic.lastIndexOf("_"))+" e")+sAic;
                                }
                                else
                                	g.printError("Abfrage.ZusaetzlicheSpalten: Datentyp "+sTyp+" wird nicht unterstützt!",iBegriff);
				

				//g.progInfo(i+".: "+Obj+"/"+sTyp+"/"+sEinzeln);
				sGesamt+=sEinzeln;
			}// direkte Ebene
		}// Schleife über Spalten
              }// Schleife über Ebenen
              //g.progInfo(sGesmat);
              if (bEbenePlus)
                iEbenen++;
              return(sGesamt);
	}
    
    public static int getSub(Global g,Tabellenspeicher Tab,String sArt)
    {
    	for (int i=1;i<4;i++)
    	{
    		int iAic=Tab.getI("Sub"+i);
    		if (iAic>0)
    		{
    			int iPos=g.TabBegriffe.getPos("Aic",iAic);
    			if (iPos>=0)
    			{
    				int iG=g.TabBegriffe.getI(iPos,"Gruppe");
    				String sBG=g.TabBegriffgruppen.getKennung(iG);
//    				g.fixtestError("getSub"+i+":"+sBG);
    				if (sBG.equals(sArt))
    					return iAic;
    			}
    		}
    		
    	}
    	return 0;
    }
    
    public static String getSubBG(Global g,Tabellenspeicher Tab,int iNr)
    {
    	int iAic=Tab.getI("Sub"+iNr);
    	if (iAic>0)
		{
			int iPos=g.TabBegriffe.getPos("Aic",iAic);
			if (iPos>=0)
			{
				int iG=g.TabBegriffe.getI(iPos,"Gruppe");
				String sBG=g.TabBegriffgruppen.getKennung(iG);
				return sBG;
			}
		}
    	return null;
    }

	/*public static void ClearVecEigPer(Global g)
	{
		if (VecEigPer != null)
		{
			for (int i=0;i<VecEigPer.size();i++)
				if (g.TabEigenschaften.posInhalt("Aic",VecEigPer.elementAt(i)))
					g.TabEigenschaften.setInhalt("Periode",null);
			VecEigPer.removeAllElements();
		}
	}*/

	private static String EigElement(Object Obj)
	{
		int iEig=Tabellenspeicher.geti(Obj);//Obj instanceof Integer ? ((Integer)Obj).intValue() : ((BewEig)Obj).Eig();
                BewEig BE=Obj instanceof BewEig ? (BewEig)Obj:null;
                return iEig<0 ? "m"+(-iEig):""+(BE!=null && BE.Bew()>0 ? BE.Bew()+"b":"")+iEig+(BE!=null && BE.Rolle()>0 ? "r"+BE.Rolle():"")+(BE!=null && BE.Art()!=null ? BE.Art():"");
		//return iEig<0 ? "m"+(-iEig):""+(Obj instanceof BewEig?((BewEig)Obj).Bew()+"b":"")+iEig;
	}

        /*public Vector getVBed()
        {
          JCOutlinerNode Nod = (JCOutlinerNode)NodVBed.getChildren().elementAt(0);
          return (Vector)Nod.getUserData();
        }*/

        public Vector getBed() // für Import
        {
          JCOutlinerNode Nod = (JCOutlinerNode)NodBed.getChildren().elementAt(0);
          return (Vector)Nod.getUserData();
        }

        private void setDiff(String sVergl)
        {
          g.progInfo("setDiff mit "+sVergl);
          if (VecDateJoker==null)
            VecDateJoker=new Vector<String>();
          if (!VecDateJoker.contains(sVergl))
            VecDateJoker.addElement(sVergl);
          if (sVergl.startsWith("*Zahl-"))
            iJokerZahl=Sort.geti(sVergl.substring(6,sVergl.length()-1));
          //g.progInfo("VecDateJoker="+Sort.gets2(VecDateJoker));
        }
        
        private String useDiff(String sVergl)
        {
          boolean bInt=sVergl.startsWith("#");
          if (bInt)
        	  sVergl=sVergl.substring(1);
          if (sVergl.startsWith("*Zahl-"))
            return ""+iJokerZahl;
          g.progInfo("useDiff:"+sVergl+"/"+bInt);
          DateWOD DW=null;
          if (sVergl.startsWith("von"))
            DW=new DateWOD(getVon());
          else if (sVergl.startsWith("bis"))
            DW=new DateWOD(getBis());
          else if (sVergl.startsWith("now()"))
            DW=new DateWOD();
          else if (sVergl.startsWith("today()"))
          {
            DW=new DateWOD();
            DW.setTimeZero();
          }
          else if (sVergl.startsWith("*newyear*"))
            DW=DateWOD.getNewYear(getVon());
          else if (sVergl.startsWith("*Stichtag*"))
            DW=new DateWOD(g.dtStichtag);
          int iPos=sVergl.startsWith("now()")?5:sVergl.startsWith("today()")?7:sVergl.startsWith("*newyear")?9:sVergl.startsWith("*Stichtag")?10:3;
          boolean bPlus=sVergl.charAt(iPos)=='+';
          int iAnz=Integer.parseInt(sVergl.substring(iPos+1,sVergl.length()-1));
          g.testInfo(DW+"/"+iAnz+"/"+bPlus);
          for (int i2=0;i2<iAnz;i2++)
          {
            if (sVergl.endsWith("d"))
              if (bPlus) DW.tomorrow(); else DW.yesterday();
            else if (sVergl.endsWith("w"))
              if (bPlus) DW.nextWeek(); else DW.prevWeek();
            else if (sVergl.endsWith("m"))
              if (bPlus) DW.nextMonth(); else DW.prevMonth();
            else if (sVergl.endsWith("y"))
              if (bPlus) DW.nextYear(); else DW.prevYear();
          }

          String s=bInt ? ""+Static.DateToInt(DW.toTimestamp()):g.DateTimeToString(DW.toTimestamp());
          g.fixtestInfo(DW+"/"+iAnz+"/"+bPlus+"->"+s);
          return s;
        }
        
    private String getSVon(boolean bLDATE,boolean bOJ)
    {
    	return (lBits2&cstLokZR)>0 || !bOJ ? bLDATE ? "*vonInt*":"*von*":"(select "+g.fDate("von", "MMDD")+" from zr)";//iVB>0 ? "von"+iVB+"()":"(select "+(bOJ ? g.fDate("von", "MMDD"):bLDATE ? "vonInt":"von")+" from zr)";
    }
    
    private String getSBis(boolean bLDATE,boolean bOJ)
    {
    	return (lBits2&cstLokZR)>0 || !bOJ ? bLDATE ? "*bisInt*":"*bis*":"(select "+g.fDate("bis", "MMDD")+" from zr)";//:iVB>0 ? "bis"+iVB+"()":"(select "+(bOJ ? g.fDate("bis", "MMDD"):bLDATE ? "bisInt":"bis")+" from zr)";
    }

	@SuppressWarnings("unchecked")
	public String Check(Vector VecSpalten,Vector Vec,String s,boolean bVB)
	{
		//System.out.println("    ------ Check:"+VecSpalten+"/"+Vec+"/"+s);
		if (Vec == null || Vec.size()==0)
			return s;
		//if (s.equals("")) TabJoker=null;
                //bVB=s.equals(" ");
                //if (s.equals("") && !bVB)
                //  g.fixInfo("Bedingung von "+sDefBez);
		s = (s.equals("")?" where":s+" and")+ (Vec.size()>1 ? "(":" ");
		//s = s+" and"+ (Vec.size()>1 ? "(":" ");
		//System.out.println("s="+s);
                for (int i=0;i<Vec.size();i++)
		{
			//System.out.println(i+".:"+Vec.elementAt(i).getClass().getName());
			JCOutlinerNode Nod = (JCOutlinerNode)Vec.elementAt(i);
			Vector Vec2 = (Vector)Nod.getUserData();
			//g.fixInfo("getUserData="+Vec2);
			String sDatentyp = Vec2.elementAt(0).toString();
                        //System.out.println("Check: Datentyp="+sDatentyp+"/"+Vec);
			Vector VecEig=(Vector)Vec2.elementAt(1);
                        String sElement;
                        if (bVB)
                        {
                          //g.progInfo("Vorbedingung mit " + VecEig);
                          sElement=sDatentyp.equals("SysAic") ? iBew>0 ? "p2.aic_bew_pool":"aic_stamm" : g.getVB(iBew,Sort.geti(VecEig,0));                         
                        }
                        else
                          sElement=VecToKennung(VecEig);
			contains(VecSpalten,VecEig,0);
                        String sArt=(String)Vec2.elementAt(2);
                        boolean bLDATE=bVB && (sElement.startsWith("LDATE") || sElement.equals("VB"));
                        if ((bLDATE || bVB && sElement.startsWith("BOOL")) && sArt.startsWith("is "))
                          sArt=sArt.equals("is null") ? "=0":">0";
                        boolean b=sArt.equals("<>");
                        boolean bLike=sArt.contains("like");
                        String s2=null;
                        Object Obj=Vec2.elementAt(3);
                        String sVergl=Obj==null || !(Obj instanceof String) ? null:(String)Obj;
                        //g.fixtestError("Check "+sDefBez+":"+VecEig+"/"+sArt+"/"+sVergl);
                        if (sVergl != null && (sVergl.equals("*Checkbox*") || sVergl.equals("*Radiobutton*"))) // && TabJoker==null)
                        {
                          if (TabJoker==null)
                            TabJoker=new Tabellenspeicher(g,new String[] {"Kennung","Eigenschaft","Cbx","Komponente","Stamm","Abfrage","Bez"});
                          int iEig=Sort.geti(VecEig.elementAt(VecEig.size()-1));
                          if (!TabJoker.posInhalt("Eigenschaft", iEig))
                          {
                            TabJoker.newLine();
                            boolean bCbx=sVergl.equals("*Checkbox*");
                            TabJoker.setInhalt("Kennung", "v"+sElement+"  "+(bCbx ? "\\*Checkbox\\*":"\\*Radiobutton\\*"));
                            TabJoker.setInhalt("Eigenschaft", iEig);
                            TabJoker.setInhalt("Cbx", new Boolean(bCbx));
                          }
                          //TabJoker.showGrid();
                        }
                        if (sArt.equals("=") && (sVergl.equals("*Checkbox*") || sVergl.equals("*Radiobutton*") || (iBits&cstMengen)>0 && sVergl!= null && sVergl.equals("*Qry*")))
                          sArt="";
                        int iBBitsGes=Vec2.size()>4 ? Tabellenspeicher.geti(Vec2.elementAt(4)):0;
                        int iBBits=iBBitsGes&VBART;
                        if (bVB && sElement!=null && sElement.equals("VB"))
                      	  sElement=iBBits==VON ? "LDateVon":"LDateBis";
                        boolean bZeit=(iBBitsGes&ZEIT)>0;
                        boolean bOJ=(iBBitsGes&OJ)>0;
                        boolean bKen=(iBBitsGes&KEN)>0;
//                        if (iBBits>0)
//                        	g.fixtestError(sDefBez+": Zeit="+bZeit+", OJ="+bOJ);
                        //g.progInfo("iBBits="+iBBits);
                        //g.fixtestInfo("Vergleich:"+sVergl+"/"+sArt+"/"+bLike+", Zeit="+bZeit);
//                        String sVon=null;
//                        String sBis=null;
//                        if (bLDATE)
//                		{
//                        	sVon="(select vonInt from zr)";
//                        	sBis="(select bisInt from zr)";
//                		}
//                        else if (sVergl !=null)// && sVergl.contains("von"))
//                        {
//                        	sVon="(select von from zr)";
//                        	sBis="(select bis from zr)";
//                		}
                        if (sVergl !=null && !bLike && (!g.ASA() || sVergl.startsWith("now()") || sVergl.startsWith("today()") || sVergl.startsWith("von") || sVergl.startsWith("bis")
                                              || sVergl.startsWith("*newyear") || sVergl.startsWith("*Stichtag") || sVergl.startsWith("*Zahl-")))
                        {
                          if (sVergl.equals("von"))
                            sVergl=getSVon(bLDATE,bOJ);//g.ASA() ? "von":sVon;
                          else if (sVergl.equals("bis"))
                            sVergl=getSBis(bLDATE,bOJ);//g.ASA() ? "bis":sBis;
                          else if (sVergl.equals("von and bis"))
                            sVergl=g.ASA() ? "von and bis":getSVon(bLDATE,bOJ)+" and "+getSBis(bLDATE,bOJ);
                          else if (sVergl.equals("now()"))
                            sVergl=g.now();//g.Oracle() ? "sysdate":g.MS()?"GETDATE()":"now()";
                          else if (sVergl.equals("today()"))
                            sVergl=bLDATE ? "*to3day*":g.today();//g.Oracle() ? "trunc(sysdate)" : g.MS() ? "CAST(CONVERT(char(8), GETDATE(), 112) AS datetime)" : g.MySQL() ? "current_date" : "today()";
                          else if (sVergl.equals("*Stichtag*"))
                            sVergl=bLDATE ? "*Stich3tag*":"*Stich2tag*";//g.DateTimeToString(g.dtStichtag);
                          else if (sVergl.equals("*newyear*"))
                            sVergl=bLDATE ? "*new3year*":"*new2year*";//g.DateTimeToString(DateWOD.getNewYear(g.getVon()).toTimestamp());
                          else if (sVergl.startsWith("von") || sVergl.startsWith("bis") || sVergl.startsWith("now()") || sVergl.startsWith("today()")
                                   || sVergl.startsWith("*newyear") || sVergl.startsWith("*Stichtag") || sVergl.startsWith("*Zahl-"))
                          {
                        	  sVergl=(bLDATE ? "#":"")+sVergl;
                        	  setDiff(sVergl);
                          }
                          else if (!g.MySQL() && (sVergl.startsWith("'19") || sVergl.startsWith("'20")) && sVergl.length()>9)
                            sVergl=g.MS()?sVergl.substring(0,5)+'-'+sVergl.substring(sVergl.lastIndexOf('/')+1,sVergl.lastIndexOf("'"))+'-'+sVergl.substring(sVergl.indexOf('/')+1,sVergl.lastIndexOf('/'))+"'":"to_timestamp("+sVergl+",'YYYY/MM/DD')";
                        }
                        if (sDatentyp.endsWith("Boolean") && sArt.equals("=") && sVergl.startsWith("0"))
                        {
                          /*if (sArt.equals("=") && sVergl.startsWith("1"))
                            {
                              //g.testInfo(sDatentyp+": ändere von "+sArt+" "+sVergl+" ->is not null");
                              sArt="is not null";
                              sVergl=null;
                            }*/
                            //if (sArt.equals("=") && sVergl.startsWith("0"))
                            //  {
                                //g.testInfo(sDatentyp+": ändere von "+sArt+" "+sVergl+" ->is null");
                                sArt="is null";
                                sVergl=null;
                            //  }

                        }
                        if (sDatentyp.endsWith("Hierarchie") && (sArt.equals("=") || b) && sVergl.indexOf("JokerVec")==-1 && sVergl.indexOf("UseVec")==-1)
                        {
                          //Vector Vec3=new Vector();
                          String s3=(bVB ?"":"v")+sElement;
                          String s4=""+VecEig.elementAt(VecEig.size()-1);
                          s2=" "+sVergl+(b/* && g.Oracle()*/?" not":"")+" in("+(iBew==0 && sVergl.startsWith("*meine")?"aic_stamm,":"")+s3;
                          int iPos=s3.lastIndexOf("_"+s4);
                          while (iPos>0)
                          {
                            s3=s3.substring(0,iPos);
                            iPos=s3.lastIndexOf("_"+s4);
                            s2+=","+s3;
                          }
                          s2+=')';//+(b && g.ASA()?" is unknown":"");
                        }
                        else if (sArt.equals("soundex"))
                        {
                          s2="soundex(e"+sElement+")=soundex("+sVergl+")";
                        }
                        else
                        {
                          String sSpalte=(sVergl != null && (sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular")) ? "p":sDatentyp.equals("GPS") ? "b":
                        		  bVB ?"":sDatentyp.equals("Gruppe") || sDatentyp.equals("Hierarchie") || sDatentyp.equals("Benutzer") || sDatentyp.equals("LoeschBenutzer") || sDatentyp.endsWith("User")
                           || sDatentyp.equals("Anlage") ||sDatentyp.equals("Firma") || sDatentyp.equals("Mandant") || sDatentyp.endsWith("Bool3")
                           || sDatentyp.equals("BewModul") || sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular")
                           || sDatentyp.equals("Formular") || sDatentyp.equals("Land") || sDatentyp.equals("Aufgabe") || sDatentyp.equals("Status")
                           || sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("BewStamm") || sDatentyp.equals("BewHierarchie") ?
                                bKen ? "k":"v":sDatentyp.equals("BewVon_Bis") ? iBBits==VON ? "v":iBBits==BIS ? "b":"d":"e")+sElement;
                          s2=(b?g.isnull():"")+(bZeit ? g.fDate(sSpalte,"HH:mm"):bOJ ? sSpalte.equals("Stichtag") ? getSVon(bLDATE,bOJ):g.fDate(sSpalte, "MMDD"):sSpalte)+(b?",-1)":" ")+
                        		  ((sArt+sVergl).equals("=0") ? " is null": sArt+(sVergl==null ? "":" "+sVergl));
                        }
                        //System.out.println("Check: Datentyp="+sDatentyp+"/"+VecSpalten+"/ ["+s2+"]");
                        s = (i==0 ? s : s+" or ")+s2;
			Vec2 = Nod.getChildren();
			if (Vec2 != null && Vec2.size()>0)
				s = Check(VecSpalten,Vec2,s,bVB);
		}
		return Vec.size()>1 ? s+")":s;
	}
	
	public Vector<Integer> getVecBewPool(String sANR,int iStamm)
	{
		long lClock=Static.get_ms();
		if ((lBits2&cstSQL2)==0)
			return null;
		Vector<Integer> VecBewPool=null;
		if (iBew>0 && sANR != null && (iBits&cstKeinZeitraum)==0 && iStamm>0)
	    {
	    	VecBewPool=SQL.getVector("select aic_bew_pool from bewview where aic_bewegungstyp="+iBew+" and "+sANR+"="+iStamm,g);
	    	if (VecBewPool.size()==0)
	    		VecBewPool.addElement(0);
	    	g.fixInfoT("vorab Bew ermittelt: "+(Static.get_ms()-lClock)+" ms für "+VecBewPool.size()+"="+VecBewPool);    
	    	if (VecBewPool.size()>1000)
	    	{
	    		g.fixInfoT(" verwende nicht VecBewPool, da zu viele:"+VecBewPool.size());
	    		VecBewPool=null;
	    	}
	    }
		return VecBewPool;
	}

	public static Vector<Integer> FuelleVecBezeichnung(Global g,int iStammtyp)
	{
		Vector<Integer> Vec = new Vector<Integer>();
		SQL Qry = new SQL(g);
		Qry.open("select z.eig_aic_eigenschaft aic,z.reihenfolge from stammtyp_zuordnung join eigenschaft e on e.aic_eigenschaft=stammtyp_zuordnung.aic_eigenschaft,eigenschaft_zuordnung z where stammtyp_zuordnung.aic_stammtyp="+iStammtyp+
				" and e.aic_eigenschaft=z.aic_eigenschaft order by z.reihenfolge");
		int iPos = 0;
		while (!Qry.eof())
		{
			for (int i=iPos+1;i<Qry.getI("reihenfolge");i++)
			{
				Vec.addElement(null);
				//g.printInfo(">"+i+": leer");
			}
			Vec.addElement(new Integer(Qry.getI("aic")));
			iPos =Qry.getI("reihenfolge");
			//g.printInfo(">"+iPos+":"+Qry.getI("aic"));
			Qry.moveNext();
		}
		Qry.free();
		return Vec;
	}

        public static double Round(double dReg,double d2,boolean b)
        {
          if (b)
            if (Math.abs(dReg)<1000000000000000.0)
              if (dReg<0)
                return -Math.round(-dReg*d2+0.000001)/d2;
              else
                return Math.round(dReg*d2+0.000001)/d2;
            else
              return Math.rint(dReg*d2)/d2;
          else
            return dReg;
        }
        
        public Vector<Integer> FilterCheck(Vector<Integer> VecStamm)
        {
        	if (iAbfF>0)
    		{
//    			g.fixtestError("Filter mit "+g.getBegBez(g.AbfToBeg(iAbfF))+" nötig");
    			ShowAbfrage Abf=new ShowAbfrage(g,iAbfF,Abfrage.cstAbfrage/*cstHS_Filter*/);
                Abf.iBits=Abf.iBits|Abfrage.cstKeinStamm;
                Tabellenspeicher Tab=Abf.getDaten(Abf.iStt, 0, null,null);
//                Tab.showGrid("Filter "+g.getBegBez(g.AbfToBeg(iAbfF)));
                VecStamm=Tab.getVecSpalteI("v"+VecToKennung((Vector)Abf.TabSp.getInhalt("Vec",0)));//Abf.TabSpalten.getS(0,"Kennung2"));
                g.fixtestError("Filter mit "+g.getBegBez(g.AbfToBeg(iAbfF))+": "+VecStamm);
                iBits=iBits|Abfrage.cstMengen;
                if (VecStamm.size()>0 && (iBits&cstKeinStamm)>0)
                	iBits=iBits-Abfrage.cstKeinStamm;
    		}
        	return VecStamm;
        }

  public void pruefeDoppelte(Tabellenspeicher TabAbfrage)
  {
    boolean bBew=iBew>0;
    String sSp=bBew ? "aic_bew_pool":"aic_stamm";
    int iAnz=TabAbfrage==null ? -1:TabAbfrage.size();
    if (iAnz>1 && TabAbfrage.getI(0,sSp)>0/*!is(iBits,cstDistinct)*/ && iAnz>TabAbfrage.count_distinct(sSp))
    {
      g.fixtestError("Bewegungssätze doppelte bei: "+sDefBez+"("+iBegriff+")");
      Vector<Integer> Vec=new Vector<Integer>();
      for (int i=0;i<TabAbfrage.size();)
      {
        int iAic=TabAbfrage.getI(i, sSp);
        if (Vec.contains(iAic))
          TabAbfrage.clearInhalt(i);
        else
        {
          Vec.addElement(iAic);
          i++;
        }
      }
      g.fixtestError(" reduziere von "+iAnz+" auf "+TabAbfrage.size());
    } 
  };

}

