package br.ufscar.ees.mobile;

/**
 * Created by @ubiratanfsoares
 */

public class Joke {

    private String title;
    private String urlToShare;

    public Joke(String title, String urlToShare) {
        this.title = title;
        this.urlToShare = urlToShare;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToShare() {
        return urlToShare;
    }
}
