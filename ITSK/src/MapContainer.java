import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

class MapContainer extends HtmlElement {
    @FindBy(css = ".map__cluster")
    List<MapCluster> mapClusters;
    @FindBy(css = ".ymaps-2-1-68-image")
    List<WebElement> sign;

    public MapContainer() {
        super();
    }
    public int getQvantity(){
        return (mapClusters.size()+sign.size());
    }
    public int getValue() {
        int summ = 0;
        for (MapCluster q : mapClusters)
            summ = summ + q.getValue();
        return summ+sign.size();
    }

}