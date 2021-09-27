package matej.tejkogames.interfaces.services;

import java.util.List;

public interface ServiceInterface<T, I, R> {

    public T getById(I id);

    public List<T> getAll();

    public T updateById(I id, R requestBody);

    public T create(R requestBody);

    public void deleteById(I id);

    public void deleteAll();

}
