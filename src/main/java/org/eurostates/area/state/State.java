package org.eurostates.area.state;

import org.eurostates.area.Area;
import org.eurostates.area.town.Town;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public interface State extends Area {

    Set<Town> getTowns();

    Set<String> getPermissions();

    @Override
    default Set<UUID> getCitizenIds() {
        return getTowns()
                .parallelStream()
                .flatMap(t -> t.getCitizenIds().parallelStream())
                .collect(Collectors.toSet());
    }
}
