import java.util.*;

public class LinkedList<E> implements Lists<E>
{
	Node<E> head;
	int size;

	private class Node<E>
	{
		E data;
		Node<E> next;

		public Node(E val)
		{
			data = val;
			next = null;
		}
	}

	/* CONSTRUCTOR */
	public LinkedList()
	{
		head = null;
		size = 0;
	}

	/* REMOVE FUNCTION */
	public E remove(int i)
	{
		if (i < 0 && i >= size)
		{
			System.out.println("Error.");
		}
		if (i == 0)
		{
			Node<E> node = head;
			head = head.next;
			size--;
			return node.data;
		}
		else
		{
			Node<E> prev = head;
			for (int j = 0; j < i - 1; j++)
			{
				prev = prev.next;
			}
			Node<E> node = prev.next;
			prev.next = node.next;
			size--;
			return node.data;
		}
	}

	/* GET FUNCTION */
	public E get(int i)
	{
		if (i < 0 || i >= size)
		{
			return null;
		}
		Node<E> node = head;
		for (int j = 0; j < i; j++)
		{
			node = node.next;
		}
		return node.data;
	}

	/* RETURNS SIZE */
	public int size()
	{
		return size;
	}

	/* ADD FUNCTION */
	public boolean add(E val)
	{
		Node<E> node = new Node(val);
		if (head == null)
		{
			head = new Node(val);
			++size;
		}
		else
		{
			Node<E> previous = head;
			while (previous.next != null)
			{
				previous = previous.next;
			}
			previous.next = node;
			++size;
		}
		return true;
	}
}