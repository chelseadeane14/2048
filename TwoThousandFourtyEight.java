public class TwoThousandFourtyEight{
   private int Score;
   private int highTile;
   private int[][] newboard; 
  
   public int getScore(){
      int sumOfTile = 0;
      int x = 0;
      for(int i = 0; i < newboard.length; i++) {
         for(int j = 0; j < newboard[i].length; j++){
            if(newboard[i][j] != 0) {
               sumOfTile = ((newboard[i][j] * 2) - 2);
               x+=sumOfTile;
              //Score of each tile equals 2x-2
            }
         }
      }
      return x;
   
   }
   public int getHighestTile() {
      int highTile = 0;
      for(int i = 0; i < newboard.length; i++) {
         for(int j = 0; j < newboard[i].length; j++) {
            if(newboard[i][j] > highTile){
               highTile = newboard[i][j];
            }
         }
      }
      return highTile;
   }
   public TwoThousandFourtyEight copy() {
      int[][] tempBoard = newboard;
      TwoThousandFourtyEight copyBoard = new TwoThousandFourtyEight(); 
      copyBoard.setBoard(this.getBoard());
      newboard = tempBoard;
      return copyBoard;
   }
   public boolean up() {
      if(TwoDimensional2048.numUnoccupied(newboard)== 0) {
         return false; 
      }
      else{
         newboard = TwoDimensional2048.up(newboard);
         TwoDimensional2048.addNewValue(newboard);
         return true;
      }
   }
   public boolean down() {
      if(TwoDimensional2048.numUnoccupied(newboard)== 0) {
         return false; 
      }
      else{
         newboard = TwoDimensional2048.down(newboard);
         TwoDimensional2048.addNewValue(newboard);
         return true;
      }
   }
   public boolean left(){
      if(TwoDimensional2048.numUnoccupied(newboard)== 0) {
         return false; 
      }
      else{
         newboard = TwoDimensional2048.left(newboard);
         TwoDimensional2048.addNewValue(newboard);
         return true;
      }
   }
   public boolean right() {
      if(TwoDimensional2048.numUnoccupied(newboard)== 0) {
         return false; 
      }
      else{
          newboard = TwoDimensional2048.right(newboard);
          TwoDimensional2048.addNewValue(newboard);
          return true;
      }
   }
   public TwoThousandFourtyEight(int x, int y) {
      newboard = new int[x][y];
      TwoDimensional2048.addNewValue(newboard);
   }
   public TwoThousandFourtyEight() {
  
   }
   public int [][] getBoard() {
      return newboard;
   }
   public int[][] setBoard(int[][] b) {
      this.newboard = b;
      return newboard;
   }
}