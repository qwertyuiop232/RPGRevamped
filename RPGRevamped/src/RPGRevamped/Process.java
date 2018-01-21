package RPGRevamped;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Process {
	static EnemyBuilder enemy = new EnemyBuilder();
	static Battle battle = new Battle();
	static boolean isEAlive = false;
	static boolean hasTrueSight = false;
	static Random rand = new Random();

	static int level, hp, totHP, mana, totMana, exp, lvlEXP, gold, strength, defense, speed, intellect, luck, points, defenseCounter, pseudoDefense;
	static int eLevel, eHealth, eTotHealth, eStrength, eSpeed, eLuck, eEXP, eGold, eDefense, dmgDealt, turnCounter, eMana, trueSight;
	int[] userStats;
	
	
	public static void setStart() {	
		// Setting initial outputs
		hasTrueSight = false;
		trueSight = 0;
		isEAlive = false;
		turnCounter = 0;
		ConstructGUI.advance.setText("ADVANCE");
		ConstructGUI.outputText1.setText("");
		ConstructGUI.outputText2.setText("");
		ConstructGUI.outputText3.setText("");
		level = 1;
		hp = 20;
		totHP = 20;
		mana = 10;
		totMana = 10;
		exp = 0;
		lvlEXP = 1;
		gold = 50;
		strength = 1;
		defense = 1;
		speed = 1;
		intellect = 1;
		luck = 1;
		eGold = 0;
		points = 5;
		eHealth = 0;
		eTotHealth = 0;
		eLevel = 0;
		eStrength = 0;
		eSpeed = 0;
		eLuck = 0;
		eDefense = 0;
		pseudoDefense = 0;
		defenseCounter = 5;
		eMana = 0;
		setLVLEXP();
		setCharStats();
		setEStats();
	}

	public static void setAdvance() {
		if(turnCounter % 2 == 0) {
			// creating new enemy 
			if (isEAlive == false) {
				isEAlive = true;
				int[] eStats = enemy.getEStats(level);
				// 	int[] stats = {eLevel, eStrength, eEXP, eDefense, eSpeed, eHealth};
				eLevel = eStats[0];
				eStrength = eStats[1];
				eEXP = eStats[2];
				eDefense = eStats[3];
				eSpeed = eStats[4];
				eTotHealth = eStats[5];
				eLuck = eStats[6];
				eHealth = eTotHealth;
				eMana = 10;
				setEStats();
				ConstructGUI.advance.setText("ATTACK");
				ConstructGUI.outputText3.setText("");
				ConstructGUI.outputText1.setText("");
				ConstructGUI.outputText2.setText("");
				ConstructGUI.outputText4.setText("");
				ConstructGUI.outputText5.setText("");

			}else {
				dmgDealt = battle.getBattle(strength, eDefense, eSpeed, luck);
				eHealth -= dmgDealt;
				ConstructGUI.outputText1.setText("You dealt " + dmgDealt + " dmg");
				playerTurn();

				}
			setCharStats();
			setEStats();
		}
	}
	public static void setFlee() {
		if(isEAlive && turnCounter % 2 == 0) {
			turnCounter += 2;
			eTotHealth = 0;
			isEAlive = false;
			setEStats();
			ConstructGUI.outputText3.setText("You have fleed");
		} else {
			ConstructGUI.outputText3.setText("Nowhere to run...");
		}
	}
	public static void setAbility1() {
		if(turnCounter % 2 == 0 && isEAlive && mana >= (int) (0.35 * totMana)) {
			mana -= 0.35 * totMana;
			dmgDealt = (int) (intellect * 2.5) ;
			eHealth -= dmgDealt;
			ConstructGUI.outputText1.setText("You dealt " + dmgDealt + " magic dmg");
			
			playerTurn();
			setCharStats();
			setEStats();			
		} else if(mana < (int) (0.35 * totMana)) {
			ConstructGUI.outputText1.setText("Insufficient mana");
			ConstructGUI.outputText2.setText("");
			ConstructGUI.outputText3.setText("");
			ConstructGUI.outputText4.setText("");
			ConstructGUI.outputText5.setText("");

		}
	}
	public static void setAbility2() {
		if(mana >= 7 && turnCounter % 2 == 0) {
			mana -= 7;
			defenseCounter = 0;
			pseudoDefense = 5 + (int) (intellect / 4.0);
			defense += pseudoDefense;
			ConstructGUI.outputText1.setText("");
			ConstructGUI.outputText2.setText("");
			ConstructGUI.outputText3.setText("Defense increased by " + pseudoDefense);
		}else if(mana < 7) {
			ConstructGUI.outputText1.setText("Insufficient mana");
			ConstructGUI.outputText2.setText("");
			ConstructGUI.outputText3.setText("");
			ConstructGUI.outputText4.setText("");
			ConstructGUI.outputText5.setText("");
		}else {
			ConstructGUI.outputText3.setText("No enemy");
		}
		setCharStats();
		setEStats();
	}
	public static void setAbility2(boolean done) {
		defense -= pseudoDefense;
	}

	public static void setAbility3() {
		if(mana >= 4 && turnCounter % 2 == 0) {
			mana -= 4;
			hp += (int) (intellect / 2.0 * (Math.random() * 4.0 + 3));
			if (hp > totHP)
				hp = totHP;
			
			setCharStats();
		} else if(mana < 4) {
			ConstructGUI.outputText1.setText("Insufficient mana");
			ConstructGUI.outputText2.setText("");
			ConstructGUI.outputText3.setText("");
			ConstructGUI.outputText4.setText("");
			ConstructGUI.outputText5.setText("");

		}
	}
	public static void assign(int selectedIndex) {
		
		if (points > 0 && turnCounter % 2 == 0){
			points--;
			switch (selectedIndex) {
			case 1: strength++;
			break;
			case 2: totHP += 10 + (int) Math.pow(level, 1.1);
			break;
			case 3: speed++;
			break;
			case 4: luck++;
			break;
			case 5: intellect++;
			break;
			case 6: defense++;
			break;
			default: points++;
			break;
			}
			if (selectedIndex == 2) {
				hp += 10 + (int) Math.pow(level, 1.1);
			}
			if (selectedIndex == 5) {
				totMana += 5 + (int) Math.pow(level, 1.5);
				mana += 5 + (int) Math.pow(level, 1.5);
			}
		} else if(points < 0) {
			ConstructGUI.outputText1.setText("Insufficient stat points");
			ConstructGUI.outputText2.setText("");
			ConstructGUI.outputText3.setText("");
			ConstructGUI.outputText4.setText("");
			ConstructGUI.outputText5.setText("");

		}
		setCharStats();
	}
	public static void buy(int selectedIndex) {
		// {"(10g) Mana Potion", "(10g) Health Potion", "(50g)Strength Seed", "(50g)Vitality Potion",
		// "(20g)Speed Potion", "(50g)Book",
		// "(10g)Bomb(active when bought)", "(30g)Armor"};
		int fakeGold = gold;
		switch (selectedIndex) {
		case 0: fakeGold -= 10;
		break;
		case 1: fakeGold -= 10;
		break;
		case 2: fakeGold -= 50;
		break;
		case 3: fakeGold -= 50;
		break;
		case 4: fakeGold -= 20;
		break;
		case 5: fakeGold -= 50;
		break;
		case 6: fakeGold -= 10;
		break;
		case 7: fakeGold -= 30;
		break;
		case 8: fakeGold -= 400;
		}
		if(fakeGold >= 0) {
			gold = fakeGold;
			ConstructGUI.outputText1.setText("");
			ConstructGUI.outputText2.setText("");
			ConstructGUI.outputText3.setText("Purchase Successful");
			ConstructGUI.outputText4.setText("");
			ConstructGUI.outputText5.setText("");	
			switch (selectedIndex) {
			case 0: mana = totMana;
			break;
			case 1: hp = totHP;
			break;
			case 2: strength++;
			break;
			case 3: totHP += 25 ;
			break;
			case 4: speed++;
			break;
			case 5: intellect++;
			break;
			case 6: bombEnemy();
			break;
			case 7: defense++;
			break;
			case 8: setTrueSight();
			break;
			
			}
			setEStats();
			setCharStats();
		} else {
			ConstructGUI.outputText1.setText("Insufficient funds");
			ConstructGUI.outputText2.setText("");
			ConstructGUI.outputText3.setText("");
			ConstructGUI.outputText4.setText("");
			ConstructGUI.outputText5.setText("");			
		}
	}

	private static void setTrueSight() {
		if(hasTrueSight) {
			ConstructGUI.outputText3.setText("Already Purchased");
			gold += 400;
		}
		hasTrueSight = true;
		trueSight = 1;
		setEStats();
		setCharStats();
	}

	private static void bombEnemy() {
		if(isEAlive) {
			eHealth -= (eTotHealth * 0.2);
			if(eHealth <= 0) {
				eHealth = 0;
				isEAlive = false;
				setLVLEXP();
				ConstructGUI.advance.setText("ADVANCE");
				ConstructGUI.outputText2.setText("Enemy has been defeated");
				setEStats();
				setCharStats();
			}
		} else {
			ConstructGUI.outputText3.setText("There is no enemy");
			ConstructGUI.outputText1.setText("");
			ConstructGUI.outputText2.setText("");
			ConstructGUI.outputText4.setText("");
			ConstructGUI.outputText5.setText("");
			gold += 10;
		}
	}

	public static void setLVLEXP() {
		// exp gained 1-10 * level * 7
		if(eMana > 0 && eHealth == 0) {
			exp += eEXP;
			eGold = rand.nextInt(10) + 1;
			gold += eGold;
			ConstructGUI.outputText3.setText(eGold + " gold dropped");
			ConstructGUI.outputText4.setText(eEXP + " exp gained");
			eMana = 0;
		}
		lvlEXP = (int) Math.pow(level, 3);
		if(exp > lvlEXP) {
			ConstructGUI.outputText5.setText("Level up!!!");
			level++;
			points += 10;
			totHP += 25;
			speed += 2;
			defense += 2;
			intellect += 2;
			strength += 2;
			totMana += 10;
			mana = totMana;
			hp = totHP;
			setLVLEXP();
		}
		
	}
	public static void setSave() {
		if(turnCounter % 2 == 0) {
			try {   
	        	FileOutputStream fileStream = new FileOutputStream("saveFile");   
	        	ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
	        	int[] saveState = {level, hp, totHP, mana, totMana, exp, gold, strength, defense, 
	        			speed, intellect, luck, points, eHealth, eTotHealth, eSpeed, eDefense, eEXP, eStrength,
	        			defenseCounter, pseudoDefense, turnCounter, eLevel, trueSight};
	        	objectStream.writeObject(saveState);
	        	ConstructGUI.outputText3.setText("Saved");
				ConstructGUI.outputText1.setText("");
				ConstructGUI.outputText2.setText("");
				ConstructGUI.outputText4.setText("");
				ConstructGUI.outputText5.setText("");

	        	objectStream.close();   
	        	fileStream.close();   

	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		}
		
	}
	public static void setLoad() {
		
		int[] load;
		
		try {
			FileInputStream fileStream = new FileInputStream("saveFile");
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
			load = (int[]) objectStream.readObject();
				level = load[0];
				hp = load[1];
				totHP = load[2];
				mana = load[3];
				totMana = load[4];
			    exp = load[5];
			    gold = load[6];
			    strength = load[7];
			    defense = load[8];
			    speed = load[9];
			    intellect = load[10];
			    luck = load[11];
			    points = load[12];
			    eHealth = load[13];
			    defenseCounter = load[19];
			    pseudoDefense = load [20];
			    turnCounter = load[21];
			    trueSight = load[23];
			    if(eHealth != 0) {
			    	isEAlive = true;
				    eTotHealth = load[14];
				    eSpeed = load[15];
				    eDefense = load[16];
				    eEXP = load[17];
				    eStrength = load[18];
				    eLevel = load[22];
			    } else {
			    	eMana = 0;
			    	isEAlive = false;
			    	eTotHealth = 0;
			    	ConstructGUI.advance.setText("ADVANCE");
			    }
			    
				ConstructGUI.outputText3.setText("Loaded");
			    fileStream.close();
			    objectStream.close();
		} catch(Exception e) {
				e.printStackTrace();
		}
		ConstructGUI.outputText1.setText("");
		ConstructGUI.outputText2.setText("");
		ConstructGUI.outputText4.setText("");
		ConstructGUI.outputText5.setText("");
		if(trueSight == 1) {
			hasTrueSight = true;
		}
		setLVLEXP();
		setCharStats();
		setEStats();
	}
	public static void setCharStats() {
		ConstructGUI.level.setText("Level: " + level);
		ConstructGUI.health.setText("Health: " + hp + "/" + totHP);
		ConstructGUI.exp.setText("EXP: " + exp + "/" + lvlEXP);
		ConstructGUI.defense.setText("Defense: " + defense);
		ConstructGUI.intellect.setText("Intellect: " + intellect);
		ConstructGUI.gold.setText("Gold: " + gold);
		ConstructGUI.mana.setText("Mana: " + mana + "/" + totMana);
		ConstructGUI.speed.setText("Speed: " + speed);
		ConstructGUI.luck.setText("Luck: " + luck);
		ConstructGUI.strength.setText("Strength: " + strength);
		ConstructGUI.points.setText("Stat points: " + points);
	}

	public static void setEStats() {
		if (eTotHealth == 0) {
			ConstructGUI.eHealth.setText("");
			ConstructGUI.eLevel.setText("");
			ConstructGUI.eDefense.setText("");
			ConstructGUI.eIntellect.setText("");
			ConstructGUI.eMana.setText("");
			ConstructGUI.eSpeed.setText("");
			ConstructGUI.eLuck.setText("");
			ConstructGUI.eStrength.setText("");
		} 
		else {
			ConstructGUI.eHealth.setText("Enemy HP: " + eHealth + "/" + eTotHealth);
			ConstructGUI.eLevel.setText("Level: " + eLevel);
			if(hasTrueSight) {
				ConstructGUI.eDefense.setText("Defense: " + eDefense);
				ConstructGUI.eIntellect.setText("Intellect: 0");
				ConstructGUI.eMana.setText("Mana: " + eMana + "/" + 10);
				ConstructGUI.eSpeed.setText("Speed: " + eSpeed);
				ConstructGUI.eLuck.setText("Luck: " + eLuck);
				ConstructGUI.eStrength.setText("Strength: " + eStrength);
			}
		}
	}
	public static void playerTurn() {
		turnCounter++;
		mana += (rand.nextInt(6) + 5) / 100.0 * totMana;
		if (mana > totMana) {
			mana = totMana;	}	
		
		if(defenseCounter > 5) {
			setAbility2(true);
			setEStats();
			setCharStats();
			ConstructGUI.outputText3.setText("Ability 2 has worn off");
		}
		if(eHealth <= 0) {
			eHealth = 0;
			isEAlive = false;
			ConstructGUI.outputText3.setText("");
			setEStats();
			setLVLEXP();
			ConstructGUI.advance.setText("ADVANCE");
			ConstructGUI.outputText2.setText("Enemy has been defeated");
			turnCounter++;
		} else {
			enemyTurn();
		}

	}
	public static void enemyTurn() {
		dmgDealt = battle.getBattle(eStrength, defense, speed, eLuck);
		hp -= dmgDealt;
		ConstructGUI.outputText2.setText("Enemy has dealt " + dmgDealt + " dmg");
	
		if(hp <= 0) {
			hp = 0;
			ConstructGUI.outputText3.setText("You have died");
		} else {
		turnCounter++;
		}
	}


}
