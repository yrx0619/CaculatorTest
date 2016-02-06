package activitytest.yerx.com.caculatortest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {
    Button[] m_buttonNum;
    Button[] m_buttonOp;
    Button m_buttonClear;
    Button m_buttonDel;
    //Button m_buttonDot;
    TextView m_resultView;
    int m_result;
    int[] m_number_queue;
    int[] m_op_queue;
    String num;

    private void getBtnNumById(){
        m_buttonNum[0] = (Button)findViewById(R.id.button_num0);
        m_buttonNum[1] = (Button)findViewById(R.id.button_num1);
        m_buttonNum[2] = (Button)findViewById(R.id.button_num2);
        m_buttonNum[3] = (Button)findViewById(R.id.button_num3);
        m_buttonNum[4] = (Button)findViewById(R.id.button_num4);
        m_buttonNum[5] = (Button)findViewById(R.id.button_num5);
        m_buttonNum[6] = (Button)findViewById(R.id.button_num6);
        m_buttonNum[7] = (Button)findViewById(R.id.button_num7);
        m_buttonNum[8] = (Button)findViewById(R.id.button_num8);
        m_buttonNum[9] = (Button)findViewById(R.id.button_num9);
        m_buttonOp[0] = (Button) findViewById(R.id.button_op_plus);
        m_buttonOp[1] = (Button) findViewById(R.id.button_op_subtraction);
        m_buttonOp[2] = (Button) findViewById(R.id.button_op_multiplicative);
        m_buttonOp[3] = (Button) findViewById(R.id.button_op_division);
        m_buttonOp[4] = (Button) findViewById(R.id.button_op_equal);
        m_buttonClear = (Button) findViewById(R.id.button_clear);
        m_buttonDel = (Button) findViewById(R.id.button_delete);

    }

    private void setBtnNumClickListener(){
        int index = 0;
        for(;index < 10; index++)
            m_buttonNum[index].setOnClickListener(this);
    }

    private void setBtnOpClickListener() {
        int index = 0;
        for (; index < 5; index++)
            m_buttonOp[index].setOnClickListener(this);
    }


    private void setBtnClearClickListener() {
        m_buttonClear.setOnClickListener(this);
    }

    private void setBtnDelClickListener() {
        m_buttonDel.setOnClickListener(this);
    }

    private Boolean isNotNumberButton(int id)
    {
        switch(id){
            case R.id.button_op_equal:
            case R.id.button_op_multiplicative:
            case R.id.button_op_plus:
            case R.id.button_op_subtraction:
            case R.id.button_op_division:
            case R.id.button_dot:
                return Boolean.TRUE;
            default:
                return Boolean.FALSE;
        }
    }

    private Boolean isPlusOperation(int op) {
        if (1 == op)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    private Boolean isClearButton(int id) {
        if (R.id.button_clear == id)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    private Boolean isDelButton(int id) {
        if (R.id.button_delete == id)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    private int getCountInOperationQueue() {
        int i = 0;
        int count = 0;
        for (; i < 2; i++)
            if (m_op_queue[i] != 0)
                count++;
        Log.d("count", Integer.toString(count));
        return count;
    }

    private void addInOperationQueue(int id, int index) {
        switch (id) {
            case R.id.button_op_equal:
                m_op_queue[index] = 5;
                break;
            case R.id.button_op_multiplicative:
                m_op_queue[index] = 3;
                break;
            case R.id.button_op_plus:
                m_op_queue[index] = 1;
                break;
            case R.id.button_op_subtraction:
                m_op_queue[index] = 2;
                break;
            case R.id.button_op_division:
                m_op_queue[index] = 4;
                break;
            case R.id.button_dot:
            default:
        }
    }

    private void intialNumberQueue() {
        for (int i = 0; i < 2; i++) {
            m_number_queue[i] = 0;
        }
    }

    private void intialOperationQueue() {
        for (int i = 0; i < 2; i++) {
            m_op_queue[i] = 0;
        }
    }

    private void removedZeroInNumberHead() {
        if (num.startsWith("0") && (num.length() > 1))
            num = num.substring(1, num.length());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_buttonNum = new Button[10];
        m_buttonOp = new Button[5];
        m_number_queue = new int[2];
        m_op_queue = new int[2];
        intialNumberQueue();
        intialOperationQueue();
        getBtnNumById();
        num = "";
        m_resultView = (TextView) findViewById(R.id.caculator_text);
        //m_buttonNum[1].setOnClickListener(this);
        //Log.d("Create", m_op_queue[0] + "," + m_op_queue[1] + "," + m_number_queue[0] + "," + m_number_queue[1]);
        setBtnNumClickListener();
        setBtnOpClickListener();
        setBtnClearClickListener();
        setBtnDelClickListener();
    }

    @Override
    public void onClick(View v) {

        Button tmp_btn;
        String result;

        if (isClearButton(v.getId())) {
            num = "";
            m_result = 0;
            m_number_queue[0] = 0;
            m_number_queue[1] = 0;
            m_op_queue[0] = 0;
            m_op_queue[1] = 0;
            m_resultView.setText("0");
        } else if (isDelButton(v.getId())) {
            String m_tmp;
            m_tmp = m_resultView.getText().toString();
            m_tmp = m_tmp.substring(0, m_tmp.length() - 1);
            if (m_tmp.length() >= 1)
                m_resultView.setText(m_tmp);
            else
                m_resultView.setText("0");
        } else if (isNotNumberButton(v.getId()) == Boolean.FALSE) {
            tmp_btn = (Button) findViewById(v.getId());
            num = num + tmp_btn.getText().toString();
            removedZeroInNumberHead();
            //Log.d("click Num", m_op_queue[0] + "," + m_op_queue[1] + "," + m_number_queue[0] + "," + m_number_queue[1]);
            m_resultView.setText(num);
        } else if (isNotNumberButton(v.getId()) == Boolean.TRUE) {
            //Log.d("141", m_op_queue[0] + "," + m_op_queue[1] + "," + m_number_queue[0] + "," + m_number_queue[1]);
            if (getCountInOperationQueue() == 0) {
                num = m_resultView.getText().toString();
                m_number_queue[0] = Integer.parseInt(num);
                addInOperationQueue(v.getId(), 0);
                num = "";
                //Log.d("click Op", m_op_queue[0] + "," + m_op_queue[1] + "," + m_number_queue[0] + "," + m_number_queue[1]);

            } else if (getCountInOperationQueue() == 1) {
                num = m_resultView.getText().toString();
                m_number_queue[1] = Integer.parseInt(num);
                addInOperationQueue(v.getId(), 1);
                num = "";
                //Log.d("click op2", m_op_queue[0] + "," + m_op_queue[1] + "," + m_number_queue[0] + "," + m_number_queue[1]);
            }
            if (getCountInOperationQueue() == 2) {
                //Log.d("click op3", m_op_queue[0] + "," + m_op_queue[1] + "," + m_number_queue[0] + "," + m_number_queue[1]);
                if (isPlusOperation(m_op_queue[0])) {
                    m_result = m_number_queue[0] + m_number_queue[1];

                }
                m_number_queue[1] = 0;
                m_number_queue[0] = m_result;
                m_op_queue[0] = m_op_queue[1];
                m_op_queue[1] = 0;
                num = "";
                result = Integer.toString(m_result);
                m_resultView.setText(result);
            }
        }

    }
}
