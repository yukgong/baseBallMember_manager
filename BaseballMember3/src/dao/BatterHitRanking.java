package dao;

import java.util.ArrayList;

import daoInterface.DaoInterface;
import dto.*;
import single.SingletonClass;

public class BatterHitRanking implements DaoInterface{
	
	public BatterHitRanking() {}

	@Override
	public void process() {
		// 타자만을 수집한 배열
				ArrayList<Human> sortList = positionSelect(2);

				Human obj = null; // Swap을 위해 temp 대신 빈 객체하나를 준비한다.
				for (int i = 0; i < sortList.size() - 1; i++) {
					for (int j = i + 1; j < sortList.size(); j++) {
						Batter b1 = (Batter) sortList.get(i);
						Batter b2 = (Batter) sortList.get(j);
						if (b1.getHitAvg() < b2.getHitAvg()) {
							obj = sortList.get(i);
							sortList.set(i, sortList.get(j));
							sortList.set(j, obj);
						}
					}
				}

				for (int i = 0; i < sortList.size(); i++) {
					Human human = sortList.get(i);
					System.out.println(i + " : " + human);
				}
			}
			
			static public ArrayList<Human> positionSelect(int num) {
				SingletonClass sc = SingletonClass.getInstance();
				ArrayList<Human> reList = new ArrayList<Human>();

				for (int i = 0; i < sc.list.size(); i++) {
					Human human = sc.list.get(i);
					if (num == 1) { // pitcher
						if (human.getNumber() < 2000) {
							reList.add(human);
						}

					} else if (num == 2) { // batter
						if (human.getNumber() >= 2000) {
							reList.add(human);
						}
					}
				}
				return reList;
	}
}
