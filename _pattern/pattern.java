package DSASheet._pattern;
import java.util.*;
public class pattern {

    public static void square(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void R_triangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void L_triangle(int n) {
        
        for (int i = 0; i < n; i++) {
                for (int k = 0; k < n-1-i; k++) {
                System.out.print(" ");
                    }
                for (int j = 0; j <= i; j++) {
                    System.out.print("*");
                }
                System.out.println();
        }
    }
    public static void R_num_triangle(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void R_num_triangle_1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i+1; j++) {
                System.out.print(i+1 + " ");
            }
            System.out.println();
        }
    }
    public static void invert_R_triangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    public static void invert_R_num_triangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print(j+1 + " ");
            }
            System.out.println();
        }
    }
    public static void pyramid(int n) {
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n-1-i; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void invert_pyramid(int n) {
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n-1-i; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (2 * n - (2 * i + 1)); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void diamond(int n) {
        pyramid(n);
        invert_pyramid(n);
    }
    public static void half_diamond(int n) {
        for (int i = 1; i <= 2 * n -1; i++) {
            int star = (i > n) ? 2 * n - i : i;
            for (int j = 0; j < star; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        //     int star =0;
        //     int base = (2*n/2) +1;
        // for (int i = 1; i <= 2 * n -1; i++) {
        //     if (i<base) star =i;
        //     else star-=1;
        //     for (int j = 0; j < star; j++) {
        //         System.out.print("*");
        //     }
        //     System.out.println();
        // }
    }

    public static void num_R_triangle(int n) {
        int star;
        for (int i = 0; i < n; i++) {
            star = (i % 2 == 0) ? 1 : 0;
            for (int j = 0; j <= i; j++) {
                System.out.print(star + " ");
                star = 1 - star;
            }
            System.out.println();
        }
        // int star;
        // for (int i = 0; i < n; i++) {
        //     star = (i % 2 == 0) ? 1 : 0;
        //     System.out.print(star + " ");
        //     for (int j = 0; j < i; j++) {
        //         star = 1 - star;
        //         System.out.print(star + " ");
                
        //     }
        //     System.out.println();
    }

    public static void bar_num(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print(j);
            for (int j = 0; j < 2 * (n - i); j++)
                System.out.print(" ");
            for (int j = i; j >= 1; j--)
                System.out.print(j);
            System.out.println();
        }
    }

    public static void R_row_triangle(int n) {
        int jk = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(jk + " ");
                jk++;
            }
            System.out.println();
        }
    }

    public static void R_col_triangle(int n) {
        for (int i = 1; i <= n; i++) {
            int jk = i - n;
            for (int j = 0; j < i; j++) {
                jk += n - j;
                System.out.print(jk + " ");
            }
            System.out.println();
        }
    }

    public static void R_alb_triangle(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print((char)(65 + j) + " ");
            }
            System.out.println();
        }
    }

    public static void R_invert_alb_triangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print((char)(65 + j) + " ");
            }
            System.out.println();
        }
    }

    public static void R_alb_triangle_1(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print((char)(64 + i) + " ");
            }
            System.out.println();
        }
    }

    public static void diamond_alb(int n) {
        for (int i = 0; i < n; i++) {
            int jk = 0;
            int b = (2 * i + 1) / 2;
            for (int j = 0; j < n - i - 1; j++) System.out.print(" ");
            for (int j = 0; j < 2 * i + 1; j++) {
                char ch = (char)(65 + jk);
                System.out.print(ch);
                if (j < b) jk++;
                else jk--;
            }
            System.out.println();
        }
    }
    public static void R_alb_triangle_2(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print((char)(65 + (n - i) + j) + " ");
            }
            System.out.println();
        }
    }
    public static void design_1(int n) {
        int space = -2;
        for (int i = 0; i < 2 * n; i++) {
            int star;
            if (i < n) {
                star = n - i;
                space += 2;
            } else {
                star = i - n + 1;
                space -= 2;
            }
            for (int j = 0; j < star; j++) System.out.print("*");
            for (int j = 0; j < space; j++) System.out.print(" ");
            for (int j = 0; j < star; j++) System.out.print("*");
            System.out.println();
        }
    }

    public static void design_2(int n) {
        int star = 0, space = 0;
        for (int i = 1; i <= 2 * n; i++) {
            if (i <= n) {
                star = i;
                space = 2 * n - 2 * i;
            } else if (i == n + 1) {
                star = n;
                space = 0;
            } else {
                star -= 1;
                space += 2;
            }
            for (int j = 0; j < star; j++) System.out.print("*");
            for (int j = 0; j < space; j++) System.out.print(" ");
            for (int j = 0; j < star; j++) System.out.print("*");
            System.out.println();
        }
    }

    public static void num_square(int n) {
        int size = 2 * n - 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int t = i;
                int l = j;
                int r = size - 1 - j;
                int b = size - 1 - i;
                System.out.print(n - Math.min(Math.min(t, b), Math.min(l, r)));
            }
            System.out.println();
        }
    }
    public static void snack(int n) {
        int size = n;
        int c=0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i%2==0){
                    c=c+1;
                    System.out.print(c+ (c<=9 ?"  ":" "));//just for space 
                }
                else{
                    System.out.print(c--+(c<=9 ?"  ":" "));
                }
            }
            c=c+n;
            System.out.println();
        }
    }
    public static void hollow_square(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == n - 1)
                    System.out.print("* ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }
     public static void numPattern(int n) {
        for (int i = 1; i <= n; i++) {
            int num = i - n;
            for (int j = 0; j < i; j++) {
                num += (n - j);
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = input.nextInt();

        // Example call
        
        R_alb_triangle_2(n);
        input.close();
    }
}


