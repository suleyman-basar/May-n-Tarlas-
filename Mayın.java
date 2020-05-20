import java.util.*;

public class Minefield
{

    public static void main(String[] args)
    {
        Random rand = new Random();
        int num = 0;
        Scanner scan = new Scanner(System.in);
        char[][] field = new char[10][10];
        boolean success = true;
           
        //Mayınlar %25 ihtimalle, boş alanlar %75 ihtimalle geliyor
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                num = rand.nextInt(100);

                if (num < 75)
                    field[i][j] = 'O';
                else
                    field[i][j] = '*';
            }
        }

        // Hedef için random pozisyon oluştur (G ile işaretli)
        int goalX = rand.nextInt(10);
        int goalY = rand.nextInt(10);

        field[goalY][goalX] = 'G';


        //Hedefin baş. noktası için random pozisyon oluştur (M ile işaretli)
        int startX = rand.nextInt(10);
        int startY = rand.nextInt(10);

        field[startY][startX] = 'M';


        // başlangıçtaki alanı yazdır
        System.out.println("Alanın: ");
        for (int i = 0; i < 10; i++)
        {
            System.out.println();
            for (int j = 0; j < 10; j++)
                System.out.print(field[i][j]);
        }


        // Geçerli komutlarımız U, D, L, R 
        System.out.print("\n\nKomut girin: ");
        String path = scan.next();

        int pathLength = path.length();

        System.out.println("\n");
        Character move;


        // Oyuncunun hareketini izlemek için.
        // Oyuncu bir mayına giderse veya sonuna ulaşırsa durur.
        for (int i = 0; i < pathLength; i++)
        {
            move = path.charAt(i);

            //Up
            if (move.equals('U'))
            {
                //Mayına basıldığında
                if (field[startY - 1][startX] == '*')
                {
                    field[startY - 1][startX] = 'X';
                    field[startY][startX] = '|';
                    System.out.println("Bumm Patladın!");
                    break;
                }
                //Sona ulaşıldığında
                else if (field[startY - 1][startX] == 'G')
                {
                    field[startY - 1][startX] = 'M';
                    field[startY][startX] = '|';
                    System.out.println("Tebrikler!!");
                    break;
                }

                //sonra devam edilecek durum 
                field[startY][startX] = '|';
                field[startY - 1][startX] = 'M';

                startY = startY - 1;
            }
            
            else if (move == 'D')
            {
                if (field[startY + 1][startX] == '*')
                {
                    field[startY + 1][startX] = 'X';
                    field[startY][startX] = '|';
                    System.out.println("Bumm Patladın!");
                    break;
                }
                else if (field[startY + 1][startX] == 'G')
                {
                    field[startY + 1][startX] = 'M';
                    field[startY][startX] = '|';
                    System.out.println("Tebrikler!!");
                    break;
                }

                field[startY][startX] = '|';
                field[startY + 1][startX] = 'M';

                startY = startY + 1;
            }
            //sola hareket
            else if (move == 'L')
            {
                if (field[startY][startX - 1] == '*')
                {
                    field[startY][startX - 1] = 'X';
                    field[startY][startX] = '-';
                    System.out.println("Bumm Patladın!");
                    break;
                }
                else if (field[startY][startX - 1] == 'G')
                {
                    field[startY][startX - 1] = 'M';
                    field[startY][startX] = '-';
                    System.out.println("Tebrikler!!");
                    break;
                }

                field[startY][startX] = '-';
                field[startY][startX - 1] = 'M';

                startX = startX - 1;
            }
            //sağa hareket
            else if (move == 'R')
            {
                if (field[startY][startX + 1] == '*')
                {
                    field[startY][startX + 1] = 'X';
                    field[startY][startX] = '-';
                    System.out.println("Bumm Patladın!");
                    break;
                }
                else if (field[startY][startX + 1] == 'G')
                {
                    field[startY][startX + 1] = 'M';
                    field[startY][startX] = '-';
                    System.out.println("Tebrikler!!");
                    break;
                }

                field[startY][startX] = '-';
                field[startY][startX + 1] = 'M';

                startX = startX + 1;
            }
        }


        //hareket bittikten sonra alanı yazdırıyor
        //'-' yatayda hareketi '|' dikeyde hareketi gösterir.
        System.out.println("\nAlanınız: ");
        for (int i = 0; i < 10; i++)
        {
            System.out.println();
            for (int j = 0; j < 10; j++)
            {
                System.out.print(field[i][j]);


                if (field[i][j] == 'G')
                    success = false;
            }
        }

        // kapanış mesajı
        if (success)
            System.out.println("\n\nTebrikler, Başarıyla Tamamladın.");
		else
			System.out.println("\n\nBaşaramadın."
                    + "\nBir dahaki sefere iyi şanslar!");

        scan.close();
    }

}