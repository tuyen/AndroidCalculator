package app.caculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
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
	private Button btnDel;
	private String input;
	private Button def;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtInput = (TextView) findViewById(R.id.input);
		txtOutput = (TextView) findViewById(R.id.output);
		btn0 = (Button) findViewById(R.id.btn_0);
		btn1 = (Button) findViewById(R.id.btn_1);
		btn2 = (Button) findViewById(R.id.btn_2);
		btn3 = (Button) findViewById(R.id.btn_3);
		btn4 = (Button) findViewById(R.id.btn_4);
		btn5 = (Button) findViewById(R.id.btn_5);
		btn6 = (Button) findViewById(R.id.btn_6);
		btn7 = (Button) findViewById(R.id.btn_7);
		btn8 = (Button) findViewById(R.id.btn_8);
		btn9 = (Button) findViewById(R.id.btn_9);
		btnComma = (Button) findViewById(R.id.btn_comma);
		btnNegative = (Button) findViewById(R.id.btn_negative);
		btnResult = (Button) findViewById(R.id.btn_result);
		btnSub = (Button) findViewById(R.id.btn_sub);
		btnPlus = (Button) findViewById(R.id.btn_plus);
		btnMulti = (Button) findViewById(R.id.btn_multi);
		btnDivide = (Button) findViewById(R.id.btn_divide);
		btnDividePlus = (Button) findViewById(R.id.btn_div_plus);
		btnSqr = (Button) findViewById(R.id.btn_sqr);
		btnSqrt = (Button) findViewById(R.id.btn_sqrt);
		btnFactorial = (Button) findViewById(R.id.btn_factorial);
		btnLn = (Button) findViewById(R.id.btn_ln);
		btnLog = (Button) findViewById(R.id.btn_log);
		btnSin = (Button) findViewById(R.id.btn_sin);
		btnCos = (Button) findViewById(R.id.btn_cos);
		btnTan = (Button) findViewById(R.id.btn_tan);
		btnAc = (Button) findViewById(R.id.btn_ac);
		btnHis = (Button) findViewById(R.id.btn_history);
		btnDel = (Button) findViewById(R.id.btn_del);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		def = (Button) v;
		input += (String) def.getText();
		txtInput.setText(input);
	}

	private void onBtnClick(View v)
	{
		def = (Button) v;
		input += (String) def.getText();
		txtInput.setText(input);
	}
}
