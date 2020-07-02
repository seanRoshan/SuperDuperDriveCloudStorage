package com.seanroshan.superduperdrivecloudstorage.backend.constants;

/**
 * Contains constants for error messages
 *
 * @author Sean Roshan
 */
public enum BusinessError {


    REQUIRED_FIELDS("SDDCSSU001", 1, "PLEASE MAKE SURE TO ENTER VALID DATA IN ALL REQUIRED FIELDS!"),
    INVALID_FIRST_NAME("SDDCSSU002", 2, "INVALID FIRST NAME, FIRST NAME MUST BE BETWEEN 1 to 20 CHARACTERS!"),
    INVALID_LAST_NAME("SDDCSSU003", 3, "INVALID LAST NAME, LAST NAME MUST BE BETWEEN 1 to 20 CHARACTERS!"),
    INVALID_USER_NAME("SDDCSSU004", 4, "INVALID USER NAME, USER NAME MUST BE BETWEEN 1 to 20 CHARACTERS!"),
    INVALID_PASSWORD("SDDCSSU005", 5, "INVALID PASSWORD, PASSWORD MUST BE BETWEEN 8 to 20 CHARACTERS! AND FOLLOW PASSWORD RULES!"),
    USER_NAME_EXISTS("SDDCSSU006", 6, "USER NAME ALREADY EXISTS, PLEASE CHOOSE A DIFFERENT USER NAME!");


    private final String businessErrorCode;
    private final int errorCode;
    private final String errorMessage;

    BusinessError(String businessErrorCode, int errorCode, String errorMessage) {
        this.businessErrorCode = businessErrorCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getBusinessErrorCode() {
        return businessErrorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
