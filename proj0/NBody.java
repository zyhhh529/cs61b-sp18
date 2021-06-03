public class NBody{
	public static void main(String[] args) {

		// read in all the data
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		// draw BG picture
		String BGpic = "images/starfield.jpg";
		StdDraw.setScale(radius*(-1), radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, BGpic);

		StdDraw.show();
		//StdDraw.pause(2000);

		for (Planet p : planets ) {
			p.draw();
		}

		StdDraw.enableDoubleBuffering();

		int t = 0;
		while (t<T) {
			int plannum = planets.length;
			double[] xForces = new double[plannum];
			double[] yForces = new double[plannum];
			int a = 0;
			for (Planet p : planets ) {
				xForces[a] = p.calcNetForceExertedByX(planets);
				yForces[a++] = p.calcNetForceExertedByY(planets);
			}

			for (int i=0; i<plannum; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, BGpic);
			for (Planet p : planets ) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			t += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	 	  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                 	  	  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}

	public static double readRadius(String add){
		In in = new In(add);
		int t1 = in.readInt();
		double radius = in.readDouble();
		return radius; 
	}

	public static Planet[] readPlanets(String add){
		In in = new In(add);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] planArr = new Planet[num];

		for (int i=num; i>0 ; i-- ) { //x y coordinate x y initial velocity mass pic name 
			double x_cor = in.readDouble();
			double y_cor = in.readDouble();
			double x_vel = in.readDouble();
			double y_vel = in.readDouble();
			double plan_mass = in.readDouble();
			String pic_name = "images/" + in.readString();

			Planet p = new Planet(x_cor, y_cor, x_vel, y_vel, plan_mass, pic_name);
			planArr[num-i] = p;
		}

		return planArr;
	}
}