import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Uzoma Nwiwu
 *
 */
public class Node extends Observable {
	/**
	 * Node identifier
	 */
	private Long id;

	/**
	 * Node latitude 
	 */
	private double latitude;

	/**
	 * Node longitude
	 */
	private double longitude;

	/**
	 * list of sections that start from the node
	 */
	private ArrayList<Sektion> sectionStart;
	
	private String nom;


	public Node() {
		this.sectionStart = new ArrayList<Sektion>();
	}

	/**
	 * 
	 * @param id               the node identifier
	 * @param latitude         node latitude
	 * @param longitude        node longitude
	 * @param sectionStart     list of sections that start from the node
	 */
	public Node(Long id, String nom, double latitude, double longitude, ArrayList<Sektion> sectionStart) {
		this.id = id;
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
		if (sectionStart != null)
			this.sectionStart = sectionStart;
		else
			this.sectionStart = new ArrayList<Sektion>();
	}

	/**
	 * 
	 * @param longi -  longitude
	 * @param lati - latitude
	 * @return the distance between the point (longi,lati) and the node position
	 */
	public double calculDistanceEuclidienne(double longi, double lati) {
		return Math.sqrt(Math.pow(lati - latitude, 2) + Math.pow(longi - longitude, 2));
	}

	/**
	 * add t to the list of sections
	 * 
	 * @param t - a new section
	 */
	public void addSektionStart(Sektion t) {
		this.sectionStart.add(t);
	}

	/**
	 * 
	 * @return node identifier
	 */
	public Long getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	/**
	 * 
	 * @param id - node identifier
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return node latitude 
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * 
	 * @param latitude -  node latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 
	 * @return node longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * 
	 * @param longitude longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 
	 * @return list of sections that start from the node
	 */
	public ArrayList<Sektion> getSectionStart() {
		return sectionStart;
	}

	/**
	 * 
	 * @param sectionStart - list of sections that start from the node
	 */
	public void setSectionStart(ArrayList<Sektion> sectionStart) {
		this.sectionStart = sectionStart;
	}

	/**
	 * display method for debugging
	 */
	public String toString() {
		String res = "Node :";
		res += ", id = " + this.id;
		res += ", latitude= " + this.latitude;
		res += ", longitude= " + this.longitude;
		return res;
	}
}
