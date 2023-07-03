package com.cybersolution.fazeal.logistics.constants;

public final class AppConstants {

	private AppConstants() {

	}

	public static final String USER_AGENT = "User-Agent";
	public static final CharSequence POSTMAN_RUNTIME = "PostmanRuntime";
	public static final String ROLE_TYPE_NOT_FROM_LIST = "ROLE_TYPE_NOT_FROM_LIST";
	public static final String EMAIL_INVALID = "EMAIL_INVALID";
	public static final String EMAIL_ALREADY_EXIST = "EMAIL_ALREADY_EXIST";
	public static final String MOBILE_NUMBER_ALREADY_EXIST = "MOBILE_NUMBER_ALREADY_EXIST";
	public static final String USER_NAME_ALREADY_EXIST = "USER_NAME_ALREADY_EXIST";
	
	public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
	public static final String SIGN_UP_NOT_DONE= "SIGN_UP_NOT_DONE";
	public static final String PIPELINE = "|";

	public static final String ADDRESSLINEONE_BLANK ="ADDRESSLINEONE_BLANK";
	public static final String POSTAL_CODE_BLANK ="POSTAL_CODE_BLANK";
	public static final String COUNTRYID_BLANK = "COUNTRYID_BLANK";
	public static final String STATEID_BLANK = "STATEID_BLANK";
	public static final String CITY_BLANK = "CITY_BLANK";
	public static final String ID = "id";
	public static final String SUCCESS_MESSAGE_USER_INFO ="SUCCESS_MESSAGE_USER_INFO";

	public static final String FIRST_NAME_BLANK = "FIRST_NAME_BLANK";
	public static final String LAST_NAME_BLANK = "LAST_NAME_BLANK";
	public static final String EMAIL_NOT_BLANK = "EMAIL_NOT_BLANK";
	public static final String GENDER_TYPE_BLANK = "GENDER_TYPE_BLANK";
	public static final String DATE_OF_BIRTH_BLANK = "DATE_OF_BIRTH_BLANK";
	public static final String EMPTY = "";
	public static final String TYPE_MISMATCH = "TYPE_MISMATCH";
	public static final String VALIDATION_FAILED = "VALIDATION_FAILED";
	public static final String INVALID_TYPE = "INVALID_TYPE";

	public static final String BIND_MISMATCH = "BIND_MISMATCH";

	public static final String INVALID_PARAMS = "INVALID_PARAMS";

	public static final String REFRESH_TOKEN_EXPIRED_MSG = "REFRESH_TOKEN_EXPIRED_MSG";
	public static final String BEARER = "Bearer";
	public static final String PASSWORD_NOT_EMPTY = "PASSWORD_NOT_EMPTY";
	public static final String PASSWORD_EMPTY = "PASSWORD_EMPTY";
	public static final String USER_NAME_EMPTY = "USER_NAME_EMPTY";
	public static final String PASSWORD_INVALID = "PASSWORD_INVALID";
	public static final String REFRESH_TOKEN_NOT_EMPTY = "REFRESH_TOKEN_NOT_EMPTY";
	

	public static final String COUNTRY_NOT_FOUND= "COUNTRY_NOT_FOUND";
	public static final String COUNTRY_NAME_BLANK= "COUNTRY_NAME_BLANK";
	public static final String COUNTRY_CODE_BLANK= "COUNTRY_CODE_BLANK";
	public static final String COUNTRY_DELETED= "COUNTRY_DELETED";
	public static final String COUNTRY_SAVED= "COUNTRY_SAVED";
	public static final String COUNTRY_CONTROLLER= "Country Controller";
	public static final String STATE_NOT_FOUND= "STATE_NOT_FOUND";
	public static final String STATE_DELETED= "STATE_DELETED";
	public static final String STATE_SAVED= "STATE_SAVED";
	public static final String STATE_CONTROLLER= "State Controller";

	/**
	 * ROLE ACCESS
	 */
	public static final String HAS_ADMIN_ROLE_OR_USER_ROLE = "(hasRole('ROLE_USER') || hasRole('ROLE_ADMIN'))";
	public static final String HAS_ADMIN_ROLE_OR_ROLE_SALESPERSON = "(hasRole('ROLE_SALESPERSON') || hasRole('ROLE_ADMIN'))";
	
