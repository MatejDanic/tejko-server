package matej.tejkogames.interfaces.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ServiceInterface<T, I, R> {

    public T getById(I id);

    public List<T> getAll(Integer page, Integer size, String sort, String direction);

    public List<T> getBulkById(Set<I> ids);

    public T updateById(I id, R objectRequest);

    public List<T> updateBulkById(Map<I, R> idObjectRequestMap);

    public T create(R objectRequest);

    public List<T> createBulk(List<R> objectRequestList);

    public void deleteById(I id);

    public void deleteAll();

    public void deleteBulkById(Set<I> idSet);

}
