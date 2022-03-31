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
        List<Show> persistedShows = repository.receive();
        for (Show persisted : persistedShows) {
            if (!shows.contains(persisted)) {
                shows.add(persisted);
            }
        }

        return shows;
    }

    public void add(Show newShow) {
        repository.save(newShow);
        shows.add(newShow);
    }
}
