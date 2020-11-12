import java.util.*;

public class ArrayList<E> implements Lists<E>
{
	E[] arr;
	int size;

	/* CONSTRUCTOR */
	public ArrayList()
	{
		arr = (E[]) new Object[15];
		size = 0;
	}

	/* GET FUNCTION */
	public E get(int i)
	{
		if (i < 0 || i >= arr.length)
		{
			System.out.println("Invalid.");
		}
		return arr[i];
	}

	/* ADD FUNCTION */
	public boolean add(E val)
	{
		if (size == arr.length)
		{
			growArray();
		}
		arr[size] = val;
		size++;
		return true;
	}

	/* ADDS LENGTH TO ARRAY */
	public void growArray()
	{
		E[] newArr = (E[]) new Object[arr.length * 2];
		for (int i = 0; i < arr.length; i++)
		{
			newArr[i] = arr[i];
		}
		arr = newArr;
	}

	/* REMOVE FUNCTION */
	public E remove(int i)
	{
		if (i < 0 || i >= arr.length)
		{
			System.out.println("Error.");
		}
		E val = arr[i];
		--size;
		return val;
	}

	/* RETURNS SIZE */
	public int size()
	{
		return size;
	}
}