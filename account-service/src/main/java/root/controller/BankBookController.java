package root.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import root.model.dto.BankBookDto;
import root.model.dto.Response;
import root.security.UserInfoFilter;
import root.service.BankBookService;

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
