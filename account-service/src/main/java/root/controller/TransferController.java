package root.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import root.model.dto.TransactionDto;
import root.service.TransferService;
import root.validation.EntityCreated;
import java.math.BigDecimal;

@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    @PostMapping(value = "/transfer", produces = "application/json", consumes = "application/x-www-form-urlencoded")
    public TransactionDto transfer(@RequestBody String requestParameter) throws Exception {
        String transferDetails = requestParameter.substring(5).replaceAll("%2C", " ");

        String sourceBankBookId = transferDetails.substring(0, transferDetails.indexOf(" "));
        String targetBankBookId = transferDetails.substring(transferDetails.indexOf(" ") + 1, transferDetails.lastIndexOf(" "));
        Double amount = Double.valueOf(transferDetails.substring(transferDetails.lastIndexOf(" ")));

        return transferService.transfer(new TransactionDto(null, sourceBankBookId, targetBankBookId, new BigDecimal(amount), null, null, null));
    }
}
