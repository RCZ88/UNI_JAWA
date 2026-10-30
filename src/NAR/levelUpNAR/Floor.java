package NAR.levelUpNAR;

import java.util.ArrayList;

public class Floor {
	private ArrayList<MansionAsset>assets;
	private String viz;
	private String floorName;
	
	public Floor(String viz, String floorName) {
		assets = new ArrayList<>();
		this.viz  =viz;
		this.floorName = floorName;
	}
	
	
	
	public ArrayList<MansionAsset> getAssets() {
		return assets;
	}



	public void setAssets(ArrayList<MansionAsset> assets) {
		this.assets = assets;
	}



	public String getFloorName() {
		return floorName;
	}



	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}



	public String getViz() {
		return viz;
	}



	public void setViz(String viz) {
		this.viz = viz;
	}
	public void addAsset(MansionAsset asset) {
		this.assets.add(asset);
	}
}
