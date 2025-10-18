package assign07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class CandidateSearchTest {
	Candidate[] candidateList;
	Candidate firstCandidate, lastCandidate, empty, kyle, danny, logan, mario, matthew, angie, applicant;
	
	@BeforeEach
	public void setup() {
		candidateList = new Candidate[5];
		
		matthew = new Candidate("Matthew", 26, 8);
		mario = new Candidate("Mario",  23, 10);
		empty = new Candidate("", 1);
		kyle = new Candidate("Kyle", 41, 10);
		danny = new Candidate("Danny", 32);
		logan = new Candidate("Logan", 28, 6);
		angie = new Candidate("Angie", 24, 9);
		
		candidateList[0] = new Candidate("Matthew", 26, 8);
		candidateList[1] = new Candidate("Angie", 24, 9);
		candidateList[2] = new Candidate("Danny", 32, 7);
		candidateList[3] = new Candidate("Logan", 28, 6);
		candidateList[4] = new Candidate("Mario",  23, 10);
	}

	/*
	 * Test cases for sequentialSearch
	 */
	@Test
	public void testSequentialSearchCandidateExists() {
		assertEquals(matthew, CandidateSearch.sequentialSearch(candidateList, matthew));
	}
	
	@Test
	public void testSequentialSearchCandidateDoesNotExist() {
		assertEquals(null, CandidateSearch.sequentialSearch(candidateList, kyle));
	}
	
	@Test
	public void testSequentialSearchEmptyCandidate() {
		assertEquals(null, CandidateSearch.sequentialSearch(candidateList, empty));
	}
	
	/**
	 * Test cases for getCallCount
	 */
	
	@Test
	public void testGetCallCountFirstIndexSequentialSearch() {
		applicant = CandidateSearch.sequentialSearch(candidateList, new Candidate("Matthew", 26, 8));
		assertEquals(1, CandidateSearch.getCallCount());
	}
	
	@Test
	public void testGetCallCountLastIndexSequentialSearch() {
		applicant = CandidateSearch.sequentialSearch(candidateList, new Candidate("Mario", 23, 10));
		assertEquals(5, CandidateSearch.getCallCount());
	}
	
	@Test
	public void testGetCallCountNullSequentialSearch() {
		applicant = CandidateSearch.sequentialSearch(candidateList, new Candidate("Mindy", 19, 6));
		assertEquals(6, CandidateSearch.getCallCount());
	}
	
	@Test
	public void testgetCallCountFirstIndexBinarySearch() {
		applicant = CandidateSearch.binarySearch(candidateList, angie);
		assertEquals(2, CandidateSearch.getCallCount()); // has to call recursive case once
	}
	
	@Test
	public void testgetCallCountMiddleIndexBinarySearch() {
		applicant = CandidateSearch.binarySearch(candidateList, logan);
		assertEquals(1, CandidateSearch.getCallCount()); // does not have to call recursive case
	}
	
	@Test
	public void testgetCallCountLastIndexBinarySearch() {
		applicant = CandidateSearch.binarySearch(candidateList, mario);
		assertEquals(2, CandidateSearch.getCallCount());
	}
	
	@Test 
	public void testGetCallCountNullBinarySearch() {
		applicant = CandidateSearch.binarySearch(candidateList, null);
		assertEquals(0, CandidateSearch.getCallCount());
	}
	
	@Test
	public void testGetCallCountBinNotInArrayarySearch() {
		applicant = CandidateSearch.binarySearch(candidateList, kyle);
		assertEquals(4, CandidateSearch.getCallCount());
	}
	/**
	 * Test cases for binarySearch
	 **/
	@Test
	public void testBinarySearchCandidateExists() {
		assertEquals(matthew, CandidateSearch.binarySearch(candidateList, matthew));
	}
	
	@Test
	public void testBinarySearchCandidateDoesNotExist() {
		assertEquals(null, CandidateSearch.binarySearch(candidateList, kyle));
	}
	
	
	

}
