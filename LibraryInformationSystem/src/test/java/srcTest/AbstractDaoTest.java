package srcTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import Interfaces.BookDAO;
import Interfaces.CustomerDAO;
import Interfaces.LoanDAO;
import src.PersistenceApplicationContext;

/**
 *
 * @author Matúš Čongrády
 *
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AbstractDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected BookDAO bookDAO;

    @Autowired
    protected CustomerDAO customerDAO;

    @Autowired
    protected LoanDAO loanDao;
   
}