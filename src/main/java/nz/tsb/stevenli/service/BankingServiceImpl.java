package nz.tsb.stevenli.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nz.tsb.stevenli.dao.AccountDao;
import nz.tsb.stevenli.dao.TransactionDao;
import nz.tsb.stevenli.data.TransactionStatus;
import nz.tsb.stevenli.entity.Account;
import nz.tsb.stevenli.entity.Transaction;
import nz.tsb.stevenli.exception.BankingException;

/** The implementation class of {@link BankingService} interface. */
@Service
public class BankingServiceImpl implements BankingService {

  private static final Logger LOGGER = LogManager.getLogger(BankingServiceImpl.class);

  private static final String INVALID_ACCOUNT = "Transfer failed due to invalid account id";

  @Autowired private AccountDao accountDao;
  @Autowired private TransactionDao transactionDao;

  @Override
  public List<Account> getAccount(final String customerNumber, final String customerUniqueId) {
    LOGGER.info(
        "Fetching accounts for customerNumber={}, customerUniqueId={}",
        customerNumber,
        customerUniqueId);
    return accountDao.getAccount(customerNumber, customerUniqueId);
  }

  @Override
  public List<Transaction> getTransaction(final long accountId) {
    LOGGER.info("Fetching transactions for accountId={}", accountId);
    return transactionDao.getTransaction(accountId);
  }

  @Transactional(readOnly = false)
  @Override
  public void transfer(final long accountFromId, final long accountToId, final double amount) {
    LOGGER.info(
        "Starting transfer from account {} to account {} with amount {}",
        accountFromId,
        accountToId,
        amount);
    final Optional<Account> opAccountFrom = accountDao.getAccountById(accountFromId);
    if (opAccountFrom.isEmpty()) {
      throw new BankingException(INVALID_ACCOUNT);
    }
    final Account accountFrom = opAccountFrom.get();

    final Optional<Account> opAccountTo = accountDao.getAccountById(accountToId);
    if (opAccountTo.isEmpty()) {
      throw new BankingException(INVALID_ACCOUNT);
    }
    final Account accountTo = opAccountTo.get();

    if (!StringUtils.equals(
            accountFrom.getCustomer().getCustomerNumber(),
            accountTo.getCustomer().getCustomerNumber()) ||
        !StringUtils.equals(
            accountFrom.getCustomer().getCustomerUniqueId(),
            accountTo.getCustomer().getCustomerUniqueId())) {
      throw new BankingException(
          "Transfer failed because two accounts belong to different customers.");
    }

    if (accountFrom.getAmount() - amount < 0) {
      throw new BankingException("Transfer failed because the balance is low.");
    }

    accountFrom.setAmount(accountFrom.getAmount() - amount);
    accountTo.setAmount(accountTo.getAmount() + amount);

    final OffsetDateTime createdDate = OffsetDateTime.now();

    final Transaction transactionFrom = new Transaction();
    transactionFrom.setAccount(accountFrom);
    transactionFrom.setAccountFrom(accountFromId);
    transactionFrom.setAccountTo(accountToId);
    transactionFrom.setAmount(amount);
    transactionFrom.setCreatedDate(createdDate);
    transactionFrom.setDescription("Transaction started from this account.");
    transactionFrom.setTransactionStatus(TransactionStatus.SUCCESS);
    accountFrom.getTransaction().add(transactionFrom);
    accountDao.update(accountFrom);

    final Transaction transactionTo = new Transaction();
    transactionTo.setAccount(accountTo);
    transactionTo.setAccountFrom(accountFromId);
    transactionTo.setAccountTo(accountToId);
    transactionTo.setAmount(amount);
    transactionTo.setCreatedDate(createdDate);
    transactionTo.setDescription("Transaction ended in this account.");
    transactionTo.setTransactionStatus(TransactionStatus.SUCCESS);
    accountTo.getTransaction().add(transactionTo);
    accountDao.update(accountTo);

    LOGGER.info(
        "Successfully transferred from account {} to account {} with amount {}",
        accountFromId,
        accountToId,
        amount);
  }
}
