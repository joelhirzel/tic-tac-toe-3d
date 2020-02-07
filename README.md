# 3D Tic Tac Toe

A local multiplayer Tic Tac Toe game on a three-dimensional board, written in Java with Swing. Choose the player count, board size, and winning line length before each game.

## Run

Requires JDK 11 or newer. No external libraries are needed.

```sh
mkdir -p build
javac --release 11 -d build src/*.java
java -cp build TicTacToe
```

## Controls

| Key | Action |
| --- | --- |
| 1–9 | Choose player count, board size, then winning length |
| Arrow keys | Move the selected cell along the first two axes |
| Comma / period | Move the selected cell along the third axis |
| Enter | Place a move |
| J / L, I / K, O / P | Move the view |
| R / T / Z | Rotate the view |
