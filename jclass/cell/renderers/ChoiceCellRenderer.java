/*     */ package jclass.cell.renderers;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import jclass.cell.CellInfo;
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
/*     */ public class ChoiceCellRenderer
/*     */   extends StringCellRenderer
/*     */ {
/*     */   private String[] items;
/*     */   protected int[] values;
/*     */   
/*     */   public ChoiceCellRenderer()
/*     */   {
/*  84 */     this.items = null;
/*  85 */     this.values = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChoiceCellRenderer(String[] paramArrayOfString)
/*     */   {
/*  93 */     this(paramArrayOfString, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChoiceCellRenderer(String[] paramArrayOfString, int[] paramArrayOfInt)
/*     */   {
/* 103 */     this.items = paramArrayOfString;
/* 104 */     this.values = paramArrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void draw(Graphics paramGraphics, CellInfo paramCellInfo, Object paramObject, boolean paramBoolean)
/*     */   {
/* 111 */     super.draw(paramGraphics, paramCellInfo, mapDataToString(paramObject), paramBoolean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/* 118 */     return super.getPreferredSize(paramCellInfo, mapDataToString(paramObject));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String mapDataToString(Object paramObject)
/*     */   {
/* 126 */     if ((this.items != null) && ((paramObject instanceof Number))) {
/* 127 */       int i = getIndexFromValue(((Number)paramObject).intValue());
/* 128 */       if ((i >= 0) && (i < this.items.length))
/* 129 */         return this.items[i];
/*     */     }
/* 131 */     return paramObject.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private int getIndexFromValue(int paramInt)
/*     */   {
/* 138 */     if (this.values == null)
/* 139 */       return paramInt;
/* 140 */     for (int i = 0; i < this.values.length; i++)
/* 141 */       if (this.values[i] == paramInt)
/* 142 */         return i;
/* 143 */     return -1;
/*     */   }
/*     */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\cell\renderers\ChoiceCellRenderer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */