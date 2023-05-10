package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.RepairOrder;
import com.odev.repairapp.model.RepairPriority;
import com.odev.repairapp.model.RepairStatus;
import com.odev.repairapp.repository.*;
import com.odev.repairapp.request.DashboardRequest;
import com.odev.repairapp.response.DashboardResponse;
import com.odev.repairapp.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository repository;
    private final RepairStatusRepository repairStatusRepository;
    private final RepairPriorityRepository repairPriorityRepository;
    private final DeviceRepository deviceRepository;
    private final DefectRepository defectRepository;
    private final BrandRepository brandRepository;

    public DashboardResponse getStats(DashboardRequest request){
        Boolean paymentStatus = request.paymentStatus();
        RepairStatus repairStatus = null;
        RepairPriority repairPriority = null;

        if (request.statusId() != null)
            repairStatus = repairStatusRepository.findById(request.statusId()).orElseThrow(() -> new RepairAppException("Repair status not found", ErrorCode.REPAIR_STATUS_NOT_FOUND));

        if (request.priorityId() != null)
            repairPriority = repairPriorityRepository.findById(request.priorityId()).orElseThrow(() -> new RepairAppException("Repair priority not found", ErrorCode.REPAIR_PRIORITY_NOT_FOUND));

        List<RepairOrder> orders = repository.findAllByCriteria(paymentStatus, repairStatus, repairPriority);

        long orderCount = orders.size();
        double amount = orders.stream().filter(o -> o.getTotalCharge() > 0).mapToDouble(RepairOrder::getTotalCharge).sum();
        double cost = orders.stream().filter(o -> o.getTotalCost() > 0).mapToDouble(RepairOrder::getTotalCost).sum();
        double profit = amount - cost;

        long brandCount = brandRepository.count();
        long deviceCount = deviceRepository.count();
        long defectCount = defectRepository.count();

        return new DashboardResponse(amount, cost, profit, orderCount, brandCount, defectCount, deviceCount );


    }
}
