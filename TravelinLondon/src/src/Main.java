import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Uzoma Nwiwu
 *
 */
public class Main {
	
	public static void main(String[] args) {
		ArrayList<Node> NodeSet = new ArrayList<Node>();
		Plan LondonPlan = new Plan(6000, 6000);
		
		Node BakerStreet = new Node((long) 0, "BakerStreet", 51.5231577,-0.1590517, null);
		NodeSet.add(BakerStreet);
		Node KingsCrossStreet = new Node((long) 1,"KingsCrossStreet", 51.5342603,-0.1288248, null);
		NodeSet.add(KingsCrossStreet);
		Node Moorgate = new Node((long) 2,"Moorgate", 51.5185151,-0.0902714, null);
		NodeSet.add(Moorgate);
		Node BankStreet = new Node((long) 3,"BankStreet", 51.5039803,-0.0236612, null);
		NodeSet.add(BankStreet);
		Node OxfordCircus = new Node((long) 4,"OxfordCircus", 51.5152117,-0.144044, null);
		NodeSet.add(OxfordCircus);
		Node ChanceryLane = new Node((long) 5,"ChanceryLane", 51.5184956,-0.1141648, null);
		NodeSet.add(ChanceryLane);
		Node CoventGarden = new Node((long) 6,"CoventGarden", 51.5130007,-0.1263508, null);
		NodeSet.add(CoventGarden);
		Node PicadillyCircus = new Node((long) 7,"PicadillyCircus", 51.510121,-0.136357, null);
		NodeSet.add(PicadillyCircus);
		Node GreenPark = new Node((long) 8,"GreenPark", 51.50674,-0.1450359, null);
		NodeSet.add(GreenPark);
		Node Monument = new Node((long) 9,"Monument", 51.5108828,-0.0887974, null);
		NodeSet.add(Monument);
		
		HashMap<Long, Node> hm = new HashMap<Long, Node>();
		for (Node n : NodeSet) {
			hm.put(n.getId(), n);
		}
		LondonPlan.setCollectionNodes(hm);
		
		Sektion BakerStreetKingsCrossStreet = new Sektion("BakerStreetKingsCrossStreet", BakerStreet, KingsCrossStreet);
		BakerStreet.addSektionStart(BakerStreetKingsCrossStreet);		
		
		Sektion KingsCrossStreetBakerStreet = new Sektion("KingsCrossStreetBakerStreet", KingsCrossStreet, BakerStreet);
		KingsCrossStreet.addSektionStart(KingsCrossStreetBakerStreet);
		
		Sektion KingsCrossStreetMoorgate = new Sektion("KingsCrossStreetMoorgate", KingsCrossStreet, Moorgate);
		KingsCrossStreet.addSektionStart(KingsCrossStreetMoorgate);
		
		Sektion MoorgateKingsCrossStreet = new Sektion("MoorgateKingsCrossStreet", Moorgate, KingsCrossStreet);
		Moorgate.addSektionStart(MoorgateKingsCrossStreet);
		
		Sektion MoorgateBankStreet = new Sektion("MoorgateBankStreet", Moorgate, BankStreet);
		Moorgate.addSektionStart(MoorgateBankStreet);
		
		Sektion BankStreetMoorgate = new Sektion("BankStreetMoorgate", BankStreet, Moorgate);
		BankStreet.addSektionStart(BankStreetMoorgate);
		
		Sektion MoorgateChanceryLane = new Sektion("MoorgateChanceryLane", Moorgate, ChanceryLane);
		Moorgate.addSektionStart(MoorgateChanceryLane);
		
		Sektion ChanceryLaneMoorgate = new Sektion("ChanceryLaneMoorgate", ChanceryLane, Moorgate);
		ChanceryLane.addSektionStart(ChanceryLaneMoorgate);
		
		Sektion ChanceryLaneKingsCrossStreet = new Sektion("ChanceryLaneKingsCrossStreet", ChanceryLane, KingsCrossStreet);
		ChanceryLane.addSektionStart(ChanceryLaneKingsCrossStreet);
		
		Sektion KingsCrossStreetChanceryLane = new Sektion("KingsCrossStreetChanceryLane", KingsCrossStreet, ChanceryLane);
		KingsCrossStreet.addSektionStart(KingsCrossStreetChanceryLane);
		
		Sektion OxfordCircusChanceryLane = new Sektion("OxfordCircusChanceryLane", OxfordCircus, ChanceryLane);
		OxfordCircus.addSektionStart(OxfordCircusChanceryLane);
		
		Sektion ChanceryLaneOxfordCircus = new Sektion("ChanceryLaneOxfordCircus", ChanceryLane, OxfordCircus);
		ChanceryLane.addSektionStart(ChanceryLaneOxfordCircus);
		
		Sektion OxfordCircusBakerStreet = new Sektion("OxfordCircusBakerStreet", OxfordCircus, BakerStreet);
		OxfordCircus.addSektionStart(OxfordCircusBakerStreet);
		
		Sektion BakerStreetOxfordCircus = new Sektion("BakerStreetOxfordCircus", BakerStreet, OxfordCircus);
		BakerStreet.addSektionStart(BakerStreetOxfordCircus);
		
		Sektion OxfordCircusCoventGarden = new Sektion("OxfordCircusCoventGarden", OxfordCircus, CoventGarden);
		OxfordCircus.addSektionStart(OxfordCircusCoventGarden);
		
		Sektion CoventGardenOxfordCircus = new Sektion("CoventGardenOxfordCircus", CoventGarden, OxfordCircus);
		CoventGarden.addSektionStart(CoventGardenOxfordCircus);
		
		Sektion ChanceryLaneCoventGarden = new Sektion("ChanceryLaneCoventGarden", ChanceryLane, CoventGarden);
		ChanceryLane.addSektionStart(ChanceryLaneCoventGarden);
		
		Sektion CoventGardenChanceryLane = new Sektion("ChanceryLaneCoventGarden", CoventGarden, ChanceryLane);
		CoventGarden.addSektionStart(CoventGardenChanceryLane);
		
		Sektion MonumentCoventGarden = new Sektion("MonumentCoventGarden", Monument, CoventGarden);
		Monument.addSektionStart(MonumentCoventGarden);
		
		Sektion CoventGardenMonument = new Sektion("CoventGardenMonument", CoventGarden, Monument);
		CoventGarden.addSektionStart(CoventGardenMonument);
		
		Sektion MonumentBankStreet = new Sektion("MonumentBankStreet", Monument, BankStreet);
		Monument.addSektionStart(MonumentBankStreet);
		
		Sektion BankStreetMonument = new Sektion("BankStreetMonument", BankStreet, Monument);
		BankStreet.addSektionStart(BankStreetMonument);
		
		Sektion PicadillyCircusCoventGarden = new Sektion("PicadillyCircusCoventGarden", PicadillyCircus, CoventGarden);
		PicadillyCircus.addSektionStart(PicadillyCircusCoventGarden);
		
		Sektion CoventGardenPicadillyCircus = new Sektion("CoventGardenPicadillyCircus", CoventGarden, PicadillyCircus);
		CoventGarden.addSektionStart(CoventGardenPicadillyCircus);
	
		Sektion PicadillyCircusGreenPark = new Sektion("PicadillyCircusGreenPark", PicadillyCircus, GreenPark);
		PicadillyCircus.addSektionStart(PicadillyCircusGreenPark);
		
		Sektion GreenParkPicadillyCircus = new Sektion("GreenParkPicadillyCircus", GreenPark, PicadillyCircus);
		GreenPark.addSektionStart(GreenParkPicadillyCircus);
		
		DeliveryRequest ddl = new DeliveryRequest(NodeSet);
		Graph SectionsMatrix = new Graph(LondonPlan, ddl);
		
		for (int i = 0; i<SectionsMatrix.getMatriceRoute().length; i++) {
			//for(int j = 0; j<SectionsMatrix.getMatriceRoute()[i].length;j++) {
				if (SectionsMatrix.getMatriceRoute()[i][3] != null) { 
				System.out.println(SectionsMatrix.getMatriceRoute()[i][3].toString());
				}
			//}
		}
		
	}
}
