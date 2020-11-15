
public class Ponto {
	final private double x;
	final private double y;

	public Ponto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	private static double area(Ponto p0, Ponto p1, Ponto p2) {
		return (p1.getX() - p0.getX()) * (p2.getY() - p0.getY()) 
				- (p2.getX() - p0.getX()) * (p1.getY() - p0.getY());
	}

	/**
	 * Calcula a distância entre o ponto atual e o ponto p0
	 * @param p0
	 * @return O valor da distância
	 */
	public double distancia(Ponto p0) {
		return Math.sqrt((this.getX() - p0.getX()) * (this.getX() - p0.getX())
				+ (this.getY() - p0.getY()) * (this.getY() - p0.getY()));
	}
	
	public static boolean ehCurvaEsquerda(Ponto p0, Ponto p1, Ponto p2){
		return (area(p0,p1,p2) > 0);
	}
	
	public static boolean ehCurvaDireita(Ponto p0, Ponto p1, Ponto p2){
		return (area(p0,p1,p2) < 0);
	}
	
	public String toString(){
		int x = (int)this.getX();
		int y = (int) this.getY();
		return ( x+" "+y);
	}	
}
