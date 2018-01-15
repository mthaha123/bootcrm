import com.bootcrm.model.Customer;
import com.bootcrm.service.imp.CustomerServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringAndMybatis {
    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"config/applicationContext.xml"});
        CustomerServiceImpl customerServiceImp = (CustomerServiceImpl) ac.getBean("customerServiceImpl");
        customerServiceImp.insert(new Customer("马云", null, null, "6", "2", "22", "马化腾",
                "0108888887",
                "13888888888", "123456", "北京三里桥"));
    }

}
