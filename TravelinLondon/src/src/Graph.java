import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Uzoma Nwiwu
 *
 */
public class Graph {
	/**
	 * delivery request
	 */
	private DeliveryRequest deliveryRequest;

	/**
	 * matrix that contains the routes between the different delivery points
	 */
	private Route[][] matriceRoute;

	/**
	 * plan
	 */
	private Plan plan;

	/**
	 * 
	 * @param plan
	 * @param delivery request
	 */
	public Graph(Plan plan, DeliveryRequest deliveryRequest) {
		this.deliveryRequest = deliveryRequest;
		this.plan = plan;
		ArrayList<Node> nodes = new ArrayList<Node>(this.deliveryRequest.getDeliveryPoints());

		this.matriceRoute = new Route[nodes.size()][nodes.size()];

		for (int i = 0; i < nodes.size(); i++) {

			Node nodeCurrent = nodes.remove(i);
			Collection<Route> plusCourtsChemins = this.plan.calculerPlusCourtChemin(nodeCurrent, nodes);
			for (Route t : plusCourtsChemins) {
				int indexDepart = i;
				Long idNodeArrive = t.getNodeArrive().getId();
				int indexArrivee = getIndexOf(idNodeArrive);
				matriceRoute[indexDepart][indexArrivee] = t;
			}
			nodes.add(i, nodeCurrent);
		}
	}

	/**
	 * 
	 * @param id of a delivery point
	 * @return the index of the node corresponding to the id
	 */
	private int getIndexOf(Long id) {
	

		ArrayList<Node> nodes = new ArrayList<Node>(this.deliveryRequest.getDeliveryPoints());
		for (Node n : nodes) {
			if (n.getId() == id)
				return getIndexOf(n);
		}

		return -1;
	}

	/**
	 * 
	 * @param node
	 * @return index of node
	 */
	private int getIndexOf(Node node) {
		int index = this.deliveryRequest.getDeliveryPoints().indexOf(node);
		if (index != -1)
			return index;

	

		try {
			Node nodePlane = this.plan.searchNodeById(node.getId());
			return getIndexOf(nodePlane.getId());
		} catch (Exception e) {
		}

		return -1;
	}

	/**
	 * 
	 * @param i indice i
	 * @return node corresponding to the id
	 */
	public Node getNode(int i) {
		
		return this.deliveryRequest.getDeliveryPoints().get(i);

	}

	/**
	 * 
	 * @param Depart Node
	 * @param Arrival Node
	 * @return the path if it exists between the departure and arrival node, otherwise
	 *         null
	 */
	public Route getRoute(Node nodeDepart, Node nodeArrive) {
		int indexDepart = getIndexOf(nodeDepart);
		int indexArrivee = getIndexOf(nodeArrive);
		if (indexDepart < 0 || indexArrivee < 0)
			return null;

		return matriceRoute[indexDepart][indexArrivee];
	}

	/**
	 * 
	 * @param index of Departure
	 * @param index of Arrival
	 * @return path between an index node indexDepart and an index node
	 *         index of Arrival
	 */
	public Route getRoute(int indexDepart, int indexArrivee) {
		return matriceRoute[indexDepart][indexArrivee];
	}

	/**
	 * 
	 * @return path matrix
	 */
	public Route[][] getMatriceRoute() {
		return matriceRoute;
	}

	/**
	 * 
	 * @return delivery request
	 */
	public DeliveryRequest getDeliveryRequest() {
		return deliveryRequest;
	}

	/**
	 * 
	 * @return the plan
	 */
	public Plan getPlan() {
		return plan;
	}
}