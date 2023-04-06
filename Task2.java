import java.util.*;


class Node<E>
{
	protected E data;
	protected Node<E> next;
	protected Node<E> prev;
	protected boolean isUsed;

	public Node()
	{
		data = null;
		next = null;
		prev = null;
		isUsed = false;
	}

	public Node(E data, Node<E> prev, Node<E> next, boolean isUsed)
	{
		this.data = data;
		this.prev = prev;
		this.next = next;
		this.isUsed = isUsed;
	}
}

class DLL4<E>
{
	private Node<E> head, tail;

	public DLL4()
	{
		head = null;
		tail = null;
	}

	public Node<E> getHead()
	{
		return head;
	}

	public Node<E> getTail()
	{
		return tail;
	}

	public void setHead(Node<E> head)
	{
		this.head = head;
	}

	public void setTail(Node<E> tail)
	{
		this.tail = tail;
	}

	public void insertFirst(E e)
	{
		Node<E> first = new Node(e, null, head, false);
		if (head != null)
			head.prev = first;
		if (tail == null)
			tail = first;
		head = first;
	}

	public void insertLast(E e)
	{
		if (head != null)
		{
			Node<E> last = new Node(e, tail, null, false);
			tail.next = last;
			tail = last;
		} else
			this.insertFirst(e);
	}
    public void print(){
                Node<E> tmp = head;
                while(tmp.next!=null){
                    System.out.print(tmp.data + " <-> ");
                    tmp=tmp.next;
                }
                System.out.print(tmp.data+"\n");
            }
}

public class Task2
{
	private static boolean isPrime(int num)
	{
		if (num <= 1)
		{
			return false;
		} else{

			for (int i = 2; i <= Math.sqrt(num); i++)
			{
				if ((num % i) == 0)
				{
					return false;
				}
			}
			return true;
		}
	}

	private static void pairPrimes(DLL4<Integer> list)
	{
		Node<Integer> fixed = list.getHead();

		while (fixed != null)
		{
			Node<Integer> iterator = fixed.next;
			boolean isUsed = false;
			while (iterator != null)
			{
				if ((!iterator.isUsed && !fixed.isUsed) && isPrime(Math.abs(fixed.data - iterator.data)))
				{
					Node<Integer> fixedTemp = fixed; // go inicijaliziram temp za fiksiraniot
					isUsed = true;
					fixed = fixed.next;

                    // Promena na mestata na fixed i iterator
					
					if (fixedTemp.prev == null)  // tuka proveruvam dali prviot dvizac mi e na head
					{
						list.setHead(iterator);
					}

					if (iterator.next == null) // tuka proveruvam dali vtoriot dvizac mi e na tail
					{
						list.setTail(fixedTemp);
					}

					Node<Integer> temp = fixedTemp.next; // gi pravam vrskite za next
					fixedTemp.next = iterator.next; 
					iterator.next = temp;

					if (fixedTemp.next != null) // dali fixed temp se naogja na tail
					{
						fixedTemp.next.prev = fixedTemp;  // ja pravam vrskata pomegju toj sto e pred fixed i fixedTemp
					}

					if (iterator.next != null) // ako iteratorot ne se naogja na tail
					{
						iterator.next.prev = iterator; // go povrzuvam iteratorot so negoviot sledbenik
					}

					temp = fixedTemp.prev;  // gi pravam vrskite prev
					fixedTemp.prev = iterator.prev;
					iterator.prev = temp;


					if (fixedTemp.prev != null) // ako fiksiraniot e na head
					{
						fixedTemp.prev.next = fixedTemp; // go povrzuvam naredniot so fixed
					}

					if (iterator.prev != null) 
					{
						iterator.prev.next = iterator; //
					}

					fixedTemp.isUsed = true; // gi azuriram iskoristenite 
					iterator.isUsed = true; 
					break;
				}
				iterator = iterator.next;
			}
			if (!isUsed)
			{
				fixed = fixed.next;
			}
		}
	}

	public static void main(String[] args){
		DLL4<Integer> list = new DLL4<Integer>();

		list.insertLast(7);
		list.insertLast(8);
		list.insertLast(13);
		list.insertLast(4);
		list.insertLast(11);
		list.insertLast(1);
		list.insertLast(7);
		list.insertLast(4);
		list.insertLast(15);
        System.out.println("Before pairing: ");
		list.print();
        pairPrimes(list);
		System.out.println("After pairing: ");
		list.print();
        
        
	}
}

