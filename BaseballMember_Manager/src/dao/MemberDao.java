package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import dto.*;

//DAO (Data Access Object)�� == Back end

public class MemberDao {
	// ��� ����
	Scanner scanner = new Scanner(System.in);
	int count;

	// �迭
	Human humanArr[] = new Human[20];
	
	// ������
	public MemberDao() {
	}

	public void init() throws Exception {
		for (int i = 0; i < humanArr.length; i++) {
			humanArr[i] = null;
		}
		System.out.println("Baseball Member ���� ���α׷��� ����Ǿ����ϴ�.");
		fileLoad();
		System.out.println(count);
	}

	public void insert() {

		while (count < humanArr.length) {
			// ����/Ÿ�� ?
			System.out.print((count + 1) + "��° ������ ������ ���� (1. ����, 2. Ÿ��) = ");
			int choice = scanner.nextInt();

			if (!(choice == 1 || choice == 2)) {
				System.out.println("�߸��Է��߽��ϴ�. �ٽ� �Է����ּ���.");
				continue;
			}

			System.out.print("��ȣ :");
			int number = scanner.nextInt();

			// �̸� �Է��ϱ�
			String name;
			while (true) {
				boolean b = true;
				System.out.print("�̸� :");
				name = scanner.next();
				for (int i = 0; i < humanArr.length; i++) {
					if (humanArr[i] != null) {
						String findName = humanArr[i].getName();
						if (name.equals(findName)) {
							System.out.println("�ߺ��� �̸��� �ֽ��ϴ�. ��ȣ��, ���ĺ����� �������ּ���.");
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

			System.out.print("���� :");
			int age = scanner.nextInt();

			System.out.print("���� :");
			double height = scanner.nextInt();

			if (choice == 1) {
				System.out.print("�̱� Ƚ�� : ");
				int win = scanner.nextInt();

				System.out.print("�� Ƚ�� : ");
				int lose = scanner.nextInt();

				System.out.print("����� : ");
				double defence = scanner.nextDouble();

				Pitcher pitcher = new Pitcher(number, name, age, height, win, lose, defence);
				humanArr[count] = pitcher;

			} else if (choice == 2) {
				System.out.print("���� ���� Ƚ�� :");
				int batcount = scanner.nextInt();

				System.out.print("���� Ƚ�� :");
				int hit = scanner.nextInt();

				System.out.print("��Ʈ�� :");
				double hitAvg = scanner.nextDouble();

				Batter batter = new Batter(number, name, age, height, batcount, hit, hitAvg);
				humanArr[count] = batter;
			}

			count++;
			break;
		}
	}

	public void delete() {
		// ������ �̸� �Է¹ޱ�
		System.out.print("������ ���� :");
		String deleteMember = scanner.next();

		// �Է� ���� �̸��� �迭�� �ִ��� Ȯ���ϰ� �ε����� ã��
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				String name = humanArr[i].getName();
				if (name.equals(deleteMember)) {
					// ã�� ������ �������� Ÿ������ Ȯ���ؼ� �ʱ�ȭ �ϱ�
					if (humanArr[i] instanceof Batter) {
						humanArr[i] = null;
					} else if (humanArr[i] instanceof Pitcher) {
						humanArr[i] = null;
					}
					System.out.println(deleteMember + " ������ �����߽��ϴ�.");
					count--;
					return;
				}
			}
		}
		System.out.println("�ش� ������ ã�� �� �����ϴ�.");
	}

	public void select() {

		// �˻��� ���� �̸� �Է¹ޱ�
		System.out.print("�˻��� ���� :");
		String searchName = scanner.next();

		// �Է� ���� �̸��� �迭�� �ִ��� Ȯ���ϰ� �ε����� ã��
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				String name = humanArr[i].getName();
				if (name.equals(searchName)) {
					// ã�� ������ �������� Ÿ������ Ȯ���ؼ� ���� ���� ����ϱ�
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

		System.out.println("�ش� ������ ã�� �� �����ϴ�.");
	}

	public void update() {
		// ������ ���� �̸� �Է¹ޱ�
		System.out.print("�˻��� ���� :");
		String updateName = scanner.next();

		// �Է� ���� �̸��� �迭�� �ִ��� Ȯ���ϰ� �ε����� ã��
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				String name = humanArr[i].getName();
				if (name.equals(updateName)) {
					System.out.println("������ ������ �Է����ּ���");
					// ã�� ���� ���� �Է��ϱ�
					if (humanArr[i] instanceof Batter) {
						System.out.print("���� ���� Ƚ�� :");
						int batcount = scanner.nextInt();
						((Batter) humanArr[i]).setBatcount(batcount);

						System.out.print("���� Ƚ�� :");
						int hit = scanner.nextInt();
						((Batter) humanArr[i]).setHit(hit);

						System.out.print("��Ʈ�� :");
						double hitAvg = scanner.nextDouble();
						((Batter) humanArr[i]).setHitAvg(hitAvg);
						
					} else if (humanArr[i] instanceof Pitcher) {
						System.out.print("�̱� Ƚ�� : ");
						int win = scanner.nextInt();
						((Pitcher) humanArr[i]).setWin(win);

						System.out.print("�� Ƚ�� : ");
						int lose = scanner.nextInt();
						
						((Pitcher) humanArr[i]).setLose(lose);

						System.out.print("����� : ");
						double defence = scanner.nextDouble();
						((Pitcher) humanArr[i]).setDefence(defence);

					}
					System.out.println("������ �Ϸ�Ǿ����ϴ�.");
					return;
				}
			}
		}

		System.out.println("�ش� ������ ã�� �� �����ϴ�.");
	}

	public void allPrint() {
		if (count == -1) {
			System.out.println("����� ������ �������� �ʽ��ϴ�. ������ ������ּ���.");
		}

		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				// ã�� ������ �������� Ÿ������ Ȯ���ؼ� ���� ���� ����ϱ�
				if (humanArr[i] instanceof Batter) {
					System.out.println("������ : Ÿ��");
					((Batter) humanArr[i]).print();
					System.out.println();
				} else if (humanArr[i] instanceof Pitcher) {
					System.out.println("������ : ����");
					((Pitcher) humanArr[i]).print();
					System.out.println();
				}
			}

		}

	}

	//--------------------------------
	
	public void fileSave() throws Exception {
		FileWriter fw = new FileWriter("d:/tmp/baseballMember.txt");
		BufferedWriter bw = new BufferedWriter(fw); // Ư�� ���Ͽ� ���� ������ ���� ���� ��ü ����
		PrintWriter pw = new PrintWriter(bw); // ���ڿ��� ����ϴ� ��ü ����
		
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null) {
				//�迭�� ������� ������ ������ ���Ͽ� ����
				if (humanArr[i] instanceof Batter) {
					pw.println(((Batter) humanArr[i]).getVelue());
				} else if (humanArr[i] instanceof Pitcher) {
					pw.println(((Pitcher) humanArr[i]).getVelue());
				}
			}
		}
		System.out.println("������ ����Ǿ����ϴ�.");
		pw.close(); 
	}
	
	public void fileLoad() throws Exception {
		System.out.println("������ �ε� �Ǿ����ϴ�.");
		
		String fileDir = "d:/tmp/baseballMember.txt";
		FileReader fr = new FileReader(fileDir);
		BufferedReader br = new BufferedReader (fr); // Ư�� ���Ͽ� ���� ������ ���� ���� ��ü ����
		
		//���� ������ �迭�� �ֱ�
		int w = 0;
		String str = "";
		String strArr[] = new String[8];
		while((str = br.readLine()) != null) {
			strArr = str.split(",");
			if(strArr[0].equals("����")) {
				int number = Integer.parseInt(strArr[1]);
				String name = strArr[2];
				int age = Integer.parseInt(strArr[3]);
				double height = Double.parseDouble(strArr[4]);
				int batcount = Integer.parseInt(strArr[5]);
				int hit = Integer.parseInt(strArr[6]);
				double hitAvg = Double.parseDouble(strArr[7]);

				Batter batter = new Batter(number, name, age, height, batcount, hit, hitAvg);
				humanArr[w] = batter;	
				
			} else if (strArr[0].equals("Ÿ��")) {
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
		
		//�迭 �ȿ� ������ ����ִ��� Ȯ���ϱ�
		int num = 0;
		for (int i = 0; i < humanArr.length; i++) {
			if(humanArr[i] != null) {
				num++;
			}
		}
		count = num;
	}
}
