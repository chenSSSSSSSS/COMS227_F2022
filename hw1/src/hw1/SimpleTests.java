package hw1;
import hw1.CarStereo;

public class SimpleTests {
	public static void main (String []args)
	{
		
	// test volume
		CarStereo c = new CarStereo( 100, 200, 5);
		System.out.println(c.getVolume());   //0.5
		c.louder();
		c.louder();
		System.out.println(c.getVolume());   //0.82
		c.louder();
        c.louder();
        System.out.println(c.getVolume());   //1.0
        c.quieter();
        System.out.println(c.getVolume());   //0.84
        System.out.println();
        
        //set frequency and find current frequency
        c = new CarStereo( 100, 200, 5);
		System.out.println(c.getTuner());   //100
		c.setTuner(123.4);
		System.out.println(c.getTuner());   //123.4
		c.setTuner(500);
        System.out.println(c.getTuner());   //200
        c.setTuner(42);
        System.out.println(c.getTuner());   //100
        System.out.println();
        
        //turn dail 
        c = new CarStereo( 100, 200, 5);
		System.out.println(c.getTuner());    // 100
		c.turnDial(360);
		System.out.println(c.getTuner());    //200
		c.turnDial(-180);
        System.out.println(c.getTuner());    //150
        c.turnDial(15);
        System.out.println(c.getTuner());    //154.17
        c.turnDial(720);
        System.out.println(c.getTuner());    //200
        System.out.println();
        
        
        // setting from the station number
        c = new CarStereo( 100, 200, 5);
        c.setTunerFromStationNumber(3);
        System.out.println(c.getTuner());   //170
        c.setTunerFromStationNumber(42);
        System.out.println(c.getTuner());   //190
        c.setTunerFromStationNumber(-5);
        System.out.println(c.getTuner());   //110
        System.out.println();
	    
        //findStationNumber
        c = new CarStereo( 100, 200, 5);
        c.setTuner(162);
	    System.out.println( c.findStationNumber());   //3
	    c.setTuner(134);
	    System.out.println(c.findStationNumber());   //1
	    c.setTuner(180);
	    System.out.println(c.findStationNumber());   //4
	    c.setTuner(200);
	    System.out.println(c.findStationNumber());   //4
	    System.out.println();
  
	    c = new CarStereo( 100, 200, 5);
	    c.setTuner(162);
	    c.seekUp();
	    System.out.println(c.getTuner());  //190
	    c.setTuner(185);
	    c.seekUp();
	    System.out.println(c.getTuner()); // 110
	    c.setTuner(98);
	    c.seekDown();
	    System.out.println(c.getTuner()); 
	    System.out.println();
	
	    c = new CarStereo( 100, 200, 5);
	    c.setTuner(162);
	    c.savePreset();
	    c.setTuner(123.4);
	    c.goToPreset();
	    System.out.println(c.getTuner());
	
	
	}
}
