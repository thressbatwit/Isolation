package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import application.Clock;

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
	
	Clock c = new Clock();
	
	//Variables to determine gird and window size
	public final static int GRIDHEIGHT=12;
	public final static int GRIDWIDTH=12;
	public final static int WINDOWHEIGHT=500;
	public final static int WINDOWWIDTH=500;


	
	@Override
	public void start(Stage primaryStage) {
		try {
			//root contains the graphics of the gridelements, added in the for loop below
			GridPane root = new GridPane();
			
			//This assumes square window and graphic!
			//Stroke size must be subtracted...
			int graphicSize=WINDOWHEIGHT/GRIDHEIGHT - 3;
			
			//array containing the gridElement objects
			GridElement[][] g = new GridElement[GRIDHEIGHT][GRIDWIDTH];
			
			//create all of the grid location and
			//add them to the GridPane;
			for(int i=0;i<GRIDHEIGHT;i++) {
				for (int j=0;j<GRIDWIDTH;j++) {
					if(i == 0 || i == 11 || j == 0 || j == 11) {
						//edge squares are all black and cannot be moved onto
						g[i][j] = new GridElement(i,j,graphicSize, Color.BLACK);
						root.add(g[i][j].getGraphic(), i, j);
					} else {
						g[i][j] = new GridElement(i,j,graphicSize, Color.WHITE);
						root.add(g[i][j].getGraphic(), i, j);
					}
				}
			}
			
			//starting tiles cannot be popped
			g[1][1].changeFill(Color.LIME);
			g[10][10].changeFill(Color.LIME);
			
			//create player object and add player to gridpane
			Player p1 = new Player(10,10,graphicSize, Color.RED, true);
			root.add(p1.getGraphic(), 10, 10);
			
			//player 2 object
			Player p2 = new Player(1,1,graphicSize, Color.BLUE, false);
			root.add(p2.getGraphic(), 1, 1);
			
			
			
			//setting up keyboard interaction
			root.setOnKeyPressed(
					(e)->{
						int x=p1.getX();
						int y=p1.getY();
						
						int x2 = p2.getX();
						int y2 = p2.getY();
						
						//moves the player piece based on key input
						//the val variable and the if statement
						//ensure that you don't move past the edge
						//of the grid.
						if(e.getCode()==KeyCode.W) {
							int val=y-1;
							if(g[y-1][x].getColor()!=Color.BLACK) {
								//up
								if(x!=x2||y-1!=y2) {
									if(val<0) val=0;
									p1.move(x,val,root);
									g[x][y].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.X) {
							int val=y+1;
							if(g[y+1][x].getColor()!=Color.BLACK) {
								//down
								if(x!=x2||y+1!=y2) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p1.move(x,val,root);
									g[x][y].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.A) {
							int val=x-1;
							if(g[y][x-1].getColor()!=Color.BLACK) {
								//left
								if(x-1!=x2||y!=y2) {
									if(val<0) val=0;
									p1.move(val,y,root);
									g[x][y].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.D) {
							int val=x+1;
							if(g[y][x+1].getColor()!=Color.BLACK) {
								//right
								if(x+1!=x2||y2!=y) {
									if(val>GRIDWIDTH-1) val=GRIDWIDTH-1;
									p1.move(val,y,root);
									g[x][y].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.E) {
							int val=y-1;
							if(g[y-1][x+1].getColor()!=Color.BLACK) {
								//right and up
								if(x+1!=x2||y-1!=y2) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p1.move(x+1,val,root);
									g[x][y].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.Q) {
							int val=y-1;
							if(g[y-1][x-1].getColor()!=Color.BLACK&&y-1!=y&&x-1!=x) {
								//left and up
								if(x-1!=x2||y-1!=y2) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p1.move(x-1,val,root);
									g[x][y].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.Z) {
							int val=y+1;
							if(g[y+1][x-1].getColor()!=Color.BLACK&&y+1!=y&&x-1!=x) {
								//right down
								if(x-1!=x2||y+1!=y2) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p1.move(x-1,val,root);
									g[x][y].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.C) {
							int val=y+1;
							if(g[y+1][x+1].getColor()!=Color.BLACK&&y+1!=y&&x+1!=x) {
								//left down
								if(x+1!=x2||y+1!=y2) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p1.move(x+1,val,root);
									g[x][y].changeFill(Color.BLACK);
								}
							}
						}


						//test

						//moves the player piece based on key input
						//the val variable and the if statement
						//ensure that you don't move past the edge
						//of the grid.
						if(e.getCode()==KeyCode.Y) {
							int val=y2-1;
							if(g[y2-1][x2].getColor()!=Color.BLACK) {
								if(x!=x2||y2-1!=y) {
									if(val<0) val=0;
									p2.move(x2,val,root);
									g[x2][y2].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.N) {
							int val=y2+1;
							if(g[y2+1][x2].getColor()!=Color.BLACK) {
								if(x!=x2||y2+1!=y) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p2.move(x2,val,root);
									g[x2][y2].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.G) {
							int val=x2-1;
							if(g[y2][x2-1].getColor()!=Color.BLACK) {
								if(x2-1!=x||y!=y2) {
									if(val<0) val=0;
									p2.move(val,y2,root);
									g[x2][y2].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.J) {
							int val=x2+1;
							if(g[y2][x2+1].getColor()!=Color.BLACK) {
								if(x2+1!=x||y2!=y) {
									if(val>GRIDWIDTH-1) val=GRIDWIDTH-1;
									p2.move(val,y2,root);
									g[x2][y2].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.U) {
							int val=y2-1;
							if(g[y2-1][x2+1].getColor()!=Color.BLACK) {
								if(x2+1!=x||y2-1!=y) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p2.move(x2+1,val,root);
									g[x2][y2].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.T) {
							int val=y2-1;
							if(g[y2-1][x2-1].getColor()!=Color.BLACK) {
								if(x2-1!=x||y2-1!=y) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p2.move(x2-1,val,root);
									g[x2][y2].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.B) {
							int val=y2+1;
							if(g[y2+1][x2-1].getColor()!=Color.BLACK) {
								if(x2-1!=x||y2+1!=y) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p2.move(x2-1,val,root);
									g[x2][y2].changeFill(Color.BLACK);
								}
							}
						}
						if(e.getCode()==KeyCode.M) {
							int val=y2+1;
							if(g[y2+1][x2+1].getColor()!=Color.BLACK) {
								if(x2+1!=x||y2+1!=y) {
									if(val>GRIDHEIGHT-1) val=GRIDHEIGHT-1;
									p2.move(x2+1,val,root);
									g[x2][y2].changeFill(Color.BLACK);
								}
							}
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