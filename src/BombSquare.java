public class BombSquare extends GameSquare
{
    /**
     * Private GameBoard object for obtaining board data.
     */
    private GameBoard board;                            
    /**
     * Private boolean value of the state of this BombSquare. 
     * True if this square contains a bomb. False otherwise.
     */
    private boolean hasBomb;
    /**
     * Private boolean value of the state of this BombSquare.
     * True if this square is flagged. False otherwise.
     */
    private boolean flag;
    /**
     * Private boolean value of the state of this BombSquare. 
     * True if this square is revealed. False otherwise.
     */
    private boolean revealed;
    /**
     * Integer constant controlling the probability of a square being a mine.
     */
	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");
        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
        this.flag = false;
        this.revealed = false;        
    }

    /**
     * Checks the adjacent 8 squares for the number of squares that are bombs.
     * @param bombSquare BombSquare reference, this is the square of which the adjacent squares are checked.
     * @return Integer value calculated of the number of bombs found in adjacent squares.
     */
    private int checkAdjacent(BombSquare bombSquare) {
        int count = 0;
        for(int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if((BombSquare)board.getSquareAt(bombSquare.getXLocation()+i, bombSquare.getYLocation()+j) == null) {
                    //do nothing - catches edge case squares
                }else if(((BombSquare)board.getSquareAt(bombSquare.getXLocation()+i, bombSquare.getYLocation()+j)).getHasBomb()) {
                    count++;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    /**
     * Recursive operation that reveals squares that aren't bombs around a square with 0 adjacent bombs in it.
     * @param x Integer X position value of square.
     * @param y Integer Y position value of square.
     */
    private void clearArea(int x, int y) {
        if((BombSquare)board.getSquareAt(x, y) == null)return;
        if(((BombSquare)board.getSquareAt(x, y)).getRevealed())return;

        BombSquare temp = (BombSquare)board.getSquareAt(x, y);
        if((int)checkAdjacent(temp)!=0) {
            temp.setRevealed(true);
            clickOnNumber(temp);
            return;
        }
        if((int)checkAdjacent(temp)==0) {
            temp.setRevealed(true);
            temp.setImage("images/0.png");

            clearArea(temp.getXLocation()-1, temp.getYLocation());
            clearArea(temp.getXLocation()+1, temp.getYLocation());
            clearArea(temp.getXLocation(), temp.getYLocation()-1);
            clearArea(temp.getXLocation(), temp.getYLocation()+1);
            clearArea(temp.getXLocation()-1, temp.getYLocation()+1);
            clearArea(temp.getXLocation()+1, temp.getYLocation()-1);
            clearArea(temp.getXLocation()-1, temp.getYLocation()-1);
            clearArea(temp.getXLocation()+1, temp.getYLocation()+1);
        }
        
    }

    /**
     * Method handling the logic behind clicking on a square that isn't a bomb - changing the icon of these squares appropiately or clearing the area around a 0 square.
     * @param bombSquare The BombSquare object reference to which has been clicked on.
     */
    private void clickOnNumber(BombSquare bombSquare) {
        switch((int)checkAdjacent(bombSquare)){
            case 0:
                bombSquare.setImage("images/0.png");
                clearArea(bombSquare.getXLocation(), bombSquare.getYLocation());
                break;                    
            case 1:
                bombSquare.setImage("images/1.png");
                break;
            case 2:
                bombSquare.setImage("images/2.png");
                break;
            case 3:
                bombSquare.setImage("images/3.png");
                break;
            case 4:
                bombSquare.setImage("images/4.png");
                break;
            case 5:
                bombSquare.setImage("images/5.png");
                break;
            case 6:
                bombSquare.setImage("images/6.png");
                break;
            case 7:
                bombSquare.setImage("images/7.png");
                break;
            case 8:
                bombSquare.setImage("images/8.png");
                break;
            case 9:
                bombSquare.setImage("images/9.png");
                break;
            default:
                break;
        }
    }

    /**
     * Method invoked when a BombSquare button has been clicked with the left mouse button.
     */
    @Override
    public void leftClicked() {
        if(!flag) {            
            if(this.hasBomb) {                
                setImage("images/bomb.png");
                System.out.println("GAME OVER");
                this.revealed = true;
            }else {
                clickOnNumber(this);
                this.revealed = true;
            }
        }
    }

    /**
     * Method invoked when a BombSquare button has been clicked with the right mouse button.
     */
    @Override
    public void rightClicked() {
        if(!flag){
            flag = true;
            setImage("images/flag.png");
        }else{
            flag = false;
            setImage("images/blank.png");
        }
    }

    //Getters and setters for object private variables.
    public boolean getHasBomb() {
        return this.hasBomb;
    }
    
    public boolean getFlag() {
        return this.flag;
    }

    public boolean getRevealed() {
        return this.revealed;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }
}
