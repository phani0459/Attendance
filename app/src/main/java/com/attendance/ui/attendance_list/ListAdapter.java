package com.attendance.ui.attendance_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.attendance.R;
import com.attendance.database.User;
import com.attendance.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<User> mAttendanceList;

    public ListAdapter(List<User> attendanceList) {
        mAttendanceList = attendanceList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog_view, parent, false));
    }

    @Override
    public int getItemCount() {
        if (mAttendanceList != null && mAttendanceList.size() > 0) {
            return mAttendanceList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<User> userList) {
        mAttendanceList.addAll(userList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mAttendanceList = new ArrayList<>();
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.date_text_view)
        TextView dateTextView;

        @BindView(R.id.attendance_text_view)
        TextView attendanceTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            super.onBind(position);

            final User blog = mAttendanceList.get(position);
            attendanceTextView.setText("PRESENT");

            if (blog.getDate() != null) {
                dateTextView.setText(blog.getDate());
            }

            if (blog.getIsPresent() == 0) {
                attendanceTextView.setText("ABSENT");
            }
        }
    }

}
