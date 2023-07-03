package controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO_Interfaces.OfferLetterDAO;
import models.Candidate;
import models.Employee;
import models.HrmsEmploymentOffer;
import models.OfferModel;
import service.OfferLetterMailService;
import service.OfferLetterService;

@Controller
public class OfferLetterController {

	private final Logger logger = LoggerFactory.getLogger(OfferLetterController.class);

	private OfferLetterDAO offerLetterDAO;
	private OfferModel of;
	private OfferLetterService offerservice;

	@Autowired
	public OfferLetterController(OfferLetterDAO offerLetterDAO, OfferLetterService offerservice) {
		this.offerLetterDAO = offerLetterDAO;
		this.offerservice = offerservice;
	}

	// getting data of candidates whose offerletters are already provided
	@RequestMapping(value = "/provided", method = RequestMethod.GET)
	public String getOfferLetterProvidedCandidates(Model model) {
		logger.info("Request received for  offers already provided");

		List<Candidate> candidates = offerLetterDAO.findAllProvidedCandidates();
		model.addAttribute("candidates", candidates);
		return "OfferProvidedCandidates";
	}

	// getting data of candidates whose offerletters have to be issue
	@RequestMapping(value = "/issue", method = RequestMethod.GET)
	public String getIssuingCandidates(Model model) {
		logger.info("Request received for  offers to be issue");

		List<Candidate> candidates = offerLetterDAO.findAllIssuedCandidates();
		model.addAttribute("candidates", candidates);
		return "offerCandidates";
	}

	// getting a form for issuing offerletter with details of candidates and respective admin automatically
	@RequestMapping("/get-candidate-details")
	public String getCandidateDetails(@RequestParam("id") int candidateId, Model model) {
		logger.info(
				"after selection of a candidate for issue offerletter getting candidate details or candidate object");

		Candidate candidate = offerLetterDAO.getCandidateById(candidateId);
		int HR_id = 301;
		Employee emp = offerLetterDAO.getHrById(HR_id);
		logger.info(
				"getting hr details for specifing the hr details on the offerletter (nothing but the admin who has logged in)");

		List<String> listOfDocuments = offerLetterDAO.getAllDocuments();

		System.out.println(listOfDocuments);

		logger.info(
				"getting the list of documents should bring while coming to induction , it will be selected by hr from dropdown");

		model.addAttribute("candidate", candidate);
		model.addAttribute("hr", emp);
		model.addAttribute("listOfDocuments", listOfDocuments);

		return "candidateOfferForm";
	}

	// redirect the
	@RequestMapping("/email")
	public String sendOfferMail(@Validated OfferModel offerModel, Model model) {
		of = offerModel;
		logger.info("getting all the data from the filled by hr storing it in the offerModel ");

		System.out.println(offerModel.getDocuments());
		model.addAttribute("offerModel", offerModel);

		// Return the appropriate view
		return "offerEmail";
	}

	// insert the candidate data in emplomentOffers table , employmentOfferDocuments table and changing status of
	// employee from NA to AC
	@RequestMapping("/sendOfferLetter")
	public ResponseEntity<String> redirectedFromOfferLetter(HrmsEmploymentOffer eofr, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		eofr.setOfferId(offerLetterDAO.getLatestEofrIdFromDatabase() + 1);
		logger.info("setting refid");
		logger.info("Setting refid for the employment offer");
		eofr.setReferenceId("ref " + eofr.getOfferId());
		eofr.setCandidateId(Integer.parseInt(of.getCandidateId()));
		System.out.println(of);

		eofr.setHrEmail(of.getAdminEmail());
		eofr.setHrMobileNumber(Long.parseLong(of.getAdminMobile()));
		eofr.setOfferDate(Date.valueOf(of.getOfferDate()));
		eofr.setOfferedJob(of.getOfferedJob());
		eofr.setReportingDate(Date.valueOf(LocalDate.parse(of.getReportingDate())));
		eofr.setStatus("INPR");
		logger.info("setting all the data to HrmsEmploymentOffer for inserting into employmentoffers table");

		try {
			OfferLetterMailService.sendEmail(request, response, of);
			logger.info("Email sent successfully to the candidate");

		} catch (Exception e) {
			logger.error("Error occurred while sending email to the candidate", e);

			e.printStackTrace();
		}
		logger.info("sent mail to the candidate");

		offerLetterDAO.insertEofrInto(eofr);
		logger.info("inserting data into HrmsEmploymentOffer here by calling dao method");

		offerservice.updateEmploymentOfferDocuments(eofr, of);
		logger.info("inserting into  employmentofferdocuemnts table");

		offerLetterDAO.updateCandidateStatus("cand_status", "AC");
		logger.info("finally after all, now change candidate status from NA to AC");

		return ResponseEntity.ok("SUCCESS");

	}

}
