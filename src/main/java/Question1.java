import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Question1 {
    private HashMap<String, Set<String>> categoriesPerHostname;
    private HashMap<String, Set<String>> hostnamesPerCategory;

    private int totalLines;

    public Question1(String csvFilePath) {
        categoriesPerHostname = new HashMap<>();
        hostnamesPerCategory = new HashMap<>();
        totalLines = 0;

        try {
            Path path = Paths.get(csvFilePath);
            List<String> listOfData = new ArrayList<>();

            if (Files.exists(path)) {
                try (Stream<String> lines = Files.lines(path)) {
                    lines.forEach(line -> {
                        totalLines++;

                        String[] tokens = line.split(",");

                        if (categoriesPerHostname.get(tokens[1]) == null){
                            categoriesPerHostname.put(tokens[1], new HashSet<>(Arrays.asList(tokens[0])));
                        }
                        else{
                            categoriesPerHostname.get(tokens[1]).add(tokens[0]);
                        }

                        if (hostnamesPerCategory.get(tokens[0]) == null){
                            hostnamesPerCategory.put(tokens[0], new HashSet<>(Arrays.asList(tokens[1])));
                        }
                        else{
                            categoriesPerHostname.get(tokens[0]).add(tokens[1]);
                        }

                    });
                }
            }
        }catch (IOException exception){
            categoriesPerHostname.clear();
            hostnamesPerCategory.clear();
            totalLines = 0;
        }
    }


    public static int count_lines(String csvFile) throws IOException {
        Path path = Paths.get(csvFile);
        List<String> listOfData = new ArrayList<>();

        if(Files.exists(path)) {
            try (Stream<String> lines = Files.lines(path)) {
                //String line = String.valueOf(lines.findFirst());
                lines.forEach(line->{
                    // System.out.println(line);
                    listOfData.add(line); } );
            }
        }

        return listOfData.size();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(count_lines("url_categories_2020_06_21.csv"));
    }
}
