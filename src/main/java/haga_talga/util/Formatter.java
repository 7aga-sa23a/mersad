package haga_talga.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================
 * AsciiFormatterUtil - Standalone Utility
 * ============================================================
 * Enta bas me7tag tensko el file da w t7oto f project bta3tak.
 * Mfeesh ay dependency tanya me7tag tazwedha.
 *
 * Estamel:
 * AsciiFormatterUtil.header("HELLO", "cyan", "double", "center", 80);
 * AsciiFormatterUtil.box("Menu item 1\nMenu item 2", "magenta", "rounded",
 * "center", 80);
 * AsciiFormatterUtil.success("Course added!");
 * AsciiFormatterUtil.table(headers, rows, "cyan");
 * ============================================================
 */
public class Formatter {

    // =========================================================
    // ANSI Colors (Foreground)
    // =========================================================
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    // =========================================================
    // ANSI Colors (Background)
    // =========================================================
    private static final String BG_RED = "\u001B[41m";
    private static final String BG_GREEN = "\u001B[42m";
    private static final String BG_YELLOW = "\u001B[43m";
    private static final String BG_BLUE = "\u001B[44m";
    private static final String BG_MAGENTA = "\u001B[45m";
    private static final String BG_CYAN = "\u001B[46m";
    private static final String BG_WHITE = "\u001B[47m";
    private static final String BG_BLACK = "\u001B[40m";

    // =========================================================
    // ASCII Font Data (5-row pixel font)
    // =========================================================
    private static final Map<Character, String[]> FONT = new LinkedHashMap<>();

    static {
        FONT.put('A', new String[] { " █████ ", "██   ██", "███████", "██   ██", "██   ██" });
        FONT.put('B', new String[] { "██████ ", "██   ██", "██████ ", "██   ██", "██████ " });
        FONT.put('C', new String[] { " ██████", "██     ", "██     ", "██     ", " ██████" });
        FONT.put('D', new String[] { "██████ ", "██   ██", "██   ██", "██   ██", "██████ " });
        FONT.put('E', new String[] { "███████", "██     ", "█████  ", "██     ", "███████" });
        FONT.put('F', new String[] { "███████", "██     ", "█████  ", "██     ", "██     " });
        FONT.put('G', new String[] { " ██████", "██     ", "██  ███", "██   ██", " ██████" });
        FONT.put('H', new String[] { "██   ██", "██   ██", "███████", "██   ██", "██   ██" });
        FONT.put('I', new String[] { "███", " ██", " ██", " ██", "███" });
        FONT.put('J', new String[] { "     ██", "     ██", "     ██", "██   ██", " █████ " });
        FONT.put('K', new String[] { "██   ██", "██  ██ ", "█████  ", "██  ██ ", "██   ██" });
        FONT.put('L', new String[] { "██     ", "██     ", "██     ", "██     ", "███████" });
        FONT.put('M', new String[] { "███   ███", "████ ████", "██ ███ ██", "██     ██", "██     ██" });
        FONT.put('N', new String[] { "███   ██", "████  ██", "██ ██ ██", "██  ████", "██   ███" });
        FONT.put('O', new String[] { " █████ ", "██   ██", "██   ██", "██   ██", " █████ " });
        FONT.put('P', new String[] { "██████ ", "██   ██", "██████ ", "██     ", "██     " });
        FONT.put('Q', new String[] { " █████ ", "██   ██", "██   ██", "██ ▄ ██", " █████▄" });
        FONT.put('R', new String[] { "██████ ", "██   ██", "██████ ", "██   ██", "██   ██" });
        FONT.put('S', new String[] { " ██████", "██     ", " █████ ", "     ██", "██████ " });
        FONT.put('T', new String[] { "████████", "   ██   ", "   ██   ", "   ██   ", "   ██   " });
        FONT.put('U', new String[] { "██   ██", "██   ██", "██   ██", "██   ██", " █████ " });
        FONT.put('V', new String[] { "██    ██", "██    ██", "██    ██", " ██  ██ ", "  ████  " });
        FONT.put('W', new String[] { "██     ██", "██     ██", "██  █  ██", "██ ███ ██", " ███ ███ " });
        FONT.put('X', new String[] { "██   ██", " ██ ██ ", "  ███  ", " ██ ██ ", "██   ██" });
        FONT.put('Y', new String[] { "██    ██", " ██  ██ ", "  ████  ", "   ██   ", "   ██   " });
        FONT.put('Z', new String[] { "███████", "   ███ ", "  ███  ", " ███   ", "███████" });
        FONT.put('0', new String[] { " █████ ", "██  ███", "██ █ ██", "███  ██", " █████ " });
        FONT.put('1', new String[] { " ██", "███", " ██", " ██", "████" });
        FONT.put('2', new String[] { " █████ ", "██   ██", "   ███ ", " ███   ", "███████" });
        FONT.put('3', new String[] { "██████ ", "     ██", " █████ ", "     ██", "██████ " });
        FONT.put('4', new String[] { "██  ██ ", "██  ██ ", "███████", "    ██ ", "    ██ " });
        FONT.put('5', new String[] { "███████", "██     ", "██████ ", "     ██", "██████ " });
        FONT.put('6', new String[] { " █████ ", "██     ", "██████ ", "██   ██", " █████ " });
        FONT.put('7', new String[] { "███████", "    ██ ", "   ██  ", "  ██   ", " ██    " });
        FONT.put('8', new String[] { " █████ ", "██   ██", " █████ ", "██   ██", " █████ " });
        FONT.put('9', new String[] { " █████ ", "██   ██", " ██████", "     ██", " █████ " });
        FONT.put('!', new String[] { "██", "██", "██", "  ", "██" });
        FONT.put('?', new String[] { " █████ ", "██   ██", "   ███ ", "   ░░  ", "   ██  " });
        FONT.put('.', new String[] { "   ", "   ", "   ", "   ", "██ " });
        FONT.put(',', new String[] { "   ", "   ", "   ", " ██", "██ " });
        FONT.put('-', new String[] { "      ", "      ", "█████ ", "      ", "      " });
        FONT.put(' ', new String[] { "    ", "    ", "    ", "    ", "    " });
    }

