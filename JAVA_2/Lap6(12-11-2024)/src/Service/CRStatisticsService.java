package Service;

import Entity.CRStatistics;
import Entity.StatisticsView;
import IGeneric.IGeneral;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CRStatisticsService implements IGeneral<CRStatistics> {
    private List<StatisticsView> statisticsViews;

    public CRStatisticsService(List<StatisticsView> statisticsViews) {
        this.statisticsViews = statisticsViews;
    }
    @Override
    public Map<CRStatistics, CRStatistics> dataCRS() {
        Map<CRStatistics, CRStatistics> dataCRS = statisticsViews.stream().collect(Collectors.groupingBy((cr) -> {
            return new CRStatistics(cr.getProductId(), cr.getMonthOfDate(), cr.getYearOfDate());
        }, Collectors.collectingAndThen(Collectors.toList(), (listCR) -> {
            CRStatistics cr = new CRStatistics();
            StatisticsView first = (StatisticsView)listCR.getFirst();
            cr.setId(first.getProductId());
            cr.setMonth(first.getMonthOfDate());
            cr.setYear(first.getYearOfDate());
            int totalVIew = listCR.stream().mapToInt(StatisticsView::getView).sum();
            cr.setTotalView(totalVIew);
            cr.setTotalAddTo(listCR.stream().mapToInt(StatisticsView::getNumberOfAddToCard).sum());
            cr.setTotalCheckOut(listCR.stream().mapToInt(StatisticsView::getNumberOfCheckout).sum());
            return cr;
        })));
        return dataCRS;
    }
}
