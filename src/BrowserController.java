import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class BrowserController {
    private BrowserView view;

    public BrowserController() {
        view = new BrowserView();

        view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlString = view.getTextField().getText().trim();
                if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
                    urlString = "https://" + urlString;
                }

                String finalUrlString = urlString;
                new Thread(() -> {
                    try {
                        URL url = new URL(finalUrlString);

                        // Display URL parts
                        String info = "Protocol: " + url.getProtocol() +
                                " | Host: " + url.getHost() +
                                " | Port: " + (url.getPort() == -1 ? "default" : url.getPort()) +
                                " | Path: " + url.getFile();
                        SwingUtilities.invokeLater(() -> view.setInfoText(info));

                        // Fetch and display headers
                        URLConnection connection = url.openConnection();
                        connection.connect();
                        Map<String, List<String>> headers = connection.getHeaderFields();
                        System.out.println("--- Response Headers ---");
                        for (String key : headers.keySet()) {
                            System.out.println(key + ": " + headers.get(key));
                        }

                        // Display HTML content
                        SwingUtilities.invokeLater(() -> {
                            try {
                                view.getEditorPane().setPage(finalUrlString);
                            } catch (IOException ex) {
                                view.setEditorPaneContent("Error loading page: " + ex.getMessage());
                            }
                        });

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        SwingUtilities.invokeLater(() -> view.setEditorPaneContent("Error loading page: " + ex.getMessage()));
                    }
                }).start();
            }
        });
    }
}