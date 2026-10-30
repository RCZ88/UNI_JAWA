package NAR.levelUpNAR;

public abstract class HauntedObject extends MansionAsset{
	private int spookLevel;
	private boolean isDiscovered;
}


class JuggernautRelic extends HauntedObject implements Lootable{

	@Override
	void interaction() {
		// TODO Auto-generated method stub
		
	}

	
}

class ArcaneOilpainting extends HauntedObject implements Lootable{

	@Override
	void interaction() {
		// TODO Auto-generated method stub
		
	}

	
}


class NoiseHaunterRadio extends HauntedObject implements Hackable{

	@Override
	void interaction() {
		// TODO Auto-generated method stub
		
	}
	
}