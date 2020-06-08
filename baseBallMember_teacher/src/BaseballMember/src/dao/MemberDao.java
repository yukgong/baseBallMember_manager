package BaseballMember.src.dao;

import java.util.ArrayList;
import java.util.Scanner;

import BaseballMember.src.dto.Pitcher;
import fileIO.FileDataClass;
import BaseballMember.src.dto.Batter;
import BaseballMember.src.dto.Human;

// Data Access Object	= model == back end
public class MemberDao {
	// 멤버 변수
	Scanner scanner = new Scanner(System.in);
	private Human human[]; // 부모 객체인 휴먼으로 자식 객체를 관리하기 위해 전역 변수 설정
	private int memberNumber;
	private int count;
	private ArrayList<Human> list = new ArrayList<Human>();
	FileDataClass fdc;

	// 생성자
	public MemberDao() throws Exception {
		// file 새로 만들기
		fdc = new FileDataClass("baseBall");
		fdc.createFile();
		
		
		human = new Human[20]; // 변수 20개 생성 -> 이건 배열로 변수를 잡은 것
		// human = new Human(); // 객체 생성 -> 이건 객체로 잡은 것. 객체 != 변수는 다르다!

		count = 0;
		loadData();
		memberNumber = 1000 + count;
	}

	public void insert() {

		// 투수/타자?
		System.out.println("투수(1)/타자(2) = ");
		int postion = scanner.nextInt();

		// Human 데이터 입력
		System.out.print("이름 :");
		String name = scanner.next();

		System.out.print("나이 :");
		int age = scanner.nextInt();

		System.out.print("신장 :");
		double height = scanner.nextDouble();

		// 투수
		Human h = null; // h의 유효범위가 if 안에서만 적용되면 안됨
		if (postion == 1) {
			// win
			System.out.print("승 :");
			int win = scanner.nextInt();

			// lose
			System.out.print("패 :");
			int lose = scanner.nextInt();

			// defense
			System.out.print("방어율 :");
			double defence = scanner.nextDouble();

			// Pitcher의 생성자내의 변수에 값을 대입해준다.
			h = new Pitcher(memberNumber, name, age, height, win, lose, defence);
		}
		// 타자
		else if (postion == 2) {
			Batter bat = new Batter(); // 인스턴스를 생성해서 해당 인스턴스에 직접 넣어주는 방법이 있다.

			// 선수 기본 정보

			int number = memberNumber + 1000;
			bat.setNumber(number);
			bat.setName(name);
			bat.setAge(age);
			bat.setHeight(height);

			// 타수
			System.out.print("타수 : ");
			int batcount = scanner.nextInt();
			bat.setBatcount(batcount);

			// 안타수
			System.out.print("안타수 : ");
			int hit = scanner.nextInt();
			bat.setHit(hit);

			// 타율
			System.out.print("타율 : ");
			double hitAvg = scanner.nextDouble();
			bat.setHitAvg(hitAvg);

			h = bat;
		}

		human[count] = h; // Pitcher or Batter의 변수 값을 배열에 대입해준다.
		memberNumber++;
		count++;
	}

	public void delete() {
		// 선수 이름 입력받기
		System.out.print("삭제할 선수명 : ");
		String searchName = scanner.next();

		if (searchName.equals("")) {
			System.out.println("이름을 정확히 입력해주십시오.");
		}
		int findIndex = search(searchName);
		if (findIndex == -1) {
			System.out.println("선수 명단에 없습니다. 삭제할 수 없습니다.");
			return;
		}

		// 삭제 : 배열값을 비우는 다양한 방법이 있다.
		//
		// 방법 1 : humanArr[i] = null;

		// 방법 2 : 클래스에 직접 메소드 만들어주기;
		if (human[findIndex] instanceof Pitcher) {
			Pitcher p = (Pitcher) human[findIndex];
			p.allClear();
		}

		// 방법 3 : 배열의 값을 직접 초기화하기
		else if (human[findIndex] instanceof Batter) {
			Batter b = (Batter) human[findIndex];
			b.setNumber(0);
			b.setName("");
			b.setAge(0);
			b.setHeight(0);
			b.setBatcount(0);
			b.setHit(0);
			b.setHitAvg(0);
		}
	}

