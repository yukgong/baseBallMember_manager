package dao;

import java.util.ArrayList;
import java.util.Scanner;

import daoInterface.DaoInterface;
import dto.*;
import single.SingletonClass;

public class SelectClass implements DaoInterface {
	Scanner scanner = new Scanner(System.in);

	public SelectClass() {}
	
	//검색 기능을 update나 delete에서도 간편하게 사용하기 위해 static으로 설정
	static public int search(String name) {
		SingletonClass sc = SingletonClass.getInstance();

		int findIndex = -1;
		for (int i = 0; i < sc.list.size(); i++) {
			Human h = sc.list.get(i);
			if (name.equals(h.getName())) {
				findIndex = i;
				break;
			}
		}
		return findIndex;
	}

	@Override
	public void process() {
		SingletonClass sc = SingletonClass.getInstance();

		System.out.println("검색할 선수명:");
		String searchName = scanner.next();

		if (searchName.equals("")) {
			System.out.println("이름을 정확히 입력해주십시오.");
		}

		int findIndex = search(searchName);
		if (findIndex == -1) {
			System.out.println("선수 명단에 없는 이름입니다.");
		} else {
			Human human = sc.list.get(findIndex);

			System.out.println("번호 : " + human.getNumber());
			System.out.println("이름 : " + human.getName());
			System.out.println("나이 : " + human.getAge());
			System.out.println("신장 : " + human.getHeight());
			if (human instanceof Pitcher) {
				System.out.println("승 : " + ((Pitcher) human).getWin());
				System.out.println("패 : " + ((Pitcher) human).getLose());
				System.out.println("방어율 : " + ((Pitcher) human).getDefence());
			} else if (human instanceof Batter) {
				System.out.println("타수 : " + ((Batter) human).getBatcount());
				System.out.println("안타수 : " + ((Batter) human).getHit());
				System.out.println("타율 : " + ((Batter) human).getHitAvg());
			}
		}
	}
}
