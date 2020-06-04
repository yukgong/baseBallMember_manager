package main;

import java.util.Scanner;

import dao.MemberDao;

public class MainClass {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		// 메뉴구성 == front end

		// 불러오기는 자동으로
		// 저장하기는 메뉴로

		// 초기화
		int choice;
		MemberDao mDAO = new MemberDao();
		mDAO.init();
		out: while (true) {
			
			// 선택할 메뉴 보여주기
			System.out.println("------메뉴------");
			System.out.println("1. 선수 추가");
			System.out.println("2. 선수 삭제 ");
			System.out.println("3. 선수 정보 검색");
			System.out.println("4. 선수 정보 수정");
			System.out.println("5. 선수 정보 모두 출력");
			System.out.println("6. 선수 저장");
			System.out.println("7. 종료");
			System.out.println("--------------\n");
			System.out.println("메뉴 번호를 입력해주세요");
			System.out.print(">>>");
			choice = scanner.nextInt();
			
			// 잘못된 번호를 선택했을 때
			if (choice <= 0 || choice > 7) {
				System.out.println("잘못입력했습니다. 다시 입력해주세요.");
				continue;
			}

			switch (choice) {
			case 1: // 선수 추가
				mDAO.insert();
				break;

			case 2: // 선수 삭제
				mDAO.delete();
				break;

			case 3: // 선수 검색
				mDAO.select();
				break;
				
			case 4: // 선수 수정
				mDAO.update();
				break;
				
			case 5: // 선수 정보 모두 출력
				mDAO.allPrint();
				break;
				
			case 6: // 선수 정보 모두 출력
				mDAO.fileSave();
				break;
				
			case 7: // 종료
				System.out.println("이용해주셔서 감사합니다.");
				break out;
			}
		}
	}
}
