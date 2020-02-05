package class24_hw_game;

public class Ball {
	int x;
	int y;
	int Vx;
	int Vy;
	int level;
	int radius;
	int score;
	int[] color;
	static int[] scores = {10, 25, 50, 100, 200}; 
	static int[] radii = {100, 75, 50, 30, 30}; 
	static int[] speedsMin = {1, 1, 5, 5, 7}; 
	static int[][] colors = {{255,243,170}, {255,208,168}, {255,177,177}, {217,209,255}, {255,11,233}};
	
	public void moveBall() {
		if (x+radius > Gotcha.gameX || x-radius < 0)	this.Vx *= -1;
		if (y+radius > Gotcha.gameY|| y-radius < 0)	this.Vy *= -1;
		this.x += this.Vx; 
		this.y += this.Vy;
	}

//	public Ball(int x, int y, int rad, int vx, int vy) {
//		super();
//		this.x = x;
//		this.y = y;
//		this.radius = rad;
//		Vx = vx;
//		Vy = vy;
//		this.score = 1000/rad;
//		//this.color =
//	}
	public boolean isHit(int xMouse, int yMouse) {
		int xDist = (xMouse - this.x);
		int yDist = (yMouse - this.y);
		double dist = Math.sqrt(Math.pow(xDist, 2.0) + Math.pow(yDist, 2.0));
		//System.out.println(dist);
		//System.out.println(this.radius);
		if (dist < radius) {
			return true;
		} return false;
	}
	
	public Ball() { //No-Arg constructor, generates randomly. 
		super();
		this.x = (int) (Math.random() * (Gotcha.gameX/2.0)+0.25*Gotcha.gameX);
		this.y = (int) (Math.random() * (Gotcha.gameY/2.0)+0.25*Gotcha.gameY);
		this.level = (int) (Math.random()*5);
		//System.out.println((int) (Math.random()*5));
		this.radius = radii[this.level];
		Vx = (int) (Gotcha.speedFactor * Math.random()*5 +(speedsMin[this.level]));
		Vy = (int) (Gotcha.speedFactor * Math.random()*5 + (speedsMin[this.level]));
		this.score = scores[this.level];
		this.color = colors[this.level];
	}
}
