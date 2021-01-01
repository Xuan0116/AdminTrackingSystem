import java.util.HashMap;

public class VisitReport extends Report {
    private DataContainer VisitContainer = new DataContainer();

    public VisitReport(){
        super("All Visit History");

        VisitContainer.metadata.add("No");
        VisitContainer.metadata.add("Date Time");
        VisitContainer.metadata.add("CustID");
        VisitContainer.metadata.add("ShopID");

        int count = -1;
        for (Visit v : Visit.visits) {
            VisitContainer.data.add(new HashMap<String, String>());
            VisitContainer.data.get(++count).put("No", Integer.toString(count + 1));
            VisitContainer.data.get(count).put("Date Time", Visit.visits.get(count).getDate().
                    concat(Visit.visits.get(count).getTime()));
            VisitContainer.data.get(count).put("CustID",String.valueOf(Visit.visits.get(count).getCustID()));
            VisitContainer.data.get(count).put("ShopID", String.valueOf(Visit.visits.get(count).getShopID()));
        }
    }

    public void exportCSV() {
        CSV.export(super.getTitle(), VisitContainer);
    }

    public void display() {
        Table.display(super.getTitle(), VisitContainer);
    }



}
