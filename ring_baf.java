package algo5;
import java.io.BufferedReader;
import java.util.Arrays;
class Queue {
	Queue(int size){
		this.size = size ;
		queue = new int[size];
		top = 0;
		tail = 0;
	}
	private final int size;
	private int[] queue;
	private int top = 0, tail = 0;
	//private boolean max_judge = false;
	
	private int next(int p) {
		if( p == size -1) {
			return 0;
		}else {
			return p + 1;
		}
	}
	
	private int prev(int p) {
		if(p == 0) {
			return size - 1;
		}else {
			return p - 1;
		}
	}
	
	//末尾データ追加OK
	public void enqueue_tail(int val) {
		if (next(tail) == top) {
			System.out.println("キューが満杯です");
		}else{
			queue[tail] = val;
			tail = next(tail);
		}
		System.out.println( top + ":" + tail);
	}
	//末尾データ取得
	public int dequeue_tail() {
		int ret;
		if(top == tail) {
			return -1;
		}else {
			//tail = (tail - 1) % size;
			tail = prev(tail);
			ret = queue[tail];	
			System.out.println( top + ":" + tail);
			return ret;
		}
	}
	
	//先頭データ追加
	public void enqueue_top(int val) {
		if (prev(top) == tail) {
			System.out.println("キューが満杯です");
		}else {
			top = prev(top);
			queue[top] = val;		
		}
		System.out.println(top + ":" + tail);
	}
	//先頭データ取得OK?
	public int dequeue_top() {
		int ret;
		if(top == tail) {
			return -1;
		}else {
			ret = queue[top];
			//top = (top + 1) % size;
			top = next(top);
			System.out.println( top + ":" + tail);
			return ret;
		}
	}
	//要素数カウント
	public int count_queue() {
		if(top < tail) {
			return tail - top;
		}else if(top == tail){
			return 0;
		}else {
			return size-(top - tail);
		}
	}
	
	//要素出力
	public void print_queue() {
		for(int i = top; i != tail; i = (i+1) % size) {
			System.out.print(queue[i] + " ");
		}
		System.out.println("（要素数：" + this.count_queue() + ")");
	}
	

}

public class ring_baf {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue queue = new Queue(5);	//0123が実質的な使用領域
		int command = 0, count = 0;
		
		do {
			System.out.print("手順" + count + ",現在のキュー：");
			queue.print_queue();
			System.out.print("コマンド入力>>");
			command = intReader();
			
			//実行する奴を場合分け
			switch(command) {
			case 1: {
				System.out.print("末尾にデータを追加します>>");
				int input = intReader();
				queue.enqueue_tail(input);
				break;	
			}
			case 2: {
				System.out.println("末尾のデータを取り出します");
				int get = queue.dequeue_tail();
				if(get == -1) {
					System.out.println("--取り出せるものがありません");
				}else {
					System.out.println("--" + get + "を取り出しました");
				}
				break;
			}
			case 3: {
				System.out.print("先頭にデータを追加します>>");
				int input = intReader();
				queue.enqueue_top(input);
				break;	
			}
			case 4: {
				System.out.println("先頭のデータを取り出します");
				int get = queue.dequeue_top();
				if(get == -1) {
					System.out.println("--取り出せるものがありません");
				}else {
					System.out.println("--" + get + "を取り出しました");
				}
				break;
			}
			}
			count++;
		}while(command != 0);
	}
	
	private static int intReader() {
		try {
			BufferedReader read = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = read.readLine();
			return Integer.parseInt(str);
		}catch (java.io.IOException e) {
			System.err.println("IO exception");
			return 0;
		}catch (NumberFormatException e) {
			return 0;
		}
	}

}
