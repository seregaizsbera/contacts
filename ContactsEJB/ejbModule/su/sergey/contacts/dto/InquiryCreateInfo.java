package su.sergey.contacts.dto;

public interface InquiryCreateInfo {
    String getAlias();
    String getQuery();
    Integer getScope();
    Integer getMode();
    String getRole();
    String getDescription();
}
