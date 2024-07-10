package com.padr.gys.infra.outbound.persistence.dashboard.repository;

import java.util.List;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DashboardRepository {

    private final EntityManager entityManager;

    public RentPaymentStatusStatistic.StatisticElement findUnpaidRentPaymentInvoices() {
        String queryString = """
                SELECT DISTINCT COALESCE(SUM(i.amount), 0) as invoiceRevenue, count(i.id) as numberOfInvoice
                FROM invoices i
                WHERE
                    i.type = 'RENT_PAYMENT'
                    AND i.is_published = true
                    AND i.is_deleted = false
                    AND NOT EXISTS (SELECT pd.id from payment_declarations pd where pd.invoice_id = i.id and pd.approvement_status = 'APPROVED' OR pd.approvement_status = 'WAITING')
                    AND NOW() > i.date_of_invoice
                """;

        Query query = entityManager.createNativeQuery(queryString, RentPaymentStatusStatistic.StatisticElement.class);

        return (RentPaymentStatusStatistic.StatisticElement) query.getSingleResult();
    }

    public RentPaymentStatusStatistic.StatisticElement findUpcomingRentPaymentInvoices() {
        String queryString = """
                SELECT DISTINCT COALESCE(SUM(i.amount), 0) as invoiceRevenue, count(i.id) as numberOfInvoice
                FROM invoices i
                WHERE
                    i.type = 'RENT_PAYMENT'
                    AND i.is_published = true
                    AND i.is_deleted = false
                    AND NOT EXISTS (SELECT pd.id from payment_declarations pd where pd.invoice_id = i.id and pd.approvement_status = 'APPROVED')
                    AND (NOW() >= date_trunc('month', i.date_of_invoice) AND NOW() <= i.date_of_invoice)
                """;
        Query query = entityManager.createNativeQuery(queryString, RentPaymentStatusStatistic.StatisticElement.class);

        return (RentPaymentStatusStatistic.StatisticElement) query.getSingleResult();
    }

    public RentPaymentStatusStatistic.StatisticElement findPendingRentPaymentInvoices() {
        String queryString = """
                SELECT DISTINCT
                    COALESCE(SUM(i.amount), 0) as invoiceRevenue,
                    count(i.id) as numberOfInvoice
                from
                    invoices i
                inner join payment_declarations pd on pd.invoice_id = i.id
                where
                    i.type = 'RENT_PAYMENT'
                    AND i.is_published = true
                    AND i.is_deleted = false
                    and pd.approvement_status = 'WAITING'
                        """;

        Query query = entityManager.createNativeQuery(queryString, RentPaymentStatusStatistic.StatisticElement.class);

        return (RentPaymentStatusStatistic.StatisticElement) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<RentalIncomeStatistic.MonthlyStatisticElement> calculateRentRevenueByMonths() {
        String queryString = """
                SELECT
                    cast(date_part('month', pd.last_modified_date) as text) as "month",
                    COALESCE(SUM(i.amount), 0) as revenue
                FROM
                    invoices i
                INNER JOIN payment_declarations pd on
                    pd.invoice_id = i.id
                WHERE
                    i.type = 'RENT_PAYMENT'
                    AND i.is_deleted = false
                    AND pd.approvement_status = 'APPROVED'
                    AND date_part('year', pd.last_modified_date) = date_part('year', NOW())
                GROUP BY date_part('month', pd.last_modified_date)
                        """;

        Query query = entityManager.createNativeQuery(queryString, RentalIncomeStatistic.MonthlyStatisticElement.class);

        return (List<RentalIncomeStatistic.MonthlyStatisticElement>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<RealEstateDistributionByCategoriesStatistic.StatisticElement> findRealEstateDistrbutionByCategoryStatistics() {
        String queryString = """
                SELECT
                    c.name as categoryName,
                    count(re.id) as numberOfRealEstate
                FROM
                    real_estates re
                INNER JOIN categories c on re.category_id = c.id
                WHERE
                    re.is_deleted = false
                GROUP BY re.category_id, c.name
                                """;

        Query query = entityManager.createNativeQuery(queryString,
                RealEstateDistributionByCategoriesStatistic.StatisticElement.class);

        return (List<RealEstateDistributionByCategoriesStatistic.StatisticElement>) query.getResultList();
    }

    public OccupancyStatistic findOccupancyStatistic() {
        String occupancyCountQueryString = """
                SELECT
                	count(re.id)
                FROM
                	real_estates re
                WHERE
                	re.is_deleted = false
                	AND EXISTS (
                	SELECT
                		rc.id
                	FROM
                		rental_contracts rc
                	WHERE
                		rc.real_estate_id = re.id
                		AND rc.is_published = true
                		AND rc.is_deleted = false)\s
                """;

        Query occupancyCountQuery = entityManager.createNativeQuery(occupancyCountQueryString, Long.class);

        Long occupancyCount = (Long) occupancyCountQuery.getSingleResult();

        String realEstateCountQueryString = """
                SELECT
                	count(re.id)
                FROM
                	real_estates re
                WHERE
                	re.is_deleted = false
                """;

        Query realEstateCountQuery = entityManager.createNativeQuery(realEstateCountQueryString, Long.class);

        Long realEstateCount = (Long) realEstateCountQuery.getSingleResult();

        return OccupancyStatistic.builder()
                .occupancyCount(occupancyCount)
                .realEstateCount(realEstateCount)
                .build();
    }
}
