package de.egga.designamusement.mainstage;

import java.util.ArrayList;
import java.util.List;

public class MainStage {

    private final Clock clock;
    private final MainStageRepository repository;
    private final ArrayList<Show> shows = new ArrayList<>();

    public MainStage(Clock clock, MainStageRepository repository) {
        this.clock = clock;
        this.repository = repository;
    }

    public List<Show> listAllShows() {
        updateShowsFromRepository();
        return shows;
    }

    public void add(Show newShow) {
        repository.save(newShow);
        shows.add(newShow);
    }

    public List<Show> listShowsBelow(int affordablePrice) {
        updateShowsFromRepository();

        ArrayList<Show> affordableShows = new ArrayList<>();

        for (Show show : shows) {
            if (show.getPrice() <= affordablePrice) {
                affordableShows.add(show);
            }
        }

        return affordableShows;
    }

    public List<Show> listShowsFor(Audience audience) {
        updateShowsFromRepository();

        ArrayList<Show> suitableShows = new ArrayList<>();

        for (Show show : shows) {
            if (show.getAudience().equals(audience)) {
                suitableShows.add(show);
            }
        }

        return suitableShows;
    }

    public Show getNextShow() {
        updateShowsFromRepository();

        Show nextShow = null;
        for (Show show : shows) {
            if (show.getTime().isAfter(clock.now())) {
                if (nextShow == null || show.getTime().isBefore(nextShow.getTime())) {
                    nextShow = show;
                }
            }
        }

        return nextShow;
    }

    private void updateShowsFromRepository() {
        List<Show> persistedShows = repository.receive();
        for (Show persisted : persistedShows) {
            if (!shows.contains(persisted)) {
                shows.add(persisted);
            }
        }
    }
}
