package service;

import java.util.List;

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
	private OfferLetterDAO offerLetterDAO;

	@Autowired
	private ApplicationContext context;

	@Autowired
	public OfferLetterService(OfferLetterDAO offerLetterDAO) {
		this.offerLetterDAO = offerLetterDAO;
	}

	@Transactional
	public void updateEmploymentOfferDocuments(HrmsEmploymentOffer employmentOfferModel, OfferModel of) {

		System.out.println("in here");
		// getting eofrId
		int eofrId = employmentOfferModel.getCandidateId();
		// getting the list of documents should bring by candidate
		List<String> documentsToBring = of.getDocuments();

		System.out.println(documentsToBring + "newly updarted code");
		// setting inductionDocumentTypes model from inductionDocumentTypes table
		List<InductionDocumentTypes> inductionDocuments = offerLetterDAO.getInductionDocuments();

		System.out.println(inductionDocuments);
		int docIndex = 1;
		for (String document : documentsToBring) {
			// getting IdtyId by the document name
			int idtyId = offerLetterDAO.findIdtyIdByTitle(inductionDocuments, document);

			EmploymentOfferDocComposite empoffdocComposite = context.getBean(EmploymentOfferDocComposite.class);
			EmploymentOfferDocument employmentofferdocument = context.getBean(EmploymentOfferDocument.class);

			empoffdocComposite.setOfferid(eofrId);
			empoffdocComposite.setDocumentIndex(docIndex);
			employmentofferdocument.setEmpoff(empoffdocComposite);
			employmentofferdocument.setOfferidentity(idtyId);

			System.out.println(employmentofferdocument);
			// update the data into data base which got from entity model of employmentofferdocuments
			offerLetterDAO.updateEmploymentOfferDocuments(employmentofferdocument);
			docIndex++;
		}
	}

}
