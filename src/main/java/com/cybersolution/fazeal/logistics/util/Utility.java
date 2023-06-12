package com.cybersolution.fazeal.logistics.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.constants.Status;
import com.cybersolution.fazeal.logistics.security.services.UserDetailsImpl;
import com.cybersolution.fazeal.common.exception.GenericException;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class Utility {

	@Value(value = "${auth.password.regex}")
	private String passwordRegex;
	@Value(value = "${auth.mobileno.regex}")
	private String mobileNoRegex;
	@Value(value = "${auth.email.regex}")
	private String emailRegex;
	@Value(value = "${auth.name.regex}")
	private String spacialCharacterRegex;
	@Value(value = "${auth.image.regex}")
	private String imgRegex;
	@Value(value = "${auth.username.regex}")
	private String userRegex;
	@Value(value = "${auth.number.regex}")
	private String numberRegex;

	@Value(value = "${auth.uploadfile.regex}")
	private String uploadfileRegex;
	@Value(value = "${auth.charnumber.regex}")
	private String characterWithNumber;
	@Value(value = "#{'${address.type}'.split(',')}")
	private List<String> addressTypes;

	static SecureRandom secureRandom = new SecureRandom();
	@Autowired
	private Messages messages;


	public static int get5DigitsNumberGenerator() {
		return secureRandom.nextInt(99999);
	}

	public boolean isEmailValid(String email) {
		if (StringUtils.hasText(email)) {
			return Pattern.compile(emailRegex).matcher(email).matches();
		}
		return false;
	}

	public boolean isMobileNoValid(String mobileNo) {
		if (StringUtils.hasText(mobileNo)) {
			return Pattern.compile(mobileNoRegex).matcher(mobileNo).matches();
		}
		return false;
	}

	public boolean isPasswordValidator(String password) {
		if (StringUtils.hasText(password)) {
			return Pattern.compile(passwordRegex).matcher(password).matches();
		}
		return false;
	}

	public boolean isSpacialCharacterValidator(String value) {
		return Pattern.compile(spacialCharacterRegex).matcher(value).find();
	}

	public boolean isImageValidator(String value) {
		return Pattern.compile(imgRegex).matcher(value).matches();
	}

	public boolean isUserValidator(String value) {
		return Pattern.compile(userRegex).matcher(value).matches();
	}

	public boolean isNumberValidator(String value) {
		return Pattern.compile(numberRegex).matcher(value).matches();
	}

	public boolean isDocumentValidator(String value) {
		return Pattern.compile(uploadfileRegex).matcher(value).matches();
	}

	public boolean ischaracterWithNumberValidator(String value) {
		return Pattern.compile(characterWithNumber).matcher(value).find();
	}

	public boolean addressTypeValidator(String value) {
		if (StringUtils.hasText(value)) {
			return addressTypes.contains(value);
		}
		return false;
	}
	public void addressLineValidator(String addressLine) {
		if (addressLine.length() < 3) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
					messages.get(AppConstants.ADDRESS_LINE_LESS_THAN_3));
		}
		if (addressLine.length() > 15) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
					messages.get(AppConstants.ADDRESS_LINE_MORE_THAN_15));
		}
	}

	public void addressDescriptionValidator(String description) {
		if (Objects.isNull(description)) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.ADDRESS_DESCRIPTION_NOT_NULL,
					messages.get(AppConstants.ADDRESS_DESCRIPTION_NOT_NULL));
		}
		if (description.length() < 3) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
					messages.get(AppConstants.ADDRESS_DESCRIPTION_LESS_THAN_3));
		}
		if (description.length() > 250) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
					messages.get(AppConstants.ADDRESS_DESCRIPTION_MORE_THAN_250));
		}
	}

	public void zipCodeValidator(String zipCode) {
		if (Objects.isNull(zipCode)) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.ZIPCODE_NOT_NULL,
					messages.get(AppConstants.ZIPCODE_NOT_NULL));
		}
		if (!isNumberValidator(zipCode)) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
					messages.get(AppConstants.INVALID_ZIP_CODE));
		}
		if (zipCode.length() < 5) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
					messages.get(AppConstants.ZIPCODE_LESS_THAN_5));
		}
		if (zipCode.length() > 10) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
					messages.get(AppConstants.ZIPCODE_MORE_THAN_10));
		}

	}

	/**
	 * @return device details
	 * @apiNote Used to get device details from HttpServletRequest for logging in
	 *          User Sessions
	 * @Param HttpServletRequest
	 */
	public static String getDeviceType(HttpServletRequest httpReq) {
		String device = null;
		if (!ObjectUtils.isEmpty(httpReq) && !ObjectUtils.isEmpty(httpReq.getHeader(AppConstants.USER_AGENT))) {

			UserAgent userAgent = UserAgent.parseUserAgentString(httpReq.getHeader(AppConstants.USER_AGENT));
			OperatingSystem agent = userAgent.getOperatingSystem();
			device = agent.getDeviceType().getName() + "-" + agent.getName();
		}
		return device;
	}

	/**
	 * @return browser details
	 * @apiNote Used to get browser details from HttpServletRequest
	 * @Param HttpServletRequest
	 */
	public static String getBrowserType(HttpServletRequest httpReq) {
		String browser = null;
		if (!ObjectUtils.isEmpty(httpReq) && !ObjectUtils.isEmpty(httpReq.getHeader(AppConstants.USER_AGENT))) {
			String[] deviceDetails = httpReq.getHeader(AppConstants.USER_AGENT).split(" ");
			if (!ObjectUtils.isEmpty(deviceDetails) && deviceDetails[0].contains(AppConstants.POSTMAN_RUNTIME)) {
				browser = deviceDetails[0];
			} else {
				UserAgent userAgent = UserAgent.parseUserAgentString(httpReq.getHeader(AppConstants.USER_AGENT));
				browser = userAgent.getBrowser().getName();
			}
		}
		return browser;
	}

	/**
	 * @return ip address
	 * @apiNote Used to get ip Address from HttpServletRequest
	 * @Param HttpServletRequest
	 */
	public static String getIpAddress(HttpServletRequest httpReq) {
		String ipAddress = null;
		if (!ObjectUtils.isEmpty(httpReq)) {

			ipAddress = httpReq.getRemoteAddr();
		}
		return ipAddress;
	}

	public static List<String> getStatus() {
		List<String> strings = new ArrayList<>();
		for (Status statusCode : Status.values()) {

			strings.add(statusCode.name());
		}
		return strings;
	}


	public static boolean isDuplicateWithInList(List<String> names) {

		for (int i = 0; i < names.size(); i++) {
			for (int j = i + 1; j < names.size(); j++) {
				if (names.get(i).equals(names.get(j))) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean compareTwoList(List<String> listOne, List<String> listTwo) {
		int count = 0;
		for (int i = 0; i < listOne.size(); i++) {
			for (int j = i; j <= i; j++) {
				if (listOne.get(i).equals(listTwo.get(j))) {
					count++;
				}

				if (count > 1)
					return true;
			}
		}

		return false;
	}

	public static String digitsGenerator(String size, Integer length) {

		String productNumber = "";

		for (int i = 0; i < length - size.length(); i++) {
			productNumber = productNumber + "0";
		}

		return productNumber;
	}

	public static UserDetailsImpl getUserFromSecurityContext() {
		return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@SneakyThrows
	public static CommonsMultipartFile getImageFileByIndex(DataFetchingEnvironment dataFetchingEnvironment, int index) {
		GraphQLContext graphQLContext = dataFetchingEnvironment.getContext();
		Map<String, List<Part>> map = graphQLContext.getFiles().orElse(null);
		if(Objects.isNull(map)){
			return null;
		}
		Part imageFile = null;
		if(Objects.nonNull(map.get("files")) && map.get("files").size() >= index+1) {
			imageFile = map.get("files").get(index);
			FileItem fileItem = new DiskFileItem(imageFile.getSubmittedFileName(), imageFile.getContentType(), false,imageFile.getSubmittedFileName(), (int) imageFile.getSize(), null);
			InputStream in = imageFile.getInputStream();
			OutputStream out = fileItem.getOutputStream();
			in.transferTo(out);
			return new CommonsMultipartFile(fileItem);
		}
		return null;
	}

	@SneakyThrows
	public static List<CommonsMultipartFile> getImagesFiles(DataFetchingEnvironment dataFetchingEnvironment) {
		GraphQLContext graphQLContext = dataFetchingEnvironment.getContext();
		Map<String, List<Part>> map = graphQLContext.getFiles().orElse(null);
		if(Objects.isNull(map.get("files"))) {
			return null;
		}
		List<Part> imageFiles = map.get("files");
		List<CommonsMultipartFile> files = new ArrayList<CommonsMultipartFile>();
		for(Part image: imageFiles) {
			FileItem fileItem = new DiskFileItem(image.getSubmittedFileName(), image.getContentType(), false,image.getSubmittedFileName(), (int) image.getSize(), null);
			InputStream in = image.getInputStream();
			OutputStream out = fileItem.getOutputStream();
			in.transferTo(out);
			files.add(new CommonsMultipartFile(fileItem));
		}
		return files;
	}

}
