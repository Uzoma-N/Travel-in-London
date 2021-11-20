


/**
 * @author Uzoma Nwiwu
 *
 */
public class Sektion {

	/**
	 * length in meters of the section
	 */
	private double length;

	/**
	 * name of the section
	 */
	private String nom;

	/**
	 * node from which the section starts
	 */
	private Node nodeOrigin;

	/**
	 * ode at which the section arrives
	 */
	private Node nodeDestination;

	/**
	 * @param aLength        length of the section in meters
	 * @param aNom          name of the section
	 * @param anOrigin     node from which the section starts
	 * @param aDestination node at which the section arrives
	 */
	public Sektion(double aLength, String aNom, Node anOrigin, Node aDestination) {

		this.length = aLength;
		this.nom = aNom;
		this.nodeOrigin = anOrigin;
		this.nodeDestination = aDestination;

	}
	
	public Sektion(String aNom, Node anOrigin, Node aDestination) {

		this.length = calculDistanceNodes(anOrigin, aDestination);
		this.nom = aNom;
		this.nodeOrigin = anOrigin;
		this.nodeDestination = aDestination;

	}

	/**
	 * @return length of the section in meters
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length of the section in meters
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * @return name of the section
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom - name of the section
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the node from which the section starts
	 */
	public Node getNodeOrigin() {
		return nodeOrigin;
	}

	/**
	 * @param nodeOrigin - the node from which the section starts
	 */
	public void setNodeOrigin(Node nodeOrigin) {
		this.nodeOrigin = nodeOrigin;
	}

	/**
	 * @return the node at which the section arrives
	 */
	public Node getNodeDestination() {
		return nodeDestination;
	}

	/**
	 * @param nodeDestination the node at which the section arrives
	 */
	public void setNodeDestination(Node nodeDestination) {
		this.nodeDestination = nodeDestination;
	}

	/**
	 * @param longi - angular longitude from which the distance is calculated
	 * @param lati  - angular latitude from which the distance is calculated
	 * @return  angular distance as the crow flies between the coordinates 
	 *         given in parameter and the middle of the section
	 */
	public double calculDistanceEuclidienne(double longi, double lati) {
		double latitude = (nodeOrigin.getLatitude() + nodeDestination.getLatitude()) / 2.0;
		double longitude = (nodeOrigin.getLongitude() + nodeDestination.getLongitude()) / 2.0;
		return Math.sqrt(Math.pow(lati - latitude, 2) + Math.pow(longi - longitude, 2));
	}
	
	public double calculDistanceNodes(Node aNode, Node otherNode) {
		double R = 6378.137; // Radius of earth in KM
		double lat1 = aNode.getLatitude();
		double lat2 = otherNode.getLatitude();
		double lon1 = aNode.getLongitude();
		double lon2 = otherNode.getLongitude();
		double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
		double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = R * c;
	    return d * 1000; // meters
	}

	/**
	 * @return description - concise section for user display
	 */
	public String afich() {
		return nom + ", " + "on " + length + "m";
	}

	/**
	 * display method for debugging
	 */
	public String toString() {
		String res = "Section{" + this.nodeOrigin.getNom() + "->" + this.nodeDestination.getNom() + " " + " Name = "
				+ this.nom + ", Length= " + this.length + " meters}";
		return res;
	}
}