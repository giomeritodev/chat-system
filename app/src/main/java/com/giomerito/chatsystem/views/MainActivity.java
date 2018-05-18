package com.giomerito.chatsystem.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.giomerito.chatsystem.MessageAdapter;
import com.giomerito.chatsystem.R;
import com.giomerito.chatsystem.model.FriendlyMessage;
import com.giomerito.chatsystem.util.constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = constants.ANONYMOUS;

        //Inicializa referencias das views
        mProgressBar = findViewById(R.id.progressBar_main_activity);
        mMessageListView = findViewById(R.id.list_view_main_activity_message);
        mPhotoPickerButton = findViewById(R.id.imagem_button_main_activity_photoPickerButton);
        mMessageEditText = findViewById(R.id.edittext_main_activity_messageEdit);
        mSendButton = findViewById(R.id.button_main_activity_send);

        //Inicializa message ListView e seu adapter
        List<FriendlyMessage> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        //Inicializa ProgressBar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        //ImagePickerButton mostra um seletor de imagens para fazer upload de uma imagem para uma mensagem
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO implementar selecionador de imagens
            }
        });

        //Ativar o botão Enviar quando houver texto para enviar
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() > 0){
                    mSendButton.setEnabled(true);
                }else{
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(constants.DEFAULT_MSG_LENGTH_LIMIT)});

        //enviar uma mensagem e limpar o EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO enviar message

                //Limpa o input box
                mMessageEditText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
