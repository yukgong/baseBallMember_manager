package dao;

import java.util.ArrayList;

import daoInterface.DaoInterface;
import dto.*;

public class PitcherDefenseRanking implements DaoInterface {

	public PitcherDefenseRanking() {
	}

	@Override
	public void process() {
		// 투수만을 수집한 배열
		ArrayList<Human> sortList = BatterHitRanking.positionSelect(1);

		// 오름차순 정렬
		Human obj = null;
		for (int i = 0; i < sortList.size() - 1; i++) {
			for (int j = i + 1; j < sortList.size(); j++) {
				Pitcher p1 = (Pitcher) sortList.get(i);
				Pitcher p2 = (Pitcher) sortList.get(j);
				if (p1.getDefence() > p2.getDefence()) {
					obj = sortList.get(i);
					sortList.set(i, sortList.get(j));
					sortList.set(j, obj);
				}
			}
		}
		// 출력
		for (int i = 0; i < sortList.size(); i++) {
			Human human = sortList.get(i);
			System.out.println(i + " : " + human);
		}
	}
}
