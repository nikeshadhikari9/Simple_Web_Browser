import java.awt.event.*;

public class BrowserController {
    private BrowserView view;

    public BrowserController() {
        view = new BrowserView();

        view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = view.getTextField().getText().trim();

                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://" + url;
                }

                try {
                    view.getEditorPane().setPage(url);
                } catch (Exception ex) {
                    view.setEditorPaneContent("Error loading page: " + ex.getMessage());
                }
            }
        });
    }
}
