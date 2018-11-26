/* Here is how the game works for reference:

The escape room requires three conditions to be met, occurring in three individually connected rooms located in a giant mansion.
In order to successfully win the escape room, you have only 30 turns to solve the room before you are trapped in the mansion forever.

Foyer-
The foyer is where the player starts the game. Inside the foyer is a bench, a chest, an unlit candle, and a note. There is a door to the north, but it is locked.
The note says, "May my light show you the way."
Inside the chest is a box of matches.
When you light the match, and then light the candle, the door to the next room to the north is unlocked, and you can move to the next chamber.

Library-
In the library there are stacks of books lining the shelves, a desk, a pen, and a scroll. There is another locked door to the north.
The scroll says, "Share your story."
When you search the bookshelf, there is an open book on one shelf titled "The Autobiography of..." with the rest of the title empty.
When you pick up the pen and write your name on the book, the door to the north opens.

Conservatory-
In the conservatory, there is a large door to the north that is locked. There are three instruments here- a trumpet, a drum, and a piano. There is a piece of sheet music on a stand in the center of the room.
The sheet music is empty, but it has this phrase written on the musical staff: "Timbre, Tone, and Time" representing the Trumpet, Piano, and Drum.
When the trumpet, piano, and drum are played in that order, the door to the north opens, and you are able to escape the room and complete the game.

The player can only input two-word verb-noun statements from a given list of verbs and nouns. There are also four directional commands (north, south, east, and west).

Verbs-
open
read
look
close
write
get
light
play

Nouns-
door
candle
book
trumpet
room
note
pen
piano
bench
matches
scroll
drum
chest
shelves
music
lock
  */

// import scanner so user can input commands when prompted
import java.util.Scanner;

public class Main {

    public static Scanner scan;
    public static int turnsToEscape = 30;
    public static String output = "You find yourself in a large, dim foyer. There is a great, wooden door with a gold handle to the north. To the west is a red bench pushed up against the wall. To the east is a small, black chest on the floor. In the center of the room, there is a large candle. The candle is unlit.\n";

    //location for player; start with inFoyer as true because this is the room the player begins in
    public static boolean inFoyer = true;
    public static boolean inLibrary = false;
    public static boolean inConservatory = false;
    //foyer conditions (determined through tests)
    public static boolean chestOpen = false;
    public static boolean hasMatches = false;
    public static boolean candleLit = false;
    public static boolean redDoorLocked = true;
    public static boolean redDoorOpen = false;
    //library conditions (determined through tests)
    public static boolean getPen = false;
    public static boolean writeBook = true;
    public static boolean blueDoorLocked = true;
    public static boolean blueDoorOpen = false ;
    //conservatory conditions (determined through tests)
    public static boolean playTrumpet = false;
    public static boolean playPiano = false;
    public static boolean playDrum = false;


    public static void main(String[] args) {

        //introductory statement containing information for player
        System.out.printf("Greetings, player! You are trapped in a mansion and need to escape, but you only have 30 turns. You can enter the commands [help], [look], or two-word verb-noun statements. The command [help] will remind you of the commands you can use, and [look] without a noun will restate the description of the room you are in. Your verbs are [open], [read], [look], [close], [write], [get], [light], and [play]. Your nouns are [door], [candle], [book], [trumpet], [room], [note], [pen], [piano], [bench], [matches], [scroll], [drum], [chest], [shelves], [music], and [lock]. To move, you can indicate direction with the commands [north], [south], [east], and [west]. Do not include brackets in your inputs. Best of luck!\n\n");

        //ensure number of turns to escape is maxed out at 30; playing trumpet, piano, and drum stops turns
        for (int i = 1; i < 31; i++) {
            playGame();
            turnsToEscape--;
            if (playTrumpet && playPiano && playDrum) {
                break;
            }
        }

        //player escapes and completes game when trumpet, piano, and drum are played in that order; use if-else statement
        if (playTrumpet && playPiano && playDrum) {
            System.out.printf("\nSuccess! The yellow door to the north opens and you escape the conservatory, completing the game with %d spare turns.", turnsToEscape);
        }
        else {
            System.out.printf("\nNice try! You have failed the escape room challenge. Better luck next time!");
        }
    }

    //location of player (method for each room)
    public static void playGame() {
        if (inFoyer) {
            foyer();
        }
        else if (inLibrary) {
            library();
        }
        else if (inConservatory) {
            conservatory();
        }
    }

