package pt.ua.fastaveiro;

public interface RepositoryCallback<T> {
    void onCompleted(T response);
}
