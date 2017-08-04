package model;

import java.io.Serializable;

//歌曲类
public class Song implements Serializable {

	private static final long serialVersionUID = 1L;
	// 属性:歌曲id,歌曲名name,演唱者singer
	private String id;
	private String name;
	private String singer;

	// 构造方法
	public Song() {
	}

	public Song(String id, String name, String singer) {
		this.setId(id);
		this.setName(name);
		this.setSinger(singer);
	}

	// getter和setter方法
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	// hashCode和equals方法
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((singer == null) ? 0 : singer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() == Song.class) {
			Song song = (Song) obj;
			if (song.getId().equals(id) && song.getName().equals(name) && song.getSinger().equals(this.singer)) {
				return true;
			}
		}
		return false;
	}

	// 重写toString方法
	@Override
	public String toString() {
		return "歌曲信息 :【id:" + id + ", 歌曲名称:" + name + ", 演唱者:" + singer + "】";
	}
}
