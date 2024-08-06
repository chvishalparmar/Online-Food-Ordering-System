package com.food.ordersystem.serviceImp;

import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.BillDto;
import com.food.ordersystem.dto.OrderDto;
import com.food.ordersystem.enitites.Bill;
import com.food.ordersystem.enitites.Orders;
import com.food.ordersystem.enums.DeliveryStatus;
import com.food.ordersystem.exceptions.BillExceptionHandler;
import com.food.ordersystem.repo.BillRepo;
import com.food.ordersystem.services.BillService;
import com.food.ordersystem.services.CURDOrderService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BillServiceImp implements BillService{


    private final CURDOrderService curdOrderService;

    private final BillRepo billRepo;
    @Override
    public BillDto getBill(OrderDto orderDto) {

       Orders order  = curdOrderService.getOrder(orderDto);

       if(checkOrderStatus(order)){
       Bill bill = new Bill();
       bill.setUser(order.getUser());
       bill.setOrders(order);
       Bill genratedBill =  billRepo.save(bill);

       BillDto dto = new BillDto(genratedBill.getBillId() , genratedBill.getUser().getName(), genratedBill.getOrders());

       return dto;
       }

       return null;

    }

    private boolean checkOrderStatus(Orders order){

        if(order.getDeliveryStatus() == DeliveryStatus.SHIPPED){
            return true;
        }else{
            throw new BillExceptionHandler("Bill Can be Genrated for SHIPPED order ! your Order Status is " + order.getDeliveryStatus() );
        }
    }
    
}
