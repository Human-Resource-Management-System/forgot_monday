package testcontroller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DAO_Interfaces.OfferLetterDAO;
import controllers.OfferLetterController;
import models.Candidate;
import models.OfferModel;

public class OfferLetterControllerTest {

	@Mock
	private OfferLetterDAO offerLetterDAO;

	@InjectMocks
	private OfferLetterController offerLetterController;

	@Mock
	private Model model;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetOfferLetterProvidedCandidates() {
		// Prepare test data
		List<Candidate> candidates = new ArrayList<>();
		candidates.add(new Candidate("John Doe"));
		candidates.add(new Candidate("Jane Smith"));

		// Mock the behavior of offerLetterDAO
		when(offerLetterDAO.findAllProvidedCandidates()).thenReturn(candidates);

		// Call the method under test
		String viewName = offerLetterController.getOfferLetterProvidedCandidates(model);

		// Verify the interactions and assertions
		verify(offerLetterDAO, times(1)).findAllProvidedCandidates();
		verify(model, times(1)).addAttribute(eq("candidates"), eq(candidates));
		assert viewName.equals("OfferProvidedCandidates");
	}

	@Test
	public void testGetIssuingCandidates() {
		// Prepare test data
		List<Candidate> candidates = new ArrayList<>();
		candidates.add(new Candidate("John Doe"));
		candidates.add(new Candidate("Jane Smith"));

		// Mock the behavior of offerLetterDAO
		when(offerLetterDAO.findAllIssuedCandidates()).thenReturn(candidates);

		// Call the method under test
		String viewName = offerLetterController.getIssuingCandidates(model);

		// Verify the interactions and assertions
		verify(offerLetterDAO, times(1)).findAllIssuedCandidates();
		verify(model, times(1)).addAttribute(eq("candidates"), eq(candidates));
		assert viewName.equals("offerCandidates");
	}

	@Test
	public void testSendOfferMail() {
		// Prepare test data
		OfferModel offerModel = new OfferModel();
		offerModel.setDocuments("Document 1");

		// Call the method under test
		String viewName = offerLetterController.sendOfferMail(offerModel, model);

		// Verify the interactions and assertions
		verify(model, times(1)).addAttribute("offerModel", offerModel);
		assert viewName.equals("offerEmail");
	}

}
