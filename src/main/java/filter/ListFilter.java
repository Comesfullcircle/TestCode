package filter;

import java.util.List;
import java.util.stream.Collectors;

public class ListFilter {
    public List<Integer> filterBy(List<Integer> target, int from, int to){
        if (from >= to) {
            throw new RuntimeException(
                    String.format("from=%s 값이 To=%s 보다 더 크거나 같습니다.", from, to)
            );
        }
        return target.stream()
                .filter(each -> from < each && each < to)
                .collect(Collectors.toList());
    }
}
