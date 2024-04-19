package me.helium.util.gui;

public enum ChatFormat {
    RED("§c"),
    GREEN("§a"),
    RESET("§r");

    private final String code;

    ChatFormat(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}
