package project.core.domain.enums;

public enum SystemRole {
    ADMIN,
	MANAGER,
	CUSTOMER;
	public static SystemRole fromStringToEnum(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input không được null");
        }
        for (SystemRole role : SystemRole.values()) {
            if (role.name().equalsIgnoreCase(input)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy enum phù hợp với: " + input);
    }
}
