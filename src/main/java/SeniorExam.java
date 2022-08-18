import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.UUID;

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
            Set<String> categoryHostnames = hostnames.get(category);
            categoryHostnames.forEach(hostname -> System.out.println("Hostname: " + hostname));
            return categoryHostnames.size();
        }
    }



    public static int countCategories(String hostname) throws IOException {
        HashMap<String, Set<String>> categories = countCategoriesPerHostname();
        if (hostname == null || categories.isEmpty() || !categories.containsKey(hostname)){
            return -1;
        }
        else{
            Set<String> hostnameCategories = categories.get(hostname);
            hostnameCategories.forEach(category -> System.out.println("Category: " + category));
            return hostnameCategories.size();
        }
    }

    public static URLCategories buildURLCategories() throws IOException {
        HashMap<String, Set<String>> hostnamesPerCategory = countHostnamesPerCategory();

        List<URLCategory> categories = new ArrayList<>();
        for (Map.Entry<String,Set<String>> entry : hostnamesPerCategory.entrySet()){
            List<URLPattern> urlPatterns = new ArrayList<>();
            for (String pattern : entry.getValue()){
                URLPattern urlPattern = new URLPattern();
                urlPattern.setMatchType("EXACT");
                urlPattern.setHost(pattern);

                urlPatterns.add(urlPattern);
            }

            URLCategory category = new URLCategory();
            category.setUrlCategoryId(UUID.randomUUID().toString());
            category.setName(entry.getKey());
            category.setUrlPatterns(urlPatterns);

            categories.add(category);
        }

        URLCategories urlCategories = new URLCategories();
        urlCategories.setUrlCategories(categories);

        return urlCategories;
    }

    public static void urlCategoriesToJson() throws IOException{
        URLCategories urlCategories = buildURLCategories();

        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("url_categories.json")){
            gson.toJson(urlCategories,writer);
        }
    }

    public static void main(String[] args) throws IOException {
        urlCategoriesToJson();
    }
}
