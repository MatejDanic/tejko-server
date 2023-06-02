package com.tejko.utils;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.tejko.constants.YambConstants;
import com.tejko.models.yamb.Dice;
import com.tejko.models.yamb.enums.BoxType;

public class YambUtil {

	public static int calculateScore(Collection<Dice> diceList, BoxType selectedBoxType) {
		int result = 0;
		switch (selectedBoxType) {
			case ONES:
			case TWOS:
			case THREES:
			case FOURS:
			case FIVES:
			case SIXES:
				result = calculateSumByBoxType(diceList, selectedBoxType);
				break;
			case MAX:
			case MIN:
				result = calculateSum(diceList);
				break;
			case TRIPS:
				result = calculateSumOfRepeatingValue(diceList, 3);
				break;
			case STRAIGHT:
				result = calculateStraight(diceList);
				break;
			case BOAT:
				result = calculateBoat(diceList);
				break;
			case CARRIAGE:
				result = calculateSumOfRepeatingValue(diceList, 4);
				break;
			case YAMB:
				result = calculateSumOfRepeatingValue(diceList, 5);
				break;
		}
		return result;
	}

	// returns sum of all dice values
	private static int calculateSum(Collection<Dice> diceList) {
		return diceList.stream().mapToInt(Dice::getValue).reduce(0, Integer::sum);
	}

	// returns sum of dice  values that equal the selected box type 
	// only applicable for box types [ONES-SIXES]
	private static int calculateSumByBoxType(Collection<Dice> diceList, BoxType boxType) {
		return calculateSum(diceList.stream().filter(a -> a.getValue() == (boxType.ordinal()-1)).collect(Collectors.toList()));
	}

	// returns sum of dice values if the number of times that the value ocurrs is equal to or larger than the repeat threshold
	// values exceeding the threshold are ignored 
	private static int calculateSumOfRepeatingValue(Collection<Dice> diceList, int repeatThreshold) {
		Map<Integer, Long> valueCountMap = diceList.stream().collect(Collectors.groupingBy(Dice::getValue, Collectors.counting()));
		for (int diceValue = 1; diceValue <= 6; diceValue++) {
			if (valueCountMap.get(diceValue) >= repeatThreshold) {
				return diceValue * repeatThreshold + YambConstants.REPEAT_THRESHOLD_BONUS_MAP.get(repeatThreshold);
			}
		}
		return 0; // repeat threshold has not been reached
	}

	// returns bonus scores if dice values contain small or large straights
	// small (1, 2, 3, 4 , 5)
	// large (2, 3, 4, 5, 6)
	private static int calculateStraight(Collection<Dice> diceList) {
		if (diceList.stream().mapToInt(Dice::getValue).boxed().collect(Collectors.toList()).containsAll(YambConstants.SMALL_STRAIGHT)) {
			return YambConstants.BONUS_SMALL_STRAIGHT;
		} else if (diceList.stream().mapToInt(Dice::getValue).boxed().collect(Collectors.toList()).containsAll(YambConstants.LARGE_STRAIGHT)) {
			return YambConstants.BONUS_LARGE_STRAIGHT;
		}
		return 0;
	}

	// checks if two distinct dice values repeat 2 and 3 times respectively
	public static int calculateBoat(Collection<Dice> diceList) {
		// same as count, but value type is Integer instead of Long
		Map<Integer, Integer> valueCountMap = diceList.stream().collect(Collectors.groupingBy(Dice::getValue, Collectors.summingInt(a -> 1)));
		if (valueCountMap.containsValue(2) && valueCountMap.containsValue(3)) {
			return valueCountMap.entrySet().stream().mapToInt(entry -> entry.getKey() * Math.toIntExact(entry.getValue())).sum() + YambConstants.BONUS_BOAT;
		}
		return 0;
	}

}
