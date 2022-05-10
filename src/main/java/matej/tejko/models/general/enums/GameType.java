package matej.tejko.models.general.enums;

public enum GameType {

    YAMB(Values.YAMB);

    private GameType(String value) {
        if (!this.name().equals(value))
            throw new IllegalArgumentException("Incorrect use of GameType");
    }

    public static class Values {
        public static final String YAMB = "YAMB";
    }

}