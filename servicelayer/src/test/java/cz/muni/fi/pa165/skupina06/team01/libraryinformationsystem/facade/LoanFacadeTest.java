package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoanDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.LoanService;

public class LoanFacadeTest extends AbstractFacadeTest {
    @Mock
    private LoanService loanService;

    @InjectMocks
    @Autowired
    private LoanFacadeImpl loanFacade;

    private Loan loan;
    private LoanDTO loanDTO;
    private List<Loan> loans;

    private Long loanId = 1L;

    private Customer cust1;
    private CustomerDTO custDTO1;
    private Customer cust2;
    private CustomerDTO custDTO2;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        loanFacade = (LoanFacadeImpl) unwrapProxy(loanFacade);
        ReflectionTestUtils.setField(loanFacade, "loanService", loanService);
        ReflectionTestUtils.setField(loanFacade, "beanMappingService", beanMappingService);

        cust1 = new Customer();
        cust1.setLogin("johan1");
        cust1.setName("Johan");
        cust1.setSurname("Krivý");
        cust1.setPassword("pass");

        custDTO1 = new CustomerDTO();
        custDTO1.setLogin("johan1");
        custDTO1.setName("Johan");
        custDTO1.setSurname("Krivý");
        custDTO1.setPassword("pass");

        cust2 = new Customer();
        cust2.setLogin("john2");
        cust2.setName("Johny");
        cust2.setSurname("Riko");
        cust2.setPassword("word");

        custDTO2 = new CustomerDTO();
        custDTO2.setLogin("john2");
        custDTO2.setName("Johny");
        custDTO2.setSurname("Riko");
        custDTO2.setPassword("word");

        loan = new Loan(cust1);

        loanDTO = new LoanDTO();
        loanDTO.setCustomer(custDTO1);
        loanDTO.setTimestamp(loan.getTimestamp());
    }

    @Test
    public void testCreateLoan(){
        when(beanMappingService.mapTo(loanDTO, Loan.class)).thenReturn(loan);
        loanFacade.create(loanDTO);

        verify(loanService).create(loan);
        verify(beanMappingService).mapTo(loanDTO, Loan.class);
    }

    @Test
    public void testUpdateLoan() {
        when(beanMappingService.mapTo(loanDTO, Loan.class)).thenReturn(loan);
        loanFacade.create(loanDTO);

        //verify(sustomerService).registerCustomer(customer, "pass");
        verify(beanMappingService).mapTo(loanDTO, Loan.class);

        loanDTO.setCustomer(custDTO2);

        loanFacade.update(loanDTO);
        verify(loanService).update(loan);
        verify(beanMappingService, times(2)).mapTo(loanDTO, Loan.class);
    }

    @Test
    public void testFindAll() {
        when(loanService.findAll()).thenReturn(Collections.singletonList(loan));
        when(beanMappingService.mapTo(Collections.singletonList(loan), LoanDTO.class)).thenReturn(Collections.singletonList(loanDTO));

        Collection<LoanDTO> loans = loanFacade.findAll();

        assertThat(loan.getTimestamp()).isEqualTo(((List<LoanDTO>) loans).get(0).getTimestamp());
        verify(beanMappingService).mapTo(Collections.singletonList(loan), LoanDTO.class);
        verify(loanService).findAll();
    }

    @Test
    public void testFindAllWithNull() {
        when(loanService.findAll()).thenReturn(null);

        Collection<LoanDTO> loans = loanFacade.findAll();

        assertThat(loans).isNull();
        verify(loanService).findAll();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(loanService.findById(loanId)).thenReturn(loan);
        when(beanMappingService.mapTo(loan, LoanDTO.class)).thenReturn(loanDTO);

        LoanDTO loanDTO = loanFacade.findById(loanId);

        assertThat(loanDTO).isNotNull();
        assertThat(loan.getTimestamp()).isEqualTo(loanDTO.getTimestamp());
        verify(loanService).findById(loanId);
        verify(beanMappingService).mapTo(loan, LoanDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(loanService.findById(loanId)).thenReturn(null);

        LoanDTO loanDTO = loanFacade.findById(loanId);

        assertThat(loanDTO).isNull();
        verify(loanService).findById(loanId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }
}
