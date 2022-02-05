package root.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import root.model.UserInfo;
import root.model.dto.BankBookDto;
import root.model.dto.TransactionDto;
import root.security.UserInfoFilter;
import root.service.BankBookService;
import root.validation.EntityCreated;
import root.validation.EntityUpdated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class BankBookController {
    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService){
        this.bankBookService = bankBookService;
    }

    @PostMapping(value = "/bank-book", produces = "application/json", consumes = "application/x-www-form-urlencoded")
    public List <BankBookDto> getUserAccounts() throws Exception {

        return bankBookService.findAllBankBookDtoOfUser(UserInfoFilter.userInfo.getEmail());
    }

}
