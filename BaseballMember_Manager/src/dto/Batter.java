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
		"타자," +
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
		System.out.println("번호 : " + getNumber());
		System.out.println("이름 : " + getName());
		System.out.println("나이 : " + getAge());
		System.out.println("신장 : " + getHeight());
		System.out.println("공 횟수 : " + getBatcount());
		System.out.println("공을 친 횟수 : " + getHit());
		System.out.println("평균 히트율 : " + getHitAvg());
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
