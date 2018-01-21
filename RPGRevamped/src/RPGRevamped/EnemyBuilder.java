package RPGRevamped;

import java.util.Random;

public class EnemyBuilder {
	Random rand = new Random();
	
	private int eStrength, eHealth, eEXP, eLevel, eDefense, eSpeed, rarity, eLuck;
	
	public EnemyBuilder() {
		eStrength = 0;
		eHealth = 0;
		eEXP = 0;
		eLevel = 0;
		eDefense = 0;
		eSpeed = 0;
		rarity = 0;
		eLuck = 0;
	}
	private void setEStats(int level) {
		eLevel = rand.nextInt(5) + level - 5;
		if(eLevel <= 0)
			eLevel = 1;
		rarity = rand.nextInt(10) + 1; 
		eStrength =	(int) (eLevel * 0.6 * (rarity * 0.5 + 0.7));
		eEXP = rarity * eLevel * 7;
		eDefense = (int) (eLevel * 0.7 * (rarity * 0.9 + 0.7));
		eSpeed = (int) (eLevel * 0.8 * (rarity * 0.5 + .7));
		eHealth = (int) (eLevel * 5 * (rarity * 0.5 + 1.0));
		eLuck = (int) (eLevel * 0.7 * (rarity * 0.3 + .7));
	}
	public int[] getEStats(int level) {
		setEStats(level);
		int[] stats = {eLevel, eStrength, eEXP, eDefense, eSpeed, eHealth, eLuck};
		return stats;
	}
}
