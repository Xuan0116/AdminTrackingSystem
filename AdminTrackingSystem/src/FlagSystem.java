import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class FlagSystem {
    public static void flagCase() {

        for(int i = 0; i < Case.caseArrayList.size(); i++){
            for(int j = 0; j < Shop.shops.size(); j++){
                if(Shop.shops.get(j).getId() == Case.caseArrayList.get(i).getShopID())
                    Shop.shops.get(j).setStatus("Case");
                for(int k = 0; k < Customer.custs.size(); k++){
                    if(Customer.custs.get(k).getId() == Case.caseArrayList.get(i).getCustID()){
                        Customer.custs.get(k).setStatus("Case");
                    }
                }
            }
        }
    }

    public static void flagClosed(){

        for(int i = 0; i < Case.caseArrayList.size(); i++){
            for(int j = 0; j < Visit.visits.size(); j++){
                //if shop true > then compared time, if time yes then add into closed list.
                if(Visit.visits.get(j).getShopID() == Case.caseArrayList.get(i).getShopID()){

                    int visitCustID = Visit.visits.get(j).getCustID();
                    int visitShopID = Visit.visits.get(j).getShopID();
                    LocalDateTime VisitDT = Visit.visits.get(j).getDt();
                    LocalDateTime CaseDT = Case.caseArrayList.get(i).getDt();
                    LocalDateTime BfCaseDT = CaseDT.minusHours(1);
                    LocalDateTime AfCaseDT = CaseDT.plusHours(1);

                    if((VisitDT.isBefore(CaseDT) && VisitDT.isAfter(BfCaseDT))
                            || (VisitDT.isAfter(CaseDT) && VisitDT.isBefore(AfCaseDT)))
                        new Closed(VisitDT,visitCustID,visitShopID);
                }
            }
        }

        for(int i = 0; i < Closed.ClosedArrayList.size(); i++)
            for(int j = 0; j < Customer.custs.size(); j++){
                if(Customer.custs.get(j).getId() == Closed.ClosedArrayList.get(i).getCustID()){
                    if(Customer.custs.get(j).getStatus() != "Case")
                        Customer.custs.get(j).setStatus("Closed");
                }
            }
    }

    public static void customFlag(int mode){
        ArrayList<Integer> templist = new ArrayList<Integer>();

        VisitReport visitReport = new VisitReport();
        visitReport.display();

        while(true){
            System.out.println("Choose from visit history to be flagged:");
            System.out.println("(Please input one by one && input -1 to stop)");
            int choice = InputValid.checkRange(-1,Visit.visits.size());
            if(choice == -1)
                break;
            else
                templist.add(choice-1);
        }

        for(int i = 0; i < templist.size();i++){
            if(mode == 1)
                new Case(Visit.visits.get(templist.get(i)).getDt(),
                        Visit.visits.get(templist.get(i)).getCustID(), Visit.visits.get(templist.get(i)).getShopID());

            else
                new Closed(Visit.visits.get(templist.get(i)).getDt(),
                        Visit.visits.get(templist.get(i)).getCustID(), Visit.visits.get(templist.get(i)).getShopID());
        }

        flagClosed();
        flagCase();

        System.out.println("Flag successfully.\n");

    }

    public static void customFlagNormal(int role,int mode){
        /** role 1 = customer // role 2 = shop */
        /** mode 1 = case to normal // mode 2 = closed to normal */

        if(role == 1 && mode == 1)
            customFlagCaseNormalCust();
        else if (role == 1 && mode == 2)
            customFlagClosedNormalCust();
        else if (role == 2 && mode == 1)
            customFlagCaseNormalShop();
        else
            customFlagClosedNormalShop();

        System.out.println("Flag Successfully.\n");

    }
    public static void customFlagCaseNormalCust(){
        int choice;

        CustReport custReport = new CustReport();
        custReport.display();
        System.out.print("Please enter No. to be flag Normal: ");
        choice = InputValid.checkRange(1,Customer.custs.size());

        for(int i = 0; i < Case.caseArrayList.size(); i++){
            if(Case.caseArrayList.get(i).getCustID() == choice){
                /**Remove from case pool */
                Case.caseArrayList.remove(i);
                /** Mark the Customer status to normal */
                Customer.custs.get(choice-1).setStatus("Normal");
            }
        }
    }

    public static void customFlagCaseNormalShop(){
        int choice;

        ShopReport shopReport = new ShopReport();
        shopReport.display();
        System.out.print("Please enter No. to be flag Normal: ");
        choice = InputValid.checkRange(1,Shop.shops.size());

        for(int i = 0; i < Case.caseArrayList.size(); i++){
            if(Case.caseArrayList.get(i).getShopID() == choice){
                /**Remove from case pool */
                Case.caseArrayList.remove(i);
                /** Mark the Customer status to normal */
                Shop.shops.get(choice-1).setStatus("Normal");
            }
        }
    }

    public static void customFlagClosedNormalCust(){
        int choice;

        CustReport custReport = new CustReport();
        custReport.display();
        System.out.print("Please enter No. to be flag Normal: ");
        choice = InputValid.checkRange(1,Customer.custs.size());

        for(int i = 0; i < Closed.ClosedArrayList.size(); i++){
            if(Closed.ClosedArrayList.get(i).getCustID() == choice){
                /**Remove from Closed pool */
                Closed.ClosedArrayList.remove(i);
                /** Mark the Customer status to normal */
                Customer.custs.get(choice-1).setStatus("Normal");
            }
        }
    }

    public static void customFlagClosedNormalShop(){
        int choice;

        ShopReport shopReport = new ShopReport();
        shopReport.display();
        System.out.print("Please enter No. to be flag Normal: ");
        choice = InputValid.checkRange(1,Shop.shops.size());

        for(int i = 0; i < Closed.ClosedArrayList.size(); i++){
            if(Closed.ClosedArrayList.get(i).getCustID() == choice){
                /**Remove from Closed pool */
                Closed.ClosedArrayList.remove(i);
                /** Mark the Customer status to normal */
                Shop.shops.get(choice-1).setStatus("Normal");
            }
        }
    }
}
