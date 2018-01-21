package RPGRevamped;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ConstructGUI{
	
	// declare variables
	JFrame frame = new JFrame();
	static JButton save, load, restart, advance, flee, ability1, ability2, ability3, add, buy;
	JPanel user, enemy, abilities, saves, fight;
	static JComboBox<String> assign, buyItem;
	static String[] itemShop = {"(10g) Mana Potion", "(10g) Health Potion", "(50g)Strength Seed", "(50g)Vitality Potion", "(20g)Speed Potion", "(50g)Book",
			"(10g)Bomb (Immediate)", "(30g)Armor", "(400g)True Sight"};
	String[] statAssign = {"ASSIGN", "STRENGTH", "HEALTH", "SPEED", "LUCK", "INTELLECT", "DEFENSE"};
	static JLabel charName, enemyName, level, health, eHealth, exp, strength, luck, speed, defense, 
	gold, mana, intellect, points, eLevel, outputText1, outputText2, outputText3, outputText4, outputText5,
	eSpeed, eStrength, eDefense, eLuck, eIntellect, eMana;
	BufferedImage healing = null;

	
	public ConstructGUI() {
		setUIFont (new javax.swing.plaf.FontUIResource("Dialog",Font.PLAIN, 14));
		frame.setTitle("RPG - revamped");
		frame.setLayout(new BorderLayout());
		frame.setSize(700,600);
		frame.setLocation(750,200);
		
		// initialize JPanel components
		save = new JButton("SAVE");
		load = new JButton("LOAD");
		restart = new JButton("RESTART");
		advance = new JButton("ADVANCE");
		flee = new JButton("FLEE");
		buy = new JButton("BUY");
		setBufferedImage();
		ability1 = new JButton("FIRE BALL");
		ability2 = new JButton("BULK UP");
		ability3 = new JButton(new ImageIcon(healing));
		add = new JButton("ADD");
		
		assign = new JComboBox<String>(statAssign);
		buyItem = new JComboBox<String>(itemShop);
		
		ability1.setPreferredSize(new Dimension(120, 40));
		ability2.setPreferredSize(new Dimension(120, 40));
		add.setPreferredSize(new Dimension(80, 40));
		assign.setPreferredSize(new Dimension(120, 40));
		ability3.setPreferredSize(new Dimension(120, 40));
		
		charName = new JLabel("The User", SwingConstants.CENTER);
		level = new JLabel("", SwingConstants.CENTER);
		health = new JLabel("", SwingConstants.CENTER);
		mana = new JLabel("", SwingConstants.CENTER);
		eHealth = new JLabel("", SwingConstants.CENTER);
		strength = new JLabel("", SwingConstants.CENTER);	
		luck = new JLabel("", SwingConstants.CENTER);
		speed = new JLabel("", SwingConstants.CENTER);
		intellect = new JLabel("", SwingConstants.CENTER);
		defense = new JLabel("", SwingConstants.CENTER);
		gold = new JLabel("", SwingConstants.CENTER);
		exp = new JLabel("", SwingConstants.CENTER);
		eStrength = new JLabel("", SwingConstants.CENTER);
		eSpeed = new JLabel("", SwingConstants.CENTER);
		eMana = new JLabel("", SwingConstants.CENTER);
		eLuck = new JLabel("", SwingConstants.CENTER);
		eIntellect = new JLabel("", SwingConstants.CENTER);
		eDefense = new JLabel("", SwingConstants.CENTER);

		outputText1 = new JLabel("", SwingConstants.CENTER);
		outputText2 = new JLabel("", SwingConstants.CENTER);
		outputText3 = new JLabel("", SwingConstants.CENTER);
		outputText4 = new JLabel("", SwingConstants.CENTER);
		outputText5 = new JLabel("", SwingConstants.CENTER);
		points = new JLabel("", SwingConstants.CENTER);
		eLevel = new JLabel("", SwingConstants.CENTER);
		enemyName = new JLabel("The Enemy", SwingConstants.CENTER);

		user = new JPanel();
		saves = new JPanel();
		fight = new JPanel();
		abilities = new JPanel();
		enemy = new JPanel();
		
		// add JPanels
		frame.getContentPane().add(saves, BorderLayout.NORTH);
		frame.getContentPane().add(fight, BorderLayout.CENTER);
		frame.getContentPane().add(user, BorderLayout.WEST);
		frame.getContentPane().add(abilities, BorderLayout.SOUTH);
		frame.getContentPane().add(enemy, BorderLayout.EAST);
		
		// top layout
		saves.setLayout(new FlowLayout());
		saves.setPreferredSize(new Dimension(600,80));
		saves.add(save);
		saves.add(load);
		saves.add(restart);

		// middle layout
		fight.setLayout(new GridLayout(11, 1, 0, 0));
		fight.setPreferredSize(new Dimension(100, 600));
		fight.add(advance);
		fight.add(flee);
		fight.add(outputText1);
		fight.add(outputText2);
		fight.add(outputText3);
		fight.add(outputText4);
		fight.add(outputText5);
		fight.add(buyItem);
		fight.add(buy);


		// left layout
		user.setLayout(new GridLayout(7, 2, 0, 0));
		user.setPreferredSize(new Dimension(250, 440));
		user.setBackground(Color.GRAY);
		user.add(charName);
		user.add(level);
		user.add(health);
		user.add(mana);
		user.add(exp);
		user.add(gold);
		user.add(strength);
		user.add(defense);
		user.add(speed);
		user.add(intellect);
		user.add(luck);
		user.add(points);
		
		// bottom layout
		abilities.setLayout(new FlowLayout());
		abilities.setPreferredSize(new Dimension(600, 80));
		abilities.add(assign);
		abilities.add(add);
		abilities.add(ability1);
		abilities.add(ability2);
		abilities.add(ability3);

		// right layout
		enemy.setLayout(new GridLayout(7, 2, 0, 0));
		enemy.setPreferredSize(new Dimension(250, 440));
		enemy.setBackground(Color.GRAY);
		enemy.add(enemyName);
		enemy.add(eLevel);
		enemy.add(eHealth);	
		enemy.add(eMana);
		enemy.add(eStrength);
		enemy.add(eDefense);
		enemy.add(eSpeed);
		enemy.add(eIntellect);
		enemy.add(eLuck);

		// add event listeners 	JButton save, load, restart, advance, flee, heal, ability1, ability2, ability3, add;
		ActionListener event = new ProcessHandler( save, load, restart, advance, flee, ability1, ability2, ability3, add, buy);
		save.addActionListener(event);
		load.addActionListener(event);
		restart.addActionListener(event);
		advance.addActionListener(event);
		flee.addActionListener(event);
		ability1.addActionListener(event);
		ability2.addActionListener(event);
		ability3.addActionListener(event);
		add.addActionListener(event);
		buy.addActionListener(event);
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void setBufferedImage() {
		try {
		    healing = ImageIO.read(new File("images/IconHeal.png"));
			} catch (IOException e) {
				System.out.println("image not found");
			}
	}
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
	    Enumeration<Object> keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 
}
