package su.sergey.contacts.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Coworker;
import su.sergey.contacts.person.valueobjects.Friend;
import su.sergey.contacts.person.valueobjects.Icq;
import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.person.valueobjects.Related;
import su.sergey.contacts.person.valueobjects.Shnip;
import su.sergey.contacts.person.valueobjects.impl.DefaultCoworker;
import su.sergey.contacts.person.valueobjects.impl.DefaultFriend;
import su.sergey.contacts.person.valueobjects.impl.DefaultIcq;
import su.sergey.contacts.person.valueobjects.impl.DefaultMsu;
import su.sergey.contacts.person.valueobjects.impl.DefaultPersonAttributes;
import su.sergey.contacts.person.valueobjects.impl.DefaultRelated;
import su.sergey.contacts.person.valueobjects.impl.DefaultShnip;
import su.sergey.contacts.util.ContactsDateTimeFormat;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.util.validation.FieldException;
import su.sergey.contacts.util.validation.FieldsValidator;
import su.sergey.contacts.util.validation.impl.AbstractFieldsValidator;

public final class PersonPacker implements PersonParameters {
	private static final String DATE_FORMAT = ContactsDateTimeFormat.DEFAULT_DATE_FORMAT;
	private static final String YEAR_FORMAT = ContactsDateTimeFormat.DEFAULT_YEAR_FORMAT;
	private HttpServletRequest request;
	private FieldsValidator validator;
	
	public PersonPacker(HttpServletRequest request) {
		this.request = request;
		validator = new AbstractFieldsValidator();
	}

	private String getAddress() {
		return ParameterUtil.getString(request, PN_ADDRESS);
	}
	
	private String getEmail() {
		return ParameterUtil.getString(request, PN_EMAIL);
	}
	
	private String getIcqSearch() {
		return ParameterUtil.getString(request, PN_ICQ_SEARCH);
	}
	
	private String getFirstName() {
		return ParameterUtil.getString(request, PN_FIRST_NAME);
	}
	
	private String getLastName() {
		return ParameterUtil.getString(request, PN_LAST_NAME);
	}
	
	private String getMiddleName() {
		return ParameterUtil.getString(request, PN_MIDDLE_NAME);
	}
	
	private String getPhone() {
		return ParameterUtil.getString(request, PN_PHONE);
	}
	
	private Date getBirthday() throws InvalidParameterException {
        Date result;
        String value;
        try {
            value = validator.validateString(request.getParameter(PN_BIRTHDAY), true);
            result = (value.length() > 0)
                     ? validator.validateDate(value, DATE_FORMAT)
                     : null;
        } catch (FieldException e) {
            throw new InvalidParameterException("Введен неправильный параметр", "День рождения после...");
        }
        return result;
	}
	
	private int getMonthOfBirthday() throws InvalidParameterException {
        int result;
        String value;
        try {
        	value = ParameterUtil.getString(request, PN_MONTH_OF_BIRTHDAY);
        	if (value == null) {
        		return 0;
        	}
            result = validator.validateInt(value);
        } catch (FieldException e) {
            throw new InvalidParameterException("Неправильно указан параметр", "Месяц рождения");
        }
		return result;
	}
	
	private Date getAfterBirthday() throws InvalidParameterException {
        Date result;
        String value;
        try {
            value = validator.validateString(request.getParameter(PN_AFTER_BIRTHDAY), true);
            result = (value.length() > 0)
                     ? validator.validateDate(value, DATE_FORMAT)
                     : null;
        } catch (FieldException e) {
            throw new InvalidParameterException("Введен неправильный параметр", "День рождения после...");
        }
        return result;
	}
	
	private Date getBeforeBirthday() throws InvalidParameterException {
        Date result;
        String value;
        try {
            value = validator.validateString(request.getParameter(PN_BEFORE_BIRTHDAY), true);
            result = (value.length() > 0)
                     ? validator.validateDate(value, DATE_FORMAT)
                     : null;
        } catch (FieldException e) {
            throw new InvalidParameterException("Введен неправильный параметр", "День рождения после...");
        }
        return result;
	}
	
	private Integer getId() {
		Integer result = ParameterUtil.getInteger(request, PN_PERSON_ID);
		return result;
	}
	
