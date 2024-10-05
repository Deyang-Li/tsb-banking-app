package nz.tsb.stevenli;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import nz.tsb.stevenli.dao.CustomerDao;
import nz.tsb.stevenli.data.Status;
import nz.tsb.stevenli.data.TransactionStatus;
import nz.tsb.stevenli.entity.Account;
import nz.tsb.stevenli.entity.Customer;
import nz.tsb.stevenli.entity.Transaction;

/** Entry class for the tsb banking app service. */
@SpringBootApplication(scanBasePackageClasses = {TsbBankingAppApplication.class})
public class TsbBankingAppApplication extends SpringBootServletInitializer {

  private static final double TRANSACTION_AMOUNT = 100.0;
  private static final double ACCOUNT1_BALANCE = 10000.0;
  private static final double ACCOUNT2_BALANCE = 12000.0;

  @Autowired private CustomerDao customerDao;

  /**
   * Entry point for the service.
   *
   * @param args Provided command line arguments.
   */
  public static void main(final String[] args) {
    SpringApplication.run(TsbBankingAppApplication.class, args);
  }

  /**
   * Initialize the database when starting the application.
   *
   * @return {@link CommandLineRunner} class.
   */
  @Bean
  CommandLineRunner runner() {
    return args -> {
      final OffsetDateTime nowDateTime = OffsetDateTime.now();
      final Transaction transaction1 = new Transaction();
      transaction1.setAccountFrom(1);
      transaction1.setAccountTo(2);
      transaction1.setAmount(TRANSACTION_AMOUNT);
      transaction1.setCreatedDate(nowDateTime);
      transaction1.setDescription("Transaction started from this account.");
      transaction1.setTransactionStatus(TransactionStatus.SUCCESS);

      final Transaction transaction2 = new Transaction();
      transaction2.setAccountFrom(1);
      transaction2.setAccountTo(2);
      transaction2.setAmount(TRANSACTION_AMOUNT);
      transaction2.setCreatedDate(nowDateTime);
      transaction2.setDescription("Transaction ended in this account.");
      transaction2.setTransactionStatus(TransactionStatus.SUCCESS);

      final Account account1 = new Account();
      account1.setAccountDescription("This is account 1.");
      account1.setAccountName("account1");
      account1.setAccountNumber("32353630");
      account1.setAmount(ACCOUNT1_BALANCE);
      account1.setCreatedDate(OffsetDateTime.parse("2024-10-04T10:15:30+13:00"));
      account1.setStatus(Status.ACTIVE);
      account1.setTransaction(List.of(transaction1));

      final Account account2 = new Account();
      account2.setAccountDescription("This is account 2.");
      account2.setAccountName("account2");
      account2.setAccountNumber("36598901");
      account2.setAmount(ACCOUNT2_BALANCE);
      account2.setCreatedDate(OffsetDateTime.parse("2024-10-05T15:21:30+13:00"));
      account2.setStatus(Status.ACTIVE);
      account2.setTransaction(List.of(transaction2));

      final Customer customer = new Customer();
      customer.setCreatedDate(OffsetDateTime.parse("2023-12-21T12:17:30+13:00"));
      customer.setCustomerNumber("123456");
      customer.setCustomerUniqueId("CU0001");
      customer.setDateOfBirth("06/11/1988");
      customer.setFirstName("Steven");
      customer.setLastName("Li");
      customer.setAccount(List.of(account1, account2));

      account1.setCustomer(customer);
      account2.setCustomer(customer);

      transaction1.setAccount(account1);
      transaction2.setAccount(account2);
      customerDao.save(customer);
    };
  }
}
