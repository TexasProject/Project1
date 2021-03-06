

public class LoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle
savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText
etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText
etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin =
(Button) findViewById(R.id.bLogin);
        final TextView
registerLink = (TextView) findViewById(R.id.tvRegisterHere);


        registerLink.setOnClickListener(new View.OnClickListener()
{
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,
RegisterActivity.class);

                LoginActivity.this.startActivity(registerIntent);

            }
        });

        bLogin.setOnClickListener(new View.OnClickListener()
{
            @Override
            public void onClick(View v) {
                final String username =
etUserName.getText().toString();
                final String password =
etPassword.getText().toString();


                // Response
received from the server
                Response.Listener<String>
responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String
response) {
                        try {
                            JSONObject
jsonResponse = new JSONObject(response);
                            boolean success =
jsonResponse.getBoolean("success");

                            if (success) {




                                String
LastName = jsonResponse.getString("LastName");
                                Intent
intent = new Intent(LoginActivity.this, UserAreaActivity.class);

                               
intent.putExtra("username", username);
                               
intent.putExtra("LastName", LastName);

                               
LoginActivity.this.startActivity(intent);
                            } else {
                               
AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                               
builder.setMessage("Login Failed")
                                       
.setNegativeButton("Retry", null)
                                       
.create()
                                       
.show();
                            }

                        } catch (JSONException e)
{
                           
e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest
= new
LoginRequest(username,
password, responseListener);
                RequestQueue queue =
Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}

 

