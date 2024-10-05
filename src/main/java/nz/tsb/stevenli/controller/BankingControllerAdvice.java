package nz.tsb.stevenli.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import nz.tsb.stevenli.exception.BankingException;

/** Banking Application Exception Handlers. */
@ControllerAdvice
public class BankingControllerAdvice {

  private static final Logger LOGGER = LogManager.getLogger(BankingControllerAdvice.class);

  /**
   * Handle the {@link BankingException} instance exception. Return {@link ResponseEntity} with http
   * status and error message.
   *
   * @param e the {@link BankingException} instance exception
   * @return {@link ResponseEntity} with corresponding status and message.
   */
  @ExceptionHandler(BankingException.class)
  public ResponseEntity<String> handleBankingException(final BankingException e) {
    LOGGER.info(e.getMessage(), e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle the {@link Exception} instance exception. Return {@link ResponseEntity} with http status
   * and error message.
   *
   * @param e the {@link Exception} instance exception.
   * @return {@link ResponseEntity} with corresponding status and message.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(final Exception e) {
    LOGGER.info(e.getMessage(), e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
