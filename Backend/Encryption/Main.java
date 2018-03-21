class Encrypt{
    public static void main(String[] args) throws Exception {

        passQL pass = new passQL();

        //pass.AddUser("jimmy", "badname", "user");

        String[] check0 = pass.CheckPass("jimmy","badname");
        String[] check1 = pass.CheckPass("jimmy","goodname");

        System.out.println(" ");
        System.out.println("Correct : " + check0[0] + " " + check0[1]);
        System.out.println("Wrong : " + check1[0] + " " + check1[1]);
    }
}