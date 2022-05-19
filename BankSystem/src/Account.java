public class Account {
	private String ano;
	private String owner;
	private long balance;

	public Account(String ano, String owner, long balance) {
		this.ano = ano;
		this.owner = owner;
		this.balance = balance;
	}

	public String getAno() { return ano; }
	public void setAno(String ano) { this.ano = ano; }
	public String getOwner() { return owner; }
	public void setOwner(String owner) { this.owner = owner; }
	public long getBalance() { return balance; }
	public void setBalance(long balance) { this.balance = balance; }
	public void depositBalance(long balance) { this.balance = this.balance + balance; }
	public void withdrawBalance(long balance) { this.balance = this.balance - balance; }

	public static Object toString(Account[] accountArray) {
		// TODO Auto-generated method stub
		return null;
	}
}