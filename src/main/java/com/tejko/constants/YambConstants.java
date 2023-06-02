package com.tejko.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tejko.models.yamb.enums.BoxType;

public class YambConstants {

	public static final int NUMBER_OF_ROLLS = 3;
	public static final int NUMBER_OF_DICE = 5;

	public static final int BONUS_TRIPS = 10;
	public static final int BONUS_SMALL_STRAIGHT = 35;
	public static final int BONUS_LARGE_STRAIGHT = 45;
	public static final int BONUS_BOAT = 30;
	public static final int BONUS_CARRIAGE = 40;
	public static final int BONUS_YAMB = 50;

	public static final int TOP_SECTION_SUM_BONUS_THRESHOLD = 60;
	public static final int TOP_SECTION_SUM_BONUS = 30;
	
	public static final Set<Integer> SMALL_STRAIGHT = Collections.unmodifiableSet(Set.of(1, 2, 3, 4, 5));
	public static final Set<Integer> LARGE_STRAIGHT = Collections.unmodifiableSet(Set.of(2, 3, 4, 5, 6));

	public static final Map<Integer, Integer> REPEAT_THRESHOLD_BONUS_MAP = Collections.unmodifiableMap(Map.of(
		3, BONUS_TRIPS,
		4, BONUS_CARRIAGE,
		5, BONUS_YAMB
	));

	public static final List<BoxType> TOP_SECTION = Collections.unmodifiableList(Arrays.asList(
		BoxType.ONES,
		BoxType.TWOS,
		BoxType.THREES,
		BoxType.FOURS, 
		BoxType.FIVES, 
		BoxType.SIXES
	));

	public static final List<BoxType> BOTTOM_SECTION = Collections.unmodifiableList(Arrays.asList(
		BoxType.TRIPS,
		BoxType.STRAIGHT,
		BoxType.CARRIAGE,
		BoxType.BOAT,
		BoxType.YAMB
	));

	public static final String ERROR_MESSAGE_ROLL_LIMIT_REACHED = "The roll limit has been reached!";
    public static final String ERROR_MESSAGE_ANNOUNCEMENT_REQUIRED = "An announcement is required!";
    public static final String ERROR_MESSAGE_ANNOUNCEMENT_ALREADY_DECLARED = "An announcement has already been made!";
    public static final String ERROR_MESSAGE_ANNOUNCEMENT_NOT_AVAILABLE = "An announcement is available only after first roll!";
    public static final String ERROR_MESSAGE_BOX_ALREADY_FILLED = "The box has already been filled!";
    public static final String ERROR_MESSAGE_BOX_NOT_AVAILABLE = "The box is not available!";
    public static final String ERROR_MESSAGE_DICE_ROLL_REQUIRED = "To fill a box, the first dice roll is required!";
    public static final String ERROR_MESSAGE_BOX_NOT_ANNOUNCED = "The box is different from announcement!";
    public static final String ERROR_MESSAGE_RESTART_COMPLETED_SHEET = "A completed sheet cannot be restarted!";

}
