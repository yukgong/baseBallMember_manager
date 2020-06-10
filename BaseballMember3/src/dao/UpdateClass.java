package dao;

import java.util.Scanner;

import daoInterface.DaoInterface;
import dto.*;
import single.SingletonClass;

public class UpdateClass implements DaoInterface {
	Scanner scanner = new Scanner(System.in);

	public UpdateClass() {}
	
	@Override
	public void process() {
		SingletonClass sc = SingletonClass.getInstance();

		while (true) {
			System.out.println("수정할 선수명:");
			String searchName = scanner.next();

			if (searchName.equals("")) {
				System.out.println("이름을 정확히 입력해주십시오.");
				continue;
			}

			int findIndex = SelectClass.search(searchName);
			Human human = sc.list.get(findIndex);

			if (findIndex == -1) {
				System.out.println("선수 명단에 없는 이름입니다.");
				return;
			}
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
				
				return;

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
				return;
			}
		break;
		}
	}
}
