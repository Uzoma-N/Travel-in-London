


/**
 * @author Uzoma Nwiwu
 *
 */
public class Pair<F, S> {
	/**
	 * First object
	 */
	private F first;

	/**
	 * Second object
	 */
	private S second;

	/**
	 * 
	 * @param second - second object
	 */
	public void setSecond(S second) {
		this.second = second;
	}

	/**
	 * 
	 * @param first - first object
	 */
	public void setFirst(F first) {
		this.first = first;
	}

	/**
	 * 
	 * @param o1 -  first object
	 * @param o2 -  second object
	 */
	public Pair(F o1, S o2) {
		first = o1;
		second = o2;
	}

	/**
	 * 
	 * @return first object
	 */
	public F getFirst() {
		return first;
	}

	/**
	 * 
	 * @return second object
	 */
	public S getSecond() {
		return second;
	}
}