	public void select() {
		System.out.println("검색할 선수명:");
		String searchName = scanner.next();

		int findIndex = search(searchName);
		if (findIndex == -1) {
			System.out.println("선수 명단에 없습니다.");
		} else {
			System.out.println("번호 : " + human[findIndex].getNumber());
			System.out.println("이름 : " + human[findIndex].getName());
			System.out.println("나이 : " + human[findIndex].getAge());
			System.out.println("신장 : " + human[findIndex].getHeight());
			if (human[findIndex] instanceof Pitcher) {
				System.out.println("승 : " + ((Pitcher) human[findIndex]).getWin());
				System.out.println("패 : " + ((Pitcher) human[findIndex]).getLose());
				System.out.println("방어율 : " + ((Pitcher) human[findIndex]).getDefence());
			} else if (human[findIndex] instanceof Batter) {
				System.out.println("타수 : " + ((Batter) human[findIndex]).getBatcount());
				System.out.println("안타수 : " + ((Batter) human[findIndex]).getHit());
				System.out.println("타율 : " + ((Batter) human[findIndex]).getHitAvg());
			}
		}
	}

	public void update() {
		System.out.println("수정할 선수명:");
		String searchName = scanner.next();

		int findIndex = search(searchName);
		if (findIndex == -1) {
			System.out.println("선수 명단에 없습니다.");
		} else {
			if (human[findIndex] instanceof Pitcher) {
				System.out.println("승 : ");
				int win = scanner.nextInt();

				System.out.println("패 : ");
				int lose = scanner.nextInt();

				System.out.println("방어율 : ");
				double defence = scanner.nextDouble();

				Pitcher pit = (Pitcher) human[findIndex];
				pit.setWin(win);
				pit.setLose(lose);
				pit.setDefence(defence);

			} else if (human[findIndex] instanceof Batter) {
				System.out.println("타수 : ");
				int batcount = scanner.nextInt();

				System.out.println("안타수 : ");
				int hit = scanner.nextInt();

				System.out.println("타율 : ");
				int hitAvg = scanner.nextInt();

				Batter bat = (Batter) human[findIndex];
				bat.setBatcount(batcount);
				bat.setHit(hit);
				bat.setHitAvg(hitAvg);

			}
		}
	}

	public void allprint() {
		for (int i = 0; i < human.length; i++) {
			if (human[i] != null && !human[i].getName().equals("")) {
				System.out.println(human[i].toString());
			}
		}
	}

