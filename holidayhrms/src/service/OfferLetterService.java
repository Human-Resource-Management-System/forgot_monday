package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import DAO_Interfaces.OfferLetterDAO;
import models.EmploymentOfferDocComposite;
import models.EmploymentOfferDocument;
import models.HrmsEmploymentOffer;
import models.InductionDocumentTypes;
import models.OfferModel;

public class OfferLetterService {
	private final Logger logger = LoggerFactory.getLogger(OfferLetterService.class);

	private OfferLetterDAO offerLetterDAO;

	@Autowired
	private ApplicationContext context;

	@Autowired
	public OfferLetterService(OfferLetterDAO offerLetterDAO) {
		this.offerLetterDAO = offerLetterDAO;
	}

	@Transactional
	public void updateEmploymentOfferDocuments(HrmsEmploymentOffer employmentOfferModel, OfferModel of) {
		logger.info("Updating employment offer documents");
		logger.debug("Getting the candidate ID from the employment offer model");

		System.out.println("in here");
		// getting eofrId
		int eofrId = employmentOfferModel.getCandidateId();
		// getting the list of documents should bring by candidate
		logger.debug("Getting the list of documents to bring by the candidate");
		List<String> documentsToBring = of.getDocuments();
		logger.debug("Documents to bring: {}", documentsToBring);

		System.out.println(documentsToBring + "newly updarted code");
		logger.debug("Fetching the induction documents from the database");
		// setting inductionDocumentTypes model from inductionDocumentTypes table
		List<InductionDocumentTypes> inductionDocuments = offerLetterDAO.getInductionDocuments();
		logger.debug("Induction documents: {}", inductionDocuments);

		System.out.println(inductionDocuments);
		int docIndex = 1;
		for (String document : documentsToBring) {
			logger.debug("Processing document: {}", document);

			// getting IdtyId by the document name
			int idtyId = offerLetterDAO.findIdtyIdByTitle(inductionDocuments, document);

			EmploymentOfferDocComposite empoffdocComposite = context.getBean(EmploymentOfferDocComposite.class);
			EmploymentOfferDocument employmentofferdocument = context.getBean(EmploymentOfferDocument.class);

			empoffdocComposite.setOfferid(eofrId);
			empoffdocComposite.setDocumentIndex(docIndex);
			employmentofferdocument.setEmpoff(empoffdocComposite);
			employmentofferdocument.setOfferidentity(idtyId);
			logger.debug("Updated employment offer document: {}", employmentofferdocument);

			System.out.println(employmentofferdocument);
			// update the data into data base which got from entity model of employmentofferdocuments
			offerLetterDAO.updateEmploymentOfferDocuments(employmentofferdocument);
			docIndex++;
		}
		logger.info("Employment offer documents updated successfully");

	}

}
