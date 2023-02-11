package types;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ContainerC<T, V> {
    UUID uuid;
    List<Map.Entry<T, V>> combinations;

    private ContainerC() {
    }

    public ContainerC(UUID uuid, List<Map.Entry<T, V>> combinations) {
        this.uuid = uuid;
        this.combinations = combinations;
    }

    @Override
    public String toString() {
        return "{\"ContainerC\" : {" +
                "\"uuid\" : \"" + uuid +
                "\", \"combinations\" : {" + combinations.stream().map(q->"\""+q.getKey()+"\" : \""+q.getValue()+"\"").collect(Collectors.joining(",")) +
                "}}}";
    }
}
