package patterns.iterators;

import java.util.List;

public class GenericIterator<T>  implements UserIteratorInterface<T> {

    private final List<T> list;
    private int position = 0;

    public GenericIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return position < list.size();
    }

    @Override
    public T next() {
        return list.get(position++);
    }
}
