package NAR.levelUpNAR;

import java.util.HashMap;

public abstract class MansionAsset {
	public static final HashMap<String, Class> ALL_ASSETS = new HashMap<>();
	public static final Class []ASSET_TYPES = new Class[] {
		AutomatedHVAC.class,
		EcoJetSprinkler.class,
		JuggernautRelic.class,
		ArcaneOilpainting.class,
		EntertainmentNode.class,
		NoiseHaunterRadio.class
	};
	
	private boolean hasCoin;
	
	
	
	public MansionAsset() {
		this.hasCoin = true;
	}
	
	public void removeCoin() {
		hasCoin = false;
	}

	public static void initItems() {
		for(Class c : ASSET_TYPES) {
			ALL_ASSETS.put(getAbbriv(c), c);
		}
	}
	
	abstract void interaction();
	
	public static <T extends MansionAsset> String getAbbriv(Class<T> clazz) {
		String cName = clazz.getName();
		StringBuilder sb = new StringBuilder();
		sb.append(cName.charAt(0));
		for(int i = 1; i<cName.length(); i++) {
			if(Util.isUpperCase(cName.charAt(i))) {
				sb.append(cName.charAt(i));
				break;
			};
		}
		return sb.toString();
	}
	
	public static <T extends MansionAsset> String getName(Class<T>clazz) {
		String cName = clazz.getName();
		StringBuilder sb = new StringBuilder();
		for(char letter : cName.toCharArray()) {
			if(Util.isUpperCase(letter)) {
				sb.append(" ");
			}
			sb.append(letter);
		}
		return sb.toString().substring(1, sb.length()-1);
	}
}
