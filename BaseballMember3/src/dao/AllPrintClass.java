package dao;

import java.util.ArrayList;
import java.util.Scanner;

import daoInterface.DaoInterface;
import dto.*;
import single.SingletonClass;

public class AllPrintClass implements DaoInterface {
	Scanner scanner = new Scanner(System.in);

	@Override
	public void process() {
		SingletonClass sc = SingletonClass.getInstance();
		
		for (Human a : sc.list) {
			System.out.println(a.toString());
		}
	}
}
