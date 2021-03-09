package com.foxminded.racer;

import com.foxminded.util.FileToListConverter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class RaceRanker {

    private FileToListConverter fileReader;
    private String startTimesFileName;
    private String endTimesFileName;
    private String abbreviationsFileName;

    public RaceRanker(FileToListConverter fileReader, String startTimesFileName, String endTimesFileName, String abbreviationsFileName) {
        this.fileReader = fileReader;
        this.startTimesFileName = startTimesFileName;
        this.endTimesFileName = endTimesFileName;
        this.abbreviationsFileName = abbreviationsFileName;
    }

    public List<String> getLapRanking() throws IOException, URISyntaxException {
        List<String> startTimesList = fileReader.read(startTimesFileName);
        List<String> endTimesList = fileReader.read(endTimesFileName);
        List<String> abbreviationsList = fileReader.read(abbreviationsFileName);

        AtomicInteger counter = new AtomicInteger(1);

        List<String> sortedRacersList = abbreviationsList.stream()
                .map(currentElement -> {
                     String[] splitedString = currentElement.split("_");
                     RacerData racerData = new RacerData();

                     racerData.setRacerAbbreviation(splitedString[0]);
                     racerData.setRacerName(splitedString[1]);
                     racerData.setTeamName(splitedString[2]);

                     return racerData;
                })
                .peek(currentRacerData -> currentRacerData.setStartTime(getLocalTime(startTimesList, currentRacerData.getRacerAbbreviation())))
                .peek(currentRacerData -> currentRacerData.setEndTime(getLocalTime(endTimesList, currentRacerData.getRacerAbbreviation())))
                .peek(currentRacerData -> currentRacerData.setLapDuration(Duration.between(currentRacerData.getStartTime(), currentRacerData.getEndTime())))
                .sorted(Comparator.comparing(RacerData::getLapDuration))
                .map(currentRacerData -> convertToStringResult(counter, currentRacerData))
                .collect(Collectors.toList());

        return sortedRacersList;
    }

    private String convertToStringResult(AtomicInteger counter, RacerData currentRacerData) {
        int count = counter.getAndIncrement();
        String result = String.format("%-19s | %-25s | %s", count + "." + currentRacerData.getRacerName(), currentRacerData.getTeamName(), formatDuration(currentRacerData.getLapDuration()));
        if (count == 15) {
            return result + "\n----------------------------------------------------------" ;
        }
        return result;
    }


    private String formatDuration(Duration duration) {
        return DateTimeFormatter.ofPattern("m:ss.SSS").format(LocalTime.MIDNIGHT.plus(duration));
    }

    private LocalTime getLocalTime(List<String> listContainingTimeString, String abbreviation) {
        LocalTime localTime = listContainingTimeString.stream().filter(currentString -> currentString.contains(abbreviation))
                .map(current -> LocalTime.parse(current.substring(current.indexOf("_") + 1))).findFirst().orElse(null);
        return localTime;
    }

}

