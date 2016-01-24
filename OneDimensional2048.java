public class OneDimensional2048 {
   public static void main (String[] argv) {
      int[] row; 
      assert(!validateValue(8, 4)); 
      assert(validateValue(8, 8)); 
      assert(validateValue(8, 16)); 
      assert(validateValue(0, 16)); 
      assert(!validateValue(7, 2048)); 
      assert(!validateValue(1025, 2048)); 
      assert(validateRow(new int[]{2, 2, 2, 2})); 
      assert(validateRow(new int[]{0, 2, 4, 0, 32})); 
      assert(!validateRow(new int[]{2, 2, 0, 3, 4, 0})); 
      assert(validateRow(new int[]{})); 
      assert(!validateRow(new int[]{8, 2, 64, 32, 30})); 
      row = new int[]{0,0,4,0,0}; 
      assert(moveLeft(row) && identicalRows(new int[]{4,0,0,0,0}, row)); 
      row = new int[]{0,0,4,0,0}; 
      assert(moveLeft(row) && !identicalRows(new int[]{4,0,0,0,0,0}, row)); 
      row = new int[]{2,0,4,0,0,16}; 
      assert(moveLeft(row) && identicalRows(new int[]{2,4,16,0,0,0}, row)); 
      row = new int[]{0,0,0}; 
      assert(moveLeft(row) && identicalRows(new int[]{0,0,0}, row)); 
      assert(!moveLeft(new int[]{2,0,31})); row = new int[]{4,16,2048}; 
      assert(moveLeft(row) && identicalRows(new int[]{4,16,2048}, row)); 
      row = new int[]{8,8,16,16,32,32}; 
      assert(combineLeft(row) && identicalRows(new int[]{16,32,64,0,0,0}, row)); 
      row = new int[]{2,0,2,8,0,8,64,0,64,0}; 
      assert(combineLeft(row) && identicalRows(new int[]{4,16,128,0,0,0,0,0,0,0}, row)); 
      row = new int[]{2,0,8,2,0,64,4,0,64,0}; 
      assert(combineLeft(row) && identicalRows(new int[]{2,8,2,64,4,64,0,0,0,0}, row)); 
      row = new int[]{2,0,8,2,0,64,4,0,64,0}; 
      assert(combineLeft(row) && identicalRows(new int[]{2,8,2,64,4,64,0,0,0,0}, row)); 
      row = new int[]{0,0,2,2,128,64,0,64}; 
      assert(combineLeft(row) && identicalRows(new int[]{4,128,128,0,0,0,0,0}, row)); 
      row = new int[]{0,0,2,2,128,1234,64,0,64}; 
      assert(!combineLeft(row)); row = new int[]{}; 
      assert(combineLeft(row) && identicalRows(new int[]{}, row)); 
      row = new int[]{0,1024,512,256,128,64,32,16,8,4,2,0,2,0}; 
      assert(combineLeft(row) && combineLeft(row) && combineLeft(row) && combineLeft(row) &&       
             combineLeft(row) && combineLeft(row) && combineLeft(row) && combineLeft(row) &&       
             combineLeft(row) && combineLeft(row) && identicalRows(new int[]{2048,0,0,0,0,0,0,0,0,0,0,0,0,0}, row));

   }
   public static boolean validateValue(int value, int maxPowerOfTwo) {
        int base = 2;
        if (value == 0){
            return true;
        }
        for (int i = 1; i <= maxPowerOfTwo; i++) {
           if((value >= 0)&&(value <= maxPowerOfTwo)) {
               if (Math.pow(base, i) == value)
               {
                  return true;
               }
           }  
        }
        return false;
   }
   public static boolean validateRow(int[] row) {
      for(int i = 0; i < row.length; i++) {
         if(!validateValue(row[i], 2048)){
            return false;
         }
      }
         return true;
         
   }
   public static boolean identicalRows(int[]testRow, int[] row) {
      for(int i = 0; i < row.length; i++) {
         if(testRow.length != row.length) {
            return false;
         }
         else if (testRow.length == row.length){
            testRow[i] += row[i];
         }
      }
      return true; 
   }
   public static boolean moveLeft(int[] row){
      
         if(!validateRow(row)){
            return false;
         }
         int[] z = new int[row.length];
         int x = 0;
         for(int i = 0; i < row.length; i++){
            if(row[i] != 0) {
               z[x] = row[i];
               x++;
            }
         }
         for(int i = 0; i < row.length; i++){
            row[i] = z[i];
         }  
     return true;
   } 
   public static boolean combineLeft(int[] row) {
         if(!validateRow(row)){
            return false;
         }
         moveLeft(row);
         for(int i = 0; i < row.length - 1; i++) {
            if((row[i] == row[i+1]) && (row[i] != 0)) {
               row[i] = row[i] * 2;
               row[i+1] = 0;
               moveLeft(row);
            }
         }
         moveLeft(row);

        return true;    
   }
}
