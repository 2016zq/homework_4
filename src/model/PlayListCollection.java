package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//��������
public class PlayListCollection {
	// ����:��Ų����б�ļ���
	private Map<String, PlayList> playListMap;

	// ���췽��
	public PlayListCollection() {
		this.setPlayListMap(new HashMap<String, PlayList>());
	}

	public PlayListCollection(Map<String, PlayList> playListMap) {
		this.setPlayListMap(playListMap);
	}

	// getter��setter����
	public Map<String, PlayList> getPlayListMap() {
		return playListMap;
	}

	public void setPlayListMap(Map<String, PlayList> playListMap) {
		this.playListMap = playListMap;
	}

	/**
	 * ��Ӳ����б�
	 * 
	 * @param playList
	 *            ��ӵĲ����б�
	 */
	public void addPlayList(PlayList playList) {
		if (playListMap.keySet().contains(playList.getPlayListName())) {
			System.out.println("���ʧ��:�ò����б��Ѿ�����~~");
			return;
		}
		playListMap.put(playList.getPlayListName(), playList);
		System.out.println("�����б���ӳɹ�!");
	}

	/**
	 * ɾ�������б�
	 * 
	 * @param playListName
	 *            ��Ҫɾ�������б������
	 */
	public void deletecPlayList(String playListName) {
		if (!playListMap.containsKey(playListName)) {
			System.out.println("ɾ��ʧ��:�����ڸò����б�!");
			return;
		}
		if (playListName.equals("�������б�")) {
			System.out.println("ɾ��ʧ��:����ɾ���������б�!");
			return;
		}
		playListMap.remove(playListName);
		System.out.println("ɾ���ɹ�!");
	}

	/**
	 * ������ͨ�����ֲ�ѯ
	 * 
	 * @param playListName
	 *            �����б�����
	 * @return �����б�
	 */
	public PlayList searchPlayListByName(String playListName) {
		Set<String> playListNames = playListMap.keySet();
		for (String s : playListNames) {
			if (s.equals(playListName)) {
				System.out.println("�ò����б���ڣ�");
				System.out.println("�ò����б�����Ϊ��" + playListName);
				playListMap.get(playListName).displayAllSong();
				return playListMap.get(playListName);
			}
		}
		System.out.println("�ò����б����ڣ�");
		return null;
	}

	/**
	 * ��������ʾ���в����б�����
	 */
	public void displayPlayListName() {
		if (playListMap.isEmpty()) {
			System.out.println("�����б�Ϊ�գ�");
		} else {
			Set<String> playListNames = playListMap.keySet();
			for (String s : playListNames) {
				System.out.println(s);
			}
		}
	}

}
