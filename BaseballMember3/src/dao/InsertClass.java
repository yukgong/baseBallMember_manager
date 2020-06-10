package dao;

import java.util.ArrayList;
import java.util.Scanner;

import daoInterface.DaoInterface;
import dto.*;
import single.SingletonClass;

public class InsertClass implements DaoInterface {
	Scanner scanner = new Scanner(System.in);
	
	public InsertClass() {
		SingletonClass sc = SingletonClass.getInstance();
		
		sc.memberNumber = sc.list.get(sc.list.size() - 1).getNumber(); // 제일 마지막 번호
		if (sc.memberNumber >= 2000) {
			sc.memberNumber -= 1000; // 기본값은 1000번대여야 하므로, 타자면 1000을 빼줌// 빼준값이 가장 마지막 선수의 번호가 되므로 다음 선수 번호를 지정해주려면 +1을 해준다.
		}
			sc.memberNumber += 1;
	}

	@Override
	public void process() {
		SingletonClass sc = SingletonClass.getInstance(); // 호출한다고 무거워지는게 아니다.
		
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
			h = new Pitcher(sc.memberNumber, name, age, height, win, lose, defence);
		}
		// 타자
		else if (postion == 2) {
			Batter bat = new Batter(); // 인스턴스를 생성해서 해당 인스턴스에 직접 넣어주는 방법이 있다.

			// 선수 기본 정보

			int number = sc.memberNumber + 1000;
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
		
		sc.list.add(h); // Pitcher or Batter의 변수 값을 배열에 대입해준다.
		sc.memberNumber++;
	}
}
