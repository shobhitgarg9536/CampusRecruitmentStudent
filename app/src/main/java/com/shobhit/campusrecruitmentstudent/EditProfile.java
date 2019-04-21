package com.shobhit.campusrecruitmentstudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends Fragment {

    EditText etstudentName, etage, etcontact, ethspercent, ethsyear, ethsboard, etSPercent, etSyear, etSBoard,
            etGCollegeNam , etGbranch , etCourse, etUniversity, etPercent, etYear, etback, etSkills;
    Button btregister;
    String studentName, age, contact, hspercent, hsyear, hsboard, SPercent, Syear, SBoard,
            GCollegeNam , Gbranch , Course, University, Percent, Year, back, Skills;

    SharedPreferences sharedPreferences;
    String SHARED_PREFS = "Shared_Register";

    private DatabaseReference databaseRegister;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.student_details, container, false);

        etstudentName = linearLayout.findViewById(R.id.editTextName);
        etage = linearLayout.findViewById(R.id.editTextAge);
        etcontact = linearLayout.findViewById(R.id.editTextContact);
        ethspercent = linearLayout.findViewById(R.id.editText10percantage);
        ethsyear = linearLayout.findViewById(R.id.editText10year);
        ethsboard = linearLayout.findViewById(R.id.editText10Board);
        etSPercent = linearLayout.findViewById(R.id.editText12Percentage);
        etSyear = linearLayout.findViewById(R.id.editText12year);
        etSBoard = linearLayout.findViewById(R.id.editTextBoard);
        etGCollegeNam = linearLayout.findViewById(R.id.editTextcollegeName);
        etGbranch = linearLayout.findViewById(R.id.editTextcollegeBranch);
        etCourse = linearLayout.findViewById(R.id.editTextcollegeCourse);
        etUniversity = linearLayout.findViewById(R.id.editTextcollegeUniversity);
        etPercent = linearLayout.findViewById(R.id.editTextcollegePercentage);
        etYear = linearLayout.findViewById(R.id.editTextcollegeYear);
        etback = linearLayout.findViewById(R.id.editTextcollegeBackLock);
        etSkills = linearLayout.findViewById(R.id.editTextcollegeSkills);


        btregister = linearLayout.findViewById(R.id.buttoncollegeregister);

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
                    Toast.makeText(getContext(), "Details Added", Toast.LENGTH_LONG).show();
                  /*  sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Email",email);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), StudentNavbar.class));
                */}
            }
        });
        return linearLayout;
    }
}
