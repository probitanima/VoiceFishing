package org.probit.voicefishing.util;

import java.io.Serializable;
import java.util.ArrayList;

import org.probit.voicefishing.model.Item;

public class SaveFile implements Serializable {

	private static final long serialVersionUID = 1L;

	public SaveFile() {
		level = 1;
		experience = 0;
		coin = 0;
		highscore = 0;

		gender = 0;
		soundEnable = true; // sound on/off

		item = new ArrayList<Item>();
		item.add(new Item(0, 0, 10, 1, 2));
		item.add(new Item(0, 1, 10, 1, 3));
		item.add(new Item(0, 2, 10, 1, 4));
		item.add(new Item(0, 3, 10, 1, 5));
		item.add(new Item(0, 4, 10, 1, 6));
		item.add(new Item(1, 0, 10, 10, 2));
		item.add(new Item(1, 1, 10, 10, 3));
		item.add(new Item(1, 2, 10, 10, 4));
		
		item.get(0).buyItem();		
		item.get(0).setUsing(true);
	}

	public void setGender(int newGender) {
		gender = newGender;
	}

	public int getGender() {
		return gender;
	}

	public int getExp() {
		return (int) experience * 100 / (level * 100);
	}

	public void usingItem(int choise) {
		for (Item _item : item) {
			// same type
			if (_item.type == item.get(choise).type) {
				_item.setUsing(false);
			}
		}

		item.get(choise).setUsing(true);
	}

	public boolean getSoundEnable() {
		return soundEnable;
	}

	public void setSoundEnable() {
		if(this.soundEnable)
			this.soundEnable = false;
		else
			this.soundEnable = true;
	}

	public int level;
	public double experience;
	public int coin;
	public int highscore;

	public ArrayList<Item> item;

	private int gender;
	private boolean soundEnable; // sound on/off

}
