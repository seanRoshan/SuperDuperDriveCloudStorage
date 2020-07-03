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
    USER_NAME_EXISTS("SDDCSSU006", 6, "USER NAME ALREADY EXISTS, PLEASE CHOOSE A DIFFERENT USER NAME!"),
    REGISTRATION_FAILED("SDDCSSU007", 7, "REGISTRATION FAILED!, PLEASE TRY AGAIN LATER"),
    ADD_NOTE_FAILED("SDDCSSU008", 8, "FAILED TO ADD THE NEW NOTE!"),
    UPDATE_NOTE_FAILED("SDDCSSU009", 9, "FAILED TO UPDATE THE NEW NOTE!"),
    DELETE_NOTE_FAILED("SDDCSSU010", 10, "FAILED TO DELETE THE NOTE!"),
    DELETE_FILE_FAILED("SDDCSSU011", 11, "FAILED TO DELETE THE FILE!"),
    UPLOAD_FILE_FAILED("SDDCSSU012", 12, "FAILED TO UPLOAD THE FILE!"),
    DOWNLOAD_FILE_FAILED("SDDCSSU013", 13, "FAILED TO DOWNLOAD THE FILE!"),
    ADD_CREDENTIAL_FAILED("SDDCSSU014", 14, "FAILED TO ADD THE NEW CREDENTIAL!"),
    UPDATE_CREDENTIAL_FAILED("SDDCSSU015", 15, "FAILED TO UPDATE THE NEW CREDENTIAL!"),
    DELETE_CREDENTIAL_FAILED("SDDCSSU016", 16, "FAILED TO DELETE THE CREDENTIAL!"),
    FILED_ALREADY_UPLOADED("SDDCSSU017", 17, "YOU HAVE ALREADY UPLOADED THIS FILE!"),
    NO_FILE("SDDCSSU018", 18, "NO FILE IS SELECTED TO UPLOAD!");


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
