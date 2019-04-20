package com.shobhit.campusrecruitmentstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AppliedJobs extends Fragment {

    private DatabaseReference databaseStudent,databaseAppliedJobs;
    ListView lvJobs;
    List<JobPostingModel> listJobs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.applied_jobs, container, false);
        lvJobs = linearLayout.findViewById(R.id.listJobs);

        listJobs = new ArrayList<>();

        String studentId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseStudent = FirebaseDatabase.getInstance().getReference("Students").child(studentId).child("applied");


        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listJobs.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    System.out.println(postSnapshot.getKey());
                    databaseAppliedJobs = FirebaseDatabase.getInstance().getReference("jobs").child(postSnapshot.getKey());

                    databaseAppliedJobs.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            JobPostingModel jobPostingModel = dataSnapshot.getValue(JobPostingModel.class);
                                listJobs.add(jobPostingModel);
                            JobPostingArray jobPostingArray = new JobPostingArray(getActivity(), listJobs);
                            lvJobs.setAdapter(jobPostingArray);

                            lvJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String jobId = listJobs.get(position).getJobId();
                                    //System.out.println(jobId);
                                    Intent i = new Intent(getContext(), ViewAppliedJob.class);
                                    i.putExtra("jobId",jobId);
                                    startActivity(i);
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }



                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return linearLayout;
    }
}