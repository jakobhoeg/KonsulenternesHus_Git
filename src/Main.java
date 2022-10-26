package src;
import java.sql.*;
import java.util.*;

public class KonsulenternesHus {


    public static void main(String args[]) {

        do {
            try {
                System.out.println("Velkommen til Konsulenternes Hus");
                System.out.println("Ønsker du at logge ind som konsulent? "+GREEN+"Tast 1"+RESET);
                System.out.println("Ønsker du at logge ind som fastansat? "+GREEN+"Tast 2"+RESET);
                Scanner sc = new Scanner(System.in);
                int nr = sc.nextInt();

                if (nr == 1) {
                    loginKonsulent();
                }
                else if (nr == 2) {
                    loginFastAnsat();
                }
                else {
                    System.out.println("Forkert input!");
                }

                //Step 2 efter man er logget ind via "loginKonsulent" eller "loginFastansat".
                //Hvis man valgte 1 helt i starten, kører den successLoginKonsulent, ellers kører den successLoginFastAnsat
                if (nr == 1) {
                    successLoginKonsulent();
                }
                else if (nr == 2) {
                    successLoginFastAnsat();
                }
                else {
                    System.out.println("Forkert input!");
                }


            } catch (Exception e) {
                System.out.println("Du kan kun indtaste tal!");
            }
        } while (true);

    }


    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";

    public static String loginBrugerNavn;


