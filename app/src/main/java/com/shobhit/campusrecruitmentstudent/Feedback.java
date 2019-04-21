package com.shobhit.campusrecruitmentstudent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends Fragment {

    EditText etfeedback;
    Button btsend;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.feedback, container, false);
         etfeedback =  constraintLayout.findViewById(R.id.edittextfeed);
         btsend = constraintLayout.findViewById(R.id.buttonFeedback);

        btsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback  = etfeedback.getText().toString();

                DatabaseReference databaseCompany = FirebaseDatabase.getInstance().getReference("feedback").child("student");
                String key  = databaseCompany.push().getKey();
                databaseCompany.child(key).setValue(feedback);

            }
        });

        return constraintLayout;
    }
}
