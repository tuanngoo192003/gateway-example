package project.core.application.enums;

public enum MailFormat {
    VTI_MAIL("vti.com.vn"),
    FPT_EDU_MAIL("fpt.edu.vn"),
    FPT_MAIL("fpt.com"),
    STARDARD_MAIL("gmail.com");

    private String value;

    MailFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
