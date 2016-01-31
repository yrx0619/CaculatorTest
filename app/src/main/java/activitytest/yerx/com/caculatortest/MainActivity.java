package activitytest.yerx.com.caculatortest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {
    Button[] m_buttonNum;
    //Button m_buttonOp[4];
    //Button m_buttonDot;
    TextView m_reslutView;

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
    }

    private void setBtnNumClickListener(){
        int index = 0;
        for(;index < 10; index++)
            m_buttonNum[index].setOnClickListener(this);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_buttonNum = new Button[10];
        getBtnNumById();
        m_reslutView = (TextView)findViewById(R.id.caculator_text);
        //m_buttonNum[1].setOnClickListener(this);
        setBtnNumClickListener();
    }

    @Override
    public void onClick(View v) {
        String num;
        Button tmp_btn;
        int index = 0;
        for(; index < 10; index++)
        {
            if(isNotNumberButton(v.getId()) == Boolean.FALSE) {
                tmp_btn = (Button) findViewById(v.getId());
                num = tmp_btn.getText().toString();
                m_reslutView.setText(num);
            }
        }
    }
}
