/*     */ package jclass.cell.renderers;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import jclass.cell.CellInfo;
/*     */ import jclass.cell.CellRenderer;
/*     */ import jclass.cell.Utilities;
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
/*     */ public class StringCellRenderer
/*     */   implements CellRenderer
/*     */ {
/*  71 */   protected Utilities utilities = new Utilities();
/*  72 */   protected boolean drawClipArrowOutline = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics paramGraphics, CellInfo paramCellInfo, Object paramObject, boolean paramBoolean)
/*     */   {
/*  83 */     if (paramObject == null) {
/*  84 */       return;
/*     */     }
/*  86 */     String str1 = paramObject.toString();
			  String str2;
/*  87 */     if ((str1 == null) || (str1.length() == 0)) {
/*  88 */       return;
/*     */     }
/*  90 */     FontMetrics localFontMetrics = paramCellInfo.getFontMetrics();
/*  91 */     Rectangle localRectangle = paramCellInfo.getDrawingArea();
/*  92 */     paramGraphics.setFont(paramCellInfo.getFont());
/*  93 */     paramGraphics.setColor(paramBoolean ? paramCellInfo.getSelectedForeground() : paramCellInfo.getForeground());
/*     */     int i;
/*  95 */     int j; if (str1.indexOf('\n') != -1) {
/*  96 */       i = 0;int k = localFontMetrics.getHeight();
/*  97 */       int m = 0;
/*     */       
/*  99 */       int n = getHeight(localFontMetrics, str1);
/* 100 */       int i1 = getAlignedY(k - 4, localRectangle.height, n, paramCellInfo.getVerticalAlignment());
/*     */       
/* 102 */       int i2 = paramCellInfo.getHorizontalAlignment();
/* 103 */       for (; (j = str1.indexOf('\n', i)) != -1; i1 += k) {
/* 104 */         str2 = str1.substring(i, j);
/* 105 */         m = getAlignedX(0, localRectangle.width, localFontMetrics.stringWidth(str2), i2);
/* 106 */         paramGraphics.drawString(str2, m, i1);i = j + 1;
/*     */       }
/*     */       
/*     */ 
/* 108 */       str2 = str1.substring(i, str1.length());
/* 109 */       m = getAlignedX(0, localRectangle.width, localFontMetrics.stringWidth(str2), i2);
/* 110 */       paramGraphics.drawString(str2, m, i1);
/*     */     } else {
/* 112 */       i = getAlignedX(0, localRectangle.width, localFontMetrics.stringWidth(str1), paramCellInfo.getHorizontalAlignment());
/* 113 */       j = getAlignedY(localFontMetrics.getHeight() - 4, localRectangle.height, localFontMetrics.getHeight(), paramCellInfo.getVerticalAlignment());
/* 114 */       paramGraphics.drawString(str1, i, j);
/*     */     }
/*     */     
/* 117 */     Dimension localDimension = getPreferredSize(paramCellInfo, str1);
/* 118 */     if (((localDimension.width > localRectangle.width) || (localDimension.height > localRectangle.height)) && 
/* 119 */       (paramCellInfo.getClipHints() != 0)) {
/* 120 */       Utilities.drawClipArrows(paramGraphics, paramCellInfo, localDimension, 
/* 121 */         4, 
/* 122 */         this.drawClipArrowOutline);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAlignedX(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 131 */     if (paramInt4 == 1)
/* 132 */       return paramInt1 + (paramInt2 - paramInt3) / 2;
/* 133 */     if (paramInt4 == 2)
/* 134 */       return paramInt1 + paramInt2 - paramInt3;
/* 135 */     return paramInt1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAlignedY(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 143 */     if (paramInt4 == 1)
/* 144 */       return paramInt1 + (paramInt2 - paramInt3) / 2;
/* 145 */     if (paramInt4 == 2)
/* 146 */       return paramInt1 + paramInt2 - paramInt3;
/* 147 */     return paramInt1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getHeight(FontMetrics paramFontMetrics, String paramString)
/*     */   {
/* 154 */     if ((paramString == null) || (paramString.length() == 0))
/* 155 */       return 0;
/* 156 */     int i = 1;
/* 157 */     for (int j = 0; j < paramString.length(); j++)
/* 158 */       if (paramString.charAt(j) == '\n') i++;
/* 159 */     return paramFontMetrics.getHeight() * i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getWidth(FontMetrics paramFontMetrics, String paramString)
/*     */   {
/* 166 */     if ((paramString == null) || (paramString.length() == 0)) {
/* 167 */       return 0;
/*     */     }
/*     */     
/* 170 */     if (paramString.indexOf('\n') != -1) {
/* 171 */       int i = 0;int k = 0;
/* 172 */       int j; for (; (j = paramString.indexOf('\n', i)) != -1; i = j + 1)
/* 173 */         k = Math.max(k, paramFontMetrics.stringWidth(paramString.substring(i, j)));
/* 174 */       return Math.max(k, paramFontMetrics.stringWidth(paramString.substring(i, paramString.length())));
/*     */     }
/*     */     
/* 177 */     return paramFontMetrics.stringWidth(paramString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/* 187 */     if (paramObject == null) {
/* 188 */       return new Dimension(0, 0);
/*     */     }
/* 190 */     String str = paramObject.toString();
/* 191 */     if ((str == null) || (str.length() == 0)) {
/* 192 */       return new Dimension(0, 0);
/*     */     }
/* 194 */     FontMetrics localFontMetrics = paramCellInfo.getFontMetrics();
/*     */     
/* 196 */     if (str.indexOf('\n') != -1) {
/* 197 */       return new Dimension(getWidth(localFontMetrics, str), getHeight(localFontMetrics, str));
/*     */     }
/* 199 */     return new Dimension(localFontMetrics.stringWidth(str), localFontMetrics.getHeight());
/*     */   }
/*     */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\cell\renderers\StringCellRenderer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */