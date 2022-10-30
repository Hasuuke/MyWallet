package vn.tdtu.mad.mywallet;

import java.util.List;

public class Section {
    private String sectionName;
    private List<String> sectionsItems;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<String> getSectionsItems() {
        return sectionsItems;
    }

    public void setSectionsItems(List<String> sectionsItems) {
        this.sectionsItems = sectionsItems;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", sectionsItems=" + sectionsItems +
                '}';
    }

    public Section(String sectionName, List<String> sectionsItems){
        this.sectionName = sectionName;
        this.sectionsItems = sectionsItems;
    }


}
