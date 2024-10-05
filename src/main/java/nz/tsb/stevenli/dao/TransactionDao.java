package nz.tsb.stevenli.dao;

import java.util.List;
import nz.tsb.stevenli.entity.Transaction;

/** The interface of operating transaction table data. */
public interface TransactionDao {
  /**
   * Returns all the transactions for an account.
   *
   * @param accountId the given account id.
   * @return the list of transaction data.
   */
  List<Transaction> getTransaction(long accountId);

  /**
   * Persist a transaction.
   *
   * @param transaction the new transaction entity.
   */
  void save(Transaction transaction);
}
