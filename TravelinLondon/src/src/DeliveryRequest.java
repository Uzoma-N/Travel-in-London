import java.util.ArrayList;
import java.util.Collections;
//import java.util.Collections;
/**
 * @author Uzoma Nwiwu
 *
 */
import java.util.Observable;

public class DeliveryRequest extends Observable {
	/**
	 * speed of the deliverer in km/h (15km/h)
	 */
	public static final double VITESSE_LIVREUR_M_PAR_S = 15. * 1000. / 3600.;

	/**
	 * warehouse
	 */
	/**
	 * list of delivery points
	 */
	private ArrayList<Node> deliveryPoints;

	/**
	 * number of deliverers
	 */

	/**
	 * 
	 * @param unEntrepot           warehouse
	 * @param desDeliveryPoints list of delivery points
	 * @param aNumberOfDeliverers
	 */

	/**
	 * Builder with the number of deliverers set to 1 by default
	 * 
	 * @param unEntrepot           warehouse
	 * @param desDeliveryPoints list of delivery points
	 */
	public DeliveryRequest(ArrayList<Node> desDeliveryPoints) {
		this.deliveryPoints = desDeliveryPoints;
	}

	/**
	 * Default constructor
	 */
	public DeliveryRequest() {
		this.deliveryPoints = new ArrayList<Node>();
	}

	/**
	 * 
	 * @return warehouse
	 */


	/**
	 * 
	 * @param entrepot warehouse
	 */


	/**
	 * 
	 * @return list of delivery points
	 */
	public ArrayList<Node> getDeliveryPoints() {
		return deliveryPoints;
	}

	/**
	 * 
	 * @param deliveryPoints list of delivery points
	 */
	public void setDeliveryPoints(ArrayList<Node> deliveryPoints) {
		this.deliveryPoints = deliveryPoints;
		setChanged();
		notifyObservers();
	}

	/**
	 * 
	 * @param index index of the delivery to be selected
	 * @param sel   true if the delivery point must be selected, false otherwise
	 */

	/**
	 * 
	 * @param sel true if the warehouse must be selected, false otherwise
	 */


	/**
	 * 
	 * @return number of deliverers
	 */


	/**
	 * 
	 * @param nombreLivreurs - number of deliverers
	 */
	
	/**
	 * 
	 * @param pdl the delivery point to be deleted
	 */


	/**
	 * 
	 * @param pdl point of delivery
	 * @return  the position of the delivery point in the delivery request
	 */
	
	public int getIndexNode(Node pdl) {
		for (int i = 0; i < deliveryPoints.size(); i++) {
			if (deliveryPoints.get(i).getId().equals(pdl.getId()))
				return i;
		}
		return -1;
	}

	/**
	 * 
	 * @param longi
	 * @param lati
	 * @return closest delivery point to the point (longi,lati)
	 */
	public Node getClosestNode(double longi, double lati) {
		Node pdl = null;
		double distanceMin = Double.MAX_VALUE;
		for (Node tempo : deliveryPoints) {
			double distanceTempo = tempo.calculDistanceEuclidienne(longi, lati);
			if (distanceTempo < distanceMin) {
				pdl = tempo;
				distanceMin = distanceTempo;
			}
		}
		return pdl;
	}

	/**
	 * 
	 * @param node
	 * @return p delivery point corresponding to the node
	 */
	public Node getNodeByNode(Node node) {
		for (Node p : deliveryPoints) {
			if (p.getId().equals(node.getId()))
				return p;
		}
		return null;
	}

	/**
	 * Sorts the list of delivery points by time of arrival
	 * 
	 * @param list of delivery points
	 */
	

	/**
	 * @param pdl1
	 * @param pdl2
	 * @return the value 0 if both delivery points have the same
	 *         delivery time, a value less than 0 if pdl1 has a time 
	 *         before pdl2, a value greater than 0 otherwise
	 */


	/**
	 * display method for debugging
	 */
	public String toString() {
		String res = "DeliveryRequest{\n";
		res += "  deliveryPoints = [";
		for (Node p : this.deliveryPoints)
			res += "\n    " + p;
		res += "\n  ]\n";
		res += "}";
		return res;
	}

}
