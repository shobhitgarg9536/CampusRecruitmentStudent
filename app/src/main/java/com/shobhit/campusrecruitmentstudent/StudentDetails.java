package com.shobhit.campusrecruitmentstudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentDetails extends AppCompatActivity {

    EditText etstudentName, etage, etcontact, ethspercent, ethsyear, ethsboard, etSPercent, etSyear, etSBoard,
     etGCollegeNam , etGbranch , etCourse, etUniversity, etPercent, etYear, etback, etSkills;
    Button btregister;
    String studentName, age, contact, hspercent, hsyear, hsboard, SPercent, Syear, SBoard,
            GCollegeNam , Gbranch , Course, University, Percent, Year, back, Skills;

    SharedPreferences sharedPreferences;
    String SHARED_PREFS = "Shared_Register";

    private DatabaseReference databaseRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details);

        etstudentName = findViewById(R.id.editTextName);
        etage = findViewById(R.id.editTextAge);
        etcontact = findViewById(R.id.editTextContact);
        ethspercent = findViewById(R.id.editText10percantage);
        ethsyear = findViewById(R.id.editText10year);
        ethsboard = findViewById(R.id.editText10Board);
        etSPercent = findViewById(R.id.editText12Percentage);
        etSyear = findViewById(R.id.editText12year);
        etSBoard = findViewById(R.id.editTextBoard);
        etGCollegeNam = findViewById(R.id.editTextcollegeName);
        etGbranch = findViewById(R.id.editTextcollegeBranch);
        etCourse = findViewById(R.id.editTextcollegeCourse);
        etUniversity = findViewById(R.id.editTextcollegeUniversity);
        etPercent = findViewById(R.id.editTextcollegePercentage);
        etYear = findViewById(R.id.editTextcollegeYear);
        etback = findViewById(R.id.editTextcollegeBackLock);
        etSkills = findViewById(R.id.editTextcollegeSkills);


        btregister = findViewById(R.id.buttoncollegeregister);

        databaseRegister = FirebaseDatabase.getInstance().getReference("Students");

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                studentName = etstudentName.getText().toString();
                age = etage.getText().toString();
                contact = etcontact.getText().toString();
                hspercent = ethspercent.getText().toString();
                hsyear = ethsyear.getText().toString();
                hsboard = ethsboard.getText().toString();
                SPercent = etSPercent.getText().toString();
                Syear = etSyear.getText().toString();
                SBoard = etSBoard.getText().toString();
                GCollegeNam = etGCollegeNam.getText().toString();
                Gbranch = etGbranch.getText().toString();
                Course = etCourse.getText().toString();
                University = etUniversity.getText().toString();
                Percent = etPercent.getText().toString();
                Year = etYear.getText().toString();
                back = etback.getText().toString();
                Skills = etSkills.getText().toString();

                if (!TextUtils.isEmpty(studentName) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(contact) &&
                        !TextUtils.isEmpty(hspercent) && !TextUtils.isEmpty(hsyear)
                        && !TextUtils.isEmpty(hsboard) && !TextUtils.isEmpty(SPercent) &&
                        !TextUtils.isEmpty(Syear) && !TextUtils.isEmpty(SBoard)
                        && !TextUtils.isEmpty(GCollegeNam) && !TextUtils.isEmpty(Gbranch) &&
                        !TextUtils.isEmpty(Course) && !TextUtils.isEmpty(University)
                        && !TextUtils.isEmpty(Percent) && !TextUtils.isEmpty(Year) &&
                        !TextUtils.isEmpty(back) && !TextUtils.isEmpty(Skills)) {
                    databaseRegister.push().getKey();
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    StudentRegister studentRegister = new StudentRegister(userId, studentName, age, contact, hspercent,
                            hsyear , hsboard, SPercent, Syear, SBoard,GCollegeNam, Gbranch,Course,
                            University, Percent, Year ,back, Skills);
                    databaseRegister.child(userId).setValue(studentRegister);
                    Toast.makeText(StudentDetails.this, "Details Added", Toast.LENGTH_LONG).show();
                    sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Email",email);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), StudentNavbar.class));
                }
            }
        });
    }
}