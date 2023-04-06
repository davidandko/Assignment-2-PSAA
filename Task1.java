
// Да се напише функција која како аргумент добива магацин. Функцијата треба да ја сортира
// содржината на магацинот така што најмалиот елемент ќе биде сместен на врвот од
// магацинот, а најголемиот елемент ќе биде сместен на дното. Притоа, за реализација на
// решението функцијата може да користи само магацини како помошна структура
// (дополнително дефинирање на променливи – обични целобројни или структурни не е
// дозволено).
// Да се напише главна програма во која ќе се тестира работата на функцијата.
// Пример: За магацин со содржина bottom[2, 5, 6, 1, 4, 3, 9, 7, 8]top, функцијата треба да
// добие: bottom[9, 8, 7, 6, 5, 4, 3, 2, 1]top



import java.util.Scanner;
import java.util.Stack;

public class Task1 {
    static Stack<Integer> sort(Stack<Integer> g){
        Stack<Integer> temp=new Stack<>();
        Stack<Integer> st1=new Stack<>();
        Stack<Integer> st2=new Stack<>();

        temp.push(g.pop());
        while(!g.empty()){
            if(g.peek() > temp.peek()){ // dali elementot vo glavniot magacin e pogolem od temp
                st1.push(temp.pop()); // go prefrlam elementot vo st1
                temp.push(g.pop());  // go vnesuvam elementot od glaven vo temp
            }else{
                st1.push(g.pop()); // ako elementot od glavniot magacin e pomal go prefrlam vo st1 direktno
            }
        }
        //tuka magacinot e prazen
       
        g.push(temp.pop()); // go stavam najgolemiot element sto sum go pronasol vo glavniot magacin

        do{
            if(st1.empty()){ 
                while(!st2.empty()){
                    if(temp.isEmpty()){
                        temp.push(st2.pop());
                    }else{
                        if(st2.peek() > temp.peek()){
                            st1.push(temp.pop());
                            temp.push(st2.pop());
                        }else{
                            st1.push(st2.pop());
                        }
                    }
    
                }
                g.push(temp.pop()); // go stavam najgolemiot  element sto sum go nasol vo glavniot magacin
            }else{
                while(!st1.empty()){
                    if(temp.isEmpty()){
                        temp.push(st1.pop());
                    }else{
                        if(st1.peek() > temp.peek()){
                            st2.push(temp.pop());
                            temp.push(st1.pop());
                        }else{
                            st2.push(st1.pop());
                        }
                    }
                }
                g.push(temp.pop()); // go stavam najgolemiot  element sto sum go nasol vo glavniot magacin
            }

        if(st1.empty() && st2.empty()){
            break;
        }
        }while(true);
           
        return g; 
    }

   public static void main(String[] args) throws Exception {

        Scanner input=new Scanner(System.in);
        System.out.println("Enter number of elements: ");
        int n=input.nextInt();
        Stack<Integer> mainStack=new Stack<Integer>();

        System.out.println("Start entering elements into the stack: ");
        for(int i=0;i<n;i++){
            int broj=input.nextInt();
            mainStack.push(broj);
        }
        input.close();
        
        System.out.println("Sorted stack: ");
        sort(mainStack);
        while(!mainStack.empty()){
        System.out.println(mainStack.pop());
        }
    }     
}  
