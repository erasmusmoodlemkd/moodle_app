package mk.edu.ukim.fzf.moodlefzf;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;

public class  ExternalAssetsRepository {

    private AssetResponseParser parser = null;
    private ExecutorService executorService;

    public ExternalAssetsRepository(ExecutorService executorService, AssetResponseParser parser) {
        this.executorService = executorService;
        this.parser = parser;
    }

    public void getAsset(String urlString, final RepositoryCallback<AssetResponse> callback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Result.Success<AssetResponse> result = (Result.Success<AssetResponse>) download(urlString);
                    callback.onCompleted(result.data);
                } catch (Exception e) {

                }
            }
        });
    }

    private Result<AssetResponse> download(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setDoOutput(true);

            AssetResponse loginResponse = parser.parse(httpConnection.getInputStream());
            return new Result.Success<AssetResponse>(loginResponse);
        } catch (Exception e) {
            return new Result.Error<AssetResponse>(e);
        }
    }
}
