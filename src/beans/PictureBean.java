package beans;

import java.io.Serializable;

public class PictureBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String url;
	private String source;
	private String local_add;
	private String from;
	private String title;
	private int interesting;
	private int group;
	private String alt; // the description of pircture
	
	
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLocal_add() {
		return local_add;
	}
	public void setLocal_add(String local_add) {
		this.local_add = local_add;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getInteresting() {
		return interesting;
	}
	public void setInteresting(int interesting) {
		this.interesting = interesting;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	
	
	
	
	
	
	/*String img_id;
	String img_title;
	String img_description;
	int img_isLike;
	public String getImg_id() {
		return img_id;
	}
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	public String getImg_title() {
		return img_title;
	}
	public void setImg_title(String img_title) {
		this.img_title = img_title;
	}
	public String getImg_description() {
		return img_description;
	}
	public void setImg_description(String img_description) {
		this.img_description = img_description;
	}
	public int getImg_isLike() {
		return img_isLike;
	}
	public void setImg_isLike(int img_isLike) {
		this.img_isLike = img_isLike;
	}*/
	
	
}
  