package nz.tsb.stevenli.dao;

import java.util.List;
import java.util.Optional;
import nz.tsb.stevenli.entity.Account;

/** The interface of operating account table data. */
public interface AccountDao {
  /**
   * Returns all the accounts for a customer.
   *
   * @param customerNumber the given customer number.
   * @param customerUniqueId the given customer unique Id.
   * @return the list of account data.
   */
  List<Account> getAccount(String customerNumber, String customerUniqueId);

  /**
   * Returns a specific account.
   *
   * @param accountId the given account id.
   * @return the account data. Empty if no such account exists.
   */
  Optional<Account> getAccountById(long accountId);

  /**
   * Update an account.
   *
   * @param account the account entity that contains new data.
   */
  void update(Account account);

  /**
   * Persist an account.
   *
   * @param account the account entity to be persisted.
   */
  void save(Account account);
}
