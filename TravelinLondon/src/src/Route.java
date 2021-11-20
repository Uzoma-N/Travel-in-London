import java.util.ArrayList;


/**
 * @author Uzoma Nwiwu
 *
 */

public class Route {
	/**
	 * node from which the path starts
	 */
	final private Node nodeDepart;

	/**
	 * the node at which the path arrives
	 */
	final private Node nodeArrive;

	/**
	 * length of the trip in meters
	 */
	final private double length;

	/**
	 * the ordered list of the sections to be taken to go from 
	 * nodeDepart to nodeArive
	 */
	final private ArrayList<Sektion> listSection;

	/**
	 * @param listSection the ordered list of the sections to be taken to 
	 *                         go from nodeDepart to nodeArive
	 */
		
	public Route(ArrayList<Sektion> listSection) {
		this.nodeDepart = listSection.get(0).getNodeOrigin();
		this.nodeArrive = listSection.get(listSection.size() - 1).getNodeDestination();
		this.listSection = listSection;
		double lengthTemp = 0;
		for (Sektion t : listSection) {
			lengthTemp += t.getLength();
		}
		this.length = lengthTemp;
	}

	/**
	 * @param longi - angular longitude from which the distance is calculated
	 * @param lati  - angular latitude from which the distance is calculated
	 * @return the angular distance as the crow flies between the coordinates 
	 *         given as a parameter and the middle of the section of the path 
	 *         closest to the coordinates given as a parameter
	 */
	public double calculDistanceEuclidienne(double longi, double lati) {
		double distanceMin = Double.MAX_VALUE;
		for (Sektion t : listSection) {
			double distanceTempo = t.calculDistanceEuclidienne(longi, lati);
			if (distanceTempo < distanceMin) {
				distanceMin = distanceTempo;
			}
		}

		return distanceMin;
	}

	/**
	 * @return node from which the path starts
	 */
	public Node getNodeDepart() {
		return nodeDepart;
	}

	/**
	 * @return node to which the path arrives
	 */
	public Node getNodeArrive() {
		return nodeArrive;
	}

	/**getNodeArrive  getNodeArrive
	 * @return the ordered list of the sections to be taken 
	 *         to go from nodeDepart to nodeArivee
	 */
	public ArrayList<Sektion> getListSection() {
		return listSection;
	}

	/**
	 * @return the length of the trip in meters
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @return the travel time in milliseconds
	 */
	public Long getTimeCourse() {
		return new Long((long) (1000. * (this.length / DeliveryRequest.VITESSE_LIVREUR_M_PAR_S)));
	}

	/**
	 * @return concise description of a trip for user display
	 */
	public String afich() {
		String texte = " via a length route " + length + " m\n";
		texte += "<ul>";
		for (Sektion t : listSection) {
			texte += "<li>";
			texte += t.afich() + "\n";
			texte += "</li>";
		}
		texte += "</ul>";

		return texte;
	}

	/**
	 * display method for debugging
	 */
	public String toString() {

		String renvoi = "Route{" + nodeDepart.getNom() + "->" + nodeArrive.getNom() + " : " + length + " meters by "
				+ listSection.size() + " sections[";
		for (Sektion t : listSection)
			renvoi += t.toString();
		return renvoi;
	}
}