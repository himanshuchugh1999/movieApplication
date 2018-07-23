package com.brillicaservices.gurjas.firebasemoviessample.movies;

public class MoviesModelView {
    String title;
    int releaseYear;
    String description;
    double rating;
    int image;
    int id;

    public MoviesModelView(int id,String title, String description, int releaseYear, int rating, int image) {
        this.id=id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.image = image;
    }
    public MoviesModelView(String title, String description, int releaseYear,double rating,int image) {
//
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.image = image;
    }

    public MoviesModelView() {

    }




    public int getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public int getId(){return id;}

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setId(int id) {
        this.id = id;
    }
}
