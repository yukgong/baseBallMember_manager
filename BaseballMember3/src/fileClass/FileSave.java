package fileClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import daoInterface.DaoInterface;
import dto.*;
import single.SingletonClass;

public class FileSave implements DaoInterface{
	private File file;
	
	public FileSave() {
		String path = "d:/tmp/singletonList.txt";
		file = new File(path);
	}

	@Override
	public void process() {
		SingletonClass sc = SingletonClass.getInstance();
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

			for (int i = 0; i < sc.list.size(); i++) {
				Human human = sc.list.get(i);
				pw.println(human);
			}
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
