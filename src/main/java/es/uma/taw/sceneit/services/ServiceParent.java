package es.uma.taw.sceneit.services;
import es.uma.taw.sceneit.entity.toDTO;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceParent<E extends toDTO<D>, D> {

    protected List<D> convertToDTOList(List<E> entities) {
        return entities.stream()
                .map(E::toDTO)
                .collect(Collectors.toList());
    }
}