class Encrypt{
    public static void main(String[] args) throws Exception {

        passQL pass = new passQL();

        if(pass.AddUser("hitler", "the_jew", "manager") != true)
        {
            //enter new password
            System.out.println("Error in user creation, user already exist.");
        }

        String[] check0 = pass.CheckPass("jimmy","badname");
        String[] check1 = pass.CheckPass("jimmy","goodname");

        String[] check2 = pass.CheckPass("hitler","badname");
        String[] check3 = pass.CheckPass("hitler","the_jew");

        System.out.println(" ");
        System.out.println("Jimmy");
        System.out.println("Correct : " + check0[0] + " " + check0[1]);
        System.out.println("Wrong : " + check1[0] + " " + check1[1]);
        System.out.println(" ");
        System.out.println("Hitler");
        System.out.println("Correct : " + check3[0] + " " + check3[1]);
        System.out.println("Wrong : " + check2[0] + " " + check2[1]);
    }
}