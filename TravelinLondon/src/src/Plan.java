import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.PriorityQueue;

/**
 * @author Uzoma Nwiwu
 *
 */

public class Plan extends Observable {
	/**
	 * set of nodes in the plane
	 */
	private HashMap<Long, Node> nodes;

	/**
	 * all sections of the plan
	 */
	private ArrayList<Sektion> sections;

	/**
	 * width of the plane
	 */
	int planewidth;

	/**
	 * height of the plane
	 */
	int planeheight;

	/**
	 * minimum latitude of the plane
	 */
	double latmin;

	/**
	 * maximum latitude of the plane
	 */
	double latmax;

	/**
	 * minimum longitude of plane
	 */
	double longmin;

	/**
	 * maximum longitude of plane
	 */
	double longmax;

	public Plan() {
	}

	/**
	 * 
	 * @param widthplane - width of plane
	 * @param heightplane - height of plane
	 */
	public Plan(int planewidth, int planeheight) {
		this.planewidth = planewidth;
		this.planeheight = planeheight;
	}

	/**
	 * 
	 * @return width of the plane
	 */
	public int getPlanewidth() {
		return planewidth;
	}

	/**
	 * 
	 * @param planewidth - width of the plane
	 */
	public void setPlanewidth(int planewidth) {
		this.planewidth = planewidth;
	}

	/**
	 * 
	 * @return height of the plane
	 */
	public int getPlaneheight() {
		return planeheight;
	}

	/**
	 * 
	 * @param planeheight height of the plane
	 */
	public void setPlaneheight(int planeheight) {
		this.planeheight = planeheight;
	}

	/**
	 * 
	 * @return minimum latitude 
	 */
	public double getLatmin() {
		return latmin;
	}

	/**
	 * 
	 * @param latmin - minimum latitude 
	 */
	public void setLatmin(double latmin) {
		this.latmin = latmin;
	}

	/**
	 * 
	 * @return minimum latitude 
	 */
	public double getLatmax() {
		return latmax;
	}

	/**
	 * 
	 * @param latmax - maximum latitude 
	 */
	public void setLatmax(double latmax) {
		this.latmax = latmax;
	}

	/**
	 * 
	 * @return minimum longitude
	 */
	public double getLongmin() {
		return longmin;
	}

	/**
	 * 
	 * @param longmin -  minimum longitude
	 */
	public void setLongmin(double longmin) {
		this.longmin = longmin;
	}

	/**
	 * 
	 * @return maximum longitude
	 */
	public double getLongmax() {
		return longmax;
	}

	/**
	 * 
	 * @param longmax -  minimum longitude
	 */
	public void setLongmax(double longmax) {
		this.longmax = longmax;
	}

	/**
	 * 
	 * @param unId id of the desired node
	 * @return 	   node corresponding to the id
	 * @throws     Exception
	 */
	public Node searchNodeById(Long unId) throws Exception {
		return nodes.get(unId);
	}

	/**
	 * 
	 * @param longi longitude
	 * @param lati  latitude
	 * @return 		node closest to the point (longi,lati)
	 */
	public Node getClosestNode(double longi, double lati) {
		Node n = null;
		double distanceMin = Double.MAX_VALUE;
		for (Map.Entry<Long, Node> mapentry : nodes.entrySet()) {
			double distanceTempo = mapentry.getValue()
					.calculDistanceEuclidienne(longi, lati);
			if (distanceTempo < distanceMin) {
				n = mapentry.getValue();
				distanceMin = distanceTempo;
			}
		}
		return n;
	}

	/**
	 * 
	 * @param longi longitude
	 * @param lati  latitude
	 * @return      the section closest to the point (longi,lati)
	 */
	public Sektion getTronconLePlusProche(double longi, double lati) {
		Sektion t = null;
		double distanceMin = Double.MAX_VALUE;
		for (Sektion tempo : sections) {
			double distanceTempo = tempo.calculDistanceEuclidienne(longi, lati);
			if (distanceTempo < distanceMin) {
				t = tempo;
				distanceMin = distanceTempo;
			}
		}
		return t;
	}

	/**
	 * 
	 * @param a distance
	 * @param b distance
	 * @return  -1 if a<b, 1 if a>b and 0 if a=b
	 */
	private int compareDistances(double a, double b) {
		if (a < b)
			return -1;
		else if (a > b)
			return 1;
		return 0;
	}

