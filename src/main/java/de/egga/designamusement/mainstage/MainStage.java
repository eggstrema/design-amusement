package de.egga.designamusement.mainstage;

import java.util.ArrayList;
import java.util.List;

public class MainStage {

    private final ArrayList<Show> shows = new ArrayList<>();
    private final MainStageRepository repository;

    public MainStage(MainStageRepository repository) {
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

    private void updateShowsFromRepository() {
        List<Show> persistedShows = repository.receive();
        for (Show persisted : persistedShows) {
            if (!shows.contains(persisted)) {
                shows.add(persisted);
            }
        }
    }
}
