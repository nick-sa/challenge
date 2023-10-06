package de.bcxp.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    public static void main(String... args) {


    	

    	
        String dayWithSmallestTempSpread = calculateSmallestTempSpread(getWeatherData());     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        String countryWithHighestPopulationDensity = calculateHighestPopulationDensity(getCountriesData()); // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }


    
/**
 * This function calculates the smallest temperature spread from from a string array of days and two string arrays of temperatures;
 * @param arrays is a two-dimensional array with three string arrays. 
 * @return This function returns the element of the first array at the index of the least component-wise difference between the second and third arrays. 
 */
public static String calculateSmallestTempSpread(String[][] arrays) {
	return componentWiseOperation(arrays[0], arrays[1], arrays[2], "SmallestTempSpread");
}
/**
 * This function calculates the highest population density country
 * @param arrays is a two-dimensional array with the first array containing country name strings, the second array containing population number strings and the third array containing area number strings.
 * @return
 */
public static String calculateHighestPopulationDensity(String[][] arrays) {
	return componentWiseOperation(arrays[0], arrays[1], arrays[2], "HighestPopulationDensity");
}
/**
 * This is the helper function that calculates the desired value based on the arrays.
 * @param arrays is a two-dimensional array with three string arrays
 * @param goal is the desired value that should be calculated
 * @return This function passes the three arrays as separate arrays to the main calculating function and gets the return from there.
 */
public static String componentWiseOperation(String[][] arrays, String goal) {
	return componentWiseOperation(arrays[0], arrays[1], arrays[2], goal);
}

/**
 * This function is the main helper function that determines the desired index and returns the string at that index.
 * @param array1 first array usually contains names or labels
 * @param array2 second array has the first numbers in strings
 * @param array3 third array has the second numbers in strings
 * @param goal the goal determines which value should be calculated using the strings
 * @return the string at the determined index position of the first array
 */
public static String componentWiseOperation(String[] array1, String[] array2, String[] array3, String goal) {
	
	if (goal.equals("SmallestTempSpread")) {
		long[] resultingArray = new long[array2.length]; 
		Arrays.setAll(resultingArray, i -> Long.parseLong(array2[i]) - Long.parseLong(array3[i]));
		return array1[indexOfMin(resultingArray)];
	}
	
	if (goal.equals("HighestPopulationDensity")) {
		long[] resultingArray = new long[array2.length]; 
		Arrays.setAll(resultingArray, i -> Long.parseLong(array2[i]) / Long.parseLong(array3[i]));
		return array1[indexOfMax(resultingArray)];
	} else {
		return null;
	}
		
}
/**
 * This function calculates the index of the smallest element in the array in O(n)
 * @param numberarray the array with Long numbers
 * @return This function returns the index of the smallest element of the array.
 */
private static int indexOfMin(long[] numberarray) {
	int indexMin = 0;
	long min = numberarray[0];
	for (int i = 0; i < numberarray.length; i++) {
		if (numberarray[i] < min) {
			indexMin = i;
			min = numberarray[i];
		}
	}
	
	return indexMin;
}

/**
 * This function determines the index of the biggest number in the array in O(n)
 * @param numberarray is an array with numbers of which the biggest should have its index calculated.
 * @return This function returns the index of the biggest element in the array.
 */
private static int indexOfMax(long[] numberarray) {
	int indexMax = 0;
	long max = numberarray[0];
	for (int i = 0; i < numberarray.length; i++) {
		if (numberarray[i] > max) {
			indexMax = i;
			max = numberarray[i];
		}
	}
	
	return indexMax;
}
	
	

/**
 * This is the simplest function to get the two-dimensional array with three arrays containing the required weather data. 
 * @return This function passes the default values to the main data extraction function and returns three string arrays in an array, the first string array has the days, the two other string arrays have the highest and lowest temperatures
 */
public static String[][] getWeatherData() {
	
	return getData("./src/main/resources/de/bcxp/challenge/weather.csv", 0, 1, 2, ',');
}

/**
 * This is a version of the getWeatherData() function that takes an arbitrary location as a parameter in case the weather file is in a different location 
 * @param location it takes a string of the path in the filesystem to the weather.csv file
 * @return This function passes the new location with other values at default to the main data extraction function and returns three string arrays in an array, the first string array has the days, the two other string arrays have the highest and lowest temperatures.
 */
public static String[][] getWeatherData(String location) {
	
	return getData(location, 0, 1, 2, ',');
}
/**
 * This function gets the weather data with arbitrary column numbers in case the default column numbers have changed.
 * @param param1 the column number for the first array, contains strings with day labels 
 * @param param2 the column number for the second array containing the maximum temperature on that day
 * @param param3 the column number for the third array containing the minimum temperature on that day
 * @return this function passes the arbitrary column numbers with the default parameters to the main helper function to get the weather data arrays
 */
public static String[][] getWeatherData(int param1, int param2, int param3) {

	
	return getData("./src/main/resources/de/bcxp/challenge/weather.csv", param1, param2, param3, ','); 
}
/**
 * This function gets the weather data using arbitrary location and column numbers in case the location of the file changes and the column numbers of the maximum and minimum values change
 * @param location it takes a string of the path in the filesystem to the weather.csv file
 * @param param1 the column number for the first array, contains strings with day labels 
 * @param param2 the column number for the second array containing the maximum temperature on that day
 * @param param3 the column number for the third array containing the minimum temperature on that day
 * @return this function passes the arbitrary location and column numbers with other parameters default of the main data extraction function to get the weather data arrays.
 */
public static String[][] getWeatherData(String location, int param1, int param2, int param3) {

	return getData(location, param1, param2, param3, ','); 
}


/**
 * This function gets the weather data with an arbitrary column delimiter in case the delimiter changes 
 * @param delimiter the default delimiter in .csv files is a comma, but it can be a semicolon or something else
 * @return this function passes the arbitrary delimiter with the default parameters to the main data extraction function to get the weather data arrays
 */
public static String[][] getWeatherData(char delimiter) {
	
	return getData("./src/main/resources/de/bcxp/challenge/weather.csv", 0, 1, 2, delimiter);
}
/**
 * This function gets the weather data with an arbitrary column delimiter and an arbitrary location.
 * @param location it takes a string of the path in the filesystem to the weather.csv file
 * @param delimiter the default delimiter in .csv files is a comma, but it can be a semicolon or something else
 * @return the specified delimiter and file location are passed to the main data extraction function with default values to get the weather arrays
 */
public static String[][] getWeatherData(String location, char delimiter) {
	
	return getData(location, 0, 1, 2, delimiter);
}
/**
 * This function gets the weather data with arbitrary column numbers and arbitrary delimiter
 * @param param1 the first array contains day labels
 * @param param2 the second array contains maximum daily temperatures
 * @param param3 the third array contains minimum daily temperatures
 * @param delimiter the delimiter defines the column separator
 * @return This function is passed to the main data extraction function with the specified parameters
 */
public static String[][] getWeatherData(int param1, int param2, int param3, char delimiter) {

	return getData("./src/main/resources/de/bcxp/challenge/weather.csv", param1, param2, param3, delimiter); 
}
/**
 * This function gets the weather data with an arbitrary file location and arbitrary column numbers  and arbitrary location
 * @param location the location of the .csv file with weather data in the file system
 * @param param1 the first array contains day labels
 * @param param2 the second array contains maximum daily temperatures
 * @param param3 the third array contains minimum daily temperatures
 * @param delimiter the delimiter defines the column separator
 * @return This function passes the arbitrary parameters to the data extraction function
 */
public static String[][] getWeatherData(String location, int param1, int param2, int param3, char delimiter) {
	
	return getData(location, param1, param2, param3, delimiter); 
}





/**
 * This function gets the data about countries, population and areas in order to calculate the population density using the default parameters
 * @return This function passes default parameters to the main data extraction function to get the data about countries, population and area
 */
public static String[][] getCountriesData() {
	
	return getData("./src/main/resources/de/bcxp/challenge/countries.csv", 0, 3, 4, ';');
}
/**
 * This function gets the data about countries using an arbitrary file location
 * @param location the path to the .csv file containing the data about countries, population and country areas
 * @return This function passes the default values for country data with the arbitrary file location to the main data extraction function
 */
public static String[][] getCountriesData(String location) {
	
	return getData(location, 0, 3, 4, ';');
}
/**
 * This function gets the country data with arbitrary column numbers
 * @param param1 the first parameter is the column containing country names
 * @param param2 the second parameter is the column containing populations
 * @param param3 the third parameter is the column containing areas of countries
 * @return This function passes the arbitrary columns with default other parameters to the main data extraction function to extract country data
 */
public static String[][] getCountriesData(int param1, int param2, int param3) {
	
	return getData("./src/main/resources/de/bcxp/challenge/countries.csv", param1, param2, param3, ';');
}
/**
 * This function extracts country data using the default parameters with arbitrary file location and arbitrary column numbers. 
 * @param location the location of the .csv file
 * @param param1 the column with country names
 * @param param2 the column with population data
 * @param param3 the column with country area
 * @return This function passes the arbitrary parameters with default parameters for countries to the main data extraction function
 */
public static String[][] getCountriesData(String location, int param1, int param2, int param3) {
	
	return getData(location, param1, param2, param3, ';');
}


/**
 * This function extracts the country data with an arbitrary delimiter
 * @param delimiter the arbitrary delimiter separating columns
 * @return This function passes the default country parameters and the arbitrary delimiter to the main data extraction function 
 */
public static String[][] getCountriesData(char delimiter) {
	
	return getData("./src/main/resources/de/bcxp/challenge/countries.csv", 0, 3, 4, delimiter);
}
/**
 * This function extracts country data using an arbitrary location and delimiter
 * @param location the location of the .csv file
 * @param delimiter
 * @return This function passes the arbitrary file location and delimiter with default country parameters to the main data extraction function
 */
public static String[][] getCountriesData(String location, char delimiter) {
	
	return getData(location, 0, 3, 4, delimiter);
}
/**
 * This function extracts country data using arbitrary column numbers and delimiter
 * @param param1 the column with country names
 * @param param2 the column with population data
 * @param param3 the column with country area
 * @param delimiter the column separator in the .csv file
 * @return This function passes the arbitrary parameters with default values for country data to the main data extraction function
 */
public static String[][] getCountriesData(int param1, int param2, int param3, char delimiter) {
	
	return getData("./src/main/resources/de/bcxp/challenge/countries.csv", param1, param2, param3, delimiter);
}
/**
 * This function extracts country data using arbitrary column numbers, arbitrary file location and arbitrary delimiter
 * @param location the location of the .csv file with country data
 * @param param1 the number of the column with country names
 * @param param2 the number of the column with population data
 * @param param3 the number of the column with area data
 * @param delimiter the column separator
 * @return This function passes the specified parameters to the main data extraction function
 */
public static String[][] getCountriesData(String location, int param1, int param2, int param3, char delimiter) {
	
	return getData(location, param1, param2, param3, delimiter);
}


/**
 * This function extracts the data from .csv files into three string arrays in a two dimensional array. 
 * @param location this parameter specifies the location of the .csv file in the file system
 * @param param1 this parameter specifies the number of the first column of the .csv file that should be represented in the first array
 * @param param2 this parameter specifies the number of the second column to be represented in the second array
 * @param param3 this parameter specifies the number of the third column to be represented in the third array
 * @param delimiter this parameter specifies the delimiter between columns in the .csv file
 * @return This function returns a two dimensional array of string arrays with three string arrays - the first array contains the data from the first column, the second array contains the data from the second column and the third array countains data from the third column.
 */
public static String[][] getData(String location, int param1, int param2, int param3, char delimiter) {

	List<String> stringList = new ArrayList<>(); 
	List<String> numberList1 = new ArrayList<>();
	List<String> numberList2 = new ArrayList<>();
	String stringDelimiter = String.valueOf(delimiter);

 
			
	try (BufferedReader br = new BufferedReader(new FileReader(location))) {
	    String line;
	    br.readLine();
	    while ((line = br.readLine()) != null) {
	    	
	    	stringList.add( line.split(stringDelimiter)[param1]);
	    	numberList1.add(line.split(stringDelimiter)[param2].replaceAll("\\.", "").split(",")[0]);
	    	numberList2.add(line.split(stringDelimiter)[param3].replaceAll("\\.", "").split(",")[0]);
	    	
	    }
	    br.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	
	String[] stringArray = new String[stringList.size()];
	stringArray = stringList.toArray(stringArray);
	
	String[] numberArray1 = new String[numberList1.size()];
	numberArray1 = numberList1.toArray(numberArray1);
	
	String[] numberArray2 = new String[numberList2.size()];
	numberArray2 = numberList2.toArray(numberArray2);
	
	
	String[][] returnArrays = {stringArray,numberArray1,numberArray2};
	
return returnArrays;
	
}




}