/*     */ package jclass.cell.renderers;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import jclass.cell.CellInfo;
/*     */ import jclass.cell.CellRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EllipsisCellRenderer
/*     */   implements CellRenderer
/*     */ {
/*     */   public void draw(Graphics paramGraphics, CellInfo paramCellInfo, Object paramObject, boolean paramBoolean)
/*     */   {
/*  84 */     if (paramObject == null)
/*  85 */       return;
/*  86 */     String str1 = paramObject.toString();
/*  87 */     if ((str1 == null) || (str1.length() == 0)) {
/*  88 */       return;
/*     */     }
/*  90 */     FontMetrics localFontMetrics = paramCellInfo.getFontMetrics();
/*  91 */     Rectangle localRectangle = paramCellInfo.getDrawingArea();
/*  92 */     paramGraphics.setFont(paramCellInfo.getFont());
/*  93 */     paramGraphics.setColor(paramBoolean ? paramCellInfo.getSelectedForeground() : 
/*  94 */       paramCellInfo.getForeground());
/*     */     
/*  96 */     int i = localFontMetrics.stringWidth(str1);
/*     */     
/*  98 */     int j = 0;int k = localFontMetrics.getHeight() - 4;
/*     */     
/*     */ 
/* 101 */     int m = paramCellInfo.getHorizontalAlignment();
/* 102 */     if (m == 2) {
/* 103 */       j += localRectangle.width - localFontMetrics.stringWidth(str1);
/* 104 */     } else if (m == 1) {
/* 105 */       j += (localRectangle.width - localFontMetrics.stringWidth(str1)) / 2;
/*     */     }
/*     */     
/* 108 */     m = paramCellInfo.getVerticalAlignment();
/* 109 */     if (m == 1) {
/* 110 */       k += (localRectangle.height - localFontMetrics.getHeight()) / 2;
/* 111 */     } else if (m == 2) {
/* 112 */       k += localRectangle.height - localFontMetrics.getHeight();
/*     */     }
/* 114 */     if (i < localRectangle.width) {
/* 115 */       paramGraphics.drawString(str1, j, k);
/*     */     } else {
/* 117 */       String str2 = " ..";
/* 118 */       int n = str1.length();
/* 119 */       while ((localFontMetrics.stringWidth(str1.substring(0, n) + str2) > localRectangle.width) && (n > 0))
/* 120 */         n--;
/* 121 */       paramGraphics.drawString(str1.substring(0, n) + str2, j, k);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/* 132 */     if (paramObject == null) {
/* 133 */       return null;
/*     */     }
/* 135 */     String str = paramObject.toString();
/*     */     
/* 137 */     if ((str == null) || (str.length() == 0)) {
/* 138 */       return new Dimension(0, 0);
/*     */     }
/* 140 */     FontMetrics localFontMetrics = paramCellInfo.getFontMetrics();
/* 141 */     paramCellInfo.getDrawingArea();
/*     */     
/* 143 */     return new Dimension(localFontMetrics.stringWidth(str), localFontMetrics.getHeight());
/*     */   }
/*     */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\cell\renderers\EllipsisCellRenderer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */