import java.io.*;

class Assignment8A {
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String movieTitle, genre;
    int releaseYear, howManyMovies;
    float rating;
    LaserDisc[] movies;

    System.out.println("[Laser Disc Collection]");

    System.out.print("How many movies do you have? ");
    howManyMovies = Integer.parseInt(br.readLine());
    movies = new LaserDisc[howManyMovies];

    for (int i = 0; i < howManyMovies; i++) {
      if (i != 0) System.out.println();

      System.out.println("Movie " + (i + 1) + ":");
      System.out.print("*Enter Title: ");
      movieTitle = br.readLine();

      System.out.print("*Enter Genre: ");
      genre = br.readLine();

      System.out.print("*Enter Release Year: ");
      releaseYear = Integer.parseInt(br.readLine());

      System.out.print("*Enter Rating: ");
      rating = Float.parseFloat(br.readLine());

      movies[i] = new LaserDisc(movieTitle, releaseYear, genre, rating);
    }

    int choice = 0;
    do {
      System.out.println("\n[Main Menu]");
      System.out.println("1) Movie Info");
      System.out.println("2) Recommend a Good Movie");
      System.out.println("3) Log off");
      System.out.print("Choice: ");
      do {
        choice = Integer.parseInt(br.readLine());
      } while (choice < 1 || choice > 3);

      switch (choice) {
        case 1:
          int whichMovie;
          System.out.print("\nWhich movie do you want? ");
          whichMovie = Integer.parseInt(br.readLine());

          if (whichMovie < 0 || whichMovie > howManyMovies - 1) {
            System.out.println("Sorry, that's not a valie Movie index");
          } else {
            System.out.println(whichMovie + ". " + movies[whichMovie]);
          }
          break;
        case 2:
          LaserDisc movie;
          do {
            movie = movies[(int) (Math.random() * howManyMovies)];
          } while (!movie.isItGood());
          
          System.out.println("\nYou should try: " + movie.getMovieTitle() + " (" + movie.getReleaseYear() + ")!");
          System.out.println("It has a rating of " + movie.getRating());
          break;
        case 3:
          System.out.println("\nGoodbye!");
          break;
      }
    } while (choice != 3);
  }
}

public class LaserDisc {
  private String movieTitle, genre;
  private int releaseYear;
  private float rating;

  public LaserDisc() {
    this.movieTitle = "Star Wars Holiday Special";
    this.releaseYear = 1978;
    this.genre = "Science Fiction";
    this.rating = 5.0f;
  }

  public LaserDisc(String movieTitle, int releaseYear, String genre, float rating) {
    this.movieTitle = movieTitle;
    this.releaseYear = releaseYear < 1978 ? 1978 : releaseYear;
    this.genre = genre;
    this.rating = rating < 0.0f ? 0.0f : rating > 5.0f ? 0.0f : rating;
  }

  public boolean isItGood() {
    return this.rating >= 3.0f;
  }

  public String getMovieTitle() {
    return this.movieTitle;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  public int getReleaseYear() {
    return this.releaseYear;
  }

  public void setReleaseYear(int releaseYear) {
    this.releaseYear = releaseYear;
  }

  public String getGenre() {
    return this.genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public float getRating() {
    return this.rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public String toString() {
    return this.movieTitle + ", " + this.releaseYear + "\nGenre: " + this.genre + "\nRating: " + this.rating;
  }
}