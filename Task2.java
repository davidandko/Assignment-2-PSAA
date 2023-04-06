// Да се напише програма во која корисникот од тастатура внесува двојно поврзана листа
// (DLinkedList) со произволен број на елементи. Да се напише функција која ја добива двојно
// поврзаната листа како аргумент. Функцијата треба за секој јазол во листата, почнувајќи од
// водечкиот јазол, да провери дали јазолот може да го замени местото со некој од неговите
// следбеници. Притоа, секој јазол може да направи само една замена. Замената меѓу јазлите
// се случува ако вредностите помеѓу двата јазли се разликуваат за прост број. Функцијата
// треба да ја враќа променетата поврзана листа и информација за паровите од јазли помеѓу
// кои се случила замена на нивните места во листата.
// Да се напише главна програма во која ќе се тестира работата на функцијата.
// Пример: Внесена е листата: 7 <-> 8 <-> 13 <-> 4 <-> 11 <-> 1 <-> 7 <-> 4 <-> 15.
// Првиот јазол 7 во разлика со јазолот 8 дава 1, што не е прост број; првиот јазол 7 во разлика
// со јазолот 13 дава 6, што не е прост број; првиот јазол 7 во разлика со јазолот 4 дава 3, па се
// прави замена на овие два јазли. Листата е:
// 4 <-> 8 <-> 13 <-> 7 <-> 11 <-> 1 <-> 7 <-> 4 <-> 15.
// Двата избледени јазли не смее да ги менуваат местата.
// Се разгледува јазолот 8 кој во разлика со јазолот 13 дава 5, што е прост број, па и овие јазли
// ги менуваат местата и листата е:
// 4 <-> 13 <-> 8 <-> 7 <-> 11 <-> 1 <-> 7 <-> 4 <-> 15.
// Следно се разгледува јазолот 11. Тој во разлика со јазолот 1 враќа 10; во разлика со јазолот
// 7 враќа 4; во разлика со јазолот 4 враќа 7, па овие јазли ги менуваат местата. Листата ќе
// стане:
// 4 <-> 13 <-> 8 <-> 7 <-> 4 <-> 1 <-> 7 <-> 11 <-> 15.
// Се продолжува со јазолот 1. За него јазолот 7 враќа разлика 1, јазолот 11 не треба да се
// разгледува бидејќи веќе бил заменет, а јазолот 5 враќа 4.
// Се продолжува со јазолот 7, па за него 11 не се разгледува бидејќи овој јазол веќе бил
// заменет, а јазолот 15 враќа разлика 8, па промена не се случува.
// Јазолот 15 нема следбеници.
// Крајната листа е:
// 4 <-> 13 <-> 8 <-> 7 <-> 4 <-> 1 <-> 7 <-> 11 <-> 15.




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

