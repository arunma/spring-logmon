package sg.edu.nus.pe.maid.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaidUsageBill {
    private Integer id;
    private Double amount;
    private String description;
}