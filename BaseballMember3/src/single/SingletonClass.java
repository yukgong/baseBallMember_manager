package single;

import java.util.ArrayList;
import java.util.Scanner;
import dto.*;
import fileClass.FileLoad;

public class SingletonClass {
	//멤버 변수
	private static SingletonClass sc = null;
	public ArrayList<Human> list = null; // null로 잡는 것을 권장.
	public int memberNumber;
	
	private SingletonClass() {
		memberNumber = 1000;
		list = new ArrayList<Human>();
	}
	
	public static SingletonClass getInstance() {
		if(sc == null) {
			sc = new SingletonClass();
		}
		return sc;
	}
}
