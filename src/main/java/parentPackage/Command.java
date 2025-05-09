package parentPackage;

public enum Command {
    REPORT("R"),
    ATTACK("A"),
    DEFEND("D"),
    TRIGGER_SPECIAL_ABILITY("T");

    private final String shortcut;

    Command(String shortcut) {
        this.shortcut = shortcut;
    }

    public static Command getFromShortcut(String line) {
        for (Command command : Command.values()) {
            if (command.shortcut.equals(line)) {
                return command;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getShortcut() {
        return shortcut;
    }
}
