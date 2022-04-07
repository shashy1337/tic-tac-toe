import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GetReady {

    //1
    public static char[][] map = null;
    public static final int SIZE = 3;
    //2
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static Scanner sc = new Scanner(System.in);
    public static Random randInt = new Random();

    //3
    public static void initMap(){
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap(){
        for(int i = 0; i <= SIZE; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //4
    public static void humanTurn(){
         int x, y;
         do {
             System.out.println("Введите координаты в формате X Y");
             x = sc.nextInt() - 1;
             y = sc.nextInt() - 1;
         } while (!isCellValid(x, y));
         map[y][x] = DOT_X;
    }


    //5
    public static boolean isCellValid(int x, int y){
        if(x < 0 || x>= SIZE || y < 0 || y >= SIZE) return false;
        if(map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    //6
    public static void compTurn(){
        int x, y;
        do{
            x = randInt.nextInt(SIZE);
            y = randInt.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
    }

    public static boolean checkWin(char symb){
        if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if(map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if(map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if(map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if(map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if(map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
        return false;
    }

    public static boolean isMapFull(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if(map[i][j] == DOT_EMPTY) return false;
            }
        }

        return true;
    }



    public static void main(String[] args) {
        initMap();
        printMap();
        while (true){
            humanTurn();
            printMap();
            if (checkWin(DOT_X)){
                System.out.println("Победил хуман");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья");
                break;
            }
            compTurn();
            printMap();
            if (checkWin(DOT_O)){
                System.out.println("Победил бот!");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья!");
                break;
            }
        }
        System.out.println("Игра закончена!");
    }
}
