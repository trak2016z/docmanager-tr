package pl.ksprzk.docmanager.persistence.document;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Przemysław Kasprzyk
 */
interface DocumentRepository extends PagingAndSortingRepository<Document, Integer>{

}
