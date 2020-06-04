package dto;

public class Batter extends Human {
	private int batcount;
	private int hit;
	private double hitAvg; // 0.0 ~ 10.0
	
	public Batter() {
	}
	
	public Batter(int batcount, int hit, double hitAvg) {
		super();
		this.batcount = batcount;
		this.hit = hit;
		this.hitAvg = hitAvg;
	}


	public Batter(int number, String name, int age, double height, int batcount, int hit, double hitAvg) {
		super(number, name, age, height);
		this.batcount = batcount;
		this.hit = hit;
		this.hitAvg = hitAvg;
	}
	public String getVelue() {
		String str =
		"Ÿ��," +
		getNumber() + "," +
		getName() + "," +
		getAge() + "," +
		getHeight() + "," +
		getBatcount() + "," +
		getHit() + "," +
		getHitAvg();
		
		return str;
	}
	
	public void print() {
		System.out.println("��ȣ : " + getNumber());
		System.out.println("�̸� : " + getName());
		System.out.println("���� : " + getAge());
		System.out.println("���� : " + getHeight());
		System.out.println("�� Ƚ�� : " + getBatcount());
		System.out.println("���� ģ Ƚ�� : " + getHit());
		System.out.println("��� ��Ʈ�� : " + getHitAvg());
	}
	
	//getter Setter-----------------------------

	public int getBatcount() {
		return batcount;
	}

	public void setBatcount(int batcount) {
		this.batcount = batcount;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public double getHitAvg() {
		return hitAvg;
	}

	public void setHitAvg(double hitAvg) {
		this.hitAvg = hitAvg;
	}
	
	
}
