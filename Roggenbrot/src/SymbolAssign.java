
public class SymbolAssign {
	private char symbol;
	private int id;
	
	private SymbolAssign(char symbol, int id){
		this.symbol = symbol;
		this.id = id;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