    // =========================================================
    // Internal helpers (private)
    // =========================================================

    /** Besheel el ANSI codes 3ashan y7seb tool el satr el fe3ly */
    private static int visibleLength(String s) {
        return s.replaceAll("\u001B\\[[;\\d]*m", "").length();
    }

    /** Bercod el alwan mn el esm bta3to */
    private static String resolveColor(String name) {
        if (name == null)
            return CYAN;
        return switch (name.toLowerCase()) {
            case "red" -> RED;
            case "green" -> GREEN;
            case "yellow" -> YELLOW;
            case "blue" -> BLUE;
            case "magenta" -> MAGENTA;
            case "white" -> WHITE;
            default -> CYAN;
        };
    }

    /** Bercod el alwan el khalfeya */
    private static String resolveBgColor(String name) {
        if (name == null || name.isBlank())
            return "";
        return switch (name.toLowerCase()) {
            case "red" -> BG_RED;
            case "green" -> BG_GREEN;
            case "yellow" -> BG_YELLOW;
            case "blue" -> BG_BLUE;
            case "magenta" -> BG_MAGENTA;
            case "cyan" -> BG_CYAN;
            case "white" -> BG_WHITE;
            case "black" -> BG_BLACK;
            default -> "";
        };
    }

    /** Box style characters */
    private record BoxChars(String tl, String tr, String bl, String br, String h, String v) {
    }

    private static BoxChars resolveBoxStyle(String style) {
        if (style == null)
            style = "single";
        return switch (style.toLowerCase()) {
            case "double" -> new BoxChars("╔", "╗", "╚", "╝", "═", "║");
            case "rounded" -> new BoxChars("╭", "╮", "╰", "╯", "─", "│");
            default -> new BoxChars("┌", "┐", "└", "┘", "─", "│");
        };
    }

    /** Bersem el box 7awel el content */
    private static List<String> drawBox(String[] contentLines, BoxChars bc, int padding, String contentAlign) {
        int maxLen = 0;
        for (String l : contentLines)
            maxLen = Math.max(maxLen, visibleLength(l));
        int inner = maxLen + padding * 2;

        List<String> out = new ArrayList<>();
        String hLine = bc.h().repeat(inner + 2);
        out.add(bc.tl() + hLine + bc.tr());
        for (int p = 0; p < padding / 2; p++)
            out.add(bc.v() + " ".repeat(inner + 2) + bc.v());

        for (String line : contentLines) {
            int pad = inner - visibleLength(line);
            int left = 0;
            if ("center".equalsIgnoreCase(contentAlign))
                left = pad / 2;
            else if ("right".equalsIgnoreCase(contentAlign))
                left = pad;
            int right = pad - left;
            out.add(bc.v() + " ".repeat(left + 1) + line + " ".repeat(right + 1) + bc.v());
        }
        for (int p = 0; p < padding / 2; p++)
            out.add(bc.v() + " ".repeat(inner + 2) + bc.v());
        out.add(bc.bl() + hLine + bc.br());
        return out;
    }

