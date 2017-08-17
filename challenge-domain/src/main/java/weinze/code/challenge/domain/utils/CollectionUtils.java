package weinze.code.challenge.domain.utils;

import static java.util.Objects.nonNull;

import java.util.Collection;
import java.util.stream.Stream;

public class CollectionUtils {

    public static <T> Stream<T> stream(Collection<T> collection) {
        return nonNull(collection) ? collection.stream() : Stream.empty();
    }
}
