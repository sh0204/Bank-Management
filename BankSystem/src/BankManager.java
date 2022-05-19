import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class BankManager {
    public static int cnt = 0;
    private static BankManager instance = new BankManager();
    private static Account[] accountArray = new Account[100];
    private static Scanner scanner = new Scanner(System.in);
    static long balance;
    static long deposit;
    
    
    
    public static BankManager getInstance() {
        return instance;
    }
    
    //계좌생성하기
    static void join() {
    	
    	JPanel CreatePane = new JPanel(new GridLayout(0, 1));
    	JTextField AnoField = new JTextField(10);
        JTextField OwnerField = new JTextField(5);
        JTextField BalanceField = new JTextField(50);
        
        CreatePane.add(new JLabel("계좌번호: "));
        CreatePane.add(AnoField);
        CreatePane.add(new JLabel("계좌 주: "));
        CreatePane.add(OwnerField);
        CreatePane.add(new JLabel("초기금액: "));
        CreatePane.add(BalanceField);
        
        int result = JOptionPane.showConfirmDialog(null, CreatePane, "계좌 생성",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
              
        if (result == JOptionPane.OK_OPTION) {
            String ano = AnoField.getText();
            String owner = OwnerField.getText();
            balance = Integer.parseInt(BalanceField.getText());
            
            accountArray[cnt] = new Account(ano,owner,balance);
            cnt++;
            JOptionPane.showMessageDialog(null,"계좌생성 성공");
        }

        
    }
    
    //계좌확인하기
    private static Account login(String ano) {
        for (int i = 0 ; i < cnt; i++) {
            if (accountArray[i].getAno().equals(ano)) {
                return accountArray[i];
            }
        }
        JOptionPane.showMessageDialog(null, "해당 계좌가 없습니다. 프로그램이 종료됩니다.",
        	      "경고", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    //계좌목록보기
    static void query() {
    	String num = null;
    	String name = null;
    	long money= 0;
    	Component frame = null;
    	for(int i=0; i<cnt; i++) {
    		num = accountArray[i].getAno();
    		name = accountArray[i].getOwner();
    		money = accountArray[i].getBalance();
           
    		JPanel InfoPane = new JPanel(new GridLayout(0, 1));
        	JLabel numLabel = getLabel("계좌번호 :" + num);
        	JLabel nameLabel = getLabel("계좌 주 :" + name);
        	JLabel moneyLabel = getLabel("계좌잔고 :" + money);
        	InfoPane.add(numLabel);
        	InfoPane.add(nameLabel);
        	InfoPane.add(moneyLabel);
    		JOptionPane.showMessageDialog(
                    null, InfoPane, "계좌목록 : ",
                        JOptionPane.INFORMATION_MESSAGE);
          }
    	

    	
    }
    private static JLabel getLabel(String title) {
		// TODO Auto-generated method stub
		return new JLabel(title);
	}

	//예금하기
    static void deposit() {
        
        JPanel DepositPane = new JPanel(new GridLayout(0, 1));
    	JTextField AnoField = new JTextField(10);
        JTextField BalanceField = new JTextField(50);
        
        DepositPane.add(new JLabel("계좌번호: "));
        DepositPane.add(AnoField);
        DepositPane.add(new JLabel("입금 금액: "));
        DepositPane.add(BalanceField);
        
        int result = JOptionPane.showConfirmDialog(null, DepositPane, "입금",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        Component frame = null;
        
        if (result == JOptionPane.OK_OPTION) {
        	String ano = AnoField.getText();
        	login(ano).setBalance(login(ano).getBalance()+Long.parseLong(BalanceField.getText()));
        	deposit = Long.parseLong(BalanceField.getText());
            balance = balance+deposit;
            
            JOptionPane.showMessageDialog(frame,"계좌 잔고: " + balance ,"입금 성공",  JOptionPane.INFORMATION_MESSAGE);
        }
       
    }

    //출금하기
    static void withdraw() {
    
        JPanel WithdrawPane = new JPanel(new GridLayout(0, 1));
    	JTextField AnoField = new JTextField(10);
        JTextField WithdrawField = new JTextField(50);
        
        WithdrawPane.add(new JLabel("계좌번호: "));
        WithdrawPane.add(AnoField);
        WithdrawPane.add(new JLabel("출금 금액: "));
        WithdrawPane.add(WithdrawField);
        
        int result = JOptionPane.showConfirmDialog(null,  WithdrawPane, "출금",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        Component frame = null;
        
        if (result == JOptionPane.OK_OPTION) {
        	String ano = AnoField.getText();
            long withdraw = Long.parseLong(WithdrawField.getText());
            if(withdraw < balance) {
            	login(ano).setBalance(login(ano).getBalance()-Long.parseLong(WithdrawField.getText()));
            	balance = balance-withdraw;
            	JOptionPane.showMessageDialog(frame,"계좌 잔고: " + balance  ,"출금성공",  JOptionPane.INFORMATION_MESSAGE);
            	
            }else {
            	JOptionPane.showMessageDialog(frame,"출금 가능액을 초과하였습니다. 계좌잔고:"+balance);
            	}
          }
        
    }
    
    static JFrame myFrame = null;
    public static void main(String[] args) {
    	
    	JPanel LoginPane = new JPanel(new GridLayout(0, 1));
     	JTextField IdField = new JTextField(10);
     	
     	LoginPane.add(new JLabel("ID: "));
     	LoginPane.add(IdField);
        JPasswordField PwField = new JPasswordField(50);
        LoginPane.add(new JLabel("PW: "));
        LoginPane.add(PwField);
        int result = JOptionPane.showConfirmDialog(null, LoginPane, "LOGIN", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION) {
        	int loop =1;
            
    		while(loop<2) {
    			
    			int input=8;
    			String[] options = {"Create", "Withdraw", "Deposit", "Query", "LOGOUT"};
    	        JOptionPane myPane = new JOptionPane();
    	        myPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
    	        myPane.setMessage("항목을 선택해주세요.");
    	        
    	        myPane.setOptions(options);
    	        myPane.setInitialValue("Exit");
    	        JDialog myDialog = myPane.createDialog (myFrame, "Bank System");
    	        myDialog.setVisible(true);
    	        Object answer = myPane.getValue();
    	        
    			if (answer=="Create") {
    				input=1;
    				//join();
    			}
    	       else  if (answer=="Withdraw") {
    	    	   input=2;
    	    	   //withdraw();
    	       }
    	       else if(answer=="Deposit") {
    	    	   input=3;
    	    	   //deposit();
    	       }
    	       else if(answer=="Query") {
    	    	   input=4;
    	    	   //query();
    	       }
    	       else {
    	    	   break;
    	    	  
    	       }
    			if(input==1) {
    				join();
    			}
    			else if(input==2) {
    				withdraw();
    			}
    			else if(input==3) {
    				deposit();
    			}
    			else if(input==4) {
    				query();
    			}
    			else;
    			Component Again = null;
    			int n = JOptionPane.showConfirmDialog(Again,  "처음화면으로 돌아가시겠습니까?"," ",JOptionPane.YES_NO_OPTION);
    			if(n!=0) break;
    			
    		}
    		
    		
        }	 
        }
        
        
}
