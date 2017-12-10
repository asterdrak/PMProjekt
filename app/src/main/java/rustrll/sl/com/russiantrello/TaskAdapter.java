package rustrll.sl.com.russiantrello;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    public Task task;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        task = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView taskName = (TextView) convertView.findViewById(R.id.taskName);
        TextView taskDueDate = (TextView) convertView.findViewById(R.id.taskDueDate);

        if(task.getMoscow() != "" && task.getMoscow() != null){
            LinearLayout buttons = (LinearLayout) convertView.findViewById(R.id.buttons);
            buttons.setVisibility(View.GONE);
        }
        // Populate the data into the template view using the data object
        taskName.setText(task.getName());
        taskDueDate.setText(task.getDueDate());

        Button mustButton = (Button) convertView.findViewById(R.id.must);
        mustButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setMoscow("must");
            }
        });

        Button couldButton = (Button) convertView.findViewById(R.id.could);
        mustButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setMoscow("could");
            }
        });

        Button shouldButton = (Button) convertView.findViewById(R.id.should);
        mustButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setMoscow("should");
            }
        });

        Button wontButton = (Button) convertView.findViewById(R.id.wont);
        mustButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setMoscow("wont");
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
