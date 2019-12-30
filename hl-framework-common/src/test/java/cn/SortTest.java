package cn;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {

    @Test
    public void testDuration() {
        Integer[] a = {5,2,3,7,5,6};
        List<Integer> list = Arrays.asList(a);
        list.sort((item1, item2) ->
                item1 - item2
        );
        System.out.println(list);
    }

}
