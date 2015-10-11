package com.fmi.dm.hw1;

/**
 *
 * @author Dimitar
 */
public class LeapingFrogsMain {
    public static void main(String[] args) {
        LeapingFrogs leapingFrogs = new LeapingFrogs(1);
        leapingFrogs.solve();
        
        leapingFrogs.setFrogsPerSide(2);
        leapingFrogs.solve();
        
        leapingFrogs.setFrogsPerSide(3);
        leapingFrogs.solve();
        
        leapingFrogs.setFrogsPerSide(4);
        leapingFrogs.solve();
                
        leapingFrogs.setFrogsPerSide(5);
        leapingFrogs.solve();
       
        leapingFrogs.setFrogsPerSide(15);
        leapingFrogs.solve();
    }
}
