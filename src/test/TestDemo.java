package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import model.*;

//测试类
public class TestDemo {
	private static PlayList mainPlayList = new PlayList("主播放列表", new ArrayList<Song>());
	private static PlayListCollection player = new PlayListCollection();
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// 将主播放列表加入player播放器中
		player.getPlayListMap().put(mainPlayList.getPlayListName(), mainPlayList);
		TestDemo td = new TestDemo();
		td.mainMenu();
	}

	// 主菜单
	public void mainMenu() {
		System.out.println("*********************************************** ");
		System.out.println("                    **主菜单**");
		System.out.println("                     1--播放列表管理");
		System.out.println("                     2--播放器管理");
		System.out.println("                     0--退出");
		System.out.println("***********************************************");
		System.out.println("请输入对应数字进行操作:");
		int num;
		try {
			num = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("输入格式有误~");
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
			System.out.println("没有对应的操作哟~");
			mainMenu();
			return;
		}
	}

	// 播放列表管理菜单
	public void playListMenu() {
		System.out.println("***********************************************");
		System.out.println("                **播放列表管理**");
		System.out.println("                 1--将歌曲添加到主播放列表");
		System.out.println("                 2--将歌曲添加到普通播放列表");
		System.out.println("                 3--通过歌曲id查询播放列表中的歌曲   ");
		System.out.println("                 4--通过歌曲名称查询播放列表中的歌曲");
		System.out.println("                 5--修改播放列表中的歌曲");
		System.out.println("                 6--删除播放列表中的歌曲");
		System.out.println("                 7--显示播放列表中的所有歌曲");
		System.out.println("                 8--导出歌单");
		System.out.println("                 9--返回上一级菜单");
		System.out.println("***********************************************");
		System.out.println("请输入对应的数字对播放列表进行管理:");
		int num;
		try {
			num = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("输入格式有误~");
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
			System.out.println("将歌曲添加到普通播放列表");
			System.out.println("请输入要添加的播放列表名称:");
			String playListName2 = input.next();
			if (player.getPlayListMap().containsKey(playListName2)) {
				PlayList pl = player.getPlayListMap().get(playListName2);
				pl.addPlayList(pl);
			} else {
				System.out.println("该播放列表不存在,请先将播放列表添加到播放器中!");
			}
			playListMenu();
			return;
		case 3:
			System.out.println("请输入要查询的播放列表的名称:");
			String playListName3 = input.next();
			if (player.getPlayListMap().containsKey(playListName3)) {
				PlayList pl = player.getPlayListMap().get(playListName3);
				System.out.println("请输入要查询的歌曲id:");
				String id = input.next();
				pl.searchSongById(id);
			} else {
				System.out.println("该播放列表不存在,请先将播放列表添加到播放器中!");
			}
			playListMenu();
			return;
		case 4:
			System.out.println("请输入要查询的播放列表的名称:");
			String playListName4 = input.next();
			if (player.getPlayListMap().containsKey(playListName4)) {
				PlayList pl = player.getPlayListMap().get(playListName4);
				System.out.println("请输入要查询的歌曲名称:");
				String name = input.next();
				pl.searchSongByName(name);
			} else {
				System.out.println("该播放列表不存在,请先将播放列表添加到播放器中!");
			}
			playListMenu();
			return;
		case 5:
			Song s5;
			System.out.println("请输入要修改的播放列表名称:");
			String plName= input.next();
			for (PlayList playlist : player.getPlayListMap().values()) {
				if(playlist.getPlayListName().equals(plName)) {
					System.out.println("请输入要修改的歌曲id:");
					String id5 = input.next();
					for (Song song :playlist.getMusicList()) {
							if (song.getId().equals(id5)) {
								System.out.println("请输入修改后的歌曲名称:");
								String name5 = input.next();
								System.out.println("请输入修改后的演唱者:");
								String singer5 = input.next();
								s5 = new Song(id5, name5, singer5);
								playlist.updateSong(s5);
								playListMenu();
								return;
							}
						}
						System.out.println(plName + "中不存在该歌曲!");
						playListMenu();
						return;
					}
				else {
					System.out.println("播放列表" + plName +"不存在!");
					playListMenu();
					return;
					}
				}
		case 6:
			System.out.println("请输入要删除的歌曲所在的播放列表:");
			String playListName6 = input.next();
			if (player.getPlayListMap().containsKey(playListName6)) {
				PlayList pl6 = player.getPlayListMap().get(playListName6);
				pl6.displayAllSong();
				System.out.println("请输入要删除的歌曲的id:");
				String id6 = input.next();
				Song songtemp = null;
				for (Song s : pl6.getMusicList()) {
					if (s.getId().equals(id6)) {
						songtemp = s;
					}
				}
				pl6.deleteSong(id6);
				if (playListName6.equals("主播放列表")) {
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
				System.out.println("该播放列表不存在!");
			}
			playListMenu();
			return;
		case 7:
			System.out.println("请输入要查看的播放列表的名称:");
			String playListName7 = input.next();
			if (player.getPlayListMap().containsKey(playListName7)) {
				PlayList pl7 = player.getPlayListMap().get(playListName7);
				pl7.displayAllSong();
			} else {
				System.out.println("不存在该播放列表。。。。。");
			}
			playListMenu();
			return;
		case 8:
			System.out.println("**导出歌单**");
			System.out.println("请输入要导出歌单的名称：");
			String playListName = input.next();
			if (!player.getPlayListMap().containsKey(playListName)) {
				System.out.println("该歌单不存在！");
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
			System.out.println("导出成功");
			playListMenu();
			break;
		case 9:
			mainMenu();
		default:
			System.out.println("没有对应的操作哟~");
			playListMenu();
			return;
		}
	}

	// 播放器菜单
	public void playerMenu() {
		System.out.println("***********************************************");
		System.out.println("                **播放器管理**");
		System.out.println("                 1--向播放器添加播放列表");
		System.out.println("                 2--从播放器删除播放列表");
		System.out.println("                 3--通过名字查询播放列表信息   ");
		System.out.println("                 4--显示所有播放列表的名称   ");
		System.out.println("                 9--返回上一级菜单");
		System.out.println("***********************************************");
		System.out.println("请输入对应的数字对播放列表进行管理:");
		int num;
		try {
			num = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("输入格式有误~");
			input.next();
			playerMenu();
			return;
		}
		switch (num) {
		case 1:
			System.out.println("输入要添加的播放列表的名称:");
			String playListname = input.next();
			PlayList playList = new PlayList(playListname);
			player.addPlayList(playList);
			playerMenu();
			break;
		case 2:
			System.out.println("请输入要删除的列表的名称:");
			String dName = input.next();
			player.deletecPlayList(dName);
			playerMenu();
			break;
		case 3:
			System.out.println("请输入要查找的播放列表的名字:");
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
			System.out.println("没有对应的操作哟~");
			playerMenu();
			return;
		}
	}
}
