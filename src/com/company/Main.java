package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static char[][] map;
    private static final int SIZE = 5;
    private static final int DOTS_TO_WIN = 4;

    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char DOT_EMPTY = '*';

    private static int aiX;
    private static int aiY;
    private static int agoAiX;
    private static int agoAiY;
    private static int aiError;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        initMap();
        printMap();

        boolean aiWin = false;
        boolean humanWin = false;

        do {
            humanTurn();
            printMap();
            humanWin = checkWin(DOT_X);
            if (humanWin) break;
            aiTurn();
            printMap();
            aiWin = checkWin(DOT_O);
            if (aiWin) break;
        } while (!mapIsFull());

        if (aiWin) System.out.println("Вы проиграли...");
        if (humanWin) System.out.println("Вы победили!");
        if (!(humanWin || aiWin)) System.out.println("Ничья.");
    }

    private static boolean checkWin(char symbol) {

        for (char[] row : map) {
            int quantitySymbolInRow = 0;
            for (int i = 0; i < SIZE; i++) {
                if (row[i] == symbol) {
                    quantitySymbolInRow++;
                } else if (quantitySymbolInRow > 0) {
                    quantitySymbolInRow = 0;
                }
                if (quantitySymbolInRow == DOTS_TO_WIN) {
                    return true;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            int quantitySymbolInRow = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == symbol) {
                    quantitySymbolInRow++;
                } else if (quantitySymbolInRow > 0) {
                    quantitySymbolInRow = 0;
                }
                if (quantitySymbolInRow == DOTS_TO_WIN) {
                    return true;
                }
            }
        }

        int quantitySymbolInRow = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == symbol) {
                quantitySymbolInRow++;
            } else if (quantitySymbolInRow > 0) {
                quantitySymbolInRow = 0;
            }
            if (quantitySymbolInRow == DOTS_TO_WIN) {
                return true;
            }
        }
        quantitySymbolInRow = 0;
        for (int i = 0; i < SIZE-1; i++) {
            if (map[i][i+1] == symbol) {
                quantitySymbolInRow++;
            } else if (quantitySymbolInRow > 0) {
                quantitySymbolInRow = 0;
            }
            if (quantitySymbolInRow == DOTS_TO_WIN) {
                return true;
            }
        }
        quantitySymbolInRow = 0;
        for (int i = 0; i < SIZE-1; i++) {
            if (map[i+1][i] == symbol) {
                quantitySymbolInRow++;
            } else if (quantitySymbolInRow > 0) {
                quantitySymbolInRow = 0;
            }
            if (quantitySymbolInRow == DOTS_TO_WIN) {
                return true;
            }
        }


        quantitySymbolInRow = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][SIZE-i-1] == symbol) {
                quantitySymbolInRow++;
            } else if (quantitySymbolInRow > 0) {
                quantitySymbolInRow = 0;
            }
            if (quantitySymbolInRow == DOTS_TO_WIN) {
                return true;
            }
        }
        quantitySymbolInRow = 0;
        for (int i = 0; i < SIZE-1; i++) {
            if (map[i][SIZE-i-2] == symbol) {
                quantitySymbolInRow++;
            } else if (quantitySymbolInRow > 0) {
                quantitySymbolInRow = 0;
            }
            if (quantitySymbolInRow == DOTS_TO_WIN) {
                return true;
            }
        }
        quantitySymbolInRow = 0;
        for (int i = 0; i < SIZE-1; i++) {
            if (map[i+1][SIZE-i-1] == symbol) {
                quantitySymbolInRow++;
            } else if (quantitySymbolInRow > 0) {
                quantitySymbolInRow = 0;
            }
            if (quantitySymbolInRow == DOTS_TO_WIN) {
                return true;
            }
        }
        return false;
    }

    private static boolean mapIsFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    private static void aiTurn() {
        
        do {
            aiError++;
            if (aiError > 100) {
                aiError();
                break;
            }
        if(agoAiX == aiX){
            } else {
                if (aiX < agoAiX) {
                    agoAiX = aiX;
                    aiX = aiX - 3;
                    if (aiX < 1) {
                        agoAiX = aiX;
                        aiX = aiX + 2;
                    }
                } else {
                    agoAiX = aiX;
                    aiX++;
                    if (aiX > SIZE) {
                        agoAiX = aiX;
                        aiX = aiX - 2;
                    }
                }
            }

            if (agoAiY == aiY){
            } else {
                if (aiY < agoAiY) {
                    agoAiY = aiY;
                    aiY = aiY - 3;
                    if (aiY < 1) {
                        agoAiY = aiY;
                        aiY = aiY + 2;
                    }
                } else {
                    agoAiY = aiY;
                    aiY++;
                    if (aiY > SIZE) {
                        agoAiY = aiY;
                        aiY = aiY - 2;
                    }
                }
            }
        } while (!isCellValid(aiX, aiY));
        if (aiError != 0){
            map[aiX][aiY] = DOT_O;
        }
    }

    private static void aiError(){
        int x;
        int y;
        do {
            x = new Random().nextInt(SIZE);
            y = new Random().nextInt(SIZE);
            aiError++;
            if (aiError > 100){
                break;
            }
        } while (!isCellValid(x, y));
        map[x][y] = DOT_O;
        aiError = 0;
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты");
            System.out.println("Введите X");
            x = scanner.nextInt() -1;
            System.out.println("Введите Y");
            y = scanner.nextInt() -1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
        aiX = x; aiY = y;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE && map[x][y] == DOT_EMPTY;
    }

    private static void printMap() {
        for (int i = 0; i < SIZE; i++) {
            System.out.println();
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void initMap(){
        map = new char[SIZE][SIZE];
        for (char[] row : map){
            Arrays.fill(row, DOT_EMPTY);
        }
    }
}
