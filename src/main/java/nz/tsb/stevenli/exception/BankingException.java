package nz.tsb.stevenli.exception;

/** Customized an unchecked exception to store banking error info. */
public class BankingException extends RuntimeException {
  private static final long serialVersionUID = 3770914963106537900L;

  /**
   * BankingException constructor with single error message.
   *
   * @param message error message.
   */
  public BankingException(final String message) {
    super(message);
  }

  /**
   * BankingException constructor with error message and cause.
   *
   * @param message error message.
   * @param cause the cause of exception.
   */
  public BankingException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
