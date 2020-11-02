import javax.swing.ImageIcon;
import java.util.Random;

public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.
    private boolean flag;
	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");
        Random rand = new Random();
        this.board = board;
        this.hasBomb = (rand.nextInt(MINE_PROBABILITY) == 0);
        this.flag = false;
        checkAdjacent();

    }

    public boolean getHasBomb() {
        return this.hasBomb;
    }

    private int checkAdjacent() {
        int count = 0;
        for(int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if(i != 0 && j != 0) {
                    
                }
            }
        }
        return 0;
    }

    @Override
    public void leftClicked() {
        if(this.hasBomb){
            setImage("images/bomb.png");
            System.out.print("BombSquare.leftClicked()");
        }else{

        }
    }

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
}
