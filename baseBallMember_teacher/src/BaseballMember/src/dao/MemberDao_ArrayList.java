package BaseballMember.src.dao;

import java.util.ArrayList;
import java.util.Scanner;
import BaseballMember.src.dto.Pitcher;
import fileIO.FileDataClass;
import BaseballMember.src.dto.Batter;
import BaseballMember.src.dto.Human;

public class MemberDao_ArrayList {
	// 멤버 변수
	Scanner scanner = new Scanner(System.in);
	private int memberNumber;
	private ArrayList<Human> list = new ArrayList<Human>();
	FileDataClass fdc;

	public MemberDao_ArrayList() throws Exception {
		fdc = new FileDataClass("baseBall");
		fdc.createFile();

		loadData();
		memberNumber = 1000;

		// list에서 제일 마지막 선수의 number 취득
		memberNumber = list.get(list.size() - 1).getNumber(); // 제일 마지막 번호
		if (memberNumber >= 2000) {
			memberNumber -= 1000; // 기본값은 1000번대여야 하므로, 타자면 1000을 빼줌
			memberNumber += 1; // 빼준값이 가장 마지막 선수의 번호가 되므로 다음 선수 번호를 지정해주려면 +1을 해준다.
		}
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

		list.add(h); // Pitcher or Batter의 변수 값을 배열에 대입해준다.
		memberNumber++;
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
			System.out.println("선수 명단에 없는 이름입니다.");
		} else {
			list.remove(findIndex);
			System.out.println(searchName + "선수를 명단에서 삭제했습니다.");
		}
	}

	public void select() {
		System.out.println("검색할 선수명:");
		String searchName = scanner.next();

		if (searchName.equals("")) {
			System.out.println("이름을 정확히 입력해주십시오.");
		}

		int findIndex = search(searchName);
		if (findIndex == -1) {
			System.out.println("선수 명단에 없는 이름입니다.");
		} else {
			Human human = list.get(findIndex);

			System.out.println("번호 : " + human.getNumber());
			System.out.println("이름 : " + human.getName());
			System.out.println("나이 : " + human.getAge());
			System.out.println("신장 : " + human.getHeight());
			if (list.get(findIndex) instanceof Pitcher) {
				System.out.println("승 : " + ((Pitcher) human).getWin());
				System.out.println("패 : " + ((Pitcher) human).getLose());
				System.out.println("방어율 : " + ((Pitcher) human).getDefence());
			} else if (list.get(findIndex) instanceof Batter) {
				System.out.println("타수 : " + ((Batter) human).getBatcount());
				System.out.println("안타수 : " + ((Batter) human).getHit());
				System.out.println("타율 : " + ((Batter) human).getHitAvg());
			}
		}
	}

	public void update() {
		System.out.println("수정할 선수명:");
		String searchName = scanner.next();

		if (searchName.equals("")) {
			System.out.println("이름을 정확히 입력해주십시오.");
		}

		int findIndex = search(searchName);
		Human human = list.get(findIndex);

		if (findIndex == -1) {
			System.out.println("선수 명단에 없는 이름입니다.");
		} else {
			if (human instanceof Pitcher) {
				System.out.println("승 : ");
				int win = scanner.nextInt();

				System.out.println("패 : ");
				int lose = scanner.nextInt();

				System.out.println("방어율 : ");
				double defence = scanner.nextDouble();

				Pitcher pit = (Pitcher) human;
				pit.setWin(win);
				pit.setLose(lose);
				pit.setDefence(defence);

			} else if (human instanceof Batter) {
				System.out.println("타수 : ");
				int batcount = scanner.nextInt();

				System.out.println("안타수 : ");
				int hit = scanner.nextInt();

				System.out.println("타율 : ");
				int hitAvg = scanner.nextInt();

				Batter bat = (Batter) human;
				bat.setBatcount(batcount);
				bat.setHit(hit);
				bat.setHitAvg(hitAvg);
			}
		}
	}

	public void allPrint() {
		for (Human a : list) {
			System.out.println(a.toString());
		}
	}

	// File IO--------------------------------

	public void saveData() {
		// 배열 할당
		String datas[] = new String[list.size()];

		// datas배열에 human데이터 집어넣기
		for (int i = 0; i < datas.length; i++) {
			Human human = list.get(i);
			datas[i] = human.toString();
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

		// datas 배열에 있는 값을 human배열에 대입한다.
		for (int i = 0; i < datas.length; i++) {
			// datas 배열의 내용을 "-"토큰 단위로 잘라 splitData 배열에 넣어준다.
			String splitData[] = datas[i].split("-");

			// 선수 번호로 투수인지 타자인지 구분하기 위해 splitData의 0번째 값을 확인한다.
			int title = Integer.parseInt(splitData[0]);

			// 0번째 값이 2000미만이면 투수이므로 Pitcher 객체를 생성해
			// 생성자의 인자값으로 splitData의 배열 값을 대입해준다.
			// 생성한 Pitcher 객체를 인스턴스화해 human 배열에 대입한다.
			Human human = null;
			if (title < 2000) { // 투수
				list.add(new Pitcher(Integer.parseInt(splitData[0]), splitData[1], Integer.parseInt(splitData[2]),
						Double.parseDouble(splitData[3]), Integer.parseInt(splitData[4]),
						Integer.parseInt(splitData[5]), Double.parseDouble(splitData[6])));
			} else {
				list.add(new Batter(Integer.parseInt(splitData[0]), splitData[1], Integer.parseInt(splitData[2]),
						Double.parseDouble(splitData[3]), Integer.parseInt(splitData[4]),
						Integer.parseInt(splitData[5]), Double.parseDouble(splitData[6])));
			}
		} // for end
	}

	public void batterHitRanking() {
		// 타자만을 수집한 배열
		ArrayList<Human> sortList = positionSelect(2);

		Human obj = null; // Swap을 위해 temp 대신 빈 객체하나를 준비한다.
		for (int i = 0; i < sortList.size() - 1; i++) {
			for (int j = i + 1; j < sortList.size(); j++) {
				Batter b1 = (Batter) sortList.get(i);
				Batter b2 = (Batter) sortList.get(j);
				if (b1.getHitAvg() < b2.getHitAvg()) {
					obj = sortList.get(i);
					sortList.set(i, sortList.get(j));
					sortList.set(j, obj);
				}
			}
		}

		for (int i = 0; i < sortList.size(); i++) {
			Human human = sortList.get(i);
			System.out.println(i + " : " + human);
		}
	}

	public void pitcherDefenseRanking() {
		// 투수만을 수집한 배열
		ArrayList<Human> sortList = positionSelect(1);

		// 오름차순 정렬
		Human obj = null;
		for (int i = 0; i < sortList.size() - 1; i++) {
			for (int j = i + 1; j < sortList.size(); j++) {
				Pitcher p1 = (Pitcher) sortList.get(i);
				Pitcher p2 = (Pitcher) sortList.get(j);
				if(p1.getDefence() > p2.getDefence()) {
					obj = sortList.get(i);
					sortList.set(i, sortList.get(j));
					sortList.set(j ,obj);
				}
			}
		}

		// 출력
	}

	// Method for method----------------------

	public int search(String name) {
		int findIndex = -1;
		for (int i = 0; i < list.size(); i++) {
			Human h = list.get(i);
			if (name.equals(h.getName())) {
				findIndex = i;
			}
		}
		return findIndex;
	}

	public ArrayList<Human> positionSelect(int num) {
		ArrayList<Human> reList = new ArrayList<Human>();

		for (int i = 0; i < list.size(); i++) {
			Human human = list.get(i);
			if (num == 1) { // pitcher
				if (human.getNumber() < 2000) {
					reList.add(human);
				}

			} else if (num == 2) { // batter
				if (human.getNumber() >= 2000) {
					reList.add(human);
				}
			}
		}

		return reList;
	}

}
