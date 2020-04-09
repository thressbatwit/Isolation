package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Program to demonstrate GridPane usage.
 * Rather than use best practices and error
 * checking, I've opted to keep the code as
 * readable as possible.
 * 
 * You move the red square with the arrow keys.
 * The exact positioning of the "player" square
 * is not correct, as can be seen from the strokes
 * on the squares that make up the grid. To correct
 * this, you'd have to play with the relative x and y
 * of the player square.
 * 
 * @author schuster
 */
public class Main extends Application {
	
	//Variables to determine gird and window size
	public final static int GRIDHEIGHT=10;
	public final static int GRIDWIDTH=10;
	public final static int WINDOWHEIGHT=500;
	public final static int WINDOWWIDTH=500;


	
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			
			//This assumes square window and graphic!
			//Stroke size must be subtracted...
			int graphicSize=WINDOWHEIGHT/GRIDHEIGHT - 3;
			
			//create all of the grid location and
			//add them to the GridPane;
			for(int i=0;i<GRIDHEIGHT;i++) {
				for (int j=0;j<GRIDWIDTH;j++) {
					root.add(new GridElement(i,j,graphicSize, Color.WHITE).getGraphic(), i, j);
				}
			}
			
			//create player object and add player to gridpane
			Player p1 = new Player(9,9,graphicSize, Color.RED);
			root.add(p1.getGraphic(), 9, 9);
			
			//player 2 object
			Player p2 = new Player(0,0,graphicSize, Color.BLUE);
			root.add(p2.getGraphic(), 0, 0);
			
			//setting up keyboard interaction
			root.setOnKeyPressed(
					(e)->{
						int x=p1.getX();
						int y=p1.getY();
						
						//moves the player piece based on key input
						//the val variable and the if statement
						//ensure that you don't move past the edge
						//of the grid.
						if(e.getCode()==KeyCode.UP) {
							int val=y-1;
							if(val<0) val=0;
							p1.move(x,val,root);
						}
						if(e.getCode()==KeyCode.DOWN) {
							int val=y+1;
							if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
							p1.move(x,val,root);
						}
						if(e.getCode()==KeyCode.LEFT) {
							int val=x-1;
							if(val<0) val=0;
							p1.move(val,y,root);
						}
						if(e.getCode()==KeyCode.RIGHT) {
							int val=x+1;
							if(val>GRIDWIDTH-1) val=GRIDWIDTH-1;
							p1.move(val,y,root);
						}
					}
				);
			
			//Standard JavaFX stuff to show the window.
			Scene scene = new Scene(root,WINDOWWIDTH,WINDOWHEIGHT);
			primaryStage.setScene(scene);
			primaryStage.show();
			root.requestFocus();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}