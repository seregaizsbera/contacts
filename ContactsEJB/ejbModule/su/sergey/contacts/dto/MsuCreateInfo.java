package su.sergey.contacts.dto;

import java.util.Date;

public interface MsuCreateInfo {
    Integer getPerson();
    Date getGraduate();
    Integer getDepartment();
    Boolean getHospice();
    Boolean getTutor();
    String getSubfaculty();
    String getNote();
}
