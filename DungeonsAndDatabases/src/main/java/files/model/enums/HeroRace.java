package files.model.enums;

public enum HeroRace {
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

    HeroRace(String chosenRace) {
        this.validRace = chosenRace;
    }

    public String getValidRace() {
        return validRace;
    }
}