	/**
	 * 
	 * @param nodeDepart   starting node
	 * @param nodesArrive  list of arrival nodes
	 * @return 			   list of routes from the starting node to the different nodes
	 *        				of arrivals
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Route> calculerPlusCourtChemin(Node nodeDepart,
											ArrayList<Node> nodesArrive) {
		// Return value
		ArrayList<Route> ensembleDijsktraaNodes = new ArrayList<Route>();
		// Number of vertices
		int numberSummits = nodes.size();
		// Nodes from 0 to n-1 according to the index
		boolean summitsBlack[] = new boolean[numberSummits]; 
		Arrays.fill(summitsBlack, false);
		// Table d filled with infinity distance
		double distance[] = new double[numberSummits];
		Arrays.fill(distance, Double.MAX_VALUE);
		/*
		 * Table of predecessors filled with an int that does not
		 * reference a summit
		 */
		int predecessor[] = new int[numberSummits];
		Arrays.fill(predecessor, numberSummits + 1);
		HashMap<Integer, ArrayList<Sektion>> section =
			new HashMap<Integer, ArrayList<Sektion>>();
		// tableCorrespondanceIdSommit : we know the id, we want the top
		HashMap<Long, Integer> tableOfCorrespondenceIdSummit =
			new HashMap<Long, Integer>();
		 // tableCorrespondanceSommitId : we know the top, we want the id
		HashMap<Integer, Long> tableDeCorrespondanceSommetId =
			new HashMap<Integer, Long>();
		/*
		 * priority queue that sorts the values according to
		 * distances in the table
		 */
		PriorityQueue<Integer> fileOfPriority =
			new PriorityQueue<Integer>(numberSummits,
					(a, b) -> compareDistances(distance[a], distance[b]));
		
		int correspondance = 0;
		for (Map.Entry<Long, Node> entry : nodes.entrySet()) {
			Long idTempo = entry.getKey(); // build the correspondence tables
			Integer summitTempo = new Integer(correspondance);
			tableOfCorrespondenceIdSummit.put(idTempo, summitTempo);
			tableDeCorrespondanceSommetId.put(summitTempo, idTempo);
			correspondance++;

		}
		Long idNodeDepart = nodeDepart.getId();
		fileOfPriority.add(tableOfCorrespondenceIdSummit.get(idNodeDepart));
		distance[tableOfCorrespondenceIdSummit.get(idNodeDepart)] = 0;
		section.put(tableOfCorrespondenceIdSummit.get(idNodeDepart),
													  new ArrayList<Sektion>());
		/*
		 * obtain a hash table with for key the id of the Node 
		 * and for value the index in the graph
		 */

		while (fileOfPriority.size() != 0) {
			// extract the nearest vertex, thanks to the prio
			Integer summitCurrent = fileOfPriority.poll();
			// get the distance of this node
			double distanceNode = distance[summitCurrent];
			
			if (!summitsBlack[summitCurrent]) { // if the node is black we pass
				/*
				 * We define the current node as black = finished  
				 * at the end of the iteration
				 */
				summitsBlack[summitCurrent] = true;

				try {

					for (Sektion t : searchNodeById(
							tableDeCorrespondanceSommetId.get(summitCurrent))
								.getSectionStart()) {
						/*
						 * go through the trunks of the current node to 
						 * get the destination nodes and the lengths
						 */
						// recover destination
						Node voisin = t.getNodeDestination();
						// retrieve its summit number
						Integer numeroSommetVoisin =
								tableOfCorrespondenceIdSummit
									.get(voisin.getId());
						/*
						 * We calculate the distance (length of the trunk + distance 
						 * of the current node (which is also the origin node)
						 */
						double distanceVoisin = distanceNode + t.getLength();
						/*
						 * If the distance is smaller we update and push the values
						 * 
						 */
						if (distanceVoisin < distance[numeroSommetVoisin]) {
							distance[numeroSommetVoisin] = distanceVoisin;
							predecessor[numeroSommetVoisin] = summitCurrent;
							fileOfPriority.add(numeroSommetVoisin);
							section.put(numeroSommetVoisin,
										(ArrayList<Sektion>) section
											.get(summitCurrent).clone());
							section.get(numeroSommetVoisin).add(t);

						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		/*
		 * At the end of the loop, distance has the distances from the starting vertex 
		 * to all other vertices, and predecessor allows to follow the path 
		 * from the origin node to the desired node.
		 */

		for (int i = 0; i < nodesArrive.size(); i++) {
			/*
			 * browse the nodesArrival and retrieve the corresponding vertex 
			 * to get the list of trunk of the vertex, then we create the path
			 */
			Node nodeCurrent = nodesArrive.get(i);
			Long idCurrent = nodeCurrent.getId();
			Integer sommetCorrespondant = tableOfCorrespondenceIdSummit
										  .get(idCurrent);
			ArrayList<Sektion> listSectionCurrent = section
					.get(sommetCorrespondant);
			ensembleDijsktraaNodes.add(new Route(listSectionCurrent));
		}

		return ensembleDijsktraaNodes;
	}

	/**
	 * 
	 * @return  collection of nodes
	 */
	public HashMap<Long, Node> getCollectionNodes() {
		return nodes;
	}

	/**
	 * 
	 * @param nodes - collection of nodes
	 */
	public void setCollectionNodes(HashMap<Long, Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * 
	 * @return list of sections
	 */
	public ArrayList<Sektion> getCollectionTroncons() {
		return sections;
	}

	/**
	 * 
	 * @param sections -  list of sections
	 */
	public void setCollectionTroncons(ArrayList<Sektion> sections) {
		this.sections = sections;
		setChanged();
		notifyObservers();
	}

	/**
	 * display method for debugging
	 */
	@Override
	public String toString() {
		return "Plan [nodes=" + nodes + ", sections=" + sections + "]";
	}

}