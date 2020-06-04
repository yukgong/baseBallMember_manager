package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import dto.*;

//DAO (Data Access Object)모델 == Back end

public class MemberDao {
	// 멤버 변수
	Scanner scanner = new Scanner(System.in);
	int count;

	// 배열
	Human humanArr[] = new Human[20];
	
	// 생성자
	public MemberDao() {
	}

	public void init() throws Exception {
		for (int i = 0; i < humanArr.length; i++) {
			humanArr[i] = null;
		}
		System.out.println("Baseball Member 관리 프로그램이 연결되었습니다.");
		fileLoad();
		System.out.println(count);
	}

	public void insert() {

		while (count < humanArr.length) {
			// 투수/타자 ?
			System.out.print((count + 1) + "번째 선수의 포지션 선택 (1. 투수, 2. 타자) = ");
			int choice = scanner.nextInt();

			if (!(choice == 1 || choice == 2)) {
				System.out.println("잘못입력했습니다. 다시 입력해주세요.");
				continue;
			}

			System.out.print("번호 :");
			int number = scanner.nextInt();

			// 이름 입력하기
			String name;
			while (true) {
				boolean b = true;
				System.out.print("이름 :");
				name = scanner.next();
				for (int i = 0; i < humanArr.length; i++) {
					if (humanArr[i] != null) {
						String findName = humanArr[i].getName();
						if (name.equals(findName)) {
							System.out.println("중복된 이름이 있습니다. 번호나, 알파벳으로 구분해주세요.");
							b = false;
							break;
						}
					}
				}
				if(b == false) {
					continue;
				}
				break;
				
			}

			System.out.print("나이 :");
			int age = scanner.nextInt();

			System.out.print("신장 :");
			double height = scanner.nextInt();

			if (choice == 1) {
				System.out.print("이긴 횟수 : ");
				int win = scanner.nextInt();

				System.out.print("진 횟수 : ");
				int lose = scanner.nextInt();

				System.out.print("방어율 : ");
				double defence = scanner.nextDouble();

				Pitcher pitcher = new Pitcher(number, name, age, height, win, lose, defence);
				humanArr[count] = pitcher;

			} else if (choice == 2) {
				System.out.print("공이 맞은 횟수 :");
				int batcount = scanner.nextInt();

				System.out.print("던진 횟수 :");
				int hit = scanner.nextInt();

				System.out.print("히트율 :");
				double hitAvg = scanner.nextDouble();

				Batter batter = new Batter(number, name, age, height, batcount, hit, hitAvg);
				humanArr[count] = batter;
			}

			count++;
			break;
		}
	}

