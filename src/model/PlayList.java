package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//播放列表类
public class PlayList implements Serializable {

	private static final long serialVersionUID = 1L;
	// 属性:播放列表名称，播放列表中的歌曲集合
	private String playListName;
	private List<Song> musicList;

	// 构造方法
	public PlayList() {
	}

	public PlayList(String playListName) {
		this.setPlayListName(playListName);
		this.setMusicList(new ArrayList<Song>());
	}

	public PlayList(String playListName, List<Song> musicList) {
		this.setPlayListName(playListName);
		this.setMusicList(musicList);
	}

	// getter和setter方法
	public String getPlayListName() {
		return playListName;
	}

	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}

	public List<Song> getMusicList() {
		return musicList;
	}

	public void setMusicList(List<Song> musicList) {
		this.musicList = musicList;
	}

	// 重写toString方法
	@Override
	public String toString() {
		return "播放列表 \n列表名称:" + playListName + "\n列表歌曲清单:" + musicList;
	}

	/**
	 * 将歌曲添加到播放列表中
	 * 
	 * @param playList
	 *            添加的列表
	 */
	public void addPlayList(PlayList playList) {
		System.out.println("请输入要添加的歌曲的数量:");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int num;
		try {
			num = input.nextInt();
			if (num == 0) {
				System.out.println("错误提示:不可以添加0首!");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("错误提示:输入格式有误~");
			input.next();
			return;
		}
		for (int i = 1; i <= num; i++) {
			System.out.println("请输入第" + i + "首歌的信息:");
			System.out.println("请输入歌曲id:");
			String id = input.next();
			for (Song s : playList.getMusicList()) {
				if (s.getId().equals(id)) {
					System.out.println("错误提示:id不能重复!");
					return;
				}
			}
			System.out.println("请输入歌曲名称:");
			String name = input.next();
			System.out.println("请输入演唱者:");
			String singer = input.next();
			for (Song s : playList.getMusicList()) {
				if (s.getName().equals(name) && s.getSinger().equals(singer)) {
					System.out.println("错误提示:该歌曲在当前播放列表中已存在!");
					return;
				}
			}
			Song song = new Song(id, name, singer);
			playList.musicList.add(song);
			System.out.println("添加成功!");
		}
	}

	/**
	 * 显示该播放列表中的所有歌曲
	 */
	public void displayAllSong() {
		if (this.musicList.isEmpty()) {
			System.out.println("该播放列表还没有歌曲，赶快去添加歌曲吧~");
		} else {
			System.out.println("播放列表<<" + playListName + ">>中的所有歌曲为:");
			for (Song s : musicList) {
				System.out.println(s);
			}
		}
	}

	/**
	 * 通过id查询该列表中的歌曲
	 * 
	 * @param id
	 *            歌曲id
	 * @return 歌曲Song
	 */
	public Song searchSongById(String id) {
		Iterator<Song> itr = musicList.iterator();
		if (itr.hasNext()) {
			Song s = itr.next();
			if (s.getId().equals(id)) {
				System.out.println("找到啦~~!");
				System.out.println("歌曲信息为:");
				System.out.println(s);
				return s;
			}
		}
		System.out.println("该歌曲在播放列表<<" + this.getPlayListName() + ">>中不存在该歌曲。。。。");
		return null;
	}

	/**
	 * 通过名称查询歌曲
	 * 
	 * @param name
	 *            歌曲名
	 * @return 歌曲Song
	 */
	public Song searchSongByName(String name) {
		for (Song s : musicList) {
			if (s.getName().equals(name)) {
				System.out.println("找到啦~~!");
				System.out.println("歌曲信息为:");
				System.out.println(s);
				return s;
			}
		}
		System.out.println("该歌曲在播放列表<<" + this.getPlayListName() + ">>中不存在。。。。");
		return null;
	}

	/**
	 * 修改该列表中的歌曲
	 * 
	 * @param song
	 *            传入修改后的歌曲，不能修改id
	 */
	public void updateSong(Song song) {
		for (Song s : musicList) {
			if (s.getId().equals(song.getId())) {
				s.setName(song.getName());
				s.setSinger(song.getSinger());
				System.out.println("修改成功!");
				break;
			}
		}
	}

	/**
	 * 从播放列表删除歌曲
	 * 
	 * @param id
	 *            要删除的歌曲的id
	 */
	public void deleteSong(String id) {
		for (Song s : musicList) {
			if (s.getId().equals(id)) {
				musicList.remove(s);
				System.out.println("删除成功!");
				return;
			}
		}
		System.out.println("该歌曲在播放列表<<" + this.getPlayListName() + ">>中不存在。。。。");
	}
}
