package com.shobhit.campusrecruitmentstudent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment {

    TextView etstudentName, etage, etcontact, ethspercent, ethsyear, ethsboard, etSPercent, etSyear, etSBoard,
            etGCollegeNam , etGbranch , etCourse, etUniversity, etPercent, etYear, etback, etSkills;

    String studentId="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.view_student_profile, container, false);

        etstudentName = linearLayout.findViewById(R.id.textViewViewStudentName);
        etage = linearLayout.findViewById(R.id.textViewViewStudentAge);
        etcontact = linearLayout.findViewById(R.id.textViewViewStudentContact);
        ethspercent = linearLayout.findViewById(R.id.textViewViewStudentHPercent);
        ethsyear = linearLayout.findViewById(R.id.textViewViewStudentHYear);
        ethsboard = linearLayout.findViewById(R.id.textViewViewStudentHBoard);
        etSPercent = linearLayout.findViewById(R.id.textViewViewStudentSPercent);
        etSyear = linearLayout.findViewById(R.id.textViewViewStudentSYear);
        etSBoard = linearLayout.findViewById(R.id.textViewViewStudentSBoard);
        etGCollegeNam = linearLayout.findViewById(R.id.textViewViewStudentCollege);
        etGbranch = linearLayout.findViewById(R.id.textViewViewStudentBranch);
        etCourse = linearLayout.findViewById(R.id.textViewViewStudentCourse);
        etUniversity = linearLayout.findViewById(R.id.textViewViewStudentUniversity);
        etPercent = linearLayout.findViewById(R.id.textViewViewStudentPercent);
        etYear = linearLayout.findViewById(R.id.textViewViewStudentYear);
        etback = linearLayout.findViewById(R.id.textViewViewStudentBacks);
        etSkills = linearLayout.findViewById(R.id.textViewViewStudentSkills);


        studentId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference databaseRegister = FirebaseDatabase.getInstance().getReference("Students").child(studentId);

        System.out.println(studentId);
        databaseRegister.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                AllStudentView allStudentView = dataSnapshot.getValue(AllStudentView.class);
                System.out.println(allStudentView.getAge());
                System.out.println(allStudentView.getContact());
                System.out.println(allStudentView.getBack());
                etstudentName.setText( allStudentView.getStudentName());
                etage.setText(allStudentView.getAge());
                etcontact.setText(allStudentView.getContact());
                ethspercent.setText( allStudentView.getHspercent());
                ethsyear.setText(allStudentView.getHsyear());
                ethsboard.setText(allStudentView.getHsboard());
                etSPercent.setText( allStudentView.getsPercent());
                etSyear.setText(allStudentView.getSyear());
                etSBoard.setText(allStudentView.getgBoard());
                etGCollegeNam.setText(allStudentView.getgCollegeNam());
                etGbranch.setText( allStudentView.getGbranch());
                etCourse.setText(allStudentView.getCourse());
                etUniversity.setText(allStudentView.getUniversity());
                etPercent.setText(allStudentView.getPercent());
                etYear.setText( allStudentView.getYear());
                etback.setText(allStudentView.getBack());
                etSkills.setText(allStudentView.getSkills());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return linearLayout;
    }
}
