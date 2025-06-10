package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@Command(
        name = "gendiff",
        version = "gendiff 1.0",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

    @Parameters(index = "0", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file")
    private String filePath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    private boolean helpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionRequested;

    public static void main(String[] args) {
        // Для тестирования без параметров
        if (args.length == 0) {
            args = new String[]{
                    "src/main/resources/file1.json",
                    "src/main/resources/file2.json"
            };
        }

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try {
            String fullPath1 = getFullPath(filePath1);
            String fullPath2 = getFullPath(filePath2);

            System.out.println("Full path to file 1: " + fullPath1);
            System.out.println("Full path to file 2: " + fullPath2);

            Map<String, Object> file1Map = parseFile(fullPath1);
            Map<String, Object> file2Map = parseFile(fullPath2);

            if (file1Map == null || file2Map == null) {
                System.err.println("Error: One of the files could not be parsed.");
                System.exit(1);
            }

            String diff = Differ.generate(file1Map, file2Map, format);
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private String getFullPath(String filename) {
        // First try to resolve as absolute path
        Path path = Paths.get(filename);
        if (path.isAbsolute()) {
            return path.toString();
        }

        // Then try resources directory
        Path resourcePath = Paths.get("src", "main", "resources", filename);
        if (resourcePath.toFile().exists()) {
            return resourcePath.toString();
        }

        // Finally try relative path
        return path.toAbsolutePath().toString();
    }

    private Map<String, Object> parseFile(String filePath) {
        try {
            ObjectMapper mapper = filePath.toLowerCase().endsWith(".json")
                    ? new ObjectMapper()
                    : new ObjectMapper(new YAMLFactory());

            return mapper.readValue(new File(filePath), Map.class);
        } catch (Exception e) {
            System.err.println("Error parsing file: " + filePath + " - " + e.getMessage());
            return null;
        }
    }
}
