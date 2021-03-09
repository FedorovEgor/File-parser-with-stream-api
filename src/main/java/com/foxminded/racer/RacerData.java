package com.foxminded.racer;

import java.time.Duration;
import java.time.LocalTime;

public class RacerData {
    private String racerAbbreviation;
    private String racerName;
    private String teamName;
    private LocalTime startTime;
    private LocalTime endTime;
    private Duration duration;

    public String getRacerAbbreviation() {
        return racerAbbreviation;
    }

    public void setRacerAbbreviation(String racerAbbreviation) {
        this.racerAbbreviation = racerAbbreviation;
    }

    public String getRacerName() {
        return racerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Duration getLapDuration() {
        return duration;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setLapDuration(Duration duration) {
        this.duration = duration;
    }

}

