package pl.ksprzk.docmanager.persistence.comment;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Transactional
interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>{

}
