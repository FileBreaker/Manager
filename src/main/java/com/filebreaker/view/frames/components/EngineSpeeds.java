package com.filebreaker.view.frames.components;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

class EngineSpeeds {
	
	@SuppressWarnings("serial")
	static NavigableMap<EngineSpeedValue, Integer> engineSpeedCorrespondences = new TreeMap<EngineSpeedValue, Integer>() {{
		put(EngineSpeedValue.STOPPED, 0);
		put(EngineSpeedValue.PERCENT_10, 10);
		put(EngineSpeedValue.PERCENT_20, 20);
		put(EngineSpeedValue.PERCENT_30, 30);
		put(EngineSpeedValue.PERCENT_40, 40);
		put(EngineSpeedValue.PERCENT_50, 50);
		put(EngineSpeedValue.PERCENT_60, 60);
		put(EngineSpeedValue.PERCENT_70, 70);
		put(EngineSpeedValue.PERCENT_80, 80);
		put(EngineSpeedValue.PERCENT_90, 90);
		put(EngineSpeedValue.FULL_SPEED, 100);
	}};
	
	public enum EngineSpeedValue {
		STOPPED, PERCENT_10, PERCENT_20, PERCENT_30, PERCENT_40, 
		PERCENT_50, PERCENT_60, PERCENT_70, PERCENT_80, PERCENT_90, FULL_SPEED;
		
		public EngineSpeedValue speedUp(){
			Entry<EngineSpeedValue, Integer> entry = engineSpeedCorrespondences.higherEntry(this);
			
			if(entry != null){
				return entry.getKey();
			} 
			
			return this;
		}
		
		public EngineSpeedValue speedDown(){
			Entry<EngineSpeedValue, Integer> entry = engineSpeedCorrespondences.lowerEntry(this);
			
			if(entry != null){
				return entry.getKey();
			} 
			
			return this;
		}
		
		public int getSpeedPercentage(){
			return engineSpeedCorrespondences.get(this);
		}
	}
}