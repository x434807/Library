package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.data;


import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.BookService;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.CustomerService;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.LoanService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author Martin Piatka
 *
 */

@Component
@Transactional
public class InitializerImpl implements Initializer{

    @Inject
    CustomerService customerService;

    @Inject
    LoanService loanService;

    @Inject
    BookService bookService;

    @Override
    public void loadData() {

    }
}
