package prob09;

public class MyStack extends Object{
	int num;
	int count = 0;
	int [] numList;
	
	public MyStack() {
	}

	public MyStack(int num) {
		this.num = num;
		if(num>0) {
			numList = new int[num];
		}
		else {
			num = 10;
			numList = new int[num];
		}
	}
	
	public void push(int stackNum) {
		if(isFull()) {
			int temp[] = new int[numList.length*2];
			System.arraycopy(numList, 0, temp, 0, numList.length);
			numList = temp;
			temp = null;
		}
		numList[count] = stackNum;
		count++;
	}
	public boolean isEmpty() {
		boolean b = false;
		if(count== 0)
			return true;
		else
			return b;
	}
	public boolean isFull() {
		boolean b = false;
		if(count == numList.length)
			return true;
		else
			return b;
	}
	public int top() {
		if(isEmpty())return -1;
		int a;
		//if(count <= num)
		a = numList[count-1];
		return a;
	}
	public int pop() {
		int a;
		if(count >0) {
			a = numList[count-1];
			count--;
			return a;
		}
		else {
			a = -1;
			return a;
		}
	}
}
//1.	MyStack클래스는 기본생성자로 생성하면 int 타입의 정수를 10개만 저장하는 Stack 클래스이다. 
//2.	객체 생성 시 생성자 매개변수로 배열의 크기를 지정할 수 있으나 음수가 
//		매개변수로 들어올 경우는 기본적으로 10개의 정수를 저장하도록 한다. 
//3.	push() 메서드로 Stack에 정수를 저장한다.
//4.	isEmpty() 메서드로 Stack이 비어있는지 확인할 수 있다. 
//5.	ifFull() 메서드로 Stack이 가득찼는지 확인할 수 있다. 
//6.	top() 메서드로 Stack에서 최상위에 저장된 숫자가 뭔지 알 수 있다. 
//		top() 메서드를 호출했다고 해서 숫자가 삭제되는 것은 아니다.
//		꺼낼 숫자가 없는 경우 -1을 리턴한다.
//7.	pop() 메서드로 Stack에서 최상위에 저장된 숫자를 꺼낼 수 있다. 
//		스택에서 숫자를 꺼내면 그 숫자는 Stack에서 삭제된다. 꺼낼 숫자가 없는 경우 -1을 리턴한다.
//8.	java.util.Stack class는 사용하지 않는다.
