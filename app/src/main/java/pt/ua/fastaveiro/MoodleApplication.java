package pt.ua.fastaveiro;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoodleApplication extends Application {

    ExecutorService executorService = Executors.newFixedThreadPool(4);
    AssetResponseParser assetResponseParser = new AssetResponseParser();
    public ExternalAssetsRepository externalAssetsRepository = new ExternalAssetsRepository(executorService, assetResponseParser);
}
