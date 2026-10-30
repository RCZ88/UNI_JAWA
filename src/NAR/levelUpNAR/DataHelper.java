package NAR.levelUpNAR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class DataHelper {
	public static final Path MANSION_INTERIOR = Path.of("mansion_interior.txt");
	
	
	
	public static ArrayList<Floor> loadInterior() {
		try {
			ArrayList<String>lines = (ArrayList<String>) Files.readAllLines(MANSION_INTERIOR);
			HashMap<String, String>interiors = new HashMap<>();
			StringBuilder sb = new StringBuilder();
			String currentInterior = "";
			int floorIndex = 0;
			ArrayList<Floor>floors = new ArrayList<>();
			ArrayList<MansionAsset>assetOfFloor = new ArrayList<MansionAsset>();
			for(String line : lines) {
				if(line.charAt(0) == '#') {
					interiors.put(currentInterior, sb.toString());
					sb = new StringBuilder();
					Floor floor = new Floor(interiors.get(currentInterior), currentInterior);
					for(MansionAsset ma : assetOfFloor) {
						floor.addAsset(ma);
					}
					floors.set(floorIndex, floor);
				
					assetOfFloor.clear();
					floorIndex++;
					continue;
				}
				if(line.charAt(0) != '+') {
					currentInterior = line.substring(0, line.length()-2);
					continue;
				}
				sb.append(line).append("\n");
				String[]components = "[".split(line);
				ArrayList<String>abrivs = new ArrayList<String>();
				for(int i = 1; i<components.length; i++){
					String a= components[i].substring(0, 1);
					abrivs.add(a);
					assetOfFloor.add((MansionAsset)MansionAsset.ALL_ASSETS.get(a).newInstance());
				}
				
			}
			
			
			return floors;
		} catch (IOException | IllegalAccessException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        return new ArrayList<>();
	
	}
}
