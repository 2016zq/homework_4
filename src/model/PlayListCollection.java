package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//播放器类
public class PlayListCollection {
	// 属性:存放播放列表的集合
	private Map<String, PlayList> playListMap;

	// 构造方法
	public PlayListCollection() {
		this.setPlayListMap(new HashMap<String, PlayList>());
	}

	public PlayListCollection(Map<String, PlayList> playListMap) {
		this.setPlayListMap(playListMap);
	}

	// getter和setter方法
	public Map<String, PlayList> getPlayListMap() {
		return playListMap;
	}

	public void setPlayListMap(Map<String, PlayList> playListMap) {
		this.playListMap = playListMap;
	}

	/**
	 * 添加播放列表
	 * 
	 * @param playList
	 *            添加的播放列表
	 */
	public void addPlayList(PlayList playList) {
		if (playListMap.keySet().contains(playList.getPlayListName())) {
			System.out.println("添加失败:该播放列表已经存在~~");
			return;
		}
		playListMap.put(playList.getPlayListName(), playList);
		System.out.println("播放列表添加成功!");
	}

	/**
	 * 删除播放列表
	 * 
	 * @param playListName
	 *            需要删除播放列表的名字
	 */
	public void deletecPlayList(String playListName) {
		if (!playListMap.containsKey(playListName)) {
			System.out.println("删除失败:不存在该播放列表!");
			return;
		}
		if (playListName.equals("主播放列表")) {
			System.out.println("删除失败:不能删除主播放列表!");
			return;
		}
		playListMap.remove(playListName);
		System.out.println("删除成功!");
	}

	/**
	 * 方法：通过名字查询
	 * 
	 * @param playListName
	 *            播放列表名字
	 * @return 播放列表
	 */
	public PlayList searchPlayListByName(String playListName) {
		Set<String> playListNames = playListMap.keySet();
		for (String s : playListNames) {
			if (s.equals(playListName)) {
				System.out.println("该播放列表存在！");
				System.out.println("该播放列表名称为：" + playListName);
				playListMap.get(playListName).displayAllSong();
				return playListMap.get(playListName);
			}
		}
		System.out.println("该播放列表不存在！");
		return null;
	}

	/**
	 * 方法：显示所有播放列表名称
	 */
	public void displayPlayListName() {
		if (playListMap.isEmpty()) {
			System.out.println("播放列表为空！");
		} else {
			Set<String> playListNames = playListMap.keySet();
			for (String s : playListNames) {
				System.out.println(s);
			}
		}
	}

}
