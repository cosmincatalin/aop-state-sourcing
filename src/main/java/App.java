import com.configurations.AppConfig;
import com.models.AddressModel;
import com.models.CompanyModel;
import com.services.CompanyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CompanyService companyService = context.getBean(CompanyService.class);

        Long start = System.currentTimeMillis();

        for (int i = 1; i <= 10000; i++ ) {
            CompanyModel company = new CompanyModel();
            company.setName("VaultTec Corp");
            company.setId(i);

            AddressModel address1 = new AddressModel();
            address1.setCity("Boston");
            address1.setCountry("US");
            address1.setStreet("Downtown Lane 8");

            AddressModel address2 = new AddressModel();
            address2.setCity("Kuala Lumpur");
            address2.setCountry("Malaysia");
            address2.setStreet("Penong Men 18");

            company.addAddress(address1);
            company.addAddress(address2);

            companyService.addCompany(company);
        }

        Long finish = System.currentTimeMillis();

        System.out.println("Duration: " + String.format("%2.02f", (float)( finish - start ) / 60000) + " minutes");

    }
}
