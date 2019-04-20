package com.shobhit.campusrecruitmentstudent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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

public class Jobs  extends Fragment {

    private DatabaseReference databaseJobs;
    ListView lvJobs;
    List<JobPostingModel> listJobs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.jobs, container, false);
        lvJobs = linearLayout.findViewById(R.id.listJobs);

        listJobs = new ArrayList<>();

        databaseJobs = FirebaseDatabase.getInstance().getReference("jobs");
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseJobs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listJobs.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    JobPostingModel jobPostingModel = postSnapshot.getValue(JobPostingModel.class);
                        listJobs.add(jobPostingModel);
                }

                JobPostingArray jobPostingArray = new JobPostingArray(getActivity(), listJobs);
                lvJobs.setAdapter(jobPostingArray);
                lvJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String jobId = listJobs.get(position).getJobId();
                        //System.out.println(jobId);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return linearLayout;
    }
}
