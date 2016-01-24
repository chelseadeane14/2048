import java.util.Scanner;
public class TwoDimensional2048 {
   public static void main (String[] argv){
      int[][] b, br, brc;
		int[][] b2;
		int tmp;

		int[][] initb = {
			{0,2,0,0,2},
			{0,2,0,0,0},
			{0,2,0,2,0},
			{0,2,0,2,2},
			{2,0,2,0,0}};
		int[][] lb = {
			{4,0,0,0,0},
			{2,0,0,0,0},
			{4,0,0,0,0},
			{4,2,0,0,0},
			{4,0,0,0,0}};
		int[][] ub = {
			{2,4,2,4,4},
			{0,4,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0}};
		int[][] rb = {
			{0,0,0,0,4},
			{0,0,0,0,2},
			{0,0,0,0,4},
			{0,0,0,2,4},
			{0,0,0,0,4}};
		int[][] db = {
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,4,0,0,0},
			{2,4,2,4,4}};
		int[][] multb = {
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,4},
			{0,0,0,0,16}};
		int[][] bprerot = {
			{0,0,2,2},
			{0,2,2,0},
			{0,2,0,2},
			{2,0,0,0},
			{0,2,0,2},
			{0,0,0,2},
			{0,0,0,0}};
		int[][] brot1 = {
			{2,0,2,0,2,2,0},
			{2,2,0,0,0,0,0},
			{0,2,2,0,2,0,0},
			{0,0,0,2,0,0,0}};
		int[][] brot3 = {
			{0,0,0,2,0,0,0},
			{0,0,2,0,2,2,0},
			{0,0,0,0,0,2,2},
			{0,2,2,0,2,0,2}};

		// Check the creation of boards, and adding new values
		// to the board.
		b = blankBoard(5, 5);
		assert(validateBoard(b));

		for (int i = 0 ; i < 10 ; i++) {
			addNewValue(b);
		} 
		assert(validateBoard(b));
		tmp = b[1][4];
		b[1][4] = 33;
		assert(!validateBoard(b));
		b[1][4] = tmp; 
		assert(identicalBoard(initb, b));

		// Check if the randCoord method works
		brc = blankBoard(5, 6);
		// add in some random values
		brc[0][0] = 2;
		brc[1][1] = 2;
		brc[2][2] = 2;
		brc[3][3] = 2;
		brc[4][4] = 2;
		brc[4][5] = 2;
		int[][] coordAnswers = {
			{0, 1},
			{3, 2},
			{2, 0},
			{0, 4}};
		for (int i = 0 ; i < 4 ; i++) {
			int[] coord = randCoord(brc, (i * 41)%numUnoccupied(brc));
			assert(coord[0] == coordAnswers[i][0] && 
			       coord[1] == coordAnswers[i][1]);
		}

		// Check rotation.
		br = blankBoard(7, 4);
		for (int i = 0 ; i < 10 ; i++) {
			addNewValue(br);
		}
		assert(validateBoard(br));
		assert(identicalBoard(br, bprerot));
		br = rotateLeft(br);
		assert(identicalBoard(br, brot1));
		br = rotateLeft(rotateLeft(br));
		assert(identicalBoard(br, brot3));

		// Check the movement operations.
		
      b2 = b;
      b = left(b2);
		assert(identicalBoard(lb, b));
      b = up(b2);
		assert(identicalBoard(ub, b));
		b = right(b2);
		assert(identicalBoard(rb, b));
		b = down(b2);
		assert(identicalBoard(db, b));

		b = b2;
		b = left(b);
		b = up(b);
		b = right(b);
		b = down(b);
		b = right(b);
		b = down(b);
		assert(identicalBoard(multb, b));

		// Now lets test playing!
		String[] moves = {"l",
				  "lr",
				  "ldru",
				  "ldruldru",
				  "lrudrldudlr",
				  "uldruldruldruldruldr"};
		int[][][] answers = {
			{{0,0,0,0},
			 {0,0,0,0},
			 {2,0,0,0},
			 {0,0,0,0}},
			{{0,0,0,2},
			 {0,0,0,0},
			 {0,0,0,2},
			 {0,0,0,0}},
			{{2,0,0,2},
			 {0,0,0,4},
			 {0,0,0,0},
			 {0,0,0,0}},
			{{2,0,8,2},
			 {0,0,0,4},
			 {0,0,0,0},
			 {0,0,0,0}},
			{{0,0,0,2},
			 {0,0,0,0},
			 {0,0,0,2},
			 {0,8,8,2}},
			{{0,0,0,0},
			 {0,0,0,2},
			 {0,4,16,2},
			 {0,4,4,8}}
		};
		for (int i = 0 ; i < moves.length ; i++) {
			int[][] bm = blankBoard(4,4);
			for (int j = 0 ; j < moves[i].length() ; j++) {
				bm = makeMove(bm, moves[i].charAt(j));
			}
			assert(identicalBoard(answers[i], bm));
		}
   }
   public static boolean validateBoard(int[][] b) {
      for(int i = 0; i < b.length; i++) {
            if(!OneDimensional2048.validateRow(b[i])) {
               return false;
            }
         
      }
      return true;
   }
   public static int[][] blankBoard(int x, int y) {
      int[][] board = new int [x][y];
      return board;
   }
   public static boolean identicalBoard(int[][] a, int[][] b) {
      if(a.length != b.length) {
         return false;
      }
      
      for(int i = 0; i < a.length; i++) {
         if(a[i].length != b[i].length) {
            return false; 
         }
      
         for(int j = 0; j < a[i].length; j++) {
            if(a[i][j] != b[i][j]) {
               return false;  
            }
         }  
      }
      return true; 
   }
   public static int numUnoccupied(int[][] b) {
      int x = 0; 
      for(int i = 0; i < b.length; i++){
         for(int j = 0; j < b[i].length; j++) {
            if(b[i][j] == 0){
               x+=1;   
            }      
         }
      }
      return x;
   }  
   public static int [] randCoord(int[][] b, int offset) {
      int [] z = new int [2];
      int y = 0;
      int [] s = {-1, -1};
      if(offset > numUnoccupied(b)) {
         return s;
      }
      for(int i = 0; i < b.length; i++){
         for(int j = 0; j < b[i].length; j++) {
            if(b[i][j] == 0){
               if(y == offset) {
                  z[0] = i;
                  z[1] = j;
                  return z;
               }
               else {
                 y+=1; 
               }  
            }      
         }
      }
     

      return s;      
   }
   public static boolean addNewValue(int[][] b){
         if(Rnd2048.randValue() != 2){
            return false;
         }
         else{
            if(numUnoccupied(b) == 0) {
               return false; 
            }
            int val = Rnd2048.randValue();      
         
            int offset = Rnd2048.randNum(numUnoccupied(b));
            int [] z = randCoord(b, offset);
            addValue(b, z[0], z[1], val);          
         }
      
         return true; 
   } 
   public static boolean addValue(int[][] b, int x, int y, int val){

      if((x > b.length)||(x < 0)){
         return false;
      }
      
      if((y > b[0].length)||(y < 0)){
         return false; 
      }
      
      if(b[x][y] != 0) {
         return false;
      }      

      b[x][y] = val;
      return true;
   }
   public static int [][] combineLeft(int[][] b) {
      for(int i = 0; i < b.length; i++) {
         OneDimensional2048.combineLeft(b[i]);   
      }
      return b;
   }
   public static int [][] rotateLeft(int[][] b) {
      int [][] z = blankBoard(b[0].length, b.length);
      if (!validateBoard(b)) {
         int[][] r = {{-1, -1},{-1, -1}};
         return r;
      }
      for(int i = 0; i < b.length; i++) {
         for(int j = 0; j < b[i].length; j++) {
            
            z[b[i].length - 1 - j][i] = b[i][j];
         }
      }
      return z;     
   }
   public static int[][] left(int[][] b) {
      b = rotateLeft(b);
      b = rotateLeft(b);
      b = rotateLeft(b);
      b = rotateLeft(b);
      b = combineLeft(b);
      return b;      
   }
   public static int[][] right(int[][] b) {
      b = rotateLeft(b);
      b = rotateLeft(b);
      b = combineLeft(b);
      b = rotateLeft(b);
      b = rotateLeft(b);     
      return b;
   }
   public static int[][] up(int[][] b) {
      b = rotateLeft(b);
      b = combineLeft(b);
      b = rotateLeft(b);
      b = rotateLeft(b);
      b = rotateLeft(b);
      return b;     
   }
   public static int[][] down(int[][] b) {
      b = rotateLeft(b);
      b = rotateLeft(b);
      b = rotateLeft(b);
      b = combineLeft(b);
      b = rotateLeft(b);     
      return b;
   } 
     public static void play2048() {
		String s = "";
		Scanner terminalInput = new Scanner(System.in);
		int[][] b = blankBoard(5,5);
		boolean ret;
		
		ret = addNewValue(b);
		assert(ret);
		while (!s.equals("q")) {
			b = makeMove(b, s.charAt(0));
    		printBoard(b);
			s = terminalInput.nextLine();
		}		
	} 

	public static int[][] makeMove(int[][] b, char m) {
		if (!addNewValue(b)) {
			return b;
		}
		switch(m) {
		case 'l':
			return left(b);
		case 'r':
			return right(b);
		case 'u':
			return up(b);
		case 'd':
			return down(b);
		}
		return b;
	}
   public static void printBoard(int [][] b) {
      for(int i = 0; i < b.length; i++) {
         for(int j = 0; j <b[i].length; j++) {
            System.out.print(b[i][j] + " ");
         }
         System.out.println();
      }
      System.out.println();
   }
} 

