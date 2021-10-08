package matej.tejkogames.interfaces.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ServiceInterface<T, I, R> {

    public T getById(I id);

    public List<T> getAll(Integer page, Integer size, String sort, String direction);

    public List<T> getAllByIdIn(Set<I> ids);

    public T updateById(I id, R requestBody);

    public List<T> updateAll(Map<I, R> idRequestMap);

    public T create(R requestBody);

    public void deleteById(I id);

    public void deleteAll();

    public void deleteAllById(Set<I> idSet);

}
