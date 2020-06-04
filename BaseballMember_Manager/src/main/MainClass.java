package main;

import java.util.Scanner;

import dao.MemberDao;

public class MainClass {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		// �޴����� == front end

		// �ҷ������ �ڵ�����
		// �����ϱ�� �޴���

		// �ʱ�ȭ
		int choice;
		MemberDao mDAO = new MemberDao();
		mDAO.init();
		out: while (true) {
			
			// ������ �޴� �����ֱ�
			System.out.println("------�޴�------");
			System.out.println("1. ���� �߰�");
			System.out.println("2. ���� ���� ");
			System.out.println("3. ���� ���� �˻�");
			System.out.println("4. ���� ���� ����");
			System.out.println("5. ���� ���� ��� ���");
			System.out.println("6. ���� ����");
			System.out.println("7. ����");
			System.out.println("--------------\n");
			System.out.println("�޴� ��ȣ�� �Է����ּ���");
			System.out.print(">>>");
			choice = scanner.nextInt();
			
			// �߸��� ��ȣ�� �������� ��
			if (choice <= 0 || choice > 7) {
				System.out.println("�߸��Է��߽��ϴ�. �ٽ� �Է����ּ���.");
				continue;
			}

			switch (choice) {
			case 1: // ���� �߰�
				mDAO.insert();
				break;

			case 2: // ���� ����
				mDAO.delete();
				break;

			case 3: // ���� �˻�
				mDAO.select();
				break;
				
			case 4: // ���� ����
				mDAO.update();
				break;
				
			case 5: // ���� ���� ��� ���
				mDAO.allPrint();
				break;
				
			case 6: // ���� ���� ��� ���
				mDAO.fileSave();
				break;
				
			case 7: // ����
				System.out.println("�̿����ּż� �����մϴ�.");
				break out;
			}
		}
	}
}