	/**
	 * Sign Up
	 */
	public static final String NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_FIRSTNAME="NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_FIRSTNAME";
	public static final String NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_LASTNAME="NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_LASTNAME";
	public static final String NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_MIDDLENAME="NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_MIDDLENAME";
	public static final String  MOBILE_NO_MUST_BE_10_15_DIGITS = "MOBILE_NO_MUST_BE_10_15_DIGITS";
	public static final String  INVALID_GENDER_TYPE = "INVALID_GENDER_TYPE";

	public static final String INVALID_IMG_TYPE = "INVALID_IMG_TYPE";
	public static final String USER_NAME_NOT_EMPTY = "USER_NAME_NOT_EMPTY";
	public static final String USER_NAME_INVALID = "USER_NAME_INVALID";
	public static final String MOBILE_NUMBER_BLANK ="MOBILE_NUMBER_BLANK";


	/**
	 * Business Address
	 */

	public static final String ADDRESS_DESCRIPTION_NOT_NULL="ADDRESS_DESCRIPTION_NOT_NULL";
	public static final String ZIPCODE_NOT_NULL="ZIPCODE_NOT_NULL";
	public static final String SIMPLE_DATE_FORMAT = "yyy-MM-dd'T'HH:mm:ss";
	public static final String LOCAL_DATE_FORMAT = "yyyy-MM-dd";
	public static final String EXPECTED_AST_TYPE_ERROR = "EXPECTED_AST_TYPE_ERROR";
	public static final String AUTHERIZATION = "Authorization";
	public static final String ACCESS_DENIED_EXCEPTION = "ACCESS_DENIED_EXCEPTION";
	public static final String ERR_CONSTRAINT_VIOLATION = "ERR_CONSTRAINT_VIOLATION";
	public static final String ERROR_INVALID_SYNTAX = "ERROR_INVALID_SYNTAX";
	public static final String JSON_NOT_CORRECT = "JSON_NOT_CORRECT";
	public static final String AMAZON_CLIENT = "AMAZON_CLIENT";
	public static final String AMAZON_SERVICE_ERROR = "AMAZON_SERVICE_ERROR";
	public static final String API_NOT_FOUND = "API_NOT_FOUND";
	public static final String LOGGED_OUT = "logged out";
	
	public static final String IMAGE_CANT_BE_BLANK= "IMAGE_CANT_BE_BLANK";
	public static final String ADDRESS_DESCRIPTION_LESS_THAN_3 = "ADDRESS_DESCRIPTION_LESS_THAN_3";
	public static final String ADDRESS_DESCRIPTION_MORE_THAN_250 = "ADDRESS_DESCRIPTION_MORE_THAN_250";
	public static final String ADDRESS_LINE_LESS_THAN_3 = "ADDRESS_LINE_LESS_THAN_3";
	public static final String ADDRESS_LINE_MORE_THAN_15 = "ADDRESS_LINE_MORE_THAN_15";
	public static final String INVALID_ZIP_CODE = "INVALID_ZIP_CODE";
	public static final String ZIPCODE_LESS_THAN_5 = "ZIPCODE_LESS_THAN_5";
	public static final String ZIPCODE_MORE_THAN_10 = "ZIPCODE_MORE_THAN_10";
	public static final String DRIVER_ID_IMAGE_REQUIRED = "DRIVER_ID_IMAGE_REQUIRED";
	public static final String DRIVER_LICENSE_IMAGE_REQUIRED = "DRIVER_LICENSE_IMAGE_REQUIRED";
	public static final String DRIVER_PERSONAL_IMAGE_REQUIRED = "DRIVER_PERSONAL_IMAGE_REQUIRED";

