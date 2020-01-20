package nsfd.desktop.network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpClient {

    private final String host;
    private final int port;
    private final int timeout;

    public TcpClient(String host, int port, int timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public String executeRequest(String data) throws IOException {
        var socket = new Socket(host, port);
        socket.setSoTimeout(timeout);

        OutputStream output = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(output, StandardCharsets.UTF_8);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        InputStream input = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);

        bufferedWriter.write(data.toCharArray());
        bufferedWriter.flush();

        StringBuilder response = new StringBuilder();
        for (int c = reader.read(); c != -1; c = reader.read()) {
            response.append((char) c);
        }

        return response.toString();
    }
}