	private Integer getGender() {
	    return ParameterUtil.getInteger(request, PN_GENDER);
	}
	
	public PersonHandle getHandle() {
		Integer id = getId();
		PersonHandle result = null;
		if (id != null) {
			result = new PersonHandle(id);
		}
		return result;
	}
	
	private Integer getSearchGroupMode() {
		return ParameterUtil.getInteger(request, PN_SEARCH_GROUP_MODE);
	}

    public PersonSearchParameters getSearchParameters() throws InvalidParameterException {
		String address = getAddress();
		String phone = getPhone();
		String icq = getIcqSearch();
		String email = getEmail();
		String middleName = getMiddleName();
		String firstName = getFirstName();
		String lastName = getLastName();
		Date afterBirthday = getAfterBirthday();
		Date beforeBirthday = getBeforeBirthday();
		int monthOfBirthday = getMonthOfBirthday();
		Integer gender = getGender();
		Integer groupMode = getSearchGroupMode();
		PersonSearchParameters searchParameters =
		                      new PersonSearchParameters(firstName,
		                                                 middleName,
		                                                 lastName,
		                                                 phone,
		                                                 afterBirthday,
		                                                 beforeBirthday,
		                                                 monthOfBirthday,
		                                                 email,
		                                                 icq,
		                                                 address,
		                                                 gender,
		                                                 groupMode,
		                                                 false);
		return searchParameters;
    }
    
    private Collection getGroups() {
    	Collection result = new ArrayList();
    	String values[] = request.getParameterValues(PN_GROUP);
    	if (values != null) {
    		for (int i = 0; i < values.length; i++) {
    		    result.add(values[i]);
    		}
    	}
    	return result;
    }
    
    private String getCoworkerDescription() {
    	return ParameterUtil.getString(request, PN_COWORKER_DESCRIPTION);
    }
    
    private String getFriendDescription() {
    	return ParameterUtil.getString(request, PN_FRIEND_DESCRIPTION);
    }
    
    private String getCoworkerJob() {
    	return ParameterUtil.getString(request, PN_COWORKER_JOB);
    }
    
    private Coworker getCoworkerInfo() {
    	DefaultCoworker result = null;
    	if (getGroups().contains(GROUP_COWORKER)) {
    		result = new DefaultCoworker();
        	result.setDescription(getCoworkerDescription());
        	result.setJob(getCoworkerJob());
        	result.setAdministration(getCoworkerAdministration());
        	result.setDepartment(getCoworkerDepartment());
        	result.setPost(getCoworkerPost());
    	}
    	return result;
    }
    
    private String getCoworkerPost() {
    	return ParameterUtil.getString(request, PN_COWORKER_POST);
    }
    
    private String getCoworkerAdministration() {
    	return ParameterUtil.getString(request, PN_COWORKER_ADMINISTRATION);
    }
    
    private String getCoworkerDepartment() {
    	return ParameterUtil.getString(request, PN_COWORKER_DEPARTMENT);
    }
    
    private Friend getFriendInfo() {
    	DefaultFriend result = null;
    	if (getGroups().contains(GROUP_FRIEND)) {
    		result = new DefaultFriend();
        	result.setDescription(getFriendDescription());
    	}
    	return result;
    }
    
    private Integer getMsuDepartmentId() {
    	return ParameterUtil.getInteger(request, PN_MSU_DEPARTMENT_ID);
    }
    
    private Date getMsuGraduateDate() throws InvalidParameterException {
        Date result;
        String value;
        try {
            value = validator.validateString(request.getParameter(PN_MSU_GRADUATE_DATE), true);
            result = (value.length() > 0)
                     ? validator.validateDate(value, YEAR_FORMAT)
                     : null;
        } catch (FieldException e) {
            throw new InvalidParameterException("Введен неправильный параметр", "Дата выпуска из МГУ");
        }
        return result;
    }
    
    private Date getShnipGraduateDate() throws InvalidParameterException {
        Date result;
        String value;
        try {
            value = validator.validateString(request.getParameter(PN_SHNIP_GRADUATE_DATE), true);
            result = (value.length() > 0)
                     ? validator.validateDate(value, YEAR_FORMAT)
                     : null;
        } catch (FieldException e) {
            throw new InvalidParameterException("Введен неправильный параметр", "Дата выпуска из ШНИП'а");
        }
        return result;
    }
    