	public static final String USER_ID_REQUIRED = "USER_ID_REQUIRED";
	public static final String VEHICLE_TYPE_REQUIRED = "VEHICLE_TYPE_REQUIRED";
	public static final String LICENSE_PLATE_NUMBER_REQUIRED = "LICENSE_PLATE_NUMBER_REQUIRED";
	public static final String VEHICLE_BRAND_REQUIRED = "VEHICLE_BRAND_REQUIRED";
	public static final String VEHICLE_MODEL_NAME_REQUIRED = "VEHICLE_MODEL_NAME_REQUIRED";
	public static final String VEHICLE_CAPACITY_REQUIRED = "VEHICLE_CAPACITY_REQUIRED";
	public static final String VEHICLE_FUEL_TYPE_REQUIRED = "VEHICLE_FUEL_TYPE_REQUIRED";
	public static final String VEHICLE_MILE_AGE_REQUIRED = "VEHICLE_MILE_AGE_REQUIRED";
	public static final String VEHICLE_YEAR_REQUIRED = "VEHICLE_YEAR_REQUIRED";
	public static final String VEHICLE_STATUS_REQUIRED = "VEHICLE_STATUS_REQUIRED";
	public static final String DRIVING_METHOD_REQUIRED = "DRIVING_METHOD_REQUIRED";
	public static final String VEHICLE_COLOR_REQUIRED = "VEHICLE_COLOR_REQUIRED";
	public static final String DRIVING_TYPE_INVALID = "DRIVING_TYPE_INVALID";
	public static final String VEHICLE_ID = "Vehicle ID";
	public static final String SUCCESS_MESSAGE_VEHICLE_DETAILS= "SUCCESS_MESSAGE_VEHICLE_DETAILS";
	public static final String VEHICLE_IMAGES_EMPTY= "VEHICLE_IMAGES_EMPTY";
	public static final String VEHICLE_ID_REQUIRED= "VEHICLE_ID_REQUIRED";
	public static final String VEHICLE_NOT_FOUND= "VEHICLE_NOT_FOUND";
	public static final String SUCCESS_MESSAGE_VEHICLE_IMAGES= "SUCCESS_MESSAGE_VEHICLE_IMAGES";
	public static final String ADDRESS_DESCRIPTION_REQUIRED= "ADDRESS_DESCRIPTION_REQUIRED";
	public static final String LOCATION_NAME_REQUIRED= "LOCATION_NAME_REQUIRED";
	public static final String STREET_NUMBER_REQUIRED= "STREET_NUMBER_REQUIRED";
	public static final String ADDRESS_ID= "Address ID";
	public static final String SUCCESS_MESSAGE_ADDRESS_DETAILS= "SUCCESS_MESSAGE_ADDRESS_DETAILS";
	public static final String VEHICLES_EMPTY= "VEHICLES_EMPTY";
	public static final String DRIVER_ALREADY_REGISTERED= "DRIVER_ALREADY_REGISTERED";
	public static final String ADDRESS_EMPTY= "ADDRESS_EMPTY";
	public static final String SUCCESS_MESSAGE_AGREEMENTS_DONE = "SUCCESS_MESSAGE_AGREEMENTS_DONE";
	public static final String AVAILABLE_REQUIRED = "AVAILABLE_REQUIRED";
	public static final String DRIVER_AVAILABILITY_UPDATED = "DRIVER_AVAILABILITY_UPDATED";
	public static final String USERNAME_REQUIRED = "USERNAME_REQUIRED";
	public static final String NOT_AUTHORIZED_TO_LOGIN = "NOT_AUTHORIZED_TO_LOGIN";
	public static final String DRIVING_METHOD_UPDATED = "DRIVING_METHOD_UPDATED";
	public static final String REVIEW_RATING_VALIDATION = "REVIEW_RATING_VALIDATION";
	public static final String REVIEW_FEEDBACK_NOT_BLANK = "REVIEW_FEEDBACK_NOT_BLANK";
	public static final String REVIEW_ADDED_SUCCESSFULLY = "REVIEW_ADDED_SUCCESSFULLY";
	public static final String REVIEW_CONTROLLER="Logistics Review Controller";
	public static final String REVIEW_NOT_FOUND = "REVIEW_NOT_FOUND";
	public static final String REVIEW_UPDATED_SUCCESSFULLY = "REVIEW_UPDATED_SUCCESSFULLY";
	public static final String SORT_DIR_DESC = "desc";
	public static final String DEFAULT_SORT_FIELD_REVIEW="rating";
	public static final String CONTACT_NO_MUST_BE_09_15_DIGITS = "CONTACT_NO_MUST_BE_09_15_DIGITS";
	public static final String CONTACT_NO_MUST_BE_DIGITS_ONLY = "CONTACT_NO_MUST_BE_DIGITS_ONLY";
	public static final String CONTACT_NUMBER_UPDATED_SUCCESSFULLY= "CONTACT_NUMBER_UPDATED_SUCCESSFULLY";

	
}