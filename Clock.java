package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Clock extends Application{
	
	private static Integer time=300;
	private static Integer minutes;
	private static Integer seconds;
	private static Integer finalSeconds;
	
	private Timeline timeline;

	private Label secondsLabel = new Label("00");
	private Label minutesLabel = new Label("5");
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Game Clock");
		Group root = new Group();
		
		GridPane grid = new GridPane();
		grid.setHgap(6);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(0,5,0,5));
		
		minutesLabel.setTextFill(Color.GREEN);
		minutesLabel.setFont(new Font("Arial",30));
		
		secondsLabel.setTextFill(Color.GREEN);
		secondsLabel.setFont(new Font("Arial",30));
		
		Button startButton = new Button();
		startButton.setText("Start");
		startButton.setPrefWidth(50);
		startButton.setPrefHeight(35);
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				startTimer();
			}	
		});
		
		Button stopButton = new Button();
		stopButton.setText("Stop");
		stopButton.setPrefWidth(50);
		stopButton.setPrefHeight(35);
		stopButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stopTimer();
			}	
		});
		
		Button resetButton = new Button();
		resetButton.setText("Reset");
		resetButton.setPrefWidth(50);
		resetButton.setPrefHeight(35);
		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				resetTimer();
			}	
		});
		
		grid.add(startButton,7, 1);
		grid.add(stopButton, 7, 4);
		grid.add(resetButton, 7, 5);
		grid.add(minutesLabel,7, 3);
		grid.add(secondsLabel,8, 3);

		root.getChildren().addAll(grid);
		primaryStage.setScene(new Scene(root,200,300));
		primaryStage.show();	
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}
	
	public void startTimer() {
		
		EventHandler<ActionEvent> timerHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
						time--;
						seconds = time;
						minutes = seconds/60;
						finalSeconds = seconds%60;
				
						minutesLabel.setText(minutes.toString());
						secondsLabel.setText(finalSeconds.toString());		
						
			}
			
		};
		
		timeline= new Timeline(new KeyFrame(Duration.millis(1000),timerHandler));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
	}
	
	public void stopTimer() {
		
		timeline.pause();
		
	}
	
	public void resetTimer() {
		
		time=300;
		minutesLabel.setText("5");
		secondsLabel.setText("00");
	}

}
