import javax.swing.*;
import java.awt.*;

public class BrowserView {
    private JFrame frame;
    private JTextField searchBar;
    private JEditorPane contentPanel;
    private JButton searchButton;
    private JTextArea infoArea;

    public BrowserView() {
        frame = new JFrame("Illumination Browser");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        searchBar = new JTextField();
        searchBar.setBounds(100, 10, 600, 30);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(searchBar);

        searchButton = new JButton("Search");
        searchButton.setBounds(730, 10, 80, 30);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(searchButton);

        contentPanel = new JEditorPane();
        contentPanel.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(5, 90, 970, 660);
        frame.add(scrollPane);

        infoArea = new JTextArea();
        infoArea.setBounds(5, 50, 970, 40);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        infoArea.setEditable(false);
        frame.add(infoArea);

        frame.setVisible(true);
    }

    public JTextField getTextField() {
        return searchBar;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setEditorPaneContent(String content) {
        contentPanel.setContentType("text/html");
        contentPanel.setText(content);
    }

    public JEditorPane getEditorPane() {
        return contentPanel;
    }

    public void setInfoText(String info) {
        infoArea.setText(info);
    }
}
