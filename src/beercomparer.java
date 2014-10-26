import java.util.Comparator;


public class beercomparer implements Comparator<beer>{

	@Override
	public int compare(beer o1, beer o2) {
		// TODO Auto-generated method stub
		return o1.beername.compareTo(o2.beername);
	}

}
