package app.caculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Stack;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener
{
	private TextView txtInput;
	private TextView txtOutput;
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	private Button btn9;
	private Button btnComma;
	private Button btnNegative;
	private Button btnResult;
	private Button btnSub;
	private Button btnPlus;
	private Button btnMulti;
	private Button btnDivide;
	private Button btnDividePlus;
	private Button btnSqr;
	private Button btnSqrt;
	private Button btnFactorial;
	private Button btnLn;
	private Button btnLog;
	private Button btnSin;
	private Button btnCos;
	private Button btnTan;
	private Button btnAc;
	private Button btnHis;
	private Button btnOpeningQuotaion;
	private Button btnClosingQuotaion;
	private String strInput;
	private Button btnDef;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtInput = (TextView) findViewById(R.id.input);
		txtOutput = (TextView) findViewById(R.id.output);
		btn0 = (Button) findViewById(R.id.btn_0);
		btn0.setOnClickListener(this);
		btn1 = (Button) findViewById(R.id.btn_1);
		btn1.setOnClickListener(this);
		btn2 = (Button) findViewById(R.id.btn_2);
		btn2.setOnClickListener(this);
		btn3 = (Button) findViewById(R.id.btn_3);
		btn3.setOnClickListener(this);
		btn4 = (Button) findViewById(R.id.btn_4);
		btn4.setOnClickListener(this);
		btn5 = (Button) findViewById(R.id.btn_5);
		btn5.setOnClickListener(this);
		btn6 = (Button) findViewById(R.id.btn_6);
		btn6.setOnClickListener(this);
		btn7 = (Button) findViewById(R.id.btn_7);
		btn7.setOnClickListener(this);
		btn8 = (Button) findViewById(R.id.btn_8);
		btn8.setOnClickListener(this);
		btn9 = (Button) findViewById(R.id.btn_9);
		btn9.setOnClickListener(this);
		btnComma = (Button) findViewById(R.id.btn_comma);
		btnComma.setOnClickListener(this);
		btnNegative = (Button) findViewById(R.id.btn_negative);
		btnNegative.setOnClickListener(this);
		btnResult = (Button) findViewById(R.id.btn_result);
		btnResult.setOnClickListener(this);
		btnSub = (Button) findViewById(R.id.btn_sub);
		btnSub.setOnClickListener(this);
		btnPlus = (Button) findViewById(R.id.btn_plus);
		btnPlus.setOnClickListener(this);
		btnMulti = (Button) findViewById(R.id.btn_multi);
		btnMulti.setOnClickListener(this);
		btnDivide = (Button) findViewById(R.id.btn_divide);
		btnDivide.setOnClickListener(this);
		btnDividePlus = (Button) findViewById(R.id.btn_div_plus);
		btnDividePlus.setOnClickListener(this);
		btnSqr = (Button) findViewById(R.id.btn_sqr);
		btnSqr.setOnClickListener(this);
		btnSqrt = (Button) findViewById(R.id.btn_sqrt);
		btnSqrt.setOnClickListener(this);
		btnFactorial = (Button) findViewById(R.id.btn_factorial);
		btnFactorial.setOnClickListener(this);
		btnLn = (Button) findViewById(R.id.btn_ln);
		btnLn.setOnClickListener(this);
		btnLog = (Button) findViewById(R.id.btn_log);
		btnLog.setOnClickListener(this);
		btnSin = (Button) findViewById(R.id.btn_sin);
		btnSin.setOnClickListener(this);
		btnCos = (Button) findViewById(R.id.btn_cos);
		btnCos.setOnClickListener(this);
		btnTan = (Button) findViewById(R.id.btn_tan);
		btnTan.setOnClickListener(this);
		btnAc = (Button) findViewById(R.id.btn_ac);
		btnAc.setOnClickListener(this);
		btnHis = (Button) findViewById(R.id.btn_history);
		btnHis.setOnClickListener(this);
		btnOpeningQuotaion = (Button) findViewById(R.id.btn_OpeningQuotation);
		btnOpeningQuotaion.setOnClickListener(this);
		btnClosingQuotaion = (Button) findViewById(R.id.btn_ClosingQuotation);
		btnClosingQuotaion.setOnClickListener(this);
	}

	String expression = "";
	String oldClick = "";

	@Override
	public void onClick(View v)
	{
		expression = txtInput.getText().toString();
		btnDef = (Button) v;
		strInput = (String) btnDef.getText();
		if (oldClick.equals("=") || oldClick.equals("History"))
			expression = "";
		if ("|+|-|×|÷|%|^|".contains(oldClick) && ("|+|-|×|÷|%|^|!|".contains(strInput)))
			return;
		if (oldClick.equals("!") && oldClick.equals(strInput))
			return;
		switch (strInput)
		{
		case "=":
			if (expression.equals("") || !extractData())
			{
				txtInput.setText("Please enter right expression!");
				break;
			}

			// calculate all
			calculateWhenPressEqual();
			// write down the output
			txtOutput.setText(operand.pop());
			// write history
			writeHistory(DateFormat.getDateTimeInstance().format(new Date()) + ": " + expression + " = "
					+ txtOutput.getText());
			break;
		case "AC":
			operand.clear();
			operator.clear();
			txtInput.setText("");
			txtOutput.setText("");
			break;
		case "History":
			txtInput.setText(readHistory());
			break;
		default:
			if (isUnaryFunction(strInput) && !strInput.equals("!"))
				strInput = expression + strInput + "(";
			else
				strInput = expression + strInput;
			txtInput.setText(strInput);
			break;
		}
		oldClick = btnDef.getText().toString();
	}

	private boolean extractData()
	{
		String[] ss = expression.split("");
		String oldStr = "";
		String str = "";
		if ("|+|-|×|÷|%|^|!|".contains(ss[1]))
			return false;
		for (int i = 1; i < ss.length; i++)
		{
			str = ss[i];
			// if input operand
			if (isOperand(str))
			{
				if (str.equals("±"))
					str = "-";
				tmpOperand += str;
			} else
			{
				if (isOperand(oldStr))
				{
					operand.add(tmpOperand);
					tmpOperand = "";
				}

				// if input opening quote
				if (isOpeningQuote(str))
				{
					if (oldStr.matches(".*[a-z].*"))
					{
						operator.add(tmpOperand);
						tmpOperand = "";
					}
					operator.add(str);
				} else
				{
					// if input closing quote
					if (isClosingQuote(str))
					{
						calculateWhenClosingQuote();
					} else
					{
						//
						if (str.matches(".*[a-z].*"))
						{
							tmpOperand += str;
							oldStr = str;
							continue;
						}

						// if operator is not null
						if (operator.isEmpty() == false)
						{
							// calculate if priority of input <= priority of
							// top
							// of stack
							while (getPriority(str) <= getPriority(operator.peek()))
							{
								if (isBinaryFunction(operator.peek()))
									calculate(Double.parseDouble(operand.pop()), operator.pop(),
											Double.parseDouble(operand.pop()));
								else
									calculate(operator.pop(), Double.parseDouble(operand.pop()));
								if (operator.isEmpty())
									break;
							}
						}
						operator.push(str);
					}
				}
			}
			oldStr = str;
		}
		// check if the last character is operand, add it to stack
		if (isOperand(oldStr))
		{
			operand.add(tmpOperand);
			tmpOperand = "";
		}
		return true;
	}

	Stack<String> operand = new Stack<String>();
	Stack<String> operator = new Stack<String>();

	private void calculateWhenClosingQuote()
	{
		if (isOpeningQuote(operator.peek()))
		{
			operator.pop();
			if (operator.isEmpty() == false)
				if (isUnaryFunction(operator.peek()))
				{
					calculate(operator.pop(), Double.parseDouble(operand.pop()));
				}
		} else
		{
			calculate(Double.parseDouble(operand.pop()), operator.pop(), Double.parseDouble(operand.pop()));
			calculateWhenClosingQuote();
		}
	}

	private void calculateWhenPressEqual()
	{
		while (operator.isEmpty() == false)
			if (isOpeningQuote(operator.peek()))
			{
				operator.pop();
				if (operator.isEmpty() == false)
					if (isUnaryFunction(operator.peek()))
					{
						calculate(operator.pop(), Double.parseDouble(operand.pop()));
					} else
					{
						calculate(Double.parseDouble(operand.pop()), operator.pop(), Double.parseDouble(operand.pop()));
					}
			} else
			{
				if (isUnaryFunction(operator.peek()))
				{
					calculate(operator.pop(), Double.parseDouble(operand.pop()));
				} else
				{
					calculate(Double.parseDouble(operand.pop()), operator.pop(), Double.parseDouble(operand.pop()));
				}
			}
	}

	final String UNARY_FUNC = "|√|!|sin|cos|tan|ln|log|";
	final String BINARY_FUNC = "|+|-|×|÷|%|^|";
	boolean isDigit = true;
	String tmpOperand = "";

	private boolean isOperand(String s)
	{
		if (UNARY_FUNC.contains(s) || BINARY_FUNC.contains(s) || s.equals("(") || s.equals(")"))
			return false;
		return true;
	}

	private boolean isUnaryFunction(String op)
	{
		if (UNARY_FUNC.contains(op))
			return true;
		return false;
	}

	private boolean isBinaryFunction(String op)
	{
		if (BINARY_FUNC.contains(op))
			return true;
		return false;
	}

	private int getPriority(String s)
	{
		int returnVal = -1;
		switch (s)
		{
		case "+":
		case "-":
			returnVal = 0;
			break;
		case "×":
		case "÷":
		case "%":
			returnVal = 1;
			break;
		case "√":
		case "sin":
		case "cos":
		case "tan":
		case "ln":
		case "log":
		case "^":
			returnVal = 2;
			break;
		case "!":
			returnVal = 3;
			break;
		default:
			break;
		}
		return returnVal;
	}

	private boolean isOpeningQuote(String s)
	{
		if (s.equals("("))
			return true;
		return false;
	}

	private boolean isClosingQuote(String s)
	{
		if (s.equals(")"))
			return true;
		return false;
	}

	private double calculate(double a, String x, double b)
	{
		double returnVal = -1;
		switch (x)
		{
		case "+":
			returnVal = a + b;
			break;
		case "-":
			returnVal = b - a;
			break;
		case "×":
			returnVal = a * b;
			break;
		case "÷":
			returnVal = b / a;
			break;
		case "%":
			returnVal = b % a;
			break;
		case "^":
			returnVal = Math.pow(b, a);
			break;
		default:
			break;
		}
		operand.push(String.valueOf(returnVal));
		return returnVal;
	}

	private double calculate(String x, double a)
	{
		double returnVal = -1;
		switch (x)
		{
		case "√":
			returnVal = Math.sqrt(a);
			break;
		case "!":
			returnVal = factorial(a);
			break;
		case "sin":
			returnVal = Math.sin(a);
			break;
		case "cos":
			returnVal = Math.cos(a);
			break;
		case "tan":
			returnVal = Math.tan(a);
			break;
		case "log":
			returnVal = Math.log10(a);
			break;
		case "ln":
			returnVal = Math.log(a);
			break;
		default:
			break;
		}
		operand.push(String.valueOf(returnVal));
		return returnVal;
	}

	private double factorial(double number)
	{
		if (number <= 1)
			return 1;
		else
			return number * factorial(number - 1);
	}

	private void writeHistory(String data)
	{
		try
		{
			File fout = new File(this.getFilesDir() + "/history.txt");
			FileOutputStream fos = new FileOutputStream(fout, true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			try
			{
				bw.write(data);
				bw.newLine();
				bw.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				Log.e("login activity", "Can not read file: " + e.toString());
				e.printStackTrace();
			}
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			Log.e("login activity", "File not found: " + e.toString());
			e.printStackTrace();
		}
	}

	private String readHistory()
	{
		String ret = "";
		try
		{
			InputStream inputStream = openFileInput("history.txt");

			if (inputStream != null)
			{
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null)
				{
					stringBuilder.append(receiveString);
					stringBuilder.append(System.getProperty("line.separator"));
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e)
		{
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e)
		{
			Log.e("login activity", "Can not read file: " + e.toString());
		}

		return ret;
	}
}
