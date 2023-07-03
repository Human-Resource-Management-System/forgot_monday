package testcontroller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DAO_Interfaces.OfferLetterDAO;
import controllers.OfferLetterController;
import models.Candidate;

public class OfferLetterControllerTest {

	@Mock
	@Autowired
	private OfferLetterDAO offerLetterDAO;

	@Mock
	@Autowired
	private OfferLetterController offerLetterController;

	@BeforeMethod
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetOfferLetterProvidedCandidates() {
		// Prepare test data
		List<Candidate> candidates = new ArrayList<>();
		candidates.add(new Candidate());
		candidates.add(new Candidate());
		when(offerLetterDAO.findAllProvidedCandidates()).thenReturn(candidates);

		// Mock the Model object
		Model model = mock(Model.class);

		// Call the controller method
		String viewName = offerLetterController.getOfferLetterProvidedCandidates(model);

		// Verify the interaction with the offerLetterDAO
		verify(offerLetterDAO).findAllProvidedCandidates();

		// Verify the interaction with the Model
		verify(model).addAttribute("candidates", candidates);

		// Assert the view name returned by the controller method
		assertEquals("OfferProvidedCandidates", viewName);
	}
}
