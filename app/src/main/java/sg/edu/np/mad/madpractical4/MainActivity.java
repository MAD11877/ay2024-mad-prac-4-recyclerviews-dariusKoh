package sg.edu.np.mad.madpractical4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize a new User object
        User user = new User("John Doe", "MAD Developer", 1, false);

        Intent recieveIntentAdapter = getIntent();
        user.setName(recieveIntentAdapter.getExtras().getString("name","Name"));
        user.setDescription(recieveIntentAdapter.getExtras().getString("description","Description"));
        user.setFollowed(recieveIntentAdapter.getExtras().getBoolean("followed",false));
        user.setId(recieveIntentAdapter.getExtras().getInt("id", 1));

        // Get the TextViews and Button from the Layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);
        Button btnMessage = findViewById(R.id.btnMessage);

        // Set the TextViews with the User's name, description and default button message
        tvName.setText(user.name);
        tvDescription.setText(user.description);
        if (user.followed){
            user.followed = false;
            btnFollow.setText("Follow");
            Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
        } else {
            user.followed = true;
            btnFollow.setText("Unfollow");
            Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
        }

        // 5.1
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.followed){
                    user.followed = false;
                    btnFollow.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();

                } else {
                    user.followed = true;
                    btnFollow.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMessageGroup = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(intentMessageGroup);
            }
        });
    }
}