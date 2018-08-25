import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

class AzsFiltrContainer extends HtmlElement {
    public AzsFiltrContainer() {
        super();
    }

    @FindBy(xpath = "//div[@class='azs-filter__contain']//label")
    List<AzsItem> menuList;

    public AzsItem getItem(final String item) throws Exception {

        for(int w=0;w<menuList.size();w++){
            if(menuList.get(w).getText().equalsIgnoreCase(item)){

                return menuList.get(w);
            }
     } throw new Exception();
    }
}