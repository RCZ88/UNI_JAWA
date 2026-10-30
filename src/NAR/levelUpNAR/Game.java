package NAR.levelUpNAR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;

public class Game {
	private static final ArrayList<Floor>FLOORS = DataHelper.loadInterior();
	private static final Vector<String> FLOOR_NAMES = new Vector<String>();
	private static final Scanner sc = new Scanner(System.in);
	
	public static final void init_game() {
		FLOORS.forEach((f) -> FLOOR_NAMES.add(f.getFloorName()));
	}
	
	
	
	public static void handleRoomSelection() {
		System.out.println("Go To:");
		int count = 0;
		for(Floor floor : FLOORS) {
			System.out.printf("%d. %s", ++count, floor.getFloorName());
		}
		System.out.println("4. Exit");
		int input = Util.handleValidIntInput(1, 4, "Select Floor");
		
	}
	
	public static void handleRoom(int roomIndex) {
		String roomPrint = FLOORS.get(roomIndex).getViz();
		System.out.println(roomPrint + "\n\n");
		
		
		int count = 1;
		for(MansionAsset ma : FLOORS.get(roomIndex).getAssets()) {
			System.out.printf("%d [%s] %s\n", count++, MansionAsset.getAbbriv(ma.getClass()), MansionAsset.getName(ma.getClass()));
		}
		
	}
}
