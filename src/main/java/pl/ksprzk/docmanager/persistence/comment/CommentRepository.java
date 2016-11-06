package pl.ksprzk.docmanager.persistence.comment;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Przemysław Kasprzyk
 */
interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>{

}
