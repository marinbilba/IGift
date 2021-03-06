package com.viauc.igift.ui.groups;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.SingleTop;
import com.viauc.igift.R;

@Parent
@SingleTop
@Layout(R.layout.groups_header_layout)
public class HeaderView {

    private static final String TAG = "HeaderView";

    @View(R.id.header_text)
    TextView headerText;

    private final Context mContext;
    private final String mHeaderText;

    public HeaderView(Context context,String headerText) {
        this.mContext = context;
        this.mHeaderText = headerText;
    }

    @Resolve
    private void onResolve(){
        headerText.setText(mHeaderText);
    }

    @Expand
    private void onExpand(){
    }

    @Collapse
    private void onCollapse(){
    }
}