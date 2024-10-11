package org.example.aadphase02assignment.customStatusCodes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.dto.CustomerStatus;
import org.example.aadphase02assignment.dto.ItemStatus;
import org.example.aadphase02assignment.dto.OrderStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedItemCustomerOrderErrorStatusCodes implements OrderStatus, CustomerStatus, ItemStatus {
    private int statusCode;
    private String statusMessage;
}
