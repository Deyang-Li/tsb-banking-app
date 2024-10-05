package nz.tsb.stevenli.data;

import java.util.List;
import nz.tsb.stevenli.entity.Account;

/**
 * A record to store get accounts API's response.
 *
 * @param accounts the list of accounts.
 */
public record AccountResponse(List<Account> accounts) {
}
