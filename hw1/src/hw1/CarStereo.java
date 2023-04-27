package hw1;
/**
 * Models the behavior of a simple radio
 * @author Chen Sang 
 *
 */

public class CarStereo {
	
	/**
	 * Amount the volume changes for each louder() or quieter() operation
	 */
	public static final double VOLUME_STEP = 0.16;
	
	/**
	 * volume of this radio
	 */
	private double currentVolume; 
	
	/**
	 * maximum frequency of this radio
	 */
	private double maxFrequency;

	/**
	 * minimum frequency of this radio
	 */
	private double minFrequency;
	
	/**
	 * current frequency of this radio
	 */
	private double currentFrequency;
	
	/**
	 * Station numbers of this radio
	 */
	private int numStation;
	
    /**
     * save preset Station number from givenFrequency in savePreset() method
     */
	private int presetStationNum;
	
	/**
	 * Constructs a radio with the given band (frequency range) and number of stations.
	 * @param givenMinFrequency
	 * @param givenMaxFrequency
	 * @param givenNumStations
	 */
	public CarStereo(double givenMinFrequency, double givenMaxFrequency, int givenNumStations)
	{ 
	    currentVolume    = 0.5;
	    maxFrequency     = givenMaxFrequency;
	    minFrequency     = givenMinFrequency;
	    currentFrequency = minFrequency; 
	    numStation       = givenNumStations;
	    presetStationNum = 0;
	}
	
	/**
	 * access  current volume
	 * @return current volume
	 */
	public double getVolume() 
	{
	
		return currentVolume;	
	}
	
	/**
	 * increase volume by VOLUME_STEP(0.16) per time, 
	 * but Current volume has to less and equal to 1.0 
	 */
	public void louder()
	{
	    currentVolume = Math.min(currentVolume += VOLUME_STEP, 1.0);
	}
	
	/**
	 * decrease volume by (0.16) per time, 
	 * but current volume always bigger or equal to 0
	 */
	public void quieter()
	{
		currentVolume = Math.max(currentVolume -= VOLUME_STEP, 0.0);
	}
	
	/**
	 * access current frequency
	 * @return current frequency 
	 */
	public double getTuner()
	{    
		return currentFrequency;	
	}
	
	/**
	 * Find Station number of given Frequency
	 * @return number Station 
	 */
	
     public int findStationNumber()
     {
    	 double bandWidth       = (maxFrequency - minFrequency) / numStation ;  
    	 double distance        = currentFrequency - minFrequency;
    	 int    newnumStation   = (int) (distance / bandWidth);
    	 int    StationNum = Math.max(0,Math.min(newnumStation, numStation-1));
    
    	 return StationNum; 
 
     }
	/**
	 * mutate current Frequency. 
	 * if givenFrenquncy > maxFrequency,then set to maxFrequency. 
	 * if givenFrequency < miniFrequency, then set to miniFrequency
	 * @param givenFrequency
	 */
	public void setTuner(double givenFrequency) 
	{
		currentFrequency = Math.max(Math.min(givenFrequency, maxFrequency), minFrequency);	
	}
	
	
	/** 
	 * turn dial(degrees) to find current frequency
	 * calculat the range bwteween maxFreqency and minFrequency,
	 * covert degree to frequency by (degrees / 360) * range + frequency from last run 
	 * @param degrees
	 */
	public void turnDial(double degrees)
	{
		double range        = maxFrequency - minFrequency;		
		double degreeToFreq = ((degrees / 360) * range)+ currentFrequency;
		
		currentFrequency    = Math.max(minFrequency, Math.min(degreeToFreq, maxFrequency));
	}
	
	/**
	 * FIND the frequency of given station number 
	 * @param stationNumber
	 */
	public void setTunerFromStationNumber(int stationNumber)
	{
		double bandWidth    = (maxFrequency - minFrequency) / numStation;   //range of each station 
		int    newStation   = Math.max(0, Math.min(stationNumber, numStation - 1));  
	    
		currentFrequency    = (newStation * bandWidth) +(bandWidth * 0.5) + minFrequency;
	}
	
	
     /** 
      * Sets the tuner to the frequency of the next station above the current one
      */
     public void seekUp() 
     {
    	int oldNumStation = findStationNumber();
    	int newNumStation = (oldNumStation + 1) % numStation; 
    	setTunerFromStationNumber(newNumStation);   //find frequency for new Number station
     }

     /**
      * Sets the tuner to the frequency of the next station below the current one
      */
    public void seekDown() 
    {
    	int oldNumStation = findStationNumber();
    	int newNumStation = (oldNumStation + numStation - 1) % numStation;
    	setTunerFromStationNumber(newNumStation);    //find frequency for new Number station
    }

    /**
     * Stores the current station number as the preset.
     */
   
    public void savePreset() 
    {
       int currentStation = findStationNumber();   
       presetStationNum   = currentStation;        //should i creat this instance variable?
    }

    /**
     * Sets the tuner to the exact broadcast frequency of the current preset station number
     */
    public void goToPreset() 
    { 
       setTunerFromStationNumber(presetStationNum);	
    }
   

}
