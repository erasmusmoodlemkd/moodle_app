package mk.edu.ukim.fzf.moodlefzf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetResponseParser {

    public AssetResponse parse(InputStream inputStream) throws IOException {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line=reader.readLine())!=null) {
                sb.append(line).append(" ");
            }
        } finally {
            reader.close();
        }
        return new AssetResponse(sb.toString());
    }
}
