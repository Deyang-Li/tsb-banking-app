package nz.tsb.stevenli.service;

import java.util.List;
import nz.tsb.stevenli.entity.Account;
import nz.tsb.stevenli.entity.Transaction;

/** Interface which provides banking services. */
public interface BankingService {
  /**
   * Returns all the accounts for a customer.
   *
   * @param customerNumber the given customer number.
   * @param customerUniqueId the given customer unique Id.
   * @return the list of account data.
   */
  List<Account> getAccount(String customerNumber, String customerUniqueId);

  /**
   * Returns all the transactions for an account.
   *
   * @param accountId the given account id.
   * @return the list of transaction data.
   */
  List<Transaction> getTransaction(long accountId);

  /**
   * Make a transfer between two accounts that belong to a customer.
   *
   * @param accountFromId the account id where the transfer is from.
   * @param accountToId the account id where the transfer is to.
   * @param amount the transfer amount.
   * @throws BankingException If the transfer failed.
   */
  void transfer(long accountFromId, long accountToId, double amount);
}
