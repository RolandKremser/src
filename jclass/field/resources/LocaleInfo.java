/*    */ package jclass.field.resources;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ListResourceBundle;
/*    */ 
/*    */ public class LocaleInfo extends ListResourceBundle implements Serializable
/*    */ {
/*    */   public Object[][] getContents()
/*    */   {
/* 10 */     return contents;
/*    */   }
/*    */   
/* 13 */   static final Object[][] contents = {
/* 14 */     {
/* 15 */     "LocalCurrency", "$" }, 
/*    */     
/* 17 */     {
/* 18 */     "InternationalCurrency", "USD" }, 
/*    */     
/* 20 */     {
/* 21 */     "CurrencyPattern", "$#,##0.00;($#,##0.00)" }, 
/*    */     
/* 23 */     {
/* 24 */     "DecimalPattern", "#,##0.###;-#,##0.###" }, 
/*    */     
/* 26 */     {
/* 27 */     "FullTime", "h:mm:ss o'clock a z" }, 
/*    */     
/* 29 */     {
/* 30 */     "LongTime", "h:mm:ss a z" }, 
/*    */     
/* 32 */     {
/* 33 */     "MediumTime", "h:mm:ss a" }, 
/*    */     
/* 35 */     {
/* 36 */     "ShortTime", "h:mm a" }, 
/*    */     
/* 38 */     {
/* 39 */     "FullDate", "EEEE, MMMM d, yyyy" }, 
/*    */     
/* 41 */     {
/* 42 */     "LongDate", "MMMM d, yyyy" }, 
/*    */     
/* 44 */     {
/* 45 */     "MediumDate", "dd-MMM-yy" }, 
/*    */     
/* 47 */     {
/* 48 */     "ShortDate", "M/d/yy" }, 
/*    */     
/* 50 */     {
/* 51 */     "DateTime", "{1} {0}" } };
/*    */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\field\resources\LocaleInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */