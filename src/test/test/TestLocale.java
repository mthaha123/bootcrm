import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestLocale {

    @Test
    //本地化对象locale
    public void testLocale() {
        Locale locale = Locale.getDefault();
        System.out.println(locale.getCountry());
        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getLanguage());
    }

    //国际化
    @Test
    public void testI18N() {
        Locale locale = Locale.CHINA;
        ResourceBundle bundle = ResourceBundle.getBundle("msg",locale);
        System.out.println(bundle.getString("title"));
        System.out.println(bundle.getString("username"));
        System.out.println(bundle.getString("pwd"));
    }
}