	public void delete() {
		// 삭제할 이름 입력받기
		System.out.print("삭제할 선수 :");
		String deleteMember = scanner.next();

		// 입력 받은 이름이 배열에 있는지 확인하고 인덱스값 찾기
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				String name = humanArr[i].getName();
				if (name.equals(deleteMember)) {
					// 찾은 선수가 투수인지 타자인지 확인해서 초기화 하기
					if (humanArr[i] instanceof Batter) {
						humanArr[i] = null;
					} else if (humanArr[i] instanceof Pitcher) {
						humanArr[i] = null;
					}
					System.out.println(deleteMember + " 선수를 삭제했습니다.");
					count--;
					return;
				}
			}
		}
		System.out.println("해당 선수를 찾을 수 없습니다.");
	}

	public void select() {

		// 검색할 선수 이름 입력받기
		System.out.print("검색할 선수 :");
		String searchName = scanner.next();

		// 입력 받은 이름이 배열에 있는지 확인하고 인덱스값 찾기
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				String name = humanArr[i].getName();
				if (name.equals(searchName)) {
					// 찾은 선수가 투수인지 타자인지 확인해서 선수 정보 출력하기
					if (humanArr[i] instanceof Batter) {
						((Batter) humanArr[i]).print();
						return;
					} else if (humanArr[i] instanceof Pitcher) {
						((Pitcher) humanArr[i]).print();
						return;
					}
				}
			}
		}

		System.out.println("해당 선수를 찾을 수 없습니다.");
	}

	public void update() {
		// 수정할 선수 이름 입력받기
		System.out.print("검색할 선수 :");
		String updateName = scanner.next();

		// 입력 받은 이름이 배열에 있는지 확인하고 인덱스값 찾기
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				String name = humanArr[i].getName();
				if (name.equals(updateName)) {
					System.out.println("수정할 내용을 입력해주세요");
					// 찾은 선수 정보 입력하기
					if (humanArr[i] instanceof Batter) {
						System.out.print("공이 맞은 횟수 :");
						int batcount = scanner.nextInt();
						((Batter) humanArr[i]).setBatcount(batcount);

						System.out.print("던진 횟수 :");
						int hit = scanner.nextInt();
						((Batter) humanArr[i]).setHit(hit);

						System.out.print("히트율 :");
						double hitAvg = scanner.nextDouble();
						((Batter) humanArr[i]).setHitAvg(hitAvg);
						
					} else if (humanArr[i] instanceof Pitcher) {
						System.out.print("이긴 횟수 : ");
						int win = scanner.nextInt();
						((Pitcher) humanArr[i]).setWin(win);

						System.out.print("진 횟수 : ");
						int lose = scanner.nextInt();
						
						((Pitcher) humanArr[i]).setLose(lose);

						System.out.print("방어율 : ");
						double defence = scanner.nextDouble();
						((Pitcher) humanArr[i]).setDefence(defence);

					}
					System.out.println("수정이 완료되었습니다.");
					return;
				}
			}
		}

		System.out.println("해당 선수를 찾을 수 없습니다.");
	}

	public void allPrint() {
		if (count == -1) {
			System.out.println("출력할 선수가 존재하지 않습니다. 선수를 등록해주세요.");
		}

		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				// 찾은 선수가 투수인지 타자인지 확인해서 선수 정보 출력하기
				if (humanArr[i] instanceof Batter) {
					System.out.println("포지션 : 타자");
					((Batter) humanArr[i]).print();
					System.out.println();
				} else if (humanArr[i] instanceof Pitcher) {
					System.out.println("포지션 : 투수");
					((Pitcher) humanArr[i]).print();
					System.out.println();
				}
			}

		}

	}

	//--------------------------------
	
	public void fileSave() throws Exception {
		FileWriter fw = new FileWriter("d:/tmp/baseballMember.txt");
		BufferedWriter bw = new BufferedWriter(fw); // 특정 파일에 문장 단위로 쓰기 위한 객체 생성
		PrintWriter pw = new PrintWriter(bw); // 문자열을 출력하는 객체 생성
		
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				//배열이 비어있지 않으면 내용을 파일에 쓰기
				if (humanArr[i] instanceof Batter) {
					pw.println(((Batter) humanArr[i]).getVelue());
				} else if (humanArr[i] instanceof Pitcher) {
					pw.println(((Pitcher) humanArr[i]).getVelue());
				}
			}
		}
		System.out.println("파일이 저장되었습니다.");
		pw.close(); 
	}
	
	public void fileLoad() throws Exception {
		System.out.println("파일이 로드 되었습니다.");
		
		String fileDir = "d:/tmp/baseballMember.txt";
		FileReader fr = new FileReader(fileDir);
		BufferedReader br = new BufferedReader (fr); // 특정 파일에 문장 단위로 쓰기 위한 객체 생성
		
		//파일 내용을 배열에 넣기
		int w = 0;
		String str = "";
		String strArr[] = new String[8];
		while((str = br.readLine()) != null) {
			strArr = str.split(",");
			if(strArr[0].equals("투수")) {
				int number = Integer.parseInt(strArr[1]);
				String name = strArr[2];
				int age = Integer.parseInt(strArr[3]);
				double height = Double.parseDouble(strArr[4]);
				int batcount = Integer.parseInt(strArr[5]);
				int hit = Integer.parseInt(strArr[6]);
				double hitAvg = Double.parseDouble(strArr[7]);

				Batter batter = new Batter(number, name, age, height, batcount, hit, hitAvg);
				humanArr[w] = batter;	
				
			} else if (strArr[0].equals("타자")) {
				int number = Integer.parseInt(strArr[1]);
				String name = strArr[2];
				int age = Integer.parseInt(strArr[3]);
				double height = Double.parseDouble(strArr[4]);
				int win = Integer.parseInt(strArr[5]);
				int lose = Integer.parseInt(strArr[6]);
				double defence = Double.parseDouble(strArr[7]);

				Pitcher pitcher = new Pitcher(number, name, age, height, win, lose, defence);
				humanArr[w] = pitcher;
			}
			w++;
		}
		
		//배열 안에 선수가 몇명있는지 확인하기
		int num = 0;
		for (int i = 0; i < humanArr.length; i++) {
			if(humanArr[i] != null) {
				num++;
			}
		}
		count = num;
	}
}
