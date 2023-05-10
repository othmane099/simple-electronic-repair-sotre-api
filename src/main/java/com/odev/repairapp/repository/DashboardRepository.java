package com.odev.repairapp.repository;

import com.odev.repairapp.model.RepairOrder;
import com.odev.repairapp.model.RepairPriority;
import com.odev.repairapp.model.RepairStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DashboardRepository {
    private final EntityManager em;

    public List<RepairOrder> findAllByCriteria(
            Boolean paymentStatus, RepairStatus status, RepairPriority priority)
    {
        System.out.println(paymentStatus);
        System.out.println(status);
        System.out.println(priority);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RepairOrder> cq = cb.createQuery(RepairOrder.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<RepairOrder> root = cq.from(RepairOrder.class);

        if (paymentStatus != null){
            Predicate paymentPredicate = cb.equal(root.get("paymentStatus"), paymentStatus);
            predicates.add(paymentPredicate);
        }

        if (status != null){
            Join<RepairOrder, RepairStatus> ordersStatus = root.join("repairStatus");
            Predicate statusPredicate = cb.equal(ordersStatus.get("name"), status.getName());
            predicates.add(statusPredicate);
        }

        if (priority != null){
            Join<RepairOrder, RepairStatus> ordersPriority = root.join("repairPriority");
            Predicate priorityPredicate = cb.equal(ordersPriority.get("name"), priority.getName());
            predicates.add(priorityPredicate);
        }

        if (predicates.isEmpty())
            cq.select(root);
        else
            cq.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

        TypedQuery<RepairOrder> query = em.createQuery(cq);
        return query.getResultList();
    }

}
