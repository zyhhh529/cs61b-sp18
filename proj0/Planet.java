public class Planet{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double x_dis = this.xxPos - p.xxPos;
		double y_dis = this.yyPos - p.yyPos;
		return Math.sqrt(x_dis * x_dis + y_dis * y_dis);
	}

	public double calcForceExertedBy(Planet p){
		double dis = this.calcDistance(p);
		double f = G * this.mass * p.mass / (dis * dis);
		return f;	
	}

	public double calcForceExertedByX(Planet p){
		double f = calcForceExertedBy(p);
		double f_dx = f / this.calcDistance(p) * (p.xxPos - this.xxPos);
		return f_dx;
	}

	public double calcForceExertedByY(Planet p){
		double f = calcForceExertedBy(p);
		double f_dy = f / this.calcDistance(p) * (p.yyPos - this.yyPos);
		return f_dy;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double f_dx = 0;
		for (Planet planet : allPlanets ) {
			if(planet.equals(this)) continue;
			f_dx += this.calcForceExertedByX(planet);
		}
		return f_dx;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double f_dy = 0;
		for (Planet planet : allPlanets ) {
			if(planet.equals(this)) continue;
			f_dy += this.calcForceExertedByY(planet);
		}
		return f_dy;
	}

	public void update(double dt, double fx, double fy){
		double acc_x = fx / this.mass;
		double acc_y = fy / this.mass;
		this.xxVel += acc_x * dt;
		this.yyVel += acc_y * dt;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw(){
		//StdDraw.setScale(-100, 100);
		StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName); 
		StdDraw.show();
		//StdDraw.pause(2000);
	}

}