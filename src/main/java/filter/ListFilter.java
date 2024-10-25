package filter;

import java.util.List;
import java.util.stream.Collectors;

public class ListFilter {
    public List<Integer> filterBy(List<Integer> target, int from, int to){
        return target.stream()
                .filter(each -> from < each && each < to)
                .collect(Collectors.toList());
    }
}
