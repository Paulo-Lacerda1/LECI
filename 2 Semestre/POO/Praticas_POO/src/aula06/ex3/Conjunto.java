package aula06.ex3;

public class Conjunto {
    private int[] conjunto;
	
	public Conjunto() {
		int[] conjunto = new int[0];
		this.conjunto = conjunto;
	}
	public Conjunto(int[] c) {
		this.conjunto = c;
	}
	
	public void insert(int n) {
		int[] c = new int[conjunto.length+1];
		if (contains(n) == true) {
			return;
		} else {
			c[c.length-1] = n;
			System.arraycopy(conjunto, 0, c, 0, conjunto.length);
		}	
		conjunto = c;
	}
	
	public boolean contains(int n) {
		for(int i = 0; i < conjunto.length ; i++) {
			if (conjunto[i] == n)
				return true;
		}
		return false;
	}
	
	public void remove(int n) {
		for(int i = 0; i < conjunto.length ; i++) {
			if (conjunto[i] == n) {
				conjunto[i] = 0;
				return;
			}
		}
	}
	
	public void empty() {
		for(int i = 0; i < conjunto.length ; i++) {
			conjunto[i] = 0;
		}
	}
	
	public int size() {
		int size = 0;
		for(int i = 0; i < conjunto.length ; i++) {
			if (conjunto[i] != 0) {
				size++;
			}
		}
		return size;
	}
	
	public Conjunto unir(Conjunto add) {
		Conjunto c = new Conjunto(conjunto);
		int[] a = add.getConjunto();
		for (int i = 0; i < a.length; i++) {
			if(c.contains(a[i])==false) {
				c.insert(a[i]);
			}
		}
		return c;
	}
	
	public Conjunto interset(Conjunto diff) {
		Conjunto c = new Conjunto(conjunto);
		Conjunto c1 = new Conjunto();
		int[] a = diff.getConjunto();
		for (int i = 0; i < a.length; i++) {
			if(c.contains(a[i])==true) {
				c1.insert(a[i]);
			}
		}
		return c1;
	}
	
	public Conjunto subtrair(Conjunto inter) {
		Conjunto c = new Conjunto(conjunto);
		int[] a = inter.getConjunto();
		for (int i = 0; i < a.length; i++) {
			if(c.contains(a[i])==true) {
				c.remove(a[i]);
			}
		}
		return c;
	}

	public int[] getConjunto() {
		return conjunto;
	}

	public void setConjunto(int[] conjunto) {
		this.conjunto = conjunto;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.conjunto.length ; i++) {
			if (this.conjunto[i] != 0) {
				sb.append(this.conjunto[i] + " ");
			}
		}
		return sb.toString();
	}
}
