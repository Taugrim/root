package types;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ContainerPWS<T, V> {
    UUID uuid;
    Map<T, V>  combinations;

    private ContainerPWS() {
    }

    public ContainerPWS(UUID uuid, Map<T, V> combinations) {
        this.uuid = uuid;
        this.combinations = combinations;
    }
    @Override
    public String toString() {
        return "{\"ContainerPWS\" : {" +
                "\"uuid\" : \"" + uuid +
                "\", \"combinations\" : {" + combinations.entrySet().stream().map(q->"\""+q.getKey()+"\" : \""+q.getValue()+"\"").collect(Collectors.joining(",")) +
                "}}}";
    }
}

