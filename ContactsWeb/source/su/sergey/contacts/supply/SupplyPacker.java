package su.sergey.contacts.supply;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;
import su.sergey.contacts.supply.valueobjects.impl.DefaultSupplyAttributes;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public final class SupplyPacker implements SupplyParameters {
	private HttpServletRequest request;
	
	public SupplyPacker(HttpServletRequest request) {
		this.request = request;
	}

	private String getAddress() {
		return ParameterUtil.getString(request, PN_ADDRESS);
	}
	
	private String getEmail() {
		return ParameterUtil.getString(request, PN_EMAIL);
	}
	
	private Integer getId() {
		Integer result = ParameterUtil.getInteger(request, PN_SUPPLY_ID);
		return result;
	}
	
	private boolean getImportant() {
		return ParameterUtil.getBoolean(request, PN_IMPORTANT);
	}
	
	private boolean getImportantOnly() {
		return ParameterUtil.getBoolean(request, PN_IMPORTANT_ONLY);
	}
	
	private String getInn() {
		return ParameterUtil.getString(request, PN_INN);
	}
	
	private String getKpp() {
		return ParameterUtil.getString(request, PN_KPP);
	}
	
	private String getOgrn() {
		return ParameterUtil.getString(request, PN_OGRN);
	}
	
	private Integer getKind() {
		Integer result = ParameterUtil.getInteger(request, PN_KIND);
		return result;
	}
	
	private String getMetro() {
		return ParameterUtil.getString(request, PN_METRO);
	}
	
	private String getName() {
		return ParameterUtil.getString(request, PN_NAME);
	}
	
    private String getNote() {
    	return ParameterUtil.getString(request, PN_NOTE);
    }
    
	private String getParentName() {
		return ParameterUtil.getString(request, PN_PARENT_NAME);
	}
	
	private String getPhone() {
		return ParameterUtil.getString(request, PN_PHONE);
	}
	
	private String getPropertyForm() {
		return ParameterUtil.getString(request, PN_PROPERTY_FORM);
	}
	
	private String getShortName() {
		return ParameterUtil.getString(request, PN_SHORT_NAME);
	}
	
	private String getUrl() {
		return ParameterUtil.getString(request, PN_URL);
	}
	
	public SupplyHandle getHandle() {
		Integer id = getId();
		SupplyHandle result = null;
		if (id != null) {
			result = new SupplyHandle(id);
		}
		return result;
	}
	
    public SupplySearchParameters getSearchParameters() throws InvalidParameterException {
    	SupplySearchParameters result = new SupplySearchParameters();
    	result.setAddress(getAddress());
    	result.setEmail(getEmail());
    	result.setFullData(false);
    	result.setImportantOnly(getImportantOnly());
    	result.setInn(getInn());
    	result.setKpp(getKpp());
    	result.setOgrn(getOgrn());
    	result.setKind(getKind());
    	result.setMetro(getMetro());
    	result.setName(getName());
    	result.setNote(getNote());
    	result.setParentName(getParentName());
    	result.setPropertyForm(getPropertyForm());
        result.setShortName(getShortName());
    	result.setPhone(getPhone());
    	result.setUrl(getUrl());
		return result;
    }
    
    public SupplyAttributes  getAttributes() throws InvalidParameterException {
    	DefaultSupplyAttributes result = new DefaultSupplyAttributes();
    	result.setAddress(getAddress());
    	result.setImportant(getImportant());
    	result.setInn(getInn());
    	result.setKpp(getKpp());
    	result.setOgrn(getOgrn());
    	result.setKind(getKind());
    	result.setMetro(getMetro());
    	result.setName(getName());
    	result.setNote(getNote());
    	result.setParentName(getParentName());
    	result.setPropertyForm(getPropertyForm());
        result.setShortName(getShortName());
    	result.setUrl(getUrl());
    	return result;
    }
}