    private boolean getMsuHospice() {
    	return ParameterUtil.getBoolean(request, PN_MSU_HOSPICE);
    }
    
    private String getMsuSubfaculty() {
    	return ParameterUtil.getString(request, PN_MSU_SUBFACULTY);
    }
    
    private Msu getMsuInfo() throws InvalidParameterException {
    	DefaultMsu result = null;
    	if (getGroups().contains(GROUP_MSU)) {
    		result = new DefaultMsu();
    	    result.setDepartmentId(getMsuDepartmentId());
    	    result.setGraduateDate(getMsuGraduateDate());
    	    result.setHospice(getMsuHospice());
    	    result.setSubfaculty(getMsuSubfaculty());
    	    result.setDescription(getMsuDescription());
    	}
    	return result;
    }
    
    private String getNote() {
    	return ParameterUtil.getString(request, PN_NOTE);
    }
    
    private Long getIcqUin() {
    	return ParameterUtil.getLong(request, PN_ICQ_UIN);
    }
    
    private String getIcqNickName() {
    	return ParameterUtil.getString(request, PN_ICQ_NICKNANE);
    }
    
    private Icq getIcq() {
    	DefaultIcq result = null;
    	Long uin = getIcqUin();
    	String nickName = getIcqNickName();
    	if (uin != null && nickName != null) {
        	result = new DefaultIcq();
        	result.setIcq(getIcqUin());
        	result.setNickname(getIcqNickName());
    	}
    	return result;
    }
    
    private String getShnipDescription() {
    	return ParameterUtil.getString(request, PN_SHNIP_DESCRIPTION);
    }
    
    private String getMsuDescription() {
    	return ParameterUtil.getString(request, PN_MSU_DESCRIPTION);
    }
    
    private String getShnipFormLetter() {
    	return ParameterUtil.getString(request, PN_SHNIP_FORMLETTER);
    }
    
    private PersonHandle getShnipFormLeader() {
    	PersonHandle result = null;
    	Integer formLeaderId = ParameterUtil.getInteger(request, PN_SHNIP_FORMLEADER);
    	if (formLeaderId != null) {
    		result = new PersonHandle(formLeaderId);
    	}
    	return result;
    }
    
    private Shnip getShnipInfo() throws InvalidParameterException {
    	DefaultShnip result = null;
    	if (getGroups().contains(GROUP_SHNIP)) {
    		result = new DefaultShnip();
        	result.setDescription(getShnipDescription());
        	result.setFormLeader(getShnipFormLeader());
    	    result.setFormLetter(getShnipFormLetter());
    	    result.setGraduateDate(getShnipGraduateDate());
    	}
    	return result;
    }
    
    private String getRelatedDescription() {
    	return ParameterUtil.getString(request, PN_RELATED_DESCRIPTION);
    }
    
    private String getRelatedRelationShip() {
    	return ParameterUtil.getString(request, PN_RELATED_RELATIONSHIP);
    }
    
    private Related getRelatedInfo() {
    	DefaultRelated result = null;
    	if (getGroups().contains(GROUP_RELATED)) {
    	    result = new DefaultRelated();
    		result.setDescription(getRelatedDescription());
    	    result.setRelationship(getRelatedRelationShip());
    	}
    	return result;
    }
    
    public PersonAttributes getAttributes() throws InvalidParameterException {
    	DefaultPersonAttributes attributes = new DefaultPersonAttributes();
    	attributes.setAddress(getAddress());
    	attributes.setBirthday(getBirthday());
    	attributes.setCoworkerInfo(getCoworkerInfo());
    	attributes.setFirstName(getFirstName());
    	attributes.setFriendInfo(getFriendInfo());
    	attributes.setIcq(getIcq());
    	attributes.setLastName(getLastName());
    	attributes.setMiddleName(getMiddleName());
    	attributes.setMsuInfo(getMsuInfo());
    	attributes.setNote(getNote());
    	attributes.setRelatedInfo(getRelatedInfo());
    	attributes.setShnipInfo(getShnipInfo());
    	attributes.setGender(getGender());
    	return attributes;
    }
}
