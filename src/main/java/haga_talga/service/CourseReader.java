package haga_talga.service;

import haga_talga.model.CourseData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Hey! This Service handles reading subjects from JSON file
 * Reads from data/courses.json
 */
public class CourseReader {

    /** Data file path */
    private static final String DATA_FILE = "src/main/resources/courses.json";

    /** Date format */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** DateTime format */
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** Gson for JSON */
    private final Gson gson;

    /** List of subjects in memory */
    private final List<CourseData> courses;

    /**
     * Constructor creates Gson and reads subjects
     */
    public CourseReader() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        this.courses = new ArrayList<>();
        loadCourses();
    }

    /**
     * Gets all subjects
     */
    public List<CourseData> getCourses() {
        return new ArrayList<>(courses);
    }

    /**
     * Gets subject by index
     */
    public CourseData getCourse(int index) {
        if (index >= 0 && index < courses.size()) {
            return courses.get(index);
        }
        return null;
    }

    /**
     * Reads subjects from JSON file
     */
    private void loadCourses() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<CourseData>>() {
            }.getType();
            List<CourseData> loaded = gson.fromJson(reader, listType);
            if (loaded != null) {
                courses.clear();
                courses.addAll(loaded);
            }
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }

    /**
     * Adapter for LocalDate
     */
    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {
        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.format(DATE_FORMAT));
            }
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String dateStr = in.nextString();
            return LocalDate.parse(dateStr, DATE_FORMAT);
        }
    }

    /**
     * Adapter for LocalDateTime
     */
    private static class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
        @Override
        public void write(JsonWriter out, LocalDateTime value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.format(DATETIME_FORMAT));
            }
        }

        @Override
        public LocalDateTime read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String dateTimeStr = in.nextString();
            return LocalDateTime.parse(dateTimeStr, DATETIME_FORMAT);
        }
    }
}