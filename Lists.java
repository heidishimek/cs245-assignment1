import java.util.*;

public interface Lists<E>
{
	E get(int i);
	boolean add(E num);
	E remove(int i);
	int size();
}