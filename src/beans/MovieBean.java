package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieBean implements Serializable {


	private static final long serialVersionUID = 1L;

	String movie_id;
	String movie_name;
	ArrayList<PictureBean> pictures;
	int groups;
	
	
	public int getGroups() {
		return groups;
	}
	public void setGroups(int groups) {
		this.groups = groups;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public ArrayList<PictureBean> getPictures() {
		return pictures;
	}
	public void setPictures(ArrayList<PictureBean> pictures) {
		this.pictures = pictures;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
