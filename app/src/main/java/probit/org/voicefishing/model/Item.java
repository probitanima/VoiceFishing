package org.probit.voicefishing.model;

import java.io.Serializable;

import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.framework.impl.ImplPixmap;

public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public int type;		// ≥¨Ω√¡Ÿ or ≥¨Ω√πŸ¥√
	public int id;
	
	public int price;
	public int many;
	public int last;
	
	public int effect;
	
	private boolean using;
	
	public Item(int type, int id, int price, int many, int effect) {
		this.type = type;
		this.id = id;
		this.price = price;
		this.many = many;
		this.effect = effect;
		using = false;
	}
	
	public int buyItem() {
		last += many;
		return price;
	}
	
	public int doEffect() {
		return effect;
	}
	
	public void setUsing(boolean using) {
		this.using = using;
	}
	
	public boolean getUsing() {
		return using;
	}
	
}
