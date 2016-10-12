####################################################################################
Card Game Set Application README Documentation
####################################################################################


-Card Game Set Description


The Card Game Set is a card game where a group of players try to identify a "set" of cards from those placed
face-up on a table.Each Card has four attributes : Color,Number,Shape and Shade. Here are individual options for each : 

1.Color (red, green, or purple)

2.Shape (diamond, squiggle, or oval)

3.Shading (solid, empty, or striped)

4.Number (one, two, or three)

Rule : Three cards are a part of a set if, for each property, the values are all the same or all different.

For example:
-The cards "two red solid squiggles", "one green solid diamond", "three purple solid ovals" would make up a
set. (number, shape, and color are different, shading is the same)

-The cards "two red solid squiggles", "one green solid squiggles", "three purple solid ovals" would not make
up a set, because shape is the same on two cards, but different on the third.

-A game of Set starts by dealing 12 cards, face-up. When a player sees three cards that make up a set, they
yell "Set!" and grab the cards. New cards are dealt from the deck to replace them.

-If no player can find a set, three more cards are dealt (to make 15, then 18, then 21…)

-The game is over when there are no cards left in the deck, and no sets left on the table. The player with the
most sets wins.


Following are the API that is included in this particular project :

1.A method that takes three cards, and determines whether the three cards make a set

2.A method that given a "board" of cards, will either find a set, or determine if there are no sets on the table

3.A method that will play an entire game of Set, from beginning to end, and return a list of each valid sets
you removed from the board.


-How to run

> Pre requisites :

-Mac OS /Unix (Needs to be tested for Windows) 
-Java 8 on classpath
Check that could be performed for this ,execute as following:
-------------------------------------------------
Ashwini-Rao-MBP:~ exilant$ java -version
java version "1.8.0_45"
Java(TM) SE Runtime Environment (build 1.8.0_45-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.45-b02, mixed mode)
-------------------------------------------------
If Java 8 not on classpath,then got terminal and edit .bash_profile file and add below :
-------------------------------------------------

export JAVA_HOME=$(/usr/libexec/java_home)

OR

export JAVA_HOME=$(<path to JDK 1.8 Home>)
-------------------------------------------------

-Access to Terminal/Command Prompt

> Running set game :

java -jar set.jar


@See Example Set Runs for more examples