	// 메소드는 하나의 역할만 담당한다.
	// 코드가 너무 짧던가 길던가는 중요하지 않다(ex)getter, setter)
	public int search(String searchName) {

		int index = -1;
		// 입력 받은 이름이 배열에 있는지 확인하고 인덱스값 찾기
		for (int i = 0; i < human.length; i++) {
			if (human[i] != null) {
				if (searchName.equals(human[i].getName())) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

	public void saveData() {

		// 배열 안에 선수가 몇명 있는지 확인하기
		// 1001-홍길동-24-178.1-10-3-0.12
		int len = 0;
		for (int i = 0; i < human.length; i++) {
			if (human[i] != null) {
				len++;
			}
		}

		// 배열 할당
		String datas[] = new String[len];

		// datas배열에 human데이터 집어넣기
		for (int i = 0; i < datas.length; i++) {
			datas[i] = human[i].toString();
		}

		// 배열에 복사한 내용 파일에 저장하기
		fdc.saveData(datas);
	}

	public void loadData() throws Exception {
		// loadData 메소드는 파일에 저장된 내용을 배열에 담아두는 역할을 한다.
		// loadData 메소드의 값을 리턴받아 datas 배열에 대입한다.
		String datas[] = fdc.loadData();
		/*
		 * datas : Pitcher, Batter -> Human[] 객체 생성 값을 저장
		 */
		
		//datas배열에 몇명이 있는지 확인하기
		for (int i = 0; i < datas.length; i++) {
			if(datas[i] != null) {
				count++;
			}
		}
		System.out.println("선수들은 총 : "+ count + "명 입니다.");

		// datas 배열에 있는 값을 human배열에 대입한다.
		for (int i = 0; i < datas.length; i++) {
			// datas 배열의 내용을 "-"토큰 단위로 잘라 splitData 배열에 넣어준다.
			String splitData[] = datas[i].split("-");

			// 선수 번호로 투수인지 타자인지 구분하기 위해 splitData의 0번째 값을 확인한다.
			int title = Integer.parseInt(splitData[0]);

			// 0번째 값이 2000미만이면 투수이므로 Pitcher 객체를 생성해
			// 생성자의 인자값으로 splitData의 배열 값을 대입해준다.
			// 생성한 Pitcher 객체를 인스턴스화해 human 배열에 대입한다.
			if (title < 2000) { // 투수
				human[i] = new Pitcher(Integer.parseInt(splitData[0]), splitData[1], Integer.parseInt(splitData[2]),
						Double.parseDouble(splitData[3]), Integer.parseInt(splitData[4]),
						Integer.parseInt(splitData[5]), Double.parseDouble(splitData[6]));
			} else {
				human[i] = new Batter(Integer.parseInt(splitData[0]), splitData[1], Integer.parseInt(splitData[2]),
						Double.parseDouble(splitData[3]), Integer.parseInt(splitData[4]),
						Integer.parseInt(splitData[5]), Double.parseDouble(splitData[6]));
			}
		}
	}

	// 타율 순위 출력 1 ~ n
	public void hitAvgPrint() {
		// 원본이 변하면안되므로 새로운 배열에 human배열에서 타자의 데이터만 대입한다.
		Human datas[] = new Human[20];
		for (int i = 0; i < human.length; i++) {
			if (human[i] instanceof Batter) {
				datas[i] = human[i];
			}
		}

		// 타자의 타율을 비교해 더 큰 데이터를 가진 사람이 앞쪽으로 오도록 Swap을 사용해 바꿔준다.
		Batter temp = new Batter();
		for (int i = 0; i < datas.length - 1; i++) {
			for (int j = i + 1; j < datas.length; j++) {
				if (!(datas[i] == null || datas[j] == null)) {
					if (((Batter) datas[i]).getHitAvg() > ((Batter) datas[j]).getHitAvg()) {
						temp = ((Batter) datas[i]);
						datas[i] = datas[j];
						datas[j] = temp;
					}
				}
			}
		}

		// datas 배열 출력하기
		int num = 1;
		for (int i = 0; i < datas.length; i++) {
			if (datas[i] != null) {
				System.out.println(num + "위 : " + datas[i].getName() + " 타율 : " + ((Batter) datas[i]).getHitAvg());
			num++;
			}
		}
	}

	// 방어율 순위 출력 1 ~ n
	public void defencePrint() {
		// 원본이 변하면안되므로 새로운 배열에 human배열에서 타자의 데이터만 대입한다.
		Human datas[] = new Human[20];
		for (int i = 0; i < human.length; i++) {
			if (human[i] instanceof Pitcher) {
				datas[i] = human[i];
			}
		}

		// 타자의 타율을 비교해 더 큰 데이터를 가진 사람이 앞쪽으로 오도록 Swap을 사용해 바꿔준다.
		Pitcher temp = new Pitcher();
		for (int i = 0; i < datas.length - 1; i++) {
			for (int j = i + 1; j < datas.length; j++) {

				if (!(datas[i] == null || datas[j] == null)) {
					if (((Pitcher) datas[i]).getDefence() < ((Pitcher) datas[j]).getDefence()) {
						temp = ((Pitcher) datas[i]);
						datas[i] = datas[j];
						datas[j] = temp;
					}
				}
			}
		}

		// datas 배열 출력하기
		int num = 1;
		for (int i = 0; i < datas.length; i++) {
			if (datas[i] != null) {
				System.out.println(num + "위 : " + datas[i].getName() + " 방어율 : " + ((Pitcher) datas[i]).getDefence());
				num++;
			}
		}
	}
}
