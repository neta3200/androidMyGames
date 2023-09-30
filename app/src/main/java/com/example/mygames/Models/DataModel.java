package com.example.mygames.Models;

public class DataModel {
    private String title;
    private String publisher;
    private String description;
    private String gameUrl;
    private String genre;
    private String platform;
    private String developer;
    private String thumbnail;
    private String releaseDate;

    public DataModel(String title, String publisher, String description, String gameUrl, String genre, String platform, String developer, String thumbnail, String releaseDate) {
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.gameUrl = gameUrl;
        this.genre = genre;
        this.platform = platform;
        this.developer = developer;
        this.thumbnail = thumbnail;
        this.releaseDate = releaseDate;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getGameUrl() { return gameUrl; }
    public void setGameUrl(String gameUrl) { this.gameUrl = gameUrl; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    public String getDeveloper() { return developer; }
    public void setDeveloper(String developer) { this.developer = developer; }
    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
}
