package de.scoopgmbh.copper.monitoring.core.statistic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class StatisticCreator<T,R> implements Serializable{
	private static final long serialVersionUID = -8510844252874340757L;
	
	private TimeframeGroup<T,R> currentGroupFunction;
	
	public StatisticCreator(TimeframeGroup<T,R> firstGroupFunctions) {
		super();
		this.currentGroupFunction = firstGroupFunctions;
		usedGroupFunnction.add(currentGroupFunction);
	}
	
	List<TimeframeGroup<T,R>> usedGroupFunnction= new ArrayList<TimeframeGroup<T,R>>();
	
	/**
	 * first value must be in the first group
	 *
	 * @param <T>
	 * @param <R>
	 */
	public void add(T listvalue ){
		if (currentGroupFunction.isInGroup(listvalue)){
			currentGroupFunction.addToGroup(listvalue);
		} else {
			currentGroupFunction.doAggregateAndSaveResult();
			currentGroupFunction.clear();
			currentGroupFunction = currentGroupFunction.nextGroup();
			if (currentGroupFunction!=null){
				usedGroupFunnction.add(currentGroupFunction);
				add(listvalue);
				
			}
		}
	};
	
	public List<R> getAggregatedResult(){
		ArrayList<R> result = new ArrayList<R>();
		for (TimeframeGroup<T,R> groupFunction: usedGroupFunnction){
			if (!groupFunction.isAggregated()){//for last group 
				groupFunction.doAggregateAndSaveResult();
				groupFunction.clear();
			}
			result.add(groupFunction.getAggregate());
		}
		return result;
	}

}