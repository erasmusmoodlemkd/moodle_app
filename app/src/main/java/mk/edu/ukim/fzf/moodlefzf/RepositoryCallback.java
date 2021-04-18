package mk.edu.ukim.fzf.moodlefzf;

public interface RepositoryCallback<T> {
    void onCompleted(T response);
}
