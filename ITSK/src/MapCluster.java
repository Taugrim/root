import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(css = ".map__cluster")
class MapCluster extends HtmlElement {
   public int getValue(){
       return Integer.parseInt(this.getText());
   }

    public MapCluster() {
        super();
    }
}