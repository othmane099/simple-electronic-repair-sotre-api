package com.odev.repairapp.utils;

import com.odev.repairapp.model.RepairOrder;
import com.odev.repairapp.model.RepairStatus;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CriteriaFilter<T> {


//    public Specification<RepairOrder> phoneContains(String phone) {
//        return (root, cq, cb) -> cb.like(root.get("phone"), "%" + phone + "%");
//    }
//
//    public Specification<RepairOrder> nameContains(String name) {
//        return (root, cq, cb) -> cb.like(root.get("name"), "%" + name + "%");
//    }
//
//    public Specification<RepairOrder> trackingContains(String tracking) {
//        return (root, cq, cb) -> cb.like(root.get("tracking"), "%" + tracking + "%");
//    }



    public Specification<T> contains(String rootName, String keyword) {
        return (root, cq, cb) -> cb.like(root.get(rootName), "%" + keyword + "%");
    }


    public Specification<RepairOrder> hasStatus(String joinName, String keyword) {
        return (root, query, criteriaBuilder) -> {
            Join<RepairOrder, RepairStatus> ordersStatus = root.join(joinName);
            return criteriaBuilder.like(ordersStatus.get("name"), "%" + keyword + "%");
        };
    }
}
