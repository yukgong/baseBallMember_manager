package dto;

public class Pitcher extends Human {

	private int win;
	private int lose;
	private double defence; // ����� 0.0 ~ 10.0
	
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
		System.out.println("��ȣ : " + getNumber());
		System.out.println("�̸� : " + getName());
		System.out.println("���� : " + getAge());
		System.out.println("���� : " + getHeight());
		System.out.println("�̱� Ƚ�� : " + getWin());
		System.out.println("�� Ƚ�� : " + getLose());
		System.out.println("����� : " + getDefence());
	}
	
	public String getVelue() {
		String str =
		"����," +
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
