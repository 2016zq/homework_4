package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import model.*;

//������
public class TestDemo {
	private static PlayList mainPlayList = new PlayList("�������б�", new ArrayList<Song>());
	private static PlayListCollection player = new PlayListCollection();
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// ���������б����player��������
		player.getPlayListMap().put(mainPlayList.getPlayListName(), mainPlayList);
		TestDemo td = new TestDemo();
		td.mainMenu();
	}

	// ���˵�
	public void mainMenu() {
		System.out.println("*********************************************** ");
		System.out.println("                    **���˵�**");
		System.out.println("                     1--�����б����");
		System.out.println("                     2--����������");
		System.out.println("                     0--�˳�");
		System.out.println("***********************************************");
		System.out.println("�������Ӧ���ֽ��в���:");
		int num;
		try {
			num = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("�����ʽ����~");
			input.next();
			mainMenu();
			return;
		}
		switch (num) {
		case 1:
			playListMenu();
			break;
		case 2:
			playerMenu();
			break;
		case 0:
			System.exit(0);
			break;
		default:
			System.out.println("û�ж�Ӧ�Ĳ���Ӵ~");
			mainMenu();
			return;
		}
	}

	// �����б����˵�
	public void playListMenu() {
		System.out.println("***********************************************");
		System.out.println("                **�����б����**");
		System.out.println("                 1--��������ӵ��������б�");
		System.out.println("                 2--��������ӵ���ͨ�����б�");
		System.out.println("                 3--ͨ������id��ѯ�����б��еĸ���   ");
		System.out.println("                 4--ͨ���������Ʋ�ѯ�����б��еĸ���");
		System.out.println("                 5--�޸Ĳ����б��еĸ���");
		System.out.println("                 6--ɾ�������б��еĸ���");
		System.out.println("                 7--��ʾ�����б��е����и���");
		System.out.println("                 8--�����赥");
		System.out.println("                 9--������һ���˵�");
		System.out.println("***********************************************");
		System.out.println("�������Ӧ�����ֶԲ����б���й���:");
		int num;
		try {
			num = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("�����ʽ����~");
			input.next();
			playListMenu();
			return;
		}
		switch (num) {
		case 1:
			mainPlayList.addPlayList(mainPlayList);
			playListMenu();
			return;
		case 2:
			System.out.println("��������ӵ���ͨ�����б�");
			System.out.println("������Ҫ��ӵĲ����б�����:");
			String playListName2 = input.next();
			if (player.getPlayListMap().containsKey(playListName2)) {
				PlayList pl = player.getPlayListMap().get(playListName2);
				pl.addPlayList(pl);
			} else {
				System.out.println("�ò����б�����,���Ƚ������б���ӵ���������!");
			}
			playListMenu();
			return;
		case 3:
			System.out.println("������Ҫ��ѯ�Ĳ����б������:");
			String playListName3 = input.next();
			if (player.getPlayListMap().containsKey(playListName3)) {
				PlayList pl = player.getPlayListMap().get(playListName3);
				System.out.println("������Ҫ��ѯ�ĸ���id:");
				String id = input.next();
				pl.searchSongById(id);
			} else {
				System.out.println("�ò����б�����,���Ƚ������б���ӵ���������!");
			}
			playListMenu();
			return;
		case 4:
			System.out.println("������Ҫ��ѯ�Ĳ����б������:");
			String playListName4 = input.next();
			if (player.getPlayListMap().containsKey(playListName4)) {
				PlayList pl = player.getPlayListMap().get(playListName4);
				System.out.println("������Ҫ��ѯ�ĸ�������:");
				String name = input.next();
				pl.searchSongByName(name);
			} else {
				System.out.println("�ò����б�����,���Ƚ������б���ӵ���������!");
			}
			playListMenu();
			return;
		case 5:
			Song s5;
			System.out.println("������Ҫ�޸ĵĲ����б�����:");
			String plName= input.next();
			for (PlayList playlist : player.getPlayListMap().values()) {
				if(playlist.getPlayListName().equals(plName)) {
					System.out.println("������Ҫ�޸ĵĸ���id:");
					String id5 = input.next();
					for (Song song :playlist.getMusicList()) {
							if (song.getId().equals(id5)) {
								System.out.println("�������޸ĺ�ĸ�������:");
								String name5 = input.next();
								System.out.println("�������޸ĺ���ݳ���:");
								String singer5 = input.next();
								s5 = new Song(id5, name5, singer5);
								playlist.updateSong(s5);
								playListMenu();
								return;
							}
						}
						System.out.println(plName + "�в����ڸø���!");
						playListMenu();
						return;
					}
				else {
					System.out.println("�����б�" + plName +"������!");
					playListMenu();
					return;
					}
				}
		case 6:
			System.out.println("������Ҫɾ���ĸ������ڵĲ����б�:");
			String playListName6 = input.next();
			if (player.getPlayListMap().containsKey(playListName6)) {
				PlayList pl6 = player.getPlayListMap().get(playListName6);
				pl6.displayAllSong();
				System.out.println("������Ҫɾ���ĸ�����id:");
				String id6 = input.next();
				Song songtemp = null;
				for (Song s : pl6.getMusicList()) {
					if (s.getId().equals(id6)) {
						songtemp = s;
					}
				}
				pl6.deleteSong(id6);
				if (playListName6.equals("�������б�")) {
					for (PlayList playlist : player.getPlayListMap().values()) {
						for (Song song : playlist.getMusicList()) {
							if (song.getName().equals(songtemp.getName())
									&& song.getSinger().equals(songtemp.getSinger())) {
								playlist.getMusicList().remove(song);
								break;
							}
						}
					}
				}
			} else {
				System.out.println("�ò����б�����!");
			}
			playListMenu();
			return;
		case 7:
			System.out.println("������Ҫ�鿴�Ĳ����б������:");
			String playListName7 = input.next();
			if (player.getPlayListMap().containsKey(playListName7)) {
				PlayList pl7 = player.getPlayListMap().get(playListName7);
				pl7.displayAllSong();
			} else {
				System.out.println("�����ڸò����б���������");
			}
			playListMenu();
			return;
		case 8:
			System.out.println("**�����赥**");
			System.out.println("������Ҫ�����赥�����ƣ�");
			String playListName = input.next();
			if (!player.getPlayListMap().containsKey(playListName)) {
				System.out.println("�ø赥�����ڣ�");
				playListMenu();
				break;
			}
			try {
				FileOutputStream fos = new FileOutputStream(playListName + ".txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				PlayList playList = player.getPlayListMap().get(playListName);
				oos.writeObject(playList);
				oos.close();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("�����ɹ�");
			playListMenu();
			break;
		case 9:
			mainMenu();
		default:
			System.out.println("û�ж�Ӧ�Ĳ���Ӵ~");
			playListMenu();
			return;
		}
	}

	// �������˵�
	public void playerMenu() {
		System.out.println("***********************************************");
		System.out.println("                **����������**");
		System.out.println("                 1--�򲥷�����Ӳ����б�");
		System.out.println("                 2--�Ӳ�����ɾ�������б�");
		System.out.println("                 3--ͨ�����ֲ�ѯ�����б���Ϣ   ");
		System.out.println("                 4--��ʾ���в����б������   ");
		System.out.println("                 9--������һ���˵�");
		System.out.println("***********************************************");
		System.out.println("�������Ӧ�����ֶԲ����б���й���:");
		int num;
		try {
			num = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("�����ʽ����~");
			input.next();
			playerMenu();
			return;
		}
		switch (num) {
		case 1:
			System.out.println("����Ҫ��ӵĲ����б������:");
			String playListname = input.next();
			PlayList playList = new PlayList(playListname);
			player.addPlayList(playList);
			playerMenu();
			break;
		case 2:
			System.out.println("������Ҫɾ�����б������:");
			String dName = input.next();
			player.deletecPlayList(dName);
			playerMenu();
			break;
		case 3:
			System.out.println("������Ҫ���ҵĲ����б������:");
			String qName = input.next();
			player.searchPlayListByName(qName);
			playerMenu();
			break;
		case 4:
			player.displayPlayListName();
			playerMenu();
			break;
		case 9:
			mainMenu();
			break;
		default:
			System.out.println("û�ж�Ӧ�Ĳ���Ӵ~");
			playerMenu();
			return;
		}
	}
}
