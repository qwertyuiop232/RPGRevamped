package RPGRevamped;
import java.util.Random;

public class Battle {
	
	Random rand = new Random();
	private int damage, defense, speed, luck, crit, dodge, dmgDealt;
	
	public Battle() {

		damage = 0;
		defense = 0;
		speed = 0;
		luck = 0;
		crit = 0;
		dodge = 0;
		dmgDealt = 0;
	}
	public int getBattle(int strength, int defense, int speed, int luck) {
		this.damage = strength;
		this.defense = defense;
		this.speed = speed;
		this.luck = luck;
		setDmg();
		return dmgDealt;
		
	}
	private void setDmg() {
		dodge = rand.nextInt(101) + (int) (speed / 3.3);
		crit = rand.nextInt(101) + (int) (luck / 3.0);
		
		if(dodge > 93) {
			dmgDealt = 0;
		} else if(crit > 85) {
			dmgDealt = damage * 2;
		} else {
			dmgDealt = rand.nextInt(5)+ (int) (damage * (1 - defense / 200.0));
		}
		
	}

}
