package files.model;

public enum Race {
    DRAGONBORN("Dragonborn"),
    DWARF("Dwarf"),
    ELF("Elf"),
    GNOME("Gnome"),
    HALFELF("Halfelf"),
    HALFORC("Halforc"),
    HALFLING("Halfling"),
    HUMAN("Human"),
    TIEFLING("Tiefling");

    private final String validRace;

    Race(String chosenRace) {
        this.validRace = chosenRace;
    }

    public String getValidRace() {
        return validRace;
    }
}