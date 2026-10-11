package org.example.dsaa.lab01;

public class Drawer {
	private static void drawLine(int n, char ch) {
		// TODO

	}


	public static void drawPyramid(int n) {
        int barLenght = 2*n - 1;
        for (int i = 1; i <= 2*n; i += 2) {
            for (int j = 0; j < (barLenght - i)/2; j++) {
                System.out.print(".");
            }

            for (int j = 0; j < i; j++) {
                System.out.print("X");
            }

            for (int j = 0; j < (barLenght - i)/2; j++) {
                System.out.print(".");
            }
            System.out.println();
        }

	}

	
	public static void drawChristmassTree(int n) {
        int barLenght = 2*n - 1;
        for (int k = 1; k <= n; k ++) {

            for (int i = 1; i <= 2*k; i += 2) {
                for (int j = 0; j < (barLenght - i)/2; j++) {
                    System.out.print(".");
                }

                for (int j = 0; j < i; j++) {
                    System.out.print("X");
                }

                for (int j = 0; j < (barLenght - i)/2; j++) {
                    System.out.print(".");
                }
                System.out.println();

            }
        }

	}

}
