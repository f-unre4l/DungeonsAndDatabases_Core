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

    private final String name;

    HeroRace(String chosenHeroRace) {
        this.name = chosenHeroRace;
    }

    public String getName() {
        return name;
    }
}