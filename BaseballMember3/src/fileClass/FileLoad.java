package fileClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import daoInterface.DaoInterface;
import dto.*;
import single.SingletonClass;

public class FileLoad implements DaoInterface  {

	private File file;

	public FileLoad() {
		String path = "d:/tmp/singletonList.txt";
		file = new File(path);
		createFile();
	}
	
	public void createFile() {
		try {
			if (file.createNewFile()) { // 파일 생성하기
				System.out.println("파일이 생성되었습니다.");
			} else {
				System.out.println("파일 생성 실패");// 파일 생성 실패시 메세지 출력
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void process() {
		SingletonClass sc = SingletonClass.getInstance();
		// data의 갯수를 조사 -> 배열 ... 하지만 list를 사용하면, 이처리가 필요없다.

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			// 배열 저장
			String str = "";
			while ((str = br.readLine()) != null) {
				// str -> 번호-이름-나이-신장
				
				//문자열을 자른다.
				String splitData[] = str.split("-");
				int number = Integer.parseInt(splitData[0]);
				if (number < 2000) { // 투수
					sc.list.add(new Pitcher(Integer.parseInt(splitData[0]), 
											splitData[1], 
											Integer.parseInt(splitData[2]),
											Double.parseDouble(splitData[3]), 
											Integer.parseInt(splitData[4]),
											Integer.parseInt(splitData[5]), 
											Double.parseDouble(splitData[6])));
				} else {
					sc.list.add(new Batter(	Integer.parseInt(splitData[0]), 
											splitData[1], 
											Integer.parseInt(splitData[2]),
											Double.parseDouble(splitData[3]), 
											Integer.parseInt(splitData[4]),
											Integer.parseInt(splitData[5]), 
											Double.parseDouble(splitData[6])));
				}
				
			}
			br.close();
			System.out.println("list.size() = " + sc.list.size());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