    //possible cases for foyer
    public static void foyer() {
        System.out.printf("\n%s You have %d turns to escape.\n>>", output, turnsToEscape);
        scan = new Scanner(System.in);
        String testFoyer = scan.nextLine();

        switch (testFoyer) {
            case "help":
                output = "Verbs are open, close, light, read, write, play, look, get. Nouns are door, room, bench, chest, candle, note, matches, shelves, book, pen, scroll, music, trumpet, piano, drum, lock. Directions are north, south, east, west. Good luck!\n";
                break;

            case "look":
                output = "You find yourself in a large, dim foyer. There is a great, wooden door with a gold handle to the north. To the west is a red bench pushed up against the wall. To the east is a small, black chest on the floor. In the center of the room, there is a large candle. The candle is unlit.\n";
                break;

            case "look candle":
                output = "The candle is not lit.\n";
                break;

            case "look bench":
                output = "There is a small, folded-up note sitting on the bench against the western wall.\n";
                break;

            case "read note":
                output = "After unfolding the note, you can make out the phrase \"May my light show you the way\" in cursive.\n";
                break;

            case "look chest":
                output = "You see the small, black chest on the floor, but it is closed. However, it does not seem to be locked.\n";
                break;

            case "open chest":
                chestOpen = true;
                output = "Upon opening the chest, you find a pack of matches.\n";
                break;

            case "get matches":
                hasMatches = true;
                output = "You retrieve the pack of matches from the chest.\n";
                break;

            case "light candle":
                if (hasMatches) {
                    candleLit = true;
                    redDoorLocked = false;
                    output = "You light the candle with a match. Suddenly, a fierce wind blows through the room, extinguishing the match in your hand but making the candle burn brighter. You then hear a loud metal grinding sound from the north.\n";
                }
                else {
                    output = "You do not have any matches and cannot light the candle :(\n";
                }
                break;

            case "look door":
                if (!redDoorLocked) {
                    if (redDoorOpen) {
                        output = "The door to the north is unlocked and open.\n";
                    }
                    else {
                        output = "The door to the north is unlocked, but it's closed.\n";
                    }
                }
                else {
                    output = "The door to the north is locked :(\n";
                }
                break;

            case "open door":
                if (!redDoorLocked) {
                    redDoorOpen = true;
                    output = "The door has been opened.\n";
                }
                break;

            case "north":
                if (redDoorOpen) {
                    inFoyer = false;
                    inLibrary = true;
                    output = "You leave the foyer, and the door slams loudly behind you. It relocks automatically, so you cannot return to the foyer. You now find yourself in a library filled with shelves of books. There is a wooden desk in the center of the room with a pen and scroll lying on it. There is a blue door to the north with a silver handle.\n";
                }
                break;

            default:
                output = "In the foyer there is a bench, a chest, an unlit candle, and a note. There is a locked door to the north.\n";
        }
    }

    //possible cases for library
    public static void library() {
        System.out.printf("\n%sYou have %d turns left.\n>>", output, turnsToEscape);
        scan = new Scanner(System.in);
        String testLibrary = scan.nextLine();

        switch (testLibrary) {
            case "help":
                output = "Verbs are open, close, light, read, write, play, look, get. Nouns are door, room, bench, chest, candle, note, matches, shelves, book, pen, scroll, music, trumpet, piano, drum, lock. Directions are north, south, east, west. Good luck!\n";
                break;

            case "look":
                output = "You find yourself in a library filled with shelves of books. There is a wooden desk in the center of the room with a pen and scroll lying on it. There is a blue door to the north with a silver handle.\n";
                break;

            case "look shelves":
                output= "When you search the closest bookshelf, you see an open book one of the shelves that stands out to you. It is titled \"The Autobiography of...\" with the rest of the title missing.\n";
                break;

            case "get pen":
                getPen = true;
                output = "You have picked up the pen from the desk.\n";
                break;

            case "read scroll":
                output = "You unfold the scroll. It says \"Share your story\" in cursive writing.\n";
                break;

            case "write book":
                writeBook = true;
                blueDoorLocked = false;
                output = "By writing your name on the book, you complete the autobiography. You then hear a loud metal grinding sound from the north.\n";
                break;

            case "look door":
                if (!blueDoorLocked) {
                    if (blueDoorOpen) {
                        output = "The door to the north is unlocked and open.\n";
                    }
                    else {
                        output = "The door to the north is unlocked, but it's closed.\n";
                    }
                }
                else {
                    output = "The door to the north is locked :(\n";
                }
                break;

            case "open door":
                if (!blueDoorLocked) {
                    blueDoorOpen = true;
                    output = "The door has been opened.\n";
                }
                break;

            case "north":
                if (blueDoorOpen) {
                    inLibrary = false;
                    inConservatory = true;
                    output = "You leave the library, and the door slams loudly behind you. It relocks automatically, so you cannot return to the library. You now find yourself in the conservatory. There is a large yellow door to the north that is locked. There are three instruments- a shiny trumpet, a drum, and a grand piano. There's also a piece of sheet music on a stand in the middle of the room.\n";
                }
                break;

            default:
                output = "In the library there are stacks of books lining the shelves, a desk, a pen, and a scroll. There is another locked door to the north.\n";
        }
    }

    public static void conservatory()  {
        System.out.printf("\n%s You have %d turns left.\n>>", output, turnsToEscape);
        scan = new Scanner(System.in);
        String testConservatory = scan.nextLine();

        switch (testConservatory) {
            case "help":
                output = "Verbs are open, close, light, read, write, play, look, get. Nouns are door, room, bench, chest, candle, note, matches, shelves, book, pen, scroll, music, trumpet, piano, drum, lock. Directions are north, south, east, west. Good luck!\n";
                break;

            case "look music":
                output= "The sheet music is empty, but there is something written on the musical staff.\n";
                break;

            case "read music":
                output = "You look at the musical staff and see this phrase: \"Timbre, Tone, and Time\". You are well-versed in music and know that this represents the trumpet, piano, and drum.\n";
                break;

            case "play trumpet":
                if (!playPiano && !playDrum) {
                    playTrumpet = true;
                    output = "Luckily, you know how to play the trumpet. You do so successfully.\n";
                }
                else {
                    output = "Oh no... the trumpet is broken and won't play :(\n";
                }
                break;

            case "play piano":
                if (playTrumpet && !playDrum) {
                    playPiano = true;
                    output = "Luckily, you know how to play the piano. You successfully play Someone Like You by Adele.\n";
                }
                else {
                    output = "Oh no... the piano is broken and won't play :(\n";
                }
                break;

            case "play drum":
                if (playTrumpet && playPiano) {
                    playDrum = true;
                    output = "Luckily, you know how to play the drums, too! You play Fool in the Rain by Led Zeppelin successfully.\n";
                }
                else {
                    output = "Oh no... the drum is broken and won't play :(\n";
                }
                break;

            default:
                output = "In the conservatory there is a large door to the north that is locked. There are three instruments- a trumpet, a piano, and a drum. There is a sheet of music on a stand.\n";
        }
    }
}
