package nz.tsb.stevenli.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import nz.tsb.stevenli.entity.Account;
import nz.tsb.stevenli.entity.Account_;
import nz.tsb.stevenli.entity.Customer_;

/** The implementation class for operating account table data from database. */
@Component
public class AccountDaoImpl implements AccountDao {

  @PersistenceContext private EntityManager entityManager;

  @Transactional(readOnly = true)
  @Override
  public List<Account> getAccount(final String customerNumber, final String customerUniqueId) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Account> query = criteriaBuilder.createQuery(Account.class);
    final Root<Account> root = query.from(Account.class);
    Predicate equalCustomerNumber =
        criteriaBuilder.equal(
            root.get(Account_.customer).get(Customer_.customerNumber), customerNumber);
    Predicate equalCustomerId =
        criteriaBuilder.equal(
            root.get(Account_.customer).get(Customer_.customerUniqueId), customerUniqueId);
    query.select(root).where(criteriaBuilder.and(equalCustomerNumber, equalCustomerId));
    return entityManager.createQuery(query).getResultList();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Account> getAccountById(final long accountId) {
    return Optional.ofNullable(entityManager.find(Account.class, accountId));
  }

  @Transactional(readOnly = false)
  @Override
  public void update(final Account account) {
    entityManager.merge(account);
  }

  @Transactional(readOnly = false)
  @Override
  public void save(final Account account) {
    entityManager.persist(account);
  }
}