    /** Bepartition el satr 3al yemeen aw shemal aw noss */
    private static String alignLine(String line, String align, int width) {
        if (align == null || align.equalsIgnoreCase("left"))
            return line;
        int len = visibleLength(line);
        if (len >= width)
            return line;
        if (align.equalsIgnoreCase("center"))
            return " ".repeat((width - len) / 2) + line;
        if (align.equalsIgnoreCase("right"))
            return " ".repeat(width - len) + line;
        return line;
    }

    /** Bersem el ASCII art men el text */
    private static String[] renderAsciiArt(String text) {
        String upper = text.toUpperCase();
        String[][] charRows = new String[upper.length()][];
        for (int i = 0; i < upper.length(); i++) {
            char c = upper.charAt(i);
            charRows[i] = FONT.getOrDefault(c, FONT.get(' '));
        }
        int rows = 5;
        StringBuilder[] lines = new StringBuilder[rows];
        for (int r = 0; r < rows; r++)
            lines[r] = new StringBuilder();
        for (int i = 0; i < charRows.length; i++) {
            String[] glyph = charRows[i];
            for (int r = 0; r < rows; r++) {
                lines[r].append(glyph[r]);
                if (i < charRows.length - 1)
                    lines[r].append(" ");
            }
        }
        String[] result = new String[rows];
        for (int r = 0; r < rows; r++)
            result[r] = lines[r].toString();
        return result;
    }

    private static void printRow(String[] row, int[] colWidths, String color) {
        StringBuilder sb = new StringBuilder("│");
        for (int i = 0; i < colWidths.length; i++) {
            String val = (row.length > i && row[i] != null) ? row[i] : "";
            int pad = colWidths[i] - val.length();
            sb.append(" ").append(val).append(" ".repeat(pad)).append(" │");
        }
        System.out.println(color + sb + RESET);
    }

    // =========================================================
    // PUBLIC API
    // =========================================================

    /**
     * Btetba3 text kbeer (ASCII Art) gowa box molawan.
     * 
     * @param text  el nas elly 3ayzo tersmaha
     * @param color "cyan" | "red" | "green" | "yellow" | "blue" | "magenta" |
     *              "white"
     * @param style "single" | "double" | "rounded"
     * @param align "left" | "center" | "right"
     * @param width 3ard el terminal (masalan 80 aw 120)
     */
    public static void header(String text, String color, String style, String align, int width) {
        String c = resolveColor(color);
        BoxChars bc = resolveBoxStyle(style);
        String[] artLines = renderAsciiArt(text);
        List<String> box = drawBox(artLines, bc, 2, "center");
        System.out.println();
        for (String line : box)
            System.out.println(alignLine(c + BOLD + line + RESET, align, width));
        System.out.println();
    }

    /** Zay el header bas mn 8er box - text kbeer ASCII art bass */
    public static void banner(String text, String color, String align, int width) {
        String c = resolveColor(color);
        String[] artLines = renderAsciiArt(text);
        System.out.println();
        for (String line : artLines)
            System.out.println(alignLine(c + BOLD + line + RESET, align, width));
        System.out.println();
    }

    /**
     * Bte3mel wrap l ay text (ay 3adad men el sotor) gowa box molawan.
     * Tafsel ben el sotor bel "\n"
     * Masalan: AsciiFormatterUtil.box("Line 1\nLine 2\nLine 3", "magenta",
     * "double", "center", 80);
     */
    public static void box(String text, String color, String style, String align, int width) {
        String c = resolveColor(color);
        BoxChars bc = resolveBoxStyle(style);
        String[] lines = text.replace("\\n", "\n").split("\n");
        List<String> box = drawBox(lines, bc, 2, "left");
        System.out.println();
        for (String line : box)
            System.out.println(alignLine(c + BOLD + line + RESET, align, width));
        System.out.println();
    }

    /**
     * Btetba3 5at fasel (Divider).
     * 
     * @param character el 7arf elly 3ayzo tetba3 beh (masalan "─", "~", "=", "*")
     * @param lineWidth tool el 5at
     * @param color     loon el 5at
     * @param align     "left" | "center" | "right" (bel nessba l 3ard el terminal)
     * @param termWidth 3ard el terminal
     */
    public static void divider(String character, int lineWidth, String color, String align, int termWidth) {
        String c = resolveColor(color);
        String line = character.repeat(lineWidth);
        System.out.println(alignLine(c + BOLD + line + RESET, align, termWidth));
    }

    /** Divider bsit mn 8er alignment */
    public static void divider(String character, int lineWidth, String color) {
        divider(character, lineWidth, color, "left", lineWidth);
    }

