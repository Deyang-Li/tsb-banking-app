package nz.tsb.stevenli.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import nz.tsb.stevenli.entity.Account_;
import nz.tsb.stevenli.entity.Transaction;
import nz.tsb.stevenli.entity.Transaction_;

/** The implementation class for operating transaction table data from database. */
@Component
public class TransactionDaoImpl implements TransactionDao {

  @PersistenceContext private EntityManager entityManager;

  @Transactional(readOnly = true)
  @Override
  public List<Transaction> getTransaction(final long accountId) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Transaction> query = criteriaBuilder.createQuery(Transaction.class);
    final Root<Transaction> root = query.from(Transaction.class);
    Predicate equalAccountId =
        criteriaBuilder.equal(root.get(Transaction_.account).get(Account_.accountId), accountId);
    query.select(root).where(criteriaBuilder.and(equalAccountId));
    return entityManager.createQuery(query).getResultList();
  }

  @Transactional(readOnly = false)
  @Override
  public void save(final Transaction transaction) {
    entityManager.persist(transaction);
  }
}
