package root.service;

import root.model.dto.BankBookDto;

import java.util.List;

public interface BankBookService {
    public List<BankBookDto> findAllBankBookDtoOfUser(String userEmail) throws Exception;
}
