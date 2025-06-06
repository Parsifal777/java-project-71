package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Paths;
import java.util.Map;

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
                    "file1.json",
                    "file2.json"
            };
        }

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try {
            // Читаем и парсим оба файла
            Map<String, Object> data1 = FileReader.readAndParse(getResourcePath(filePath1));
            Map<String, Object> data2 = FileReader.readAndParse(getResourcePath(filePath2));

            // Генерируем разницу
            String diff = Differ.generate(data1, data2, format);
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private String getResourcePath(String filename) {
        // Для файлов в resources
        return Paths.get("src", "main", "resources", filename).toString();
    }


}