    static void loginKonsulent() {
        do {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=KonsulenternesHus","sa","1234");
                Statement stmt = con.createStatement();
                Scanner sc = new Scanner(System.in);
                System.out.print("Indtast brugernavn: ");
                String brugernavn = sc.next();
                System.out.print("Indtast kodeord: ");
                String kodeord = sc.next();
                ResultSet rs=stmt.executeQuery("select * from tblKonsulent where fldBrugernavn = '"+brugernavn+"' and fldKodeord = '"+kodeord+"'");
                if(rs.next()){
                    System.out.println("Du er nu logget ind.");
                    System.out.println();
                    loginBrugerNavn = brugernavn;
                    break;
                }
                else{
                    System.out.println("Forkert brugernavn eller kode. Prøv igen.");
                }
            } catch (Exception e) {
                System.out.println("Databasefejl: " + e.getMessage());
            }
        } while (true);
    }


    static void loginFastAnsat() {
        do {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=KonsulenternesHus","sa","1234");
                Statement stmt = con.createStatement();
                Scanner sc = new Scanner(System.in);
                System.out.print("Indtast brugernavn: ");
                String brugernavn = sc.next();
                System.out.print("Indtast kodeord: ");
                String kodeord = sc.next();
                ResultSet rs = stmt.executeQuery("select * from tblFastansat where fldBrugernavn = '"+brugernavn+"' and fldKodeord = '"+kodeord+"'");
                if(rs.next()){
                    System.out.println("Du er nu logget ind.");
                    System.out.println();
                    loginBrugerNavn = brugernavn;
                    break;
                }
                else{
                    System.out.println("Forkert brugernavn eller kode. Prøv igen.");
                }
            } catch (Exception e) {
                System.out.println("Databasefejl: " + e.getMessage());
            }
        } while (true);
    }

    static void successLoginKonsulent() {
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Velkommen,"+GREEN+" "+loginBrugerNavn + "!" + RESET);
                System.out.println("Dette er hovedmenuen for konsulenter.");
                System.out.println("Ønsker du at at se en liste med konsulenter? "+GREEN+"Tast 1"+RESET);
                System.out.println("Ønsker du at se en liste med projekter? "+GREEN+"Tast 2"+RESET);
                int nummer = sc.nextInt();

                if (nummer == 1){
                    DB.selectSQL("SELECT fldKonsulentID, fldKonsulentNavn, fldEmail, fldTlf, fldBranche, fldBeskrivelse FROM tblKonsulent");
                    DB.showDisplayData();

                    System.out.println();
                    System.out.println("Ønsker du at se en anden liste? "+GREEN+"Tast 1"+RESET);
                    System.out.println("Ønsker du at logge ud? "+GREEN+"Tast 2"+RESET);
                    int nummer2 = sc.nextInt();
                    if (nummer2 == 1) {
                        continue;
                    }
                    else {
                        System.out.println("Du er nu logget ud!");
                        System.exit(0);
                    }
                }
                else if (nummer == 2){
                    DB.selectSQL("SELECT fldProjektID, fldProjektNavn, fldBeskrivelse, fldStartDato, fldEstimeretTidUger, fldBeskrivelse FROM tblProjekt");
                    DB.showDisplayData();

                    System.out.println();
                    System.out.println("Ønsker du at se en anden liste? "+GREEN+"Tast 1"+RESET);
                    System.out.println("Ønsker du at logge ud? "+GREEN+"Tast 2"+RESET);
                    int nummer2 = sc.nextInt();
                    if (nummer2 == 1) {
                        continue;
                    }
                    else {
                        System.out.println("Du er nu logget ud!");
                        System.exit(0);
                    }
                }
                else{
                    System.out.println("Forkert input");
                }
            } catch (Exception e) {
                System.out.println("Du kan kun indtaste tal");
            }
        } while (true);
    }

    static void successLoginFastAnsat(){
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Velkommen,"+GREEN+" "+loginBrugerNavn + "!" + RESET);
                System.out.println("Dette er hovedmenuen for fastansatte.");
                System.out.println("Ønsker du at oprette en ny konsulent? " + GREEN + "Tast 1" + RESET);
                System.out.println("Ønsker du at opdatere en eksisterende konsulent? "+GREEN+"Tast 2"+RESET);
                System.out.println("Ønsker du at se en liste over konsulenter? "+GREEN+"Tast 3"+RESET);
                System.out.println("Ønsker du at oprette et nyt projekt? "+GREEN+"Tast 4"+RESET);
                System.out.println("Ønsker du at opdatere et eksisterende projekt? "+GREEN+"Tast 5"+RESET);
                System.out.println("Ønsker du at se en liste over projekter? "+GREEN+"Tast 6"+RESET);
                System.out.println("Ønsker du at logge ud? "+GREEN+"Tast 0"+RESET);
                int fastansatOpg = sc.nextInt();

                //Logge ud
                if (fastansatOpg == 0) {
                    System.out.println("Du er nu logget ud!");
                    System.exit(0);
                }

                //Opret konsulent
                else if (fastansatOpg == 1) {
                    opretKonsulent();
                }

                //Opdatere eksisterende konsulent
                else if (fastansatOpg == 2) {
                    opdaterKonsulent();
                }

                else if (fastansatOpg == 3) {
                    seKonsulentListe();
                }

                else if (fastansatOpg == 4) {
                    opretProjekt();
                }

                else if (fastansatOpg == 5) {
                    opdaterProjekt();
                }

                else if (fastansatOpg == 6) {
                    seProjektListe();
                }

                else {
                    System.out.println("Forkert input!");
                }

            } catch (Exception e) {
                System.out.println("Du kan kun indtaste tal!");
            }
        } while (true);
    }

    static void opretKonsulent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indtast konsulentens navn: ");
        String konsulentNavn = sc.nextLine();
        System.out.println("Indtast konsulentens email: ");
        String konsulentEmail = sc.nextLine();
        System.out.println("Indtast konsulentens telefonnummer: ");
        String konsulentTlf = sc.nextLine();
        System.out.println("Indtast konsulentens branche: ");
        String konsulentBranche = sc.nextLine();
        System.out.println("Indtast konsulentens beskrivelse (maks 250 tegn): ");
        String konsulentBeskrivelse = sc.nextLine();
        System.out.println("Indtast konsulentens ønskede login brugernavn: ");
        String konsulentBrugernavn = sc.nextLine();
        System.out.println("Indtast konsulentens ønskede login kodeord: ");
        String konsulentKodeord = sc.nextLine();

        DB.insertSQL("Insert into tblKonsulent values('"+ konsulentNavn + "','" + konsulentEmail + "','"  + konsulentTlf + "','" + konsulentBranche + "','" + konsulentBeskrivelse + "','" + konsulentBrugernavn + "','" + konsulentKodeord + "')");
        System.out.println("Konsulent oprettet!");

        System.out.println("Ønsker du at oprette/redigere yderligere eller ønsker du at logge ud? Tast 1 for at redigere yderligere, tast 2 for at logge ud");
        Scanner sc1 = new Scanner(System.in);
        int jaNej = sc1.nextInt();
        if (jaNej == 1) {
            return;
        } else {
            System.out.println("Du er nu logget ud!");
            System.exit(0);
        }
    }

    static void opdaterKonsulent() {
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Hvad ønsker du at opdatere? Hav venligst konsulentID klar.");
                System.out.println(GREEN + "Tast 1"+RESET + " for at opdatere konsulentens navn");
                System.out.println(GREEN + "Tast 2"+RESET + " for at opdatere konsulentens email");
                System.out.println(GREEN + "Tast 3"+RESET + " for at opdatere konsulentens telefonnummer");
                System.out.println(GREEN + "Tast 4"+RESET + " for at opdatere konsulentens branche");
                System.out.println(GREEN + "Tast 5"+RESET + " for at opdatere konsulentens beskrivelse");
                System.out.println(GREEN + "Tast 6"+RESET + " for at opdatere konsulentens login brugernavn");
                System.out.println(GREEN + "Tast 7"+RESET + " for at opdatere konsulentens login kodeord");
                System.out.println(GREEN + "Tast 0"+RESET + " for at gå tilbage til hovedmenuen");
                int opdaterKonsulent = sc.nextInt();

                if (opdaterKonsulent == 0) {
                    break;
                }
                else if (opdaterKonsulent == 1) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast konsulentens ID: ");
                    int konsulentID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast konsulentens nye navn: ");
                    String konsulentNavn = sc1.nextLine();
                    DB.updateSQL("Update tblKonsulent set fldKonsulentNavn = '"+konsulentNavn+"' where fldKonsulentID = '"+konsulentID+"'");
                    System.out.println("Du har opdateret konsulentens navn!");
                    System.out.println();
                }
                else if (opdaterKonsulent == 2) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast konsulentens ID: ");
                    int konsulentID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast konsulentens nye email: ");
                    String konsulentEmail = sc1.nextLine();
                    DB.updateSQL("Update tblKonsulent set fldEmail = '"+konsulentEmail+"' where fldKonsulentID = '"+konsulentID+"'");
                    System.out.println("Du har opdateret konsulentens email!");
                    System.out.println();
                }
                else if (opdaterKonsulent == 3) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast konsulentens ID: ");
                    int konsulentID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast konsulentens nye telefonnummer: ");
                    int konsulentTlf = sc1.nextInt();
                    DB.updateSQL("Update tblKonsulent set fldTlf = '"+konsulentTlf+"' where fldKonsulentID = '"+konsulentID+"'");
                    System.out.println("Du har opdateret konsulentens telefonnummer!");
                    System.out.println();
                }
                else if (opdaterKonsulent == 4) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast konsulentens ID: ");
                    int konsulentID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast konsulentens nye branche: ");
                    String konsulentBranche = sc1.nextLine();
                    DB.updateSQL("Update tblKonsulent set fldBranche = '"+konsulentBranche+"' where fldKonsulentID = '"+konsulentID+"'");
                    System.out.println("Du har opdateret konsulentens branche!");
                    System.out.println();
                }
                else if (opdaterKonsulent == 5) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast konsulentens ID: ");
                    int konsulentID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast konsulentens nye beskrivelse (max 250 tegn): ");
                    String konsulentBeskrivelse = sc1.nextLine();
                    DB.updateSQL("Update tblKonsulent set fldBeskrivelse = '"+konsulentBeskrivelse+"' where fldKonsulentID = '"+konsulentID+"'");
                    System.out.println("Du har opdateret konsulentens beskrivelse!");
                    System.out.println();
                }
                else if (opdaterKonsulent == 6) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast konsulentens ID: ");
                    int konsulentID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast konsulentens nye login brugernavn: ");
                    String konsulentBrugernavn = sc1.nextLine();
                    DB.updateSQL("Update tblKonsulent set fldBrugernavn = '"+konsulentBrugernavn+"' where fldKonsulentID = '"+konsulentID+"'");
                    System.out.println("Du har opdateret konsulentens brugernavn!");
                    System.out.println();
                }
                else if (opdaterKonsulent == 7) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast konsulentens ID: ");
                    int konsulentID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast konsulentens nye login kodeord: ");
                    String konsulentKodeord = sc1.nextLine();
                    DB.updateSQL("Update tblKonsulent set fldKodeord = '"+konsulentKodeord+"' where fldKonsulentID = '"+konsulentID+"'");
                    System.out.println("Du har opdateret konsulentens kodeord");
                    System.out.println();
                }
                else {
                    System.out.println("Du har indtastet et forkert tal. Prøv igen.");
                }
            } catch (Exception e) {
                System.out.println("Forkert input. Prøv igen.");
            }
        } while (true);
    }


    static void seKonsulentListe() {
        System.out.println("Her er en liste over alle konsulenter: ");
        DB.selectSQL("SELECT fldKonsulentID, fldKonsulentNavn, fldEmail, fldTlf, fldBranche, fldBeskrivelse FROM tblKonsulent");
        DB.showDisplayData();
        System.out.println();
    }

    static void opretProjekt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indtast projektets navn: ");
        String projektNavn = sc.nextLine();
        System.out.println("Indtast projektets beskrivelse (max 250 tegn): ");
        String projektBeskrivelse = sc.nextLine();
        System.out.println("Indtast projektets startdato (YYYY-MM-DD): ");
        String projektStartDato = sc.nextLine();
        System.out.println("Indtast projektets estimerede tid (i uger): ");
        int projektEstimeretTid = sc.nextInt();
        System.out.println("Indtast projektets tilknyttede kundeID: ");
        int projektKundeID = sc.nextInt();

        DB.insertSQL("INSERT INTO tblProjekt (fldProjektNavn, fldBeskrivelse, fldStartDato, fldProjektEstimeretTidUger, fldKundeID) VALUES ('"+projektNavn+"', '"+projektBeskrivelse+"', '"+projektStartDato+"', '"+projektEstimeretTid+"', '"+projektKundeID+"')");
        System.out.println("Konsulent oprettet!");

        System.out.println("Ønsker du at oprette/redigere yderligere eller ønsker du at logge ud? Tast 1 for at redigere yderligere, tast 2 for at logge ud");
        Scanner sc1 = new Scanner(System.in);
        int jaNej = sc1.nextInt();
        if (jaNej == 1) {
            return;
        } else {
            System.out.println("Du er nu logget ud!");
            System.exit(0);
        }
    }


    static void opdaterProjekt() {
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Hvad ønsker du at opdatere? Hav venligst projektID klar.");
                System.out.println(GREEN + "Tast 1"+RESET + " for at opdatere projektets navn");
                System.out.println(GREEN + "Tast 2"+RESET + " for at opdatere projektets beskrivelse (max 250 tegn)");
                System.out.println(GREEN + "Tast 3"+RESET + " for at opdatere projektets startdato (YYYY-MM-DD)");
                System.out.println(GREEN + "Tast 4"+RESET + " for at opdatere projektets estimerede tid (i uger)");
                System.out.println(GREEN + "Tast 5"+RESET + " for at opdatere projektets tilknyttede kundeID");
                System.out.println(GREEN + "Tast 0"+RESET + " for at gå tilbage til hovedmenuen");
                int opdaterProjekt = sc.nextInt();

                if (opdaterProjekt == 0) {
                    break;
                }
                else if (opdaterProjekt == 1) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast projektets ID: ");
                    int projektID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast projektets nye navn: ");
                    String projektNavn = sc1.nextLine();
                    DB.updateSQL("Update tblProjekt set fldProjektNavn = '"+projektNavn+"' where fldProjektID = '"+projektID+"'");
                    System.out.println("Du har opdateret projektets navn!");
                    System.out.println();
                }
                else if (opdaterProjekt == 2) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast projektets ID: ");
                    int projektID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast projektets nye beskrivelse (max 250 tegn): ");
                    String projektBeskrivelse = sc1.nextLine();
                    DB.updateSQL("Update tblProjekt set fldBeskrivelse = '"+projektBeskrivelse+"' where fldProjektID = '"+projektID+"'");
                    System.out.println("Du har opdateret projektets beskrivelse!");
                    System.out.println();
                }
                else if (opdaterProjekt == 3) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast projektets ID: ");
                    int projektID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast projektets nye startdato (YYYY-MM-DD): ");
                    String projektStartdato = sc1.nextLine();
                    DB.updateSQL("Update tblProjekt set fldStartdato = '"+projektStartdato+"' where fldProjektID = '"+projektID+"'");
                    System.out.println("Du har opdateret projektets startdato!");
                    System.out.println();
                }
                else if (opdaterProjekt == 4) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast projektets ID: ");
                    int projektID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast projektets nye estimerede tid (i uger): ");
                    int projektEstimeretTid = sc1.nextInt();
                    DB.updateSQL("Update tblProjekt set fldEstimeretTidUger = '" + projektEstimeretTid + "' where fldProjektID = '" + projektID + "'");
                    System.out.println("Du har opdateret projektets estimerede tid!");
                    System.out.println();
                }
                else if (opdaterProjekt == 5) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Indtast projektets ID: ");
                    int projektID = sc1.nextInt();
                    sc1.nextLine();
                    System.out.println("Indtast projektets nye kundeID: ");
                    int projektKundeID = sc1.nextInt();
                    DB.updateSQL("Update tblProjekt set fldKundeID = '" + projektKundeID + "' where fldProjektID = '" + projektID + "'");
                    System.out.println("Du har opdateret projektets kundeID!");
                    System.out.println();
                }
                else {
                    System.out.println("Du har indtastet et forkert tal. Prøv igen.");
                }
            } catch (Exception e) {
                System.out.println("Forkert input. Prøv igen.");
            }
        } while (true);
    }

    static void seProjektListe() {
        System.out.println("Her er en liste over alle projekter: ");
        DB.selectSQL("SELECT * FROM tblProjekt");
        DB.showDisplayData();
        System.out.println();
    }



}




