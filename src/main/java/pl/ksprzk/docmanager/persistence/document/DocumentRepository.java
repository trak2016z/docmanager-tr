package pl.ksprzk.docmanager.persistence.document;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import pl.ksprzk.docmanager.persistence.user.User;

/**
 *
 * @author Przemysław Kasprzyk
 */
interface DocumentRepository extends PagingAndSortingRepository<Document, Integer> {

   Document findDocumentByName(String name);

   @Query(value = "select d from Document d where d.user=:user")
   List<Document> findDocumentByUser(@Param(value = "user") User user);
}
