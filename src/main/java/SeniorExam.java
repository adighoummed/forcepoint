import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SeniorExam {
    public static final String CSV_FILE_NAME = "url_categories_2020_06_21.csv";

    static int countLines() throws IOException {
        int totalLines = 0;

        Path path = Paths.get(CSV_FILE_NAME);

        if (Files.exists(path)) {
            try (Stream<String> lines = Files.lines(path)) {
                totalLines = (int) lines.count();
            }
        }
        return totalLines;
    }

    public static HashMap<String, Set<String>> countCategoriesPerHostname() throws IOException {
        HashMap<String, Set<String>> categoriesPerHostname = new HashMap<>();
        Path path = Paths.get(CSV_FILE_NAME);

        if (Files.exists(path)) {
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(line -> {
                    String[] tokens = line.split(",");

                    if (categoriesPerHostname.get(tokens[1]) == null){
                        categoriesPerHostname.put(tokens[1], new HashSet<>(Arrays.asList(tokens[0])));
                    }
                    else{
                        categoriesPerHostname.get(tokens[1]).add(tokens[0]);
                    }
                });
            }
        }
        return categoriesPerHostname;
    }

    public static HashMap<String, Set<String>> countHostnamesPerCategory() throws IOException {
        HashMap<String, Set<String>> hostnamesPerCategory = new HashMap<>();
        Path path = Paths.get(CSV_FILE_NAME);

        if (Files.exists(path)) {
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(line -> {
                    String[] tokens = line.split(",");

                    if (hostnamesPerCategory.get(tokens[0]) == null){
                        hostnamesPerCategory.put(tokens[0], new HashSet<>(Arrays.asList(tokens[1])));
                    }
                    else{
                        hostnamesPerCategory.get(tokens[0]).add(tokens[1]);
                    }

                });
            }
        }
        return hostnamesPerCategory;
    }

    public static int countHostnames(String category) throws IOException {
        HashMap<String, Set<String>> hostnames = countHostnamesPerCategory();
        if (category == null || hostnames.isEmpty() || !hostnames.containsKey(category)){
            return -1;
        }
        else{
            return hostnames.get(category).size();
        }
    }



    public static int countCategories(String hostname) throws IOException {
        HashMap<String, Set<String>> categories = countCategoriesPerHostname();
        if (hostname == null || categories.isEmpty() || !categories.containsKey(hostname)){
            return -1;
        }
        else{
            return categories.get(hostname).size();
        }
    }



}
