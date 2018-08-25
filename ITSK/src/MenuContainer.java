import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@FindBy(css = ".menu-mobile__list")
class MenuContainer extends HtmlElement {
    public MenuContainer() {
        super();

    }
        @FindBy(css = ".menu-mobile__list>a")
    List<HtmlElement> menuList;

    public HtmlElement getItem(final String item) {
        return menuList.stream().filter(q -> q.getText().equalsIgnoreCase(item)).findFirst().get();
    }
}