package studentdatabaseapplication;


public enum GradeLevel {
    MATRIC(1, "Martic"),
    INTERMEDIATE(2, "Intermediate"),
    B_TECH(3, "B.tech"),
    M_TECH(4, "M.tech");

    private final int code;
    private final String displayName;

    GradeLevel(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static GradeLevel fromCode(int code) {
        for (GradeLevel level : GradeLevel.values()) {
            if (level.getCode() == code) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid grade level code: " + code);
    }
}