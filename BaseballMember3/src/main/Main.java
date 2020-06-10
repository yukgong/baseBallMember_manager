package main;

import java.util.Scanner;

import dao.*;
import daoInterface.DaoInterface;
import fileClass.*;
import single.SingletonClass;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		(new FileLoad()).process();

		// menu 구성 == front end

		while (true) {
			DaoInterface dao = null; //반복문이 실행 될 때마다 dao인스턴스 리셋.
			
			System.out.println("------메뉴------");
			System.out.println("1. 선수 추가");
			System.out.println("2. 선수 삭제 ");
			System.out.println("3. 선수 정보 검색");
			System.out.println("4. 선수 정보 수정");
			System.out.println("5. 선수 정보 모두 출력");
			System.out.println("6. 선수 저장");
			System.out.println("7. 타자 순위 출력");
			System.out.println("8. 투수 순위 출력");
			System.out.println("9. 종료");
			System.out.println("--------------\n");
			System.out.println("메뉴 번호를 입력해주세요");
			System.out.print(">>>");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1: // 선수 추가
				dao = new InsertClass();
				break;
				
			case 2: // 선수 삭제
				dao = new DeleteClass();
				break;

			case 3: // 선수 검색
				dao = new SelectClass();
				break;

			case 4: // 선수 수정
				dao = new UpdateClass();
				break;

			case 5: // 선수 정보 모두 출력
				dao = new AllPrintClass();
				break;

			case 6: // 선수 정보 저장
				dao = new FileSave();
				break;
				
			case 7: // 타율 순위 출력
				dao = new BatterHitRanking();
				break;
				
			case 8: // 방어율 순위 출력
				dao = new PitcherDefenseRanking();
				break;
				
			case 9: // 종료
				System.out.println("이용해주셔서 감사합니다.");
				System.exit(0); // 프로그램 종료
				break;
			}		
			dao.process(); 
			// 스위치문에서 바로 실행되었던 이전 실습예제와 달리 
			// 스위치문에서는 dao에 객체만 대입해주고 
			// 스위치문이 끝나면 process기능이 실행되도록 구성.
		}
	}
}
