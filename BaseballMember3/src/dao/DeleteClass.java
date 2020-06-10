package dao;

import java.util.ArrayList;
import java.util.Scanner;

import daoInterface.DaoInterface;
import dto.*;
import single.SingletonClass;

public class DeleteClass implements DaoInterface { // DAO 인터페이스를 따로 만들어 인터페이스 상속을 할 수 있다.
	Scanner scanner = new Scanner(System.in);

	public DeleteClass() {}
	
	@Override
	public void process() {
		SingletonClass sc = SingletonClass.getInstance();

		// 선수 이름 입력받기
		System.out.print("삭제할 선수명 : ");
		String searchName = scanner.next();

		if (searchName.equals("")) {
			System.out.println("이름을 정확히 입력해주십시오.");
		}

		int findIndex = SelectClass.search(searchName);
		if (findIndex == -1) {
			System.out.println("선수 명단에 없는 이름입니다.");
		} else {
			Human h = sc.list.remove(findIndex); // 삭제가 되었는지 확인하기 위해
			System.out.println(h.getName() + "선수를 명단에서 삭제했습니다.");
		}
	}

}
