package com.shobhit.campusrecruitmentstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewAppliedJob extends AppCompatActivity {

    TextView tvCompanyName, tvabout, tvaddress, tvcity, tvcontact, tvvacancyname, tvpostname, tvnoofvacancy, tvdateofinterview,
            tvcontactperson;
    Button btapply;

    String jobId="";
    String companyId="";
    private DatabaseReference databaseJobs, databaseRegister,databaseStudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_job);

        tvCompanyName = findViewById(R.id.textViewjobsCompanyName);
        tvabout = findViewById(R.id.textView4jobCompanyAbout);
        tvaddress = findViewById(R.id.textViewjobCompanyAddress);
        tvcity = findViewById(R.id.textViewjobCompanyCity);
        tvcontact = findViewById(R.id.textViewjobCompanyContact);
        tvvacancyname = findViewById(R.id.textViewjobCompanyVanacyName);
        tvpostname = findViewById(R.id.textViewjobCompanyPostName);
        tvnoofvacancy = findViewById(R.id.textViewjobCompanyNoOfVacancy);
        tvdateofinterview = findViewById(R.id.textViewjobCompanyDateOfInterview);
        tvcontactperson = findViewById(R.id.textViewjobCompanyContactPerson);
        btapply = findViewById(R.id.buttonApply);
        btapply.setVisibility(View.GONE);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            jobId =(String) b.get("jobId");
        }
        databaseJobs = FirebaseDatabase.getInstance().getReference("jobs").child(jobId);
        databaseJobs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ApplyJobModel applyJobModel = dataSnapshot.getValue(ApplyJobModel.class);
                tvcontactperson.setText(applyJobModel.getContactPerson());
                tvdateofinterview.setText(applyJobModel.getDateOfInterview());
                tvnoofvacancy.setText(applyJobModel.getNoOfVacancy());
                tvpostname.setText(applyJobModel.getPostName());
                tvvacancyname.setText(applyJobModel.getVacancyName());
                companyId = applyJobModel.getCompanyId();

                databaseRegister = FirebaseDatabase.getInstance().getReference("register").child(applyJobModel.getCompanyId());
                System.out.println(databaseJobs.toString());
                databaseRegister.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        ApplyJobCompanyDetailModel applyJobModel = dataSnapshot.getValue(ApplyJobCompanyDetailModel.class);
                        tvCompanyName.setText(applyJobModel.getName());
                        tvcontact.setText(applyJobModel.getContact());
                        tvabout.setText(applyJobModel.getAbout());
                        tvaddress.setText(applyJobModel.getAddress());
                        tvcity.setText(applyJobModel.getCity());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
