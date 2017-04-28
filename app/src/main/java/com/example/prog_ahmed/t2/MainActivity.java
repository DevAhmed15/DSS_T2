package com.example.prog_ahmed.t2;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBhelper db;
    EditText name,mail,marks,pass,id;
    Button add,view,upd,del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DBhelper(this);
        name=(EditText)findViewById(R.id.name);
        mail=(EditText)findViewById(R.id.email);
        marks=(EditText)findViewById(R.id.marks);
        pass=(EditText)findViewById(R.id.password);
        id=(EditText)findViewById(R.id.idUp);
        add=(Button)findViewById(R.id.addBu);
        view=(Button)findViewById(R.id.viewBu);
        upd=(Button)findViewById(R.id.up);
        del=(Button)findViewById(R.id.delete);

        addData();
        showAll();
        update();

    }
    public void addData(){
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
boolean Isin=db.insertData(name.getText().toString(),mail.getText().toString(),marks.getText().toString(),pass.getText().toString());
                    if(Isin == true)
                        Toast.makeText(MainActivity.this," Inserted Successfully ",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this," ERROR ",Toast.LENGTH_LONG).show();
                    }});
    }
    public void showAll()
    {
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=db.getAll();
                        if(res.getCount() == 0)
                        {
                            showMsg("Error ","No Data");
                            return;
                        }
                        StringBuffer bu=new StringBuffer();
                        while (res.moveToNext()){
                            bu.append("ID : "+res.getString(0)+"\n");
                            bu.append("Name : "+res.getString(1)+"\n");
                            bu.append("Email : "+res.getString(2)+"\n");
                            bu.append("Marks : "+res.getString(3)+"\n");
                            bu.append("Password : "+res.getString(4)+"\n");
                        }
                        showMsg("Data",bu.toString());

                    }});
    }
    public void update(){
     upd.setOnClickListener(
             new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     boolean isUpd=db.UpdateData(id.getText().toString(),name.getText().toString(),
                             mail.getText().toString(),marks.getText().toString(),pass.getText().toString());
                     if(isUpd == true)
                         Toast.makeText(MainActivity.this," Updated Successfully ",Toast.LENGTH_LONG).show();
                     else
                         Toast.makeText(MainActivity.this," ERROR ",Toast.LENGTH_LONG).show();
                 }
             }
     );
    }
    public void DeleteData(){
        del.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows=db.delete(id.getText().toString());
                        if(deletedRows > 1)
                            Toast.makeText(MainActivity.this," Deleted Successfully ",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this," ERROR ",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void showMsg(String title,String Msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
      builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Msg);
        builder.show();
    }
}
