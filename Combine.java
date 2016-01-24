public class Combine {
   public static void main (String [] argv ) { 
       assert validateChar('2')    : "2 should be a valid char"; 
       assert validateChar('4')    : "4 should be a valid char"; 
       assert validateChar('_')    : "_ should be a valid char"; 
       assert !validateChar(' ')    : "space should not be a valid char"; 
       assert !validateChar('3')    : "3 should not be a valid char"; 
       assert !validateRow("2222") : "2222 should not be valid"; 
       assert !validateRow("__")   : "__ should not be valid"; 
       assert !validateRow("aaa")  : "aaa should not be valid"; 
       assert !validateRow("333")  : "333 should not be valid"; 
       assert validateRow("22_")   : "22_ should be valid"; 
       assert validateRow("_2_")   : "_2_ should be valid"; 
       assert validateRow("242")   : "242 should be valid"; 
       assert "4__".equals(moveLeft("__4"))    : "__4 doesn't change to 4__"; 
       assert "4__".equals(moveLeft("_4_"))    : "_4_ doesn't change to 4__"; 
       assert "42_".equals(moveLeft("4_2"))    : "4_2 doesn't change to 42_"; 
       assert "4__".equals(combineLeft("__4")) : "__4 doesn't change to 4__"; 
       assert "8__".equals(combineLeft("_44")) : "_44 doesn't change to 8__"; 
       assert "4__".equals(combineLeft("2_2")) : "2_2 doesn't change to 4__"; 
       assert "4__".equals(combineLeft("22_")) : "22_ doesn't change to 4__"; 
       assert "44_".equals(combineLeft("422")) : "422 doesn't change to 44_"; 
       assert "242".equals(combineLeft("242")) : "242 doesn't change to 242"; 
       assert "8__".equals(combineLeft(combineLeft("422"))) : "Double invocation doesn't work!"; 
// Erroneous Inputs! 
       assert "_222".equals(combineLeft("_222")) : "2222 should be invalid!"; 
       assert "333".equals(combineLeft("333"))   : "333 should be invalid!"; 
       assert "__".equals(combineLeft("__"))     : "__ should be invalid!";
   }
   public static boolean validateChar(char ch) {
      if (((ch == '4') || (ch == '2')) || (ch == '_')) {
         return true;
      }
      else {
         return false;
      }
   }
  
    public static boolean validateRow(String row) {
      if (row.length() == 3) {
       char x = row.charAt(0);
       char y = row.charAt(1);
       char z = row.charAt(2);

      if (validateChar(x)) {
         if(validateChar(y)){
            if(validateChar(z)){
               return true;
            }
            else {
               return false;
            }
         }
         else {
            return false;
         } 
           } 
   }
   return false;
 }
   public static String moveLeft(String row) {
      if(!validateRow(row)) {
         return row; 
      }
      else {
         row = row.replaceAll("_", "");
         row = row + "___";
         row = row.substring(0,3);
         return row;
      }
      
   }
  public static String combineLeft(String row) {
    if(!validateRow(row)){
      return row;
   }
   else {

      row = moveLeft(row);
 
   

     if(row.contains("22")) {
         row = row.replaceAll("22", "4_");
         row = moveLeft(row);
      }
      else if (row.contains("2_2")) {
         row = row.replaceAll("2_2", "4_");
         row = moveLeft(row);
      } 
    else {
         row = row.replaceAll("44", "8_");
         row = moveLeft(row);
      }
    
   return row;
 
  
}
}
}
