package id.ac.umn.w07_28917;

import java.io.Serializable;

public class SourceVideo implements Serializable {
    private String title;
    private String description;
    private String videoURI;

    public SourceVideo(String title, String description, String videoURI) {
        this.title = title;
        this.description = description;
        this.videoURI = videoURI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURI() {
        return videoURI;
    }

    public void setVideoURI(String videoURI) {
        this.videoURI = videoURI;
    }

    public String toString() {
        return this.getTitle() + " => " + this.getDescription();
    }
}
