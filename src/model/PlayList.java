package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//�����б���
public class PlayList implements Serializable {

	private static final long serialVersionUID = 1L;
	// ����:�����б����ƣ������б��еĸ�������
	private String playListName;
	private List<Song> musicList;

	// ���췽��
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

	// getter��setter����
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

	// ��дtoString����
	@Override
	public String toString() {
		return "�����б� \n�б�����:" + playListName + "\n�б�����嵥:" + musicList;
	}

	/**
	 * ��������ӵ������б���
	 * 
	 * @param playList
	 *            ��ӵ��б�
	 */
	public void addPlayList(PlayList playList) {
		System.out.println("������Ҫ��ӵĸ���������:");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int num;
		try {
			num = input.nextInt();
			if (num == 0) {
				System.out.println("������ʾ:���������0��!");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("������ʾ:�����ʽ����~");
			input.next();
			return;
		}
		for (int i = 1; i <= num; i++) {
			System.out.println("�������" + i + "�׸����Ϣ:");
			System.out.println("���������id:");
			String id = input.next();
			for (Song s : playList.getMusicList()) {
				if (s.getId().equals(id)) {
					System.out.println("������ʾ:id�����ظ�!");
					return;
				}
			}
			System.out.println("�������������:");
			String name = input.next();
			System.out.println("�������ݳ���:");
			String singer = input.next();
			for (Song s : playList.getMusicList()) {
				if (s.getName().equals(name) && s.getSinger().equals(singer)) {
					System.out.println("������ʾ:�ø����ڵ�ǰ�����б����Ѵ���!");
					return;
				}
			}
			Song song = new Song(id, name, singer);
			playList.musicList.add(song);
			System.out.println("��ӳɹ�!");
		}
	}

	/**
	 * ��ʾ�ò����б��е����и���
	 */
	public void displayAllSong() {
		if (this.musicList.isEmpty()) {
			System.out.println("�ò����б�û�и������Ͽ�ȥ��Ӹ�����~");
		} else {
			System.out.println("�����б�<<" + playListName + ">>�е����и���Ϊ:");
			for (Song s : musicList) {
				System.out.println(s);
			}
		}
	}

	/**
	 * ͨ��id��ѯ���б��еĸ���
	 * 
	 * @param id
	 *            ����id
	 * @return ����Song
	 */
	public Song searchSongById(String id) {
		Iterator<Song> itr = musicList.iterator();
		if (itr.hasNext()) {
			Song s = itr.next();
			if (s.getId().equals(id)) {
				System.out.println("�ҵ���~~!");
				System.out.println("������ϢΪ:");
				System.out.println(s);
				return s;
			}
		}
		System.out.println("�ø����ڲ����б�<<" + this.getPlayListName() + ">>�в����ڸø�����������");
		return null;
	}

	/**
	 * ͨ�����Ʋ�ѯ����
	 * 
	 * @param name
	 *            ������
	 * @return ����Song
	 */
	public Song searchSongByName(String name) {
		for (Song s : musicList) {
			if (s.getName().equals(name)) {
				System.out.println("�ҵ���~~!");
				System.out.println("������ϢΪ:");
				System.out.println(s);
				return s;
			}
		}
		System.out.println("�ø����ڲ����б�<<" + this.getPlayListName() + ">>�в����ڡ�������");
		return null;
	}

	/**
	 * �޸ĸ��б��еĸ���
	 * 
	 * @param song
	 *            �����޸ĺ�ĸ����������޸�id
	 */
	public void updateSong(Song song) {
		for (Song s : musicList) {
			if (s.getId().equals(song.getId())) {
				s.setName(song.getName());
				s.setSinger(song.getSinger());
				System.out.println("�޸ĳɹ�!");
				break;
			}
		}
	}

	/**
	 * �Ӳ����б�ɾ������
	 * 
	 * @param id
	 *            Ҫɾ���ĸ�����id
	 */
	public void deleteSong(String id) {
		for (Song s : musicList) {
			if (s.getId().equals(id)) {
				musicList.remove(s);
				System.out.println("ɾ���ɹ�!");
				return;
			}
		}
		System.out.println("�ø����ڲ����б�<<" + this.getPlayListName() + ">>�в����ڡ�������");
	}
}
