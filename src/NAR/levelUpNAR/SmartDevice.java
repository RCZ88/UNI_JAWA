package NAR.levelUpNAR;

public abstract class SmartDevice extends MansionAsset{
	private int powerWatt;
	private boolean isOn;
	
	
}

class AutomatedHVAC extends SmartDevice implements Schedulable{
	
	@Override
	void interaction() {
		// TODO Auto-generated method stub
		
	}
	
}

class EcoJetSprinkler extends SmartDevice implements Schedulable{

	@Override
	void interaction() {
		// TODO Auto-generated method stub
		
	}
	
}

class EntertainmentNode  extends SmartDevice implements Hackable{

	@Override
	void interaction() {
		// TODO Auto-generated method stub
		
	}
	
}


