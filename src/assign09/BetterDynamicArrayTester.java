package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the BetterDynamicArray class. 
 * 
 * @author CS 1420 course staff and Matthew Suggars
 * @version November 1 2025.
 */
public class BetterDynamicArrayTester {   
    
	// These variables can be used in each test.
	// Feel free to add more.
	private static BetterDynamicArray threeEvents;
	private static AudioEvent e1, e2, e3, e4, e5, e6;
	
	// A private helper for making AudioEvent objects.
	// This only uses VolumeEvent objects, but assuming your
	// AudioEvent subclasses are correct, it should work for any of them.
	private static AudioEvent makeEvent(int time) {
		return new VolumeEvent(time, 1, 1);
	}
	
	// This code executes before each test.
	// You can reference threeEvents in your tests without having to create it.
	@BeforeEach
	public void setup(){
		e1 = makeEvent(1);
		e2 = makeEvent(2);
		e3 = makeEvent(3);
		e4 = makeEvent(4);
		e5 = makeEvent(5);
		e6 = makeEvent(6);
		threeEvents = new BetterDynamicArray();
		threeEvents.add(e1);
		threeEvents.add(e2);
		threeEvents.add(e3);
	}
       
    @Test
    public void testConstructor() {
        BetterDynamicArray array = new BetterDynamicArray();
        assertEquals(0, array.size(), "Constructor did not yield a 0-sized dynamic array");
        assertEquals("[] backing array length: 10", array.toString(), 
        		"Constructor did not yield the correct dynamic array (via toString)");
    }
        
    @Test
    public void testConstructorCreatesDistinctArrays() {
        BetterDynamicArray array = new BetterDynamicArray();
        BetterDynamicArray otherArray = new BetterDynamicArray();
        otherArray.add(new VolumeEvent(4, 1, 1));
        assertEquals(0, array.size(), 
        		"Appending an element to one DynamicArray object changed the size of a different DynamicArray object");
    }
    
    @Test
    public void testAppendSimple() {
        String expected = "[" + e1 + ", " + e2 + ", " + e3 + "]" + " backing array length: 10";
        assertEquals(expected, threeEvents.toString(), "Failed adding 3 elements to empty dynamic array");
        assertEquals(3, threeEvents.size(), "Incorrect size after adding 3 elements to dynamic array");
    }
    
    @Test
    public void testAppendLarger() {
        // Appending >= 10 elements tests the double-length growth of a dynamic array.
        AudioEvent[] largeArray = new AudioEvent[11];
        for(int i = 0; i < largeArray.length; i++) {
        	largeArray[i] = makeEvent(i);
        }
        BetterDynamicArray array = new BetterDynamicArray();
        for(AudioEvent elem : largeArray) 
            array.add(elem);
        String expected = Arrays.toString(largeArray) + " backing array length: 20";
        assertEquals(expected, array.toString(), "Failed adding 11 elements to dynamic array");
        assertEquals(11, array.size(), "Incorrect size after adding 11 elements to dynamic array");
    }
    
    @Test
    public void testInsertFront() {
        threeEvents.insert(0, e5);
        String expected = "[" + e5 + ", " + e1 + ", " + e2 + ", " + e3 + "]" + " backing array length: 10";
        assertEquals(expected, threeEvents.toString(), "Failed inserting an element to the front");
        assertEquals(4, threeEvents.size(), "Incorrect size after inserting element to the front");
    }
    
    @Test
    public void testInsertMiddle() {
    	threeEvents.insert(1, e6);
        String expected = "[" + e1 + ", " + e6 + ", " + e2 + ", " + e3 + "]" + " backing array length: 10";
        assertEquals(expected, threeEvents.toString(), "Failed inserting an element to the middle");
        assertEquals(4, threeEvents.size(), "Incorrect size after inserting element to the middle");
    }
    
    @Test
    public void testInsertEnd() {
    	threeEvents.insert(3, e4);
        String expected = "[" + e1 + ", " + e2 + ", " + e3 + ", " + e4 + "]" + " backing array length: 10";
        assertEquals(expected, threeEvents.toString(), "Failed inserting an element to the end");
        assertEquals(4, threeEvents.size(), "Incorrect size after inserting element to the end");
    }
    
