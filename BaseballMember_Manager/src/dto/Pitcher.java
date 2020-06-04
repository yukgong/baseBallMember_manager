package dto;

public class Pitcher extends Human {

	private int win;
	private int lose;
	private double defence; // 방어율 0.0 ~ 10.0
	
	public Pitcher(int win, int lose, double defence) {
		super();
		this.win = win;
		this.lose = lose;
		this.defence = defence;
	}
	
	public Pitcher(int number, String name, int age, double height, int win, int lose, double defence) {
		super(number, name, age, height);
		this.win = win;
		this.lose = lose;
		this.defence = defence;
	}
	
	public void print() {
		System.out.println("번호 : " + getNumber());
		System.out.println("이름 : " + getName());
		System.out.println("나이 : " + getAge());
		System.out.println("신장 : " + getHeight());
		System.out.println("이긴 횟수 : " + getWin());
		System.out.println("진 횟수 : " + getLose());
		System.out.println("방어율 : " + getDefence());
	}
	
	public String getVelue() {
		String str =
		"투수," +
		getNumber() + "," +
		getName() + "," +
		getAge() + "," +
		getHeight() + "," +
		getWin() + "," +
		getLose() + "," +
		getDefence();
		
		return str;
	}
	
	//getter Setter-----------------------------

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public double getDefence() {
		return defence;
	}

	public void setDefence(double defence) {
		this.defence = defence;
	}
	
	
}