    /**
     * Btetba3 prompt bel color 3ashan ta5od input mn el user (el cursor byf4al f
     * nafs el satr).
     * Ba3daha hatesta5dem el Scanner bta3tak 3ashan ta5od el input.
     */
    public static void prompt(String text, String color, String align, int width) {
        String c = resolveColor(color);
        String coloredLine = c + BOLD + text + RESET;
        System.out.print(alignLine(coloredLine, align, width));
        System.out.flush();
    }

    /** Prompt bsit mn 8er alignment */
    public static void prompt(String text, String color) {
        System.out.print(resolveColor(color) + BOLD + text + RESET);
        System.out.flush();
    }

    /**
     * Btemse7 el console 5ales (Clear Screen).
     */
    public static void clearScreen() {
        final String os = System.getProperty("os.name");

        // Windows approach
        if (os.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        // Any other OS
        else {
            try {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    /** Resalet naga7 (Success) b 5alfeya 5adra */
    public static void success(String message) {
        System.out.println(BG_GREEN + WHITE + BOLD + " [✔] SUCCESS: " + message + " " + RESET);
    }

    /** Resalet 8alat (Error) b 5alfeya 7amra */
    public static void error(String message) {
        System.out.println(BG_RED + WHITE + BOLD + " [✖] ERROR: " + message + " " + RESET);
    }

    /** Resalet ta7zeer (Warning) b 5alfeya safra */
    public static void warning(String message) {
        System.out.println(BG_YELLOW + WHITE + BOLD + " [!] WARNING: " + message + " " + RESET);
    }

    /** Ma3loma (Info) b 5alfeya zar2a */
    public static void info(String message) {
        System.out.println(BG_BLUE + WHITE + BOLD + " [i] INFO: " + message + " " + RESET);
    }

    /**
     * Btetba3 el text 7arf 7arf zay el aala el katba (Typewriter Animation).
     * 
     * @param delayMs el wa2t bel milliseconds ben kol 7arf (masalan 50)
     */
    public static void typewriter(String text, int delayMs, String color) {
        String c = resolveColor(color);
        System.out.print(c + BOLD);
        for (char ch : text.toCharArray()) {
            System.out.print(ch);
            System.out.flush();
            sleep(delayMs);
        }
        System.out.println(RESET);
    }

    /**
     * Bersem shareet taqadom (Progress Bar). lazm tetnada gowa loop.
     * Masalan:
     * for (int i = 0; i <= 100; i += 10) {
     * AsciiFormatterUtil.progressBar(i, 100, "green");
     * AsciiFormatterUtil.sleep(100);
     * }
     */
    public static void progressBar(int current, int total, String color) {
        String c = resolveColor(color);
        int barLength = 50;
        int progress = (int) (((double) current / total) * barLength);
        int percent = (int) (((double) current / total) * 100);
        StringBuilder bar = new StringBuilder(c + "[");
        for (int i = 0; i < barLength; i++)
            bar.append(i < progress ? "█" : "░");
        bar.append("] ").append(percent).append("%").append(RESET);
        System.out.print("\r" + bar);
        if (current >= total)
            System.out.println();
    }

    /**
     * Bersem gadwal monazam w msatar (Table).
     * 
     * @param headers esmaа el columns
     * @param rows    el byanat (kol row hia array mn el String)
     * @param color   loon el gadwal
     */
    public static void table(String[] headers, String[][] rows, String color) {
        String c = resolveColor(color);
        int[] colWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            colWidths[i] = headers[i].length();
            for (String[] row : rows)
                if (row.length > i && row[i] != null)
                    colWidths[i] = Math.max(colWidths[i], row[i].length());
        }
        StringBuilder top = new StringBuilder("┌");
        StringBuilder mid = new StringBuilder("├");
        StringBuilder bot = new StringBuilder("└");
        for (int i = 0; i < colWidths.length; i++) {
            top.append("─".repeat(colWidths[i] + 2));
            mid.append("─".repeat(colWidths[i] + 2));
            bot.append("─".repeat(colWidths[i] + 2));
            if (i < colWidths.length - 1) {
                top.append("┬");
                mid.append("┼");
                bot.append("┴");
            } else {
                top.append("┐");
                mid.append("┤");
                bot.append("┘");
            }
        }
        System.out.println(c + top + RESET);
        printRow(headers, colWidths, c);
        System.out.println(c + mid + RESET);
        for (String[] row : rows)
            printRow(row, colWidths, c);
        System.out.println(c + bot + RESET);
    }

    /** Bysalla el execution shwaya */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
