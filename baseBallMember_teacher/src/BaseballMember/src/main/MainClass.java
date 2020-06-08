package BaseballMember.src.main;

import java.util.Scanner;

import BaseballMember.src.dao.MemberDao;
import BaseballMember.src.dao.MemberDao_ArrayList;

public class MainClass {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		MemberDao_ArrayList dao = new MemberDao_ArrayList();
		
		
		// menu 구성 == front end

		while (true) {
			System.out.println("------메뉴------");
			System.out.println("1. 선수 추가");
			System.out.println("2. 선수 삭제 ");
			System.out.println("3. 선수 정보 검색");
			System.out.println("4. 선수 정보 수정");
			System.out.println("5. 선수 정보 모두 출력");
			System.out.println("6. 선수 저장");
			System.out.println("7. 타율 순위 출력");
			System.out.println("8. 방어율 순위 출력");
			System.out.println("9. 종료");
			System.out.println("--------------\n");
			System.out.println("메뉴 번호를 입력해주세요");
			System.out.print(">>>");
			int choice = scanner.nextInt();
			
			switch (choice) {
			case 1: // 선수 추가
				dao.insert();
				break;

			case 2: // 선수 삭제
				dao.delete();
				break;

			case 3: // 선수 검색
				dao.select();
				break;
				
			case 4: // 선수 수정
				dao.update();
				break;
				
			case 5: // 선수 정보 모두 출력
				dao.allPrint();
				break;
				
			case 6: // 선수 정보 저장
				dao.saveData();
				break;
				
			case 7: // 타율 순위 출력
				dao.batterHitRanking();
				break;
//				
//			case 8: // 방어율 순위 출력
//				dao.defencePrint();
//				break;
				
			case 9: // 종료
				System.out.println("이용해주셔서 감사합니다.");
				System.exit(0); //프로그램 종료
				break;
			}

		}
	}
}
