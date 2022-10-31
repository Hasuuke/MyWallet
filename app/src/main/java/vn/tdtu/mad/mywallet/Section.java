package vn.tdtu.mad.mywallet;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String sectionName;
    private ArrayList<Transaction> sectionsItems;

    public Section(String sectionName, ArrayList<Transaction> sectionsItems){
        this.sectionName = sectionName;
        this.sectionsItems = sectionsItems;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public ArrayList<Transaction> getSectionsItems() {
        return sectionsItems;
    }

    public void addSectionsItems(ArrayList<Transaction> transaction) {
        this.sectionsItems= transaction;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", sectionsItems=" + sectionsItems +
                '}';
    }


}
