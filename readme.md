# Wheel of Fortune
This project is an assignment in course 62550 UX and Android application development taken at DTU.

The rules are as following:

1. The game is for one player.
2. When the game starts, a word is randomly chosen from predefined categories and displayed along with the category.
3. The word is displayed with the letters hidden.
4. The player “spins the wheel”.
5. The possible results of the “spinning the wheel” are: a number of points e.g 1000 or “bankrupt”.
6. In the event of a value being shown, a letter (consonant or vowel) is chosen by the user (from a keyboard or otherwise). If the letter is present, the user’s points total is incremented by the value shown times the number of occurrences of the letter. The occurrences of the letter are revealed in the word. If the letter is not present the user loses a “life”.
7. In the event of “bankrupt” being shown, the user loses all their points.
8. The “wheel is spun” until the game is won or lost. 
9. The game is won when all the letters have been found and the user still has a life. 
10. The game is lost when the user has no lives left and the word has not been found. 
11. A user starts with 5 “lives”.

## Features
- Random word from random category on game start.
- Graphically spinning wheel.
- The user can guess a letter after each spin with a custom made keyboard for letter input.
- The user wins the game after all letters are guessed.
- The user loses the game after all lives are lost.
- The user can play again after a game is ended.
- The user wins points after each correct guess by the spin result times the number of occurences of the letter in the word

## Features to implement
- High score
- Dark mode
- Extended support for longer words/phrases 