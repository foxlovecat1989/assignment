package assignment2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AccessLogParser {

//  2. Access log parsing
//  There is an access log in the proxy server, which records the track logs and stats.
//  Each row is composed of three columns delimited by empty string.
//  ● request url
//  ● response time
//  ● http status code
//    http://www.yahoo.com/ 150 200
//    https://www.yahoo.com/news/ 200 200
//    https://sports.yahoo.com/ 10 200
//    https://techcrunch.com/startups/ 30 200
//    https://www.huffingtonpost.com/ 70 200
//    https://www.huffingtonpost.co.uk/ 1000 200
//    https://www.huffingtonpost.co.uk/entry/brexit-secretary?utm_hp_ref=uk-politics 500 404
//    https://developer.github.com/apps/building-oauth-apps/ 40 200
//    https://developer.github.com/v3/ 33 200
//    https://developer.github.com:8080/v3/ 77 500
//    Please implement a java program to parse the log to calculate the following statistics
//  ● Count the number of occurrences of the domain grouping by http status code
//    (the domain of the url "https://developer.github.com/v3/" is "developer.github.com")
//  ● Average the numbers in the response time column of each domain
//  ● Calculate 99 percentile of each domain (based on the response time column)

    public static void main(String[] args) throws IOException {
        new AccessLogParser().process();
    }

    private void process() throws IOException {
        Path path = Path.of("src/main/resources/assignment2/assignment2.log");
        List<String> lines = readFile(path);
        checkIsEmptyContent(lines);

        Map<String, Set<String>> countByStatusCode = getCountByStatusCode(lines);
        System.out.println("1. Count the number of occurrences of the domain grouping by http status code: ");
        System.out.println(countByStatusCode);

        Map<String, Double> averageResponseTimeByDomain = getAverageResponseTimeByDomain(lines);
        System.out.println("2. Average the numbers in the response time column of each domain:");
        System.out.println(averageResponseTimeByDomain);

        Map<String, Integer> percentile99ByDomain = getPercentile99ByDomain(lines);
        System.out.println("3. Calculate 99 percentile of each domain:");
        System.out.println(percentile99ByDomain);
    }

    private List<String> readFile(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("讀取檔案發生錯誤");
            throw e;
        }
    }

    private Map<String, Integer> getPercentile99ByDomain(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(" "))
                .collect(Collectors.groupingBy(
                        parts -> getDomain(parts[0]),
                        Collectors.mapping(parts -> Integer.parseInt(parts[1]), Collectors.toList())))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> percentile99(entry.getValue())));
    }

    private Map<String, Double> getAverageResponseTimeByDomain(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(" "))
                .collect(Collectors.groupingBy(
                        parts -> getDomain(parts[0]),
                        Collectors.averagingInt(parts -> Integer.parseInt(parts[1]))));
    }

    private Map<String, Set<String>> getCountByStatusCode(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(" "))
                .collect(Collectors.groupingBy(parts -> parts[2])).entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(element -> getDomain(element[0]))
                                .collect(Collectors.toSet())));
    }

    private void checkIsEmptyContent(List<String> lines) {
        boolean isEmptyContent = lines.isEmpty();
        if (!isEmptyContent)
            return;

        String errorMsg = "log無資料";
        System.out.println(errorMsg);

        throw new IllegalStateException(errorMsg);
    }

    private String getDomain(String url) {
        int start = url.indexOf("//") + 2;
        int port = url.indexOf(":", start);
        boolean isHasPort = port > 0;
        int end = isHasPort ? port : url.indexOf("/", start);
        if (end == -1)
            end = url.length();

        return url.substring(start, end);
    }

    private int percentile99(List<Integer> values) {
        List<Integer> sortedValues = values.stream()
                .sorted().collect(Collectors.toList());
        int index = (int) Math.ceil(0.99 * sortedValues.size()) - 1;

        return values.get(index);
    }
}

