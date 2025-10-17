package assign07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class CandidateSearchTest {
	Candidate[] candidateList;
	Candidate firstCandidate;
	Candidate lastCandidate;
	Candidate empty;
	
	@BeforeEach
	public void setup() {
		candidateList = new Candidate[5];
		candidateList[0] = new Candidate("Matthew", 26, 8);
		candidateList[1] = new Candidate("Angie", 24, 9);
		candidateList[2] = new Candidate("Danny", 32, 7);
		candidateList[3] = new Candidate("Logan", 28, 6);
		candidateList[4] = new Candidate("Mario",  23, 10);
		
		Candidate firstCandidate = new Candidate("Matthew", 26, 8);
		Candidate lastCandidate = new Candidate("Mario",  23, 10);
		Candidate empty = new Candidate("", 1);
	}

	/*
	 * Test cases for sequentialSearch
	 */
	@Test
	public void testSequentialSearchCandidateExists() {
		assertEquals(firstCandidate, CandidateSearch.sequentialSearch(candidateList, firstCandidate));
	}
	
	@Test
	public void testSequentialSearchCandidateDoesNotExist() {
		Candidate kyle = new Candidate("Kyle", 41, 10);
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
	public void testGetCallCountFirstIndex() {
		Candidate me = CandidateSearch.sequentialSearch(candidateList, new Candidate("Matthew", 26, 8));
		assertEquals(1, CandidateSearch.getCallCount());
	}
	
	@Test
	public void testGetCallCountLastIndex() {
		Candidate applicant = CandidateSearch.sequentialSearch(candidateList, new Candidate("Mario", 23, 10));
		assertEquals(5, CandidateSearch.getCallCount());
	}
	
	@Test
	public void testGetCallCountNull() {
		Candidate applicant = CandidateSearch.sequentialSearch(candidateList, new Candidate("Mindy", 19, 6));
		assertEquals(6, CandidateSearch.getCallCount());
	}
	
	/**
	 * Test cases for binarySearch
	 
	@Test
	public void testBinarySearchCandidateExists() {
		assertEquals(firstCandidate, CandidateSearch.binarySearch(candidateList, firstCandidate));
	}*/
	
	
	

}
