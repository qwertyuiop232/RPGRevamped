package RPGRevamped;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ProcessHandler implements ActionListener {
	
	private JButton save, load, restart, advance, flee, ability1, ability2, ability3, add, buy;
	
	public ProcessHandler(JButton save,JButton load,JButton restart,JButton advance,JButton flee,
			JButton ability1,JButton ability2, JButton ability3, JButton add, JButton buy) {
		this.save = save;
		this.load = load;
		this.restart = restart;
		this.advance = advance;
		this.flee = flee;
		this.ability1 = ability1;
		this.ability2 = ability2;
		this.ability3 = ability3;
		this.add = add;
		this.buy = buy;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source == save) {
			Process.setSave();
		}else if (source == load){
			Process.setLoad();			
		}else if (source == restart) {
			Process.setStart();
		}else if (source == advance) {
			Process.setAdvance();
		}else if(source == flee) {
			Process.setFlee();
		}else if(source == ability1) {
			Process.setAbility1();
		}else if(source == ability2) {
			Process.setAbility2();
		}else if(source == ability3) {
			Process.setAbility3();
		}else if(source == add) {
			Process.assign(ConstructGUI.assign.getSelectedIndex());
		}else if(source == buy) {
			Process.buy(ConstructGUI.buyItem.getSelectedIndex());
		}
	}
}
