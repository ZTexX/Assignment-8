import java.io.*;

class Assignment8B {
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Player[] players = new Player[2];

    System.out.println("[Collision Tester]");
    
    for (int i = 0; i < 2; i++) {
      if (i != 0) System.out.println();

      System.out.println("Create Player " + (i + 1));
      System.out.print("Enter X position: ");
      int x = Integer.parseInt(br.readLine());

      System.out.print("Enter Y position: ");
      int y = Integer.parseInt(br.readLine());

      System.out.print("Enter Player Hitbox Width: ");
      int width = Integer.parseInt(br.readLine());

      System.out.print("Enter Player Hitbox Height: ");
      int height = Integer.parseInt(br.readLine());

      players[i] = new Player(x, y, width, height);
    }

    do {
      System.out.println(String.format("%nPlayer 1 is at (%d,%d) and Player 2 is at (%d,%d)", players[0].getX(), players[0].getY(), players[1].getX(), players[1].getY()));

      int whichPlayer = 0;
      System.out.println("Which one do you want to move?");
      do {
        whichPlayer = Integer.parseInt(br.readLine());
      } while (whichPlayer < 1 || whichPlayer > 2);

      String whichDirection = "";
      System.out.println("Which direction should Player " + whichPlayer + " move (up, down, left, or right)?");
      do {
        whichDirection = br.readLine();
      } while (!whichDirection.equalsIgnoreCase("up") && !whichDirection.equalsIgnoreCase("down") && !whichDirection.equalsIgnoreCase("left") && !whichDirection.equalsIgnoreCase("right"));

      System.out.println("How far should Player " + whichPlayer + " move?");
      int howFar = Integer.parseInt(br.readLine());

      if (whichDirection.equalsIgnoreCase("down") || whichDirection.equalsIgnoreCase("left")) howFar *= -1;

      if (whichDirection.equalsIgnoreCase("up") || whichDirection.equalsIgnoreCase("down")) {
        players[whichPlayer - 1].moveVertical(howFar);
      } else {
        players[whichPlayer - 1].moveHorizontal(howFar);
      }

      Player otherPlayer = players[whichPlayer - 1] == players[0] ? players[1] : players[0];

      if (players[whichPlayer - 1].didTheyCollide(otherPlayer)) {
        System.out.println(String.format("Player 1 is at (%d,%d) and Player 2 is at (%d,%d)", players[0].getX(), players[0].getY(), players[1].getX(), players[1].getY()));
        System.out.println("They collided!");
        break;
      }
    } while (true);

    System.out.println("Program Ends");
  }
}

class Player {
  private int width, height, x, y;

  public Player(int x, int y, int width, int height) {
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void moveHorizontal(int x) {
    this.x += x;
  }

  public void moveVertical(int y) {
    this.y += y;
  }

  public boolean didTheyCollide(Player otherPlayer) {
    if (this.x < (otherPlayer.getWidth() + otherPlayer.getX()) &&
      (this.width + this.x) > otherPlayer.getX() &&
      this.y < (otherPlayer.getHeight() + otherPlayer.getY()) &&
      (this.height + this.y) > otherPlayer.getY()) {
        return true;
    }

    return false;
  }
}