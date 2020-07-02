package com.seanroshan.superduperdrivecloudstorage.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains String Utilities for validation and related methods
 *
 * @author Sean Roshan
 */
public class ValidationUtils {

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Method to validate string fields
     *
     * @param input      - input string for validation
     * @param isRequired - field to specify if the field is required
     * @param minLength  - minimum length of the string
     * @param maxLength  - maximum length of the string
     * @return {@link Boolean}
     */
    public static boolean validateString(String input, boolean isRequired, int minLength, int maxLength) {
        LOGGER.debug(input, isRequired, minLength, maxLength);
        boolean isValid = (!isRequired && (input == null || input.isEmpty())) || input.length() >= minLength && input.length() <= maxLength;
        LOGGER.debug(isValid);
        return isValid;
    }


    /**
     * Method to validate Pattern
     *
     * @param inputData    - input inputData for validation
     * @param inputPattern - pattern to validate input
     * @param isRequired   - accepts null and empty
     * @return {@link Boolean}
     */
    public static boolean validatePattern(String inputData, String inputPattern, boolean isRequired) {
        LOGGER.debug(inputData, isRequired);
        boolean isValid = !isRequired && (inputData == null || inputData.isEmpty()) || (inputData != null && !inputData.isEmpty() && inputData.matches(inputPattern));
        LOGGER.debug(isValid);
        return isValid;
    }

    /**
     * Method to validate Password
     *
     * @param inputData - input inputData for validation
     * @return {@link Boolean}
     */
    public static boolean validatePassword(String inputData) {
        LOGGER.debug(inputData);
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%^&*_])[a-zA-Z\\d!@#$%^&*_]{8,20}$";
        boolean isValid = validatePattern(inputData, passwordPattern, true);
        LOGGER.debug(isValid);
        return isValid;
    }

}
