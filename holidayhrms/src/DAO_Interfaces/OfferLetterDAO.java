package DAO_Interfaces;

import java.util.List;

import models.Candidate;
import models.Employee;
import models.EmploymentOfferDocument;
import models.HrmsEmploymentOffer;
import models.InductionDocumentTypes;

public interface OfferLetterDAO {

	List<Candidate> findAllIssuedCandidates();

	Candidate getCandidateById(int employeeId);

	int getLatestEofrIdFromDatabase();

	void insertEofrInto(HrmsEmploymentOffer eofr);

	Employee getHrById(int hR_id);

	List<String> getAllDocuments();

	void updateCandidateStatus(String cand_status, String newValue);

	List<Candidate> findAllProvidedCandidates();

	// void updateEmploymentOfferDocuments(HrmsEmploymentOffer employmentOfferModel, OfferModel of);

	List<InductionDocumentTypes> getInductionDocuments();

	int findIdtyIdByTitle(List<InductionDocumentTypes> inductionDocuments, String document);

	// void updateEmploymentOfferDocuments(EmploymentOfferDocument employmentofferdocument);

	void updateEmploymentOfferDocuments(EmploymentOfferDocument employmentofferdocument);

	// void updateEmploymentOfferDocuments();

}
