package vn.tdtu.mad.mywallet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class SectionDayComperator implements Comparator<SectionDay> {
    @Override
    public int compare(SectionDay sec1, SectionDay sec2) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        try {
            date1 = sdf.parse(sec1.getDayName());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date date2 = null;
        try {
            date2 = sdf.parse(sec2.getDayName());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int result = date1.compareTo(date2);
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
