package pl.ksprzk.docmanager.persistence.document;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Transactional
interface RateRepository extends CrudRepository<Rate, Integer>{

}
