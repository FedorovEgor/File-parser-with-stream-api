package com.foxminded.app;

import com.foxminded.racer.RaceRanker;
import com.foxminded.util.FileToListConverter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        RaceRanker raceRanker = new RaceRanker(new FileToListConverter(), "start.log", "end.log", "abbreviations.txt");

            List<String> rankingTable = raceRanker.getLapRanking();
            rankingTable.forEach(System.out::println);

    }
}

