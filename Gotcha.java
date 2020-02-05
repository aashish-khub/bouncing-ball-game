package class24_hw_game;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Gotcha extends PApplet{
	final static int gameX = 800;
	final static int gameY = 600;
	final int time = 30;
	static double speedFactor;
	int ballCount;
	int playerScore;
	int hitCount;
	int missCount;
	long startTime;
	Ball[] gameBalls;
	boolean gameBegun;
	boolean gameOver;	
	
	public void setup() {
		size(gameX, gameY);
		gameOver = false;
		gameBegun = false;
		playerScore = 0; hitCount = 0; missCount = 0;
		stroke(0); strokeWeight(3);
		textSize(10); textAlign(CENTER);
		ellipseMode(CENTER); 
		smooth();
	}

	public void draw() {
		background(255, 230, 238);
		if (!gameBegun) { //Initializing menu
			fill(0, 64, 128);
			textSize(100);
			text("Gotcha!", gameX/2, 4*gameY/10);
			fill(0, 100, 200); textSize(50);
			text("Select Difficulty Level", gameX/2, 6*gameY/10);
			textSize(25);
			text("Hit 1 for Easy, 2 for Intermediate, and 3 for Expert.\n"
					+ "Hit any other key for BOSS MODE!", gameX/2, 7*gameY/10);			
		}
		else {
			if (System.currentTimeMillis() - startTime < 1000*time) {
				textSize(10);
				for (int i = 0; i < ballCount; i++) {
					fill(gameBalls[i].color[0], gameBalls[i].color[1], gameBalls[i].color[2]);
					ellipse(gameBalls[i].x, gameBalls[i].y, 2*gameBalls[i].radius, 2*gameBalls[i].radius); 
					fill(0);
					text(gameBalls[i].score, gameBalls[i].x-5, gameBalls[i].y+5);
					gameBalls[i].moveBall();
				}
			} else { //when game Over
				gameOver = true;
				fill(225, 0, 0);
				textSize(100);
				text("Game over!", gameX/2, 4*gameY/10);
				String result = "You scored "+playerScore+" points, \n"
						+ "with "+hitCount+" hits and "+missCount+" miss(es).";
				textSize(50);
				text(result, gameX/2, 7*gameY/10);
				textSize(25);
				text("Hit the Spacebar to re-start.", gameX/2, 95*gameY/100);
			}
		}
	}
	
	public void mousePressed() {
		if (!gameOver){
			boolean anyHit = false;
			int x = this.mouseX; int y = this.mouseY;
			for (int i = ballCount-1; i >= 0; i--) {
				if (gameBalls[i].isHit(x, y)){
					//System.out.printf("%d, %d, %d, %d.\n", x, y, gameBall.x, gameBall.y);
					playerScore += gameBalls[i].score;
					hitCount += 1;
					anyHit = true;
					gameBalls[i] = new Ball();
					break; //Ensures that only "top" ball is pressed! 
				}
			} if (!anyHit) {missCount +=1;}
		}
	}
	
	public void keyPressed() {
		if (!gameBegun) {
			///1: Easy; 2: Intermediate; 3: Hard
			switch(key) {
			case '1':
				ballCount = 3; 
				speedFactor = 1/3;
				break;
			case '2':
				ballCount = 5;
				speedFactor = 2/3;
				break;
			case '3':
				ballCount = 7;
				speedFactor = 1;
				break;
			default:
				ballCount = 12;
				speedFactor = 2.0;
			}
			//System.out.println(ballCount);
			//System.out.println(speedFactor);
			gameBegun = true;
			startTime = System.currentTimeMillis();
			gameBalls = new Ball[ballCount];
			for (int i = 0; i < ballCount; i++) {
				gameBalls[i] = new Ball();
			}
		}
		if (gameOver) {
			//System.out.println(key);
			if (key == ' ') {
				setup();
			}
		}
	}
}