    @Test
    public void testInsertInvalidLowIndex() {
    	// This assertion checks that calling insert with an index that is too low throws the
    	// IndexIndexOutOfBoundsException.
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.insert(-1, e4); },
    			"Failed to throw exception when inserting with too-low index");
    }
    
    @Test
    public void testInsertInvalidHighIndex() {
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.insert(4, e4); }, 
    			"Failed to throw exception when inserting with too-high index");
    }
    
    @Test
    public void testGetElement(){
        assertEquals(e1, threeEvents.get(0), "Failed getting element from front");
        assertEquals(e2, threeEvents.get(1), "Failed getting element from middle");
        assertEquals(e3, threeEvents.get(2), "Failed getting element from end");
    }
    
    @Test
    public void testGetElementSizeUnchanged(){
        threeEvents.get(0);
        threeEvents.get(1);
        threeEvents.get(2);
        assertEquals(3, threeEvents.size(), "Calling getElement changed the size of dynamic array");
    }
    
    @Test
    public void testGetElementInvalidLow() {
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.get(-1); }, 
    			"Failed to throw exception when getting element with too-low index");
    }
    
    @Test
    public void testGetElementInvalidHigh() {
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.get(3); },
    			"Failed to throw exception when getting element with too-high index");
    }
    
    @Test
    public void testDoublingIsFaster() {
        double betterDynamicArrayTime = DynamicArrayTimer.addToBetterDynamicArray(10000);
        double regularDynamicArrayTime = DynamicArrayTimer.addToDynamicArray(10000);
        assertTrue(betterDynamicArrayTime < regularDynamicArrayTime,
                "The time to add 10k items to a doubling dynamic array should be faster, " +
                "but it is not with the current implementation");
    }
    
    // Step 1: Add tests for set, remove, and sort methods.
    @Test
    public void testSetElement() {
    	AudioEvent e7 = new VolumeEvent(7, 1, 1);
    	threeEvents.set(0, e7);
    	threeEvents.set(1, e1);
    	threeEvents.set(2, e2);
    	assertEquals(e7, threeEvents.get(0), "Does not set element properly");
    	assertEquals(e1, threeEvents.get(1), "Does not set element properly");
    	assertEquals(e2, threeEvents.get(2), "Does not set element properly");
    }
    
    @Test
    public void testSetElementDoesNotChangeSize() {
    	AudioEvent e7 = new VolumeEvent(7, 1, 1);
    	threeEvents.set(0, e7);
    	assertEquals(3, threeEvents.size(), 
    			"Setting an element in the array changes the size of the array");
    }
    
    @Test
    public void testSetElementInvalidLow() {
    	assertThrows(IndexOutOfBoundsException.class, () -> {threeEvents.set(-1, e1);}, 
    			"Sets element in array when index is out of bounds");
    }
    
    @Test
    public void testSetElementInvalidHigh() {
    	assertThrows(IndexOutOfBoundsException.class, () -> {threeEvents.set(3, e1);}, 
    			"Sets element in array when index is out of bounds");
    }
    @Test
    public void testRemoveElement() {
    	threeEvents.remove(e1);
    	assertNotEquals(e1, threeEvents.get(0), "Does not remove element from array");
    }
    
    @Test
    public void testRemoveElementChangesElementCount() {
    	threeEvents.remove(e1);
    	assertEquals(2, threeEvents.size(), "Removing an element from array changes sixe of array");
    }
    
    @Test
    public void testRemoveIndexInvalidHigh() {
    	assertThrows(IndexOutOfBoundsException.class, () -> {threeEvents.remove(3);} , 
    			"Attempts to remove element with index too high");
    }
    
    @Test
    public void testRemoveIndexInvalidLow() {
    	assertThrows(IndexOutOfBoundsException.class, () -> {threeEvents.remove(-1);}, 
    			"Attempts to remove element with index too low");
    }
    
    @Test
    public void testSort() {
    	
    }
    
    // Step 2: Add tests for all methods that operate on a larger dynamic array that has doubled its length.
}