import java.util.*;
import java.io.*;

class Main {
	 static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int Jack, Jill;
			while (true) {
				StringTokenizer str = new StringTokenizer(br.readLine());
				Jack = Integer.parseInt(str.nextToken());
				Jill = Integer.parseInt(str.nextToken());
				if (Jack == 0 && Jill == 0) break;
				
				TreeMap<Integer, Integer> cd = new TreeMap<Integer, Integer>();
				int a, b, count=0;
				while ((Jack--) != 0) {
					a = Integer.parseInt(br.readLine());
					cd.put(a, a);
				}
				
				while ((Jill--) != 0) {
					b = Integer.parseInt(br.readLine());
					if (cd.get(b) != null) ++count;
				}
				System.out.println(count);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
