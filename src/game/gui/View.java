package game.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View extends Application implements EventHandler<ActionEvent>{
	
	// class attributes
	public static Battle battle;
	public static Stage primaryStage;
	public static Scene scene;	
	public static Scene startScene;
	public static StackPane root;
	public static BorderPane root1;
	private static StackPane startGameRoot = new StackPane();
	public static BorderPane startGameBorderPane = new BorderPane();
	private static BorderPane gameBorderPane;
	private static Button exitButton;
	public static MediaPlayer songPlayer;
	public static boolean easyMode;
	public static boolean hardMode;
	public static VBox leftBox;
	public static HBox infoBox;
	public static VBox rightBox;
	public static HBox topBox;
	public static ScrollPane lanesScrollPanel = new ScrollPane();
	public static VBox lanesView;
	public static ArrayList<Boolean> isLaneLost = new ArrayList<Boolean>();
	public static ArrayList<HBox> lanes = new ArrayList<HBox>(); // this corresponds to the original lanes in the GUI
	public static ArrayList<Label> lanesInfo;
	public static ArrayList<AnchorPane> lanesTitansPanes;
	public static HBox titansView = new HBox();
	private static Label currentScore;
	private static Label currentTurn;
	private static Label currentPhase;
	private static Label currentResources;
	private static HBox currentInfoBox;
	private static ImageView weaponShopView;
	private static Label weaponShopLabel;
	private static BorderPane weaponShopPane;
	private static HBox weaponHBox;
	private static ImageView passTurnImageView;
	private static Label passTurnLabel;
	private static VBox weaponVBox1;
	private static VBox weaponVBox2;
	private static VBox weaponVBox3;
	private static VBox weaponVBox4;
	private static Button weaponButton1;
	private static Button weaponButton2;
	private static Button weaponButton3;
	private static Button weaponButton4;
	private static Button closeShop;
	private static ToggleGroup toggleGroup;
	private static RadioButton lane1btn;
	private static RadioButton lane2btn;
	private static RadioButton lane3btn;
	private static RadioButton lane4btn;
	private static RadioButton lane5btn;
	private static Button confirmButton;
	private static Label selectedLane;
	private static VBox radioBtnsVBox;
	private static Button startGameButton;
	private static VBox Box;
	private static ImageView PiercingCannonImage = new ImageView(new Image("game/gui/media/weapons/PiercingCannon.png"));
	private static ImageView SniperCannonImage = new ImageView(new Image("game/gui/media/Weapons/SniperCannon.png"));
	private static ImageView VolleySpreadCannonImage = new ImageView(new Image("game/gui/media/Weapons/VolleySpreadCannon.png"));;
	private static ImageView WallTrapImage = new ImageView(new Image("game/gui/media/Weapons/WallTrap.png"));
	private static Button easyModeButton;
	private static Button hardModeButton;
	private static HBox hbox;
	private static ArrayList<ArrayList<Label>> allWeaponsInLane;
	private static ArrayList<PriorityQueue<ImageView>> titans;
	private static ImageView helpImageView;
	private static Label helpLabel;
	private static BorderPane helpPane;
	private static Button closeHelp;
	private static boolean helpImageClicked;
	private static boolean weaponShopClicked;
	
	@Override
	public void start(Stage primaryStage1) throws Exception {	
		
		// Setting the stage where the game will take place in.
		View.primaryStage = primaryStage1;
		View.primaryStage.setTitle("Attack On Titan");
		View.primaryStage.setFullScreen(true);
		View.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		View.primaryStage.setFullScreenExitHint("");
		View.primaryStage.getIcons().add(new Image("game/gui/media/logo.jpg"));
		
		BorderPane root0 = new BorderPane(); // border pane to add the click anywhere in it.
		StackPane stack = new StackPane(); // stack pane to show the intro video and the click anywhere on it.
		
		// Setting the intro video for the game
		try {
		File mediaFile = new File("./src/game/gui/media/Intro.mp4");
		Media media = new Media(mediaFile.toURI().toString());
		MediaPlayer introMediaPlayer = new MediaPlayer(media);
		MediaView introMediaView = new MediaView(introMediaPlayer);
		introMediaPlayer.setAutoPlay(true);
		introMediaView.setMediaPlayer(introMediaPlayer);
		introMediaView.fitHeightProperty().bind(primaryStage.heightProperty());
		introMediaView.fitWidthProperty().bind(primaryStage.widthProperty());
		introMediaView.setVisible(true);
		stack.getChildren().add(introMediaView);
		
		introMediaPlayer.setOnEndOfMedia( () -> {
			startGameScene();
		});
		
		stack.setOnMouseClicked(e -> {
			introMediaPlayer.stop();
			startGameScene();
		});
		
		} catch (Exception e) {
			System.out.println("Intro.mp4 NOT FOUND!");
		}
		stack.getChildren().add(root0); 
		Label skipLabel = new Label("CLICK ANYWHERE TO SKIP");
		skipLabel.setPadding(new Insets(30,30,30,30));
		skipLabel.setTextFill(Color.color(1, 1, 1));
		skipLabel.setFont(new Font("Bebas Neue", 20));
		HBox skipBox = new HBox();
		skipBox.setAlignment(Pos.BOTTOM_CENTER);
		skipBox.getChildren().add(skipLabel);
		root0.setBottom(skipBox);
		Scene scene = new Scene (stack);
		View.scene = scene;
		View.primaryStage.setScene(scene);
		View.primaryStage.show();
	}
	
	public void startGameScene() {
		
		// Organizing the Scene
		
		// setting the image background image
		View.startGameRoot = new StackPane();
		View.startGameBorderPane = new BorderPane();
		
		try {
			File filePath = new File("./src/game/gui/media/back1.mp4");
			Media media = new Media(filePath.toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
			MediaView mediaView = new MediaView(mediaPlayer);
			mediaView.fitWidthProperty().bind(View.primaryStage.widthProperty());
			mediaView.fitHeightProperty().bind(View.primaryStage.heightProperty());
			mediaView.setPreserveRatio(false);
			View.startGameRoot.getChildren().add(mediaView);
		} catch (Exception e) {
			
		}
		
		View.startGameRoot.setBackground(null);
		View.startGameRoot.getChildren().add(View.startGameBorderPane);
		
		HBox startGameBox = new HBox(50);
		startGameBox.getStyleClass().add("mode-button-container");
		
		HBox GameTitleBox = new HBox(10);
		Label GameLabel = new Label("ATTACK ON TITAN");
		
		GameLabel.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 50));
		GameLabel.setTextFill(Color.rgb(255, 255, 204));
		GameTitleBox.setAlignment(Pos.CENTER);
		GameTitleBox.getChildren().add(GameLabel);
		startGameBox.setAlignment(Pos.CENTER);
		
		View.startGameButton = new Button("START GAME");
		View.startGameButton.getStyleClass().add("easy-mode-button");
		View.startGameButton.setTextAlignment(TextAlignment.CENTER);
		View.startGameButton.setOnAction(e -> checkPlayingMode());
		
		// Create the exit button
		Button exitButton = new Button("Exit");
		exitButton.setOnAction(e -> View.primaryStage.close());
		
		// Create an HBox to hold the button
		HBox buttonBox = new HBox(exitButton);
		buttonBox.getStyleClass().add("exit-button-container");
		buttonBox.setStyle("-fx-padding: 10px; -fx-alignment: top-right;");
		
		startGameBox.getChildren().addAll(View.startGameButton);
		View.Box = new VBox();
		Box.getChildren().add(GameTitleBox);
		Box.getChildren().add(startGameBox);
		Box.setAlignment(Pos.CENTER);
		View.startGameBorderPane.setCenter(Box);
		View.startGameBorderPane.setBottom(buttonBox);

		View.scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		View.scene.setRoot(View.startGameRoot);
		View.primaryStage.setFullScreen(true);
	}
	
	public void checkPlayingMode() {
		
		// Organizing the Scene
		
		// setting the image background
		StackPane root = new StackPane();
		BorderPane root1 = new BorderPane();
		
		try {
			Image background = new Image("game/gui/media/back11.jpg");
			ImageView backgroundIV = new ImageView(background);
			backgroundIV.fitHeightProperty().bind(View.primaryStage.heightProperty());
			backgroundIV.fitWidthProperty().bind(View.primaryStage.widthProperty());
			root.getChildren().add(backgroundIV);
		} catch (Exception e) {
			System.out.println("back11.jpg NOT FOUND");
		}
		
		HBox chooseMode = new HBox(50);
		chooseMode.getStyleClass().add("mode-button-container");
		
		HBox welcomeBox = new HBox(10);
		Label welcome = new Label("Choose Game Mode");
		
		welcome.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 50));
		welcome.setTextFill(Color.rgb(255, 255, 204));
		welcomeBox.setAlignment(Pos.CENTER);
		welcomeBox.getChildren().add(welcome);
		chooseMode.setAlignment(Pos.CENTER);
		
		View.easyModeButton = new Button("EASY");
		easyModeButton.getStyleClass().add("easy-mode-button");
		easyModeButton.setTextAlignment(TextAlignment.CENTER);
		easyModeButton.setOnAction(e -> {
			View.easyMode = true;
			showInstructionScene();
		});
		
		View.hardModeButton = new Button("HARD");
		hardModeButton.getStyleClass().add("hard-mode-button");
		hardModeButton.setTextAlignment(TextAlignment.CENTER);
		hardModeButton.setOnAction(e ->{
			View.hardMode = true;
			showInstructionScene();
		});
		
		// Create the exit button
		Button exitButton = new Button("Exit");
		exitButton.setOnAction(e -> View.primaryStage.close());
		
		// Create an HBox to hold the button
		HBox buttonBox = new HBox(exitButton);
		buttonBox.getStyleClass().add("exit-button-container");
		buttonBox.setStyle("-fx-padding: 10px; -fx-alignment: top-right;");
		
		chooseMode.getChildren().addAll(easyModeButton, hardModeButton);
		VBox chooseModeBox = new VBox();
		chooseModeBox.getChildren().add(welcomeBox);
		chooseModeBox.getChildren().add(chooseMode);
		chooseModeBox.setAlignment(Pos.CENTER);
		root1.setCenter(chooseModeBox);
		root1.setBottom(buttonBox);
		root.getChildren().add(root1);
		View.scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		View.scene.setRoot(root);
		View.primaryStage.setFullScreen(true);
	}
	
	public static void showInstructionScene() {
		// Create a new BorderPane for the instruction scene
		StackPane root = new StackPane();
		BorderPane root1 = new BorderPane();
		
		try {
			Image background = new Image("game/gui/media/logo.jpg");
			ImageView backgroundIV = new ImageView(background);
			backgroundIV.fitHeightProperty().bind(View.primaryStage.heightProperty());
			backgroundIV.fitWidthProperty().bind(View.primaryStage.widthProperty());
			root.getChildren().add(backgroundIV);
		} catch (Exception e) {
			System.out.println("logo.jpg NOT FOUND");
		}
		
		// Create the exit button
		Button exitButton = new Button("Exit");
		exitButton.setOnAction(e -> View.primaryStage.close());
				
		// Create an HBox to hold the button
		HBox buttonBox = new HBox(exitButton);
		buttonBox.getStyleClass().add("exit-button-container");
		buttonBox.setStyle("-fx-padding: 10px; -fx-alignment: top-right;");
		
		VBox instructionsContainer = new VBox();
		instructionsContainer.setAlignment(Pos.CENTER);
		instructionsContainer.setPadding(new Insets(50));
		instructionsContainer.setStyle("-fx-background-color: rgba(0,0,0,0.5);");
		Label instructionsTitle = new Label("Game Instructions");
		instructionsTitle.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 70));
		instructionsTitle.setTextFill(Color.rgb(255, 255, 204));
		
		VBox instructionsContent = new VBox();
		instructionsContent.setPadding(new Insets(20));
		instructionsContent.setSpacing(10);
		instructionsContent.setAlignment(Pos.TOP_LEFT);

		// TODO: Add Game instructions here
		Label l1 = new Label("BATTLE FIELD:");
		Label l2 = new Label("The battle field is divided into multiple lanes, each lane will have the following:\n"
				+ "    1. A part of the wall to be defended. This wall part will have a starting HP that decreases after being attacked\n"
				+ "       and if this part of the wall is destroyed, this lane will no longer be considered an active lane and will be a lost lane.\n"
				+ "    2. The weapons that the player has already deployed into this lane.\n"
				+ "    3. The titans that are on their way to attack the part of the wall at the end of the lane. The titans can be at different distances from the wall\n"
				+ "       depending on how much they have already moved. Each titan will have a starting HP that decreases after being attacked.");
		Label l3 = new Label("TURN ACTIONS: ");
		Label l4 = new Label("Each turn the player can choose to either Purchase and Deploy a weapon or pass their turn without any actions.\n"
				+ "Either way the turn will proceed as follows. After the players's action, The titans will do their move action.\n"
				+ "Then the weapons will don their attack action followd by the titans' attack actions. After that, Titan will be added to the lanes\n"
				+ "according to the logic mentioned above. Finally, finalizing the turn by updating the battle phase and the relevant info if needed based on\n"
				+ "the number of elapsed turns, also according to the logic mentioned above.");
		l1.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 30));
		l1.setTextFill(Color.rgb(255, 255, 204));
		l2.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 20));
		l2.setTextFill(Color.rgb(255, 255, 204));
		l3.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 30));
		l3.setTextFill(Color.rgb(255, 255, 204));
		l4.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 20));
		l4.setTextFill(Color.rgb(255, 255, 204));
		
		instructionsContent.getChildren().addAll(l1, l2, l3, l4);
		
		Button nextScene = new Button("PLAY!");
		nextScene.getStyleClass().add("easy-mode-button");
		nextScene.setOnAction(e -> {
			try {
				GameScene();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		HBox nextSceneBox = new HBox(nextScene);
		nextSceneBox.getStyleClass().add("easy-mode-button");
		nextSceneBox.setStyle("-fx-padding: 10px;");
		nextSceneBox.setAlignment(Pos.CENTER);

		
		instructionsContainer.getChildren().add(instructionsTitle);
		instructionsContainer.getChildren().add(instructionsContent);
		instructionsContainer.getChildren().add(nextSceneBox);
		
		root1.setCenter(instructionsContainer);
		root1.setBottom(buttonBox);
		root.getChildren().add(root1);
		View.scene.setRoot(root);
		View.primaryStage.setFullScreen(true);
	}

	public static void GameScene() throws IOException {
		View.root = new StackPane();
		View.root1 = new BorderPane();
		
		try {
			Image background = new Image("game/gui/media/back1.jpg");
			ImageView backgroundIV = new ImageView(background);
			backgroundIV.fitHeightProperty().bind(View.primaryStage.heightProperty());
			backgroundIV.fitWidthProperty().bind(View.primaryStage.widthProperty());
			View.root.getChildren().add(backgroundIV);
		} catch (Exception e) {
			System.out.println("back1.jpg NOT FOUND");
		}
		
		// Create the exit button
		View.exitButton = new Button("Exit");
		exitButton.setOnAction(e -> View.primaryStage.close());
		
		// Create an HBox to hold the button
		HBox buttonBox = new HBox(exitButton);
		buttonBox.getStyleClass().add("exit-button-container");
		buttonBox.setStyle("-fx-padding: 10px; -fx-alignment: top-right;");
		View.root1.setBottom(buttonBox);
		
		int initialNumOfLanes = 0;
		int initialResourcesPerLane = 0;
		if(View.easyMode) {
			initialNumOfLanes = 3;
			initialResourcesPerLane = 250;
		}
		else if(View.hardMode) {
			initialNumOfLanes = 5;
			initialResourcesPerLane = 125;
		}
		
		// Created the battle
		View.battle = new Battle(1, 0, 100, initialNumOfLanes, initialResourcesPerLane);
		
		View.gameBorderPane = new BorderPane();
		View.root1.setCenter(gameBorderPane);
		
		// Add the current info of the gameplay
		View.currentInfoBox = showCurrentInfo(View.battle);
		View.gameBorderPane.setBottom(currentInfoBox);
		
		// Define a VBox for the left part of the gameBorderPane which will contain the weaponShop and titansInfo and weaponInfo
		View.leftBox = createGameLeftBox(View.battle);
		gameBorderPane.setLeft(leftBox);
		BorderPane.setMargin(leftBox, new Insets(20));
		
		View.setUpLanes();

		
		View.root.getChildren().add(View.root1);
		View.scene.setRoot(View.root);
		View.primaryStage.setFullScreen(true);
	}

	public static HBox showCurrentInfo(Battle battle) {
		HBox currentInfoBox = new HBox();
		currentInfoBox.setSpacing(15);
		currentInfoBox.setAlignment(Pos.CENTER);
		
		View.currentScore = new Label ("Score: " + battle.getScore());
		View.currentTurn = new Label ("Turn: " + battle.getNumberOfTurns());
		View.currentPhase = new Label ("Phase: " + battle.getBattlePhase());
		View.currentResources = new Label ("Resources: " + battle.getResourcesGathered());
		
		View.currentScore.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 30));
		View.currentScore.setTextFill(Color.rgb(37, 211, 102));
		
		View.currentTurn.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 30));
		View.currentTurn.setTextFill(Color.rgb(255, 255, 204));

		View.currentPhase.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 30));
		View.currentPhase.setTextFill(Color.rgb(163, 68, 62));
		
		View.currentResources.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 30));
		View.currentResources.setTextFill(Color.rgb(255, 255, 204));
		
		currentInfoBox.getChildren().addAll(currentScore, currentTurn, currentPhase, currentResources);
		
		HBox bottomContainer = new HBox(currentInfoBox);
		bottomContainer.setAlignment(Pos.CENTER);
		
		return bottomContainer;
	}
	
	public static void updateCurrentInfo() {
		View.currentScore.setText("Score: " + View.battle.getScore());
		View.currentTurn.setText("Turn: " + View.battle.getNumberOfTurns());
		View.currentPhase.setText("Phase: " + View.battle.getBattlePhase());
		View.currentResources.setText("Resources: " + View.battle.getResourcesGathered());
	}
	
	public static VBox createGameLeftBox (Battle battle) {
		VBox res = new VBox();
		res.setSpacing(30);
		res.setAlignment(Pos.CENTER);
		
		VBox weaponShopBox = new VBox();
		weaponShopBox.setAlignment(Pos.CENTER);
		
		View.weaponShopLabel = new Label ("Purchase Weapon");
		View.weaponShopLabel.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 25));
		View.weaponShopLabel.setTextFill(Color.rgb(255, 255, 204));
		View.weaponShopLabel.setAlignment(Pos.CENTER);
		
		Image weaponShop = new Image("game/gui/media/shop.png");
		View.weaponShopView = new ImageView(weaponShop);
		View.weaponShopView.setFitWidth(150);
		View.weaponShopView.setFitHeight(150);
		View.weaponShopView.setPreserveRatio(true);
		weaponShopBox.getChildren().add(View.weaponShopView);
		
		weaponShopBox.getChildren().add(View.weaponShopLabel);
		
		res.getChildren().add(weaponShopBox);
		
		View.weaponShopClicked = false;
		
		View.weaponShopView.setOnMouseClicked(e ->{
			if(!weaponShopClicked) {
				View.weaponShopView.setOpacity(0.5);
				weaponShopClicked = true;
				View.weaponShopPane = new BorderPane();
				View.weaponShopPane.setMaxSize(800, 400);
				View.weaponShopPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(5), new Insets(5))));
				
				View.closeShop = new Button("Close Shop");
				closeShop.setOnAction(closeEvent ->{
					if(weaponShopClicked) {
						View.weaponShopView.setOpacity(1);
						weaponShopClicked = false;
						View.root.getChildren().remove(View.weaponShopPane);
					}
				});
				
				HBox closeShopBox = new HBox(closeShop);
				closeShopBox.setAlignment(Pos.CENTER);
				closeShop.setTextAlignment(TextAlignment.CENTER);
				BorderPane.setMargin(closeShopBox, new Insets(20));
				closeShopBox.getStyleClass().add("exit-button-container");
				View.weaponShopPane.setBottom(closeShopBox);
				
				View.weaponHBox = new HBox(10);
				View.weaponHBox.setAlignment(Pos.BOTTOM_CENTER);
				
				View.toggleGroup = new ToggleGroup();
				
				View.radioBtnsVBox = new VBox();
				View.lane1btn = new RadioButton("Lane 1");
				lane1btn.setToggleGroup(toggleGroup);
				
				View.lane2btn = new RadioButton("Lane 2");
				View.lane2btn.setToggleGroup(toggleGroup);
				
				View.lane3btn = new RadioButton("Lane 3");
				lane3btn.setToggleGroup(toggleGroup);
				
				if(View.hardMode) {
					View.lane4btn = new RadioButton("Lane 4");
					lane4btn.setToggleGroup(toggleGroup);
					
					View.lane5btn = new RadioButton("Lane 5");
					lane5btn.setToggleGroup(toggleGroup);
				}
				View.radioBtnsVBox.getChildren().addAll(View.lane1btn, View.lane2btn, View.lane3btn);
				if(View.hardMode) {
					View.radioBtnsVBox.getChildren().addAll(View.lane4btn, View.lane5btn);
				}			
				
				View.confirmButton = new Button("Confirm");
				
				View.confirmButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
				View.confirmButton.setOnMouseEntered(enterEvent -> View.confirmButton.setStyle("-fx-background-color: darkblue; -fx-text-fill: white;"));
				View.confirmButton.setOnMouseExited(exitEvent -> View.confirmButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;"));
				
				View.selectedLane = new Label("");
				
				confirmButton.setOnAction(confirmEvent -> {
					RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
					
					if(selectedRadioButton != null) {
						selectedLane.setText("Selected: " + selectedRadioButton.getText());
					} else {
						selectedLane.setText("No option selected");
					}
				});
				
				View.radioBtnsVBox.getChildren().addAll(View.confirmButton, View.selectedLane);
				
				View.weaponVBox1 = new VBox();
				View.weaponVBox1.setAlignment(Pos.BOTTOM_CENTER);
				View.weaponButton1 = new Button("Name: Anti-Titan Shell" + '\n' + "Type: Piercing Cannon" + '\n' + "Price: 25" + '\n' + "Damage Points: 10");
				View.weaponButton1.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-text-alignment: center;");
				View.weaponButton1.setOnMouseEntered(enterEvent -> View.weaponButton1.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-text-alignment: center;"));
				View.weaponButton1.setOnMouseExited(exitEvent -> View.weaponButton1.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-text-alignment: center;"));
				View.weaponButton1.setOnAction(b1ev ->{
					if(selectedLane.getText().equals("No option selected") || selectedLane.getText().equals("")) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("NO LANE SELECTED");
						alert.setHeaderText(null);
						alert.setContentText("Please Select a lane");
						alert.initOwner(View.primaryStage);
						alert.showAndWait();
					}
	
					else {
						if (View.battle.isGameOver()) {
							View.easyMode = false;
							View.hardMode = false;
							View.lanes.removeAll(View.lanes);
							View.lanesView = new VBox(10);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("GAME OVER");
							alert.setHeaderText(null);
							alert.setContentText("YOU LOST! ALL LANES ARE DESTROYED\nYOUR SCORE: "+View.battle.getScore());
							alert.initOwner(View.primaryStage);
							alert.showAndWait();		
							View.scene.setRoot(View.startGameRoot);
						}
						else {
							char s = View.selectedLane.getText().charAt(15);
							String sx = s+"";
							int laneNumber = Integer.parseInt(sx) - 1;
							Lane l = View.battle.getOriginalLanes().get(laneNumber);
							try {
								View.battle.purchaseWeapon(1, l);
								View.updateCurrentInfo();
								View.updateLanes();
								View.updateTitansInLanes();
								View.updateLanesInfo();
								String ffff = View.allWeaponsInLane.get(laneNumber).get(0).getText().substring(3);
								int i = Integer.parseInt(ffff) + 1;
								View.allWeaponsInLane.get(laneNumber).get(0).setText(" x " + i);
							} catch (InsufficientResourcesException e1) {
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Insufficient Resources");
								alert.setHeaderText(null);
								alert.setContentText(e1.getMessage());
								alert.initOwner(View.primaryStage);
								alert.showAndWait();
							} catch (InvalidLaneException e1) {
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Invalid Lane");
								alert.setHeaderText(null);
								alert.setContentText(e1.getMessage());
								alert.initOwner(View.primaryStage);
								alert.showAndWait();
							}
						}
					}
				});
				View.PiercingCannonImage.setFitWidth(140);
				View.PiercingCannonImage.setFitHeight(1400);
				View.PiercingCannonImage.setPreserveRatio(true);
				View.weaponVBox1.getChildren().addAll( View.PiercingCannonImage, View.weaponButton1);
				View.weaponVBox1.setAlignment(Pos.BOTTOM_CENTER);
	
				View.weaponVBox2 = new VBox();
				View.weaponVBox2.setAlignment(Pos.BOTTOM_CENTER);
				View.weaponButton2 = new Button("Name: Long Range Spear" + '\n' + "Type: Sniper Cannon" + '\n' + "Price: 25" + '\n' + "Damage Points: 35");
				View.weaponButton2.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-text-alignment: center;");
				View.weaponButton2.setOnMouseEntered(enterEvent -> View.weaponButton2.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-text-alignment: center;"));
				View.weaponButton2.setOnMouseExited(exitEvent -> View.weaponButton2.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-text-alignment: center;"));
				View.weaponButton2.setOnAction(b2ev ->{
					if(selectedLane.getText().equals("No option selected") || selectedLane.getText().equals("")) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("NO LANE SELECTED");
						alert.setHeaderText(null);
						alert.setContentText("Please Select a lane");
						alert.initOwner(View.primaryStage);
						alert.showAndWait();
					}
					else {
						if (View.battle.isGameOver()) {
							View.easyMode = false;
							View.hardMode = false;
							View.lanes.removeAll(View.lanes);
							View.lanesView = new VBox(10);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("GAME OVER");
							alert.setHeaderText(null);
							alert.setContentText("YOU LOST! ALL LANES ARE DESTROYED\nYOUR SCORE: "+View.battle.getScore());
							alert.initOwner(View.primaryStage);
							alert.showAndWait();		
							View.scene.setRoot(View.startGameRoot);
						}
						else {
							char s = View.selectedLane.getText().charAt(15);
							String sx = s+"";
							int laneNumber = Integer.parseInt(sx) - 1;
							Lane l = View.battle.getOriginalLanes().get(laneNumber);
							
							try {
								View.battle.purchaseWeapon(2, l);
								View.updateCurrentInfo();
								View.updateLanes();
								View.updateTitansInLanes();
								View.updateLanesInfo();
								String ffff = View.allWeaponsInLane.get(laneNumber).get(1).getText().substring(3);
								int i = Integer.parseInt(ffff) + 1;
								View.allWeaponsInLane.get(laneNumber).get(1).setText(" x " + i);
							} catch (InsufficientResourcesException e1) {
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Insufficient Resources");
								alert.setHeaderText(null);
								alert.setContentText(e1.getMessage());
								alert.initOwner(View.primaryStage);
								alert.showAndWait();
							} catch (InvalidLaneException e1) {
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Invalid Lane");
								alert.setHeaderText(null);
								alert.setContentText(e1.getMessage());
								alert.initOwner(View.primaryStage);
								alert.showAndWait();
							}
						}
					}
				});
				View.SniperCannonImage.setFitWidth(140);
				View.SniperCannonImage.setFitHeight(1400);
				View.SniperCannonImage.setPreserveRatio(true);
				View.weaponVBox2.getChildren().addAll(View.SniperCannonImage,View.weaponButton2);
				View.weaponVBox2.setAlignment(Pos.BOTTOM_CENTER);
	
				View.weaponVBox3 = new VBox();
				View.weaponVBox3.setAlignment(Pos.BOTTOM_CENTER);
				View.weaponButton3 = new Button("Name: Wall Spread Cannon" + '\n' + "Type: Volley Spread Cannon" + '\n' + "Price: 100" + '\n' + "Damage Points: 5");
				View.weaponButton3.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-text-alignment: center;");
				View.weaponButton3.setOnMouseEntered(enterEvent -> View.weaponButton3.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-text-alignment: center;"));
				View.weaponButton3.setOnMouseExited(exitEvent -> View.weaponButton3.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-text-alignment: center;"));
				View.weaponButton3.setOnAction(b3ev ->{
					if(selectedLane.getText().equals("No option selected")|| selectedLane.getText().equals("")) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("NO LANE SELECTED");
						alert.setHeaderText(null);
						alert.setContentText("Please Select a lane");
						alert.initOwner(View.primaryStage);
						alert.showAndWait();
					}
					else {
						if (View.battle.isGameOver()) {
							View.easyMode = false;
							View.hardMode = false;
							View.lanes.removeAll(View.lanes);
							View.lanesView = new VBox(10);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("GAME OVER");
							alert.setHeaderText(null);
							alert.setContentText("YOU LOST! ALL LANES ARE DESTROYED\nYOUR SCORE: "+View.battle.getScore());
							alert.initOwner(View.primaryStage);
							alert.showAndWait();		
							View.scene.setRoot(View.startGameRoot);
						}
						else {
							char s = View.selectedLane.getText().charAt(15);
							String sx = s+"";
							int laneNumber = Integer.parseInt(sx) - 1;
							Lane l = View.battle.getOriginalLanes().get(laneNumber);
							
							try {
								View.battle.purchaseWeapon(3, l);
								View.updateCurrentInfo();
								View.updateLanes();
								View.updateTitansInLanes();
								View.updateLanesInfo();							
								String ffff = View.allWeaponsInLane.get(laneNumber).get(2).getText().substring(3);
								int i = Integer.parseInt(ffff) + 1;
								View.allWeaponsInLane.get(laneNumber).get(2).setText(" x " + i);
							} catch (InsufficientResourcesException e1) {
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Insufficient Resources");
								alert.setHeaderText(null);
								alert.setContentText(e1.getMessage());
								alert.initOwner(View.primaryStage);
								alert.showAndWait();
							} catch (InvalidLaneException e1) {
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Invalid Lane");
								alert.setHeaderText(null);
								alert.setContentText(e1.getMessage());
								alert.initOwner(View.primaryStage);
								alert.showAndWait();
							}
						}
					}
				});
				View.VolleySpreadCannonImage.setFitWidth(140);
				View.VolleySpreadCannonImage.setFitHeight(1400);
				View.VolleySpreadCannonImage.setPreserveRatio(true);
				View.weaponVBox3.getChildren().addAll(View.VolleySpreadCannonImage,View.weaponButton3);
				View.weaponVBox3.setAlignment(Pos.BOTTOM_CENTER);
	
				View.weaponVBox4 = new VBox();
				View.weaponVBox4.setAlignment(Pos.BOTTOM_CENTER);
				View.weaponButton4 = new Button("Name: Proximity Trap" + '\n' + "Type: Wall Trap" + '\n' + "Price: 75" + '\n' + "Damage Points: 100");
				View.weaponButton4.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-text-alignment: center;");
				View.weaponButton4.setOnMouseEntered(enterEvent -> View.weaponButton4.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-text-alignment: center;"));
				View.weaponButton4.setOnMouseExited(exitEvent -> View.weaponButton4.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-text-alignment: center;"));
				View.weaponButton4.setOnAction(b4ev ->{
					if(selectedLane.getText().equals("No option selected")|| selectedLane.getText().equals("")) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("NO LANE SELECTED");
						alert.setHeaderText(null);
						alert.setContentText("Please Select a lane");
						alert.initOwner(View.primaryStage);
						alert.showAndWait();
					}
					else {
						if (View.battle.isGameOver()) {
							View.easyMode = false;
							View.hardMode = false;
							View.lanes.removeAll(View.lanes);
							View.lanesView = new VBox(10);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("GAME OVER");
							alert.setHeaderText(null);
							alert.setContentText("YOU LOST! ALL LANES ARE DESTROYED\nYOUR SCORE: "+View.battle.getScore());
							alert.initOwner(View.primaryStage);
							alert.showAndWait();		
							View.scene.setRoot(View.startGameRoot);
						}
						else {
							char s = View.selectedLane.getText().charAt(15);
							String sx = s+"";
							int laneNumber = Integer.parseInt(sx) - 1;
							Lane l = View.battle.getOriginalLanes().get(laneNumber);
							
							try {
								View.battle.purchaseWeapon(4, l);
								View.updateCurrentInfo();
								View.updateLanes();
								View.updateTitansInLanes();
								View.updateLanesInfo();
								String ffff = View.allWeaponsInLane.get(laneNumber).get(3).getText().substring(3);
								int i = Integer.parseInt(ffff) + 1;
								View.allWeaponsInLane.get(laneNumber).get(3).setText(" x " + i);
							} catch (InsufficientResourcesException e1) {
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Insufficient Resources");
								alert.setHeaderText(null);
								alert.setContentText(e1.getMessage());
								alert.initOwner(View.primaryStage);
								alert.showAndWait();
							} catch (InvalidLaneException e1) {
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Invalid Lane");
								alert.setHeaderText(null);
								alert.setContentText(e1.getMessage());
								alert.initOwner(View.primaryStage);
								alert.showAndWait();
							}
						}
					}
					
				});
				View.WallTrapImage.setFitWidth(140);
				View.WallTrapImage.setFitHeight(1400);
				View.WallTrapImage.setPreserveRatio(true);
				
				View.weaponVBox4.getChildren().addAll(View.WallTrapImage,View.weaponButton4);
				View.weaponVBox4.setAlignment(Pos.BOTTOM_CENTER);
				
				View.radioBtnsVBox.setAlignment(Pos.CENTER);
				View.weaponHBox.getChildren().addAll(View.weaponVBox1, View.weaponVBox2, View.weaponVBox3, View.weaponVBox4, View.radioBtnsVBox);
				View.weaponShopPane.setCenter(View.weaponHBox);
				View.root.getChildren().add(weaponShopPane);
			}
		});
		
		VBox passTurnBox = new VBox();
		passTurnBox.setAlignment(Pos.CENTER);
		Image passTurnImage = new Image("game/gui/media/passTurn.png");
		View.passTurnImageView = new ImageView (passTurnImage);
		View.passTurnImageView.setFitHeight(150);
		View.passTurnImageView.setFitWidth(150);
		View.passTurnImageView.setPreserveRatio(true);
		passTurnBox.getChildren().add(View.passTurnImageView);
		
		View.passTurnLabel = new Label("Pass Turn");
		passTurnLabel.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 25));
		passTurnLabel.setTextFill(Color.rgb(255, 255, 204));
		passTurnLabel.setAlignment(Pos.CENTER);
		passTurnBox.getChildren().add(passTurnLabel);
		
		res.getChildren().add(passTurnBox);
		
		View.passTurnImageView.setOnMouseClicked(e -> {
			if (View.battle.isGameOver()) {
				View.easyMode = false;
				View.hardMode = false;
				View.lanes.removeAll(View.lanes);
				View.lanesView = new VBox(10);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("GAME OVER");
				alert.setHeaderText(null);
				alert.setContentText("YOU LOST! ALL LANES ARE DESTROYED\nYOUR SCORE: "+View.battle.getScore());
				alert.initOwner(View.primaryStage);
				alert.showAndWait();		
				View.scene.setRoot(View.startGameRoot);
			}
			else {
				View.battle.passTurn();
				View.updateCurrentInfo();
				View.updateLanes();
				View.updateTitansInLanes();
				View.updateLanesInfo();
			}
		});
		
		View.helpImageClicked = false;
		
		VBox helpBox = new VBox();
		helpBox.setAlignment(Pos.CENTER);
		Image helpImage = new Image("game/gui/media/help.png");
		View.helpImageView = new ImageView (helpImage);
		View.helpImageView.setFitHeight(150);
		View.helpImageView.setFitWidth(150);
		View.helpImageView.setPreserveRatio(true);
		helpBox.getChildren().add(View.helpImageView);	
		
		View.helpLabel = new Label("Help Me");
		helpLabel.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 25));
		helpLabel.setTextFill(Color.rgb(255, 255, 204));
		helpLabel.setAlignment(Pos.CENTER);
		helpBox.getChildren().add(helpLabel);
		
		res.getChildren().add(helpBox);
		
		View.helpImageView.setOnMouseClicked(e -> {
			if(!helpImageClicked) {
				View.helpImageView.setOpacity(0.5);
				helpImageClicked = true;
				View.helpPane = new BorderPane();
				View.helpPane.setMaxSize(900, 200);
				View.helpPane.setBackground(new Background(new BackgroundFill(Color.rgb(251, 223, 207), new CornerRadii(5), new Insets(5))));
				
				View.closeHelp = new Button("Close");
				closeHelp.setOnAction(closeEvent ->{
					if (helpImageClicked) {
						helpImageView.setOpacity(1.0);
						helpImageClicked = false;
						View.root.getChildren().remove(View.helpPane);
					}
				});
				
				HBox closeBox = new HBox(closeHelp);
				closeBox.setAlignment(Pos.CENTER);
				closeHelp.setTextAlignment(TextAlignment.CENTER);
				BorderPane.setMargin(closeBox, new Insets(20));
				closeBox.getStyleClass().add("exit-button-container");
				View.helpPane.setBottom(closeBox);
				
				int currentRes = View.battle.getResourcesGathered();
				int maxlaneDanger = 0;
				int maxlaneNo = 0;
				for (int i = 0; i<View.battle.getOriginalLanes().size(); i++) {
					if(View.battle.getOriginalLanes().get(i).getDangerLevel() > maxlaneDanger) {
						maxlaneDanger = View.battle.getOriginalLanes().get(i).getDangerLevel();
						maxlaneNo = i;
					}
				}
				int laneHealth = View.battle.getOriginalLanes().get(maxlaneNo).getLaneWall().getCurrentHealth();
				
				String command = "";
				Random rand = new Random();
	
				if(laneHealth < 10000) {
				    if(currentRes > 75) {
				        String[] options = {"Buy Wall Trap in Lane " + (maxlaneNo + 1)};
				        command = options[rand.nextInt(options.length)];
				    } else if (currentRes > 25) {
				        String[] options = {"Buy Sniper Cannon in Lane " + (maxlaneNo + 1), "Buy Piercing Cannon in Lane " + (maxlaneNo + 1)};
				        command = options[rand.nextInt(options.length)];
				    } else {
				        command = "Pass Turn";
				    }
				} else {
				    if(currentRes > 100) {
				        String[] options = {"Buy Volley Spread Cannon in Lane " + (maxlaneNo + 1)};
				        command = options[rand.nextInt(options.length)];
				    } else if (currentRes > 25) {
				        String[] options = {"Buy Sniper Cannon in Lane " + (maxlaneNo + 1), "Buy Piercing Cannon in Lane " + (maxlaneNo + 1)};
				        command = options[rand.nextInt(options.length)];
				    } else {
				        command = "Pass Turn";
				    }
				}
				
				Label AISuggestion = new Label(command);
				AISuggestion.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 40));
				AISuggestion.setTextFill(Color.rgb(29, 29, 29));
				AISuggestion.setAlignment(Pos.CENTER);
				helpPane.setCenter(AISuggestion);
				
				View.root.getChildren().add(helpPane);
			}
			
		});
		
		return res;
	}
	
	public static void setUpLanes () {
		int i = 0;
		View.lanesView  = new VBox(10);
		View.isLaneLost = new ArrayList<Boolean>();
		View.lanesInfo = new ArrayList<Label>();
		View.allWeaponsInLane = new ArrayList<ArrayList<Label>>();
		View.lanesTitansPanes = new ArrayList<AnchorPane>();
		for(int j = i; j < View.battle.getOriginalLanes().size(); j++) {
						
			Label healthAndDanger = new Label("Wall Current Health: " + View.battle.getOriginalLanes().get(i).getLaneWall().getCurrentHealth() + "\nDanger Level: " + View.battle.getOriginalLanes().get(i).getDangerLevel());
			healthAndDanger.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 25));
			healthAndDanger.setTextFill(Color.rgb(255, 255, 204));
			healthAndDanger.setAlignment(Pos.CENTER);
			healthAndDanger.setTextAlignment(TextAlignment.CENTER);
			View.lanesInfo.add(healthAndDanger);
			
			VBox allWeapons = new VBox(5);
			allWeapons.setAlignment(Pos.CENTER);
			
			HBox weapon1hbox = new HBox(5);
			HBox weapon2hbox = new HBox(5);
			HBox weapon3hbox = new HBox(5);
			HBox weapon4hbox = new HBox(5);
			
			weapon1hbox.setAlignment(Pos.CENTER);
			weapon2hbox.setAlignment(Pos.CENTER);
			weapon3hbox.setAlignment(Pos.CENTER);
			weapon4hbox.setAlignment(Pos.CENTER);

			Label weapon1no = new Label(" x 0");
			Label weapon2no = new Label(" x 0");
			Label weapon3no = new Label(" x 0");
			Label weapon4no = new Label(" x 0");
			
			weapon1no.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 25));
			weapon2no.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 25));
			weapon3no.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 25));
			weapon4no.setFont(Font.font("Bebas Neue", FontWeight.LIGHT, 25));

			
			ImageView img1 = new ImageView(new Image("game/gui/media/Weapons/PiercingCannon.png"));
			img1.setFitHeight(90);
			img1.setFitWidth(90);
			
			ImageView img2 = new ImageView(new Image("game/gui/media/Weapons/SniperCannon.png"));
			img2.setFitHeight(90);
			img2.setFitWidth(90);
			
			ImageView img3 = new ImageView(new Image("game/gui/media/Weapons/VolleySpreadCannon.png"));
			img3.setFitHeight(90);
			img3.setFitWidth(90);
			
			ImageView img4 = new ImageView(new Image("game/gui/media/Weapons/WallTrap.png"));
			img4.setFitHeight(90);
			img4.setFitWidth(90);
			
			weapon1hbox.getChildren().addAll(img1, weapon1no);
			weapon2hbox.getChildren().addAll(img2, weapon2no);
			weapon3hbox.getChildren().addAll(img3, weapon3no);
			weapon4hbox.getChildren().addAll(img4, weapon4no);
			
			allWeapons.getChildren().addAll(weapon1hbox, weapon2hbox, weapon3hbox, weapon4hbox);
			
			ArrayList<Label> weaponsInLane = new ArrayList<Label>();
			weaponsInLane.add(weapon1no);
			weaponsInLane.add(weapon2no);
			weaponsInLane.add(weapon3no);
			weaponsInLane.add(weapon4no);
			View.allWeaponsInLane.add(weaponsInLane);
			
			View.hbox = new HBox();
			hbox.setSpacing(5);
			hbox.setAlignment(Pos.CENTER);
			
			Label laneLabel = new Label("Lane " + (j+1));
			laneLabel.setRotate(90);
			laneLabel.setAlignment(Pos.CENTER);
			laneLabel.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 30));
			
			Rectangle wallBox = new Rectangle(50, 500);
			wallBox.setFill(Color.GRAY);
			
			AnchorPane laneAnchorPane = new AnchorPane();
			Rectangle laneRectangle = new Rectangle(800, 10);
			
			laneRectangle.setFill(Color.rgb(110, 91, 45));
			
			laneAnchorPane.getChildren().add(laneRectangle);
			laneAnchorPane.prefWidthProperty().bind(laneRectangle.widthProperty());
			laneAnchorPane.setPrefHeight(500);
			laneAnchorPane.setMaxWidth(800);
			laneAnchorPane.setMinWidth(800);
			laneAnchorPane.setMaxHeight(500);
			laneAnchorPane.setMinHeight(500);
			AnchorPane.setBottomAnchor(laneRectangle, 0.0);
			
			HBox lanePlusWall = new HBox();
			lanePlusWall.getChildren().addAll(wallBox, laneAnchorPane);
			lanePlusWall.setAlignment(Pos.BOTTOM_CENTER);
			
			VBox laneDetails = new VBox(5);
			laneDetails.setAlignment(Pos.CENTER);
			laneDetails.getChildren().addAll(allWeapons, healthAndDanger);
			
			hbox.getChildren().addAll(laneDetails, laneLabel, lanePlusWall);
			hbox.setPadding(new Insets(10));
			hbox.setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-border-style: solid; -fx-border-radius: 5;");
			
			View.lanesTitansPanes.add(laneAnchorPane);
			View.lanes.add(hbox);
			View.lanesView.getChildren().add(hbox);
			View.isLaneLost.add(false);
		}
		View.updateTitansInLanes();
		View.lanesScrollPanel.setContent(View.lanesView);
		View.lanesScrollPanel.setFitToWidth(true);
		View.lanesScrollPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		View.lanesScrollPanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        View.lanesScrollPanel.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        View.lanesView.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
		View.gameBorderPane.setCenter(View.lanesScrollPanel);
		BorderPane.setMargin(View.lanesScrollPanel, new Insets(20));
	}
	
	public static void updateLanes() {
		for(int i = 0; i < View.battle.getOriginalLanes().size(); i++) {
			Lane l = View.battle.getOriginalLanes().get(i);
			if(l.isLaneLost() && !View.isLaneLost.get(i)) {
				Color redColor = Color.rgb(255, 0, 0, 0.5);
				Background redBackground = new Background(new BackgroundFill(redColor, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
				View.lanes.get(i).setBackground(redBackground);
				View.lanes.get(i).setAlignment(Pos.CENTER);
				Label LaneLost = new Label ("LANE LOST");
				LaneLost.setRotate(90);
				LaneLost.setAlignment(Pos.CENTER);
				LaneLost.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 30));
				View.lanes.get(i).getChildren().add(LaneLost);
				View.isLaneLost.set(i, true);
			}
		}
	}
	
	public static void updateTitansInLanes() {
		int i = 0;
		for(AnchorPane an: View.lanesTitansPanes) {
			an.getChildren().clear();
			if(!View.battle.getOriginalLanes().get(i).isLaneLost()) {
				Iterator<Titan> iterator = View.battle.getOriginalLanes().get(i).getTitans().iterator();
				while(iterator.hasNext()) {
					Titan t = iterator.next();
					VBox titanV = new VBox(0);
					titanV.setAlignment(Pos.CENTER);
					Label health = new Label(t.getCurrentHealth() + " HP");
					titanV.getChildren().add(health);
					if(t instanceof PureTitan) {
						ImageView pureIV = new ImageView(new Image("game/gui/media/Titans/PureTitan.png"));
						pureIV.setFitHeight(120);
						pureIV.setPreserveRatio(true);
						titanV.getChildren().add(pureIV);
						AnchorPane.setLeftAnchor(titanV, (double)(t.getDistance()*8));
						AnchorPane.setTopAnchor(titanV, (500.0 - (double)(t.getHeightInMeters() * 8)));
						an.getChildren().add(titanV);
					}
					else if (t instanceof AbnormalTitan) {				
						ImageView abnormalIV = new ImageView(new Image("game/gui/media/Titans/AbnormalTitan.png"));
						abnormalIV.setFitHeight(80);
						abnormalIV.setPreserveRatio(true);
						titanV.getChildren().add(abnormalIV);
						AnchorPane.setLeftAnchor(titanV, (double)(t.getDistance()*8));
						AnchorPane.setTopAnchor(titanV, (500.0 - (double)(t.getHeightInMeters() * 8)));
						an.getChildren().add(titanV);
					}
					else if (t instanceof ArmoredTitan) {
						ImageView armoredIV = new ImageView(new Image("game/gui/media/Titans/ArmoredTitan.png"));
						armoredIV.setFitHeight(120);
						armoredIV.setPreserveRatio(true);
						titanV.getChildren().add(armoredIV);
						AnchorPane.setLeftAnchor(titanV, (double)(t.getDistance()*8));
						AnchorPane.setTopAnchor(titanV, (500.0 - (double)(t.getHeightInMeters() * 8)));
						an.getChildren().add(titanV);
					}
					else if (t instanceof ColossalTitan) {
						ImageView colossalIV = new ImageView(new Image("game/gui/media/Titans/ColossalTtian.png"));
						colossalIV.setFitHeight(480);
						colossalIV.setPreserveRatio(true);
						titanV.getChildren().add(colossalIV);
						AnchorPane.setLeftAnchor(titanV, (double)(t.getDistance()*8));
						AnchorPane.setTopAnchor(titanV, (500.0 - (double)(t.getHeightInMeters() * 8)));
						an.getChildren().add(titanV);
					}
				}
			}
			i++;
		}
	}
	
	public static void updateLanesInfo() {
		for(int i = 0; i < View.lanesInfo.size(); i++) {
			View.lanesInfo.get(i).setText("Wall Current Health: " + View.battle.getOriginalLanes().get(i).getLaneWall().getCurrentHealth() + "\nDanger Level: " + View.battle.getOriginalLanes().get(i).getDangerLevel());
		}
	}
	
	public static void main (String [] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent arg0) {
		
	}
}
