package cl.telematica.multimedio.tareamultimedios.model;

//import java.util.ArrayList;

public class Element {
	private String title;
	private String image;
	private String points;
	private String link;

	public Element() {
	}

	public Element(String title, String image, String points, String link,
			String genre) {
		this.title = title;
		this.image = image;
		this.points = points;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
	
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	
	


}
