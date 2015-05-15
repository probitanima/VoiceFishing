package org.probit.voicefishing.util;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {
	public interface PoolObjectFactory<T> {
		public T createObject();
	}
	
	private final List<T> freeObjects;
	private final PoolObjectFactory<T> factory;
	private final int maxSize;					//무한히 커지는 것을 막기 위함
	
	public Pool(PoolObjectFactory<T> factory, int maxSize) {
		this.factory = factory;
		this.maxSize = maxSize;
		this.freeObjects = new ArrayList<T>(maxSize);
	}
	
	// pool에 보관중인 인스턴스를 반환
	// 보관된 객체가 있을 경우 객체들을 재사용
	// 없으면 팩토리를 통해 새로운 객체 생성
	public T newObject() {
		T object = null;
		
		if(freeObjects.size() == 0)
			object = factory.createObject();
		else
			object = freeObjects.remove(freeObjects.size() - 1);
		
		return object;
	}
	
	public void free(T object) {
		if(freeObjects.size() < maxSize)
			freeObjects.add(object);
	}
	
}
